package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.SkaldVaultV1Argon2idParameters
import com.libertasprimordium.skald.security.SkaldVaultV1Argon2idRootDerivation
import com.libertasprimordium.skald.security.SkaldVaultV1Argon2idRootDerivationRejectionReason
import com.libertasprimordium.skald.security.SkaldVaultV1Argon2idRootDerivationResult
import com.libertasprimordium.skald.security.SkaldVaultV1Argon2idType
import com.libertasprimordium.skald.security.SkaldVaultV1HeaderCommitment
import com.libertasprimordium.skald.security.SkaldVaultV1HeaderCommitmentResult
import com.libertasprimordium.skald.security.SkaldVaultV1PassphrasePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1PassphrasePolicyResult
import com.libertasprimordium.skald.security.SkaldVaultV1RootMaterial
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SkaldVaultV1Argon2idRootDerivationTest {
    @Test
    fun fixedNonSecretFixtureProducesDeterministic64ByteRootMaterial() {
        val root = deriveFixtureRoot()

        assertEquals(SkaldVaultV1Argon2idRootDerivation.ROOT_MATERIAL_BYTES, root.byteLength)
        assertEquals(EXPECTED_ROOT_MATERIAL_HEX, root.bytes.toHex())
    }

    @Test
    fun argon2idVersionAndFloorConstantsMatchV1Policy() {
        assertEquals(19, SkaldVaultV1Argon2idRootDerivation.ARGON2_VERSION_19)
        assertEquals(65_536, SkaldVaultV1Argon2idRootDerivation.MINIMUM_MEMORY_KIB)
        assertEquals(3, SkaldVaultV1Argon2idRootDerivation.MINIMUM_ITERATIONS)
        assertEquals(1, SkaldVaultV1Argon2idRootDerivation.REQUIRED_PARALLELISM)
        assertEquals(16, SkaldVaultV1Argon2idRootDerivation.MINIMUM_SALT_BYTES)
        assertEquals(32, SkaldVaultV1Argon2idRootDerivation.PREFERRED_NEW_VAULT_SALT_BYTES)
        assertEquals(64, SkaldVaultV1Argon2idRootDerivation.ROOT_MATERIAL_BYTES)
    }

    @Test
    fun invalidParametersFailClosedBeforeDerivation() {
        assertRejected(
            parameters = SkaldVaultV1Argon2idParameters(memoryKiB = 65_535),
            reason = SkaldVaultV1Argon2idRootDerivationRejectionReason.MemoryBelowV1Floor,
        )
        assertRejected(
            parameters = SkaldVaultV1Argon2idParameters(iterations = 2),
            reason = SkaldVaultV1Argon2idRootDerivationRejectionReason.IterationsBelowV1Floor,
        )
        assertRejected(
            parameters = SkaldVaultV1Argon2idParameters(parallelism = 2),
            reason = SkaldVaultV1Argon2idRootDerivationRejectionReason.UnsupportedParallelism,
        )
        assertRejected(
            salt = ByteArray(15) { it.toByte() },
            reason = SkaldVaultV1Argon2idRootDerivationRejectionReason.SaltTooShort,
        )
        assertRejected(
            parameters = SkaldVaultV1Argon2idParameters(type = SkaldVaultV1Argon2idType.Argon2i),
            reason = SkaldVaultV1Argon2idRootDerivationRejectionReason.UnsupportedArgon2Type,
        )
        assertRejected(
            parameters = SkaldVaultV1Argon2idParameters(version = 16),
            reason = SkaldVaultV1Argon2idRootDerivationRejectionReason.UnsupportedArgon2Version,
        )
        assertRejected(
            parameters = SkaldVaultV1Argon2idParameters(outputBytes = 32),
            reason = SkaldVaultV1Argon2idRootDerivationRejectionReason.UnsupportedOutputLength,
        )
        assertRejected(
            passphraseBytes = ByteArray(0),
            reason = SkaldVaultV1Argon2idRootDerivationRejectionReason.EmptyPassphraseBytes,
        )
    }

    @Test
    fun rootMaterialBytesAreDefensiveCopies() {
        val root = deriveFixtureRoot()
        val first = root.bytes

        first[0] = 0

        assertContentEquals(EXPECTED_ROOT_MATERIAL_HEX.hexToBytes(), root.bytes)
    }

    @Test
    fun fixedRootMaterialCanFeedExistingHkdfHmacBuildingBlocksInTestScopeOnly() {
        val root = deriveFixtureRoot().bytes
        val header = SkaldVaultV1HeaderCommitment.vectorFixtureHeader()
        val canonicalHeader = acceptedHeaderValue(SkaldVaultV1HeaderCommitment.canonicalHeaderBytes(header))
        val expandedKeys = acceptedHeaderValue(
            SkaldVaultV1HeaderCommitment.expandRootMaterial(rootMaterial = root, header = header),
        )
        val tag = acceptedHeaderValue(
            SkaldVaultV1HeaderCommitment.computeHeaderCommitment(
                headerCommitmentKey = expandedKeys.headerCommitmentKey,
                canonicalHeaderBytes = canonicalHeader,
            ),
        )

        assertTrue(
            acceptedHeaderValue(
                SkaldVaultV1HeaderCommitment.verifyHeaderCommitment(
                    headerCommitmentKey = expandedKeys.headerCommitmentKey,
                    canonicalHeaderBytes = canonicalHeader,
                    expectedTag = tag,
                ),
            ),
        )
        assertFalse(
            acceptedHeaderValue(
                SkaldVaultV1HeaderCommitment.verifyHeaderCommitment(
                    headerCommitmentKey = expandedKeys.headerCommitmentKey,
                    canonicalHeaderBytes = canonicalHeader.copyOf().also { it[it.lastIndex] = 1 },
                    expectedTag = tag,
                ),
            ),
        )
    }

    private fun deriveFixtureRoot(): SkaldVaultV1RootMaterial {
        val normalized = when (
            val result = SkaldVaultV1PassphrasePolicy.normalizeAndEncode(FIXTURE_PASSPHRASE)
        ) {
            is SkaldVaultV1PassphrasePolicyResult.Accepted -> result.value
            is SkaldVaultV1PassphrasePolicyResult.Rejected -> error(result.safeMessage)
        }
        return when (
            val result = SkaldVaultV1Argon2idRootDerivation.deriveRootMaterial(
                normalizedPassphrase = normalized,
                salt = FIXTURE_SALT_HEX.hexToBytes(),
                parameters = SkaldVaultV1Argon2idParameters(),
            )
        ) {
            is SkaldVaultV1Argon2idRootDerivationResult.Accepted -> result.value
            is SkaldVaultV1Argon2idRootDerivationResult.Rejected -> error(result.safeMessage)
        }
    }

    private fun assertRejected(
        passphraseBytes: ByteArray = FIXTURE_PASSPHRASE.encodeToByteArray(),
        salt: ByteArray = FIXTURE_SALT_HEX.hexToBytes(),
        parameters: SkaldVaultV1Argon2idParameters = SkaldVaultV1Argon2idParameters(),
        reason: SkaldVaultV1Argon2idRootDerivationRejectionReason,
    ) {
        val result = SkaldVaultV1Argon2idRootDerivation.deriveRootMaterial(
            normalizedPassphraseBytes = passphraseBytes,
            salt = salt,
            parameters = parameters,
        )
        val rejected = when (result) {
            is SkaldVaultV1Argon2idRootDerivationResult.Accepted -> error("Argon2id input should fail.")
            is SkaldVaultV1Argon2idRootDerivationResult.Rejected -> result
        }

        assertEquals(reason, rejected.reason)
        assertFalse(rejected.safeMessage.contains(FIXTURE_PASSPHRASE))
        assertFalse(rejected.safeMessage.contains(EXPECTED_ROOT_MATERIAL_HEX))
    }

    private fun <T> acceptedHeaderValue(result: SkaldVaultV1HeaderCommitmentResult<T>): T =
        when (result) {
            is SkaldVaultV1HeaderCommitmentResult.Accepted -> result.value
            is SkaldVaultV1HeaderCommitmentResult.Rejected -> error(result.safeMessage)
        }

    private fun ByteArray.toHex(): String =
        joinToString(separator = "") { byte -> "%02x".format(byte.toInt() and 0xff) }

    private fun String.hexToBytes(): ByteArray {
        require(length % 2 == 0)
        return chunked(2).map { it.toInt(16).toByte() }.toByteArray()
    }

    private companion object {
        const val FIXTURE_PASSPHRASE = "Skald-Vault.Test_Fixture-01"
        const val FIXTURE_SALT_HEX =
            "000102030405060708090a0b0c0d0e0f101112131415161718191a1b1c1d1e1f"
        const val EXPECTED_ROOT_MATERIAL_HEX =
            "36686ff5939587fce8eafdc430767fa36427ecc80b7eca0ac050fc3813fe754a" +
                "982187d6315ae1e779d6479f486e9a3ec99c059371477497464302dc9167cff7"
    }
}
