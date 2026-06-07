package com.libertasprimordium.skald.security

import com.google.crypto.tink.aead.XChaCha20Poly1305Key
import com.google.crypto.tink.aead.internal.InsecureNonceXChaCha20Poly1305
import org.bouncycastle.crypto.generators.Argon2BytesGenerator
import org.bouncycastle.crypto.modes.ChaCha20Poly1305

internal object AndroidVaultCryptoDependencyCompileProbe {
    val availableApiClassNames: List<String> =
        listOf(
            XChaCha20Poly1305Key::class.java.name,
            InsecureNonceXChaCha20Poly1305::class.java.name,
            Argon2BytesGenerator::class.java.name,
            ChaCha20Poly1305::class.java.name,
        )

    val note: String =
        "Android compile probe only; no vault encryption, key derivation, storage, or persistence is performed."
}
