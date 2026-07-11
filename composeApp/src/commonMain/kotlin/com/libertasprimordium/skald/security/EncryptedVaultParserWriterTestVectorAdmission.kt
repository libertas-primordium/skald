package com.libertasprimordium.skald.security

@JvmInline
value class EncryptedVaultParserWriterTestVectorAdmissionSafeLabel(val value: String) {
    override fun toString(): String = "EncryptedVaultParserWriterTestVectorAdmissionSafeLabel(REDACTED)"
}

enum class EncryptedVaultParserWriterTestVectorAdmissionKind(val label: String) {
    EncryptedLocalVaultParserWriterTestVectorAdmission(
        "ENCRYPTED_LOCAL_VAULT_PARSER_WRITER_TEST_VECTOR_ADMISSION",
    ),
}

enum class EncryptedVaultParserWriterTestVectorAdmissionSourceSet(val label: String) {
    CommonMainPolicy("COMMON_MAIN_POLICY"),
}

enum class EncryptedVaultParserWriterTestVectorAdmissionPolicyLabel(
    val safeLabel: EncryptedVaultParserWriterTestVectorAdmissionSafeLabel,
) {
    ParserWriterTestVectorAdmission(
        EncryptedVaultParserWriterTestVectorAdmissionSafeLabel(
            "skald-encrypted-local-vault-parser-writer-test-vector-admission-v1",
        ),
    ),
    ParserWriterSyntheticVectorPolicy(
        EncryptedVaultParserWriterTestVectorAdmissionSafeLabel(
            "skald-vault-v1-parser-writer-synthetic-vector-policy",
        ),
    ),
    InMemoryTestVectorPolicy(
        EncryptedVaultParserWriterTestVectorAdmissionSafeLabel(
            "skald-vault-v1-in-memory-test-vector-policy",
        ),
    ),
    NonWalletVectorPolicy(
        EncryptedVaultParserWriterTestVectorAdmissionSafeLabel(
            "skald-vault-v1-non-wallet-vector-policy",
        ),
    ),
    TestSourceVectorConfinementPolicy(
        EncryptedVaultParserWriterTestVectorAdmissionSafeLabel(
            "skald-vault-v1-test-source-vector-confinement-policy",
        ),
    ),
    RedactedVectorDisplayPolicy(
        EncryptedVaultParserWriterTestVectorAdmissionSafeLabel(
            "skald-vault-v1-redacted-vector-display-policy",
        ),
    ),
    NoProductionVectorBytesPolicy(
        EncryptedVaultParserWriterTestVectorAdmissionSafeLabel(
            "skald-vault-v1-no-production-vector-bytes-policy",
        ),
    ),
    ParserVectorRequirementPolicy(
        EncryptedVaultParserWriterTestVectorAdmissionSafeLabel(
            "skald-vault-v1-parser-vector-requirement-policy",
        ),
    ),
    WriterVectorRequirementPolicy(
        EncryptedVaultParserWriterTestVectorAdmissionSafeLabel(
            "skald-vault-v1-writer-vector-requirement-policy",
        ),
    ),
    AndroidParserWriterVectorRequirementPolicy(
        EncryptedVaultParserWriterTestVectorAdmissionSafeLabel(
            "skald-vault-v1-android-parser-writer-vector-requirement-policy",
        ),
    ),
    DesktopParserWriterVectorRequirementPolicy(
        EncryptedVaultParserWriterTestVectorAdmissionSafeLabel(
            "skald-vault-v1-desktop-parser-writer-vector-requirement-policy",
        ),
    ),
}

enum class EncryptedVaultParserWriterSyntheticVectorClass(
    val safeLabel: EncryptedVaultParserWriterTestVectorAdmissionSafeLabel,
) {
    MinimalHeaderOnlySyntheticVector(
        EncryptedVaultParserWriterTestVectorAdmissionSafeLabel(
            "minimal-header-only-synthetic-vector",
        ),
    ),
    HeaderAndKdfSectionSyntheticVector(
        EncryptedVaultParserWriterTestVectorAdmissionSafeLabel(
            "header-and-kdf-section-synthetic-vector",
        ),
    ),
    HeaderKeyEnvelopeDirectorySyntheticVector(
        EncryptedVaultParserWriterTestVectorAdmissionSafeLabel(
            "header-key-envelope-directory-synthetic-vector",
        ),
    ),
    SingleRecordEnvelopeSyntheticVector(
        EncryptedVaultParserWriterTestVectorAdmissionSafeLabel(
            "single-record-envelope-synthetic-vector",
        ),
    ),
    MultiRecordDirectorySyntheticVector(
        EncryptedVaultParserWriterTestVectorAdmissionSafeLabel(
            "multi-record-directory-synthetic-vector",
        ),
    ),
    UnsupportedVersionSyntheticVector(
        EncryptedVaultParserWriterTestVectorAdmissionSafeLabel(
            "unsupported-version-synthetic-vector",
        ),
    ),
    UnknownCriticalFeatureSyntheticVector(
        EncryptedVaultParserWriterTestVectorAdmissionSafeLabel(
            "unknown-critical-feature-synthetic-vector",
        ),
    ),
    TruncatedHeaderSyntheticVector(
        EncryptedVaultParserWriterTestVectorAdmissionSafeLabel(
            "truncated-header-synthetic-vector",
        ),
    ),
    TruncatedRecordEnvelopeSyntheticVector(
        EncryptedVaultParserWriterTestVectorAdmissionSafeLabel(
            "truncated-record-envelope-synthetic-vector",
        ),
    ),
    RedactedDiagnosticsSyntheticVector(
        EncryptedVaultParserWriterTestVectorAdmissionSafeLabel(
            "redacted-diagnostics-synthetic-vector",
        ),
    ),
    MigrationRequiredSyntheticVector(
        EncryptedVaultParserWriterTestVectorAdmissionSafeLabel(
            "migration-required-synthetic-vector",
        ),
    ),
    CorruptionSuspectedSyntheticVector(
        EncryptedVaultParserWriterTestVectorAdmissionSafeLabel(
            "corruption-suspected-synthetic-vector",
        ),
    ),
}

