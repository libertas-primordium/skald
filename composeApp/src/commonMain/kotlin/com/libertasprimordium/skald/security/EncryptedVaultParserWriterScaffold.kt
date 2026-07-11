package com.libertasprimordium.skald.security

@JvmInline
value class EncryptedVaultParserWriterScaffoldSafeLabel(val value: String) {
    override fun toString(): String = "EncryptedVaultParserWriterScaffoldSafeLabel(REDACTED)"
}

enum class EncryptedVaultParserWriterScaffoldKind(val label: String) {
    EncryptedLocalVaultParserWriterImplementationScaffold(
        "ENCRYPTED_LOCAL_VAULT_PARSER_WRITER_IMPLEMENTATION_SCAFFOLD",
    ),
}

enum class EncryptedVaultParserWriterScaffoldSourceSet(val label: String) {
    CommonMainPolicy("COMMON_MAIN_POLICY"),
}

enum class EncryptedVaultParserWriterScaffoldPolicyLabel(
    val safeLabel: EncryptedVaultParserWriterScaffoldSafeLabel,
) {
    ParserWriterScaffold(
        EncryptedVaultParserWriterScaffoldSafeLabel(
            "skald-encrypted-local-vault-parser-writer-scaffold-v1",
        ),
    ),
    DisabledParserScaffold(
        EncryptedVaultParserWriterScaffoldSafeLabel(
            "skald-vault-v1-disabled-parser-scaffold",
        ),
    ),
    DisabledWriterScaffold(
        EncryptedVaultParserWriterScaffoldSafeLabel(
            "skald-vault-v1-disabled-writer-scaffold",
        ),
    ),
    ParserResultRedactionPolicy(
        EncryptedVaultParserWriterScaffoldSafeLabel(
            "skald-vault-v1-parser-result-redaction-policy",
        ),
    ),
    WriterResultRedactionPolicy(
        EncryptedVaultParserWriterScaffoldSafeLabel(
            "skald-vault-v1-writer-result-redaction-policy",
        ),
    ),
    TestSourceVectorCatalogPolicy(
        EncryptedVaultParserWriterScaffoldSafeLabel(
            "skald-vault-v1-test-source-vector-catalog-policy",
        ),
    ),
}

enum class EncryptedVaultParserWriterScaffoldCheck {
    StorageReadinessDecisionPresent,
    ContainerFormatV1DecisionPresent,
    StoragePathSessionLifecycleDecisionPresent,
    MigrationCorruptionPolicyDecisionPresent,
    ParserWriterAdmissionGatePresent,
    ParserWriterTestVectorAdmissionPresent,
    EncryptedVaultDesignPresent,
    CryptoDecisionPresent,
    DependencyReviewPresent,
    ProviderBoundaryPresent,
    SecureStorageBoundaryPresent,
    SecureMetadataBoundaryPresent,
    ParserWriterImplementationScaffoldPresent,
    DisabledParserScaffoldPresent,
    DisabledWriterScaffoldPresent,
    ParserInterfacePresent,
    WriterInterfacePresent,
    ParserResultModelPresent,
    WriterResultModelPresent,
    RedactedDiagnosticsModelPresent,
    TestSourceSyntheticVectorCatalogAdmitted,
    TestSourceSyntheticVectorBytesAllowed,
    FutureParserImplementationRequiresSeparatePass,
    FutureWriterImplementationRequiresSeparatePass,
    FutureParserWriterVectorExecutionRequiresSeparatePass,
    FutureParserWriterKatExecutionRequiresSeparatePass,
    FutureVaultStorageRepositoryRequiresSeparatePass,
    FutureSecureStorageSuccessRequiresSeparatePass,
    FutureSecureMetadataSuccessRequiresSeparatePass,
    FutureProductionSyncRequiresSeparatePass,
    FutureProductionProviderSelectionRequiresSeparatePass,
    WorkingParserImplementationAbsent,
    WorkingWriterImplementationAbsent,
    ProductionVectorBytesAbsent,
    ProductionParserInputBytesAbsent,
    ProductionWriterOutputBytesAbsent,
    ParserVectorExecutionAbsent,
    WriterVectorExecutionAbsent,
    RoundTripVectorExecutionAbsent,
    NegativeVectorExecutionAbsent,
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
    DisabledParserRejectsWithoutConsumingBytes,
    DisabledWriterRejectsWithoutProducingBytes,
    ParserImplementationAuthorizationAbsent,
    WriterImplementationAuthorizationAbsent,
    ProductionVectorCreationAuthorizationAbsent,
    ParserVectorExecutionAuthorizationAbsent,
    WriterVectorExecutionAuthorizationAbsent,
    SerializationAuthorizationAbsent,
    ParsingAuthorizationAbsent,
    FileReadAuthorizationAbsent,
    FileWriteAuthorizationAbsent,
    FileDeleteAuthorizationAbsent,
    DirectoryCreationAuthorizationAbsent,
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
    TestSourceSyntheticVectorCatalogNotCreated,
    TestSourceSyntheticVectorBytesNotCreated,
    TestSourceSyntheticVectorBytesNotLogged,
    TestSourceSyntheticVectorBytesNotUsedByWorkingParserWriter,
    DiagnosticsPolicyContainsNoSensitiveMaterial,
    DisplayOutputRedactedOrSafeLabelOnly,
    NormalSourceMaterialGuardExcludesBuildHistory,
    LocalArtifactRootExcludedFromNormalSourceMaterialCorpus,
}

