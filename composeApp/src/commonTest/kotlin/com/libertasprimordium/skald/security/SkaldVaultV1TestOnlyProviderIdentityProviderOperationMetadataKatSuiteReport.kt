package com.libertasprimordium.skald.security

data class SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatSuiteReport(
    val markerCount: Int,
    val fixtureRowCount: Int,
    val publicVectorRowCount: Int,
    val caseBindingCount: Int,
    val executableMetadataKatSuiteReportCount: Int,
    val providerOperationKatAdmissionCount: Int,
    val providerOperationMetadataKatCount: Int,
    val providerOperationMetadataKatValidationCount: Int,
    val expectedSafeIdMatched: Boolean,
    val expectedFixtureIdMatched: Boolean,
    val expectedVectorIdMatched: Boolean,
    val expectedCaseIdMatched: Boolean,
    val expectedProviderOperationMetadataKatIdMatched: Boolean,
    val metadataKatSuitePassed: Boolean,
    val providerOperationAdmissionModeled: Boolean,
    val providerOperationMetadataKatEvaluated: Boolean,
    val providerOperationMetadataKatPassed: Boolean,
    val providerOperationMetadataKatValidationPassed: Boolean,
    val suiteReportGenerated: Boolean,
    val providerOperationMetadataKatSuitePassed: Boolean,
    val suiteIsCommonTestOnly: Boolean,
    val suiteIsProductionAuthorization: Boolean,
    val suiteIsProviderSelectionAuthorization: Boolean,
    val suiteIsProviderOperationAuthorization: Boolean,
    val suiteIsKatExecutorAuthorization: Boolean,
    val suiteIsCryptoAuthorization: Boolean,
    val suiteIsVaultPersistenceAuthorization: Boolean,
    val suiteIsMainnetAuthorization: Boolean,
    val suiteReportsProviderOperationShapeOnly: Boolean,
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
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatSuiteReport(redactedMarkerId, redactedFixtureId, redactedVectorId, redactedCaseId, redactedProviderOperationMetadataKatId, commonTestOnly, providerOperationShapeSuiteOnly, nonAuthorizing)"
}

object SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatSuiteReportPolicy {
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

