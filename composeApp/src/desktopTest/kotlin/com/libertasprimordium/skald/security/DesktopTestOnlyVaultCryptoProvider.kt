package com.libertasprimordium.skald.security

import com.google.crypto.tink.aead.internal.InsecureNonceXChaCha20Poly1305
import org.bouncycastle.crypto.generators.Argon2BytesGenerator
import org.bouncycastle.crypto.params.Argon2Parameters

enum class DesktopTestOnlyVaultCryptoProviderExecutionMode(
    val providerLevelKatExecutionAllowed: Boolean,
    val providerOperationsAllowed: Boolean,
) {
    StaticImplementationOnly(
        providerLevelKatExecutionAllowed = false,
        providerOperationsAllowed = false,
    ),
    ProviderLevelPublicKatExecution(
        providerLevelKatExecutionAllowed = true,
        providerOperationsAllowed = false,
    ),
}

data class DesktopTestOnlyVaultCryptoProviderImplementationReport(
    val identity: SkaldVaultV1TestOnlyExecutableProviderIdentity,
    val executionMode: DesktopTestOnlyVaultCryptoProviderExecutionMode,
    val implementsVaultCryptoProviderInTestSource: Boolean,
    val productionImplementationPresent: Boolean,
    val providerLevelKatExecutedInThisBranch: Boolean,
    val kdfExecutedInThisBranch: Boolean,
    val aeadExecutedInThisBranch: Boolean,
    val providerOperationExecutedInThisBranch: Boolean,
    val providerSelectionEnabled: Boolean,
    val productionProviderSelectable: Boolean,
    val providerRegistryEntryPresent: Boolean,
    val providerFactoryPresent: Boolean,
    val providerDispatcherPresent: Boolean,
    val executorTargetPresent: Boolean,
) {
    override fun toString(): String =
        if (executionMode.providerLevelKatExecutionAllowed) {
            "DesktopTestOnlyVaultCryptoProviderImplementationReport(" +
                "REDACTED, TEST_SOURCE_ONLY, PROVIDER_LEVEL_PUBLIC_KAT_EXECUTION_ONLY, " +
                "NO_PROVIDER_SELECTION, NO_PRODUCTION_PROVIDER" +
                ")"
        } else {
            "DesktopTestOnlyVaultCryptoProviderImplementationReport(" +
                "REDACTED, TEST_SOURCE_ONLY, PROVIDER_IMPLEMENTATION_ONLY, PUBLIC_KAT_SCOPE_ONLY, " +
                "NO_PROVIDER_LEVEL_KAT_EXECUTION, NO_PROVIDER_SELECTION" +
                ")"
        }
}

class DesktopTestOnlyVaultCryptoProvider(
    val identity: SkaldVaultV1TestOnlyExecutableProviderIdentity =
        SkaldVaultV1TestOnlyExecutableProviderIdentityPolicy.currentTestOnlyExecutableProviderIdentity(),
    val executionMode: DesktopTestOnlyVaultCryptoProviderExecutionMode =
        DesktopTestOnlyVaultCryptoProviderExecutionMode.StaticImplementationOnly,
    private val disabledProvider: DisabledVaultCryptoProvider = DisabledVaultCryptoProvider(),
) : VaultCryptoProvider {
    override val statusReport: VaultCryptoProviderStatusReport =
        commonDisabledVaultCryptoProviderStatus()

    val implementationReport: DesktopTestOnlyVaultCryptoProviderImplementationReport =
        DesktopTestOnlyVaultCryptoProviderImplementationReport(
            identity = identity,
            executionMode = executionMode,
            implementsVaultCryptoProviderInTestSource = true,
            productionImplementationPresent = identity.productionImplementationPresent,
            providerLevelKatExecutedInThisBranch = executionMode.providerLevelKatExecutionAllowed,
            kdfExecutedInThisBranch = executionMode.providerLevelKatExecutionAllowed,
            aeadExecutedInThisBranch = executionMode.providerLevelKatExecutionAllowed,
            providerOperationExecutedInThisBranch = false,
            providerSelectionEnabled = identity.providerSelectionEnabled,
            productionProviderSelectable = identity.productionProviderSelectable,
            providerRegistryEntryPresent = identity.providerRegistryEntryPresent,
            providerFactoryPresent = identity.providerFactoryPresent,
            providerDispatcherPresent = identity.providerDispatcherPresent,
            executorTargetPresent = identity.executorTargetPresent,
        )

    val providerLevelKatExecutionEnabled: Boolean
        get() = executionMode.providerLevelKatExecutionAllowed

    val providerOperationsEnabled: Boolean
        get() = executionMode.providerOperationsAllowed

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
        if (!providerLevelKatExecutionEnabled) {
            return disabledProvider.validateKat(request)
        }
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
        if (providerLevelKatExecutionEnabled) {
            "DesktopTestOnlyVaultCryptoProvider(" +
                "REDACTED, TEST_SOURCE_ONLY, PROVIDER_LEVEL_PUBLIC_KAT_EXECUTION_ONLY, " +
                "PUBLIC_VECTORS_ONLY, NO_PROVIDER_SELECTION, NO_PRODUCTION_PROVIDER" +
                ")"
        } else {
            "DesktopTestOnlyVaultCryptoProvider(" +
                "REDACTED, TEST_SOURCE_ONLY, PROVIDER_IMPLEMENTATION_ONLY, STATIC_ONLY, " +
                "NO_PROVIDER_LEVEL_KAT_EXECUTION" +
                ")"
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

    private fun xChaChaVector(): DesktopTestOnlyXChaChaPublicVector =
        DesktopTestOnlyXChaChaPublicVector(
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

private data class DesktopTestOnlyXChaChaPublicVector(
    val key: ByteArray,
    val nonce: ByteArray,
    val aad: ByteArray,
    val plaintext: ByteArray,
    val ciphertextAndTag: ByteArray,
)
