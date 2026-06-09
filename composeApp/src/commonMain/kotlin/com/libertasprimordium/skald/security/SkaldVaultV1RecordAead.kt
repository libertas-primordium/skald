package com.libertasprimordium.skald.security

private const val UINT16_MAX = 0xffff
private const val RECORD_ID_BYTES = 16
private const val VAULT_ID_BYTES = 16
private const val HEADER_COMMITMENT_CONTEXT_BYTES = 32
private const val MAX_RECORD_METADATA_BYTES = 1024

internal expect fun skaldVaultV1TinkRecordAeadEncrypt(
    recordAeadKey: ByteArray,
    plaintext: ByteArray,
    associatedData: ByteArray,
): ByteArray

internal expect fun skaldVaultV1TinkRecordAeadDecrypt(
    recordAeadKey: ByteArray,
    ciphertext: ByteArray,
    associatedData: ByteArray,
): ByteArray

enum class SkaldVaultV1RecordType(val typeId: String) {
    SecretPayload("secret-payload"),
    SensitiveMetadata("sensitive-metadata"),
    BackupExport("backup-export"),
    ProviderKatTest("provider-kat-test"),
}

enum class SkaldVaultV1RecordAeadRejectionReason(val label: String) {
    UnsupportedSuiteId("unsupported provider suite id"),
    UnsupportedPolicyId("unsupported policy id"),
    UnsupportedVersion("unsupported version"),
    UnsupportedRecordType("unsupported record type"),
    MalformedLength("malformed length"),
    MalformedIntegerValue("malformed integer value"),
    MalformedStringValue("malformed string value"),
    InvalidRecordAeadKeyLength("invalid record AEAD key length"),
    AeadEncryptionFailed("record AEAD encryption failed"),
    AeadDecryptionFailed("record AEAD decryption failed"),
}

sealed class SkaldVaultV1RecordAeadResult<out T> {
    data class Accepted<out T>(val value: T) : SkaldVaultV1RecordAeadResult<T>()

    data class Rejected(
        val reason: SkaldVaultV1RecordAeadRejectionReason,
        val safeMessage: String,
    ) : SkaldVaultV1RecordAeadResult<Nothing>()
}

data class SkaldVaultV1RecordAadContext(
    val vaultMagic: String = SkaldVaultV1HeaderCommitment.VAULT_MAGIC,
    val vaultFormatVersion: Int = SkaldVaultV1HeaderCommitment.VAULT_FORMAT_VERSION,
    val providerSuiteId: String = SkaldVaultV1HeaderCommitment.PROVIDER_SUITE_ID,
    val vaultId: ByteArray,
    val recordFormatPolicyId: String = SkaldVaultV1HeaderCommitment.RECORD_FORMAT_POLICY_ID,
    val recordFormatPolicyVersion: Int = SkaldVaultV1HeaderCommitment.RECORD_FORMAT_POLICY_VERSION,
    val aadPolicyId: String = SkaldVaultV1HeaderCommitment.AAD_POLICY_ID,
    val aadPolicyVersion: Int = SkaldVaultV1HeaderCommitment.AAD_POLICY_VERSION,
    val keyExpansionPolicyId: String = SkaldVaultV1HeaderCommitment.KEY_EXPANSION_POLICY_ID,
    val headerCommitmentPrimitivePolicyId: String =
        SkaldVaultV1HeaderCommitment.HEADER_COMMITMENT_PRIMITIVE_POLICY_ID,
    val headerCommitmentPolicyId: String =
        SkaldVaultV1HeaderCommitment.HEADER_COMMITMENT_POLICY_ID,
    val headerCommitmentContext: ByteArray,
    val recordTypeId: String,
    val recordId: ByteArray,
    val recordVersionCounter: Long,
    val integrityCriticalRecordMetadata: ByteArray = ByteArray(0),
    val storageNamespace: String = SkaldVaultV1RecordAead.STORAGE_NAMESPACE,
)

class SkaldVaultV1RecordCiphertext private constructor(
    bytes: ByteArray,
) {
    private val ciphertextBytes: ByteArray = bytes.copyOf()

    val bytes: ByteArray
        get() = ciphertextBytes.copyOf()

    companion object {
        internal fun create(bytes: ByteArray): SkaldVaultV1RecordCiphertext =
            SkaldVaultV1RecordCiphertext(bytes)
    }
}

class SkaldVaultV1RecordPlaintext private constructor(
    bytes: ByteArray,
) {
    private val plaintextBytes: ByteArray = bytes.copyOf()

    val bytes: ByteArray
        get() = plaintextBytes.copyOf()

    companion object {
        internal fun create(bytes: ByteArray): SkaldVaultV1RecordPlaintext =
            SkaldVaultV1RecordPlaintext(bytes)
    }
}

