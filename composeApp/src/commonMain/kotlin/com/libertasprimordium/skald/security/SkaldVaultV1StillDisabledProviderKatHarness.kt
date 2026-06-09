package com.libertasprimordium.skald.security

enum class SkaldVaultV1ProviderKatStage(val label: String) {
    PassphrasePolicyValidated("passphrase policy validated"),
    Argon2idRootMaterialDerived("Argon2id root material derived"),
    HkdfSubkeysDerived("HKDF subkeys derived"),
    CanonicalHeaderSerialized("canonical header serialized"),
    HeaderCommitmentVerified("header commitment verified"),
    StrictAadSerialized("strict AAD serialized"),
    RecordAeadRoundTripCompleted("record AEAD round trip completed"),
    RecordAeadNegativeChecksCompleted("record AEAD negative checks completed"),
}

enum class SkaldVaultV1ProviderKatFailureReason(val label: String) {
    PassphrasePolicyRejected("passphrase policy rejected"),
    Argon2idRootDerivationRejected("Argon2id root derivation rejected"),
    HeaderCommitmentInputRejected("header commitment input rejected"),
    HeaderCommitmentVerificationFailed("header commitment verification failed"),
    StrictAadRejected("strict AAD rejected"),
    DeterministicVectorMismatch("deterministic vector mismatch"),
    RecordAeadRejected("record AEAD rejected"),
    RecordAeadUnexpectedlyAccepted("record AEAD unexpectedly accepted"),
    RecordPlaintextMismatch("record plaintext mismatch"),
}

sealed class SkaldVaultV1ProviderKatHarnessResult<out T> {
    data class Accepted<out T>(val value: T) : SkaldVaultV1ProviderKatHarnessResult<T>()

    data class Rejected(
        val reason: SkaldVaultV1ProviderKatFailureReason,
        val safeMessage: String,
        val stagesCompleted: List<SkaldVaultV1ProviderKatStage>,
        val recordAeadStageReached: Boolean,
    ) : SkaldVaultV1ProviderKatHarnessResult<Nothing>()
}

data class SkaldVaultV1ProviderKatExpectedVectors(
    val normalizedPassphraseUtf8Hex: String,
    val argon2idRootMaterialHex: String,
    val hkdfHeaderCommitmentInfoHex: String,
    val hkdfRecordAeadInfoHex: String,
    val hkdfHeaderCommitmentKeyHex: String,
    val hkdfRecordAeadKeyHex: String,
    val canonicalHeaderHex: String,
    val hmacHeaderCommitmentTagHex: String,
    val strictAadHex: String,
)

data class SkaldVaultV1ProviderKatRequest(
    val passphrase: String,
    val header: SkaldVaultV1CanonicalHeader,
    val argon2idParameters: SkaldVaultV1Argon2idParameters,
    val expectedHeaderCommitmentTag: ByteArray,
    val aadContext: SkaldVaultV1RecordAadContext,
    val plaintext: ByteArray,
    val expectedVectors: SkaldVaultV1ProviderKatExpectedVectors,
    val enforceDeterministicVectorMatches: Boolean = true,
) {
    fun withExpectedHeaderCommitmentTag(tag: ByteArray): SkaldVaultV1ProviderKatRequest =
        copy(expectedHeaderCommitmentTag = tag.copyOf())

    fun withPlaintext(bytes: ByteArray): SkaldVaultV1ProviderKatRequest =
        copy(plaintext = bytes.copyOf())
}

data class SkaldVaultV1ProviderKatEvidence(
    val stagesCompleted: List<SkaldVaultV1ProviderKatStage>,
    val deterministicVectorsMatched: Set<ProductionProviderDeterministicKatVector>,
    val randomizedAeadBehavioralChecksPassed: Set<ProductionProviderRandomizedAeadBehavioralKatCheck>,
    val headerCommitmentVerifiedBeforeRecordAead: Boolean,
    val recordAeadStageReached: Boolean,
    val ciphertextTreatedAsDeterministic: Boolean,
    val productionProviderSelectable: Boolean,
    val vaultCreationEnabled: Boolean,
    val vaultPersistenceEnabled: Boolean,
    val manifestStorageImplemented: Boolean,
    val secureStorageEnabled: Boolean,
)

