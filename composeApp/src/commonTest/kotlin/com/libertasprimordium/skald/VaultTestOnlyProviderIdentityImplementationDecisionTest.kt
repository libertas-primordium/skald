package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityForbiddenImplementationEffect
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityForbiddenPromotionPath
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionCapabilities
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionOutcome
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionRequest
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionSafeLabel
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionStatus
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationEvidenceSource
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationRequiredEvidenceClass
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDecisionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityIsolationGuardPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespacePolicy
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityImplementationDecisionTest {
    @Test
    fun currentImplementationDecisionEvidenceIsModeledAndStillDisabled() {
        val evidence =
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionPolicy.currentImplementationDecisionEvidence()

        assertEquals(
            "skald-vault-v1-test-only-provider-identity-implementation-decision-v1",
            evidence.policyId,
        )
        assertEquals(1, evidence.policyVersion)
        assertTrue(evidence.implementationDecisionModeled)
        assertTrue(evidence.stillDisabled)
        assertTrue(evidence.docsMayDescribeFutureReview)
        assertTrue(evidence.testsMayAssertBlockedDecision)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionStatus.ImplementationDecisionModeled in
                evidence.statuses,
        )
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionStatus.StillDisabled in evidence.statuses)
        assertFalse(evidence.capabilities.implementationDecisionAuthorizesImplementation)
    }

    @Test
    fun currentDecisionOutcomesRemainBlockedAndNonAuthorizing() {
        val evidence =
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionPolicy.currentImplementationDecisionEvidence()

        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionOutcome.CurrentDecisionBlocked in
                evidence.outcomes,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionOutcome.ImplementationNotAuthorized in
                evidence.outcomes,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionOutcome.ProductionPromotionNotAuthorized in
                evidence.outcomes,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionOutcome.MainnetNotAuthorized in
                evidence.outcomes,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionOutcome.FutureBranchReviewRequired in
                evidence.outcomes,
        )
    }

    @Test
    fun noCurrentTestOnlyOrProductionProviderIdentityImplementationIsPresent() {
        val evidence =
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionPolicy.currentImplementationDecisionEvidence()

        assertFalse(evidence.capabilities.testOnlyIdentityImplementationPresent)
        assertFalse(evidence.capabilities.productionIdentityImplementationPresent)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.NoTestOnlyProviderIdentityImplementation in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.NoProductionProviderIdentityImplementation in
                evidence.blockers,
        )
    }

    @Test
    fun providerSelectionRemainsDisabledProviderOnlyWhenPriorEvidenceIsComposed() {
        val decision = SkaldVaultV1TestOnlyProviderIdentityDecisionPolicy.currentIdentityDecisionEvidence()
        val isolation = SkaldVaultV1TestOnlyProviderIdentityIsolationGuardPolicy.currentIsolationEvidence()
        val namespace = SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespacePolicy.currentNamespaceEvidence()
        val sourceSet =
            SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementPolicy.currentSourceSetConfinementEvidence()
        val evidence = SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionPolicy
            .evaluateImplementationDecision(
                SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionRequest(
                    includePriorIdentityDecisionEvidence = true,
                    includePriorIsolationEvidence = true,
                    includePriorNamespaceEvidence = true,
                    includePriorSourceSetConfinementEvidence = true,
                ),
            )
        val selected = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selected.selectedCandidateId)
        assertTrue(selected.selectedProviderIsDisabled)
        assertIs<DisabledVaultCryptoProvider>(selected.selectedProvider)
        assertFalse(selected.productionProviderSelectable)
        assertTrue(evidence.priorIdentityDecisionEvidenceIncluded)
        assertTrue(evidence.priorIsolationEvidenceIncluded)
        assertTrue(evidence.priorNamespaceEvidenceIncluded)
        assertTrue(evidence.priorSourceSetConfinementEvidenceIncluded)
        assertFalse(decision.disabledCapabilities.canImplementProviderNow)
        assertFalse(isolation.disabledCapabilities.canImplementProviderNow)
        assertFalse(namespace.disabledCapabilities.canImplementProviderNow)
        assertFalse(sourceSet.capabilities.canImplementProviderNow)
        assertFalse(evidence.capabilities.canUseAsProviderSelectionId)
    }

    @Test
    fun productionProviderSelectableRemainsFalse() {
        val evidence =
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionPolicy.currentImplementationDecisionEvidence()

        assertFalse(evidence.capabilities.productionProviderSelectable)
        assertFalse(VaultCryptoProviderSelectionRegistry.select().productionProviderSelectable)
    }

    @Test
    fun allCurrentCapabilityBooleansRemainFalse() {
        val evidence =
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionPolicy.currentImplementationDecisionEvidence()

        assertAllCapabilityBooleansFalse(evidence.capabilities)
    }

    @Test
    fun allImplementationDecisionGatesAreRepresentedExactlyOnce() {
        val rows = currentEvidence().decisionGateRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.entries.toSet(),
            rows.map { it.gate }.toSet(),
        )
        assertTrue(rows.all { it.modeled })
        assertTrue(rows.none { it.implementationAuthorization })
        assertTrue(rows.none { it.productionPromotionAuthorization })
    }

    @Test
    fun allRequiredEvidenceClassesAreRepresentedExactlyOnce() {
        val rows = currentEvidence().requiredEvidenceRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationRequiredEvidenceClass.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRequiredEvidenceClass.entries.toSet(),
            rows.map { it.evidenceClass }.toSet(),
        )
        assertTrue(rows.all { it.modeled })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allForbiddenImplementationEffectsAreRepresentedExactlyOnce() {
        val rows = currentEvidence().forbiddenImplementationEffectRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityForbiddenImplementationEffect.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityForbiddenImplementationEffect.entries.toSet(),
            rows.map { it.effect }.toSet(),
        )
        assertTrue(rows.all { it.forbidden })
        assertTrue(rows.none { it.presentNow })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allForbiddenPromotionPathsAreRepresentedExactlyOnce() {
        val rows = currentEvidence().forbiddenPromotionPathRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityForbiddenPromotionPath.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityForbiddenPromotionPath.entries.toSet(),
            rows.map { it.promotionPath }.toSet(),
        )
        assertTrue(rows.all { it.forbidden })
        assertTrue(rows.none { it.currentBridgePresent })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun priorIdentityDecisionEvidenceRemainsNonAuthorizingWhenComposed() {
        val prior = SkaldVaultV1TestOnlyProviderIdentityDecisionPolicy.currentIdentityDecisionEvidence()
        val evidence = evidenceWithOnly(
            includeIdentityDecision = true,
            includeIsolation = false,
            includeNamespace = false,
            includeSourceSet = false,
        )
        val row = evidence.evidenceSourceRows.single {
            it.evidenceSource ==
                SkaldVaultV1TestOnlyProviderIdentityImplementationEvidenceSource.TestOnlyProviderIdentityDecision
        }

        assertTrue(evidence.priorIdentityDecisionEvidenceIncluded)
        assertTrue(row.included)
        assertTrue(row.nonAuthorizing)
        assertFalse(row.authorizesImplementation)
        assertFalse(prior.disabledCapabilities.canImplementProviderNow)
    }

    @Test
    fun priorIdentityIsolationEvidenceRemainsNonAuthorizingWhenComposed() {
        val prior = SkaldVaultV1TestOnlyProviderIdentityIsolationGuardPolicy.currentIsolationEvidence()
        val evidence = evidenceWithOnly(
            includeIdentityDecision = false,
            includeIsolation = true,
            includeNamespace = false,
            includeSourceSet = false,
        )
        val row = evidence.evidenceSourceRows.single {
            it.evidenceSource ==
                SkaldVaultV1TestOnlyProviderIdentityImplementationEvidenceSource.TestOnlyProviderIdentityIsolationGuard
        }

        assertTrue(evidence.priorIsolationEvidenceIncluded)
        assertTrue(row.included)
        assertTrue(row.nonAuthorizing)
        assertFalse(row.authorizesImplementation)
        assertFalse(prior.disabledCapabilities.canImplementProviderNow)
    }

    @Test
    fun priorSyntheticNamespaceEvidenceRemainsNonAuthorizingWhenComposed() {
        val prior = SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespacePolicy.currentNamespaceEvidence()
        val evidence = evidenceWithOnly(
            includeIdentityDecision = false,
            includeIsolation = false,
            includeNamespace = true,
            includeSourceSet = false,
        )
        val row = evidence.evidenceSourceRows.single {
            it.evidenceSource ==
                SkaldVaultV1TestOnlyProviderIdentityImplementationEvidenceSource.SyntheticIdentityNamespace
        }

        assertTrue(evidence.priorNamespaceEvidenceIncluded)
        assertTrue(row.included)
        assertTrue(row.nonAuthorizing)
        assertFalse(row.authorizesImplementation)
        assertFalse(prior.disabledCapabilities.canImplementProviderNow)
    }

    @Test
    fun priorSourceSetConfinementEvidenceRemainsNonAuthorizingWhenComposed() {
        val prior =
            SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementPolicy.currentSourceSetConfinementEvidence()
        val evidence = evidenceWithOnly(
            includeIdentityDecision = false,
            includeIsolation = false,
            includeNamespace = false,
            includeSourceSet = true,
        )
        val row = evidence.evidenceSourceRows.single {
            it.evidenceSource ==
                SkaldVaultV1TestOnlyProviderIdentityImplementationEvidenceSource
                    .TestOnlyProviderIdentitySourceSetConfinement
        }

        assertTrue(evidence.priorSourceSetConfinementEvidenceIncluded)
        assertTrue(row.included)
        assertTrue(row.nonAuthorizing)
        assertFalse(row.authorizesImplementation)
        assertFalse(prior.capabilities.canImplementProviderNow)
    }

    @Test
    fun absenceProvenGatesAreNegativeEvidenceOnlyAndDoNotAuthorizeImplementation() {
        val evidence = currentEvidence()
        val absenceRows = evidence.decisionGateRows.filter { it.negativeAbsenceEvidence }

        assertTrue(evidence.negativeAbsenceEvidenceModeled)
        assertFalse(evidence.negativeAbsenceEvidenceAuthorizesImplementation)
        assertTrue(absenceRows.isNotEmpty())
        assertTrue(absenceRows.all { it.satisfiedNow })
        assertTrue(absenceRows.none { it.implementationAuthorization })
        assertTrue(absenceRows.none { it.productionPromotionAuthorization })
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.ProductionSourceAbsenceProven in
                absenceRows.map { it.gate },
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.MainnetAbsenceProven in
                absenceRows.map { it.gate },
        )
    }

    @Test
    fun futureApprovalAndImplementationReviewGatesRemainUnsatisfied() {
        val rows = currentEvidence().decisionGateRows.associateBy { it.gate }

        assertFalse(
            rows.getValue(SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.ExplicitFutureBranchApproval)
                .satisfiedNow,
        )
        assertFalse(
            rows.getValue(SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.SourceSetPlacementApproved)
                .satisfiedNow,
        )
        assertFalse(
            rows.getValue(SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.SyntheticSafeIdApproved)
                .satisfiedNow,
        )
        assertFalse(
            rows.getValue(
                SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate
                    .FutureTestOnlyImplementationReviewComplete,
            ).satisfiedNow,
        )
    }

    @Test
    fun falsePositiveClaimsAddBlockersButDoNotChangeCapabilities() {
        val evidence = SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionPolicy
            .evaluateImplementationDecision(
                SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionRequest(
                    userConsentOverrideRequested = true,
                    warningOnlyEvidenceClaimed = true,
                    testOnlyEvidenceClaimedAsProductionPromotion = true,
                    releaseEvidenceClaimed = true,
                    futureBranchApprovalClaimed = true,
                    sourceSetPlacementApprovalClaimed = true,
                    syntheticSafeIdApprovalClaimed = true,
                    implementationReviewCompleteClaimed = true,
                    providerImplementationClaimed = true,
                    registryEntryClaimed = true,
                    factoryReachabilityClaimed = true,
                    dispatcherReachabilityClaimed = true,
                    executorTargetClaimed = true,
                    providerKatExecutorClaimed = true,
                    persistenceReachabilityClaimed = true,
                    settingsReachabilityClaimed = true,
                    uiReachabilityClaimed = true,
                    mainnetReachabilityClaimed = true,
                ),
            )

        assertAllCapabilityBooleansFalse(evidence.capabilities)
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.UserConsentCannotOverride in evidence.blockers)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.WarningOnlyEvidenceNonAuthorizing in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.TestOnlyEvidenceNonAuthorizing in
                evidence.blockers,
        )
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.ReleaseEvidenceNonAuthorizing in evidence.blockers)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.FutureBranchApprovalClaimRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.SourceSetPlacementApprovalClaimRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.SyntheticSafeIdApprovalClaimRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.ImplementationReviewCompleteClaimRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.ProviderImplementationClaimRejected in
                evidence.blockers,
        )
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.RegistryEntryClaimRejected in evidence.blockers)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.FactoryReachabilityRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.DispatcherReachabilityRejected in
                evidence.blockers,
        )
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.ExecutorTargetRejected in evidence.blockers)
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.ProviderKatExecutorRejected in evidence.blockers)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.PersistenceReachabilityRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.SettingsReachabilityRejected in
                evidence.blockers,
        )
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.UiReachabilityRejected in evidence.blockers)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.MainnetReachabilityRejected in
                evidence.blockers,
        )
    }

    @Test
    fun requestStringAndSafeLabelsRemainRedacted() {
        val request = SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionRequest(
            futureBranchApprovalClaimed = true,
            sourceSetPlacementApprovalClaimed = true,
            syntheticSafeIdApprovalClaimed = true,
            implementationReviewCompleteClaimed = true,
            providerImplementationClaimed = true,
            mainnetReachabilityClaimed = true,
        ).toString()
        val label = SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionSafeLabel("future-review").toString()

        val forbiddenTerms = listOf(
            "future-review",
            "candidate",
            "source",
            "placement",
            "identity",
            "provider",
            "storage",
            "backend",
            "crypto",
            "path",
            "endpoint",
            "wallet",
            "descriptor",
            "approval",
        )
        forbiddenTerms.forEach { term ->
            assertFalse(request.contains(term, ignoreCase = true), "request leaked $term")
            assertFalse(label.contains(term, ignoreCase = true), "label leaked $term")
        }
    }

    private fun currentEvidence() =
        SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionPolicy.currentImplementationDecisionEvidence()

    private fun evidenceWithOnly(
        includeIdentityDecision: Boolean,
        includeIsolation: Boolean,
        includeNamespace: Boolean,
        includeSourceSet: Boolean,
    ) = SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionPolicy.evaluateImplementationDecision(
        SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionRequest(
            includePriorIdentityDecisionEvidence = includeIdentityDecision,
            includePriorIsolationEvidence = includeIsolation,
            includePriorNamespaceEvidence = includeNamespace,
            includePriorSourceSetConfinementEvidence = includeSourceSet,
        ),
    )

    private fun assertAllCapabilityBooleansFalse(
        capabilities: SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionCapabilities,
    ) {
        assertFalse(capabilities.implementationDecisionAuthorizesImplementation)
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
        assertFalse(capabilities.canUseForStorageNamespace)
        assertFalse(capabilities.canUseForStoragePath)
        assertFalse(capabilities.canUseForManifestReadWrite)
        assertFalse(capabilities.canUseForMigration)
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
}
