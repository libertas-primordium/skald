package com.libertasprimordium.skald.security

enum class EncryptedVaultImplementationStatus(
    val label: String,
    val implementationAvailable: Boolean,
) {
    NotImplemented("not implemented", implementationAvailable = false),
}

enum class EncryptedVaultDecisionRole(
    val label: String,
    val acceptedAsProductionDefault: Boolean,
) {
    DesignTarget("design target only", acceptedAsProductionDefault = false),
    ReviewedCompatibilityFallback("reviewed compatibility fallback only", acceptedAsProductionDefault = false),
    PlatformWrappingOrReviewedFallback("platform wrapping or reviewed fallback only", acceptedAsProductionDefault = false),
    RejectedDefault("rejected as production default", acceptedAsProductionDefault = false),
}

enum class EncryptedVaultKdfAlgorithm(
    val label: String,
    val role: EncryptedVaultDecisionRole,
    val memoryHard: Boolean,
) {
    Argon2id(
        label = "Argon2id",
        role = EncryptedVaultDecisionRole.DesignTarget,
        memoryHard = true,
    ),
    Scrypt(
        label = "scrypt",
        role = EncryptedVaultDecisionRole.ReviewedCompatibilityFallback,
        memoryHard = true,
    ),
    Pbkdf2(
        label = "PBKDF2",
        role = EncryptedVaultDecisionRole.RejectedDefault,
        memoryHard = false,
    ),
}

enum class EncryptedVaultAeadAlgorithm(
    val label: String,
    val role: EncryptedVaultDecisionRole,
    val preferredRecordEnvelope: Boolean,
) {
    XChaCha20Poly1305(
        label = "XChaCha20-Poly1305",
        role = EncryptedVaultDecisionRole.DesignTarget,
        preferredRecordEnvelope = true,
    ),
    ChaCha20Poly1305(
        label = "ChaCha20-Poly1305",
        role = EncryptedVaultDecisionRole.ReviewedCompatibilityFallback,
        preferredRecordEnvelope = false,
    ),
    Aes256Gcm(
        label = "AES-256-GCM",
        role = EncryptedVaultDecisionRole.PlatformWrappingOrReviewedFallback,
        preferredRecordEnvelope = false,
    ),
    AesGcmSiv(
        label = "AES-GCM-SIV",
        role = EncryptedVaultDecisionRole.ReviewedCompatibilityFallback,
        preferredRecordEnvelope = false,
    ),
}

data class EncryptedVaultNoncePolicy(
    val label: String,
    val byteLength: Int,
    val randomPerRecord: Boolean,
    val duplicateNonceIsCorruption: Boolean,
    val designOnly: Boolean,
) {
    companion object {
        val XChaCha20Random24ByteRecordNonce = EncryptedVaultNoncePolicy(
            label = "random 24-byte XChaCha20-Poly1305 nonce per record",
            byteLength = 24,
            randomPerRecord = true,
            duplicateNonceIsCorruption = true,
            designOnly = true,
        )
    }
}

enum class EncryptedVaultAssociatedDataRequirement(val label: String) {
    BindVaultMagicDomainMarker("bind vault magic/domain marker"),
    BindVaultFormatVersion("bind vault format version"),
    BindProviderSuiteId("bind provider suite id"),
    BindVaultId("bind opaque vault id"),
    BindRecordFormatPolicyId("bind record format policy id"),
    BindAadPolicyId("bind AAD policy id"),
    BindRecordId("bind opaque record id"),
    BindRecordClass("bind record class"),
    BindRecordVersionOrCounter("bind record version or monotonic counter"),
    BindHeaderCommitmentContext("bind header commitment context"),
    BindIntegrityCriticalRecordMetadata("bind integrity-critical record metadata"),
    ExcludeSensitiveWalletMetadata("exclude sensitive wallet metadata from associated data"),
}

enum class EncryptedVaultKeyHierarchyRequirement(val label: String) {
    UserUnlockSecretRequired("user unlock secret required"),
    PassphraseDerivedRootMaterial("passphrase-derived root material"),
    Argon2idRootMaterial64Bytes("Argon2id derives 64-byte root material for v1"),
    HkdfSha256KeyExpansionSelected("HKDF-SHA-256 selected for v1 key expansion"),
    HmacSha256HeaderCommitmentSelected("HMAC-SHA-256 selected for v1 header commitment"),
    HeaderCommitmentKey32Bytes("header commitment key material is 32 bytes"),
    RecordAeadKey32Bytes("record AEAD key material is 32 bytes"),
    VersionedKeySeparationLabelsRequired("versioned key-separation labels required"),
    HeaderCommitmentKeySeparated("header commitment key material separated from record AEAD key material"),
    RecordAeadKeyMaterialDerivedFromRoot("record AEAD key material derived from root material"),
    FutureWrappingMetadataLabelReserved("future wrapping metadata label reserved but not implemented"),
    FutureExportMigrationLabelReserved("future export/migration label reserved but not implemented"),
    NoRandomTinkVaultKeyInV1("no random Tink vault key in v1"),
    NoPersistedTinkKeysetInV1("no persisted Tink keyset in v1"),
    SeparateMetadataEncryptionKey("separate metadata encryption key"),
    SeparateSecretPayloadEncryptionKey("separate secret payload encryption key"),
    SeparateBackupExportEncryptionKey("separate backup/export encryption key"),
    OptionalPlatformWrappingOnly("optional platform wrapping only"),
}

enum class EncryptedVaultPlatform(val label: String) {
    Android("Android"),
    LinuxDesktop("Linux desktop"),
}

enum class EncryptedVaultPlatformWrappingRole(
    val label: String,
    val primaryStorage: Boolean,
) {
    OptionalWrappingHelperAfterReview("optional wrapping helper after review", primaryStorage = false),
    NotPrimaryStorage("not primary storage", primaryStorage = false),
}

data class EncryptedVaultPlatformPolicy(
    val platform: EncryptedVaultPlatform,
    val appControlledVaultIsPrimary: Boolean,
    val passphraseUnlockRequired: Boolean,
    val platformWrappingRole: EncryptedVaultPlatformWrappingRole,
    val hardwareBackedWrappingOptional: Boolean,
    val osKeyringPrimaryStorageAllowed: Boolean,
    val implementationNote: String,
) {
    val isFailClosedDesign: Boolean
        get() = appControlledVaultIsPrimary && !osKeyringPrimaryStorageAllowed
}

