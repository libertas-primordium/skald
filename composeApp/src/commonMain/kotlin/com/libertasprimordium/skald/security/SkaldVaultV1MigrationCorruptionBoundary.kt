package com.libertasprimordium.skald.security

interface SkaldVaultV1MigrationCorruptionBoundary {
    fun evaluate(
        request: SkaldVaultV1VaultMigrationCorruptionRequest,
    ): SkaldVaultV1VaultMigrationCorruptionResult<SkaldVaultV1VaultMigrationCorruptionEvidence>
}

enum class SkaldVaultV1VaultMigrationCorruptionSource(val label: String) {
    NoEvidence("no migration/corruption evidence"),
    PolicySummary("migration/corruption policy summary"),
    EvidenceKind("migration/corruption evidence kind"),
    FailureClass("migration/corruption failure class"),
    RequiredAction("migration/corruption required action"),
    ComposedTypedEvidence("composed typed migration/corruption evidence"),
    RawStorageCandidate("raw storage candidate"),
}

enum class SkaldVaultV1VaultMigrationCorruptionStatus(val label: String) {
    NoEvidenceAvailable("no migration/corruption evidence available"),
    PolicySummaryModeled("migration/corruption policy summary modeled"),
    EvidenceKindModeled("migration/corruption evidence kind modeled"),
    FailureClassModeled("migration/corruption failure class modeled"),
    RequiredActionModeled("migration/corruption required action modeled"),
    MigrationCorruptionBlockedStillDisabled("migration/corruption remains blocked and still disabled"),
    RawCandidateRejected("raw migration/corruption candidate rejected"),
}

enum class SkaldVaultV1VaultMigrationCorruptionDecision(
    val migrationAllowed: Boolean,
    val repairAllowed: Boolean,
    val quarantineAllowed: Boolean,
    val recordReadWriteAllowed: Boolean,
    val providerCryptoAllowed: Boolean,
    val persistenceAllowed: Boolean,
) {
    BlockedFailClosed(
        migrationAllowed = false,
        repairAllowed = false,
        quarantineAllowed = false,
        recordReadWriteAllowed = false,
        providerCryptoAllowed = false,
        persistenceAllowed = false,
    ),
    ModelOnlyClassification(
        migrationAllowed = false,
        repairAllowed = false,
        quarantineAllowed = false,
        recordReadWriteAllowed = false,
        providerCryptoAllowed = false,
        persistenceAllowed = false,
    ),
    RejectOperation(
        migrationAllowed = false,
        repairAllowed = false,
        quarantineAllowed = false,
        recordReadWriteAllowed = false,
        providerCryptoAllowed = false,
        persistenceAllowed = false,
    ),
    RequireManualReview(
        migrationAllowed = false,
        repairAllowed = false,
        quarantineAllowed = false,
        recordReadWriteAllowed = false,
        providerCryptoAllowed = false,
        persistenceAllowed = false,
    ),
    UnsupportedFailClosed(
        migrationAllowed = false,
        repairAllowed = false,
        quarantineAllowed = false,
        recordReadWriteAllowed = false,
        providerCryptoAllowed = false,
        persistenceAllowed = false,
    ),
}

enum class SkaldVaultV1VaultMigrationCorruptionEvidenceKind(val label: String) {
    VaultContainerHeaderEvidence("vault container header evidence"),
    ContainerVersionEvidence("container version evidence"),
    ProviderSuiteIdEvidence("provider suite id evidence"),
    KdfParameterEvidence("KDF parameter evidence"),
    HeaderCommitmentEvidence("header commitment evidence"),
    AadContractEvidence("AAD contract evidence"),
    ManifestVersionEvidence("manifest version evidence"),
    ManifestCurrentRecordReferenceEvidence("manifest current-record reference evidence"),
    StorageIndexVersionEvidence("storage-index version evidence"),
    StorageIndexRecordReferenceEvidence("storage-index record reference evidence"),
    RecordDescriptorEvidence("record descriptor evidence"),
    RecordPurposeEvidence("record purpose evidence"),
    RecordNonceCounterEvidence("record nonce/counter evidence"),
    RecordCiphertextPlaceholderEvidence("record ciphertext placeholder evidence"),
    RecordAuthenticationFailureEvidence("record authentication failure evidence"),
    StaleRecordEvidence("stale-record evidence"),
    RollbackSuspicionEvidence("rollback suspicion evidence"),
    InterruptedWriteEvidence("interrupted-write evidence"),
    PartialManifestWriteEvidence("partial-manifest-write evidence"),
    PartialStorageIndexWriteEvidence("partial-storage-index-write evidence"),
    OrphanRecordEvidence("orphan-record evidence"),
    MissingRecordEvidence("missing-record evidence"),
    DuplicateRecordEvidence("duplicate-record evidence"),
    ConflictingRecordEvidence("conflicting-record evidence"),
    UnknownFutureVersionEvidence("unknown-future-version evidence"),
    UnsupportedOldVersionEvidence("unsupported-old-version evidence"),
    MigrationRequiredEvidence("migration-required evidence"),
    MigrationPlanEvidence("migration-plan evidence"),
    CorruptionDetectedEvidence("corruption-detected evidence"),
    RecoveryAttemptEvidence("recovery-attempt evidence"),
    QuarantineRequiredEvidence("quarantine-required evidence"),
    ManualReviewRequiredEvidence("manual-review-required evidence"),
    FailClosedRequiredEvidence("fail-closed-required evidence"),
}

enum class SkaldVaultV1VaultMigrationCorruptionEventKind(val label: String) {
    ContainerOpenRequested("container open requested"),
    ManifestReadRequested("manifest read requested"),
    StorageIndexReadRequested("storage-index read requested"),
    RecordReadRequested("record read requested"),
    RecordWriteRequested("record write requested"),
    VersionMismatchDetected("version mismatch detected"),
    HeaderCommitmentMismatchDetected("header commitment mismatch detected"),
    AuthenticationFailureDetected("authentication failure detected"),
    StaleRecordDetected("stale record detected"),
    RollbackSuspicionDetected("rollback suspicion detected"),
    PartialWriteSuspected("partial write suspected"),
    CrashRecoveryRequested("crash recovery requested"),
    MigrationRequested("migration requested"),
    RepairRequested("repair requested"),
    QuarantineRequested("quarantine requested"),
    ManualReviewRequested("manual review requested"),
    SupportDiagnosticRequested("support diagnostic requested"),
    MainnetRequestAttempted("mainnet request attempted"),
}

enum class SkaldVaultV1VaultMigrationCorruptionFailureClass(val label: String) {
    NoEvidence("no evidence"),
    UnsupportedVersion("unsupported version"),
    UnknownFutureVersion("unknown future version"),
    MalformedHeader("malformed header"),
    HeaderCommitmentMismatch("header commitment mismatch"),
    ProviderSuiteMismatch("provider suite mismatch"),
    KdfParameterMismatch("KDF parameter mismatch"),
    AadMismatch("AAD mismatch"),
    ManifestMissing("manifest missing"),
    ManifestMalformed("manifest malformed"),
    StorageIndexMissing("storage index missing"),
    StorageIndexMalformed("storage index malformed"),
    RecordDescriptorMalformed("record descriptor malformed"),
    RecordMissing("record missing"),
    RecordOrphaned("record orphaned"),
    DuplicateRecord("duplicate record"),
    ConflictingRecord("conflicting record"),
    StaleRecord("stale record"),
    RollbackSuspected("rollback suspected"),
    PartialWriteSuspected("partial write suspected"),
    CrashRecoveryRequired("crash recovery required"),
    AuthenticationFailed("authentication failed"),
    CorruptionDetected("corruption detected"),
    MigrationRequired("migration required"),
    MigrationUnsafe("migration unsafe"),
    QuarantineRequired("quarantine required"),
    SecureStorageUnavailable("secure storage unavailable"),
    ProviderUnavailable("provider unavailable"),
    StorageServiceDisabled("storage service disabled"),
    RedactionUnsafe("redaction unsafe"),
    ClearWipeStrategyUnavailable("clear/wipe strategy unavailable"),
    ManualReviewRequired("manual review required"),
    MainnetBlocked("mainnet blocked"),
}

