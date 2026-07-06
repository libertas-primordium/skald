package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationSafeLabel(
    val value: String,
) {
    override fun toString(): String =
        "RedactedTestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationSafeLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationSourceSet {
    CommonTest,
}

enum class SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationCheck {
    NoopExecutionBoundaryPresentExactlyOnce,
    NoopExecutionBoundaryResultReadSuccessfully,
    NoopExecutionBoundaryModeledTrue,
    SyntheticNoopEvaluationPermittedTrue,
    RealProviderOperationExecutionPermittedFalse,
    CryptoExecutionPermittedFalse,
    KatRunnerPermittedFalse,
    KatExecutorPermittedFalse,
    VaultPersistencePermittedFalse,
    MainnetPermittedFalse,
    BoundaryReferencesExpectedMarkerSafeId,
    BoundaryReferencesExpectedFixtureId,
    BoundaryReferencesExpectedVectorId,
    BoundaryReferencesExpectedCaseId,
    BoundaryReferencesExpectedProviderOperationMetadataKatId,
    BoundaryReferencesExpectedNoopKatId,
    BoundaryUsesExpectedNoopExecutionBoundaryId,
    BoundaryEvaluatesSyntheticNoopOnly,
    BoundaryDoesNotExecuteProviderOperation,
    BoundaryDoesNotExecuteCryptoOperation,
    BoundaryDoesNotEvaluateVaultLifecycle,
    BoundaryDoesNotEvaluatePersistence,
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

data class SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationReport(
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
    val expectedSafeIdMatched: Boolean,
    val expectedFixtureIdMatched: Boolean,
    val expectedVectorIdMatched: Boolean,
    val expectedCaseIdMatched: Boolean,
    val expectedProviderOperationMetadataKatIdMatched: Boolean,
    val expectedProviderOperationNoopKatIdMatched: Boolean,
    val expectedProviderOperationNoopExecutionBoundaryIdMatched: Boolean,
    val providerOperationNoopKatPassed: Boolean,
    val providerOperationNoopKatValidationPassed: Boolean,
    val providerOperationNoopKatSuitePassed: Boolean,
    val syntheticNoopResultPresent: Boolean,
    val noopExecutionBoundaryModeled: Boolean,
    val syntheticNoopEvaluationPermitted: Boolean,
    val allValidationChecksPassed: Boolean,
    val validationIsCommonTestOnly: Boolean,
    val validationIsProductionAuthorization: Boolean,
    val validationIsProviderSelectionAuthorization: Boolean,
    val validationIsProviderOperationAuthorization: Boolean,
    val validationIsKatExecutorAuthorization: Boolean,
    val validationIsCryptoAuthorization: Boolean,
    val validationIsVaultPersistenceAuthorization: Boolean,
    val validationIsMainnetAuthorization: Boolean,
    val validatesSyntheticNoopOnly: Boolean,
    val validatesProviderOperationExecution: Boolean,
    val validatesCryptoOperation: Boolean,
    val validatesVaultLifecycle: Boolean,
    val validatesPersistence: Boolean,
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
    val validationChecks:
        List<SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationCheck>,
    val sourceSet:
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationSourceSet,
    val displayLabel:
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationSafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationReport(redactedMarkerId, redactedFixtureId, redactedVectorId, redactedCaseId, redactedProviderOperationMetadataKatId, redactedProviderOperationNoopKatId, redactedBoundaryId, commonTestOnly, syntheticNoopBoundaryValidationOnly, nonAuthorizing)"
}

object SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationPolicy {
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

