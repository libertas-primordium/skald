package com.libertasprimordium.skald.security

interface SkaldVaultV1PassphrasePolicyBoundary {
    fun evaluate(
        request: SkaldVaultV1VaultPassphrasePolicyRequest,
    ): SkaldVaultV1VaultPassphrasePolicyResult<SkaldVaultV1VaultPassphrasePolicyEvidence>
}

enum class SkaldVaultV1VaultPassphrasePolicySource(val label: String) {
    NoEvidence("no passphrase policy evidence supplied"),
    PolicySummary("passphrase policy summary requested"),
    NormalizationPolicyIdentifier("normalization policy identifier requested"),
    EncodingPolicyIdentifier("encoding policy identifier requested"),
    RetryThrottlePolicyStatus("retry and throttle policy status requested"),
    ComposedTypedEvidence("typed passphrase policy evidence supplied"),
    RawPassphraseCandidate("raw passphrase candidate"),
}

enum class SkaldVaultV1VaultPassphrasePolicyStatus(val label: String) {
    BlockedFailClosed("passphrase policy remains blocked and fail-closed"),
    InputNotAccepted("passphrase input is not accepted"),
    PolicyIdentifierModeled("policy identifier modeled"),
    FutureImplementationRequired("future implementation required"),
    ForbiddenByPolicy("forbidden by policy"),
    RawInputRejected("raw passphrase policy input rejected"),
}

enum class SkaldVaultV1VaultPassphrasePolicyDecision(
    val passphraseInputAccepted: Boolean,
    val unlockAvailable: Boolean,
    val mainnetAvailable: Boolean,
) {
    BlockedFailClosed(
        passphraseInputAccepted = false,
        unlockAvailable = false,
        mainnetAvailable = false,
    ),
    Forbidden(
        passphraseInputAccepted = false,
        unlockAvailable = false,
        mainnetAvailable = false,
    ),
    PolicyIdentifierModeled(
        passphraseInputAccepted = false,
        unlockAvailable = false,
        mainnetAvailable = false,
    ),
    FutureImplementationRequired(
        passphraseInputAccepted = false,
        unlockAvailable = false,
        mainnetAvailable = false,
    ),
    UnsupportedFailClosed(
        passphraseInputAccepted = false,
        unlockAvailable = false,
        mainnetAvailable = false,
    ),
}

enum class SkaldVaultV1VaultPassphraseInputClass(val label: String) {
    PassphraseInputNotProvided("passphrase input not provided"),
    PolicySummaryOnly("policy summary only"),
    NormalizationPolicyIdentifierOnly("normalization policy identifier only"),
    EncodingPolicyIdentifierOnly("encoding policy identifier only"),
    RetryThrottlePolicyIdentifierOnly("retry and throttle policy identifier only"),
    RawInputRejected("raw passphrase input rejected"),
}

enum class SkaldVaultV1VaultPassphraseNormalizationPolicy(
    val policyId: String,
    val executionAvailable: Boolean,
) {
    UnicodeNfcRequired(
        policyId = "unicode-nfc-utf8-no-controls-no-whitespace-v1",
        executionAvailable = false,
    ),
}

enum class SkaldVaultV1VaultPassphraseEncodingPolicy(
    val policyId: String,
    val executionAvailable: Boolean,
) {
    Utf8Required(
        policyId = "unicode-nfc-utf8-no-controls-no-whitespace-v1",
        executionAvailable = false,
    ),
}

enum class SkaldVaultV1VaultPassphraseRetryPolicy(val label: String) {
    RequiredFutureOnly("retry policy is required and future-only"),
}

enum class SkaldVaultV1VaultPassphraseThrottlePolicy(val label: String) {
    RequiredFutureOnly("throttle policy is required and future-only"),
}

enum class SkaldVaultV1VaultPassphraseLockoutPolicy(val label: String) {
    RequiresReviewOrExplicitRejection("lockout policy requires review or explicit rejection rationale"),
}

enum class SkaldVaultV1VaultPassphrasePolicyCategory(val label: String) {
    PassphraseInputNotAccepted("passphrase input is currently not accepted"),
    NormalizationPolicyIdentifierModeled("passphrase normalization policy identifier is modeled"),
    EncodingPolicyIdentifierModeled("passphrase encoding policy identifier is modeled"),
    MinimumPolicyFutureOnly("passphrase minimum policy is future-only"),
    MaximumPolicyFutureOnly("passphrase maximum policy is future-only"),
    ControlCharacterRejectionRequired("control-character rejection is required"),
    WhitespaceHandlingRequired("whitespace handling is required"),
    UnicodeNfcNormalizationRequired("Unicode NFC normalization is required"),
    Utf8EncodingRequired("UTF-8 encoding is required"),
    RetryPolicyRequired("retry policy is required"),
    ThrottlingPolicyRequired("throttling policy is required"),
    LockoutPolicyRequiredOrRejectedWithRationale("lockout policy is required or must be explicitly rejected"),
    MemoryLifetimePolicyRequired("passphrase memory lifetime policy is required"),
    ClearWipeStrategyRequired("clear/wipe strategy is required"),
    RedactionPolicyRequired("passphrase redaction policy is required"),
    UiEntryPolicyAbsent("passphrase UI entry policy is absent"),
    PassphraseStorageForbidden("passphrase storage is forbidden"),
    HashingFingerprintingForbidden("passphrase hashing or fingerprinting is forbidden"),
    LoggingForbidden("passphrase logging is forbidden"),
    BuildHistoryInclusionForbidden("passphrase build-history inclusion is forbidden"),
    CrashReportInclusionForbidden("passphrase crash-report inclusion is forbidden"),
    AnalyticsInclusionForbidden("passphrase analytics inclusion is forbidden"),
    BiometricConvenienceUnlockFutureOnly("biometric convenience unlock is future-only"),
    AndroidKeystoreWrappingFutureOnly("Android Keystore wrapping is future-only"),
    OsKeyringPasswordManagerPassphraseStorageRejected(
        "OS keyring and password-manager passphrase storage remains rejected",
    ),
    UnlockUnavailable("vault unlock remains unavailable"),
}

