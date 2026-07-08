package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditSafeLabel(val value: String) {
    override fun toString(): String =
        "RedactedTestOnlyProviderSelectionValidationCompletionAuditSafeLabel"
}

enum class SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditKind(val label: String) {
    TestOnlyProviderSelectionValidationCompletionAudit(
        "TEST_ONLY_PROVIDER_SELECTION_VALIDATION_COMPLETION_AUDIT",
    ),
}

enum class SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditSourceSet(val label: String) {
    CommonTestOnly("COMMON_TEST_ONLY"),
}

enum class SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditCheck {
    ExecutableScopeAdmissionPresent,
    ExecutableScopeAdmissionPassed,
    ExecutableProviderImplementationPresent,
    ExecutableProviderImplementationTestSourceOnly,
    ProviderLevelPublicKatEvidencePresent,
    ProviderLevelPublicKatExecutionPassed,
    ProviderSelectionValidationPresent,
    ProviderSelectionValidationPassed,
    DesktopSelectedProviderKdfPublicKatPassed,
    DesktopSelectedProviderAeadPublicKatPassed,
    AndroidSelectedProviderKdfPublicKatPassed,
    AndroidSelectedProviderAeadPublicKatPassed,
    ExplicitValidationScopeRequired,
    TestOnlyProviderSelectionForValidationEnabled,
    TestOnlyProviderSelectedForPublicKat,
    ProductionProviderSelectionDisabled,
    ProductionProviderNotSelectable,
    ProductionSelectionStillDisabledProviderOnly,
    ProductionProviderImplementationAbsent,
    ProductionRegistryEntryAbsent,
    ProductionFactoryAbsent,
    ProductionDispatcherAbsent,
    ProductionExecutorTargetAbsent,
    ProviderChoiceNotPersisted,
    ProviderSelectionUiAbsent,
    TracePayloadAbsent,
    ProviderHandlesAbsent,
    SelectedProviderKatOutputNotLogged,
    VaultPersistenceAbsent,
    SecureStorageSuccessAbsent,
    SecureMetadataSuccessAbsent,
    ProductionSyncAbsent,
    SigningBroadcastingAbsent,
    UiAbsent,
    EndpointAbsent,
    MainnetAbsent,
    FutureProductionProviderSelectionRequiresSeparatePass,
    FutureVaultPersistenceRequiresSeparatePass,
    CompletionAuditEvidenceOnly,
    SelectionValidationEvidenceOnly,
    SelectedProviderKatSuccessDoesNotAuthorizeVaultPersistence,
    SelectedProviderKatSuccessDoesNotAuthorizeProductionProviderImplementation,
    BuildHistoryExcludedFromNormalSourceMaterialCorpus,
    LocalArtifactRootExcludedFromNormalSourceMaterialCorpus,
}

enum class SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditFailureLabel {
    ExecutableScopeAdmissionMissing,
    ExecutableProviderImplementationMissing,
    ProviderLevelPublicKatEvidenceMissing,
    ProviderSelectionValidationMissing,
    SelectedProviderPublicKatEvidenceMissing,
    ExplicitValidationScopeMissing,
    ProductionProviderSelectionEnabled,
    ProductionProviderSelectable,
    ProductionProviderSurfacePresent,
    ProviderChoicePersisted,
    ProviderSelectionUiPresent,
    ForbiddenVaultOrRuntimeSurfacePresent,
    ForbiddenMaterialSurfacePresent,
    CorpusBoundaryMissing,
}

