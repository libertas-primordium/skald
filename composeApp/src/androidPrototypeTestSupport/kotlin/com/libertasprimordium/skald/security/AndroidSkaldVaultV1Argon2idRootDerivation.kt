package com.libertasprimordium.skald.security

import org.bouncycastle.crypto.generators.Argon2BytesGenerator
import org.bouncycastle.crypto.params.Argon2Parameters

internal actual fun skaldVaultV1Argon2idRootMaterial(
    normalizedPassphraseBytes: ByteArray,
    salt: ByteArray,
    memoryKiB: Int,
    iterations: Int,
    parallelism: Int,
    outputBytes: Int,
): ByteArray {
    val passphraseCopy = normalizedPassphraseBytes.copyOf()
    val saltCopy = salt.copyOf()
    val output = ByteArray(outputBytes)
    val parameters = Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
        .withVersion(Argon2Parameters.ARGON2_VERSION_13)
        .withMemoryAsKB(memoryKiB)
        .withIterations(iterations)
        .withParallelism(parallelism)
        .withSalt(saltCopy)
        .build()

    try {
        val generator = Argon2BytesGenerator()
        generator.init(parameters)
        generator.generateBytes(passphraseCopy, output)
        return output
    } finally {
        parameters.clear()
        passphraseCopy.fill(0)
        saltCopy.fill(0)
    }
}