object SkaldVaultV1RecordAead {
    const val STORAGE_NAMESPACE = "skald-vault/v1/local-records"

    val VECTOR_RECORD_AEAD_KEY: ByteArray
        get() = hexToBytes("7634139c7f7d165280344d986374090b9124a4caa45739915c75ff2b300a0872")

    val VECTOR_PLAINTEXT: ByteArray
        get() = "skald vault v1 fixed non-secret record plaintext".encodeToByteArray()

    fun vectorFixtureAadContext(): SkaldVaultV1RecordAadContext =
        SkaldVaultV1RecordAadContext(
            vaultId = (0x20..0x2f).map { it.toByte() }.toByteArray(),
            headerCommitmentContext =
                hexToBytes("1d09a657d8929444c1e955410b2dcb3bfc0df2d19b128154e17a5fbd751237d5"),
            recordTypeId = SkaldVaultV1RecordType.SensitiveMetadata.typeId,
            recordId = (0x40..0x4f).map { it.toByte() }.toByteArray(),
            recordVersionCounter = 7,
            integrityCriticalRecordMetadata =
                "skald-vault-v1-record-metadata-fixture".encodeToByteArray(),
        )

    fun aadBytes(
        context: SkaldVaultV1RecordAadContext,
    ): SkaldVaultV1RecordAeadResult<ByteArray> {
        validateAadContext(context)?.let { return rejected(it) }
        return accepted(
            byteList {
                putStringField(1, context.vaultMagic)
                putU16Field(2, context.vaultFormatVersion)
                putStringField(3, context.providerSuiteId)
                putBytesField(4, context.vaultId)
                putStringField(5, context.recordFormatPolicyId)
                putU16Field(6, context.recordFormatPolicyVersion)
                putStringField(7, context.aadPolicyId)
                putU16Field(8, context.aadPolicyVersion)
                putStringField(9, context.keyExpansionPolicyId)
                putStringField(10, context.headerCommitmentPrimitivePolicyId)
                putStringField(11, context.headerCommitmentPolicyId)
                putBytesField(12, context.headerCommitmentContext)
                putStringField(13, context.recordTypeId)
                putBytesField(14, context.recordId)
                putU64Field(15, context.recordVersionCounter)
                putBytesField(16, context.integrityCriticalRecordMetadata)
                putStringField(17, context.storageNamespace)
            },
        )
    }

    fun encryptRecord(
        recordAeadKey: ByteArray,
        plaintext: ByteArray,
        aadContext: SkaldVaultV1RecordAadContext,
    ): SkaldVaultV1RecordAeadResult<SkaldVaultV1RecordCiphertext> {
        if (recordAeadKey.size != SkaldVaultV1HeaderCommitment.RECORD_AEAD_KEY_BYTES) {
            return rejected(SkaldVaultV1RecordAeadRejectionReason.InvalidRecordAeadKeyLength)
        }
        val associatedData = when (val result = aadBytes(aadContext)) {
            is SkaldVaultV1RecordAeadResult.Accepted -> result.value
            is SkaldVaultV1RecordAeadResult.Rejected -> return result
        }
        return try {
            accepted(
                SkaldVaultV1RecordCiphertext.create(
                    skaldVaultV1TinkRecordAeadEncrypt(
                        recordAeadKey = recordAeadKey.copyOf(),
                        plaintext = plaintext.copyOf(),
                        associatedData = associatedData,
                    ),
                ),
            )
        } catch (_: Exception) {
            rejected(SkaldVaultV1RecordAeadRejectionReason.AeadEncryptionFailed)
        }
    }

    fun decryptRecord(
        recordAeadKey: ByteArray,
        ciphertext: ByteArray,
        aadContext: SkaldVaultV1RecordAadContext,
    ): SkaldVaultV1RecordAeadResult<SkaldVaultV1RecordPlaintext> {
        if (recordAeadKey.size != SkaldVaultV1HeaderCommitment.RECORD_AEAD_KEY_BYTES) {
            return rejected(SkaldVaultV1RecordAeadRejectionReason.InvalidRecordAeadKeyLength)
        }
        val associatedData = when (val result = aadBytes(aadContext)) {
            is SkaldVaultV1RecordAeadResult.Accepted -> result.value
            is SkaldVaultV1RecordAeadResult.Rejected -> return result
        }
        return try {
            accepted(
                SkaldVaultV1RecordPlaintext.create(
                    skaldVaultV1TinkRecordAeadDecrypt(
                        recordAeadKey = recordAeadKey.copyOf(),
                        ciphertext = ciphertext.copyOf(),
                        associatedData = associatedData,
                    ),
                ),
            )
        } catch (_: Exception) {
            rejected(SkaldVaultV1RecordAeadRejectionReason.AeadDecryptionFailed)
        }
    }

