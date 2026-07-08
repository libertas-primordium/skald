package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportSafeLabel(val value: String) {
    override fun toString(): String = "RedactedTestOnlyProviderIdentityDescriptorSuiteReportSafeLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportKind {
    InertTestOnlyProviderIdentityDescriptorSuiteReport,
}

enum class SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportSourceSet {
    CommonTest,
}

enum class SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportCheck {
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
    DescriptorChainComplete,
    DescriptorCommonTestOnly,
    DescriptorValidationCommonTestOnly,
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

enum class SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportFailureLabel {
    TransitionGateEvidenceMissing,
    MarkerEvidenceMissing,
    MarkerValidationEvidenceMissing,
    MarkerSuiteReportEvidenceMissing,
    MarkerCompletionAuditEvidenceMissing,
    DescriptorEvidenceMissing,
    DescriptorValidationEvidenceMissing,
    DescriptorChainIncomplete,
    DescriptorNotCommonTestOnly,
    DescriptorValidationNotCommonTestOnly,
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

enum class SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportBlocker {
    None,
}

enum class SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportWarning {
    None,
}

data class SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReport(
    val descriptorSuiteReportId: SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportSafeLabel,
    val descriptorSuiteReportVersion: Int,
    val descriptorSuiteReportKind: SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportKind,
    val sourceSet: SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportSourceSet,
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
    val descriptorSuiteReportPassed: Boolean,
    val descriptorSuiteReportPassedIsCommonTestOnlyEvidence: Boolean,
    val descriptorChainComplete: Boolean,
    val descriptorCommonTestOnly: Boolean,
    val descriptorValidationCommonTestOnly: Boolean,
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
    val suiteCheckCount: Int,
    val blockerCount: Int,
    val warningCount: Int,
    val suiteChecks: List<SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportCheck>,
    val failureLabels: List<SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportFailureLabel>,
    val blockers: List<SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportBlocker>,
    val warnings: List<SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportWarning>,
    val displayLabel: SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportSafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReport(" +
            "REDACTED, COMMON_TEST_ONLY, PROVIDER_IDENTITY_DESCRIPTOR_SUITE_REPORT_ONLY, " +
            "PAYLOAD_FREE, ALL_EXECUTABLE_CAPABILITIES_FALSE, NOT_AUTHORIZATION, DISABLED_PROVIDER_ONLY" +
            ")"
}

object SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportPolicy {
    fun currentProviderIdentityDescriptorSuiteReport():
        SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReport {
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
        val checks = SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportCheck.entries.toList()

        val descriptorPresent =
            descriptorValidation.descriptorPresent &&
                descriptor.descriptorCreated &&
                descriptor.descriptorCheckCount == SkaldVaultV1TestOnlyProviderIdentityDescriptorCheck.entries.size &&
                descriptor.sourceSet == SkaldVaultV1TestOnlyProviderIdentityDescriptorSourceSet.CommonTest
        val descriptorValidationPresent =
            descriptorValidation.descriptorValidationPassed &&
                descriptorValidation.validationCheckCount ==
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationCheck.entries.size &&
                descriptorValidation.sourceSet ==
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationSourceSet.CommonTest
        val descriptorCapabilityManifestPresent =
            descriptorValidation.descriptorCapabilityManifestPresent &&
                descriptor.providerCapabilitiesDeclared &&
                descriptor.capabilityManifest.providerCapabilitiesDeclared &&
                descriptor.capabilityManifest.capabilityCount ==
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorCapability.entries.size
        val descriptorExecutableCapabilitiesAllFalse =
            descriptorValidation.descriptorExecutableCapabilitiesAllFalse &&
                !descriptorValidation.providerExecutableCapabilitiesPresent &&
                !descriptorValidation.canDeriveKdf &&
                !descriptorValidation.canEncrypt &&
                !descriptorValidation.canDecrypt &&
                !descriptorValidation.canGenerateKeys &&
                !descriptorValidation.canWrapKeys &&
                !descriptorValidation.canUnwrapKeys &&
                !descriptorValidation.canRunProviderOperations &&
                !descriptorValidation.canRunKat &&
                !descriptorValidation.canPersistVault &&
                !descriptorValidation.canAccessSecureStorage &&
                !descriptorValidation.canAccessSecureMetadata &&
                !descriptorValidation.canSelectInProduction &&
                !descriptorValidation.canReachMainnet
        val descriptorCommonTestOnly =
            descriptor.descriptorCommonTestOnly &&
                descriptorValidation.descriptorCommonTestOnly &&
                descriptor.sourceSet == SkaldVaultV1TestOnlyProviderIdentityDescriptorSourceSet.CommonTest
        val descriptorValidationCommonTestOnly =
            descriptorValidation.descriptorValidationPassedIsCommonTestOnlyEvidence &&
                descriptorValidation.sourceSet ==
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationSourceSet.CommonTest

        val markerCreatedIsInertMarkerEvidenceOnly =
            descriptorValidation.markerCreatedIsInertMarkerEvidenceOnly &&
                descriptor.markerCreatedIsInertMarkerEvidenceOnly &&
                markerCompletionAudit.markerCreatedIsInertMarkerEvidenceOnly &&
                markerSuite.markerCreatedIsInertMarkerEvidenceOnly &&
                markerValidation.markerCreatedIsInertMarkerEvidenceOnly &&
                marker.markerCreated &&
                marker.inertMarkerOnly &&
                !marker.implementationAuthorizationPresent
        val implementationMarkerPresentIsInertMarkerEvidenceOnly =
            descriptorValidation.implementationMarkerPresentIsInertMarkerEvidenceOnly &&
                descriptor.implementationMarkerPresentIsInertMarkerEvidenceOnly &&
                markerCompletionAudit.implementationMarkerPresentIsInertMarkerEvidenceOnly &&
                markerSuite.implementationMarkerPresentIsInertMarkerEvidenceOnly &&
                markerValidation.implementationMarkerPresentIsInertMarkerEvidenceOnly &&
                marker.implementationMarkerPresent &&
                marker.inertMarkerOnly &&
                !marker.implementationAuthorizationPresent
        val markerValidationPassedIsValidationEvidenceOnly =
            descriptorValidation.markerValidationPassedIsValidationEvidenceOnly &&
                descriptor.markerValidationPassedIsValidationEvidenceOnly &&
                markerCompletionAudit.markerValidationPassedIsValidationEvidenceOnly &&
                markerSuite.markerValidationPassedIsValidationEvidenceOnly &&
                markerValidation.markerValidationIsCommonTestOnlyEvidence &&
                markerValidation.markerValidationPassed &&
                !markerValidation.implementationAuthorizationPresent
        val markerSuiteReportPassedIsSuiteReportEvidenceOnly =
            descriptorValidation.markerSuiteReportPassedIsSuiteReportEvidenceOnly &&
                descriptor.markerSuiteReportPassedIsSuiteReportEvidenceOnly &&
                markerCompletionAudit.markerSuiteReportPassedIsSuiteReportEvidenceOnly &&
                markerSuite.markerSuiteReportIsCommonTestOnlyEvidence &&
                markerSuite.markerSuiteReportPassed &&
                !markerSuite.implementationAuthorizationPresent
        val markerCompletionAuditPassedIsCompletionAuditEvidenceOnly =
            descriptorValidation.markerCompletionAuditPassedIsCompletionAuditEvidenceOnly &&
                descriptor.markerCompletionAuditPassedIsCompletionAuditEvidenceOnly &&
                markerCompletionAudit.markerCompletionAuditIsCommonTestOnlyEvidence &&
                markerCompletionAudit.markerCompletionAuditPassed &&
                !markerCompletionAudit.implementationAuthorizationPresent

        val forbiddenRuntimeSurfacePresent =
            descriptorValidation.providerExecutableCapabilitiesPresent ||
                descriptorValidation.providerImplementationPresent ||
                descriptorValidation.productionProviderIdentityPresent ||
                descriptorValidation.providerRegistryEntryPresent ||
                descriptorValidation.providerFactoryPresent ||
                descriptorValidation.providerDispatcherPresent ||
                descriptorValidation.executorTargetPresent ||
                descriptorValidation.providerHandlePresent ||
                descriptorValidation.containsVaultCryptoProvider ||
                descriptorValidation.implementsVaultCryptoProvider ||
                descriptorValidation.vaultCryptoProviderInstanceExposed ||
                descriptorValidation.providerOperationExecuted ||
                descriptorValidation.cryptoExecuted ||
                descriptorValidation.katRunnerPresent ||
                descriptorValidation.katExecutorPresent ||
                descriptorValidation.providerKatExecutorPresent ||
                descriptorValidation.tracePayloadPresent ||
                descriptorValidation.rawKatMaterialPresent ||
                descriptorValidation.publicVectorBytesPresent ||
                descriptorValidation.publicVectorHexPresent ||
                descriptorValidation.vaultLifecyclePresent ||
                descriptorValidation.vaultPersistencePresent ||
                descriptorValidation.secureSecretStorageSuccessPresent ||
                descriptorValidation.secureMetadataStorageSuccessPresent ||
                descriptorValidation.productionSyncPresent ||
                descriptorValidation.signingBroadcastingPresent ||
                descriptorValidation.uiPresent ||
                descriptorValidation.endpointPresent ||
                descriptorValidation.mainnetPresent
        val forbiddenAuthorizationPresent =
            descriptorValidation.implementationAuthorizationPresent ||
                descriptorValidation.productionAuthorizationPresent ||
                descriptorValidation.providerSelectionAuthorizationPresent ||
                descriptorValidation.providerOperationAuthorizationPresent ||
                descriptorValidation.cryptoAuthorizationPresent ||
                descriptorValidation.katRunnerAuthorizationPresent ||
                descriptorValidation.katExecutorAuthorizationPresent ||
                descriptorValidation.providerKatExecutorAuthorizationPresent ||
                descriptorValidation.vaultPersistenceAuthorizationPresent ||
                descriptorValidation.syncAuthorizationPresent ||
                descriptorValidation.signingBroadcastingAuthorizationPresent ||
                descriptorValidation.uiAuthorizationPresent ||
                descriptorValidation.endpointAuthorizationPresent ||
                descriptorValidation.mainnetAuthorizationPresent
        val descriptorCreatedIsDescriptorEvidenceOnly =
            descriptorValidation.descriptorCreatedIsDescriptorEvidenceOnly &&
                descriptor.descriptorCreatedIsCommonTestOnlyEvidence &&
                descriptorValidation.descriptorCreated &&
                descriptorCommonTestOnly &&
                !forbiddenAuthorizationPresent
        val providerCapabilitiesDeclaredIsDescriptorEvidenceOnly =
            descriptorValidation.providerCapabilitiesDeclaredIsDescriptorEvidenceOnly &&
                descriptorValidation.providerCapabilitiesDeclared &&
                descriptorCapabilityManifestPresent &&
                descriptorExecutableCapabilitiesAllFalse &&
                !forbiddenRuntimeSurfacePresent &&
                !forbiddenAuthorizationPresent
        val descriptorValidationPassedIsValidationEvidenceOnly =
            descriptorValidation.descriptorValidationPassed &&
                descriptorValidation.descriptorValidationPassedIsCommonTestOnlyEvidence &&
                descriptorValidationCommonTestOnly &&
                !forbiddenAuthorizationPresent
        val userApprovalScopedToInertProviderIdentityChain =
            descriptorValidation.userApprovalScopedToInertProviderIdentityChain &&
                descriptor.userApprovalScopedToInertProviderIdentityChain &&
                markerCompletionAudit.userApprovalScopedToInertMarkerChain &&
                marker.userApprovedInertMarkerPass &&
                !transitionGate.implementationAuthorized
        val descriptorChainComplete =
            descriptorPresent &&
                descriptorValidationPresent &&
                descriptorValidation.descriptorValidationPassed &&
                descriptorValidationPassedIsValidationEvidenceOnly &&
                descriptorCreatedIsDescriptorEvidenceOnly &&
                providerCapabilitiesDeclaredIsDescriptorEvidenceOnly &&
                descriptorExecutableCapabilitiesAllFalse

        val failures = buildList {
            if (!descriptorValidation.transitionGatePresent || !descriptorValidation.transitionGateHumanReviewReady) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportFailureLabel
                        .TransitionGateEvidenceMissing,
                )
            }
            if (
                !descriptorValidation.markerPresent ||
                !descriptorValidation.markerCreated ||
                !descriptorValidation.implementationMarkerPresent
            ) {
                add(SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportFailureLabel.MarkerEvidenceMissing)
            }
            if (!descriptorValidation.markerValidationPresent || !descriptorValidation.markerValidationPassed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportFailureLabel
                        .MarkerValidationEvidenceMissing,
                )
            }
            if (!descriptorValidation.markerSuiteReportPresent || !descriptorValidation.markerSuiteReportPassed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportFailureLabel
                        .MarkerSuiteReportEvidenceMissing,
                )
            }
            if (
                !descriptorValidation.markerCompletionAuditPresent ||
                !descriptorValidation.markerCompletionAuditPassed
            ) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportFailureLabel
                        .MarkerCompletionAuditEvidenceMissing,
                )
            }
            if (!descriptorPresent || !descriptorCreatedIsDescriptorEvidenceOnly) {
                add(SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportFailureLabel.DescriptorEvidenceMissing)
            }
            if (!descriptorValidationPresent || !descriptorValidationPassedIsValidationEvidenceOnly) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportFailureLabel
                        .DescriptorValidationEvidenceMissing,
                )
            }
            if (!descriptorChainComplete) {
                add(SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportFailureLabel.DescriptorChainIncomplete)
            }
            if (!descriptorCommonTestOnly) {
                add(SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportFailureLabel.DescriptorNotCommonTestOnly)
            }
            if (!descriptorValidationCommonTestOnly) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportFailureLabel
                        .DescriptorValidationNotCommonTestOnly,
                )
            }
            if (!descriptorValidation.descriptorInert) {
                add(SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportFailureLabel.DescriptorNotInert)
            }
            if (!descriptorValidation.descriptorPayloadFree) {
                add(SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportFailureLabel.DescriptorPayloadPresent)
            }
            if (
                !descriptorValidation.descriptorSafeLabelOnly ||
                !descriptorValidation.markerIdentityLabelMatchesExpected ||
                !descriptorValidation.markerIdentityLabelSafe
            ) {
                add(SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportFailureLabel.DescriptorUnsafeLabel)
            }
            if (!descriptorCapabilityManifestPresent) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportFailureLabel
                        .DescriptorCapabilityManifestMissing,
                )
            }
            if (!descriptorExecutableCapabilitiesAllFalse) {
                add(SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportFailureLabel.ExecutableCapabilityPresent)
            }
            if (forbiddenRuntimeSurfacePresent) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportFailureLabel
                        .ForbiddenRuntimeSurfacePresent,
                )
            }
            if (forbiddenAuthorizationPresent) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportFailureLabel
                        .ForbiddenAuthorizationPresent,
                )
            }
            if (descriptorValidation.productionProviderSelectable) {
                add(SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportFailureLabel.ProductionProviderSelectable)
            }
            if (
                !descriptorValidation.buildHistoryExcludedFromNormalSourceMaterialCorpus ||
                !descriptorValidation.localArtifactRootExcludedFromNormalSourceMaterialCorpus ||
                !descriptorValidation.docsReadmeUseDedicatedCorpus
            ) {
                add(SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportFailureLabel.CorpusBoundaryMissing)
            }
        }

        val descriptorSuiteReportPassed = failures.isEmpty()
        val descriptorSuiteReportPassedIsCommonTestOnlyEvidence =
            descriptorSuiteReportPassed &&
                !forbiddenAuthorizationPresent &&
                !descriptorValidation.productionProviderSelectable
        val blockers =
            if (descriptorSuiteReportPassed) {
                emptyList()
            } else {
                listOf(SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportBlocker.None)
            }
        val warnings = emptyList<SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportWarning>()

        return SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReport(
            descriptorSuiteReportId =
                SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportSafeLabel(
                    "test-only-provider-identity-descriptor-suite-report-common-test-only",
                ),
            descriptorSuiteReportVersion = 1,
            descriptorSuiteReportKind =
                SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportKind
                    .InertTestOnlyProviderIdentityDescriptorSuiteReport,
            sourceSet = SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportSourceSet.CommonTest,
            transitionGatePresent = descriptorValidation.transitionGatePresent,
            transitionGateHumanReviewReady = descriptorValidation.transitionGateHumanReviewReady,
            markerPresent = descriptorValidation.markerPresent,
            markerCreated = descriptorValidation.markerCreated,
            implementationMarkerPresent = descriptorValidation.implementationMarkerPresent,
            markerValidationPresent = descriptorValidation.markerValidationPresent,
            markerValidationPassed = descriptorValidation.markerValidationPassed,
            markerSuiteReportPresent = descriptorValidation.markerSuiteReportPresent,
            markerSuiteReportPassed = descriptorValidation.markerSuiteReportPassed,
            markerCompletionAuditPresent = descriptorValidation.markerCompletionAuditPresent,
            markerCompletionAuditPassed = descriptorValidation.markerCompletionAuditPassed,
            descriptorPresent = descriptorPresent,
            descriptorCreated = descriptorValidation.descriptorCreated,
            providerCapabilitiesDeclared = descriptorValidation.providerCapabilitiesDeclared,
            descriptorValidationPresent = descriptorValidationPresent,
            descriptorValidationPassed = descriptorValidation.descriptorValidationPassed,
            descriptorSuiteReportPassed = descriptorSuiteReportPassed,
            descriptorSuiteReportPassedIsCommonTestOnlyEvidence =
                descriptorSuiteReportPassedIsCommonTestOnlyEvidence,
            descriptorChainComplete = descriptorChainComplete,
            descriptorCommonTestOnly = descriptorCommonTestOnly,
            descriptorValidationCommonTestOnly = descriptorValidationCommonTestOnly,
            descriptorInert = descriptorValidation.descriptorInert,
            descriptorPayloadFree = descriptorValidation.descriptorPayloadFree,
            descriptorSafeLabelOnly = descriptorValidation.descriptorSafeLabelOnly,
            descriptorDeterministic = descriptorValidation.descriptorDeterministic,
            descriptorCapabilityManifestPresent = descriptorCapabilityManifestPresent,
            descriptorExecutableCapabilitiesAllFalse = descriptorExecutableCapabilitiesAllFalse,
            markerIdentityLabelMatchesExpected = descriptorValidation.markerIdentityLabelMatchesExpected,
            markerIdentityLabelSafe = descriptorValidation.markerIdentityLabelSafe,
            markerCreatedIsInertMarkerEvidenceOnly = markerCreatedIsInertMarkerEvidenceOnly,
            implementationMarkerPresentIsInertMarkerEvidenceOnly =
                implementationMarkerPresentIsInertMarkerEvidenceOnly,
            markerValidationPassedIsValidationEvidenceOnly = markerValidationPassedIsValidationEvidenceOnly,
            markerSuiteReportPassedIsSuiteReportEvidenceOnly =
                markerSuiteReportPassedIsSuiteReportEvidenceOnly,
            markerCompletionAuditPassedIsCompletionAuditEvidenceOnly =
                markerCompletionAuditPassedIsCompletionAuditEvidenceOnly,
            descriptorCreatedIsDescriptorEvidenceOnly = descriptorCreatedIsDescriptorEvidenceOnly,
            providerCapabilitiesDeclaredIsDescriptorEvidenceOnly =
                providerCapabilitiesDeclaredIsDescriptorEvidenceOnly,
            descriptorValidationPassedIsValidationEvidenceOnly =
                descriptorValidationPassedIsValidationEvidenceOnly,
            userApprovalScopedToInertProviderIdentityChain =
                userApprovalScopedToInertProviderIdentityChain,
            providerExecutableCapabilitiesPresent = descriptorValidation.providerExecutableCapabilitiesPresent,
            providerImplementationPresent = descriptorValidation.providerImplementationPresent,
            productionProviderIdentityPresent = descriptorValidation.productionProviderIdentityPresent,
            providerRegistryEntryPresent = descriptorValidation.providerRegistryEntryPresent,
            providerFactoryPresent = descriptorValidation.providerFactoryPresent,
            providerDispatcherPresent = descriptorValidation.providerDispatcherPresent,
            executorTargetPresent = descriptorValidation.executorTargetPresent,
            providerHandlePresent = descriptorValidation.providerHandlePresent,
            containsVaultCryptoProvider = descriptorValidation.containsVaultCryptoProvider,
            implementsVaultCryptoProvider = descriptorValidation.implementsVaultCryptoProvider,
            vaultCryptoProviderInstanceExposed = descriptorValidation.vaultCryptoProviderInstanceExposed,
            providerOperationExecuted = descriptorValidation.providerOperationExecuted,
            cryptoExecuted = descriptorValidation.cryptoExecuted,
            katRunnerPresent = descriptorValidation.katRunnerPresent,
            katExecutorPresent = descriptorValidation.katExecutorPresent,
            providerKatExecutorPresent = descriptorValidation.providerKatExecutorPresent,
            tracePayloadPresent = descriptorValidation.tracePayloadPresent,
            rawKatMaterialPresent = descriptorValidation.rawKatMaterialPresent,
            publicVectorBytesPresent = descriptorValidation.publicVectorBytesPresent,
            publicVectorHexPresent = descriptorValidation.publicVectorHexPresent,
            vaultLifecyclePresent = descriptorValidation.vaultLifecyclePresent,
            vaultPersistencePresent = descriptorValidation.vaultPersistencePresent,
            secureSecretStorageSuccessPresent = descriptorValidation.secureSecretStorageSuccessPresent,
            secureMetadataStorageSuccessPresent = descriptorValidation.secureMetadataStorageSuccessPresent,
            productionSyncPresent = descriptorValidation.productionSyncPresent,
            signingBroadcastingPresent = descriptorValidation.signingBroadcastingPresent,
            uiPresent = descriptorValidation.uiPresent,
            endpointPresent = descriptorValidation.endpointPresent,
            mainnetPresent = descriptorValidation.mainnetPresent,
            productionProviderSelectable = descriptorValidation.productionProviderSelectable,
            disabledProviderOnly = descriptorValidation.disabledProviderOnly,
            canDeriveKdf = descriptorValidation.canDeriveKdf,
            canEncrypt = descriptorValidation.canEncrypt,
            canDecrypt = descriptorValidation.canDecrypt,
            canGenerateKeys = descriptorValidation.canGenerateKeys,
            canWrapKeys = descriptorValidation.canWrapKeys,
            canUnwrapKeys = descriptorValidation.canUnwrapKeys,
            canRunProviderOperations = descriptorValidation.canRunProviderOperations,
            canRunKat = descriptorValidation.canRunKat,
            canPersistVault = descriptorValidation.canPersistVault,
            canAccessSecureStorage = descriptorValidation.canAccessSecureStorage,
            canAccessSecureMetadata = descriptorValidation.canAccessSecureMetadata,
            canSelectInProduction = descriptorValidation.canSelectInProduction,
            canReachMainnet = descriptorValidation.canReachMainnet,
            implementationAuthorizationPresent = descriptorValidation.implementationAuthorizationPresent,
            productionAuthorizationPresent = descriptorValidation.productionAuthorizationPresent,
            providerSelectionAuthorizationPresent = descriptorValidation.providerSelectionAuthorizationPresent,
            providerOperationAuthorizationPresent = descriptorValidation.providerOperationAuthorizationPresent,
            cryptoAuthorizationPresent = descriptorValidation.cryptoAuthorizationPresent,
            katRunnerAuthorizationPresent = descriptorValidation.katRunnerAuthorizationPresent,
            katExecutorAuthorizationPresent = descriptorValidation.katExecutorAuthorizationPresent,
            providerKatExecutorAuthorizationPresent = descriptorValidation.providerKatExecutorAuthorizationPresent,
            vaultPersistenceAuthorizationPresent = descriptorValidation.vaultPersistenceAuthorizationPresent,
            syncAuthorizationPresent = descriptorValidation.syncAuthorizationPresent,
            signingBroadcastingAuthorizationPresent = descriptorValidation.signingBroadcastingAuthorizationPresent,
            uiAuthorizationPresent = descriptorValidation.uiAuthorizationPresent,
            endpointAuthorizationPresent = descriptorValidation.endpointAuthorizationPresent,
            mainnetAuthorizationPresent = descriptorValidation.mainnetAuthorizationPresent,
            buildHistoryExcludedFromNormalSourceMaterialCorpus =
                descriptorValidation.buildHistoryExcludedFromNormalSourceMaterialCorpus,
            localArtifactRootExcludedFromNormalSourceMaterialCorpus =
                descriptorValidation.localArtifactRootExcludedFromNormalSourceMaterialCorpus,
            docsReadmeUseDedicatedCorpus = descriptorValidation.docsReadmeUseDedicatedCorpus,
            evidenceCount = 60,
            suiteCheckCount = checks.size,
            blockerCount = blockers.size,
            warningCount = warnings.size,
            suiteChecks = checks,
            failureLabels = failures,
            blockers = blockers,
            warnings = warnings,
            displayLabel =
                SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportSafeLabel(
                    "inert provider identity descriptor suite report evidence only",
                ),
        )
    }
}
