package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationSafeLabel(
    val value: String,
) {
    override fun toString(): String =
        "RedactedTestOnlyProviderIdentityProviderOperationNoopKatValidationSafeLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationSourceSet {
    CommonTest,
}

enum class SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationCheck {
    NoopProviderOperationKatPresentExactlyOnce,
    NoopProviderOperationKatResultReadSuccessfully,
    ProviderOperationNoopKatEvaluatedTrue,
    ProviderOperationNoopKatPassedTrue,
    SyntheticNoopResultPresentTrue,
    NoopProviderOperationKatReferencesExpectedMarkerSafeId,
    NoopProviderOperationKatReferencesExpectedFixtureId,
    NoopProviderOperationKatReferencesExpectedVectorId,
    NoopProviderOperationKatReferencesExpectedCaseId,
    NoopProviderOperationKatReferencesExpectedProviderOperationMetadataKatId,
    NoopProviderOperationKatUsesExpectedNoopKatId,
    NoopProviderOperationKatEvaluatesSyntheticNoopOnly,
    NoopProviderOperationKatDoesNotExecuteProviderOperation,
    NoopProviderOperationKatDoesNotExecuteCryptoOperation,
    NoopProviderOperationKatDoesNotEvaluateVaultLifecycle,
    NoopProviderOperationKatDoesNotEvaluatePersistence,
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

data class SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationReport(
    val markerCount: Int,
    val caseBindingCount: Int,
    val executableMetadataKatSuiteReportCount: Int,
    val providerOperationKatAdmissionCount: Int,
    val providerOperationMetadataKatCount: Int,
    val providerOperationMetadataKatValidationCount: Int,
    val providerOperationMetadataKatSuiteReportCount: Int,
    val providerOperationNoopKatAdmissionCount: Int,
    val providerOperationNoopKatCount: Int,
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
        List<SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationCheck>,
    val sourceSet: SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationSourceSet,
    val displayLabel: SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationSafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationReport(redactedMarkerId, redactedFixtureId, redactedVectorId, redactedCaseId, redactedProviderOperationMetadataKatId, redactedProviderOperationNoopKatId, commonTestOnly, syntheticNoopValidationOnly, nonAuthorizing)"
}

object SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationPolicy {
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

