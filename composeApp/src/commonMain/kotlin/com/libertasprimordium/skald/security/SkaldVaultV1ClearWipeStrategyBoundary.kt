package com.libertasprimordium.skald.security

interface SkaldVaultV1ClearWipeStrategyBoundary {
    fun evaluate(
        request: SkaldVaultV1VaultClearWipeRequest,
    ): SkaldVaultV1VaultClearWipeResult<SkaldVaultV1VaultClearWipeEvidence>
}

enum class SkaldVaultV1VaultClearWipeSource(val label: String) {
    NoEvidence("no clear/wipe evidence"),
    PolicySummary("clear/wipe policy summary"),
    ValueAndEvent("value and lifecycle event"),
    StrategyClass("strategy class"),
    ComposedTypedEvidence("composed typed evidence"),
    RawClearWipeCandidate("raw clear/wipe candidate"),
}

enum class SkaldVaultV1VaultClearWipeStatus(val label: String) {
    NoEvidenceAvailable("no clear/wipe evidence available"),
    PolicySummaryModeled("clear/wipe policy summary modeled"),
    RequirementModeled("clear/wipe requirement modeled"),
    StrategyModeled("clear/wipe strategy modeled"),
    ClearWipeBlockedStillDisabled("clear/wipe remains blocked and still disabled"),
    RawCandidateRejected("raw clear/wipe candidate rejected"),
}

enum class SkaldVaultV1VaultClearWipeDecision(
    val actualClearAllowed: Boolean,
    val actualZeroizationAllowed: Boolean,
    val unlockAllowed: Boolean,
    val persistenceAllowed: Boolean,
) {
    BlockedFailClosed(
        actualClearAllowed = false,
        actualZeroizationAllowed = false,
        unlockAllowed = false,
        persistenceAllowed = false,
    ),
    ModelOnlyRequirement(
        actualClearAllowed = false,
        actualZeroizationAllowed = false,
        unlockAllowed = false,
        persistenceAllowed = false,
    ),
    ForbiddenValueRejected(
        actualClearAllowed = false,
        actualZeroizationAllowed = false,
        unlockAllowed = false,
        persistenceAllowed = false,
    ),
    UnsupportedFailClosed(
        actualClearAllowed = false,
        actualZeroizationAllowed = false,
        unlockAllowed = false,
        persistenceAllowed = false,
    ),
}

enum class SkaldVaultV1VaultClearWipeValueKind(val label: String) {
    PassphraseInputBuffer("passphrase input buffer"),
    PinInputBuffer("PIN input buffer"),
    MnemonicText("mnemonic text"),
    SeedBytes("seed bytes"),
    PrivateKeyBytes("private key bytes"),
    XprvTprvWifText("xprv/tprv/WIF text"),
    NostrNsecPrivateKeyMaterial("Nostr nsec/private key material"),
    DescriptorPrivateMaterial("descriptor private material"),
    ProviderRootKey("provider root key"),
    VaultRootKey("vault root key"),
    MetadataEncryptionKey("metadata encryption key"),
    RecordEncryptionKey("record encryption key"),
    BackupExportKey("backup/export key"),
    KeyWrappingKey("key-wrapping key"),
    RawKdfInput("raw KDF input"),
    RawKdfOutput("raw KDF output"),
    RawAeadKey("raw AEAD key"),
    RandomEntropyBuffer("random entropy buffer"),
    DecryptedVaultRecord("decrypted vault record"),
    DecryptedMetadataRecord("decrypted metadata record"),
    EncryptedRecordStagingBuffer("encrypted record staging buffer"),
    ManifestStagingBuffer("manifest staging buffer"),
    StorageIndexStagingBuffer("storage-index staging buffer"),
    ProviderSessionHandle("provider session handle"),
    StorageSessionHandle("storage session handle"),
    LockSessionToken("lock/session token"),
    PassphraseRetryThrottleState("passphrase retry/throttle state"),
    RedactionDiagnosticStagingValue("redaction/diagnostic staging value"),
    WalletLabelTransactionNoteSensitiveMetadata("wallet label or transaction note sensitive metadata"),
    BackendCredentialStagingValue("backend credential staging value"),
    LightningCashuNostrCredentialStagingValue("Lightning/Cashu/Nostr credential staging value"),
    FuturePlatformWrappedKeyReference("future platform-wrapped key reference"),
    FutureAndroidHardwareWrappedKeyHandle("future Android hardware-wrapped key handle"),
    FutureLinuxOptionalKeyWrappingHandle("future Linux optional key-wrapping handle"),
}

enum class SkaldVaultV1VaultClearWipeEventKind(val label: String) {
    UserRequestsLock("user requests lock"),
    TimeoutReached("timeout reached"),
    AppBackgrounded("app backgrounded"),
    AppForegroundedAfterLockRequiredState("app foregrounded after lock-required state"),
    AppCloseShutdown("app close/shutdown"),
    ErrorFault("error/fault"),
    UnlockAttemptFails("unlock attempt fails"),
    UnlockCancelled("unlock cancelled"),
    ProviderSelectionChanges("provider selection changes"),
    ProviderKatStatusChanges("provider KAT status changes"),
    PersistenceReadinessChanges("persistence readiness changes"),
    StorageSafetyChanges("storage safety changes"),
    StorageServiceFailure("storage service failure"),
    SecureStorageCapabilityChanges("secure storage capability changes"),
    SecureMetadataCapabilityChanges("secure metadata capability changes"),
    RootPathEvidenceChanges("root/path evidence changes"),
    PlatformSecurityPostureChanges("platform security posture changes"),
    MainnetRequestAttempted("mainnet request attempted"),
    MigrationCorruptionDetected("migration/corruption detected"),
    CrashRecoveryBegins("crash recovery begins"),
    BackupExportCompletes("backup/export completes"),
    SupportDebugDiagnosticRequested("support/debug diagnostic requested"),
    RecordDecryptCompletes("record decrypt completes"),
    RecordEncryptCompletes("record encrypt completes"),
    SessionQueried("session queried"),
}

