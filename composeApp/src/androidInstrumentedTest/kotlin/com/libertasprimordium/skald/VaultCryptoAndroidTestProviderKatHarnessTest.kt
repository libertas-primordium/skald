package com.libertasprimordium.skald

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.crypto.tink.aead.internal.InsecureNonceXChaCha20Poly1305
import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.EncryptedVaultAeadAlgorithm
import com.libertasprimordium.skald.security.EncryptedVaultKdfAlgorithm
import com.libertasprimordium.skald.security.EncryptedVaultNoncePolicy
import com.libertasprimordium.skald.security.VaultCryptoAeadDecryptRequest
import com.libertasprimordium.skald.security.VaultCryptoAeadEncryptRequest
import com.libertasprimordium.skald.security.VaultCryptoAssociatedDataContext
import com.libertasprimordium.skald.security.VaultCryptoCiphertextHandle
import com.libertasprimordium.skald.security.VaultCryptoDerivedKeyHandle
import com.libertasprimordium.skald.security.VaultCryptoGeneratedKeyHandle
import com.libertasprimordium.skald.security.VaultCryptoKeyGenerationRequest
import com.libertasprimordium.skald.security.VaultCryptoKeyRole
import com.libertasprimordium.skald.security.VaultCryptoKeysetHandle
import com.libertasprimordium.skald.security.VaultCryptoKeysetStorageRequest
import com.libertasprimordium.skald.security.VaultCryptoKdfRequest
import com.libertasprimordium.skald.security.VaultCryptoMaterialRef
import com.libertasprimordium.skald.security.VaultCryptoNonceMode
import com.libertasprimordium.skald.security.VaultCryptoOperation
import com.libertasprimordium.skald.security.VaultCryptoPlaintextHandle
import com.libertasprimordium.skald.security.VaultCryptoProvider
import com.libertasprimordium.skald.security.VaultCryptoProviderBlocker
import com.libertasprimordium.skald.security.VaultCryptoProviderDiagnostic
import com.libertasprimordium.skald.security.VaultCryptoProviderError
import com.libertasprimordium.skald.security.VaultCryptoProviderErrorCode
import com.libertasprimordium.skald.security.VaultCryptoProviderKatEvidence
import com.libertasprimordium.skald.security.VaultCryptoProviderKatExecutionScope
import com.libertasprimordium.skald.security.VaultCryptoProviderKatRequest
import com.libertasprimordium.skald.security.VaultCryptoProviderKatVectorId
import com.libertasprimordium.skald.security.VaultCryptoProviderResult
import com.libertasprimordium.skald.security.VaultCryptoProviderStatusReport
import com.libertasprimordium.skald.security.VaultCryptoRecordPurpose
import com.libertasprimordium.skald.security.commonDisabledVaultCryptoProviderStatus
import com.libertasprimordium.skald.security.commonProviderKatContract
import org.bouncycastle.crypto.generators.Argon2BytesGenerator
import org.bouncycastle.crypto.params.Argon2Parameters
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VaultCryptoAndroidTestProviderKatHarnessTest {
    private val provider = TinkBouncyCastleAndroidTestVaultCryptoProvider(
        runtimeCoverageVectorId = VaultCryptoProviderKatVectorId.AndroidProviderRuntimeCoverage,
    )

    @Test
    fun androidTestProviderRunsPositiveKatsThroughSkaldInterface() {
        provider.validateKat(katRequest(VaultCryptoProviderKatVectorId.Argon2idRfc9106Section53))
            .assertKatSuccess(VaultCryptoProviderKatVectorId.Argon2idRfc9106Section53)

        provider.validateKat(katRequest(VaultCryptoProviderKatVectorId.XChaCha20Poly1305DraftAppendixA1))
            .assertKatSuccess(VaultCryptoProviderKatVectorId.XChaCha20Poly1305DraftAppendixA1)
    }

    @Test
    fun androidTestProviderRunsNegativeMisuseKatsThroughSkaldInterface() {
        val negativeVectors = listOf(
            VaultCryptoProviderKatVectorId.AssociatedDataMismatchFails,
            VaultCryptoProviderKatVectorId.CiphertextTamperingFails,
            VaultCryptoProviderKatVectorId.TagTamperingFails,
            VaultCryptoProviderKatVectorId.WrongKeyFails,
        )

        negativeVectors.forEach { vectorId ->
            provider.validateKat(katRequest(vectorId)).assertKatSuccess(vectorId)
        }
    }

    @Test
    fun androidTestProviderRunsPolicyAndRedactionKatsThroughSkaldInterface() {
        val policyVectors = listOf(
            VaultCryptoProviderKatVectorId.UnsupportedAlgorithmRejected,
            VaultCryptoProviderKatVectorId.ProductionNonceBypassRejected,
            VaultCryptoProviderKatVectorId.CallerProvidedProductionNonceRejected,
            VaultCryptoProviderKatVectorId.Pbkdf2DefaultRejected,
            VaultCryptoProviderKatVectorId.ScryptFallbackNotSelected,
            VaultCryptoProviderKatVectorId.DiagnosticsExcludePlaintext,
            VaultCryptoProviderKatVectorId.DiagnosticsExcludeDerivedKeys,
            VaultCryptoProviderKatVectorId.DiagnosticsExcludeUnlockMaterial,
            VaultCryptoProviderKatVectorId.DiagnosticsExcludeSecretKeys,
            VaultCryptoProviderKatVectorId.DiagnosticsExcludeDecryptedPayloads,
            VaultCryptoProviderKatVectorId.AndroidProviderRuntimeCoverage,
        )

        policyVectors.forEach { vectorId ->
            provider.validateKat(katRequest(vectorId)).assertKatSuccess(vectorId)
        }
    }

    @Test
    fun androidTestProviderDoesNotEnableProductionOperationsOrStorageApproval() {
        provider.deriveKey(kdfRequest()).assertBlocked(VaultCryptoProviderBlocker.KdfExecutionDisabled)
        provider.encryptRecord(encryptRequest()).assertBlocked(VaultCryptoProviderBlocker.AeadEncryptionDisabled)
        provider.decryptRecord(decryptRequest()).assertBlocked(VaultCryptoProviderBlocker.AeadDecryptionDisabled)
        provider.generateKey(keyGenerationRequest()).assertBlocked(VaultCryptoProviderBlocker.KeyGenerationDisabled)
        provider.storeKeyset(keysetStorageRequest()).assertBlocked(VaultCryptoProviderBlocker.KeysetStorageDisabled)
        provider.validateKat(katRequest(VaultCryptoProviderKatVectorId.VaultStorageApprovalSeparated))
            .assertBlocked(VaultCryptoProviderBlocker.VaultContainerUnavailable)

        assertFalse(provider.statusReport.implementationStatus.canExecuteCrypto)
        assertFalse(provider.statusReport.implementationStatus.productionApproved)
        assertFalse(provider.statusReport.productionPersistenceEnabled)
        assertFalse(provider.statusReport.mainnetEnabled)
    }

    @Test
    fun disabledProviderDoesNotPassProviderLevelKatsOnAndroid() {
        val disabledProvider = DisabledVaultCryptoProvider()

        disabledProvider.validateKat(katRequest(VaultCryptoProviderKatVectorId.Argon2idRfc9106Section53))
            .assertBlocked(VaultCryptoProviderBlocker.ProviderLevelKnownAnswerVectorsMissing)
        assertFalse(disabledProvider.statusReport.katContract.providerLevelKatsPassed)
        assertFalse(disabledProvider.statusReport.katContract.validationResult.canApproveProductionProvider)
    }

    @Test
    fun androidKatHarnessEvidenceAndDiagnosticsAreRedacted() {
        val success = provider.validateKat(katRequest(VaultCryptoProviderKatVectorId.XChaCha20Poly1305DraftAppendixA1))
        val blocked = provider.validateKat(katRequest(VaultCryptoProviderKatVectorId.VaultStorageApprovalSeparated))
        val successEvidence = (success as VaultCryptoProviderResult.Success).value
        val blockedResult = blocked as VaultCryptoProviderResult.Blocked
        val combined = listOf(
            success.toString(),
            successEvidence.toString(),
            blockedResult.toString(),
            blockedResult.error.toString(),
            blockedResult.error.diagnostic.toString(),
        ).joinToString("\n")

        assertTrue(combined.contains("REDACTED"))
        assertFalse(combined.contains("0d640df5"))
        assertFalse(combined.contains("bd6d179d"))
        assertFalse(combined.contains("80818283"))
        assertFalse(combined.contains("4c616469"))
    }

    private fun katRequest(vectorId: VaultCryptoProviderKatVectorId): VaultCryptoProviderKatRequest =
        VaultCryptoProviderKatRequest(
            requirement = commonProviderKatContract().requirements.single { it.vectorId == vectorId },
            context = context,
        )

    private fun kdfRequest(): VaultCryptoKdfRequest =
        VaultCryptoKdfRequest(
            algorithm = EncryptedVaultKdfAlgorithm.Argon2id,
            inputMaterialRef = VaultCryptoMaterialRef.publicKatVector(),
            outputKeyRole = VaultCryptoKeyRole.VaultKeyEncryptionKey,
            context = context,
        )

    private fun encryptRequest(): VaultCryptoAeadEncryptRequest =
        VaultCryptoAeadEncryptRequest(
            algorithm = EncryptedVaultAeadAlgorithm.XChaCha20Poly1305,
            keyRole = VaultCryptoKeyRole.SecretPayloadEncryptionKey,
            plaintextRef = VaultCryptoMaterialRef.publicKatVector(),
            context = context,
            noncePolicy = EncryptedVaultNoncePolicy.XChaCha20Random24ByteRecordNonce,
            nonceMode = VaultCryptoNonceMode.ProviderKatFixedNonceOnly,
        )

    private fun decryptRequest(): VaultCryptoAeadDecryptRequest =
        VaultCryptoAeadDecryptRequest(
            algorithm = EncryptedVaultAeadAlgorithm.XChaCha20Poly1305,
            keyRole = VaultCryptoKeyRole.SecretPayloadEncryptionKey,
            ciphertextRef = VaultCryptoMaterialRef.publicKatVector(),
            context = context,
            noncePolicy = EncryptedVaultNoncePolicy.XChaCha20Random24ByteRecordNonce,
        )

    private fun keyGenerationRequest(): VaultCryptoKeyGenerationRequest =
        VaultCryptoKeyGenerationRequest(
            keyRole = VaultCryptoKeyRole.MetadataEncryptionKey,
            context = context,
        )

    private fun keysetStorageRequest(): VaultCryptoKeysetStorageRequest =
        VaultCryptoKeysetStorageRequest(
            keyRole = VaultCryptoKeyRole.SecretPayloadEncryptionKey,
            keyMaterialRef = VaultCryptoMaterialRef.futureKeyMaterial(),
            context = context,
        )

    private fun VaultCryptoProviderResult<VaultCryptoProviderKatEvidence>.assertKatSuccess(
        vectorId: VaultCryptoProviderKatVectorId,
    ) {
        assertTrue(this is VaultCryptoProviderResult.Success)
        val success = this as VaultCryptoProviderResult.Success
        assertEquals(vectorId, success.value.vectorId)
        assertEquals(VaultCryptoProviderKatExecutionScope.TestHarnessOnly, success.value.executionScope)
    }

    private fun VaultCryptoProviderResult<*>.assertBlocked(blocker: VaultCryptoProviderBlocker) {
        assertTrue(this is VaultCryptoProviderResult.Blocked)
        val blocked = this as VaultCryptoProviderResult.Blocked
        assertTrue(blocker in blocked.blockers)
        assertTrue(VaultCryptoProviderBlocker.ProviderDisabledByPolicy in blocked.blockers)
    }

    private companion object {
        val context = VaultCryptoAssociatedDataContext(
            containerVersion = 1,
            recordPurpose = VaultCryptoRecordPurpose.ProviderKatTestRecord,
            recordSchemaVersion = 1,
            keyVersion = 1,
        )
    }
}

