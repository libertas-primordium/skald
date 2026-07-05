package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundarySafeLabel(
    val value: String,
) {
    override fun toString(): String =
        "RedactedTestOnlyProviderIdentityProviderOperationNoopExecutionBoundarySafeLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundarySourceSet {
    CommonTest,
}

enum class SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryState {
    SyntheticNoopEvaluationPermittedForCommonTestOnly,
    RealProviderOperationExecutionForbidden,
    CryptoExecutionForbidden,
    KatRunnerForbidden,
    KatExecutorForbidden,
    ProviderSelectionForbidden,
    RegistryFactoryDispatcherForbidden,
    VaultLifecycleForbidden,
    PersistenceForbidden,
    SyncForbidden,
    SigningBroadcastingForbidden,
    EndpointUiBackendBdkForbidden,
    MainnetForbidden,
}

data class SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundary(
    val markerCount: Int,
    val caseBindingCount: Int,
    val providerOperationMetadataKatCount: Int,
    val providerOperationMetadataKatValidationCount: Int,
    val providerOperationMetadataKatSuiteReportCount: Int,
    val providerOperationNoopKatAdmissionCount: Int,
    val providerOperationNoopKatCount: Int,
    val providerOperationNoopKatValidationCount: Int,
    val providerOperationNoopKatSuiteReportCount: Int,
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
    val boundaryIsCommonTestOnly: Boolean,
    val boundaryIsProductionAuthorization: Boolean,
    val boundaryIsProviderSelectionAuthorization: Boolean,
    val boundaryIsProviderOperationAuthorization: Boolean,
    val boundaryIsKatExecutorAuthorization: Boolean,
    val boundaryIsCryptoAuthorization: Boolean,
    val boundaryIsVaultPersistenceAuthorization: Boolean,
    val boundaryIsMainnetAuthorization: Boolean,
    val realProviderOperationExecutionPermitted: Boolean,
    val cryptoExecutionPermitted: Boolean,
    val katRunnerPermitted: Boolean,
    val katExecutorPermitted: Boolean,
    val vaultPersistencePermitted: Boolean,
    val mainnetPermitted: Boolean,
    val evaluatesSyntheticNoopOnly: Boolean,
    val evaluatesProviderOperationExecution: Boolean,
    val evaluatesCryptoOperation: Boolean,
    val evaluatesVaultLifecycle: Boolean,
    val evaluatesPersistence: Boolean,
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
    val boundaryStates:
        List<SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryState>,
    val sourceSet: SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundarySourceSet,
    val displayLabel: SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundarySafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundary(redactedMarkerId, redactedFixtureId, redactedVectorId, redactedCaseId, redactedProviderOperationMetadataKatId, redactedProviderOperationNoopKatId, redactedBoundaryId, commonTestOnly, syntheticNoopBoundaryOnly, nonAuthorizing)"
}

object SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryPolicy {
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

