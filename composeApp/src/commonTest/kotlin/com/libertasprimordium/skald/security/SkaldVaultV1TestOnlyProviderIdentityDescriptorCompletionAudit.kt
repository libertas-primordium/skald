package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditSafeLabel(val value: String) {
    override fun toString(): String = "RedactedTestOnlyProviderIdentityDescriptorCompletionAuditSafeLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditKind {
    InertTestOnlyProviderIdentityDescriptorCompletionAudit,
}

enum class SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditSourceSet {
    CommonTest,
}

enum class SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditCheck {
    TransitionGatePresent,
    TransitionGateHumanReviewReady,
    MarkerPresent,
    MarkerCreated,
    ImplementationMarkerPresent,
    MarkerValidationPresent,
    MarkerValidationPassed,
    MarkerSuiteReportPresent,
    MarkerSuiteReportPassed,
    MarkerCompletionAuditPresent,
    MarkerCompletionAuditPassed,
    DescriptorPresent,
    DescriptorCreated,
    ProviderCapabilitiesDeclared,
    DescriptorValidationPresent,
    DescriptorValidationPassed,
    DescriptorSuiteReportPresent,
    DescriptorSuiteReportPassed,
    DescriptorChainComplete,
    DescriptorCommonTestOnly,
    DescriptorValidationCommonTestOnly,
    DescriptorSuiteReportCommonTestOnly,
    DescriptorInert,
    DescriptorPayloadFree,
    DescriptorSafeLabelOnly,
    DescriptorDeterministic,
    DescriptorCapabilityManifestPresent,
    DescriptorExecutableCapabilitiesAllFalse,
    MarkerIdentityLabelMatchesExpected,
    MarkerIdentityLabelSafe,
    MarkerCreatedEvidenceOnly,
    ImplementationMarkerPresentEvidenceOnly,
    MarkerValidationPassedEvidenceOnly,
    MarkerSuiteReportPassedEvidenceOnly,
    MarkerCompletionAuditPassedEvidenceOnly,
    DescriptorCreatedEvidenceOnly,
    ProviderCapabilitiesDeclaredEvidenceOnly,
    DescriptorValidationPassedEvidenceOnly,
    DescriptorSuiteReportPassedEvidenceOnly,
    UserApprovalScopedToInertProviderIdentityChain,
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
    DocsReadmeUseDedicatedCorpus,
}

enum class SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditFailureLabel {
    TransitionGateEvidenceMissing,
    MarkerEvidenceMissing,
    MarkerValidationEvidenceMissing,
    MarkerSuiteReportEvidenceMissing,
    MarkerCompletionAuditEvidenceMissing,
    DescriptorEvidenceMissing,
    DescriptorValidationEvidenceMissing,
    DescriptorSuiteReportEvidenceMissing,
    DescriptorChainIncomplete,
    DescriptorNotCommonTestOnly,
    DescriptorValidationNotCommonTestOnly,
    DescriptorSuiteReportNotCommonTestOnly,
    DescriptorNotInert,
    DescriptorPayloadPresent,
    DescriptorUnsafeLabel,
    DescriptorCapabilityManifestMissing,
    ExecutableCapabilityPresent,
    ForbiddenRuntimeSurfacePresent,
    ForbiddenAuthorizationPresent,
    ProductionProviderSelectable,
    CorpusBoundaryMissing,
}

enum class SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditBlocker {
    None,
}

enum class SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditWarning {
    None,
}

