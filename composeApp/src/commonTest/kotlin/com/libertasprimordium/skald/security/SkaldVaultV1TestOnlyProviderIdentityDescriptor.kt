package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityDescriptorSafeLabel(val value: String) {
    override fun toString(): String = "RedactedTestOnlyProviderIdentitySafeLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityDescriptorKind {
    InertTestOnlyProviderIdentityDescriptor,
}

enum class SkaldVaultV1TestOnlyProviderIdentityDescriptorSourceSet {
    CommonTest,
}

enum class SkaldVaultV1TestOnlyProviderIdentityDescriptorCapability {
    DeriveKdf,
    Encrypt,
    Decrypt,
    GenerateKeys,
    WrapKeys,
    UnwrapKeys,
    RunProviderOperations,
    RunKat,
    PersistVault,
    AccessSecureStorage,
    AccessSecureMetadata,
    SelectInProduction,
    ReachMainnet,
}

enum class SkaldVaultV1TestOnlyProviderIdentityDescriptorCheck {
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
    MarkerCreatedEvidenceOnly,
    ImplementationMarkerPresentEvidenceOnly,
    MarkerValidationPassedEvidenceOnly,
    MarkerSuiteReportPassedEvidenceOnly,
    MarkerCompletionAuditPassedEvidenceOnly,
    DescriptorCreated,
    DescriptorCommonTestOnly,
    DescriptorInert,
    DescriptorSafeLabelOnly,
    DescriptorDeterministic,
    DescriptorPayloadFree,
    ProviderCapabilitiesDeclared,
    ExecutableProviderCapabilitiesAbsent,
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

enum class SkaldVaultV1TestOnlyProviderIdentityDescriptorFailureLabel {
    TransitionGateEvidenceMissing,
    MarkerEvidenceMissing,
    MarkerValidationEvidenceMissing,
    MarkerSuiteReportEvidenceMissing,
    MarkerCompletionAuditEvidenceMissing,
    DescriptorNotCommonTestOnly,
    DescriptorNotInert,
    DescriptorUnsafeLabel,
    DescriptorPayloadPresent,
    ExecutableCapabilityPresent,
    ForbiddenRuntimeSurfacePresent,
    ForbiddenAuthorizationPresent,
    ProductionProviderSelectable,
    CorpusBoundaryMissing,
}

enum class SkaldVaultV1TestOnlyProviderIdentityDescriptorBlocker {
    None,
}

enum class SkaldVaultV1TestOnlyProviderIdentityDescriptorWarning {
    None,
}

data class SkaldVaultV1TestOnlyProviderIdentityDescriptorCapabilityManifest(
    val manifestLabel: SkaldVaultV1TestOnlyProviderIdentityDescriptorSafeLabel,
    val providerCapabilitiesDeclared: Boolean,
    val providerExecutableCapabilitiesPresent: Boolean,
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
    val capabilityCount: Int,
    val capabilities: List<SkaldVaultV1TestOnlyProviderIdentityDescriptorCapability>,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityCapabilityManifest(" +
            "REDACTED, PAYLOAD_FREE, ALL_EXECUTABLE_CAPABILITIES_FALSE, NOT_AUTHORIZATION" +
            ")"
}

data class SkaldVaultV1TestOnlyProviderIdentityDescriptor(
    val descriptorId: SkaldVaultV1TestOnlyProviderIdentityDescriptorSafeLabel,
    val descriptorVersion: Int,
    val descriptorKind: SkaldVaultV1TestOnlyProviderIdentityDescriptorKind,
    val sourceSet: SkaldVaultV1TestOnlyProviderIdentityDescriptorSourceSet,
    val namespaceLabel: SkaldVaultV1TestOnlyProviderIdentityDescriptorSafeLabel,
    val providerIdentityLabel: SkaldVaultV1TestOnlyProviderIdentityDescriptorSafeLabel,
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
    val descriptorCreated: Boolean,
    val descriptorCreatedIsCommonTestOnlyEvidence: Boolean,
    val descriptorInert: Boolean,
    val descriptorCommonTestOnly: Boolean,
    val descriptorSafeLabelOnly: Boolean,
    val descriptorDeterministic: Boolean,
    val descriptorPayloadFree: Boolean,
    val markerCreatedIsInertMarkerEvidenceOnly: Boolean,
    val implementationMarkerPresentIsInertMarkerEvidenceOnly: Boolean,
    val markerValidationPassedIsValidationEvidenceOnly: Boolean,
    val markerSuiteReportPassedIsSuiteReportEvidenceOnly: Boolean,
    val markerCompletionAuditPassedIsCompletionAuditEvidenceOnly: Boolean,
    val userApprovalScopedToInertProviderIdentityChain: Boolean,
    val providerCapabilitiesDeclared: Boolean,
    val providerExecutableCapabilitiesPresent: Boolean,
    val capabilityManifest: SkaldVaultV1TestOnlyProviderIdentityDescriptorCapabilityManifest,
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
    val descriptorCheckCount: Int,
    val blockerCount: Int,
    val warningCount: Int,
    val descriptorChecks: List<SkaldVaultV1TestOnlyProviderIdentityDescriptorCheck>,
    val failureLabels: List<SkaldVaultV1TestOnlyProviderIdentityDescriptorFailureLabel>,
    val blockers: List<SkaldVaultV1TestOnlyProviderIdentityDescriptorBlocker>,
    val warnings: List<SkaldVaultV1TestOnlyProviderIdentityDescriptorWarning>,
    val displayLabel: SkaldVaultV1TestOnlyProviderIdentityDescriptorSafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentitySafeReport(" +
            "REDACTED, COMMON_TEST_ONLY, PROVIDER_IDENTITY_DESCRIPTOR_ONLY, PAYLOAD_FREE, " +
            "NOT_AUTHORIZATION, DISABLED_PROVIDER_ONLY" +
            ")"
}

object SkaldVaultV1TestOnlyProviderIdentityDescriptorPolicy {
    private const val expectedNamespaceLabel = "skald-test-only-provider-identity-v1"
    private const val expectedSafeIdentityLabel =
        "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"

    fun currentProviderIdentityDescriptor(): SkaldVaultV1TestOnlyProviderIdentityDescriptor {
        val transitionGate =
            SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGatePolicy
                .currentProviderIdentityImplementationTransitionGate()
        val marker = SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy.currentImplementationMarker()
        val validation =
            SkaldVaultV1TestOnlyProviderIdentityMarkerValidationPolicy.currentProviderIdentityMarkerValidation()
        val suite =
            SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportPolicy.currentProviderIdentityMarkerSuiteReport()
        val completionAudit =
            SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditPolicy
                .currentProviderIdentityMarkerCompletionAudit()
        val checks = SkaldVaultV1TestOnlyProviderIdentityDescriptorCheck.entries.toList()

        val transitionGatePresent =
            completionAudit.transitionGatePresent &&
                suite.transitionGatePresent &&
                validation.transitionGatePresent &&
                marker.transitionGateReviewed &&
                transitionGate.reviewReadyForHumanDecision
        val transitionGateHumanReviewReady =
            completionAudit.transitionGateHumanReviewReady &&
                suite.transitionGateHumanReviewReady &&
                validation.transitionGateHumanReviewReady &&
                marker.transitionGateHumanReviewReady &&
                transitionGate.reviewReadyForHumanDecision &&
                transitionGate.reviewReadyIsHumanDecisionOnly
        val markerPresent =
            completionAudit.markerPresent &&
                suite.markerPresent &&
                validation.markerPresent &&
                marker.markerCreated &&
                marker.implementationMarkerPresent &&
                marker.markerId.value == marker.safeId.value
        val markerValidationPresent =
            completionAudit.markerValidationPresent &&
                suite.markerValidationPresent &&
                validation.markerValidationPassed &&
                validation.validationCheckCount == SkaldVaultV1TestOnlyProviderIdentityMarkerValidationCheck.entries.size
        val markerSuiteReportPresent =
            completionAudit.markerSuiteReportPresent &&
                suite.markerSuiteReportPassed &&
                suite.suiteCheckCount == SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportCheck.entries.size
        val markerCompletionAuditPresent =
            completionAudit.markerCompletionAuditPassed &&
                completionAudit.markerChainComplete &&
                completionAudit.completionCheckCount ==
                    SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditCheck.entries.size &&
                completionAudit.sourceSet ==
                    SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditSourceSet.CommonTest

        val markerCreatedIsInertMarkerEvidenceOnly =
            completionAudit.markerCreatedIsInertMarkerEvidenceOnly &&
                suite.markerCreatedIsInertMarkerEvidenceOnly &&
                validation.markerCreatedIsInertMarkerEvidenceOnly &&
                marker.markerCreated &&
                marker.inertMarkerOnly &&
                !marker.implementationAuthorizationPresent
        val implementationMarkerPresentIsInertMarkerEvidenceOnly =
            completionAudit.implementationMarkerPresentIsInertMarkerEvidenceOnly &&
                suite.implementationMarkerPresentIsInertMarkerEvidenceOnly &&
                validation.implementationMarkerPresentIsInertMarkerEvidenceOnly &&
                marker.implementationMarkerPresent &&
                marker.inertMarkerOnly &&
                !marker.implementationAuthorizationPresent
        val markerValidationPassedIsValidationEvidenceOnly =
            completionAudit.markerValidationPassedIsValidationEvidenceOnly &&
                suite.markerValidationPassedIsValidationEvidenceOnly &&
                validation.markerValidationIsCommonTestOnlyEvidence &&
                validation.markerValidationPassed &&
                !validation.implementationAuthorizationPresent
        val markerSuiteReportPassedIsSuiteReportEvidenceOnly =
            completionAudit.markerSuiteReportPassedIsSuiteReportEvidenceOnly &&
                suite.markerSuiteReportPassed &&
                suite.markerSuiteReportIsCommonTestOnlyEvidence &&
                !suite.implementationAuthorizationPresent
        val markerCompletionAuditPassedIsCompletionAuditEvidenceOnly =
            completionAudit.markerCompletionAuditPassed &&
                completionAudit.markerCompletionAuditIsCommonTestOnlyEvidence &&
                !completionAudit.implementationAuthorizationPresent

        val descriptorCommonTestOnly =
            completionAudit.sourceSet ==
                SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditSourceSet.CommonTest &&
                suite.sourceSet == SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportSourceSet.CommonTest &&
                validation.sourceSet == SkaldVaultV1TestOnlyProviderIdentityMarkerValidationSourceSet.CommonTest &&
                marker.sourceSet == SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet.CommonTest &&
                marker.commonTestOnly
        val markerIdentityLabelMatchesExpected =
            completionAudit.markerIdentityLabelMatchesExpected &&
                suite.markerIdentityLabelMatchesExpected &&
                validation.markerIdentityLabelMatchesExpected &&
                marker.markerId.value == expectedSafeIdentityLabel &&
                marker.safeId.value == expectedSafeIdentityLabel &&
                marker.syntheticIdentityLabel.value == expectedSafeIdentityLabel
        val markerIdentityLabelSafe =
            completionAudit.markerIdentityLabelSafe &&
                suite.markerIdentityLabelSafe &&
                validation.markerIdentityLabelSafe &&
                markerIdentityLabelMatchesExpected &&
                marker.testOnlyNamespaceConformant
        val descriptorSafeLabelOnly =
            markerIdentityLabelSafe &&
                expectedNamespaceLabel == "skald-test-only-provider-identity-v1" &&
                !marker.toString().contains(expectedSafeIdentityLabel) &&
                !validation.toString().contains(expectedSafeIdentityLabel) &&
                !suite.toString().contains(expectedSafeIdentityLabel) &&
                !completionAudit.toString().contains(expectedSafeIdentityLabel)
        val descriptorInert =
            completionAudit.markerInert &&
                marker.inertMarkerOnly &&
                !marker.providerOperationExecuted &&
                !marker.cryptoExecuted
        val descriptorDeterministic =
            completionAudit.markerDeterministic &&
                validation.markerDeterministic &&
                marker.deterministicSafeIdentity
        val descriptorPayloadFree =
            !completionAudit.tracePayloadPresent &&
                !completionAudit.rawKatMaterialPresent &&
                !completionAudit.publicVectorBytesPresent &&
                !completionAudit.publicVectorHexPresent

        val providerCapabilitiesDeclared = true
        val canDeriveKdf = false
        val canEncrypt = false
        val canDecrypt = false
        val canGenerateKeys = false
        val canWrapKeys = false
        val canUnwrapKeys = false
        val canRunProviderOperations = false
        val canRunKat = false
        val canPersistVault = false
        val canAccessSecureStorage = false
        val canAccessSecureMetadata = false
        val canSelectInProduction = false
        val canReachMainnet = false
        val providerExecutableCapabilitiesPresent =
            canDeriveKdf ||
                canEncrypt ||
                canDecrypt ||
                canGenerateKeys ||
                canWrapKeys ||
                canUnwrapKeys ||
                canRunProviderOperations ||
                canRunKat ||
                canPersistVault ||
                canAccessSecureStorage ||
                canAccessSecureMetadata ||
                canSelectInProduction ||
                canReachMainnet
        val capabilities = SkaldVaultV1TestOnlyProviderIdentityDescriptorCapability.entries.toList()
        val capabilityManifest =
            SkaldVaultV1TestOnlyProviderIdentityDescriptorCapabilityManifest(
                manifestLabel =
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorSafeLabel(
                        "test-only-provider-identity-inert-capability-manifest",
                    ),
                providerCapabilitiesDeclared = providerCapabilitiesDeclared,
                providerExecutableCapabilitiesPresent = providerExecutableCapabilitiesPresent,
                canDeriveKdf = canDeriveKdf,
                canEncrypt = canEncrypt,
                canDecrypt = canDecrypt,
                canGenerateKeys = canGenerateKeys,
                canWrapKeys = canWrapKeys,
                canUnwrapKeys = canUnwrapKeys,
                canRunProviderOperations = canRunProviderOperations,
                canRunKat = canRunKat,
                canPersistVault = canPersistVault,
                canAccessSecureStorage = canAccessSecureStorage,
                canAccessSecureMetadata = canAccessSecureMetadata,
                canSelectInProduction = canSelectInProduction,
                canReachMainnet = canReachMainnet,
                capabilityCount = capabilities.size,
                capabilities = capabilities,
            )

        val providerImplementationPresent =
            completionAudit.providerImplementationPresent ||
                suite.providerImplementationPresent ||
                validation.providerImplementationPresent ||
                marker.providerImplementationPresent ||
                transitionGate.providerImplementationPresent
        val productionProviderIdentityPresent =
            completionAudit.productionProviderIdentityPresent ||
                suite.productionProviderIdentityPresent ||
                validation.productionProviderIdentityPresent ||
                marker.productionProviderIdentityPresent
        val providerRegistryEntryPresent =
            completionAudit.providerRegistryEntryPresent ||
                suite.providerRegistryEntryPresent ||
                validation.providerRegistryEntryPresent ||
                marker.providerRegistryEntryPresent ||
                transitionGate.providerRegistryEntryPresent
        val providerFactoryPresent =
            completionAudit.providerFactoryPresent ||
                suite.providerFactoryPresent ||
                validation.providerFactoryPresent ||
                marker.providerFactoryPresent ||
                transitionGate.providerFactoryPresent
        val providerDispatcherPresent =
            completionAudit.providerDispatcherPresent ||
                suite.providerDispatcherPresent ||
                validation.providerDispatcherPresent ||
                marker.providerDispatcherPresent ||
                transitionGate.providerDispatcherPresent
        val executorTargetPresent =
            completionAudit.executorTargetPresent ||
                suite.executorTargetPresent ||
                validation.executorTargetPresent ||
                marker.executorTargetPresent ||
                transitionGate.executorTargetPresent
        val providerHandlePresent =
            completionAudit.providerHandlePresent ||
                suite.providerHandlePresent ||
                validation.providerHandlePresent ||
                marker.runtimeSelectable ||
                marker.registrySelectable ||
                marker.factoryReachable ||
                marker.dispatcherReachable
        val containsVaultCryptoProvider =
            completionAudit.containsVaultCryptoProvider ||
                suite.containsVaultCryptoProvider ||
                validation.containsVaultCryptoProvider ||
                marker.vaultCryptoProviderInstanceExposed
        val implementsVaultCryptoProvider =
            completionAudit.implementsVaultCryptoProvider ||
                suite.implementsVaultCryptoProvider ||
                validation.implementsVaultCryptoProvider ||
                marker.implementsVaultCryptoProvider
        val vaultCryptoProviderInstanceExposed =
            marker.vaultCryptoProviderInstanceExposed ||
                containsVaultCryptoProvider
        val providerOperationExecuted =
            completionAudit.providerOperationExecuted ||
                suite.providerOperationExecuted ||
                validation.providerOperationExecuted ||
                marker.providerOperationExecuted ||
                transitionGate.providerOperationExecuted
        val cryptoExecuted =
            completionAudit.cryptoExecuted ||
                suite.cryptoExecuted ||
                validation.cryptoExecuted ||
                marker.cryptoExecuted ||
                transitionGate.cryptoExecuted
        val katRunnerPresent =
            completionAudit.katRunnerPresent ||
                suite.katRunnerPresent ||
                validation.katRunnerPresent ||
                marker.katRunnerPresent ||
                transitionGate.katRunnerPresent
        val katExecutorPresent =
            completionAudit.katExecutorPresent ||
                suite.katExecutorPresent ||
                validation.katExecutorPresent ||
                marker.katExecutorPresent ||
                transitionGate.katExecutorPresent
        val providerKatExecutorPresent =
            completionAudit.providerKatExecutorPresent ||
                suite.providerKatExecutorPresent ||
                validation.providerKatExecutorPresent ||
                marker.providerKatExecutorReachable ||
                transitionGate.providerKatExecutorPresent
        val tracePayloadPresent =
            completionAudit.tracePayloadPresent ||
                suite.tracePayloadPresent ||
                validation.tracePayloadPresent ||
                marker.tracePayloadPresent ||
                transitionGate.tracePayloadPresent
        val rawKatMaterialPresent =
            completionAudit.rawKatMaterialPresent ||
                suite.rawKatMaterialPresent ||
                validation.rawKatMaterialPresent ||
                marker.rawKatMaterialPresent ||
                transitionGate.rawKatMaterialPresent
        val publicVectorBytesPresent =
            completionAudit.publicVectorBytesPresent ||
                suite.publicVectorBytesPresent ||
                validation.publicVectorBytesPresent ||
                marker.publicVectorBytesPresent ||
                transitionGate.publicVectorBytesPresent
        val publicVectorHexPresent =
            completionAudit.publicVectorHexPresent ||
                suite.publicVectorHexPresent ||
                validation.publicVectorHexPresent ||
                marker.publicVectorHexPresent ||
                transitionGate.publicVectorHexPresent
        val vaultLifecyclePresent =
            completionAudit.vaultLifecyclePresent ||
                suite.vaultLifecyclePresent ||
                validation.vaultLifecyclePresent ||
                marker.canUseForVaultLifecycle ||
                transitionGate.vaultLifecyclePresent
        val vaultPersistencePresent =
            completionAudit.vaultPersistencePresent ||
                suite.vaultPersistencePresent ||
                validation.vaultPersistencePresent ||
                marker.vaultPersistencePresent ||
                transitionGate.vaultPersistencePresent
        val secureSecretStorageSuccessPresent =
            completionAudit.secureSecretStorageSuccessPresent ||
                suite.secureSecretStorageSuccessPresent ||
                validation.secureSecretStorageSuccessPresent
        val secureMetadataStorageSuccessPresent =
            completionAudit.secureMetadataStorageSuccessPresent ||
                suite.secureMetadataStorageSuccessPresent ||
                validation.secureMetadataStorageSuccessPresent
        val productionSyncPresent =
            completionAudit.productionSyncPresent ||
                suite.productionSyncPresent ||
                validation.productionSyncPresent ||
                marker.productionSyncPresent ||
                transitionGate.productionSyncPresent
        val signingBroadcastingPresent =
            completionAudit.signingBroadcastingPresent ||
                suite.signingBroadcastingPresent ||
                validation.signingBroadcastingPresent ||
                marker.signingBroadcastingPresent ||
                transitionGate.signingBroadcastingPresent
        val uiPresent =
            completionAudit.uiPresent ||
                suite.uiPresent ||
                validation.uiPresent ||
                marker.uiPresent ||
                transitionGate.uiPresent
        val endpointPresent =
            completionAudit.endpointPresent ||
                suite.endpointPresent ||
                validation.endpointPresent ||
                marker.endpointPresent ||
                transitionGate.endpointPresent
        val mainnetPresent =
            completionAudit.mainnetPresent ||
                suite.mainnetPresent ||
                validation.mainnetPresent ||
                marker.mainnetPresent ||
                transitionGate.mainnetPresent
        val productionProviderSelectable =
            completionAudit.productionProviderSelectable ||
                suite.productionProviderSelectable ||
                validation.productionProviderSelectable ||
                marker.productionProviderSelectable ||
                transitionGate.productionProviderSelectable ||
                canSelectInProduction
        val disabledProviderOnly =
            completionAudit.disabledProviderOnly &&
                suite.disabledProviderOnly &&
                validation.disabledProviderOnly &&
                marker.disabledProviderOnly &&
                transitionGate.disabledProviderOnly &&
                !productionProviderSelectable

        val implementationAuthorizationPresent =
            completionAudit.implementationAuthorizationPresent ||
                suite.implementationAuthorizationPresent ||
                validation.implementationAuthorizationPresent ||
                marker.implementationAuthorizationPresent ||
                transitionGate.implementationAuthorized
        val productionAuthorizationPresent =
            completionAudit.productionAuthorizationPresent ||
                suite.productionAuthorizationPresent ||
                validation.productionAuthorizationPresent ||
                marker.productionAuthorizationPresent ||
                transitionGate.productionAuthorizationPresent
        val providerSelectionAuthorizationPresent =
            completionAudit.providerSelectionAuthorizationPresent ||
                suite.providerSelectionAuthorizationPresent ||
                validation.providerSelectionAuthorizationPresent ||
                marker.providerSelectionAuthorizationPresent ||
                transitionGate.providerSelectionAuthorizationPresent
        val providerOperationAuthorizationPresent =
            completionAudit.providerOperationAuthorizationPresent ||
                suite.providerOperationAuthorizationPresent ||
                validation.providerOperationAuthorizationPresent ||
                marker.providerOperationAuthorizationPresent ||
                transitionGate.providerOperationAuthorizationPresent
        val cryptoAuthorizationPresent =
            completionAudit.cryptoAuthorizationPresent ||
                suite.cryptoAuthorizationPresent ||
                validation.cryptoAuthorizationPresent ||
                marker.cryptoAuthorizationPresent ||
                transitionGate.cryptoAuthorizationPresent
        val katRunnerAuthorizationPresent =
            completionAudit.katRunnerAuthorizationPresent ||
                suite.katRunnerAuthorizationPresent ||
                validation.katRunnerAuthorizationPresent ||
                marker.katRunnerAuthorizationPresent ||
                transitionGate.katRunnerAuthorizationPresent
        val katExecutorAuthorizationPresent =
            completionAudit.katExecutorAuthorizationPresent ||
                suite.katExecutorAuthorizationPresent ||
                validation.katExecutorAuthorizationPresent ||
                marker.katExecutorAuthorizationPresent ||
                transitionGate.katExecutorAuthorizationPresent
        val providerKatExecutorAuthorizationPresent =
            completionAudit.providerKatExecutorAuthorizationPresent ||
                suite.providerKatExecutorAuthorizationPresent ||
                validation.providerKatExecutorAuthorizationPresent ||
                marker.providerKatExecutorAuthorizationPresent ||
                transitionGate.providerKatExecutorAuthorizationPresent
        val vaultPersistenceAuthorizationPresent =
            completionAudit.vaultPersistenceAuthorizationPresent ||
                suite.vaultPersistenceAuthorizationPresent ||
                validation.vaultPersistenceAuthorizationPresent ||
                marker.vaultPersistenceAuthorizationPresent ||
                transitionGate.vaultPersistenceAuthorizationPresent
        val syncAuthorizationPresent =
            completionAudit.syncAuthorizationPresent ||
                suite.syncAuthorizationPresent ||
                validation.syncAuthorizationPresent ||
                marker.syncAuthorizationPresent ||
                transitionGate.syncAuthorizationPresent
        val signingBroadcastingAuthorizationPresent =
            completionAudit.signingBroadcastingAuthorizationPresent ||
                suite.signingBroadcastingAuthorizationPresent ||
                validation.signingBroadcastingAuthorizationPresent ||
                marker.signingBroadcastingAuthorizationPresent ||
                transitionGate.signingBroadcastingAuthorizationPresent
        val uiAuthorizationPresent =
            completionAudit.uiAuthorizationPresent ||
                suite.uiAuthorizationPresent ||
                validation.uiAuthorizationPresent ||
                marker.uiAuthorizationPresent ||
                transitionGate.uiAuthorizationPresent
        val endpointAuthorizationPresent =
            completionAudit.endpointAuthorizationPresent ||
                suite.endpointAuthorizationPresent ||
                validation.endpointAuthorizationPresent ||
                marker.endpointAuthorizationPresent ||
                transitionGate.endpointAuthorizationPresent
        val mainnetAuthorizationPresent =
            completionAudit.mainnetAuthorizationPresent ||
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
                vaultCryptoProviderInstanceExposed ||
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
        val buildHistoryExcludedFromNormalSourceMaterialCorpus =
            completionAudit.buildHistoryExcludedFromNormalSourceMaterialCorpus &&
                suite.buildHistoryExcludedFromNormalSourceMaterialCorpus &&
                validation.buildHistoryExcludedFromNormalSourceMaterialCorpus &&
                transitionGate.buildHistoryExcludedFromNormalSourceMaterialCorpus
        val localArtifactRootExcludedFromNormalSourceMaterialCorpus =
            completionAudit.localArtifactRootExcludedFromNormalSourceMaterialCorpus
        val docsReadmeUseDedicatedCorpus =
            completionAudit.docsReadmeUseDedicatedCorpus &&
                suite.docsReadmeUseDedicatedCorpus &&
                validation.docsReadmeUseDedicatedCorpus &&
                transitionGate.docsReadmeSeparateCorpus

        val descriptorCreated =
            transitionGatePresent &&
                transitionGateHumanReviewReady &&
                markerPresent &&
                markerValidationPresent &&
                markerSuiteReportPresent &&
                markerCompletionAuditPresent
        val descriptorCreatedIsCommonTestOnlyEvidence =
            descriptorCreated &&
                descriptorCommonTestOnly &&
                !implementationAuthorizationPresent &&
                !providerSelectionAuthorizationPresent &&
                !providerOperationAuthorizationPresent &&
                !cryptoAuthorizationPresent

        val failures = buildList {
            if (!transitionGatePresent || !transitionGateHumanReviewReady) {
                add(SkaldVaultV1TestOnlyProviderIdentityDescriptorFailureLabel.TransitionGateEvidenceMissing)
            }
            if (!markerPresent || !marker.markerCreated || !marker.implementationMarkerPresent) {
                add(SkaldVaultV1TestOnlyProviderIdentityDescriptorFailureLabel.MarkerEvidenceMissing)
            }
            if (!markerValidationPresent || !validation.markerValidationPassed) {
                add(SkaldVaultV1TestOnlyProviderIdentityDescriptorFailureLabel.MarkerValidationEvidenceMissing)
            }
            if (!markerSuiteReportPresent || !suite.markerSuiteReportPassed) {
                add(SkaldVaultV1TestOnlyProviderIdentityDescriptorFailureLabel.MarkerSuiteReportEvidenceMissing)
            }
            if (!markerCompletionAuditPresent || !completionAudit.markerCompletionAuditPassed) {
                add(SkaldVaultV1TestOnlyProviderIdentityDescriptorFailureLabel.MarkerCompletionAuditEvidenceMissing)
            }
            if (!descriptorCommonTestOnly || !descriptorCreatedIsCommonTestOnlyEvidence) {
                add(SkaldVaultV1TestOnlyProviderIdentityDescriptorFailureLabel.DescriptorNotCommonTestOnly)
            }
            if (!descriptorInert) {
                add(SkaldVaultV1TestOnlyProviderIdentityDescriptorFailureLabel.DescriptorNotInert)
            }
            if (!descriptorSafeLabelOnly || !markerIdentityLabelSafe) {
                add(SkaldVaultV1TestOnlyProviderIdentityDescriptorFailureLabel.DescriptorUnsafeLabel)
            }
            if (!descriptorPayloadFree) {
                add(SkaldVaultV1TestOnlyProviderIdentityDescriptorFailureLabel.DescriptorPayloadPresent)
            }
            if (providerExecutableCapabilitiesPresent) {
                add(SkaldVaultV1TestOnlyProviderIdentityDescriptorFailureLabel.ExecutableCapabilityPresent)
            }
            if (forbiddenRuntimeSurfacePresent) {
                add(SkaldVaultV1TestOnlyProviderIdentityDescriptorFailureLabel.ForbiddenRuntimeSurfacePresent)
            }
            if (forbiddenAuthorizationPresent) {
                add(SkaldVaultV1TestOnlyProviderIdentityDescriptorFailureLabel.ForbiddenAuthorizationPresent)
            }
            if (productionProviderSelectable) {
                add(SkaldVaultV1TestOnlyProviderIdentityDescriptorFailureLabel.ProductionProviderSelectable)
            }
            if (
                !buildHistoryExcludedFromNormalSourceMaterialCorpus ||
                !localArtifactRootExcludedFromNormalSourceMaterialCorpus ||
                !docsReadmeUseDedicatedCorpus
            ) {
                add(SkaldVaultV1TestOnlyProviderIdentityDescriptorFailureLabel.CorpusBoundaryMissing)
            }
        }

        val blockers =
            if (failures.isEmpty()) {
                emptyList()
            } else {
                listOf(SkaldVaultV1TestOnlyProviderIdentityDescriptorBlocker.None)
            }
        val warnings = emptyList<SkaldVaultV1TestOnlyProviderIdentityDescriptorWarning>()

        return SkaldVaultV1TestOnlyProviderIdentityDescriptor(
            descriptorId =
                SkaldVaultV1TestOnlyProviderIdentityDescriptorSafeLabel(
                    "test-only-provider-identity-descriptor-common-test-only",
                ),
            descriptorVersion = 1,
            descriptorKind =
                SkaldVaultV1TestOnlyProviderIdentityDescriptorKind.InertTestOnlyProviderIdentityDescriptor,
            sourceSet = SkaldVaultV1TestOnlyProviderIdentityDescriptorSourceSet.CommonTest,
            namespaceLabel = SkaldVaultV1TestOnlyProviderIdentityDescriptorSafeLabel(expectedNamespaceLabel),
            providerIdentityLabel =
                SkaldVaultV1TestOnlyProviderIdentityDescriptorSafeLabel(marker.safeId.value),
            transitionGatePresent = transitionGatePresent,
            transitionGateHumanReviewReady = transitionGateHumanReviewReady,
            markerPresent = markerPresent,
            markerCreated = marker.markerCreated,
            implementationMarkerPresent = marker.implementationMarkerPresent,
            markerValidationPresent = markerValidationPresent,
            markerValidationPassed = validation.markerValidationPassed,
            markerSuiteReportPresent = markerSuiteReportPresent,
            markerSuiteReportPassed = suite.markerSuiteReportPassed,
            markerCompletionAuditPresent = markerCompletionAuditPresent,
            markerCompletionAuditPassed = completionAudit.markerCompletionAuditPassed,
            descriptorCreated = descriptorCreated,
            descriptorCreatedIsCommonTestOnlyEvidence = descriptorCreatedIsCommonTestOnlyEvidence,
            descriptorInert = descriptorInert,
            descriptorCommonTestOnly = descriptorCommonTestOnly,
            descriptorSafeLabelOnly = descriptorSafeLabelOnly,
            descriptorDeterministic = descriptorDeterministic,
            descriptorPayloadFree = descriptorPayloadFree,
            markerCreatedIsInertMarkerEvidenceOnly = markerCreatedIsInertMarkerEvidenceOnly,
            implementationMarkerPresentIsInertMarkerEvidenceOnly =
                implementationMarkerPresentIsInertMarkerEvidenceOnly,
            markerValidationPassedIsValidationEvidenceOnly = markerValidationPassedIsValidationEvidenceOnly,
            markerSuiteReportPassedIsSuiteReportEvidenceOnly =
                markerSuiteReportPassedIsSuiteReportEvidenceOnly,
            markerCompletionAuditPassedIsCompletionAuditEvidenceOnly =
                markerCompletionAuditPassedIsCompletionAuditEvidenceOnly,
            userApprovalScopedToInertProviderIdentityChain =
                completionAudit.userApprovalScopedToInertMarkerChain &&
                    userApprovalScopedToInertProviderIdentityChain(
                        transitionGateAuthorized = transitionGate.implementationAuthorized,
                        markerUserApproval = marker.userApprovedInertMarkerPass,
                    ),
            providerCapabilitiesDeclared = providerCapabilitiesDeclared,
            providerExecutableCapabilitiesPresent = providerExecutableCapabilitiesPresent,
            capabilityManifest = capabilityManifest,
            canDeriveKdf = canDeriveKdf,
            canEncrypt = canEncrypt,
            canDecrypt = canDecrypt,
            canGenerateKeys = canGenerateKeys,
            canWrapKeys = canWrapKeys,
            canUnwrapKeys = canUnwrapKeys,
            canRunProviderOperations = canRunProviderOperations,
            canRunKat = canRunKat,
            canPersistVault = canPersistVault,
            canAccessSecureStorage = canAccessSecureStorage,
            canAccessSecureMetadata = canAccessSecureMetadata,
            canSelectInProduction = canSelectInProduction,
            canReachMainnet = canReachMainnet,
            providerImplementationPresent = providerImplementationPresent,
            productionProviderIdentityPresent = productionProviderIdentityPresent,
            providerRegistryEntryPresent = providerRegistryEntryPresent,
            providerFactoryPresent = providerFactoryPresent,
            providerDispatcherPresent = providerDispatcherPresent,
            executorTargetPresent = executorTargetPresent,
            providerHandlePresent = providerHandlePresent,
            containsVaultCryptoProvider = containsVaultCryptoProvider,
            implementsVaultCryptoProvider = implementsVaultCryptoProvider,
            vaultCryptoProviderInstanceExposed = vaultCryptoProviderInstanceExposed,
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
            evidenceCount = 44,
            descriptorCheckCount = checks.size,
            blockerCount = blockers.size,
            warningCount = warnings.size,
            descriptorChecks = checks,
            failureLabels = failures,
            blockers = blockers,
            warnings = warnings,
            displayLabel =
                SkaldVaultV1TestOnlyProviderIdentityDescriptorSafeLabel(
                    "inert provider identity descriptor evidence only",
                ),
        )
    }

    private fun userApprovalScopedToInertProviderIdentityChain(
        transitionGateAuthorized: Boolean,
        markerUserApproval: Boolean,
    ): Boolean =
        markerUserApproval && !transitionGateAuthorized
}
