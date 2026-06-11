package com.libertasprimordium.skald.security

interface SkaldVaultV1RuntimeRandomnessAuthorizationBoundary {
    fun evaluate(
        request: SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest,
    ): SkaldVaultV1VaultRuntimeRandomnessAuthorizationResult<
        SkaldVaultV1VaultRuntimeRandomnessAuthorizationEvidence,
    >
}

enum class SkaldVaultV1VaultRuntimeRandomnessAuthorizationSource(val label: String) {
    NoEvidence("no runtime randomness authorization evidence"),
    PolicySummary("runtime randomness authorization policy summary"),
    OperationKind("runtime randomness operation kind"),
    Purpose("runtime randomness purpose"),
    SourceKind("runtime randomness source kind"),
    RequiredGate("runtime randomness required gate"),
    ComposedTypedEvidence("composed typed runtime randomness evidence"),
    RawRandomnessCandidate("raw randomness candidate"),
}

enum class SkaldVaultV1VaultRuntimeRandomnessAuthorizationStatus(val label: String) {
    NoEvidenceAvailable("no runtime randomness authorization evidence available"),
    PolicySummaryModeled("runtime randomness authorization policy summary modeled"),
    OperationKindModeled("runtime randomness operation kind modeled"),
    PurposeModeled("runtime randomness purpose modeled"),
    SourceKindModeled("runtime randomness source kind modeled"),
    RequiredGateModeled("runtime randomness required gate modeled"),
    RuntimeRandomnessBlockedStillDisabled("runtime randomness authorization remains blocked and still disabled"),
    RawCandidateRejected("raw runtime randomness candidate rejected"),
}

enum class SkaldVaultV1VaultRuntimeRandomnessAuthorizationDecision(
    val randomnessOperationAllowed: Boolean,
    val runtimeRandomnessAllowed: Boolean,
    val osCryptographicRandomnessAllowed: Boolean,
    val providerRandomnessAllowed: Boolean,
    val saltNonceKeyMaterialAllowed: Boolean,
    val providerOperationAllowed: Boolean,
    val unlockAllowed: Boolean,
    val persistenceAllowed: Boolean,
) {
    BlockedFailClosed(
        randomnessOperationAllowed = false,
        runtimeRandomnessAllowed = false,
        osCryptographicRandomnessAllowed = false,
        providerRandomnessAllowed = false,
        saltNonceKeyMaterialAllowed = false,
        providerOperationAllowed = false,
        unlockAllowed = false,
        persistenceAllowed = false,
    ),
    Unauthorized(
        randomnessOperationAllowed = false,
        runtimeRandomnessAllowed = false,
        osCryptographicRandomnessAllowed = false,
        providerRandomnessAllowed = false,
        saltNonceKeyMaterialAllowed = false,
        providerOperationAllowed = false,
        unlockAllowed = false,
        persistenceAllowed = false,
    ),
    ForbiddenSourceRejected(
        randomnessOperationAllowed = false,
        runtimeRandomnessAllowed = false,
        osCryptographicRandomnessAllowed = false,
        providerRandomnessAllowed = false,
        saltNonceKeyMaterialAllowed = false,
        providerOperationAllowed = false,
        unlockAllowed = false,
        persistenceAllowed = false,
    ),
    TestOnlyScopeRejectedForProduction(
        randomnessOperationAllowed = false,
        runtimeRandomnessAllowed = false,
        osCryptographicRandomnessAllowed = false,
        providerRandomnessAllowed = false,
        saltNonceKeyMaterialAllowed = false,
        providerOperationAllowed = false,
        unlockAllowed = false,
        persistenceAllowed = false,
    ),
    UnsupportedFailClosed(
        randomnessOperationAllowed = false,
        runtimeRandomnessAllowed = false,
        osCryptographicRandomnessAllowed = false,
        providerRandomnessAllowed = false,
        saltNonceKeyMaterialAllowed = false,
        providerOperationAllowed = false,
        unlockAllowed = false,
        persistenceAllowed = false,
    ),
}

enum class SkaldVaultV1VaultRandomnessOperationKind(val label: String) {
    RuntimeRandomnessAvailabilityCheck("runtime randomness availability check"),
    OsCryptographicRandomnessRequest("OS cryptographic randomness request"),
    ProviderRandomnessRequest("provider randomness request"),
    HardwareBackedEntropyEvidenceRequest("hardware-backed entropy evidence request"),
    SaltGeneration("salt generation"),
    NonceGeneration("nonce generation"),
    KeyGenerationEntropyRequest("key-generation entropy request"),
    KdfSaltRequest("KDF salt request"),
    AeadNonceRequest("AEAD nonce request"),
    RecordNonceCounterSeedRequest("record nonce/counter seed request"),
    ManifestNonceCounterSeedRequest("manifest nonce/counter seed request"),
    StorageIndexNonceCounterSeedRequest("storage-index nonce/counter seed request"),
    BackupExportNonceSaltRequest("backup/export nonce/salt request"),
    MigrationNonceSaltRequest("migration nonce/salt request"),
    ProviderKatRandomnessRequest("provider KAT randomness request"),
    DeterministicTestVectorRandomnessRequest("deterministic test-vector randomness request"),
    ProductionRuntimeRandomnessRequest("production runtime randomness request"),
    ReleaseValidationRandomnessRequest("release validation randomness request"),
    MainnetRandomnessRequest("mainnet randomness request"),
}

enum class SkaldVaultV1VaultRandomnessPurpose(val label: String) {
    CreateVault("create vault"),
    UnlockVault("unlock vault"),
    DeriveVaultKeys("derive vault keys"),
    GenerateSalt("generate salt"),
    GenerateAeadNonce("generate AEAD nonce"),
    GenerateRecordNonce("generate record nonce"),
    GenerateMetadataNonce("generate metadata nonce"),
    GenerateStorageIndexNonce("generate storage-index nonce"),
    GenerateManifestNonce("generate manifest nonce"),
    GenerateKeyWrappingMaterial("generate key-wrapping material"),
    GenerateBackupExportMaterial("generate backup/export material"),
    RunProviderKat("run provider KAT"),
    RunRuntimeHealthCheck("run runtime health check"),
    VerifyEntropySourceAvailability("verify entropy source availability"),
    MigrationRecoveryPlanning("migration/recovery planning"),
    TestOnlyDeterministicVector("test-only deterministic vector"),
    ProductionRuntime("production runtime"),
    ReleaseValidation("release validation"),
    MainnetValidation("mainnet validation"),
}

enum class SkaldVaultV1VaultRandomnessSourceKind(
    val label: String,
    val forbidden: Boolean,
    val platformReviewRequired: Boolean,
    val providerReviewRequired: Boolean,
    val deterministicTestOnly: Boolean,
) {
    OsCryptographicRandomnessCsprng(
        label = "OS cryptographic randomness/CSPRNG",
        forbidden = false,
        platformReviewRequired = true,
        providerReviewRequired = false,
        deterministicTestOnly = false,
    ),
    ProviderOwnedCryptographicRandomness(
        label = "provider-owned cryptographic randomness",
        forbidden = false,
        platformReviewRequired = false,
        providerReviewRequired = true,
        deterministicTestOnly = false,
    ),
    HardwareBackedEntropyEvidence(
        label = "hardware-backed entropy evidence",
        forbidden = false,
        platformReviewRequired = true,
        providerReviewRequired = false,
        deterministicTestOnly = false,
    ),
    AndroidOsCsprngEvidence(
        label = "Android OS CSPRNG evidence",
        forbidden = false,
        platformReviewRequired = true,
        providerReviewRequired = false,
        deterministicTestOnly = false,
    ),
    AndroidHardwareBackedCapabilityEvidence(
        label = "Android hardware-backed capability evidence",
        forbidden = false,
        platformReviewRequired = true,
        providerReviewRequired = false,
        deterministicTestOnly = false,
    ),
    LinuxOsCsprngEvidence(
        label = "Linux OS CSPRNG evidence",
        forbidden = false,
        platformReviewRequired = true,
        providerReviewRequired = false,
        deterministicTestOnly = false,
    ),
    LinuxHardwareRngEvidence(
        label = "Linux hardware RNG evidence",
        forbidden = false,
        platformReviewRequired = true,
        providerReviewRequired = false,
        deterministicTestOnly = false,
    ),
    DeterministicTestVectorSource(
        label = "deterministic test-vector source",
        forbidden = false,
        platformReviewRequired = false,
        providerReviewRequired = false,
        deterministicTestOnly = true,
    ),
    ForbiddenKotlinRandom(
        label = "forbidden Kotlin general-purpose random",
        forbidden = true,
        platformReviewRequired = false,
        providerReviewRequired = false,
        deterministicTestOnly = false,
    ),
    ForbiddenJavaRandom(
        label = "forbidden Java general-purpose random",
        forbidden = true,
        platformReviewRequired = false,
        providerReviewRequired = false,
        deterministicTestOnly = false,
    ),
    ForbiddenTimeBasedRandomness(
        label = "forbidden time-based randomness",
        forbidden = true,
        platformReviewRequired = false,
        providerReviewRequired = false,
        deterministicTestOnly = false,
    ),
    ForbiddenMathRandom(
        label = "forbidden math-library random",
        forbidden = true,
        platformReviewRequired = false,
        providerReviewRequired = false,
        deterministicTestOnly = false,
    ),
    ForbiddenUserSuppliedRandomness(
        label = "forbidden user-supplied randomness",
        forbidden = true,
        platformReviewRequired = false,
        providerReviewRequired = false,
        deterministicTestOnly = false,
    ),
    ForbiddenNetworkSuppliedRandomness(
        label = "forbidden network-supplied randomness",
        forbidden = true,
        platformReviewRequired = false,
        providerReviewRequired = false,
        deterministicTestOnly = false,
    ),
    ForbiddenPersistedRandomness(
        label = "forbidden persisted randomness",
        forbidden = true,
        platformReviewRequired = false,
        providerReviewRequired = false,
        deterministicTestOnly = false,
    ),
    UnknownSource(
        label = "unknown source",
        forbidden = true,
        platformReviewRequired = true,
        providerReviewRequired = false,
        deterministicTestOnly = false,
    ),
    UnsupportedSource(
        label = "unsupported source",
        forbidden = true,
        platformReviewRequired = true,
        providerReviewRequired = false,
        deterministicTestOnly = false,
    ),
}

