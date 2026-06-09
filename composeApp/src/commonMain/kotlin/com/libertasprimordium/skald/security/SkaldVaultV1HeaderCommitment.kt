package com.libertasprimordium.skald.security

private const val UINT16_MAX = 0xffff
private const val HMAC_SHA256_BYTES = 32

internal expect fun skaldVaultV1HmacSha256(
    key: ByteArray,
    message: ByteArray,
): ByteArray

enum class SkaldVaultV1KeyPurpose(val label: String) {
    HeaderCommitment("skald-vault/v1/header-commitment-key"),
    RecordAead("skald-vault/v1/record-aead-key"),
}

enum class SkaldVaultV1HeaderCommitmentRejectionReason(val label: String) {
    UnsupportedSuiteId("unsupported provider suite id"),
    UnsupportedKdfAlgorithm("unsupported KDF algorithm"),
    UnsupportedKdfVersion("unsupported KDF version"),
    UnsupportedKdfParameters("unsupported KDF parameters"),
    UnsupportedPolicyId("unsupported policy id"),
    UnsupportedVersion("unsupported version"),
    UnsupportedKeyPurpose("unsupported key purpose"),
    MalformedLength("malformed length"),
    MalformedIntegerValue("malformed integer value"),
    MalformedStringValue("malformed string value"),
    InvalidRootMaterialLength("invalid root material length"),
    InvalidHeaderCommitmentKeyLength("invalid header commitment key length"),
    InvalidHeaderCommitmentTagLength("invalid header commitment tag length"),
}

sealed class SkaldVaultV1HeaderCommitmentResult<out T> {
    data class Accepted<out T>(val value: T) : SkaldVaultV1HeaderCommitmentResult<T>()

    data class Rejected(
        val reason: SkaldVaultV1HeaderCommitmentRejectionReason,
        val safeMessage: String,
    ) : SkaldVaultV1HeaderCommitmentResult<Nothing>()
}

data class SkaldVaultV1CanonicalHeader(
    val vaultMagic: String = SkaldVaultV1HeaderCommitment.VAULT_MAGIC,
    val vaultFormatVersion: Int = SkaldVaultV1HeaderCommitment.VAULT_FORMAT_VERSION,
    val providerSuiteId: String = SkaldVaultV1HeaderCommitment.PROVIDER_SUITE_ID,
    val kdfAlgorithmId: String = SkaldVaultV1HeaderCommitment.KDF_ALGORITHM_ID,
    val kdfVersion: Int = SkaldVaultV1HeaderCommitment.KDF_VERSION,
    val kdfMemoryKiB: Int = SkaldVaultV1HeaderCommitment.KDF_MEMORY_KIB,
    val kdfTimeCost: Int = SkaldVaultV1HeaderCommitment.KDF_TIME_COST,
    val kdfParallelism: Int = SkaldVaultV1HeaderCommitment.KDF_PARALLELISM,
    val salt: ByteArray,
    val derivedRootMaterialBytes: Int = SkaldVaultV1HeaderCommitment.ARGON2ID_ROOT_MATERIAL_BYTES,
    val vaultId: ByteArray,
    val passphraseEncodingPolicyId: String =
        SkaldVaultV1HeaderCommitment.PASSPHRASE_ENCODING_POLICY_ID,
    val keyExpansionPolicyId: String = SkaldVaultV1HeaderCommitment.KEY_EXPANSION_POLICY_ID,
    val keySeparationPolicyId: String = SkaldVaultV1HeaderCommitment.KEY_SEPARATION_POLICY_ID,
    val headerCommitmentPrimitivePolicyId: String =
        SkaldVaultV1HeaderCommitment.HEADER_COMMITMENT_PRIMITIVE_POLICY_ID,
    val headerCommitmentPolicyId: String =
        SkaldVaultV1HeaderCommitment.HEADER_COMMITMENT_POLICY_ID,
    val aadPolicyId: String = SkaldVaultV1HeaderCommitment.AAD_POLICY_ID,
    val aadPolicyVersion: Int = SkaldVaultV1HeaderCommitment.AAD_POLICY_VERSION,
    val recordFormatPolicyId: String = SkaldVaultV1HeaderCommitment.RECORD_FORMAT_POLICY_ID,
    val recordFormatPolicyVersion: Int =
        SkaldVaultV1HeaderCommitment.RECORD_FORMAT_POLICY_VERSION,
    val featureFlags: Int = SkaldVaultV1HeaderCommitment.FEATURE_FLAGS,
    val integrityCriticalHeaderMetadata: ByteArray = ByteArray(0),
)

