package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.EncryptedVaultBlockingIssue
import com.libertasprimordium.skald.security.EncryptedVaultReadinessPolicy
import com.libertasprimordium.skald.security.EncryptedVaultRequirement
import com.libertasprimordium.skald.security.EncryptedVaultRequirementStatus
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceContract
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidence
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidenceState
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceGate
import com.libertasprimordium.skald.security.ProductionProviderConstructionContractStatus
import com.libertasprimordium.skald.security.SkaldVaultV1CandidateRecordDescriptor
import com.libertasprimordium.skald.security.SkaldVaultV1Manifest
import com.libertasprimordium.skald.security.SkaldVaultV1ManifestFormat
import com.libertasprimordium.skald.security.SkaldVaultV1ManifestRecordEntry
import com.libertasprimordium.skald.security.SkaldVaultV1ManifestRejectionReason
import com.libertasprimordium.skald.security.SkaldVaultV1ManifestResult
import com.libertasprimordium.skald.security.SkaldVaultV1RecordType
import com.libertasprimordium.skald.security.SkaldVaultV1StaleRecordDecisionKind
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class VaultManifestParserStalePolicyTest {
    @Test
    fun fixedNonSecretManifestFixtureSerializesDeterministically() {
        val serialized = acceptedBytes(SkaldVaultV1ManifestFormat.vectorFixtureManifest())

        assertEquals(EXPECTED_MANIFEST_HEX, serialized.toHex())
    }

    @Test
    fun parseSerializedFixtureReturnsLogicalFixture() {
        val fixture = SkaldVaultV1ManifestFormat.vectorFixtureManifest()
        val parsed = acceptedManifest(SkaldVaultV1ManifestFormat.parse(acceptedBytes(fixture)))

        assertManifestEquals(fixture, parsed)
    }

    @Test
    fun serializerOrdersRecordEntriesDeterministically() {
        val fixture = SkaldVaultV1ManifestFormat.vectorFixtureManifest()
        val reversed = fixture.copy(records = fixture.records.reversed())

        assertContentEquals(acceptedBytes(fixture), acceptedBytes(reversed))
    }

    @Test
    fun parserRejectsBadMagicUnsupportedVersionSuitePolicyAndNamespaces() {
        val serialized = acceptedBytes(SkaldVaultV1ManifestFormat.vectorFixtureManifest())

        assertRejected(
            SkaldVaultV1ManifestFormat.parse(
                serialized.replaceLengthPrefixedField(1, "WRONG-MANIFEST".encodeToByteArray()),
            ),
            SkaldVaultV1ManifestRejectionReason.UnsupportedMagic,
        )
        assertRejected(
            SkaldVaultV1ManifestFormat.parse(serialized.replaceU16Field(3, 2)),
            SkaldVaultV1ManifestRejectionReason.UnsupportedVersion,
        )
        assertRejected(
            SkaldVaultV1ManifestFormat.parse(
                serialized.replaceLengthPrefixedField(5, "unsupported-suite".encodeToByteArray()),
            ),
            SkaldVaultV1ManifestRejectionReason.UnsupportedSuiteId,
        )
        assertRejected(
            SkaldVaultV1ManifestFormat.parse(
                serialized.replaceLengthPrefixedField(2, "unsupported-manifest-policy".encodeToByteArray()),
            ),
            SkaldVaultV1ManifestRejectionReason.UnsupportedPolicyId,
        )
        assertRejected(
            SkaldVaultV1ManifestFormat.parse(
                serialized.replaceLengthPrefixedField(7, "unsupported-storage".encodeToByteArray()),
            ),
            SkaldVaultV1ManifestRejectionReason.UnsupportedStorageNamespace,
        )
        assertRejected(
            SkaldVaultV1ManifestFormat.parse(
                serialized.replaceLengthPrefixedField(8, "unsupported-records".encodeToByteArray()),
            ),
            SkaldVaultV1ManifestRejectionReason.UnsupportedRecordNamespace,
        )
    }

    @Test
    fun parserRejectsMalformedLengthsTruncationTrailingUnknownAndDuplicateFields() {
        val serialized = acceptedBytes(SkaldVaultV1ManifestFormat.vectorFixtureManifest())

        assertRejected(
            SkaldVaultV1ManifestFormat.parse(serialized.replaceLengthPrefixedField(4, ByteArray(15))),
            SkaldVaultV1ManifestRejectionReason.MalformedLength,
        )
        assertRejected(
            SkaldVaultV1ManifestFormat.parse(serialized.replaceLengthPrefixedField(6, ByteArray(31))),
            SkaldVaultV1ManifestRejectionReason.MalformedLength,
        )
        assertRejected(
            SkaldVaultV1ManifestFormat.parse(serialized.copyOf(serialized.size - 1)),
            SkaldVaultV1ManifestRejectionReason.TruncatedInput,
        )
        assertRejected(
            SkaldVaultV1ManifestFormat.parse(serialized.replaceLengthPrefixOnly(6, UINT16_MAX_FOR_TEST)),
            SkaldVaultV1ManifestRejectionReason.TruncatedInput,
        )
        assertRejected(
            SkaldVaultV1ManifestFormat.parse(serialized + 0x00.toByte()),
            SkaldVaultV1ManifestRejectionReason.TrailingBytes,
        )
        assertRejected(
            SkaldVaultV1ManifestFormat.parse(serialized.replaceFieldId(2, 99)),
            SkaldVaultV1ManifestRejectionReason.UnknownField,
        )
        assertRejected(
            SkaldVaultV1ManifestFormat.parse(serialized.duplicateField(2)),
            SkaldVaultV1ManifestRejectionReason.DuplicatedField,
        )
    }

    @Test
    fun parserRejectsDuplicateConflictingMalformedRecordAndTombstoneEvidence() {
        val serialized = acceptedBytes(SkaldVaultV1ManifestFormat.vectorFixtureManifest())
        val activeRecordId = SkaldVaultV1ManifestFormat.activeFixtureRecordId()

        assertRejected(
            SkaldVaultV1ManifestFormat.parse(
                serialized
                    .replaceManifestRecordBytesField(recordIndex = 1, fieldId = 1, replacement = activeRecordId)
                    .replaceManifestRecordU64Field(recordIndex = 1, fieldId = 3, replacement = 7),
            ),
            SkaldVaultV1ManifestRejectionReason.DuplicateRecordId,
        )
        assertRejected(
            SkaldVaultV1ManifestFormat.parse(
                serialized.replaceManifestRecordBytesField(
                    recordIndex = 1,
                    fieldId = 1,
                    replacement = activeRecordId,
                ),
            ),
            SkaldVaultV1ManifestRejectionReason.ConflictingLatestCounter,
        )
        assertRejected(
            SkaldVaultV1ManifestFormat.parse(
                serialized.replaceManifestRecordBytesField(
                    recordIndex = 0,
                    fieldId = 4,
                    replacement = "x".repeat(129).encodeToByteArray(),
                ),
            ),
            SkaldVaultV1ManifestRejectionReason.MalformedRecordReference,
        )
        assertRejected(
            SkaldVaultV1ManifestFormat.parse(
                serialized.replaceManifestRecordU16Field(recordIndex = 0, fieldId = 5, replacement = 2),
            ),
            SkaldVaultV1ManifestRejectionReason.MalformedTombstoneState,
        )
    }

    @Test
    fun serializerRejectsUnsupportedModelValuesBeforeBytesAreReturned() {
        val fixture = SkaldVaultV1ManifestFormat.vectorFixtureManifest()

        assertRejected(
            SkaldVaultV1ManifestFormat.serialize(fixture.copy(providerSuiteId = "unsupported-suite")),
            SkaldVaultV1ManifestRejectionReason.UnsupportedSuiteId,
        )
        assertRejected(
            SkaldVaultV1ManifestFormat.serialize(fixture.copy(manifestPolicyId = "unsupported-policy")),
            SkaldVaultV1ManifestRejectionReason.UnsupportedPolicyId,
        )
        assertRejected(
            SkaldVaultV1ManifestFormat.serialize(fixture.copy(storageNamespace = "unsupported-storage")),
            SkaldVaultV1ManifestRejectionReason.UnsupportedStorageNamespace,
        )
        assertRejected(
            SkaldVaultV1ManifestFormat.serialize(
                fixture.copy(
                    records = fixture.records + fixture.records.first(),
                ),
            ),
            SkaldVaultV1ManifestRejectionReason.DuplicateRecordId,
        )
        assertRejected(
            SkaldVaultV1ManifestFormat.serialize(
                fixture.copy(
                    records = listOf(
                        fixture.records.first().copy(recordReference = "x".repeat(129)),
                    ),
                ),
            ),
            SkaldVaultV1ManifestRejectionReason.MalformedRecordReference,
        )
    }

    @Test
    fun stalePolicyClassifiesCurrentNewerStaleConflictsAndUnknownIds() {
        val manifest = SkaldVaultV1ManifestFormat.vectorFixtureManifest()
        val active = manifest.records.first()

        assertDecision(
            manifest,
            candidate = active.candidate(counter = 7),
            expected = SkaldVaultV1StaleRecordDecisionKind.CurrentTrusted,
        )
        assertDecision(
            manifest,
            candidate = active.candidate(counter = 8),
            expected = SkaldVaultV1StaleRecordDecisionKind.NewerPendingManifestUpdate,
        )
        assertDecision(
            manifest,
            candidate = active.candidate(counter = 6),
            expected = SkaldVaultV1StaleRecordDecisionKind.StaleRejected,
        )
        assertDecision(
            manifest,
            candidate = active.candidate(counter = 7, recordTypeId = SkaldVaultV1RecordType.SecretPayload.typeId),
            expected = SkaldVaultV1StaleRecordDecisionKind.RecordTypeConflictRejected,
        )
        assertDecision(
            manifest,
            candidate = active.candidate(counter = 7, tombstone = true),
            expected = SkaldVaultV1StaleRecordDecisionKind.TombstoneConflictRejected,
        )
        assertDecision(
            manifest,
            candidate = SkaldVaultV1CandidateRecordDescriptor(
                recordId = (0x70..0x7f).map { it.toByte() }.toByteArray(),
                recordTypeId = SkaldVaultV1RecordType.SensitiveMetadata.typeId,
                recordVersionCounter = 1,
                recordReference = "in-memory-manifest-record-0003",
                tombstone = false,
            ),
            expected = SkaldVaultV1StaleRecordDecisionKind.UnknownRecordPendingManifestUpdate,
        )
    }

    @Test
    fun manifestAndStaleEvidenceIsRepresentedButPersistenceAndSelectionRemainDisabled() {
        val contract = ProductionProviderAcceptanceContract.v1()
        val policy = contract.staleRecordManifestPolicy
        val storageContract = contract.containerManifestStorageContract
        val readiness = EncryptedVaultReadinessPolicy.disabled()
        val selection = VaultCryptoProviderSelectionRegistry.select()
        val evidence = ProductionProviderAcceptanceEvidence.currentDesignOnly()

        assertEquals(ProductionProviderConstructionContractStatus.ImplementedTested, policy.contractStatus)
        assertTrue(policy.manifestParserImplemented)
        assertTrue(policy.manifestWriterImplemented)
        assertTrue(policy.staleRecordDecisionPolicyImplemented)
        assertFalse(policy.manifestReadWriteImplemented)
        assertFalse(policy.storageIndexReadWriteImplemented)
        assertFalse(policy.staleRecordEnforcementImplemented)
        assertFalse(policy.fullLocalDirectoryRollbackResistanceClaimed)
        assertFalse(policy.externalOrTrustedMonotonicAnchorDesigned)

        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            storageContract.manifestContractStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            storageContract.staleRecordPolicyStatus,
        )
        assertTrue(storageContract.manifestParserImplemented)
        assertTrue(storageContract.manifestWriterImplemented)
        assertTrue(storageContract.staleRecordDecisionPolicyImplemented)
        assertFalse(storageContract.vaultPersistenceImplemented)
        assertFalse(storageContract.manifestReadWriteImplemented)
        assertFalse(storageContract.storageIndexReadWriteImplemented)
        assertFalse(storageContract.atomicWriteRecoveryImplementationAdded)
        assertFalse(storageContract.fullLocalDirectoryRollbackResistanceClaimed)
        assertFalse(storageContract.externalOrTrustedMonotonicAntiRollbackAnchorImplemented)

        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            evidence.stateFor(ProductionProviderAcceptanceGate.ManifestContractApproved),
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            evidence.stateFor(ProductionProviderAcceptanceGate.StaleRecordManifestPolicyApproved),
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[EncryptedVaultRequirement.ManifestParserImplemented],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[EncryptedVaultRequirement.ManifestWriterImplemented],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[EncryptedVaultRequirement.StaleRecordDecisionPolicyImplemented],
        )
        assertTrue(EncryptedVaultBlockingIssue.ManifestReadWriteImplementationMissing in readiness.blockers)
        assertTrue(EncryptedVaultBlockingIssue.StorageSuccessPathAbsent in readiness.blockers)
        assertTrue(EncryptedVaultBlockingIssue.AntiRollbackAnchorAbsentNoFullRollbackClaim in readiness.blockers)
        assertFalse(EncryptedVaultBlockingIssue.StaleRecordManifestPolicyImplementationMissing in readiness.blockers)
        assertFalse(readiness.productionPersistenceEnabled)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertTrue(selection.selectedProviderIsDisabled)
        assertFalse(selection.productionProviderSelectable)
    }

    private fun SkaldVaultV1ManifestRecordEntry.candidate(
        counter: Long,
        recordTypeId: String = this.recordTypeId,
        tombstone: Boolean = this.tombstone,
    ): SkaldVaultV1CandidateRecordDescriptor =
        SkaldVaultV1CandidateRecordDescriptor(
            recordId = recordId,
            recordTypeId = recordTypeId,
            recordVersionCounter = counter,
            recordReference = recordReference,
            tombstone = tombstone,
        )

    private fun assertDecision(
        manifest: SkaldVaultV1Manifest,
        candidate: SkaldVaultV1CandidateRecordDescriptor,
        expected: SkaldVaultV1StaleRecordDecisionKind,
    ) {
        val decision = SkaldVaultV1ManifestFormat.decideRecordState(manifest, candidate)

        assertEquals(expected, decision.kind)
        assertEquals(expected.trustedCurrent, decision.trustedCurrent)
        assertEquals(expected.pendingManifestUpdate, decision.pendingManifestUpdate)
        assertEquals(expected.acceptedForRecordUse, decision.acceptedForRecordUse)
        assertFalse(decision.fullRollbackProtectionClaimed)
        assertFalse(decision.safeMessage.contains(candidate.recordReference ?: "not-present"))
    }

    private fun acceptedBytes(manifest: SkaldVaultV1Manifest): ByteArray =
        when (val result = SkaldVaultV1ManifestFormat.serialize(manifest)) {
            is SkaldVaultV1ManifestResult.Accepted -> result.value
            is SkaldVaultV1ManifestResult.Rejected -> error(result.safeMessage)
        }

    private fun acceptedManifest(
        result: SkaldVaultV1ManifestResult<SkaldVaultV1Manifest>,
    ): SkaldVaultV1Manifest =
        when (result) {
            is SkaldVaultV1ManifestResult.Accepted -> result.value
            is SkaldVaultV1ManifestResult.Rejected -> error(result.safeMessage)
        }

    private fun assertRejected(
        result: SkaldVaultV1ManifestResult<*>,
        reason: SkaldVaultV1ManifestRejectionReason,
    ) {
        assertTrue(result is SkaldVaultV1ManifestResult.Rejected)
        assertEquals(reason, result.reason)
        assertFalse(result.safeMessage.contains("in-memory-manifest-record"))
    }

    private fun assertManifestEquals(expected: SkaldVaultV1Manifest, actual: SkaldVaultV1Manifest) {
        assertEquals(expected.manifestMagic, actual.manifestMagic)
        assertEquals(expected.manifestPolicyId, actual.manifestPolicyId)
        assertEquals(expected.manifestPolicyVersion, actual.manifestPolicyVersion)
        assertContentEquals(expected.vaultId, actual.vaultId)
        assertEquals(expected.providerSuiteId, actual.providerSuiteId)
        assertContentEquals(expected.headerCommitmentContext, actual.headerCommitmentContext)
        assertEquals(expected.storageNamespace, actual.storageNamespace)
        assertEquals(expected.recordNamespace, actual.recordNamespace)
        assertEquals(expected.manifestSequence, actual.manifestSequence)
        assertContentEquals(expected.crashRecoveryMetadata, actual.crashRecoveryMetadata)
        assertEquals(expected.featureFlags, actual.featureFlags)
        assertRecordsEqual(expected.records, actual.records)
    }

    private fun assertRecordsEqual(
        expected: List<SkaldVaultV1ManifestRecordEntry>,
        actual: List<SkaldVaultV1ManifestRecordEntry>,
    ) {
        assertEquals(expected.size, actual.size)
        expected.zip(actual).forEach { (left, right) ->
            assertContentEquals(left.recordId, right.recordId)
            assertEquals(left.recordTypeId, right.recordTypeId)
            assertEquals(left.latestRecordVersionCounter, right.latestRecordVersionCounter)
            assertEquals(left.recordReference, right.recordReference)
            assertEquals(left.tombstone, right.tombstone)
        }
    }

    private fun ByteArray.replaceFieldId(fieldId: Int, replacementFieldId: Int): ByteArray {
        val field = locateTopLevelField(fieldId)
        return copyOf().also {
            it[field.fieldStart] = ((replacementFieldId ushr 8) and 0xff).toByte()
            it[field.fieldStart + 1] = (replacementFieldId and 0xff).toByte()
        }
    }

    private fun ByteArray.duplicateField(fieldId: Int): ByteArray {
        val field = locateTopLevelField(fieldId)
        val duplicate = copyOfRange(field.fieldStart, field.fieldEnd)
        return copyOfRange(0, field.fieldEnd) + duplicate + copyOfRange(field.fieldEnd, size)
    }

    private fun ByteArray.replaceLengthPrefixOnly(fieldId: Int, replacementLength: Int): ByteArray {
        val field = locateTopLevelField(fieldId)
        return copyOf().also {
            it[field.valueLengthOffset] = ((replacementLength ushr 8) and 0xff).toByte()
            it[field.valueLengthOffset + 1] = (replacementLength and 0xff).toByte()
        }
    }

    private fun ByteArray.replaceLengthPrefixedField(fieldId: Int, replacement: ByteArray): ByteArray {
        val field = locateTopLevelField(fieldId)
        val prefix = byteArrayOf(
            ((fieldId ushr 8) and 0xff).toByte(),
            (fieldId and 0xff).toByte(),
            ((replacement.size ushr 8) and 0xff).toByte(),
            (replacement.size and 0xff).toByte(),
        )
        return copyOfRange(0, field.fieldStart) + prefix + replacement + copyOfRange(field.fieldEnd, size)
    }

    private fun ByteArray.replaceU16Field(fieldId: Int, replacement: Int): ByteArray {
        val field = locateTopLevelField(fieldId)
        return copyOf().also {
            it[field.valueStart] = ((replacement ushr 8) and 0xff).toByte()
            it[field.valueStart + 1] = (replacement and 0xff).toByte()
        }
    }

    private fun ByteArray.replaceManifestRecordBytesField(
        recordIndex: Int,
        fieldId: Int,
        replacement: ByteArray,
    ): ByteArray =
        replaceRecordsSection { section ->
            section.replaceManifestRecordEntry(recordIndex) { entry ->
                entry.replaceNestedLengthPrefixedField(fieldId, replacement, RECORD_ENTRY_FIELD_KINDS)
            }
        }

    private fun ByteArray.replaceManifestRecordU16Field(recordIndex: Int, fieldId: Int, replacement: Int): ByteArray =
        replaceRecordsSection { section ->
            section.replaceManifestRecordEntry(recordIndex) { entry ->
                entry.replaceNestedU16Field(fieldId, replacement, RECORD_ENTRY_FIELD_KINDS)
            }
        }

    private fun ByteArray.replaceManifestRecordU64Field(recordIndex: Int, fieldId: Int, replacement: Long): ByteArray =
        replaceRecordsSection { section ->
            section.replaceManifestRecordEntry(recordIndex) { entry ->
                entry.replaceNestedU64Field(fieldId, replacement, RECORD_ENTRY_FIELD_KINDS)
            }
        }

    private fun ByteArray.replaceRecordsSection(transform: (ByteArray) -> ByteArray): ByteArray {
        val section = locateTopLevelField(12)
        val transformed = transform(copyOfRange(section.valueStart, section.fieldEnd))
        val prefix = byteArrayOf(
            0x00,
            0x0c,
            ((transformed.size ushr 8) and 0xff).toByte(),
            (transformed.size and 0xff).toByte(),
        )
        return copyOfRange(0, section.fieldStart) + prefix + transformed + copyOfRange(section.fieldEnd, size)
    }

    private fun ByteArray.replaceManifestRecordEntry(
        recordIndex: Int,
        transform: (ByteArray) -> ByteArray,
    ): ByteArray {
        var offset = 2
        repeat(recordIndex) {
            val length = readU16(offset)
            offset += 2 + length
        }
        val length = readU16(offset)
        val valueStart = offset + 2
        val valueEnd = valueStart + length
        val transformed = transform(copyOfRange(valueStart, valueEnd))
        val prefix = byteArrayOf(
            ((transformed.size ushr 8) and 0xff).toByte(),
            (transformed.size and 0xff).toByte(),
        )
        return copyOfRange(0, offset) + prefix + transformed + copyOfRange(valueEnd, size)
    }

    private fun ByteArray.replaceNestedLengthPrefixedField(
        fieldId: Int,
        replacement: ByteArray,
        fieldKinds: Map<Int, FieldKind>,
    ): ByteArray {
        val field = locateNestedField(fieldId, fieldKinds)
        val prefix = byteArrayOf(
            ((fieldId ushr 8) and 0xff).toByte(),
            (fieldId and 0xff).toByte(),
            ((replacement.size ushr 8) and 0xff).toByte(),
            (replacement.size and 0xff).toByte(),
        )
        return copyOfRange(0, field.fieldStart) + prefix + replacement + copyOfRange(field.fieldEnd, size)
    }

    private fun ByteArray.replaceNestedU16Field(
        fieldId: Int,
        replacement: Int,
        fieldKinds: Map<Int, FieldKind>,
    ): ByteArray {
        val field = locateNestedField(fieldId, fieldKinds)
        return copyOf().also {
            it[field.valueStart] = ((replacement ushr 8) and 0xff).toByte()
            it[field.valueStart + 1] = (replacement and 0xff).toByte()
        }
    }

    private fun ByteArray.replaceNestedU64Field(
        fieldId: Int,
        replacement: Long,
        fieldKinds: Map<Int, FieldKind>,
    ): ByteArray {
        val field = locateNestedField(fieldId, fieldKinds)
        return copyOf().also {
            for ((index, shift) in (56 downTo 0 step 8).withIndex()) {
                it[field.valueStart + index] = ((replacement ushr shift) and 0xff).toByte()
            }
        }
    }

    private fun ByteArray.locateTopLevelField(fieldId: Int): LocatedField =
        locateNestedField(fieldId, TOP_LEVEL_FIELD_KINDS)

    private fun ByteArray.locateNestedField(
        fieldId: Int,
        fieldKinds: Map<Int, FieldKind>,
    ): LocatedField {
        var offset = 0
        while (offset < size) {
            val actualFieldId = readU16(offset)
            val kind = fieldKinds.getValue(actualFieldId)
            val valueLengthOffset: Int
            val valueStart: Int
            val fieldEnd: Int
            when (kind) {
                FieldKind.LengthPrefixed -> {
                    valueLengthOffset = offset + 2
                    val length = readU16(valueLengthOffset)
                    valueStart = offset + 4
                    fieldEnd = valueStart + length
                }
                FieldKind.U16 -> {
                    valueLengthOffset = -1
                    valueStart = offset + 2
                    fieldEnd = valueStart + 2
                }
                FieldKind.U32 -> {
                    valueLengthOffset = -1
                    valueStart = offset + 2
                    fieldEnd = valueStart + 4
                }
                FieldKind.U64 -> {
                    valueLengthOffset = -1
                    valueStart = offset + 2
                    fieldEnd = valueStart + 8
                }
            }
            if (actualFieldId == fieldId) {
                return LocatedField(
                    fieldStart = offset,
                    valueLengthOffset = valueLengthOffset,
                    valueStart = valueStart,
                    fieldEnd = fieldEnd,
                )
            }
            offset = fieldEnd
        }
        error("Field $fieldId not found")
    }

    private fun ByteArray.readU16(offset: Int): Int =
        ((this[offset].toInt() and 0xff) shl 8) or (this[offset + 1].toInt() and 0xff)

    private fun ByteArray.toHex(): String =
        joinToString(separator = "") { byte -> byte.toUByte().toString(16).padStart(2, '0') }

    private enum class FieldKind {
        LengthPrefixed,
        U16,
        U32,
        U64,
    }

    private data class LocatedField(
        val fieldStart: Int,
        val valueLengthOffset: Int,
        val valueStart: Int,
        val fieldEnd: Int,
    )

    private companion object {
        const val UINT16_MAX_FOR_TEST = 0xffff
        const val EXPECTED_MANIFEST_HEX =
            "00010017534b414c442d5641554c542d56312d4d414e494645535400020023736b616c642d7661756c742d76312d6d" +
                "616e69666573742d636f6e74726163742d76310003000100040010202122232425262728292a2b2c2d2e2f0005004b" +
                "736b616c642d7661756c742d76312d626f756e6379636173746c652d6172676f6e3269642d74696e6b2d786368616368" +
                "613230706f6c79313330352d6f732d73656375726572616e646f6d000600201d09a657d8929444c1e955410b2dcb3b" +
                "fc0df2d19b128154e17a5fbd751237d50007001c736b616c642d7661756c742f76312f6c6f63616c2d7265636f726473" +
                "00080016736b616c642d7661756c742f76312f7265636f72647300090000000000000004000a0035736b616c642d7661" +
                "756c742d76312d6d616e69666573742d7061727365722d63726173682d6d657461646174612d66697874757265000b" +
                "00000000000c00b60002005a00010010404142434445464748494a4b4c4d4e4f0002001273656e7369746976652d6d" +
                "65746164617461000300000000000000070004001e696e2d6d656d6f72792d6d616e69666573742d7265636f72642d30" +
                "30303100050000005600010010505152535455565758595a5b5c5d5e5f0002000e7365637265742d7061796c6f616400" +
                "0300000000000000030004001e696e2d6d656d6f72792d6d616e69666573742d7265636f72642d3030303200050001"
        val TOP_LEVEL_FIELD_KINDS: Map<Int, FieldKind> = mapOf(
            1 to FieldKind.LengthPrefixed,
            2 to FieldKind.LengthPrefixed,
            3 to FieldKind.U16,
            4 to FieldKind.LengthPrefixed,
            5 to FieldKind.LengthPrefixed,
            6 to FieldKind.LengthPrefixed,
            7 to FieldKind.LengthPrefixed,
            8 to FieldKind.LengthPrefixed,
            9 to FieldKind.U64,
            10 to FieldKind.LengthPrefixed,
            11 to FieldKind.U32,
            12 to FieldKind.LengthPrefixed,
        )
        val RECORD_ENTRY_FIELD_KINDS: Map<Int, FieldKind> = mapOf(
            1 to FieldKind.LengthPrefixed,
            2 to FieldKind.LengthPrefixed,
            3 to FieldKind.U64,
            4 to FieldKind.LengthPrefixed,
            5 to FieldKind.U16,
        )
    }
}
