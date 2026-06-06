package com.libertasprimordium.skald

import com.libertasprimordium.skald.domain.core.NetworkEnvironment
import com.libertasprimordium.skald.domain.onchain.BackendProfileValidationError
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendProfile
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendTrustModel
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendType
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendValidator
import com.libertasprimordium.skald.domain.onchain.EditableBitcoinBackendProfileInput
import com.libertasprimordium.skald.domain.onchain.PlaceholderBitcoinBackendConnectionTester
import com.libertasprimordium.skald.domain.onchain.WalletOperationResult
import com.libertasprimordium.skald.settings.BitcoinBackendSettingsCodec
import com.libertasprimordium.skald.settings.InMemorySettingsStorage
import com.libertasprimordium.skald.settings.PersistentSettingsRepository
import com.libertasprimordium.skald.settings.SettingsWriteResult
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class BackendSettingsTest {
    @Test
    fun savesBitcoinCoreBackendProfileAndReadsItBackThroughRepository() {
        val storage = InMemorySettingsStorage()
        val profile = validatedProfile(
            validInput(
                label = "Local regtest Core",
                type = BitcoinBackendType.BitcoinCoreRpc,
                network = NetworkEnvironment.Regtest,
                host = "127.0.0.1",
                portText = "18443",
            ),
        )

        assertIs<SettingsWriteResult.Saved>(PersistentSettingsRepository(storage).saveBitcoinBackendProfile(profile))
        val reloaded = PersistentSettingsRepository(storage).loadBitcoinBackendSettings()

        assertEquals(1, reloaded.profiles.size)
        assertEquals(BitcoinBackendType.BitcoinCoreRpc, reloaded.profiles.single().type)
        assertEquals("tcp://127.0.0.1:18443", reloaded.profiles.single().endpointDisplay)
        assertNull(reloaded.profiles.single().credentialReference)
    }

    @Test
    fun savesElectrumBackendProfileAndReadsItBackThroughRepository() {
        val storage = InMemorySettingsStorage()
        val profile = validatedProfile(
            validInput(
                label = "Electrum signet profile",
                type = BitcoinBackendType.Electrum,
                network = NetworkEnvironment.Signet,
                host = "example.invalid",
                portText = "50002",
                useTls = true,
                trustModel = BitcoinBackendTrustModel.TrustedThirdParty,
            ),
        )

        assertIs<SettingsWriteResult.Saved>(PersistentSettingsRepository(storage).saveBitcoinBackendProfile(profile))
        val reloaded = PersistentSettingsRepository(storage).loadBitcoinBackendSettings()

        assertEquals(BitcoinBackendType.Electrum, reloaded.profiles.single().type)
        assertEquals("tls://example.invalid:50002", reloaded.profiles.single().endpointDisplay)
        assertEquals(BitcoinBackendTrustModel.TrustedThirdParty, reloaded.profiles.single().trustModel)
    }

    @Test
    fun savesElectrumBackendProfileFromUnifiedHostPortAddressField() {
        val storage = InMemorySettingsStorage()
        val profile = validatedProfile(
            validInput(
                label = "Unified Electrum profile",
                type = BitcoinBackendType.Electrum,
                network = NetworkEnvironment.Regtest,
                host = "127.0.0.1:50001",
                portText = "",
            ),
        )

        assertIs<SettingsWriteResult.Saved>(PersistentSettingsRepository(storage).saveBitcoinBackendProfile(profile))
        val reloaded = PersistentSettingsRepository(storage).loadBitcoinBackendSettings()

        assertEquals("tcp://127.0.0.1:50001", reloaded.profiles.single().endpointDisplay)
        assertNull(reloaded.profiles.single().credentialReference)
        assertFalse(assertNotNull(storage.readText()).contains("127.0.0.1:50001"))
    }

    @Test
    fun savesBracketedIpv6ElectrumProfileAndPreservesNormalizedDisplay() {
        val storage = InMemorySettingsStorage()
        val profile = validatedProfile(
            validInput(
                label = "IPv6 regtest Electrum profile",
                type = BitcoinBackendType.Electrum,
                network = NetworkEnvironment.Regtest,
                host = "[::1]:50001",
                portText = "",
                useTls = false,
                trustModel = BitcoinBackendTrustModel.UserOwnedNode,
            ),
        )

        assertIs<SettingsWriteResult.Saved>(PersistentSettingsRepository(storage).saveBitcoinBackendProfile(profile))
        val reloaded = PersistentSettingsRepository(storage).loadBitcoinBackendSettings()

        assertEquals(BitcoinBackendType.Electrum, reloaded.profiles.single().type)
        assertEquals("tcp://[::1]:50001", reloaded.profiles.single().endpointDisplay)
        assertNull(reloaded.profiles.single().credentialReference)
    }

    @Test
    fun savesOnionElectrumProfileWithoutCredentialMaterial() {
        val storage = InMemorySettingsStorage()
        val profile = validatedProfile(
            validInput(
                label = "Onion Electrum profile",
                type = BitcoinBackendType.Electrum,
                network = NetworkEnvironment.Signet,
                host = "exampleexampleexample.onion",
                portText = "50001",
                useTls = false,
                trustModel = BitcoinBackendTrustModel.UserOwnedNode,
            ),
        )

        assertIs<SettingsWriteResult.Saved>(PersistentSettingsRepository(storage).saveBitcoinBackendProfile(profile))
        val reloaded = PersistentSettingsRepository(storage).loadBitcoinBackendSettings()

        assertEquals("tcp://exampleexampleexample.onion:50001", reloaded.profiles.single().endpointDisplay)
        assertTrue(reloaded.profiles.single().warnings.any { it.contains("Onion", ignoreCase = true) })
        assertNull(reloaded.profiles.single().credentialReference)
    }

    @Test
    fun savesEsploraBackendProfileAndReadsItBackThroughRepository() {
        val storage = InMemorySettingsStorage()
        val profile = validatedProfile(
            validInput(
                label = "Esplora testnet4 profile",
                type = BitcoinBackendType.Esplora,
                network = NetworkEnvironment.Testnet4,
                host = "https://example.invalid/api",
                portText = "",
                useTls = false,
                trustModel = BitcoinBackendTrustModel.PublicBackend,
            ),
        )

        assertIs<SettingsWriteResult.Saved>(PersistentSettingsRepository(storage).saveBitcoinBackendProfile(profile))
        val reloaded = PersistentSettingsRepository(storage).loadBitcoinBackendSettings()

        assertEquals(BitcoinBackendType.Esplora, reloaded.profiles.single().type)
        assertEquals("https://example.invalid/api", reloaded.profiles.single().endpointDisplay)
        assertEquals(BitcoinBackendTrustModel.PublicBackend, reloaded.profiles.single().trustModel)
    }

    @Test
    fun decodesExistingSeparateHostPortProfileStorageAfterUnifiedAddressUiPass() {
        val decoded = BitcoinBackendSettingsCodec.decode(
            """
            skald.backend-settings.v1
            selected|-
            profile|legacy-core|Legacy Core|BitcoinCoreRpc|Regtest|tcp|127.0.0.1|18443|false||UserOwnedNode
            """.trimIndent(),
        )

        val profile = decoded.profiles.single()
        assertEquals("Legacy Core", profile.label)
        assertEquals(BitcoinBackendType.BitcoinCoreRpc, profile.type)
        assertEquals("tcp://127.0.0.1:18443", profile.endpointDisplay)
        assertNull(profile.credentialReference)
    }

    @Test
    fun selectingOneProfilePersistsSelectionState() {
        val storage = InMemorySettingsStorage()
        val repository = PersistentSettingsRepository(storage)
        val first = validatedProfile(validInput(label = "Core first"))
        val second = validatedProfile(validInput(label = "Core second", host = "localhost", portText = "18444"))

        assertIs<SettingsWriteResult.Saved>(repository.saveBitcoinBackendProfile(first))
        assertIs<SettingsWriteResult.Saved>(repository.saveBitcoinBackendProfile(second))
        assertIs<SettingsWriteResult.Saved>(repository.selectBitcoinBackendProfile(first.id))
        val reloaded = PersistentSettingsRepository(storage).loadBitcoinBackendSettings()

        assertEquals(first.id, reloaded.selectedProfileId)
        assertTrue(reloaded.profiles.single { it.id == first.id }.isSelected)
        assertFalse(reloaded.profiles.single { it.id == second.id }.isSelected)
    }

    @Test
    fun deletingSelectedProfileClearsSelection() {
        val storage = InMemorySettingsStorage()
        val repository = PersistentSettingsRepository(storage)
        val profile = validatedProfile(validInput(label = "Delete selected Core"))

        assertIs<SettingsWriteResult.Saved>(repository.saveBitcoinBackendProfile(profile))
        assertIs<SettingsWriteResult.Saved>(repository.selectBitcoinBackendProfile(profile.id))
        assertIs<SettingsWriteResult.Saved>(repository.deleteBitcoinBackendProfile(profile.id))
        val reloaded = PersistentSettingsRepository(storage).loadBitcoinBackendSettings()

        assertTrue(reloaded.profiles.isEmpty())
        assertNull(reloaded.selectedProfileId)
    }

    @Test
    fun blankLabelValidationFails() {
        val result = BitcoinBackendValidator.validate(validInput(label = " "))

        assertContains(result.errors, BackendProfileValidationError.BlankLabel)
        assertNull(result.normalizedProfile)
    }

    @Test
    fun missingHostValidationFails() {
        val result = BitcoinBackendValidator.validate(validInput(host = ""))

        assertContains(result.errors, BackendProfileValidationError.MissingHost)
        assertNull(result.normalizedProfile)
    }

    @Test
    fun invalidPortValidationFails() {
        val result = BitcoinBackendValidator.validate(validInput(portText = "70000"))

        assertContains(result.errors, BackendProfileValidationError.InvalidPort)
        assertNull(result.normalizedProfile)
    }

    @Test
    fun mainnetSelectionIsRejected() {
        val result = BitcoinBackendValidator.validate(validInput(network = NetworkEnvironment.MainnetDisabled))

        assertContains(result.errors, BackendProfileValidationError.MainnetDisabled)
        assertNull(result.normalizedProfile)
    }

    @Test
    fun urlUserInfoAndCredentialLikeEndpointInputIsRejected() {
        val result = BitcoinBackendValidator.validate(
            validInput(
                type = BitcoinBackendType.Esplora,
                host = "https://DEMO_VALUE_DO_NOT_USE@example.invalid/api",
                portText = "",
            ),
        )

        assertContains(result.errors, BackendProfileValidationError.CredentialMaterialRejected)
        assertNull(result.normalizedProfile)
    }

    @Test
    fun mainnetDefaultCoreRpcPortIsRejectedEvenOnDevelopmentNetwork() {
        val result = BitcoinBackendValidator.validate(
            validInput(
                type = BitcoinBackendType.BitcoinCoreRpc,
                network = NetworkEnvironment.Regtest,
                host = "127.0.0.1",
                portText = "8332",
            ),
        )

        assertContains(result.errors, BackendProfileValidationError.MainnetDefaultEndpointRejected)
        assertNull(result.normalizedProfile)
    }

    @Test
    fun unifiedAddressInputRejectsAmbiguousSeparatePortAndMalformedIpv6() {
        val ambiguous = BitcoinBackendValidator.validate(
            validInput(
                type = BitcoinBackendType.Electrum,
                host = "127.0.0.1:50001",
                portText = "50002",
            ),
        )
        val malformedIpv6 = BitcoinBackendValidator.validate(
            validInput(
                type = BitcoinBackendType.Electrum,
                host = "[::1",
                portText = "",
            ),
        )

        assertContains(ambiguous.errors, BackendProfileValidationError.AmbiguousEndpointPort)
        assertContains(malformedIpv6.errors, BackendProfileValidationError.MalformedEndpoint)
        assertNull(ambiguous.normalizedProfile)
        assertNull(malformedIpv6.normalizedProfile)
    }

    @Test
    fun serializedSettingsDoNotContainCredentialFieldsOrSecretLikeValues() {
        val storage = InMemorySettingsStorage()
        val profile = validatedProfile(validInput(label = "Serialized Core"))

        assertIs<SettingsWriteResult.Saved>(PersistentSettingsRepository(storage).saveBitcoinBackendProfile(profile))
        val serialized = assertNotNull(storage.readText()).lowercase()

        assertFalse(serialized.contains("credential"))
        assertFalse(serialized.contains("password"))
        assertFalse(serialized.contains("token"))
        assertFalse(serialized.contains("cookie"))
        assertFalse(serialized.contains("macaroon"))
        assertFalse(serialized.contains("nsec"))
    }

    @Test
    fun noDefaultSkaldManagedBackendEndpointExists() {
        val state = PersistentSettingsRepository(InMemorySettingsStorage()).loadBitcoinBackendSettings()

        assertTrue(state.profiles.isEmpty())
        assertNull(state.selectedProfileId)
    }

    @Test
    fun publicBackendWarningIsPresentWhenTrustModelIsPublicOrThirdParty() {
        val publicProfile = validatedProfile(
            validInput(
                label = "Public Esplora",
                type = BitcoinBackendType.Esplora,
                host = "example.invalid",
                portText = "",
                trustModel = BitcoinBackendTrustModel.PublicBackend,
            ),
        )
        val thirdPartyProfile = validatedProfile(
            validInput(
                label = "Trusted Electrum",
                type = BitcoinBackendType.Electrum,
                host = "example.invalid",
                portText = "50001",
                trustModel = BitcoinBackendTrustModel.TrustedThirdParty,
            ),
        )

        assertTrue(publicProfile.warnings.any { it.contains("Public backends", ignoreCase = true) })
        assertTrue(thirdPartyProfile.warnings.any { it.contains("observe wallet queries", ignoreCase = true) })
    }

    @Test
    fun connectionTestRemainsDisabledAndNotImplemented() {
        val profile = validatedProfile(validInput(label = "No connection Core"))
        val result = PlaceholderBitcoinBackendConnectionTester().testConnection(profile)

        assertTrue(result is WalletOperationResult.Disabled)
        assertTrue(result.reason.contains("CONNECTION_TEST_NOT_IMPLEMENTED"))
    }

    private fun validatedProfile(input: EditableBitcoinBackendProfileInput): BitcoinBackendProfile {
        val result = BitcoinBackendValidator.validate(input)
        assertTrue(result.errors.isEmpty(), result.errors.joinToString { it.message })
        return assertNotNull(result.normalizedProfile)
    }

    private fun validInput(
        label: String = "Local testnet Core",
        type: BitcoinBackendType = BitcoinBackendType.BitcoinCoreRpc,
        network: NetworkEnvironment = NetworkEnvironment.Testnet4,
        host: String = "127.0.0.1",
        portText: String = "18443",
        useTls: Boolean = false,
        path: String = "",
        trustModel: BitcoinBackendTrustModel = BitcoinBackendTrustModel.UserOwnedNode,
    ): EditableBitcoinBackendProfileInput =
        EditableBitcoinBackendProfileInput(
            label = label,
            type = type,
            network = network,
            host = host,
            portText = portText,
            useTls = useTls,
            path = path,
            trustModel = trustModel,
        )
}