enum class SkaldVaultV1VaultMigrationCorruptionRequiredAction(val label: String) {
    FailClosed("fail closed"),
    RejectOperation("reject operation"),
    RequireManualReview("require manual review"),
    RequireBackupBeforeMigration("require backup before migration"),
    RequireDryRunMigrationFirst("require dry-run migration first"),
    RequireProviderValidation("require provider validation"),
    RequireStorageSafetyValidation("require storage safety validation"),
    RequireManifestValidation("require manifest validation"),
    RequireStorageIndexValidation("require storage-index validation"),
    RequireRecordQuarantine("require record quarantine"),
    RequireStaleRecordReview("require stale-record review"),
    RequireRollbackReview("require rollback review"),
    RequireCorruptionReview("require corruption review"),
    RequireClearWipeHandling("require clear/wipe handling"),
    RequireRedactedDiagnosticOnly("require redacted diagnostic only"),
    RequireUserVisibleWarning("require user-visible warning"),
    RequireReleaseHardeningReview("require release-hardening review"),
    UnsupportedNoActionAvailable("unsupported/no action available"),
}

enum class SkaldVaultV1VaultMigrationCorruptionSeverity(val label: String) {
    InformationalModelOnly("informational model-only"),
    WarningFailClosed("warning; fail closed"),
    BlockingFailure("blocking failure"),
    CriticalManualReview("critical manual review"),
    UnsupportedFailClosed("unsupported; fail closed"),
}

enum class SkaldVaultV1VaultMigrationCorruptionLimitation(val label: String) {
    ModelOnlyNoRuntimeMigration("model only; no runtime migration"),
    DoesNotParseRealStorage("real persisted storage is not parsed"),
    DoesNotRepairStorage("storage repair is not implemented"),
    DoesNotRunMigration("migration execution is not implemented"),
    DoesNotRunMigrationDryRun("migration dry-run execution is not implemented"),
    DoesNotQuarantineRecords("record quarantine is not implemented"),
    DoesNotRecoverRecords("record recovery is not implemented"),
    DoesNotVerifyAeadAuthentication("real AEAD authentication is not verified"),
    DoesNotVerifyHeaderCommitment("real header commitments are not verified"),
    RollbackResistanceUnproven("rollback resistance is unproven"),
    CrashRecoveryUnproven("crash recovery is unproven"),
    RedactedDiagnosticsOnly("diagnostics must remain redacted evidence only"),
    NoFilesystemStorageOrSettings("filesystem, storage, and Settings persistence are absent"),
    NoUnlockPersistenceOrProviderSelection("unlock, persistence, and provider selection remain unavailable"),
}

enum class SkaldVaultV1VaultMigrationCorruptionBlocker(val label: String) {
    MigrationCorruptionBoundaryStillDisabled("migration/corruption boundary remains still-disabled"),
    NoEvidenceAvailable("no migration/corruption evidence available"),
    MigrationImplementationMissing("migration implementation missing"),
    MigrationDryRunMissing("migration dry-run implementation missing"),
    RepairImplementationMissing("repair implementation missing"),
    QuarantineImplementationMissing("quarantine implementation missing"),
    CrashRecoveryImplementationMissing("crash recovery implementation missing"),
    RollbackProtectionUnavailable("rollback protection unavailable"),
    RealCorruptionDetectionUnavailable("real corruption detection unavailable"),
    HeaderCommitmentVerificationUnavailable("header commitment verification unavailable"),
    AeadAuthenticationUnavailable("AEAD authentication unavailable"),
    RecordReadWriteUnavailable("record read/write unavailable"),
    ManifestReadWriteUnavailable("manifest read/write unavailable"),
    StorageIndexReadWriteUnavailable("storage-index read/write unavailable"),
    ProviderCryptoUnavailable("provider crypto unavailable"),
    StorageServiceUnavailable("storage service unavailable"),
    SecureSecretStorageUnavailable("secure secret storage unavailable"),
    SecureMetadataStorageUnavailable("secure metadata storage unavailable"),
    UnlockUnavailable("unlock unavailable"),
    ActiveSessionUnavailable("active session unavailable"),
    ProviderSelectionDisabled("provider selection disabled"),
    VaultCreationUnavailable("vault creation unavailable"),
    VaultPersistenceUnavailable("vault persistence unavailable"),
    RedactionUnsafe("redaction unsafe for diagnostics"),
    ClearWipeStrategyUnavailable("clear/wipe strategy unavailable"),
    WarningOnlyEvidenceRejected("warning-only evidence cannot enable migration"),
    UserConsentOverrideRejected("user consent cannot override missing hard gates"),
    MainnetUnavailable("mainnet remains unavailable"),
    RawStorageCandidateRejected("raw storage candidate rejected"),
}

enum class SkaldVaultV1VaultMigrationCorruptionWarning(val label: String) {
    EvidenceOnly("migration/corruption result is evidence only"),
    NoRealStoragePresent("no real persisted storage is present"),
    FutureImplementationRequiresReview("future migration/corruption implementation requires review"),
    StaleRecordCannotProveMaliciousRollback("stale records cannot prove malicious rollback by themselves"),
    RollbackResistanceRequiresFutureAnchor("rollback resistance requires a future reviewed anchor"),
    CrashRecoveryCannotBeProvenByModel("crash recovery cannot be proven by this model"),
    RedactedFailureReportingOnly("failure reporting must remain redacted"),
}

data class SkaldVaultV1VaultMigrationCorruptionCapability(
    val migrationAvailable: Boolean,
    val migrationDryRunAvailable: Boolean,
    val repairAvailable: Boolean,
    val quarantineAvailable: Boolean,
    val crashRecoveryAvailable: Boolean,
    val rollbackProtectionAvailable: Boolean,
    val realCorruptionDetectionAvailable: Boolean,
    val realHeaderCommitmentVerificationAvailable: Boolean,
    val realAeadAuthenticationAvailable: Boolean,
    val recordReadAvailable: Boolean,
    val recordWriteAvailable: Boolean,
    val manifestReadWriteAvailable: Boolean,
    val storageIndexReadWriteAvailable: Boolean,
    val providerCryptoAvailable: Boolean,
    val storageServiceAvailable: Boolean,
    val secureSecretStorageAvailable: Boolean,
    val secureMetadataStorageAvailable: Boolean,
    val unlockAvailable: Boolean,
    val activeSessionAvailable: Boolean,
    val providerSelectable: Boolean,
    val vaultCreationAvailable: Boolean,
    val vaultUnlockAvailable: Boolean,
    val vaultPersistenceAvailable: Boolean,
    val mainnetAvailable: Boolean,
) {
    companion object {
        val StillDisabled = SkaldVaultV1VaultMigrationCorruptionCapability(
            migrationAvailable = false,
            migrationDryRunAvailable = false,
            repairAvailable = false,
            quarantineAvailable = false,
            crashRecoveryAvailable = false,
            rollbackProtectionAvailable = false,
            realCorruptionDetectionAvailable = false,
            realHeaderCommitmentVerificationAvailable = false,
            realAeadAuthenticationAvailable = false,
            recordReadAvailable = false,
            recordWriteAvailable = false,
            manifestReadWriteAvailable = false,
            storageIndexReadWriteAvailable = false,
            providerCryptoAvailable = false,
            storageServiceAvailable = false,
            secureSecretStorageAvailable = false,
            secureMetadataStorageAvailable = false,
            unlockAvailable = false,
            activeSessionAvailable = false,
            providerSelectable = false,
            vaultCreationAvailable = false,
            vaultUnlockAvailable = false,
            vaultPersistenceAvailable = false,
            mainnetAvailable = false,
        )
    }
}