    internal fun hexToBytes(hex: String): ByteArray {
        require(hex.length % 2 == 0)
        return ByteArray(hex.length / 2) { index ->
            hex.substring(index * 2, index * 2 + 2).toInt(16).toByte()
        }
    }

    private fun validateAadContext(
        context: SkaldVaultV1RecordAadContext,
    ): SkaldVaultV1RecordAeadRejectionReason? {
        if (context.vaultMagic != SkaldVaultV1HeaderCommitment.VAULT_MAGIC) {
            return SkaldVaultV1RecordAeadRejectionReason.UnsupportedPolicyId
        }
        if (context.providerSuiteId != SkaldVaultV1HeaderCommitment.PROVIDER_SUITE_ID) {
            return SkaldVaultV1RecordAeadRejectionReason.UnsupportedSuiteId
        }
        if (context.vaultFormatVersion != SkaldVaultV1HeaderCommitment.VAULT_FORMAT_VERSION ||
            context.recordFormatPolicyVersion != SkaldVaultV1HeaderCommitment.RECORD_FORMAT_POLICY_VERSION ||
            context.aadPolicyVersion != SkaldVaultV1HeaderCommitment.AAD_POLICY_VERSION
        ) {
            return SkaldVaultV1RecordAeadRejectionReason.UnsupportedVersion
        }
        if (context.vaultId.size != VAULT_ID_BYTES ||
            context.headerCommitmentContext.size != HEADER_COMMITMENT_CONTEXT_BYTES ||
            context.recordId.size != RECORD_ID_BYTES ||
            context.integrityCriticalRecordMetadata.size > MAX_RECORD_METADATA_BYTES
        ) {
            return SkaldVaultV1RecordAeadRejectionReason.MalformedLength
        }
        if (context.recordVersionCounter < 0) {
            return SkaldVaultV1RecordAeadRejectionReason.MalformedIntegerValue
        }
        listOf(
            context.vaultMagic,
            context.providerSuiteId,
            context.recordFormatPolicyId,
            context.aadPolicyId,
            context.keyExpansionPolicyId,
            context.headerCommitmentPrimitivePolicyId,
            context.headerCommitmentPolicyId,
            context.recordTypeId,
            context.storageNamespace,
        ).forEach { value ->
            validateAsciiString(value)?.let { return it }
        }
        if (context.recordFormatPolicyId != SkaldVaultV1HeaderCommitment.RECORD_FORMAT_POLICY_ID ||
            context.aadPolicyId != SkaldVaultV1HeaderCommitment.AAD_POLICY_ID ||
            context.keyExpansionPolicyId != SkaldVaultV1HeaderCommitment.KEY_EXPANSION_POLICY_ID ||
            context.headerCommitmentPrimitivePolicyId !=
            SkaldVaultV1HeaderCommitment.HEADER_COMMITMENT_PRIMITIVE_POLICY_ID ||
            context.headerCommitmentPolicyId != SkaldVaultV1HeaderCommitment.HEADER_COMMITMENT_POLICY_ID ||
            context.storageNamespace != STORAGE_NAMESPACE
        ) {
            return SkaldVaultV1RecordAeadRejectionReason.UnsupportedPolicyId
        }
        if (SkaldVaultV1RecordType.entries.none { it.typeId == context.recordTypeId }) {
            return SkaldVaultV1RecordAeadRejectionReason.UnsupportedRecordType
        }
        return null
    }

    private fun validateAsciiString(
        value: String,
    ): SkaldVaultV1RecordAeadRejectionReason? {
        val bytes = value.encodeToByteArray()
        if (bytes.isEmpty() || bytes.size > UINT16_MAX) {
            return SkaldVaultV1RecordAeadRejectionReason.MalformedLength
        }
        if (bytes.any { byte -> byte.toInt() !in 0x20..0x7e }) {
            return SkaldVaultV1RecordAeadRejectionReason.MalformedStringValue
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

    private fun MutableList<Byte>.putU64Field(fieldId: Int, value: Long) {
        putU16(fieldId)
        putU64(value)
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

    private fun MutableList<Byte>.putU64(value: Long) {
        require(value >= 0)
        for (shift in 56 downTo 0 step 8) {
            add(((value ushr shift) and 0xff).toByte())
        }
    }

    private fun <T> accepted(value: T): SkaldVaultV1RecordAeadResult.Accepted<T> =
        SkaldVaultV1RecordAeadResult.Accepted(value)

    private fun rejected(
        reason: SkaldVaultV1RecordAeadRejectionReason,
    ): SkaldVaultV1RecordAeadResult.Rejected =
        SkaldVaultV1RecordAeadResult.Rejected(
            reason = reason,
            safeMessage = "Skald Vault v1 record AEAD input rejected: ${reason.label}.",
        )
}
