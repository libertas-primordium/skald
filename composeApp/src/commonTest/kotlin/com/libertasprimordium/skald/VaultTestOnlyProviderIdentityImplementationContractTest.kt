package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDecisionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationContractCapabilities
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationContractEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationContractEvidenceSource
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationContractForbiddenPromotionPath
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationContractOutcome
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationContractPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequest
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequiredEvidenceClass
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationContractSafeLabel
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationContractStatus
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenClause
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditPolicy
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

class VaultTestOnlyProviderIdentityImplementationContractTest {
    @Test
    fun currentImplementationContractEvidenceIsModeledAndStillDisabled() {
        val evidence =
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractPolicy.currentImplementationContractEvidence()

        assertEquals(
            "skald-vault-v1-test-only-provider-identity-implementation-contract-v1",
            evidence.policyId,
        )
        assertEquals(1, evidence.policyVersion)
        assertTrue(evidence.implementationContractModeled)
        assertTrue(evidence.stillDisabled)
        assertTrue(evidence.contractRequirementsModeled)
        assertTrue(evidence.forbiddenImplementationClausesModeled)
        assertTrue(evidence.forbiddenRuntimeLinkagesModeled)
        assertTrue(evidence.docsMayDescribeFutureReview)
        assertTrue(evidence.testsMayAssertBlockedContract)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractStatus.ImplementationContractModeled in
                evidence.statuses,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractStatus.StillDisabled in evidence.statuses,
        )
        assertFalse(evidence.capabilities.implementationContractAuthorizesImplementation)
    }

    @Test
    fun currentContractOutcomesRemainBlockedReviewRequiredAndNonAuthorizing() {
        val outcomes = currentEvidence().outcomes

        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationContractOutcome.CurrentContractBlocked in outcomes)
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationContractOutcome.ContractReviewRequired in outcomes)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractOutcome.ImplementationNotAuthorized in
                outcomes,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractOutcome.ProductionPromotionNotAuthorized in
                outcomes,
        )
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationContractOutcome.MainnetNotAuthorized in outcomes)
    }

    @Test
    fun noCurrentTestOnlyOrProductionProviderIdentityImplementationIsPresent() {
        val evidence = currentEvidence()

        assertFalse(evidence.capabilities.testOnlyIdentityImplementationPresent)
        assertFalse(evidence.capabilities.productionIdentityImplementationPresent)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.NoTestOnlyProviderIdentityImplementation in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.NoProductionProviderIdentityImplementation in
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
        val scopeDecision =
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionPolicy.currentScopeDecisionEvidence()
        val evidence = SkaldVaultV1TestOnlyProviderIdentityImplementationContractPolicy
            .evaluateImplementationContract(
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequest(
                    includePriorIdentityDecisionEvidence = true,
                    includePriorIsolationEvidence = true,
                    includePriorNamespaceEvidence = true,
                    includePriorSourceSetConfinementEvidence = true,
                    includePriorImplementationDecisionEvidence = true,
                    includePriorPrerequisiteAuditEvidence = true,
                    includePriorScopeDecisionEvidence = true,
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
        assertFalse(decision.disabledCapabilities.canImplementProviderNow)
        assertFalse(isolation.disabledCapabilities.canImplementProviderNow)
        assertFalse(namespace.disabledCapabilities.canImplementProviderNow)
        assertFalse(sourceSet.capabilities.canImplementProviderNow)
        assertFalse(implementationDecision.capabilities.implementationDecisionAuthorizesImplementation)
        assertFalse(prerequisiteAudit.capabilities.prerequisiteAuditAuthorizesImplementation)
        assertFalse(scopeDecision.capabilities.scopeDecisionAuthorizesImplementation)
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
    fun allContractSectionsAreRepresentedExactlyOnce() {
        val rows = currentEvidence().sectionRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.entries.toSet(),
            rows.map { it.section }.toSet(),
        )
        assertTrue(rows.all { it.modeled })
        assertTrue(rows.all { it.nonAuthorizing })
        assertTrue(rows.none { it.currentImplementationAllowed })
    }

    @Test
    fun allContractRequirementsAreRepresentedExactlyOnceAndRemainNonAuthorizing() {
        val rows = currentEvidence().requirementRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.entries.toSet(),
            rows.map { it.requirement }.toSet(),
        )
        assertTrue(rows.all { it.modeled })
        assertTrue(rows.all { it.nonAuthorizing })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allForbiddenImplementationClausesAreRepresentedExactlyOnce() {
        val rows = currentEvidence().forbiddenImplementationClauseRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenClause.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenClause.entries.toSet(),
            rows.map { it.clause }.toSet(),
        )
        assertTrue(rows.all { it.forbidden })
        assertTrue(rows.none { it.currentBridgePresent })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allForbiddenRuntimeLinkagesAreRepresentedExactlyOnce() {
        val rows = currentEvidence().forbiddenRuntimeLinkageRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage.entries.toSet(),
            rows.map { it.runtimeLinkage }.toSet(),
        )
        assertTrue(rows.all { it.forbidden })
        assertTrue(rows.none { it.currentBridgePresent })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allRequiredEvidenceClassesAreRepresentedExactlyOnce() {
        val rows = currentEvidence().requiredEvidenceRows

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequiredEvidenceClass.entries.size,
            rows.size,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequiredEvidenceClass.entries.toSet(),
            rows.map { it.evidenceClass }.toSet(),
        )
        assertTrue(rows.all { it.modeled })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allForbiddenPromotionPathsAreRepresentedExactlyOnce() {
        val rows = currentEvidence().forbiddenPromotionPathRows

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractForbiddenPromotionPath.entries.size,
            rows.size,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractForbiddenPromotionPath.entries.toSet(),
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
        val row =
            evidence.rowFor(
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractEvidenceSource
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
        val row =
            evidence.rowFor(
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractEvidenceSource
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
        val row =
            evidence.rowFor(
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractEvidenceSource.SyntheticIdentityNamespace,
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
        val row =
            evidence.rowFor(
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractEvidenceSource
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
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractEvidenceSource
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
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractEvidenceSource
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
        val row =
            evidence.rowFor(
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractEvidenceSource
                    .TestOnlyProviderIdentityImplementationScopeDecision,
            )

        assertTrue(evidence.priorScopeDecisionEvidenceIncluded)
        assertTrue(row.included)
        assertTrue(row.nonAuthorizing)
        assertFalse(row.authorizesImplementation)
        assertFalse(prior.capabilities.scopeDecisionAuthorizesImplementation)
    }

    @Test
    fun falsePositiveClaimsAddBlockersButDoNotChangeCapabilities() {
        val evidence = SkaldVaultV1TestOnlyProviderIdentityImplementationContractPolicy
            .evaluateImplementationContract(
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequest(
                    userConsentOverrideRequested = true,
                    warningOnlyEvidenceClaimed = true,
                    testOnlyEvidenceClaimedAsProductionPromotion = true,
                    releaseEvidenceClaimed = true,
                    futureBranchApprovalClaimed = true,
                    prerequisiteCompletionClaimed = true,
                    scopeApprovalClaimed = true,
                    contractSatisfactionClaimed = true,
                    contractClaimedAsImplementationAuthorization = true,
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
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.UserConsentCannotOverride in evidence.blockers)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.WarningOnlyEvidenceNonAuthorizing in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.TestOnlyEvidenceNonAuthorizing in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.ReleaseEvidenceNonAuthorizing in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.FutureBranchApprovalClaimRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.PrerequisiteCompletionClaimRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.ScopeApprovalClaimRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.ContractSatisfactionClaimRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker
                .ContractAsImplementationAuthorizationRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.ProviderImplementationClaimRejected in
                evidence.blockers,
        )
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.RegistryEntryClaimRejected in evidence.blockers)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.FactoryReachabilityRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.DispatcherReachabilityRejected in
                evidence.blockers,
        )
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.ExecutorTargetRejected in evidence.blockers)
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.ProviderKatExecutorRejected in evidence.blockers)
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.ProviderOperationRejected in evidence.blockers)
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.CryptoExecutionRejected in evidence.blockers)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.PersistenceReachabilityRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.SettingsReachabilityRejected in
                evidence.blockers,
        )
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.UiReachabilityRejected in evidence.blockers)
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.MainnetReachabilityRejected in evidence.blockers)
    }

    @Test
    fun requestStringAndSafeLabelsRemainRedacted() {
        val request = SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequest(
            futureBranchApprovalClaimed = true,
            prerequisiteCompletionClaimed = true,
            scopeApprovalClaimed = true,
            contractSatisfactionClaimed = true,
            providerImplementationClaimed = true,
            cryptoExecutionClaimed = true,
            mainnetReachabilityClaimed = true,
        ).toString()
        val label =
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractSafeLabel("contract-future-review").toString()

        val forbiddenTerms = listOf(
            "contract-future-review",
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
        )
        forbiddenTerms.forEach { term ->
            assertFalse(request.contains(term, ignoreCase = true), "request leaked $term")
            assertFalse(label.contains(term, ignoreCase = true), "label leaked $term")
        }
    }

    private fun currentEvidence() =
        SkaldVaultV1TestOnlyProviderIdentityImplementationContractPolicy.currentImplementationContractEvidence()

    private fun evidenceWithOnly(
        includeIdentityDecision: Boolean = false,
        includeIsolation: Boolean = false,
        includeNamespace: Boolean = false,
        includeSourceSet: Boolean = false,
        includeImplementationDecision: Boolean = false,
        includePrerequisiteAudit: Boolean = false,
        includeScopeDecision: Boolean = false,
    ) = SkaldVaultV1TestOnlyProviderIdentityImplementationContractPolicy.evaluateImplementationContract(
        SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequest(
            includePriorIdentityDecisionEvidence = includeIdentityDecision,
            includePriorIsolationEvidence = includeIsolation,
            includePriorNamespaceEvidence = includeNamespace,
            includePriorSourceSetConfinementEvidence = includeSourceSet,
            includePriorImplementationDecisionEvidence = includeImplementationDecision,
            includePriorPrerequisiteAuditEvidence = includePrerequisiteAudit,
            includePriorScopeDecisionEvidence = includeScopeDecision,
        ),
    )

    private fun SkaldVaultV1TestOnlyProviderIdentityImplementationContractEvidence.rowFor(
        source: SkaldVaultV1TestOnlyProviderIdentityImplementationContractEvidenceSource,
    ) = evidenceSourceRows.single { it.evidenceSource == source }

    private fun assertAllCapabilityBooleansFalse(
        capabilities: SkaldVaultV1TestOnlyProviderIdentityImplementationContractCapabilities,
    ) {
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