enum class SkaldVaultV1VaultClearWipeRequirement(val label: String) {
    SensitiveValueClassModeled("sensitive value class modeled"),
    LifecycleTriggerModeled("lifecycle trigger modeled"),
    ActualClearImplementationRequiredFutureOnly("actual clear implementation is required later"),
    ActualZeroizationImplementationRequiredFutureOnly("actual zeroization implementation is required later"),
    JvmZeroizationCannotBeProvenByModel("JVM zeroization cannot be proven by this model"),
    ProviderOwnedClearReviewRequired("provider-owned clear review required"),
    StorageServiceClearReviewRequired("storage-service clear review required"),
    SessionInvalidationReviewRequired("session invalidation review required"),
    RedactionDiagnosticClearReviewRequired("redaction/diagnostic staging clear review required"),
    FailClosedLockRequired("fail-closed lock required"),
    RawSensitiveValuesRejected("raw sensitive values are rejected"),
    UnlockPersistenceProviderSelectionRemainDisabled("unlock, persistence, and provider selection remain disabled"),
    MainnetRemainsDisabled("mainnet remains disabled"),
}

enum class SkaldVaultV1VaultClearWipeStrategyClass(val label: String) {
    ForbiddenValueMustNeverBeAccepted("forbidden value; must never be accepted"),
    NoRealValuePresentNoWipePossible("no real value present; no wipe possible"),
    ModelOnlyRequirement("model-only requirement"),
    BestEffortFutureJvmClearRequired("best-effort future JVM clear required"),
    BestEffortFutureNativePlatformClearRequired("best-effort future native/platform clear required"),
    ProviderOwnedClearRequired("provider-owned clear required"),
    StorageServiceClearRequired("storage-service clear required"),
    SessionTokenInvalidationRequired("session-token invalidation required"),
    RedactionOnlyClearRequired("redaction-only clear required"),
    DeleteStagedReferenceRequired("delete staged reference required"),
    UnsupportedFailClosed("unsupported/fail-closed"),
    ImpossibleToProveJvmZeroizationLimitation("impossible-to-prove JVM zeroization limitation"),
}

enum class SkaldVaultV1VaultClearWipeLimitation(val label: String) {
    ModelOnlyNoRuntimeClear("model only; no runtime clear"),
    NoRawSensitiveValuesAccepted("raw sensitive values are not accepted"),
    NoMutableSensitiveBuffersAccepted("mutable sensitive buffers are not accepted"),
    NoActualMemoryZeroization("actual memory zeroization is not implemented"),
    JvmCopiesCannotBeProvenCleared("JVM/Kotlin copies cannot be proven cleared"),
    NoProviderClearCall("provider clear calls are not implemented"),
    NoStorageClearCall("storage clear calls are not implemented"),
    NoSessionInvalidationImplementation("session invalidation is not implemented"),
    NoFilesystemStorageOrSettings("filesystem, storage, and Settings persistence are absent"),
    NoUnlockPersistenceOrProviderSelection("unlock, persistence, and provider selection remain unavailable"),
}

enum class SkaldVaultV1VaultClearWipeBlocker(val label: String) {
    ClearWipeStrategyStillDisabled("clear/wipe strategy remains still-disabled"),
    ActualClearImplementationMissing("actual clear implementation is missing"),
    ActualZeroizationImplementationMissing("actual zeroization implementation is missing"),
    JvmZeroizationUnproven("JVM zeroization is unproven"),
    NativeZeroizationUnavailable("native/platform zeroization is unavailable"),
    ProviderClearUnavailable("provider-owned clear is unavailable"),
    StorageClearUnavailable("storage-service clear is unavailable"),
    SessionInvalidationUnavailable("session invalidation is unavailable"),
    PassphrasePolicyInputBlocked("passphrase policy still blocks input"),
    RedactionLeakageNonLogging("redaction/leakage policy remains non-logging"),
    LockSessionUnlockBlocked("lock/session lifecycle remains unlock-blocked"),
    PersistenceReadinessBlocked("persistence readiness remains blocked"),
    ProviderSelectionDisabled("provider selection remains disabled"),
    DisabledStorageServiceFacadeStillDisabled("disabled storage service facade remains still-disabled"),
    WarningOnlyEvidenceRejected("warning-only evidence cannot enable clear/wipe readiness"),
    UserConsentOverrideRejected("user consent cannot override missing hard gates"),
    RawSensitiveValueRejected("raw sensitive value rejected"),
    MainnetUnavailable("mainnet remains unavailable"),
}

enum class SkaldVaultV1VaultClearWipeWarning(val label: String) {
    EvidenceOnly("clear/wipe result is evidence only"),
    NoRealSensitiveValuePresent("no real sensitive value is present"),
    FutureImplementationRequiresReview("future clear/wipe implementation requires review"),
    JvmZeroizationCannotBeGuaranteed("JVM zeroization cannot be guaranteed by this model"),
    FailClosedOnLifecycleOrFailureEvent("lifecycle and failure events must fail closed"),
}

data class SkaldVaultV1VaultClearWipeCapability(
    val actualClearImplemented: Boolean,
    val actualZeroizationImplemented: Boolean,
    val jvmZeroizationProven: Boolean,
    val nativeZeroizationAvailable: Boolean,
    val providerClearAvailable: Boolean,
    val storageClearAvailable: Boolean,
    val sessionInvalidationAvailable: Boolean,
    val passphraseClearAvailable: Boolean,
    val keyMaterialClearAvailable: Boolean,
    val decryptedRecordClearAvailable: Boolean,
    val diagnosticBufferClearAvailable: Boolean,
    val unlockAvailable: Boolean,
    val activeSessionAvailable: Boolean,
    val decryptedKeyMaterialPresent: Boolean,
    val providerSelectable: Boolean,
    val productionProviderSelected: Boolean,
    val providerCryptoAvailable: Boolean,
    val vaultCreationAvailable: Boolean,
    val vaultUnlockAvailable: Boolean,
    val vaultPersistenceAvailable: Boolean,
    val secureSecretStorageAvailable: Boolean,
    val secureMetadataStorageAvailable: Boolean,
    val mainnetAvailable: Boolean,
) {
    companion object {
        val StillDisabled = SkaldVaultV1VaultClearWipeCapability(
            actualClearImplemented = false,
            actualZeroizationImplemented = false,
            jvmZeroizationProven = false,
            nativeZeroizationAvailable = false,
            providerClearAvailable = false,
            storageClearAvailable = false,
            sessionInvalidationAvailable = false,
            passphraseClearAvailable = false,
            keyMaterialClearAvailable = false,
            decryptedRecordClearAvailable = false,
            diagnosticBufferClearAvailable = false,
            unlockAvailable = false,
            activeSessionAvailable = false,
            decryptedKeyMaterialPresent = false,
            providerSelectable = false,
            productionProviderSelected = false,
            providerCryptoAvailable = false,
            vaultCreationAvailable = false,
            vaultUnlockAvailable = false,
            vaultPersistenceAvailable = false,
            secureSecretStorageAvailable = false,
            secureMetadataStorageAvailable = false,
            mainnetAvailable = false,
        )
    }
}

