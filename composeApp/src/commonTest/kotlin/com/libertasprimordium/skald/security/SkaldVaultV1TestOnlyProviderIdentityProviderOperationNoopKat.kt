package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatSafeLabel(
    val value: String,
) {
    override fun toString(): String =
        "RedactedTestOnlyProviderIdentityProviderOperationNoopKatSafeLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatSourceSet {
    CommonTest,
}

enum class SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatResultCategory {
    AdmissionCriteriaSatisfiedForFutureOnly,
    MetadataSuiteSatisfied,
    SyntheticNoopResultModeled,
    ProviderOperationExecutionStillAbsent,
    CryptoExecutionStillAbsent,
    KatRunnerStillAbsent,
    KatExecutorStillAbsent,
    ProviderSelectionStillDisabledOnly,
    ProductionProviderStillNotSelectable,
    RuntimeReachabilityStillAbsent,
    MainnetStillDisabled,
}

data class SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatResult(
    val markerCount: Int,
    val caseBindingCount: Int,
    val executableMetadataKatSuiteReportCount: Int,
    val providerOperationKatAdmissionCount: Int,
    val providerOperationMetadataKatCount: Int,
    val providerOperationMetadataKatValidationCount: Int,
    val providerOperationMetadataKatSuiteReportCount: Int,
    val providerOperationNoopKatAdmissionCount: Int,
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
    val syntheticNoopResultPresent: Boolean,
    val noopKatIsCommonTestOnly: Boolean,
    val noopKatIsProductionAuthorization: Boolean,
    val noopKatIsProviderSelectionAuthorization: Boolean,
    val noopKatIsProviderOperationAuthorization: Boolean,
    val noopKatIsKatExecutorAuthorization: Boolean,
    val noopKatIsCryptoAuthorization: Boolean,
    val noopKatIsVaultPersistenceAuthorization: Boolean,
    val noopKatIsMainnetAuthorization: Boolean,
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
    val resultCategories:
        List<SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatResultCategory>,
    val sourceSet: SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatSourceSet,
    val displayLabel: SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatSafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatResult(redactedMarkerId, redactedFixtureId, redactedVectorId, redactedCaseId, redactedProviderOperationMetadataKatId, redactedProviderOperationNoopKatId, commonTestOnly, syntheticNoopOnly, nonAuthorizing)"
}

object SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatPolicy {
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

