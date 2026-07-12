package com.libertasprimordium.skald

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.EncryptedVaultWorkingParser
import com.libertasprimordium.skald.security.EncryptedVaultWorkingParserBlocker
import com.libertasprimordium.skald.security.EncryptedVaultWorkingParserRequest
import com.libertasprimordium.skald.security.EncryptedVaultWorkingParserStatus
import com.libertasprimordium.skald.security.EncryptedVaultWorkingParserSyntheticClassification
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionDecision
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VaultCryptoAndroidWorkingParserSyntheticVectorExecutionTest {
    @Test
    fun androidTestSourceSyntheticVectorsExecuteThroughWorkingParserSafely() {
        val cases = listOf(
            AndroidParserCase(
                marker = "skv-min-a",
                classification =
                    EncryptedVaultWorkingParserSyntheticClassification.SyntheticHeaderOnly,
                sectionCount = 1,
            ),
            AndroidParserCase(
                marker = "skv-kdf-b",
                classification =
                    EncryptedVaultWorkingParserSyntheticClassification.SyntheticKdfSection,
                sectionCount = 2,
            ),
            AndroidParserCase(
                marker = "skv-env-c",
                classification =
                    EncryptedVaultWorkingParserSyntheticClassification.SyntheticDirectory,
                sectionCount = 3,
            ),
            AndroidParserCase(
                marker = "skv-one-d",
                classification =
                    EncryptedVaultWorkingParserSyntheticClassification
                        .SyntheticSingleRecordEnvelope,
                sectionCount = 4,
            ),
            AndroidParserCase(
                marker = "skv-many-e",
                classification =
                    EncryptedVaultWorkingParserSyntheticClassification
                        .SyntheticMultiRecordDirectory,
                sectionCount = 5,
            ),
        )

        cases.forEach { case ->
            val material = case.marker.encodeToByteArray()
            val beforeParse = material.copyOf()
            val request = EncryptedVaultWorkingParserRequest.testSourceSyntheticVector(material)
            val result = EncryptedVaultWorkingParser.parse(request)
            val repeated = EncryptedVaultWorkingParser.parse(
                EncryptedVaultWorkingParserRequest.testSourceSyntheticVector(
                    case.marker.encodeToByteArray(),
                ),
            )
            val rendered = listOf(request.toString(), result.toString(), result.diagnostics.toString())
                .joinToString(separator = " ")

            assertTrue(material.contentEquals(beforeParse))
            assertEquals(result, repeated)
            assertEquals(EncryptedVaultWorkingParserStatus.ParsedSyntheticVector, result.status)
            assertTrue(result.accepted)
            assertEquals(case.classification, result.classification)
            assertEquals(case.sectionCount, result.sectionCount)
            assertEquals(material.size, result.consumedByteCount)
            assertEquals(0, result.producedByteCount)
            assertTrue(result.testSourceSyntheticVectorBytesOnly)
            assertFalse(result.inputBytesExposed)
            assertFalse(result.inputBytesCopiedToResult)
            assertFalse(result.sectionBytesExposed)
            assertFalse(result.parsedPayloadByteArraysCreated)
            assertFalse(result.repositoryObjectCreated)
            assertFalse(result.storageStateCreated)
            assertFalse(result.writerObjectCreated)
            assertFalse(result.fileIoUsed)
            assertFalse(result.cryptoAuthenticationExecuted)
            assertTrue(result.diagnostics.safeLabelsOnly)
            assertTrue(result.diagnostics.payloadFree)
            assertTrue(result.diagnostics.rawBytesFree)
            assertFalse(rendered.contains(case.marker))
            assertFalse(rendered.contains(material.decodeToString()))
        }
    }

    @Test
    fun androidWorkingParserFailsClosedForUnsupportedVersionAndTruncatedHeader() {
        val cases = listOf(
            AndroidParserFailure("skv-ver-f", EncryptedVaultWorkingParserBlocker.UnsupportedVersion),
            AndroidParserFailure(
                "skv-crit-g",
                EncryptedVaultWorkingParserBlocker.UnsupportedCriticalFeature,
            ),
            AndroidParserFailure("skv-cut-h", EncryptedVaultWorkingParserBlocker.TruncatedHeader),
            AndroidParserFailure(
                "skv-cut-i",
                EncryptedVaultWorkingParserBlocker.TruncatedRecordEnvelope,
            ),
            AndroidParserFailure(
                marker = "skv-red-j",
                blocker = EncryptedVaultWorkingParserBlocker.RedactedDiagnosticsOnly,
                classification =
                    EncryptedVaultWorkingParserSyntheticClassification.RedactedDiagnosticsOnly,
            ),
            AndroidParserFailure("skv-mig-k", EncryptedVaultWorkingParserBlocker.MigrationRequired),
            AndroidParserFailure(
                "skv-cor-l",
                EncryptedVaultWorkingParserBlocker.CorruptionSuspected,
            ),
        )

        cases.forEach { case ->
            val material = case.marker.encodeToByteArray()
            val result = EncryptedVaultWorkingParser.parse(
                EncryptedVaultWorkingParserRequest.testSourceSyntheticVector(material),
            )
            val rendered = listOf(result.toString(), result.diagnostics.toString())
                .joinToString(separator = " ")

            assertEquals(EncryptedVaultWorkingParserStatus.FailedClosed, result.status)
            assertFalse(result.accepted)
            assertEquals(case.classification, result.classification)
            assertTrue(result.blockers.contains(case.blocker))
            assertEquals(1, result.blockerCount)
            assertEquals(material.size, result.consumedByteCount)
            assertEquals(0, result.producedByteCount)
            assertFalse(result.inputBytesExposed)
            assertFalse(result.inputBytesCopiedToResult)
            assertFalse(result.sectionBytesExposed)
            assertFalse(result.parsedPayloadByteArraysCreated)
            assertFalse(result.repositoryObjectCreated)
            assertFalse(result.storageStateCreated)
            assertFalse(result.writerObjectCreated)
            assertFalse(result.fileIoUsed)
            assertFalse(result.cryptoAuthenticationExecuted)
            assertTrue(result.diagnostics.safeLabelsOnly)
            assertTrue(result.diagnostics.payloadFree)
            assertTrue(result.diagnostics.rawBytesFree)
            assertFalse(rendered.contains(case.marker))
            assertFalse(rendered.contains(material.decodeToString()))
        }
    }

    @Test
    fun androidWorkingParserRejectsRuntimeDerivedNonExactInputs() {
        val valid = "skv-min-a".encodeToByteArray()
        val otherValid = "skv-kdf-b".encodeToByteArray()
        val empty = valid.copyOf(0)
        val unknown = valid.copyOf(2).also { candidate ->
            candidate[0] = (candidate[0].toInt() xor 1).toByte()
        }
        val prefixed = valid.copyOf(valid.size + 1).also { candidate ->
            valid.copyInto(candidate, destinationOffset = 1)
            candidate[0] = valid.last()
        }
        val suffixed = valid.copyOf(valid.size + 1).also { candidate ->
            candidate[candidate.lastIndex] = valid.first()
        }
        val concatenated = valid + otherValid
        val caseAltered = valid.copyOf().also { candidate ->
            candidate[0] = (candidate[0].toInt() xor ASCII_CASE_BIT).toByte()
        }

        val emptyResult = parse(empty)
        val unknownResult = parse(unknown)
        val prefixedResult = parse(prefixed)
        val suffixedResult = parse(suffixed)
        val concatenatedResult = parse(concatenated)
        val caseAlteredResult = parse(caseAltered)

        assertRejected(emptyResult, EncryptedVaultWorkingParserBlocker.EmptyInput)
        listOf(
            unknownResult,
            prefixedResult,
            suffixedResult,
            concatenatedResult,
            caseAlteredResult,
        ).forEach { result ->
            assertRejected(
                result,
                EncryptedVaultWorkingParserBlocker.UnrecognizedSyntheticVector,
            )
        }

        listOf(
            valid,
            otherValid,
            empty,
            unknown,
            prefixed,
            suffixed,
            concatenated,
            caseAltered,
        ).forEach { it.fill(0) }
    }

    @Test
    fun androidWorkingParserSingletonDisplayIsExactlyRedacted() {
        val rendered = EncryptedVaultWorkingParser.toString()

        assertEquals(EXPECTED_PARSER_DISPLAY, rendered)
        assertFalse(rendered.contains('@'))
        assertFalse(rendered.contains("skv-"))
    }

    @Test
    fun androidWorkingParserExecutionDoesNotAuthorizeProductionSurfaces() {
        val evidence = EncryptedVaultWorkingParser.currentParserEvidence()
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertTrue(evidence.workingParserImplementationPresent)
        assertTrue(evidence.commonMainInMemoryParserPresent)
        assertTrue(evidence.syntheticVectorParserExecutionSupported)
        assertFalse(evidence.workingWriterImplementationPresent)
        assertFalse(evidence.productionVectorBytesPresent)
        assertFalse(evidence.productionParserInputBytesPresent)
        assertFalse(evidence.productionWriterOutputBytesPresent)
        assertFalse(evidence.productionVaultBytesPresent)
        assertFalse(evidence.vaultContainerSerializationPresent)
        assertFalse(evidence.vaultFileReadPresent)
        assertFalse(evidence.vaultFileWritePresent)
        assertFalse(evidence.vaultFileDeletePresent)
        assertFalse(evidence.vaultDirectoryCreated)
        assertFalse(evidence.kdfExecutionPresent)
        assertFalse(evidence.aeadExecutionPresent)
        assertFalse(evidence.encryptionExecutionPresent)
        assertFalse(evidence.decryptionExecutionPresent)
        assertFalse(evidence.authenticationExecutionPresent)
        assertFalse(evidence.keyGenerationPresent)
        assertFalse(evidence.nonceGenerationPresent)
        assertFalse(evidence.tinkKeysetCreationPresent)
        assertFalse(evidence.tinkKeysetPersistencePresent)
        assertFalse(evidence.lockSessionImplementationPresent)
        assertFalse(evidence.unlockImplementationPresent)
        assertFalse(evidence.runtimeSessionKeyPresent)
        assertFalse(evidence.secureSecretStorageSuccessPathPresent)
        assertFalse(evidence.secureMetadataStorageSuccessPathPresent)
        assertFalse(evidence.productionObservationPersistencePresent)
        assertFalse(evidence.productionAddressIndexPersistencePresent)
        assertFalse(evidence.productionUtxoPersistencePresent)
        assertFalse(evidence.productionWalletHistoryPersistencePresent)
        assertFalse(evidence.productionSyncPresent)
        assertFalse(evidence.productionBackendClientPresent)
        assertFalse(evidence.productionProviderSelectionEnabled)
        assertFalse(evidence.productionProviderSelectable)
        assertTrue(evidence.productionSelectionStillDisabledProviderOnly)
        assertFalse(evidence.signingBroadcastingPresent)
        assertFalse(evidence.uiActionEnablementPresent)
        assertFalse(evidence.endpointPresent)
        assertFalse(evidence.mainnetPresent)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertEquals(VaultCryptoProviderSelectionDecision.DisabledProviderSelected, selection.decision)
        assertTrue(selection.selectedProviderIsDisabled)
        assertTrue(selection.selectedProvider is DisabledVaultCryptoProvider)
        assertFalse(selection.productionProviderSelectable)
    }

    private data class AndroidParserCase(
        val marker: String,
        val classification: EncryptedVaultWorkingParserSyntheticClassification,
        val sectionCount: Int,
    )

    private data class AndroidParserFailure(
        val marker: String,
        val blocker: EncryptedVaultWorkingParserBlocker,
        val classification: EncryptedVaultWorkingParserSyntheticClassification =
            EncryptedVaultWorkingParserSyntheticClassification.NotClassified,
    )

    private fun parse(material: ByteArray) =
        EncryptedVaultWorkingParser.parse(
            EncryptedVaultWorkingParserRequest.testSourceSyntheticVector(material),
        )

    private fun assertRejected(
        result: com.libertasprimordium.skald.security.EncryptedVaultWorkingParserResult,
        blocker: EncryptedVaultWorkingParserBlocker,
    ) {
        assertEquals(EncryptedVaultWorkingParserStatus.FailedClosed, result.status)
        assertFalse(result.accepted)
        assertEquals(EncryptedVaultWorkingParserSyntheticClassification.NotClassified, result.classification)
        assertEquals(listOf(blocker), result.blockers)
        assertEquals(0, result.sectionCount)
        assertEquals(0, result.producedByteCount)
        assertFalse(result.inputBytesExposed)
        assertFalse(result.inputBytesCopiedToResult)
        assertFalse(result.sectionBytesExposed)
        assertFalse(result.parsedPayloadByteArraysCreated)
        assertFalse(result.repositoryObjectCreated)
        assertFalse(result.storageStateCreated)
        assertFalse(result.writerObjectCreated)
        assertFalse(result.fileIoUsed)
        assertFalse(result.cryptoAuthenticationExecuted)
        assertTrue(result.diagnostics.safeLabelsOnly)
        assertTrue(result.diagnostics.payloadFree)
        assertTrue(result.diagnostics.rawBytesFree)
    }

    private companion object {
        const val ASCII_CASE_BIT = 0x20
        const val EXPECTED_PARSER_DISPLAY =
            "EncryptedVaultWorkingParser(REDACTED, COMMON_MAIN_IN_MEMORY_PARSER, " +
                "SYNTHETIC_ONLY, NO_BYTES_EXPOSED, NO_IO, NO_CRYPTO_AUTH_EXECUTION)"
    }
}