data class SkaldVaultV1VaultMigrationCorruptionPolicySummary(
    val policyId: String,
    val policyVersion: Int,
    val evidenceKinds: Set<SkaldVaultV1VaultMigrationCorruptionEvidenceKind>,
    val eventKinds: Set<SkaldVaultV1VaultMigrationCorruptionEventKind>,
    val failureClasses: Set<SkaldVaultV1VaultMigrationCorruptionFailureClass>,
    val requiredActions: Set<SkaldVaultV1VaultMigrationCorruptionRequiredAction>,
    val limitations: Set<SkaldVaultV1VaultMigrationCorruptionLimitation>,
    val stillDisabled: Boolean,
)

class SkaldVaultV1VaultMigrationCorruptionPolicyToken internal constructor(
    val policyId: String,
    val evidenceKind: SkaldVaultV1VaultMigrationCorruptionEvidenceKind?,
    val failureClass: SkaldVaultV1VaultMigrationCorruptionFailureClass?,
    val requiredAction: SkaldVaultV1VaultMigrationCorruptionRequiredAction?,
    val containsRawPersistedBytes: Boolean = false,
    val containsCiphertext: Boolean = false,
    val containsPlaintext: Boolean = false,
    val containsNonceOrTagBytes: Boolean = false,
    val containsHeaderCommitmentBytes: Boolean = false,
    val containsKeyMaterial: Boolean = false,
    val containsRootText: Boolean = false,
    val containsPlannedPathText: Boolean = false,
    val containsRecordIdentifier: Boolean = false,
    val containsPayload: Boolean = false,
) {
    override fun toString(): String =
        "SkaldVaultV1VaultMigrationCorruptionPolicyToken(" +
            "policyId=$policyId, " +
            "evidenceKind=${evidenceKind?.name ?: "none"}, " +
            "failureClass=${failureClass?.name ?: "none"}, " +
            "requiredAction=${requiredAction?.name ?: "none"}, " +
            "redacted=true)"
}

data class SkaldVaultV1VaultMigrationCorruptionEvidence(
    val policyId: String,
    val policyVersion: Int,
    val status: SkaldVaultV1VaultMigrationCorruptionStatus,
    val decision: SkaldVaultV1VaultMigrationCorruptionDecision,
    val source: SkaldVaultV1VaultMigrationCorruptionSource,
    val evidenceKind: SkaldVaultV1VaultMigrationCorruptionEvidenceKind?,
    val eventKind: SkaldVaultV1VaultMigrationCorruptionEventKind?,
    val failureClass: SkaldVaultV1VaultMigrationCorruptionFailureClass?,
    val severity: SkaldVaultV1VaultMigrationCorruptionSeverity,
    val requiredActions: Set<SkaldVaultV1VaultMigrationCorruptionRequiredAction>,
    val limitations: Set<SkaldVaultV1VaultMigrationCorruptionLimitation>,
    val blockers: Set<SkaldVaultV1VaultMigrationCorruptionBlocker>,
    val warnings: Set<SkaldVaultV1VaultMigrationCorruptionWarning>,
    val capability: SkaldVaultV1VaultMigrationCorruptionCapability,
    val policySummary: SkaldVaultV1VaultMigrationCorruptionPolicySummary,
    val policyTokenEvidence: SkaldVaultV1VaultMigrationCorruptionPolicyToken,
    val disabledStorageServiceEvidenceConsumed: Boolean,
    val storageSafetyPreflightEvidenceConsumed: Boolean,
    val platformPathConstructionEvidenceConsumed: Boolean,
    val persistenceReadinessEvidenceConsumed: Boolean,
    val redactionLeakageEvidenceConsumed: Boolean,
    val clearWipeStrategyEvidenceConsumed: Boolean,
    val lockSessionLifecycleEvidenceConsumed: Boolean,
    val providerSelectionEvidenceConsumed: Boolean,
    val disabledProviderFacadeEvidenceConsumed: Boolean,
    val secureStorageEvidenceConsumed: Boolean,
    val secureMetadataEvidenceConsumed: Boolean,
    val migrationCorruptionBoundaryModeled: Boolean = true,
    val migrationCorruptionBoundaryStillDisabled: Boolean = true,
    val migrationCorruptionClassifiesFailureKinds: Boolean = true,
    val migrationCorruptionDoesNotParseRealStorage: Boolean = true,
    val migrationCorruptionDoesNotRepairStorage: Boolean = true,
    val migrationCorruptionDoesNotRunMigration: Boolean = true,
    val migrationCorruptionDoesNotEnableUnlock: Boolean = true,
    val migrationCorruptionDoesNotEnablePersistence: Boolean = true,
    val migrationCorruptionDoesNotEnableProviderSelection: Boolean = true,
    val migrationCorruptionFailureVocabularyModeled: Boolean = true,
    val migrationReady: Boolean = false,
    val repairReady: Boolean = false,
    val quarantineReady: Boolean = false,
    val rollbackProtectionReady: Boolean = false,
    val crashRecoveryReady: Boolean = false,
    val realCorruptionDetectionReady: Boolean = false,
    val headerCommitmentVerificationReady: Boolean = false,
    val aeadAuthenticationReady: Boolean = false,
    val recordReadReady: Boolean = false,
    val recordWriteReady: Boolean = false,
    val unlockReady: Boolean = false,
    val providerReady: Boolean = false,
    val persistenceReady: Boolean = false,
    val mainnetReady: Boolean = false,
) {
    override fun toString(): String =
        "SkaldVaultV1VaultMigrationCorruptionEvidence(" +
            "policyId=$policyId, " +
            "status=${status.name}, " +
            "decision=${decision.name}, " +
            "source=${source.name}, " +
            "evidenceKind=${evidenceKind?.name ?: "none"}, " +
            "failureClass=${failureClass?.name ?: "none"}, " +
            "severity=${severity.name}, " +
            "capability=still-disabled, " +
            "policyTokenEvidence=$policyTokenEvidence)"
}

sealed class SkaldVaultV1VaultMigrationCorruptionResult<out T> {
    data class Blocked<out T>(
        val value: T,
    ) : SkaldVaultV1VaultMigrationCorruptionResult<T>() {
        override fun toString(): String =
            "SkaldVaultV1VaultMigrationCorruptionResult.Blocked(value=$value)"
    }

    data class Rejected(
        val reason: SkaldVaultV1VaultMigrationCorruptionFailureReason,
        val status: SkaldVaultV1VaultMigrationCorruptionStatus,
        val source: SkaldVaultV1VaultMigrationCorruptionSource,
        val safeMessage: String,
    ) : SkaldVaultV1VaultMigrationCorruptionResult<Nothing>() {
        override fun toString(): String =
            "SkaldVaultV1VaultMigrationCorruptionResult.Rejected(" +
                "reason=${reason.name}, " +
                "status=${status.name})"
    }
}

enum class SkaldVaultV1VaultMigrationCorruptionFailureReason(val label: String) {
    EmptyEvidenceRejected("empty migration/corruption evidence rejected"),
    RawPersistedContainerBytesRejected("raw persisted container bytes rejected"),
    RawManifestBytesRejected("raw manifest bytes rejected"),
    RawStorageIndexBytesRejected("raw storage-index bytes rejected"),
    RawRecordBytesRejected("raw record bytes rejected"),
    RawCiphertextRejected("raw ciphertext rejected"),
    RawPlaintextRejected("raw plaintext rejected"),
    AeadTagBytesRejected("AEAD tag bytes rejected"),
    NonceBytesRejected("nonce bytes rejected"),
    HeaderCommitmentBytesRejected("header commitment bytes rejected"),
    KdfOutputRejected("KDF output rejected"),
    ProviderKeyMaterialRejected("provider key material rejected"),
    PassphraseInputRejected("passphrase-like input rejected"),
    ByteArrayInputRejected("byte-array input rejected"),
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
    RawMigrationCorruptionInputRejected("raw migration/corruption input rejected"),
}

