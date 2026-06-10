package com.libertasprimordium.skald.security

interface SkaldVaultV1RedactionLeakageBoundary {
    fun evaluate(
        request: SkaldVaultV1VaultRedactionRequest,
    ): SkaldVaultV1VaultRedactionResult<SkaldVaultV1VaultRedactionEvidence>
}

enum class SkaldVaultV1VaultRedactionValueKind(val label: String) {
    Unknown("unknown value kind"),
    Passphrase("passphrase"),
    Pin("PIN"),
    MnemonicPhrase("mnemonic"),
    Seed("seed"),
    PrivateKey("private key"),
    XprvTprv("xprv or tprv"),
    Wif("WIF"),
    NostrNsec("Nostr nsec"),
    NostrPrivateKeyDerivedWalletMaterial("Nostr private-key-derived wallet material"),
    DescriptorPrivateMaterial("descriptor private material"),
    ProviderRootKey("provider root key"),
    VaultRootKey("vault root key"),
    MetadataEncryptionKey("metadata encryption key"),
    RecordEncryptionKey("record encryption key"),
    BackupExportKey("backup/export key"),
    KeyWrappingKey("key-wrapping key"),
    RawKdfOutput("raw KDF output"),
    RawAeadKey("raw AEAD key"),
    RandomEntropySample("random entropy sample"),
    DecryptedVaultRecord("decrypted vault record"),
    EncryptedVaultRecordBytes("encrypted vault record bytes"),
    RecordIdentifier("record identifier"),
    ManifestIdentifier("manifest identifier"),
    StorageIndexIdentifier("storage index identifier"),
    PlannedArtifactLocationToken("planned artifact-location token"),
    RootToken("root token"),
    RawPlatformRootPath("raw platform root or path string"),
    BackendCredential("backend credential"),
    RpcCookie("RPC cookie"),
    LightningMacaroonRuneNwcSecret("Lightning macaroon, rune, or NWC secret"),
    PhoenixdToken("Phoenixd token"),
    CashuProofMaterial("Cashu proof material"),
    WalletDatabaseBytes("wallet database bytes"),
    BdkPersistenceHandle("BDK persistence handle"),
    TransactionHex("transaction hex"),
    Psbt("PSBT"),
    TxidOutpoint("txid or outpoint"),
    BitcoinAddress("Bitcoin address"),
    NostrEventSignature("Nostr event or signature"),
    PaymentTransactionNote("payment note or transaction note"),
    WalletLabel("wallet label"),
    UtxoLabel("UTXO label"),
    BackendEndpoint("backend endpoint"),
    OnionEndpoint("onion endpoint"),
    TorRoutingMetadata("Tor routing metadata"),
    AndroidDeviceIdentifier("Android device identifier"),
    FilesystemErrorText("filesystem error text"),
    ExceptionStackTrace("exception stack trace"),
    PublicNonWalletCryptographicKatVector("public non-wallet cryptographic KAT vector"),
    PublicPolicyIdentifier("public policy identifier"),
    SentinelPlaceholder("sentinel placeholder"),
    EnumStatusValue("enum or status value"),
    BooleanCapabilityFlag("boolean capability flag"),
    AggregateCountStatistic("aggregate count or statistic"),
}

enum class SkaldVaultV1VaultRedactionOutputTarget(val label: String) {
    DefaultToString("default toString"),
    UiStatusText("UI status text"),
    OperationResultSummary("operation result summary"),
    ErrorFailureSummary("error or failure summary"),
    BuildHistory("build history"),
    SourceGuardDiagnostics("source guard diagnostics"),
    TestAssertionMessage("test assertion message"),
    FutureStructuredAppLog("future structured app log"),
    FutureCrashReport("future crash report"),
    FutureSupportExport("future support export"),
    FutureBackupExportManifest("future backup/export manifest"),
    FutureDebugOnlyDiagnosticPanel("future debug-only diagnostic panel"),
}

enum class SkaldVaultV1VaultRedactionScope(val label: String) {
    Production("production"),
    UiStatus("UI status"),
    OperationResult("operation result"),
    FailureSummary("failure summary"),
    BuildHistory("build history"),
    SourceGuard("source guard"),
    Test("test"),
    Docs("docs"),
    KatVector("KAT vector"),
    WalletUtxoSync("wallet, UTXO, or sync"),
    FutureStructuredAppLog("future structured app log"),
    FutureCrashReport("future crash report"),
    FutureSupportExport("future support export"),
    FutureBackupExport("future backup/export"),
    FutureDebugOnly("future debug-only"),
}

enum class SkaldVaultV1VaultLeakageSeverity(val label: String) {
    ForbiddenSecret("forbidden secret"),
    SensitiveMetadata("sensitive metadata"),
    OperationalMetadata("operational metadata"),
    PublicEvidence("public evidence"),
    Harmless("harmless enum or capability"),
    Unsupported("unsupported fail-closed"),
}

enum class SkaldVaultV1VaultForbiddenValueClass(val label: String) {
    UserUnlockSecret("user unlock secret"),
    WalletKeyMaterial("wallet key material"),
    ProviderKeyMaterial("provider key material"),
    VaultKeyMaterial("vault key material"),
    RawCryptoOutput("raw cryptographic output"),
    DecryptedPayload("decrypted payload"),
    Credential("credential"),
    WalletDatabase("wallet database"),
    PersistenceHandle("persistence handle"),
    StackTrace("stack trace"),
}

enum class SkaldVaultV1VaultAllowedEvidenceClass(val label: String) {
    PublicPolicyIdentifier("public policy identifier"),
    PublicNonWalletCryptographicKatVector("public non-wallet cryptographic KAT vector"),
    SentinelPlaceholder("sentinel placeholder"),
    EnumStatusValue("enum or status value"),
    BooleanCapabilityFlag("boolean capability flag"),
    AggregateCountStatistic("aggregate count or statistic"),
}