class SkaldVaultV1ExpandedKeys private constructor(
    headerCommitmentKey: ByteArray,
    recordAeadKey: ByteArray,
) {
    private val headerCommitmentKeyBytes: ByteArray = headerCommitmentKey.copyOf()
    private val recordAeadKeyBytes: ByteArray = recordAeadKey.copyOf()

    val headerCommitmentKey: ByteArray
        get() = headerCommitmentKeyBytes.copyOf()

    val recordAeadKey: ByteArray
        get() = recordAeadKeyBytes.copyOf()

    companion object {
        internal fun create(
            headerCommitmentKey: ByteArray,
            recordAeadKey: ByteArray,
        ): SkaldVaultV1ExpandedKeys =
            SkaldVaultV1ExpandedKeys(
                headerCommitmentKey = headerCommitmentKey,
                recordAeadKey = recordAeadKey,
            )
    }
}

object SkaldVaultV1HeaderCommitment {
    const val VAULT_MAGIC = "SKALD-VAULT-V1"
    const val VAULT_FORMAT_VERSION = 1
    const val PROVIDER_SUITE_ID =
        "skald-vault-v1-bouncycastle-argon2id-tink-xchacha20poly1305-os-securerandom"
    const val KDF_ALGORITHM_ID = "argon2id"
    const val KDF_VERSION = 19
    const val KDF_MEMORY_KIB = 65_536
    const val KDF_TIME_COST = 3
    const val KDF_PARALLELISM = 1
    const val ARGON2ID_ROOT_MATERIAL_BYTES = 64
    const val HEADER_COMMITMENT_KEY_BYTES = 32
    const val RECORD_AEAD_KEY_BYTES = 32
    const val PASSPHRASE_ENCODING_POLICY_ID =
        "unicode-nfc-utf8-no-controls-no-whitespace-v1"
    const val KEY_EXPANSION_POLICY_ID =
        "skald-vault-v1-hkdf-sha256-key-expansion-v1"
    const val KEY_SEPARATION_POLICY_ID =
        "skald-vault-v1-key-separation-labels-v1"
    const val HEADER_COMMITMENT_PRIMITIVE_POLICY_ID =
        "skald-vault-v1-hmac-sha256-header-commitment-v1"
    const val HEADER_COMMITMENT_POLICY_ID =
        "skald-vault-v1-header-commitment-v1"
    const val AAD_POLICY_ID = "skald-vault-v1-record-aad-v1"
    const val AAD_POLICY_VERSION = 1
    const val RECORD_FORMAT_POLICY_ID = "skald-vault-v1-record-format-v1"
    const val RECORD_FORMAT_POLICY_VERSION = 1
    const val FEATURE_FLAGS = 0
    const val ROOT_DOMAIN_LABEL = "skald-vault/v1/root-domain"
    const val RESERVED_WRAPPING_METADATA_LABEL = "skald-vault/v1/reserved/wrapping-metadata"
    const val RESERVED_EXPORT_MIGRATION_LABEL = "skald-vault/v1/reserved/export-migration"
    const val TEST_ONLY_RAW_KEY_PROBE_LABEL = "skald-vault/test-only/raw-key-probe"

