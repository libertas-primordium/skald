package com.libertasprimordium.skald

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.libertasprimordium.skald.security.SkaldVaultV1Argon2idParameters
import com.libertasprimordium.skald.security.SkaldVaultV1Argon2idRootDerivation
import com.libertasprimordium.skald.security.SkaldVaultV1Argon2idRootDerivationRejectionReason
import com.libertasprimordium.skald.security.SkaldVaultV1Argon2idRootDerivationResult
import com.libertasprimordium.skald.security.SkaldVaultV1PassphrasePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1PassphrasePolicyResult
import com.libertasprimordium.skald.security.SkaldVaultV1PassphraseRejectionReason
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VaultCryptoAndroidPassphraseArgon2idRootDerivationTest {
    @Test
    fun androidNfcNormalizationMatchesExpectedPassphrasePolicy() {
        val composed = acceptedBytes("\u00e9")
        val decomposed = acceptedBytes("e\u0301")
        val rejected = SkaldVaultV1PassphrasePolicy.normalizeAndEncode("android\u200bformat")

        assertArrayEquals(byteArrayOf(0xc3.toByte(), 0xa9.toByte()), composed)
        assertArrayEquals(composed, decomposed)
        val rejection = when (rejected) {
            is SkaldVaultV1PassphrasePolicyResult.Accepted -> error("Format character should be rejected.")
            is SkaldVaultV1PassphrasePolicyResult.Rejected -> rejected
        }
        assertEquals(SkaldVaultV1PassphraseRejectionReason.InvisibleFormatCharacter, rejection.reason)
        assertFalse(rejection.safeMessage.contains("android\u200bformat"))
    }

    @Test
    fun androidArgon2idRootDerivationMatchesFixedNonSecretFixture() {
        val normalized = when (val result = SkaldVaultV1PassphrasePolicy.normalizeAndEncode(FIXTURE_PASSPHRASE)) {
            is SkaldVaultV1PassphrasePolicyResult.Accepted -> result.value
            is SkaldVaultV1PassphrasePolicyResult.Rejected -> error(result.safeMessage)
        }
        val root = when (
            val result = SkaldVaultV1Argon2idRootDerivation.deriveRootMaterial(
                normalizedPassphrase = normalized,
                salt = FIXTURE_SALT_HEX.hexToBytes(),
                parameters = SkaldVaultV1Argon2idParameters(),
            )
        ) {
            is SkaldVaultV1Argon2idRootDerivationResult.Accepted -> result.value
            is SkaldVaultV1Argon2idRootDerivationResult.Rejected -> error(result.safeMessage)
        }

        assertEquals(SkaldVaultV1Argon2idRootDerivation.ROOT_MATERIAL_BYTES, root.byteLength)
        assertArrayEquals(EXPECTED_ROOT_MATERIAL_HEX.hexToBytes(), root.bytes)
    }

    @Test
    fun androidArgon2idInvalidFloorParamsFailClosed() {
        val result = SkaldVaultV1Argon2idRootDerivation.deriveRootMaterial(
            normalizedPassphraseBytes = FIXTURE_PASSPHRASE.encodeToByteArray(),
            salt = FIXTURE_SALT_HEX.hexToBytes(),
            parameters = SkaldVaultV1Argon2idParameters(memoryKiB = 65_535),
        )
        val rejection = when (result) {
            is SkaldVaultV1Argon2idRootDerivationResult.Accepted -> error("Memory floor violation should fail.")
            is SkaldVaultV1Argon2idRootDerivationResult.Rejected -> result
        }

        assertEquals(SkaldVaultV1Argon2idRootDerivationRejectionReason.MemoryBelowV1Floor, rejection.reason)
        assertFalse(rejection.safeMessage.contains(FIXTURE_PASSPHRASE))
        assertFalse(rejection.safeMessage.contains(EXPECTED_ROOT_MATERIAL_HEX))
    }

    private fun acceptedBytes(passphrase: String): ByteArray =
        when (val result = SkaldVaultV1PassphrasePolicy.normalizeAndEncode(passphrase)) {
            is SkaldVaultV1PassphrasePolicyResult.Accepted -> result.value.utf8Bytes
            is SkaldVaultV1PassphrasePolicyResult.Rejected -> error(result.safeMessage)
        }

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
