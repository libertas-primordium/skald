package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDecisionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationContractPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGatePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageEvidenceSource
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageForbiddenPromotionPath
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageGuardCapabilities
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageGuardEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageGuardPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageGuardRequest
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageOutcome
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageRequiredEvidenceClass
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageSafeLabel
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageStatus
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageClass
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface
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

class VaultTestOnlyProviderIdentityImplementationRuntimeLinkageGuardTest {
    @Test
    fun currentRuntimeLinkageGuardEvidenceIsModeledAndStillDisabled() {
        val evidence =
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageGuardPolicy
                .currentRuntimeLinkageGuardEvidence()

        assertEquals(
            "skald-vault-v1-test-only-provider-identity-implementation-runtime-linkage-guard-v1",
            evidence.policyId,
        )
        assertEquals(1, evidence.policyVersion)
        assertTrue(evidence.runtimeLinkageGuardModeled)
        assertTrue(evidence.stillDisabled)
        assertTrue(evidence.runtimeSurfacesModeled)
        assertTrue(evidence.forbiddenRuntimeBridgesModeled)
        assertTrue(evidence.forbiddenPromotionPathsModeled)
        assertTrue(evidence.docsMayDescribeFutureReview)
        assertTrue(evidence.testsMayAssertBlockedRuntimeLinkage)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageStatus
                .RuntimeLinkageGuardModeled in evidence.statuses,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageStatus.StillDisabled in
                evidence.statuses,
        )
        assertFalse(evidence.capabilities.runtimeLinkageGuardAuthorizesImplementation)
    }

    @Test
    fun currentRuntimeLinkageOutcomesRemainBlockedDisconnectedAndNonAuthorizing() {
        val outcomes = currentEvidence().outcomes

        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageOutcome
                .CurrentRuntimeLinkageBlocked in outcomes,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageOutcome
                .RuntimeLinkageReviewRequired in outcomes,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageOutcome
                .RuntimeSurfacesDisconnected in outcomes,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageOutcome.ImplementationNotAuthorized in
                outcomes,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageOutcome.ProductionPromotionNotAuthorized in
                outcomes,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageOutcome.MainnetNotAuthorized in
                outcomes,
        )
    }

    @Test
    fun noCurrentTestOnlyOrProductionProviderIdentityImplementationIsPresent() {
        val evidence = currentEvidence()

        assertFalse(evidence.capabilities.testOnlyIdentityImplementationPresent)
        assertFalse(evidence.capabilities.productionIdentityImplementationPresent)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker
                .NoTestOnlyProviderIdentityImplementation in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker
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
        val evidence = SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageGuardPolicy
            .evaluateRuntimeLinkageGuard(
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageGuardRequest(
                    includePriorIdentityDecisionEvidence = true,
                    includePriorIsolationEvidence = true,
                    includePriorNamespaceEvidence = true,
                    includePriorSourceSetConfinementEvidence = true,
                    includePriorImplementationDecisionEvidence = true,
                    includePriorPrerequisiteAuditEvidence = true,
                    includePriorScopeDecisionEvidence = true,
                    includePriorImplementationContractEvidence = true,
                    includePriorReadinessGateEvidence = true,
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
        assertFalse(decision.disabledCapabilities.canImplementProviderNow)
        assertFalse(isolation.disabledCapabilities.canImplementProviderNow)
        assertFalse(namespace.disabledCapabilities.canImplementProviderNow)
        assertFalse(sourceSet.capabilities.canImplementProviderNow)
        assertFalse(implementationDecision.capabilities.implementationDecisionAuthorizesImplementation)
        assertFalse(prerequisiteAudit.capabilities.prerequisiteAuditAuthorizesImplementation)
        assertFalse(scopeDecision.capabilities.scopeDecisionAuthorizesImplementation)
        assertFalse(implementationContract.capabilities.implementationContractAuthorizesImplementation)
        assertFalse(readinessGate.capabilities.readinessGateAuthorizesImplementation)
        assertFalse(evidence.capabilities.canUseAsProviderSelectionId)
        assertFalse(evidence.currentProviderSelectionBridgePresent)
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
    fun allCurrentRuntimeBridgeBooleansRemainFalse() {
        assertAllBridgeBooleansFalse(currentEvidence())
    }

    @Test
    fun allRuntimeSurfacesAreRepresentedExactlyOnce() {
        val rows = currentEvidence().runtimeSurfaceRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface.entries.toSet(),
            rows.map { it.surface }.toSet(),
        )
        assertTrue(rows.all { it.modeled })
        assertTrue(rows.all { it.disconnectedNow })
        assertTrue(rows.none { it.currentBridgePresent })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allRuntimeLinkageClassesAreRepresentedExactlyOnce() {
        val rows = currentEvidence().runtimeLinkageClassRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageClass.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageClass.entries.toSet(),
            rows.map { it.linkageClass }.toSet(),
        )
        assertTrue(rows.all { it.modeled })
        assertTrue(rows.all { it.nonAuthorizing })
        assertTrue(rows.none { it.currentBridgePresent })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allForbiddenRuntimeBridgesAreRepresentedExactlyOnceAndRemainDisconnected() {
        val rows = currentEvidence().forbiddenRuntimeBridgeRows

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge.entries.size, rows.size)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge.entries.toSet(),
            rows.map { it.bridge }.toSet(),
        )
        assertTrue(rows.all { it.forbidden })
        assertTrue(rows.none { it.currentBridgePresent })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allRequiredEvidenceClassesAreRepresentedExactlyOnce() {
        val rows = currentEvidence().requiredEvidenceRows

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageRequiredEvidenceClass.entries.size,
            rows.size,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageRequiredEvidenceClass.entries.toSet(),
            rows.map { it.evidenceClass }.toSet(),
        )
        assertTrue(rows.all { it.modeled })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allForbiddenPromotionPathsAreRepresentedExactlyOnce() {
        val rows = currentEvidence().forbiddenPromotionPathRows

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageForbiddenPromotionPath.entries.size,
            rows.size,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageForbiddenPromotionPath.entries.toSet(),
            rows.map { it.promotionRoute }.toSet(),
        )
        assertTrue(rows.all { it.forbidden })
        assertTrue(rows.none { it.currentBridgePresent })
        assertTrue(rows.none { it.authorizesImplementation })
    }

    @Test
    fun allPriorEvidenceSourcesRemainNonAuthorizingWhenComposed() {
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeIdentityDecision = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageEvidenceSource
                .TestOnlyProviderIdentityDecision,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeIsolation = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageEvidenceSource
                .TestOnlyProviderIdentityIsolationGuard,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeNamespace = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageEvidenceSource.SyntheticIdentityNamespace,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeSourceSet = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageEvidenceSource
                .TestOnlyProviderIdentitySourceSetConfinement,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeImplementationDecision = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageEvidenceSource
                .TestOnlyProviderIdentityImplementationDecision,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includePrerequisiteAudit = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageEvidenceSource
                .TestOnlyProviderIdentityImplementationPrerequisiteAudit,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeScopeDecision = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageEvidenceSource
                .TestOnlyProviderIdentityImplementationScopeDecision,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeImplementationContract = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageEvidenceSource
                .TestOnlyProviderIdentityImplementationContract,
        )
        assertPriorEvidenceNonAuthorizing(
            evidenceWithOnly(includeReadinessGate = true),
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageEvidenceSource
                .TestOnlyProviderIdentityImplementationReadinessGate,
        )
    }

    @Test
    fun falsePositiveClaimsAddBlockersButDoNotChangeCapabilitiesOrBridgeBooleans() {
        val evidence = SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageGuardPolicy
            .evaluateRuntimeLinkageGuard(
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageGuardRequest(
                    userConsentOverrideRequested = true,
                    warningOnlyEvidenceClaimed = true,
                    testOnlyEvidenceClaimedAsProductionPromotion = true,
                    releaseEvidenceClaimed = true,
                    futureBranchApprovalClaimed = true,
                    readinessClaimed = true,
                    runtimeLinkageClaimed = true,
                    runtimeLinkageClaimedAsImplementationAuthorization = true,
                    providerSelectionLinkClaimed = true,
                    registryLinkClaimed = true,
                    factoryLinkClaimed = true,
                    dispatcherLinkClaimed = true,
                    executorTargetLinkClaimed = true,
                    providerKatExecutorLinkClaimed = true,
                    providerOperationLinkClaimed = true,
                    cryptoExecutionLinkClaimed = true,
                    vaultLifecycleLinkClaimed = true,
                    persistenceLinkClaimed = true,
                    secureStorageLinkClaimed = true,
                    secureMetadataLinkClaimed = true,
                    backendClientLinkClaimed = true,
                    bdkWalletStateLinkClaimed = true,
                    settingsCodecLinkClaimed = true,
                    uiSurfaceLinkClaimed = true,
                    signingBroadcastingLinkClaimed = true,
                    torNostrLinkClaimed = true,
                    publicEndpointLinkClaimed = true,
                    mainnetLinkClaimed = true,
                ),
            )

        assertAllCapabilityBooleansFalse(evidence.capabilities)
        assertAllBridgeBooleansFalse(evidence)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.UserConsentCannotOverride in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker
                .WarningOnlyEvidenceNonAuthorizing in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker
                .TestOnlyEvidenceNonAuthorizing in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.ReleaseEvidenceNonAuthorizing in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker
                .FutureBranchApprovalClaimRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.ReadinessClaimRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker
                .RuntimeLinkageClaimRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker
                .RuntimeLinkageAsImplementationAuthorizationRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker
                .ProviderSelectionLinkRejected in evidence.blockers,
        )
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.RegistryLinkRejected in evidence.blockers)
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.FactoryLinkRejected in evidence.blockers)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.DispatcherLinkRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.ExecutorTargetLinkRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker
                .ProviderKatExecutorLinkRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker
                .ProviderOperationLinkRejected in evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.CryptoExecutionLinkRejected in
                evidence.blockers,
        )
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.VaultLifecycleLinkRejected in
                evidence.blockers,
        )
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.PersistenceLinkRejected in evidence.blockers)
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.SecureStorageLinkRejected in evidence.blockers)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.SecureMetadataLinkRejected in
                evidence.blockers,
        )
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.BackendClientLinkRejected in evidence.blockers)
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.BdkWalletStateLinkRejected in evidence.blockers)
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.SettingsCodecLinkRejected in evidence.blockers)
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.UiSurfaceLinkRejected in evidence.blockers)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker
                .SigningBroadcastingLinkRejected in evidence.blockers,
        )
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.TorNostrLinkRejected in evidence.blockers)
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.PublicEndpointLinkRejected in evidence.blockers)
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.MainnetLinkRejected in evidence.blockers)
    }

    @Test
    fun requestStringAndSafeLabelsRemainRedacted() {
        val request = SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageGuardRequest(
            futureBranchApprovalClaimed = true,
            readinessClaimed = true,
            runtimeLinkageClaimed = true,
            providerSelectionLinkClaimed = true,
            cryptoExecutionLinkClaimed = true,
            mainnetLinkClaimed = true,
        ).toString()
        val label =
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageSafeLabel(
                "runtime-linkage-future-review",
            ).toString()

        val forbiddenTerms = listOf(
            "runtime-linkage-future-review",
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
        )
        forbiddenTerms.forEach { term ->
            assertFalse(request.contains(term, ignoreCase = true), "request leaked $term")
            assertFalse(label.contains(term, ignoreCase = true), "label leaked $term")
        }
    }

    private fun currentEvidence() =
        SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageGuardPolicy
            .currentRuntimeLinkageGuardEvidence()

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
    ) = SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageGuardPolicy.evaluateRuntimeLinkageGuard(
        SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageGuardRequest(
            includePriorIdentityDecisionEvidence = includeIdentityDecision,
            includePriorIsolationEvidence = includeIsolation,
            includePriorNamespaceEvidence = includeNamespace,
            includePriorSourceSetConfinementEvidence = includeSourceSet,
            includePriorImplementationDecisionEvidence = includeImplementationDecision,
            includePriorPrerequisiteAuditEvidence = includePrerequisiteAudit,
            includePriorScopeDecisionEvidence = includeScopeDecision,
            includePriorImplementationContractEvidence = includeImplementationContract,
            includePriorReadinessGateEvidence = includeReadinessGate,
        ),
    )

    private fun assertPriorEvidenceNonAuthorizing(
        evidence: SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageGuardEvidence,
        source: SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageEvidenceSource,
    ) {
        val row = evidence.evidenceSourceRows.single { it.evidenceSource == source }

        assertTrue(row.included)
        assertTrue(row.modeled)
        assertTrue(row.nonAuthorizing)
        assertFalse(row.authorizesImplementation)
        assertFalse(row.authorizesProductionPromotion)
        assertAllCapabilityBooleansFalse(evidence.capabilities)
        assertAllBridgeBooleansFalse(evidence)
    }

    private fun assertAllCapabilityBooleansFalse(
        capabilities: SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageGuardCapabilities,
    ) {
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

    private fun assertAllBridgeBooleansFalse(
        evidence: SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageGuardEvidence,
    ) {
        assertFalse(evidence.currentProviderSelectionBridgePresent)
        assertFalse(evidence.currentRegistryBridgePresent)
        assertFalse(evidence.currentFactoryBridgePresent)
        assertFalse(evidence.currentDispatcherBridgePresent)
        assertFalse(evidence.currentExecutorTargetBridgePresent)
        assertFalse(evidence.currentProviderKatExecutorBridgePresent)
        assertFalse(evidence.currentProviderOperationBridgePresent)
        assertFalse(evidence.currentCryptoExecutionBridgePresent)
        assertFalse(evidence.currentVaultLifecycleBridgePresent)
        assertFalse(evidence.currentPersistenceBridgePresent)
        assertFalse(evidence.currentSecureStorageBridgePresent)
        assertFalse(evidence.currentSecureMetadataBridgePresent)
        assertFalse(evidence.currentBackendClientBridgePresent)
        assertFalse(evidence.currentBdkWalletStateBridgePresent)
        assertFalse(evidence.currentSettingsCodecBridgePresent)
        assertFalse(evidence.currentUiSurfaceBridgePresent)
        assertFalse(evidence.currentSigningBroadcastingBridgePresent)
        assertFalse(evidence.currentTorNostrBridgePresent)
        assertFalse(evidence.currentPublicEndpointBridgePresent)
        assertFalse(evidence.currentMainnetBridgePresent)
    }
}
