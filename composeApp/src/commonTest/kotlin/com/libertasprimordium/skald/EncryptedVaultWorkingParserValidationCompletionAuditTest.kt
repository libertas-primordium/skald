package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.EncryptedVaultParserWriterSyntheticVectorCatalog
import com.libertasprimordium.skald.security.EncryptedVaultWorkingParserBlocker
import com.libertasprimordium.skald.security.EncryptedVaultWorkingParserSyntheticClassification
import com.libertasprimordium.skald.security.EncryptedVaultWorkingParserValidationCompletionAudit
import com.libertasprimordium.skald.security.EncryptedVaultWorkingParserValidationCompletionAuditKind
import com.libertasprimordium.skald.security.EncryptedVaultWorkingParserValidationCompletionAuditSourceSet
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class EncryptedVaultWorkingParserValidationCompletionAuditTest {
    private fun report() =
        EncryptedVaultWorkingParserValidationCompletionAudit.currentReport()

    @Test
    fun auditIsCommonTestValidationOnlyAndReusesTheExistingCatalog() {
        val report = report()

        assertEquals(1, report.reportVersion)
        assertEquals(
            EncryptedVaultWorkingParserValidationCompletionAuditKind
                .WorkingParserValidationCompletionAuditOnly,
            report.reportKind,
        )
        assertEquals(
            "ENCRYPTED_LOCAL_VAULT_WORKING_PARSER_VALIDATION_COMPLETION_AUDIT_ONLY",
            report.reportKind.label,
        )
        assertEquals(
            EncryptedVaultWorkingParserValidationCompletionAuditSourceSet
                .CommonTestValidationOnly,
            report.sourceSet,
        )
        assertEquals("COMMON_TEST_VALIDATION_ONLY", report.sourceSet.label)
        assertTrue(report.workingParserAdmissionGatePresent)
        assertTrue(report.parserWriterSyntheticVectorCatalogPresent)
        assertTrue(report.parserWriterImplementationScaffoldPresent)
        assertTrue(report.workingParserImplementationPresent)
        assertTrue(report.commonMainInMemoryParserPresent)
        assertTrue(report.parserCompiledIntoProductionArtifacts)
        assertTrue(report.syntheticTestVectorExecutionPresent)
        assertTrue(report.syntheticTestVectorExecutionValidated)
        assertTrue(report.existingCatalogReused)
        assertTrue(report.noSecondCatalogCreated)
        assertTrue(report.noNewPersistentVectorFixturesCreated)
    }

    @Test
    fun everyExistingCatalogVectorMatchesItsExpectedClassificationOrBlocker() {
        val report = report()

        assertEquals(12, report.vectorClassCount)
        assertEquals(5, report.expectedAcceptedCatalogVectorCount)
        assertEquals(7, report.expectedNonValidCatalogVectorCount)
        assertTrue(report.allCatalogVectorsCovered)
        assertTrue(report.allExpectedAcceptedClassificationsMatched)
        assertTrue(report.allExpectedFailClosedOutcomesMatched)
        assertEquals(
            setOf(
                EncryptedVaultWorkingParserSyntheticClassification.SyntheticHeaderOnly,
                EncryptedVaultWorkingParserSyntheticClassification.SyntheticKdfSection,
                EncryptedVaultWorkingParserSyntheticClassification.SyntheticDirectory,
                EncryptedVaultWorkingParserSyntheticClassification.SyntheticSingleRecordEnvelope,
                EncryptedVaultWorkingParserSyntheticClassification.SyntheticMultiRecordDirectory,
            ),
            report.expectedAcceptedClassifications.toSet(),
        )
        assertEquals(
            setOf(
                EncryptedVaultWorkingParserBlocker.UnsupportedVersion,
                EncryptedVaultWorkingParserBlocker.UnsupportedCriticalFeature,
                EncryptedVaultWorkingParserBlocker.TruncatedHeader,
                EncryptedVaultWorkingParserBlocker.TruncatedRecordEnvelope,
                EncryptedVaultWorkingParserBlocker.RedactedDiagnosticsOnly,
                EncryptedVaultWorkingParserBlocker.MigrationRequired,
                EncryptedVaultWorkingParserBlocker.CorruptionSuspected,
            ),
            report.expectedFailClosedBlockers.toSet(),
        )
        assertFalse(report.redactedDiagnosticsVectorTreatedAsValidContainer)
    }

    @Test
    fun runtimeOnlyNegativeInputsMeetTheExactMatchContract() {
        val report = report()

        assertTrue(report.emptyInputRejected)
        assertTrue(report.unknownInputRejected)
        assertTrue(report.exactMarkerMatchRequired)
        assertTrue(report.prefixedMarkerRejected)
        assertTrue(report.suffixedMarkerRejected)
        assertTrue(report.caseAlteredMarkerRejected)
        assertTrue(report.concatenatedMarkersRejected)
        assertTrue(report.removedByteMarkerRejected)
        assertTrue(report.replacedByteMarkerRejected)
        assertTrue(report.blockers.isEmpty())
    }

    @Test
    fun parserInputOwnershipAndResultPayloadBoundariesPass() {
        val report = report()

        assertTrue(report.deterministicRepeatedResults)
        assertTrue(report.parserInputNotMutated)
        assertTrue(report.parserInputNotRetained)
        assertTrue(report.parserResultContainsNoByteArray)
        assertTrue(report.parserResultContainsNoInputReference)
        assertTrue(report.parserResultContainsNoSectionBytes)
        assertTrue(report.parserResultContainsNoPayload)
        assertTrue(report.parserDiagnosticsSafeLabelsOnly)
        assertTrue(report.parserDiagnosticsPayloadFree)
        assertTrue(report.resultToStringRedacted)
        assertTrue(report.diagnosticsToStringRedacted)
    }

    @Test
    fun parserObjectToStringIsRedactedAndCurrentContractAuditPasses() {
        val report = report()

        assertTrue(report.parserToStringRedacted)
        assertTrue(report.parserValidationCompletionAuditPassed)
        assertTrue(report.blockers.isEmpty())
        assertEquals(0, report.blockerCount)
        assertEquals(0, report.warningCount)
    }

    @Test
    fun auditReportsNoProductionParserReachabilityOrRealVaultParsing() {
        val report = report()

        assertFalse(report.productionParserCallSitePresent)
        assertFalse(report.productionParserRegistryPresent)
        assertFalse(report.productionParserFactoryPresent)
        assertFalse(report.productionParserDispatcherPresent)
        assertFalse(report.productionParserServicePresent)
        assertFalse(report.productionParserRepositoryIntegrationPresent)
        assertFalse(report.productionParserStorageIntegrationPresent)
        assertFalse(report.productionParserUiIntegrationPresent)
        assertFalse(report.productionVaultFileParsingEnabled)
        assertFalse(report.realVaultDataParsingEnabled)
        assertFalse(report.productionVectorBytesPresent)
        assertFalse(report.productionParserInputFixturesPresent)
        assertFalse(report.productionWriterOutputBytesPresent)
        assertFalse(report.realVaultFormatParserPresent)
        assertFalse(report.canonicalBinaryLayoutImplemented)
        assertFalse(report.streamingParserPresent)
        assertFalse(report.productionInputSizePolicyFinalized)
        assertFalse(report.authenticatedContainerParsingPresent)
        assertFalse(report.productionVaultParserReady)
    }

    @Test
    fun auditReportsNoWriterIoCryptoStorageSyncProviderOrMainnetExpansion() {
        val report = report()

        assertFalse(report.workingWriterImplementationPresent)
        assertFalse(report.writerExecutionPresent)
        assertFalse(report.vaultContainerSerializationPresent)
        assertFalse(report.vaultFileReadPresent)
        assertFalse(report.vaultFileWritePresent)
        assertFalse(report.vaultFileDeletePresent)
        assertFalse(report.vaultDirectoryCreated)
        assertFalse(report.kdfExecutionPresent)
        assertFalse(report.aeadExecutionPresent)
        assertFalse(report.encryptionExecutionPresent)
        assertFalse(report.decryptionExecutionPresent)
        assertFalse(report.authenticationExecutionPresent)
        assertFalse(report.keyGenerationPresent)
        assertFalse(report.nonceGenerationPresent)
        assertFalse(report.tinkKeysetCreationPresent)
        assertFalse(report.tinkKeysetPersistencePresent)
        assertFalse(report.encryptedVaultRepositorySuccessPresent)
        assertFalse(report.secureSecretStorageSuccessPathPresent)
        assertFalse(report.secureMetadataStorageSuccessPathPresent)
        assertFalse(report.productionObservationPersistencePresent)
        assertFalse(report.productionAddressIndexPersistencePresent)
        assertFalse(report.productionUtxoPersistencePresent)
        assertFalse(report.productionWalletHistoryPersistencePresent)
        assertFalse(report.productionSyncPresent)
        assertFalse(report.productionBackendClientPresent)
        assertFalse(report.productionProviderSelectionEnabled)
        assertFalse(report.productionProviderSelectable)
        assertTrue(report.productionSelectionStillDisabledProviderOnly)
        assertFalse(report.signingBroadcastingPresent)
        assertFalse(report.uiActionEnablementPresent)
        assertFalse(report.endpointPresent)
        assertFalse(report.mainnetPresent)
    }

    @Test
    fun allFutureProductionCapabilitiesRemainSeparatePasses() {
        val report = report()

        assertTrue(report.futureCanonicalBinaryLayoutDecisionRequiresSeparatePass)
        assertTrue(report.futureProductionInputSizeAndStreamingPolicyRequiresSeparatePass)
        assertTrue(report.futureAuthenticatedContainerParsingRequiresSeparatePass)
        assertTrue(report.futureWorkingWriterAdmissionRequiresSeparatePass)
        assertTrue(report.futureWorkingWriterImplementationRequiresSeparatePass)
        assertTrue(report.futureParserWriterRoundTripRequiresSeparatePass)
        assertTrue(report.futureVaultStorageRepositoryRequiresSeparatePass)
        assertTrue(report.futureSecureStorageSuccessRequiresSeparatePass)
        assertTrue(report.futureSecureMetadataSuccessRequiresSeparatePass)
        assertTrue(report.futureProductionSyncRequiresSeparatePass)
        assertTrue(report.futureProductionProviderSelectionRequiresSeparatePass)
    }

    @Test
    fun auditReportDisplayIsRedactedAndContainsNoCatalogMarker() {
        val report = report()
        val rendered = listOf(report.toString(), report.reportId.toString()).joinToString(" | ")

        assertContains(rendered, "REDACTED")
        assertContains(rendered, "COMMON_TEST_VALIDATION_ONLY")
        EncryptedVaultParserWriterSyntheticVectorCatalog.fixtures.forEach { fixture ->
            assertFalse(rendered.contains(fixture.markerForTestSourceConfinementAssertionOnly()))
        }
        assertFalse(Regex("\\b[0-9a-fA-F]{64}\\b").containsMatchIn(rendered))
        assertEquals(report(), report)
    }
}