enum class EncryptedVaultRequirement(val label: String) {
    DependencySelectionReviewed("dependency selection reviewed"),
    DisabledProviderBoundaryModeled("disabled provider boundary modeled"),
    ProviderSelectionBoundaryModeled("provider selection boundary modeled"),
    ProviderKatContractModeled("provider-level KAT contract modeled"),
    ProviderLevelKatStrategyContractModeled("provider-level KAT strategy contract modeled"),
    RandomizedAeadBehavioralKatPolicyModeled("randomized AEAD behavioral KAT policy modeled"),
    IntegratedVerificationOrderKatPolicyModeled("integrated verification-order KAT policy modeled"),
    StillDisabledProviderIntegrationHarnessImplementedAndTested(
        "still-disabled provider integration harness implemented and tested",
    ),
    ProviderLevelKatsExecutedInStillDisabledHarness(
        "provider-level KATs executed in still-disabled harness",
    ),
    RandomizedAeadBehavioralKatsExecutedInStillDisabledHarness(
        "randomized AEAD behavioral KATs executed in still-disabled harness",
    ),
    IntegratedVerificationOrderKatsExecutedInStillDisabledHarness(
        "integrated verification-order KATs executed in still-disabled harness",
    ),
    StillDisabledProviderFacadeImplementedAndTested(
        "still-disabled provider facade implemented and tested",
    ),
    StillDisabledProviderFacadeMetadataOnly(
        "still-disabled provider facade exposes metadata only",
    ),
    StillDisabledProviderFacadeOperationsDisabled(
        "still-disabled provider facade operations return disabled results",
    ),
    VaultContainerContractModeled("vault container contract modeled"),
    ManifestContractModeled("manifest contract modeled"),
    StaleRecordManifestPolicyModeled("stale-record and rollback manifest policy modeled"),
    StoragePolicyContractModeled("storage policy contract modeled"),
    PlatformStorageBoundaryContractModeled("platform storage boundary contract modeled"),
    AtomicityCrashRecoveryContractModeled("atomicity and crash-recovery contract modeled"),
    AtomicWriteStrategyContractModeled("atomic write strategy contract modeled"),
    CrashRecoveryContractModeled("crash-recovery contract modeled"),
    StorageInterruptionTestContractModeled("storage interruption-test contract modeled"),
    StorageAtomicityCrashSimulatorImplementedAndTested(
        "in-memory storage atomicity/crash simulator implemented and tested",
    ),
    StorageFailureModelModeled("storage failure model modeled"),
    StorageNamespacePathHygieneModeled("storage namespace/path hygiene modeled"),
    StorageNamespacePathPolicyImplementedAndTested(
        "storage namespace/path policy implemented and tested",
    ),
    StorageLayoutPlanImplementedAndTested(
        "rootless logical storage layout plan implemented and tested",
    ),
    PathContainmentPlannerImplementedAndTested(
        "path-containment planner implemented and tested",
    ),
    PlatformStorageRootContractModeled("platform storage root contract modeled"),
    PlatformRootSettingsPolicyModeled("platform root settings policy modeled"),
    AndroidAppPrivateRootPolicyModeled("Android app-private internal root policy modeled"),
    LinuxRootSettingsPolicyModeled("Linux root settings policy modeled"),
    OsKeyringPassphraseStorageRejected("OS keyring passphrase storage rejected"),
    PasswordManagerIntegrationRejected("password-manager integration rejected"),
    PassphraseFirstDefaultModeled("passphrase-first default modeled"),
    SettingsUiAbsent("vault storage-root Settings UI absent"),
    SettingsPersistenceAbsent("vault storage-root settings persistence absent"),
    SafePathConstructionContractModeled("safe path-construction contract modeled"),
    SymlinkTraversalContractModeled("symlink and filesystem traversal contract modeled"),
    StoragePermissionOwnershipContractModeled("storage permission and ownership contract modeled"),
    DurabilityCapabilityContractModeled("durability capability contract modeled"),
    DurabilityFailClosedPolicyModeled("durability fail-closed policy modeled"),
    WarningOnlyDurabilityPersistenceRejected("warning-only durability persistence rejected"),
    SecureStorageBoundaryContractModeled("secure storage boundary contract modeled"),
    RollbackLimitationAndAntiRollbackAnchorModeled(
        "rollback limitation and anti-rollback anchor status modeled",
    ),
    KdfCalibrationPolicyModeled("KDF calibration policy modeled"),
    KdfCandidateParameterPolicyModeled("candidate KDF parameter policy modeled"),
    Argon2idCalibrationPolicyImplementedAndTested(
        "Argon2id calibration policy building block implemented and tested",
    ),
    Argon2idCandidateSelectionPolicyImplementedAndTested(
        "Argon2id calibration candidate-selection policy implemented and tested",
    ),
    Argon2idMemoryFailureHandlingModeledAndTested(
        "Argon2id calibration memory/execution failure handling modeled and tested",
    ),
    Argon2idStoredParameterNoDowngradeModeledAndTested(
        "Argon2id stored-parameter no-downgrade behavior modeled and tested",
    ),
    AndroidCompatibilityEntropyPolicyModeled("Android compatibility and entropy policy modeled"),
    RuntimeCryptoProviderChecksModeled("runtime crypto provider checks modeled"),
    RuntimeRandomnessProviderChecksModeled("runtime randomness provider checks modeled"),
    RuntimeEntropyChecksModeled("runtime cryptographic randomness checks modeled"),
    VaultCreationFailClosedWarningModeled("vault-creation fail-closed warning modeled"),
    ProductionProviderAcceptanceContractModeled("production provider acceptance contract modeled"),
    VaultHeaderCommitmentPolicyModeled("vault header commitment policy modeled"),
    HkdfSha256KeyExpansionPolicyModeled("HKDF-SHA-256 key-expansion policy modeled"),
    HmacSha256HeaderCommitmentPrimitiveModeled("HMAC-SHA-256 header-commitment primitive modeled"),
    KeyExpansionOutputLayoutModeled("key-expansion output layout modeled"),
    PrimitiveThreatModelRationaleModeled("primitive threat model and rationale modeled"),
    CanonicalHeaderByteVectorContractModeled("canonical header byte vector contract modeled"),
    HkdfSha256VectorContractModeled("HKDF-SHA-256 vector contract modeled"),
    HmacSha256HeaderCommitmentVectorContractModeled(
        "HMAC-SHA-256 header-commitment vector contract modeled",
    ),
    CanonicalHeaderEncodingPolicyModeled("canonical header encoding policy modeled"),
    KeySeparationLabelsPolicyModeled("key-separation labels policy modeled"),
    CanonicalHeaderSerializerImplementedAndVectorTested(
        "canonical header serializer implemented and vector-tested",
    ),
    HkdfSha256KeyExpansionImplementedAndVectorTested(
        "HKDF-SHA-256 key expansion implemented and vector-tested",
    ),
    HmacSha256HeaderCommitmentImplementedAndVectorTested(
        "HMAC-SHA-256 header commitment implemented and vector-tested",
    ),
    StrictAadContractModeled("strict AEAD associated-data contract modeled"),
    StrictAadSerializationImplementedAndTested(
        "strict AEAD associated-data serialization implemented and tested",
    ),
    TinkRecordAeadBuildingBlockImplementedAndTested(
        "Tink XChaCha20-Poly1305 record AEAD building block implemented and tested",
    ),
    TinkNonKeyCommitmentMitigationModeled("Tink non-key-commitment mitigation modeled"),
    PassphraseEncodingPolicyModeled("passphrase encoding policy modeled"),
    PassphrasePolicyValidationImplementedAndTested(
        "passphrase policy validation implemented and tested",
    ),
    Argon2idPassphraseRootDerivationImplementedAndTested(
        "Argon2id passphrase-to-root-material derivation implemented and tested",
    ),
    TinkRawKeyFeasibilityPolicyModeled("Tink raw-key feasibility policy modeled"),
    Argon2idBoundedCalibrationPolicyModeled("bounded Argon2id calibration policy modeled"),
    KdfParametersCalibrated("KDF parameters calibrated"),
    AeadImplementationVerified("AEAD implementation verified"),
    ProviderBoundaryKnownAnswerVectorsPassed("provider-boundary known-answer vectors passed"),
    KnownAnswerVectorsIdentified("known-answer vectors identified"),
    VaultContainerFormatImplemented("vault container format implemented"),
    VaultContainerParserImplemented("vault container parser implemented"),
    VaultContainerWriterImplemented("vault container writer implemented"),
    ManifestParserImplemented("in-memory manifest parser implemented"),
    ManifestWriterImplemented("in-memory manifest writer implemented"),
    StaleRecordDecisionPolicyImplemented("local manifest-relative stale-record decision policy implemented"),
    LockSessionLifecycleTested("lock/session lifecycle tested"),
    RedactionTestsPassed("redaction tests passed"),
    MigrationAndCorruptionTestsPassed("migration and corruption tests passed"),
    SecureSecretStorageAvailable("secure secret storage available"),
    SecureMetadataStorageAvailable("secure metadata storage available"),
    ProductionPersistenceApproved("production persistence approved"),
    MainnetReleaseApproved("mainnet release approved"),
}

