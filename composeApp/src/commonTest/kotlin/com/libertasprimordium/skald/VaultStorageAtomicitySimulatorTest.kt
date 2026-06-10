package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.EncryptedVaultCapability
import com.libertasprimordium.skald.security.EncryptedVaultReadinessPolicy
import com.libertasprimordium.skald.security.EncryptedVaultRequirement
import com.libertasprimordium.skald.security.EncryptedVaultRequirementStatus
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceBlocker
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidence
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidenceState
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceGate
import com.libertasprimordium.skald.security.ProductionProviderAtomicWritePhase
import com.libertasprimordium.skald.security.ProductionProviderConstructionContractStatus
import com.libertasprimordium.skald.security.SkaldVaultV1CandidateRecordDescriptor
import com.libertasprimordium.skald.security.SkaldVaultV1Container
import com.libertasprimordium.skald.security.SkaldVaultV1ContainerFormat
import com.libertasprimordium.skald.security.SkaldVaultV1ContainerResult
import com.libertasprimordium.skald.security.SkaldVaultV1Manifest
import com.libertasprimordium.skald.security.SkaldVaultV1ManifestFormat
import com.libertasprimordium.skald.security.SkaldVaultV1ManifestRejectionReason
import com.libertasprimordium.skald.security.SkaldVaultV1ManifestResult
import com.libertasprimordium.skald.security.SkaldVaultV1StaleRecordDecisionKind
import com.libertasprimordium.skald.security.VaultCryptoDependencyCandidate
import com.libertasprimordium.skald.security.VaultCryptoDependencyCapability
import com.libertasprimordium.skald.security.VaultCryptoDependencyProbeCatalog
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import com.libertasprimordium.skald.security.commonProductionProviderAcceptanceContract
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class VaultStorageAtomicitySimulatorTest {
    @Test
    fun interruptionAtEachDocumentedPhaseReturnsSafeRecoveryDecision() {
        val previousContainer = fixtureContainerBytes()
        val previousManifest = fixtureManifestBytes()
        val candidateContainer = newerContainerBytes(counter = 8)
        val candidateManifest = newerManifestBytes(counter = 8)
        val stable = SkaldVaultV1InMemoryStorageAtomicitySimulator.stableCommittedState(
            containerBytes = previousContainer,
            manifestBytes = previousManifest,
        )

        val expected = mapOf(
            ProductionProviderAtomicWritePhase.BeforeTempContainerWrite to
                SkaldVaultV1StorageAtomicityRecoveryDecision.UsePreviousCommittedState,
            ProductionProviderAtomicWritePhase.DuringTempContainerWrite to
                SkaldVaultV1StorageAtomicityRecoveryDecision.UsePreviousCommittedState,
            ProductionProviderAtomicWritePhase.AfterTempContainerWriteBeforeValidation to
                SkaldVaultV1StorageAtomicityRecoveryDecision.UsePreviousCommittedState,
            ProductionProviderAtomicWritePhase.AfterTempContainerValidationBeforeTempManifestWrite to
                SkaldVaultV1StorageAtomicityRecoveryDecision.UsePreviousCommittedState,
            ProductionProviderAtomicWritePhase.DuringTempManifestWrite to
                SkaldVaultV1StorageAtomicityRecoveryDecision.UsePreviousCommittedState,
            ProductionProviderAtomicWritePhase.AfterTempManifestWriteBeforeCommit to
                SkaldVaultV1StorageAtomicityRecoveryDecision.UsePreviousCommittedState,
            ProductionProviderAtomicWritePhase.AfterCommittingContainerBeforeManifest to
                SkaldVaultV1StorageAtomicityRecoveryDecision.UsePreviousCommittedState,
            ProductionProviderAtomicWritePhase.AfterCommittingManifestBeforeCleanup to
                SkaldVaultV1StorageAtomicityRecoveryDecision.UseNewCommittedState,
            ProductionProviderAtomicWritePhase.DuringCleanupOldOrTempState to
                SkaldVaultV1StorageAtomicityRecoveryDecision.UseNewCommittedState,
            ProductionProviderAtomicWritePhase.StartupRecovery to
                SkaldVaultV1StorageAtomicityRecoveryDecision.UseNewCommittedState,
        )

        assertEquals(ProductionProviderAtomicWritePhase.entries.toSet(), expected.keys)

        expected.forEach { (phase, decision) ->
            val result = SkaldVaultV1InMemoryStorageAtomicitySimulator.simulateWrite(
                initialState = stable,
                candidateContainerBytes = candidateContainer,
                candidateManifestBytes = candidateManifest,
                interruptAt = phase,
            )

            assertEquals(decision, result.decision, "$phase")
            assertFalse(result.persistenceEnabled)
            assertFalse(result.platformStorageTouched)
            assertFalse(result.fullRollbackProtectionClaimed)
            when (decision) {
                SkaldVaultV1StorageAtomicityRecoveryDecision.UsePreviousCommittedState -> {
                    assertContentEquals(previousContainer, result.selectedContainerBytes, "$phase")
                    assertContentEquals(previousManifest, result.selectedManifestBytes, "$phase")
                    assertFalse(result.newerRecordAcceptedWithoutManifestAuthority, "$phase")
                    if (phase != ProductionProviderAtomicWritePhase.BeforeTempContainerWrite) {
                        assertTrue(
                            result.tempStateQuarantined || result.inconsistentStateQuarantined,
                            "$phase must quarantine incomplete or inconsistent state",
                        )
                    }
                }
                SkaldVaultV1StorageAtomicityRecoveryDecision.UseNewCommittedState -> {
                    assertContentEquals(candidateContainer, result.selectedContainerBytes, "$phase")
                    assertContentEquals(candidateManifest, result.selectedManifestBytes, "$phase")
                    assertFalse(result.newerRecordAcceptedWithoutManifestAuthority, "$phase")
                }
                else -> error("Unexpected phase decision $decision for $phase")
            }
        }
    }

    @Test
    fun completeInMemoryWriteUsesNewCommittedStateWithoutPersistence() {
        val result = SkaldVaultV1InMemoryStorageAtomicitySimulator.simulateWrite(
            initialState = SkaldVaultV1InMemoryStorageAtomicitySimulator.stableCommittedState(
                containerBytes = fixtureContainerBytes(),
                manifestBytes = fixtureManifestBytes(),
            ),
            candidateContainerBytes = newerContainerBytes(counter = 8),
            candidateManifestBytes = newerManifestBytes(counter = 8),
            interruptAt = null,
        )

        assertEquals(SkaldVaultV1StorageAtomicityRecoveryDecision.UseNewCommittedState, result.decision)
        assertFalse(result.persistenceEnabled)
        assertFalse(result.platformStorageTouched)
        assertFalse(result.tempStateQuarantined)
        assertFalse(result.inconsistentStateQuarantined)
    }

    @Test
    fun startupRecoveryRejectsMalformedMissingMismatchStaleAndUnknownStates() {
        val validContainer = fixtureContainerBytes()
        val validManifest = fixtureManifestBytes()

        assertRecoveryDecision(
            state = stateWithoutPrevious(containerBytes = validContainer, manifestBytes = null),
            expected = SkaldVaultV1StorageAtomicityRecoveryDecision.RejectMissingManifest,
        )
        assertRecoveryDecision(
            state = stateWithoutPrevious(containerBytes = validContainer, manifestBytes = validManifest.copyOf(12)),
            expected = SkaldVaultV1StorageAtomicityRecoveryDecision.RejectMalformedManifest,
        )
        assertRecoveryDecision(
            state = stateWithoutPrevious(containerBytes = validContainer.copyOf(12), manifestBytes = validManifest),
            expected = SkaldVaultV1StorageAtomicityRecoveryDecision.RejectMalformedContainer,
        )
        assertRecoveryDecision(
            state = stateWithoutPrevious(containerBytes = validContainer, manifestBytes = manifestWithVaultIdMismatch()),
            expected = SkaldVaultV1StorageAtomicityRecoveryDecision.RejectManifestContainerMismatch,
        )
        assertRecoveryDecision(
            state = stateWithoutPrevious(containerBytes = validContainer, manifestBytes = manifestWithUnsupportedSuite()),
            expected = SkaldVaultV1StorageAtomicityRecoveryDecision.RejectProviderSuiteMismatch,
        )
        assertRecoveryDecision(
            state = stateWithoutPrevious(containerBytes = validContainer, manifestBytes = manifestWithHeaderContextMismatch()),
            expected = SkaldVaultV1StorageAtomicityRecoveryDecision.RejectHeaderCommitmentContextMismatch,
        )
        assertRecoveryDecision(
            state = stateWithoutPrevious(containerBytes = validContainer, manifestBytes = manifestWithUnsupportedStorageNamespace()),
            expected = SkaldVaultV1StorageAtomicityRecoveryDecision.RejectStorageNamespaceMismatch,
        )
        assertRecoveryDecision(
            state = stateWithoutPrevious(containerBytes = validContainer, manifestBytes = manifestWithMissingActiveRecord()),
            expected = SkaldVaultV1StorageAtomicityRecoveryDecision.RejectRecordMissing,
        )
        assertRecoveryDecision(
            state = stateWithoutPrevious(containerBytes = validContainer, manifestBytes = newerManifestBytes(counter = 8)),
            expected = SkaldVaultV1StorageAtomicityRecoveryDecision.RejectStaleRecord,
        )
        assertRecoveryDecision(
            state = stateWithoutPrevious(containerBytes = validContainer, manifestBytes = manifestWithConflictingCounters()),
            expected = SkaldVaultV1StorageAtomicityRecoveryDecision.RejectConflictingCounter,
        )
        assertRecoveryDecision(
            state = SkaldVaultV1SimulatedStorageState(unknownStateMarker = true),
            expected = SkaldVaultV1StorageAtomicityRecoveryDecision.UnknownStorageState,
        )
    }

    @Test
    fun simulatorEvidenceIsRepresentedButDoesNotEnablePersistenceOrSelection() {
        val contract = commonProductionProviderAcceptanceContract()
        val storageContract = contract.containerManifestStorageContract
        val evidence = ProductionProviderAcceptanceEvidence.currentDesignOnly()
        val assessment = contract.assess(evidence)
        val readiness = EncryptedVaultReadinessPolicy.disabled()
        val dependency = VaultCryptoDependencyProbeCatalog.currentSpikeResults()
            .single { it.candidate == VaultCryptoDependencyCandidate.TinkBouncyCastleSplit }
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(
            "skald-vault-v1-in-memory-storage-atomicity-simulator-policy-v1",
            storageContract.storageAtomicitySimulatorPolicyId,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            storageContract.storageAtomicitySimulatorStatus,
        )
        assertTrue(storageContract.inMemoryAtomicityCrashSimulatorImplemented)
        assertTrue(storageContract.inMemoryAtomicityCrashSimulatorInterruptionTestsExecuted)
        assertTrue(storageContract.inMemoryAtomicityCrashSimulatorRecoveryDecisionsTested)
        assertFalse(storageContract.platformStorageImplementationAdded)
        assertFalse(storageContract.vaultPersistenceImplemented)
        assertFalse(storageContract.atomicWriteRecoveryImplementationAdded)
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            evidence.stateFor(ProductionProviderAcceptanceGate.StorageAtomicityCrashSimulatorExecuted),
        )
        assertFalse(assessment.productionProviderSelectable)
        assertFalse(assessment.productionPersistenceAllowed)
        assertContains(assessment.blockers, ProductionProviderAcceptanceBlocker.ModelOnlyGateEvidence)

        listOf(
            ProductionProviderAcceptanceEvidenceState.Missing to ProductionProviderAcceptanceBlocker.MissingGateEvidence,
            ProductionProviderAcceptanceEvidenceState.Unknown to ProductionProviderAcceptanceBlocker.UnknownGateEvidence,
            ProductionProviderAcceptanceEvidenceState.Failed to ProductionProviderAcceptanceBlocker.FailedGateEvidence,
            ProductionProviderAcceptanceEvidenceState.Unsupported to
                ProductionProviderAcceptanceBlocker.UnsupportedGateEvidence,
        ).forEach { (state, blocker) ->
            val blocked = contract.assess(
                ProductionProviderAcceptanceEvidence(
                    gateStates = ProductionProviderAcceptanceGate.entries.associateWith {
                        ProductionProviderAcceptanceEvidenceState.Satisfied
                    } + mapOf(
                        ProductionProviderAcceptanceGate.StorageAtomicityCrashSimulatorExecuted to state,
                    ),
                ),
            )

            assertContains(blocked.blockers, blocker)
            assertFalse(blocked.productionProviderSelectable)
            assertFalse(blocked.productionPersistenceAllowed)
        }

        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.StorageAtomicityCrashSimulatorImplementedAndTested
            ],
        )
        assertContains(readiness.capabilities, EncryptedVaultCapability.InMemoryStorageAtomicityCrashSimulator)
        assertFalse(readiness.productionPersistenceEnabled)
        assertContains(
            dependency.capabilities,
            VaultCryptoDependencyCapability.InMemoryStorageAtomicityCrashSimulatorTested,
        )
        assertFalse(dependency.storageEnabled)
        assertFalse(dependency.productionPersistenceEnabled)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertTrue(selection.selectedProviderIsDisabled)
        assertFalse(selection.productionProviderSelectable)
    }

    private fun assertRecoveryDecision(
        state: SkaldVaultV1SimulatedStorageState,
        expected: SkaldVaultV1StorageAtomicityRecoveryDecision,
    ) {
        val result = SkaldVaultV1InMemoryStorageAtomicitySimulator.recover(state)

        assertEquals(expected, result.decision)
        assertFalse(result.persistenceEnabled)
        assertFalse(result.platformStorageTouched)
        assertFalse(result.fullRollbackProtectionClaimed)
        assertFalse(result.safeMessage.contains("in-memory-manifest-record"))
    }

    private fun fixtureContainerBytes(): ByteArray =
        acceptedContainerBytes(SkaldVaultV1ContainerFormat.vectorFixtureContainer())

    private fun fixtureManifestBytes(): ByteArray =
        acceptedManifestBytes(SkaldVaultV1ManifestFormat.vectorFixtureManifest())

    private fun newerContainerBytes(counter: Long): ByteArray {
        val fixture = SkaldVaultV1ContainerFormat.vectorFixtureContainer()
        val record = fixture.encryptedRecordEntries.single().copy(recordVersionCounter = counter)
        val manifestState = fixture.manifestSection.recordStates.single().copy(
            latestRecordVersionCounter = counter,
        )
        return acceptedContainerBytes(
            fixture.copy(
                encryptedRecordEntries = listOf(record),
                manifestSection = fixture.manifestSection.copy(
                    manifestSequence = fixture.manifestSection.manifestSequence + 1,
                    recordStates = listOf(manifestState),
                ),
            ),
        )
    }

    private fun newerManifestBytes(counter: Long): ByteArray =
        acceptedManifestBytes(
            SkaldVaultV1ManifestFormat.vectorFixtureManifest().withActiveCounter(counter)
                .copy(manifestSequence = 5),
        )

    private fun manifestWithVaultIdMismatch(): ByteArray =
        acceptedManifestBytes(
            SkaldVaultV1ManifestFormat.vectorFixtureManifest().copy(
                vaultId = (0x30..0x3f).map { it.toByte() }.toByteArray(),
            ),
        )

    private fun manifestWithHeaderContextMismatch(): ByteArray =
        acceptedManifestBytes(
            SkaldVaultV1ManifestFormat.vectorFixtureManifest().copy(
                headerCommitmentContext = ByteArray(32) { 0x55.toByte() },
            ),
        )

    private fun manifestWithMissingActiveRecord(): ByteArray {
        val fixture = SkaldVaultV1ManifestFormat.vectorFixtureManifest()
        val missing = fixture.records.first().copy(
            recordId = (0x70..0x7f).map { it.toByte() }.toByteArray(),
            recordReference = "in-memory-manifest-record-0003",
        )
        return acceptedManifestBytes(fixture.copy(records = fixture.records + missing))
    }

    private fun manifestWithUnsupportedSuite(): ByteArray =
        fixtureManifestBytes().replaceLengthPrefixedField(5, "unsupported-suite".encodeToByteArray())

    private fun manifestWithUnsupportedStorageNamespace(): ByteArray =
        fixtureManifestBytes().replaceLengthPrefixedField(7, "unsupported-storage".encodeToByteArray())

    private fun manifestWithConflictingCounters(): ByteArray =
        fixtureManifestBytes().replaceManifestRecordBytesField(
            recordIndex = 1,
            fieldId = 1,
            replacement = SkaldVaultV1ManifestFormat.activeFixtureRecordId(),
        )

    private fun SkaldVaultV1Manifest.withActiveCounter(counter: Long): SkaldVaultV1Manifest =
        copy(
            records = records.map { record ->
                if (record.recordId.contentEquals(SkaldVaultV1ManifestFormat.activeFixtureRecordId())) {
                    record.copy(latestRecordVersionCounter = counter)
                } else {
                    record
                }
            },
        )

    private fun stateWithoutPrevious(
        containerBytes: ByteArray?,
        manifestBytes: ByteArray?,
    ): SkaldVaultV1SimulatedStorageState =
        SkaldVaultV1SimulatedStorageState(
            committedContainerBytes = containerBytes,
            committedManifestBytes = manifestBytes,
        )

    private fun acceptedContainerBytes(container: SkaldVaultV1Container): ByteArray =
        when (val result = SkaldVaultV1ContainerFormat.serialize(container)) {
            is SkaldVaultV1ContainerResult.Accepted -> result.value
            is SkaldVaultV1ContainerResult.Rejected -> error(result.safeMessage)
        }

    private fun acceptedManifestBytes(manifest: SkaldVaultV1Manifest): ByteArray =
        when (val result = SkaldVaultV1ManifestFormat.serialize(manifest)) {
            is SkaldVaultV1ManifestResult.Accepted -> result.value
            is SkaldVaultV1ManifestResult.Rejected -> error(result.safeMessage)
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

private const val CONTAINER_COMMITTED = "container-committed"
private const val MANIFEST_COMMITTED = "manifest-committed"

private data class SkaldVaultV1SimulatedStorageState(
    val previousCommittedContainerBytes: ByteArray? = null,
    val previousCommittedManifestBytes: ByteArray? = null,
    val committedContainerBytes: ByteArray? = null,
    val committedManifestBytes: ByteArray? = null,
    val temporaryContainerBytes: ByteArray? = null,
    val temporaryManifestBytes: ByteArray? = null,
    val temporaryStorageIndexMetadata: ByteArray? = null,
    val commitMarkers: Set<String> = emptySet(),
    val unknownStateMarker: Boolean = false,
)

private enum class SkaldVaultV1StorageAtomicityRecoveryDecision {
    UsePreviousCommittedState,
    UseNewCommittedState,
    QuarantineTempState,
    QuarantineInconsistentState,
    RequireUserRecoveryAction,
    UnknownStorageState,
    RejectMissingManifest,
    RejectMalformedManifest,
    RejectMalformedContainer,
    RejectManifestContainerMismatch,
    RejectProviderSuiteMismatch,
    RejectHeaderCommitmentContextMismatch,
    RejectStorageNamespaceMismatch,
    RejectRecordMissing,
    RejectStaleRecord,
    RejectConflictingCounter,
}

private data class SkaldVaultV1StorageAtomicitySimulationResult(
    val decision: SkaldVaultV1StorageAtomicityRecoveryDecision,
    val selectedContainerBytes: ByteArray? = null,
    val selectedManifestBytes: ByteArray? = null,
    val tempStateQuarantined: Boolean = false,
    val inconsistentStateQuarantined: Boolean = false,
    val newerRecordAcceptedWithoutManifestAuthority: Boolean = false,
    val persistenceEnabled: Boolean = false,
    val platformStorageTouched: Boolean = false,
    val fullRollbackProtectionClaimed: Boolean = false,
    val safeMessage: String,
)

private object SkaldVaultV1InMemoryStorageAtomicitySimulator {
    fun stableCommittedState(
        containerBytes: ByteArray,
        manifestBytes: ByteArray,
    ): SkaldVaultV1SimulatedStorageState =
        SkaldVaultV1SimulatedStorageState(
            previousCommittedContainerBytes = containerBytes.copyOf(),
            previousCommittedManifestBytes = manifestBytes.copyOf(),
            committedContainerBytes = containerBytes.copyOf(),
            committedManifestBytes = manifestBytes.copyOf(),
        )

    fun simulateWrite(
        initialState: SkaldVaultV1SimulatedStorageState,
        candidateContainerBytes: ByteArray,
        candidateManifestBytes: ByteArray,
        interruptAt: ProductionProviderAtomicWritePhase?,
    ): SkaldVaultV1StorageAtomicitySimulationResult {
        val tempStorageIndex = "in-memory-non-secret-storage-index-fixture".encodeToByteArray()
        val interruptedState = when (interruptAt) {
            null -> initialState.copy(
                previousCommittedContainerBytes = initialState.committedContainerBytes,
                previousCommittedManifestBytes = initialState.committedManifestBytes,
                committedContainerBytes = candidateContainerBytes.copyOf(),
                committedManifestBytes = candidateManifestBytes.copyOf(),
                temporaryContainerBytes = null,
                temporaryManifestBytes = null,
                temporaryStorageIndexMetadata = null,
                commitMarkers = setOf(CONTAINER_COMMITTED, MANIFEST_COMMITTED),
            )
            ProductionProviderAtomicWritePhase.BeforeTempContainerWrite -> initialState
            ProductionProviderAtomicWritePhase.DuringTempContainerWrite -> initialState.copy(
                temporaryContainerBytes = candidateContainerBytes.copyOf(candidateContainerBytes.size / 2),
            )
            ProductionProviderAtomicWritePhase.AfterTempContainerWriteBeforeValidation -> initialState.copy(
                temporaryContainerBytes = candidateContainerBytes.copyOf(),
            )
            ProductionProviderAtomicWritePhase.AfterTempContainerValidationBeforeTempManifestWrite -> initialState.copy(
                temporaryContainerBytes = candidateContainerBytes.copyOf(),
            )
            ProductionProviderAtomicWritePhase.DuringTempManifestWrite -> initialState.copy(
                temporaryContainerBytes = candidateContainerBytes.copyOf(),
                temporaryManifestBytes = candidateManifestBytes.copyOf(candidateManifestBytes.size / 2),
            )
            ProductionProviderAtomicWritePhase.AfterTempManifestWriteBeforeCommit -> initialState.copy(
                temporaryContainerBytes = candidateContainerBytes.copyOf(),
                temporaryManifestBytes = candidateManifestBytes.copyOf(),
                temporaryStorageIndexMetadata = tempStorageIndex,
            )
            ProductionProviderAtomicWritePhase.AfterCommittingContainerBeforeManifest -> initialState.copy(
                previousCommittedContainerBytes = initialState.committedContainerBytes,
                previousCommittedManifestBytes = initialState.committedManifestBytes,
                committedContainerBytes = candidateContainerBytes.copyOf(),
                temporaryContainerBytes = candidateContainerBytes.copyOf(),
                commitMarkers = setOf(CONTAINER_COMMITTED),
            )
            ProductionProviderAtomicWritePhase.AfterCommittingManifestBeforeCleanup -> initialState.copy(
                previousCommittedContainerBytes = initialState.committedContainerBytes,
                previousCommittedManifestBytes = initialState.committedManifestBytes,
                committedContainerBytes = candidateContainerBytes.copyOf(),
                committedManifestBytes = candidateManifestBytes.copyOf(),
                temporaryContainerBytes = candidateContainerBytes.copyOf(),
                temporaryManifestBytes = candidateManifestBytes.copyOf(),
                temporaryStorageIndexMetadata = tempStorageIndex,
                commitMarkers = setOf(CONTAINER_COMMITTED, MANIFEST_COMMITTED),
            )
            ProductionProviderAtomicWritePhase.DuringCleanupOldOrTempState -> initialState.copy(
                previousCommittedContainerBytes = initialState.committedContainerBytes,
                previousCommittedManifestBytes = initialState.committedManifestBytes,
                committedContainerBytes = candidateContainerBytes.copyOf(),
                committedManifestBytes = candidateManifestBytes.copyOf(),
                temporaryContainerBytes = initialState.committedContainerBytes,
                temporaryManifestBytes = initialState.committedManifestBytes,
                temporaryStorageIndexMetadata = tempStorageIndex,
                commitMarkers = setOf(CONTAINER_COMMITTED, MANIFEST_COMMITTED),
            )
            ProductionProviderAtomicWritePhase.StartupRecovery -> initialState.copy(
                previousCommittedContainerBytes = initialState.committedContainerBytes,
                previousCommittedManifestBytes = initialState.committedManifestBytes,
                committedContainerBytes = candidateContainerBytes.copyOf(),
                committedManifestBytes = candidateManifestBytes.copyOf(),
                commitMarkers = setOf(CONTAINER_COMMITTED, MANIFEST_COMMITTED),
            )
        }
        return recover(interruptedState)
    }

    fun recover(
        state: SkaldVaultV1SimulatedStorageState,
    ): SkaldVaultV1StorageAtomicitySimulationResult {
        if (state.unknownStateMarker) {
            return result(
                decision = SkaldVaultV1StorageAtomicityRecoveryDecision.UnknownStorageState,
                safeMessage = "Unknown in-memory simulated storage state is rejected.",
            )
        }

        val committed = validatePair(
            containerBytes = state.committedContainerBytes,
            manifestBytes = state.committedManifestBytes,
        )
        val tempPresent = state.temporaryContainerBytes != null ||
            state.temporaryManifestBytes != null ||
            state.temporaryStorageIndexMetadata != null
        val fullyCommitted = CONTAINER_COMMITTED in state.commitMarkers &&
            MANIFEST_COMMITTED in state.commitMarkers

        if (committed is PairValidation.Valid) {
            if (tempPresent && !fullyCommitted) {
                return usePreviousOrCommitted(
                    state = state,
                    committed = committed,
                    tempStateQuarantined = true,
                    safeMessage = "Uncommitted temporary state is quarantined; committed state remains authoritative.",
                )
            }
            return result(
                decision = if (sameBytes(
                        state.previousCommittedContainerBytes,
                        state.committedContainerBytes,
                    ) && sameBytes(
                        state.previousCommittedManifestBytes,
                        state.committedManifestBytes,
                    )
                ) {
                    SkaldVaultV1StorageAtomicityRecoveryDecision.UsePreviousCommittedState
                } else {
                    SkaldVaultV1StorageAtomicityRecoveryDecision.UseNewCommittedState
                },
                selectedContainerBytes = state.committedContainerBytes,
                selectedManifestBytes = state.committedManifestBytes,
                tempStateQuarantined = tempPresent,
                safeMessage = "Committed in-memory state validates against container, manifest, and stale-record policy.",
            )
        }

        val previous = validatePair(
            containerBytes = state.previousCommittedContainerBytes,
            manifestBytes = state.previousCommittedManifestBytes,
        )
        if (previous is PairValidation.Valid) {
            return result(
                decision = SkaldVaultV1StorageAtomicityRecoveryDecision.UsePreviousCommittedState,
                selectedContainerBytes = state.previousCommittedContainerBytes,
                selectedManifestBytes = state.previousCommittedManifestBytes,
                tempStateQuarantined = tempPresent,
                inconsistentStateQuarantined = true,
                safeMessage = "Invalid newer state is quarantined; previous committed state remains authoritative.",
            )
        }

        return when (committed) {
            is PairValidation.Invalid -> result(
                decision = committed.decision,
                safeMessage = committed.safeMessage,
            )
            is PairValidation.Valid -> error("Valid committed state is returned before this branch.")
        }
    }

    private fun usePreviousOrCommitted(
        state: SkaldVaultV1SimulatedStorageState,
        committed: PairValidation.Valid,
        tempStateQuarantined: Boolean,
        safeMessage: String,
    ): SkaldVaultV1StorageAtomicitySimulationResult {
        val previousValid = validatePair(
            containerBytes = state.previousCommittedContainerBytes,
            manifestBytes = state.previousCommittedManifestBytes,
        ) is PairValidation.Valid
        return if (previousValid) {
            result(
                decision = SkaldVaultV1StorageAtomicityRecoveryDecision.UsePreviousCommittedState,
                selectedContainerBytes = state.previousCommittedContainerBytes,
                selectedManifestBytes = state.previousCommittedManifestBytes,
                tempStateQuarantined = tempStateQuarantined,
                safeMessage = safeMessage,
            )
        } else {
            result(
                decision = SkaldVaultV1StorageAtomicityRecoveryDecision.UsePreviousCommittedState,
                selectedContainerBytes = committed.containerBytes,
                selectedManifestBytes = committed.manifestBytes,
                tempStateQuarantined = tempStateQuarantined,
                safeMessage = safeMessage,
            )
        }
    }

    private fun validatePair(
        containerBytes: ByteArray?,
        manifestBytes: ByteArray?,
    ): PairValidation =
        when {
            containerBytes == null && manifestBytes == null -> PairValidation.Invalid(
                decision = SkaldVaultV1StorageAtomicityRecoveryDecision.UnknownStorageState,
                safeMessage = "No committed container or manifest evidence is present.",
            )
            containerBytes != null && manifestBytes == null -> PairValidation.Invalid(
                decision = SkaldVaultV1StorageAtomicityRecoveryDecision.RejectMissingManifest,
                safeMessage = "Committed container has no manifest authority.",
            )
            containerBytes == null && manifestBytes != null -> PairValidation.Invalid(
                decision = SkaldVaultV1StorageAtomicityRecoveryDecision.RejectMalformedContainer,
                safeMessage = "Committed manifest has no container evidence.",
            )
            else -> validateParsedPair(containerBytes = containerBytes, manifestBytes = manifestBytes)
        }

    private fun validateParsedPair(
        containerBytes: ByteArray?,
        manifestBytes: ByteArray?,
    ): PairValidation {
        val parsedContainer = when (val result = SkaldVaultV1ContainerFormat.parse(containerBytes!!)) {
            is SkaldVaultV1ContainerResult.Accepted -> result.value
            is SkaldVaultV1ContainerResult.Rejected -> return PairValidation.Invalid(
                decision = SkaldVaultV1StorageAtomicityRecoveryDecision.RejectMalformedContainer,
                safeMessage = "Committed container bytes are malformed.",
            )
        }
        val parsedManifest = when (val result = SkaldVaultV1ManifestFormat.parse(manifestBytes!!)) {
            is SkaldVaultV1ManifestResult.Accepted -> result.value
            is SkaldVaultV1ManifestResult.Rejected -> return PairValidation.Invalid(
                decision = mapManifestRejection(result.reason),
                safeMessage = "Committed manifest bytes are malformed or unsupported.",
            )
        }

        if (!parsedManifest.vaultId.contentEquals(parsedContainer.vaultId)) {
            return PairValidation.Invalid(
                decision = SkaldVaultV1StorageAtomicityRecoveryDecision.RejectManifestContainerMismatch,
                safeMessage = "Manifest and container vault ids do not match.",
            )
        }
        if (parsedManifest.providerSuiteId != parsedContainer.providerSuiteId) {
            return PairValidation.Invalid(
                decision = SkaldVaultV1StorageAtomicityRecoveryDecision.RejectProviderSuiteMismatch,
                safeMessage = "Manifest and container provider suites do not match.",
            )
        }
        if (!parsedManifest.headerCommitmentContext.contentEquals(parsedContainer.headerCommitmentTag)) {
            return PairValidation.Invalid(
                decision = SkaldVaultV1StorageAtomicityRecoveryDecision.RejectHeaderCommitmentContextMismatch,
                safeMessage = "Manifest and container header commitment contexts do not match.",
            )
        }
        if (parsedManifest.storageNamespace != parsedContainer.manifestSection.storageNamespace) {
            return PairValidation.Invalid(
                decision = SkaldVaultV1StorageAtomicityRecoveryDecision.RejectStorageNamespaceMismatch,
                safeMessage = "Manifest and container storage namespaces do not match.",
            )
        }

        val recordsById = parsedContainer.encryptedRecordEntries.associateBy { it.recordId.toHexForSimulator() }
        parsedManifest.records
            .filterNot { it.tombstone }
            .forEach { manifestRecord ->
                if (recordsById[manifestRecord.recordId.toHexForSimulator()] == null) {
                    return PairValidation.Invalid(
                        decision = SkaldVaultV1StorageAtomicityRecoveryDecision.RejectRecordMissing,
                        safeMessage = "Manifest references missing active record evidence.",
                    )
                }
            }
        parsedContainer.encryptedRecordEntries.forEach { record ->
            val manifestRecord = parsedManifest.records.singleOrNull {
                it.recordId.contentEquals(record.recordId)
            } ?: return PairValidation.Invalid(
                decision = SkaldVaultV1StorageAtomicityRecoveryDecision.RejectRecordMissing,
                safeMessage = "Record has no manifest authority.",
            )
            val staleDecision = SkaldVaultV1ManifestFormat.decideRecordState(
                manifest = parsedManifest,
                candidate = SkaldVaultV1CandidateRecordDescriptor(
                    recordId = record.recordId,
                    recordTypeId = record.recordTypeId,
                    recordVersionCounter = record.recordVersionCounter,
                    recordReference = record.recordReference,
                    tombstone = manifestRecord.tombstone,
                ),
            )
            if (staleDecision.kind != SkaldVaultV1StaleRecordDecisionKind.CurrentTrusted) {
                return PairValidation.Invalid(
                    decision = when (staleDecision.kind) {
                        SkaldVaultV1StaleRecordDecisionKind.RecordTypeConflictRejected,
                        SkaldVaultV1StaleRecordDecisionKind.TombstoneConflictRejected,
                        -> SkaldVaultV1StorageAtomicityRecoveryDecision.RejectManifestContainerMismatch
                        else -> SkaldVaultV1StorageAtomicityRecoveryDecision.RejectStaleRecord
                    },
                    safeMessage = "Record is not current under local manifest-relative stale-record policy.",
                )
            }
        }

        return PairValidation.Valid(
            containerBytes = containerBytes,
            manifestBytes = manifestBytes,
        )
    }

    private fun mapManifestRejection(
        reason: SkaldVaultV1ManifestRejectionReason,
    ): SkaldVaultV1StorageAtomicityRecoveryDecision =
        when (reason) {
            SkaldVaultV1ManifestRejectionReason.UnsupportedSuiteId ->
                SkaldVaultV1StorageAtomicityRecoveryDecision.RejectProviderSuiteMismatch
            SkaldVaultV1ManifestRejectionReason.UnsupportedStorageNamespace ->
                SkaldVaultV1StorageAtomicityRecoveryDecision.RejectStorageNamespaceMismatch
            SkaldVaultV1ManifestRejectionReason.DuplicateRecordId,
            SkaldVaultV1ManifestRejectionReason.ConflictingLatestCounter,
            -> SkaldVaultV1StorageAtomicityRecoveryDecision.RejectConflictingCounter
            else -> SkaldVaultV1StorageAtomicityRecoveryDecision.RejectMalformedManifest
        }

    private fun result(
        decision: SkaldVaultV1StorageAtomicityRecoveryDecision,
        selectedContainerBytes: ByteArray? = null,
        selectedManifestBytes: ByteArray? = null,
        tempStateQuarantined: Boolean = false,
        inconsistentStateQuarantined: Boolean = false,
        safeMessage: String,
    ): SkaldVaultV1StorageAtomicitySimulationResult =
        SkaldVaultV1StorageAtomicitySimulationResult(
            decision = decision,
            selectedContainerBytes = selectedContainerBytes,
            selectedManifestBytes = selectedManifestBytes,
            tempStateQuarantined = tempStateQuarantined,
            inconsistentStateQuarantined = inconsistentStateQuarantined,
            safeMessage = safeMessage,
        )

    private fun sameBytes(left: ByteArray?, right: ByteArray?): Boolean =
        when {
            left == null && right == null -> true
            left == null || right == null -> false
            else -> left.contentEquals(right)
        }

    private sealed class PairValidation {
        data class Valid(
            val containerBytes: ByteArray,
            val manifestBytes: ByteArray,
        ) : PairValidation()

        data class Invalid(
            val decision: SkaldVaultV1StorageAtomicityRecoveryDecision,
            val safeMessage: String,
        ) : PairValidation()
    }
}

private fun ByteArray.toHexForSimulator(): String =
    joinToString(separator = "") { byte -> byte.toUByte().toString(16).padStart(2, '0') }
