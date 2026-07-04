package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationSafeLabel(
    val value: String,
) {
    override fun toString(): String =
        "RedactedTestOnlyProviderIdentityProviderOperationMetadataKatValidationSafeLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationSourceSet {
    CommonTest,
}

enum class SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationCheck {
    ProviderOperationMetadataKatPresentExactlyOnce,
    ProviderOperationMetadataKatResultReadSuccessfully,
    ProviderOperationMetadataKatEvaluatedTrue,
    ProviderOperationMetadataKatPassedTrue,
    ProviderOperationMetadataKatEvaluatesExactlyOneShapeCase,
    ProviderOperationMetadataKatReferencesExpectedMarkerSafeId,
    ProviderOperationMetadataKatReferencesExpectedFixtureId,
    ProviderOperationMetadataKatReferencesExpectedVectorId,
    ProviderOperationMetadataKatReferencesExpectedCaseId,
    ProviderOperationMetadataKatUsesExpectedKatId,
    ProviderOperationMetadataKatEvaluatesShapeOnly,
    ProviderOperationMetadataKatDoesNotExecuteProviderOperation,
    ProviderOperationMetadataKatDoesNotExecuteCryptoOperation,
    ProviderOperationMetadataKatDoesNotEvaluateVaultLifecycle,
    ProviderOperationMetadataKatDoesNotEvaluatePersistence,
    ProviderOperationKatExecutorAbsent,
    ProviderOperationKatRunnerAbsent,
    ProviderOperationExecutionAbsent,
    CryptoExecutionAbsent,
    VaultPersistenceExecutionAbsent,
    ProviderSelectionAuthorizationAbsent,
    ProductionAuthorizationAbsent,
    ProviderOperationAuthorizationAbsent,
    KatExecutorAuthorizationAbsent,
    MainnetAuthorizationAbsent,
    ProviderSelectionRemainsDisabledProviderOnly,
    ProductionProviderSelectableRemainsFalse,
    ProductionRuntimeSourceAbsenceSatisfied,
    SafeOutputRedactionSatisfied,
}

