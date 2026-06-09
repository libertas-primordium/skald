package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.SkaldVaultV1HeaderCommitment
import com.libertasprimordium.skald.security.SkaldVaultV1HeaderCommitmentRejectionReason
import com.libertasprimordium.skald.security.SkaldVaultV1HeaderCommitmentResult
import com.libertasprimordium.skald.security.SkaldVaultV1KeyPurpose
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class VaultCanonicalHeaderHkdfHmacVectorTest {
    @Test
    fun productionSourceCanonicalHeaderSerializerMatchesDocumentedHex() {
        val canonicalHeader = acceptedValue(
            SkaldVaultV1HeaderCommitment.canonicalHeaderBytes(
                SkaldVaultV1HeaderCommitment.vectorFixtureHeader(),
            ),
        )

        assertEquals(EXPECTED_CANONICAL_HEADER_HEX, canonicalHeader.toHex())
        assertEquals(509, canonicalHeader.size)
    }

    @Test
    fun productionSourceHkdfInfoFixturesMatchDocumentedHex() {
        assertEquals(
            EXPECTED_HEADER_INFO_HEX,
            acceptedValue(
                SkaldVaultV1HeaderCommitment.hkdfInfoBytes(
                    SkaldVaultV1KeyPurpose.HeaderCommitment,
                ),
            ).toHex(),
        )
        assertEquals(
            EXPECTED_RECORD_INFO_HEX,
            acceptedValue(
                SkaldVaultV1HeaderCommitment.hkdfInfoBytes(
                    SkaldVaultV1KeyPurpose.RecordAead,
                ),
            ).toHex(),
        )
    }

    @Test
    fun productionSourceHkdfSha256VectorsMatchDocumentedOutputs() {
        val expandedKeys = acceptedValue(
            SkaldVaultV1HeaderCommitment.expandRootMaterial(
                rootMaterial = ROOT_MATERIAL_FIXTURE,
                header = SkaldVaultV1HeaderCommitment.vectorFixtureHeader(),
            ),
        )

        assertEquals(EXPECTED_HEADER_COMMITMENT_KEY_HEX, expandedKeys.headerCommitmentKey.toHex())
        assertEquals(EXPECTED_RECORD_AEAD_KEY_HEX, expandedKeys.recordAeadKey.toHex())
    }

    @Test
    fun expandedKeysReturnDefensiveCopies() {
        val expandedKeys = acceptedValue(
            SkaldVaultV1HeaderCommitment.expandRootMaterial(
                rootMaterial = ROOT_MATERIAL_FIXTURE,
                header = SkaldVaultV1HeaderCommitment.vectorFixtureHeader(),
            ),
        )
        val firstRead = expandedKeys.headerCommitmentKey

        firstRead[0] = (firstRead[0].toInt() xor 0xff).toByte()

        assertEquals(EXPECTED_HEADER_COMMITMENT_KEY_HEX, expandedKeys.headerCommitmentKey.toHex())
    }

    @Test
    fun productionSourceHmacSha256HeaderCommitmentVectorMatchesDocumentedOutput() {
        val header = SkaldVaultV1HeaderCommitment.vectorFixtureHeader()
        val canonicalHeader = acceptedValue(SkaldVaultV1HeaderCommitment.canonicalHeaderBytes(header))
        val expandedKeys = acceptedValue(
            SkaldVaultV1HeaderCommitment.expandRootMaterial(
                rootMaterial = ROOT_MATERIAL_FIXTURE,
                header = header,
            ),
        )
        val tag = acceptedValue(
            SkaldVaultV1HeaderCommitment.computeHeaderCommitment(
                headerCommitmentKey = expandedKeys.headerCommitmentKey,
                canonicalHeaderBytes = canonicalHeader,
            ),
        )

        assertEquals(EXPECTED_HEADER_COMMITMENT_TAG_HEX, tag.toHex())
        assertTrue(
            acceptedValue(
                SkaldVaultV1HeaderCommitment.verifyHeaderCommitment(
                    headerCommitmentKey = expandedKeys.headerCommitmentKey,
                    canonicalHeaderBytes = canonicalHeader,
                    expectedTag = tag,
                ),
            ),
        )
        assertTrue(
            acceptedValue(
                SkaldVaultV1HeaderCommitment.verifyHeaderCommitment(
                    header = header,
                    rootMaterial = ROOT_MATERIAL_FIXTURE,
                    expectedTag = tag,
                ),
            ),
        )
    }

    @Test
    fun headerCommitmentVerificationFailsClosedForWrongHeaderKeyAndTag() {
        val header = SkaldVaultV1HeaderCommitment.vectorFixtureHeader()
        val canonicalHeader = acceptedValue(SkaldVaultV1HeaderCommitment.canonicalHeaderBytes(header))
        val expandedKeys = acceptedValue(
            SkaldVaultV1HeaderCommitment.expandRootMaterial(
                rootMaterial = ROOT_MATERIAL_FIXTURE,
                header = header,
            ),
        )
        val tag = acceptedValue(
            SkaldVaultV1HeaderCommitment.computeHeaderCommitment(
                headerCommitmentKey = expandedKeys.headerCommitmentKey,
                canonicalHeaderBytes = canonicalHeader,
            ),
        )
        val wrongHeader = canonicalHeader.copyOf().also { bytes ->
            bytes[bytes.lastIndex] = (bytes.last().toInt() xor 0x01).toByte()
        }
        val wrongTag = tag.copyOf().also { bytes ->
            bytes[bytes.lastIndex] = (bytes.last().toInt() xor 0x01).toByte()
        }
        val wrongKey = expandedKeys.headerCommitmentKey.also { bytes ->
            bytes[bytes.lastIndex] = (bytes.last().toInt() xor 0x01).toByte()
        }
        val wrongRootMaterial = ROOT_MATERIAL_FIXTURE.copyOf().also { bytes ->
            bytes[bytes.lastIndex] = (bytes.last().toInt() xor 0x01).toByte()
        }

        assertFalse(
            acceptedValue(
                SkaldVaultV1HeaderCommitment.verifyHeaderCommitment(
                    headerCommitmentKey = expandedKeys.headerCommitmentKey,
                    canonicalHeaderBytes = wrongHeader,
                    expectedTag = tag,
                ),
            ),
        )
        assertFalse(
            acceptedValue(
                SkaldVaultV1HeaderCommitment.verifyHeaderCommitment(
                    headerCommitmentKey = wrongKey,
                    canonicalHeaderBytes = canonicalHeader,
                    expectedTag = tag,
                ),
            ),
        )
        assertFalse(
            acceptedValue(
                SkaldVaultV1HeaderCommitment.verifyHeaderCommitment(
                    headerCommitmentKey = expandedKeys.headerCommitmentKey,
                    canonicalHeaderBytes = canonicalHeader,
                    expectedTag = wrongTag,
                ),
            ),
        )
        assertFalse(
            acceptedValue(
                SkaldVaultV1HeaderCommitment.verifyHeaderCommitment(
                    header = header,
                    rootMaterial = wrongRootMaterial,
                    expectedTag = tag,
                ),
            ),
        )
    }

    @Test
    fun invalidInputLengthsAreRejected() {
        val header = SkaldVaultV1HeaderCommitment.vectorFixtureHeader()
        val canonicalHeader = acceptedValue(SkaldVaultV1HeaderCommitment.canonicalHeaderBytes(header))
        val validKey = EXPECTED_HEADER_COMMITMENT_KEY_HEX.hexToBytes()
        val validTag = EXPECTED_HEADER_COMMITMENT_TAG_HEX.hexToBytes()

        assertEquals(
            SkaldVaultV1HeaderCommitmentRejectionReason.InvalidRootMaterialLength,
            rejectedReason(
                SkaldVaultV1HeaderCommitment.expandRootMaterial(
                    rootMaterial = ROOT_MATERIAL_FIXTURE.copyOf(63),
                    header = header,
                ),
            ),
        )
        assertEquals(
            SkaldVaultV1HeaderCommitmentRejectionReason.InvalidHeaderCommitmentKeyLength,
            rejectedReason(
                SkaldVaultV1HeaderCommitment.computeHeaderCommitment(
                    headerCommitmentKey = validKey.copyOf(31),
                    canonicalHeaderBytes = canonicalHeader,
                ),
            ),
        )
        assertEquals(
            SkaldVaultV1HeaderCommitmentRejectionReason.InvalidHeaderCommitmentKeyLength,
            rejectedReason(
                SkaldVaultV1HeaderCommitment.verifyHeaderCommitment(
                    headerCommitmentKey = validKey.copyOf(31),
                    canonicalHeaderBytes = canonicalHeader,
                    expectedTag = validTag,
                ),
            ),
        )
        assertEquals(
            SkaldVaultV1HeaderCommitmentRejectionReason.InvalidHeaderCommitmentTagLength,
            rejectedReason(
                SkaldVaultV1HeaderCommitment.verifyHeaderCommitment(
                    headerCommitmentKey = validKey,
                    canonicalHeaderBytes = canonicalHeader,
                    expectedTag = validTag.copyOf(31),
                ),
            ),
        )
    }

    @Test
    fun unsupportedPurposePolicyAndSuiteAreRejected() {
        val header = SkaldVaultV1HeaderCommitment.vectorFixtureHeader()

        assertEquals(
            SkaldVaultV1HeaderCommitmentRejectionReason.UnsupportedKeyPurpose,
            rejectedReason(
                SkaldVaultV1HeaderCommitment.expandRootMaterialForPurpose(
                    rootMaterial = ROOT_MATERIAL_FIXTURE,
                    header = header,
                    purposeLabel = "skald-vault/v1/unsupported-purpose",
                ),
            ),
        )
        assertEquals(
            SkaldVaultV1HeaderCommitmentRejectionReason.UnsupportedPolicyId,
            rejectedReason(
                SkaldVaultV1HeaderCommitment.canonicalHeaderBytes(
                    header.copy(
                        keyExpansionPolicyId =
                            "skald-vault-v1-unsupported-key-expansion-policy",
                    ),
                ),
            ),
        )
        assertEquals(
            SkaldVaultV1HeaderCommitmentRejectionReason.UnsupportedSuiteId,
            rejectedReason(
                SkaldVaultV1HeaderCommitment.canonicalHeaderBytes(
                    header.copy(providerSuiteId = "skald-vault-v1-unsupported-suite"),
                ),
            ),
        )
    }

    @Test
    fun malformedHeaderEvidenceIsRejectedBeforeCommitmentUse() {
        val header = SkaldVaultV1HeaderCommitment.vectorFixtureHeader()

        assertEquals(
            SkaldVaultV1HeaderCommitmentRejectionReason.MalformedLength,
            rejectedReason(
                SkaldVaultV1HeaderCommitment.canonicalHeaderBytes(
                    header.copy(salt = ByteArray(15)),
                ),
            ),
        )
        assertEquals(
            SkaldVaultV1HeaderCommitmentRejectionReason.MalformedStringValue,
            rejectedReason(
                SkaldVaultV1HeaderCommitment.canonicalHeaderBytes(
                    header.copy(aadPolicyId = "skald-vault-v1-record-aad-v1\n"),
                ),
            ),
        )
    }

    private fun <T> acceptedValue(
        result: SkaldVaultV1HeaderCommitmentResult<T>,
    ): T =
        when (result) {
            is SkaldVaultV1HeaderCommitmentResult.Accepted -> result.value
            is SkaldVaultV1HeaderCommitmentResult.Rejected -> error(result.safeMessage)
        }

    private fun rejectedReason(
        result: SkaldVaultV1HeaderCommitmentResult<*>,
    ): SkaldVaultV1HeaderCommitmentRejectionReason =
        when (result) {
            is SkaldVaultV1HeaderCommitmentResult.Accepted ->
                error("Expected rejection but received an accepted result.")
            is SkaldVaultV1HeaderCommitmentResult.Rejected -> result.reason
        }

    private fun ByteArray.toHex(): String =
        joinToString(separator = "") { byte ->
            (byte.toInt() and 0xff).toString(radix = 16).padStart(2, '0')
        }

    private fun String.hexToBytes(): ByteArray {
        require(length % 2 == 0)
        return chunked(2)
            .map { it.toInt(radix = 16).toByte() }
            .toByteArray()
    }

    private companion object {
        val ROOT_MATERIAL_FIXTURE: ByteArray = (0xa0..0xdf).map { it.toByte() }.toByteArray()

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