enum class EncryptedVaultParserWriterScaffoldFailureLabel {
    PriorEvidenceMissing,
    ScaffoldMissing,
    FutureSeparatePassGateMissing,
    WorkingParserWriterPresent,
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
    DisabledParserWriterContractBroken,
    TestSourceVectorConfinementBroken,
    DiagnosticsMaterialPresent,
    CorpusBoundaryMissing,
}

enum class EncryptedVaultParserWriterScaffoldStatus(val label: String) {
    DisabledFailClosed("disabled fail closed"),
    RejectedByScaffold("rejected by scaffold"),
}

enum class EncryptedVaultParserWriterScaffoldBlocker(val label: String) {
    WorkingParserMissing("working parser implementation missing"),
    WorkingWriterMissing("working writer implementation missing"),
    ParserDisabledByScaffold("parser disabled by scaffold"),
    WriterDisabledByScaffold("writer disabled by scaffold"),
    ProductionVectorCreationNotAuthorized("production vector creation not authorized"),
    SerializationNotAuthorized("serialization not authorized"),
    ParsingNotAuthorized("parsing not authorized"),
    FileIoNotAuthorized("file I/O not authorized"),
    CryptoExecutionNotAuthorized("crypto execution not authorized"),
    StorageRepositoryNotAuthorized("storage repository not authorized"),
    ProductionProviderSelectionNotAuthorized("production provider selection not authorized"),
    MainnetDisabled("mainnet disabled"),
}

data class EncryptedVaultParserWriterRedactedDiagnostics(
    val status: EncryptedVaultParserWriterScaffoldStatus,
    val blocker: EncryptedVaultParserWriterScaffoldBlocker,
    val consumedByteCount: Int,
    val producedByteCount: Int,
    val safeLabelsOnly: Boolean = true,
    val payloadFree: Boolean = true,
) {
    override fun toString(): String =
        "EncryptedVaultParserWriterRedactedDiagnostics(REDACTED, SAFE_LABELS_ONLY, PAYLOAD_FREE)"
}

data class EncryptedVaultParserRequest(
    val requestLabel: EncryptedVaultParserWriterScaffoldSafeLabel =
        EncryptedVaultParserWriterScaffoldSafeLabel("disabled parser scaffold request"),
    val sourceSet: EncryptedVaultParserWriterScaffoldSourceSet =
        EncryptedVaultParserWriterScaffoldSourceSet.CommonMainPolicy,
    val declaredInputByteCount: Int = 0,
    val productionParserInputBytesPresent: Boolean = false,
    val testSourceSyntheticVectorBytesPresent: Boolean = false,
) {
    override fun toString(): String =
        "EncryptedVaultParserRequest(REDACTED, COMMON_MAIN_POLICY, NO_MATERIAL)"
}

data class EncryptedVaultWriterRequest(
    val requestLabel: EncryptedVaultParserWriterScaffoldSafeLabel =
        EncryptedVaultParserWriterScaffoldSafeLabel("disabled writer scaffold request"),
    val sourceSet: EncryptedVaultParserWriterScaffoldSourceSet =
        EncryptedVaultParserWriterScaffoldSourceSet.CommonMainPolicy,
    val requestedOutputByteCount: Int = 0,
    val productionWriterOutputBytesPresent: Boolean = false,
    val testSourceSyntheticVectorBytesPresent: Boolean = false,
) {
    override fun toString(): String =
        "EncryptedVaultWriterRequest(REDACTED, COMMON_MAIN_POLICY, NO_MATERIAL)"
}

data class EncryptedVaultParserResult(
    val status: EncryptedVaultParserWriterScaffoldStatus,
    val blocker: EncryptedVaultParserWriterScaffoldBlocker,
    val accepted: Boolean,
    val consumedByteCount: Int,
    val producedByteCount: Int,
    val diagnostics: EncryptedVaultParserWriterRedactedDiagnostics,
) {
    override fun toString(): String =
        "EncryptedVaultParserResult(REDACTED, DISABLED, NO_MATERIAL, NO_IO)"
}

data class EncryptedVaultWriterResult(
    val status: EncryptedVaultParserWriterScaffoldStatus,
    val blocker: EncryptedVaultParserWriterScaffoldBlocker,
    val accepted: Boolean,
    val consumedByteCount: Int,
    val producedByteCount: Int,
    val diagnostics: EncryptedVaultParserWriterRedactedDiagnostics,
) {
    override fun toString(): String =
        "EncryptedVaultWriterResult(REDACTED, DISABLED, NO_MATERIAL, NO_IO)"
}

interface EncryptedVaultParserScaffold {
    fun parse(request: EncryptedVaultParserRequest = EncryptedVaultParserRequest()): EncryptedVaultParserResult
}

interface EncryptedVaultWriterScaffold {
    fun write(request: EncryptedVaultWriterRequest = EncryptedVaultWriterRequest()): EncryptedVaultWriterResult
}

object DisabledEncryptedVaultParserScaffold : EncryptedVaultParserScaffold {
    override fun parse(request: EncryptedVaultParserRequest): EncryptedVaultParserResult {
        val diagnostics = EncryptedVaultParserWriterRedactedDiagnostics(
            status = EncryptedVaultParserWriterScaffoldStatus.RejectedByScaffold,
            blocker = EncryptedVaultParserWriterScaffoldBlocker.ParserDisabledByScaffold,
            consumedByteCount = 0,
            producedByteCount = 0,
        )
        return EncryptedVaultParserResult(
            status = EncryptedVaultParserWriterScaffoldStatus.RejectedByScaffold,
            blocker = EncryptedVaultParserWriterScaffoldBlocker.ParserDisabledByScaffold,
            accepted = false,
            consumedByteCount = 0,
            producedByteCount = 0,
            diagnostics = diagnostics,
        )
    }
}

