package com.libertasprimordium.skald.security

import java.text.Normalizer

internal actual fun skaldVaultV1NormalizeNfc(input: String): String =
    Normalizer.normalize(input, Normalizer.Form.NFC)
