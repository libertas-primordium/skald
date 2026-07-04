package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityProviderOperationKatAdmissionSafeLabel(val value: String) {
    override fun toString(): String =
        "RedactedTestOnlyProviderIdentityProviderOperationKatAdmissionSafeLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityProviderOperationKatAdmissionSourceSet {
    CommonTest,
}

enum class SkaldVaultV1TestOnlyProviderIdentityProviderOperationKatAdmissionOutcome {
    ProviderOperationKatAdmissionModeled,
    FutureProviderOperationKatRequiresSeparateBranch,
    CurrentProviderOperationKatNotPresent,
    CurrentProviderOperationExecutionAbsent,
    CurrentCryptoExecutionAbsent,
    CurrentKatRunnerAbsent,
    CurrentKatExecutorAbsent,
    ProviderSelectionAuthorizationAbsent,
    ProductionAuthorizationAbsent,
    KatExecutorAuthorizationAbsent,
    CryptoAuthorizationAbsent,
    VaultPersistenceAuthorizationAbsent,
    MainnetAuthorizationAbsent,
}

enum class SkaldVaultV1TestOnlyProviderIdentityFutureProviderOperationKatCriterion {
    FutureBranchMustBeExplicitlyApproved,
    FutureProviderOperationKatMustRemainCommonTestOnly,
    FutureProviderOperationKatMustUseValidatedMetadataKatSuiteOnly,
    FutureProviderOperationKatMustUsePublicNonSecretTextOnlyVectorOnly,
    FutureProviderOperationKatMustNotUseRawBytes,
    FutureProviderOperationKatMustNotUseHex,
    FutureProviderOperationKatMustNotUseCryptoMaterial,
    FutureProviderOperationKatMustNotUseWalletMaterial,
    FutureProviderOperationKatMustNotUseEndpointMaterial,
    FutureProviderOperationKatMustNotUseProviderHandles,
    FutureProviderOperationKatMustNotImplementVaultCryptoProvider,
    FutureProviderOperationKatMustNotUseVaultCryptoProviderInstance,
    FutureProviderOperationKatMustNotUseProviderSelection,
    FutureProviderOperationKatMustNotUseRegistry,
    FutureProviderOperationKatMustNotUseFactory,
    FutureProviderOperationKatMustNotUseDispatcher,
    FutureProviderOperationKatMustNotUseExecutorTarget,
    FutureProviderOperationKatMustNotRunKdfHkdfHmacAead,
    FutureProviderOperationKatMustNotTouchVaultLifecycle,
    FutureProviderOperationKatMustNotTouchPersistence,
    FutureProviderOperationKatMustNotTouchBackendBdkSettingsUi,
    FutureProviderOperationKatMustNotSignOrBroadcast,
    FutureProviderOperationKatMustNotEnableMainnet,
    FutureProviderOperationKatMustRemainCoveredBySourceGuards,
    FutureProviderOperationKatMustRemainRedactedInOutput,
}

enum class SkaldVaultV1TestOnlyProviderIdentityForbiddenCurrentProviderOperationKatState {
    ProviderOperationKatPresent,
    ProviderOperationExecutionPresent,
    CryptoExecutionPresent,
    KatRunnerPresent,
    KatExecutorPresent,
    ProviderSelectionAuthorizationPresent,
    ProductionAuthorizationPresent,
    KatExecutorAuthorizationPresent,
    CryptoAuthorizationPresent,
    VaultPersistenceAuthorizationPresent,
    MainnetAuthorizationPresent,
}

