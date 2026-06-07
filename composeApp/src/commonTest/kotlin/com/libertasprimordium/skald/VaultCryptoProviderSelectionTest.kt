package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.EncryptedVaultPlatform
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderImplementationState
import com.libertasprimordium.skald.security.VaultCryptoProviderProductionApprovalGate
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionBlocker
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionDecision
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRequest
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionUse
import com.libertasprimordium.skald.security.AndroidVaultCompatibilityAssessmentRequest
import com.libertasprimordium.skald.security.AndroidVaultCompatibilityEvidence
import com.libertasprimordium.skald.security.commonAndroidVaultCompatibilityPolicy
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class VaultCryptoProviderSelectionTest {
    @Test
    fun currentRegistrySelectsDisabledProviderOnly() {
        val result = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, result.selectedCandidateId)
        assertTrue(result.selectedProviderIsDisabled)
        assertTrue(result.selectedProvider is DisabledVaultCryptoProvider)
        assertEquals(VaultCryptoProviderSelectionDecision.DisabledProviderSelected, result.decision)
        assertFalse(result.productionProviderSelectable)
        assertTrue(result.candidates.none { it.productionSelectable })
        assertContains(result.blockers, VaultCryptoProviderSelectionBlocker.ProviderDisabledByPolicy)
        assertContains(result.blockers, VaultCryptoProviderSelectionBlocker.ProductionProviderImplementationMissing)
        assertFalse(result.selectedProvider.statusReport.canDeriveKeys)
        assertFalse(result.selectedProvider.statusReport.canEncryptRecords)
        assertFalse(result.selectedProvider.statusReport.productionPersistenceEnabled)
        assertFalse(result.selectedProvider.statusReport.mainnetEnabled)
    }

    @Test
    fun tinkBouncyCandidateCannotBeSelectedAsProductionProvider() {
        val result = VaultCryptoProviderSelectionRegistry.select(
            VaultCryptoProviderSelectionRequest(
                requestedCandidate = VaultCryptoProviderCandidateId.TinkBouncyCastleSplit,
                use = VaultCryptoProviderSelectionUse.ProductionPersistence,
            ),
        )
        val candidate = result.requestedCandidate

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, result.selectedCandidateId)
        assertTrue(result.selectedProvider is DisabledVaultCryptoProvider)
        assertEquals(VaultCryptoProviderCandidateId.TinkBouncyCastleSplit, candidate.id)
        assertEquals(VaultCryptoProviderSelectionDecision.Blocked, candidate.decision)
        assertEquals(VaultCryptoProviderImplementationState.ExecutableUnavailable, candidate.implementationState)
        assertFalse(candidate.productionSelectable)
        assertContains(candidate.blockers, VaultCryptoProviderSelectionBlocker.ProductionProviderImplementationMissing)
        assertContains(candidate.blockers, VaultCryptoProviderSelectionBlocker.ProductionProviderLevelKatsMissing)
        assertContains(candidate.blockers, VaultCryptoProviderSelectionBlocker.KdfParameterPolicyNotFinal)
        assertContains(candidate.blockers, VaultCryptoProviderSelectionBlocker.TinkKeysetOrRawKeyHandlingUnapproved)
        assertContains(candidate.blockers, VaultCryptoProviderSelectionBlocker.SecureSecretStorageDisabled)
        assertContains(candidate.blockers, VaultCryptoProviderSelectionBlocker.SecureMetadataStorageDisabled)
        assertContains(candidate.blockers, VaultCryptoProviderSelectionBlocker.VaultContainerStorageReviewMissing)
        assertContains(
            candidate.productionApprovalGates.map { it.gate },
            VaultCryptoProviderProductionApprovalGate.ProductionProviderImplementationExists,
        )
        assertFalse(
            candidate.productionApprovalGates
                .single { it.gate == VaultCryptoProviderProductionApprovalGate.ProductionProviderImplementationExists }
                .satisfied,
        )
    }

    @Test
    fun dependencyAndTestProviderKatEvidenceAreInsufficientForSelection() {
        val result = VaultCryptoProviderSelectionRegistry.select(
            VaultCryptoProviderSelectionRequest(
                requestedCandidate = VaultCryptoProviderCandidateId.TinkBouncyCastleSplit,
            ),
        )
        val candidate = result.requestedCandidate

        assertTrue(candidate.evidence.katEvidence.dependencyLevelKatsPassed)
        assertTrue(candidate.evidence.katEvidence.testProviderKatsPassed)
        assertFalse(candidate.evidence.katEvidence.productionProviderKatsPassed)
        assertFalse(candidate.evidence.katEvidence.dependencyLevelKatsSatisfyProductionSelection)
        assertFalse(candidate.evidence.katEvidence.testProviderKatsSatisfyProductionSelection)
        assertContains(
            candidate.blockers,
            VaultCryptoProviderSelectionBlocker.DependencyLevelKatsInsufficientForSelection,
        )
        assertContains(
            candidate.blockers,
            VaultCryptoProviderSelectionBlocker.TestProviderKatsInsufficientForSelection,
        )
    }

    @Test
    fun androidCompatibilityPlanningAndStorageGatesDoNotSelectProductionProvider() {
        val androidCompatibility = commonAndroidVaultCompatibilityPolicy().assess(
            AndroidVaultCompatibilityAssessmentRequest(
                evidence = AndroidVaultCompatibilityEvidence.supportedPlanningEvidence(),
            ),
        )
        val result = VaultCryptoProviderSelectionRegistry.select(
            request = VaultCryptoProviderSelectionRequest(
                requestedCandidate = VaultCryptoProviderCandidateId.TinkBouncyCastleSplit,
                platform = EncryptedVaultPlatform.Android,
                use = VaultCryptoProviderSelectionUse.ProductionPersistence,
                requireUniversalAndroidParameterPolicy = true,
            ),
            androidCompatibilityAssessment = androidCompatibility,
        )
        val candidate = result.requestedCandidate

        assertTrue(candidate.evidence.parameterPolicy.androidBaselineCoverageSatisfied)
        assertTrue(candidate.evidence.platformCoverage.androidCompatibilityPlanningSatisfied)
        assertFalse(candidate.evidence.platformCoverage.lowEndModelTestingRequired)
        assertFalse(candidate.evidence.platformCoverage.midRangeModelTestingRequired)
        assertFalse(candidate.evidence.parameterPolicy.finalParametersApproved)
        assertFalse(VaultCryptoProviderSelectionBlocker.AndroidCompatibilityRuntimeChecksMissing in candidate.blockers)
        assertContains(candidate.blockers, VaultCryptoProviderSelectionBlocker.SecureSecretStorageDisabled)
        assertContains(candidate.blockers, VaultCryptoProviderSelectionBlocker.SecureMetadataStorageDisabled)
        assertFalse(candidate.productionSelectable)
    }

    @Test
    fun unknownAndroidCompatibilityEvidenceBlocksAndroidProviderPlanning() {
        val result = VaultCryptoProviderSelectionRegistry.select(
            request = VaultCryptoProviderSelectionRequest(
                requestedCandidate = VaultCryptoProviderCandidateId.TinkBouncyCastleSplit,
                platform = EncryptedVaultPlatform.Android,
                requireUniversalAndroidParameterPolicy = true,
            ),
        )
        val candidate = result.requestedCandidate

        assertFalse(candidate.evidence.platformCoverage.androidCompatibilityPlanningSatisfied)
        assertContains(
            candidate.blockers,
            VaultCryptoProviderSelectionBlocker.AndroidCompatibilityRuntimeChecksMissing,
        )
        assertFalse(candidate.productionSelectable)
    }

    @Test
    fun mainnetRequestIsBlocked() {
        val result = VaultCryptoProviderSelectionRegistry.select(
            VaultCryptoProviderSelectionRequest(
                requestedCandidate = VaultCryptoProviderCandidateId.TinkBouncyCastleSplit,
                use = VaultCryptoProviderSelectionUse.MainnetProductionPersistence,
                requestMainnet = true,
            ),
        )

        assertContains(result.blockers, VaultCryptoProviderSelectionBlocker.MainnetDisabled)
        assertContains(result.requestedCandidate.blockers, VaultCryptoProviderSelectionBlocker.MainnetDisabled)
        assertFalse(result.productionProviderSelectable)
    }

    @Test
    fun libsodiumCandidatesRemainRejectedOrDeferredAndNonSelectable() {
        val candidates = VaultCryptoProviderSelectionRegistry.candidates().associateBy { it.id }
        val lazysodium = candidates.getValue(VaultCryptoProviderCandidateId.LazysodiumJavaAndroid)
        val ionSpin = candidates.getValue(VaultCryptoProviderCandidateId.IonSpinKmpLibsodium)

        assertEquals(VaultCryptoProviderSelectionDecision.Rejected, lazysodium.decision)
        assertContains(lazysodium.blockers, VaultCryptoProviderSelectionBlocker.CandidateRejectedForCurrentVault)
        assertContains(lazysodium.blockers, VaultCryptoProviderSelectionBlocker.NativePackagingUnverified)
        assertFalse(lazysodium.productionSelectable)

        assertEquals(VaultCryptoProviderSelectionDecision.Deferred, ionSpin.decision)
        assertContains(ionSpin.blockers, VaultCryptoProviderSelectionBlocker.CandidateDeferred)
        assertContains(ionSpin.blockers, VaultCryptoProviderSelectionBlocker.NativePackagingUnverified)
        assertFalse(ionSpin.productionSelectable)
    }
}
