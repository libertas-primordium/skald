package com.libertasprimordium.skald.security

@JvmInline
value class EncryptedVaultWorkingParserAdmissionSafeLabel(val value: String) {
    override fun toString(): String = "EncryptedVaultWorkingParserAdmissionSafeLabel(REDACTED)"
}

enum class EncryptedVaultWorkingParserAdmissionKind(val label: String) {
    EncryptedLocalVaultWorkingParserAdmissionGate(
        "ENCRYPTED_LOCAL_VAULT_WORKING_PARSER_ADMISSION_GATE",
    ),
}

enum class EncryptedVaultWorkingParserAdmissionSourceSet(val label: String) {
    CommonMainPolicy("COMMON_MAIN_POLICY"),
}

enum class EncryptedVaultWorkingParserAdmissionPolicyLabel(
    val safeLabel: EncryptedVaultWorkingParserAdmissionSafeLabel,
) {
    WorkingParserAdmission(
        EncryptedVaultWorkingParserAdmissionSafeLabel(
            "skald-encrypted-local-vault-working-parser-admission-v1",
        ),
    ),
    InMemoryParserPolicy(
        EncryptedVaultWorkingParserAdmissionSafeLabel(
            "skald-vault-v1-in-memory-parser-policy",
        ),
    ),
    SyntheticVectorParserPolicy(
        EncryptedVaultWorkingParserAdmissionSafeLabel(
            "skald-vault-v1-synthetic-vector-parser-policy",
        ),
    ),
    ParserFailClosedPolicy(
        EncryptedVaultWorkingParserAdmissionSafeLabel(
            "skald-vault-v1-parser-fail-closed-policy",
        ),
    ),
    ParserRedactedResultPolicy(
        EncryptedVaultWorkingParserAdmissionSafeLabel(
            "skald-vault-v1-parser-redacted-result-policy",
        ),
    ),
    ParserErrorTaxonomyPolicy(
        EncryptedVaultWorkingParserAdmissionSafeLabel(
            "skald-vault-v1-parser-error-taxonomy-policy",
        ),
    ),
    NoFileIoParserPolicy(
        EncryptedVaultWorkingParserAdmissionSafeLabel(
            "skald-vault-v1-no-file-io-parser-policy",
        ),
    ),
    NoCryptoParserPolicy(
        EncryptedVaultWorkingParserAdmissionSafeLabel(
            "skald-vault-v1-no-crypto-parser-policy",
        ),
    ),
    DesktopParserVectorRequirementPolicy(
        EncryptedVaultWorkingParserAdmissionSafeLabel(
            "skald-vault-v1-desktop-parser-vector-requirement-policy",
        ),
    ),
    AndroidParserVectorRequirementPolicy(
        EncryptedVaultWorkingParserAdmissionSafeLabel(
            "skald-vault-v1-android-parser-vector-requirement-policy",
        ),
    ),
}

enum class EncryptedVaultWorkingParserAdmissionErrorTaxonomyLabel(
    val safeLabel: EncryptedVaultWorkingParserAdmissionSafeLabel,
) {
    UnsupportedVersion(
        EncryptedVaultWorkingParserAdmissionSafeLabel("unsupported-version"),
    ),
    UnsupportedCriticalFeature(
        EncryptedVaultWorkingParserAdmissionSafeLabel("unsupported-critical-feature"),
    ),
    TruncatedInput(
        EncryptedVaultWorkingParserAdmissionSafeLabel("truncated-input"),
    ),
    MalformedSectionOrder(
        EncryptedVaultWorkingParserAdmissionSafeLabel("malformed-section-order"),
    ),
    MigrationRequired(
        EncryptedVaultWorkingParserAdmissionSafeLabel("migration-required"),
    ),
    CorruptionSuspected(
        EncryptedVaultWorkingParserAdmissionSafeLabel("corruption-suspected"),
    ),
    StorageUnavailable(
        EncryptedVaultWorkingParserAdmissionSafeLabel("storage-unavailable"),
    ),
}

