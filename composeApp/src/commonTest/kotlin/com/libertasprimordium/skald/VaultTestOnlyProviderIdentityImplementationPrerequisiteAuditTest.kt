package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDecisionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenShortcut
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditCapabilities
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditOutcome
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditRequest
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditSafeLabel
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditStatus
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteCategory
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteEvidenceSource
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteForbiddenPromotionPath
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteRequiredEvidenceClass
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

class VaultTestOnlyProviderIdentityImplementationPrerequisiteAuditTest {
    @Test
    fun currentPrerequisiteAuditEvidenceIsModeledAndStillDisabled() {
        val evidence =
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditPolicy
                .currentPrerequisiteAuditEvidence()

        assertEquals(
            "skald-vault-v1-test-only-provider-identity-implementation-prerequisite-audit-v1",
            evidence.policyId,
        )
        assertEquals(1, evidence.policyVersion)
        assertTrue(evidence.prerequisiteAuditModeled)
        assertTrue(evidence.stillDisabled)
        assertTrue(evidence.docsMayDescribeFutureReview)
        assertTrue(evidence.testsMayAssertBlockedAudit)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditStatus
                .PrerequisiteAuditModeled in evidence.statuses,
        )
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditStatus.StillDisabled in evidence.statuses)
        assertFalse(evidence.capabilities.prerequisiteAuditAuthorizesImplementation)
    }

    @Test
    fun currentAuditOutcomesRemainBlockedIncompleteAndNonAuthorizing() {
        val outcomes = currentEvidence().outcomes

        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditOutcome.CurrentAuditBlocked in outcomes)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditOutcome.PrerequisitesIncomplete in
                outcomes,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditOutcome.FutureBranchReviewRequired in
                outcomes,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditOutcome.ImplementationNotAuthorized in
                outcomes,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditOutcome.ProductionPromotionNotAuthorized in
                outcomes,
        )
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditOutcome.MainnetNotAuthorized in outcomes)
    }

    @Test
    fun noCurrentTestOnlyOrProductionProviderIdentityImplementationIsPresent() {
        val evidence = currentEvidence()

        assertFalse(evidence.capabilities.testOnlyIdentityImplementationPresent)
        assertFalse(evidence.capabilities.productionIdentityImplementationPresent)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker
                .NoTestOnlyProviderIdentityImplementation in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker
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
        val evidence = SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditPolicy
            .evaluatePrerequisiteAudit(
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditRequest(
                    includePriorIdentityDecisionEvidence = true,
                    includePriorIsolationEvidence = true,
                    includePriorNamespaceEvidence = true,
                    includePriorSourceSetConfinementEvidence = true,
                    includePriorImplementationDecisionEvidence = true,
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
        assertFalse(decision.disabledCapabilities.canImplementProviderNow)
        assertFalse(isolation.disabledCapabilities.canImplementProviderNow)
        assertFalse(namespace.disabledCapabilities.canImplementProviderNow)
        assertFalse(sourceSet.capabilities.canImplementProviderNow)
        assertFalse(implementationDecision.capabilities.canImplementProviderNow)
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
    fun allPrerequisiteCategoriesAreRepresentedExactlyOnce() {
        val rows = currentEvidence().categoryRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteCategory.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteCategory.entries.toSet(),
            rows.map { it.category }.toSet(),
        )
        assertTrue(rows.all { it.modeled })
        assertTrue(rows.all { it.nonAuthorizing })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allPrerequisiteItemsAreRepresentedExactlyOnce() {
        val rows = currentEvidence().prerequisiteItemRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.entries.toSet(),
            rows.map { it.item }.toSet(),
        )
        assertTrue(rows.all { it.modeled })
        assertTrue(rows.none { it.implementationAuthorization })
        assertTrue(rows.none { it.productionPromotionAuthorization })
    }

    @Test
    fun allRequiredEvidenceClassesAreRepresentedExactlyOnce() {
        val rows = currentEvidence().requiredEvidenceRows

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteRequiredEvidenceClass.entries.size,
            rows.size,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteRequiredEvidenceClass.entries.toSet(),
            rows.map { it.evidenceClass }.toSet(),
        )
        assertTrue(rows.all { it.modeled })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allForbiddenImplementationShortcutsAreRepresentedExactlyOnce() {
        val rows = currentEvidence().forbiddenShortcutRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenShortcut.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenShortcut.entries.toSet(),
            rows.map { it.shortcut }.toSet(),
        )
        assertTrue(rows.all { it.forbidden })
        assertTrue(rows.none { it.currentBridgePresent })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allForbiddenPromotionPathsAreRepresentedExactlyOnce() {
        val rows = currentEvidence().forbiddenPromotionPathRows

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteForbiddenPromotionPath.entries.size,
            rows.size,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteForbiddenPromotionPath.entries.toSet(),
            rows.map { it.promotionRoute }.toSet(),
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
            includeImplementationDecision = false,
        )
        val row = evidence.evidenceSourceRows.single {
            it.evidenceSource ==
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteEvidenceSource
                    .TestOnlyProviderIdentityDecision
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
            includeImplementationDecision = false,
        )
        val row = evidence.evidenceSourceRows.single {
            it.evidenceSource ==
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteEvidenceSource
                    .TestOnlyProviderIdentityIsolationGuard
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
            includeImplementationDecision = false,
        )
        val row = evidence.evidenceSourceRows.single {
            it.evidenceSource ==
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteEvidenceSource.SyntheticIdentityNamespace
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
            includeImplementationDecision = false,
        )
        val row = evidence.evidenceSourceRows.single {
            it.evidenceSource ==
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteEvidenceSource
                    .TestOnlyProviderIdentitySourceSetConfinement
        }

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
        val evidence = evidenceWithOnly(
            includeIdentityDecision = false,
            includeIsolation = false,
            includeNamespace = false,
            includeSourceSet = false,
            includeImplementationDecision = true,
        )
        val row = evidence.evidenceSourceRows.single {
            it.evidenceSource ==
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteEvidenceSource
                    .TestOnlyProviderIdentityImplementationDecision
        }

        assertTrue(evidence.priorImplementationDecisionEvidenceIncluded)
        assertTrue(row.included)
        assertTrue(row.nonAuthorizing)
        assertFalse(row.authorizesImplementation)
        assertFalse(prior.capabilities.implementationDecisionAuthorizesImplementation)
    }

    @Test
    fun absenceProvenPrerequisitesAreNegativeEvidenceOnlyAndDoNotAuthorizeImplementation() {
        val evidence = currentEvidence()
        val absenceRows = evidence.prerequisiteItemRows.filter { it.negativeAbsenceEvidence }

        assertTrue(evidence.negativeAbsenceEvidenceModeled)
        assertFalse(evidence.negativeAbsenceEvidenceAuthorizesImplementation)
        assertTrue(absenceRows.isNotEmpty())
        assertTrue(absenceRows.all { it.satisfiedNow })
        assertTrue(absenceRows.none { it.implementationAuthorization })
        assertTrue(absenceRows.none { it.productionPromotionAuthorization })
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.ProductionSourceAbsenceProven in
                absenceRows.map { it.item },
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.MainnetAbsenceProven in
                absenceRows.map { it.item },
        )
    }

    @Test
    fun currentApprovalReviewPrerequisitesRemainBlockedOrFutureOnly() {
        val rows = currentEvidence().prerequisiteItemRows.associateBy { it.item }

        assertFalse(
            rows.getValue(
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem
                    .ExplicitFutureBranchApprovalRecorded,
            ).satisfiedNow,
        )
        assertFalse(
            rows.getValue(
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.SyntheticSafeIdApprovedForReview,
            ).satisfiedNow,
        )
        assertFalse(
            rows.getValue(
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem
                    .NonProductionSourceSetPlacementApprovedForReview,
            ).satisfiedNow,
        )
        assertFalse(
            rows.getValue(
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem
                    .RedactionAndLeakageReviewComplete,
            ).satisfiedNow,
        )
        assertFalse(
            rows.getValue(
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem
                    .FutureTestOnlyImplementationReviewComplete,
            ).satisfiedNow,
        )
        assertTrue(
            rows.getValue(
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.SourceGuardCoverageComplete,
            ).sourceGuardEvidenceOnly,
        )
        assertFalse(
            rows.getValue(
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.SourceGuardCoverageComplete,
            ).implementationAuthorization,
        )
    }

    @Test
    fun falsePositiveClaimsAddBlockersButDoNotChangeCapabilities() {
        val evidence = SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditPolicy
            .evaluatePrerequisiteAudit(
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditRequest(
                    userConsentOverrideRequested = true,
                    warningOnlyEvidenceClaimed = true,
                    testOnlyEvidenceClaimedAsProductionPromotion = true,
                    releaseEvidenceClaimed = true,
                    futureBranchApprovalClaimed = true,
                    syntheticSafeIdApprovalClaimed = true,
                    sourceSetPlacementApprovalClaimed = true,
                    sourceGuardCoverageClaimed = true,
                    redactionReviewCompleteClaimed = true,
                    implementationReviewCompleteClaimed = true,
                    providerImplementationClaimed = true,
                    registryEntryClaimed = true,
                    factoryReachabilityClaimed = true,
                    dispatcherReachabilityClaimed = true,
                    executorTargetClaimed = true,
                    providerKatExecutorClaimed = true,
                    providerOperationClaimed = true,
                    persistenceReachabilityClaimed = true,
                    settingsReachabilityClaimed = true,
                    uiReachabilityClaimed = true,
                    mainnetReachabilityClaimed = true,
                ),
            )

        assertAllCapabilityBooleansFalse(evidence.capabilities)
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.UserConsentCannotOverride in evidence.blockers)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.WarningOnlyEvidenceNonAuthorizing in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.TestOnlyEvidenceNonAuthorizing in
                evidence.blockers,
        )
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.ReleaseEvidenceNonAuthorizing in evidence.blockers)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.FutureBranchApprovalClaimRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.SyntheticSafeIdApprovalClaimRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.SourceSetPlacementApprovalClaimRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.SourceGuardCoverageClaimRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.RedactionReviewClaimRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.ImplementationReviewCompleteClaimRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.ProviderImplementationClaimRejected in
                evidence.blockers,
        )
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.RegistryEntryClaimRejected in evidence.blockers)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.FactoryReachabilityRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.DispatcherReachabilityRejected in
                evidence.blockers,
        )
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.ExecutorTargetRejected in evidence.blockers)
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.ProviderKatExecutorRejected in evidence.blockers)
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.ProviderOperationRejected in evidence.blockers)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.PersistenceReachabilityRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.SettingsReachabilityRejected in
                evidence.blockers,
        )
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.UiReachabilityRejected in evidence.blockers)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.MainnetReachabilityRejected in
                evidence.blockers,
        )
    }

    @Test
    fun requestStringAndSafeLabelsRemainRedacted() {
        val request = SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditRequest(
            futureBranchApprovalClaimed = true,
            syntheticSafeIdApprovalClaimed = true,
            sourceSetPlacementApprovalClaimed = true,
            sourceGuardCoverageClaimed = true,
            redactionReviewCompleteClaimed = true,
            implementationReviewCompleteClaimed = true,
            providerImplementationClaimed = true,
            mainnetReachabilityClaimed = true,
        ).toString()
        val label =
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditSafeLabel("future-review").toString()

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
        SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditPolicy
            .currentPrerequisiteAuditEvidence()

    private fun evidenceWithOnly(
        includeIdentityDecision: Boolean,
        includeIsolation: Boolean,
        includeNamespace: Boolean,
        includeSourceSet: Boolean,
        includeImplementationDecision: Boolean,
    ) = SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditPolicy.evaluatePrerequisiteAudit(
        SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditRequest(
            includePriorIdentityDecisionEvidence = includeIdentityDecision,
            includePriorIsolationEvidence = includeIsolation,
            includePriorNamespaceEvidence = includeNamespace,
            includePriorSourceSetConfinementEvidence = includeSourceSet,
            includePriorImplementationDecisionEvidence = includeImplementationDecision,
        ),
    )

    private fun assertAllCapabilityBooleansFalse(
        capabilities: SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditCapabilities,
    ) {
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
