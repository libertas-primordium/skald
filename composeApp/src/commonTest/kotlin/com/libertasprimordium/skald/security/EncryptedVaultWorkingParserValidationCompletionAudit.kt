package com.libertasprimordium.skald.security

@JvmInline
value class EncryptedVaultWorkingParserValidationCompletionAuditSafeLabel(val value: String) {
    override fun toString(): String =
        "EncryptedVaultWorkingParserValidationCompletionAuditSafeLabel(REDACTED)"
}

enum class EncryptedVaultWorkingParserValidationCompletionAuditKind(val label: String) {
    WorkingParserValidationCompletionAuditOnly(
        "ENCRYPTED_LOCAL_VAULT_WORKING_PARSER_VALIDATION_COMPLETION_AUDIT_ONLY",
    ),
}

enum class EncryptedVaultWorkingParserValidationCompletionAuditSourceSet(val label: String) {
    CommonTestValidationOnly("COMMON_TEST_VALIDATION_ONLY"),
}

enum class EncryptedVaultWorkingParserValidationCompletionAuditBlocker(val safeLabel: String) {
    SuffixedMarkerAccepted("suffixed-marker-accepted"),
    ConcatenatedMarkersAccepted("concatenated-markers-accepted"),
    ParserToStringNotExplicitlyRedacted("parser-to-string-not-explicitly-redacted"),
    CatalogCoverageMismatch("catalog-coverage-mismatch"),
    AcceptedClassificationMismatch("accepted-classification-mismatch"),
    FailClosedOutcomeMismatch("fail-closed-outcome-mismatch"),
    EmptyInputAccepted("empty-input-accepted"),
    UnknownInputAccepted("unknown-input-accepted"),
    PrefixedMarkerAccepted("prefixed-marker-accepted"),
    CaseAlteredMarkerAccepted("case-altered-marker-accepted"),
    RemovedByteMarkerAccepted("removed-byte-marker-accepted"),
    ReplacedByteMarkerAccepted("replaced-byte-marker-accepted"),
    RepeatedResultMismatch("repeated-result-mismatch"),
    ParserInputMutated("parser-input-mutated"),
    ParserInputRetainedByResult("parser-input-retained-by-result"),
    ParserResultMaterialExposure("parser-result-material-exposure"),
    ParserDiagnosticsMaterialExposure("parser-diagnostics-material-exposure"),
    ResultToStringNotRedacted("result-to-string-not-redacted"),
    DiagnosticsToStringNotRedacted("diagnostics-to-string-not-redacted"),
}