    fun currentProviderOperationMetadataKatSuiteReport():
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatSuiteReport {
        val marker = SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy.currentMarker()
        val capabilityMatrix =
            SkaldVaultV1TestOnlyProviderIdentityCapabilityMatrixPolicy.currentCapabilityMatrix()
        val caseBinding =
            SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingPolicy.currentKatCaseBinding()
        val caseBindingValidation =
            SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationPolicy
                .currentKatCaseBindingValidationReport()
        val executableMetadataKat =
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatPolicy.evaluateCurrentMetadataKat()
        val executableMetadataKatValidation =
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationPolicy
                .currentExecutableMetadataKatValidationReport()
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

        val markerCount =
            if (
                providerOperationMetadataKatValidation.markerCount == 1 &&
                providerOperationMetadataKat.markerCount == 1 &&
                marker.safeId.value == EXPECTED_SAFE_ID &&
                caseBinding.markerSafeId.value == EXPECTED_SAFE_ID &&
                capabilityMatrix.expectedSafeIdMatched
            ) {
                1
            } else {
                0
            }
        val fixtureRowCount = providerOperationMetadataKatValidation.fixtureRowCount
        val publicVectorRowCount = providerOperationMetadataKatValidation.publicVectorRowCount
        val caseBindingCount = providerOperationMetadataKatValidation.caseBindingCount
        val executableMetadataKatSuiteReportCount =
            providerOperationMetadataKatValidation.executableMetadataKatSuiteReportCount
        val providerOperationKatAdmissionCount =
            providerOperationMetadataKatValidation.providerOperationKatAdmissionCount
        val providerOperationMetadataKatCount =
            providerOperationMetadataKatValidation.providerOperationMetadataKatCount
        val providerOperationMetadataKatValidationCount =
            if (
                providerOperationMetadataKatValidation.allValidationChecksPassed &&
                providerOperationMetadataKatValidation.providerOperationMetadataKatEvaluated &&
                providerOperationMetadataKatValidation.providerOperationMetadataKatPassed
            ) {
                1
            } else {
                0
            }

        val expectedSafeIdMatched =
            markerCount == 1 &&
                providerOperationMetadataKat.expectedSafeIdMatched &&
                providerOperationMetadataKatValidation.expectedSafeIdMatched &&
                providerOperationKatAdmission.expectedSafeIdMatched &&
                executableMetadataKatSuiteReport.expectedSafeIdMatched &&
                executableMetadataKatValidation.expectedSafeIdMatched &&
                executableMetadataKat.expectedSafeIdMatched &&
                caseBinding.expectedSafeIdMatched &&
                caseBindingValidation.expectedSafeIdMatched
        val expectedFixtureIdMatched =
            providerOperationMetadataKat.expectedFixtureIdMatched &&
                providerOperationMetadataKatValidation.expectedFixtureIdMatched &&
                providerOperationKatAdmission.expectedFixtureIdMatched &&
                executableMetadataKatSuiteReport.expectedFixtureIdMatched &&
                executableMetadataKatValidation.expectedFixtureIdMatched &&
                executableMetadataKat.expectedFixtureIdMatched &&
                caseBinding.expectedFixtureIdMatched &&
                caseBindingValidation.expectedFixtureIdMatched &&
                caseBinding.fixtureId.value == EXPECTED_FIXTURE_ID
        val expectedVectorIdMatched =
            providerOperationMetadataKat.expectedVectorIdMatched &&
                providerOperationMetadataKatValidation.expectedVectorIdMatched &&
                providerOperationKatAdmission.expectedVectorIdMatched &&
                executableMetadataKatSuiteReport.expectedVectorIdMatched &&
                executableMetadataKatValidation.expectedVectorIdMatched &&
                executableMetadataKat.expectedVectorIdMatched &&
                caseBinding.expectedVectorIdMatched &&
                caseBindingValidation.expectedVectorIdMatched &&
                caseBinding.vectorId.value == EXPECTED_VECTOR_ID
        val expectedCaseIdMatched =
            providerOperationMetadataKat.expectedCaseIdMatched &&
                providerOperationMetadataKatValidation.expectedCaseIdMatched &&
                providerOperationKatAdmission.expectedCaseIdMatched &&
                executableMetadataKatSuiteReport.expectedCaseIdMatched &&
                executableMetadataKatValidation.expectedCaseIdMatched &&
                executableMetadataKat.expectedCaseIdMatched &&
                caseBinding.expectedCaseIdMatched &&
                caseBindingValidation.expectedCaseIdMatched &&
                caseBinding.caseId.value == EXPECTED_CASE_ID
        val expectedProviderOperationMetadataKatIdMatched =
            providerOperationMetadataKat.expectedProviderOperationMetadataKatIdMatched &&
                providerOperationMetadataKatValidation.expectedProviderOperationMetadataKatIdMatched &&
                EXPECTED_PROVIDER_OPERATION_METADATA_KAT_ID ==
                "skald-test-only-provider-identity-provider-operation-metadata-kat-v1-inert-identity"

        val metadataKatSuitePassed =
            providerOperationMetadataKat.metadataKatSuitePassed &&
                providerOperationMetadataKatValidation.metadataKatSuitePassed &&
                providerOperationKatAdmission.metadataKatSuitePassed &&
                executableMetadataKatSuiteReport.suitePassed
        val providerOperationAdmissionModeled =
            providerOperationMetadataKat.providerOperationAdmissionModeled &&
                providerOperationMetadataKatValidation.providerOperationAdmissionModeled &&
                providerOperationKatAdmission.futureProviderOperationKatCriteriaModeled &&
                !providerOperationKatAdmission.futureProviderOperationKatCriteriaAuthorizeCurrentExecution
        val providerOperationMetadataKatEvaluated =
            providerOperationMetadataKat.providerOperationMetadataKatEvaluated &&
                providerOperationMetadataKatValidation.providerOperationMetadataKatEvaluated
        val providerOperationMetadataKatPassed =
            providerOperationMetadataKat.providerOperationMetadataKatPassed &&
                providerOperationMetadataKatValidation.providerOperationMetadataKatPassed
        val providerOperationMetadataKatValidationPassed =
            providerOperationMetadataKatValidation.allValidationChecksPassed

        val suiteIsCommonTestOnly =
            providerOperationMetadataKat.evaluationIsCommonTestOnly &&
                providerOperationMetadataKatValidation.validationIsCommonTestOnly &&
                providerOperationKatAdmission.admissionIsCommonTestOnly &&
                executableMetadataKatSuiteReport.suiteIsCommonTestOnly &&
                executableMetadataKatValidation.validationIsCommonTestOnly &&
                executableMetadataKat.evaluationIsCommonTestOnly &&
                caseBinding.caseIsCommonTestOnly &&
                caseBindingValidation.validationIsCommonTestOnly &&
                capabilityMatrix.matrixIsCommonTestOnly &&
                marker.sourceSet == SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet.CommonTest

        val providerOperationKatExecutorPresent =
            providerOperationMetadataKat.providerOperationKatExecutorPresent ||
                providerOperationMetadataKatValidation.providerOperationKatExecutorPresent ||
                providerOperationKatAdmission.currentKatExecutorPresent ||
                executableMetadataKatSuiteReport.executableKatExecutorPresent ||
                executableMetadataKatSuiteReport.katExecutorPresent ||
                executableMetadataKatValidation.executableKatExecutorPresent ||
                executableMetadataKatValidation.katExecutorPresent ||
                executableMetadataKat.executableKatExecutorPresent ||
                executableMetadataKat.katExecutorPresent ||
                caseBindingValidation.katExecutorPresent
        val providerOperationKatRunnerPresent =
            providerOperationMetadataKat.providerOperationKatRunnerPresent ||
                providerOperationMetadataKatValidation.providerOperationKatRunnerPresent ||
                providerOperationKatAdmission.currentKatRunnerPresent ||
                executableMetadataKatSuiteReport.katRunnerPresent ||
                executableMetadataKatValidation.katRunnerPresent ||
                executableMetadataKat.katRunnerPresent
        val providerOperationExecutionPresent =
            providerOperationMetadataKat.providerOperationExecutionPresent ||
                providerOperationMetadataKatValidation.providerOperationExecutionPresent ||
                providerOperationKatAdmission.currentProviderOperationExecutionPresent ||
                executableMetadataKatSuiteReport.providerOperationExecutionReachable ||
                executableMetadataKatValidation.providerOperationExecutionReachable ||
                executableMetadataKat.providerOperationReachable ||
                caseBinding.providerOperationReachable ||
                caseBindingValidation.providerOperationReachable ||
                capabilityMatrix.providerOperationReachable
        val cryptoExecutionPresent =
            providerOperationMetadataKat.cryptoExecutionPresent ||
                providerOperationMetadataKatValidation.cryptoExecutionPresent ||
                providerOperationKatAdmission.currentCryptoExecutionPresent ||
                executableMetadataKatSuiteReport.cryptoExecutionReachable ||
                executableMetadataKatValidation.cryptoExecutionReachable ||
                executableMetadataKat.cryptoExecutionReachable ||
                caseBinding.cryptoExecutionReachable ||
                caseBindingValidation.cryptoExecutionReachable ||
                capabilityMatrix.cryptoExecutionReachable
        val rawKatMaterialPresent =
            providerOperationMetadataKat.rawKatMaterialPresent ||
                providerOperationMetadataKatValidation.rawKatMaterialPresent ||
                providerOperationKatAdmission.rawKatMaterialPresent ||
                executableMetadataKatSuiteReport.rawKatMaterialPresent ||
                executableMetadataKatValidation.rawKatMaterialPresent ||
                executableMetadataKat.rawKatMaterialPresent ||
                caseBindingValidation.rawKatMaterialPresent
        val rawVectorBytesPresent =
            providerOperationMetadataKat.rawVectorBytesPresent ||
                providerOperationMetadataKatValidation.rawVectorBytesPresent ||
                providerOperationKatAdmission.rawVectorBytesPresent ||
                executableMetadataKatSuiteReport.rawVectorBytesPresent ||
                executableMetadataKatValidation.rawVectorBytesPresent ||
                executableMetadataKat.rawVectorBytesPresent ||
                caseBindingValidation.rawVectorBytesPresent
        val rawVectorHexPresent =
            providerOperationMetadataKat.rawVectorHexPresent ||
                providerOperationMetadataKatValidation.rawVectorHexPresent ||
                providerOperationKatAdmission.rawVectorHexPresent ||
                executableMetadataKatSuiteReport.rawVectorHexPresent ||
                executableMetadataKatValidation.rawVectorHexPresent ||
                executableMetadataKat.rawVectorHexPresent ||
                caseBindingValidation.rawVectorHexPresent
        val publicVectorBytesPresent =
            providerOperationMetadataKat.publicVectorBytesPresent ||
                providerOperationMetadataKatValidation.publicVectorBytesPresent ||
                providerOperationKatAdmission.publicVectorBytesPresent ||
                executableMetadataKatSuiteReport.publicVectorBytesPresent ||
                executableMetadataKatValidation.publicVectorBytesPresent ||
                executableMetadataKat.publicVectorBytesPresent ||
                caseBindingValidation.publicVectorBytesPresent
        val publicVectorHexPresent =
            providerOperationMetadataKat.publicVectorHexPresent ||
                providerOperationMetadataKatValidation.publicVectorHexPresent ||
                providerOperationKatAdmission.publicVectorHexPresent ||
                executableMetadataKatSuiteReport.publicVectorHexPresent ||
                executableMetadataKatValidation.publicVectorHexPresent ||
                executableMetadataKat.publicVectorHexPresent ||
                caseBindingValidation.publicVectorHexPresent

        val runtimeSelectable =
            providerOperationMetadataKat.runtimeSelectable ||
                providerOperationMetadataKatValidation.runtimeSelectable ||
                providerOperationKatAdmission.runtimeSelectable ||
                executableMetadataKatSuiteReport.runtimeSelectable ||
                executableMetadataKatValidation.runtimeSelectable ||
                executableMetadataKat.runtimeSelectable ||
                caseBinding.runtimeSelectable ||
                caseBindingValidation.runtimeSelectable ||
                capabilityMatrix.runtimeSelectable
        val registrySelectable =
            providerOperationMetadataKat.registrySelectable ||
                providerOperationMetadataKatValidation.registrySelectable ||
                providerOperationKatAdmission.registrySelectable ||
                executableMetadataKatSuiteReport.registrySelectable ||
                executableMetadataKatValidation.registrySelectable ||
                executableMetadataKat.registrySelectable ||
                caseBinding.registrySelectable ||
                caseBindingValidation.registrySelectable ||
                capabilityMatrix.registrySelectable
        val factoryReachable =
            providerOperationMetadataKat.factoryReachable ||
                providerOperationMetadataKatValidation.factoryReachable ||
                providerOperationKatAdmission.factoryReachable ||
                executableMetadataKatSuiteReport.factoryReachable ||
                executableMetadataKatValidation.factoryReachable ||
                executableMetadataKat.factoryReachable ||
                caseBinding.factoryReachable ||
                caseBindingValidation.factoryReachable ||
                capabilityMatrix.factoryReachable
        val dispatcherReachable =
            providerOperationMetadataKat.dispatcherReachable ||
                providerOperationMetadataKatValidation.dispatcherReachable ||
                providerOperationKatAdmission.dispatcherReachable ||
                executableMetadataKatSuiteReport.dispatcherReachable ||
                executableMetadataKatValidation.dispatcherReachable ||
                executableMetadataKat.dispatcherReachable ||
                caseBinding.dispatcherReachable ||
                caseBindingValidation.dispatcherReachable ||
                capabilityMatrix.dispatcherReachable
        val executorTargetable =
            providerOperationMetadataKat.executorTargetable ||
                providerOperationMetadataKatValidation.executorTargetable ||
                providerOperationKatAdmission.executorTargetable ||
                executableMetadataKatSuiteReport.executorTargetable ||
                executableMetadataKatValidation.executorTargetable ||
                executableMetadataKat.executorTargetable ||
                caseBinding.executorTargetable ||
                caseBindingValidation.executorTargetable ||
                capabilityMatrix.executorTargetable
        val providerKatExecutorReachable =
            providerOperationMetadataKat.providerKatExecutorReachable ||
                providerOperationMetadataKatValidation.providerKatExecutorReachable ||
                providerOperationKatAdmission.providerKatExecutorReachable ||
                executableMetadataKatSuiteReport.providerKatExecutorReachable ||
                executableMetadataKatValidation.providerKatExecutorReachable ||
                executableMetadataKat.providerKatExecutorReachable ||
                caseBinding.providerKatExecutorReachable ||
                caseBindingValidation.providerKatExecutorReachable ||
                capabilityMatrix.providerKatExecutorReachable
        val providerOperationReachable =
            providerOperationMetadataKat.providerOperationReachable ||
                providerOperationMetadataKatValidation.providerOperationReachable ||
                providerOperationKatAdmission.providerOperationReachable ||
                providerOperationExecutionPresent
        val cryptoExecutionReachable =
            providerOperationMetadataKat.cryptoExecutionReachable ||
                providerOperationMetadataKatValidation.cryptoExecutionReachable ||
                providerOperationKatAdmission.cryptoExecutionReachable ||
                cryptoExecutionPresent
        val vaultLifecycleReachable =
            providerOperationMetadataKat.vaultLifecycleReachable ||
                providerOperationMetadataKatValidation.vaultLifecycleReachable ||
                providerOperationKatAdmission.vaultLifecycleReachable ||
                executableMetadataKatSuiteReport.vaultLifecycleReachable ||
                executableMetadataKatValidation.vaultLifecycleReachable ||
                executableMetadataKat.vaultLifecycleReachable ||
                caseBinding.vaultLifecycleReachable ||
                caseBindingValidation.vaultLifecycleReachable ||
                capabilityMatrix.vaultLifecycleReachable
        val persistenceReachable =
            providerOperationMetadataKat.persistenceReachable ||
                providerOperationMetadataKatValidation.persistenceReachable ||
                providerOperationKatAdmission.persistenceReachable ||
                executableMetadataKatSuiteReport.persistenceReachable ||
                executableMetadataKatValidation.persistenceReachable ||
                executableMetadataKat.persistenceReachable ||
                caseBinding.persistenceReachable ||
                caseBindingValidation.persistenceReachable ||
                capabilityMatrix.persistenceReachable
        val productionSyncReachable =
            providerOperationMetadataKat.productionSyncReachable ||
                providerOperationMetadataKatValidation.productionSyncReachable ||
                providerOperationKatAdmission.productionSyncReachable ||
                executableMetadataKatSuiteReport.productionSyncReachable ||
                executableMetadataKatValidation.productionSyncReachable ||
                executableMetadataKat.productionSyncReachable ||
                caseBinding.productionSyncReachable ||
                caseBindingValidation.productionSyncReachable ||
                capabilityMatrix.productionSyncReachable
        val backendClientReachable =
            providerOperationMetadataKat.backendClientReachable ||
                providerOperationMetadataKatValidation.backendClientReachable ||
                providerOperationKatAdmission.backendClientReachable ||
                executableMetadataKatSuiteReport.backendClientReachable ||
                executableMetadataKatValidation.backendClientReachable ||
                executableMetadataKat.backendClientReachable ||
                caseBinding.backendClientReachable ||
                caseBindingValidation.backendClientReachable ||
                capabilityMatrix.backendClientReachable
        val bdkWalletStateReachable =
            providerOperationMetadataKat.bdkWalletStateReachable ||
                providerOperationMetadataKatValidation.bdkWalletStateReachable ||
                providerOperationKatAdmission.bdkWalletStateReachable ||
                executableMetadataKatSuiteReport.bdkWalletStateReachable ||
                executableMetadataKatValidation.bdkWalletStateReachable ||
                executableMetadataKat.bdkWalletStateReachable ||
                caseBinding.bdkWalletStateReachable ||
                caseBindingValidation.bdkWalletStateReachable ||
                capabilityMatrix.bdkWalletStateReachable
        val settingsCodecReachable =
            providerOperationMetadataKat.settingsCodecReachable ||
                providerOperationMetadataKatValidation.settingsCodecReachable ||
                providerOperationKatAdmission.settingsCodecReachable ||
                executableMetadataKatSuiteReport.settingsCodecReachable ||
                executableMetadataKatValidation.settingsCodecReachable ||
                executableMetadataKat.settingsCodecReachable ||
                caseBinding.settingsCodecReachable ||
                caseBindingValidation.settingsCodecReachable ||
                capabilityMatrix.settingsCodecReachable
        val uiSurfaceReachable =
            providerOperationMetadataKat.uiSurfaceReachable ||
                providerOperationMetadataKatValidation.uiSurfaceReachable ||
                providerOperationKatAdmission.uiSurfaceReachable ||
                executableMetadataKatSuiteReport.uiSurfaceReachable ||
                executableMetadataKatValidation.uiSurfaceReachable ||
                executableMetadataKat.uiSurfaceReachable ||
                caseBinding.uiSurfaceReachable ||
                caseBindingValidation.uiSurfaceReachable ||
                capabilityMatrix.uiSurfaceReachable
        val signingBroadcastingReachable =
            providerOperationMetadataKat.signingBroadcastingReachable ||
                providerOperationMetadataKatValidation.signingBroadcastingReachable ||
                providerOperationKatAdmission.signingBroadcastingReachable ||
                executableMetadataKatSuiteReport.signingBroadcastingReachable ||
                executableMetadataKatValidation.signingBroadcastingReachable ||
                executableMetadataKat.signingBroadcastingReachable ||
                caseBinding.signingBroadcastingReachable ||
                caseBindingValidation.signingBroadcastingReachable ||
                capabilityMatrix.signingBroadcastingReachable
        val publicEndpointReachable =
            providerOperationMetadataKat.publicEndpointReachable ||
                providerOperationMetadataKatValidation.publicEndpointReachable ||
                providerOperationKatAdmission.publicEndpointReachable ||
                executableMetadataKatSuiteReport.publicEndpointReachable ||
                executableMetadataKatValidation.publicEndpointReachable ||
                executableMetadataKat.publicEndpointReachable ||
                caseBinding.publicEndpointReachable ||
                caseBindingValidation.publicEndpointReachable ||
                capabilityMatrix.publicEndpointReachable
        val mainnetReachable =
            providerOperationMetadataKat.mainnetReachable ||
                providerOperationMetadataKatValidation.mainnetReachable ||
                providerOperationKatAdmission.mainnetReachable ||
                executableMetadataKatSuiteReport.mainnetReachable ||
                executableMetadataKatValidation.mainnetReachable ||
                executableMetadataKat.mainnetReachable ||
                caseBinding.mainnetReachable ||
                caseBindingValidation.mainnetReachable ||
                capabilityMatrix.mainnetReachable
        val implementsVaultCryptoProvider =
            providerOperationMetadataKat.implementsVaultCryptoProvider ||
                providerOperationMetadataKatValidation.implementsVaultCryptoProvider ||
                providerOperationKatAdmission.implementsVaultCryptoProvider ||
                executableMetadataKatSuiteReport.implementsVaultCryptoProvider ||
                executableMetadataKatValidation.implementsVaultCryptoProvider ||
                executableMetadataKat.implementsVaultCryptoProvider ||
                caseBinding.implementsVaultCryptoProvider ||
                caseBindingValidation.implementsVaultCryptoProvider ||
                capabilityMatrix.implementsVaultCryptoProvider
        val containsVaultCryptoProvider =
            providerOperationMetadataKat.containsVaultCryptoProvider ||
                providerOperationMetadataKatValidation.containsVaultCryptoProvider ||
                providerOperationKatAdmission.containsVaultCryptoProvider ||
                executableMetadataKatSuiteReport.containsVaultCryptoProvider ||
                executableMetadataKatValidation.containsVaultCryptoProvider ||
                executableMetadataKat.containsVaultCryptoProvider ||
                caseBinding.containsVaultCryptoProvider ||
                caseBindingValidation.containsVaultCryptoProvider ||
                capabilityMatrix.containsVaultCryptoProvider
        val canExecuteProviderOperations =
            providerOperationMetadataKat.canExecuteProviderOperations ||
                providerOperationMetadataKatValidation.canExecuteProviderOperations ||
                providerOperationKatAdmission.canExecuteProviderOperations ||
                executableMetadataKatSuiteReport.canExecuteProviderOperations ||
                executableMetadataKatValidation.canExecuteProviderOperations ||
                executableMetadataKat.canExecuteProviderOperations ||
                caseBinding.canExecuteProviderOperations ||
                caseBindingValidation.canExecuteProviderOperations ||
                capabilityMatrix.canExecuteProviderOperations
        val canExecuteCrypto =
            providerOperationMetadataKat.canExecuteCrypto ||
                providerOperationMetadataKatValidation.canExecuteCrypto ||
                providerOperationKatAdmission.canExecuteCrypto ||
                executableMetadataKatSuiteReport.canExecuteCrypto ||
                executableMetadataKatValidation.canExecuteCrypto ||
                executableMetadataKat.canExecuteCrypto ||
                caseBinding.canExecuteCrypto ||
                caseBindingValidation.canExecuteCrypto ||
                capabilityMatrix.canExecuteCrypto
        val canUseForVaultLifecycle =
            providerOperationMetadataKat.canUseForVaultLifecycle ||
                providerOperationMetadataKatValidation.canUseForVaultLifecycle ||
                providerOperationKatAdmission.canUseForVaultLifecycle ||
                executableMetadataKatSuiteReport.canUseForVaultLifecycle ||
                executableMetadataKatValidation.canUseForVaultLifecycle ||
                executableMetadataKat.canUseForVaultLifecycle ||
                caseBinding.canUseForVaultLifecycle ||
                caseBindingValidation.canUseForVaultLifecycle ||
                capabilityMatrix.canUseForVaultLifecycle
        val canUseForPersistence =
            providerOperationMetadataKat.canUseForPersistence ||
                providerOperationMetadataKatValidation.canUseForPersistence ||
                providerOperationKatAdmission.canUseForPersistence ||
                executableMetadataKatSuiteReport.canUseForPersistence ||
                executableMetadataKatValidation.canUseForPersistence ||
                executableMetadataKat.canUseForPersistence ||
                caseBinding.canUseForPersistence ||
                caseBindingValidation.canUseForPersistence ||
                capabilityMatrix.canUseForPersistence
        val canUseForSync =
            providerOperationMetadataKat.canUseForSync ||
                providerOperationMetadataKatValidation.canUseForSync ||
                providerOperationKatAdmission.canUseForSync ||
                executableMetadataKatSuiteReport.canUseForSync ||
                executableMetadataKatValidation.canUseForSync ||
                executableMetadataKat.canUseForSync ||
                caseBinding.canUseForSync ||
                caseBindingValidation.canUseForSync ||
                capabilityMatrix.canUseForSync
        val canUseForSigning =
            providerOperationMetadataKat.canUseForSigning ||
                providerOperationMetadataKatValidation.canUseForSigning ||
                providerOperationKatAdmission.canUseForSigning ||
                executableMetadataKatSuiteReport.canUseForSigning ||
                executableMetadataKatValidation.canUseForSigning ||
                executableMetadataKat.canUseForSigning ||
                caseBinding.canUseForSigning ||
                caseBindingValidation.canUseForSigning ||
                capabilityMatrix.canUseForSigning
        val canUseForBroadcasting =
            providerOperationMetadataKat.canUseForBroadcasting ||
                providerOperationMetadataKatValidation.canUseForBroadcasting ||
                providerOperationKatAdmission.canUseForBroadcasting ||
                executableMetadataKatSuiteReport.canUseForBroadcasting ||
                executableMetadataKatValidation.canUseForBroadcasting ||
                executableMetadataKat.canUseForBroadcasting ||
                caseBinding.canUseForBroadcasting ||
                caseBindingValidation.canUseForBroadcasting ||
                capabilityMatrix.canUseForBroadcasting
        val canUseForMainnet =
            providerOperationMetadataKat.canUseForMainnet ||
                providerOperationMetadataKatValidation.canUseForMainnet ||
                providerOperationKatAdmission.canUseForMainnet ||
                executableMetadataKatSuiteReport.canUseForMainnet ||
                executableMetadataKatValidation.canUseForMainnet ||
                executableMetadataKat.canUseForMainnet ||
                caseBinding.canUseForMainnet ||
                caseBindingValidation.canUseForMainnet ||
                capabilityMatrix.canUseForMainnet
        val productionProviderSelectable =
            providerOperationMetadataKat.productionProviderSelectable ||
                providerOperationMetadataKatValidation.productionProviderSelectable ||
                providerOperationKatAdmission.productionProviderSelectable ||
                executableMetadataKatSuiteReport.productionProviderSelectable ||
                executableMetadataKatValidation.productionProviderSelectable ||
                executableMetadataKat.productionProviderSelectable ||
                caseBinding.productionProviderSelectable ||
                caseBindingValidation.productionProviderSelectable ||
                capabilityMatrix.productionProviderSelectable ||
                marker.productionProviderSelectable

        val suiteReportsProviderOperationShapeOnly =
            providerOperationMetadataKat.evaluatesProviderOperationShapeOnly &&
                providerOperationMetadataKatValidation.validatesProviderOperationShapeOnly &&
                providerOperationMetadataKatValidationPassed &&
                metadataKatSuitePassed &&
                providerOperationAdmissionModeled
        val suiteReportsProviderOperationExecution =
            providerOperationMetadataKat.evaluatesProviderOperationExecution ||
                providerOperationMetadataKatValidation.validatesProviderOperationExecution ||
                providerOperationExecutionPresent
        val suiteReportsCryptoOperation =
            providerOperationMetadataKat.evaluatesCryptoOperation ||
                providerOperationMetadataKatValidation.validatesCryptoOperation ||
                cryptoExecutionPresent
        val suiteReportsVaultLifecycle =
            providerOperationMetadataKat.evaluatesVaultLifecycle ||
                providerOperationMetadataKatValidation.validatesVaultLifecycle ||
                vaultLifecycleReachable
        val suiteReportsPersistence =
            providerOperationMetadataKat.evaluatesPersistence ||
                providerOperationMetadataKatValidation.validatesPersistence ||
                persistenceReachable

        val suiteReportGenerated =
            suiteIsCommonTestOnly &&
                providerOperationMetadataKatCount == 1 &&
                providerOperationMetadataKatValidationCount == 1
        val providerOperationMetadataKatSuitePassed =
            suiteReportGenerated &&
                markerCount == 1 &&
                fixtureRowCount == 1 &&
                publicVectorRowCount == 1 &&
                caseBindingCount == 1 &&
                executableMetadataKatSuiteReportCount == 1 &&
                providerOperationKatAdmissionCount == 1 &&
                expectedSafeIdMatched &&
                expectedFixtureIdMatched &&
                expectedVectorIdMatched &&
                expectedCaseIdMatched &&
                expectedProviderOperationMetadataKatIdMatched &&
                metadataKatSuitePassed &&
                providerOperationAdmissionModeled &&
                providerOperationMetadataKatEvaluated &&
                providerOperationMetadataKatPassed &&
                providerOperationMetadataKatValidationPassed &&
                suiteReportsProviderOperationShapeOnly &&
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

        return SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatSuiteReport(
            markerCount = markerCount,
            fixtureRowCount = fixtureRowCount,
            publicVectorRowCount = publicVectorRowCount,
            caseBindingCount = caseBindingCount,
            executableMetadataKatSuiteReportCount = executableMetadataKatSuiteReportCount,
            providerOperationKatAdmissionCount = providerOperationKatAdmissionCount,
            providerOperationMetadataKatCount = providerOperationMetadataKatCount,
            providerOperationMetadataKatValidationCount = providerOperationMetadataKatValidationCount,
            expectedSafeIdMatched = expectedSafeIdMatched,
            expectedFixtureIdMatched = expectedFixtureIdMatched,
            expectedVectorIdMatched = expectedVectorIdMatched,
            expectedCaseIdMatched = expectedCaseIdMatched,
            expectedProviderOperationMetadataKatIdMatched = expectedProviderOperationMetadataKatIdMatched,
            metadataKatSuitePassed = metadataKatSuitePassed,
            providerOperationAdmissionModeled = providerOperationAdmissionModeled,
            providerOperationMetadataKatEvaluated = providerOperationMetadataKatEvaluated,
            providerOperationMetadataKatPassed = providerOperationMetadataKatPassed,
            providerOperationMetadataKatValidationPassed = providerOperationMetadataKatValidationPassed,
            suiteReportGenerated = suiteReportGenerated,
            providerOperationMetadataKatSuitePassed = providerOperationMetadataKatSuitePassed,
            suiteIsCommonTestOnly = suiteIsCommonTestOnly,
            suiteIsProductionAuthorization = false,
            suiteIsProviderSelectionAuthorization = false,
            suiteIsProviderOperationAuthorization = false,
            suiteIsKatExecutorAuthorization = false,
            suiteIsCryptoAuthorization = false,
            suiteIsVaultPersistenceAuthorization = false,
            suiteIsMainnetAuthorization = false,
            suiteReportsProviderOperationShapeOnly = suiteReportsProviderOperationShapeOnly,
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
        )
    }
}
