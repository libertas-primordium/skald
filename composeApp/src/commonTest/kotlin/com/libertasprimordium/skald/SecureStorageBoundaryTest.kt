package com.libertasprimordium.skald

import com.libertasprimordium.skald.domain.core.CredentialReference
import com.libertasprimordium.skald.domain.core.NetworkEnvironment
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendTrustModel
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendType
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendValidator
import com.libertasprimordium.skald.domain.onchain.EditableBitcoinBackendProfileInput
import com.libertasprimordium.skald.domain.security.SecretId
import com.libertasprimordium.skald.domain.security.SecretKind
import com.libertasprimordium.skald.domain.security.SecretLabel
import com.libertasprimordium.skald.domain.security.SecretPolicy
import com.libertasprimordium.skald.domain.security.SecretRecoveryWarning
import com.libertasprimordium.skald.domain.security.SecretStorageStatus
import com.libertasprimordium.skald.security.DisabledSecureSecretStorage
import com.libertasprimordium.skald.security.SecretPayload
import com.libertasprimordium.skald.security.SecureStorageResult
import com.libertasprimordium.skald.security.toUiStatus
import com.libertasprimordium.skald.settings.BitcoinBackendSettingsCodec
import com.libertasprimordium.skald.settings.InMemorySettingsStorage
import com.libertasprimordium.skald.settings.PersistentSettingsRepository
import com.libertasprimordium.skald.settings.SettingsWriteResult
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class SecureStorageBoundaryTest {
    private val storage = DisabledSecureSecretStorage()

    @Test
    fun disabledSecureStorageReportsFailClosedCapability() {
        val capability = storage.capability

        assertEquals(SecretStorageStatus.NotImplemented, capability.status)
        assertTrue(capability.isFailClosed)
        assertFalse(capability.canListMetadata)
        assertFalse(capability.canStoreSecrets)
        assertFalse(capability.canReadSecrets)
        assertFalse(capability.canDeleteSecrets)
    }

    @Test
    fun putSecretRejectsSeedOrMnemonicSecret() {
        val seedMetadata = metadata(SecretKind.BitcoinSeed, "app-seed")
        val mnemonicMetadata = metadata(SecretKind.Bip39Mnemonic, "mnemonic")

        val seedResult = storage.putSecret(seedMetadata, SecretPayload.placeholderOnly())
        val mnemonicResult = storage.putSecret(mnemonicMetadata, SecretPayload.placeholderOnly())

        assertIs<SecureStorageResult.Rejected>(seedResult)
        assertIs<SecureStorageResult.Rejected>(mnemonicResult)
        assertTrue(seedResult.reason.contains("SECRET_STORAGE_NOT_IMPLEMENTED"))
        assertTrue(mnemonicResult.reason.contains("SECRET_STORAGE_NOT_IMPLEMENTED"))
    }

    @Test
    fun putSecretRejectsNostrSecret() {
        val result = storage.putSecret(
            metadata(SecretKind.NostrNsec, "nostr-identity-key"),
            SecretPayload.placeholderOnly(),
        )

        assertIs<SecureStorageResult.Rejected>(result)
        assertTrue(result.reason.contains("SECRET_STORAGE_NOT_IMPLEMENTED"))
    }

    @Test
    fun putSecretRejectsLightningCredentialSecret() {
        val macaroon = storage.putSecret(
            metadata(SecretKind.LightningMacaroon, "lightning-macaroon"),
            SecretPayload.placeholderOnly(),
        )
        val nwc = storage.putSecret(
            metadata(SecretKind.NwcSecret, "nwc-secret"),
            SecretPayload.placeholderOnly(),
        )

        assertIs<SecureStorageResult.Rejected>(macaroon)
        assertIs<SecureStorageResult.Rejected>(nwc)
    }

    @Test
    fun getSecretRejectsAnySecretId() {
        val result = storage.getSecret(SecretId("SECRET_STORAGE_DISABLED"))

        assertIs<SecureStorageResult.Unavailable>(result)
        assertTrue(result.reason.contains("SECRET_STORAGE_NOT_IMPLEMENTED"))
    }

    @Test
    fun deleteSecretRejectsAnySecretId() {
        val result = storage.deleteSecret(SecretId("SECRET_STORAGE_DISABLED"))

        assertIs<SecureStorageResult.Disabled>(result)
        assertTrue(result.reason.contains("SECRET_STORAGE_NOT_IMPLEMENTED"))
    }

    @Test
    fun secretPayloadDisplayIsAlwaysRedacted() {
        val payload = SecretPayload.placeholderOnly()

        assertEquals("SecretPayload(REDACTED)", payload.toString())
        assertEquals("REDACTED_SECRET_PAYLOAD", payload.redactedDisplay())
    }

    @Test
    fun secretMetadataContainsNoPayloadValue() {
        val metadata = metadata(SecretKind.BackupEncryptionKey, "backup-key")

        assertFalse(metadata.containsSecretPayload)
        assertFalse(metadata.toString().contains("NOT_A_REAL_SECRET"))
        assertFalse(metadata.toString().contains("DEMO_VALUE_DO_NOT_USE"))
    }

    @Test
    fun backendSettingsSerializationStillOmitsCredentialValues() {
        val profile = assertNotNull(
            BitcoinBackendValidator.validate(
                EditableBitcoinBackendProfileInput(
                    label = "Storage reference sanitizer",
                    type = BitcoinBackendType.BitcoinCoreRpc,
                    network = NetworkEnvironment.Testnet4,
                    host = "127.0.0.1",
                    portText = "18443",
                    useTls = false,
                    path = "",
                    trustModel = BitcoinBackendTrustModel.UserOwnedNode,
                ),
            ).normalizedProfile,
        ).copy(
            credentialReference = CredentialReference("CREDENTIAL_STORAGE_NOT_IMPLEMENTED"),
        )
        val settingsStorage = InMemorySettingsStorage()

        assertIs<SettingsWriteResult.Saved>(
            PersistentSettingsRepository(settingsStorage).saveBitcoinBackendProfile(profile),
        )
        val serialized = assertNotNull(settingsStorage.readText())

        assertFalse(serialized.contains("credential", ignoreCase = true))
        assertFalse(serialized.contains("CREDENTIAL_STORAGE_NOT_IMPLEMENTED"))
        assertFalse(serialized.contains("NOT_A_REAL_SECRET"))
        assertFalse(serialized.contains("DEMO_VALUE_DO_NOT_USE"))
    }

    @Test
    fun backendCredentialReferenceFixtureIsMetadataOnlyWhenPresent() {
        val reference = CredentialReference("secure-storage-reference-placeholder")

        assertFalse(reference.value.contains("NOT_A_REAL_SECRET"))
        assertFalse(reference.value.contains("DEMO_VALUE_DO_NOT_USE"))
        assertFalse(reference.value.contains("password", ignoreCase = true))
        assertFalse(reference.value.contains("token", ignoreCase = true))
    }

    @Test
    fun noSecureStorageOperationReturnsSuccess() {
        val metadata = metadata(SecretKind.MetadataEncryptionKey, "metadata-key")
        val operations = listOf(
            storage.listMetadata(),
            storage.putSecret(metadata, SecretPayload.placeholderOnly()),
            storage.getSecret(metadata.id),
            storage.deleteSecret(metadata.id),
        )

        assertTrue(operations.none { it is SecureStorageResult.Success<*> })
    }

    @Test
    fun uiStatusReportsSecretStorageDisabled() {
        val status = storage.capability.toUiStatus()

        assertEquals("disabled / not implemented", status.state)
        assertTrue(status.detail.contains("not enabled", ignoreCase = true))
        assertTrue(status.plannedSecretClasses.any { it.contains("App seed") })
        assertTrue(status.disabledActions.contains("Initialize secret storage"))
    }

    @Test
    fun secretPolicyRequiresSecureStorageForPlannedSecretKinds() {
        assertTrue(SecretPolicy.plannedSecretKinds.contains(SecretKind.BitcoinSeed))
        assertTrue(SecretPolicy.plannedSecretKinds.contains(SecretKind.NostrNsec))
        assertTrue(SecretPolicy.plannedSecretKinds.contains(SecretKind.CashuProofMaterial))
        assertTrue(SecretPolicy.plannedSecretKinds.all { SecretPolicy.requiresSecureStorage(it) })
    }

    @Test
    fun malformedBackendSettingsStillFailSafelyToEmptyState() {
        val decoded = BitcoinBackendSettingsCodec.decode("skald.backend-settings.v1\nprofile|bad")

        assertTrue(decoded.profiles.isEmpty())
        assertEquals(null, decoded.selectedProfileId)
    }

    private fun metadata(
        kind: SecretKind,
        id: String,
    ) = SecretPolicy.placeholderMetadata(
        id = SecretId(id),
        label = SecretLabel("${kind.label} metadata"),
        kind = kind,
        recoveryWarning = SecretRecoveryWarning.BackupKeyRequired,
    )
}
