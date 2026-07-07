package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityMarkerValidationSafeLabel(val value: String) {
    override fun toString(): String = "RedactedTestOnlyProviderIdentityMarkerValidationSafeLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityMarkerValidationKind {
    InertTestOnlyProviderIdentityMarkerValidation,
}

enum class SkaldVaultV1TestOnlyProviderIdentityMarkerValidationSourceSet {
    CommonTest,
}

enum class SkaldVaultV1TestOnlyProviderIdentityMarkerValidationCheck {
    MarkerPresent,
    MarkerCreated,
    ImplementationMarkerPresent,
    MarkerCommonTestOnly,
    MarkerInert,
    MarkerSafeLabelOnly,
    MarkerDeterministic,
    MarkerIdentityLabelMatchesExpected,
    MarkerIdentityLabelSafe,
    TransitionGatePresent,
    TransitionGateHumanReviewReady,
    UserApprovalScopedToInertMarker,
    ProviderImplementationAbsent,
    ProductionProviderIdentityAbsent,
    ProviderRegistryEntryAbsent,
    ProviderFactoryAbsent,
    ProviderDispatcherAbsent,
    ExecutorTargetAbsent,
    ProviderOperationExecutionAbsent,
    CryptoExecutionAbsent,
    KatRunnerAbsent,
    KatExecutorAbsent,
    TracePayloadAbsent,
    RawKatMaterialAbsent,
    PublicVectorBytesAbsent,
    PublicVectorHexAbsent,
    VaultLifecycleAbsent,
    VaultPersistenceAbsent,
    SecureStorageSuccessAbsent,
    ProductionSyncAbsent,
    SigningBroadcastingAbsent,
    UiAbsent,
    EndpointAbsent,
    MainnetAbsent,
    AuthorizationAbsent,
    DisabledProviderOnly,
    ProductionProviderNotSelectable,
    BuildHistoryExcludedFromNormalSourceMaterialCorpus,
}

enum class SkaldVaultV1TestOnlyProviderIdentityMarkerValidationFailureLabel {
    MarkerEvidenceMissing,
    MarkerNotCommonTestOnly,
    MarkerNotInert,
    MarkerUnsafeIdentityLabel,
    TransitionGateEvidenceMissing,
    ForbiddenRuntimeSurfacePresent,
    ForbiddenAuthorizationPresent,
    ProductionProviderSelectable,
}

enum class SkaldVaultV1TestOnlyProviderIdentityMarkerValidationBlocker {
    None,
}

enum class SkaldVaultV1TestOnlyProviderIdentityMarkerValidationWarning {
    None,
}

