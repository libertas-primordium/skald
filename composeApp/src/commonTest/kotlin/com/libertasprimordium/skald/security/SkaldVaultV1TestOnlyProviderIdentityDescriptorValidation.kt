package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationSafeLabel(val value: String) {
    override fun toString(): String = "RedactedTestOnlyProviderIdentityDescriptorValidationSafeLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationKind {
    InertTestOnlyProviderIdentityDescriptorValidation,
}

enum class SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationSourceSet {
    CommonTest,
}

enum class SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationCheck {
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
    DescriptorCommonTestOnly,
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

enum class SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationFailureLabel {
    TransitionGateEvidenceMissing,
    MarkerEvidenceMissing,
    MarkerValidationEvidenceMissing,
    MarkerSuiteReportEvidenceMissing,
    MarkerCompletionAuditEvidenceMissing,
    DescriptorEvidenceMissing,
    DescriptorNotCommonTestOnly,
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

enum class SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationBlocker {
    None,
}

enum class SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationWarning {
    None,
}

data class SkaldVaultV1TestOnlyProviderIdentityDescriptorValidation(
    val descriptorValidationId: SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationSafeLabel,
    val descriptorValidationVersion: Int,
    val descriptorValidationKind: SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationKind,
    val sourceSet: SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationSourceSet,
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
    val descriptorValidationPassed: Boolean,
    val descriptorValidationPassedIsCommonTestOnlyEvidence: Boolean,
    val descriptorCommonTestOnly: Boolean,
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
    val validationCheckCount: Int,
    val blockerCount: Int,
    val warningCount: Int,
    val validationChecks: List<SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationCheck>,
    val failureLabels: List<SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationFailureLabel>,
    val blockers: List<SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationBlocker>,
    val warnings: List<SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationWarning>,
    val displayLabel: SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationSafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityDescriptorValidation(" +
            "REDACTED, COMMON_TEST_ONLY, PROVIDER_IDENTITY_DESCRIPTOR_VALIDATION_ONLY, " +
            "PAYLOAD_FREE, ALL_EXECUTABLE_CAPABILITIES_FALSE, NOT_AUTHORIZATION, DISABLED_PROVIDER_ONLY" +
            ")"
}

object SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationPolicy {
    fun currentProviderIdentityDescriptorValidation():
        SkaldVaultV1TestOnlyProviderIdentityDescriptorValidation {
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
        val checks = SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationCheck.entries.toList()

        val descriptorPresent =
            descriptor.descriptorCreated &&
                descriptor.descriptorCheckCount == SkaldVaultV1TestOnlyProviderIdentityDescriptorCheck.entries.size &&
                descriptor.sourceSet == SkaldVaultV1TestOnlyProviderIdentityDescriptorSourceSet.CommonTest
        val descriptorCapabilityManifestPresent =
            descriptor.providerCapabilitiesDeclared &&
                descriptor.capabilityManifest.providerCapabilitiesDeclared &&
                descriptor.capabilityManifest.capabilityCount ==
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorCapability.entries.size
        val descriptorExecutableCapabilitiesAllFalse =
            !descriptor.providerExecutableCapabilitiesPresent &&
                !descriptor.capabilityManifest.providerExecutableCapabilitiesPresent &&
                !descriptor.canDeriveKdf &&
                !descriptor.canEncrypt &&
                !descriptor.canDecrypt &&
                !descriptor.canGenerateKeys &&
                !descriptor.canWrapKeys &&
                !descriptor.canUnwrapKeys &&
                !descriptor.canRunProviderOperations &&
                !descriptor.canRunKat &&
                !descriptor.canPersistVault &&
                !descriptor.canAccessSecureStorage &&
                !descriptor.canAccessSecureMetadata &&
                !descriptor.canSelectInProduction &&
                !descriptor.canReachMainnet &&
                !descriptor.capabilityManifest.canDeriveKdf &&
                !descriptor.capabilityManifest.canEncrypt &&
                !descriptor.capabilityManifest.canDecrypt &&
                !descriptor.capabilityManifest.canGenerateKeys &&
                !descriptor.capabilityManifest.canWrapKeys &&
                !descriptor.capabilityManifest.canUnwrapKeys &&
                !descriptor.capabilityManifest.canRunProviderOperations &&
                !descriptor.capabilityManifest.canRunKat &&
                !descriptor.capabilityManifest.canPersistVault &&
                !descriptor.capabilityManifest.canAccessSecureStorage &&
                !descriptor.capabilityManifest.canAccessSecureMetadata &&
                !descriptor.capabilityManifest.canSelectInProduction &&
                !descriptor.capabilityManifest.canReachMainnet
        val markerIdentityLabelMatchesExpected =
            descriptor.providerIdentityLabel.value == marker.safeId.value &&
                marker.markerId.value == marker.safeId.value &&
                marker.syntheticIdentityLabel.value == marker.safeId.value &&
                descriptor.namespaceLabel.value == "skald-test-only-provider-identity-v1"
        val markerIdentityLabelSafe =
            descriptor.descriptorSafeLabelOnly &&
                markerIdentityLabelMatchesExpected &&
                marker.testOnlyNamespaceConformant &&
                marker.deterministicSafeIdentity

        val markerCreatedIsInertMarkerEvidenceOnly =
            descriptor.markerCreatedIsInertMarkerEvidenceOnly &&
                markerCompletionAudit.markerCreatedIsInertMarkerEvidenceOnly &&
                markerSuite.markerCreatedIsInertMarkerEvidenceOnly &&
                markerValidation.markerCreatedIsInertMarkerEvidenceOnly &&
                marker.markerCreated &&
                marker.inertMarkerOnly &&
                !marker.implementationAuthorizationPresent
        val implementationMarkerPresentIsInertMarkerEvidenceOnly =
            descriptor.implementationMarkerPresentIsInertMarkerEvidenceOnly &&
                markerCompletionAudit.implementationMarkerPresentIsInertMarkerEvidenceOnly &&
                markerSuite.implementationMarkerPresentIsInertMarkerEvidenceOnly &&
                markerValidation.implementationMarkerPresentIsInertMarkerEvidenceOnly &&
                marker.implementationMarkerPresent &&
                marker.inertMarkerOnly &&
                !marker.implementationAuthorizationPresent
        val markerValidationPassedIsValidationEvidenceOnly =
            descriptor.markerValidationPassedIsValidationEvidenceOnly &&
                markerCompletionAudit.markerValidationPassedIsValidationEvidenceOnly &&
                markerSuite.markerValidationPassedIsValidationEvidenceOnly &&
                markerValidation.markerValidationIsCommonTestOnlyEvidence &&
                markerValidation.markerValidationPassed &&
                !markerValidation.implementationAuthorizationPresent
        val markerSuiteReportPassedIsSuiteReportEvidenceOnly =
            descriptor.markerSuiteReportPassedIsSuiteReportEvidenceOnly &&
                markerCompletionAudit.markerSuiteReportPassedIsSuiteReportEvidenceOnly &&
                markerSuite.markerSuiteReportIsCommonTestOnlyEvidence &&
                markerSuite.markerSuiteReportPassed &&
                !markerSuite.implementationAuthorizationPresent
        val markerCompletionAuditPassedIsCompletionAuditEvidenceOnly =
            descriptor.markerCompletionAuditPassedIsCompletionAuditEvidenceOnly &&
                markerCompletionAudit.markerCompletionAuditIsCommonTestOnlyEvidence &&
                markerCompletionAudit.markerCompletionAuditPassed &&
                !markerCompletionAudit.implementationAuthorizationPresent

        val forbiddenRuntimeSurfacePresent =
            descriptor.providerImplementationPresent ||
                descriptor.productionProviderIdentityPresent ||
                descriptor.providerRegistryEntryPresent ||
                descriptor.providerFactoryPresent ||
                descriptor.providerDispatcherPresent ||
                descriptor.executorTargetPresent ||
                descriptor.providerHandlePresent ||
                descriptor.containsVaultCryptoProvider ||
                descriptor.implementsVaultCryptoProvider ||
                descriptor.vaultCryptoProviderInstanceExposed ||
                descriptor.providerOperationExecuted ||
                descriptor.cryptoExecuted ||
                descriptor.katRunnerPresent ||
                descriptor.katExecutorPresent ||
                descriptor.providerKatExecutorPresent ||
                descriptor.tracePayloadPresent ||
                descriptor.rawKatMaterialPresent ||
                descriptor.publicVectorBytesPresent ||
                descriptor.publicVectorHexPresent ||
                descriptor.vaultLifecyclePresent ||
                descriptor.vaultPersistencePresent ||
                descriptor.secureSecretStorageSuccessPresent ||
                descriptor.secureMetadataStorageSuccessPresent ||
                descriptor.productionSyncPresent ||
                descriptor.signingBroadcastingPresent ||
                descriptor.uiPresent ||
                descriptor.endpointPresent ||
                descriptor.mainnetPresent
        val forbiddenAuthorizationPresent =
            descriptor.implementationAuthorizationPresent ||
                descriptor.productionAuthorizationPresent ||
                descriptor.providerSelectionAuthorizationPresent ||
                descriptor.providerOperationAuthorizationPresent ||
                descriptor.cryptoAuthorizationPresent ||
                descriptor.katRunnerAuthorizationPresent ||
                descriptor.katExecutorAuthorizationPresent ||
                descriptor.providerKatExecutorAuthorizationPresent ||
                descriptor.vaultPersistenceAuthorizationPresent ||
                descriptor.syncAuthorizationPresent ||
                descriptor.signingBroadcastingAuthorizationPresent ||
                descriptor.uiAuthorizationPresent ||
                descriptor.endpointAuthorizationPresent ||
                descriptor.mainnetAuthorizationPresent
        val descriptorCreatedIsDescriptorEvidenceOnly =
            descriptor.descriptorCreated &&
                descriptor.descriptorCreatedIsCommonTestOnlyEvidence &&
                descriptor.descriptorCommonTestOnly &&
                !forbiddenAuthorizationPresent
        val providerCapabilitiesDeclaredIsDescriptorEvidenceOnly =
            descriptor.providerCapabilitiesDeclared &&
                descriptorCapabilityManifestPresent &&
                descriptorExecutableCapabilitiesAllFalse &&
                !forbiddenRuntimeSurfacePresent &&
                !forbiddenAuthorizationPresent
        val userApprovalScopedToInertProviderIdentityChain =
            descriptor.userApprovalScopedToInertProviderIdentityChain &&
                markerCompletionAudit.userApprovalScopedToInertMarkerChain &&
                marker.userApprovedInertMarkerPass &&
                !transitionGate.implementationAuthorized

        val failures = buildList {
            if (!descriptor.transitionGatePresent || !descriptor.transitionGateHumanReviewReady) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationFailureLabel
                        .TransitionGateEvidenceMissing,
                )
            }
            if (!descriptor.markerPresent || !descriptor.markerCreated || !descriptor.implementationMarkerPresent) {
                add(SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationFailureLabel.MarkerEvidenceMissing)
            }
            if (!descriptor.markerValidationPresent || !descriptor.markerValidationPassed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationFailureLabel
                        .MarkerValidationEvidenceMissing,
                )
            }
            if (!descriptor.markerSuiteReportPresent || !descriptor.markerSuiteReportPassed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationFailureLabel
                        .MarkerSuiteReportEvidenceMissing,
                )
            }
            if (!descriptor.markerCompletionAuditPresent || !descriptor.markerCompletionAuditPassed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationFailureLabel
                        .MarkerCompletionAuditEvidenceMissing,
                )
            }
            if (!descriptorPresent || !descriptorCreatedIsDescriptorEvidenceOnly) {
                add(SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationFailureLabel.DescriptorEvidenceMissing)
            }
            if (!descriptor.descriptorCommonTestOnly) {
                add(SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationFailureLabel.DescriptorNotCommonTestOnly)
            }
            if (!descriptor.descriptorInert) {
                add(SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationFailureLabel.DescriptorNotInert)
            }
            if (!descriptor.descriptorPayloadFree) {
                add(SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationFailureLabel.DescriptorPayloadPresent)
            }
            if (!descriptor.descriptorSafeLabelOnly || !markerIdentityLabelSafe) {
                add(SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationFailureLabel.DescriptorUnsafeLabel)
            }
            if (!descriptorCapabilityManifestPresent) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationFailureLabel
                        .DescriptorCapabilityManifestMissing,
                )
            }
            if (!descriptorExecutableCapabilitiesAllFalse) {
                add(SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationFailureLabel.ExecutableCapabilityPresent)
            }
            if (forbiddenRuntimeSurfacePresent) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationFailureLabel
                        .ForbiddenRuntimeSurfacePresent,
                )
            }
            if (forbiddenAuthorizationPresent) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationFailureLabel
                        .ForbiddenAuthorizationPresent,
                )
            }
            if (descriptor.productionProviderSelectable) {
                add(SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationFailureLabel.ProductionProviderSelectable)
            }
            if (
                !descriptor.buildHistoryExcludedFromNormalSourceMaterialCorpus ||
                !descriptor.localArtifactRootExcludedFromNormalSourceMaterialCorpus ||
                !descriptor.docsReadmeUseDedicatedCorpus
            ) {
                add(SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationFailureLabel.CorpusBoundaryMissing)
            }
        }

        val descriptorValidationPassed = failures.isEmpty()
        val descriptorValidationPassedIsCommonTestOnlyEvidence =
            descriptorValidationPassed &&
                !forbiddenAuthorizationPresent &&
                !descriptor.productionProviderSelectable
        val blockers =
            if (descriptorValidationPassed) {
                emptyList()
            } else {
                listOf(SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationBlocker.None)
            }
        val warnings = emptyList<SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationWarning>()

        return SkaldVaultV1TestOnlyProviderIdentityDescriptorValidation(
            descriptorValidationId =
                SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationSafeLabel(
                    "test-only-provider-identity-descriptor-validation-common-test-only",
                ),
            descriptorValidationVersion = 1,
            descriptorValidationKind =
                SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationKind
                    .InertTestOnlyProviderIdentityDescriptorValidation,
            sourceSet = SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationSourceSet.CommonTest,
            transitionGatePresent = descriptor.transitionGatePresent,
            transitionGateHumanReviewReady = descriptor.transitionGateHumanReviewReady,
            markerPresent = descriptor.markerPresent,
            markerCreated = descriptor.markerCreated,
            implementationMarkerPresent = descriptor.implementationMarkerPresent,
            markerValidationPresent = descriptor.markerValidationPresent,
            markerValidationPassed = descriptor.markerValidationPassed,
            markerSuiteReportPresent = descriptor.markerSuiteReportPresent,
            markerSuiteReportPassed = descriptor.markerSuiteReportPassed,
            markerCompletionAuditPresent = descriptor.markerCompletionAuditPresent,
            markerCompletionAuditPassed = descriptor.markerCompletionAuditPassed,
            descriptorPresent = descriptorPresent,
            descriptorCreated = descriptor.descriptorCreated,
            providerCapabilitiesDeclared = descriptor.providerCapabilitiesDeclared,
            descriptorValidationPassed = descriptorValidationPassed,
            descriptorValidationPassedIsCommonTestOnlyEvidence =
                descriptorValidationPassedIsCommonTestOnlyEvidence,
            descriptorCommonTestOnly = descriptor.descriptorCommonTestOnly,
            descriptorInert = descriptor.descriptorInert,
            descriptorPayloadFree = descriptor.descriptorPayloadFree,
            descriptorSafeLabelOnly = descriptor.descriptorSafeLabelOnly,
            descriptorDeterministic = descriptor.descriptorDeterministic,
            descriptorCapabilityManifestPresent = descriptorCapabilityManifestPresent,
            descriptorExecutableCapabilitiesAllFalse = descriptorExecutableCapabilitiesAllFalse,
            markerIdentityLabelMatchesExpected = markerIdentityLabelMatchesExpected,
            markerIdentityLabelSafe = markerIdentityLabelSafe,
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
            userApprovalScopedToInertProviderIdentityChain =
                userApprovalScopedToInertProviderIdentityChain,
            providerExecutableCapabilitiesPresent = descriptor.providerExecutableCapabilitiesPresent,
            providerImplementationPresent = descriptor.providerImplementationPresent,
            productionProviderIdentityPresent = descriptor.productionProviderIdentityPresent,
            providerRegistryEntryPresent = descriptor.providerRegistryEntryPresent,
            providerFactoryPresent = descriptor.providerFactoryPresent,
            providerDispatcherPresent = descriptor.providerDispatcherPresent,
            executorTargetPresent = descriptor.executorTargetPresent,
            providerHandlePresent = descriptor.providerHandlePresent,
            containsVaultCryptoProvider = descriptor.containsVaultCryptoProvider,
            implementsVaultCryptoProvider = descriptor.implementsVaultCryptoProvider,
            vaultCryptoProviderInstanceExposed = descriptor.vaultCryptoProviderInstanceExposed,
            providerOperationExecuted = descriptor.providerOperationExecuted,
            cryptoExecuted = descriptor.cryptoExecuted,
            katRunnerPresent = descriptor.katRunnerPresent,
            katExecutorPresent = descriptor.katExecutorPresent,
            providerKatExecutorPresent = descriptor.providerKatExecutorPresent,
            tracePayloadPresent = descriptor.tracePayloadPresent,
            rawKatMaterialPresent = descriptor.rawKatMaterialPresent,
            publicVectorBytesPresent = descriptor.publicVectorBytesPresent,
            publicVectorHexPresent = descriptor.publicVectorHexPresent,
            vaultLifecyclePresent = descriptor.vaultLifecyclePresent,
            vaultPersistencePresent = descriptor.vaultPersistencePresent,
            secureSecretStorageSuccessPresent = descriptor.secureSecretStorageSuccessPresent,
            secureMetadataStorageSuccessPresent = descriptor.secureMetadataStorageSuccessPresent,
            productionSyncPresent = descriptor.productionSyncPresent,
            signingBroadcastingPresent = descriptor.signingBroadcastingPresent,
            uiPresent = descriptor.uiPresent,
            endpointPresent = descriptor.endpointPresent,
            mainnetPresent = descriptor.mainnetPresent,
            productionProviderSelectable = descriptor.productionProviderSelectable,
            disabledProviderOnly = descriptor.disabledProviderOnly,
            canDeriveKdf = descriptor.canDeriveKdf,
            canEncrypt = descriptor.canEncrypt,
            canDecrypt = descriptor.canDecrypt,
            canGenerateKeys = descriptor.canGenerateKeys,
            canWrapKeys = descriptor.canWrapKeys,
            canUnwrapKeys = descriptor.canUnwrapKeys,
            canRunProviderOperations = descriptor.canRunProviderOperations,
            canRunKat = descriptor.canRunKat,
            canPersistVault = descriptor.canPersistVault,
            canAccessSecureStorage = descriptor.canAccessSecureStorage,
            canAccessSecureMetadata = descriptor.canAccessSecureMetadata,
            canSelectInProduction = descriptor.canSelectInProduction,
            canReachMainnet = descriptor.canReachMainnet,
            implementationAuthorizationPresent = descriptor.implementationAuthorizationPresent,
            productionAuthorizationPresent = descriptor.productionAuthorizationPresent,
            providerSelectionAuthorizationPresent = descriptor.providerSelectionAuthorizationPresent,
            providerOperationAuthorizationPresent = descriptor.providerOperationAuthorizationPresent,
            cryptoAuthorizationPresent = descriptor.cryptoAuthorizationPresent,
            katRunnerAuthorizationPresent = descriptor.katRunnerAuthorizationPresent,
            katExecutorAuthorizationPresent = descriptor.katExecutorAuthorizationPresent,
            providerKatExecutorAuthorizationPresent = descriptor.providerKatExecutorAuthorizationPresent,
            vaultPersistenceAuthorizationPresent = descriptor.vaultPersistenceAuthorizationPresent,
            syncAuthorizationPresent = descriptor.syncAuthorizationPresent,
            signingBroadcastingAuthorizationPresent = descriptor.signingBroadcastingAuthorizationPresent,
            uiAuthorizationPresent = descriptor.uiAuthorizationPresent,
            endpointAuthorizationPresent = descriptor.endpointAuthorizationPresent,
            mainnetAuthorizationPresent = descriptor.mainnetAuthorizationPresent,
            buildHistoryExcludedFromNormalSourceMaterialCorpus =
                descriptor.buildHistoryExcludedFromNormalSourceMaterialCorpus,
            localArtifactRootExcludedFromNormalSourceMaterialCorpus =
                descriptor.localArtifactRootExcludedFromNormalSourceMaterialCorpus,
            docsReadmeUseDedicatedCorpus = descriptor.docsReadmeUseDedicatedCorpus,
            evidenceCount = 52,
            validationCheckCount = checks.size,
            blockerCount = blockers.size,
            warningCount = warnings.size,
            validationChecks = checks,
            failureLabels = failures,
            blockers = blockers,
            warnings = warnings,
            displayLabel =
                SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationSafeLabel(
                    "inert provider identity descriptor validation evidence only",
                ),
        )
    }
}
