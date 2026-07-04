package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingSafeId(val value: String) {
    override fun toString(): String =
        "RedactedTestOnlyProviderIdentityKatCaseBindingSafeId"
}

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingSafeLabel(val value: String) {
    override fun toString(): String =
        "RedactedTestOnlyProviderIdentityKatCaseBindingSafeLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingSourceSet {
    CommonTest,
}

enum class SkaldVaultV1TestOnlyProviderIdentityKatCaseKind {
    PublicVectorMetadataCase,
}

enum class SkaldVaultV1TestOnlyProviderIdentityKatCasePurpose {
    InertIdentityMetadataOnly,
}

enum class SkaldVaultV1TestOnlyProviderIdentityKatCaseExecutionKind {
    NonExecutableCaseBinding,
}

data class SkaldVaultV1TestOnlyProviderIdentityKatCaseBinding(
    val caseId: SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingSafeId,
    val markerSafeId: SkaldVaultV1TestOnlyProviderIdentityMarkerSafeId,
    val fixtureId: SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalogSafeId,
    val vectorId: SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorFixtureSafeId,
    val sourceSet: SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingSourceSet,
    val caseKind: SkaldVaultV1TestOnlyProviderIdentityKatCaseKind,
    val casePurpose: SkaldVaultV1TestOnlyProviderIdentityKatCasePurpose,
    val caseExecutionKind: SkaldVaultV1TestOnlyProviderIdentityKatCaseExecutionKind,
    val markerCount: Int,
    val fixtureRowCount: Int,
    val publicVectorRowCount: Int,
    val caseCount: Int,
    val expectedSafeIdMatched: Boolean,
    val expectedFixtureIdMatched: Boolean,
    val expectedVectorIdMatched: Boolean,
    val expectedCaseIdMatched: Boolean,
    val caseIsCommonTestOnly: Boolean,
    val caseIsMetadataOnly: Boolean,
    val caseIsExecutable: Boolean,
    val caseContainsRawBytes: Boolean,
    val caseContainsHex: Boolean,
    val caseContainsCryptoMaterial: Boolean,
    val caseContainsWalletMaterial: Boolean,
    val caseContainsEndpointMaterial: Boolean,
    val caseContainsProviderHandle: Boolean,
    val caseAuthorizesProduction: Boolean,
    val caseAuthorizesProviderSelection: Boolean,
    val caseAuthorizesKatExecution: Boolean,
    val caseAuthorizesCryptoExecution: Boolean,
    val caseAuthorizesVaultPersistence: Boolean,
    val caseAuthorizesMainnet: Boolean,
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
    val displayLabel: SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingSafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityKatCaseBinding(redactedCaseId, redactedMarkerId, redactedFixtureId, redactedVectorId, commonTestOnly, metadataOnly, nonExecutable, nonAuthorizing)"
}

object SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingPolicy {
    private const val EXPECTED_SAFE_ID: String =
        "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"
    private const val EXPECTED_FIXTURE_ID: String =
        "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata"
    private const val EXPECTED_VECTOR_ID: String =
        "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata"
    private const val EXPECTED_CASE_ID: String =
        "skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata"