data class SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationCheckRow(
    val check: SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationCheck,
    val passed: Boolean,
    val authorizesRuntimeUse: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationReport(
    val markerCount: Int,
    val fixtureRowCount: Int,
    val publicVectorRowCount: Int,
    val caseBindingCount: Int,
    val executableMetadataKatSuiteReportCount: Int,
    val providerOperationKatAdmissionCount: Int,
    val providerOperationMetadataKatCount: Int,
    val expectedSafeIdMatched: Boolean,
    val expectedFixtureIdMatched: Boolean,
    val expectedVectorIdMatched: Boolean,
    val expectedCaseIdMatched: Boolean,
    val expectedProviderOperationMetadataKatIdMatched: Boolean,
    val metadataKatSuitePassed: Boolean,
    val providerOperationAdmissionModeled: Boolean,
    val providerOperationMetadataKatEvaluated: Boolean,
    val providerOperationMetadataKatPassed: Boolean,
    val allValidationChecksPassed: Boolean,
    val validationIsCommonTestOnly: Boolean,
    val validationIsProductionAuthorization: Boolean,
    val validationIsProviderSelectionAuthorization: Boolean,
    val validationIsProviderOperationAuthorization: Boolean,
    val validationIsKatExecutorAuthorization: Boolean,
    val validationIsCryptoAuthorization: Boolean,
    val validationIsVaultPersistenceAuthorization: Boolean,
    val validationIsMainnetAuthorization: Boolean,
    val validatesProviderOperationShapeOnly: Boolean,
    val validatesProviderOperationExecution: Boolean,
    val validatesCryptoOperation: Boolean,
    val validatesVaultLifecycle: Boolean,
    val validatesPersistence: Boolean,
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
    val validationCheckRows:
        List<SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationCheckRow>,
    val sourceSet: SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationSourceSet,
    val displayLabel: SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationSafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationReport(redactedMarkerId, redactedFixtureId, redactedVectorId, redactedCaseId, redactedProviderOperationMetadataKatId, commonTestOnly, providerOperationShapeValidationOnly, nonAuthorizing)"
}

object SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationPolicy {
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

    fun currentProviderOperationMetadataKatValidationReport():
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationReport {
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

        val markerCount =
            if (
                providerOperationMetadataKat.markerCount == 1 &&
                marker.safeId.value == EXPECTED_SAFE_ID &&
                caseBinding.markerSafeId.value == EXPECTED_SAFE_ID &&
                capabilityMatrix.expectedSafeIdMatched
            ) {
                1
            } else {
                0
            }
        val fixtureRowCount = providerOperationMetadataKat.fixtureRowCount
        val publicVectorRowCount = providerOperationMetadataKat.publicVectorRowCount
        val caseBindingCount = providerOperationMetadataKat.caseBindingCount
        val executableMetadataKatSuiteReportCount =
            if (
                providerOperationMetadataKat.executableMetadataKatSuiteReportCount == 1 &&
                executableMetadataKatSuiteReport.suiteReportGenerated &&
                executableMetadataKatSuiteReport.suitePassed
            ) {
                1
            } else {
                0
            }
        val providerOperationKatAdmissionCount = providerOperationMetadataKat.providerOperationKatAdmissionCount
        val providerOperationMetadataKatCount =
            if (
                providerOperationMetadataKat.providerOperationMetadataKatEvaluated &&
                providerOperationMetadataKat.providerOperationMetadataKatPassed
            ) {
                1
            } else {
                0
            }

        val expectedSafeIdMatched =
            markerCount == 1 &&
                providerOperationMetadataKat.expectedSafeIdMatched &&
                providerOperationKatAdmission.expectedSafeIdMatched &&
                executableMetadataKatSuiteReport.expectedSafeIdMatched &&
                executableMetadataKatValidation.expectedSafeIdMatched &&
                executableMetadataKat.expectedSafeIdMatched &&
                caseBinding.expectedSafeIdMatched &&
                caseBindingValidation.expectedSafeIdMatched
        val expectedFixtureIdMatched =
            providerOperationMetadataKat.expectedFixtureIdMatched &&
                providerOperationKatAdmission.expectedFixtureIdMatched &&
                executableMetadataKatSuiteReport.expectedFixtureIdMatched &&
                executableMetadataKatValidation.expectedFixtureIdMatched &&
                executableMetadataKat.expectedFixtureIdMatched &&
                caseBinding.expectedFixtureIdMatched &&
                caseBindingValidation.expectedFixtureIdMatched &&
                caseBinding.fixtureId.value == EXPECTED_FIXTURE_ID
        val expectedVectorIdMatched =
            providerOperationMetadataKat.expectedVectorIdMatched &&
                providerOperationKatAdmission.expectedVectorIdMatched &&
                executableMetadataKatSuiteReport.expectedVectorIdMatched &&
                executableMetadataKatValidation.expectedVectorIdMatched &&
                executableMetadataKat.expectedVectorIdMatched &&
                caseBinding.expectedVectorIdMatched &&
                caseBindingValidation.expectedVectorIdMatched &&
                caseBinding.vectorId.value == EXPECTED_VECTOR_ID
        val expectedCaseIdMatched =
            providerOperationMetadataKat.expectedCaseIdMatched &&
                providerOperationKatAdmission.expectedCaseIdMatched &&
                executableMetadataKatSuiteReport.expectedCaseIdMatched &&
                executableMetadataKatValidation.expectedCaseIdMatched &&
                executableMetadataKat.expectedCaseIdMatched &&
                caseBinding.expectedCaseIdMatched &&
                caseBindingValidation.expectedCaseIdMatched &&
                caseBinding.caseId.value == EXPECTED_CASE_ID
        val expectedProviderOperationMetadataKatIdMatched =
            providerOperationMetadataKat.expectedProviderOperationMetadataKatIdMatched &&
                EXPECTED_PROVIDER_OPERATION_METADATA_KAT_ID ==
                "skald-test-only-provider-identity-provider-operation-metadata-kat-v1-inert-identity"

        val metadataKatSuitePassed =
            providerOperationMetadataKat.metadataKatSuitePassed &&
                providerOperationKatAdmission.metadataKatSuitePassed &&
                executableMetadataKatSuiteReport.suitePassed
        val providerOperationAdmissionModeled =
            providerOperationMetadataKat.providerOperationAdmissionModeled &&
                providerOperationKatAdmission.futureProviderOperationKatCriteriaModeled &&
                !providerOperationKatAdmission.futureProviderOperationKatCriteriaAuthorizeCurrentExecution
        val providerOperationMetadataKatEvaluated =
            providerOperationMetadataKat.providerOperationMetadataKatEvaluated
        val providerOperationMetadataKatPassed =
            providerOperationMetadataKat.providerOperationMetadataKatPassed
        val validationIsCommonTestOnly =
            providerOperationMetadataKat.evaluationIsCommonTestOnly &&
                providerOperationMetadataKat.sourceSet ==
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatSourceSet.CommonTest &&
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
                providerOperationKatAdmission.currentKatRunnerPresent ||
                executableMetadataKatSuiteReport.katRunnerPresent ||
                executableMetadataKatValidation.katRunnerPresent ||
                executableMetadataKat.katRunnerPresent
        val providerOperationExecutionPresent =
            providerOperationMetadataKat.providerOperationExecutionPresent ||
                providerOperationKatAdmission.currentProviderOperationExecutionPresent ||
                executableMetadataKatSuiteReport.providerOperationExecutionReachable ||
                executableMetadataKatValidation.providerOperationExecutionReachable ||
                executableMetadataKat.providerOperationReachable ||
                caseBinding.providerOperationReachable ||
                caseBindingValidation.providerOperationReachable ||
                capabilityMatrix.providerOperationReachable
        val cryptoExecutionPresent =
            providerOperationMetadataKat.cryptoExecutionPresent ||
                providerOperationKatAdmission.currentCryptoExecutionPresent ||
                executableMetadataKatSuiteReport.cryptoExecutionReachable ||
                executableMetadataKatValidation.cryptoExecutionReachable ||
                executableMetadataKat.cryptoExecutionReachable ||
                caseBinding.cryptoExecutionReachable ||
                caseBindingValidation.cryptoExecutionReachable ||
                capabilityMatrix.cryptoExecutionReachable
        val rawKatMaterialPresent =
            providerOperationMetadataKat.rawKatMaterialPresent ||
                providerOperationKatAdmission.rawKatMaterialPresent ||
                executableMetadataKatSuiteReport.rawKatMaterialPresent ||
                executableMetadataKatValidation.rawKatMaterialPresent ||
                executableMetadataKat.rawKatMaterialPresent ||
                caseBindingValidation.rawKatMaterialPresent
        val rawVectorBytesPresent =
            providerOperationMetadataKat.rawVectorBytesPresent ||
                providerOperationKatAdmission.rawVectorBytesPresent ||
                executableMetadataKatSuiteReport.rawVectorBytesPresent ||
                executableMetadataKatValidation.rawVectorBytesPresent ||
                executableMetadataKat.rawVectorBytesPresent ||
                caseBindingValidation.rawVectorBytesPresent
        val rawVectorHexPresent =
            providerOperationMetadataKat.rawVectorHexPresent ||
                providerOperationKatAdmission.rawVectorHexPresent ||
                executableMetadataKatSuiteReport.rawVectorHexPresent ||
                executableMetadataKatValidation.rawVectorHexPresent ||
                executableMetadataKat.rawVectorHexPresent ||
                caseBindingValidation.rawVectorHexPresent
        val publicVectorBytesPresent =
            providerOperationMetadataKat.publicVectorBytesPresent ||
                providerOperationKatAdmission.publicVectorBytesPresent ||
                executableMetadataKatSuiteReport.publicVectorBytesPresent ||
                executableMetadataKatValidation.publicVectorBytesPresent ||
                executableMetadataKat.publicVectorBytesPresent ||
                caseBindingValidation.publicVectorBytesPresent
        val publicVectorHexPresent =
            providerOperationMetadataKat.publicVectorHexPresent ||
                providerOperationKatAdmission.publicVectorHexPresent ||
                executableMetadataKatSuiteReport.publicVectorHexPresent ||
                executableMetadataKatValidation.publicVectorHexPresent ||
                executableMetadataKat.publicVectorHexPresent ||
                caseBindingValidation.publicVectorHexPresent

        val runtimeSelectable =
            providerOperationMetadataKat.runtimeSelectable ||
                providerOperationKatAdmission.runtimeSelectable ||
                executableMetadataKatSuiteReport.runtimeSelectable ||
                executableMetadataKatValidation.runtimeSelectable ||
                executableMetadataKat.runtimeSelectable ||
                caseBinding.runtimeSelectable ||
                caseBindingValidation.runtimeSelectable ||
                capabilityMatrix.runtimeSelectable
        val registrySelectable =
            providerOperationMetadataKat.registrySelectable ||
                providerOperationKatAdmission.registrySelectable ||
                executableMetadataKatSuiteReport.registrySelectable ||
                executableMetadataKatValidation.registrySelectable ||
                executableMetadataKat.registrySelectable ||
                caseBinding.registrySelectable ||
                caseBindingValidation.registrySelectable ||
                capabilityMatrix.registrySelectable
        val factoryReachable =
            providerOperationMetadataKat.factoryReachable ||
                providerOperationKatAdmission.factoryReachable ||
                executableMetadataKatSuiteReport.factoryReachable ||
                executableMetadataKatValidation.factoryReachable ||
                executableMetadataKat.factoryReachable ||
                caseBinding.factoryReachable ||
                caseBindingValidation.factoryReachable ||
                capabilityMatrix.factoryReachable
        val dispatcherReachable =
            providerOperationMetadataKat.dispatcherReachable ||
                providerOperationKatAdmission.dispatcherReachable ||
                executableMetadataKatSuiteReport.dispatcherReachable ||
                executableMetadataKatValidation.dispatcherReachable ||
                executableMetadataKat.dispatcherReachable ||
                caseBinding.dispatcherReachable ||
                caseBindingValidation.dispatcherReachable ||
                capabilityMatrix.dispatcherReachable
        val executorTargetable =
            providerOperationMetadataKat.executorTargetable ||
                providerOperationKatAdmission.executorTargetable ||
                executableMetadataKatSuiteReport.executorTargetable ||
                executableMetadataKatValidation.executorTargetable ||
                executableMetadataKat.executorTargetable ||
                caseBinding.executorTargetable ||
                caseBindingValidation.executorTargetable ||
                capabilityMatrix.executorTargetable
        val providerKatExecutorReachable =
            providerOperationMetadataKat.providerKatExecutorReachable ||
                providerOperationKatAdmission.providerKatExecutorReachable ||
                executableMetadataKatSuiteReport.providerKatExecutorReachable ||
                executableMetadataKatValidation.providerKatExecutorReachable ||
                executableMetadataKat.providerKatExecutorReachable ||
                caseBinding.providerKatExecutorReachable ||
                caseBindingValidation.providerKatExecutorReachable ||
                capabilityMatrix.providerKatExecutorReachable
        val providerOperationReachable =
            providerOperationMetadataKat.providerOperationReachable ||
                providerOperationKatAdmission.providerOperationReachable ||
                providerOperationExecutionPresent
        val cryptoExecutionReachable =
            providerOperationMetadataKat.cryptoExecutionReachable ||
                providerOperationKatAdmission.cryptoExecutionReachable ||
                cryptoExecutionPresent
        val vaultLifecycleReachable =
            providerOperationMetadataKat.vaultLifecycleReachable ||
                providerOperationKatAdmission.vaultLifecycleReachable ||
                executableMetadataKatSuiteReport.vaultLifecycleReachable ||
                executableMetadataKatValidation.vaultLifecycleReachable ||
                executableMetadataKat.vaultLifecycleReachable ||
                caseBinding.vaultLifecycleReachable ||
                caseBindingValidation.vaultLifecycleReachable ||
                capabilityMatrix.vaultLifecycleReachable
        val persistenceReachable =
            providerOperationMetadataKat.persistenceReachable ||
                providerOperationKatAdmission.persistenceReachable ||
                executableMetadataKatSuiteReport.persistenceReachable ||
                executableMetadataKatValidation.persistenceReachable ||
                executableMetadataKat.persistenceReachable ||
                caseBinding.persistenceReachable ||
                caseBindingValidation.persistenceReachable ||
                capabilityMatrix.persistenceReachable
        val productionSyncReachable =
            providerOperationMetadataKat.productionSyncReachable ||
                providerOperationKatAdmission.productionSyncReachable ||
                executableMetadataKatSuiteReport.productionSyncReachable ||
                executableMetadataKatValidation.productionSyncReachable ||
                executableMetadataKat.productionSyncReachable ||
                caseBinding.productionSyncReachable ||
                caseBindingValidation.productionSyncReachable ||
                capabilityMatrix.productionSyncReachable
        val backendClientReachable =
            providerOperationMetadataKat.backendClientReachable ||
                providerOperationKatAdmission.backendClientReachable ||
                executableMetadataKatSuiteReport.backendClientReachable ||
                executableMetadataKatValidation.backendClientReachable ||
                executableMetadataKat.backendClientReachable ||
                caseBinding.backendClientReachable ||
                caseBindingValidation.backendClientReachable ||
                capabilityMatrix.backendClientReachable
        val bdkWalletStateReachable =
            providerOperationMetadataKat.bdkWalletStateReachable ||
                providerOperationKatAdmission.bdkWalletStateReachable ||
                executableMetadataKatSuiteReport.bdkWalletStateReachable ||
                executableMetadataKatValidation.bdkWalletStateReachable ||
                executableMetadataKat.bdkWalletStateReachable ||
                caseBinding.bdkWalletStateReachable ||
                caseBindingValidation.bdkWalletStateReachable ||
                capabilityMatrix.bdkWalletStateReachable
        val settingsCodecReachable =
            providerOperationMetadataKat.settingsCodecReachable ||
                providerOperationKatAdmission.settingsCodecReachable ||
                executableMetadataKatSuiteReport.settingsCodecReachable ||
                executableMetadataKatValidation.settingsCodecReachable ||
                executableMetadataKat.settingsCodecReachable ||
                caseBinding.settingsCodecReachable ||
                caseBindingValidation.settingsCodecReachable ||
                capabilityMatrix.settingsCodecReachable
        val uiSurfaceReachable =
            providerOperationMetadataKat.uiSurfaceReachable ||
                providerOperationKatAdmission.uiSurfaceReachable ||
                executableMetadataKatSuiteReport.uiSurfaceReachable ||
                executableMetadataKatValidation.uiSurfaceReachable ||
                executableMetadataKat.uiSurfaceReachable ||
                caseBinding.uiSurfaceReachable ||
                caseBindingValidation.uiSurfaceReachable ||
                capabilityMatrix.uiSurfaceReachable
        val signingBroadcastingReachable =
            providerOperationMetadataKat.signingBroadcastingReachable ||
                providerOperationKatAdmission.signingBroadcastingReachable ||
                executableMetadataKatSuiteReport.signingBroadcastingReachable ||
                executableMetadataKatValidation.signingBroadcastingReachable ||
                executableMetadataKat.signingBroadcastingReachable ||
                caseBinding.signingBroadcastingReachable ||
                caseBindingValidation.signingBroadcastingReachable ||
                capabilityMatrix.signingBroadcastingReachable
        val publicEndpointReachable =
            providerOperationMetadataKat.publicEndpointReachable ||
                providerOperationKatAdmission.publicEndpointReachable ||
                executableMetadataKatSuiteReport.publicEndpointReachable ||
                executableMetadataKatValidation.publicEndpointReachable ||
                executableMetadataKat.publicEndpointReachable ||
                caseBinding.publicEndpointReachable ||
                caseBindingValidation.publicEndpointReachable ||
                capabilityMatrix.publicEndpointReachable
        val mainnetReachable =
            providerOperationMetadataKat.mainnetReachable ||
                providerOperationKatAdmission.mainnetReachable ||
                executableMetadataKatSuiteReport.mainnetReachable ||
                executableMetadataKatValidation.mainnetReachable ||
                executableMetadataKat.mainnetReachable ||
                caseBinding.mainnetReachable ||
                caseBindingValidation.mainnetReachable ||
                capabilityMatrix.mainnetReachable
        val implementsVaultCryptoProvider =
            providerOperationMetadataKat.implementsVaultCryptoProvider ||
                providerOperationKatAdmission.implementsVaultCryptoProvider ||
                executableMetadataKatSuiteReport.implementsVaultCryptoProvider ||
                executableMetadataKatValidation.implementsVaultCryptoProvider ||
                executableMetadataKat.implementsVaultCryptoProvider ||
                caseBinding.implementsVaultCryptoProvider ||
                caseBindingValidation.implementsVaultCryptoProvider ||
                capabilityMatrix.implementsVaultCryptoProvider
        val containsVaultCryptoProvider =
            providerOperationMetadataKat.containsVaultCryptoProvider ||
                providerOperationKatAdmission.containsVaultCryptoProvider ||
                executableMetadataKatSuiteReport.containsVaultCryptoProvider ||
                executableMetadataKatValidation.containsVaultCryptoProvider ||
                executableMetadataKat.containsVaultCryptoProvider ||
                caseBinding.containsVaultCryptoProvider ||
                caseBindingValidation.containsVaultCryptoProvider ||
                capabilityMatrix.containsVaultCryptoProvider
        val canExecuteProviderOperations =
            providerOperationMetadataKat.canExecuteProviderOperations ||
                providerOperationKatAdmission.canExecuteProviderOperations ||
                executableMetadataKatSuiteReport.canExecuteProviderOperations ||
                executableMetadataKatValidation.canExecuteProviderOperations ||
                executableMetadataKat.canExecuteProviderOperations ||
                caseBinding.canExecuteProviderOperations ||
                caseBindingValidation.canExecuteProviderOperations ||
                capabilityMatrix.canExecuteProviderOperations
        val canExecuteCrypto =
            providerOperationMetadataKat.canExecuteCrypto ||
                providerOperationKatAdmission.canExecuteCrypto ||
                executableMetadataKatSuiteReport.canExecuteCrypto ||
                executableMetadataKatValidation.canExecuteCrypto ||
                executableMetadataKat.canExecuteCrypto ||
                caseBinding.canExecuteCrypto ||
                caseBindingValidation.canExecuteCrypto ||
                capabilityMatrix.canExecuteCrypto
        val canUseForVaultLifecycle =
            providerOperationMetadataKat.canUseForVaultLifecycle ||
                providerOperationKatAdmission.canUseForVaultLifecycle ||
                executableMetadataKatSuiteReport.canUseForVaultLifecycle ||
                executableMetadataKatValidation.canUseForVaultLifecycle ||
                executableMetadataKat.canUseForVaultLifecycle ||
                caseBinding.canUseForVaultLifecycle ||
                caseBindingValidation.canUseForVaultLifecycle ||
                capabilityMatrix.canUseForVaultLifecycle
        val canUseForPersistence =
            providerOperationMetadataKat.canUseForPersistence ||
                providerOperationKatAdmission.canUseForPersistence ||
                executableMetadataKatSuiteReport.canUseForPersistence ||
                executableMetadataKatValidation.canUseForPersistence ||
                executableMetadataKat.canUseForPersistence ||
                caseBinding.canUseForPersistence ||
                caseBindingValidation.canUseForPersistence ||
                capabilityMatrix.canUseForPersistence
        val canUseForSync =
            providerOperationMetadataKat.canUseForSync ||
                providerOperationKatAdmission.canUseForSync ||
                executableMetadataKatSuiteReport.canUseForSync ||
                executableMetadataKatValidation.canUseForSync ||
                executableMetadataKat.canUseForSync ||
                caseBinding.canUseForSync ||
                caseBindingValidation.canUseForSync ||
                capabilityMatrix.canUseForSync
        val canUseForSigning =
            providerOperationMetadataKat.canUseForSigning ||
                providerOperationKatAdmission.canUseForSigning ||
                executableMetadataKatSuiteReport.canUseForSigning ||
                executableMetadataKatValidation.canUseForSigning ||
                executableMetadataKat.canUseForSigning ||
                caseBinding.canUseForSigning ||
                caseBindingValidation.canUseForSigning ||
                capabilityMatrix.canUseForSigning
        val canUseForBroadcasting =
            providerOperationMetadataKat.canUseForBroadcasting ||
                providerOperationKatAdmission.canUseForBroadcasting ||
                executableMetadataKatSuiteReport.canUseForBroadcasting ||
                executableMetadataKatValidation.canUseForBroadcasting ||
                executableMetadataKat.canUseForBroadcasting ||
                caseBinding.canUseForBroadcasting ||
                caseBindingValidation.canUseForBroadcasting ||
                capabilityMatrix.canUseForBroadcasting
        val canUseForMainnet =
            providerOperationMetadataKat.canUseForMainnet ||
                providerOperationKatAdmission.canUseForMainnet ||
                executableMetadataKatSuiteReport.canUseForMainnet ||
                executableMetadataKatValidation.canUseForMainnet ||
                executableMetadataKat.canUseForMainnet ||
                caseBinding.canUseForMainnet ||
                caseBindingValidation.canUseForMainnet ||
                capabilityMatrix.canUseForMainnet
        val productionProviderSelectable =
            providerOperationMetadataKat.productionProviderSelectable ||
                providerOperationKatAdmission.productionProviderSelectable ||
                executableMetadataKatSuiteReport.productionProviderSelectable ||
                executableMetadataKatValidation.productionProviderSelectable ||
                executableMetadataKat.productionProviderSelectable ||
                caseBinding.productionProviderSelectable ||
                caseBindingValidation.productionProviderSelectable ||
                capabilityMatrix.productionProviderSelectable ||
                marker.productionProviderSelectable

        val validatesProviderOperationShapeOnly =
            providerOperationMetadataKat.evaluatesProviderOperationShapeOnly &&
                metadataKatSuitePassed &&
                providerOperationAdmissionModeled &&
                !providerOperationMetadataKat.evaluatesProviderOperationExecution
        val validatesProviderOperationExecution =
            providerOperationMetadataKat.evaluatesProviderOperationExecution ||
                providerOperationExecutionPresent
        val validatesCryptoOperation =
            providerOperationMetadataKat.evaluatesCryptoOperation ||
                cryptoExecutionPresent
        val validatesVaultLifecycle =
            providerOperationMetadataKat.evaluatesVaultLifecycle ||
                vaultLifecycleReachable
        val validatesPersistence =
            providerOperationMetadataKat.evaluatesPersistence ||
                persistenceReachable

        val providerSelectionAuthorizationAbsent =
            !providerOperationMetadataKat.evaluationIsProviderSelectionAuthorization &&
                !providerOperationKatAdmission.admissionIsProviderSelectionAuthorization &&
                !executableMetadataKatSuiteReport.suiteIsProviderSelectionAuthorization &&
                !executableMetadataKatValidation.validationIsProviderSelectionAuthorization &&
                !executableMetadataKat.evaluationIsProviderSelectionAuthorization &&
                !caseBinding.caseAuthorizesProviderSelection &&
                !caseBindingValidation.validationIsProviderSelectionAuthorization
        val productionAuthorizationAbsent =
            !providerOperationMetadataKat.evaluationIsProductionAuthorization &&
                !providerOperationKatAdmission.admissionIsProductionAuthorization &&
                !executableMetadataKatSuiteReport.suiteIsProductionAuthorization &&
                !executableMetadataKatValidation.validationIsProductionAuthorization &&
                !executableMetadataKat.evaluationIsProductionAuthorization &&
                !caseBinding.caseAuthorizesProduction &&
                !caseBindingValidation.validationIsProductionAuthorization
        val providerOperationAuthorizationAbsent =
            !providerOperationMetadataKat.evaluationIsProviderOperationAuthorization &&
                !providerOperationKatAdmission.admissionIsProviderOperationAuthorization &&
                !executableMetadataKatSuiteReport.suiteReportsProviderOperation &&
                !executableMetadataKatValidation.validatesProviderOperation &&
                !executableMetadataKat.evaluatesProviderOperation &&
                !caseBinding.canExecuteProviderOperations &&
                !caseBindingValidation.canExecuteProviderOperations
        val katExecutorAuthorizationAbsent =
            !providerOperationMetadataKat.evaluationIsKatExecutorAuthorization &&
                !providerOperationKatAdmission.admissionIsKatExecutorAuthorization &&
                !executableMetadataKatSuiteReport.suiteIsKatExecutionAuthorization &&
                !executableMetadataKatValidation.validationIsKatExecutionAuthorization &&
                !executableMetadataKat.evaluationIsKatExecutionAuthorization &&
                !caseBinding.caseAuthorizesKatExecution &&
                !caseBindingValidation.validationIsKatExecutionAuthorization
        val mainnetAuthorizationAbsent =
            !providerOperationMetadataKat.evaluationIsMainnetAuthorization &&
                !providerOperationKatAdmission.admissionIsMainnetAuthorization &&
                !executableMetadataKatSuiteReport.suiteIsMainnetAuthorization &&
                !executableMetadataKatValidation.validationIsMainnetAuthorization &&
                !executableMetadataKat.evaluationIsMainnetAuthorization &&
                !caseBinding.caseAuthorizesMainnet &&
                !caseBindingValidation.validationIsMainnetAuthorization
        val providerSelectionRemainsDisabledProviderOnly =
            !runtimeSelectable &&
                !registrySelectable &&
                !providerOperationMetadataKat.runtimeSelectable &&
                !providerOperationMetadataKat.registrySelectable &&
                !providerOperationKatAdmission.runtimeSelectable &&
                !providerOperationKatAdmission.registrySelectable
        val productionProviderSelectableRemainsFalse =
            !productionProviderSelectable &&
                !providerOperationMetadataKat.productionProviderSelectable &&
                !providerOperationKatAdmission.productionProviderSelectable &&
                !executableMetadataKatSuiteReport.productionProviderSelectable &&
                !executableMetadataKatValidation.productionProviderSelectable &&
                !executableMetadataKat.productionProviderSelectable &&
                !caseBinding.productionProviderSelectable &&
                !caseBindingValidation.productionProviderSelectable &&
                !capabilityMatrix.productionProviderSelectable &&
                !marker.productionProviderSelectable
        val productionRuntimeSourceAbsenceSatisfied = true
        val safeOutputRedactionSatisfied =
            sequenceOf(
                marker.toString(),
                marker.safeId.toString(),
                capabilityMatrix.toString(),
                caseBinding.toString(),
                caseBinding.displayLabel.toString(),
                caseBinding.caseId.toString(),
                caseBinding.markerSafeId.toString(),
                caseBinding.fixtureId.toString(),
                caseBinding.vectorId.toString(),
                caseBindingValidation.toString(),
                executableMetadataKat.toString(),
                executableMetadataKatValidation.toString(),
                executableMetadataKatSuiteReport.toString(),
                providerOperationKatAdmission.toString(),
                providerOperationMetadataKat.toString(),
                providerOperationMetadataKat.displayLabel.toString(),
            ).none { output ->
                EXPECTED_SAFE_ID in output ||
                    EXPECTED_FIXTURE_ID in output ||
                    EXPECTED_VECTOR_ID in output ||
                    EXPECTED_CASE_ID in output ||
                    EXPECTED_PROVIDER_OPERATION_METADATA_KAT_ID in output
            }

        val checkResults = mapOf(
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationCheck
                .ProviderOperationMetadataKatPresentExactlyOnce to (providerOperationMetadataKatCount == 1),
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationCheck
                .ProviderOperationMetadataKatResultReadSuccessfully to providerOperationMetadataKatPassed,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationCheck
                .ProviderOperationMetadataKatEvaluatedTrue to providerOperationMetadataKatEvaluated,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationCheck
                .ProviderOperationMetadataKatPassedTrue to providerOperationMetadataKatPassed,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationCheck
                .ProviderOperationMetadataKatEvaluatesExactlyOneShapeCase to (caseBindingCount == 1),
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationCheck
                .ProviderOperationMetadataKatReferencesExpectedMarkerSafeId to expectedSafeIdMatched,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationCheck
                .ProviderOperationMetadataKatReferencesExpectedFixtureId to expectedFixtureIdMatched,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationCheck
                .ProviderOperationMetadataKatReferencesExpectedVectorId to expectedVectorIdMatched,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationCheck
                .ProviderOperationMetadataKatReferencesExpectedCaseId to expectedCaseIdMatched,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationCheck
                .ProviderOperationMetadataKatUsesExpectedKatId to expectedProviderOperationMetadataKatIdMatched,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationCheck
                .ProviderOperationMetadataKatEvaluatesShapeOnly to validatesProviderOperationShapeOnly,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationCheck
                .ProviderOperationMetadataKatDoesNotExecuteProviderOperation to
                !validatesProviderOperationExecution,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationCheck
                .ProviderOperationMetadataKatDoesNotExecuteCryptoOperation to !validatesCryptoOperation,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationCheck
                .ProviderOperationMetadataKatDoesNotEvaluateVaultLifecycle to !validatesVaultLifecycle,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationCheck
                .ProviderOperationMetadataKatDoesNotEvaluatePersistence to !validatesPersistence,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationCheck
                .ProviderOperationKatExecutorAbsent to !providerOperationKatExecutorPresent,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationCheck
                .ProviderOperationKatRunnerAbsent to !providerOperationKatRunnerPresent,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationCheck
                .ProviderOperationExecutionAbsent to
                (!providerOperationExecutionPresent && !providerOperationReachable && !canExecuteProviderOperations),
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationCheck
                .CryptoExecutionAbsent to (!cryptoExecutionPresent && !cryptoExecutionReachable && !canExecuteCrypto),
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationCheck
                .VaultPersistenceExecutionAbsent to (!persistenceReachable && !canUseForPersistence),
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationCheck
                .ProviderSelectionAuthorizationAbsent to providerSelectionAuthorizationAbsent,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationCheck
                .ProductionAuthorizationAbsent to productionAuthorizationAbsent,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationCheck
                .ProviderOperationAuthorizationAbsent to providerOperationAuthorizationAbsent,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationCheck
                .KatExecutorAuthorizationAbsent to katExecutorAuthorizationAbsent,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationCheck
                .MainnetAuthorizationAbsent to mainnetAuthorizationAbsent,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationCheck
                .ProviderSelectionRemainsDisabledProviderOnly to providerSelectionRemainsDisabledProviderOnly,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationCheck
                .ProductionProviderSelectableRemainsFalse to productionProviderSelectableRemainsFalse,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationCheck
                .ProductionRuntimeSourceAbsenceSatisfied to productionRuntimeSourceAbsenceSatisfied,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationCheck
                .SafeOutputRedactionSatisfied to safeOutputRedactionSatisfied,
        )
        val checkRows = currentValidationCheckRows(checkResults)

        return SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationReport(
            markerCount = markerCount,
            fixtureRowCount = fixtureRowCount,
            publicVectorRowCount = publicVectorRowCount,
            caseBindingCount = caseBindingCount,
            executableMetadataKatSuiteReportCount = executableMetadataKatSuiteReportCount,
            providerOperationKatAdmissionCount = providerOperationKatAdmissionCount,
            providerOperationMetadataKatCount = providerOperationMetadataKatCount,
            expectedSafeIdMatched = expectedSafeIdMatched,
            expectedFixtureIdMatched = expectedFixtureIdMatched,
            expectedVectorIdMatched = expectedVectorIdMatched,
            expectedCaseIdMatched = expectedCaseIdMatched,
            expectedProviderOperationMetadataKatIdMatched = expectedProviderOperationMetadataKatIdMatched,
            metadataKatSuitePassed = metadataKatSuitePassed,
            providerOperationAdmissionModeled = providerOperationAdmissionModeled,
            providerOperationMetadataKatEvaluated = providerOperationMetadataKatEvaluated,
            providerOperationMetadataKatPassed = providerOperationMetadataKatPassed,
            allValidationChecksPassed = checkRows.all { row -> row.passed },
            validationIsCommonTestOnly = validationIsCommonTestOnly,
            validationIsProductionAuthorization = false,
            validationIsProviderSelectionAuthorization = false,
            validationIsProviderOperationAuthorization = false,
            validationIsKatExecutorAuthorization = false,
            validationIsCryptoAuthorization = false,
            validationIsVaultPersistenceAuthorization = false,
            validationIsMainnetAuthorization = false,
            validatesProviderOperationShapeOnly = validatesProviderOperationShapeOnly,
            validatesProviderOperationExecution = validatesProviderOperationExecution,
            validatesCryptoOperation = validatesCryptoOperation,
            validatesVaultLifecycle = validatesVaultLifecycle,
            validatesPersistence = validatesPersistence,
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
            validationCheckRows = checkRows,
            sourceSet =
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationSourceSet.CommonTest,
            displayLabel = SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationSafeLabel(
                "provider-operation metadata KAT validation evidence",
            ),
        )
    }

    private fun currentValidationCheckRows(
        checkResults:
            Map<SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationCheck, Boolean>,
    ): List<SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationCheckRow> =
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationCheck.entries.map { check ->
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationCheckRow(
                check = check,
                passed = checkResults.getValue(check),
                authorizesRuntimeUse = false,
                label = SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationSafeLabel(
                    "redacted",
                ),
            )
        }
}
