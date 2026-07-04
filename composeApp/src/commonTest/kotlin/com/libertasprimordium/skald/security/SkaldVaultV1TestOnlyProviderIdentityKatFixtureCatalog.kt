package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalogSafeId(val value: String) {
    override fun toString(): String = "RedactedTestOnlyProviderIdentityKatFixtureCatalogSafeId"
}

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalogSafeLabel(val value: String) {
    override fun toString(): String = "RedactedTestOnlyProviderIdentityKatFixtureCatalogSafeLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalogSourceSet {
    CommonTest,
}

enum class SkaldVaultV1TestOnlyProviderIdentityKatFixturePurpose {
    InertIdentityMetadataOnly,
}

enum class SkaldVaultV1TestOnlyProviderIdentityKatFixtureCategory {
    IdentityMarkerFixtureMetadata,
}

enum class SkaldVaultV1TestOnlyProviderIdentityKatFixtureMaterialKind {
    NoRawKatMaterial,
}

data class SkaldVaultV1TestOnlyProviderIdentityKatFixtureMetadata(
    val fixtureId: SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalogSafeId,
    val markerSafeId: SkaldVaultV1TestOnlyProviderIdentityMarkerSafeId,
    val sourceSet: SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalogSourceSet,
    val fixturePurpose: SkaldVaultV1TestOnlyProviderIdentityKatFixturePurpose,
    val fixtureCategory: SkaldVaultV1TestOnlyProviderIdentityKatFixtureCategory,
    val fixtureMaterialKind: SkaldVaultV1TestOnlyProviderIdentityKatFixtureMaterialKind,
    val markerCount: Int,
    val inventoryCount: Int,
    val profileCount: Int,
    val validationReportCount: Int,
    val reachabilityProofCount: Int,
    val capabilityMatrixCount: Int,
    val fixtureScopeCount: Int,
    val expectedSafeIdMatched: Boolean,
    val catalogIsCommonTestOnly: Boolean,
    val catalogContainsExactlyOneFixture: Boolean,
    val catalogIsProductionAuthorization: Boolean,
    val catalogIsProviderSelectionAuthorization: Boolean,
    val catalogIsKatExecutionAuthorization: Boolean,
    val catalogIsCryptoAuthorization: Boolean,
    val catalogIsVaultPersistenceAuthorization: Boolean,
    val catalogIsMainnetAuthorization: Boolean,
    val fixtureMetadataOnly: Boolean,
    val rawKatMaterialPresent: Boolean,
    val rawVectorBytesPresent: Boolean,
    val rawVectorHexPresent: Boolean,
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
    val displayLabel: SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalogSafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityKatFixtureMetadata(redactedFixtureId, redactedMarkerId, commonTestOnly, metadataOnly, nonAuthorizing)"
}

data class SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalog(
    val fixtureRows: List<SkaldVaultV1TestOnlyProviderIdentityKatFixtureMetadata>,
    val markerCount: Int,
    val inventoryCount: Int,
    val profileCount: Int,
    val validationReportCount: Int,
    val reachabilityProofCount: Int,
    val capabilityMatrixCount: Int,
    val fixtureScopeCount: Int,
    val expectedSafeIdMatched: Boolean,
    val catalogIsCommonTestOnly: Boolean,
    val catalogContainsExactlyOneFixture: Boolean,
    val catalogIsProductionAuthorization: Boolean,
    val catalogIsProviderSelectionAuthorization: Boolean,
    val catalogIsKatExecutionAuthorization: Boolean,
    val catalogIsCryptoAuthorization: Boolean,
    val catalogIsVaultPersistenceAuthorization: Boolean,
    val catalogIsMainnetAuthorization: Boolean,
    val fixtureMetadataOnly: Boolean,
    val rawKatMaterialPresent: Boolean,
    val rawVectorBytesPresent: Boolean,
    val rawVectorHexPresent: Boolean,
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
    val sourceSet: SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalogSourceSet,
    val displayLabel: SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalogSafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalog(redactedFixtureIds, redactedMarkerIds, commonTestOnly, metadataOnly, nonAuthorizing)"
}

object SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalogPolicy {
    private const val EXPECTED_SAFE_ID: String =
        "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"
    private const val FIXTURE_ID: String =
        "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata"