    fun vectorFixtureHeader(): SkaldVaultV1CanonicalHeader =
        SkaldVaultV1CanonicalHeader(
            salt = (0x00..0x1f).map { it.toByte() }.toByteArray(),
            vaultId = (0x20..0x2f).map { it.toByte() }.toByteArray(),
            integrityCriticalHeaderMetadata = ByteArray(0),
        )

    fun canonicalHeaderBytes(
        header: SkaldVaultV1CanonicalHeader,
    ): SkaldVaultV1HeaderCommitmentResult<ByteArray> {
        validateHeader(header)?.let { return rejected(it) }
        return accepted(
            byteList {
                putStringField(1, header.vaultMagic)
                putU16Field(2, header.vaultFormatVersion)
                putStringField(3, header.providerSuiteId)
                putStringField(4, header.kdfAlgorithmId)
                putU16Field(5, header.kdfVersion)
                putU32Field(6, header.kdfMemoryKiB)
                putU32Field(7, header.kdfTimeCost)
                putU32Field(8, header.kdfParallelism)
                putBytesField(9, header.salt)
                putU16Field(10, header.derivedRootMaterialBytes)
                putBytesField(11, header.vaultId)
                putStringField(12, header.passphraseEncodingPolicyId)
                putStringField(13, header.keyExpansionPolicyId)
                putStringField(14, header.keySeparationPolicyId)
                putStringField(15, header.headerCommitmentPrimitivePolicyId)
                putStringField(16, header.headerCommitmentPolicyId)
                putStringField(17, header.aadPolicyId)
                putU16Field(18, header.aadPolicyVersion)
                putStringField(19, header.recordFormatPolicyId)
                putU16Field(20, header.recordFormatPolicyVersion)
                putU32Field(21, header.featureFlags)
                putBytesField(22, header.integrityCriticalHeaderMetadata)
            },
        )
    }

    fun hkdfInfoBytes(
        purpose: SkaldVaultV1KeyPurpose,
    ): SkaldVaultV1HeaderCommitmentResult<ByteArray> =
        hkdfInfoBytes(purpose.label)

    fun hkdfInfoBytes(
        purposeLabel: String,
    ): SkaldVaultV1HeaderCommitmentResult<ByteArray> {
        if (SkaldVaultV1KeyPurpose.entries.none { it.label == purposeLabel }) {
            return rejected(SkaldVaultV1HeaderCommitmentRejectionReason.UnsupportedKeyPurpose)
        }
        validateAsciiString(purposeLabel)?.let { return rejected(it) }
        return accepted(
            byteList {
                putStringField(1, ROOT_DOMAIN_LABEL)
                putStringField(2, PROVIDER_SUITE_ID)
                putStringField(3, purposeLabel)
                putStringField(4, KEY_EXPANSION_POLICY_ID)
                putU16Field(5, VAULT_FORMAT_VERSION)
            },
        )
    }

    fun expandRootMaterial(
        rootMaterial: ByteArray,
        header: SkaldVaultV1CanonicalHeader,
    ): SkaldVaultV1HeaderCommitmentResult<SkaldVaultV1ExpandedKeys> {
        if (rootMaterial.size != ARGON2ID_ROOT_MATERIAL_BYTES) {
            return rejected(SkaldVaultV1HeaderCommitmentRejectionReason.InvalidRootMaterialLength)
        }
        validateHeader(header)?.let { return rejected(it) }
        val headerInfo = when (val result = hkdfInfoBytes(SkaldVaultV1KeyPurpose.HeaderCommitment)) {
            is SkaldVaultV1HeaderCommitmentResult.Accepted -> result.value
            is SkaldVaultV1HeaderCommitmentResult.Rejected -> return result
        }
        val recordInfo = when (val result = hkdfInfoBytes(SkaldVaultV1KeyPurpose.RecordAead)) {
            is SkaldVaultV1HeaderCommitmentResult.Accepted -> result.value
            is SkaldVaultV1HeaderCommitmentResult.Rejected -> return result
        }
        return accepted(
            SkaldVaultV1ExpandedKeys.create(
                headerCommitmentKey = hkdfSha256(
                    inputKeyingMaterial = rootMaterial,
                    salt = header.salt,
                    info = headerInfo,
                    outputBytes = HEADER_COMMITMENT_KEY_BYTES,
                ),
                recordAeadKey = hkdfSha256(
                    inputKeyingMaterial = rootMaterial,
                    salt = header.salt,
                    info = recordInfo,
                    outputBytes = RECORD_AEAD_KEY_BYTES,
                ),
            ),
        )
    }

