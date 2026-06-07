package com.libertasprimordium.skald

import com.google.crypto.tink.aead.internal.InsecureNonceXChaCha20Poly1305
import org.bouncycastle.crypto.generators.Argon2BytesGenerator
import org.bouncycastle.crypto.params.Argon2Parameters
import kotlin.test.Test
import kotlin.test.assertContentEquals

class VaultCryptoKatValidationTest {
    @Test
    fun bouncyCastleArgon2idMatchesRfc9106PublicVector() {
        val parameters = Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
            .withVersion(Argon2Parameters.ARGON2_VERSION_13)
            .withMemoryAsKB(32)
            .withIterations(3)
            .withParallelism(4)
            .withSalt(repeatedByte(0x02, size = 16))
            .withSecret(repeatedByte(0x03, size = 8))
            .withAdditional(repeatedByte(0x04, size = 12))
            .build()
        val output = ByteArray(32)

        try {
            val generator = Argon2BytesGenerator()
            generator.init(parameters)
            generator.generateBytes(repeatedByte(0x01, size = 32), output)
        } finally {
            parameters.clear()
        }

        assertContentEquals(
            hexToBytes(
                "0d640df58d78766c08c037a34a8b53c9" +
                    "d01ef0452d75b65eb52520e96b01e659",
            ),
            output,
        )
    }

    @Test
    fun tinkXChaCha20Poly1305MatchesXChaChaDraftPublicVector() {
        val aead = InsecureNonceXChaCha20Poly1305(
            hexToBytes(
                "808182838485868788898a8b8c8d8e8f" +
                    "909192939495969798999a9b9c9d9e9f",
            ),
        )
        val nonce = hexToBytes("404142434445464748494a4b4c4d4e4f5051525354555657")
        val aad = hexToBytes("50515253c0c1c2c3c4c5c6c7")
        val plaintext = hexToBytes(
            "4c616469657320616e642047656e746c" +
                "656d656e206f662074686520636c6173" +
                "73206f66202739393a20496620492063" +
                "6f756c64206f6666657220796f75206f" +
                "6e6c79206f6e652074697020666f7220" +
                "746865206675747572652c2073756e73" +
                "637265656e20776f756c642062652069" +
                "742e",
        )
        val expectedCiphertextAndTag = hexToBytes(
            "bd6d179d3e83d43b9576579493c0e939" +
                "572a1700252bfaccbed2902c21396cbb" +
                "731c7f1b0b4aa6440bf3a82f4eda7e39" +
                "ae64c6708c54c216cb96b72e1213b452" +
                "2f8c9ba40db5d945b11b69b982c1bb9e" +
                "3f3fac2bc369488f76b2383565d3fff9" +
                "21f9664c97637da9768812f615c68b13" +
                "b52ec0875924c1c7987947deafd8780acf49",
        )

        val ciphertextAndTag = aead.encrypt(nonce, plaintext, aad)

        assertContentEquals(expectedCiphertextAndTag, ciphertextAndTag)
        assertContentEquals(plaintext, aead.decrypt(nonce, ciphertextAndTag, aad))
    }

    private fun repeatedByte(value: Int, size: Int): ByteArray =
        ByteArray(size) { value.toByte() }

    private fun hexToBytes(hex: String): ByteArray {
        require(hex.length % 2 == 0) { "hex test vector must have an even length" }
        return hex.chunked(2)
            .map { octet -> octet.toInt(radix = 16).toByte() }
            .toByteArray()
    }
}