data class SkaldVaultV1VaultClearWipePolicySummary(
    val policyId: String,
    val policyVersion: Int,
    val valueKinds: Set<SkaldVaultV1VaultClearWipeValueKind>,
    val eventKinds: Set<SkaldVaultV1VaultClearWipeEventKind>,
    val strategyClasses: Set<SkaldVaultV1VaultClearWipeStrategyClass>,
    val requirements: Set<SkaldVaultV1VaultClearWipeRequirement>,
    val limitations: Set<SkaldVaultV1VaultClearWipeLimitation>,
    val stillDisabled: Boolean,
)

class SkaldVaultV1VaultClearWipePolicyToken internal constructor(
    val policyId: String,
    val valueKind: SkaldVaultV1VaultClearWipeValueKind?,
    val eventKind: SkaldVaultV1VaultClearWipeEventKind?,
    val strategyClass: SkaldVaultV1VaultClearWipeStrategyClass,
    val containsRawSensitiveValue: Boolean = false,
    val containsPassphraseMaterial: Boolean = false,
    val containsKeyMaterial: Boolean = false,
    val containsProviderKeyMaterial: Boolean = false,
    val containsRawBuffer: Boolean = false,
    val containsRootText: Boolean = false,
    val containsPlannedLocationText: Boolean = false,
    val containsRecordIdentifier: Boolean = false,
    val containsPayload: Boolean = false,
    val containsRawBytes: Boolean = false,
) {
    override fun toString(): String =
        "SkaldVaultV1VaultClearWipePolicyToken(" +
            "policyId=$policyId, " +
            "valueKind=${valueKind?.name ?: "none"}, " +
            "eventKind=${eventKind?.name ?: "none"}, " +
            "strategyClass=${strategyClass.name}, " +
            "redacted=true)"
}

data class SkaldVaultV1VaultClearWipeEvidence(
    val policyId: String,
    val policyVersion: Int,
    val status: SkaldVaultV1VaultClearWipeStatus,
    val decision: SkaldVaultV1VaultClearWipeDecision,
    val source: SkaldVaultV1VaultClearWipeSource,
    val valueKind: SkaldVaultV1VaultClearWipeValueKind?,
    val eventKind: SkaldVaultV1VaultClearWipeEventKind?,
    val strategyClass: SkaldVaultV1VaultClearWipeStrategyClass,
    val requirements: Set<SkaldVaultV1VaultClearWipeRequirement>,
    val limitations: Set<SkaldVaultV1VaultClearWipeLimitation>,
    val blockers: Set<SkaldVaultV1VaultClearWipeBlocker>,
    val warnings: Set<SkaldVaultV1VaultClearWipeWarning>,
    val capability: SkaldVaultV1VaultClearWipeCapability,
    val policySummary: SkaldVaultV1VaultClearWipePolicySummary,
    val policyTokenEvidence: SkaldVaultV1VaultClearWipePolicyToken,
    val passphrasePolicyEvidenceConsumed: Boolean,
    val redactionLeakageEvidenceConsumed: Boolean,
    val lockSessionLifecycleEvidenceConsumed: Boolean,
    val persistenceReadinessEvidenceConsumed: Boolean,
    val disabledProviderFacadeEvidenceConsumed: Boolean,
    val disabledStorageServiceEvidenceConsumed: Boolean,
    val secureStorageEvidenceConsumed: Boolean,
    val secureMetadataEvidenceConsumed: Boolean,
    val dependencyReadinessEvidenceConsumed: Boolean,
    val clearWipeStrategyBoundaryModeled: Boolean = true,
    val clearWipeStrategyStillDisabled: Boolean = true,
    val clearWipeStrategyDoesNotAcceptRawSensitiveValues: Boolean = true,
    val clearWipeStrategyDoesNotClearRealMemory: Boolean = true,
    val clearWipeStrategyDoesNotProveJvmZeroization: Boolean = true,
    val clearWipeStrategyDoesNotEnableUnlock: Boolean = true,
    val clearWipeStrategyDoesNotEnablePersistence: Boolean = true,
    val clearWipeStrategyDoesNotEnableProviderSelection: Boolean = true,
    val clearWipeFailureVocabularyModeled: Boolean = true,
    val actualClearReady: Boolean = false,
    val wipeReady: Boolean = false,
    val zeroizationReady: Boolean = false,
    val keyMaterialClearReady: Boolean = false,
    val passphraseClearReady: Boolean = false,
    val unlockReady: Boolean = false,
    val providerReady: Boolean = false,
    val persistenceReady: Boolean = false,
    val mainnetReady: Boolean = false,
) {
    override fun toString(): String =
        "SkaldVaultV1VaultClearWipeEvidence(" +
            "policyId=$policyId, " +
            "status=${status.name}, " +
            "decision=${decision.name}, " +
            "source=${source.name}, " +
            "valueKind=${valueKind?.name ?: "none"}, " +
            "eventKind=${eventKind?.name ?: "none"}, " +
            "strategyClass=${strategyClass.name}, " +
            "capability=still-disabled, " +
            "policyTokenEvidence=$policyTokenEvidence)"
}

sealed class SkaldVaultV1VaultClearWipeResult<out T> {
    data class Blocked<out T>(val value: T) : SkaldVaultV1VaultClearWipeResult<T>() {
        override fun toString(): String =
            "SkaldVaultV1VaultClearWipeResult.Blocked(value=$value)"
    }

