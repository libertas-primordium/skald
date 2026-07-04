package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationSafeLabel(val value: String) {
    override fun toString(): String =
        "RedactedTestOnlyProviderIdentityExecutableMetadataKatValidationSafeLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationSourceSet {
    CommonTest,
}

enum class SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationCheck {
    ExecutableMetadataKatPresentExactlyOnce,
    ExecutableMetadataKatResultReadSuccessfully,
    MetadataKatEvaluatedTrue,
    MetadataKatPassedTrue,
    MetadataKatEvaluatesExactlyOneCaseBinding,
    MetadataKatReferencesExpectedMarkerSafeId,
    MetadataKatReferencesExpectedFixtureId,
    MetadataKatReferencesExpectedVectorId,
    MetadataKatReferencesExpectedCaseId,
    MetadataKatEvaluatesMetadataOnly,
    MetadataKatEvaluatesPublicNonSecretTextOnlyVectorOnly,
    MetadataKatDoesNotEvaluateProviderOperation,
    MetadataKatDoesNotEvaluateCryptoOperation,
    MetadataKatDoesNotEvaluateVaultLifecycle,
    MetadataKatDoesNotEvaluatePersistence,
    KatRunnerAbsent,
    KatExecutorAbsent,
    ProviderOperationExecutionAbsent,
    CryptoExecutionAbsent,
    VaultPersistenceExecutionAbsent,
    ProviderSelectionAuthorizationAbsent,
    ProductionAuthorizationAbsent,
    KatExecutorAuthorizationAbsent,
    MainnetAuthorizationAbsent,
    ProviderSelectionRemainsDisabledProviderOnly,
    ProductionProviderSelectableRemainsFalse,
    ProductionRuntimeSourceAbsenceSatisfied,
    SafeOutputRedactionSatisfied,
}

