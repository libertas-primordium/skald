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

enum class SkaldVaultV1TestOnlyProviderIdentityMarkerKind {
    InertTestOnlyProviderIdentityMarker,
}

data class SkaldVaultV1TestOnlyProviderIdentityMarker(
    val markerId: SkaldVaultV1TestOnlyProviderIdentityMarkerSafeId,
    val markerVersion: Int,
    val markerKind: SkaldVaultV1TestOnlyProviderIdentityMarkerKind,
    val safeId: SkaldVaultV1TestOnlyProviderIdentityMarkerSafeId,
    val namespace: SkaldVaultV1TestOnlyProviderIdentityMarkerNamespace,
    val family: SkaldVaultV1TestOnlyProviderIdentityMarkerFamily,
    val purpose: SkaldVaultV1TestOnlyProviderIdentityMarkerPurpose,
    val sourceSet: SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet,
    val syntheticIdentityLabel: SkaldVaultV1TestOnlyProviderIdentityMarkerSafeId,
    val displayLabel: SkaldVaultV1TestOnlyProviderIdentityMarkerSafeLabel,
    val deterministicSafeIdentity: Boolean,
    val markerCreated: Boolean,
    val inertMarkerOnly: Boolean,
    val commonTestOnly: Boolean,
    val testOnlyNamespaceConformant: Boolean,
    val transitionGateReviewed: Boolean,
    val transitionGateHumanReviewReady: Boolean,
    val userApprovedInertMarkerPass: Boolean,
    val implementationMarkerPresent: Boolean,
    val implementedNow: Boolean,
    val providerImplementationPresent: Boolean,
    val productionProviderIdentityPresent: Boolean,
    val providerRegistryEntryPresent: Boolean,
    val providerFactoryPresent: Boolean,
    val providerDispatcherPresent: Boolean,
    val executorTargetPresent: Boolean,
    val providerOperationExecuted: Boolean,
    val cryptoExecuted: Boolean,
    val katRunnerPresent: Boolean,
    val katExecutorPresent: Boolean,
    val tracePayloadPresent: Boolean,
    val rawKatMaterialPresent: Boolean,
    val publicVectorBytesPresent: Boolean,
    val publicVectorHexPresent: Boolean,
    val vaultPersistencePresent: Boolean,
    val productionSyncPresent: Boolean,
    val signingBroadcastingPresent: Boolean,
    val uiPresent: Boolean,
    val endpointPresent: Boolean,
    val mainnetPresent: Boolean,
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
    val disabledProviderOnly: Boolean,
    val implementationAuthorizationPresent: Boolean,
    val productionAuthorizationPresent: Boolean,
    val providerSelectionAuthorizationPresent: Boolean,
    val providerOperationAuthorizationPresent: Boolean,
    val cryptoAuthorizationPresent: Boolean,
    val katRunnerAuthorizationPresent: Boolean,
    val katExecutorAuthorizationPresent: Boolean,
    val providerKatExecutorAuthorizationPresent: Boolean,
    val vaultPersistenceAuthorizationPresent: Boolean,
    val syncAuthorizationPresent: Boolean,
    val signingBroadcastingAuthorizationPresent: Boolean,
    val uiAuthorizationPresent: Boolean,
    val endpointAuthorizationPresent: Boolean,
    val mainnetAuthorizationPresent: Boolean,
    val evidenceCount: Int,
    val blockerCount: Int,
    val warningCount: Int,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityMarker(REDACTED, COMMON_TEST_ONLY, INERT_MARKER_ONLY, NOT_AUTHORIZATION, DISABLED_PROVIDER_ONLY)"
}

object SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy {
    private val baseMarker: SkaldVaultV1TestOnlyProviderIdentityMarker by lazy {
        buildMarker(transitionGate = null)
    }

    private val implementationMarker: SkaldVaultV1TestOnlyProviderIdentityMarker by lazy {
        buildMarker(
            transitionGate =
                SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGatePolicy
                    .currentProviderIdentityImplementationTransitionGate(),
        )
    }

