package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationAcceptanceCriterion
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGatePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationConstraint
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationEscalationGate
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenCurrentArtifact
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPlanCapabilities
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPlanEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPlanEvidenceSource
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPlanFutureReviewRequirement
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPlanOutcome
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPlanPhase
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPlanPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPlanRequest
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPlanSafeLabel
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPlanStatus
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPlannedTestOnlyArtifact
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationRollbackCriterion
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityImplementationPlanTest {
    @Test
    fun currentImplementationPlanEvidenceIsModeledAndStillDisabled() {
        val evidence = currentEvidence()

        assertEquals(
            "skald-vault-v1-test-only-provider-identity-implementation-plan-v1",
            evidence.policyId,
        )
        assertEquals(1, evidence.policyVersion)
        assertTrue(evidence.implementationPlanModeled)
        assertTrue(evidence.stillDisabled)
        assertTrue(evidence.planningOnlyCurrentBranch)
        assertTrue(evidence.testOnlyImplementationNextPhaseModeled)
        assertTrue(evidence.productionImplementationFuturePhaseModeled)
        assertTrue(evidence.acceptanceCriteriaModeled)
        assertTrue(evidence.escalationGatesModeled)
        assertTrue(evidence.rollbackCriteriaModeled)
        assertTrue(evidence.docsMayDescribePlan)
        assertTrue(evidence.testsMayAssertBlockedPlan)
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationPlanStatus.PlanModeled in evidence.statuses)
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationPlanStatus.StillDisabled in evidence.statuses)
        assertFalse(evidence.capabilities.implementationPlanAuthorizesImplementation)
        assertFalse(evidence.currentPlanAdmitsImplementation)
    }

    @Test
    fun currentPlanOutcomeIsModeledImplementationBlockedNextBranchRequiredAndMainnetBlocked() {
        val outcomes = currentEvidence().outcomes

        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationPlanOutcome.CurrentPlanModeled in outcomes)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanOutcome.CurrentImplementationNotAuthorized in
                outcomes,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanOutcome.TestOnlyImplementationRequiresNextBranch in
                outcomes,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanOutcome.ProductionImplementationNotAuthorized in
                outcomes,
        )
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationPlanOutcome.MainnetNotAuthorized in outcomes)
    }

    @Test
    fun allPlanPhasesAreRepresentedExactlyOnce() {
        val rows = currentEvidence().planPhaseRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationPlanPhase.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanPhase.entries.toSet(),
            rows.map { it.phase }.toSet(),
        )
        assertTrue(rows.all { it.modeled })
        assertTrue(rows.single {
            it.phase == SkaldVaultV1TestOnlyProviderIdentityImplementationPlanPhase.PlanningOnlyCurrentBranch
        }.currentBranch)
        assertTrue(rows.filterNot {
            it.phase == SkaldVaultV1TestOnlyProviderIdentityImplementationPlanPhase.PlanningOnlyCurrentBranch
        }.all { it.futureOnly })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allPlannedTestOnlyArtifactsAreRepresentedExactlyOnceAndFutureOnly() {
        val rows = currentEvidence().plannedTestOnlyArtifactRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationPlannedTestOnlyArtifact.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlannedTestOnlyArtifact.entries.toSet(),
            rows.map { it.artifact }.toSet(),
        )
        assertTrue(rows.all { it.plannedForFuture })
        assertTrue(rows.none { it.presentNow })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allForbiddenCurrentArtifactsAreRepresentedExactlyOnce() {
        val rows = currentEvidence().forbiddenCurrentArtifactRows

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenCurrentArtifact.entries.size,
            rows.size,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenCurrentArtifact.entries.toSet(),
            rows.map { it.artifact }.toSet(),
        )
        assertTrue(rows.all { it.forbiddenNow })
        assertTrue(rows.none { it.presentNow })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allImplementationConstraintsAreRepresentedExactlyOnce() {
        val rows = currentEvidence().implementationConstraintRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationConstraint.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationConstraint.entries.toSet(),
            rows.map { it.constraint }.toSet(),
        )
        assertTrue(rows.all { it.requiredForFutureImplementation })
        assertTrue(rows.all { it.currentBranchSatisfiesByAbsenceOnly })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allAcceptanceCriteriaAreRepresentedExactlyOnce() {
        val rows = currentEvidence().acceptanceCriterionRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationAcceptanceCriterion.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationAcceptanceCriterion.entries.toSet(),
            rows.map { it.criterion }.toSet(),
        )
        assertTrue(rows.all { it.requiredForFutureImplementation })
        assertTrue(rows.none { it.satisfiedNow })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allEscalationGatesAreRepresentedExactlyOnce() {
        val rows = currentEvidence().escalationGateRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationEscalationGate.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationEscalationGate.entries.toSet(),
            rows.map { it.gate }.toSet(),
        )
        assertTrue(rows.all { it.requiredBeforeEscalation })
        assertTrue(rows.none { it.satisfiedNow })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allRollbackCriteriaAreRepresentedExactlyOnce() {
        val rows = currentEvidence().rollbackCriterionRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationRollbackCriterion.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRollbackCriterion.entries.toSet(),
            rows.map { it.criterion }.toSet(),
        )
        assertTrue(rows.all { it.wouldRequireRollback })
        assertTrue(rows.none { it.triggeredNow })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allEvidenceSourcesAreRepresentedExactlyOnceAndNonAuthorizing() {
        val rows = currentEvidence().evidenceSourceRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationPlanEvidenceSource.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanEvidenceSource.entries.toSet(),
            rows.map { it.evidenceSource }.toSet(),
        )
        assertTrue(rows.all { it.included })
        assertTrue(rows.all { it.nonAuthorizing })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allFutureReviewRequirementsAreRepresentedExactlyOnceAndNonAuthorizing() {
        val rows = currentEvidence().futureReviewRows

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanFutureReviewRequirement.entries.size,
            rows.size,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanFutureReviewRequirement.entries.toSet(),
            rows.map { it.requirement }.toSet(),
        )
        assertTrue(rows.all { it.requiredBeforeImplementation })
        assertTrue(rows.none { it.satisfiedNow })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun providerSelectionRemainsDisabledProviderOnlyWhenComposedWithPriorAdmissionAndRedactionEvidence() {
        val admission =
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGatePolicy.currentAdmissionGateEvidence()
        val redaction =
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardPolicy.currentRedactionGuardEvidence()
        val evidence = currentEvidence()
        val selected = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selected.selectedCandidateId)
        assertTrue(selected.selectedProviderIsDisabled)
        assertIs<DisabledVaultCryptoProvider>(selected.selectedProvider)
        assertFalse(selected.productionProviderSelectable)
        assertFalse(admission.capabilities.admissionGateAuthorizesImplementation)
        assertFalse(redaction.capabilities.redactionGuardAuthorizesImplementation)
        assertFalse(evidence.capabilities.implementationPlanAuthorizesImplementation)
        assertAllPlanBooleansFalse(evidence)
    }

    @Test
    fun productionProviderSelectableRemainsFalse() {
        val evidence = currentEvidence()

        assertFalse(evidence.capabilities.productionProviderSelectable)
        assertFalse(evidence.currentProductionProviderSelectableChanged)
        assertFalse(VaultCryptoProviderSelectionRegistry.select().productionProviderSelectable)
    }

    @Test
    fun allCurrentCapabilityBooleansRemainFalse() {
        assertAllCapabilityBooleansFalse(currentEvidence().capabilities)
    }

    @Test
    fun allCurrentPlanBooleansRemainFalse() {
        assertAllPlanBooleansFalse(currentEvidence())
    }

    @Test
    fun allowedTrueBooleansAreOnlyNonAuthorizingPlanningEvidence() {
        val evidence = currentEvidence()

        assertTrue(evidence.implementationPlanModeled)
        assertTrue(evidence.stillDisabled)
        assertTrue(evidence.planningOnlyCurrentBranch)
        assertTrue(evidence.testOnlyImplementationNextPhaseModeled)
        assertTrue(evidence.productionImplementationFuturePhaseModeled)
        assertTrue(evidence.acceptanceCriteriaModeled)
        assertTrue(evidence.escalationGatesModeled)
        assertTrue(evidence.rollbackCriteriaModeled)
        assertTrue(evidence.docsMayDescribePlan)
        assertTrue(evidence.testsMayAssertBlockedPlan)
        assertTrue(evidence.planPhaseRows.none { it.authorizesImplementation })
        assertTrue(evidence.plannedTestOnlyArtifactRows.none { it.presentNow })
        assertTrue(evidence.forbiddenCurrentArtifactRows.none { it.presentNow })
        assertTrue(evidence.acceptanceCriterionRows.none { it.satisfiedNow })
        assertTrue(evidence.escalationGateRows.none { it.satisfiedNow })
        assertTrue(evidence.rollbackCriterionRows.none { it.triggeredNow })
        assertAllCapabilityBooleansFalse(evidence.capabilities)
        assertAllPlanBooleansFalse(evidence)
    }

    @Test
    fun priorAdmissionAndRedactionEvidenceRemainNonAuthorizingWhenComposed() {
        val admissionOnly = SkaldVaultV1TestOnlyProviderIdentityImplementationPlanPolicy.evaluateImplementationPlan(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanRequest(
                includePriorAdmissionGateEvidence = true,
                includePriorRedactionGuardEvidence = false,
            ),
        )
        val redactionOnly = SkaldVaultV1TestOnlyProviderIdentityImplementationPlanPolicy.evaluateImplementationPlan(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanRequest(
                includePriorAdmissionGateEvidence = false,
                includePriorRedactionGuardEvidence = true,
            ),
        )

        assertPriorEvidenceNonAuthorizing(
            admissionOnly,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanEvidenceSource.AdmissionGateEvidence,
        )
        assertPriorEvidenceNonAuthorizing(
            redactionOnly,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanEvidenceSource.RedactionGuardEvidence,
        )
    }

    @Test
    fun falsePositivePlanAndRuntimeClaimsAddBlockersWithoutChangingCapabilitiesOrPlanBooleans() {
        val evidence = SkaldVaultV1TestOnlyProviderIdentityImplementationPlanPolicy.evaluateImplementationPlan(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanRequest(
                userConsentOverrideRequested = true,
                warningOnlyEvidenceClaimed = true,
                testOnlyEvidenceClaimedAsProductionPromotion = true,
                releaseEvidenceClaimed = true,
                planClaimedAsImplementationAuthorization = true,
                testOnlyImplementationClaimedNow = true,
                productionImplementationClaimed = true,
                providerSelectionClaimed = true,
                productionProviderSelectableClaimed = true,
                vaultPersistenceClaimed = true,
                productionSyncClaimed = true,
                signingBroadcastingClaimed = true,
                publicEndpointClaimed = true,
                mainnetClaimed = true,
            ),
        )

        assertAllCapabilityBooleansFalse(evidence.capabilities)
        assertAllPlanBooleansFalse(evidence)
        listOf(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker.UserConsentCannotOverride,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker.WarningOnlyEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker.TestOnlyEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker.ReleaseEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker.PlanAuthorizationClaimRejected,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker.TestOnlyImplementationClaimRejected,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker.ProductionImplementationClaimRejected,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker.ProviderSelectionClaimRejected,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker.ProductionProviderSelectableClaimRejected,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker.VaultPersistenceClaimRejected,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker.ProductionSyncClaimRejected,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker.SigningBroadcastingClaimRejected,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker.PublicEndpointClaimRejected,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker.MainnetClaimRejected,
        ).forEach { blocker ->
            assertTrue(blocker in evidence.blockers, "missing blocker $blocker")
        }
    }

    @Test
    fun requestStringAndSafeLabelsDoNotExposeSensitivePlanOrDiagnosticReferences() {
        val request = SkaldVaultV1TestOnlyProviderIdentityImplementationPlanRequest(
            planClaimedAsImplementationAuthorization = true,
            testOnlyImplementationClaimedNow = true,
            productionImplementationClaimed = true,
            providerSelectionClaimed = true,
            productionProviderSelectableClaimed = true,
            vaultPersistenceClaimed = true,
            productionSyncClaimed = true,
            signingBroadcastingClaimed = true,
            publicEndpointClaimed = true,
            mainnetClaimed = true,
        ).toString()
        val label = SkaldVaultV1TestOnlyProviderIdentityImplementationPlanSafeLabel(
            "candidate-provider-storage-backend-crypto-path-endpoint-wallet-plan",
        ).toString()

        val forbiddenTerms = listOf(
            "candidate",
            "raw material",
            "identity",
            "provider",
            "storage",
            "backend",
            "crypto",
            "path",
            "file",
            "endpoint",
            "wallet",
            "descriptor",
            "source location",
            "future approval",
            "implementation payload",
            "plan payload",
            "hash",
            "fingerprint",
            "crash report",
            "analytics",
            "support export",
        )
        forbiddenTerms.forEach { term ->
            assertFalse(request.contains(term, ignoreCase = true), "request leaked $term")
            assertFalse(label.contains(term, ignoreCase = true), "label leaked $term")
        }
    }

    @Test
    fun implementationPlanModelsForbiddenImportScanForNewCommonMainFile() {
        val evidence = currentEvidence()
        val sourceGuardEvidence = evidence.evidenceSourceRows.single {
            it.evidenceSource ==
                SkaldVaultV1TestOnlyProviderIdentityImplementationPlanEvidenceSource.SourceGuardCoverageEvidence
        }

        assertTrue(sourceGuardEvidence.included)
        assertTrue(sourceGuardEvidence.nonAuthorizing)
        assertFalse(sourceGuardEvidence.authorizesImplementation)
        assertFalse(evidence.capabilities.canExecuteAead)
        assertFalse(evidence.capabilities.canUseForBdkWalletState)
        assertFalse(evidence.capabilities.canUseForSettingsCodec)
        assertFalse(evidence.capabilities.canUseForMainnet)
        assertAllPlanBooleansFalse(evidence)
    }

    private fun currentEvidence() =
        SkaldVaultV1TestOnlyProviderIdentityImplementationPlanPolicy.currentImplementationPlanEvidence()

    private fun assertPriorEvidenceNonAuthorizing(
        evidence: SkaldVaultV1TestOnlyProviderIdentityImplementationPlanEvidence,
        source: SkaldVaultV1TestOnlyProviderIdentityImplementationPlanEvidenceSource,
    ) {
        val row = evidence.evidenceSourceRows.single { it.evidenceSource == source }

        assertTrue(row.included)
        assertTrue(row.nonAuthorizing)
        assertFalse(row.authorizesImplementation)
        assertAllCapabilityBooleansFalse(evidence.capabilities)
        assertAllPlanBooleansFalse(evidence)
    }

    private fun assertAllCapabilityBooleansFalse(
        capabilities: SkaldVaultV1TestOnlyProviderIdentityImplementationPlanCapabilities,
    ) {
        assertFalse(capabilities.implementationPlanAuthorizesImplementation)
        assertFalse(capabilities.admissionGateAuthorizesImplementation)
        assertFalse(capabilities.redactionGuardAuthorizesImplementation)
        assertFalse(capabilities.sourceGuardCoverageAuthorizesImplementation)
        assertFalse(capabilities.testOnlyIdentityImplementationPresent)
        assertFalse(capabilities.productionIdentityImplementationPresent)
        assertFalse(capabilities.canImplementProviderNow)
        assertFalse(capabilities.canInstantiateProviderNow)
        assertFalse(capabilities.canRegisterProviderNow)
        assertFalse(capabilities.canUseAsProviderSelectionId)
        assertFalse(capabilities.canUseAsRegistryKey)
        assertFalse(capabilities.canUseAsFactoryInput)
        assertFalse(capabilities.canUseAsDispatcherInput)
        assertFalse(capabilities.canUseAsExecutorTarget)
        assertFalse(capabilities.canUseForProviderKatExecutor)
        assertFalse(capabilities.canExecuteProviderOperations)
        assertFalse(capabilities.canExecuteRandomness)
        assertFalse(capabilities.canExecuteKdf)
        assertFalse(capabilities.canExecuteHkdf)
        assertFalse(capabilities.canExecuteHmac)
        assertFalse(capabilities.canExecuteAead)
        assertFalse(capabilities.canGenerateKeys)
        assertFalse(capabilities.canStoreKeysets)
        assertFalse(capabilities.canUseForVaultCreation)
        assertFalse(capabilities.canUseForVaultUnlock)
        assertFalse(capabilities.canUseForVaultSession)
        assertFalse(capabilities.canUseForVaultPersistence)
        assertFalse(capabilities.canUseForSecureStorage)
        assertFalse(capabilities.canUseForSecureMetadataStorage)
        assertFalse(capabilities.canUseForProductionSync)
        assertFalse(capabilities.canUseForBackendClient)
        assertFalse(capabilities.canUseForBdkWalletState)
        assertFalse(capabilities.canUseForSettingsCodec)
        assertFalse(capabilities.canUseForUiSurface)
        assertFalse(capabilities.canUseForSigning)
        assertFalse(capabilities.canUseForBroadcasting)
        assertFalse(capabilities.canUseForTorTransport)
        assertFalse(capabilities.canUseForNostrParsing)
        assertFalse(capabilities.canUseForPublicEndpointDefault)
        assertFalse(capabilities.canUseForMainnet)
        assertFalse(capabilities.productionProviderSelectable)
    }

    private fun assertAllPlanBooleansFalse(
        evidence: SkaldVaultV1TestOnlyProviderIdentityImplementationPlanEvidence,
    ) {
        assertFalse(evidence.currentPlanAdmitsImplementation)
        assertFalse(evidence.currentTestOnlyImplementationStarted)
        assertFalse(evidence.currentProductionImplementationStarted)
        assertFalse(evidence.currentProviderSelectionChanged)
        assertFalse(evidence.currentProductionProviderSelectableChanged)
        assertFalse(evidence.currentVaultPersistenceChanged)
        assertFalse(evidence.currentProductionSyncChanged)
        assertFalse(evidence.currentSigningBroadcastingChanged)
        assertFalse(evidence.currentPublicEndpointChanged)
        assertFalse(evidence.currentMainnetChanged)
    }
}