enum class SkaldVaultV1VaultRedactionDecision(
    val rawValueAllowed: Boolean,
    val secretAllowed: Boolean,
    val productionAllowed: Boolean,
) {
    Forbidden(rawValueAllowed = false, secretAllowed = false, productionAllowed = false),
    RedactCompletely(rawValueAllowed = false, secretAllowed = false, productionAllowed = false),
    ReplaceWithStableRedactedToken(rawValueAllowed = false, secretAllowed = false, productionAllowed = false),
    SummarizeClassOnly(rawValueAllowed = false, secretAllowed = false, productionAllowed = false),
    SummarizeCountOnly(rawValueAllowed = false, secretAllowed = false, productionAllowed = true),
    AllowPublicPolicyIdentifier(rawValueAllowed = true, secretAllowed = false, productionAllowed = true),
    AllowPublicNonWalletVectorInTestScopeOnly(
        rawValueAllowed = true,
        secretAllowed = false,
        productionAllowed = false,
    ),
    AllowEnumOrCapability(rawValueAllowed = true, secretAllowed = false, productionAllowed = true),
    RejectDiagnostic(rawValueAllowed = false, secretAllowed = false, productionAllowed = false),
    RequiresManualReview(rawValueAllowed = false, secretAllowed = false, productionAllowed = false),
    UnsupportedFailClosed(rawValueAllowed = false, secretAllowed = false, productionAllowed = false),
}

enum class SkaldVaultV1VaultRedactionStatus(val label: String) {
    Classified("redaction request classified"),
    ForbiddenSensitiveValue("sensitive value class is forbidden"),
    RedactedSensitiveValue("sensitive value class is redacted"),
    PublicEvidenceAllowed("public evidence class is allowed"),
    PublicVectorAllowedInNarrowScope("public non-wallet vector is allowed in narrow scope"),
    DiagnosticRejected("diagnostic is rejected"),
    ManualReviewRequired("manual review required"),
    UnsupportedFailClosed("unsupported value kind fails closed"),
    RawCandidateRejected("raw redaction candidate is rejected"),
}

enum class SkaldVaultV1VaultRedactionFailureReason(val label: String) {
    NoCandidateEvidenceSupplied("no redaction candidate evidence supplied"),
    RawSecretInputRejected("raw secret input is rejected"),
    PassphraseInputRejected("passphrase input is rejected"),
    PinInputRejected("PIN input is rejected"),
    KeyMaterialInputRejected("key material input is rejected"),
    CredentialInputRejected("credential input is rejected"),
    RawByteArrayInputRejected("raw byte-array input is rejected"),
    RawAbsoluteLocationInputRejected("raw absolute location input is rejected"),
    RawRelativeLocationInputRejected("raw relative location input is rejected"),
    LinkLikeInputRejected("link-like input is rejected"),
    PlatformObjectLikeInputRejected("platform object-like input is rejected"),
    StackTraceInputRejected("stack trace input is rejected"),
    SecretMaterialRejected("secret-looking material is rejected"),
    WalletMaterialRejected("wallet or key-looking material is rejected"),
    BitcoinAddressLikeEvidenceRejected("Bitcoin address-like evidence is rejected"),
    TransactionLikeEvidenceRejected("transaction-id-like evidence is rejected"),
    TraversalRejected("traversal-bearing evidence is rejected"),
    EmptyEvidenceRejected("empty redaction evidence is rejected"),
    UnsupportedEvidenceRejected("unsupported redaction evidence is rejected"),
    UnknownValueKindRejected("unknown value kind fails closed"),
    PublicVectorScopeRejected("public non-wallet vector is not allowed in this scope"),
}

enum class SkaldVaultV1VaultRedactionBlocker(val label: String) {
    RawSecretsNotAccepted("raw secrets are not accepted"),
    SensitiveValueForbidden("sensitive value class is forbidden"),
    RawValueRedacted("raw value must be redacted"),
    UnknownValueKindRejected("unknown value kind is rejected"),
    PublicVectorScopeRejected("public vector scope is rejected"),
    PublicVectorRejectedInWalletUtxoSyncScope("public vector exception is rejected in wallet, UTXO, or sync scope"),
    RuntimeLoggingDisabled("runtime logging is disabled"),
    CrashReportingDisabled("crash reporting is disabled"),
    AnalyticsDisabled("analytics is disabled"),
    SupportExportDisabled("support export is disabled"),
    SecretHashingDisabled("secret hashing is disabled"),
    SecretFingerprintingDisabled("secret fingerprinting is disabled"),
    RawSecretDisplayDisabled("raw secret display is disabled"),
    ProviderSelectionBlocked("provider selection remains blocked"),
    VaultUnlockBlocked("vault unlock remains blocked"),
    VaultPersistenceBlocked("vault persistence remains blocked"),
    MainnetDisabled("mainnet remains disabled"),
}

enum class SkaldVaultV1VaultRedactionWarning(val label: String) {
    EvidenceOnly("redaction policy is evidence only"),
    NoRuntimeLogging("runtime logging is not implemented"),
    NoCrashReporting("crash reporting is not implemented"),
    NoAnalytics("analytics is not implemented"),
    NoSupportExport("support export is not implemented"),
    NoSecretHashing("secret hashing is not implemented"),
    NoSecretFingerprinting("secret fingerprinting is not implemented"),
    NoPartialSecretDisplay("partial secret display is not allowed"),
    PublicVectorsScopedToDocsTestsAndSourceGuards(
        "public non-wallet vectors are scoped to docs, tests, and source guards",
    ),
    WalletUtxoSyncMaterialNeverUsesPublicVectorException(
        "wallet, UTXO, and sync material never uses the public-vector exception",
    ),
}

data class SkaldVaultV1VaultRedactionCapability(
    val runtimeLoggingEnabled: Boolean = false,
    val crashReportingEnabled: Boolean = false,
    val analyticsEnabled: Boolean = false,
    val supportExportEnabled: Boolean = false,
    val secretFingerprintingEnabled: Boolean = false,
    val secretHashingEnabled: Boolean = false,
    val rawSecretDisplayEnabled: Boolean = false,
    val defaultToStringSafeForSensitiveValues: Boolean = true,
    val buildHistorySecretAllowed: Boolean = false,
    val operationResultSecretAllowed: Boolean = false,
    val uiSecretAllowed: Boolean = false,
    val providerSelectable: Boolean = false,
    val vaultUnlockAvailable: Boolean = false,
    val vaultPersistenceAvailable: Boolean = false,
    val mainnetAvailable: Boolean = false,
)

