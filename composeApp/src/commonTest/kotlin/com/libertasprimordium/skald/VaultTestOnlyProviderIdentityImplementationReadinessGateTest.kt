package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDecisionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationContractPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenReadinessShortcut
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessDependency
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessEvidenceSource
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenPromotionPath
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateCapabilities
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGatePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateRequest
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateSafeLabel
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateStatus
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessOutcome
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessRequiredEvidenceClass
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection
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

class VaultTestOnlyProviderIdentityImplementationReadinessGateTest {
    @Test
    fun currentReadinessGateEvidenceIsModeledAndStillDisabled() {
        val evidence =
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGatePolicy.currentReadinessGateEvidence()

        assertEquals(
            "skald-vault-v1-test-only-provider-identity-implementation-readiness-gate-v1",
            evidence.policyId,
        )
        assertEquals(1, evidence.policyVersion)
        assertTrue(evidence.readinessGateModeled)
        assertTrue(evidence.stillDisabled)
        assertTrue(evidence.readinessDependenciesModeled)
        assertTrue(evidence.readinessChecksModeled)
        assertTrue(evidence.forbiddenRuntimeLinkagesModeled)
        assertTrue(evidence.docsMayDescribeFutureReview)
        assertTrue(evidence.testsMayAssertBlockedReadiness)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateStatus.ReadinessGateModeled in
                evidence.statuses,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateStatus.StillDisabled in
                evidence.statuses,
        )
        assertFalse(evidence.capabilities.readinessGateAuthorizesImplementation)
    }

    @Test
    fun currentReadinessOutcomesRemainBlockedIncompleteAndNonAuthorizing() {
        val outcomes = currentEvidence().outcomes

        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessOutcome.CurrentReadinessBlocked in
                outcomes,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessOutcome.ReadinessReviewRequired in
                outcomes,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessOutcome.DependenciesIncomplete in
                outcomes,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessOutcome
                .ContractNotSatisfiedForImplementation in outcomes,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessOutcome.ImplementationNotAuthorized in
                outcomes,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessOutcome.ProductionPromotionNotAuthorized in
                outcomes,
        )
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessOutcome.MainnetNotAuthorized in outcomes)
    }

    @Test
    fun noCurrentTestOnlyOrProductionProviderIdentityImplementationIsPresent() {
        val evidence = currentEvidence()

        assertFalse(evidence.capabilities.testOnlyIdentityImplementationPresent)
        assertFalse(evidence.capabilities.productionIdentityImplementationPresent)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker
                .NoTestOnlyProviderIdentityImplementation in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker
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
        val evidence = SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGatePolicy
            .evaluateReadinessGate(
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateRequest(
                    includePriorIdentityDecisionEvidence = true,
                    includePriorIsolationEvidence = true,
                    includePriorNamespaceEvidence = true,
                    includePriorSourceSetConfinementEvidence = true,
                    includePriorImplementationDecisionEvidence = true,
                    includePriorPrerequisiteAuditEvidence = true,
                    includePriorScopeDecisionEvidence = true,
                    includePriorImplementationContractEvidence = true,
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
        assertFalse(decision.disabledCapabilities.canImplementProviderNow)
        assertFalse(isolation.disabledCapabilities.canImplementProviderNow)
        assertFalse(namespace.disabledCapabilities.canImplementProviderNow)
        assertFalse(sourceSet.capabilities.canImplementProviderNow)
        assertFalse(implementationDecision.capabilities.implementationDecisionAuthorizesImplementation)
        assertFalse(prerequisiteAudit.capabilities.prerequisiteAuditAuthorizesImplementation)
        assertFalse(scopeDecision.capabilities.scopeDecisionAuthorizesImplementation)
        assertFalse(implementationContract.capabilities.implementationContractAuthorizesImplementation)
        assertFalse(evidence.capabilities.canUseAsProviderSelectionId)
    }

    @Test
    fun productionProviderSelectableRemainsFalse() {
        val evidence = currentEvidence()

        assertFalse(evidence.capabilities.productionProviderSelectable)
        assertFalse(VaultCryptoProviderSelectionRegistry.select().productionProviderSelectable)
    }

    @Test
    fun allCurrentCapabilityBooleansRemainFalse() {
        assertAllCapabilityBooleansFalse(currentEvidence().capabilities)
    }

    @Test
    fun allReadinessSectionsAreRepresentedExactlyOnce() {
        val rows = currentEvidence().sectionRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.entries.toSet(),
            rows.map { it.section }.toSet(),
        )
        assertTrue(rows.all { it.modeled })
        assertTrue(rows.all { it.nonAuthorizing })
        assertTrue(rows.none { it.currentImplementationAllowed })
    }

    @Test
    fun allReadinessDependenciesAreRepresentedExactlyOnce() {
        val rows = currentEvidence().dependencyRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessDependency.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessDependency.entries.toSet(),
            rows.map { it.dependency }.toSet(),
        )
        assertTrue(rows.all { it.modeled })
        assertTrue(rows.all { it.nonAuthorizing })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allReadinessChecksAreRepresentedExactlyOnceAndRemainNonAuthorizing() {
        val rows = currentEvidence().checkRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.entries.toSet(),
            rows.map { it.check }.toSet(),
        )
        assertTrue(rows.all { it.modeled })
        assertTrue(rows.all { it.nonAuthorizing })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allRequiredEvidenceClassesAreRepresentedExactlyOnce() {
        val rows = currentEvidence().requiredEvidenceRows

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessRequiredEvidenceClass.entries.size,
            rows.size,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessRequiredEvidenceClass.entries.toSet(),
            rows.map { it.evidenceClass }.toSet(),
        )
        assertTrue(rows.all { it.modeled })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allForbiddenReadinessShortcutsAreRepresentedExactlyOnce() {
        val rows = currentEvidence().forbiddenReadinessShortcutRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenReadinessShortcut.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenReadinessShortcut.entries.toSet(),
            rows.map { it.shortcut }.toSet(),
        )
        assertTrue(rows.all { it.forbidden })
        assertTrue(rows.none { it.currentBridgePresent })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allForbiddenRuntimeLinkagesAreRepresentedExactlyOnce() {
        val rows = currentEvidence().forbiddenRuntimeLinkageRows

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage.entries.size,
            rows.size,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage.entries.toSet(),
            rows.map { it.runtimeLinkage }.toSet(),
        )
        assertTrue(rows.all { it.forbidden })
        assertTrue(rows.none { it.currentBridgePresent })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allForbiddenPromotionPathsAreRepresentedExactlyOnce() {
        val rows = currentEvidence().forbiddenPromotionPathRows

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenPromotionPath.entries.size,
            rows.size,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenPromotionPath.entries.toSet(),
            rows.map { it.promotionRoute }.toSet(),
        )
        assertTrue(rows.all { it.forbidden })
        assertTrue(rows.none { it.currentBridgePresent })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun priorIdentityDecisionEvidenceRemainsNonAuthorizingWhenComposed() {
        val prior = SkaldVaultV1TestOnlyProviderIdentityDecisionPolicy.currentIdentityDecisionEvidence()
        val evidence = evidenceWithOnly(includeIdentityDecision = true)
        val row = evidence.rowFor(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessEvidenceSource
                .TestOnlyProviderIdentityDecision,
        )

        assertTrue(evidence.priorIdentityDecisionEvidenceIncluded)
        assertTrue(row.included)
        assertTrue(row.nonAuthorizing)
        assertFalse(row.authorizesImplementation)
        assertFalse(prior.disabledCapabilities.canImplementProviderNow)
    }

    @Test
    fun priorIdentityIsolationEvidenceRemainsNonAuthorizingWhenComposed() {
        val prior = SkaldVaultV1TestOnlyProviderIdentityIsolationGuardPolicy.currentIsolationEvidence()
        val evidence = evidenceWithOnly(includeIsolation = true)
        val row = evidence.rowFor(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessEvidenceSource
                .TestOnlyProviderIdentityIsolationGuard,
        )

        assertTrue(evidence.priorIsolationEvidenceIncluded)
        assertTrue(row.included)
        assertTrue(row.nonAuthorizing)
        assertFalse(row.authorizesImplementation)
        assertFalse(prior.disabledCapabilities.canImplementProviderNow)
    }

    @Test
    fun priorSyntheticNamespaceEvidenceRemainsNonAuthorizingWhenComposed() {
        val prior = SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespacePolicy.currentNamespaceEvidence()
        val evidence = evidenceWithOnly(includeNamespace = true)
        val row = evidence.rowFor(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessEvidenceSource.SyntheticIdentityNamespace,
        )

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
        val evidence = evidenceWithOnly(includeSourceSet = true)
        val row = evidence.rowFor(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessEvidenceSource
                .TestOnlyProviderIdentitySourceSetConfinement,
        )

        assertTrue(evidence.priorSourceSetConfinementEvidenceIncluded)
        assertTrue(row.included)
        assertTrue(row.nonAuthorizing)
        assertFalse(row.authorizesImplementation)
        assertFalse(prior.capabilities.canImplementProviderNow)
    }

    @Test
    fun priorImplementationDecisionEvidenceRemainsNonAuthorizingWhenComposed() {
        val prior =
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionPolicy.currentImplementationDecisionEvidence()
        val evidence = evidenceWithOnly(includeImplementationDecision = true)
        val row = evidence.rowFor(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessEvidenceSource
                .TestOnlyProviderIdentityImplementationDecision,
        )

        assertTrue(evidence.priorImplementationDecisionEvidenceIncluded)
        assertTrue(row.included)
        assertTrue(row.nonAuthorizing)
        assertFalse(row.authorizesImplementation)
        assertFalse(prior.capabilities.implementationDecisionAuthorizesImplementation)
    }

    @Test
    fun priorPrerequisiteAuditEvidenceRemainsNonAuthorizingWhenComposed() {
        val prior =
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditPolicy.currentPrerequisiteAuditEvidence()
        val evidence = evidenceWithOnly(includePrerequisiteAudit = true)
        val row = evidence.rowFor(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessEvidenceSource
                .TestOnlyProviderIdentityImplementationPrerequisiteAudit,
        )

        assertTrue(evidence.priorPrerequisiteAuditEvidenceIncluded)
        assertTrue(row.included)
        assertTrue(row.nonAuthorizing)
        assertFalse(row.authorizesImplementation)
        assertFalse(prior.capabilities.prerequisiteAuditAuthorizesImplementation)
    }

    @Test
    fun priorScopeDecisionEvidenceRemainsNonAuthorizingWhenComposed() {
        val prior = SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionPolicy.currentScopeDecisionEvidence()
        val evidence = evidenceWithOnly(includeScopeDecision = true)
        val row = evidence.rowFor(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessEvidenceSource
                .TestOnlyProviderIdentityImplementationScopeDecision,
        )

        assertTrue(evidence.priorScopeDecisionEvidenceIncluded)
        assertTrue(row.included)
        assertTrue(row.nonAuthorizing)
        assertFalse(row.authorizesImplementation)
        assertFalse(prior.capabilities.scopeDecisionAuthorizesImplementation)
    }

    @Test
    fun priorImplementationContractEvidenceRemainsNonAuthorizingWhenComposed() {
        val prior =
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractPolicy.currentImplementationContractEvidence()
        val evidence = evidenceWithOnly(includeImplementationContract = true)
        val row = evidence.rowFor(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessEvidenceSource
                .TestOnlyProviderIdentityImplementationContract,
        )

        assertTrue(evidence.priorImplementationContractEvidenceIncluded)
        assertTrue(row.included)
        assertTrue(row.nonAuthorizing)
        assertFalse(row.authorizesImplementation)
        assertFalse(prior.capabilities.implementationContractAuthorizesImplementation)
    }

    @Test
    fun falsePositiveClaimsAddBlockersButDoNotChangeCapabilities() {
        val evidence = SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGatePolicy
            .evaluateReadinessGate(
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateRequest(
                    userConsentOverrideRequested = true,
                    warningOnlyEvidenceClaimed = true,
                    testOnlyEvidenceClaimedAsProductionPromotion = true,
                    releaseEvidenceClaimed = true,
                    futureBranchApprovalClaimed = true,
                    prerequisiteCompletionClaimed = true,
                    scopeApprovalClaimed = true,
                    contractSatisfactionClaimed = true,
                    readinessClaimed = true,
                    readinessClaimedAsImplementationAuthorization = true,
                    providerImplementationClaimed = true,
                    registryEntryClaimed = true,
                    factoryReachabilityClaimed = true,
                    dispatcherReachabilityClaimed = true,
                    executorTargetClaimed = true,
                    providerKatExecutorClaimed = true,
                    providerOperationClaimed = true,
                    cryptoExecutionClaimed = true,
                    persistenceReachabilityClaimed = true,
                    settingsReachabilityClaimed = true,
                    uiReachabilityClaimed = true,
                    mainnetReachabilityClaimed = true,
                ),
            )

        assertAllCapabilityBooleansFalse(evidence.capabilities)
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.UserConsentCannotOverride in evidence.blockers)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.WarningOnlyEvidenceNonAuthorizing in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.TestOnlyEvidenceNonAuthorizing in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.ReleaseEvidenceNonAuthorizing in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.FutureBranchApprovalClaimRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker
                .PrerequisiteCompletionClaimRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.ScopeApprovalClaimRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.ContractSatisfactionClaimRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.ReadinessClaimRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker
                .ReadinessAsImplementationAuthorizationRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.ProviderImplementationClaimRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.RegistryEntryClaimRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.FactoryReachabilityRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.DispatcherReachabilityRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.ExecutorTargetRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.ProviderKatExecutorRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.ProviderOperationRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.CryptoExecutionRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.PersistenceReachabilityRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.SettingsReachabilityRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.UiReachabilityRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.MainnetReachabilityRejected in
                evidence.blockers,
        )
    }

    @Test
    fun requestStringAndSafeLabelsRemainRedacted() {
        val request = SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateRequest(
            futureBranchApprovalClaimed = true,
            prerequisiteCompletionClaimed = true,
            scopeApprovalClaimed = true,
            contractSatisfactionClaimed = true,
            readinessClaimed = true,
            providerImplementationClaimed = true,
            cryptoExecutionClaimed = true,
            mainnetReachabilityClaimed = true,
        ).toString()
        val label =
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateSafeLabel(
                "readiness-future-review",
            ).toString()

        val forbiddenTerms = listOf(
            "readiness-future-review",
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
        )
        forbiddenTerms.forEach { term ->
            assertFalse(request.contains(term, ignoreCase = true), "request leaked $term")
            assertFalse(label.contains(term, ignoreCase = true), "label leaked $term")
        }
    }

    private fun currentEvidence() =
        SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGatePolicy.currentReadinessGateEvidence()

    private fun evidenceWithOnly(
        includeIdentityDecision: Boolean = false,
        includeIsolation: Boolean = false,
        includeNamespace: Boolean = false,
        includeSourceSet: Boolean = false,
        includeImplementationDecision: Boolean = false,
        includePrerequisiteAudit: Boolean = false,
        includeScopeDecision: Boolean = false,
        includeImplementationContract: Boolean = false,
    ) = SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGatePolicy.evaluateReadinessGate(
        SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateRequest(
            includePriorIdentityDecisionEvidence = includeIdentityDecision,
            includePriorIsolationEvidence = includeIsolation,
            includePriorNamespaceEvidence = includeNamespace,
            includePriorSourceSetConfinementEvidence = includeSourceSet,
            includePriorImplementationDecisionEvidence = includeImplementationDecision,
            includePriorPrerequisiteAuditEvidence = includePrerequisiteAudit,
            includePriorScopeDecisionEvidence = includeScopeDecision,
            includePriorImplementationContractEvidence = includeImplementationContract,
        ),
    )

    private fun SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateEvidence.rowFor(
        source: SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessEvidenceSource,
    ) = evidenceSourceRows.single { it.evidenceSource == source }

    private fun assertAllCapabilityBooleansFalse(
        capabilities: SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateCapabilities,
    ) {
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
}
