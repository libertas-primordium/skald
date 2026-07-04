package com.libertasprimordium.skald.security

object SkaldVaultV1TestOnlyProviderIdentityInventory {
    val markers: List<SkaldVaultV1TestOnlyProviderIdentityMarker> =
        listOf(SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy.currentMarker())

    val markerCount: Int
        get() = markers.size

    val implementedNow: Boolean = true
    val inventoryContainsOnlyInertMarkers: Boolean = true
    val inventoryIsCommonTestOnly: Boolean = true
    val inventoryIsProductionRegistry: Boolean = false
    val runtimeSelectable: Boolean = false
    val registrySelectable: Boolean = false
    val factoryReachable: Boolean = false
    val dispatcherReachable: Boolean = false
    val executorTargetable: Boolean = false
    val providerKatExecutorReachable: Boolean = false
    val implementsVaultCryptoProvider: Boolean = false
    val containsVaultCryptoProvider: Boolean = false
    val canExecuteProviderOperations: Boolean = false
    val canExecuteCrypto: Boolean = false
    val canUseForVaultLifecycle: Boolean = false
    val canUseForPersistence: Boolean = false
    val canUseForSync: Boolean = false
    val canUseForSigning: Boolean = false
    val canUseForBroadcasting: Boolean = false
    val canUseForMainnet: Boolean = false
    val productionProviderSelectable: Boolean = false

    fun markerForSafeId(
        safeId: SkaldVaultV1TestOnlyProviderIdentityMarkerSafeId,
    ): SkaldVaultV1TestOnlyProviderIdentityMarker? =
        markers.singleOrNull { marker -> marker.safeId.value == safeId.value }

    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityInventory(redactedMarkerIds, commonTestOnly, inert, nonAuthorizing)"
}
