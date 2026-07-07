package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportSafeLabel(val value: String) {
    override fun toString(): String = "RedactedTestOnlyProviderIdentityMarkerSuiteReportSafeLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportKind {
    InertTestOnlyProviderIdentityMarkerSuiteReport,
}

enum class SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportSourceSet {
    CommonTest,
}

enum class SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportCheck {
    TransitionGatePresent,
    TransitionGateHumanReviewReady,
    MarkerPresent,
    MarkerCreated,
    ImplementationMarkerPresent,
    MarkerValidationPresent,
    MarkerValidationPassed,
    MarkerCommonTestOnly,
    MarkerValidationCommonTestOnly,
    MarkerInert,
    MarkerSafeLabelOnly,
    MarkerDeterministic,
    MarkerIdentityLabelMatchesExpected,
    MarkerIdentityLabelSafe,
    UserApprovalScopedToInertMarker,
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
}

enum class SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportFailureLabel {
    TransitionGateEvidenceMissing,
    MarkerEvidenceMissing,
    MarkerValidationEvidenceMissing,
    MarkerNotCommonTestOnly,
    MarkerValidationNotCommonTestOnly,
    MarkerNotInert,
    MarkerUnsafeIdentityLabel,
    ForbiddenRuntimeSurfacePresent,
    ForbiddenAuthorizationPresent,
    ProductionProviderSelectable,
}

enum class SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportBlocker {
    None,
}

enum class SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportWarning {
    None,
}

