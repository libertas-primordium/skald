package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityKatFixtureScopeSafeLabel(val value: String) {
    override fun toString(): String = "RedactedTestOnlyProviderIdentityKatFixtureScopeSafeLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityKatFixtureScopeSourceSet {
    CommonTest,
}

enum class SkaldVaultV1TestOnlyProviderIdentityKatFixtureScopeCategory {
    IdentityMarkerFixtureMetadata,
    IdentityInventoryFixtureMetadata,
    IdentityProfileFixtureMetadata,
    IdentityValidationFixtureMetadata,
    IdentityReachabilityFixtureMetadata,
    IdentityCapabilityFixtureMetadata,
    FutureKatFixtureNameOnly,
    FutureKatFixturePurposeOnly,
    FutureKatFixtureSourceSetOnly,
    FutureKatFixtureRedactionOnly,
    FutureKatFixtureSourceGuardOnly,
}

enum class SkaldVaultV1TestOnlyProviderIdentityKatFixtureForbiddenMaterialClass {
    RawKatVectorBytes,
    RawKatVectorHex,
    PlaintextBytes,
    CiphertextBytes,
    NonceBytes,
    SaltBytes,
    AeadTagBytes,
    KeyBytes,
    KeysetBytes,
    PassphraseMaterial,
    SeedMaterial,
    MnemonicMaterial,
    PrivateKeyMaterial,
    XprvMaterial,
    XpubMaterial,
    NsecMaterial,
    PsbtMaterial,
    TransactionMaterial,
    WalletDescriptorMaterial,
    BackendEndpointMaterial,
    ProviderHandleMaterial,
    CryptoObjectMaterial,
}