data class EncryptedVaultWorkingParserValidationCompletionAuditReport(
    val reportId: EncryptedVaultWorkingParserValidationCompletionAuditSafeLabel,
    val reportVersion: Int,
    val reportKind: EncryptedVaultWorkingParserValidationCompletionAuditKind,
    val sourceSet: EncryptedVaultWorkingParserValidationCompletionAuditSourceSet,
    val workingParserAdmissionGatePresent: Boolean,
    val parserWriterSyntheticVectorCatalogPresent: Boolean,
    val parserWriterImplementationScaffoldPresent: Boolean,
    val workingParserImplementationPresent: Boolean,
    val commonMainInMemoryParserPresent: Boolean,
    val parserCompiledIntoProductionArtifacts: Boolean,
    val syntheticTestVectorExecutionPresent: Boolean,
    val syntheticTestVectorExecutionValidated: Boolean,
    val existingCatalogReused: Boolean,
    val noSecondCatalogCreated: Boolean,
    val noNewPersistentVectorFixturesCreated: Boolean,
    val vectorClassCount: Int,
    val expectedAcceptedCatalogVectorCount: Int,
    val expectedNonValidCatalogVectorCount: Int,
    val allCatalogVectorsCovered: Boolean,
    val allExpectedAcceptedClassificationsMatched: Boolean,
    val allExpectedFailClosedOutcomesMatched: Boolean,
    val expectedAcceptedClassifications: List<EncryptedVaultWorkingParserSyntheticClassification>,
    val expectedFailClosedBlockers: List<EncryptedVaultWorkingParserBlocker>,
    val redactedDiagnosticsVectorTreatedAsValidContainer: Boolean,
    val emptyInputRejected: Boolean,
    val unknownInputRejected: Boolean,
    val exactMarkerMatchRequired: Boolean,
    val prefixedMarkerRejected: Boolean,
    val suffixedMarkerRejected: Boolean,
    val caseAlteredMarkerRejected: Boolean,
    val concatenatedMarkersRejected: Boolean,
    val removedByteMarkerRejected: Boolean,
    val replacedByteMarkerRejected: Boolean,
    val deterministicRepeatedResults: Boolean,
    val parserInputNotMutated: Boolean,
    val parserInputNotRetained: Boolean,
    val parserResultContainsNoByteArray: Boolean,
    val parserResultContainsNoInputReference: Boolean,
    val parserResultContainsNoSectionBytes: Boolean,
    val parserResultContainsNoPayload: Boolean,
    val parserDiagnosticsSafeLabelsOnly: Boolean,
    val parserDiagnosticsPayloadFree: Boolean,
    val parserToStringRedacted: Boolean,
    val resultToStringRedacted: Boolean,
    val diagnosticsToStringRedacted: Boolean,
    val productionParserCallSitePresent: Boolean,
    val productionParserRegistryPresent: Boolean,
    val productionParserFactoryPresent: Boolean,
    val productionParserDispatcherPresent: Boolean,
    val productionParserServicePresent: Boolean,
    val productionParserRepositoryIntegrationPresent: Boolean,
    val productionParserStorageIntegrationPresent: Boolean,
    val productionParserUiIntegrationPresent: Boolean,
    val productionVaultFileParsingEnabled: Boolean,
    val realVaultDataParsingEnabled: Boolean,
    val productionVectorBytesPresent: Boolean,
    val productionParserInputFixturesPresent: Boolean,
    val productionWriterOutputBytesPresent: Boolean,
    val workingWriterImplementationPresent: Boolean,
    val writerExecutionPresent: Boolean,
    val vaultContainerSerializationPresent: Boolean,
    val vaultFileReadPresent: Boolean,
    val vaultFileWritePresent: Boolean,
    val vaultFileDeletePresent: Boolean,
    val vaultDirectoryCreated: Boolean,
    val kdfExecutionPresent: Boolean,
    val aeadExecutionPresent: Boolean,
    val encryptionExecutionPresent: Boolean,
    val decryptionExecutionPresent: Boolean,
    val authenticationExecutionPresent: Boolean,
    val keyGenerationPresent: Boolean,
    val nonceGenerationPresent: Boolean,
    val tinkKeysetCreationPresent: Boolean,
    val tinkKeysetPersistencePresent: Boolean,
    val encryptedVaultRepositorySuccessPresent: Boolean,
    val secureSecretStorageSuccessPathPresent: Boolean,
    val secureMetadataStorageSuccessPathPresent: Boolean,
    val productionObservationPersistencePresent: Boolean,
    val productionAddressIndexPersistencePresent: Boolean,
    val productionUtxoPersistencePresent: Boolean,
    val productionWalletHistoryPersistencePresent: Boolean,
    val productionSyncPresent: Boolean,
    val productionBackendClientPresent: Boolean,
    val productionProviderSelectionEnabled: Boolean,
    val productionProviderSelectable: Boolean,
    val productionSelectionStillDisabledProviderOnly: Boolean,
    val signingBroadcastingPresent: Boolean,
    val uiActionEnablementPresent: Boolean,
    val endpointPresent: Boolean,
    val mainnetPresent: Boolean,
    val realVaultFormatParserPresent: Boolean,
    val canonicalBinaryLayoutImplemented: Boolean,
    val streamingParserPresent: Boolean,
    val productionInputSizePolicyFinalized: Boolean,
    val authenticatedContainerParsingPresent: Boolean,
    val productionVaultParserReady: Boolean,
    val futureCanonicalBinaryLayoutDecisionRequiresSeparatePass: Boolean,
    val futureProductionInputSizeAndStreamingPolicyRequiresSeparatePass: Boolean,
    val futureAuthenticatedContainerParsingRequiresSeparatePass: Boolean,
    val futureWorkingWriterAdmissionRequiresSeparatePass: Boolean,
    val futureWorkingWriterImplementationRequiresSeparatePass: Boolean,
    val futureParserWriterRoundTripRequiresSeparatePass: Boolean,
    val futureVaultStorageRepositoryRequiresSeparatePass: Boolean,
    val futureSecureStorageSuccessRequiresSeparatePass: Boolean,
    val futureSecureMetadataSuccessRequiresSeparatePass: Boolean,
    val futureProductionSyncRequiresSeparatePass: Boolean,
    val futureProductionProviderSelectionRequiresSeparatePass: Boolean,
    val parserValidationCompletionAuditPassed: Boolean,
    val blockers: List<EncryptedVaultWorkingParserValidationCompletionAuditBlocker>,
    val blockerCount: Int,
    val warningCount: Int,
) {
    override fun toString(): String =
        "EncryptedVaultWorkingParserValidationCompletionAuditReport(" +
            "REDACTED, COMMON_TEST_VALIDATION_ONLY, SAFE_LABELS_ONLY, NO_BYTES, NO_PAYLOADS)"
}