interface SkaldVaultV1ProviderKatRecordAeadAdapter {
    fun sealRecord(
        recordAeadKey: ByteArray,
        plaintext: ByteArray,
        aadContext: SkaldVaultV1RecordAadContext,
    ): SkaldVaultV1RecordAeadResult<SkaldVaultV1RecordCiphertext>

    fun openRecord(
        recordAeadKey: ByteArray,
        ciphertext: ByteArray,
        aadContext: SkaldVaultV1RecordAadContext,
    ): SkaldVaultV1RecordAeadResult<SkaldVaultV1RecordPlaintext>
}

object SkaldVaultV1ProviderKatRecordAeadBuildingBlock : SkaldVaultV1ProviderKatRecordAeadAdapter {
    override fun sealRecord(
        recordAeadKey: ByteArray,
        plaintext: ByteArray,
        aadContext: SkaldVaultV1RecordAadContext,
    ): SkaldVaultV1RecordAeadResult<SkaldVaultV1RecordCiphertext> =
        SkaldVaultV1RecordAead.encryptRecord(
            recordAeadKey = recordAeadKey.copyOf(),
            plaintext = plaintext.copyOf(),
            aadContext = aadContext,
        )

    override fun openRecord(
        recordAeadKey: ByteArray,
        ciphertext: ByteArray,
        aadContext: SkaldVaultV1RecordAadContext,
    ): SkaldVaultV1RecordAeadResult<SkaldVaultV1RecordPlaintext> =
        SkaldVaultV1RecordAead.decryptRecord(
            recordAeadKey = recordAeadKey.copyOf(),
            ciphertext = ciphertext.copyOf(),
            aadContext = aadContext,
        )
}

object SkaldVaultV1ProviderKatFixtures {
    const val FIXED_NON_SECRET_PASSPHRASE = "Skald-Vault.Test_Fixture-01"
    const val NORMALIZED_PASSPHRASE_UTF8_HEX =
        "536b616c642d5661756c742e546573745f466978747572652d3031"
    const val ARGON2ID_ROOT_MATERIAL_HEX =
        "36686ff5939587fce8eafdc430767fa36427ecc80b7eca0ac050fc3813fe754a" +
            "982187d6315ae1e779d6479f486e9a3ec99c059371477497464302dc9167cff7"
    const val HKDF_HEADER_COMMITMENT_KEY_HEX =
        "eddd7c105c3fa05b99e4ae5c7e89da5599afa9e815a4c492e716bf2837621b51"
    const val HKDF_RECORD_AEAD_KEY_HEX =
        "cea1e452f8d32adbcb790a995a410cdc739db8b85be70f90cbafd1ce35a4aa99"
    const val HKDF_HEADER_COMMITMENT_INFO_HEX =
        "0001001a736b616c642d7661756c742f76312f726f6f742d646f6d61696e0002004b736b616c" +
            "642d7661756c742d76312d626f756e6379636173746c652d6172676f6e3269642d74696e6b2d" +
            "786368616368613230706f6c79313330352d6f732d73656375726572616e646f6d0003002473" +
            "6b616c642d7661756c742f76312f6865616465722d636f6d6d69746d656e742d6b6579000400" +
            "2b736b616c642d7661756c742d76312d686b64662d7368613235362d6b65792d657870616e73" +
            "696f6e2d763100050001"
    const val HKDF_RECORD_AEAD_INFO_HEX =
        "0001001a736b616c642d7661756c742f76312f726f6f742d646f6d61696e0002004b736b616c" +
            "642d7661756c742d76312d626f756e6379636173746c652d6172676f6e3269642d74696e6b2d" +
            "786368616368613230706f6c79313330352d6f732d73656375726572616e646f6d0003001e73" +
            "6b616c642d7661756c742f76312f7265636f72642d616561642d6b65790004002b736b616c64" +
            "2d7661756c742d76312d686b64662d7368613235362d6b65792d657870616e73696f6e2d7631" +
            "00050001"
    const val HMAC_HEADER_COMMITMENT_TAG_HEX =
        "ca05712299343dad10391c938168e5121f916c1cbbcb04c006f3156890167a36"
    const val CANONICAL_HEADER_HEX =
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
    const val STRICT_AAD_HEX =
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