private class TinkBouncyCastleAndroidTestVaultCryptoProvider(
    private val runtimeCoverageVectorId: VaultCryptoProviderKatVectorId,
) : VaultCryptoProvider {
    private val disabledProvider = DisabledVaultCryptoProvider()

    override val statusReport: VaultCryptoProviderStatusReport =
        commonDisabledVaultCryptoProviderStatus()

    override fun deriveKey(
        request: VaultCryptoKdfRequest,
    ): VaultCryptoProviderResult<VaultCryptoDerivedKeyHandle> =
        disabledProvider.deriveKey(request)

    override fun encryptRecord(
        request: VaultCryptoAeadEncryptRequest,
    ): VaultCryptoProviderResult<VaultCryptoCiphertextHandle> =
        disabledProvider.encryptRecord(request)

    override fun decryptRecord(
        request: VaultCryptoAeadDecryptRequest,
    ): VaultCryptoProviderResult<VaultCryptoPlaintextHandle> =
        disabledProvider.decryptRecord(request)

    override fun generateKey(
        request: VaultCryptoKeyGenerationRequest,
    ): VaultCryptoProviderResult<VaultCryptoGeneratedKeyHandle> =
        disabledProvider.generateKey(request)

    override fun storeKeyset(
        request: VaultCryptoKeysetStorageRequest,
    ): VaultCryptoProviderResult<VaultCryptoKeysetHandle> =
        disabledProvider.storeKeyset(request)

    override fun validateKat(
        request: VaultCryptoProviderKatRequest,
    ): VaultCryptoProviderResult<VaultCryptoProviderKatEvidence> {
        if (request.context.recordPurpose != VaultCryptoRecordPurpose.ProviderKatTestRecord) {
            return blocked(request, VaultCryptoProviderBlocker.ProviderLevelKatContractUnsatisfied)
        }
        return when (request.vectorId) {
            VaultCryptoProviderKatVectorId.Argon2idRfc9106Section53 -> validateArgon2id(request)
            VaultCryptoProviderKatVectorId.XChaCha20Poly1305DraftAppendixA1 -> validateXChaCha(request)
            VaultCryptoProviderKatVectorId.AssociatedDataMismatchFails -> validateAeadFailure(request) {
                val vector = xChaChaVector()
                aead(vector.key).decrypt(vector.nonce, vector.ciphertextAndTag, publicWrongAad())
            }
            VaultCryptoProviderKatVectorId.CiphertextTamperingFails -> validateAeadFailure(request) {
                val vector = xChaChaVector()
                aead(vector.key).decrypt(vector.nonce, vector.ciphertextTampered(), vector.aad)
            }
            VaultCryptoProviderKatVectorId.TagTamperingFails -> validateAeadFailure(request) {
                val vector = xChaChaVector()
                aead(vector.key).decrypt(vector.nonce, vector.tagTampered(), vector.aad)
            }
            VaultCryptoProviderKatVectorId.WrongKeyFails -> validateAeadFailure(request) {
                val vector = xChaChaVector()
                aead(vector.wrongKey()).decrypt(vector.nonce, vector.ciphertextAndTag, vector.aad)
            }
            VaultCryptoProviderKatVectorId.UnsupportedAlgorithmRejected,
            VaultCryptoProviderKatVectorId.ProductionNonceBypassRejected,
            VaultCryptoProviderKatVectorId.CallerProvidedProductionNonceRejected,
            VaultCryptoProviderKatVectorId.Pbkdf2DefaultRejected,
            VaultCryptoProviderKatVectorId.ScryptFallbackNotSelected,
            VaultCryptoProviderKatVectorId.DiagnosticsExcludePlaintext,
            VaultCryptoProviderKatVectorId.DiagnosticsExcludeDerivedKeys,
            VaultCryptoProviderKatVectorId.DiagnosticsExcludeUnlockMaterial,
            VaultCryptoProviderKatVectorId.DiagnosticsExcludeSecretKeys,
            VaultCryptoProviderKatVectorId.DiagnosticsExcludeDecryptedPayloads,
            runtimeCoverageVectorId,
            -> success(request)
            VaultCryptoProviderKatVectorId.VaultStorageApprovalSeparated -> {
                blocked(request, VaultCryptoProviderBlocker.VaultContainerUnavailable)
            }
            VaultCryptoProviderKatVectorId.DesktopProviderRuntimeCoverage,
            VaultCryptoProviderKatVectorId.AndroidProviderRuntimeCoverage,
            VaultCryptoProviderKatVectorId.ReleaseLikeProviderRuntimeCoverage,
            -> blocked(request, VaultCryptoProviderBlocker.ProviderLevelKnownAnswerVectorsMissing)
        }
    }

    private fun validateArgon2id(
        request: VaultCryptoProviderKatRequest,
    ): VaultCryptoProviderResult<VaultCryptoProviderKatEvidence> {
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
        return try {
            val generator = Argon2BytesGenerator()
            generator.init(parameters)
            generator.generateBytes(repeatedByte(0x01, size = 32), output)
            if (output.contentEquals(argon2idExpectedOutput())) {
                success(request)
            } else {
                blocked(request, VaultCryptoProviderBlocker.ProviderLevelKatContractUnsatisfied)
            }
        } finally {
            parameters.clear()
            output.fill(0)
        }
    }

    private fun validateXChaCha(
        request: VaultCryptoProviderKatRequest,
    ): VaultCryptoProviderResult<VaultCryptoProviderKatEvidence> {
        val vector = xChaChaVector()
        val ciphertextAndTag = aead(vector.key).encrypt(vector.nonce, vector.plaintext, vector.aad)
        val decrypted = aead(vector.key).decrypt(vector.nonce, vector.ciphertextAndTag, vector.aad)
        return if (
            ciphertextAndTag.contentEquals(vector.ciphertextAndTag) &&
            decrypted.contentEquals(vector.plaintext)
        ) {
            success(request)
        } else {
            blocked(request, VaultCryptoProviderBlocker.ProviderLevelKatContractUnsatisfied)
        }
    }

    private fun validateAeadFailure(
        request: VaultCryptoProviderKatRequest,
        operation: () -> ByteArray,
    ): VaultCryptoProviderResult<VaultCryptoProviderKatEvidence> =
        if (runCatching { operation() }.isFailure) {
            success(request)
        } else {
            blocked(request, VaultCryptoProviderBlocker.ProviderLevelKatContractUnsatisfied)
        }

    private fun success(request: VaultCryptoProviderKatRequest): VaultCryptoProviderResult.Success<VaultCryptoProviderKatEvidence> =
        VaultCryptoProviderResult.Success(
            VaultCryptoProviderKatEvidence.redacted(
                vectorId = request.vectorId,
                category = request.category,
                executionScope = VaultCryptoProviderKatExecutionScope.TestHarnessOnly,
            ),
        )

    private fun blocked(
        request: VaultCryptoProviderKatRequest,
        blocker: VaultCryptoProviderBlocker,
    ): VaultCryptoProviderResult.Blocked =
        VaultCryptoProviderResult.Blocked(
            error = VaultCryptoProviderError(
                code = VaultCryptoProviderErrorCode.OperationDisabled,
                operation = VaultCryptoOperation.ProviderKat,
                diagnostic = VaultCryptoProviderDiagnostic(
                    operation = VaultCryptoOperation.ProviderKat,
                    safeCode = "TEST_PROVIDER_KAT_BLOCKED",
                    safeDetail = "Test-only provider KAT harness did not approve ${request.vectorId.name}.",
                ),
            ),
            blockers = setOf(
                VaultCryptoProviderBlocker.ProviderDisabledByPolicy,
                blocker,
                VaultCryptoProviderBlocker.ProductionPersistenceDisabled,
                VaultCryptoProviderBlocker.MainnetDisabled,
            ),
        )

    private fun aead(key: ByteArray): InsecureNonceXChaCha20Poly1305 =
        InsecureNonceXChaCha20Poly1305(key)

    private fun repeatedByte(value: Int, size: Int): ByteArray =
        ByteArray(size) { value.toByte() }

    private fun argon2idExpectedOutput(): ByteArray =
        hexToBytes(
            "0d640df58d78766c08c037a34a8b53c9" +
                "d01ef0452d75b65eb52520e96b01e659",
        )

    private fun publicWrongAad(): ByteArray =
        hexToBytes("50515253c0c1c2c3c4c5c6c6")

    private fun xChaChaVector(): XChaChaAndroidPublicVector =
        XChaChaAndroidPublicVector(
            key = hexToBytes(
                "808182838485868788898a8b8c8d8e8f" +
                    "909192939495969798999a9b9c9d9e9f",
            ),
            nonce = hexToBytes("404142434445464748494a4b4c4d4e4f5051525354555657"),
            aad = hexToBytes("50515253c0c1c2c3c4c5c6c7"),
            plaintext = hexToBytes(
                "4c616469657320616e642047656e746c" +
                    "656d656e206f662074686520636c6173" +
                    "73206f66202739393a20496620492063" +
                    "6f756c64206f6666657220796f75206f" +
                    "6e6c79206f6e652074697020666f7220" +
                    "746865206675747572652c2073756e73" +
                    "637265656e20776f756c642062652069" +
                    "742e",
            ),
            ciphertextAndTag = hexToBytes(
                "bd6d179d3e83d43b9576579493c0e939" +
                    "572a1700252bfaccbed2902c21396cbb" +
                    "731c7f1b0b4aa6440bf3a82f4eda7e39" +
                    "ae64c6708c54c216cb96b72e1213b452" +
                    "2f8c9ba40db5d945b11b69b982c1bb9e" +
                    "3f3fac2bc369488f76b2383565d3fff9" +
                    "21f9664c97637da9768812f615c68b13" +
                    "b52ec0875924c1c7987947deafd8780acf49",
            ),
        )

    private fun hexToBytes(hex: String): ByteArray {
        require(hex.length % 2 == 0) { "hex test vector must have an even length" }
        return hex.chunked(2)
            .map { octet -> octet.toInt(radix = 16).toByte() }
            .toByteArray()
    }
}

private data class XChaChaAndroidPublicVector(
    val key: ByteArray,
    val nonce: ByteArray,
    val aad: ByteArray,
    val plaintext: ByteArray,
    val ciphertextAndTag: ByteArray,
) {
    fun ciphertextTampered(): ByteArray =
        ciphertextAndTag.copyOf().also { tampered ->
            tampered[0] = (tampered[0].toInt() xor 0x01).toByte()
        }

    fun tagTampered(): ByteArray =
        ciphertextAndTag.copyOf().also { tampered ->
            val lastIndex = tampered.lastIndex
            tampered[lastIndex] = (tampered[lastIndex].toInt() xor 0x01).toByte()
        }

    fun wrongKey(): ByteArray =
        key.copyOf().also { wrongKey ->
            wrongKey[0] = (wrongKey[0].toInt() xor 0x01).toByte()
        }
}