enum class SkaldVaultV1VaultDiagnosticSafetyEvidence(val label: String) {
    DefaultStringRenderingRedacted("default string rendering redacted"),
    OperationResultSummaryRedacted("operation result summary redacted"),
    ErrorFailureSummaryRedacted("error or failure summary redacted"),
    BuildHistorySecretsForbidden("build history secrets forbidden"),
    SourceGuardDiagnosticsScoped("source guard diagnostics scoped"),
    FutureAppLogDisabled("future app log disabled"),
    FutureCrashReportDisabled("future crash report disabled"),
    FutureSupportExportDisabled("future support export disabled"),
}

enum class SkaldVaultV1VaultSourceGuardMaterialClass(val label: String) {
    NoLoggingImplementation("no logging implementation"),
    NoCrashReportingImplementation("no crash reporting implementation"),
    NoAnalyticsImplementation("no analytics implementation"),
    NoSecretHashingOrFingerprinting("no secret hashing or fingerprinting"),
    NoFilesystemApis("no filesystem APIs"),
    NoSettingsPersistence("no Settings persistence"),
    NoProviderCryptoExecution("no provider crypto execution"),
    NoStorageSuccessPath("no storage success path"),
    PublicNonWalletVectorScoped("public non-wallet vector scoped"),
    WalletMaterialFixtureRejected("wallet material fixture rejected"),
}

enum class SkaldVaultV1VaultRedactionEvidenceSource(val label: String) {
    StandalonePolicy("standalone redaction policy"),
    LockSessionLifecycle("lock/session lifecycle evidence"),
    PersistenceReadiness("persistence readiness evidence"),
    DisabledStorageService("disabled storage service evidence"),
    StorageSafetyPreflight("storage safety preflight evidence"),
    PlannedArtifactLocation("planned artifact-location evidence"),
    PlatformRoot("platform root evidence"),
    ProviderSelection("provider selection evidence"),
    ProviderAcceptance("provider acceptance evidence"),
    DisabledProviderFacade("disabled provider facade evidence"),
    SecureStorage("secure storage evidence"),
    SecureMetadata("secure metadata evidence"),
    CryptoDependency("crypto dependency evidence"),
    SourceGuard("source guard evidence"),
}

class SkaldVaultV1VaultRedactionToken internal constructor(
    val valueKind: SkaldVaultV1VaultRedactionValueKind,
    val outputTarget: SkaldVaultV1VaultRedactionOutputTarget,
    val decision: SkaldVaultV1VaultRedactionDecision,
) {
    val rawValueExposed: Boolean = false
    val containsSecretMaterial: Boolean = false
    val containsPassphraseMaterial: Boolean = false
    val containsKeyMaterial: Boolean = false
    val containsProviderKeyMaterial: Boolean = false
    val containsRootText: Boolean = false
    val containsPlannedLocationText: Boolean = false
    val containsRecordIdentifier: Boolean = false
    val containsPayload: Boolean = false
    val containsWalletLabel: Boolean = false
    val containsTransactionNote: Boolean = false
    val containsBackendCredential: Boolean = false
    val containsEndpointCredential: Boolean = false
    val containsStackTrace: Boolean = false
    val containsRawBytes: Boolean = false
    val stableFingerprintOfSecret: Boolean = false
    val hashOfSecret: Boolean = false
    val partialSecretDisplay: Boolean = false

    override fun toString(): String =
        "SkaldVaultV1VaultRedactionToken(valueKind=$valueKind, outputTarget=$outputTarget, decision=$decision, redacted=true)"
}

data class SkaldVaultV1VaultRedactionPolicySummary(
    val policyId: String,
    val policyVersion: Int,
    val valueKindCount: Int,
    val outputTargetCount: Int,
    val scopeCount: Int,
    val stillDisabled: Boolean,
) {
    override fun toString(): String =
        "SkaldVaultV1VaultRedactionPolicySummary(policyId=$policyId, policyVersion=$policyVersion, stillDisabled=$stillDisabled)"
}

