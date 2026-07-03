package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDecisionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationAllowedFutureScopeItem
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategory
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionCapabilities
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionRequest
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionSafeLabel
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionStatus
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationScopeEvidenceSource
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationScopeForbiddenPromotionPath
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationScopeForbiddenShortcut
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationScopeOutcome
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationScopeRequiredEvidenceClass
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

class VaultTestOnlyProviderIdentityImplementationScopeDecisionTest {
    @Test
    fun currentScopeDecisionEvidenceIsModeledAndStillDisabled() {
        val evidence =
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionPolicy.currentScopeDecisionEvidence()

        assertEquals(
            "skald-vault-v1-test-only-provider-identity-implementation-scope-decision-v1",
            evidence.policyId,
        )
        assertEquals(1, evidence.policyVersion)
        assertTrue(evidence.scopeDecisionModeled)
        assertTrue(evidence.stillDisabled)
        assertTrue(evidence.allowedFutureScopeItemsModeled)
        assertTrue(evidence.forbiddenScopeItemsModeled)
        assertTrue(evidence.docsMayDescribeFutureReview)
        assertTrue(evidence.testsMayAssertBlockedScope)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionStatus.ScopeDecisionModeled in
                evidence.statuses,
        )
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionStatus.StillDisabled in evidence.statuses)
        assertFalse(evidence.capabilities.scopeDecisionAuthorizesImplementation)
    }

    @Test
    fun currentScopeOutcomesRemainBlockedReviewRequiredAndNonAuthorizing() {
        val outcomes = currentEvidence().outcomes

        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationScopeOutcome.CurrentScopeBlocked in outcomes)
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationScopeOutcome.ScopeReviewRequired in outcomes)
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationScopeOutcome.ImplementationNotAuthorized in outcomes)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeOutcome.ProductionPromotionNotAuthorized in
                outcomes,
        )
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationScopeOutcome.MainnetNotAuthorized in outcomes)
    }

    @Test
    fun noCurrentTestOnlyOrProductionProviderIdentityImplementationIsPresent() {
        val evidence = currentEvidence()

        assertFalse(evidence.capabilities.testOnlyIdentityImplementationPresent)
        assertFalse(evidence.capabilities.productionIdentityImplementationPresent)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.NoTestOnlyProviderIdentityImplementation in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.NoProductionProviderIdentityImplementation in
                evidence.blockers,
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
        val evidence = SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionPolicy
            .evaluateScopeDecision(
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionRequest(
                    includePriorIdentityDecisionEvidence = true,
                    includePriorIsolationEvidence = true,
                    includePriorNamespaceEvidence = true,
                    includePriorSourceSetConfinementEvidence = true,
                    includePriorImplementationDecisionEvidence = true,
                    includePriorPrerequisiteAuditEvidence = true,
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
        assertFalse(decision.disabledCapabilities.canImplementProviderNow)
        assertFalse(isolation.disabledCapabilities.canImplementProviderNow)
        assertFalse(namespace.disabledCapabilities.canImplementProviderNow)
        assertFalse(sourceSet.capabilities.canImplementProviderNow)
        assertFalse(implementationDecision.capabilities.implementationDecisionAuthorizesImplementation)
        assertFalse(prerequisiteAudit.capabilities.prerequisiteAuditAuthorizesImplementation)
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
    fun allScopeCategoriesAreRepresentedExactlyOnce() {
        val rows = currentEvidence().categoryRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategory.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategory.entries.toSet(),
            rows.map { it.category }.toSet(),
        )
        assertTrue(rows.all { it.modeled })
        assertTrue(rows.all { it.nonAuthorizing })
        assertTrue(rows.none { it.currentImplementationAllowed })
    }

    @Test
    fun allAllowedFutureScopeItemsAreRepresentedExactlyOnceAndRemainNonAuthorizing() {
        val rows = currentEvidence().allowedFutureScopeItemRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationAllowedFutureScopeItem.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationAllowedFutureScopeItem.entries.toSet(),
            rows.map { it.item }.toSet(),
        )
        assertTrue(rows.all { it.modeled })
        assertTrue(rows.all { it.allowedForFutureReview })
        assertTrue(rows.all { it.nonAuthorizing })
        assertTrue(rows.none { it.currentAuthorization })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allForbiddenScopeItemsAreRepresentedExactlyOnce() {
        val rows = currentEvidence().forbiddenScopeItemRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem.entries.toSet(),
            rows.map { it.item }.toSet(),
        )
        assertTrue(rows.all { it.modeled })
        assertTrue(rows.all { it.forbiddenNow })
        assertTrue(rows.none { it.currentBridgePresent })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allRequiredEvidenceClassesAreRepresentedExactlyOnce() {
        val rows = currentEvidence().requiredEvidenceRows

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeRequiredEvidenceClass.entries.size,
            rows.size,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeRequiredEvidenceClass.entries.toSet(),
            rows.map { it.evidenceClass }.toSet(),
        )
        assertTrue(rows.all { it.modeled })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allForbiddenScopeShortcutsAreRepresentedExactlyOnce() {
        val rows = currentEvidence().forbiddenShortcutRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationScopeForbiddenShortcut.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeForbiddenShortcut.entries.toSet(),
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
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeForbiddenPromotionPath.entries.size,
            rows.size,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeForbiddenPromotionPath.entries.toSet(),
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
        val row = evidence.rowFor(SkaldVaultV1TestOnlyProviderIdentityImplementationScopeEvidenceSource.TestOnlyProviderIdentityDecision)

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
        val row =
            evidence.rowFor(SkaldVaultV1TestOnlyProviderIdentityImplementationScopeEvidenceSource.TestOnlyProviderIdentityIsolationGuard)

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
        val row =
            evidence.rowFor(SkaldVaultV1TestOnlyProviderIdentityImplementationScopeEvidenceSource.SyntheticIdentityNamespace)

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
        val row =
            evidence.rowFor(
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeEvidenceSource
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
        val row =
            evidence.rowFor(
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeEvidenceSource
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
        val row =
            evidence.rowFor(
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeEvidenceSource
                    .TestOnlyProviderIdentityImplementationPrerequisiteAudit,
            )

        assertTrue(evidence.priorPrerequisiteAuditEvidenceIncluded)
        assertTrue(row.included)
        assertTrue(row.nonAuthorizing)
        assertFalse(row.authorizesImplementation)
        assertFalse(prior.capabilities.prerequisiteAuditAuthorizesImplementation)
    }

    @Test
    fun falsePositiveClaimsAddBlockersButDoNotChangeCapabilities() {
        val evidence = SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionPolicy
            .evaluateScopeDecision(
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionRequest(
                    userConsentOverrideRequested = true,
                    warningOnlyEvidenceClaimed = true,
                    testOnlyEvidenceClaimedAsProductionPromotion = true,
                    releaseEvidenceClaimed = true,
                    futureBranchApprovalClaimed = true,
                    prerequisiteCompletionClaimed = true,
                    implementationScopeApprovalClaimed = true,
                    allowedFutureScopeClaimedAsCurrentAuthorization = true,
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
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.UserConsentCannotOverride in evidence.blockers)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.WarningOnlyEvidenceNonAuthorizing in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.TestOnlyEvidenceNonAuthorizing in
                evidence.blockers,
        )
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.ReleaseEvidenceNonAuthorizing in evidence.blockers)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.FutureBranchApprovalClaimRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.PrerequisiteCompletionClaimRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.ImplementationScopeApprovalClaimRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker
                .AllowedFutureScopeAsCurrentAuthorizationRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.ProviderImplementationClaimRejected in
                evidence.blockers,
        )
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.RegistryEntryClaimRejected in evidence.blockers)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.FactoryReachabilityRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.DispatcherReachabilityRejected in
                evidence.blockers,
        )
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.ExecutorTargetRejected in evidence.blockers)
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.ProviderKatExecutorRejected in evidence.blockers)
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.ProviderOperationRejected in evidence.blockers)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.PersistenceReachabilityRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.SettingsReachabilityRejected in
                evidence.blockers,
        )
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.UiReachabilityRejected in evidence.blockers)
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.MainnetReachabilityRejected in evidence.blockers)
    }

    @Test
    fun requestStringAndSafeLabelsRemainRedacted() {
        val request = SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionRequest(
            futureBranchApprovalClaimed = true,
            prerequisiteCompletionClaimed = true,
            implementationScopeApprovalClaimed = true,
            allowedFutureScopeClaimedAsCurrentAuthorization = true,
            providerImplementationClaimed = true,
            mainnetReachabilityClaimed = true,
        ).toString()
        val label =
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionSafeLabel("scope-future-review").toString()

        val forbiddenTerms = listOf(
            "scope-future-review",
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
        )
        forbiddenTerms.forEach { term ->
            assertFalse(request.contains(term, ignoreCase = true), "request leaked $term")
            assertFalse(label.contains(term, ignoreCase = true), "label leaked $term")
        }
    }

    private fun currentEvidence() =
        SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionPolicy.currentScopeDecisionEvidence()

    private fun evidenceWithOnly(
        includeIdentityDecision: Boolean = false,
        includeIsolation: Boolean = false,
        includeNamespace: Boolean = false,
        includeSourceSet: Boolean = false,
        includeImplementationDecision: Boolean = false,
        includePrerequisiteAudit: Boolean = false,
    ) = SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionPolicy.evaluateScopeDecision(
        SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionRequest(
            includePriorIdentityDecisionEvidence = includeIdentityDecision,
            includePriorIsolationEvidence = includeIsolation,
            includePriorNamespaceEvidence = includeNamespace,
            includePriorSourceSetConfinementEvidence = includeSourceSet,
            includePriorImplementationDecisionEvidence = includeImplementationDecision,
            includePriorPrerequisiteAuditEvidence = includePrerequisiteAudit,
        ),
    )

    private fun SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionEvidence.rowFor(
        source: SkaldVaultV1TestOnlyProviderIdentityImplementationScopeEvidenceSource,
    ) = evidenceSourceRows.single { it.evidenceSource == source }

    private fun assertAllCapabilityBooleansFalse(
        capabilities: SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionCapabilities,
    ) {
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