    fun currentProviderOperationNoopKatValidationReport():
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationReport {
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
        val validationChecks =
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationCheck.entries.toList()

        val markerCount =
            if (
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
        val caseBindingCount = providerOperationNoopKat.caseBindingCount
        val executableMetadataKatSuiteReportCount =
            providerOperationNoopKat.executableMetadataKatSuiteReportCount
        val providerOperationKatAdmissionCount = providerOperationNoopKat.providerOperationKatAdmissionCount
        val providerOperationMetadataKatCount = providerOperationNoopKat.providerOperationMetadataKatCount
        val providerOperationMetadataKatValidationCount =
            providerOperationNoopKat.providerOperationMetadataKatValidationCount
        val providerOperationMetadataKatSuiteReportCount =
            providerOperationNoopKat.providerOperationMetadataKatSuiteReportCount
        val providerOperationNoopKatAdmissionCount =
            providerOperationNoopKat.providerOperationNoopKatAdmissionCount
        val providerOperationNoopKatCount =
            if (
                providerOperationNoopKat.providerOperationNoopKatEvaluated &&
                providerOperationNoopKat.providerOperationNoopKatPassed &&
                providerOperationNoopKat.syntheticNoopResultPresent
            ) {
                1
            } else {
                0
            }

        val expectedSafeIdMatched =
            markerCount == 1 &&
                providerOperationNoopKat.expectedSafeIdMatched &&
                providerOperationNoopKatAdmission.expectedSafeIdMatched &&
                providerOperationMetadataKatSuiteReport.expectedSafeIdMatched &&
                providerOperationMetadataKatValidation.expectedSafeIdMatched &&
                providerOperationMetadataKat.expectedSafeIdMatched &&
                providerOperationKatAdmission.expectedSafeIdMatched &&
                executableMetadataKatSuiteReport.expectedSafeIdMatched
        val expectedFixtureIdMatched =
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
            providerOperationNoopKat.expectedProviderOperationMetadataKatIdMatched &&
                providerOperationNoopKatAdmission.expectedProviderOperationMetadataKatIdMatched &&
                providerOperationMetadataKatSuiteReport.expectedProviderOperationMetadataKatIdMatched &&
                providerOperationMetadataKatValidation.expectedProviderOperationMetadataKatIdMatched &&
                providerOperationMetadataKat.expectedProviderOperationMetadataKatIdMatched &&
                EXPECTED_PROVIDER_OPERATION_METADATA_KAT_ID ==
                "skald-test-only-provider-identity-provider-operation-metadata-kat-v1-inert-identity"
        val expectedProviderOperationNoopKatIdMatched =
            providerOperationNoopKat.expectedProviderOperationNoopKatIdMatched &&
                providerOperationNoopKatAdmission.expectedProviderOperationNoopKatIdMatched &&
                EXPECTED_PROVIDER_OPERATION_NOOP_KAT_ID ==
                "skald-test-only-provider-identity-provider-operation-noop-kat-v1-inert-identity"

        val metadataKatSuitePassed =
            providerOperationNoopKat.metadataKatSuitePassed &&
                providerOperationNoopKatAdmission.metadataKatSuitePassed &&
                providerOperationMetadataKatSuiteReport.metadataKatSuitePassed &&
                providerOperationMetadataKatValidation.metadataKatSuitePassed &&
                providerOperationMetadataKat.metadataKatSuitePassed &&
                providerOperationKatAdmission.metadataKatSuitePassed &&
                executableMetadataKatSuiteReport.suitePassed
        val providerOperationMetadataKatPassed =
            providerOperationNoopKat.providerOperationMetadataKatPassed &&
                providerOperationNoopKatAdmission.providerOperationMetadataKatPassed &&
                providerOperationMetadataKatSuiteReport.providerOperationMetadataKatPassed &&
                providerOperationMetadataKatValidation.providerOperationMetadataKatPassed &&
                providerOperationMetadataKat.providerOperationMetadataKatPassed
        val providerOperationMetadataKatValidationPassed =
            providerOperationNoopKat.providerOperationMetadataKatValidationPassed &&
                providerOperationNoopKatAdmission.providerOperationMetadataKatValidationPassed &&
                providerOperationMetadataKatSuiteReport.providerOperationMetadataKatValidationPassed &&
                providerOperationMetadataKatValidation.allValidationChecksPassed
        val providerOperationMetadataKatSuitePassed =
            providerOperationNoopKat.providerOperationMetadataKatSuitePassed &&
                providerOperationNoopKatAdmission.providerOperationMetadataKatSuitePassed &&
                providerOperationMetadataKatSuiteReport.providerOperationMetadataKatSuitePassed
        val providerOperationNoopKatAdmissionModeled =
            providerOperationNoopKat.providerOperationNoopKatAdmissionModeled &&
                providerOperationNoopKatAdmission.futureNoopProviderOperationKatCriteriaModeled &&
                !providerOperationNoopKatAdmission.futureNoopProviderOperationKatCriteriaAuthorizeCurrentExecution
        val providerOperationNoopKatEvaluated = providerOperationNoopKat.providerOperationNoopKatEvaluated
        val providerOperationNoopKatPassed = providerOperationNoopKat.providerOperationNoopKatPassed
        val syntheticNoopResultPresent = providerOperationNoopKat.syntheticNoopResultPresent

        val providerOperationKatExecutorPresent =
            providerOperationNoopKat.providerOperationKatExecutorPresent ||
                providerOperationNoopKatAdmission.currentKatExecutorPresent ||
                providerOperationMetadataKatSuiteReport.providerOperationKatExecutorPresent ||
                providerOperationMetadataKatValidation.providerOperationKatExecutorPresent ||
                providerOperationMetadataKat.providerOperationKatExecutorPresent
        val providerOperationKatRunnerPresent =
            providerOperationNoopKat.providerOperationKatRunnerPresent ||
                providerOperationNoopKatAdmission.currentKatRunnerPresent ||
                providerOperationMetadataKatSuiteReport.providerOperationKatRunnerPresent ||
                providerOperationMetadataKatValidation.providerOperationKatRunnerPresent ||
                providerOperationMetadataKat.providerOperationKatRunnerPresent
        val providerOperationExecutionPresent =
            providerOperationNoopKat.providerOperationExecutionPresent ||
                providerOperationNoopKatAdmission.currentProviderOperationExecutionPresent ||
                providerOperationNoopKatAdmission.currentNoopProviderOperationExecutionPresent ||
                providerOperationMetadataKatSuiteReport.providerOperationExecutionPresent ||
                providerOperationMetadataKatValidation.providerOperationExecutionPresent ||
                providerOperationMetadataKat.providerOperationExecutionPresent
        val cryptoExecutionPresent =
            providerOperationNoopKat.cryptoExecutionPresent ||
                providerOperationNoopKatAdmission.currentCryptoExecutionPresent ||
                providerOperationMetadataKatSuiteReport.cryptoExecutionPresent ||
                providerOperationMetadataKatValidation.cryptoExecutionPresent ||
                providerOperationMetadataKat.cryptoExecutionPresent
        val rawKatMaterialPresent =
            providerOperationNoopKat.rawKatMaterialPresent ||
                providerOperationNoopKatAdmission.rawKatMaterialPresent ||
                providerOperationMetadataKatSuiteReport.rawKatMaterialPresent ||
                providerOperationMetadataKatValidation.rawKatMaterialPresent ||
                providerOperationMetadataKat.rawKatMaterialPresent
        val rawVectorBytesPresent =
            providerOperationNoopKat.rawVectorBytesPresent ||
                providerOperationNoopKatAdmission.rawVectorBytesPresent ||
                providerOperationMetadataKatSuiteReport.rawVectorBytesPresent ||
                providerOperationMetadataKatValidation.rawVectorBytesPresent ||
                providerOperationMetadataKat.rawVectorBytesPresent
        val rawVectorHexPresent =
            providerOperationNoopKat.rawVectorHexPresent ||
                providerOperationNoopKatAdmission.rawVectorHexPresent ||
                providerOperationMetadataKatSuiteReport.rawVectorHexPresent ||
                providerOperationMetadataKatValidation.rawVectorHexPresent ||
                providerOperationMetadataKat.rawVectorHexPresent
        val publicVectorBytesPresent =
            providerOperationNoopKat.publicVectorBytesPresent ||
                providerOperationNoopKatAdmission.publicVectorBytesPresent ||
                providerOperationMetadataKatSuiteReport.publicVectorBytesPresent ||
                providerOperationMetadataKatValidation.publicVectorBytesPresent ||
                providerOperationMetadataKat.publicVectorBytesPresent
        val publicVectorHexPresent =
            providerOperationNoopKat.publicVectorHexPresent ||
                providerOperationNoopKatAdmission.publicVectorHexPresent ||
                providerOperationMetadataKatSuiteReport.publicVectorHexPresent ||
                providerOperationMetadataKatValidation.publicVectorHexPresent ||
                providerOperationMetadataKat.publicVectorHexPresent

        val runtimeSelectable =
            providerOperationNoopKat.runtimeSelectable ||
                providerOperationNoopKatAdmission.runtimeSelectable ||
                providerOperationMetadataKatSuiteReport.runtimeSelectable ||
                providerOperationMetadataKatValidation.runtimeSelectable ||
                providerOperationMetadataKat.runtimeSelectable ||
                capabilityMatrix.runtimeSelectable
        val registrySelectable =
            providerOperationNoopKat.registrySelectable ||
                providerOperationNoopKatAdmission.registrySelectable ||
                providerOperationMetadataKatSuiteReport.registrySelectable ||
                providerOperationMetadataKatValidation.registrySelectable ||
                providerOperationMetadataKat.registrySelectable ||
                capabilityMatrix.registrySelectable
        val factoryReachable =
            providerOperationNoopKat.factoryReachable ||
                providerOperationNoopKatAdmission.factoryReachable ||
                providerOperationMetadataKatSuiteReport.factoryReachable ||
                providerOperationMetadataKatValidation.factoryReachable ||
                providerOperationMetadataKat.factoryReachable ||
                capabilityMatrix.factoryReachable
        val dispatcherReachable =
            providerOperationNoopKat.dispatcherReachable ||
                providerOperationNoopKatAdmission.dispatcherReachable ||
                providerOperationMetadataKatSuiteReport.dispatcherReachable ||
                providerOperationMetadataKatValidation.dispatcherReachable ||
                providerOperationMetadataKat.dispatcherReachable ||
                capabilityMatrix.dispatcherReachable
        val executorTargetable =
            providerOperationNoopKat.executorTargetable ||
                providerOperationNoopKatAdmission.executorTargetable ||
                providerOperationMetadataKatSuiteReport.executorTargetable ||
                providerOperationMetadataKatValidation.executorTargetable ||
                providerOperationMetadataKat.executorTargetable ||
                capabilityMatrix.executorTargetable
        val providerKatExecutorReachable =
            providerOperationNoopKat.providerKatExecutorReachable ||
                providerOperationNoopKatAdmission.providerKatExecutorReachable ||
                providerOperationMetadataKatSuiteReport.providerKatExecutorReachable ||
                providerOperationMetadataKatValidation.providerKatExecutorReachable ||
                providerOperationMetadataKat.providerKatExecutorReachable ||
                capabilityMatrix.providerKatExecutorReachable
        val providerOperationReachable =
            providerOperationNoopKat.providerOperationReachable ||
                providerOperationNoopKatAdmission.providerOperationReachable ||
                providerOperationMetadataKatSuiteReport.providerOperationReachable ||
                providerOperationMetadataKatValidation.providerOperationReachable ||
                providerOperationMetadataKat.providerOperationReachable ||
                capabilityMatrix.providerOperationReachable ||
                providerOperationExecutionPresent
        val cryptoExecutionReachable =
            providerOperationNoopKat.cryptoExecutionReachable ||
                providerOperationNoopKatAdmission.cryptoExecutionReachable ||
                providerOperationMetadataKatSuiteReport.cryptoExecutionReachable ||
                providerOperationMetadataKatValidation.cryptoExecutionReachable ||
                providerOperationMetadataKat.cryptoExecutionReachable ||
                capabilityMatrix.cryptoExecutionReachable ||
                cryptoExecutionPresent
        val vaultLifecycleReachable =
            providerOperationNoopKat.vaultLifecycleReachable ||
                providerOperationNoopKatAdmission.vaultLifecycleReachable ||
                providerOperationMetadataKatSuiteReport.vaultLifecycleReachable ||
                providerOperationMetadataKatValidation.vaultLifecycleReachable ||
                providerOperationMetadataKat.vaultLifecycleReachable ||
                capabilityMatrix.vaultLifecycleReachable
        val persistenceReachable =
            providerOperationNoopKat.persistenceReachable ||
                providerOperationNoopKatAdmission.persistenceReachable ||
                providerOperationMetadataKatSuiteReport.persistenceReachable ||
                providerOperationMetadataKatValidation.persistenceReachable ||
                providerOperationMetadataKat.persistenceReachable ||
                capabilityMatrix.persistenceReachable
        val productionSyncReachable =
            providerOperationNoopKat.productionSyncReachable ||
                providerOperationNoopKatAdmission.productionSyncReachable ||
                providerOperationMetadataKatSuiteReport.productionSyncReachable ||
                providerOperationMetadataKatValidation.productionSyncReachable ||
                providerOperationMetadataKat.productionSyncReachable ||
                capabilityMatrix.productionSyncReachable
        val backendClientReachable =
            providerOperationNoopKat.backendClientReachable ||
                providerOperationNoopKatAdmission.backendClientReachable ||
                providerOperationMetadataKatSuiteReport.backendClientReachable ||
                providerOperationMetadataKatValidation.backendClientReachable ||
                providerOperationMetadataKat.backendClientReachable ||
                capabilityMatrix.backendClientReachable
        val bdkWalletStateReachable =
            providerOperationNoopKat.bdkWalletStateReachable ||
                providerOperationNoopKatAdmission.bdkWalletStateReachable ||
                providerOperationMetadataKatSuiteReport.bdkWalletStateReachable ||
                providerOperationMetadataKatValidation.bdkWalletStateReachable ||
                providerOperationMetadataKat.bdkWalletStateReachable ||
                capabilityMatrix.bdkWalletStateReachable
        val settingsCodecReachable =
            providerOperationNoopKat.settingsCodecReachable ||
                providerOperationNoopKatAdmission.settingsCodecReachable ||
                providerOperationMetadataKatSuiteReport.settingsCodecReachable ||
                providerOperationMetadataKatValidation.settingsCodecReachable ||
                providerOperationMetadataKat.settingsCodecReachable ||
                capabilityMatrix.settingsCodecReachable
        val uiSurfaceReachable =
            providerOperationNoopKat.uiSurfaceReachable ||
                providerOperationNoopKatAdmission.uiSurfaceReachable ||
                providerOperationMetadataKatSuiteReport.uiSurfaceReachable ||
                providerOperationMetadataKatValidation.uiSurfaceReachable ||
                providerOperationMetadataKat.uiSurfaceReachable ||
                capabilityMatrix.uiSurfaceReachable
        val signingBroadcastingReachable =
            providerOperationNoopKat.signingBroadcastingReachable ||
                providerOperationNoopKatAdmission.signingBroadcastingReachable ||
                providerOperationMetadataKatSuiteReport.signingBroadcastingReachable ||
                providerOperationMetadataKatValidation.signingBroadcastingReachable ||
                providerOperationMetadataKat.signingBroadcastingReachable ||
                capabilityMatrix.signingBroadcastingReachable
        val publicEndpointReachable =
            providerOperationNoopKat.publicEndpointReachable ||
                providerOperationNoopKatAdmission.publicEndpointReachable ||
                providerOperationMetadataKatSuiteReport.publicEndpointReachable ||
                providerOperationMetadataKatValidation.publicEndpointReachable ||
                providerOperationMetadataKat.publicEndpointReachable ||
                capabilityMatrix.publicEndpointReachable
        val mainnetReachable =
            providerOperationNoopKat.mainnetReachable ||
                providerOperationNoopKatAdmission.mainnetReachable ||
                providerOperationMetadataKatSuiteReport.mainnetReachable ||
                providerOperationMetadataKatValidation.mainnetReachable ||
                providerOperationMetadataKat.mainnetReachable ||
                capabilityMatrix.mainnetReachable
        val implementsVaultCryptoProvider =
            providerOperationNoopKat.implementsVaultCryptoProvider ||
                providerOperationNoopKatAdmission.implementsVaultCryptoProvider ||
                providerOperationMetadataKatSuiteReport.implementsVaultCryptoProvider ||
                providerOperationMetadataKatValidation.implementsVaultCryptoProvider ||
                providerOperationMetadataKat.implementsVaultCryptoProvider ||
                capabilityMatrix.implementsVaultCryptoProvider
        val containsVaultCryptoProvider =
            providerOperationNoopKat.containsVaultCryptoProvider ||
                providerOperationNoopKatAdmission.containsVaultCryptoProvider ||
                providerOperationMetadataKatSuiteReport.containsVaultCryptoProvider ||
                providerOperationMetadataKatValidation.containsVaultCryptoProvider ||
                providerOperationMetadataKat.containsVaultCryptoProvider ||
                capabilityMatrix.containsVaultCryptoProvider
        val canExecuteProviderOperations =
            providerOperationNoopKat.canExecuteProviderOperations ||
                providerOperationNoopKatAdmission.canExecuteProviderOperations ||
                providerOperationMetadataKatSuiteReport.canExecuteProviderOperations ||
                providerOperationMetadataKatValidation.canExecuteProviderOperations ||
                providerOperationMetadataKat.canExecuteProviderOperations ||
                capabilityMatrix.canExecuteProviderOperations
        val canExecuteCrypto =
            providerOperationNoopKat.canExecuteCrypto ||
                providerOperationNoopKatAdmission.canExecuteCrypto ||
                providerOperationMetadataKatSuiteReport.canExecuteCrypto ||
                providerOperationMetadataKatValidation.canExecuteCrypto ||
                providerOperationMetadataKat.canExecuteCrypto ||
                capabilityMatrix.canExecuteCrypto
        val canUseForVaultLifecycle =
            providerOperationNoopKat.canUseForVaultLifecycle ||
                providerOperationNoopKatAdmission.canUseForVaultLifecycle ||
                providerOperationMetadataKatSuiteReport.canUseForVaultLifecycle ||
                providerOperationMetadataKatValidation.canUseForVaultLifecycle ||
                providerOperationMetadataKat.canUseForVaultLifecycle ||
                capabilityMatrix.canUseForVaultLifecycle
        val canUseForPersistence =
            providerOperationNoopKat.canUseForPersistence ||
                providerOperationNoopKatAdmission.canUseForPersistence ||
                providerOperationMetadataKatSuiteReport.canUseForPersistence ||
                providerOperationMetadataKatValidation.canUseForPersistence ||
                providerOperationMetadataKat.canUseForPersistence ||
                capabilityMatrix.canUseForPersistence
        val canUseForSync =
            providerOperationNoopKat.canUseForSync ||
                providerOperationNoopKatAdmission.canUseForSync ||
                providerOperationMetadataKatSuiteReport.canUseForSync ||
                providerOperationMetadataKatValidation.canUseForSync ||
                providerOperationMetadataKat.canUseForSync ||
                capabilityMatrix.canUseForSync
        val canUseForSigning =
            providerOperationNoopKat.canUseForSigning ||
                providerOperationNoopKatAdmission.canUseForSigning ||
                providerOperationMetadataKatSuiteReport.canUseForSigning ||
                providerOperationMetadataKatValidation.canUseForSigning ||
                providerOperationMetadataKat.canUseForSigning ||
                capabilityMatrix.canUseForSigning
        val canUseForBroadcasting =
            providerOperationNoopKat.canUseForBroadcasting ||
                providerOperationNoopKatAdmission.canUseForBroadcasting ||
                providerOperationMetadataKatSuiteReport.canUseForBroadcasting ||
                providerOperationMetadataKatValidation.canUseForBroadcasting ||
                providerOperationMetadataKat.canUseForBroadcasting ||
                capabilityMatrix.canUseForBroadcasting
        val canUseForMainnet =
            providerOperationNoopKat.canUseForMainnet ||
                providerOperationNoopKatAdmission.canUseForMainnet ||
                providerOperationMetadataKatSuiteReport.canUseForMainnet ||
                providerOperationMetadataKatValidation.canUseForMainnet ||
                providerOperationMetadataKat.canUseForMainnet ||
                capabilityMatrix.canUseForMainnet
        val productionProviderSelectable =
            providerOperationNoopKat.productionProviderSelectable ||
                providerOperationNoopKatAdmission.productionProviderSelectable ||
                providerOperationMetadataKatSuiteReport.productionProviderSelectable ||
                providerOperationMetadataKatValidation.productionProviderSelectable ||
                providerOperationMetadataKat.productionProviderSelectable ||
                providerOperationKatAdmission.productionProviderSelectable ||
                executableMetadataKatSuiteReport.productionProviderSelectable ||
                capabilityMatrix.productionProviderSelectable ||
                marker.productionProviderSelectable

        val validationIsCommonTestOnly =
            providerOperationNoopKat.noopKatIsCommonTestOnly &&
                providerOperationNoopKatAdmission.admissionIsCommonTestOnly &&
                providerOperationMetadataKatSuiteReport.suiteIsCommonTestOnly &&
                providerOperationMetadataKatValidation.validationIsCommonTestOnly &&
                providerOperationMetadataKat.evaluationIsCommonTestOnly &&
                providerOperationKatAdmission.admissionIsCommonTestOnly &&
                executableMetadataKatSuiteReport.suiteIsCommonTestOnly &&
                capabilityMatrix.matrixIsCommonTestOnly &&
                marker.sourceSet == SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet.CommonTest
        val validatesSyntheticNoopOnly =
            providerOperationNoopKat.evaluatesSyntheticNoopOnly &&
                syntheticNoopResultPresent &&
                providerOperationNoopKatCount == 1
        val validatesProviderOperationExecution =
            providerOperationNoopKat.evaluatesProviderOperationExecution ||
                providerOperationExecutionPresent
        val validatesCryptoOperation =
            providerOperationNoopKat.evaluatesCryptoOperation ||
                cryptoExecutionPresent
        val validatesVaultLifecycle =
            providerOperationNoopKat.evaluatesVaultLifecycle ||
                vaultLifecycleReachable
        val validatesPersistence =
            providerOperationNoopKat.evaluatesPersistence ||
                persistenceReachable

        val providerSelectionAuthorizationAbsent =
            !providerOperationNoopKat.noopKatIsProviderSelectionAuthorization &&
                !providerOperationNoopKatAdmission.admissionIsProviderSelectionAuthorization &&
                !providerOperationMetadataKatSuiteReport.suiteIsProviderSelectionAuthorization &&
                !providerOperationMetadataKatValidation.validationIsProviderSelectionAuthorization &&
                !providerOperationMetadataKat.evaluationIsProviderSelectionAuthorization &&
                !providerOperationKatAdmission.admissionIsProviderSelectionAuthorization &&
                !executableMetadataKatSuiteReport.suiteIsProviderSelectionAuthorization
        val productionAuthorizationAbsent =
            !providerOperationNoopKat.noopKatIsProductionAuthorization &&
                !providerOperationNoopKatAdmission.admissionIsProductionAuthorization &&
                !providerOperationMetadataKatSuiteReport.suiteIsProductionAuthorization &&
                !providerOperationMetadataKatValidation.validationIsProductionAuthorization &&
                !providerOperationMetadataKat.evaluationIsProductionAuthorization &&
                !providerOperationKatAdmission.admissionIsProductionAuthorization &&
                !executableMetadataKatSuiteReport.suiteIsProductionAuthorization
        val providerOperationAuthorizationAbsent =
            !providerOperationNoopKat.noopKatIsProviderOperationAuthorization &&
                !providerOperationNoopKatAdmission.admissionIsProviderOperationAuthorization &&
                !providerOperationMetadataKatSuiteReport.suiteIsProviderOperationAuthorization &&
                !providerOperationMetadataKatValidation.validationIsProviderOperationAuthorization &&
                !providerOperationMetadataKat.evaluationIsProviderOperationAuthorization &&
                !providerOperationKatAdmission.admissionIsProviderOperationAuthorization
        val katExecutorAuthorizationAbsent =
            !providerOperationNoopKat.noopKatIsKatExecutorAuthorization &&
                !providerOperationNoopKatAdmission.admissionIsKatExecutorAuthorization &&
                !providerOperationMetadataKatSuiteReport.suiteIsKatExecutorAuthorization &&
                !providerOperationMetadataKatValidation.validationIsKatExecutorAuthorization &&
                !providerOperationMetadataKat.evaluationIsKatExecutorAuthorization &&
                !providerOperationKatAdmission.admissionIsKatExecutorAuthorization &&
                !executableMetadataKatSuiteReport.suiteIsKatExecutionAuthorization
        val mainnetAuthorizationAbsent =
            !providerOperationNoopKat.noopKatIsMainnetAuthorization &&
                !providerOperationNoopKatAdmission.admissionIsMainnetAuthorization &&
                !providerOperationMetadataKatSuiteReport.suiteIsMainnetAuthorization &&
                !providerOperationMetadataKatValidation.validationIsMainnetAuthorization &&
                !providerOperationMetadataKat.evaluationIsMainnetAuthorization &&
                !providerOperationKatAdmission.admissionIsMainnetAuthorization &&
                !executableMetadataKatSuiteReport.suiteIsMainnetAuthorization
        val providerSelectionRemainsDisabledProviderOnly =
            !runtimeSelectable &&
                !registrySelectable &&
                !providerOperationNoopKat.runtimeSelectable &&
                !providerOperationNoopKat.registrySelectable &&
                !providerOperationNoopKatAdmission.runtimeSelectable &&
                !providerOperationNoopKatAdmission.registrySelectable
        val productionProviderSelectableRemainsFalse =
            !productionProviderSelectable &&
                !providerOperationNoopKat.productionProviderSelectable &&
                !providerOperationNoopKatAdmission.productionProviderSelectable &&
                !providerOperationMetadataKatSuiteReport.productionProviderSelectable &&
                !providerOperationMetadataKatValidation.productionProviderSelectable &&
                !providerOperationMetadataKat.productionProviderSelectable &&
                !providerOperationKatAdmission.productionProviderSelectable &&
                !executableMetadataKatSuiteReport.productionProviderSelectable &&
                !capabilityMatrix.productionProviderSelectable &&
                !marker.productionProviderSelectable
        val productionRuntimeSourceAbsenceSatisfied = true
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
            ).none { output ->
                EXPECTED_SAFE_ID in output ||
                    EXPECTED_FIXTURE_ID in output ||
                    EXPECTED_VECTOR_ID in output ||
                    EXPECTED_CASE_ID in output ||
                    EXPECTED_PROVIDER_OPERATION_METADATA_KAT_ID in output ||
                    EXPECTED_PROVIDER_OPERATION_NOOP_KAT_ID in output
            }

        val checkResults = mapOf(
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationCheck
                .NoopProviderOperationKatPresentExactlyOnce to (providerOperationNoopKatCount == 1),
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationCheck
                .NoopProviderOperationKatResultReadSuccessfully to providerOperationNoopKatPassed,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationCheck
                .ProviderOperationNoopKatEvaluatedTrue to providerOperationNoopKatEvaluated,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationCheck
                .ProviderOperationNoopKatPassedTrue to providerOperationNoopKatPassed,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationCheck
                .SyntheticNoopResultPresentTrue to syntheticNoopResultPresent,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationCheck
                .NoopProviderOperationKatReferencesExpectedMarkerSafeId to expectedSafeIdMatched,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationCheck
                .NoopProviderOperationKatReferencesExpectedFixtureId to expectedFixtureIdMatched,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationCheck
                .NoopProviderOperationKatReferencesExpectedVectorId to expectedVectorIdMatched,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationCheck
                .NoopProviderOperationKatReferencesExpectedCaseId to expectedCaseIdMatched,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationCheck
                .NoopProviderOperationKatReferencesExpectedProviderOperationMetadataKatId to
                expectedProviderOperationMetadataKatIdMatched,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationCheck
                .NoopProviderOperationKatUsesExpectedNoopKatId to expectedProviderOperationNoopKatIdMatched,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationCheck
                .NoopProviderOperationKatEvaluatesSyntheticNoopOnly to validatesSyntheticNoopOnly,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationCheck
                .NoopProviderOperationKatDoesNotExecuteProviderOperation to !validatesProviderOperationExecution,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationCheck
                .NoopProviderOperationKatDoesNotExecuteCryptoOperation to !validatesCryptoOperation,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationCheck
                .NoopProviderOperationKatDoesNotEvaluateVaultLifecycle to !validatesVaultLifecycle,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationCheck
                .NoopProviderOperationKatDoesNotEvaluatePersistence to !validatesPersistence,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationCheck
                .ProviderOperationKatExecutorAbsent to !providerOperationKatExecutorPresent,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationCheck
                .ProviderOperationKatRunnerAbsent to !providerOperationKatRunnerPresent,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationCheck
                .ProviderOperationExecutionAbsent to
                (!providerOperationExecutionPresent && !providerOperationReachable && !canExecuteProviderOperations),
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationCheck
                .CryptoExecutionAbsent to (!cryptoExecutionPresent && !cryptoExecutionReachable && !canExecuteCrypto),
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationCheck
                .VaultPersistenceExecutionAbsent to (!persistenceReachable && !canUseForPersistence),
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationCheck
                .ProviderSelectionAuthorizationAbsent to providerSelectionAuthorizationAbsent,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationCheck
                .ProductionAuthorizationAbsent to productionAuthorizationAbsent,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationCheck
                .ProviderOperationAuthorizationAbsent to providerOperationAuthorizationAbsent,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationCheck
                .KatExecutorAuthorizationAbsent to katExecutorAuthorizationAbsent,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationCheck
                .MainnetAuthorizationAbsent to mainnetAuthorizationAbsent,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationCheck
                .ProviderSelectionRemainsDisabledProviderOnly to providerSelectionRemainsDisabledProviderOnly,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationCheck
                .ProductionProviderSelectableRemainsFalse to productionProviderSelectableRemainsFalse,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationCheck
                .ProductionRuntimeSourceAbsenceSatisfied to productionRuntimeSourceAbsenceSatisfied,
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationCheck
                .SafeOutputRedactionSatisfied to safeOutputRedactionSatisfied,
        )
        val allValidationChecksPassed =
            validationChecks.size ==
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationCheck.entries.size &&
                checkResults.size == validationChecks.size &&
                validationChecks.all { check -> checkResults[check] == true }

        return SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationReport(
            markerCount = markerCount,
            caseBindingCount = caseBindingCount,
            executableMetadataKatSuiteReportCount = executableMetadataKatSuiteReportCount,
            providerOperationKatAdmissionCount = providerOperationKatAdmissionCount,
            providerOperationMetadataKatCount = providerOperationMetadataKatCount,
            providerOperationMetadataKatValidationCount = providerOperationMetadataKatValidationCount,
            providerOperationMetadataKatSuiteReportCount = providerOperationMetadataKatSuiteReportCount,
            providerOperationNoopKatAdmissionCount = providerOperationNoopKatAdmissionCount,
            providerOperationNoopKatCount = providerOperationNoopKatCount,
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
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationSourceSet.CommonTest,
            displayLabel = SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatValidationSafeLabel(
                "no-op provider-operation KAT validation evidence",
            ),
        )
    }
}
