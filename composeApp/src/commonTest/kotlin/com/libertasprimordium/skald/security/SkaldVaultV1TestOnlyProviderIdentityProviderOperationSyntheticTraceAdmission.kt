package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceAdmissionSafeLabel(
    val value: String,
) {
    override fun toString(): String =
        "RedactedTestOnlyProviderIdentityProviderOperationSyntheticTraceAdmissionSafeLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceAdmissionSourceSet {
    CommonTest,
}

enum class SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceAdmissionOutcome {
    SyntheticTraceAdmissionModeled,
    FutureSyntheticTraceRequiresSeparateBranch,
    CurrentSyntheticTraceNotPresent,
    CurrentTracePayloadAbsent,
    CurrentNoopProviderOperationExecutionAbsent,
    CurrentRealProviderOperationExecutionAbsent,
    CurrentCryptoExecutionAbsent,
    CurrentKatRunnerAbsent,
    CurrentKatExecutorAbsent,
    ProviderSelectionAuthorizationAbsent,
    ProductionAuthorizationAbsent,
    ProviderOperationAuthorizationAbsent,
    KatExecutorAuthorizationAbsent,
    CryptoAuthorizationAbsent,
    VaultPersistenceAuthorizationAbsent,
    MainnetAuthorizationAbsent,
}

enum class SkaldVaultV1TestOnlyProviderIdentityFutureSyntheticTraceCriterion {
    FutureBranchMustBeExplicitlyApproved,
    FutureSyntheticTraceMustRemainCommonTestOnly,
    FutureSyntheticTraceMustUseNoopExecutionBoundarySuiteOnly,
    FutureSyntheticTraceMustReadFixedSyntheticNoopResultOnly,
    FutureSyntheticTraceMustNotContainPayloadBytes,
    FutureSyntheticTraceMustNotContainHex,
    FutureSyntheticTraceMustNotContainProviderHandles,
    FutureSyntheticTraceMustNotContainCryptoObjects,
    FutureSyntheticTraceMustNotContainWalletMaterial,
    FutureSyntheticTraceMustNotContainEndpointMaterial,
    FutureSyntheticTraceMustNotImplementVaultCryptoProvider,
    FutureSyntheticTraceMustNotUseVaultCryptoProviderInstance,
    FutureSyntheticTraceMustNotUseProviderSelection,
    FutureSyntheticTraceMustNotUseRegistry,
    FutureSyntheticTraceMustNotUseFactory,
    FutureSyntheticTraceMustNotUseDispatcher,
    FutureSyntheticTraceMustNotUseExecutorTarget,
    FutureSyntheticTraceMustNotRunKdfHkdfHmacAead,
    FutureSyntheticTraceMustNotTouchVaultLifecycle,
    FutureSyntheticTraceMustNotTouchPersistence,
    FutureSyntheticTraceMustNotTouchBackendBdkSettingsUi,
    FutureSyntheticTraceMustNotSignOrBroadcast,
    FutureSyntheticTraceMustNotEnableMainnet,
    FutureSyntheticTraceMustRemainCoveredBySourceGuards,
    FutureSyntheticTraceMustRemainRedactedInOutput,
}

enum class SkaldVaultV1TestOnlyProviderIdentityForbiddenCurrentSyntheticTraceState {
    SyntheticTracePresent,
    TracePayloadPresent,
    NoopProviderOperationExecutionPresent,
    RealProviderOperationExecutionPresent,
    CryptoExecutionPresent,
    KatRunnerPresent,
    KatExecutorPresent,
    ProviderSelectionAuthorizationPresent,
    ProductionAuthorizationPresent,
    ProviderOperationAuthorizationPresent,
    KatExecutorAuthorizationPresent,
    CryptoAuthorizationPresent,
    VaultPersistenceAuthorizationPresent,
    MainnetAuthorizationPresent,
}