class SkaldVaultV1VaultRedactionRequest private constructor(
    val valueKind: SkaldVaultV1VaultRedactionValueKind,
    val outputTarget: SkaldVaultV1VaultRedactionOutputTarget,
    val scope: SkaldVaultV1VaultRedactionScope,
    private val evidenceSource: SkaldVaultV1VaultRedactionEvidenceSource,
    private val publicEvidenceId: String?,
    private val aggregateCount: Int?,
    private val rawCandidateFailureReason: SkaldVaultV1VaultRedactionFailureReason?,
) {
    val rawCandidateRejected: Boolean
        get() = rawCandidateFailureReason != null

    internal fun rawCandidateFailureReasonOrNull(): SkaldVaultV1VaultRedactionFailureReason? =
        rawCandidateFailureReason

    internal fun publicEvidenceIdOrNull(): String? = publicEvidenceId

    internal fun aggregateCountOrNull(): Int? = aggregateCount

    internal fun evidenceSource(): SkaldVaultV1VaultRedactionEvidenceSource = evidenceSource

    override fun toString(): String =
        "SkaldVaultV1VaultRedactionRequest(valueKind=$valueKind, outputTarget=$outputTarget, scope=$scope, source=$evidenceSource, raw=redacted)"

    companion object {
        fun classify(
            valueKind: SkaldVaultV1VaultRedactionValueKind,
            outputTarget: SkaldVaultV1VaultRedactionOutputTarget =
                SkaldVaultV1VaultRedactionOutputTarget.DefaultToString,
            scope: SkaldVaultV1VaultRedactionScope = SkaldVaultV1VaultRedactionScope.Production,
            evidenceSource: SkaldVaultV1VaultRedactionEvidenceSource =
                SkaldVaultV1VaultRedactionEvidenceSource.StandalonePolicy,
        ): SkaldVaultV1VaultRedactionRequest =
            SkaldVaultV1VaultRedactionRequest(
                valueKind = valueKind,
                outputTarget = outputTarget,
                scope = scope,
                evidenceSource = evidenceSource,
                publicEvidenceId = null,
                aggregateCount = null,
                rawCandidateFailureReason = null,
            )

        fun publicPolicyIdentifier(
            policyId: String,
            outputTarget: SkaldVaultV1VaultRedactionOutputTarget =
                SkaldVaultV1VaultRedactionOutputTarget.OperationResultSummary,
            scope: SkaldVaultV1VaultRedactionScope = SkaldVaultV1VaultRedactionScope.Production,
        ): SkaldVaultV1VaultRedactionRequest =
            SkaldVaultV1VaultRedactionRequest(
                valueKind = SkaldVaultV1VaultRedactionValueKind.PublicPolicyIdentifier,
                outputTarget = outputTarget,
                scope = scope,
                evidenceSource = SkaldVaultV1VaultRedactionEvidenceSource.StandalonePolicy,
                publicEvidenceId = policyId.takeIf { isSafePublicEvidenceId(it) },
                aggregateCount = null,
                rawCandidateFailureReason = if (isSafePublicEvidenceId(policyId)) {
                    null
                } else {
                    classifyRawCandidateFailure(policyId)
                },
            )

        fun publicNonWalletVector(
            vectorId: String,
            outputTarget: SkaldVaultV1VaultRedactionOutputTarget =
                SkaldVaultV1VaultRedactionOutputTarget.TestAssertionMessage,
            scope: SkaldVaultV1VaultRedactionScope,
        ): SkaldVaultV1VaultRedactionRequest =
            SkaldVaultV1VaultRedactionRequest(
                valueKind = SkaldVaultV1VaultRedactionValueKind.PublicNonWalletCryptographicKatVector,
                outputTarget = outputTarget,
                scope = scope,
                evidenceSource = SkaldVaultV1VaultRedactionEvidenceSource.StandalonePolicy,
                publicEvidenceId = vectorId.takeIf { isSafePublicEvidenceId(it) },
                aggregateCount = null,
                rawCandidateFailureReason = if (isSafePublicEvidenceId(vectorId)) {
                    null
                } else {
                    classifyRawCandidateFailure(vectorId)
                },
            )

        fun aggregateCount(
            count: Int,
            outputTarget: SkaldVaultV1VaultRedactionOutputTarget =
                SkaldVaultV1VaultRedactionOutputTarget.OperationResultSummary,
            scope: SkaldVaultV1VaultRedactionScope = SkaldVaultV1VaultRedactionScope.Production,
        ): SkaldVaultV1VaultRedactionRequest =
            SkaldVaultV1VaultRedactionRequest(
                valueKind = SkaldVaultV1VaultRedactionValueKind.AggregateCountStatistic,
                outputTarget = outputTarget,
                scope = scope,
                evidenceSource = SkaldVaultV1VaultRedactionEvidenceSource.StandalonePolicy,
                publicEvidenceId = null,
                aggregateCount = count.coerceAtLeast(0),
                rawCandidateFailureReason = null,
            )

        fun fromEvidenceSource(
            evidenceSource: SkaldVaultV1VaultRedactionEvidenceSource,
            valueKind: SkaldVaultV1VaultRedactionValueKind =
                SkaldVaultV1VaultRedactionValueKind.EnumStatusValue,
            outputTarget: SkaldVaultV1VaultRedactionOutputTarget =
                SkaldVaultV1VaultRedactionOutputTarget.OperationResultSummary,
            scope: SkaldVaultV1VaultRedactionScope = SkaldVaultV1VaultRedactionScope.Production,
        ): SkaldVaultV1VaultRedactionRequest =
            classify(
                valueKind = valueKind,
                outputTarget = outputTarget,
                scope = scope,
                evidenceSource = evidenceSource,
            )

        fun rawRedactionCandidate(rawCandidate: String?): SkaldVaultV1VaultRedactionRequest =
            SkaldVaultV1VaultRedactionRequest(
                valueKind = SkaldVaultV1VaultRedactionValueKind.Unknown,
                outputTarget = SkaldVaultV1VaultRedactionOutputTarget.DefaultToString,
                scope = SkaldVaultV1VaultRedactionScope.Production,
                evidenceSource = SkaldVaultV1VaultRedactionEvidenceSource.StandalonePolicy,
                publicEvidenceId = null,
                aggregateCount = null,
                rawCandidateFailureReason = classifyRawCandidateFailure(rawCandidate),
            )

        private fun isSafePublicEvidenceId(candidate: String): Boolean {
            if (candidate.isBlank()) return false
            if (candidate.length > 96) return false
            return candidate.all { ch ->
                ch in 'a'..'z' ||
                    ch in 'A'..'Z' ||
                    ch in '0'..'9' ||
                    ch == '-' ||
                    ch == '_' ||
                    ch == '.'
            }
        }

        private fun classifyRawCandidateFailure(rawCandidate: String?): SkaldVaultV1VaultRedactionFailureReason {
            val candidate = rawCandidate?.trim() ?: return SkaldVaultV1VaultRedactionFailureReason.EmptyEvidenceRejected
            if (candidate.isEmpty()) return SkaldVaultV1VaultRedactionFailureReason.EmptyEvidenceRejected
            val lower = candidate.lowercase()
            if (lower.contains("://")) return SkaldVaultV1VaultRedactionFailureReason.LinkLikeInputRejected
            if (candidate.startsWith("/") || candidate.matches(Regex("""^[A-Za-z]:\\.*""")) ||
                candidate.startsWith("""\\""")
            ) {
                return SkaldVaultV1VaultRedactionFailureReason.RawAbsoluteLocationInputRejected
            }
            if (candidate.contains("/") || candidate.contains("\\")) {
                return SkaldVaultV1VaultRedactionFailureReason.RawRelativeLocationInputRejected
            }
            if (lower.contains("file-object") || lower.contains("path-object") || lower.contains("stream") ||
                lower.contains("database") || lower.contains("handle")
            ) {
                return SkaldVaultV1VaultRedactionFailureReason.PlatformObjectLikeInputRejected
            }
            if (lower.contains("stack-trace") || lower.contains("exception-stack")) {
                return SkaldVaultV1VaultRedactionFailureReason.StackTraceInputRejected
            }
            if (lower.contains("byte-array") || lower.contains("bytes")) {
                return SkaldVaultV1VaultRedactionFailureReason.RawByteArrayInputRejected
            }
            if (lower.contains("passphrase") || lower.contains("password")) {
                return SkaldVaultV1VaultRedactionFailureReason.PassphraseInputRejected
            }
            if (lower.contains("pin-fixture")) return SkaldVaultV1VaultRedactionFailureReason.PinInputRejected
            if (lower.contains("key-material") || lower.contains("raw-key") || lower.contains("secret-key")) {
                return SkaldVaultV1VaultRedactionFailureReason.KeyMaterialInputRejected
            }
            if (lower.contains("credential") || lower.contains("cookie") || lower.contains("token")) {
                return SkaldVaultV1VaultRedactionFailureReason.CredentialInputRejected
            }
            if (lower.contains("secret") || lower.contains("seed") || lower.contains("mnemonic")) {
                return SkaldVaultV1VaultRedactionFailureReason.SecretMaterialRejected
            }
            if (lower.startsWith("nsec1") || lower.startsWith("xprv") || lower.startsWith("tprv") ||
                isWifLike(candidate)
            ) {
                return SkaldVaultV1VaultRedactionFailureReason.WalletMaterialRejected
            }
            if (lower.startsWith("bc1") || lower.startsWith("tb1") || lower.startsWith("bcrt1")) {
                return SkaldVaultV1VaultRedactionFailureReason.BitcoinAddressLikeEvidenceRejected
            }
            if (candidate.length == 64 && candidate.all { it.isDigit() || it.lowercaseChar() in 'a'..'f' }) {
                return SkaldVaultV1VaultRedactionFailureReason.TransactionLikeEvidenceRejected
            }
            if (candidate.contains("..")) return SkaldVaultV1VaultRedactionFailureReason.TraversalRejected
            if (!isSafePublicEvidenceId(candidate)) {
                return SkaldVaultV1VaultRedactionFailureReason.UnsupportedEvidenceRejected
            }
            return SkaldVaultV1VaultRedactionFailureReason.RawSecretInputRejected
        }

        private fun isWifLike(candidate: String): Boolean {
            if (candidate.length !in 51..52) return false
            if (candidate.first() !in setOf('K', 'L', '5')) return false
            return candidate.all { ch ->
                ch in '1'..'9' ||
                    ch in 'A'..'H' ||
                    ch in 'J'..'N' ||
                    ch in 'P'..'Z' ||
                    ch in 'a'..'k' ||
                    ch in 'm'..'z'
            }
        }
    }
}

