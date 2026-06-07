package com.libertasprimordium.skald.security

enum class VaultCryptoProviderImplementationStatus(
    val label: String,
    val canExecuteCrypto: Boolean,
    val productionApproved: Boolean,
) {
    DisabledBoundaryOnly(
        label = "disabled provider boundary only",
        canExecuteCrypto = false,
        productionApproved = false,
    ),
}

enum class VaultCryptoProviderCapability(
    val label: String,
    val enabled: Boolean,
) {
    SkaldOwnedBoundary("Skald-owned provider boundary", enabled = true),
    TypedAlgorithmPolicy("typed algorithm policy", enabled = true),
    TypedAssociatedDataContext("typed associated-data context", enabled = true),
    TypedRecordPurposes("typed record purposes", enabled = true),
    ProviderLevelKatRequirements("provider-level KAT requirements", enabled = true),
    ProviderLevelKatContract("provider-level KAT contract", enabled = true),
    TestOnlyProviderKatHarness("test-only provider KAT harness", enabled = false),
    RedactedDiagnostics("redacted diagnostics", enabled = true),
    KdfExecution("KDF execution", enabled = false),
    AeadEncryption("AEAD encryption", enabled = false),
    AeadDecryption("AEAD decryption", enabled = false),
    KeyGeneration("key generation", enabled = false),
    KeysetStorage("keyset storage", enabled = false),
    VaultContainerReadWrite("vault container read/write", enabled = false),
    ProductionPersistence("production persistence", enabled = false),
    MainnetOperation("mainnet operation", enabled = false),
}

enum class VaultCryptoProviderBlocker(val label: String) {
    ProviderImplementationMissing("provider implementation missing"),
    ProviderDisabledByPolicy("provider disabled by policy"),
    KdfExecutionDisabled("KDF execution disabled"),
    AeadEncryptionDisabled("AEAD encryption disabled"),
    AeadDecryptionDisabled("AEAD decryption disabled"),
    KeyGenerationDisabled("key generation disabled"),
    KeysetStorageDisabled("keyset storage disabled"),
    ProviderLevelKnownAnswerVectorsMissing("provider-level known-answer vectors missing"),
    ProviderLevelKatContractUnsatisfied("provider-level KAT contract unsatisfied"),
    KdfParametersUncalibrated("KDF parameters uncalibrated"),
    ProductionProviderBoundaryUnapproved("production provider boundary unapproved"),
    TinkKeysetStorageUnapproved("Tink keyset storage unapproved"),
    VaultContainerUnavailable("vault container unavailable"),
    SecureSecretStorageUnavailable("secure secret storage unavailable"),
    SecureMetadataStorageUnavailable("secure metadata storage unavailable"),
    ProductionPersistenceDisabled("production persistence disabled"),
    MainnetDisabled("mainnet disabled"),
}

enum class VaultCryptoProviderErrorCode(val label: String) {
    ProviderUnavailable("provider unavailable"),
    OperationDisabled("operation disabled"),
    UnsupportedByDisabledProvider("unsupported by disabled provider"),
    MainnetDisabled("mainnet disabled"),
}

enum class VaultCryptoOperation(val label: String) {
    DeriveKey("derive key"),
    EncryptRecord("encrypt record"),
    DecryptRecord("decrypt record"),
    GenerateKey("generate key"),
    StoreKeyset("store keyset"),
    ProviderKat("provider KAT"),
}

enum class VaultCryptoRecordPurpose(val label: String) {
    SecretPayloadRecord("secret payload record"),
    SensitiveMetadataRecord("sensitive metadata record"),
    BackupExportRecord("backup/export record"),
    PlatformWrappingRecord("platform wrapping record"),
    ProviderKatTestRecord("provider KAT/test record"),
}

enum class VaultCryptoKeyRole(val label: String) {
    VaultKeyEncryptionKey("vault key-encryption key"),
    MetadataEncryptionKey("metadata encryption key"),
    SecretPayloadEncryptionKey("secret payload encryption key"),
    BackupExportEncryptionKey("backup/export encryption key"),
    PlatformWrappingKey("platform wrapping key"),
    ProviderKatKey("provider KAT key"),
}

enum class VaultCryptoMaterialRefKind(val label: String) {
    FutureUnlockMaterial("future unlock material"),
    FuturePlaintextRecord("future plaintext record"),
    FutureCiphertextRecord("future ciphertext record"),
    FutureKeyMaterial("future key material"),
    PublicKatVector("public KAT vector"),
    UnavailablePlaceholder("unavailable placeholder"),
}

class VaultCryptoMaterialRef private constructor(val kind: VaultCryptoMaterialRefKind) {
    override fun toString(): String = "VaultCryptoMaterialRef(REDACTED)"

