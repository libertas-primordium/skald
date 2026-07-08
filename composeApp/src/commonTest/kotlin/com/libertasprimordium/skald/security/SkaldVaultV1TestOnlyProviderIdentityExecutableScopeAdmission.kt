package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionSafeLabel(val value: String) {
    override fun toString(): String = "RedactedTestOnlyProviderIdentityExecutableScopeAdmissionSafeLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionKind {
    TestOnlyProviderIdentityExecutableScopeAdmission,
}

enum class SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionSourceSet {
    CommonTest,
}

enum class SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionCheck {
    TransitionGatePresent,
    DescriptorCompletionAuditPresent,
    DescriptorCompletionAuditPassed,
    DescriptorChainComplete,
    DisabledVaultCryptoProviderBoundaryPresent,
    DependencyKatDesktopPassedEvidencePresent,
    DependencyKatAndroidPassedEvidencePresent,
    Argon2idCalibrationProbeEvidencePresent,
    TestOnlyExecutableProviderImplementationAdmitted,
    PublicKatProviderImplementationScopeAdmitted,
    PlannedExecutableProviderCanCoverKdf,
    PlannedExecutableProviderCanCoverAead,
    PlannedExecutableProviderScopeIsPublicKatOnly,
    PlannedProviderImplementationMustRemainTestSourceOnly,
    FutureProviderLevelKatExecutionRequiresSeparatePass,
    FutureProviderSelectionEnablementRequiresSeparatePass,
    ImplementationAbsentInThisPass,
    ProviderImplementationAbsent,
    ExecutableProviderImplementationAbsent,
    ExecutableProviderProductionSourceAbsent,
    ProviderOperationExecutionAbsent,
    CryptoExecutionAbsent,
    KdfExecutionAbsent,
    AeadExecutionAbsent,
    KatRunnerAbsent,
    KatExecutorAbsent,
    ProviderLevelKatExecutionAbsent,
    ProviderSelectionDisabled,
    ProductionProviderNotSelectable,
    DisabledProviderOnly,
    VaultPersistenceAbsent,
    ProductionSyncAbsent,
    SigningBroadcastingAbsent,
    UiAbsent,
    EndpointAbsent,
    MainnetAbsent,
    ScopedAdmissionsAreFutureOnly,
    DependencyEvidencePriorOnly,
    Argon2idProbeEvidencePriorOnly,
    ExecutableScopeAdmissionCommonTestOnlyEvidence,
    ProductionAuthorizationAbsent,
    ProviderSelectionAuthorizationAbsent,
    ExecutionAuthorizationAbsent,
    KatAuthorizationAbsent,
    VaultPersistenceAuthorizationAbsent,
    UiEndpointMainnetAuthorizationAbsent,
    BuildHistoryExcludedFromNormalSourceMaterialCorpus,
    LocalArtifactRootExcludedFromNormalSourceMaterialCorpus,
    DocsReadmeUseDedicatedCorpus,
}

enum class SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionFailureLabel {
    TransitionGateEvidenceMissing,
    DescriptorCompletionAuditEvidenceMissing,
    DescriptorChainIncomplete,
    DisabledVaultCryptoProviderBoundaryMissing,
    DependencyKatDesktopEvidenceMissing,
    DependencyKatAndroidEvidenceMissing,
    Argon2idCalibrationProbeEvidenceMissing,
    ScopedAdmissionMissing,
    ImplementationPresent,
    ExecutionPresent,
    ProviderSelectionEnabled,
    ForbiddenRuntimeSurfacePresent,
    ForbiddenAuthorizationPresent,
    ProductionProviderSelectable,
    CorpusBoundaryMissing,
}

enum class SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionBlocker {
    None,
}

enum class SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionWarning {
    None,
}

