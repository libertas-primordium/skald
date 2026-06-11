package com.libertasprimordium.skald.security

interface SkaldVaultV1ProviderOperationAuthorizationBoundary {
    fun evaluate(
        request: SkaldVaultV1VaultProviderOperationAuthorizationRequest,
    ): SkaldVaultV1VaultProviderOperationAuthorizationResult<SkaldVaultV1VaultProviderOperationAuthorizationEvidence>
}

enum class SkaldVaultV1VaultProviderOperationAuthorizationSource(val label: String) {
    NoEvidence("no provider operation authorization evidence"),
    PolicySummary("provider operation authorization policy summary"),
    OperationKind("provider operation kind"),
    OperationPurpose("provider operation purpose"),
    RequiredGate("provider operation required gate"),
    ComposedTypedEvidence("composed typed provider operation evidence"),
    RawOperationCandidate("raw provider operation candidate"),
}

enum class SkaldVaultV1VaultProviderOperationAuthorizationStatus(val label: String) {
    NoEvidenceAvailable("no provider operation authorization evidence available"),
    PolicySummaryModeled("provider operation authorization policy summary modeled"),
    OperationKindModeled("provider operation kind modeled"),
    OperationPurposeModeled("provider operation purpose modeled"),
    RequiredGateModeled("provider operation required gate modeled"),
    ProviderOperationBlockedStillDisabled("provider operation authorization remains blocked and still disabled"),
    RawCandidateRejected("raw provider operation candidate rejected"),
}

enum class SkaldVaultV1VaultProviderOperationAuthorizationDecision(
    val providerOperationAllowed: Boolean,
    val providerCryptoAllowed: Boolean,
    val katAllowed: Boolean,
    val randomnessAllowed: Boolean,
    val kdfAllowed: Boolean,
    val aeadAllowed: Boolean,
    val unlockAllowed: Boolean,
    val persistenceAllowed: Boolean,
) {
    BlockedFailClosed(
        providerOperationAllowed = false,
        providerCryptoAllowed = false,
        katAllowed = false,
        randomnessAllowed = false,
        kdfAllowed = false,
        aeadAllowed = false,
        unlockAllowed = false,
        persistenceAllowed = false,
    ),
    Unauthorized(
        providerOperationAllowed = false,
        providerCryptoAllowed = false,
        katAllowed = false,
        randomnessAllowed = false,
        kdfAllowed = false,
        aeadAllowed = false,
        unlockAllowed = false,
        persistenceAllowed = false,
    ),
    RejectOperation(
        providerOperationAllowed = false,
        providerCryptoAllowed = false,
        katAllowed = false,
        randomnessAllowed = false,
        kdfAllowed = false,
        aeadAllowed = false,
        unlockAllowed = false,
        persistenceAllowed = false,
    ),
    TestOnlyScopeRejectedForProduction(
        providerOperationAllowed = false,
        providerCryptoAllowed = false,
        katAllowed = false,
        randomnessAllowed = false,
        kdfAllowed = false,
        aeadAllowed = false,
        unlockAllowed = false,
        persistenceAllowed = false,
    ),
    UnsupportedFailClosed(
        providerOperationAllowed = false,
        providerCryptoAllowed = false,
        katAllowed = false,
        randomnessAllowed = false,
        kdfAllowed = false,
        aeadAllowed = false,
        unlockAllowed = false,
        persistenceAllowed = false,
    ),
}

enum class SkaldVaultV1VaultProviderOperationKind(val label: String) {
    ProviderAvailabilityCheck("provider availability check"),
    ProviderKatExecution("provider KAT execution"),
    RuntimeRandomnessCheck("runtime randomness check"),
    EntropyNonceGeneration("entropy/nonces generation"),
    SaltGeneration("salt generation"),
    KeyGeneration("key generation"),
    Argon2idKdfExecution("Argon2id/KDF execution"),
    HkdfExtractionExpansion("HKDF extraction/expansion"),
    HmacComputation("HMAC computation"),
    HeaderCommitmentComputation("header commitment computation"),
    HeaderCommitmentVerification("header commitment verification"),
    AeadEncrypt("AEAD encrypt"),
    AeadDecrypt("AEAD decrypt"),
    RecordEncrypt("record encrypt"),
    RecordDecrypt("record decrypt"),
    ManifestAuthentication("manifest authentication"),
    StorageIndexAuthentication("storage-index authentication"),
    KeyWrapping("key wrapping"),
    KeyUnwrapping("key unwrapping"),
    ProviderClearDispose("provider clear/dispose"),
    ProviderSelfTest("provider self-test"),
    ProductionProviderSelection("production provider selection"),
    VaultCreate("vault create"),
    VaultUnlock("vault unlock"),
    VaultPersistence("vault persistence"),
    MainnetOperation("mainnet operation"),
}

enum class SkaldVaultV1VaultProviderOperationPurpose(val label: String) {
    CreateVault("create vault"),
    UnlockVault("unlock vault"),
    RotateKeyMaterial("rotate key material"),
    EncryptCurrentContainer("encrypt current container"),
    DecryptCurrentContainer("decrypt current container"),
    EncryptMetadata("encrypt metadata"),
    DecryptMetadata("decrypt metadata"),
    EncryptRecord("encrypt record"),
    DecryptRecord("decrypt record"),
    AuthenticateManifest("authenticate manifest"),
    AuthenticateStorageIndex("authenticate storage index"),
    VerifyMigrationCandidate("verify migration candidate"),
    VerifyCorruptionEvidence("verify corruption evidence"),
    RunProviderKat("run provider KAT"),
    RunRuntimeHealthCheck("run runtime health check"),
    PrepareBackupExport("prepare backup/export"),
    RestoreImport("restore/import"),
    TestOnlyDeterministicVector("test-only deterministic vector"),
    ProductionRuntime("production runtime"),
    ReleaseValidation("release validation"),
    MainnetValidation("mainnet validation"),
}

enum class SkaldVaultV1VaultProviderOperationRequiredGate(val label: String) {
    ProviderIsNotDisabled("provider is not disabled"),
    ProviderSelectedThroughRegistry("provider selected through registry"),
    ProductionProviderSelectableTrue("productionProviderSelectable=true"),
    ProductionProviderImplementationExists("production provider implementation exists"),
    ProviderKatsApproved("provider KATs approved"),
    DependencyProbeApproved("dependency probe approved"),
    RuntimeRandomnessCheckApproved("runtime randomness check approved"),
    EntropyPolicyApproved("entropy policy approved"),
    FinalKdfCalibrationApproved("final KDF calibration approved"),
    Argon2idParameterPolicyApproved("Argon2id parameter policy approved"),
    PassphrasePolicyApproved("passphrase policy approved"),
    LockSessionLifecycleApproved("lock/session lifecycle approved"),
    RedactionLeakagePolicyApproved("redaction/leakage policy approved"),
    ClearWipeStrategyApproved("clear/wipe strategy approved"),
    PersistenceReadinessApprovedForStorage("persistence readiness approved where storage is involved"),
    StorageSafetyApprovedForStorage("storage safety approved where storage is involved"),
    MigrationCorruptionPolicyApprovedForParsingMigration(
        "migration/corruption policy approved where parsing/migration is involved",
    ),
    SecureSecretStorageApproved("secure secret storage approved"),
    SecureMetadataStorageApproved("secure metadata storage approved"),
    NoRawSecretDiagnosticExposure("no raw secret diagnostic exposure"),
    NoProviderOperationInUiOrDomainPolicyDirectly("no provider operation in UI/domain policy directly"),
    NoBdkPersistenceBypass("no BDK persistence bypass"),
    OperationAllowedForNetworkMode("operation allowed for network mode"),
    MainnetReleaseReviewApproved("mainnet remains disabled unless release review approves it"),
}