    data class Rejected(
        val reason: SkaldVaultV1VaultClearWipeFailureReason,
        val status: SkaldVaultV1VaultClearWipeStatus,
        val source: SkaldVaultV1VaultClearWipeSource,
        val safeMessage: String,
    ) : SkaldVaultV1VaultClearWipeResult<Nothing>() {
        override fun toString(): String =
            "SkaldVaultV1VaultClearWipeResult.Rejected(reason=${reason.name}, status=${status.name})"
    }
}

enum class SkaldVaultV1VaultClearWipeFailureReason(val label: String) {
    EmptyEvidenceRejected("empty clear/wipe evidence rejected"),
    RawSensitiveValueRejected("raw sensitive value rejected"),
    PassphraseInputRejected("passphrase-like input rejected"),
    PinInputRejected("PIN-like input rejected"),
    MnemonicInputRejected("mnemonic-like input rejected"),
    SeedMaterialRejected("seed-like material rejected"),
    RawKeyInputRejected("raw key-like input rejected"),
    RawEntropyInputRejected("raw entropy-like input rejected"),
    RawRecordInputRejected("raw record-like input rejected"),
    ProviderHandleInputRejected("provider handle-like input rejected"),
    StorageHandleInputRejected("storage handle-like input rejected"),
    MutableBufferInputRejected("mutable buffer-like input rejected"),
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
    RawClearWipeInputRejected("raw clear/wipe input rejected"),
}

class SkaldVaultV1VaultClearWipeRequest private constructor(
    val source: SkaldVaultV1VaultClearWipeSource,
    val valueKind: SkaldVaultV1VaultClearWipeValueKind?,
    val eventKind: SkaldVaultV1VaultClearWipeEventKind?,
    val strategyClass: SkaldVaultV1VaultClearWipeStrategyClass?,
    private val rawCandidate: String?,
    private val passphrasePolicyEvidence: SkaldVaultV1VaultPassphrasePolicyEvidence?,
    private val redactionLeakageEvidence: SkaldVaultV1VaultRedactionEvidence?,
    private val lockSessionLifecycleEvidence: SkaldVaultV1VaultLockSessionEvidence?,
    private val persistenceReadinessEvidence: SkaldVaultV1VaultPersistenceReadinessEvidence?,
    private val disabledProviderFacadeMetadata: SkaldVaultV1StillDisabledProviderFacadeMetadata?,
    private val disabledStorageServiceEvidence: SkaldVaultV1VaultStorageDisabledEvidence?,
    private val secureStorageCapability: SecureStorageCapability?,
    private val secureMetadataCapability: SecureMetadataPersistenceCapability?,
    private val dependencyProbeResult: VaultCryptoDependencyProbeResult?,
) {
    val rawCandidateRejected: Boolean
        get() = source == SkaldVaultV1VaultClearWipeSource.RawClearWipeCandidate

    internal fun rawCandidateOrNull(): String? = rawCandidate

    internal fun passphrasePolicyEvidenceOrNull(): SkaldVaultV1VaultPassphrasePolicyEvidence? =
        passphrasePolicyEvidence

    internal fun redactionLeakageEvidenceOrNull(): SkaldVaultV1VaultRedactionEvidence? =
        redactionLeakageEvidence

    internal fun lockSessionLifecycleEvidenceOrNull(): SkaldVaultV1VaultLockSessionEvidence? =
        lockSessionLifecycleEvidence

    internal fun persistenceReadinessEvidenceOrNull(): SkaldVaultV1VaultPersistenceReadinessEvidence? =
        persistenceReadinessEvidence

    internal fun disabledProviderFacadeMetadataOrNull(): SkaldVaultV1StillDisabledProviderFacadeMetadata? =
        disabledProviderFacadeMetadata

    internal fun disabledStorageServiceEvidenceOrNull(): SkaldVaultV1VaultStorageDisabledEvidence? =
        disabledStorageServiceEvidence

    internal fun secureStorageCapabilityOrNull(): SecureStorageCapability? = secureStorageCapability

    internal fun secureMetadataCapabilityOrNull(): SecureMetadataPersistenceCapability? = secureMetadataCapability

    internal fun dependencyProbeResultOrNull(): VaultCryptoDependencyProbeResult? = dependencyProbeResult

    override fun toString(): String =
        "SkaldVaultV1VaultClearWipeRequest(" +
            "source=${source.name}, " +
            "valueKind=${valueKind?.name ?: "none"}, " +
            "eventKind=${eventKind?.name ?: "none"}, " +
            "strategyClass=${strategyClass?.name ?: "none"}, " +
            "rawCandidate=REDACTED)"

    companion object {
        fun noEvidence(): SkaldVaultV1VaultClearWipeRequest =
            SkaldVaultV1VaultClearWipeRequest(
                source = SkaldVaultV1VaultClearWipeSource.NoEvidence,
                valueKind = null,
                eventKind = null,
                strategyClass = null,
                rawCandidate = null,
                passphrasePolicyEvidence = null,
                redactionLeakageEvidence = null,
                lockSessionLifecycleEvidence = null,
                persistenceReadinessEvidence = null,
                disabledProviderFacadeMetadata = null,
                disabledStorageServiceEvidence = null,
                secureStorageCapability = null,
                secureMetadataCapability = null,
                dependencyProbeResult = null,
            )

        fun summary(): SkaldVaultV1VaultClearWipeRequest =
            noEvidence().copyFor(source = SkaldVaultV1VaultClearWipeSource.PolicySummary)

        fun forValueAndEvent(
            valueKind: SkaldVaultV1VaultClearWipeValueKind,
            eventKind: SkaldVaultV1VaultClearWipeEventKind,
        ): SkaldVaultV1VaultClearWipeRequest =
            noEvidence().copyFor(
                source = SkaldVaultV1VaultClearWipeSource.ValueAndEvent,
                valueKind = valueKind,
                eventKind = eventKind,
            )

        fun forStrategy(
            strategyClass: SkaldVaultV1VaultClearWipeStrategyClass,
        ): SkaldVaultV1VaultClearWipeRequest =
            noEvidence().copyFor(
                source = SkaldVaultV1VaultClearWipeSource.StrategyClass,
                strategyClass = strategyClass,
            )

        fun fromEvidence(
            passphrasePolicyEvidence: SkaldVaultV1VaultPassphrasePolicyEvidence? = null,
            redactionLeakageEvidence: SkaldVaultV1VaultRedactionEvidence? = null,
            lockSessionLifecycleEvidence: SkaldVaultV1VaultLockSessionEvidence? = null,
            persistenceReadinessEvidence: SkaldVaultV1VaultPersistenceReadinessEvidence? = null,
            disabledProviderFacadeMetadata: SkaldVaultV1StillDisabledProviderFacadeMetadata? = null,
            disabledStorageServiceEvidence: SkaldVaultV1VaultStorageDisabledEvidence? = null,
            secureStorageCapability: SecureStorageCapability? = null,
            secureMetadataCapability: SecureMetadataPersistenceCapability? = null,
            dependencyProbeResult: VaultCryptoDependencyProbeResult? = null,
            valueKind: SkaldVaultV1VaultClearWipeValueKind? = null,
            eventKind: SkaldVaultV1VaultClearWipeEventKind? = null,
        ): SkaldVaultV1VaultClearWipeRequest =
            SkaldVaultV1VaultClearWipeRequest(
                source = SkaldVaultV1VaultClearWipeSource.ComposedTypedEvidence,
                valueKind = valueKind,
                eventKind = eventKind,
                strategyClass = null,
                rawCandidate = null,
                passphrasePolicyEvidence = passphrasePolicyEvidence,
                redactionLeakageEvidence = redactionLeakageEvidence,
                lockSessionLifecycleEvidence = lockSessionLifecycleEvidence,
                persistenceReadinessEvidence = persistenceReadinessEvidence,
                disabledProviderFacadeMetadata = disabledProviderFacadeMetadata,
                disabledStorageServiceEvidence = disabledStorageServiceEvidence,
                secureStorageCapability = secureStorageCapability,
                secureMetadataCapability = secureMetadataCapability,
                dependencyProbeResult = dependencyProbeResult,
            )

        fun rawClearWipeCandidate(rawCandidate: String?): SkaldVaultV1VaultClearWipeRequest =
            noEvidence().copyFor(
                source = SkaldVaultV1VaultClearWipeSource.RawClearWipeCandidate,
                rawCandidate = rawCandidate,
            )
    }

    private fun copyFor(
        source: SkaldVaultV1VaultClearWipeSource = this.source,
        valueKind: SkaldVaultV1VaultClearWipeValueKind? = this.valueKind,
        eventKind: SkaldVaultV1VaultClearWipeEventKind? = this.eventKind,
        strategyClass: SkaldVaultV1VaultClearWipeStrategyClass? = this.strategyClass,
        rawCandidate: String? = this.rawCandidate,
    ): SkaldVaultV1VaultClearWipeRequest =
        SkaldVaultV1VaultClearWipeRequest(
            source = source,
            valueKind = valueKind,
            eventKind = eventKind,
            strategyClass = strategyClass,
            rawCandidate = rawCandidate,
            passphrasePolicyEvidence = passphrasePolicyEvidence,
            redactionLeakageEvidence = redactionLeakageEvidence,
            lockSessionLifecycleEvidence = lockSessionLifecycleEvidence,
            persistenceReadinessEvidence = persistenceReadinessEvidence,
            disabledProviderFacadeMetadata = disabledProviderFacadeMetadata,
            disabledStorageServiceEvidence = disabledStorageServiceEvidence,
            secureStorageCapability = secureStorageCapability,
            secureMetadataCapability = secureMetadataCapability,
            dependencyProbeResult = dependencyProbeResult,
        )
}

