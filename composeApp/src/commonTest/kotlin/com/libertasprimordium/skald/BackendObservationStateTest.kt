package com.libertasprimordium.skald

import com.libertasprimordium.skald.domain.core.NetworkEnvironment
import com.libertasprimordium.skald.domain.core.SatsAmount
import com.libertasprimordium.skald.domain.onchain.BackendObservationBlockingIssue
import com.libertasprimordium.skald.domain.onchain.BackendObservationCapability
import com.libertasprimordium.skald.domain.onchain.BackendObservationPolicy
import com.libertasprimordium.skald.domain.onchain.BackendObservationPolicyRequest
import com.libertasprimordium.skald.domain.onchain.BackendObservationSession
import com.libertasprimordium.skald.domain.onchain.BackendObservationSessionId
import com.libertasprimordium.skald.domain.onchain.BackendObservationSource
import com.libertasprimordium.skald.domain.onchain.BackendObservationStatus
import com.libertasprimordium.skald.domain.onchain.BackendObservationTrust
import com.libertasprimordium.skald.domain.onchain.BackendObservationWarning
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletProfileId
import com.libertasprimordium.skald.domain.onchain.ObservedConfirmationKind
import com.libertasprimordium.skald.domain.onchain.ObservedConfirmationState
import com.libertasprimordium.skald.domain.onchain.ObservedOutpoint
import com.libertasprimordium.skald.domain.onchain.ObservedScriptClass
import com.libertasprimordium.skald.domain.onchain.ObservedUtxo
import com.libertasprimordium.skald.domain.onchain.ObservedUtxoId
import com.libertasprimordium.skald.domain.onchain.ObservedUtxoLifecycle
import com.libertasprimordium.skald.domain.onchain.ObservedUtxoRiskFlag
import com.libertasprimordium.skald.domain.onchain.ObservedUtxoSpendReadiness
import com.libertasprimordium.skald.domain.onchain.ObservedWalletScope
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressDerivationIndex
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressLifecycleState
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressPolicyBlockingIssue
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressPolicyDecisionState
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressPolicyWarning
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressSource
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressState
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressWalletContext
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressWalletOperationalState
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class BackendObservationStateTest {
    @Test
    fun displayedAddressRemainsUnusedBeforeBackendObservation() {
        val wallet = operationalWallet()
        val displayed = displayedAddress(wallet)

        val summary = BackendObservationPolicy.evaluate(
            BackendObservationPolicyRequest(
                session = BackendObservationSession.localRegtestElectrumHarness(),
                wallet = wallet,
                candidate = displayed,
                observedUtxos = emptyList(),
            ),
        )

        assertEquals(ReceiveAddressLifecycleState.Displayed, displayed.lifecycleState)
        assertFalse(displayed.isUsed)
        assertNull(summary.addressUsage)
        assertContains(summary.blockingIssues, BackendObservationBlockingIssue.NoReceiveObservation)
    }

    @Test
    fun unconfirmedObservationMarksAddressUsedWithoutAuthorizingSpend() {
        val wallet = operationalWallet()
        val displayed = displayedAddress(wallet)
        val utxo = observedUtxo(
            wallet = wallet,
            address = displayed,
            lifecycle = ObservedUtxoLifecycle.BackendObservedUnconfirmed,
            confirmationState = ObservedConfirmationState.unconfirmed(),
        )

        val summary = BackendObservationPolicy.evaluate(
            BackendObservationPolicyRequest(
                session = BackendObservationSession.localRegtestElectrumHarness(),
                wallet = wallet,
                candidate = displayed,
                observedUtxos = listOf(utxo),
            ),
        )

        val usage = assertNotNull(summary.addressUsage)
        assertEquals(BackendObservationStatus.Completed, summary.status)
        assertEquals(ReceiveAddressLifecycleState.Displayed, usage.lifecycleBefore)
        assertEquals(ReceiveAddressLifecycleState.ObservedUnconfirmed, usage.lifecycleAfter)
        assertTrue(usage.addressMarkedUsed)
        assertEquals(ReceiveAddressPolicyDecisionState.WarningRequired, usage.reuseDecision.state)
        assertContains(usage.reuseDecision.warnings, ReceiveAddressPolicyWarning.AddressReuseRequiresExplicitConfirmation)
        assertContains(usage.reuseDecision.blockingIssues, ReceiveAddressPolicyBlockingIssue.AddressAlreadyUsed)
        assertContains(summary.warnings, BackendObservationWarning.CoinControlRequiredBeforeSpend)
        assertContains(summary.warnings, BackendObservationWarning.ObservationDoesNotAuthorizeSpending)
        assertFalse(summary.anySpendableWithoutCoinControl)
        assertFalse(summary.signingOrBroadcastEnabled)
        assertFalse(summary.productionSyncEnabled)
    }

    @Test
    fun confirmedObservationCarriesConfirmationDepthAndMarksAddressUsed() {
        val wallet = operationalWallet()
        val displayed = displayedAddress(wallet)
        val utxo = observedUtxo(
            wallet = wallet,
            address = displayed,
            lifecycle = ObservedUtxoLifecycle.BackendObservedConfirmed,
            confirmationState = ObservedConfirmationState.confirmed(3),
        )

        val summary = BackendObservationPolicy.evaluate(
            BackendObservationPolicyRequest(
                session = BackendObservationSession.localRegtestElectrumHarness(),
                wallet = wallet,
                candidate = displayed,
                observedUtxos = listOf(utxo),
            ),
        )

        assertEquals(BackendObservationStatus.Completed, summary.status)
        assertEquals(1, summary.observedUtxoCount)
        assertEquals(SatsAmount(25_000), summary.totalAmount)
        assertEquals(ObservedConfirmationKind.Confirmed, summary.observedUtxos.single().confirmationState.kind)
        assertEquals(3, summary.observedUtxos.single().confirmationState.depth)
        assertEquals(ReceiveAddressLifecycleState.ObservedConfirmed, assertNotNull(summary.addressUsage).lifecycleAfter)
    }

    @Test
    fun mainnetObservationRequestsAreRejected() {
        val wallet = operationalWallet(network = NetworkEnvironment.MainnetDisabled)
        val displayed = displayedAddress(wallet)
        val session = BackendObservationSession(
            id = BackendObservationSessionId("mainnet-disabled-observation"),
            network = NetworkEnvironment.MainnetDisabled,
            source = BackendObservationSource.UserSelectedElectrum,
            trust = BackendObservationTrust.UserOwnedNode,
            status = BackendObservationStatus.Observing,
            capabilities = defaultCapabilities(),
        )

        val summary = BackendObservationPolicy.evaluate(
            BackendObservationPolicyRequest(
                session = session,
                wallet = wallet,
                candidate = displayed,
                observedUtxos = listOf(
                    observedUtxo(
                        wallet = wallet,
                        address = displayed,
                        network = NetworkEnvironment.MainnetDisabled,
                        lifecycle = ObservedUtxoLifecycle.BackendObservedConfirmed,
                        confirmationState = ObservedConfirmationState.confirmed(1),
                    ),
                ),
            ),
        )

        assertEquals(BackendObservationStatus.Blocked, summary.status)
        assertContains(summary.blockingIssues, BackendObservationBlockingIssue.MainnetDisabled)
        assertContains(summary.warnings, BackendObservationWarning.MainnetDisabled)
        assertNull(summary.addressUsage)
        assertFalse(session.mainnetEnabled)
    }

    @Test
    fun publicBackendTrustProducesPrivacyWarnings() {
        val session = BackendObservationSession(
            id = BackendObservationSessionId("public-backend-observation"),
            network = NetworkEnvironment.Signet,
            source = BackendObservationSource.UserSelectedElectrum,
            trust = BackendObservationTrust.UserSelectedPublicBackend,
            status = BackendObservationStatus.Observing,
            capabilities = defaultCapabilities(),
        )

        val warnings = BackendObservationPolicy.warningsForSession(session)

        assertTrue(session.usesPublicBackend)
        assertContains(warnings, BackendObservationWarning.PublicBackendPrivacyLeak)
        assertContains(warnings, BackendObservationWarning.BackendCanLinkWalletQueries)
        assertFalse(session.usesSkaldManagedInfrastructure)
    }

    @Test
    fun onionAndLocalBackendTrustRemainExplicitlyLabeled() {
        val localWarnings = BackendObservationPolicy.warningsForSession(
            BackendObservationSession.localRegtestElectrumHarness(),
        )
        val onionWarnings = BackendObservationPolicy.warningsForSession(
            BackendObservationSession(
                id = BackendObservationSessionId("onion-backend-observation"),
                network = NetworkEnvironment.Signet,
                source = BackendObservationSource.UserSelectedElectrum,
                trust = BackendObservationTrust.UserSelectedOnionBackend,
                status = BackendObservationStatus.Observing,
                capabilities = defaultCapabilities(),
            ),
        )

        assertContains(localWarnings, BackendObservationWarning.LocalRegtestOnly)
        assertContains(onionWarnings, BackendObservationWarning.OnionBackendSelected)
        assertContains(onionWarnings, BackendObservationWarning.BackendCanLinkWalletQueries)
    }

    @Test
    fun staleAndConflictingObservationsDoNotBecomeSafe() {
        val wallet = operationalWallet()
        val displayed = displayedAddress(wallet)
        val stale = observedUtxo(
            wallet = wallet,
            address = displayed,
            lifecycle = ObservedUtxoLifecycle.UnknownStale,
            confirmationState = ObservedConfirmationState(ObservedConfirmationKind.UnknownStale),
            riskFlags = setOf(ObservedUtxoRiskFlag.StaleObservation),
            spendReadiness = ObservedUtxoSpendReadiness.StaleOrConflictingBlocked,
        )
        val conflicting = observedUtxo(
            wallet = wallet,
            address = displayed,
            lifecycle = ObservedUtxoLifecycle.ConflictingOrReorgRisk,
            confirmationState = ObservedConfirmationState(ObservedConfirmationKind.ConflictingOrReorgRisk),
            riskFlags = setOf(ObservedUtxoRiskFlag.ReorgOrConflictRisk),
            spendReadiness = ObservedUtxoSpendReadiness.StaleOrConflictingBlocked,
        )

        val staleSummary = BackendObservationPolicy.evaluate(
            BackendObservationPolicyRequest(
                session = BackendObservationSession.localRegtestElectrumHarness(),
                wallet = wallet,
                candidate = displayed,
                observedUtxos = listOf(stale),
            ),
        )
        val conflictSummary = BackendObservationPolicy.evaluate(
            BackendObservationPolicyRequest(
                session = BackendObservationSession.localRegtestElectrumHarness(),
                wallet = wallet,
                candidate = displayed,
                observedUtxos = listOf(conflicting),
            ),
        )

        assertEquals(BackendObservationStatus.Stale, staleSummary.status)
        assertContains(staleSummary.blockingIssues, BackendObservationBlockingIssue.StaleObservation)
        assertContains(staleSummary.warnings, BackendObservationWarning.StaleObservationNotSafe)
        assertNull(staleSummary.addressUsage)
        assertFalse(staleSummary.anySpendableWithoutCoinControl)

        assertEquals(BackendObservationStatus.Blocked, conflictSummary.status)
        assertContains(conflictSummary.blockingIssues, BackendObservationBlockingIssue.ConflictingObservation)
        assertContains(conflictSummary.warnings, BackendObservationWarning.ReorgOrConflictRisk)
        assertNull(conflictSummary.addressUsage)
        assertFalse(conflictSummary.anySpendableWithoutCoinControl)
    }

    @Test
    fun identityLinkedAndImportedKeySourcesCarryFutureRiskWarnings() {
        val nostrWallet = operationalWallet(source = ReceiveAddressSource.NostrNsecImportedSpend)
        val importedWallet = operationalWallet(source = ReceiveAddressSource.ImportedSingleKey)

        val nostrSummary = BackendObservationPolicy.evaluate(
            BackendObservationPolicyRequest(
                session = BackendObservationSession.localRegtestElectrumHarness(),
                wallet = nostrWallet,
                candidate = displayedAddress(nostrWallet),
                observedUtxos = listOf(
                    observedUtxo(
                        wallet = nostrWallet,
                        address = displayedAddress(nostrWallet),
                        riskFlags = setOf(
                            ObservedUtxoRiskFlag.IdentityLinkedSource,
                            ObservedUtxoRiskFlag.ImportedKeySource,
                            ObservedUtxoRiskFlag.CoinControlRequired,
                        ),
                    ),
                ),
            ),
        )
        val importedSummary = BackendObservationPolicy.evaluate(
            BackendObservationPolicyRequest(
                session = BackendObservationSession.localRegtestElectrumHarness(),
                wallet = importedWallet,
                candidate = displayedAddress(importedWallet),
                observedUtxos = listOf(
                    observedUtxo(
                        wallet = importedWallet,
                        address = displayedAddress(importedWallet),
                        riskFlags = setOf(
                            ObservedUtxoRiskFlag.ImportedKeySource,
                            ObservedUtxoRiskFlag.CoinControlRequired,
                        ),
                    ),
                ),
            ),
        )

        assertContains(nostrSummary.warnings, BackendObservationWarning.IdentityLinkedUtxo)
        assertContains(nostrSummary.warnings, BackendObservationWarning.ImportedKeyBackupRisk)
        assertContains(importedSummary.warnings, BackendObservationWarning.ImportedKeyBackupRisk)
        assertFalse(importedSummary.warnings.contains(BackendObservationWarning.IdentityLinkedUtxo))
    }

    private fun operationalWallet(
        network: NetworkEnvironment = NetworkEnvironment.Regtest,
        source: ReceiveAddressSource = ReceiveAddressSource.NativeDescriptor,
    ): ReceiveAddressWalletContext =
        ReceiveAddressWalletContext(
            profileId = DescriptorWalletProfileId("observation-policy-${network.name.lowercase()}-${source.name.lowercase()}"),
            profileLabel = "Backend observation policy placeholder",
            network = network,
            source = source,
            operationalState = ReceiveAddressWalletOperationalState.OperationalDevelopmentWallet,
            canDeriveReceiveAddresses = true,
        )

    private fun displayedAddress(wallet: ReceiveAddressWalletContext): ReceiveAddressState =
        ReceiveAddressState.placeholderReserved(
            wallet = wallet,
            derivationIndex = ReceiveAddressDerivationIndex(0),
        ).markDisplayed()

    private fun observedUtxo(
        wallet: ReceiveAddressWalletContext,
        address: ReceiveAddressState,
        network: NetworkEnvironment = wallet.network,
        lifecycle: ObservedUtxoLifecycle = ObservedUtxoLifecycle.BackendObservedConfirmed,
        confirmationState: ObservedConfirmationState = ObservedConfirmationState.confirmed(1),
        riskFlags: Set<ObservedUtxoRiskFlag> = setOf(ObservedUtxoRiskFlag.CoinControlRequired),
        spendReadiness: ObservedUtxoSpendReadiness = ObservedUtxoSpendReadiness.CoinControlRequired,
    ): ObservedUtxo =
        ObservedUtxo(
            id = ObservedUtxoId("OBSERVED_UTXO_NOT_REAL"),
            walletProfileId = wallet.profileId,
            sourceAddressId = address.id,
            network = network,
            amount = SatsAmount(25_000),
            outpoint = ObservedOutpoint.NotReal,
            lifecycle = lifecycle,
            confirmationState = confirmationState,
            scriptClass = ObservedScriptClass.Bip86P2tr,
            walletScope = ObservedWalletScope.NativeDescriptor,
            riskFlags = riskFlags,
            spendReadiness = spendReadiness,
        )

    private fun defaultCapabilities(): Set<BackendObservationCapability> =
        setOf(
            BackendObservationCapability.AddressUsageObservation,
            BackendObservationCapability.UtxoObservation,
            BackendObservationCapability.BackendTrustMetadata,
            BackendObservationCapability.NoProductionSync,
            BackendObservationCapability.NoSigning,
            BackendObservationCapability.NoBroadcasting,
            BackendObservationCapability.NoMainnet,
        )
}