data class SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceAdmission(
    val markerCount: Int,
    val caseBindingCount: Int,
    val providerOperationMetadataKatCount: Int,
    val providerOperationNoopKatCount: Int,
    val providerOperationNoopKatValidationCount: Int,
    val providerOperationNoopKatSuiteReportCount: Int,
    val providerOperationNoopExecutionBoundaryCount: Int,
    val providerOperationNoopExecutionBoundaryValidationCount: Int,
    val providerOperationNoopExecutionBoundarySuiteReportCount: Int,
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
    val futureSyntheticTraceCriteriaAuthorizeCurrentTrace: Boolean,
    val admissionIsCommonTestOnly: Boolean,
    val admissionIsProductionAuthorization: Boolean,
    val admissionIsProviderSelectionAuthorization: Boolean,
    val admissionIsProviderOperationAuthorization: Boolean,
    val admissionIsKatExecutorAuthorization: Boolean,
    val admissionIsCryptoAuthorization: Boolean,
    val admissionIsVaultPersistenceAuthorization: Boolean,
    val admissionIsMainnetAuthorization: Boolean,
    val currentSyntheticTracePresent: Boolean,
    val currentTracePayloadPresent: Boolean,
    val currentNoopProviderOperationExecutionPresent: Boolean,
    val currentRealProviderOperationExecutionPresent: Boolean,
    val currentCryptoExecutionPresent: Boolean,
    val currentKatRunnerPresent: Boolean,
    val currentKatExecutorPresent: Boolean,
    val realProviderOperationExecutionPermitted: Boolean,
    val cryptoExecutionPermitted: Boolean,
    val katRunnerPermitted: Boolean,
    val katExecutorPermitted: Boolean,
    val vaultPersistencePermitted: Boolean,
    val mainnetPermitted: Boolean,
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
    val admissionOutcomes:
        List<SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceAdmissionOutcome>,
    val futureSyntheticTraceCriteria:
        List<SkaldVaultV1TestOnlyProviderIdentityFutureSyntheticTraceCriterion>,
    val forbiddenCurrentSyntheticTraceStates:
        List<SkaldVaultV1TestOnlyProviderIdentityForbiddenCurrentSyntheticTraceState>,
    val sourceSet: SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceAdmissionSourceSet,
    val displayLabel:
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceAdmissionSafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceAdmission(redactedMarkerId, redactedFixtureId, redactedVectorId, redactedCaseId, redactedProviderOperationMetadataKatId, redactedProviderOperationNoopKatId, redactedBoundaryId, redactedBoundarySuiteReportId, redactedSyntheticTraceId, commonTestOnly, admissionOnly, nonAuthorizing)"
}

object SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceAdmissionPolicy {
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

    private val currentAdmission:
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceAdmission by lazy {
            buildProviderOperationSyntheticTraceAdmission()
        }

    fun currentProviderOperationSyntheticTraceAdmission():
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceAdmission =
        currentAdmission

