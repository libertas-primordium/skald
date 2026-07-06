package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundarySuiteReportSafeLabel(
    val value: String,
) {
    override fun toString(): String =
        "RedactedTestOnlyProviderIdentityProviderOperationNoopExecutionBoundarySuiteReportSafeLabel"
}

data class SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundarySuiteReport(
    val markerCount: Int,
    val caseBindingCount: Int,
    val providerOperationMetadataKatCount: Int,
    val providerOperationMetadataKatValidationCount: Int,
    val providerOperationMetadataKatSuiteReportCount: Int,
    val providerOperationNoopKatAdmissionCount: Int,
    val providerOperationNoopKatCount: Int,
    val providerOperationNoopKatValidationCount: Int,
    val providerOperationNoopKatSuiteReportCount: Int,
    val providerOperationNoopExecutionBoundaryCount: Int,
    val providerOperationNoopExecutionBoundaryValidationCount: Int,
    val expectedSafeIdMatched: Boolean,
    val expectedFixtureIdMatched: Boolean,
    val expectedVectorIdMatched: Boolean,
    val expectedCaseIdMatched: Boolean,
    val expectedProviderOperationMetadataKatIdMatched: Boolean,
    val expectedProviderOperationNoopKatIdMatched: Boolean,
    val expectedProviderOperationNoopExecutionBoundaryIdMatched: Boolean,
    val expectedProviderOperationNoopExecutionBoundarySuiteReportIdMatched: Boolean,
    val providerOperationNoopKatPassed: Boolean,
    val providerOperationNoopKatValidationPassed: Boolean,
    val providerOperationNoopKatSuitePassed: Boolean,
    val syntheticNoopResultPresent: Boolean,
    val noopExecutionBoundaryModeled: Boolean,
    val syntheticNoopEvaluationPermitted: Boolean,
    val noopExecutionBoundaryValidationPassed: Boolean,
    val suiteReportGenerated: Boolean,
    val noopExecutionBoundarySuitePassed: Boolean,
    val suiteIsCommonTestOnly: Boolean,
    val suiteIsProductionAuthorization: Boolean,
    val suiteIsProviderSelectionAuthorization: Boolean,
    val suiteIsProviderOperationAuthorization: Boolean,
    val suiteIsKatExecutorAuthorization: Boolean,
    val suiteIsCryptoAuthorization: Boolean,
    val suiteIsVaultPersistenceAuthorization: Boolean,
    val suiteIsMainnetAuthorization: Boolean,
    val suiteReportsSyntheticNoopOnly: Boolean,
    val suiteReportsProviderOperationExecution: Boolean,
    val suiteReportsCryptoOperation: Boolean,
    val suiteReportsVaultLifecycle: Boolean,
    val suiteReportsPersistence: Boolean,
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
    val displayLabel:
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundarySuiteReportSafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundarySuiteReport(redactedMarkerId, redactedFixtureId, redactedVectorId, redactedCaseId, redactedProviderOperationMetadataKatId, redactedProviderOperationNoopKatId, redactedBoundaryId, redactedBoundarySuiteReportId, commonTestOnly, syntheticNoopBoundarySuiteOnly, nonAuthorizing)"
}

object SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundarySuiteReportPolicy {
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

