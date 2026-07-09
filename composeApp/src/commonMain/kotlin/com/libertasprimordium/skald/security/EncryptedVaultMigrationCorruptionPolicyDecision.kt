package com.libertasprimordium.skald.security

@JvmInline
value class EncryptedVaultMigrationCorruptionPolicySafeLabel(val value: String) {
    override fun toString(): String = "RedactedEncryptedVaultMigrationCorruptionPolicySafeLabel"
}

enum class EncryptedVaultMigrationCorruptionPolicyDecisionKind(val label: String) {
    EncryptedLocalVaultMigrationCorruptionPolicyDecision(
        "ENCRYPTED_LOCAL_VAULT_MIGRATION_CORRUPTION_POLICY_DECISION",
    ),
}

enum class EncryptedVaultMigrationCorruptionPolicyDecisionSourceSet(val label: String) {
    CommonMainPolicy("COMMON_MAIN_POLICY"),
}

enum class EncryptedVaultMigrationCorruptionPolicyLabel(
    val safeLabel: EncryptedVaultMigrationCorruptionPolicySafeLabel,
) {
    MigrationPolicy(
        EncryptedVaultMigrationCorruptionPolicySafeLabel(
            "skald-encrypted-local-vault-migration-policy-v1",
        ),
    ),
    CorruptionPolicy(
        EncryptedVaultMigrationCorruptionPolicySafeLabel(
            "skald-encrypted-local-vault-corruption-policy-v1",
        ),
    ),
    AuthenticateBeforeMigrationPolicy(
        EncryptedVaultMigrationCorruptionPolicySafeLabel(
            "skald-vault-v1-authenticate-before-migration-policy",
        ),
    ),
    MigrationDryRunRequiredPolicy(
        EncryptedVaultMigrationCorruptionPolicySafeLabel(
            "skald-vault-v1-migration-dry-run-required-policy",
        ),
    ),
    MigrationBackupPreconditionPolicy(
        EncryptedVaultMigrationCorruptionPolicySafeLabel(
            "skald-vault-v1-migration-backup-precondition-policy",
        ),
    ),
    MigrationRollbackPolicy(
        EncryptedVaultMigrationCorruptionPolicySafeLabel(
            "skald-vault-v1-migration-rollback-policy",
        ),
    ),
    FailClosedCorruptionPolicy(
        EncryptedVaultMigrationCorruptionPolicySafeLabel(
            "skald-vault-v1-fail-closed-corruption-policy",
        ),
    ),
    NoDestructiveRepairDefaultPolicy(
        EncryptedVaultMigrationCorruptionPolicySafeLabel(
            "skald-vault-v1-no-destructive-repair-default-policy",
        ),
    ),
    RedactedCorruptionDiagnosticsPolicy(
        EncryptedVaultMigrationCorruptionPolicySafeLabel(
            "skald-vault-v1-redacted-corruption-diagnostics-policy",
        ),
    ),
    AtomicReplaceReferencePolicy(
        EncryptedVaultMigrationCorruptionPolicySafeLabel(
            "skald-vault-v1-atomic-replace-reference-policy",
        ),
    ),
    PartialWriteDetectionPolicy(
        EncryptedVaultMigrationCorruptionPolicySafeLabel(
            "skald-vault-v1-partial-write-detection-policy",
        ),
    ),
}

enum class EncryptedVaultMigrationCorruptionPolicyDecisionCheck {
    StorageReadinessDecisionPresent,
    ContainerFormatV1DecisionPresent,
    StoragePathSessionLifecycleDecisionPresent,
    EncryptedVaultDesignPresent,
    CryptoDecisionPresent,
    DependencyReviewPresent,
    ProviderBoundaryPresent,
    SecureStorageBoundaryPresent,
    SecureMetadataBoundaryPresent,
    VaultMigrationCorruptionPolicyDecisionAdmitted,
    AuthenticateBeforeMigrationPolicyAdmitted,
    MigrationDryRunPolicyAdmitted,
    MigrationBackupPreconditionPolicyAdmitted,
    MigrationRollbackPolicyAdmitted,
    FailClosedCorruptionPolicyAdmitted,
    NoDestructiveRepairDefaultPolicyAdmitted,
    RedactedCorruptionDiagnosticsPolicyAdmitted,
    AtomicReplacePolicyReferenceAdmitted,
    PartialWriteDetectionPolicyAdmitted,
    FutureMigrationImplementationRequiresSeparatePass,
    FutureCorruptionDetectionImplementationRequiresSeparatePass,
    FutureVaultContainerParserRequiresSeparatePass,
    FutureVaultContainerWriterRequiresSeparatePass,
    FutureVaultStorageRepositoryRequiresSeparatePass,
    FutureSecureStorageSuccessRequiresSeparatePass,
    FutureSecureMetadataSuccessRequiresSeparatePass,
    FutureProductionSyncRequiresSeparatePass,
    FutureProductionProviderSelectionRequiresSeparatePass,
    MigrationImplementationAbsent,
    MigrationExecutionAbsent,
    CorruptionDetectionImplementationAbsent,
    CorruptionRepairImplementationAbsent,
    BackupCreationAbsent,
    RollbackImplementationAbsent,
    AtomicReplaceImplementationAbsent,
    PartialWriteDetectionImplementationAbsent,
    VaultStoragePathImplementationAbsent,
    VaultDirectoryNotCreated,
    VaultFileReadAbsent,
    VaultFileWriteAbsent,
    VaultFileDeleteAbsent,
    VaultContainerParserAbsent,
    VaultContainerWriterAbsent,
    VaultContainerSerializationAbsent,
    VaultContainerParsingAbsent,
    VaultContainerBytesNotProduced,
    EncryptedVaultFileFormatNotImplemented,
    EncryptedVaultRepositorySuccessAbsent,
    LockSessionImplementationAbsent,
    UnlockImplementationAbsent,
    RuntimeSessionKeyAbsent,
    SessionKeyNotCached,
    PlaintextCacheAbsent,
    SecureSecretStorageSuccessPathAbsent,
    SecureMetadataStorageSuccessPathAbsent,
    ProductionObservationPersistenceAbsent,
    ProductionAddressIndexPersistenceAbsent,
    ProductionUtxoPersistenceAbsent,
    ProductionWalletHistoryPersistenceAbsent,
    ProductionSyncAbsent,
    ProductionBackendClientAbsent,
    ProductionProviderSelectionDisabled,
    ProductionProviderNotSelectable,
    ProductionSelectionStillDisabledProviderOnly,
    SigningBroadcastingAbsent,
    UiActionEnablementAbsent,
    EndpointAbsent,
    MainnetAbsent,
    MigrationImplementationAuthorizationAbsent,
    CorruptionDetectionAuthorizationAbsent,
    CorruptionRepairAuthorizationAbsent,
    BackupCreationAuthorizationAbsent,
    RollbackAuthorizationAbsent,
    AtomicReplaceAuthorizationAbsent,
    PartialWriteDetectionAuthorizationAbsent,
    VaultStoragePathImplementationAuthorizationAbsent,
    VaultDirectoryCreationAuthorizationAbsent,
    VaultFileReadAuthorizationAbsent,
    VaultFileWriteAuthorizationAbsent,
    VaultFileDeleteAuthorizationAbsent,
    VaultSessionImplementationAuthorizationAbsent,
    VaultUnlockAuthorizationAbsent,
    VaultLockAuthorizationAbsent,
    VaultContainerImplementationAuthorizationAbsent,
    VaultContainerParserAuthorizationAbsent,
    VaultContainerWriterAuthorizationAbsent,
    ProductionStorageAuthorizationAbsent,
    ProductionSecretStorageAuthorizationAbsent,
    ProductionMetadataStorageAuthorizationAbsent,
    ProductionSyncAuthorizationAbsent,
    ProductionProviderSelectionAuthorizationAbsent,
    ProductionProviderImplementationAuthorizationAbsent,
    SigningBroadcastingAuthorizationAbsent,
    UiAuthorizationAbsent,
    EndpointAuthorizationAbsent,
    MainnetAuthorizationAbsent,
    MigrationPolicyCreatesNoPayloadPlan,
    CorruptionPolicyCreatesNoRepairObject,
    AtomicReplacePolicyImplementsNoFileIo,
    DiagnosticsPolicyContainsNoSensitiveMaterial,
    BuildHistoryExcludedFromNormalSourceMaterialCorpus,
    LocalArtifactRootExcludedFromNormalSourceMaterialCorpus,
}

