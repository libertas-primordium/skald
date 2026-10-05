package com.libertasprimordium.skald

import com.libertasprimordium.skald.domain.security.SecretId
import com.libertasprimordium.skald.domain.security.SecretKind
import com.libertasprimordium.skald.domain.security.SecretMetadata
import com.libertasprimordium.skald.domain.security.SecretStorageStatus
import com.libertasprimordium.skald.domain.security.SecretRecoveryWarning
import com.libertasprimordium.skald.domain.security.SecretLabel
import com.libertasprimordium.skald.security.SecureMetadataRecordId
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformPathConstructionArtifactKind
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformPathConstructionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformPathConstructionRequest
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformPathConstructionResult
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformRootResolverPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformRootResolverRequest
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformRootResolverResult
import com.libertasprimordium.skald.security.SkaldVaultV1StorageIdentifierSource
import com.libertasprimordium.skald.security.SkaldVaultV1StorageNamespacePathPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1StorageNamespacePathResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultStorageRecordDescriptor
import com.libertasprimordium.skald.security.SkaldVaultV1VaultStorageRecordDescriptorResult
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertIs

class VaultApprovedInputDomainTest {
    @Test
    fun existingOpaqueIdentifierFactoriesRoundTripWithoutOperationalPaths() {
        val root = assertIs<SkaldVaultV1PlatformRootResolverResult.Accepted<*>>(
            SkaldVaultV1PlatformRootResolverPolicy.resolve(
                SkaldVaultV1PlatformRootResolverRequest.androidAppPrivateInternalEvidence(),
            ),
        ).value as com.libertasprimordium.skald.security.SkaldVaultV1PlatformRootResolverEvidence
        repeat(256) { index ->
            // Identifier bytes only: no randomness, wallet keys, cryptographic fixture or engine state.
            val identifier = ByteArray(16) { offset -> (index + offset).toByte() }
            val encoded = assertIs<SkaldVaultV1StorageNamespacePathResult.Accepted<*>>(
                SkaldVaultV1StorageNamespacePathPolicy.encodeRecordStorageId(identifier),
            ).value as com.libertasprimordium.skald.security.SkaldVaultV1StoragePathSegment
            val descriptor = assertIs<SkaldVaultV1VaultStorageRecordDescriptorResult.Accepted<*>>(
                SkaldVaultV1VaultStorageRecordDescriptor.rawCandidate(encoded.value),
            ).value as SkaldVaultV1VaultStorageRecordDescriptor
            assertEquals(encoded.value, descriptor.testOnlyRecordIdentifier())
            assertFalse(descriptor.toString().contains(encoded.value))
            val path = assertIs<SkaldVaultV1PlatformPathConstructionResult.Accepted<*>>(
                SkaldVaultV1PlatformPathConstructionPolicy.plan(
                    SkaldVaultV1PlatformPathConstructionRequest.fromRootEvidenceAndSegmentValues(
                        rootEvidence = root,
                        artifactKind = SkaldVaultV1PlatformPathConstructionArtifactKind.RecordArtifact,
                        segmentValues = listOf("skald-vault-v1", "records", encoded.value),
                    ),
                ),
            ).value as com.libertasprimordium.skald.security.SkaldVaultV1PlatformPathConstructionEvidence
            path.locations().forEach { location ->
                assertFalse(location.platformPathConstructed)
                assertFalse(location.usableForFileIo)
                assertFalse(location.usableForPersistence)
                assertFalse(location.recordReadWriteAvailable)
            }
        }
    }