    fun currentProviderOperationNoopExecutionBoundarySuiteReport():
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundarySuiteReport {
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

        val markerCount =
            if (
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
        val caseBindingCount = providerOperationNoopExecutionBoundaryValidation.caseBindingCount
        val providerOperationMetadataKatCount =
            providerOperationNoopExecutionBoundaryValidation.providerOperationMetadataKatCount
        val providerOperationMetadataKatValidationCount =
            providerOperationNoopExecutionBoundaryValidation.providerOperationMetadataKatValidationCount
        val providerOperationMetadataKatSuiteReportCount =
            providerOperationNoopExecutionBoundaryValidation.providerOperationMetadataKatSuiteReportCount
        val providerOperationNoopKatAdmissionCount =
            providerOperationNoopExecutionBoundaryValidation.providerOperationNoopKatAdmissionCount
        val providerOperationNoopKatCount =
            providerOperationNoopExecutionBoundaryValidation.providerOperationNoopKatCount
        val providerOperationNoopKatValidationCount =
            providerOperationNoopExecutionBoundaryValidation.providerOperationNoopKatValidationCount
        val providerOperationNoopKatSuiteReportCount =
            providerOperationNoopExecutionBoundaryValidation.providerOperationNoopKatSuiteReportCount
        val providerOperationNoopExecutionBoundaryCount =
            providerOperationNoopExecutionBoundaryValidation.providerOperationNoopExecutionBoundaryCount
        val providerOperationNoopExecutionBoundaryValidationCount =
            if (
                providerOperationNoopExecutionBoundaryValidation.allValidationChecksPassed &&
                providerOperationNoopExecutionBoundaryValidation.noopExecutionBoundaryModeled &&
                providerOperationNoopExecutionBoundaryValidation.syntheticNoopEvaluationPermitted
            ) {
                1
            } else {
                0
            }

        val expectedSafeIdMatched =
            markerCount == 1 &&
                providerOperationNoopExecutionBoundaryValidation.expectedSafeIdMatched &&
                providerOperationNoopExecutionBoundary.expectedSafeIdMatched &&
                providerOperationNoopKatSuiteReport.expectedSafeIdMatched &&
                providerOperationNoopKatValidation.expectedSafeIdMatched &&
                providerOperationNoopKat.expectedSafeIdMatched &&
                providerOperationNoopKatAdmission.expectedSafeIdMatched &&
                providerOperationMetadataKatSuiteReport.expectedSafeIdMatched
        val expectedFixtureIdMatched =
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
            providerOperationNoopExecutionBoundaryValidation.expectedProviderOperationNoopKatIdMatched &&
                providerOperationNoopExecutionBoundary.expectedProviderOperationNoopKatIdMatched &&
                providerOperationNoopKatSuiteReport.expectedProviderOperationNoopKatIdMatched &&
                providerOperationNoopKatValidation.expectedProviderOperationNoopKatIdMatched &&
                providerOperationNoopKat.expectedProviderOperationNoopKatIdMatched &&
                providerOperationNoopKatAdmission.expectedProviderOperationNoopKatIdMatched &&
                EXPECTED_PROVIDER_OPERATION_NOOP_KAT_ID ==
                "skald-test-only-provider-identity-provider-operation-noop-kat-v1-inert-identity"
        val expectedProviderOperationNoopExecutionBoundaryIdMatched =
            providerOperationNoopExecutionBoundaryValidation
                .expectedProviderOperationNoopExecutionBoundaryIdMatched &&
                providerOperationNoopExecutionBoundary.expectedProviderOperationNoopExecutionBoundaryIdMatched &&
                EXPECTED_PROVIDER_OPERATION_NOOP_EXECUTION_BOUNDARY_ID ==
                "skald-test-only-provider-identity-provider-operation-noop-execution-boundary-v1-inert-identity"
        val expectedProviderOperationNoopExecutionBoundarySuiteReportIdMatched =
            EXPECTED_PROVIDER_OPERATION_NOOP_EXECUTION_BOUNDARY_SUITE_REPORT_ID ==
                "skald-test-only-provider-identity-provider-operation-noop-execution-boundary-suite-report-v1-inert-identity"

        val providerOperationNoopKatPassed =
            providerOperationNoopExecutionBoundaryValidation.providerOperationNoopKatPassed &&
                providerOperationNoopExecutionBoundary.providerOperationNoopKatPassed &&
                providerOperationNoopKatSuiteReport.providerOperationNoopKatPassed &&
                providerOperationNoopKatValidation.providerOperationNoopKatPassed &&
                providerOperationNoopKat.providerOperationNoopKatPassed
        val providerOperationNoopKatValidationPassed =
            providerOperationNoopExecutionBoundaryValidation.providerOperationNoopKatValidationPassed &&
                providerOperationNoopExecutionBoundary.providerOperationNoopKatValidationPassed &&
                providerOperationNoopKatSuiteReport.providerOperationNoopKatValidationPassed &&
                providerOperationNoopKatValidation.allValidationChecksPassed
        val providerOperationNoopKatSuitePassed =
            providerOperationNoopExecutionBoundaryValidation.providerOperationNoopKatSuitePassed &&
                providerOperationNoopExecutionBoundary.providerOperationNoopKatSuitePassed &&
                providerOperationNoopKatSuiteReport.providerOperationNoopKatSuitePassed
        val syntheticNoopResultPresent =
            providerOperationNoopExecutionBoundaryValidation.syntheticNoopResultPresent &&
                providerOperationNoopExecutionBoundary.syntheticNoopResultPresent &&
                providerOperationNoopKatSuiteReport.syntheticNoopResultPresent &&
                providerOperationNoopKatValidation.syntheticNoopResultPresent &&
                providerOperationNoopKat.syntheticNoopResultPresent
        val noopExecutionBoundaryModeled =
            providerOperationNoopExecutionBoundaryValidation.noopExecutionBoundaryModeled &&
                providerOperationNoopExecutionBoundary.noopExecutionBoundaryModeled
        val syntheticNoopEvaluationPermitted =
            providerOperationNoopExecutionBoundaryValidation.syntheticNoopEvaluationPermitted &&
                providerOperationNoopExecutionBoundary.syntheticNoopEvaluationPermitted
        val noopExecutionBoundaryValidationPassed =
            providerOperationNoopExecutionBoundaryValidationCount == 1 &&
                providerOperationNoopExecutionBoundaryValidation.allValidationChecksPassed

        val realProviderOperationExecutionPermitted =
            providerOperationNoopExecutionBoundaryValidation.realProviderOperationExecutionPermitted ||
                providerOperationNoopExecutionBoundary.realProviderOperationExecutionPermitted
        val cryptoExecutionPermitted =
            providerOperationNoopExecutionBoundaryValidation.cryptoExecutionPermitted ||
                providerOperationNoopExecutionBoundary.cryptoExecutionPermitted
        val katRunnerPermitted =
            providerOperationNoopExecutionBoundaryValidation.katRunnerPermitted ||
                providerOperationNoopExecutionBoundary.katRunnerPermitted
        val katExecutorPermitted =
            providerOperationNoopExecutionBoundaryValidation.katExecutorPermitted ||
                providerOperationNoopExecutionBoundary.katExecutorPermitted
        val vaultPersistencePermitted =
            providerOperationNoopExecutionBoundaryValidation.vaultPersistencePermitted ||
                providerOperationNoopExecutionBoundary.vaultPersistencePermitted
        val mainnetPermitted =
            providerOperationNoopExecutionBoundaryValidation.mainnetPermitted ||
                providerOperationNoopExecutionBoundary.mainnetPermitted

        val providerOperationKatExecutorPresent =
            providerOperationNoopExecutionBoundaryValidation.providerOperationKatExecutorPresent ||
                providerOperationNoopExecutionBoundary.providerOperationKatExecutorPresent ||
                providerOperationNoopKatSuiteReport.providerOperationKatExecutorPresent ||
                providerOperationNoopKatValidation.providerOperationKatExecutorPresent ||
                providerOperationNoopKat.providerOperationKatExecutorPresent ||
                providerOperationNoopKatAdmission.currentKatExecutorPresent ||
                providerOperationMetadataKatSuiteReport.providerOperationKatExecutorPresent
        val providerOperationKatRunnerPresent =
            providerOperationNoopExecutionBoundaryValidation.providerOperationKatRunnerPresent ||
                providerOperationNoopExecutionBoundary.providerOperationKatRunnerPresent ||
                providerOperationNoopKatSuiteReport.providerOperationKatRunnerPresent ||
                providerOperationNoopKatValidation.providerOperationKatRunnerPresent ||
                providerOperationNoopKat.providerOperationKatRunnerPresent ||
                providerOperationNoopKatAdmission.currentKatRunnerPresent ||
                providerOperationMetadataKatSuiteReport.providerOperationKatRunnerPresent
        val providerOperationExecutionPresent =
            providerOperationNoopExecutionBoundaryValidation.providerOperationExecutionPresent ||
                providerOperationNoopExecutionBoundary.providerOperationExecutionPresent ||
                providerOperationNoopKatSuiteReport.providerOperationExecutionPresent ||
                providerOperationNoopKatValidation.providerOperationExecutionPresent ||
                providerOperationNoopKat.providerOperationExecutionPresent ||
                providerOperationNoopKatAdmission.currentProviderOperationExecutionPresent ||
                providerOperationNoopKatAdmission.currentNoopProviderOperationExecutionPresent ||
                providerOperationMetadataKatSuiteReport.providerOperationExecutionPresent ||
                realProviderOperationExecutionPermitted
        val cryptoExecutionPresent =
            providerOperationNoopExecutionBoundaryValidation.cryptoExecutionPresent ||
                providerOperationNoopExecutionBoundary.cryptoExecutionPresent ||
                providerOperationNoopKatSuiteReport.cryptoExecutionPresent ||
                providerOperationNoopKatValidation.cryptoExecutionPresent ||
                providerOperationNoopKat.cryptoExecutionPresent ||
                providerOperationNoopKatAdmission.currentCryptoExecutionPresent ||
                providerOperationMetadataKatSuiteReport.cryptoExecutionPresent ||
                cryptoExecutionPermitted
        val rawKatMaterialPresent =
            providerOperationNoopExecutionBoundaryValidation.rawKatMaterialPresent ||
                providerOperationNoopExecutionBoundary.rawKatMaterialPresent ||
                providerOperationNoopKatSuiteReport.rawKatMaterialPresent ||
                providerOperationNoopKatValidation.rawKatMaterialPresent ||
                providerOperationNoopKat.rawKatMaterialPresent ||
                providerOperationNoopKatAdmission.rawKatMaterialPresent ||
                providerOperationMetadataKatSuiteReport.rawKatMaterialPresent
        val rawVectorBytesPresent =
            providerOperationNoopExecutionBoundaryValidation.rawVectorBytesPresent ||
                providerOperationNoopExecutionBoundary.rawVectorBytesPresent ||
                providerOperationNoopKatSuiteReport.rawVectorBytesPresent ||
                providerOperationNoopKatValidation.rawVectorBytesPresent ||
                providerOperationNoopKat.rawVectorBytesPresent ||
                providerOperationNoopKatAdmission.rawVectorBytesPresent ||
                providerOperationMetadataKatSuiteReport.rawVectorBytesPresent
        val rawVectorHexPresent =
            providerOperationNoopExecutionBoundaryValidation.rawVectorHexPresent ||
                providerOperationNoopExecutionBoundary.rawVectorHexPresent ||
                providerOperationNoopKatSuiteReport.rawVectorHexPresent ||
                providerOperationNoopKatValidation.rawVectorHexPresent ||
                providerOperationNoopKat.rawVectorHexPresent ||
                providerOperationNoopKatAdmission.rawVectorHexPresent ||
                providerOperationMetadataKatSuiteReport.rawVectorHexPresent
        val publicVectorBytesPresent =
            providerOperationNoopExecutionBoundaryValidation.publicVectorBytesPresent ||
                providerOperationNoopExecutionBoundary.publicVectorBytesPresent ||
                providerOperationNoopKatSuiteReport.publicVectorBytesPresent ||
                providerOperationNoopKatValidation.publicVectorBytesPresent ||
                providerOperationNoopKat.publicVectorBytesPresent ||
                providerOperationNoopKatAdmission.publicVectorBytesPresent ||
                providerOperationMetadataKatSuiteReport.publicVectorBytesPresent
        val publicVectorHexPresent =
            providerOperationNoopExecutionBoundaryValidation.publicVectorHexPresent ||
                providerOperationNoopExecutionBoundary.publicVectorHexPresent ||
                providerOperationNoopKatSuiteReport.publicVectorHexPresent ||
                providerOperationNoopKatValidation.publicVectorHexPresent ||
                providerOperationNoopKat.publicVectorHexPresent ||
                providerOperationNoopKatAdmission.publicVectorHexPresent ||
                providerOperationMetadataKatSuiteReport.publicVectorHexPresent

        val runtimeSelectable =
            providerOperationNoopExecutionBoundaryValidation.runtimeSelectable ||
                providerOperationNoopExecutionBoundary.runtimeSelectable ||
                providerOperationNoopKatSuiteReport.runtimeSelectable ||
                providerOperationNoopKatValidation.runtimeSelectable ||
                providerOperationNoopKat.runtimeSelectable ||
                providerOperationNoopKatAdmission.runtimeSelectable ||
                providerOperationMetadataKatSuiteReport.runtimeSelectable ||
                capabilityMatrix.runtimeSelectable
        val registrySelectable =
            providerOperationNoopExecutionBoundaryValidation.registrySelectable ||
                providerOperationNoopExecutionBoundary.registrySelectable ||
                providerOperationNoopKatSuiteReport.registrySelectable ||
                providerOperationNoopKatValidation.registrySelectable ||
                providerOperationNoopKat.registrySelectable ||
                providerOperationNoopKatAdmission.registrySelectable ||
                providerOperationMetadataKatSuiteReport.registrySelectable ||
                capabilityMatrix.registrySelectable
        val factoryReachable =
            providerOperationNoopExecutionBoundaryValidation.factoryReachable ||
                providerOperationNoopExecutionBoundary.factoryReachable ||
                providerOperationNoopKatSuiteReport.factoryReachable ||
                providerOperationNoopKatValidation.factoryReachable ||
                providerOperationNoopKat.factoryReachable ||
                providerOperationNoopKatAdmission.factoryReachable ||
                providerOperationMetadataKatSuiteReport.factoryReachable ||
                capabilityMatrix.factoryReachable
        val dispatcherReachable =
            providerOperationNoopExecutionBoundaryValidation.dispatcherReachable ||
                providerOperationNoopExecutionBoundary.dispatcherReachable ||
                providerOperationNoopKatSuiteReport.dispatcherReachable ||
                providerOperationNoopKatValidation.dispatcherReachable ||
                providerOperationNoopKat.dispatcherReachable ||
                providerOperationNoopKatAdmission.dispatcherReachable ||
                providerOperationMetadataKatSuiteReport.dispatcherReachable ||
                capabilityMatrix.dispatcherReachable
        val executorTargetable =
            providerOperationNoopExecutionBoundaryValidation.executorTargetable ||
                providerOperationNoopExecutionBoundary.executorTargetable ||
                providerOperationNoopKatSuiteReport.executorTargetable ||
                providerOperationNoopKatValidation.executorTargetable ||
                providerOperationNoopKat.executorTargetable ||
                providerOperationNoopKatAdmission.executorTargetable ||
                providerOperationMetadataKatSuiteReport.executorTargetable ||
                capabilityMatrix.executorTargetable
        val providerKatExecutorReachable =
            providerOperationNoopExecutionBoundaryValidation.providerKatExecutorReachable ||
                providerOperationNoopExecutionBoundary.providerKatExecutorReachable ||
                providerOperationNoopKatSuiteReport.providerKatExecutorReachable ||
                providerOperationNoopKatValidation.providerKatExecutorReachable ||
                providerOperationNoopKat.providerKatExecutorReachable ||
                providerOperationNoopKatAdmission.providerKatExecutorReachable ||
                providerOperationMetadataKatSuiteReport.providerKatExecutorReachable ||
                capabilityMatrix.providerKatExecutorReachable
        val providerOperationReachable =
            providerOperationNoopExecutionBoundaryValidation.providerOperationReachable ||
                providerOperationNoopExecutionBoundary.providerOperationReachable ||
                providerOperationNoopKatSuiteReport.providerOperationReachable ||
                providerOperationNoopKatValidation.providerOperationReachable ||
                providerOperationNoopKat.providerOperationReachable ||
                providerOperationNoopKatAdmission.providerOperationReachable ||
                providerOperationMetadataKatSuiteReport.providerOperationReachable ||
                capabilityMatrix.providerOperationReachable ||
                providerOperationExecutionPresent
        val cryptoExecutionReachable =
            providerOperationNoopExecutionBoundaryValidation.cryptoExecutionReachable ||
                providerOperationNoopExecutionBoundary.cryptoExecutionReachable ||
                providerOperationNoopKatSuiteReport.cryptoExecutionReachable ||
                providerOperationNoopKatValidation.cryptoExecutionReachable ||
                providerOperationNoopKat.cryptoExecutionReachable ||
                providerOperationNoopKatAdmission.cryptoExecutionReachable ||
                providerOperationMetadataKatSuiteReport.cryptoExecutionReachable ||
                capabilityMatrix.cryptoExecutionReachable ||
                cryptoExecutionPresent
        val vaultLifecycleReachable =
            providerOperationNoopExecutionBoundaryValidation.vaultLifecycleReachable ||
                providerOperationNoopExecutionBoundary.vaultLifecycleReachable ||
                providerOperationNoopKatSuiteReport.vaultLifecycleReachable ||
                providerOperationNoopKatValidation.vaultLifecycleReachable ||
                providerOperationNoopKat.vaultLifecycleReachable ||
                providerOperationNoopKatAdmission.vaultLifecycleReachable ||
                providerOperationMetadataKatSuiteReport.vaultLifecycleReachable ||
                capabilityMatrix.vaultLifecycleReachable
        val persistenceReachable =
            providerOperationNoopExecutionBoundaryValidation.persistenceReachable ||
                providerOperationNoopExecutionBoundary.persistenceReachable ||
                providerOperationNoopKatSuiteReport.persistenceReachable ||
                providerOperationNoopKatValidation.persistenceReachable ||
                providerOperationNoopKat.persistenceReachable ||
                providerOperationNoopKatAdmission.persistenceReachable ||
                providerOperationMetadataKatSuiteReport.persistenceReachable ||
                capabilityMatrix.persistenceReachable ||
                vaultPersistencePermitted
        val productionSyncReachable =
            providerOperationNoopExecutionBoundaryValidation.productionSyncReachable ||
                providerOperationNoopExecutionBoundary.productionSyncReachable ||
                providerOperationNoopKatSuiteReport.productionSyncReachable ||
                providerOperationNoopKatValidation.productionSyncReachable ||
                providerOperationNoopKat.productionSyncReachable ||
                providerOperationNoopKatAdmission.productionSyncReachable ||
                providerOperationMetadataKatSuiteReport.productionSyncReachable ||
                capabilityMatrix.productionSyncReachable
        val backendClientReachable =
            providerOperationNoopExecutionBoundaryValidation.backendClientReachable ||
                providerOperationNoopExecutionBoundary.backendClientReachable ||
                providerOperationNoopKatSuiteReport.backendClientReachable ||
                providerOperationNoopKatValidation.backendClientReachable ||
                providerOperationNoopKat.backendClientReachable ||
                providerOperationNoopKatAdmission.backendClientReachable ||
                providerOperationMetadataKatSuiteReport.backendClientReachable ||
                capabilityMatrix.backendClientReachable
        val bdkWalletStateReachable =
            providerOperationNoopExecutionBoundaryValidation.bdkWalletStateReachable ||
                providerOperationNoopExecutionBoundary.bdkWalletStateReachable ||
                providerOperationNoopKatSuiteReport.bdkWalletStateReachable ||
                providerOperationNoopKatValidation.bdkWalletStateReachable ||
                providerOperationNoopKat.bdkWalletStateReachable ||
                providerOperationNoopKatAdmission.bdkWalletStateReachable ||
                providerOperationMetadataKatSuiteReport.bdkWalletStateReachable ||
                capabilityMatrix.bdkWalletStateReachable
        val settingsCodecReachable =
            providerOperationNoopExecutionBoundaryValidation.settingsCodecReachable ||
                providerOperationNoopExecutionBoundary.settingsCodecReachable ||
                providerOperationNoopKatSuiteReport.settingsCodecReachable ||
                providerOperationNoopKatValidation.settingsCodecReachable ||
                providerOperationNoopKat.settingsCodecReachable ||
                providerOperationNoopKatAdmission.settingsCodecReachable ||
                providerOperationMetadataKatSuiteReport.settingsCodecReachable ||
                capabilityMatrix.settingsCodecReachable
        val uiSurfaceReachable =
            providerOperationNoopExecutionBoundaryValidation.uiSurfaceReachable ||
                providerOperationNoopExecutionBoundary.uiSurfaceReachable ||
                providerOperationNoopKatSuiteReport.uiSurfaceReachable ||
                providerOperationNoopKatValidation.uiSurfaceReachable ||
                providerOperationNoopKat.uiSurfaceReachable ||
                providerOperationNoopKatAdmission.uiSurfaceReachable ||
                providerOperationMetadataKatSuiteReport.uiSurfaceReachable ||
                capabilityMatrix.uiSurfaceReachable
        val signingBroadcastingReachable =
            providerOperationNoopExecutionBoundaryValidation.signingBroadcastingReachable ||
                providerOperationNoopExecutionBoundary.signingBroadcastingReachable ||
                providerOperationNoopKatSuiteReport.signingBroadcastingReachable ||
                providerOperationNoopKatValidation.signingBroadcastingReachable ||
                providerOperationNoopKat.signingBroadcastingReachable ||
                providerOperationNoopKatAdmission.signingBroadcastingReachable ||
                providerOperationMetadataKatSuiteReport.signingBroadcastingReachable ||
                capabilityMatrix.signingBroadcastingReachable
        val publicEndpointReachable =
            providerOperationNoopExecutionBoundaryValidation.publicEndpointReachable ||
                providerOperationNoopExecutionBoundary.publicEndpointReachable ||
                providerOperationNoopKatSuiteReport.publicEndpointReachable ||
                providerOperationNoopKatValidation.publicEndpointReachable ||
                providerOperationNoopKat.publicEndpointReachable ||
                providerOperationNoopKatAdmission.publicEndpointReachable ||
                providerOperationMetadataKatSuiteReport.publicEndpointReachable ||
                capabilityMatrix.publicEndpointReachable
        val mainnetReachable =
            providerOperationNoopExecutionBoundaryValidation.mainnetReachable ||
                providerOperationNoopExecutionBoundary.mainnetReachable ||
                providerOperationNoopKatSuiteReport.mainnetReachable ||
                providerOperationNoopKatValidation.mainnetReachable ||
                providerOperationNoopKat.mainnetReachable ||
                providerOperationNoopKatAdmission.mainnetReachable ||
                providerOperationMetadataKatSuiteReport.mainnetReachable ||
                capabilityMatrix.mainnetReachable ||
                mainnetPermitted
        val implementsVaultCryptoProvider =
            providerOperationNoopExecutionBoundaryValidation.implementsVaultCryptoProvider ||
                providerOperationNoopExecutionBoundary.implementsVaultCryptoProvider ||
                providerOperationNoopKatSuiteReport.implementsVaultCryptoProvider ||
                providerOperationNoopKatValidation.implementsVaultCryptoProvider ||
                providerOperationNoopKat.implementsVaultCryptoProvider ||
                providerOperationNoopKatAdmission.implementsVaultCryptoProvider ||
                providerOperationMetadataKatSuiteReport.implementsVaultCryptoProvider ||
                capabilityMatrix.implementsVaultCryptoProvider
        val containsVaultCryptoProvider =
            providerOperationNoopExecutionBoundaryValidation.containsVaultCryptoProvider ||
                providerOperationNoopExecutionBoundary.containsVaultCryptoProvider ||
                providerOperationNoopKatSuiteReport.containsVaultCryptoProvider ||
                providerOperationNoopKatValidation.containsVaultCryptoProvider ||
                providerOperationNoopKat.containsVaultCryptoProvider ||
                providerOperationNoopKatAdmission.containsVaultCryptoProvider ||
                providerOperationMetadataKatSuiteReport.containsVaultCryptoProvider ||
                capabilityMatrix.containsVaultCryptoProvider
        val canExecuteProviderOperations =
            providerOperationNoopExecutionBoundaryValidation.canExecuteProviderOperations ||
                providerOperationNoopExecutionBoundary.canExecuteProviderOperations ||
                providerOperationNoopKatSuiteReport.canExecuteProviderOperations ||
                providerOperationNoopKatValidation.canExecuteProviderOperations ||
                providerOperationNoopKat.canExecuteProviderOperations ||
                providerOperationNoopKatAdmission.canExecuteProviderOperations ||
                providerOperationMetadataKatSuiteReport.canExecuteProviderOperations ||
                capabilityMatrix.canExecuteProviderOperations ||
                realProviderOperationExecutionPermitted
        val canExecuteCrypto =
            providerOperationNoopExecutionBoundaryValidation.canExecuteCrypto ||
                providerOperationNoopExecutionBoundary.canExecuteCrypto ||
                providerOperationNoopKatSuiteReport.canExecuteCrypto ||
                providerOperationNoopKatValidation.canExecuteCrypto ||
                providerOperationNoopKat.canExecuteCrypto ||
                providerOperationNoopKatAdmission.canExecuteCrypto ||
                providerOperationMetadataKatSuiteReport.canExecuteCrypto ||
                capabilityMatrix.canExecuteCrypto ||
                cryptoExecutionPermitted
        val canUseForVaultLifecycle =
            providerOperationNoopExecutionBoundaryValidation.canUseForVaultLifecycle ||
                providerOperationNoopExecutionBoundary.canUseForVaultLifecycle ||
                providerOperationNoopKatSuiteReport.canUseForVaultLifecycle ||
                providerOperationNoopKatValidation.canUseForVaultLifecycle ||
                providerOperationNoopKat.canUseForVaultLifecycle ||
                providerOperationNoopKatAdmission.canUseForVaultLifecycle ||
                providerOperationMetadataKatSuiteReport.canUseForVaultLifecycle ||
                capabilityMatrix.canUseForVaultLifecycle
        val canUseForPersistence =
            providerOperationNoopExecutionBoundaryValidation.canUseForPersistence ||
                providerOperationNoopExecutionBoundary.canUseForPersistence ||
                providerOperationNoopKatSuiteReport.canUseForPersistence ||
                providerOperationNoopKatValidation.canUseForPersistence ||
                providerOperationNoopKat.canUseForPersistence ||
                providerOperationNoopKatAdmission.canUseForPersistence ||
                providerOperationMetadataKatSuiteReport.canUseForPersistence ||
                capabilityMatrix.canUseForPersistence ||
                vaultPersistencePermitted
        val canUseForSync =
            providerOperationNoopExecutionBoundaryValidation.canUseForSync ||
                providerOperationNoopExecutionBoundary.canUseForSync ||
                providerOperationNoopKatSuiteReport.canUseForSync ||
                providerOperationNoopKatValidation.canUseForSync ||
                providerOperationNoopKat.canUseForSync ||
                providerOperationNoopKatAdmission.canUseForSync ||
                providerOperationMetadataKatSuiteReport.canUseForSync ||
                capabilityMatrix.canUseForSync
        val canUseForSigning =
            providerOperationNoopExecutionBoundaryValidation.canUseForSigning ||
                providerOperationNoopExecutionBoundary.canUseForSigning ||
                providerOperationNoopKatSuiteReport.canUseForSigning ||
                providerOperationNoopKatValidation.canUseForSigning ||
                providerOperationNoopKat.canUseForSigning ||
                providerOperationNoopKatAdmission.canUseForSigning ||
                providerOperationMetadataKatSuiteReport.canUseForSigning ||
                capabilityMatrix.canUseForSigning
        val canUseForBroadcasting =
            providerOperationNoopExecutionBoundaryValidation.canUseForBroadcasting ||
                providerOperationNoopExecutionBoundary.canUseForBroadcasting ||
                providerOperationNoopKatSuiteReport.canUseForBroadcasting ||
                providerOperationNoopKatValidation.canUseForBroadcasting ||
                providerOperationNoopKat.canUseForBroadcasting ||
                providerOperationNoopKatAdmission.canUseForBroadcasting ||
                providerOperationMetadataKatSuiteReport.canUseForBroadcasting ||
                capabilityMatrix.canUseForBroadcasting
        val canUseForMainnet =
            providerOperationNoopExecutionBoundaryValidation.canUseForMainnet ||
                providerOperationNoopExecutionBoundary.canUseForMainnet ||
                providerOperationNoopKatSuiteReport.canUseForMainnet ||
                providerOperationNoopKatValidation.canUseForMainnet ||
                providerOperationNoopKat.canUseForMainnet ||
                providerOperationNoopKatAdmission.canUseForMainnet ||
                providerOperationMetadataKatSuiteReport.canUseForMainnet ||
                capabilityMatrix.canUseForMainnet ||
                mainnetPermitted
        val productionProviderSelectable =
            providerOperationNoopExecutionBoundaryValidation.productionProviderSelectable ||
                providerOperationNoopExecutionBoundary.productionProviderSelectable ||
                providerOperationNoopKatSuiteReport.productionProviderSelectable ||
                providerOperationNoopKatValidation.productionProviderSelectable ||
                providerOperationNoopKat.productionProviderSelectable ||
                providerOperationNoopKatAdmission.productionProviderSelectable ||
                providerOperationMetadataKatSuiteReport.productionProviderSelectable ||
                capabilityMatrix.productionProviderSelectable ||
                marker.productionProviderSelectable

        val suiteIsCommonTestOnly =
            providerOperationNoopExecutionBoundaryValidation.validationIsCommonTestOnly &&
                providerOperationNoopExecutionBoundary.boundaryIsCommonTestOnly &&
                providerOperationNoopKatSuiteReport.suiteIsCommonTestOnly &&
                providerOperationNoopKatValidation.validationIsCommonTestOnly &&
                providerOperationNoopKat.noopKatIsCommonTestOnly &&
                providerOperationNoopKatAdmission.admissionIsCommonTestOnly &&
                providerOperationMetadataKatSuiteReport.suiteIsCommonTestOnly &&
                capabilityMatrix.matrixIsCommonTestOnly &&
                marker.sourceSet == SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet.CommonTest
        val suiteReportsSyntheticNoopOnly =
            providerOperationNoopExecutionBoundaryValidation.validatesSyntheticNoopOnly &&
                providerOperationNoopExecutionBoundary.evaluatesSyntheticNoopOnly &&
                providerOperationNoopKatSuiteReport.suiteReportsSyntheticNoopOnly &&
                providerOperationNoopKatValidation.validatesSyntheticNoopOnly &&
                providerOperationNoopKat.evaluatesSyntheticNoopOnly &&
                noopExecutionBoundaryValidationPassed &&
                noopExecutionBoundaryModeled &&
                syntheticNoopEvaluationPermitted &&
                syntheticNoopResultPresent
        val suiteReportsProviderOperationExecution =
            providerOperationNoopExecutionBoundaryValidation.validatesProviderOperationExecution ||
                providerOperationNoopExecutionBoundary.evaluatesProviderOperationExecution ||
                providerOperationNoopKatSuiteReport.suiteReportsProviderOperationExecution ||
                providerOperationNoopKatValidation.validatesProviderOperationExecution ||
                providerOperationNoopKat.evaluatesProviderOperationExecution ||
                providerOperationExecutionPresent ||
                realProviderOperationExecutionPermitted
        val suiteReportsCryptoOperation =
            providerOperationNoopExecutionBoundaryValidation.validatesCryptoOperation ||
                providerOperationNoopExecutionBoundary.evaluatesCryptoOperation ||
                providerOperationNoopKatSuiteReport.suiteReportsCryptoOperation ||
                providerOperationNoopKatValidation.validatesCryptoOperation ||
                providerOperationNoopKat.evaluatesCryptoOperation ||
                cryptoExecutionPresent ||
                cryptoExecutionPermitted
        val suiteReportsVaultLifecycle =
            providerOperationNoopExecutionBoundaryValidation.validatesVaultLifecycle ||
                providerOperationNoopExecutionBoundary.evaluatesVaultLifecycle ||
                providerOperationNoopKatSuiteReport.suiteReportsVaultLifecycle ||
                providerOperationNoopKatValidation.validatesVaultLifecycle ||
                providerOperationNoopKat.evaluatesVaultLifecycle ||
                vaultLifecycleReachable
        val suiteReportsPersistence =
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
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundarySuiteReportSafeLabel(
                    "no-op provider-operation execution-boundary suite report evidence",
                ).toString(),
            ).none { output ->
                EXPECTED_SAFE_ID in output ||
                    EXPECTED_FIXTURE_ID in output ||
                    EXPECTED_VECTOR_ID in output ||
                    EXPECTED_CASE_ID in output ||
                    EXPECTED_PROVIDER_OPERATION_METADATA_KAT_ID in output ||
                    EXPECTED_PROVIDER_OPERATION_NOOP_KAT_ID in output ||
                    EXPECTED_PROVIDER_OPERATION_NOOP_EXECUTION_BOUNDARY_ID in output ||
                    EXPECTED_PROVIDER_OPERATION_NOOP_EXECUTION_BOUNDARY_SUITE_REPORT_ID in output
            }

        val suiteReportGenerated =
            suiteIsCommonTestOnly &&
                markerCount == 1 &&
                caseBindingCount == 1 &&
                providerOperationMetadataKatCount == 1 &&
                providerOperationMetadataKatValidationCount == 1 &&
                providerOperationMetadataKatSuiteReportCount == 1 &&
                providerOperationNoopKatAdmissionCount == 1 &&
                providerOperationNoopKatCount == 1 &&
                providerOperationNoopKatValidationCount == 1 &&
                providerOperationNoopKatSuiteReportCount == 1 &&
                providerOperationNoopExecutionBoundaryCount == 1 &&
                providerOperationNoopExecutionBoundaryValidationCount == 1 &&
                expectedSafeIdMatched &&
                expectedFixtureIdMatched &&
                expectedVectorIdMatched &&
                expectedCaseIdMatched &&
                expectedProviderOperationMetadataKatIdMatched &&
                expectedProviderOperationNoopKatIdMatched &&
                expectedProviderOperationNoopExecutionBoundaryIdMatched &&
                expectedProviderOperationNoopExecutionBoundarySuiteReportIdMatched &&
                providerOperationNoopKatPassed &&
                providerOperationNoopKatValidationPassed &&
                providerOperationNoopKatSuitePassed &&
                syntheticNoopResultPresent &&
                noopExecutionBoundaryModeled &&
                syntheticNoopEvaluationPermitted &&
                noopExecutionBoundaryValidationPassed &&
                suiteReportsSyntheticNoopOnly &&
                safeOutputRedactionSatisfied &&
                !suiteReportsProviderOperationExecution &&
                !suiteReportsCryptoOperation &&
                !suiteReportsVaultLifecycle &&
                !suiteReportsPersistence &&
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
        val noopExecutionBoundarySuitePassed =
            suiteReportGenerated &&
                !realProviderOperationExecutionPermitted &&
                !cryptoExecutionPermitted &&
                !katRunnerPermitted &&
                !katExecutorPermitted &&
                !vaultPersistencePermitted &&
                !mainnetPermitted

        return SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundarySuiteReport(
            markerCount = markerCount,
            caseBindingCount = caseBindingCount,
            providerOperationMetadataKatCount = providerOperationMetadataKatCount,
            providerOperationMetadataKatValidationCount = providerOperationMetadataKatValidationCount,
            providerOperationMetadataKatSuiteReportCount = providerOperationMetadataKatSuiteReportCount,
            providerOperationNoopKatAdmissionCount = providerOperationNoopKatAdmissionCount,
            providerOperationNoopKatCount = providerOperationNoopKatCount,
            providerOperationNoopKatValidationCount = providerOperationNoopKatValidationCount,
            providerOperationNoopKatSuiteReportCount = providerOperationNoopKatSuiteReportCount,
            providerOperationNoopExecutionBoundaryCount = providerOperationNoopExecutionBoundaryCount,
            providerOperationNoopExecutionBoundaryValidationCount =
                providerOperationNoopExecutionBoundaryValidationCount,
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
            providerOperationNoopKatPassed = providerOperationNoopKatPassed,
            providerOperationNoopKatValidationPassed = providerOperationNoopKatValidationPassed,
            providerOperationNoopKatSuitePassed = providerOperationNoopKatSuitePassed,
            syntheticNoopResultPresent = syntheticNoopResultPresent,
            noopExecutionBoundaryModeled = noopExecutionBoundaryModeled,
            syntheticNoopEvaluationPermitted = syntheticNoopEvaluationPermitted,
            noopExecutionBoundaryValidationPassed = noopExecutionBoundaryValidationPassed,
            suiteReportGenerated = suiteReportGenerated,
            noopExecutionBoundarySuitePassed = noopExecutionBoundarySuitePassed,
            suiteIsCommonTestOnly = suiteIsCommonTestOnly,
            suiteIsProductionAuthorization = false,
            suiteIsProviderSelectionAuthorization = false,
            suiteIsProviderOperationAuthorization = false,
            suiteIsKatExecutorAuthorization = false,
            suiteIsCryptoAuthorization = false,
            suiteIsVaultPersistenceAuthorization = false,
            suiteIsMainnetAuthorization = false,
            suiteReportsSyntheticNoopOnly = suiteReportsSyntheticNoopOnly,
            suiteReportsProviderOperationExecution = suiteReportsProviderOperationExecution,
            suiteReportsCryptoOperation = suiteReportsCryptoOperation,
            suiteReportsVaultLifecycle = suiteReportsVaultLifecycle,
            suiteReportsPersistence = suiteReportsPersistence,
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
            displayLabel =
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundarySuiteReportSafeLabel(
                    "no-op provider-operation execution-boundary suite report evidence",
                ),
        )
    }
}
