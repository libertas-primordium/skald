package com.libertasprimordium.skald.security

data class SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatResult(
    val markerCount: Int,
    val fixtureRowCount: Int,
    val publicVectorRowCount: Int,
    val caseBindingCount: Int,
    val caseBindingValidationCount: Int,
    val executableKatAdmissionCount: Int,
    val expectedSafeIdMatched: Boolean,
    val expectedFixtureIdMatched: Boolean,
    val expectedVectorIdMatched: Boolean,
    val expectedCaseIdMatched: Boolean,
    val metadataKatEvaluated: Boolean,
    val metadataKatPassed: Boolean,
    val evaluationIsCommonTestOnly: Boolean,
    val evaluationIsProductionAuthorization: Boolean,
    val evaluationIsProviderSelectionAuthorization: Boolean,
    val evaluationIsKatExecutionAuthorization: Boolean,
    val evaluationIsCryptoAuthorization: Boolean,
    val evaluationIsVaultPersistenceAuthorization: Boolean,
    val evaluationIsMainnetAuthorization: Boolean,
    val evaluatesMetadataOnly: Boolean,
    val evaluatesPublicNonSecretTextOnlyVector: Boolean,
    val evaluatesProviderOperation: Boolean,
    val evaluatesCryptoOperation: Boolean,
    val evaluatesVaultLifecycle: Boolean,
    val evaluatesPersistence: Boolean,
    val caseBindingIsMetadataOnly: Boolean,
    val caseBindingIsExecutable: Boolean,
    val caseBindingContainsRawBytes: Boolean,
    val caseBindingContainsHex: Boolean,
    val caseBindingContainsCryptoMaterial: Boolean,
    val caseBindingContainsWalletMaterial: Boolean,
    val caseBindingContainsEndpointMaterial: Boolean,
    val caseBindingContainsProviderHandle: Boolean,
    val rawKatMaterialPresent: Boolean,
    val rawVectorBytesPresent: Boolean,
    val rawVectorHexPresent: Boolean,
    val publicVectorBytesPresent: Boolean,
    val publicVectorHexPresent: Boolean,
    val executableKatExecutorPresent: Boolean,
    val katRunnerPresent: Boolean,
    val katExecutorPresent: Boolean,
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
        "SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatResult(redactedMarkerId, redactedFixtureId, redactedVectorId, redactedCaseId, commonTestOnly, metadataEvaluationOnly, nonAuthorizing)"
}

object SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatPolicy {
    private const val EXPECTED_SAFE_ID: String =
        "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"
    private const val EXPECTED_FIXTURE_ID: String =
        "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata"
    private const val EXPECTED_VECTOR_ID: String =
        "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata"
    private const val EXPECTED_CASE_ID: String =
        "skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata"