object DisabledEncryptedVaultWriterScaffold : EncryptedVaultWriterScaffold {
    override fun write(request: EncryptedVaultWriterRequest): EncryptedVaultWriterResult {
        val diagnostics = EncryptedVaultParserWriterRedactedDiagnostics(
            status = EncryptedVaultParserWriterScaffoldStatus.RejectedByScaffold,
            blocker = EncryptedVaultParserWriterScaffoldBlocker.WriterDisabledByScaffold,
            consumedByteCount = 0,
            producedByteCount = 0,
        )
        return EncryptedVaultWriterResult(
            status = EncryptedVaultParserWriterScaffoldStatus.RejectedByScaffold,
            blocker = EncryptedVaultParserWriterScaffoldBlocker.WriterDisabledByScaffold,
            accepted = false,
            consumedByteCount = 0,
            producedByteCount = 0,
            diagnostics = diagnostics,
        )
    }
}

data class EncryptedVaultParserWriterScaffold(
    val scaffoldId: EncryptedVaultParserWriterScaffoldSafeLabel,
    val scaffoldVersion: Int,
    val scaffoldKind: EncryptedVaultParserWriterScaffoldKind,
    val sourceSet: EncryptedVaultParserWriterScaffoldSourceSet,
    val storageReadinessDecisionPresent: Boolean,
    val containerFormatV1DecisionPresent: Boolean,
    val storagePathSessionLifecycleDecisionPresent: Boolean,
    val migrationCorruptionPolicyDecisionPresent: Boolean,
    val parserWriterAdmissionGatePresent: Boolean,
    val parserWriterTestVectorAdmissionPresent: Boolean,
    val encryptedVaultDesignPresent: Boolean,
    val cryptoDecisionPresent: Boolean,
    val dependencyReviewPresent: Boolean,
    val providerBoundaryPresent: Boolean,
    val secureStorageBoundaryPresent: Boolean,
    val secureMetadataBoundaryPresent: Boolean,
    val parserWriterImplementationScaffoldPresent: Boolean,
    val disabledParserScaffoldPresent: Boolean,
    val disabledWriterScaffoldPresent: Boolean,
    val parserInterfacePresent: Boolean,
    val writerInterfacePresent: Boolean,
    val parserResultModelPresent: Boolean,
    val writerResultModelPresent: Boolean,
    val redactedDiagnosticsModelPresent: Boolean,
    val testSourceSyntheticVectorCatalogAdmitted: Boolean,
    val testSourceSyntheticVectorBytesAllowed: Boolean,
    val futureParserImplementationRequiresSeparatePass: Boolean,
    val futureWriterImplementationRequiresSeparatePass: Boolean,
    val futureParserWriterVectorExecutionRequiresSeparatePass: Boolean,
    val futureParserWriterKatExecutionRequiresSeparatePass: Boolean,
    val futureVaultStorageRepositoryRequiresSeparatePass: Boolean,
    val futureSecureStorageSuccessRequiresSeparatePass: Boolean,
    val futureSecureMetadataSuccessRequiresSeparatePass: Boolean,
    val futureProductionSyncRequiresSeparatePass: Boolean,
    val futureProductionProviderSelectionRequiresSeparatePass: Boolean,
    val workingParserImplementationPresent: Boolean,
    val workingWriterImplementationPresent: Boolean,
    val productionVectorBytesPresent: Boolean,
    val productionParserInputBytesPresent: Boolean,
    val productionWriterOutputBytesPresent: Boolean,
    val parserVectorExecutionPresent: Boolean,
    val writerVectorExecutionPresent: Boolean,
    val roundTripVectorExecutionPresent: Boolean,
    val negativeVectorExecutionPresent: Boolean,
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
    val parserImplementationAuthorizationPresent: Boolean,
    val writerImplementationAuthorizationPresent: Boolean,
    val productionVectorCreationAuthorizationPresent: Boolean,
    val parserVectorExecutionAuthorizationPresent: Boolean,
    val writerVectorExecutionAuthorizationPresent: Boolean,
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
    val parserWriterImplementationScaffoldPresentIsEvidenceOnly: Boolean,
    val disabledParserScaffoldPresentIsNotParserImplementation: Boolean,
    val disabledWriterScaffoldPresentIsNotWriterImplementation: Boolean,
    val parserInterfacePresentIsNotParserExecution: Boolean,
    val writerInterfacePresentIsNotWriterExecution: Boolean,
    val testSourceSyntheticVectorBytesAllowedIsNotProductionVectorAuthorization: Boolean,
    val futureTestSourceSyntheticBytesAreNotProductionVaultBytes: Boolean,
    val disabledParserRejectsWithoutConsumingBytes: Boolean,
    val disabledWriterRejectsWithoutProducingBytes: Boolean,
    val testSourceSyntheticVectorCatalogCreated: Boolean,
    val testSourceSyntheticVectorBytesCreated: Boolean,
    val testSourceSyntheticVectorBytesExecuted: Boolean,
    val testSourceSyntheticVectorBytesLogged: Boolean,
    val testSourceSyntheticVectorCatalogConfinedToTestSource: Boolean,
    val diagnosticsPolicyContainsNoSensitiveMaterial: Boolean,
    val normalSourceMaterialGuardExcludesBuildHistory: Boolean,
    val localArtifactRootExcludedFromNormalSourceMaterialCorpus: Boolean,
    val parserWriterScaffoldDecisionPassed: Boolean,
    val evidenceCount: Int,
    val scaffoldCheckCount: Int,
    val blockerCount: Int,
    val warningCount: Int,
    val policyLabels: List<EncryptedVaultParserWriterScaffoldPolicyLabel>,
    val scaffoldChecks: List<EncryptedVaultParserWriterScaffoldCheck>,
    val failureLabels: List<EncryptedVaultParserWriterScaffoldFailureLabel>,
    val displayLabel: EncryptedVaultParserWriterScaffoldSafeLabel,
) {
    override fun toString(): String =
        "EncryptedVaultParserWriterScaffold(" +
            "REDACTED, COMMON_MAIN_POLICY, SCAFFOLD_ONLY, DISABLED_PARSER, DISABLED_WRITER, " +
            "NO_WORKING_IMPLEMENTATION, NO_PRODUCTION_VECTORS, NO_SERIALIZATION_PARSING, " +
            "NO_VAULT_MATERIAL, NO_IO, NO_CRYPTO_EXECUTION, NO_RUNTIME_SESSION, " +
            "NO_STORAGE_SUCCESS, DISABLED_PROVIDER_ONLY, NO_SIGNING_BROADCASTING_UI_ENDPOINT_MAINNET)"
}

