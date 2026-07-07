package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSafeLabel(
    val value: String,
) {
    override fun toString(): String =
        "RedactedTestOnlyProviderIdentityProviderOperationSyntheticTraceSafeLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSourceSet {
    CommonTest,
}

enum class SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceEvent {
    TraceAdmissionSatisfiedForFutureOnly,
    NoopExecutionBoundarySuiteObserved,
    FixedSyntheticNoopResultObserved,
    TracePayloadStillAbsent,
    RealProviderOperationExecutionStillAbsent,
    CryptoExecutionStillAbsent,
    KatRunnerStillAbsent,
    KatExecutorStillAbsent,
    ProviderSelectionStillDisabledOnly,
    RuntimeReachabilityStillAbsent,
    MainnetStillDisabled,
}

data class SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTrace(
    val markerCount: Int,
    val caseBindingCount: Int,
    val providerOperationMetadataKatCount: Int,
    val providerOperationNoopKatCount: Int,
    val providerOperationNoopKatValidationCount: Int,
    val providerOperationNoopKatSuiteReportCount: Int,
    val providerOperationNoopExecutionBoundaryCount: Int,
    val providerOperationNoopExecutionBoundaryValidationCount: Int,
    val providerOperationNoopExecutionBoundarySuiteReportCount: Int,
    val providerOperationSyntheticTraceAdmissionCount: Int,
    val expectedSafeIdMatched: Boolean,
    val expectedFixtureIdMatched: Boolean,
    val expectedVectorIdMatched: Boolean,
    val expectedCaseIdMatched: Boolean,
    val expectedProviderOperationMetadataKatIdMatched: Boolean,
    val expectedProviderOperationNoopKatIdMatched: Boolean,
    val expectedProviderOperationNoopExecutionBoundaryIdMatched: Boolean,
    val expectedProviderOperationNoopExecutionBoundarySuiteReportIdMatched: Boolean,
    val expectedProviderOperationSyntheticTraceIdMatched: Boolean,
    val providerOperationNoopKatPassed: Boolean,
    val providerOperationNoopKatValidationPassed: Boolean,
    val providerOperationNoopKatSuitePassed: Boolean,
    val syntheticNoopResultPresent: Boolean,
    val noopExecutionBoundaryModeled: Boolean,
    val syntheticNoopEvaluationPermitted: Boolean,
    val noopExecutionBoundaryValidationPassed: Boolean,
    val noopExecutionBoundarySuitePassed: Boolean,
    val futureSyntheticTraceCriteriaModeled: Boolean,
    val syntheticTraceCreated: Boolean,
    val syntheticTracePayloadFree: Boolean,
    val syntheticTracePayloadPresent: Boolean,
    val syntheticTraceContainsRawBytes: Boolean,
    val syntheticTraceContainsHex: Boolean,
    val syntheticTraceContainsProviderHandles: Boolean,
    val syntheticTraceContainsCryptoObjects: Boolean,
    val syntheticTraceContainsWalletMaterial: Boolean,
    val syntheticTraceContainsEndpointMaterial: Boolean,
    val syntheticTraceContainsSourceLocation: Boolean,
    val syntheticTraceContainsDiagnosticsPayload: Boolean,
    val syntheticTraceContainsAnalyticsPayload: Boolean,
    val syntheticTraceContainsCrashReportPayload: Boolean,
    val syntheticTraceContainsSupportExportPayload: Boolean,
    val traceIsCommonTestOnly: Boolean,
    val traceIsProductionAuthorization: Boolean,
    val traceIsProviderSelectionAuthorization: Boolean,
    val traceIsProviderOperationAuthorization: Boolean,
    val traceIsKatExecutorAuthorization: Boolean,
    val traceIsCryptoAuthorization: Boolean,
    val traceIsVaultPersistenceAuthorization: Boolean,
    val traceIsMainnetAuthorization: Boolean,
    val traceReportsSyntheticNoopOnly: Boolean,
    val traceReportsProviderOperationExecution: Boolean,
    val traceReportsCryptoOperation: Boolean,
    val traceReportsVaultLifecycle: Boolean,
    val traceReportsPersistence: Boolean,
    val realProviderOperationExecutionPermitted: Boolean,
    val cryptoExecutionPermitted: Boolean,
    val katRunnerPermitted: Boolean,
    val katExecutorPermitted: Boolean,
    val vaultPersistencePermitted: Boolean,
    val mainnetPermitted: Boolean,
    val providerOperationKatExecutorPresent: Boolean,
    val providerOperationKatRunnerPresent: Boolean,
    val providerOperationExecutionPresent: Boolean,
    val cryptoExecutionPresent: Boolean,
    val rawKatMaterialPresent: Boolean,
    val rawVectorBytesPresent: Boolean,
    val rawVectorHexPresent: Boolean,
    val publicVectorBytesPresent: Boolean,
    val publicVectorHexPresent: Boolean,
    val runtimeSelectable: Boolean,
    val registrySelectable: Boolean,
    val factoryReachable: Boolean,
    val dispatcherReachable: Boolean,
    val executorTargetable: Boolean,
    val providerKatExecutorReachable: Boolean,
    val providerOperationReachable: Boolean,
    val cryptoExecutionReachable: Boolean,
    val vaultLifecycleReachable: Boolean,
    val persistenceReachable: Boolean,
    val productionSyncReachable: Boolean,
    val backendClientReachable: Boolean,
    val bdkWalletStateReachable: Boolean,
    val settingsCodecReachable: Boolean,
    val uiSurfaceReachable: Boolean,
    val signingBroadcastingReachable: Boolean,
    val publicEndpointReachable: Boolean,
    val mainnetReachable: Boolean,
    val implementsVaultCryptoProvider: Boolean,
    val containsVaultCryptoProvider: Boolean,
    val canExecuteProviderOperations: Boolean,
    val canExecuteCrypto: Boolean,
    val canUseForVaultLifecycle: Boolean,
    val canUseForPersistence: Boolean,
    val canUseForSync: Boolean,
    val canUseForSigning: Boolean,
    val canUseForBroadcasting: Boolean,
    val canUseForMainnet: Boolean,
    val productionProviderSelectable: Boolean,
    val traceEvents:
        List<SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceEvent>,
    val sourceSet: SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSourceSet,
    val displayLabel: SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTrace(redactedMarkerId, redactedFixtureId, redactedVectorId, redactedCaseId, redactedProviderOperationMetadataKatId, redactedProviderOperationNoopKatId, redactedBoundaryId, redactedBoundarySuiteReportId, redactedSyntheticTraceId, commonTestOnly, payloadFreeSyntheticTraceOnly, nonAuthorizing)"
}

object SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTracePolicy {
    private const val EXPECTED_SAFE_ID: String =
        "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"
    private const val EXPECTED_FIXTURE_ID: String =
        "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata"
    private const val EXPECTED_VECTOR_ID: String =
        "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata"
    private const val EXPECTED_CASE_ID: String =
        "skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata"
    private const val EXPECTED_PROVIDER_OPERATION_METADATA_KAT_ID: String =
        "skald-test-only-provider-identity-provider-operation-metadata-kat-v1-inert-identity"
    private const val EXPECTED_PROVIDER_OPERATION_NOOP_KAT_ID: String =
        "skald-test-only-provider-identity-provider-operation-noop-kat-v1-inert-identity"
    private const val EXPECTED_PROVIDER_OPERATION_NOOP_EXECUTION_BOUNDARY_ID: String =
        "skald-test-only-provider-identity-provider-operation-noop-execution-boundary-v1-inert-identity"
    private const val EXPECTED_PROVIDER_OPERATION_NOOP_EXECUTION_BOUNDARY_SUITE_REPORT_ID: String =
        "skald-test-only-provider-identity-provider-operation-noop-execution-boundary-suite-report-v1-inert-identity"
    private const val EXPECTED_PROVIDER_OPERATION_SYNTHETIC_TRACE_ID: String =
        "skald-test-only-provider-identity-provider-operation-synthetic-trace-v1-inert-identity"