    companion object {
        fun futureUnlockMaterial(): VaultCryptoMaterialRef =
            VaultCryptoMaterialRef(VaultCryptoMaterialRefKind.FutureUnlockMaterial)

        fun futurePlaintextRecord(): VaultCryptoMaterialRef =
            VaultCryptoMaterialRef(VaultCryptoMaterialRefKind.FuturePlaintextRecord)

        fun futureCiphertextRecord(): VaultCryptoMaterialRef =
            VaultCryptoMaterialRef(VaultCryptoMaterialRefKind.FutureCiphertextRecord)

        fun futureKeyMaterial(): VaultCryptoMaterialRef =
            VaultCryptoMaterialRef(VaultCryptoMaterialRefKind.FutureKeyMaterial)

        fun publicKatVector(): VaultCryptoMaterialRef =
            VaultCryptoMaterialRef(VaultCryptoMaterialRefKind.PublicKatVector)

        fun unavailablePlaceholder(): VaultCryptoMaterialRef =
            VaultCryptoMaterialRef(VaultCryptoMaterialRefKind.UnavailablePlaceholder)
    }
}

enum class VaultCryptoNonceMode(
    val label: String,
    val callerProvidedProductionNonceAllowed: Boolean,
) {
    RandomPerRecordOnly(
        label = "random per-record nonce only",
        callerProvidedProductionNonceAllowed = false,
    ),
    ProviderKatFixedNonceOnly(
        label = "provider KAT fixed nonce only",
        callerProvidedProductionNonceAllowed = false,
    ),
}

data class VaultCryptoAssociatedDataContext(
    val containerVersion: Int,
    val recordPurpose: VaultCryptoRecordPurpose,
    val recordSchemaVersion: Int,
    val keyVersion: Int,
    val requirements: Set<EncryptedVaultAssociatedDataRequirement> =
        EncryptedVaultAssociatedDataRequirement.entries.toSet(),
) {
    val excludesSensitiveWalletMetadata: Boolean
        get() = EncryptedVaultAssociatedDataRequirement.ExcludeSensitiveWalletMetadata in requirements
}

data class VaultCryptoKdfRequest(
    val algorithm: EncryptedVaultKdfAlgorithm,
    val inputMaterialRef: VaultCryptoMaterialRef,
    val outputKeyRole: VaultCryptoKeyRole,
    val context: VaultCryptoAssociatedDataContext,
)

data class VaultCryptoAeadEncryptRequest(
    val algorithm: EncryptedVaultAeadAlgorithm,
    val keyRole: VaultCryptoKeyRole,
    val plaintextRef: VaultCryptoMaterialRef,
    val context: VaultCryptoAssociatedDataContext,
    val noncePolicy: EncryptedVaultNoncePolicy =
        EncryptedVaultNoncePolicy.XChaCha20Random24ByteRecordNonce,
    val nonceMode: VaultCryptoNonceMode = VaultCryptoNonceMode.RandomPerRecordOnly,
)

data class VaultCryptoAeadDecryptRequest(
    val algorithm: EncryptedVaultAeadAlgorithm,
    val keyRole: VaultCryptoKeyRole,
    val ciphertextRef: VaultCryptoMaterialRef,
    val context: VaultCryptoAssociatedDataContext,
    val noncePolicy: EncryptedVaultNoncePolicy =
        EncryptedVaultNoncePolicy.XChaCha20Random24ByteRecordNonce,
)

data class VaultCryptoKeyGenerationRequest(
    val keyRole: VaultCryptoKeyRole,
    val context: VaultCryptoAssociatedDataContext,
)

data class VaultCryptoKeysetStorageRequest(
    val keyRole: VaultCryptoKeyRole,
    val keyMaterialRef: VaultCryptoMaterialRef,
    val context: VaultCryptoAssociatedDataContext,
)

class VaultCryptoDerivedKeyHandle private constructor(val role: VaultCryptoKeyRole) {
    override fun toString(): String = "VaultCryptoDerivedKeyHandle(REDACTED)"

    companion object {
        fun redacted(role: VaultCryptoKeyRole): VaultCryptoDerivedKeyHandle =
            VaultCryptoDerivedKeyHandle(role)
    }
}

class VaultCryptoCiphertextHandle private constructor(val purpose: VaultCryptoRecordPurpose) {
    override fun toString(): String = "VaultCryptoCiphertextHandle(REDACTED)"

    companion object {
        fun redacted(purpose: VaultCryptoRecordPurpose): VaultCryptoCiphertextHandle =
            VaultCryptoCiphertextHandle(purpose)
    }
}

class VaultCryptoPlaintextHandle private constructor(val purpose: VaultCryptoRecordPurpose) {
    override fun toString(): String = "VaultCryptoPlaintextHandle(REDACTED)"

    companion object {
        fun redacted(purpose: VaultCryptoRecordPurpose): VaultCryptoPlaintextHandle =
            VaultCryptoPlaintextHandle(purpose)
    }
}