data class SkaldVaultV1TestOnlyProviderIdentityMarkerValidation(
    val validationId: SkaldVaultV1TestOnlyProviderIdentityMarkerValidationSafeLabel,
    val validationVersion: Int,
    val validationKind: SkaldVaultV1TestOnlyProviderIdentityMarkerValidationKind,
    val sourceSet: SkaldVaultV1TestOnlyProviderIdentityMarkerValidationSourceSet,
    val markerPresent: Boolean,
    val markerCreated: Boolean,
    val implementationMarkerPresent: Boolean,
    val markerCommonTestOnly: Boolean,
    val markerInert: Boolean,
    val markerSafeLabelOnly: Boolean,
    val markerDeterministic: Boolean,
    val markerValidationPassed: Boolean,
    val markerValidationIsCommonTestOnlyEvidence: Boolean,
    val markerCreatedIsInertMarkerEvidenceOnly: Boolean,
    val implementationMarkerPresentIsInertMarkerEvidenceOnly: Boolean,
    val transitionGatePresent: Boolean,
    val transitionGateHumanReviewReady: Boolean,
    val userApprovalScopedToInertMarker: Boolean,
    val markerIdentityLabelMatchesExpected: Boolean,
    val markerIdentityLabelSafe: Boolean,
    val providerImplementationPresent: Boolean,
    val productionProviderIdentityPresent: Boolean,
    val providerRegistryEntryPresent: Boolean,
    val providerFactoryPresent: Boolean,
    val providerDispatcherPresent: Boolean,
    val executorTargetPresent: Boolean,
    val providerHandlePresent: Boolean,
    val containsVaultCryptoProvider: Boolean,
    val implementsVaultCryptoProvider: Boolean,
    val providerOperationExecuted: Boolean,
    val cryptoExecuted: Boolean,
    val katRunnerPresent: Boolean,
    val katExecutorPresent: Boolean,
    val providerKatExecutorPresent: Boolean,
    val tracePayloadPresent: Boolean,
    val rawKatMaterialPresent: Boolean,
    val publicVectorBytesPresent: Boolean,
    val publicVectorHexPresent: Boolean,
    val vaultLifecyclePresent: Boolean,
    val vaultPersistencePresent: Boolean,
    val secureSecretStorageSuccessPresent: Boolean,
    val secureMetadataStorageSuccessPresent: Boolean,
    val productionSyncPresent: Boolean,
    val signingBroadcastingPresent: Boolean,
    val uiPresent: Boolean,
    val endpointPresent: Boolean,
    val mainnetPresent: Boolean,
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
    val buildHistoryExcludedFromNormalSourceMaterialCorpus: Boolean,
    val docsReadmeUseDedicatedCorpus: Boolean,
    val evidenceCount: Int,
    val validationCheckCount: Int,
    val blockerCount: Int,
    val warningCount: Int,
    val validationChecks: List<SkaldVaultV1TestOnlyProviderIdentityMarkerValidationCheck>,
    val failureLabels: List<SkaldVaultV1TestOnlyProviderIdentityMarkerValidationFailureLabel>,
    val blockers: List<SkaldVaultV1TestOnlyProviderIdentityMarkerValidationBlocker>,
    val warnings: List<SkaldVaultV1TestOnlyProviderIdentityMarkerValidationWarning>,
    val displayLabel: SkaldVaultV1TestOnlyProviderIdentityMarkerValidationSafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityMarkerValidation(" +
            "REDACTED, COMMON_TEST_ONLY, MARKER_VALIDATION_ONLY, NOT_AUTHORIZATION, DISABLED_PROVIDER_ONLY" +
            ")"
}

object SkaldVaultV1TestOnlyProviderIdentityMarkerValidationPolicy {
    private const val expectedSafeIdentityLabel =
        "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"

    fun currentProviderIdentityMarkerValidation(): SkaldVaultV1TestOnlyProviderIdentityMarkerValidation {
        val marker = SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy.currentImplementationMarker()
        val transitionGate =
            SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGatePolicy
                .currentProviderIdentityImplementationTransitionGate()
        val checks = SkaldVaultV1TestOnlyProviderIdentityMarkerValidationCheck.entries.toList()

        val markerPresent =
            marker.markerCreated &&
                marker.implementationMarkerPresent &&
                marker.markerId.value == marker.safeId.value
        val markerCommonTestOnly =
            marker.commonTestOnly &&
                marker.sourceSet == SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet.CommonTest
        val markerInert =
            marker.inertMarkerOnly &&
                !marker.providerOperationExecuted &&
                !marker.cryptoExecuted &&
                !marker.katRunnerPresent &&
                !marker.katExecutorPresent
        val markerIdentityLabelMatchesExpected =
            marker.markerId.value == expectedSafeIdentityLabel &&
                marker.safeId.value == expectedSafeIdentityLabel &&
                marker.syntheticIdentityLabel.value == expectedSafeIdentityLabel
        val markerIdentityLabelSafe =
            markerIdentityLabelMatchesExpected &&
                marker.testOnlyNamespaceConformant &&
                marker.deterministicSafeIdentity
        val markerSafeLabelOnly =
            markerIdentityLabelSafe &&
                marker.displayLabel.value == "inert synthetic marker" &&
                !marker.toString().contains(expectedSafeIdentityLabel)
        val transitionGatePresent =
            marker.transitionGateReviewed &&
                transitionGate.reviewReadyForHumanDecision
        val transitionGateHumanReviewReady =
            marker.transitionGateHumanReviewReady &&
                transitionGate.reviewReadyForHumanDecision &&
                transitionGate.reviewReadyIsHumanDecisionOnly
        val userApprovalScopedToInertMarker =
            marker.userApprovedInertMarkerPass &&
                marker.inertMarkerOnly &&
                !transitionGate.implementationAuthorized

        val providerImplementationPresent =
            marker.providerImplementationPresent || transitionGate.providerImplementationPresent
        val productionProviderIdentityPresent =
            marker.productionProviderIdentityPresent
        val providerRegistryEntryPresent =
            marker.providerRegistryEntryPresent || transitionGate.providerRegistryEntryPresent
        val providerFactoryPresent =
            marker.providerFactoryPresent || transitionGate.providerFactoryPresent
        val providerDispatcherPresent =
            marker.providerDispatcherPresent || transitionGate.providerDispatcherPresent
        val executorTargetPresent =
            marker.executorTargetPresent || transitionGate.executorTargetPresent
        val providerHandlePresent =
            marker.runtimeSelectable ||
                marker.registrySelectable ||
                marker.factoryReachable ||
                marker.dispatcherReachable
        val containsVaultCryptoProvider =
            marker.vaultCryptoProviderInstanceExposed
        val implementsVaultCryptoProvider =
            marker.implementsVaultCryptoProvider
        val providerOperationExecuted =
            marker.providerOperationExecuted || transitionGate.providerOperationExecuted
        val cryptoExecuted =
            marker.cryptoExecuted || transitionGate.cryptoExecuted
        val katRunnerPresent =
            marker.katRunnerPresent || transitionGate.katRunnerPresent
        val katExecutorPresent =
            marker.katExecutorPresent || transitionGate.katExecutorPresent
        val providerKatExecutorPresent =
            marker.providerKatExecutorReachable || transitionGate.providerKatExecutorPresent
        val tracePayloadPresent =
            marker.tracePayloadPresent || transitionGate.tracePayloadPresent
        val rawKatMaterialPresent =
            marker.rawKatMaterialPresent || transitionGate.rawKatMaterialPresent
        val publicVectorBytesPresent =
            marker.publicVectorBytesPresent || transitionGate.publicVectorBytesPresent
        val publicVectorHexPresent =
            marker.publicVectorHexPresent || transitionGate.publicVectorHexPresent
        val vaultLifecyclePresent =
            marker.canUseForVaultLifecycle || transitionGate.vaultLifecyclePresent
        val vaultPersistencePresent =
            marker.vaultPersistencePresent || transitionGate.vaultPersistencePresent
        val secureSecretStorageSuccessPresent =
            false
        val secureMetadataStorageSuccessPresent =
            false
        val productionSyncPresent =
            marker.productionSyncPresent || transitionGate.productionSyncPresent
        val signingBroadcastingPresent =
            marker.signingBroadcastingPresent || transitionGate.signingBroadcastingPresent
        val uiPresent =
            marker.uiPresent || transitionGate.uiPresent
        val endpointPresent =
            marker.endpointPresent || transitionGate.endpointPresent
        val mainnetPresent =
            marker.mainnetPresent || transitionGate.mainnetPresent
        val productionProviderSelectable =
            marker.productionProviderSelectable || transitionGate.productionProviderSelectable
        val disabledProviderOnly =
            marker.disabledProviderOnly && transitionGate.disabledProviderOnly

        val implementationAuthorizationPresent =
            marker.implementationAuthorizationPresent || transitionGate.implementationAuthorized
        val productionAuthorizationPresent =
            marker.productionAuthorizationPresent || transitionGate.productionAuthorizationPresent
        val providerSelectionAuthorizationPresent =
            marker.providerSelectionAuthorizationPresent || transitionGate.providerSelectionAuthorizationPresent
        val providerOperationAuthorizationPresent =
            marker.providerOperationAuthorizationPresent || transitionGate.providerOperationAuthorizationPresent
        val cryptoAuthorizationPresent =
            marker.cryptoAuthorizationPresent || transitionGate.cryptoAuthorizationPresent
        val katRunnerAuthorizationPresent =
            marker.katRunnerAuthorizationPresent || transitionGate.katRunnerAuthorizationPresent
        val katExecutorAuthorizationPresent =
            marker.katExecutorAuthorizationPresent || transitionGate.katExecutorAuthorizationPresent
        val providerKatExecutorAuthorizationPresent =
            marker.providerKatExecutorAuthorizationPresent || transitionGate.providerKatExecutorAuthorizationPresent
        val vaultPersistenceAuthorizationPresent =
            marker.vaultPersistenceAuthorizationPresent || transitionGate.vaultPersistenceAuthorizationPresent
        val syncAuthorizationPresent =
            marker.syncAuthorizationPresent || transitionGate.syncAuthorizationPresent
        val signingBroadcastingAuthorizationPresent =
            marker.signingBroadcastingAuthorizationPresent || transitionGate.signingBroadcastingAuthorizationPresent
        val uiAuthorizationPresent =
            marker.uiAuthorizationPresent || transitionGate.uiAuthorizationPresent
        val endpointAuthorizationPresent =
            marker.endpointAuthorizationPresent || transitionGate.endpointAuthorizationPresent
        val mainnetAuthorizationPresent =
            marker.mainnetAuthorizationPresent || transitionGate.mainnetAuthorizationPresent

        val forbiddenRuntimeSurfacePresent =
            providerImplementationPresent ||
                productionProviderIdentityPresent ||
                providerRegistryEntryPresent ||
                providerFactoryPresent ||
                providerDispatcherPresent ||
                executorTargetPresent ||
                providerHandlePresent ||
                containsVaultCryptoProvider ||
                implementsVaultCryptoProvider ||
                providerOperationExecuted ||
                cryptoExecuted ||
                katRunnerPresent ||
                katExecutorPresent ||
                providerKatExecutorPresent ||
                tracePayloadPresent ||
                rawKatMaterialPresent ||
                publicVectorBytesPresent ||
                publicVectorHexPresent ||
                vaultLifecyclePresent ||
                vaultPersistencePresent ||
                secureSecretStorageSuccessPresent ||
                secureMetadataStorageSuccessPresent ||
                productionSyncPresent ||
                signingBroadcastingPresent ||
                uiPresent ||
                endpointPresent ||
                mainnetPresent
        val forbiddenAuthorizationPresent =
            implementationAuthorizationPresent ||
                productionAuthorizationPresent ||
                providerSelectionAuthorizationPresent ||
                providerOperationAuthorizationPresent ||
                cryptoAuthorizationPresent ||
                katRunnerAuthorizationPresent ||
                katExecutorAuthorizationPresent ||
                providerKatExecutorAuthorizationPresent ||
                vaultPersistenceAuthorizationPresent ||
                syncAuthorizationPresent ||
                signingBroadcastingAuthorizationPresent ||
                uiAuthorizationPresent ||
                endpointAuthorizationPresent ||
                mainnetAuthorizationPresent

        val failures = buildList {
            if (!markerPresent) {
                add(SkaldVaultV1TestOnlyProviderIdentityMarkerValidationFailureLabel.MarkerEvidenceMissing)
            }
            if (!markerCommonTestOnly) {
                add(SkaldVaultV1TestOnlyProviderIdentityMarkerValidationFailureLabel.MarkerNotCommonTestOnly)
            }
            if (!markerInert) {
                add(SkaldVaultV1TestOnlyProviderIdentityMarkerValidationFailureLabel.MarkerNotInert)
            }
            if (!markerSafeLabelOnly || !markerIdentityLabelSafe) {
                add(SkaldVaultV1TestOnlyProviderIdentityMarkerValidationFailureLabel.MarkerUnsafeIdentityLabel)
            }
            if (!transitionGatePresent || !transitionGateHumanReviewReady) {
                add(SkaldVaultV1TestOnlyProviderIdentityMarkerValidationFailureLabel.TransitionGateEvidenceMissing)
            }
            if (forbiddenRuntimeSurfacePresent) {
                add(SkaldVaultV1TestOnlyProviderIdentityMarkerValidationFailureLabel.ForbiddenRuntimeSurfacePresent)
            }
            if (forbiddenAuthorizationPresent) {
                add(SkaldVaultV1TestOnlyProviderIdentityMarkerValidationFailureLabel.ForbiddenAuthorizationPresent)
            }
            if (productionProviderSelectable) {
                add(SkaldVaultV1TestOnlyProviderIdentityMarkerValidationFailureLabel.ProductionProviderSelectable)
            }
        }

        val markerValidationPassed = failures.isEmpty()
        val blockers =
            if (markerValidationPassed) {
                emptyList()
            } else {
                listOf(SkaldVaultV1TestOnlyProviderIdentityMarkerValidationBlocker.None)
            }
        val warnings = emptyList<SkaldVaultV1TestOnlyProviderIdentityMarkerValidationWarning>()

        return SkaldVaultV1TestOnlyProviderIdentityMarkerValidation(
            validationId =
                SkaldVaultV1TestOnlyProviderIdentityMarkerValidationSafeLabel(
                    "test-only-provider-identity-marker-validation-common-test-only",
                ),
            validationVersion = 1,
            validationKind =
                SkaldVaultV1TestOnlyProviderIdentityMarkerValidationKind
                    .InertTestOnlyProviderIdentityMarkerValidation,
            sourceSet = SkaldVaultV1TestOnlyProviderIdentityMarkerValidationSourceSet.CommonTest,
            markerPresent = markerPresent,
            markerCreated = marker.markerCreated,
            implementationMarkerPresent = marker.implementationMarkerPresent,
            markerCommonTestOnly = markerCommonTestOnly,
            markerInert = markerInert,
            markerSafeLabelOnly = markerSafeLabelOnly,
            markerDeterministic = marker.deterministicSafeIdentity,
            markerValidationPassed = markerValidationPassed,
            markerValidationIsCommonTestOnlyEvidence = markerValidationPassed,
            markerCreatedIsInertMarkerEvidenceOnly =
                marker.markerCreated &&
                    marker.inertMarkerOnly &&
                    !marker.implementationAuthorizationPresent,
            implementationMarkerPresentIsInertMarkerEvidenceOnly =
                marker.implementationMarkerPresent &&
                    marker.inertMarkerOnly &&
                    !marker.implementationAuthorizationPresent,
            transitionGatePresent = transitionGatePresent,
            transitionGateHumanReviewReady = transitionGateHumanReviewReady,
            userApprovalScopedToInertMarker = userApprovalScopedToInertMarker,
            markerIdentityLabelMatchesExpected = markerIdentityLabelMatchesExpected,
            markerIdentityLabelSafe = markerIdentityLabelSafe,
            providerImplementationPresent = providerImplementationPresent,
            productionProviderIdentityPresent = productionProviderIdentityPresent,
            providerRegistryEntryPresent = providerRegistryEntryPresent,
            providerFactoryPresent = providerFactoryPresent,
            providerDispatcherPresent = providerDispatcherPresent,
            executorTargetPresent = executorTargetPresent,
            providerHandlePresent = providerHandlePresent,
            containsVaultCryptoProvider = containsVaultCryptoProvider,
            implementsVaultCryptoProvider = implementsVaultCryptoProvider,
            providerOperationExecuted = providerOperationExecuted,
            cryptoExecuted = cryptoExecuted,
            katRunnerPresent = katRunnerPresent,
            katExecutorPresent = katExecutorPresent,
            providerKatExecutorPresent = providerKatExecutorPresent,
            tracePayloadPresent = tracePayloadPresent,
            rawKatMaterialPresent = rawKatMaterialPresent,
            publicVectorBytesPresent = publicVectorBytesPresent,
            publicVectorHexPresent = publicVectorHexPresent,
            vaultLifecyclePresent = vaultLifecyclePresent,
            vaultPersistencePresent = vaultPersistencePresent,
            secureSecretStorageSuccessPresent = secureSecretStorageSuccessPresent,
            secureMetadataStorageSuccessPresent = secureMetadataStorageSuccessPresent,
            productionSyncPresent = productionSyncPresent,
            signingBroadcastingPresent = signingBroadcastingPresent,
            uiPresent = uiPresent,
            endpointPresent = endpointPresent,
            mainnetPresent = mainnetPresent,
            productionProviderSelectable = productionProviderSelectable,
            disabledProviderOnly = disabledProviderOnly,
            implementationAuthorizationPresent = implementationAuthorizationPresent,
            productionAuthorizationPresent = productionAuthorizationPresent,
            providerSelectionAuthorizationPresent = providerSelectionAuthorizationPresent,
            providerOperationAuthorizationPresent = providerOperationAuthorizationPresent,
            cryptoAuthorizationPresent = cryptoAuthorizationPresent,
            katRunnerAuthorizationPresent = katRunnerAuthorizationPresent,
            katExecutorAuthorizationPresent = katExecutorAuthorizationPresent,
            providerKatExecutorAuthorizationPresent = providerKatExecutorAuthorizationPresent,
            vaultPersistenceAuthorizationPresent = vaultPersistenceAuthorizationPresent,
            syncAuthorizationPresent = syncAuthorizationPresent,
            signingBroadcastingAuthorizationPresent = signingBroadcastingAuthorizationPresent,
            uiAuthorizationPresent = uiAuthorizationPresent,
            endpointAuthorizationPresent = endpointAuthorizationPresent,
            mainnetAuthorizationPresent = mainnetAuthorizationPresent,
            buildHistoryExcludedFromNormalSourceMaterialCorpus =
                transitionGate.buildHistoryExcludedFromNormalSourceMaterialCorpus,
            docsReadmeUseDedicatedCorpus = transitionGate.docsReadmeSeparateCorpus,
            evidenceCount = 24,
            validationCheckCount = checks.size,
            blockerCount = blockers.size,
            warningCount = warnings.size,
            validationChecks = checks,
            failureLabels = failures,
            blockers = blockers,
            warnings = warnings,
            displayLabel =
                SkaldVaultV1TestOnlyProviderIdentityMarkerValidationSafeLabel(
                    "inert marker validation evidence only",
                ),
        )
    }
}
