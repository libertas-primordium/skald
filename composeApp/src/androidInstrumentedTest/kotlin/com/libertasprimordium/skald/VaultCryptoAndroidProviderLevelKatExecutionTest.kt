package com.libertasprimordium.skald

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.crypto.tink.aead.internal.InsecureNonceXChaCha20Poly1305
import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.VaultCryptoAeadDecryptRequest
import com.libertasprimordium.skald.security.VaultCryptoAeadEncryptRequest
import com.libertasprimordium.skald.security.VaultCryptoAssociatedDataContext
import com.libertasprimordium.skald.security.VaultCryptoCiphertextHandle
import com.libertasprimordium.skald.security.VaultCryptoDerivedKeyHandle
import com.libertasprimordium.skald.security.VaultCryptoGeneratedKeyHandle
import com.libertasprimordium.skald.security.VaultCryptoKeyGenerationRequest
import com.libertasprimordium.skald.security.VaultCryptoKeysetHandle
import com.libertasprimordium.skald.security.VaultCryptoKeysetStorageRequest
import com.libertasprimordium.skald.security.VaultCryptoKdfRequest
import com.libertasprimordium.skald.security.VaultCryptoOperation
import com.libertasprimordium.skald.security.VaultCryptoPlaintextHandle
import com.libertasprimordium.skald.security.VaultCryptoProvider
import com.libertasprimordium.skald.security.VaultCryptoProviderBlocker
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderDiagnostic
import com.libertasprimordium.skald.security.VaultCryptoProviderError
import com.libertasprimordium.skald.security.VaultCryptoProviderErrorCode
import com.libertasprimordium.skald.security.VaultCryptoProviderKatEvidence
import com.libertasprimordium.skald.security.VaultCryptoProviderKatExecutionScope
import com.libertasprimordium.skald.security.VaultCryptoProviderKatRequest
import com.libertasprimordium.skald.security.VaultCryptoProviderKatVectorId
import com.libertasprimordium.skald.security.VaultCryptoProviderResult
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionDecision
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
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
class VaultCryptoAndroidProviderLevelKatExecutionTest {
    private fun provider(): AndroidProviderLevelPublicKatVaultCryptoProvider =
        AndroidProviderLevelPublicKatVaultCryptoProvider()

    @Test
    fun androidProviderLevelKdfPublicKatExecutesThroughTestOnlyProvider() {
        val result = provider().validateKat(katRequest(VaultCryptoProviderKatVectorId.Argon2idRfc9106Section53))

        result.assertKatSuccess(VaultCryptoProviderKatVectorId.Argon2idRfc9106Section53)
    }

    @Test
    fun androidProviderLevelAeadPublicKatExecutesThroughTestOnlyProvider() {
        val result = provider().validateKat(
            katRequest(VaultCryptoProviderKatVectorId.XChaCha20Poly1305DraftAppendixA1),
        )

        result.assertKatSuccess(VaultCryptoProviderKatVectorId.XChaCha20Poly1305DraftAppendixA1)
    }

    @Test
    fun androidProviderLevelPublicKatsDoNotEnableSelectionProductionStorageSyncOrMainnet() {
        val provider = provider()
        val selection = VaultCryptoProviderSelectionRegistry.select()

        provider.validateKat(katRequest(VaultCryptoProviderKatVectorId.Argon2idRfc9106Section53))
            .assertKatSuccess(VaultCryptoProviderKatVectorId.Argon2idRfc9106Section53)
        provider.validateKat(katRequest(VaultCryptoProviderKatVectorId.XChaCha20Poly1305DraftAppendixA1))
            .assertKatSuccess(VaultCryptoProviderKatVectorId.XChaCha20Poly1305DraftAppendixA1)
        provider.validateKat(katRequest(VaultCryptoProviderKatVectorId.VaultStorageApprovalSeparated))
            .assertBlocked(VaultCryptoProviderBlocker.VaultContainerUnavailable)

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertEquals(VaultCryptoProviderSelectionDecision.DisabledProviderSelected, selection.decision)
        assertTrue(selection.selectedProviderIsDisabled)
        assertTrue(selection.selectedProvider is DisabledVaultCryptoProvider)
        assertFalse(selection.productionProviderSelectable)
        assertFalse(provider.statusReport.implementationStatus.canExecuteCrypto)
        assertFalse(provider.statusReport.implementationStatus.productionApproved)
        assertFalse(provider.statusReport.productionPersistenceEnabled)
        assertFalse(provider.statusReport.mainnetEnabled)
    }

