package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1Argon2idCalibrationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1HeaderCommitment
import com.libertasprimordium.skald.security.SkaldVaultV1StillDisabledProviderFacade
import com.libertasprimordium.skald.security.SkaldVaultV1StillDisabledProviderFacadeGate
import com.libertasprimordium.skald.security.SkaldVaultV1StillDisabledProviderFacadeOperation
import com.libertasprimordium.skald.security.SkaldVaultV1StillDisabledProviderFacadeReason
import com.libertasprimordium.skald.security.SkaldVaultV1StillDisabledProviderFacadeStatus
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SkaldVaultV1StillDisabledProviderFacadeTest {
    @Test
    fun facadeReportsStillDisabledMetadataAndSuitePolicies() {
        val metadata = SkaldVaultV1StillDisabledProviderFacade.metadata()

        assertEquals(
            SkaldVaultV1HeaderCommitment.PROVIDER_SUITE_ID,
            metadata.suiteId,
        )
        assertEquals(
            SkaldVaultV1StillDisabledProviderFacadeStatus.StillDisabled,
            metadata.status,
        )
        assertFalse(metadata.status.selectable)
        assertFalse(metadata.status.operationsEnabled)
        assertEquals("Bouncy Castle Argon2id", metadata.kdf)
        assertEquals("Tink XChaCha20-Poly1305", metadata.recordAead)
        assertEquals("OS SecureRandom", metadata.runtimeRandomness)
        assertEquals(SkaldVaultV1HeaderCommitment.PASSPHRASE_ENCODING_POLICY_ID, metadata.passphrasePolicyId)
        assertEquals(SkaldVaultV1HeaderCommitment.KEY_EXPANSION_POLICY_ID, metadata.keyExpansionPolicyId)
        assertEquals(SkaldVaultV1HeaderCommitment.KEY_SEPARATION_POLICY_ID, metadata.keySeparationPolicyId)
        assertEquals(
            SkaldVaultV1HeaderCommitment.HEADER_COMMITMENT_PRIMITIVE_POLICY_ID,
            metadata.headerCommitmentPrimitivePolicyId,
        )
        assertEquals(SkaldVaultV1HeaderCommitment.HEADER_COMMITMENT_POLICY_ID, metadata.headerCommitmentPolicyId)
        assertEquals(SkaldVaultV1HeaderCommitment.AAD_POLICY_ID, metadata.strictAadPolicyId)
        assertEquals(SkaldVaultV1HeaderCommitment.RECORD_FORMAT_POLICY_ID, metadata.recordFormatPolicyId)
        assertEquals("skald-vault-v1-provider-level-kat-strategy-v1", metadata.providerLevelKatPolicyId)
        assertEquals(
            "skald-vault-v1-randomized-aead-behavioral-kat-v1",
            metadata.randomizedAeadBehavioralKatPolicyId,
        )
        assertEquals(
            "skald-vault-v1-integrated-verification-order-kat-v1",
            metadata.verificationOrderKatPolicyId,
        )
        assertEquals("skald-vault-v1-stale-record-manifest-policy-v1", metadata.staleRecordManifestPolicyId)
        assertEquals(SkaldVaultV1Argon2idCalibrationPolicy.POLICY_ID, metadata.calibrationPolicyId)
    }

    @Test
    fun facadeReportsRemainingGatesAndDisabledEvidence() {
        val metadata = SkaldVaultV1StillDisabledProviderFacade.metadata()
        val evidence = metadata.evidence

        assertContains(metadata.disabledReasons, SkaldVaultV1StillDisabledProviderFacadeReason.ProviderSelectionDisabled)
        assertContains(metadata.disabledReasons, SkaldVaultV1StillDisabledProviderFacadeReason.VaultCreationDisabled)
        assertContains(metadata.disabledReasons, SkaldVaultV1StillDisabledProviderFacadeReason.VaultUnlockDisabled)
        assertContains(metadata.disabledReasons, SkaldVaultV1StillDisabledProviderFacadeReason.VaultPersistenceDisabled)
        assertContains(metadata.disabledReasons, SkaldVaultV1StillDisabledProviderFacadeReason.ManifestStorageMissing)
        assertContains(metadata.disabledReasons, SkaldVaultV1StillDisabledProviderFacadeReason.SecureSecretStorageDisabled)
        assertContains(metadata.disabledReasons, SkaldVaultV1StillDisabledProviderFacadeReason.SecureMetadataStorageDisabled)
        assertContains(
            metadata.disabledReasons,
            SkaldVaultV1StillDisabledProviderFacadeReason.FinalCalibrationApprovalMissing,
        )
        assertContains(metadata.disabledReasons, SkaldVaultV1StillDisabledProviderFacadeReason.ReleaseApprovalMissing)

        assertContains(metadata.remainingGates, SkaldVaultV1StillDisabledProviderFacadeGate.ReleaseApproval)
        assertContains(
            metadata.remainingGates,
            SkaldVaultV1StillDisabledProviderFacadeGate.ManifestBackedStaleRecordPolicy,
        )
        assertContains(metadata.remainingGates, SkaldVaultV1StillDisabledProviderFacadeGate.SecureSecretStorageReview)
        assertContains(metadata.remainingGates, SkaldVaultV1StillDisabledProviderFacadeGate.SecureMetadataStorageReview)

        assertTrue(evidence.passphrasePolicyImplementedAndTested)
        assertTrue(evidence.argon2idRootDerivationImplementedAndTested)
        assertTrue(evidence.canonicalHeaderSerializerImplementedAndTested)
        assertTrue(evidence.hkdfSha256ImplementedAndTested)
        assertTrue(evidence.hmacSha256HeaderCommitmentImplementedAndTested)
        assertTrue(evidence.strictAadSerializationImplementedAndTested)
        assertTrue(evidence.tinkRecordAeadBuildingBlockImplementedAndTested)
        assertTrue(evidence.providerKatHarnessExistsAndExecutes)
        assertTrue(evidence.argon2idCalibrationPolicyEvidenceExists)
        assertFalse(evidence.productionProviderSelectable)
        assertFalse(evidence.vaultCreationEnabled)
        assertFalse(evidence.vaultUnlockEnabled)
        assertFalse(evidence.vaultPersistenceEnabled)
        assertFalse(evidence.manifestStorageImplemented)
        assertFalse(evidence.secureStorageEnabled)
        assertFalse(evidence.releaseApproved)
    }

    @Test
    fun disabledOperationResultsAreTypedAndNeverEnablePersistence() {
        val disabledResults = listOf(
            SkaldVaultV1StillDisabledProviderFacade.vaultCreationDisabled()
                to SkaldVaultV1StillDisabledProviderFacadeOperation.ReportVaultCreationDisabled,
            SkaldVaultV1StillDisabledProviderFacade.vaultUnlockDisabled()
                to SkaldVaultV1StillDisabledProviderFacadeOperation.ReportVaultUnlockDisabled,
            SkaldVaultV1StillDisabledProviderFacade.vaultPersistenceDisabled()
                to SkaldVaultV1StillDisabledProviderFacadeOperation.ReportVaultPersistenceDisabled,
            SkaldVaultV1StillDisabledProviderFacade.storageDisabled()
                to SkaldVaultV1StillDisabledProviderFacadeOperation.ReportStorageDisabled,
            SkaldVaultV1StillDisabledProviderFacade.providerSelectionDisabled()
                to SkaldVaultV1StillDisabledProviderFacadeOperation.ReportProviderSelectionDisabled,
        )

        disabledResults.forEach { (result, operation) ->
            assertEquals(operation, result.operation)
            assertFalse(result.productionProviderSelectable)
            assertFalse(result.vaultPersistenceEnabled)
            assertTrue(result.safeMessage.contains("still disabled"))
        }
    }

    @Test
    fun providerSelectionRegistryDoesNotExposeOrSelectFacade() {
        val result = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, result.selectedCandidateId)
        assertTrue(result.selectedProvider is DisabledVaultCryptoProvider)
        assertTrue(result.selectedProviderIsDisabled)
        assertFalse(result.productionProviderSelectable)
        assertTrue(result.candidates.none { it.id.name.contains("Facade") })
        assertTrue(VaultCryptoProviderCandidateId.entries.none { it.name.contains("Facade") })
    }
}