enum class EncryptedVaultParserWriterTestVectorAdmissionCheck {
    StorageReadinessDecisionPresent,
    ContainerFormatV1DecisionPresent,
    StoragePathSessionLifecycleDecisionPresent,
    MigrationCorruptionPolicyDecisionPresent,
    ParserWriterAdmissionGatePresent,
    EncryptedVaultDesignPresent,
    CryptoDecisionPresent,
    DependencyReviewPresent,
    ProviderBoundaryPresent,
    SecureStorageBoundaryPresent,
    SecureMetadataBoundaryPresent,
    ParserWriterTestVectorAdmissionPassed,
    FutureSyntheticVectorCreationAdmitted,
    FutureInMemoryVectorExecutionAdmitted,
    FutureParserVectorCoverageAdmitted,
    FutureWriterVectorCoverageAdmitted,
    FutureRoundTripVectorCoverageAdmitted,
    FutureNegativeVectorCoverageAdmitted,
    FutureRedactedVectorDiagnosticsAdmitted,
    FutureDesktopParserWriterVectorExecutionRequired,
    FutureAndroidParserWriterVectorExecutionRequired,
    FutureParserImplementationRequiresSeparatePass,
    FutureWriterImplementationRequiresSeparatePass,
    FutureParserWriterKatExecutionRequiresSeparatePass,
    FutureVaultStorageRepositoryRequiresSeparatePass,
    FutureSecureStorageSuccessRequiresSeparatePass,
    FutureSecureMetadataSuccessRequiresSeparatePass,
    FutureProductionSyncRequiresSeparatePass,
    FutureProductionProviderSelectionRequiresSeparatePass,
    SyntheticVectorBytesAbsent,
    InMemoryVectorBytesAbsent,
    ProductionVectorBytesAbsent,
    ParserVectorExecutionAbsent,
    WriterVectorExecutionAbsent,
    RoundTripVectorExecutionAbsent,
    NegativeVectorExecutionAbsent,
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
    SyntheticVectorCreationAuthorizationAbsent,
    InMemoryVectorExecutionAuthorizationAbsent,
    ParserVectorExecutionAuthorizationAbsent,
    WriterVectorExecutionAuthorizationAbsent,
    ParserImplementationAuthorizationAbsent,
    WriterImplementationAuthorizationAbsent,
    VaultContainerSerializationAuthorizationAbsent,
    VaultContainerParsingAuthorizationAbsent,
    VaultFileReadAuthorizationAbsent,
    VaultFileWriteAuthorizationAbsent,
    VaultFileDeleteAuthorizationAbsent,
    VaultDirectoryCreationAuthorizationAbsent,
    KdfAeadEncryptionDecryptionAuthorizationAbsent,
    KeyNonceGenerationAuthorizationAbsent,
    TinkKeysetCreationPersistenceAuthorizationAbsent,
    MigrationCorruptionBackupRollbackAtomicReplaceAuthorizationAbsent,
    ProductionStorageAuthorizationAbsent,
    ProductionSecretStorageAuthorizationAbsent,
    ProductionMetadataStorageAuthorizationAbsent,
    ProductionSyncAuthorizationAbsent,
    ProductionProviderSelectionAuthorizationAbsent,
    ProductionProviderImplementationAuthorizationAbsent,
    SigningBroadcastingAuthorizationAbsent,
    UiEndpointMainnetAuthorizationAbsent,
    ProductionProviderSelectionStillDisabledProviderOnly,
    TestVectorPolicyCreatesNoVectorBytes,
    TestVectorPolicyCreatesNoParserInputBytes,
    TestVectorPolicyCreatesNoWriterOutputBytes,
    TestVectorPolicyExecutesNoKat,
    TestVectorPolicyKeepsFutureVectorsTestSourceOnly,
    TestVectorPolicyExcludesWalletAndInfrastructureMaterial,
    DiagnosticsPolicyContainsNoSensitiveMaterial,
    DisplayOutputSafeLabelOnly,
    BuildHistoryExcludedFromNormalSourceMaterialCorpus,
    LocalArtifactRootExcludedFromNormalSourceMaterialCorpus,
}

enum class EncryptedVaultParserWriterTestVectorAdmissionFailureLabel {
    PriorEvidenceMissing,
    TestVectorAdmissionMissing,
    FutureSeparatePassGateMissing,
    VectorRuntimeSurfacePresent,
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
    VectorPolicyMaterialPresent,
    CorpusBoundaryMissing,
}