enum class SkaldVaultV1VaultRandomnessRequiredGate(val label: String) {
    SourceIsNotForbidden("source is not forbidden"),
    SourceIsOsCsprngOrReviewedProviderRandomness("source is OS CSPRNG or reviewed provider randomness"),
    SourceIsNotKotlinRandom("source is not Kotlin Random"),
    SourceIsNotJavaRandom("source is not Java Random"),
    SourceIsNotMathRandom("source is not math-library random"),
    SourceIsNotTimestampTimeBased("source is not timestamp/time-based"),
    SourceIsNotUserSupplied("source is not user supplied"),
    SourceIsNotNetworkSupplied("source is not network supplied"),
    SourceIsNotPersistedOrReused("source is not persisted/reused randomness"),
    SourceReviewCompletedForPlatform("source review completed for platform"),
    LinuxEntropyReviewCompletedWhereLinuxRuntimeIsUsed(
        "Linux entropy review completed where Linux runtime is used",
    ),
    AndroidEntropyReviewCompletedWhereAndroidRuntimeIsUsed(
        "Android entropy review completed where Android runtime is used",
    ),
    ProviderOperationAuthorizationApproved("provider operation authorization approved"),
    ProviderSelectableWhereProviderRandomnessIsUsed("provider selectable where provider randomness is used"),
    ProviderKatsApprovedWhereProviderRandomnessIsUsed("provider KATs approved where provider randomness is used"),
    RuntimeRandomnessHealthCheckApproved("runtime randomness health check approved"),
    RedactionLeakagePolicyApproved("redaction/leakage policy approved"),
    ClearWipePolicyApprovedWhereBuffersAreInvolved("clear/wipe policy approved where buffers are involved"),
    PassphrasePolicyApprovedWhereKdfSaltIsUsedForUnlock(
        "passphrase policy approved where KDF salt is used for unlock",
    ),
    LockSessionApprovedWhereUnlockSessionUsesRandomness(
        "lock/session approved where unlock/session uses randomness",
    ),
    PersistenceReadinessApprovedWhereStorageUsesRandomness(
        "persistence readiness approved where storage uses randomness",
    ),
    StorageSafetyApprovedWhereStorageUsesRandomness(
        "storage safety approved where storage uses randomness",
    ),
    MigrationCorruptionPolicyApprovedWhereMigrationRecoveryUsesRandomness(
        "migration/corruption policy approved where migration/recovery uses randomness",
    ),
    NoRawRandomEntropyBytesInDiagnostics("no raw random/entropy bytes in diagnostics"),
    NoDeterministicTestVectorSourceInProductionRuntime(
        "no deterministic test-vector source in production runtime",
    ),
    MainnetReleaseReviewApproved("mainnet remains disabled unless release review approves it"),
}

enum class SkaldVaultV1VaultRandomnessBlocker(val label: String) {
    RuntimeRandomnessAuthorizationStillDisabled("runtime randomness authorization remains still-disabled"),
    NoEvidenceAvailable("no runtime randomness authorization evidence available"),
    RuntimeRandomnessHealthCheckMissing("runtime randomness health check missing"),
    PlatformRandomnessSourceReviewMissing("platform randomness source review missing"),
    LinuxEntropyReviewMissing("Linux entropy review missing"),
    AndroidEntropyReviewMissing("Android entropy review missing"),
    SourceForbidden("randomness source is forbidden"),
    SourceUnknownOrUnsupported("randomness source is unknown or unsupported"),
    GeneralPurposeRandomRejected("general-purpose random APIs are rejected"),
    TimeBasedRandomnessRejected("time-based randomness is rejected"),
    UserSuppliedRandomnessRejected("user-supplied randomness is rejected"),
    NetworkSuppliedRandomnessRejected("network-supplied randomness is rejected"),
    PersistedRandomnessRejected("persisted or reused randomness is rejected"),
    DeterministicTestVectorRejectedForProduction("deterministic test-vector source rejected for production"),
    ProviderOperationAuthorizationBlocked("provider operation authorization remains blocked"),
    ProviderSelectionDisabled("provider selection disabled"),
    DisabledProviderSelected("registry selects only the disabled provider"),
    ProductionProviderSelectableFalse("productionProviderSelectable remains false"),
    ProviderKatApprovalMissing("provider KAT approval missing"),
    ProviderRandomnessUnavailable("provider randomness unavailable"),
    OsCryptographicRandomnessUnavailable("OS cryptographic randomness unavailable"),
    HardwareBackedEntropyUnavailable("hardware-backed entropy unavailable"),
    SaltGenerationUnavailable("salt generation unavailable"),
    NonceGenerationUnavailable("nonce generation unavailable"),
    KeyGenerationEntropyUnavailable("key-generation entropy unavailable"),
    PassphrasePolicyBlocked("passphrase policy blocked"),
    LockSessionLifecycleBlocked("lock/session lifecycle blocked"),
    PersistenceReadinessBlocked("persistence readiness blocked"),
    StorageSafetyPreflightBlocked("storage safety preflight blocked"),
    DisabledStorageServiceUnavailable("disabled storage service unavailable"),
    MigrationCorruptionBoundaryDisabled("migration/corruption boundary disabled"),
    ClearWipeStrategyBlocked("clear/wipe strategy blocked"),
    RedactionLeakageUnsafe("redaction/leakage unsafe for diagnostics"),
    SecureSecretStorageUnavailable("secure secret storage unavailable"),
    SecureMetadataStorageUnavailable("secure metadata storage unavailable"),
    WarningOnlyEvidenceRejected("warning-only evidence cannot authorize randomness"),
    UserConsentOverrideRejected("user consent cannot override missing hard gates"),
    MainnetUnavailable("mainnet remains unavailable"),
    RawRandomnessCandidateRejected("raw randomness candidate rejected"),
}

enum class SkaldVaultV1VaultRandomnessWarning(val label: String) {
    EvidenceOnly("runtime randomness authorization result is evidence only"),
    NoRandomnessApiCalled("no randomness API is called"),
    NoEntropySaltNonceOrKeyGenerated("no entropy, salts, nonces, or keys are generated"),
    ProviderOperationsUnauthorized("provider operations remain unauthorized"),
    PlatformSourcesUnreviewed("platform randomness sources remain unreviewed"),
    HardwareBackedKeyProtectionSeparateFromEntropy(
        "hardware-backed key protection is separate from entropy quality",
    ),
    GeneralPurposeRandomForbidden("general-purpose random sources are forbidden for vault material"),
    FutureImplementationRequiresReview("future runtime randomness implementation requires review"),
    RedactedDiagnosticsOnly("diagnostics must remain redacted"),
}

