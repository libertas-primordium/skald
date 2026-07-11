package com.libertasprimordium.skald.security

@JvmInline
value class EncryptedVaultParserWriterAdmissionSafeLabel(val value: String) {
    override fun toString(): String = "RedactedEncryptedVaultParserWriterAdmissionSafeLabel"
}

enum class EncryptedVaultParserWriterAdmissionKind(val label: String) {
    EncryptedLocalVaultParserWriterAdmissionGate(
        "ENCRYPTED_LOCAL_VAULT_PARSER_WRITER_ADMISSION_GATE",
    ),
}

enum class EncryptedVaultParserWriterAdmissionSourceSet(val label: String) {
    CommonMainPolicy("COMMON_MAIN_POLICY"),
}

enum class EncryptedVaultParserWriterAdmissionPolicyLabel(
    val safeLabel: EncryptedVaultParserWriterAdmissionSafeLabel,
) {
    ParserWriterAdmission(
        EncryptedVaultParserWriterAdmissionSafeLabel(
            "skald-encrypted-local-vault-parser-writer-admission-v1",
        ),
    ),
    ParserAdmissionPolicy(
        EncryptedVaultParserWriterAdmissionSafeLabel(
            "skald-vault-v1-parser-admission-policy",
        ),
    ),
    WriterAdmissionPolicy(
        EncryptedVaultParserWriterAdmissionSafeLabel(
            "skald-vault-v1-writer-admission-policy",
        ),
    ),
    CanonicalSerializationPolicy(
        EncryptedVaultParserWriterAdmissionSafeLabel(
            "skald-vault-v1-canonical-serialization-policy",
        ),
    ),
    AuthenticateBeforeParsePolicy(
        EncryptedVaultParserWriterAdmissionSafeLabel(
            "skald-vault-v1-authenticate-before-parse-policy",
        ),
    ),
    RedactedParserErrorPolicy(
        EncryptedVaultParserWriterAdmissionSafeLabel(
            "skald-vault-v1-redacted-parser-error-policy",
        ),
    ),
    RedactedWriterErrorPolicy(
        EncryptedVaultParserWriterAdmissionSafeLabel(
            "skald-vault-v1-redacted-writer-error-policy",
        ),
    ),
    NoPayloadDiagnosticsPolicy(
        EncryptedVaultParserWriterAdmissionSafeLabel(
            "skald-vault-v1-no-payload-diagnostics-policy",
        ),
    ),
    ParserKatRequirementPolicy(
        EncryptedVaultParserWriterAdmissionSafeLabel(
            "skald-vault-v1-parser-kat-requirement-policy",
        ),
    ),
    WriterKatRequirementPolicy(
        EncryptedVaultParserWriterAdmissionSafeLabel(
            "skald-vault-v1-writer-kat-requirement-policy",
        ),
    ),
}

enum class EncryptedVaultParserWriterAdmissionCheck {
    StorageReadinessDecisionPresent,
    ContainerFormatV1DecisionPresent,
    StoragePathSessionLifecycleDecisionPresent,
    MigrationCorruptionPolicyDecisionPresent,
    EncryptedVaultDesignPresent,
    CryptoDecisionPresent,
    DependencyReviewPresent,
    ProviderBoundaryPresent,
    SecureStorageBoundaryPresent,
    SecureMetadataBoundaryPresent,
    VaultParserWriterAdmissionGatePassed,
    VaultParserImplementationPathAdmitted,
    VaultWriterImplementationPathAdmitted,
    CanonicalSerializationPolicyAdmitted,
    AuthenticateBeforeParsePolicyAdmitted,
    RedactedParserErrorPolicyAdmitted,
    RedactedWriterErrorPolicyAdmitted,
    NoPayloadDiagnosticsPolicyAdmitted,
    ParserKatRequirementAdmitted,
    WriterKatRequirementAdmitted,
    FutureVaultParserImplementationRequiresSeparatePass,
    FutureVaultWriterImplementationRequiresSeparatePass,
    FutureParserWriterKatExecutionRequiresSeparatePass,
    FutureVaultStorageRepositoryRequiresSeparatePass,
    FutureSecureStorageSuccessRequiresSeparatePass,
    FutureSecureMetadataSuccessRequiresSeparatePass,
    FutureProductionSyncRequiresSeparatePass,
    FutureProductionProviderSelectionRequiresSeparatePass,
    VaultParserImplementationAbsent,
    VaultWriterImplementationAbsent,
    VaultContainerSerializationAbsent,
    VaultContainerParsingAbsent,
    VaultContainerBytesNotProduced,
    VaultContainerBytesNotConsumed,
    VaultHeaderNotSerialized,
    VaultHeaderNotParsed,
    VaultRecordDirectoryNotSerialized,
    VaultRecordDirectoryNotParsed,
    VaultRecordEnvelopeNotSerialized,
    VaultRecordEnvelopeNotParsed,
    VaultFileReadAbsent,
    VaultFileWriteAbsent,
    VaultFileDeleteAbsent,
    VaultDirectoryNotCreated,
    AtomicReplaceImplementationAbsent,
    PartialWriteDetectionImplementationAbsent,
    MigrationImplementationAbsent,
    MigrationExecutionAbsent,
    CorruptionDetectionImplementationAbsent,
    CorruptionRepairImplementationAbsent,
    BackupCreationAbsent,
    RollbackImplementationAbsent,
    KdfExecutionAbsent,
    AeadExecutionAbsent,
    EncryptionExecutionAbsent,
    DecryptionExecutionAbsent,
    KeyGenerationAbsent,
    NonceGenerationAbsent,
    TinkKeysetCreationAbsent,
    TinkKeysetPersistenceAbsent,
    VaultStoragePathImplementationAbsent,
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
    VaultParserImplementationAuthorizationAbsent,
    VaultWriterImplementationAuthorizationAbsent,
    VaultContainerSerializationAuthorizationAbsent,
    VaultContainerParsingAuthorizationAbsent,
    VaultFileReadAuthorizationAbsent,
    VaultFileWriteAuthorizationAbsent,
    VaultFileDeleteAuthorizationAbsent,
    VaultDirectoryCreationAuthorizationAbsent,
    AtomicReplaceAuthorizationAbsent,
    PartialWriteDetectionAuthorizationAbsent,
    MigrationImplementationAuthorizationAbsent,
    CorruptionDetectionAuthorizationAbsent,
    CorruptionRepairAuthorizationAbsent,
    BackupCreationAuthorizationAbsent,
    RollbackAuthorizationAbsent,
    KdfExecutionAuthorizationAbsent,
    AeadExecutionAuthorizationAbsent,
    EncryptionAuthorizationAbsent,
    DecryptionAuthorizationAbsent,
    KeyGenerationAuthorizationAbsent,
    NonceGenerationAuthorizationAbsent,
    TinkKeysetCreationAuthorizationAbsent,
    TinkKeysetPersistenceAuthorizationAbsent,
    VaultSessionImplementationAuthorizationAbsent,
    VaultUnlockAuthorizationAbsent,
    VaultLockAuthorizationAbsent,
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
    ParserPolicyCreatesNoParserObject,
    WriterPolicyCreatesNoWriterObject,
    ParserWriterKatPolicyCreatesNoVectorBytes,
    ParserWriterKatPolicyExecutesNoKat,
    DiagnosticsPolicyContainsNoSensitiveMaterial,
    BuildHistoryExcludedFromNormalSourceMaterialCorpus,
    LocalArtifactRootExcludedFromNormalSourceMaterialCorpus,
}