    @Test
    fun untrustedWrappingNormalizationAndBoundaryMutationsNeverEnterIdentifierDomain() {
        val valid = "record_" + "0123456789abcdef".repeat(2)
        val invalid = buildList {
            addAll(listOf("", " ", ".", "..", "plain-label", "record_", "record_unapproved", "record_safe_record_fixture"))
            addAll(listOf(" " + valid, valid + " ", valid.uppercase(), valid + "0", valid.dropLast(1), "x" + valid, valid + valid))
            addAll(listOf("record_" + "0".repeat(64), "record_" + "1".repeat(95), "record_" + "1".repeat(106)))
            addAll(listOf("0".repeat(64), "1".repeat(95), "1".repeat(106), "X".repeat(4097)))
            addAll(listOf("record_../value", "record_%2e%2e", "record_\\value", "record_/value", "record_\u200b" + "0".repeat(32)))
            (0..33).filter { it != 32 }.forEach { length -> add("record_" + "0".repeat(length)) }
            listOf("_", "-", "g", "G", " ", "\t", "/", "\\", ":", "@", "%", "\u03bf").forEach { character ->
                add("record_" + "0".repeat(15) + character + "0".repeat(16))
            }
        }
        val root = assertIs<SkaldVaultV1PlatformRootResolverResult.Accepted<*>>(
            SkaldVaultV1PlatformRootResolverPolicy.resolve(SkaldVaultV1PlatformRootResolverRequest.androidAppPrivateInternalEvidence()),
        ).value as com.libertasprimordium.skald.security.SkaldVaultV1PlatformRootResolverEvidence
        invalid.forEachIndexed { index, candidate ->
            assertIs<SkaldVaultV1StorageNamespacePathResult.Rejected>(
                SkaldVaultV1StorageNamespacePathPolicy.validatePathSegment(candidate), "Namespace mutation $index",
            )
            val descriptor = assertIs<SkaldVaultV1VaultStorageRecordDescriptorResult.Rejected>(
                SkaldVaultV1VaultStorageRecordDescriptor.rawCandidate(candidate), "Descriptor mutation $index",
            )
            assertIs<SkaldVaultV1PlatformPathConstructionResult.Rejected>(
                SkaldVaultV1PlatformPathConstructionPolicy.plan(
                    SkaldVaultV1PlatformPathConstructionRequest.fromRootEvidenceAndSegmentValues(
                        rootEvidence = root,
                        artifactKind = SkaldVaultV1PlatformPathConstructionArtifactKind.ExplicitSegmentProbe,
                        segmentValues = listOf("skald-vault-v1", candidate),
                    ),
                ), "Platform segment mutation $index",
            )
            if (candidate.isNotBlank()) assertFalse(descriptor.toString().contains(candidate))
        }
        assertIs<SkaldVaultV1StorageNamespacePathResult.Rejected>(
            SkaldVaultV1StorageNamespacePathPolicy.validatePathSegment(valid, SkaldVaultV1StorageIdentifierSource.UserControlledText),
        )
        assertIs<SkaldVaultV1StorageNamespacePathResult.Rejected>(
            SkaldVaultV1StorageNamespacePathPolicy.validatePathSegment(valid, SkaldVaultV1StorageIdentifierSource.SecretMaterialCandidate),
        )
    }

    @Test
    fun directlyConstructedPlaceholdersRejectUnapprovedRawMetadata() {
        listOf("", "unapproved", "record_unapproved", "0".repeat(64), "1".repeat(95), "1".repeat(106), "X".repeat(4097)).forEach { candidate ->
            assertFailsWith<IllegalArgumentException> { SecretId(candidate) }
            assertFailsWith<IllegalArgumentException> { SecretLabel(candidate) }
            assertFailsWith<IllegalArgumentException> { SecureMetadataRecordId(candidate) }
            assertFailsWith<IllegalArgumentException> {
                SecretMetadata(SecretId("backup-key"), SecretLabel("${SecretKind.MoneroRecoveryMaterial.label} metadata"), SecretKind.MoneroRecoveryMaterial, candidate, SecretStorageStatus.NotImplemented, SecretRecoveryWarning.NativeSeedRequired)
            }
        }
        SecretKind.entries.forEach { kind ->
            val label = SecretLabel("${kind.label} metadata")
            assertFalse(label.toString().contains(label.value))
        }
        assertEquals("backup-key", SecretId("backup-key").value)
        assertEquals("SECURE_METADATA_DISABLED", SecureMetadataRecordId("SECURE_METADATA_DISABLED").value)
    }
}
