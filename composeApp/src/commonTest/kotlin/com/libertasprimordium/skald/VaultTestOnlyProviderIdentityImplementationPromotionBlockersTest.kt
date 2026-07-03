package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDecisionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationContractPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenPromotionPath
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenPromotionSource
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenPromotionTarget
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersCapabilities
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersRequest
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersSafeLabel
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionEvidenceSource
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionOutcome
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionRequiredEvidenceClass
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionStage
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionStatus
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGatePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageGuardPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionPolicy
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

class VaultTestOnlyProviderIdentityImplementationPromotionBlockersTest {
    @Test
    fun currentPromotionBlockersEvidenceIsModeledAndStillDisabled() {
        val evidence =
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersPolicy
                .currentPromotionBlockersEvidence()

        assertEquals(
            "skald-vault-v1-test-only-provider-identity-implementation-promotion-blockers-v1",
            evidence.policyId,
        )
        assertEquals(1, evidence.policyVersion)
        assertTrue(evidence.promotionBlockersModeled)
        assertTrue(evidence.stillDisabled)
        assertTrue(evidence.promotionStagesModeled)
        assertTrue(evidence.forbiddenPromotionSourcesModeled)
        assertTrue(evidence.forbiddenPromotionTargetsModeled)
        assertTrue(evidence.forbiddenPromotionPathsModeled)
        assertTrue(evidence.docsMayDescribeFutureReview)
        assertTrue(evidence.testsMayAssertBlockedPromotion)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionStatus.PromotionBlockersModeled in
                evidence.statuses,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionStatus.StillDisabled in evidence.statuses,
        )
        assertFalse(evidence.capabilities.promotionBlockersAuthorizeImplementation)
    }

    @Test
    fun currentPromotionOutcomesRemainBlockedAndNonAuthorizing() {
        val outcomes = currentEvidence().outcomes

        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionOutcome.CurrentPromotionBlocked in outcomes)
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionOutcome.PromotionReviewRequired in outcomes)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionOutcome.TestOnlyIdentityNonPromotable in
                outcomes,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionOutcome.ProductionIdentityNotAuthorized in
                outcomes,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionOutcome.ProviderSelectionNotAuthorized in
                outcomes,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionOutcome.ProductionProviderSelectableFalse in
                outcomes,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionOutcome.VaultPersistenceNotAuthorized in
                outcomes,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionOutcome.ProductionSyncNotAuthorized in
                outcomes,
        )
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionOutcome.MainnetNotAuthorized in outcomes)
    }

    @Test
    fun noCurrentTestOnlyOrProductionProviderIdentityImplementationIsPresent() {
        val evidence = currentEvidence()

        assertFalse(evidence.capabilities.testOnlyIdentityImplementationPresent)
        assertFalse(evidence.capabilities.productionIdentityImplementationPresent)
        assertFalse(evidence.currentPromotionToTestOnlyIdentityImplementationPresent)
        assertFalse(evidence.currentPromotionToProductionIdentityPresent)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker
                .NoTestOnlyProviderIdentityImplementation in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker
                .NoProductionProviderIdentityImplementation in evidence.blockers,
        )
    }

    @Test
    fun providerSelectionRemainsDisabledProviderOnlyWhenAllPriorEvidenceIsComposed() {
        val decision = SkaldVaultV1TestOnlyProviderIdentityDecisionPolicy.currentIdentityDecisionEvidence()
        val isolation = SkaldVaultV1TestOnlyProviderIdentityIsolationGuardPolicy.currentIsolationEvidence()
        val namespace = SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespacePolicy.currentNamespaceEvidence()
        val sourceSet =
            SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementPolicy.currentSourceSetConfinementEvidence()
        val implementationDecision =
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionPolicy.currentImplementationDecisionEvidence()
        val prerequisiteAudit =
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditPolicy.currentPrerequisiteAuditEvidence()
        val scopeDecision =
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionPolicy.currentScopeDecisionEvidence()
        val implementationContract =
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractPolicy.currentImplementationContractEvidence()
        val readinessGate =
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGatePolicy.currentReadinessGateEvidence()
        val runtimeLinkage =
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageGuardPolicy
                .currentRuntimeLinkageGuardEvidence()
        val evidence = SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersPolicy
            .evaluatePromotionBlockers(
                SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersRequest(
                    includePriorIdentityDecisionEvidence = true,
                    includePriorIsolationEvidence = true,
                    includePriorNamespaceEvidence = true,
                    includePriorSourceSetConfinementEvidence = true,
                    includePriorImplementationDecisionEvidence = true,
                    includePriorPrerequisiteAuditEvidence = true,
                    includePriorScopeDecisionEvidence = true,
                    includePriorImplementationContractEvidence = true,
                    includePriorReadinessGateEvidence = true,
                    includePriorRuntimeLinkageGuardEvidence = true,
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
        assertTrue(evidence.priorImplementationDecisionEvidenceIncluded)
        assertTrue(evidence.priorPrerequisiteAuditEvidenceIncluded)
        assertTrue(evidence.priorScopeDecisionEvidenceIncluded)
        assertTrue(evidence.priorImplementationContractEvidenceIncluded)
        assertTrue(evidence.priorReadinessGateEvidenceIncluded)
        assertTrue(evidence.priorRuntimeLinkageGuardEvidenceIncluded)
        assertFalse(decision.disabledCapabilities.canImplementProviderNow)
        assertFalse(isolation.disabledCapabilities.canImplementProviderNow)
        assertFalse(namespace.disabledCapabilities.canImplementProviderNow)
        assertFalse(sourceSet.capabilities.canImplementProviderNow)
        assertFalse(implementationDecision.capabilities.implementationDecisionAuthorizesImplementation)
        assertFalse(prerequisiteAudit.capabilities.prerequisiteAuditAuthorizesImplementation)
        assertFalse(scopeDecision.capabilities.scopeDecisionAuthorizesImplementation)
        assertFalse(implementationContract.capabilities.implementationContractAuthorizesImplementation)
        assertFalse(readinessGate.capabilities.readinessGateAuthorizesImplementation)
        assertFalse(runtimeLinkage.capabilities.runtimeLinkageGuardAuthorizesImplementation)
        assertFalse(evidence.capabilities.promotionBlockersAuthorizeProviderSelection)
        assertFalse(evidence.currentPromotionToProviderSelectionPresent)
    }

    @Test
    fun productionProviderSelectableRemainsFalse() {
        val evidence = currentEvidence()

        assertFalse(evidence.capabilities.productionProviderSelectable)
        assertFalse(evidence.capabilities.promotionBlockersAuthorizeProductionProviderSelectable)
        assertFalse(VaultCryptoProviderSelectionRegistry.select().productionProviderSelectable)
    }

    @Test
    fun allCurrentCapabilityBooleansRemainFalse() {
        assertAllCapabilityBooleansFalse(currentEvidence().capabilities)
    }

    @Test
    fun allCurrentPromotionBooleansRemainFalse() {
        assertAllPromotionBooleansFalse(currentEvidence())
    }

    @Test
    fun allPromotionStagesAreRepresentedExactlyOnce() {
        val rows = currentEvidence().promotionStageRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionStage.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionStage.entries.toSet(),
            rows.map { it.stage }.toSet(),
        )
        assertTrue(rows.all { it.modeled })
        assertTrue(rows.none { it.currentPromotionPresent })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allForbiddenPromotionSourcesAreRepresentedExactlyOnce() {
        val rows = currentEvidence().forbiddenPromotionSourceRows

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenPromotionSource.entries.size,
            rows.size,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenPromotionSource.entries.toSet(),
            rows.map { it.source }.toSet(),
        )
        assertTrue(rows.all { it.forbidden })
        assertTrue(rows.none { it.authorizesImplementation })
        assertTrue(rows.none { it.authorizesProductionPromotion })
    }

    @Test
    fun allForbiddenPromotionTargetsAreRepresentedExactlyOnce() {
        val rows = currentEvidence().forbiddenPromotionTargetRows

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenPromotionTarget.entries.size,
            rows.size,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenPromotionTarget.entries.toSet(),
            rows.map { it.target }.toSet(),
        )
        assertTrue(rows.all { it.forbidden })
        assertTrue(rows.none { it.currentPromotionPresent })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allForbiddenPromotionPathsAreRepresentedExactlyOnce() {
        val rows = currentEvidence().forbiddenPromotionPathRows

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenPromotionPath.entries.size,
            rows.size,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenPromotionPath.entries.toSet(),
            rows.map { it.promotionRoute }.toSet(),
        )
        assertTrue(rows.all { it.forbidden })
        assertTrue(rows.none { it.currentPromotionPresent })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allRequiredEvidenceClassesAreRepresentedExactlyOnce() {
        val rows = currentEvidence().requiredEvidenceRows

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionRequiredEvidenceClass.entries.size,
            rows.size,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionRequiredEvidenceClass.entries.toSet(),
            rows.map { it.evidenceClass }.toSet(),
        )
        assertTrue(rows.all { it.modeled })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allPromotionBlockerRowsAreRepresentedExactlyOnceAndRemainNonAuthorizing() {
        val rows = currentEvidence().promotionBlockerRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.entries.toSet(),
            rows.map { it.blocker }.toSet(),
        )
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allPriorEvidenceSourcesRemainNonAuthorizingWhenComposed() {
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeIdentityDecision = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionEvidenceSource
                .TestOnlyProviderIdentityDecision,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeIsolation = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionEvidenceSource
                .TestOnlyProviderIdentityIsolationGuard,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeNamespace = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionEvidenceSource.SyntheticIdentityNamespace,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeSourceSet = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionEvidenceSource
                .TestOnlyProviderIdentitySourceSetConfinement,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeImplementationDecision = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionEvidenceSource
                .TestOnlyProviderIdentityImplementationDecision,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includePrerequisiteAudit = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionEvidenceSource
                .TestOnlyProviderIdentityImplementationPrerequisiteAudit,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeScopeDecision = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionEvidenceSource
                .TestOnlyProviderIdentityImplementationScopeDecision,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeImplementationContract = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionEvidenceSource
                .TestOnlyProviderIdentityImplementationContract,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeReadinessGate = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionEvidenceSource
                .TestOnlyProviderIdentityImplementationReadinessGate,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeRuntimeLinkageGuard = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionEvidenceSource
                .TestOnlyProviderIdentityImplementationRuntimeLinkageGuard,
        )
    }

    @Test
    fun falsePositiveClaimsAddBlockersButDoNotChangeCapabilitiesOrPromotionBooleans() {
        val evidence = SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersPolicy
            .evaluatePromotionBlockers(
                SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersRequest(
                    userConsentOverrideRequested = true,
                    warningOnlyEvidenceClaimed = true,
                    testOnlyEvidenceClaimedAsProductionPromotion = true,
                    releaseEvidenceClaimed = true,
                    futureBranchApprovalClaimed = true,
                    promotionClaimed = true,
                    testOnlyIdentityPromotionClaimed = true,
                    productionIdentityClaimed = true,
                    productionProviderClaimed = true,
                    productionProviderSelectableClaimed = true,
                    providerSelectionPromotionClaimed = true,
                    registryPromotionClaimed = true,
                    factoryPromotionClaimed = true,
                    dispatcherPromotionClaimed = true,
                    executorTargetPromotionClaimed = true,
                    providerKatExecutorPromotionClaimed = true,
                    providerOperationPromotionClaimed = true,
                    cryptoExecutionPromotionClaimed = true,
                    vaultPersistencePromotionClaimed = true,
                    productionSyncPromotionClaimed = true,
                    settingsPromotionClaimed = true,
                    uiPromotionClaimed = true,
                    signingBroadcastingPromotionClaimed = true,
                    publicEndpointPromotionClaimed = true,
                    mainnetPromotionClaimed = true,
                ),
            )

        assertAllCapabilityBooleansFalse(evidence.capabilities)
        assertAllPromotionBooleansFalse(evidence)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.UserConsentCannotOverride in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker
                .WarningOnlyEvidenceNonAuthorizing in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker
                .TestOnlyEvidenceNonAuthorizing in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.ReleaseEvidenceNonAuthorizing in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker
                .FutureBranchApprovalClaimRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.PromotionClaimRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker
                .TestOnlyIdentityPromotionClaimRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.ProductionIdentityClaimRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.ProductionProviderClaimRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker
                .ProductionProviderSelectableClaimRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker
                .ProviderSelectionPromotionRejected in evidence.blockers,
        )
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.RegistryPromotionRejected in evidence.blockers)
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.FactoryPromotionRejected in evidence.blockers)
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.DispatcherPromotionRejected in evidence.blockers)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.ExecutorTargetPromotionRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker
                .ProviderKatExecutorPromotionRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker
                .ProviderOperationPromotionRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.CryptoExecutionPromotionRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker
                .VaultPersistencePromotionRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.ProductionSyncPromotionRejected in
                evidence.blockers,
        )
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.SettingsPromotionRejected in evidence.blockers)
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.UiPromotionRejected in evidence.blockers)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker
                .SigningBroadcastingPromotionRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.PublicEndpointPromotionRejected in
                evidence.blockers,
        )
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.MainnetPromotionRejected in evidence.blockers)
    }

    @Test
    fun requestStringAndSafeLabelsRemainRedacted() {
        val request = SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersRequest(
            futureBranchApprovalClaimed = true,
            promotionClaimed = true,
            productionProviderSelectableClaimed = true,
            providerSelectionPromotionClaimed = true,
            cryptoExecutionPromotionClaimed = true,
            mainnetPromotionClaimed = true,
        ).toString()
        val label =
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersSafeLabel(
                "promotion-future-review",
            ).toString()

        val forbiddenTerms = listOf(
            "promotion-future-review",
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
            "prerequisite",
            "scope",
            "contract",
            "readiness",
            "runtime",
            "linkage",
            "promotion",
        )
        forbiddenTerms.forEach { term ->
            assertFalse(request.contains(term, ignoreCase = true), "request leaked $term")
            assertFalse(label.contains(term, ignoreCase = true), "label leaked $term")
        }
    }

    private fun currentEvidence() =
        SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersPolicy
            .currentPromotionBlockersEvidence()

    private fun evidenceWithOnly(
        includeIdentityDecision: Boolean = false,
        includeIsolation: Boolean = false,
        includeNamespace: Boolean = false,
        includeSourceSet: Boolean = false,
        includeImplementationDecision: Boolean = false,
        includePrerequisiteAudit: Boolean = false,
        includeScopeDecision: Boolean = false,
        includeImplementationContract: Boolean = false,
        includeReadinessGate: Boolean = false,
        includeRuntimeLinkageGuard: Boolean = false,
    ) = SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersPolicy.evaluatePromotionBlockers(
        SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersRequest(
            includePriorIdentityDecisionEvidence = includeIdentityDecision,
            includePriorIsolationEvidence = includeIsolation,
            includePriorNamespaceEvidence = includeNamespace,
            includePriorSourceSetConfinementEvidence = includeSourceSet,
            includePriorImplementationDecisionEvidence = includeImplementationDecision,
            includePriorPrerequisiteAuditEvidence = includePrerequisiteAudit,
            includePriorScopeDecisionEvidence = includeScopeDecision,
            includePriorImplementationContractEvidence = includeImplementationContract,
            includePriorReadinessGateEvidence = includeReadinessGate,
            includePriorRuntimeLinkageGuardEvidence = includeRuntimeLinkageGuard,
        ),
    )

    private fun assertPriorEvidenceNonAuthorizing(
        evidence: SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersEvidence,
        source: SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionEvidenceSource,
    ) {
        val row = evidence.evidenceSourceRows.single { it.evidenceSource == source }

        assertTrue(row.included)
        assertTrue(row.modeled)
        assertTrue(row.nonAuthorizing)
        assertFalse(row.authorizesImplementation)
        assertFalse(row.authorizesProductionPromotion)
        assertAllCapabilityBooleansFalse(evidence.capabilities)
        assertAllPromotionBooleansFalse(evidence)
    }

    private fun assertAllCapabilityBooleansFalse(
        capabilities: SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersCapabilities,
    ) {
        assertFalse(capabilities.promotionBlockersAuthorizeImplementation)
        assertFalse(capabilities.promotionBlockersAuthorizeProductionIdentity)
        assertFalse(capabilities.promotionBlockersAuthorizeProviderSelection)
        assertFalse(capabilities.promotionBlockersAuthorizeProductionProviderSelectable)
        assertFalse(capabilities.runtimeLinkageGuardAuthorizesImplementation)
        assertFalse(capabilities.readinessGateAuthorizesImplementation)
        assertFalse(capabilities.implementationContractAuthorizesImplementation)
        assertFalse(capabilities.scopeDecisionAuthorizesImplementation)
        assertFalse(capabilities.prerequisiteAuditAuthorizesImplementation)
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

    private fun assertAllPromotionBooleansFalse(
        evidence: SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersEvidence,
    ) {
        assertFalse(evidence.currentPromotionToTestOnlyIdentityImplementationPresent)
        assertFalse(evidence.currentPromotionToProductionIdentityPresent)
        assertFalse(evidence.currentPromotionToProviderSelectionPresent)
        assertFalse(evidence.currentPromotionToRegistryPresent)
        assertFalse(evidence.currentPromotionToFactoryPresent)
        assertFalse(evidence.currentPromotionToDispatcherPresent)
        assertFalse(evidence.currentPromotionToExecutorTargetPresent)
        assertFalse(evidence.currentPromotionToProviderKatExecutorPresent)
        assertFalse(evidence.currentPromotionToProviderOperationPresent)
        assertFalse(evidence.currentPromotionToCryptoExecutionPresent)
        assertFalse(evidence.currentPromotionToVaultPersistencePresent)
        assertFalse(evidence.currentPromotionToProductionSyncPresent)
        assertFalse(evidence.currentPromotionToSettingsPresent)
        assertFalse(evidence.currentPromotionToUiPresent)
        assertFalse(evidence.currentPromotionToSigningBroadcastingPresent)
        assertFalse(evidence.currentPromotionToPublicEndpointPresent)
        assertFalse(evidence.currentPromotionToMainnetPresent)
    }
}