data class SkaldVaultV1TestOnlyProviderIdentityProviderOperationKatAdmission(
    val markerCount: Int,
    val caseBindingCount: Int,
    val executableMetadataKatCount: Int,
    val executableMetadataKatValidationCount: Int,
    val executableMetadataKatSuiteReportCount: Int,
    val expectedSafeIdMatched: Boolean,
    val expectedFixtureIdMatched: Boolean,
    val expectedVectorIdMatched: Boolean,
    val expectedCaseIdMatched: Boolean,
    val metadataKatEvaluated: Boolean,
    val metadataKatPassed: Boolean,
    val executableMetadataKatValidationPassed: Boolean,
    val metadataKatSuitePassed: Boolean,
    val admissionIsCommonTestOnly: Boolean,
    val admissionIsProductionAuthorization: Boolean,
    val admissionIsProviderSelectionAuthorization: Boolean,
    val admissionIsProviderOperationAuthorization: Boolean,
    val admissionIsKatExecutorAuthorization: Boolean,
    val admissionIsCryptoAuthorization: Boolean,
    val admissionIsVaultPersistenceAuthorization: Boolean,
    val admissionIsMainnetAuthorization: Boolean,
    val futureProviderOperationKatCriteriaModeled: Boolean,
    val futureProviderOperationKatCriteriaAuthorizeCurrentExecution: Boolean,
    val currentProviderOperationKatPresent: Boolean,
    val currentProviderOperationExecutionPresent: Boolean,
    val currentCryptoExecutionPresent: Boolean,
    val currentKatRunnerPresent: Boolean,
    val currentKatExecutorPresent: Boolean,
    val rawKatMaterialPresent: Boolean,
    val rawVectorBytesPresent: Boolean,
    val rawVectorHexPresent: Boolean,
    val publicVectorBytesPresent: Boolean,
    val publicVectorHexPresent: Boolean,
    val providerOperationReachable: Boolean,
    val cryptoExecutionReachable: Boolean,
    val runtimeSelectable: Boolean,
    val registrySelectable: Boolean,
    val factoryReachable: Boolean,
    val dispatcherReachable: Boolean,
    val executorTargetable: Boolean,
    val providerKatExecutorReachable: Boolean,
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
    val admissionOutcomes: List<SkaldVaultV1TestOnlyProviderIdentityProviderOperationKatAdmissionOutcome>,
    val futureProviderOperationKatCriteria:
        List<SkaldVaultV1TestOnlyProviderIdentityFutureProviderOperationKatCriterion>,
    val forbiddenCurrentProviderOperationKatStates:
        List<SkaldVaultV1TestOnlyProviderIdentityForbiddenCurrentProviderOperationKatState>,
    val sourceSet: SkaldVaultV1TestOnlyProviderIdentityProviderOperationKatAdmissionSourceSet,
    val displayLabel: SkaldVaultV1TestOnlyProviderIdentityProviderOperationKatAdmissionSafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityProviderOperationKatAdmission(redactedMarkerId, redactedFixtureId, redactedVectorId, redactedCaseId, commonTestOnly, nonExecutable, nonAuthorizing)"
}

object SkaldVaultV1TestOnlyProviderIdentityProviderOperationKatAdmissionPolicy {
    private const val EXPECTED_SAFE_ID: String =
        "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"
    private const val EXPECTED_FIXTURE_ID: String =
        "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata"
    private const val EXPECTED_VECTOR_ID: String =
        "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata"
    private const val EXPECTED_CASE_ID: String =
        "skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata"

