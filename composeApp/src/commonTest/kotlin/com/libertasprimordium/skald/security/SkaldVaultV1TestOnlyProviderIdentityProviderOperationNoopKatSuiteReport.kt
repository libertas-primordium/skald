package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatSuiteReportSafeLabel(
    val value: String,
) {
    override fun toString(): String =
        "RedactedTestOnlyProviderIdentityProviderOperationNoopKatSuiteReportSafeLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatSuiteReportSourceSet {
    CommonTest,
}

data class SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatSuiteReport(
    val markerCount: Int,
    val caseBindingCount: Int,
    val executableMetadataKatSuiteReportCount: Int,
    val providerOperationKatAdmissionCount: Int,
    val providerOperationMetadataKatCount: Int,
    val providerOperationMetadataKatValidationCount: Int,
    val providerOperationMetadataKatSuiteReportCount: Int,
    val providerOperationNoopKatAdmissionCount: Int,
    val providerOperationNoopKatCount: Int,
    val providerOperationNoopKatValidationCount: Int,
    val expectedSafeIdMatched: Boolean,
    val expectedFixtureIdMatched: Boolean,
    val expectedVectorIdMatched: Boolean,
    val expectedCaseIdMatched: Boolean,
    val expectedProviderOperationMetadataKatIdMatched: Boolean,
    val expectedProviderOperationNoopKatIdMatched: Boolean,
    val metadataKatSuitePassed: Boolean,
    val providerOperationMetadataKatPassed: Boolean,
    val providerOperationMetadataKatValidationPassed: Boolean,
    val providerOperationMetadataKatSuitePassed: Boolean,
    val providerOperationNoopKatAdmissionModeled: Boolean,
    val providerOperationNoopKatEvaluated: Boolean,
    val providerOperationNoopKatPassed: Boolean,
    val providerOperationNoopKatValidationPassed: Boolean,
    val syntheticNoopResultPresent: Boolean,
    val suiteReportGenerated: Boolean,
    val providerOperationNoopKatSuitePassed: Boolean,
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
    val sourceSet: SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatSuiteReportSourceSet,
    val displayLabel: SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatSuiteReportSafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatSuiteReport(redactedMarkerId, redactedFixtureId, redactedVectorId, redactedCaseId, redactedProviderOperationMetadataKatId, redactedProviderOperationNoopKatId, commonTestOnly, syntheticNoopSuiteOnly, nonAuthorizing)"
}

object SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatSuiteReportPolicy {
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