object EncryptedVaultWorkingParserValidationCompletionAudit {
    fun currentReport(): EncryptedVaultWorkingParserValidationCompletionAuditReport {
        val admission =
            EncryptedVaultWorkingParserAdmissionGatePolicy.currentWorkingParserAdmissionGate()
        val scaffold = EncryptedVaultParserWriterScaffoldPolicy.currentParserWriterScaffold()
        val catalog = EncryptedVaultParserWriterSyntheticVectorCatalog.currentCatalogReport()
        val parserEvidence = EncryptedVaultWorkingParser.currentParserEvidence()
        val fixtureObservations =
            EncryptedVaultParserWriterSyntheticVectorCatalog.fixtures.map(::observeFixture)

        val expectedAccepted = acceptedExpectations()
        val expectedFailed = failedExpectations()
        val observedClasses = fixtureObservations.map { it.vectorClass }
        val allCatalogVectorsCovered =
            observedClasses.size == EncryptedVaultParserWriterSyntheticVectorCatalogClass.entries.size &&
                observedClasses.toSet() ==
                EncryptedVaultParserWriterSyntheticVectorCatalogClass.entries.toSet()
        val allExpectedAcceptedClassificationsMatched =
            expectedAccepted.all { (vectorClass, classification) ->
                fixtureObservations.single { it.vectorClass == vectorClass }
                    .matchesAccepted(classification)
            }
        val allExpectedFailClosedOutcomesMatched =
            expectedFailed.all { (vectorClass, expectation) ->
                fixtureObservations.single { it.vectorClass == vectorClass }
                    .matchesFailed(expectation)
            }

        val negativeObservation = observeRuntimeNegativeInputs()
        val deterministicRepeatedResults = fixtureObservations.all { it.repeatedResultMatched }
        val parserInputNotMutated = fixtureObservations.all { it.inputNotMutated }
        val parserResultContainsNoByteArray =
            fixtureObservations.all { it.resultContainsNoByteArrayEvidence }
        val parserResultContainsNoInputReference =
            fixtureObservations.all { it.resultContainsNoInputReferenceEvidence }
        val parserResultContainsNoSectionBytes =
            fixtureObservations.all { it.resultContainsNoSectionBytesEvidence }
        val parserResultContainsNoPayload =
            fixtureObservations.all { it.resultContainsNoPayloadEvidence }
        val parserDiagnosticsSafeLabelsOnly =
            fixtureObservations.all { it.diagnosticsSafeLabelsOnly }
        val parserDiagnosticsPayloadFree =
            fixtureObservations.all { it.diagnosticsPayloadFree }
        val parserInputNotRetained =
            fixtureObservations.all { it.resultStableAfterCallerMutation } &&
                parserResultContainsNoInputReference &&
                parserResultContainsNoByteArray &&
                parserDiagnosticsPayloadFree
        val resultToStringRedacted = fixtureObservations.all { it.resultToStringRedacted }
        val diagnosticsToStringRedacted =
            fixtureObservations.all { it.diagnosticsToStringRedacted }
        val parserToStringRedacted =
            EncryptedVaultWorkingParser.toString().contains("REDACTED")
        val redactedDiagnosticsVectorTreatedAsValidContainer =
            fixtureObservations.single {
                it.vectorClass ==
                    EncryptedVaultParserWriterSyntheticVectorCatalogClass
                        .RedactedDiagnosticsSyntheticVector
            }.accepted

        val blockers = buildList {
            if (!allCatalogVectorsCovered) {
                add(
                    EncryptedVaultWorkingParserValidationCompletionAuditBlocker
                        .CatalogCoverageMismatch,
                )
            }
            if (!allExpectedAcceptedClassificationsMatched) {
                add(
                    EncryptedVaultWorkingParserValidationCompletionAuditBlocker
                        .AcceptedClassificationMismatch,
                )
            }
            if (!allExpectedFailClosedOutcomesMatched) {
                add(
                    EncryptedVaultWorkingParserValidationCompletionAuditBlocker
                        .FailClosedOutcomeMismatch,
                )
            }
            if (!negativeObservation.emptyInputRejected) {
                add(EncryptedVaultWorkingParserValidationCompletionAuditBlocker.EmptyInputAccepted)
            }
            if (!negativeObservation.unknownInputRejected) {
                add(EncryptedVaultWorkingParserValidationCompletionAuditBlocker.UnknownInputAccepted)
            }
            if (!negativeObservation.prefixedMarkerRejected) {
                add(EncryptedVaultWorkingParserValidationCompletionAuditBlocker.PrefixedMarkerAccepted)
            }
            if (!negativeObservation.suffixedMarkerRejected) {
                add(EncryptedVaultWorkingParserValidationCompletionAuditBlocker.SuffixedMarkerAccepted)
            }
            if (!negativeObservation.caseAlteredMarkerRejected) {
                add(EncryptedVaultWorkingParserValidationCompletionAuditBlocker.CaseAlteredMarkerAccepted)
            }
            if (!negativeObservation.concatenatedMarkersRejected) {
                add(
                    EncryptedVaultWorkingParserValidationCompletionAuditBlocker
                        .ConcatenatedMarkersAccepted,
                )
            }
            if (!negativeObservation.removedByteMarkerRejected) {
                add(EncryptedVaultWorkingParserValidationCompletionAuditBlocker.RemovedByteMarkerAccepted)
            }
            if (!negativeObservation.replacedByteMarkerRejected) {
                add(EncryptedVaultWorkingParserValidationCompletionAuditBlocker.ReplacedByteMarkerAccepted)
            }
            if (!deterministicRepeatedResults) {
                add(EncryptedVaultWorkingParserValidationCompletionAuditBlocker.RepeatedResultMismatch)
            }
            if (!parserInputNotMutated) {
                add(EncryptedVaultWorkingParserValidationCompletionAuditBlocker.ParserInputMutated)
            }
            if (!parserInputNotRetained) {
                add(
                    EncryptedVaultWorkingParserValidationCompletionAuditBlocker
                        .ParserInputRetainedByResult,
                )
            }
            if (
                !parserResultContainsNoByteArray ||
                !parserResultContainsNoInputReference ||
                !parserResultContainsNoSectionBytes ||
                !parserResultContainsNoPayload
            ) {
                add(
                    EncryptedVaultWorkingParserValidationCompletionAuditBlocker
                        .ParserResultMaterialExposure,
                )
            }
            if (!parserDiagnosticsSafeLabelsOnly || !parserDiagnosticsPayloadFree) {
                add(
                    EncryptedVaultWorkingParserValidationCompletionAuditBlocker
                        .ParserDiagnosticsMaterialExposure,
                )
            }
            if (!parserToStringRedacted) {
                add(
                    EncryptedVaultWorkingParserValidationCompletionAuditBlocker
                        .ParserToStringNotExplicitlyRedacted,
                )
            }
            if (!resultToStringRedacted) {
                add(EncryptedVaultWorkingParserValidationCompletionAuditBlocker.ResultToStringNotRedacted)
            }
            if (!diagnosticsToStringRedacted) {
                add(
                    EncryptedVaultWorkingParserValidationCompletionAuditBlocker
                        .DiagnosticsToStringNotRedacted,
                )
            }
        }

        val syntheticTestVectorExecutionValidated = blockers.isEmpty()

        return EncryptedVaultWorkingParserValidationCompletionAuditReport(
            reportId = EncryptedVaultWorkingParserValidationCompletionAuditSafeLabel(
                "skald-encrypted-local-vault-working-parser-validation-completion-audit-v1",
            ),
            reportVersion = 1,
            reportKind =
                EncryptedVaultWorkingParserValidationCompletionAuditKind
                    .WorkingParserValidationCompletionAuditOnly,
            sourceSet =
                EncryptedVaultWorkingParserValidationCompletionAuditSourceSet
                    .CommonTestValidationOnly,
            workingParserAdmissionGatePresent = admission.workingParserAdmissionGatePassed,
            parserWriterSyntheticVectorCatalogPresent = catalog.syntheticVectorCatalogPresent,
            parserWriterImplementationScaffoldPresent =
                scaffold.parserWriterImplementationScaffoldPresent,
            workingParserImplementationPresent = parserEvidence.workingParserImplementationPresent,
            commonMainInMemoryParserPresent = parserEvidence.commonMainInMemoryParserPresent,
            parserCompiledIntoProductionArtifacts = true,
            syntheticTestVectorExecutionPresent = true,
            syntheticTestVectorExecutionValidated = syntheticTestVectorExecutionValidated,
            existingCatalogReused = true,
            noSecondCatalogCreated = true,
            noNewPersistentVectorFixturesCreated = true,
            vectorClassCount = catalog.vectorClassCount,
            expectedAcceptedCatalogVectorCount = expectedAccepted.size,
            expectedNonValidCatalogVectorCount = expectedFailed.size,
            allCatalogVectorsCovered = allCatalogVectorsCovered,
            allExpectedAcceptedClassificationsMatched =
                allExpectedAcceptedClassificationsMatched,
            allExpectedFailClosedOutcomesMatched = allExpectedFailClosedOutcomesMatched,
            expectedAcceptedClassifications = expectedAccepted.values.toList(),
            expectedFailClosedBlockers = expectedFailed.values.map { it.blocker },
            redactedDiagnosticsVectorTreatedAsValidContainer =
                redactedDiagnosticsVectorTreatedAsValidContainer,
            emptyInputRejected = negativeObservation.emptyInputRejected,
            unknownInputRejected = negativeObservation.unknownInputRejected,
            exactMarkerMatchRequired =
                negativeObservation.prefixedMarkerRejected &&
                    negativeObservation.suffixedMarkerRejected &&
                    negativeObservation.caseAlteredMarkerRejected &&
                    negativeObservation.concatenatedMarkersRejected &&
                    negativeObservation.removedByteMarkerRejected &&
                    negativeObservation.replacedByteMarkerRejected,
            prefixedMarkerRejected = negativeObservation.prefixedMarkerRejected,
            suffixedMarkerRejected = negativeObservation.suffixedMarkerRejected,
            caseAlteredMarkerRejected = negativeObservation.caseAlteredMarkerRejected,
            concatenatedMarkersRejected = negativeObservation.concatenatedMarkersRejected,
            removedByteMarkerRejected = negativeObservation.removedByteMarkerRejected,
            replacedByteMarkerRejected = negativeObservation.replacedByteMarkerRejected,
            deterministicRepeatedResults = deterministicRepeatedResults,
            parserInputNotMutated = parserInputNotMutated,
            parserInputNotRetained = parserInputNotRetained,
            parserResultContainsNoByteArray = parserResultContainsNoByteArray,
            parserResultContainsNoInputReference = parserResultContainsNoInputReference,
            parserResultContainsNoSectionBytes = parserResultContainsNoSectionBytes,
            parserResultContainsNoPayload = parserResultContainsNoPayload,
            parserDiagnosticsSafeLabelsOnly = parserDiagnosticsSafeLabelsOnly,
            parserDiagnosticsPayloadFree = parserDiagnosticsPayloadFree,
            parserToStringRedacted = parserToStringRedacted,
            resultToStringRedacted = resultToStringRedacted,
            diagnosticsToStringRedacted = diagnosticsToStringRedacted,
            productionParserCallSitePresent = false,
            productionParserRegistryPresent = false,
            productionParserFactoryPresent = false,
            productionParserDispatcherPresent = false,
            productionParserServicePresent = false,
            productionParserRepositoryIntegrationPresent = false,
            productionParserStorageIntegrationPresent = false,
            productionParserUiIntegrationPresent = false,
            productionVaultFileParsingEnabled = false,
            realVaultDataParsingEnabled = false,
            productionVectorBytesPresent = parserEvidence.productionVectorBytesPresent,
            productionParserInputFixturesPresent = parserEvidence.productionParserInputBytesPresent,
            productionWriterOutputBytesPresent = parserEvidence.productionWriterOutputBytesPresent,
            workingWriterImplementationPresent = parserEvidence.workingWriterImplementationPresent,
            writerExecutionPresent = false,
            vaultContainerSerializationPresent = parserEvidence.vaultContainerSerializationPresent,
            vaultFileReadPresent = parserEvidence.vaultFileReadPresent,
            vaultFileWritePresent = parserEvidence.vaultFileWritePresent,
            vaultFileDeletePresent = parserEvidence.vaultFileDeletePresent,
            vaultDirectoryCreated = parserEvidence.vaultDirectoryCreated,
            kdfExecutionPresent = parserEvidence.kdfExecutionPresent,
            aeadExecutionPresent = parserEvidence.aeadExecutionPresent,
            encryptionExecutionPresent = parserEvidence.encryptionExecutionPresent,
            decryptionExecutionPresent = parserEvidence.decryptionExecutionPresent,
            authenticationExecutionPresent = parserEvidence.authenticationExecutionPresent,
            keyGenerationPresent = parserEvidence.keyGenerationPresent,
            nonceGenerationPresent = parserEvidence.nonceGenerationPresent,
            tinkKeysetCreationPresent = parserEvidence.tinkKeysetCreationPresent,
            tinkKeysetPersistencePresent = parserEvidence.tinkKeysetPersistencePresent,
            encryptedVaultRepositorySuccessPresent =
                parserEvidence.encryptedVaultRepositorySuccessPresent,
            secureSecretStorageSuccessPathPresent =
                parserEvidence.secureSecretStorageSuccessPathPresent,
            secureMetadataStorageSuccessPathPresent =
                parserEvidence.secureMetadataStorageSuccessPathPresent,
            productionObservationPersistencePresent =
                parserEvidence.productionObservationPersistencePresent,
            productionAddressIndexPersistencePresent =
                parserEvidence.productionAddressIndexPersistencePresent,
            productionUtxoPersistencePresent = parserEvidence.productionUtxoPersistencePresent,
            productionWalletHistoryPersistencePresent =
                parserEvidence.productionWalletHistoryPersistencePresent,
            productionSyncPresent = parserEvidence.productionSyncPresent,
            productionBackendClientPresent = parserEvidence.productionBackendClientPresent,
            productionProviderSelectionEnabled =
                parserEvidence.productionProviderSelectionEnabled,
            productionProviderSelectable = parserEvidence.productionProviderSelectable,
            productionSelectionStillDisabledProviderOnly =
                parserEvidence.productionSelectionStillDisabledProviderOnly,
            signingBroadcastingPresent = parserEvidence.signingBroadcastingPresent,
            uiActionEnablementPresent = parserEvidence.uiActionEnablementPresent,
            endpointPresent = parserEvidence.endpointPresent,
            mainnetPresent = parserEvidence.mainnetPresent,
            realVaultFormatParserPresent = false,
            canonicalBinaryLayoutImplemented = false,
            streamingParserPresent = false,
            productionInputSizePolicyFinalized = false,
            authenticatedContainerParsingPresent = false,
            productionVaultParserReady = false,
            futureCanonicalBinaryLayoutDecisionRequiresSeparatePass = true,
            futureProductionInputSizeAndStreamingPolicyRequiresSeparatePass = true,
            futureAuthenticatedContainerParsingRequiresSeparatePass = true,
            futureWorkingWriterAdmissionRequiresSeparatePass = true,
            futureWorkingWriterImplementationRequiresSeparatePass =
                parserEvidence.futureWorkingWriterImplementationRequiresSeparatePass,
            futureParserWriterRoundTripRequiresSeparatePass =
                parserEvidence.futureParserWriterRoundTripRequiresSeparatePass,
            futureVaultStorageRepositoryRequiresSeparatePass =
                parserEvidence.futureVaultStorageRepositoryRequiresSeparatePass,
            futureSecureStorageSuccessRequiresSeparatePass =
                parserEvidence.futureSecureStorageSuccessRequiresSeparatePass,
            futureSecureMetadataSuccessRequiresSeparatePass =
                parserEvidence.futureSecureMetadataSuccessRequiresSeparatePass,
            futureProductionSyncRequiresSeparatePass =
                parserEvidence.futureProductionSyncRequiresSeparatePass,
            futureProductionProviderSelectionRequiresSeparatePass =
                parserEvidence.futureProductionProviderSelectionRequiresSeparatePass,
            parserValidationCompletionAuditPassed = blockers.isEmpty(),
            blockers = blockers,
            blockerCount = blockers.size,
            warningCount = 0,
        )
    }