    fun evaluateCurrentMetadataKat(): SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatResult {
        val marker = SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy.currentMarker()
        val capabilityMatrix =
            SkaldVaultV1TestOnlyProviderIdentityCapabilityMatrixPolicy.currentCapabilityMatrix()
        val fixtureScope =
            SkaldVaultV1TestOnlyProviderIdentityKatFixtureScopePolicy.currentKatFixtureScope()
        val fixtureCatalog =
            SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalogPolicy.currentKatFixtureCatalog()
        val fixtureValidation =
            SkaldVaultV1TestOnlyProviderIdentityKatFixtureValidationPolicy.currentKatFixtureValidationReport()
        val publicVectorFixture =
            SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorFixturePolicy.currentPublicVectorFixture()
        val publicVectorValidation =
            SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationPolicy
                .currentPublicVectorValidationReport()
        val caseBinding =
            SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingPolicy.currentKatCaseBinding()
        val caseBindingValidation =
            SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationPolicy
                .currentKatCaseBindingValidationReport()
        val executableAdmission =
            SkaldVaultV1TestOnlyProviderIdentityExecutableKatAdmissionPolicy.currentExecutableKatAdmission()
        val fixtureRow = fixtureCatalog.fixtureRows.singleOrNull()
        val publicVectorRow = publicVectorFixture.publicVectorRows.singleOrNull()

        val markerCount =
            if (
                marker.safeId.value == EXPECTED_SAFE_ID &&
                capabilityMatrix.expectedSafeIdMatched &&
                fixtureScope.expectedSafeIdMatched &&
                fixtureCatalog.expectedSafeIdMatched &&
                fixtureValidation.expectedSafeIdMatched &&
                publicVectorFixture.expectedSafeIdMatched &&
                publicVectorValidation.expectedSafeIdMatched &&
                caseBinding.expectedSafeIdMatched &&
                caseBindingValidation.expectedSafeIdMatched &&
                executableAdmission.expectedSafeIdMatched &&
                fixtureRow?.markerSafeId?.value == EXPECTED_SAFE_ID &&
                publicVectorRow?.markerSafeId?.value == EXPECTED_SAFE_ID &&
                caseBinding.markerSafeId.value == EXPECTED_SAFE_ID
            ) {
                1
            } else {
                0
            }
        val fixtureRowCount = fixtureCatalog.fixtureRows.size
        val publicVectorRowCount = publicVectorFixture.publicVectorRows.size
        val caseBindingCount = caseBinding.caseCount
        val caseBindingValidationCount =
            if (caseBindingValidation.caseBindingCount == 1 && caseBindingValidation.allValidationChecksPassed) {
                1
            } else {
                0
            }
        val executableKatAdmissionCount =
            if (
                executableAdmission.futureExecutableKatCriteriaModeled &&
                !executableAdmission.futureExecutableKatCriteriaAuthorizeCurrentExecution &&
                !executableAdmission.currentExecutableKatPresent &&
                !executableAdmission.currentKatRunnerPresent &&
                !executableAdmission.currentKatExecutorPresent
            ) {
                1
            } else {
                0
            }
        val expectedSafeIdMatched = markerCount == 1
        val expectedFixtureIdMatched =
            fixtureValidation.expectedFixtureIdMatched &&
                publicVectorFixture.expectedFixtureIdMatched &&
                publicVectorValidation.expectedFixtureIdMatched &&
                caseBinding.expectedFixtureIdMatched &&
                caseBindingValidation.expectedFixtureIdMatched &&
                executableAdmission.expectedFixtureIdMatched &&
                fixtureRow?.fixtureId?.value == EXPECTED_FIXTURE_ID &&
                publicVectorRow?.fixtureId?.value == EXPECTED_FIXTURE_ID &&
                caseBinding.fixtureId.value == EXPECTED_FIXTURE_ID
        val expectedVectorIdMatched =
            publicVectorFixture.expectedVectorIdMatched &&
                publicVectorValidation.expectedVectorIdMatched &&
                caseBinding.expectedVectorIdMatched &&
                caseBindingValidation.expectedVectorIdMatched &&
                executableAdmission.expectedVectorIdMatched &&
                publicVectorRow?.vectorId?.value == EXPECTED_VECTOR_ID &&
                caseBinding.vectorId.value == EXPECTED_VECTOR_ID
        val expectedCaseIdMatched =
            caseBinding.expectedCaseIdMatched &&
                caseBindingValidation.expectedCaseIdMatched &&
                executableAdmission.expectedCaseIdMatched &&
                caseBinding.caseId.value == EXPECTED_CASE_ID
        val evaluationIsCommonTestOnly =
            marker.sourceSet == SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet.CommonTest &&
                capabilityMatrix.matrixIsCommonTestOnly &&
                fixtureScope.fixtureScopeIsCommonTestOnly &&
                fixtureCatalog.catalogIsCommonTestOnly &&
                fixtureValidation.validationIsCommonTestOnly &&
                publicVectorFixture.fixtureIsCommonTestOnly &&
                publicVectorValidation.validationIsCommonTestOnly &&
                caseBinding.caseIsCommonTestOnly &&
                caseBindingValidation.validationIsCommonTestOnly &&
                executableAdmission.admissionIsCommonTestOnly &&
                caseBinding.sourceSet ==
                SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingSourceSet.CommonTest
        val evaluatesMetadataOnly =
            caseBinding.caseIsMetadataOnly &&
                caseBindingValidation.caseBindingIsMetadataOnly &&
                caseBindingValidation.allValidationChecksPassed
        val evaluatesPublicNonSecretTextOnlyVector =
            publicVectorFixture.vectorIsPublicAndNonSecret &&
                publicVectorFixture.vectorIsTextOnly &&
                publicVectorValidation.vectorIsPublicAndNonSecret &&
                publicVectorValidation.vectorIsTextOnly &&
                publicVectorValidation.allValidationChecksPassed
        val caseBindingIsExecutable =
            caseBinding.caseIsExecutable ||
                caseBindingValidation.caseBindingIsExecutable
        val caseBindingContainsRawBytes =
            caseBinding.caseContainsRawBytes ||
                caseBindingValidation.caseBindingContainsRawBytes
        val caseBindingContainsHex =
            caseBinding.caseContainsHex ||
                caseBindingValidation.caseBindingContainsHex
        val caseBindingContainsCryptoMaterial =
            caseBinding.caseContainsCryptoMaterial ||
                caseBindingValidation.caseBindingContainsCryptoMaterial
        val caseBindingContainsWalletMaterial =
            caseBinding.caseContainsWalletMaterial ||
                caseBindingValidation.caseBindingContainsWalletMaterial
        val caseBindingContainsEndpointMaterial =
            caseBinding.caseContainsEndpointMaterial ||
                caseBindingValidation.caseBindingContainsEndpointMaterial
        val caseBindingContainsProviderHandle =
            caseBinding.caseContainsProviderHandle ||
                caseBindingValidation.caseBindingContainsProviderHandle
        val rawKatMaterialPresent =
            fixtureScope.rawKatMaterialPresent ||
                fixtureCatalog.rawKatMaterialPresent ||
                fixtureValidation.rawKatMaterialPresent ||
                publicVectorValidation.rawKatMaterialPresent ||
                caseBindingValidation.rawKatMaterialPresent ||
                executableAdmission.rawKatMaterialPresent
        val rawVectorBytesPresent =
            fixtureCatalog.rawVectorBytesPresent ||
                fixtureValidation.rawVectorBytesPresent ||
                publicVectorValidation.rawVectorBytesPresent ||
                caseBindingValidation.rawVectorBytesPresent ||
                executableAdmission.rawVectorBytesPresent
        val rawVectorHexPresent =
            fixtureCatalog.rawVectorHexPresent ||
                fixtureValidation.rawVectorHexPresent ||
                publicVectorValidation.rawVectorHexPresent ||
                caseBindingValidation.rawVectorHexPresent ||
                executableAdmission.rawVectorHexPresent
        val publicVectorBytesPresent =
            publicVectorValidation.publicVectorBytesPresent ||
                caseBindingValidation.publicVectorBytesPresent ||
                executableAdmission.publicVectorBytesPresent
        val publicVectorHexPresent =
            publicVectorValidation.publicVectorHexPresent ||
                caseBindingValidation.publicVectorHexPresent ||
                executableAdmission.publicVectorHexPresent
        val executableKatExecutorPresent =
            fixtureScope.katExecutorPresent ||
                fixtureCatalog.katExecutorPresent ||
                fixtureValidation.katExecutorPresent ||
                publicVectorValidation.katExecutorPresent ||
                caseBindingValidation.katExecutorPresent ||
                executableAdmission.currentKatExecutorPresent ||
                executableAdmission.katExecutorPresent
        val katRunnerPresent = executableAdmission.currentKatRunnerPresent
        val providerOperationReachable =
            capabilityMatrix.providerOperationReachable ||
                fixtureScope.providerOperationReachable ||
                fixtureCatalog.providerOperationReachable ||
                fixtureValidation.providerOperationReachable ||
                publicVectorFixture.providerOperationReachable ||
                publicVectorValidation.providerOperationReachable ||
                caseBinding.providerOperationReachable ||
                caseBindingValidation.providerOperationReachable ||
                executableAdmission.providerOperationReachable
        val cryptoExecutionReachable =
            capabilityMatrix.cryptoExecutionReachable ||
                fixtureScope.cryptoExecutionReachable ||
                fixtureCatalog.cryptoExecutionReachable ||
                fixtureValidation.cryptoExecutionReachable ||
                publicVectorFixture.cryptoExecutionReachable ||
                publicVectorValidation.cryptoExecutionReachable ||
                caseBinding.cryptoExecutionReachable ||
                caseBindingValidation.cryptoExecutionReachable ||
                executableAdmission.cryptoExecutionReachable
        val runtimeSelectable =
            capabilityMatrix.runtimeSelectable ||
                fixtureScope.runtimeSelectable ||
                fixtureCatalog.runtimeSelectable ||
                fixtureValidation.runtimeSelectable ||
                publicVectorFixture.runtimeSelectable ||
                publicVectorValidation.runtimeSelectable ||
                caseBinding.runtimeSelectable ||
                caseBindingValidation.runtimeSelectable ||
                executableAdmission.runtimeSelectable
        val registrySelectable =
            capabilityMatrix.registrySelectable ||
                fixtureScope.registrySelectable ||
                fixtureCatalog.registrySelectable ||
                fixtureValidation.registrySelectable ||
                publicVectorFixture.registrySelectable ||
                publicVectorValidation.registrySelectable ||
                caseBinding.registrySelectable ||
                caseBindingValidation.registrySelectable ||
                executableAdmission.registrySelectable
        val factoryReachable =
            capabilityMatrix.factoryReachable ||
                fixtureScope.factoryReachable ||
                fixtureCatalog.factoryReachable ||
                fixtureValidation.factoryReachable ||
                publicVectorFixture.factoryReachable ||
                publicVectorValidation.factoryReachable ||
                caseBinding.factoryReachable ||
                caseBindingValidation.factoryReachable ||
                executableAdmission.factoryReachable
        val dispatcherReachable =
            capabilityMatrix.dispatcherReachable ||
                fixtureScope.dispatcherReachable ||
                fixtureCatalog.dispatcherReachable ||
                fixtureValidation.dispatcherReachable ||
                publicVectorFixture.dispatcherReachable ||
                publicVectorValidation.dispatcherReachable ||
                caseBinding.dispatcherReachable ||
                caseBindingValidation.dispatcherReachable ||
                executableAdmission.dispatcherReachable
        val executorTargetable =
            capabilityMatrix.executorTargetable ||
                fixtureScope.executorTargetable ||
                fixtureCatalog.executorTargetable ||
                fixtureValidation.executorTargetable ||
                publicVectorFixture.executorTargetable ||
                publicVectorValidation.executorTargetable ||
                caseBinding.executorTargetable ||
                caseBindingValidation.executorTargetable ||
                executableAdmission.executorTargetable
        val providerKatExecutorReachable =
            capabilityMatrix.providerKatExecutorReachable ||
                fixtureScope.providerKatExecutorReachable ||
                fixtureCatalog.providerKatExecutorReachable ||
                fixtureValidation.providerKatExecutorReachable ||
                publicVectorFixture.providerKatExecutorReachable ||
                publicVectorValidation.providerKatExecutorReachable ||
                caseBinding.providerKatExecutorReachable ||
                caseBindingValidation.providerKatExecutorReachable ||
                executableAdmission.providerKatExecutorReachable
        val vaultLifecycleReachable =
            capabilityMatrix.vaultLifecycleReachable ||
                fixtureScope.vaultLifecycleReachable ||
                fixtureCatalog.vaultLifecycleReachable ||
                fixtureValidation.vaultLifecycleReachable ||
                publicVectorFixture.vaultLifecycleReachable ||
                publicVectorValidation.vaultLifecycleReachable ||
                caseBinding.vaultLifecycleReachable ||
                caseBindingValidation.vaultLifecycleReachable ||
                executableAdmission.vaultLifecycleReachable
        val persistenceReachable =
            capabilityMatrix.persistenceReachable ||
                fixtureScope.persistenceReachable ||
                fixtureCatalog.persistenceReachable ||
                fixtureValidation.persistenceReachable ||
                publicVectorFixture.persistenceReachable ||
                publicVectorValidation.persistenceReachable ||
                caseBinding.persistenceReachable ||
                caseBindingValidation.persistenceReachable ||
                executableAdmission.persistenceReachable
        val productionSyncReachable =
            capabilityMatrix.productionSyncReachable ||
                fixtureScope.productionSyncReachable ||
                fixtureCatalog.productionSyncReachable ||
                fixtureValidation.productionSyncReachable ||
                publicVectorFixture.productionSyncReachable ||
                publicVectorValidation.productionSyncReachable ||
                caseBinding.productionSyncReachable ||
                caseBindingValidation.productionSyncReachable ||
                executableAdmission.productionSyncReachable
        val backendClientReachable =
            capabilityMatrix.backendClientReachable ||
                fixtureScope.backendClientReachable ||
                fixtureCatalog.backendClientReachable ||
                fixtureValidation.backendClientReachable ||
                publicVectorFixture.backendClientReachable ||
                publicVectorValidation.backendClientReachable ||
                caseBinding.backendClientReachable ||
                caseBindingValidation.backendClientReachable ||
                executableAdmission.backendClientReachable
        val bdkWalletStateReachable =
            capabilityMatrix.bdkWalletStateReachable ||
                fixtureScope.bdkWalletStateReachable ||
                fixtureCatalog.bdkWalletStateReachable ||
                fixtureValidation.bdkWalletStateReachable ||
                publicVectorFixture.bdkWalletStateReachable ||
                publicVectorValidation.bdkWalletStateReachable ||
                caseBinding.bdkWalletStateReachable ||
                caseBindingValidation.bdkWalletStateReachable ||
                executableAdmission.bdkWalletStateReachable
        val settingsCodecReachable =
            capabilityMatrix.settingsCodecReachable ||
                fixtureScope.settingsCodecReachable ||
                fixtureCatalog.settingsCodecReachable ||
                fixtureValidation.settingsCodecReachable ||
                publicVectorFixture.settingsCodecReachable ||
                publicVectorValidation.settingsCodecReachable ||
                caseBinding.settingsCodecReachable ||
                caseBindingValidation.settingsCodecReachable ||
                executableAdmission.settingsCodecReachable
        val uiSurfaceReachable =
            capabilityMatrix.uiSurfaceReachable ||
                fixtureScope.uiSurfaceReachable ||
                fixtureCatalog.uiSurfaceReachable ||
                fixtureValidation.uiSurfaceReachable ||
                publicVectorFixture.uiSurfaceReachable ||
                publicVectorValidation.uiSurfaceReachable ||
                caseBinding.uiSurfaceReachable ||
                caseBindingValidation.uiSurfaceReachable ||
                executableAdmission.uiSurfaceReachable
        val signingBroadcastingReachable =
            capabilityMatrix.signingBroadcastingReachable ||
                fixtureScope.signingBroadcastingReachable ||
                fixtureCatalog.signingBroadcastingReachable ||
                fixtureValidation.signingBroadcastingReachable ||
                publicVectorFixture.signingBroadcastingReachable ||
                publicVectorValidation.signingBroadcastingReachable ||
                caseBinding.signingBroadcastingReachable ||
                caseBindingValidation.signingBroadcastingReachable ||
                executableAdmission.signingBroadcastingReachable
        val publicEndpointReachable =
            capabilityMatrix.publicEndpointReachable ||
                fixtureScope.publicEndpointReachable ||
                fixtureCatalog.publicEndpointReachable ||
                fixtureValidation.publicEndpointReachable ||
                publicVectorFixture.publicEndpointReachable ||
                publicVectorValidation.publicEndpointReachable ||
                caseBinding.publicEndpointReachable ||
                caseBindingValidation.publicEndpointReachable ||
                executableAdmission.publicEndpointReachable
        val mainnetReachable =
            capabilityMatrix.mainnetReachable ||
                fixtureScope.mainnetReachable ||
                fixtureCatalog.mainnetReachable ||
                fixtureValidation.mainnetReachable ||
                publicVectorFixture.mainnetReachable ||
                publicVectorValidation.mainnetReachable ||
                caseBinding.mainnetReachable ||
                caseBindingValidation.mainnetReachable ||
                executableAdmission.mainnetReachable
        val implementsVaultCryptoProvider =
            capabilityMatrix.implementsVaultCryptoProvider ||
                fixtureScope.implementsVaultCryptoProvider ||
                fixtureCatalog.implementsVaultCryptoProvider ||
                fixtureValidation.implementsVaultCryptoProvider ||
                publicVectorFixture.implementsVaultCryptoProvider ||
                publicVectorValidation.implementsVaultCryptoProvider ||
                caseBinding.implementsVaultCryptoProvider ||
                caseBindingValidation.implementsVaultCryptoProvider ||
                executableAdmission.implementsVaultCryptoProvider
        val containsVaultCryptoProvider =
            capabilityMatrix.containsVaultCryptoProvider ||
                fixtureScope.containsVaultCryptoProvider ||
                fixtureCatalog.containsVaultCryptoProvider ||
                fixtureValidation.containsVaultCryptoProvider ||
                publicVectorFixture.containsVaultCryptoProvider ||
                publicVectorValidation.containsVaultCryptoProvider ||
                caseBinding.containsVaultCryptoProvider ||
                caseBindingValidation.containsVaultCryptoProvider ||
                executableAdmission.containsVaultCryptoProvider
        val canExecuteProviderOperations =
            capabilityMatrix.canExecuteProviderOperations ||
                fixtureCatalog.canExecuteProviderOperations ||
                fixtureValidation.canExecuteProviderOperations ||
                publicVectorFixture.canExecuteProviderOperations ||
                publicVectorValidation.canExecuteProviderOperations ||
                caseBinding.canExecuteProviderOperations ||
                caseBindingValidation.canExecuteProviderOperations ||
                executableAdmission.canExecuteProviderOperations
        val canExecuteCrypto =
            capabilityMatrix.canExecuteCrypto ||
                fixtureCatalog.canExecuteCrypto ||
                fixtureValidation.canExecuteCrypto ||
                publicVectorFixture.canExecuteCrypto ||
                publicVectorValidation.canExecuteCrypto ||
                caseBinding.canExecuteCrypto ||
                caseBindingValidation.canExecuteCrypto ||
                executableAdmission.canExecuteCrypto
        val canUseForVaultLifecycle =
            capabilityMatrix.canUseForVaultLifecycle ||
                fixtureCatalog.canUseForVaultLifecycle ||
                fixtureValidation.canUseForVaultLifecycle ||
                publicVectorFixture.canUseForVaultLifecycle ||
                publicVectorValidation.canUseForVaultLifecycle ||
                caseBinding.canUseForVaultLifecycle ||
                caseBindingValidation.canUseForVaultLifecycle ||
                executableAdmission.canUseForVaultLifecycle
        val canUseForPersistence =
            capabilityMatrix.canUseForPersistence ||
                fixtureCatalog.canUseForPersistence ||
                fixtureValidation.canUseForPersistence ||
                publicVectorFixture.canUseForPersistence ||
                publicVectorValidation.canUseForPersistence ||
                caseBinding.canUseForPersistence ||
                caseBindingValidation.canUseForPersistence ||
                executableAdmission.canUseForPersistence
        val canUseForSync =
            capabilityMatrix.canUseForSync ||
                fixtureCatalog.canUseForSync ||
                fixtureValidation.canUseForSync ||
                publicVectorFixture.canUseForSync ||
                publicVectorValidation.canUseForSync ||
                caseBinding.canUseForSync ||
                caseBindingValidation.canUseForSync ||
                executableAdmission.canUseForSync
        val canUseForSigning =
            capabilityMatrix.canUseForSigning ||
                fixtureCatalog.canUseForSigning ||
                fixtureValidation.canUseForSigning ||
                publicVectorFixture.canUseForSigning ||
                publicVectorValidation.canUseForSigning ||
                caseBinding.canUseForSigning ||
                caseBindingValidation.canUseForSigning ||
                executableAdmission.canUseForSigning
        val canUseForBroadcasting =
            capabilityMatrix.canUseForBroadcasting ||
                fixtureCatalog.canUseForBroadcasting ||
                fixtureValidation.canUseForBroadcasting ||
                publicVectorFixture.canUseForBroadcasting ||
                publicVectorValidation.canUseForBroadcasting ||
                caseBinding.canUseForBroadcasting ||
                caseBindingValidation.canUseForBroadcasting ||
                executableAdmission.canUseForBroadcasting
        val canUseForMainnet =
            capabilityMatrix.canUseForMainnet ||
                fixtureCatalog.canUseForMainnet ||
                fixtureValidation.canUseForMainnet ||
                publicVectorFixture.canUseForMainnet ||
                publicVectorValidation.canUseForMainnet ||
                caseBinding.canUseForMainnet ||
                caseBindingValidation.canUseForMainnet ||
                executableAdmission.canUseForMainnet
        val productionProviderSelectable =
            capabilityMatrix.productionProviderSelectable ||
                fixtureScope.productionProviderSelectable ||
                fixtureCatalog.productionProviderSelectable ||
                fixtureValidation.productionProviderSelectable ||
                publicVectorFixture.productionProviderSelectable ||
                publicVectorValidation.productionProviderSelectable ||
                caseBinding.productionProviderSelectable ||
                caseBindingValidation.productionProviderSelectable ||
                executableAdmission.productionProviderSelectable
        val metadataKatEvaluated =
            evaluationIsCommonTestOnly &&
                executableKatAdmissionCount == 1
        val metadataKatPassed =
            metadataKatEvaluated &&
                markerCount == 1 &&
                fixtureRowCount == 1 &&
                publicVectorRowCount == 1 &&
                caseBindingCount == 1 &&
                caseBindingValidationCount == 1 &&
                expectedSafeIdMatched &&
                expectedFixtureIdMatched &&
                expectedVectorIdMatched &&
                expectedCaseIdMatched &&
                evaluatesMetadataOnly &&
                evaluatesPublicNonSecretTextOnlyVector &&
                !caseBindingIsExecutable &&
                !caseBindingContainsRawBytes &&
                !caseBindingContainsHex &&
                !caseBindingContainsCryptoMaterial &&
                !caseBindingContainsWalletMaterial &&
                !caseBindingContainsEndpointMaterial &&
                !caseBindingContainsProviderHandle &&
                !rawKatMaterialPresent &&
                !rawVectorBytesPresent &&
                !rawVectorHexPresent &&
                !publicVectorBytesPresent &&
                !publicVectorHexPresent &&
                !executableKatExecutorPresent &&
                !katRunnerPresent &&
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

        return SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatResult(
            markerCount = markerCount,
            fixtureRowCount = fixtureRowCount,
            publicVectorRowCount = publicVectorRowCount,
            caseBindingCount = caseBindingCount,
            caseBindingValidationCount = caseBindingValidationCount,
            executableKatAdmissionCount = executableKatAdmissionCount,
            expectedSafeIdMatched = expectedSafeIdMatched,
            expectedFixtureIdMatched = expectedFixtureIdMatched,
            expectedVectorIdMatched = expectedVectorIdMatched,
            expectedCaseIdMatched = expectedCaseIdMatched,
            metadataKatEvaluated = metadataKatEvaluated,
            metadataKatPassed = metadataKatPassed,
            evaluationIsCommonTestOnly = evaluationIsCommonTestOnly,
            evaluationIsProductionAuthorization = false,
            evaluationIsProviderSelectionAuthorization = false,
            evaluationIsKatExecutionAuthorization = false,
            evaluationIsCryptoAuthorization = false,
            evaluationIsVaultPersistenceAuthorization = false,
            evaluationIsMainnetAuthorization = false,
            evaluatesMetadataOnly = evaluatesMetadataOnly,
            evaluatesPublicNonSecretTextOnlyVector = evaluatesPublicNonSecretTextOnlyVector,
            evaluatesProviderOperation = false,
            evaluatesCryptoOperation = false,
            evaluatesVaultLifecycle = false,
            evaluatesPersistence = false,
            caseBindingIsMetadataOnly = evaluatesMetadataOnly,
            caseBindingIsExecutable = caseBindingIsExecutable,
            caseBindingContainsRawBytes = caseBindingContainsRawBytes,
            caseBindingContainsHex = caseBindingContainsHex,
            caseBindingContainsCryptoMaterial = caseBindingContainsCryptoMaterial,
            caseBindingContainsWalletMaterial = caseBindingContainsWalletMaterial,
            caseBindingContainsEndpointMaterial = caseBindingContainsEndpointMaterial,
            caseBindingContainsProviderHandle = caseBindingContainsProviderHandle,
            rawKatMaterialPresent = rawKatMaterialPresent,
            rawVectorBytesPresent = rawVectorBytesPresent,
            rawVectorHexPresent = rawVectorHexPresent,
            publicVectorBytesPresent = publicVectorBytesPresent,
            publicVectorHexPresent = publicVectorHexPresent,
            executableKatExecutorPresent = executableKatExecutorPresent,
            katRunnerPresent = katRunnerPresent,
            katExecutorPresent = executableKatExecutorPresent,
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