enum class SkaldVaultV1VaultPassphraseEvidenceSource(val label: String) {
    StandalonePolicy("standalone passphrase policy"),
    RedactionLeakage("redaction/leakage boundary"),
    LockSessionLifecycle("lock/session lifecycle boundary"),
    PersistenceReadiness("persistence readiness gate"),
    ProviderSelection("provider selection evidence"),
    ProviderAcceptance("provider acceptance evidence"),
    DisabledProviderFacade("disabled provider facade"),
    DependencyProbe("dependency probe"),
    Argon2idCalibration("Argon2id calibration policy"),
    AndroidCompatibilityEntropy("Android compatibility and entropy policy"),
    SecureStorage("secure secret storage boundary"),
    SecureMetadata("secure metadata boundary"),
    SourceGuard("source guard evidence"),
}

enum class SkaldVaultV1VaultPassphraseFailureReason(val label: String) {
    EmptyEvidenceRejected("empty passphrase policy evidence is rejected"),
    RawPassphraseInputRejected("raw passphrase input is rejected"),
    PinMaterialRejected("PIN-like input is rejected"),
    MnemonicMaterialRejected("mnemonic-like input is rejected"),
    SeedMaterialRejected("seed phrase-like input is rejected"),
    HashFingerprintEvidenceRejected("hash or fingerprint input is rejected"),
    BiometricEvidenceRejected("biometric evidence input is rejected"),
    KeyMaterialRejected("key material input is rejected"),
    ByteArrayLikeInputRejected("byte-array-like input is rejected"),
    RawAbsoluteLocationInputRejected("raw absolute location input is rejected"),
    RawRelativeLocationInputRejected("raw relative location input is rejected"),
    LinkLikeInputRejected("link-like input is rejected"),
    PlatformObjectLikeInputRejected("platform object-like input is rejected"),
    WalletMaterialRejected("wallet or key-looking material is rejected"),
    BitcoinAddressLikeEvidenceRejected("Bitcoin address-like evidence is rejected"),
    TraversalRejected("traversal-bearing input is rejected"),
    UnsupportedCharactersRejected("unsupported characters are rejected"),
    UnsupportedEvidenceRejected("unsupported passphrase policy evidence is rejected"),
}

enum class SkaldVaultV1VaultPassphraseBlocker(val label: String) {
    PassphraseInputRejected("passphrase input remains rejected"),
    PassphraseStorageForbidden("passphrase storage remains forbidden"),
    PassphraseHashFingerprintForbidden("passphrase hashing or fingerprinting remains forbidden"),
    PassphraseKdfExecutionUnavailable("passphrase KDF execution remains unavailable"),
    RetryPolicyMissing("retry policy implementation missing"),
    ThrottlePolicyMissing("throttle policy implementation missing"),
    LockoutPolicyMissing("lockout policy implementation or rejection review missing"),
    ClearWipeStrategyMissing("clear/wipe strategy review missing"),
    RedactionPolicyRequired("passphrase redaction policy required"),
    UiEntryAbsent("passphrase UI entry remains absent"),
    LockSessionUnlockBlocked("lock/session lifecycle still blocks unlock"),
    PersistenceReadinessBlocked("persistence readiness remains blocked"),
    ProviderSelectionDisabled("provider selection remains disabled"),
    ProductionProviderNotSelectable("production provider remains not selectable"),
    SecureSecretStorageUnavailable("secure secret storage is unavailable"),
    SecureMetadataStorageUnavailable("secure metadata storage is unavailable"),
    KdfCalibrationNotFinal("final KDF calibration approval missing"),
    ProviderKatsNotSelectable("provider KAT evidence does not make provider selectable"),
    WarningOnlyEvidenceRejected("warning-only evidence cannot enable passphrase unlock"),
    UserConsentOverrideRejected("user consent cannot override missing hard gates"),
    MainnetUnavailable("mainnet remains unavailable"),
}