    fun request(): SkaldVaultV1ProviderKatRequest =
        SkaldVaultV1ProviderKatRequest(
            passphrase = FIXED_NON_SECRET_PASSPHRASE,
            header = SkaldVaultV1HeaderCommitment.vectorFixtureHeader(),
            argon2idParameters = SkaldVaultV1Argon2idParameters(),
            expectedHeaderCommitmentTag = HMAC_HEADER_COMMITMENT_TAG_HEX.hexToBytes(),
            aadContext = SkaldVaultV1RecordAead.vectorFixtureAadContext(),
            plaintext = SkaldVaultV1RecordAead.VECTOR_PLAINTEXT,
            expectedVectors = SkaldVaultV1ProviderKatExpectedVectors(
                normalizedPassphraseUtf8Hex = NORMALIZED_PASSPHRASE_UTF8_HEX,
                argon2idRootMaterialHex = ARGON2ID_ROOT_MATERIAL_HEX,
                hkdfHeaderCommitmentInfoHex = HKDF_HEADER_COMMITMENT_INFO_HEX,
                hkdfRecordAeadInfoHex = HKDF_RECORD_AEAD_INFO_HEX,
                hkdfHeaderCommitmentKeyHex = HKDF_HEADER_COMMITMENT_KEY_HEX,
                hkdfRecordAeadKeyHex = HKDF_RECORD_AEAD_KEY_HEX,
                canonicalHeaderHex = CANONICAL_HEADER_HEX,
                hmacHeaderCommitmentTagHex = HMAC_HEADER_COMMITMENT_TAG_HEX,
                strictAadHex = STRICT_AAD_HEX,
            ),
        )
}