data class SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAudit(
    val completionAuditId: SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditSafeLabel,
    val completionAuditVersion: Int,
    val completionAuditKind: SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditKind,
    val sourceSet: SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditSourceSet,
    val executableScopeAdmissionPresent: Boolean,
    val executableScopeAdmissionPassed: Boolean,
    val executableProviderImplementationPresent: Boolean,
    val executableProviderImplementationTestSourceOnly: Boolean,
    val providerLevelPublicKatEvidencePresent: Boolean,
    val providerLevelPublicKatExecutionPassed: Boolean,
    val providerSelectionValidationPresent: Boolean,
    val providerSelectionValidationPassed: Boolean,
    val desktopSelectedProviderKdfPublicKatPassed: Boolean,
    val desktopSelectedProviderAeadPublicKatPassed: Boolean,
    val androidSelectedProviderKdfPublicKatPassed: Boolean,
    val androidSelectedProviderAeadPublicKatPassed: Boolean,
    val explicitValidationScopeRequired: Boolean,
    val testOnlyProviderSelectionForValidationEnabled: Boolean,
    val testOnlyProviderSelectedForPublicKat: Boolean,
    val productionProviderSelectionEnabled: Boolean,
    val productionProviderSelectable: Boolean,
    val productionSelectionStillDisabledProviderOnly: Boolean,
    val productionProviderImplementationPresent: Boolean,
    val productionRegistryEntryPresent: Boolean,
    val productionFactoryPresent: Boolean,
    val productionDispatcherPresent: Boolean,
    val productionExecutorTargetPresent: Boolean,
    val providerChoicePersisted: Boolean,
    val providerSelectionUiPresent: Boolean,
    val tracePayloadPresent: Boolean,
    val providerHandlesExposed: Boolean,
    val selectedProviderKatOutputLogged: Boolean,
    val vaultPersistencePresent: Boolean,
    val secureStorageSuccessPathPresent: Boolean,
    val secureMetadataSuccessPathPresent: Boolean,
    val productionSyncPresent: Boolean,
    val signingBroadcastingPresent: Boolean,
    val uiPresent: Boolean,
    val endpointPresent: Boolean,
    val mainnetPresent: Boolean,
    val futureProductionProviderSelectionRequiresSeparatePass: Boolean,
    val futureVaultPersistenceRequiresSeparatePass: Boolean,
    val testOnlyProviderSelectionValidationCompletionAuditPassed: Boolean,
    val testOnlyProviderSelectionValidationCompletionAuditPassedIsAuditEvidenceOnly: Boolean,
    val testOnlyProviderSelectionValidationPassedIsValidationEvidenceOnly: Boolean,
    val testOnlyProviderSelectionForValidationEnabledIsNotProductionSelectionAuthorization: Boolean,
    val testOnlyProviderSelectedForPublicKatIsNotProductionProviderSelection: Boolean,
    val selectedProviderKdfPublicKatPassedIsNotProductionKdfAuthorization: Boolean,
    val selectedProviderAeadPublicKatPassedIsNotProductionAeadAuthorization: Boolean,
    val selectedProviderKatSuccessIsNotVaultPersistenceAuthorization: Boolean,
    val selectedProviderKatSuccessIsNotProductionProviderImplementationAuthorization: Boolean,
    val buildHistoryExcludedFromNormalSourceMaterialCorpus: Boolean,
    val localArtifactRootExcludedFromNormalSourceMaterialCorpus: Boolean,
    val evidenceCount: Int,
    val completionCheckCount: Int,
    val blockerCount: Int,
    val warningCount: Int,
    val completionChecks: List<SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditCheck>,
    val failureLabels: List<SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditFailureLabel>,
    val displayLabel: SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditSafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAudit(" +
            "REDACTED, COMMON_TEST_ONLY, PROVIDER_SELECTION_VALIDATION_COMPLETION_AUDIT_ONLY, " +
            "SELECTED_PROVIDER_PUBLIC_KAT_COMPLETE, NO_PRODUCTION_SELECTION, " +
            "DISABLED_PROVIDER_ONLY, NO_PROVIDER_CHOICE_PERSISTENCE, NO_PROVIDER_SELECTION_UI, " +
            "NO_STORAGE_SYNC_SIGNING_UI_ENDPOINT_MAINNET" +
            ")"
}

object SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditPolicy {
    fun currentProviderSelectionValidationCompletionAudit():
        SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAudit {
        val executableScopeAdmission =
            SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionPolicy
                .currentProviderIdentityExecutableScopeAdmission()
        val executableProviderImplementation =
            SkaldVaultV1TestOnlyExecutableProviderIdentityPolicy.currentTestOnlyExecutableProviderIdentity()
        val providerLevelKatEvidence =
            SkaldVaultV1ProviderLevelPublicKatEvidencePolicy.currentProviderLevelPublicKatEvidence()
        val providerSelectionValidation =
            SkaldVaultV1TestOnlyProviderSelectionValidationPolicy.currentProviderSelectionValidation()
        val checks = SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditCheck.entries.toList()

        val executableScopeAdmissionPresent =
            executableScopeAdmission.executableScopeAdmissionPassed &&
                executableScopeAdmission.executableScopeAdmissionPassedIsCommonTestOnlyEvidence
        val executableProviderImplementationPresent =
            executableProviderImplementation.implementationPresent &&
                executableProviderImplementation.providerKind ==
                    SkaldVaultV1TestOnlyExecutableProviderKind.TestOnlyExecutablePublicKatProvider
        val executableProviderImplementationTestSourceOnly =
            executableProviderImplementationPresent &&
                executableProviderImplementation.sourceScope ==
                    SkaldVaultV1TestOnlyExecutableProviderSourceScope.TestSourceOnly &&
                !executableProviderImplementation.productionImplementationPresent
        val providerLevelPublicKatEvidencePresent =
            providerLevelKatEvidence.publicKatScopeOnly &&
                providerLevelKatEvidence.sourceScope ==
                    SkaldVaultV1TestOnlyExecutableProviderSourceScope.TestSourceOnly
        val providerLevelPublicKatExecutionPassed =
            providerLevelKatEvidence.testOnlyProviderLevelKatExecutionPassed &&
                providerLevelKatEvidence.desktopProviderLevelKatExecuted &&
                providerLevelKatEvidence.androidProviderLevelKatExecuted &&
                providerLevelKatEvidence.kdfProviderKatPassed &&
                providerLevelKatEvidence.aeadProviderKatPassed
        val providerSelectionValidationPresent =
            providerSelectionValidation.validationKind ==
                SkaldVaultV1TestOnlyProviderSelectionValidationKind.TestOnlyProviderSelectionValidation &&
                providerSelectionValidation.sourceScope ==
                    SkaldVaultV1TestOnlyExecutableProviderSourceScope.TestSourceOnly
        val providerSelectionValidationPassed =
            providerSelectionValidation.testOnlyProviderSelectionValidationPassed &&
                providerSelectionValidation.failureLabels.isEmpty()
        val desktopSelectedProviderKdfPublicKatPassed =
            providerSelectionValidation.desktopTestOnlyProviderSelected &&
                providerSelectionValidation.selectedProviderKdfPublicKatPassed
        val desktopSelectedProviderAeadPublicKatPassed =
            providerSelectionValidation.desktopTestOnlyProviderSelected &&
                providerSelectionValidation.selectedProviderAeadPublicKatPassed
        val androidSelectedProviderKdfPublicKatPassed =
            providerSelectionValidation.androidTestOnlyProviderSelected &&
                providerSelectionValidation.selectedProviderKdfPublicKatPassed
        val androidSelectedProviderAeadPublicKatPassed =
            providerSelectionValidation.androidTestOnlyProviderSelected &&
                providerSelectionValidation.selectedProviderAeadPublicKatPassed

        val explicitValidationScopeRequired = providerSelectionValidation.explicitValidationScopeRequired
        val testOnlyProviderSelectionForValidationEnabled =
            providerSelectionValidation.testOnlyProviderSelectionForValidationEnabled
        val testOnlyProviderSelectedForPublicKat =
            providerSelectionValidation.testOnlyProviderSelectedForPublicKat
        val productionProviderSelectionEnabled =
            providerSelectionValidation.productionProviderSelectionEnabled
        val productionProviderSelectable =
            providerSelectionValidation.productionProviderSelectable
        val productionSelectionStillDisabledProviderOnly =
            providerSelectionValidation.productionSelectionStillDisabledProviderOnly
        val productionProviderImplementationPresent =
            providerSelectionValidation.productionProviderImplementationPresent
        val productionRegistryEntryPresent =
            providerSelectionValidation.productionRegistryEntryPresent
        val productionFactoryPresent =
            providerSelectionValidation.productionFactoryPresent
        val productionDispatcherPresent =
            providerSelectionValidation.productionDispatcherPresent
        val productionExecutorTargetPresent =
            providerSelectionValidation.productionExecutorTargetPresent
        val providerChoicePersisted = providerSelectionValidation.providerChoicePersisted
        val providerSelectionUiPresent = providerSelectionValidation.providerSelectionUiPresent
        val tracePayloadPresent = providerSelectionValidation.tracePayloadPresent
        val providerHandlesExposed = providerSelectionValidation.providerHandlesExposed
        val selectedProviderKatOutputLogged = false
        val vaultPersistencePresent = providerSelectionValidation.vaultPersistencePresent
        val secureStorageSuccessPathPresent = providerSelectionValidation.secureStorageSuccessPathPresent
        val secureMetadataSuccessPathPresent = providerSelectionValidation.secureMetadataSuccessPathPresent
        val productionSyncPresent = providerSelectionValidation.productionSyncPresent
        val signingBroadcastingPresent = providerSelectionValidation.signingBroadcastingPresent
        val uiPresent = providerSelectionValidation.uiPresent
        val endpointPresent = providerSelectionValidation.endpointPresent
        val mainnetPresent = providerSelectionValidation.mainnetPresent
        val futureProductionProviderSelectionRequiresSeparatePass =
            providerSelectionValidation.futureProductionProviderSelectionRequiresSeparatePass
        val futureVaultPersistenceRequiresSeparatePass =
            providerSelectionValidation.futureVaultPersistenceRequiresSeparatePass
        val forbiddenProductionSurfacePresent =
            productionProviderSelectionEnabled ||
                productionProviderSelectable ||
                productionProviderImplementationPresent ||
                productionRegistryEntryPresent ||
                productionFactoryPresent ||
                productionDispatcherPresent ||
                productionExecutorTargetPresent
        val forbiddenVaultOrRuntimeSurfacePresent =
            providerChoicePersisted ||
                providerSelectionUiPresent ||
                vaultPersistencePresent ||
                secureStorageSuccessPathPresent ||
                secureMetadataSuccessPathPresent ||
                productionSyncPresent ||
                signingBroadcastingPresent ||
                uiPresent ||
                endpointPresent ||
                mainnetPresent
        val forbiddenMaterialSurfacePresent =
            tracePayloadPresent || providerHandlesExposed || selectedProviderKatOutputLogged
        val buildHistoryExcludedFromNormalSourceMaterialCorpus =
            providerSelectionValidation.buildHistoryExcludedFromNormalSourceMaterialCorpus &&
                providerLevelKatEvidence.buildHistoryExcludedFromNormalSourceMaterialCorpus &&
                executableProviderImplementation.buildHistoryExcludedFromNormalSourceMaterialCorpus &&
                executableScopeAdmission.buildHistoryExcludedFromNormalSourceMaterialCorpus
        val localArtifactRootExcludedFromNormalSourceMaterialCorpus =
            providerSelectionValidation.localArtifactRootExcludedFromNormalSourceMaterialCorpus &&
                providerLevelKatEvidence.localArtifactRootExcludedFromNormalSourceMaterialCorpus &&
                executableProviderImplementation.localArtifactRootExcludedFromNormalSourceMaterialCorpus &&
                executableScopeAdmission.localArtifactRootExcludedFromNormalSourceMaterialCorpus

        val failures = buildList {
            if (!executableScopeAdmissionPresent || !executableScopeAdmission.executableScopeAdmissionPassed) {
                add(
                    SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditFailureLabel
                        .ExecutableScopeAdmissionMissing,
                )
            }
            if (!executableProviderImplementationPresent || !executableProviderImplementationTestSourceOnly) {
                add(
                    SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditFailureLabel
                        .ExecutableProviderImplementationMissing,
                )
            }
            if (!providerLevelPublicKatEvidencePresent || !providerLevelPublicKatExecutionPassed) {
                add(
                    SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditFailureLabel
                        .ProviderLevelPublicKatEvidenceMissing,
                )
            }
            if (!providerSelectionValidationPresent || !providerSelectionValidationPassed) {
                add(
                    SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditFailureLabel
                        .ProviderSelectionValidationMissing,
                )
            }
            if (
                !desktopSelectedProviderKdfPublicKatPassed ||
                !desktopSelectedProviderAeadPublicKatPassed ||
                !androidSelectedProviderKdfPublicKatPassed ||
                !androidSelectedProviderAeadPublicKatPassed
            ) {
                add(
                    SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditFailureLabel
                        .SelectedProviderPublicKatEvidenceMissing,
                )
            }
            if (!explicitValidationScopeRequired) {
                add(
                    SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditFailureLabel
                        .ExplicitValidationScopeMissing,
                )
            }
            if (productionProviderSelectionEnabled) {
                add(
                    SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditFailureLabel
                        .ProductionProviderSelectionEnabled,
                )
            }
            if (productionProviderSelectable) {
                add(
                    SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditFailureLabel
                        .ProductionProviderSelectable,
                )
            }
            if (
                productionProviderImplementationPresent ||
                productionRegistryEntryPresent ||
                productionFactoryPresent ||
                productionDispatcherPresent ||
                productionExecutorTargetPresent
            ) {
                add(
                    SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditFailureLabel
                        .ProductionProviderSurfacePresent,
                )
            }
            if (providerChoicePersisted) {
                add(
                    SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditFailureLabel
                        .ProviderChoicePersisted,
                )
            }
            if (providerSelectionUiPresent) {
                add(
                    SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditFailureLabel
                        .ProviderSelectionUiPresent,
                )
            }
            if (forbiddenVaultOrRuntimeSurfacePresent) {
                add(
                    SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditFailureLabel
                        .ForbiddenVaultOrRuntimeSurfacePresent,
                )
            }
            if (forbiddenMaterialSurfacePresent) {
                add(
                    SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditFailureLabel
                        .ForbiddenMaterialSurfacePresent,
                )
            }
            if (!buildHistoryExcludedFromNormalSourceMaterialCorpus || !localArtifactRootExcludedFromNormalSourceMaterialCorpus) {
                add(
                    SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditFailureLabel
                        .CorpusBoundaryMissing,
                )
            }
        }

        val testOnlyProviderSelectionValidationCompletionAuditPassed =
            failures.isEmpty() &&
                !forbiddenProductionSurfacePresent &&
                !forbiddenVaultOrRuntimeSurfacePresent &&
                !forbiddenMaterialSurfacePresent

        return SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAudit(
            completionAuditId = SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditSafeLabel(
                "skald-test-only-provider-selection-validation-completion-audit-v1",
            ),
            completionAuditVersion = 1,
            completionAuditKind =
                SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditKind
                    .TestOnlyProviderSelectionValidationCompletionAudit,
            sourceSet =
                SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditSourceSet.CommonTestOnly,
            executableScopeAdmissionPresent = executableScopeAdmissionPresent,
            executableScopeAdmissionPassed = executableScopeAdmission.executableScopeAdmissionPassed,
            executableProviderImplementationPresent = executableProviderImplementationPresent,
            executableProviderImplementationTestSourceOnly = executableProviderImplementationTestSourceOnly,
            providerLevelPublicKatEvidencePresent = providerLevelPublicKatEvidencePresent,
            providerLevelPublicKatExecutionPassed = providerLevelPublicKatExecutionPassed,
            providerSelectionValidationPresent = providerSelectionValidationPresent,
            providerSelectionValidationPassed = providerSelectionValidationPassed,
            desktopSelectedProviderKdfPublicKatPassed = desktopSelectedProviderKdfPublicKatPassed,
            desktopSelectedProviderAeadPublicKatPassed = desktopSelectedProviderAeadPublicKatPassed,
            androidSelectedProviderKdfPublicKatPassed = androidSelectedProviderKdfPublicKatPassed,
            androidSelectedProviderAeadPublicKatPassed = androidSelectedProviderAeadPublicKatPassed,
            explicitValidationScopeRequired = explicitValidationScopeRequired,
            testOnlyProviderSelectionForValidationEnabled = testOnlyProviderSelectionForValidationEnabled,
            testOnlyProviderSelectedForPublicKat = testOnlyProviderSelectedForPublicKat,
            productionProviderSelectionEnabled = productionProviderSelectionEnabled,
            productionProviderSelectable = productionProviderSelectable,
            productionSelectionStillDisabledProviderOnly = productionSelectionStillDisabledProviderOnly,
            productionProviderImplementationPresent = productionProviderImplementationPresent,
            productionRegistryEntryPresent = productionRegistryEntryPresent,
            productionFactoryPresent = productionFactoryPresent,
            productionDispatcherPresent = productionDispatcherPresent,
            productionExecutorTargetPresent = productionExecutorTargetPresent,
            providerChoicePersisted = providerChoicePersisted,
            providerSelectionUiPresent = providerSelectionUiPresent,
            tracePayloadPresent = tracePayloadPresent,
            providerHandlesExposed = providerHandlesExposed,
            selectedProviderKatOutputLogged = selectedProviderKatOutputLogged,
            vaultPersistencePresent = vaultPersistencePresent,
            secureStorageSuccessPathPresent = secureStorageSuccessPathPresent,
            secureMetadataSuccessPathPresent = secureMetadataSuccessPathPresent,
            productionSyncPresent = productionSyncPresent,
            signingBroadcastingPresent = signingBroadcastingPresent,
            uiPresent = uiPresent,
            endpointPresent = endpointPresent,
            mainnetPresent = mainnetPresent,
            futureProductionProviderSelectionRequiresSeparatePass =
                futureProductionProviderSelectionRequiresSeparatePass,
            futureVaultPersistenceRequiresSeparatePass = futureVaultPersistenceRequiresSeparatePass,
            testOnlyProviderSelectionValidationCompletionAuditPassed =
                testOnlyProviderSelectionValidationCompletionAuditPassed,
            testOnlyProviderSelectionValidationCompletionAuditPassedIsAuditEvidenceOnly =
                testOnlyProviderSelectionValidationCompletionAuditPassed &&
                    !productionProviderSelectionEnabled &&
                    !productionProviderSelectable,
            testOnlyProviderSelectionValidationPassedIsValidationEvidenceOnly =
                providerSelectionValidationPassed &&
                    !productionProviderSelectionEnabled &&
                    !productionProviderSelectable,
            testOnlyProviderSelectionForValidationEnabledIsNotProductionSelectionAuthorization =
                testOnlyProviderSelectionForValidationEnabled && !productionProviderSelectionEnabled,
            testOnlyProviderSelectedForPublicKatIsNotProductionProviderSelection =
                testOnlyProviderSelectedForPublicKat && productionSelectionStillDisabledProviderOnly,
            selectedProviderKdfPublicKatPassedIsNotProductionKdfAuthorization =
                desktopSelectedProviderKdfPublicKatPassed &&
                    androidSelectedProviderKdfPublicKatPassed &&
                    !productionProviderSelectionEnabled,
            selectedProviderAeadPublicKatPassedIsNotProductionAeadAuthorization =
                desktopSelectedProviderAeadPublicKatPassed &&
                    androidSelectedProviderAeadPublicKatPassed &&
                    !productionProviderSelectionEnabled,
            selectedProviderKatSuccessIsNotVaultPersistenceAuthorization =
                !vaultPersistencePresent &&
                    !secureStorageSuccessPathPresent &&
                    !secureMetadataSuccessPathPresent,
            selectedProviderKatSuccessIsNotProductionProviderImplementationAuthorization =
                !productionProviderImplementationPresent &&
                    !productionRegistryEntryPresent &&
                    !productionFactoryPresent &&
                    !productionDispatcherPresent &&
                    !productionExecutorTargetPresent,
            buildHistoryExcludedFromNormalSourceMaterialCorpus =
                buildHistoryExcludedFromNormalSourceMaterialCorpus,
            localArtifactRootExcludedFromNormalSourceMaterialCorpus =
                localArtifactRootExcludedFromNormalSourceMaterialCorpus,
            evidenceCount = 16,
            completionCheckCount = checks.size,
            blockerCount = failures.size,
            warningCount = 0,
            completionChecks = checks,
            failureLabels = failures,
            displayLabel = SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditSafeLabel(
                "test-only provider-selection validation completion audit",
            ),
        )
    }
}