enum class EncryptedVaultParserWriterAdmissionFailureLabel {
    ExistingEvidenceMissing,
    ParserWriterAdmissionMissing,
    FutureSeparatePassGateMissing,
    ParserWriterRuntimeSurfacePresent,
    FileStorageRuntimeSurfacePresent,
    MigrationCorruptionRuntimeSurfacePresent,
    CryptoRuntimeSurfacePresent,
    LockSessionRuntimeSurfacePresent,
    ProductionStorageSurfacePresent,
    ProductionSyncSurfacePresent,
    ProductionProviderSelectionSurfacePresent,
    SigningBroadcastingUiEndpointOrMainnetSurfacePresent,
    ProductionAuthorizationPresent,
    PolicyMaterialPresent,
    CorpusBoundaryMissing,
}

data class EncryptedVaultParserWriterAdmissionGate(
    val admissionId: EncryptedVaultParserWriterAdmissionSafeLabel,
    val admissionVersion: Int,
    val admissionKind: EncryptedVaultParserWriterAdmissionKind,
    val sourceSet: EncryptedVaultParserWriterAdmissionSourceSet,
    val storageReadinessDecisionPresent: Boolean,
    val containerFormatV1DecisionPresent: Boolean,
    val storagePathSessionLifecycleDecisionPresent: Boolean,
    val migrationCorruptionPolicyDecisionPresent: Boolean,
    val encryptedVaultDesignPresent: Boolean,
    val cryptoDecisionPresent: Boolean,
    val dependencyReviewPresent: Boolean,
    val providerBoundaryPresent: Boolean,
    val secureStorageBoundaryPresent: Boolean,
    val secureMetadataBoundaryPresent: Boolean,
    val vaultParserWriterAdmissionGatePassed: Boolean,
    val vaultParserImplementationPathAdmitted: Boolean,
    val vaultWriterImplementationPathAdmitted: Boolean,
    val canonicalSerializationPolicyAdmitted: Boolean,
    val authenticateBeforeParsePolicyAdmitted: Boolean,
    val redactedParserErrorPolicyAdmitted: Boolean,
    val redactedWriterErrorPolicyAdmitted: Boolean,
    val noPayloadDiagnosticsPolicyAdmitted: Boolean,
    val parserKatRequirementAdmitted: Boolean,
    val writerKatRequirementAdmitted: Boolean,
    val futureVaultParserImplementationRequiresSeparatePass: Boolean,
    val futureVaultWriterImplementationRequiresSeparatePass: Boolean,
    val futureParserWriterKatExecutionRequiresSeparatePass: Boolean,
    val futureVaultStorageRepositoryRequiresSeparatePass: Boolean,
    val futureSecureStorageSuccessRequiresSeparatePass: Boolean,
    val futureSecureMetadataSuccessRequiresSeparatePass: Boolean,
    val futureProductionSyncRequiresSeparatePass: Boolean,
    val futureProductionProviderSelectionRequiresSeparatePass: Boolean,
    val vaultParserImplementationPresent: Boolean,
    val vaultWriterImplementationPresent: Boolean,
    val vaultContainerSerializationPresent: Boolean,
    val vaultContainerParsingPresent: Boolean,
    val vaultContainerBytesProduced: Boolean,
    val vaultContainerBytesConsumed: Boolean,
    val vaultHeaderSerialized: Boolean,
    val vaultHeaderParsed: Boolean,
    val vaultRecordDirectorySerialized: Boolean,
    val vaultRecordDirectoryParsed: Boolean,
    val vaultRecordEnvelopeSerialized: Boolean,
    val vaultRecordEnvelopeParsed: Boolean,
    val vaultFileReadPresent: Boolean,
    val vaultFileWritePresent: Boolean,
    val vaultFileDeletePresent: Boolean,
    val vaultDirectoryCreated: Boolean,
    val atomicReplaceImplementationPresent: Boolean,
    val partialWriteDetectionImplementationPresent: Boolean,
    val migrationImplementationPresent: Boolean,
    val migrationExecutionPresent: Boolean,
    val corruptionDetectionImplementationPresent: Boolean,
    val corruptionRepairImplementationPresent: Boolean,
    val backupCreationPresent: Boolean,
    val rollbackImplementationPresent: Boolean,
    val kdfExecutionPresent: Boolean,
    val aeadExecutionPresent: Boolean,
    val encryptionExecutionPresent: Boolean,
    val decryptionExecutionPresent: Boolean,
    val keyGenerationPresent: Boolean,
    val nonceGenerationPresent: Boolean,
    val tinkKeysetCreationPresent: Boolean,
    val tinkKeysetPersistencePresent: Boolean,
    val vaultStoragePathImplementationPresent: Boolean,
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
    val vaultParserImplementationAuthorizationPresent: Boolean,
    val vaultWriterImplementationAuthorizationPresent: Boolean,
    val vaultContainerSerializationAuthorizationPresent: Boolean,
    val vaultContainerParsingAuthorizationPresent: Boolean,
    val vaultFileReadAuthorizationPresent: Boolean,
    val vaultFileWriteAuthorizationPresent: Boolean,
    val vaultFileDeleteAuthorizationPresent: Boolean,
    val vaultDirectoryCreationAuthorizationPresent: Boolean,
    val atomicReplaceAuthorizationPresent: Boolean,
    val partialWriteDetectionAuthorizationPresent: Boolean,
    val migrationImplementationAuthorizationPresent: Boolean,
    val corruptionDetectionAuthorizationPresent: Boolean,
    val corruptionRepairAuthorizationPresent: Boolean,
    val backupCreationAuthorizationPresent: Boolean,
    val rollbackAuthorizationPresent: Boolean,
    val kdfExecutionAuthorizationPresent: Boolean,
    val aeadExecutionAuthorizationPresent: Boolean,
    val encryptionAuthorizationPresent: Boolean,
    val decryptionAuthorizationPresent: Boolean,
    val keyGenerationAuthorizationPresent: Boolean,
    val nonceGenerationAuthorizationPresent: Boolean,
    val tinkKeysetCreationAuthorizationPresent: Boolean,
    val tinkKeysetPersistenceAuthorizationPresent: Boolean,
    val vaultSessionImplementationAuthorizationPresent: Boolean,
    val vaultUnlockAuthorizationPresent: Boolean,
    val vaultLockAuthorizationPresent: Boolean,
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
    val vaultParserWriterAdmissionGatePassedIsLaterBranchOnly: Boolean,
    val vaultParserImplementationPathAdmittedIsNotParserImplementation: Boolean,
    val vaultWriterImplementationPathAdmittedIsNotWriterImplementation: Boolean,
    val canonicalSerializationPolicyAdmittedIsNotSerialization: Boolean,
    val authenticateBeforeParsePolicyAdmittedIsNotAuthenticatedParserImplementation: Boolean,
    val redactedParserErrorPolicyAdmittedIsNotParserDiagnosticsImplementation: Boolean,
    val redactedWriterErrorPolicyAdmittedIsNotWriterDiagnosticsImplementation: Boolean,
    val parserKatRequirementAdmittedIsNotParserKatExecution: Boolean,
    val writerKatRequirementAdmittedIsNotWriterKatExecution: Boolean,
    val futureParserAuthenticatesBeforeTrustingRecordMetadata: Boolean,
    val futureParserFailsClosedOnUnsupportedCriticalFeatures: Boolean,
    val futureParserFailsClosedOnUnknownUnsupportedVersions: Boolean,
    val futureParserDistinguishesFailureClasses: Boolean,
    val futureParserDiagnosticsRedactedSafeLabelsOnly: Boolean,
    val futureParserDiagnosticsExposeNoMaterial: Boolean,
    val noParserInThisBranch: Boolean,
    val noVaultBytesConsumedInThisBranch: Boolean,
    val futureWriterCanonicalDeterministicStructuralSerialization: Boolean,
    val futureWriterUsesRandomizedPerRecordNoncePolicyForEncryption: Boolean,
    val futureWriterDoesNotWriteUnauthenticatedSensitiveMetadata: Boolean,
    val futureWriterDoesNotSerializePlaintextSecretsOrMetadata: Boolean,
    val futureWriterDistinguishesFailureClasses: Boolean,
    val futureWriterDiagnosticsRedactedSafeLabelsOnly: Boolean,
    val futureWriterDiagnosticsExposeNoMaterial: Boolean,
    val noWriterInThisBranch: Boolean,
    val noVaultBytesProducedInThisBranch: Boolean,
    val futureParserWriterKatRequiresPublicSyntheticVectors: Boolean,
    val futureParserWriterVectorsTestSourceOnlyByDefault: Boolean,
    val futureParserWriterVectorsContainNoWalletMaterial: Boolean,
    val futureParserWriterVectorsAvoidProductionContinuousHex: Boolean,
    val noParserWriterKatRunsInThisBranch: Boolean,
    val parserPolicyCreatesNoParserObject: Boolean,
    val writerPolicyCreatesNoWriterObject: Boolean,
    val parserWriterKatPolicyCreatesNoVectorBytes: Boolean,
    val parserWriterKatPolicyExecutesNoKat: Boolean,
    val diagnosticsPolicyContainsNoSensitiveMaterial: Boolean,
    val normalSourceMaterialGuardExcludesBuildHistory: Boolean,
    val localArtifactRootExcludedFromNormalSourceMaterialCorpus: Boolean,
    val parserWriterAdmissionGateDecisionPassed: Boolean,
    val evidenceCount: Int,
    val admissionCheckCount: Int,
    val blockerCount: Int,
    val warningCount: Int,
    val policyLabels: List<EncryptedVaultParserWriterAdmissionPolicyLabel>,
    val admissionChecks: List<EncryptedVaultParserWriterAdmissionCheck>,
    val failureLabels: List<EncryptedVaultParserWriterAdmissionFailureLabel>,
    val displayLabel: EncryptedVaultParserWriterAdmissionSafeLabel,
) {
    override fun toString(): String =
        "EncryptedVaultParserWriterAdmissionGate(" +
            "REDACTED, COMMON_MAIN_POLICY, PARSER_WRITER_ADMISSION_GATE_ONLY, " +
            "LATER_BRANCHES_ONLY, POLICY_LABELS_ONLY, NO_PARSER, NO_WRITER, " +
            "NO_SERIALIZATION_PARSING, NO_VAULT_BYTES, NO_FILE_IO, " +
            "NO_CRYPTO_EXECUTION, NO_RUNTIME_CRYPTO_MATERIAL, NO_MIGRATION_REPAIR, " +
            "NO_RUNTIME_SESSION, NO_SECURE_STORAGE_SUCCESS, NO_PRODUCTION_SYNC, " +
            "DISABLED_PROVIDER_ONLY, NO_SIGNING_BROADCASTING_UI_ENDPOINT_MAINNET" +
            ")"
}