    fun expandRootMaterialForPurpose(
        rootMaterial: ByteArray,
        header: SkaldVaultV1CanonicalHeader,
        purposeLabel: String,
    ): SkaldVaultV1HeaderCommitmentResult<ByteArray> {
        if (rootMaterial.size != ARGON2ID_ROOT_MATERIAL_BYTES) {
            return rejected(SkaldVaultV1HeaderCommitmentRejectionReason.InvalidRootMaterialLength)
        }
        validateHeader(header)?.let { return rejected(it) }
        val outputBytes = when (purposeLabel) {
            SkaldVaultV1KeyPurpose.HeaderCommitment.label -> HEADER_COMMITMENT_KEY_BYTES
            SkaldVaultV1KeyPurpose.RecordAead.label -> RECORD_AEAD_KEY_BYTES
            else -> return rejected(SkaldVaultV1HeaderCommitmentRejectionReason.UnsupportedKeyPurpose)
        }
        val info = when (val result = hkdfInfoBytes(purposeLabel)) {
            is SkaldVaultV1HeaderCommitmentResult.Accepted -> result.value
            is SkaldVaultV1HeaderCommitmentResult.Rejected -> return result
        }
        return accepted(
            hkdfSha256(
                inputKeyingMaterial = rootMaterial,
                salt = header.salt,
                info = info,
                outputBytes = outputBytes,
            ),
        )
    }

    fun computeHeaderCommitment(
        headerCommitmentKey: ByteArray,
        canonicalHeaderBytes: ByteArray,
    ): SkaldVaultV1HeaderCommitmentResult<ByteArray> {
        if (headerCommitmentKey.size != HEADER_COMMITMENT_KEY_BYTES) {
            return rejected(
                SkaldVaultV1HeaderCommitmentRejectionReason.InvalidHeaderCommitmentKeyLength,
            )
        }
        return accepted(skaldVaultV1HmacSha256(headerCommitmentKey, canonicalHeaderBytes))
    }

    fun verifyHeaderCommitment(
        headerCommitmentKey: ByteArray,
        canonicalHeaderBytes: ByteArray,
        expectedTag: ByteArray,
    ): SkaldVaultV1HeaderCommitmentResult<Boolean> {
        if (headerCommitmentKey.size != HEADER_COMMITMENT_KEY_BYTES) {
            return rejected(
                SkaldVaultV1HeaderCommitmentRejectionReason.InvalidHeaderCommitmentKeyLength,
            )
        }
        if (expectedTag.size != HMAC_SHA256_BYTES) {
            return rejected(
                SkaldVaultV1HeaderCommitmentRejectionReason.InvalidHeaderCommitmentTagLength,
            )
        }
        val actualTag = skaldVaultV1HmacSha256(headerCommitmentKey, canonicalHeaderBytes)
        return accepted(constantTimeEquals(actualTag, expectedTag))
    }

    fun verifyHeaderCommitment(
        header: SkaldVaultV1CanonicalHeader,
        rootMaterial: ByteArray,
        expectedTag: ByteArray,
    ): SkaldVaultV1HeaderCommitmentResult<Boolean> {
        val canonical = when (val result = canonicalHeaderBytes(header)) {
            is SkaldVaultV1HeaderCommitmentResult.Accepted -> result.value
            is SkaldVaultV1HeaderCommitmentResult.Rejected -> return result
        }
        val expandedKeys = when (val result = expandRootMaterial(rootMaterial, header)) {
            is SkaldVaultV1HeaderCommitmentResult.Accepted -> result.value
            is SkaldVaultV1HeaderCommitmentResult.Rejected -> return result
        }
        return verifyHeaderCommitment(
            headerCommitmentKey = expandedKeys.headerCommitmentKey,
            canonicalHeaderBytes = canonical,
            expectedTag = expectedTag,
        )
    }