enum class SkaldVaultV1VaultProviderOperationBlocker(val label: String) {
    ProviderOperationAuthorizationStillDisabled("provider operation authorization remains still-disabled"),
    ProviderOperationAuthorizationRuntimeReviewMissing("provider operation authorization runtime review missing"),
    ProviderSelectionDisabled("provider selection disabled"),
    DisabledProviderSelected("registry selects only the disabled provider"),
    ProductionProviderSelectableFalse("productionProviderSelectable remains false"),
    ProductionProviderImplementationMissing("production provider implementation missing"),
    ProviderKatApprovalMissing("provider KAT approval missing"),
    DependencyProbeApprovalMissing("dependency probe approval missing"),
    RuntimeRandomnessCheckUnavailable("runtime randomness check unavailable"),
    EntropyPolicyUnavailable("entropy policy unavailable"),
    FinalKdfCalibrationMissing("final KDF calibration missing"),
    Argon2idParameterPolicyNotFinal("Argon2id parameter policy is not final"),
    PassphrasePolicyBlocked("passphrase policy blocked"),
    LockSessionLifecycleBlocked("lock/session lifecycle blocked"),
    RedactionLeakageUnsafe("redaction/leakage unsafe for diagnostics"),
    ClearWipeStrategyBlocked("clear/wipe strategy blocked"),
    PersistenceReadinessBlocked("persistence readiness blocked"),
    StorageSafetyPreflightBlocked("storage safety preflight blocked"),
    MigrationCorruptionBoundaryDisabled("migration/corruption boundary disabled"),
    DisabledStorageServiceFacadeStillDisabled("disabled storage service facade remains still-disabled"),
    SecureSecretStorageUnavailable("secure secret storage unavailable"),
    SecureMetadataStorageUnavailable("secure metadata storage unavailable"),
    RawSecretDiagnosticsRejected("raw secret diagnostics rejected"),
    UiDomainProviderOperationRejected("provider operation direct UI/domain use rejected"),
    BdkPersistenceBypassRejected("BDK persistence bypass rejected"),
    NetworkModeBlocked("operation network mode blocked"),
    MainnetUnavailable("mainnet remains unavailable"),
    WarningOnlyEvidenceRejected("warning-only evidence cannot authorize provider operations"),
    UserConsentOverrideRejected("user consent cannot override missing hard gates"),
    TestOnlyVectorScopeRejectedForProduction("test-only vector scope cannot authorize production runtime"),
    RawOperationCandidateRejected("raw provider operation candidate rejected"),
}

enum class SkaldVaultV1VaultProviderOperationWarning(val label: String) {
    EvidenceOnly("provider operation authorization result is evidence only"),
    NoProviderOperationExecuted("no provider operation is executed"),
    RegistrySelectsDisabledProvider("provider registry selects only the disabled provider"),
    ProductionProviderSelectableFalse("productionProviderSelectable remains false"),
    TestOnlyScopeDoesNotAuthorizeProduction("test-only scope does not authorize production runtime operations"),
    FutureImplementationRequiresReview("future provider operation implementation requires review"),
    RedactedDiagnosticsOnly("diagnostics must remain redacted"),
}