enum class EncryptedVaultRequirementStatus(
    val label: String,
    val satisfiedForProductionPersistence: Boolean,
) {
    Unresolved("unresolved", satisfiedForProductionPersistence = false),
    CandidateReviewedOnly("candidate reviewed only", satisfiedForProductionPersistence = false),
    ImplementedStillDisabled("implemented but still disabled", satisfiedForProductionPersistence = false),
    Absent("absent", satisfiedForProductionPersistence = false),
    DisabledByPolicy("disabled by policy", satisfiedForProductionPersistence = false),
}

enum class EncryptedVaultBlockingIssue(val label: String) {
    VaultImplementationUnavailable("vault implementation unavailable"),
    CryptoDependenciesNotSelected("crypto dependencies not selected for implementation"),
    ProductionProviderImplementationUnavailable("production provider implementation unavailable"),
    ProviderSelectionProductionBlocked("provider selection production blocked"),
    ProductionProviderAcceptanceContractIncomplete("production provider acceptance contract incomplete"),
    VaultHeaderCommitmentUnimplemented("vault header commitment unimplemented"),
    HkdfSha256KeyExpansionUnimplemented("HKDF-SHA-256 key expansion unimplemented"),
    HmacSha256HeaderCommitmentUnimplemented("HMAC-SHA-256 header commitment unimplemented"),
    KeyExpansionOutputLayoutUnimplemented("key-expansion output layout unimplemented"),
    CanonicalHeaderByteVectorsTestScopeOnly("canonical header byte vectors are test-scope evidence only"),
    HkdfSha256VectorsTestScopeOnly("HKDF-SHA-256 vectors are test-scope evidence only"),
    HmacSha256HeaderCommitmentVectorsTestScopeOnly(
        "HMAC-SHA-256 header commitment vectors are test-scope evidence only",
    ),
    CanonicalHeaderEncodingUnimplemented("canonical header encoding unimplemented"),
    KeySeparationLabelsUnimplemented("key-separation labels unimplemented"),
    VaultHeaderCommitmentProviderIntegrationMissing(
        "vault header commitment building block is not wired into a provider",
    ),
    HkdfSha256KeyExpansionProviderIntegrationMissing(
        "HKDF-SHA-256 key expansion building block is not wired into a provider",
    ),
    HmacSha256HeaderCommitmentProviderIntegrationMissing(
        "HMAC-SHA-256 header commitment building block is not wired into a provider",
    ),
    KeyExpansionOutputLayoutProviderIntegrationMissing(
        "key-expansion output layout is not wired into a provider",
    ),
    CanonicalHeaderSerializerProviderIntegrationMissing(
        "canonical header serializer is not wired into a provider or vault format boundary",
    ),
    KeySeparationLabelsProviderIntegrationMissing(
        "key-separation labels are not wired into a provider",
    ),
    StrictAadProviderIntegrationMissing(
        "strict AEAD associated-data building block is not wired into a provider",
    ),
    TinkRecordAeadProviderIntegrationMissing(
        "Tink record AEAD building block is not wired into a provider",
    ),
    StillDisabledProviderIntegrationHarnessNotSelectable(
        "still-disabled provider integration harness is not a selectable production provider",
    ),
    StillDisabledProviderFacadeNotSelectable(
        "still-disabled provider facade is not a selectable production provider",
    ),
    StaleRecordManifestIntegrationMissing(
        "record version/counter binding is not backed by a trusted manifest or storage index",
    ),
    PassphraseEncodingPolicyUnapproved("passphrase encoding policy unapproved"),
    PassphrasePolicyProviderIntegrationMissing(
        "passphrase policy validation is not wired into vault creation or unlock",
    ),
    Argon2idRootDerivationProviderIntegrationMissing(
        "Argon2id root derivation is not wired into a provider or unlock path",
    ),
    TinkRawKeyFeasibilityProbeOnly("Tink raw-key feasibility probe is test-scope evidence only"),
    Argon2idBoundedCalibrationUnapproved("bounded Argon2id calibration unapproved"),
    KdfParametersUncalibrated("KDF parameters uncalibrated"),
    AeadDependencyUnverified("AEAD dependency unverified"),
    ProviderKnownAnswerVectorsMissing("provider known-answer vectors missing"),
    ProviderLevelKatStrategyContractOnly("provider-level KAT strategy is contract/model evidence only"),
    RandomizedAeadBehavioralKatExecutionMissing("randomized AEAD behavioral KAT execution missing"),
    IntegratedVerificationOrderKatExecutionMissing("integrated verification-order KAT execution missing"),
    StaleRecordManifestPolicyImplementationMissing("stale-record and rollback manifest policy unimplemented"),
    VaultContainerPersistenceImplementationMissing("vault container persistence implementation missing"),
    ManifestReadWriteImplementationMissing("manifest storage read/write implementation missing"),
    StorageSuccessPathAbsent("storage success path absent"),
    PlatformStorageImplementationMissing("platform storage implementation missing"),
    AtomicWriteImplementationMissing("atomic write implementation missing"),
    CrashRecoveryImplementationMissing("crash-recovery implementation missing"),
    StorageInterruptionTestsMissing("storage interruption tests missing"),
    StorageFailureRuntimeMappingMissing("storage failure runtime mapping missing"),
    StorageNamespacePathImplementationMissing("storage namespace/path implementation missing"),
    StoragePathConstructionImplementationMissing("actual storage path construction implementation missing"),
    PlatformStorageRootImplementationMissing("platform storage root resolution implementation missing"),
    PlatformRootSettingsImplementationMissing("platform root settings implementation missing"),
    SettingsUiMissing("vault storage-root Settings UI missing"),
    SettingsPersistenceMissing("vault storage-root settings persistence missing"),
    LinuxCustomRootValidationMissing("Linux custom root validation missing"),
    SafePathConstructionImplementationMissing("safe storage path construction implementation missing"),
    SymlinkTraversalImplementationMissing("symlink and traversal check implementation missing"),
    StoragePermissionOwnershipImplementationMissing("storage permission and ownership check implementation missing"),
    DurabilityCapabilityProbeImplementationMissing("durability capability probe implementation missing"),
    DurabilityFailClosedRuntimeEvidenceMissing(
        "durability fail-closed runtime evidence missing",
    ),
    WarningOnlyDurabilityPersistenceRejected("warning-only durability persistence rejected"),
    AntiRollbackAnchorAbsentNoFullRollbackClaim(
        "anti-rollback anchor absent; full local-directory rollback resistance is not claimed",
    ),
    ManifestStorageAtomicityReviewMissing("manifest/storage atomicity and crash-recovery review missing"),
    KnownAnswerVectorsMissing("known-answer vectors missing"),
    VaultContainerFormatAbsent("vault container format absent"),
    LockSessionLifecycleUntested("lock/session lifecycle untested"),
    RedactionTestsMissing("redaction tests missing"),
    MigrationAndCorruptionTestsMissing("migration and corruption tests missing"),
    SecureSecretStorageDisabled("secure secret storage disabled"),
    SecureMetadataStorageDisabled("secure metadata storage disabled"),
    ProductionPersistenceDisabled("production persistence disabled"),
    MainnetDisabled("mainnet disabled"),
}