class VaultCryptoGeneratedKeyHandle private constructor(val role: VaultCryptoKeyRole) {
    override fun toString(): String = "VaultCryptoGeneratedKeyHandle(REDACTED)"

    companion object {
        fun redacted(role: VaultCryptoKeyRole): VaultCryptoGeneratedKeyHandle =
            VaultCryptoGeneratedKeyHandle(role)
    }
}

class VaultCryptoKeysetHandle private constructor(val role: VaultCryptoKeyRole) {
    override fun toString(): String = "VaultCryptoKeysetHandle(REDACTED)"

    companion object {
        fun redacted(role: VaultCryptoKeyRole): VaultCryptoKeysetHandle =
            VaultCryptoKeysetHandle(role)
    }
}

data class VaultCryptoProviderDiagnostic(
    val operation: VaultCryptoOperation,
    val safeCode: String,
    val safeDetail: String,
) {
    override fun toString(): String =
        "VaultCryptoProviderDiagnostic(operation=${operation.name}, safeCode=$safeCode)"
}

data class VaultCryptoProviderError(
    val code: VaultCryptoProviderErrorCode,
    val operation: VaultCryptoOperation,
    val diagnostic: VaultCryptoProviderDiagnostic,
)

sealed interface VaultCryptoProviderResult<out T> {
    data class Success<T>(
        val value: T,
    ) : VaultCryptoProviderResult<T>

    data class Blocked(
        val error: VaultCryptoProviderError,
        val blockers: Set<VaultCryptoProviderBlocker>,
    ) : VaultCryptoProviderResult<Nothing>
}

enum class VaultCryptoKatRequirementStatus(
    val label: String,
    val satisfied: Boolean,
) {
    DependencyLevelPassedProviderLevelMissing(
        label = "dependency-level KAT passed; provider-level KAT missing",
        satisfied = false,
    ),
    ContractRequiredExecutableProviderMissing(
        label = "contract requirement modeled; executable provider missing",
        satisfied = false,
    ),
    NegativeProviderTestRequired(
        label = "negative provider test required",
        satisfied = false,
    ),
    PlatformCoverageRequired(
        label = "provider runtime platform coverage required",
        satisfied = false,
    ),
    RedactionTestRequired(
        label = "provider redaction test required",
        satisfied = false,
    ),
    StorageBoundaryReviewRequired(
        label = "storage boundary review required",
        satisfied = false,
    ),
    NotApplicableToDisabledProvider(
        label = "not applicable to disabled provider",
        satisfied = false,
    ),
}

enum class VaultCryptoProviderKatCategory(val label: String) {
    PositiveKdfVector("positive KDF vector"),
    PositiveAeadVector("positive AEAD vector"),
    NegativeMisuseVector("negative misuse vector"),
    AlgorithmPolicy("algorithm policy"),
    NoncePolicy("nonce policy"),
    Redaction("redaction"),
    PlatformCoverage("platform coverage"),
    StorageSeparation("storage separation"),
}

enum class VaultCryptoProviderKatVectorId(
    val label: String,
    val officialOrReferenceVector: Boolean,
) {
    Argon2idRfc9106Section53(
        label = "Argon2id RFC 9106 section 5.3",
        officialOrReferenceVector = true,
    ),
    XChaCha20Poly1305DraftAppendixA1(
        label = "XChaCha20-Poly1305 draft appendix A.1",
        officialOrReferenceVector = true,
    ),
    AssociatedDataMismatchFails(
        label = "associated-data mismatch fails closed",
        officialOrReferenceVector = false,
    ),
    CiphertextTamperingFails(
        label = "ciphertext tampering fails closed",
        officialOrReferenceVector = false,
    ),
    TagTamperingFails(
        label = "authentication-tag tampering fails closed",
        officialOrReferenceVector = false,
    ),
    WrongKeyFails(
        label = "wrong key fails closed",
        officialOrReferenceVector = false,
    ),
    UnsupportedAlgorithmRejected(
        label = "unsupported algorithm rejected",
        officialOrReferenceVector = false,
    ),
    ProductionNonceBypassRejected(
        label = "production nonce policy bypass rejected",
        officialOrReferenceVector = false,
    ),
    CallerProvidedProductionNonceRejected(
        label = "caller-provided production nonce rejected",
        officialOrReferenceVector = false,
    ),
    Pbkdf2DefaultRejected(
        label = "PBKDF2 default rejected",
        officialOrReferenceVector = false,
    ),
    ScryptFallbackNotSelected(
        label = "scrypt fallback not selected by default",
        officialOrReferenceVector = false,
    ),
    DiagnosticsExcludePlaintext(
        label = "diagnostics exclude plaintext",
        officialOrReferenceVector = false,
    ),
    DiagnosticsExcludeDerivedKeys(
        label = "diagnostics exclude derived keys",
        officialOrReferenceVector = false,
    ),
    DiagnosticsExcludeUnlockMaterial(
        label = "diagnostics exclude unlock material",
        officialOrReferenceVector = false,
    ),
    DiagnosticsExcludeSecretKeys(
        label = "diagnostics exclude secret keys",
        officialOrReferenceVector = false,
    ),
    DiagnosticsExcludeDecryptedPayloads(
        label = "diagnostics exclude decrypted payloads",
        officialOrReferenceVector = false,
    ),
    DesktopProviderRuntimeCoverage(
        label = "desktop provider runtime coverage",
        officialOrReferenceVector = false,
    ),
    AndroidProviderRuntimeCoverage(
        label = "Android provider runtime coverage",
        officialOrReferenceVector = false,
    ),
    ReleaseLikeProviderRuntimeCoverage(
        label = "release-like provider runtime coverage",
        officialOrReferenceVector = false,
    ),
    VaultStorageApprovalSeparated(
        label = "vault storage approval remains separate",
        officialOrReferenceVector = false,
    ),
}