object SkaldVaultV1ClearWipeStrategyPolicy : SkaldVaultV1ClearWipeStrategyBoundary {
    const val POLICY_ID = "skald-vault-v1-clear-wipe-strategy-boundary-v1"
    const val POLICY_VERSION = 1

    fun currentPolicySummary(): SkaldVaultV1VaultClearWipePolicySummary =
        SkaldVaultV1VaultClearWipePolicySummary(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            valueKinds = SkaldVaultV1VaultClearWipeValueKind.entries.toSet(),
            eventKinds = SkaldVaultV1VaultClearWipeEventKind.entries.toSet(),
            strategyClasses = SkaldVaultV1VaultClearWipeStrategyClass.entries.toSet(),
            requirements = SkaldVaultV1VaultClearWipeRequirement.entries.toSet(),
            limitations = SkaldVaultV1VaultClearWipeLimitation.entries.toSet(),
            stillDisabled = true,
        )

    override fun evaluate(
        request: SkaldVaultV1VaultClearWipeRequest,
    ): SkaldVaultV1VaultClearWipeResult<SkaldVaultV1VaultClearWipeEvidence> =
        when (request.source) {
            SkaldVaultV1VaultClearWipeSource.RawClearWipeCandidate -> rejectRawCandidate(request)
            else -> blocked(request)
        }

    private fun blocked(
        request: SkaldVaultV1VaultClearWipeRequest,
    ): SkaldVaultV1VaultClearWipeResult.Blocked<SkaldVaultV1VaultClearWipeEvidence> {
        val strategy = request.strategyClass ?: strategyFor(
            valueKind = request.valueKind,
            eventKind = request.eventKind,
        )
        val requirements = requirementsFor(request, strategy)
        val limitations = SkaldVaultV1VaultClearWipeLimitation.entries.toSet()
        val blockers = blockersFor(request, strategy)
        val status = statusFor(request)
        val decision = decisionFor(request, strategy)
        val policyTokenEvidence = SkaldVaultV1VaultClearWipePolicyToken(
            policyId = POLICY_ID,
            valueKind = request.valueKind,
            eventKind = request.eventKind,
            strategyClass = strategy,
        )

        return SkaldVaultV1VaultClearWipeResult.Blocked(
            SkaldVaultV1VaultClearWipeEvidence(
                policyId = POLICY_ID,
                policyVersion = POLICY_VERSION,
                status = status,
                decision = decision,
                source = request.source,
                valueKind = request.valueKind,
                eventKind = request.eventKind,
                strategyClass = strategy,
                requirements = requirements,
                limitations = limitations,
                blockers = blockers,
                warnings = SkaldVaultV1VaultClearWipeWarning.entries.toSet(),
                capability = SkaldVaultV1VaultClearWipeCapability.StillDisabled,
                policySummary = currentPolicySummary(),
                policyTokenEvidence = policyTokenEvidence,
                passphrasePolicyEvidenceConsumed = request.passphrasePolicyEvidenceOrNull() != null,
                redactionLeakageEvidenceConsumed = request.redactionLeakageEvidenceOrNull() != null,
                lockSessionLifecycleEvidenceConsumed = request.lockSessionLifecycleEvidenceOrNull() != null,
                persistenceReadinessEvidenceConsumed = request.persistenceReadinessEvidenceOrNull() != null,
                disabledProviderFacadeEvidenceConsumed = request.disabledProviderFacadeMetadataOrNull() != null,
                disabledStorageServiceEvidenceConsumed = request.disabledStorageServiceEvidenceOrNull() != null,
                secureStorageEvidenceConsumed = request.secureStorageCapabilityOrNull() != null,
                secureMetadataEvidenceConsumed = request.secureMetadataCapabilityOrNull() != null,
                dependencyReadinessEvidenceConsumed = request.dependencyProbeResultOrNull() != null,
            ),
        )
    }