enum class EncryptedVaultWarning(val label: String) {
    ReadinessOnlyNoEncryption("readiness model only; no encryption"),
    AlgorithmTargetsAreDesignOnly("algorithm targets are design-only"),
    KdfCalibrationProbeOnly("KDF calibration policy/probes are not production settings"),
    KdfCandidateParameterPolicyNotFinal("candidate KDF parameter policy is not final"),
    OsKeyringsNotPrimaryStorage("OS keyrings are not primary storage"),
    OsKeyringPassphraseStorageRejected("OS keyring passphrase storage rejected"),
    PasswordManagerIntegrationRejected("Skald-managed password-manager integration rejected"),
    AndroidWrappingOptional("Android wrapping is optional"),
    LinuxPassphraseFirst("Linux passphrase-first vault policy"),
    TorRoutingMetadataSensitive("Tor routing metadata is sensitive metadata"),
    MemoryClearingBestEffort("memory clearing is best-effort"),
}

enum class EncryptedVaultCapability(
    val label: String,
    val enabledInProduction: Boolean,
) {
    ReadinessPolicyModel("readiness policy model", enabledInProduction = true),
    AlgorithmDecisionRecord("algorithm decision record", enabledInProduction = true),
    PlatformPolicyModel("platform policy model", enabledInProduction = true),
    Argon2idCalibrationPolicyModel("Argon2id calibration policy model", enabledInProduction = true),
    Argon2idCandidateParameterPolicyModel("Argon2id candidate parameter policy model", enabledInProduction = true),
    Argon2idCalibrationPolicyBuildingBlock(
        "still-disabled Argon2id calibration policy building block",
        enabledInProduction = false,
    ),
    Argon2idCandidateSelectionPolicyBuildingBlock(
        "still-disabled Argon2id candidate-selection policy building block",
        enabledInProduction = false,
    ),
    Argon2idMemoryFailureHandlingModel(
        "still-disabled Argon2id memory/execution failure model",
        enabledInProduction = false,
    ),
    Argon2idStoredParameterNoDowngradeModel(
        "still-disabled Argon2id stored-parameter no-downgrade model",
        enabledInProduction = false,
    ),
    AndroidArgon2idCalibrationEvidenceCaptureModel(
        "Android Argon2id calibration evidence capture model",
        enabledInProduction = true,
    ),
    ProviderKatContractModel("provider-level KAT contract model", enabledInProduction = true),
    ProviderLevelKatStrategyContractModel("provider-level KAT strategy contract model", enabledInProduction = true),
    RandomizedAeadBehavioralKatPolicyModel(
        "randomized AEAD behavioral KAT policy model",
        enabledInProduction = true,
    ),
    IntegratedVerificationOrderKatPolicyModel(
        "integrated verification-order KAT policy model",
        enabledInProduction = true,
    ),
    StillDisabledProviderIntegrationHarness(
        "still-disabled integrated provider KAT harness",
        enabledInProduction = false,
    ),
    StillDisabledProviderLevelKatExecution(
        "still-disabled provider-level KAT execution",
        enabledInProduction = false,
    ),
    StillDisabledRandomizedAeadBehavioralKatExecution(
        "still-disabled randomized AEAD behavioral KAT execution",
        enabledInProduction = false,
    ),
    StillDisabledIntegratedVerificationOrderKatExecution(
        "still-disabled integrated verification-order KAT execution",
        enabledInProduction = false,
    ),
    StillDisabledProviderFacadeBoundary(
        "still-disabled provider facade boundary",
        enabledInProduction = false,
    ),
    VaultContainerContractModel("vault container contract model", enabledInProduction = true),
    ManifestContractModel("manifest contract model", enabledInProduction = true),
    StaleRecordManifestPolicyModel(
        "stale-record and rollback manifest policy model",
        enabledInProduction = true,
    ),
    StoragePolicyContractModel("storage policy contract model", enabledInProduction = true),
    PlatformStorageBoundaryContractModel(
        "platform storage boundary contract model",
        enabledInProduction = true,
    ),
    AtomicityCrashRecoveryContractModel(
        "atomicity and crash-recovery contract model",
        enabledInProduction = true,
    ),
    AtomicWriteStrategyContractModel("atomic write strategy contract model", enabledInProduction = true),
    CrashRecoveryContractModel("crash-recovery contract model", enabledInProduction = true),
    StorageInterruptionTestContractModel("storage interruption-test contract model", enabledInProduction = true),
    InMemoryStorageAtomicityCrashSimulator(
        "still-disabled in-memory storage atomicity/crash simulator",
        enabledInProduction = false,
    ),
    StorageFailureModelContractModel("storage failure model contract", enabledInProduction = true),
    StorageNamespacePathHygieneContractModel(
        "storage namespace/path hygiene contract model",
        enabledInProduction = true,
    ),
    StorageNamespacePathPolicyBuildingBlock(
        "still-disabled storage namespace/path policy building block",
        enabledInProduction = false,
    ),
    StorageLayoutPlanBuildingBlock(
        "still-disabled rootless storage layout plan building block",
        enabledInProduction = false,
    ),
    PathContainmentPlannerBuildingBlock(
        "still-disabled path-containment planner building block",
        enabledInProduction = false,
    ),
    PlatformStorageRootContractModel(
        "platform storage root contract model",
        enabledInProduction = true,
    ),
    PlatformRootSettingsPolicyModel(
        "platform root settings policy model",
        enabledInProduction = true,
    ),
    AndroidAppPrivateRootPolicyModel(
        "Android app-private internal root policy model",
        enabledInProduction = true,
    ),
    LinuxRootSettingsPolicyModel(
        "Linux root settings policy model",
        enabledInProduction = true,
    ),
    OsKeyringPassphraseRejectionModel(
        "OS keyring passphrase storage rejection model",
        enabledInProduction = true,
    ),
    PasswordManagerIntegrationRejectionModel(
        "password-manager integration rejection model",
        enabledInProduction = true,
    ),
    PassphraseFirstDefaultModel(
        "passphrase-first vault authority model",
        enabledInProduction = true,
    ),
    SafePathConstructionContractModel(
        "safe path-construction contract model",
        enabledInProduction = true,
    ),
    SymlinkTraversalContractModel(
        "symlink and traversal contract model",
        enabledInProduction = true,
    ),
    StoragePermissionOwnershipContractModel(
        "storage permission and ownership contract model",
        enabledInProduction = true,
    ),
    DurabilityCapabilityContractModel(
        "durability capability contract model",
        enabledInProduction = true,
    ),
    DurabilityFailClosedPolicyModel(
        "durability fail-closed policy model",
        enabledInProduction = true,
    ),
    WarningOnlyDurabilityRejectionModel(
        "warning-only durability rejection model",
        enabledInProduction = true,
    ),
    RollbackLimitationAntiRollbackAnchorModel(
        "rollback limitation and anti-rollback anchor model",
        enabledInProduction = true,
    ),
    SecureStorageBoundaryContractModel(
        "secure storage boundary contract model",
        enabledInProduction = true,
    ),
    InMemoryVaultContainerParserWriterBuildingBlock(
        "still-disabled in-memory vault container parser/writer building block",
        enabledInProduction = false,
    ),
    InMemoryManifestParserWriterBuildingBlock(
        "still-disabled in-memory manifest parser/writer building block",
        enabledInProduction = false,
    ),
    LocalManifestStaleRecordDecisionPolicyBuildingBlock(
        "still-disabled local manifest-relative stale-record decision policy building block",
        enabledInProduction = false,
    ),
    ProviderSelectionBoundaryModel("provider selection boundary model", enabledInProduction = true),
    AndroidCompatibilityEntropyPolicyModel(
        "Android compatibility and entropy policy model",
        enabledInProduction = true,
    ),
    RuntimeRandomnessProviderCheckModel(
        "runtime randomness provider check model",
        enabledInProduction = true,
    ),
    ProductionProviderAcceptanceContractModel(
        "production provider acceptance contract model",
        enabledInProduction = true,
    ),
    VaultHeaderCommitmentPolicyModel("vault header commitment policy model", enabledInProduction = true),
    HkdfSha256KeyExpansionPolicyModel(
        "HKDF-SHA-256 key-expansion policy model",
        enabledInProduction = true,
    ),
    HmacSha256HeaderCommitmentPrimitivePolicyModel(
        "HMAC-SHA-256 header-commitment primitive policy model",
        enabledInProduction = true,
    ),
    KeyExpansionOutputLayoutPolicyModel("key-expansion output layout policy model", enabledInProduction = true),
    PrimitiveThreatModelRationaleModel("primitive threat model and rationale model", enabledInProduction = true),
    CanonicalHeaderByteVectorContractModel(
        "canonical header byte vector contract model",
        enabledInProduction = true,
    ),
    HkdfSha256VectorContractModel("HKDF-SHA-256 vector contract model", enabledInProduction = true),
    HmacSha256HeaderCommitmentVectorContractModel(
        "HMAC-SHA-256 header-commitment vector contract model",
        enabledInProduction = true,
    ),
    CanonicalHeaderEncodingPolicyModel(
        "canonical header encoding policy model",
        enabledInProduction = true,
    ),
    KeySeparationLabelsPolicyModel("key-separation labels policy model", enabledInProduction = true),
    CanonicalHeaderSerializerBuildingBlock(
        "still-disabled canonical header serializer building block",
        enabledInProduction = false,
    ),
    HkdfSha256KeyExpansionBuildingBlock(
        "still-disabled HKDF-SHA-256 key-expansion building block",
        enabledInProduction = false,
    ),
    HmacSha256HeaderCommitmentBuildingBlock(
        "still-disabled HMAC-SHA-256 header-commitment building block",
        enabledInProduction = false,
    ),
    StrictAadContractPolicyModel("strict AEAD associated-data contract policy model", enabledInProduction = true),
    StrictAadSerializationBuildingBlock(
        "still-disabled strict AEAD associated-data serialization building block",
        enabledInProduction = false,
    ),
    TinkRecordAeadBuildingBlock(
        "still-disabled Tink XChaCha20-Poly1305 record AEAD building block",
        enabledInProduction = false,
    ),
    TinkNonKeyCommitmentMitigationModel(
        "Tink non-key-commitment mitigation policy model",
        enabledInProduction = true,
    ),
    PassphraseEncodingPolicyModel("passphrase encoding policy model", enabledInProduction = true),
    PassphrasePolicyValidationBuildingBlock(
        "still-disabled passphrase policy validation building block",
        enabledInProduction = false,
    ),
    Argon2idPassphraseRootDerivationBuildingBlock(
        "still-disabled Argon2id passphrase-to-root-material building block",
        enabledInProduction = false,
    ),
    TinkRawKeyFeasibilityPolicyModel("Tink raw-key feasibility policy model", enabledInProduction = true),
    Argon2idBoundedCalibrationPolicyModel("bounded Argon2id calibration policy model", enabledInProduction = true),
    TestOnlyProviderKatHarnessModel("test-only provider KAT harness model", enabledInProduction = false),
    DisabledCryptoProviderBoundary("disabled crypto provider boundary", enabledInProduction = false),
    FutureEncryptedVaultImplementation("future encrypted vault implementation", enabledInProduction = false),
    FutureProductionSecretPersistence("future production secret persistence", enabledInProduction = false),
    FutureProductionMetadataPersistence("future production metadata persistence", enabledInProduction = false),
}