enum class VaultCryptoProviderKatContractStatus(
    val label: String,
    val providerLevelKatsPassed: Boolean,
    val executableProviderRequired: Boolean,
) {
    ContractModeledProviderMissing(
        label = "provider KAT contract modeled; executable provider missing",
        providerLevelKatsPassed = false,
        executableProviderRequired = true,
    ),
}

enum class VaultCryptoProviderKatBlocker(val label: String) {
    ExecutableProviderMissing("executable provider missing"),
    DependencyLevelKatsDoNotSatisfyProviderContract("dependency-level KATs do not satisfy provider contract"),
    PositiveKdfProviderKatMissing("positive KDF provider KAT missing"),
    PositiveAeadProviderKatMissing("positive AEAD provider KAT missing"),
    NegativeMisuseProviderKatsMissing("negative misuse provider KATs missing"),
    UnsupportedAlgorithmRejectionUntested("unsupported algorithm rejection untested"),
    NoncePolicyBypassUntested("nonce policy bypass untested"),
    DesktopProviderRuntimeKatMissing("desktop provider runtime KAT missing"),
    AndroidProviderRuntimeKatMissing("Android provider runtime KAT missing"),
    ReleaseLikeProviderRuntimeKatMissing("release-like provider runtime KAT missing"),
    ProviderRedactionTestsMissing("provider redaction tests missing"),
    VaultStorageApprovalSeparate("vault storage approval is separate"),
}

data class VaultCryptoProviderKatRequirement(
    val vectorId: VaultCryptoProviderKatVectorId,
    val category: VaultCryptoProviderKatCategory,
    val operation: VaultCryptoOperation,
    val algorithmLabel: String,
    val vectorSource: String,
    val positiveTest: Boolean,
    val requiresDesktopRuntime: Boolean,
    val requiresAndroidRuntime: Boolean,
    val requiresReleaseLikeRuntime: Boolean = false,
    val testOnlyCallerNonceAllowed: Boolean = false,
    val productionCallerNonceAllowed: Boolean = false,
    val requiredForProviderApproval: Boolean = true,
    val status: VaultCryptoKatRequirementStatus,
)

enum class VaultCryptoProviderKatExecutionScope(
    val label: String,
    val productionProvider: Boolean,
) {
    TestHarnessOnly("test harness only", productionProvider = false),
    FutureProductionProvider("future production provider", productionProvider = true),
}

data class VaultCryptoProviderKatRequest(
    val requirement: VaultCryptoProviderKatRequirement,
    val context: VaultCryptoAssociatedDataContext,
) {
    val vectorId: VaultCryptoProviderKatVectorId
        get() = requirement.vectorId

    val category: VaultCryptoProviderKatCategory
        get() = requirement.category
}

class VaultCryptoProviderKatEvidence private constructor(
    val vectorId: VaultCryptoProviderKatVectorId,
    val category: VaultCryptoProviderKatCategory,
    val executionScope: VaultCryptoProviderKatExecutionScope,
) {
    override fun toString(): String = "VaultCryptoProviderKatEvidence(REDACTED)"

    companion object {
        fun redacted(
            vectorId: VaultCryptoProviderKatVectorId,
            category: VaultCryptoProviderKatCategory,
            executionScope: VaultCryptoProviderKatExecutionScope,
        ): VaultCryptoProviderKatEvidence =
            VaultCryptoProviderKatEvidence(
                vectorId = vectorId,
                category = category,
                executionScope = executionScope,
            )
    }
}

data class VaultCryptoProviderKatValidationResult(
    val status: VaultCryptoProviderKatContractStatus,
    val providerLevelKatsPassed: Boolean,
    val dependencyLevelEvidenceOnly: Boolean,
    val blockers: Set<VaultCryptoProviderKatBlocker>,
    val diagnostics: List<VaultCryptoProviderDiagnostic>,
) {
    val canApproveProductionProvider: Boolean
        get() = providerLevelKatsPassed && blockers.isEmpty()
}

