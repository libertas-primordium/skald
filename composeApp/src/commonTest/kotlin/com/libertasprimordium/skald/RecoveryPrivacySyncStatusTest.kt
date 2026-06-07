package com.libertasprimordium.skald

import com.libertasprimordium.skald.domain.core.NetworkEnvironment
import com.libertasprimordium.skald.domain.core.SatsAmount
import com.libertasprimordium.skald.domain.onchain.BackendObservationPolicy
import com.libertasprimordium.skald.domain.onchain.BackendObservationPolicyRequest
import com.libertasprimordium.skald.domain.onchain.BackendObservationSession
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendProfile
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendTrustModel
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendType
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendValidator
import com.libertasprimordium.skald.domain.onchain.BitcoinWalletSyncRequest
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletProfileId
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletSettingsState
import com.libertasprimordium.skald.domain.onchain.DisabledBitcoinWalletSyncService
import com.libertasprimordium.skald.domain.onchain.EditableBitcoinBackendProfileInput
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
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressSource
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressState
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressWalletContext
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressWalletOperationalState
import com.libertasprimordium.skald.domain.privacy.PrivacyRiskLevel
import com.libertasprimordium.skald.domain.privacy.PrivacySyncFindingCategory
import com.libertasprimordium.skald.domain.privacy.PrivacySyncStatusAnalyzer
import com.libertasprimordium.skald.domain.recovery.RecoverySyncItemState
import com.libertasprimordium.skald.domain.recovery.RecoverySyncStatusPolicy
import com.libertasprimordium.skald.security.DisabledSecureSecretStorage
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class RecoveryPrivacySyncStatusTest {
    private val service = DisabledBitcoinWalletSyncService()
    private val secureStorage = DisabledSecureSecretStorage().capability

    @Test
    fun recoveryStatusReportsProductionSyncDisabled() {
        val recovery = RecoverySyncStatusPolicy.from(
            syncResult = service.sync(request(profile = null, wallet = null, candidate = null)),
            descriptorWalletSettings = DescriptorWalletSettingsState.Empty,
            secureStorageCapability = secureStorage,
        )

        assertFalse(recovery.productionSyncEnabled)
        assertContains(recovery.items.map { it.label }, "Production wallet sync")
        assertEquals(
            RecoverySyncItemState.Disabled,
            recovery.items.single { it.label == "Production wallet sync" }.state,
        )
    }

    @Test
    fun recoveryStatusReportsObservationPersistenceDeferredAndSecureStorageUnavailable() {
        val wallet = operationalWallet()
        val recovery = RecoverySyncStatusPolicy.from(
            syncResult = service.sync(
                request(profile = profile(NetworkEnvironment.Regtest), wallet = wallet, candidate = displayedAddress(wallet)),
            ),
            descriptorWalletSettings = DescriptorWalletSettingsState.Empty,
            secureStorageCapability = secureStorage,
        )

        assertFalse(recovery.productionObservationPersistenceEnabled)
        assertContains(recovery.items.map { it.label }, "Observation and UTXO persistence")
        assertContains(recovery.items.map { it.label }, "Encrypted vault readiness")
        assertContains(recovery.items.map { it.label }, "Secure metadata vault")
        assertContains(recovery.items.map { it.label }, "UTXO state persistence")
        assertContains(recovery.items.map { it.label }, "Secure storage")
        assertEquals(
            RecoverySyncItemState.DeferredUntilEncryptedVault,
            recovery.items.single { it.label == "Observation and UTXO persistence" }.state,
        )
        assertEquals(RecoverySyncItemState.Unavailable, recovery.items.single { it.label == "Secure storage" }.state)
        assertEquals(
            RecoverySyncItemState.DeferredUntilEncryptedVault,
            recovery.items.single { it.label == "Encrypted vault readiness" }.state,
        )
        assertEquals(
            RecoverySyncItemState.DeferredUntilEncryptedVault,
            recovery.items.single { it.label == "Secure metadata vault" }.state,
        )
        assertTrue(
            recovery.items.single { it.label == "Observation and UTXO persistence" }
                .detail
                .contains("encrypted vault storage"),
        )
    }

    @Test
    fun recoveryStatusDoesNotTreatTestOnlyBdkValidationAsProductionWalletRecovery() {
        val recovery = RecoverySyncStatusPolicy.from(
            syncResult = service.sync(request(profile = null, wallet = null, candidate = null)),
            descriptorWalletSettings = DescriptorWalletSettingsState.Empty,
            secureStorageCapability = secureStorage,
        )

        val validationItem = recovery.items.single { it.label == "Desktop regtest BDK validation" }
        assertEquals(RecoverySyncItemState.TestOnlyNotRecoverable, validationItem.state)
        assertFalse(recovery.testValidationCountsAsProductionRecovery)
        assertTrue(validationItem.detail.contains("does not create recoverable production"))
    }

    @Test
    fun privacyStatusReportsPublicBackendWarning() {
        val wallet = operationalWallet(NetworkEnvironment.Signet)
        val status = PrivacySyncStatusAnalyzer.analyze(
            service.sync(
                request(
                    profile = profile(
                        network = NetworkEnvironment.Signet,
                        type = BitcoinBackendType.Esplora,
                        address = "https://example.invalid/api",
                        port = "",
                        trustModel = BitcoinBackendTrustModel.PublicBackend,
                        useTls = true,
                    ),
                    wallet = wallet,
                    candidate = displayedAddress(wallet),
                ),
            ),
        )

        assertContains(status.findings.map { it.title }, "Public backend privacy risk")
        assertContains(status.findings.map { it.title }, "Encrypted vault readiness unavailable")
        assertContains(status.findings.map { it.title }, "Secure metadata storage unavailable")
        assertTrue(status.findings.single { it.title == "Public backend privacy risk" }.level == PrivacyRiskLevel.Danger)
        assertFalse(status.canRunAnalysis)
    }

    @Test
    fun privacyStatusReportsOnionTorLabelWithoutTransportClaim() {
        val wallet = operationalWallet(NetworkEnvironment.Signet)
        val status = PrivacySyncStatusAnalyzer.analyze(
            service.sync(
                request(
                    profile = profile(
                        network = NetworkEnvironment.Signet,
                        type = BitcoinBackendType.Electrum,
                        address = "exampleexampleexample.onion",
                        port = "50001",
                    ),
                    wallet = wallet,
                    candidate = displayedAddress(wallet),
                ),
            ),
        )

        assertContains(status.findings.map { it.title }, "Onion endpoint labeled")
        assertContains(status.findings.map { it.title }, "Tor transport not implemented")
        assertFalse(status.torTransportImplemented)
        assertFalse(status.findings.any { it.detail.contains("Tor transport is available") })
    }

    @Test
    fun privacyStatusDistinguishesDisplayedAddressFromObservedUsedAddress() {
        val wallet = operationalWallet()
        val displayed = displayedAddress(wallet)
        val baseStatus = PrivacySyncStatusAnalyzer.analyze(
            service.sync(request(profile = profile(NetworkEnvironment.Regtest), wallet = wallet, candidate = displayed)),
        )

        val observedSummary = observedSummary(wallet, displayed)
        val observedStatus = PrivacySyncStatusAnalyzer.analyze(
            syncResult = service.sync(request(profile = profile(NetworkEnvironment.Regtest), wallet = wallet, candidate = displayed)),
            observationSummary = observedSummary,
        )

        assertContains(baseStatus.findings.map { it.title }, "Displayed address is not used")
        assertFalse(baseStatus.findings.any { it.title == "Backend-observed address marked used" })
        assertNotNull(observedSummary.addressUsage)
        assertContains(observedStatus.findings.map { it.title }, "Backend-observed address marked used")
        assertContains(observedStatus.findings.map { it.title }, "Address reuse requires confirmation")
    }

    @Test
    fun privacyStatusReportsIdentityLinkedPlaceholderWithoutParsingNostrKeys() {
        val wallet = operationalWallet(source = ReceiveAddressSource.NostrNsecImportedSpend)
        val displayed = displayedAddress(wallet)
        val status = PrivacySyncStatusAnalyzer.analyze(
            syncResult = service.sync(request(profile = profile(NetworkEnvironment.Regtest), wallet = wallet, candidate = displayed)),
            observationSummary = observedSummary(wallet, displayed),
        )

        assertContains(status.findings.map { it.title }, "Identity-linked UTXO placeholder")
        assertContains(status.findings.map { it.category }, PrivacySyncFindingCategory.IdentityLinkage)
        assertFalse(status.toString().contains("nsec1"))
    }

    private fun request(
        profile: BitcoinBackendProfile?,
        wallet: ReceiveAddressWalletContext?,
        candidate: ReceiveAddressState?,
    ): BitcoinWalletSyncRequest =
        BitcoinWalletSyncRequest(
            backendProfile = profile,
            backendValidation = profile?.let {
                BitcoinBackendValidator.validate(
                    EditableBitcoinBackendProfileInput(
                        id = it.id,
                        label = it.label,
                        type = it.type,
                        network = it.network,
                        host = when (val endpoint = it.endpoint) {
                            com.libertasprimordium.skald.domain.onchain.BackendNotConfigured -> ""
                            is com.libertasprimordium.skald.domain.onchain.HttpEndpoint -> endpoint.host
                            is com.libertasprimordium.skald.domain.onchain.TcpEndpoint -> endpoint.host
                        },
                        portText = when (val endpoint = it.endpoint) {
                            com.libertasprimordium.skald.domain.onchain.BackendNotConfigured -> ""
                            is com.libertasprimordium.skald.domain.onchain.HttpEndpoint -> endpoint.port?.toString().orEmpty()
                            is com.libertasprimordium.skald.domain.onchain.TcpEndpoint -> endpoint.port.toString()
                        },
                        useTls = when (val endpoint = it.endpoint) {
                            com.libertasprimordium.skald.domain.onchain.BackendNotConfigured -> false
                            is com.libertasprimordium.skald.domain.onchain.HttpEndpoint -> endpoint.useTls
                            is com.libertasprimordium.skald.domain.onchain.TcpEndpoint -> endpoint.useTls
                        },
                        path = when (val endpoint = it.endpoint) {
                            com.libertasprimordium.skald.domain.onchain.BackendNotConfigured -> ""
                            is com.libertasprimordium.skald.domain.onchain.HttpEndpoint -> endpoint.path.orEmpty()
                            is com.libertasprimordium.skald.domain.onchain.TcpEndpoint -> ""
                        },
                        trustModel = it.trustModel,
                    ),
                )
            },
            wallet = wallet,
            candidate = candidate,
            secureStorageCapability = secureStorage,
        )

    private fun profile(
        network: NetworkEnvironment,
        type: BitcoinBackendType = BitcoinBackendType.BitcoinCoreRpc,
        address: String = "127.0.0.1",
        port: String = "18443",
        trustModel: BitcoinBackendTrustModel = BitcoinBackendTrustModel.UserOwnedNode,
        useTls: Boolean = false,
    ): BitcoinBackendProfile {
        val validation = BitcoinBackendValidator.validate(
            EditableBitcoinBackendProfileInput(
                label = "Recovery privacy sync profile",
                type = type,
                network = network,
                host = address,
                portText = port,
                useTls = useTls,
                path = "",
                trustModel = trustModel,
            ),
        )
        assertTrue(validation.errors.isEmpty(), validation.errors.joinToString { it.message })
        return assertNotNull(validation.normalizedProfile)
    }

    private fun operationalWallet(
        network: NetworkEnvironment = NetworkEnvironment.Regtest,
        source: ReceiveAddressSource = ReceiveAddressSource.NativeDescriptor,
    ): ReceiveAddressWalletContext =
        ReceiveAddressWalletContext(
            profileId = DescriptorWalletProfileId("recovery-privacy-${network.name.lowercase()}-${source.name.lowercase()}"),
            profileLabel = "Recovery privacy placeholder",
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

    private fun observedSummary(
        wallet: ReceiveAddressWalletContext,
        displayed: ReceiveAddressState,
    ) = BackendObservationPolicy.evaluate(
        BackendObservationPolicyRequest(
            session = BackendObservationSession.localRegtestElectrumHarness(),
            wallet = wallet,
            candidate = displayed,
            observedUtxos = listOf(
                ObservedUtxo(
                    id = ObservedUtxoId("OBSERVED_UTXO_NOT_REAL"),
                    walletProfileId = wallet.profileId,
                    sourceAddressId = displayed.id,
                    network = wallet.network,
                    amount = SatsAmount(25_000),
                    outpoint = ObservedOutpoint.NotReal,
                    lifecycle = ObservedUtxoLifecycle.BackendObservedConfirmed,
                    confirmationState = ObservedConfirmationState.confirmed(1),
                    scriptClass = ObservedScriptClass.Bip86P2tr,
                    walletScope = if (wallet.source.requiresIdentityLinkageWarning) {
                        ObservedWalletScope.NostrIdentityLinked
                    } else {
                        ObservedWalletScope.NativeDescriptor
                    },
                    riskFlags = buildSet {
                        add(ObservedUtxoRiskFlag.CoinControlRequired)
                        if (wallet.source.requiresIdentityLinkageWarning) {
                            add(ObservedUtxoRiskFlag.IdentityLinkedSource)
                        }
                    },
                    spendReadiness = ObservedUtxoSpendReadiness.CoinControlRequired,
                ),
            ),
        ),
    )
}
