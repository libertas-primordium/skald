package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderSelectionValidationSafeLabel(val value: String) {
    override fun toString(): String = "RedactedTestOnlyProviderSelectionValidationSafeLabel"
}

enum class SkaldVaultV1TestOnlyProviderSelectionValidationKind(val label: String) {
    TestOnlyProviderSelectionValidation("TEST_ONLY_PROVIDER_SELECTION_VALIDATION"),
}

enum class SkaldVaultV1TestOnlyProviderSelectionValidationCheck {
    ProviderLevelPublicKatEvidencePresent,
    ProviderLevelPublicKatEvidencePassed,
    TestSourceOnly,
    ExplicitValidationScopeRequired,
    TestOnlyProviderSelectionValidationPassed,
    TestOnlyProviderSelectionForValidationEnabled,
    TestOnlyProviderSelectedForPublicKat,
    DesktopTestOnlyProviderSelected,
    AndroidTestOnlyProviderSelected,
    SelectedProviderKdfPublicKatPassed,
    SelectedProviderAeadPublicKatPassed,
    ProductionProviderSelectionDisabled,
    ProductionSelectionStillDisabledProviderOnly,
    ProductionProviderNotSelectable,
    ProductionProviderImplementationAbsent,
    ProductionRegistryEntryAbsent,
    ProductionFactoryAbsent,
    ProductionDispatcherAbsent,
    ProductionExecutorTargetAbsent,
    ProviderChoiceNotPersisted,
    ProviderSelectionUiAbsent,
    TracePayloadAbsent,
    ProviderHandlesAbsent,
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
    BuildHistoryExcludedFromNormalSourceMaterialCorpus,
    LocalArtifactRootExcludedFromNormalSourceMaterialCorpus,
}

enum class SkaldVaultV1TestOnlyProviderSelectionValidationFailureLabel {
    ProviderLevelPublicKatEvidenceMissing,
    ExplicitValidationScopeMissing,
    SelectedProviderKatMissing,
    ProductionProviderSelectionEnabled,
    ProductionProviderSelectable,
    ProductionProviderSurfacePresent,
    ProviderChoicePersisted,
    ProviderSelectionUiPresent,
    ForbiddenVaultOrRuntimeSurfacePresent,
    ForbiddenMaterialSurfacePresent,
    CorpusBoundaryMissing,
}

