package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatAdmissionSafeLabel(
    val value: String,
) {
    override fun toString(): String =
        "RedactedTestOnlyProviderIdentityProviderOperationNoopKatAdmissionSafeLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatAdmissionSourceSet {
    CommonTest,
}

enum class SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatAdmissionOutcome {
    ProviderOperationNoopKatAdmissionModeled,
    FutureNoopProviderOperationKatRequiresSeparateBranch,
    CurrentNoopProviderOperationKatNotPresent,
    CurrentNoopProviderOperationExecutionAbsent,
    CurrentProviderOperationExecutionAbsent,
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

enum class SkaldVaultV1TestOnlyProviderIdentityFutureNoopProviderOperationKatCriterion {
    FutureBranchMustBeExplicitlyApproved,
    FutureNoopProviderOperationKatMustRemainCommonTestOnly,
    FutureNoopProviderOperationKatMustUseValidatedProviderOperationMetadataSuiteOnly,
    FutureNoopProviderOperationKatMustUsePublicNonSecretTextOnlyVectorOnly,
    FutureNoopProviderOperationKatMustReturnOnlySyntheticNoopResult,
    FutureNoopProviderOperationKatMustNotUseRawBytes,
    FutureNoopProviderOperationKatMustNotUseHex,
    FutureNoopProviderOperationKatMustNotUseCryptoMaterial,
    FutureNoopProviderOperationKatMustNotUseWalletMaterial,
    FutureNoopProviderOperationKatMustNotUseEndpointMaterial,
    FutureNoopProviderOperationKatMustNotUseProviderHandles,
    FutureNoopProviderOperationKatMustNotImplementVaultCryptoProvider,
    FutureNoopProviderOperationKatMustNotUseVaultCryptoProviderInstance,
    FutureNoopProviderOperationKatMustNotUseProviderSelection,
    FutureNoopProviderOperationKatMustNotUseRegistry,
    FutureNoopProviderOperationKatMustNotUseFactory,
    FutureNoopProviderOperationKatMustNotUseDispatcher,
    FutureNoopProviderOperationKatMustNotUseExecutorTarget,
    FutureNoopProviderOperationKatMustNotRunKdfHkdfHmacAead,
    FutureNoopProviderOperationKatMustNotTouchVaultLifecycle,
    FutureNoopProviderOperationKatMustNotTouchPersistence,
    FutureNoopProviderOperationKatMustNotTouchBackendBdkSettingsUi,
    FutureNoopProviderOperationKatMustNotSignOrBroadcast,
    FutureNoopProviderOperationKatMustNotEnableMainnet,
    FutureNoopProviderOperationKatMustRemainCoveredBySourceGuards,
    FutureNoopProviderOperationKatMustRemainRedactedInOutput,
}

enum class SkaldVaultV1TestOnlyProviderIdentityForbiddenCurrentNoopProviderOperationKatState {
    NoopProviderOperationKatPresent,
    NoopProviderOperationExecutionPresent,
    ProviderOperationExecutionPresent,
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

data class SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatAdmission(
    val markerCount: Int,
    val caseBindingCount: Int,
    val executableMetadataKatSuiteReportCount: Int,
    val providerOperationKatAdmissionCount: Int,
    val providerOperationMetadataKatCount: Int,
    val providerOperationMetadataKatValidationCount: Int,
    val providerOperationMetadataKatSuiteReportCount: Int,
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
    val admissionIsCommonTestOnly: Boolean,
    val admissionIsProductionAuthorization: Boolean,
    val admissionIsProviderSelectionAuthorization: Boolean,
    val admissionIsProviderOperationAuthorization: Boolean,
    val admissionIsKatExecutorAuthorization: Boolean,
    val admissionIsCryptoAuthorization: Boolean,
    val admissionIsVaultPersistenceAuthorization: Boolean,
    val admissionIsMainnetAuthorization: Boolean,
    val futureNoopProviderOperationKatCriteriaModeled: Boolean,
    val futureNoopProviderOperationKatCriteriaAuthorizeCurrentExecution: Boolean,
    val currentNoopProviderOperationKatPresent: Boolean,
    val currentNoopProviderOperationExecutionPresent: Boolean,
    val currentProviderOperationExecutionPresent: Boolean,
    val currentCryptoExecutionPresent: Boolean,
    val currentKatRunnerPresent: Boolean,
    val currentKatExecutorPresent: Boolean,
    val syntheticNoopResultPresent: Boolean,
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
    val admissionOutcomes:
        List<SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatAdmissionOutcome>,
    val futureNoopProviderOperationKatCriteria:
        List<SkaldVaultV1TestOnlyProviderIdentityFutureNoopProviderOperationKatCriterion>,
    val forbiddenCurrentNoopProviderOperationKatStates:
        List<SkaldVaultV1TestOnlyProviderIdentityForbiddenCurrentNoopProviderOperationKatState>,
    val sourceSet: SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatAdmissionSourceSet,
    val displayLabel: SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatAdmissionSafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatAdmission(redactedMarkerId, redactedFixtureId, redactedVectorId, redactedCaseId, redactedProviderOperationMetadataKatId, redactedProviderOperationNoopKatId, commonTestOnly, noopProviderOperationAdmissionOnly, nonExecutable, nonAuthorizing)"
}

object SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatAdmissionPolicy {
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