data class VaultCryptoProviderKatContract(
    val status: VaultCryptoProviderKatContractStatus,
    val requirements: List<VaultCryptoProviderKatRequirement>,
    val blockers: Set<VaultCryptoProviderKatBlocker>,
    val diagnostics: List<VaultCryptoProviderDiagnostic>,
) {
    val dependencyLevelKatsSatisfyProviderContract: Boolean = false

    val providerLevelKatsPassed: Boolean
        get() = status.providerLevelKatsPassed &&
            requirements.all { it.status.satisfied } &&
            blockers.isEmpty()

    val validationResult: VaultCryptoProviderKatValidationResult
        get() = VaultCryptoProviderKatValidationResult(
            status = status,
            providerLevelKatsPassed = providerLevelKatsPassed,
            dependencyLevelEvidenceOnly = true,
            blockers = blockers,
            diagnostics = diagnostics,
        )
}

data class VaultCryptoProviderStatusReport(
    val implementationStatus: VaultCryptoProviderImplementationStatus,
    val algorithmPolicy: EncryptedVaultAlgorithmPolicy,
    val capabilities: Set<VaultCryptoProviderCapability>,
    val blockers: Set<VaultCryptoProviderBlocker>,
    val katContract: VaultCryptoProviderKatContract,
    val katRequirements: List<VaultCryptoProviderKatRequirement>,
    val diagnostics: List<VaultCryptoProviderDiagnostic>,
    val implementationNote: String,
) {
    val canDeriveKeys: Boolean
        get() = VaultCryptoProviderCapability.KdfExecution.enabledCapability()

    val canEncryptRecords: Boolean
        get() = VaultCryptoProviderCapability.AeadEncryption.enabledCapability()

    val canDecryptRecords: Boolean
        get() = VaultCryptoProviderCapability.AeadDecryption.enabledCapability()

    val canGenerateKeys: Boolean
        get() = VaultCryptoProviderCapability.KeyGeneration.enabledCapability()

    val canStoreKeysets: Boolean
        get() = VaultCryptoProviderCapability.KeysetStorage.enabledCapability()

    val productionPersistenceEnabled: Boolean
        get() = VaultCryptoProviderCapability.ProductionPersistence.enabledCapability()

    val mainnetEnabled: Boolean
        get() = VaultCryptoProviderCapability.MainnetOperation.enabledCapability()

    private fun VaultCryptoProviderCapability.enabledCapability(): Boolean =
        this in capabilities && enabled
}

interface VaultCryptoProvider {
    val statusReport: VaultCryptoProviderStatusReport

    fun deriveKey(request: VaultCryptoKdfRequest): VaultCryptoProviderResult<VaultCryptoDerivedKeyHandle>

    fun encryptRecord(request: VaultCryptoAeadEncryptRequest): VaultCryptoProviderResult<VaultCryptoCiphertextHandle>

    fun decryptRecord(request: VaultCryptoAeadDecryptRequest): VaultCryptoProviderResult<VaultCryptoPlaintextHandle>

    fun generateKey(request: VaultCryptoKeyGenerationRequest): VaultCryptoProviderResult<VaultCryptoGeneratedKeyHandle>

    fun storeKeyset(request: VaultCryptoKeysetStorageRequest): VaultCryptoProviderResult<VaultCryptoKeysetHandle>

    fun validateKat(
        request: VaultCryptoProviderKatRequest,
    ): VaultCryptoProviderResult<VaultCryptoProviderKatEvidence>
}