    fun currentProviderOperationNoopExecutionBoundary():
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundary {
        val marker = SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy.currentMarker()
        val capabilityMatrix =
            SkaldVaultV1TestOnlyProviderIdentityCapabilityMatrixPolicy.currentCapabilityMatrix()
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
        val providerOperationNoopKatSuiteReport =
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatSuiteReportPolicy
                .currentProviderOperationNoopKatSuiteReport()
        val boundaryStates =
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryState.entries.toList()

        val markerCount =
            if (
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
        val caseBindingCount = providerOperationNoopKatSuiteReport.caseBindingCount
        val providerOperationMetadataKatCount =
            providerOperationNoopKatSuiteReport.providerOperationMetadataKatCount
        val providerOperationMetadataKatValidationCount =
            providerOperationNoopKatSuiteReport.providerOperationMetadataKatValidationCount
        val providerOperationMetadataKatSuiteReportCount =
            providerOperationNoopKatSuiteReport.providerOperationMetadataKatSuiteReportCount
        val providerOperationNoopKatAdmissionCount =
            providerOperationNoopKatSuiteReport.providerOperationNoopKatAdmissionCount
        val providerOperationNoopKatCount = providerOperationNoopKatSuiteReport.providerOperationNoopKatCount
        val providerOperationNoopKatValidationCount =
            providerOperationNoopKatSuiteReport.providerOperationNoopKatValidationCount
        val providerOperationNoopKatSuiteReportCount =
            if (
                providerOperationNoopKatSuiteReport.suiteReportGenerated &&
                providerOperationNoopKatSuiteReport.providerOperationNoopKatSuitePassed
            ) {
                1
            } else {
                0
            }

        val expectedSafeIdMatched =
            markerCount == 1 &&
                providerOperationNoopKatSuiteReport.expectedSafeIdMatched &&
                providerOperationNoopKatValidation.expectedSafeIdMatched &&
                providerOperationNoopKat.expectedSafeIdMatched &&
                providerOperationNoopKatAdmission.expectedSafeIdMatched &&
                providerOperationMetadataKatSuiteReport.expectedSafeIdMatched &&
                providerOperationMetadataKatValidation.expectedSafeIdMatched
        val expectedFixtureIdMatched =
            providerOperationNoopKatSuiteReport.expectedFixtureIdMatched &&
                providerOperationNoopKatValidation.expectedFixtureIdMatched &&
                providerOperationNoopKat.expectedFixtureIdMatched &&
                providerOperationNoopKatAdmission.expectedFixtureIdMatched &&
                providerOperationMetadataKatSuiteReport.expectedFixtureIdMatched &&
                providerOperationMetadataKatValidation.expectedFixtureIdMatched &&
                EXPECTED_FIXTURE_ID ==
                "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata"
        val expectedVectorIdMatched =
            providerOperationNoopKatSuiteReport.expectedVectorIdMatched &&
                providerOperationNoopKatValidation.expectedVectorIdMatched &&
                providerOperationNoopKat.expectedVectorIdMatched &&
                providerOperationNoopKatAdmission.expectedVectorIdMatched &&
                providerOperationMetadataKatSuiteReport.expectedVectorIdMatched &&
                providerOperationMetadataKatValidation.expectedVectorIdMatched &&
                EXPECTED_VECTOR_ID ==
                "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata"
        val expectedCaseIdMatched =
            providerOperationNoopKatSuiteReport.expectedCaseIdMatched &&
                providerOperationNoopKatValidation.expectedCaseIdMatched &&
                providerOperationNoopKat.expectedCaseIdMatched &&
                providerOperationNoopKatAdmission.expectedCaseIdMatched &&
                providerOperationMetadataKatSuiteReport.expectedCaseIdMatched &&
                providerOperationMetadataKatValidation.expectedCaseIdMatched &&
                EXPECTED_CASE_ID ==
                "skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata"
        val expectedProviderOperationMetadataKatIdMatched =
            providerOperationNoopKatSuiteReport.expectedProviderOperationMetadataKatIdMatched &&
                providerOperationNoopKatValidation.expectedProviderOperationMetadataKatIdMatched &&
                providerOperationNoopKat.expectedProviderOperationMetadataKatIdMatched &&
                providerOperationNoopKatAdmission.expectedProviderOperationMetadataKatIdMatched &&
                providerOperationMetadataKatSuiteReport.expectedProviderOperationMetadataKatIdMatched &&
                providerOperationMetadataKatValidation.expectedProviderOperationMetadataKatIdMatched &&
                EXPECTED_PROVIDER_OPERATION_METADATA_KAT_ID ==
                "skald-test-only-provider-identity-provider-operation-metadata-kat-v1-inert-identity"
        val expectedProviderOperationNoopKatIdMatched =
            providerOperationNoopKatSuiteReport.expectedProviderOperationNoopKatIdMatched &&
                providerOperationNoopKatValidation.expectedProviderOperationNoopKatIdMatched &&
                providerOperationNoopKat.expectedProviderOperationNoopKatIdMatched &&
                providerOperationNoopKatAdmission.expectedProviderOperationNoopKatIdMatched &&
                EXPECTED_PROVIDER_OPERATION_NOOP_KAT_ID ==
                "skald-test-only-provider-identity-provider-operation-noop-kat-v1-inert-identity"
        val expectedProviderOperationNoopExecutionBoundaryIdMatched =
            EXPECTED_PROVIDER_OPERATION_NOOP_EXECUTION_BOUNDARY_ID ==
                "skald-test-only-provider-identity-provider-operation-noop-execution-boundary-v1-inert-identity"

        val providerOperationNoopKatPassed =
            providerOperationNoopKatSuiteReport.providerOperationNoopKatPassed &&
                providerOperationNoopKatValidation.providerOperationNoopKatPassed &&
                providerOperationNoopKat.providerOperationNoopKatPassed
        val providerOperationNoopKatValidationPassed =
            providerOperationNoopKatSuiteReport.providerOperationNoopKatValidationPassed &&
                providerOperationNoopKatValidation.allValidationChecksPassed
        val providerOperationNoopKatSuitePassed =
            providerOperationNoopKatSuiteReport.providerOperationNoopKatSuitePassed
        val syntheticNoopResultPresent =
            providerOperationNoopKatSuiteReport.syntheticNoopResultPresent &&
                providerOperationNoopKatValidation.syntheticNoopResultPresent &&
                providerOperationNoopKat.syntheticNoopResultPresent

        val providerOperationKatExecutorPresent =
            providerOperationNoopKatSuiteReport.providerOperationKatExecutorPresent ||
                providerOperationNoopKatValidation.providerOperationKatExecutorPresent ||
                providerOperationNoopKat.providerOperationKatExecutorPresent ||
                providerOperationNoopKatAdmission.currentKatExecutorPresent ||
                providerOperationMetadataKatSuiteReport.providerOperationKatExecutorPresent ||
                providerOperationMetadataKatValidation.providerOperationKatExecutorPresent
        val providerOperationKatRunnerPresent =
            providerOperationNoopKatSuiteReport.providerOperationKatRunnerPresent ||
                providerOperationNoopKatValidation.providerOperationKatRunnerPresent ||
                providerOperationNoopKat.providerOperationKatRunnerPresent ||
                providerOperationNoopKatAdmission.currentKatRunnerPresent ||
                providerOperationMetadataKatSuiteReport.providerOperationKatRunnerPresent ||
                providerOperationMetadataKatValidation.providerOperationKatRunnerPresent
        val providerOperationExecutionPresent =
            providerOperationNoopKatSuiteReport.providerOperationExecutionPresent ||
                providerOperationNoopKatValidation.providerOperationExecutionPresent ||
                providerOperationNoopKat.providerOperationExecutionPresent ||
                providerOperationNoopKatAdmission.currentProviderOperationExecutionPresent ||
                providerOperationNoopKatAdmission.currentNoopProviderOperationExecutionPresent ||
                providerOperationMetadataKatSuiteReport.providerOperationExecutionPresent ||
                providerOperationMetadataKatValidation.providerOperationExecutionPresent
        val cryptoExecutionPresent =
            providerOperationNoopKatSuiteReport.cryptoExecutionPresent ||
                providerOperationNoopKatValidation.cryptoExecutionPresent ||
                providerOperationNoopKat.cryptoExecutionPresent ||
                providerOperationNoopKatAdmission.currentCryptoExecutionPresent ||
                providerOperationMetadataKatSuiteReport.cryptoExecutionPresent ||
                providerOperationMetadataKatValidation.cryptoExecutionPresent
        val rawKatMaterialPresent =
            providerOperationNoopKatSuiteReport.rawKatMaterialPresent ||
                providerOperationNoopKatValidation.rawKatMaterialPresent ||
                providerOperationNoopKat.rawKatMaterialPresent ||
                providerOperationNoopKatAdmission.rawKatMaterialPresent ||
                providerOperationMetadataKatSuiteReport.rawKatMaterialPresent ||
                providerOperationMetadataKatValidation.rawKatMaterialPresent
        val rawVectorBytesPresent =
            providerOperationNoopKatSuiteReport.rawVectorBytesPresent ||
                providerOperationNoopKatValidation.rawVectorBytesPresent ||
                providerOperationNoopKat.rawVectorBytesPresent ||
                providerOperationNoopKatAdmission.rawVectorBytesPresent ||
                providerOperationMetadataKatSuiteReport.rawVectorBytesPresent ||
                providerOperationMetadataKatValidation.rawVectorBytesPresent
        val rawVectorHexPresent =
            providerOperationNoopKatSuiteReport.rawVectorHexPresent ||
                providerOperationNoopKatValidation.rawVectorHexPresent ||
                providerOperationNoopKat.rawVectorHexPresent ||
                providerOperationNoopKatAdmission.rawVectorHexPresent ||
                providerOperationMetadataKatSuiteReport.rawVectorHexPresent ||
                providerOperationMetadataKatValidation.rawVectorHexPresent
        val publicVectorBytesPresent =
            providerOperationNoopKatSuiteReport.publicVectorBytesPresent ||
                providerOperationNoopKatValidation.publicVectorBytesPresent ||
                providerOperationNoopKat.publicVectorBytesPresent ||
                providerOperationNoopKatAdmission.publicVectorBytesPresent ||
                providerOperationMetadataKatSuiteReport.publicVectorBytesPresent ||
                providerOperationMetadataKatValidation.publicVectorBytesPresent
        val publicVectorHexPresent =
            providerOperationNoopKatSuiteReport.publicVectorHexPresent ||
                providerOperationNoopKatValidation.publicVectorHexPresent ||
                providerOperationNoopKat.publicVectorHexPresent ||
                providerOperationNoopKatAdmission.publicVectorHexPresent ||
                providerOperationMetadataKatSuiteReport.publicVectorHexPresent ||
                providerOperationMetadataKatValidation.publicVectorHexPresent

        val runtimeSelectable =
            providerOperationNoopKatSuiteReport.runtimeSelectable ||
                providerOperationNoopKatValidation.runtimeSelectable ||
                providerOperationNoopKat.runtimeSelectable ||
                providerOperationNoopKatAdmission.runtimeSelectable ||
                providerOperationMetadataKatSuiteReport.runtimeSelectable ||
                providerOperationMetadataKatValidation.runtimeSelectable ||
                capabilityMatrix.runtimeSelectable
        val registrySelectable =
            providerOperationNoopKatSuiteReport.registrySelectable ||
                providerOperationNoopKatValidation.registrySelectable ||
                providerOperationNoopKat.registrySelectable ||
                providerOperationNoopKatAdmission.registrySelectable ||
                providerOperationMetadataKatSuiteReport.registrySelectable ||
                providerOperationMetadataKatValidation.registrySelectable ||
                capabilityMatrix.registrySelectable
        val factoryReachable =
            providerOperationNoopKatSuiteReport.factoryReachable ||
                providerOperationNoopKatValidation.factoryReachable ||
                providerOperationNoopKat.factoryReachable ||
                providerOperationNoopKatAdmission.factoryReachable ||
                providerOperationMetadataKatSuiteReport.factoryReachable ||
                providerOperationMetadataKatValidation.factoryReachable ||
                capabilityMatrix.factoryReachable
        val dispatcherReachable =
            providerOperationNoopKatSuiteReport.dispatcherReachable ||
                providerOperationNoopKatValidation.dispatcherReachable ||
                providerOperationNoopKat.dispatcherReachable ||
                providerOperationNoopKatAdmission.dispatcherReachable ||
                providerOperationMetadataKatSuiteReport.dispatcherReachable ||
                providerOperationMetadataKatValidation.dispatcherReachable ||
                capabilityMatrix.dispatcherReachable
        val executorTargetable =
            providerOperationNoopKatSuiteReport.executorTargetable ||
                providerOperationNoopKatValidation.executorTargetable ||
                providerOperationNoopKat.executorTargetable ||
                providerOperationNoopKatAdmission.executorTargetable ||
                providerOperationMetadataKatSuiteReport.executorTargetable ||
                providerOperationMetadataKatValidation.executorTargetable ||
                capabilityMatrix.executorTargetable
        val providerKatExecutorReachable =
            providerOperationNoopKatSuiteReport.providerKatExecutorReachable ||
                providerOperationNoopKatValidation.providerKatExecutorReachable ||
                providerOperationNoopKat.providerKatExecutorReachable ||
                providerOperationNoopKatAdmission.providerKatExecutorReachable ||
                providerOperationMetadataKatSuiteReport.providerKatExecutorReachable ||
                providerOperationMetadataKatValidation.providerKatExecutorReachable ||
                capabilityMatrix.providerKatExecutorReachable
        val providerOperationReachable =
            providerOperationNoopKatSuiteReport.providerOperationReachable ||
                providerOperationNoopKatValidation.providerOperationReachable ||
                providerOperationNoopKat.providerOperationReachable ||
                providerOperationNoopKatAdmission.providerOperationReachable ||
                providerOperationMetadataKatSuiteReport.providerOperationReachable ||
                providerOperationMetadataKatValidation.providerOperationReachable ||
                capabilityMatrix.providerOperationReachable ||
                providerOperationExecutionPresent
        val cryptoExecutionReachable =
            providerOperationNoopKatSuiteReport.cryptoExecutionReachable ||
                providerOperationNoopKatValidation.cryptoExecutionReachable ||
                providerOperationNoopKat.cryptoExecutionReachable ||
                providerOperationNoopKatAdmission.cryptoExecutionReachable ||
                providerOperationMetadataKatSuiteReport.cryptoExecutionReachable ||
                providerOperationMetadataKatValidation.cryptoExecutionReachable ||
                capabilityMatrix.cryptoExecutionReachable ||
                cryptoExecutionPresent
        val vaultLifecycleReachable =
            providerOperationNoopKatSuiteReport.vaultLifecycleReachable ||
                providerOperationNoopKatValidation.vaultLifecycleReachable ||
                providerOperationNoopKat.vaultLifecycleReachable ||
                providerOperationNoopKatAdmission.vaultLifecycleReachable ||
                providerOperationMetadataKatSuiteReport.vaultLifecycleReachable ||
                providerOperationMetadataKatValidation.vaultLifecycleReachable ||
                capabilityMatrix.vaultLifecycleReachable
        val persistenceReachable =
            providerOperationNoopKatSuiteReport.persistenceReachable ||
                providerOperationNoopKatValidation.persistenceReachable ||
                providerOperationNoopKat.persistenceReachable ||
                providerOperationNoopKatAdmission.persistenceReachable ||
                providerOperationMetadataKatSuiteReport.persistenceReachable ||
                providerOperationMetadataKatValidation.persistenceReachable ||
                capabilityMatrix.persistenceReachable
        val productionSyncReachable =
            providerOperationNoopKatSuiteReport.productionSyncReachable ||
                providerOperationNoopKatValidation.productionSyncReachable ||
                providerOperationNoopKat.productionSyncReachable ||
                providerOperationNoopKatAdmission.productionSyncReachable ||
                providerOperationMetadataKatSuiteReport.productionSyncReachable ||
                providerOperationMetadataKatValidation.productionSyncReachable ||
                capabilityMatrix.productionSyncReachable
        val backendClientReachable =
            providerOperationNoopKatSuiteReport.backendClientReachable ||
                providerOperationNoopKatValidation.backendClientReachable ||
                providerOperationNoopKat.backendClientReachable ||
                providerOperationNoopKatAdmission.backendClientReachable ||
                providerOperationMetadataKatSuiteReport.backendClientReachable ||
                providerOperationMetadataKatValidation.backendClientReachable ||
                capabilityMatrix.backendClientReachable
        val bdkWalletStateReachable =
            providerOperationNoopKatSuiteReport.bdkWalletStateReachable ||
                providerOperationNoopKatValidation.bdkWalletStateReachable ||
                providerOperationNoopKat.bdkWalletStateReachable ||
                providerOperationNoopKatAdmission.bdkWalletStateReachable ||
                providerOperationMetadataKatSuiteReport.bdkWalletStateReachable ||
                providerOperationMetadataKatValidation.bdkWalletStateReachable ||
                capabilityMatrix.bdkWalletStateReachable
        val settingsCodecReachable =
            providerOperationNoopKatSuiteReport.settingsCodecReachable ||
                providerOperationNoopKatValidation.settingsCodecReachable ||
                providerOperationNoopKat.settingsCodecReachable ||
                providerOperationNoopKatAdmission.settingsCodecReachable ||
                providerOperationMetadataKatSuiteReport.settingsCodecReachable ||
                providerOperationMetadataKatValidation.settingsCodecReachable ||
                capabilityMatrix.settingsCodecReachable
        val uiSurfaceReachable =
            providerOperationNoopKatSuiteReport.uiSurfaceReachable ||
                providerOperationNoopKatValidation.uiSurfaceReachable ||
                providerOperationNoopKat.uiSurfaceReachable ||
                providerOperationNoopKatAdmission.uiSurfaceReachable ||
                providerOperationMetadataKatSuiteReport.uiSurfaceReachable ||
                providerOperationMetadataKatValidation.uiSurfaceReachable ||
                capabilityMatrix.uiSurfaceReachable
        val signingBroadcastingReachable =
            providerOperationNoopKatSuiteReport.signingBroadcastingReachable ||
                providerOperationNoopKatValidation.signingBroadcastingReachable ||
                providerOperationNoopKat.signingBroadcastingReachable ||
                providerOperationNoopKatAdmission.signingBroadcastingReachable ||
                providerOperationMetadataKatSuiteReport.signingBroadcastingReachable ||
                providerOperationMetadataKatValidation.signingBroadcastingReachable ||
                capabilityMatrix.signingBroadcastingReachable
        val publicEndpointReachable =
            providerOperationNoopKatSuiteReport.publicEndpointReachable ||
                providerOperationNoopKatValidation.publicEndpointReachable ||
                providerOperationNoopKat.publicEndpointReachable ||
                providerOperationNoopKatAdmission.publicEndpointReachable ||
                providerOperationMetadataKatSuiteReport.publicEndpointReachable ||
                providerOperationMetadataKatValidation.publicEndpointReachable ||
                capabilityMatrix.publicEndpointReachable
        val mainnetReachable =
            providerOperationNoopKatSuiteReport.mainnetReachable ||
                providerOperationNoopKatValidation.mainnetReachable ||
                providerOperationNoopKat.mainnetReachable ||
                providerOperationNoopKatAdmission.mainnetReachable ||
                providerOperationMetadataKatSuiteReport.mainnetReachable ||
                providerOperationMetadataKatValidation.mainnetReachable ||
                capabilityMatrix.mainnetReachable
        val implementsVaultCryptoProvider =
            providerOperationNoopKatSuiteReport.implementsVaultCryptoProvider ||
                providerOperationNoopKatValidation.implementsVaultCryptoProvider ||
                providerOperationNoopKat.implementsVaultCryptoProvider ||
                providerOperationNoopKatAdmission.implementsVaultCryptoProvider ||
                providerOperationMetadataKatSuiteReport.implementsVaultCryptoProvider ||
                providerOperationMetadataKatValidation.implementsVaultCryptoProvider ||
                capabilityMatrix.implementsVaultCryptoProvider
        val containsVaultCryptoProvider =
            providerOperationNoopKatSuiteReport.containsVaultCryptoProvider ||
                providerOperationNoopKatValidation.containsVaultCryptoProvider ||
                providerOperationNoopKat.containsVaultCryptoProvider ||
                providerOperationNoopKatAdmission.containsVaultCryptoProvider ||
                providerOperationMetadataKatSuiteReport.containsVaultCryptoProvider ||
                providerOperationMetadataKatValidation.containsVaultCryptoProvider ||
                capabilityMatrix.containsVaultCryptoProvider
        val canExecuteProviderOperations =
            providerOperationNoopKatSuiteReport.canExecuteProviderOperations ||
                providerOperationNoopKatValidation.canExecuteProviderOperations ||
                providerOperationNoopKat.canExecuteProviderOperations ||
                providerOperationNoopKatAdmission.canExecuteProviderOperations ||
                providerOperationMetadataKatSuiteReport.canExecuteProviderOperations ||
                providerOperationMetadataKatValidation.canExecuteProviderOperations ||
                capabilityMatrix.canExecuteProviderOperations
        val canExecuteCrypto =
            providerOperationNoopKatSuiteReport.canExecuteCrypto ||
                providerOperationNoopKatValidation.canExecuteCrypto ||
                providerOperationNoopKat.canExecuteCrypto ||
                providerOperationNoopKatAdmission.canExecuteCrypto ||
                providerOperationMetadataKatSuiteReport.canExecuteCrypto ||
                providerOperationMetadataKatValidation.canExecuteCrypto ||
                capabilityMatrix.canExecuteCrypto
        val canUseForVaultLifecycle =
            providerOperationNoopKatSuiteReport.canUseForVaultLifecycle ||
                providerOperationNoopKatValidation.canUseForVaultLifecycle ||
                providerOperationNoopKat.canUseForVaultLifecycle ||
                providerOperationNoopKatAdmission.canUseForVaultLifecycle ||
                providerOperationMetadataKatSuiteReport.canUseForVaultLifecycle ||
                providerOperationMetadataKatValidation.canUseForVaultLifecycle ||
                capabilityMatrix.canUseForVaultLifecycle
        val canUseForPersistence =
            providerOperationNoopKatSuiteReport.canUseForPersistence ||
                providerOperationNoopKatValidation.canUseForPersistence ||
                providerOperationNoopKat.canUseForPersistence ||
                providerOperationNoopKatAdmission.canUseForPersistence ||
                providerOperationMetadataKatSuiteReport.canUseForPersistence ||
                providerOperationMetadataKatValidation.canUseForPersistence ||
                capabilityMatrix.canUseForPersistence
        val canUseForSync =
            providerOperationNoopKatSuiteReport.canUseForSync ||
                providerOperationNoopKatValidation.canUseForSync ||
                providerOperationNoopKat.canUseForSync ||
                providerOperationNoopKatAdmission.canUseForSync ||
                providerOperationMetadataKatSuiteReport.canUseForSync ||
                providerOperationMetadataKatValidation.canUseForSync ||
                capabilityMatrix.canUseForSync
        val canUseForSigning =
            providerOperationNoopKatSuiteReport.canUseForSigning ||
                providerOperationNoopKatValidation.canUseForSigning ||
                providerOperationNoopKat.canUseForSigning ||
                providerOperationNoopKatAdmission.canUseForSigning ||
                providerOperationMetadataKatSuiteReport.canUseForSigning ||
                providerOperationMetadataKatValidation.canUseForSigning ||
                capabilityMatrix.canUseForSigning
        val canUseForBroadcasting =
            providerOperationNoopKatSuiteReport.canUseForBroadcasting ||
                providerOperationNoopKatValidation.canUseForBroadcasting ||
                providerOperationNoopKat.canUseForBroadcasting ||
                providerOperationNoopKatAdmission.canUseForBroadcasting ||
                providerOperationMetadataKatSuiteReport.canUseForBroadcasting ||
                providerOperationMetadataKatValidation.canUseForBroadcasting ||
                capabilityMatrix.canUseForBroadcasting
        val canUseForMainnet =
            providerOperationNoopKatSuiteReport.canUseForMainnet ||
                providerOperationNoopKatValidation.canUseForMainnet ||
                providerOperationNoopKat.canUseForMainnet ||
                providerOperationNoopKatAdmission.canUseForMainnet ||
                providerOperationMetadataKatSuiteReport.canUseForMainnet ||
                providerOperationMetadataKatValidation.canUseForMainnet ||
                capabilityMatrix.canUseForMainnet
        val productionProviderSelectable =
            providerOperationNoopKatSuiteReport.productionProviderSelectable ||
                providerOperationNoopKatValidation.productionProviderSelectable ||
                providerOperationNoopKat.productionProviderSelectable ||
                providerOperationNoopKatAdmission.productionProviderSelectable ||
                providerOperationMetadataKatSuiteReport.productionProviderSelectable ||
                providerOperationMetadataKatValidation.productionProviderSelectable ||
                capabilityMatrix.productionProviderSelectable ||
                marker.productionProviderSelectable

        val boundaryIsCommonTestOnly =
            providerOperationNoopKatSuiteReport.suiteIsCommonTestOnly &&
                providerOperationNoopKatValidation.validationIsCommonTestOnly &&
                providerOperationNoopKat.noopKatIsCommonTestOnly &&
                providerOperationNoopKatAdmission.admissionIsCommonTestOnly &&
                providerOperationMetadataKatSuiteReport.suiteIsCommonTestOnly &&
                providerOperationMetadataKatValidation.validationIsCommonTestOnly &&
                capabilityMatrix.matrixIsCommonTestOnly &&
                marker.sourceSet == SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet.CommonTest

        val realProviderOperationExecutionPermitted = false
        val cryptoExecutionPermitted = false
        val katRunnerPermitted = false
        val katExecutorPermitted = false
        val vaultPersistencePermitted = false
        val mainnetPermitted = false

        val evaluatesSyntheticNoopOnly =
            providerOperationNoopKatSuiteReport.suiteReportsSyntheticNoopOnly &&
                providerOperationNoopKatValidation.validatesSyntheticNoopOnly &&
                providerOperationNoopKat.evaluatesSyntheticNoopOnly &&
                providerOperationNoopKatSuitePassed &&
                syntheticNoopResultPresent
        val evaluatesProviderOperationExecution =
            providerOperationNoopKatSuiteReport.suiteReportsProviderOperationExecution ||
                providerOperationNoopKatValidation.validatesProviderOperationExecution ||
                providerOperationNoopKat.evaluatesProviderOperationExecution ||
                providerOperationExecutionPresent ||
                realProviderOperationExecutionPermitted
        val evaluatesCryptoOperation =
            providerOperationNoopKatSuiteReport.suiteReportsCryptoOperation ||
                providerOperationNoopKatValidation.validatesCryptoOperation ||
                providerOperationNoopKat.evaluatesCryptoOperation ||
                cryptoExecutionPresent ||
                cryptoExecutionPermitted
        val evaluatesVaultLifecycle =
            providerOperationNoopKatSuiteReport.suiteReportsVaultLifecycle ||
                providerOperationNoopKatValidation.validatesVaultLifecycle ||
                providerOperationNoopKat.evaluatesVaultLifecycle ||
                vaultLifecycleReachable
        val evaluatesPersistence =
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
                providerOperationMetadataKatValidation.toString(),
                providerOperationMetadataKatValidation.displayLabel.toString(),
                providerOperationMetadataKatSuiteReport.toString(),
                providerOperationNoopKatAdmission.toString(),
                providerOperationNoopKatAdmission.displayLabel.toString(),
                providerOperationNoopKat.toString(),
                providerOperationNoopKat.displayLabel.toString(),
                providerOperationNoopKatValidation.toString(),
                providerOperationNoopKatValidation.displayLabel.toString(),
                providerOperationNoopKatSuiteReport.toString(),
                providerOperationNoopKatSuiteReport.displayLabel.toString(),
            ).none { output ->
                EXPECTED_SAFE_ID in output ||
                    EXPECTED_FIXTURE_ID in output ||
                    EXPECTED_VECTOR_ID in output ||
                    EXPECTED_CASE_ID in output ||
                    EXPECTED_PROVIDER_OPERATION_METADATA_KAT_ID in output ||
                    EXPECTED_PROVIDER_OPERATION_NOOP_KAT_ID in output ||
                    EXPECTED_PROVIDER_OPERATION_NOOP_EXECUTION_BOUNDARY_ID in output
            }
        val noopExecutionBoundaryModeled =
            boundaryIsCommonTestOnly &&
                boundaryStates.size ==
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryState.entries.size &&
                markerCount == 1 &&
                caseBindingCount == 1 &&
                providerOperationMetadataKatCount == 1 &&
                providerOperationMetadataKatValidationCount == 1 &&
                providerOperationMetadataKatSuiteReportCount == 1 &&
                providerOperationNoopKatAdmissionCount == 1 &&
                providerOperationNoopKatCount == 1 &&
                providerOperationNoopKatValidationCount == 1 &&
                providerOperationNoopKatSuiteReportCount == 1 &&
                expectedSafeIdMatched &&
                expectedFixtureIdMatched &&
                expectedVectorIdMatched &&
                expectedCaseIdMatched &&
                expectedProviderOperationMetadataKatIdMatched &&
                expectedProviderOperationNoopKatIdMatched &&
                expectedProviderOperationNoopExecutionBoundaryIdMatched &&
                providerOperationNoopKatPassed &&
                providerOperationNoopKatValidationPassed &&
                providerOperationNoopKatSuitePassed &&
                syntheticNoopResultPresent &&
                evaluatesSyntheticNoopOnly &&
                safeOutputRedactionSatisfied &&
                !evaluatesProviderOperationExecution &&
                !evaluatesCryptoOperation &&
                !evaluatesVaultLifecycle &&
                !evaluatesPersistence &&
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
                !productionProviderSelectable &&
                !realProviderOperationExecutionPermitted &&
                !cryptoExecutionPermitted &&
                !katRunnerPermitted &&
                !katExecutorPermitted &&
                !vaultPersistencePermitted &&
                !mainnetPermitted
        val syntheticNoopEvaluationPermitted =
            noopExecutionBoundaryModeled &&
                providerOperationNoopKatSuitePassed &&
                syntheticNoopResultPresent &&
                !realProviderOperationExecutionPermitted &&
                !cryptoExecutionPermitted &&
                !katRunnerPermitted &&
                !katExecutorPermitted

        return SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundary(
            markerCount = markerCount,
            caseBindingCount = caseBindingCount,
            providerOperationMetadataKatCount = providerOperationMetadataKatCount,
            providerOperationMetadataKatValidationCount = providerOperationMetadataKatValidationCount,
            providerOperationMetadataKatSuiteReportCount = providerOperationMetadataKatSuiteReportCount,
            providerOperationNoopKatAdmissionCount = providerOperationNoopKatAdmissionCount,
            providerOperationNoopKatCount = providerOperationNoopKatCount,
            providerOperationNoopKatValidationCount = providerOperationNoopKatValidationCount,
            providerOperationNoopKatSuiteReportCount = providerOperationNoopKatSuiteReportCount,
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
            boundaryIsCommonTestOnly = boundaryIsCommonTestOnly,
            boundaryIsProductionAuthorization = false,
            boundaryIsProviderSelectionAuthorization = false,
            boundaryIsProviderOperationAuthorization = false,
            boundaryIsKatExecutorAuthorization = false,
            boundaryIsCryptoAuthorization = false,
            boundaryIsVaultPersistenceAuthorization = false,
            boundaryIsMainnetAuthorization = false,
            realProviderOperationExecutionPermitted = realProviderOperationExecutionPermitted,
            cryptoExecutionPermitted = cryptoExecutionPermitted,
            katRunnerPermitted = katRunnerPermitted,
            katExecutorPermitted = katExecutorPermitted,
            vaultPersistencePermitted = vaultPersistencePermitted,
            mainnetPermitted = mainnetPermitted,
            evaluatesSyntheticNoopOnly = evaluatesSyntheticNoopOnly,
            evaluatesProviderOperationExecution = evaluatesProviderOperationExecution,
            evaluatesCryptoOperation = evaluatesCryptoOperation,
            evaluatesVaultLifecycle = evaluatesVaultLifecycle,
            evaluatesPersistence = evaluatesPersistence,
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
            boundaryStates = boundaryStates,
            sourceSet =
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundarySourceSet.CommonTest,
            displayLabel =
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundarySafeLabel(
                    "no-op provider-operation execution boundary evidence",
                ),
        )
    }
}