data class SkaldVaultV1VaultRedactionEvidence(
    val policyId: String,
    val policyVersion: Int,
    val status: SkaldVaultV1VaultRedactionStatus,
    val decision: SkaldVaultV1VaultRedactionDecision,
    val valueKind: SkaldVaultV1VaultRedactionValueKind,
    val outputTarget: SkaldVaultV1VaultRedactionOutputTarget,
    val scope: SkaldVaultV1VaultRedactionScope,
    val evidenceSource: SkaldVaultV1VaultRedactionEvidenceSource,
    val leakageSeverity: SkaldVaultV1VaultLeakageSeverity,
    val forbiddenValueClass: SkaldVaultV1VaultForbiddenValueClass?,
    val allowedEvidenceClass: SkaldVaultV1VaultAllowedEvidenceClass?,
    val capability: SkaldVaultV1VaultRedactionCapability,
    val redactionMarker: SkaldVaultV1VaultRedactionToken,
    val diagnosticSafetyEvidence: Set<SkaldVaultV1VaultDiagnosticSafetyEvidence>,
    val sourceGuardMaterialClasses: Set<SkaldVaultV1VaultSourceGuardMaterialClass>,
    val blockers: Set<SkaldVaultV1VaultRedactionBlocker>,
    val warnings: Set<SkaldVaultV1VaultRedactionWarning>,
    val redactionLeakageBoundaryModeled: Boolean = true,
    val redactionLeakageBoundaryStillDisabled: Boolean = true,
    val redactionLeakageClassifiesSensitiveValueKinds: Boolean = true,
    val redactionLeakageDoesNotAcceptRawSecrets: Boolean = true,
    val redactionLeakageDoesNotHashSecrets: Boolean = true,
    val redactionLeakageDoesNotLog: Boolean = true,
    val redactionLeakageDoesNotEnablePersistence: Boolean = true,
    val redactionLeakageDoesNotEnableProviderSelection: Boolean = true,
    val redactionLeakageFailureVocabularyModeled: Boolean = true,
    val rawSensitiveValueExposed: Boolean = false,
    val rawByteArrayExposed: Boolean = false,
    val stackTraceExposed: Boolean = false,
    val runtimeLoggingImplemented: Boolean = false,
    val crashReportingImplemented: Boolean = false,
    val analyticsImplemented: Boolean = false,
    val supportExportImplemented: Boolean = false,
    val secretHashingImplemented: Boolean = false,
    val secretFingerprintingImplemented: Boolean = false,
    val unlockReady: Boolean = false,
    val providerReady: Boolean = false,
    val persistenceReady: Boolean = false,
    val mainnetReady: Boolean = false,
) {
    override fun toString(): String =
        "SkaldVaultV1VaultRedactionEvidence(policyId=$policyId, status=$status, decision=$decision, valueKind=$valueKind, outputTarget=$outputTarget, scope=$scope, raw=redacted)"
}

sealed class SkaldVaultV1VaultRedactionResult<out T> {
    abstract val safeMessage: String
    abstract val rawValueExposed: Boolean
    abstract val secretMaterialExposed: Boolean
    abstract val loggingImplemented: Boolean
    abstract val persistenceEnabled: Boolean
    abstract val providerSelectable: Boolean

    data class Classified<out T>(
        val value: T,
    ) : SkaldVaultV1VaultRedactionResult<T>() {
        override val safeMessage: String = "redaction request classified with redacted diagnostics"
        override val rawValueExposed: Boolean = false
        override val secretMaterialExposed: Boolean = false
        override val loggingImplemented: Boolean = false
        override val persistenceEnabled: Boolean = false
        override val providerSelectable: Boolean = false
    }

    data class Rejected(
        val reason: SkaldVaultV1VaultRedactionFailureReason,
        val status: SkaldVaultV1VaultRedactionStatus,
    ) : SkaldVaultV1VaultRedactionResult<Nothing>() {
        override val safeMessage: String = "redaction request rejected with redacted diagnostics"
        override val rawValueExposed: Boolean = false
        override val secretMaterialExposed: Boolean = false
        override val loggingImplemented: Boolean = false
        override val persistenceEnabled: Boolean = false
        override val providerSelectable: Boolean = false
    }
}

