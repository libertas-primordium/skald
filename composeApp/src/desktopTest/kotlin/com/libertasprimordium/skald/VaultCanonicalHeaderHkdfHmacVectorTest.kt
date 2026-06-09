package com.libertasprimordium.skald

import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class VaultCanonicalHeaderHkdfHmacVectorTest {
    @Test
    fun canonicalHeaderFixtureBytesMatchDocumentedHex() {
        assertEquals(EXPECTED_CANONICAL_HEADER_HEX, canonicalHeaderBytes().toHex())
        assertEquals(509, canonicalHeaderBytes().size)
    }

    @Test
    fun hkdfInfoFixturesMatchDocumentedHex() {
        assertEquals(EXPECTED_HEADER_INFO_HEX, hkdfInfoBytes(HEADER_COMMITMENT_KEY_LABEL).toHex())
        assertEquals(EXPECTED_RECORD_INFO_HEX, hkdfInfoBytes(RECORD_AEAD_KEY_LABEL).toHex())
    }

    @Test
    fun hkdfSha256VectorsMatchDocumentedOutputs() {
        assertEquals(
            EXPECTED_HEADER_COMMITMENT_KEY_HEX,
            hkdfSha256(
                inputKeyingMaterial = ROOT_MATERIAL_FIXTURE,
                salt = SALT_FIXTURE,
                info = hkdfInfoBytes(HEADER_COMMITMENT_KEY_LABEL),
                outputBytes = 32,
            ).toHex(),
        )
        assertEquals(
            EXPECTED_RECORD_AEAD_KEY_HEX,
            hkdfSha256(
                inputKeyingMaterial = ROOT_MATERIAL_FIXTURE,
                salt = SALT_FIXTURE,
                info = hkdfInfoBytes(RECORD_AEAD_KEY_LABEL),
                outputBytes = 32,
            ).toHex(),
        )
    }

    @Test
    fun hmacSha256HeaderCommitmentVectorMatchesDocumentedOutput() {
        val headerCommitmentKey = EXPECTED_HEADER_COMMITMENT_KEY_HEX.hexToBytes()

        assertContentEquals(
            headerCommitmentKey,
            hkdfSha256(
                inputKeyingMaterial = ROOT_MATERIAL_FIXTURE,
                salt = SALT_FIXTURE,
                info = hkdfInfoBytes(HEADER_COMMITMENT_KEY_LABEL),
                outputBytes = 32,
            ),
        )
        assertEquals(
            EXPECTED_HEADER_COMMITMENT_TAG_HEX,
            hmacSha256(headerCommitmentKey, canonicalHeaderBytes()).toHex(),
        )
    }

    private fun canonicalHeaderBytes(): ByteArray =
        byteList {
            putStringField(1, VAULT_MAGIC)
            putU16Field(2, VAULT_FORMAT_VERSION)
            putStringField(3, PROVIDER_SUITE_ID)
            putStringField(4, KDF_ALGORITHM_ID)
            putU16Field(5, KDF_VERSION)
            putU32Field(6, KDF_MEMORY_KIB)
            putU32Field(7, KDF_TIME_COST)
            putU32Field(8, KDF_PARALLELISM)
            putBytesField(9, SALT_FIXTURE)
            putU16Field(10, ARGON2ID_ROOT_MATERIAL_BYTES)
            putBytesField(11, VAULT_ID_FIXTURE)
            putStringField(12, PASSPHRASE_ENCODING_POLICY_ID)
            putStringField(13, KEY_EXPANSION_POLICY_ID)
            putStringField(14, KEY_SEPARATION_POLICY_ID)
            putStringField(15, HEADER_COMMITMENT_PRIMITIVE_POLICY_ID)
            putStringField(16, HEADER_COMMITMENT_POLICY_ID)
            putStringField(17, AAD_POLICY_ID)
            putU16Field(18, AAD_POLICY_VERSION)
            putStringField(19, RECORD_FORMAT_POLICY_ID)
            putU16Field(20, RECORD_FORMAT_POLICY_VERSION)
            putU32Field(21, FEATURE_FLAGS)
            putBytesField(22, INTEGRITY_CRITICAL_HEADER_METADATA_FIXTURE)
        }

    private fun hkdfInfoBytes(purposeLabel: String): ByteArray =
        byteList {
            putStringField(1, ROOT_DOMAIN_LABEL)
            putStringField(2, PROVIDER_SUITE_ID)
            putStringField(3, purposeLabel)
            putStringField(4, KEY_EXPANSION_POLICY_ID)
            putU16Field(5, VAULT_FORMAT_VERSION)
        }

    private fun hkdfSha256(
        inputKeyingMaterial: ByteArray,
        salt: ByteArray,
        info: ByteArray,
        outputBytes: Int,
    ): ByteArray {
        val pseudorandomKey = hmacSha256(salt, inputKeyingMaterial)
        val output = mutableListOf<Byte>()
        var previous = ByteArray(0)
        var counter = 1

        while (output.size < outputBytes) {
            previous = hmacSha256(pseudorandomKey, previous + info + byteArrayOf(counter.toByte()))
            output.addAll(previous.toList())
            counter += 1
        }

        return output.take(outputBytes).toByteArray()
    }

    private fun hmacSha256(key: ByteArray, message: ByteArray): ByteArray {
        val mac = Mac.getInstance(HMAC_SHA256)
        mac.init(SecretKeySpec(key, HMAC_SHA256))
        return mac.doFinal(message)
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
        add(((value ushr 8) and 0xff).toByte())
        add((value and 0xff).toByte())
    }

    private fun MutableList<Byte>.putU32(value: Int) {
        add(((value ushr 24) and 0xff).toByte())
        add(((value ushr 16) and 0xff).toByte())
        add(((value ushr 8) and 0xff).toByte())
        add((value and 0xff).toByte())
    }

    private fun ByteArray.toHex(): String =
        joinToString(separator = "") { byte -> "%02x".format(byte.toInt() and 0xff) }

    private fun String.hexToBytes(): ByteArray {
        require(length % 2 == 0)
        return chunked(2)
            .map { it.toInt(radix = 16).toByte() }
            .toByteArray()
    }

    private companion object {
        const val HMAC_SHA256 = "HmacSHA256"
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
        const val HEADER_COMMITMENT_KEY_LABEL = "skald-vault/v1/header-commitment-key"
        const val RECORD_AEAD_KEY_LABEL = "skald-vault/v1/record-aead-key"

        val SALT_FIXTURE: ByteArray = (0x00..0x1f).map { it.toByte() }.toByteArray()
        val VAULT_ID_FIXTURE: ByteArray = (0x20..0x2f).map { it.toByte() }.toByteArray()
        val ROOT_MATERIAL_FIXTURE: ByteArray = (0xa0..0xdf).map { it.toByte() }.toByteArray()
        val INTEGRITY_CRITICAL_HEADER_METADATA_FIXTURE: ByteArray = ByteArray(0)

        const val EXPECTED_CANONICAL_HEADER_HEX =
            "0001000e534b414c442d5641554c542d5631000200010003004b736b616c642d7661756c742d76312d" +
                "626f756e6379636173746c652d6172676f6e3269642d74696e6b2d786368616368613230706f6c" +
                "79313330352d6f732d73656375726572616e646f6d000400086172676f6e3269640005001300" +
                "060001000000070000000300080000000100090020000102030405060708090a0b0c0d0e0f10" +
                "1112131415161718191a1b1c1d1e1f000a0040000b0010202122232425262728292a2b2c2d2e" +
                "2f000c002d756e69636f64652d6e66632d757466382d6e6f2d636f6e74726f6c732d6e6f2d77" +
                "6869746573706163652d7631000d002b736b616c642d7661756c742d76312d686b64662d7368" +
                "613235362d6b65792d657870616e73696f6e2d7631000e0027736b616c642d7661756c742d76" +
                "312d6b65792d73657061726174696f6e2d6c6162656c732d7631000f002f736b616c642d7661" +
                "756c742d76312d686d61632d7368613235362d6865616465722d636f6d6d69746d656e742d76" +
                "3100100023736b616c642d7661756c742d76312d6865616465722d636f6d6d69746d656e742d76" +
                "310011001c736b616c642d7661756c742d76312d7265636f72642d6161642d76310012000100" +
                "13001f736b616c642d7661756c742d76312d7265636f72642d666f726d61742d763100140001" +
                "00150000000000160000"
        const val EXPECTED_HEADER_INFO_HEX =
            "0001001a736b616c642d7661756c742f76312f726f6f742d646f6d61696e0002004b736b616c" +
                "642d7661756c742d76312d626f756e6379636173746c652d6172676f6e3269642d74696e6b2d" +
                "786368616368613230706f6c79313330352d6f732d73656375726572616e646f6d0003002473" +
                "6b616c642d7661756c742f76312f6865616465722d636f6d6d69746d656e742d6b6579000400" +
                "2b736b616c642d7661756c742d76312d686b64662d7368613235362d6b65792d657870616e73" +
                "696f6e2d763100050001"
        const val EXPECTED_RECORD_INFO_HEX =
            "0001001a736b616c642d7661756c742f76312f726f6f742d646f6d61696e0002004b736b616c" +
                "642d7661756c742d76312d626f756e6379636173746c652d6172676f6e3269642d74696e6b2d" +
                "786368616368613230706f6c79313330352d6f732d73656375726572616e646f6d0003001e73" +
                "6b616c642d7661756c742f76312f7265636f72642d616561642d6b65790004002b736b616c64" +
                "2d7661756c742d76312d686b64662d7368613235362d6b65792d657870616e73696f6e2d7631" +
                "00050001"
        const val EXPECTED_HEADER_COMMITMENT_KEY_HEX =
            "dc852862ead9ba057d6fb32842cff96b5dd853215fdcce2cff085383906d40fd"
        const val EXPECTED_RECORD_AEAD_KEY_HEX =
            "7634139c7f7d165280344d986374090b9124a4caa45739915c75ff2b300a0872"
        const val EXPECTED_HEADER_COMMITMENT_TAG_HEX =
            "1d09a657d8929444c1e955410b2dcb3bfc0df2d19b128154e17a5fbd751237d5"
    }
}