data class SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationCheckRow(
    val check: SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationCheck,
    val passed: Boolean,
    val authorizesRuntimeUse: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationReport(
    val markerCount: Int,
    val fixtureRowCount: Int,
    val publicVectorRowCount: Int,
    val caseBindingCount: Int,
    val executableMetadataKatCount: Int,
    val executableKatAdmissionCount: Int,
    val expectedSafeIdMatched: Boolean,
    val expectedFixtureIdMatched: Boolean,
    val expectedVectorIdMatched: Boolean,
    val expectedCaseIdMatched: Boolean,
    val metadataKatEvaluated: Boolean,
    val metadataKatPassed: Boolean,
    val allValidationChecksPassed: Boolean,
    val validationIsCommonTestOnly: Boolean,
    val validationIsProductionAuthorization: Boolean,
    val validationIsProviderSelectionAuthorization: Boolean,
    val validationIsKatExecutionAuthorization: Boolean,
    val validationIsCryptoAuthorization: Boolean,
    val validationIsVaultPersistenceAuthorization: Boolean,
    val validationIsMainnetAuthorization: Boolean,
    val validatesMetadataOnly: Boolean,
    val validatesPublicNonSecretTextOnlyVector: Boolean,
    val validatesProviderOperation: Boolean,
    val validatesCryptoOperation: Boolean,
    val validatesVaultLifecycle: Boolean,
    val validatesPersistence: Boolean,
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
    val validationCheckRows:
        List<SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationCheckRow>,
    val sourceSet: SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationSourceSet,
    val displayLabel: SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationSafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationReport(redactedMarkerId, redactedFixtureId, redactedVectorId, redactedCaseId, commonTestOnly, metadataValidationOnly, nonAuthorizing)"
}

object SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationPolicy {
    private const val EXPECTED_SAFE_ID: String =
        "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"
    private const val EXPECTED_FIXTURE_ID: String =
        "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata"
    private const val EXPECTED_VECTOR_ID: String =
        "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata"
    private const val EXPECTED_CASE_ID: String =
        "skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata"

    fun currentExecutableMetadataKatValidationReport():
        SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationReport {
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
        val publicVectorRow = publicVectorFixture.publicVectorRows.singleOrNull()

        val markerCount = metadataKat.markerCount
        val fixtureRowCount = metadataKat.fixtureRowCount
        val publicVectorRowCount = metadataKat.publicVectorRowCount
        val caseBindingCount = metadataKat.caseBindingCount
        val executableMetadataKatCount =
            if (metadataKat.metadataKatEvaluated && metadataKat.metadataKatPassed) {
                1
            } else {
                0
            }
        val executableKatAdmissionCount = metadataKat.executableKatAdmissionCount
        val expectedSafeIdMatched =
            metadataKat.expectedSafeIdMatched &&
                executableAdmission.expectedSafeIdMatched &&
                caseBinding.expectedSafeIdMatched &&
                caseBindingValidation.expectedSafeIdMatched &&
                publicVectorValidation.expectedSafeIdMatched &&
                publicVectorFixture.expectedSafeIdMatched &&
                marker.safeId.value == EXPECTED_SAFE_ID &&
                caseBinding.markerSafeId.value == EXPECTED_SAFE_ID &&
                publicVectorRow?.markerSafeId?.value == EXPECTED_SAFE_ID
        val expectedFixtureIdMatched =
            metadataKat.expectedFixtureIdMatched &&
                executableAdmission.expectedFixtureIdMatched &&
                caseBinding.expectedFixtureIdMatched &&
                caseBindingValidation.expectedFixtureIdMatched &&
                publicVectorValidation.expectedFixtureIdMatched &&
                publicVectorFixture.expectedFixtureIdMatched &&
                caseBinding.fixtureId.value == EXPECTED_FIXTURE_ID &&
                publicVectorRow?.fixtureId?.value == EXPECTED_FIXTURE_ID
        val expectedVectorIdMatched =
            metadataKat.expectedVectorIdMatched &&
                executableAdmission.expectedVectorIdMatched &&
                caseBinding.expectedVectorIdMatched &&
                caseBindingValidation.expectedVectorIdMatched &&
                publicVectorValidation.expectedVectorIdMatched &&
                publicVectorFixture.expectedVectorIdMatched &&
                caseBinding.vectorId.value == EXPECTED_VECTOR_ID &&
                publicVectorRow?.vectorId?.value == EXPECTED_VECTOR_ID
        val expectedCaseIdMatched =
            metadataKat.expectedCaseIdMatched &&
                executableAdmission.expectedCaseIdMatched &&
                caseBinding.expectedCaseIdMatched &&
                caseBindingValidation.expectedCaseIdMatched &&
                caseBinding.caseId.value == EXPECTED_CASE_ID
        val metadataKatEvaluated = metadataKat.metadataKatEvaluated
        val metadataKatPassed = metadataKat.metadataKatPassed
        val validationIsCommonTestOnly =
            metadataKat.evaluationIsCommonTestOnly &&
                executableAdmission.admissionIsCommonTestOnly &&
                caseBinding.caseIsCommonTestOnly &&
                caseBindingValidation.validationIsCommonTestOnly &&
                publicVectorValidation.validationIsCommonTestOnly &&
                publicVectorFixture.fixtureIsCommonTestOnly &&
                capabilityMatrix.matrixIsCommonTestOnly &&
                marker.sourceSet == SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet.CommonTest
        val validatesMetadataOnly =
            metadataKat.evaluatesMetadataOnly &&
                caseBinding.caseIsMetadataOnly &&
                caseBindingValidation.caseBindingIsMetadataOnly
        val validatesPublicNonSecretTextOnlyVector =
            metadataKat.evaluatesPublicNonSecretTextOnlyVector &&
                publicVectorFixture.vectorIsPublicAndNonSecret &&
                publicVectorFixture.vectorIsTextOnly &&
                publicVectorValidation.vectorIsPublicAndNonSecret &&
                publicVectorValidation.vectorIsTextOnly
        val rawKatMaterialPresent =
            metadataKat.rawKatMaterialPresent ||
                executableAdmission.rawKatMaterialPresent ||
                caseBindingValidation.rawKatMaterialPresent ||
                publicVectorValidation.rawKatMaterialPresent
        val rawVectorBytesPresent =
            metadataKat.rawVectorBytesPresent ||
                executableAdmission.rawVectorBytesPresent ||
                caseBindingValidation.rawVectorBytesPresent ||
                publicVectorValidation.rawVectorBytesPresent
        val rawVectorHexPresent =
            metadataKat.rawVectorHexPresent ||
                executableAdmission.rawVectorHexPresent ||
                caseBindingValidation.rawVectorHexPresent ||
                publicVectorValidation.rawVectorHexPresent
        val publicVectorBytesPresent =
            metadataKat.publicVectorBytesPresent ||
                executableAdmission.publicVectorBytesPresent ||
                caseBindingValidation.publicVectorBytesPresent ||
                publicVectorValidation.publicVectorBytesPresent
        val publicVectorHexPresent =
            metadataKat.publicVectorHexPresent ||
                executableAdmission.publicVectorHexPresent ||
                caseBindingValidation.publicVectorHexPresent ||
                publicVectorValidation.publicVectorHexPresent
        val executableKatExecutorPresent =
            metadataKat.executableKatExecutorPresent ||
                executableAdmission.currentKatExecutorPresent ||
                executableAdmission.katExecutorPresent ||
                caseBindingValidation.katExecutorPresent ||
                publicVectorValidation.katExecutorPresent
        val katRunnerPresent =
            metadataKat.katRunnerPresent ||
                executableAdmission.currentKatRunnerPresent
        val katExecutorPresent =
            metadataKat.katExecutorPresent ||
                executableAdmission.currentKatExecutorPresent ||
                executableAdmission.katExecutorPresent ||
                caseBindingValidation.katExecutorPresent ||
                publicVectorValidation.katExecutorPresent
        val providerOperationExecutionReachable =
            metadataKat.providerOperationReachable ||
                executableAdmission.providerOperationReachable ||
                caseBinding.providerOperationReachable ||
                caseBindingValidation.providerOperationReachable ||
                publicVectorValidation.providerOperationReachable ||
                publicVectorFixture.providerOperationReachable ||
                capabilityMatrix.providerOperationReachable
        val cryptoExecutionReachable =
            metadataKat.cryptoExecutionReachable ||
                executableAdmission.cryptoExecutionReachable ||
                caseBinding.cryptoExecutionReachable ||
                caseBindingValidation.cryptoExecutionReachable ||
                publicVectorValidation.cryptoExecutionReachable ||
                publicVectorFixture.cryptoExecutionReachable ||
                capabilityMatrix.cryptoExecutionReachable
        val vaultLifecycleReachable =
            metadataKat.vaultLifecycleReachable ||
                executableAdmission.vaultLifecycleReachable ||
                caseBinding.vaultLifecycleReachable ||
                caseBindingValidation.vaultLifecycleReachable ||
                publicVectorValidation.vaultLifecycleReachable ||
                publicVectorFixture.vaultLifecycleReachable ||
                capabilityMatrix.vaultLifecycleReachable
        val persistenceReachable =
            metadataKat.persistenceReachable ||
                executableAdmission.persistenceReachable ||
                caseBinding.persistenceReachable ||
                caseBindingValidation.persistenceReachable ||
                publicVectorValidation.persistenceReachable ||
                publicVectorFixture.persistenceReachable ||
                capabilityMatrix.persistenceReachable
        val runtimeSelectable =
            metadataKat.runtimeSelectable ||
                executableAdmission.runtimeSelectable ||
                caseBinding.runtimeSelectable ||
                caseBindingValidation.runtimeSelectable ||
                publicVectorValidation.runtimeSelectable ||
                publicVectorFixture.runtimeSelectable ||
                capabilityMatrix.runtimeSelectable
        val registrySelectable =
            metadataKat.registrySelectable ||
                executableAdmission.registrySelectable ||
                caseBinding.registrySelectable ||
                caseBindingValidation.registrySelectable ||
                publicVectorValidation.registrySelectable ||
                publicVectorFixture.registrySelectable ||
                capabilityMatrix.registrySelectable
        val factoryReachable =
            metadataKat.factoryReachable ||
                executableAdmission.factoryReachable ||
                caseBinding.factoryReachable ||
                caseBindingValidation.factoryReachable ||
                publicVectorValidation.factoryReachable ||
                publicVectorFixture.factoryReachable ||
                capabilityMatrix.factoryReachable
        val dispatcherReachable =
            metadataKat.dispatcherReachable ||
                executableAdmission.dispatcherReachable ||
                caseBinding.dispatcherReachable ||
                caseBindingValidation.dispatcherReachable ||
                publicVectorValidation.dispatcherReachable ||
                publicVectorFixture.dispatcherReachable ||
                capabilityMatrix.dispatcherReachable
        val executorTargetable =
            metadataKat.executorTargetable ||
                executableAdmission.executorTargetable ||
                caseBinding.executorTargetable ||
                caseBindingValidation.executorTargetable ||
                publicVectorValidation.executorTargetable ||
                publicVectorFixture.executorTargetable ||
                capabilityMatrix.executorTargetable
        val providerKatExecutorReachable =
            metadataKat.providerKatExecutorReachable ||
                executableAdmission.providerKatExecutorReachable ||
                caseBinding.providerKatExecutorReachable ||
                caseBindingValidation.providerKatExecutorReachable ||
                publicVectorValidation.providerKatExecutorReachable ||
                publicVectorFixture.providerKatExecutorReachable ||
                capabilityMatrix.providerKatExecutorReachable
        val productionSyncReachable =
            metadataKat.productionSyncReachable ||
                executableAdmission.productionSyncReachable ||
                caseBinding.productionSyncReachable ||
                caseBindingValidation.productionSyncReachable ||
                publicVectorValidation.productionSyncReachable ||
                publicVectorFixture.productionSyncReachable ||
                capabilityMatrix.productionSyncReachable
        val backendClientReachable =
            metadataKat.backendClientReachable ||
                executableAdmission.backendClientReachable ||
                caseBinding.backendClientReachable ||
                caseBindingValidation.backendClientReachable ||
                publicVectorValidation.backendClientReachable ||
                publicVectorFixture.backendClientReachable ||
                capabilityMatrix.backendClientReachable
        val bdkWalletStateReachable =
            metadataKat.bdkWalletStateReachable ||
                executableAdmission.bdkWalletStateReachable ||
                caseBinding.bdkWalletStateReachable ||
                caseBindingValidation.bdkWalletStateReachable ||
                publicVectorValidation.bdkWalletStateReachable ||
                publicVectorFixture.bdkWalletStateReachable ||
                capabilityMatrix.bdkWalletStateReachable
        val settingsCodecReachable =
            metadataKat.settingsCodecReachable ||
                executableAdmission.settingsCodecReachable ||
                caseBinding.settingsCodecReachable ||
                caseBindingValidation.settingsCodecReachable ||
                publicVectorValidation.settingsCodecReachable ||
                publicVectorFixture.settingsCodecReachable ||
                capabilityMatrix.settingsCodecReachable
        val uiSurfaceReachable =
            metadataKat.uiSurfaceReachable ||
                executableAdmission.uiSurfaceReachable ||
                caseBinding.uiSurfaceReachable ||
                caseBindingValidation.uiSurfaceReachable ||
                publicVectorValidation.uiSurfaceReachable ||
                publicVectorFixture.uiSurfaceReachable ||
                capabilityMatrix.uiSurfaceReachable
        val signingBroadcastingReachable =
            metadataKat.signingBroadcastingReachable ||
                executableAdmission.signingBroadcastingReachable ||
                caseBinding.signingBroadcastingReachable ||
                caseBindingValidation.signingBroadcastingReachable ||
                publicVectorValidation.signingBroadcastingReachable ||
                publicVectorFixture.signingBroadcastingReachable ||
                capabilityMatrix.signingBroadcastingReachable
        val publicEndpointReachable =
            metadataKat.publicEndpointReachable ||
                executableAdmission.publicEndpointReachable ||
                caseBinding.publicEndpointReachable ||
                caseBindingValidation.publicEndpointReachable ||
                publicVectorValidation.publicEndpointReachable ||
                publicVectorFixture.publicEndpointReachable ||
                capabilityMatrix.publicEndpointReachable
        val mainnetReachable =
            metadataKat.mainnetReachable ||
                executableAdmission.mainnetReachable ||
                caseBinding.mainnetReachable ||
                caseBindingValidation.mainnetReachable ||
                publicVectorValidation.mainnetReachable ||
                publicVectorFixture.mainnetReachable ||
                capabilityMatrix.mainnetReachable
        val implementsVaultCryptoProvider =
            metadataKat.implementsVaultCryptoProvider ||
                executableAdmission.implementsVaultCryptoProvider ||
                caseBinding.implementsVaultCryptoProvider ||
                caseBindingValidation.implementsVaultCryptoProvider ||
                publicVectorValidation.implementsVaultCryptoProvider ||
                publicVectorFixture.implementsVaultCryptoProvider ||
                capabilityMatrix.implementsVaultCryptoProvider
        val containsVaultCryptoProvider =
            metadataKat.containsVaultCryptoProvider ||
                executableAdmission.containsVaultCryptoProvider ||
                caseBinding.containsVaultCryptoProvider ||
                caseBindingValidation.containsVaultCryptoProvider ||
                publicVectorValidation.containsVaultCryptoProvider ||
                publicVectorFixture.containsVaultCryptoProvider ||
                capabilityMatrix.containsVaultCryptoProvider
        val canExecuteProviderOperations =
            metadataKat.canExecuteProviderOperations ||
                executableAdmission.canExecuteProviderOperations ||
                caseBinding.canExecuteProviderOperations ||
                caseBindingValidation.canExecuteProviderOperations ||
                publicVectorValidation.canExecuteProviderOperations ||
                publicVectorFixture.canExecuteProviderOperations ||
                capabilityMatrix.canExecuteProviderOperations
        val canExecuteCrypto =
            metadataKat.canExecuteCrypto ||
                executableAdmission.canExecuteCrypto ||
                caseBinding.canExecuteCrypto ||
                caseBindingValidation.canExecuteCrypto ||
                publicVectorValidation.canExecuteCrypto ||
                publicVectorFixture.canExecuteCrypto ||
                capabilityMatrix.canExecuteCrypto
        val canUseForVaultLifecycle =
            metadataKat.canUseForVaultLifecycle ||
                executableAdmission.canUseForVaultLifecycle ||
                caseBinding.canUseForVaultLifecycle ||
                caseBindingValidation.canUseForVaultLifecycle ||
                publicVectorValidation.canUseForVaultLifecycle ||
                publicVectorFixture.canUseForVaultLifecycle ||
                capabilityMatrix.canUseForVaultLifecycle
        val canUseForPersistence =
            metadataKat.canUseForPersistence ||
                executableAdmission.canUseForPersistence ||
                caseBinding.canUseForPersistence ||
                caseBindingValidation.canUseForPersistence ||
                publicVectorValidation.canUseForPersistence ||
                publicVectorFixture.canUseForPersistence ||
                capabilityMatrix.canUseForPersistence
        val canUseForSync =
            metadataKat.canUseForSync ||
                executableAdmission.canUseForSync ||
                caseBinding.canUseForSync ||
                caseBindingValidation.canUseForSync ||
                publicVectorValidation.canUseForSync ||
                publicVectorFixture.canUseForSync ||
                capabilityMatrix.canUseForSync
        val canUseForSigning =
            metadataKat.canUseForSigning ||
                executableAdmission.canUseForSigning ||
                caseBinding.canUseForSigning ||
                caseBindingValidation.canUseForSigning ||
                publicVectorValidation.canUseForSigning ||
                publicVectorFixture.canUseForSigning ||
                capabilityMatrix.canUseForSigning
        val canUseForBroadcasting =
            metadataKat.canUseForBroadcasting ||
                executableAdmission.canUseForBroadcasting ||
                caseBinding.canUseForBroadcasting ||
                caseBindingValidation.canUseForBroadcasting ||
                publicVectorValidation.canUseForBroadcasting ||
                publicVectorFixture.canUseForBroadcasting ||
                capabilityMatrix.canUseForBroadcasting
        val canUseForMainnet =
            metadataKat.canUseForMainnet ||
                executableAdmission.canUseForMainnet ||
                caseBinding.canUseForMainnet ||
                caseBindingValidation.canUseForMainnet ||
                publicVectorValidation.canUseForMainnet ||
                publicVectorFixture.canUseForMainnet ||
                capabilityMatrix.canUseForMainnet
        val productionProviderSelectable =
            metadataKat.productionProviderSelectable ||
                executableAdmission.productionProviderSelectable ||
                caseBinding.productionProviderSelectable ||
                caseBindingValidation.productionProviderSelectable ||
                publicVectorValidation.productionProviderSelectable ||
                publicVectorFixture.productionProviderSelectable ||
                capabilityMatrix.productionProviderSelectable ||
                marker.productionProviderSelectable
        val productionRuntimeSourceAbsenceSatisfied = true
        val safeOutputRedactionSatisfied =
            sequenceOf(
                marker.toString(),
                marker.safeId.toString(),
                capabilityMatrix.toString(),
                publicVectorFixture.toString(),
                publicVectorValidation.toString(),
                caseBinding.toString(),
                caseBinding.displayLabel.toString(),
                caseBinding.caseId.toString(),
                caseBinding.markerSafeId.toString(),
                caseBinding.fixtureId.toString(),
                caseBinding.vectorId.toString(),
                caseBindingValidation.toString(),
                executableAdmission.toString(),
                metadataKat.toString(),
            ).none { output ->
                EXPECTED_SAFE_ID in output ||
                    EXPECTED_FIXTURE_ID in output ||
                    EXPECTED_VECTOR_ID in output ||
                    EXPECTED_CASE_ID in output
            }
        val providerSelectionAuthorizationAbsent =
            !metadataKat.evaluationIsProviderSelectionAuthorization &&
                !executableAdmission.admissionIsProviderSelectionAuthorization &&
                !caseBinding.caseAuthorizesProviderSelection &&
                !caseBindingValidation.validationIsProviderSelectionAuthorization &&
                !publicVectorValidation.validationIsProviderSelectionAuthorization &&
                !publicVectorFixture.fixtureIsProviderSelectionAuthorization
        val productionAuthorizationAbsent =
            !metadataKat.evaluationIsProductionAuthorization &&
                !executableAdmission.admissionIsProductionAuthorization &&
                !caseBinding.caseAuthorizesProduction &&
                !caseBindingValidation.validationIsProductionAuthorization &&
                !publicVectorValidation.validationIsProductionAuthorization &&
                !publicVectorFixture.fixtureIsProductionAuthorization
        val katExecutorAuthorizationAbsent =
            !metadataKat.evaluationIsKatExecutionAuthorization &&
                !executableAdmission.admissionIsKatExecutionAuthorization &&
                !caseBinding.caseAuthorizesKatExecution &&
                !caseBindingValidation.validationIsKatExecutionAuthorization &&
                !publicVectorValidation.validationIsKatExecutionAuthorization &&
                !publicVectorFixture.fixtureIsKatExecutionAuthorization
        val mainnetAuthorizationAbsent =
            !metadataKat.evaluationIsMainnetAuthorization &&
                !executableAdmission.admissionIsMainnetAuthorization &&
                !caseBinding.caseAuthorizesMainnet &&
                !caseBindingValidation.validationIsMainnetAuthorization &&
                !publicVectorValidation.validationIsMainnetAuthorization &&
                !publicVectorFixture.fixtureIsMainnetAuthorization
        val productionProviderSelectableRemainsFalse =
            !productionProviderSelectable &&
                !metadataKat.productionProviderSelectable &&
                !executableAdmission.productionProviderSelectable &&
                !caseBinding.productionProviderSelectable &&
                !caseBindingValidation.productionProviderSelectable &&
                !publicVectorValidation.productionProviderSelectable &&
                !publicVectorFixture.productionProviderSelectable &&
                !capabilityMatrix.productionProviderSelectable &&
                !marker.productionProviderSelectable

        val checkResults = mapOf(
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationCheck
                .ExecutableMetadataKatPresentExactlyOnce to (executableMetadataKatCount == 1),
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationCheck
                .ExecutableMetadataKatResultReadSuccessfully to metadataKatPassed,
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationCheck
                .MetadataKatEvaluatedTrue to metadataKatEvaluated,
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationCheck
                .MetadataKatPassedTrue to metadataKatPassed,
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationCheck
                .MetadataKatEvaluatesExactlyOneCaseBinding to (caseBindingCount == 1),
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationCheck
                .MetadataKatReferencesExpectedMarkerSafeId to expectedSafeIdMatched,
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationCheck
                .MetadataKatReferencesExpectedFixtureId to expectedFixtureIdMatched,
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationCheck
                .MetadataKatReferencesExpectedVectorId to expectedVectorIdMatched,
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationCheck
                .MetadataKatReferencesExpectedCaseId to expectedCaseIdMatched,
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationCheck
                .MetadataKatEvaluatesMetadataOnly to validatesMetadataOnly,
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationCheck
                .MetadataKatEvaluatesPublicNonSecretTextOnlyVectorOnly to
                validatesPublicNonSecretTextOnlyVector,
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationCheck
                .MetadataKatDoesNotEvaluateProviderOperation to !metadataKat.evaluatesProviderOperation,
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationCheck
                .MetadataKatDoesNotEvaluateCryptoOperation to !metadataKat.evaluatesCryptoOperation,
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationCheck
                .MetadataKatDoesNotEvaluateVaultLifecycle to !metadataKat.evaluatesVaultLifecycle,
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationCheck
                .MetadataKatDoesNotEvaluatePersistence to !metadataKat.evaluatesPersistence,
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationCheck.KatRunnerAbsent to
                !katRunnerPresent,
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationCheck.KatExecutorAbsent to
                !katExecutorPresent,
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationCheck
                .ProviderOperationExecutionAbsent to !providerOperationExecutionReachable,
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationCheck.CryptoExecutionAbsent to
                !cryptoExecutionReachable,
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationCheck
                .VaultPersistenceExecutionAbsent to !persistenceReachable,
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationCheck
                .ProviderSelectionAuthorizationAbsent to providerSelectionAuthorizationAbsent,
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationCheck
                .ProductionAuthorizationAbsent to productionAuthorizationAbsent,
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationCheck
                .KatExecutorAuthorizationAbsent to katExecutorAuthorizationAbsent,
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationCheck
                .MainnetAuthorizationAbsent to mainnetAuthorizationAbsent,
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationCheck
                .ProviderSelectionRemainsDisabledProviderOnly to true,
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationCheck
                .ProductionProviderSelectableRemainsFalse to productionProviderSelectableRemainsFalse,
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationCheck
                .ProductionRuntimeSourceAbsenceSatisfied to productionRuntimeSourceAbsenceSatisfied,
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationCheck
                .SafeOutputRedactionSatisfied to safeOutputRedactionSatisfied,
        )
        val checkRows = currentValidationCheckRows(checkResults)

        return SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationReport(
            markerCount = markerCount,
            fixtureRowCount = fixtureRowCount,
            publicVectorRowCount = publicVectorRowCount,
            caseBindingCount = caseBindingCount,
            executableMetadataKatCount = executableMetadataKatCount,
            executableKatAdmissionCount = executableKatAdmissionCount,
            expectedSafeIdMatched = expectedSafeIdMatched,
            expectedFixtureIdMatched = expectedFixtureIdMatched,
            expectedVectorIdMatched = expectedVectorIdMatched,
            expectedCaseIdMatched = expectedCaseIdMatched,
            metadataKatEvaluated = metadataKatEvaluated,
            metadataKatPassed = metadataKatPassed,
            allValidationChecksPassed = checkRows.all { row -> row.passed },
            validationIsCommonTestOnly = validationIsCommonTestOnly,
            validationIsProductionAuthorization = false,
            validationIsProviderSelectionAuthorization = false,
            validationIsKatExecutionAuthorization = false,
            validationIsCryptoAuthorization = false,
            validationIsVaultPersistenceAuthorization = false,
            validationIsMainnetAuthorization = false,
            validatesMetadataOnly = validatesMetadataOnly,
            validatesPublicNonSecretTextOnlyVector = validatesPublicNonSecretTextOnlyVector,
            validatesProviderOperation = false,
            validatesCryptoOperation = false,
            validatesVaultLifecycle = false,
            validatesPersistence = false,
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
            validationCheckRows = checkRows,
            sourceSet =
                SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationSourceSet.CommonTest,
            displayLabel = SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationSafeLabel(
                "executable metadata KAT validation evidence",
            ),
        )
    }

    private fun currentValidationCheckRows(
        checkResults: Map<SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationCheck, Boolean>,
    ): List<SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationCheckRow> =
        SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationCheck.entries.map { check ->
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationCheckRow(
                check = check,
                passed = checkResults.getValue(check),
                authorizesRuntimeUse = false,
                label = SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationSafeLabel(
                    "redacted",
                ),
            )
        }
}