class SkaldVaultV1VaultMigrationCorruptionRequest private constructor(
    val source: SkaldVaultV1VaultMigrationCorruptionSource,
    val evidenceKind: SkaldVaultV1VaultMigrationCorruptionEvidenceKind?,
    val eventKind: SkaldVaultV1VaultMigrationCorruptionEventKind?,
    val failureClass: SkaldVaultV1VaultMigrationCorruptionFailureClass?,
    val requestedAction: SkaldVaultV1VaultMigrationCorruptionRequiredAction?,
    private val rawCandidate: String?,
    private val disabledStorageServiceEvidence: SkaldVaultV1VaultStorageDisabledEvidence?,
    private val storageSafetyPreflightEvidence: SkaldVaultV1StorageSafetyPreflightEvidence?,
    private val platformPathConstructionEvidence: SkaldVaultV1PlatformPathConstructionEvidence?,
    private val persistenceReadinessEvidence: SkaldVaultV1VaultPersistenceReadinessEvidence?,
    private val redactionLeakageEvidence: SkaldVaultV1VaultRedactionEvidence?,
    private val clearWipeStrategyEvidence: SkaldVaultV1VaultClearWipeEvidence?,
    private val lockSessionLifecycleEvidence: SkaldVaultV1VaultLockSessionEvidence?,
    private val providerSelectionResult: VaultCryptoProviderSelectionResult?,
    private val disabledProviderFacadeMetadata: SkaldVaultV1StillDisabledProviderFacadeMetadata?,
    private val secureStorageCapability: SecureStorageCapability?,
    private val secureMetadataCapability: SecureMetadataPersistenceCapability?,
) {
    val rawCandidateRejected: Boolean
        get() = source == SkaldVaultV1VaultMigrationCorruptionSource.RawStorageCandidate

    internal fun rawCandidateOrNull(): String? = rawCandidate

    internal fun disabledStorageServiceEvidenceOrNull(): SkaldVaultV1VaultStorageDisabledEvidence? =
        disabledStorageServiceEvidence

    internal fun storageSafetyPreflightEvidenceOrNull(): SkaldVaultV1StorageSafetyPreflightEvidence? =
        storageSafetyPreflightEvidence

    internal fun platformPathConstructionEvidenceOrNull(): SkaldVaultV1PlatformPathConstructionEvidence? =
        platformPathConstructionEvidence

    internal fun persistenceReadinessEvidenceOrNull(): SkaldVaultV1VaultPersistenceReadinessEvidence? =
        persistenceReadinessEvidence

    internal fun redactionLeakageEvidenceOrNull(): SkaldVaultV1VaultRedactionEvidence? =
        redactionLeakageEvidence

    internal fun clearWipeStrategyEvidenceOrNull(): SkaldVaultV1VaultClearWipeEvidence? =
        clearWipeStrategyEvidence

    internal fun lockSessionLifecycleEvidenceOrNull(): SkaldVaultV1VaultLockSessionEvidence? =
        lockSessionLifecycleEvidence

    internal fun providerSelectionResultOrNull(): VaultCryptoProviderSelectionResult? =
        providerSelectionResult

    internal fun disabledProviderFacadeMetadataOrNull(): SkaldVaultV1StillDisabledProviderFacadeMetadata? =
        disabledProviderFacadeMetadata

    internal fun secureStorageCapabilityOrNull(): SecureStorageCapability? = secureStorageCapability

    internal fun secureMetadataCapabilityOrNull(): SecureMetadataPersistenceCapability? = secureMetadataCapability

    override fun toString(): String =
        "SkaldVaultV1VaultMigrationCorruptionRequest(" +
            "source=${source.name}, " +
            "evidenceKind=${evidenceKind?.name ?: "none"}, " +
            "eventKind=${eventKind?.name ?: "none"}, " +
            "failureClass=${failureClass?.name ?: "none"}, " +
            "requestedAction=${requestedAction?.name ?: "none"}, " +
            "rawCandidate=REDACTED)"

    companion object {
        fun noEvidence(): SkaldVaultV1VaultMigrationCorruptionRequest =
            SkaldVaultV1VaultMigrationCorruptionRequest(
                source = SkaldVaultV1VaultMigrationCorruptionSource.NoEvidence,
                evidenceKind = null,
                eventKind = null,
                failureClass = null,
                requestedAction = null,
                rawCandidate = null,
                disabledStorageServiceEvidence = null,
                storageSafetyPreflightEvidence = null,
                platformPathConstructionEvidence = null,
                persistenceReadinessEvidence = null,
                redactionLeakageEvidence = null,
                clearWipeStrategyEvidence = null,
                lockSessionLifecycleEvidence = null,
                providerSelectionResult = null,
                disabledProviderFacadeMetadata = null,
                secureStorageCapability = null,
                secureMetadataCapability = null,
            )

        fun summary(): SkaldVaultV1VaultMigrationCorruptionRequest =
            noEvidence().copyFor(source = SkaldVaultV1VaultMigrationCorruptionSource.PolicySummary)

        fun forEvidenceKind(
            evidenceKind: SkaldVaultV1VaultMigrationCorruptionEvidenceKind,
            eventKind: SkaldVaultV1VaultMigrationCorruptionEventKind? = null,
        ): SkaldVaultV1VaultMigrationCorruptionRequest =
            noEvidence().copyFor(
                source = SkaldVaultV1VaultMigrationCorruptionSource.EvidenceKind,
                evidenceKind = evidenceKind,
                eventKind = eventKind,
            )

        fun forFailureClass(
            failureClass: SkaldVaultV1VaultMigrationCorruptionFailureClass,
            eventKind: SkaldVaultV1VaultMigrationCorruptionEventKind? = null,
        ): SkaldVaultV1VaultMigrationCorruptionRequest =
            noEvidence().copyFor(
                source = SkaldVaultV1VaultMigrationCorruptionSource.FailureClass,
                failureClass = failureClass,
                eventKind = eventKind,
            )

        fun forRequiredAction(
            requestedAction: SkaldVaultV1VaultMigrationCorruptionRequiredAction,
        ): SkaldVaultV1VaultMigrationCorruptionRequest =
            noEvidence().copyFor(
                source = SkaldVaultV1VaultMigrationCorruptionSource.RequiredAction,
                requestedAction = requestedAction,
            )

        fun fromEvidence(
            disabledStorageServiceEvidence: SkaldVaultV1VaultStorageDisabledEvidence? = null,
            storageSafetyPreflightEvidence: SkaldVaultV1StorageSafetyPreflightEvidence? = null,
            platformPathConstructionEvidence: SkaldVaultV1PlatformPathConstructionEvidence? = null,
            persistenceReadinessEvidence: SkaldVaultV1VaultPersistenceReadinessEvidence? = null,
            redactionLeakageEvidence: SkaldVaultV1VaultRedactionEvidence? = null,
            clearWipeStrategyEvidence: SkaldVaultV1VaultClearWipeEvidence? = null,
            lockSessionLifecycleEvidence: SkaldVaultV1VaultLockSessionEvidence? = null,
            providerSelectionResult: VaultCryptoProviderSelectionResult? = null,
            disabledProviderFacadeMetadata: SkaldVaultV1StillDisabledProviderFacadeMetadata? = null,
            secureStorageCapability: SecureStorageCapability? = null,
            secureMetadataCapability: SecureMetadataPersistenceCapability? = null,
            evidenceKind: SkaldVaultV1VaultMigrationCorruptionEvidenceKind? = null,
            failureClass: SkaldVaultV1VaultMigrationCorruptionFailureClass? = null,
            eventKind: SkaldVaultV1VaultMigrationCorruptionEventKind? = null,
        ): SkaldVaultV1VaultMigrationCorruptionRequest =
            SkaldVaultV1VaultMigrationCorruptionRequest(
                source = SkaldVaultV1VaultMigrationCorruptionSource.ComposedTypedEvidence,
                evidenceKind = evidenceKind,
                eventKind = eventKind,
                failureClass = failureClass,
                requestedAction = null,
                rawCandidate = null,
                disabledStorageServiceEvidence = disabledStorageServiceEvidence,
                storageSafetyPreflightEvidence = storageSafetyPreflightEvidence,
                platformPathConstructionEvidence = platformPathConstructionEvidence,
                persistenceReadinessEvidence = persistenceReadinessEvidence,
                redactionLeakageEvidence = redactionLeakageEvidence,
                clearWipeStrategyEvidence = clearWipeStrategyEvidence,
                lockSessionLifecycleEvidence = lockSessionLifecycleEvidence,
                providerSelectionResult = providerSelectionResult,
                disabledProviderFacadeMetadata = disabledProviderFacadeMetadata,
                secureStorageCapability = secureStorageCapability,
                secureMetadataCapability = secureMetadataCapability,
            )

        fun rawStorageCandidate(rawCandidate: String?): SkaldVaultV1VaultMigrationCorruptionRequest =
            noEvidence().copyFor(
                source = SkaldVaultV1VaultMigrationCorruptionSource.RawStorageCandidate,
                rawCandidate = rawCandidate,
            )
    }

    private fun copyFor(
        source: SkaldVaultV1VaultMigrationCorruptionSource = this.source,
        evidenceKind: SkaldVaultV1VaultMigrationCorruptionEvidenceKind? = this.evidenceKind,
        eventKind: SkaldVaultV1VaultMigrationCorruptionEventKind? = this.eventKind,
        failureClass: SkaldVaultV1VaultMigrationCorruptionFailureClass? = this.failureClass,
        requestedAction: SkaldVaultV1VaultMigrationCorruptionRequiredAction? = this.requestedAction,
        rawCandidate: String? = this.rawCandidate,
    ): SkaldVaultV1VaultMigrationCorruptionRequest =
        SkaldVaultV1VaultMigrationCorruptionRequest(
            source = source,
            evidenceKind = evidenceKind,
            eventKind = eventKind,
            failureClass = failureClass,
            requestedAction = requestedAction,
            rawCandidate = rawCandidate,
            disabledStorageServiceEvidence = disabledStorageServiceEvidence,
            storageSafetyPreflightEvidence = storageSafetyPreflightEvidence,
            platformPathConstructionEvidence = platformPathConstructionEvidence,
            persistenceReadinessEvidence = persistenceReadinessEvidence,
            redactionLeakageEvidence = redactionLeakageEvidence,
            clearWipeStrategyEvidence = clearWipeStrategyEvidence,
            lockSessionLifecycleEvidence = lockSessionLifecycleEvidence,
            providerSelectionResult = providerSelectionResult,
            disabledProviderFacadeMetadata = disabledProviderFacadeMetadata,
            secureStorageCapability = secureStorageCapability,
            secureMetadataCapability = secureMetadataCapability,
        )
}

