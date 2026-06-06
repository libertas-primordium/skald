package com.libertasprimordium.skald

import com.libertasprimordium.skald.domain.core.CredentialReference
import com.libertasprimordium.skald.domain.core.NetworkEnvironment
import com.libertasprimordium.skald.domain.core.SatsAmount
import com.libertasprimordium.skald.domain.onchain.BackendCredentialPolicy
import com.libertasprimordium.skald.domain.onchain.BackendObservationPolicy
import com.libertasprimordium.skald.domain.onchain.BackendObservationPolicyRequest
import com.libertasprimordium.skald.domain.onchain.BackendObservationSession
import com.libertasprimordium.skald.domain.onchain.BackendObservationStatus
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendAdapterBlockingIssue
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendAdapterCapability
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendAdapterPolicy
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendAdapterStatus
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendAdapterWarning
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendObservationRequest
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendTrustModel
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendType
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendValidator
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletProfileId
import com.libertasprimordium.skald.domain.onchain.DisabledProductionBitcoinBackendAdapter
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
import com.libertasprimordium.skald.security.DisabledSecureSecretStorage
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class ProductionBackendAdapterBoundaryTest {
    private val secureStorage = DisabledSecureSecretStorage().capability
    private val adapter = DisabledProductionBitcoinBackendAdapter()

    @Test
    fun disabledAdapterReportsNoBackendConfiguredWithoutNetworking() {
        val result = adapter.observe(
            BitcoinBackendObservationRequest(
                profile = null,
                wallet = null,
                candidate = null,
                secureStorageCapability = secureStorage,
            ),
        )

        assertEquals(BitcoinBackendAdapterStatus.NotConfigured, result.status)
        assertContains(result.blockingIssues, BitcoinBackendAdapterBlockingIssue.BackendNotConfigured)
        assertNull(result.observationSummary)
        assertFalse(result.productionNetworkingEnabled)
        assertFalse(result.productionSyncEnabled)
        assertFalse(result.signingOrBroadcastEnabled)
        assertFalse(result.mainnetEnabled)
        assertFalse(result.usesSkaldManagedInfrastructure)
        assertContains(result.capabilities, BitcoinBackendAdapterCapability.NoProductionNetworking)
    }

    @Test
    fun localRegtestProfileIsRepresentedButProductionSyncStaysDisabled() {
        val profile = profile(
            type = BitcoinBackendType.BitcoinCoreRpc,
            network = NetworkEnvironment.Regtest,
            address = "127.0.0.1",
            port = "18443",
        )
        val wallet = operationalWallet(NetworkEnvironment.Regtest)
        val result = adapter.observe(request(profile, wallet))

        assertEquals(BitcoinBackendAdapterStatus.ProductionSyncDisabled, result.status)
        assertContains(result.blockingIssues, BitcoinBackendAdapterBlockingIssue.ProductionSyncDisabled)
        assertContains(result.warnings, BitcoinBackendAdapterWarning.ProductionSyncDisabled)
        assertContains(result.warnings, BitcoinBackendAdapterWarning.NoNetworkAttempted)
        assertContains(result.warnings, BitcoinBackendAdapterWarning.UserOwnedNodePreferred)
        val summary = assertNotNull(result.observationSummary)
        assertEquals(BackendObservationStatus.NotStarted, summary.status)
        assertEquals(0, summary.observedUtxoCount)
        assertFalse(summary.productionSyncEnabled)
        assertFalse(summary.signingOrBroadcastEnabled)
    }

    @Test
    fun publicBackendTrustProducesAdapterPrivacyWarning() {
        val profile = profile(
            type = BitcoinBackendType.Esplora,
            network = NetworkEnvironment.Signet,
            address = "https://example.invalid/api",
            port = "",
            trustModel = BitcoinBackendTrustModel.PublicBackend,
        )
        val wallet = operationalWallet(NetworkEnvironment.Signet)
        val result = adapter.observe(request(profile, wallet))

        assertContains(result.warnings, BitcoinBackendAdapterWarning.PublicBackendPrivacyLeak)
        assertContains(result.warnings, BitcoinBackendAdapterWarning.BackendCanLinkWalletQueries)
        assertFalse(result.productionNetworkingEnabled)
    }

    @Test
    fun onionBackendTrustLabelIsPreserved() {
        val profile = profile(
            type = BitcoinBackendType.Electrum,
            network = NetworkEnvironment.Signet,
            address = "exampleexampleexample.onion",
            port = "50001",
        )
        val wallet = operationalWallet(NetworkEnvironment.Signet)
        val result = adapter.observe(request(profile, wallet))

        assertContains(result.warnings, BitcoinBackendAdapterWarning.OnionTorLabelPreserved)
        assertEquals(
            com.libertasprimordium.skald.domain.onchain.BackendObservationTrust.UserSelectedOnionBackend,
            BitcoinBackendAdapterPolicy.observationTrustFor(profile),
        )
    }

    @Test
    fun credentialReferenceIsMetadataOnlyAndBlockedByDisabledSecureStorage() {
        val profile = profile(
            type = BitcoinBackendType.BitcoinCoreRpc,
            network = NetworkEnvironment.Regtest,
            address = "127.0.0.1",
            port = "18443",
        ).copy(
            credentialPolicy = BackendCredentialPolicy.CredentialReferenceOnly,
            credentialReference = CredentialReference("secure-storage-reference-placeholder"),
        )
        val wallet = operationalWallet(NetworkEnvironment.Regtest)
        val result = adapter.observe(request(profile, wallet))

        assertEquals(BitcoinBackendAdapterStatus.CredentialsUnavailable, result.status)
        assertContains(result.blockingIssues, BitcoinBackendAdapterBlockingIssue.CredentialsUnavailable)
        assertContains(result.warnings, BitcoinBackendAdapterWarning.CredentialsRequireSecureStorage)
        assertFalse(result.toString().contains("DEMO_VALUE_DO_NOT_USE"))
        assertFalse(result.toString().contains("password", ignoreCase = true))
    }

    @Test
    fun mainnetObservationIsRejectedBeforeSync() {
        val profile = profile(
            type = BitcoinBackendType.Electrum,
            network = NetworkEnvironment.Signet,
            address = "example.invalid",
            port = "50001",
        ).copy(network = NetworkEnvironment.MainnetDisabled)
        val wallet = operationalWallet(NetworkEnvironment.MainnetDisabled)
        val result = adapter.observe(request(profile, wallet))

        assertEquals(BitcoinBackendAdapterStatus.MainnetRejected, result.status)
        assertContains(result.blockingIssues, BitcoinBackendAdapterBlockingIssue.MainnetDisabled)
        assertFalse(result.mainnetEnabled)
        assertFalse(result.productionNetworkingEnabled)
    }

    @Test
    fun sanitizedPlaceholderObservationCanBeRepresentedThroughSkaldOwnedSummary() {
        val profile = profile(
            type = BitcoinBackendType.Electrum,
            network = NetworkEnvironment.Regtest,
            address = "127.0.0.1",
            port = "50001",
        )
        val wallet = operationalWallet(NetworkEnvironment.Regtest)
        val address = ReceiveAddressState.placeholderReserved(wallet).markDisplayed()
        val summary = BackendObservationPolicy.evaluate(
            BackendObservationPolicyRequest(
                session = BackendObservationSession.localRegtestElectrumHarness(),
                wallet = wallet,
                candidate = address,
                observedUtxos = listOf(
                    ObservedUtxo(
                        id = ObservedUtxoId("OBSERVED_UTXO_NOT_REAL"),
                        walletProfileId = wallet.profileId,
                        sourceAddressId = address.id,
                        network = NetworkEnvironment.Regtest,
                        amount = SatsAmount(25_000),
                        outpoint = ObservedOutpoint.NotReal,
                        lifecycle = ObservedUtxoLifecycle.BackendObservedConfirmed,
                        confirmationState = ObservedConfirmationState.confirmed(1),
                        scriptClass = ObservedScriptClass.Bip86P2tr,
                        walletScope = ObservedWalletScope.NativeDescriptor,
                        riskFlags = setOf(ObservedUtxoRiskFlag.CoinControlRequired),
                        spendReadiness = ObservedUtxoSpendReadiness.CoinControlRequired,
                    ),
                ),
            ),
        )

        val result = BitcoinBackendAdapterPolicy.sanitizedPlaceholderObservationResult(profile, summary)

        assertEquals(BitcoinBackendAdapterStatus.PlaceholderObservation, result.status)
        assertEquals(BackendObservationStatus.Completed, assertNotNull(result.observationSummary).status)
        assertEquals(1, result.observationSummary.observedUtxoCount)
        assertFalse(result.observationSummary.anySpendableWithoutCoinControl)
        assertFalse(result.productionNetworkingEnabled)
        assertFalse(result.signingOrBroadcastEnabled)
    }

    private fun request(
        profile: com.libertasprimordium.skald.domain.onchain.BitcoinBackendProfile,
        wallet: ReceiveAddressWalletContext,
    ): BitcoinBackendObservationRequest =
        BitcoinBackendObservationRequest(
            profile = profile,
            wallet = wallet,
            candidate = ReceiveAddressState.placeholderReserved(
                wallet = wallet,
                derivationIndex = ReceiveAddressDerivationIndex(0),
            ).markDisplayed(),
            secureStorageCapability = secureStorage,
        )

    private fun profile(
        type: BitcoinBackendType,
        network: NetworkEnvironment,
        address: String,
        port: String,
        trustModel: BitcoinBackendTrustModel = BitcoinBackendTrustModel.UserOwnedNode,
    ) = assertNotNull(
        BitcoinBackendValidator.validate(
            EditableBitcoinBackendProfileInput(
                label = "Adapter boundary profile",
                type = type,
                network = network,
                host = address,
                portText = port,
                useTls = type == BitcoinBackendType.Esplora,
                path = "",
                trustModel = trustModel,
            ),
        ).normalizedProfile,
    )

    private fun operationalWallet(network: NetworkEnvironment): ReceiveAddressWalletContext =
        ReceiveAddressWalletContext(
            profileId = DescriptorWalletProfileId("adapter-boundary-${network.name.lowercase()}"),
            profileLabel = "Adapter boundary placeholder",
            network = network,
            source = ReceiveAddressSource.NativeDescriptor,
            operationalState = ReceiveAddressWalletOperationalState.OperationalDevelopmentWallet,
            canDeriveReceiveAddresses = true,
        )
}