    private fun observeFixture(
        fixture: EncryptedVaultParserWriterSyntheticVector,
    ): FixtureObservation {
        val callerOwnedInput = fixture.bytesForDisabledScaffoldRequestOnly()
        val beforeParse = callerOwnedInput.copyOf()
        val request = EncryptedVaultWorkingParserRequest.testSourceSyntheticVector(callerOwnedInput)
        val result = EncryptedVaultWorkingParser.parse(request)
        val inputNotMutated = callerOwnedInput.contentEquals(beforeParse)
        val beforeCallerMutation = result.safeSnapshot()
        callerOwnedInput.fill(0)
        val afterCallerMutation = result.safeSnapshot()

        val repeatedCallerOwnedInput = fixture.bytesForDisabledScaffoldRequestOnly()
        val repeatedResult = EncryptedVaultWorkingParser.parse(
            EncryptedVaultWorkingParserRequest.testSourceSyntheticVector(
                repeatedCallerOwnedInput,
            ),
        )
        repeatedCallerOwnedInput.fill(0)

        val marker = fixture.markerForTestSourceConfinementAssertionOnly()
        val renderedResult = result.toString()
        val renderedDiagnostics = result.diagnostics.toString()
        val noMarkerInResult = marker !in renderedResult
        val noMarkerInDiagnostics = marker !in renderedDiagnostics

        return FixtureObservation(
            vectorClass = fixture.vectorClass,
            status = result.status,
            classification = result.classification,
            accepted = result.accepted,
            sectionCount = result.sectionCount,
            blockers = result.blockers,
            inputNotMutated = inputNotMutated,
            resultStableAfterCallerMutation = beforeCallerMutation == afterCallerMutation,
            repeatedResultMatched = beforeCallerMutation == repeatedResult.safeSnapshot(),
            resultContainsNoByteArrayEvidence =
                !result.inputBytesExposed &&
                    !result.inputBytesCopiedToResult &&
                    !result.parsedPayloadByteArraysCreated,
            resultContainsNoInputReferenceEvidence =
                !result.inputBytesExposed && !result.inputBytesCopiedToResult,
            resultContainsNoSectionBytesEvidence = !result.sectionBytesExposed,
            resultContainsNoPayloadEvidence =
                !result.sectionBytesExposed &&
                    !result.parsedPayloadByteArraysCreated &&
                    result.producedByteCount == 0,
            diagnosticsSafeLabelsOnly =
                result.diagnostics.safeLabelsOnly &&
                    result.diagnostics.rawBytesFree &&
                    result.diagnostics.filePathFree &&
                    result.diagnostics.cryptoMaterialFree &&
                    noMarkerInDiagnostics,
            diagnosticsPayloadFree = result.diagnostics.payloadFree && noMarkerInDiagnostics,
            resultToStringRedacted =
                "REDACTED" in renderedResult &&
                    "NO_BYTES_EXPOSED" in renderedResult &&
                    noMarkerInResult,
            diagnosticsToStringRedacted =
                "REDACTED" in renderedDiagnostics &&
                    "NO_BYTES_EXPOSED" in renderedDiagnostics &&
                    noMarkerInDiagnostics,
        )
    }

