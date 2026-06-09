package com.libertasprimordium.skald.security

import kotlin.text.CharCategory

internal expect fun skaldVaultV1NormalizeNfc(input: String): String

enum class SkaldVaultV1PassphraseRejectionReason(val label: String) {
    EmptyPassphrase("empty passphrase"),
    UnicodeControlCharacter("Unicode control character"),
    UnicodeWhitespaceCharacter("Unicode whitespace character"),
    UnicodeSeparatorCharacter("Unicode separator character"),
    InvisibleFormatCharacter("invisible format character"),
    MalformedUnicodeScalar("malformed Unicode scalar"),
    NormalizationFailed("Unicode normalization failed"),
}

sealed class SkaldVaultV1PassphrasePolicyResult<out T> {
    data class Accepted<out T>(val value: T) : SkaldVaultV1PassphrasePolicyResult<T>()

    data class Rejected(
        val reason: SkaldVaultV1PassphraseRejectionReason,
        val safeMessage: String,
    ) : SkaldVaultV1PassphrasePolicyResult<Nothing>()
}

class SkaldVaultV1NormalizedPassphrase private constructor(
    utf8Bytes: ByteArray,
) {
    private val encodedBytes: ByteArray = utf8Bytes.copyOf()

    val utf8Bytes: ByteArray
        get() = encodedBytes.copyOf()

    val byteLength: Int
        get() = encodedBytes.size

    companion object {
        internal fun create(utf8Bytes: ByteArray): SkaldVaultV1NormalizedPassphrase =
            SkaldVaultV1NormalizedPassphrase(utf8Bytes)
    }
}

object SkaldVaultV1PassphrasePolicy {
    const val POLICY_ID = "unicode-nfc-utf8-no-controls-no-whitespace-v1"

    fun normalizeAndEncode(
        passphrase: String,
    ): SkaldVaultV1PassphrasePolicyResult<SkaldVaultV1NormalizedPassphrase> {
        if (passphrase.isEmpty()) {
            return rejected(SkaldVaultV1PassphraseRejectionReason.EmptyPassphrase)
        }
        if (hasMalformedSurrogatePair(passphrase)) {
            return rejected(SkaldVaultV1PassphraseRejectionReason.MalformedUnicodeScalar)
        }

        val normalized = try {
            skaldVaultV1NormalizeNfc(passphrase)
        } catch (_: Throwable) {
            return rejected(SkaldVaultV1PassphraseRejectionReason.NormalizationFailed)
        }

        if (normalized.isEmpty()) {
            return rejected(SkaldVaultV1PassphraseRejectionReason.EmptyPassphrase)
        }
        if (hasMalformedSurrogatePair(normalized)) {
            return rejected(SkaldVaultV1PassphraseRejectionReason.MalformedUnicodeScalar)
        }

        normalized.forEach { character ->
            rejectForbidden(character)?.let { reason -> return rejected(reason) }
        }

        return SkaldVaultV1PassphrasePolicyResult.Accepted(
            SkaldVaultV1NormalizedPassphrase.create(normalized.encodeToByteArray()),
        )
    }

    private fun rejectForbidden(
        character: Char,
    ): SkaldVaultV1PassphraseRejectionReason? {
        if (character.category == CharCategory.CONTROL) {
            return SkaldVaultV1PassphraseRejectionReason.UnicodeControlCharacter
        }
        if (character.isWhitespace()) {
            return SkaldVaultV1PassphraseRejectionReason.UnicodeWhitespaceCharacter
        }
        if (
            character.category == CharCategory.SPACE_SEPARATOR ||
            character.category == CharCategory.LINE_SEPARATOR ||
            character.category == CharCategory.PARAGRAPH_SEPARATOR
        ) {
            return SkaldVaultV1PassphraseRejectionReason.UnicodeSeparatorCharacter
        }
        if (character.category == CharCategory.FORMAT) {
            return SkaldVaultV1PassphraseRejectionReason.InvisibleFormatCharacter
        }
        return null
    }

    private fun hasMalformedSurrogatePair(value: String): Boolean {
        var index = 0
        while (index < value.length) {
            val character = value[index]
            when {
                character.isHighSurrogateCodeUnit() -> {
                    if (index + 1 >= value.length || !value[index + 1].isLowSurrogateCodeUnit()) {
                        return true
                    }
                    index += 2
                }
                character.isLowSurrogateCodeUnit() -> return true
                else -> index += 1
            }
        }
        return false
    }

    private fun Char.isHighSurrogateCodeUnit(): Boolean =
        code in 0xd800..0xdbff

    private fun Char.isLowSurrogateCodeUnit(): Boolean =
        code in 0xdc00..0xdfff

    private fun rejected(
        reason: SkaldVaultV1PassphraseRejectionReason,
    ): SkaldVaultV1PassphrasePolicyResult.Rejected =
        SkaldVaultV1PassphrasePolicyResult.Rejected(
            reason = reason,
            safeMessage = "Skald Vault v1 passphrase rejected: ${reason.label}.",
        )
}