    private fun rejectRawCandidate(
        request: SkaldVaultV1VaultClearWipeRequest,
    ): SkaldVaultV1VaultClearWipeResult.Rejected =
        SkaldVaultV1VaultClearWipeResult.Rejected(
            reason = classifyRawCandidate(request.rawCandidateOrNull()),
            status = SkaldVaultV1VaultClearWipeStatus.RawCandidateRejected,
            source = SkaldVaultV1VaultClearWipeSource.RawClearWipeCandidate,
            safeMessage = "Raw clear/wipe material is not accepted by this model-only boundary.",
        )

    private fun statusFor(
        request: SkaldVaultV1VaultClearWipeRequest,
    ): SkaldVaultV1VaultClearWipeStatus =
        when (request.source) {
            SkaldVaultV1VaultClearWipeSource.NoEvidence ->
                SkaldVaultV1VaultClearWipeStatus.NoEvidenceAvailable
            SkaldVaultV1VaultClearWipeSource.PolicySummary ->
                SkaldVaultV1VaultClearWipeStatus.PolicySummaryModeled
            SkaldVaultV1VaultClearWipeSource.ValueAndEvent ->
                SkaldVaultV1VaultClearWipeStatus.RequirementModeled
            SkaldVaultV1VaultClearWipeSource.StrategyClass ->
                SkaldVaultV1VaultClearWipeStatus.StrategyModeled
            SkaldVaultV1VaultClearWipeSource.ComposedTypedEvidence ->
                SkaldVaultV1VaultClearWipeStatus.ClearWipeBlockedStillDisabled
            SkaldVaultV1VaultClearWipeSource.RawClearWipeCandidate ->
                SkaldVaultV1VaultClearWipeStatus.RawCandidateRejected
        }

    private fun decisionFor(
        request: SkaldVaultV1VaultClearWipeRequest,
        strategy: SkaldVaultV1VaultClearWipeStrategyClass,
    ): SkaldVaultV1VaultClearWipeDecision =
        when {
            request.source == SkaldVaultV1VaultClearWipeSource.NoEvidence ||
                request.source == SkaldVaultV1VaultClearWipeSource.ComposedTypedEvidence ->
                SkaldVaultV1VaultClearWipeDecision.BlockedFailClosed
            strategy == SkaldVaultV1VaultClearWipeStrategyClass.ForbiddenValueMustNeverBeAccepted ->
                SkaldVaultV1VaultClearWipeDecision.ForbiddenValueRejected
            strategy == SkaldVaultV1VaultClearWipeStrategyClass.UnsupportedFailClosed ||
                strategy == SkaldVaultV1VaultClearWipeStrategyClass.ImpossibleToProveJvmZeroizationLimitation ->
                SkaldVaultV1VaultClearWipeDecision.UnsupportedFailClosed
            else -> SkaldVaultV1VaultClearWipeDecision.ModelOnlyRequirement
        }

    private fun strategyFor(
        valueKind: SkaldVaultV1VaultClearWipeValueKind?,
        eventKind: SkaldVaultV1VaultClearWipeEventKind?,
    ): SkaldVaultV1VaultClearWipeStrategyClass =
        when {
            valueKind == null && eventKind == null ->
                SkaldVaultV1VaultClearWipeStrategyClass.NoRealValuePresentNoWipePossible
            valueKind in forbiddenInputKinds ->
                SkaldVaultV1VaultClearWipeStrategyClass.ForbiddenValueMustNeverBeAccepted
            valueKind in providerOwnedKinds ->
                SkaldVaultV1VaultClearWipeStrategyClass.ProviderOwnedClearRequired
            valueKind in storageOwnedKinds ->
                SkaldVaultV1VaultClearWipeStrategyClass.StorageServiceClearRequired
            valueKind == SkaldVaultV1VaultClearWipeValueKind.LockSessionToken ->
                SkaldVaultV1VaultClearWipeStrategyClass.SessionTokenInvalidationRequired
            valueKind == SkaldVaultV1VaultClearWipeValueKind.RedactionDiagnosticStagingValue ||
                eventKind == SkaldVaultV1VaultClearWipeEventKind.SupportDebugDiagnosticRequested ->
                SkaldVaultV1VaultClearWipeStrategyClass.RedactionOnlyClearRequired
            eventKind in referenceDeletionEvents ->
                SkaldVaultV1VaultClearWipeStrategyClass.DeleteStagedReferenceRequired
            valueKind in futurePlatformHandleKinds ->
                SkaldVaultV1VaultClearWipeStrategyClass.BestEffortFutureNativePlatformClearRequired
            valueKind in jvmClearKinds ->
                SkaldVaultV1VaultClearWipeStrategyClass.BestEffortFutureJvmClearRequired
            else -> SkaldVaultV1VaultClearWipeStrategyClass.ModelOnlyRequirement
        }