    private fun buildProviderOperationSyntheticTraceAdmission():
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceAdmission {
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
        val admissionOutcomes =
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceAdmissionOutcome.entries.toList()
        val futureCriteria =
            SkaldVaultV1TestOnlyProviderIdentityFutureSyntheticTraceCriterion.entries.toList()
        val forbiddenStates =
            SkaldVaultV1TestOnlyProviderIdentityForbiddenCurrentSyntheticTraceState.entries.toList()

        val markerCount =
            if (
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
        val caseBindingCount = providerOperationNoopExecutionBoundarySuiteReport.caseBindingCount
        val providerOperationMetadataKatCount =
            providerOperationNoopExecutionBoundarySuiteReport.providerOperationMetadataKatCount
        val providerOperationNoopKatCount =
            providerOperationNoopExecutionBoundarySuiteReport.providerOperationNoopKatCount
        val providerOperationNoopKatValidationCount =
            providerOperationNoopExecutionBoundarySuiteReport.providerOperationNoopKatValidationCount
        val providerOperationNoopKatSuiteReportCount =
            providerOperationNoopExecutionBoundarySuiteReport.providerOperationNoopKatSuiteReportCount
        val providerOperationNoopExecutionBoundaryCount =
            providerOperationNoopExecutionBoundarySuiteReport.providerOperationNoopExecutionBoundaryCount
        val providerOperationNoopExecutionBoundaryValidationCount =
            providerOperationNoopExecutionBoundarySuiteReport.providerOperationNoopExecutionBoundaryValidationCount
        val providerOperationNoopExecutionBoundarySuiteReportCount =
            if (
                providerOperationNoopExecutionBoundarySuiteReport.suiteReportGenerated &&
                providerOperationNoopExecutionBoundarySuiteReport.noopExecutionBoundarySuitePassed
            ) {
                1
            } else {
                0
            }

        val expectedSafeIdMatched =
            markerCount == 1 &&
                providerOperationNoopExecutionBoundarySuiteReport.expectedSafeIdMatched &&
                providerOperationNoopExecutionBoundaryValidation.expectedSafeIdMatched &&
                providerOperationNoopExecutionBoundary.expectedSafeIdMatched &&
                providerOperationNoopKatSuiteReport.expectedSafeIdMatched &&
                providerOperationNoopKatValidation.expectedSafeIdMatched &&
                providerOperationNoopKat.expectedSafeIdMatched &&
                providerOperationNoopKatAdmission.expectedSafeIdMatched &&
                providerOperationMetadataKatSuiteReport.expectedSafeIdMatched
        val expectedFixtureIdMatched =
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
            providerOperationNoopExecutionBoundarySuiteReport
                .expectedProviderOperationNoopExecutionBoundaryIdMatched &&
                providerOperationNoopExecutionBoundaryValidation
                    .expectedProviderOperationNoopExecutionBoundaryIdMatched &&
                providerOperationNoopExecutionBoundary.expectedProviderOperationNoopExecutionBoundaryIdMatched &&
                EXPECTED_PROVIDER_OPERATION_NOOP_EXECUTION_BOUNDARY_ID ==
                "skald-test-only-provider-identity-provider-operation-noop-execution-boundary-v1-inert-identity"
        val expectedProviderOperationNoopExecutionBoundarySuiteReportIdMatched =
            providerOperationNoopExecutionBoundarySuiteReport
                .expectedProviderOperationNoopExecutionBoundarySuiteReportIdMatched &&
                EXPECTED_PROVIDER_OPERATION_NOOP_EXECUTION_BOUNDARY_SUITE_REPORT_ID ==
                "skald-test-only-provider-identity-provider-operation-noop-execution-boundary-suite-report-v1-inert-identity"
        val expectedProviderOperationSyntheticTraceIdMatched =
            EXPECTED_PROVIDER_OPERATION_SYNTHETIC_TRACE_ID ==
                "skald-test-only-provider-identity-provider-operation-synthetic-trace-v1-inert-identity"

        val providerOperationNoopKatPassed =
            providerOperationNoopExecutionBoundarySuiteReport.providerOperationNoopKatPassed &&
                providerOperationNoopExecutionBoundaryValidation.providerOperationNoopKatPassed &&
                providerOperationNoopExecutionBoundary.providerOperationNoopKatPassed &&
                providerOperationNoopKatSuiteReport.providerOperationNoopKatPassed &&
                providerOperationNoopKatValidation.providerOperationNoopKatPassed &&
                providerOperationNoopKat.providerOperationNoopKatPassed
        val providerOperationNoopKatValidationPassed =
            providerOperationNoopExecutionBoundarySuiteReport.providerOperationNoopKatValidationPassed &&
                providerOperationNoopExecutionBoundaryValidation.providerOperationNoopKatValidationPassed &&
                providerOperationNoopExecutionBoundary.providerOperationNoopKatValidationPassed &&
                providerOperationNoopKatSuiteReport.providerOperationNoopKatValidationPassed &&
                providerOperationNoopKatValidation.allValidationChecksPassed
        val providerOperationNoopKatSuitePassed =
            providerOperationNoopExecutionBoundarySuiteReport.providerOperationNoopKatSuitePassed &&
                providerOperationNoopExecutionBoundaryValidation.providerOperationNoopKatSuitePassed &&
                providerOperationNoopExecutionBoundary.providerOperationNoopKatSuitePassed &&
                providerOperationNoopKatSuiteReport.providerOperationNoopKatSuitePassed
        val syntheticNoopResultPresent =
            providerOperationNoopExecutionBoundarySuiteReport.syntheticNoopResultPresent &&
                providerOperationNoopExecutionBoundaryValidation.syntheticNoopResultPresent &&
                providerOperationNoopExecutionBoundary.syntheticNoopResultPresent &&
                providerOperationNoopKatSuiteReport.syntheticNoopResultPresent &&
                providerOperationNoopKatValidation.syntheticNoopResultPresent &&
                providerOperationNoopKat.syntheticNoopResultPresent
        val noopExecutionBoundaryModeled =
            providerOperationNoopExecutionBoundarySuiteReport.noopExecutionBoundaryModeled &&
                providerOperationNoopExecutionBoundaryValidation.noopExecutionBoundaryModeled &&
                providerOperationNoopExecutionBoundary.noopExecutionBoundaryModeled
        val syntheticNoopEvaluationPermitted =
            providerOperationNoopExecutionBoundarySuiteReport.syntheticNoopEvaluationPermitted &&
                providerOperationNoopExecutionBoundaryValidation.syntheticNoopEvaluationPermitted &&
                providerOperationNoopExecutionBoundary.syntheticNoopEvaluationPermitted
        val noopExecutionBoundaryValidationPassed =
            providerOperationNoopExecutionBoundarySuiteReport.noopExecutionBoundaryValidationPassed &&
                providerOperationNoopExecutionBoundaryValidation.allValidationChecksPassed
        val noopExecutionBoundarySuitePassed =
            providerOperationNoopExecutionBoundarySuiteReport.noopExecutionBoundarySuitePassed

        val currentSyntheticTracePresent = false
        val currentTracePayloadPresent = false
        val currentNoopProviderOperationExecutionPresent =
            providerOperationNoopKatAdmission.currentNoopProviderOperationExecutionPresent ||
                providerOperationNoopExecutionBoundarySuiteReport.providerOperationExecutionPresent ||
                providerOperationNoopExecutionBoundaryValidation.providerOperationExecutionPresent ||
                providerOperationNoopExecutionBoundary.providerOperationExecutionPresent
        val currentRealProviderOperationExecutionPresent =
            providerOperationNoopKatAdmission.currentProviderOperationExecutionPresent ||
                providerOperationNoopExecutionBoundarySuiteReport.providerOperationExecutionPresent ||
                providerOperationNoopExecutionBoundaryValidation.providerOperationExecutionPresent ||
                providerOperationNoopExecutionBoundary.providerOperationExecutionPresent
        val currentCryptoExecutionPresent =
            providerOperationNoopKatAdmission.currentCryptoExecutionPresent ||
                providerOperationNoopExecutionBoundarySuiteReport.cryptoExecutionPresent ||
                providerOperationNoopExecutionBoundaryValidation.cryptoExecutionPresent ||
                providerOperationNoopExecutionBoundary.cryptoExecutionPresent
        val currentKatRunnerPresent =
            providerOperationNoopKatAdmission.currentKatRunnerPresent ||
                providerOperationNoopExecutionBoundarySuiteReport.providerOperationKatRunnerPresent ||
                providerOperationNoopExecutionBoundaryValidation.providerOperationKatRunnerPresent ||
                providerOperationNoopExecutionBoundary.providerOperationKatRunnerPresent
        val currentKatExecutorPresent =
            providerOperationNoopKatAdmission.currentKatExecutorPresent ||
                providerOperationNoopExecutionBoundarySuiteReport.providerOperationKatExecutorPresent ||
                providerOperationNoopExecutionBoundaryValidation.providerOperationKatExecutorPresent ||
                providerOperationNoopExecutionBoundary.providerOperationKatExecutorPresent

        val realProviderOperationExecutionPermitted =
            providerOperationNoopExecutionBoundarySuiteReport.realProviderOperationExecutionPermitted ||
                providerOperationNoopExecutionBoundaryValidation.realProviderOperationExecutionPermitted ||
                providerOperationNoopExecutionBoundary.realProviderOperationExecutionPermitted
        val cryptoExecutionPermitted =
            providerOperationNoopExecutionBoundarySuiteReport.cryptoExecutionPermitted ||
                providerOperationNoopExecutionBoundaryValidation.cryptoExecutionPermitted ||
                providerOperationNoopExecutionBoundary.cryptoExecutionPermitted
        val katRunnerPermitted =
            providerOperationNoopExecutionBoundarySuiteReport.katRunnerPermitted ||
                providerOperationNoopExecutionBoundaryValidation.katRunnerPermitted ||
                providerOperationNoopExecutionBoundary.katRunnerPermitted
        val katExecutorPermitted =
            providerOperationNoopExecutionBoundarySuiteReport.katExecutorPermitted ||
                providerOperationNoopExecutionBoundaryValidation.katExecutorPermitted ||
                providerOperationNoopExecutionBoundary.katExecutorPermitted
        val vaultPersistencePermitted =
            providerOperationNoopExecutionBoundarySuiteReport.vaultPersistencePermitted ||
                providerOperationNoopExecutionBoundaryValidation.vaultPersistencePermitted ||
                providerOperationNoopExecutionBoundary.vaultPersistencePermitted
        val mainnetPermitted =
            providerOperationNoopExecutionBoundarySuiteReport.mainnetPermitted ||
                providerOperationNoopExecutionBoundaryValidation.mainnetPermitted ||
                providerOperationNoopExecutionBoundary.mainnetPermitted

        val rawKatMaterialPresent =
            providerOperationNoopExecutionBoundarySuiteReport.rawKatMaterialPresent ||
                providerOperationNoopExecutionBoundaryValidation.rawKatMaterialPresent ||
                providerOperationNoopExecutionBoundary.rawKatMaterialPresent ||
                providerOperationNoopKatSuiteReport.rawKatMaterialPresent ||
                providerOperationNoopKatValidation.rawKatMaterialPresent ||
                providerOperationNoopKat.rawKatMaterialPresent ||
                providerOperationNoopKatAdmission.rawKatMaterialPresent ||
                providerOperationMetadataKatSuiteReport.rawKatMaterialPresent
        val rawVectorBytesPresent =
            providerOperationNoopExecutionBoundarySuiteReport.rawVectorBytesPresent ||
                providerOperationNoopExecutionBoundaryValidation.rawVectorBytesPresent ||
                providerOperationNoopExecutionBoundary.rawVectorBytesPresent ||
                providerOperationNoopKatSuiteReport.rawVectorBytesPresent ||
                providerOperationNoopKatValidation.rawVectorBytesPresent ||
                providerOperationNoopKat.rawVectorBytesPresent ||
                providerOperationNoopKatAdmission.rawVectorBytesPresent ||
                providerOperationMetadataKatSuiteReport.rawVectorBytesPresent
        val rawVectorHexPresent =
            providerOperationNoopExecutionBoundarySuiteReport.rawVectorHexPresent ||
                providerOperationNoopExecutionBoundaryValidation.rawVectorHexPresent ||
                providerOperationNoopExecutionBoundary.rawVectorHexPresent ||
                providerOperationNoopKatSuiteReport.rawVectorHexPresent ||
                providerOperationNoopKatValidation.rawVectorHexPresent ||
                providerOperationNoopKat.rawVectorHexPresent ||
                providerOperationNoopKatAdmission.rawVectorHexPresent ||
                providerOperationMetadataKatSuiteReport.rawVectorHexPresent
        val publicVectorBytesPresent =
            providerOperationNoopExecutionBoundarySuiteReport.publicVectorBytesPresent ||
                providerOperationNoopExecutionBoundaryValidation.publicVectorBytesPresent ||
                providerOperationNoopExecutionBoundary.publicVectorBytesPresent ||
                providerOperationNoopKatSuiteReport.publicVectorBytesPresent ||
                providerOperationNoopKatValidation.publicVectorBytesPresent ||
                providerOperationNoopKat.publicVectorBytesPresent ||
                providerOperationNoopKatAdmission.publicVectorBytesPresent ||
                providerOperationMetadataKatSuiteReport.publicVectorBytesPresent
        val publicVectorHexPresent =
            providerOperationNoopExecutionBoundarySuiteReport.publicVectorHexPresent ||
                providerOperationNoopExecutionBoundaryValidation.publicVectorHexPresent ||
                providerOperationNoopExecutionBoundary.publicVectorHexPresent ||
                providerOperationNoopKatSuiteReport.publicVectorHexPresent ||
                providerOperationNoopKatValidation.publicVectorHexPresent ||
                providerOperationNoopKat.publicVectorHexPresent ||
                providerOperationNoopKatAdmission.publicVectorHexPresent ||
                providerOperationMetadataKatSuiteReport.publicVectorHexPresent

        val runtimeSelectable =
            providerOperationNoopExecutionBoundarySuiteReport.runtimeSelectable ||
                capabilityMatrix.runtimeSelectable
        val registrySelectable =
            providerOperationNoopExecutionBoundarySuiteReport.registrySelectable ||
                capabilityMatrix.registrySelectable
        val factoryReachable =
            providerOperationNoopExecutionBoundarySuiteReport.factoryReachable ||
                capabilityMatrix.factoryReachable
        val dispatcherReachable =
            providerOperationNoopExecutionBoundarySuiteReport.dispatcherReachable ||
                capabilityMatrix.dispatcherReachable
        val executorTargetable =
            providerOperationNoopExecutionBoundarySuiteReport.executorTargetable ||
                capabilityMatrix.executorTargetable
        val providerKatExecutorReachable =
            providerOperationNoopExecutionBoundarySuiteReport.providerKatExecutorReachable ||
                capabilityMatrix.providerKatExecutorReachable
        val providerOperationReachable =
            providerOperationNoopExecutionBoundarySuiteReport.providerOperationReachable ||
                capabilityMatrix.providerOperationReachable ||
                currentNoopProviderOperationExecutionPresent ||
                currentRealProviderOperationExecutionPresent ||
                realProviderOperationExecutionPermitted
        val cryptoExecutionReachable =
            providerOperationNoopExecutionBoundarySuiteReport.cryptoExecutionReachable ||
                capabilityMatrix.cryptoExecutionReachable ||
                currentCryptoExecutionPresent ||
                cryptoExecutionPermitted
        val vaultLifecycleReachable =
            providerOperationNoopExecutionBoundarySuiteReport.vaultLifecycleReachable ||
                capabilityMatrix.vaultLifecycleReachable
        val persistenceReachable =
            providerOperationNoopExecutionBoundarySuiteReport.persistenceReachable ||
                capabilityMatrix.persistenceReachable ||
                vaultPersistencePermitted
        val productionSyncReachable =
            providerOperationNoopExecutionBoundarySuiteReport.productionSyncReachable ||
                capabilityMatrix.productionSyncReachable
        val backendClientReachable =
            providerOperationNoopExecutionBoundarySuiteReport.backendClientReachable ||
                capabilityMatrix.backendClientReachable
        val bdkWalletStateReachable =
            providerOperationNoopExecutionBoundarySuiteReport.bdkWalletStateReachable ||
                capabilityMatrix.bdkWalletStateReachable
        val settingsCodecReachable =
            providerOperationNoopExecutionBoundarySuiteReport.settingsCodecReachable ||
                capabilityMatrix.settingsCodecReachable
        val uiSurfaceReachable =
            providerOperationNoopExecutionBoundarySuiteReport.uiSurfaceReachable ||
                capabilityMatrix.uiSurfaceReachable
        val signingBroadcastingReachable =
            providerOperationNoopExecutionBoundarySuiteReport.signingBroadcastingReachable ||
                capabilityMatrix.signingBroadcastingReachable
        val publicEndpointReachable =
            providerOperationNoopExecutionBoundarySuiteReport.publicEndpointReachable ||
                capabilityMatrix.publicEndpointReachable
        val mainnetReachable =
            providerOperationNoopExecutionBoundarySuiteReport.mainnetReachable ||
                capabilityMatrix.mainnetReachable ||
                mainnetPermitted
        val implementsVaultCryptoProvider =
            providerOperationNoopExecutionBoundarySuiteReport.implementsVaultCryptoProvider ||
                capabilityMatrix.implementsVaultCryptoProvider
        val containsVaultCryptoProvider =
            providerOperationNoopExecutionBoundarySuiteReport.containsVaultCryptoProvider ||
                capabilityMatrix.containsVaultCryptoProvider
        val canExecuteProviderOperations =
            providerOperationNoopExecutionBoundarySuiteReport.canExecuteProviderOperations ||
                capabilityMatrix.canExecuteProviderOperations ||
                currentNoopProviderOperationExecutionPresent ||
                currentRealProviderOperationExecutionPresent ||
                realProviderOperationExecutionPermitted
        val canExecuteCrypto =
            providerOperationNoopExecutionBoundarySuiteReport.canExecuteCrypto ||
                capabilityMatrix.canExecuteCrypto ||
                currentCryptoExecutionPresent ||
                cryptoExecutionPermitted
        val canUseForVaultLifecycle =
            providerOperationNoopExecutionBoundarySuiteReport.canUseForVaultLifecycle ||
                capabilityMatrix.canUseForVaultLifecycle
        val canUseForPersistence =
            providerOperationNoopExecutionBoundarySuiteReport.canUseForPersistence ||
                capabilityMatrix.canUseForPersistence ||
                vaultPersistencePermitted
        val canUseForSync =
            providerOperationNoopExecutionBoundarySuiteReport.canUseForSync ||
                capabilityMatrix.canUseForSync
        val canUseForSigning =
            providerOperationNoopExecutionBoundarySuiteReport.canUseForSigning ||
                capabilityMatrix.canUseForSigning
        val canUseForBroadcasting =
            providerOperationNoopExecutionBoundarySuiteReport.canUseForBroadcasting ||
                capabilityMatrix.canUseForBroadcasting
        val canUseForMainnet =
            providerOperationNoopExecutionBoundarySuiteReport.canUseForMainnet ||
                capabilityMatrix.canUseForMainnet ||
                mainnetPermitted
        val productionProviderSelectable =
            providerOperationNoopExecutionBoundarySuiteReport.productionProviderSelectable ||
                capabilityMatrix.productionProviderSelectable ||
                marker.productionProviderSelectable

        val admissionIsCommonTestOnly =
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
        val admissionIsProductionAuthorization =
            providerOperationNoopExecutionBoundarySuiteReport.suiteIsProductionAuthorization ||
                providerOperationNoopExecutionBoundaryValidation.validationIsProductionAuthorization ||
                providerOperationNoopExecutionBoundary.boundaryIsProductionAuthorization ||
                providerOperationNoopKatSuiteReport.suiteIsProductionAuthorization ||
                providerOperationNoopKatValidation.validationIsProductionAuthorization ||
                providerOperationNoopKat.noopKatIsProductionAuthorization ||
                providerOperationNoopKatAdmission.admissionIsProductionAuthorization ||
                providerOperationMetadataKatSuiteReport.suiteIsProductionAuthorization
        val admissionIsProviderSelectionAuthorization =
            providerOperationNoopExecutionBoundarySuiteReport.suiteIsProviderSelectionAuthorization ||
                providerOperationNoopExecutionBoundaryValidation.validationIsProviderSelectionAuthorization ||
                providerOperationNoopExecutionBoundary.boundaryIsProviderSelectionAuthorization ||
                providerOperationNoopKatSuiteReport.suiteIsProviderSelectionAuthorization ||
                providerOperationNoopKatValidation.validationIsProviderSelectionAuthorization ||
                providerOperationNoopKat.noopKatIsProviderSelectionAuthorization ||
                providerOperationNoopKatAdmission.admissionIsProviderSelectionAuthorization ||
                providerOperationMetadataKatSuiteReport.suiteIsProviderSelectionAuthorization
        val admissionIsProviderOperationAuthorization =
            providerOperationNoopExecutionBoundarySuiteReport.suiteIsProviderOperationAuthorization ||
                providerOperationNoopExecutionBoundaryValidation.validationIsProviderOperationAuthorization ||
                providerOperationNoopExecutionBoundary.boundaryIsProviderOperationAuthorization ||
                providerOperationNoopKatSuiteReport.suiteIsProviderOperationAuthorization ||
                providerOperationNoopKatValidation.validationIsProviderOperationAuthorization ||
                providerOperationNoopKat.noopKatIsProviderOperationAuthorization ||
                providerOperationNoopKatAdmission.admissionIsProviderOperationAuthorization ||
                providerOperationMetadataKatSuiteReport.suiteIsProviderOperationAuthorization
        val admissionIsKatExecutorAuthorization =
            providerOperationNoopExecutionBoundarySuiteReport.suiteIsKatExecutorAuthorization ||
                providerOperationNoopExecutionBoundaryValidation.validationIsKatExecutorAuthorization ||
                providerOperationNoopExecutionBoundary.boundaryIsKatExecutorAuthorization ||
                providerOperationNoopKatSuiteReport.suiteIsKatExecutorAuthorization ||
                providerOperationNoopKatValidation.validationIsKatExecutorAuthorization ||
                providerOperationNoopKat.noopKatIsKatExecutorAuthorization ||
                providerOperationNoopKatAdmission.admissionIsKatExecutorAuthorization ||
                providerOperationMetadataKatSuiteReport.suiteIsKatExecutorAuthorization
        val admissionIsCryptoAuthorization =
            providerOperationNoopExecutionBoundarySuiteReport.suiteIsCryptoAuthorization ||
                providerOperationNoopExecutionBoundaryValidation.validationIsCryptoAuthorization ||
                providerOperationNoopExecutionBoundary.boundaryIsCryptoAuthorization ||
                providerOperationNoopKatSuiteReport.suiteIsCryptoAuthorization ||
                providerOperationNoopKatValidation.validationIsCryptoAuthorization ||
                providerOperationNoopKat.noopKatIsCryptoAuthorization ||
                providerOperationNoopKatAdmission.admissionIsCryptoAuthorization ||
                providerOperationMetadataKatSuiteReport.suiteIsCryptoAuthorization
        val admissionIsVaultPersistenceAuthorization =
            providerOperationNoopExecutionBoundarySuiteReport.suiteIsVaultPersistenceAuthorization ||
                providerOperationNoopExecutionBoundaryValidation.validationIsVaultPersistenceAuthorization ||
                providerOperationNoopExecutionBoundary.boundaryIsVaultPersistenceAuthorization ||
                providerOperationNoopKatSuiteReport.suiteIsVaultPersistenceAuthorization ||
                providerOperationNoopKatValidation.validationIsVaultPersistenceAuthorization ||
                providerOperationNoopKat.noopKatIsVaultPersistenceAuthorization ||
                providerOperationNoopKatAdmission.admissionIsVaultPersistenceAuthorization ||
                providerOperationMetadataKatSuiteReport.suiteIsVaultPersistenceAuthorization
        val admissionIsMainnetAuthorization =
            providerOperationNoopExecutionBoundarySuiteReport.suiteIsMainnetAuthorization ||
                providerOperationNoopExecutionBoundaryValidation.validationIsMainnetAuthorization ||
                providerOperationNoopExecutionBoundary.boundaryIsMainnetAuthorization ||
                providerOperationNoopKatSuiteReport.suiteIsMainnetAuthorization ||
                providerOperationNoopKatValidation.validationIsMainnetAuthorization ||
                providerOperationNoopKat.noopKatIsMainnetAuthorization ||
                providerOperationNoopKatAdmission.admissionIsMainnetAuthorization ||
                providerOperationMetadataKatSuiteReport.suiteIsMainnetAuthorization

        val futureSyntheticTraceCriteriaModeled =
            admissionIsCommonTestOnly &&
                providerOperationNoopExecutionBoundarySuiteReportCount == 1 &&
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
                admissionOutcomes.size ==
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceAdmissionOutcome
                    .entries
                    .size &&
                futureCriteria.size ==
                SkaldVaultV1TestOnlyProviderIdentityFutureSyntheticTraceCriterion.entries.size &&
                forbiddenStates.size ==
                SkaldVaultV1TestOnlyProviderIdentityForbiddenCurrentSyntheticTraceState.entries.size &&
                !currentSyntheticTracePresent &&
                !currentTracePayloadPresent &&
                !currentNoopProviderOperationExecutionPresent &&
                !currentRealProviderOperationExecutionPresent &&
                !currentCryptoExecutionPresent &&
                !currentKatRunnerPresent &&
                !currentKatExecutorPresent &&
                !admissionIsProductionAuthorization &&
                !admissionIsProviderSelectionAuthorization &&
                !admissionIsProviderOperationAuthorization &&
                !admissionIsKatExecutorAuthorization &&
                !admissionIsCryptoAuthorization &&
                !admissionIsVaultPersistenceAuthorization &&
                !admissionIsMainnetAuthorization &&
                !realProviderOperationExecutionPermitted &&
                !cryptoExecutionPermitted &&
                !katRunnerPermitted &&
                !katExecutorPermitted &&
                !vaultPersistencePermitted &&
                !mainnetPermitted &&
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

        return SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceAdmission(
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
            futureSyntheticTraceCriteriaAuthorizeCurrentTrace = false,
            admissionIsCommonTestOnly = admissionIsCommonTestOnly,
            admissionIsProductionAuthorization = admissionIsProductionAuthorization,
            admissionIsProviderSelectionAuthorization = admissionIsProviderSelectionAuthorization,
            admissionIsProviderOperationAuthorization = admissionIsProviderOperationAuthorization,
            admissionIsKatExecutorAuthorization = admissionIsKatExecutorAuthorization,
            admissionIsCryptoAuthorization = admissionIsCryptoAuthorization,
            admissionIsVaultPersistenceAuthorization = admissionIsVaultPersistenceAuthorization,
            admissionIsMainnetAuthorization = admissionIsMainnetAuthorization,
            currentSyntheticTracePresent = currentSyntheticTracePresent,
            currentTracePayloadPresent = currentTracePayloadPresent,
            currentNoopProviderOperationExecutionPresent = currentNoopProviderOperationExecutionPresent,
            currentRealProviderOperationExecutionPresent = currentRealProviderOperationExecutionPresent,
            currentCryptoExecutionPresent = currentCryptoExecutionPresent,
            currentKatRunnerPresent = currentKatRunnerPresent,
            currentKatExecutorPresent = currentKatExecutorPresent,
            realProviderOperationExecutionPermitted = realProviderOperationExecutionPermitted,
            cryptoExecutionPermitted = cryptoExecutionPermitted,
            katRunnerPermitted = katRunnerPermitted,
            katExecutorPermitted = katExecutorPermitted,
            vaultPersistencePermitted = vaultPersistencePermitted,
            mainnetPermitted = mainnetPermitted,
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
            admissionOutcomes = admissionOutcomes,
            futureSyntheticTraceCriteria = futureCriteria,
            forbiddenCurrentSyntheticTraceStates = forbiddenStates,
            sourceSet = SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceAdmissionSourceSet
                .CommonTest,
            displayLabel = SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceAdmissionSafeLabel(
                "synthetic provider-operation trace admission evidence",
            ),
        )
    }
}