    fun currentMarker(): SkaldVaultV1TestOnlyProviderIdentityMarker =
        baseMarker

    fun currentImplementationMarker(): SkaldVaultV1TestOnlyProviderIdentityMarker =
        implementationMarker

    private fun buildMarker(
        transitionGate: SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGate?,
    ): SkaldVaultV1TestOnlyProviderIdentityMarker {
        val family = SkaldVaultV1TestOnlyProviderIdentityMarkerFamily.DeterministicKat
        val purpose = SkaldVaultV1TestOnlyProviderIdentityMarkerPurpose.InertMarker
        val safeId =
            SkaldVaultV1TestOnlyProviderIdentityMarkerSafeId(
                SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespacePolicy.ALLOWED_NAMESPACE_PREFIX +
                    "${family.token}-${purpose.token}",
            )
        val namespaceConformant =
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespacePolicy.evaluateNamespace(
                SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceRequest(
                    candidateSafeId = SkaldVaultV1TestOnlyProviderSyntheticIdentitySafeId(safeId.value),
                ),
            ).candidateLabelAcceptedForFutureReview
        return SkaldVaultV1TestOnlyProviderIdentityMarker(
            markerId = safeId,
            markerVersion = 1,
            markerKind = SkaldVaultV1TestOnlyProviderIdentityMarkerKind.InertTestOnlyProviderIdentityMarker,
            safeId = safeId,
            namespace = SkaldVaultV1TestOnlyProviderIdentityMarkerNamespace.TestOnlyProviderIdentityV1,
            family = family,
            purpose = purpose,
            sourceSet = SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet.CommonTest,
            syntheticIdentityLabel = safeId,
            displayLabel = SkaldVaultV1TestOnlyProviderIdentityMarkerSafeLabel("inert synthetic marker"),
            deterministicSafeIdentity = true,
            markerCreated = true,
            inertMarkerOnly = true,
            commonTestOnly = true,
            testOnlyNamespaceConformant = namespaceConformant,
            transitionGateReviewed = transitionGate != null,
            transitionGateHumanReviewReady = transitionGate?.reviewReadyForHumanDecision == true,
            userApprovedInertMarkerPass = true,
            implementationMarkerPresent = true,
            implementedNow = true,
            providerImplementationPresent = false,
            productionProviderIdentityPresent = false,
            providerRegistryEntryPresent = false,
            providerFactoryPresent = false,
            providerDispatcherPresent = false,
            executorTargetPresent = false,
            providerOperationExecuted = false,
            cryptoExecuted = false,
            katRunnerPresent = false,
            katExecutorPresent = false,
            tracePayloadPresent = false,
            rawKatMaterialPresent = false,
            publicVectorBytesPresent = false,
            publicVectorHexPresent = false,
            vaultPersistencePresent = false,
            productionSyncPresent = false,
            signingBroadcastingPresent = false,
            uiPresent = false,
            endpointPresent = false,
            mainnetPresent = false,
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
            disabledProviderOnly =
                transitionGate
                    ?.let { gate -> gate.disabledProviderOnly && !gate.productionProviderSelectable }
                    ?: true,
            implementationAuthorizationPresent = false,
            productionAuthorizationPresent = false,
            providerSelectionAuthorizationPresent = false,
            providerOperationAuthorizationPresent = false,
            cryptoAuthorizationPresent = false,
            katRunnerAuthorizationPresent = false,
            katExecutorAuthorizationPresent = false,
            providerKatExecutorAuthorizationPresent = false,
            vaultPersistenceAuthorizationPresent = false,
            syncAuthorizationPresent = false,
            signingBroadcastingAuthorizationPresent = false,
            uiAuthorizationPresent = false,
            endpointAuthorizationPresent = false,
            mainnetAuthorizationPresent = false,
            evidenceCount = 8,
            blockerCount = 0,
            warningCount = 0,
        )
    }
}