enum class EncryptedVaultWorkingParserAdmissionCheck {
    StorageReadinessDecisionPresent,
    ContainerFormatV1DecisionPresent,
    StoragePathSessionLifecycleDecisionPresent,
    MigrationCorruptionPolicyDecisionPresent,
    ParserWriterAdmissionGatePresent,
    ParserWriterTestVectorAdmissionPresent,
    ParserWriterImplementationScaffoldPresent,
    ParserWriterSyntheticVectorCatalogPresent,
    EncryptedVaultDesignPresent,
    CryptoDecisionPresent,
    DependencyReviewPresent,
    ProviderBoundaryPresent,
    SecureStorageBoundaryPresent,
    SecureMetadataBoundaryPresent,
    WorkingParserAdmissionGatePassed,
    FutureCommonMainInMemoryParserAdmitted,
    FutureSyntheticVectorParserExecutionAdmitted,
    FutureParserFailClosedPolicyAdmitted,
    FutureParserRedactedResultPolicyAdmitted,
    FutureParserErrorTaxonomyAdmitted,
    FutureUnsupportedVersionPolicyAdmitted,
    FutureUnsupportedCriticalFeaturePolicyAdmitted,
    FutureTruncatedInputPolicyAdmitted,
    FutureMigrationRequiredPolicyAdmitted,
    FutureCorruptionSuspectedPolicyAdmitted,
    FutureDesktopParserVectorExecutionRequired,
    FutureAndroidParserVectorExecutionRequired,
    FutureWorkingParserImplementationRequiresSeparatePass,
    FutureWorkingWriterImplementationRequiresSeparatePass,
    FutureParserWriterRoundTripRequiresSeparatePass,
    FutureVaultStorageRepositoryRequiresSeparatePass,
    FutureSecureStorageSuccessRequiresSeparatePass,
    FutureSecureMetadataSuccessRequiresSeparatePass,
    FutureProductionSyncRequiresSeparatePass,
    FutureProductionProviderSelectionRequiresSeparatePass,
    WorkingParserImplementationAbsent,
    WorkingWriterImplementationAbsent,
    ParserVectorExecutionAbsent,
    WriterVectorExecutionAbsent,
    RoundTripVectorExecutionAbsent,
    NegativeVectorExecutionAbsent,
    ProductionVectorBytesAbsent,
    ProductionParserInputBytesAbsent,
    ProductionWriterOutputBytesAbsent,
    VaultContainerSerializationAbsent,
    VaultContainerParsingAbsent,
    VaultContainerBytesProducedAbsent,
    VaultContainerBytesConsumedAbsent,
    VaultHeaderSerializedAbsent,
    VaultHeaderParsedAbsent,
    VaultRecordDirectorySerializedAbsent,
    VaultRecordDirectoryParsedAbsent,
    VaultRecordEnvelopeSerializedAbsent,
    VaultRecordEnvelopeParsedAbsent,
    VaultFileReadAbsent,
    VaultFileWriteAbsent,
    VaultFileDeleteAbsent,
    VaultDirectoryCreatedAbsent,
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
    AuthenticationExecutionAbsent,
    KeyGenerationAbsent,
    NonceGenerationAbsent,
    TinkKeysetCreationAbsent,
    TinkKeysetPersistenceAbsent,
    VaultStoragePathImplementationAbsent,
    EncryptedVaultFileFormatImplementedAbsent,
    EncryptedVaultRepositorySuccessAbsent,
    LockSessionImplementationAbsent,
    UnlockImplementationAbsent,
    RuntimeSessionKeyAbsent,
    SessionKeyCachedAbsent,
    PlaintextCacheAbsent,
    SecureSecretStorageSuccessPathAbsent,
    SecureMetadataStorageSuccessPathAbsent,
    ProductionObservationPersistenceAbsent,
    ProductionAddressIndexPersistenceAbsent,
    ProductionUtxoPersistenceAbsent,
    ProductionWalletHistoryPersistenceAbsent,
    ProductionSyncAbsent,
    ProductionBackendClientAbsent,
    ProductionProviderSelectionEnabledAbsent,
    ProductionProviderSelectableAbsent,
    ProductionSelectionStillDisabledProviderOnly,
    SigningBroadcastingAbsent,
    UiActionEnablementAbsent,
    EndpointAbsent,
    MainnetAbsent,
    WorkingParserImplementationAuthorizationAbsent,
    WorkingWriterImplementationAuthorizationAbsent,
    ParserVectorExecutionAuthorizationAbsent,
    WriterVectorExecutionAuthorizationAbsent,
    ProductionVectorCreationAuthorizationAbsent,
    SerializationAuthorizationAbsent,
    ParsingAuthorizationAbsent,
    FileReadAuthorizationAbsent,
    FileWriteAuthorizationAbsent,
    FileDeleteAuthorizationAbsent,
    DirectoryCreationAuthorizationAbsent,
    KdfAeadEncryptionDecryptionAuthenticationAuthorizationAbsent,
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
    FutureParserCommonMainOnly,
    FutureParserInMemoryOnly,
    FutureParserNoFileIo,
    FutureParserNoDirectoryCreation,
    FutureParserNoSharedPreferencesOrSettingsStorage,
    FutureParserNoKdfAeadEncryptionDecryptionAuthentication,
    FutureParserNoKeyNonceTink,
    FutureParserNoPersistenceOrRepositorySuccess,
    FutureParserSyntheticVectorsOnlyUntilSeparateApproval,
    FutureParserReturnsRedactedSkaldModelsOnly,
    FutureParserFailClosedOnRequiredFailures,
    FutureWriterImplementationRemainsSeparate,
    WorkingParserAdmissionCreatesNoParserObject,
    WorkingParserAdmissionConsumesNoBytes,
    WorkingParserAdmissionCreatesNoParsedHeader,
    WorkingParserAdmissionCreatesNoParsedDirectory,
    WorkingParserAdmissionCreatesNoParsedEnvelope,
    DiagnosticsPolicyContainsNoSensitiveMaterial,
    DisplayOutputRedactedOrSafeLabelOnly,
    NormalSourceMaterialGuardExcludesBuildHistory,
    LocalArtifactRootExcludedFromNormalSourceMaterialCorpus,
}

enum class EncryptedVaultWorkingParserAdmissionFailureLabel {
    PriorEvidenceMissing,
    WorkingParserAdmissionMissing,
    FutureSeparatePassGateMissing,
    ParserRuntimeSurfacePresent,
    WriterRuntimeSurfacePresent,
    VectorRuntimeSurfacePresent,
    ParserWriterMaterialSurfacePresent,
    FileStorageRuntimeSurfacePresent,
    MigrationCorruptionRuntimeSurfacePresent,
    CryptoRuntimeSurfacePresent,
    LockSessionRuntimeSurfacePresent,
    ProductionStorageSurfacePresent,
    ProductionSyncSurfacePresent,
    ProductionProviderSelectionSurfacePresent,
    SigningBroadcastingUiEndpointOrMainnetSurfacePresent,
    ProductionAuthorizationPresent,
    FutureParserPolicyMissing,
    DiagnosticsMaterialPresent,
    CorpusBoundaryMissing,
}