    private val currentTrace:
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTrace by lazy {
            buildProviderOperationSyntheticTrace()
        }

    fun currentProviderOperationSyntheticTrace():
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTrace =
        currentTrace

    private fun buildProviderOperationSyntheticTrace():
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTrace {
        val marker = SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy.currentMarker()
        val capabilityMatrix =
            SkaldVaultV1TestOnlyProviderIdentityCapabilityMatrixPolicy.currentCapabilityMatrix()
        val providerOperationMetadataKatSuiteReport =
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatSuiteReportPolicy
                .currentProviderOperationMetadataKatSuiteReport()
        val providerOperationNoopKatAdmission =
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatAdmissionPolicy
                .currentProviderOperationNoopKatAdmission()
        val providerOperationNoopKat =
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatPolicy
                .evaluateCurrentProviderOperationNoopKat()
        val providerOperationNoopKatValidation =
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationPolicy
                .currentProviderOperationNoopKatValidationReport()
        val providerOperationNoopKatSuiteReport =
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatSuiteReportPolicy
                .currentProviderOperationNoopKatSuiteReport()
        val providerOperationNoopExecutionBoundary =
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryPolicy
                .currentProviderOperationNoopExecutionBoundary()
        val providerOperationNoopExecutionBoundaryValidation =
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationPolicy
                .currentProviderOperationNoopExecutionBoundaryValidationReport()
        val providerOperationNoopExecutionBoundarySuiteReport =
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundarySuiteReportPolicy
                .currentProviderOperationNoopExecutionBoundarySuiteReport()
        val traceAdmission =
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceAdmissionPolicy
                .currentProviderOperationSyntheticTraceAdmission()
        val traceEvents =
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceEvent.entries.toList()

        val markerCount =
            if (
                traceAdmission.markerCount == 1 &&
                providerOperationNoopExecutionBoundarySuiteReport.markerCount == 1 &&
                providerOperationNoopExecutionBoundaryValidation.markerCount == 1 &&
                providerOperationNoopExecutionBoundary.markerCount == 1 &&
                providerOperationNoopKatSuiteReport.markerCount == 1 &&
                providerOperationNoopKatValidation.markerCount == 1 &&
                providerOperationNoopKat.markerCount == 1 &&
                providerOperationNoopKatAdmission.markerCount == 1 &&
                providerOperationMetadataKatSuiteReport.markerCount == 1 &&
                marker.safeId.value == EXPECTED_SAFE_ID &&
                capabilityMatrix.expectedSafeIdMatched
            ) {
                1
            } else {
                0
            }
        val caseBindingCount = traceAdmission.caseBindingCount
        val providerOperationMetadataKatCount = traceAdmission.providerOperationMetadataKatCount
        val providerOperationNoopKatCount = traceAdmission.providerOperationNoopKatCount
        val providerOperationNoopKatValidationCount = traceAdmission.providerOperationNoopKatValidationCount
        val providerOperationNoopKatSuiteReportCount = traceAdmission.providerOperationNoopKatSuiteReportCount
        val providerOperationNoopExecutionBoundaryCount =
            traceAdmission.providerOperationNoopExecutionBoundaryCount
        val providerOperationNoopExecutionBoundaryValidationCount =
            traceAdmission.providerOperationNoopExecutionBoundaryValidationCount
        val providerOperationNoopExecutionBoundarySuiteReportCount =
            traceAdmission.providerOperationNoopExecutionBoundarySuiteReportCount
        val providerOperationSyntheticTraceAdmissionCount =
            if (
                traceAdmission.futureSyntheticTraceCriteriaModeled &&
                !traceAdmission.futureSyntheticTraceCriteriaAuthorizeCurrentTrace &&
                !traceAdmission.currentSyntheticTracePresent &&
                !traceAdmission.currentTracePayloadPresent
            ) {
                1
            } else {
                0
            }

        val expectedSafeIdMatched =
            markerCount == 1 &&
                traceAdmission.expectedSafeIdMatched &&
                providerOperationNoopExecutionBoundarySuiteReport.expectedSafeIdMatched &&
                providerOperationNoopExecutionBoundaryValidation.expectedSafeIdMatched &&
                providerOperationNoopExecutionBoundary.expectedSafeIdMatched &&
                providerOperationNoopKatSuiteReport.expectedSafeIdMatched &&
                providerOperationNoopKatValidation.expectedSafeIdMatched &&
                providerOperationNoopKat.expectedSafeIdMatched &&
                providerOperationNoopKatAdmission.expectedSafeIdMatched &&
                providerOperationMetadataKatSuiteReport.expectedSafeIdMatched
        val expectedFixtureIdMatched =
            traceAdmission.expectedFixtureIdMatched &&
                providerOperationNoopExecutionBoundarySuiteReport.expectedFixtureIdMatched &&
                providerOperationNoopExecutionBoundaryValidation.expectedFixtureIdMatched &&
                providerOperationNoopExecutionBoundary.expectedFixtureIdMatched &&
                providerOperationNoopKatSuiteReport.expectedFixtureIdMatched &&
                providerOperationNoopKatValidation.expectedFixtureIdMatched &&
                providerOperationNoopKat.expectedFixtureIdMatched &&
                providerOperationNoopKatAdmission.expectedFixtureIdMatched &&
                providerOperationMetadataKatSuiteReport.expectedFixtureIdMatched &&
                EXPECTED_FIXTURE_ID ==
                "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata"
        val expectedVectorIdMatched =
            traceAdmission.expectedVectorIdMatched &&
                providerOperationNoopExecutionBoundarySuiteReport.expectedVectorIdMatched &&
                providerOperationNoopExecutionBoundaryValidation.expectedVectorIdMatched &&
                providerOperationNoopExecutionBoundary.expectedVectorIdMatched &&
                providerOperationNoopKatSuiteReport.expectedVectorIdMatched &&
                providerOperationNoopKatValidation.expectedVectorIdMatched &&
                providerOperationNoopKat.expectedVectorIdMatched &&
                providerOperationNoopKatAdmission.expectedVectorIdMatched &&
                providerOperationMetadataKatSuiteReport.expectedVectorIdMatched &&
                EXPECTED_VECTOR_ID ==
                "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata"
        val expectedCaseIdMatched =
            traceAdmission.expectedCaseIdMatched &&
                providerOperationNoopExecutionBoundarySuiteReport.expectedCaseIdMatched &&
                providerOperationNoopExecutionBoundaryValidation.expectedCaseIdMatched &&
                providerOperationNoopExecutionBoundary.expectedCaseIdMatched &&
                providerOperationNoopKatSuiteReport.expectedCaseIdMatched &&
                providerOperationNoopKatValidation.expectedCaseIdMatched &&
                providerOperationNoopKat.expectedCaseIdMatched &&
                providerOperationNoopKatAdmission.expectedCaseIdMatched &&
                providerOperationMetadataKatSuiteReport.expectedCaseIdMatched &&
                EXPECTED_CASE_ID ==
                "skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata"
        val expectedProviderOperationMetadataKatIdMatched =
            traceAdmission.expectedProviderOperationMetadataKatIdMatched &&
                providerOperationNoopExecutionBoundarySuiteReport.expectedProviderOperationMetadataKatIdMatched &&
                providerOperationNoopExecutionBoundaryValidation.expectedProviderOperationMetadataKatIdMatched &&
                providerOperationNoopExecutionBoundary.expectedProviderOperationMetadataKatIdMatched &&
                providerOperationNoopKatSuiteReport.expectedProviderOperationMetadataKatIdMatched &&
                providerOperationNoopKatValidation.expectedProviderOperationMetadataKatIdMatched &&
                providerOperationNoopKat.expectedProviderOperationMetadataKatIdMatched &&
                providerOperationNoopKatAdmission.expectedProviderOperationMetadataKatIdMatched &&
                providerOperationMetadataKatSuiteReport.expectedProviderOperationMetadataKatIdMatched &&
                EXPECTED_PROVIDER_OPERATION_METADATA_KAT_ID ==
                "skald-test-only-provider-identity-provider-operation-metadata-kat-v1-inert-identity"
        val expectedProviderOperationNoopKatIdMatched =
            traceAdmission.expectedProviderOperationNoopKatIdMatched &&
                providerOperationNoopExecutionBoundarySuiteReport.expectedProviderOperationNoopKatIdMatched &&
                providerOperationNoopExecutionBoundaryValidation.expectedProviderOperationNoopKatIdMatched &&
                providerOperationNoopExecutionBoundary.expectedProviderOperationNoopKatIdMatched &&
                providerOperationNoopKatSuiteReport.expectedProviderOperationNoopKatIdMatched &&
                providerOperationNoopKatValidation.expectedProviderOperationNoopKatIdMatched &&
                providerOperationNoopKat.expectedProviderOperationNoopKatIdMatched &&
                providerOperationNoopKatAdmission.expectedProviderOperationNoopKatIdMatched &&
                EXPECTED_PROVIDER_OPERATION_NOOP_KAT_ID ==
                "skald-test-only-provider-identity-provider-operation-noop-kat-v1-inert-identity"
        val expectedProviderOperationNoopExecutionBoundaryIdMatched =
            traceAdmission.expectedProviderOperationNoopExecutionBoundaryIdMatched &&
                providerOperationNoopExecutionBoundarySuiteReport
                    .expectedProviderOperationNoopExecutionBoundaryIdMatched &&
                providerOperationNoopExecutionBoundaryValidation
                    .expectedProviderOperationNoopExecutionBoundaryIdMatched &&
                providerOperationNoopExecutionBoundary.expectedProviderOperationNoopExecutionBoundaryIdMatched &&
                EXPECTED_PROVIDER_OPERATION_NOOP_EXECUTION_BOUNDARY_ID ==
                "skald-test-only-provider-identity-provider-operation-noop-execution-boundary-v1-inert-identity"
        val expectedProviderOperationNoopExecutionBoundarySuiteReportIdMatched =
            traceAdmission.expectedProviderOperationNoopExecutionBoundarySuiteReportIdMatched &&
                providerOperationNoopExecutionBoundarySuiteReport
                    .expectedProviderOperationNoopExecutionBoundarySuiteReportIdMatched &&
                EXPECTED_PROVIDER_OPERATION_NOOP_EXECUTION_BOUNDARY_SUITE_REPORT_ID ==
                "skald-test-only-provider-identity-provider-operation-noop-execution-boundary-suite-report-v1-inert-identity"
        val expectedProviderOperationSyntheticTraceIdMatched =
            traceAdmission.expectedProviderOperationSyntheticTraceIdMatched &&
                EXPECTED_PROVIDER_OPERATION_SYNTHETIC_TRACE_ID ==
                "skald-test-only-provider-identity-provider-operation-synthetic-trace-v1-inert-identity"

        val providerOperationNoopKatPassed =
            traceAdmission.providerOperationNoopKatPassed &&
                providerOperationNoopExecutionBoundarySuiteReport.providerOperationNoopKatPassed &&
                providerOperationNoopExecutionBoundaryValidation.providerOperationNoopKatPassed &&
                providerOperationNoopExecutionBoundary.providerOperationNoopKatPassed &&
                providerOperationNoopKatSuiteReport.providerOperationNoopKatPassed &&
                providerOperationNoopKatValidation.providerOperationNoopKatPassed &&
                providerOperationNoopKat.providerOperationNoopKatPassed
        val providerOperationNoopKatValidationPassed =
            traceAdmission.providerOperationNoopKatValidationPassed &&
                providerOperationNoopExecutionBoundarySuiteReport.providerOperationNoopKatValidationPassed &&
                providerOperationNoopExecutionBoundaryValidation.providerOperationNoopKatValidationPassed &&
                providerOperationNoopExecutionBoundary.providerOperationNoopKatValidationPassed &&
                providerOperationNoopKatSuiteReport.providerOperationNoopKatValidationPassed &&
                providerOperationNoopKatValidation.allValidationChecksPassed
        val providerOperationNoopKatSuitePassed =
            traceAdmission.providerOperationNoopKatSuitePassed &&
                providerOperationNoopExecutionBoundarySuiteReport.providerOperationNoopKatSuitePassed &&
                providerOperationNoopExecutionBoundaryValidation.providerOperationNoopKatSuitePassed &&
                providerOperationNoopExecutionBoundary.providerOperationNoopKatSuitePassed &&
                providerOperationNoopKatSuiteReport.providerOperationNoopKatSuitePassed
        val syntheticNoopResultPresent =
            traceAdmission.syntheticNoopResultPresent &&
                providerOperationNoopExecutionBoundarySuiteReport.syntheticNoopResultPresent &&
                providerOperationNoopExecutionBoundaryValidation.syntheticNoopResultPresent &&
                providerOperationNoopExecutionBoundary.syntheticNoopResultPresent &&
                providerOperationNoopKatSuiteReport.syntheticNoopResultPresent &&
                providerOperationNoopKatValidation.syntheticNoopResultPresent &&
                providerOperationNoopKat.syntheticNoopResultPresent
        val noopExecutionBoundaryModeled =
            traceAdmission.noopExecutionBoundaryModeled &&
                providerOperationNoopExecutionBoundarySuiteReport.noopExecutionBoundaryModeled &&
                providerOperationNoopExecutionBoundaryValidation.noopExecutionBoundaryModeled &&
                providerOperationNoopExecutionBoundary.noopExecutionBoundaryModeled
        val syntheticNoopEvaluationPermitted =
            traceAdmission.syntheticNoopEvaluationPermitted &&
                providerOperationNoopExecutionBoundarySuiteReport.syntheticNoopEvaluationPermitted &&
                providerOperationNoopExecutionBoundaryValidation.syntheticNoopEvaluationPermitted &&
                providerOperationNoopExecutionBoundary.syntheticNoopEvaluationPermitted
        val noopExecutionBoundaryValidationPassed =
            traceAdmission.noopExecutionBoundaryValidationPassed &&
                providerOperationNoopExecutionBoundarySuiteReport.noopExecutionBoundaryValidationPassed &&
                providerOperationNoopExecutionBoundaryValidation.allValidationChecksPassed
        val noopExecutionBoundarySuitePassed =
            traceAdmission.noopExecutionBoundarySuitePassed &&
                providerOperationNoopExecutionBoundarySuiteReport.noopExecutionBoundarySuitePassed
        val futureSyntheticTraceCriteriaModeled = traceAdmission.futureSyntheticTraceCriteriaModeled

        val syntheticTracePayloadFree = true
        val syntheticTracePayloadPresent = false
        val syntheticTraceContainsRawBytes = false
        val syntheticTraceContainsHex = false
        val syntheticTraceContainsProviderHandles = false
        val syntheticTraceContainsCryptoObjects = false
        val syntheticTraceContainsWalletMaterial = false
        val syntheticTraceContainsEndpointMaterial = false
        val syntheticTraceContainsSourceLocation = false
        val syntheticTraceContainsDiagnosticsPayload = false
        val syntheticTraceContainsAnalyticsPayload = false
        val syntheticTraceContainsCrashReportPayload = false
        val syntheticTraceContainsSupportExportPayload = false

        val traceIsCommonTestOnly =
            traceAdmission.admissionIsCommonTestOnly &&
                providerOperationNoopExecutionBoundarySuiteReport.suiteIsCommonTestOnly &&
                providerOperationNoopExecutionBoundaryValidation.validationIsCommonTestOnly &&
                providerOperationNoopExecutionBoundary.boundaryIsCommonTestOnly &&
                providerOperationNoopKatSuiteReport.suiteIsCommonTestOnly &&
                providerOperationNoopKatValidation.validationIsCommonTestOnly &&
                providerOperationNoopKat.noopKatIsCommonTestOnly &&
                providerOperationNoopKatAdmission.admissionIsCommonTestOnly &&
                providerOperationMetadataKatSuiteReport.suiteIsCommonTestOnly &&
                capabilityMatrix.matrixIsCommonTestOnly &&
                marker.sourceSet == SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet.CommonTest
        val traceIsProductionAuthorization =
            traceAdmission.admissionIsProductionAuthorization ||
                providerOperationNoopExecutionBoundarySuiteReport.suiteIsProductionAuthorization ||
                providerOperationNoopExecutionBoundaryValidation.validationIsProductionAuthorization ||
                providerOperationNoopExecutionBoundary.boundaryIsProductionAuthorization ||
                providerOperationNoopKatSuiteReport.suiteIsProductionAuthorization ||
                providerOperationNoopKatValidation.validationIsProductionAuthorization ||
                providerOperationNoopKat.noopKatIsProductionAuthorization ||
                providerOperationNoopKatAdmission.admissionIsProductionAuthorization ||
                providerOperationMetadataKatSuiteReport.suiteIsProductionAuthorization
        val traceIsProviderSelectionAuthorization =
            traceAdmission.admissionIsProviderSelectionAuthorization ||
                providerOperationNoopExecutionBoundarySuiteReport.suiteIsProviderSelectionAuthorization ||
                providerOperationNoopExecutionBoundaryValidation.validationIsProviderSelectionAuthorization ||
                providerOperationNoopExecutionBoundary.boundaryIsProviderSelectionAuthorization ||
                providerOperationNoopKatSuiteReport.suiteIsProviderSelectionAuthorization ||
                providerOperationNoopKatValidation.validationIsProviderSelectionAuthorization ||
                providerOperationNoopKat.noopKatIsProviderSelectionAuthorization ||
                providerOperationNoopKatAdmission.admissionIsProviderSelectionAuthorization ||
                providerOperationMetadataKatSuiteReport.suiteIsProviderSelectionAuthorization
        val traceIsProviderOperationAuthorization =
            traceAdmission.admissionIsProviderOperationAuthorization ||
                providerOperationNoopExecutionBoundarySuiteReport.suiteIsProviderOperationAuthorization ||
                providerOperationNoopExecutionBoundaryValidation.validationIsProviderOperationAuthorization ||
                providerOperationNoopExecutionBoundary.boundaryIsProviderOperationAuthorization ||
                providerOperationNoopKatSuiteReport.suiteIsProviderOperationAuthorization ||
                providerOperationNoopKatValidation.validationIsProviderOperationAuthorization ||
                providerOperationNoopKat.noopKatIsProviderOperationAuthorization ||
                providerOperationNoopKatAdmission.admissionIsProviderOperationAuthorization ||
                providerOperationMetadataKatSuiteReport.suiteIsProviderOperationAuthorization
        val traceIsKatExecutorAuthorization =
            traceAdmission.admissionIsKatExecutorAuthorization ||
                providerOperationNoopExecutionBoundarySuiteReport.suiteIsKatExecutorAuthorization ||
                providerOperationNoopExecutionBoundaryValidation.validationIsKatExecutorAuthorization ||
                providerOperationNoopExecutionBoundary.boundaryIsKatExecutorAuthorization ||
                providerOperationNoopKatSuiteReport.suiteIsKatExecutorAuthorization ||
                providerOperationNoopKatValidation.validationIsKatExecutorAuthorization ||
                providerOperationNoopKat.noopKatIsKatExecutorAuthorization ||
                providerOperationNoopKatAdmission.admissionIsKatExecutorAuthorization ||
                providerOperationMetadataKatSuiteReport.suiteIsKatExecutorAuthorization
        val traceIsCryptoAuthorization =
            traceAdmission.admissionIsCryptoAuthorization ||
                providerOperationNoopExecutionBoundarySuiteReport.suiteIsCryptoAuthorization ||
                providerOperationNoopExecutionBoundaryValidation.validationIsCryptoAuthorization ||
                providerOperationNoopExecutionBoundary.boundaryIsCryptoAuthorization ||
                providerOperationNoopKatSuiteReport.suiteIsCryptoAuthorization ||
                providerOperationNoopKatValidation.validationIsCryptoAuthorization ||
                providerOperationNoopKat.noopKatIsCryptoAuthorization ||
                providerOperationNoopKatAdmission.admissionIsCryptoAuthorization ||
                providerOperationMetadataKatSuiteReport.suiteIsCryptoAuthorization
        val traceIsVaultPersistenceAuthorization =
            traceAdmission.admissionIsVaultPersistenceAuthorization ||
                providerOperationNoopExecutionBoundarySuiteReport.suiteIsVaultPersistenceAuthorization ||
                providerOperationNoopExecutionBoundaryValidation.validationIsVaultPersistenceAuthorization ||
                providerOperationNoopExecutionBoundary.boundaryIsVaultPersistenceAuthorization ||
                providerOperationNoopKatSuiteReport.suiteIsVaultPersistenceAuthorization ||
                providerOperationNoopKatValidation.validationIsVaultPersistenceAuthorization ||
                providerOperationNoopKat.noopKatIsVaultPersistenceAuthorization ||
                providerOperationNoopKatAdmission.admissionIsVaultPersistenceAuthorization ||
                providerOperationMetadataKatSuiteReport.suiteIsVaultPersistenceAuthorization
        val traceIsMainnetAuthorization =
            traceAdmission.admissionIsMainnetAuthorization ||
                providerOperationNoopExecutionBoundarySuiteReport.suiteIsMainnetAuthorization ||
                providerOperationNoopExecutionBoundaryValidation.validationIsMainnetAuthorization ||
                providerOperationNoopExecutionBoundary.boundaryIsMainnetAuthorization ||
                providerOperationNoopKatSuiteReport.suiteIsMainnetAuthorization ||
                providerOperationNoopKatValidation.validationIsMainnetAuthorization ||
                providerOperationNoopKat.noopKatIsMainnetAuthorization ||
                providerOperationNoopKatAdmission.admissionIsMainnetAuthorization ||
                providerOperationMetadataKatSuiteReport.suiteIsMainnetAuthorization

        val realProviderOperationExecutionPermitted =
            traceAdmission.realProviderOperationExecutionPermitted ||
                providerOperationNoopExecutionBoundarySuiteReport.realProviderOperationExecutionPermitted ||
                providerOperationNoopExecutionBoundaryValidation.realProviderOperationExecutionPermitted ||
                providerOperationNoopExecutionBoundary.realProviderOperationExecutionPermitted
        val cryptoExecutionPermitted =
            traceAdmission.cryptoExecutionPermitted ||
                providerOperationNoopExecutionBoundarySuiteReport.cryptoExecutionPermitted ||
                providerOperationNoopExecutionBoundaryValidation.cryptoExecutionPermitted ||
                providerOperationNoopExecutionBoundary.cryptoExecutionPermitted
        val katRunnerPermitted =
            traceAdmission.katRunnerPermitted ||
                providerOperationNoopExecutionBoundarySuiteReport.katRunnerPermitted ||
                providerOperationNoopExecutionBoundaryValidation.katRunnerPermitted ||
                providerOperationNoopExecutionBoundary.katRunnerPermitted
        val katExecutorPermitted =
            traceAdmission.katExecutorPermitted ||
                providerOperationNoopExecutionBoundarySuiteReport.katExecutorPermitted ||
                providerOperationNoopExecutionBoundaryValidation.katExecutorPermitted ||
                providerOperationNoopExecutionBoundary.katExecutorPermitted
        val vaultPersistencePermitted =
            traceAdmission.vaultPersistencePermitted ||
                providerOperationNoopExecutionBoundarySuiteReport.vaultPersistencePermitted ||
                providerOperationNoopExecutionBoundaryValidation.vaultPersistencePermitted ||
                providerOperationNoopExecutionBoundary.vaultPersistencePermitted
        val mainnetPermitted =
            traceAdmission.mainnetPermitted ||
                providerOperationNoopExecutionBoundarySuiteReport.mainnetPermitted ||
                providerOperationNoopExecutionBoundaryValidation.mainnetPermitted ||
                providerOperationNoopExecutionBoundary.mainnetPermitted

        val providerOperationKatExecutorPresent =
            traceAdmission.currentKatExecutorPresent ||
                providerOperationNoopExecutionBoundarySuiteReport.providerOperationKatExecutorPresent ||
                providerOperationNoopExecutionBoundaryValidation.providerOperationKatExecutorPresent ||
                providerOperationNoopExecutionBoundary.providerOperationKatExecutorPresent ||
                providerOperationNoopKatSuiteReport.providerOperationKatExecutorPresent ||
                providerOperationNoopKatValidation.providerOperationKatExecutorPresent ||
                providerOperationNoopKat.providerOperationKatExecutorPresent ||
                providerOperationNoopKatAdmission.currentKatExecutorPresent ||
                providerOperationMetadataKatSuiteReport.providerOperationKatExecutorPresent
        val providerOperationKatRunnerPresent =
            traceAdmission.currentKatRunnerPresent ||
                providerOperationNoopExecutionBoundarySuiteReport.providerOperationKatRunnerPresent ||
                providerOperationNoopExecutionBoundaryValidation.providerOperationKatRunnerPresent ||
                providerOperationNoopExecutionBoundary.providerOperationKatRunnerPresent ||
                providerOperationNoopKatSuiteReport.providerOperationKatRunnerPresent ||
                providerOperationNoopKatValidation.providerOperationKatRunnerPresent ||
                providerOperationNoopKat.providerOperationKatRunnerPresent ||
                providerOperationNoopKatAdmission.currentKatRunnerPresent ||
                providerOperationMetadataKatSuiteReport.providerOperationKatRunnerPresent
        val providerOperationExecutionPresent =
            traceAdmission.currentNoopProviderOperationExecutionPresent ||
                traceAdmission.currentRealProviderOperationExecutionPresent ||
                providerOperationNoopExecutionBoundarySuiteReport.providerOperationExecutionPresent ||
                providerOperationNoopExecutionBoundaryValidation.providerOperationExecutionPresent ||
                providerOperationNoopExecutionBoundary.providerOperationExecutionPresent ||
                providerOperationNoopKatSuiteReport.providerOperationExecutionPresent ||
                providerOperationNoopKatValidation.providerOperationExecutionPresent ||
                providerOperationNoopKat.providerOperationExecutionPresent ||
                providerOperationNoopKatAdmission.currentNoopProviderOperationExecutionPresent ||
                providerOperationNoopKatAdmission.currentProviderOperationExecutionPresent ||
                providerOperationMetadataKatSuiteReport.providerOperationExecutionPresent ||
                realProviderOperationExecutionPermitted
        val cryptoExecutionPresent =
            traceAdmission.currentCryptoExecutionPresent ||
                providerOperationNoopExecutionBoundarySuiteReport.cryptoExecutionPresent ||
                providerOperationNoopExecutionBoundaryValidation.cryptoExecutionPresent ||
                providerOperationNoopExecutionBoundary.cryptoExecutionPresent ||
                providerOperationNoopKatSuiteReport.cryptoExecutionPresent ||
                providerOperationNoopKatValidation.cryptoExecutionPresent ||
                providerOperationNoopKat.cryptoExecutionPresent ||
                providerOperationNoopKatAdmission.currentCryptoExecutionPresent ||
                providerOperationMetadataKatSuiteReport.cryptoExecutionPresent ||
                cryptoExecutionPermitted

        val rawKatMaterialPresent =
            traceAdmission.rawKatMaterialPresent ||
                providerOperationNoopExecutionBoundarySuiteReport.rawKatMaterialPresent ||
                providerOperationNoopExecutionBoundaryValidation.rawKatMaterialPresent ||
                providerOperationNoopExecutionBoundary.rawKatMaterialPresent ||
                providerOperationNoopKatSuiteReport.rawKatMaterialPresent ||
                providerOperationNoopKatValidation.rawKatMaterialPresent ||
                providerOperationNoopKat.rawKatMaterialPresent ||
                providerOperationNoopKatAdmission.rawKatMaterialPresent ||
                providerOperationMetadataKatSuiteReport.rawKatMaterialPresent
        val rawVectorBytesPresent =
            traceAdmission.rawVectorBytesPresent ||
                providerOperationNoopExecutionBoundarySuiteReport.rawVectorBytesPresent ||
                providerOperationNoopExecutionBoundaryValidation.rawVectorBytesPresent ||
                providerOperationNoopExecutionBoundary.rawVectorBytesPresent ||
                providerOperationNoopKatSuiteReport.rawVectorBytesPresent ||
                providerOperationNoopKatValidation.rawVectorBytesPresent ||
                providerOperationNoopKat.rawVectorBytesPresent ||
                providerOperationNoopKatAdmission.rawVectorBytesPresent ||
                providerOperationMetadataKatSuiteReport.rawVectorBytesPresent
        val rawVectorHexPresent =
            traceAdmission.rawVectorHexPresent ||
                providerOperationNoopExecutionBoundarySuiteReport.rawVectorHexPresent ||
                providerOperationNoopExecutionBoundaryValidation.rawVectorHexPresent ||
                providerOperationNoopExecutionBoundary.rawVectorHexPresent ||
                providerOperationNoopKatSuiteReport.rawVectorHexPresent ||
                providerOperationNoopKatValidation.rawVectorHexPresent ||
                providerOperationNoopKat.rawVectorHexPresent ||
                providerOperationNoopKatAdmission.rawVectorHexPresent ||
                providerOperationMetadataKatSuiteReport.rawVectorHexPresent
        val publicVectorBytesPresent =
            traceAdmission.publicVectorBytesPresent ||
                providerOperationNoopExecutionBoundarySuiteReport.publicVectorBytesPresent ||
                providerOperationNoopExecutionBoundaryValidation.publicVectorBytesPresent ||
                providerOperationNoopExecutionBoundary.publicVectorBytesPresent ||
                providerOperationNoopKatSuiteReport.publicVectorBytesPresent ||
                providerOperationNoopKatValidation.publicVectorBytesPresent ||
                providerOperationNoopKat.publicVectorBytesPresent ||
                providerOperationNoopKatAdmission.publicVectorBytesPresent ||
                providerOperationMetadataKatSuiteReport.publicVectorBytesPresent
        val publicVectorHexPresent =
            traceAdmission.publicVectorHexPresent ||
                providerOperationNoopExecutionBoundarySuiteReport.publicVectorHexPresent ||
                providerOperationNoopExecutionBoundaryValidation.publicVectorHexPresent ||
                providerOperationNoopExecutionBoundary.publicVectorHexPresent ||
                providerOperationNoopKatSuiteReport.publicVectorHexPresent ||
                providerOperationNoopKatValidation.publicVectorHexPresent ||
                providerOperationNoopKat.publicVectorHexPresent ||
                providerOperationNoopKatAdmission.publicVectorHexPresent ||
                providerOperationMetadataKatSuiteReport.publicVectorHexPresent

        val runtimeSelectable = traceAdmission.runtimeSelectable || capabilityMatrix.runtimeSelectable
        val registrySelectable = traceAdmission.registrySelectable || capabilityMatrix.registrySelectable
        val factoryReachable = traceAdmission.factoryReachable || capabilityMatrix.factoryReachable
        val dispatcherReachable = traceAdmission.dispatcherReachable || capabilityMatrix.dispatcherReachable
        val executorTargetable = traceAdmission.executorTargetable || capabilityMatrix.executorTargetable
        val providerKatExecutorReachable =
            traceAdmission.providerKatExecutorReachable || capabilityMatrix.providerKatExecutorReachable
        val providerOperationReachable =
            traceAdmission.providerOperationReachable ||
                capabilityMatrix.providerOperationReachable ||
                providerOperationExecutionPresent ||
                realProviderOperationExecutionPermitted
        val cryptoExecutionReachable =
            traceAdmission.cryptoExecutionReachable ||
                capabilityMatrix.cryptoExecutionReachable ||
                cryptoExecutionPresent ||
                cryptoExecutionPermitted
        val vaultLifecycleReachable =
            traceAdmission.vaultLifecycleReachable || capabilityMatrix.vaultLifecycleReachable
        val persistenceReachable =
            traceAdmission.persistenceReachable ||
                capabilityMatrix.persistenceReachable ||
                vaultPersistencePermitted
        val productionSyncReachable =
            traceAdmission.productionSyncReachable || capabilityMatrix.productionSyncReachable
        val backendClientReachable =
            traceAdmission.backendClientReachable || capabilityMatrix.backendClientReachable
        val bdkWalletStateReachable =
            traceAdmission.bdkWalletStateReachable || capabilityMatrix.bdkWalletStateReachable
        val settingsCodecReachable =
            traceAdmission.settingsCodecReachable || capabilityMatrix.settingsCodecReachable
        val uiSurfaceReachable = traceAdmission.uiSurfaceReachable || capabilityMatrix.uiSurfaceReachable
        val signingBroadcastingReachable =
            traceAdmission.signingBroadcastingReachable || capabilityMatrix.signingBroadcastingReachable
        val publicEndpointReachable =
            traceAdmission.publicEndpointReachable || capabilityMatrix.publicEndpointReachable
        val mainnetReachable =
            traceAdmission.mainnetReachable || capabilityMatrix.mainnetReachable || mainnetPermitted
        val implementsVaultCryptoProvider =
            traceAdmission.implementsVaultCryptoProvider ||
                capabilityMatrix.implementsVaultCryptoProvider ||
                marker.implementsVaultCryptoProvider
        val containsVaultCryptoProvider =
            traceAdmission.containsVaultCryptoProvider ||
                capabilityMatrix.containsVaultCryptoProvider ||
                marker.vaultCryptoProviderInstanceExposed
        val canExecuteProviderOperations =
            traceAdmission.canExecuteProviderOperations ||
                capabilityMatrix.canExecuteProviderOperations ||
                providerOperationExecutionPresent ||
                realProviderOperationExecutionPermitted
        val canExecuteCrypto =
            traceAdmission.canExecuteCrypto ||
                capabilityMatrix.canExecuteCrypto ||
                cryptoExecutionPresent ||
                cryptoExecutionPermitted
        val canUseForVaultLifecycle =
            traceAdmission.canUseForVaultLifecycle || capabilityMatrix.canUseForVaultLifecycle
        val canUseForPersistence =
            traceAdmission.canUseForPersistence ||
                capabilityMatrix.canUseForPersistence ||
                vaultPersistencePermitted
        val canUseForSync = traceAdmission.canUseForSync || capabilityMatrix.canUseForSync
        val canUseForSigning = traceAdmission.canUseForSigning || capabilityMatrix.canUseForSigning
        val canUseForBroadcasting =
            traceAdmission.canUseForBroadcasting || capabilityMatrix.canUseForBroadcasting
        val canUseForMainnet =
            traceAdmission.canUseForMainnet || capabilityMatrix.canUseForMainnet || mainnetPermitted
        val productionProviderSelectable =
            traceAdmission.productionProviderSelectable ||
                capabilityMatrix.productionProviderSelectable ||
                marker.productionProviderSelectable

        val traceReportsSyntheticNoopOnly =
            futureSyntheticTraceCriteriaModeled &&
                providerOperationNoopExecutionBoundarySuiteReport.suiteReportsSyntheticNoopOnly &&
                providerOperationNoopExecutionBoundaryValidation.validatesSyntheticNoopOnly &&
                providerOperationNoopExecutionBoundary.evaluatesSyntheticNoopOnly &&
                providerOperationNoopKatSuiteReport.suiteReportsSyntheticNoopOnly &&
                providerOperationNoopKatValidation.validatesSyntheticNoopOnly &&
                providerOperationNoopKat.evaluatesSyntheticNoopOnly &&
                syntheticNoopResultPresent
        val traceReportsProviderOperationExecution =
            providerOperationNoopExecutionBoundarySuiteReport.suiteReportsProviderOperationExecution ||
                providerOperationNoopExecutionBoundaryValidation.validatesProviderOperationExecution ||
                providerOperationNoopExecutionBoundary.evaluatesProviderOperationExecution ||
                providerOperationNoopKatSuiteReport.suiteReportsProviderOperationExecution ||
                providerOperationNoopKatValidation.validatesProviderOperationExecution ||
                providerOperationNoopKat.evaluatesProviderOperationExecution ||
                providerOperationExecutionPresent ||
                realProviderOperationExecutionPermitted
        val traceReportsCryptoOperation =
            providerOperationNoopExecutionBoundarySuiteReport.suiteReportsCryptoOperation ||
                providerOperationNoopExecutionBoundaryValidation.validatesCryptoOperation ||
                providerOperationNoopExecutionBoundary.evaluatesCryptoOperation ||
                providerOperationNoopKatSuiteReport.suiteReportsCryptoOperation ||
                providerOperationNoopKatValidation.validatesCryptoOperation ||
                providerOperationNoopKat.evaluatesCryptoOperation ||
                cryptoExecutionPresent ||
                cryptoExecutionPermitted
        val traceReportsVaultLifecycle =
            providerOperationNoopExecutionBoundarySuiteReport.suiteReportsVaultLifecycle ||
                providerOperationNoopExecutionBoundaryValidation.validatesVaultLifecycle ||
                providerOperationNoopExecutionBoundary.evaluatesVaultLifecycle ||
                providerOperationNoopKatSuiteReport.suiteReportsVaultLifecycle ||
                providerOperationNoopKatValidation.validatesVaultLifecycle ||
                providerOperationNoopKat.evaluatesVaultLifecycle ||
                vaultLifecycleReachable
        val traceReportsPersistence =
            providerOperationNoopExecutionBoundarySuiteReport.suiteReportsPersistence ||
                providerOperationNoopExecutionBoundaryValidation.validatesPersistence ||
                providerOperationNoopExecutionBoundary.evaluatesPersistence ||
                providerOperationNoopKatSuiteReport.suiteReportsPersistence ||
                providerOperationNoopKatValidation.validatesPersistence ||
                providerOperationNoopKat.evaluatesPersistence ||
                persistenceReachable ||
                vaultPersistencePermitted

        val safeOutputRedactionSatisfied =
            sequenceOf(
                marker.toString(),
                marker.safeId.toString(),
                capabilityMatrix.toString(),
                providerOperationMetadataKatSuiteReport.toString(),
                providerOperationNoopKatAdmission.toString(),
                providerOperationNoopKatAdmission.displayLabel.toString(),
                providerOperationNoopKat.toString(),
                providerOperationNoopKat.displayLabel.toString(),
                providerOperationNoopKatValidation.toString(),
                providerOperationNoopKatValidation.displayLabel.toString(),
                providerOperationNoopKatSuiteReport.toString(),
                providerOperationNoopKatSuiteReport.displayLabel.toString(),
                providerOperationNoopExecutionBoundary.toString(),
                providerOperationNoopExecutionBoundary.displayLabel.toString(),
                providerOperationNoopExecutionBoundaryValidation.toString(),
                providerOperationNoopExecutionBoundaryValidation.displayLabel.toString(),
                providerOperationNoopExecutionBoundarySuiteReport.toString(),
                providerOperationNoopExecutionBoundarySuiteReport.displayLabel.toString(),
                traceAdmission.toString(),
                traceAdmission.displayLabel.toString(),
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSafeLabel(
                    "payload-free synthetic provider-operation trace evidence",
                ).toString(),
            ).none { output ->
                EXPECTED_SAFE_ID in output ||
                    EXPECTED_FIXTURE_ID in output ||
                    EXPECTED_VECTOR_ID in output ||
                    EXPECTED_CASE_ID in output ||
                    EXPECTED_PROVIDER_OPERATION_METADATA_KAT_ID in output ||
                    EXPECTED_PROVIDER_OPERATION_NOOP_KAT_ID in output ||
                    EXPECTED_PROVIDER_OPERATION_NOOP_EXECUTION_BOUNDARY_ID in output ||
                    EXPECTED_PROVIDER_OPERATION_NOOP_EXECUTION_BOUNDARY_SUITE_REPORT_ID in output ||
                    EXPECTED_PROVIDER_OPERATION_SYNTHETIC_TRACE_ID in output
            }

        val syntheticTraceCreated =
            traceIsCommonTestOnly &&
                markerCount == 1 &&
                caseBindingCount == 1 &&
                providerOperationMetadataKatCount == 1 &&
                providerOperationNoopKatCount == 1 &&
                providerOperationNoopKatValidationCount == 1 &&
                providerOperationNoopKatSuiteReportCount == 1 &&
                providerOperationNoopExecutionBoundaryCount == 1 &&
                providerOperationNoopExecutionBoundaryValidationCount == 1 &&
                providerOperationNoopExecutionBoundarySuiteReportCount == 1 &&
                providerOperationSyntheticTraceAdmissionCount == 1 &&
                expectedSafeIdMatched &&
                expectedFixtureIdMatched &&
                expectedVectorIdMatched &&
                expectedCaseIdMatched &&
                expectedProviderOperationMetadataKatIdMatched &&
                expectedProviderOperationNoopKatIdMatched &&
                expectedProviderOperationNoopExecutionBoundaryIdMatched &&
                expectedProviderOperationNoopExecutionBoundarySuiteReportIdMatched &&
                expectedProviderOperationSyntheticTraceIdMatched &&
                providerOperationNoopKatPassed &&
                providerOperationNoopKatValidationPassed &&
                providerOperationNoopKatSuitePassed &&
                syntheticNoopResultPresent &&
                noopExecutionBoundaryModeled &&
                syntheticNoopEvaluationPermitted &&
                noopExecutionBoundaryValidationPassed &&
                noopExecutionBoundarySuitePassed &&
                futureSyntheticTraceCriteriaModeled &&
                syntheticTracePayloadFree &&
                !syntheticTracePayloadPresent &&
                !syntheticTraceContainsRawBytes &&
                !syntheticTraceContainsHex &&
                !syntheticTraceContainsProviderHandles &&
                !syntheticTraceContainsCryptoObjects &&
                !syntheticTraceContainsWalletMaterial &&
                !syntheticTraceContainsEndpointMaterial &&
                !syntheticTraceContainsSourceLocation &&
                !syntheticTraceContainsDiagnosticsPayload &&
                !syntheticTraceContainsAnalyticsPayload &&
                !syntheticTraceContainsCrashReportPayload &&
                !syntheticTraceContainsSupportExportPayload &&
                traceEvents.size ==
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceEvent.entries.size &&
                traceReportsSyntheticNoopOnly &&
                safeOutputRedactionSatisfied &&
                !traceIsProductionAuthorization &&
                !traceIsProviderSelectionAuthorization &&
                !traceIsProviderOperationAuthorization &&
                !traceIsKatExecutorAuthorization &&
                !traceIsCryptoAuthorization &&
                !traceIsVaultPersistenceAuthorization &&
                !traceIsMainnetAuthorization &&
                !traceReportsProviderOperationExecution &&
                !traceReportsCryptoOperation &&
                !traceReportsVaultLifecycle &&
                !traceReportsPersistence &&
                !realProviderOperationExecutionPermitted &&
                !cryptoExecutionPermitted &&
                !katRunnerPermitted &&
                !katExecutorPermitted &&
                !vaultPersistencePermitted &&
                !mainnetPermitted &&
                !providerOperationKatExecutorPresent &&
                !providerOperationKatRunnerPresent &&
                !providerOperationExecutionPresent &&
                !cryptoExecutionPresent &&
                !rawKatMaterialPresent &&
                !rawVectorBytesPresent &&
                !rawVectorHexPresent &&
                !publicVectorBytesPresent &&
                !publicVectorHexPresent &&
                !runtimeSelectable &&
                !registrySelectable &&
                !factoryReachable &&
                !dispatcherReachable &&
                !executorTargetable &&
                !providerKatExecutorReachable &&
                !providerOperationReachable &&
                !cryptoExecutionReachable &&
                !vaultLifecycleReachable &&
                !persistenceReachable &&
                !productionSyncReachable &&
                !backendClientReachable &&
                !bdkWalletStateReachable &&
                !settingsCodecReachable &&
                !uiSurfaceReachable &&
                !signingBroadcastingReachable &&
                !publicEndpointReachable &&
                !mainnetReachable &&
                !implementsVaultCryptoProvider &&
                !containsVaultCryptoProvider &&
                !canExecuteProviderOperations &&
                !canExecuteCrypto &&
                !canUseForVaultLifecycle &&
                !canUseForPersistence &&
                !canUseForSync &&
                !canUseForSigning &&
                !canUseForBroadcasting &&
                !canUseForMainnet &&
                !productionProviderSelectable

        return SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTrace(
            markerCount = markerCount,
            caseBindingCount = caseBindingCount,
            providerOperationMetadataKatCount = providerOperationMetadataKatCount,
            providerOperationNoopKatCount = providerOperationNoopKatCount,
            providerOperationNoopKatValidationCount = providerOperationNoopKatValidationCount,
            providerOperationNoopKatSuiteReportCount = providerOperationNoopKatSuiteReportCount,
            providerOperationNoopExecutionBoundaryCount = providerOperationNoopExecutionBoundaryCount,
            providerOperationNoopExecutionBoundaryValidationCount =
                providerOperationNoopExecutionBoundaryValidationCount,
            providerOperationNoopExecutionBoundarySuiteReportCount =
                providerOperationNoopExecutionBoundarySuiteReportCount,
            providerOperationSyntheticTraceAdmissionCount = providerOperationSyntheticTraceAdmissionCount,
            expectedSafeIdMatched = expectedSafeIdMatched,
            expectedFixtureIdMatched = expectedFixtureIdMatched,
            expectedVectorIdMatched = expectedVectorIdMatched,
            expectedCaseIdMatched = expectedCaseIdMatched,
            expectedProviderOperationMetadataKatIdMatched = expectedProviderOperationMetadataKatIdMatched,
            expectedProviderOperationNoopKatIdMatched = expectedProviderOperationNoopKatIdMatched,
            expectedProviderOperationNoopExecutionBoundaryIdMatched =
                expectedProviderOperationNoopExecutionBoundaryIdMatched,
            expectedProviderOperationNoopExecutionBoundarySuiteReportIdMatched =
                expectedProviderOperationNoopExecutionBoundarySuiteReportIdMatched,
            expectedProviderOperationSyntheticTraceIdMatched = expectedProviderOperationSyntheticTraceIdMatched,
            providerOperationNoopKatPassed = providerOperationNoopKatPassed,
            providerOperationNoopKatValidationPassed = providerOperationNoopKatValidationPassed,
            providerOperationNoopKatSuitePassed = providerOperationNoopKatSuitePassed,
            syntheticNoopResultPresent = syntheticNoopResultPresent,
            noopExecutionBoundaryModeled = noopExecutionBoundaryModeled,
            syntheticNoopEvaluationPermitted = syntheticNoopEvaluationPermitted,
            noopExecutionBoundaryValidationPassed = noopExecutionBoundaryValidationPassed,
            noopExecutionBoundarySuitePassed = noopExecutionBoundarySuitePassed,
            futureSyntheticTraceCriteriaModeled = futureSyntheticTraceCriteriaModeled,
            syntheticTraceCreated = syntheticTraceCreated,
            syntheticTracePayloadFree = syntheticTracePayloadFree,
            syntheticTracePayloadPresent = syntheticTracePayloadPresent,
            syntheticTraceContainsRawBytes = syntheticTraceContainsRawBytes,
            syntheticTraceContainsHex = syntheticTraceContainsHex,
            syntheticTraceContainsProviderHandles = syntheticTraceContainsProviderHandles,
            syntheticTraceContainsCryptoObjects = syntheticTraceContainsCryptoObjects,
            syntheticTraceContainsWalletMaterial = syntheticTraceContainsWalletMaterial,
            syntheticTraceContainsEndpointMaterial = syntheticTraceContainsEndpointMaterial,
            syntheticTraceContainsSourceLocation = syntheticTraceContainsSourceLocation,
            syntheticTraceContainsDiagnosticsPayload = syntheticTraceContainsDiagnosticsPayload,
            syntheticTraceContainsAnalyticsPayload = syntheticTraceContainsAnalyticsPayload,
            syntheticTraceContainsCrashReportPayload = syntheticTraceContainsCrashReportPayload,
            syntheticTraceContainsSupportExportPayload = syntheticTraceContainsSupportExportPayload,
            traceIsCommonTestOnly = traceIsCommonTestOnly,
            traceIsProductionAuthorization = traceIsProductionAuthorization,
            traceIsProviderSelectionAuthorization = traceIsProviderSelectionAuthorization,
            traceIsProviderOperationAuthorization = traceIsProviderOperationAuthorization,
            traceIsKatExecutorAuthorization = traceIsKatExecutorAuthorization,
            traceIsCryptoAuthorization = traceIsCryptoAuthorization,
            traceIsVaultPersistenceAuthorization = traceIsVaultPersistenceAuthorization,
            traceIsMainnetAuthorization = traceIsMainnetAuthorization,
            traceReportsSyntheticNoopOnly = traceReportsSyntheticNoopOnly,
            traceReportsProviderOperationExecution = traceReportsProviderOperationExecution,
            traceReportsCryptoOperation = traceReportsCryptoOperation,
            traceReportsVaultLifecycle = traceReportsVaultLifecycle,
            traceReportsPersistence = traceReportsPersistence,
            realProviderOperationExecutionPermitted = realProviderOperationExecutionPermitted,
            cryptoExecutionPermitted = cryptoExecutionPermitted,
            katRunnerPermitted = katRunnerPermitted,
            katExecutorPermitted = katExecutorPermitted,
            vaultPersistencePermitted = vaultPersistencePermitted,
            mainnetPermitted = mainnetPermitted,
            providerOperationKatExecutorPresent = providerOperationKatExecutorPresent,
            providerOperationKatRunnerPresent = providerOperationKatRunnerPresent,
            providerOperationExecutionPresent = providerOperationExecutionPresent,
            cryptoExecutionPresent = cryptoExecutionPresent,
            rawKatMaterialPresent = rawKatMaterialPresent,
            rawVectorBytesPresent = rawVectorBytesPresent,
            rawVectorHexPresent = rawVectorHexPresent,
            publicVectorBytesPresent = publicVectorBytesPresent,
            publicVectorHexPresent = publicVectorHexPresent,
            runtimeSelectable = runtimeSelectable,
            registrySelectable = registrySelectable,
            factoryReachable = factoryReachable,
            dispatcherReachable = dispatcherReachable,
            executorTargetable = executorTargetable,
            providerKatExecutorReachable = providerKatExecutorReachable,
            providerOperationReachable = providerOperationReachable,
            cryptoExecutionReachable = cryptoExecutionReachable,
            vaultLifecycleReachable = vaultLifecycleReachable,
            persistenceReachable = persistenceReachable,
            productionSyncReachable = productionSyncReachable,
            backendClientReachable = backendClientReachable,
            bdkWalletStateReachable = bdkWalletStateReachable,
            settingsCodecReachable = settingsCodecReachable,
            uiSurfaceReachable = uiSurfaceReachable,
            signingBroadcastingReachable = signingBroadcastingReachable,
            publicEndpointReachable = publicEndpointReachable,
            mainnetReachable = mainnetReachable,
            implementsVaultCryptoProvider = implementsVaultCryptoProvider,
            containsVaultCryptoProvider = containsVaultCryptoProvider,
            canExecuteProviderOperations = canExecuteProviderOperations,
            canExecuteCrypto = canExecuteCrypto,
            canUseForVaultLifecycle = canUseForVaultLifecycle,
            canUseForPersistence = canUseForPersistence,
            canUseForSync = canUseForSync,
            canUseForSigning = canUseForSigning,
            canUseForBroadcasting = canUseForBroadcasting,
            canUseForMainnet = canUseForMainnet,
            productionProviderSelectable = productionProviderSelectable,
            traceEvents = traceEvents,
            sourceSet = SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSourceSet.CommonTest,
            displayLabel = SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSafeLabel(
                "payload-free synthetic provider-operation trace evidence",
            ),
        )
    }
}
