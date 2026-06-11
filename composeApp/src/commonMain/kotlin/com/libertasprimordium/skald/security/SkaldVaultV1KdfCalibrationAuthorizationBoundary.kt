package com.libertasprimordium.skald.security

interface SkaldVaultV1KdfCalibrationAuthorizationBoundary {
    fun evaluate(
        request: SkaldVaultV1VaultKdfCalibrationAuthorizationRequest,
    ): SkaldVaultV1VaultKdfCalibrationAuthorizationResult<SkaldVaultV1VaultKdfCalibrationAuthorizationEvidence>
}

enum class SkaldVaultV1VaultKdfCalibrationAuthorizationSource(val label: String) {
    NoEvidence("no evidence"),
    PolicySummary("policy summary"),
    OperationKind("KDF operation kind"),
    Purpose("KDF purpose"),
    ParameterKind("KDF parameter or evidence kind"),
    PlatformClass("KDF platform or device class"),
    RequiredGate("required KDF authorization gate"),
    ComposedTypedEvidence("composed typed readiness and security evidence"),
    RawKdfCandidate("rejected raw KDF candidate"),
}

enum class SkaldVaultV1VaultKdfCalibrationAuthorizationStatus(val label: String) {
    NoEvidenceAvailable("no KDF calibration authorization evidence available"),
    PolicySummaryModeled("KDF calibration authorization policy summary modeled"),
    OperationKindModeled("KDF operation kind modeled"),
    PurposeModeled("KDF purpose modeled"),
    ParameterKindModeled("KDF parameter or evidence kind modeled"),
    PlatformClassModeled("KDF platform or device class modeled"),
    RequiredGateModeled("KDF required gate modeled"),
    KdfCalibrationBlockedStillDisabled("KDF calibration authorization blocked because the boundary is still disabled"),
    RawCandidateRejected("raw KDF candidate rejected"),
}

enum class SkaldVaultV1VaultKdfCalibrationAuthorizationDecision(
    val label: String,
    val kdfCalibrationAllowed: Boolean,
    val kdfExecutionAllowed: Boolean,
    val argon2idAllowed: Boolean,
    val finalParameterApprovalAllowed: Boolean,
    val androidCalibrationAllowed: Boolean,
    val linuxCalibrationAllowed: Boolean,
    val unlockAllowed: Boolean,
    val persistenceAllowed: Boolean,
) {
    BlockedFailClosed(
        label = "blocked fail-closed",
        kdfCalibrationAllowed = false,
        kdfExecutionAllowed = false,
        argon2idAllowed = false,
        finalParameterApprovalAllowed = false,
        androidCalibrationAllowed = false,
        linuxCalibrationAllowed = false,
        unlockAllowed = false,
        persistenceAllowed = false,
    ),
    Unauthorized(
        label = "unauthorized",
        kdfCalibrationAllowed = false,
        kdfExecutionAllowed = false,
        argon2idAllowed = false,
        finalParameterApprovalAllowed = false,
        androidCalibrationAllowed = false,
        linuxCalibrationAllowed = false,
        unlockAllowed = false,
        persistenceAllowed = false,
    ),
    RejectOperation(
        label = "operation rejected",
        kdfCalibrationAllowed = false,
        kdfExecutionAllowed = false,
        argon2idAllowed = false,
        finalParameterApprovalAllowed = false,
        androidCalibrationAllowed = false,
        linuxCalibrationAllowed = false,
        unlockAllowed = false,
        persistenceAllowed = false,
    ),
    TestOnlyScopeRejectedForProduction(
        label = "test-only KDF profile rejected for production runtime",
        kdfCalibrationAllowed = false,
        kdfExecutionAllowed = false,
        argon2idAllowed = false,
        finalParameterApprovalAllowed = false,
        androidCalibrationAllowed = false,
        linuxCalibrationAllowed = false,
        unlockAllowed = false,
        persistenceAllowed = false,
    ),
    UnsupportedFailClosed(
        label = "unsupported KDF platform or evidence fails closed",
        kdfCalibrationAllowed = false,
        kdfExecutionAllowed = false,
        argon2idAllowed = false,
        finalParameterApprovalAllowed = false,
        androidCalibrationAllowed = false,
        linuxCalibrationAllowed = false,
        unlockAllowed = false,
        persistenceAllowed = false,
    ),
}

enum class SkaldVaultV1VaultKdfOperationKind(val label: String) {
    KdfAvailabilityCheck("KDF availability check"),
    Argon2idCalibrationRequest("Argon2id calibration request"),
    Argon2idParameterFinalizationRequest("Argon2id parameter finalization request"),
    AndroidCalibrationCaptureReview("Android calibration capture review"),
    LinuxCalibrationReview("Linux calibration review"),
    DesktopCalibrationReview("desktop calibration review"),
    LowMemoryDeviceClassReview("low-memory device class review"),
    HighMemoryDesktopClassReview("high-memory desktop class review"),
    InteractiveUnlockCostReview("interactive unlock cost review"),
    DosUxCostReview("DoS/UX cost review"),
    MemoryCostApproval("memory cost approval"),
    IterationTimeCostApproval("iteration/time cost approval"),
    ParallelismApproval("parallelism approval"),
    SaltLengthApproval("salt length approval"),
    OutputLengthApproval("output length approval"),
    VersionIdApproval("version/id approval"),
    ParameterBoundsValidation("parameter bounds validation"),
    TestVectorParameterReview("test-vector parameter review"),
    ProductionRuntimeParameterReview("production runtime parameter review"),
    ReleaseValidationParameterReview("release validation parameter review"),
    MainnetParameterReview("mainnet parameter review"),
    KdfExecutionAuthorization("KDF execution authorization"),
}

enum class SkaldVaultV1VaultKdfPurpose(val label: String) {
    CreateVault("create vault"),
    UnlockVault("unlock vault"),
    DeriveRootVaultKey("derive root vault key"),
    DeriveMetadataKey("derive metadata key"),
    DeriveRecordKeyMaterial("derive record key material"),
    DeriveWrappingMaterial("derive wrapping material"),
    ValidateProviderKat("validate provider KAT"),
    ValidateDeterministicKatVector("validate deterministic KAT/vector"),
    CalibrateAndroidClass("calibrate Android class"),
    CalibrateLinuxDesktopClass("calibrate Linux desktop class"),
    CalibrateReleaseProfile("calibrate release profile"),
    ReviewProductionRuntimeProfile("review production runtime profile"),
    ReviewMigrationCompatibility("review migration compatibility"),
    BackupExportPreparation("backup/export preparation"),
    RestoreImportPreparation("restore/import preparation"),
    TestOnlyDeterministicVector("test-only deterministic vector"),
    ReleaseValidation("release validation"),
    MainnetValidation("mainnet validation"),
}

