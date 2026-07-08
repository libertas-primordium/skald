package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1ProviderLevelPublicKatEvidenceSafeLabel(val value: String) {
    override fun toString(): String = "RedactedProviderLevelPublicKatEvidenceSafeLabel"
}

enum class SkaldVaultV1ProviderLevelPublicKatExecutionKind(val label: String) {
    TestOnlyProviderLevelPublicKatExecution("TEST_ONLY_PROVIDER_LEVEL_PUBLIC_KAT_EXECUTION"),
}

enum class SkaldVaultV1ProviderLevelPublicKatEvidenceCheck {
    TestOnlyExecutableProviderIdentityPresent,
    TestSourceOnly,
    PublicKatScopeOnly,
    DesktopProviderLevelKatExecuted,
    AndroidProviderLevelKatExecuted,
    KdfProviderKatExecuted,
    KdfProviderKatPassed,
    AeadProviderKatExecuted,
    AeadProviderKatPassed,
    TestOnlyProviderLevelKatExecutionPassed,
    ProviderSelectionDisabled,
    ProductionProviderNotSelectable,
    ProductionProviderImplementationAbsent,
    ProductionProviderRegistryEntryAbsent,
    ProductionProviderFactoryAbsent,
    ProductionProviderDispatcherAbsent,
    ProductionExecutorTargetAbsent,
    ProviderSelectionAuthorizationAbsent,
    ProductionAuthorizationAbsent,
    VaultPersistenceAbsent,
    SecureStorageSuccessAbsent,
    SecureMetadataSuccessAbsent,
    ProductionSyncAbsent,
    SigningBroadcastingAbsent,
    UiAbsent,
    EndpointAbsent,
    MainnetAbsent,
    FutureProviderSelectionRequiresSeparatePass,
    BuildHistoryExcludedFromNormalSourceMaterialCorpus,
    LocalArtifactRootExcludedFromNormalSourceMaterialCorpus,
}

enum class SkaldVaultV1ProviderLevelPublicKatEvidenceFailureLabel {
    TestOnlyProviderIdentityMissing,
    ProviderLevelKatExecutionMissing,
    ProviderLevelKatFailed,
    ProviderSelectionEnabled,
    ProductionProviderSelectable,
    ProductionProviderSurfacePresent,
    ForbiddenVaultOrRuntimeSurfacePresent,
    AuthorizationPresent,
    CorpusBoundaryMissing,
}