data class EncryptedVaultParserWriterTestVectorAdmission(
    val admissionId: EncryptedVaultParserWriterTestVectorAdmissionSafeLabel,
    val admissionVersion: Int,
    val admissionKind: EncryptedVaultParserWriterTestVectorAdmissionKind,
    val sourceSet: EncryptedVaultParserWriterTestVectorAdmissionSourceSet,
    val storageReadinessDecisionPresent: Boolean,
    val containerFormatV1DecisionPresent: Boolean,
    val storagePathSessionLifecycleDecisionPresent: Boolean,
    val migrationCorruptionPolicyDecisionPresent: Boolean,
    val parserWriterAdmissionGatePresent: Boolean,
    val encryptedVaultDesignPresent: Boolean,
    val cryptoDecisionPresent: Boolean,
    val dependencyReviewPresent: Boolean,
    val providerBoundaryPresent: Boolean,
    val secureStorageBoundaryPresent: Boolean,
    val secureMetadataBoundaryPresent: Boolean,
    val parserWriterTestVectorAdmissionPassed: Boolean,
    val futureSyntheticVectorCreationAdmitted: Boolean,
    val futureInMemoryVectorExecutionAdmitted: Boolean,
    val futureParserVectorCoverageAdmitted: Boolean,
    val futureWriterVectorCoverageAdmitted: Boolean,
    val futureRoundTripVectorCoverageAdmitted: Boolean,
    val futureNegativeVectorCoverageAdmitted: Boolean,
    val futureRedactedVectorDiagnosticsAdmitted: Boolean,
    val futureDesktopParserWriterVectorExecutionRequired: Boolean,
    val futureAndroidParserWriterVectorExecutionRequired: Boolean,
    val futureParserImplementationRequiresSeparatePass: Boolean,
    val futureWriterImplementationRequiresSeparatePass: Boolean,
    val futureParserWriterKatExecutionRequiresSeparatePass: Boolean,
    val futureVaultStorageRepositoryRequiresSeparatePass: Boolean,
    val futureSecureStorageSuccessRequiresSeparatePass: Boolean,
    val futureSecureMetadataSuccessRequiresSeparatePass: Boolean,
    val futureProductionSyncRequiresSeparatePass: Boolean,
    val futureProductionProviderSelectionRequiresSeparatePass: Boolean,
    val syntheticVectorBytesPresent: Boolean,
    val inMemoryVectorBytesPresent: Boolean,
    val productionVectorBytesPresent: Boolean,
    val parserVectorExecutionPresent: Boolean,
    val writerVectorExecutionPresent: Boolean,
    val roundTripVectorExecutionPresent: Boolean,
    val negativeVectorExecutionPresent: Boolean,
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
    val syntheticVectorCreationAuthorizationPresent: Boolean,
    val inMemoryVectorExecutionAuthorizationPresent: Boolean,
    val parserVectorExecutionAuthorizationPresent: Boolean,
    val writerVectorExecutionAuthorizationPresent: Boolean,
    val parserImplementationAuthorizationPresent: Boolean,
    val writerImplementationAuthorizationPresent: Boolean,
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
    val parserWriterTestVectorAdmissionPassedIsLaterBranchOnly: Boolean,
    val futureSyntheticVectorCreationAdmittedIsNotVectorCreation: Boolean,
    val futureInMemoryVectorExecutionAdmittedIsNotVectorExecution: Boolean,
    val futureParserVectorCoverageAdmittedIsNotParserImplementation: Boolean,
    val futureWriterVectorCoverageAdmittedIsNotWriterImplementation: Boolean,
    val futureRoundTripVectorCoverageAdmittedIsNotRoundTripExecution: Boolean,
    val futureNegativeVectorCoverageAdmittedIsNotNegativeVectorExecution: Boolean,
    val futureRedactedVectorDiagnosticsAdmittedIsNotDiagnosticsImplementation: Boolean,
    val futureVectorsSyntheticNonWalletNonUserNonNetworkNonSecret: Boolean,
    val futureVectorsTestSourceOnly: Boolean,
    val futureVectorsContainNoWalletOrInfrastructureMaterial: Boolean,
    val futureVectorsMayUseSafeStructuralLabelsAndCounters: Boolean,
    val futureVectorsAvoidProductionContinuousHex: Boolean,
    val futureVectorBytesNotInProductionSourceByDefault: Boolean,
    val futureVectorBytesNotInDisplayOutput: Boolean,
    val futureVectorDiagnosticsSafeLabelOnly: Boolean,
    val futureDesktopJvmVectorExecutionBeforeRepositorySuccess: Boolean,
    val futureAndroidRuntimeVectorExecutionBeforeRepositorySuccess: Boolean,
    val futureNegativeVectorsDistinguishFailureClasses: Boolean,
    val noVectorsCreatedInThisBranch: Boolean,
    val noParserWriterKatRunsInThisBranch: Boolean,
    val testVectorPolicyCreatesNoVectorBytes: Boolean,
    val testVectorPolicyCreatesNoParserInputBytes: Boolean,
    val testVectorPolicyCreatesNoWriterOutputBytes: Boolean,
    val testVectorPolicyExecutesNoKat: Boolean,
    val diagnosticsPolicyContainsNoSensitiveMaterial: Boolean,
    val normalSourceMaterialGuardExcludesBuildHistory: Boolean,
    val localArtifactRootExcludedFromNormalSourceMaterialCorpus: Boolean,
    val parserWriterTestVectorAdmissionDecisionPassed: Boolean,
    val evidenceCount: Int,
    val admissionCheckCount: Int,
    val blockerCount: Int,
    val warningCount: Int,
    val policyLabels: List<EncryptedVaultParserWriterTestVectorAdmissionPolicyLabel>,
    val syntheticVectorClasses: List<EncryptedVaultParserWriterSyntheticVectorClass>,
    val admissionChecks: List<EncryptedVaultParserWriterTestVectorAdmissionCheck>,
    val failureLabels: List<EncryptedVaultParserWriterTestVectorAdmissionFailureLabel>,
    val displayLabel: EncryptedVaultParserWriterTestVectorAdmissionSafeLabel,
) {
    override fun toString(): String =
        "EncryptedVaultParserWriterTestVectorAdmission(" +
            "REDACTED, COMMON_MAIN_POLICY, TEST_VECTOR_ADMISSION_ONLY, " +
            "LATER_BRANCHES_ONLY, POLICY_LABELS_ONLY, NO_VECTOR_CREATION, " +
            "NO_PARSER, NO_WRITER, NO_SERIALIZATION_PARSING, NO_ARTIFACT_MATERIAL, " +
            "NO_IO, NO_CRYPTO_EXECUTION, NO_RUNTIME_CRYPTO_MATERIAL, " +
            "NO_MIGRATION_REPAIR, NO_RUNTIME_SESSION, NO_SECURE_STORAGE_SUCCESS, " +
            "NO_PRODUCTION_SYNC, DISABLED_PROVIDER_ONLY, " +
            "NO_SIGNING_BROADCASTING_UI_ENDPOINT_MAINNET" +
            ")"
}

