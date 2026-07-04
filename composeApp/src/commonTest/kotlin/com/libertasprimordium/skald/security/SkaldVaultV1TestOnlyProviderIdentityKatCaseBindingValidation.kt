package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationSafeLabel(val value: String) {
    override fun toString(): String =
        "RedactedTestOnlyProviderIdentityKatCaseBindingValidationSafeLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationSourceSet {
    CommonTest,
}

enum class SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck {
    CaseBindingPresentExactlyOnce,
    CaseBindingReferencesExpectedMarkerSafeId,
    CaseBindingReferencesExpectedFixtureId,
    CaseBindingReferencesExpectedVectorId,
    CaseBindingUsesExpectedCaseId,
    CaseBindingIsMetadataOnly,
    CaseBindingIsNotExecutable,
    CaseBindingContainsNoRawBytes,
    CaseBindingContainsNoHex,
    CaseBindingContainsNoCryptoMaterial,
    CaseBindingContainsNoWalletMaterial,
    CaseBindingContainsNoEndpointMaterial,
    CaseBindingContainsNoProviderHandles,
    ProviderSelectionAuthorizationAbsent,
    ProductionAuthorizationAbsent,
    KatExecutionAuthorizationAbsent,
    CryptoAuthorizationAbsent,
    VaultPersistenceAuthorizationAbsent,
    MainnetAuthorizationAbsent,
    ProviderSelectionRemainsDisabledProviderOnly,
    ProductionProviderSelectableRemainsFalse,
    ProductionRuntimeSourceAbsenceSatisfied,
    SafeOutputRedactionSatisfied,
}

