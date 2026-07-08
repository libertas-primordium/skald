package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyExecutableProviderIdentitySafeLabel(val value: String) {
    override fun toString(): String = "RedactedTestOnlyExecutableProviderIdentitySafeLabel"
}

enum class SkaldVaultV1TestOnlyExecutableProviderKind(val label: String) {
    TestOnlyExecutablePublicKatProvider("TEST_ONLY_EXECUTABLE_PUBLIC_KAT_PROVIDER"),
}

enum class SkaldVaultV1TestOnlyExecutableProviderSourceScope(val label: String) {
    TestSourceOnly("TEST_SOURCE_ONLY"),
}

enum class SkaldVaultV1TestOnlyExecutableProviderIdentityCheck {
    ExecutableScopeAdmissionPresent,
    ExecutableScopeAdmissionPassed,
    DescriptorCompletionAuditPresent,
    DescriptorCompletionAuditPassed,
    TestOnlyExecutableProviderImplementationAdmitted,
    PublicKatProviderImplementationScopeAdmitted,
    PlannedProviderImplementationMustRemainTestSourceOnly,
    FutureProviderLevelKatExecutionRequiresSeparatePass,
    FutureProviderSelectionEnablementRequiresSeparatePass,
    TestSourceImplementationPresent,
    ProductionImplementationAbsent,
    ProductionProviderNotSelectable,
    ProviderSelectionDisabled,
    ProductionProviderRegistryEntryAbsent,
    ProductionProviderFactoryAbsent,
    ProductionProviderDispatcherAbsent,
    ProductionExecutorTargetAbsent,
    PublicKatScopeOnly,
    KdfPublicKatCoverageCapabilityLabeled,
    AeadPublicKatCoverageCapabilityLabeled,
    ProviderLevelKatExecutionDeferred,
    KdfExecutionAbsentInThisBranch,
    AeadExecutionAbsentInThisBranch,
    VaultPersistenceAbsent,
    SecureStorageSuccessAbsent,
    SecureMetadataSuccessAbsent,
    ProductionSyncAbsent,
    SigningBroadcastingAbsent,
    UiAbsent,
    EndpointAbsent,
    MainnetAbsent,
    ProviderHandlesAbsent,
    RawKatMaterialAbsent,
    TracePayloadAbsent,
    DisabledProviderOnly,
    BuildHistoryExcludedFromNormalSourceMaterialCorpus,
    LocalArtifactRootExcludedFromNormalSourceMaterialCorpus,
}

enum class SkaldVaultV1TestOnlyExecutableProviderIdentityFailureLabel {
    ExecutableScopeAdmissionMissing,
    DescriptorCompletionAuditMissing,
    ScopedImplementationAdmissionMissing,
    ImplementationNotTestSourceOnly,
    ProductionImplementationPresent,
    ProductionProviderSelectable,
    ProviderSelectionEnabled,
    ProductionRegistryFactoryDispatcherOrExecutorPresent,
    ProviderLevelKatExecutedInThisBranch,
    KdfOrAeadExecutedInThisBranch,
    ForbiddenRuntimeSurfacePresent,
    ForbiddenMaterialSurfacePresent,
    CorpusBoundaryMissing,
}

enum class SkaldVaultV1TestOnlyExecutableProviderIdentityBlocker {
    None,
}

enum class SkaldVaultV1TestOnlyExecutableProviderIdentityWarning {
    None,
}