enum class SkaldVaultV1VaultKdfParameterKind(val label: String) {
    AlgorithmId("algorithm id"),
    Argon2idVersion("Argon2id version"),
    MemoryCost("memory cost"),
    IterationTimeCost("iteration/time cost"),
    Parallelism("parallelism"),
    SaltLength("salt length"),
    OutputLength("output length"),
    AssociatedProviderSuiteId("associated provider suite id"),
    PlatformClass("platform class"),
    DeviceClass("device class"),
    CalibrationTimestampCategory("calibration timestamp category"),
    CalibrationEnvironmentCategory("calibration environment category"),
    CalibrationResultSummary("calibration result summary"),
    AndroidCalibrationCaptureEvidence("Android calibration capture evidence"),
    LinuxDesktopCalibrationEvidence("Linux desktop calibration evidence"),
    ReleaseProfileEvidence("release-profile evidence"),
    TestVectorProfileEvidence("test-vector profile evidence"),
    MigrationCompatibilityEvidence("migration compatibility evidence"),
    UserExperienceThresholdEvidence("user-experience threshold evidence"),
    DosThresholdEvidence("DoS threshold evidence"),
    FinalApprovalEvidence("final approval evidence"),
    ManualReviewEvidence("manual review evidence"),
    FailClosedEvidence("fail-closed evidence"),
}

enum class SkaldVaultV1VaultKdfPlatformClass(
    val label: String,
    val androidRuntime: Boolean,
    val linuxRuntime: Boolean,
    val unsupported: Boolean,
    val testOnlyProfile: Boolean,
    val mainnetBlocked: Boolean,
) {
    AndroidModernSupportedDevice(
        label = "Android modern supported device",
        androidRuntime = true,
        linuxRuntime = false,
        unsupported = false,
        testOnlyProfile = false,
        mainnetBlocked = false,
    ),
    AndroidLowMemoryUnsupportedFailClosed(
        label = "Android low-memory unsupported/fail-closed",
        androidRuntime = true,
        linuxRuntime = false,
        unsupported = true,
        testOnlyProfile = false,
        mainnetBlocked = false,
    ),
    AndroidHardwareBackedCapabilityPresentNotKdfApproval(
        label = "Android hardware-backed capability present, not KDF approval",
        androidRuntime = true,
        linuxRuntime = false,
        unsupported = false,
        testOnlyProfile = false,
        mainnetBlocked = false,
    ),
    AndroidHardwareBackedCapabilityAbsentNotKdfApproval(
        label = "Android hardware-backed capability absent, still not KDF approval",
        androidRuntime = true,
        linuxRuntime = false,
        unsupported = false,
        testOnlyProfile = false,
        mainnetBlocked = false,
    ),
    LinuxDesktop(
        label = "Linux desktop",
        androidRuntime = false,
        linuxRuntime = true,
        unsupported = false,
        testOnlyProfile = false,
        mainnetBlocked = false,
    ),
    LinuxLowMemoryUnsupportedFailClosed(
        label = "Linux low-memory unsupported/fail-closed",
        androidRuntime = false,
        linuxRuntime = true,
        unsupported = true,
        testOnlyProfile = false,
        mainnetBlocked = false,
    ),
    DesktopHighMemoryProfile(
        label = "desktop high-memory profile",
        androidRuntime = false,
        linuxRuntime = true,
        unsupported = false,
        testOnlyProfile = false,
        mainnetBlocked = false,
    ),
    TestOnlyDeterministicProfile(
        label = "test-only deterministic profile",
        androidRuntime = false,
        linuxRuntime = false,
        unsupported = false,
        testOnlyProfile = true,
        mainnetBlocked = false,
    ),
    ReleaseValidationProfile(
        label = "release-validation profile",
        androidRuntime = false,
        linuxRuntime = false,
        unsupported = false,
        testOnlyProfile = false,
        mainnetBlocked = false,
    ),
    MainnetProfileBlocked(
        label = "mainnet profile blocked",
        androidRuntime = false,
        linuxRuntime = false,
        unsupported = false,
        testOnlyProfile = false,
        mainnetBlocked = true,
    ),
    UnknownPlatform(
        label = "unknown platform",
        androidRuntime = false,
        linuxRuntime = false,
        unsupported = true,
        testOnlyProfile = false,
        mainnetBlocked = false,
    ),
    UnsupportedPlatform(
        label = "unsupported platform",
        androidRuntime = false,
        linuxRuntime = false,
        unsupported = true,
        testOnlyProfile = false,
        mainnetBlocked = false,
    ),
}

enum class SkaldVaultV1VaultKdfRequiredGate(val label: String) {
    AlgorithmIsArgon2id("algorithm is Argon2id"),
    AlgorithmVersionIdApproved("algorithm/version id approved"),
    ParameterBoundsReviewed("parameter bounds reviewed"),
    MemoryCostReviewed("memory cost reviewed"),
    IterationTimeCostReviewed("iteration/time cost reviewed"),
    ParallelismReviewed("parallelism reviewed"),
    SaltLengthReviewed("salt length reviewed"),
    OutputLengthReviewed("output length reviewed"),
    ProviderSuiteIdReviewed("provider suite id reviewed"),
    ProviderOperationAuthorizationApproved("provider operation authorization approved"),
    ProviderSelectableWhereProviderKdfIsUsed("provider selectable where provider KDF is used"),
    ProviderKatsApprovedWhereProviderKdfIsUsed("provider KATs approved where provider KDF is used"),
    RuntimeRandomnessAuthorizationApprovedForSaltGeneration(
        "runtime randomness authorization approved for salt generation",
    ),
    PassphrasePolicyApproved("passphrase policy approved"),
    PassphraseNormalizationEncodingPolicyApproved("passphrase normalization/encoding policy approved"),
    ClearWipePolicyApproved("clear/wipe policy approved"),
    RedactionLeakagePolicyApproved("redaction/leakage policy approved"),
    LockSessionLifecycleApproved("lock/session lifecycle approved"),
    PersistenceReadinessApprovedWhereStorageUnlockIsInvolved(
        "persistence readiness approved where storage/unlock is involved",
    ),
    SecureSecretStorageApproved("secure secret storage approved"),
    SecureMetadataStorageApproved("secure metadata storage approved"),
    AndroidCalibrationCaptureApprovedForAndroidRuntime(
        "Android calibration capture approved for Android runtime",
    ),
    LinuxCalibrationApprovedForLinuxRuntime("Linux calibration approved for Linux runtime"),
    DosUxThresholdsApproved("DoS/UX thresholds approved"),
    MigrationCompatibilityApprovedWhereOldVaultsAreInvolved(
        "migration compatibility approved where old vaults are involved",
    ),
    NoRawPassphraseKdfMaterialInDiagnostics("no raw passphrase/KDF material in diagnostics"),
    TestVectorProfileNotUsedForProductionRuntime("test-vector profile not used for production runtime"),
    MainnetReleaseReviewApproved("mainnet remains disabled unless release review approves it"),
}

