package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.EncryptedVaultBlockingIssue
import com.libertasprimordium.skald.security.EncryptedVaultReadinessPolicy
import com.libertasprimordium.skald.security.EncryptedVaultRequirement
import com.libertasprimordium.skald.security.EncryptedVaultRequirementStatus
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceContract
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidenceState
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceGate
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceBlocker
import com.libertasprimordium.skald.security.ProductionProviderConstructionContractStatus
import com.libertasprimordium.skald.security.SkaldVaultV1CanonicalHeader
import com.libertasprimordium.skald.security.SkaldVaultV1Container
import com.libertasprimordium.skald.security.SkaldVaultV1ContainerFormat
import com.libertasprimordium.skald.security.SkaldVaultV1ContainerManifestRecordState
import com.libertasprimordium.skald.security.SkaldVaultV1ContainerManifestSection
import com.libertasprimordium.skald.security.SkaldVaultV1ContainerRecordEntry
import com.libertasprimordium.skald.security.SkaldVaultV1ContainerRejectionReason
import com.libertasprimordium.skald.security.SkaldVaultV1ContainerResult
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class VaultContainerParserWriterTest {
    @Test
    fun fixedNonSecretContainerFixtureSerializesDeterministically() {
        val serialized = acceptedBytes(SkaldVaultV1ContainerFormat.vectorFixtureContainer())

        assertEquals(EXPECTED_CONTAINER_HEX, serialized.toHex())
        assertEquals(0, serialized.size % 1)
    }

    @Test
    fun parseSerializedFixtureReturnsLogicalFixture() {
        val fixture = SkaldVaultV1ContainerFormat.vectorFixtureContainer()
        val parsed = acceptedContainer(SkaldVaultV1ContainerFormat.parse(acceptedBytes(fixture)))

        assertContainerEquals(fixture, parsed)
    }

    @Test
    fun parserRejectsBadMagicUnsupportedVersionSuiteAndPolicies() {
        val serialized = acceptedBytes(SkaldVaultV1ContainerFormat.vectorFixtureContainer())

        assertRejected(
            SkaldVaultV1ContainerFormat.parse(
                serialized.replaceLengthPrefixedField(1, "WRONG-VAULT".encodeToByteArray()),
            ),
            SkaldVaultV1ContainerRejectionReason.UnsupportedMagic,
        )
        assertRejected(
            SkaldVaultV1ContainerFormat.parse(serialized.replaceU16Field(2, 2)),
            SkaldVaultV1ContainerRejectionReason.UnsupportedVersion,
        )
        assertRejected(
            SkaldVaultV1ContainerFormat.parse(
                serialized.replaceLengthPrefixedField(7, "unsupported-suite".encodeToByteArray()),
            ),
            SkaldVaultV1ContainerRejectionReason.UnsupportedSuiteId,
        )
        assertRejected(
            SkaldVaultV1ContainerFormat.parse(
                serialized.replaceLengthPrefixedField(17, "unsupported-key-expansion".encodeToByteArray()),
            ),
            SkaldVaultV1ContainerRejectionReason.UnsupportedPolicyId,
        )
        assertRejected(
            SkaldVaultV1ContainerFormat.parse(
                serialized.replaceLengthPrefixedField(25, "unsupported-manifest-policy".encodeToByteArray()),
            ),
            SkaldVaultV1ContainerRejectionReason.UnsupportedPolicyId,
        )
    }

    @Test
    fun parserRejectsMalformedKdfSaltVaultIdAndCommitmentLengths() {
        val serialized = acceptedBytes(SkaldVaultV1ContainerFormat.vectorFixtureContainer())

        assertRejected(
            SkaldVaultV1ContainerFormat.parse(serialized.replaceU32Field(10, 1)),
            SkaldVaultV1ContainerRejectionReason.UnsupportedKdfParameters,
        )
        assertRejected(
            SkaldVaultV1ContainerFormat.parse(serialized.replaceLengthPrefixedField(13, ByteArray(15))),
            SkaldVaultV1ContainerRejectionReason.MalformedLength,
        )
        assertRejected(
            SkaldVaultV1ContainerFormat.parse(serialized.replaceLengthPrefixedField(15, ByteArray(15))),
            SkaldVaultV1ContainerRejectionReason.MalformedLength,
        )
        assertRejected(
            SkaldVaultV1ContainerFormat.parse(serialized.replaceLengthPrefixedField(6, ByteArray(31))),
            SkaldVaultV1ContainerRejectionReason.MalformedLength,
        )
    }

    @Test
    fun parserRejectsTruncatedMalformedLengthTrailingUnknownAndDuplicateFields() {
        val serialized = acceptedBytes(SkaldVaultV1ContainerFormat.vectorFixtureContainer())

        assertRejected(
            SkaldVaultV1ContainerFormat.parse(serialized.copyOf(serialized.size - 1)),
            SkaldVaultV1ContainerRejectionReason.TruncatedInput,
        )
        assertRejected(
            SkaldVaultV1ContainerFormat.parse(serialized.replaceLengthPrefixOnly(6, UINT16_MAX_FOR_TEST)),
            SkaldVaultV1ContainerRejectionReason.TruncatedInput,
        )
        assertRejected(
            SkaldVaultV1ContainerFormat.parse(serialized + 0x00.toByte()),
            SkaldVaultV1ContainerRejectionReason.TrailingBytes,
        )
        assertRejected(
            SkaldVaultV1ContainerFormat.parse(serialized.replaceFieldId(3, 99)),
            SkaldVaultV1ContainerRejectionReason.UnknownField,
        )
        assertRejected(
            SkaldVaultV1ContainerFormat.parse(serialized.duplicateField(3)),
            SkaldVaultV1ContainerRejectionReason.DuplicatedField,
        )
    }

    @Test
    fun parserRejectsMalformedRecordAndManifestSections() {
        val unsupportedRecordType = SkaldVaultV1ContainerFormat.vectorFixtureContainer().copy(
            encryptedRecordEntries = listOf(
                SkaldVaultV1ContainerFormat.vectorFixtureContainer().encryptedRecordEntries.single()
                    .copy(recordTypeId = "unsupported-record-type"),
            ),
        )
        assertRejected(
            SkaldVaultV1ContainerFormat.serialize(unsupportedRecordType),
            SkaldVaultV1ContainerRejectionReason.UnsupportedRecordType,
        )

        val malformedManifest = SkaldVaultV1ContainerFormat.vectorFixtureContainer().copy(
            manifestSection = SkaldVaultV1ContainerFormat.vectorFixtureContainer().manifestSection.copy(
                recordStates = listOf(
                    SkaldVaultV1ContainerFormat.vectorFixtureContainer().manifestSection.recordStates.single()
                        .copy(latestRecordVersionCounter = 99),
                ),
            ),
        )
        assertRejected(
            SkaldVaultV1ContainerFormat.serialize(malformedManifest),
            SkaldVaultV1ContainerRejectionReason.MalformedManifestSection,
        )
    }

    @Test
    fun serializerRejectsUnsupportedModelValuesBeforeBytesAreReturned() {
        val fixture = SkaldVaultV1ContainerFormat.vectorFixtureContainer()

        assertRejected(
            SkaldVaultV1ContainerFormat.serialize(
                fixture.copy(
                    canonicalHeader = fixture.canonicalHeader.copy(providerSuiteId = "unsupported-suite"),
                ),
            ),
            SkaldVaultV1ContainerRejectionReason.UnsupportedSuiteId,
        )
        assertRejected(
            SkaldVaultV1ContainerFormat.serialize(
                fixture.copy(containerPolicyId = "unsupported-container-policy"),
            ),
            SkaldVaultV1ContainerRejectionReason.UnsupportedPolicyId,
        )
        assertRejected(
            SkaldVaultV1ContainerFormat.serialize(
                fixture.copy(headerCommitmentTag = ByteArray(31)),
            ),
            SkaldVaultV1ContainerRejectionReason.MalformedLength,
        )
        assertRejected(
            SkaldVaultV1ContainerFormat.serialize(
                fixture.copy(
                    encryptedRecordEntries = listOf(
                        fixture.encryptedRecordEntries.single().copy(recordReference = "x".repeat(129)),
                    ),
                ),
            ),
            SkaldVaultV1ContainerRejectionReason.MalformedRecordReference,
        )
    }

    @Test
    fun parserWriterEvidenceIsRepresentedButPersistenceAndSelectionRemainDisabled() {
        val contract = ProductionProviderAcceptanceContract.v1()
        val storageContract = contract.containerManifestStorageContract
        val assessment = contract.assess()
        val readiness = EncryptedVaultReadinessPolicy.disabled()
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            storageContract.vaultContainerContractStatus,
        )
        assertTrue(storageContract.parserImplemented)
        assertTrue(storageContract.writerImplemented)
        assertFalse(storageContract.vaultPersistenceImplemented)
        assertFalse(storageContract.manifestReadWriteImplemented)
        assertFalse(storageContract.storageIndexReadWriteImplemented)
        assertFalse(storageContract.filesystemVaultStorageImplemented)
        assertFalse(storageContract.databaseVaultStorageImplemented)
        assertFalse(storageContract.dataStoreVaultStorageImplemented)
        assertFalse(storageContract.sharedPreferencesVaultStorageImplemented)
        assertFalse(storageContract.secureSecretStorageSuccessPathImplemented)
        assertFalse(storageContract.secureMetadataStorageSuccessPathImplemented)
        assertFalse(storageContract.atomicWriteRecoveryImplementationAdded)
        assertFalse(storageContract.fullLocalDirectoryRollbackResistanceClaimed)
        assertFalse(storageContract.externalOrTrustedMonotonicAntiRollbackAnchorImplemented)

        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            ProductionProviderAcceptanceGate.VaultContainerContractApproved.currentEvidenceState(),
        )
        assertFalse(assessment.productionProviderSelectable)
        assertFalse(assessment.productionPersistenceAllowed)
        assertTrue(ProductionProviderAcceptanceBlocker.ModelOnlyGateEvidence in assessment.blockers)
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[EncryptedVaultRequirement.VaultContainerFormatImplemented],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[EncryptedVaultRequirement.VaultContainerParserImplemented],
        )
        assertTrue(EncryptedVaultBlockingIssue.VaultContainerPersistenceImplementationMissing in readiness.blockers)
        assertFalse(readiness.productionPersistenceEnabled)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertTrue(selection.selectedProviderIsDisabled)
        assertFalse(selection.productionProviderSelectable)
    }

    private fun ProductionProviderAcceptanceGate.currentEvidenceState(): ProductionProviderAcceptanceEvidenceState =
        com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidence.currentDesignOnly().stateFor(this)

    private fun acceptedBytes(container: SkaldVaultV1Container): ByteArray =
        when (val result = SkaldVaultV1ContainerFormat.serialize(container)) {
            is SkaldVaultV1ContainerResult.Accepted -> result.value
            is SkaldVaultV1ContainerResult.Rejected -> error(result.safeMessage)
        }

    private fun acceptedContainer(
        result: SkaldVaultV1ContainerResult<SkaldVaultV1Container>,
    ): SkaldVaultV1Container =
        when (result) {
            is SkaldVaultV1ContainerResult.Accepted -> result.value
            is SkaldVaultV1ContainerResult.Rejected -> error(result.safeMessage)
        }

    private fun assertRejected(
        result: SkaldVaultV1ContainerResult<*>,
        reason: SkaldVaultV1ContainerRejectionReason,
    ) {
        assertTrue(result is SkaldVaultV1ContainerResult.Rejected)
        assertEquals(reason, result.reason)
        assertFalse(result.safeMessage.contains("fixed non-secret container ciphertext placeholder"))
    }

    private fun assertContainerEquals(expected: SkaldVaultV1Container, actual: SkaldVaultV1Container) {
        assertEquals(expected.vaultMagic, actual.vaultMagic)
        assertEquals(expected.vaultFormatVersion, actual.vaultFormatVersion)
        assertEquals(expected.containerPolicyId, actual.containerPolicyId)
        assertEquals(expected.containerPolicyVersion, actual.containerPolicyVersion)
        assertHeaderEquals(expected.canonicalHeader, actual.canonicalHeader)
        assertContentEquals(expected.headerCommitmentTag, actual.headerCommitmentTag)
        assertEquals(expected.manifestPolicyId, actual.manifestPolicyId)
        assertEquals(expected.manifestPolicyVersion, actual.manifestPolicyVersion)
        assertEquals(expected.storagePolicyId, actual.storagePolicyId)
        assertEquals(expected.storagePolicyVersion, actual.storagePolicyVersion)
        assertRecordEntriesEqual(expected.encryptedRecordEntries, actual.encryptedRecordEntries)
        assertManifestSectionEquals(expected.manifestSection, actual.manifestSection)
        assertContentEquals(expected.preUnlockIntegrityMetadata, actual.preUnlockIntegrityMetadata)
    }

    private fun assertHeaderEquals(expected: SkaldVaultV1CanonicalHeader, actual: SkaldVaultV1CanonicalHeader) {
        assertEquals(expected.vaultMagic, actual.vaultMagic)
        assertEquals(expected.vaultFormatVersion, actual.vaultFormatVersion)
        assertEquals(expected.providerSuiteId, actual.providerSuiteId)
        assertEquals(expected.kdfAlgorithmId, actual.kdfAlgorithmId)
        assertEquals(expected.kdfVersion, actual.kdfVersion)
        assertEquals(expected.kdfMemoryKiB, actual.kdfMemoryKiB)
        assertEquals(expected.kdfTimeCost, actual.kdfTimeCost)
        assertEquals(expected.kdfParallelism, actual.kdfParallelism)
        assertContentEquals(expected.salt, actual.salt)
        assertEquals(expected.derivedRootMaterialBytes, actual.derivedRootMaterialBytes)
        assertContentEquals(expected.vaultId, actual.vaultId)
        assertEquals(expected.passphraseEncodingPolicyId, actual.passphraseEncodingPolicyId)
        assertEquals(expected.keyExpansionPolicyId, actual.keyExpansionPolicyId)
        assertEquals(expected.keySeparationPolicyId, actual.keySeparationPolicyId)
        assertEquals(expected.headerCommitmentPrimitivePolicyId, actual.headerCommitmentPrimitivePolicyId)
        assertEquals(expected.headerCommitmentPolicyId, actual.headerCommitmentPolicyId)
        assertEquals(expected.aadPolicyId, actual.aadPolicyId)
        assertEquals(expected.aadPolicyVersion, actual.aadPolicyVersion)
        assertEquals(expected.recordFormatPolicyId, actual.recordFormatPolicyId)
        assertEquals(expected.recordFormatPolicyVersion, actual.recordFormatPolicyVersion)
        assertEquals(expected.featureFlags, actual.featureFlags)
        assertContentEquals(expected.integrityCriticalHeaderMetadata, actual.integrityCriticalHeaderMetadata)
    }

    private fun assertRecordEntriesEqual(
        expected: List<SkaldVaultV1ContainerRecordEntry>,
        actual: List<SkaldVaultV1ContainerRecordEntry>,
    ) {
        assertEquals(expected.size, actual.size)
        expected.zip(actual).forEach { (left, right) ->
            assertEquals(left.recordTypeId, right.recordTypeId)
            assertContentEquals(left.recordId, right.recordId)
            assertEquals(left.recordVersionCounter, right.recordVersionCounter)
            assertContentEquals(left.ciphertext, right.ciphertext)
            assertContentEquals(left.integrityCriticalRecordMetadata, right.integrityCriticalRecordMetadata)
            assertEquals(left.recordReference, right.recordReference)
        }
    }

    private fun assertManifestSectionEquals(
        expected: SkaldVaultV1ContainerManifestSection,
        actual: SkaldVaultV1ContainerManifestSection,
    ) {
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
        assertManifestStatesEqual(expected.recordStates, actual.recordStates)
    }

    private fun assertManifestStatesEqual(
        expected: List<SkaldVaultV1ContainerManifestRecordState>,
        actual: List<SkaldVaultV1ContainerManifestRecordState>,
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

    private fun ByteArray.replaceU32Field(fieldId: Int, replacement: Int): ByteArray {
        val field = locateTopLevelField(fieldId)
        return copyOf().also {
            it[field.valueStart] = ((replacement ushr 24) and 0xff).toByte()
            it[field.valueStart + 1] = ((replacement ushr 16) and 0xff).toByte()
            it[field.valueStart + 2] = ((replacement ushr 8) and 0xff).toByte()
            it[field.valueStart + 3] = (replacement and 0xff).toByte()
        }
    }

    private fun ByteArray.locateTopLevelField(fieldId: Int): LocatedField {
        var offset = 0
        while (offset < size) {
            val actualFieldId = readU16(offset)
            val kind = TOP_LEVEL_FIELD_KINDS.getValue(actualFieldId)
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
    }

    private data class LocatedField(
        val fieldStart: Int,
        val valueLengthOffset: Int,
        val valueStart: Int,
        val fieldEnd: Int,
    )

    private companion object {
        const val UINT16_MAX_FOR_TEST = 0xffff
        const val EXPECTED_CONTAINER_HEX =
            "0001000e534b414c442d5641554c542d56310002000100030024736b616c642d7661756c742d76312d636f6e7461696e" +
                "65722d636f6e74726163742d763100040001000501fd0001000e534b414c442d5641554c542d5631000200010003004b" +
                "736b616c642d7661756c742d76312d626f756e6379636173746c652d6172676f6e3269642d74696e6b2d786368616368" +
                "613230706f6c79313330352d6f732d73656375726572616e646f6d000400086172676f6e326964000500130006000100" +
                "0000070000000300080000000100090020000102030405060708090a0b0c0d0e0f101112131415161718191a1b1c1d1e" +
                "1f000a0040000b0010202122232425262728292a2b2c2d2e2f000c002d756e69636f64652d6e66632d757466382d6e6f" +
                "2d636f6e74726f6c732d6e6f2d776869746573706163652d7631000d002b736b616c642d7661756c742d76312d686b64" +
                "662d7368613235362d6b65792d657870616e73696f6e2d7631000e0027736b616c642d7661756c742d76312d6b65792d" +
                "73657061726174696f6e2d6c6162656c732d7631000f002f736b616c642d7661756c742d76312d686d61632d73686132" +
                "35362d6865616465722d636f6d6d69746d656e742d763100100023736b616c642d7661756c742d76312d686561646572" +
                "2d636f6d6d69746d656e742d76310011001c736b616c642d7661756c742d76312d7265636f72642d6161642d76310012" +
                "00010013001f736b616c642d7661756c742d76312d7265636f72642d666f726d61742d76310014000100150000000000" +
                "160000000600201d09a657d8929444c1e955410b2dcb3bfc0df2d19b128154e17a5fbd751237d50007004b736b616c64" +
                "2d7661756c742d76312d626f756e6379636173746c652d6172676f6e3269642d74696e6b2d786368616368613230706f" +
                "6c79313330352d6f732d73656375726572616e646f6d000800086172676f6e32696400090013000a00010000000b0000" +
                "0003000c00000001000d0020000102030405060708090a0b0c0d0e0f101112131415161718191a1b1c1d1e1f000e0040" +
                "000f0010202122232425262728292a2b2c2d2e2f0010002d756e69636f64652d6e66632d757466382d6e6f2d636f6e74" +
                "726f6c732d6e6f2d776869746573706163652d76310011002b736b616c642d7661756c742d76312d686b64662d736861" +
                "3235362d6b65792d657870616e73696f6e2d763100120027736b616c642d7661756c742d76312d6b65792d7365706172" +
                "6174696f6e2d6c6162656c732d76310013002f736b616c642d7661756c742d76312d686d61632d7368613235362d6865" +
                "616465722d636f6d6d69746d656e742d763100140023736b616c642d7661756c742d76312d6865616465722d636f6d6d" +
                "69746d656e742d76310015001f736b616c642d7661756c742d76312d7265636f72642d666f726d61742d763100160001" +
                "0017001c736b616c642d7661756c742d76312d7265636f72642d6161642d76310018000100190023736b616c642d7661" +
                "756c742d76312d6d616e69666573742d636f6e74726163742d7631001a0001001b002f736b616c642d7661756c742d76" +
                "312d6c6f63616c2d6d616e69666573742d73746f726167652d706f6c6963792d7631001c0001001d00000000001e0000" +
                "001f00ba000100b60001001273656e7369746976652d6d6574616461746100020010404142434445464748494a4b4c4d" +
                "4e4f00030000000000000007000400316669786564206e6f6e2d73656372657420636f6e7461696e6572206369706865" +
                "727465787420706c616365686f6c64657200050028736b616c642d7661756c742d76312d636f6e7461696e65722d7265" +
                "636f72642d6d657461646174610006001d696e2d6d656d6f72792d666978747572652d7265636f72642d303030310020" +
                "01a400010017534b414c442d5641554c542d56312d4d414e494645535400020023736b616c642d7661756c742d76312d" +
                "6d616e69666573742d636f6e74726163742d76310003000100040010202122232425262728292a2b2c2d2e2f0005004b" +
                "736b616c642d7661756c742d76312d626f756e6379636173746c652d6172676f6e3269642d74696e6b2d786368616368" +
                "613230706f6c79313330352d6f732d73656375726572616e646f6d000600201d09a657d8929444c1e955410b2dcb3bfc" +
                "0df2d19b128154e17a5fbd751237d50007001c736b616c642d7661756c742f76312f6c6f63616c2d7265636f72647300" +
                "080016736b616c642d7661756c742f76312f7265636f72647300090000000000000001000a002e736b616c642d766175" +
                "6c742d76312d6d616e69666573742d63726173682d6d657461646174612d66697874757265000b005d00010059000100" +
                "10404142434445464748494a4b4c4d4e4f0002001273656e7369746976652d6d65746164617461000300000000000000" +
                "070004001d696e2d6d656d6f72792d666978747572652d7265636f72642d3030303100050000"
        val TOP_LEVEL_FIELD_KINDS: Map<Int, FieldKind> = mapOf(
            1 to FieldKind.LengthPrefixed,
            2 to FieldKind.U16,
            3 to FieldKind.LengthPrefixed,
            4 to FieldKind.U16,
            5 to FieldKind.LengthPrefixed,
            6 to FieldKind.LengthPrefixed,
            7 to FieldKind.LengthPrefixed,
            8 to FieldKind.LengthPrefixed,
            9 to FieldKind.U16,
            10 to FieldKind.U32,
            11 to FieldKind.U32,
            12 to FieldKind.U32,
            13 to FieldKind.LengthPrefixed,
            14 to FieldKind.U16,
            15 to FieldKind.LengthPrefixed,
            16 to FieldKind.LengthPrefixed,
            17 to FieldKind.LengthPrefixed,
            18 to FieldKind.LengthPrefixed,
            19 to FieldKind.LengthPrefixed,
            20 to FieldKind.LengthPrefixed,
            21 to FieldKind.LengthPrefixed,
            22 to FieldKind.U16,
            23 to FieldKind.LengthPrefixed,
            24 to FieldKind.U16,
            25 to FieldKind.LengthPrefixed,
            26 to FieldKind.U16,
            27 to FieldKind.LengthPrefixed,
            28 to FieldKind.U16,
            29 to FieldKind.U32,
            30 to FieldKind.LengthPrefixed,
            31 to FieldKind.LengthPrefixed,
            32 to FieldKind.LengthPrefixed,
        )
    }
}
