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
    BindContainerVersion("bind container version"),
    BindVaultId("bind opaque vault id"),
    BindRecordId("bind opaque record id"),
    BindRecordClass("bind record class"),
    BindRecordSchemaVersion("bind record schema version"),
    BindKeyVersion("bind key version"),
    ExcludeSensitiveWalletMetadata("exclude sensitive wallet metadata from associated data"),
}

enum class EncryptedVaultKeyHierarchyRequirement(val label: String) {
    UserUnlockSecretRequired("user unlock secret required"),
    PassphraseDerivedKek("passphrase-derived key-encryption key"),
    RandomVaultRootKey("random vault root key"),
    VaultRootKeyNotUsedDirectlyForRecords("vault root key is not used directly for record AEAD"),
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
    KdfParametersCalibrated("KDF parameters calibrated"),
    AeadImplementationVerified("AEAD implementation verified"),
    KnownAnswerVectorsIdentified("known-answer vectors identified"),
    VaultContainerFormatImplemented("vault container format implemented"),
    VaultContainerParserImplemented("vault container parser implemented"),
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
    Absent("absent", satisfiedForProductionPersistence = false),
    DisabledByPolicy("disabled by policy", satisfiedForProductionPersistence = false),
}

enum class EncryptedVaultBlockingIssue(val label: String) {
    VaultImplementationUnavailable("vault implementation unavailable"),
    CryptoDependenciesNotSelected("crypto dependencies not selected for implementation"),
    KdfParametersUncalibrated("KDF parameters uncalibrated"),
    AeadDependencyUnverified("AEAD dependency unverified"),
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
    OsKeyringsNotPrimaryStorage("OS keyrings are not primary storage"),
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
        put(EncryptedVaultRequirement.DependencySelectionReviewed, EncryptedVaultRequirementStatus.Unresolved)
        put(EncryptedVaultRequirement.KdfParametersCalibrated, EncryptedVaultRequirementStatus.Unresolved)
        put(EncryptedVaultRequirement.AeadImplementationVerified, EncryptedVaultRequirementStatus.Unresolved)
        put(EncryptedVaultRequirement.KnownAnswerVectorsIdentified, EncryptedVaultRequirementStatus.Unresolved)
        put(EncryptedVaultRequirement.VaultContainerFormatImplemented, EncryptedVaultRequirementStatus.Absent)
        put(EncryptedVaultRequirement.VaultContainerParserImplemented, EncryptedVaultRequirementStatus.Absent)
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
        platformPolicies = setOf(androidEncryptedVaultPlatformPolicy(), linuxDesktopEncryptedVaultPlatformPolicy()),
        requirementStatuses = requirementStatuses,
        blockers = setOf(
            EncryptedVaultBlockingIssue.VaultImplementationUnavailable,
            EncryptedVaultBlockingIssue.CryptoDependenciesNotSelected,
            EncryptedVaultBlockingIssue.KdfParametersUncalibrated,
            EncryptedVaultBlockingIssue.AeadDependencyUnverified,
            EncryptedVaultBlockingIssue.KnownAnswerVectorsMissing,
            EncryptedVaultBlockingIssue.VaultContainerFormatAbsent,
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
            EncryptedVaultWarning.OsKeyringsNotPrimaryStorage,
            EncryptedVaultWarning.AndroidWrappingOptional,
            EncryptedVaultWarning.LinuxPassphraseFirst,
            EncryptedVaultWarning.TorRoutingMetadataSensitive,
            EncryptedVaultWarning.MemoryClearingBestEffort,
        ),
        capabilities = EncryptedVaultCapability.entries.toSet(),
        implementationNote = "Encrypted vault readiness is modeled but the vault is not implemented. No keys are generated, no crypto is performed, and no data is persisted.",
        futureImplementationHint = "Resolve dependency selection, KDF calibration, AEAD verification, known-answer vectors, container format, lock/session lifecycle, redaction, and migration tests before enabling any persistence.",
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
        implementationNote = "Android Keystore wrapping may be evaluated later, but the app-controlled vault/session-lock model remains primary.",
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