    fun currentProviderOperationNoopKatSuiteReport():
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatSuiteReport {
        val marker = SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy.currentMarker()
        val capabilityMatrix =
            SkaldVaultV1TestOnlyProviderIdentityCapabilityMatrixPolicy.currentCapabilityMatrix()
        val executableMetadataKatSuiteReport =
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatSuiteReportPolicy
                .currentExecutableMetadataKatSuiteReport()
        val providerOperationKatAdmission =
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationKatAdmissionPolicy
                .currentProviderOperationKatAdmission()
        val providerOperationMetadataKat =
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatPolicy
                .evaluateCurrentProviderOperationMetadataKat()
        val providerOperationMetadataKatValidation =
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationPolicy
                .currentProviderOperationMetadataKatValidationReport()
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

        val markerCount =
            if (
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
        val caseBindingCount = providerOperationNoopKatValidation.caseBindingCount
        val executableMetadataKatSuiteReportCount =
            providerOperationNoopKatValidation.executableMetadataKatSuiteReportCount
        val providerOperationKatAdmissionCount =
            providerOperationNoopKatValidation.providerOperationKatAdmissionCount
        val providerOperationMetadataKatCount =
            providerOperationNoopKatValidation.providerOperationMetadataKatCount
        val providerOperationMetadataKatValidationCount =
            providerOperationNoopKatValidation.providerOperationMetadataKatValidationCount
        val providerOperationMetadataKatSuiteReportCount =
            providerOperationNoopKatValidation.providerOperationMetadataKatSuiteReportCount
        val providerOperationNoopKatAdmissionCount =
            providerOperationNoopKatValidation.providerOperationNoopKatAdmissionCount
        val providerOperationNoopKatCount = providerOperationNoopKatValidation.providerOperationNoopKatCount
        val providerOperationNoopKatValidationCount =
            if (
                providerOperationNoopKatValidation.allValidationChecksPassed &&
                providerOperationNoopKatValidation.providerOperationNoopKatPassed &&
                providerOperationNoopKatValidation.syntheticNoopResultPresent
            ) {
                1
            } else {
                0
            }

        val expectedSafeIdMatched =
            markerCount == 1 &&
                providerOperationNoopKatValidation.expectedSafeIdMatched &&
                providerOperationNoopKat.expectedSafeIdMatched &&
                providerOperationNoopKatAdmission.expectedSafeIdMatched &&
                providerOperationMetadataKatSuiteReport.expectedSafeIdMatched &&
                providerOperationMetadataKatValidation.expectedSafeIdMatched &&
                providerOperationMetadataKat.expectedSafeIdMatched &&
                providerOperationKatAdmission.expectedSafeIdMatched &&
                executableMetadataKatSuiteReport.expectedSafeIdMatched
        val expectedFixtureIdMatched =
            providerOperationNoopKatValidation.expectedFixtureIdMatched &&
                providerOperationNoopKat.expectedFixtureIdMatched &&
                providerOperationNoopKatAdmission.expectedFixtureIdMatched &&
                providerOperationMetadataKatSuiteReport.expectedFixtureIdMatched &&
                providerOperationMetadataKatValidation.expectedFixtureIdMatched &&
                providerOperationMetadataKat.expectedFixtureIdMatched &&
                providerOperationKatAdmission.expectedFixtureIdMatched &&
                executableMetadataKatSuiteReport.expectedFixtureIdMatched &&
                EXPECTED_FIXTURE_ID ==
                "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata"
        val expectedVectorIdMatched =
            providerOperationNoopKatValidation.expectedVectorIdMatched &&
                providerOperationNoopKat.expectedVectorIdMatched &&
                providerOperationNoopKatAdmission.expectedVectorIdMatched &&
                providerOperationMetadataKatSuiteReport.expectedVectorIdMatched &&
                providerOperationMetadataKatValidation.expectedVectorIdMatched &&
                providerOperationMetadataKat.expectedVectorIdMatched &&
                providerOperationKatAdmission.expectedVectorIdMatched &&
                executableMetadataKatSuiteReport.expectedVectorIdMatched &&
                EXPECTED_VECTOR_ID ==
                "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata"
        val expectedCaseIdMatched =
            providerOperationNoopKatValidation.expectedCaseIdMatched &&
                providerOperationNoopKat.expectedCaseIdMatched &&
                providerOperationNoopKatAdmission.expectedCaseIdMatched &&
                providerOperationMetadataKatSuiteReport.expectedCaseIdMatched &&
                providerOperationMetadataKatValidation.expectedCaseIdMatched &&
                providerOperationMetadataKat.expectedCaseIdMatched &&
                providerOperationKatAdmission.expectedCaseIdMatched &&
                executableMetadataKatSuiteReport.expectedCaseIdMatched &&
                EXPECTED_CASE_ID ==
                "skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata"
        val expectedProviderOperationMetadataKatIdMatched =
            providerOperationNoopKatValidation.expectedProviderOperationMetadataKatIdMatched &&
                providerOperationNoopKat.expectedProviderOperationMetadataKatIdMatched &&
                providerOperationNoopKatAdmission.expectedProviderOperationMetadataKatIdMatched &&
                providerOperationMetadataKatSuiteReport.expectedProviderOperationMetadataKatIdMatched &&
                providerOperationMetadataKatValidation.expectedProviderOperationMetadataKatIdMatched &&
                providerOperationMetadataKat.expectedProviderOperationMetadataKatIdMatched &&
                EXPECTED_PROVIDER_OPERATION_METADATA_KAT_ID ==
                "skald-test-only-provider-identity-provider-operation-metadata-kat-v1-inert-identity"
        val expectedProviderOperationNoopKatIdMatched =
            providerOperationNoopKatValidation.expectedProviderOperationNoopKatIdMatched &&
                providerOperationNoopKat.expectedProviderOperationNoopKatIdMatched &&
                providerOperationNoopKatAdmission.expectedProviderOperationNoopKatIdMatched &&
                EXPECTED_PROVIDER_OPERATION_NOOP_KAT_ID ==
                "skald-test-only-provider-identity-provider-operation-noop-kat-v1-inert-identity"

        val metadataKatSuitePassed =
            providerOperationNoopKatValidation.metadataKatSuitePassed &&
                providerOperationNoopKat.metadataKatSuitePassed &&
                providerOperationNoopKatAdmission.metadataKatSuitePassed &&
                providerOperationMetadataKatSuiteReport.metadataKatSuitePassed &&
                providerOperationMetadataKatValidation.metadataKatSuitePassed &&
                providerOperationMetadataKat.metadataKatSuitePassed &&
                providerOperationKatAdmission.metadataKatSuitePassed &&
                executableMetadataKatSuiteReport.suitePassed
        val providerOperationMetadataKatPassed =
            providerOperationNoopKatValidation.providerOperationMetadataKatPassed &&
                providerOperationNoopKat.providerOperationMetadataKatPassed &&
                providerOperationNoopKatAdmission.providerOperationMetadataKatPassed &&
                providerOperationMetadataKatSuiteReport.providerOperationMetadataKatPassed &&
                providerOperationMetadataKatValidation.providerOperationMetadataKatPassed &&
                providerOperationMetadataKat.providerOperationMetadataKatPassed
        val providerOperationMetadataKatValidationPassed =
            providerOperationNoopKatValidation.providerOperationMetadataKatValidationPassed &&
                providerOperationNoopKat.providerOperationMetadataKatValidationPassed &&
                providerOperationNoopKatAdmission.providerOperationMetadataKatValidationPassed &&
                providerOperationMetadataKatSuiteReport.providerOperationMetadataKatValidationPassed &&
                providerOperationMetadataKatValidation.allValidationChecksPassed
        val providerOperationMetadataKatSuitePassed =
            providerOperationNoopKatValidation.providerOperationMetadataKatSuitePassed &&
                providerOperationNoopKat.providerOperationMetadataKatSuitePassed &&
                providerOperationNoopKatAdmission.providerOperationMetadataKatSuitePassed &&
                providerOperationMetadataKatSuiteReport.providerOperationMetadataKatSuitePassed
        val providerOperationNoopKatAdmissionModeled =
            providerOperationNoopKatValidation.providerOperationNoopKatAdmissionModeled &&
                providerOperationNoopKat.providerOperationNoopKatAdmissionModeled &&
                providerOperationNoopKatAdmission.futureNoopProviderOperationKatCriteriaModeled &&
                !providerOperationNoopKatAdmission.futureNoopProviderOperationKatCriteriaAuthorizeCurrentExecution
        val providerOperationNoopKatEvaluated =
            providerOperationNoopKatValidation.providerOperationNoopKatEvaluated &&
                providerOperationNoopKat.providerOperationNoopKatEvaluated
        val providerOperationNoopKatPassed =
            providerOperationNoopKatValidation.providerOperationNoopKatPassed &&
                providerOperationNoopKat.providerOperationNoopKatPassed
        val providerOperationNoopKatValidationPassed =
            providerOperationNoopKatValidation.allValidationChecksPassed
        val syntheticNoopResultPresent =
            providerOperationNoopKatValidation.syntheticNoopResultPresent &&
                providerOperationNoopKat.syntheticNoopResultPresent

        val providerOperationKatExecutorPresent =
            providerOperationNoopKatValidation.providerOperationKatExecutorPresent ||
                providerOperationNoopKat.providerOperationKatExecutorPresent ||
                providerOperationNoopKatAdmission.currentKatExecutorPresent ||
                providerOperationMetadataKatSuiteReport.providerOperationKatExecutorPresent ||
                providerOperationMetadataKatValidation.providerOperationKatExecutorPresent ||
                providerOperationMetadataKat.providerOperationKatExecutorPresent
        val providerOperationKatRunnerPresent =
            providerOperationNoopKatValidation.providerOperationKatRunnerPresent ||
                providerOperationNoopKat.providerOperationKatRunnerPresent ||
                providerOperationNoopKatAdmission.currentKatRunnerPresent ||
                providerOperationMetadataKatSuiteReport.providerOperationKatRunnerPresent ||
                providerOperationMetadataKatValidation.providerOperationKatRunnerPresent ||
                providerOperationMetadataKat.providerOperationKatRunnerPresent
        val providerOperationExecutionPresent =
            providerOperationNoopKatValidation.providerOperationExecutionPresent ||
                providerOperationNoopKat.providerOperationExecutionPresent ||
                providerOperationNoopKatAdmission.currentProviderOperationExecutionPresent ||
                providerOperationNoopKatAdmission.currentNoopProviderOperationExecutionPresent ||
                providerOperationMetadataKatSuiteReport.providerOperationExecutionPresent ||
                providerOperationMetadataKatValidation.providerOperationExecutionPresent ||
                providerOperationMetadataKat.providerOperationExecutionPresent
        val cryptoExecutionPresent =
            providerOperationNoopKatValidation.cryptoExecutionPresent ||
                providerOperationNoopKat.cryptoExecutionPresent ||
                providerOperationNoopKatAdmission.currentCryptoExecutionPresent ||
                providerOperationMetadataKatSuiteReport.cryptoExecutionPresent ||
                providerOperationMetadataKatValidation.cryptoExecutionPresent ||
                providerOperationMetadataKat.cryptoExecutionPresent
        val rawKatMaterialPresent =
            providerOperationNoopKatValidation.rawKatMaterialPresent ||
                providerOperationNoopKat.rawKatMaterialPresent ||
                providerOperationNoopKatAdmission.rawKatMaterialPresent ||
                providerOperationMetadataKatSuiteReport.rawKatMaterialPresent ||
                providerOperationMetadataKatValidation.rawKatMaterialPresent ||
                providerOperationMetadataKat.rawKatMaterialPresent
        val rawVectorBytesPresent =
            providerOperationNoopKatValidation.rawVectorBytesPresent ||
                providerOperationNoopKat.rawVectorBytesPresent ||
                providerOperationNoopKatAdmission.rawVectorBytesPresent ||
                providerOperationMetadataKatSuiteReport.rawVectorBytesPresent ||
                providerOperationMetadataKatValidation.rawVectorBytesPresent ||
                providerOperationMetadataKat.rawVectorBytesPresent
        val rawVectorHexPresent =
            providerOperationNoopKatValidation.rawVectorHexPresent ||
                providerOperationNoopKat.rawVectorHexPresent ||
                providerOperationNoopKatAdmission.rawVectorHexPresent ||
                providerOperationMetadataKatSuiteReport.rawVectorHexPresent ||
                providerOperationMetadataKatValidation.rawVectorHexPresent ||
                providerOperationMetadataKat.rawVectorHexPresent
        val publicVectorBytesPresent =
            providerOperationNoopKatValidation.publicVectorBytesPresent ||
                providerOperationNoopKat.publicVectorBytesPresent ||
                providerOperationNoopKatAdmission.publicVectorBytesPresent ||
                providerOperationMetadataKatSuiteReport.publicVectorBytesPresent ||
                providerOperationMetadataKatValidation.publicVectorBytesPresent ||
                providerOperationMetadataKat.publicVectorBytesPresent
        val publicVectorHexPresent =
            providerOperationNoopKatValidation.publicVectorHexPresent ||
                providerOperationNoopKat.publicVectorHexPresent ||
                providerOperationNoopKatAdmission.publicVectorHexPresent ||
                providerOperationMetadataKatSuiteReport.publicVectorHexPresent ||
                providerOperationMetadataKatValidation.publicVectorHexPresent ||
                providerOperationMetadataKat.publicVectorHexPresent

        val runtimeSelectable =
            providerOperationNoopKatValidation.runtimeSelectable ||
                providerOperationNoopKat.runtimeSelectable ||
                providerOperationNoopKatAdmission.runtimeSelectable ||
                providerOperationMetadataKatSuiteReport.runtimeSelectable ||
                providerOperationMetadataKatValidation.runtimeSelectable ||
                providerOperationMetadataKat.runtimeSelectable ||
                capabilityMatrix.runtimeSelectable
        val registrySelectable =
            providerOperationNoopKatValidation.registrySelectable ||
                providerOperationNoopKat.registrySelectable ||
                providerOperationNoopKatAdmission.registrySelectable ||
                providerOperationMetadataKatSuiteReport.registrySelectable ||
                providerOperationMetadataKatValidation.registrySelectable ||
                providerOperationMetadataKat.registrySelectable ||
                capabilityMatrix.registrySelectable
        val factoryReachable =
            providerOperationNoopKatValidation.factoryReachable ||
                providerOperationNoopKat.factoryReachable ||
                providerOperationNoopKatAdmission.factoryReachable ||
                providerOperationMetadataKatSuiteReport.factoryReachable ||
                providerOperationMetadataKatValidation.factoryReachable ||
                providerOperationMetadataKat.factoryReachable ||
                capabilityMatrix.factoryReachable
        val dispatcherReachable =
            providerOperationNoopKatValidation.dispatcherReachable ||
                providerOperationNoopKat.dispatcherReachable ||
                providerOperationNoopKatAdmission.dispatcherReachable ||
                providerOperationMetadataKatSuiteReport.dispatcherReachable ||
                providerOperationMetadataKatValidation.dispatcherReachable ||
                providerOperationMetadataKat.dispatcherReachable ||
                capabilityMatrix.dispatcherReachable
        val executorTargetable =
            providerOperationNoopKatValidation.executorTargetable ||
                providerOperationNoopKat.executorTargetable ||
                providerOperationNoopKatAdmission.executorTargetable ||
                providerOperationMetadataKatSuiteReport.executorTargetable ||
                providerOperationMetadataKatValidation.executorTargetable ||
                providerOperationMetadataKat.executorTargetable ||
                capabilityMatrix.executorTargetable
        val providerKatExecutorReachable =
            providerOperationNoopKatValidation.providerKatExecutorReachable ||
                providerOperationNoopKat.providerKatExecutorReachable ||
                providerOperationNoopKatAdmission.providerKatExecutorReachable ||
                providerOperationMetadataKatSuiteReport.providerKatExecutorReachable ||
                providerOperationMetadataKatValidation.providerKatExecutorReachable ||
                providerOperationMetadataKat.providerKatExecutorReachable ||
                capabilityMatrix.providerKatExecutorReachable
        val providerOperationReachable =
            providerOperationNoopKatValidation.providerOperationReachable ||
                providerOperationNoopKat.providerOperationReachable ||
                providerOperationNoopKatAdmission.providerOperationReachable ||
                providerOperationMetadataKatSuiteReport.providerOperationReachable ||
                providerOperationMetadataKatValidation.providerOperationReachable ||
                providerOperationMetadataKat.providerOperationReachable ||
                capabilityMatrix.providerOperationReachable ||
                providerOperationExecutionPresent
        val cryptoExecutionReachable =
            providerOperationNoopKatValidation.cryptoExecutionReachable ||
                providerOperationNoopKat.cryptoExecutionReachable ||
                providerOperationNoopKatAdmission.cryptoExecutionReachable ||
                providerOperationMetadataKatSuiteReport.cryptoExecutionReachable ||
                providerOperationMetadataKatValidation.cryptoExecutionReachable ||
                providerOperationMetadataKat.cryptoExecutionReachable ||
                capabilityMatrix.cryptoExecutionReachable ||
                cryptoExecutionPresent
        val vaultLifecycleReachable =
            providerOperationNoopKatValidation.vaultLifecycleReachable ||
                providerOperationNoopKat.vaultLifecycleReachable ||
                providerOperationNoopKatAdmission.vaultLifecycleReachable ||
                providerOperationMetadataKatSuiteReport.vaultLifecycleReachable ||
                providerOperationMetadataKatValidation.vaultLifecycleReachable ||
                providerOperationMetadataKat.vaultLifecycleReachable ||
                capabilityMatrix.vaultLifecycleReachable
        val persistenceReachable =
            providerOperationNoopKatValidation.persistenceReachable ||
                providerOperationNoopKat.persistenceReachable ||
                providerOperationNoopKatAdmission.persistenceReachable ||
                providerOperationMetadataKatSuiteReport.persistenceReachable ||
                providerOperationMetadataKatValidation.persistenceReachable ||
                providerOperationMetadataKat.persistenceReachable ||
                capabilityMatrix.persistenceReachable
        val productionSyncReachable =
            providerOperationNoopKatValidation.productionSyncReachable ||
                providerOperationNoopKat.productionSyncReachable ||
                providerOperationNoopKatAdmission.productionSyncReachable ||
                providerOperationMetadataKatSuiteReport.productionSyncReachable ||
                providerOperationMetadataKatValidation.productionSyncReachable ||
                providerOperationMetadataKat.productionSyncReachable ||
                capabilityMatrix.productionSyncReachable
        val backendClientReachable =
            providerOperationNoopKatValidation.backendClientReachable ||
                providerOperationNoopKat.backendClientReachable ||
                providerOperationNoopKatAdmission.backendClientReachable ||
                providerOperationMetadataKatSuiteReport.backendClientReachable ||
                providerOperationMetadataKatValidation.backendClientReachable ||
                providerOperationMetadataKat.backendClientReachable ||
                capabilityMatrix.backendClientReachable
        val bdkWalletStateReachable =
            providerOperationNoopKatValidation.bdkWalletStateReachable ||
                providerOperationNoopKat.bdkWalletStateReachable ||
                providerOperationNoopKatAdmission.bdkWalletStateReachable ||
                providerOperationMetadataKatSuiteReport.bdkWalletStateReachable ||
                providerOperationMetadataKatValidation.bdkWalletStateReachable ||
                providerOperationMetadataKat.bdkWalletStateReachable ||
                capabilityMatrix.bdkWalletStateReachable
        val settingsCodecReachable =
            providerOperationNoopKatValidation.settingsCodecReachable ||
                providerOperationNoopKat.settingsCodecReachable ||
                providerOperationNoopKatAdmission.settingsCodecReachable ||
                providerOperationMetadataKatSuiteReport.settingsCodecReachable ||
                providerOperationMetadataKatValidation.settingsCodecReachable ||
                providerOperationMetadataKat.settingsCodecReachable ||
                capabilityMatrix.settingsCodecReachable
        val uiSurfaceReachable =
            providerOperationNoopKatValidation.uiSurfaceReachable ||
                providerOperationNoopKat.uiSurfaceReachable ||
                providerOperationNoopKatAdmission.uiSurfaceReachable ||
                providerOperationMetadataKatSuiteReport.uiSurfaceReachable ||
                providerOperationMetadataKatValidation.uiSurfaceReachable ||
                providerOperationMetadataKat.uiSurfaceReachable ||
                capabilityMatrix.uiSurfaceReachable
        val signingBroadcastingReachable =
            providerOperationNoopKatValidation.signingBroadcastingReachable ||
                providerOperationNoopKat.signingBroadcastingReachable ||
                providerOperationNoopKatAdmission.signingBroadcastingReachable ||
                providerOperationMetadataKatSuiteReport.signingBroadcastingReachable ||
                providerOperationMetadataKatValidation.signingBroadcastingReachable ||
                providerOperationMetadataKat.signingBroadcastingReachable ||
                capabilityMatrix.signingBroadcastingReachable
        val publicEndpointReachable =
            providerOperationNoopKatValidation.publicEndpointReachable ||
                providerOperationNoopKat.publicEndpointReachable ||
                providerOperationNoopKatAdmission.publicEndpointReachable ||
                providerOperationMetadataKatSuiteReport.publicEndpointReachable ||
                providerOperationMetadataKatValidation.publicEndpointReachable ||
                providerOperationMetadataKat.publicEndpointReachable ||
                capabilityMatrix.publicEndpointReachable
        val mainnetReachable =
            providerOperationNoopKatValidation.mainnetReachable ||
                providerOperationNoopKat.mainnetReachable ||
                providerOperationNoopKatAdmission.mainnetReachable ||
                providerOperationMetadataKatSuiteReport.mainnetReachable ||
                providerOperationMetadataKatValidation.mainnetReachable ||
                providerOperationMetadataKat.mainnetReachable ||
                capabilityMatrix.mainnetReachable
        val implementsVaultCryptoProvider =
            providerOperationNoopKatValidation.implementsVaultCryptoProvider ||
                providerOperationNoopKat.implementsVaultCryptoProvider ||
                providerOperationNoopKatAdmission.implementsVaultCryptoProvider ||
                providerOperationMetadataKatSuiteReport.implementsVaultCryptoProvider ||
                providerOperationMetadataKatValidation.implementsVaultCryptoProvider ||
                providerOperationMetadataKat.implementsVaultCryptoProvider ||
                capabilityMatrix.implementsVaultCryptoProvider
        val containsVaultCryptoProvider =
            providerOperationNoopKatValidation.containsVaultCryptoProvider ||
                providerOperationNoopKat.containsVaultCryptoProvider ||
                providerOperationNoopKatAdmission.containsVaultCryptoProvider ||
                providerOperationMetadataKatSuiteReport.containsVaultCryptoProvider ||
                providerOperationMetadataKatValidation.containsVaultCryptoProvider ||
                providerOperationMetadataKat.containsVaultCryptoProvider ||
                capabilityMatrix.containsVaultCryptoProvider
        val canExecuteProviderOperations =
            providerOperationNoopKatValidation.canExecuteProviderOperations ||
                providerOperationNoopKat.canExecuteProviderOperations ||
                providerOperationNoopKatAdmission.canExecuteProviderOperations ||
                providerOperationMetadataKatSuiteReport.canExecuteProviderOperations ||
                providerOperationMetadataKatValidation.canExecuteProviderOperations ||
                providerOperationMetadataKat.canExecuteProviderOperations ||
                capabilityMatrix.canExecuteProviderOperations
        val canExecuteCrypto =
            providerOperationNoopKatValidation.canExecuteCrypto ||
                providerOperationNoopKat.canExecuteCrypto ||
                providerOperationNoopKatAdmission.canExecuteCrypto ||
                providerOperationMetadataKatSuiteReport.canExecuteCrypto ||
                providerOperationMetadataKatValidation.canExecuteCrypto ||
                providerOperationMetadataKat.canExecuteCrypto ||
                capabilityMatrix.canExecuteCrypto
        val canUseForVaultLifecycle =
            providerOperationNoopKatValidation.canUseForVaultLifecycle ||
                providerOperationNoopKat.canUseForVaultLifecycle ||
                providerOperationNoopKatAdmission.canUseForVaultLifecycle ||
                providerOperationMetadataKatSuiteReport.canUseForVaultLifecycle ||
                providerOperationMetadataKatValidation.canUseForVaultLifecycle ||
                providerOperationMetadataKat.canUseForVaultLifecycle ||
                capabilityMatrix.canUseForVaultLifecycle
        val canUseForPersistence =
            providerOperationNoopKatValidation.canUseForPersistence ||
                providerOperationNoopKat.canUseForPersistence ||
                providerOperationNoopKatAdmission.canUseForPersistence ||
                providerOperationMetadataKatSuiteReport.canUseForPersistence ||
                providerOperationMetadataKatValidation.canUseForPersistence ||
                providerOperationMetadataKat.canUseForPersistence ||
                capabilityMatrix.canUseForPersistence
        val canUseForSync =
            providerOperationNoopKatValidation.canUseForSync ||
                providerOperationNoopKat.canUseForSync ||
                providerOperationNoopKatAdmission.canUseForSync ||
                providerOperationMetadataKatSuiteReport.canUseForSync ||
                providerOperationMetadataKatValidation.canUseForSync ||
                providerOperationMetadataKat.canUseForSync ||
                capabilityMatrix.canUseForSync
        val canUseForSigning =
            providerOperationNoopKatValidation.canUseForSigning ||
                providerOperationNoopKat.canUseForSigning ||
                providerOperationNoopKatAdmission.canUseForSigning ||
                providerOperationMetadataKatSuiteReport.canUseForSigning ||
                providerOperationMetadataKatValidation.canUseForSigning ||
                providerOperationMetadataKat.canUseForSigning ||
                capabilityMatrix.canUseForSigning
        val canUseForBroadcasting =
            providerOperationNoopKatValidation.canUseForBroadcasting ||
                providerOperationNoopKat.canUseForBroadcasting ||
                providerOperationNoopKatAdmission.canUseForBroadcasting ||
                providerOperationMetadataKatSuiteReport.canUseForBroadcasting ||
                providerOperationMetadataKatValidation.canUseForBroadcasting ||
                providerOperationMetadataKat.canUseForBroadcasting ||
                capabilityMatrix.canUseForBroadcasting
        val canUseForMainnet =
            providerOperationNoopKatValidation.canUseForMainnet ||
                providerOperationNoopKat.canUseForMainnet ||
                providerOperationNoopKatAdmission.canUseForMainnet ||
                providerOperationMetadataKatSuiteReport.canUseForMainnet ||
                providerOperationMetadataKatValidation.canUseForMainnet ||
                providerOperationMetadataKat.canUseForMainnet ||
                capabilityMatrix.canUseForMainnet
        val productionProviderSelectable =
            providerOperationNoopKatValidation.productionProviderSelectable ||
                providerOperationNoopKat.productionProviderSelectable ||
                providerOperationNoopKatAdmission.productionProviderSelectable ||
                providerOperationMetadataKatSuiteReport.productionProviderSelectable ||
                providerOperationMetadataKatValidation.productionProviderSelectable ||
                providerOperationMetadataKat.productionProviderSelectable ||
                providerOperationKatAdmission.productionProviderSelectable ||
                executableMetadataKatSuiteReport.productionProviderSelectable ||
                capabilityMatrix.productionProviderSelectable ||
                marker.productionProviderSelectable

        val suiteIsCommonTestOnly =
            providerOperationNoopKatValidation.validationIsCommonTestOnly &&
                providerOperationNoopKat.noopKatIsCommonTestOnly &&
                providerOperationNoopKatAdmission.admissionIsCommonTestOnly &&
                providerOperationMetadataKatSuiteReport.suiteIsCommonTestOnly &&
                providerOperationMetadataKatValidation.validationIsCommonTestOnly &&
                providerOperationMetadataKat.evaluationIsCommonTestOnly &&
                providerOperationKatAdmission.admissionIsCommonTestOnly &&
                executableMetadataKatSuiteReport.suiteIsCommonTestOnly &&
                capabilityMatrix.matrixIsCommonTestOnly &&
                marker.sourceSet == SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet.CommonTest
        val suiteReportsSyntheticNoopOnly =
            providerOperationNoopKatValidation.validatesSyntheticNoopOnly &&
                providerOperationNoopKat.evaluatesSyntheticNoopOnly &&
                syntheticNoopResultPresent &&
                providerOperationNoopKatCount == 1 &&
                providerOperationNoopKatValidationCount == 1
        val suiteReportsProviderOperationExecution =
            providerOperationNoopKatValidation.validatesProviderOperationExecution ||
                providerOperationNoopKat.evaluatesProviderOperationExecution ||
                providerOperationExecutionPresent
        val suiteReportsCryptoOperation =
            providerOperationNoopKatValidation.validatesCryptoOperation ||
                providerOperationNoopKat.evaluatesCryptoOperation ||
                cryptoExecutionPresent
        val suiteReportsVaultLifecycle =
            providerOperationNoopKatValidation.validatesVaultLifecycle ||
                providerOperationNoopKat.evaluatesVaultLifecycle ||
                vaultLifecycleReachable
        val suiteReportsPersistence =
            providerOperationNoopKatValidation.validatesPersistence ||
                providerOperationNoopKat.evaluatesPersistence ||
                persistenceReachable

        val safeOutputRedactionSatisfied =
            sequenceOf(
                marker.toString(),
                marker.safeId.toString(),
                capabilityMatrix.toString(),
                executableMetadataKatSuiteReport.toString(),
                providerOperationKatAdmission.toString(),
                providerOperationMetadataKat.toString(),
                providerOperationMetadataKat.displayLabel.toString(),
                providerOperationMetadataKatValidation.toString(),
                providerOperationMetadataKatValidation.displayLabel.toString(),
                providerOperationMetadataKatSuiteReport.toString(),
                providerOperationNoopKatAdmission.toString(),
                providerOperationNoopKatAdmission.displayLabel.toString(),
                providerOperationNoopKat.toString(),
                providerOperationNoopKat.displayLabel.toString(),
                providerOperationNoopKatValidation.toString(),
                providerOperationNoopKatValidation.displayLabel.toString(),
            ).none { output ->
                EXPECTED_SAFE_ID in output ||
                    EXPECTED_FIXTURE_ID in output ||
                    EXPECTED_VECTOR_ID in output ||
                    EXPECTED_CASE_ID in output ||
                    EXPECTED_PROVIDER_OPERATION_METADATA_KAT_ID in output ||
                    EXPECTED_PROVIDER_OPERATION_NOOP_KAT_ID in output
            }
        val suiteReportGenerated =
            suiteIsCommonTestOnly &&
                providerOperationNoopKatCount == 1 &&
                providerOperationNoopKatValidationCount == 1 &&
                safeOutputRedactionSatisfied
        val providerOperationNoopKatSuitePassed =
            suiteReportGenerated &&
                markerCount == 1 &&
                caseBindingCount == 1 &&
                executableMetadataKatSuiteReportCount == 1 &&
                providerOperationKatAdmissionCount == 1 &&
                providerOperationMetadataKatCount == 1 &&
                providerOperationMetadataKatValidationCount == 1 &&
                providerOperationMetadataKatSuiteReportCount == 1 &&
                providerOperationNoopKatAdmissionCount == 1 &&
                expectedSafeIdMatched &&
                expectedFixtureIdMatched &&
                expectedVectorIdMatched &&
                expectedCaseIdMatched &&
                expectedProviderOperationMetadataKatIdMatched &&
                expectedProviderOperationNoopKatIdMatched &&
                metadataKatSuitePassed &&
                providerOperationMetadataKatPassed &&
                providerOperationMetadataKatValidationPassed &&
                providerOperationMetadataKatSuitePassed &&
                providerOperationNoopKatAdmissionModeled &&
                providerOperationNoopKatEvaluated &&
                providerOperationNoopKatPassed &&
                providerOperationNoopKatValidationPassed &&
                syntheticNoopResultPresent &&
                suiteReportsSyntheticNoopOnly &&
                !suiteReportsProviderOperationExecution &&
                !suiteReportsCryptoOperation &&
                !suiteReportsVaultLifecycle &&
                !suiteReportsPersistence &&
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

        return SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatSuiteReport(
            markerCount = markerCount,
            caseBindingCount = caseBindingCount,
            executableMetadataKatSuiteReportCount = executableMetadataKatSuiteReportCount,
            providerOperationKatAdmissionCount = providerOperationKatAdmissionCount,
            providerOperationMetadataKatCount = providerOperationMetadataKatCount,
            providerOperationMetadataKatValidationCount = providerOperationMetadataKatValidationCount,
            providerOperationMetadataKatSuiteReportCount = providerOperationMetadataKatSuiteReportCount,
            providerOperationNoopKatAdmissionCount = providerOperationNoopKatAdmissionCount,
            providerOperationNoopKatCount = providerOperationNoopKatCount,
            providerOperationNoopKatValidationCount = providerOperationNoopKatValidationCount,
            expectedSafeIdMatched = expectedSafeIdMatched,
            expectedFixtureIdMatched = expectedFixtureIdMatched,
            expectedVectorIdMatched = expectedVectorIdMatched,
            expectedCaseIdMatched = expectedCaseIdMatched,
            expectedProviderOperationMetadataKatIdMatched = expectedProviderOperationMetadataKatIdMatched,
            expectedProviderOperationNoopKatIdMatched = expectedProviderOperationNoopKatIdMatched,
            metadataKatSuitePassed = metadataKatSuitePassed,
            providerOperationMetadataKatPassed = providerOperationMetadataKatPassed,
            providerOperationMetadataKatValidationPassed = providerOperationMetadataKatValidationPassed,
            providerOperationMetadataKatSuitePassed = providerOperationMetadataKatSuitePassed,
            providerOperationNoopKatAdmissionModeled = providerOperationNoopKatAdmissionModeled,
            providerOperationNoopKatEvaluated = providerOperationNoopKatEvaluated,
            providerOperationNoopKatPassed = providerOperationNoopKatPassed,
            providerOperationNoopKatValidationPassed = providerOperationNoopKatValidationPassed,
            syntheticNoopResultPresent = syntheticNoopResultPresent,
            suiteReportGenerated = suiteReportGenerated,
            providerOperationNoopKatSuitePassed = providerOperationNoopKatSuitePassed,
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
            sourceSet =
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatSuiteReportSourceSet.CommonTest,
            displayLabel =
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatSuiteReportSafeLabel(
                    "no-op provider-operation KAT suite report evidence",
                ),
        )
    }
}