object EncryptedVaultParserWriterTestVectorAdmissionPolicy {
    fun currentParserWriterTestVectorAdmission(): EncryptedVaultParserWriterTestVectorAdmission {
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
        val parserWriterGate =
            EncryptedVaultParserWriterAdmissionGatePolicy.currentParserWriterAdmissionGate()
        val providerSelection = VaultCryptoProviderSelectionRegistry.select()
        val checks = EncryptedVaultParserWriterTestVectorAdmissionCheck.entries.toList()
        val policyLabels = EncryptedVaultParserWriterTestVectorAdmissionPolicyLabel.entries.toList()
        val syntheticVectorClasses = EncryptedVaultParserWriterSyntheticVectorClass.entries.toList()

        val storageReadinessDecisionPresent = storageDecision.storageReadinessDecisionPassed
        val containerFormatV1DecisionPresent = containerDecision.containerFormatV1DecisionPassed
        val storagePathSessionLifecycleDecisionPresent =
            storagePathDecision.storagePathSessionLifecycleDecisionPassed
        val migrationCorruptionPolicyDecisionPresent =
            migrationDecision.migrationCorruptionPolicyDecisionPassed
        val parserWriterAdmissionGatePresent =
            parserWriterGate.parserWriterAdmissionGateDecisionPassed
        val encryptedVaultDesignPresent =
            parserWriterGate.encryptedVaultDesignPresent &&
                storageDecision.encryptedVaultDesignPresent &&
                containerDecision.encryptedVaultDesignPresent &&
                storagePathDecision.encryptedVaultDesignPresent &&
                migrationDecision.encryptedVaultDesignPresent
        val cryptoDecisionPresent =
            parserWriterGate.cryptoDecisionPresent &&
                storageDecision.cryptoDecisionPresent &&
                containerDecision.cryptoDecisionPresent &&
                storagePathDecision.cryptoDecisionPresent &&
                migrationDecision.cryptoDecisionPresent
        val dependencyReviewPresent =
            parserWriterGate.dependencyReviewPresent &&
                storageDecision.dependencyReviewPresent &&
                containerDecision.dependencyReviewPresent &&
                storagePathDecision.dependencyReviewPresent &&
                migrationDecision.dependencyReviewPresent
        val providerBoundaryPresent =
            parserWriterGate.providerBoundaryPresent &&
                storageDecision.disabledVaultCryptoProviderBoundaryPresent &&
                containerDecision.vaultCryptoProviderBoundaryPresent &&
                storagePathDecision.providerBoundaryPresent &&
                migrationDecision.providerBoundaryPresent
        val secureStorageBoundaryPresent =
            parserWriterGate.secureStorageBoundaryPresent &&
                storageDecision.secureStorageBoundaryPresent &&
                storagePathDecision.secureStorageBoundaryPresent &&
                migrationDecision.secureStorageBoundaryPresent
        val secureMetadataBoundaryPresent =
            parserWriterGate.secureMetadataBoundaryPresent &&
                storageDecision.secureMetadataBoundaryPresent &&
                storagePathDecision.secureMetadataBoundaryPresent &&
                migrationDecision.secureMetadataBoundaryPresent

        val futureSyntheticVectorCreationAdmitted = true
        val futureInMemoryVectorExecutionAdmitted = true
        val futureParserVectorCoverageAdmitted = true
        val futureWriterVectorCoverageAdmitted = true
        val futureRoundTripVectorCoverageAdmitted = true
        val futureNegativeVectorCoverageAdmitted = true
        val futureRedactedVectorDiagnosticsAdmitted = true
        val futureDesktopParserWriterVectorExecutionRequired = true
        val futureAndroidParserWriterVectorExecutionRequired = true
        val futureParserImplementationRequiresSeparatePass =
            parserWriterGate.futureVaultParserImplementationRequiresSeparatePass
        val futureWriterImplementationRequiresSeparatePass =
            parserWriterGate.futureVaultWriterImplementationRequiresSeparatePass
        val futureParserWriterKatExecutionRequiresSeparatePass =
            parserWriterGate.futureParserWriterKatExecutionRequiresSeparatePass
        val futureVaultStorageRepositoryRequiresSeparatePass =
            parserWriterGate.futureVaultStorageRepositoryRequiresSeparatePass
        val futureSecureStorageSuccessRequiresSeparatePass =
            parserWriterGate.futureSecureStorageSuccessRequiresSeparatePass
        val futureSecureMetadataSuccessRequiresSeparatePass =
            parserWriterGate.futureSecureMetadataSuccessRequiresSeparatePass
        val futureProductionSyncRequiresSeparatePass =
            parserWriterGate.futureProductionSyncRequiresSeparatePass
        val futureProductionProviderSelectionRequiresSeparatePass =
            parserWriterGate.futureProductionProviderSelectionRequiresSeparatePass

        val syntheticVectorBytesPresent = false
        val inMemoryVectorBytesPresent = false
        val productionVectorBytesPresent = false
        val parserVectorExecutionPresent = false
        val writerVectorExecutionPresent = false
        val roundTripVectorExecutionPresent = false
        val negativeVectorExecutionPresent = false
        val vaultParserImplementationPresent = parserWriterGate.vaultParserImplementationPresent
        val vaultWriterImplementationPresent = parserWriterGate.vaultWriterImplementationPresent
        val vaultContainerSerializationPresent = parserWriterGate.vaultContainerSerializationPresent
        val vaultContainerParsingPresent = parserWriterGate.vaultContainerParsingPresent
        val vaultContainerBytesProduced = parserWriterGate.vaultContainerBytesProduced
        val vaultContainerBytesConsumed = parserWriterGate.vaultContainerBytesConsumed
        val vaultHeaderSerialized = parserWriterGate.vaultHeaderSerialized
        val vaultHeaderParsed = parserWriterGate.vaultHeaderParsed
        val vaultRecordDirectorySerialized = parserWriterGate.vaultRecordDirectorySerialized
        val vaultRecordDirectoryParsed = parserWriterGate.vaultRecordDirectoryParsed
        val vaultRecordEnvelopeSerialized = parserWriterGate.vaultRecordEnvelopeSerialized
        val vaultRecordEnvelopeParsed = parserWriterGate.vaultRecordEnvelopeParsed
        val vaultFileReadPresent = parserWriterGate.vaultFileReadPresent
        val vaultFileWritePresent = parserWriterGate.vaultFileWritePresent
        val vaultFileDeletePresent = parserWriterGate.vaultFileDeletePresent
        val vaultDirectoryCreated = parserWriterGate.vaultDirectoryCreated
        val atomicReplaceImplementationPresent = parserWriterGate.atomicReplaceImplementationPresent
        val partialWriteDetectionImplementationPresent =
            parserWriterGate.partialWriteDetectionImplementationPresent
        val migrationImplementationPresent = parserWriterGate.migrationImplementationPresent
        val migrationExecutionPresent = parserWriterGate.migrationExecutionPresent
        val corruptionDetectionImplementationPresent =
            parserWriterGate.corruptionDetectionImplementationPresent
        val corruptionRepairImplementationPresent =
            parserWriterGate.corruptionRepairImplementationPresent
        val backupCreationPresent = parserWriterGate.backupCreationPresent
        val rollbackImplementationPresent = parserWriterGate.rollbackImplementationPresent
        val kdfExecutionPresent = parserWriterGate.kdfExecutionPresent
        val aeadExecutionPresent = parserWriterGate.aeadExecutionPresent
        val encryptionExecutionPresent = parserWriterGate.encryptionExecutionPresent
        val decryptionExecutionPresent = parserWriterGate.decryptionExecutionPresent
        val keyGenerationPresent = parserWriterGate.keyGenerationPresent
        val nonceGenerationPresent = parserWriterGate.nonceGenerationPresent
        val tinkKeysetCreationPresent = parserWriterGate.tinkKeysetCreationPresent
        val tinkKeysetPersistencePresent = parserWriterGate.tinkKeysetPersistencePresent
        val vaultStoragePathImplementationPresent =
            parserWriterGate.vaultStoragePathImplementationPresent
        val encryptedVaultFileFormatImplemented = parserWriterGate.encryptedVaultFileFormatImplemented
        val encryptedVaultRepositorySuccessPresent =
            parserWriterGate.encryptedVaultRepositorySuccessPresent
        val lockSessionImplementationPresent = parserWriterGate.lockSessionImplementationPresent
        val unlockImplementationPresent = parserWriterGate.unlockImplementationPresent
        val runtimeSessionKeyPresent = parserWriterGate.runtimeSessionKeyPresent
        val sessionKeyCached = parserWriterGate.sessionKeyCached
        val plaintextCachePresent = parserWriterGate.plaintextCachePresent
        val secureSecretStorageSuccessPathPresent =
            parserWriterGate.secureSecretStorageSuccessPathPresent
        val secureMetadataStorageSuccessPathPresent =
            parserWriterGate.secureMetadataStorageSuccessPathPresent
        val productionObservationPersistencePresent =
            parserWriterGate.productionObservationPersistencePresent
        val productionAddressIndexPersistencePresent =
            parserWriterGate.productionAddressIndexPersistencePresent
        val productionUtxoPersistencePresent = parserWriterGate.productionUtxoPersistencePresent
        val productionWalletHistoryPersistencePresent =
            parserWriterGate.productionWalletHistoryPersistencePresent
        val productionSyncPresent = parserWriterGate.productionSyncPresent
        val productionBackendClientPresent = parserWriterGate.productionBackendClientPresent
        val productionProviderSelectionEnabled = !providerSelection.selectedProviderIsDisabled
        val productionProviderSelectable = providerSelection.productionProviderSelectable
        val productionSelectionStillDisabledProviderOnly =
            providerSelection.selectedProviderIsDisabled &&
                !productionProviderSelectionEnabled &&
                !productionProviderSelectable
        val signingBroadcastingPresent = parserWriterGate.signingBroadcastingPresent
        val uiActionEnablementPresent = parserWriterGate.uiActionEnablementPresent
        val endpointPresent = parserWriterGate.endpointPresent
        val mainnetPresent = parserWriterGate.mainnetPresent

        val syntheticVectorCreationAuthorizationPresent = false
        val inMemoryVectorExecutionAuthorizationPresent = false
        val parserVectorExecutionAuthorizationPresent = false
        val writerVectorExecutionAuthorizationPresent = false
        val parserImplementationAuthorizationPresent = false
        val writerImplementationAuthorizationPresent = false
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

        val parserWriterTestVectorAdmissionPassedIsLaterBranchOnly =
            futureSyntheticVectorCreationAdmitted &&
                futureInMemoryVectorExecutionAdmitted &&
                futureParserWriterKatExecutionRequiresSeparatePass &&
                !syntheticVectorBytesPresent &&
                !inMemoryVectorBytesPresent
        val futureSyntheticVectorCreationAdmittedIsNotVectorCreation =
            futureSyntheticVectorCreationAdmitted &&
                !syntheticVectorBytesPresent &&
                !productionVectorBytesPresent
        val futureInMemoryVectorExecutionAdmittedIsNotVectorExecution =
            futureInMemoryVectorExecutionAdmitted &&
                !parserVectorExecutionPresent &&
                !writerVectorExecutionPresent &&
                !roundTripVectorExecutionPresent &&
                !negativeVectorExecutionPresent
        val futureParserVectorCoverageAdmittedIsNotParserImplementation =
            futureParserVectorCoverageAdmitted &&
                !vaultParserImplementationPresent &&
                !parserVectorExecutionPresent
        val futureWriterVectorCoverageAdmittedIsNotWriterImplementation =
            futureWriterVectorCoverageAdmitted &&
                !vaultWriterImplementationPresent &&
                !writerVectorExecutionPresent
        val futureRoundTripVectorCoverageAdmittedIsNotRoundTripExecution =
            futureRoundTripVectorCoverageAdmitted && !roundTripVectorExecutionPresent
        val futureNegativeVectorCoverageAdmittedIsNotNegativeVectorExecution =
            futureNegativeVectorCoverageAdmitted && !negativeVectorExecutionPresent
        val futureRedactedVectorDiagnosticsAdmittedIsNotDiagnosticsImplementation =
            futureRedactedVectorDiagnosticsAdmitted &&
                !parserVectorExecutionPresent &&
                !writerVectorExecutionPresent
        val futureVectorsSyntheticNonWalletNonUserNonNetworkNonSecret = true
        val futureVectorsTestSourceOnly = true
        val futureVectorsContainNoWalletOrInfrastructureMaterial = true
        val futureVectorsMayUseSafeStructuralLabelsAndCounters = true
        val futureVectorsAvoidProductionContinuousHex = true
        val futureVectorBytesNotInProductionSourceByDefault = true
        val futureVectorBytesNotInDisplayOutput = true
        val futureVectorDiagnosticsSafeLabelOnly = true
        val futureDesktopJvmVectorExecutionBeforeRepositorySuccess = true
        val futureAndroidRuntimeVectorExecutionBeforeRepositorySuccess = true
        val futureNegativeVectorsDistinguishFailureClasses = true
        val noVectorsCreatedInThisBranch =
            !syntheticVectorBytesPresent &&
                !inMemoryVectorBytesPresent &&
                !productionVectorBytesPresent
        val noParserWriterKatRunsInThisBranch =
            !parserVectorExecutionPresent &&
                !writerVectorExecutionPresent &&
                !roundTripVectorExecutionPresent &&
                !negativeVectorExecutionPresent
        val testVectorPolicyCreatesNoVectorBytes = noVectorsCreatedInThisBranch
        val testVectorPolicyCreatesNoParserInputBytes = !vaultContainerBytesConsumed
        val testVectorPolicyCreatesNoWriterOutputBytes = !vaultContainerBytesProduced
        val testVectorPolicyExecutesNoKat = noParserWriterKatRunsInThisBranch
        val diagnosticsPolicyContainsNoSensitiveMaterial =
            (policyLabels.map { it.safeLabel.value } + syntheticVectorClasses.map { it.safeLabel.value })
                .all { label ->
                    "/" !in label &&
                        "\\" !in label &&
                        "." !in label &&
                        "~" !in label &&
                        !label.contains("path", ignoreCase = true) &&
                        !label.contains("file", ignoreCase = true) &&
                        !label.contains("hash", ignoreCase = true) &&
                        !label.contains("mac", ignoreCase = true) &&
                        !label.contains("stack", ignoreCase = true) &&
                        !label.contains("raw", ignoreCase = true) &&
                        !label.contains("payload", ignoreCase = true)
                }
        val normalSourceMaterialGuardExcludesBuildHistory = true
        val localArtifactRootExcludedFromNormalSourceMaterialCorpus = true

        val priorEvidencePresent =
            storageReadinessDecisionPresent &&
                containerFormatV1DecisionPresent &&
                storagePathSessionLifecycleDecisionPresent &&
                migrationCorruptionPolicyDecisionPresent &&
                parserWriterAdmissionGatePresent &&
                encryptedVaultDesignPresent &&
                cryptoDecisionPresent &&
                dependencyReviewPresent &&
                providerBoundaryPresent &&
                secureStorageBoundaryPresent &&
                secureMetadataBoundaryPresent
        val admissionPresent =
            futureSyntheticVectorCreationAdmitted &&
                futureInMemoryVectorExecutionAdmitted &&
                futureParserVectorCoverageAdmitted &&
                futureWriterVectorCoverageAdmitted &&
                futureRoundTripVectorCoverageAdmitted &&
                futureNegativeVectorCoverageAdmitted &&
                futureRedactedVectorDiagnosticsAdmitted &&
                futureDesktopParserWriterVectorExecutionRequired &&
                futureAndroidParserWriterVectorExecutionRequired
        val futureSeparatePassGatesPresent =
            futureParserImplementationRequiresSeparatePass &&
                futureWriterImplementationRequiresSeparatePass &&
                futureParserWriterKatExecutionRequiresSeparatePass &&
                futureVaultStorageRepositoryRequiresSeparatePass &&
                futureSecureStorageSuccessRequiresSeparatePass &&
                futureSecureMetadataSuccessRequiresSeparatePass &&
                futureProductionSyncRequiresSeparatePass &&
                futureProductionProviderSelectionRequiresSeparatePass
        val vectorRuntimeSurfacePresent =
            syntheticVectorBytesPresent ||
                inMemoryVectorBytesPresent ||
                productionVectorBytesPresent ||
                parserVectorExecutionPresent ||
                writerVectorExecutionPresent ||
                roundTripVectorExecutionPresent ||
                negativeVectorExecutionPresent
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
            syntheticVectorCreationAuthorizationPresent ||
                inMemoryVectorExecutionAuthorizationPresent ||
                parserVectorExecutionAuthorizationPresent ||
                writerVectorExecutionAuthorizationPresent ||
                parserImplementationAuthorizationPresent ||
                writerImplementationAuthorizationPresent ||
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
        val vectorPolicyMaterialAbsent =
            testVectorPolicyCreatesNoVectorBytes &&
                testVectorPolicyCreatesNoParserInputBytes &&
                testVectorPolicyCreatesNoWriterOutputBytes &&
                testVectorPolicyExecutesNoKat &&
                futureVectorsTestSourceOnly &&
                futureVectorsContainNoWalletOrInfrastructureMaterial &&
                diagnosticsPolicyContainsNoSensitiveMaterial
        val corpusBoundaryPresent =
            normalSourceMaterialGuardExcludesBuildHistory &&
                localArtifactRootExcludedFromNormalSourceMaterialCorpus

        val failures = buildList {
            if (!priorEvidencePresent) {
                add(EncryptedVaultParserWriterTestVectorAdmissionFailureLabel.PriorEvidenceMissing)
            }
            if (!admissionPresent) {
                add(
                    EncryptedVaultParserWriterTestVectorAdmissionFailureLabel
                        .TestVectorAdmissionMissing,
                )
            }
            if (!futureSeparatePassGatesPresent) {
                add(
                    EncryptedVaultParserWriterTestVectorAdmissionFailureLabel
                        .FutureSeparatePassGateMissing,
                )
            }
            if (vectorRuntimeSurfacePresent) {
                add(
                    EncryptedVaultParserWriterTestVectorAdmissionFailureLabel
                        .VectorRuntimeSurfacePresent,
                )
            }
            if (parserWriterRuntimeSurfacePresent) {
                add(
                    EncryptedVaultParserWriterTestVectorAdmissionFailureLabel
                        .ParserWriterRuntimeSurfacePresent,
                )
            }
            if (fileStorageRuntimeSurfacePresent) {
                add(
                    EncryptedVaultParserWriterTestVectorAdmissionFailureLabel
                        .FileStorageRuntimeSurfacePresent,
                )
            }
            if (migrationCorruptionRuntimeSurfacePresent) {
                add(
                    EncryptedVaultParserWriterTestVectorAdmissionFailureLabel
                        .MigrationCorruptionRuntimeSurfacePresent,
                )
            }
            if (cryptoRuntimeSurfacePresent) {
                add(
                    EncryptedVaultParserWriterTestVectorAdmissionFailureLabel
                        .CryptoRuntimeSurfacePresent,
                )
            }
            if (lockSessionRuntimeSurfacePresent) {
                add(
                    EncryptedVaultParserWriterTestVectorAdmissionFailureLabel
                        .LockSessionRuntimeSurfacePresent,
                )
            }
            if (productionStorageSurfacePresent) {
                add(
                    EncryptedVaultParserWriterTestVectorAdmissionFailureLabel
                        .ProductionStorageSurfacePresent,
                )
            }
            if (productionSyncSurfacePresent) {
                add(
                    EncryptedVaultParserWriterTestVectorAdmissionFailureLabel
                        .ProductionSyncSurfacePresent,
                )
            }
            if (productionProviderSelectionSurfacePresent || !productionSelectionStillDisabledProviderOnly) {
                add(
                    EncryptedVaultParserWriterTestVectorAdmissionFailureLabel
                        .ProductionProviderSelectionSurfacePresent,
                )
            }
            if (signingBroadcastingUiEndpointOrMainnetSurfacePresent) {
                add(
                    EncryptedVaultParserWriterTestVectorAdmissionFailureLabel
                        .SigningBroadcastingUiEndpointOrMainnetSurfacePresent,
                )
            }
            if (productionAuthorizationPresent) {
                add(
                    EncryptedVaultParserWriterTestVectorAdmissionFailureLabel
                        .ProductionAuthorizationPresent,
                )
            }
            if (!vectorPolicyMaterialAbsent) {
                add(
                    EncryptedVaultParserWriterTestVectorAdmissionFailureLabel
                        .VectorPolicyMaterialPresent,
                )
            }
            if (!corpusBoundaryPresent) {
                add(EncryptedVaultParserWriterTestVectorAdmissionFailureLabel.CorpusBoundaryMissing)
            }
        }

        val parserWriterTestVectorAdmissionPassed = failures.isEmpty()
        val parserWriterTestVectorAdmissionDecisionPassed =
            parserWriterTestVectorAdmissionPassed &&
                parserWriterTestVectorAdmissionPassedIsLaterBranchOnly &&
                futureSyntheticVectorCreationAdmittedIsNotVectorCreation &&
                futureInMemoryVectorExecutionAdmittedIsNotVectorExecution &&
                futureParserVectorCoverageAdmittedIsNotParserImplementation &&
                futureWriterVectorCoverageAdmittedIsNotWriterImplementation &&
                futureRoundTripVectorCoverageAdmittedIsNotRoundTripExecution &&
                futureNegativeVectorCoverageAdmittedIsNotNegativeVectorExecution &&
                testVectorPolicyCreatesNoVectorBytes &&
                testVectorPolicyCreatesNoParserInputBytes &&
                testVectorPolicyCreatesNoWriterOutputBytes &&
                testVectorPolicyExecutesNoKat

        return EncryptedVaultParserWriterTestVectorAdmission(
            admissionId = EncryptedVaultParserWriterTestVectorAdmissionSafeLabel(
                "skald-encrypted-local-vault-parser-writer-test-vector-admission-v1",
            ),
            admissionVersion = 1,
            admissionKind =
                EncryptedVaultParserWriterTestVectorAdmissionKind
                    .EncryptedLocalVaultParserWriterTestVectorAdmission,
            sourceSet = EncryptedVaultParserWriterTestVectorAdmissionSourceSet.CommonMainPolicy,
            storageReadinessDecisionPresent = storageReadinessDecisionPresent,
            containerFormatV1DecisionPresent = containerFormatV1DecisionPresent,
            storagePathSessionLifecycleDecisionPresent =
                storagePathSessionLifecycleDecisionPresent,
            migrationCorruptionPolicyDecisionPresent = migrationCorruptionPolicyDecisionPresent,
            parserWriterAdmissionGatePresent = parserWriterAdmissionGatePresent,
            encryptedVaultDesignPresent = encryptedVaultDesignPresent,
            cryptoDecisionPresent = cryptoDecisionPresent,
            dependencyReviewPresent = dependencyReviewPresent,
            providerBoundaryPresent = providerBoundaryPresent,
            secureStorageBoundaryPresent = secureStorageBoundaryPresent,
            secureMetadataBoundaryPresent = secureMetadataBoundaryPresent,
            parserWriterTestVectorAdmissionPassed = parserWriterTestVectorAdmissionPassed,
            futureSyntheticVectorCreationAdmitted = futureSyntheticVectorCreationAdmitted,
            futureInMemoryVectorExecutionAdmitted = futureInMemoryVectorExecutionAdmitted,
            futureParserVectorCoverageAdmitted = futureParserVectorCoverageAdmitted,
            futureWriterVectorCoverageAdmitted = futureWriterVectorCoverageAdmitted,
            futureRoundTripVectorCoverageAdmitted = futureRoundTripVectorCoverageAdmitted,
            futureNegativeVectorCoverageAdmitted = futureNegativeVectorCoverageAdmitted,
            futureRedactedVectorDiagnosticsAdmitted = futureRedactedVectorDiagnosticsAdmitted,
            futureDesktopParserWriterVectorExecutionRequired =
                futureDesktopParserWriterVectorExecutionRequired,
            futureAndroidParserWriterVectorExecutionRequired =
                futureAndroidParserWriterVectorExecutionRequired,
            futureParserImplementationRequiresSeparatePass =
                futureParserImplementationRequiresSeparatePass,
            futureWriterImplementationRequiresSeparatePass =
                futureWriterImplementationRequiresSeparatePass,
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
            syntheticVectorBytesPresent = syntheticVectorBytesPresent,
            inMemoryVectorBytesPresent = inMemoryVectorBytesPresent,
            productionVectorBytesPresent = productionVectorBytesPresent,
            parserVectorExecutionPresent = parserVectorExecutionPresent,
            writerVectorExecutionPresent = writerVectorExecutionPresent,
            roundTripVectorExecutionPresent = roundTripVectorExecutionPresent,
            negativeVectorExecutionPresent = negativeVectorExecutionPresent,
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
            syntheticVectorCreationAuthorizationPresent =
                syntheticVectorCreationAuthorizationPresent,
            inMemoryVectorExecutionAuthorizationPresent =
                inMemoryVectorExecutionAuthorizationPresent,
            parserVectorExecutionAuthorizationPresent = parserVectorExecutionAuthorizationPresent,
            writerVectorExecutionAuthorizationPresent = writerVectorExecutionAuthorizationPresent,
            parserImplementationAuthorizationPresent = parserImplementationAuthorizationPresent,
            writerImplementationAuthorizationPresent = writerImplementationAuthorizationPresent,
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
            parserWriterTestVectorAdmissionPassedIsLaterBranchOnly =
                parserWriterTestVectorAdmissionPassedIsLaterBranchOnly,
            futureSyntheticVectorCreationAdmittedIsNotVectorCreation =
                futureSyntheticVectorCreationAdmittedIsNotVectorCreation,
            futureInMemoryVectorExecutionAdmittedIsNotVectorExecution =
                futureInMemoryVectorExecutionAdmittedIsNotVectorExecution,
            futureParserVectorCoverageAdmittedIsNotParserImplementation =
                futureParserVectorCoverageAdmittedIsNotParserImplementation,
            futureWriterVectorCoverageAdmittedIsNotWriterImplementation =
                futureWriterVectorCoverageAdmittedIsNotWriterImplementation,
            futureRoundTripVectorCoverageAdmittedIsNotRoundTripExecution =
                futureRoundTripVectorCoverageAdmittedIsNotRoundTripExecution,
            futureNegativeVectorCoverageAdmittedIsNotNegativeVectorExecution =
                futureNegativeVectorCoverageAdmittedIsNotNegativeVectorExecution,
            futureRedactedVectorDiagnosticsAdmittedIsNotDiagnosticsImplementation =
                futureRedactedVectorDiagnosticsAdmittedIsNotDiagnosticsImplementation,
            futureVectorsSyntheticNonWalletNonUserNonNetworkNonSecret =
                futureVectorsSyntheticNonWalletNonUserNonNetworkNonSecret,
            futureVectorsTestSourceOnly = futureVectorsTestSourceOnly,
            futureVectorsContainNoWalletOrInfrastructureMaterial =
                futureVectorsContainNoWalletOrInfrastructureMaterial,
            futureVectorsMayUseSafeStructuralLabelsAndCounters =
                futureVectorsMayUseSafeStructuralLabelsAndCounters,
            futureVectorsAvoidProductionContinuousHex = futureVectorsAvoidProductionContinuousHex,
            futureVectorBytesNotInProductionSourceByDefault =
                futureVectorBytesNotInProductionSourceByDefault,
            futureVectorBytesNotInDisplayOutput = futureVectorBytesNotInDisplayOutput,
            futureVectorDiagnosticsSafeLabelOnly = futureVectorDiagnosticsSafeLabelOnly,
            futureDesktopJvmVectorExecutionBeforeRepositorySuccess =
                futureDesktopJvmVectorExecutionBeforeRepositorySuccess,
            futureAndroidRuntimeVectorExecutionBeforeRepositorySuccess =
                futureAndroidRuntimeVectorExecutionBeforeRepositorySuccess,
            futureNegativeVectorsDistinguishFailureClasses =
                futureNegativeVectorsDistinguishFailureClasses,
            noVectorsCreatedInThisBranch = noVectorsCreatedInThisBranch,
            noParserWriterKatRunsInThisBranch = noParserWriterKatRunsInThisBranch,
            testVectorPolicyCreatesNoVectorBytes = testVectorPolicyCreatesNoVectorBytes,
            testVectorPolicyCreatesNoParserInputBytes =
                testVectorPolicyCreatesNoParserInputBytes,
            testVectorPolicyCreatesNoWriterOutputBytes =
                testVectorPolicyCreatesNoWriterOutputBytes,
            testVectorPolicyExecutesNoKat = testVectorPolicyExecutesNoKat,
            diagnosticsPolicyContainsNoSensitiveMaterial =
                diagnosticsPolicyContainsNoSensitiveMaterial,
            normalSourceMaterialGuardExcludesBuildHistory =
                normalSourceMaterialGuardExcludesBuildHistory,
            localArtifactRootExcludedFromNormalSourceMaterialCorpus =
                localArtifactRootExcludedFromNormalSourceMaterialCorpus,
            parserWriterTestVectorAdmissionDecisionPassed =
                parserWriterTestVectorAdmissionDecisionPassed,
            evidenceCount = 11,
            admissionCheckCount = checks.size,
            blockerCount = failures.size,
            warningCount = 0,
            policyLabels = policyLabels,
            syntheticVectorClasses = syntheticVectorClasses,
            admissionChecks = checks,
            failureLabels = failures,
            displayLabel = EncryptedVaultParserWriterTestVectorAdmissionSafeLabel(
                "encrypted vault parser writer test vector admission",
            ),
        )
    }
}