enum class SkaldVaultV1VaultKdfBlocker(val label: String) {
    KdfCalibrationAuthorizationStillDisabled("KDF calibration authorization boundary is still disabled"),
    NoEvidenceAvailable("no KDF calibration authorization evidence available"),
    FinalKdfCalibrationMissing("final KDF calibration is missing"),
    FinalParameterApprovalMissing("final parameter approval is missing"),
    Argon2idExecutionUnavailable("Argon2id execution is unavailable"),
    Argon2idCalibrationUnavailable("Argon2id calibration is unavailable"),
    ParameterBoundsReviewMissing("parameter bounds review is missing"),
    MemoryCostReviewMissing("memory cost review is missing"),
    IterationCostReviewMissing("iteration/time cost review is missing"),
    ParallelismReviewMissing("parallelism review is missing"),
    SaltLengthReviewMissing("salt length review is missing"),
    OutputLengthReviewMissing("output length review is missing"),
    ProviderOperationAuthorizationBlocked("provider operation authorization is blocked"),
    RuntimeRandomnessAuthorizationBlocked("runtime randomness authorization is blocked"),
    DisabledProviderSelected("provider selection still selects the disabled provider"),
    ProductionProviderSelectableFalse("productionProviderSelectable remains false"),
    ProviderKatApprovalMissing("provider KAT approval is missing"),
    PassphrasePolicyBlocked("passphrase policy is blocked"),
    LockSessionLifecycleBlocked("lock/session lifecycle is blocked"),
    PersistenceReadinessBlocked("persistence readiness is blocked"),
    ClearWipeStrategyBlocked("clear/wipe strategy is blocked"),
    RedactionLeakageUnsafe("redaction/leakage policy is unsafe or unavailable"),
    SecureSecretStorageUnavailable("secure secret storage is unavailable"),
    SecureMetadataStorageUnavailable("secure metadata storage is unavailable"),
    AndroidCalibrationMissing("Android calibration approval is missing"),
    LinuxCalibrationMissing("Linux calibration approval is missing"),
    MigrationCompatibilityMissing("migration compatibility approval is missing"),
    TestVectorProfileRejectedForProduction("test-vector profile cannot authorize production runtime KDF"),
    WarningOnlyEvidenceRejected("warning-only evidence cannot authorize KDF calibration"),
    UserConsentOverrideRejected("user consent cannot override missing hard gates"),
    MainnetUnavailable("mainnet remains disabled"),
    RawKdfCandidateRejected("raw KDF candidate was rejected"),
    UnknownUnsupportedPlatformClass("unknown or unsupported platform class fails closed"),
}

enum class SkaldVaultV1VaultKdfWarning(val label: String) {
    EvidenceOnly("KDF calibration authorization is evidence-only"),
    NoArgon2idRun("Argon2id is not run"),
    NoKdfCalibrationOrBenchmarkRun("KDF calibration and benchmark execution are not run"),
    NoFinalParameterApproval("final KDF parameters are not approved"),
    NoPassphraseAcceptedOrNormalized("passphrases are not accepted, normalized, or encoded"),
    NoSaltGeneratedOrConsumed("salts are not generated or consumed"),
    ProviderOperationsUnauthorized("provider operations are unauthorized"),
    RuntimeRandomnessUnauthorized("runtime randomness is unauthorized"),
    AndroidCalibrationFutureReviewedOnly("Android calibration remains future-reviewed only"),
    LinuxCalibrationFutureReviewedOnly("Linux calibration remains future-reviewed only"),
    TestVectorScopeDoesNotAuthorizeProduction("test-vector scope does not authorize production"),
    RedactedDiagnosticsOnly("diagnostics are redacted evidence only"),
    FutureImplementationRequiresReview("future KDF implementation requires explicit review"),
}