object EncryptedVaultParserWriterAdmissionGatePolicy {
    fun currentParserWriterAdmissionGate(): EncryptedVaultParserWriterAdmissionGate {
        val storageDecision =
            EncryptedVaultStorageReadinessDecisionPolicy.currentStorageReadinessDecision()
        val containerDecision =
            EncryptedVaultContainerFormatV1DecisionPolicy.currentContainerFormatV1Decision()
        val storagePathDecision =
            EncryptedVaultStoragePathSessionLifecycleDecisionPolicy
                .currentStoragePathSessionLifecycleDecision()
        val migrationDecision =
            EncryptedVaultMigrationCorruptionPolicyDecisionPolicy
                .currentMigrationCorruptionPolicyDecision()
        val providerSelection = VaultCryptoProviderSelectionRegistry.select()
        val checks = EncryptedVaultParserWriterAdmissionCheck.entries.toList()
        val policyLabels = EncryptedVaultParserWriterAdmissionPolicyLabel.entries.toList()

        val storageReadinessDecisionPresent = storageDecision.storageReadinessDecisionPassed
        val containerFormatV1DecisionPresent = containerDecision.containerFormatV1DecisionPassed
        val storagePathSessionLifecycleDecisionPresent =
            storagePathDecision.storagePathSessionLifecycleDecisionPassed
        val migrationCorruptionPolicyDecisionPresent =
            migrationDecision.migrationCorruptionPolicyDecisionPassed
        val encryptedVaultDesignPresent =
            storageDecision.encryptedVaultDesignPresent &&
                containerDecision.encryptedVaultDesignPresent &&
                storagePathDecision.encryptedVaultDesignPresent &&
                migrationDecision.encryptedVaultDesignPresent
        val cryptoDecisionPresent =
            storageDecision.cryptoDecisionPresent &&
                containerDecision.cryptoDecisionPresent &&
                storagePathDecision.cryptoDecisionPresent &&
                migrationDecision.cryptoDecisionPresent
        val dependencyReviewPresent =
            storageDecision.dependencyReviewPresent &&
                containerDecision.dependencyReviewPresent &&
                storagePathDecision.dependencyReviewPresent &&
                migrationDecision.dependencyReviewPresent
        val providerBoundaryPresent =
            storageDecision.disabledVaultCryptoProviderBoundaryPresent &&
                containerDecision.vaultCryptoProviderBoundaryPresent &&
                storagePathDecision.providerBoundaryPresent &&
                migrationDecision.providerBoundaryPresent
        val secureStorageBoundaryPresent =
            storageDecision.secureStorageBoundaryPresent &&
                storagePathDecision.secureStorageBoundaryPresent &&
                migrationDecision.secureStorageBoundaryPresent
        val secureMetadataBoundaryPresent =
            storageDecision.secureMetadataBoundaryPresent &&
                storagePathDecision.secureMetadataBoundaryPresent &&
                migrationDecision.secureMetadataBoundaryPresent

        val vaultParserImplementationPathAdmitted = true
        val vaultWriterImplementationPathAdmitted = true
        val canonicalSerializationPolicyAdmitted = true
        val authenticateBeforeParsePolicyAdmitted = true
        val redactedParserErrorPolicyAdmitted = true
        val redactedWriterErrorPolicyAdmitted = true
        val noPayloadDiagnosticsPolicyAdmitted = true
        val parserKatRequirementAdmitted = true
        val writerKatRequirementAdmitted = true
        val futureVaultParserImplementationRequiresSeparatePass = true
        val futureVaultWriterImplementationRequiresSeparatePass = true
        val futureParserWriterKatExecutionRequiresSeparatePass = true
        val futureVaultStorageRepositoryRequiresSeparatePass =
            migrationDecision.futureVaultStorageRepositoryRequiresSeparatePass
        val futureSecureStorageSuccessRequiresSeparatePass =
            migrationDecision.futureSecureStorageSuccessRequiresSeparatePass
        val futureSecureMetadataSuccessRequiresSeparatePass =
            migrationDecision.futureSecureMetadataSuccessRequiresSeparatePass
        val futureProductionSyncRequiresSeparatePass =
            migrationDecision.futureProductionSyncRequiresSeparatePass
        val futureProductionProviderSelectionRequiresSeparatePass =
            migrationDecision.futureProductionProviderSelectionRequiresSeparatePass

        val vaultParserImplementationPresent = false
        val vaultWriterImplementationPresent = false
        val vaultContainerSerializationPresent = migrationDecision.vaultContainerSerializationPresent
        val vaultContainerParsingPresent = migrationDecision.vaultContainerParsingPresent
        val vaultContainerBytesProduced = migrationDecision.vaultContainerBytesProduced
        val vaultContainerBytesConsumed = false
        val vaultHeaderSerialized = false
        val vaultHeaderParsed = false
        val vaultRecordDirectorySerialized = false
        val vaultRecordDirectoryParsed = false
        val vaultRecordEnvelopeSerialized = false
        val vaultRecordEnvelopeParsed = false
        val vaultFileReadPresent = migrationDecision.vaultFileReadPresent
        val vaultFileWritePresent = migrationDecision.vaultFileWritePresent
        val vaultFileDeletePresent = migrationDecision.vaultFileDeletePresent
        val vaultDirectoryCreated = migrationDecision.vaultDirectoryCreated
        val atomicReplaceImplementationPresent = migrationDecision.atomicReplaceImplementationPresent
        val partialWriteDetectionImplementationPresent =
            migrationDecision.partialWriteDetectionImplementationPresent
        val migrationImplementationPresent = migrationDecision.migrationImplementationPresent
        val migrationExecutionPresent = migrationDecision.migrationExecutionPresent
        val corruptionDetectionImplementationPresent =
            migrationDecision.corruptionDetectionImplementationPresent
        val corruptionRepairImplementationPresent =
            migrationDecision.corruptionRepairImplementationPresent
        val backupCreationPresent = migrationDecision.backupCreationPresent
        val rollbackImplementationPresent = migrationDecision.rollbackImplementationPresent
        val kdfExecutionPresent = false
        val aeadExecutionPresent = false
        val encryptionExecutionPresent = false
        val decryptionExecutionPresent = false
        val keyGenerationPresent = false
        val nonceGenerationPresent = false
        val tinkKeysetCreationPresent = false
        val tinkKeysetPersistencePresent = false
        val vaultStoragePathImplementationPresent =
            migrationDecision.vaultStoragePathImplementationPresent
        val encryptedVaultFileFormatImplemented = migrationDecision.encryptedVaultFileFormatImplemented
        val encryptedVaultRepositorySuccessPresent =
            migrationDecision.encryptedVaultRepositorySuccessPresent
        val lockSessionImplementationPresent = migrationDecision.lockSessionImplementationPresent
        val unlockImplementationPresent = migrationDecision.unlockImplementationPresent
        val runtimeSessionKeyPresent = migrationDecision.runtimeSessionKeyPresent
        val sessionKeyCached = migrationDecision.sessionKeyCached
        val plaintextCachePresent = migrationDecision.plaintextCachePresent
        val secureSecretStorageSuccessPathPresent =
            migrationDecision.secureSecretStorageSuccessPathPresent
        val secureMetadataStorageSuccessPathPresent =
            migrationDecision.secureMetadataStorageSuccessPathPresent
        val productionObservationPersistencePresent =
            migrationDecision.productionObservationPersistencePresent
        val productionAddressIndexPersistencePresent =
            migrationDecision.productionAddressIndexPersistencePresent
        val productionUtxoPersistencePresent = migrationDecision.productionUtxoPersistencePresent
        val productionWalletHistoryPersistencePresent =
            migrationDecision.productionWalletHistoryPersistencePresent
        val productionSyncPresent = migrationDecision.productionSyncPresent
        val productionBackendClientPresent = migrationDecision.productionBackendClientPresent
        val productionProviderSelectionEnabled = !providerSelection.selectedProviderIsDisabled
        val productionProviderSelectable = providerSelection.productionProviderSelectable
        val productionSelectionStillDisabledProviderOnly =
            providerSelection.selectedProviderIsDisabled &&
                !productionProviderSelectionEnabled &&
                !productionProviderSelectable
        val signingBroadcastingPresent = migrationDecision.signingBroadcastingPresent
        val uiActionEnablementPresent = migrationDecision.uiActionEnablementPresent
        val endpointPresent = migrationDecision.endpointPresent
        val mainnetPresent = migrationDecision.mainnetPresent

        val vaultParserImplementationAuthorizationPresent = false
        val vaultWriterImplementationAuthorizationPresent = false
        val vaultContainerSerializationAuthorizationPresent = false
        val vaultContainerParsingAuthorizationPresent = false
        val vaultFileReadAuthorizationPresent = false
        val vaultFileWriteAuthorizationPresent = false
        val vaultFileDeleteAuthorizationPresent = false
        val vaultDirectoryCreationAuthorizationPresent = false
        val atomicReplaceAuthorizationPresent = false
        val partialWriteDetectionAuthorizationPresent = false
        val migrationImplementationAuthorizationPresent = false
        val corruptionDetectionAuthorizationPresent = false
        val corruptionRepairAuthorizationPresent = false
        val backupCreationAuthorizationPresent = false
        val rollbackAuthorizationPresent = false
        val kdfExecutionAuthorizationPresent = false
        val aeadExecutionAuthorizationPresent = false
        val encryptionAuthorizationPresent = false
        val decryptionAuthorizationPresent = false
        val keyGenerationAuthorizationPresent = false
        val nonceGenerationAuthorizationPresent = false
        val tinkKeysetCreationAuthorizationPresent = false
        val tinkKeysetPersistenceAuthorizationPresent = false
        val vaultSessionImplementationAuthorizationPresent = false
        val vaultUnlockAuthorizationPresent = false
        val vaultLockAuthorizationPresent = false
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

        val vaultParserWriterAdmissionGatePassedIsLaterBranchOnly =
            vaultParserImplementationPathAdmitted &&
                vaultWriterImplementationPathAdmitted &&
                futureVaultParserImplementationRequiresSeparatePass &&
                futureVaultWriterImplementationRequiresSeparatePass &&
                !vaultParserImplementationPresent &&
                !vaultWriterImplementationPresent
        val vaultParserImplementationPathAdmittedIsNotParserImplementation =
            vaultParserImplementationPathAdmitted &&
                !vaultParserImplementationPresent &&
                !vaultContainerParsingPresent &&
                !vaultContainerBytesConsumed
        val vaultWriterImplementationPathAdmittedIsNotWriterImplementation =
            vaultWriterImplementationPathAdmitted &&
                !vaultWriterImplementationPresent &&
                !vaultContainerSerializationPresent &&
                !vaultContainerBytesProduced
        val canonicalSerializationPolicyAdmittedIsNotSerialization =
            canonicalSerializationPolicyAdmitted &&
                !vaultContainerSerializationPresent &&
                !vaultHeaderSerialized &&
                !vaultRecordDirectorySerialized &&
                !vaultRecordEnvelopeSerialized
        val authenticateBeforeParsePolicyAdmittedIsNotAuthenticatedParserImplementation =
            authenticateBeforeParsePolicyAdmitted &&
                !vaultParserImplementationPresent &&
                !vaultContainerParsingPresent &&
                !aeadExecutionPresent
        val redactedParserErrorPolicyAdmittedIsNotParserDiagnosticsImplementation =
            redactedParserErrorPolicyAdmitted &&
                !vaultParserImplementationPresent &&
                !vaultContainerBytesConsumed
        val redactedWriterErrorPolicyAdmittedIsNotWriterDiagnosticsImplementation =
            redactedWriterErrorPolicyAdmitted &&
                !vaultWriterImplementationPresent &&
                !vaultContainerBytesProduced
        val parserKatRequirementAdmittedIsNotParserKatExecution =
            parserKatRequirementAdmitted &&
                !vaultParserImplementationPresent &&
                !vaultContainerBytesConsumed
        val writerKatRequirementAdmittedIsNotWriterKatExecution =
            writerKatRequirementAdmitted &&
                !vaultWriterImplementationPresent &&
                !vaultContainerBytesProduced

        val futureParserAuthenticatesBeforeTrustingRecordMetadata = true
        val futureParserFailsClosedOnUnsupportedCriticalFeatures = true
        val futureParserFailsClosedOnUnknownUnsupportedVersions = true
        val futureParserDistinguishesFailureClasses = true
        val futureParserDiagnosticsRedactedSafeLabelsOnly = true
        val futureParserDiagnosticsExposeNoMaterial = true
        val noParserInThisBranch = !vaultParserImplementationPresent
        val noVaultBytesConsumedInThisBranch = !vaultContainerBytesConsumed
        val futureWriterCanonicalDeterministicStructuralSerialization = true
        val futureWriterUsesRandomizedPerRecordNoncePolicyForEncryption = true
        val futureWriterDoesNotWriteUnauthenticatedSensitiveMetadata = true
        val futureWriterDoesNotSerializePlaintextSecretsOrMetadata = true
        val futureWriterDistinguishesFailureClasses = true
        val futureWriterDiagnosticsRedactedSafeLabelsOnly = true
        val futureWriterDiagnosticsExposeNoMaterial = true
        val noWriterInThisBranch = !vaultWriterImplementationPresent
        val noVaultBytesProducedInThisBranch = !vaultContainerBytesProduced
        val futureParserWriterKatRequiresPublicSyntheticVectors = true
        val futureParserWriterVectorsTestSourceOnlyByDefault = true
        val futureParserWriterVectorsContainNoWalletMaterial = true
        val futureParserWriterVectorsAvoidProductionContinuousHex = true
        val noParserWriterKatRunsInThisBranch =
            !vaultParserImplementationPresent &&
                !vaultWriterImplementationPresent &&
                !vaultContainerBytesProduced &&
                !vaultContainerBytesConsumed
        val parserPolicyCreatesNoParserObject = !vaultParserImplementationPresent
        val writerPolicyCreatesNoWriterObject = !vaultWriterImplementationPresent
        val parserWriterKatPolicyCreatesNoVectorBytes =
            !vaultContainerBytesProduced &&
                !vaultContainerBytesConsumed
        val parserWriterKatPolicyExecutesNoKat = noParserWriterKatRunsInThisBranch
        val diagnosticsPolicyContainsNoSensitiveMaterial = policyLabels.all { policy ->
            val label = policy.safeLabel.value
            "/" !in label &&
                "\\" !in label &&
                "." !in label &&
                "~" !in label &&
                !label.contains("path", ignoreCase = true) &&
                !label.contains("file", ignoreCase = true) &&
                !label.contains("hash", ignoreCase = true) &&
                !label.contains("mac", ignoreCase = true) &&
                !label.contains("stack", ignoreCase = true) &&
                !label.contains("source", ignoreCase = true) &&
                !label.contains("raw", ignoreCase = true)
        }
        val normalSourceMaterialGuardExcludesBuildHistory = true
        val localArtifactRootExcludedFromNormalSourceMaterialCorpus = true

        val existingEvidencePresent =
            storageReadinessDecisionPresent &&
                containerFormatV1DecisionPresent &&
                storagePathSessionLifecycleDecisionPresent &&
                migrationCorruptionPolicyDecisionPresent &&
                encryptedVaultDesignPresent &&
                cryptoDecisionPresent &&
                dependencyReviewPresent &&
                providerBoundaryPresent &&
                secureStorageBoundaryPresent &&
                secureMetadataBoundaryPresent
        val admissionPresent =
            vaultParserImplementationPathAdmitted &&
                vaultWriterImplementationPathAdmitted &&
                canonicalSerializationPolicyAdmitted &&
                authenticateBeforeParsePolicyAdmitted &&
                redactedParserErrorPolicyAdmitted &&
                redactedWriterErrorPolicyAdmitted &&
                noPayloadDiagnosticsPolicyAdmitted &&
                parserKatRequirementAdmitted &&
                writerKatRequirementAdmitted
        val futureSeparatePassGatesPresent =
            futureVaultParserImplementationRequiresSeparatePass &&
                futureVaultWriterImplementationRequiresSeparatePass &&
                futureParserWriterKatExecutionRequiresSeparatePass &&
                futureVaultStorageRepositoryRequiresSeparatePass &&
                futureSecureStorageSuccessRequiresSeparatePass &&
                futureSecureMetadataSuccessRequiresSeparatePass &&
                futureProductionSyncRequiresSeparatePass &&
                futureProductionProviderSelectionRequiresSeparatePass
        val parserWriterRuntimeSurfacePresent =
            vaultParserImplementationPresent ||
                vaultWriterImplementationPresent ||
                vaultContainerSerializationPresent ||
                vaultContainerParsingPresent ||
                vaultContainerBytesProduced ||
                vaultContainerBytesConsumed ||
                vaultHeaderSerialized ||
                vaultHeaderParsed ||
                vaultRecordDirectorySerialized ||
                vaultRecordDirectoryParsed ||
                vaultRecordEnvelopeSerialized ||
                vaultRecordEnvelopeParsed
        val fileStorageRuntimeSurfacePresent =
            vaultFileReadPresent ||
                vaultFileWritePresent ||
                vaultFileDeletePresent ||
                vaultDirectoryCreated ||
                atomicReplaceImplementationPresent ||
                partialWriteDetectionImplementationPresent ||
                vaultStoragePathImplementationPresent ||
                encryptedVaultFileFormatImplemented
        val migrationCorruptionRuntimeSurfacePresent =
            migrationImplementationPresent ||
                migrationExecutionPresent ||
                corruptionDetectionImplementationPresent ||
                corruptionRepairImplementationPresent ||
                backupCreationPresent ||
                rollbackImplementationPresent
        val cryptoRuntimeSurfacePresent =
            kdfExecutionPresent ||
                aeadExecutionPresent ||
                encryptionExecutionPresent ||
                decryptionExecutionPresent ||
                keyGenerationPresent ||
                nonceGenerationPresent ||
                tinkKeysetCreationPresent ||
                tinkKeysetPersistencePresent
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
            vaultParserImplementationAuthorizationPresent ||
                vaultWriterImplementationAuthorizationPresent ||
                vaultContainerSerializationAuthorizationPresent ||
                vaultContainerParsingAuthorizationPresent ||
                vaultFileReadAuthorizationPresent ||
                vaultFileWriteAuthorizationPresent ||
                vaultFileDeleteAuthorizationPresent ||
                vaultDirectoryCreationAuthorizationPresent ||
                atomicReplaceAuthorizationPresent ||
                partialWriteDetectionAuthorizationPresent ||
                migrationImplementationAuthorizationPresent ||
                corruptionDetectionAuthorizationPresent ||
                corruptionRepairAuthorizationPresent ||
                backupCreationAuthorizationPresent ||
                rollbackAuthorizationPresent ||
                kdfExecutionAuthorizationPresent ||
                aeadExecutionAuthorizationPresent ||
                encryptionAuthorizationPresent ||
                decryptionAuthorizationPresent ||
                keyGenerationAuthorizationPresent ||
                nonceGenerationAuthorizationPresent ||
                tinkKeysetCreationAuthorizationPresent ||
                tinkKeysetPersistenceAuthorizationPresent ||
                vaultSessionImplementationAuthorizationPresent ||
                vaultUnlockAuthorizationPresent ||
                vaultLockAuthorizationPresent ||
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
            parserPolicyCreatesNoParserObject &&
                writerPolicyCreatesNoWriterObject &&
                parserWriterKatPolicyCreatesNoVectorBytes &&
                parserWriterKatPolicyExecutesNoKat &&
                diagnosticsPolicyContainsNoSensitiveMaterial
        val corpusBoundaryPresent =
            normalSourceMaterialGuardExcludesBuildHistory &&
                localArtifactRootExcludedFromNormalSourceMaterialCorpus

        val failures = buildList {
            if (!existingEvidencePresent) {
                add(EncryptedVaultParserWriterAdmissionFailureLabel.ExistingEvidenceMissing)
            }
            if (!admissionPresent) {
                add(EncryptedVaultParserWriterAdmissionFailureLabel.ParserWriterAdmissionMissing)
            }
            if (!futureSeparatePassGatesPresent) {
                add(EncryptedVaultParserWriterAdmissionFailureLabel.FutureSeparatePassGateMissing)
            }
            if (parserWriterRuntimeSurfacePresent) {
                add(
                    EncryptedVaultParserWriterAdmissionFailureLabel
                        .ParserWriterRuntimeSurfacePresent,
                )
            }
            if (fileStorageRuntimeSurfacePresent) {
                add(EncryptedVaultParserWriterAdmissionFailureLabel.FileStorageRuntimeSurfacePresent)
            }
            if (migrationCorruptionRuntimeSurfacePresent) {
                add(
                    EncryptedVaultParserWriterAdmissionFailureLabel
                        .MigrationCorruptionRuntimeSurfacePresent,
                )
            }
            if (cryptoRuntimeSurfacePresent) {
                add(EncryptedVaultParserWriterAdmissionFailureLabel.CryptoRuntimeSurfacePresent)
            }
            if (lockSessionRuntimeSurfacePresent) {
                add(EncryptedVaultParserWriterAdmissionFailureLabel.LockSessionRuntimeSurfacePresent)
            }
            if (productionStorageSurfacePresent) {
                add(EncryptedVaultParserWriterAdmissionFailureLabel.ProductionStorageSurfacePresent)
            }
            if (productionSyncSurfacePresent) {
                add(EncryptedVaultParserWriterAdmissionFailureLabel.ProductionSyncSurfacePresent)
            }
            if (productionProviderSelectionSurfacePresent || !productionSelectionStillDisabledProviderOnly) {
                add(
                    EncryptedVaultParserWriterAdmissionFailureLabel
                        .ProductionProviderSelectionSurfacePresent,
                )
            }
            if (signingBroadcastingUiEndpointOrMainnetSurfacePresent) {
                add(
                    EncryptedVaultParserWriterAdmissionFailureLabel
                        .SigningBroadcastingUiEndpointOrMainnetSurfacePresent,
                )
            }
            if (productionAuthorizationPresent) {
                add(EncryptedVaultParserWriterAdmissionFailureLabel.ProductionAuthorizationPresent)
            }
            if (!policyMaterialAbsent) {
                add(EncryptedVaultParserWriterAdmissionFailureLabel.PolicyMaterialPresent)
            }
            if (!corpusBoundaryPresent) {
                add(EncryptedVaultParserWriterAdmissionFailureLabel.CorpusBoundaryMissing)
            }
        }

        val vaultParserWriterAdmissionGatePassed = failures.isEmpty()
        val parserWriterAdmissionGateDecisionPassed =
            vaultParserWriterAdmissionGatePassed &&
                vaultParserWriterAdmissionGatePassedIsLaterBranchOnly &&
                vaultParserImplementationPathAdmittedIsNotParserImplementation &&
                vaultWriterImplementationPathAdmittedIsNotWriterImplementation &&
                canonicalSerializationPolicyAdmittedIsNotSerialization &&
                authenticateBeforeParsePolicyAdmittedIsNotAuthenticatedParserImplementation &&
                parserKatRequirementAdmittedIsNotParserKatExecution &&
                writerKatRequirementAdmittedIsNotWriterKatExecution

        return EncryptedVaultParserWriterAdmissionGate(
            admissionId = EncryptedVaultParserWriterAdmissionSafeLabel(
                "skald-encrypted-local-vault-parser-writer-admission-gate-v1",
            ),
            admissionVersion = 1,
            admissionKind =
                EncryptedVaultParserWriterAdmissionKind
                    .EncryptedLocalVaultParserWriterAdmissionGate,
            sourceSet = EncryptedVaultParserWriterAdmissionSourceSet.CommonMainPolicy,
            storageReadinessDecisionPresent = storageReadinessDecisionPresent,
            containerFormatV1DecisionPresent = containerFormatV1DecisionPresent,
            storagePathSessionLifecycleDecisionPresent =
                storagePathSessionLifecycleDecisionPresent,
            migrationCorruptionPolicyDecisionPresent = migrationCorruptionPolicyDecisionPresent,
            encryptedVaultDesignPresent = encryptedVaultDesignPresent,
            cryptoDecisionPresent = cryptoDecisionPresent,
            dependencyReviewPresent = dependencyReviewPresent,
            providerBoundaryPresent = providerBoundaryPresent,
            secureStorageBoundaryPresent = secureStorageBoundaryPresent,
            secureMetadataBoundaryPresent = secureMetadataBoundaryPresent,
            vaultParserWriterAdmissionGatePassed = vaultParserWriterAdmissionGatePassed,
            vaultParserImplementationPathAdmitted = vaultParserImplementationPathAdmitted,
            vaultWriterImplementationPathAdmitted = vaultWriterImplementationPathAdmitted,
            canonicalSerializationPolicyAdmitted = canonicalSerializationPolicyAdmitted,
            authenticateBeforeParsePolicyAdmitted = authenticateBeforeParsePolicyAdmitted,
            redactedParserErrorPolicyAdmitted = redactedParserErrorPolicyAdmitted,
            redactedWriterErrorPolicyAdmitted = redactedWriterErrorPolicyAdmitted,
            noPayloadDiagnosticsPolicyAdmitted = noPayloadDiagnosticsPolicyAdmitted,
            parserKatRequirementAdmitted = parserKatRequirementAdmitted,
            writerKatRequirementAdmitted = writerKatRequirementAdmitted,
            futureVaultParserImplementationRequiresSeparatePass =
                futureVaultParserImplementationRequiresSeparatePass,
            futureVaultWriterImplementationRequiresSeparatePass =
                futureVaultWriterImplementationRequiresSeparatePass,
            futureParserWriterKatExecutionRequiresSeparatePass =
                futureParserWriterKatExecutionRequiresSeparatePass,
            futureVaultStorageRepositoryRequiresSeparatePass =
                futureVaultStorageRepositoryRequiresSeparatePass,
            futureSecureStorageSuccessRequiresSeparatePass =
                futureSecureStorageSuccessRequiresSeparatePass,
            futureSecureMetadataSuccessRequiresSeparatePass =
                futureSecureMetadataSuccessRequiresSeparatePass,
            futureProductionSyncRequiresSeparatePass = futureProductionSyncRequiresSeparatePass,
            futureProductionProviderSelectionRequiresSeparatePass =
                futureProductionProviderSelectionRequiresSeparatePass,
            vaultParserImplementationPresent = vaultParserImplementationPresent,
            vaultWriterImplementationPresent = vaultWriterImplementationPresent,
            vaultContainerSerializationPresent = vaultContainerSerializationPresent,
            vaultContainerParsingPresent = vaultContainerParsingPresent,
            vaultContainerBytesProduced = vaultContainerBytesProduced,
            vaultContainerBytesConsumed = vaultContainerBytesConsumed,
            vaultHeaderSerialized = vaultHeaderSerialized,
            vaultHeaderParsed = vaultHeaderParsed,
            vaultRecordDirectorySerialized = vaultRecordDirectorySerialized,
            vaultRecordDirectoryParsed = vaultRecordDirectoryParsed,
            vaultRecordEnvelopeSerialized = vaultRecordEnvelopeSerialized,
            vaultRecordEnvelopeParsed = vaultRecordEnvelopeParsed,
            vaultFileReadPresent = vaultFileReadPresent,
            vaultFileWritePresent = vaultFileWritePresent,
            vaultFileDeletePresent = vaultFileDeletePresent,
            vaultDirectoryCreated = vaultDirectoryCreated,
            atomicReplaceImplementationPresent = atomicReplaceImplementationPresent,
            partialWriteDetectionImplementationPresent =
                partialWriteDetectionImplementationPresent,
            migrationImplementationPresent = migrationImplementationPresent,
            migrationExecutionPresent = migrationExecutionPresent,
            corruptionDetectionImplementationPresent = corruptionDetectionImplementationPresent,
            corruptionRepairImplementationPresent = corruptionRepairImplementationPresent,
            backupCreationPresent = backupCreationPresent,
            rollbackImplementationPresent = rollbackImplementationPresent,
            kdfExecutionPresent = kdfExecutionPresent,
            aeadExecutionPresent = aeadExecutionPresent,
            encryptionExecutionPresent = encryptionExecutionPresent,
            decryptionExecutionPresent = decryptionExecutionPresent,
            keyGenerationPresent = keyGenerationPresent,
            nonceGenerationPresent = nonceGenerationPresent,
            tinkKeysetCreationPresent = tinkKeysetCreationPresent,
            tinkKeysetPersistencePresent = tinkKeysetPersistencePresent,
            vaultStoragePathImplementationPresent = vaultStoragePathImplementationPresent,
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
            vaultParserImplementationAuthorizationPresent =
                vaultParserImplementationAuthorizationPresent,
            vaultWriterImplementationAuthorizationPresent =
                vaultWriterImplementationAuthorizationPresent,
            vaultContainerSerializationAuthorizationPresent =
                vaultContainerSerializationAuthorizationPresent,
            vaultContainerParsingAuthorizationPresent = vaultContainerParsingAuthorizationPresent,
            vaultFileReadAuthorizationPresent = vaultFileReadAuthorizationPresent,
            vaultFileWriteAuthorizationPresent = vaultFileWriteAuthorizationPresent,
            vaultFileDeleteAuthorizationPresent = vaultFileDeleteAuthorizationPresent,
            vaultDirectoryCreationAuthorizationPresent = vaultDirectoryCreationAuthorizationPresent,
            atomicReplaceAuthorizationPresent = atomicReplaceAuthorizationPresent,
            partialWriteDetectionAuthorizationPresent = partialWriteDetectionAuthorizationPresent,
            migrationImplementationAuthorizationPresent =
                migrationImplementationAuthorizationPresent,
            corruptionDetectionAuthorizationPresent = corruptionDetectionAuthorizationPresent,
            corruptionRepairAuthorizationPresent = corruptionRepairAuthorizationPresent,
            backupCreationAuthorizationPresent = backupCreationAuthorizationPresent,
            rollbackAuthorizationPresent = rollbackAuthorizationPresent,
            kdfExecutionAuthorizationPresent = kdfExecutionAuthorizationPresent,
            aeadExecutionAuthorizationPresent = aeadExecutionAuthorizationPresent,
            encryptionAuthorizationPresent = encryptionAuthorizationPresent,
            decryptionAuthorizationPresent = decryptionAuthorizationPresent,
            keyGenerationAuthorizationPresent = keyGenerationAuthorizationPresent,
            nonceGenerationAuthorizationPresent = nonceGenerationAuthorizationPresent,
            tinkKeysetCreationAuthorizationPresent = tinkKeysetCreationAuthorizationPresent,
            tinkKeysetPersistenceAuthorizationPresent = tinkKeysetPersistenceAuthorizationPresent,
            vaultSessionImplementationAuthorizationPresent =
                vaultSessionImplementationAuthorizationPresent,
            vaultUnlockAuthorizationPresent = vaultUnlockAuthorizationPresent,
            vaultLockAuthorizationPresent = vaultLockAuthorizationPresent,
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
            vaultParserWriterAdmissionGatePassedIsLaterBranchOnly =
                vaultParserWriterAdmissionGatePassedIsLaterBranchOnly,
            vaultParserImplementationPathAdmittedIsNotParserImplementation =
                vaultParserImplementationPathAdmittedIsNotParserImplementation,
            vaultWriterImplementationPathAdmittedIsNotWriterImplementation =
                vaultWriterImplementationPathAdmittedIsNotWriterImplementation,
            canonicalSerializationPolicyAdmittedIsNotSerialization =
                canonicalSerializationPolicyAdmittedIsNotSerialization,
            authenticateBeforeParsePolicyAdmittedIsNotAuthenticatedParserImplementation =
                authenticateBeforeParsePolicyAdmittedIsNotAuthenticatedParserImplementation,
            redactedParserErrorPolicyAdmittedIsNotParserDiagnosticsImplementation =
                redactedParserErrorPolicyAdmittedIsNotParserDiagnosticsImplementation,
            redactedWriterErrorPolicyAdmittedIsNotWriterDiagnosticsImplementation =
                redactedWriterErrorPolicyAdmittedIsNotWriterDiagnosticsImplementation,
            parserKatRequirementAdmittedIsNotParserKatExecution =
                parserKatRequirementAdmittedIsNotParserKatExecution,
            writerKatRequirementAdmittedIsNotWriterKatExecution =
                writerKatRequirementAdmittedIsNotWriterKatExecution,
            futureParserAuthenticatesBeforeTrustingRecordMetadata =
                futureParserAuthenticatesBeforeTrustingRecordMetadata,
            futureParserFailsClosedOnUnsupportedCriticalFeatures =
                futureParserFailsClosedOnUnsupportedCriticalFeatures,
            futureParserFailsClosedOnUnknownUnsupportedVersions =
                futureParserFailsClosedOnUnknownUnsupportedVersions,
            futureParserDistinguishesFailureClasses = futureParserDistinguishesFailureClasses,
            futureParserDiagnosticsRedactedSafeLabelsOnly =
                futureParserDiagnosticsRedactedSafeLabelsOnly,
            futureParserDiagnosticsExposeNoMaterial = futureParserDiagnosticsExposeNoMaterial,
            noParserInThisBranch = noParserInThisBranch,
            noVaultBytesConsumedInThisBranch = noVaultBytesConsumedInThisBranch,
            futureWriterCanonicalDeterministicStructuralSerialization =
                futureWriterCanonicalDeterministicStructuralSerialization,
            futureWriterUsesRandomizedPerRecordNoncePolicyForEncryption =
                futureWriterUsesRandomizedPerRecordNoncePolicyForEncryption,
            futureWriterDoesNotWriteUnauthenticatedSensitiveMetadata =
                futureWriterDoesNotWriteUnauthenticatedSensitiveMetadata,
            futureWriterDoesNotSerializePlaintextSecretsOrMetadata =
                futureWriterDoesNotSerializePlaintextSecretsOrMetadata,
            futureWriterDistinguishesFailureClasses = futureWriterDistinguishesFailureClasses,
            futureWriterDiagnosticsRedactedSafeLabelsOnly =
                futureWriterDiagnosticsRedactedSafeLabelsOnly,
            futureWriterDiagnosticsExposeNoMaterial = futureWriterDiagnosticsExposeNoMaterial,
            noWriterInThisBranch = noWriterInThisBranch,
            noVaultBytesProducedInThisBranch = noVaultBytesProducedInThisBranch,
            futureParserWriterKatRequiresPublicSyntheticVectors =
                futureParserWriterKatRequiresPublicSyntheticVectors,
            futureParserWriterVectorsTestSourceOnlyByDefault =
                futureParserWriterVectorsTestSourceOnlyByDefault,
            futureParserWriterVectorsContainNoWalletMaterial =
                futureParserWriterVectorsContainNoWalletMaterial,
            futureParserWriterVectorsAvoidProductionContinuousHex =
                futureParserWriterVectorsAvoidProductionContinuousHex,
            noParserWriterKatRunsInThisBranch = noParserWriterKatRunsInThisBranch,
            parserPolicyCreatesNoParserObject = parserPolicyCreatesNoParserObject,
            writerPolicyCreatesNoWriterObject = writerPolicyCreatesNoWriterObject,
            parserWriterKatPolicyCreatesNoVectorBytes = parserWriterKatPolicyCreatesNoVectorBytes,
            parserWriterKatPolicyExecutesNoKat = parserWriterKatPolicyExecutesNoKat,
            diagnosticsPolicyContainsNoSensitiveMaterial =
                diagnosticsPolicyContainsNoSensitiveMaterial,
            normalSourceMaterialGuardExcludesBuildHistory =
                normalSourceMaterialGuardExcludesBuildHistory,
            localArtifactRootExcludedFromNormalSourceMaterialCorpus =
                localArtifactRootExcludedFromNormalSourceMaterialCorpus,
            parserWriterAdmissionGateDecisionPassed = parserWriterAdmissionGateDecisionPassed,
            evidenceCount = 10,
            admissionCheckCount = checks.size,
            blockerCount = failures.size,
            warningCount = 0,
            policyLabels = policyLabels,
            admissionChecks = checks,
            failureLabels = failures,
            displayLabel = EncryptedVaultParserWriterAdmissionSafeLabel(
                "encrypted vault parser writer admission gate",
            ),
        )
    }
}