data class SkaldVaultV1VaultRandomnessCapability(
    val randomnessOperationAuthorized: Boolean,
    val runtimeRandomnessAvailable: Boolean,
    val osCryptographicRandomnessAvailable: Boolean,
    val providerRandomnessAvailable: Boolean,
    val hardwareBackedEntropyAvailable: Boolean,
    val androidOsCsprngAvailable: Boolean,
    val androidHardwareBackedEntropyAvailable: Boolean,
    val linuxOsCsprngAvailable: Boolean,
    val linuxHardwareEntropyAvailable: Boolean,
    val saltGenerationAvailable: Boolean,
    val nonceGenerationAvailable: Boolean,
    val keyGenerationEntropyAvailable: Boolean,
    val kdfSaltAvailable: Boolean,
    val aeadNonceAvailable: Boolean,
    val recordNonceAvailable: Boolean,
    val metadataNonceAvailable: Boolean,
    val manifestNonceAvailable: Boolean,
    val storageIndexNonceAvailable: Boolean,
    val deterministicTestVectorRandomnessAvailable: Boolean,
    val productionRuntimeRandomnessAvailable: Boolean,
    val providerOperationAuthorized: Boolean,
    val providerCryptoAvailable: Boolean,
    val providerSelectable: Boolean,
    val vaultCreationAvailable: Boolean,
    val vaultUnlockAvailable: Boolean,
    val vaultPersistenceAvailable: Boolean,
    val secureSecretStorageAvailable: Boolean,
    val secureMetadataStorageAvailable: Boolean,
    val mainnetAvailable: Boolean,
) {
    companion object {
        val StillDisabled = SkaldVaultV1VaultRandomnessCapability(
            randomnessOperationAuthorized = false,
            runtimeRandomnessAvailable = false,
            osCryptographicRandomnessAvailable = false,
            providerRandomnessAvailable = false,
            hardwareBackedEntropyAvailable = false,
            androidOsCsprngAvailable = false,
            androidHardwareBackedEntropyAvailable = false,
            linuxOsCsprngAvailable = false,
            linuxHardwareEntropyAvailable = false,
            saltGenerationAvailable = false,
            nonceGenerationAvailable = false,
            keyGenerationEntropyAvailable = false,
            kdfSaltAvailable = false,
            aeadNonceAvailable = false,
            recordNonceAvailable = false,
            metadataNonceAvailable = false,
            manifestNonceAvailable = false,
            storageIndexNonceAvailable = false,
            deterministicTestVectorRandomnessAvailable = false,
            productionRuntimeRandomnessAvailable = false,
            providerOperationAuthorized = false,
            providerCryptoAvailable = false,
            providerSelectable = false,
            vaultCreationAvailable = false,
            vaultUnlockAvailable = false,
            vaultPersistenceAvailable = false,
            secureSecretStorageAvailable = false,
            secureMetadataStorageAvailable = false,
            mainnetAvailable = false,
        )
    }
}

data class SkaldVaultV1VaultRandomnessPolicySummary(
    val policyId: String,
    val policyVersion: Int,
    val operationKinds: Set<SkaldVaultV1VaultRandomnessOperationKind>,
    val purposes: Set<SkaldVaultV1VaultRandomnessPurpose>,
    val sourceKinds: Set<SkaldVaultV1VaultRandomnessSourceKind>,
    val requiredGates: Set<SkaldVaultV1VaultRandomnessRequiredGate>,
    val stillDisabled: Boolean,
)

class SkaldVaultV1VaultRandomnessPolicyToken internal constructor(
    val policyId: String,
    val operationKind: SkaldVaultV1VaultRandomnessOperationKind?,
    val purpose: SkaldVaultV1VaultRandomnessPurpose?,
    val sourceKind: SkaldVaultV1VaultRandomnessSourceKind?,
    val requiredGate: SkaldVaultV1VaultRandomnessRequiredGate?,
    val containsRandomBytes: Boolean = false,
    val containsEntropyBytes: Boolean = false,
    val containsSaltBytes: Boolean = false,
    val containsNonceBytes: Boolean = false,
    val containsKeyMaterial: Boolean = false,
    val containsProviderHandle: Boolean = false,
    val containsKdfMaterial: Boolean = false,
    val containsCiphertext: Boolean = false,
    val containsPlaintext: Boolean = false,
    val containsTagOrHeaderCommitmentBytes: Boolean = false,
    val containsRecordIdentifier: Boolean = false,
    val containsRootOrPathText: Boolean = false,
    val containsPayload: Boolean = false,
) {
    override fun toString(): String =
        "SkaldVaultV1VaultRandomnessPolicyToken(" +
            "policyId=$policyId, " +
            "operationKind=${operationKind?.name ?: "none"}, " +
            "purpose=${purpose?.name ?: "none"}, " +
            "sourceKind=${sourceKind?.name ?: "none"}, " +
            "requiredGate=${requiredGate?.name ?: "none"}, " +
            "redacted=true)"
}

data class SkaldVaultV1VaultRuntimeRandomnessAuthorizationEvidence(
    val policyId: String,
    val policyVersion: Int,
    val status: SkaldVaultV1VaultRuntimeRandomnessAuthorizationStatus,
    val decision: SkaldVaultV1VaultRuntimeRandomnessAuthorizationDecision,
    val source: SkaldVaultV1VaultRuntimeRandomnessAuthorizationSource,
    val operationKind: SkaldVaultV1VaultRandomnessOperationKind?,
    val purpose: SkaldVaultV1VaultRandomnessPurpose?,
    val sourceKind: SkaldVaultV1VaultRandomnessSourceKind?,
    val requiredGate: SkaldVaultV1VaultRandomnessRequiredGate?,
    val requiredGates: Set<SkaldVaultV1VaultRandomnessRequiredGate>,
    val blockers: Set<SkaldVaultV1VaultRandomnessBlocker>,
    val warnings: Set<SkaldVaultV1VaultRandomnessWarning>,
    val capability: SkaldVaultV1VaultRandomnessCapability,
    val policySummary: SkaldVaultV1VaultRandomnessPolicySummary,
    val policyTokenEvidence: SkaldVaultV1VaultRandomnessPolicyToken,
    val providerOperationAuthorizationEvidenceConsumed: Boolean,
    val providerSelectionEvidenceConsumed: Boolean,
    val providerAcceptanceEvidenceConsumed: Boolean,
    val dependencyProbeEvidenceConsumed: Boolean,
    val runtimeRandomnessPolicyEvidenceConsumed: Boolean,
    val androidCompatibilityEntropyEvidenceConsumed: Boolean,
    val redactionLeakageEvidenceConsumed: Boolean,
    val clearWipeStrategyEvidenceConsumed: Boolean,
    val passphrasePolicyEvidenceConsumed: Boolean,
    val lockSessionLifecycleEvidenceConsumed: Boolean,
    val persistenceReadinessEvidenceConsumed: Boolean,
    val storageSafetyPreflightEvidenceConsumed: Boolean,
    val disabledStorageServiceEvidenceConsumed: Boolean,
    val migrationCorruptionEvidenceConsumed: Boolean,
    val secureStorageEvidenceConsumed: Boolean,
    val secureMetadataEvidenceConsumed: Boolean,
    val runtimeRandomnessAuthorizationBoundaryModeled: Boolean = true,
    val runtimeRandomnessAuthorizationStillDisabled: Boolean = true,
    val runtimeRandomnessAuthorizationBlocksAllOperations: Boolean = true,
    val runtimeRandomnessAuthorizationDoesNotCallRandomApis: Boolean = true,
    val runtimeRandomnessAuthorizationDoesNotGenerateEntropy: Boolean = true,
    val runtimeRandomnessAuthorizationDoesNotGenerateSaltOrNonce: Boolean = true,
    val runtimeRandomnessAuthorizationDoesNotGenerateKeys: Boolean = true,
    val runtimeRandomnessAuthorizationDoesNotEnableProviderOperations: Boolean = true,
    val runtimeRandomnessAuthorizationDoesNotEnableUnlock: Boolean = true,
    val runtimeRandomnessAuthorizationDoesNotEnablePersistence: Boolean = true,
    val runtimeRandomnessAuthorizationDoesNotEnableProviderSelection: Boolean = true,
    val runtimeRandomnessFailureVocabularyModeled: Boolean = true,
    val runtimeRandomnessReady: Boolean = false,
    val osCsprngReady: Boolean = false,
    val providerRandomnessReady: Boolean = false,
    val hardwareBackedEntropyReady: Boolean = false,
    val saltGenerationReady: Boolean = false,
    val nonceGenerationReady: Boolean = false,
    val keyGenerationReady: Boolean = false,
    val kdfSaltReady: Boolean = false,
    val aeadNonceReady: Boolean = false,
    val randomnessOperationAuthorized: Boolean = false,
    val providerOperationAuthorized: Boolean = false,
    val providerCryptoReady: Boolean = false,
    val providerSelectable: Boolean = false,
    val vaultUnlockReady: Boolean = false,
    val vaultStorageAvailable: Boolean = false,
    val persistenceReady: Boolean = false,
    val vaultPersistenceReady: Boolean = false,
) {
    override fun toString(): String =
        "SkaldVaultV1VaultRuntimeRandomnessAuthorizationEvidence(" +
            "policyId=$policyId, " +
            "status=${status.name}, " +
            "decision=${decision.name}, " +
            "source=${source.name}, " +
            "operationKind=${operationKind?.name ?: "none"}, " +
            "purpose=${purpose?.name ?: "none"}, " +
            "sourceKind=${sourceKind?.name ?: "none"}, " +
            "requiredGate=${requiredGate?.name ?: "none"}, " +
            "capability=still-disabled, " +
            "policyTokenEvidence=$policyTokenEvidence)"
}