data class SkaldVaultV1TestOnlyProviderSelectionValidation(
    val validationId: SkaldVaultV1TestOnlyProviderSelectionValidationSafeLabel,
    val validationVersion: Int,
    val validationKind: SkaldVaultV1TestOnlyProviderSelectionValidationKind,
    val sourceScope: SkaldVaultV1TestOnlyExecutableProviderSourceScope,
    val providerLevelPublicKatEvidencePresent: Boolean,
    val providerLevelPublicKatEvidencePassed: Boolean,
    val explicitValidationScopeRequired: Boolean,
    val testOnlyProviderSelectionValidationPassed: Boolean,
    val testOnlyProviderSelectionForValidationEnabled: Boolean,
    val testOnlyProviderSelectedForPublicKat: Boolean,
    val desktopTestOnlyProviderSelected: Boolean,
    val androidTestOnlyProviderSelected: Boolean,
    val selectedProviderKdfPublicKatPassed: Boolean,
    val selectedProviderAeadPublicKatPassed: Boolean,
    val productionProviderSelectable: Boolean,
    val productionProviderSelectionEnabled: Boolean,
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
    val buildHistoryExcludedFromNormalSourceMaterialCorpus: Boolean,
    val localArtifactRootExcludedFromNormalSourceMaterialCorpus: Boolean,
    val evidenceCount: Int,
    val validationCheckCount: Int,
    val blockerCount: Int,
    val warningCount: Int,
    val validationChecks: List<SkaldVaultV1TestOnlyProviderSelectionValidationCheck>,
    val failureLabels: List<SkaldVaultV1TestOnlyProviderSelectionValidationFailureLabel>,
    val displayLabel: SkaldVaultV1TestOnlyProviderSelectionValidationSafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderSelectionValidation(" +
            "REDACTED, TEST_SOURCE_ONLY, PROVIDER_SELECTION_VALIDATION_ONLY, " +
            "PUBLIC_KAT_SCOPE_ONLY, NO_PRODUCTION_SELECTION, DISABLED_PROVIDER_ONLY, " +
            "NO_STORAGE_SYNC_SIGNING_UI_ENDPOINT_MAINNET" +
            ")"
}

object SkaldVaultV1TestOnlyProviderSelectionValidationPolicy {
    fun currentProviderSelectionValidation(
        desktopTestOnlyProviderSelected: Boolean = true,
        androidTestOnlyProviderSelected: Boolean = true,
        selectedProviderKdfPublicKatPassed: Boolean = true,
        selectedProviderAeadPublicKatPassed: Boolean = true,
    ): SkaldVaultV1TestOnlyProviderSelectionValidation {
        val providerLevelKatEvidence =
            SkaldVaultV1ProviderLevelPublicKatEvidencePolicy.currentProviderLevelPublicKatEvidence()
        val checks = SkaldVaultV1TestOnlyProviderSelectionValidationCheck.entries.toList()
        val providerLevelPublicKatEvidencePresent =
            providerLevelKatEvidence.testOnlyProviderLevelKatExecutionPassed &&
                providerLevelKatEvidence.publicKatScopeOnly
        val providerLevelPublicKatEvidencePassed =
            providerLevelKatEvidence.desktopProviderLevelKatExecuted &&
                providerLevelKatEvidence.androidProviderLevelKatExecuted &&
                providerLevelKatEvidence.kdfProviderKatPassed &&
                providerLevelKatEvidence.aeadProviderKatPassed
        val explicitValidationScopeRequired = true
        val testOnlyProviderSelectionForValidationEnabled = true
        val testOnlyProviderSelectedForPublicKat =
            desktopTestOnlyProviderSelected && androidTestOnlyProviderSelected
        val productionProviderSelectable = providerLevelKatEvidence.productionProviderSelectable
        val productionProviderSelectionEnabled = providerLevelKatEvidence.providerSelectionEnabled
        val productionSelectionStillDisabledProviderOnly =
            !productionProviderSelectionEnabled && !productionProviderSelectable
        val productionProviderImplementationPresent =
            providerLevelKatEvidence.productionProviderImplementationPresent
        val productionRegistryEntryPresent =
            providerLevelKatEvidence.productionProviderRegistryEntryPresent
        val productionFactoryPresent =
            providerLevelKatEvidence.productionProviderFactoryPresent
        val productionDispatcherPresent =
            providerLevelKatEvidence.productionProviderDispatcherPresent
        val productionExecutorTargetPresent =
            providerLevelKatEvidence.productionExecutorTargetPresent
        val providerChoicePersisted = false
        val providerSelectionUiPresent = false
        val tracePayloadPresent = false
        val providerHandlesExposed = false
        val vaultPersistencePresent = providerLevelKatEvidence.vaultPersistencePresent
        val secureStorageSuccessPathPresent =
            providerLevelKatEvidence.secureStorageSuccessPathPresent
        val secureMetadataSuccessPathPresent =
            providerLevelKatEvidence.secureMetadataSuccessPathPresent
        val productionSyncPresent = providerLevelKatEvidence.productionSyncPresent
        val signingBroadcastingPresent = providerLevelKatEvidence.signingBroadcastingPresent
        val uiPresent = providerLevelKatEvidence.uiPresent
        val endpointPresent = providerLevelKatEvidence.endpointPresent
        val mainnetPresent = providerLevelKatEvidence.mainnetPresent
        val futureProductionProviderSelectionRequiresSeparatePass = true
        val futureVaultPersistenceRequiresSeparatePass = true
        val forbiddenProductionSurfacePresent =
            productionProviderSelectionEnabled ||
                productionProviderSelectable ||
                productionProviderImplementationPresent ||
                productionRegistryEntryPresent ||
                productionFactoryPresent ||
                productionDispatcherPresent ||
                productionExecutorTargetPresent
        val forbiddenVaultOrRuntimeSurfacePresent =
            vaultPersistencePresent ||
                secureStorageSuccessPathPresent ||
                secureMetadataSuccessPathPresent ||
                productionSyncPresent ||
                signingBroadcastingPresent ||
                uiPresent ||
                endpointPresent ||
                mainnetPresent
        val forbiddenMaterialSurfacePresent =
            tracePayloadPresent || providerHandlesExposed
        val testOnlyProviderSelectionValidationPassed =
            providerLevelPublicKatEvidencePresent &&
                providerLevelPublicKatEvidencePassed &&
                explicitValidationScopeRequired &&
                testOnlyProviderSelectionForValidationEnabled &&
                testOnlyProviderSelectedForPublicKat &&
                selectedProviderKdfPublicKatPassed &&
                selectedProviderAeadPublicKatPassed &&
                productionSelectionStillDisabledProviderOnly &&
                !forbiddenProductionSurfacePresent &&
                !providerChoicePersisted &&
                !providerSelectionUiPresent &&
                !forbiddenVaultOrRuntimeSurfacePresent &&
                !forbiddenMaterialSurfacePresent &&
                futureProductionProviderSelectionRequiresSeparatePass &&
                futureVaultPersistenceRequiresSeparatePass &&
                providerLevelKatEvidence.buildHistoryExcludedFromNormalSourceMaterialCorpus &&
                providerLevelKatEvidence.localArtifactRootExcludedFromNormalSourceMaterialCorpus

        val failures = buildList {
            if (!providerLevelPublicKatEvidencePresent || !providerLevelPublicKatEvidencePassed) {
                add(
                    SkaldVaultV1TestOnlyProviderSelectionValidationFailureLabel
                        .ProviderLevelPublicKatEvidenceMissing,
                )
            }
            if (!explicitValidationScopeRequired) {
                add(SkaldVaultV1TestOnlyProviderSelectionValidationFailureLabel.ExplicitValidationScopeMissing)
            }
            if (
                !testOnlyProviderSelectedForPublicKat ||
                !selectedProviderKdfPublicKatPassed ||
                !selectedProviderAeadPublicKatPassed
            ) {
                add(SkaldVaultV1TestOnlyProviderSelectionValidationFailureLabel.SelectedProviderKatMissing)
            }
            if (productionProviderSelectionEnabled) {
                add(SkaldVaultV1TestOnlyProviderSelectionValidationFailureLabel.ProductionProviderSelectionEnabled)
            }
            if (productionProviderSelectable) {
                add(SkaldVaultV1TestOnlyProviderSelectionValidationFailureLabel.ProductionProviderSelectable)
            }
            if (
                productionProviderImplementationPresent ||
                productionRegistryEntryPresent ||
                productionFactoryPresent ||
                productionDispatcherPresent ||
                productionExecutorTargetPresent
            ) {
                add(SkaldVaultV1TestOnlyProviderSelectionValidationFailureLabel.ProductionProviderSurfacePresent)
            }
            if (providerChoicePersisted) {
                add(SkaldVaultV1TestOnlyProviderSelectionValidationFailureLabel.ProviderChoicePersisted)
            }
            if (providerSelectionUiPresent) {
                add(SkaldVaultV1TestOnlyProviderSelectionValidationFailureLabel.ProviderSelectionUiPresent)
            }
            if (forbiddenVaultOrRuntimeSurfacePresent) {
                add(
                    SkaldVaultV1TestOnlyProviderSelectionValidationFailureLabel
                        .ForbiddenVaultOrRuntimeSurfacePresent,
                )
            }
            if (forbiddenMaterialSurfacePresent) {
                add(SkaldVaultV1TestOnlyProviderSelectionValidationFailureLabel.ForbiddenMaterialSurfacePresent)
            }
            if (
                !providerLevelKatEvidence.buildHistoryExcludedFromNormalSourceMaterialCorpus ||
                !providerLevelKatEvidence.localArtifactRootExcludedFromNormalSourceMaterialCorpus
            ) {
                add(SkaldVaultV1TestOnlyProviderSelectionValidationFailureLabel.CorpusBoundaryMissing)
            }
        }

        return SkaldVaultV1TestOnlyProviderSelectionValidation(
            validationId = SkaldVaultV1TestOnlyProviderSelectionValidationSafeLabel(
                "skald-test-only-provider-selection-validation-v1",
            ),
            validationVersion = 1,
            validationKind =
                SkaldVaultV1TestOnlyProviderSelectionValidationKind.TestOnlyProviderSelectionValidation,
            sourceScope = SkaldVaultV1TestOnlyExecutableProviderSourceScope.TestSourceOnly,
            providerLevelPublicKatEvidencePresent = providerLevelPublicKatEvidencePresent,
            providerLevelPublicKatEvidencePassed = providerLevelPublicKatEvidencePassed,
            explicitValidationScopeRequired = explicitValidationScopeRequired,
            testOnlyProviderSelectionValidationPassed = testOnlyProviderSelectionValidationPassed,
            testOnlyProviderSelectionForValidationEnabled = testOnlyProviderSelectionForValidationEnabled,
            testOnlyProviderSelectedForPublicKat = testOnlyProviderSelectedForPublicKat,
            desktopTestOnlyProviderSelected = desktopTestOnlyProviderSelected,
            androidTestOnlyProviderSelected = androidTestOnlyProviderSelected,
            selectedProviderKdfPublicKatPassed = selectedProviderKdfPublicKatPassed,
            selectedProviderAeadPublicKatPassed = selectedProviderAeadPublicKatPassed,
            productionProviderSelectable = productionProviderSelectable,
            productionProviderSelectionEnabled = productionProviderSelectionEnabled,
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
            buildHistoryExcludedFromNormalSourceMaterialCorpus =
                providerLevelKatEvidence.buildHistoryExcludedFromNormalSourceMaterialCorpus,
            localArtifactRootExcludedFromNormalSourceMaterialCorpus =
                providerLevelKatEvidence.localArtifactRootExcludedFromNormalSourceMaterialCorpus,
            evidenceCount = 12,
            validationCheckCount = checks.size,
            blockerCount = failures.size,
            warningCount = 0,
            validationChecks = checks,
            failureLabels = failures,
            displayLabel = SkaldVaultV1TestOnlyProviderSelectionValidationSafeLabel(
                "test-only provider selection validation evidence",
            ),
        )
    }
}
