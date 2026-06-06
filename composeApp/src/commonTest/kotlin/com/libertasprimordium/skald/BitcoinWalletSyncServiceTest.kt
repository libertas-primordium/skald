package com.libertasprimordium.skald

import com.libertasprimordium.skald.domain.core.CredentialReference
import com.libertasprimordium.skald.domain.core.NetworkEnvironment
import com.libertasprimordium.skald.domain.onchain.BackendCredentialPolicy
import com.libertasprimordium.skald.domain.onchain.BackendObservationStatus
import com.libertasprimordium.skald.domain.onchain.BackendProfileValidationError
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendProfile
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendTrustModel
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendType
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendValidator
import com.libertasprimordium.skald.domain.onchain.BitcoinWalletSyncBlocker
import com.libertasprimordium.skald.domain.onchain.BitcoinWalletSyncCapability
import com.libertasprimordium.skald.domain.onchain.BitcoinWalletSyncRequest
import com.libertasprimordium.skald.domain.onchain.BitcoinWalletSyncStatus
import com.libertasprimordium.skald.domain.onchain.BitcoinWalletSyncWarning
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletProfileId
import com.libertasprimordium.skald.domain.onchain.DisabledBitcoinWalletSyncService
import com.libertasprimordium.skald.domain.onchain.EditableBitcoinBackendProfileInput
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

class BitcoinWalletSyncServiceTest {
    private val service = DisabledBitcoinWalletSyncService()
    private val secureStorage = DisabledSecureSecretStorage().capability

    @Test
    fun syncFacadeReturnsDisabledFailClosedResultByDefault() {
        val result = service.sync(
            request(
                profile = null,
                wallet = null,
                candidate = null,
            ),
        )

        assertEquals(BitcoinWalletSyncStatus.NotConfigured, result.status)
        assertContains(result.blockers, BitcoinWalletSyncBlocker.BackendNotConfigured)
        assertContains(result.blockers, BitcoinWalletSyncBlocker.SyncDisabled)
        assertContains(result.blockers, BitcoinWalletSyncBlocker.ProductionBackendDisabled)
        assertContains(result.blockers, BitcoinWalletSyncBlocker.ObservationPersistenceUnavailable)
        assertContains(result.warnings, BitcoinWalletSyncWarning.NoNetworkAttempted)
        assertNull(result.backendObservationResult)
        assertNull(result.observationSummary)
        assertFalse(result.productionNetworkingEnabled)
        assertFalse(result.productionSyncEnabled)
        assertFalse(result.observationPersistenceEnabled)
        assertFalse(result.signingOrBroadcastEnabled)
        assertFalse(result.mainnetEnabled)
        assertFalse(result.usesSkaldManagedInfrastructure)
    }

    @Test
    fun invalidEndpointBlocksSyncBeforeAdapterInvocation() {
        val validation = BitcoinBackendValidator.validate(
            input(
                type = BitcoinBackendType.Esplora,
                address = "https://DEMO_VALUE_DO_NOT_USE@example.invalid/api",
                port = "",
                useTls = true,
            ),
        )

        val result = service.sync(
            request(
                profile = validation.normalizedProfile,
                validation = validation,
                wallet = operationalWallet(),
                candidate = displayedAddress(operationalWallet()),
            ),
        )

        assertEquals(BitcoinWalletSyncStatus.EndpointInvalid, result.status)
        assertContains(result.blockers, BitcoinWalletSyncBlocker.EndpointInvalid)
        assertContains(result.blockers, BitcoinWalletSyncBlocker.CredentialMaterialRejected)
        assertContains(result.blockers, BitcoinWalletSyncBlocker.CredentialsUnavailable)
        assertContains(result.preflight.endpointValidationErrors, BackendProfileValidationError.CredentialMaterialRejected)
        assertNull(result.backendObservationResult)
        assertFalse(result.toString().contains("DEMO_VALUE_DO_NOT_USE"))
    }

