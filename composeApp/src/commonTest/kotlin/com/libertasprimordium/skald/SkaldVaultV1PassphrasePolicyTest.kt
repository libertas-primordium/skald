package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.SkaldVaultV1NormalizedPassphrase
import com.libertasprimordium.skald.security.SkaldVaultV1PassphrasePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1PassphrasePolicyResult
import com.libertasprimordium.skald.security.SkaldVaultV1PassphraseRejectionReason
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SkaldVaultV1PassphrasePolicyTest {
    @Test
    fun policyIdIsStable() {
        assertEquals(
            "unicode-nfc-utf8-no-controls-no-whitespace-v1",
            SkaldVaultV1PassphrasePolicy.POLICY_ID,
        )
    }

    @Test
    fun visibleAsciiLettersDigitsAndPunctuationAreAccepted() {
        assertContentEquals("Letters".encodeToByteArray(), acceptedBytes("Letters"))
        assertContentEquals("1234567890".encodeToByteArray(), acceptedBytes("1234567890"))
        assertContentEquals("!#$%&()*+,/:;<=>?@[]^`{|}~".encodeToByteArray(), acceptedBytes("!#$%&()*+,/:;<=>?@[]^`{|}~"))
        assertContentEquals("hyphen-period_under".encodeToByteArray(), acceptedBytes("hyphen-period_under"))
    }

    @Test
    fun whitespaceSeparatorsControlsAndInvisibleFormatCharactersAreRejected() {
        assertRejected("", SkaldVaultV1PassphraseRejectionReason.EmptyPassphrase)
        assertRejected(" leading", SkaldVaultV1PassphraseRejectionReason.UnicodeWhitespaceCharacter)
        assertRejected("trailing ", SkaldVaultV1PassphraseRejectionReason.UnicodeWhitespaceCharacter)
        assertRejected("internal space", SkaldVaultV1PassphraseRejectionReason.UnicodeWhitespaceCharacter)
        assertRejected(
            "tab\tvalue",
            SkaldVaultV1PassphraseRejectionReason.UnicodeControlCharacter,
            SkaldVaultV1PassphraseRejectionReason.UnicodeWhitespaceCharacter,
        )
        assertRejected(
            "line\nvalue",
            SkaldVaultV1PassphraseRejectionReason.UnicodeControlCharacter,
            SkaldVaultV1PassphraseRejectionReason.UnicodeWhitespaceCharacter,
        )
        assertRejected(
            "line\rvalue",
            SkaldVaultV1PassphraseRejectionReason.UnicodeControlCharacter,
            SkaldVaultV1PassphraseRejectionReason.UnicodeWhitespaceCharacter,
        )
        assertRejected(
            "non\u00a0breaking",
            SkaldVaultV1PassphraseRejectionReason.UnicodeSeparatorCharacter,
            SkaldVaultV1PassphraseRejectionReason.UnicodeWhitespaceCharacter,
        )
        assertRejected("zero\u200bwidth", SkaldVaultV1PassphraseRejectionReason.InvisibleFormatCharacter)
        assertRejected("bell\u0007value", SkaldVaultV1PassphraseRejectionReason.UnicodeControlCharacter)
        assertRejected("word\u2060joiner", SkaldVaultV1PassphraseRejectionReason.InvisibleFormatCharacter)
        assertRejected("\ud83d", SkaldVaultV1PassphraseRejectionReason.MalformedUnicodeScalar)
    }

    @Test
    fun composedAndDecomposedAccentedFormsNormalizeToSameNfcUtf8Bytes() {
        val composed = acceptedBytes("\u00e9")
        val decomposed = acceptedBytes("e\u0301")

        assertContentEquals(byteArrayOf(0xc3.toByte(), 0xa9.toByte()), composed)
        assertContentEquals(composed, decomposed)
    }

    @Test
    fun visibleCharactersAreNotTrimmedCasedCollapsedOrLocaleTransformed() {
        assertContentEquals("-Edge-".encodeToByteArray(), acceptedBytes("-Edge-"))
        assertContentEquals("AaAa".encodeToByteArray(), acceptedBytes("AaAa"))
        assertContentEquals("I\u0130i".encodeToByteArray(), acceptedBytes("I\u0130i"))
        assertContentEquals("Repeat---___".encodeToByteArray(), acceptedBytes("Repeat---___"))
    }

    @Test
    fun emojiIsAcceptedWhenItContainsNoForbiddenCharacters() {
        assertContentEquals("\ud83d\udd10".encodeToByteArray(), acceptedBytes("\ud83d\udd10"))
    }

    @Test
    fun acceptedPassphraseBytesAreDefensiveCopies() {
        val accepted = accepted("Copy_Check")
        val first = accepted.utf8Bytes

        first[0] = 0

        assertContentEquals("Copy_Check".encodeToByteArray(), accepted.utf8Bytes)
    }

    private fun acceptedBytes(passphrase: String): ByteArray =
        accepted(passphrase).utf8Bytes

    private fun accepted(passphrase: String): SkaldVaultV1NormalizedPassphrase =
        when (val result = SkaldVaultV1PassphrasePolicy.normalizeAndEncode(passphrase)) {
            is SkaldVaultV1PassphrasePolicyResult.Accepted -> result.value
            is SkaldVaultV1PassphrasePolicyResult.Rejected -> error(result.safeMessage)
        }

    private fun assertRejected(
        passphrase: String,
        vararg expectedReasons: SkaldVaultV1PassphraseRejectionReason,
    ) {
        val result = SkaldVaultV1PassphrasePolicy.normalizeAndEncode(passphrase)
        val rejected = when (result) {
            is SkaldVaultV1PassphrasePolicyResult.Accepted -> error("Passphrase should have been rejected.")
            is SkaldVaultV1PassphrasePolicyResult.Rejected -> result
        }

        assertTrue(expectedReasons.contains(rejected.reason))
        if (passphrase.isNotEmpty()) {
            assertFalse(rejected.safeMessage.contains(passphrase))
        }
    }
}