    fun evaluateCurrentProviderOperationNoopKat():
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatResult {
        val marker = SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy.currentMarker()
        val capabilityMatrix =
            SkaldVaultV1TestOnlyProviderIdentityCapabilityMatrixPolicy.currentCapabilityMatrix()
        val caseBinding =
            SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingPolicy.currentKatCaseBinding()
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
        val resultCategories =
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatResultCategory.entries.toList()

        val markerCount =
            if (
                providerOperationNoopKatAdmission.markerCount == 1 &&
                providerOperationMetadataKatSuiteReport.markerCount == 1 &&
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
        val caseBindingCount = providerOperationNoopKatAdmission.caseBindingCount
        val executableMetadataKatSuiteReportCount =
            providerOperationNoopKatAdmission.executableMetadataKatSuiteReportCount
        val providerOperationKatAdmissionCount =
            providerOperationNoopKatAdmission.providerOperationKatAdmissionCount
        val providerOperationMetadataKatCount =
            providerOperationNoopKatAdmission.providerOperationMetadataKatCount
        val providerOperationMetadataKatValidationCount =
            providerOperationNoopKatAdmission.providerOperationMetadataKatValidationCount
        val providerOperationMetadataKatSuiteReportCount =
            providerOperationNoopKatAdmission.providerOperationMetadataKatSuiteReportCount
        val providerOperationNoopKatAdmissionCount =
            if (
                providerOperationNoopKatAdmission.futureNoopProviderOperationKatCriteriaModeled &&
                !providerOperationNoopKatAdmission.futureNoopProviderOperationKatCriteriaAuthorizeCurrentExecution &&
                !providerOperationNoopKatAdmission.currentNoopProviderOperationKatPresent &&
                !providerOperationNoopKatAdmission.currentNoopProviderOperationExecutionPresent &&
                !providerOperationNoopKatAdmission.currentProviderOperationExecutionPresent &&
                !providerOperationNoopKatAdmission.currentCryptoExecutionPresent
            ) {
                1
            } else {
                0
            }

        val expectedSafeIdMatched =
            markerCount == 1 &&
                providerOperationNoopKatAdmission.expectedSafeIdMatched &&
                providerOperationMetadataKatSuiteReport.expectedSafeIdMatched &&
                providerOperationMetadataKatValidation.expectedSafeIdMatched &&
                providerOperationMetadataKat.expectedSafeIdMatched &&
                providerOperationKatAdmission.expectedSafeIdMatched &&
                executableMetadataKatSuiteReport.expectedSafeIdMatched
        val expectedFixtureIdMatched =
            providerOperationNoopKatAdmission.expectedFixtureIdMatched &&
                providerOperationMetadataKatSuiteReport.expectedFixtureIdMatched &&
                providerOperationMetadataKatValidation.expectedFixtureIdMatched &&
                providerOperationMetadataKat.expectedFixtureIdMatched &&
                providerOperationKatAdmission.expectedFixtureIdMatched &&
                executableMetadataKatSuiteReport.expectedFixtureIdMatched &&
                caseBinding.fixtureId.value == EXPECTED_FIXTURE_ID
        val expectedVectorIdMatched =
            providerOperationNoopKatAdmission.expectedVectorIdMatched &&
                providerOperationMetadataKatSuiteReport.expectedVectorIdMatched &&
                providerOperationMetadataKatValidation.expectedVectorIdMatched &&
                providerOperationMetadataKat.expectedVectorIdMatched &&
                providerOperationKatAdmission.expectedVectorIdMatched &&
                executableMetadataKatSuiteReport.expectedVectorIdMatched &&
                caseBinding.vectorId.value == EXPECTED_VECTOR_ID
        val expectedCaseIdMatched =
            providerOperationNoopKatAdmission.expectedCaseIdMatched &&
                providerOperationMetadataKatSuiteReport.expectedCaseIdMatched &&
                providerOperationMetadataKatValidation.expectedCaseIdMatched &&
                providerOperationMetadataKat.expectedCaseIdMatched &&
                providerOperationKatAdmission.expectedCaseIdMatched &&
                executableMetadataKatSuiteReport.expectedCaseIdMatched &&
                caseBinding.caseId.value == EXPECTED_CASE_ID
        val expectedProviderOperationMetadataKatIdMatched =
            providerOperationNoopKatAdmission.expectedProviderOperationMetadataKatIdMatched &&
                providerOperationMetadataKatSuiteReport.expectedProviderOperationMetadataKatIdMatched &&
                providerOperationMetadataKatValidation.expectedProviderOperationMetadataKatIdMatched &&
                providerOperationMetadataKat.expectedProviderOperationMetadataKatIdMatched &&
                EXPECTED_PROVIDER_OPERATION_METADATA_KAT_ID ==
                "skald-test-only-provider-identity-provider-operation-metadata-kat-v1-inert-identity"
        val expectedProviderOperationNoopKatIdMatched =
            providerOperationNoopKatAdmission.expectedProviderOperationNoopKatIdMatched &&
                EXPECTED_PROVIDER_OPERATION_NOOP_KAT_ID ==
                "skald-test-only-provider-identity-provider-operation-noop-kat-v1-inert-identity"

        val metadataKatSuitePassed =
            providerOperationNoopKatAdmission.metadataKatSuitePassed &&
                providerOperationMetadataKatSuiteReport.metadataKatSuitePassed &&
                providerOperationMetadataKatValidation.metadataKatSuitePassed &&
                providerOperationMetadataKat.metadataKatSuitePassed &&
                providerOperationKatAdmission.metadataKatSuitePassed &&
                executableMetadataKatSuiteReport.suitePassed
        val providerOperationMetadataKatPassed =
            providerOperationNoopKatAdmission.providerOperationMetadataKatPassed &&
                providerOperationMetadataKatSuiteReport.providerOperationMetadataKatPassed &&
                providerOperationMetadataKatValidation.providerOperationMetadataKatPassed &&
                providerOperationMetadataKat.providerOperationMetadataKatPassed
        val providerOperationMetadataKatValidationPassed =
            providerOperationNoopKatAdmission.providerOperationMetadataKatValidationPassed &&
                providerOperationMetadataKatSuiteReport.providerOperationMetadataKatValidationPassed &&
                providerOperationMetadataKatValidation.allValidationChecksPassed
        val providerOperationMetadataKatSuitePassed =
            providerOperationNoopKatAdmission.providerOperationMetadataKatSuitePassed &&
                providerOperationMetadataKatSuiteReport.providerOperationMetadataKatSuitePassed
        val providerOperationNoopKatAdmissionModeled =
            providerOperationNoopKatAdmission.futureNoopProviderOperationKatCriteriaModeled &&
                !providerOperationNoopKatAdmission.futureNoopProviderOperationKatCriteriaAuthorizeCurrentExecution &&
                providerOperationNoopKatAdmissionCount == 1

        val providerOperationKatExecutorPresent =
            providerOperationNoopKatAdmission.currentKatExecutorPresent ||
                providerOperationMetadataKatSuiteReport.providerOperationKatExecutorPresent ||
                providerOperationMetadataKatValidation.providerOperationKatExecutorPresent ||
                providerOperationMetadataKat.providerOperationKatExecutorPresent
        val providerOperationKatRunnerPresent =
            providerOperationNoopKatAdmission.currentKatRunnerPresent ||
                providerOperationMetadataKatSuiteReport.providerOperationKatRunnerPresent ||
                providerOperationMetadataKatValidation.providerOperationKatRunnerPresent ||
                providerOperationMetadataKat.providerOperationKatRunnerPresent
        val providerOperationExecutionPresent =
            providerOperationNoopKatAdmission.currentProviderOperationExecutionPresent ||
                providerOperationNoopKatAdmission.currentNoopProviderOperationExecutionPresent ||
                providerOperationMetadataKatSuiteReport.providerOperationExecutionPresent ||
                providerOperationMetadataKatValidation.providerOperationExecutionPresent ||
                providerOperationMetadataKat.providerOperationExecutionPresent
        val cryptoExecutionPresent =
            providerOperationNoopKatAdmission.currentCryptoExecutionPresent ||
                providerOperationMetadataKatSuiteReport.cryptoExecutionPresent ||
                providerOperationMetadataKatValidation.cryptoExecutionPresent ||
                providerOperationMetadataKat.cryptoExecutionPresent

        val rawKatMaterialPresent =
            providerOperationNoopKatAdmission.rawKatMaterialPresent ||
                providerOperationMetadataKatSuiteReport.rawKatMaterialPresent ||
                providerOperationMetadataKatValidation.rawKatMaterialPresent ||
                providerOperationMetadataKat.rawKatMaterialPresent
        val rawVectorBytesPresent =
            providerOperationNoopKatAdmission.rawVectorBytesPresent ||
                providerOperationMetadataKatSuiteReport.rawVectorBytesPresent ||
                providerOperationMetadataKatValidation.rawVectorBytesPresent ||
                providerOperationMetadataKat.rawVectorBytesPresent
        val rawVectorHexPresent =
            providerOperationNoopKatAdmission.rawVectorHexPresent ||
                providerOperationMetadataKatSuiteReport.rawVectorHexPresent ||
                providerOperationMetadataKatValidation.rawVectorHexPresent ||
                providerOperationMetadataKat.rawVectorHexPresent
        val publicVectorBytesPresent =
            providerOperationNoopKatAdmission.publicVectorBytesPresent ||
                providerOperationMetadataKatSuiteReport.publicVectorBytesPresent ||
                providerOperationMetadataKatValidation.publicVectorBytesPresent ||
                providerOperationMetadataKat.publicVectorBytesPresent
        val publicVectorHexPresent =
            providerOperationNoopKatAdmission.publicVectorHexPresent ||
                providerOperationMetadataKatSuiteReport.publicVectorHexPresent ||
                providerOperationMetadataKatValidation.publicVectorHexPresent ||
                providerOperationMetadataKat.publicVectorHexPresent

        val runtimeSelectable =
            providerOperationNoopKatAdmission.runtimeSelectable ||
                providerOperationMetadataKatSuiteReport.runtimeSelectable ||
                providerOperationMetadataKatValidation.runtimeSelectable ||
                providerOperationMetadataKat.runtimeSelectable ||
                capabilityMatrix.runtimeSelectable
        val registrySelectable =
            providerOperationNoopKatAdmission.registrySelectable ||
                providerOperationMetadataKatSuiteReport.registrySelectable ||
                providerOperationMetadataKatValidation.registrySelectable ||
                providerOperationMetadataKat.registrySelectable ||
                capabilityMatrix.registrySelectable
        val factoryReachable =
            providerOperationNoopKatAdmission.factoryReachable ||
                providerOperationMetadataKatSuiteReport.factoryReachable ||
                providerOperationMetadataKatValidation.factoryReachable ||
                providerOperationMetadataKat.factoryReachable ||
                capabilityMatrix.factoryReachable
        val dispatcherReachable =
            providerOperationNoopKatAdmission.dispatcherReachable ||
                providerOperationMetadataKatSuiteReport.dispatcherReachable ||
                providerOperationMetadataKatValidation.dispatcherReachable ||
                providerOperationMetadataKat.dispatcherReachable ||
                capabilityMatrix.dispatcherReachable
        val executorTargetable =
            providerOperationNoopKatAdmission.executorTargetable ||
                providerOperationMetadataKatSuiteReport.executorTargetable ||
                providerOperationMetadataKatValidation.executorTargetable ||
                providerOperationMetadataKat.executorTargetable ||
                capabilityMatrix.executorTargetable
        val providerKatExecutorReachable =
            providerOperationNoopKatAdmission.providerKatExecutorReachable ||
                providerOperationMetadataKatSuiteReport.providerKatExecutorReachable ||
                providerOperationMetadataKatValidation.providerKatExecutorReachable ||
                providerOperationMetadataKat.providerKatExecutorReachable ||
                capabilityMatrix.providerKatExecutorReachable
        val providerOperationReachable =
            providerOperationNoopKatAdmission.providerOperationReachable ||
                providerOperationMetadataKatSuiteReport.providerOperationReachable ||
                providerOperationMetadataKatValidation.providerOperationReachable ||
                providerOperationMetadataKat.providerOperationReachable ||
                providerOperationExecutionPresent ||
                capabilityMatrix.providerOperationReachable
        val cryptoExecutionReachable =
            providerOperationNoopKatAdmission.cryptoExecutionReachable ||
                providerOperationMetadataKatSuiteReport.cryptoExecutionReachable ||
                providerOperationMetadataKatValidation.cryptoExecutionReachable ||
                providerOperationMetadataKat.cryptoExecutionReachable ||
                cryptoExecutionPresent ||
                capabilityMatrix.cryptoExecutionReachable
        val vaultLifecycleReachable =
            providerOperationNoopKatAdmission.vaultLifecycleReachable ||
                providerOperationMetadataKatSuiteReport.vaultLifecycleReachable ||
                providerOperationMetadataKatValidation.vaultLifecycleReachable ||
                providerOperationMetadataKat.vaultLifecycleReachable ||
                capabilityMatrix.vaultLifecycleReachable
        val persistenceReachable =
            providerOperationNoopKatAdmission.persistenceReachable ||
                providerOperationMetadataKatSuiteReport.persistenceReachable ||
                providerOperationMetadataKatValidation.persistenceReachable ||
                providerOperationMetadataKat.persistenceReachable ||
                capabilityMatrix.persistenceReachable
        val productionSyncReachable =
            providerOperationNoopKatAdmission.productionSyncReachable ||
                providerOperationMetadataKatSuiteReport.productionSyncReachable ||
                providerOperationMetadataKatValidation.productionSyncReachable ||
                providerOperationMetadataKat.productionSyncReachable ||
                capabilityMatrix.productionSyncReachable
        val backendClientReachable =
            providerOperationNoopKatAdmission.backendClientReachable ||
                providerOperationMetadataKatSuiteReport.backendClientReachable ||
                providerOperationMetadataKatValidation.backendClientReachable ||
                providerOperationMetadataKat.backendClientReachable ||
                capabilityMatrix.backendClientReachable
        val bdkWalletStateReachable =
            providerOperationNoopKatAdmission.bdkWalletStateReachable ||
                providerOperationMetadataKatSuiteReport.bdkWalletStateReachable ||
                providerOperationMetadataKatValidation.bdkWalletStateReachable ||
                providerOperationMetadataKat.bdkWalletStateReachable ||
                capabilityMatrix.bdkWalletStateReachable
        val settingsCodecReachable =
            providerOperationNoopKatAdmission.settingsCodecReachable ||
                providerOperationMetadataKatSuiteReport.settingsCodecReachable ||
                providerOperationMetadataKatValidation.settingsCodecReachable ||
                providerOperationMetadataKat.settingsCodecReachable ||
                capabilityMatrix.settingsCodecReachable
        val uiSurfaceReachable =
            providerOperationNoopKatAdmission.uiSurfaceReachable ||
                providerOperationMetadataKatSuiteReport.uiSurfaceReachable ||
                providerOperationMetadataKatValidation.uiSurfaceReachable ||
                providerOperationMetadataKat.uiSurfaceReachable ||
                capabilityMatrix.uiSurfaceReachable
        val signingBroadcastingReachable =
            providerOperationNoopKatAdmission.signingBroadcastingReachable ||
                providerOperationMetadataKatSuiteReport.signingBroadcastingReachable ||
                providerOperationMetadataKatValidation.signingBroadcastingReachable ||
                providerOperationMetadataKat.signingBroadcastingReachable ||
                capabilityMatrix.signingBroadcastingReachable
        val publicEndpointReachable =
            providerOperationNoopKatAdmission.publicEndpointReachable ||
                providerOperationMetadataKatSuiteReport.publicEndpointReachable ||
                providerOperationMetadataKatValidation.publicEndpointReachable ||
                providerOperationMetadataKat.publicEndpointReachable ||
                capabilityMatrix.publicEndpointReachable
        val mainnetReachable =
            providerOperationNoopKatAdmission.mainnetReachable ||
                providerOperationMetadataKatSuiteReport.mainnetReachable ||
                providerOperationMetadataKatValidation.mainnetReachable ||
                providerOperationMetadataKat.mainnetReachable ||
                capabilityMatrix.mainnetReachable
        val implementsVaultCryptoProvider =
            providerOperationNoopKatAdmission.implementsVaultCryptoProvider ||
                providerOperationMetadataKatSuiteReport.implementsVaultCryptoProvider ||
                providerOperationMetadataKatValidation.implementsVaultCryptoProvider ||
                providerOperationMetadataKat.implementsVaultCryptoProvider ||
                capabilityMatrix.implementsVaultCryptoProvider
        val containsVaultCryptoProvider =
            providerOperationNoopKatAdmission.containsVaultCryptoProvider ||
                providerOperationMetadataKatSuiteReport.containsVaultCryptoProvider ||
                providerOperationMetadataKatValidation.containsVaultCryptoProvider ||
                providerOperationMetadataKat.containsVaultCryptoProvider ||
                capabilityMatrix.containsVaultCryptoProvider
        val canExecuteProviderOperations =
            providerOperationNoopKatAdmission.canExecuteProviderOperations ||
                providerOperationMetadataKatSuiteReport.canExecuteProviderOperations ||
                providerOperationMetadataKatValidation.canExecuteProviderOperations ||
                providerOperationMetadataKat.canExecuteProviderOperations ||
                capabilityMatrix.canExecuteProviderOperations
        val canExecuteCrypto =
            providerOperationNoopKatAdmission.canExecuteCrypto ||
                providerOperationMetadataKatSuiteReport.canExecuteCrypto ||
                providerOperationMetadataKatValidation.canExecuteCrypto ||
                providerOperationMetadataKat.canExecuteCrypto ||
                capabilityMatrix.canExecuteCrypto
        val canUseForVaultLifecycle =
            providerOperationNoopKatAdmission.canUseForVaultLifecycle ||
                providerOperationMetadataKatSuiteReport.canUseForVaultLifecycle ||
                providerOperationMetadataKatValidation.canUseForVaultLifecycle ||
                providerOperationMetadataKat.canUseForVaultLifecycle ||
                capabilityMatrix.canUseForVaultLifecycle
        val canUseForPersistence =
            providerOperationNoopKatAdmission.canUseForPersistence ||
                providerOperationMetadataKatSuiteReport.canUseForPersistence ||
                providerOperationMetadataKatValidation.canUseForPersistence ||
                providerOperationMetadataKat.canUseForPersistence ||
                capabilityMatrix.canUseForPersistence
        val canUseForSync =
            providerOperationNoopKatAdmission.canUseForSync ||
                providerOperationMetadataKatSuiteReport.canUseForSync ||
                providerOperationMetadataKatValidation.canUseForSync ||
                providerOperationMetadataKat.canUseForSync ||
                capabilityMatrix.canUseForSync
        val canUseForSigning =
            providerOperationNoopKatAdmission.canUseForSigning ||
                providerOperationMetadataKatSuiteReport.canUseForSigning ||
                providerOperationMetadataKatValidation.canUseForSigning ||
                providerOperationMetadataKat.canUseForSigning ||
                capabilityMatrix.canUseForSigning
        val canUseForBroadcasting =
            providerOperationNoopKatAdmission.canUseForBroadcasting ||
                providerOperationMetadataKatSuiteReport.canUseForBroadcasting ||
                providerOperationMetadataKatValidation.canUseForBroadcasting ||
                providerOperationMetadataKat.canUseForBroadcasting ||
                capabilityMatrix.canUseForBroadcasting
        val canUseForMainnet =
            providerOperationNoopKatAdmission.canUseForMainnet ||
                providerOperationMetadataKatSuiteReport.canUseForMainnet ||
                providerOperationMetadataKatValidation.canUseForMainnet ||
                providerOperationMetadataKat.canUseForMainnet ||
                capabilityMatrix.canUseForMainnet
        val productionProviderSelectable =
            providerOperationNoopKatAdmission.productionProviderSelectable ||
                providerOperationMetadataKatSuiteReport.productionProviderSelectable ||
                providerOperationMetadataKatValidation.productionProviderSelectable ||
                providerOperationMetadataKat.productionProviderSelectable ||
                capabilityMatrix.productionProviderSelectable ||
                marker.productionProviderSelectable

        val noopKatIsCommonTestOnly =
            providerOperationNoopKatAdmission.admissionIsCommonTestOnly &&
                providerOperationMetadataKatSuiteReport.suiteIsCommonTestOnly &&
                providerOperationMetadataKatValidation.validationIsCommonTestOnly &&
                providerOperationMetadataKat.evaluationIsCommonTestOnly &&
                providerOperationKatAdmission.admissionIsCommonTestOnly &&
                executableMetadataKatSuiteReport.suiteIsCommonTestOnly &&
                capabilityMatrix.matrixIsCommonTestOnly &&
                marker.sourceSet == SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet.CommonTest
        val evaluatesSyntheticNoopOnly =
            resultCategories.size ==
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatResultCategory.entries.size &&
            resultCategories.contains(
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatResultCategory
                    .SyntheticNoopResultModeled,
            ) &&
                providerOperationNoopKatAdmissionModeled &&
                providerOperationMetadataKatSuitePassed &&
                !providerOperationNoopKatAdmission.syntheticNoopResultPresent
        val evaluatesProviderOperationExecution = providerOperationExecutionPresent
        val evaluatesCryptoOperation = cryptoExecutionPresent
        val evaluatesVaultLifecycle = vaultLifecycleReachable
        val evaluatesPersistence = persistenceReachable
        val syntheticNoopResultPresent =
            evaluatesSyntheticNoopOnly &&
                !evaluatesProviderOperationExecution &&
                !evaluatesCryptoOperation &&
                !evaluatesVaultLifecycle &&
                !evaluatesPersistence
        val providerOperationNoopKatEvaluated =
            noopKatIsCommonTestOnly &&
                expectedProviderOperationNoopKatIdMatched &&
                providerOperationNoopKatAdmissionModeled &&
                providerOperationMetadataKatSuitePassed
        val providerOperationNoopKatPassed =
            providerOperationNoopKatEvaluated &&
                syntheticNoopResultPresent &&
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
                metadataKatSuitePassed &&
                providerOperationMetadataKatPassed &&
                providerOperationMetadataKatValidationPassed &&
                evaluatesSyntheticNoopOnly &&
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
                !productionProviderSelectable

        return SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatResult(
            markerCount = markerCount,
            caseBindingCount = caseBindingCount,
            executableMetadataKatSuiteReportCount = executableMetadataKatSuiteReportCount,
            providerOperationKatAdmissionCount = providerOperationKatAdmissionCount,
            providerOperationMetadataKatCount = providerOperationMetadataKatCount,
            providerOperationMetadataKatValidationCount = providerOperationMetadataKatValidationCount,
            providerOperationMetadataKatSuiteReportCount = providerOperationMetadataKatSuiteReportCount,
            providerOperationNoopKatAdmissionCount = providerOperationNoopKatAdmissionCount,
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
            syntheticNoopResultPresent = syntheticNoopResultPresent,
            noopKatIsCommonTestOnly = noopKatIsCommonTestOnly,
            noopKatIsProductionAuthorization = false,
            noopKatIsProviderSelectionAuthorization = false,
            noopKatIsProviderOperationAuthorization = false,
            noopKatIsKatExecutorAuthorization = false,
            noopKatIsCryptoAuthorization = false,
            noopKatIsVaultPersistenceAuthorization = false,
            noopKatIsMainnetAuthorization = false,
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
            resultCategories = resultCategories,
            sourceSet = SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatSourceSet.CommonTest,
            displayLabel = SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatSafeLabel(
                "synthetic no-op provider-operation KAT evidence",
            ),
        )
    }
}