enum class SkaldVaultV1VaultPassphraseWarning(val label: String) {
    ModelOnly("passphrase policy boundary is model-only"),
    NoRuntimePassphraseHandling("runtime passphrase handling is absent"),
    NoKdfExecution("KDF execution is absent"),
    NoLoggingDiagnostics("logging and diagnostics implementation is absent"),
    NoUnlockOrPersistence("unlock and persistence remain unavailable"),
}

data class SkaldVaultV1VaultPassphraseCapability(
    val passphraseInputAccepted: Boolean = false,
    val passphraseStored: Boolean = false,
    val passphraseNormalized: Boolean = false,
    val passphraseEncoded: Boolean = false,
    val passphraseHashed: Boolean = false,
    val passphraseFingerprintCreated: Boolean = false,
    val passphraseLogged: Boolean = false,
    val passphraseIncludedInBuildHistory: Boolean = false,
    val passphraseIncludedInCrashReport: Boolean = false,
    val passphraseIncludedInAnalytics: Boolean = false,
    val retryPolicyImplemented: Boolean = false,
    val throttlePolicyImplemented: Boolean = false,
    val lockoutPolicyImplemented: Boolean = false,
    val clearStrategyImplemented: Boolean = false,
    val biometricUnlockAvailable: Boolean = false,
    val androidKeystoreWrappingAvailable: Boolean = false,
    val osKeyringPassphraseStorageAvailable: Boolean = false,
    val passwordManagerPassphraseStorageAvailable: Boolean = false,
    val unlockAvailable: Boolean = false,
    val activeSessionAvailable: Boolean = false,
    val decryptedKeyMaterialPresent: Boolean = false,
    val providerSelectable: Boolean = false,
    val productionProviderSelected: Boolean = false,
    val providerCryptoAvailable: Boolean = false,
    val vaultCreationAvailable: Boolean = false,
    val vaultUnlockAvailable: Boolean = false,
    val vaultPersistenceAvailable: Boolean = false,
    val secureSecretStorageAvailable: Boolean = false,
    val secureMetadataStorageAvailable: Boolean = false,
    val mainnetAvailable: Boolean = false,
)

data class SkaldVaultV1VaultPassphraseRedactionEvidence(
    val rawPassphraseAccepted: Boolean = false,
    val rawPassphraseRendered: Boolean = false,
    val passphraseLengthRendered: Boolean = false,
    val passphrasePrefixSuffixRendered: Boolean = false,
    val passphraseHashRendered: Boolean = false,
    val passphraseFingerprintRendered: Boolean = false,
    val redactionBoundaryRequired: Boolean = true,
)

data class SkaldVaultV1VaultPassphrasePolicySummary(
    val policyId: String,
    val policyVersion: Int,
    val normalizationPolicy: SkaldVaultV1VaultPassphraseNormalizationPolicy,
    val encodingPolicy: SkaldVaultV1VaultPassphraseEncodingPolicy,
    val retryPolicy: SkaldVaultV1VaultPassphraseRetryPolicy,
    val throttlePolicy: SkaldVaultV1VaultPassphraseThrottlePolicy,
    val lockoutPolicy: SkaldVaultV1VaultPassphraseLockoutPolicy,
    val policyCategories: Set<SkaldVaultV1VaultPassphrasePolicyCategory>,
    val requiredEvidenceSources: Set<SkaldVaultV1VaultPassphraseEvidenceSource>,
    val stillDisabled: Boolean,
)

class SkaldVaultV1VaultPassphrasePolicyToken internal constructor(
    private val category: SkaldVaultV1VaultPassphrasePolicyCategory,
    private val decision: SkaldVaultV1VaultPassphrasePolicyDecision,
    private val inputClass: SkaldVaultV1VaultPassphraseInputClass,
) {
    override fun toString(): String =
        "SkaldVaultV1VaultPassphrasePolicyToken(category=$category, decision=$decision, inputClass=$inputClass, raw=<redacted>)"
}

