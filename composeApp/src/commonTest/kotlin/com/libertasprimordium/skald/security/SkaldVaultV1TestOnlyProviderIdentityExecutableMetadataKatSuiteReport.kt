package com.libertasprimordium.skald.security

data class SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatSuiteReport(
    val markerCount: Int,
    val fixtureRowCount: Int,
    val publicVectorRowCount: Int,
    val caseBindingCount: Int,
    val executableMetadataKatCount: Int,
    val executableMetadataKatValidationCount: Int,
    val executableKatAdmissionCount: Int,
    val expectedSafeIdMatched: Boolean,
    val expectedFixtureIdMatched: Boolean,
    val expectedVectorIdMatched: Boolean,
    val expectedCaseIdMatched: Boolean,
    val metadataKatEvaluated: Boolean,
    val metadataKatPassed: Boolean,
    val executableMetadataKatValidationPassed: Boolean,
    val suiteReportGenerated: Boolean,
    val suitePassed: Boolean,
    val suiteIsCommonTestOnly: Boolean,
    val suiteIsProductionAuthorization: Boolean,
    val suiteIsProviderSelectionAuthorization: Boolean,
    val suiteIsKatExecutionAuthorization: Boolean,
    val suiteIsCryptoAuthorization: Boolean,
    val suiteIsVaultPersistenceAuthorization: Boolean,
    val suiteIsMainnetAuthorization: Boolean,
    val suiteReportsMetadataOnly: Boolean,
    val suiteReportsPublicNonSecretTextOnlyVector: Boolean,
    val suiteReportsProviderOperation: Boolean,
    val suiteReportsCryptoOperation: Boolean,
    val suiteReportsVaultLifecycle: Boolean,
    val suiteReportsPersistence: Boolean,
    val rawKatMaterialPresent: Boolean,
    val rawVectorBytesPresent: Boolean,
    val rawVectorHexPresent: Boolean,
    val publicVectorBytesPresent: Boolean,
    val publicVectorHexPresent: Boolean,
    val executableKatExecutorPresent: Boolean,
    val katRunnerPresent: Boolean,
    val katExecutorPresent: Boolean,
    val providerOperationExecutionReachable: Boolean,
    val cryptoExecutionReachable: Boolean,
    val vaultLifecycleReachable: Boolean,
    val persistenceReachable: Boolean,
    val runtimeSelectable: Boolean,
    val registrySelectable: Boolean,
    val factoryReachable: Boolean,
    val dispatcherReachable: Boolean,
    val executorTargetable: Boolean,
    val providerKatExecutorReachable: Boolean,
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
        "SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatSuiteReport(redactedMarkerId, redactedFixtureId, redactedVectorId, redactedCaseId, commonTestOnly, metadataSuiteOnly, nonAuthorizing)"
}

object SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatSuiteReportPolicy {
    private const val EXPECTED_SAFE_ID: String =
        "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"
    private const val EXPECTED_FIXTURE_ID: String =
        "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata"
    private const val EXPECTED_VECTOR_ID: String =
        "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata"
    private const val EXPECTED_CASE_ID: String =
        "skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata"

