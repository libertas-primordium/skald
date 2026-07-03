package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDecisionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationContractPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationCoverageGap
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenPatternClass
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeRoot
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationGuardCoverageCategory
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationGuardedModelBoundary
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGatePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageGuardPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageCapabilities
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageEvidenceSource
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageForbiddenPromotionPath
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageOutcome
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoveragePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageRequest
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageRequiredEvidenceClass
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageSafeLabel
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageStatus
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

class VaultTestOnlyProviderIdentityImplementationSourceGuardCoverageTest {
    @Test
    fun currentSourceGuardCoverageEvidenceIsModeledAndStillDisabled() {
        val evidence =
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoveragePolicy
                .currentSourceGuardCoverageEvidence()

        assertEquals(
            "skald-vault-v1-test-only-provider-identity-implementation-source-guard-coverage-v1",
            evidence.policyId,
        )
        assertEquals(1, evidence.policyVersion)
        assertTrue(evidence.sourceGuardCoverageModeled)
        assertTrue(evidence.stillDisabled)
        assertTrue(evidence.allIdentityImplementationModelBoundariesRepresented)
        assertTrue(evidence.desktopSourceGuardCoverageModeled)
        assertTrue(evidence.runtimeRootCoverageModeled)
        assertTrue(evidence.forbiddenImportScanModeled)
        assertTrue(evidence.materialScanModeled)
        assertTrue(evidence.positiveFlagScanModeled)
        assertTrue(evidence.runtimeHookScanModeled)
        assertTrue(evidence.promotionFlagScanModeled)
        assertTrue(evidence.docsMayDescribeCoverage)
        assertTrue(evidence.testsMayAssertBlockedSourceGuardCoverage)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageStatus
                .SourceGuardCoverageModeled in evidence.statuses,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageStatus.StillDisabled in
                evidence.statuses,
        )
        assertFalse(evidence.capabilities.sourceGuardCoverageAuthorizesImplementation)
    }

    @Test
    fun currentCoverageOutcomesRemainModeledBlockedAndNonAuthorizing() {
        val outcomes = currentEvidence().outcomes

        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageOutcome.CurrentCoverageModeled in
                outcomes,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageOutcome
                .SourceGuardCoverageRequired in outcomes,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageOutcome
                .RuntimeRootsRemainUnlinked in outcomes,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageOutcome
                .ImplementationNotAuthorized in outcomes,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageOutcome
                .ProductionPromotionNotAuthorized in outcomes,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageOutcome.MainnetNotAuthorized in
                outcomes,
        )
    }

    @Test
    fun allIdentityImplementationModelBoundariesAreRepresentedExactlyOnce() {
        val rows = currentEvidence().guardedModelBoundaryRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationGuardedModelBoundary.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationGuardedModelBoundary.entries.toSet(),
            rows.map { it.boundary }.toSet(),
        )
        assertTrue(rows.all { it.modeled })
        assertTrue(rows.all { it.represented })
        assertTrue(rows.all { it.coveredByDesktopSourceGuard })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allGuardCoverageCategoriesAreRepresentedExactlyOnce() {
        val rows = currentEvidence().guardCoverageCategoryRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationGuardCoverageCategory.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationGuardCoverageCategory.entries.toSet(),
            rows.map { it.category }.toSet(),
        )
        assertTrue(rows.all { it.modeled })
        assertTrue(rows.all { it.nonAuthorizing })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allForbiddenRuntimeRootsAreRepresentedExactlyOnce() {
        val rows = currentEvidence().forbiddenRuntimeRootRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeRoot.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeRoot.entries.toSet(),
            rows.map { it.root }.toSet(),
        )
        assertTrue(rows.all { it.coveredByRuntimeRootScan })
        assertTrue(rows.none { it.positiveFlagPresent })
        assertTrue(rows.none { it.runtimeHookPresent })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allForbiddenPatternClassesAreRepresentedExactlyOnce() {
        val rows = currentEvidence().forbiddenPatternClassRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenPatternClass.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenPatternClass.entries.toSet(),
            rows.map { it.patternClass }.toSet(),
        )
        assertTrue(rows.all { it.modeled })
        assertTrue(rows.none { it.presentInRuntimeRoots })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allCoverageGapsAreRepresentedExactlyOnceAndRemainNonAuthorizing() {
        val rows = currentEvidence().coverageGapRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationCoverageGap.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationCoverageGap.entries.toSet(),
            rows.map { it.gap }.toSet(),
        )
        assertTrue(rows.all { it.modeled })
        assertTrue(rows.none { it.currentGapPresent })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allRequiredEvidenceClassesAreRepresentedExactlyOnce() {
        val rows = currentEvidence().requiredEvidenceRows

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageRequiredEvidenceClass.entries.size,
            rows.size,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageRequiredEvidenceClass.entries.toSet(),
            rows.map { it.evidenceClass }.toSet(),
        )
        assertTrue(rows.all { it.modeled })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allForbiddenPromotionPathsAreRepresentedExactlyOnce() {
        val rows = currentEvidence().forbiddenPromotionPathRows

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageForbiddenPromotionPath.entries.size,
            rows.size,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageForbiddenPromotionPath.entries.toSet(),
            rows.map { it.promotionRoute }.toSet(),
        )
        assertTrue(rows.all { it.forbidden })
        assertTrue(rows.none { it.authorizesImplementation })
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
        val promotionBlockers =
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersPolicy
                .currentPromotionBlockersEvidence()
        val evidence = SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoveragePolicy
            .evaluateSourceGuardCoverage(
                SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageRequest(
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
                    includePriorPromotionBlockersEvidence = true,
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
        assertTrue(evidence.priorPromotionBlockersEvidenceIncluded)
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
        assertFalse(promotionBlockers.capabilities.promotionBlockersAuthorizeProviderSelection)
        assertFalse(evidence.capabilities.sourceGuardCoverageAuthorizesImplementation)
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
    fun allPositiveRuntimeAndPromotionBooleansRemainFalse() {
        assertAllPositiveRuntimeAndPromotionBooleansFalse(currentEvidence())
    }

    @Test
    fun coverageBooleansThatAreTrueAreOnlyNonAuthorizingCoverageEvidence() {
        val evidence = currentEvidence()

        assertTrue(evidence.allIdentityImplementationModelBoundariesRepresented)
        assertTrue(evidence.desktopSourceGuardCoverageModeled)
        assertTrue(evidence.runtimeRootCoverageModeled)
        assertTrue(evidence.forbiddenImportScanModeled)
        assertTrue(evidence.materialScanModeled)
        assertTrue(evidence.positiveFlagScanModeled)
        assertTrue(evidence.runtimeHookScanModeled)
        assertTrue(evidence.promotionFlagScanModeled)
        assertTrue(evidence.guardCoverageCategoryRows.all { it.nonAuthorizing })
        assertTrue(evidence.guardedModelBoundaryRows.none { it.authorizesImplementation })
        assertTrue(evidence.coverageGapRows.none { it.authorizesImplementation })
        assertAllCapabilityBooleansFalse(evidence.capabilities)
    }

    @Test
    fun allPriorEvidenceSourcesRemainNonAuthorizingWhenComposed() {
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeIdentityDecision = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageEvidenceSource
                .TestOnlyProviderIdentityDecision,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeIsolation = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageEvidenceSource
                .TestOnlyProviderIdentityIsolationGuard,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeNamespace = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageEvidenceSource
                .SyntheticIdentityNamespace,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeSourceSet = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageEvidenceSource
                .TestOnlyProviderIdentitySourceSetConfinement,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeImplementationDecision = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageEvidenceSource
                .TestOnlyProviderIdentityImplementationDecision,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includePrerequisiteAudit = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageEvidenceSource
                .TestOnlyProviderIdentityImplementationPrerequisiteAudit,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeScopeDecision = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageEvidenceSource
                .TestOnlyProviderIdentityImplementationScopeDecision,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeImplementationContract = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageEvidenceSource
                .TestOnlyProviderIdentityImplementationContract,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeReadinessGate = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageEvidenceSource
                .TestOnlyProviderIdentityImplementationReadinessGate,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeRuntimeLinkageGuard = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageEvidenceSource
                .TestOnlyProviderIdentityImplementationRuntimeLinkageGuard,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includePromotionBlockers = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageEvidenceSource
                .TestOnlyProviderIdentityImplementationPromotionBlockers,
        )
    }

    @Test
    fun falsePositiveClaimsAddBlockersButDoNotChangeCapabilitiesOrPositiveFlags() {
        val evidence = SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoveragePolicy
            .evaluateSourceGuardCoverage(
                SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageRequest(
                    userConsentOverrideRequested = true,
                    warningOnlyEvidenceClaimed = true,
                    testOnlyEvidenceClaimedAsProductionPromotion = true,
                    releaseEvidenceClaimed = true,
                    sourceGuardCoverageClaimed = true,
                    sourceGuardCoverageClaimedAsImplementationAuthorization = true,
                    runtimeRootCoverageClaimed = true,
                    positiveFlagScanClaimed = true,
                    materialScanClaimed = true,
                    docCrossLinkClaimed = true,
                    providerImplementationClaimed = true,
                    providerSelectionClaimed = true,
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
                    promotionClaimed = true,
                    mainnetReachabilityClaimed = true,
                ),
            )

        assertAllCapabilityBooleansFalse(evidence.capabilities)
        assertAllPositiveRuntimeAndPromotionBooleansFalse(evidence)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                .UserConsentCannotOverride in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                .WarningOnlyEvidenceNonAuthorizing in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                .TestOnlyEvidenceNonAuthorizing in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                .ReleaseEvidenceNonAuthorizing in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                .CoverageClaimNonAuthorizing in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                .RuntimeRootClaimRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                .PositiveFlagClaimRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                .ProviderImplementationClaimRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                .ProviderSelectionClaimRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                .RegistryEntryClaimRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                .FactoryReachabilityClaimRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                .DispatcherReachabilityClaimRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                .ExecutorTargetClaimRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                .ProviderKatExecutorClaimRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                .ProviderOperationClaimRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                .CryptoExecutionClaimRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                .PersistenceReachabilityClaimRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                .SettingsReachabilityClaimRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                .UiReachabilityClaimRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                .PromotionClaimRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                .MainnetReachabilityClaimRejected in evidence.blockers,
        )
    }

    @Test
    fun requestStringAndSafeLabelsRemainRedacted() {
        val request = SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageRequest(
            sourceGuardCoverageClaimed = true,
            sourceGuardCoverageClaimedAsImplementationAuthorization = true,
            runtimeRootCoverageClaimed = true,
            positiveFlagScanClaimed = true,
            providerImplementationClaimed = true,
            providerSelectionClaimed = true,
            mainnetReachabilityClaimed = true,
        ).toString()
        val label =
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageSafeLabel(
                "source-guard-future-review",
            ).toString()

        val forbiddenTerms = listOf(
            "source-guard-future-review",
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
            "prerequisite",
            "scope",
            "contract",
            "readiness",
            "runtime linkage",
            "promotion",
            "source guard",
        )
        forbiddenTerms.forEach { term ->
            assertFalse(request.contains(term, ignoreCase = true), "request leaked $term")
            assertFalse(label.contains(term, ignoreCase = true), "label leaked $term")
        }
    }

    @Test
    fun sourceGuardCoverageModelsForbiddenImportScanForNewCommonMainFile() {
        val evidence = currentEvidence()
        val forbiddenImportScanRow = evidence.guardCoverageCategoryRows.single {
            it.category == SkaldVaultV1TestOnlyProviderIdentityImplementationGuardCoverageCategory
                .ForbiddenImportScanCoverage
        }
        val sourceGuardCoverageBoundary = evidence.guardedModelBoundaryRows.single {
            it.boundary == SkaldVaultV1TestOnlyProviderIdentityImplementationGuardedModelBoundary
                .TestOnlyProviderIdentityImplementationSourceGuardCoverage
        }

        assertTrue(forbiddenImportScanRow.modeled)
        assertTrue(forbiddenImportScanRow.nonAuthorizing)
        assertFalse(forbiddenImportScanRow.authorizesImplementation)
        assertTrue(sourceGuardCoverageBoundary.coveredByDesktopSourceGuard)
        assertFalse(sourceGuardCoverageBoundary.authorizesImplementation)
        assertFalse(evidence.positiveCryptoExecutionFlagPresent)
        assertFalse(evidence.capabilities.canExecuteAead)
        assertFalse(evidence.capabilities.canUseForBdkWalletState)
        assertFalse(evidence.capabilities.canUseForSettingsCodec)
        assertFalse(evidence.capabilities.canUseForMainnet)
    }

    private fun currentEvidence() =
        SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoveragePolicy
            .currentSourceGuardCoverageEvidence()

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
        includePromotionBlockers: Boolean = false,
    ) = SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoveragePolicy.evaluateSourceGuardCoverage(
        SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageRequest(
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
            includePriorPromotionBlockersEvidence = includePromotionBlockers,
        ),
    )

    private fun assertPriorEvidenceNonAuthorizing(
        evidence: SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageEvidence,
        source: SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageEvidenceSource,
    ) {
        val row = evidence.evidenceSourceRows.single { it.evidenceSource == source }

        assertTrue(row.included)
        assertTrue(row.modeled)
        assertTrue(row.nonAuthorizing)
        assertFalse(row.authorizesImplementation)
        assertAllCapabilityBooleansFalse(evidence.capabilities)
        assertAllPositiveRuntimeAndPromotionBooleansFalse(evidence)
    }

    private fun assertAllCapabilityBooleansFalse(
        capabilities: SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageCapabilities,
    ) {
        assertFalse(capabilities.sourceGuardCoverageAuthorizesImplementation)
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

    private fun assertAllPositiveRuntimeAndPromotionBooleansFalse(
        evidence: SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageEvidence,
    ) {
        assertFalse(evidence.positiveImplementationFlagPresent)
        assertFalse(evidence.positivePromotionFlagPresent)
        assertFalse(evidence.positiveRuntimeBridgeFlagPresent)
        assertFalse(evidence.positiveProviderSelectionFlagPresent)
        assertFalse(evidence.positiveRegistryFactoryDispatcherFlagPresent)
        assertFalse(evidence.positiveExecutorTargetFlagPresent)
        assertFalse(evidence.positiveProviderKatExecutorFlagPresent)
        assertFalse(evidence.positiveProviderOperationFlagPresent)
        assertFalse(evidence.positiveCryptoExecutionFlagPresent)
        assertFalse(evidence.positiveVaultPersistenceFlagPresent)
        assertFalse(evidence.positiveProductionSyncFlagPresent)
        assertFalse(evidence.positiveSettingsUiFlagPresent)
        assertFalse(evidence.positiveBackendBdkFlagPresent)
        assertFalse(evidence.positiveSigningBroadcastingFlagPresent)
        assertFalse(evidence.positivePublicEndpointFlagPresent)
        assertFalse(evidence.positiveMainnetFlagPresent)
    }
}