    private fun observeRuntimeNegativeInputs(): RuntimeNegativeObservation {
        val first = EncryptedVaultParserWriterSyntheticVectorCatalog.fixtures.first()
        val second = EncryptedVaultParserWriterSyntheticVectorCatalog.fixtures[1]
        val valid = first.bytesForDisabledScaffoldRequestOnly()
        val otherValid = second.bytesForDisabledScaffoldRequestOnly()

        val empty = valid.copyOf(0)
        val unknown = valid.copyOf(2).also { candidate -> candidate[0] = (candidate[0].toInt() xor 1).toByte() }
        val prefixed = valid.copyOf(valid.size + 1).also { candidate ->
            valid.copyInto(candidate, destinationOffset = 1)
            candidate[0] = valid.last()
        }
        val suffixed = valid.copyOf(valid.size + 1).also { candidate ->
            candidate[candidate.lastIndex] = valid.first()
        }
        val caseAltered = valid.copyOf().also { candidate ->
            candidate[0] = (candidate[0].toInt() xor ASCII_CASE_BIT).toByte()
        }
        val concatenated = valid + otherValid
        val removed = valid.copyOf(valid.size - 1)
        val replaced = valid.copyOf().also { candidate ->
            candidate[SYNTHETIC_TOKEN_INDEX] =
                (candidate[SYNTHETIC_TOKEN_INDEX].toInt() xor 1).toByte()
        }

        val observation = RuntimeNegativeObservation(
            emptyInputRejected = parseRejected(empty),
            unknownInputRejected = parseRejected(unknown),
            prefixedMarkerRejected = parseRejected(prefixed),
            suffixedMarkerRejected = parseRejected(suffixed),
            caseAlteredMarkerRejected = parseRejected(caseAltered),
            concatenatedMarkersRejected = parseRejected(concatenated),
            removedByteMarkerRejected = parseRejected(removed),
            replacedByteMarkerRejected = parseRejected(replaced),
        )

        listOf(
            valid,
            otherValid,
            empty,
            unknown,
            prefixed,
            suffixed,
            caseAltered,
            concatenated,
            removed,
            replaced,
        ).forEach { it.fill(0) }
        return observation
    }

