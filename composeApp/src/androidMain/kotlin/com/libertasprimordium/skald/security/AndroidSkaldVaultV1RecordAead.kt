package com.libertasprimordium.skald.security

import com.google.crypto.tink.Aead
import com.google.crypto.tink.InsecureSecretKeyAccess
import com.google.crypto.tink.KeyStatus
import com.google.crypto.tink.KeysetHandle
import com.google.crypto.tink.RegistryConfiguration
import com.google.crypto.tink.aead.AeadConfig
import com.google.crypto.tink.aead.XChaCha20Poly1305Key
import com.google.crypto.tink.aead.XChaCha20Poly1305Parameters
import com.google.crypto.tink.util.SecretBytes

internal actual fun skaldVaultV1TinkRecordAeadEncrypt(
    recordAeadKey: ByteArray,
    plaintext: ByteArray,
    associatedData: ByteArray,
): ByteArray =
    skaldVaultV1RecordAeadPrimitive(recordAeadKey).encrypt(plaintext, associatedData)

internal actual fun skaldVaultV1TinkRecordAeadDecrypt(
    recordAeadKey: ByteArray,
    ciphertext: ByteArray,
    associatedData: ByteArray,
): ByteArray =
    skaldVaultV1RecordAeadPrimitive(recordAeadKey).decrypt(ciphertext, associatedData)

private fun skaldVaultV1RecordAeadPrimitive(recordAeadKey: ByteArray): Aead {
    AeadConfig.register()
    val keyBytes = recordAeadKey.copyOf()
    try {
        val key = XChaCha20Poly1305Key.create(
            XChaCha20Poly1305Parameters.Variant.NO_PREFIX,
            SecretBytes.copyFrom(keyBytes, InsecureSecretKeyAccess.get()),
            null,
        )
        return KeysetHandle.newBuilder()
            .addEntry(
                KeysetHandle.importKey(key)
                    .withFixedId(FIXED_NON_SECRET_RECORD_AEAD_KEY_ID)
                    .setStatus(KeyStatus.ENABLED)
                    .makePrimary(),
            )
            .build()
            .getPrimitive(RegistryConfiguration.get(), Aead::class.java)
    } finally {
        keyBytes.fill(0)
    }
}

private const val FIXED_NON_SECRET_RECORD_AEAD_KEY_ID = 0x534b5241
