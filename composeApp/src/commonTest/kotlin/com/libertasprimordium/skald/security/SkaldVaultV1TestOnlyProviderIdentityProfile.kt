package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityProfileSafeLabel(val value: String) {
    override fun toString(): String = "RedactedTestOnlyProviderIdentityProfileSafeLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityProfileSourceSet {
    CommonTest,
}

enum class SkaldVaultV1TestOnlyProviderIdentityProfileKind {
    InertTestOnlyIdentityProfile,
}

enum class SkaldVaultV1TestOnlyProviderIdentityProfilePurpose {
    FutureValidationMetadataOnly,
}

data class SkaldVaultV1TestOnlyProviderIdentityProfile(
    val marker: SkaldVaultV1TestOnlyProviderIdentityMarker,
    val inventorySize: Int,
    val sourceSet: SkaldVaultV1TestOnlyProviderIdentityProfileSourceSet,
    val profileKind: SkaldVaultV1TestOnlyProviderIdentityProfileKind,
    val profilePurpose: SkaldVaultV1TestOnlyProviderIdentityProfilePurpose,
    val displayLabel: SkaldVaultV1TestOnlyProviderIdentityProfileSafeLabel,
    val implementedNow: Boolean,
    val profileIsCommonTestOnly: Boolean,
    val profileIsProductionProviderProfile: Boolean,
    val profileIsProviderRegistry: Boolean,
    val profileIsProviderFactoryInput: Boolean,
    val profileIsProviderDispatcherInput: Boolean,
    val profileIsExecutorTargetInput: Boolean,
    val runtimeSelectable: Boolean,
    val registrySelectable: Boolean,
    val factoryReachable: Boolean,
    val dispatcherReachable: Boolean,
    val executorTargetable: Boolean,
    val providerKatExecutorReachable: Boolean,
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
        "SkaldVaultV1TestOnlyProviderIdentityProfile(redactedMarkerId, commonTestOnly, inert, metadataOnly, nonAuthorizing)"
}

object SkaldVaultV1TestOnlyProviderIdentityProfilePolicy {
    fun currentProfile(): SkaldVaultV1TestOnlyProviderIdentityProfile {
        val inventory = SkaldVaultV1TestOnlyProviderIdentityInventory
        val marker = inventory.markers.single()
        return SkaldVaultV1TestOnlyProviderIdentityProfile(
            marker = marker,
            inventorySize = inventory.markerCount,
            sourceSet = SkaldVaultV1TestOnlyProviderIdentityProfileSourceSet.CommonTest,
            profileKind = SkaldVaultV1TestOnlyProviderIdentityProfileKind.InertTestOnlyIdentityProfile,
            profilePurpose = SkaldVaultV1TestOnlyProviderIdentityProfilePurpose.FutureValidationMetadataOnly,
            displayLabel = SkaldVaultV1TestOnlyProviderIdentityProfileSafeLabel("inert synthetic profile"),
            implementedNow = true,
            profileIsCommonTestOnly = true,
            profileIsProductionProviderProfile = false,
            profileIsProviderRegistry = false,
            profileIsProviderFactoryInput = false,
            profileIsProviderDispatcherInput = false,
            profileIsExecutorTargetInput = false,
            runtimeSelectable = false,
            registrySelectable = false,
            factoryReachable = false,
            dispatcherReachable = false,
            executorTargetable = false,
            providerKatExecutorReachable = false,
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
        )
    }
}
