package com.libertasprimordium.skald

import com.libertasprimordium.skald.domain.core.CredentialReference
import com.libertasprimordium.skald.domain.core.NetworkEnvironment
import com.libertasprimordium.skald.domain.onchain.BackendCredentialPolicy
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendProfile
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendTrustModel
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendType
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendValidator
import com.libertasprimordium.skald.domain.onchain.BitcoinWalletSyncRequest
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletProfileId
import com.libertasprimordium.skald.domain.onchain.DisabledBitcoinWalletSyncService
import com.libertasprimordium.skald.domain.onchain.EditableBitcoinBackendProfileInput
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressDerivationIndex
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressSource
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressState
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressWalletContext
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressWalletOperationalState
import com.libertasprimordium.skald.security.DisabledSecureSecretStorage
import com.libertasprimordium.skald.ui.components.BitcoinWalletSyncStatusUiMapper
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class BitcoinWalletSyncStatusUiModelTest {
    private val service = DisabledBitcoinWalletSyncService()
    private val secureStorage = DisabledSecureSecretStorage().capability

    @Test
    fun disabledSyncStatusModelProducesExpectedBlockerLabels() {
        val model = BitcoinWalletSyncStatusUiMapper.from(
            service.sync(request(profile = null, wallet = null, candidate = null)),
        )

        assertContains(model.blockers, "Production sync is not implemented.")
        assertContains(model.blockers, "No backend profile is selected.")
        assertContains(model.blockers, "Production backend adapter is disabled.")
        assertContains(model.blockers, "Observation persistence is unavailable.")
        assertContains(model.warnings, "No network connection is attempted.")
        assertContains(model.lockedActionLabel, "SYNC_NOT_IMPLEMENTED")
        assertFalse(model.canRunSync)
        assertFalse(model.networkAttempted)
    }

    @Test
    fun publicBackendProfileSurfacesPrivacyWarning() {
        val wallet = operationalWallet(NetworkEnvironment.Signet)
        val model = BitcoinWalletSyncStatusUiMapper.from(
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

        assertContains(model.warnings, "Public backends can observe wallet queries.")
        assertContains(model.warnings, "The selected backend can link wallet queries.")
        assertFalse(model.networkAttempted)
    }

    @Test
    fun onionBackendProfileSurfacesTorLabelWithoutTransportClaim() {
        val wallet = operationalWallet(NetworkEnvironment.Signet)
        val model = BitcoinWalletSyncStatusUiMapper.from(
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

        assertContains(model.warnings, "Onion/Tor endpoint labeling is preserved.")
        assertContains(model.warnings, "Tor transport is not implemented.")
        assertFalse(model.warnings.any { it.contains("Tor transport implemented") })
    }

    @Test
    fun invalidEndpointSurfacesEndpointAndCredentialBlockers() {
        val wallet = operationalWallet(NetworkEnvironment.Signet)
        val validation = BitcoinBackendValidator.validate(
            input(
                network = NetworkEnvironment.Signet,
                type = BitcoinBackendType.Esplora,
                address = "https://REDACTED_USERINFO@example.invalid/api",
                port = "",
                useTls = true,
            ),
        )
        val model = BitcoinWalletSyncStatusUiMapper.from(
            service.sync(
                request(
                    profile = validation.normalizedProfile,
                    validation = validation,
                    wallet = wallet,
                    candidate = displayedAddress(wallet),
                ),
            ),
        )

        assertContains(model.blockers, "Selected endpoint metadata is invalid.")
        assertContains(model.blockers, "Credential or userinfo material was rejected.")
        assertFalse(model.toString().contains("REDACTED_USERINFO"))
    }

    @Test
    fun noBackendSelectedSurfacesBackendNotConfiguredBlocker() {
        val wallet = operationalWallet(NetworkEnvironment.Regtest)
        val model = BitcoinWalletSyncStatusUiMapper.from(
            service.sync(request(profile = null, wallet = wallet, candidate = displayedAddress(wallet))),
        )

        assertContains(model.blockers, "No backend profile is selected.")
        assertContains(model.blockers, "Production sync is not implemented.")
        assertFalse(model.canRunSync)
    }

    @Test
    fun nonOperationalWalletMetadataSurfacesNoOperationalWalletBlocker() {
        val wallet = ReceiveAddressWalletContext(
            profileId = DescriptorWalletProfileId("sync-status-non-operational-wallet"),
            profileLabel = "Sync status non-operational placeholder",
            network = NetworkEnvironment.Regtest,
            source = ReceiveAddressSource.NativeDescriptor,
            operationalState = ReceiveAddressWalletOperationalState.NonOperationalMetadata,
            canDeriveReceiveAddresses = false,
        )
        val model = BitcoinWalletSyncStatusUiMapper.from(
            service.sync(
                request(
                    profile = profile(NetworkEnvironment.Regtest),
                    wallet = wallet,
                    candidate = displayedAddress(wallet),
                ),
            ),
        )

        assertContains(model.blockers, "No operational descriptor wallet is available.")
        assertFalse(model.canRunSync)
    }

    @Test
    fun secureStorageUnavailableAndCredentialReferenceAreVisibleAsBlockers() {
        val wallet = operationalWallet(NetworkEnvironment.Regtest)
        val profile = profile(NetworkEnvironment.Regtest).copy(
            credentialPolicy = BackendCredentialPolicy.CredentialReferenceOnly,
            credentialReference = CredentialReference("secure-storage-reference-placeholder"),
        )
        val model = BitcoinWalletSyncStatusUiMapper.from(
            service.sync(request(profile = profile, wallet = wallet, candidate = displayedAddress(wallet))),
        )

        assertContains(model.blockers, "Secure storage is unavailable.")
        assertContains(model.blockers, "Backend credentials are unavailable.")
        assertContains(model.warnings, "Credential references require secure storage.")
        assertFalse(model.toString().contains("secure-storage-reference-placeholder"))
    }

    @Test
    fun mainnetCannotBeSurfacedAsValidSyncTarget() {
        val wallet = operationalWallet(NetworkEnvironment.MainnetDisabled)
        val model = BitcoinWalletSyncStatusUiMapper.from(
            service.sync(
                request(
                    profile = profile(NetworkEnvironment.Signet).copy(network = NetworkEnvironment.MainnetDisabled),
                    wallet = wallet,
                    candidate = displayedAddress(wallet),
                ),
            ),
        )

        assertContains(model.blockers, "Mainnet is disabled.")
        assertFalse(model.canRunSync)
        assertFalse(model.networkAttempted)
    }

    @Test
    fun statusModelNeverExposesWorkingSyncAction() {
        val wallet = operationalWallet(NetworkEnvironment.Regtest)
        val model = BitcoinWalletSyncStatusUiMapper.from(
            service.sync(request(profile = profile(NetworkEnvironment.Regtest), wallet = wallet, candidate = displayedAddress(wallet))),
        )

        assertFalse(model.canRunSync)
        assertFalse(model.networkAttempted)
        assertContains(model.lockedActionLabel, "production wallet sync")
        assertContains(model.lockedActionLabel, "remain disabled")
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
                network = network,
                type = type,
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
        network: NetworkEnvironment,
        type: BitcoinBackendType,
        address: String,
        port: String,
        trustModel: BitcoinBackendTrustModel = BitcoinBackendTrustModel.UserOwnedNode,
        useTls: Boolean = false,
    ): EditableBitcoinBackendProfileInput =
        EditableBitcoinBackendProfileInput(
            label = "Sync status profile",
            type = type,
            network = network,
            host = address,
            portText = port,
            useTls = useTls,
            path = "",
            trustModel = trustModel,
        )

    private fun operationalWallet(network: NetworkEnvironment): ReceiveAddressWalletContext =
        ReceiveAddressWalletContext(
            profileId = DescriptorWalletProfileId("sync-status-${network.name.lowercase()}"),
            profileLabel = "Sync status operational placeholder",
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