data class EncryptedVaultWorkingParserAdmissionGate(
    val admissionId: EncryptedVaultWorkingParserAdmissionSafeLabel,
    val admissionVersion: Int,
    val admissionKind: EncryptedVaultWorkingParserAdmissionKind,
    val sourceSet: EncryptedVaultWorkingParserAdmissionSourceSet,
    val storageReadinessDecisionPresent: Boolean,
    val containerFormatV1DecisionPresent: Boolean,
    val storagePathSessionLifecycleDecisionPresent: Boolean,
    val migrationCorruptionPolicyDecisionPresent: Boolean,
    val parserWriterAdmissionGatePresent: Boolean,
    val parserWriterTestVectorAdmissionPresent: Boolean,
    val parserWriterImplementationScaffoldPresent: Boolean,
    val parserWriterSyntheticVectorCatalogPresent: Boolean,
    val encryptedVaultDesignPresent: Boolean,
    val cryptoDecisionPresent: Boolean,
    val dependencyReviewPresent: Boolean,
    val providerBoundaryPresent: Boolean,
    val secureStorageBoundaryPresent: Boolean,
    val secureMetadataBoundaryPresent: Boolean,
    val workingParserAdmissionGatePassed: Boolean,
    val futureCommonMainInMemoryParserAdmitted: Boolean,
    val futureSyntheticVectorParserExecutionAdmitted: Boolean,
    val futureParserFailClosedPolicyAdmitted: Boolean,
    val futureParserRedactedResultPolicyAdmitted: Boolean,
    val futureParserErrorTaxonomyAdmitted: Boolean,
    val futureUnsupportedVersionPolicyAdmitted: Boolean,
    val futureUnsupportedCriticalFeaturePolicyAdmitted: Boolean,
    val futureTruncatedInputPolicyAdmitted: Boolean,
    val futureMigrationRequiredPolicyAdmitted: Boolean,
    val futureCorruptionSuspectedPolicyAdmitted: Boolean,
    val futureDesktopParserVectorExecutionRequired: Boolean,
    val futureAndroidParserVectorExecutionRequired: Boolean,
    val futureWorkingParserImplementationRequiresSeparatePass: Boolean,
    val futureWorkingWriterImplementationRequiresSeparatePass: Boolean,
    val futureParserWriterRoundTripRequiresSeparatePass: Boolean,
    val futureVaultStorageRepositoryRequiresSeparatePass: Boolean,
    val futureSecureStorageSuccessRequiresSeparatePass: Boolean,
    val futureSecureMetadataSuccessRequiresSeparatePass: Boolean,
    val futureProductionSyncRequiresSeparatePass: Boolean,
    val futureProductionProviderSelectionRequiresSeparatePass: Boolean,
    val workingParserImplementationPresent: Boolean,
    val workingWriterImplementationPresent: Boolean,
    val parserVectorExecutionPresent: Boolean,
    val writerVectorExecutionPresent: Boolean,
    val roundTripVectorExecutionPresent: Boolean,
    val negativeVectorExecutionPresent: Boolean,
    val productionVectorBytesPresent: Boolean,
    val productionParserInputBytesPresent: Boolean,
    val productionWriterOutputBytesPresent: Boolean,
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
    val authenticationExecutionPresent: Boolean,
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
    val workingParserImplementationAuthorizationPresent: Boolean,
    val workingWriterImplementationAuthorizationPresent: Boolean,
    val parserVectorExecutionAuthorizationPresent: Boolean,
    val writerVectorExecutionAuthorizationPresent: Boolean,
    val productionVectorCreationAuthorizationPresent: Boolean,
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
    val authenticationAuthorizationPresent: Boolean,
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
    val workingParserAdmissionGatePassedIsAdmissionEvidenceOnly: Boolean,
    val futureCommonMainInMemoryParserAdmittedIsNotParserImplementation: Boolean,
    val futureSyntheticVectorParserExecutionAdmittedIsNotParserExecution: Boolean,
    val futureParserFailClosedPolicyAdmittedIsNotParserImplementation: Boolean,
    val futureParserRedactedResultPolicyAdmittedIsNotDiagnosticsImplementation: Boolean,
    val futureParserErrorTaxonomyAdmittedIsNotParserImplementation: Boolean,
    val futureWorkingWriterImplementationRequiresSeparatePassKeepsWriterSeparate: Boolean,
    val futureParserImplementationCommonMainOnly: Boolean,
    val futureParserImplementationInMemoryOnly: Boolean,
    val futureParserImplementationNoFileIo: Boolean,
    val futureParserImplementationNoDirectoryCreation: Boolean,
    val futureParserImplementationNoSharedPreferencesOrSettingsStorage: Boolean,
    val futureParserImplementationNoKdfAeadEncryptionDecryptionAuthentication: Boolean,
    val futureParserImplementationNoKeyNonceTink: Boolean,
    val futureParserImplementationNoPersistenceOrRepositorySuccess: Boolean,
    val futureParserImplementationSyntheticVectorsOnlyUntilSeparateApproval: Boolean,
    val futureParserImplementationReturnsRedactedSkaldModelsOnly: Boolean,
    val futureParserImplementationFailClosedOnRequiredFailures: Boolean,
    val futureWriterImplementationRemainsSeparate: Boolean,
    val workingParserAdmissionCreatesParserObject: Boolean,
    val workingParserAdmissionConsumesBytes: Boolean,
    val workingParserAdmissionCreatesParsedHeader: Boolean,
    val workingParserAdmissionCreatesParsedDirectory: Boolean,
    val workingParserAdmissionCreatesParsedEnvelope: Boolean,
    val diagnosticsPolicyContainsNoSensitiveMaterial: Boolean,
    val normalSourceMaterialGuardExcludesBuildHistory: Boolean,
    val localArtifactRootExcludedFromNormalSourceMaterialCorpus: Boolean,
    val workingParserAdmissionGateDecisionPassed: Boolean,
    val evidenceCount: Int,
    val admissionCheckCount: Int,
    val blockerCount: Int,
    val warningCount: Int,
    val policyLabels: List<EncryptedVaultWorkingParserAdmissionPolicyLabel>,
    val errorTaxonomyLabels: List<EncryptedVaultWorkingParserAdmissionErrorTaxonomyLabel>,
    val admissionChecks: List<EncryptedVaultWorkingParserAdmissionCheck>,
    val failureLabels: List<EncryptedVaultWorkingParserAdmissionFailureLabel>,
    val displayLabel: EncryptedVaultWorkingParserAdmissionSafeLabel,
) {
    override fun toString(): String =
        "EncryptedVaultWorkingParserAdmissionGate(" +
            "REDACTED, COMMON_MAIN_POLICY, WORKING_PARSER_ADMISSION_ONLY, " +
            "LATER_BRANCHES_ONLY, IN_MEMORY_PARSER_FUTURE_ONLY, NO_PARSER_EXECUTION, " +
            "NO_WRITER, NO_PRODUCTION_VECTORS, NO_SERIALIZATION_PARSING, NO_VAULT_MATERIAL, " +
            "NO_IO, NO_CRYPTO_AUTH_EXECUTION, NO_RUNTIME_SESSION, NO_STORAGE_SUCCESS, " +
            "DISABLED_PROVIDER_ONLY, NO_SIGNING_BROADCASTING_UI_ENDPOINT_MAINNET)"
}