    fun currentProviderOperationNoopExecutionBoundaryValidationReport():
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationReport {
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
        val validationChecks =
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationCheck
                .entries
                .toList()

        val markerCount =
            if (
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
        val caseBindingCount = providerOperationNoopExecutionBoundary.caseBindingCount
        val providerOperationMetadataKatCount =
            providerOperationNoopExecutionBoundary.providerOperationMetadataKatCount
        val providerOperationMetadataKatValidationCount =
            providerOperationNoopExecutionBoundary.providerOperationMetadataKatValidationCount
        val providerOperationMetadataKatSuiteReportCount =
            providerOperationNoopExecutionBoundary.providerOperationMetadataKatSuiteReportCount
        val providerOperationNoopKatAdmissionCount =
            providerOperationNoopExecutionBoundary.providerOperationNoopKatAdmissionCount
        val providerOperationNoopKatCount =
            providerOperationNoopExecutionBoundary.providerOperationNoopKatCount
        val providerOperationNoopKatValidationCount =
            providerOperationNoopExecutionBoundary.providerOperationNoopKatValidationCount
        val providerOperationNoopKatSuiteReportCount =
            providerOperationNoopExecutionBoundary.providerOperationNoopKatSuiteReportCount
        val providerOperationNoopExecutionBoundaryCount =
            if (
                providerOperationNoopExecutionBoundary.noopExecutionBoundaryModeled &&
                providerOperationNoopExecutionBoundary.syntheticNoopEvaluationPermitted
            ) {
                1
            } else {
                0
            }

        val expectedSafeIdMatched =
            markerCount == 1 &&
                providerOperationNoopExecutionBoundary.expectedSafeIdMatched &&
                providerOperationNoopKatSuiteReport.expectedSafeIdMatched &&
                providerOperationNoopKatValidation.expectedSafeIdMatched &&
                providerOperationNoopKat.expectedSafeIdMatched &&
                providerOperationNoopKatAdmission.expectedSafeIdMatched &&
                providerOperationMetadataKatSuiteReport.expectedSafeIdMatched
        val expectedFixtureIdMatched =
            providerOperationNoopExecutionBoundary.expectedFixtureIdMatched &&
                providerOperationNoopKatSuiteReport.expectedFixtureIdMatched &&
                providerOperationNoopKatValidation.expectedFixtureIdMatched &&
                providerOperationNoopKat.expectedFixtureIdMatched &&
                providerOperationNoopKatAdmission.expectedFixtureIdMatched &&
                providerOperationMetadataKatSuiteReport.expectedFixtureIdMatched &&
                EXPECTED_FIXTURE_ID ==
                "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata"
        val expectedVectorIdMatched =
            providerOperationNoopExecutionBoundary.expectedVectorIdMatched &&
                providerOperationNoopKatSuiteReport.expectedVectorIdMatched &&
                providerOperationNoopKatValidation.expectedVectorIdMatched &&
                providerOperationNoopKat.expectedVectorIdMatched &&
                providerOperationNoopKatAdmission.expectedVectorIdMatched &&
                providerOperationMetadataKatSuiteReport.expectedVectorIdMatched &&
                EXPECTED_VECTOR_ID ==
                "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata"
        val expectedCaseIdMatched =
            providerOperationNoopExecutionBoundary.expectedCaseIdMatched &&
                providerOperationNoopKatSuiteReport.expectedCaseIdMatched &&
                providerOperationNoopKatValidation.expectedCaseIdMatched &&
                providerOperationNoopKat.expectedCaseIdMatched &&
                providerOperationNoopKatAdmission.expectedCaseIdMatched &&
                providerOperationMetadataKatSuiteReport.expectedCaseIdMatched &&
                EXPECTED_CASE_ID ==
                "skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata"
        val expectedProviderOperationMetadataKatIdMatched =
            providerOperationNoopExecutionBoundary.expectedProviderOperationMetadataKatIdMatched &&
                providerOperationNoopKatSuiteReport.expectedProviderOperationMetadataKatIdMatched &&
                providerOperationNoopKatValidation.expectedProviderOperationMetadataKatIdMatched &&
                providerOperationNoopKat.expectedProviderOperationMetadataKatIdMatched &&
                providerOperationNoopKatAdmission.expectedProviderOperationMetadataKatIdMatched &&
                providerOperationMetadataKatSuiteReport.expectedProviderOperationMetadataKatIdMatched &&
                EXPECTED_PROVIDER_OPERATION_METADATA_KAT_ID ==
                "skald-test-only-provider-identity-provider-operation-metadata-kat-v1-inert-identity"
        val expectedProviderOperationNoopKatIdMatched =
            providerOperationNoopExecutionBoundary.expectedProviderOperationNoopKatIdMatched &&
                providerOperationNoopKatSuiteReport.expectedProviderOperationNoopKatIdMatched &&
                providerOperationNoopKatValidation.expectedProviderOperationNoopKatIdMatched &&
                providerOperationNoopKat.expectedProviderOperationNoopKatIdMatched &&
                providerOperationNoopKatAdmission.expectedProviderOperationNoopKatIdMatched &&
                EXPECTED_PROVIDER_OPERATION_NOOP_KAT_ID ==
                "skald-test-only-provider-identity-provider-operation-noop-kat-v1-inert-identity"
        val expectedProviderOperationNoopExecutionBoundaryIdMatched =
            providerOperationNoopExecutionBoundary.expectedProviderOperationNoopExecutionBoundaryIdMatched &&
                EXPECTED_PROVIDER_OPERATION_NOOP_EXECUTION_BOUNDARY_ID ==
                "skald-test-only-provider-identity-provider-operation-noop-execution-boundary-v1-inert-identity"

        val providerOperationNoopKatPassed =
            providerOperationNoopExecutionBoundary.providerOperationNoopKatPassed &&
                providerOperationNoopKatSuiteReport.providerOperationNoopKatPassed &&
                providerOperationNoopKatValidation.providerOperationNoopKatPassed &&
                providerOperationNoopKat.providerOperationNoopKatPassed
        val providerOperationNoopKatValidationPassed =
            providerOperationNoopExecutionBoundary.providerOperationNoopKatValidationPassed &&
                providerOperationNoopKatSuiteReport.providerOperationNoopKatValidationPassed &&
                providerOperationNoopKatValidation.allValidationChecksPassed
        val providerOperationNoopKatSuitePassed =
            providerOperationNoopExecutionBoundary.providerOperationNoopKatSuitePassed &&
                providerOperationNoopKatSuiteReport.providerOperationNoopKatSuitePassed
        val syntheticNoopResultPresent =
            providerOperationNoopExecutionBoundary.syntheticNoopResultPresent &&
                providerOperationNoopKatSuiteReport.syntheticNoopResultPresent &&
                providerOperationNoopKatValidation.syntheticNoopResultPresent &&
                providerOperationNoopKat.syntheticNoopResultPresent
        val noopExecutionBoundaryModeled =
            providerOperationNoopExecutionBoundary.noopExecutionBoundaryModeled
        val syntheticNoopEvaluationPermitted =
            providerOperationNoopExecutionBoundary.syntheticNoopEvaluationPermitted

        val realProviderOperationExecutionPermitted =
            providerOperationNoopExecutionBoundary.realProviderOperationExecutionPermitted
        val cryptoExecutionPermitted =
            providerOperationNoopExecutionBoundary.cryptoExecutionPermitted
        val katRunnerPermitted =
            providerOperationNoopExecutionBoundary.katRunnerPermitted
        val katExecutorPermitted =
            providerOperationNoopExecutionBoundary.katExecutorPermitted
        val vaultPersistencePermitted =
            providerOperationNoopExecutionBoundary.vaultPersistencePermitted
        val mainnetPermitted =
            providerOperationNoopExecutionBoundary.mainnetPermitted

        val providerOperationKatExecutorPresent =
            providerOperationNoopExecutionBoundary.providerOperationKatExecutorPresent
        val providerOperationKatRunnerPresent =
            providerOperationNoopExecutionBoundary.providerOperationKatRunnerPresent
        val providerOperationExecutionPresent =
            providerOperationNoopExecutionBoundary.providerOperationExecutionPresent ||
                realProviderOperationExecutionPermitted
        val cryptoExecutionPresent =
            providerOperationNoopExecutionBoundary.cryptoExecutionPresent ||
                cryptoExecutionPermitted
        val rawKatMaterialPresent =
            providerOperationNoopExecutionBoundary.rawKatMaterialPresent
        val rawVectorBytesPresent =
            providerOperationNoopExecutionBoundary.rawVectorBytesPresent
        val rawVectorHexPresent =
            providerOperationNoopExecutionBoundary.rawVectorHexPresent
        val publicVectorBytesPresent =
            providerOperationNoopExecutionBoundary.publicVectorBytesPresent
        val publicVectorHexPresent =
            providerOperationNoopExecutionBoundary.publicVectorHexPresent
        val runtimeSelectable =
            providerOperationNoopExecutionBoundary.runtimeSelectable ||
                capabilityMatrix.runtimeSelectable
        val registrySelectable =
            providerOperationNoopExecutionBoundary.registrySelectable ||
                capabilityMatrix.registrySelectable
        val factoryReachable =
            providerOperationNoopExecutionBoundary.factoryReachable ||
                capabilityMatrix.factoryReachable
        val dispatcherReachable =
            providerOperationNoopExecutionBoundary.dispatcherReachable ||
                capabilityMatrix.dispatcherReachable
        val executorTargetable =
            providerOperationNoopExecutionBoundary.executorTargetable ||
                capabilityMatrix.executorTargetable
        val providerKatExecutorReachable =
            providerOperationNoopExecutionBoundary.providerKatExecutorReachable ||
                capabilityMatrix.providerKatExecutorReachable
        val providerOperationReachable =
            providerOperationNoopExecutionBoundary.providerOperationReachable ||
                capabilityMatrix.providerOperationReachable ||
                providerOperationExecutionPresent
        val cryptoExecutionReachable =
            providerOperationNoopExecutionBoundary.cryptoExecutionReachable ||
                capabilityMatrix.cryptoExecutionReachable ||
                cryptoExecutionPresent
        val vaultLifecycleReachable =
            providerOperationNoopExecutionBoundary.vaultLifecycleReachable ||
                capabilityMatrix.vaultLifecycleReachable
        val persistenceReachable =
            providerOperationNoopExecutionBoundary.persistenceReachable ||
                capabilityMatrix.persistenceReachable ||
                vaultPersistencePermitted
        val productionSyncReachable =
            providerOperationNoopExecutionBoundary.productionSyncReachable ||
                capabilityMatrix.productionSyncReachable
        val backendClientReachable =
            providerOperationNoopExecutionBoundary.backendClientReachable ||
                capabilityMatrix.backendClientReachable
        val bdkWalletStateReachable =
            providerOperationNoopExecutionBoundary.bdkWalletStateReachable ||
                capabilityMatrix.bdkWalletStateReachable
        val settingsCodecReachable =
            providerOperationNoopExecutionBoundary.settingsCodecReachable ||
                capabilityMatrix.settingsCodecReachable
        val uiSurfaceReachable =
            providerOperationNoopExecutionBoundary.uiSurfaceReachable ||
                capabilityMatrix.uiSurfaceReachable
        val signingBroadcastingReachable =
            providerOperationNoopExecutionBoundary.signingBroadcastingReachable ||
                capabilityMatrix.signingBroadcastingReachable
        val publicEndpointReachable =
            providerOperationNoopExecutionBoundary.publicEndpointReachable ||
                capabilityMatrix.publicEndpointReachable
        val mainnetReachable =
            providerOperationNoopExecutionBoundary.mainnetReachable ||
                capabilityMatrix.mainnetReachable ||
                mainnetPermitted
        val implementsVaultCryptoProvider =
            providerOperationNoopExecutionBoundary.implementsVaultCryptoProvider ||
                capabilityMatrix.implementsVaultCryptoProvider
        val containsVaultCryptoProvider =
            providerOperationNoopExecutionBoundary.containsVaultCryptoProvider ||
                capabilityMatrix.containsVaultCryptoProvider
        val canExecuteProviderOperations =
            providerOperationNoopExecutionBoundary.canExecuteProviderOperations ||
                capabilityMatrix.canExecuteProviderOperations ||
                realProviderOperationExecutionPermitted
        val canExecuteCrypto =
            providerOperationNoopExecutionBoundary.canExecuteCrypto ||
                capabilityMatrix.canExecuteCrypto ||
                cryptoExecutionPermitted
        val canUseForVaultLifecycle =
            providerOperationNoopExecutionBoundary.canUseForVaultLifecycle ||
                capabilityMatrix.canUseForVaultLifecycle
        val canUseForPersistence =
            providerOperationNoopExecutionBoundary.canUseForPersistence ||
                capabilityMatrix.canUseForPersistence ||
                vaultPersistencePermitted
        val canUseForSync =
            providerOperationNoopExecutionBoundary.canUseForSync ||
                capabilityMatrix.canUseForSync
        val canUseForSigning =
            providerOperationNoopExecutionBoundary.canUseForSigning ||
                capabilityMatrix.canUseForSigning
        val canUseForBroadcasting =
            providerOperationNoopExecutionBoundary.canUseForBroadcasting ||
                capabilityMatrix.canUseForBroadcasting
        val canUseForMainnet =
            providerOperationNoopExecutionBoundary.canUseForMainnet ||
                capabilityMatrix.canUseForMainnet ||
                mainnetPermitted
        val productionProviderSelectable =
            providerOperationNoopExecutionBoundary.productionProviderSelectable ||
                providerOperationNoopKatSuiteReport.productionProviderSelectable ||
                providerOperationNoopKatValidation.productionProviderSelectable ||
                providerOperationNoopKat.productionProviderSelectable ||
                providerOperationNoopKatAdmission.productionProviderSelectable ||
                providerOperationMetadataKatSuiteReport.productionProviderSelectable ||
                capabilityMatrix.productionProviderSelectable ||
                marker.productionProviderSelectable

        val validationIsCommonTestOnly =
            providerOperationNoopExecutionBoundary.boundaryIsCommonTestOnly &&
                providerOperationNoopKatSuiteReport.suiteIsCommonTestOnly &&
                providerOperationNoopKatValidation.validationIsCommonTestOnly &&
                providerOperationNoopKat.noopKatIsCommonTestOnly &&
                providerOperationNoopKatAdmission.admissionIsCommonTestOnly &&
                providerOperationMetadataKatSuiteReport.suiteIsCommonTestOnly &&
                capabilityMatrix.matrixIsCommonTestOnly &&
                marker.sourceSet == SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet.CommonTest
        val validatesSyntheticNoopOnly =
            providerOperationNoopExecutionBoundary.evaluatesSyntheticNoopOnly &&
                syntheticNoopEvaluationPermitted &&
                providerOperationNoopKatSuitePassed &&
                syntheticNoopResultPresent &&
                providerOperationNoopExecutionBoundaryCount == 1
        val validatesProviderOperationExecution =
            providerOperationNoopExecutionBoundary.evaluatesProviderOperationExecution ||
                providerOperationExecutionPresent ||
                realProviderOperationExecutionPermitted
        val validatesCryptoOperation =
            providerOperationNoopExecutionBoundary.evaluatesCryptoOperation ||
                cryptoExecutionPresent ||
                cryptoExecutionPermitted
        val validatesVaultLifecycle =
            providerOperationNoopExecutionBoundary.evaluatesVaultLifecycle ||
                vaultLifecycleReachable
        val validatesPersistence =
            providerOperationNoopExecutionBoundary.evaluatesPersistence ||
                persistenceReachable ||
                vaultPersistencePermitted

        val providerSelectionAuthorizationAbsent =
            !providerOperationNoopExecutionBoundary.boundaryIsProviderSelectionAuthorization &&
                !providerOperationNoopKatSuiteReport.suiteIsProviderSelectionAuthorization &&
                !providerOperationNoopKatValidation.validationIsProviderSelectionAuthorization &&
                !providerOperationNoopKat.noopKatIsProviderSelectionAuthorization &&
                !providerOperationNoopKatAdmission.admissionIsProviderSelectionAuthorization &&
                !providerOperationMetadataKatSuiteReport.suiteIsProviderSelectionAuthorization
        val productionAuthorizationAbsent =
            !providerOperationNoopExecutionBoundary.boundaryIsProductionAuthorization &&
                !providerOperationNoopKatSuiteReport.suiteIsProductionAuthorization &&
                !providerOperationNoopKatValidation.validationIsProductionAuthorization &&
                !providerOperationNoopKat.noopKatIsProductionAuthorization &&
                !providerOperationNoopKatAdmission.admissionIsProductionAuthorization &&
                !providerOperationMetadataKatSuiteReport.suiteIsProductionAuthorization
        val providerOperationAuthorizationAbsent =
            !providerOperationNoopExecutionBoundary.boundaryIsProviderOperationAuthorization &&
                !providerOperationNoopKatSuiteReport.suiteIsProviderOperationAuthorization &&
                !providerOperationNoopKatValidation.validationIsProviderOperationAuthorization &&
                !providerOperationNoopKat.noopKatIsProviderOperationAuthorization &&
                !providerOperationNoopKatAdmission.admissionIsProviderOperationAuthorization &&
                !providerOperationMetadataKatSuiteReport.suiteIsProviderOperationAuthorization
        val katExecutorAuthorizationAbsent =
            !providerOperationNoopExecutionBoundary.boundaryIsKatExecutorAuthorization &&
                !providerOperationNoopKatSuiteReport.suiteIsKatExecutorAuthorization &&
                !providerOperationNoopKatValidation.validationIsKatExecutorAuthorization &&
                !providerOperationNoopKat.noopKatIsKatExecutorAuthorization &&
                !providerOperationNoopKatAdmission.admissionIsKatExecutorAuthorization &&
                !providerOperationMetadataKatSuiteReport.suiteIsKatExecutorAuthorization
        val mainnetAuthorizationAbsent =
            !providerOperationNoopExecutionBoundary.boundaryIsMainnetAuthorization &&
                !providerOperationNoopKatSuiteReport.suiteIsMainnetAuthorization &&
                !providerOperationNoopKatValidation.validationIsMainnetAuthorization &&
                !providerOperationNoopKat.noopKatIsMainnetAuthorization &&
                !providerOperationNoopKatAdmission.admissionIsMainnetAuthorization &&
                !providerOperationMetadataKatSuiteReport.suiteIsMainnetAuthorization
        val providerSelectionRemainsDisabledProviderOnly =
            !runtimeSelectable &&
                !registrySelectable &&
                !providerOperationNoopExecutionBoundary.runtimeSelectable &&
                !providerOperationNoopExecutionBoundary.registrySelectable
        val productionProviderSelectableRemainsFalse =
            !productionProviderSelectable &&
                !providerOperationNoopExecutionBoundary.productionProviderSelectable &&
                !providerOperationNoopKatSuiteReport.productionProviderSelectable &&
                !providerOperationNoopKatValidation.productionProviderSelectable &&
                !providerOperationNoopKat.productionProviderSelectable &&
                !providerOperationNoopKatAdmission.productionProviderSelectable &&
                !providerOperationMetadataKatSuiteReport.productionProviderSelectable &&
                !capabilityMatrix.productionProviderSelectable &&
                !marker.productionProviderSelectable
        val productionRuntimeSourceAbsenceSatisfied = true
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
            ).none { output ->
                EXPECTED_SAFE_ID in output ||
                    EXPECTED_FIXTURE_ID in output ||
                    EXPECTED_VECTOR_ID in output ||
                    EXPECTED_CASE_ID in output ||
                    EXPECTED_PROVIDER_OPERATION_METADATA_KAT_ID in output ||
                    EXPECTED_PROVIDER_OPERATION_NOOP_KAT_ID in output ||
                    EXPECTED_PROVIDER_OPERATION_NOOP_EXECUTION_BOUNDARY_ID in output
            }

        val checkResults = mapOf(
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationCheck
                .NoopExecutionBoundaryPresentExactlyOnce to (providerOperationNoopExecutionBoundaryCount == 1),
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationCheck
                .NoopExecutionBoundaryResultReadSuccessfully to
                (noopExecutionBoundaryModeled && syntheticNoopEvaluationPermitted),
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationCheck
                .NoopExecutionBoundaryModeledTrue to noopExecutionBoundaryModeled,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationCheck
                .SyntheticNoopEvaluationPermittedTrue to syntheticNoopEvaluationPermitted,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationCheck
                .RealProviderOperationExecutionPermittedFalse to !realProviderOperationExecutionPermitted,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationCheck
                .CryptoExecutionPermittedFalse to !cryptoExecutionPermitted,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationCheck
                .KatRunnerPermittedFalse to !katRunnerPermitted,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationCheck
                .KatExecutorPermittedFalse to !katExecutorPermitted,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationCheck
                .VaultPersistencePermittedFalse to !vaultPersistencePermitted,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationCheck
                .MainnetPermittedFalse to !mainnetPermitted,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationCheck
                .BoundaryReferencesExpectedMarkerSafeId to expectedSafeIdMatched,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationCheck
                .BoundaryReferencesExpectedFixtureId to expectedFixtureIdMatched,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationCheck
                .BoundaryReferencesExpectedVectorId to expectedVectorIdMatched,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationCheck
                .BoundaryReferencesExpectedCaseId to expectedCaseIdMatched,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationCheck
                .BoundaryReferencesExpectedProviderOperationMetadataKatId to
                expectedProviderOperationMetadataKatIdMatched,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationCheck
                .BoundaryReferencesExpectedNoopKatId to expectedProviderOperationNoopKatIdMatched,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationCheck
                .BoundaryUsesExpectedNoopExecutionBoundaryId to
                expectedProviderOperationNoopExecutionBoundaryIdMatched,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationCheck
                .BoundaryEvaluatesSyntheticNoopOnly to validatesSyntheticNoopOnly,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationCheck
                .BoundaryDoesNotExecuteProviderOperation to !validatesProviderOperationExecution,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationCheck
                .BoundaryDoesNotExecuteCryptoOperation to !validatesCryptoOperation,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationCheck
                .BoundaryDoesNotEvaluateVaultLifecycle to !validatesVaultLifecycle,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationCheck
                .BoundaryDoesNotEvaluatePersistence to !validatesPersistence,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationCheck
                .ProviderOperationKatExecutorAbsent to !providerOperationKatExecutorPresent,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationCheck
                .ProviderOperationKatRunnerAbsent to !providerOperationKatRunnerPresent,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationCheck
                .ProviderOperationExecutionAbsent to
                (
                    !providerOperationExecutionPresent &&
                        !providerOperationReachable &&
                        !canExecuteProviderOperations &&
                        !realProviderOperationExecutionPermitted
                    ),
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationCheck
                .CryptoExecutionAbsent to
                (
                    !cryptoExecutionPresent &&
                        !cryptoExecutionReachable &&
                        !canExecuteCrypto &&
                        !cryptoExecutionPermitted
                    ),
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationCheck
                .VaultPersistenceExecutionAbsent to
                (!persistenceReachable && !canUseForPersistence && !vaultPersistencePermitted),
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationCheck
                .ProviderSelectionAuthorizationAbsent to providerSelectionAuthorizationAbsent,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationCheck
                .ProductionAuthorizationAbsent to productionAuthorizationAbsent,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationCheck
                .ProviderOperationAuthorizationAbsent to providerOperationAuthorizationAbsent,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationCheck
                .KatExecutorAuthorizationAbsent to katExecutorAuthorizationAbsent,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationCheck
                .MainnetAuthorizationAbsent to mainnetAuthorizationAbsent,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationCheck
                .ProviderSelectionRemainsDisabledProviderOnly to providerSelectionRemainsDisabledProviderOnly,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationCheck
                .ProductionProviderSelectableRemainsFalse to productionProviderSelectableRemainsFalse,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationCheck
                .ProductionRuntimeSourceAbsenceSatisfied to productionRuntimeSourceAbsenceSatisfied,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationCheck
                .SafeOutputRedactionSatisfied to safeOutputRedactionSatisfied,
        )
        val allValidationChecksPassed =
            validationChecks.size ==
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationCheck
                    .entries
                    .size &&
                checkResults.size == validationChecks.size &&
                validationChecks.all { check -> checkResults[check] == true }

        return SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationReport(
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
            expectedSafeIdMatched = expectedSafeIdMatched,
            expectedFixtureIdMatched = expectedFixtureIdMatched,
            expectedVectorIdMatched = expectedVectorIdMatched,
            expectedCaseIdMatched = expectedCaseIdMatched,
            expectedProviderOperationMetadataKatIdMatched = expectedProviderOperationMetadataKatIdMatched,
            expectedProviderOperationNoopKatIdMatched = expectedProviderOperationNoopKatIdMatched,
            expectedProviderOperationNoopExecutionBoundaryIdMatched =
                expectedProviderOperationNoopExecutionBoundaryIdMatched,
            providerOperationNoopKatPassed = providerOperationNoopKatPassed,
            providerOperationNoopKatValidationPassed = providerOperationNoopKatValidationPassed,
            providerOperationNoopKatSuitePassed = providerOperationNoopKatSuitePassed,
            syntheticNoopResultPresent = syntheticNoopResultPresent,
            noopExecutionBoundaryModeled = noopExecutionBoundaryModeled,
            syntheticNoopEvaluationPermitted = syntheticNoopEvaluationPermitted,
            allValidationChecksPassed = allValidationChecksPassed,
            validationIsCommonTestOnly = validationIsCommonTestOnly,
            validationIsProductionAuthorization = false,
            validationIsProviderSelectionAuthorization = false,
            validationIsProviderOperationAuthorization = false,
            validationIsKatExecutorAuthorization = false,
            validationIsCryptoAuthorization = false,
            validationIsVaultPersistenceAuthorization = false,
            validationIsMainnetAuthorization = false,
            validatesSyntheticNoopOnly = validatesSyntheticNoopOnly,
            validatesProviderOperationExecution = validatesProviderOperationExecution,
            validatesCryptoOperation = validatesCryptoOperation,
            validatesVaultLifecycle = validatesVaultLifecycle,
            validatesPersistence = validatesPersistence,
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
            validationChecks = validationChecks,
            sourceSet =
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationSourceSet
                    .CommonTest,
            displayLabel =
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryValidationSafeLabel(
                    "no-op provider-operation execution-boundary validation evidence",
                ),
        )
    }
}