    private fun requirementsFor(
        request: SkaldVaultV1VaultClearWipeRequest,
        strategy: SkaldVaultV1VaultClearWipeStrategyClass,
    ): Set<SkaldVaultV1VaultClearWipeRequirement> =
        buildSet {
            add(SkaldVaultV1VaultClearWipeRequirement.RawSensitiveValuesRejected)
            add(SkaldVaultV1VaultClearWipeRequirement.UnlockPersistenceProviderSelectionRemainDisabled)
            add(SkaldVaultV1VaultClearWipeRequirement.MainnetRemainsDisabled)
            add(SkaldVaultV1VaultClearWipeRequirement.JvmZeroizationCannotBeProvenByModel)
            if (request.valueKind != null) {
                add(SkaldVaultV1VaultClearWipeRequirement.SensitiveValueClassModeled)
            }
            if (request.eventKind != null) {
                add(SkaldVaultV1VaultClearWipeRequirement.LifecycleTriggerModeled)
                add(SkaldVaultV1VaultClearWipeRequirement.FailClosedLockRequired)
            }
            when (strategy) {
                SkaldVaultV1VaultClearWipeStrategyClass.ProviderOwnedClearRequired ->
                    add(SkaldVaultV1VaultClearWipeRequirement.ProviderOwnedClearReviewRequired)
                SkaldVaultV1VaultClearWipeStrategyClass.StorageServiceClearRequired ->
                    add(SkaldVaultV1VaultClearWipeRequirement.StorageServiceClearReviewRequired)
                SkaldVaultV1VaultClearWipeStrategyClass.SessionTokenInvalidationRequired ->
                    add(SkaldVaultV1VaultClearWipeRequirement.SessionInvalidationReviewRequired)
                SkaldVaultV1VaultClearWipeStrategyClass.RedactionOnlyClearRequired ->
                    add(SkaldVaultV1VaultClearWipeRequirement.RedactionDiagnosticClearReviewRequired)
                SkaldVaultV1VaultClearWipeStrategyClass.BestEffortFutureJvmClearRequired ->
                    add(SkaldVaultV1VaultClearWipeRequirement.ActualClearImplementationRequiredFutureOnly)
                SkaldVaultV1VaultClearWipeStrategyClass.BestEffortFutureNativePlatformClearRequired ->
                    add(SkaldVaultV1VaultClearWipeRequirement.ActualZeroizationImplementationRequiredFutureOnly)
                else -> Unit
            }
        }

    private fun blockersFor(
        request: SkaldVaultV1VaultClearWipeRequest,
        strategy: SkaldVaultV1VaultClearWipeStrategyClass,
    ): Set<SkaldVaultV1VaultClearWipeBlocker> =
        buildSet {
            add(SkaldVaultV1VaultClearWipeBlocker.ClearWipeStrategyStillDisabled)
            add(SkaldVaultV1VaultClearWipeBlocker.ActualClearImplementationMissing)
            add(SkaldVaultV1VaultClearWipeBlocker.ActualZeroizationImplementationMissing)
            add(SkaldVaultV1VaultClearWipeBlocker.JvmZeroizationUnproven)
            add(SkaldVaultV1VaultClearWipeBlocker.NativeZeroizationUnavailable)
            add(SkaldVaultV1VaultClearWipeBlocker.ProviderSelectionDisabled)
            add(SkaldVaultV1VaultClearWipeBlocker.MainnetUnavailable)
            if (request.passphrasePolicyEvidenceOrNull() != null) {
                add(SkaldVaultV1VaultClearWipeBlocker.PassphrasePolicyInputBlocked)
            }
            if (request.redactionLeakageEvidenceOrNull() != null) {
                add(SkaldVaultV1VaultClearWipeBlocker.RedactionLeakageNonLogging)
            }
            if (request.lockSessionLifecycleEvidenceOrNull() != null) {
                add(SkaldVaultV1VaultClearWipeBlocker.LockSessionUnlockBlocked)
            }
            if (request.persistenceReadinessEvidenceOrNull() != null) {
                add(SkaldVaultV1VaultClearWipeBlocker.PersistenceReadinessBlocked)
            }
            if (request.disabledStorageServiceEvidenceOrNull() != null) {
                add(SkaldVaultV1VaultClearWipeBlocker.DisabledStorageServiceFacadeStillDisabled)
            }
            when (strategy) {
                SkaldVaultV1VaultClearWipeStrategyClass.ForbiddenValueMustNeverBeAccepted ->
                    add(SkaldVaultV1VaultClearWipeBlocker.RawSensitiveValueRejected)
                SkaldVaultV1VaultClearWipeStrategyClass.ProviderOwnedClearRequired ->
                    add(SkaldVaultV1VaultClearWipeBlocker.ProviderClearUnavailable)
                SkaldVaultV1VaultClearWipeStrategyClass.StorageServiceClearRequired ->
                    add(SkaldVaultV1VaultClearWipeBlocker.StorageClearUnavailable)
                SkaldVaultV1VaultClearWipeStrategyClass.SessionTokenInvalidationRequired ->
                    add(SkaldVaultV1VaultClearWipeBlocker.SessionInvalidationUnavailable)
                else -> Unit
            }
            add(SkaldVaultV1VaultClearWipeBlocker.WarningOnlyEvidenceRejected)
            add(SkaldVaultV1VaultClearWipeBlocker.UserConsentOverrideRejected)
        }