class SkaldVaultV1StillDisabledProviderKatHarness(
    private val recordAead: SkaldVaultV1ProviderKatRecordAeadAdapter =
        SkaldVaultV1ProviderKatRecordAeadBuildingBlock,
) {
    fun runProviderLevelKat(
        request: SkaldVaultV1ProviderKatRequest = SkaldVaultV1ProviderKatFixtures.request(),
    ): SkaldVaultV1ProviderKatHarnessResult<SkaldVaultV1ProviderKatEvidence> {
        val stages = mutableListOf<SkaldVaultV1ProviderKatStage>()
        val deterministicVectors = mutableSetOf<ProductionProviderDeterministicKatVector>()
        val randomizedChecks = mutableSetOf<ProductionProviderRandomizedAeadBehavioralKatCheck>()

        val normalized = when (val result = SkaldVaultV1PassphrasePolicy.normalizeAndEncode(request.passphrase)) {
            is SkaldVaultV1PassphrasePolicyResult.Accepted -> result.value
            is SkaldVaultV1PassphrasePolicyResult.Rejected ->
                return rejected(SkaldVaultV1ProviderKatFailureReason.PassphrasePolicyRejected, stages)
        }
        stages += SkaldVaultV1ProviderKatStage.PassphrasePolicyValidated
        if (
            !matchVector(
                request,
                deterministicVectors,
                ProductionProviderDeterministicKatVector.PassphrasePolicyNormalizationVector,
                normalized.utf8Bytes,
                request.expectedVectors.normalizedPassphraseUtf8Hex,
            )
        ) {
            return rejected(SkaldVaultV1ProviderKatFailureReason.DeterministicVectorMismatch, stages)
        }

        val rootMaterial = when (
            val result = SkaldVaultV1Argon2idRootDerivation.deriveRootMaterial(
                normalizedPassphrase = normalized,
                salt = request.header.salt,
                parameters = request.argon2idParameters,
            )
        ) {
            is SkaldVaultV1Argon2idRootDerivationResult.Accepted -> result.value
            is SkaldVaultV1Argon2idRootDerivationResult.Rejected ->
                return rejected(SkaldVaultV1ProviderKatFailureReason.Argon2idRootDerivationRejected, stages)
        }
        stages += SkaldVaultV1ProviderKatStage.Argon2idRootMaterialDerived
        if (
            !matchVector(
                request,
                deterministicVectors,
                ProductionProviderDeterministicKatVector.Argon2idRootMaterialFixture,
                rootMaterial.bytes,
                request.expectedVectors.argon2idRootMaterialHex,
            )
        ) {
            return rejected(SkaldVaultV1ProviderKatFailureReason.DeterministicVectorMismatch, stages)
        }

        val expandedKeys = when (
            val result = SkaldVaultV1HeaderCommitment.expandRootMaterial(
                rootMaterial = rootMaterial.bytes,
                header = request.header,
            )
        ) {
            is SkaldVaultV1HeaderCommitmentResult.Accepted -> result.value
            is SkaldVaultV1HeaderCommitmentResult.Rejected ->
                return rejected(SkaldVaultV1ProviderKatFailureReason.HeaderCommitmentInputRejected, stages)
        }
        stages += SkaldVaultV1ProviderKatStage.HkdfSubkeysDerived
        val headerInfo = when (
            val result = SkaldVaultV1HeaderCommitment.hkdfInfoBytes(SkaldVaultV1KeyPurpose.HeaderCommitment)
        ) {
            is SkaldVaultV1HeaderCommitmentResult.Accepted -> result.value
            is SkaldVaultV1HeaderCommitmentResult.Rejected ->
                return rejected(SkaldVaultV1ProviderKatFailureReason.HeaderCommitmentInputRejected, stages)
        }
        val recordInfo = when (
            val result = SkaldVaultV1HeaderCommitment.hkdfInfoBytes(SkaldVaultV1KeyPurpose.RecordAead)
        ) {
            is SkaldVaultV1HeaderCommitmentResult.Accepted -> result.value
            is SkaldVaultV1HeaderCommitmentResult.Rejected ->
                return rejected(SkaldVaultV1ProviderKatFailureReason.HeaderCommitmentInputRejected, stages)
        }
        if (
            request.enforceDeterministicVectorMatches &&
            (
                headerInfo.toHex() != request.expectedVectors.hkdfHeaderCommitmentInfoHex ||
                    recordInfo.toHex() != request.expectedVectors.hkdfRecordAeadInfoHex
            )
        ) {
            return rejected(SkaldVaultV1ProviderKatFailureReason.DeterministicVectorMismatch, stages)
        }
        if (request.enforceDeterministicVectorMatches) {
            deterministicVectors += ProductionProviderDeterministicKatVector.HkdfInfoByteVectors
        }
        if (
            !matchVector(
                request,
                deterministicVectors,
                ProductionProviderDeterministicKatVector.HkdfHeaderCommitmentKeyVector,
                expandedKeys.headerCommitmentKey,
                request.expectedVectors.hkdfHeaderCommitmentKeyHex,
            ) ||
            !matchVector(
                request,
                deterministicVectors,
                ProductionProviderDeterministicKatVector.HkdfRecordAeadKeyVector,
                expandedKeys.recordAeadKey,
                request.expectedVectors.hkdfRecordAeadKeyHex,
            )
        ) {
            return rejected(SkaldVaultV1ProviderKatFailureReason.DeterministicVectorMismatch, stages)
        }

        val canonicalHeader = when (
            val result = SkaldVaultV1HeaderCommitment.canonicalHeaderBytes(request.header)
        ) {
            is SkaldVaultV1HeaderCommitmentResult.Accepted -> result.value
            is SkaldVaultV1HeaderCommitmentResult.Rejected ->
                return rejected(SkaldVaultV1ProviderKatFailureReason.HeaderCommitmentInputRejected, stages)
        }
        stages += SkaldVaultV1ProviderKatStage.CanonicalHeaderSerialized
        if (
            !matchVector(
                request,
                deterministicVectors,
                ProductionProviderDeterministicKatVector.CanonicalHeaderByteVector,
                canonicalHeader,
                request.expectedVectors.canonicalHeaderHex,
            )
        ) {
            return rejected(SkaldVaultV1ProviderKatFailureReason.DeterministicVectorMismatch, stages)
        }

        val headerCommitmentVerified = when (
            val result = SkaldVaultV1HeaderCommitment.verifyHeaderCommitment(
                headerCommitmentKey = expandedKeys.headerCommitmentKey,
                canonicalHeaderBytes = canonicalHeader,
                expectedTag = request.expectedHeaderCommitmentTag.copyOf(),
            )
        ) {
            is SkaldVaultV1HeaderCommitmentResult.Accepted -> result.value
            is SkaldVaultV1HeaderCommitmentResult.Rejected ->
                return rejected(SkaldVaultV1ProviderKatFailureReason.HeaderCommitmentInputRejected, stages)
        }
        if (!headerCommitmentVerified) {
            return rejected(SkaldVaultV1ProviderKatFailureReason.HeaderCommitmentVerificationFailed, stages)
        }
        stages += SkaldVaultV1ProviderKatStage.HeaderCommitmentVerified
        if (
            !matchVector(
                request,
                deterministicVectors,
                ProductionProviderDeterministicKatVector.HmacHeaderCommitmentVector,
                request.expectedHeaderCommitmentTag,
                request.expectedVectors.hmacHeaderCommitmentTagHex,
            )
        ) {
            return rejected(SkaldVaultV1ProviderKatFailureReason.DeterministicVectorMismatch, stages)
        }

        val aadBytes = when (val result = SkaldVaultV1RecordAead.aadBytes(request.aadContext)) {
            is SkaldVaultV1RecordAeadResult.Accepted -> result.value
            is SkaldVaultV1RecordAeadResult.Rejected ->
                return rejected(SkaldVaultV1ProviderKatFailureReason.StrictAadRejected, stages)
        }
        stages += SkaldVaultV1ProviderKatStage.StrictAadSerialized
        if (
            !matchVector(
                request,
                deterministicVectors,
                ProductionProviderDeterministicKatVector.StrictAadByteVector,
                aadBytes,
                request.expectedVectors.strictAadHex,
            )
        ) {
            return rejected(SkaldVaultV1ProviderKatFailureReason.DeterministicVectorMismatch, stages)
        }

        val ciphertext = when (
            val result = recordAead.sealRecord(
                recordAeadKey = expandedKeys.recordAeadKey,
                plaintext = request.plaintext,
                aadContext = request.aadContext,
            )
        ) {
            is SkaldVaultV1RecordAeadResult.Accepted -> result.value
            is SkaldVaultV1RecordAeadResult.Rejected ->
                return rejected(
                    reason = SkaldVaultV1ProviderKatFailureReason.RecordAeadRejected,
                    stages = stages,
                    recordAeadStageReached = true,
                )
        }
        val plaintext = when (
            val result = recordAead.openRecord(
                recordAeadKey = expandedKeys.recordAeadKey,
                ciphertext = ciphertext.bytes,
                aadContext = request.aadContext,
            )
        ) {
            is SkaldVaultV1RecordAeadResult.Accepted -> result.value
            is SkaldVaultV1RecordAeadResult.Rejected ->
                return rejected(
                    reason = SkaldVaultV1ProviderKatFailureReason.RecordAeadRejected,
                    stages = stages,
                    recordAeadStageReached = true,
                )
        }
        if (!plaintext.bytes.contentEquals(request.plaintext)) {
            return rejected(
                reason = SkaldVaultV1ProviderKatFailureReason.RecordPlaintextMismatch,
                stages = stages,
                recordAeadStageReached = true,
            )
        }
        stages += SkaldVaultV1ProviderKatStage.RecordAeadRoundTripCompleted
        randomizedChecks += ProductionProviderRandomizedAeadBehavioralKatCheck.EncryptDecryptRoundTrip
        randomizedChecks += ProductionProviderRandomizedAeadBehavioralKatCheck.CiphertextNotTreatedAsDeterministic

        randomizedChecks += requireAeadRejection(
            key = expandedKeys.recordAeadKey,
            ciphertext = ciphertext.bytes,
            aadContext = request.aadContext.copy(
                integrityCriticalRecordMetadata = "wrong-aad-fixture".encodeToByteArray(),
            ),
            check = ProductionProviderRandomizedAeadBehavioralKatCheck.WrongAadFails,
        ) ?: return rejected(
            SkaldVaultV1ProviderKatFailureReason.RecordAeadUnexpectedlyAccepted,
            stages,
            recordAeadStageReached = true,
        )
        randomizedChecks += requireAeadRejection(
            key = expandedKeys.recordAeadKey.copyOf().also { it[0] = (it[0].toInt() xor 0x01).toByte() },
            ciphertext = ciphertext.bytes,
            aadContext = request.aadContext,
            check = ProductionProviderRandomizedAeadBehavioralKatCheck.WrongKeyFails,
        ) ?: return rejected(
            SkaldVaultV1ProviderKatFailureReason.RecordAeadUnexpectedlyAccepted,
            stages,
            recordAeadStageReached = true,
        )
        randomizedChecks += requireAeadRejection(
            key = expandedKeys.recordAeadKey,
            ciphertext = ciphertext.bytes.copyOf().also { it[0] = (it[0].toInt() xor 0x01).toByte() },
            aadContext = request.aadContext,
            check = ProductionProviderRandomizedAeadBehavioralKatCheck.TamperedCiphertextFails,
        ) ?: return rejected(
            SkaldVaultV1ProviderKatFailureReason.RecordAeadUnexpectedlyAccepted,
            stages,
            recordAeadStageReached = true,
        )
        randomizedChecks += requireAeadRejection(
            key = expandedKeys.recordAeadKey,
            ciphertext = ciphertext.bytes.copyOf().also { it[it.lastIndex] = (it[it.lastIndex].toInt() xor 0x01).toByte() },
            aadContext = request.aadContext,
            check = ProductionProviderRandomizedAeadBehavioralKatCheck.TamperedTagFails,
        ) ?: return rejected(
            SkaldVaultV1ProviderKatFailureReason.RecordAeadUnexpectedlyAccepted,
            stages,
            recordAeadStageReached = true,
        )
        randomizedChecks += requireAeadRejection(
            key = expandedKeys.recordAeadKey,
            ciphertext = ciphertext.bytes,
            aadContext = request.aadContext.copy(vaultId = (0x60..0x6f).map { it.toByte() }.toByteArray()),
            check = ProductionProviderRandomizedAeadBehavioralKatCheck.WrongVaultIdFails,
        ) ?: return rejected(
            SkaldVaultV1ProviderKatFailureReason.RecordAeadUnexpectedlyAccepted,
            stages,
            recordAeadStageReached = true,
        )
        randomizedChecks += requireAeadRejection(
            key = expandedKeys.recordAeadKey,
            ciphertext = ciphertext.bytes,
            aadContext = request.aadContext.copy(recordId = (0x50..0x5f).map { it.toByte() }.toByteArray()),
            check = ProductionProviderRandomizedAeadBehavioralKatCheck.WrongRecordIdFails,
        ) ?: return rejected(
            SkaldVaultV1ProviderKatFailureReason.RecordAeadUnexpectedlyAccepted,
            stages,
            recordAeadStageReached = true,
        )
        randomizedChecks += requireAeadRejection(
            key = expandedKeys.recordAeadKey,
            ciphertext = ciphertext.bytes,
            aadContext = request.aadContext.copy(recordTypeId = SkaldVaultV1RecordType.SecretPayload.typeId),
            check = ProductionProviderRandomizedAeadBehavioralKatCheck.WrongRecordTypeFails,
        ) ?: return rejected(
            SkaldVaultV1ProviderKatFailureReason.RecordAeadUnexpectedlyAccepted,
            stages,
            recordAeadStageReached = true,
        )
        randomizedChecks += requireAeadRejection(
            key = expandedKeys.recordAeadKey,
            ciphertext = ciphertext.bytes,
            aadContext = request.aadContext.copy(recordVersionCounter = request.aadContext.recordVersionCounter + 1),
            check = ProductionProviderRandomizedAeadBehavioralKatCheck.WrongRecordVersionCounterFails,
        ) ?: return rejected(
            SkaldVaultV1ProviderKatFailureReason.RecordAeadUnexpectedlyAccepted,
            stages,
            recordAeadStageReached = true,
        )
        randomizedChecks += requireAeadRejection(
            key = expandedKeys.recordAeadKey,
            ciphertext = ciphertext.bytes,
            aadContext = request.aadContext.copy(providerSuiteId = "unsupported-provider-suite"),
            check = ProductionProviderRandomizedAeadBehavioralKatCheck.WrongProviderSuiteIdFails,
        ) ?: return rejected(
            SkaldVaultV1ProviderKatFailureReason.RecordAeadUnexpectedlyAccepted,
            stages,
            recordAeadStageReached = true,
        )
        randomizedChecks += requireAeadRejection(
            key = expandedKeys.recordAeadKey,
            ciphertext = ciphertext.bytes,
            aadContext = request.aadContext.copy(headerCommitmentContext = ByteArray(32) { 0x7f.toByte() }),
            check = ProductionProviderRandomizedAeadBehavioralKatCheck.WrongHeaderCommitmentContextFails,
        ) ?: return rejected(
            SkaldVaultV1ProviderKatFailureReason.RecordAeadUnexpectedlyAccepted,
            stages,
            recordAeadStageReached = true,
        )
        stages += SkaldVaultV1ProviderKatStage.RecordAeadNegativeChecksCompleted

        return SkaldVaultV1ProviderKatHarnessResult.Accepted(
            SkaldVaultV1ProviderKatEvidence(
                stagesCompleted = stages.toList(),
                deterministicVectorsMatched = deterministicVectors.toSet(),
                randomizedAeadBehavioralChecksPassed = randomizedChecks.toSet(),
                headerCommitmentVerifiedBeforeRecordAead = true,
                recordAeadStageReached = true,
                ciphertextTreatedAsDeterministic = false,
                productionProviderSelectable = false,
                vaultCreationEnabled = false,
                vaultPersistenceEnabled = false,
                manifestStorageImplemented = false,
                secureStorageEnabled = false,
            ),
        )
    }

    private fun matchVector(
        request: SkaldVaultV1ProviderKatRequest,
        matched: MutableSet<ProductionProviderDeterministicKatVector>,
        vector: ProductionProviderDeterministicKatVector,
        actual: ByteArray,
        expectedHex: String,
    ): Boolean {
        if (!request.enforceDeterministicVectorMatches) {
            return true
        }
        if (actual.toHex() != expectedHex) {
            return false
        }
        matched += vector
        return true
    }

    private fun requireAeadRejection(
        key: ByteArray,
        ciphertext: ByteArray,
        aadContext: SkaldVaultV1RecordAadContext,
        check: ProductionProviderRandomizedAeadBehavioralKatCheck,
    ): Set<ProductionProviderRandomizedAeadBehavioralKatCheck>? =
        when (
            recordAead.openRecord(
                recordAeadKey = key.copyOf(),
                ciphertext = ciphertext.copyOf(),
                aadContext = aadContext,
            )
        ) {
            is SkaldVaultV1RecordAeadResult.Accepted -> null
            is SkaldVaultV1RecordAeadResult.Rejected -> setOf(check)
        }

    private fun rejected(
        reason: SkaldVaultV1ProviderKatFailureReason,
        stages: List<SkaldVaultV1ProviderKatStage>,
        recordAeadStageReached: Boolean = false,
    ): SkaldVaultV1ProviderKatHarnessResult.Rejected =
        SkaldVaultV1ProviderKatHarnessResult.Rejected(
            reason = reason,
            safeMessage = "Skald Vault v1 still-disabled provider KAT harness rejected: ${reason.label}.",
            stagesCompleted = stages.toList(),
            recordAeadStageReached = recordAeadStageReached,
        )
}

private fun ByteArray.toHex(): String =
    joinToString(separator = "") { byte -> (byte.toInt() and 0xff).toString(16).padStart(2, '0') }

private fun String.hexToBytes(): ByteArray {
    require(length % 2 == 0)
    return chunked(2).map { it.toInt(16).toByte() }.toByteArray()
}
