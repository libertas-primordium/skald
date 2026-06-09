package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.ProductionProviderDeterministicKatVector
import com.libertasprimordium.skald.security.ProductionProviderRandomizedAeadBehavioralKatCheck
import com.libertasprimordium.skald.security.SkaldVaultV1CanonicalHeader
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderKatEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderKatFailureReason
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderKatFixtures
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderKatHarnessResult
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderKatRecordAeadAdapter
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderKatRecordAeadBuildingBlock
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderKatStage
import com.libertasprimordium.skald.security.SkaldVaultV1RecordAadContext
import com.libertasprimordium.skald.security.SkaldVaultV1RecordAeadResult
import com.libertasprimordium.skald.security.SkaldVaultV1RecordCiphertext
import com.libertasprimordium.skald.security.SkaldVaultV1RecordPlaintext
import com.libertasprimordium.skald.security.SkaldVaultV1StillDisabledProviderKatHarness
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SkaldVaultV1StillDisabledProviderKatHarnessTest {
    @Test
    fun integratedProviderKatSucceedsWithFixedNonSecretFixtures() {
        val evidence = accepted(SkaldVaultV1StillDisabledProviderKatHarness().runProviderLevelKat())

        assertEquals(
            listOf(
                SkaldVaultV1ProviderKatStage.PassphrasePolicyValidated,
                SkaldVaultV1ProviderKatStage.Argon2idRootMaterialDerived,
                SkaldVaultV1ProviderKatStage.HkdfSubkeysDerived,
                SkaldVaultV1ProviderKatStage.CanonicalHeaderSerialized,
                SkaldVaultV1ProviderKatStage.HeaderCommitmentVerified,
                SkaldVaultV1ProviderKatStage.StrictAadSerialized,
                SkaldVaultV1ProviderKatStage.RecordAeadRoundTripCompleted,
                SkaldVaultV1ProviderKatStage.RecordAeadNegativeChecksCompleted,
            ),
            evidence.stagesCompleted,
        )
        assertEquals(ProductionProviderDeterministicKatVector.entries.toSet(), evidence.deterministicVectorsMatched)
        assertEquals(
            ProductionProviderRandomizedAeadBehavioralKatCheck.entries.toSet(),
            evidence.randomizedAeadBehavioralChecksPassed,
        )
        assertTrue(evidence.headerCommitmentVerifiedBeforeRecordAead)
        assertTrue(evidence.recordAeadStageReached)
        assertFalse(evidence.ciphertextTreatedAsDeterministic)
        assertFalse(evidence.productionProviderSelectable)
        assertFalse(evidence.vaultCreationEnabled)
        assertFalse(evidence.vaultPersistenceEnabled)
        assertFalse(evidence.manifestStorageImplemented)
        assertFalse(evidence.secureStorageEnabled)
    }

    @Test
    fun integratedFixtureConstantsAreStableAndNonSecretProjectVectors() {
        assertEquals(
            "536b616c642d5661756c742e546573745f466978747572652d3031",
            SkaldVaultV1ProviderKatFixtures.NORMALIZED_PASSPHRASE_UTF8_HEX,
        )
        assertEquals(128, SkaldVaultV1ProviderKatFixtures.ARGON2ID_ROOT_MATERIAL_HEX.length)
        assertEquals(64, SkaldVaultV1ProviderKatFixtures.HKDF_HEADER_COMMITMENT_KEY_HEX.length)
        assertEquals(64, SkaldVaultV1ProviderKatFixtures.HKDF_RECORD_AEAD_KEY_HEX.length)
        assertEquals(64, SkaldVaultV1ProviderKatFixtures.HMAC_HEADER_COMMITMENT_TAG_HEX.length)
        assertEquals(1018, SkaldVaultV1ProviderKatFixtures.CANONICAL_HEADER_HEX.length)
        assertEquals(990, SkaldVaultV1ProviderKatFixtures.STRICT_AAD_HEX.length)
    }

    @Test
    fun headerCommitmentFailurePreventsRecordAeadStage() {
        val countingAead = CountingRecordAeadAdapter()
        val harness = SkaldVaultV1StillDisabledProviderKatHarness(recordAead = countingAead)
        val request = SkaldVaultV1ProviderKatFixtures.request().withExpectedHeaderCommitmentTag(
            SkaldVaultV1ProviderKatFixtures.HMAC_HEADER_COMMITMENT_TAG_HEX
                .hexToBytes()
                .also { it[it.lastIndex] = (it[it.lastIndex].toInt() xor 0x01).toByte() },
        )

        val rejected = rejected(harness.runProviderLevelKat(request))

        assertEquals(SkaldVaultV1ProviderKatFailureReason.HeaderCommitmentVerificationFailed, rejected.reason)
        assertFalse(rejected.recordAeadStageReached)
        assertEquals(0, countingAead.sealCalls)
        assertEquals(0, countingAead.openCalls)
        assertContains(rejected.stagesCompleted, SkaldVaultV1ProviderKatStage.CanonicalHeaderSerialized)
        assertFalse(rejected.stagesCompleted.contains(SkaldVaultV1ProviderKatStage.HeaderCommitmentVerified))
    }

    @Test
    fun wrongPassphraseDerivedRootPreventsRecordAeadStage() {
        val countingAead = CountingRecordAeadAdapter()
        val harness = SkaldVaultV1StillDisabledProviderKatHarness(recordAead = countingAead)
        val request = SkaldVaultV1ProviderKatFixtures.request().copy(
            passphrase = "Skald-Vault.Test_Fixture-02",
            enforceDeterministicVectorMatches = false,
        )

        val rejected = rejected(harness.runProviderLevelKat(request))

        assertEquals(SkaldVaultV1ProviderKatFailureReason.HeaderCommitmentVerificationFailed, rejected.reason)
        assertFalse(rejected.recordAeadStageReached)
        assertEquals(0, countingAead.sealCalls)
        assertEquals(0, countingAead.openCalls)
        assertContains(rejected.stagesCompleted, SkaldVaultV1ProviderKatStage.Argon2idRootMaterialDerived)
        assertContains(rejected.stagesCompleted, SkaldVaultV1ProviderKatStage.HkdfSubkeysDerived)
    }

    @Test
    fun modifiedHeaderOrUnsupportedSuitePreventsRecordAeadStage() {
        val modifiedHeaderResult = runRejectedWithCounting(
            header = SkaldVaultV1ProviderKatFixtures.request().header.copy(
                integrityCriticalHeaderMetadata = byteArrayOf(0x01),
            ),
        )
        val unsupportedSuiteResult = runRejectedWithCounting(
            header = SkaldVaultV1ProviderKatFixtures.request().header.copy(
                providerSuiteId = "unsupported-provider-suite",
            ),
        )

        assertEquals(
            SkaldVaultV1ProviderKatFailureReason.HeaderCommitmentVerificationFailed,
            modifiedHeaderResult.rejected.reason,
        )
        assertEquals(0, modifiedHeaderResult.countingAead.sealCalls)
        assertEquals(0, modifiedHeaderResult.countingAead.openCalls)
        assertEquals(
            SkaldVaultV1ProviderKatFailureReason.HeaderCommitmentInputRejected,
            unsupportedSuiteResult.rejected.reason,
        )
        assertEquals(0, unsupportedSuiteResult.countingAead.sealCalls)
        assertEquals(0, unsupportedSuiteResult.countingAead.openCalls)
    }

    @Test
    fun deterministicVectorMismatchBlocksBeforeRecordAead() {
        val countingAead = CountingRecordAeadAdapter()
        val harness = SkaldVaultV1StillDisabledProviderKatHarness(recordAead = countingAead)
        val request = SkaldVaultV1ProviderKatFixtures.request().copy(
            expectedVectors = SkaldVaultV1ProviderKatFixtures.request().expectedVectors.copy(
                strictAadHex = "00",
            ),
        )

        val rejected = rejected(harness.runProviderLevelKat(request))

        assertEquals(SkaldVaultV1ProviderKatFailureReason.DeterministicVectorMismatch, rejected.reason)
        assertFalse(rejected.recordAeadStageReached)
        assertEquals(0, countingAead.sealCalls)
        assertEquals(0, countingAead.openCalls)
        assertContains(rejected.stagesCompleted, SkaldVaultV1ProviderKatStage.StrictAadSerialized)
    }

    private fun runRejectedWithCounting(
        header: SkaldVaultV1CanonicalHeader,
    ): RejectedWithCountingAead {
        val countingAead = CountingRecordAeadAdapter()
        val harness = SkaldVaultV1StillDisabledProviderKatHarness(recordAead = countingAead)
        val request = SkaldVaultV1ProviderKatFixtures.request().copy(
            header = header,
            enforceDeterministicVectorMatches = false,
        )
        return RejectedWithCountingAead(
            rejected = rejected(harness.runProviderLevelKat(request)),
            countingAead = countingAead,
        )
    }

    private fun accepted(
        result: SkaldVaultV1ProviderKatHarnessResult<SkaldVaultV1ProviderKatEvidence>,
    ): SkaldVaultV1ProviderKatEvidence =
        when (result) {
            is SkaldVaultV1ProviderKatHarnessResult.Accepted -> result.value
            is SkaldVaultV1ProviderKatHarnessResult.Rejected -> error(result.safeMessage)
        }

    private fun rejected(
        result: SkaldVaultV1ProviderKatHarnessResult<*>,
    ): SkaldVaultV1ProviderKatHarnessResult.Rejected =
        when (result) {
            is SkaldVaultV1ProviderKatHarnessResult.Accepted -> error("Expected KAT rejection.")
            is SkaldVaultV1ProviderKatHarnessResult.Rejected -> result
        }

    private data class RejectedWithCountingAead(
        val rejected: SkaldVaultV1ProviderKatHarnessResult.Rejected,
        val countingAead: CountingRecordAeadAdapter,
    )

    private class CountingRecordAeadAdapter : SkaldVaultV1ProviderKatRecordAeadAdapter {
        var sealCalls: Int = 0
            private set
        var openCalls: Int = 0
            private set

        override fun sealRecord(
            recordAeadKey: ByteArray,
            plaintext: ByteArray,
            aadContext: SkaldVaultV1RecordAadContext,
        ): SkaldVaultV1RecordAeadResult<SkaldVaultV1RecordCiphertext> {
            sealCalls += 1
            return SkaldVaultV1ProviderKatRecordAeadBuildingBlock.sealRecord(
                recordAeadKey = recordAeadKey,
                plaintext = plaintext,
                aadContext = aadContext,
            )
        }

        override fun openRecord(
            recordAeadKey: ByteArray,
            ciphertext: ByteArray,
            aadContext: SkaldVaultV1RecordAadContext,
        ): SkaldVaultV1RecordAeadResult<SkaldVaultV1RecordPlaintext> {
            openCalls += 1
            return SkaldVaultV1ProviderKatRecordAeadBuildingBlock.openRecord(
                recordAeadKey = recordAeadKey,
                ciphertext = ciphertext,
                aadContext = aadContext,
            )
        }
    }

    private fun String.hexToBytes(): ByteArray {
        require(length % 2 == 0)
        return chunked(2).map { it.toInt(16).toByte() }.toByteArray()
    }
}