object SkaldVaultV1MigrationCorruptionPolicy : SkaldVaultV1MigrationCorruptionBoundary {
    const val POLICY_ID = "skald-vault-v1-migration-corruption-boundary-v1"
    const val POLICY_VERSION = 1

    fun currentPolicySummary(): SkaldVaultV1VaultMigrationCorruptionPolicySummary =
        SkaldVaultV1VaultMigrationCorruptionPolicySummary(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            evidenceKinds = SkaldVaultV1VaultMigrationCorruptionEvidenceKind.entries.toSet(),
            eventKinds = SkaldVaultV1VaultMigrationCorruptionEventKind.entries.toSet(),
            failureClasses = SkaldVaultV1VaultMigrationCorruptionFailureClass.entries.toSet(),
            requiredActions = SkaldVaultV1VaultMigrationCorruptionRequiredAction.entries.toSet(),
            limitations = SkaldVaultV1VaultMigrationCorruptionLimitation.entries.toSet(),
            stillDisabled = true,
        )

    override fun evaluate(
        request: SkaldVaultV1VaultMigrationCorruptionRequest,
    ): SkaldVaultV1VaultMigrationCorruptionResult<SkaldVaultV1VaultMigrationCorruptionEvidence> =
        when (request.source) {
            SkaldVaultV1VaultMigrationCorruptionSource.RawStorageCandidate -> rejectRawCandidate(request)
            else -> blocked(request)
        }

    private fun blocked(
        request: SkaldVaultV1VaultMigrationCorruptionRequest,
    ): SkaldVaultV1VaultMigrationCorruptionResult.Blocked<SkaldVaultV1VaultMigrationCorruptionEvidence> {
        val failureClass = request.failureClass ?: failureForEvidence(request.evidenceKind)
        val requiredActions = actionsFor(
            request = request,
            failureClass = failureClass,
        )
        val severity = severityFor(failureClass)
        val decision = decisionFor(
            request = request,
            failureClass = failureClass,
            requiredActions = requiredActions,
        )
        val policyTokenEvidence = SkaldVaultV1VaultMigrationCorruptionPolicyToken(
            policyId = POLICY_ID,
            evidenceKind = request.evidenceKind,
            failureClass = failureClass,
            requiredAction = request.requestedAction,
        )

        return SkaldVaultV1VaultMigrationCorruptionResult.Blocked(
            SkaldVaultV1VaultMigrationCorruptionEvidence(
                policyId = POLICY_ID,
                policyVersion = POLICY_VERSION,
                status = statusFor(request),
                decision = decision,
                source = request.source,
                evidenceKind = request.evidenceKind,
                eventKind = request.eventKind,
                failureClass = failureClass,
                severity = severity,
                requiredActions = requiredActions,
                limitations = SkaldVaultV1VaultMigrationCorruptionLimitation.entries.toSet(),
                blockers = blockersFor(request),
                warnings = SkaldVaultV1VaultMigrationCorruptionWarning.entries.toSet(),
                capability = SkaldVaultV1VaultMigrationCorruptionCapability.StillDisabled,
                policySummary = currentPolicySummary(),
                policyTokenEvidence = policyTokenEvidence,
                disabledStorageServiceEvidenceConsumed = request.disabledStorageServiceEvidenceOrNull() != null,
                storageSafetyPreflightEvidenceConsumed = request.storageSafetyPreflightEvidenceOrNull() != null,
                platformPathConstructionEvidenceConsumed = request.platformPathConstructionEvidenceOrNull() != null,
                persistenceReadinessEvidenceConsumed = request.persistenceReadinessEvidenceOrNull() != null,
                redactionLeakageEvidenceConsumed = request.redactionLeakageEvidenceOrNull() != null,
                clearWipeStrategyEvidenceConsumed = request.clearWipeStrategyEvidenceOrNull() != null,
                lockSessionLifecycleEvidenceConsumed = request.lockSessionLifecycleEvidenceOrNull() != null,
                providerSelectionEvidenceConsumed = request.providerSelectionResultOrNull() != null,
                disabledProviderFacadeEvidenceConsumed = request.disabledProviderFacadeMetadataOrNull() != null,
                secureStorageEvidenceConsumed = request.secureStorageCapabilityOrNull() != null,
                secureMetadataEvidenceConsumed = request.secureMetadataCapabilityOrNull() != null,
            ),
        )
    }

    private fun rejectRawCandidate(
        request: SkaldVaultV1VaultMigrationCorruptionRequest,
    ): SkaldVaultV1VaultMigrationCorruptionResult.Rejected =
        SkaldVaultV1VaultMigrationCorruptionResult.Rejected(
            reason = classifyRawCandidate(request.rawCandidateOrNull()),
            status = SkaldVaultV1VaultMigrationCorruptionStatus.RawCandidateRejected,
            source = SkaldVaultV1VaultMigrationCorruptionSource.RawStorageCandidate,
            safeMessage = "Raw migration/corruption storage material is not accepted by this model-only boundary.",
        )

