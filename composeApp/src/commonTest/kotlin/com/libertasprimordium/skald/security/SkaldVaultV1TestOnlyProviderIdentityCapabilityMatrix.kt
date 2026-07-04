package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityCapabilityMatrixSafeLabel(val value: String) {
    override fun toString(): String = "RedactedTestOnlyProviderIdentityCapabilityMatrixSafeLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityCapabilityMatrixSourceSet {
    CommonTest,
}

enum class SkaldVaultV1TestOnlyProviderIdentityCapabilityCategory {
    ProviderSelection,
    ProviderRegistry,
    ProviderFactory,
    ProviderDispatcher,
    ExecutorTarget,
    ProviderKatExecutor,
    ProviderOperation,
    RuntimeRandomness,
    Kdf,
    Hkdf,
    Hmac,
    Aead,
    KeyGeneration,
    KeysetStorage,
    VaultLifecycle,
    VaultPersistence,
    SecureStorage,
    SecureMetadata,
    ProductionSync,
    BackendClient,
    BdkWalletState,
    SettingsCodec,
    UiSurface,
    Signing,
    Broadcasting,
    PublicEndpointDefault,
    Mainnet,
}

data class SkaldVaultV1TestOnlyProviderIdentityCapabilityRow(
    val category: SkaldVaultV1TestOnlyProviderIdentityCapabilityCategory,
    val capabilityPresent: Boolean,
    val reachable: Boolean,
    val authorizesRuntimeUse: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityCapabilityMatrixSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityCapabilityMatrix(
    val markerCount: Int,
    val inventoryCount: Int,
    val profileCount: Int,
    val validationReportCount: Int,
    val reachabilityProofCount: Int,
    val expectedSafeIdMatched: Boolean,
    val matrixIsCommonTestOnly: Boolean,
    val matrixIsProductionAuthorization: Boolean,
    val matrixIsProviderSelectionAuthorization: Boolean,
    val matrixIsRegistryAuthorization: Boolean,
    val matrixIsFactoryAuthorization: Boolean,
    val matrixIsDispatcherAuthorization: Boolean,
    val matrixIsExecutorAuthorization: Boolean,
    val matrixIsCryptoAuthorization: Boolean,
    val matrixIsVaultPersistenceAuthorization: Boolean,
    val matrixIsMainnetAuthorization: Boolean,
    val everyCapabilityBlocked: Boolean,
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
    val capabilityRows: List<SkaldVaultV1TestOnlyProviderIdentityCapabilityRow>,
    val sourceSet: SkaldVaultV1TestOnlyProviderIdentityCapabilityMatrixSourceSet,
    val displayLabel: SkaldVaultV1TestOnlyProviderIdentityCapabilityMatrixSafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityCapabilityMatrix(redactedMarkerId, commonTestOnly, inert, allCapabilitiesBlocked, nonAuthorizing)"
}

object SkaldVaultV1TestOnlyProviderIdentityCapabilityMatrixPolicy {
    private const val EXPECTED_SAFE_ID: String =
        "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"

    fun currentCapabilityMatrix(): SkaldVaultV1TestOnlyProviderIdentityCapabilityMatrix {
        val inventory = SkaldVaultV1TestOnlyProviderIdentityInventory
        val marker = inventory.markers.single()
        val profile = SkaldVaultV1TestOnlyProviderIdentityProfilePolicy.currentProfile()
        val validationReport =
            SkaldVaultV1TestOnlyProviderIdentityProfileValidationPolicy.currentValidationReport()
        val reachabilityProof =
            SkaldVaultV1TestOnlyProviderIdentityReachabilityProofPolicy.currentReachabilityProof()
        val capabilityRows = currentCapabilityRows()
        val everyCapabilityBlocked =
            capabilityRows.all { row -> !row.capabilityPresent && !row.reachable && !row.authorizesRuntimeUse } &&
                !reachabilityProof.providerSelectionReachable &&
                !reachabilityProof.providerRegistryReachable &&
                !reachabilityProof.providerFactoryReachable &&
                !reachabilityProof.providerDispatcherReachable &&
                !reachabilityProof.executorTargetReachable &&
                !reachabilityProof.providerKatExecutorReachable &&
                !reachabilityProof.providerOperationReachable &&
                !reachabilityProof.cryptoExecutionReachable &&
                !reachabilityProof.vaultLifecycleReachable &&
                !reachabilityProof.persistenceReachable &&
                !reachabilityProof.productionSyncReachable &&
                !reachabilityProof.backendClientReachable &&
                !reachabilityProof.bdkWalletStateReachable &&
                !reachabilityProof.settingsCodecReachable &&
                !reachabilityProof.uiSurfaceReachable &&
                !reachabilityProof.signingBroadcastingReachable &&
                !reachabilityProof.publicEndpointReachable &&
                !reachabilityProof.mainnetReachable

        return SkaldVaultV1TestOnlyProviderIdentityCapabilityMatrix(
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
            expectedSafeIdMatched =
                marker.safeId.value == EXPECTED_SAFE_ID &&
                    profile.marker.safeId.value == EXPECTED_SAFE_ID &&
                    validationReport.expectedSafeIdMatched &&
                    reachabilityProof.expectedSafeIdMatched,
            matrixIsCommonTestOnly =
                marker.sourceSet == SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet.CommonTest &&
                    inventory.inventoryIsCommonTestOnly &&
                    profile.profileIsCommonTestOnly &&
                    validationReport.validationIsCommonTestOnly &&
                    reachabilityProof.proofIsCommonTestOnly,
            matrixIsProductionAuthorization = false,
            matrixIsProviderSelectionAuthorization = false,
            matrixIsRegistryAuthorization = false,
            matrixIsFactoryAuthorization = false,
            matrixIsDispatcherAuthorization = false,
            matrixIsExecutorAuthorization = false,
            matrixIsCryptoAuthorization = false,
            matrixIsVaultPersistenceAuthorization = false,
            matrixIsMainnetAuthorization = false,
            everyCapabilityBlocked = everyCapabilityBlocked,
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
            capabilityRows = capabilityRows,
            sourceSet = SkaldVaultV1TestOnlyProviderIdentityCapabilityMatrixSourceSet.CommonTest,
            displayLabel = SkaldVaultV1TestOnlyProviderIdentityCapabilityMatrixSafeLabel(
                "inert capability matrix",
            ),
        )
    }

    private fun currentCapabilityRows(): List<SkaldVaultV1TestOnlyProviderIdentityCapabilityRow> =
        SkaldVaultV1TestOnlyProviderIdentityCapabilityCategory.entries.map { category ->
            SkaldVaultV1TestOnlyProviderIdentityCapabilityRow(
                category = category,
                capabilityPresent = false,
                reachable = false,
                authorizesRuntimeUse = false,
                label = SkaldVaultV1TestOnlyProviderIdentityCapabilityMatrixSafeLabel("redacted"),
            )
        }
}
