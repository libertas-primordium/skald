package com.libertasprimordium.skald

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.libertasprimordium.skald.security.SkaldVaultV1RecordAead
import com.libertasprimordium.skald.security.SkaldVaultV1RecordAeadRejectionReason
import com.libertasprimordium.skald.security.SkaldVaultV1RecordAeadResult
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VaultCryptoAndroidRecordAeadBuildingBlockTest {
    @Test
    fun androidRecordAeadBuildingBlockRoundTripsAndRejectsWrongAad() {
        val ciphertext = when (
            val result = SkaldVaultV1RecordAead.encryptRecord(
                recordAeadKey = SkaldVaultV1RecordAead.VECTOR_RECORD_AEAD_KEY,
                plaintext = SkaldVaultV1RecordAead.VECTOR_PLAINTEXT,
                aadContext = SkaldVaultV1RecordAead.vectorFixtureAadContext(),
            )
        ) {
            is SkaldVaultV1RecordAeadResult.Accepted -> result.value
            is SkaldVaultV1RecordAeadResult.Rejected -> error(result.safeMessage)
        }
        val decrypted = when (
            val result = SkaldVaultV1RecordAead.decryptRecord(
                recordAeadKey = SkaldVaultV1RecordAead.VECTOR_RECORD_AEAD_KEY,
                ciphertext = ciphertext.bytes,
                aadContext = SkaldVaultV1RecordAead.vectorFixtureAadContext(),
            )
        ) {
            is SkaldVaultV1RecordAeadResult.Accepted -> result.value
            is SkaldVaultV1RecordAeadResult.Rejected -> error(result.safeMessage)
        }
        val wrongAad = SkaldVaultV1RecordAead.decryptRecord(
            recordAeadKey = SkaldVaultV1RecordAead.VECTOR_RECORD_AEAD_KEY,
            ciphertext = ciphertext.bytes,
            aadContext = SkaldVaultV1RecordAead.vectorFixtureAadContext().copy(recordVersionCounter = 8),
        )

        assertArrayEquals(SkaldVaultV1RecordAead.VECTOR_PLAINTEXT, decrypted.bytes)
        assertTrue(wrongAad is SkaldVaultV1RecordAeadResult.Rejected)
        assertEquals(
            SkaldVaultV1RecordAeadRejectionReason.AeadDecryptionFailed,
            (wrongAad as SkaldVaultV1RecordAeadResult.Rejected).reason,
        )
    }
}