    private fun hkdfSha256(
        inputKeyingMaterial: ByteArray,
        salt: ByteArray,
        info: ByteArray,
        outputBytes: Int,
    ): ByteArray {
        val pseudorandomKey = skaldVaultV1HmacSha256(salt, inputKeyingMaterial)
        val output = mutableListOf<Byte>()
        var previous = ByteArray(0)
        var counter = 1

        while (output.size < outputBytes) {
            previous = skaldVaultV1HmacSha256(
                pseudorandomKey,
                previous + info + byteArrayOf(counter.toByte()),
            )
            output.addAll(previous.toList())
            counter += 1
        }

        return output.take(outputBytes).toByteArray()
    }

    private fun validateHeader(
        header: SkaldVaultV1CanonicalHeader,
    ): SkaldVaultV1HeaderCommitmentRejectionReason? {
        if (header.vaultMagic != VAULT_MAGIC) {
            return SkaldVaultV1HeaderCommitmentRejectionReason.UnsupportedPolicyId
        }
        if (header.vaultFormatVersion != VAULT_FORMAT_VERSION ||
            header.aadPolicyVersion != AAD_POLICY_VERSION ||
            header.recordFormatPolicyVersion != RECORD_FORMAT_POLICY_VERSION
        ) {
            return SkaldVaultV1HeaderCommitmentRejectionReason.UnsupportedVersion
        }
        if (header.providerSuiteId != PROVIDER_SUITE_ID) {
            return SkaldVaultV1HeaderCommitmentRejectionReason.UnsupportedSuiteId
        }
        if (header.kdfAlgorithmId != KDF_ALGORITHM_ID) {
            return SkaldVaultV1HeaderCommitmentRejectionReason.UnsupportedKdfAlgorithm
        }
        if (header.kdfVersion != KDF_VERSION) {
            return SkaldVaultV1HeaderCommitmentRejectionReason.UnsupportedKdfVersion
        }
        if (header.kdfMemoryKiB != KDF_MEMORY_KIB ||
            header.kdfTimeCost != KDF_TIME_COST ||
            header.kdfParallelism != KDF_PARALLELISM ||
            header.derivedRootMaterialBytes != ARGON2ID_ROOT_MATERIAL_BYTES
        ) {
            return SkaldVaultV1HeaderCommitmentRejectionReason.UnsupportedKdfParameters
        }
        if (header.salt.size !in 16..64 || header.vaultId.size != 16 ||
            header.integrityCriticalHeaderMetadata.size > 1024
        ) {
            return SkaldVaultV1HeaderCommitmentRejectionReason.MalformedLength
        }
        if (header.featureFlags != FEATURE_FLAGS) {
            return SkaldVaultV1HeaderCommitmentRejectionReason.UnsupportedVersion
        }
        listOf(
            header.vaultMagic,
            header.providerSuiteId,
            header.kdfAlgorithmId,
            header.passphraseEncodingPolicyId,
            header.keyExpansionPolicyId,
            header.keySeparationPolicyId,
            header.headerCommitmentPrimitivePolicyId,
            header.headerCommitmentPolicyId,
            header.aadPolicyId,
            header.recordFormatPolicyId,
        ).forEach { value ->
            validateAsciiString(value)?.let { return it }
        }
        if (header.passphraseEncodingPolicyId != PASSPHRASE_ENCODING_POLICY_ID ||
            header.keyExpansionPolicyId != KEY_EXPANSION_POLICY_ID ||
            header.keySeparationPolicyId != KEY_SEPARATION_POLICY_ID ||
            header.headerCommitmentPrimitivePolicyId != HEADER_COMMITMENT_PRIMITIVE_POLICY_ID ||
            header.headerCommitmentPolicyId != HEADER_COMMITMENT_POLICY_ID ||
            header.aadPolicyId != AAD_POLICY_ID ||
            header.recordFormatPolicyId != RECORD_FORMAT_POLICY_ID
        ) {
            return SkaldVaultV1HeaderCommitmentRejectionReason.UnsupportedPolicyId
        }
        listOf(
            header.vaultFormatVersion,
            header.kdfVersion,
            header.kdfMemoryKiB,
            header.kdfTimeCost,
            header.kdfParallelism,
            header.derivedRootMaterialBytes,
            header.aadPolicyVersion,
            header.recordFormatPolicyVersion,
            header.featureFlags,
        ).forEach { value ->
            if (value < 0) {
                return SkaldVaultV1HeaderCommitmentRejectionReason.MalformedIntegerValue
            }
        }
        return null
    }

