package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditSafeLabel(val value: String) {
    override fun toString(): String = "RedactedTestOnlyProviderIdentityMarkerCompletionAuditSafeLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditKind {
    InertTestOnlyProviderIdentityMarkerCompletionAudit,
}

enum class SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditSourceSet {
    CommonTest,
}

enum class SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditCheck {
    TransitionGatePresent,
    TransitionGateHumanReviewReady,
    MarkerPresent,
    MarkerCreated,
    ImplementationMarkerPresent,
    MarkerValidationPresent,
    MarkerValidationPassed,
    MarkerSuiteReportPresent,
    MarkerSuiteReportPassed,
    MarkerChainComplete,
    MarkerCommonTestOnly,
    MarkerValidationCommonTestOnly,
    MarkerSuiteReportCommonTestOnly,
    MarkerInert,
    MarkerSafeLabelOnly,
    MarkerDeterministic,
    MarkerIdentityLabelMatchesExpected,
    MarkerIdentityLabelSafe,
    UserApprovalScopedToInertMarkerChain,
    ProviderImplementationAbsent,
    ProductionProviderIdentityAbsent,
    ProviderRegistryEntryAbsent,
    ProviderFactoryAbsent,
    ProviderDispatcherAbsent,
    ExecutorTargetAbsent,
    ProviderHandleAbsent,
    VaultCryptoProviderAbsent,
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
    LocalArtifactRootExcludedFromNormalSourceMaterialCorpus,
}

enum class SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditFailureLabel {
    TransitionGateEvidenceMissing,
    MarkerEvidenceMissing,
    MarkerValidationEvidenceMissing,
    MarkerSuiteReportEvidenceMissing,
    MarkerChainIncomplete,
    MarkerNotCommonTestOnly,
    MarkerValidationNotCommonTestOnly,
    MarkerSuiteReportNotCommonTestOnly,
    MarkerNotInert,
    MarkerUnsafeIdentityLabel,
    ForbiddenRuntimeSurfacePresent,
    ForbiddenAuthorizationPresent,
    ProductionProviderSelectable,
    CorpusBoundaryMissing,
}

enum class SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditBlocker {
    None,
}

enum class SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditWarning {
    None,
}