data class SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAudit(
    val descriptorCompletionAuditId: SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditSafeLabel,
    val descriptorCompletionAuditVersion: Int,
    val descriptorCompletionAuditKind: SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditKind,
    val sourceSet: SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditSourceSet,
    val transitionGatePresent: Boolean,
    val transitionGateHumanReviewReady: Boolean,
    val markerPresent: Boolean,
    val markerCreated: Boolean,
    val implementationMarkerPresent: Boolean,
    val markerValidationPresent: Boolean,
    val markerValidationPassed: Boolean,
    val markerSuiteReportPresent: Boolean,
    val markerSuiteReportPassed: Boolean,
    val markerCompletionAuditPresent: Boolean,
    val markerCompletionAuditPassed: Boolean,
    val descriptorPresent: Boolean,
    val descriptorCreated: Boolean,
    val providerCapabilitiesDeclared: Boolean,
    val descriptorValidationPresent: Boolean,
    val descriptorValidationPassed: Boolean,
    val descriptorSuiteReportPresent: Boolean,
    val descriptorSuiteReportPassed: Boolean,
    val descriptorCompletionAuditPassed: Boolean,
    val descriptorCompletionAuditPassedIsCommonTestOnlyEvidence: Boolean,
    val descriptorChainComplete: Boolean,
    val descriptorCommonTestOnly: Boolean,
    val descriptorValidationCommonTestOnly: Boolean,
    val descriptorSuiteReportCommonTestOnly: Boolean,
    val descriptorInert: Boolean,
    val descriptorPayloadFree: Boolean,
    val descriptorSafeLabelOnly: Boolean,
    val descriptorDeterministic: Boolean,
    val descriptorCapabilityManifestPresent: Boolean,
    val descriptorExecutableCapabilitiesAllFalse: Boolean,
    val markerIdentityLabelMatchesExpected: Boolean,
    val markerIdentityLabelSafe: Boolean,
    val markerCreatedIsInertMarkerEvidenceOnly: Boolean,
    val implementationMarkerPresentIsInertMarkerEvidenceOnly: Boolean,
    val markerValidationPassedIsValidationEvidenceOnly: Boolean,
    val markerSuiteReportPassedIsSuiteReportEvidenceOnly: Boolean,
    val markerCompletionAuditPassedIsCompletionAuditEvidenceOnly: Boolean,
    val descriptorCreatedIsDescriptorEvidenceOnly: Boolean,
    val providerCapabilitiesDeclaredIsDescriptorEvidenceOnly: Boolean,
    val descriptorValidationPassedIsValidationEvidenceOnly: Boolean,
    val descriptorSuiteReportPassedIsSuiteReportEvidenceOnly: Boolean,
    val userApprovalScopedToInertProviderIdentityChain: Boolean,
    val providerExecutableCapabilitiesPresent: Boolean,
    val providerImplementationPresent: Boolean,
    val productionProviderIdentityPresent: Boolean,
    val providerRegistryEntryPresent: Boolean,
    val providerFactoryPresent: Boolean,
    val providerDispatcherPresent: Boolean,
    val executorTargetPresent: Boolean,
    val providerHandlePresent: Boolean,
    val containsVaultCryptoProvider: Boolean,
    val implementsVaultCryptoProvider: Boolean,
    val vaultCryptoProviderInstanceExposed: Boolean,
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
    val canDeriveKdf: Boolean,
    val canEncrypt: Boolean,
    val canDecrypt: Boolean,
    val canGenerateKeys: Boolean,
    val canWrapKeys: Boolean,
    val canUnwrapKeys: Boolean,
    val canRunProviderOperations: Boolean,
    val canRunKat: Boolean,
    val canPersistVault: Boolean,
    val canAccessSecureStorage: Boolean,
    val canAccessSecureMetadata: Boolean,
    val canSelectInProduction: Boolean,
    val canReachMainnet: Boolean,
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
    val completionChecks: List<SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditCheck>,
    val failureLabels: List<SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditFailureLabel>,
    val blockers: List<SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditBlocker>,
    val warnings: List<SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditWarning>,
    val displayLabel: SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditSafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAudit(" +
            "REDACTED, COMMON_TEST_ONLY, PROVIDER_IDENTITY_DESCRIPTOR_COMPLETION_AUDIT_ONLY, " +
            "PAYLOAD_FREE, ALL_EXECUTABLE_CAPABILITIES_FALSE, NOT_AUTHORIZATION, DISABLED_PROVIDER_ONLY" +
            ")"
}

object SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditPolicy {
    fun currentProviderIdentityDescriptorCompletionAudit():
        SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAudit {
        val transitionGate =
            SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGatePolicy
                .currentProviderIdentityImplementationTransitionGate()
        val marker = SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy.currentImplementationMarker()
        val markerValidation =
            SkaldVaultV1TestOnlyProviderIdentityMarkerValidationPolicy.currentProviderIdentityMarkerValidation()
        val markerSuite =
            SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportPolicy.currentProviderIdentityMarkerSuiteReport()
        val markerCompletionAudit =
            SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditPolicy
                .currentProviderIdentityMarkerCompletionAudit()
        val descriptor = SkaldVaultV1TestOnlyProviderIdentityDescriptorPolicy.currentProviderIdentityDescriptor()
        val descriptorValidation =
            SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationPolicy
                .currentProviderIdentityDescriptorValidation()
        val descriptorSuiteReport =
            SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportPolicy
                .currentProviderIdentityDescriptorSuiteReport()
        val checks = SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditCheck.entries.toList()

        val descriptorSuiteReportPresent =
            descriptorSuiteReport.descriptorSuiteReportPassed &&
                descriptorSuiteReport.suiteCheckCount ==
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportCheck.entries.size &&
                descriptorSuiteReport.sourceSet ==
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportSourceSet.CommonTest
        val descriptorSuiteReportCommonTestOnly =
            descriptorSuiteReport.descriptorSuiteReportPassedIsCommonTestOnlyEvidence &&
                descriptorSuiteReport.sourceSet ==
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportSourceSet.CommonTest

        val forbiddenRuntimeSurfacePresent =
            descriptorSuiteReport.providerExecutableCapabilitiesPresent ||
                descriptorSuiteReport.providerImplementationPresent ||
                descriptorSuiteReport.productionProviderIdentityPresent ||
                descriptorSuiteReport.providerRegistryEntryPresent ||
                descriptorSuiteReport.providerFactoryPresent ||
                descriptorSuiteReport.providerDispatcherPresent ||
                descriptorSuiteReport.executorTargetPresent ||
                descriptorSuiteReport.providerHandlePresent ||
                descriptorSuiteReport.containsVaultCryptoProvider ||
                descriptorSuiteReport.implementsVaultCryptoProvider ||
                descriptorSuiteReport.vaultCryptoProviderInstanceExposed ||
                descriptorSuiteReport.providerOperationExecuted ||
                descriptorSuiteReport.cryptoExecuted ||
                descriptorSuiteReport.katRunnerPresent ||
                descriptorSuiteReport.katExecutorPresent ||
                descriptorSuiteReport.providerKatExecutorPresent ||
                descriptorSuiteReport.tracePayloadPresent ||
                descriptorSuiteReport.rawKatMaterialPresent ||
                descriptorSuiteReport.publicVectorBytesPresent ||
                descriptorSuiteReport.publicVectorHexPresent ||
                descriptorSuiteReport.vaultLifecyclePresent ||
                descriptorSuiteReport.vaultPersistencePresent ||
                descriptorSuiteReport.secureSecretStorageSuccessPresent ||
                descriptorSuiteReport.secureMetadataStorageSuccessPresent ||
                descriptorSuiteReport.productionSyncPresent ||
                descriptorSuiteReport.signingBroadcastingPresent ||
                descriptorSuiteReport.uiPresent ||
                descriptorSuiteReport.endpointPresent ||
                descriptorSuiteReport.mainnetPresent
        val forbiddenAuthorizationPresent =
            descriptorSuiteReport.implementationAuthorizationPresent ||
                descriptorSuiteReport.productionAuthorizationPresent ||
                descriptorSuiteReport.providerSelectionAuthorizationPresent ||
                descriptorSuiteReport.providerOperationAuthorizationPresent ||
                descriptorSuiteReport.cryptoAuthorizationPresent ||
                descriptorSuiteReport.katRunnerAuthorizationPresent ||
                descriptorSuiteReport.katExecutorAuthorizationPresent ||
                descriptorSuiteReport.providerKatExecutorAuthorizationPresent ||
                descriptorSuiteReport.vaultPersistenceAuthorizationPresent ||
                descriptorSuiteReport.syncAuthorizationPresent ||
                descriptorSuiteReport.signingBroadcastingAuthorizationPresent ||
                descriptorSuiteReport.uiAuthorizationPresent ||
                descriptorSuiteReport.endpointAuthorizationPresent ||
                descriptorSuiteReport.mainnetAuthorizationPresent
        val descriptorSuiteReportPassedIsSuiteReportEvidenceOnly =
            descriptorSuiteReport.descriptorSuiteReportPassed &&
                descriptorSuiteReport.descriptorSuiteReportPassedIsCommonTestOnlyEvidence &&
                descriptorSuiteReportCommonTestOnly &&
                !forbiddenAuthorizationPresent
        val descriptorChainComplete =
            descriptorSuiteReport.descriptorChainComplete &&
                descriptorSuiteReportPresent &&
                descriptorSuiteReportPassedIsSuiteReportEvidenceOnly &&
                descriptorSuiteReport.descriptorExecutableCapabilitiesAllFalse
        val userApprovalScopedToInertProviderIdentityChain =
            descriptorSuiteReport.userApprovalScopedToInertProviderIdentityChain &&
                descriptorValidation.userApprovalScopedToInertProviderIdentityChain &&
                descriptor.userApprovalScopedToInertProviderIdentityChain &&
                markerCompletionAudit.userApprovalScopedToInertMarkerChain &&
                marker.userApprovedInertMarkerPass &&
                !transitionGate.implementationAuthorized

        val failures = buildList {
            if (!descriptorSuiteReport.transitionGatePresent || !descriptorSuiteReport.transitionGateHumanReviewReady) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditFailureLabel
                        .TransitionGateEvidenceMissing,
                )
            }
            if (
                !descriptorSuiteReport.markerPresent ||
                !descriptorSuiteReport.markerCreated ||
                !descriptorSuiteReport.implementationMarkerPresent
            ) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditFailureLabel
                        .MarkerEvidenceMissing,
                )
            }
            if (!descriptorSuiteReport.markerValidationPresent || !descriptorSuiteReport.markerValidationPassed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditFailureLabel
                        .MarkerValidationEvidenceMissing,
                )
            }
            if (!descriptorSuiteReport.markerSuiteReportPresent || !descriptorSuiteReport.markerSuiteReportPassed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditFailureLabel
                        .MarkerSuiteReportEvidenceMissing,
                )
            }
            if (
                !descriptorSuiteReport.markerCompletionAuditPresent ||
                !descriptorSuiteReport.markerCompletionAuditPassed
            ) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditFailureLabel
                        .MarkerCompletionAuditEvidenceMissing,
                )
            }
            if (!descriptorSuiteReport.descriptorPresent || !descriptorSuiteReport.descriptorCreatedIsDescriptorEvidenceOnly) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditFailureLabel
                        .DescriptorEvidenceMissing,
                )
            }
            if (
                !descriptorSuiteReport.descriptorValidationPresent ||
                !descriptorSuiteReport.descriptorValidationPassedIsValidationEvidenceOnly
            ) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditFailureLabel
                        .DescriptorValidationEvidenceMissing,
                )
            }
            if (!descriptorSuiteReportPresent || !descriptorSuiteReportPassedIsSuiteReportEvidenceOnly) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditFailureLabel
                        .DescriptorSuiteReportEvidenceMissing,
                )
            }
            if (!descriptorChainComplete) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditFailureLabel
                        .DescriptorChainIncomplete,
                )
            }
            if (!descriptorSuiteReport.descriptorCommonTestOnly) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditFailureLabel
                        .DescriptorNotCommonTestOnly,
                )
            }
            if (!descriptorSuiteReport.descriptorValidationCommonTestOnly) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditFailureLabel
                        .DescriptorValidationNotCommonTestOnly,
                )
            }
            if (!descriptorSuiteReportCommonTestOnly) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditFailureLabel
                        .DescriptorSuiteReportNotCommonTestOnly,
                )
            }
            if (!descriptorSuiteReport.descriptorInert) {
                add(SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditFailureLabel.DescriptorNotInert)
            }
            if (!descriptorSuiteReport.descriptorPayloadFree) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditFailureLabel
                        .DescriptorPayloadPresent,
                )
            }
            if (
                !descriptorSuiteReport.descriptorSafeLabelOnly ||
                !descriptorSuiteReport.markerIdentityLabelMatchesExpected ||
                !descriptorSuiteReport.markerIdentityLabelSafe
            ) {
                add(SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditFailureLabel.DescriptorUnsafeLabel)
            }
            if (!descriptorSuiteReport.descriptorCapabilityManifestPresent) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditFailureLabel
                        .DescriptorCapabilityManifestMissing,
                )
            }
            if (!descriptorSuiteReport.descriptorExecutableCapabilitiesAllFalse) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditFailureLabel
                        .ExecutableCapabilityPresent,
                )
            }
            if (forbiddenRuntimeSurfacePresent) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditFailureLabel
                        .ForbiddenRuntimeSurfacePresent,
                )
            }
            if (forbiddenAuthorizationPresent) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditFailureLabel
                        .ForbiddenAuthorizationPresent,
                )
            }
            if (descriptorSuiteReport.productionProviderSelectable) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditFailureLabel
                        .ProductionProviderSelectable,
                )
            }
            if (
                !descriptorSuiteReport.buildHistoryExcludedFromNormalSourceMaterialCorpus ||
                !descriptorSuiteReport.localArtifactRootExcludedFromNormalSourceMaterialCorpus ||
                !descriptorSuiteReport.docsReadmeUseDedicatedCorpus
            ) {
                add(SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditFailureLabel.CorpusBoundaryMissing)
            }
        }

        val descriptorCompletionAuditPassed = failures.isEmpty()
        val descriptorCompletionAuditPassedIsCommonTestOnlyEvidence =
            descriptorCompletionAuditPassed &&
                !forbiddenAuthorizationPresent &&
                !descriptorSuiteReport.productionProviderSelectable
        val blockers =
            if (descriptorCompletionAuditPassed) {
                emptyList()
            } else {
                listOf(SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditBlocker.None)
            }
        val warnings = emptyList<SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditWarning>()

        return SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAudit(
            descriptorCompletionAuditId =
                SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditSafeLabel(
                    "test-only-provider-identity-descriptor-completion-audit-common-test-only",
                ),
            descriptorCompletionAuditVersion = 1,
            descriptorCompletionAuditKind =
                SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditKind
                    .InertTestOnlyProviderIdentityDescriptorCompletionAudit,
            sourceSet = SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditSourceSet.CommonTest,
            transitionGatePresent = descriptorSuiteReport.transitionGatePresent,
            transitionGateHumanReviewReady = descriptorSuiteReport.transitionGateHumanReviewReady,
            markerPresent = descriptorSuiteReport.markerPresent,
            markerCreated = descriptorSuiteReport.markerCreated,
            implementationMarkerPresent = descriptorSuiteReport.implementationMarkerPresent,
            markerValidationPresent = descriptorSuiteReport.markerValidationPresent,
            markerValidationPassed = descriptorSuiteReport.markerValidationPassed,
            markerSuiteReportPresent = descriptorSuiteReport.markerSuiteReportPresent,
            markerSuiteReportPassed = descriptorSuiteReport.markerSuiteReportPassed,
            markerCompletionAuditPresent = descriptorSuiteReport.markerCompletionAuditPresent,
            markerCompletionAuditPassed = descriptorSuiteReport.markerCompletionAuditPassed,
            descriptorPresent = descriptorSuiteReport.descriptorPresent,
            descriptorCreated = descriptorSuiteReport.descriptorCreated,
            providerCapabilitiesDeclared = descriptorSuiteReport.providerCapabilitiesDeclared,
            descriptorValidationPresent = descriptorSuiteReport.descriptorValidationPresent,
            descriptorValidationPassed = descriptorSuiteReport.descriptorValidationPassed,
            descriptorSuiteReportPresent = descriptorSuiteReportPresent,
            descriptorSuiteReportPassed = descriptorSuiteReport.descriptorSuiteReportPassed,
            descriptorCompletionAuditPassed = descriptorCompletionAuditPassed,
            descriptorCompletionAuditPassedIsCommonTestOnlyEvidence =
                descriptorCompletionAuditPassedIsCommonTestOnlyEvidence,
            descriptorChainComplete = descriptorChainComplete,
            descriptorCommonTestOnly = descriptorSuiteReport.descriptorCommonTestOnly,
            descriptorValidationCommonTestOnly = descriptorSuiteReport.descriptorValidationCommonTestOnly,
            descriptorSuiteReportCommonTestOnly = descriptorSuiteReportCommonTestOnly,
            descriptorInert = descriptorSuiteReport.descriptorInert,
            descriptorPayloadFree = descriptorSuiteReport.descriptorPayloadFree,
            descriptorSafeLabelOnly = descriptorSuiteReport.descriptorSafeLabelOnly,
            descriptorDeterministic = descriptorSuiteReport.descriptorDeterministic,
            descriptorCapabilityManifestPresent = descriptorSuiteReport.descriptorCapabilityManifestPresent,
            descriptorExecutableCapabilitiesAllFalse = descriptorSuiteReport.descriptorExecutableCapabilitiesAllFalse,
            markerIdentityLabelMatchesExpected = descriptorSuiteReport.markerIdentityLabelMatchesExpected,
            markerIdentityLabelSafe = descriptorSuiteReport.markerIdentityLabelSafe,
            markerCreatedIsInertMarkerEvidenceOnly =
                descriptorSuiteReport.markerCreatedIsInertMarkerEvidenceOnly &&
                    markerValidation.markerCreatedIsInertMarkerEvidenceOnly,
            implementationMarkerPresentIsInertMarkerEvidenceOnly =
                descriptorSuiteReport.implementationMarkerPresentIsInertMarkerEvidenceOnly &&
                    markerValidation.implementationMarkerPresentIsInertMarkerEvidenceOnly,
            markerValidationPassedIsValidationEvidenceOnly =
                descriptorSuiteReport.markerValidationPassedIsValidationEvidenceOnly,
            markerSuiteReportPassedIsSuiteReportEvidenceOnly =
                descriptorSuiteReport.markerSuiteReportPassedIsSuiteReportEvidenceOnly,
            markerCompletionAuditPassedIsCompletionAuditEvidenceOnly =
                descriptorSuiteReport.markerCompletionAuditPassedIsCompletionAuditEvidenceOnly,
            descriptorCreatedIsDescriptorEvidenceOnly =
                descriptorSuiteReport.descriptorCreatedIsDescriptorEvidenceOnly,
            providerCapabilitiesDeclaredIsDescriptorEvidenceOnly =
                descriptorSuiteReport.providerCapabilitiesDeclaredIsDescriptorEvidenceOnly,
            descriptorValidationPassedIsValidationEvidenceOnly =
                descriptorSuiteReport.descriptorValidationPassedIsValidationEvidenceOnly,
            descriptorSuiteReportPassedIsSuiteReportEvidenceOnly =
                descriptorSuiteReportPassedIsSuiteReportEvidenceOnly,
            userApprovalScopedToInertProviderIdentityChain =
                userApprovalScopedToInertProviderIdentityChain,
            providerExecutableCapabilitiesPresent = descriptorSuiteReport.providerExecutableCapabilitiesPresent,
            providerImplementationPresent = descriptorSuiteReport.providerImplementationPresent,
            productionProviderIdentityPresent = descriptorSuiteReport.productionProviderIdentityPresent,
            providerRegistryEntryPresent = descriptorSuiteReport.providerRegistryEntryPresent,
            providerFactoryPresent = descriptorSuiteReport.providerFactoryPresent,
            providerDispatcherPresent = descriptorSuiteReport.providerDispatcherPresent,
            executorTargetPresent = descriptorSuiteReport.executorTargetPresent,
            providerHandlePresent = descriptorSuiteReport.providerHandlePresent,
            containsVaultCryptoProvider = descriptorSuiteReport.containsVaultCryptoProvider,
            implementsVaultCryptoProvider = descriptorSuiteReport.implementsVaultCryptoProvider,
            vaultCryptoProviderInstanceExposed = descriptorSuiteReport.vaultCryptoProviderInstanceExposed,
            providerOperationExecuted = descriptorSuiteReport.providerOperationExecuted,
            cryptoExecuted = descriptorSuiteReport.cryptoExecuted,
            katRunnerPresent = descriptorSuiteReport.katRunnerPresent,
            katExecutorPresent = descriptorSuiteReport.katExecutorPresent,
            providerKatExecutorPresent = descriptorSuiteReport.providerKatExecutorPresent,
            tracePayloadPresent = descriptorSuiteReport.tracePayloadPresent,
            rawKatMaterialPresent = descriptorSuiteReport.rawKatMaterialPresent,
            publicVectorBytesPresent = descriptorSuiteReport.publicVectorBytesPresent,
            publicVectorHexPresent = descriptorSuiteReport.publicVectorHexPresent,
            vaultLifecyclePresent = descriptorSuiteReport.vaultLifecyclePresent,
            vaultPersistencePresent = descriptorSuiteReport.vaultPersistencePresent,
            secureSecretStorageSuccessPresent = descriptorSuiteReport.secureSecretStorageSuccessPresent,
            secureMetadataStorageSuccessPresent = descriptorSuiteReport.secureMetadataStorageSuccessPresent,
            productionSyncPresent = descriptorSuiteReport.productionSyncPresent,
            signingBroadcastingPresent = descriptorSuiteReport.signingBroadcastingPresent,
            uiPresent = descriptorSuiteReport.uiPresent,
            endpointPresent = descriptorSuiteReport.endpointPresent,
            mainnetPresent = descriptorSuiteReport.mainnetPresent,
            productionProviderSelectable = descriptorSuiteReport.productionProviderSelectable,
            disabledProviderOnly = descriptorSuiteReport.disabledProviderOnly,
            canDeriveKdf = descriptorSuiteReport.canDeriveKdf,
            canEncrypt = descriptorSuiteReport.canEncrypt,
            canDecrypt = descriptorSuiteReport.canDecrypt,
            canGenerateKeys = descriptorSuiteReport.canGenerateKeys,
            canWrapKeys = descriptorSuiteReport.canWrapKeys,
            canUnwrapKeys = descriptorSuiteReport.canUnwrapKeys,
            canRunProviderOperations = descriptorSuiteReport.canRunProviderOperations,
            canRunKat = descriptorSuiteReport.canRunKat,
            canPersistVault = descriptorSuiteReport.canPersistVault,
            canAccessSecureStorage = descriptorSuiteReport.canAccessSecureStorage,
            canAccessSecureMetadata = descriptorSuiteReport.canAccessSecureMetadata,
            canSelectInProduction = descriptorSuiteReport.canSelectInProduction,
            canReachMainnet = descriptorSuiteReport.canReachMainnet,
            implementationAuthorizationPresent = descriptorSuiteReport.implementationAuthorizationPresent,
            productionAuthorizationPresent = descriptorSuiteReport.productionAuthorizationPresent,
            providerSelectionAuthorizationPresent = descriptorSuiteReport.providerSelectionAuthorizationPresent,
            providerOperationAuthorizationPresent = descriptorSuiteReport.providerOperationAuthorizationPresent,
            cryptoAuthorizationPresent = descriptorSuiteReport.cryptoAuthorizationPresent,
            katRunnerAuthorizationPresent = descriptorSuiteReport.katRunnerAuthorizationPresent,
            katExecutorAuthorizationPresent = descriptorSuiteReport.katExecutorAuthorizationPresent,
            providerKatExecutorAuthorizationPresent = descriptorSuiteReport.providerKatExecutorAuthorizationPresent,
            vaultPersistenceAuthorizationPresent = descriptorSuiteReport.vaultPersistenceAuthorizationPresent,
            syncAuthorizationPresent = descriptorSuiteReport.syncAuthorizationPresent,
            signingBroadcastingAuthorizationPresent = descriptorSuiteReport.signingBroadcastingAuthorizationPresent,
            uiAuthorizationPresent = descriptorSuiteReport.uiAuthorizationPresent,
            endpointAuthorizationPresent = descriptorSuiteReport.endpointAuthorizationPresent,
            mainnetAuthorizationPresent = descriptorSuiteReport.mainnetAuthorizationPresent,
            buildHistoryExcludedFromNormalSourceMaterialCorpus =
                descriptorSuiteReport.buildHistoryExcludedFromNormalSourceMaterialCorpus,
            localArtifactRootExcludedFromNormalSourceMaterialCorpus =
                descriptorSuiteReport.localArtifactRootExcludedFromNormalSourceMaterialCorpus,
            docsReadmeUseDedicatedCorpus = descriptorSuiteReport.docsReadmeUseDedicatedCorpus,
            evidenceCount = 68,
            completionCheckCount = checks.size,
            blockerCount = blockers.size,
            warningCount = warnings.size,
            completionChecks = checks,
            failureLabels = failures,
            blockers = blockers,
            warnings = warnings,
            displayLabel =
                SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditSafeLabel(
                    "inert provider identity descriptor completion audit evidence only",
                ),
        )
    }
}
