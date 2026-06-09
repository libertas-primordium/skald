package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.Argon2idVersion
import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.EncryptedVaultAeadAlgorithm
import com.libertasprimordium.skald.security.ProductionProviderAadBindingField
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceBlocker
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidence
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidenceState
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceGate
import com.libertasprimordium.skald.security.ProductionProviderPrimitiveRole
import com.libertasprimordium.skald.security.ProductionProviderSuiteModel
import com.libertasprimordium.skald.security.ProductionProviderTamperCoverage
import com.libertasprimordium.skald.security.ProductionProviderWeakDeviceFailureMode
import com.libertasprimordium.skald.security.RuntimeRandomnessSourceKind
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderImplementationState
import com.libertasprimordium.skald.security.VaultCryptoProviderProductionApprovalGate
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionBlocker
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRequest
import com.libertasprimordium.skald.security.commonProductionProviderAcceptanceContract
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class ProductionProviderAcceptanceContractTest {
    private val contract = commonProductionProviderAcceptanceContract()

    @Test
    fun v1SuiteIdentityIsPinnedAndNotPluggable() {
        val suite = contract.suite

        assertEquals(
            "skald-vault-v1-bouncycastle-argon2id-tink-xchacha20poly1305-os-securerandom",
            suite.suiteId,
        )
        assertEquals(ProductionProviderSuiteModel.SinglePinnedSuite, suite.model)
        assertFalse(suite.model.providerAgilityAllowedInV1)
        assertEquals(ProductionProviderPrimitiveRole.Kdf, suite.kdf.role)
        assertEquals("Bouncy Castle", suite.kdf.implementation)
        assertEquals("Argon2id", suite.kdf.algorithm)
        assertEquals("Argon2id version 19", suite.kdf.versionOrTemplate)
        assertEquals("org.bouncycastle:bcprov-jdk18on:1.84", suite.kdf.artifact)
        assertEquals(ProductionProviderPrimitiveRole.Aead, suite.aead.role)
        assertEquals("Tink", suite.aead.implementation)
        assertEquals("XChaCha20-Poly1305", suite.aead.algorithm)
        assertEquals("com.google.crypto.tink:tink/tink-android:1.21.0", suite.aead.artifact)
        assertEquals(ProductionProviderPrimitiveRole.RuntimeRandomness, suite.runtimeRandomness.role)
        assertEquals("OS SecureRandom", suite.runtimeRandomness.implementation)
        assertContains(suite.pinnedArtifacts, "org.bouncycastle:bcprov-jdk18on:1.84")
        assertContains(suite.pinnedArtifacts, "com.google.crypto.tink:tink:1.21.0")
        assertContains(suite.pinnedArtifacts, "com.google.crypto.tink:tink-android:1.21.0")
    }

    @Test
    fun currentDesignOnlyEvidenceIsIncompleteAndNonSelectable() {
        val assessment = contract.assess()

        assertFalse(assessment.allRequiredGatesSatisfied)
        assertFalse(assessment.productionProviderSelectable)
        assertFalse(assessment.productionPersistenceAllowed)
        assertContains(assessment.blockers, ProductionProviderAcceptanceBlocker.UnknownGateEvidence)
        assertContains(
            assessment.blockers,
            ProductionProviderAcceptanceBlocker.ProductionProviderSelectionStillDisabled,
        )
        assertContains(
            assessment.blockers,
            ProductionProviderAcceptanceBlocker.ProductionPersistenceStillDisabled,
        )
        assertNotNull(assessment.userFacingWarning)
    }

    @Test
    fun failedOrPartialEvidenceBlocksContractSatisfaction() {
        val failedEvidence = ProductionProviderAcceptanceEvidence(
            gateStates = ProductionProviderAcceptanceGate.entries.associateWith {
                ProductionProviderAcceptanceEvidenceState.Satisfied
            } + mapOf(
                ProductionProviderAcceptanceGate.AeadKatsPassed to
                    ProductionProviderAcceptanceEvidenceState.Failed,
            ),
        )
        val partialEvidence = ProductionProviderAcceptanceEvidence(
            gateStates = mapOf(
                ProductionProviderAcceptanceGate.ExactProviderSuitePinned to
                    ProductionProviderAcceptanceEvidenceState.Satisfied,
                ProductionProviderAcceptanceGate.Argon2idPolicyApproved to
                    ProductionProviderAcceptanceEvidenceState.Missing,
            ),
        )

        val failed = contract.assess(failedEvidence)
        val partial = contract.assess(partialEvidence)

        assertFalse(failed.allRequiredGatesSatisfied)
        assertContains(failed.blockers, ProductionProviderAcceptanceBlocker.FailedGateEvidence)
        assertFalse(failed.productionProviderSelectable)
        assertFalse(failed.productionPersistenceAllowed)

        assertFalse(partial.allRequiredGatesSatisfied)
        assertContains(partial.blockers, ProductionProviderAcceptanceBlocker.MissingGateEvidence)
        assertContains(partial.blockers, ProductionProviderAcceptanceBlocker.UnknownGateEvidence)
        assertFalse(partial.productionProviderSelectable)
        assertFalse(partial.productionPersistenceAllowed)
    }

    @Test
    fun allGateEvidenceStillDoesNotSelectProviderInThisBranch() {
        val assessment = contract.assess(ProductionProviderAcceptanceEvidence.allSatisfiedForReviewOnly())
        val result = VaultCryptoProviderSelectionRegistry.select(
            request = VaultCryptoProviderSelectionRequest(
                requestedCandidate = VaultCryptoProviderCandidateId.TinkBouncyCastleSplit,
            ),
            productionProviderAcceptanceAssessment = assessment,
        )
        val candidate = result.requestedCandidate

        assertTrue(assessment.allRequiredGatesSatisfied)
        assertFalse(assessment.productionProviderSelectable)
        assertFalse(assessment.productionPersistenceAllowed)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, result.selectedCandidateId)
        assertTrue(result.selectedProvider is DisabledVaultCryptoProvider)
        assertFalse(result.productionProviderSelectable)
        assertFalse(candidate.productionSelectable)
        assertFalse(
            VaultCryptoProviderSelectionBlocker.ProductionProviderAcceptanceContractIncomplete in
                candidate.blockers,
        )
        assertContains(candidate.blockers, VaultCryptoProviderSelectionBlocker.ProductionProviderImplementationMissing)
        assertContains(candidate.blockers, VaultCryptoProviderSelectionBlocker.ProductionProviderLevelKatsMissing)
        assertContains(candidate.blockers, VaultCryptoProviderSelectionBlocker.SecureSecretStorageDisabled)
        assertTrue(candidate.evidence.productionAcceptance.allRequiredGatesSatisfied)
        assertFalse(candidate.evidence.productionAcceptance.productionProviderSelectableByContract)
        assertFalse(candidate.evidence.productionAcceptance.productionPersistenceAllowedByContract)
        assertTrue(
            candidate.productionApprovalGates
                .single {
                    it.gate == VaultCryptoProviderProductionApprovalGate
                        .ProductionProviderAcceptanceContractSatisfied
                }
                .satisfied,
        )
    }

    @Test
    fun incompleteAcceptanceEvidenceBlocksProviderSelectionAndPersistence() {
        val result = VaultCryptoProviderSelectionRegistry.select(
            VaultCryptoProviderSelectionRequest(
                requestedCandidate = VaultCryptoProviderCandidateId.TinkBouncyCastleSplit,
            ),
        )
        val candidate = result.requestedCandidate

        assertContains(
            candidate.blockers,
            VaultCryptoProviderSelectionBlocker.ProductionProviderAcceptanceContractIncomplete,
        )
        assertFalse(candidate.evidence.productionAcceptance.allRequiredGatesSatisfied)
        assertFalse(candidate.evidence.productionAcceptance.productionProviderSelectableByContract)
        assertFalse(candidate.evidence.productionAcceptance.productionPersistenceAllowedByContract)
        assertFalse(
            candidate.productionApprovalGates
                .single {
                    it.gate == VaultCryptoProviderProductionApprovalGate
                        .ProductionProviderAcceptanceContractSatisfied
                }
                .satisfied,
        )
        assertFalse(result.productionProviderSelectable)
    }

    @Test
    fun debugOrTestProviderCannotBecomeReleaseSelectable() {
        val assessment = contract.assess(
            evidence = ProductionProviderAcceptanceEvidence.allSatisfiedForReviewOnly(),
            providerImplementationState = VaultCryptoProviderImplementationState.TestOnly,
            releaseReadiness = true,
        )

        assertTrue(assessment.allRequiredGatesSatisfied)
        assertContains(
            assessment.blockers,
            ProductionProviderAcceptanceBlocker.DebugOrTestProviderNotReleaseSelectable,
        )
        assertFalse(assessment.productionProviderSelectable)
        assertFalse(assessment.productionPersistenceAllowed)
    }

    @Test
    fun argon2idPolicyCapturesFloorAndFailClosedDowngradeRules() {
        val policy = contract.argon2idPolicy

        assertEquals(Argon2idVersion.Version19, policy.version)
        assertEquals(64, policy.minimumMemoryMiB)
        assertEquals(3, policy.passes)
        assertEquals(1, policy.lanes)
        assertEquals(1_000, policy.preferredUnlockMillis)
        assertEquals(2_000, policy.acceptableUnlockMillis)
        assertFalse(policy.twoSecondsIsFailureCondition)
        assertFalse(policy.weakenToForceSubOneSecondAllowed)
        assertTrue(policy.existingVaultParametersAuthoritative)
        assertFalse(policy.silentParameterDowngradeAllowed)
        assertEquals(
            ProductionProviderWeakDeviceFailureMode.FailClosedWithUserMessage,
            policy.weakerDeviceFailureMode,
        )
    }

    @Test
    fun nonKeyCommittingAeadRequiresVaultLevelCommitmentAndStrictAad() {
        val policy = contract.aeadPolicy

        assertEquals(EncryptedVaultAeadAlgorithm.XChaCha20Poly1305, policy.primitive)
        assertTrue(policy.nonKeyCommitting)
        assertFalse(policy.successfulDecryptAloneProvesCorrectVaultKey)
        assertTrue(policy.vaultLevelKeyCommitmentRequiredBeforeRecordDecrypt)
        assertTrue(policy.headerAuthenticationRequiredBeforeRecordDecrypt)
        assertEquals(ProductionProviderAadBindingField.entries.toSet(), policy.strictAadBindingFields)
        assertEquals(ProductionProviderTamperCoverage.entries.toSet(), policy.requiredTamperCoverage)
    }

    @Test
    fun runtimeRandomnessAndAndroidWrappingPoliciesStaySeparate() {
        val randomness = contract.runtimeRandomnessPolicy
        val androidWrapping = contract.androidWrappingPolicy

        assertEquals(RuntimeRandomnessSourceKind.OsCryptographicRandomness, randomness.sourceKind)
        assertEquals("OS SecureRandom", randomness.exactProviderPathLabel)
        assertTrue(randomness.unknownProviderStateBlocksVaultCreation)
        assertTrue(randomness.forbiddenRandomApisRejected)
        assertFalse(randomness.tinySampleIsEntropyQualityProof)

        assertTrue(androidWrapping.optionalFutureConvenienceLayerOnly)
        assertFalse(androidWrapping.hardwareOrBiometricWrappingRequired)
        assertTrue(androidWrapping.passphrasePrimaryAuthority)
        assertTrue(androidWrapping.passwordOnlyModeFirstClass)
        assertFalse(androidWrapping.biometricUnlockReplacesPassphrase)
        assertTrue(androidWrapping.roughlyWeeklyPassphrasePromptAfterBiometricUnlockRequired)
    }
}