data class SkaldVaultV1ProviderLevelPublicKatEvidence(
    val providerLevelKatEvidenceId: SkaldVaultV1ProviderLevelPublicKatEvidenceSafeLabel,
    val providerLevelKatExecutionVersion: Int,
    val providerKind: SkaldVaultV1TestOnlyExecutableProviderKind,
    val sourceScope: SkaldVaultV1TestOnlyExecutableProviderSourceScope,
    val desktopProviderLevelKatExecuted: Boolean,
    val androidProviderLevelKatExecuted: Boolean,
    val kdfProviderKatExecuted: Boolean,
    val kdfProviderKatPassed: Boolean,
    val aeadProviderKatExecuted: Boolean,
    val aeadProviderKatPassed: Boolean,
    val publicKatScopeOnly: Boolean,
    val providerSelectionEnabled: Boolean,
    val productionProviderSelectable: Boolean,
    val productionProviderImplementationPresent: Boolean,
    val productionProviderRegistryEntryPresent: Boolean,
    val productionProviderFactoryPresent: Boolean,
    val productionProviderDispatcherPresent: Boolean,
    val productionExecutorTargetPresent: Boolean,
    val providerSelectionAuthorizationPresent: Boolean,
    val productionAuthorizationPresent: Boolean,
    val vaultPersistencePresent: Boolean,
    val secureStorageSuccessPathPresent: Boolean,
    val secureMetadataSuccessPathPresent: Boolean,
    val productionSyncPresent: Boolean,
    val signingBroadcastingPresent: Boolean,
    val uiPresent: Boolean,
    val endpointPresent: Boolean,
    val mainnetPresent: Boolean,
    val testOnlyProviderLevelKatExecutionPassed: Boolean,
    val futureProviderSelectionEnablementRequiresSeparatePass: Boolean,
    val buildHistoryExcludedFromNormalSourceMaterialCorpus: Boolean,
    val localArtifactRootExcludedFromNormalSourceMaterialCorpus: Boolean,
    val evidenceCount: Int,
    val blockerCount: Int,
    val warningCount: Int,
    val evidenceChecks: List<SkaldVaultV1ProviderLevelPublicKatEvidenceCheck>,
    val failureLabels: List<SkaldVaultV1ProviderLevelPublicKatEvidenceFailureLabel>,
    val displayLabel: SkaldVaultV1ProviderLevelPublicKatEvidenceSafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1ProviderLevelPublicKatEvidence(" +
            "REDACTED, TEST_SOURCE_ONLY, PROVIDER_LEVEL_PUBLIC_KAT_EXECUTION_ONLY, " +
            "PUBLIC_VECTORS_ONLY, NO_PROVIDER_SELECTION, NO_PRODUCTION_PROVIDER, " +
            "NO_STORAGE_SYNC_SIGNING_UI_ENDPOINT_MAINNET" +
            ")"
}

object SkaldVaultV1ProviderLevelPublicKatEvidencePolicy {
    fun currentProviderLevelPublicKatEvidence(
        desktopProviderLevelKatExecuted: Boolean = true,
        androidProviderLevelKatExecuted: Boolean = true,
        kdfProviderKatPassed: Boolean = true,
        aeadProviderKatPassed: Boolean = true,
    ): SkaldVaultV1ProviderLevelPublicKatEvidence {
        val identity =
            SkaldVaultV1TestOnlyExecutableProviderIdentityPolicy.currentTestOnlyExecutableProviderIdentity()
        val checks = SkaldVaultV1ProviderLevelPublicKatEvidenceCheck.entries.toList()
        val kdfProviderKatExecuted = desktopProviderLevelKatExecuted && androidProviderLevelKatExecuted
        val aeadProviderKatExecuted = desktopProviderLevelKatExecuted && androidProviderLevelKatExecuted
        val providerSelectionEnabled = identity.providerSelectionEnabled
        val productionProviderSelectable = identity.productionProviderSelectable
        val productionProviderImplementationPresent = identity.productionImplementationPresent
        val productionProviderRegistryEntryPresent = identity.providerRegistryEntryPresent
        val productionProviderFactoryPresent = identity.providerFactoryPresent
        val productionProviderDispatcherPresent = identity.providerDispatcherPresent
        val productionExecutorTargetPresent = identity.executorTargetPresent
        val vaultPersistencePresent = identity.vaultPersistencePresent
        val secureStorageSuccessPathPresent = identity.secureStorageSuccessPathPresent
        val secureMetadataSuccessPathPresent = identity.secureMetadataSuccessPathPresent
        val productionSyncPresent = identity.productionSyncPresent
        val signingBroadcastingPresent = identity.signingBroadcastingPresent
        val uiPresent = identity.uiPresent
        val endpointPresent = identity.endpointPresent
        val mainnetPresent = identity.mainnetPresent
        val providerSelectionAuthorizationPresent = false
        val productionAuthorizationPresent = false
        val testOnlyProviderLevelKatExecutionPassed =
            desktopProviderLevelKatExecuted &&
                androidProviderLevelKatExecuted &&
                kdfProviderKatExecuted &&
                kdfProviderKatPassed &&
                aeadProviderKatExecuted &&
                aeadProviderKatPassed &&
                identity.publicKatScopeOnly &&
                !providerSelectionEnabled &&
                !productionProviderSelectable &&
                !productionProviderImplementationPresent &&
                !productionProviderRegistryEntryPresent &&
                !productionProviderFactoryPresent &&
                !productionProviderDispatcherPresent &&
                !productionExecutorTargetPresent &&
                !providerSelectionAuthorizationPresent &&
                !productionAuthorizationPresent &&
                !vaultPersistencePresent &&
                !secureStorageSuccessPathPresent &&
                !secureMetadataSuccessPathPresent &&
                !productionSyncPresent &&
                !signingBroadcastingPresent &&
                !uiPresent &&
                !endpointPresent &&
                !mainnetPresent

        val failures = buildList {
            if (!identity.implementationPresent || !identity.publicKatScopeOnly) {
                add(SkaldVaultV1ProviderLevelPublicKatEvidenceFailureLabel.TestOnlyProviderIdentityMissing)
            }
            if (!desktopProviderLevelKatExecuted || !androidProviderLevelKatExecuted) {
                add(SkaldVaultV1ProviderLevelPublicKatEvidenceFailureLabel.ProviderLevelKatExecutionMissing)
            }
            if (!kdfProviderKatPassed || !aeadProviderKatPassed) {
                add(SkaldVaultV1ProviderLevelPublicKatEvidenceFailureLabel.ProviderLevelKatFailed)
            }
            if (providerSelectionEnabled) {
                add(SkaldVaultV1ProviderLevelPublicKatEvidenceFailureLabel.ProviderSelectionEnabled)
            }
            if (productionProviderSelectable) {
                add(SkaldVaultV1ProviderLevelPublicKatEvidenceFailureLabel.ProductionProviderSelectable)
            }
            if (
                productionProviderImplementationPresent ||
                productionProviderRegistryEntryPresent ||
                productionProviderFactoryPresent ||
                productionProviderDispatcherPresent ||
                productionExecutorTargetPresent
            ) {
                add(SkaldVaultV1ProviderLevelPublicKatEvidenceFailureLabel.ProductionProviderSurfacePresent)
            }
            if (
                vaultPersistencePresent ||
                secureStorageSuccessPathPresent ||
                secureMetadataSuccessPathPresent ||
                productionSyncPresent ||
                signingBroadcastingPresent ||
                uiPresent ||
                endpointPresent ||
                mainnetPresent
            ) {
                add(SkaldVaultV1ProviderLevelPublicKatEvidenceFailureLabel.ForbiddenVaultOrRuntimeSurfacePresent)
            }
            if (providerSelectionAuthorizationPresent || productionAuthorizationPresent) {
                add(SkaldVaultV1ProviderLevelPublicKatEvidenceFailureLabel.AuthorizationPresent)
            }
            if (
                !identity.buildHistoryExcludedFromNormalSourceMaterialCorpus ||
                !identity.localArtifactRootExcludedFromNormalSourceMaterialCorpus
            ) {
                add(SkaldVaultV1ProviderLevelPublicKatEvidenceFailureLabel.CorpusBoundaryMissing)
            }
        }

        return SkaldVaultV1ProviderLevelPublicKatEvidence(
            providerLevelKatEvidenceId = SkaldVaultV1ProviderLevelPublicKatEvidenceSafeLabel(
                "skald-test-only-provider-level-public-kat-execution-v1",
            ),
            providerLevelKatExecutionVersion = 1,
            providerKind = identity.providerKind,
            sourceScope = identity.sourceScope,
            desktopProviderLevelKatExecuted = desktopProviderLevelKatExecuted,
            androidProviderLevelKatExecuted = androidProviderLevelKatExecuted,
            kdfProviderKatExecuted = kdfProviderKatExecuted,
            kdfProviderKatPassed = kdfProviderKatPassed,
            aeadProviderKatExecuted = aeadProviderKatExecuted,
            aeadProviderKatPassed = aeadProviderKatPassed,
            publicKatScopeOnly = identity.publicKatScopeOnly,
            providerSelectionEnabled = providerSelectionEnabled,
            productionProviderSelectable = productionProviderSelectable,
            productionProviderImplementationPresent = productionProviderImplementationPresent,
            productionProviderRegistryEntryPresent = productionProviderRegistryEntryPresent,
            productionProviderFactoryPresent = productionProviderFactoryPresent,
            productionProviderDispatcherPresent = productionProviderDispatcherPresent,
            productionExecutorTargetPresent = productionExecutorTargetPresent,
            providerSelectionAuthorizationPresent = providerSelectionAuthorizationPresent,
            productionAuthorizationPresent = productionAuthorizationPresent,
            vaultPersistencePresent = vaultPersistencePresent,
            secureStorageSuccessPathPresent = secureStorageSuccessPathPresent,
            secureMetadataSuccessPathPresent = secureMetadataSuccessPathPresent,
            productionSyncPresent = productionSyncPresent,
            signingBroadcastingPresent = signingBroadcastingPresent,
            uiPresent = uiPresent,
            endpointPresent = endpointPresent,
            mainnetPresent = mainnetPresent,
            testOnlyProviderLevelKatExecutionPassed = testOnlyProviderLevelKatExecutionPassed,
            futureProviderSelectionEnablementRequiresSeparatePass =
                identity.futureProviderSelectionEnablementRequiresSeparatePass,
            buildHistoryExcludedFromNormalSourceMaterialCorpus =
                identity.buildHistoryExcludedFromNormalSourceMaterialCorpus,
            localArtifactRootExcludedFromNormalSourceMaterialCorpus =
                identity.localArtifactRootExcludedFromNormalSourceMaterialCorpus,
            evidenceCount = 12,
            blockerCount = failures.size,
            warningCount = 0,
            evidenceChecks = checks,
            failureLabels = failures,
            displayLabel = SkaldVaultV1ProviderLevelPublicKatEvidenceSafeLabel(
                "test-only provider-level public KAT execution evidence",
            ),
        )
    }
}