data class SkaldVaultV1TestOnlyExecutableProviderIdentity(
    val providerImplementationId: SkaldVaultV1TestOnlyExecutableProviderIdentitySafeLabel,
    val providerIdentityLabel: SkaldVaultV1TestOnlyExecutableProviderIdentitySafeLabel,
    val providerKind: SkaldVaultV1TestOnlyExecutableProviderKind,
    val sourceScope: SkaldVaultV1TestOnlyExecutableProviderSourceScope,
    val executableScopeAdmissionPresent: Boolean,
    val executableScopeAdmissionPassed: Boolean,
    val descriptorCompletionAuditPresent: Boolean,
    val descriptorCompletionAuditPassed: Boolean,
    val testOnlyExecutableProviderImplementationAdmitted: Boolean,
    val publicKatProviderImplementationScopeAdmitted: Boolean,
    val plannedProviderImplementationMustRemainTestSourceOnly: Boolean,
    val futureProviderLevelKatExecutionRequiresSeparatePass: Boolean,
    val futureProviderSelectionEnablementRequiresSeparatePass: Boolean,
    val implementationPresent: Boolean,
    val productionImplementationPresent: Boolean,
    val productionProviderSelectable: Boolean,
    val providerSelectionEnabled: Boolean,
    val providerRegistryEntryPresent: Boolean,
    val providerFactoryPresent: Boolean,
    val providerDispatcherPresent: Boolean,
    val executorTargetPresent: Boolean,
    val publicKatScopeOnly: Boolean,
    val canCoverKdfPublicKat: Boolean,
    val canCoverAeadPublicKat: Boolean,
    val providerLevelKatExecutedInThisBranch: Boolean,
    val kdfExecutedInThisBranch: Boolean,
    val aeadExecutedInThisBranch: Boolean,
    val vaultPersistencePresent: Boolean,
    val secureStorageSuccessPathPresent: Boolean,
    val secureMetadataSuccessPathPresent: Boolean,
    val productionSyncPresent: Boolean,
    val signingBroadcastingPresent: Boolean,
    val uiPresent: Boolean,
    val endpointPresent: Boolean,
    val mainnetPresent: Boolean,
    val providerHandlesExposed: Boolean,
    val rawKatMaterialExposed: Boolean,
    val tracePayloadPresent: Boolean,
    val disabledProviderOnly: Boolean,
    val buildHistoryExcludedFromNormalSourceMaterialCorpus: Boolean,
    val localArtifactRootExcludedFromNormalSourceMaterialCorpus: Boolean,
    val evidenceCount: Int,
    val implementationCheckCount: Int,
    val blockerCount: Int,
    val warningCount: Int,
    val implementationChecks: List<SkaldVaultV1TestOnlyExecutableProviderIdentityCheck>,
    val failureLabels: List<SkaldVaultV1TestOnlyExecutableProviderIdentityFailureLabel>,
    val blockers: List<SkaldVaultV1TestOnlyExecutableProviderIdentityBlocker>,
    val warnings: List<SkaldVaultV1TestOnlyExecutableProviderIdentityWarning>,
    val displayLabel: SkaldVaultV1TestOnlyExecutableProviderIdentitySafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyExecutableProviderIdentity(" +
            "REDACTED, TEST_SOURCE_ONLY, PROVIDER_IMPLEMENTATION_ONLY, PUBLIC_KAT_SCOPE_ONLY, " +
            "NO_PROVIDER_LEVEL_KAT_EXECUTION, NO_PROVIDER_SELECTION, DISABLED_PROVIDER_ONLY" +
            ")"
}

object SkaldVaultV1TestOnlyExecutableProviderIdentityPolicy {
    fun currentTestOnlyExecutableProviderIdentity(): SkaldVaultV1TestOnlyExecutableProviderIdentity {
        val admission =
            SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionPolicy
                .currentProviderIdentityExecutableScopeAdmission()
        val descriptorCompletionAudit =
            SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditPolicy
                .currentProviderIdentityDescriptorCompletionAudit()
        val descriptor = SkaldVaultV1TestOnlyProviderIdentityDescriptorPolicy.currentProviderIdentityDescriptor()
        val checks = SkaldVaultV1TestOnlyExecutableProviderIdentityCheck.entries.toList()

        val executableScopeAdmissionPresent =
            admission.executableScopeAdmissionPassed &&
                admission.executableScopeAdmissionPassedIsCommonTestOnlyEvidence
        val descriptorCompletionAuditPresent =
            descriptorCompletionAudit.descriptorCompletionAuditPassed &&
                descriptorCompletionAudit.descriptorCompletionAuditPassedIsCommonTestOnlyEvidence
        val implementationPresent = true
        val productionImplementationPresent = false
        val productionProviderSelectable = admission.productionProviderSelectable
        val providerSelectionEnabled = admission.providerSelectionEnabled
        val providerRegistryEntryPresent = admission.providerRegistryEntryPresent
        val providerFactoryPresent = admission.providerFactoryPresent
        val providerDispatcherPresent = admission.providerDispatcherPresent
        val executorTargetPresent = admission.executorTargetPresent
        val publicKatScopeOnly =
            admission.publicKatProviderImplementationScopeAdmitted &&
                admission.plannedExecutableProviderScopeIsPublicKatOnly
        val canCoverKdfPublicKat =
            admission.plannedExecutableProviderCanCoverKdf &&
                admission.plannedExecutableProviderCanCoverKdfIsFutureScopeOnly
        val canCoverAeadPublicKat =
            admission.plannedExecutableProviderCanCoverAead &&
                admission.plannedExecutableProviderCanCoverAeadIsFutureScopeOnly
        val providerLevelKatExecutedInThisBranch = false
        val kdfExecutedInThisBranch = false
        val aeadExecutedInThisBranch = false
        val forbiddenRuntimeSurfacePresent =
            productionImplementationPresent ||
                productionProviderSelectable ||
                providerSelectionEnabled ||
                providerRegistryEntryPresent ||
                providerFactoryPresent ||
                providerDispatcherPresent ||
                executorTargetPresent ||
                providerLevelKatExecutedInThisBranch ||
                kdfExecutedInThisBranch ||
                aeadExecutedInThisBranch ||
                admission.providerOperationExecuted ||
                admission.cryptoExecuted ||
                admission.katRunnerPresent ||
                admission.katExecutorPresent ||
                admission.vaultLifecyclePresent ||
                admission.vaultPersistencePresent ||
                admission.secureSecretStorageSuccessPresent ||
                admission.secureMetadataStorageSuccessPresent ||
                admission.productionSyncPresent ||
                admission.signingBroadcastingPresent ||
                admission.uiPresent ||
                admission.endpointPresent ||
                admission.mainnetPresent
        val forbiddenMaterialSurfacePresent =
            admission.tracePayloadPresent ||
                admission.rawKatMaterialPresent ||
                admission.publicVectorBytesPresent ||
                admission.publicVectorHexPresent

        val failures = buildList {
            if (!executableScopeAdmissionPresent) {
                add(SkaldVaultV1TestOnlyExecutableProviderIdentityFailureLabel.ExecutableScopeAdmissionMissing)
            }
            if (!descriptorCompletionAuditPresent || !descriptorCompletionAudit.descriptorChainComplete) {
                add(SkaldVaultV1TestOnlyExecutableProviderIdentityFailureLabel.DescriptorCompletionAuditMissing)
            }
            if (
                !admission.testOnlyExecutableProviderImplementationAdmitted ||
                !admission.publicKatProviderImplementationScopeAdmitted ||
                !admission.plannedProviderImplementationMustRemainTestSourceOnly
            ) {
                add(SkaldVaultV1TestOnlyExecutableProviderIdentityFailureLabel.ScopedImplementationAdmissionMissing)
            }
            if (!implementationPresent || !admission.plannedProviderImplementationMustRemainTestSourceOnly) {
                add(SkaldVaultV1TestOnlyExecutableProviderIdentityFailureLabel.ImplementationNotTestSourceOnly)
            }
            if (productionImplementationPresent) {
                add(SkaldVaultV1TestOnlyExecutableProviderIdentityFailureLabel.ProductionImplementationPresent)
            }
            if (productionProviderSelectable) {
                add(SkaldVaultV1TestOnlyExecutableProviderIdentityFailureLabel.ProductionProviderSelectable)
            }
            if (providerSelectionEnabled) {
                add(SkaldVaultV1TestOnlyExecutableProviderIdentityFailureLabel.ProviderSelectionEnabled)
            }
            if (providerRegistryEntryPresent || providerFactoryPresent || providerDispatcherPresent || executorTargetPresent) {
                add(
                    SkaldVaultV1TestOnlyExecutableProviderIdentityFailureLabel
                        .ProductionRegistryFactoryDispatcherOrExecutorPresent,
                )
            }
            if (providerLevelKatExecutedInThisBranch) {
                add(
                    SkaldVaultV1TestOnlyExecutableProviderIdentityFailureLabel
                        .ProviderLevelKatExecutedInThisBranch,
                )
            }
            if (kdfExecutedInThisBranch || aeadExecutedInThisBranch) {
                add(SkaldVaultV1TestOnlyExecutableProviderIdentityFailureLabel.KdfOrAeadExecutedInThisBranch)
            }
            if (forbiddenRuntimeSurfacePresent) {
                add(SkaldVaultV1TestOnlyExecutableProviderIdentityFailureLabel.ForbiddenRuntimeSurfacePresent)
            }
            if (forbiddenMaterialSurfacePresent) {
                add(SkaldVaultV1TestOnlyExecutableProviderIdentityFailureLabel.ForbiddenMaterialSurfacePresent)
            }
            if (
                !admission.buildHistoryExcludedFromNormalSourceMaterialCorpus ||
                !admission.localArtifactRootExcludedFromNormalSourceMaterialCorpus
            ) {
                add(SkaldVaultV1TestOnlyExecutableProviderIdentityFailureLabel.CorpusBoundaryMissing)
            }
        }
        val blockers =
            if (failures.isEmpty()) {
                emptyList()
            } else {
                listOf(SkaldVaultV1TestOnlyExecutableProviderIdentityBlocker.None)
            }
        val warnings = emptyList<SkaldVaultV1TestOnlyExecutableProviderIdentityWarning>()

        return SkaldVaultV1TestOnlyExecutableProviderIdentity(
            providerImplementationId = SkaldVaultV1TestOnlyExecutableProviderIdentitySafeLabel(
                "skald-test-only-executable-vault-crypto-provider-implementation-v1",
            ),
            providerIdentityLabel =
                SkaldVaultV1TestOnlyExecutableProviderIdentitySafeLabel(descriptor.providerIdentityLabel.value),
            providerKind = SkaldVaultV1TestOnlyExecutableProviderKind.TestOnlyExecutablePublicKatProvider,
            sourceScope = SkaldVaultV1TestOnlyExecutableProviderSourceScope.TestSourceOnly,
            executableScopeAdmissionPresent = executableScopeAdmissionPresent,
            executableScopeAdmissionPassed = admission.executableScopeAdmissionPassed,
            descriptorCompletionAuditPresent = descriptorCompletionAuditPresent,
            descriptorCompletionAuditPassed = descriptorCompletionAudit.descriptorCompletionAuditPassed,
            testOnlyExecutableProviderImplementationAdmitted =
                admission.testOnlyExecutableProviderImplementationAdmitted,
            publicKatProviderImplementationScopeAdmitted =
                admission.publicKatProviderImplementationScopeAdmitted,
            plannedProviderImplementationMustRemainTestSourceOnly =
                admission.plannedProviderImplementationMustRemainTestSourceOnly,
            futureProviderLevelKatExecutionRequiresSeparatePass =
                admission.futureProviderLevelKatExecutionRequiresSeparatePass,
            futureProviderSelectionEnablementRequiresSeparatePass =
                admission.futureProviderSelectionEnablementRequiresSeparatePass,
            implementationPresent = implementationPresent,
            productionImplementationPresent = productionImplementationPresent,
            productionProviderSelectable = productionProviderSelectable,
            providerSelectionEnabled = providerSelectionEnabled,
            providerRegistryEntryPresent = providerRegistryEntryPresent,
            providerFactoryPresent = providerFactoryPresent,
            providerDispatcherPresent = providerDispatcherPresent,
            executorTargetPresent = executorTargetPresent,
            publicKatScopeOnly = publicKatScopeOnly,
            canCoverKdfPublicKat = canCoverKdfPublicKat,
            canCoverAeadPublicKat = canCoverAeadPublicKat,
            providerLevelKatExecutedInThisBranch = providerLevelKatExecutedInThisBranch,
            kdfExecutedInThisBranch = kdfExecutedInThisBranch,
            aeadExecutedInThisBranch = aeadExecutedInThisBranch,
            vaultPersistencePresent = admission.vaultPersistencePresent,
            secureStorageSuccessPathPresent = admission.secureSecretStorageSuccessPresent,
            secureMetadataSuccessPathPresent = admission.secureMetadataStorageSuccessPresent,
            productionSyncPresent = admission.productionSyncPresent,
            signingBroadcastingPresent = admission.signingBroadcastingPresent,
            uiPresent = admission.uiPresent,
            endpointPresent = admission.endpointPresent,
            mainnetPresent = admission.mainnetPresent,
            providerHandlesExposed = false,
            rawKatMaterialExposed = false,
            tracePayloadPresent = admission.tracePayloadPresent,
            disabledProviderOnly = admission.disabledProviderOnly,
            buildHistoryExcludedFromNormalSourceMaterialCorpus =
                admission.buildHistoryExcludedFromNormalSourceMaterialCorpus,
            localArtifactRootExcludedFromNormalSourceMaterialCorpus =
                admission.localArtifactRootExcludedFromNormalSourceMaterialCorpus,
            evidenceCount = 14,
            implementationCheckCount = checks.size,
            blockerCount = blockers.size,
            warningCount = warnings.size,
            implementationChecks = checks,
            failureLabels = failures,
            blockers = blockers,
            warnings = warnings,
            displayLabel = SkaldVaultV1TestOnlyExecutableProviderIdentitySafeLabel(
                "test-source-only executable provider implementation",
            ),
        )
    }
}