    private fun validateAsciiString(
        value: String,
    ): SkaldVaultV1HeaderCommitmentRejectionReason? {
        val bytes = value.encodeToByteArray()
        if (bytes.isEmpty() || bytes.size > UINT16_MAX) {
            return SkaldVaultV1HeaderCommitmentRejectionReason.MalformedLength
        }
        if (bytes.any { byte -> byte.toInt() !in 0x20..0x7e }) {
            return SkaldVaultV1HeaderCommitmentRejectionReason.MalformedStringValue
        }
        return null
    }

    private fun byteList(block: MutableList<Byte>.() -> Unit): ByteArray =
        mutableListOf<Byte>().apply(block).toByteArray()

    private fun MutableList<Byte>.putStringField(fieldId: Int, value: String) {
        putU16(fieldId)
        putLengthPrefixedBytes(value.encodeToByteArray())
    }

    private fun MutableList<Byte>.putBytesField(fieldId: Int, value: ByteArray) {
        putU16(fieldId)
        putLengthPrefixedBytes(value)
    }

    private fun MutableList<Byte>.putU16Field(fieldId: Int, value: Int) {
        putU16(fieldId)
        putU16(value)
    }

    private fun MutableList<Byte>.putU32Field(fieldId: Int, value: Int) {
        putU16(fieldId)
        putU32(value)
    }

    private fun MutableList<Byte>.putLengthPrefixedBytes(value: ByteArray) {
        putU16(value.size)
        value.forEach { add(it) }
    }

    private fun MutableList<Byte>.putU16(value: Int) {
        require(value in 0..UINT16_MAX)
        add(((value ushr 8) and 0xff).toByte())
        add((value and 0xff).toByte())
    }

    private fun MutableList<Byte>.putU32(value: Int) {
        require(value >= 0)
        add(((value ushr 24) and 0xff).toByte())
        add(((value ushr 16) and 0xff).toByte())
        add(((value ushr 8) and 0xff).toByte())
        add((value and 0xff).toByte())
    }

    private fun constantTimeEquals(left: ByteArray, right: ByteArray): Boolean {
        if (left.size != right.size) {
            return false
        }
        var accumulator = 0
        left.indices.forEach { index ->
            accumulator = accumulator or (left[index].toInt() xor right[index].toInt())
        }
        return accumulator == 0
    }

    private fun <T> accepted(value: T): SkaldVaultV1HeaderCommitmentResult.Accepted<T> =
        SkaldVaultV1HeaderCommitmentResult.Accepted(value)

    private fun rejected(
        reason: SkaldVaultV1HeaderCommitmentRejectionReason,
    ): SkaldVaultV1HeaderCommitmentResult.Rejected =
        SkaldVaultV1HeaderCommitmentResult.Rejected(
            reason = reason,
            safeMessage = "Skald Vault v1 header commitment input rejected: ${reason.label}.",
        )
}
