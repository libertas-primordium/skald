package com.libertasprimordium.skald.security

import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

internal actual fun skaldVaultV1HmacSha256(
    key: ByteArray,
    message: ByteArray,
): ByteArray {
    val mac = Mac.getInstance("HmacSHA256")
    mac.init(SecretKeySpec(key, "HmacSHA256"))
    return mac.doFinal(message)
}