    private fun statusFor(
        request: SkaldVaultV1VaultMigrationCorruptionRequest,
    ): SkaldVaultV1VaultMigrationCorruptionStatus =
        when (request.source) {
            SkaldVaultV1VaultMigrationCorruptionSource.NoEvidence ->
                SkaldVaultV1VaultMigrationCorruptionStatus.NoEvidenceAvailable
            SkaldVaultV1VaultMigrationCorruptionSource.PolicySummary ->
                SkaldVaultV1VaultMigrationCorruptionStatus.PolicySummaryModeled
            SkaldVaultV1VaultMigrationCorruptionSource.EvidenceKind ->
                SkaldVaultV1VaultMigrationCorruptionStatus.EvidenceKindModeled
            SkaldVaultV1VaultMigrationCorruptionSource.FailureClass ->
                SkaldVaultV1VaultMigrationCorruptionStatus.FailureClassModeled
            SkaldVaultV1VaultMigrationCorruptionSource.RequiredAction ->
                SkaldVaultV1VaultMigrationCorruptionStatus.RequiredActionModeled
            SkaldVaultV1VaultMigrationCorruptionSource.ComposedTypedEvidence ->
                SkaldVaultV1VaultMigrationCorruptionStatus.MigrationCorruptionBlockedStillDisabled
            SkaldVaultV1VaultMigrationCorruptionSource.RawStorageCandidate ->
                SkaldVaultV1VaultMigrationCorruptionStatus.RawCandidateRejected
        }

    private fun decisionFor(
        request: SkaldVaultV1VaultMigrationCorruptionRequest,
        failureClass: SkaldVaultV1VaultMigrationCorruptionFailureClass?,
        requiredActions: Set<SkaldVaultV1VaultMigrationCorruptionRequiredAction>,
    ): SkaldVaultV1VaultMigrationCorruptionDecision =
        when {
            request.source == SkaldVaultV1VaultMigrationCorruptionSource.NoEvidence ||
                request.source == SkaldVaultV1VaultMigrationCorruptionSource.ComposedTypedEvidence ->
                SkaldVaultV1VaultMigrationCorruptionDecision.BlockedFailClosed
            failureClass in manualReviewFailureClasses ||
                requiredActions.contains(SkaldVaultV1VaultMigrationCorruptionRequiredAction.RequireManualReview) ->
                SkaldVaultV1VaultMigrationCorruptionDecision.RequireManualReview
            requiredActions.contains(SkaldVaultV1VaultMigrationCorruptionRequiredAction.RejectOperation) ->
                SkaldVaultV1VaultMigrationCorruptionDecision.RejectOperation
            failureClass == SkaldVaultV1VaultMigrationCorruptionFailureClass.NoEvidence ->
                SkaldVaultV1VaultMigrationCorruptionDecision.UnsupportedFailClosed
            else -> SkaldVaultV1VaultMigrationCorruptionDecision.ModelOnlyClassification
        }

    private fun failureForEvidence(
        evidenceKind: SkaldVaultV1VaultMigrationCorruptionEvidenceKind?,
    ): SkaldVaultV1VaultMigrationCorruptionFailureClass? =
        when (evidenceKind) {
            null -> null
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.VaultContainerHeaderEvidence ->
                SkaldVaultV1VaultMigrationCorruptionFailureClass.MalformedHeader
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.ContainerVersionEvidence,
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.UnsupportedOldVersionEvidence ->
                SkaldVaultV1VaultMigrationCorruptionFailureClass.UnsupportedVersion
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.ProviderSuiteIdEvidence ->
                SkaldVaultV1VaultMigrationCorruptionFailureClass.ProviderSuiteMismatch
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.KdfParameterEvidence ->
                SkaldVaultV1VaultMigrationCorruptionFailureClass.KdfParameterMismatch
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.HeaderCommitmentEvidence ->
                SkaldVaultV1VaultMigrationCorruptionFailureClass.HeaderCommitmentMismatch
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.AadContractEvidence ->
                SkaldVaultV1VaultMigrationCorruptionFailureClass.AadMismatch
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.ManifestVersionEvidence,
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.PartialManifestWriteEvidence ->
                SkaldVaultV1VaultMigrationCorruptionFailureClass.ManifestMalformed
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.ManifestCurrentRecordReferenceEvidence,
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.MissingRecordEvidence ->
                SkaldVaultV1VaultMigrationCorruptionFailureClass.RecordMissing
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.StorageIndexVersionEvidence,
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.PartialStorageIndexWriteEvidence ->
                SkaldVaultV1VaultMigrationCorruptionFailureClass.StorageIndexMalformed
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.StorageIndexRecordReferenceEvidence ->
                SkaldVaultV1VaultMigrationCorruptionFailureClass.RecordDescriptorMalformed
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.RecordDescriptorEvidence,
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.RecordPurposeEvidence,
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.RecordNonceCounterEvidence ->
                SkaldVaultV1VaultMigrationCorruptionFailureClass.RecordDescriptorMalformed
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.RecordCiphertextPlaceholderEvidence,
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.RecordAuthenticationFailureEvidence ->
                SkaldVaultV1VaultMigrationCorruptionFailureClass.AuthenticationFailed
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.StaleRecordEvidence ->
                SkaldVaultV1VaultMigrationCorruptionFailureClass.StaleRecord
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.RollbackSuspicionEvidence ->
                SkaldVaultV1VaultMigrationCorruptionFailureClass.RollbackSuspected
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.InterruptedWriteEvidence ->
                SkaldVaultV1VaultMigrationCorruptionFailureClass.PartialWriteSuspected
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.OrphanRecordEvidence ->
                SkaldVaultV1VaultMigrationCorruptionFailureClass.RecordOrphaned
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.DuplicateRecordEvidence ->
                SkaldVaultV1VaultMigrationCorruptionFailureClass.DuplicateRecord
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.ConflictingRecordEvidence ->
                SkaldVaultV1VaultMigrationCorruptionFailureClass.ConflictingRecord
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.UnknownFutureVersionEvidence ->
                SkaldVaultV1VaultMigrationCorruptionFailureClass.UnknownFutureVersion
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.MigrationRequiredEvidence,
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.MigrationPlanEvidence ->
                SkaldVaultV1VaultMigrationCorruptionFailureClass.MigrationRequired
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.CorruptionDetectedEvidence ->
                SkaldVaultV1VaultMigrationCorruptionFailureClass.CorruptionDetected
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.RecoveryAttemptEvidence ->
                SkaldVaultV1VaultMigrationCorruptionFailureClass.CrashRecoveryRequired
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.QuarantineRequiredEvidence ->
                SkaldVaultV1VaultMigrationCorruptionFailureClass.QuarantineRequired
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.ManualReviewRequiredEvidence ->
                SkaldVaultV1VaultMigrationCorruptionFailureClass.ManualReviewRequired
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.FailClosedRequiredEvidence ->
                SkaldVaultV1VaultMigrationCorruptionFailureClass.CorruptionDetected
        }