    @Test
    fun androidProviderLevelPublicKatOutputIsRedactedAndDoesNotExposeVectorMaterial() {
        val provider = provider()
        val success = provider.validateKat(katRequest(VaultCryptoProviderKatVectorId.XChaCha20Poly1305DraftAppendixA1))
        val blocked = provider.validateKat(katRequest(VaultCryptoProviderKatVectorId.VaultStorageApprovalSeparated))
        val successEvidence = (success as VaultCryptoProviderResult.Success).value
        val blockedResult = blocked as VaultCryptoProviderResult.Blocked
        val output = listOf(
            provider.toString(),
            success.toString(),
            successEvidence.toString(),
            blocked.toString(),
            blockedResult.error.toString(),
            blockedResult.error.diagnostic.toString(),
        ).joinToString(separator = " ")
        val forbiddenText = listOf(
            "0d640df5",
            "bd6d179d",
            "80818283",
            "40414243",
            "4c616469",
            "ciphertext",
            "plaintext",
            "nonce",
            "tag",
            "provider handle",
            "source location",
            "stack trace",
            "diagnostics payload",
            "analytics payload",
            "crash-report payload",
            "support-export payload",
            "endpoint value",
            "filesystem path",
            "wpkh(",
            "tr(",
            "xpub",
            "xprv",
            "tprv",
            "psbt",
            "nsec",
        )

        assertTrue(output.contains("REDACTED"))
        assertTrue(output.contains("TEST_SOURCE_ONLY"))
        assertTrue(output.contains("PROVIDER_LEVEL_PUBLIC_KAT_EXECUTION_ONLY"))
        assertTrue(output.contains("NO_PROVIDER_SELECTION"))
        assertTrue(output.contains("NO_PRODUCTION_PROVIDER"))
        forbiddenText.forEach { forbidden ->
            assertFalse("Output leaked forbidden text: $forbidden", output.contains(forbidden, ignoreCase = true))
        }
        assertFalse(Regex("\\b[0-9a-fA-F]{64}\\b").containsMatchIn(output))
        assertFalse(Regex("\\b(?:bc1|tb1|bcrt1)[a-z0-9]{20,}\\b", RegexOption.IGNORE_CASE).containsMatchIn(output))
    }

    private fun katRequest(vectorId: VaultCryptoProviderKatVectorId): VaultCryptoProviderKatRequest =
        VaultCryptoProviderKatRequest(
            requirement = commonProviderKatContract().requirements.single { it.vectorId == vectorId },
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

internal class AndroidProviderLevelPublicKatVaultCryptoProvider : VaultCryptoProvider {
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
            VaultCryptoProviderKatVectorId.VaultStorageApprovalSeparated -> {
                blocked(request, VaultCryptoProviderBlocker.VaultContainerUnavailable)
            }
            else -> blocked(request, VaultCryptoProviderBlocker.ProviderLevelKatContractUnsatisfied)
        }
    }

    override fun toString(): String =
        "AndroidProviderLevelPublicKatVaultCryptoProvider(" +
            "REDACTED, TEST_SOURCE_ONLY, PROVIDER_LEVEL_PUBLIC_KAT_EXECUTION_ONLY, " +
            "PUBLIC_VECTORS_ONLY, NO_PROVIDER_SELECTION, NO_PRODUCTION_PROVIDER" +
            ")"

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
        return try {
            if (
                ciphertextAndTag.contentEquals(vector.ciphertextAndTag) &&
                decrypted.contentEquals(vector.plaintext)
            ) {
                success(request)
            } else {
                blocked(request, VaultCryptoProviderBlocker.ProviderLevelKatContractUnsatisfied)
            }
        } finally {
            ciphertextAndTag.fill(0)
            decrypted.fill(0)
        }
    }

    private fun success(
        request: VaultCryptoProviderKatRequest,
    ): VaultCryptoProviderResult.Success<VaultCryptoProviderKatEvidence> =
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
                    safeCode = "TEST_ONLY_PROVIDER_LEVEL_PUBLIC_KAT_BLOCKED",
                    safeDetail = "Test-only provider-level public KAT did not approve ${request.vectorId.name}.",
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

    private fun xChaChaVector(): AndroidProviderLevelXChaChaPublicVector =
        AndroidProviderLevelXChaChaPublicVector(
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

private data class AndroidProviderLevelXChaChaPublicVector(
    val key: ByteArray,
    val nonce: ByteArray,
    val aad: ByteArray,
    val plaintext: ByteArray,
    val ciphertextAndTag: ByteArray,
)
