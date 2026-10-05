package com.libertasprimordium.skald

import com.libertasprimordium.skald.domain.monero.MoneroNetworkEvidence
import com.libertasprimordium.skald.security.DisabledSecureWalletMetadataRepository
import com.libertasprimordium.skald.security.SecureMetadataPayload
import com.libertasprimordium.skald.security.SecureMetadataPersistencePolicy
import com.libertasprimordium.skald.security.SecureMetadataRecordDescriptor
import com.libertasprimordium.skald.security.SecureMetadataRecordId
import com.libertasprimordium.skald.security.SecureMetadataRepositoryError
import com.libertasprimordium.skald.security.SecureMetadataRepositoryResult
import com.libertasprimordium.skald.security.SecureMetadataRequirement
import com.libertasprimordium.skald.security.SecureMetadataStorageStatus
import com.libertasprimordium.skald.security.SensitiveMetadataKind
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class SecureMetadataBoundaryTest {
    private val repository = DisabledSecureWalletMetadataRepository()
    private val secureMetadata = repository.capability

    @Test
    fun everySensitiveMetadataKindRequiresEncryptedMetadataStorage() {
        assertEquals(SensitiveMetadataKind.entries.toSet(), secureMetadata.sensitiveKinds)
        assertTrue(secureMetadata.isFailClosed)
        assertEquals(SecureMetadataStorageStatus.EncryptedVaultUnavailable, secureMetadata.status)
        assertFalse(secureMetadata.canListMetadata)
        assertFalse(secureMetadata.canStoreMetadata)
        assertFalse(secureMetadata.canReadMetadata)
        assertFalse(secureMetadata.canDeleteMetadata)
        assertTrue(SecureMetadataRequirement.AppControlledEncryptedVault in secureMetadata.requirements)
        assertTrue(SecureMetadataRequirement.NoPlaintextSettingsStorage in secureMetadata.requirements)
        assertTrue(SecureMetadataRequirement.NoOsKeyringAsPrimaryStore in secureMetadata.requirements)
    }

    @Test
    fun eachMetadataKindRequiresEncryptedPolicyAndAllNetworkOperationsFailClosed() {
        SensitiveMetadataKind.entries.forEach { kind ->
            assertTrue(SecureMetadataPersistencePolicy.requiresEncryptedMetadataStorage(kind))
            val policy = SecureMetadataPersistencePolicy.evaluate(secureMetadata, setOf(kind))
            assertEquals(setOf(kind), policy.requiredKinds)
            assertFalse(policy.canPersistSensitiveMetadata)
            assertTrue(SecureMetadataRepositoryError.EncryptedVaultUnavailable in policy.blockers)
            assertTrue(SecureMetadataRepositoryError.MetadataPersistenceDisabled in policy.blockers)
            MoneroNetworkEvidence.entries.forEach { network ->
                val descriptor = descriptor(kind, network)
                val put = repository.putMetadata(descriptor, SecureMetadataPayload.placeholderOnly())
                val get = repository.getMetadata(descriptor.id)
                val delete = repository.deleteMetadata(descriptor.id)
                val list = repository.listMetadata(kind)
                assertIs<SecureMetadataRepositoryResult.Rejected>(put)
                assertIs<SecureMetadataRepositoryResult.Unavailable>(get)
                assertIs<SecureMetadataRepositoryResult.Disabled>(delete)
                assertIs<SecureMetadataRepositoryResult.Disabled>(list)
                assertEquals(
                    if (network == MoneroNetworkEvidence.Mainnet) SecureMetadataRepositoryError.MainnetDisabled
                    else SecureMetadataRepositoryError.EncryptedVaultUnavailable,
                    put.error,
                )
                assertTrue(put.reason.contains("SECURE_METADATA_PERSISTENCE_DISABLED"))
                listOf(put, get, delete, list).forEach { result ->
                    assertFalse(result is SecureMetadataRepositoryResult.Success<*>)
                    assertFalse(result.toString().contains(descriptor.id.value))
                }
                assertFalse(descriptor.containsSensitivePayload)
            }
        }
        assertIs<SecureMetadataRepositoryResult.Disabled>(repository.listMetadata())
    }

    @Test
    fun defaultNetworkIsUnspecifiedWithoutAuthorization() {
        val descriptor = SecureMetadataRecordDescriptor(
            id = SecureMetadataRecordId("SECURE_METADATA_DISABLED"),
            kind = SensitiveMetadataKind.MoneroScanCheckpoint,
        )
        assertEquals(MoneroNetworkEvidence.Unspecified, descriptor.network)
        assertIs<SecureMetadataRepositoryResult.Rejected>(
            repository.putMetadata(descriptor, SecureMetadataPayload.placeholderOnly()),
        )
    }

    @Test
    fun disabledSecureMetadataRepositoryDoesNotExposePayloadOrIdentifier() {
        val payload = SecureMetadataPayload.placeholderOnly()
        assertEquals("SecureMetadataPayload(REDACTED)", payload.toString())
        assertEquals("REDACTED_SECURE_METADATA_PAYLOAD", payload.redactedDisplay())
        SensitiveMetadataKind.entries.forEach { kind ->
            val descriptor = descriptor(kind)
            val result = repository.putMetadata(descriptor, payload)
            assertFalse(descriptor.toString().contains(descriptor.id.value))
            assertFalse(result.toString().contains(descriptor.id.value))
            assertFalse(result.toString().contains("SYNTHETIC_PAYLOAD"))
        }
    }

    @Test
    fun mainnetMetadataPersistencePathCannotSucceed() {
        val result = repository.putMetadata(
            descriptor(SensitiveMetadataKind.MoneroTransactionMetadata, MoneroNetworkEvidence.Mainnet),
            SecureMetadataPayload.placeholderOnly(),
        )
        assertIs<SecureMetadataRepositoryResult.Rejected>(result)
        assertEquals(SecureMetadataRepositoryError.MainnetDisabled, result.error)
        assertTrue(result.error.safeDetail.contains("Mainnet"))
    }

    @Test
    fun claimedCapabilityCannotEnableRepositoryOrRemoveEncryptedPolicyGate() {
        val capability = secureMetadata.copy(
            canListMetadata = true,
            canStoreMetadata = true,
            canReadMetadata = true,
            canDeleteMetadata = true,
            requirements = emptySet(),
        )
        val claimed = DisabledSecureWalletMetadataRepository(capability)
        val decision = SecureMetadataPersistencePolicy.evaluate(capability)
        assertFalse(decision.canPersistSensitiveMetadata)
        assertTrue(SecureMetadataRepositoryError.EncryptedVaultUnavailable in decision.blockers)
        SensitiveMetadataKind.entries.forEach { kind ->
            val descriptor = descriptor(kind)
            assertIs<SecureMetadataRepositoryResult.Rejected>(claimed.putMetadata(descriptor, SecureMetadataPayload.placeholderOnly()))
            assertIs<SecureMetadataRepositoryResult.Unavailable>(claimed.getMetadata(descriptor.id))
            assertIs<SecureMetadataRepositoryResult.Disabled>(claimed.deleteMetadata(descriptor.id))
            assertIs<SecureMetadataRepositoryResult.Disabled>(claimed.listMetadata(kind))
        }
    }

    private fun descriptor(
        kind: SensitiveMetadataKind,
        network: MoneroNetworkEvidence = MoneroNetworkEvidence.Unspecified,
    ) = SecureMetadataRecordDescriptor(
        id = SecureMetadataRecordId("SECURE_METADATA_DISABLED"),
        kind = kind,
        network = network,
    )
}