enum class EncryptedVaultMigrationCorruptionPolicyFailureLabel {
    ExistingEvidenceMissing,
    MigrationCorruptionAdmissionMissing,
    FutureSeparatePassGateMissing,
    MigrationRuntimeSurfacePresent,
    CorruptionRuntimeSurfacePresent,
    BackupRollbackAtomicRuntimeSurfacePresent,
    StoragePathRuntimeSurfacePresent,
    ContainerRuntimeSurfacePresent,
    LockSessionRuntimeSurfacePresent,
    ProductionStorageSurfacePresent,
    ProductionSyncSurfacePresent,
    ProductionProviderSelectionSurfacePresent,
    SigningBroadcastingUiEndpointOrMainnetSurfacePresent,
    ProductionAuthorizationPresent,
    PolicyMaterialPresent,
    CorpusBoundaryMissing,
}

data class EncryptedVaultMigrationCorruptionPolicyDecision(
    val decisionId: EncryptedVaultMigrationCorruptionPolicySafeLabel,
    val decisionVersion: Int,
    val decisionKind: EncryptedVaultMigrationCorruptionPolicyDecisionKind,
    val sourceSet: EncryptedVaultMigrationCorruptionPolicyDecisionSourceSet,
    val storageReadinessDecisionPresent: Boolean,
    val containerFormatV1DecisionPresent: Boolean,
    val storagePathSessionLifecycleDecisionPresent: Boolean,
    val encryptedVaultDesignPresent: Boolean,
    val cryptoDecisionPresent: Boolean,
    val dependencyReviewPresent: Boolean,
    val providerBoundaryPresent: Boolean,
    val secureStorageBoundaryPresent: Boolean,
    val secureMetadataBoundaryPresent: Boolean,
    val vaultMigrationCorruptionPolicyDecisionAdmitted: Boolean,
    val authenticateBeforeMigrationPolicyAdmitted: Boolean,
    val migrationDryRunPolicyAdmitted: Boolean,
    val migrationBackupPreconditionPolicyAdmitted: Boolean,
    val migrationRollbackPolicyAdmitted: Boolean,
    val failClosedCorruptionPolicyAdmitted: Boolean,
    val noDestructiveRepairDefaultPolicyAdmitted: Boolean,
    val redactedCorruptionDiagnosticsPolicyAdmitted: Boolean,
    val atomicReplacePolicyReferenceAdmitted: Boolean,
    val partialWriteDetectionPolicyAdmitted: Boolean,
    val futureMigrationImplementationRequiresSeparatePass: Boolean,
    val futureCorruptionDetectionImplementationRequiresSeparatePass: Boolean,
    val futureVaultContainerParserRequiresSeparatePass: Boolean,
    val futureVaultContainerWriterRequiresSeparatePass: Boolean,
    val futureVaultStorageRepositoryRequiresSeparatePass: Boolean,
    val futureSecureStorageSuccessRequiresSeparatePass: Boolean,
    val futureSecureMetadataSuccessRequiresSeparatePass: Boolean,
    val futureProductionSyncRequiresSeparatePass: Boolean,
    val futureProductionProviderSelectionRequiresSeparatePass: Boolean,
    val migrationImplementationPresent: Boolean,
    val migrationExecutionPresent: Boolean,
    val corruptionDetectionImplementationPresent: Boolean,
    val corruptionRepairImplementationPresent: Boolean,
    val backupCreationPresent: Boolean,
    val rollbackImplementationPresent: Boolean,
    val atomicReplaceImplementationPresent: Boolean,
    val partialWriteDetectionImplementationPresent: Boolean,
    val vaultStoragePathImplementationPresent: Boolean,
    val vaultDirectoryCreated: Boolean,
    val vaultFileReadPresent: Boolean,
    val vaultFileWritePresent: Boolean,
    val vaultFileDeletePresent: Boolean,
    val vaultContainerParserPresent: Boolean,
    val vaultContainerWriterPresent: Boolean,
    val vaultContainerSerializationPresent: Boolean,
    val vaultContainerParsingPresent: Boolean,
    val vaultContainerBytesProduced: Boolean,
    val encryptedVaultFileFormatImplemented: Boolean,
    val encryptedVaultRepositorySuccessPresent: Boolean,
    val lockSessionImplementationPresent: Boolean,
    val unlockImplementationPresent: Boolean,
    val runtimeSessionKeyPresent: Boolean,
    val sessionKeyCached: Boolean,
    val plaintextCachePresent: Boolean,
    val secureSecretStorageSuccessPathPresent: Boolean,
    val secureMetadataStorageSuccessPathPresent: Boolean,
    val productionObservationPersistencePresent: Boolean,
    val productionAddressIndexPersistencePresent: Boolean,
    val productionUtxoPersistencePresent: Boolean,
    val productionWalletHistoryPersistencePresent: Boolean,
    val productionSyncPresent: Boolean,
    val productionBackendClientPresent: Boolean,
    val productionProviderSelectionEnabled: Boolean,
    val productionProviderSelectable: Boolean,
    val productionSelectionStillDisabledProviderOnly: Boolean,
    val signingBroadcastingPresent: Boolean,
    val uiActionEnablementPresent: Boolean,
    val endpointPresent: Boolean,
    val mainnetPresent: Boolean,
    val migrationImplementationAuthorizationPresent: Boolean,
    val corruptionDetectionAuthorizationPresent: Boolean,
    val corruptionRepairAuthorizationPresent: Boolean,
    val backupCreationAuthorizationPresent: Boolean,
    val rollbackAuthorizationPresent: Boolean,
    val atomicReplaceAuthorizationPresent: Boolean,
    val partialWriteDetectionAuthorizationPresent: Boolean,
    val vaultStoragePathImplementationAuthorizationPresent: Boolean,
    val vaultDirectoryCreationAuthorizationPresent: Boolean,
    val vaultFileReadAuthorizationPresent: Boolean,
    val vaultFileWriteAuthorizationPresent: Boolean,
    val vaultFileDeleteAuthorizationPresent: Boolean,
    val vaultSessionImplementationAuthorizationPresent: Boolean,
    val vaultUnlockAuthorizationPresent: Boolean,
    val vaultLockAuthorizationPresent: Boolean,
    val vaultContainerImplementationAuthorizationPresent: Boolean,
    val vaultContainerParserAuthorizationPresent: Boolean,
    val vaultContainerWriterAuthorizationPresent: Boolean,
    val productionStorageAuthorizationPresent: Boolean,
    val productionSecretStorageAuthorizationPresent: Boolean,
    val productionMetadataStorageAuthorizationPresent: Boolean,
    val productionSyncAuthorizationPresent: Boolean,
    val productionProviderSelectionAuthorizationPresent: Boolean,
    val productionProviderImplementationAuthorizationPresent: Boolean,
    val signingBroadcastingAuthorizationPresent: Boolean,
    val uiAuthorizationPresent: Boolean,
    val endpointAuthorizationPresent: Boolean,
    val mainnetAuthorizationPresent: Boolean,
    val vaultMigrationCorruptionPolicyDecisionAdmittedIsLaterBranchOnly: Boolean,
    val authenticateBeforeMigrationPolicyAdmittedIsNotAuthenticatedParserImplementation: Boolean,
    val migrationDryRunPolicyAdmittedIsNotMigrationExecution: Boolean,
    val migrationBackupPreconditionPolicyAdmittedIsNotBackupExportImplementation: Boolean,
    val migrationRollbackPolicyAdmittedIsNotRollbackImplementation: Boolean,
    val failClosedCorruptionPolicyAdmittedIsNotCorruptionDetectionImplementation: Boolean,
    val noDestructiveRepairDefaultPolicyAdmittedIsNotRepairImplementation: Boolean,
    val redactedCorruptionDiagnosticsPolicyAdmittedIsNotDiagnosticsImplementation: Boolean,
    val atomicReplacePolicyReferenceAdmittedIsNotFileWriteImplementation: Boolean,
    val partialWriteDetectionPolicyAdmittedIsNotPartialWriteDetectionImplementation: Boolean,
    val futureMigrationAuthenticatesSourceVaultBeforeTransformingRecords: Boolean,
    val futureMigrationDryRunBeforeWriting: Boolean,
    val futureMigrationRequiresBackupOrRecoveryPrecondition: Boolean,
    val futureMigrationRejectsUnknownCriticalRecordClasses: Boolean,
    val futureMigrationFailsClosedOnUnknownUnsupportedVersions: Boolean,
    val futureMigrationPreservesRecordClassAndKeySeparation: Boolean,
    val futureMigrationDiagnosticsRedacted: Boolean,
    val noMigrationRunsInThisBranch: Boolean,
    val futureCorruptionAuthenticatesBeforeTrustingMetadata: Boolean,
    val futureCorruptionHandlingFailsClosed: Boolean,
    val futureDestructiveRepairOptInOnly: Boolean,
    val futureCorruptionDiagnosticsSafeLabelsOnly: Boolean,
    val futurePartialWriteDiagnosticsDoNotExposeMaterial: Boolean,
    val noCorruptionDetectionOrRepairRunsInThisBranch: Boolean,
    val futureWriterRequiresReviewedAtomicReplaceStrategy: Boolean,
    val futureWriterDoesNotExposeTempNamesOrPaths: Boolean,
    val futureWriterDistinguishesFailureClasses: Boolean,
    val noWriterOrAtomicReplaceInThisBranch: Boolean,
    val migrationPolicyCreatesNoPayloadPlan: Boolean,
    val corruptionPolicyCreatesNoRepairObject: Boolean,
    val atomicReplacePolicyImplementsNoFileIo: Boolean,
    val diagnosticsPolicyContainsNoSensitiveMaterial: Boolean,
    val normalSourceMaterialGuardExcludesBuildHistory: Boolean,
    val localArtifactRootExcludedFromNormalSourceMaterialCorpus: Boolean,
    val migrationCorruptionPolicyDecisionPassed: Boolean,
    val evidenceCount: Int,
    val decisionCheckCount: Int,
    val blockerCount: Int,
    val warningCount: Int,
    val policyLabels: List<EncryptedVaultMigrationCorruptionPolicyLabel>,
    val decisionChecks: List<EncryptedVaultMigrationCorruptionPolicyDecisionCheck>,
    val failureLabels: List<EncryptedVaultMigrationCorruptionPolicyFailureLabel>,
    val displayLabel: EncryptedVaultMigrationCorruptionPolicySafeLabel,
) {
    override fun toString(): String =
        "EncryptedVaultMigrationCorruptionPolicyDecision(" +
            "REDACTED, COMMON_MAIN_POLICY, MIGRATION_CORRUPTION_POLICY_DECISION_ONLY, " +
            "LATER_BRANCHES_ONLY, POLICY_LABELS_ONLY, NO_MIGRATION_EXECUTION, " +
            "NO_CORRUPTION_REPAIR, NO_BACKUP_ROLLBACK_ATOMIC_REPLACE, NO_STORAGE_IO, " +
            "NO_CONTAINER_IO, NO_RUNTIME_SESSION, NO_SECURE_STORAGE_SUCCESS, " +
            "NO_PRODUCTION_SYNC, DISABLED_PROVIDER_ONLY, " +
            "NO_SIGNING_BROADCASTING_UI_ENDPOINT_MAINNET" +
            ")"
}

