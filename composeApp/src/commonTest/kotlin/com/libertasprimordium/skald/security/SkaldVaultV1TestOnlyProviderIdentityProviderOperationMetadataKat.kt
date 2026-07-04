package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatSafeLabel(val value: String) {
    override fun toString(): String =
        "RedactedTestOnlyProviderIdentityProviderOperationMetadataKatSafeLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatSourceSet {
    CommonTest,
}

enum class SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataCategory {
    IdentityMarkerMetadata,
    PublicVectorMetadata,
    CaseBindingMetadata,
    ExecutableMetadataKatSuiteMetadata,
    ProviderOperationAdmissionMetadata,
    ProviderOperationShapeMetadata,
    NonExecutableProviderOperationEvidence,
    NonCryptoProviderOperationEvidence,
    NonAuthorizingProviderOperationEvidence,
}

enum class SkaldVaultV1TestOnlyProviderIdentityProviderOperationShapeLabel {
    IdentityMetadataCheck,
    PublicVectorMetadataCheck,
    CaseBindingMetadataCheck,
    AdmissionCriteriaCheck,
    CapabilityMatrixCheck,
    SourceGuardAbsenceCheck,
}

data class SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatResult(
    val markerCount: Int,
    val fixtureRowCount: Int,
    val publicVectorRowCount: Int,
    val caseBindingCount: Int,
    val executableMetadataKatSuiteReportCount: Int,
    val providerOperationKatAdmissionCount: Int,
    val expectedSafeIdMatched: Boolean,
    val expectedFixtureIdMatched: Boolean,
    val expectedVectorIdMatched: Boolean,
    val expectedCaseIdMatched: Boolean,
    val expectedProviderOperationMetadataKatIdMatched: Boolean,
    val metadataKatSuitePassed: Boolean,
    val providerOperationAdmissionModeled: Boolean,
    val providerOperationMetadataKatEvaluated: Boolean,
    val providerOperationMetadataKatPassed: Boolean,
    val evaluationIsCommonTestOnly: Boolean,
    val evaluationIsProductionAuthorization: Boolean,
    val evaluationIsProviderSelectionAuthorization: Boolean,
    val evaluationIsProviderOperationAuthorization: Boolean,
    val evaluationIsKatExecutorAuthorization: Boolean,
    val evaluationIsCryptoAuthorization: Boolean,
    val evaluationIsVaultPersistenceAuthorization: Boolean,
    val evaluationIsMainnetAuthorization: Boolean,
    val evaluatesProviderOperationShapeOnly: Boolean,
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
    val metadataCategories: List<SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataCategory>,
    val providerOperationShapeLabels: List<SkaldVaultV1TestOnlyProviderIdentityProviderOperationShapeLabel>,
    val sourceSet: SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatSourceSet,
    val displayLabel: SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatSafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatResult(redactedMarkerId, redactedFixtureId, redactedVectorId, redactedCaseId, redactedProviderOperationMetadataKatId, commonTestOnly, providerOperationShapeOnly, nonAuthorizing)"
}

object SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatPolicy {
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