    private fun parseRejected(input: ByteArray): Boolean {
        val result = EncryptedVaultWorkingParser.parse(
            EncryptedVaultWorkingParserRequest.testSourceSyntheticVector(input),
        )
        return result.status == EncryptedVaultWorkingParserStatus.FailedClosed &&
            !result.accepted &&
            result.classification !in acceptedClassifications
    }

    private fun acceptedExpectations():
        Map<
            EncryptedVaultParserWriterSyntheticVectorCatalogClass,
            EncryptedVaultWorkingParserSyntheticClassification,
        > =
        linkedMapOf(
            EncryptedVaultParserWriterSyntheticVectorCatalogClass.MinimalHeaderOnlySyntheticVector to
                EncryptedVaultWorkingParserSyntheticClassification.SyntheticHeaderOnly,
            EncryptedVaultParserWriterSyntheticVectorCatalogClass.HeaderAndKdfSectionSyntheticVector to
                EncryptedVaultWorkingParserSyntheticClassification.SyntheticKdfSection,
            EncryptedVaultParserWriterSyntheticVectorCatalogClass.HeaderKeyEnvelopeDirectorySyntheticVector to
                EncryptedVaultWorkingParserSyntheticClassification.SyntheticDirectory,
            EncryptedVaultParserWriterSyntheticVectorCatalogClass.SingleRecordEnvelopeSyntheticVector to
                EncryptedVaultWorkingParserSyntheticClassification.SyntheticSingleRecordEnvelope,
            EncryptedVaultParserWriterSyntheticVectorCatalogClass.MultiRecordDirectorySyntheticVector to
                EncryptedVaultWorkingParserSyntheticClassification.SyntheticMultiRecordDirectory,
        )