    fun currentProviderOperationKatAdmission():
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationKatAdmission {
        val marker = SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy.currentMarker()
        val capabilityMatrix =
            SkaldVaultV1TestOnlyProviderIdentityCapabilityMatrixPolicy.currentCapabilityMatrix()
        val caseBinding =
            SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingPolicy.currentKatCaseBinding()
        val caseBindingValidation =
            SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationPolicy
                .currentKatCaseBindingValidationReport()
        val executableKatAdmission =
            SkaldVaultV1TestOnlyProviderIdentityExecutableKatAdmissionPolicy.currentExecutableKatAdmission()
        val metadataKat =
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatPolicy.evaluateCurrentMetadataKat()
        val metadataKatValidation =
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationPolicy
                .currentExecutableMetadataKatValidationReport()
        val suiteReport =
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatSuiteReportPolicy
                .currentExecutableMetadataKatSuiteReport()
        val admissionOutcomes =
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationKatAdmissionOutcome.entries.toList()
        val futureCriteria =
            SkaldVaultV1TestOnlyProviderIdentityFutureProviderOperationKatCriterion.entries.toList()
        val forbiddenStates =
            SkaldVaultV1TestOnlyProviderIdentityForbiddenCurrentProviderOperationKatState.entries.toList()

        val markerCount =
            if (
                marker.safeId.value == EXPECTED_SAFE_ID &&
                capabilityMatrix.expectedSafeIdMatched &&
                caseBinding.expectedSafeIdMatched &&
                caseBindingValidation.expectedSafeIdMatched &&
                executableKatAdmission.expectedSafeIdMatched &&
                metadataKat.expectedSafeIdMatched &&
                metadataKatValidation.expectedSafeIdMatched &&
                suiteReport.expectedSafeIdMatched &&
                caseBinding.markerSafeId.value == EXPECTED_SAFE_ID
            ) {
                1
            } else {
                0
            }
        val caseBindingCount = suiteReport.caseBindingCount
        val executableMetadataKatCount = suiteReport.executableMetadataKatCount
        val executableMetadataKatValidationCount = suiteReport.executableMetadataKatValidationCount
        val executableMetadataKatSuiteReportCount =
            if (suiteReport.suiteReportGenerated && suiteReport.suitePassed) {
                1
            } else {
                0
            }
        val expectedSafeIdMatched = markerCount == 1
        val expectedFixtureIdMatched =
            caseBinding.expectedFixtureIdMatched &&
                caseBindingValidation.expectedFixtureIdMatched &&
                executableKatAdmission.expectedFixtureIdMatched &&
                metadataKat.expectedFixtureIdMatched &&
                metadataKatValidation.expectedFixtureIdMatched &&
                suiteReport.expectedFixtureIdMatched &&
                caseBinding.fixtureId.value == EXPECTED_FIXTURE_ID
        val expectedVectorIdMatched =
            caseBinding.expectedVectorIdMatched &&
                caseBindingValidation.expectedVectorIdMatched &&
                executableKatAdmission.expectedVectorIdMatched &&
                metadataKat.expectedVectorIdMatched &&
                metadataKatValidation.expectedVectorIdMatched &&
                suiteReport.expectedVectorIdMatched &&
                caseBinding.vectorId.value == EXPECTED_VECTOR_ID
        val expectedCaseIdMatched =
            caseBinding.expectedCaseIdMatched &&
                caseBindingValidation.expectedCaseIdMatched &&
                executableKatAdmission.expectedCaseIdMatched &&
                metadataKat.expectedCaseIdMatched &&
                metadataKatValidation.expectedCaseIdMatched &&
                suiteReport.expectedCaseIdMatched &&
                caseBinding.caseId.value == EXPECTED_CASE_ID
        val metadataKatEvaluated =
            metadataKat.metadataKatEvaluated &&
                metadataKatValidation.metadataKatEvaluated &&
                suiteReport.metadataKatEvaluated
        val metadataKatPassed =
            metadataKat.metadataKatPassed &&
                metadataKatValidation.metadataKatPassed &&
                suiteReport.metadataKatPassed
        val executableMetadataKatValidationPassed =
            metadataKatValidation.allValidationChecksPassed &&
                suiteReport.executableMetadataKatValidationPassed
        val metadataKatSuitePassed = suiteReport.suitePassed
        val admissionIsCommonTestOnly =
            marker.sourceSet == SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet.CommonTest &&
                capabilityMatrix.matrixIsCommonTestOnly &&
                caseBinding.caseIsCommonTestOnly &&
                caseBindingValidation.validationIsCommonTestOnly &&
                executableKatAdmission.admissionIsCommonTestOnly &&
                metadataKat.evaluationIsCommonTestOnly &&
                metadataKatValidation.validationIsCommonTestOnly &&
                suiteReport.suiteIsCommonTestOnly &&
                caseBinding.sourceSet ==
                SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingSourceSet.CommonTest

        val currentProviderOperationKatPresent =
            suiteReport.suiteReportsProviderOperation ||
                metadataKatValidation.validatesProviderOperation ||
                metadataKat.evaluatesProviderOperation ||
                caseBinding.canExecuteProviderOperations ||
                caseBindingValidation.canExecuteProviderOperations
        val currentProviderOperationExecutionPresent =
            suiteReport.providerOperationExecutionReachable ||
                metadataKatValidation.providerOperationExecutionReachable ||
                metadataKat.providerOperationReachable ||
                executableKatAdmission.providerOperationReachable ||
                caseBinding.providerOperationReachable ||
                caseBindingValidation.providerOperationReachable ||
                capabilityMatrix.providerOperationReachable
        val currentCryptoExecutionPresent =
            suiteReport.cryptoExecutionReachable ||
                metadataKatValidation.cryptoExecutionReachable ||
                metadataKat.cryptoExecutionReachable ||
                executableKatAdmission.cryptoExecutionReachable ||
                caseBinding.cryptoExecutionReachable ||
                caseBindingValidation.cryptoExecutionReachable ||
                capabilityMatrix.cryptoExecutionReachable
        val currentKatRunnerPresent =
            suiteReport.katRunnerPresent ||
                metadataKatValidation.katRunnerPresent ||
                metadataKat.katRunnerPresent ||
                executableKatAdmission.currentKatRunnerPresent
        val currentKatExecutorPresent =
            suiteReport.katExecutorPresent ||
                metadataKatValidation.katExecutorPresent ||
                metadataKat.katExecutorPresent ||
                executableKatAdmission.currentKatExecutorPresent ||
                executableKatAdmission.katExecutorPresent ||
                caseBindingValidation.katExecutorPresent
        val rawKatMaterialPresent =
            suiteReport.rawKatMaterialPresent ||
                metadataKatValidation.rawKatMaterialPresent ||
                metadataKat.rawKatMaterialPresent ||
                executableKatAdmission.rawKatMaterialPresent ||
                caseBindingValidation.rawKatMaterialPresent
        val rawVectorBytesPresent =
            suiteReport.rawVectorBytesPresent ||
                metadataKatValidation.rawVectorBytesPresent ||
                metadataKat.rawVectorBytesPresent ||
                executableKatAdmission.rawVectorBytesPresent ||
                caseBindingValidation.rawVectorBytesPresent
        val rawVectorHexPresent =
            suiteReport.rawVectorHexPresent ||
                metadataKatValidation.rawVectorHexPresent ||
                metadataKat.rawVectorHexPresent ||
                executableKatAdmission.rawVectorHexPresent ||
                caseBindingValidation.rawVectorHexPresent
        val publicVectorBytesPresent =
            suiteReport.publicVectorBytesPresent ||
                metadataKatValidation.publicVectorBytesPresent ||
                metadataKat.publicVectorBytesPresent ||
                executableKatAdmission.publicVectorBytesPresent ||
                caseBindingValidation.publicVectorBytesPresent
        val publicVectorHexPresent =
            suiteReport.publicVectorHexPresent ||
                metadataKatValidation.publicVectorHexPresent ||
                metadataKat.publicVectorHexPresent ||
                executableKatAdmission.publicVectorHexPresent ||
                caseBindingValidation.publicVectorHexPresent

        val providerOperationReachable = currentProviderOperationExecutionPresent
        val cryptoExecutionReachable = currentCryptoExecutionPresent
        val runtimeSelectable =
            suiteReport.runtimeSelectable ||
                metadataKatValidation.runtimeSelectable ||
                metadataKat.runtimeSelectable ||
                executableKatAdmission.runtimeSelectable ||
                caseBinding.runtimeSelectable ||
                caseBindingValidation.runtimeSelectable ||
                capabilityMatrix.runtimeSelectable
        val registrySelectable =
            suiteReport.registrySelectable ||
                metadataKatValidation.registrySelectable ||
                metadataKat.registrySelectable ||
                executableKatAdmission.registrySelectable ||
                caseBinding.registrySelectable ||
                caseBindingValidation.registrySelectable ||
                capabilityMatrix.registrySelectable
        val factoryReachable =
            suiteReport.factoryReachable ||
                metadataKatValidation.factoryReachable ||
                metadataKat.factoryReachable ||
                executableKatAdmission.factoryReachable ||
                caseBinding.factoryReachable ||
                caseBindingValidation.factoryReachable ||
                capabilityMatrix.factoryReachable
        val dispatcherReachable =
            suiteReport.dispatcherReachable ||
                metadataKatValidation.dispatcherReachable ||
                metadataKat.dispatcherReachable ||
                executableKatAdmission.dispatcherReachable ||
                caseBinding.dispatcherReachable ||
                caseBindingValidation.dispatcherReachable ||
                capabilityMatrix.dispatcherReachable
        val executorTargetable =
            suiteReport.executorTargetable ||
                metadataKatValidation.executorTargetable ||
                metadataKat.executorTargetable ||
                executableKatAdmission.executorTargetable ||
                caseBinding.executorTargetable ||
                caseBindingValidation.executorTargetable ||
                capabilityMatrix.executorTargetable
        val providerKatExecutorReachable =
            suiteReport.providerKatExecutorReachable ||
                metadataKatValidation.providerKatExecutorReachable ||
                metadataKat.providerKatExecutorReachable ||
                executableKatAdmission.providerKatExecutorReachable ||
                caseBinding.providerKatExecutorReachable ||
                caseBindingValidation.providerKatExecutorReachable ||
                capabilityMatrix.providerKatExecutorReachable
        val vaultLifecycleReachable =
            suiteReport.vaultLifecycleReachable ||
                metadataKatValidation.vaultLifecycleReachable ||
                metadataKat.vaultLifecycleReachable ||
                executableKatAdmission.vaultLifecycleReachable ||
                caseBinding.vaultLifecycleReachable ||
                caseBindingValidation.vaultLifecycleReachable ||
                capabilityMatrix.vaultLifecycleReachable
        val persistenceReachable =
            suiteReport.persistenceReachable ||
                metadataKatValidation.persistenceReachable ||
                metadataKat.persistenceReachable ||
                executableKatAdmission.persistenceReachable ||
                caseBinding.persistenceReachable ||
                caseBindingValidation.persistenceReachable ||
                capabilityMatrix.persistenceReachable
        val productionSyncReachable =
            suiteReport.productionSyncReachable ||
                metadataKatValidation.productionSyncReachable ||
                metadataKat.productionSyncReachable ||
                executableKatAdmission.productionSyncReachable ||
                caseBinding.productionSyncReachable ||
                caseBindingValidation.productionSyncReachable ||
                capabilityMatrix.productionSyncReachable
        val backendClientReachable =
            suiteReport.backendClientReachable ||
                metadataKatValidation.backendClientReachable ||
                metadataKat.backendClientReachable ||
                executableKatAdmission.backendClientReachable ||
                caseBinding.backendClientReachable ||
                caseBindingValidation.backendClientReachable ||
                capabilityMatrix.backendClientReachable
        val bdkWalletStateReachable =
            suiteReport.bdkWalletStateReachable ||
                metadataKatValidation.bdkWalletStateReachable ||
                metadataKat.bdkWalletStateReachable ||
                executableKatAdmission.bdkWalletStateReachable ||
                caseBinding.bdkWalletStateReachable ||
                caseBindingValidation.bdkWalletStateReachable ||
                capabilityMatrix.bdkWalletStateReachable
        val settingsCodecReachable =
            suiteReport.settingsCodecReachable ||
                metadataKatValidation.settingsCodecReachable ||
                metadataKat.settingsCodecReachable ||
                executableKatAdmission.settingsCodecReachable ||
                caseBinding.settingsCodecReachable ||
                caseBindingValidation.settingsCodecReachable ||
                capabilityMatrix.settingsCodecReachable
        val uiSurfaceReachable =
            suiteReport.uiSurfaceReachable ||
                metadataKatValidation.uiSurfaceReachable ||
                metadataKat.uiSurfaceReachable ||
                executableKatAdmission.uiSurfaceReachable ||
                caseBinding.uiSurfaceReachable ||
                caseBindingValidation.uiSurfaceReachable ||
                capabilityMatrix.uiSurfaceReachable
        val signingBroadcastingReachable =
            suiteReport.signingBroadcastingReachable ||
                metadataKatValidation.signingBroadcastingReachable ||
                metadataKat.signingBroadcastingReachable ||
                executableKatAdmission.signingBroadcastingReachable ||
                caseBinding.signingBroadcastingReachable ||
                caseBindingValidation.signingBroadcastingReachable ||
                capabilityMatrix.signingBroadcastingReachable
        val publicEndpointReachable =
            suiteReport.publicEndpointReachable ||
                metadataKatValidation.publicEndpointReachable ||
                metadataKat.publicEndpointReachable ||
                executableKatAdmission.publicEndpointReachable ||
                caseBinding.publicEndpointReachable ||
                caseBindingValidation.publicEndpointReachable ||
                capabilityMatrix.publicEndpointReachable
        val mainnetReachable =
            suiteReport.mainnetReachable ||
                metadataKatValidation.mainnetReachable ||
                metadataKat.mainnetReachable ||
                executableKatAdmission.mainnetReachable ||
                caseBinding.mainnetReachable ||
                caseBindingValidation.mainnetReachable ||
                capabilityMatrix.mainnetReachable
        val implementsVaultCryptoProvider =
            suiteReport.implementsVaultCryptoProvider ||
                metadataKatValidation.implementsVaultCryptoProvider ||
                metadataKat.implementsVaultCryptoProvider ||
                executableKatAdmission.implementsVaultCryptoProvider ||
                caseBinding.implementsVaultCryptoProvider ||
                caseBindingValidation.implementsVaultCryptoProvider ||
                capabilityMatrix.implementsVaultCryptoProvider
        val containsVaultCryptoProvider =
            suiteReport.containsVaultCryptoProvider ||
                metadataKatValidation.containsVaultCryptoProvider ||
                metadataKat.containsVaultCryptoProvider ||
                executableKatAdmission.containsVaultCryptoProvider ||
                caseBinding.containsVaultCryptoProvider ||
                caseBindingValidation.containsVaultCryptoProvider ||
                capabilityMatrix.containsVaultCryptoProvider
        val canExecuteProviderOperations =
            suiteReport.canExecuteProviderOperations ||
                metadataKatValidation.canExecuteProviderOperations ||
                metadataKat.canExecuteProviderOperations ||
                executableKatAdmission.canExecuteProviderOperations ||
                caseBinding.canExecuteProviderOperations ||
                caseBindingValidation.canExecuteProviderOperations ||
                capabilityMatrix.canExecuteProviderOperations
        val canExecuteCrypto =
            suiteReport.canExecuteCrypto ||
                metadataKatValidation.canExecuteCrypto ||
                metadataKat.canExecuteCrypto ||
                executableKatAdmission.canExecuteCrypto ||
                caseBinding.canExecuteCrypto ||
                caseBindingValidation.canExecuteCrypto ||
                capabilityMatrix.canExecuteCrypto
        val canUseForVaultLifecycle =
            suiteReport.canUseForVaultLifecycle ||
                metadataKatValidation.canUseForVaultLifecycle ||
                metadataKat.canUseForVaultLifecycle ||
                executableKatAdmission.canUseForVaultLifecycle ||
                caseBinding.canUseForVaultLifecycle ||
                caseBindingValidation.canUseForVaultLifecycle ||
                capabilityMatrix.canUseForVaultLifecycle
        val canUseForPersistence =
            suiteReport.canUseForPersistence ||
                metadataKatValidation.canUseForPersistence ||
                metadataKat.canUseForPersistence ||
                executableKatAdmission.canUseForPersistence ||
                caseBinding.canUseForPersistence ||
                caseBindingValidation.canUseForPersistence ||
                capabilityMatrix.canUseForPersistence
        val canUseForSync =
            suiteReport.canUseForSync ||
                metadataKatValidation.canUseForSync ||
                metadataKat.canUseForSync ||
                executableKatAdmission.canUseForSync ||
                caseBinding.canUseForSync ||
                caseBindingValidation.canUseForSync ||
                capabilityMatrix.canUseForSync
        val canUseForSigning =
            suiteReport.canUseForSigning ||
                metadataKatValidation.canUseForSigning ||
                metadataKat.canUseForSigning ||
                executableKatAdmission.canUseForSigning ||
                caseBinding.canUseForSigning ||
                caseBindingValidation.canUseForSigning ||
                capabilityMatrix.canUseForSigning
        val canUseForBroadcasting =
            suiteReport.canUseForBroadcasting ||
                metadataKatValidation.canUseForBroadcasting ||
                metadataKat.canUseForBroadcasting ||
                executableKatAdmission.canUseForBroadcasting ||
                caseBinding.canUseForBroadcasting ||
                caseBindingValidation.canUseForBroadcasting ||
                capabilityMatrix.canUseForBroadcasting
        val canUseForMainnet =
            suiteReport.canUseForMainnet ||
                metadataKatValidation.canUseForMainnet ||
                metadataKat.canUseForMainnet ||
                executableKatAdmission.canUseForMainnet ||
                caseBinding.canUseForMainnet ||
                caseBindingValidation.canUseForMainnet ||
                capabilityMatrix.canUseForMainnet
        val productionProviderSelectable =
            suiteReport.productionProviderSelectable ||
                metadataKatValidation.productionProviderSelectable ||
                metadataKat.productionProviderSelectable ||
                executableKatAdmission.productionProviderSelectable ||
                caseBinding.productionProviderSelectable ||
                caseBindingValidation.productionProviderSelectable ||
                capabilityMatrix.productionProviderSelectable ||
                marker.productionProviderSelectable

        return SkaldVaultV1TestOnlyProviderIdentityProviderOperationKatAdmission(
            markerCount = markerCount,
            caseBindingCount = caseBindingCount,
            executableMetadataKatCount = executableMetadataKatCount,
            executableMetadataKatValidationCount = executableMetadataKatValidationCount,
            executableMetadataKatSuiteReportCount = executableMetadataKatSuiteReportCount,
            expectedSafeIdMatched = expectedSafeIdMatched,
            expectedFixtureIdMatched = expectedFixtureIdMatched,
            expectedVectorIdMatched = expectedVectorIdMatched,
            expectedCaseIdMatched = expectedCaseIdMatched,
            metadataKatEvaluated = metadataKatEvaluated,
            metadataKatPassed = metadataKatPassed,
            executableMetadataKatValidationPassed = executableMetadataKatValidationPassed,
            metadataKatSuitePassed = metadataKatSuitePassed,
            admissionIsCommonTestOnly = admissionIsCommonTestOnly,
            admissionIsProductionAuthorization = false,
            admissionIsProviderSelectionAuthorization = false,
            admissionIsProviderOperationAuthorization = false,
            admissionIsKatExecutorAuthorization = false,
            admissionIsCryptoAuthorization = false,
            admissionIsVaultPersistenceAuthorization = false,
            admissionIsMainnetAuthorization = false,
            futureProviderOperationKatCriteriaModeled =
                futureCriteria.size ==
                    SkaldVaultV1TestOnlyProviderIdentityFutureProviderOperationKatCriterion.entries.size,
            futureProviderOperationKatCriteriaAuthorizeCurrentExecution = false,
            currentProviderOperationKatPresent = currentProviderOperationKatPresent,
            currentProviderOperationExecutionPresent = currentProviderOperationExecutionPresent,
            currentCryptoExecutionPresent = currentCryptoExecutionPresent,
            currentKatRunnerPresent = currentKatRunnerPresent,
            currentKatExecutorPresent = currentKatExecutorPresent,
            rawKatMaterialPresent = rawKatMaterialPresent,
            rawVectorBytesPresent = rawVectorBytesPresent,
            rawVectorHexPresent = rawVectorHexPresent,
            publicVectorBytesPresent = publicVectorBytesPresent,
            publicVectorHexPresent = publicVectorHexPresent,
            providerOperationReachable = providerOperationReachable,
            cryptoExecutionReachable = cryptoExecutionReachable,
            runtimeSelectable = runtimeSelectable,
            registrySelectable = registrySelectable,
            factoryReachable = factoryReachable,
            dispatcherReachable = dispatcherReachable,
            executorTargetable = executorTargetable,
            providerKatExecutorReachable = providerKatExecutorReachable,
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
            futureProviderOperationKatCriteria = futureCriteria,
            forbiddenCurrentProviderOperationKatStates = forbiddenStates,
            sourceSet =
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationKatAdmissionSourceSet.CommonTest,
            displayLabel = SkaldVaultV1TestOnlyProviderIdentityProviderOperationKatAdmissionSafeLabel(
                "provider-operation KAT admission evidence",
            ),
        )
    }
}