    fun evaluateCurrentProviderOperationMetadataKat():
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatResult {
        val marker = SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy.currentMarker()
        val capabilityMatrix =
            SkaldVaultV1TestOnlyProviderIdentityCapabilityMatrixPolicy.currentCapabilityMatrix()
        val caseBinding =
            SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingPolicy.currentKatCaseBinding()
        val caseBindingValidation =
            SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationPolicy
                .currentKatCaseBindingValidationReport()
        val metadataKat =
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatPolicy.evaluateCurrentMetadataKat()
        val metadataKatValidation =
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationPolicy
                .currentExecutableMetadataKatValidationReport()
        val suiteReport =
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatSuiteReportPolicy
                .currentExecutableMetadataKatSuiteReport()
        val providerOperationAdmission =
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationKatAdmissionPolicy
                .currentProviderOperationKatAdmission()
        val metadataCategories =
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataCategory.entries.toList()
        val providerOperationShapeLabels =
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationShapeLabel.entries.toList()

        val markerCount =
            if (
                marker.safeId.value == EXPECTED_SAFE_ID &&
                capabilityMatrix.expectedSafeIdMatched &&
                caseBinding.markerSafeId.value == EXPECTED_SAFE_ID &&
                caseBindingValidation.expectedSafeIdMatched &&
                metadataKat.expectedSafeIdMatched &&
                metadataKatValidation.expectedSafeIdMatched &&
                suiteReport.expectedSafeIdMatched &&
                providerOperationAdmission.expectedSafeIdMatched
            ) {
                1
            } else {
                0
            }
        val fixtureRowCount = suiteReport.fixtureRowCount
        val publicVectorRowCount = suiteReport.publicVectorRowCount
        val caseBindingCount = suiteReport.caseBindingCount
        val executableMetadataKatSuiteReportCount =
            if (suiteReport.suiteReportGenerated && suiteReport.suitePassed) {
                1
            } else {
                0
            }
        val providerOperationKatAdmissionCount =
            if (
                providerOperationAdmission.futureProviderOperationKatCriteriaModeled &&
                !providerOperationAdmission.futureProviderOperationKatCriteriaAuthorizeCurrentExecution &&
                !providerOperationAdmission.currentProviderOperationKatPresent &&
                !providerOperationAdmission.currentProviderOperationExecutionPresent &&
                !providerOperationAdmission.currentCryptoExecutionPresent
            ) {
                1
            } else {
                0
            }
        val expectedSafeIdMatched = markerCount == 1
        val expectedFixtureIdMatched =
            caseBinding.fixtureId.value == EXPECTED_FIXTURE_ID &&
                caseBinding.expectedFixtureIdMatched &&
                caseBindingValidation.expectedFixtureIdMatched &&
                metadataKat.expectedFixtureIdMatched &&
                metadataKatValidation.expectedFixtureIdMatched &&
                suiteReport.expectedFixtureIdMatched &&
                providerOperationAdmission.expectedFixtureIdMatched
        val expectedVectorIdMatched =
            caseBinding.vectorId.value == EXPECTED_VECTOR_ID &&
                caseBinding.expectedVectorIdMatched &&
                caseBindingValidation.expectedVectorIdMatched &&
                metadataKat.expectedVectorIdMatched &&
                metadataKatValidation.expectedVectorIdMatched &&
                suiteReport.expectedVectorIdMatched &&
                providerOperationAdmission.expectedVectorIdMatched
        val expectedCaseIdMatched =
            caseBinding.caseId.value == EXPECTED_CASE_ID &&
                caseBinding.expectedCaseIdMatched &&
                caseBindingValidation.expectedCaseIdMatched &&
                metadataKat.expectedCaseIdMatched &&
                metadataKatValidation.expectedCaseIdMatched &&
                suiteReport.expectedCaseIdMatched &&
                providerOperationAdmission.expectedCaseIdMatched
        val expectedProviderOperationMetadataKatIdMatched =
            EXPECTED_PROVIDER_OPERATION_METADATA_KAT_ID ==
                "skald-test-only-provider-identity-provider-operation-metadata-kat-v1-inert-identity"
        val metadataKatSuitePassed =
            suiteReport.suitePassed &&
                providerOperationAdmission.metadataKatSuitePassed
        val providerOperationAdmissionModeled =
            providerOperationAdmission.futureProviderOperationKatCriteriaModeled &&
                providerOperationKatAdmissionCount == 1

        val evaluationIsCommonTestOnly =
            marker.sourceSet == SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet.CommonTest &&
                capabilityMatrix.matrixIsCommonTestOnly &&
                caseBinding.caseIsCommonTestOnly &&
                caseBindingValidation.validationIsCommonTestOnly &&
                metadataKat.evaluationIsCommonTestOnly &&
                metadataKatValidation.validationIsCommonTestOnly &&
                suiteReport.suiteIsCommonTestOnly &&
                providerOperationAdmission.admissionIsCommonTestOnly &&
                caseBinding.sourceSet ==
                SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingSourceSet.CommonTest

        val providerOperationKatExecutorPresent =
            providerOperationAdmission.currentKatExecutorPresent ||
                suiteReport.executableKatExecutorPresent ||
                suiteReport.katExecutorPresent ||
                metadataKatValidation.executableKatExecutorPresent ||
                metadataKatValidation.katExecutorPresent ||
                metadataKat.executableKatExecutorPresent ||
                metadataKat.katExecutorPresent
        val providerOperationKatRunnerPresent =
            providerOperationAdmission.currentKatRunnerPresent ||
                suiteReport.katRunnerPresent ||
                metadataKatValidation.katRunnerPresent ||
                metadataKat.katRunnerPresent
        val providerOperationExecutionPresent =
            providerOperationAdmission.currentProviderOperationExecutionPresent ||
                suiteReport.providerOperationExecutionReachable ||
                metadataKatValidation.providerOperationExecutionReachable ||
                metadataKat.providerOperationReachable ||
                caseBinding.providerOperationReachable ||
                caseBindingValidation.providerOperationReachable ||
                capabilityMatrix.providerOperationReachable
        val cryptoExecutionPresent =
            providerOperationAdmission.currentCryptoExecutionPresent ||
                suiteReport.cryptoExecutionReachable ||
                metadataKatValidation.cryptoExecutionReachable ||
                metadataKat.cryptoExecutionReachable ||
                caseBinding.cryptoExecutionReachable ||
                caseBindingValidation.cryptoExecutionReachable ||
                capabilityMatrix.cryptoExecutionReachable
        val rawKatMaterialPresent =
            providerOperationAdmission.rawKatMaterialPresent ||
                suiteReport.rawKatMaterialPresent ||
                metadataKatValidation.rawKatMaterialPresent ||
                metadataKat.rawKatMaterialPresent ||
                caseBindingValidation.rawKatMaterialPresent ||
                caseBinding.caseContainsRawBytes
        val rawVectorBytesPresent =
            providerOperationAdmission.rawVectorBytesPresent ||
                suiteReport.rawVectorBytesPresent ||
                metadataKatValidation.rawVectorBytesPresent ||
                metadataKat.rawVectorBytesPresent ||
                caseBindingValidation.rawVectorBytesPresent
        val rawVectorHexPresent =
            providerOperationAdmission.rawVectorHexPresent ||
                suiteReport.rawVectorHexPresent ||
                metadataKatValidation.rawVectorHexPresent ||
                metadataKat.rawVectorHexPresent ||
                caseBindingValidation.rawVectorHexPresent ||
                caseBinding.caseContainsHex
        val publicVectorBytesPresent =
            providerOperationAdmission.publicVectorBytesPresent ||
                suiteReport.publicVectorBytesPresent ||
                metadataKatValidation.publicVectorBytesPresent ||
                metadataKat.publicVectorBytesPresent ||
                caseBindingValidation.publicVectorBytesPresent
        val publicVectorHexPresent =
            providerOperationAdmission.publicVectorHexPresent ||
                suiteReport.publicVectorHexPresent ||
                metadataKatValidation.publicVectorHexPresent ||
                metadataKat.publicVectorHexPresent ||
                caseBindingValidation.publicVectorHexPresent

        val runtimeSelectable =
            providerOperationAdmission.runtimeSelectable ||
                suiteReport.runtimeSelectable ||
                metadataKatValidation.runtimeSelectable ||
                metadataKat.runtimeSelectable ||
                caseBinding.runtimeSelectable ||
                caseBindingValidation.runtimeSelectable ||
                capabilityMatrix.runtimeSelectable
        val registrySelectable =
            providerOperationAdmission.registrySelectable ||
                suiteReport.registrySelectable ||
                metadataKatValidation.registrySelectable ||
                metadataKat.registrySelectable ||
                caseBinding.registrySelectable ||
                caseBindingValidation.registrySelectable ||
                capabilityMatrix.registrySelectable
        val factoryReachable =
            providerOperationAdmission.factoryReachable ||
                suiteReport.factoryReachable ||
                metadataKatValidation.factoryReachable ||
                metadataKat.factoryReachable ||
                caseBinding.factoryReachable ||
                caseBindingValidation.factoryReachable ||
                capabilityMatrix.factoryReachable
        val dispatcherReachable =
            providerOperationAdmission.dispatcherReachable ||
                suiteReport.dispatcherReachable ||
                metadataKatValidation.dispatcherReachable ||
                metadataKat.dispatcherReachable ||
                caseBinding.dispatcherReachable ||
                caseBindingValidation.dispatcherReachable ||
                capabilityMatrix.dispatcherReachable
        val executorTargetable =
            providerOperationAdmission.executorTargetable ||
                suiteReport.executorTargetable ||
                metadataKatValidation.executorTargetable ||
                metadataKat.executorTargetable ||
                caseBinding.executorTargetable ||
                caseBindingValidation.executorTargetable ||
                capabilityMatrix.executorTargetable
        val providerKatExecutorReachable =
            providerOperationAdmission.providerKatExecutorReachable ||
                suiteReport.providerKatExecutorReachable ||
                metadataKatValidation.providerKatExecutorReachable ||
                metadataKat.providerKatExecutorReachable ||
                caseBinding.providerKatExecutorReachable ||
                caseBindingValidation.providerKatExecutorReachable ||
                capabilityMatrix.providerKatExecutorReachable
        val providerOperationReachable =
            providerOperationAdmission.providerOperationReachable ||
                providerOperationExecutionPresent
        val cryptoExecutionReachable =
            providerOperationAdmission.cryptoExecutionReachable ||
                cryptoExecutionPresent
        val vaultLifecycleReachable =
            providerOperationAdmission.vaultLifecycleReachable ||
                suiteReport.vaultLifecycleReachable ||
                metadataKatValidation.vaultLifecycleReachable ||
                metadataKat.vaultLifecycleReachable ||
                caseBinding.vaultLifecycleReachable ||
                caseBindingValidation.vaultLifecycleReachable ||
                capabilityMatrix.vaultLifecycleReachable
        val persistenceReachable =
            providerOperationAdmission.persistenceReachable ||
                suiteReport.persistenceReachable ||
                metadataKatValidation.persistenceReachable ||
                metadataKat.persistenceReachable ||
                caseBinding.persistenceReachable ||
                caseBindingValidation.persistenceReachable ||
                capabilityMatrix.persistenceReachable
        val productionSyncReachable =
            providerOperationAdmission.productionSyncReachable ||
                suiteReport.productionSyncReachable ||
                metadataKatValidation.productionSyncReachable ||
                metadataKat.productionSyncReachable ||
                caseBinding.productionSyncReachable ||
                caseBindingValidation.productionSyncReachable ||
                capabilityMatrix.productionSyncReachable
        val backendClientReachable =
            providerOperationAdmission.backendClientReachable ||
                suiteReport.backendClientReachable ||
                metadataKatValidation.backendClientReachable ||
                metadataKat.backendClientReachable ||
                caseBinding.backendClientReachable ||
                caseBindingValidation.backendClientReachable ||
                capabilityMatrix.backendClientReachable
        val bdkWalletStateReachable =
            providerOperationAdmission.bdkWalletStateReachable ||
                suiteReport.bdkWalletStateReachable ||
                metadataKatValidation.bdkWalletStateReachable ||
                metadataKat.bdkWalletStateReachable ||
                caseBinding.bdkWalletStateReachable ||
                caseBindingValidation.bdkWalletStateReachable ||
                capabilityMatrix.bdkWalletStateReachable
        val settingsCodecReachable =
            providerOperationAdmission.settingsCodecReachable ||
                suiteReport.settingsCodecReachable ||
                metadataKatValidation.settingsCodecReachable ||
                metadataKat.settingsCodecReachable ||
                caseBinding.settingsCodecReachable ||
                caseBindingValidation.settingsCodecReachable ||
                capabilityMatrix.settingsCodecReachable
        val uiSurfaceReachable =
            providerOperationAdmission.uiSurfaceReachable ||
                suiteReport.uiSurfaceReachable ||
                metadataKatValidation.uiSurfaceReachable ||
                metadataKat.uiSurfaceReachable ||
                caseBinding.uiSurfaceReachable ||
                caseBindingValidation.uiSurfaceReachable ||
                capabilityMatrix.uiSurfaceReachable
        val signingBroadcastingReachable =
            providerOperationAdmission.signingBroadcastingReachable ||
                suiteReport.signingBroadcastingReachable ||
                metadataKatValidation.signingBroadcastingReachable ||
                metadataKat.signingBroadcastingReachable ||
                caseBinding.signingBroadcastingReachable ||
                caseBindingValidation.signingBroadcastingReachable ||
                capabilityMatrix.signingBroadcastingReachable
        val publicEndpointReachable =
            providerOperationAdmission.publicEndpointReachable ||
                suiteReport.publicEndpointReachable ||
                metadataKatValidation.publicEndpointReachable ||
                metadataKat.publicEndpointReachable ||
                caseBinding.publicEndpointReachable ||
                caseBindingValidation.publicEndpointReachable ||
                capabilityMatrix.publicEndpointReachable
        val mainnetReachable =
            providerOperationAdmission.mainnetReachable ||
                suiteReport.mainnetReachable ||
                metadataKatValidation.mainnetReachable ||
                metadataKat.mainnetReachable ||
                caseBinding.mainnetReachable ||
                caseBindingValidation.mainnetReachable ||
                capabilityMatrix.mainnetReachable
        val implementsVaultCryptoProvider =
            providerOperationAdmission.implementsVaultCryptoProvider ||
                suiteReport.implementsVaultCryptoProvider ||
                metadataKatValidation.implementsVaultCryptoProvider ||
                metadataKat.implementsVaultCryptoProvider ||
                caseBinding.implementsVaultCryptoProvider ||
                caseBindingValidation.implementsVaultCryptoProvider ||
                capabilityMatrix.implementsVaultCryptoProvider
        val containsVaultCryptoProvider =
            providerOperationAdmission.containsVaultCryptoProvider ||
                suiteReport.containsVaultCryptoProvider ||
                metadataKatValidation.containsVaultCryptoProvider ||
                metadataKat.containsVaultCryptoProvider ||
                caseBinding.containsVaultCryptoProvider ||
                caseBindingValidation.containsVaultCryptoProvider ||
                capabilityMatrix.containsVaultCryptoProvider
        val canExecuteProviderOperations =
            providerOperationAdmission.canExecuteProviderOperations ||
                suiteReport.canExecuteProviderOperations ||
                metadataKatValidation.canExecuteProviderOperations ||
                metadataKat.canExecuteProviderOperations ||
                caseBinding.canExecuteProviderOperations ||
                caseBindingValidation.canExecuteProviderOperations ||
                capabilityMatrix.canExecuteProviderOperations
        val canExecuteCrypto =
            providerOperationAdmission.canExecuteCrypto ||
                suiteReport.canExecuteCrypto ||
                metadataKatValidation.canExecuteCrypto ||
                metadataKat.canExecuteCrypto ||
                caseBinding.canExecuteCrypto ||
                caseBindingValidation.canExecuteCrypto ||
                capabilityMatrix.canExecuteCrypto
        val canUseForVaultLifecycle =
            providerOperationAdmission.canUseForVaultLifecycle ||
                suiteReport.canUseForVaultLifecycle ||
                metadataKatValidation.canUseForVaultLifecycle ||
                metadataKat.canUseForVaultLifecycle ||
                caseBinding.canUseForVaultLifecycle ||
                caseBindingValidation.canUseForVaultLifecycle ||
                capabilityMatrix.canUseForVaultLifecycle
        val canUseForPersistence =
            providerOperationAdmission.canUseForPersistence ||
                suiteReport.canUseForPersistence ||
                metadataKatValidation.canUseForPersistence ||
                metadataKat.canUseForPersistence ||
                caseBinding.canUseForPersistence ||
                caseBindingValidation.canUseForPersistence ||
                capabilityMatrix.canUseForPersistence
        val canUseForSync =
            providerOperationAdmission.canUseForSync ||
                suiteReport.canUseForSync ||
                metadataKatValidation.canUseForSync ||
                metadataKat.canUseForSync ||
                caseBinding.canUseForSync ||
                caseBindingValidation.canUseForSync ||
                capabilityMatrix.canUseForSync
        val canUseForSigning =
            providerOperationAdmission.canUseForSigning ||
                suiteReport.canUseForSigning ||
                metadataKatValidation.canUseForSigning ||
                metadataKat.canUseForSigning ||
                caseBinding.canUseForSigning ||
                caseBindingValidation.canUseForSigning ||
                capabilityMatrix.canUseForSigning
        val canUseForBroadcasting =
            providerOperationAdmission.canUseForBroadcasting ||
                suiteReport.canUseForBroadcasting ||
                metadataKatValidation.canUseForBroadcasting ||
                metadataKat.canUseForBroadcasting ||
                caseBinding.canUseForBroadcasting ||
                caseBindingValidation.canUseForBroadcasting ||
                capabilityMatrix.canUseForBroadcasting
        val canUseForMainnet =
            providerOperationAdmission.canUseForMainnet ||
                suiteReport.canUseForMainnet ||
                metadataKatValidation.canUseForMainnet ||
                metadataKat.canUseForMainnet ||
                caseBinding.canUseForMainnet ||
                caseBindingValidation.canUseForMainnet ||
                capabilityMatrix.canUseForMainnet
        val productionProviderSelectable =
            providerOperationAdmission.productionProviderSelectable ||
                suiteReport.productionProviderSelectable ||
                metadataKatValidation.productionProviderSelectable ||
                metadataKat.productionProviderSelectable ||
                caseBinding.productionProviderSelectable ||
                caseBindingValidation.productionProviderSelectable ||
                capabilityMatrix.productionProviderSelectable ||
                marker.productionProviderSelectable

        val evaluatesProviderOperationShapeOnly =
            metadataCategories.size ==
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataCategory.entries.size &&
                providerOperationShapeLabels.size ==
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationShapeLabel.entries.size &&
                metadataKatSuitePassed &&
                providerOperationAdmissionModeled &&
                !providerOperationAdmission.admissionIsProviderOperationAuthorization &&
                !suiteReport.suiteReportsProviderOperation &&
                !metadataKatValidation.validatesProviderOperation &&
                !metadataKat.evaluatesProviderOperation &&
                !caseBinding.canExecuteProviderOperations &&
                !caseBindingValidation.canExecuteProviderOperations
        val evaluatesProviderOperationExecution = providerOperationExecutionPresent
        val evaluatesCryptoOperation = cryptoExecutionPresent
        val evaluatesVaultLifecycle = vaultLifecycleReachable
        val evaluatesPersistence = persistenceReachable

        val providerOperationMetadataKatEvaluated =
            evaluationIsCommonTestOnly &&
                expectedProviderOperationMetadataKatIdMatched &&
                metadataKatSuitePassed &&
                providerOperationAdmissionModeled
        val providerOperationMetadataKatPassed =
            providerOperationMetadataKatEvaluated &&
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
                evaluatesProviderOperationShapeOnly &&
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

        return SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatResult(
            markerCount = markerCount,
            fixtureRowCount = fixtureRowCount,
            publicVectorRowCount = publicVectorRowCount,
            caseBindingCount = caseBindingCount,
            executableMetadataKatSuiteReportCount = executableMetadataKatSuiteReportCount,
            providerOperationKatAdmissionCount = providerOperationKatAdmissionCount,
            expectedSafeIdMatched = expectedSafeIdMatched,
            expectedFixtureIdMatched = expectedFixtureIdMatched,
            expectedVectorIdMatched = expectedVectorIdMatched,
            expectedCaseIdMatched = expectedCaseIdMatched,
            expectedProviderOperationMetadataKatIdMatched = expectedProviderOperationMetadataKatIdMatched,
            metadataKatSuitePassed = metadataKatSuitePassed,
            providerOperationAdmissionModeled = providerOperationAdmissionModeled,
            providerOperationMetadataKatEvaluated = providerOperationMetadataKatEvaluated,
            providerOperationMetadataKatPassed = providerOperationMetadataKatPassed,
            evaluationIsCommonTestOnly = evaluationIsCommonTestOnly,
            evaluationIsProductionAuthorization = false,
            evaluationIsProviderSelectionAuthorization = false,
            evaluationIsProviderOperationAuthorization = false,
            evaluationIsKatExecutorAuthorization = false,
            evaluationIsCryptoAuthorization = false,
            evaluationIsVaultPersistenceAuthorization = false,
            evaluationIsMainnetAuthorization = false,
            evaluatesProviderOperationShapeOnly = evaluatesProviderOperationShapeOnly,
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
            metadataCategories = metadataCategories,
            providerOperationShapeLabels = providerOperationShapeLabels,
            sourceSet =
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatSourceSet.CommonTest,
            displayLabel = SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatSafeLabel(
                "provider-operation metadata KAT evidence",
            ),
        )
    }
}