object SkaldVaultV1RedactionLeakagePolicy : SkaldVaultV1RedactionLeakageBoundary {
    const val POLICY_ID: String = "skald-vault-v1-redaction-leakage-boundary-v1"
    const val POLICY_VERSION: Int = 1

    fun summary(): SkaldVaultV1VaultRedactionPolicySummary =
        SkaldVaultV1VaultRedactionPolicySummary(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            valueKindCount = SkaldVaultV1VaultRedactionValueKind.entries.size,
            outputTargetCount = SkaldVaultV1VaultRedactionOutputTarget.entries.size,
            scopeCount = SkaldVaultV1VaultRedactionScope.entries.size,
            stillDisabled = true,
        )

    override fun evaluate(
        request: SkaldVaultV1VaultRedactionRequest,
    ): SkaldVaultV1VaultRedactionResult<SkaldVaultV1VaultRedactionEvidence> {
        request.rawCandidateFailureReasonOrNull()?.let { reason ->
            return SkaldVaultV1VaultRedactionResult.Rejected(
                reason = reason,
                status = SkaldVaultV1VaultRedactionStatus.RawCandidateRejected,
            )
        }

        val classification = classify(request.valueKind, request.scope)
        val evidence = SkaldVaultV1VaultRedactionEvidence(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            status = classification.status,
            decision = classification.decision,
            valueKind = request.valueKind,
            outputTarget = request.outputTarget,
            scope = request.scope,
            evidenceSource = request.evidenceSource(),
            leakageSeverity = classification.severity,
            forbiddenValueClass = classification.forbiddenClass,
            allowedEvidenceClass = classification.allowedEvidenceClass,
            capability = SkaldVaultV1VaultRedactionCapability(),
            redactionMarker = SkaldVaultV1VaultRedactionToken(
                valueKind = request.valueKind,
                outputTarget = request.outputTarget,
                decision = classification.decision,
            ),
            diagnosticSafetyEvidence = SkaldVaultV1VaultDiagnosticSafetyEvidence.entries.toSet(),
            sourceGuardMaterialClasses = SkaldVaultV1VaultSourceGuardMaterialClass.entries.toSet(),
            blockers = blockersFor(classification),
            warnings = SkaldVaultV1VaultRedactionWarning.entries.toSet(),
        )
        return SkaldVaultV1VaultRedactionResult.Classified(evidence)
    }

    private fun classify(
        valueKind: SkaldVaultV1VaultRedactionValueKind,
        scope: SkaldVaultV1VaultRedactionScope,
    ): RedactionClassification =
        when (valueKind) {
            SkaldVaultV1VaultRedactionValueKind.Unknown -> RedactionClassification(
                decision = SkaldVaultV1VaultRedactionDecision.UnsupportedFailClosed,
                status = SkaldVaultV1VaultRedactionStatus.UnsupportedFailClosed,
                severity = SkaldVaultV1VaultLeakageSeverity.Unsupported,
                forbiddenClass = null,
                allowedEvidenceClass = null,
            )
            SkaldVaultV1VaultRedactionValueKind.Passphrase,
            SkaldVaultV1VaultRedactionValueKind.Pin,
            SkaldVaultV1VaultRedactionValueKind.MnemonicPhrase,
            SkaldVaultV1VaultRedactionValueKind.Seed,
            SkaldVaultV1VaultRedactionValueKind.PrivateKey,
            SkaldVaultV1VaultRedactionValueKind.XprvTprv,
            SkaldVaultV1VaultRedactionValueKind.Wif,
            SkaldVaultV1VaultRedactionValueKind.NostrNsec,
            SkaldVaultV1VaultRedactionValueKind.NostrPrivateKeyDerivedWalletMaterial,
            SkaldVaultV1VaultRedactionValueKind.DescriptorPrivateMaterial,
            SkaldVaultV1VaultRedactionValueKind.ProviderRootKey,
            SkaldVaultV1VaultRedactionValueKind.VaultRootKey,
            SkaldVaultV1VaultRedactionValueKind.MetadataEncryptionKey,
            SkaldVaultV1VaultRedactionValueKind.RecordEncryptionKey,
            SkaldVaultV1VaultRedactionValueKind.BackupExportKey,
            SkaldVaultV1VaultRedactionValueKind.KeyWrappingKey,
            SkaldVaultV1VaultRedactionValueKind.RawKdfOutput,
            SkaldVaultV1VaultRedactionValueKind.RawAeadKey,
            SkaldVaultV1VaultRedactionValueKind.RandomEntropySample,
            SkaldVaultV1VaultRedactionValueKind.DecryptedVaultRecord,
            SkaldVaultV1VaultRedactionValueKind.BackendCredential,
            SkaldVaultV1VaultRedactionValueKind.RpcCookie,
            SkaldVaultV1VaultRedactionValueKind.LightningMacaroonRuneNwcSecret,
            SkaldVaultV1VaultRedactionValueKind.PhoenixdToken,
            SkaldVaultV1VaultRedactionValueKind.CashuProofMaterial,
            SkaldVaultV1VaultRedactionValueKind.WalletDatabaseBytes,
            SkaldVaultV1VaultRedactionValueKind.BdkPersistenceHandle,
            SkaldVaultV1VaultRedactionValueKind.ExceptionStackTrace,
            -> forbidden(valueKind)
            SkaldVaultV1VaultRedactionValueKind.EncryptedVaultRecordBytes ->
                redactedSensitiveMetadata(SkaldVaultV1VaultRedactionDecision.RedactCompletely)
            SkaldVaultV1VaultRedactionValueKind.RecordIdentifier,
            SkaldVaultV1VaultRedactionValueKind.ManifestIdentifier,
            SkaldVaultV1VaultRedactionValueKind.StorageIndexIdentifier,
            SkaldVaultV1VaultRedactionValueKind.PlannedArtifactLocationToken,
            SkaldVaultV1VaultRedactionValueKind.RootToken,
            -> redactedSensitiveMetadata(SkaldVaultV1VaultRedactionDecision.ReplaceWithStableRedactedToken)
            SkaldVaultV1VaultRedactionValueKind.RawPlatformRootPath ->
                redactedSensitiveMetadata(SkaldVaultV1VaultRedactionDecision.RedactCompletely)
            SkaldVaultV1VaultRedactionValueKind.PaymentTransactionNote,
            SkaldVaultV1VaultRedactionValueKind.WalletLabel,
            SkaldVaultV1VaultRedactionValueKind.UtxoLabel,
            SkaldVaultV1VaultRedactionValueKind.AndroidDeviceIdentifier,
            SkaldVaultV1VaultRedactionValueKind.FilesystemErrorText,
            -> redactedSensitiveMetadata(SkaldVaultV1VaultRedactionDecision.SummarizeClassOnly)
            SkaldVaultV1VaultRedactionValueKind.TransactionHex,
            SkaldVaultV1VaultRedactionValueKind.Psbt,
            SkaldVaultV1VaultRedactionValueKind.TxidOutpoint,
            SkaldVaultV1VaultRedactionValueKind.BitcoinAddress,
            SkaldVaultV1VaultRedactionValueKind.NostrEventSignature,
            SkaldVaultV1VaultRedactionValueKind.BackendEndpoint,
            SkaldVaultV1VaultRedactionValueKind.OnionEndpoint,
            SkaldVaultV1VaultRedactionValueKind.TorRoutingMetadata,
            -> operationalMetadata()
            SkaldVaultV1VaultRedactionValueKind.PublicNonWalletCryptographicKatVector ->
                publicVector(scope)
            SkaldVaultV1VaultRedactionValueKind.PublicPolicyIdentifier ->
                allowedPublicEvidence(SkaldVaultV1VaultAllowedEvidenceClass.PublicPolicyIdentifier)
            SkaldVaultV1VaultRedactionValueKind.SentinelPlaceholder ->
                allowedPublicEvidence(SkaldVaultV1VaultAllowedEvidenceClass.SentinelPlaceholder)
            SkaldVaultV1VaultRedactionValueKind.EnumStatusValue ->
                allowEnumOrCapability(SkaldVaultV1VaultAllowedEvidenceClass.EnumStatusValue)
            SkaldVaultV1VaultRedactionValueKind.BooleanCapabilityFlag ->
                allowEnumOrCapability(SkaldVaultV1VaultAllowedEvidenceClass.BooleanCapabilityFlag)
            SkaldVaultV1VaultRedactionValueKind.AggregateCountStatistic -> RedactionClassification(
                decision = SkaldVaultV1VaultRedactionDecision.SummarizeCountOnly,
                status = SkaldVaultV1VaultRedactionStatus.PublicEvidenceAllowed,
                severity = SkaldVaultV1VaultLeakageSeverity.PublicEvidence,
                forbiddenClass = null,
                allowedEvidenceClass = SkaldVaultV1VaultAllowedEvidenceClass.AggregateCountStatistic,
            )
        }

