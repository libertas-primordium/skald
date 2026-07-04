package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityExecutableKatAdmissionSafeLabel(val value: String) {
    override fun toString(): String =
        "RedactedTestOnlyProviderIdentityExecutableKatAdmissionSafeLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityExecutableKatAdmissionSourceSet {
    CommonTest,
}

enum class SkaldVaultV1TestOnlyProviderIdentityExecutableKatAdmissionOutcome {
    ExecutableKatAdmissionModeled,
    FutureExecutableKatRequiresSeparateBranch,
    CurrentExecutableKatNotPresent,
    CurrentKatRunnerNotPresent,
    CurrentKatExecutorNotPresent,
    ProviderOperationExecutionAbsent,
    CryptoExecutionAbsent,
    ProviderSelectionAuthorizationAbsent,
    ProductionAuthorizationAbsent,
    KatExecutionAuthorizationAbsent,
    VaultPersistenceAuthorizationAbsent,
    MainnetAuthorizationAbsent,
}

enum class SkaldVaultV1TestOnlyProviderIdentityFutureExecutableKatCriterion {
    FutureBranchMustBeExplicitlyApproved,
    FutureExecutableKatMustRemainCommonTestOnly,
    FutureExecutableKatMustUseValidatedCaseBindingOnly,
    FutureExecutableKatMustUsePublicNonSecretTextOnlyVectorOnly,
    FutureExecutableKatMustNotUseRawBytes,
    FutureExecutableKatMustNotUseHex,
    FutureExecutableKatMustNotUseCryptoMaterial,
    FutureExecutableKatMustNotUseWalletMaterial,
    FutureExecutableKatMustNotUseEndpointMaterial,
    FutureExecutableKatMustNotUseProviderHandles,
    FutureExecutableKatMustNotImplementVaultCryptoProvider,
    FutureExecutableKatMustNotUseVaultCryptoProviderInstance,
    FutureExecutableKatMustNotUseProviderSelection,
    FutureExecutableKatMustNotUseRegistry,
    FutureExecutableKatMustNotUseFactory,
    FutureExecutableKatMustNotUseDispatcher,
    FutureExecutableKatMustNotUseExecutorTarget,
    FutureExecutableKatMustNotRunKdfHkdfHmacAead,
    FutureExecutableKatMustNotTouchVaultLifecycle,
    FutureExecutableKatMustNotTouchPersistence,
    FutureExecutableKatMustNotTouchBackendBdkSettingsUi,
    FutureExecutableKatMustNotSignOrBroadcast,
    FutureExecutableKatMustNotEnableMainnet,
    FutureExecutableKatMustRemainCoveredBySourceGuards,
    FutureExecutableKatMustRemainRedactedInOutput,
}

enum class SkaldVaultV1TestOnlyProviderIdentityForbiddenCurrentExecutableKatState {
    ExecutableKatPresent,
    KatRunnerPresent,
    KatExecutorPresent,
    ProviderOperationExecutionPresent,
    CryptoExecutionPresent,
    RawKatMaterialPresent,
    PublicVectorBytesPresent,
    PublicVectorHexPresent,
    ProviderSelectionAuthorizationPresent,
    ProductionAuthorizationPresent,
    VaultPersistenceAuthorizationPresent,
    MainnetAuthorizationPresent,
}

