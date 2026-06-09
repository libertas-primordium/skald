package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.SkaldVaultV1HeaderCommitment
import com.libertasprimordium.skald.security.SkaldVaultV1RecordAadContext
import com.libertasprimordium.skald.security.SkaldVaultV1RecordAead
import com.libertasprimordium.skald.security.SkaldVaultV1RecordAeadRejectionReason
import com.libertasprimordium.skald.security.SkaldVaultV1RecordAeadResult
import com.libertasprimordium.skald.security.SkaldVaultV1RecordCiphertext
import com.libertasprimordium.skald.security.SkaldVaultV1RecordPlaintext
import com.libertasprimordium.skald.security.SkaldVaultV1RecordType
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class VaultRecordAeadBuildingBlockTest {
    @Test
    fun strictAadBytesAreDeterministicAndMatchFixtureHex() {
        val aad = acceptedAad(SkaldVaultV1RecordAead.vectorFixtureAadContext())

        assertEquals(EXPECTED_AAD_HEX, aad.toHex())
        assertEquals(495, aad.size)
    }

    @Test
    fun recordAeadRoundTripSucceedsWithoutDeterministicCiphertextAssumption() {
        val ciphertext = encrypt()
        val plaintext = decrypt(ciphertext)

        assertContentEquals(SkaldVaultV1RecordAead.VECTOR_PLAINTEXT, plaintext.bytes)
        assertFalse(ciphertext.bytes.contentEquals(SkaldVaultV1RecordAead.VECTOR_PLAINTEXT))
        assertTrue(ciphertext.bytes.size > SkaldVaultV1RecordAead.VECTOR_PLAINTEXT.size)
    }

    @Test
    fun wrongAadRecordIdentityAndHeaderContextFailClosed() {
        val ciphertext = encrypt()

        assertRejected(
            decryptResult(
                ciphertext = ciphertext.bytes,
                aadContext = SkaldVaultV1RecordAead.vectorFixtureAadContext().copy(
                    recordId = (0x50..0x5f).map { it.toByte() }.toByteArray(),
                ),
            ),
            SkaldVaultV1RecordAeadRejectionReason.AeadDecryptionFailed,
        )
        assertRejected(
            decryptResult(
                ciphertext = ciphertext.bytes,
                aadContext = SkaldVaultV1RecordAead.vectorFixtureAadContext().copy(
                    recordTypeId = SkaldVaultV1RecordType.SecretPayload.typeId,
                ),
            ),
            SkaldVaultV1RecordAeadRejectionReason.AeadDecryptionFailed,
        )
        assertRejected(
            decryptResult(
                ciphertext = ciphertext.bytes,
                aadContext = SkaldVaultV1RecordAead.vectorFixtureAadContext().copy(
                    recordVersionCounter = 8,
                ),
            ),
            SkaldVaultV1RecordAeadRejectionReason.AeadDecryptionFailed,
        )
        assertRejected(
            decryptResult(
                ciphertext = ciphertext.bytes,
                aadContext = SkaldVaultV1RecordAead.vectorFixtureAadContext().copy(
                    headerCommitmentContext = ByteArray(32) { 0x7f.toByte() },
                ),
            ),
            SkaldVaultV1RecordAeadRejectionReason.AeadDecryptionFailed,
        )
    }

    @Test
    fun wrongVaultProviderAndPoliciesFailClosed() {
        val ciphertext = encrypt()

        assertRejected(
            decryptResult(
                ciphertext = ciphertext.bytes,
                aadContext = SkaldVaultV1RecordAead.vectorFixtureAadContext().copy(
                    vaultId = (0x60..0x6f).map { it.toByte() }.toByteArray(),
                ),
            ),
            SkaldVaultV1RecordAeadRejectionReason.AeadDecryptionFailed,
        )
        assertRejected(
            SkaldVaultV1RecordAead.aadBytes(
                SkaldVaultV1RecordAead.vectorFixtureAadContext().copy(providerSuiteId = "unsupported-suite"),
            ),
            SkaldVaultV1RecordAeadRejectionReason.UnsupportedSuiteId,
        )
        assertRejected(
            SkaldVaultV1RecordAead.aadBytes(
                SkaldVaultV1RecordAead.vectorFixtureAadContext().copy(
                    aadPolicyId = "unsupported-aad-policy",
                ),
            ),
            SkaldVaultV1RecordAeadRejectionReason.UnsupportedPolicyId,
        )
        assertRejected(
            SkaldVaultV1RecordAead.aadBytes(
                SkaldVaultV1RecordAead.vectorFixtureAadContext().copy(
                    recordFormatPolicyId = "unsupported-record-format",
                ),
            ),
            SkaldVaultV1RecordAeadRejectionReason.UnsupportedPolicyId,
        )
        assertRejected(
            SkaldVaultV1RecordAead.aadBytes(
                SkaldVaultV1RecordAead.vectorFixtureAadContext().copy(
                    headerCommitmentPolicyId = "unsupported-header-policy",
                ),
            ),
            SkaldVaultV1RecordAeadRejectionReason.UnsupportedPolicyId,
        )
    }

    @Test
    fun wrongKeyTamperedCiphertextAndTamperedTagFailClosed() {
        val ciphertext = encrypt()
        val wrongKey = SkaldVaultV1RecordAead.VECTOR_RECORD_AEAD_KEY.copyOf().also { it[0] = (it[0].toInt() xor 0x01).toByte() }
        val tamperedCiphertext = ciphertext.bytes.also { it[0] = (it[0].toInt() xor 0x01).toByte() }
        val tamperedTag = ciphertext.bytes.also { it[it.lastIndex] = (it[it.lastIndex].toInt() xor 0x01).toByte() }

        assertRejected(
            decryptResult(recordAeadKey = wrongKey, ciphertext = ciphertext.bytes),
            SkaldVaultV1RecordAeadRejectionReason.AeadDecryptionFailed,
        )
        assertRejected(
            decryptResult(ciphertext = tamperedCiphertext),
            SkaldVaultV1RecordAeadRejectionReason.AeadDecryptionFailed,
        )
        assertRejected(
            decryptResult(ciphertext = tamperedTag),
            SkaldVaultV1RecordAeadRejectionReason.AeadDecryptionFailed,
        )
    }

    @Test
    fun malformedKeyAndAadInputsAreRejectedBeforeAeadUse() {
        assertRejected(
            SkaldVaultV1RecordAead.encryptRecord(
                recordAeadKey = ByteArray(31),
                plaintext = SkaldVaultV1RecordAead.VECTOR_PLAINTEXT,
                aadContext = SkaldVaultV1RecordAead.vectorFixtureAadContext(),
            ),
            SkaldVaultV1RecordAeadRejectionReason.InvalidRecordAeadKeyLength,
        )
        assertRejected(
            SkaldVaultV1RecordAead.aadBytes(
                SkaldVaultV1RecordAead.vectorFixtureAadContext().copy(recordTypeId = "unsupported-type"),
            ),
            SkaldVaultV1RecordAeadRejectionReason.UnsupportedRecordType,
        )
        assertRejected(
            SkaldVaultV1RecordAead.aadBytes(
                SkaldVaultV1RecordAead.vectorFixtureAadContext().copy(recordId = ByteArray(15)),
            ),
            SkaldVaultV1RecordAeadRejectionReason.MalformedLength,
        )
        assertRejected(
            SkaldVaultV1RecordAead.aadBytes(
                SkaldVaultV1RecordAead.vectorFixtureAadContext().copy(recordVersionCounter = -1),
            ),
            SkaldVaultV1RecordAeadRejectionReason.MalformedIntegerValue,
        )
        assertRejected(
            SkaldVaultV1RecordAead.aadBytes(
                SkaldVaultV1RecordAead.vectorFixtureAadContext().copy(
                    keyExpansionPolicyId = "unsupported-key-expansion",
                ),
            ),
            SkaldVaultV1RecordAeadRejectionReason.UnsupportedPolicyId,
        )
        assertRejected(
            SkaldVaultV1RecordAead.aadBytes(
                SkaldVaultV1RecordAead.vectorFixtureAadContext().copy(
                    headerCommitmentPrimitivePolicyId = "unsupported-header-primitive",
                ),
            ),
            SkaldVaultV1RecordAeadRejectionReason.UnsupportedPolicyId,
        )
        assertRejected(
            SkaldVaultV1RecordAead.aadBytes(
                SkaldVaultV1RecordAead.vectorFixtureAadContext().copy(vaultMagic = "SKALD\nVAULT"),
            ),
            SkaldVaultV1RecordAeadRejectionReason.UnsupportedPolicyId,
        )
        assertRejected(
            SkaldVaultV1RecordAead.aadBytes(
                SkaldVaultV1RecordAead.vectorFixtureAadContext().copy(storageNamespace = "skald-vault\nrecords"),
            ),
            SkaldVaultV1RecordAeadRejectionReason.MalformedStringValue,
        )
        assertRejected(
            SkaldVaultV1RecordAead.aadBytes(
                SkaldVaultV1RecordAead.vectorFixtureAadContext().copy(vaultFormatVersion = 2),
            ),
            SkaldVaultV1RecordAeadRejectionReason.UnsupportedVersion,
        )
    }

    private fun encrypt(): SkaldVaultV1RecordCiphertext =
        acceptedCiphertext(
            SkaldVaultV1RecordAead.encryptRecord(
                recordAeadKey = SkaldVaultV1RecordAead.VECTOR_RECORD_AEAD_KEY,
                plaintext = SkaldVaultV1RecordAead.VECTOR_PLAINTEXT,
                aadContext = SkaldVaultV1RecordAead.vectorFixtureAadContext(),
            ),
        )

    private fun decrypt(ciphertext: SkaldVaultV1RecordCiphertext): SkaldVaultV1RecordPlaintext =
        acceptedPlaintext(decryptResult(ciphertext = ciphertext.bytes))

    private fun decryptResult(
        recordAeadKey: ByteArray = SkaldVaultV1RecordAead.VECTOR_RECORD_AEAD_KEY,
        ciphertext: ByteArray,
        aadContext: SkaldVaultV1RecordAadContext = SkaldVaultV1RecordAead.vectorFixtureAadContext(),
    ): SkaldVaultV1RecordAeadResult<SkaldVaultV1RecordPlaintext> =
        SkaldVaultV1RecordAead.decryptRecord(
            recordAeadKey = recordAeadKey,
            ciphertext = ciphertext,
            aadContext = aadContext,
        )

    private fun acceptedAad(context: SkaldVaultV1RecordAadContext): ByteArray =
        when (val result = SkaldVaultV1RecordAead.aadBytes(context)) {
            is SkaldVaultV1RecordAeadResult.Accepted -> result.value
            is SkaldVaultV1RecordAeadResult.Rejected -> error(result.safeMessage)
        }

    private fun acceptedCiphertext(
        result: SkaldVaultV1RecordAeadResult<SkaldVaultV1RecordCiphertext>,
    ): SkaldVaultV1RecordCiphertext =
        when (result) {
            is SkaldVaultV1RecordAeadResult.Accepted -> result.value
            is SkaldVaultV1RecordAeadResult.Rejected -> error(result.safeMessage)
        }

    private fun acceptedPlaintext(
        result: SkaldVaultV1RecordAeadResult<SkaldVaultV1RecordPlaintext>,
    ): SkaldVaultV1RecordPlaintext =
        when (result) {
            is SkaldVaultV1RecordAeadResult.Accepted -> result.value
            is SkaldVaultV1RecordAeadResult.Rejected -> error(result.safeMessage)
        }

    private fun assertRejected(
        result: SkaldVaultV1RecordAeadResult<*>,
        reason: SkaldVaultV1RecordAeadRejectionReason,
    ) {
        assertTrue(result is SkaldVaultV1RecordAeadResult.Rejected)
        assertEquals(reason, result.reason)
        assertFalse(result.safeMessage.contains(EXCLUDED_FIXTURE_TEXT))
    }

    private fun ByteArray.toHex(): String =
        joinToString(separator = "") { byte -> byte.toUByte().toString(16).padStart(2, '0') }

    private companion object {
        const val EXCLUDED_FIXTURE_TEXT = "skald vault v1 fixed non-secret record plaintext"
        const val EXPECTED_AAD_HEX =
            "0001000e534b414c442d5641554c542d5631000200010003004b736b616c642d7661756c742d76312d" +
                "626f756e6379636173746c652d6172676f6e3269642d74696e6b2d786368616368613230706f6c79313330" +
                "352d6f732d73656375726572616e646f6d00040010202122232425262728292a2b2c2d2e2f0005001f73" +
                "6b616c642d7661756c742d76312d7265636f72642d666f726d61742d7631000600010007001c736b616c" +
                "642d7661756c742d76312d7265636f72642d6161642d7631000800010009002b736b616c642d7661756c" +
                "742d76312d686b64662d7368613235362d6b65792d657870616e73696f6e2d7631000a002f736b616c64" +
                "2d7661756c742d76312d686d61632d7368613235362d6865616465722d636f6d6d69746d656e742d7631" +
                "000b0023736b616c642d7661756c742d76312d6865616465722d636f6d6d69746d656e742d7631000c00" +
                "201d09a657d8929444c1e955410b2dcb3bfc0df2d19b128154e17a5fbd751237d5000d001273656e7369" +
                "746976652d6d65746164617461000e0010404142434445464748494a4b4c4d4e4f000f00000000000000" +
                "0700100026736b616c642d7661756c742d76312d7265636f72642d6d657461646174612d666978747572" +
                "650011001c736b616c642d7661756c742f76312f6c6f63616c2d7265636f726473"
    }
}