data class EncryptedVaultAlgorithmPolicy(
    val targetKdf: EncryptedVaultKdfAlgorithm,
    val fallbackKdfs: Set<EncryptedVaultKdfAlgorithm>,
    val rejectedDefaultKdfs: Set<EncryptedVaultKdfAlgorithm>,
    val preferredRecordAead: EncryptedVaultAeadAlgorithm,
    val fallbackAeads: Set<EncryptedVaultAeadAlgorithm>,
    val noncePolicy: EncryptedVaultNoncePolicy,
    val associatedDataRequirements: Set<EncryptedVaultAssociatedDataRequirement>,
    val keyHierarchyRequirements: Set<EncryptedVaultKeyHierarchyRequirement>,
    val designOnly: Boolean,
) {
    fun acceptsDefaultProductionKdf(algorithm: EncryptedVaultKdfAlgorithm): Boolean =
        algorithm == targetKdf &&
            algorithm !in rejectedDefaultKdfs &&
            algorithm.role == EncryptedVaultDecisionRole.DesignTarget &&
            !designOnly

    companion object {
        fun currentDesign(): EncryptedVaultAlgorithmPolicy =
            EncryptedVaultAlgorithmPolicy(
                targetKdf = EncryptedVaultKdfAlgorithm.Argon2id,
                fallbackKdfs = setOf(EncryptedVaultKdfAlgorithm.Scrypt),
                rejectedDefaultKdfs = setOf(EncryptedVaultKdfAlgorithm.Pbkdf2),
                preferredRecordAead = EncryptedVaultAeadAlgorithm.XChaCha20Poly1305,
                fallbackAeads = setOf(
                    EncryptedVaultAeadAlgorithm.ChaCha20Poly1305,
                    EncryptedVaultAeadAlgorithm.Aes256Gcm,
                    EncryptedVaultAeadAlgorithm.AesGcmSiv,
                ),
                noncePolicy = EncryptedVaultNoncePolicy.XChaCha20Random24ByteRecordNonce,
                associatedDataRequirements = EncryptedVaultAssociatedDataRequirement.entries.toSet(),
                keyHierarchyRequirements = EncryptedVaultKeyHierarchyRequirement.entries.toSet(),
                designOnly = true,
            )
    }
}