data class SkaldVaultV1TestOnlyProviderIdentityKatFixtureScopeCategoryRow(
    val category: SkaldVaultV1TestOnlyProviderIdentityKatFixtureScopeCategory,
    val metadataOnly: Boolean,
    val futureOnly: Boolean,
    val authorizesExecution: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityKatFixtureScopeSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityKatFixtureForbiddenMaterialRow(
    val materialClass: SkaldVaultV1TestOnlyProviderIdentityKatFixtureForbiddenMaterialClass,
    val forbidden: Boolean,
    val present: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityKatFixtureScopeSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityKatFixtureScope(
    val markerCount: Int,
    val inventoryCount: Int,
    val profileCount: Int,
    val validationReportCount: Int,
    val reachabilityProofCount: Int,
    val capabilityMatrixCount: Int,
    val expectedSafeIdMatched: Boolean,
    val fixtureScopeIsCommonTestOnly: Boolean,
    val fixtureScopeIsProductionAuthorization: Boolean,
    val fixtureScopeIsProviderSelectionAuthorization: Boolean,
    val fixtureScopeIsKatExecutionAuthorization: Boolean,
    val fixtureScopeIsCryptoAuthorization: Boolean,
    val fixtureScopeIsVaultPersistenceAuthorization: Boolean,
    val fixtureScopeIsMainnetAuthorization: Boolean,
    val futureFixtureMetadataOnly: Boolean,
    val rawKatMaterialPresent: Boolean,
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
    val fixtureScopeCategoryRows: List<SkaldVaultV1TestOnlyProviderIdentityKatFixtureScopeCategoryRow>,
    val forbiddenMaterialRows: List<SkaldVaultV1TestOnlyProviderIdentityKatFixtureForbiddenMaterialRow>,
    val sourceSet: SkaldVaultV1TestOnlyProviderIdentityKatFixtureScopeSourceSet,
    val displayLabel: SkaldVaultV1TestOnlyProviderIdentityKatFixtureScopeSafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityKatFixtureScope(redactedMarkerId, commonTestOnly, inert, metadataOnly, nonAuthorizing)"
}

object SkaldVaultV1TestOnlyProviderIdentityKatFixtureScopePolicy {
    private const val EXPECTED_SAFE_ID: String =
        "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"

    fun currentKatFixtureScope(): SkaldVaultV1TestOnlyProviderIdentityKatFixtureScope {
        val inventory = SkaldVaultV1TestOnlyProviderIdentityInventory
        val marker = inventory.markers.single()
        val profile = SkaldVaultV1TestOnlyProviderIdentityProfilePolicy.currentProfile()
        val validationReport =
            SkaldVaultV1TestOnlyProviderIdentityProfileValidationPolicy.currentValidationReport()
        val reachabilityProof =
            SkaldVaultV1TestOnlyProviderIdentityReachabilityProofPolicy.currentReachabilityProof()
        val capabilityMatrix =
            SkaldVaultV1TestOnlyProviderIdentityCapabilityMatrixPolicy.currentCapabilityMatrix()
        val categoryRows = currentFixtureScopeCategoryRows()
        val forbiddenRows = currentForbiddenMaterialRows()

        return SkaldVaultV1TestOnlyProviderIdentityKatFixtureScope(
            markerCount = inventory.markers.count { item -> item.safeId.value == marker.safeId.value },
            inventoryCount = if (inventory.markerCount == 1) 1 else 0,
            profileCount = if (profile.marker.safeId.value == marker.safeId.value && profile.inventorySize == 1) 1 else 0,
            validationReportCount =
                if (
                    validationReport.markerCount == 1 &&
                    validationReport.inventoryCount == 1 &&
                    validationReport.profileCount == 1
                ) {
                    1
                } else {
                    0
                },
            reachabilityProofCount =
                if (
                    reachabilityProof.markerCount == 1 &&
                    reachabilityProof.inventoryCount == 1 &&
                    reachabilityProof.profileCount == 1 &&
                    reachabilityProof.validationReportCount == 1
                ) {
                    1
                } else {
                    0
                },
            capabilityMatrixCount =
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
                },
            expectedSafeIdMatched =
                marker.safeId.value == EXPECTED_SAFE_ID &&
                    profile.marker.safeId.value == EXPECTED_SAFE_ID &&
                    validationReport.expectedSafeIdMatched &&
                    reachabilityProof.expectedSafeIdMatched &&
                    capabilityMatrix.expectedSafeIdMatched,
            fixtureScopeIsCommonTestOnly =
                marker.sourceSet == SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet.CommonTest &&
                    inventory.inventoryIsCommonTestOnly &&
                    profile.profileIsCommonTestOnly &&
                    validationReport.validationIsCommonTestOnly &&
                    reachabilityProof.proofIsCommonTestOnly &&
                    capabilityMatrix.matrixIsCommonTestOnly,
            fixtureScopeIsProductionAuthorization = false,
            fixtureScopeIsProviderSelectionAuthorization = false,
            fixtureScopeIsKatExecutionAuthorization = false,
            fixtureScopeIsCryptoAuthorization = false,
            fixtureScopeIsVaultPersistenceAuthorization = false,
            fixtureScopeIsMainnetAuthorization = false,
            futureFixtureMetadataOnly = true,
            rawKatMaterialPresent = false,
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
            fixtureScopeCategoryRows = categoryRows,
            forbiddenMaterialRows = forbiddenRows,
            sourceSet = SkaldVaultV1TestOnlyProviderIdentityKatFixtureScopeSourceSet.CommonTest,
            displayLabel = SkaldVaultV1TestOnlyProviderIdentityKatFixtureScopeSafeLabel(
                "inert KAT fixture metadata scope",
            ),
        )
    }

    private fun currentFixtureScopeCategoryRows():
        List<SkaldVaultV1TestOnlyProviderIdentityKatFixtureScopeCategoryRow> =
        SkaldVaultV1TestOnlyProviderIdentityKatFixtureScopeCategory.entries.map { category ->
            SkaldVaultV1TestOnlyProviderIdentityKatFixtureScopeCategoryRow(
                category = category,
                metadataOnly = true,
                futureOnly = category.name.startsWith("FutureKatFixture"),
                authorizesExecution = false,
                label = SkaldVaultV1TestOnlyProviderIdentityKatFixtureScopeSafeLabel("redacted"),
            )
        }

    private fun currentForbiddenMaterialRows():
        List<SkaldVaultV1TestOnlyProviderIdentityKatFixtureForbiddenMaterialRow> =
        SkaldVaultV1TestOnlyProviderIdentityKatFixtureForbiddenMaterialClass.entries.map { materialClass ->
            SkaldVaultV1TestOnlyProviderIdentityKatFixtureForbiddenMaterialRow(
                materialClass = materialClass,
                forbidden = true,
                present = false,
                label = SkaldVaultV1TestOnlyProviderIdentityKatFixtureScopeSafeLabel("redacted"),
            )
        }
}