    private fun forbidden(valueKind: SkaldVaultV1VaultRedactionValueKind): RedactionClassification {
        val forbiddenClass = when (valueKind) {
            SkaldVaultV1VaultRedactionValueKind.Passphrase,
            SkaldVaultV1VaultRedactionValueKind.Pin,
            SkaldVaultV1VaultRedactionValueKind.MnemonicPhrase,
            SkaldVaultV1VaultRedactionValueKind.Seed,
            -> SkaldVaultV1VaultForbiddenValueClass.UserUnlockSecret
            SkaldVaultV1VaultRedactionValueKind.ProviderRootKey ->
                SkaldVaultV1VaultForbiddenValueClass.ProviderKeyMaterial
            SkaldVaultV1VaultRedactionValueKind.VaultRootKey,
            SkaldVaultV1VaultRedactionValueKind.MetadataEncryptionKey,
            SkaldVaultV1VaultRedactionValueKind.RecordEncryptionKey,
            SkaldVaultV1VaultRedactionValueKind.BackupExportKey,
            SkaldVaultV1VaultRedactionValueKind.KeyWrappingKey,
            -> SkaldVaultV1VaultForbiddenValueClass.VaultKeyMaterial
            SkaldVaultV1VaultRedactionValueKind.RawKdfOutput,
            SkaldVaultV1VaultRedactionValueKind.RawAeadKey,
            SkaldVaultV1VaultRedactionValueKind.RandomEntropySample,
            -> SkaldVaultV1VaultForbiddenValueClass.RawCryptoOutput
            SkaldVaultV1VaultRedactionValueKind.DecryptedVaultRecord ->
                SkaldVaultV1VaultForbiddenValueClass.DecryptedPayload
            SkaldVaultV1VaultRedactionValueKind.BackendCredential,
            SkaldVaultV1VaultRedactionValueKind.RpcCookie,
            SkaldVaultV1VaultRedactionValueKind.LightningMacaroonRuneNwcSecret,
            SkaldVaultV1VaultRedactionValueKind.PhoenixdToken,
            SkaldVaultV1VaultRedactionValueKind.CashuProofMaterial,
            -> SkaldVaultV1VaultForbiddenValueClass.Credential
            SkaldVaultV1VaultRedactionValueKind.WalletDatabaseBytes ->
                SkaldVaultV1VaultForbiddenValueClass.WalletDatabase
            SkaldVaultV1VaultRedactionValueKind.BdkPersistenceHandle ->
                SkaldVaultV1VaultForbiddenValueClass.PersistenceHandle
            SkaldVaultV1VaultRedactionValueKind.ExceptionStackTrace ->
                SkaldVaultV1VaultForbiddenValueClass.StackTrace
            else -> SkaldVaultV1VaultForbiddenValueClass.WalletKeyMaterial
        }
        return RedactionClassification(
            decision = SkaldVaultV1VaultRedactionDecision.Forbidden,
            status = SkaldVaultV1VaultRedactionStatus.ForbiddenSensitiveValue,
            severity = SkaldVaultV1VaultLeakageSeverity.ForbiddenSecret,
            forbiddenClass = forbiddenClass,
            allowedEvidenceClass = null,
        )
    }

    private fun redactedSensitiveMetadata(
        decision: SkaldVaultV1VaultRedactionDecision,
    ): RedactionClassification =
        RedactionClassification(
            decision = decision,
            status = SkaldVaultV1VaultRedactionStatus.RedactedSensitiveValue,
            severity = SkaldVaultV1VaultLeakageSeverity.SensitiveMetadata,
            forbiddenClass = null,
            allowedEvidenceClass = null,
        )