    fun currentKatFixtureCatalog(): SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalog {
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
        val expectedSafeIdMatched =
            marker.safeId.value == EXPECTED_SAFE_ID &&
                profile.marker.safeId.value == EXPECTED_SAFE_ID &&
                validationReport.expectedSafeIdMatched &&
                reachabilityProof.expectedSafeIdMatched &&
                capabilityMatrix.expectedSafeIdMatched &&
                fixtureScope.expectedSafeIdMatched
        val catalogIsCommonTestOnly =
            marker.sourceSet == SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet.CommonTest &&
                inventory.inventoryIsCommonTestOnly &&
                profile.profileIsCommonTestOnly &&
                validationReport.validationIsCommonTestOnly &&
                reachabilityProof.proofIsCommonTestOnly &&
                capabilityMatrix.matrixIsCommonTestOnly &&
                fixtureScope.fixtureScopeIsCommonTestOnly
        val metadata = currentFixtureMetadata(
            markerSafeId = marker.safeId,
            markerCount = markerCount,
            inventoryCount = inventoryCount,
            profileCount = profileCount,
            validationReportCount = validationReportCount,
            reachabilityProofCount = reachabilityProofCount,
            capabilityMatrixCount = capabilityMatrixCount,
            fixtureScopeCount = fixtureScopeCount,
            expectedSafeIdMatched = expectedSafeIdMatched,
            catalogIsCommonTestOnly = catalogIsCommonTestOnly,
        )
        val rows = listOf(metadata)

        return SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalog(
            fixtureRows = rows,
            markerCount = markerCount,
            inventoryCount = inventoryCount,
            profileCount = profileCount,
            validationReportCount = validationReportCount,
            reachabilityProofCount = reachabilityProofCount,
            capabilityMatrixCount = capabilityMatrixCount,
            fixtureScopeCount = fixtureScopeCount,
            expectedSafeIdMatched = expectedSafeIdMatched,
            catalogIsCommonTestOnly = catalogIsCommonTestOnly,
            catalogContainsExactlyOneFixture = rows.size == 1,
            catalogIsProductionAuthorization = false,
            catalogIsProviderSelectionAuthorization = false,
            catalogIsKatExecutionAuthorization = false,
            catalogIsCryptoAuthorization = false,
            catalogIsVaultPersistenceAuthorization = false,
            catalogIsMainnetAuthorization = false,
            fixtureMetadataOnly = true,
            rawKatMaterialPresent = false,
            rawVectorBytesPresent = false,
            rawVectorHexPresent = false,
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
            sourceSet = SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalogSourceSet.CommonTest,
            displayLabel = SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalogSafeLabel(
                "inert KAT fixture metadata catalog",
            ),
        )
    }

    private fun currentFixtureMetadata(
        markerSafeId: SkaldVaultV1TestOnlyProviderIdentityMarkerSafeId,
        markerCount: Int,
        inventoryCount: Int,
        profileCount: Int,
        validationReportCount: Int,
        reachabilityProofCount: Int,
        capabilityMatrixCount: Int,
        fixtureScopeCount: Int,
        expectedSafeIdMatched: Boolean,
        catalogIsCommonTestOnly: Boolean,
    ): SkaldVaultV1TestOnlyProviderIdentityKatFixtureMetadata =
        SkaldVaultV1TestOnlyProviderIdentityKatFixtureMetadata(
            fixtureId = SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalogSafeId(FIXTURE_ID),
            markerSafeId = markerSafeId,
            sourceSet = SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalogSourceSet.CommonTest,
            fixturePurpose = SkaldVaultV1TestOnlyProviderIdentityKatFixturePurpose.InertIdentityMetadataOnly,
            fixtureCategory = SkaldVaultV1TestOnlyProviderIdentityKatFixtureCategory.IdentityMarkerFixtureMetadata,
            fixtureMaterialKind = SkaldVaultV1TestOnlyProviderIdentityKatFixtureMaterialKind.NoRawKatMaterial,
            markerCount = markerCount,
            inventoryCount = inventoryCount,
            profileCount = profileCount,
            validationReportCount = validationReportCount,
            reachabilityProofCount = reachabilityProofCount,
            capabilityMatrixCount = capabilityMatrixCount,
            fixtureScopeCount = fixtureScopeCount,
            expectedSafeIdMatched = expectedSafeIdMatched,
            catalogIsCommonTestOnly = catalogIsCommonTestOnly,
            catalogContainsExactlyOneFixture = true,
            catalogIsProductionAuthorization = false,
            catalogIsProviderSelectionAuthorization = false,
            catalogIsKatExecutionAuthorization = false,
            catalogIsCryptoAuthorization = false,
            catalogIsVaultPersistenceAuthorization = false,
            catalogIsMainnetAuthorization = false,
            fixtureMetadataOnly = true,
            rawKatMaterialPresent = false,
            rawVectorBytesPresent = false,
            rawVectorHexPresent = false,
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
            displayLabel = SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalogSafeLabel(
                "inert identity metadata fixture",
            ),
        )
}