    @Test
    fun mainnetRequestsAreRejectedBeforeProductionSync() {
        val profile = profile(NetworkEnvironment.Signet).copy(network = NetworkEnvironment.MainnetDisabled)
        val wallet = operationalWallet(NetworkEnvironment.MainnetDisabled)
        val result = service.sync(request(profile = profile, wallet = wallet, candidate = displayedAddress(wallet)))

        assertEquals(BitcoinWalletSyncStatus.MainnetRejected, result.status)
        assertContains(result.blockers, BitcoinWalletSyncBlocker.MainnetDisabled)
        assertFalse(result.mainnetEnabled)
        assertFalse(result.productionNetworkingEnabled)
    }

    @Test
    fun nonOperationalWalletBlocksSyncPreflight() {
        val profile = profile(
            network = NetworkEnvironment.Regtest,
            type = BitcoinBackendType.Electrum,
            port = "50001",
        )
        val wallet = ReceiveAddressWalletContext(
            profileId = DescriptorWalletProfileId("sync-non-operational-wallet"),
            profileLabel = "Sync non-operational placeholder",
            network = NetworkEnvironment.Regtest,
            source = ReceiveAddressSource.NativeDescriptor,
            operationalState = ReceiveAddressWalletOperationalState.NonOperationalMetadata,
            canDeriveReceiveAddresses = false,
        )
        val result = service.sync(request(profile = profile, wallet = wallet, candidate = displayedAddress(wallet)))

        assertEquals(BitcoinWalletSyncStatus.NoOperationalWallet, result.status)
        assertContains(result.blockers, BitcoinWalletSyncBlocker.NoOperationalWallet)
        assertFalse(result.productionSyncEnabled)
    }

    @Test
    fun secureStorageUnavailableIsSurfacedForFutureSecretBearingSync() {
        val wallet = operationalWallet()
        val result = service.preflight(
            request(
                profile = profile(NetworkEnvironment.Regtest),
                wallet = wallet,
                candidate = displayedAddress(wallet),
            ),
        )

        assertContains(result.blockers, BitcoinWalletSyncBlocker.SecureStorageUnavailable)
        assertFalse(result.canAttemptProductionSync)
    }

    @Test
    fun publicBackendProducesPrivacyWarningWithoutNetworkAttempt() {
        val wallet = operationalWallet(NetworkEnvironment.Signet)
        val result = service.sync(
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
        )

        assertEquals(BitcoinWalletSyncStatus.ProductionSyncDisabled, result.status)
        assertContains(result.warnings, BitcoinWalletSyncWarning.PublicBackendPrivacyLeak)
        assertContains(result.warnings, BitcoinWalletSyncWarning.BackendCanLinkWalletQueries)
        assertContains(result.warnings, BitcoinWalletSyncWarning.NoNetworkAttempted)
        assertFalse(result.productionNetworkingEnabled)
    }

    @Test
    fun onionEndpointPreservesTorLabelWithoutClaimingTransportIsImplemented() {
        val wallet = operationalWallet(NetworkEnvironment.Signet)
        val result = service.preflight(
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
        )

        assertContains(result.warnings, BitcoinWalletSyncWarning.OnionTorLabelPreserved)
        assertContains(result.warnings, BitcoinWalletSyncWarning.TorTransportNotImplemented)
        assertFalse(result.productionNetworkingEnabled)
    }

    @Test
    fun credentialReferenceWithoutSecureStorageRemainsBlocked() {
        val wallet = operationalWallet()
        val profile = profile(NetworkEnvironment.Regtest).copy(
            credentialPolicy = BackendCredentialPolicy.CredentialReferenceOnly,
            credentialReference = CredentialReference("secure-storage-reference-placeholder"),
        )
        val result = service.sync(request(profile = profile, wallet = wallet, candidate = displayedAddress(wallet)))

        assertEquals(BitcoinWalletSyncStatus.CredentialsUnavailable, result.status)
        assertContains(result.blockers, BitcoinWalletSyncBlocker.CredentialsUnavailable)
        assertContains(result.warnings, BitcoinWalletSyncWarning.CredentialsRequireSecureStorage)
        assertFalse(result.toString().contains("secure-storage-reference-placeholder-secret"))
    }