    private fun actionsFor(
        request: SkaldVaultV1VaultMigrationCorruptionRequest,
        failureClass: SkaldVaultV1VaultMigrationCorruptionFailureClass?,
    ): Set<SkaldVaultV1VaultMigrationCorruptionRequiredAction> =
        buildSet {
            add(SkaldVaultV1VaultMigrationCorruptionRequiredAction.FailClosed)
            add(SkaldVaultV1VaultMigrationCorruptionRequiredAction.RequireRedactedDiagnosticOnly)
            add(SkaldVaultV1VaultMigrationCorruptionRequiredAction.RequireUserVisibleWarning)
            if (request.requestedAction != null) {
                add(request.requestedAction)
            }
            when (failureClass) {
                SkaldVaultV1VaultMigrationCorruptionFailureClass.NoEvidence,
                null -> add(SkaldVaultV1VaultMigrationCorruptionRequiredAction.UnsupportedNoActionAvailable)
                SkaldVaultV1VaultMigrationCorruptionFailureClass.UnsupportedVersion,
                SkaldVaultV1VaultMigrationCorruptionFailureClass.UnknownFutureVersion ->
                    add(SkaldVaultV1VaultMigrationCorruptionRequiredAction.RejectOperation)
                SkaldVaultV1VaultMigrationCorruptionFailureClass.MigrationRequired -> {
                    add(SkaldVaultV1VaultMigrationCorruptionRequiredAction.RequireBackupBeforeMigration)
                    add(SkaldVaultV1VaultMigrationCorruptionRequiredAction.RequireDryRunMigrationFirst)
                    add(SkaldVaultV1VaultMigrationCorruptionRequiredAction.RequireProviderValidation)
                    add(SkaldVaultV1VaultMigrationCorruptionRequiredAction.RequireStorageSafetyValidation)
                }
                SkaldVaultV1VaultMigrationCorruptionFailureClass.MigrationUnsafe,
                SkaldVaultV1VaultMigrationCorruptionFailureClass.ManualReviewRequired ->
                    add(SkaldVaultV1VaultMigrationCorruptionRequiredAction.RequireManualReview)
                SkaldVaultV1VaultMigrationCorruptionFailureClass.StaleRecord -> {
                    add(SkaldVaultV1VaultMigrationCorruptionRequiredAction.RequireStaleRecordReview)
                    add(SkaldVaultV1VaultMigrationCorruptionRequiredAction.RequireManifestValidation)
                }
                SkaldVaultV1VaultMigrationCorruptionFailureClass.RollbackSuspected -> {
                    add(SkaldVaultV1VaultMigrationCorruptionRequiredAction.RequireRollbackReview)
                    add(SkaldVaultV1VaultMigrationCorruptionRequiredAction.RequireReleaseHardeningReview)
                }
                SkaldVaultV1VaultMigrationCorruptionFailureClass.QuarantineRequired,
                SkaldVaultV1VaultMigrationCorruptionFailureClass.RecordOrphaned,
                SkaldVaultV1VaultMigrationCorruptionFailureClass.DuplicateRecord,
                SkaldVaultV1VaultMigrationCorruptionFailureClass.ConflictingRecord ->
                    add(SkaldVaultV1VaultMigrationCorruptionRequiredAction.RequireRecordQuarantine)
                SkaldVaultV1VaultMigrationCorruptionFailureClass.PartialWriteSuspected,
                SkaldVaultV1VaultMigrationCorruptionFailureClass.CrashRecoveryRequired -> {
                    add(SkaldVaultV1VaultMigrationCorruptionRequiredAction.RequireManifestValidation)
                    add(SkaldVaultV1VaultMigrationCorruptionRequiredAction.RequireStorageIndexValidation)
                    add(SkaldVaultV1VaultMigrationCorruptionRequiredAction.RequireCorruptionReview)
                }
                SkaldVaultV1VaultMigrationCorruptionFailureClass.AuthenticationFailed,
                SkaldVaultV1VaultMigrationCorruptionFailureClass.HeaderCommitmentMismatch,
                SkaldVaultV1VaultMigrationCorruptionFailureClass.ProviderSuiteMismatch,
                SkaldVaultV1VaultMigrationCorruptionFailureClass.KdfParameterMismatch,
                SkaldVaultV1VaultMigrationCorruptionFailureClass.AadMismatch ->
                    add(SkaldVaultV1VaultMigrationCorruptionRequiredAction.RequireProviderValidation)
                SkaldVaultV1VaultMigrationCorruptionFailureClass.ManifestMissing,
                SkaldVaultV1VaultMigrationCorruptionFailureClass.ManifestMalformed ->
                    add(SkaldVaultV1VaultMigrationCorruptionRequiredAction.RequireManifestValidation)
                SkaldVaultV1VaultMigrationCorruptionFailureClass.StorageIndexMissing,
                SkaldVaultV1VaultMigrationCorruptionFailureClass.StorageIndexMalformed ->
                    add(SkaldVaultV1VaultMigrationCorruptionRequiredAction.RequireStorageIndexValidation)
                SkaldVaultV1VaultMigrationCorruptionFailureClass.CorruptionDetected,
                SkaldVaultV1VaultMigrationCorruptionFailureClass.MalformedHeader,
                SkaldVaultV1VaultMigrationCorruptionFailureClass.RecordDescriptorMalformed,
                SkaldVaultV1VaultMigrationCorruptionFailureClass.RecordMissing ->
                    add(SkaldVaultV1VaultMigrationCorruptionRequiredAction.RequireCorruptionReview)
                SkaldVaultV1VaultMigrationCorruptionFailureClass.SecureStorageUnavailable,
                SkaldVaultV1VaultMigrationCorruptionFailureClass.ProviderUnavailable,
                SkaldVaultV1VaultMigrationCorruptionFailureClass.StorageServiceDisabled,
                SkaldVaultV1VaultMigrationCorruptionFailureClass.RedactionUnsafe,
                SkaldVaultV1VaultMigrationCorruptionFailureClass.ClearWipeStrategyUnavailable,
                SkaldVaultV1VaultMigrationCorruptionFailureClass.MainnetBlocked ->
                    add(SkaldVaultV1VaultMigrationCorruptionRequiredAction.RejectOperation)
            }
            add(SkaldVaultV1VaultMigrationCorruptionRequiredAction.RequireClearWipeHandling)
        }

    private fun severityFor(
        failureClass: SkaldVaultV1VaultMigrationCorruptionFailureClass?,
    ): SkaldVaultV1VaultMigrationCorruptionSeverity =
        when (failureClass) {
            null -> SkaldVaultV1VaultMigrationCorruptionSeverity.InformationalModelOnly
            SkaldVaultV1VaultMigrationCorruptionFailureClass.NoEvidence ->
                SkaldVaultV1VaultMigrationCorruptionSeverity.UnsupportedFailClosed
            SkaldVaultV1VaultMigrationCorruptionFailureClass.MigrationUnsafe,
            SkaldVaultV1VaultMigrationCorruptionFailureClass.RollbackSuspected,
            SkaldVaultV1VaultMigrationCorruptionFailureClass.CorruptionDetected,
            SkaldVaultV1VaultMigrationCorruptionFailureClass.ManualReviewRequired ->
                SkaldVaultV1VaultMigrationCorruptionSeverity.CriticalManualReview
            SkaldVaultV1VaultMigrationCorruptionFailureClass.StaleRecord,
            SkaldVaultV1VaultMigrationCorruptionFailureClass.MigrationRequired ->
                SkaldVaultV1VaultMigrationCorruptionSeverity.WarningFailClosed
            else -> SkaldVaultV1VaultMigrationCorruptionSeverity.BlockingFailure
        }

