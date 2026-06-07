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
    NotApplicableToDisabledProvider(
        label = "not applicable to disabled provider",
        satisfied = false,
    ),
}

data class VaultCryptoProviderKatRequirement(
    val operation: VaultCryptoOperation,
    val algorithmLabel: String,
    val vectorSource: String,
    val requiresDesktopRuntime: Boolean,
    val requiresAndroidRuntime: Boolean,
    val status: VaultCryptoKatRequirementStatus,
)

data class VaultCryptoProviderStatusReport(
    val implementationStatus: VaultCryptoProviderImplementationStatus,
    val algorithmPolicy: EncryptedVaultAlgorithmPolicy,
    val capabilities: Set<VaultCryptoProviderCapability>,
    val blockers: Set<VaultCryptoProviderBlocker>,
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
    VaultCryptoProviderStatusReport(
        implementationStatus = VaultCryptoProviderImplementationStatus.DisabledBoundaryOnly,
        algorithmPolicy = EncryptedVaultAlgorithmPolicy.currentDesign(),
        capabilities = setOf(
            VaultCryptoProviderCapability.SkaldOwnedBoundary,
            VaultCryptoProviderCapability.TypedAlgorithmPolicy,
            VaultCryptoProviderCapability.TypedAssociatedDataContext,
            VaultCryptoProviderCapability.TypedRecordPurposes,
            VaultCryptoProviderCapability.ProviderLevelKatRequirements,
            VaultCryptoProviderCapability.RedactedDiagnostics,
        ),
        blockers = setOf(
            VaultCryptoProviderBlocker.ProviderImplementationMissing,
            VaultCryptoProviderBlocker.ProviderDisabledByPolicy,
            VaultCryptoProviderBlocker.ProviderLevelKnownAnswerVectorsMissing,
            VaultCryptoProviderBlocker.KdfParametersUncalibrated,
            VaultCryptoProviderBlocker.ProductionProviderBoundaryUnapproved,
            VaultCryptoProviderBlocker.TinkKeysetStorageUnapproved,
            VaultCryptoProviderBlocker.VaultContainerUnavailable,
            VaultCryptoProviderBlocker.SecureSecretStorageUnavailable,
            VaultCryptoProviderBlocker.SecureMetadataStorageUnavailable,
            VaultCryptoProviderBlocker.ProductionPersistenceDisabled,
            VaultCryptoProviderBlocker.MainnetDisabled,
        ),
        katRequirements = listOf(
            VaultCryptoProviderKatRequirement(
                operation = VaultCryptoOperation.DeriveKey,
                algorithmLabel = EncryptedVaultKdfAlgorithm.Argon2id.label,
                vectorSource = "RFC 9106 section 5.3 Argon2id public vector",
                requiresDesktopRuntime = true,
                requiresAndroidRuntime = true,
                status = VaultCryptoKatRequirementStatus.DependencyLevelPassedProviderLevelMissing,
            ),
            VaultCryptoProviderKatRequirement(
                operation = VaultCryptoOperation.EncryptRecord,
                algorithmLabel = EncryptedVaultAeadAlgorithm.XChaCha20Poly1305.label,
                vectorSource = "draft-irtf-cfrg-xchacha-02 appendix A.1 public vector",
                requiresDesktopRuntime = true,
                requiresAndroidRuntime = true,
                status = VaultCryptoKatRequirementStatus.DependencyLevelPassedProviderLevelMissing,
            ),
        ),
        diagnostics = listOf(
            VaultCryptoProviderDiagnostic(
                operation = VaultCryptoOperation.ProviderKat,
                safeCode = "PROVIDER_LEVEL_KAT_NOT_IMPLEMENTED",
                safeDetail = "Dependency-level KATs passed, but no provider-level KAT path exists.",
            ),
        ),
        implementationNote = "Disabled provider boundary only. It models reviewed algorithms, typed record purposes, KAT requirements, and redacted errors, but it does not derive keys, encrypt, decrypt, generate keys, store keysets, write containers, persist data, or enable mainnet.",
    )
