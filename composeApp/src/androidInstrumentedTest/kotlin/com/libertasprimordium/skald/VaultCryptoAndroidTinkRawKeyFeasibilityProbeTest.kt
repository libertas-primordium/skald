package com.libertasprimordium.skald

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.crypto.tink.Aead
import com.google.crypto.tink.InsecureSecretKeyAccess
import com.google.crypto.tink.KeyStatus
import com.google.crypto.tink.KeysetHandle
import com.google.crypto.tink.RegistryConfiguration
import com.google.crypto.tink.aead.AeadConfig
import com.google.crypto.tink.aead.XChaCha20Poly1305Key
import com.google.crypto.tink.aead.XChaCha20Poly1305Parameters
import com.google.crypto.tink.util.SecretBytes
import com.libertasprimordium.skald.security.ProductionProviderAndroidTinkRawKeyFeasibilityStatus
import com.libertasprimordium.skald.security.commonProductionProviderAcceptanceContract
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VaultCryptoAndroidTinkRawKeyFeasibilityProbeTest {
    @Test
    fun androidPublicTinkApiCanConstructXChaChaPrimitiveFromCallerSuppliedRawKeyBytes() {
        AeadConfig.register()
        val fixedNonSecretKeyBytes = fixedRawKeyBytes()
        val fixedNonSecretPlaintext = "skald fixed non-secret raw-key probe plaintext".encodeToByteArray()
        val fixedNonSecretAssociatedData = "skald fixed non-secret raw-key probe aad".encodeToByteArray()
        val fixedNonSecretWrongAssociatedData = "skald fixed non-secret wrong probe aad".encodeToByteArray()

        val key = XChaCha20Poly1305Key.create(
            XChaCha20Poly1305Parameters.Variant.NO_PREFIX,
            SecretBytes.copyFrom(fixedNonSecretKeyBytes, InsecureSecretKeyAccess.get()),
            null,
        )
        val keysetHandle = KeysetHandle.newBuilder()
            .addEntry(
                KeysetHandle.importKey(key)
                    .withFixedId(FIXED_NON_SECRET_KEY_ID)
                    .setStatus(KeyStatus.ENABLED)
                    .makePrimary(),
            )
            .build()
        val aead = keysetHandle.getPrimitive(RegistryConfiguration.get(), Aead::class.java)

        val ciphertext = aead.encrypt(fixedNonSecretPlaintext, fixedNonSecretAssociatedData)
        val decrypted = aead.decrypt(ciphertext, fixedNonSecretAssociatedData)
        val wrongAadResult = runCatching {
            aead.decrypt(ciphertext, fixedNonSecretWrongAssociatedData)
        }

        assertEquals(1, keysetHandle.size())
        assertArrayEquals(fixedNonSecretPlaintext, decrypted)
        assertFalse(ciphertext.contentEquals(fixedNonSecretPlaintext))
        assertTrue(wrongAadResult.isFailure)
    }

    @Test
    fun acceptanceContractRecordsAndroidPublicRawKeyApiWithoutProductionEnablement() {
        val policy = commonProductionProviderAcceptanceContract().tinkRawKeyHandlingPolicy

        assertEquals(
            ProductionProviderAndroidTinkRawKeyFeasibilityStatus.ANDROID_FEASIBLE_PUBLIC_RAW_KEY_API,
            policy.androidFeasibilityStatus,
        )
        assertTrue(policy.androidFeasibilityStatus.satisfiesAndroidFeasibilityGate)
        assertTrue(policy.androidPathMatchesDesktopPath)
        assertTrue(policy.crossPlatformFeasibilitySatisfied)
        assertTrue(policy.transientInMemoryTinkKeysetHandleRequired)
        assertFalse(policy.persistedTinkKeysetRequired)
        assertFalse(policy.randomTinkGeneratedVaultKeyRequired)
        assertFalse(policy.persistedPlaintextTinkKeysetsAllowed)
        assertFalse(policy.persistedEncryptedTinkKeysetsAllowedInV1)
        assertFalse(policy.randomTinkVaultKeysAllowed)
        assertFalse(policy.tinkKeyRotationInV1Allowed)
        assertFalse(policy.multipleActiveAeadKeysInV1Allowed)
        assertFalse(policy.internalUnsupportedReflectiveApisAllowed)
        assertFalse(policy.productionAeadExecutionImplemented)
    }

    private fun fixedRawKeyBytes(): ByteArray =
        byteArrayOf(
            0x00, 0x01, 0x02, 0x03,
            0x04, 0x05, 0x06, 0x07,
            0x08, 0x09, 0x0a, 0x0b,
            0x0c, 0x0d, 0x0e, 0x0f,
            0x10, 0x11, 0x12, 0x13,
            0x14, 0x15, 0x16, 0x17,
            0x18, 0x19, 0x1a, 0x1b,
            0x1c, 0x1d, 0x1e, 0x1f,
        )

    private companion object {
        const val FIXED_NON_SECRET_KEY_ID = 0x534b414c
    }
}
