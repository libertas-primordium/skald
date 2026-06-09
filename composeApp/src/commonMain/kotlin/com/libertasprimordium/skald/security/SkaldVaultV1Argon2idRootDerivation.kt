package com.libertasprimordium.skald.security

internal expect fun skaldVaultV1Argon2idRootMaterial(
    normalizedPassphraseBytes: ByteArray,
    salt: ByteArray,
    memoryKiB: Int,
    iterations: Int,
    parallelism: Int,
    outputBytes: Int,
): ByteArray

enum class SkaldVaultV1Argon2idType(val label: String) {
    Argon2id("Argon2id"),
    Argon2i("Argon2i"),
    Argon2d("Argon2d"),
}

data class SkaldVaultV1Argon2idParameters(
    val type: SkaldVaultV1Argon2idType = SkaldVaultV1Argon2idType.Argon2id,
    val version: Int = SkaldVaultV1HeaderCommitment.KDF_VERSION,
    val memoryKiB: Int = SkaldVaultV1HeaderCommitment.KDF_MEMORY_KIB,
    val iterations: Int = SkaldVaultV1HeaderCommitment.KDF_TIME_COST,
    val parallelism: Int = SkaldVaultV1HeaderCommitment.KDF_PARALLELISM,
    val outputBytes: Int = SkaldVaultV1HeaderCommitment.ARGON2ID_ROOT_MATERIAL_BYTES,
)

enum class SkaldVaultV1Argon2idRootDerivationRejectionReason(val label: String) {
    EmptyPassphraseBytes("empty normalized passphrase bytes"),
    UnsupportedArgon2Type("unsupported Argon2 type"),
    UnsupportedArgon2Version("unsupported Argon2 version"),
    MemoryBelowV1Floor("memory below Skald Vault v1 floor"),
    IterationsBelowV1Floor("iterations below Skald Vault v1 floor"),
    UnsupportedParallelism("unsupported parallelism"),
    SaltTooShort("salt too short"),
    UnsupportedOutputLength("unsupported output length"),
    ExecutionFailed("Argon2id execution failed"),
}

sealed class SkaldVaultV1Argon2idRootDerivationResult<out T> {
    data class Accepted<out T>(val value: T) : SkaldVaultV1Argon2idRootDerivationResult<T>()

    data class Rejected(
        val reason: SkaldVaultV1Argon2idRootDerivationRejectionReason,
        val safeMessage: String,
    ) : SkaldVaultV1Argon2idRootDerivationResult<Nothing>()
}

class SkaldVaultV1RootMaterial private constructor(
    rootMaterial: ByteArray,
) {
    private val rootMaterialBytes: ByteArray = rootMaterial.copyOf()

    val bytes: ByteArray
        get() = rootMaterialBytes.copyOf()

    val byteLength: Int
        get() = rootMaterialBytes.size

    companion object {
        internal fun create(rootMaterial: ByteArray): SkaldVaultV1RootMaterial =
            SkaldVaultV1RootMaterial(rootMaterial)
    }
}

object SkaldVaultV1Argon2idRootDerivation {
    const val MINIMUM_MEMORY_KIB = SkaldVaultV1HeaderCommitment.KDF_MEMORY_KIB
    const val MINIMUM_ITERATIONS = SkaldVaultV1HeaderCommitment.KDF_TIME_COST
    const val REQUIRED_PARALLELISM = SkaldVaultV1HeaderCommitment.KDF_PARALLELISM
    const val MINIMUM_SALT_BYTES = 16
    const val PREFERRED_NEW_VAULT_SALT_BYTES = 32
    const val ROOT_MATERIAL_BYTES = SkaldVaultV1HeaderCommitment.ARGON2ID_ROOT_MATERIAL_BYTES
    const val ARGON2_VERSION_19 = SkaldVaultV1HeaderCommitment.KDF_VERSION

    fun deriveRootMaterial(
        normalizedPassphrase: SkaldVaultV1NormalizedPassphrase,
        salt: ByteArray,
        parameters: SkaldVaultV1Argon2idParameters = SkaldVaultV1Argon2idParameters(),
    ): SkaldVaultV1Argon2idRootDerivationResult<SkaldVaultV1RootMaterial> =
        deriveRootMaterial(
            normalizedPassphraseBytes = normalizedPassphrase.utf8Bytes,
            salt = salt,
            parameters = parameters,
        )

    fun deriveRootMaterial(
        normalizedPassphraseBytes: ByteArray,
        salt: ByteArray,
        parameters: SkaldVaultV1Argon2idParameters = SkaldVaultV1Argon2idParameters(),
    ): SkaldVaultV1Argon2idRootDerivationResult<SkaldVaultV1RootMaterial> {
        validate(normalizedPassphraseBytes, salt, parameters)?.let { return rejected(it) }

        val rootMaterial = try {
            skaldVaultV1Argon2idRootMaterial(
                normalizedPassphraseBytes = normalizedPassphraseBytes.copyOf(),
                salt = salt.copyOf(),
                memoryKiB = parameters.memoryKiB,
                iterations = parameters.iterations,
                parallelism = parameters.parallelism,
                outputBytes = parameters.outputBytes,
            )
        } catch (_: Throwable) {
            return rejected(SkaldVaultV1Argon2idRootDerivationRejectionReason.ExecutionFailed)
        }

        return try {
            SkaldVaultV1Argon2idRootDerivationResult.Accepted(
                SkaldVaultV1RootMaterial.create(rootMaterial),
            )
        } finally {
            rootMaterial.fill(0)
        }
    }

    private fun validate(
        normalizedPassphraseBytes: ByteArray,
        salt: ByteArray,
        parameters: SkaldVaultV1Argon2idParameters,
    ): SkaldVaultV1Argon2idRootDerivationRejectionReason? =
        when {
            normalizedPassphraseBytes.isEmpty() ->
                SkaldVaultV1Argon2idRootDerivationRejectionReason.EmptyPassphraseBytes
            parameters.type != SkaldVaultV1Argon2idType.Argon2id ->
                SkaldVaultV1Argon2idRootDerivationRejectionReason.UnsupportedArgon2Type
            parameters.version != ARGON2_VERSION_19 ->
                SkaldVaultV1Argon2idRootDerivationRejectionReason.UnsupportedArgon2Version
            parameters.memoryKiB < MINIMUM_MEMORY_KIB ->
                SkaldVaultV1Argon2idRootDerivationRejectionReason.MemoryBelowV1Floor
            parameters.iterations < MINIMUM_ITERATIONS ->
                SkaldVaultV1Argon2idRootDerivationRejectionReason.IterationsBelowV1Floor
            parameters.parallelism != REQUIRED_PARALLELISM ->
                SkaldVaultV1Argon2idRootDerivationRejectionReason.UnsupportedParallelism
            salt.size < MINIMUM_SALT_BYTES ->
                SkaldVaultV1Argon2idRootDerivationRejectionReason.SaltTooShort
            parameters.outputBytes != ROOT_MATERIAL_BYTES ->
                SkaldVaultV1Argon2idRootDerivationRejectionReason.UnsupportedOutputLength
            else -> null
        }

    private fun rejected(
        reason: SkaldVaultV1Argon2idRootDerivationRejectionReason,
    ): SkaldVaultV1Argon2idRootDerivationResult.Rejected =
        SkaldVaultV1Argon2idRootDerivationResult.Rejected(
            reason = reason,
            safeMessage = "Skald Vault v1 Argon2id root derivation rejected: ${reason.label}.",
        )
}