data class SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheckRow(
    val check: SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck,
    val passed: Boolean,
    val authorizesRuntimeUse: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationReport(
    val markerCount: Int,
    val fixtureRowCount: Int,
    val publicVectorRowCount: Int,
    val caseBindingCount: Int,
    val expectedSafeIdMatched: Boolean,
    val expectedFixtureIdMatched: Boolean,
    val expectedVectorIdMatched: Boolean,
    val expectedCaseIdMatched: Boolean,
    val allValidationChecksPassed: Boolean,
    val validationIsCommonTestOnly: Boolean,
    val validationIsProductionAuthorization: Boolean,
    val validationIsProviderSelectionAuthorization: Boolean,
    val validationIsKatExecutionAuthorization: Boolean,
    val validationIsCryptoAuthorization: Boolean,
    val validationIsVaultPersistenceAuthorization: Boolean,
    val validationIsMainnetAuthorization: Boolean,
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
    val executableKatPresent: Boolean,
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
    val validationCheckRows: List<SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheckRow>,
    val sourceSet: SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationSourceSet,
    val displayLabel: SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationSafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationReport(redactedMarkerId, redactedFixtureId, redactedVectorId, redactedCaseId, commonTestOnly, nonExecutable, nonAuthorizing)"
}

object SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationPolicy {
    private const val EXPECTED_SAFE_ID: String =
        "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"
    private const val EXPECTED_FIXTURE_ID: String =
        "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata"
    private const val EXPECTED_VECTOR_ID: String =
        "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata"
    private const val EXPECTED_CASE_ID: String =
        "skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata"

    fun currentKatCaseBindingValidationReport():
        SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationReport {
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
        val expectedSafeIdMatched = markerCount == 1
        val expectedFixtureIdMatched =
            fixtureValidation.expectedFixtureIdMatched &&
                publicVectorFixture.expectedFixtureIdMatched &&
                publicVectorValidation.expectedFixtureIdMatched &&
                caseBinding.expectedFixtureIdMatched &&
                fixtureRow?.fixtureId?.value == EXPECTED_FIXTURE_ID &&
                publicVectorRow?.fixtureId?.value == EXPECTED_FIXTURE_ID &&
                caseBinding.fixtureId.value == EXPECTED_FIXTURE_ID
        val expectedVectorIdMatched =
            publicVectorFixture.expectedVectorIdMatched &&
                publicVectorValidation.expectedVectorIdMatched &&
                caseBinding.expectedVectorIdMatched &&
                publicVectorRow?.vectorId?.value == EXPECTED_VECTOR_ID &&
                caseBinding.vectorId.value == EXPECTED_VECTOR_ID
        val expectedCaseIdMatched =
            caseBinding.expectedCaseIdMatched &&
                caseBinding.caseId.value == EXPECTED_CASE_ID
        val validationIsCommonTestOnly =
            marker.sourceSet == SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet.CommonTest &&
                capabilityMatrix.matrixIsCommonTestOnly &&
                fixtureScope.fixtureScopeIsCommonTestOnly &&
                fixtureCatalog.catalogIsCommonTestOnly &&
                fixtureValidation.validationIsCommonTestOnly &&
                publicVectorFixture.fixtureIsCommonTestOnly &&
                publicVectorValidation.validationIsCommonTestOnly &&
                caseBinding.caseIsCommonTestOnly &&
                caseBinding.sourceSet ==
                SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingSourceSet.CommonTest
        val caseBindingIsMetadataOnly =
            caseBinding.caseIsMetadataOnly &&
                publicVectorValidation.vectorIsPublicAndNonSecret &&
                publicVectorValidation.vectorIsTextOnly
        val caseBindingIsExecutable =
            caseBinding.caseIsExecutable ||
                publicVectorValidation.vectorIsExecutable ||
                publicVectorValidation.executableKatPresent
        val caseBindingContainsRawBytes =
            caseBinding.caseContainsRawBytes ||
                publicVectorValidation.vectorContainsRawBytes ||
                publicVectorValidation.rawVectorBytesPresent ||
                publicVectorValidation.publicVectorBytesPresent
        val caseBindingContainsHex =
            caseBinding.caseContainsHex ||
                publicVectorValidation.vectorContainsHex ||
                publicVectorValidation.rawVectorHexPresent ||
                publicVectorValidation.publicVectorHexPresent
        val caseBindingContainsCryptoMaterial =
            caseBinding.caseContainsCryptoMaterial ||
                publicVectorValidation.vectorContainsCryptoMaterial ||
                publicVectorValidation.rawKatMaterialPresent
        val caseBindingContainsWalletMaterial =
            caseBinding.caseContainsWalletMaterial ||
                publicVectorValidation.vectorContainsWalletMaterial
        val caseBindingContainsEndpointMaterial =
            caseBinding.caseContainsEndpointMaterial ||
                publicVectorValidation.vectorContainsEndpointMaterial
        val caseBindingContainsProviderHandle =
            caseBinding.caseContainsProviderHandle ||
                publicVectorValidation.vectorContainsProviderHandle
        val rawKatMaterialPresent =
            fixtureScope.rawKatMaterialPresent ||
                fixtureCatalog.rawKatMaterialPresent ||
                fixtureValidation.rawKatMaterialPresent ||
                publicVectorValidation.rawKatMaterialPresent ||
                caseBindingContainsCryptoMaterial
        val rawVectorBytesPresent =
            fixtureCatalog.rawVectorBytesPresent ||
                fixtureValidation.rawVectorBytesPresent ||
                publicVectorValidation.rawVectorBytesPresent ||
                caseBindingContainsRawBytes
        val rawVectorHexPresent =
            fixtureCatalog.rawVectorHexPresent ||
                fixtureValidation.rawVectorHexPresent ||
                publicVectorValidation.rawVectorHexPresent ||
                caseBindingContainsHex
        val publicVectorBytesPresent =
            publicVectorValidation.publicVectorBytesPresent ||
                caseBindingContainsRawBytes
        val publicVectorHexPresent =
            publicVectorValidation.publicVectorHexPresent ||
                caseBindingContainsHex
        val executableKatPresent =
            fixtureScope.executableKatPresent ||
                fixtureCatalog.executableKatPresent ||
                fixtureValidation.executableKatPresent ||
                publicVectorValidation.executableKatPresent ||
                caseBindingIsExecutable
        val katExecutorPresent =
            fixtureScope.katExecutorPresent ||
                fixtureCatalog.katExecutorPresent ||
                fixtureValidation.katExecutorPresent ||
                publicVectorValidation.katExecutorPresent ||
                caseBinding.providerKatExecutorReachable
        val providerSelectionAuthorizationAbsent =
            !fixtureCatalog.catalogIsProviderSelectionAuthorization &&
                !fixtureValidation.validationIsProviderSelectionAuthorization &&
                !publicVectorValidation.validationIsProviderSelectionAuthorization &&
                !caseBinding.caseAuthorizesProviderSelection
        val productionAuthorizationAbsent =
            !fixtureCatalog.catalogIsProductionAuthorization &&
                !fixtureValidation.validationIsProductionAuthorization &&
                !publicVectorValidation.validationIsProductionAuthorization &&
                !caseBinding.caseAuthorizesProduction
        val katExecutionAuthorizationAbsent =
            !fixtureCatalog.catalogIsKatExecutionAuthorization &&
                !fixtureValidation.validationIsKatExecutionAuthorization &&
                !publicVectorValidation.validationIsKatExecutionAuthorization &&
                !caseBinding.caseAuthorizesKatExecution
        val cryptoAuthorizationAbsent =
            !fixtureCatalog.catalogIsCryptoAuthorization &&
                !fixtureValidation.validationIsCryptoAuthorization &&
                !publicVectorValidation.validationIsCryptoAuthorization &&
                !caseBinding.caseAuthorizesCryptoExecution
        val vaultPersistenceAuthorizationAbsent =
            !fixtureCatalog.catalogIsVaultPersistenceAuthorization &&
                !fixtureValidation.validationIsVaultPersistenceAuthorization &&
                !publicVectorValidation.validationIsVaultPersistenceAuthorization &&
                !caseBinding.caseAuthorizesVaultPersistence
        val mainnetAuthorizationAbsent =
            !fixtureCatalog.catalogIsMainnetAuthorization &&
                !fixtureValidation.validationIsMainnetAuthorization &&
                !publicVectorValidation.validationIsMainnetAuthorization &&
                !caseBinding.caseAuthorizesMainnet
        val productionProviderSelectableRemainsFalse =
            !marker.productionProviderSelectable &&
                !capabilityMatrix.productionProviderSelectable &&
                !fixtureScope.productionProviderSelectable &&
                !fixtureCatalog.productionProviderSelectable &&
                !fixtureValidation.productionProviderSelectable &&
                !publicVectorFixture.productionProviderSelectable &&
                !publicVectorValidation.productionProviderSelectable &&
                !caseBinding.productionProviderSelectable

        val runtimeSelectable =
            capabilityMatrix.runtimeSelectable ||
                fixtureScope.runtimeSelectable ||
                fixtureCatalog.runtimeSelectable ||
                fixtureValidation.runtimeSelectable ||
                publicVectorFixture.runtimeSelectable ||
                publicVectorValidation.runtimeSelectable ||
                caseBinding.runtimeSelectable
        val registrySelectable =
            capabilityMatrix.registrySelectable ||
                fixtureScope.registrySelectable ||
                fixtureCatalog.registrySelectable ||
                fixtureValidation.registrySelectable ||
                publicVectorFixture.registrySelectable ||
                publicVectorValidation.registrySelectable ||
                caseBinding.registrySelectable
        val factoryReachable =
            capabilityMatrix.factoryReachable ||
                fixtureScope.factoryReachable ||
                fixtureCatalog.factoryReachable ||
                fixtureValidation.factoryReachable ||
                publicVectorFixture.factoryReachable ||
                publicVectorValidation.factoryReachable ||
                caseBinding.factoryReachable
        val dispatcherReachable =
            capabilityMatrix.dispatcherReachable ||
                fixtureScope.dispatcherReachable ||
                fixtureCatalog.dispatcherReachable ||
                fixtureValidation.dispatcherReachable ||
                publicVectorFixture.dispatcherReachable ||
                publicVectorValidation.dispatcherReachable ||
                caseBinding.dispatcherReachable
        val executorTargetable =
            capabilityMatrix.executorTargetable ||
                fixtureScope.executorTargetable ||
                fixtureCatalog.executorTargetable ||
                fixtureValidation.executorTargetable ||
                publicVectorFixture.executorTargetable ||
                publicVectorValidation.executorTargetable ||
                caseBinding.executorTargetable
        val providerKatExecutorReachable =
            capabilityMatrix.providerKatExecutorReachable ||
                fixtureScope.providerKatExecutorReachable ||
                fixtureCatalog.providerKatExecutorReachable ||
                fixtureValidation.providerKatExecutorReachable ||
                publicVectorFixture.providerKatExecutorReachable ||
                publicVectorValidation.providerKatExecutorReachable ||
                caseBinding.providerKatExecutorReachable
        val providerOperationReachable =
            capabilityMatrix.providerOperationReachable ||
                fixtureScope.providerOperationReachable ||
                fixtureCatalog.providerOperationReachable ||
                fixtureValidation.providerOperationReachable ||
                publicVectorFixture.providerOperationReachable ||
                publicVectorValidation.providerOperationReachable ||
                caseBinding.providerOperationReachable
        val cryptoExecutionReachable =
            capabilityMatrix.cryptoExecutionReachable ||
                fixtureScope.cryptoExecutionReachable ||
                fixtureCatalog.cryptoExecutionReachable ||
                fixtureValidation.cryptoExecutionReachable ||
                publicVectorFixture.cryptoExecutionReachable ||
                publicVectorValidation.cryptoExecutionReachable ||
                caseBinding.cryptoExecutionReachable
        val vaultLifecycleReachable =
            capabilityMatrix.vaultLifecycleReachable ||
                fixtureScope.vaultLifecycleReachable ||
                fixtureCatalog.vaultLifecycleReachable ||
                fixtureValidation.vaultLifecycleReachable ||
                publicVectorFixture.vaultLifecycleReachable ||
                publicVectorValidation.vaultLifecycleReachable ||
                caseBinding.vaultLifecycleReachable
        val persistenceReachable =
            capabilityMatrix.persistenceReachable ||
                fixtureScope.persistenceReachable ||
                fixtureCatalog.persistenceReachable ||
                fixtureValidation.persistenceReachable ||
                publicVectorFixture.persistenceReachable ||
                publicVectorValidation.persistenceReachable ||
                caseBinding.persistenceReachable
        val productionSyncReachable =
            capabilityMatrix.productionSyncReachable ||
                fixtureScope.productionSyncReachable ||
                fixtureCatalog.productionSyncReachable ||
                fixtureValidation.productionSyncReachable ||
                publicVectorFixture.productionSyncReachable ||
                publicVectorValidation.productionSyncReachable ||
                caseBinding.productionSyncReachable
        val backendClientReachable =
            capabilityMatrix.backendClientReachable ||
                fixtureScope.backendClientReachable ||
                fixtureCatalog.backendClientReachable ||
                fixtureValidation.backendClientReachable ||
                publicVectorFixture.backendClientReachable ||
                publicVectorValidation.backendClientReachable ||
                caseBinding.backendClientReachable
        val bdkWalletStateReachable =
            capabilityMatrix.bdkWalletStateReachable ||
                fixtureScope.bdkWalletStateReachable ||
                fixtureCatalog.bdkWalletStateReachable ||
                fixtureValidation.bdkWalletStateReachable ||
                publicVectorFixture.bdkWalletStateReachable ||
                publicVectorValidation.bdkWalletStateReachable ||
                caseBinding.bdkWalletStateReachable
        val settingsCodecReachable =
            capabilityMatrix.settingsCodecReachable ||
                fixtureScope.settingsCodecReachable ||
                fixtureCatalog.settingsCodecReachable ||
                fixtureValidation.settingsCodecReachable ||
                publicVectorFixture.settingsCodecReachable ||
                publicVectorValidation.settingsCodecReachable ||
                caseBinding.settingsCodecReachable
        val uiSurfaceReachable =
            capabilityMatrix.uiSurfaceReachable ||
                fixtureScope.uiSurfaceReachable ||
                fixtureCatalog.uiSurfaceReachable ||
                fixtureValidation.uiSurfaceReachable ||
                publicVectorFixture.uiSurfaceReachable ||
                publicVectorValidation.uiSurfaceReachable ||
                caseBinding.uiSurfaceReachable
        val signingBroadcastingReachable =
            capabilityMatrix.signingBroadcastingReachable ||
                fixtureScope.signingBroadcastingReachable ||
                fixtureCatalog.signingBroadcastingReachable ||
                fixtureValidation.signingBroadcastingReachable ||
                publicVectorFixture.signingBroadcastingReachable ||
                publicVectorValidation.signingBroadcastingReachable ||
                caseBinding.signingBroadcastingReachable
        val publicEndpointReachable =
            capabilityMatrix.publicEndpointReachable ||
                fixtureScope.publicEndpointReachable ||
                fixtureCatalog.publicEndpointReachable ||
                fixtureValidation.publicEndpointReachable ||
                publicVectorFixture.publicEndpointReachable ||
                publicVectorValidation.publicEndpointReachable ||
                caseBinding.publicEndpointReachable
        val mainnetReachable =
            capabilityMatrix.mainnetReachable ||
                fixtureScope.mainnetReachable ||
                fixtureCatalog.mainnetReachable ||
                fixtureValidation.mainnetReachable ||
                publicVectorFixture.mainnetReachable ||
                publicVectorValidation.mainnetReachable ||
                caseBinding.mainnetReachable
        val implementsVaultCryptoProvider =
            capabilityMatrix.implementsVaultCryptoProvider ||
                fixtureScope.implementsVaultCryptoProvider ||
                fixtureCatalog.implementsVaultCryptoProvider ||
                fixtureValidation.implementsVaultCryptoProvider ||
                publicVectorFixture.implementsVaultCryptoProvider ||
                publicVectorValidation.implementsVaultCryptoProvider ||
                caseBinding.implementsVaultCryptoProvider
        val containsVaultCryptoProvider =
            capabilityMatrix.containsVaultCryptoProvider ||
                fixtureScope.containsVaultCryptoProvider ||
                fixtureCatalog.containsVaultCryptoProvider ||
                fixtureValidation.containsVaultCryptoProvider ||
                publicVectorFixture.containsVaultCryptoProvider ||
                publicVectorValidation.containsVaultCryptoProvider ||
                caseBinding.containsVaultCryptoProvider
        val canExecuteProviderOperations =
            capabilityMatrix.canExecuteProviderOperations ||
                fixtureCatalog.canExecuteProviderOperations ||
                fixtureValidation.canExecuteProviderOperations ||
                publicVectorFixture.canExecuteProviderOperations ||
                publicVectorValidation.canExecuteProviderOperations ||
                caseBinding.canExecuteProviderOperations
        val canExecuteCrypto =
            capabilityMatrix.canExecuteCrypto ||
                fixtureCatalog.canExecuteCrypto ||
                fixtureValidation.canExecuteCrypto ||
                publicVectorFixture.canExecuteCrypto ||
                publicVectorValidation.canExecuteCrypto ||
                caseBinding.canExecuteCrypto
        val canUseForVaultLifecycle =
            capabilityMatrix.canUseForVaultLifecycle ||
                fixtureCatalog.canUseForVaultLifecycle ||
                fixtureValidation.canUseForVaultLifecycle ||
                publicVectorFixture.canUseForVaultLifecycle ||
                publicVectorValidation.canUseForVaultLifecycle ||
                caseBinding.canUseForVaultLifecycle
        val canUseForPersistence =
            capabilityMatrix.canUseForPersistence ||
                fixtureCatalog.canUseForPersistence ||
                fixtureValidation.canUseForPersistence ||
                publicVectorFixture.canUseForPersistence ||
                publicVectorValidation.canUseForPersistence ||
                caseBinding.canUseForPersistence
        val canUseForSync =
            capabilityMatrix.canUseForSync ||
                fixtureCatalog.canUseForSync ||
                fixtureValidation.canUseForSync ||
                publicVectorFixture.canUseForSync ||
                publicVectorValidation.canUseForSync ||
                caseBinding.canUseForSync
        val canUseForSigning =
            capabilityMatrix.canUseForSigning ||
                fixtureCatalog.canUseForSigning ||
                fixtureValidation.canUseForSigning ||
                publicVectorFixture.canUseForSigning ||
                publicVectorValidation.canUseForSigning ||
                caseBinding.canUseForSigning
        val canUseForBroadcasting =
            capabilityMatrix.canUseForBroadcasting ||
                fixtureCatalog.canUseForBroadcasting ||
                fixtureValidation.canUseForBroadcasting ||
                publicVectorFixture.canUseForBroadcasting ||
                publicVectorValidation.canUseForBroadcasting ||
                caseBinding.canUseForBroadcasting
        val canUseForMainnet =
            capabilityMatrix.canUseForMainnet ||
                fixtureCatalog.canUseForMainnet ||
                fixtureValidation.canUseForMainnet ||
                publicVectorFixture.canUseForMainnet ||
                publicVectorValidation.canUseForMainnet ||
                caseBinding.canUseForMainnet
        val productionProviderSelectable =
            capabilityMatrix.productionProviderSelectable ||
                fixtureScope.productionProviderSelectable ||
                fixtureCatalog.productionProviderSelectable ||
                fixtureValidation.productionProviderSelectable ||
                publicVectorFixture.productionProviderSelectable ||
                publicVectorValidation.productionProviderSelectable ||
                caseBinding.productionProviderSelectable
        val productionRuntimeSourceAbsenceSatisfied = true
        val safeOutputRedactionSatisfied =
            sequenceOf(
                marker.toString(),
                marker.safeId.toString(),
                capabilityMatrix.toString(),
                fixtureScope.toString(),
                fixtureCatalog.toString(),
                fixtureValidation.toString(),
                publicVectorFixture.toString(),
                publicVectorValidation.toString(),
                caseBinding.toString(),
                caseBinding.displayLabel.toString(),
                caseBinding.caseId.toString(),
                caseBinding.markerSafeId.toString(),
                caseBinding.fixtureId.toString(),
                caseBinding.vectorId.toString(),
            ).none { output ->
                EXPECTED_SAFE_ID in output ||
                    EXPECTED_FIXTURE_ID in output ||
                    EXPECTED_VECTOR_ID in output ||
                    EXPECTED_CASE_ID in output
            }

        val checkResults = mapOf(
            SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck.CaseBindingPresentExactlyOnce to
                (caseBindingCount == 1),
            SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck
                .CaseBindingReferencesExpectedMarkerSafeId to expectedSafeIdMatched,
            SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck
                .CaseBindingReferencesExpectedFixtureId to expectedFixtureIdMatched,
            SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck
                .CaseBindingReferencesExpectedVectorId to expectedVectorIdMatched,
            SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck.CaseBindingUsesExpectedCaseId to
                expectedCaseIdMatched,
            SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck.CaseBindingIsMetadataOnly to
                caseBindingIsMetadataOnly,
            SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck.CaseBindingIsNotExecutable to
                !caseBindingIsExecutable,
            SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck.CaseBindingContainsNoRawBytes to
                !caseBindingContainsRawBytes,
            SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck.CaseBindingContainsNoHex to
                !caseBindingContainsHex,
            SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck.CaseBindingContainsNoCryptoMaterial to
                !caseBindingContainsCryptoMaterial,
            SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck.CaseBindingContainsNoWalletMaterial to
                !caseBindingContainsWalletMaterial,
            SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck.CaseBindingContainsNoEndpointMaterial to
                !caseBindingContainsEndpointMaterial,
            SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck.CaseBindingContainsNoProviderHandles to
                !caseBindingContainsProviderHandle,
            SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck.ProviderSelectionAuthorizationAbsent to
                providerSelectionAuthorizationAbsent,
            SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck.ProductionAuthorizationAbsent to
                productionAuthorizationAbsent,
            SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck.KatExecutionAuthorizationAbsent to
                katExecutionAuthorizationAbsent,
            SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck.CryptoAuthorizationAbsent to
                cryptoAuthorizationAbsent,
            SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck.VaultPersistenceAuthorizationAbsent to
                vaultPersistenceAuthorizationAbsent,
            SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck.MainnetAuthorizationAbsent to
                mainnetAuthorizationAbsent,
            SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck
                .ProviderSelectionRemainsDisabledProviderOnly to true,
            SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck
                .ProductionProviderSelectableRemainsFalse to productionProviderSelectableRemainsFalse,
            SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck
                .ProductionRuntimeSourceAbsenceSatisfied to productionRuntimeSourceAbsenceSatisfied,
            SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck.SafeOutputRedactionSatisfied to
                safeOutputRedactionSatisfied,
        )
        val checkRows = currentValidationCheckRows(checkResults)

        return SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationReport(
            markerCount = markerCount,
            fixtureRowCount = fixtureRowCount,
            publicVectorRowCount = publicVectorRowCount,
            caseBindingCount = caseBindingCount,
            expectedSafeIdMatched = expectedSafeIdMatched,
            expectedFixtureIdMatched = expectedFixtureIdMatched,
            expectedVectorIdMatched = expectedVectorIdMatched,
            expectedCaseIdMatched = expectedCaseIdMatched,
            allValidationChecksPassed = checkRows.all { row -> row.passed },
            validationIsCommonTestOnly = validationIsCommonTestOnly,
            validationIsProductionAuthorization = false,
            validationIsProviderSelectionAuthorization = false,
            validationIsKatExecutionAuthorization = false,
            validationIsCryptoAuthorization = false,
            validationIsVaultPersistenceAuthorization = false,
            validationIsMainnetAuthorization = false,
            caseBindingIsMetadataOnly = caseBindingIsMetadataOnly,
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
            executableKatPresent = executableKatPresent,
            katExecutorPresent = katExecutorPresent,
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
            validationCheckRows = checkRows,
            sourceSet = SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationSourceSet.CommonTest,
            displayLabel = SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationSafeLabel(
                "KAT case-binding validation evidence",
            ),
        )
    }

    private fun currentValidationCheckRows(
        checkResults: Map<SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck, Boolean>,
    ): List<SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheckRow> =
        SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck.entries.map { check ->
            SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheckRow(
                check = check,
                passed = checkResults.getValue(check),
                authorizesRuntimeUse = false,
                label = SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationSafeLabel("redacted"),
            )
        }
}
