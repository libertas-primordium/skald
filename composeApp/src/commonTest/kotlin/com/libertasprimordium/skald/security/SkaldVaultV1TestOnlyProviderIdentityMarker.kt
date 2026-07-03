package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityMarkerSafeId(val value: String) {
    override fun toString(): String = "RedactedTestOnlyProviderIdentityMarkerSafeId"
}

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityMarkerSafeLabel(val value: String) {
    override fun toString(): String = "RedactedTestOnlyProviderIdentityMarkerSafeLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityMarkerNamespace {
    TestOnlyProviderIdentityV1,
}

enum class SkaldVaultV1TestOnlyProviderIdentityMarkerFamily(val token: String) {
    DeterministicKat("deterministic-kat"),
}

enum class SkaldVaultV1TestOnlyProviderIdentityMarkerPurpose(val token: String) {
    InertMarker("inert-marker"),
}

enum class SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet {
    CommonTest,
}

data class SkaldVaultV1TestOnlyProviderIdentityMarker(
    val safeId: SkaldVaultV1TestOnlyProviderIdentityMarkerSafeId,
    val namespace: SkaldVaultV1TestOnlyProviderIdentityMarkerNamespace,
    val family: SkaldVaultV1TestOnlyProviderIdentityMarkerFamily,
    val purpose: SkaldVaultV1TestOnlyProviderIdentityMarkerPurpose,
    val sourceSet: SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet,
    val displayLabel: SkaldVaultV1TestOnlyProviderIdentityMarkerSafeLabel,
    val implementedNow: Boolean,
    val runtimeSelectable: Boolean,
    val registrySelectable: Boolean,
    val factoryReachable: Boolean,
    val dispatcherReachable: Boolean,
    val executorTargetable: Boolean,
    val providerKatExecutorReachable: Boolean,
    val implementsVaultCryptoProvider: Boolean,
    val vaultCryptoProviderInstanceExposed: Boolean,
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
        "SkaldVaultV1TestOnlyProviderIdentityMarker(redactedSafeId, commonTestOnly, inert, nonAuthorizing)"
}

object SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy {
    fun currentMarker(): SkaldVaultV1TestOnlyProviderIdentityMarker {
        val family = SkaldVaultV1TestOnlyProviderIdentityMarkerFamily.DeterministicKat
        val purpose = SkaldVaultV1TestOnlyProviderIdentityMarkerPurpose.InertMarker
        return SkaldVaultV1TestOnlyProviderIdentityMarker(
            safeId = SkaldVaultV1TestOnlyProviderIdentityMarkerSafeId(
                SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespacePolicy.ALLOWED_NAMESPACE_PREFIX +
                    "${family.token}-${purpose.token}",
            ),
            namespace = SkaldVaultV1TestOnlyProviderIdentityMarkerNamespace.TestOnlyProviderIdentityV1,
            family = family,
            purpose = purpose,
            sourceSet = SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet.CommonTest,
            displayLabel = SkaldVaultV1TestOnlyProviderIdentityMarkerSafeLabel("inert synthetic marker"),
            implementedNow = true,
            runtimeSelectable = false,
            registrySelectable = false,
            factoryReachable = false,
            dispatcherReachable = false,
            executorTargetable = false,
            providerKatExecutorReachable = false,
            implementsVaultCryptoProvider = false,
            vaultCryptoProviderInstanceExposed = false,
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