data class SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmission(
    val admissionId: SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionSafeLabel,
    val admissionVersion: Int,
    val admissionKind: SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionKind,
    val sourceSet: SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionSourceSet,
    val transitionGatePresent: Boolean,
    val transitionGateHumanReviewReady: Boolean,
    val descriptorCompletionAuditPresent: Boolean,
    val descriptorCompletionAuditPassed: Boolean,
    val descriptorCompletionAuditPassedIsCompletionAuditEvidenceOnly: Boolean,
    val descriptorChainComplete: Boolean,
    val disabledVaultCryptoProviderBoundaryPresent: Boolean,
    val dependencyKatDesktopPassedEvidencePresent: Boolean,
    val dependencyKatDesktopPassedEvidenceIsPriorEvidenceOnly: Boolean,
    val dependencyKatAndroidPassedEvidencePresent: Boolean,
    val dependencyKatAndroidPassedEvidenceIsPriorEvidenceOnly: Boolean,
    val argon2idCalibrationProbeEvidencePresent: Boolean,
    val argon2idCalibrationProbeEvidenceIsPriorProbeEvidenceOnly: Boolean,
    val executableScopeAdmissionPassed: Boolean,
    val executableScopeAdmissionPassedIsCommonTestOnlyEvidence: Boolean,
    val testOnlyExecutableProviderImplementationAdmitted: Boolean,
    val publicKatProviderImplementationScopeAdmitted: Boolean,
    val plannedExecutableProviderCanCoverKdf: Boolean,
    val plannedExecutableProviderCanCoverAead: Boolean,
    val plannedExecutableProviderScopeIsPublicKatOnly: Boolean,
    val plannedProviderImplementationMustRemainTestSourceOnly: Boolean,
    val futureProviderLevelKatExecutionRequiresSeparatePass: Boolean,
    val futureProviderSelectionEnablementRequiresSeparatePass: Boolean,
    val testOnlyExecutableProviderImplementationAdmittedIsFutureScopeOnly: Boolean,
    val publicKatProviderImplementationScopeAdmittedIsFutureScopeOnly: Boolean,
    val plannedExecutableProviderCanCoverKdfIsFutureScopeOnly: Boolean,
    val plannedExecutableProviderCanCoverAeadIsFutureScopeOnly: Boolean,
    val plannedExecutableProviderCanCoverKdfIsNotKdfExecution: Boolean,
    val plannedExecutableProviderCanCoverAeadIsNotAeadExecution: Boolean,
    val futureProviderLevelKatExecutionRequiresSeparatePassBlocksThisBranchKatExecution: Boolean,
    val futureProviderSelectionEnablementRequiresSeparatePassBlocksThisBranchSelection: Boolean,
    val implementationInThisPass: Boolean,
    val providerImplementationPresent: Boolean,
    val executableProviderImplementationPresent: Boolean,
    val executableProviderImplementedInProductionSource: Boolean,
    val providerOperationExecuted: Boolean,
    val cryptoExecuted: Boolean,
    val kdfExecuted: Boolean,
    val aeadExecuted: Boolean,
    val katRunnerPresent: Boolean,
    val katExecutorPresent: Boolean,
    val providerLevelKatExecuted: Boolean,
    val providerSelectionEnabled: Boolean,
    val productionProviderSelectable: Boolean,
    val disabledProviderOnly: Boolean,
    val providerRegistryEntryPresent: Boolean,
    val providerFactoryPresent: Boolean,
    val providerDispatcherPresent: Boolean,
    val executorTargetPresent: Boolean,
    val vaultLifecyclePresent: Boolean,
    val vaultPersistencePresent: Boolean,
    val secureSecretStorageSuccessPresent: Boolean,
    val secureMetadataStorageSuccessPresent: Boolean,
    val productionSyncPresent: Boolean,
    val signingBroadcastingPresent: Boolean,
    val uiPresent: Boolean,
    val endpointPresent: Boolean,
    val mainnetPresent: Boolean,
    val tracePayloadPresent: Boolean,
    val rawKatMaterialPresent: Boolean,
    val publicVectorBytesPresent: Boolean,
    val publicVectorHexPresent: Boolean,
    val implementationAuthorizationPresent: Boolean,
    val productionAuthorizationPresent: Boolean,
    val providerSelectionAuthorizationPresent: Boolean,
    val productionProviderImplementationAuthorized: Boolean,
    val productionProviderOperationExecutionAuthorized: Boolean,
    val productionProviderOperationAuthorizationPresent: Boolean,
    val productionCryptoExecutionAuthorized: Boolean,
    val productionCryptoAuthorizationPresent: Boolean,
    val productionVaultPersistenceAuthorized: Boolean,
    val productionSyncAuthorized: Boolean,
    val signingBroadcastingAuthorized: Boolean,
    val katRunnerAuthorizationPresent: Boolean,
    val katExecutorAuthorizationPresent: Boolean,
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
    val admissionCheckCount: Int,
    val blockerCount: Int,
    val warningCount: Int,
    val admissionChecks: List<SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionCheck>,
    val failureLabels: List<SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionFailureLabel>,
    val blockers: List<SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionBlocker>,
    val warnings: List<SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionWarning>,
    val displayLabel: SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionSafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmission(" +
            "REDACTED, COMMON_TEST_ONLY, EXECUTABLE_SCOPE_ADMISSION_ONLY, PUBLIC_KAT_SCOPE_ONLY, " +
            "FUTURE_TEST_SOURCE_ONLY, NO_PROVIDER_IMPLEMENTATION, NO_KDF_AEAD_EXECUTION, " +
            "NO_PROVIDER_SELECTION, DISABLED_PROVIDER_ONLY" +
            ")"
}

object SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionPolicy {
    fun currentProviderIdentityExecutableScopeAdmission():
        SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmission {
        val transitionGate =
            SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGatePolicy
                .currentProviderIdentityImplementationTransitionGate()
        val descriptorCompletionAudit =
            SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditPolicy
                .currentProviderIdentityDescriptorCompletionAudit()
        val dependencyEvidence =
            VaultCryptoDependencyProbeCatalog.currentSpikeResults()
                .single { it.candidate == VaultCryptoDependencyCandidate.TinkBouncyCastleSplit }
        val disabledProviderStatus = commonDisabledVaultCryptoProviderStatus()
        val argon2idPolicy = commonArgon2idCalibrationPolicy()
        val checks = SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionCheck.entries.toList()

        val descriptorCompletionAuditPresent =
            descriptorCompletionAudit.descriptorCompletionAuditPassed &&
                descriptorCompletionAudit.completionCheckCount ==
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditCheck.entries.size &&
                descriptorCompletionAudit.sourceSet ==
                    SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditSourceSet.CommonTest
        val descriptorCompletionAuditPassedIsCompletionAuditEvidenceOnly =
            descriptorCompletionAudit.descriptorCompletionAuditPassed &&
                descriptorCompletionAudit.descriptorCompletionAuditPassedIsCommonTestOnlyEvidence &&
                !descriptorCompletionAudit.implementationAuthorizationPresent &&
                !descriptorCompletionAudit.productionAuthorizationPresent &&
                !descriptorCompletionAudit.providerSelectionAuthorizationPresent
        val disabledVaultCryptoProviderBoundaryPresent =
            disabledProviderStatus.implementationStatus ==
                VaultCryptoProviderImplementationStatus.DisabledBoundaryOnly &&
                !disabledProviderStatus.implementationStatus.canExecuteCrypto &&
                !disabledProviderStatus.implementationStatus.productionApproved &&
                !disabledProviderStatus.canDeriveKeys &&
                !disabledProviderStatus.canEncryptRecords &&
                !disabledProviderStatus.canDecryptRecords &&
                !disabledProviderStatus.canGenerateKeys &&
                !disabledProviderStatus.canStoreKeysets &&
                !disabledProviderStatus.productionPersistenceEnabled &&
                !disabledProviderStatus.mainnetEnabled &&
                VaultCryptoProviderBlocker.ProviderImplementationMissing in disabledProviderStatus.blockers
        val dependencyKatDesktopPassedEvidencePresent =
            VaultCryptoDependencyCapability.DesktopKnownAnswerVectorsPass in dependencyEvidence.capabilities
        val dependencyKatAndroidPassedEvidencePresent =
            VaultCryptoDependencyCapability.AndroidKnownAnswerVectorsPass in dependencyEvidence.capabilities
        val argon2idCalibrationProbeEvidencePresent =
            VaultCryptoDependencyCapability.Argon2idCalibrationPolicyModeled in dependencyEvidence.capabilities &&
                argon2idPolicy.status ==
                    Argon2idCalibrationImplementationStatus.StillDisabledBuildingBlockImplemented &&
                !argon2idPolicy.productionKdfEnabled &&
                !SkaldVaultV1Argon2idCalibrationPolicy.evidence.productionKdfEnabled

        val testOnlyExecutableProviderImplementationAdmitted = true
        val publicKatProviderImplementationScopeAdmitted = true
        val plannedExecutableProviderCanCoverKdf = true
        val plannedExecutableProviderCanCoverAead = true
        val plannedExecutableProviderScopeIsPublicKatOnly = true
        val plannedProviderImplementationMustRemainTestSourceOnly = true
        val futureProviderLevelKatExecutionRequiresSeparatePass = true
        val futureProviderSelectionEnablementRequiresSeparatePass = true

        val implementationInThisPass = false
        val providerImplementationPresent = descriptorCompletionAudit.providerImplementationPresent
        val executableProviderImplementationPresent = false
        val executableProviderImplementedInProductionSource = false
        val providerOperationExecuted = descriptorCompletionAudit.providerOperationExecuted
        val cryptoExecuted = descriptorCompletionAudit.cryptoExecuted
        val kdfExecuted = false
        val aeadExecuted = false
        val katRunnerPresent = descriptorCompletionAudit.katRunnerPresent
        val katExecutorPresent = descriptorCompletionAudit.katExecutorPresent
        val providerLevelKatExecuted = false
        val providerSelectionEnabled = false
        val productionProviderSelectable = descriptorCompletionAudit.productionProviderSelectable

        val providerRegistryEntryPresent = descriptorCompletionAudit.providerRegistryEntryPresent
        val providerFactoryPresent = descriptorCompletionAudit.providerFactoryPresent
        val providerDispatcherPresent = descriptorCompletionAudit.providerDispatcherPresent
        val executorTargetPresent = descriptorCompletionAudit.executorTargetPresent
        val vaultLifecyclePresent = descriptorCompletionAudit.vaultLifecyclePresent
        val vaultPersistencePresent = descriptorCompletionAudit.vaultPersistencePresent
        val secureSecretStorageSuccessPresent = descriptorCompletionAudit.secureSecretStorageSuccessPresent
        val secureMetadataStorageSuccessPresent = descriptorCompletionAudit.secureMetadataStorageSuccessPresent
        val productionSyncPresent = descriptorCompletionAudit.productionSyncPresent
        val signingBroadcastingPresent = descriptorCompletionAudit.signingBroadcastingPresent
        val uiPresent = descriptorCompletionAudit.uiPresent
        val endpointPresent = descriptorCompletionAudit.endpointPresent
        val mainnetPresent = descriptorCompletionAudit.mainnetPresent
        val tracePayloadPresent = descriptorCompletionAudit.tracePayloadPresent
        val rawKatMaterialPresent = descriptorCompletionAudit.rawKatMaterialPresent
        val publicVectorBytesPresent = descriptorCompletionAudit.publicVectorBytesPresent
        val publicVectorHexPresent = descriptorCompletionAudit.publicVectorHexPresent

        val implementationAuthorizationPresent = descriptorCompletionAudit.implementationAuthorizationPresent
        val productionAuthorizationPresent = descriptorCompletionAudit.productionAuthorizationPresent
        val providerSelectionAuthorizationPresent = descriptorCompletionAudit.providerSelectionAuthorizationPresent
        val productionProviderImplementationAuthorized = false
        val productionProviderOperationExecutionAuthorized = false
        val productionProviderOperationAuthorizationPresent =
            descriptorCompletionAudit.providerOperationAuthorizationPresent
        val productionCryptoExecutionAuthorized = false
        val productionCryptoAuthorizationPresent = descriptorCompletionAudit.cryptoAuthorizationPresent
        val productionVaultPersistenceAuthorized = false
        val productionSyncAuthorized = false
        val signingBroadcastingAuthorized = false
        val katRunnerAuthorizationPresent = descriptorCompletionAudit.katRunnerAuthorizationPresent
        val katExecutorAuthorizationPresent = descriptorCompletionAudit.katExecutorAuthorizationPresent
        val vaultPersistenceAuthorizationPresent = descriptorCompletionAudit.vaultPersistenceAuthorizationPresent
        val syncAuthorizationPresent = descriptorCompletionAudit.syncAuthorizationPresent
        val signingBroadcastingAuthorizationPresent =
            descriptorCompletionAudit.signingBroadcastingAuthorizationPresent
        val uiAuthorizationPresent = descriptorCompletionAudit.uiAuthorizationPresent
        val endpointAuthorizationPresent = descriptorCompletionAudit.endpointAuthorizationPresent
        val mainnetAuthorizationPresent = descriptorCompletionAudit.mainnetAuthorizationPresent

        val scopedAdmissionsPresent =
            testOnlyExecutableProviderImplementationAdmitted &&
                publicKatProviderImplementationScopeAdmitted &&
                plannedExecutableProviderCanCoverKdf &&
                plannedExecutableProviderCanCoverAead &&
                plannedExecutableProviderScopeIsPublicKatOnly &&
                plannedProviderImplementationMustRemainTestSourceOnly &&
                futureProviderLevelKatExecutionRequiresSeparatePass &&
                futureProviderSelectionEnablementRequiresSeparatePass
        val implementationPresent =
            implementationInThisPass ||
                providerImplementationPresent ||
                executableProviderImplementationPresent ||
                executableProviderImplementedInProductionSource
        val executionPresent =
            providerOperationExecuted ||
                cryptoExecuted ||
                kdfExecuted ||
                aeadExecuted ||
                providerLevelKatExecuted ||
                katRunnerPresent ||
                katExecutorPresent
        val forbiddenRuntimeSurfacePresent =
            implementationPresent ||
                executionPresent ||
                providerSelectionEnabled ||
                providerRegistryEntryPresent ||
                providerFactoryPresent ||
                providerDispatcherPresent ||
                executorTargetPresent ||
                vaultLifecyclePresent ||
                vaultPersistencePresent ||
                secureSecretStorageSuccessPresent ||
                secureMetadataStorageSuccessPresent ||
                productionSyncPresent ||
                signingBroadcastingPresent ||
                uiPresent ||
                endpointPresent ||
                mainnetPresent ||
                tracePayloadPresent ||
                rawKatMaterialPresent ||
                publicVectorBytesPresent ||
                publicVectorHexPresent
        val forbiddenAuthorizationPresent =
            implementationAuthorizationPresent ||
                productionAuthorizationPresent ||
                providerSelectionAuthorizationPresent ||
                productionProviderImplementationAuthorized ||
                productionProviderOperationExecutionAuthorized ||
                productionProviderOperationAuthorizationPresent ||
                productionCryptoExecutionAuthorized ||
                productionCryptoAuthorizationPresent ||
                productionVaultPersistenceAuthorized ||
                productionSyncAuthorized ||
                signingBroadcastingAuthorized ||
                katRunnerAuthorizationPresent ||
                katExecutorAuthorizationPresent ||
                vaultPersistenceAuthorizationPresent ||
                syncAuthorizationPresent ||
                signingBroadcastingAuthorizationPresent ||
                uiAuthorizationPresent ||
                endpointAuthorizationPresent ||
                mainnetAuthorizationPresent
        val descriptorChainComplete =
            descriptorCompletionAudit.descriptorChainComplete &&
                descriptorCompletionAuditPresent &&
                descriptorCompletionAuditPassedIsCompletionAuditEvidenceOnly
        val disabledProviderOnly =
            descriptorCompletionAudit.disabledProviderOnly &&
                disabledVaultCryptoProviderBoundaryPresent &&
                !providerSelectionEnabled &&
                !productionProviderSelectable

        val failures = buildList {
            if (!transitionGate.reviewReadyForHumanDecision || !descriptorCompletionAudit.transitionGatePresent) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionFailureLabel
                        .TransitionGateEvidenceMissing,
                )
            }
            if (!descriptorCompletionAuditPresent || !descriptorCompletionAuditPassedIsCompletionAuditEvidenceOnly) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionFailureLabel
                        .DescriptorCompletionAuditEvidenceMissing,
                )
            }
            if (!descriptorChainComplete) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionFailureLabel
                        .DescriptorChainIncomplete,
                )
            }
            if (!disabledVaultCryptoProviderBoundaryPresent) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionFailureLabel
                        .DisabledVaultCryptoProviderBoundaryMissing,
                )
            }
            if (!dependencyKatDesktopPassedEvidencePresent) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionFailureLabel
                        .DependencyKatDesktopEvidenceMissing,
                )
            }
            if (!dependencyKatAndroidPassedEvidencePresent) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionFailureLabel
                        .DependencyKatAndroidEvidenceMissing,
                )
            }
            if (!argon2idCalibrationProbeEvidencePresent) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionFailureLabel
                        .Argon2idCalibrationProbeEvidenceMissing,
                )
            }
            if (!scopedAdmissionsPresent) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionFailureLabel
                        .ScopedAdmissionMissing,
                )
            }
            if (implementationPresent) {
                add(SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionFailureLabel.ImplementationPresent)
            }
            if (executionPresent) {
                add(SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionFailureLabel.ExecutionPresent)
            }
            if (providerSelectionEnabled) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionFailureLabel
                        .ProviderSelectionEnabled,
                )
            }
            if (forbiddenRuntimeSurfacePresent) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionFailureLabel
                        .ForbiddenRuntimeSurfacePresent,
                )
            }
            if (forbiddenAuthorizationPresent) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionFailureLabel
                        .ForbiddenAuthorizationPresent,
                )
            }
            if (productionProviderSelectable) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionFailureLabel
                        .ProductionProviderSelectable,
                )
            }
            if (
                !descriptorCompletionAudit.buildHistoryExcludedFromNormalSourceMaterialCorpus ||
                !descriptorCompletionAudit.localArtifactRootExcludedFromNormalSourceMaterialCorpus ||
                !descriptorCompletionAudit.docsReadmeUseDedicatedCorpus
            ) {
                add(SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionFailureLabel.CorpusBoundaryMissing)
            }
        }

        val executableScopeAdmissionPassed = failures.isEmpty()
        val executableScopeAdmissionPassedIsCommonTestOnlyEvidence =
            executableScopeAdmissionPassed &&
                !forbiddenRuntimeSurfacePresent &&
                !forbiddenAuthorizationPresent &&
                disabledProviderOnly
        val blockers =
            if (executableScopeAdmissionPassed) {
                emptyList()
            } else {
                listOf(SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionBlocker.None)
            }
        val warnings = emptyList<SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionWarning>()

        return SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmission(
            admissionId = SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionSafeLabel(
                "test-only-provider-identity-executable-scope-admission-common-test-only",
            ),
            admissionVersion = 1,
            admissionKind =
                SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionKind
                    .TestOnlyProviderIdentityExecutableScopeAdmission,
            sourceSet = SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionSourceSet.CommonTest,
            transitionGatePresent = descriptorCompletionAudit.transitionGatePresent,
            transitionGateHumanReviewReady = transitionGate.reviewReadyForHumanDecision,
            descriptorCompletionAuditPresent = descriptorCompletionAuditPresent,
            descriptorCompletionAuditPassed = descriptorCompletionAudit.descriptorCompletionAuditPassed,
            descriptorCompletionAuditPassedIsCompletionAuditEvidenceOnly =
                descriptorCompletionAuditPassedIsCompletionAuditEvidenceOnly,
            descriptorChainComplete = descriptorChainComplete,
            disabledVaultCryptoProviderBoundaryPresent = disabledVaultCryptoProviderBoundaryPresent,
            dependencyKatDesktopPassedEvidencePresent = dependencyKatDesktopPassedEvidencePresent,
            dependencyKatDesktopPassedEvidenceIsPriorEvidenceOnly =
                dependencyKatDesktopPassedEvidencePresent &&
                    !dependencyEvidence.implementationEnabled &&
                    !dependencyEvidence.productionPersistenceEnabled,
            dependencyKatAndroidPassedEvidencePresent = dependencyKatAndroidPassedEvidencePresent,
            dependencyKatAndroidPassedEvidenceIsPriorEvidenceOnly =
                dependencyKatAndroidPassedEvidencePresent &&
                    !dependencyEvidence.implementationEnabled &&
                    !dependencyEvidence.productionPersistenceEnabled,
            argon2idCalibrationProbeEvidencePresent = argon2idCalibrationProbeEvidencePresent,
            argon2idCalibrationProbeEvidenceIsPriorProbeEvidenceOnly =
                argon2idCalibrationProbeEvidencePresent && !argon2idPolicy.productionKdfEnabled,
            executableScopeAdmissionPassed = executableScopeAdmissionPassed,
            executableScopeAdmissionPassedIsCommonTestOnlyEvidence =
                executableScopeAdmissionPassedIsCommonTestOnlyEvidence,
            testOnlyExecutableProviderImplementationAdmitted = testOnlyExecutableProviderImplementationAdmitted,
            publicKatProviderImplementationScopeAdmitted = publicKatProviderImplementationScopeAdmitted,
            plannedExecutableProviderCanCoverKdf = plannedExecutableProviderCanCoverKdf,
            plannedExecutableProviderCanCoverAead = plannedExecutableProviderCanCoverAead,
            plannedExecutableProviderScopeIsPublicKatOnly = plannedExecutableProviderScopeIsPublicKatOnly,
            plannedProviderImplementationMustRemainTestSourceOnly =
                plannedProviderImplementationMustRemainTestSourceOnly,
            futureProviderLevelKatExecutionRequiresSeparatePass =
                futureProviderLevelKatExecutionRequiresSeparatePass,
            futureProviderSelectionEnablementRequiresSeparatePass =
                futureProviderSelectionEnablementRequiresSeparatePass,
            testOnlyExecutableProviderImplementationAdmittedIsFutureScopeOnly =
                testOnlyExecutableProviderImplementationAdmitted &&
                    !implementationInThisPass &&
                    !providerImplementationPresent,
            publicKatProviderImplementationScopeAdmittedIsFutureScopeOnly =
                publicKatProviderImplementationScopeAdmitted &&
                    futureProviderLevelKatExecutionRequiresSeparatePass &&
                    !providerLevelKatExecuted,
            plannedExecutableProviderCanCoverKdfIsFutureScopeOnly =
                plannedExecutableProviderCanCoverKdf && !kdfExecuted,
            plannedExecutableProviderCanCoverAeadIsFutureScopeOnly =
                plannedExecutableProviderCanCoverAead && !aeadExecuted,
            plannedExecutableProviderCanCoverKdfIsNotKdfExecution =
                plannedExecutableProviderCanCoverKdf && !kdfExecuted && !cryptoExecuted,
            plannedExecutableProviderCanCoverAeadIsNotAeadExecution =
                plannedExecutableProviderCanCoverAead && !aeadExecuted && !cryptoExecuted,
            futureProviderLevelKatExecutionRequiresSeparatePassBlocksThisBranchKatExecution =
                futureProviderLevelKatExecutionRequiresSeparatePass &&
                    !providerLevelKatExecuted &&
                    !katRunnerPresent &&
                    !katExecutorPresent,
            futureProviderSelectionEnablementRequiresSeparatePassBlocksThisBranchSelection =
                futureProviderSelectionEnablementRequiresSeparatePass &&
                    !providerSelectionEnabled &&
                    !productionProviderSelectable,
            implementationInThisPass = implementationInThisPass,
            providerImplementationPresent = providerImplementationPresent,
            executableProviderImplementationPresent = executableProviderImplementationPresent,
            executableProviderImplementedInProductionSource = executableProviderImplementedInProductionSource,
            providerOperationExecuted = providerOperationExecuted,
            cryptoExecuted = cryptoExecuted,
            kdfExecuted = kdfExecuted,
            aeadExecuted = aeadExecuted,
            katRunnerPresent = katRunnerPresent,
            katExecutorPresent = katExecutorPresent,
            providerLevelKatExecuted = providerLevelKatExecuted,
            providerSelectionEnabled = providerSelectionEnabled,
            productionProviderSelectable = productionProviderSelectable,
            disabledProviderOnly = disabledProviderOnly,
            providerRegistryEntryPresent = providerRegistryEntryPresent,
            providerFactoryPresent = providerFactoryPresent,
            providerDispatcherPresent = providerDispatcherPresent,
            executorTargetPresent = executorTargetPresent,
            vaultLifecyclePresent = vaultLifecyclePresent,
            vaultPersistencePresent = vaultPersistencePresent,
            secureSecretStorageSuccessPresent = secureSecretStorageSuccessPresent,
            secureMetadataStorageSuccessPresent = secureMetadataStorageSuccessPresent,
            productionSyncPresent = productionSyncPresent,
            signingBroadcastingPresent = signingBroadcastingPresent,
            uiPresent = uiPresent,
            endpointPresent = endpointPresent,
            mainnetPresent = mainnetPresent,
            tracePayloadPresent = tracePayloadPresent,
            rawKatMaterialPresent = rawKatMaterialPresent,
            publicVectorBytesPresent = publicVectorBytesPresent,
            publicVectorHexPresent = publicVectorHexPresent,
            implementationAuthorizationPresent = implementationAuthorizationPresent,
            productionAuthorizationPresent = productionAuthorizationPresent,
            providerSelectionAuthorizationPresent = providerSelectionAuthorizationPresent,
            productionProviderImplementationAuthorized = productionProviderImplementationAuthorized,
            productionProviderOperationExecutionAuthorized = productionProviderOperationExecutionAuthorized,
            productionProviderOperationAuthorizationPresent = productionProviderOperationAuthorizationPresent,
            productionCryptoExecutionAuthorized = productionCryptoExecutionAuthorized,
            productionCryptoAuthorizationPresent = productionCryptoAuthorizationPresent,
            productionVaultPersistenceAuthorized = productionVaultPersistenceAuthorized,
            productionSyncAuthorized = productionSyncAuthorized,
            signingBroadcastingAuthorized = signingBroadcastingAuthorized,
            katRunnerAuthorizationPresent = katRunnerAuthorizationPresent,
            katExecutorAuthorizationPresent = katExecutorAuthorizationPresent,
            vaultPersistenceAuthorizationPresent = vaultPersistenceAuthorizationPresent,
            syncAuthorizationPresent = syncAuthorizationPresent,
            signingBroadcastingAuthorizationPresent = signingBroadcastingAuthorizationPresent,
            uiAuthorizationPresent = uiAuthorizationPresent,
            endpointAuthorizationPresent = endpointAuthorizationPresent,
            mainnetAuthorizationPresent = mainnetAuthorizationPresent,
            buildHistoryExcludedFromNormalSourceMaterialCorpus =
                descriptorCompletionAudit.buildHistoryExcludedFromNormalSourceMaterialCorpus,
            localArtifactRootExcludedFromNormalSourceMaterialCorpus =
                descriptorCompletionAudit.localArtifactRootExcludedFromNormalSourceMaterialCorpus,
            docsReadmeUseDedicatedCorpus = descriptorCompletionAudit.docsReadmeUseDedicatedCorpus,
            evidenceCount = 12,
            admissionCheckCount = checks.size,
            blockerCount = blockers.size,
            warningCount = warnings.size,
            admissionChecks = checks,
            failureLabels = failures,
            blockers = blockers,
            warnings = warnings,
            displayLabel = SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionSafeLabel(
                "test-only executable scope admission evidence only",
            ),
        )
    }
}