data class EncryptedVaultReadiness(
    val implementationStatus: EncryptedVaultImplementationStatus,
    val algorithmPolicy: EncryptedVaultAlgorithmPolicy,
    val argon2idCalibrationPolicy: Argon2idCalibrationPolicy,
    val androidCompatibilityPolicy: AndroidSupportedPlatformPolicy,
    val platformPolicies: Set<EncryptedVaultPlatformPolicy>,
    val requirementStatuses: Map<EncryptedVaultRequirement, EncryptedVaultRequirementStatus>,
    val blockers: Set<EncryptedVaultBlockingIssue>,
    val warnings: Set<EncryptedVaultWarning>,
    val capabilities: Set<EncryptedVaultCapability>,
    val implementationNote: String,
    val futureImplementationHint: String,
) {
    val readyForProductionPersistence: Boolean
        get() = implementationStatus.implementationAvailable &&
            blockers.isEmpty() &&
            requirementStatuses.values.all { it.satisfiedForProductionPersistence }

    val secureSecretStorageAvailable: Boolean
        get() = EncryptedVaultRequirement.SecureSecretStorageAvailable.isSatisfied()

    val secureMetadataStorageAvailable: Boolean
        get() = EncryptedVaultRequirement.SecureMetadataStorageAvailable.isSatisfied()

    val productionPersistenceEnabled: Boolean = false

    val mainnetEnabled: Boolean = false

    private fun EncryptedVaultRequirement.isSatisfied(): Boolean =
        requirementStatuses[this]?.satisfiedForProductionPersistence == true
}

data class EncryptedVaultReadinessDecision(
    val readiness: EncryptedVaultReadiness,
    val blockers: Set<EncryptedVaultBlockingIssue>,
    val warnings: Set<EncryptedVaultWarning>,
) {
    val canEnableProductionPersistence: Boolean
        get() = readiness.readyForProductionPersistence && blockers.isEmpty()
}

object EncryptedVaultReadinessPolicy {
    fun disabled(): EncryptedVaultReadiness = commonDisabledEncryptedVaultReadiness()

    fun evaluate(
        readiness: EncryptedVaultReadiness = commonDisabledEncryptedVaultReadiness(),
        secureStorageCapability: SecureStorageCapability = commonDisabledSecureStorageCapability(),
        secureMetadataCapability: SecureMetadataPersistenceCapability = commonDisabledSecureMetadataCapability(),
    ): EncryptedVaultReadinessDecision {
        val blockers = readiness.blockers.toMutableSet()
        val warnings = readiness.warnings.toMutableSet()

        if (!secureStorageCapability.status.availableForSecretMaterial ||
            !secureStorageCapability.canStoreSecrets ||
            !secureStorageCapability.canReadSecrets
        ) {
            blockers += EncryptedVaultBlockingIssue.SecureSecretStorageDisabled
        }
        if (!secureMetadataCapability.status.availableForSensitiveMetadata ||
            !secureMetadataCapability.canStoreMetadata ||
            !secureMetadataCapability.canReadMetadata
        ) {
            blockers += EncryptedVaultBlockingIssue.SecureMetadataStorageDisabled
        }
        if (!readiness.productionPersistenceEnabled) {
            blockers += EncryptedVaultBlockingIssue.ProductionPersistenceDisabled
        }
        if (!readiness.mainnetEnabled) {
            blockers += EncryptedVaultBlockingIssue.MainnetDisabled
        }
        if (readiness.algorithmPolicy.designOnly) {
            warnings += EncryptedVaultWarning.AlgorithmTargetsAreDesignOnly
        }

        return EncryptedVaultReadinessDecision(
            readiness = readiness,
            blockers = blockers,
            warnings = warnings,
        )
    }
}