    private fun classifyRawCandidate(raw: String?): SkaldVaultV1VaultClearWipeFailureReason {
        val value = raw?.trim()
        if (value.isNullOrEmpty()) {
            return SkaldVaultV1VaultClearWipeFailureReason.EmptyEvidenceRejected
        }
        val lower = value.lowercase()
        return when {
            lower.contains("passphrase") || lower.contains("password") ->
                SkaldVaultV1VaultClearWipeFailureReason.PassphraseInputRejected
            lower.contains("pin") ->
                SkaldVaultV1VaultClearWipeFailureReason.PinInputRejected
            lower.contains("mnemonic") ->
                SkaldVaultV1VaultClearWipeFailureReason.MnemonicInputRejected
            lower.contains("seed") ->
                SkaldVaultV1VaultClearWipeFailureReason.SeedMaterialRejected
            lower.contains("raw-key") ||
                lower.contains("key-material") ||
                lower.contains("private-key") ->
                SkaldVaultV1VaultClearWipeFailureReason.RawKeyInputRejected
            lower.contains("entropy") ->
                SkaldVaultV1VaultClearWipeFailureReason.RawEntropyInputRejected
            lower.contains("decrypted-record") ||
                lower.contains("record-plaintext") ||
                lower.contains("record-payload") ->
                SkaldVaultV1VaultClearWipeFailureReason.RawRecordInputRejected
            lower.contains("provider-handle") ||
                lower.contains("provider-session") ->
                SkaldVaultV1VaultClearWipeFailureReason.ProviderHandleInputRejected
            lower.contains("storage-handle") ||
                lower.contains("storage-session") ->
                SkaldVaultV1VaultClearWipeFailureReason.StorageHandleInputRejected
            lower.contains("bytearray") ||
                lower.contains("chararray") ||
                lower.contains("buffer") ->
                SkaldVaultV1VaultClearWipeFailureReason.MutableBufferInputRejected
            lower.startsWith("file:") ||
                lower.startsWith("http:") ||
                lower.startsWith("https:") ||
                lower.startsWith("content:") ->
                SkaldVaultV1VaultClearWipeFailureReason.LinkLikeInputRejected
            lower.contains("file-object") ||
                lower.contains("path-object") ->
                SkaldVaultV1VaultClearWipeFailureReason.PlatformObjectLikeInputRejected
            value.contains("..") ->
                SkaldVaultV1VaultClearWipeFailureReason.TraversalRejected
            value.startsWith("/") ||
                value.startsWith("\\") ||
                value.contains(":\\") ->
                SkaldVaultV1VaultClearWipeFailureReason.RawAbsoluteLocationInputRejected
            value.contains("/") ||
                value.contains("\\") ->
                SkaldVaultV1VaultClearWipeFailureReason.RawRelativeLocationInputRejected
            lower.contains("secret") ||
                lower.contains("credential") ||
                lower.contains("token") ->
                SkaldVaultV1VaultClearWipeFailureReason.SecretMaterialRejected
            looksLikeWalletMaterial(value) ->
                SkaldVaultV1VaultClearWipeFailureReason.WalletMaterialRejected
            looksLikeBitcoinAddress(value) ->
                SkaldVaultV1VaultClearWipeFailureReason.BitcoinAddressLikeEvidenceRejected
            value.length == 64 && value.all { it.isHexDigit() } ->
                SkaldVaultV1VaultClearWipeFailureReason.TransactionLikeEvidenceRejected
            value.any { !it.isSupportedEvidenceCharacter() } ->
                SkaldVaultV1VaultClearWipeFailureReason.UnsupportedCharactersRejected
            else -> SkaldVaultV1VaultClearWipeFailureReason.RawClearWipeInputRejected
        }
    }

    private val forbiddenInputKinds = setOf(
        SkaldVaultV1VaultClearWipeValueKind.PassphraseInputBuffer,
        SkaldVaultV1VaultClearWipeValueKind.PinInputBuffer,
        SkaldVaultV1VaultClearWipeValueKind.MnemonicText,
        SkaldVaultV1VaultClearWipeValueKind.SeedBytes,
        SkaldVaultV1VaultClearWipeValueKind.PrivateKeyBytes,
        SkaldVaultV1VaultClearWipeValueKind.XprvTprvWifText,
        SkaldVaultV1VaultClearWipeValueKind.NostrNsecPrivateKeyMaterial,
        SkaldVaultV1VaultClearWipeValueKind.DescriptorPrivateMaterial,
    )

    private val providerOwnedKinds = setOf(
        SkaldVaultV1VaultClearWipeValueKind.ProviderRootKey,
        SkaldVaultV1VaultClearWipeValueKind.VaultRootKey,
        SkaldVaultV1VaultClearWipeValueKind.MetadataEncryptionKey,
        SkaldVaultV1VaultClearWipeValueKind.RecordEncryptionKey,
        SkaldVaultV1VaultClearWipeValueKind.BackupExportKey,
        SkaldVaultV1VaultClearWipeValueKind.KeyWrappingKey,
        SkaldVaultV1VaultClearWipeValueKind.RawKdfInput,
        SkaldVaultV1VaultClearWipeValueKind.RawKdfOutput,
        SkaldVaultV1VaultClearWipeValueKind.RawAeadKey,
        SkaldVaultV1VaultClearWipeValueKind.ProviderSessionHandle,
    )

    private val storageOwnedKinds = setOf(
        SkaldVaultV1VaultClearWipeValueKind.StorageSessionHandle,
        SkaldVaultV1VaultClearWipeValueKind.EncryptedRecordStagingBuffer,
        SkaldVaultV1VaultClearWipeValueKind.ManifestStagingBuffer,
        SkaldVaultV1VaultClearWipeValueKind.StorageIndexStagingBuffer,
    )

    private val futurePlatformHandleKinds = setOf(
        SkaldVaultV1VaultClearWipeValueKind.FuturePlatformWrappedKeyReference,
        SkaldVaultV1VaultClearWipeValueKind.FutureAndroidHardwareWrappedKeyHandle,
        SkaldVaultV1VaultClearWipeValueKind.FutureLinuxOptionalKeyWrappingHandle,
    )

    private val jvmClearKinds = setOf(
        SkaldVaultV1VaultClearWipeValueKind.RandomEntropyBuffer,
        SkaldVaultV1VaultClearWipeValueKind.DecryptedVaultRecord,
        SkaldVaultV1VaultClearWipeValueKind.DecryptedMetadataRecord,
        SkaldVaultV1VaultClearWipeValueKind.PassphraseRetryThrottleState,
        SkaldVaultV1VaultClearWipeValueKind.WalletLabelTransactionNoteSensitiveMetadata,
        SkaldVaultV1VaultClearWipeValueKind.BackendCredentialStagingValue,
        SkaldVaultV1VaultClearWipeValueKind.LightningCashuNostrCredentialStagingValue,
    )

    private val referenceDeletionEvents = setOf(
        SkaldVaultV1VaultClearWipeEventKind.RecordDecryptCompletes,
        SkaldVaultV1VaultClearWipeEventKind.RecordEncryptCompletes,
        SkaldVaultV1VaultClearWipeEventKind.BackupExportCompletes,
        SkaldVaultV1VaultClearWipeEventKind.CrashRecoveryBegins,
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