    fun currentProviderOperationNoopKatAdmission():
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatAdmission {
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
        val admissionOutcomes =
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatAdmissionOutcome.entries.toList()
        val futureCriteria =
            SkaldVaultV1TestOnlyProviderIdentityFutureNoopProviderOperationKatCriterion.entries.toList()
        val forbiddenStates =
            SkaldVaultV1TestOnlyProviderIdentityForbiddenCurrentNoopProviderOperationKatState.entries.toList()

        val markerCount =
            if (
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
        val caseBindingCount = providerOperationMetadataKatSuiteReport.caseBindingCount
        val executableMetadataKatSuiteReportCount =
            providerOperationMetadataKatSuiteReport.executableMetadataKatSuiteReportCount
        val providerOperationKatAdmissionCount =
            providerOperationMetadataKatSuiteReport.providerOperationKatAdmissionCount
        val providerOperationMetadataKatCount =
            providerOperationMetadataKatSuiteReport.providerOperationMetadataKatCount
        val providerOperationMetadataKatValidationCount =
            if (
                providerOperationMetadataKatSuiteReport.providerOperationMetadataKatValidationCount == 1 &&
                providerOperationMetadataKatValidation.allValidationChecksPassed
            ) {
                1
            } else {
                0
            }
        val providerOperationMetadataKatSuiteReportCount =
            if (
                providerOperationMetadataKatSuiteReport.suiteReportGenerated &&
                providerOperationMetadataKatSuiteReport.providerOperationMetadataKatSuitePassed
            ) {
                1
            } else {
                0
            }

        val expectedSafeIdMatched =
            markerCount == 1 &&
                providerOperationMetadataKatSuiteReport.expectedSafeIdMatched &&
                providerOperationMetadataKatValidation.expectedSafeIdMatched &&
                providerOperationMetadataKat.expectedSafeIdMatched &&
                providerOperationKatAdmission.expectedSafeIdMatched &&
                executableMetadataKatSuiteReport.expectedSafeIdMatched
        val expectedFixtureIdMatched =
            providerOperationMetadataKatSuiteReport.expectedFixtureIdMatched &&
                providerOperationMetadataKatValidation.expectedFixtureIdMatched &&
                providerOperationMetadataKat.expectedFixtureIdMatched &&
                providerOperationKatAdmission.expectedFixtureIdMatched &&
                executableMetadataKatSuiteReport.expectedFixtureIdMatched &&
                caseBinding.fixtureId.value == EXPECTED_FIXTURE_ID
        val expectedVectorIdMatched =
            providerOperationMetadataKatSuiteReport.expectedVectorIdMatched &&
                providerOperationMetadataKatValidation.expectedVectorIdMatched &&
                providerOperationMetadataKat.expectedVectorIdMatched &&
                providerOperationKatAdmission.expectedVectorIdMatched &&
                executableMetadataKatSuiteReport.expectedVectorIdMatched &&
                caseBinding.vectorId.value == EXPECTED_VECTOR_ID
        val expectedCaseIdMatched =
            providerOperationMetadataKatSuiteReport.expectedCaseIdMatched &&
                providerOperationMetadataKatValidation.expectedCaseIdMatched &&
                providerOperationMetadataKat.expectedCaseIdMatched &&
                providerOperationKatAdmission.expectedCaseIdMatched &&
                executableMetadataKatSuiteReport.expectedCaseIdMatched &&
                caseBinding.caseId.value == EXPECTED_CASE_ID
        val expectedProviderOperationMetadataKatIdMatched =
            providerOperationMetadataKatSuiteReport.expectedProviderOperationMetadataKatIdMatched &&
                providerOperationMetadataKatValidation.expectedProviderOperationMetadataKatIdMatched &&
                providerOperationMetadataKat.expectedProviderOperationMetadataKatIdMatched &&
                EXPECTED_PROVIDER_OPERATION_METADATA_KAT_ID ==
                "skald-test-only-provider-identity-provider-operation-metadata-kat-v1-inert-identity"
        val expectedProviderOperationNoopKatIdMatched =
            EXPECTED_PROVIDER_OPERATION_NOOP_KAT_ID ==
                "skald-test-only-provider-identity-provider-operation-noop-kat-v1-inert-identity"

        val metadataKatSuitePassed =
            providerOperationMetadataKatSuiteReport.metadataKatSuitePassed &&
                providerOperationMetadataKatValidation.metadataKatSuitePassed &&
                providerOperationMetadataKat.metadataKatSuitePassed &&
                providerOperationKatAdmission.metadataKatSuitePassed &&
                executableMetadataKatSuiteReport.suitePassed
        val providerOperationMetadataKatPassed =
            providerOperationMetadataKatSuiteReport.providerOperationMetadataKatPassed &&
                providerOperationMetadataKatValidation.providerOperationMetadataKatPassed &&
                providerOperationMetadataKat.providerOperationMetadataKatPassed
        val providerOperationMetadataKatValidationPassed =
            providerOperationMetadataKatSuiteReport.providerOperationMetadataKatValidationPassed &&
                providerOperationMetadataKatValidation.allValidationChecksPassed
        val providerOperationMetadataKatSuitePassed =
            providerOperationMetadataKatSuiteReport.providerOperationMetadataKatSuitePassed

        val currentNoopProviderOperationKatPresent = false
        val currentNoopProviderOperationExecutionPresent = false
        val syntheticNoopResultPresent = false
        val currentProviderOperationExecutionPresent =
            providerOperationMetadataKatSuiteReport.providerOperationExecutionPresent ||
                providerOperationMetadataKatValidation.providerOperationExecutionPresent ||
                providerOperationMetadataKat.providerOperationExecutionPresent ||
                providerOperationKatAdmission.currentProviderOperationExecutionPresent ||
                capabilityMatrix.providerOperationReachable
        val currentCryptoExecutionPresent =
            providerOperationMetadataKatSuiteReport.cryptoExecutionPresent ||
                providerOperationMetadataKatValidation.cryptoExecutionPresent ||
                providerOperationMetadataKat.cryptoExecutionPresent ||
                providerOperationKatAdmission.currentCryptoExecutionPresent ||
                capabilityMatrix.cryptoExecutionReachable
        val currentKatRunnerPresent =
            providerOperationMetadataKatSuiteReport.providerOperationKatRunnerPresent ||
                providerOperationMetadataKatValidation.providerOperationKatRunnerPresent ||
                providerOperationMetadataKat.providerOperationKatRunnerPresent ||
                providerOperationKatAdmission.currentKatRunnerPresent
        val currentKatExecutorPresent =
            providerOperationMetadataKatSuiteReport.providerOperationKatExecutorPresent ||
                providerOperationMetadataKatValidation.providerOperationKatExecutorPresent ||
                providerOperationMetadataKat.providerOperationKatExecutorPresent ||
                providerOperationKatAdmission.currentKatExecutorPresent

        val rawKatMaterialPresent =
            providerOperationMetadataKatSuiteReport.rawKatMaterialPresent ||
                providerOperationMetadataKatValidation.rawKatMaterialPresent ||
                providerOperationMetadataKat.rawKatMaterialPresent ||
                providerOperationKatAdmission.rawKatMaterialPresent
        val rawVectorBytesPresent =
            providerOperationMetadataKatSuiteReport.rawVectorBytesPresent ||
                providerOperationMetadataKatValidation.rawVectorBytesPresent ||
                providerOperationMetadataKat.rawVectorBytesPresent ||
                providerOperationKatAdmission.rawVectorBytesPresent
        val rawVectorHexPresent =
            providerOperationMetadataKatSuiteReport.rawVectorHexPresent ||
                providerOperationMetadataKatValidation.rawVectorHexPresent ||
                providerOperationMetadataKat.rawVectorHexPresent ||
                providerOperationKatAdmission.rawVectorHexPresent
        val publicVectorBytesPresent =
            providerOperationMetadataKatSuiteReport.publicVectorBytesPresent ||
                providerOperationMetadataKatValidation.publicVectorBytesPresent ||
                providerOperationMetadataKat.publicVectorBytesPresent ||
                providerOperationKatAdmission.publicVectorBytesPresent
        val publicVectorHexPresent =
            providerOperationMetadataKatSuiteReport.publicVectorHexPresent ||
                providerOperationMetadataKatValidation.publicVectorHexPresent ||
                providerOperationMetadataKat.publicVectorHexPresent ||
                providerOperationKatAdmission.publicVectorHexPresent

        val providerOperationReachable =
            providerOperationMetadataKatSuiteReport.providerOperationReachable ||
                providerOperationMetadataKatValidation.providerOperationReachable ||
                providerOperationMetadataKat.providerOperationReachable ||
                providerOperationKatAdmission.providerOperationReachable ||
                currentProviderOperationExecutionPresent
        val cryptoExecutionReachable =
            providerOperationMetadataKatSuiteReport.cryptoExecutionReachable ||
                providerOperationMetadataKatValidation.cryptoExecutionReachable ||
                providerOperationMetadataKat.cryptoExecutionReachable ||
                providerOperationKatAdmission.cryptoExecutionReachable ||
                currentCryptoExecutionPresent
        val runtimeSelectable =
            providerOperationMetadataKatSuiteReport.runtimeSelectable ||
                providerOperationMetadataKatValidation.runtimeSelectable ||
                providerOperationMetadataKat.runtimeSelectable ||
                providerOperationKatAdmission.runtimeSelectable ||
                capabilityMatrix.runtimeSelectable
        val registrySelectable =
            providerOperationMetadataKatSuiteReport.registrySelectable ||
                providerOperationMetadataKatValidation.registrySelectable ||
                providerOperationMetadataKat.registrySelectable ||
                providerOperationKatAdmission.registrySelectable ||
                capabilityMatrix.registrySelectable
        val factoryReachable =
            providerOperationMetadataKatSuiteReport.factoryReachable ||
                providerOperationMetadataKatValidation.factoryReachable ||
                providerOperationMetadataKat.factoryReachable ||
                providerOperationKatAdmission.factoryReachable ||
                capabilityMatrix.factoryReachable
        val dispatcherReachable =
            providerOperationMetadataKatSuiteReport.dispatcherReachable ||
                providerOperationMetadataKatValidation.dispatcherReachable ||
                providerOperationMetadataKat.dispatcherReachable ||
                providerOperationKatAdmission.dispatcherReachable ||
                capabilityMatrix.dispatcherReachable
        val executorTargetable =
            providerOperationMetadataKatSuiteReport.executorTargetable ||
                providerOperationMetadataKatValidation.executorTargetable ||
                providerOperationMetadataKat.executorTargetable ||
                providerOperationKatAdmission.executorTargetable ||
                capabilityMatrix.executorTargetable
        val providerKatExecutorReachable =
            providerOperationMetadataKatSuiteReport.providerKatExecutorReachable ||
                providerOperationMetadataKatValidation.providerKatExecutorReachable ||
                providerOperationMetadataKat.providerKatExecutorReachable ||
                providerOperationKatAdmission.providerKatExecutorReachable ||
                capabilityMatrix.providerKatExecutorReachable
        val vaultLifecycleReachable =
            providerOperationMetadataKatSuiteReport.vaultLifecycleReachable ||
                providerOperationMetadataKatValidation.vaultLifecycleReachable ||
                providerOperationMetadataKat.vaultLifecycleReachable ||
                providerOperationKatAdmission.vaultLifecycleReachable ||
                capabilityMatrix.vaultLifecycleReachable
        val persistenceReachable =
            providerOperationMetadataKatSuiteReport.persistenceReachable ||
                providerOperationMetadataKatValidation.persistenceReachable ||
                providerOperationMetadataKat.persistenceReachable ||
                providerOperationKatAdmission.persistenceReachable ||
                capabilityMatrix.persistenceReachable
        val productionSyncReachable =
            providerOperationMetadataKatSuiteReport.productionSyncReachable ||
                providerOperationMetadataKatValidation.productionSyncReachable ||
                providerOperationMetadataKat.productionSyncReachable ||
                providerOperationKatAdmission.productionSyncReachable ||
                capabilityMatrix.productionSyncReachable
        val backendClientReachable =
            providerOperationMetadataKatSuiteReport.backendClientReachable ||
                providerOperationMetadataKatValidation.backendClientReachable ||
                providerOperationMetadataKat.backendClientReachable ||
                providerOperationKatAdmission.backendClientReachable ||
                capabilityMatrix.backendClientReachable
        val bdkWalletStateReachable =
            providerOperationMetadataKatSuiteReport.bdkWalletStateReachable ||
                providerOperationMetadataKatValidation.bdkWalletStateReachable ||
                providerOperationMetadataKat.bdkWalletStateReachable ||
                providerOperationKatAdmission.bdkWalletStateReachable ||
                capabilityMatrix.bdkWalletStateReachable
        val settingsCodecReachable =
            providerOperationMetadataKatSuiteReport.settingsCodecReachable ||
                providerOperationMetadataKatValidation.settingsCodecReachable ||
                providerOperationMetadataKat.settingsCodecReachable ||
                providerOperationKatAdmission.settingsCodecReachable ||
                capabilityMatrix.settingsCodecReachable
        val uiSurfaceReachable =
            providerOperationMetadataKatSuiteReport.uiSurfaceReachable ||
                providerOperationMetadataKatValidation.uiSurfaceReachable ||
                providerOperationMetadataKat.uiSurfaceReachable ||
                providerOperationKatAdmission.uiSurfaceReachable ||
                capabilityMatrix.uiSurfaceReachable
        val signingBroadcastingReachable =
            providerOperationMetadataKatSuiteReport.signingBroadcastingReachable ||
                providerOperationMetadataKatValidation.signingBroadcastingReachable ||
                providerOperationMetadataKat.signingBroadcastingReachable ||
                providerOperationKatAdmission.signingBroadcastingReachable ||
                capabilityMatrix.signingBroadcastingReachable
        val publicEndpointReachable =
            providerOperationMetadataKatSuiteReport.publicEndpointReachable ||
                providerOperationMetadataKatValidation.publicEndpointReachable ||
                providerOperationMetadataKat.publicEndpointReachable ||
                providerOperationKatAdmission.publicEndpointReachable ||
                capabilityMatrix.publicEndpointReachable
        val mainnetReachable =
            providerOperationMetadataKatSuiteReport.mainnetReachable ||
                providerOperationMetadataKatValidation.mainnetReachable ||
                providerOperationMetadataKat.mainnetReachable ||
                providerOperationKatAdmission.mainnetReachable ||
                capabilityMatrix.mainnetReachable
        val implementsVaultCryptoProvider =
            providerOperationMetadataKatSuiteReport.implementsVaultCryptoProvider ||
                providerOperationMetadataKatValidation.implementsVaultCryptoProvider ||
                providerOperationMetadataKat.implementsVaultCryptoProvider ||
                providerOperationKatAdmission.implementsVaultCryptoProvider ||
                capabilityMatrix.implementsVaultCryptoProvider
        val containsVaultCryptoProvider =
            providerOperationMetadataKatSuiteReport.containsVaultCryptoProvider ||
                providerOperationMetadataKatValidation.containsVaultCryptoProvider ||
                providerOperationMetadataKat.containsVaultCryptoProvider ||
                providerOperationKatAdmission.containsVaultCryptoProvider ||
                capabilityMatrix.containsVaultCryptoProvider
        val canExecuteProviderOperations =
            providerOperationMetadataKatSuiteReport.canExecuteProviderOperations ||
                providerOperationMetadataKatValidation.canExecuteProviderOperations ||
                providerOperationMetadataKat.canExecuteProviderOperations ||
                providerOperationKatAdmission.canExecuteProviderOperations ||
                capabilityMatrix.canExecuteProviderOperations
        val canExecuteCrypto =
            providerOperationMetadataKatSuiteReport.canExecuteCrypto ||
                providerOperationMetadataKatValidation.canExecuteCrypto ||
                providerOperationMetadataKat.canExecuteCrypto ||
                providerOperationKatAdmission.canExecuteCrypto ||
                capabilityMatrix.canExecuteCrypto
        val canUseForVaultLifecycle =
            providerOperationMetadataKatSuiteReport.canUseForVaultLifecycle ||
                providerOperationMetadataKatValidation.canUseForVaultLifecycle ||
                providerOperationMetadataKat.canUseForVaultLifecycle ||
                providerOperationKatAdmission.canUseForVaultLifecycle ||
                capabilityMatrix.canUseForVaultLifecycle
        val canUseForPersistence =
            providerOperationMetadataKatSuiteReport.canUseForPersistence ||
                providerOperationMetadataKatValidation.canUseForPersistence ||
                providerOperationMetadataKat.canUseForPersistence ||
                providerOperationKatAdmission.canUseForPersistence ||
                capabilityMatrix.canUseForPersistence
        val canUseForSync =
            providerOperationMetadataKatSuiteReport.canUseForSync ||
                providerOperationMetadataKatValidation.canUseForSync ||
                providerOperationMetadataKat.canUseForSync ||
                providerOperationKatAdmission.canUseForSync ||
                capabilityMatrix.canUseForSync
        val canUseForSigning =
            providerOperationMetadataKatSuiteReport.canUseForSigning ||
                providerOperationMetadataKatValidation.canUseForSigning ||
                providerOperationMetadataKat.canUseForSigning ||
                providerOperationKatAdmission.canUseForSigning ||
                capabilityMatrix.canUseForSigning
        val canUseForBroadcasting =
            providerOperationMetadataKatSuiteReport.canUseForBroadcasting ||
                providerOperationMetadataKatValidation.canUseForBroadcasting ||
                providerOperationMetadataKat.canUseForBroadcasting ||
                providerOperationKatAdmission.canUseForBroadcasting ||
                capabilityMatrix.canUseForBroadcasting
        val canUseForMainnet =
            providerOperationMetadataKatSuiteReport.canUseForMainnet ||
                providerOperationMetadataKatValidation.canUseForMainnet ||
                providerOperationMetadataKat.canUseForMainnet ||
                providerOperationKatAdmission.canUseForMainnet ||
                capabilityMatrix.canUseForMainnet
        val productionProviderSelectable =
            providerOperationMetadataKatSuiteReport.productionProviderSelectable ||
                providerOperationMetadataKatValidation.productionProviderSelectable ||
                providerOperationMetadataKat.productionProviderSelectable ||
                providerOperationKatAdmission.productionProviderSelectable ||
                capabilityMatrix.productionProviderSelectable ||
                marker.productionProviderSelectable

        val admissionIsCommonTestOnly =
            providerOperationMetadataKatSuiteReport.suiteIsCommonTestOnly &&
                providerOperationMetadataKatValidation.validationIsCommonTestOnly &&
                providerOperationMetadataKat.evaluationIsCommonTestOnly &&
                providerOperationKatAdmission.admissionIsCommonTestOnly &&
                executableMetadataKatSuiteReport.suiteIsCommonTestOnly &&
                capabilityMatrix.matrixIsCommonTestOnly &&
                marker.sourceSet == SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet.CommonTest
        val futureNoopProviderOperationKatCriteriaModeled =
            futureCriteria.size ==
                SkaldVaultV1TestOnlyProviderIdentityFutureNoopProviderOperationKatCriterion.entries.size &&
                admissionOutcomes.size ==
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatAdmissionOutcome.entries.size &&
                forbiddenStates.size ==
                SkaldVaultV1TestOnlyProviderIdentityForbiddenCurrentNoopProviderOperationKatState.entries.size &&
                providerOperationMetadataKatSuitePassed &&
                providerOperationMetadataKatValidationPassed &&
                expectedProviderOperationNoopKatIdMatched
        val futureNoopProviderOperationKatCriteriaAuthorizeCurrentExecution = false

        return SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatAdmission(
            markerCount = markerCount,
            caseBindingCount = caseBindingCount,
            executableMetadataKatSuiteReportCount = executableMetadataKatSuiteReportCount,
            providerOperationKatAdmissionCount = providerOperationKatAdmissionCount,
            providerOperationMetadataKatCount = providerOperationMetadataKatCount,
            providerOperationMetadataKatValidationCount = providerOperationMetadataKatValidationCount,
            providerOperationMetadataKatSuiteReportCount = providerOperationMetadataKatSuiteReportCount,
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
            admissionIsCommonTestOnly = admissionIsCommonTestOnly,
            admissionIsProductionAuthorization = false,
            admissionIsProviderSelectionAuthorization = false,
            admissionIsProviderOperationAuthorization = false,
            admissionIsKatExecutorAuthorization = false,
            admissionIsCryptoAuthorization = false,
            admissionIsVaultPersistenceAuthorization = false,
            admissionIsMainnetAuthorization = false,
            futureNoopProviderOperationKatCriteriaModeled = futureNoopProviderOperationKatCriteriaModeled,
            futureNoopProviderOperationKatCriteriaAuthorizeCurrentExecution =
                futureNoopProviderOperationKatCriteriaAuthorizeCurrentExecution,
            currentNoopProviderOperationKatPresent = currentNoopProviderOperationKatPresent,
            currentNoopProviderOperationExecutionPresent = currentNoopProviderOperationExecutionPresent,
            currentProviderOperationExecutionPresent = currentProviderOperationExecutionPresent,
            currentCryptoExecutionPresent = currentCryptoExecutionPresent,
            currentKatRunnerPresent = currentKatRunnerPresent,
            currentKatExecutorPresent = currentKatExecutorPresent,
            syntheticNoopResultPresent = syntheticNoopResultPresent,
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
            futureNoopProviderOperationKatCriteria = futureCriteria,
            forbiddenCurrentNoopProviderOperationKatStates = forbiddenStates,
            sourceSet =
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatAdmissionSourceSet.CommonTest,
            displayLabel = SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatAdmissionSafeLabel(
                "no-op provider-operation KAT admission evidence",
            ),
        )
    }
}
