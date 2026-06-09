package com.libertasprimordium.skald

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.libertasprimordium.skald.security.ProductionProviderDeterministicKatVector
import com.libertasprimordium.skald.security.ProductionProviderRandomizedAeadBehavioralKatCheck
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderKatEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderKatHarnessResult
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderKatStage
import com.libertasprimordium.skald.security.SkaldVaultV1StillDisabledProviderKatHarness
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VaultCryptoAndroidStillDisabledProviderKatHarnessTest {
    @Test
    fun androidStillDisabledProviderKatHarnessRunsFixedNonSecretProviderKats() {
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

    private fun accepted(
        result: SkaldVaultV1ProviderKatHarnessResult<SkaldVaultV1ProviderKatEvidence>,
    ): SkaldVaultV1ProviderKatEvidence =
        when (result) {
            is SkaldVaultV1ProviderKatHarnessResult.Accepted -> result.value
            is SkaldVaultV1ProviderKatHarnessResult.Rejected -> error(result.safeMessage)
        }
}