sealed class SkaldVaultV1VaultRuntimeRandomnessAuthorizationResult<out T> {
    data class Blocked<out T>(
        val value: T,
    ) : SkaldVaultV1VaultRuntimeRandomnessAuthorizationResult<T>() {
        override fun toString(): String =
            "SkaldVaultV1VaultRuntimeRandomnessAuthorizationResult.Blocked(value=$value)"
    }

    data class Rejected(
        val reason: SkaldVaultV1VaultRandomnessFailureReason,
        val status: SkaldVaultV1VaultRuntimeRandomnessAuthorizationStatus,
        val source: SkaldVaultV1VaultRuntimeRandomnessAuthorizationSource,
        val safeMessage: String,
    ) : SkaldVaultV1VaultRuntimeRandomnessAuthorizationResult<Nothing>() {
        override fun toString(): String =
            "SkaldVaultV1VaultRuntimeRandomnessAuthorizationResult.Rejected(" +
                "reason=${reason.name}, " +
                "status=${status.name})"
    }
}

enum class SkaldVaultV1VaultRandomnessFailureReason(val label: String) {
    EmptyEvidenceRejected("empty runtime randomness evidence rejected"),
    RandomBytesRejected("random bytes rejected"),
    EntropyBytesRejected("entropy bytes rejected"),
    SaltBytesRejected("salt bytes rejected"),
    NonceBytesRejected("nonce bytes rejected"),
    KeyBytesRejected("key bytes rejected"),
    SeedBytesRejected("seed bytes rejected"),
    ProviderHandleRejected("provider handle rejected"),
    ProviderImplementationInstanceRejected("provider implementation instance rejected"),
    RawPassphraseRejected("raw passphrase rejected"),
    KdfInputRejected("KDF input rejected"),
    KdfOutputRejected("KDF output rejected"),
    AeadKeyTagCiphertextPlaintextRejected("AEAD key/tag/ciphertext/plaintext rejected"),
    RecordBytesRejected("record bytes rejected"),
    RawPersistedContainerBytesRejected("raw persisted container bytes rejected"),
    ByteArrayInputRejected("byte-array input rejected"),
    CharArrayInputRejected("char-array input rejected"),
    RandomObjectInputRejected("random object input rejected"),
    RawAbsoluteLocationInputRejected("raw absolute location input rejected"),
    RawRelativeLocationInputRejected("raw relative location input rejected"),
    LinkLikeInputRejected("link-like input rejected"),
    PlatformObjectLikeInputRejected("platform object-like input rejected"),
    SecretMaterialRejected("secret-like material rejected"),
    WalletMaterialRejected("wallet material rejected"),
    BitcoinAddressLikeEvidenceRejected("Bitcoin address-like evidence rejected"),
    TransactionLikeEvidenceRejected("transaction-like evidence rejected"),
    TraversalRejected("traversal-like evidence rejected"),
    UnsupportedCharactersRejected("unsupported evidence characters rejected"),
    RawRandomnessInputRejected("raw randomness input rejected"),
}

class SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest private constructor(
    val source: SkaldVaultV1VaultRuntimeRandomnessAuthorizationSource,
    val operationKind: SkaldVaultV1VaultRandomnessOperationKind?,
    val purpose: SkaldVaultV1VaultRandomnessPurpose?,
    val sourceKind: SkaldVaultV1VaultRandomnessSourceKind?,
    val requiredGate: SkaldVaultV1VaultRandomnessRequiredGate?,
    private val rawCandidate: String?,
    private val providerOperationAuthorizationEvidence:
        SkaldVaultV1VaultProviderOperationAuthorizationEvidence?,
    private val providerSelectionResult: VaultCryptoProviderSelectionResult?,
    private val providerAcceptanceAssessment: ProductionProviderAcceptanceAssessment?,
    private val dependencyProbeResult: VaultCryptoDependencyProbeResult?,
    private val runtimeRandomnessPolicy: RuntimeRandomnessProviderPolicy?,
    private val androidCompatibilityAssessment: AndroidVaultCompatibilityAssessment?,
    private val redactionLeakageEvidence: SkaldVaultV1VaultRedactionEvidence?,
    private val clearWipeStrategyEvidence: SkaldVaultV1VaultClearWipeEvidence?,
    private val passphrasePolicyEvidence: SkaldVaultV1VaultPassphrasePolicyEvidence?,
    private val lockSessionLifecycleEvidence: SkaldVaultV1VaultLockSessionEvidence?,
    private val persistenceReadinessEvidence: SkaldVaultV1VaultPersistenceReadinessEvidence?,
    private val storageSafetyPreflightEvidence: SkaldVaultV1StorageSafetyPreflightEvidence?,
    private val disabledStorageServiceEvidence: SkaldVaultV1VaultStorageDisabledEvidence?,
    private val migrationCorruptionEvidence: SkaldVaultV1VaultMigrationCorruptionEvidence?,
    private val secureStorageCapability: SecureStorageCapability?,
    private val secureMetadataCapability: SecureMetadataPersistenceCapability?,
) {
    val rawCandidateRejected: Boolean
        get() = source == SkaldVaultV1VaultRuntimeRandomnessAuthorizationSource.RawRandomnessCandidate

    internal fun rawCandidateOrNull(): String? = rawCandidate

    internal fun providerOperationAuthorizationEvidenceOrNull():
        SkaldVaultV1VaultProviderOperationAuthorizationEvidence? =
        providerOperationAuthorizationEvidence

    internal fun providerSelectionResultOrNull(): VaultCryptoProviderSelectionResult? =
        providerSelectionResult

    internal fun providerAcceptanceAssessmentOrNull(): ProductionProviderAcceptanceAssessment? =
        providerAcceptanceAssessment

    internal fun dependencyProbeResultOrNull(): VaultCryptoDependencyProbeResult? = dependencyProbeResult

    internal fun runtimeRandomnessPolicyOrNull(): RuntimeRandomnessProviderPolicy? = runtimeRandomnessPolicy

    internal fun androidCompatibilityAssessmentOrNull(): AndroidVaultCompatibilityAssessment? =
        androidCompatibilityAssessment

    internal fun redactionLeakageEvidenceOrNull(): SkaldVaultV1VaultRedactionEvidence? =
        redactionLeakageEvidence

    internal fun clearWipeStrategyEvidenceOrNull(): SkaldVaultV1VaultClearWipeEvidence? =
        clearWipeStrategyEvidence

    internal fun passphrasePolicyEvidenceOrNull(): SkaldVaultV1VaultPassphrasePolicyEvidence? =
        passphrasePolicyEvidence

    internal fun lockSessionLifecycleEvidenceOrNull(): SkaldVaultV1VaultLockSessionEvidence? =
        lockSessionLifecycleEvidence

    internal fun persistenceReadinessEvidenceOrNull(): SkaldVaultV1VaultPersistenceReadinessEvidence? =
        persistenceReadinessEvidence

    internal fun storageSafetyPreflightEvidenceOrNull(): SkaldVaultV1StorageSafetyPreflightEvidence? =
        storageSafetyPreflightEvidence

    internal fun disabledStorageServiceEvidenceOrNull(): SkaldVaultV1VaultStorageDisabledEvidence? =
        disabledStorageServiceEvidence

    internal fun migrationCorruptionEvidenceOrNull(): SkaldVaultV1VaultMigrationCorruptionEvidence? =
        migrationCorruptionEvidence

    internal fun secureStorageCapabilityOrNull(): SecureStorageCapability? = secureStorageCapability

    internal fun secureMetadataCapabilityOrNull(): SecureMetadataPersistenceCapability? = secureMetadataCapability

    override fun toString(): String =
        "SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest(" +
            "source=${source.name}, " +
            "operationKind=${operationKind?.name ?: "none"}, " +
            "purpose=${purpose?.name ?: "none"}, " +
            "sourceKind=${sourceKind?.name ?: "none"}, " +
            "requiredGate=${requiredGate?.name ?: "none"}, " +
            "rawCandidate=REDACTED)"

    companion object {
        fun noEvidence(): SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest =
            SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest(
                source = SkaldVaultV1VaultRuntimeRandomnessAuthorizationSource.NoEvidence,
                operationKind = null,
                purpose = null,
                sourceKind = null,
                requiredGate = null,
                rawCandidate = null,
                providerOperationAuthorizationEvidence = null,
                providerSelectionResult = null,
                providerAcceptanceAssessment = null,
                dependencyProbeResult = null,
                runtimeRandomnessPolicy = null,
                androidCompatibilityAssessment = null,
                redactionLeakageEvidence = null,
                clearWipeStrategyEvidence = null,
                passphrasePolicyEvidence = null,
                lockSessionLifecycleEvidence = null,
                persistenceReadinessEvidence = null,
                storageSafetyPreflightEvidence = null,
                disabledStorageServiceEvidence = null,
                migrationCorruptionEvidence = null,
                secureStorageCapability = null,
                secureMetadataCapability = null,
            )

        fun summary(): SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest =
            noEvidence().copyFor(source = SkaldVaultV1VaultRuntimeRandomnessAuthorizationSource.PolicySummary)

        fun forOperationKind(
            operationKind: SkaldVaultV1VaultRandomnessOperationKind,
            purpose: SkaldVaultV1VaultRandomnessPurpose? = null,
            sourceKind: SkaldVaultV1VaultRandomnessSourceKind? = null,
        ): SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest =
            noEvidence().copyFor(
                source = SkaldVaultV1VaultRuntimeRandomnessAuthorizationSource.OperationKind,
                operationKind = operationKind,
                purpose = purpose,
                sourceKind = sourceKind,
            )

        fun forPurpose(
            purpose: SkaldVaultV1VaultRandomnessPurpose,
        ): SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest =
            noEvidence().copyFor(
                source = SkaldVaultV1VaultRuntimeRandomnessAuthorizationSource.Purpose,
                purpose = purpose,
            )

        fun forSourceKind(
            sourceKind: SkaldVaultV1VaultRandomnessSourceKind,
            purpose: SkaldVaultV1VaultRandomnessPurpose? = null,
        ): SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest =
            noEvidence().copyFor(
                source = SkaldVaultV1VaultRuntimeRandomnessAuthorizationSource.SourceKind,
                sourceKind = sourceKind,
                purpose = purpose,
            )

        fun forRequiredGate(
            requiredGate: SkaldVaultV1VaultRandomnessRequiredGate,
        ): SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest =
            noEvidence().copyFor(
                source = SkaldVaultV1VaultRuntimeRandomnessAuthorizationSource.RequiredGate,
                requiredGate = requiredGate,
            )

        fun fromEvidence(
            providerOperationAuthorizationEvidence:
                SkaldVaultV1VaultProviderOperationAuthorizationEvidence? = null,
            providerSelectionResult: VaultCryptoProviderSelectionResult? = null,
            providerAcceptanceAssessment: ProductionProviderAcceptanceAssessment? = null,
            dependencyProbeResult: VaultCryptoDependencyProbeResult? = null,
            runtimeRandomnessPolicy: RuntimeRandomnessProviderPolicy? = null,
            androidCompatibilityAssessment: AndroidVaultCompatibilityAssessment? = null,
            redactionLeakageEvidence: SkaldVaultV1VaultRedactionEvidence? = null,
            clearWipeStrategyEvidence: SkaldVaultV1VaultClearWipeEvidence? = null,
            passphrasePolicyEvidence: SkaldVaultV1VaultPassphrasePolicyEvidence? = null,
            lockSessionLifecycleEvidence: SkaldVaultV1VaultLockSessionEvidence? = null,
            persistenceReadinessEvidence: SkaldVaultV1VaultPersistenceReadinessEvidence? = null,
            storageSafetyPreflightEvidence: SkaldVaultV1StorageSafetyPreflightEvidence? = null,
            disabledStorageServiceEvidence: SkaldVaultV1VaultStorageDisabledEvidence? = null,
            migrationCorruptionEvidence: SkaldVaultV1VaultMigrationCorruptionEvidence? = null,
            secureStorageCapability: SecureStorageCapability? = null,
            secureMetadataCapability: SecureMetadataPersistenceCapability? = null,
            operationKind: SkaldVaultV1VaultRandomnessOperationKind? = null,
            purpose: SkaldVaultV1VaultRandomnessPurpose? = null,
            sourceKind: SkaldVaultV1VaultRandomnessSourceKind? = null,
        ): SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest =
            SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest(
                source = SkaldVaultV1VaultRuntimeRandomnessAuthorizationSource.ComposedTypedEvidence,
                operationKind = operationKind,
                purpose = purpose,
                sourceKind = sourceKind,
                requiredGate = null,
                rawCandidate = null,
                providerOperationAuthorizationEvidence = providerOperationAuthorizationEvidence,
                providerSelectionResult = providerSelectionResult,
                providerAcceptanceAssessment = providerAcceptanceAssessment,
                dependencyProbeResult = dependencyProbeResult,
                runtimeRandomnessPolicy = runtimeRandomnessPolicy,
                androidCompatibilityAssessment = androidCompatibilityAssessment,
                redactionLeakageEvidence = redactionLeakageEvidence,
                clearWipeStrategyEvidence = clearWipeStrategyEvidence,
                passphrasePolicyEvidence = passphrasePolicyEvidence,
                lockSessionLifecycleEvidence = lockSessionLifecycleEvidence,
                persistenceReadinessEvidence = persistenceReadinessEvidence,
                storageSafetyPreflightEvidence = storageSafetyPreflightEvidence,
                disabledStorageServiceEvidence = disabledStorageServiceEvidence,
                migrationCorruptionEvidence = migrationCorruptionEvidence,
                secureStorageCapability = secureStorageCapability,
                secureMetadataCapability = secureMetadataCapability,
            )

        fun rawRandomnessCandidate(
            rawCandidate: String?,
        ): SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest =
            noEvidence().copyFor(
                source = SkaldVaultV1VaultRuntimeRandomnessAuthorizationSource.RawRandomnessCandidate,
                rawCandidate = rawCandidate,
            )
    }

    private fun copyFor(
        source: SkaldVaultV1VaultRuntimeRandomnessAuthorizationSource = this.source,
        operationKind: SkaldVaultV1VaultRandomnessOperationKind? = this.operationKind,
        purpose: SkaldVaultV1VaultRandomnessPurpose? = this.purpose,
        sourceKind: SkaldVaultV1VaultRandomnessSourceKind? = this.sourceKind,
        requiredGate: SkaldVaultV1VaultRandomnessRequiredGate? = this.requiredGate,
        rawCandidate: String? = this.rawCandidate,
    ): SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest =
        SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest(
            source = source,
            operationKind = operationKind,
            purpose = purpose,
            sourceKind = sourceKind,
            requiredGate = requiredGate,
            rawCandidate = rawCandidate,
            providerOperationAuthorizationEvidence = providerOperationAuthorizationEvidence,
            providerSelectionResult = providerSelectionResult,
            providerAcceptanceAssessment = providerAcceptanceAssessment,
            dependencyProbeResult = dependencyProbeResult,
            runtimeRandomnessPolicy = runtimeRandomnessPolicy,
            androidCompatibilityAssessment = androidCompatibilityAssessment,
            redactionLeakageEvidence = redactionLeakageEvidence,
            clearWipeStrategyEvidence = clearWipeStrategyEvidence,
            passphrasePolicyEvidence = passphrasePolicyEvidence,
            lockSessionLifecycleEvidence = lockSessionLifecycleEvidence,
            persistenceReadinessEvidence = persistenceReadinessEvidence,
            storageSafetyPreflightEvidence = storageSafetyPreflightEvidence,
            disabledStorageServiceEvidence = disabledStorageServiceEvidence,
            migrationCorruptionEvidence = migrationCorruptionEvidence,
            secureStorageCapability = secureStorageCapability,
            secureMetadataCapability = secureMetadataCapability,
        )
}