data class SkaldVaultV1VaultKdfCapability(
    val kdfCalibrationAuthorized: Boolean,
    val kdfExecutionAuthorized: Boolean,
    val argon2idExecutionAvailable: Boolean,
    val argon2idCalibrationAvailable: Boolean,
    val finalKdfParametersApproved: Boolean,
    val androidCalibrationApproved: Boolean,
    val linuxCalibrationApproved: Boolean,
    val memoryCostApproved: Boolean,
    val iterationCostApproved: Boolean,
    val parallelismApproved: Boolean,
    val saltLengthApproved: Boolean,
    val outputLengthApproved: Boolean,
    val providerOperationAuthorized: Boolean,
    val runtimeRandomnessAuthorized: Boolean,
    val saltGenerationAvailable: Boolean,
    val passphraseInputAccepted: Boolean,
    val passphraseNormalized: Boolean,
    val passphraseEncoded: Boolean,
    val clearWipeApproved: Boolean,
    val redactionApproved: Boolean,
    val lockSessionApproved: Boolean,
    val productionProviderSelected: Boolean,
    val providerSelectable: Boolean,
    val vaultCreationAvailable: Boolean,
    val vaultUnlockAvailable: Boolean,
    val vaultPersistenceAvailable: Boolean,
    val secureSecretStorageAvailable: Boolean,
    val secureMetadataStorageAvailable: Boolean,
    val mainnetAvailable: Boolean,
) {
    companion object {
        val StillDisabled = SkaldVaultV1VaultKdfCapability(
            kdfCalibrationAuthorized = false,
            kdfExecutionAuthorized = false,
            argon2idExecutionAvailable = false,
            argon2idCalibrationAvailable = false,
            finalKdfParametersApproved = false,
            androidCalibrationApproved = false,
            linuxCalibrationApproved = false,
            memoryCostApproved = false,
            iterationCostApproved = false,
            parallelismApproved = false,
            saltLengthApproved = false,
            outputLengthApproved = false,
            providerOperationAuthorized = false,
            runtimeRandomnessAuthorized = false,
            saltGenerationAvailable = false,
            passphraseInputAccepted = false,
            passphraseNormalized = false,
            passphraseEncoded = false,
            clearWipeApproved = false,
            redactionApproved = false,
            lockSessionApproved = false,
            productionProviderSelected = false,
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

data class SkaldVaultV1VaultKdfPolicyToken(
    val tokenId: String,
    val redacted: Boolean = true,
    val containsPassphrase: Boolean = false,
    val containsSaltBytes: Boolean = false,
    val containsKdfInput: Boolean = false,
    val containsKdfOutput: Boolean = false,
    val containsBenchmarkLogs: Boolean = false,
    val containsHostOrDeviceDetails: Boolean = false,
    val containsRandomBytes: Boolean = false,
    val containsEntropyBytes: Boolean = false,
    val containsKeyMaterial: Boolean = false,
    val containsProviderHandle: Boolean = false,
    val containsCiphertext: Boolean = false,
    val containsPlaintext: Boolean = false,
    val containsTagOrHeaderCommitmentBytes: Boolean = false,
    val containsRecordIdentifier: Boolean = false,
    val containsRootOrPathText: Boolean = false,
    val containsPayload: Boolean = false,
) {
    override fun toString(): String =
        "SkaldVaultV1VaultKdfPolicyToken(tokenId=$tokenId, redacted=$redacted)"
}

data class SkaldVaultV1VaultKdfPolicySummary(
    val policyId: String,
    val policyVersion: Int,
    val operationKinds: Set<SkaldVaultV1VaultKdfOperationKind>,
    val purposes: Set<SkaldVaultV1VaultKdfPurpose>,
    val parameterKinds: Set<SkaldVaultV1VaultKdfParameterKind>,
    val platformClasses: Set<SkaldVaultV1VaultKdfPlatformClass>,
    val requiredGates: Set<SkaldVaultV1VaultKdfRequiredGate>,
    val blockers: Set<SkaldVaultV1VaultKdfBlocker>,
    val warnings: Set<SkaldVaultV1VaultKdfWarning>,
    val capability: SkaldVaultV1VaultKdfCapability,
    val stillDisabled: Boolean,
) {
    val authorizesKdfCalibration: Boolean = false
    val authorizesKdfExecution: Boolean = false
    val approvesFinalParameters: Boolean = false
}

data class SkaldVaultV1VaultKdfCalibrationAuthorizationEvidence(
    val policyId: String,
    val policyVersion: Int,
    val source: SkaldVaultV1VaultKdfCalibrationAuthorizationSource,
    val status: SkaldVaultV1VaultKdfCalibrationAuthorizationStatus,
    val decision: SkaldVaultV1VaultKdfCalibrationAuthorizationDecision,
    val operationKind: SkaldVaultV1VaultKdfOperationKind?,
    val purpose: SkaldVaultV1VaultKdfPurpose?,
    val parameterKind: SkaldVaultV1VaultKdfParameterKind?,
    val platformClass: SkaldVaultV1VaultKdfPlatformClass?,
    val requiredGate: SkaldVaultV1VaultKdfRequiredGate?,
    val requiredGates: Set<SkaldVaultV1VaultKdfRequiredGate>,
    val blockers: Set<SkaldVaultV1VaultKdfBlocker>,
    val warnings: Set<SkaldVaultV1VaultKdfWarning>,
    val capability: SkaldVaultV1VaultKdfCapability,
    val policySummary: SkaldVaultV1VaultKdfPolicySummary,
    val policyTokenEvidence: SkaldVaultV1VaultKdfPolicyToken,
    val passphrasePolicyEvidenceConsumed: Boolean,
    val runtimeRandomnessAuthorizationEvidenceConsumed: Boolean,
    val providerOperationAuthorizationEvidenceConsumed: Boolean,
    val providerSelectionEvidenceConsumed: Boolean,
    val providerAcceptanceEvidenceConsumed: Boolean,
    val dependencyProbeEvidenceConsumed: Boolean,
    val argon2idCalibrationPolicyEvidenceConsumed: Boolean,
    val androidCalibrationEvidenceConsumed: Boolean,
    val encryptedVaultReadinessEvidenceConsumed: Boolean,
    val persistenceReadinessEvidenceConsumed: Boolean,
    val lockSessionLifecycleEvidenceConsumed: Boolean,
    val redactionLeakageEvidenceConsumed: Boolean,
    val clearWipeStrategyEvidenceConsumed: Boolean,
    val migrationCorruptionEvidenceConsumed: Boolean,
    val secureStorageEvidenceConsumed: Boolean,
    val secureMetadataEvidenceConsumed: Boolean,
    val kdfCalibrationAuthorizationBoundaryModeled: Boolean = true,
    val kdfCalibrationAuthorizationStillDisabled: Boolean = true,
    val kdfCalibrationAuthorizationBlocksAllOperations: Boolean = true,
    val kdfCalibrationAuthorizationDoesNotRunArgon2id: Boolean = true,
    val kdfCalibrationAuthorizationDoesNotRunCalibration: Boolean = true,
    val kdfCalibrationAuthorizationDoesNotApproveFinalParameters: Boolean = true,
    val kdfCalibrationAuthorizationDoesNotEnableUnlock: Boolean = true,
    val kdfCalibrationAuthorizationDoesNotEnablePersistence: Boolean = true,
    val kdfCalibrationAuthorizationDoesNotEnableProviderSelection: Boolean = true,
    val kdfCalibrationFailureVocabularyModeled: Boolean = true,
    val kdfCalibrationReady: Boolean = false,
    val kdfParameterApprovalReady: Boolean = false,
    val finalKdfParametersApproved: Boolean = false,
    val argon2idReady: Boolean = false,
    val argon2idExecutionReady: Boolean = false,
    val androidCalibrationApproved: Boolean = false,
    val linuxCalibrationApproved: Boolean = false,
    val kdfReady: Boolean = false,
    val vaultKeyDerivationReady: Boolean = false,
    val providerOperationAuthorized: Boolean = false,
    val providerCryptoReady: Boolean = false,
    val providerSelectable: Boolean = false,
    val vaultUnlockReady: Boolean = false,
    val vaultStorageAvailable: Boolean = false,
    val persistenceReady: Boolean = false,
    val vaultPersistenceReady: Boolean = false,
) {
    override fun toString(): String =
        "SkaldVaultV1VaultKdfCalibrationAuthorizationEvidence(" +
            "policyId=$policyId, status=$status, decision=$decision, " +
            "operationKind=$operationKind, purpose=$purpose, parameterKind=$parameterKind, " +
            "platformClass=$platformClass, requiredGate=$requiredGate, blockers=$blockers, " +
            "warnings=$warnings, capability=$capability, policyTokenEvidence=$policyTokenEvidence)"
}

enum class SkaldVaultV1VaultKdfFailureReason(val label: String) {
    EmptyEvidenceRejected("empty KDF evidence rejected"),
    ActualPassphraseRejected("actual passphrase input rejected"),
    PinRejected("PIN input rejected"),
    MnemonicRejected("mnemonic input rejected"),
    SeedBytesRejected("seed bytes rejected"),
    SaltBytesRejected("salt bytes rejected"),
    KdfInputBytesRejected("KDF input bytes rejected"),
    KdfOutputBytesRejected("KDF output bytes rejected"),
    Argon2idOutputRejected("Argon2id output rejected"),
    BenchmarkLogsRejected("benchmark logs rejected"),
    HostDetailsRejected("host details rejected"),
    DeviceIdentifierRejected("device identifier rejected"),
    RawRandomBytesRejected("raw random bytes rejected"),
    EntropyBytesRejected("entropy bytes rejected"),
    ProviderHandleRejected("provider handle rejected"),
    ProviderImplementationInstanceRejected("provider implementation instance rejected"),
    KeyMaterialRejected("key material rejected"),
    AeadKeyTagCiphertextPlaintextRejected("AEAD key/tag/ciphertext/plaintext rejected"),
    RecordBytesRejected("record bytes rejected"),
    RawPersistedContainerBytesRejected("raw persisted container bytes rejected"),
    ByteArrayInputRejected("ByteArray input rejected"),
    CharArrayInputRejected("CharArray input rejected"),
    RandomObjectInputRejected("randomness object input rejected"),
    RawAbsoluteLocationInputRejected("raw absolute location input rejected"),
    RawRelativeLocationInputRejected("raw relative location input rejected"),
    LinkLikeInputRejected("link-like input rejected"),
    PlatformObjectLikeInputRejected("platform object-like input rejected"),
    SecretMaterialRejected("secret material rejected"),
    WalletMaterialRejected("wallet material rejected"),
    BitcoinAddressLikeEvidenceRejected("Bitcoin address-like evidence rejected"),
    TransactionLikeEvidenceRejected("transaction-like evidence rejected"),
    TraversalRejected("traversal-like evidence rejected"),
    UnsupportedCharactersRejected("unsupported characters rejected"),
    RawKdfInputRejected("raw KDF input rejected"),
}

sealed class SkaldVaultV1VaultKdfCalibrationAuthorizationResult<out T> {
    data class Blocked<T>(
        val value: T,
    ) : SkaldVaultV1VaultKdfCalibrationAuthorizationResult<T>()

    data class Rejected(
        val reason: SkaldVaultV1VaultKdfFailureReason,
        val safeMessage: String,
    ) : SkaldVaultV1VaultKdfCalibrationAuthorizationResult<Nothing>()
}

class SkaldVaultV1VaultKdfCalibrationAuthorizationRequest private constructor(
    val source: SkaldVaultV1VaultKdfCalibrationAuthorizationSource,
    val operationKind: SkaldVaultV1VaultKdfOperationKind?,
    val purpose: SkaldVaultV1VaultKdfPurpose?,
    val parameterKind: SkaldVaultV1VaultKdfParameterKind?,
    val platformClass: SkaldVaultV1VaultKdfPlatformClass?,
    val requiredGate: SkaldVaultV1VaultKdfRequiredGate?,
    internal val rawCandidate: String?,
    internal val passphrasePolicyEvidence: SkaldVaultV1VaultPassphrasePolicyEvidence?,
    internal val runtimeRandomnessAuthorizationEvidence:
        SkaldVaultV1VaultRuntimeRandomnessAuthorizationEvidence?,
    internal val providerOperationAuthorizationEvidence:
        SkaldVaultV1VaultProviderOperationAuthorizationEvidence?,
    internal val providerSelectionResult: VaultCryptoProviderSelectionResult?,
    internal val providerAcceptanceAssessment: ProductionProviderAcceptanceAssessment?,
    internal val dependencyProbeResult: VaultCryptoDependencyProbeResult?,
    internal val argon2idCalibrationPolicy: Argon2idCalibrationPolicy?,
    internal val androidCalibrationAssessment: AndroidArgon2idBaselineAssessment?,
    internal val encryptedVaultReadiness: EncryptedVaultReadiness?,
    internal val persistenceReadinessEvidence: SkaldVaultV1VaultPersistenceReadinessEvidence?,
    internal val lockSessionLifecycleEvidence: SkaldVaultV1VaultLockSessionEvidence?,
    internal val redactionLeakageEvidence: SkaldVaultV1VaultRedactionEvidence?,
    internal val clearWipeStrategyEvidence: SkaldVaultV1VaultClearWipeEvidence?,
    internal val migrationCorruptionEvidence: SkaldVaultV1VaultMigrationCorruptionEvidence?,
    internal val secureStorageCapability: SecureStorageCapability?,
    internal val secureMetadataCapability: SecureMetadataPersistenceCapability?,
) {
    val rawCandidateRejected: Boolean
        get() = rawCandidate != null

    override fun toString(): String =
        "SkaldVaultV1VaultKdfCalibrationAuthorizationRequest(" +
            "source=$source, operationKind=$operationKind, purpose=$purpose, " +
            "parameterKind=$parameterKind, platformClass=$platformClass, " +
            "requiredGate=$requiredGate, rawCandidate=<redacted>)"

    companion object {
        fun noEvidence(): SkaldVaultV1VaultKdfCalibrationAuthorizationRequest =
            base(SkaldVaultV1VaultKdfCalibrationAuthorizationSource.NoEvidence)

        fun summary(): SkaldVaultV1VaultKdfCalibrationAuthorizationRequest =
            base(SkaldVaultV1VaultKdfCalibrationAuthorizationSource.PolicySummary)

        fun forOperationKind(
            operationKind: SkaldVaultV1VaultKdfOperationKind,
            purpose: SkaldVaultV1VaultKdfPurpose? = null,
            platformClass: SkaldVaultV1VaultKdfPlatformClass? = null,
        ): SkaldVaultV1VaultKdfCalibrationAuthorizationRequest =
            base(
                source = SkaldVaultV1VaultKdfCalibrationAuthorizationSource.OperationKind,
                operationKind = operationKind,
                purpose = purpose,
                platformClass = platformClass,
            )

        fun forPurpose(
            purpose: SkaldVaultV1VaultKdfPurpose,
        ): SkaldVaultV1VaultKdfCalibrationAuthorizationRequest =
            base(
                source = SkaldVaultV1VaultKdfCalibrationAuthorizationSource.Purpose,
                purpose = purpose,
            )

        fun forParameterKind(
            parameterKind: SkaldVaultV1VaultKdfParameterKind,
        ): SkaldVaultV1VaultKdfCalibrationAuthorizationRequest =
            base(
                source = SkaldVaultV1VaultKdfCalibrationAuthorizationSource.ParameterKind,
                parameterKind = parameterKind,
            )

        fun forPlatformClass(
            platformClass: SkaldVaultV1VaultKdfPlatformClass,
            purpose: SkaldVaultV1VaultKdfPurpose? = null,
        ): SkaldVaultV1VaultKdfCalibrationAuthorizationRequest =
            base(
                source = SkaldVaultV1VaultKdfCalibrationAuthorizationSource.PlatformClass,
                platformClass = platformClass,
                purpose = purpose,
            )

        fun forRequiredGate(
            requiredGate: SkaldVaultV1VaultKdfRequiredGate,
        ): SkaldVaultV1VaultKdfCalibrationAuthorizationRequest =
            base(
                source = SkaldVaultV1VaultKdfCalibrationAuthorizationSource.RequiredGate,
                requiredGate = requiredGate,
            )

        fun fromEvidence(
            passphrasePolicyEvidence: SkaldVaultV1VaultPassphrasePolicyEvidence? = null,
            runtimeRandomnessAuthorizationEvidence:
                SkaldVaultV1VaultRuntimeRandomnessAuthorizationEvidence? = null,
            providerOperationAuthorizationEvidence:
                SkaldVaultV1VaultProviderOperationAuthorizationEvidence? = null,
            providerSelectionResult: VaultCryptoProviderSelectionResult? = null,
            providerAcceptanceAssessment: ProductionProviderAcceptanceAssessment? = null,
            dependencyProbeResult: VaultCryptoDependencyProbeResult? = null,
            argon2idCalibrationPolicy: Argon2idCalibrationPolicy? = null,
            androidCalibrationAssessment: AndroidArgon2idBaselineAssessment? = null,
            encryptedVaultReadiness: EncryptedVaultReadiness? = null,
            persistenceReadinessEvidence: SkaldVaultV1VaultPersistenceReadinessEvidence? = null,
            lockSessionLifecycleEvidence: SkaldVaultV1VaultLockSessionEvidence? = null,
            redactionLeakageEvidence: SkaldVaultV1VaultRedactionEvidence? = null,
            clearWipeStrategyEvidence: SkaldVaultV1VaultClearWipeEvidence? = null,
            migrationCorruptionEvidence: SkaldVaultV1VaultMigrationCorruptionEvidence? = null,
            secureStorageCapability: SecureStorageCapability? = null,
            secureMetadataCapability: SecureMetadataPersistenceCapability? = null,
            operationKind: SkaldVaultV1VaultKdfOperationKind? = null,
            purpose: SkaldVaultV1VaultKdfPurpose? = null,
            parameterKind: SkaldVaultV1VaultKdfParameterKind? = null,
            platformClass: SkaldVaultV1VaultKdfPlatformClass? = null,
        ): SkaldVaultV1VaultKdfCalibrationAuthorizationRequest =
            base(
                source = SkaldVaultV1VaultKdfCalibrationAuthorizationSource.ComposedTypedEvidence,
                operationKind = operationKind,
                purpose = purpose,
                parameterKind = parameterKind,
                platformClass = platformClass,
                passphrasePolicyEvidence = passphrasePolicyEvidence,
                runtimeRandomnessAuthorizationEvidence = runtimeRandomnessAuthorizationEvidence,
                providerOperationAuthorizationEvidence = providerOperationAuthorizationEvidence,
                providerSelectionResult = providerSelectionResult,
                providerAcceptanceAssessment = providerAcceptanceAssessment,
                dependencyProbeResult = dependencyProbeResult,
                argon2idCalibrationPolicy = argon2idCalibrationPolicy,
                androidCalibrationAssessment = androidCalibrationAssessment,
                encryptedVaultReadiness = encryptedVaultReadiness,
                persistenceReadinessEvidence = persistenceReadinessEvidence,
                lockSessionLifecycleEvidence = lockSessionLifecycleEvidence,
                redactionLeakageEvidence = redactionLeakageEvidence,
                clearWipeStrategyEvidence = clearWipeStrategyEvidence,
                migrationCorruptionEvidence = migrationCorruptionEvidence,
                secureStorageCapability = secureStorageCapability,
                secureMetadataCapability = secureMetadataCapability,
            )

        fun rawKdfCandidate(
            rawCandidate: String?,
        ): SkaldVaultV1VaultKdfCalibrationAuthorizationRequest =
            base(
                source = SkaldVaultV1VaultKdfCalibrationAuthorizationSource.RawKdfCandidate,
                rawCandidate = rawCandidate,
            )

        private fun base(
            source: SkaldVaultV1VaultKdfCalibrationAuthorizationSource,
            operationKind: SkaldVaultV1VaultKdfOperationKind? = null,
            purpose: SkaldVaultV1VaultKdfPurpose? = null,
            parameterKind: SkaldVaultV1VaultKdfParameterKind? = null,
            platformClass: SkaldVaultV1VaultKdfPlatformClass? = null,
            requiredGate: SkaldVaultV1VaultKdfRequiredGate? = null,
            rawCandidate: String? = null,
            passphrasePolicyEvidence: SkaldVaultV1VaultPassphrasePolicyEvidence? = null,
            runtimeRandomnessAuthorizationEvidence:
                SkaldVaultV1VaultRuntimeRandomnessAuthorizationEvidence? = null,
            providerOperationAuthorizationEvidence:
                SkaldVaultV1VaultProviderOperationAuthorizationEvidence? = null,
            providerSelectionResult: VaultCryptoProviderSelectionResult? = null,
            providerAcceptanceAssessment: ProductionProviderAcceptanceAssessment? = null,
            dependencyProbeResult: VaultCryptoDependencyProbeResult? = null,
            argon2idCalibrationPolicy: Argon2idCalibrationPolicy? = null,
            androidCalibrationAssessment: AndroidArgon2idBaselineAssessment? = null,
            encryptedVaultReadiness: EncryptedVaultReadiness? = null,
            persistenceReadinessEvidence: SkaldVaultV1VaultPersistenceReadinessEvidence? = null,
            lockSessionLifecycleEvidence: SkaldVaultV1VaultLockSessionEvidence? = null,
            redactionLeakageEvidence: SkaldVaultV1VaultRedactionEvidence? = null,
            clearWipeStrategyEvidence: SkaldVaultV1VaultClearWipeEvidence? = null,
            migrationCorruptionEvidence: SkaldVaultV1VaultMigrationCorruptionEvidence? = null,
            secureStorageCapability: SecureStorageCapability? = null,
            secureMetadataCapability: SecureMetadataPersistenceCapability? = null,
        ) = SkaldVaultV1VaultKdfCalibrationAuthorizationRequest(
            source = source,
            operationKind = operationKind,
            purpose = purpose,
            parameterKind = parameterKind,
            platformClass = platformClass,
            requiredGate = requiredGate,
            rawCandidate = rawCandidate,
            passphrasePolicyEvidence = passphrasePolicyEvidence,
            runtimeRandomnessAuthorizationEvidence = runtimeRandomnessAuthorizationEvidence,
            providerOperationAuthorizationEvidence = providerOperationAuthorizationEvidence,
            providerSelectionResult = providerSelectionResult,
            providerAcceptanceAssessment = providerAcceptanceAssessment,
            dependencyProbeResult = dependencyProbeResult,
            argon2idCalibrationPolicy = argon2idCalibrationPolicy,
            androidCalibrationAssessment = androidCalibrationAssessment,
            encryptedVaultReadiness = encryptedVaultReadiness,
            persistenceReadinessEvidence = persistenceReadinessEvidence,
            lockSessionLifecycleEvidence = lockSessionLifecycleEvidence,
            redactionLeakageEvidence = redactionLeakageEvidence,
            clearWipeStrategyEvidence = clearWipeStrategyEvidence,
            migrationCorruptionEvidence = migrationCorruptionEvidence,
            secureStorageCapability = secureStorageCapability,
            secureMetadataCapability = secureMetadataCapability,
        )
    }
}

object SkaldVaultV1KdfCalibrationAuthorizationPolicy :
    SkaldVaultV1KdfCalibrationAuthorizationBoundary {
    const val POLICY_ID: String = "skald-vault-v1-kdf-calibration-authorization-boundary-v1"
    const val POLICY_VERSION: Int = 1

    fun currentPolicySummary(): SkaldVaultV1VaultKdfPolicySummary =
        SkaldVaultV1VaultKdfPolicySummary(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            operationKinds = SkaldVaultV1VaultKdfOperationKind.entries.toSet(),
            purposes = SkaldVaultV1VaultKdfPurpose.entries.toSet(),
            parameterKinds = SkaldVaultV1VaultKdfParameterKind.entries.toSet(),
            platformClasses = SkaldVaultV1VaultKdfPlatformClass.entries.toSet(),
            requiredGates = SkaldVaultV1VaultKdfRequiredGate.entries.toSet(),
            blockers = SkaldVaultV1VaultKdfBlocker.entries.toSet(),
            warnings = SkaldVaultV1VaultKdfWarning.entries.toSet(),
            capability = SkaldVaultV1VaultKdfCapability.StillDisabled,
            stillDisabled = true,
        )

    override fun evaluate(
        request: SkaldVaultV1VaultKdfCalibrationAuthorizationRequest,
    ): SkaldVaultV1VaultKdfCalibrationAuthorizationResult<SkaldVaultV1VaultKdfCalibrationAuthorizationEvidence> {
        if (request.source == SkaldVaultV1VaultKdfCalibrationAuthorizationSource.RawKdfCandidate) {
            return SkaldVaultV1VaultKdfCalibrationAuthorizationResult.Rejected(
                reason = classifyRawCandidate(request.rawCandidate),
                safeMessage = "Raw KDF authorization candidates are rejected; use typed policy evidence only.",
            )
        }

        return SkaldVaultV1VaultKdfCalibrationAuthorizationResult.Blocked(
            value = evidence(request),
        )
    }

    private fun evidence(
        request: SkaldVaultV1VaultKdfCalibrationAuthorizationRequest,
    ): SkaldVaultV1VaultKdfCalibrationAuthorizationEvidence =
        SkaldVaultV1VaultKdfCalibrationAuthorizationEvidence(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            source = request.source,
            status = statusFor(request.source),
            decision = decisionFor(request),
            operationKind = request.operationKind,
            purpose = request.purpose,
            parameterKind = request.parameterKind,
            platformClass = request.platformClass,
            requiredGate = request.requiredGate,
            requiredGates = requiredGatesFor(request),
            blockers = blockersFor(request),
            warnings = SkaldVaultV1VaultKdfWarning.entries.toSet(),
            capability = SkaldVaultV1VaultKdfCapability.StillDisabled,
            policySummary = currentPolicySummary(),
            policyTokenEvidence = SkaldVaultV1VaultKdfPolicyToken(
                tokenId = "$POLICY_ID:current:redacted",
            ),
            passphrasePolicyEvidenceConsumed = request.passphrasePolicyEvidence != null,
            runtimeRandomnessAuthorizationEvidenceConsumed =
                request.runtimeRandomnessAuthorizationEvidence != null,
            providerOperationAuthorizationEvidenceConsumed =
                request.providerOperationAuthorizationEvidence != null,
            providerSelectionEvidenceConsumed = request.providerSelectionResult != null,
            providerAcceptanceEvidenceConsumed = request.providerAcceptanceAssessment != null,
            dependencyProbeEvidenceConsumed = request.dependencyProbeResult != null,
            argon2idCalibrationPolicyEvidenceConsumed = request.argon2idCalibrationPolicy != null,
            androidCalibrationEvidenceConsumed = request.androidCalibrationAssessment != null,
            encryptedVaultReadinessEvidenceConsumed = request.encryptedVaultReadiness != null,
            persistenceReadinessEvidenceConsumed = request.persistenceReadinessEvidence != null,
            lockSessionLifecycleEvidenceConsumed = request.lockSessionLifecycleEvidence != null,
            redactionLeakageEvidenceConsumed = request.redactionLeakageEvidence != null,
            clearWipeStrategyEvidenceConsumed = request.clearWipeStrategyEvidence != null,
            migrationCorruptionEvidenceConsumed = request.migrationCorruptionEvidence != null,
            secureStorageEvidenceConsumed = request.secureStorageCapability != null,
            secureMetadataEvidenceConsumed = request.secureMetadataCapability != null,
        )

    private fun statusFor(
        source: SkaldVaultV1VaultKdfCalibrationAuthorizationSource,
    ): SkaldVaultV1VaultKdfCalibrationAuthorizationStatus =
        when (source) {
            SkaldVaultV1VaultKdfCalibrationAuthorizationSource.NoEvidence ->
                SkaldVaultV1VaultKdfCalibrationAuthorizationStatus.NoEvidenceAvailable
            SkaldVaultV1VaultKdfCalibrationAuthorizationSource.PolicySummary ->
                SkaldVaultV1VaultKdfCalibrationAuthorizationStatus.PolicySummaryModeled
            SkaldVaultV1VaultKdfCalibrationAuthorizationSource.OperationKind ->
                SkaldVaultV1VaultKdfCalibrationAuthorizationStatus.OperationKindModeled
            SkaldVaultV1VaultKdfCalibrationAuthorizationSource.Purpose ->
                SkaldVaultV1VaultKdfCalibrationAuthorizationStatus.PurposeModeled
            SkaldVaultV1VaultKdfCalibrationAuthorizationSource.ParameterKind ->
                SkaldVaultV1VaultKdfCalibrationAuthorizationStatus.ParameterKindModeled
            SkaldVaultV1VaultKdfCalibrationAuthorizationSource.PlatformClass ->
                SkaldVaultV1VaultKdfCalibrationAuthorizationStatus.PlatformClassModeled
            SkaldVaultV1VaultKdfCalibrationAuthorizationSource.RequiredGate ->
                SkaldVaultV1VaultKdfCalibrationAuthorizationStatus.RequiredGateModeled
            SkaldVaultV1VaultKdfCalibrationAuthorizationSource.ComposedTypedEvidence ->
                SkaldVaultV1VaultKdfCalibrationAuthorizationStatus.KdfCalibrationBlockedStillDisabled
            SkaldVaultV1VaultKdfCalibrationAuthorizationSource.RawKdfCandidate ->
                SkaldVaultV1VaultKdfCalibrationAuthorizationStatus.RawCandidateRejected
        }

    private fun decisionFor(
        request: SkaldVaultV1VaultKdfCalibrationAuthorizationRequest,
    ): SkaldVaultV1VaultKdfCalibrationAuthorizationDecision =
        when {
            request.operationKind == SkaldVaultV1VaultKdfOperationKind.MainnetParameterReview ||
                request.purpose == SkaldVaultV1VaultKdfPurpose.MainnetValidation ||
                request.platformClass?.mainnetBlocked == true ->
                SkaldVaultV1VaultKdfCalibrationAuthorizationDecision.RejectOperation
            request.purpose == SkaldVaultV1VaultKdfPurpose.TestOnlyDeterministicVector ||
                request.operationKind == SkaldVaultV1VaultKdfOperationKind.TestVectorParameterReview ||
                request.platformClass?.testOnlyProfile == true ->
                SkaldVaultV1VaultKdfCalibrationAuthorizationDecision.TestOnlyScopeRejectedForProduction
            request.platformClass?.unsupported == true ->
                SkaldVaultV1VaultKdfCalibrationAuthorizationDecision.UnsupportedFailClosed
            request.source == SkaldVaultV1VaultKdfCalibrationAuthorizationSource.NoEvidence ||
                request.source == SkaldVaultV1VaultKdfCalibrationAuthorizationSource.ComposedTypedEvidence ->
                SkaldVaultV1VaultKdfCalibrationAuthorizationDecision.BlockedFailClosed
            else -> SkaldVaultV1VaultKdfCalibrationAuthorizationDecision.Unauthorized
        }

    private fun requiredGatesFor(
        request: SkaldVaultV1VaultKdfCalibrationAuthorizationRequest,
    ): Set<SkaldVaultV1VaultKdfRequiredGate> =
        buildSet {
            addAll(SkaldVaultV1VaultKdfRequiredGate.entries)
            request.requiredGate?.let(::add)
        }

    private fun blockersFor(
        request: SkaldVaultV1VaultKdfCalibrationAuthorizationRequest,
    ): Set<SkaldVaultV1VaultKdfBlocker> =
        buildSet {
            add(SkaldVaultV1VaultKdfBlocker.KdfCalibrationAuthorizationStillDisabled)
            add(SkaldVaultV1VaultKdfBlocker.FinalKdfCalibrationMissing)
            add(SkaldVaultV1VaultKdfBlocker.FinalParameterApprovalMissing)
            add(SkaldVaultV1VaultKdfBlocker.Argon2idExecutionUnavailable)
            add(SkaldVaultV1VaultKdfBlocker.Argon2idCalibrationUnavailable)
            add(SkaldVaultV1VaultKdfBlocker.ParameterBoundsReviewMissing)
            add(SkaldVaultV1VaultKdfBlocker.MemoryCostReviewMissing)
            add(SkaldVaultV1VaultKdfBlocker.IterationCostReviewMissing)
            add(SkaldVaultV1VaultKdfBlocker.ParallelismReviewMissing)
            add(SkaldVaultV1VaultKdfBlocker.SaltLengthReviewMissing)
            add(SkaldVaultV1VaultKdfBlocker.OutputLengthReviewMissing)
            add(SkaldVaultV1VaultKdfBlocker.ProviderOperationAuthorizationBlocked)
            add(SkaldVaultV1VaultKdfBlocker.RuntimeRandomnessAuthorizationBlocked)
            add(SkaldVaultV1VaultKdfBlocker.DisabledProviderSelected)
            add(SkaldVaultV1VaultKdfBlocker.ProductionProviderSelectableFalse)
            add(SkaldVaultV1VaultKdfBlocker.ProviderKatApprovalMissing)
            add(SkaldVaultV1VaultKdfBlocker.PassphrasePolicyBlocked)
            add(SkaldVaultV1VaultKdfBlocker.LockSessionLifecycleBlocked)
            add(SkaldVaultV1VaultKdfBlocker.PersistenceReadinessBlocked)
            add(SkaldVaultV1VaultKdfBlocker.ClearWipeStrategyBlocked)
            add(SkaldVaultV1VaultKdfBlocker.RedactionLeakageUnsafe)
            add(SkaldVaultV1VaultKdfBlocker.SecureSecretStorageUnavailable)
            add(SkaldVaultV1VaultKdfBlocker.SecureMetadataStorageUnavailable)
            add(SkaldVaultV1VaultKdfBlocker.MigrationCompatibilityMissing)
            add(SkaldVaultV1VaultKdfBlocker.WarningOnlyEvidenceRejected)
            add(SkaldVaultV1VaultKdfBlocker.UserConsentOverrideRejected)

            when (request.source) {
                SkaldVaultV1VaultKdfCalibrationAuthorizationSource.NoEvidence ->
                    add(SkaldVaultV1VaultKdfBlocker.NoEvidenceAvailable)
                else -> Unit
            }
            if (request.platformClass?.androidRuntime == true ||
                request.operationKind == SkaldVaultV1VaultKdfOperationKind.AndroidCalibrationCaptureReview ||
                request.purpose == SkaldVaultV1VaultKdfPurpose.CalibrateAndroidClass
            ) {
                add(SkaldVaultV1VaultKdfBlocker.AndroidCalibrationMissing)
            }
            if (request.platformClass?.linuxRuntime == true ||
                request.operationKind == SkaldVaultV1VaultKdfOperationKind.LinuxCalibrationReview ||
                request.operationKind == SkaldVaultV1VaultKdfOperationKind.DesktopCalibrationReview ||
                request.purpose == SkaldVaultV1VaultKdfPurpose.CalibrateLinuxDesktopClass
            ) {
                add(SkaldVaultV1VaultKdfBlocker.LinuxCalibrationMissing)
            }
            if (
                request.platformClass?.unsupported == true
            ) {
                add(SkaldVaultV1VaultKdfBlocker.UnknownUnsupportedPlatformClass)
            }
            if (
                request.platformClass?.testOnlyProfile == true ||
                    request.operationKind == SkaldVaultV1VaultKdfOperationKind.TestVectorParameterReview ||
                    request.purpose == SkaldVaultV1VaultKdfPurpose.TestOnlyDeterministicVector
            ) {
                add(SkaldVaultV1VaultKdfBlocker.TestVectorProfileRejectedForProduction)
            }
            if (
                request.operationKind == SkaldVaultV1VaultKdfOperationKind.MainnetParameterReview ||
                    request.purpose == SkaldVaultV1VaultKdfPurpose.MainnetValidation ||
                    request.platformClass?.mainnetBlocked == true
            ) {
                add(SkaldVaultV1VaultKdfBlocker.MainnetUnavailable)
            }
        }

    private fun classifyRawCandidate(candidate: String?): SkaldVaultV1VaultKdfFailureReason {
        val value = candidate?.trim().orEmpty()
        val lower = value.lowercase()
        if (value.isBlank()) return SkaldVaultV1VaultKdfFailureReason.EmptyEvidenceRejected
        if (".." in value) return SkaldVaultV1VaultKdfFailureReason.TraversalRejected
        if (value.any { it == '#' || it == '\u0000' }) {
            return SkaldVaultV1VaultKdfFailureReason.UnsupportedCharactersRejected
        }
        if (value.startsWith("/") || Regex("""^[A-Za-z]:[\\/].*""").matches(value)) {
            return SkaldVaultV1VaultKdfFailureReason.RawAbsoluteLocationInputRejected
        }
        if ("://" in value) return SkaldVaultV1VaultKdfFailureReason.LinkLikeInputRejected
        if ("/" in value || "\\" in value) {
            return SkaldVaultV1VaultKdfFailureReason.RawRelativeLocationInputRejected
        }
        if (Regex("""^[0-9a-fA-F]{64}$""").matches(value)) {
            return SkaldVaultV1VaultKdfFailureReason.TransactionLikeEvidenceRejected
        }
        if (Regex("""^(bc1|tb1|bcrt1)[a-z0-9]{20,}$""").matches(lower)) {
            return SkaldVaultV1VaultKdfFailureReason.BitcoinAddressLikeEvidenceRejected
        }
        if (lower.startsWith("nsec") || lower.startsWith("xprv") || lower.startsWith("tprv") ||
            Regex("""^[KL5][1-9A-HJ-NP-Za-km-z]{50,51}$""").matches(value)
        ) {
            return SkaldVaultV1VaultKdfFailureReason.WalletMaterialRejected
        }

        return when {
            "passphrase" in lower -> SkaldVaultV1VaultKdfFailureReason.ActualPassphraseRejected
            "pin" in lower -> SkaldVaultV1VaultKdfFailureReason.PinRejected
            "mnemonic" in lower || "seed-phrase" in lower ->
                SkaldVaultV1VaultKdfFailureReason.MnemonicRejected
            "seed-bytes" in lower -> SkaldVaultV1VaultKdfFailureReason.SeedBytesRejected
            "salt-bytes" in lower -> SkaldVaultV1VaultKdfFailureReason.SaltBytesRejected
            "kdf-input" in lower -> SkaldVaultV1VaultKdfFailureReason.KdfInputBytesRejected
            "kdf-output" in lower -> SkaldVaultV1VaultKdfFailureReason.KdfOutputBytesRejected
            "argon2id-output" in lower -> SkaldVaultV1VaultKdfFailureReason.Argon2idOutputRejected
            "benchmark-log" in lower -> SkaldVaultV1VaultKdfFailureReason.BenchmarkLogsRejected
            "host-details" in lower || "host-memory" in lower || "host-cpu" in lower ->
                SkaldVaultV1VaultKdfFailureReason.HostDetailsRejected
            "device-identifier" in lower || "serial" in lower ->
                SkaldVaultV1VaultKdfFailureReason.DeviceIdentifierRejected
            "random-bytes" in lower -> SkaldVaultV1VaultKdfFailureReason.RawRandomBytesRejected
            "entropy-bytes" in lower -> SkaldVaultV1VaultKdfFailureReason.EntropyBytesRejected
            "provider-handle" in lower -> SkaldVaultV1VaultKdfFailureReason.ProviderHandleRejected
            "crypto-provider-instance" in lower ->
                SkaldVaultV1VaultKdfFailureReason.ProviderImplementationInstanceRejected
            "key-material" in lower || "key-bytes" in lower || "private-key" in lower ->
                SkaldVaultV1VaultKdfFailureReason.KeyMaterialRejected
            "aead-key" in lower || "aead-tag" in lower || "ciphertext" in lower || "plaintext" in lower ->
                SkaldVaultV1VaultKdfFailureReason.AeadKeyTagCiphertextPlaintextRejected
            "record-bytes" in lower || "manifest-bytes" in lower || "storage-index-bytes" in lower ->
                SkaldVaultV1VaultKdfFailureReason.RecordBytesRejected
            "container-bytes" in lower || "persisted-container" in lower ->
                SkaldVaultV1VaultKdfFailureReason.RawPersistedContainerBytesRejected
            "bytearray" in lower -> SkaldVaultV1VaultKdfFailureReason.ByteArrayInputRejected
            "chararray" in lower -> SkaldVaultV1VaultKdfFailureReason.CharArrayInputRejected
            "rng-object" in lower || "random-object" in lower ->
                SkaldVaultV1VaultKdfFailureReason.RandomObjectInputRejected
            "file-object" in lower || "path-object" in lower || "settings-value" in lower ->
                SkaldVaultV1VaultKdfFailureReason.PlatformObjectLikeInputRejected
            "secret" in lower || "credential" in lower ->
                SkaldVaultV1VaultKdfFailureReason.SecretMaterialRejected
            else -> SkaldVaultV1VaultKdfFailureReason.RawKdfInputRejected
        }
    }
}