class DisabledVaultCryptoProvider(
    override val statusReport: VaultCryptoProviderStatusReport = commonDisabledVaultCryptoProviderStatus(),
) : VaultCryptoProvider {
    override fun deriveKey(request: VaultCryptoKdfRequest): VaultCryptoProviderResult<VaultCryptoDerivedKeyHandle> =
        blocked(VaultCryptoOperation.DeriveKey, VaultCryptoProviderBlocker.KdfExecutionDisabled)

    override fun encryptRecord(
        request: VaultCryptoAeadEncryptRequest,
    ): VaultCryptoProviderResult<VaultCryptoCiphertextHandle> =
        blocked(VaultCryptoOperation.EncryptRecord, VaultCryptoProviderBlocker.AeadEncryptionDisabled)

    override fun decryptRecord(
        request: VaultCryptoAeadDecryptRequest,
    ): VaultCryptoProviderResult<VaultCryptoPlaintextHandle> =
        blocked(VaultCryptoOperation.DecryptRecord, VaultCryptoProviderBlocker.AeadDecryptionDisabled)

    override fun generateKey(
        request: VaultCryptoKeyGenerationRequest,
    ): VaultCryptoProviderResult<VaultCryptoGeneratedKeyHandle> =
        blocked(VaultCryptoOperation.GenerateKey, VaultCryptoProviderBlocker.KeyGenerationDisabled)

    override fun storeKeyset(
        request: VaultCryptoKeysetStorageRequest,
    ): VaultCryptoProviderResult<VaultCryptoKeysetHandle> =
        blocked(VaultCryptoOperation.StoreKeyset, VaultCryptoProviderBlocker.KeysetStorageDisabled)

    override fun validateKat(
        request: VaultCryptoProviderKatRequest,
    ): VaultCryptoProviderResult<VaultCryptoProviderKatEvidence> =
        blocked(VaultCryptoOperation.ProviderKat, VaultCryptoProviderBlocker.ProviderLevelKnownAnswerVectorsMissing)

    private fun blocked(
        operation: VaultCryptoOperation,
        operationBlocker: VaultCryptoProviderBlocker,
    ): VaultCryptoProviderResult.Blocked {
        val blockers = statusReport.blockers + operationBlocker
        return VaultCryptoProviderResult.Blocked(
            error = VaultCryptoProviderError(
                code = VaultCryptoProviderErrorCode.OperationDisabled,
                operation = operation,
                diagnostic = VaultCryptoProviderDiagnostic(
                    operation = operation,
                    safeCode = "VAULT_CRYPTO_PROVIDER_DISABLED",
                    safeDetail = "Disabled provider boundary only; no crypto operation was performed.",
                ),
            ),
            blockers = blockers,
        )
    }
}

fun commonDisabledVaultCryptoProviderStatus(): VaultCryptoProviderStatusReport =
    commonProviderKatContract().let { katContract ->
        VaultCryptoProviderStatusReport(
            implementationStatus = VaultCryptoProviderImplementationStatus.DisabledBoundaryOnly,
            algorithmPolicy = EncryptedVaultAlgorithmPolicy.currentDesign(),
            capabilities = setOf(
                VaultCryptoProviderCapability.SkaldOwnedBoundary,
                VaultCryptoProviderCapability.TypedAlgorithmPolicy,
                VaultCryptoProviderCapability.TypedAssociatedDataContext,
                VaultCryptoProviderCapability.TypedRecordPurposes,
                VaultCryptoProviderCapability.ProviderLevelKatRequirements,
                VaultCryptoProviderCapability.ProviderLevelKatContract,
                VaultCryptoProviderCapability.RedactedDiagnostics,
            ),
            blockers = setOf(
                VaultCryptoProviderBlocker.ProviderImplementationMissing,
                VaultCryptoProviderBlocker.ProviderDisabledByPolicy,
                VaultCryptoProviderBlocker.ProviderLevelKnownAnswerVectorsMissing,
                VaultCryptoProviderBlocker.ProviderLevelKatContractUnsatisfied,
                VaultCryptoProviderBlocker.KdfParametersUncalibrated,
                VaultCryptoProviderBlocker.ProductionProviderBoundaryUnapproved,
                VaultCryptoProviderBlocker.TinkKeysetStorageUnapproved,
                VaultCryptoProviderBlocker.VaultContainerUnavailable,
                VaultCryptoProviderBlocker.SecureSecretStorageUnavailable,
                VaultCryptoProviderBlocker.SecureMetadataStorageUnavailable,
                VaultCryptoProviderBlocker.ProductionPersistenceDisabled,
                VaultCryptoProviderBlocker.MainnetDisabled,
            ),
            katContract = katContract,
            katRequirements = katContract.requirements,
            diagnostics = listOf(
                VaultCryptoProviderDiagnostic(
                    operation = VaultCryptoOperation.ProviderKat,
                    safeCode = "PROVIDER_LEVEL_KAT_NOT_IMPLEMENTED",
                    safeDetail = "Dependency-level KATs passed, but no production provider-level KAT path exists.",
                ),
            ),
            implementationNote = "Disabled provider boundary only. It models reviewed algorithms, typed record purposes, provider-level KAT contract requirements, and redacted errors, but it does not derive keys, encrypt, decrypt, generate keys, store keysets, run production provider KATs, write containers, persist data, or enable mainnet.",
        )
    }

fun commonProviderKatContract(): VaultCryptoProviderKatContract =
    VaultCryptoProviderKatContract(
        status = VaultCryptoProviderKatContractStatus.ContractModeledProviderMissing,
        requirements = providerKatRequirements(),
        blockers = VaultCryptoProviderKatBlocker.entries.toSet(),
        diagnostics = listOf(
            VaultCryptoProviderDiagnostic(
                operation = VaultCryptoOperation.ProviderKat,
                safeCode = "PROVIDER_KAT_CONTRACT_MODELED_ONLY",
                safeDetail = "Provider-level KATs are required but cannot execute until a future provider exists.",
            ),
        ),
    )