    private fun failedExpectations():
        Map<
            EncryptedVaultParserWriterSyntheticVectorCatalogClass,
            FailedExpectation,
        > =
        linkedMapOf(
            EncryptedVaultParserWriterSyntheticVectorCatalogClass.UnsupportedVersionSyntheticVector to
                FailedExpectation(EncryptedVaultWorkingParserBlocker.UnsupportedVersion),
            EncryptedVaultParserWriterSyntheticVectorCatalogClass.UnknownCriticalFeatureSyntheticVector to
                FailedExpectation(EncryptedVaultWorkingParserBlocker.UnsupportedCriticalFeature),
            EncryptedVaultParserWriterSyntheticVectorCatalogClass.TruncatedHeaderSyntheticVector to
                FailedExpectation(EncryptedVaultWorkingParserBlocker.TruncatedHeader),
            EncryptedVaultParserWriterSyntheticVectorCatalogClass.TruncatedRecordEnvelopeSyntheticVector to
                FailedExpectation(EncryptedVaultWorkingParserBlocker.TruncatedRecordEnvelope),
            EncryptedVaultParserWriterSyntheticVectorCatalogClass.RedactedDiagnosticsSyntheticVector to
                FailedExpectation(
                    EncryptedVaultWorkingParserBlocker.RedactedDiagnosticsOnly,
                    EncryptedVaultWorkingParserSyntheticClassification.RedactedDiagnosticsOnly,
                ),
            EncryptedVaultParserWriterSyntheticVectorCatalogClass.MigrationRequiredSyntheticVector to
                FailedExpectation(EncryptedVaultWorkingParserBlocker.MigrationRequired),
            EncryptedVaultParserWriterSyntheticVectorCatalogClass.CorruptionSuspectedSyntheticVector to
                FailedExpectation(EncryptedVaultWorkingParserBlocker.CorruptionSuspected),
        )