    private fun blockersFor(
        request: SkaldVaultV1VaultMigrationCorruptionRequest,
    ): Set<SkaldVaultV1VaultMigrationCorruptionBlocker> =
        buildSet {
            add(SkaldVaultV1VaultMigrationCorruptionBlocker.MigrationCorruptionBoundaryStillDisabled)
            add(SkaldVaultV1VaultMigrationCorruptionBlocker.MigrationImplementationMissing)
            add(SkaldVaultV1VaultMigrationCorruptionBlocker.MigrationDryRunMissing)
            add(SkaldVaultV1VaultMigrationCorruptionBlocker.RepairImplementationMissing)
            add(SkaldVaultV1VaultMigrationCorruptionBlocker.QuarantineImplementationMissing)
            add(SkaldVaultV1VaultMigrationCorruptionBlocker.CrashRecoveryImplementationMissing)
            add(SkaldVaultV1VaultMigrationCorruptionBlocker.RollbackProtectionUnavailable)
            add(SkaldVaultV1VaultMigrationCorruptionBlocker.RealCorruptionDetectionUnavailable)
            add(SkaldVaultV1VaultMigrationCorruptionBlocker.HeaderCommitmentVerificationUnavailable)
            add(SkaldVaultV1VaultMigrationCorruptionBlocker.AeadAuthenticationUnavailable)
            add(SkaldVaultV1VaultMigrationCorruptionBlocker.RecordReadWriteUnavailable)
            add(SkaldVaultV1VaultMigrationCorruptionBlocker.ManifestReadWriteUnavailable)
            add(SkaldVaultV1VaultMigrationCorruptionBlocker.StorageIndexReadWriteUnavailable)
            add(SkaldVaultV1VaultMigrationCorruptionBlocker.ProviderCryptoUnavailable)
            add(SkaldVaultV1VaultMigrationCorruptionBlocker.StorageServiceUnavailable)
            add(SkaldVaultV1VaultMigrationCorruptionBlocker.SecureSecretStorageUnavailable)
            add(SkaldVaultV1VaultMigrationCorruptionBlocker.SecureMetadataStorageUnavailable)
            add(SkaldVaultV1VaultMigrationCorruptionBlocker.UnlockUnavailable)
            add(SkaldVaultV1VaultMigrationCorruptionBlocker.ActiveSessionUnavailable)
            add(SkaldVaultV1VaultMigrationCorruptionBlocker.ProviderSelectionDisabled)
            add(SkaldVaultV1VaultMigrationCorruptionBlocker.VaultCreationUnavailable)
            add(SkaldVaultV1VaultMigrationCorruptionBlocker.VaultPersistenceUnavailable)
            add(SkaldVaultV1VaultMigrationCorruptionBlocker.WarningOnlyEvidenceRejected)
            add(SkaldVaultV1VaultMigrationCorruptionBlocker.UserConsentOverrideRejected)
            add(SkaldVaultV1VaultMigrationCorruptionBlocker.MainnetUnavailable)
            if (request.source == SkaldVaultV1VaultMigrationCorruptionSource.NoEvidence) {
                add(SkaldVaultV1VaultMigrationCorruptionBlocker.NoEvidenceAvailable)
            }
            if (request.redactionLeakageEvidenceOrNull() != null) {
                add(SkaldVaultV1VaultMigrationCorruptionBlocker.RedactionUnsafe)
            }
            if (request.clearWipeStrategyEvidenceOrNull() != null) {
                add(SkaldVaultV1VaultMigrationCorruptionBlocker.ClearWipeStrategyUnavailable)
            }
        }

    private fun classifyRawCandidate(raw: String?): SkaldVaultV1VaultMigrationCorruptionFailureReason {
        val value = raw?.trim()
        if (value.isNullOrEmpty()) {
            return SkaldVaultV1VaultMigrationCorruptionFailureReason.EmptyEvidenceRejected
        }
        val lower = value.lowercase()
        return when {
            lower.contains("container-bytes") || lower.contains("persisted-bytes") ->
                SkaldVaultV1VaultMigrationCorruptionFailureReason.RawPersistedContainerBytesRejected
            lower.contains("manifest-bytes") ->
                SkaldVaultV1VaultMigrationCorruptionFailureReason.RawManifestBytesRejected
            lower.contains("storage-index-bytes") ->
                SkaldVaultV1VaultMigrationCorruptionFailureReason.RawStorageIndexBytesRejected
            lower.contains("record-bytes") ->
                SkaldVaultV1VaultMigrationCorruptionFailureReason.RawRecordBytesRejected
            lower.contains("ciphertext") ->
                SkaldVaultV1VaultMigrationCorruptionFailureReason.RawCiphertextRejected
            lower.contains("plaintext") ->
                SkaldVaultV1VaultMigrationCorruptionFailureReason.RawPlaintextRejected
            lower.contains("aead-tag") || lower == "tag" ->
                SkaldVaultV1VaultMigrationCorruptionFailureReason.AeadTagBytesRejected
            lower.contains("nonce") ->
                SkaldVaultV1VaultMigrationCorruptionFailureReason.NonceBytesRejected
            lower.contains("header-commitment") ->
                SkaldVaultV1VaultMigrationCorruptionFailureReason.HeaderCommitmentBytesRejected
            lower.contains("kdf-output") ->
                SkaldVaultV1VaultMigrationCorruptionFailureReason.KdfOutputRejected
            lower.contains("provider-key") || lower.contains("key-material") ->
                SkaldVaultV1VaultMigrationCorruptionFailureReason.ProviderKeyMaterialRejected
            lower.contains("passphrase") || lower.contains("password") || lower.contains("pin") ->
                SkaldVaultV1VaultMigrationCorruptionFailureReason.PassphraseInputRejected
            lower.contains("bytearray") || lower.contains("bytes(") ->
                SkaldVaultV1VaultMigrationCorruptionFailureReason.ByteArrayInputRejected
            lower.startsWith("file:") ||
                lower.startsWith("http:") ||
                lower.startsWith("https:") ||
                lower.startsWith("content:") ->
                SkaldVaultV1VaultMigrationCorruptionFailureReason.LinkLikeInputRejected
            lower.contains("file-object") ||
                lower.contains("path-object") ||
                lower.contains("uri-object") ||
                lower.contains("url-object") ->
                SkaldVaultV1VaultMigrationCorruptionFailureReason.PlatformObjectLikeInputRejected
            value.contains("..") ->
                SkaldVaultV1VaultMigrationCorruptionFailureReason.TraversalRejected
            value.startsWith("/") ||
                value.startsWith("\\") ||
                value.contains(":\\") ->
                SkaldVaultV1VaultMigrationCorruptionFailureReason.RawAbsoluteLocationInputRejected
            value.contains("/") ||
                value.contains("\\") ->
                SkaldVaultV1VaultMigrationCorruptionFailureReason.RawRelativeLocationInputRejected
            lower.contains("secret") ||
                lower.contains("credential") ||
                lower.contains("mnemonic") ||
                lower.contains("seed") ->
                SkaldVaultV1VaultMigrationCorruptionFailureReason.SecretMaterialRejected
            looksLikeWalletMaterial(value) ->
                SkaldVaultV1VaultMigrationCorruptionFailureReason.WalletMaterialRejected
            looksLikeBitcoinAddress(value) ->
                SkaldVaultV1VaultMigrationCorruptionFailureReason.BitcoinAddressLikeEvidenceRejected
            value.length == 64 && value.all { it.isHexDigit() } ->
                SkaldVaultV1VaultMigrationCorruptionFailureReason.TransactionLikeEvidenceRejected
            value.any { !it.isSupportedEvidenceCharacter() } ->
                SkaldVaultV1VaultMigrationCorruptionFailureReason.UnsupportedCharactersRejected
            else -> SkaldVaultV1VaultMigrationCorruptionFailureReason.RawMigrationCorruptionInputRejected
        }
    }

    private val manualReviewFailureClasses = setOf(
        SkaldVaultV1VaultMigrationCorruptionFailureClass.RollbackSuspected,
        SkaldVaultV1VaultMigrationCorruptionFailureClass.MigrationUnsafe,
        SkaldVaultV1VaultMigrationCorruptionFailureClass.CorruptionDetected,
        SkaldVaultV1VaultMigrationCorruptionFailureClass.ManualReviewRequired,
    )

    private fun looksLikeBitcoinAddress(value: String): Boolean {
        val lower = value.lowercase()
        return (lower.startsWith("bc1") || lower.startsWith("tb1") || lower.startsWith("bcrt1")) &&
            lower.length >= 24 &&
            lower.drop(3).all { it.isLetterOrDigit() }
    }

    private fun looksLikeWalletMaterial(value: String): Boolean {
        val lower = value.lowercase()
        return lower.startsWith("nsec1") ||
            lower.startsWith("xprv") ||
            lower.startsWith("tprv") ||
            ((value.startsWith("K") || value.startsWith("L") || value.startsWith("5")) && value.length >= 50)
    }

    private fun Char.isHexDigit(): Boolean =
        this in '0'..'9' || this in 'a'..'f' || this in 'A'..'F'

    private fun Char.isSupportedEvidenceCharacter(): Boolean =
        this in 'a'..'z' ||
            this in 'A'..'Z' ||
            this in '0'..'9' ||
            this == '-' ||
            this == '_' ||
            this == '.'
}
