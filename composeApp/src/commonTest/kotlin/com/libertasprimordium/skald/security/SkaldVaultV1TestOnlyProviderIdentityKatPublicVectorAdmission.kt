package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorAdmissionSafeLabel(val value: String) {
    override fun toString(): String =
        "RedactedTestOnlyProviderIdentityKatPublicVectorAdmissionSafeLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorAdmissionSourceSet {
    CommonTest,
}

enum class SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorAdmissionOutcome {
    PublicVectorAdmissionModeled,
    FutureVectorMaterialRequiresSeparateBranch,
    CurrentVectorMaterialNotPresent,
    ExecutableKatNotPresent,
    KatExecutorNotPresent,
    ProviderSelectionAuthorizationAbsent,
    ProductionAuthorizationAbsent,
    KatExecutionAuthorizationAbsent,
    CryptoAuthorizationAbsent,
    VaultPersistenceAuthorizationAbsent,
    MainnetAuthorizationAbsent,
}

enum class SkaldVaultV1TestOnlyProviderIdentityKatFuturePublicVectorCriterion {
    FutureBranchMustBeExplicitlyApproved,
    FutureVectorsMustBePublicAndNonSecret,
    FutureVectorsMustBeCommonTestOnly,
    FutureVectorsMustBeSmallAndReviewable,
    FutureVectorsMustNotContainWalletDescriptors,
    FutureVectorsMustNotContainWalletCredentials,
    FutureVectorsMustNotContainPrivateKeys,
    FutureVectorsMustNotContainSeedsOrMnemonics,
    FutureVectorsMustNotContainNsecMaterial,
    FutureVectorsMustNotContainEndpoints,
    FutureVectorsMustNotContainBackendCredentials,
    FutureVectorsMustNotContainRealWalletData,
    FutureVectorsMustNotEnableKdfHkdfHmacAeadExecution,
    FutureVectorsMustNotEnableProviderOperationExecution,
    FutureVectorsMustNotEnableProviderSelection,
    FutureVectorsMustNotEnableKatExecutor,
    FutureVectorsMustNotEnableVaultPersistence,
    FutureVectorsMustRemainCoveredBySourceGuards,
    FutureVectorsMustRemainRedactedInOutput,
}

enum class SkaldVaultV1TestOnlyProviderIdentityKatForbiddenCurrentVectorState {
    RawVectorMaterialPresent,
    PublicVectorBytesPresent,
    PublicVectorHexPresent,
    ExecutableKatPresent,
    KatExecutorPresent,
    ProviderOperationExecutionPresent,
    CryptoExecutionPresent,
    ProviderSelectionAuthorizationPresent,
    ProductionAuthorizationPresent,
    VaultPersistenceAuthorizationPresent,
    MainnetAuthorizationPresent,
}