object SkaldVaultV1RuntimeRandomnessAuthorizationPolicy :
    SkaldVaultV1RuntimeRandomnessAuthorizationBoundary {
    const val POLICY_ID = "skald-vault-v1-runtime-randomness-authorization-boundary-v1"
    const val POLICY_VERSION = 1

    fun currentPolicySummary(): SkaldVaultV1VaultRandomnessPolicySummary =
        SkaldVaultV1VaultRandomnessPolicySummary(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            operationKinds = SkaldVaultV1VaultRandomnessOperationKind.entries.toSet(),
            purposes = SkaldVaultV1VaultRandomnessPurpose.entries.toSet(),
            sourceKinds = SkaldVaultV1VaultRandomnessSourceKind.entries.toSet(),
            requiredGates = SkaldVaultV1VaultRandomnessRequiredGate.entries.toSet(),
            stillDisabled = true,
        )

    override fun evaluate(
        request: SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest,
    ): SkaldVaultV1VaultRuntimeRandomnessAuthorizationResult<
        SkaldVaultV1VaultRuntimeRandomnessAuthorizationEvidence,
    > =
        when (request.source) {
            SkaldVaultV1VaultRuntimeRandomnessAuthorizationSource.RawRandomnessCandidate ->
                rejectRawCandidate(request)
            else -> blocked(request)
        }

    private fun blocked(
        request: SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest,
    ): SkaldVaultV1VaultRuntimeRandomnessAuthorizationResult.Blocked<
        SkaldVaultV1VaultRuntimeRandomnessAuthorizationEvidence,
    > {
        val policyTokenEvidence = SkaldVaultV1VaultRandomnessPolicyToken(
            policyId = POLICY_ID,
            operationKind = request.operationKind,
            purpose = request.purpose,
            sourceKind = request.sourceKind,
            requiredGate = request.requiredGate,
        )

        return SkaldVaultV1VaultRuntimeRandomnessAuthorizationResult.Blocked(
            SkaldVaultV1VaultRuntimeRandomnessAuthorizationEvidence(
                policyId = POLICY_ID,
                policyVersion = POLICY_VERSION,
                status = statusFor(request),
                decision = decisionFor(request),
                source = request.source,
                operationKind = request.operationKind,
                purpose = request.purpose,
                sourceKind = request.sourceKind,
                requiredGate = request.requiredGate,
                requiredGates = requiredGatesFor(request),
                blockers = blockersFor(request),
                warnings = SkaldVaultV1VaultRandomnessWarning.entries.toSet(),
                capability = SkaldVaultV1VaultRandomnessCapability.StillDisabled,
                policySummary = currentPolicySummary(),
                policyTokenEvidence = policyTokenEvidence,
                providerOperationAuthorizationEvidenceConsumed =
                    request.providerOperationAuthorizationEvidenceOrNull() != null,
                providerSelectionEvidenceConsumed = request.providerSelectionResultOrNull() != null,
                providerAcceptanceEvidenceConsumed = request.providerAcceptanceAssessmentOrNull() != null,
                dependencyProbeEvidenceConsumed = request.dependencyProbeResultOrNull() != null,
                runtimeRandomnessPolicyEvidenceConsumed = request.runtimeRandomnessPolicyOrNull() != null,
                androidCompatibilityEntropyEvidenceConsumed =
                    request.androidCompatibilityAssessmentOrNull() != null,
                redactionLeakageEvidenceConsumed = request.redactionLeakageEvidenceOrNull() != null,
                clearWipeStrategyEvidenceConsumed = request.clearWipeStrategyEvidenceOrNull() != null,
                passphrasePolicyEvidenceConsumed = request.passphrasePolicyEvidenceOrNull() != null,
                lockSessionLifecycleEvidenceConsumed = request.lockSessionLifecycleEvidenceOrNull() != null,
                persistenceReadinessEvidenceConsumed = request.persistenceReadinessEvidenceOrNull() != null,
                storageSafetyPreflightEvidenceConsumed = request.storageSafetyPreflightEvidenceOrNull() != null,
                disabledStorageServiceEvidenceConsumed = request.disabledStorageServiceEvidenceOrNull() != null,
                migrationCorruptionEvidenceConsumed = request.migrationCorruptionEvidenceOrNull() != null,
                secureStorageEvidenceConsumed = request.secureStorageCapabilityOrNull() != null,
                secureMetadataEvidenceConsumed = request.secureMetadataCapabilityOrNull() != null,
            ),
        )
    }

    private fun rejectRawCandidate(
        request: SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest,
    ): SkaldVaultV1VaultRuntimeRandomnessAuthorizationResult.Rejected =
        SkaldVaultV1VaultRuntimeRandomnessAuthorizationResult.Rejected(
            reason = classifyRawCandidate(request.rawCandidateOrNull()),
            status = SkaldVaultV1VaultRuntimeRandomnessAuthorizationStatus.RawCandidateRejected,
            source = SkaldVaultV1VaultRuntimeRandomnessAuthorizationSource.RawRandomnessCandidate,
            safeMessage = "Raw runtime randomness material is not accepted by this model-only boundary.",
        )

    private fun statusFor(
        request: SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest,
    ): SkaldVaultV1VaultRuntimeRandomnessAuthorizationStatus =
        when (request.source) {
            SkaldVaultV1VaultRuntimeRandomnessAuthorizationSource.NoEvidence ->
                SkaldVaultV1VaultRuntimeRandomnessAuthorizationStatus.NoEvidenceAvailable
            SkaldVaultV1VaultRuntimeRandomnessAuthorizationSource.PolicySummary ->
                SkaldVaultV1VaultRuntimeRandomnessAuthorizationStatus.PolicySummaryModeled
            SkaldVaultV1VaultRuntimeRandomnessAuthorizationSource.OperationKind ->
                SkaldVaultV1VaultRuntimeRandomnessAuthorizationStatus.OperationKindModeled
            SkaldVaultV1VaultRuntimeRandomnessAuthorizationSource.Purpose ->
                SkaldVaultV1VaultRuntimeRandomnessAuthorizationStatus.PurposeModeled
            SkaldVaultV1VaultRuntimeRandomnessAuthorizationSource.SourceKind ->
                SkaldVaultV1VaultRuntimeRandomnessAuthorizationStatus.SourceKindModeled
            SkaldVaultV1VaultRuntimeRandomnessAuthorizationSource.RequiredGate ->
                SkaldVaultV1VaultRuntimeRandomnessAuthorizationStatus.RequiredGateModeled
            SkaldVaultV1VaultRuntimeRandomnessAuthorizationSource.ComposedTypedEvidence ->
                SkaldVaultV1VaultRuntimeRandomnessAuthorizationStatus.RuntimeRandomnessBlockedStillDisabled
            SkaldVaultV1VaultRuntimeRandomnessAuthorizationSource.RawRandomnessCandidate ->
                SkaldVaultV1VaultRuntimeRandomnessAuthorizationStatus.RawCandidateRejected
        }

    private fun decisionFor(
        request: SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest,
    ): SkaldVaultV1VaultRuntimeRandomnessAuthorizationDecision =
        when {
            request.operationKind == SkaldVaultV1VaultRandomnessOperationKind.MainnetRandomnessRequest ||
                request.purpose == SkaldVaultV1VaultRandomnessPurpose.MainnetValidation ->
                SkaldVaultV1VaultRuntimeRandomnessAuthorizationDecision.ForbiddenSourceRejected
            request.sourceKind == SkaldVaultV1VaultRandomnessSourceKind.UnknownSource ||
                request.sourceKind == SkaldVaultV1VaultRandomnessSourceKind.UnsupportedSource ->
                SkaldVaultV1VaultRuntimeRandomnessAuthorizationDecision.UnsupportedFailClosed
            request.sourceKind?.forbidden == true ->
                SkaldVaultV1VaultRuntimeRandomnessAuthorizationDecision.ForbiddenSourceRejected
            request.sourceKind?.deterministicTestOnly == true ||
                request.purpose == SkaldVaultV1VaultRandomnessPurpose.TestOnlyDeterministicVector ->
                SkaldVaultV1VaultRuntimeRandomnessAuthorizationDecision.TestOnlyScopeRejectedForProduction
            request.source == SkaldVaultV1VaultRuntimeRandomnessAuthorizationSource.NoEvidence ||
                request.source == SkaldVaultV1VaultRuntimeRandomnessAuthorizationSource.ComposedTypedEvidence ->
                SkaldVaultV1VaultRuntimeRandomnessAuthorizationDecision.BlockedFailClosed
            else -> SkaldVaultV1VaultRuntimeRandomnessAuthorizationDecision.Unauthorized
        }

    private fun requiredGatesFor(
        request: SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest,
    ): Set<SkaldVaultV1VaultRandomnessRequiredGate> =
        buildSet {
            add(SkaldVaultV1VaultRandomnessRequiredGate.SourceIsNotForbidden)
            add(SkaldVaultV1VaultRandomnessRequiredGate.SourceIsOsCsprngOrReviewedProviderRandomness)
            add(SkaldVaultV1VaultRandomnessRequiredGate.SourceIsNotKotlinRandom)
            add(SkaldVaultV1VaultRandomnessRequiredGate.SourceIsNotJavaRandom)
            add(SkaldVaultV1VaultRandomnessRequiredGate.SourceIsNotMathRandom)
            add(SkaldVaultV1VaultRandomnessRequiredGate.SourceIsNotTimestampTimeBased)
            add(SkaldVaultV1VaultRandomnessRequiredGate.SourceIsNotUserSupplied)
            add(SkaldVaultV1VaultRandomnessRequiredGate.SourceIsNotNetworkSupplied)
            add(SkaldVaultV1VaultRandomnessRequiredGate.SourceIsNotPersistedOrReused)
            add(SkaldVaultV1VaultRandomnessRequiredGate.SourceReviewCompletedForPlatform)
            add(SkaldVaultV1VaultRandomnessRequiredGate.ProviderOperationAuthorizationApproved)
            add(SkaldVaultV1VaultRandomnessRequiredGate.RuntimeRandomnessHealthCheckApproved)
            add(SkaldVaultV1VaultRandomnessRequiredGate.RedactionLeakagePolicyApproved)
            add(SkaldVaultV1VaultRandomnessRequiredGate.ClearWipePolicyApprovedWhereBuffersAreInvolved)
            add(SkaldVaultV1VaultRandomnessRequiredGate.NoRawRandomEntropyBytesInDiagnostics)
            add(SkaldVaultV1VaultRandomnessRequiredGate.NoDeterministicTestVectorSourceInProductionRuntime)
            when (request.sourceKind) {
                SkaldVaultV1VaultRandomnessSourceKind.AndroidOsCsprngEvidence,
                SkaldVaultV1VaultRandomnessSourceKind.AndroidHardwareBackedCapabilityEvidence,
                -> add(SkaldVaultV1VaultRandomnessRequiredGate.AndroidEntropyReviewCompletedWhereAndroidRuntimeIsUsed)
                SkaldVaultV1VaultRandomnessSourceKind.LinuxOsCsprngEvidence,
                SkaldVaultV1VaultRandomnessSourceKind.LinuxHardwareRngEvidence,
                -> add(SkaldVaultV1VaultRandomnessRequiredGate.LinuxEntropyReviewCompletedWhereLinuxRuntimeIsUsed)
                SkaldVaultV1VaultRandomnessSourceKind.ProviderOwnedCryptographicRandomness -> {
                    add(SkaldVaultV1VaultRandomnessRequiredGate.ProviderSelectableWhereProviderRandomnessIsUsed)
                    add(SkaldVaultV1VaultRandomnessRequiredGate.ProviderKatsApprovedWhereProviderRandomnessIsUsed)
                }
                else -> Unit
            }
            when (request.operationKind) {
                SkaldVaultV1VaultRandomnessOperationKind.KdfSaltRequest,
                -> add(SkaldVaultV1VaultRandomnessRequiredGate.PassphrasePolicyApprovedWhereKdfSaltIsUsedForUnlock)
                SkaldVaultV1VaultRandomnessOperationKind.ProductionRuntimeRandomnessRequest,
                SkaldVaultV1VaultRandomnessOperationKind.OsCryptographicRandomnessRequest,
                SkaldVaultV1VaultRandomnessOperationKind.ProviderRandomnessRequest,
                -> add(SkaldVaultV1VaultRandomnessRequiredGate.RuntimeRandomnessHealthCheckApproved)
                SkaldVaultV1VaultRandomnessOperationKind.MigrationNonceSaltRequest,
                -> add(
                    SkaldVaultV1VaultRandomnessRequiredGate
                        .MigrationCorruptionPolicyApprovedWhereMigrationRecoveryUsesRandomness,
                )
                SkaldVaultV1VaultRandomnessOperationKind.MainnetRandomnessRequest,
                -> add(SkaldVaultV1VaultRandomnessRequiredGate.MainnetReleaseReviewApproved)
                else -> Unit
            }
            when (request.purpose) {
                SkaldVaultV1VaultRandomnessPurpose.UnlockVault,
                SkaldVaultV1VaultRandomnessPurpose.DeriveVaultKeys,
                -> {
                    add(SkaldVaultV1VaultRandomnessRequiredGate.PassphrasePolicyApprovedWhereKdfSaltIsUsedForUnlock)
                    add(SkaldVaultV1VaultRandomnessRequiredGate.LockSessionApprovedWhereUnlockSessionUsesRandomness)
                }
                SkaldVaultV1VaultRandomnessPurpose.GenerateRecordNonce,
                SkaldVaultV1VaultRandomnessPurpose.GenerateMetadataNonce,
                SkaldVaultV1VaultRandomnessPurpose.GenerateStorageIndexNonce,
                SkaldVaultV1VaultRandomnessPurpose.GenerateManifestNonce,
                -> {
                    add(SkaldVaultV1VaultRandomnessRequiredGate.PersistenceReadinessApprovedWhereStorageUsesRandomness)
                    add(SkaldVaultV1VaultRandomnessRequiredGate.StorageSafetyApprovedWhereStorageUsesRandomness)
                }
                SkaldVaultV1VaultRandomnessPurpose.MigrationRecoveryPlanning,
                -> add(
                    SkaldVaultV1VaultRandomnessRequiredGate
                        .MigrationCorruptionPolicyApprovedWhereMigrationRecoveryUsesRandomness,
                )
                SkaldVaultV1VaultRandomnessPurpose.MainnetValidation,
                -> add(SkaldVaultV1VaultRandomnessRequiredGate.MainnetReleaseReviewApproved)
                else -> Unit
            }
            request.requiredGate?.let(::add)
        }

    private fun blockersFor(
        request: SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest,
    ): Set<SkaldVaultV1VaultRandomnessBlocker> =
        buildSet {
            add(SkaldVaultV1VaultRandomnessBlocker.RuntimeRandomnessAuthorizationStillDisabled)
            add(SkaldVaultV1VaultRandomnessBlocker.RuntimeRandomnessHealthCheckMissing)
            add(SkaldVaultV1VaultRandomnessBlocker.PlatformRandomnessSourceReviewMissing)
            add(SkaldVaultV1VaultRandomnessBlocker.ProviderOperationAuthorizationBlocked)
            add(SkaldVaultV1VaultRandomnessBlocker.ProviderSelectionDisabled)
            add(SkaldVaultV1VaultRandomnessBlocker.DisabledProviderSelected)
            add(SkaldVaultV1VaultRandomnessBlocker.ProductionProviderSelectableFalse)
            add(SkaldVaultV1VaultRandomnessBlocker.ProviderKatApprovalMissing)
            add(SkaldVaultV1VaultRandomnessBlocker.ProviderRandomnessUnavailable)
            add(SkaldVaultV1VaultRandomnessBlocker.OsCryptographicRandomnessUnavailable)
            add(SkaldVaultV1VaultRandomnessBlocker.HardwareBackedEntropyUnavailable)
            add(SkaldVaultV1VaultRandomnessBlocker.SaltGenerationUnavailable)
            add(SkaldVaultV1VaultRandomnessBlocker.NonceGenerationUnavailable)
            add(SkaldVaultV1VaultRandomnessBlocker.KeyGenerationEntropyUnavailable)
            add(SkaldVaultV1VaultRandomnessBlocker.PassphrasePolicyBlocked)
            add(SkaldVaultV1VaultRandomnessBlocker.LockSessionLifecycleBlocked)
            add(SkaldVaultV1VaultRandomnessBlocker.PersistenceReadinessBlocked)
            add(SkaldVaultV1VaultRandomnessBlocker.StorageSafetyPreflightBlocked)
            add(SkaldVaultV1VaultRandomnessBlocker.DisabledStorageServiceUnavailable)
            add(SkaldVaultV1VaultRandomnessBlocker.ClearWipeStrategyBlocked)
            add(SkaldVaultV1VaultRandomnessBlocker.RedactionLeakageUnsafe)
            add(SkaldVaultV1VaultRandomnessBlocker.SecureSecretStorageUnavailable)
            add(SkaldVaultV1VaultRandomnessBlocker.SecureMetadataStorageUnavailable)
            add(SkaldVaultV1VaultRandomnessBlocker.WarningOnlyEvidenceRejected)
            add(SkaldVaultV1VaultRandomnessBlocker.UserConsentOverrideRejected)
            add(SkaldVaultV1VaultRandomnessBlocker.MainnetUnavailable)
            if (request.source == SkaldVaultV1VaultRuntimeRandomnessAuthorizationSource.NoEvidence) {
                add(SkaldVaultV1VaultRandomnessBlocker.NoEvidenceAvailable)
            }
            request.sourceKind?.let { sourceKind ->
                when {
                    sourceKind == SkaldVaultV1VaultRandomnessSourceKind.UnknownSource ||
                        sourceKind == SkaldVaultV1VaultRandomnessSourceKind.UnsupportedSource ->
                        add(SkaldVaultV1VaultRandomnessBlocker.SourceUnknownOrUnsupported)
                    sourceKind.forbidden -> {
                        add(SkaldVaultV1VaultRandomnessBlocker.SourceForbidden)
                        add(SkaldVaultV1VaultRandomnessBlocker.GeneralPurposeRandomRejected)
                    }
                    sourceKind == SkaldVaultV1VaultRandomnessSourceKind.AndroidOsCsprngEvidence ||
                        sourceKind == SkaldVaultV1VaultRandomnessSourceKind.AndroidHardwareBackedCapabilityEvidence ->
                        add(SkaldVaultV1VaultRandomnessBlocker.AndroidEntropyReviewMissing)
                    sourceKind == SkaldVaultV1VaultRandomnessSourceKind.LinuxOsCsprngEvidence ||
                        sourceKind == SkaldVaultV1VaultRandomnessSourceKind.LinuxHardwareRngEvidence ->
                        add(SkaldVaultV1VaultRandomnessBlocker.LinuxEntropyReviewMissing)
                    sourceKind == SkaldVaultV1VaultRandomnessSourceKind.DeterministicTestVectorSource ->
                        add(SkaldVaultV1VaultRandomnessBlocker.DeterministicTestVectorRejectedForProduction)
                }
                when (sourceKind) {
                    SkaldVaultV1VaultRandomnessSourceKind.ForbiddenTimeBasedRandomness ->
                        add(SkaldVaultV1VaultRandomnessBlocker.TimeBasedRandomnessRejected)
                    SkaldVaultV1VaultRandomnessSourceKind.ForbiddenUserSuppliedRandomness ->
                        add(SkaldVaultV1VaultRandomnessBlocker.UserSuppliedRandomnessRejected)
                    SkaldVaultV1VaultRandomnessSourceKind.ForbiddenNetworkSuppliedRandomness ->
                        add(SkaldVaultV1VaultRandomnessBlocker.NetworkSuppliedRandomnessRejected)
                    SkaldVaultV1VaultRandomnessSourceKind.ForbiddenPersistedRandomness ->
                        add(SkaldVaultV1VaultRandomnessBlocker.PersistedRandomnessRejected)
                    else -> Unit
                }
            }
            if (request.providerOperationAuthorizationEvidenceOrNull() != null) {
                add(SkaldVaultV1VaultRandomnessBlocker.ProviderOperationAuthorizationBlocked)
            }
            if (request.providerSelectionResultOrNull() != null) {
                add(SkaldVaultV1VaultRandomnessBlocker.DisabledProviderSelected)
            }
            if (request.providerAcceptanceAssessmentOrNull() != null) {
                add(SkaldVaultV1VaultRandomnessBlocker.ProductionProviderSelectableFalse)
            }
            if (request.passphrasePolicyEvidenceOrNull() != null) {
                add(SkaldVaultV1VaultRandomnessBlocker.PassphrasePolicyBlocked)
            }
            if (request.lockSessionLifecycleEvidenceOrNull() != null) {
                add(SkaldVaultV1VaultRandomnessBlocker.LockSessionLifecycleBlocked)
            }
            if (request.persistenceReadinessEvidenceOrNull() != null) {
                add(SkaldVaultV1VaultRandomnessBlocker.PersistenceReadinessBlocked)
            }
            if (request.storageSafetyPreflightEvidenceOrNull() != null) {
                add(SkaldVaultV1VaultRandomnessBlocker.StorageSafetyPreflightBlocked)
            }
            if (request.disabledStorageServiceEvidenceOrNull() != null) {
                add(SkaldVaultV1VaultRandomnessBlocker.DisabledStorageServiceUnavailable)
            }
            if (request.migrationCorruptionEvidenceOrNull() != null) {
                add(SkaldVaultV1VaultRandomnessBlocker.MigrationCorruptionBoundaryDisabled)
            }
            if (request.clearWipeStrategyEvidenceOrNull() != null) {
                add(SkaldVaultV1VaultRandomnessBlocker.ClearWipeStrategyBlocked)
            }
            if (request.redactionLeakageEvidenceOrNull() != null) {
                add(SkaldVaultV1VaultRandomnessBlocker.RedactionLeakageUnsafe)
            }
            if (request.secureStorageCapabilityOrNull() != null) {
                add(SkaldVaultV1VaultRandomnessBlocker.SecureSecretStorageUnavailable)
            }
            if (request.secureMetadataCapabilityOrNull() != null) {
                add(SkaldVaultV1VaultRandomnessBlocker.SecureMetadataStorageUnavailable)
            }
            if (request.operationKind == SkaldVaultV1VaultRandomnessOperationKind.MainnetRandomnessRequest ||
                request.purpose == SkaldVaultV1VaultRandomnessPurpose.MainnetValidation
            ) {
                add(SkaldVaultV1VaultRandomnessBlocker.MainnetUnavailable)
            }
        }

    private fun classifyRawCandidate(rawCandidate: String?): SkaldVaultV1VaultRandomnessFailureReason {
        val candidate = rawCandidate ?: return SkaldVaultV1VaultRandomnessFailureReason.EmptyEvidenceRejected
        if (candidate.isBlank()) {
            return SkaldVaultV1VaultRandomnessFailureReason.EmptyEvidenceRejected
        }
        val normalized = candidate.lowercase()
        return when {
            normalized.contains("random-bytes") ->
                SkaldVaultV1VaultRandomnessFailureReason.RandomBytesRejected
            normalized.contains("entropy-bytes") ->
                SkaldVaultV1VaultRandomnessFailureReason.EntropyBytesRejected
            normalized.contains("salt-bytes") ->
                SkaldVaultV1VaultRandomnessFailureReason.SaltBytesRejected
            normalized.contains("nonce-bytes") ->
                SkaldVaultV1VaultRandomnessFailureReason.NonceBytesRejected
            normalized.contains("key-bytes") ->
                SkaldVaultV1VaultRandomnessFailureReason.KeyBytesRejected
            normalized.contains("seed-bytes") || normalized.contains("seed-material") ->
                SkaldVaultV1VaultRandomnessFailureReason.SeedBytesRejected
            normalized.contains("provider-handle") ->
                SkaldVaultV1VaultRandomnessFailureReason.ProviderHandleRejected
            normalized.contains("provider-implementation") ||
                normalized.contains("crypto-provider-instance") ->
                SkaldVaultV1VaultRandomnessFailureReason.ProviderImplementationInstanceRejected
            normalized.contains("passphrase") ->
                SkaldVaultV1VaultRandomnessFailureReason.RawPassphraseRejected
            normalized.contains("kdf-input") ->
                SkaldVaultV1VaultRandomnessFailureReason.KdfInputRejected
            normalized.contains("kdf-output") ->
                SkaldVaultV1VaultRandomnessFailureReason.KdfOutputRejected
            normalized.contains("aead-key") ||
                normalized.contains("aead-tag") ||
                normalized.contains("ciphertext") ||
                normalized.contains("plaintext") ->
                SkaldVaultV1VaultRandomnessFailureReason.AeadKeyTagCiphertextPlaintextRejected
            normalized.contains("record-bytes") ->
                SkaldVaultV1VaultRandomnessFailureReason.RecordBytesRejected
            normalized.contains("container-bytes") ->
                SkaldVaultV1VaultRandomnessFailureReason.RawPersistedContainerBytesRejected
            normalized.contains("bytearray") ->
                SkaldVaultV1VaultRandomnessFailureReason.ByteArrayInputRejected
            normalized.contains("chararray") ->
                SkaldVaultV1VaultRandomnessFailureReason.CharArrayInputRejected
            normalized.contains("secure-random-object") ||
                normalized.contains("java-random-object") ||
                normalized.contains("kotlin-random-object") ->
                SkaldVaultV1VaultRandomnessFailureReason.RandomObjectInputRejected
            normalized.contains("secret") ||
                normalized.contains("credential") ->
                SkaldVaultV1VaultRandomnessFailureReason.SecretMaterialRejected
            candidate.startsWith("/") ->
                SkaldVaultV1VaultRandomnessFailureReason.RawAbsoluteLocationInputRejected
            normalized.contains("://") ->
                SkaldVaultV1VaultRandomnessFailureReason.LinkLikeInputRejected
            normalized.contains("..") ->
                SkaldVaultV1VaultRandomnessFailureReason.TraversalRejected
            normalized.contains("file-object") ||
                normalized.contains("path-object") ||
                normalized.contains("uri-object") ||
                normalized.contains("url-object") ->
                SkaldVaultV1VaultRandomnessFailureReason.PlatformObjectLikeInputRejected
            normalized.contains("/") ->
                SkaldVaultV1VaultRandomnessFailureReason.RawRelativeLocationInputRejected
            looksLikeWalletMaterial(candidate) ->
                SkaldVaultV1VaultRandomnessFailureReason.WalletMaterialRejected
            looksLikeBitcoinAddress(candidate) ->
                SkaldVaultV1VaultRandomnessFailureReason.BitcoinAddressLikeEvidenceRejected
            looksLikeHex64(candidate) ->
                SkaldVaultV1VaultRandomnessFailureReason.TransactionLikeEvidenceRejected
            candidate.any { it !in 'a'..'z' && it !in 'A'..'Z' && it !in '0'..'9' && it !in "-_./:" } ->
                SkaldVaultV1VaultRandomnessFailureReason.UnsupportedCharactersRejected
            else -> SkaldVaultV1VaultRandomnessFailureReason.RawRandomnessInputRejected
        }
    }

    private fun looksLikeHex64(candidate: String): Boolean =
        candidate.length == 64 && candidate.all { it in '0'..'9' || it in 'a'..'f' || it in 'A'..'F' }

    private fun looksLikeBitcoinAddress(candidate: String): Boolean {
        val normalized = candidate.lowercase()
        return (normalized.startsWith("bc1") ||
            normalized.startsWith("tb1") ||
            normalized.startsWith("bcrt1")) &&
            normalized.length >= 24 &&
            normalized.all { it in 'a'..'z' || it in '0'..'9' }
    }

    private fun looksLikeWalletMaterial(candidate: String): Boolean =
        candidate.startsWith("nsec1") ||
            candidate.startsWith("xprv") ||
            candidate.startsWith("tprv") ||
            (candidate.length in 51..52 && candidate.first() in setOf('K', 'L', '5'))
}
