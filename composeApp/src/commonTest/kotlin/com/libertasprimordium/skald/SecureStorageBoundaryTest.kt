package com.libertasprimordium.skald

import com.libertasprimordium.skald.domain.security.SecretId
import com.libertasprimordium.skald.domain.security.SecretKind
import com.libertasprimordium.skald.domain.security.SecretLabel
import com.libertasprimordium.skald.domain.security.SecretPolicy
import com.libertasprimordium.skald.domain.security.SecretRecoveryWarning
import com.libertasprimordium.skald.domain.security.SecretStorageStatus
import com.libertasprimordium.skald.security.DisabledSecureSecretStorage
import com.libertasprimordium.skald.security.SecretPayload
import com.libertasprimordium.skald.security.SecureStorageResult
import com.libertasprimordium.skald.security.androidDisabledSecureStorageCapability
import com.libertasprimordium.skald.security.commonDisabledSecureStorageCapability
import com.libertasprimordium.skald.security.desktopDisabledSecureStorageCapability
import com.libertasprimordium.skald.security.toUiStatus
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class SecureStorageBoundaryTest {
    private val storage = DisabledSecureSecretStorage()

    @Test
    fun disabledSecureStorageReportsFailClosedCapabilityOnEveryPlatform() {
        listOf(
            commonDisabledSecureStorageCapability(),
            androidDisabledSecureStorageCapability(),
            desktopDisabledSecureStorageCapability(),
        ).forEach { capability ->
            assertEquals(SecretStorageStatus.NotImplemented, capability.status)
            assertTrue(capability.isFailClosed)
            assertFalse(capability.canListMetadata)
            assertFalse(capability.canStoreSecrets)
            assertFalse(capability.canReadSecrets)
            assertFalse(capability.canDeleteSecrets)
        }
    }

    @Test
    fun everyMoneroAndGenericSecretKindRequiresSecureStorage() {
        assertEquals(SecretKind.entries.toSet(), SecretPolicy.plannedSecretKinds.toSet())
        assertEquals(SecretKind.entries.size, SecretPolicy.plannedSecretKinds.size)
        assertTrue(SecretKind.entries.all(SecretPolicy::requiresSecureStorage))
        assertTrue(SecretPolicy.requiresSecureStorage(SecretKind.MoneroPrivateViewKey))
        assertTrue(SecretPolicy.requiresSecureStorage(SecretKind.MoneroPrivateSpendKey))
        assertTrue(SecretPolicy.requiresSecureStorage(SecretKind.MoneroRecoveryMaterial))
        assertTrue(SecretPolicy.requiresSecureStorage(SecretKind.MoneroTransactionSecretMaterial))
        assertTrue(SecretPolicy.requiresSecureStorage(SecretKind.MoneroDaemonCredential))
        assertTrue(SecretPolicy.requiresSecureStorage(SecretKind.MoneroLwsCredential))
    }

    @Test
    fun allMoneroSecretKindsRemainPayloadFreeAndFailClosed() {
        SecretKind.entries.forEach { kind ->
            val metadata = metadata(kind)
            assertFalse(metadata.containsSecretPayload)
            assertEquals(SecretStorageStatus.NotImplemented, metadata.storageStatus)
            val put = storage.putSecret(metadata, SecretPayload.placeholderOnly())
            val get = storage.getSecret(metadata.id)
            val delete = storage.deleteSecret(metadata.id)
            val list = storage.listMetadata()
            assertIs<SecureStorageResult.Rejected>(put)
            assertIs<SecureStorageResult.Unavailable>(get)
            assertIs<SecureStorageResult.Disabled>(delete)
            assertIs<SecureStorageResult.Disabled>(list)
            assertTrue(put.reason.contains("SECRET_STORAGE_NOT_IMPLEMENTED"))
            assertTrue(get.reason.contains("SECRET_STORAGE_NOT_IMPLEMENTED"))
            assertTrue(delete.reason.contains("SECRET_STORAGE_NOT_IMPLEMENTED"))
            assertTrue(list.reason.contains("SECRET_STORAGE_NOT_IMPLEMENTED"))
            listOf(put, get, delete, list).forEach { result ->
                assertFalse(result is SecureStorageResult.Success<*>)
                assertFalse(result.toString().contains(metadata.id.value))
                assertFalse(result.toString().contains(metadata.label.value))
            }
        }
    }

    @Test
    fun secretPayloadDisplayIsAlwaysRedacted() {
        val payload = SecretPayload.placeholderOnly()
        assertEquals("SecretPayload(REDACTED)", payload.toString())
        assertEquals("REDACTED_SECRET_PAYLOAD", payload.redactedDisplay())
    }

    @Test
    fun arbitraryIdentifiersCannotEnableReadsOrDeletes() {
        listOf("", "UNTRUSTED_FIXTURE", " ", "../", "X".repeat(4097)).forEach { id ->
            val failure = assertFailsWith<IllegalArgumentException> { SecretId(id) }
            if (id.isNotBlank()) assertFalse(failure.message.orEmpty().contains(id))
            assertFailsWith<IllegalArgumentException> { SecretLabel(id) }
        }
    }

    @Test
    fun claimedCapabilityDoesNotEnableDisabledImplementation() {
        val claimed = DisabledSecureSecretStorage(
            commonDisabledSecureStorageCapability().copy(
                canListMetadata = true,
                canStoreSecrets = true,
                canReadSecrets = true,
                canDeleteSecrets = true,
            ),
        )
        SecretKind.entries.forEach { kind ->
            val metadata = metadata(kind)
            assertIs<SecureStorageResult.Disabled>(claimed.listMetadata())
            assertIs<SecureStorageResult.Rejected>(claimed.putSecret(metadata, SecretPayload.placeholderOnly()))
            assertIs<SecureStorageResult.Unavailable>(claimed.getSecret(metadata.id))
            assertIs<SecureStorageResult.Disabled>(claimed.deleteSecret(metadata.id))
        }
    }

    @Test
    fun uiStatusReportsSecretStorageDisabled() {
        val status = storage.capability.toUiStatus()
        assertEquals("disabled / not implemented", status.state)
        assertTrue(status.detail.contains("not enabled", ignoreCase = true))
        assertTrue(status.plannedSecretClasses.any { it.contains("Monero", ignoreCase = true) })
        assertTrue(status.disabledActions.contains("Initialize secret storage"))
    }

    private fun metadata(kind: SecretKind) = SecretPolicy.placeholderMetadata(
        id = SecretId("backup-key"),
        label = SecretLabel("${kind.label} metadata"),
        kind = kind,
        recoveryWarning = SecretRecoveryWarning.BackupKeyRequired,
    )
}