private fun providerKatRequirements(): List<VaultCryptoProviderKatRequirement> =
    listOf(
        VaultCryptoProviderKatRequirement(
            vectorId = VaultCryptoProviderKatVectorId.Argon2idRfc9106Section53,
            category = VaultCryptoProviderKatCategory.PositiveKdfVector,
            operation = VaultCryptoOperation.DeriveKey,
            algorithmLabel = EncryptedVaultKdfAlgorithm.Argon2id.label,
            vectorSource = "RFC 9106 section 5.3 Argon2id public vector",
            positiveTest = true,
            requiresDesktopRuntime = true,
            requiresAndroidRuntime = true,
            status = VaultCryptoKatRequirementStatus.DependencyLevelPassedProviderLevelMissing,
        ),
        VaultCryptoProviderKatRequirement(
            vectorId = VaultCryptoProviderKatVectorId.XChaCha20Poly1305DraftAppendixA1,
            category = VaultCryptoProviderKatCategory.PositiveAeadVector,
            operation = VaultCryptoOperation.EncryptRecord,
            algorithmLabel = EncryptedVaultAeadAlgorithm.XChaCha20Poly1305.label,
            vectorSource = "draft-irtf-cfrg-xchacha-02 appendix A.1 public vector",
            positiveTest = true,
            requiresDesktopRuntime = true,
            requiresAndroidRuntime = true,
            testOnlyCallerNonceAllowed = true,
            status = VaultCryptoKatRequirementStatus.DependencyLevelPassedProviderLevelMissing,
        ),
        negativeKat(
            vectorId = VaultCryptoProviderKatVectorId.AssociatedDataMismatchFails,
            algorithmLabel = EncryptedVaultAeadAlgorithm.XChaCha20Poly1305.label,
            vectorSource = "Skald provider-level negative AEAD contract vector",
            operation = VaultCryptoOperation.DecryptRecord,
        ),
        negativeKat(
            vectorId = VaultCryptoProviderKatVectorId.CiphertextTamperingFails,
            algorithmLabel = EncryptedVaultAeadAlgorithm.XChaCha20Poly1305.label,
            vectorSource = "Skald provider-level ciphertext tamper contract vector",
            operation = VaultCryptoOperation.DecryptRecord,
        ),
        negativeKat(
            vectorId = VaultCryptoProviderKatVectorId.TagTamperingFails,
            algorithmLabel = EncryptedVaultAeadAlgorithm.XChaCha20Poly1305.label,
            vectorSource = "Skald provider-level tag tamper contract vector",
            operation = VaultCryptoOperation.DecryptRecord,
        ),
        negativeKat(
            vectorId = VaultCryptoProviderKatVectorId.WrongKeyFails,
            algorithmLabel = EncryptedVaultAeadAlgorithm.XChaCha20Poly1305.label,
            vectorSource = "Skald provider-level wrong-key contract vector",
            operation = VaultCryptoOperation.DecryptRecord,
        ),
        policyKat(
            vectorId = VaultCryptoProviderKatVectorId.UnsupportedAlgorithmRejected,
            category = VaultCryptoProviderKatCategory.AlgorithmPolicy,
            operation = VaultCryptoOperation.ProviderKat,
            algorithmLabel = "unsupported algorithm",
            status = VaultCryptoKatRequirementStatus.NegativeProviderTestRequired,
        ),
        policyKat(
            vectorId = VaultCryptoProviderKatVectorId.ProductionNonceBypassRejected,
            category = VaultCryptoProviderKatCategory.NoncePolicy,
            operation = VaultCryptoOperation.EncryptRecord,
            algorithmLabel = EncryptedVaultAeadAlgorithm.XChaCha20Poly1305.label,
            status = VaultCryptoKatRequirementStatus.NegativeProviderTestRequired,
        ),
        policyKat(
            vectorId = VaultCryptoProviderKatVectorId.CallerProvidedProductionNonceRejected,
            category = VaultCryptoProviderKatCategory.NoncePolicy,
            operation = VaultCryptoOperation.EncryptRecord,
            algorithmLabel = EncryptedVaultAeadAlgorithm.XChaCha20Poly1305.label,
            status = VaultCryptoKatRequirementStatus.NegativeProviderTestRequired,
        ),
        policyKat(
            vectorId = VaultCryptoProviderKatVectorId.Pbkdf2DefaultRejected,
            category = VaultCryptoProviderKatCategory.AlgorithmPolicy,
            operation = VaultCryptoOperation.DeriveKey,
            algorithmLabel = EncryptedVaultKdfAlgorithm.Pbkdf2.label,
            status = VaultCryptoKatRequirementStatus.NegativeProviderTestRequired,
        ),
        policyKat(
            vectorId = VaultCryptoProviderKatVectorId.ScryptFallbackNotSelected,
            category = VaultCryptoProviderKatCategory.AlgorithmPolicy,
            operation = VaultCryptoOperation.DeriveKey,
            algorithmLabel = EncryptedVaultKdfAlgorithm.Scrypt.label,
            status = VaultCryptoKatRequirementStatus.NegativeProviderTestRequired,
        ),
        redactionKat(VaultCryptoProviderKatVectorId.DiagnosticsExcludePlaintext),
        redactionKat(VaultCryptoProviderKatVectorId.DiagnosticsExcludeDerivedKeys),
        redactionKat(VaultCryptoProviderKatVectorId.DiagnosticsExcludeUnlockMaterial),
        redactionKat(VaultCryptoProviderKatVectorId.DiagnosticsExcludeSecretKeys),
        redactionKat(VaultCryptoProviderKatVectorId.DiagnosticsExcludeDecryptedPayloads),
        platformKat(
            vectorId = VaultCryptoProviderKatVectorId.DesktopProviderRuntimeCoverage,
            requiresDesktopRuntime = true,
            requiresAndroidRuntime = false,
        ),
        platformKat(
            vectorId = VaultCryptoProviderKatVectorId.AndroidProviderRuntimeCoverage,
            requiresDesktopRuntime = false,
            requiresAndroidRuntime = true,
        ),
        platformKat(
            vectorId = VaultCryptoProviderKatVectorId.ReleaseLikeProviderRuntimeCoverage,
            requiresDesktopRuntime = true,
            requiresAndroidRuntime = true,
            requiresReleaseLikeRuntime = true,
        ),
        VaultCryptoProviderKatRequirement(
            vectorId = VaultCryptoProviderKatVectorId.VaultStorageApprovalSeparated,
            category = VaultCryptoProviderKatCategory.StorageSeparation,
            operation = VaultCryptoOperation.ProviderKat,
            algorithmLabel = "storage boundary",
            vectorSource = "Skald provider-level storage-separation contract",
            positiveTest = false,
            requiresDesktopRuntime = false,
            requiresAndroidRuntime = false,
            status = VaultCryptoKatRequirementStatus.StorageBoundaryReviewRequired,
        ),
    )