    fun currentKatCaseBinding(): SkaldVaultV1TestOnlyProviderIdentityKatCaseBinding {
        val marker = SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy.currentMarker()
        val fixtureCatalog =
            SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalogPolicy.currentKatFixtureCatalog()
        val fixtureValidation =
            SkaldVaultV1TestOnlyProviderIdentityKatFixtureValidationPolicy.currentKatFixtureValidationReport()
        val publicVectorAdmission =
            SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorAdmissionPolicy.currentPublicVectorAdmission()
        val publicVectorFixture =
            SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorFixturePolicy.currentPublicVectorFixture()
        val publicVectorValidation =
            SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationPolicy
                .currentPublicVectorValidationReport()
        val capabilityMatrix =
            SkaldVaultV1TestOnlyProviderIdentityCapabilityMatrixPolicy.currentCapabilityMatrix()
        val fixtureRow = fixtureCatalog.fixtureRows.singleOrNull()
        val publicVectorRow = publicVectorFixture.publicVectorRows.singleOrNull()
        val caseId = SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingSafeId(EXPECTED_CASE_ID)

        val markerCount =
            if (
                marker.safeId.value == EXPECTED_SAFE_ID &&
                capabilityMatrix.expectedSafeIdMatched &&
                fixtureCatalog.expectedSafeIdMatched &&
                fixtureValidation.expectedSafeIdMatched &&
                publicVectorAdmission.expectedSafeIdMatched &&
                publicVectorFixture.expectedSafeIdMatched &&
                publicVectorValidation.expectedSafeIdMatched &&
                fixtureRow?.markerSafeId?.value == EXPECTED_SAFE_ID &&
                publicVectorRow?.markerSafeId?.value == EXPECTED_SAFE_ID
            ) {
                1
            } else {
                0
            }
        val fixtureRowCount = fixtureCatalog.fixtureRows.size
        val publicVectorRowCount = publicVectorFixture.publicVectorRows.size
        val expectedSafeIdMatched = markerCount == 1
        val expectedFixtureIdMatched =
            fixtureValidation.expectedFixtureIdMatched &&
                publicVectorAdmission.expectedFixtureIdMatched &&
                publicVectorFixture.expectedFixtureIdMatched &&
                publicVectorValidation.expectedFixtureIdMatched &&
                fixtureRow?.fixtureId?.value == EXPECTED_FIXTURE_ID &&
                publicVectorRow?.fixtureId?.value == EXPECTED_FIXTURE_ID
        val expectedVectorIdMatched =
            publicVectorFixture.expectedVectorIdMatched &&
                publicVectorValidation.expectedVectorIdMatched &&
                publicVectorRow?.vectorId?.value == EXPECTED_VECTOR_ID
        val caseIsCommonTestOnly =
            marker.sourceSet == SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet.CommonTest &&
                capabilityMatrix.matrixIsCommonTestOnly &&
                fixtureCatalog.catalogIsCommonTestOnly &&
                fixtureValidation.validationIsCommonTestOnly &&
                publicVectorAdmission.admissionIsCommonTestOnly &&
                publicVectorFixture.fixtureIsCommonTestOnly &&
                publicVectorValidation.validationIsCommonTestOnly &&
                fixtureRow?.sourceSet == SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalogSourceSet.CommonTest &&
                publicVectorRow?.sourceSet ==
                SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorFixtureSourceSet.CommonTest
        val caseContainsRawBytes =
            publicVectorValidation.vectorContainsRawBytes ||
                publicVectorValidation.rawVectorBytesPresent ||
                publicVectorValidation.publicVectorBytesPresent
        val caseContainsHex =
            publicVectorValidation.vectorContainsHex ||
                publicVectorValidation.rawVectorHexPresent ||
                publicVectorValidation.publicVectorHexPresent
        val caseContainsCryptoMaterial =
            publicVectorValidation.vectorContainsCryptoMaterial ||
                publicVectorValidation.rawKatMaterialPresent
        val caseContainsWalletMaterial =
            publicVectorValidation.vectorContainsWalletMaterial
        val caseContainsEndpointMaterial =
            publicVectorValidation.vectorContainsEndpointMaterial
        val caseContainsProviderHandle =
            publicVectorValidation.vectorContainsProviderHandle
        val caseIsExecutable =
            publicVectorValidation.vectorIsExecutable ||
                publicVectorValidation.executableKatPresent ||
                publicVectorValidation.katExecutorPresent ||
                publicVectorRow?.vectorIsExecutable == true
        val caseIsMetadataOnly =
            publicVectorFixture.vectorIsPublicAndNonSecret &&
                publicVectorFixture.vectorIsTextOnly &&
                publicVectorValidation.vectorIsPublicAndNonSecret &&
                publicVectorValidation.vectorIsTextOnly &&
                publicVectorValidation.allValidationChecksPassed &&
                !caseIsExecutable &&
                !caseContainsRawBytes &&
                !caseContainsHex &&
                !caseContainsCryptoMaterial &&
                !caseContainsWalletMaterial &&
                !caseContainsEndpointMaterial &&
                !caseContainsProviderHandle
        val caseAuthorizesProduction =
            publicVectorFixture.fixtureIsProductionAuthorization ||
                publicVectorValidation.validationIsProductionAuthorization ||
                publicVectorRow?.vectorAuthorizesProduction == true
        val caseAuthorizesProviderSelection =
            publicVectorFixture.fixtureIsProviderSelectionAuthorization ||
                publicVectorValidation.validationIsProviderSelectionAuthorization ||
                publicVectorRow?.vectorAuthorizesProviderSelection == true
        val caseAuthorizesKatExecution =
            publicVectorFixture.fixtureIsKatExecutionAuthorization ||
                publicVectorValidation.validationIsKatExecutionAuthorization ||
                publicVectorRow?.vectorAuthorizesKatExecution == true
        val caseAuthorizesCryptoExecution =
            publicVectorFixture.fixtureIsCryptoAuthorization ||
                publicVectorValidation.validationIsCryptoAuthorization ||
                publicVectorRow?.vectorAuthorizesCryptoExecution == true
        val caseAuthorizesVaultPersistence =
            publicVectorFixture.fixtureIsVaultPersistenceAuthorization ||
                publicVectorValidation.validationIsVaultPersistenceAuthorization ||
                publicVectorRow?.vectorAuthorizesVaultPersistence == true
        val caseAuthorizesMainnet =
            publicVectorFixture.fixtureIsMainnetAuthorization ||
                publicVectorValidation.validationIsMainnetAuthorization ||
                publicVectorRow?.vectorAuthorizesMainnet == true

        val runtimeSelectable =
            capabilityMatrix.runtimeSelectable ||
                fixtureCatalog.runtimeSelectable ||
                fixtureValidation.runtimeSelectable ||
                publicVectorAdmission.runtimeSelectable ||
                publicVectorFixture.runtimeSelectable ||
                publicVectorValidation.runtimeSelectable ||
                fixtureRow?.runtimeSelectable == true ||
                publicVectorRow?.runtimeSelectable == true
        val registrySelectable =
            capabilityMatrix.registrySelectable ||
                fixtureCatalog.registrySelectable ||
                fixtureValidation.registrySelectable ||
                publicVectorAdmission.registrySelectable ||
                publicVectorFixture.registrySelectable ||
                publicVectorValidation.registrySelectable ||
                fixtureRow?.registrySelectable == true ||
                publicVectorRow?.registrySelectable == true
        val factoryReachable =
            capabilityMatrix.factoryReachable ||
                fixtureCatalog.factoryReachable ||
                fixtureValidation.factoryReachable ||
                publicVectorAdmission.factoryReachable ||
                publicVectorFixture.factoryReachable ||
                publicVectorValidation.factoryReachable ||
                fixtureRow?.factoryReachable == true ||
                publicVectorRow?.factoryReachable == true
        val dispatcherReachable =
            capabilityMatrix.dispatcherReachable ||
                fixtureCatalog.dispatcherReachable ||
                fixtureValidation.dispatcherReachable ||
                publicVectorAdmission.dispatcherReachable ||
                publicVectorFixture.dispatcherReachable ||
                publicVectorValidation.dispatcherReachable ||
                fixtureRow?.dispatcherReachable == true ||
                publicVectorRow?.dispatcherReachable == true
        val executorTargetable =
            capabilityMatrix.executorTargetable ||
                fixtureCatalog.executorTargetable ||
                fixtureValidation.executorTargetable ||
                publicVectorAdmission.executorTargetable ||
                publicVectorFixture.executorTargetable ||
                publicVectorValidation.executorTargetable ||
                fixtureRow?.executorTargetable == true ||
                publicVectorRow?.executorTargetable == true
        val providerKatExecutorReachable =
            capabilityMatrix.providerKatExecutorReachable ||
                fixtureCatalog.providerKatExecutorReachable ||
                fixtureValidation.providerKatExecutorReachable ||
                publicVectorAdmission.providerKatExecutorReachable ||
                publicVectorFixture.providerKatExecutorReachable ||
                publicVectorValidation.providerKatExecutorReachable ||
                fixtureRow?.providerKatExecutorReachable == true ||
                publicVectorRow?.providerKatExecutorReachable == true
        val providerOperationReachable =
            capabilityMatrix.providerOperationReachable ||
                fixtureCatalog.providerOperationReachable ||
                fixtureValidation.providerOperationReachable ||
                publicVectorAdmission.providerOperationReachable ||
                publicVectorFixture.providerOperationReachable ||
                publicVectorValidation.providerOperationReachable ||
                fixtureRow?.providerOperationReachable == true ||
                publicVectorRow?.providerOperationReachable == true
        val cryptoExecutionReachable =
            capabilityMatrix.cryptoExecutionReachable ||
                fixtureCatalog.cryptoExecutionReachable ||
                fixtureValidation.cryptoExecutionReachable ||
                publicVectorAdmission.cryptoExecutionReachable ||
                publicVectorFixture.cryptoExecutionReachable ||
                publicVectorValidation.cryptoExecutionReachable ||
                fixtureRow?.cryptoExecutionReachable == true ||
                publicVectorRow?.cryptoExecutionReachable == true
        val vaultLifecycleReachable =
            capabilityMatrix.vaultLifecycleReachable ||
                fixtureCatalog.vaultLifecycleReachable ||
                fixtureValidation.vaultLifecycleReachable ||
                publicVectorAdmission.vaultLifecycleReachable ||
                publicVectorFixture.vaultLifecycleReachable ||
                publicVectorValidation.vaultLifecycleReachable ||
                fixtureRow?.vaultLifecycleReachable == true ||
                publicVectorRow?.vaultLifecycleReachable == true
        val persistenceReachable =
            capabilityMatrix.persistenceReachable ||
                fixtureCatalog.persistenceReachable ||
                fixtureValidation.persistenceReachable ||
                publicVectorAdmission.persistenceReachable ||
                publicVectorFixture.persistenceReachable ||
                publicVectorValidation.persistenceReachable ||
                fixtureRow?.persistenceReachable == true ||
                publicVectorRow?.persistenceReachable == true
        val productionSyncReachable =
            capabilityMatrix.productionSyncReachable ||
                fixtureCatalog.productionSyncReachable ||
                fixtureValidation.productionSyncReachable ||
                publicVectorAdmission.productionSyncReachable ||
                publicVectorFixture.productionSyncReachable ||
                publicVectorValidation.productionSyncReachable ||
                fixtureRow?.productionSyncReachable == true ||
                publicVectorRow?.productionSyncReachable == true
        val backendClientReachable =
            capabilityMatrix.backendClientReachable ||
                fixtureCatalog.backendClientReachable ||
                fixtureValidation.backendClientReachable ||
                publicVectorAdmission.backendClientReachable ||
                publicVectorFixture.backendClientReachable ||
                publicVectorValidation.backendClientReachable ||
                fixtureRow?.backendClientReachable == true ||
                publicVectorRow?.backendClientReachable == true
        val bdkWalletStateReachable =
            capabilityMatrix.bdkWalletStateReachable ||
                fixtureCatalog.bdkWalletStateReachable ||
                fixtureValidation.bdkWalletStateReachable ||
                publicVectorAdmission.bdkWalletStateReachable ||
                publicVectorFixture.bdkWalletStateReachable ||
                publicVectorValidation.bdkWalletStateReachable ||
                fixtureRow?.bdkWalletStateReachable == true ||
                publicVectorRow?.bdkWalletStateReachable == true
        val settingsCodecReachable =
            capabilityMatrix.settingsCodecReachable ||
                fixtureCatalog.settingsCodecReachable ||
                fixtureValidation.settingsCodecReachable ||
                publicVectorAdmission.settingsCodecReachable ||
                publicVectorFixture.settingsCodecReachable ||
                publicVectorValidation.settingsCodecReachable ||
                fixtureRow?.settingsCodecReachable == true ||
                publicVectorRow?.settingsCodecReachable == true
        val uiSurfaceReachable =
            capabilityMatrix.uiSurfaceReachable ||
                fixtureCatalog.uiSurfaceReachable ||
                fixtureValidation.uiSurfaceReachable ||
                publicVectorAdmission.uiSurfaceReachable ||
                publicVectorFixture.uiSurfaceReachable ||
                publicVectorValidation.uiSurfaceReachable ||
                fixtureRow?.uiSurfaceReachable == true ||
                publicVectorRow?.uiSurfaceReachable == true
        val signingBroadcastingReachable =
            capabilityMatrix.signingBroadcastingReachable ||
                fixtureCatalog.signingBroadcastingReachable ||
                fixtureValidation.signingBroadcastingReachable ||
                publicVectorAdmission.signingBroadcastingReachable ||
                publicVectorFixture.signingBroadcastingReachable ||
                publicVectorValidation.signingBroadcastingReachable ||
                fixtureRow?.signingBroadcastingReachable == true ||
                publicVectorRow?.signingBroadcastingReachable == true
        val publicEndpointReachable =
            capabilityMatrix.publicEndpointReachable ||
                fixtureCatalog.publicEndpointReachable ||
                fixtureValidation.publicEndpointReachable ||
                publicVectorAdmission.publicEndpointReachable ||
                publicVectorFixture.publicEndpointReachable ||
                publicVectorValidation.publicEndpointReachable ||
                fixtureRow?.publicEndpointReachable == true ||
                publicVectorRow?.publicEndpointReachable == true
        val mainnetReachable =
            capabilityMatrix.mainnetReachable ||
                fixtureCatalog.mainnetReachable ||
                fixtureValidation.mainnetReachable ||
                publicVectorAdmission.mainnetReachable ||
                publicVectorFixture.mainnetReachable ||
                publicVectorValidation.mainnetReachable ||
                fixtureRow?.mainnetReachable == true ||
                publicVectorRow?.mainnetReachable == true
        val implementsVaultCryptoProvider =
            capabilityMatrix.implementsVaultCryptoProvider ||
                fixtureCatalog.implementsVaultCryptoProvider ||
                fixtureValidation.implementsVaultCryptoProvider ||
                publicVectorAdmission.implementsVaultCryptoProvider ||
                publicVectorFixture.implementsVaultCryptoProvider ||
                publicVectorValidation.implementsVaultCryptoProvider ||
                fixtureRow?.implementsVaultCryptoProvider == true ||
                publicVectorRow?.implementsVaultCryptoProvider == true
        val containsVaultCryptoProvider =
            capabilityMatrix.containsVaultCryptoProvider ||
                fixtureCatalog.containsVaultCryptoProvider ||
                fixtureValidation.containsVaultCryptoProvider ||
                publicVectorAdmission.containsVaultCryptoProvider ||
                publicVectorFixture.containsVaultCryptoProvider ||
                publicVectorValidation.containsVaultCryptoProvider ||
                fixtureRow?.containsVaultCryptoProvider == true ||
                publicVectorRow?.containsVaultCryptoProvider == true
        val canExecuteProviderOperations =
            capabilityMatrix.canExecuteProviderOperations ||
                fixtureCatalog.canExecuteProviderOperations ||
                fixtureValidation.canExecuteProviderOperations ||
                publicVectorAdmission.canExecuteProviderOperations ||
                publicVectorFixture.canExecuteProviderOperations ||
                publicVectorValidation.canExecuteProviderOperations ||
                (fixtureRow?.canExecuteProviderOperations ?: false) ||
                (publicVectorRow?.canExecuteProviderOperations ?: false)
        val canExecuteCrypto =
            capabilityMatrix.canExecuteCrypto ||
                fixtureCatalog.canExecuteCrypto ||
                fixtureValidation.canExecuteCrypto ||
                publicVectorAdmission.canExecuteCrypto ||
                publicVectorFixture.canExecuteCrypto ||
                publicVectorValidation.canExecuteCrypto ||
                (fixtureRow?.canExecuteCrypto ?: false) ||
                (publicVectorRow?.canExecuteCrypto ?: false)
        val canUseForVaultLifecycle =
            capabilityMatrix.canUseForVaultLifecycle ||
                fixtureCatalog.canUseForVaultLifecycle ||
                fixtureValidation.canUseForVaultLifecycle ||
                publicVectorAdmission.canUseForVaultLifecycle ||
                publicVectorFixture.canUseForVaultLifecycle ||
                publicVectorValidation.canUseForVaultLifecycle ||
                (fixtureRow?.canUseForVaultLifecycle ?: false) ||
                (publicVectorRow?.canUseForVaultLifecycle ?: false)
        val canUseForPersistence =
            capabilityMatrix.canUseForPersistence ||
                fixtureCatalog.canUseForPersistence ||
                fixtureValidation.canUseForPersistence ||
                publicVectorAdmission.canUseForPersistence ||
                publicVectorFixture.canUseForPersistence ||
                publicVectorValidation.canUseForPersistence ||
                (fixtureRow?.canUseForPersistence ?: false) ||
                (publicVectorRow?.canUseForPersistence ?: false)
        val canUseForSync =
            capabilityMatrix.canUseForSync ||
                fixtureCatalog.canUseForSync ||
                fixtureValidation.canUseForSync ||
                publicVectorAdmission.canUseForSync ||
                publicVectorFixture.canUseForSync ||
                publicVectorValidation.canUseForSync ||
                (fixtureRow?.canUseForSync ?: false) ||
                (publicVectorRow?.canUseForSync ?: false)
        val canUseForSigning =
            capabilityMatrix.canUseForSigning ||
                fixtureCatalog.canUseForSigning ||
                fixtureValidation.canUseForSigning ||
                publicVectorAdmission.canUseForSigning ||
                publicVectorFixture.canUseForSigning ||
                publicVectorValidation.canUseForSigning ||
                (fixtureRow?.canUseForSigning ?: false) ||
                (publicVectorRow?.canUseForSigning ?: false)
        val canUseForBroadcasting =
            capabilityMatrix.canUseForBroadcasting ||
                fixtureCatalog.canUseForBroadcasting ||
                fixtureValidation.canUseForBroadcasting ||
                publicVectorAdmission.canUseForBroadcasting ||
                publicVectorFixture.canUseForBroadcasting ||
                publicVectorValidation.canUseForBroadcasting ||
                (fixtureRow?.canUseForBroadcasting ?: false) ||
                (publicVectorRow?.canUseForBroadcasting ?: false)
        val canUseForMainnet =
            capabilityMatrix.canUseForMainnet ||
                fixtureCatalog.canUseForMainnet ||
                fixtureValidation.canUseForMainnet ||
                publicVectorAdmission.canUseForMainnet ||
                publicVectorFixture.canUseForMainnet ||
                publicVectorValidation.canUseForMainnet ||
                (fixtureRow?.canUseForMainnet ?: false) ||
                (publicVectorRow?.canUseForMainnet ?: false)
        val productionProviderSelectable =
            capabilityMatrix.productionProviderSelectable ||
                fixtureCatalog.productionProviderSelectable ||
                fixtureValidation.productionProviderSelectable ||
                publicVectorAdmission.productionProviderSelectable ||
                publicVectorFixture.productionProviderSelectable ||
                publicVectorValidation.productionProviderSelectable ||
                fixtureRow?.productionProviderSelectable == true ||
                publicVectorRow?.productionProviderSelectable == true

        return SkaldVaultV1TestOnlyProviderIdentityKatCaseBinding(
            caseId = caseId,
            markerSafeId = marker.safeId,
            fixtureId = fixtureRow?.fixtureId
                ?: SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalogSafeId(EXPECTED_FIXTURE_ID),
            vectorId = publicVectorRow?.vectorId
                ?: SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorFixtureSafeId(EXPECTED_VECTOR_ID),
            sourceSet = SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingSourceSet.CommonTest,
            caseKind = SkaldVaultV1TestOnlyProviderIdentityKatCaseKind.PublicVectorMetadataCase,
            casePurpose = SkaldVaultV1TestOnlyProviderIdentityKatCasePurpose.InertIdentityMetadataOnly,
            caseExecutionKind =
                SkaldVaultV1TestOnlyProviderIdentityKatCaseExecutionKind.NonExecutableCaseBinding,
            markerCount = markerCount,
            fixtureRowCount = fixtureRowCount,
            publicVectorRowCount = publicVectorRowCount,
            caseCount = 1,
            expectedSafeIdMatched = expectedSafeIdMatched,
            expectedFixtureIdMatched = expectedFixtureIdMatched,
            expectedVectorIdMatched = expectedVectorIdMatched,
            expectedCaseIdMatched = caseId.value == EXPECTED_CASE_ID,
            caseIsCommonTestOnly = caseIsCommonTestOnly,
            caseIsMetadataOnly = caseIsMetadataOnly,
            caseIsExecutable = caseIsExecutable,
            caseContainsRawBytes = caseContainsRawBytes,
            caseContainsHex = caseContainsHex,
            caseContainsCryptoMaterial = caseContainsCryptoMaterial,
            caseContainsWalletMaterial = caseContainsWalletMaterial,
            caseContainsEndpointMaterial = caseContainsEndpointMaterial,
            caseContainsProviderHandle = caseContainsProviderHandle,
            caseAuthorizesProduction = caseAuthorizesProduction,
            caseAuthorizesProviderSelection = caseAuthorizesProviderSelection,
            caseAuthorizesKatExecution = caseAuthorizesKatExecution,
            caseAuthorizesCryptoExecution = caseAuthorizesCryptoExecution,
            caseAuthorizesVaultPersistence = caseAuthorizesVaultPersistence,
            caseAuthorizesMainnet = caseAuthorizesMainnet,
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
            displayLabel = SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingSafeLabel(
                "non-executable inert identity metadata KAT case",
            ),
        )
    }
}