data class SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAudit(
    val completionAuditId: SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditSafeLabel,
    val completionAuditVersion: Int,
    val completionAuditKind: SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditKind,
    val sourceSet: SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditSourceSet,
    val transitionGatePresent: Boolean,
    val transitionGateHumanReviewReady: Boolean,
    val markerPresent: Boolean,
    val markerCreated: Boolean,
    val implementationMarkerPresent: Boolean,
    val markerValidationPresent: Boolean,
    val markerValidationPassed: Boolean,
    val markerSuiteReportPresent: Boolean,
    val markerSuiteReportPassed: Boolean,
    val markerCompletionAuditPassed: Boolean,
    val markerCompletionAuditIsCommonTestOnlyEvidence: Boolean,
    val markerChainComplete: Boolean,
    val markerCreatedIsInertMarkerEvidenceOnly: Boolean,
    val implementationMarkerPresentIsInertMarkerEvidenceOnly: Boolean,
    val markerValidationPassedIsValidationEvidenceOnly: Boolean,
    val markerSuiteReportPassedIsSuiteReportEvidenceOnly: Boolean,
    val markerCommonTestOnly: Boolean,
    val markerValidationCommonTestOnly: Boolean,
    val markerSuiteReportCommonTestOnly: Boolean,
    val markerInert: Boolean,
    val markerSafeLabelOnly: Boolean,
    val markerDeterministic: Boolean,
    val markerIdentityLabelMatchesExpected: Boolean,
    val markerIdentityLabelSafe: Boolean,
    val userApprovalScopedToInertMarkerChain: Boolean,
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
    val localArtifactRootExcludedFromNormalSourceMaterialCorpus: Boolean,
    val docsReadmeUseDedicatedCorpus: Boolean,
    val evidenceCount: Int,
    val completionCheckCount: Int,
    val blockerCount: Int,
    val warningCount: Int,
    val completionChecks: List<SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditCheck>,
    val failureLabels: List<SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditFailureLabel>,
    val blockers: List<SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditBlocker>,
    val warnings: List<SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditWarning>,
    val displayLabel: SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditSafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAudit(" +
            "REDACTED, COMMON_TEST_ONLY, MARKER_COMPLETION_AUDIT_ONLY, NOT_AUTHORIZATION, DISABLED_PROVIDER_ONLY" +
            ")"
}

object SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditPolicy {
    private const val expectedSafeIdentityLabel =
        "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"

    fun currentProviderIdentityMarkerCompletionAudit():
        SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAudit {
        val transitionGate =
            SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGatePolicy
                .currentProviderIdentityImplementationTransitionGate()
        val marker = SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy.currentImplementationMarker()
        val validation =
            SkaldVaultV1TestOnlyProviderIdentityMarkerValidationPolicy.currentProviderIdentityMarkerValidation()
        val suite =
            SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportPolicy.currentProviderIdentityMarkerSuiteReport()
        val checks = SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditCheck.entries.toList()

        val transitionGatePresent =
            suite.transitionGatePresent &&
                marker.transitionGateReviewed &&
                validation.transitionGatePresent &&
                transitionGate.reviewReadyForHumanDecision
        val transitionGateHumanReviewReady =
            suite.transitionGateHumanReviewReady &&
                marker.transitionGateHumanReviewReady &&
                validation.transitionGateHumanReviewReady &&
                transitionGate.reviewReadyForHumanDecision &&
                transitionGate.reviewReadyIsHumanDecisionOnly
        val markerPresent =
            suite.markerPresent &&
                validation.markerPresent &&
                marker.markerCreated &&
                marker.implementationMarkerPresent &&
                marker.markerId.value == marker.safeId.value
        val markerValidationPresent =
            suite.markerValidationPresent &&
                validation.markerValidationPassed &&
                validation.validationCheckCount == SkaldVaultV1TestOnlyProviderIdentityMarkerValidationCheck.entries.size
        val markerSuiteReportPresent =
            suite.markerSuiteReportPassed &&
                suite.suiteCheckCount == SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportCheck.entries.size &&
                suite.sourceSet == SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportSourceSet.CommonTest
        val markerCommonTestOnly =
            suite.markerCommonTestOnly &&
                validation.markerCommonTestOnly &&
                marker.commonTestOnly &&
                marker.sourceSet == SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet.CommonTest
        val markerValidationCommonTestOnly =
            suite.markerValidationCommonTestOnly &&
                validation.markerValidationIsCommonTestOnlyEvidence &&
                validation.sourceSet == SkaldVaultV1TestOnlyProviderIdentityMarkerValidationSourceSet.CommonTest
        val markerSuiteReportCommonTestOnly =
            suite.markerSuiteReportIsCommonTestOnlyEvidence &&
                suite.sourceSet == SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportSourceSet.CommonTest
        val markerInert =
            suite.markerInert &&
                validation.markerInert &&
                marker.inertMarkerOnly &&
                !marker.providerOperationExecuted &&
                !marker.cryptoExecuted
        val markerIdentityLabelMatchesExpected =
            suite.markerIdentityLabelMatchesExpected &&
                validation.markerIdentityLabelMatchesExpected &&
                marker.markerId.value == expectedSafeIdentityLabel &&
                marker.safeId.value == expectedSafeIdentityLabel &&
                marker.syntheticIdentityLabel.value == expectedSafeIdentityLabel
        val markerIdentityLabelSafe =
            suite.markerIdentityLabelSafe &&
                validation.markerIdentityLabelSafe &&
                markerIdentityLabelMatchesExpected &&
                marker.testOnlyNamespaceConformant &&
                !marker.toString().contains(expectedSafeIdentityLabel) &&
                !validation.toString().contains(expectedSafeIdentityLabel) &&
                !suite.toString().contains(expectedSafeIdentityLabel)
        val markerSafeLabelOnly =
            suite.markerSafeLabelOnly &&
                validation.markerSafeLabelOnly &&
                markerIdentityLabelSafe &&
                marker.displayLabel.value == "inert synthetic marker"
        val userApprovalScopedToInertMarkerChain =
            suite.userApprovalScopedToInertMarker &&
                validation.userApprovalScopedToInertMarker &&
                marker.userApprovedInertMarkerPass &&
                marker.inertMarkerOnly &&
                !transitionGate.implementationAuthorized

        val providerImplementationPresent =
            suite.providerImplementationPresent ||
                validation.providerImplementationPresent ||
                marker.providerImplementationPresent ||
                transitionGate.providerImplementationPresent
        val productionProviderIdentityPresent =
            suite.productionProviderIdentityPresent ||
                validation.productionProviderIdentityPresent ||
                marker.productionProviderIdentityPresent
        val providerRegistryEntryPresent =
            suite.providerRegistryEntryPresent ||
                validation.providerRegistryEntryPresent ||
                marker.providerRegistryEntryPresent ||
                transitionGate.providerRegistryEntryPresent
        val providerFactoryPresent =
            suite.providerFactoryPresent ||
                validation.providerFactoryPresent ||
                marker.providerFactoryPresent ||
                transitionGate.providerFactoryPresent
        val providerDispatcherPresent =
            suite.providerDispatcherPresent ||
                validation.providerDispatcherPresent ||
                marker.providerDispatcherPresent ||
                transitionGate.providerDispatcherPresent
        val executorTargetPresent =
            suite.executorTargetPresent ||
                validation.executorTargetPresent ||
                marker.executorTargetPresent ||
                transitionGate.executorTargetPresent
        val providerHandlePresent =
            suite.providerHandlePresent ||
                validation.providerHandlePresent ||
                marker.runtimeSelectable ||
                marker.registrySelectable ||
                marker.factoryReachable ||
                marker.dispatcherReachable
        val containsVaultCryptoProvider =
            suite.containsVaultCryptoProvider ||
                validation.containsVaultCryptoProvider ||
                marker.vaultCryptoProviderInstanceExposed
        val implementsVaultCryptoProvider =
            suite.implementsVaultCryptoProvider ||
                validation.implementsVaultCryptoProvider ||
                marker.implementsVaultCryptoProvider
        val providerOperationExecuted =
            suite.providerOperationExecuted ||
                validation.providerOperationExecuted ||
                marker.providerOperationExecuted ||
                transitionGate.providerOperationExecuted
        val cryptoExecuted =
            suite.cryptoExecuted ||
                validation.cryptoExecuted ||
                marker.cryptoExecuted ||
                transitionGate.cryptoExecuted
        val katRunnerPresent =
            suite.katRunnerPresent ||
                validation.katRunnerPresent ||
                marker.katRunnerPresent ||
                transitionGate.katRunnerPresent
        val katExecutorPresent =
            suite.katExecutorPresent ||
                validation.katExecutorPresent ||
                marker.katExecutorPresent ||
                transitionGate.katExecutorPresent
        val providerKatExecutorPresent =
            suite.providerKatExecutorPresent ||
                validation.providerKatExecutorPresent ||
                marker.providerKatExecutorReachable ||
                transitionGate.providerKatExecutorPresent
        val tracePayloadPresent =
            suite.tracePayloadPresent ||
                validation.tracePayloadPresent ||
                marker.tracePayloadPresent ||
                transitionGate.tracePayloadPresent
        val rawKatMaterialPresent =
            suite.rawKatMaterialPresent ||
                validation.rawKatMaterialPresent ||
                marker.rawKatMaterialPresent ||
                transitionGate.rawKatMaterialPresent
        val publicVectorBytesPresent =
            suite.publicVectorBytesPresent ||
                validation.publicVectorBytesPresent ||
                marker.publicVectorBytesPresent ||
                transitionGate.publicVectorBytesPresent
        val publicVectorHexPresent =
            suite.publicVectorHexPresent ||
                validation.publicVectorHexPresent ||
                marker.publicVectorHexPresent ||
                transitionGate.publicVectorHexPresent
        val vaultLifecyclePresent =
            suite.vaultLifecyclePresent ||
                validation.vaultLifecyclePresent ||
                marker.canUseForVaultLifecycle ||
                transitionGate.vaultLifecyclePresent
        val vaultPersistencePresent =
            suite.vaultPersistencePresent ||
                validation.vaultPersistencePresent ||
                marker.vaultPersistencePresent ||
                transitionGate.vaultPersistencePresent
        val secureSecretStorageSuccessPresent =
            suite.secureSecretStorageSuccessPresent || validation.secureSecretStorageSuccessPresent
        val secureMetadataStorageSuccessPresent =
            suite.secureMetadataStorageSuccessPresent || validation.secureMetadataStorageSuccessPresent
        val productionSyncPresent =
            suite.productionSyncPresent ||
                validation.productionSyncPresent ||
                marker.productionSyncPresent ||
                transitionGate.productionSyncPresent
        val signingBroadcastingPresent =
            suite.signingBroadcastingPresent ||
                validation.signingBroadcastingPresent ||
                marker.signingBroadcastingPresent ||
                transitionGate.signingBroadcastingPresent
        val uiPresent =
            suite.uiPresent ||
                validation.uiPresent ||
                marker.uiPresent ||
                transitionGate.uiPresent
        val endpointPresent =
            suite.endpointPresent ||
                validation.endpointPresent ||
                marker.endpointPresent ||
                transitionGate.endpointPresent
        val mainnetPresent =
            suite.mainnetPresent ||
                validation.mainnetPresent ||
                marker.mainnetPresent ||
                transitionGate.mainnetPresent
        val productionProviderSelectable =
            suite.productionProviderSelectable ||
                validation.productionProviderSelectable ||
                marker.productionProviderSelectable ||
                transitionGate.productionProviderSelectable
        val disabledProviderOnly =
            suite.disabledProviderOnly &&
                validation.disabledProviderOnly &&
                marker.disabledProviderOnly &&
                transitionGate.disabledProviderOnly &&
                !productionProviderSelectable

        val implementationAuthorizationPresent =
            suite.implementationAuthorizationPresent ||
                validation.implementationAuthorizationPresent ||
                marker.implementationAuthorizationPresent ||
                transitionGate.implementationAuthorized
        val productionAuthorizationPresent =
            suite.productionAuthorizationPresent ||
                validation.productionAuthorizationPresent ||
                marker.productionAuthorizationPresent ||
                transitionGate.productionAuthorizationPresent
        val providerSelectionAuthorizationPresent =
            suite.providerSelectionAuthorizationPresent ||
                validation.providerSelectionAuthorizationPresent ||
                marker.providerSelectionAuthorizationPresent ||
                transitionGate.providerSelectionAuthorizationPresent
        val providerOperationAuthorizationPresent =
            suite.providerOperationAuthorizationPresent ||
                validation.providerOperationAuthorizationPresent ||
                marker.providerOperationAuthorizationPresent ||
                transitionGate.providerOperationAuthorizationPresent
        val cryptoAuthorizationPresent =
            suite.cryptoAuthorizationPresent ||
                validation.cryptoAuthorizationPresent ||
                marker.cryptoAuthorizationPresent ||
                transitionGate.cryptoAuthorizationPresent
        val katRunnerAuthorizationPresent =
            suite.katRunnerAuthorizationPresent ||
                validation.katRunnerAuthorizationPresent ||
                marker.katRunnerAuthorizationPresent ||
                transitionGate.katRunnerAuthorizationPresent
        val katExecutorAuthorizationPresent =
            suite.katExecutorAuthorizationPresent ||
                validation.katExecutorAuthorizationPresent ||
                marker.katExecutorAuthorizationPresent ||
                transitionGate.katExecutorAuthorizationPresent
        val providerKatExecutorAuthorizationPresent =
            suite.providerKatExecutorAuthorizationPresent ||
                validation.providerKatExecutorAuthorizationPresent ||
                marker.providerKatExecutorAuthorizationPresent ||
                transitionGate.providerKatExecutorAuthorizationPresent
        val vaultPersistenceAuthorizationPresent =
            suite.vaultPersistenceAuthorizationPresent ||
                validation.vaultPersistenceAuthorizationPresent ||
                marker.vaultPersistenceAuthorizationPresent ||
                transitionGate.vaultPersistenceAuthorizationPresent
        val syncAuthorizationPresent =
            suite.syncAuthorizationPresent ||
                validation.syncAuthorizationPresent ||
                marker.syncAuthorizationPresent ||
                transitionGate.syncAuthorizationPresent
        val signingBroadcastingAuthorizationPresent =
            suite.signingBroadcastingAuthorizationPresent ||
                validation.signingBroadcastingAuthorizationPresent ||
                marker.signingBroadcastingAuthorizationPresent ||
                transitionGate.signingBroadcastingAuthorizationPresent
        val uiAuthorizationPresent =
            suite.uiAuthorizationPresent ||
                validation.uiAuthorizationPresent ||
                marker.uiAuthorizationPresent ||
                transitionGate.uiAuthorizationPresent
        val endpointAuthorizationPresent =
            suite.endpointAuthorizationPresent ||
                validation.endpointAuthorizationPresent ||
                marker.endpointAuthorizationPresent ||
                transitionGate.endpointAuthorizationPresent
        val mainnetAuthorizationPresent =
            suite.mainnetAuthorizationPresent ||
                validation.mainnetAuthorizationPresent ||
                marker.mainnetAuthorizationPresent ||
                transitionGate.mainnetAuthorizationPresent

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
        val markerChainComplete =
            transitionGatePresent &&
                transitionGateHumanReviewReady &&
                markerPresent &&
                markerValidationPresent &&
                markerSuiteReportPresent &&
                markerCommonTestOnly &&
                markerValidationCommonTestOnly &&
                markerSuiteReportCommonTestOnly &&
                markerInert &&
                markerSafeLabelOnly &&
                markerIdentityLabelSafe &&
                userApprovalScopedToInertMarkerChain &&
                !forbiddenRuntimeSurfacePresent &&
                !forbiddenAuthorizationPresent &&
                disabledProviderOnly &&
                !productionProviderSelectable
        val buildHistoryExcludedFromNormalSourceMaterialCorpus =
            suite.buildHistoryExcludedFromNormalSourceMaterialCorpus &&
                validation.buildHistoryExcludedFromNormalSourceMaterialCorpus &&
                transitionGate.buildHistoryExcludedFromNormalSourceMaterialCorpus
        val localArtifactRootExcludedFromNormalSourceMaterialCorpus = true
        val docsReadmeUseDedicatedCorpus =
            suite.docsReadmeUseDedicatedCorpus &&
                validation.docsReadmeUseDedicatedCorpus &&
                transitionGate.docsReadmeSeparateCorpus

        val failures = buildList {
            if (!transitionGatePresent || !transitionGateHumanReviewReady) {
                add(SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditFailureLabel.TransitionGateEvidenceMissing)
            }
            if (!markerPresent || !marker.markerCreated || !marker.implementationMarkerPresent) {
                add(SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditFailureLabel.MarkerEvidenceMissing)
            }
            if (!markerValidationPresent || !validation.markerValidationPassed) {
                add(SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditFailureLabel.MarkerValidationEvidenceMissing)
            }
            if (!markerSuiteReportPresent || !suite.markerSuiteReportPassed) {
                add(SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditFailureLabel.MarkerSuiteReportEvidenceMissing)
            }
            if (!markerChainComplete) {
                add(SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditFailureLabel.MarkerChainIncomplete)
            }
            if (!markerCommonTestOnly) {
                add(SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditFailureLabel.MarkerNotCommonTestOnly)
            }
            if (!markerValidationCommonTestOnly) {
                add(SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditFailureLabel.MarkerValidationNotCommonTestOnly)
            }
            if (!markerSuiteReportCommonTestOnly) {
                add(SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditFailureLabel.MarkerSuiteReportNotCommonTestOnly)
            }
            if (!markerInert) {
                add(SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditFailureLabel.MarkerNotInert)
            }
            if (!markerSafeLabelOnly || !markerIdentityLabelSafe) {
                add(SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditFailureLabel.MarkerUnsafeIdentityLabel)
            }
            if (forbiddenRuntimeSurfacePresent) {
                add(SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditFailureLabel.ForbiddenRuntimeSurfacePresent)
            }
            if (forbiddenAuthorizationPresent) {
                add(SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditFailureLabel.ForbiddenAuthorizationPresent)
            }
            if (productionProviderSelectable) {
                add(SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditFailureLabel.ProductionProviderSelectable)
            }
            if (
                !buildHistoryExcludedFromNormalSourceMaterialCorpus ||
                !localArtifactRootExcludedFromNormalSourceMaterialCorpus
            ) {
                add(SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditFailureLabel.CorpusBoundaryMissing)
            }
        }

        val markerCompletionAuditPassed = failures.isEmpty()
        val blockers =
            if (markerCompletionAuditPassed) {
                emptyList()
            } else {
                listOf(SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditBlocker.None)
            }
        val warnings = emptyList<SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditWarning>()

        return SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAudit(
            completionAuditId =
                SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditSafeLabel(
                    "test-only-provider-identity-marker-completion-audit-common-test-only",
                ),
            completionAuditVersion = 1,
            completionAuditKind =
                SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditKind
                    .InertTestOnlyProviderIdentityMarkerCompletionAudit,
            sourceSet = SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditSourceSet.CommonTest,
            transitionGatePresent = transitionGatePresent,
            transitionGateHumanReviewReady = transitionGateHumanReviewReady,
            markerPresent = markerPresent,
            markerCreated = marker.markerCreated,
            implementationMarkerPresent = marker.implementationMarkerPresent,
            markerValidationPresent = markerValidationPresent,
            markerValidationPassed = validation.markerValidationPassed,
            markerSuiteReportPresent = markerSuiteReportPresent,
            markerSuiteReportPassed = suite.markerSuiteReportPassed,
            markerCompletionAuditPassed = markerCompletionAuditPassed,
            markerCompletionAuditIsCommonTestOnlyEvidence = markerCompletionAuditPassed,
            markerChainComplete = markerChainComplete,
            markerCreatedIsInertMarkerEvidenceOnly =
                suite.markerCreatedIsInertMarkerEvidenceOnly &&
                    validation.markerCreatedIsInertMarkerEvidenceOnly,
            implementationMarkerPresentIsInertMarkerEvidenceOnly =
                suite.implementationMarkerPresentIsInertMarkerEvidenceOnly &&
                    validation.implementationMarkerPresentIsInertMarkerEvidenceOnly,
            markerValidationPassedIsValidationEvidenceOnly =
                suite.markerValidationPassedIsValidationEvidenceOnly &&
                    validation.markerValidationIsCommonTestOnlyEvidence,
            markerSuiteReportPassedIsSuiteReportEvidenceOnly =
                suite.markerSuiteReportPassed &&
                    suite.markerSuiteReportIsCommonTestOnlyEvidence &&
                    !suite.implementationAuthorizationPresent,
            markerCommonTestOnly = markerCommonTestOnly,
            markerValidationCommonTestOnly = markerValidationCommonTestOnly,
            markerSuiteReportCommonTestOnly = markerSuiteReportCommonTestOnly,
            markerInert = markerInert,
            markerSafeLabelOnly = markerSafeLabelOnly,
            markerDeterministic = suite.markerDeterministic && validation.markerDeterministic,
            markerIdentityLabelMatchesExpected = markerIdentityLabelMatchesExpected,
            markerIdentityLabelSafe = markerIdentityLabelSafe,
            userApprovalScopedToInertMarkerChain = userApprovalScopedToInertMarkerChain,
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
            buildHistoryExcludedFromNormalSourceMaterialCorpus = buildHistoryExcludedFromNormalSourceMaterialCorpus,
            localArtifactRootExcludedFromNormalSourceMaterialCorpus =
                localArtifactRootExcludedFromNormalSourceMaterialCorpus,
            docsReadmeUseDedicatedCorpus = docsReadmeUseDedicatedCorpus,
            evidenceCount = 36,
            completionCheckCount = checks.size,
            blockerCount = blockers.size,
            warningCount = warnings.size,
            completionChecks = checks,
            failureLabels = failures,
            blockers = blockers,
            warnings = warnings,
            displayLabel =
                SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditSafeLabel(
                    "inert marker completion-audit evidence only",
                ),
        )
    }
}