data class SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorAdmissionOutcomeRow(
    val outcome: SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorAdmissionOutcome,
    val modeled: Boolean,
    val authorizesCurrentVectors: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorAdmissionSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityKatFuturePublicVectorCriterionRow(
    val criterion: SkaldVaultV1TestOnlyProviderIdentityKatFuturePublicVectorCriterion,
    val requiredForFutureBranch: Boolean,
    val satisfiedNow: Boolean,
    val authorizesCurrentVectors: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorAdmissionSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityKatForbiddenCurrentVectorStateRow(
    val state: SkaldVaultV1TestOnlyProviderIdentityKatForbiddenCurrentVectorState,
    val forbiddenNow: Boolean,
    val presentNow: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorAdmissionSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorAdmission(
    val markerCount: Int,
    val inventoryCount: Int,
    val profileCount: Int,
    val validationReportCount: Int,
    val reachabilityProofCount: Int,
    val capabilityMatrixCount: Int,
    val fixtureScopeCount: Int,
    val fixtureCatalogCount: Int,
    val fixtureValidationCount: Int,
    val fixtureRowCount: Int,
    val expectedSafeIdMatched: Boolean,
    val expectedFixtureIdMatched: Boolean,
    val admissionIsCommonTestOnly: Boolean,
    val admissionIsProductionAuthorization: Boolean,
    val admissionIsProviderSelectionAuthorization: Boolean,
    val admissionIsKatExecutionAuthorization: Boolean,
    val admissionIsCryptoAuthorization: Boolean,
    val admissionIsVaultPersistenceAuthorization: Boolean,
    val admissionIsMainnetAuthorization: Boolean,
    val futureVectorCriteriaModeled: Boolean,
    val futureVectorCriteriaAuthorizeCurrentVectors: Boolean,
    val currentVectorMaterialPresent: Boolean,
    val rawKatMaterialPresent: Boolean,
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
    val admissionOutcomeRows: List<SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorAdmissionOutcomeRow>,
    val futurePublicVectorCriteriaRows: List<SkaldVaultV1TestOnlyProviderIdentityKatFuturePublicVectorCriterionRow>,
    val forbiddenCurrentVectorStateRows: List<SkaldVaultV1TestOnlyProviderIdentityKatForbiddenCurrentVectorStateRow>,
    val sourceSet: SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorAdmissionSourceSet,
    val displayLabel: SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorAdmissionSafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorAdmission(redactedFixtureId, redactedMarkerId, commonTestOnly, vectorFree, nonAuthorizing)"
}

object SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorAdmissionPolicy {
    private const val EXPECTED_SAFE_ID: String =
        "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"
    private const val EXPECTED_FIXTURE_ID: String =
        "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata"

    fun currentPublicVectorAdmission(): SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorAdmission {
        val inventory = SkaldVaultV1TestOnlyProviderIdentityInventory
        val marker = inventory.markers.single()
        val profile = SkaldVaultV1TestOnlyProviderIdentityProfilePolicy.currentProfile()
        val validationReport =
            SkaldVaultV1TestOnlyProviderIdentityProfileValidationPolicy.currentValidationReport()
        val reachabilityProof =
            SkaldVaultV1TestOnlyProviderIdentityReachabilityProofPolicy.currentReachabilityProof()
        val capabilityMatrix =
            SkaldVaultV1TestOnlyProviderIdentityCapabilityMatrixPolicy.currentCapabilityMatrix()
        val fixtureScope =
            SkaldVaultV1TestOnlyProviderIdentityKatFixtureScopePolicy.currentKatFixtureScope()
        val fixtureCatalog =
            SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalogPolicy.currentKatFixtureCatalog()
        val fixtureValidation =
            SkaldVaultV1TestOnlyProviderIdentityKatFixtureValidationPolicy.currentKatFixtureValidationReport()
        val fixtureRow = fixtureCatalog.fixtureRows.singleOrNull()
        val outcomeRows = currentAdmissionOutcomeRows()
        val criteriaRows = currentFuturePublicVectorCriteriaRows()
        val forbiddenRows = currentForbiddenCurrentVectorStateRows()

        val markerCount = inventory.markers.count { item -> item.safeId.value == marker.safeId.value }
        val inventoryCount = if (inventory.markerCount == 1) 1 else 0
        val profileCount = if (profile.marker.safeId.value == marker.safeId.value && profile.inventorySize == 1) 1 else 0
        val validationReportCount =
            if (
                validationReport.markerCount == 1 &&
                validationReport.inventoryCount == 1 &&
                validationReport.profileCount == 1
            ) {
                1
            } else {
                0
            }
        val reachabilityProofCount =
            if (
                reachabilityProof.markerCount == 1 &&
                reachabilityProof.inventoryCount == 1 &&
                reachabilityProof.profileCount == 1 &&
                reachabilityProof.validationReportCount == 1
            ) {
                1
            } else {
                0
            }
        val capabilityMatrixCount =
            if (
                capabilityMatrix.markerCount == 1 &&
                capabilityMatrix.inventoryCount == 1 &&
                capabilityMatrix.profileCount == 1 &&
                capabilityMatrix.validationReportCount == 1 &&
                capabilityMatrix.reachabilityProofCount == 1
            ) {
                1
            } else {
                0
            }
        val fixtureScopeCount =
            if (
                fixtureScope.markerCount == 1 &&
                fixtureScope.inventoryCount == 1 &&
                fixtureScope.profileCount == 1 &&
                fixtureScope.validationReportCount == 1 &&
                fixtureScope.reachabilityProofCount == 1 &&
                fixtureScope.capabilityMatrixCount == 1
            ) {
                1
            } else {
                0
            }
        val fixtureCatalogCount =
            if (
                fixtureCatalog.markerCount == 1 &&
                fixtureCatalog.inventoryCount == 1 &&
                fixtureCatalog.profileCount == 1 &&
                fixtureCatalog.validationReportCount == 1 &&
                fixtureCatalog.reachabilityProofCount == 1 &&
                fixtureCatalog.capabilityMatrixCount == 1 &&
                fixtureCatalog.fixtureScopeCount == 1 &&
                fixtureCatalog.catalogContainsExactlyOneFixture
            ) {
                1
            } else {
                0
            }
        val fixtureValidationCount =
            if (
                fixtureValidation.markerCount == 1 &&
                fixtureValidation.inventoryCount == 1 &&
                fixtureValidation.profileCount == 1 &&
                fixtureValidation.validationReportCount == 1 &&
                fixtureValidation.reachabilityProofCount == 1 &&
                fixtureValidation.capabilityMatrixCount == 1 &&
                fixtureValidation.fixtureScopeCount == 1 &&
                fixtureValidation.fixtureCatalogCount == 1 &&
                fixtureValidation.fixtureRowCount == 1 &&
                fixtureValidation.allValidationChecksPassed
            ) {
                1
            } else {
                0
            }
        val fixtureRowCount = fixtureCatalog.fixtureRows.size
        val expectedSafeIdMatched =
            marker.safeId.value == EXPECTED_SAFE_ID &&
                profile.marker.safeId.value == EXPECTED_SAFE_ID &&
                validationReport.expectedSafeIdMatched &&
                reachabilityProof.expectedSafeIdMatched &&
                capabilityMatrix.expectedSafeIdMatched &&
                fixtureScope.expectedSafeIdMatched &&
                fixtureCatalog.expectedSafeIdMatched &&
                fixtureValidation.expectedSafeIdMatched &&
                fixtureRow?.markerSafeId?.value == EXPECTED_SAFE_ID
        val expectedFixtureIdMatched =
            fixtureValidation.expectedFixtureIdMatched &&
                fixtureRow?.fixtureId?.value == EXPECTED_FIXTURE_ID
        val admissionIsCommonTestOnly =
            marker.sourceSet == SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet.CommonTest &&
                inventory.inventoryIsCommonTestOnly &&
                profile.profileIsCommonTestOnly &&
                validationReport.validationIsCommonTestOnly &&
                reachabilityProof.proofIsCommonTestOnly &&
                capabilityMatrix.matrixIsCommonTestOnly &&
                fixtureScope.fixtureScopeIsCommonTestOnly &&
                fixtureCatalog.catalogIsCommonTestOnly &&
                fixtureValidation.validationIsCommonTestOnly &&
                fixtureRow?.sourceSet == SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalogSourceSet.CommonTest
        val futureVectorCriteriaModeled =
            criteriaRows.size == SkaldVaultV1TestOnlyProviderIdentityKatFuturePublicVectorCriterion.entries.size &&
                criteriaRows.all { row -> row.requiredForFutureBranch && !row.authorizesCurrentVectors }
        val futureVectorCriteriaAuthorizeCurrentVectors =
            criteriaRows.any { row -> row.authorizesCurrentVectors }
        val currentVectorMaterialPresent =
            fixtureScope.rawKatMaterialPresent ||
                fixtureCatalog.rawKatMaterialPresent ||
                fixtureCatalog.rawVectorBytesPresent ||
                fixtureCatalog.rawVectorHexPresent ||
                fixtureValidation.rawKatMaterialPresent ||
                fixtureValidation.rawVectorBytesPresent ||
                fixtureValidation.rawVectorHexPresent ||
                fixtureRow?.rawKatMaterialPresent == true ||
                fixtureRow?.rawVectorBytesPresent == true ||
                fixtureRow?.rawVectorHexPresent == true ||
                forbiddenRows.any { row -> row.presentNow }

        return SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorAdmission(
            markerCount = markerCount,
            inventoryCount = inventoryCount,
            profileCount = profileCount,
            validationReportCount = validationReportCount,
            reachabilityProofCount = reachabilityProofCount,
            capabilityMatrixCount = capabilityMatrixCount,
            fixtureScopeCount = fixtureScopeCount,
            fixtureCatalogCount = fixtureCatalogCount,
            fixtureValidationCount = fixtureValidationCount,
            fixtureRowCount = fixtureRowCount,
            expectedSafeIdMatched = expectedSafeIdMatched,
            expectedFixtureIdMatched = expectedFixtureIdMatched,
            admissionIsCommonTestOnly = admissionIsCommonTestOnly,
            admissionIsProductionAuthorization = false,
            admissionIsProviderSelectionAuthorization = false,
            admissionIsKatExecutionAuthorization = false,
            admissionIsCryptoAuthorization = false,
            admissionIsVaultPersistenceAuthorization = false,
            admissionIsMainnetAuthorization = false,
            futureVectorCriteriaModeled = futureVectorCriteriaModeled,
            futureVectorCriteriaAuthorizeCurrentVectors = futureVectorCriteriaAuthorizeCurrentVectors,
            currentVectorMaterialPresent = currentVectorMaterialPresent,
            rawKatMaterialPresent = false,
            publicVectorBytesPresent = false,
            publicVectorHexPresent = false,
            executableKatPresent = false,
            katExecutorPresent = false,
            runtimeSelectable = false,
            registrySelectable = false,
            factoryReachable = false,
            dispatcherReachable = false,
            executorTargetable = false,
            providerKatExecutorReachable = false,
            providerOperationReachable = false,
            cryptoExecutionReachable = false,
            vaultLifecycleReachable = false,
            persistenceReachable = false,
            productionSyncReachable = false,
            backendClientReachable = false,
            bdkWalletStateReachable = false,
            settingsCodecReachable = false,
            uiSurfaceReachable = false,
            signingBroadcastingReachable = false,
            publicEndpointReachable = false,
            mainnetReachable = false,
            implementsVaultCryptoProvider = false,
            containsVaultCryptoProvider = false,
            canExecuteProviderOperations = false,
            canExecuteCrypto = false,
            canUseForVaultLifecycle = false,
            canUseForPersistence = false,
            canUseForSync = false,
            canUseForSigning = false,
            canUseForBroadcasting = false,
            canUseForMainnet = false,
            productionProviderSelectable = false,
            admissionOutcomeRows = outcomeRows,
            futurePublicVectorCriteriaRows = criteriaRows,
            forbiddenCurrentVectorStateRows = forbiddenRows,
            sourceSet = SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorAdmissionSourceSet.CommonTest,
            displayLabel = SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorAdmissionSafeLabel(
                "redacted admission evidence",
            ),
        )
    }

    private fun currentAdmissionOutcomeRows():
        List<SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorAdmissionOutcomeRow> =
        SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorAdmissionOutcome.entries.map { outcome ->
            SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorAdmissionOutcomeRow(
                outcome = outcome,
                modeled = true,
                authorizesCurrentVectors = false,
                label = SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorAdmissionSafeLabel("redacted"),
            )
        }

    private fun currentFuturePublicVectorCriteriaRows():
        List<SkaldVaultV1TestOnlyProviderIdentityKatFuturePublicVectorCriterionRow> =
        SkaldVaultV1TestOnlyProviderIdentityKatFuturePublicVectorCriterion.entries.map { criterion ->
            SkaldVaultV1TestOnlyProviderIdentityKatFuturePublicVectorCriterionRow(
                criterion = criterion,
                requiredForFutureBranch = true,
                satisfiedNow = false,
                authorizesCurrentVectors = false,
                label = SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorAdmissionSafeLabel("redacted"),
            )
        }

    private fun currentForbiddenCurrentVectorStateRows():
        List<SkaldVaultV1TestOnlyProviderIdentityKatForbiddenCurrentVectorStateRow> =
        SkaldVaultV1TestOnlyProviderIdentityKatForbiddenCurrentVectorState.entries.map { state ->
            SkaldVaultV1TestOnlyProviderIdentityKatForbiddenCurrentVectorStateRow(
                state = state,
                forbiddenNow = true,
                presentNow = false,
                label = SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorAdmissionSafeLabel("redacted"),
            )
        }
}