    fun currentExecutableMetadataKatSuiteReport():
        SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatSuiteReport {
        val marker = SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy.currentMarker()
        val capabilityMatrix =
            SkaldVaultV1TestOnlyProviderIdentityCapabilityMatrixPolicy.currentCapabilityMatrix()
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
        val metadataKat =
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatPolicy.evaluateCurrentMetadataKat()
        val metadataKatValidation =
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationPolicy
                .currentExecutableMetadataKatValidationReport()
        val publicVectorRow = publicVectorFixture.publicVectorRows.singleOrNull()

        val markerCount =
            if (
                metadataKatValidation.markerCount == 1 &&
                metadataKat.markerCount == 1 &&
                marker.safeId.value == EXPECTED_SAFE_ID &&
                caseBinding.markerSafeId.value == EXPECTED_SAFE_ID &&
                publicVectorRow?.markerSafeId?.value == EXPECTED_SAFE_ID
            ) {
                1
            } else {
                0
            }
        val fixtureRowCount = metadataKatValidation.fixtureRowCount
        val publicVectorRowCount = metadataKatValidation.publicVectorRowCount
        val caseBindingCount = metadataKatValidation.caseBindingCount
        val executableMetadataKatCount = metadataKatValidation.executableMetadataKatCount
        val executableMetadataKatValidationCount =
            if (
                metadataKatValidation.allValidationChecksPassed &&
                metadataKatValidation.metadataKatEvaluated &&
                metadataKatValidation.metadataKatPassed
            ) {
                1
            } else {
                0
            }
        val executableKatAdmissionCount = metadataKatValidation.executableKatAdmissionCount
        val expectedSafeIdMatched =
            metadataKat.expectedSafeIdMatched &&
                metadataKatValidation.expectedSafeIdMatched &&
                executableAdmission.expectedSafeIdMatched &&
                caseBinding.expectedSafeIdMatched &&
                caseBindingValidation.expectedSafeIdMatched &&
                publicVectorValidation.expectedSafeIdMatched &&
                publicVectorFixture.expectedSafeIdMatched &&
                markerCount == 1
        val expectedFixtureIdMatched =
            metadataKat.expectedFixtureIdMatched &&
                metadataKatValidation.expectedFixtureIdMatched &&
                executableAdmission.expectedFixtureIdMatched &&
                caseBinding.expectedFixtureIdMatched &&
                caseBindingValidation.expectedFixtureIdMatched &&
                publicVectorValidation.expectedFixtureIdMatched &&
                publicVectorFixture.expectedFixtureIdMatched &&
                caseBinding.fixtureId.value == EXPECTED_FIXTURE_ID &&
                publicVectorRow?.fixtureId?.value == EXPECTED_FIXTURE_ID
        val expectedVectorIdMatched =
            metadataKat.expectedVectorIdMatched &&
                metadataKatValidation.expectedVectorIdMatched &&
                executableAdmission.expectedVectorIdMatched &&
                caseBinding.expectedVectorIdMatched &&
                caseBindingValidation.expectedVectorIdMatched &&
                publicVectorValidation.expectedVectorIdMatched &&
                publicVectorFixture.expectedVectorIdMatched &&
                caseBinding.vectorId.value == EXPECTED_VECTOR_ID &&
                publicVectorRow?.vectorId?.value == EXPECTED_VECTOR_ID
        val expectedCaseIdMatched =
            metadataKat.expectedCaseIdMatched &&
                metadataKatValidation.expectedCaseIdMatched &&
                executableAdmission.expectedCaseIdMatched &&
                caseBinding.expectedCaseIdMatched &&
                caseBindingValidation.expectedCaseIdMatched &&
                caseBinding.caseId.value == EXPECTED_CASE_ID
        val metadataKatEvaluated =
            metadataKat.metadataKatEvaluated &&
                metadataKatValidation.metadataKatEvaluated
        val metadataKatPassed =
            metadataKat.metadataKatPassed &&
                metadataKatValidation.metadataKatPassed
        val executableMetadataKatValidationPassed = metadataKatValidation.allValidationChecksPassed
        val suiteIsCommonTestOnly =
            metadataKat.evaluationIsCommonTestOnly &&
                metadataKatValidation.validationIsCommonTestOnly &&
                executableAdmission.admissionIsCommonTestOnly &&
                caseBinding.caseIsCommonTestOnly &&
                caseBindingValidation.validationIsCommonTestOnly &&
                publicVectorValidation.validationIsCommonTestOnly &&
                publicVectorFixture.fixtureIsCommonTestOnly &&
                capabilityMatrix.matrixIsCommonTestOnly &&
                marker.sourceSet == SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet.CommonTest
        val suiteReportsMetadataOnly =
            metadataKat.evaluatesMetadataOnly &&
                metadataKatValidation.validatesMetadataOnly &&
                caseBinding.caseIsMetadataOnly &&
                caseBindingValidation.caseBindingIsMetadataOnly
        val suiteReportsPublicNonSecretTextOnlyVector =
            metadataKat.evaluatesPublicNonSecretTextOnlyVector &&
                metadataKatValidation.validatesPublicNonSecretTextOnlyVector &&
                publicVectorFixture.vectorIsPublicAndNonSecret &&
                publicVectorFixture.vectorIsTextOnly &&
                publicVectorValidation.vectorIsPublicAndNonSecret &&
                publicVectorValidation.vectorIsTextOnly

        val rawKatMaterialPresent =
            metadataKat.rawKatMaterialPresent ||
                metadataKatValidation.rawKatMaterialPresent ||
                executableAdmission.rawKatMaterialPresent ||
                caseBindingValidation.rawKatMaterialPresent ||
                publicVectorValidation.rawKatMaterialPresent ||
                caseBinding.caseContainsCryptoMaterial
        val rawVectorBytesPresent =
            metadataKat.rawVectorBytesPresent ||
                metadataKatValidation.rawVectorBytesPresent ||
                executableAdmission.rawVectorBytesPresent ||
                caseBindingValidation.rawVectorBytesPresent ||
                publicVectorValidation.rawVectorBytesPresent ||
                caseBinding.caseContainsRawBytes
        val rawVectorHexPresent =
            metadataKat.rawVectorHexPresent ||
                metadataKatValidation.rawVectorHexPresent ||
                executableAdmission.rawVectorHexPresent ||
                caseBindingValidation.rawVectorHexPresent ||
                publicVectorValidation.rawVectorHexPresent ||
                caseBinding.caseContainsHex
        val publicVectorBytesPresent =
            metadataKat.publicVectorBytesPresent ||
                metadataKatValidation.publicVectorBytesPresent ||
                executableAdmission.publicVectorBytesPresent ||
                caseBindingValidation.publicVectorBytesPresent ||
                publicVectorValidation.publicVectorBytesPresent
        val publicVectorHexPresent =
            metadataKat.publicVectorHexPresent ||
                metadataKatValidation.publicVectorHexPresent ||
                executableAdmission.publicVectorHexPresent ||
                caseBindingValidation.publicVectorHexPresent ||
                publicVectorValidation.publicVectorHexPresent
        val executableKatExecutorPresent =
            metadataKat.executableKatExecutorPresent ||
                metadataKatValidation.executableKatExecutorPresent ||
                executableAdmission.currentKatExecutorPresent ||
                executableAdmission.katExecutorPresent ||
                caseBindingValidation.katExecutorPresent ||
                publicVectorValidation.katExecutorPresent
        val katRunnerPresent =
            metadataKat.katRunnerPresent ||
                metadataKatValidation.katRunnerPresent ||
                executableAdmission.currentKatRunnerPresent
        val katExecutorPresent =
            metadataKat.katExecutorPresent ||
                metadataKatValidation.katExecutorPresent ||
                executableAdmission.currentKatExecutorPresent ||
                executableAdmission.katExecutorPresent ||
                caseBindingValidation.katExecutorPresent ||
                publicVectorValidation.katExecutorPresent

        val providerOperationExecutionReachable =
            metadataKat.providerOperationReachable ||
                metadataKatValidation.providerOperationExecutionReachable ||
                executableAdmission.providerOperationReachable ||
                caseBinding.providerOperationReachable ||
                caseBindingValidation.providerOperationReachable ||
                publicVectorValidation.providerOperationReachable ||
                publicVectorFixture.providerOperationReachable ||
                capabilityMatrix.providerOperationReachable
        val cryptoExecutionReachable =
            metadataKat.cryptoExecutionReachable ||
                metadataKatValidation.cryptoExecutionReachable ||
                executableAdmission.cryptoExecutionReachable ||
                caseBinding.cryptoExecutionReachable ||
                caseBindingValidation.cryptoExecutionReachable ||
                publicVectorValidation.cryptoExecutionReachable ||
                publicVectorFixture.cryptoExecutionReachable ||
                capabilityMatrix.cryptoExecutionReachable
        val vaultLifecycleReachable =
            metadataKat.vaultLifecycleReachable ||
                metadataKatValidation.vaultLifecycleReachable ||
                executableAdmission.vaultLifecycleReachable ||
                caseBinding.vaultLifecycleReachable ||
                caseBindingValidation.vaultLifecycleReachable ||
                publicVectorValidation.vaultLifecycleReachable ||
                publicVectorFixture.vaultLifecycleReachable ||
                capabilityMatrix.vaultLifecycleReachable
        val persistenceReachable =
            metadataKat.persistenceReachable ||
                metadataKatValidation.persistenceReachable ||
                executableAdmission.persistenceReachable ||
                caseBinding.persistenceReachable ||
                caseBindingValidation.persistenceReachable ||
                publicVectorValidation.persistenceReachable ||
                publicVectorFixture.persistenceReachable ||
                capabilityMatrix.persistenceReachable
        val runtimeSelectable =
            metadataKat.runtimeSelectable ||
                metadataKatValidation.runtimeSelectable ||
                executableAdmission.runtimeSelectable ||
                caseBinding.runtimeSelectable ||
                caseBindingValidation.runtimeSelectable ||
                publicVectorValidation.runtimeSelectable ||
                publicVectorFixture.runtimeSelectable ||
                capabilityMatrix.runtimeSelectable
        val registrySelectable =
            metadataKat.registrySelectable ||
                metadataKatValidation.registrySelectable ||
                executableAdmission.registrySelectable ||
                caseBinding.registrySelectable ||
                caseBindingValidation.registrySelectable ||
                publicVectorValidation.registrySelectable ||
                publicVectorFixture.registrySelectable ||
                capabilityMatrix.registrySelectable
        val factoryReachable =
            metadataKat.factoryReachable ||
                metadataKatValidation.factoryReachable ||
                executableAdmission.factoryReachable ||
                caseBinding.factoryReachable ||
                caseBindingValidation.factoryReachable ||
                publicVectorValidation.factoryReachable ||
                publicVectorFixture.factoryReachable ||
                capabilityMatrix.factoryReachable
        val dispatcherReachable =
            metadataKat.dispatcherReachable ||
                metadataKatValidation.dispatcherReachable ||
                executableAdmission.dispatcherReachable ||
                caseBinding.dispatcherReachable ||
                caseBindingValidation.dispatcherReachable ||
                publicVectorValidation.dispatcherReachable ||
                publicVectorFixture.dispatcherReachable ||
                capabilityMatrix.dispatcherReachable
        val executorTargetable =
            metadataKat.executorTargetable ||
                metadataKatValidation.executorTargetable ||
                executableAdmission.executorTargetable ||
                caseBinding.executorTargetable ||
                caseBindingValidation.executorTargetable ||
                publicVectorValidation.executorTargetable ||
                publicVectorFixture.executorTargetable ||
                capabilityMatrix.executorTargetable
        val providerKatExecutorReachable =
            metadataKat.providerKatExecutorReachable ||
                metadataKatValidation.providerKatExecutorReachable ||
                executableAdmission.providerKatExecutorReachable ||
                caseBinding.providerKatExecutorReachable ||
                caseBindingValidation.providerKatExecutorReachable ||
                publicVectorValidation.providerKatExecutorReachable ||
                publicVectorFixture.providerKatExecutorReachable ||
                capabilityMatrix.providerKatExecutorReachable
        val productionSyncReachable =
            metadataKat.productionSyncReachable ||
                metadataKatValidation.productionSyncReachable ||
                executableAdmission.productionSyncReachable ||
                caseBinding.productionSyncReachable ||
                caseBindingValidation.productionSyncReachable ||
                publicVectorValidation.productionSyncReachable ||
                publicVectorFixture.productionSyncReachable ||
                capabilityMatrix.productionSyncReachable
        val backendClientReachable =
            metadataKat.backendClientReachable ||
                metadataKatValidation.backendClientReachable ||
                executableAdmission.backendClientReachable ||
                caseBinding.backendClientReachable ||
                caseBindingValidation.backendClientReachable ||
                publicVectorValidation.backendClientReachable ||
                publicVectorFixture.backendClientReachable ||
                capabilityMatrix.backendClientReachable
        val bdkWalletStateReachable =
            metadataKat.bdkWalletStateReachable ||
                metadataKatValidation.bdkWalletStateReachable ||
                executableAdmission.bdkWalletStateReachable ||
                caseBinding.bdkWalletStateReachable ||
                caseBindingValidation.bdkWalletStateReachable ||
                publicVectorValidation.bdkWalletStateReachable ||
                publicVectorFixture.bdkWalletStateReachable ||
                capabilityMatrix.bdkWalletStateReachable
        val settingsCodecReachable =
            metadataKat.settingsCodecReachable ||
                metadataKatValidation.settingsCodecReachable ||
                executableAdmission.settingsCodecReachable ||
                caseBinding.settingsCodecReachable ||
                caseBindingValidation.settingsCodecReachable ||
                publicVectorValidation.settingsCodecReachable ||
                publicVectorFixture.settingsCodecReachable ||
                capabilityMatrix.settingsCodecReachable
        val uiSurfaceReachable =
            metadataKat.uiSurfaceReachable ||
                metadataKatValidation.uiSurfaceReachable ||
                executableAdmission.uiSurfaceReachable ||
                caseBinding.uiSurfaceReachable ||
                caseBindingValidation.uiSurfaceReachable ||
                publicVectorValidation.uiSurfaceReachable ||
                publicVectorFixture.uiSurfaceReachable ||
                capabilityMatrix.uiSurfaceReachable
        val signingBroadcastingReachable =
            metadataKat.signingBroadcastingReachable ||
                metadataKatValidation.signingBroadcastingReachable ||
                executableAdmission.signingBroadcastingReachable ||
                caseBinding.signingBroadcastingReachable ||
                caseBindingValidation.signingBroadcastingReachable ||
                publicVectorValidation.signingBroadcastingReachable ||
                publicVectorFixture.signingBroadcastingReachable ||
                capabilityMatrix.signingBroadcastingReachable
        val publicEndpointReachable =
            metadataKat.publicEndpointReachable ||
                metadataKatValidation.publicEndpointReachable ||
                executableAdmission.publicEndpointReachable ||
                caseBinding.publicEndpointReachable ||
                caseBindingValidation.publicEndpointReachable ||
                publicVectorValidation.publicEndpointReachable ||
                publicVectorFixture.publicEndpointReachable ||
                capabilityMatrix.publicEndpointReachable
        val mainnetReachable =
            metadataKat.mainnetReachable ||
                metadataKatValidation.mainnetReachable ||
                executableAdmission.mainnetReachable ||
                caseBinding.mainnetReachable ||
                caseBindingValidation.mainnetReachable ||
                publicVectorValidation.mainnetReachable ||
                publicVectorFixture.mainnetReachable ||
                capabilityMatrix.mainnetReachable
        val implementsVaultCryptoProvider =
            metadataKat.implementsVaultCryptoProvider ||
                metadataKatValidation.implementsVaultCryptoProvider ||
                executableAdmission.implementsVaultCryptoProvider ||
                caseBinding.implementsVaultCryptoProvider ||
                caseBindingValidation.implementsVaultCryptoProvider ||
                publicVectorValidation.implementsVaultCryptoProvider ||
                publicVectorFixture.implementsVaultCryptoProvider ||
                capabilityMatrix.implementsVaultCryptoProvider
        val containsVaultCryptoProvider =
            metadataKat.containsVaultCryptoProvider ||
                metadataKatValidation.containsVaultCryptoProvider ||
                executableAdmission.containsVaultCryptoProvider ||
                caseBinding.containsVaultCryptoProvider ||
                caseBindingValidation.containsVaultCryptoProvider ||
                publicVectorValidation.containsVaultCryptoProvider ||
                publicVectorFixture.containsVaultCryptoProvider ||
                capabilityMatrix.containsVaultCryptoProvider
        val canExecuteProviderOperations =
            metadataKat.canExecuteProviderOperations ||
                metadataKatValidation.canExecuteProviderOperations ||
                executableAdmission.canExecuteProviderOperations ||
                caseBinding.canExecuteProviderOperations ||
                caseBindingValidation.canExecuteProviderOperations ||
                publicVectorValidation.canExecuteProviderOperations ||
                publicVectorFixture.canExecuteProviderOperations ||
                capabilityMatrix.canExecuteProviderOperations
        val canExecuteCrypto =
            metadataKat.canExecuteCrypto ||
                metadataKatValidation.canExecuteCrypto ||
                executableAdmission.canExecuteCrypto ||
                caseBinding.canExecuteCrypto ||
                caseBindingValidation.canExecuteCrypto ||
                publicVectorValidation.canExecuteCrypto ||
                publicVectorFixture.canExecuteCrypto ||
                capabilityMatrix.canExecuteCrypto
        val canUseForVaultLifecycle =
            metadataKat.canUseForVaultLifecycle ||
                metadataKatValidation.canUseForVaultLifecycle ||
                executableAdmission.canUseForVaultLifecycle ||
                caseBinding.canUseForVaultLifecycle ||
                caseBindingValidation.canUseForVaultLifecycle ||
                publicVectorValidation.canUseForVaultLifecycle ||
                publicVectorFixture.canUseForVaultLifecycle ||
                capabilityMatrix.canUseForVaultLifecycle
        val canUseForPersistence =
            metadataKat.canUseForPersistence ||
                metadataKatValidation.canUseForPersistence ||
                executableAdmission.canUseForPersistence ||
                caseBinding.canUseForPersistence ||
                caseBindingValidation.canUseForPersistence ||
                publicVectorValidation.canUseForPersistence ||
                publicVectorFixture.canUseForPersistence ||
                capabilityMatrix.canUseForPersistence
        val canUseForSync =
            metadataKat.canUseForSync ||
                metadataKatValidation.canUseForSync ||
                executableAdmission.canUseForSync ||
                caseBinding.canUseForSync ||
                caseBindingValidation.canUseForSync ||
                publicVectorValidation.canUseForSync ||
                publicVectorFixture.canUseForSync ||
                capabilityMatrix.canUseForSync
        val canUseForSigning =
            metadataKat.canUseForSigning ||
                metadataKatValidation.canUseForSigning ||
                executableAdmission.canUseForSigning ||
                caseBinding.canUseForSigning ||
                caseBindingValidation.canUseForSigning ||
                publicVectorValidation.canUseForSigning ||
                publicVectorFixture.canUseForSigning ||
                capabilityMatrix.canUseForSigning
        val canUseForBroadcasting =
            metadataKat.canUseForBroadcasting ||
                metadataKatValidation.canUseForBroadcasting ||
                executableAdmission.canUseForBroadcasting ||
                caseBinding.canUseForBroadcasting ||
                caseBindingValidation.canUseForBroadcasting ||
                publicVectorValidation.canUseForBroadcasting ||
                publicVectorFixture.canUseForBroadcasting ||
                capabilityMatrix.canUseForBroadcasting
        val canUseForMainnet =
            metadataKat.canUseForMainnet ||
                metadataKatValidation.canUseForMainnet ||
                executableAdmission.canUseForMainnet ||
                caseBinding.canUseForMainnet ||
                caseBindingValidation.canUseForMainnet ||
                publicVectorValidation.canUseForMainnet ||
                publicVectorFixture.canUseForMainnet ||
                capabilityMatrix.canUseForMainnet
        val productionProviderSelectable =
            metadataKat.productionProviderSelectable ||
                metadataKatValidation.productionProviderSelectable ||
                executableAdmission.productionProviderSelectable ||
                caseBinding.productionProviderSelectable ||
                caseBindingValidation.productionProviderSelectable ||
                publicVectorValidation.productionProviderSelectable ||
                publicVectorFixture.productionProviderSelectable ||
                capabilityMatrix.productionProviderSelectable ||
                marker.productionProviderSelectable

        val suiteReportGenerated =
            suiteIsCommonTestOnly &&
                executableMetadataKatValidationCount == 1
        val suitePassed =
            suiteReportGenerated &&
                markerCount == 1 &&
                fixtureRowCount == 1 &&
                publicVectorRowCount == 1 &&
                caseBindingCount == 1 &&
                executableMetadataKatCount == 1 &&
                executableKatAdmissionCount == 1 &&
                expectedSafeIdMatched &&
                expectedFixtureIdMatched &&
                expectedVectorIdMatched &&
                expectedCaseIdMatched &&
                metadataKatEvaluated &&
                metadataKatPassed &&
                executableMetadataKatValidationPassed &&
                suiteReportsMetadataOnly &&
                suiteReportsPublicNonSecretTextOnlyVector &&
                !rawKatMaterialPresent &&
                !rawVectorBytesPresent &&
                !rawVectorHexPresent &&
                !publicVectorBytesPresent &&
                !publicVectorHexPresent &&
                !executableKatExecutorPresent &&
                !katRunnerPresent &&
                !katExecutorPresent &&
                !providerOperationExecutionReachable &&
                !cryptoExecutionReachable &&
                !vaultLifecycleReachable &&
                !persistenceReachable &&
                !runtimeSelectable &&
                !registrySelectable &&
                !factoryReachable &&
                !dispatcherReachable &&
                !executorTargetable &&
                !providerKatExecutorReachable &&
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

        return SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatSuiteReport(
            markerCount = markerCount,
            fixtureRowCount = fixtureRowCount,
            publicVectorRowCount = publicVectorRowCount,
            caseBindingCount = caseBindingCount,
            executableMetadataKatCount = executableMetadataKatCount,
            executableMetadataKatValidationCount = executableMetadataKatValidationCount,
            executableKatAdmissionCount = executableKatAdmissionCount,
            expectedSafeIdMatched = expectedSafeIdMatched,
            expectedFixtureIdMatched = expectedFixtureIdMatched,
            expectedVectorIdMatched = expectedVectorIdMatched,
            expectedCaseIdMatched = expectedCaseIdMatched,
            metadataKatEvaluated = metadataKatEvaluated,
            metadataKatPassed = metadataKatPassed,
            executableMetadataKatValidationPassed = executableMetadataKatValidationPassed,
            suiteReportGenerated = suiteReportGenerated,
            suitePassed = suitePassed,
            suiteIsCommonTestOnly = suiteIsCommonTestOnly,
            suiteIsProductionAuthorization = false,
            suiteIsProviderSelectionAuthorization = false,
            suiteIsKatExecutionAuthorization = false,
            suiteIsCryptoAuthorization = false,
            suiteIsVaultPersistenceAuthorization = false,
            suiteIsMainnetAuthorization = false,
            suiteReportsMetadataOnly = suiteReportsMetadataOnly,
            suiteReportsPublicNonSecretTextOnlyVector = suiteReportsPublicNonSecretTextOnlyVector,
            suiteReportsProviderOperation = false,
            suiteReportsCryptoOperation = false,
            suiteReportsVaultLifecycle = false,
            suiteReportsPersistence = false,
            rawKatMaterialPresent = rawKatMaterialPresent,
            rawVectorBytesPresent = rawVectorBytesPresent,
            rawVectorHexPresent = rawVectorHexPresent,
            publicVectorBytesPresent = publicVectorBytesPresent,
            publicVectorHexPresent = publicVectorHexPresent,
            executableKatExecutorPresent = executableKatExecutorPresent,
            katRunnerPresent = katRunnerPresent,
            katExecutorPresent = katExecutorPresent,
            providerOperationExecutionReachable = providerOperationExecutionReachable,
            cryptoExecutionReachable = cryptoExecutionReachable,
            vaultLifecycleReachable = vaultLifecycleReachable,
            persistenceReachable = persistenceReachable,
            runtimeSelectable = runtimeSelectable,
            registrySelectable = registrySelectable,
            factoryReachable = factoryReachable,
            dispatcherReachable = dispatcherReachable,
            executorTargetable = executorTargetable,
            providerKatExecutorReachable = providerKatExecutorReachable,
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