object EncryptedVaultMigrationCorruptionPolicyDecisionPolicy {
    fun currentMigrationCorruptionPolicyDecision(): EncryptedVaultMigrationCorruptionPolicyDecision {
        val storageDecision =
            EncryptedVaultStorageReadinessDecisionPolicy.currentStorageReadinessDecision()
        val containerDecision =
            EncryptedVaultContainerFormatV1DecisionPolicy.currentContainerFormatV1Decision()
        val storagePathDecision =
            EncryptedVaultStoragePathSessionLifecycleDecisionPolicy
                .currentStoragePathSessionLifecycleDecision()
        val providerSelection = VaultCryptoProviderSelectionRegistry.select()
        val checks = EncryptedVaultMigrationCorruptionPolicyDecisionCheck.entries.toList()
        val policyLabels = EncryptedVaultMigrationCorruptionPolicyLabel.entries.toList()

        val storageReadinessDecisionPresent = storageDecision.storageReadinessDecisionPassed
        val containerFormatV1DecisionPresent = containerDecision.containerFormatV1DecisionPassed
        val storagePathSessionLifecycleDecisionPresent =
            storagePathDecision.storagePathSessionLifecycleDecisionPassed
        val encryptedVaultDesignPresent =
            storageDecision.encryptedVaultDesignPresent &&
                containerDecision.encryptedVaultDesignPresent &&
                storagePathDecision.encryptedVaultDesignPresent
        val cryptoDecisionPresent =
            storageDecision.cryptoDecisionPresent &&
                containerDecision.cryptoDecisionPresent &&
                storagePathDecision.cryptoDecisionPresent
        val dependencyReviewPresent =
            storageDecision.dependencyReviewPresent &&
                containerDecision.dependencyReviewPresent &&
                storagePathDecision.dependencyReviewPresent
        val providerBoundaryPresent =
            storageDecision.disabledVaultCryptoProviderBoundaryPresent &&
                containerDecision.vaultCryptoProviderBoundaryPresent &&
                storagePathDecision.providerBoundaryPresent
        val secureStorageBoundaryPresent =
            storageDecision.secureStorageBoundaryPresent &&
                storagePathDecision.secureStorageBoundaryPresent
        val secureMetadataBoundaryPresent =
            storageDecision.secureMetadataBoundaryPresent &&
                storagePathDecision.secureMetadataBoundaryPresent

        val vaultMigrationCorruptionPolicyDecisionAdmitted = true
        val authenticateBeforeMigrationPolicyAdmitted = true
        val migrationDryRunPolicyAdmitted = true
        val migrationBackupPreconditionPolicyAdmitted = true
        val migrationRollbackPolicyAdmitted = true
        val failClosedCorruptionPolicyAdmitted = true
        val noDestructiveRepairDefaultPolicyAdmitted = true
        val redactedCorruptionDiagnosticsPolicyAdmitted = true
        val atomicReplacePolicyReferenceAdmitted = true
        val partialWriteDetectionPolicyAdmitted = true

        val futureMigrationImplementationRequiresSeparatePass = true
        val futureCorruptionDetectionImplementationRequiresSeparatePass = true
        val futureVaultContainerParserRequiresSeparatePass =
            storagePathDecision.futureVaultContainerParserRequiresSeparatePass
        val futureVaultContainerWriterRequiresSeparatePass =
            storagePathDecision.futureVaultContainerWriterRequiresSeparatePass
        val futureVaultStorageRepositoryRequiresSeparatePass =
            storagePathDecision.futureVaultStorageRepositoryRequiresSeparatePass
        val futureSecureStorageSuccessRequiresSeparatePass =
            storagePathDecision.futureSecureStorageSuccessRequiresSeparatePass
        val futureSecureMetadataSuccessRequiresSeparatePass =
            storagePathDecision.futureSecureMetadataSuccessRequiresSeparatePass
        val futureProductionSyncRequiresSeparatePass =
            storagePathDecision.futureProductionSyncRequiresSeparatePass
        val futureProductionProviderSelectionRequiresSeparatePass =
            storagePathDecision.futureProductionProviderSelectionRequiresSeparatePass

        val migrationImplementationPresent = false
        val migrationExecutionPresent = false
        val corruptionDetectionImplementationPresent = false
        val corruptionRepairImplementationPresent = false
        val backupCreationPresent = false
        val rollbackImplementationPresent = false
        val atomicReplaceImplementationPresent = false
        val partialWriteDetectionImplementationPresent = false
        val vaultStoragePathImplementationPresent =
            storagePathDecision.vaultStoragePathImplementationPresent
        val vaultDirectoryCreated = storagePathDecision.vaultDirectoryCreated
        val vaultFileReadPresent = storagePathDecision.vaultFileReadPresent
        val vaultFileWritePresent = storagePathDecision.vaultFileWritePresent
        val vaultFileDeletePresent = storagePathDecision.vaultFileDeletePresent
        val vaultContainerParserPresent = storagePathDecision.vaultContainerParserPresent
        val vaultContainerWriterPresent = storagePathDecision.vaultContainerWriterPresent
        val vaultContainerSerializationPresent =
            storagePathDecision.vaultContainerSerializationPresent
        val vaultContainerParsingPresent = storagePathDecision.vaultContainerParsingPresent
        val vaultContainerBytesProduced = storagePathDecision.vaultContainerBytesProduced
        val encryptedVaultFileFormatImplemented =
            storagePathDecision.encryptedVaultFileFormatImplemented
        val encryptedVaultRepositorySuccessPresent =
            storagePathDecision.encryptedVaultRepositorySuccessPresent
        val lockSessionImplementationPresent = storagePathDecision.lockSessionImplementationPresent
        val unlockImplementationPresent = storagePathDecision.unlockImplementationPresent
        val runtimeSessionKeyPresent = storagePathDecision.runtimeSessionKeyPresent
        val sessionKeyCached = storagePathDecision.sessionKeyCached
        val plaintextCachePresent = storagePathDecision.plaintextCachePresent
        val secureSecretStorageSuccessPathPresent =
            storagePathDecision.secureSecretStorageSuccessPathPresent
        val secureMetadataStorageSuccessPathPresent =
            storagePathDecision.secureMetadataStorageSuccessPathPresent
        val productionObservationPersistencePresent =
            storagePathDecision.productionObservationPersistencePresent
        val productionAddressIndexPersistencePresent =
            storagePathDecision.productionAddressIndexPersistencePresent
        val productionUtxoPersistencePresent =
            storagePathDecision.productionUtxoPersistencePresent
        val productionWalletHistoryPersistencePresent =
            storagePathDecision.productionWalletHistoryPersistencePresent
        val productionSyncPresent = storagePathDecision.productionSyncPresent
        val productionBackendClientPresent = storagePathDecision.productionBackendClientPresent
        val productionProviderSelectionEnabled = !providerSelection.selectedProviderIsDisabled
        val productionProviderSelectable = providerSelection.productionProviderSelectable
        val productionSelectionStillDisabledProviderOnly =
            providerSelection.selectedProviderIsDisabled &&
                !productionProviderSelectionEnabled &&
                !productionProviderSelectable
        val signingBroadcastingPresent = storagePathDecision.signingBroadcastingPresent
        val uiActionEnablementPresent = storagePathDecision.uiActionEnablementPresent
        val endpointPresent = storagePathDecision.endpointPresent
        val mainnetPresent = storagePathDecision.mainnetPresent

        val migrationImplementationAuthorizationPresent = false
        val corruptionDetectionAuthorizationPresent = false
        val corruptionRepairAuthorizationPresent = false
        val backupCreationAuthorizationPresent = false
        val rollbackAuthorizationPresent = false
        val atomicReplaceAuthorizationPresent = false
        val partialWriteDetectionAuthorizationPresent = false
        val vaultStoragePathImplementationAuthorizationPresent = false
        val vaultDirectoryCreationAuthorizationPresent = false
        val vaultFileReadAuthorizationPresent = false
        val vaultFileWriteAuthorizationPresent = false
        val vaultFileDeleteAuthorizationPresent = false
        val vaultSessionImplementationAuthorizationPresent = false
        val vaultUnlockAuthorizationPresent = false
        val vaultLockAuthorizationPresent = false
        val vaultContainerImplementationAuthorizationPresent = false
        val vaultContainerParserAuthorizationPresent = false
        val vaultContainerWriterAuthorizationPresent = false
        val productionStorageAuthorizationPresent = false
        val productionSecretStorageAuthorizationPresent = false
        val productionMetadataStorageAuthorizationPresent = false
        val productionSyncAuthorizationPresent = false
        val productionProviderSelectionAuthorizationPresent = false
        val productionProviderImplementationAuthorizationPresent = false
        val signingBroadcastingAuthorizationPresent = false
        val uiAuthorizationPresent = false
        val endpointAuthorizationPresent = false
        val mainnetAuthorizationPresent = false

        val vaultMigrationCorruptionPolicyDecisionAdmittedIsLaterBranchOnly =
            vaultMigrationCorruptionPolicyDecisionAdmitted &&
                futureMigrationImplementationRequiresSeparatePass &&
                futureCorruptionDetectionImplementationRequiresSeparatePass &&
                !migrationImplementationPresent &&
                !corruptionDetectionImplementationPresent
        val authenticateBeforeMigrationPolicyAdmittedIsNotAuthenticatedParserImplementation =
            authenticateBeforeMigrationPolicyAdmitted &&
                !vaultContainerParserPresent &&
                !vaultContainerParsingPresent
        val migrationDryRunPolicyAdmittedIsNotMigrationExecution =
            migrationDryRunPolicyAdmitted &&
                !migrationImplementationPresent &&
                !migrationExecutionPresent
        val migrationBackupPreconditionPolicyAdmittedIsNotBackupExportImplementation =
            migrationBackupPreconditionPolicyAdmitted &&
                !backupCreationPresent &&
                !productionStorageAuthorizationPresent
        val migrationRollbackPolicyAdmittedIsNotRollbackImplementation =
            migrationRollbackPolicyAdmitted &&
                !rollbackImplementationPresent &&
                !vaultFileWritePresent
        val failClosedCorruptionPolicyAdmittedIsNotCorruptionDetectionImplementation =
            failClosedCorruptionPolicyAdmitted &&
                !corruptionDetectionImplementationPresent &&
                !vaultContainerParsingPresent
        val noDestructiveRepairDefaultPolicyAdmittedIsNotRepairImplementation =
            noDestructiveRepairDefaultPolicyAdmitted &&
                !corruptionRepairImplementationPresent &&
                !vaultFileWritePresent
        val redactedCorruptionDiagnosticsPolicyAdmittedIsNotDiagnosticsImplementation =
            redactedCorruptionDiagnosticsPolicyAdmitted &&
                !corruptionDetectionImplementationPresent &&
                !corruptionRepairImplementationPresent
        val atomicReplacePolicyReferenceAdmittedIsNotFileWriteImplementation =
            atomicReplacePolicyReferenceAdmitted &&
                !atomicReplaceImplementationPresent &&
                !vaultFileWritePresent
        val partialWriteDetectionPolicyAdmittedIsNotPartialWriteDetectionImplementation =
            partialWriteDetectionPolicyAdmitted &&
                !partialWriteDetectionImplementationPresent &&
                !vaultFileReadPresent

        val futureMigrationAuthenticatesSourceVaultBeforeTransformingRecords = true
        val futureMigrationDryRunBeforeWriting = true
        val futureMigrationRequiresBackupOrRecoveryPrecondition = true
        val futureMigrationRejectsUnknownCriticalRecordClasses = true
        val futureMigrationFailsClosedOnUnknownUnsupportedVersions = true
        val futureMigrationPreservesRecordClassAndKeySeparation = true
        val futureMigrationDiagnosticsRedacted = true
        val noMigrationRunsInThisBranch = !migrationExecutionPresent
        val futureCorruptionAuthenticatesBeforeTrustingMetadata = true
        val futureCorruptionHandlingFailsClosed = true
        val futureDestructiveRepairOptInOnly = true
        val futureCorruptionDiagnosticsSafeLabelsOnly = true
        val futurePartialWriteDiagnosticsDoNotExposeMaterial = true
        val noCorruptionDetectionOrRepairRunsInThisBranch =
            !corruptionDetectionImplementationPresent &&
                !corruptionRepairImplementationPresent
        val futureWriterRequiresReviewedAtomicReplaceStrategy = true
        val futureWriterDoesNotExposeTempNamesOrPaths = true
        val futureWriterDistinguishesFailureClasses = true
        val noWriterOrAtomicReplaceInThisBranch =
            !vaultContainerWriterPresent &&
                !atomicReplaceImplementationPresent
        val migrationPolicyCreatesNoPayloadPlan = !migrationImplementationPresent
        val corruptionPolicyCreatesNoRepairObject = !corruptionRepairImplementationPresent
        val atomicReplacePolicyImplementsNoFileIo =
            !atomicReplaceImplementationPresent &&
                !vaultFileReadPresent &&
                !vaultFileWritePresent &&
                !vaultFileDeletePresent
        val diagnosticsPolicyContainsNoSensitiveMaterial = policyLabels.all { policy ->
            val label = policy.safeLabel.value
            "/" !in label &&
                "\\" !in label &&
                "." !in label &&
                "~" !in label &&
                !label.contains("path", ignoreCase = true) &&
                !label.contains("file", ignoreCase = true) &&
                !label.contains("storage-key", ignoreCase = true) &&
                !label.contains("database", ignoreCase = true)
        }
        val normalSourceMaterialGuardExcludesBuildHistory = true
        val localArtifactRootExcludedFromNormalSourceMaterialCorpus = true

        val existingEvidencePresent =
            storageReadinessDecisionPresent &&
                containerFormatV1DecisionPresent &&
                storagePathSessionLifecycleDecisionPresent &&
                encryptedVaultDesignPresent &&
                cryptoDecisionPresent &&
                dependencyReviewPresent &&
                providerBoundaryPresent &&
                secureStorageBoundaryPresent &&
                secureMetadataBoundaryPresent
        val migrationCorruptionAdmissionPresent =
            vaultMigrationCorruptionPolicyDecisionAdmitted &&
                authenticateBeforeMigrationPolicyAdmitted &&
                migrationDryRunPolicyAdmitted &&
                migrationBackupPreconditionPolicyAdmitted &&
                migrationRollbackPolicyAdmitted &&
                failClosedCorruptionPolicyAdmitted &&
                noDestructiveRepairDefaultPolicyAdmitted &&
                redactedCorruptionDiagnosticsPolicyAdmitted &&
                atomicReplacePolicyReferenceAdmitted &&
                partialWriteDetectionPolicyAdmitted
        val futureSeparatePassGatesPresent =
            futureMigrationImplementationRequiresSeparatePass &&
                futureCorruptionDetectionImplementationRequiresSeparatePass &&
                futureVaultContainerParserRequiresSeparatePass &&
                futureVaultContainerWriterRequiresSeparatePass &&
                futureVaultStorageRepositoryRequiresSeparatePass &&
                futureSecureStorageSuccessRequiresSeparatePass &&
                futureSecureMetadataSuccessRequiresSeparatePass &&
                futureProductionSyncRequiresSeparatePass &&
                futureProductionProviderSelectionRequiresSeparatePass
        val migrationRuntimeSurfacePresent =
            migrationImplementationPresent || migrationExecutionPresent
        val corruptionRuntimeSurfacePresent =
            corruptionDetectionImplementationPresent || corruptionRepairImplementationPresent
        val backupRollbackAtomicRuntimeSurfacePresent =
            backupCreationPresent ||
                rollbackImplementationPresent ||
                atomicReplaceImplementationPresent ||
                partialWriteDetectionImplementationPresent
        val storagePathRuntimeSurfacePresent =
            vaultStoragePathImplementationPresent ||
                vaultDirectoryCreated ||
                vaultFileReadPresent ||
                vaultFileWritePresent ||
                vaultFileDeletePresent
        val containerRuntimeSurfacePresent =
            vaultContainerParserPresent ||
                vaultContainerWriterPresent ||
                vaultContainerSerializationPresent ||
                vaultContainerParsingPresent ||
                vaultContainerBytesProduced ||
                encryptedVaultFileFormatImplemented
        val lockSessionRuntimeSurfacePresent =
            lockSessionImplementationPresent ||
                unlockImplementationPresent ||
                runtimeSessionKeyPresent ||
                sessionKeyCached ||
                plaintextCachePresent
        val productionStorageSurfacePresent =
            encryptedVaultRepositorySuccessPresent ||
                secureSecretStorageSuccessPathPresent ||
                secureMetadataStorageSuccessPathPresent ||
                productionObservationPersistencePresent ||
                productionAddressIndexPersistencePresent ||
                productionUtxoPersistencePresent ||
                productionWalletHistoryPersistencePresent
        val productionSyncSurfacePresent = productionSyncPresent || productionBackendClientPresent
        val productionProviderSelectionSurfacePresent =
            productionProviderSelectionEnabled || productionProviderSelectable
        val signingBroadcastingUiEndpointOrMainnetSurfacePresent =
            signingBroadcastingPresent ||
                uiActionEnablementPresent ||
                endpointPresent ||
                mainnetPresent
        val productionAuthorizationPresent =
            migrationImplementationAuthorizationPresent ||
                corruptionDetectionAuthorizationPresent ||
                corruptionRepairAuthorizationPresent ||
                backupCreationAuthorizationPresent ||
                rollbackAuthorizationPresent ||
                atomicReplaceAuthorizationPresent ||
                partialWriteDetectionAuthorizationPresent ||
                vaultStoragePathImplementationAuthorizationPresent ||
                vaultDirectoryCreationAuthorizationPresent ||
                vaultFileReadAuthorizationPresent ||
                vaultFileWriteAuthorizationPresent ||
                vaultFileDeleteAuthorizationPresent ||
                vaultSessionImplementationAuthorizationPresent ||
                vaultUnlockAuthorizationPresent ||
                vaultLockAuthorizationPresent ||
                vaultContainerImplementationAuthorizationPresent ||
                vaultContainerParserAuthorizationPresent ||
                vaultContainerWriterAuthorizationPresent ||
                productionStorageAuthorizationPresent ||
                productionSecretStorageAuthorizationPresent ||
                productionMetadataStorageAuthorizationPresent ||
                productionSyncAuthorizationPresent ||
                productionProviderSelectionAuthorizationPresent ||
                productionProviderImplementationAuthorizationPresent ||
                signingBroadcastingAuthorizationPresent ||
                uiAuthorizationPresent ||
                endpointAuthorizationPresent ||
                mainnetAuthorizationPresent
        val policyMaterialAbsent =
            migrationPolicyCreatesNoPayloadPlan &&
                corruptionPolicyCreatesNoRepairObject &&
                atomicReplacePolicyImplementsNoFileIo &&
                diagnosticsPolicyContainsNoSensitiveMaterial
        val corpusBoundaryPresent =
            normalSourceMaterialGuardExcludesBuildHistory &&
                localArtifactRootExcludedFromNormalSourceMaterialCorpus

        val failures = buildList {
            if (!existingEvidencePresent) {
                add(EncryptedVaultMigrationCorruptionPolicyFailureLabel.ExistingEvidenceMissing)
            }
            if (!migrationCorruptionAdmissionPresent) {
                add(
                    EncryptedVaultMigrationCorruptionPolicyFailureLabel
                        .MigrationCorruptionAdmissionMissing,
                )
            }
            if (!futureSeparatePassGatesPresent) {
                add(EncryptedVaultMigrationCorruptionPolicyFailureLabel.FutureSeparatePassGateMissing)
            }
            if (migrationRuntimeSurfacePresent) {
                add(EncryptedVaultMigrationCorruptionPolicyFailureLabel.MigrationRuntimeSurfacePresent)
            }
            if (corruptionRuntimeSurfacePresent) {
                add(EncryptedVaultMigrationCorruptionPolicyFailureLabel.CorruptionRuntimeSurfacePresent)
            }
            if (backupRollbackAtomicRuntimeSurfacePresent) {
                add(
                    EncryptedVaultMigrationCorruptionPolicyFailureLabel
                        .BackupRollbackAtomicRuntimeSurfacePresent,
                )
            }
            if (storagePathRuntimeSurfacePresent) {
                add(EncryptedVaultMigrationCorruptionPolicyFailureLabel.StoragePathRuntimeSurfacePresent)
            }
            if (containerRuntimeSurfacePresent) {
                add(EncryptedVaultMigrationCorruptionPolicyFailureLabel.ContainerRuntimeSurfacePresent)
            }
            if (lockSessionRuntimeSurfacePresent) {
                add(EncryptedVaultMigrationCorruptionPolicyFailureLabel.LockSessionRuntimeSurfacePresent)
            }
            if (productionStorageSurfacePresent) {
                add(EncryptedVaultMigrationCorruptionPolicyFailureLabel.ProductionStorageSurfacePresent)
            }
            if (productionSyncSurfacePresent) {
                add(EncryptedVaultMigrationCorruptionPolicyFailureLabel.ProductionSyncSurfacePresent)
            }
            if (productionProviderSelectionSurfacePresent || !productionSelectionStillDisabledProviderOnly) {
                add(
                    EncryptedVaultMigrationCorruptionPolicyFailureLabel
                        .ProductionProviderSelectionSurfacePresent,
                )
            }
            if (signingBroadcastingUiEndpointOrMainnetSurfacePresent) {
                add(
                    EncryptedVaultMigrationCorruptionPolicyFailureLabel
                        .SigningBroadcastingUiEndpointOrMainnetSurfacePresent,
                )
            }
            if (productionAuthorizationPresent) {
                add(EncryptedVaultMigrationCorruptionPolicyFailureLabel.ProductionAuthorizationPresent)
            }
            if (!policyMaterialAbsent) {
                add(EncryptedVaultMigrationCorruptionPolicyFailureLabel.PolicyMaterialPresent)
            }
            if (!corpusBoundaryPresent) {
                add(EncryptedVaultMigrationCorruptionPolicyFailureLabel.CorpusBoundaryMissing)
            }
        }

        val migrationCorruptionPolicyDecisionPassed =
            failures.isEmpty() &&
                vaultMigrationCorruptionPolicyDecisionAdmittedIsLaterBranchOnly &&
                authenticateBeforeMigrationPolicyAdmittedIsNotAuthenticatedParserImplementation &&
                migrationDryRunPolicyAdmittedIsNotMigrationExecution &&
                migrationBackupPreconditionPolicyAdmittedIsNotBackupExportImplementation &&
                failClosedCorruptionPolicyAdmittedIsNotCorruptionDetectionImplementation &&
                atomicReplacePolicyReferenceAdmittedIsNotFileWriteImplementation &&
                partialWriteDetectionPolicyAdmittedIsNotPartialWriteDetectionImplementation

        return EncryptedVaultMigrationCorruptionPolicyDecision(
            decisionId = EncryptedVaultMigrationCorruptionPolicySafeLabel(
                "skald-encrypted-local-vault-migration-corruption-policy-decision-v1",
            ),
            decisionVersion = 1,
            decisionKind =
                EncryptedVaultMigrationCorruptionPolicyDecisionKind
                    .EncryptedLocalVaultMigrationCorruptionPolicyDecision,
            sourceSet = EncryptedVaultMigrationCorruptionPolicyDecisionSourceSet.CommonMainPolicy,
            storageReadinessDecisionPresent = storageReadinessDecisionPresent,
            containerFormatV1DecisionPresent = containerFormatV1DecisionPresent,
            storagePathSessionLifecycleDecisionPresent = storagePathSessionLifecycleDecisionPresent,
            encryptedVaultDesignPresent = encryptedVaultDesignPresent,
            cryptoDecisionPresent = cryptoDecisionPresent,
            dependencyReviewPresent = dependencyReviewPresent,
            providerBoundaryPresent = providerBoundaryPresent,
            secureStorageBoundaryPresent = secureStorageBoundaryPresent,
            secureMetadataBoundaryPresent = secureMetadataBoundaryPresent,
            vaultMigrationCorruptionPolicyDecisionAdmitted =
                vaultMigrationCorruptionPolicyDecisionAdmitted,
            authenticateBeforeMigrationPolicyAdmitted = authenticateBeforeMigrationPolicyAdmitted,
            migrationDryRunPolicyAdmitted = migrationDryRunPolicyAdmitted,
            migrationBackupPreconditionPolicyAdmitted = migrationBackupPreconditionPolicyAdmitted,
            migrationRollbackPolicyAdmitted = migrationRollbackPolicyAdmitted,
            failClosedCorruptionPolicyAdmitted = failClosedCorruptionPolicyAdmitted,
            noDestructiveRepairDefaultPolicyAdmitted = noDestructiveRepairDefaultPolicyAdmitted,
            redactedCorruptionDiagnosticsPolicyAdmitted =
                redactedCorruptionDiagnosticsPolicyAdmitted,
            atomicReplacePolicyReferenceAdmitted = atomicReplacePolicyReferenceAdmitted,
            partialWriteDetectionPolicyAdmitted = partialWriteDetectionPolicyAdmitted,
            futureMigrationImplementationRequiresSeparatePass =
                futureMigrationImplementationRequiresSeparatePass,
            futureCorruptionDetectionImplementationRequiresSeparatePass =
                futureCorruptionDetectionImplementationRequiresSeparatePass,
            futureVaultContainerParserRequiresSeparatePass =
                futureVaultContainerParserRequiresSeparatePass,
            futureVaultContainerWriterRequiresSeparatePass =
                futureVaultContainerWriterRequiresSeparatePass,
            futureVaultStorageRepositoryRequiresSeparatePass =
                futureVaultStorageRepositoryRequiresSeparatePass,
            futureSecureStorageSuccessRequiresSeparatePass =
                futureSecureStorageSuccessRequiresSeparatePass,
            futureSecureMetadataSuccessRequiresSeparatePass =
                futureSecureMetadataSuccessRequiresSeparatePass,
            futureProductionSyncRequiresSeparatePass = futureProductionSyncRequiresSeparatePass,
            futureProductionProviderSelectionRequiresSeparatePass =
                futureProductionProviderSelectionRequiresSeparatePass,
            migrationImplementationPresent = migrationImplementationPresent,
            migrationExecutionPresent = migrationExecutionPresent,
            corruptionDetectionImplementationPresent = corruptionDetectionImplementationPresent,
            corruptionRepairImplementationPresent = corruptionRepairImplementationPresent,
            backupCreationPresent = backupCreationPresent,
            rollbackImplementationPresent = rollbackImplementationPresent,
            atomicReplaceImplementationPresent = atomicReplaceImplementationPresent,
            partialWriteDetectionImplementationPresent = partialWriteDetectionImplementationPresent,
            vaultStoragePathImplementationPresent = vaultStoragePathImplementationPresent,
            vaultDirectoryCreated = vaultDirectoryCreated,
            vaultFileReadPresent = vaultFileReadPresent,
            vaultFileWritePresent = vaultFileWritePresent,
            vaultFileDeletePresent = vaultFileDeletePresent,
            vaultContainerParserPresent = vaultContainerParserPresent,
            vaultContainerWriterPresent = vaultContainerWriterPresent,
            vaultContainerSerializationPresent = vaultContainerSerializationPresent,
            vaultContainerParsingPresent = vaultContainerParsingPresent,
            vaultContainerBytesProduced = vaultContainerBytesProduced,
            encryptedVaultFileFormatImplemented = encryptedVaultFileFormatImplemented,
            encryptedVaultRepositorySuccessPresent = encryptedVaultRepositorySuccessPresent,
            lockSessionImplementationPresent = lockSessionImplementationPresent,
            unlockImplementationPresent = unlockImplementationPresent,
            runtimeSessionKeyPresent = runtimeSessionKeyPresent,
            sessionKeyCached = sessionKeyCached,
            plaintextCachePresent = plaintextCachePresent,
            secureSecretStorageSuccessPathPresent = secureSecretStorageSuccessPathPresent,
            secureMetadataStorageSuccessPathPresent = secureMetadataStorageSuccessPathPresent,
            productionObservationPersistencePresent = productionObservationPersistencePresent,
            productionAddressIndexPersistencePresent = productionAddressIndexPersistencePresent,
            productionUtxoPersistencePresent = productionUtxoPersistencePresent,
            productionWalletHistoryPersistencePresent = productionWalletHistoryPersistencePresent,
            productionSyncPresent = productionSyncPresent,
            productionBackendClientPresent = productionBackendClientPresent,
            productionProviderSelectionEnabled = productionProviderSelectionEnabled,
            productionProviderSelectable = productionProviderSelectable,
            productionSelectionStillDisabledProviderOnly = productionSelectionStillDisabledProviderOnly,
            signingBroadcastingPresent = signingBroadcastingPresent,
            uiActionEnablementPresent = uiActionEnablementPresent,
            endpointPresent = endpointPresent,
            mainnetPresent = mainnetPresent,
            migrationImplementationAuthorizationPresent =
                migrationImplementationAuthorizationPresent,
            corruptionDetectionAuthorizationPresent = corruptionDetectionAuthorizationPresent,
            corruptionRepairAuthorizationPresent = corruptionRepairAuthorizationPresent,
            backupCreationAuthorizationPresent = backupCreationAuthorizationPresent,
            rollbackAuthorizationPresent = rollbackAuthorizationPresent,
            atomicReplaceAuthorizationPresent = atomicReplaceAuthorizationPresent,
            partialWriteDetectionAuthorizationPresent = partialWriteDetectionAuthorizationPresent,
            vaultStoragePathImplementationAuthorizationPresent =
                vaultStoragePathImplementationAuthorizationPresent,
            vaultDirectoryCreationAuthorizationPresent = vaultDirectoryCreationAuthorizationPresent,
            vaultFileReadAuthorizationPresent = vaultFileReadAuthorizationPresent,
            vaultFileWriteAuthorizationPresent = vaultFileWriteAuthorizationPresent,
            vaultFileDeleteAuthorizationPresent = vaultFileDeleteAuthorizationPresent,
            vaultSessionImplementationAuthorizationPresent =
                vaultSessionImplementationAuthorizationPresent,
            vaultUnlockAuthorizationPresent = vaultUnlockAuthorizationPresent,
            vaultLockAuthorizationPresent = vaultLockAuthorizationPresent,
            vaultContainerImplementationAuthorizationPresent =
                vaultContainerImplementationAuthorizationPresent,
            vaultContainerParserAuthorizationPresent = vaultContainerParserAuthorizationPresent,
            vaultContainerWriterAuthorizationPresent = vaultContainerWriterAuthorizationPresent,
            productionStorageAuthorizationPresent = productionStorageAuthorizationPresent,
            productionSecretStorageAuthorizationPresent = productionSecretStorageAuthorizationPresent,
            productionMetadataStorageAuthorizationPresent = productionMetadataStorageAuthorizationPresent,
            productionSyncAuthorizationPresent = productionSyncAuthorizationPresent,
            productionProviderSelectionAuthorizationPresent =
                productionProviderSelectionAuthorizationPresent,
            productionProviderImplementationAuthorizationPresent =
                productionProviderImplementationAuthorizationPresent,
            signingBroadcastingAuthorizationPresent = signingBroadcastingAuthorizationPresent,
            uiAuthorizationPresent = uiAuthorizationPresent,
            endpointAuthorizationPresent = endpointAuthorizationPresent,
            mainnetAuthorizationPresent = mainnetAuthorizationPresent,
            vaultMigrationCorruptionPolicyDecisionAdmittedIsLaterBranchOnly =
                vaultMigrationCorruptionPolicyDecisionAdmittedIsLaterBranchOnly,
            authenticateBeforeMigrationPolicyAdmittedIsNotAuthenticatedParserImplementation =
                authenticateBeforeMigrationPolicyAdmittedIsNotAuthenticatedParserImplementation,
            migrationDryRunPolicyAdmittedIsNotMigrationExecution =
                migrationDryRunPolicyAdmittedIsNotMigrationExecution,
            migrationBackupPreconditionPolicyAdmittedIsNotBackupExportImplementation =
                migrationBackupPreconditionPolicyAdmittedIsNotBackupExportImplementation,
            migrationRollbackPolicyAdmittedIsNotRollbackImplementation =
                migrationRollbackPolicyAdmittedIsNotRollbackImplementation,
            failClosedCorruptionPolicyAdmittedIsNotCorruptionDetectionImplementation =
                failClosedCorruptionPolicyAdmittedIsNotCorruptionDetectionImplementation,
            noDestructiveRepairDefaultPolicyAdmittedIsNotRepairImplementation =
                noDestructiveRepairDefaultPolicyAdmittedIsNotRepairImplementation,
            redactedCorruptionDiagnosticsPolicyAdmittedIsNotDiagnosticsImplementation =
                redactedCorruptionDiagnosticsPolicyAdmittedIsNotDiagnosticsImplementation,
            atomicReplacePolicyReferenceAdmittedIsNotFileWriteImplementation =
                atomicReplacePolicyReferenceAdmittedIsNotFileWriteImplementation,
            partialWriteDetectionPolicyAdmittedIsNotPartialWriteDetectionImplementation =
                partialWriteDetectionPolicyAdmittedIsNotPartialWriteDetectionImplementation,
            futureMigrationAuthenticatesSourceVaultBeforeTransformingRecords =
                futureMigrationAuthenticatesSourceVaultBeforeTransformingRecords,
            futureMigrationDryRunBeforeWriting = futureMigrationDryRunBeforeWriting,
            futureMigrationRequiresBackupOrRecoveryPrecondition =
                futureMigrationRequiresBackupOrRecoveryPrecondition,
            futureMigrationRejectsUnknownCriticalRecordClasses =
                futureMigrationRejectsUnknownCriticalRecordClasses,
            futureMigrationFailsClosedOnUnknownUnsupportedVersions =
                futureMigrationFailsClosedOnUnknownUnsupportedVersions,
            futureMigrationPreservesRecordClassAndKeySeparation =
                futureMigrationPreservesRecordClassAndKeySeparation,
            futureMigrationDiagnosticsRedacted = futureMigrationDiagnosticsRedacted,
            noMigrationRunsInThisBranch = noMigrationRunsInThisBranch,
            futureCorruptionAuthenticatesBeforeTrustingMetadata =
                futureCorruptionAuthenticatesBeforeTrustingMetadata,
            futureCorruptionHandlingFailsClosed = futureCorruptionHandlingFailsClosed,
            futureDestructiveRepairOptInOnly = futureDestructiveRepairOptInOnly,
            futureCorruptionDiagnosticsSafeLabelsOnly = futureCorruptionDiagnosticsSafeLabelsOnly,
            futurePartialWriteDiagnosticsDoNotExposeMaterial =
                futurePartialWriteDiagnosticsDoNotExposeMaterial,
            noCorruptionDetectionOrRepairRunsInThisBranch =
                noCorruptionDetectionOrRepairRunsInThisBranch,
            futureWriterRequiresReviewedAtomicReplaceStrategy =
                futureWriterRequiresReviewedAtomicReplaceStrategy,
            futureWriterDoesNotExposeTempNamesOrPaths = futureWriterDoesNotExposeTempNamesOrPaths,
            futureWriterDistinguishesFailureClasses = futureWriterDistinguishesFailureClasses,
            noWriterOrAtomicReplaceInThisBranch = noWriterOrAtomicReplaceInThisBranch,
            migrationPolicyCreatesNoPayloadPlan = migrationPolicyCreatesNoPayloadPlan,
            corruptionPolicyCreatesNoRepairObject = corruptionPolicyCreatesNoRepairObject,
            atomicReplacePolicyImplementsNoFileIo = atomicReplacePolicyImplementsNoFileIo,
            diagnosticsPolicyContainsNoSensitiveMaterial =
                diagnosticsPolicyContainsNoSensitiveMaterial,
            normalSourceMaterialGuardExcludesBuildHistory =
                normalSourceMaterialGuardExcludesBuildHistory,
            localArtifactRootExcludedFromNormalSourceMaterialCorpus =
                localArtifactRootExcludedFromNormalSourceMaterialCorpus,
            migrationCorruptionPolicyDecisionPassed = migrationCorruptionPolicyDecisionPassed,
            evidenceCount = 9,
            decisionCheckCount = checks.size,
            blockerCount = failures.size,
            warningCount = 0,
            policyLabels = policyLabels,
            decisionChecks = checks,
            failureLabels = failures,
            displayLabel = EncryptedVaultMigrationCorruptionPolicySafeLabel(
                "encrypted vault migration corruption policy decision",
            ),
        )
    }
}