data class SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReport(
    val suiteReportId: SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportSafeLabel,
    val suiteReportVersion: Int,
    val suiteReportKind: SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportKind,
    val sourceSet: SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportSourceSet,
    val transitionGatePresent: Boolean,
    val transitionGateHumanReviewReady: Boolean,
    val markerPresent: Boolean,
    val markerCreated: Boolean,
    val implementationMarkerPresent: Boolean,
    val markerValidationPresent: Boolean,
    val markerValidationPassed: Boolean,
    val markerSuiteReportPassed: Boolean,
    val markerSuiteReportIsCommonTestOnlyEvidence: Boolean,
    val markerValidationPassedIsValidationEvidenceOnly: Boolean,
    val markerCreatedIsInertMarkerEvidenceOnly: Boolean,
    val implementationMarkerPresentIsInertMarkerEvidenceOnly: Boolean,
    val markerCommonTestOnly: Boolean,
    val markerValidationCommonTestOnly: Boolean,
    val markerInert: Boolean,
    val markerSafeLabelOnly: Boolean,
    val markerDeterministic: Boolean,
    val markerIdentityLabelMatchesExpected: Boolean,
    val markerIdentityLabelSafe: Boolean,
    val userApprovalScopedToInertMarker: Boolean,
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
    val suiteCheckCount: Int,
    val blockerCount: Int,
    val warningCount: Int,
    val suiteChecks: List<SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportCheck>,
    val failureLabels: List<SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportFailureLabel>,
    val blockers: List<SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportBlocker>,
    val warnings: List<SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportWarning>,
    val displayLabel: SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportSafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReport(" +
            "REDACTED, COMMON_TEST_ONLY, MARKER_SUITE_REPORT_ONLY, NOT_AUTHORIZATION, DISABLED_PROVIDER_ONLY" +
            ")"
}

object SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportPolicy {
    private const val expectedSafeIdentityLabel =
        "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"

    fun currentProviderIdentityMarkerSuiteReport(): SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReport {
        val transitionGate =
            SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGatePolicy
                .currentProviderIdentityImplementationTransitionGate()
        val marker = SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy.currentImplementationMarker()
        val validation =
            SkaldVaultV1TestOnlyProviderIdentityMarkerValidationPolicy.currentProviderIdentityMarkerValidation()
        val checks = SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportCheck.entries.toList()

        val transitionGatePresent =
            marker.transitionGateReviewed &&
                validation.transitionGatePresent &&
                transitionGate.reviewReadyForHumanDecision
        val transitionGateHumanReviewReady =
            marker.transitionGateHumanReviewReady &&
                validation.transitionGateHumanReviewReady &&
                transitionGate.reviewReadyForHumanDecision &&
                transitionGate.reviewReadyIsHumanDecisionOnly
        val markerPresent =
            validation.markerPresent &&
                marker.markerCreated &&
                marker.implementationMarkerPresent &&
                marker.markerId.value == marker.safeId.value
        val markerValidationPresent =
            validation.markerValidationPassed &&
                validation.markerPresent &&
                validation.validationCheckCount == SkaldVaultV1TestOnlyProviderIdentityMarkerValidationCheck.entries.size
        val markerCommonTestOnly =
            marker.commonTestOnly &&
                validation.markerCommonTestOnly &&
                marker.sourceSet == SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet.CommonTest
        val markerValidationCommonTestOnly =
            validation.markerValidationIsCommonTestOnlyEvidence &&
                validation.sourceSet == SkaldVaultV1TestOnlyProviderIdentityMarkerValidationSourceSet.CommonTest
        val markerInert =
            validation.markerInert &&
                marker.inertMarkerOnly &&
                !marker.providerOperationExecuted &&
                !marker.cryptoExecuted &&
                !marker.katRunnerPresent &&
                !marker.katExecutorPresent
        val markerIdentityLabelMatchesExpected =
            validation.markerIdentityLabelMatchesExpected &&
                marker.markerId.value == expectedSafeIdentityLabel &&
                marker.safeId.value == expectedSafeIdentityLabel &&
                marker.syntheticIdentityLabel.value == expectedSafeIdentityLabel
        val markerIdentityLabelSafe =
            validation.markerIdentityLabelSafe &&
                markerIdentityLabelMatchesExpected &&
                marker.testOnlyNamespaceConformant &&
                !marker.toString().contains(expectedSafeIdentityLabel) &&
                !validation.toString().contains(expectedSafeIdentityLabel)
        val markerSafeLabelOnly =
            validation.markerSafeLabelOnly &&
                markerIdentityLabelSafe &&
                marker.displayLabel.value == "inert synthetic marker"
        val userApprovalScopedToInertMarker =
            validation.userApprovalScopedToInertMarker &&
                marker.userApprovedInertMarkerPass &&
                marker.inertMarkerOnly &&
                !transitionGate.implementationAuthorized

        val providerImplementationPresent =
            marker.providerImplementationPresent ||
                validation.providerImplementationPresent ||
                transitionGate.providerImplementationPresent
        val productionProviderIdentityPresent =
            marker.productionProviderIdentityPresent ||
                validation.productionProviderIdentityPresent
        val providerRegistryEntryPresent =
            marker.providerRegistryEntryPresent ||
                validation.providerRegistryEntryPresent ||
                transitionGate.providerRegistryEntryPresent
        val providerFactoryPresent =
            marker.providerFactoryPresent ||
                validation.providerFactoryPresent ||
                transitionGate.providerFactoryPresent
        val providerDispatcherPresent =
            marker.providerDispatcherPresent ||
                validation.providerDispatcherPresent ||
                transitionGate.providerDispatcherPresent
        val executorTargetPresent =
            marker.executorTargetPresent ||
                validation.executorTargetPresent ||
                transitionGate.executorTargetPresent
        val providerHandlePresent =
            validation.providerHandlePresent ||
                marker.runtimeSelectable ||
                marker.registrySelectable ||
                marker.factoryReachable ||
                marker.dispatcherReachable
        val containsVaultCryptoProvider =
            marker.vaultCryptoProviderInstanceExposed || validation.containsVaultCryptoProvider
        val implementsVaultCryptoProvider =
            marker.implementsVaultCryptoProvider || validation.implementsVaultCryptoProvider
        val providerOperationExecuted =
            marker.providerOperationExecuted ||
                validation.providerOperationExecuted ||
                transitionGate.providerOperationExecuted
        val cryptoExecuted =
            marker.cryptoExecuted ||
                validation.cryptoExecuted ||
                transitionGate.cryptoExecuted
        val katRunnerPresent =
            marker.katRunnerPresent ||
                validation.katRunnerPresent ||
                transitionGate.katRunnerPresent
        val katExecutorPresent =
            marker.katExecutorPresent ||
                validation.katExecutorPresent ||
                transitionGate.katExecutorPresent
        val providerKatExecutorPresent =
            marker.providerKatExecutorReachable ||
                validation.providerKatExecutorPresent ||
                transitionGate.providerKatExecutorPresent
        val tracePayloadPresent =
            marker.tracePayloadPresent ||
                validation.tracePayloadPresent ||
                transitionGate.tracePayloadPresent
        val rawKatMaterialPresent =
            marker.rawKatMaterialPresent ||
                validation.rawKatMaterialPresent ||
                transitionGate.rawKatMaterialPresent
        val publicVectorBytesPresent =
            marker.publicVectorBytesPresent ||
                validation.publicVectorBytesPresent ||
                transitionGate.publicVectorBytesPresent
        val publicVectorHexPresent =
            marker.publicVectorHexPresent ||
                validation.publicVectorHexPresent ||
                transitionGate.publicVectorHexPresent
        val vaultLifecyclePresent =
            marker.canUseForVaultLifecycle ||
                validation.vaultLifecyclePresent ||
                transitionGate.vaultLifecyclePresent
        val vaultPersistencePresent =
            marker.vaultPersistencePresent ||
                validation.vaultPersistencePresent ||
                transitionGate.vaultPersistencePresent
        val secureSecretStorageSuccessPresent = validation.secureSecretStorageSuccessPresent
        val secureMetadataStorageSuccessPresent = validation.secureMetadataStorageSuccessPresent
        val productionSyncPresent =
            marker.productionSyncPresent ||
                validation.productionSyncPresent ||
                transitionGate.productionSyncPresent
        val signingBroadcastingPresent =
            marker.signingBroadcastingPresent ||
                validation.signingBroadcastingPresent ||
                transitionGate.signingBroadcastingPresent
        val uiPresent =
            marker.uiPresent ||
                validation.uiPresent ||
                transitionGate.uiPresent
        val endpointPresent =
            marker.endpointPresent ||
                validation.endpointPresent ||
                transitionGate.endpointPresent
        val mainnetPresent =
            marker.mainnetPresent ||
                validation.mainnetPresent ||
                transitionGate.mainnetPresent
        val productionProviderSelectable =
            marker.productionProviderSelectable ||
                validation.productionProviderSelectable ||
                transitionGate.productionProviderSelectable
        val disabledProviderOnly =
            marker.disabledProviderOnly &&
                validation.disabledProviderOnly &&
                transitionGate.disabledProviderOnly &&
                !productionProviderSelectable

        val implementationAuthorizationPresent =
            marker.implementationAuthorizationPresent ||
                validation.implementationAuthorizationPresent ||
                transitionGate.implementationAuthorized
        val productionAuthorizationPresent =
            marker.productionAuthorizationPresent ||
                validation.productionAuthorizationPresent ||
                transitionGate.productionAuthorizationPresent
        val providerSelectionAuthorizationPresent =
            marker.providerSelectionAuthorizationPresent ||
                validation.providerSelectionAuthorizationPresent ||
                transitionGate.providerSelectionAuthorizationPresent
        val providerOperationAuthorizationPresent =
            marker.providerOperationAuthorizationPresent ||
                validation.providerOperationAuthorizationPresent ||
                transitionGate.providerOperationAuthorizationPresent
        val cryptoAuthorizationPresent =
            marker.cryptoAuthorizationPresent ||
                validation.cryptoAuthorizationPresent ||
                transitionGate.cryptoAuthorizationPresent
        val katRunnerAuthorizationPresent =
            marker.katRunnerAuthorizationPresent ||
                validation.katRunnerAuthorizationPresent ||
                transitionGate.katRunnerAuthorizationPresent
        val katExecutorAuthorizationPresent =
            marker.katExecutorAuthorizationPresent ||
                validation.katExecutorAuthorizationPresent ||
                transitionGate.katExecutorAuthorizationPresent
        val providerKatExecutorAuthorizationPresent =
            marker.providerKatExecutorAuthorizationPresent ||
                validation.providerKatExecutorAuthorizationPresent ||
                transitionGate.providerKatExecutorAuthorizationPresent
        val vaultPersistenceAuthorizationPresent =
            marker.vaultPersistenceAuthorizationPresent ||
                validation.vaultPersistenceAuthorizationPresent ||
                transitionGate.vaultPersistenceAuthorizationPresent
        val syncAuthorizationPresent =
            marker.syncAuthorizationPresent ||
                validation.syncAuthorizationPresent ||
                transitionGate.syncAuthorizationPresent
        val signingBroadcastingAuthorizationPresent =
            marker.signingBroadcastingAuthorizationPresent ||
                validation.signingBroadcastingAuthorizationPresent ||
                transitionGate.signingBroadcastingAuthorizationPresent
        val uiAuthorizationPresent =
            marker.uiAuthorizationPresent ||
                validation.uiAuthorizationPresent ||
                transitionGate.uiAuthorizationPresent
        val endpointAuthorizationPresent =
            marker.endpointAuthorizationPresent ||
                validation.endpointAuthorizationPresent ||
                transitionGate.endpointAuthorizationPresent
        val mainnetAuthorizationPresent =
            marker.mainnetAuthorizationPresent ||
                validation.mainnetAuthorizationPresent ||
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

        val failures = buildList {
            if (!transitionGatePresent || !transitionGateHumanReviewReady) {
                add(SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportFailureLabel.TransitionGateEvidenceMissing)
            }
            if (!markerPresent || !marker.markerCreated || !marker.implementationMarkerPresent) {
                add(SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportFailureLabel.MarkerEvidenceMissing)
            }
            if (!markerValidationPresent || !validation.markerValidationPassed) {
                add(SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportFailureLabel.MarkerValidationEvidenceMissing)
            }
            if (!markerCommonTestOnly) {
                add(SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportFailureLabel.MarkerNotCommonTestOnly)
            }
            if (!markerValidationCommonTestOnly) {
                add(SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportFailureLabel.MarkerValidationNotCommonTestOnly)
            }
            if (!markerInert) {
                add(SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportFailureLabel.MarkerNotInert)
            }
            if (!markerSafeLabelOnly || !markerIdentityLabelSafe) {
                add(SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportFailureLabel.MarkerUnsafeIdentityLabel)
            }
            if (forbiddenRuntimeSurfacePresent) {
                add(SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportFailureLabel.ForbiddenRuntimeSurfacePresent)
            }
            if (forbiddenAuthorizationPresent) {
                add(SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportFailureLabel.ForbiddenAuthorizationPresent)
            }
            if (productionProviderSelectable) {
                add(SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportFailureLabel.ProductionProviderSelectable)
            }
        }

        val markerSuiteReportPassed = failures.isEmpty()
        val blockers =
            if (markerSuiteReportPassed) {
                emptyList()
            } else {
                listOf(SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportBlocker.None)
            }
        val warnings = emptyList<SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportWarning>()

        return SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReport(
            suiteReportId =
                SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportSafeLabel(
                    "test-only-provider-identity-marker-suite-report-common-test-only",
                ),
            suiteReportVersion = 1,
            suiteReportKind =
                SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportKind
                    .InertTestOnlyProviderIdentityMarkerSuiteReport,
            sourceSet = SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportSourceSet.CommonTest,
            transitionGatePresent = transitionGatePresent,
            transitionGateHumanReviewReady = transitionGateHumanReviewReady,
            markerPresent = markerPresent,
            markerCreated = marker.markerCreated,
            implementationMarkerPresent = marker.implementationMarkerPresent,
            markerValidationPresent = markerValidationPresent,
            markerValidationPassed = validation.markerValidationPassed,
            markerSuiteReportPassed = markerSuiteReportPassed,
            markerSuiteReportIsCommonTestOnlyEvidence = markerSuiteReportPassed,
            markerValidationPassedIsValidationEvidenceOnly =
                validation.markerValidationPassed &&
                    validation.markerValidationIsCommonTestOnlyEvidence &&
                    !validation.implementationAuthorizationPresent,
            markerCreatedIsInertMarkerEvidenceOnly = validation.markerCreatedIsInertMarkerEvidenceOnly,
            implementationMarkerPresentIsInertMarkerEvidenceOnly =
                validation.implementationMarkerPresentIsInertMarkerEvidenceOnly,
            markerCommonTestOnly = markerCommonTestOnly,
            markerValidationCommonTestOnly = markerValidationCommonTestOnly,
            markerInert = markerInert,
            markerSafeLabelOnly = markerSafeLabelOnly,
            markerDeterministic = marker.deterministicSafeIdentity && validation.markerDeterministic,
            markerIdentityLabelMatchesExpected = markerIdentityLabelMatchesExpected,
            markerIdentityLabelSafe = markerIdentityLabelSafe,
            userApprovalScopedToInertMarker = userApprovalScopedToInertMarker,
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
                validation.buildHistoryExcludedFromNormalSourceMaterialCorpus &&
                    transitionGate.buildHistoryExcludedFromNormalSourceMaterialCorpus,
            docsReadmeUseDedicatedCorpus =
                validation.docsReadmeUseDedicatedCorpus &&
                    transitionGate.docsReadmeSeparateCorpus,
            evidenceCount = 30,
            suiteCheckCount = checks.size,
            blockerCount = blockers.size,
            warningCount = warnings.size,
            suiteChecks = checks,
            failureLabels = failures,
            blockers = blockers,
            warnings = warnings,
            displayLabel =
                SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportSafeLabel(
                    "inert marker suite-report evidence only",
                ),
        )
    }
}
