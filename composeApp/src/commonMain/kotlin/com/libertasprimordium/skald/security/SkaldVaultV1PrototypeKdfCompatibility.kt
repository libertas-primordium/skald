package com.libertasprimordium.skald.security

/**
 * Material-free compatibility values for unsupported P0 policy evidence.
 * The historical 64-byte output is not a canonical vault root or approved
 * production KDF setting. Canonical v1 derives a 32-byte KEK and wraps an
 * independent random 32-byte vault root; its production parameters remain deferred.
 */
object SkaldVaultV1PrototypeKdfCompatibility {
    const val ARGON2_VERSION_19 = 19
    const val MEMORY_KIB = 65_536
    const val ITERATIONS = 3
    const val PARALLELISM = 1
    const val OUTPUT_BYTES = 64
}

enum class SkaldVaultV1Argon2idType(val label: String) {
    Argon2id("Argon2id"),
    Argon2i("Argon2i"),
    Argon2d("Argon2d"),
}

data class SkaldVaultV1Argon2idParameters(
    val type: SkaldVaultV1Argon2idType = SkaldVaultV1Argon2idType.Argon2id,
    val version: Int = SkaldVaultV1PrototypeKdfCompatibility.ARGON2_VERSION_19,
    val memoryKiB: Int = SkaldVaultV1PrototypeKdfCompatibility.MEMORY_KIB,
    val iterations: Int = SkaldVaultV1PrototypeKdfCompatibility.ITERATIONS,
    val parallelism: Int = SkaldVaultV1PrototypeKdfCompatibility.PARALLELISM,
    val outputBytes: Int = SkaldVaultV1PrototypeKdfCompatibility.OUTPUT_BYTES,
)