object EncryptedVaultParserWriterScaffoldPolicy {
    fun currentParserWriterScaffold(): EncryptedVaultParserWriterScaffold {
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
        val providerSelection = VaultCryptoProviderSelectionRegistry.select()
        val disabledParserResult =
            DisabledEncryptedVaultParserScaffold.parse(EncryptedVaultParserRequest())
        val disabledWriterResult =
            DisabledEncryptedVaultWriterScaffold.write(EncryptedVaultWriterRequest())
        val checks = EncryptedVaultParserWriterScaffoldCheck.entries.toList()
        val policyLabels = EncryptedVaultParserWriterScaffoldPolicyLabel.entries.toList()

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
        val encryptedVaultDesignPresent =
            testVectorAdmission.encryptedVaultDesignPresent &&
                parserWriterGate.encryptedVaultDesignPresent &&
                storageDecision.encryptedVaultDesignPresent &&
                containerDecision.encryptedVaultDesignPresent &&
                storagePathDecision.encryptedVaultDesignPresent &&
                migrationDecision.encryptedVaultDesignPresent
        val cryptoDecisionPresent =
            testVectorAdmission.cryptoDecisionPresent &&
                parserWriterGate.cryptoDecisionPresent &&
                storageDecision.cryptoDecisionPresent &&
                containerDecision.cryptoDecisionPresent &&
                storagePathDecision.cryptoDecisionPresent &&
                migrationDecision.cryptoDecisionPresent
        val dependencyReviewPresent =
            testVectorAdmission.dependencyReviewPresent &&
                parserWriterGate.dependencyReviewPresent &&
                storageDecision.dependencyReviewPresent &&
                containerDecision.dependencyReviewPresent &&
                storagePathDecision.dependencyReviewPresent &&
                migrationDecision.dependencyReviewPresent
        val providerBoundaryPresent =
            testVectorAdmission.providerBoundaryPresent &&
                parserWriterGate.providerBoundaryPresent &&
                storageDecision.disabledVaultCryptoProviderBoundaryPresent &&
                containerDecision.vaultCryptoProviderBoundaryPresent &&
                storagePathDecision.providerBoundaryPresent &&
                migrationDecision.providerBoundaryPresent
        val secureStorageBoundaryPresent =
            testVectorAdmission.secureStorageBoundaryPresent &&
                parserWriterGate.secureStorageBoundaryPresent &&
                storageDecision.secureStorageBoundaryPresent &&
                storagePathDecision.secureStorageBoundaryPresent &&
                migrationDecision.secureStorageBoundaryPresent
        val secureMetadataBoundaryPresent =
            testVectorAdmission.secureMetadataBoundaryPresent &&
                parserWriterGate.secureMetadataBoundaryPresent &&
                storageDecision.secureMetadataBoundaryPresent &&
                storagePathDecision.secureMetadataBoundaryPresent &&
                migrationDecision.secureMetadataBoundaryPresent

        val parserWriterImplementationScaffoldPresent = true
        val disabledParserScaffoldPresent = true
        val disabledWriterScaffoldPresent = true
        val parserInterfacePresent = true
        val writerInterfacePresent = true
        val parserResultModelPresent = true
        val writerResultModelPresent = true
        val redactedDiagnosticsModelPresent = true
        val testSourceSyntheticVectorCatalogAdmitted = true
        val testSourceSyntheticVectorBytesAllowed = true
        val futureParserImplementationRequiresSeparatePass =
            testVectorAdmission.futureParserImplementationRequiresSeparatePass
        val futureWriterImplementationRequiresSeparatePass =
            testVectorAdmission.futureWriterImplementationRequiresSeparatePass
        val futureParserWriterVectorExecutionRequiresSeparatePass = true
        val futureParserWriterKatExecutionRequiresSeparatePass =
            testVectorAdmission.futureParserWriterKatExecutionRequiresSeparatePass
        val futureVaultStorageRepositoryRequiresSeparatePass =
            testVectorAdmission.futureVaultStorageRepositoryRequiresSeparatePass
        val futureSecureStorageSuccessRequiresSeparatePass =
            testVectorAdmission.futureSecureStorageSuccessRequiresSeparatePass
        val futureSecureMetadataSuccessRequiresSeparatePass =
            testVectorAdmission.futureSecureMetadataSuccessRequiresSeparatePass
        val futureProductionSyncRequiresSeparatePass =
            testVectorAdmission.futureProductionSyncRequiresSeparatePass
        val futureProductionProviderSelectionRequiresSeparatePass =
            testVectorAdmission.futureProductionProviderSelectionRequiresSeparatePass

        val workingParserImplementationPresent = false
        val workingWriterImplementationPresent = false
        val productionVectorBytesPresent = testVectorAdmission.productionVectorBytesPresent
        val productionParserInputBytesPresent = false
        val productionWriterOutputBytesPresent = false
        val parserVectorExecutionPresent = testVectorAdmission.parserVectorExecutionPresent
        val writerVectorExecutionPresent = testVectorAdmission.writerVectorExecutionPresent
        val roundTripVectorExecutionPresent = testVectorAdmission.roundTripVectorExecutionPresent
        val negativeVectorExecutionPresent = testVectorAdmission.negativeVectorExecutionPresent
        val vaultContainerSerializationPresent = testVectorAdmission.vaultContainerSerializationPresent
        val vaultContainerParsingPresent = testVectorAdmission.vaultContainerParsingPresent
        val vaultContainerBytesProduced = testVectorAdmission.vaultContainerBytesProduced
        val vaultContainerBytesConsumed = testVectorAdmission.vaultContainerBytesConsumed
        val vaultHeaderSerialized = testVectorAdmission.vaultHeaderSerialized
        val vaultHeaderParsed = testVectorAdmission.vaultHeaderParsed
        val vaultRecordDirectorySerialized = testVectorAdmission.vaultRecordDirectorySerialized
        val vaultRecordDirectoryParsed = testVectorAdmission.vaultRecordDirectoryParsed
        val vaultRecordEnvelopeSerialized = testVectorAdmission.vaultRecordEnvelopeSerialized
        val vaultRecordEnvelopeParsed = testVectorAdmission.vaultRecordEnvelopeParsed
        val vaultFileReadPresent = testVectorAdmission.vaultFileReadPresent
        val vaultFileWritePresent = testVectorAdmission.vaultFileWritePresent
        val vaultFileDeletePresent = testVectorAdmission.vaultFileDeletePresent
        val vaultDirectoryCreated = testVectorAdmission.vaultDirectoryCreated
        val atomicReplaceImplementationPresent = testVectorAdmission.atomicReplaceImplementationPresent
        val partialWriteDetectionImplementationPresent =
            testVectorAdmission.partialWriteDetectionImplementationPresent
        val migrationImplementationPresent = testVectorAdmission.migrationImplementationPresent
        val migrationExecutionPresent = testVectorAdmission.migrationExecutionPresent
        val corruptionDetectionImplementationPresent =
            testVectorAdmission.corruptionDetectionImplementationPresent
        val corruptionRepairImplementationPresent =
            testVectorAdmission.corruptionRepairImplementationPresent
        val backupCreationPresent = testVectorAdmission.backupCreationPresent
        val rollbackImplementationPresent = testVectorAdmission.rollbackImplementationPresent
        val kdfExecutionPresent = testVectorAdmission.kdfExecutionPresent
        val aeadExecutionPresent = testVectorAdmission.aeadExecutionPresent
        val encryptionExecutionPresent = testVectorAdmission.encryptionExecutionPresent
        val decryptionExecutionPresent = testVectorAdmission.decryptionExecutionPresent
        val keyGenerationPresent = testVectorAdmission.keyGenerationPresent
        val nonceGenerationPresent = testVectorAdmission.nonceGenerationPresent
        val tinkKeysetCreationPresent = testVectorAdmission.tinkKeysetCreationPresent
        val tinkKeysetPersistencePresent = testVectorAdmission.tinkKeysetPersistencePresent
        val vaultStoragePathImplementationPresent =
            testVectorAdmission.vaultStoragePathImplementationPresent
        val encryptedVaultFileFormatImplemented = testVectorAdmission.encryptedVaultFileFormatImplemented
        val encryptedVaultRepositorySuccessPresent =
            testVectorAdmission.encryptedVaultRepositorySuccessPresent
        val lockSessionImplementationPresent = testVectorAdmission.lockSessionImplementationPresent
        val unlockImplementationPresent = testVectorAdmission.unlockImplementationPresent
        val runtimeSessionKeyPresent = testVectorAdmission.runtimeSessionKeyPresent
        val sessionKeyCached = testVectorAdmission.sessionKeyCached
        val plaintextCachePresent = testVectorAdmission.plaintextCachePresent
        val secureSecretStorageSuccessPathPresent =
            testVectorAdmission.secureSecretStorageSuccessPathPresent
        val secureMetadataStorageSuccessPathPresent =
            testVectorAdmission.secureMetadataStorageSuccessPathPresent
        val productionObservationPersistencePresent =
            testVectorAdmission.productionObservationPersistencePresent
        val productionAddressIndexPersistencePresent =
            testVectorAdmission.productionAddressIndexPersistencePresent
        val productionUtxoPersistencePresent = testVectorAdmission.productionUtxoPersistencePresent
        val productionWalletHistoryPersistencePresent =
            testVectorAdmission.productionWalletHistoryPersistencePresent
        val productionSyncPresent = testVectorAdmission.productionSyncPresent
        val productionBackendClientPresent = testVectorAdmission.productionBackendClientPresent
        val productionProviderSelectionEnabled = !providerSelection.selectedProviderIsDisabled
        val productionProviderSelectable = providerSelection.productionProviderSelectable
        val productionSelectionStillDisabledProviderOnly =
            providerSelection.selectedProviderIsDisabled &&
                !productionProviderSelectionEnabled &&
                !productionProviderSelectable
        val signingBroadcastingPresent = testVectorAdmission.signingBroadcastingPresent
        val uiActionEnablementPresent = testVectorAdmission.uiActionEnablementPresent
        val endpointPresent = testVectorAdmission.endpointPresent
        val mainnetPresent = testVectorAdmission.mainnetPresent

        val parserImplementationAuthorizationPresent = false
        val writerImplementationAuthorizationPresent = false
        val productionVectorCreationAuthorizationPresent = false
        val parserVectorExecutionAuthorizationPresent = false
        val writerVectorExecutionAuthorizationPresent = false
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

        val parserWriterImplementationScaffoldPresentIsEvidenceOnly =
            parserWriterImplementationScaffoldPresent &&
                disabledParserScaffoldPresent &&
                disabledWriterScaffoldPresent &&
                workingParserImplementationPresent.not() &&
                workingWriterImplementationPresent.not()
        val disabledParserScaffoldPresentIsNotParserImplementation =
            disabledParserScaffoldPresent &&
                workingParserImplementationPresent.not() &&
                disabledParserResult.accepted.not() &&
                disabledParserResult.consumedByteCount == 0
        val disabledWriterScaffoldPresentIsNotWriterImplementation =
            disabledWriterScaffoldPresent &&
                workingWriterImplementationPresent.not() &&
                disabledWriterResult.accepted.not() &&
                disabledWriterResult.producedByteCount == 0
        val parserInterfacePresentIsNotParserExecution =
            parserInterfacePresent && parserVectorExecutionPresent.not()
        val writerInterfacePresentIsNotWriterExecution =
            writerInterfacePresent && writerVectorExecutionPresent.not()
        val testSourceSyntheticVectorBytesAllowedIsNotProductionVectorAuthorization =
            testSourceSyntheticVectorBytesAllowed &&
                productionVectorBytesPresent.not() &&
                productionVectorCreationAuthorizationPresent.not()
        val futureTestSourceSyntheticBytesAreNotProductionVaultBytes =
            testSourceSyntheticVectorBytesAllowed &&
                vaultContainerBytesProduced.not() &&
                vaultContainerBytesConsumed.not() &&
                productionVectorBytesPresent.not()
        val disabledParserRejectsWithoutConsumingBytes =
            disabledParserResult.status == EncryptedVaultParserWriterScaffoldStatus.RejectedByScaffold &&
                disabledParserResult.accepted.not() &&
                disabledParserResult.consumedByteCount == 0 &&
                disabledParserResult.producedByteCount == 0
        val disabledWriterRejectsWithoutProducingBytes =
            disabledWriterResult.status == EncryptedVaultParserWriterScaffoldStatus.RejectedByScaffold &&
                disabledWriterResult.accepted.not() &&
                disabledWriterResult.consumedByteCount == 0 &&
                disabledWriterResult.producedByteCount == 0
        val testSourceSyntheticVectorCatalogCreated = false
        val testSourceSyntheticVectorBytesCreated = false
        val testSourceSyntheticVectorBytesExecuted = false
        val testSourceSyntheticVectorBytesLogged = false
        val testSourceSyntheticVectorCatalogConfinedToTestSource = true
        val diagnosticsPolicyContainsNoSensitiveMaterial = true
        val normalSourceMaterialGuardExcludesBuildHistory = true
        val localArtifactRootExcludedFromNormalSourceMaterialCorpus = true

        val priorEvidencePresent =
            storageReadinessDecisionPresent &&
                containerFormatV1DecisionPresent &&
                storagePathSessionLifecycleDecisionPresent &&
                migrationCorruptionPolicyDecisionPresent &&
                parserWriterAdmissionGatePresent &&
                parserWriterTestVectorAdmissionPresent &&
                encryptedVaultDesignPresent &&
                cryptoDecisionPresent &&
                dependencyReviewPresent &&
                providerBoundaryPresent &&
                secureStorageBoundaryPresent &&
                secureMetadataBoundaryPresent
        val scaffoldPresent =
            parserWriterImplementationScaffoldPresent &&
                disabledParserScaffoldPresent &&
                disabledWriterScaffoldPresent &&
                parserInterfacePresent &&
                writerInterfacePresent &&
                parserResultModelPresent &&
                writerResultModelPresent &&
                redactedDiagnosticsModelPresent &&
                testSourceSyntheticVectorCatalogAdmitted &&
                testSourceSyntheticVectorBytesAllowed
        val futureSeparatePassGatesPresent =
            futureParserImplementationRequiresSeparatePass &&
                futureWriterImplementationRequiresSeparatePass &&
                futureParserWriterVectorExecutionRequiresSeparatePass &&
                futureParserWriterKatExecutionRequiresSeparatePass &&
                futureVaultStorageRepositoryRequiresSeparatePass &&
                futureSecureStorageSuccessRequiresSeparatePass &&
                futureSecureMetadataSuccessRequiresSeparatePass &&
                futureProductionSyncRequiresSeparatePass &&
                futureProductionProviderSelectionRequiresSeparatePass
        val workingParserWriterPresent =
            workingParserImplementationPresent || workingWriterImplementationPresent
        val vectorRuntimeSurfacePresent =
            productionVectorBytesPresent ||
                productionParserInputBytesPresent ||
                productionWriterOutputBytesPresent ||
                parserVectorExecutionPresent ||
                writerVectorExecutionPresent ||
                roundTripVectorExecutionPresent ||
                negativeVectorExecutionPresent ||
                testSourceSyntheticVectorBytesCreated ||
                testSourceSyntheticVectorBytesExecuted ||
                testSourceSyntheticVectorBytesLogged
        val parserWriterRuntimeSurfacePresent =
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
            parserImplementationAuthorizationPresent ||
                writerImplementationAuthorizationPresent ||
                productionVectorCreationAuthorizationPresent ||
                parserVectorExecutionAuthorizationPresent ||
                writerVectorExecutionAuthorizationPresent ||
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
        val disabledParserWriterContractBroken =
            disabledParserRejectsWithoutConsumingBytes.not() ||
                disabledWriterRejectsWithoutProducingBytes.not()
        val testSourceVectorConfinementBroken =
            testSourceSyntheticVectorCatalogConfinedToTestSource.not() ||
                testSourceSyntheticVectorBytesCreated ||
                testSourceSyntheticVectorBytesExecuted ||
                testSourceSyntheticVectorBytesLogged
        val diagnosticsMaterialPresent = diagnosticsPolicyContainsNoSensitiveMaterial.not()
        val corpusBoundaryPresent =
            normalSourceMaterialGuardExcludesBuildHistory &&
                localArtifactRootExcludedFromNormalSourceMaterialCorpus

        val failures = buildList {
            if (!priorEvidencePresent) {
                add(EncryptedVaultParserWriterScaffoldFailureLabel.PriorEvidenceMissing)
            }
            if (!scaffoldPresent) {
                add(EncryptedVaultParserWriterScaffoldFailureLabel.ScaffoldMissing)
            }
            if (!futureSeparatePassGatesPresent) {
                add(EncryptedVaultParserWriterScaffoldFailureLabel.FutureSeparatePassGateMissing)
            }
            if (workingParserWriterPresent) {
                add(EncryptedVaultParserWriterScaffoldFailureLabel.WorkingParserWriterPresent)
            }
            if (vectorRuntimeSurfacePresent) {
                add(EncryptedVaultParserWriterScaffoldFailureLabel.VectorRuntimeSurfacePresent)
            }
            if (parserWriterRuntimeSurfacePresent) {
                add(EncryptedVaultParserWriterScaffoldFailureLabel.ParserWriterRuntimeSurfacePresent)
            }
            if (fileStorageRuntimeSurfacePresent) {
                add(EncryptedVaultParserWriterScaffoldFailureLabel.FileStorageRuntimeSurfacePresent)
            }
            if (migrationCorruptionRuntimeSurfacePresent) {
                add(EncryptedVaultParserWriterScaffoldFailureLabel.MigrationCorruptionRuntimeSurfacePresent)
            }
            if (cryptoRuntimeSurfacePresent) {
                add(EncryptedVaultParserWriterScaffoldFailureLabel.CryptoRuntimeSurfacePresent)
            }
            if (lockSessionRuntimeSurfacePresent) {
                add(EncryptedVaultParserWriterScaffoldFailureLabel.LockSessionRuntimeSurfacePresent)
            }
            if (productionStorageSurfacePresent) {
                add(EncryptedVaultParserWriterScaffoldFailureLabel.ProductionStorageSurfacePresent)
            }
            if (productionSyncSurfacePresent) {
                add(EncryptedVaultParserWriterScaffoldFailureLabel.ProductionSyncSurfacePresent)
            }
            if (productionProviderSelectionSurfacePresent || !productionSelectionStillDisabledProviderOnly) {
                add(EncryptedVaultParserWriterScaffoldFailureLabel.ProductionProviderSelectionSurfacePresent)
            }
            if (signingBroadcastingUiEndpointOrMainnetSurfacePresent) {
                add(
                    EncryptedVaultParserWriterScaffoldFailureLabel
                        .SigningBroadcastingUiEndpointOrMainnetSurfacePresent,
                )
            }
            if (productionAuthorizationPresent) {
                add(EncryptedVaultParserWriterScaffoldFailureLabel.ProductionAuthorizationPresent)
            }
            if (disabledParserWriterContractBroken) {
                add(EncryptedVaultParserWriterScaffoldFailureLabel.DisabledParserWriterContractBroken)
            }
            if (testSourceVectorConfinementBroken) {
                add(EncryptedVaultParserWriterScaffoldFailureLabel.TestSourceVectorConfinementBroken)
            }
            if (diagnosticsMaterialPresent) {
                add(EncryptedVaultParserWriterScaffoldFailureLabel.DiagnosticsMaterialPresent)
            }
            if (!corpusBoundaryPresent) {
                add(EncryptedVaultParserWriterScaffoldFailureLabel.CorpusBoundaryMissing)
            }
        }

        val parserWriterScaffoldDecisionPassed = failures.isEmpty()

        return EncryptedVaultParserWriterScaffold(
            scaffoldId = EncryptedVaultParserWriterScaffoldSafeLabel(
                "skald-encrypted-local-vault-parser-writer-scaffold-v1",
            ),
            scaffoldVersion = 1,
            scaffoldKind =
                EncryptedVaultParserWriterScaffoldKind
                    .EncryptedLocalVaultParserWriterImplementationScaffold,
            sourceSet = EncryptedVaultParserWriterScaffoldSourceSet.CommonMainPolicy,
            storageReadinessDecisionPresent = storageReadinessDecisionPresent,
            containerFormatV1DecisionPresent = containerFormatV1DecisionPresent,
            storagePathSessionLifecycleDecisionPresent =
                storagePathSessionLifecycleDecisionPresent,
            migrationCorruptionPolicyDecisionPresent = migrationCorruptionPolicyDecisionPresent,
            parserWriterAdmissionGatePresent = parserWriterAdmissionGatePresent,
            parserWriterTestVectorAdmissionPresent = parserWriterTestVectorAdmissionPresent,
            encryptedVaultDesignPresent = encryptedVaultDesignPresent,
            cryptoDecisionPresent = cryptoDecisionPresent,
            dependencyReviewPresent = dependencyReviewPresent,
            providerBoundaryPresent = providerBoundaryPresent,
            secureStorageBoundaryPresent = secureStorageBoundaryPresent,
            secureMetadataBoundaryPresent = secureMetadataBoundaryPresent,
            parserWriterImplementationScaffoldPresent =
                parserWriterImplementationScaffoldPresent,
            disabledParserScaffoldPresent = disabledParserScaffoldPresent,
            disabledWriterScaffoldPresent = disabledWriterScaffoldPresent,
            parserInterfacePresent = parserInterfacePresent,
            writerInterfacePresent = writerInterfacePresent,
            parserResultModelPresent = parserResultModelPresent,
            writerResultModelPresent = writerResultModelPresent,
            redactedDiagnosticsModelPresent = redactedDiagnosticsModelPresent,
            testSourceSyntheticVectorCatalogAdmitted = testSourceSyntheticVectorCatalogAdmitted,
            testSourceSyntheticVectorBytesAllowed = testSourceSyntheticVectorBytesAllowed,
            futureParserImplementationRequiresSeparatePass =
                futureParserImplementationRequiresSeparatePass,
            futureWriterImplementationRequiresSeparatePass =
                futureWriterImplementationRequiresSeparatePass,
            futureParserWriterVectorExecutionRequiresSeparatePass =
                futureParserWriterVectorExecutionRequiresSeparatePass,
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
            workingParserImplementationPresent = workingParserImplementationPresent,
            workingWriterImplementationPresent = workingWriterImplementationPresent,
            productionVectorBytesPresent = productionVectorBytesPresent,
            productionParserInputBytesPresent = productionParserInputBytesPresent,
            productionWriterOutputBytesPresent = productionWriterOutputBytesPresent,
            parserVectorExecutionPresent = parserVectorExecutionPresent,
            writerVectorExecutionPresent = writerVectorExecutionPresent,
            roundTripVectorExecutionPresent = roundTripVectorExecutionPresent,
            negativeVectorExecutionPresent = negativeVectorExecutionPresent,
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
            parserImplementationAuthorizationPresent = parserImplementationAuthorizationPresent,
            writerImplementationAuthorizationPresent = writerImplementationAuthorizationPresent,
            productionVectorCreationAuthorizationPresent =
                productionVectorCreationAuthorizationPresent,
            parserVectorExecutionAuthorizationPresent = parserVectorExecutionAuthorizationPresent,
            writerVectorExecutionAuthorizationPresent = writerVectorExecutionAuthorizationPresent,
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
            parserWriterImplementationScaffoldPresentIsEvidenceOnly =
                parserWriterImplementationScaffoldPresentIsEvidenceOnly,
            disabledParserScaffoldPresentIsNotParserImplementation =
                disabledParserScaffoldPresentIsNotParserImplementation,
            disabledWriterScaffoldPresentIsNotWriterImplementation =
                disabledWriterScaffoldPresentIsNotWriterImplementation,
            parserInterfacePresentIsNotParserExecution = parserInterfacePresentIsNotParserExecution,
            writerInterfacePresentIsNotWriterExecution = writerInterfacePresentIsNotWriterExecution,
            testSourceSyntheticVectorBytesAllowedIsNotProductionVectorAuthorization =
                testSourceSyntheticVectorBytesAllowedIsNotProductionVectorAuthorization,
            futureTestSourceSyntheticBytesAreNotProductionVaultBytes =
                futureTestSourceSyntheticBytesAreNotProductionVaultBytes,
            disabledParserRejectsWithoutConsumingBytes =
                disabledParserRejectsWithoutConsumingBytes,
            disabledWriterRejectsWithoutProducingBytes =
                disabledWriterRejectsWithoutProducingBytes,
            testSourceSyntheticVectorCatalogCreated = testSourceSyntheticVectorCatalogCreated,
            testSourceSyntheticVectorBytesCreated = testSourceSyntheticVectorBytesCreated,
            testSourceSyntheticVectorBytesExecuted = testSourceSyntheticVectorBytesExecuted,
            testSourceSyntheticVectorBytesLogged = testSourceSyntheticVectorBytesLogged,
            testSourceSyntheticVectorCatalogConfinedToTestSource =
                testSourceSyntheticVectorCatalogConfinedToTestSource,
            diagnosticsPolicyContainsNoSensitiveMaterial =
                diagnosticsPolicyContainsNoSensitiveMaterial,
            normalSourceMaterialGuardExcludesBuildHistory =
                normalSourceMaterialGuardExcludesBuildHistory,
            localArtifactRootExcludedFromNormalSourceMaterialCorpus =
                localArtifactRootExcludedFromNormalSourceMaterialCorpus,
            parserWriterScaffoldDecisionPassed = parserWriterScaffoldDecisionPassed,
            evidenceCount = 12,
            scaffoldCheckCount = checks.size,
            blockerCount = failures.size,
            warningCount = 0,
            policyLabels = policyLabels,
            scaffoldChecks = checks,
            failureLabels = failures,
            displayLabel = EncryptedVaultParserWriterScaffoldSafeLabel(
                "encrypted vault parser writer implementation scaffold",
            ),
        )
    }
}