    @Test
    fun disabledBackendAdapterTargetsObservationSummaryWithoutPersistingObservations() {
        val wallet = operationalWallet()
        val result = service.sync(
            request(
                profile = profile(NetworkEnvironment.Regtest),
                wallet = wallet,
                candidate = displayedAddress(wallet),
            ),
        )

        assertContains(result.blockers, BitcoinWalletSyncBlocker.ProductionBackendDisabled)
        assertContains(result.blockers, BitcoinWalletSyncBlocker.ObservationPersistenceUnavailable)
        val summary = assertNotNull(result.observationSummary)
        assertEquals(BackendObservationStatus.NotStarted, summary.status)
        assertEquals(0, summary.observedUtxoCount)
        assertFalse(summary.productionSyncEnabled)
        assertFalse(summary.anySpendableWithoutCoinControl)
        assertContains(result.capabilities, BitcoinWalletSyncCapability.BackendObservationSummaryTarget)
    }

    @Test
    fun serviceResultDoesNotEnableNetworkingPersistenceSigningBroadcastingOrMainnet() {
        val wallet = operationalWallet()
        val result = service.sync(
            request(
                profile = profile(NetworkEnvironment.Regtest),
                wallet = wallet,
                candidate = displayedAddress(wallet),
            ),
        )

        assertTrue(BitcoinWalletSyncCapability.FutureProductionSync in result.capabilities)
        assertFalse(result.capabilities.any { it.enabledInProduction && it.name.startsWith("Future") })
        assertFalse(result.productionNetworkingEnabled)
        assertFalse(result.productionSyncEnabled)
        assertFalse(result.observationPersistenceEnabled)
        assertFalse(result.signingOrBroadcastEnabled)
        assertFalse(result.mainnetEnabled)
        assertFalse(result.usesSkaldManagedInfrastructure)
    }

    private fun request(
        profile: BitcoinBackendProfile?,
        validation: com.libertasprimordium.skald.domain.onchain.BackendProfileValidationResult? = null,
        wallet: ReceiveAddressWalletContext?,
        candidate: ReceiveAddressState?,
    ): BitcoinWalletSyncRequest =
        BitcoinWalletSyncRequest(
            backendProfile = profile,
            backendValidation = validation,
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
            input(
                type = type,
                network = network,
                address = address,
                port = port,
                trustModel = trustModel,
                useTls = useTls,
            ),
        )
        assertTrue(validation.errors.isEmpty(), validation.errors.joinToString { it.message })
        return assertNotNull(validation.normalizedProfile)
    }

    private fun input(
        type: BitcoinBackendType = BitcoinBackendType.BitcoinCoreRpc,
        network: NetworkEnvironment = NetworkEnvironment.Regtest,
        address: String,
        port: String,
        trustModel: BitcoinBackendTrustModel = BitcoinBackendTrustModel.UserOwnedNode,
        useTls: Boolean = false,
    ): EditableBitcoinBackendProfileInput =
        EditableBitcoinBackendProfileInput(
            label = "Sync facade profile",
            type = type,
            network = network,
            host = address,
            portText = port,
            useTls = useTls,
            path = "",
            trustModel = trustModel,
        )

    private fun operationalWallet(network: NetworkEnvironment = NetworkEnvironment.Regtest): ReceiveAddressWalletContext =
        ReceiveAddressWalletContext(
            profileId = DescriptorWalletProfileId("sync-facade-${network.name.lowercase()}"),
            profileLabel = "Sync facade operational placeholder",
            network = network,
            source = ReceiveAddressSource.NativeDescriptor,
            operationalState = ReceiveAddressWalletOperationalState.OperationalDevelopmentWallet,
            canDeriveReceiveAddresses = true,
        )

    private fun displayedAddress(wallet: ReceiveAddressWalletContext): ReceiveAddressState =
        ReceiveAddressState.placeholderReserved(
            wallet = wallet,
            derivationIndex = ReceiveAddressDerivationIndex(0),
        ).markDisplayed()
}