    private fun EncryptedVaultWorkingParserResult.safeSnapshot(): SafeResultSnapshot =
        SafeResultSnapshot(
            status = status,
            classification = classification,
            accepted = accepted,
            sectionCount = sectionCount,
            consumedByteCount = consumedByteCount,
            producedByteCount = producedByteCount,
            blockers = blockers,
            blockerCount = blockerCount,
            warningCount = warningCount,
            diagnosticsStatus = diagnostics.status,
            diagnosticsClassification = diagnostics.classification,
            diagnosticsBlockerCount = diagnostics.blockerCount,
            diagnosticsWarningCount = diagnostics.warningCount,
            resultDisplay = toString(),
            diagnosticsDisplay = diagnostics.toString(),
        )

    private data class FailedExpectation(
        val blocker: EncryptedVaultWorkingParserBlocker,
        val classification: EncryptedVaultWorkingParserSyntheticClassification =
            EncryptedVaultWorkingParserSyntheticClassification.NotClassified,
    )

    private data class FixtureObservation(
        val vectorClass: EncryptedVaultParserWriterSyntheticVectorCatalogClass,
        val status: EncryptedVaultWorkingParserStatus,
        val classification: EncryptedVaultWorkingParserSyntheticClassification,
        val accepted: Boolean,
        val sectionCount: Int,
        val blockers: List<EncryptedVaultWorkingParserBlocker>,
        val inputNotMutated: Boolean,
        val resultStableAfterCallerMutation: Boolean,
        val repeatedResultMatched: Boolean,
        val resultContainsNoByteArrayEvidence: Boolean,
        val resultContainsNoInputReferenceEvidence: Boolean,
        val resultContainsNoSectionBytesEvidence: Boolean,
        val resultContainsNoPayloadEvidence: Boolean,
        val diagnosticsSafeLabelsOnly: Boolean,
        val diagnosticsPayloadFree: Boolean,
        val resultToStringRedacted: Boolean,
        val diagnosticsToStringRedacted: Boolean,
    ) {
        fun matchesAccepted(
            expectedClassification: EncryptedVaultWorkingParserSyntheticClassification,
        ): Boolean =
            status == EncryptedVaultWorkingParserStatus.ParsedSyntheticVector &&
                accepted &&
                classification == expectedClassification &&
                sectionCount > 0 &&
                blockers.isEmpty()

        fun matchesFailed(expectation: FailedExpectation): Boolean =
            status == EncryptedVaultWorkingParserStatus.FailedClosed &&
                !accepted &&
                classification == expectation.classification &&
                blockers == listOf(expectation.blocker)
    }

    private data class RuntimeNegativeObservation(
        val emptyInputRejected: Boolean,
        val unknownInputRejected: Boolean,
        val prefixedMarkerRejected: Boolean,
        val suffixedMarkerRejected: Boolean,
        val caseAlteredMarkerRejected: Boolean,
        val concatenatedMarkersRejected: Boolean,
        val removedByteMarkerRejected: Boolean,
        val replacedByteMarkerRejected: Boolean,
    )

    private data class SafeResultSnapshot(
        val status: EncryptedVaultWorkingParserStatus,
        val classification: EncryptedVaultWorkingParserSyntheticClassification,
        val accepted: Boolean,
        val sectionCount: Int,
        val consumedByteCount: Int,
        val producedByteCount: Int,
        val blockers: List<EncryptedVaultWorkingParserBlocker>,
        val blockerCount: Int,
        val warningCount: Int,
        val diagnosticsStatus: EncryptedVaultWorkingParserStatus,
        val diagnosticsClassification: EncryptedVaultWorkingParserSyntheticClassification,
        val diagnosticsBlockerCount: Int,
        val diagnosticsWarningCount: Int,
        val resultDisplay: String,
        val diagnosticsDisplay: String,
    )

    private val acceptedClassifications = setOf(
        EncryptedVaultWorkingParserSyntheticClassification.SyntheticHeaderOnly,
        EncryptedVaultWorkingParserSyntheticClassification.SyntheticKdfSection,
        EncryptedVaultWorkingParserSyntheticClassification.SyntheticDirectory,
        EncryptedVaultWorkingParserSyntheticClassification.SyntheticSingleRecordEnvelope,
        EncryptedVaultWorkingParserSyntheticClassification.SyntheticMultiRecordDirectory,
    )

    private const val ASCII_CASE_BIT = 0x20
    private const val SYNTHETIC_TOKEN_INDEX = 4
}
