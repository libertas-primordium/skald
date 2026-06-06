package com.libertasprimordium.skald

import com.libertasprimordium.skald.domain.core.NetworkEnvironment
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletCreationIntent
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletProfileId
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletWorkflow
import com.libertasprimordium.skald.domain.onchain.EditableDescriptorWalletProfileInput
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressDerivationIndex
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressLifecycleState
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressObservation
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressPolicy
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressPolicyAction
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressPolicyBlockingIssue
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressPolicyDecisionState
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressPolicyRequest
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressPolicyWarning
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressSource
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressState
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressWalletContext
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressWalletOperationalState
import com.libertasprimordium.skald.security.DisabledSecureSecretStorage
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ReceiveAddressPolicyTest {
    @Test
    fun displayedAddressIsNotUsedUntilBackendObservationExists() {
        val reserved = reservedAddress().markDisplayed()

        assertEquals(ReceiveAddressLifecycleState.Displayed, reserved.lifecycleState)
        assertTrue(reserved.lifecycleState.displayedToUser)
        assertFalse(reserved.lifecycleState.observedByBackend)
        assertFalse(reserved.isUsed)
        assertTrue(reserved.safeForNormalReceiveUse)
    }

    @Test
    fun unconfirmedBackendObservationMarksAddressUsedAndUnsafeForReuse() {
        val observed = reservedAddress()
            .markDisplayed()
            .markObserved(ReceiveAddressObservation.UnconfirmedReceive)

        assertEquals(ReceiveAddressLifecycleState.ObservedUnconfirmed, observed.lifecycleState)
        assertTrue(observed.lifecycleState.observedByBackend)
        assertTrue(observed.isUsed)
        assertFalse(observed.safeForNormalReceiveUse)
    }

    @Test
    fun confirmedBackendObservationMarksAddressUsedAndUnsafeForReuse() {
        val observed = reservedAddress()
            .markDisplayed()
            .markObserved(ReceiveAddressObservation.ConfirmedReceive)

        assertEquals(ReceiveAddressLifecycleState.ObservedConfirmed, observed.lifecycleState)
        assertTrue(observed.lifecycleState.observedByBackend)
        assertTrue(observed.isUsed)
        assertFalse(observed.safeForNormalReceiveUse)
    }

    @Test
    fun freshReceiveRequestIsBlockedForCurrentNonOperationalProfileMetadata() {
        val context = ReceiveAddressWalletContext.fromProfile(nonOperationalProfile())

        val decision = ReceiveAddressPolicy.evaluate(
            ReceiveAddressPolicyRequest(
                action = ReceiveAddressPolicyAction.RequestFreshReceiveAddress,
                wallet = context,
            ),
        )

        assertEquals(ReceiveAddressPolicyDecisionState.Blocked, decision.state)
        assertFalse(decision.allowsAction)
        assertContains(decision.blockingIssues, ReceiveAddressPolicyBlockingIssue.WalletProfileNonOperational)
        assertContains(decision.blockingIssues, ReceiveAddressPolicyBlockingIssue.ReceiveDerivationDisabled)
    }

    @Test
    fun mainnetReceivePolicyIsRejectedBeforeAddressUse() {
        val context = operationalWallet(NetworkEnvironment.MainnetDisabled)

        val decision = ReceiveAddressPolicy.evaluate(
            ReceiveAddressPolicyRequest(
                action = ReceiveAddressPolicyAction.RequestFreshReceiveAddress,
                wallet = context,
            ),
        )

        assertEquals(ReceiveAddressPolicyDecisionState.Blocked, decision.state)
        assertContains(decision.blockingIssues, ReceiveAddressPolicyBlockingIssue.MainnetDisabled)
        assertTrue(NetworkEnvironment.entries.none { it.allowsMainnetOperations })
    }

    @Test
    fun regtestAndSignetAreAllowedPolicyNetworksWithoutMainnetOperations() {
        assertTrue(ReceiveAddressPolicy.isDevelopmentReceiveNetworkAllowed(NetworkEnvironment.Regtest))
        assertTrue(ReceiveAddressPolicy.isDevelopmentReceiveNetworkAllowed(NetworkEnvironment.Signet))
        assertFalse(ReceiveAddressPolicy.isDevelopmentReceiveNetworkAllowed(NetworkEnvironment.MainnetDisabled))

        listOf(NetworkEnvironment.Regtest, NetworkEnvironment.Signet).forEach { network ->
            val decision = ReceiveAddressPolicy.evaluate(
                ReceiveAddressPolicyRequest(
                    action = ReceiveAddressPolicyAction.RequestFreshReceiveAddress,
                    wallet = operationalWallet(network),
                ),
            )

            assertEquals(ReceiveAddressPolicyDecisionState.Allowed, decision.state)
            assertTrue(decision.allowsAction)
            assertContains(decision.warnings, ReceiveAddressPolicyWarning.BackendObservationRequiredBeforeMarkingUsed)
        }
    }

    @Test
    fun unusedReservedAddressCanBeShownAgainWithObservationWarning() {
        val wallet = operationalWallet()
        val candidate = reservedAddress(wallet)

        val decision = ReceiveAddressPolicy.evaluate(
            ReceiveAddressPolicyRequest(
                action = ReceiveAddressPolicyAction.ReshowReservedOrDisplayedAddress,
                wallet = wallet,
                candidate = candidate,
            ),
        )

        assertEquals(ReceiveAddressPolicyDecisionState.Allowed, decision.state)
        assertTrue(decision.allowsAction)
        assertContains(decision.warnings, ReceiveAddressPolicyWarning.ReservedAddressMayBeShownAgainIfUnused)
        assertContains(decision.warnings, ReceiveAddressPolicyWarning.BackendObservationRequiredBeforeMarkingUsed)
    }

    @Test
    fun reuseAttemptProducesHighFrictionWarningForObservedAddress() {
        val wallet = operationalWallet()
        val candidate = reservedAddress(wallet)
            .markDisplayed()
            .markObserved(ReceiveAddressObservation.ConfirmedReceive)

        val decision = ReceiveAddressPolicy.evaluate(
            ReceiveAddressPolicyRequest(
                action = ReceiveAddressPolicyAction.AttemptAddressReuse,
                wallet = wallet,
                candidate = candidate,
            ),
        )

        assertEquals(ReceiveAddressPolicyDecisionState.WarningRequired, decision.state)
        assertFalse(decision.allowsAction)
        assertTrue(decision.requiresExplicitConfirmation)
        assertContains(decision.warnings, ReceiveAddressPolicyWarning.AddressReuseRequiresExplicitConfirmation)
        assertContains(decision.warnings, ReceiveAddressPolicyWarning.ObservedAddressReuseIsUnsafe)
        assertContains(decision.blockingIssues, ReceiveAddressPolicyBlockingIssue.AddressAlreadyUsed)
    }

    @Test
    fun explicitReuseAttemptRequiresConfirmationEvenBeforeObservation() {
        val wallet = operationalWallet()
        val candidate = reservedAddress(wallet).markDisplayed()

        val decision = ReceiveAddressPolicy.evaluate(
            ReceiveAddressPolicyRequest(
                action = ReceiveAddressPolicyAction.AttemptAddressReuse,
                wallet = wallet,
                candidate = candidate,
            ),
        )

        assertEquals(ReceiveAddressPolicyDecisionState.WarningRequired, decision.state)
        assertFalse(decision.allowsAction)
        assertTrue(decision.requiresExplicitConfirmation)
        assertContains(decision.warnings, ReceiveAddressPolicyWarning.AddressReuseRequiresExplicitConfirmation)
        assertFalse(decision.blockingIssues.contains(ReceiveAddressPolicyBlockingIssue.AddressAlreadyUsed))
    }

    @Test
    fun nostrIdentityLinkedSourceAddsFutureIdentityWarning() {
        val wallet = operationalWallet(source = ReceiveAddressSource.NostrNsecImportedSpend)

        val decision = ReceiveAddressPolicy.evaluate(
            ReceiveAddressPolicyRequest(
                action = ReceiveAddressPolicyAction.RequestFreshReceiveAddress,
                wallet = wallet,
            ),
        )

        assertContains(decision.warnings, ReceiveAddressPolicyWarning.NostrIdentityLinkageWarningRequired)
        assertContains(decision.warnings, ReceiveAddressPolicyWarning.ImportedKeyBackupWarningRequired)
    }

    @Test
    fun importedSingleKeySourceAddsSeparateBackupWarning() {
        val wallet = operationalWallet(source = ReceiveAddressSource.ImportedSingleKey)

        val decision = ReceiveAddressPolicy.evaluate(
            ReceiveAddressPolicyRequest(
                action = ReceiveAddressPolicyAction.RequestFreshReceiveAddress,
                wallet = wallet,
            ),
        )

        assertContains(decision.warnings, ReceiveAddressPolicyWarning.ImportedKeyBackupWarningRequired)
        assertFalse(decision.warnings.contains(ReceiveAddressPolicyWarning.NostrIdentityLinkageWarningRequired))
    }

    @Test
    fun receiveAddressDisplayValueUsesSentinelAndRedactedRendering() {
        val state = reservedAddress()

        assertEquals("ADDRESS_NOT_DERIVED", state.displayValue.value)
        assertTrue(state.displayValue.isPlaceholder)
        assertFalse(state.displayValue.isRealProductionAddress)
        assertEquals("ADDRESS_NOT_DERIVED", state.displayValue.toString())
    }

    private fun nonOperationalProfile() =
        DescriptorWalletWorkflow.review(
            input = EditableDescriptorWalletProfileInput(
                label = "receive policy metadata",
                intent = DescriptorWalletCreationIntent.NativeDescriptorFromAppSeed,
                network = NetworkEnvironment.Regtest,
                acknowledgements = DescriptorWalletWorkflow.requiredAcknowledgementsFor(
                    DescriptorWalletCreationIntent.NativeDescriptorFromAppSeed,
                ),
            ),
            secureStorageCapability = DisabledSecureSecretStorage().capability,
        ).profile ?: error("Expected metadata profile")

    private fun operationalWallet(
        network: NetworkEnvironment = NetworkEnvironment.Regtest,
        source: ReceiveAddressSource = ReceiveAddressSource.NativeDescriptor,
    ): ReceiveAddressWalletContext =
        ReceiveAddressWalletContext(
            profileId = DescriptorWalletProfileId("receive-policy-operational-${network.name.lowercase()}"),
            profileLabel = "receive policy operational placeholder",
            network = network,
            source = source,
            operationalState = ReceiveAddressWalletOperationalState.OperationalDevelopmentWallet,
            canDeriveReceiveAddresses = true,
        )

    private fun reservedAddress(
        wallet: ReceiveAddressWalletContext = operationalWallet(),
    ): ReceiveAddressState =
        ReceiveAddressState.placeholderReserved(
            wallet = wallet,
            derivationIndex = ReceiveAddressDerivationIndex(0),
        )

}