private fun negativeKat(
    vectorId: VaultCryptoProviderKatVectorId,
    algorithmLabel: String,
    vectorSource: String,
    operation: VaultCryptoOperation,
): VaultCryptoProviderKatRequirement =
    VaultCryptoProviderKatRequirement(
        vectorId = vectorId,
        category = VaultCryptoProviderKatCategory.NegativeMisuseVector,
        operation = operation,
        algorithmLabel = algorithmLabel,
        vectorSource = vectorSource,
        positiveTest = false,
        requiresDesktopRuntime = true,
        requiresAndroidRuntime = true,
        status = VaultCryptoKatRequirementStatus.NegativeProviderTestRequired,
    )

private fun policyKat(
    vectorId: VaultCryptoProviderKatVectorId,
    category: VaultCryptoProviderKatCategory,
    operation: VaultCryptoOperation,
    algorithmLabel: String,
    status: VaultCryptoKatRequirementStatus,
): VaultCryptoProviderKatRequirement =
    VaultCryptoProviderKatRequirement(
        vectorId = vectorId,
        category = category,
        operation = operation,
        algorithmLabel = algorithmLabel,
        vectorSource = "Skald provider-level policy contract",
        positiveTest = false,
        requiresDesktopRuntime = true,
        requiresAndroidRuntime = true,
        status = status,
    )

private fun redactionKat(vectorId: VaultCryptoProviderKatVectorId): VaultCryptoProviderKatRequirement =
    VaultCryptoProviderKatRequirement(
        vectorId = vectorId,
        category = VaultCryptoProviderKatCategory.Redaction,
        operation = VaultCryptoOperation.ProviderKat,
        algorithmLabel = "redacted diagnostics",
        vectorSource = "Skald provider-level redaction contract",
        positiveTest = false,
        requiresDesktopRuntime = true,
        requiresAndroidRuntime = true,
        status = VaultCryptoKatRequirementStatus.RedactionTestRequired,
    )

private fun platformKat(
    vectorId: VaultCryptoProviderKatVectorId,
    requiresDesktopRuntime: Boolean,
    requiresAndroidRuntime: Boolean,
    requiresReleaseLikeRuntime: Boolean = false,
): VaultCryptoProviderKatRequirement =
    VaultCryptoProviderKatRequirement(
        vectorId = vectorId,
        category = VaultCryptoProviderKatCategory.PlatformCoverage,
        operation = VaultCryptoOperation.ProviderKat,
        algorithmLabel = "provider runtime coverage",
        vectorSource = "Skald provider-level runtime coverage contract",
        positiveTest = false,
        requiresDesktopRuntime = requiresDesktopRuntime,
        requiresAndroidRuntime = requiresAndroidRuntime,
        requiresReleaseLikeRuntime = requiresReleaseLikeRuntime,
        status = VaultCryptoKatRequirementStatus.PlatformCoverageRequired,
    )