class SkaldVaultV1VaultPassphrasePolicyRequest private constructor(
    val source: SkaldVaultV1VaultPassphrasePolicySource,
    val category: SkaldVaultV1VaultPassphrasePolicyCategory,
    val inputClass: SkaldVaultV1VaultPassphraseInputClass,
    private val evidenceSources: Set<SkaldVaultV1VaultPassphraseEvidenceSource>,
    private val redactionEvidence: SkaldVaultV1VaultRedactionEvidence?,
    private val lockSessionEvidence: SkaldVaultV1VaultLockSessionEvidence?,
    private val persistenceReadinessEvidence: SkaldVaultV1VaultPersistenceReadinessEvidence?,
    private val providerSelection: VaultCryptoProviderSelectionResult?,
    private val providerAcceptanceAssessment: ProductionProviderAcceptanceAssessment?,
    private val providerFacadeMetadata: SkaldVaultV1StillDisabledProviderFacadeMetadata?,
    private val dependencyProbeResult: VaultCryptoDependencyProbeResult?,
    private val secureStorageCapability: SecureStorageCapability?,
    private val secureMetadataCapability: SecureMetadataPersistenceCapability?,
    private val rawCandidateFailureReason: SkaldVaultV1VaultPassphraseFailureReason?,
) {
    val rawCandidateRejected: Boolean
        get() = rawCandidateFailureReason != null

    internal fun evidenceSources(): Set<SkaldVaultV1VaultPassphraseEvidenceSource> = evidenceSources

    internal fun redactionEvidenceOrNull(): SkaldVaultV1VaultRedactionEvidence? = redactionEvidence

    internal fun lockSessionEvidenceOrNull(): SkaldVaultV1VaultLockSessionEvidence? = lockSessionEvidence

    internal fun persistenceReadinessEvidenceOrNull(): SkaldVaultV1VaultPersistenceReadinessEvidence? =
        persistenceReadinessEvidence

    internal fun providerSelectionOrNull(): VaultCryptoProviderSelectionResult? = providerSelection

    internal fun providerAcceptanceAssessmentOrNull(): ProductionProviderAcceptanceAssessment? =
        providerAcceptanceAssessment

    internal fun providerFacadeMetadataOrNull(): SkaldVaultV1StillDisabledProviderFacadeMetadata? =
        providerFacadeMetadata

    internal fun dependencyProbeResultOrNull(): VaultCryptoDependencyProbeResult? = dependencyProbeResult

    internal fun secureStorageCapabilityOrNull(): SecureStorageCapability? = secureStorageCapability

    internal fun secureMetadataCapabilityOrNull(): SecureMetadataPersistenceCapability? = secureMetadataCapability

    internal fun rawCandidateFailureReasonOrNull(): SkaldVaultV1VaultPassphraseFailureReason? =
        rawCandidateFailureReason

    override fun toString(): String =
        "SkaldVaultV1VaultPassphrasePolicyRequest(" +
            "source=$source, " +
            "category=$category, " +
            "inputClass=$inputClass, " +
            "evidenceSources=$evidenceSources, " +
            "redaction=<redacted>, " +
            "lockSession=<redacted>, " +
            "persistenceReadiness=<redacted>, " +
            "providerSelection=<redacted>, " +
            "providerAcceptance=<redacted>, " +
            "providerFacade=<redacted>, " +
            "dependencyProbe=<redacted>, " +
            "secureStorage=<redacted>, " +
            "secureMetadata=<redacted>, " +
            "rawCandidate=<redacted>" +
            ")"

    companion object {
        fun summary(): SkaldVaultV1VaultPassphrasePolicyRequest =
            base(
                source = SkaldVaultV1VaultPassphrasePolicySource.PolicySummary,
                category = SkaldVaultV1VaultPassphrasePolicyCategory.PassphraseInputNotAccepted,
                inputClass = SkaldVaultV1VaultPassphraseInputClass.PolicySummaryOnly,
            )

        fun normalizationPolicyIdentifier(): SkaldVaultV1VaultPassphrasePolicyRequest =
            base(
                source = SkaldVaultV1VaultPassphrasePolicySource.NormalizationPolicyIdentifier,
                category = SkaldVaultV1VaultPassphrasePolicyCategory.NormalizationPolicyIdentifierModeled,
                inputClass = SkaldVaultV1VaultPassphraseInputClass.NormalizationPolicyIdentifierOnly,
            )

        fun encodingPolicyIdentifier(): SkaldVaultV1VaultPassphrasePolicyRequest =
            base(
                source = SkaldVaultV1VaultPassphrasePolicySource.EncodingPolicyIdentifier,
                category = SkaldVaultV1VaultPassphrasePolicyCategory.EncodingPolicyIdentifierModeled,
                inputClass = SkaldVaultV1VaultPassphraseInputClass.EncodingPolicyIdentifierOnly,
            )

        fun retryThrottlePolicyStatus(): SkaldVaultV1VaultPassphrasePolicyRequest =
            base(
                source = SkaldVaultV1VaultPassphrasePolicySource.RetryThrottlePolicyStatus,
                category = SkaldVaultV1VaultPassphrasePolicyCategory.RetryPolicyRequired,
                inputClass = SkaldVaultV1VaultPassphraseInputClass.RetryThrottlePolicyIdentifierOnly,
            )

        fun forCategory(
            category: SkaldVaultV1VaultPassphrasePolicyCategory,
        ): SkaldVaultV1VaultPassphrasePolicyRequest =
            base(
                source = SkaldVaultV1VaultPassphrasePolicySource.PolicySummary,
                category = category,
                inputClass = SkaldVaultV1VaultPassphraseInputClass.PolicySummaryOnly,
            )

        fun fromEvidence(
            redactionEvidence: SkaldVaultV1VaultRedactionEvidence? = null,
            lockSessionEvidence: SkaldVaultV1VaultLockSessionEvidence? = null,
            persistenceReadinessEvidence: SkaldVaultV1VaultPersistenceReadinessEvidence? = null,
            providerSelection: VaultCryptoProviderSelectionResult? = VaultCryptoProviderSelectionRegistry.select(),
            providerAcceptanceAssessment: ProductionProviderAcceptanceAssessment? =
                commonCurrentProductionProviderAcceptanceAssessment(),
            providerFacadeMetadata: SkaldVaultV1StillDisabledProviderFacadeMetadata? =
                SkaldVaultV1StillDisabledProviderFacade.metadata(),
            dependencyProbeResult: VaultCryptoDependencyProbeResult? =
                VaultCryptoDependencyProbeCatalog.currentSpikeResults()
                    .single { it.candidate == VaultCryptoDependencyCandidate.TinkBouncyCastleSplit },
            secureStorageCapability: SecureStorageCapability? = commonDisabledSecureStorageCapability(),
            secureMetadataCapability: SecureMetadataPersistenceCapability? = commonDisabledSecureMetadataCapability(),
        ): SkaldVaultV1VaultPassphrasePolicyRequest {
            val sources = buildSet {
                if (redactionEvidence != null) add(SkaldVaultV1VaultPassphraseEvidenceSource.RedactionLeakage)
                if (lockSessionEvidence != null) add(SkaldVaultV1VaultPassphraseEvidenceSource.LockSessionLifecycle)
                if (persistenceReadinessEvidence != null) add(SkaldVaultV1VaultPassphraseEvidenceSource.PersistenceReadiness)
                if (providerSelection != null) add(SkaldVaultV1VaultPassphraseEvidenceSource.ProviderSelection)
                if (providerAcceptanceAssessment != null) add(SkaldVaultV1VaultPassphraseEvidenceSource.ProviderAcceptance)
                if (providerFacadeMetadata != null) add(SkaldVaultV1VaultPassphraseEvidenceSource.DisabledProviderFacade)
                if (dependencyProbeResult != null) add(SkaldVaultV1VaultPassphraseEvidenceSource.DependencyProbe)
                if (secureStorageCapability != null) add(SkaldVaultV1VaultPassphraseEvidenceSource.SecureStorage)
                if (secureMetadataCapability != null) add(SkaldVaultV1VaultPassphraseEvidenceSource.SecureMetadata)
            }
            return SkaldVaultV1VaultPassphrasePolicyRequest(
                source = if (sources.isEmpty()) {
                    SkaldVaultV1VaultPassphrasePolicySource.NoEvidence
                } else {
                    SkaldVaultV1VaultPassphrasePolicySource.ComposedTypedEvidence
                },
                category = SkaldVaultV1VaultPassphrasePolicyCategory.PassphraseInputNotAccepted,
                inputClass = SkaldVaultV1VaultPassphraseInputClass.PassphraseInputNotProvided,
                evidenceSources = sources,
                redactionEvidence = redactionEvidence,
                lockSessionEvidence = lockSessionEvidence,
                persistenceReadinessEvidence = persistenceReadinessEvidence,
                providerSelection = providerSelection,
                providerAcceptanceAssessment = providerAcceptanceAssessment,
                providerFacadeMetadata = providerFacadeMetadata,
                dependencyProbeResult = dependencyProbeResult,
                secureStorageCapability = secureStorageCapability,
                secureMetadataCapability = secureMetadataCapability,
                rawCandidateFailureReason = null,
            )
        }

        fun rawPassphraseCandidate(rawCandidate: String?): SkaldVaultV1VaultPassphrasePolicyRequest =
            SkaldVaultV1VaultPassphrasePolicyRequest(
                source = SkaldVaultV1VaultPassphrasePolicySource.RawPassphraseCandidate,
                category = SkaldVaultV1VaultPassphrasePolicyCategory.PassphraseInputNotAccepted,
                inputClass = SkaldVaultV1VaultPassphraseInputClass.RawInputRejected,
                evidenceSources = setOf(SkaldVaultV1VaultPassphraseEvidenceSource.StandalonePolicy),
                redactionEvidence = null,
                lockSessionEvidence = null,
                persistenceReadinessEvidence = null,
                providerSelection = null,
                providerAcceptanceAssessment = null,
                providerFacadeMetadata = null,
                dependencyProbeResult = null,
                secureStorageCapability = null,
                secureMetadataCapability = null,
                rawCandidateFailureReason = classifyRawCandidateFailure(rawCandidate),
            )

        private fun base(
            source: SkaldVaultV1VaultPassphrasePolicySource,
            category: SkaldVaultV1VaultPassphrasePolicyCategory,
            inputClass: SkaldVaultV1VaultPassphraseInputClass,
        ): SkaldVaultV1VaultPassphrasePolicyRequest =
            SkaldVaultV1VaultPassphrasePolicyRequest(
                source = source,
                category = category,
                inputClass = inputClass,
                evidenceSources = setOf(SkaldVaultV1VaultPassphraseEvidenceSource.StandalonePolicy),
                redactionEvidence = null,
                lockSessionEvidence = null,
                persistenceReadinessEvidence = null,
                providerSelection = null,
                providerAcceptanceAssessment = null,
                providerFacadeMetadata = null,
                dependencyProbeResult = null,
                secureStorageCapability = null,
                secureMetadataCapability = null,
                rawCandidateFailureReason = null,
            )

        private fun classifyRawCandidateFailure(rawCandidate: String?): SkaldVaultV1VaultPassphraseFailureReason {
            val candidate = rawCandidate?.trim() ?: return SkaldVaultV1VaultPassphraseFailureReason.EmptyEvidenceRejected
            if (candidate.isEmpty()) return SkaldVaultV1VaultPassphraseFailureReason.EmptyEvidenceRejected
            val lower = candidate.lowercase()
            if (lower.contains("://")) return SkaldVaultV1VaultPassphraseFailureReason.LinkLikeInputRejected
            if (candidate.startsWith("/") || candidate.matches(Regex("""^[A-Za-z]:\\.*""")) ||
                candidate.startsWith("""\\""")
            ) {
                return SkaldVaultV1VaultPassphraseFailureReason.RawAbsoluteLocationInputRejected
            }
            if (lower.contains("../") || lower.contains("""..\""")) {
                return SkaldVaultV1VaultPassphraseFailureReason.TraversalRejected
            }
            if (candidate.contains("/") || candidate.contains("\\")) {
                return SkaldVaultV1VaultPassphraseFailureReason.RawRelativeLocationInputRejected
            }
            if (lower.contains("file(") || lower.contains("path(") || lower.contains("uri(") ||
                lower.contains("url(") || lower.contains("stream") || lower.contains("database")
            ) {
                return SkaldVaultV1VaultPassphraseFailureReason.PlatformObjectLikeInputRejected
            }
            if (lower.contains("bytearray") || lower.contains("bytes")) {
                return SkaldVaultV1VaultPassphraseFailureReason.ByteArrayLikeInputRejected
            }
            if (lower.contains("passphrase") || lower.contains("password")) {
                return SkaldVaultV1VaultPassphraseFailureReason.RawPassphraseInputRejected
            }
            if (lower.contains("pin")) return SkaldVaultV1VaultPassphraseFailureReason.PinMaterialRejected
            if (lower.contains("mnemonic")) return SkaldVaultV1VaultPassphraseFailureReason.MnemonicMaterialRejected
            if (lower.contains("seed")) return SkaldVaultV1VaultPassphraseFailureReason.SeedMaterialRejected
            if (lower.contains("hash") || lower.contains("fingerprint")) {
                return SkaldVaultV1VaultPassphraseFailureReason.HashFingerprintEvidenceRejected
            }
            if (lower.contains("biometric")) {
                return SkaldVaultV1VaultPassphraseFailureReason.BiometricEvidenceRejected
            }
            if (lower.contains("key") || lower.contains("secret") || lower.contains("credential")) {
                return SkaldVaultV1VaultPassphraseFailureReason.KeyMaterialRejected
            }
            if (candidate.matches(Regex("""(?i)^[0-9a-f]{64}$"""))) {
                return SkaldVaultV1VaultPassphraseFailureReason.WalletMaterialRejected
            }
            if (lower.matches(Regex("""^(bc1|tb1|bcrt1)[a-z0-9]{20,}$"""))) {
                return SkaldVaultV1VaultPassphraseFailureReason.BitcoinAddressLikeEvidenceRejected
            }
            if (lower.matches(Regex("""^(nsec1|xprv|tprv)[a-z0-9]+$""")) ||
                candidate.matches(Regex("""^[KL5][1-9A-HJ-NP-Za-km-z]{50,51}$"""))
            ) {
                return SkaldVaultV1VaultPassphraseFailureReason.WalletMaterialRejected
            }
            if (!candidate.all { ch ->
                    ch in 'a'..'z' ||
                        ch in 'A'..'Z' ||
                        ch in '0'..'9' ||
                        ch == '-' ||
                        ch == '_' ||
                        ch == '.'
                }
            ) {
                return SkaldVaultV1VaultPassphraseFailureReason.UnsupportedCharactersRejected
            }
            return SkaldVaultV1VaultPassphraseFailureReason.UnsupportedEvidenceRejected
        }
    }
}

data class SkaldVaultV1VaultPassphrasePolicyEvidence(
    val policyId: String,
    val policyVersion: Int,
    val status: SkaldVaultV1VaultPassphrasePolicyStatus,
    val decision: SkaldVaultV1VaultPassphrasePolicyDecision,
    val source: SkaldVaultV1VaultPassphrasePolicySource,
    val inputClass: SkaldVaultV1VaultPassphraseInputClass,
    val category: SkaldVaultV1VaultPassphrasePolicyCategory,
    val capability: SkaldVaultV1VaultPassphraseCapability,
    val redactedPolicyMarker: SkaldVaultV1VaultPassphrasePolicyToken,
    val redactionEvidence: SkaldVaultV1VaultPassphraseRedactionEvidence,
    val policySummary: SkaldVaultV1VaultPassphrasePolicySummary,
    val blockers: Set<SkaldVaultV1VaultPassphraseBlocker>,
    val warnings: Set<SkaldVaultV1VaultPassphraseWarning>,
    val evidenceSources: Set<SkaldVaultV1VaultPassphraseEvidenceSource>,
    val redactionLeakageEvidenceConsumed: Boolean,
    val lockSessionEvidenceConsumed: Boolean,
    val persistenceReadinessEvidenceConsumed: Boolean,
    val providerSelectionEvidenceConsumed: Boolean,
    val providerAcceptanceEvidenceConsumed: Boolean,
    val disabledProviderFacadeEvidenceConsumed: Boolean,
    val dependencyProbeEvidenceConsumed: Boolean,
    val secureStorageEvidenceConsumed: Boolean,
    val secureMetadataEvidenceConsumed: Boolean,
)

sealed class SkaldVaultV1VaultPassphrasePolicyResult<out T> {
    abstract val passphraseInputAccepted: Boolean
    abstract val unlockAvailable: Boolean

    data class Blocked<out T>(val value: T) : SkaldVaultV1VaultPassphrasePolicyResult<T>() {
        override val passphraseInputAccepted: Boolean = false
        override val unlockAvailable: Boolean = false

        override fun toString(): String = "Blocked(value=<redacted-passphrase-policy-evidence>)"
    }

    data class Rejected(
        val reason: SkaldVaultV1VaultPassphraseFailureReason,
        val status: SkaldVaultV1VaultPassphrasePolicyStatus,
        val source: SkaldVaultV1VaultPassphrasePolicySource,
        val safeMessage: String = reason.label,
    ) : SkaldVaultV1VaultPassphrasePolicyResult<Nothing>() {
        override val passphraseInputAccepted: Boolean = false
        override val unlockAvailable: Boolean = false

        override fun toString(): String =
            "Rejected(" +
                "reason=$reason, " +
                "status=$status, " +
                "source=$source, " +
                "safeMessage=$safeMessage, " +
                "rawPassphrase=<redacted>" +
                ")"
    }
}

object SkaldVaultV1PassphrasePolicyGate : SkaldVaultV1PassphrasePolicyBoundary {
    const val POLICY_ID = "skald-vault-v1-passphrase-policy-boundary-v1"
    const val POLICY_VERSION = 1

    override fun evaluate(
        request: SkaldVaultV1VaultPassphrasePolicyRequest,
    ): SkaldVaultV1VaultPassphrasePolicyResult<SkaldVaultV1VaultPassphrasePolicyEvidence> {
        request.rawCandidateFailureReasonOrNull()?.let { reason ->
            return SkaldVaultV1VaultPassphrasePolicyResult.Rejected(
                reason = reason,
                status = SkaldVaultV1VaultPassphrasePolicyStatus.RawInputRejected,
                source = request.source,
            )
        }
        return blockedEvidence(request)
    }

    fun currentPolicySummary(): SkaldVaultV1VaultPassphrasePolicySummary =
        SkaldVaultV1VaultPassphrasePolicySummary(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            normalizationPolicy = SkaldVaultV1VaultPassphraseNormalizationPolicy.UnicodeNfcRequired,
            encodingPolicy = SkaldVaultV1VaultPassphraseEncodingPolicy.Utf8Required,
            retryPolicy = SkaldVaultV1VaultPassphraseRetryPolicy.RequiredFutureOnly,
            throttlePolicy = SkaldVaultV1VaultPassphraseThrottlePolicy.RequiredFutureOnly,
            lockoutPolicy = SkaldVaultV1VaultPassphraseLockoutPolicy.RequiresReviewOrExplicitRejection,
            policyCategories = SkaldVaultV1VaultPassphrasePolicyCategory.entries.toSet(),
            requiredEvidenceSources = SkaldVaultV1VaultPassphraseEvidenceSource.entries.toSet(),
            stillDisabled = true,
        )

    private fun blockedEvidence(
        request: SkaldVaultV1VaultPassphrasePolicyRequest,
    ): SkaldVaultV1VaultPassphrasePolicyResult.Blocked<SkaldVaultV1VaultPassphrasePolicyEvidence> {
        val status = statusFor(request)
        val decision = decisionFor(request.category)
        return SkaldVaultV1VaultPassphrasePolicyResult.Blocked(
            SkaldVaultV1VaultPassphrasePolicyEvidence(
                policyId = POLICY_ID,
                policyVersion = POLICY_VERSION,
                status = status,
                decision = decision,
                source = request.source,
                inputClass = request.inputClass,
                category = request.category,
                capability = SkaldVaultV1VaultPassphraseCapability(),
                redactedPolicyMarker = SkaldVaultV1VaultPassphrasePolicyToken(
                    category = request.category,
                    decision = decision,
                    inputClass = request.inputClass,
                ),
                redactionEvidence = SkaldVaultV1VaultPassphraseRedactionEvidence(),
                policySummary = currentPolicySummary(),
                blockers = blockersFor(request),
                warnings = SkaldVaultV1VaultPassphraseWarning.entries.toSet(),
                evidenceSources = request.evidenceSources(),
                redactionLeakageEvidenceConsumed = request.redactionEvidenceOrNull() != null,
                lockSessionEvidenceConsumed = request.lockSessionEvidenceOrNull() != null,
                persistenceReadinessEvidenceConsumed = request.persistenceReadinessEvidenceOrNull() != null,
                providerSelectionEvidenceConsumed = request.providerSelectionOrNull() != null,
                providerAcceptanceEvidenceConsumed = request.providerAcceptanceAssessmentOrNull() != null,
                disabledProviderFacadeEvidenceConsumed = request.providerFacadeMetadataOrNull() != null,
                dependencyProbeEvidenceConsumed = request.dependencyProbeResultOrNull() != null,
                secureStorageEvidenceConsumed = request.secureStorageCapabilityOrNull() != null,
                secureMetadataEvidenceConsumed = request.secureMetadataCapabilityOrNull() != null,
            ),
        )
    }

    private fun statusFor(
        request: SkaldVaultV1VaultPassphrasePolicyRequest,
    ): SkaldVaultV1VaultPassphrasePolicyStatus =
        when (request.category) {
            SkaldVaultV1VaultPassphrasePolicyCategory.PassphraseStorageForbidden,
            SkaldVaultV1VaultPassphrasePolicyCategory.HashingFingerprintingForbidden,
            SkaldVaultV1VaultPassphrasePolicyCategory.LoggingForbidden,
            SkaldVaultV1VaultPassphrasePolicyCategory.BuildHistoryInclusionForbidden,
            SkaldVaultV1VaultPassphrasePolicyCategory.CrashReportInclusionForbidden,
            SkaldVaultV1VaultPassphrasePolicyCategory.AnalyticsInclusionForbidden,
            SkaldVaultV1VaultPassphrasePolicyCategory.OsKeyringPasswordManagerPassphraseStorageRejected,
            -> SkaldVaultV1VaultPassphrasePolicyStatus.ForbiddenByPolicy
            SkaldVaultV1VaultPassphrasePolicyCategory.NormalizationPolicyIdentifierModeled,
            SkaldVaultV1VaultPassphrasePolicyCategory.EncodingPolicyIdentifierModeled,
            -> SkaldVaultV1VaultPassphrasePolicyStatus.PolicyIdentifierModeled
            SkaldVaultV1VaultPassphrasePolicyCategory.PassphraseInputNotAccepted,
            SkaldVaultV1VaultPassphrasePolicyCategory.UnlockUnavailable,
            -> SkaldVaultV1VaultPassphrasePolicyStatus.InputNotAccepted
            else -> SkaldVaultV1VaultPassphrasePolicyStatus.FutureImplementationRequired
        }

    private fun decisionFor(
        category: SkaldVaultV1VaultPassphrasePolicyCategory,
    ): SkaldVaultV1VaultPassphrasePolicyDecision =
        when (category) {
            SkaldVaultV1VaultPassphrasePolicyCategory.PassphraseStorageForbidden,
            SkaldVaultV1VaultPassphrasePolicyCategory.HashingFingerprintingForbidden,
            SkaldVaultV1VaultPassphrasePolicyCategory.LoggingForbidden,
            SkaldVaultV1VaultPassphrasePolicyCategory.BuildHistoryInclusionForbidden,
            SkaldVaultV1VaultPassphrasePolicyCategory.CrashReportInclusionForbidden,
            SkaldVaultV1VaultPassphrasePolicyCategory.AnalyticsInclusionForbidden,
            SkaldVaultV1VaultPassphrasePolicyCategory.OsKeyringPasswordManagerPassphraseStorageRejected,
            -> SkaldVaultV1VaultPassphrasePolicyDecision.Forbidden
            SkaldVaultV1VaultPassphrasePolicyCategory.NormalizationPolicyIdentifierModeled,
            SkaldVaultV1VaultPassphrasePolicyCategory.EncodingPolicyIdentifierModeled,
            -> SkaldVaultV1VaultPassphrasePolicyDecision.PolicyIdentifierModeled
            SkaldVaultV1VaultPassphrasePolicyCategory.PassphraseInputNotAccepted,
            SkaldVaultV1VaultPassphrasePolicyCategory.UnlockUnavailable,
            -> SkaldVaultV1VaultPassphrasePolicyDecision.BlockedFailClosed
            else -> SkaldVaultV1VaultPassphrasePolicyDecision.FutureImplementationRequired
        }

    private fun blockersFor(
        request: SkaldVaultV1VaultPassphrasePolicyRequest,
    ): Set<SkaldVaultV1VaultPassphraseBlocker> = buildSet {
        addAll(SkaldVaultV1VaultPassphraseBlocker.entries)
        if (request.redactionEvidenceOrNull() != null) {
            add(SkaldVaultV1VaultPassphraseBlocker.RedactionPolicyRequired)
        }
        if (request.lockSessionEvidenceOrNull() != null) {
            add(SkaldVaultV1VaultPassphraseBlocker.LockSessionUnlockBlocked)
        }
        if (request.persistenceReadinessEvidenceOrNull() != null) {
            add(SkaldVaultV1VaultPassphraseBlocker.PersistenceReadinessBlocked)
        }
        if (request.providerSelectionOrNull()?.decision?.productionProviderSelectable == false) {
            add(SkaldVaultV1VaultPassphraseBlocker.ProviderSelectionDisabled)
        }
        if (request.secureStorageCapabilityOrNull()?.isFailClosed == true) {
            add(SkaldVaultV1VaultPassphraseBlocker.SecureSecretStorageUnavailable)
        }
        if (request.secureMetadataCapabilityOrNull()?.isFailClosed == true) {
            add(SkaldVaultV1VaultPassphraseBlocker.SecureMetadataStorageUnavailable)
        }
    }
}