data class SkaldVaultV1TestOnlyProviderIdentityExecutableKatAdmission(
    val markerCount: Int,
    val fixtureRowCount: Int,
    val publicVectorRowCount: Int,
    val caseBindingCount: Int,
    val caseBindingValidationCount: Int,
    val expectedSafeIdMatched: Boolean,
    val expectedFixtureIdMatched: Boolean,
    val expectedVectorIdMatched: Boolean,
    val expectedCaseIdMatched: Boolean,
    val admissionIsCommonTestOnly: Boolean,
    val admissionIsProductionAuthorization: Boolean,
    val admissionIsProviderSelectionAuthorization: Boolean,
    val admissionIsKatExecutionAuthorization: Boolean,
    val admissionIsCryptoAuthorization: Boolean,
    val admissionIsVaultPersistenceAuthorization: Boolean,
    val admissionIsMainnetAuthorization: Boolean,
    val futureExecutableKatCriteriaModeled: Boolean,
    val futureExecutableKatCriteriaAuthorizeCurrentExecution: Boolean,
    val currentExecutableKatPresent: Boolean,
    val currentKatRunnerPresent: Boolean,
    val currentKatExecutorPresent: Boolean,
    val rawKatMaterialPresent: Boolean,
    val rawVectorBytesPresent: Boolean,
    val rawVectorHexPresent: Boolean,
    val publicVectorBytesPresent: Boolean,
    val publicVectorHexPresent: Boolean,
    val executableKatPresent: Boolean,
    val katExecutorPresent: Boolean,
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
    val admissionOutcomes: List<SkaldVaultV1TestOnlyProviderIdentityExecutableKatAdmissionOutcome>,
    val futureExecutableKatCriteria: List<SkaldVaultV1TestOnlyProviderIdentityFutureExecutableKatCriterion>,
    val forbiddenCurrentExecutableKatStates:
        List<SkaldVaultV1TestOnlyProviderIdentityForbiddenCurrentExecutableKatState>,
    val sourceSet: SkaldVaultV1TestOnlyProviderIdentityExecutableKatAdmissionSourceSet,
    val displayLabel: SkaldVaultV1TestOnlyProviderIdentityExecutableKatAdmissionSafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityExecutableKatAdmission(redactedMarkerId, redactedFixtureId, redactedVectorId, redactedCaseId, commonTestOnly, nonExecutable, nonAuthorizing)"
}

object SkaldVaultV1TestOnlyProviderIdentityExecutableKatAdmissionPolicy {
    private const val EXPECTED_SAFE_ID: String =
        "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"
    private const val EXPECTED_FIXTURE_ID: String =
        "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata"
    private const val EXPECTED_VECTOR_ID: String =
        "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata"
    private const val EXPECTED_CASE_ID: String =
        "skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata"