object EncryptedVaultWorkingParserAdmissionGatePolicy {
    fun currentWorkingParserAdmissionGate(): EncryptedVaultWorkingParserAdmissionGate {
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
        val testVectorAdmission =
            EncryptedVaultParserWriterTestVectorAdmissionPolicy
                .currentParserWriterTestVectorAdmission()
        val scaffold = EncryptedVaultParserWriterScaffoldPolicy.currentParserWriterScaffold()
        val providerSelection = VaultCryptoProviderSelectionRegistry.select()
        val checks = EncryptedVaultWorkingParserAdmissionCheck.entries.toList()
        val policyLabels = EncryptedVaultWorkingParserAdmissionPolicyLabel.entries.toList()
        val errorTaxonomyLabels =
            EncryptedVaultWorkingParserAdmissionErrorTaxonomyLabel.entries.toList()

        val storageReadinessDecisionPresent = storageDecision.storageReadinessDecisionPassed
        val containerFormatV1DecisionPresent = containerDecision.containerFormatV1DecisionPassed
        val storagePathSessionLifecycleDecisionPresent =
            storagePathDecision.storagePathSessionLifecycleDecisionPassed
        val migrationCorruptionPolicyDecisionPresent =
            migrationDecision.migrationCorruptionPolicyDecisionPassed
        val parserWriterAdmissionGatePresent =
            parserWriterGate.parserWriterAdmissionGateDecisionPassed
        val parserWriterTestVectorAdmissionPresent =
            testVectorAdmission.parserWriterTestVectorAdmissionDecisionPassed
        val parserWriterImplementationScaffoldPresent =
            scaffold.parserWriterScaffoldDecisionPassed
        val parserWriterSyntheticVectorCatalogPresent = true
        val encryptedVaultDesignPresent =
            scaffold.encryptedVaultDesignPresent &&
                testVectorAdmission.encryptedVaultDesignPresent &&
                parserWriterGate.encryptedVaultDesignPresent &&
                storageDecision.encryptedVaultDesignPresent &&
                containerDecision.encryptedVaultDesignPresent &&
                storagePathDecision.encryptedVaultDesignPresent &&
                migrationDecision.encryptedVaultDesignPresent
        val cryptoDecisionPresent =
            scaffold.cryptoDecisionPresent &&
                testVectorAdmission.cryptoDecisionPresent &&
                parserWriterGate.cryptoDecisionPresent &&
                storageDecision.cryptoDecisionPresent &&
                containerDecision.cryptoDecisionPresent &&
                storagePathDecision.cryptoDecisionPresent &&
                migrationDecision.cryptoDecisionPresent
        val dependencyReviewPresent =
            scaffold.dependencyReviewPresent &&
                testVectorAdmission.dependencyReviewPresent &&
                parserWriterGate.dependencyReviewPresent &&
                storageDecision.dependencyReviewPresent &&
                containerDecision.dependencyReviewPresent &&
                storagePathDecision.dependencyReviewPresent &&
                migrationDecision.dependencyReviewPresent
        val providerBoundaryPresent =
            scaffold.providerBoundaryPresent &&
                testVectorAdmission.providerBoundaryPresent &&
                parserWriterGate.providerBoundaryPresent &&
                storageDecision.disabledVaultCryptoProviderBoundaryPresent &&
                containerDecision.vaultCryptoProviderBoundaryPresent &&
                storagePathDecision.providerBoundaryPresent &&
                migrationDecision.providerBoundaryPresent
        val secureStorageBoundaryPresent =
            scaffold.secureStorageBoundaryPresent &&
                testVectorAdmission.secureStorageBoundaryPresent &&
                parserWriterGate.secureStorageBoundaryPresent &&
                storageDecision.secureStorageBoundaryPresent &&
                storagePathDecision.secureStorageBoundaryPresent &&
                migrationDecision.secureStorageBoundaryPresent
        val secureMetadataBoundaryPresent =
            scaffold.secureMetadataBoundaryPresent &&
                testVectorAdmission.secureMetadataBoundaryPresent &&
                parserWriterGate.secureMetadataBoundaryPresent &&
                storageDecision.secureMetadataBoundaryPresent &&
                storagePathDecision.secureMetadataBoundaryPresent &&
                migrationDecision.secureMetadataBoundaryPresent

        val futureCommonMainInMemoryParserAdmitted = true
        val futureSyntheticVectorParserExecutionAdmitted = true
        val futureParserFailClosedPolicyAdmitted = true
        val futureParserRedactedResultPolicyAdmitted = true
        val futureParserErrorTaxonomyAdmitted = true
        val futureUnsupportedVersionPolicyAdmitted = true
        val futureUnsupportedCriticalFeaturePolicyAdmitted = true
        val futureTruncatedInputPolicyAdmitted = true
        val futureMigrationRequiredPolicyAdmitted = true
        val futureCorruptionSuspectedPolicyAdmitted = true
        val futureDesktopParserVectorExecutionRequired = true
        val futureAndroidParserVectorExecutionRequired = true
        val futureWorkingParserImplementationRequiresSeparatePass = true
        val futureWorkingWriterImplementationRequiresSeparatePass =
            scaffold.futureWriterImplementationRequiresSeparatePass
        val futureParserWriterRoundTripRequiresSeparatePass = true
        val futureVaultStorageRepositoryRequiresSeparatePass =
            scaffold.futureVaultStorageRepositoryRequiresSeparatePass
        val futureSecureStorageSuccessRequiresSeparatePass =
            scaffold.futureSecureStorageSuccessRequiresSeparatePass
        val futureSecureMetadataSuccessRequiresSeparatePass =
            scaffold.futureSecureMetadataSuccessRequiresSeparatePass
        val futureProductionSyncRequiresSeparatePass =
            scaffold.futureProductionSyncRequiresSeparatePass
        val futureProductionProviderSelectionRequiresSeparatePass =
            scaffold.futureProductionProviderSelectionRequiresSeparatePass

        val workingParserImplementationPresent = false
        val workingWriterImplementationPresent = scaffold.workingWriterImplementationPresent
        val parserVectorExecutionPresent = false
        val writerVectorExecutionPresent = scaffold.writerVectorExecutionPresent
        val roundTripVectorExecutionPresent = false
        val negativeVectorExecutionPresent = false
        val productionVectorBytesPresent = scaffold.productionVectorBytesPresent
        val productionParserInputBytesPresent = scaffold.productionParserInputBytesPresent
        val productionWriterOutputBytesPresent = scaffold.productionWriterOutputBytesPresent
        val vaultContainerSerializationPresent = scaffold.vaultContainerSerializationPresent
        val vaultContainerParsingPresent = scaffold.vaultContainerParsingPresent
        val vaultContainerBytesProduced = scaffold.vaultContainerBytesProduced
        val vaultContainerBytesConsumed = scaffold.vaultContainerBytesConsumed
        val vaultHeaderSerialized = scaffold.vaultHeaderSerialized
        val vaultHeaderParsed = scaffold.vaultHeaderParsed
        val vaultRecordDirectorySerialized = scaffold.vaultRecordDirectorySerialized
        val vaultRecordDirectoryParsed = scaffold.vaultRecordDirectoryParsed
        val vaultRecordEnvelopeSerialized = scaffold.vaultRecordEnvelopeSerialized
        val vaultRecordEnvelopeParsed = scaffold.vaultRecordEnvelopeParsed
        val vaultFileReadPresent = scaffold.vaultFileReadPresent
        val vaultFileWritePresent = scaffold.vaultFileWritePresent
        val vaultFileDeletePresent = scaffold.vaultFileDeletePresent
        val vaultDirectoryCreated = scaffold.vaultDirectoryCreated
        val atomicReplaceImplementationPresent = scaffold.atomicReplaceImplementationPresent
        val partialWriteDetectionImplementationPresent =
            scaffold.partialWriteDetectionImplementationPresent
        val migrationImplementationPresent = scaffold.migrationImplementationPresent
        val migrationExecutionPresent = scaffold.migrationExecutionPresent
        val corruptionDetectionImplementationPresent =
            scaffold.corruptionDetectionImplementationPresent
        val corruptionRepairImplementationPresent = scaffold.corruptionRepairImplementationPresent
        val backupCreationPresent = scaffold.backupCreationPresent
        val rollbackImplementationPresent = scaffold.rollbackImplementationPresent
        val kdfExecutionPresent = scaffold.kdfExecutionPresent
        val aeadExecutionPresent = scaffold.aeadExecutionPresent
        val encryptionExecutionPresent = scaffold.encryptionExecutionPresent
        val decryptionExecutionPresent = scaffold.decryptionExecutionPresent
        val authenticationExecutionPresent = false
        val keyGenerationPresent = scaffold.keyGenerationPresent
        val nonceGenerationPresent = scaffold.nonceGenerationPresent
        val tinkKeysetCreationPresent = scaffold.tinkKeysetCreationPresent
        val tinkKeysetPersistencePresent = scaffold.tinkKeysetPersistencePresent
        val vaultStoragePathImplementationPresent = scaffold.vaultStoragePathImplementationPresent
        val encryptedVaultFileFormatImplemented = scaffold.encryptedVaultFileFormatImplemented
        val encryptedVaultRepositorySuccessPresent =
            scaffold.encryptedVaultRepositorySuccessPresent
        val lockSessionImplementationPresent = scaffold.lockSessionImplementationPresent
        val unlockImplementationPresent = scaffold.unlockImplementationPresent
        val runtimeSessionKeyPresent = scaffold.runtimeSessionKeyPresent
        val sessionKeyCached = scaffold.sessionKeyCached
        val plaintextCachePresent = scaffold.plaintextCachePresent
        val secureSecretStorageSuccessPathPresent =
            scaffold.secureSecretStorageSuccessPathPresent
        val secureMetadataStorageSuccessPathPresent =
            scaffold.secureMetadataStorageSuccessPathPresent
        val productionObservationPersistencePresent =
            scaffold.productionObservationPersistencePresent
        val productionAddressIndexPersistencePresent =
            scaffold.productionAddressIndexPersistencePresent
        val productionUtxoPersistencePresent = scaffold.productionUtxoPersistencePresent
        val productionWalletHistoryPersistencePresent =
            scaffold.productionWalletHistoryPersistencePresent
        val productionSyncPresent = scaffold.productionSyncPresent
        val productionBackendClientPresent = scaffold.productionBackendClientPresent
        val productionProviderSelectionEnabled = !providerSelection.selectedProviderIsDisabled
        val productionProviderSelectable = providerSelection.productionProviderSelectable
        val productionSelectionStillDisabledProviderOnly =
            providerSelection.selectedProviderIsDisabled &&
                productionProviderSelectionEnabled.not() &&
                productionProviderSelectable.not()
        val signingBroadcastingPresent = scaffold.signingBroadcastingPresent
        val uiActionEnablementPresent = scaffold.uiActionEnablementPresent
        val endpointPresent = scaffold.endpointPresent
        val mainnetPresent = scaffold.mainnetPresent

        val workingParserImplementationAuthorizationPresent = false
        val workingWriterImplementationAuthorizationPresent = false
        val parserVectorExecutionAuthorizationPresent = false
        val writerVectorExecutionAuthorizationPresent = false
        val productionVectorCreationAuthorizationPresent = false
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
        val authenticationAuthorizationPresent = false
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

        val futureParserImplementationCommonMainOnly = true
        val futureParserImplementationInMemoryOnly = true
        val futureParserImplementationNoFileIo = true
        val futureParserImplementationNoDirectoryCreation = true
        val futureParserImplementationNoSharedPreferencesOrSettingsStorage = true
        val futureParserImplementationNoKdfAeadEncryptionDecryptionAuthentication = true
        val futureParserImplementationNoKeyNonceTink = true
        val futureParserImplementationNoPersistenceOrRepositorySuccess = true
        val futureParserImplementationSyntheticVectorsOnlyUntilSeparateApproval = true
        val futureParserImplementationReturnsRedactedSkaldModelsOnly = true
        val futureParserImplementationFailClosedOnRequiredFailures =
            errorTaxonomyLabels.map { it.safeLabel.value }.containsAll(
                listOf(
                    "unsupported-version",
                    "unsupported-critical-feature",
                    "truncated-input",
                    "migration-required",
                    "corruption-suspected",
                    "storage-unavailable",
                ),
            )
        val futureWriterImplementationRemainsSeparate = true
        val workingParserAdmissionCreatesParserObject = false
        val workingParserAdmissionConsumesBytes = false
        val workingParserAdmissionCreatesParsedHeader = false
        val workingParserAdmissionCreatesParsedDirectory = false
        val workingParserAdmissionCreatesParsedEnvelope = false

        val workingParserAdmissionGatePassedIsAdmissionEvidenceOnly =
            futureCommonMainInMemoryParserAdmitted &&
                workingParserImplementationPresent.not() &&
                parserVectorExecutionPresent.not()
        val futureCommonMainInMemoryParserAdmittedIsNotParserImplementation =
            futureCommonMainInMemoryParserAdmitted && workingParserImplementationPresent.not()
        val futureSyntheticVectorParserExecutionAdmittedIsNotParserExecution =
            futureSyntheticVectorParserExecutionAdmitted && parserVectorExecutionPresent.not()
        val futureParserFailClosedPolicyAdmittedIsNotParserImplementation =
            futureParserFailClosedPolicyAdmitted && workingParserImplementationPresent.not()
        val futureParserRedactedResultPolicyAdmittedIsNotDiagnosticsImplementation =
            futureParserRedactedResultPolicyAdmitted && parserVectorExecutionPresent.not()
        val futureParserErrorTaxonomyAdmittedIsNotParserImplementation =
            futureParserErrorTaxonomyAdmitted && workingParserImplementationPresent.not()
        val futureWorkingWriterImplementationRequiresSeparatePassKeepsWriterSeparate =
            futureWorkingWriterImplementationRequiresSeparatePass &&
                workingWriterImplementationPresent.not() &&
                writerVectorExecutionPresent.not()
        val diagnosticsPolicyContainsNoSensitiveMaterial =
            (policyLabels.map { it.safeLabel.value } + errorTaxonomyLabels.map { it.safeLabel.value })
                .all { label ->
                    "/" !in label &&
                        "\\" !in label &&
                        "." !in label &&
                        "~" !in label &&
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
                parserWriterTestVectorAdmissionPresent &&
                parserWriterImplementationScaffoldPresent &&
                parserWriterSyntheticVectorCatalogPresent &&
                encryptedVaultDesignPresent &&
                cryptoDecisionPresent &&
                dependencyReviewPresent &&
                providerBoundaryPresent &&
                secureStorageBoundaryPresent &&
                secureMetadataBoundaryPresent
        val admissionPresent =
            futureCommonMainInMemoryParserAdmitted &&
                futureSyntheticVectorParserExecutionAdmitted &&
                futureParserFailClosedPolicyAdmitted &&
                futureParserRedactedResultPolicyAdmitted &&
                futureParserErrorTaxonomyAdmitted &&
                futureUnsupportedVersionPolicyAdmitted &&
                futureUnsupportedCriticalFeaturePolicyAdmitted &&
                futureTruncatedInputPolicyAdmitted &&
                futureMigrationRequiredPolicyAdmitted &&
                futureCorruptionSuspectedPolicyAdmitted &&
                futureDesktopParserVectorExecutionRequired &&
                futureAndroidParserVectorExecutionRequired
        val futureSeparatePassGatesPresent =
            futureWorkingParserImplementationRequiresSeparatePass &&
                futureWorkingWriterImplementationRequiresSeparatePass &&
                futureParserWriterRoundTripRequiresSeparatePass &&
                futureVaultStorageRepositoryRequiresSeparatePass &&
                futureSecureStorageSuccessRequiresSeparatePass &&
                futureSecureMetadataSuccessRequiresSeparatePass &&
                futureProductionSyncRequiresSeparatePass &&
                futureProductionProviderSelectionRequiresSeparatePass
        val parserRuntimeSurfacePresent =
            workingParserImplementationPresent ||
                parserVectorExecutionPresent ||
                workingParserAdmissionCreatesParserObject ||
                workingParserAdmissionConsumesBytes ||
                workingParserAdmissionCreatesParsedHeader ||
                workingParserAdmissionCreatesParsedDirectory ||
                workingParserAdmissionCreatesParsedEnvelope
        val writerRuntimeSurfacePresent = workingWriterImplementationPresent || writerVectorExecutionPresent
        val vectorRuntimeSurfacePresent =
            productionVectorBytesPresent ||
                productionParserInputBytesPresent ||
                productionWriterOutputBytesPresent ||
                roundTripVectorExecutionPresent ||
                negativeVectorExecutionPresent
        val parserWriterMaterialSurfacePresent =
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
                authenticationExecutionPresent ||
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
            workingParserImplementationAuthorizationPresent ||
                workingWriterImplementationAuthorizationPresent ||
                parserVectorExecutionAuthorizationPresent ||
                writerVectorExecutionAuthorizationPresent ||
                productionVectorCreationAuthorizationPresent ||
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
                authenticationAuthorizationPresent ||
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
        val futureParserPolicyPresent =
            futureParserImplementationCommonMainOnly &&
                futureParserImplementationInMemoryOnly &&
                futureParserImplementationNoFileIo &&
                futureParserImplementationNoDirectoryCreation &&
                futureParserImplementationNoSharedPreferencesOrSettingsStorage &&
                futureParserImplementationNoKdfAeadEncryptionDecryptionAuthentication &&
                futureParserImplementationNoKeyNonceTink &&
                futureParserImplementationNoPersistenceOrRepositorySuccess &&
                futureParserImplementationSyntheticVectorsOnlyUntilSeparateApproval &&
                futureParserImplementationReturnsRedactedSkaldModelsOnly &&
                futureParserImplementationFailClosedOnRequiredFailures &&
                futureWriterImplementationRemainsSeparate
        val diagnosticsMaterialPresent = diagnosticsPolicyContainsNoSensitiveMaterial.not()
        val corpusBoundaryPresent =
            normalSourceMaterialGuardExcludesBuildHistory &&
                localArtifactRootExcludedFromNormalSourceMaterialCorpus

        val failures = buildList {
            if (!priorEvidencePresent) {
                add(EncryptedVaultWorkingParserAdmissionFailureLabel.PriorEvidenceMissing)
            }
            if (!admissionPresent) {
                add(EncryptedVaultWorkingParserAdmissionFailureLabel.WorkingParserAdmissionMissing)
            }
            if (!futureSeparatePassGatesPresent) {
                add(EncryptedVaultWorkingParserAdmissionFailureLabel.FutureSeparatePassGateMissing)
            }
            if (parserRuntimeSurfacePresent) {
                add(EncryptedVaultWorkingParserAdmissionFailureLabel.ParserRuntimeSurfacePresent)
            }
            if (writerRuntimeSurfacePresent) {
                add(EncryptedVaultWorkingParserAdmissionFailureLabel.WriterRuntimeSurfacePresent)
            }
            if (vectorRuntimeSurfacePresent) {
                add(EncryptedVaultWorkingParserAdmissionFailureLabel.VectorRuntimeSurfacePresent)
            }
            if (parserWriterMaterialSurfacePresent) {
                add(EncryptedVaultWorkingParserAdmissionFailureLabel.ParserWriterMaterialSurfacePresent)
            }
            if (fileStorageRuntimeSurfacePresent) {
                add(EncryptedVaultWorkingParserAdmissionFailureLabel.FileStorageRuntimeSurfacePresent)
            }
            if (migrationCorruptionRuntimeSurfacePresent) {
                add(EncryptedVaultWorkingParserAdmissionFailureLabel.MigrationCorruptionRuntimeSurfacePresent)
            }
            if (cryptoRuntimeSurfacePresent) {
                add(EncryptedVaultWorkingParserAdmissionFailureLabel.CryptoRuntimeSurfacePresent)
            }
            if (lockSessionRuntimeSurfacePresent) {
                add(EncryptedVaultWorkingParserAdmissionFailureLabel.LockSessionRuntimeSurfacePresent)
            }
            if (productionStorageSurfacePresent) {
                add(EncryptedVaultWorkingParserAdmissionFailureLabel.ProductionStorageSurfacePresent)
            }
            if (productionSyncSurfacePresent) {
                add(EncryptedVaultWorkingParserAdmissionFailureLabel.ProductionSyncSurfacePresent)
            }
            if (productionProviderSelectionSurfacePresent || !productionSelectionStillDisabledProviderOnly) {
                add(EncryptedVaultWorkingParserAdmissionFailureLabel.ProductionProviderSelectionSurfacePresent)
            }
            if (signingBroadcastingUiEndpointOrMainnetSurfacePresent) {
                add(
                    EncryptedVaultWorkingParserAdmissionFailureLabel
                        .SigningBroadcastingUiEndpointOrMainnetSurfacePresent,
                )
            }
            if (productionAuthorizationPresent) {
                add(EncryptedVaultWorkingParserAdmissionFailureLabel.ProductionAuthorizationPresent)
            }
            if (!futureParserPolicyPresent) {
                add(EncryptedVaultWorkingParserAdmissionFailureLabel.FutureParserPolicyMissing)
            }
            if (diagnosticsMaterialPresent) {
                add(EncryptedVaultWorkingParserAdmissionFailureLabel.DiagnosticsMaterialPresent)
            }
            if (!corpusBoundaryPresent) {
                add(EncryptedVaultWorkingParserAdmissionFailureLabel.CorpusBoundaryMissing)
            }
        }

        val workingParserAdmissionGatePassed = failures.isEmpty()
        val workingParserAdmissionGateDecisionPassed =
            workingParserAdmissionGatePassed &&
                workingParserAdmissionGatePassedIsAdmissionEvidenceOnly &&
                futureCommonMainInMemoryParserAdmittedIsNotParserImplementation &&
                futureSyntheticVectorParserExecutionAdmittedIsNotParserExecution &&
                futureWorkingWriterImplementationRequiresSeparatePassKeepsWriterSeparate &&
                parserRuntimeSurfacePresent.not() &&
                productionAuthorizationPresent.not()

        return EncryptedVaultWorkingParserAdmissionGate(
            admissionId = EncryptedVaultWorkingParserAdmissionSafeLabel(
                "skald-encrypted-local-vault-working-parser-admission-v1",
            ),
            admissionVersion = 1,
            admissionKind =
                EncryptedVaultWorkingParserAdmissionKind
                    .EncryptedLocalVaultWorkingParserAdmissionGate,
            sourceSet = EncryptedVaultWorkingParserAdmissionSourceSet.CommonMainPolicy,
            storageReadinessDecisionPresent = storageReadinessDecisionPresent,
            containerFormatV1DecisionPresent = containerFormatV1DecisionPresent,
            storagePathSessionLifecycleDecisionPresent =
                storagePathSessionLifecycleDecisionPresent,
            migrationCorruptionPolicyDecisionPresent = migrationCorruptionPolicyDecisionPresent,
            parserWriterAdmissionGatePresent = parserWriterAdmissionGatePresent,
            parserWriterTestVectorAdmissionPresent = parserWriterTestVectorAdmissionPresent,
            parserWriterImplementationScaffoldPresent =
                parserWriterImplementationScaffoldPresent,
            parserWriterSyntheticVectorCatalogPresent =
                parserWriterSyntheticVectorCatalogPresent,
            encryptedVaultDesignPresent = encryptedVaultDesignPresent,
            cryptoDecisionPresent = cryptoDecisionPresent,
            dependencyReviewPresent = dependencyReviewPresent,
            providerBoundaryPresent = providerBoundaryPresent,
            secureStorageBoundaryPresent = secureStorageBoundaryPresent,
            secureMetadataBoundaryPresent = secureMetadataBoundaryPresent,
            workingParserAdmissionGatePassed = workingParserAdmissionGatePassed,
            futureCommonMainInMemoryParserAdmitted = futureCommonMainInMemoryParserAdmitted,
            futureSyntheticVectorParserExecutionAdmitted =
                futureSyntheticVectorParserExecutionAdmitted,
            futureParserFailClosedPolicyAdmitted = futureParserFailClosedPolicyAdmitted,
            futureParserRedactedResultPolicyAdmitted =
                futureParserRedactedResultPolicyAdmitted,
            futureParserErrorTaxonomyAdmitted = futureParserErrorTaxonomyAdmitted,
            futureUnsupportedVersionPolicyAdmitted = futureUnsupportedVersionPolicyAdmitted,
            futureUnsupportedCriticalFeaturePolicyAdmitted =
                futureUnsupportedCriticalFeaturePolicyAdmitted,
            futureTruncatedInputPolicyAdmitted = futureTruncatedInputPolicyAdmitted,
            futureMigrationRequiredPolicyAdmitted = futureMigrationRequiredPolicyAdmitted,
            futureCorruptionSuspectedPolicyAdmitted =
                futureCorruptionSuspectedPolicyAdmitted,
            futureDesktopParserVectorExecutionRequired =
                futureDesktopParserVectorExecutionRequired,
            futureAndroidParserVectorExecutionRequired =
                futureAndroidParserVectorExecutionRequired,
            futureWorkingParserImplementationRequiresSeparatePass =
                futureWorkingParserImplementationRequiresSeparatePass,
            futureWorkingWriterImplementationRequiresSeparatePass =
                futureWorkingWriterImplementationRequiresSeparatePass,
            futureParserWriterRoundTripRequiresSeparatePass =
                futureParserWriterRoundTripRequiresSeparatePass,
            futureVaultStorageRepositoryRequiresSeparatePass =
                futureVaultStorageRepositoryRequiresSeparatePass,
            futureSecureStorageSuccessRequiresSeparatePass =
                futureSecureStorageSuccessRequiresSeparatePass,
            futureSecureMetadataSuccessRequiresSeparatePass =
                futureSecureMetadataSuccessRequiresSeparatePass,
            futureProductionSyncRequiresSeparatePass = futureProductionSyncRequiresSeparatePass,
            futureProductionProviderSelectionRequiresSeparatePass =
                futureProductionProviderSelectionRequiresSeparatePass,
            workingParserImplementationPresent = workingParserImplementationPresent,
            workingWriterImplementationPresent = workingWriterImplementationPresent,
            parserVectorExecutionPresent = parserVectorExecutionPresent,
            writerVectorExecutionPresent = writerVectorExecutionPresent,
            roundTripVectorExecutionPresent = roundTripVectorExecutionPresent,
            negativeVectorExecutionPresent = negativeVectorExecutionPresent,
            productionVectorBytesPresent = productionVectorBytesPresent,
            productionParserInputBytesPresent = productionParserInputBytesPresent,
            productionWriterOutputBytesPresent = productionWriterOutputBytesPresent,
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
            authenticationExecutionPresent = authenticationExecutionPresent,
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
            productionSelectionStillDisabledProviderOnly =
                productionSelectionStillDisabledProviderOnly,
            signingBroadcastingPresent = signingBroadcastingPresent,
            uiActionEnablementPresent = uiActionEnablementPresent,
            endpointPresent = endpointPresent,
            mainnetPresent = mainnetPresent,
            workingParserImplementationAuthorizationPresent =
                workingParserImplementationAuthorizationPresent,
            workingWriterImplementationAuthorizationPresent =
                workingWriterImplementationAuthorizationPresent,
            parserVectorExecutionAuthorizationPresent = parserVectorExecutionAuthorizationPresent,
            writerVectorExecutionAuthorizationPresent = writerVectorExecutionAuthorizationPresent,
            productionVectorCreationAuthorizationPresent =
                productionVectorCreationAuthorizationPresent,
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
            authenticationAuthorizationPresent = authenticationAuthorizationPresent,
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
            workingParserAdmissionGatePassedIsAdmissionEvidenceOnly =
                workingParserAdmissionGatePassedIsAdmissionEvidenceOnly,
            futureCommonMainInMemoryParserAdmittedIsNotParserImplementation =
                futureCommonMainInMemoryParserAdmittedIsNotParserImplementation,
            futureSyntheticVectorParserExecutionAdmittedIsNotParserExecution =
                futureSyntheticVectorParserExecutionAdmittedIsNotParserExecution,
            futureParserFailClosedPolicyAdmittedIsNotParserImplementation =
                futureParserFailClosedPolicyAdmittedIsNotParserImplementation,
            futureParserRedactedResultPolicyAdmittedIsNotDiagnosticsImplementation =
                futureParserRedactedResultPolicyAdmittedIsNotDiagnosticsImplementation,
            futureParserErrorTaxonomyAdmittedIsNotParserImplementation =
                futureParserErrorTaxonomyAdmittedIsNotParserImplementation,
            futureWorkingWriterImplementationRequiresSeparatePassKeepsWriterSeparate =
                futureWorkingWriterImplementationRequiresSeparatePassKeepsWriterSeparate,
            futureParserImplementationCommonMainOnly = futureParserImplementationCommonMainOnly,
            futureParserImplementationInMemoryOnly = futureParserImplementationInMemoryOnly,
            futureParserImplementationNoFileIo = futureParserImplementationNoFileIo,
            futureParserImplementationNoDirectoryCreation =
                futureParserImplementationNoDirectoryCreation,
            futureParserImplementationNoSharedPreferencesOrSettingsStorage =
                futureParserImplementationNoSharedPreferencesOrSettingsStorage,
            futureParserImplementationNoKdfAeadEncryptionDecryptionAuthentication =
                futureParserImplementationNoKdfAeadEncryptionDecryptionAuthentication,
            futureParserImplementationNoKeyNonceTink = futureParserImplementationNoKeyNonceTink,
            futureParserImplementationNoPersistenceOrRepositorySuccess =
                futureParserImplementationNoPersistenceOrRepositorySuccess,
            futureParserImplementationSyntheticVectorsOnlyUntilSeparateApproval =
                futureParserImplementationSyntheticVectorsOnlyUntilSeparateApproval,
            futureParserImplementationReturnsRedactedSkaldModelsOnly =
                futureParserImplementationReturnsRedactedSkaldModelsOnly,
            futureParserImplementationFailClosedOnRequiredFailures =
                futureParserImplementationFailClosedOnRequiredFailures,
            futureWriterImplementationRemainsSeparate = futureWriterImplementationRemainsSeparate,
            workingParserAdmissionCreatesParserObject = workingParserAdmissionCreatesParserObject,
            workingParserAdmissionConsumesBytes = workingParserAdmissionConsumesBytes,
            workingParserAdmissionCreatesParsedHeader =
                workingParserAdmissionCreatesParsedHeader,
            workingParserAdmissionCreatesParsedDirectory =
                workingParserAdmissionCreatesParsedDirectory,
            workingParserAdmissionCreatesParsedEnvelope =
                workingParserAdmissionCreatesParsedEnvelope,
            diagnosticsPolicyContainsNoSensitiveMaterial =
                diagnosticsPolicyContainsNoSensitiveMaterial,
            normalSourceMaterialGuardExcludesBuildHistory =
                normalSourceMaterialGuardExcludesBuildHistory,
            localArtifactRootExcludedFromNormalSourceMaterialCorpus =
                localArtifactRootExcludedFromNormalSourceMaterialCorpus,
            workingParserAdmissionGateDecisionPassed = workingParserAdmissionGateDecisionPassed,
            evidenceCount = 14,
            admissionCheckCount = checks.size,
            blockerCount = failures.size,
            warningCount = 0,
            policyLabels = policyLabels,
            errorTaxonomyLabels = errorTaxonomyLabels,
            admissionChecks = checks,
            failureLabels = failures,
            displayLabel = EncryptedVaultWorkingParserAdmissionSafeLabel(
                "encrypted vault working parser admission gate",
            ),
        )
    }
}