fun commonDisabledEncryptedVaultReadiness(): EncryptedVaultReadiness {
    val requirementStatuses = buildMap {
        put(
            EncryptedVaultRequirement.DependencySelectionReviewed,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.DisabledProviderBoundaryModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.ProviderSelectionBoundaryModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.ProviderKatContractModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.ProviderLevelKatStrategyContractModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.RandomizedAeadBehavioralKatPolicyModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.IntegratedVerificationOrderKatPolicyModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.StillDisabledProviderIntegrationHarnessImplementedAndTested,
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
        )
        put(
            EncryptedVaultRequirement.ProviderLevelKatsExecutedInStillDisabledHarness,
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
        )
        put(
            EncryptedVaultRequirement.RandomizedAeadBehavioralKatsExecutedInStillDisabledHarness,
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
        )
        put(
            EncryptedVaultRequirement.IntegratedVerificationOrderKatsExecutedInStillDisabledHarness,
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
        )
        put(
            EncryptedVaultRequirement.StillDisabledProviderFacadeImplementedAndTested,
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
        )
        put(
            EncryptedVaultRequirement.StillDisabledProviderFacadeMetadataOnly,
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
        )
        put(
            EncryptedVaultRequirement.StillDisabledProviderFacadeOperationsDisabled,
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
        )
        put(
            EncryptedVaultRequirement.VaultContainerContractModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.ManifestContractModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.StaleRecordManifestPolicyModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.StoragePolicyContractModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.PlatformStorageBoundaryContractModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.AtomicityCrashRecoveryContractModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.AtomicWriteStrategyContractModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.CrashRecoveryContractModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.StorageInterruptionTestContractModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.StorageAtomicityCrashSimulatorImplementedAndTested,
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
        )
        put(
            EncryptedVaultRequirement.StorageFailureModelModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.StorageNamespacePathHygieneModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.StorageNamespacePathPolicyImplementedAndTested,
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
        )
        put(
            EncryptedVaultRequirement.StorageLayoutPlanImplementedAndTested,
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
        )
        put(
            EncryptedVaultRequirement.PathContainmentPlannerImplementedAndTested,
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
        )
        put(
            EncryptedVaultRequirement.PlatformStorageRootContractModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.PlatformRootSettingsPolicyModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.AndroidAppPrivateRootPolicyModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.LinuxRootSettingsPolicyModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.OsKeyringPassphraseStorageRejected,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.PasswordManagerIntegrationRejected,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.PassphraseFirstDefaultModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.SettingsUiAbsent,
            EncryptedVaultRequirementStatus.DisabledByPolicy,
        )
        put(
            EncryptedVaultRequirement.SettingsPersistenceAbsent,
            EncryptedVaultRequirementStatus.DisabledByPolicy,
        )
        put(
            EncryptedVaultRequirement.SafePathConstructionContractModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.SymlinkTraversalContractModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.StoragePermissionOwnershipContractModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.DurabilityCapabilityContractModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.DurabilityFailClosedPolicyModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.WarningOnlyDurabilityPersistenceRejected,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.SecureStorageBoundaryContractModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.RollbackLimitationAndAntiRollbackAnchorModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.KdfCalibrationPolicyModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.KdfCandidateParameterPolicyModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.Argon2idCalibrationPolicyImplementedAndTested,
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
        )
        put(
            EncryptedVaultRequirement.Argon2idCandidateSelectionPolicyImplementedAndTested,
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
        )
        put(
            EncryptedVaultRequirement.Argon2idMemoryFailureHandlingModeledAndTested,
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
        )
        put(
            EncryptedVaultRequirement.Argon2idStoredParameterNoDowngradeModeledAndTested,
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
        )
        put(
            EncryptedVaultRequirement.AndroidCompatibilityEntropyPolicyModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.RuntimeCryptoProviderChecksModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.RuntimeRandomnessProviderChecksModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.RuntimeEntropyChecksModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.VaultCreationFailClosedWarningModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.ProductionProviderAcceptanceContractModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.VaultHeaderCommitmentPolicyModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.HkdfSha256KeyExpansionPolicyModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.HmacSha256HeaderCommitmentPrimitiveModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.KeyExpansionOutputLayoutModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.PrimitiveThreatModelRationaleModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.CanonicalHeaderByteVectorContractModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.HkdfSha256VectorContractModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.HmacSha256HeaderCommitmentVectorContractModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.CanonicalHeaderEncodingPolicyModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.KeySeparationLabelsPolicyModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.CanonicalHeaderSerializerImplementedAndVectorTested,
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
        )
        put(
            EncryptedVaultRequirement.HkdfSha256KeyExpansionImplementedAndVectorTested,
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
        )
        put(
            EncryptedVaultRequirement.HmacSha256HeaderCommitmentImplementedAndVectorTested,
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
        )
        put(
            EncryptedVaultRequirement.StrictAadContractModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.StrictAadSerializationImplementedAndTested,
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
        )
        put(
            EncryptedVaultRequirement.TinkRecordAeadBuildingBlockImplementedAndTested,
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
        )
        put(
            EncryptedVaultRequirement.TinkNonKeyCommitmentMitigationModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.PassphraseEncodingPolicyModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.PassphrasePolicyValidationImplementedAndTested,
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
        )
        put(
            EncryptedVaultRequirement.Argon2idPassphraseRootDerivationImplementedAndTested,
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
        )
        put(
            EncryptedVaultRequirement.TinkRawKeyFeasibilityPolicyModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(
            EncryptedVaultRequirement.Argon2idBoundedCalibrationPolicyModeled,
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
        )
        put(EncryptedVaultRequirement.KdfParametersCalibrated, EncryptedVaultRequirementStatus.Unresolved)
        put(EncryptedVaultRequirement.AeadImplementationVerified, EncryptedVaultRequirementStatus.Unresolved)
        put(EncryptedVaultRequirement.ProviderBoundaryKnownAnswerVectorsPassed, EncryptedVaultRequirementStatus.Absent)
        put(EncryptedVaultRequirement.KnownAnswerVectorsIdentified, EncryptedVaultRequirementStatus.Unresolved)
        put(
            EncryptedVaultRequirement.VaultContainerFormatImplemented,
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
        )
        put(
            EncryptedVaultRequirement.VaultContainerParserImplemented,
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
        )
        put(
            EncryptedVaultRequirement.VaultContainerWriterImplemented,
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
        )
        put(
            EncryptedVaultRequirement.ManifestParserImplemented,
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
        )
        put(
            EncryptedVaultRequirement.ManifestWriterImplemented,
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
        )
        put(
            EncryptedVaultRequirement.StaleRecordDecisionPolicyImplemented,
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
        )
        put(EncryptedVaultRequirement.LockSessionLifecycleTested, EncryptedVaultRequirementStatus.Absent)
        put(EncryptedVaultRequirement.RedactionTestsPassed, EncryptedVaultRequirementStatus.Absent)
        put(EncryptedVaultRequirement.MigrationAndCorruptionTestsPassed, EncryptedVaultRequirementStatus.Absent)
        put(EncryptedVaultRequirement.SecureSecretStorageAvailable, EncryptedVaultRequirementStatus.DisabledByPolicy)
        put(EncryptedVaultRequirement.SecureMetadataStorageAvailable, EncryptedVaultRequirementStatus.DisabledByPolicy)
        put(EncryptedVaultRequirement.ProductionPersistenceApproved, EncryptedVaultRequirementStatus.DisabledByPolicy)
        put(EncryptedVaultRequirement.MainnetReleaseApproved, EncryptedVaultRequirementStatus.DisabledByPolicy)
    }
    return EncryptedVaultReadiness(
        implementationStatus = EncryptedVaultImplementationStatus.NotImplemented,
        algorithmPolicy = EncryptedVaultAlgorithmPolicy.currentDesign(),
        argon2idCalibrationPolicy = commonArgon2idCalibrationPolicy(),
        androidCompatibilityPolicy = commonAndroidVaultCompatibilityPolicy(),
        platformPolicies = setOf(androidEncryptedVaultPlatformPolicy(), linuxDesktopEncryptedVaultPlatformPolicy()),
        requirementStatuses = requirementStatuses,
        blockers = setOf(
            EncryptedVaultBlockingIssue.VaultImplementationUnavailable,
            EncryptedVaultBlockingIssue.CryptoDependenciesNotSelected,
            EncryptedVaultBlockingIssue.ProductionProviderImplementationUnavailable,
            EncryptedVaultBlockingIssue.ProviderSelectionProductionBlocked,
            EncryptedVaultBlockingIssue.ProductionProviderAcceptanceContractIncomplete,
            EncryptedVaultBlockingIssue.StillDisabledProviderIntegrationHarnessNotSelectable,
            EncryptedVaultBlockingIssue.StillDisabledProviderFacadeNotSelectable,
            EncryptedVaultBlockingIssue.StaleRecordManifestIntegrationMissing,
            EncryptedVaultBlockingIssue.VaultContainerPersistenceImplementationMissing,
            EncryptedVaultBlockingIssue.ManifestReadWriteImplementationMissing,
            EncryptedVaultBlockingIssue.StorageSuccessPathAbsent,
            EncryptedVaultBlockingIssue.PlatformStorageImplementationMissing,
            EncryptedVaultBlockingIssue.AtomicWriteImplementationMissing,
            EncryptedVaultBlockingIssue.CrashRecoveryImplementationMissing,
            EncryptedVaultBlockingIssue.StorageInterruptionTestsMissing,
            EncryptedVaultBlockingIssue.StorageFailureRuntimeMappingMissing,
            EncryptedVaultBlockingIssue.StoragePathConstructionImplementationMissing,
            EncryptedVaultBlockingIssue.PlatformStorageRootImplementationMissing,
            EncryptedVaultBlockingIssue.PlatformRootSettingsImplementationMissing,
            EncryptedVaultBlockingIssue.SettingsUiMissing,
            EncryptedVaultBlockingIssue.SettingsPersistenceMissing,
            EncryptedVaultBlockingIssue.LinuxCustomRootValidationMissing,
            EncryptedVaultBlockingIssue.SafePathConstructionImplementationMissing,
            EncryptedVaultBlockingIssue.SymlinkTraversalImplementationMissing,
            EncryptedVaultBlockingIssue.StoragePermissionOwnershipImplementationMissing,
            EncryptedVaultBlockingIssue.DurabilityCapabilityProbeImplementationMissing,
            EncryptedVaultBlockingIssue.DurabilityFailClosedRuntimeEvidenceMissing,
            EncryptedVaultBlockingIssue.WarningOnlyDurabilityPersistenceRejected,
            EncryptedVaultBlockingIssue.TinkRawKeyFeasibilityProbeOnly,
            EncryptedVaultBlockingIssue.Argon2idBoundedCalibrationUnapproved,
            EncryptedVaultBlockingIssue.KdfParametersUncalibrated,
            EncryptedVaultBlockingIssue.AeadDependencyUnverified,
            EncryptedVaultBlockingIssue.AntiRollbackAnchorAbsentNoFullRollbackClaim,
            EncryptedVaultBlockingIssue.ManifestStorageAtomicityReviewMissing,
            EncryptedVaultBlockingIssue.KnownAnswerVectorsMissing,
            EncryptedVaultBlockingIssue.LockSessionLifecycleUntested,
            EncryptedVaultBlockingIssue.RedactionTestsMissing,
            EncryptedVaultBlockingIssue.MigrationAndCorruptionTestsMissing,
            EncryptedVaultBlockingIssue.SecureSecretStorageDisabled,
            EncryptedVaultBlockingIssue.SecureMetadataStorageDisabled,
            EncryptedVaultBlockingIssue.ProductionPersistenceDisabled,
            EncryptedVaultBlockingIssue.MainnetDisabled,
        ),
        warnings = setOf(
            EncryptedVaultWarning.ReadinessOnlyNoEncryption,
            EncryptedVaultWarning.AlgorithmTargetsAreDesignOnly,
            EncryptedVaultWarning.KdfCalibrationProbeOnly,
            EncryptedVaultWarning.KdfCandidateParameterPolicyNotFinal,
            EncryptedVaultWarning.OsKeyringsNotPrimaryStorage,
            EncryptedVaultWarning.OsKeyringPassphraseStorageRejected,
            EncryptedVaultWarning.PasswordManagerIntegrationRejected,
            EncryptedVaultWarning.AndroidWrappingOptional,
            EncryptedVaultWarning.LinuxPassphraseFirst,
            EncryptedVaultWarning.TorRoutingMetadataSensitive,
            EncryptedVaultWarning.MemoryClearingBestEffort,
        ),
        capabilities = EncryptedVaultCapability.entries.toSet(),
        implementationNote = "Encrypted vault readiness, Android compatibility/entropy policy, runtime randomness provider checks, a v1 production-provider acceptance contract, header commitment, HKDF-SHA-256 key-expansion policy, HMAC-SHA-256 header-commitment primitive policy, canonical header vector contract, HKDF/HMAC test-vector contracts, canonical header encoding, key-separation labels, strict AAD, provider-level KAT strategy, randomized AEAD behavioral KAT policy, integrated verification-order KAT policy, vault container contract, manifest contract, stale-record policy, platform storage boundary, atomic write strategy, crash-recovery contract, storage interruption-test contract, storage failure model, storage namespace/path hygiene, platform storage-root contract, platform root settings policy, safe path-construction contract, symlink/traversal contract, storage permission/ownership contract, durability capability contract, durability fail-closed policy, warning-only durability rejection, secure-storage boundary contract, rollback limitation/anti-rollback anchor status, a disabled provider boundary, a still-disabled provider facade boundary, and a provider-selection boundary are modeled. The passphrase policy validation, explicit-parameter Bouncy Castle Argon2id root derivation, Argon2id calibration floor/candidate-selection/memory-failure/no-downgrade policy, canonical header serializer, HKDF-SHA-256 key expansion, HMAC-SHA-256 header-commitment, strict AAD serialization, Tink XChaCha20-Poly1305 record AEAD, in-memory vault container parser/writer, in-memory manifest parser/writer, local manifest-relative stale-record decision, in-memory storage atomicity/crash simulator, storage namespace/path policy, rootless logical storage layout plan, and path-containment planner building blocks now exist and match fixed non-secret tests where applicable. A still-disabled provider integration harness composes those building blocks and executes provider-level deterministic vectors plus randomized AEAD behavioral KATs in the required verification order, and a still-disabled facade exposes only metadata/status and typed disabled operation results, but the vault is not implemented. Provider selection returns only the disabled provider. No vault creation, unlock, manifest storage read/write, vault storage, filesystem/database/platform settings persistence, vault storage-root Settings UI, settings persistence, OS keyring passphrase storage, password-manager integration, actual path construction, path joining, directory creation, platform root selection, real path containment checks, symlink checks, permission checks, durability probes, warning-only encrypted vault persistence path, atomic write/recovery implementation, key generation, secure storage success path, metadata persistence, anti-rollback anchor, or provider-selectable record AEAD path is enabled.",
        futureImplementationHint = "The Tink plus Bouncy Castle split stack has candidate-level dependency, license, keyset/storage, split-provider review evidence, desktop and Android test-scope Tink raw-key public API feasibility evidence, a disabled Skald-owned provider boundary, a still-disabled metadata-only provider facade, provider-level KAT strategy evidence for deterministic vectors plus randomized AEAD behavioral checks, a still-disabled verification-order KAT harness requiring header commitment before record decrypt, implemented-still-disabled in-memory container parser/writer evidence, implemented-still-disabled in-memory manifest parser/writer and local manifest-relative stale-record decision evidence, implemented-still-disabled in-memory storage atomicity/crash simulator evidence, implemented-still-disabled storage namespace/path policy evidence, implemented-still-disabled rootless logical storage layout plan evidence, implemented-still-disabled path-containment planner evidence, model-only platform storage boundary, platform storage-root, platform root settings, safe path-construction, symlink/traversal, permission/ownership, durability capability, durability fail-closed and warning-only rejection policies, atomic write strategy, crash recovery, interruption-test, storage failure model, secure-storage boundary, and rollback-limitation contracts, an implemented-still-disabled Argon2id calibration floor and candidate-selection policy, memory/execution fail-closed handling, no stored-parameter downgrade model, a manual Android calibration evidence-capture model, a supported Android compatibility/entropy policy, a runtime randomness provider check model, a v1 production-provider acceptance contract, header commitment policy, HKDF-SHA-256 key-expansion policy, HMAC-SHA-256 header-commitment primitive policy, key-expansion output layout policy, canonical header/HKDF/HMAC vectors matched by still-disabled building blocks, canonical header encoding policy, key-separation labels policy, passphrase validation and explicit Argon2id root derivation building blocks, strict AAD serialization and Tink record AEAD building blocks, and a disabled provider-selection boundary. Production implementation remains blocked until final production KDF parameter approval, supported-platform runtime provider and randomness checks, selectable production provider implementation, production provider-boundary KAT validation on desktop and Android, manifest-backed storage integration, platform storage implementation, actual path construction and platform root review, Settings UI/persistence and Linux custom-root validation review where custom roots are used, real path containment checks, symlink/permission/durability implementation review, durability proof that is not warning-only or user-consent-overridable, atomic write and crash recovery implementation, interruption/corruption tests, secure storage boundaries, lock/session lifecycle, redaction, migration, storage, and release-hardening reviews pass.",
    )
}

fun androidEncryptedVaultPlatformPolicy(): EncryptedVaultPlatformPolicy =
    EncryptedVaultPlatformPolicy(
        platform = EncryptedVaultPlatform.Android,
        appControlledVaultIsPrimary = true,
        passphraseUnlockRequired = true,
        platformWrappingRole = EncryptedVaultPlatformWrappingRole.OptionalWrappingHelperAfterReview,
        hardwareBackedWrappingOptional = true,
        osKeyringPrimaryStorageAllowed = false,
        implementationNote = "Android OS cryptographic randomness or reviewed provider randomness is required for vault material. Keystore or StrongBox wrapping may be evaluated later as optional key protection, but the app-controlled vault/session-lock model remains primary.",
    )

fun linuxDesktopEncryptedVaultPlatformPolicy(): EncryptedVaultPlatformPolicy =
    EncryptedVaultPlatformPolicy(
        platform = EncryptedVaultPlatform.LinuxDesktop,
        appControlledVaultIsPrimary = true,
        passphraseUnlockRequired = true,
        platformWrappingRole = EncryptedVaultPlatformWrappingRole.NotPrimaryStorage,
        hardwareBackedWrappingOptional = false,
        osKeyringPrimaryStorageAllowed = false,
        implementationNote = "Linux v1 is passphrase-first. libsecret and KWallet are not primary storage and are not enabled as wrapping helpers.",
    )