    fun currentExecutableKatAdmission(): SkaldVaultV1TestOnlyProviderIdentityExecutableKatAdmission {
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
        val fixtureRow = fixtureCatalog.fixtureRows.singleOrNull()
        val publicVectorRow = publicVectorFixture.publicVectorRows.singleOrNull()
        val admissionOutcomes = SkaldVaultV1TestOnlyProviderIdentityExecutableKatAdmissionOutcome.entries.toList()
        val futureCriteria = SkaldVaultV1TestOnlyProviderIdentityFutureExecutableKatCriterion.entries.toList()
        val forbiddenStates =
            SkaldVaultV1TestOnlyProviderIdentityForbiddenCurrentExecutableKatState.entries.toList()

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
            if (
                caseBindingValidation.caseBindingCount == 1 &&
                caseBindingValidation.allValidationChecksPassed
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
                fixtureRow?.fixtureId?.value == EXPECTED_FIXTURE_ID &&
                publicVectorRow?.fixtureId?.value == EXPECTED_FIXTURE_ID &&
                caseBinding.fixtureId.value == EXPECTED_FIXTURE_ID
        val expectedVectorIdMatched =
            publicVectorFixture.expectedVectorIdMatched &&
                publicVectorValidation.expectedVectorIdMatched &&
                caseBinding.expectedVectorIdMatched &&
                caseBindingValidation.expectedVectorIdMatched &&
                publicVectorRow?.vectorId?.value == EXPECTED_VECTOR_ID &&
                caseBinding.vectorId.value == EXPECTED_VECTOR_ID
        val expectedCaseIdMatched =
            caseBinding.expectedCaseIdMatched &&
                caseBindingValidation.expectedCaseIdMatched &&
                caseBinding.caseId.value == EXPECTED_CASE_ID
        val admissionIsCommonTestOnly =
            marker.sourceSet == SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet.CommonTest &&
                capabilityMatrix.matrixIsCommonTestOnly &&
                fixtureScope.fixtureScopeIsCommonTestOnly &&
                fixtureCatalog.catalogIsCommonTestOnly &&
                fixtureValidation.validationIsCommonTestOnly &&
                publicVectorFixture.fixtureIsCommonTestOnly &&
                publicVectorValidation.validationIsCommonTestOnly &&
                caseBinding.caseIsCommonTestOnly &&
                caseBindingValidation.validationIsCommonTestOnly &&
                caseBinding.sourceSet ==
                SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingSourceSet.CommonTest

        val currentExecutableKatPresent =
            fixtureScope.executableKatPresent ||
                fixtureCatalog.executableKatPresent ||
                fixtureValidation.executableKatPresent ||
                publicVectorFixture.vectorIsExecutable ||
                publicVectorValidation.vectorIsExecutable ||
                publicVectorValidation.executableKatPresent ||
                caseBinding.caseIsExecutable ||
                caseBindingValidation.caseBindingIsExecutable ||
                caseBindingValidation.executableKatPresent
        val currentKatExecutorPresent =
            fixtureScope.katExecutorPresent ||
                fixtureCatalog.katExecutorPresent ||
                fixtureValidation.katExecutorPresent ||
                publicVectorValidation.katExecutorPresent ||
                caseBinding.providerKatExecutorReachable ||
                caseBindingValidation.katExecutorPresent
        val rawKatMaterialPresent =
            fixtureScope.rawKatMaterialPresent ||
                fixtureCatalog.rawKatMaterialPresent ||
                fixtureValidation.rawKatMaterialPresent ||
                publicVectorValidation.rawKatMaterialPresent ||
                caseBinding.caseContainsCryptoMaterial ||
                caseBindingValidation.rawKatMaterialPresent ||
                caseBindingValidation.caseBindingContainsCryptoMaterial
        val rawVectorBytesPresent =
            fixtureCatalog.rawVectorBytesPresent ||
                fixtureValidation.rawVectorBytesPresent ||
                publicVectorValidation.rawVectorBytesPresent ||
                caseBinding.caseContainsRawBytes ||
                caseBindingValidation.rawVectorBytesPresent ||
                caseBindingValidation.caseBindingContainsRawBytes
        val rawVectorHexPresent =
            fixtureCatalog.rawVectorHexPresent ||
                fixtureValidation.rawVectorHexPresent ||
                publicVectorValidation.rawVectorHexPresent ||
                caseBinding.caseContainsHex ||
                caseBindingValidation.rawVectorHexPresent ||
                caseBindingValidation.caseBindingContainsHex
        val publicVectorBytesPresent =
            publicVectorValidation.publicVectorBytesPresent ||
                caseBindingValidation.publicVectorBytesPresent
        val publicVectorHexPresent =
            publicVectorValidation.publicVectorHexPresent ||
                caseBindingValidation.publicVectorHexPresent

        val providerOperationReachable =
            capabilityMatrix.providerOperationReachable ||
                fixtureCatalog.providerOperationReachable ||
                fixtureValidation.providerOperationReachable ||
                publicVectorFixture.providerOperationReachable ||
                publicVectorValidation.providerOperationReachable ||
                caseBinding.providerOperationReachable ||
                caseBindingValidation.providerOperationReachable
        val cryptoExecutionReachable =
            capabilityMatrix.cryptoExecutionReachable ||
                fixtureCatalog.cryptoExecutionReachable ||
                fixtureValidation.cryptoExecutionReachable ||
                publicVectorFixture.cryptoExecutionReachable ||
                publicVectorValidation.cryptoExecutionReachable ||
                caseBinding.cryptoExecutionReachable ||
                caseBindingValidation.cryptoExecutionReachable
        val runtimeSelectable =
            capabilityMatrix.runtimeSelectable ||
                fixtureCatalog.runtimeSelectable ||
                fixtureValidation.runtimeSelectable ||
                publicVectorFixture.runtimeSelectable ||
                publicVectorValidation.runtimeSelectable ||
                caseBinding.runtimeSelectable ||
                caseBindingValidation.runtimeSelectable
        val registrySelectable =
            capabilityMatrix.registrySelectable ||
                fixtureCatalog.registrySelectable ||
                fixtureValidation.registrySelectable ||
                publicVectorFixture.registrySelectable ||
                publicVectorValidation.registrySelectable ||
                caseBinding.registrySelectable ||
                caseBindingValidation.registrySelectable
        val factoryReachable =
            capabilityMatrix.factoryReachable ||
                fixtureCatalog.factoryReachable ||
                fixtureValidation.factoryReachable ||
                publicVectorFixture.factoryReachable ||
                publicVectorValidation.factoryReachable ||
                caseBinding.factoryReachable ||
                caseBindingValidation.factoryReachable
        val dispatcherReachable =
            capabilityMatrix.dispatcherReachable ||
                fixtureCatalog.dispatcherReachable ||
                fixtureValidation.dispatcherReachable ||
                publicVectorFixture.dispatcherReachable ||
                publicVectorValidation.dispatcherReachable ||
                caseBinding.dispatcherReachable ||
                caseBindingValidation.dispatcherReachable
        val executorTargetable =
            capabilityMatrix.executorTargetable ||
                fixtureCatalog.executorTargetable ||
                fixtureValidation.executorTargetable ||
                publicVectorFixture.executorTargetable ||
                publicVectorValidation.executorTargetable ||
                caseBinding.executorTargetable ||
                caseBindingValidation.executorTargetable
        val providerKatExecutorReachable =
            capabilityMatrix.providerKatExecutorReachable ||
                fixtureCatalog.providerKatExecutorReachable ||
                fixtureValidation.providerKatExecutorReachable ||
                publicVectorFixture.providerKatExecutorReachable ||
                publicVectorValidation.providerKatExecutorReachable ||
                caseBinding.providerKatExecutorReachable ||
                caseBindingValidation.providerKatExecutorReachable
        val vaultLifecycleReachable =
            capabilityMatrix.vaultLifecycleReachable ||
                fixtureCatalog.vaultLifecycleReachable ||
                fixtureValidation.vaultLifecycleReachable ||
                publicVectorFixture.vaultLifecycleReachable ||
                publicVectorValidation.vaultLifecycleReachable ||
                caseBinding.vaultLifecycleReachable ||
                caseBindingValidation.vaultLifecycleReachable
        val persistenceReachable =
            capabilityMatrix.persistenceReachable ||
                fixtureCatalog.persistenceReachable ||
                fixtureValidation.persistenceReachable ||
                publicVectorFixture.persistenceReachable ||
                publicVectorValidation.persistenceReachable ||
                caseBinding.persistenceReachable ||
                caseBindingValidation.persistenceReachable
        val productionSyncReachable =
            capabilityMatrix.productionSyncReachable ||
                fixtureCatalog.productionSyncReachable ||
                fixtureValidation.productionSyncReachable ||
                publicVectorFixture.productionSyncReachable ||
                publicVectorValidation.productionSyncReachable ||
                caseBinding.productionSyncReachable ||
                caseBindingValidation.productionSyncReachable
        val backendClientReachable =
            capabilityMatrix.backendClientReachable ||
                fixtureCatalog.backendClientReachable ||
                fixtureValidation.backendClientReachable ||
                publicVectorFixture.backendClientReachable ||
                publicVectorValidation.backendClientReachable ||
                caseBinding.backendClientReachable ||
                caseBindingValidation.backendClientReachable
        val bdkWalletStateReachable =
            capabilityMatrix.bdkWalletStateReachable ||
                fixtureCatalog.bdkWalletStateReachable ||
                fixtureValidation.bdkWalletStateReachable ||
                publicVectorFixture.bdkWalletStateReachable ||
                publicVectorValidation.bdkWalletStateReachable ||
                caseBinding.bdkWalletStateReachable ||
                caseBindingValidation.bdkWalletStateReachable
        val settingsCodecReachable =
            capabilityMatrix.settingsCodecReachable ||
                fixtureCatalog.settingsCodecReachable ||
                fixtureValidation.settingsCodecReachable ||
                publicVectorFixture.settingsCodecReachable ||
                publicVectorValidation.settingsCodecReachable ||
                caseBinding.settingsCodecReachable ||
                caseBindingValidation.settingsCodecReachable
        val uiSurfaceReachable =
            capabilityMatrix.uiSurfaceReachable ||
                fixtureCatalog.uiSurfaceReachable ||
                fixtureValidation.uiSurfaceReachable ||
                publicVectorFixture.uiSurfaceReachable ||
                publicVectorValidation.uiSurfaceReachable ||
                caseBinding.uiSurfaceReachable ||
                caseBindingValidation.uiSurfaceReachable
        val signingBroadcastingReachable =
            capabilityMatrix.signingBroadcastingReachable ||
                fixtureCatalog.signingBroadcastingReachable ||
                fixtureValidation.signingBroadcastingReachable ||
                publicVectorFixture.signingBroadcastingReachable ||
                publicVectorValidation.signingBroadcastingReachable ||
                caseBinding.signingBroadcastingReachable ||
                caseBindingValidation.signingBroadcastingReachable
        val publicEndpointReachable =
            capabilityMatrix.publicEndpointReachable ||
                fixtureCatalog.publicEndpointReachable ||
                fixtureValidation.publicEndpointReachable ||
                publicVectorFixture.publicEndpointReachable ||
                publicVectorValidation.publicEndpointReachable ||
                caseBinding.publicEndpointReachable ||
                caseBindingValidation.publicEndpointReachable
        val mainnetReachable =
            capabilityMatrix.mainnetReachable ||
                fixtureCatalog.mainnetReachable ||
                fixtureValidation.mainnetReachable ||
                publicVectorFixture.mainnetReachable ||
                publicVectorValidation.mainnetReachable ||
                caseBinding.mainnetReachable ||
                caseBindingValidation.mainnetReachable
        val implementsVaultCryptoProvider =
            capabilityMatrix.implementsVaultCryptoProvider ||
                fixtureCatalog.implementsVaultCryptoProvider ||
                fixtureValidation.implementsVaultCryptoProvider ||
                publicVectorFixture.implementsVaultCryptoProvider ||
                publicVectorValidation.implementsVaultCryptoProvider ||
                caseBinding.implementsVaultCryptoProvider ||
                caseBindingValidation.implementsVaultCryptoProvider
        val containsVaultCryptoProvider =
            capabilityMatrix.containsVaultCryptoProvider ||
                fixtureCatalog.containsVaultCryptoProvider ||
                fixtureValidation.containsVaultCryptoProvider ||
                publicVectorFixture.containsVaultCryptoProvider ||
                publicVectorValidation.containsVaultCryptoProvider ||
                caseBinding.containsVaultCryptoProvider ||
                caseBindingValidation.containsVaultCryptoProvider
        val canExecuteProviderOperations =
            capabilityMatrix.canExecuteProviderOperations ||
                fixtureCatalog.canExecuteProviderOperations ||
                fixtureValidation.canExecuteProviderOperations ||
                publicVectorFixture.canExecuteProviderOperations ||
                publicVectorValidation.canExecuteProviderOperations ||
                caseBinding.canExecuteProviderOperations ||
                caseBindingValidation.canExecuteProviderOperations
        val canExecuteCrypto =
            capabilityMatrix.canExecuteCrypto ||
                fixtureCatalog.canExecuteCrypto ||
                fixtureValidation.canExecuteCrypto ||
                publicVectorFixture.canExecuteCrypto ||
                publicVectorValidation.canExecuteCrypto ||
                caseBinding.canExecuteCrypto ||
                caseBindingValidation.canExecuteCrypto
        val canUseForVaultLifecycle =
            capabilityMatrix.canUseForVaultLifecycle ||
                fixtureCatalog.canUseForVaultLifecycle ||
                fixtureValidation.canUseForVaultLifecycle ||
                publicVectorFixture.canUseForVaultLifecycle ||
                publicVectorValidation.canUseForVaultLifecycle ||
                caseBinding.canUseForVaultLifecycle ||
                caseBindingValidation.canUseForVaultLifecycle
        val canUseForPersistence =
            capabilityMatrix.canUseForPersistence ||
                fixtureCatalog.canUseForPersistence ||
                fixtureValidation.canUseForPersistence ||
                publicVectorFixture.canUseForPersistence ||
                publicVectorValidation.canUseForPersistence ||
                caseBinding.canUseForPersistence ||
                caseBindingValidation.canUseForPersistence
        val canUseForSync =
            capabilityMatrix.canUseForSync ||
                fixtureCatalog.canUseForSync ||
                fixtureValidation.canUseForSync ||
                publicVectorFixture.canUseForSync ||
                publicVectorValidation.canUseForSync ||
                caseBinding.canUseForSync ||
                caseBindingValidation.canUseForSync
        val canUseForSigning =
            capabilityMatrix.canUseForSigning ||
                fixtureCatalog.canUseForSigning ||
                fixtureValidation.canUseForSigning ||
                publicVectorFixture.canUseForSigning ||
                publicVectorValidation.canUseForSigning ||
                caseBinding.canUseForSigning ||
                caseBindingValidation.canUseForSigning
        val canUseForBroadcasting =
            capabilityMatrix.canUseForBroadcasting ||
                fixtureCatalog.canUseForBroadcasting ||
                fixtureValidation.canUseForBroadcasting ||
                publicVectorFixture.canUseForBroadcasting ||
                publicVectorValidation.canUseForBroadcasting ||
                caseBinding.canUseForBroadcasting ||
                caseBindingValidation.canUseForBroadcasting
        val canUseForMainnet =
            capabilityMatrix.canUseForMainnet ||
                fixtureCatalog.canUseForMainnet ||
                fixtureValidation.canUseForMainnet ||
                publicVectorFixture.canUseForMainnet ||
                publicVectorValidation.canUseForMainnet ||
                caseBinding.canUseForMainnet ||
                caseBindingValidation.canUseForMainnet
        val productionProviderSelectable =
            capabilityMatrix.productionProviderSelectable ||
                fixtureScope.productionProviderSelectable ||
                fixtureCatalog.productionProviderSelectable ||
                fixtureValidation.productionProviderSelectable ||
                publicVectorFixture.productionProviderSelectable ||
                publicVectorValidation.productionProviderSelectable ||
                caseBinding.productionProviderSelectable ||
                caseBindingValidation.productionProviderSelectable

        return SkaldVaultV1TestOnlyProviderIdentityExecutableKatAdmission(
            markerCount = markerCount,
            fixtureRowCount = fixtureRowCount,
            publicVectorRowCount = publicVectorRowCount,
            caseBindingCount = caseBindingCount,
            caseBindingValidationCount = caseBindingValidationCount,
            expectedSafeIdMatched = expectedSafeIdMatched,
            expectedFixtureIdMatched = expectedFixtureIdMatched,
            expectedVectorIdMatched = expectedVectorIdMatched,
            expectedCaseIdMatched = expectedCaseIdMatched,
            admissionIsCommonTestOnly = admissionIsCommonTestOnly,
            admissionIsProductionAuthorization = false,
            admissionIsProviderSelectionAuthorization = false,
            admissionIsKatExecutionAuthorization = false,
            admissionIsCryptoAuthorization = false,
            admissionIsVaultPersistenceAuthorization = false,
            admissionIsMainnetAuthorization = false,
            futureExecutableKatCriteriaModeled =
                futureCriteria.size ==
                    SkaldVaultV1TestOnlyProviderIdentityFutureExecutableKatCriterion.entries.size,
            futureExecutableKatCriteriaAuthorizeCurrentExecution = false,
            currentExecutableKatPresent = currentExecutableKatPresent,
            currentKatRunnerPresent = false,
            currentKatExecutorPresent = currentKatExecutorPresent,
            rawKatMaterialPresent = rawKatMaterialPresent,
            rawVectorBytesPresent = rawVectorBytesPresent,
            rawVectorHexPresent = rawVectorHexPresent,
            publicVectorBytesPresent = publicVectorBytesPresent,
            publicVectorHexPresent = publicVectorHexPresent,
            executableKatPresent = currentExecutableKatPresent,
            katExecutorPresent = currentKatExecutorPresent,
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
            futureExecutableKatCriteria = futureCriteria,
            forbiddenCurrentExecutableKatStates = forbiddenStates,
            sourceSet = SkaldVaultV1TestOnlyProviderIdentityExecutableKatAdmissionSourceSet.CommonTest,
            displayLabel = SkaldVaultV1TestOnlyProviderIdentityExecutableKatAdmissionSafeLabel(
                "executable KAT admission evidence",
            ),
        )
    }
}