data class SkaldVaultV1VaultProviderOperationCapability(
    val providerOperationAuthorized: Boolean,
    val providerCryptoAvailable: Boolean,
    val providerKatExecutionAvailable: Boolean,
    val runtimeRandomnessCheckAvailable: Boolean,
    val entropyGenerationAvailable: Boolean,
    val saltGenerationAvailable: Boolean,
    val nonceGenerationAvailable: Boolean,
    val keyGenerationAvailable: Boolean,
    val kdfExecutionAvailable: Boolean,
    val argon2idExecutionAvailable: Boolean,
    val hkdfExecutionAvailable: Boolean,
    val hmacExecutionAvailable: Boolean,
    val headerCommitmentComputationAvailable: Boolean,
    val headerCommitmentVerificationAvailable: Boolean,
    val aeadEncryptAvailable: Boolean,
    val aeadDecryptAvailable: Boolean,
    val recordEncryptAvailable: Boolean,
    val recordDecryptAvailable: Boolean,
    val keyWrappingAvailable: Boolean,
    val keyUnwrappingAvailable: Boolean,
    val providerClearAvailable: Boolean,
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
        val StillDisabled = SkaldVaultV1VaultProviderOperationCapability(
            providerOperationAuthorized = false,
            providerCryptoAvailable = false,
            providerKatExecutionAvailable = false,
            runtimeRandomnessCheckAvailable = false,
            entropyGenerationAvailable = false,
            saltGenerationAvailable = false,
            nonceGenerationAvailable = false,
            keyGenerationAvailable = false,
            kdfExecutionAvailable = false,
            argon2idExecutionAvailable = false,
            hkdfExecutionAvailable = false,
            hmacExecutionAvailable = false,
            headerCommitmentComputationAvailable = false,
            headerCommitmentVerificationAvailable = false,
            aeadEncryptAvailable = false,
            aeadDecryptAvailable = false,
            recordEncryptAvailable = false,
            recordDecryptAvailable = false,
            keyWrappingAvailable = false,
            keyUnwrappingAvailable = false,
            providerClearAvailable = false,
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

data class SkaldVaultV1VaultProviderOperationPolicySummary(
    val policyId: String,
    val policyVersion: Int,
    val operationKinds: Set<SkaldVaultV1VaultProviderOperationKind>,
    val operationPurposes: Set<SkaldVaultV1VaultProviderOperationPurpose>,
    val requiredGates: Set<SkaldVaultV1VaultProviderOperationRequiredGate>,
    val stillDisabled: Boolean,
)

class SkaldVaultV1VaultProviderOperationPolicyToken internal constructor(
    val policyId: String,
    val operationKind: SkaldVaultV1VaultProviderOperationKind?,
    val operationPurpose: SkaldVaultV1VaultProviderOperationPurpose?,
    val requiredGate: SkaldVaultV1VaultProviderOperationRequiredGate?,
    val containsPassphrase: Boolean = false,
    val containsKeyMaterial: Boolean = false,
    val containsProviderHandle: Boolean = false,
    val containsEntropyRandomSaltNonceBytes: Boolean = false,
    val containsKdfOutput: Boolean = false,
    val containsHeaderCommitmentBytes: Boolean = false,
    val containsAeadTagBytes: Boolean = false,
    val containsCiphertext: Boolean = false,
    val containsPlaintext: Boolean = false,
    val containsRecordIdentifier: Boolean = false,
    val containsRootOrPathText: Boolean = false,
    val containsPayload: Boolean = false,
) {
    override fun toString(): String =
        "SkaldVaultV1VaultProviderOperationPolicyToken(" +
            "policyId=$policyId, " +
            "operationKind=${operationKind?.name ?: "none"}, " +
            "operationPurpose=${operationPurpose?.name ?: "none"}, " +
            "requiredGate=${requiredGate?.name ?: "none"}, " +
            "redacted=true)"
}

data class SkaldVaultV1VaultProviderOperationAuthorizationEvidence(
    val policyId: String,
    val policyVersion: Int,
    val status: SkaldVaultV1VaultProviderOperationAuthorizationStatus,
    val decision: SkaldVaultV1VaultProviderOperationAuthorizationDecision,
    val source: SkaldVaultV1VaultProviderOperationAuthorizationSource,
    val operationKind: SkaldVaultV1VaultProviderOperationKind?,
    val operationPurpose: SkaldVaultV1VaultProviderOperationPurpose?,
    val requiredGate: SkaldVaultV1VaultProviderOperationRequiredGate?,
    val requiredGates: Set<SkaldVaultV1VaultProviderOperationRequiredGate>,
    val blockers: Set<SkaldVaultV1VaultProviderOperationBlocker>,
    val warnings: Set<SkaldVaultV1VaultProviderOperationWarning>,
    val capability: SkaldVaultV1VaultProviderOperationCapability,
    val policySummary: SkaldVaultV1VaultProviderOperationPolicySummary,
    val policyTokenEvidence: SkaldVaultV1VaultProviderOperationPolicyToken,
    val providerSelectionEvidenceConsumed: Boolean,
    val providerAcceptanceEvidenceConsumed: Boolean,
    val dependencyProbeEvidenceConsumed: Boolean,
    val encryptedVaultReadinessEvidenceConsumed: Boolean,
    val persistenceReadinessEvidenceConsumed: Boolean,
    val lockSessionLifecycleEvidenceConsumed: Boolean,
    val passphrasePolicyEvidenceConsumed: Boolean,
    val redactionLeakageEvidenceConsumed: Boolean,
    val clearWipeStrategyEvidenceConsumed: Boolean,
    val migrationCorruptionEvidenceConsumed: Boolean,
    val storageSafetyPreflightEvidenceConsumed: Boolean,
    val disabledStorageServiceEvidenceConsumed: Boolean,
    val secureStorageEvidenceConsumed: Boolean,
    val secureMetadataEvidenceConsumed: Boolean,
    val providerOperationAuthorizationBoundaryModeled: Boolean = true,
    val providerOperationAuthorizationStillDisabled: Boolean = true,
    val providerOperationAuthorizationBlocksAllOperations: Boolean = true,
    val providerOperationAuthorizationDoesNotRunCrypto: Boolean = true,
    val providerOperationAuthorizationDoesNotRunKat: Boolean = true,
    val providerOperationAuthorizationDoesNotGenerateRandomness: Boolean = true,
    val providerOperationAuthorizationDoesNotEnableUnlock: Boolean = true,
    val providerOperationAuthorizationDoesNotEnablePersistence: Boolean = true,
    val providerOperationAuthorizationDoesNotEnableProviderSelection: Boolean = true,
    val providerOperationFailureVocabularyModeled: Boolean = true,
    val providerOperationAuthorized: Boolean = false,
    val providerCryptoReady: Boolean = false,
    val providerKatReady: Boolean = false,
    val runtimeRandomnessReady: Boolean = false,
    val entropyGenerationReady: Boolean = false,
    val keyGenerationReady: Boolean = false,
    val kdfReady: Boolean = false,
    val aeadReady: Boolean = false,
    val recordEncryptionReady: Boolean = false,
    val recordDecryptionReady: Boolean = false,
    val providerSelectable: Boolean = false,
    val vaultUnlockReady: Boolean = false,
    val vaultStorageAvailable: Boolean = false,
    val persistenceReady: Boolean = false,
    val vaultPersistenceReady: Boolean = false,
) {
    override fun toString(): String =
        "SkaldVaultV1VaultProviderOperationAuthorizationEvidence(" +
            "policyId=$policyId, " +
            "status=${status.name}, " +
            "decision=${decision.name}, " +
            "source=${source.name}, " +
            "operationKind=${operationKind?.name ?: "none"}, " +
            "operationPurpose=${operationPurpose?.name ?: "none"}, " +
            "requiredGate=${requiredGate?.name ?: "none"}, " +
            "capability=still-disabled, " +
            "policyTokenEvidence=$policyTokenEvidence)"
}

sealed class SkaldVaultV1VaultProviderOperationAuthorizationResult<out T> {
    data class Blocked<out T>(
        val value: T,
    ) : SkaldVaultV1VaultProviderOperationAuthorizationResult<T>() {
        override fun toString(): String =
            "SkaldVaultV1VaultProviderOperationAuthorizationResult.Blocked(value=$value)"
    }

    data class Rejected(
        val reason: SkaldVaultV1VaultProviderOperationFailureReason,
        val status: SkaldVaultV1VaultProviderOperationAuthorizationStatus,
        val source: SkaldVaultV1VaultProviderOperationAuthorizationSource,
        val safeMessage: String,
    ) : SkaldVaultV1VaultProviderOperationAuthorizationResult<Nothing>() {
        override fun toString(): String =
            "SkaldVaultV1VaultProviderOperationAuthorizationResult.Rejected(" +
                "reason=${reason.name}, " +
                "status=${status.name})"
    }
}

enum class SkaldVaultV1VaultProviderOperationFailureReason(val label: String) {
    EmptyEvidenceRejected("empty provider operation evidence rejected"),
    ProviderHandleRejected("provider handle rejected"),
    ProviderImplementationInstanceRejected("provider implementation instance rejected"),
    RawPassphraseRejected("raw passphrase rejected"),
    KeyMaterialRejected("key material rejected"),
    KdfInputRejected("KDF input rejected"),
    KdfOutputRejected("KDF output rejected"),
    EntropyRandomSaltNonceBytesRejected("entropy/random/salt/nonce bytes rejected"),
    AeadKeyTagCiphertextPlaintextRejected("AEAD key/tag/ciphertext/plaintext rejected"),
    RecordBytesRejected("record bytes rejected"),
    RawPersistedContainerBytesRejected("raw persisted container bytes rejected"),
    ByteArrayInputRejected("byte-array input rejected"),
    CharArrayInputRejected("char-array input rejected"),
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
    RawProviderOperationInputRejected("raw provider operation input rejected"),
}

class SkaldVaultV1VaultProviderOperationAuthorizationRequest private constructor(
    val source: SkaldVaultV1VaultProviderOperationAuthorizationSource,
    val operationKind: SkaldVaultV1VaultProviderOperationKind?,
    val operationPurpose: SkaldVaultV1VaultProviderOperationPurpose?,
    val requiredGate: SkaldVaultV1VaultProviderOperationRequiredGate?,
    private val rawCandidate: String?,
    private val providerSelectionResult: VaultCryptoProviderSelectionResult?,
    private val providerAcceptanceAssessment: ProductionProviderAcceptanceAssessment?,
    private val dependencyProbeResult: VaultCryptoDependencyProbeResult?,
    private val encryptedVaultReadiness: EncryptedVaultReadiness?,
    private val persistenceReadinessEvidence: SkaldVaultV1VaultPersistenceReadinessEvidence?,
    private val lockSessionLifecycleEvidence: SkaldVaultV1VaultLockSessionEvidence?,
    private val passphrasePolicyEvidence: SkaldVaultV1VaultPassphrasePolicyEvidence?,
    private val redactionLeakageEvidence: SkaldVaultV1VaultRedactionEvidence?,
    private val clearWipeStrategyEvidence: SkaldVaultV1VaultClearWipeEvidence?,
    private val migrationCorruptionEvidence: SkaldVaultV1VaultMigrationCorruptionEvidence?,
    private val storageSafetyPreflightEvidence: SkaldVaultV1StorageSafetyPreflightEvidence?,
    private val disabledStorageServiceEvidence: SkaldVaultV1VaultStorageDisabledEvidence?,
    private val secureStorageCapability: SecureStorageCapability?,
    private val secureMetadataCapability: SecureMetadataPersistenceCapability?,
) {
    val rawCandidateRejected: Boolean
        get() = source == SkaldVaultV1VaultProviderOperationAuthorizationSource.RawOperationCandidate

    internal fun rawCandidateOrNull(): String? = rawCandidate

    internal fun providerSelectionResultOrNull(): VaultCryptoProviderSelectionResult? = providerSelectionResult

    internal fun providerAcceptanceAssessmentOrNull(): ProductionProviderAcceptanceAssessment? =
        providerAcceptanceAssessment

    internal fun dependencyProbeResultOrNull(): VaultCryptoDependencyProbeResult? = dependencyProbeResult

    internal fun encryptedVaultReadinessOrNull(): EncryptedVaultReadiness? = encryptedVaultReadiness

    internal fun persistenceReadinessEvidenceOrNull(): SkaldVaultV1VaultPersistenceReadinessEvidence? =
        persistenceReadinessEvidence

    internal fun lockSessionLifecycleEvidenceOrNull(): SkaldVaultV1VaultLockSessionEvidence? =
        lockSessionLifecycleEvidence

    internal fun passphrasePolicyEvidenceOrNull(): SkaldVaultV1VaultPassphrasePolicyEvidence? =
        passphrasePolicyEvidence

    internal fun redactionLeakageEvidenceOrNull(): SkaldVaultV1VaultRedactionEvidence? =
        redactionLeakageEvidence

    internal fun clearWipeStrategyEvidenceOrNull(): SkaldVaultV1VaultClearWipeEvidence? =
        clearWipeStrategyEvidence

    internal fun migrationCorruptionEvidenceOrNull(): SkaldVaultV1VaultMigrationCorruptionEvidence? =
        migrationCorruptionEvidence

    internal fun storageSafetyPreflightEvidenceOrNull(): SkaldVaultV1StorageSafetyPreflightEvidence? =
        storageSafetyPreflightEvidence

    internal fun disabledStorageServiceEvidenceOrNull(): SkaldVaultV1VaultStorageDisabledEvidence? =
        disabledStorageServiceEvidence

    internal fun secureStorageCapabilityOrNull(): SecureStorageCapability? = secureStorageCapability

    internal fun secureMetadataCapabilityOrNull(): SecureMetadataPersistenceCapability? = secureMetadataCapability

    override fun toString(): String =
        "SkaldVaultV1VaultProviderOperationAuthorizationRequest(" +
            "source=${source.name}, " +
            "operationKind=${operationKind?.name ?: "none"}, " +
            "operationPurpose=${operationPurpose?.name ?: "none"}, " +
            "requiredGate=${requiredGate?.name ?: "none"}, " +
            "rawCandidate=REDACTED)"

    companion object {
        fun noEvidence(): SkaldVaultV1VaultProviderOperationAuthorizationRequest =
            SkaldVaultV1VaultProviderOperationAuthorizationRequest(
                source = SkaldVaultV1VaultProviderOperationAuthorizationSource.NoEvidence,
                operationKind = null,
                operationPurpose = null,
                requiredGate = null,
                rawCandidate = null,
                providerSelectionResult = null,
                providerAcceptanceAssessment = null,
                dependencyProbeResult = null,
                encryptedVaultReadiness = null,
                persistenceReadinessEvidence = null,
                lockSessionLifecycleEvidence = null,
                passphrasePolicyEvidence = null,
                redactionLeakageEvidence = null,
                clearWipeStrategyEvidence = null,
                migrationCorruptionEvidence = null,
                storageSafetyPreflightEvidence = null,
                disabledStorageServiceEvidence = null,
                secureStorageCapability = null,
                secureMetadataCapability = null,
            )

        fun summary(): SkaldVaultV1VaultProviderOperationAuthorizationRequest =
            noEvidence().copyFor(source = SkaldVaultV1VaultProviderOperationAuthorizationSource.PolicySummary)

        fun forOperationKind(
            operationKind: SkaldVaultV1VaultProviderOperationKind,
            operationPurpose: SkaldVaultV1VaultProviderOperationPurpose? = null,
        ): SkaldVaultV1VaultProviderOperationAuthorizationRequest =
            noEvidence().copyFor(
                source = SkaldVaultV1VaultProviderOperationAuthorizationSource.OperationKind,
                operationKind = operationKind,
                operationPurpose = operationPurpose,
            )

        fun forOperationPurpose(
            operationPurpose: SkaldVaultV1VaultProviderOperationPurpose,
        ): SkaldVaultV1VaultProviderOperationAuthorizationRequest =
            noEvidence().copyFor(
                source = SkaldVaultV1VaultProviderOperationAuthorizationSource.OperationPurpose,
                operationPurpose = operationPurpose,
            )

        fun forRequiredGate(
            requiredGate: SkaldVaultV1VaultProviderOperationRequiredGate,
        ): SkaldVaultV1VaultProviderOperationAuthorizationRequest =
            noEvidence().copyFor(
                source = SkaldVaultV1VaultProviderOperationAuthorizationSource.RequiredGate,
                requiredGate = requiredGate,
            )

        fun fromEvidence(
            providerSelectionResult: VaultCryptoProviderSelectionResult? = null,
            providerAcceptanceAssessment: ProductionProviderAcceptanceAssessment? = null,
            dependencyProbeResult: VaultCryptoDependencyProbeResult? = null,
            encryptedVaultReadiness: EncryptedVaultReadiness? = null,
            persistenceReadinessEvidence: SkaldVaultV1VaultPersistenceReadinessEvidence? = null,
            lockSessionLifecycleEvidence: SkaldVaultV1VaultLockSessionEvidence? = null,
            passphrasePolicyEvidence: SkaldVaultV1VaultPassphrasePolicyEvidence? = null,
            redactionLeakageEvidence: SkaldVaultV1VaultRedactionEvidence? = null,
            clearWipeStrategyEvidence: SkaldVaultV1VaultClearWipeEvidence? = null,
            migrationCorruptionEvidence: SkaldVaultV1VaultMigrationCorruptionEvidence? = null,
            storageSafetyPreflightEvidence: SkaldVaultV1StorageSafetyPreflightEvidence? = null,
            disabledStorageServiceEvidence: SkaldVaultV1VaultStorageDisabledEvidence? = null,
            secureStorageCapability: SecureStorageCapability? = null,
            secureMetadataCapability: SecureMetadataPersistenceCapability? = null,
            operationKind: SkaldVaultV1VaultProviderOperationKind? = null,
            operationPurpose: SkaldVaultV1VaultProviderOperationPurpose? = null,
        ): SkaldVaultV1VaultProviderOperationAuthorizationRequest =
            SkaldVaultV1VaultProviderOperationAuthorizationRequest(
                source = SkaldVaultV1VaultProviderOperationAuthorizationSource.ComposedTypedEvidence,
                operationKind = operationKind,
                operationPurpose = operationPurpose,
                requiredGate = null,
                rawCandidate = null,
                providerSelectionResult = providerSelectionResult,
                providerAcceptanceAssessment = providerAcceptanceAssessment,
                dependencyProbeResult = dependencyProbeResult,
                encryptedVaultReadiness = encryptedVaultReadiness,
                persistenceReadinessEvidence = persistenceReadinessEvidence,
                lockSessionLifecycleEvidence = lockSessionLifecycleEvidence,
                passphrasePolicyEvidence = passphrasePolicyEvidence,
                redactionLeakageEvidence = redactionLeakageEvidence,
                clearWipeStrategyEvidence = clearWipeStrategyEvidence,
                migrationCorruptionEvidence = migrationCorruptionEvidence,
                storageSafetyPreflightEvidence = storageSafetyPreflightEvidence,
                disabledStorageServiceEvidence = disabledStorageServiceEvidence,
                secureStorageCapability = secureStorageCapability,
                secureMetadataCapability = secureMetadataCapability,
            )

        fun rawOperationCandidate(rawCandidate: String?): SkaldVaultV1VaultProviderOperationAuthorizationRequest =
            noEvidence().copyFor(
                source = SkaldVaultV1VaultProviderOperationAuthorizationSource.RawOperationCandidate,
                rawCandidate = rawCandidate,
            )
    }

    private fun copyFor(
        source: SkaldVaultV1VaultProviderOperationAuthorizationSource = this.source,
        operationKind: SkaldVaultV1VaultProviderOperationKind? = this.operationKind,
        operationPurpose: SkaldVaultV1VaultProviderOperationPurpose? = this.operationPurpose,
        requiredGate: SkaldVaultV1VaultProviderOperationRequiredGate? = this.requiredGate,
        rawCandidate: String? = this.rawCandidate,
    ): SkaldVaultV1VaultProviderOperationAuthorizationRequest =
        SkaldVaultV1VaultProviderOperationAuthorizationRequest(
            source = source,
            operationKind = operationKind,
            operationPurpose = operationPurpose,
            requiredGate = requiredGate,
            rawCandidate = rawCandidate,
            providerSelectionResult = providerSelectionResult,
            providerAcceptanceAssessment = providerAcceptanceAssessment,
            dependencyProbeResult = dependencyProbeResult,
            encryptedVaultReadiness = encryptedVaultReadiness,
            persistenceReadinessEvidence = persistenceReadinessEvidence,
            lockSessionLifecycleEvidence = lockSessionLifecycleEvidence,
            passphrasePolicyEvidence = passphrasePolicyEvidence,
            redactionLeakageEvidence = redactionLeakageEvidence,
            clearWipeStrategyEvidence = clearWipeStrategyEvidence,
            migrationCorruptionEvidence = migrationCorruptionEvidence,
            storageSafetyPreflightEvidence = storageSafetyPreflightEvidence,
            disabledStorageServiceEvidence = disabledStorageServiceEvidence,
            secureStorageCapability = secureStorageCapability,
            secureMetadataCapability = secureMetadataCapability,
        )
}

object SkaldVaultV1ProviderOperationAuthorizationPolicy :
    SkaldVaultV1ProviderOperationAuthorizationBoundary {
    const val POLICY_ID = "skald-vault-v1-provider-operation-authorization-boundary-v1"
    const val POLICY_VERSION = 1

    fun currentPolicySummary(): SkaldVaultV1VaultProviderOperationPolicySummary =
        SkaldVaultV1VaultProviderOperationPolicySummary(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            operationKinds = SkaldVaultV1VaultProviderOperationKind.entries.toSet(),
            operationPurposes = SkaldVaultV1VaultProviderOperationPurpose.entries.toSet(),
            requiredGates = SkaldVaultV1VaultProviderOperationRequiredGate.entries.toSet(),
            stillDisabled = true,
        )

    override fun evaluate(
        request: SkaldVaultV1VaultProviderOperationAuthorizationRequest,
    ): SkaldVaultV1VaultProviderOperationAuthorizationResult<
        SkaldVaultV1VaultProviderOperationAuthorizationEvidence,
    > =
        when (request.source) {
            SkaldVaultV1VaultProviderOperationAuthorizationSource.RawOperationCandidate ->
                rejectRawCandidate(request)
            else -> blocked(request)
        }

    private fun blocked(
        request: SkaldVaultV1VaultProviderOperationAuthorizationRequest,
    ): SkaldVaultV1VaultProviderOperationAuthorizationResult.Blocked<
        SkaldVaultV1VaultProviderOperationAuthorizationEvidence,
    > {
        val policyTokenEvidence = SkaldVaultV1VaultProviderOperationPolicyToken(
            policyId = POLICY_ID,
            operationKind = request.operationKind,
            operationPurpose = request.operationPurpose,
            requiredGate = request.requiredGate,
        )

        return SkaldVaultV1VaultProviderOperationAuthorizationResult.Blocked(
            SkaldVaultV1VaultProviderOperationAuthorizationEvidence(
                policyId = POLICY_ID,
                policyVersion = POLICY_VERSION,
                status = statusFor(request),
                decision = decisionFor(request),
                source = request.source,
                operationKind = request.operationKind,
                operationPurpose = request.operationPurpose,
                requiredGate = request.requiredGate,
                requiredGates = requiredGatesFor(request),
                blockers = blockersFor(request),
                warnings = SkaldVaultV1VaultProviderOperationWarning.entries.toSet(),
                capability = SkaldVaultV1VaultProviderOperationCapability.StillDisabled,
                policySummary = currentPolicySummary(),
                policyTokenEvidence = policyTokenEvidence,
                providerSelectionEvidenceConsumed = request.providerSelectionResultOrNull() != null,
                providerAcceptanceEvidenceConsumed = request.providerAcceptanceAssessmentOrNull() != null,
                dependencyProbeEvidenceConsumed = request.dependencyProbeResultOrNull() != null,
                encryptedVaultReadinessEvidenceConsumed = request.encryptedVaultReadinessOrNull() != null,
                persistenceReadinessEvidenceConsumed = request.persistenceReadinessEvidenceOrNull() != null,
                lockSessionLifecycleEvidenceConsumed = request.lockSessionLifecycleEvidenceOrNull() != null,
                passphrasePolicyEvidenceConsumed = request.passphrasePolicyEvidenceOrNull() != null,
                redactionLeakageEvidenceConsumed = request.redactionLeakageEvidenceOrNull() != null,
                clearWipeStrategyEvidenceConsumed = request.clearWipeStrategyEvidenceOrNull() != null,
                migrationCorruptionEvidenceConsumed = request.migrationCorruptionEvidenceOrNull() != null,
                storageSafetyPreflightEvidenceConsumed = request.storageSafetyPreflightEvidenceOrNull() != null,
                disabledStorageServiceEvidenceConsumed = request.disabledStorageServiceEvidenceOrNull() != null,
                secureStorageEvidenceConsumed = request.secureStorageCapabilityOrNull() != null,
                secureMetadataEvidenceConsumed = request.secureMetadataCapabilityOrNull() != null,
            ),
        )
    }

    private fun rejectRawCandidate(
        request: SkaldVaultV1VaultProviderOperationAuthorizationRequest,
    ): SkaldVaultV1VaultProviderOperationAuthorizationResult.Rejected =
        SkaldVaultV1VaultProviderOperationAuthorizationResult.Rejected(
            reason = classifyRawCandidate(request.rawCandidateOrNull()),
            status = SkaldVaultV1VaultProviderOperationAuthorizationStatus.RawCandidateRejected,
            source = SkaldVaultV1VaultProviderOperationAuthorizationSource.RawOperationCandidate,
            safeMessage = "Raw provider operation material is not accepted by this model-only boundary.",
        )

    private fun statusFor(
        request: SkaldVaultV1VaultProviderOperationAuthorizationRequest,
    ): SkaldVaultV1VaultProviderOperationAuthorizationStatus =
        when (request.source) {
            SkaldVaultV1VaultProviderOperationAuthorizationSource.NoEvidence ->
                SkaldVaultV1VaultProviderOperationAuthorizationStatus.NoEvidenceAvailable
            SkaldVaultV1VaultProviderOperationAuthorizationSource.PolicySummary ->
                SkaldVaultV1VaultProviderOperationAuthorizationStatus.PolicySummaryModeled
            SkaldVaultV1VaultProviderOperationAuthorizationSource.OperationKind ->
                SkaldVaultV1VaultProviderOperationAuthorizationStatus.OperationKindModeled
            SkaldVaultV1VaultProviderOperationAuthorizationSource.OperationPurpose ->
                SkaldVaultV1VaultProviderOperationAuthorizationStatus.OperationPurposeModeled
            SkaldVaultV1VaultProviderOperationAuthorizationSource.RequiredGate ->
                SkaldVaultV1VaultProviderOperationAuthorizationStatus.RequiredGateModeled
            SkaldVaultV1VaultProviderOperationAuthorizationSource.ComposedTypedEvidence ->
                SkaldVaultV1VaultProviderOperationAuthorizationStatus.ProviderOperationBlockedStillDisabled
            SkaldVaultV1VaultProviderOperationAuthorizationSource.RawOperationCandidate ->
                SkaldVaultV1VaultProviderOperationAuthorizationStatus.RawCandidateRejected
        }

    private fun decisionFor(
        request: SkaldVaultV1VaultProviderOperationAuthorizationRequest,
    ): SkaldVaultV1VaultProviderOperationAuthorizationDecision =
        when {
            request.operationKind == SkaldVaultV1VaultProviderOperationKind.MainnetOperation ||
                request.operationPurpose == SkaldVaultV1VaultProviderOperationPurpose.MainnetValidation ->
                SkaldVaultV1VaultProviderOperationAuthorizationDecision.RejectOperation
            request.operationPurpose == SkaldVaultV1VaultProviderOperationPurpose.TestOnlyDeterministicVector ->
                SkaldVaultV1VaultProviderOperationAuthorizationDecision.TestOnlyScopeRejectedForProduction
            request.source == SkaldVaultV1VaultProviderOperationAuthorizationSource.NoEvidence ||
                request.source == SkaldVaultV1VaultProviderOperationAuthorizationSource.ComposedTypedEvidence ->
                SkaldVaultV1VaultProviderOperationAuthorizationDecision.BlockedFailClosed
            else -> SkaldVaultV1VaultProviderOperationAuthorizationDecision.Unauthorized
        }

    private fun requiredGatesFor(
        request: SkaldVaultV1VaultProviderOperationAuthorizationRequest,
    ): Set<SkaldVaultV1VaultProviderOperationRequiredGate> =
        buildSet {
            add(SkaldVaultV1VaultProviderOperationRequiredGate.ProviderIsNotDisabled)
            add(SkaldVaultV1VaultProviderOperationRequiredGate.ProviderSelectedThroughRegistry)
            add(SkaldVaultV1VaultProviderOperationRequiredGate.ProductionProviderSelectableTrue)
            add(SkaldVaultV1VaultProviderOperationRequiredGate.ProductionProviderImplementationExists)
            add(SkaldVaultV1VaultProviderOperationRequiredGate.ProviderKatsApproved)
            add(SkaldVaultV1VaultProviderOperationRequiredGate.DependencyProbeApproved)
            add(SkaldVaultV1VaultProviderOperationRequiredGate.RedactionLeakagePolicyApproved)
            add(SkaldVaultV1VaultProviderOperationRequiredGate.ClearWipeStrategyApproved)
            add(SkaldVaultV1VaultProviderOperationRequiredGate.NoRawSecretDiagnosticExposure)
            add(SkaldVaultV1VaultProviderOperationRequiredGate.NoProviderOperationInUiOrDomainPolicyDirectly)
            add(SkaldVaultV1VaultProviderOperationRequiredGate.NoBdkPersistenceBypass)
            add(SkaldVaultV1VaultProviderOperationRequiredGate.OperationAllowedForNetworkMode)
            when (request.operationKind) {
                SkaldVaultV1VaultProviderOperationKind.RuntimeRandomnessCheck,
                SkaldVaultV1VaultProviderOperationKind.EntropyNonceGeneration,
                SkaldVaultV1VaultProviderOperationKind.SaltGeneration,
                SkaldVaultV1VaultProviderOperationKind.KeyGeneration,
                -> {
                    add(SkaldVaultV1VaultProviderOperationRequiredGate.RuntimeRandomnessCheckApproved)
                    add(SkaldVaultV1VaultProviderOperationRequiredGate.EntropyPolicyApproved)
                }
                SkaldVaultV1VaultProviderOperationKind.Argon2idKdfExecution,
                SkaldVaultV1VaultProviderOperationKind.HkdfExtractionExpansion,
                SkaldVaultV1VaultProviderOperationKind.HmacComputation,
                SkaldVaultV1VaultProviderOperationKind.HeaderCommitmentComputation,
                SkaldVaultV1VaultProviderOperationKind.HeaderCommitmentVerification,
                -> {
                    add(SkaldVaultV1VaultProviderOperationRequiredGate.FinalKdfCalibrationApproved)
                    add(SkaldVaultV1VaultProviderOperationRequiredGate.Argon2idParameterPolicyApproved)
                }
                SkaldVaultV1VaultProviderOperationKind.VaultUnlock -> {
                    add(SkaldVaultV1VaultProviderOperationRequiredGate.PassphrasePolicyApproved)
                    add(SkaldVaultV1VaultProviderOperationRequiredGate.LockSessionLifecycleApproved)
                    add(SkaldVaultV1VaultProviderOperationRequiredGate.FinalKdfCalibrationApproved)
                    add(SkaldVaultV1VaultProviderOperationRequiredGate.Argon2idParameterPolicyApproved)
                }
                SkaldVaultV1VaultProviderOperationKind.VaultPersistence,
                SkaldVaultV1VaultProviderOperationKind.RecordEncrypt,
                SkaldVaultV1VaultProviderOperationKind.RecordDecrypt,
                SkaldVaultV1VaultProviderOperationKind.ManifestAuthentication,
                SkaldVaultV1VaultProviderOperationKind.StorageIndexAuthentication,
                -> {
                    add(SkaldVaultV1VaultProviderOperationRequiredGate.PersistenceReadinessApprovedForStorage)
                    add(SkaldVaultV1VaultProviderOperationRequiredGate.StorageSafetyApprovedForStorage)
                    add(SkaldVaultV1VaultProviderOperationRequiredGate.SecureSecretStorageApproved)
                    add(SkaldVaultV1VaultProviderOperationRequiredGate.SecureMetadataStorageApproved)
                }
                SkaldVaultV1VaultProviderOperationKind.AeadEncrypt,
                SkaldVaultV1VaultProviderOperationKind.AeadDecrypt,
                -> add(SkaldVaultV1VaultProviderOperationRequiredGate.RuntimeRandomnessCheckApproved)
                SkaldVaultV1VaultProviderOperationKind.MainnetOperation ->
                    add(SkaldVaultV1VaultProviderOperationRequiredGate.MainnetReleaseReviewApproved)
                else -> Unit
            }
            when (request.operationPurpose) {
                SkaldVaultV1VaultProviderOperationPurpose.VerifyMigrationCandidate,
                SkaldVaultV1VaultProviderOperationPurpose.VerifyCorruptionEvidence,
                -> add(
                    SkaldVaultV1VaultProviderOperationRequiredGate
                        .MigrationCorruptionPolicyApprovedForParsingMigration,
                )
                SkaldVaultV1VaultProviderOperationPurpose.MainnetValidation ->
                    add(SkaldVaultV1VaultProviderOperationRequiredGate.MainnetReleaseReviewApproved)
                else -> Unit
            }
            request.requiredGate?.let(::add)
        }

    private fun blockersFor(
        request: SkaldVaultV1VaultProviderOperationAuthorizationRequest,
    ): Set<SkaldVaultV1VaultProviderOperationBlocker> =
        buildSet {
            add(SkaldVaultV1VaultProviderOperationBlocker.ProviderOperationAuthorizationStillDisabled)
            add(SkaldVaultV1VaultProviderOperationBlocker.ProviderOperationAuthorizationRuntimeReviewMissing)
            add(SkaldVaultV1VaultProviderOperationBlocker.ProviderSelectionDisabled)
            add(SkaldVaultV1VaultProviderOperationBlocker.DisabledProviderSelected)
            add(SkaldVaultV1VaultProviderOperationBlocker.ProductionProviderSelectableFalse)
            add(SkaldVaultV1VaultProviderOperationBlocker.ProductionProviderImplementationMissing)
            add(SkaldVaultV1VaultProviderOperationBlocker.ProviderKatApprovalMissing)
            add(SkaldVaultV1VaultProviderOperationBlocker.DependencyProbeApprovalMissing)
            add(SkaldVaultV1VaultProviderOperationBlocker.RuntimeRandomnessCheckUnavailable)
            add(SkaldVaultV1VaultProviderOperationBlocker.EntropyPolicyUnavailable)
            add(SkaldVaultV1VaultProviderOperationBlocker.FinalKdfCalibrationMissing)
            add(SkaldVaultV1VaultProviderOperationBlocker.Argon2idParameterPolicyNotFinal)
            add(SkaldVaultV1VaultProviderOperationBlocker.ClearWipeStrategyBlocked)
            add(SkaldVaultV1VaultProviderOperationBlocker.RedactionLeakageUnsafe)
            add(SkaldVaultV1VaultProviderOperationBlocker.RawSecretDiagnosticsRejected)
            add(SkaldVaultV1VaultProviderOperationBlocker.UiDomainProviderOperationRejected)
            add(SkaldVaultV1VaultProviderOperationBlocker.BdkPersistenceBypassRejected)
            add(SkaldVaultV1VaultProviderOperationBlocker.NetworkModeBlocked)
            add(SkaldVaultV1VaultProviderOperationBlocker.MainnetUnavailable)
            add(SkaldVaultV1VaultProviderOperationBlocker.WarningOnlyEvidenceRejected)
            add(SkaldVaultV1VaultProviderOperationBlocker.UserConsentOverrideRejected)
            if (request.providerSelectionResultOrNull() != null) {
                add(SkaldVaultV1VaultProviderOperationBlocker.DisabledProviderSelected)
            }
            if (request.providerAcceptanceAssessmentOrNull() != null) {
                add(SkaldVaultV1VaultProviderOperationBlocker.ProductionProviderSelectableFalse)
            }
            if (request.dependencyProbeResultOrNull() != null) {
                add(SkaldVaultV1VaultProviderOperationBlocker.DependencyProbeApprovalMissing)
            }
            if (request.persistenceReadinessEvidenceOrNull() != null) {
                add(SkaldVaultV1VaultProviderOperationBlocker.PersistenceReadinessBlocked)
            }
            if (request.lockSessionLifecycleEvidenceOrNull() != null) {
                add(SkaldVaultV1VaultProviderOperationBlocker.LockSessionLifecycleBlocked)
            }
            if (request.passphrasePolicyEvidenceOrNull() != null) {
                add(SkaldVaultV1VaultProviderOperationBlocker.PassphrasePolicyBlocked)
            }
            if (request.redactionLeakageEvidenceOrNull() != null) {
                add(SkaldVaultV1VaultProviderOperationBlocker.RedactionLeakageUnsafe)
            }
            if (request.clearWipeStrategyEvidenceOrNull() != null) {
                add(SkaldVaultV1VaultProviderOperationBlocker.ClearWipeStrategyBlocked)
            }
            if (request.migrationCorruptionEvidenceOrNull() != null) {
                add(SkaldVaultV1VaultProviderOperationBlocker.MigrationCorruptionBoundaryDisabled)
            }
            if (request.storageSafetyPreflightEvidenceOrNull() != null) {
                add(SkaldVaultV1VaultProviderOperationBlocker.StorageSafetyPreflightBlocked)
            }
            if (request.disabledStorageServiceEvidenceOrNull() != null) {
                add(SkaldVaultV1VaultProviderOperationBlocker.DisabledStorageServiceFacadeStillDisabled)
            }
            if (request.secureStorageCapabilityOrNull() != null) {
                add(SkaldVaultV1VaultProviderOperationBlocker.SecureSecretStorageUnavailable)
            }
            if (request.secureMetadataCapabilityOrNull() != null) {
                add(SkaldVaultV1VaultProviderOperationBlocker.SecureMetadataStorageUnavailable)
            }
            if (request.operationPurpose == SkaldVaultV1VaultProviderOperationPurpose.TestOnlyDeterministicVector) {
                add(SkaldVaultV1VaultProviderOperationBlocker.TestOnlyVectorScopeRejectedForProduction)
            }
            if (request.operationKind == SkaldVaultV1VaultProviderOperationKind.MainnetOperation ||
                request.operationPurpose == SkaldVaultV1VaultProviderOperationPurpose.MainnetValidation
            ) {
                add(SkaldVaultV1VaultProviderOperationBlocker.MainnetUnavailable)
            }
        }

    private fun classifyRawCandidate(
        rawCandidate: String?,
    ): SkaldVaultV1VaultProviderOperationFailureReason {
        val candidate = rawCandidate ?: return SkaldVaultV1VaultProviderOperationFailureReason.EmptyEvidenceRejected
        if (candidate.isBlank()) {
            return SkaldVaultV1VaultProviderOperationFailureReason.EmptyEvidenceRejected
        }
        val normalized = candidate.lowercase()
        return when {
            normalized.contains("provider-handle") ->
                SkaldVaultV1VaultProviderOperationFailureReason.ProviderHandleRejected
            normalized.contains("provider-implementation") ||
                normalized.contains("crypto-provider-instance") ->
                SkaldVaultV1VaultProviderOperationFailureReason.ProviderImplementationInstanceRejected
            normalized.contains("passphrase") ->
                SkaldVaultV1VaultProviderOperationFailureReason.RawPassphraseRejected
            normalized.contains("kdf-input") ->
                SkaldVaultV1VaultProviderOperationFailureReason.KdfInputRejected
            normalized.contains("kdf-output") ->
                SkaldVaultV1VaultProviderOperationFailureReason.KdfOutputRejected
            normalized.contains("entropy") ||
                normalized.contains("random") ||
                normalized.contains("salt") ||
                normalized.contains("nonce") ->
                SkaldVaultV1VaultProviderOperationFailureReason.EntropyRandomSaltNonceBytesRejected
            normalized.contains("aead-key") ||
                normalized.contains("aead-tag") ||
                normalized.contains("ciphertext") ||
                normalized.contains("plaintext") ->
                SkaldVaultV1VaultProviderOperationFailureReason.AeadKeyTagCiphertextPlaintextRejected
            normalized.contains("record-bytes") ->
                SkaldVaultV1VaultProviderOperationFailureReason.RecordBytesRejected
            normalized.contains("container-bytes") ->
                SkaldVaultV1VaultProviderOperationFailureReason.RawPersistedContainerBytesRejected
            normalized.contains("bytearray") ->
                SkaldVaultV1VaultProviderOperationFailureReason.ByteArrayInputRejected
            normalized.contains("chararray") ->
                SkaldVaultV1VaultProviderOperationFailureReason.CharArrayInputRejected
            normalized.contains("key-material") ||
                normalized.contains("secret-key") ->
                SkaldVaultV1VaultProviderOperationFailureReason.KeyMaterialRejected
            candidate.startsWith("/") ->
                SkaldVaultV1VaultProviderOperationFailureReason.RawAbsoluteLocationInputRejected
            normalized.contains("://") ->
                SkaldVaultV1VaultProviderOperationFailureReason.LinkLikeInputRejected
            normalized.contains("..") ->
                SkaldVaultV1VaultProviderOperationFailureReason.TraversalRejected
            normalized.contains("file-object") ||
                normalized.contains("path-object") ->
                SkaldVaultV1VaultProviderOperationFailureReason.PlatformObjectLikeInputRejected
            normalized.contains("/") ->
                SkaldVaultV1VaultProviderOperationFailureReason.RawRelativeLocationInputRejected
            normalized.contains("secret") ||
                normalized.contains("credential") ->
                SkaldVaultV1VaultProviderOperationFailureReason.SecretMaterialRejected
            looksLikeWalletMaterial(candidate) ->
                SkaldVaultV1VaultProviderOperationFailureReason.WalletMaterialRejected
            looksLikeBitcoinAddress(candidate) ->
                SkaldVaultV1VaultProviderOperationFailureReason.BitcoinAddressLikeEvidenceRejected
            looksLikeHex64(candidate) ->
                SkaldVaultV1VaultProviderOperationFailureReason.TransactionLikeEvidenceRejected
            candidate.any { it !in 'a'..'z' && it !in 'A'..'Z' && it !in '0'..'9' && it !in "-_./:" } ->
                SkaldVaultV1VaultProviderOperationFailureReason.UnsupportedCharactersRejected
            else -> SkaldVaultV1VaultProviderOperationFailureReason.RawProviderOperationInputRejected
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