    private fun operationalMetadata(): RedactionClassification =
        RedactionClassification(
            decision = SkaldVaultV1VaultRedactionDecision.SummarizeClassOnly,
            status = SkaldVaultV1VaultRedactionStatus.Classified,
            severity = SkaldVaultV1VaultLeakageSeverity.OperationalMetadata,
            forbiddenClass = null,
            allowedEvidenceClass = null,
        )

    private fun allowedPublicEvidence(
        allowedEvidenceClass: SkaldVaultV1VaultAllowedEvidenceClass,
    ): RedactionClassification =
        RedactionClassification(
            decision = SkaldVaultV1VaultRedactionDecision.AllowPublicPolicyIdentifier,
            status = SkaldVaultV1VaultRedactionStatus.PublicEvidenceAllowed,
            severity = SkaldVaultV1VaultLeakageSeverity.PublicEvidence,
            forbiddenClass = null,
            allowedEvidenceClass = allowedEvidenceClass,
        )

    private fun allowEnumOrCapability(
        allowedEvidenceClass: SkaldVaultV1VaultAllowedEvidenceClass,
    ): RedactionClassification =
        RedactionClassification(
            decision = SkaldVaultV1VaultRedactionDecision.AllowEnumOrCapability,
            status = SkaldVaultV1VaultRedactionStatus.PublicEvidenceAllowed,
            severity = SkaldVaultV1VaultLeakageSeverity.Harmless,
            forbiddenClass = null,
            allowedEvidenceClass = allowedEvidenceClass,
        )

    private fun publicVector(
        scope: SkaldVaultV1VaultRedactionScope,
    ): RedactionClassification {
        val allowedScope = scope in setOf(
            SkaldVaultV1VaultRedactionScope.Docs,
            SkaldVaultV1VaultRedactionScope.Test,
            SkaldVaultV1VaultRedactionScope.KatVector,
            SkaldVaultV1VaultRedactionScope.SourceGuard,
        )
        return if (allowedScope) {
            RedactionClassification(
                decision = SkaldVaultV1VaultRedactionDecision.AllowPublicNonWalletVectorInTestScopeOnly,
                status = SkaldVaultV1VaultRedactionStatus.PublicVectorAllowedInNarrowScope,
                severity = SkaldVaultV1VaultLeakageSeverity.PublicEvidence,
                forbiddenClass = null,
                allowedEvidenceClass = SkaldVaultV1VaultAllowedEvidenceClass.PublicNonWalletCryptographicKatVector,
            )
        } else {
            RedactionClassification(
                decision = SkaldVaultV1VaultRedactionDecision.RejectDiagnostic,
                status = SkaldVaultV1VaultRedactionStatus.DiagnosticRejected,
                severity = SkaldVaultV1VaultLeakageSeverity.Unsupported,
                forbiddenClass = null,
                allowedEvidenceClass = null,
            )
        }
    }

    private fun blockersFor(
        classification: RedactionClassification,
    ): Set<SkaldVaultV1VaultRedactionBlocker> =
        buildSet {
            add(SkaldVaultV1VaultRedactionBlocker.RuntimeLoggingDisabled)
            add(SkaldVaultV1VaultRedactionBlocker.CrashReportingDisabled)
            add(SkaldVaultV1VaultRedactionBlocker.AnalyticsDisabled)
            add(SkaldVaultV1VaultRedactionBlocker.SupportExportDisabled)
            add(SkaldVaultV1VaultRedactionBlocker.SecretHashingDisabled)
            add(SkaldVaultV1VaultRedactionBlocker.SecretFingerprintingDisabled)
            add(SkaldVaultV1VaultRedactionBlocker.RawSecretDisplayDisabled)
            add(SkaldVaultV1VaultRedactionBlocker.ProviderSelectionBlocked)
            add(SkaldVaultV1VaultRedactionBlocker.VaultUnlockBlocked)
            add(SkaldVaultV1VaultRedactionBlocker.VaultPersistenceBlocked)
            add(SkaldVaultV1VaultRedactionBlocker.MainnetDisabled)
            when (classification.decision) {
                SkaldVaultV1VaultRedactionDecision.Forbidden -> {
                    add(SkaldVaultV1VaultRedactionBlocker.RawSecretsNotAccepted)
                    add(SkaldVaultV1VaultRedactionBlocker.SensitiveValueForbidden)
                }
                SkaldVaultV1VaultRedactionDecision.RedactCompletely,
                SkaldVaultV1VaultRedactionDecision.ReplaceWithStableRedactedToken,
                SkaldVaultV1VaultRedactionDecision.SummarizeClassOnly,
                SkaldVaultV1VaultRedactionDecision.SummarizeCountOnly,
                -> add(SkaldVaultV1VaultRedactionBlocker.RawValueRedacted)
                SkaldVaultV1VaultRedactionDecision.RejectDiagnostic -> {
                    add(SkaldVaultV1VaultRedactionBlocker.PublicVectorScopeRejected)
                    add(SkaldVaultV1VaultRedactionBlocker.PublicVectorRejectedInWalletUtxoSyncScope)
                }
                SkaldVaultV1VaultRedactionDecision.RequiresManualReview ->
                    add(SkaldVaultV1VaultRedactionBlocker.RawValueRedacted)
                SkaldVaultV1VaultRedactionDecision.UnsupportedFailClosed ->
                    add(SkaldVaultV1VaultRedactionBlocker.UnknownValueKindRejected)
                SkaldVaultV1VaultRedactionDecision.AllowPublicPolicyIdentifier,
                SkaldVaultV1VaultRedactionDecision.AllowPublicNonWalletVectorInTestScopeOnly,
                SkaldVaultV1VaultRedactionDecision.AllowEnumOrCapability,
                -> Unit
            }
        }

    private data class RedactionClassification(
        val decision: SkaldVaultV1VaultRedactionDecision,
        val status: SkaldVaultV1VaultRedactionStatus,
        val severity: SkaldVaultV1VaultLeakageSeverity,
        val forbiddenClass: SkaldVaultV1VaultForbiddenValueClass?,
        val allowedEvidenceClass: SkaldVaultV1VaultAllowedEvidenceClass?,
    )
}
