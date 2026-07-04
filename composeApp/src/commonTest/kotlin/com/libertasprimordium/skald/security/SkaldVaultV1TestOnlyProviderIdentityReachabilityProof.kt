package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityReachabilityProofSafeLabel(val value: String) {
    override fun toString(): String = "RedactedTestOnlyProviderIdentityReachabilityProofSafeLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityReachabilityProofSourceSet {
    CommonTest,
}

enum class SkaldVaultV1TestOnlyProviderIdentityReachabilitySurface {
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
    VaultCreation,
    VaultUnlock,
    VaultSession,
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

data class SkaldVaultV1TestOnlyProviderIdentityReachabilitySurfaceRow(
    val surface: SkaldVaultV1TestOnlyProviderIdentityReachabilitySurface,
    val reachable: Boolean,
    val authorizesRuntimeUse: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityReachabilityProofSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityReachabilityProof(
    val markerCount: Int,
    val inventoryCount: Int,
    val profileCount: Int,
    val validationReportCount: Int,
    val expectedSafeIdMatched: Boolean,
    val proofIsCommonTestOnly: Boolean,
    val proofIsProductionAuthorization: Boolean,
    val proofIsProviderSelectionAuthorization: Boolean,
    val proofIsRegistryAuthorization: Boolean,
    val proofIsFactoryAuthorization: Boolean,
    val proofIsDispatcherAuthorization: Boolean,
    val proofIsExecutorAuthorization: Boolean,
    val proofIsCryptoAuthorization: Boolean,
    val proofIsVaultPersistenceAuthorization: Boolean,
    val proofIsMainnetAuthorization: Boolean,
    val providerSelectionReachable: Boolean,
    val providerRegistryReachable: Boolean,
    val providerFactoryReachable: Boolean,
    val providerDispatcherReachable: Boolean,
    val executorTargetReachable: Boolean,
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
    val runtimeSelectable: Boolean,
    val registrySelectable: Boolean,
    val factoryReachable: Boolean,
    val dispatcherReachable: Boolean,
    val executorTargetable: Boolean,
    val providerKatExecutorTargetable: Boolean,
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
    val reachabilitySurfaceRows: List<SkaldVaultV1TestOnlyProviderIdentityReachabilitySurfaceRow>,
    val sourceSet: SkaldVaultV1TestOnlyProviderIdentityReachabilityProofSourceSet,
    val displayLabel: SkaldVaultV1TestOnlyProviderIdentityReachabilityProofSafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityReachabilityProof(redactedMarkerId, commonTestOnly, inert, nonAuthorizing)"
}

object SkaldVaultV1TestOnlyProviderIdentityReachabilityProofPolicy {
    private const val EXPECTED_SAFE_ID: String =
        "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"

    fun currentReachabilityProof(): SkaldVaultV1TestOnlyProviderIdentityReachabilityProof {
        val inventory = SkaldVaultV1TestOnlyProviderIdentityInventory
        val marker = inventory.markers.single()
        val profile = SkaldVaultV1TestOnlyProviderIdentityProfilePolicy.currentProfile()
        val validationReport =
            SkaldVaultV1TestOnlyProviderIdentityProfileValidationPolicy.currentValidationReport()

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

        val providerSelectionReachable =
            marker.runtimeSelectable ||
                inventory.runtimeSelectable ||
                profile.runtimeSelectable ||
                validationReport.runtimeSelectable ||
                validationReport.validationIsProviderSelectionAuthorization
        val providerRegistryReachable =
            marker.registrySelectable ||
                inventory.registrySelectable ||
                profile.registrySelectable ||
                validationReport.registrySelectable ||
                validationReport.validationIsRegistryAuthorization
        val providerFactoryReachable =
            marker.factoryReachable ||
                inventory.factoryReachable ||
                profile.factoryReachable ||
                profile.profileIsProviderFactoryInput ||
                validationReport.factoryReachable ||
                validationReport.validationIsFactoryAuthorization
        val providerDispatcherReachable =
            marker.dispatcherReachable ||
                inventory.dispatcherReachable ||
                profile.dispatcherReachable ||
                profile.profileIsProviderDispatcherInput ||
                validationReport.dispatcherReachable ||
                validationReport.validationIsDispatcherAuthorization
        val executorTargetReachable =
            marker.executorTargetable ||
                inventory.executorTargetable ||
                profile.executorTargetable ||
                profile.profileIsExecutorTargetInput ||
                validationReport.executorTargetable ||
                validationReport.validationIsExecutorAuthorization
        val providerKatExecutorReachable =
            marker.providerKatExecutorReachable ||
                inventory.providerKatExecutorReachable ||
                profile.providerKatExecutorReachable ||
                validationReport.providerKatExecutorReachable
        val providerOperationReachable =
            marker.canExecuteProviderOperations ||
                inventory.canExecuteProviderOperations ||
                profile.canExecuteProviderOperations ||
                validationReport.canExecuteProviderOperations
        val cryptoExecutionReachable =
            marker.canExecuteCrypto ||
                inventory.canExecuteCrypto ||
                profile.canExecuteCrypto ||
                validationReport.canExecuteCrypto ||
                validationReport.validationIsCryptoAuthorization
        val vaultLifecycleReachable =
            marker.canUseForVaultLifecycle ||
                inventory.canUseForVaultLifecycle ||
                profile.canUseForVaultLifecycle ||
                validationReport.canUseForVaultLifecycle
        val persistenceReachable =
            marker.canUseForPersistence ||
                inventory.canUseForPersistence ||
                profile.canUseForPersistence ||
                validationReport.canUseForPersistence ||
                validationReport.validationIsVaultPersistenceAuthorization
        val productionSyncReachable =
            marker.canUseForSync ||
                inventory.canUseForSync ||
                profile.canUseForSync ||
                validationReport.canUseForSync
        val signingBroadcastingReachable =
            marker.canUseForSigning ||
                marker.canUseForBroadcasting ||
                inventory.canUseForSigning ||
                inventory.canUseForBroadcasting ||
                profile.canUseForSigning ||
                profile.canUseForBroadcasting ||
                validationReport.canUseForSigning ||
                validationReport.canUseForBroadcasting
        val mainnetReachable =
            marker.canUseForMainnet ||
                inventory.canUseForMainnet ||
                profile.canUseForMainnet ||
                validationReport.canUseForMainnet ||
                validationReport.validationIsMainnetAuthorization

        return SkaldVaultV1TestOnlyProviderIdentityReachabilityProof(
            markerCount = markerCount,
            inventoryCount = inventoryCount,
            profileCount = profileCount,
            validationReportCount = validationReportCount,
            expectedSafeIdMatched =
                marker.safeId.value == EXPECTED_SAFE_ID &&
                    profile.marker.safeId.value == EXPECTED_SAFE_ID &&
                    validationReport.expectedSafeIdMatched,
            proofIsCommonTestOnly =
                marker.sourceSet == SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet.CommonTest &&
                    inventory.inventoryIsCommonTestOnly &&
                    profile.profileIsCommonTestOnly &&
                    validationReport.validationIsCommonTestOnly,
            proofIsProductionAuthorization = false,
            proofIsProviderSelectionAuthorization = false,
            proofIsRegistryAuthorization = false,
            proofIsFactoryAuthorization = false,
            proofIsDispatcherAuthorization = false,
            proofIsExecutorAuthorization = false,
            proofIsCryptoAuthorization = false,
            proofIsVaultPersistenceAuthorization = false,
            proofIsMainnetAuthorization = false,
            providerSelectionReachable = providerSelectionReachable,
            providerRegistryReachable = providerRegistryReachable,
            providerFactoryReachable = providerFactoryReachable,
            providerDispatcherReachable = providerDispatcherReachable,
            executorTargetReachable = executorTargetReachable,
            providerKatExecutorReachable = providerKatExecutorReachable,
            providerOperationReachable = providerOperationReachable,
            cryptoExecutionReachable = cryptoExecutionReachable,
            vaultLifecycleReachable = vaultLifecycleReachable,
            persistenceReachable = persistenceReachable,
            productionSyncReachable = productionSyncReachable,
            backendClientReachable = false,
            bdkWalletStateReachable = false,
            settingsCodecReachable = false,
            uiSurfaceReachable = false,
            signingBroadcastingReachable = signingBroadcastingReachable,
            publicEndpointReachable = false,
            mainnetReachable = mainnetReachable,
            runtimeSelectable = false,
            registrySelectable = false,
            factoryReachable = false,
            dispatcherReachable = false,
            executorTargetable = false,
            providerKatExecutorTargetable = false,
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
            reachabilitySurfaceRows = currentReachabilitySurfaceRows(),
            sourceSet = SkaldVaultV1TestOnlyProviderIdentityReachabilityProofSourceSet.CommonTest,
            displayLabel = SkaldVaultV1TestOnlyProviderIdentityReachabilityProofSafeLabel(
                "inert reachability proof",
            ),
        )
    }

    private fun currentReachabilitySurfaceRows():
        List<SkaldVaultV1TestOnlyProviderIdentityReachabilitySurfaceRow> =
        SkaldVaultV1TestOnlyProviderIdentityReachabilitySurface.entries.map { surface ->
            SkaldVaultV1TestOnlyProviderIdentityReachabilitySurfaceRow(
                surface = surface,
                reachable = false,
                authorizesRuntimeUse = false,
                label = SkaldVaultV1TestOnlyProviderIdentityReachabilityProofSafeLabel("redacted"),
            )
        }
}
