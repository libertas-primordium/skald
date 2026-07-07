package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGateChain
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGateCheck
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGatePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGateSourceSet
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityImplementationTransitionGateTest {
    @Test
    fun transitionGateIsCommonTestOnlyAndTransitionGateOnly() {
        val gate = gate()

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGateSourceSet.CommonTest,
            gate.sourceSet,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGateChain
                .TestOnlyProviderIdentityImplementationPrerequisiteChain,
            gate.chainName,
        )
        assertTrue(gate.transitionGateIsCommonTestOnly)
        assertTrue(gate.transitionGateOnly)
    }

    @Test
    fun transitionGateReadsPriorIdentityDecisionIsolationAndSourceSetEvidence() {
        val gate = gate()

        assertTrue(gate.identityDecisionPresent)
        assertTrue(gate.isolationGuardPresent)
        assertTrue(gate.syntheticNamespacePresent)
        assertTrue(gate.sourceSetConfinementPresent)
    }

    @Test
    fun transitionGateReadsImplementationDecisionPrerequisiteScopeContractReadinessAndPlanEvidence() {
        val gate = gate()

        assertTrue(gate.implementationDecisionPresent)
        assertTrue(gate.prerequisiteAuditPresent)
        assertTrue(gate.scopeDecisionPresent)
        assertTrue(gate.implementationContractPresent)
        assertTrue(gate.readinessGatePresent)
        assertTrue(gate.runtimeLinkageGuardPresent)
        assertTrue(gate.promotionBlockersPresent)
        assertTrue(gate.implementationAdmissionGatePresent)
        assertTrue(gate.implementationPlanPresent)
    }

    @Test
    fun transitionGateReadsKatFixtureProviderOperationAndSyntheticTraceEvidence() {
        val gate = gate()

        assertTrue(gate.katFixtureChainPresent)
        assertTrue(gate.providerOperationMetadataChainPresent)
        assertTrue(gate.providerOperationNoopKatChainPresent)
        assertTrue(gate.providerOperationNoopExecutionBoundaryChainPresent)
        assertTrue(gate.providerOperationSyntheticTraceChainPresent)
        assertTrue(gate.syntheticTraceCompletionAuditPresent)
        assertTrue(gate.syntheticTraceCompletionAuditPassed)
        assertTrue(gate.sourceGuardCoveragePresent)
        assertTrue(gate.productionRuntimeAbsenceProven)
    }

    @Test
    fun transitionGateMarksHumanReviewReadyWithoutAuthorizingImplementation() {
        val gate = gate()

        assertTrue(gate.reviewReadyForHumanDecision)
        assertTrue(gate.reviewReadyIsHumanDecisionOnly)
        assertFalse(gate.implementationAuthorized)
        assertFalse(gate.providerImplementationAuthorized)
        assertFalse(gate.providerOperationExecutionAuthorized)
        assertFalse(gate.cryptoExecutionAuthorized)
        assertFalse(gate.katExecutorAuthorized)
        assertFalse(gate.mainnetAuthorized)
    }

    @Test
    fun transitionGatePassingIsNotProductionProviderSelectionOperationCryptoKatPersistenceSyncUiEndpointOrMainnetAuthorization() {
        val gate = gate()

        assertFalse(gate.productionAuthorizationPresent)
        assertFalse(gate.providerSelectionAuthorizationPresent)
        assertFalse(gate.providerOperationAuthorizationPresent)
        assertFalse(gate.cryptoAuthorizationPresent)
        assertFalse(gate.katRunnerAuthorizationPresent)
        assertFalse(gate.katExecutorAuthorizationPresent)
        assertFalse(gate.providerKatExecutorAuthorizationPresent)
        assertFalse(gate.vaultPersistenceAuthorizationPresent)
        assertFalse(gate.syncAuthorizationPresent)
        assertFalse(gate.signingBroadcastingAuthorizationPresent)
        assertFalse(gate.uiAuthorizationPresent)
        assertFalse(gate.endpointAuthorizationPresent)
        assertFalse(gate.mainnetAuthorizationPresent)
    }

    @Test
    fun productionProviderSelectableFalseAndProviderSelectionRemainsDisabledOnly() {
        val gate = gate()

        assertTrue(gate.disabledProviderOnly)
        assertFalse(gate.productionProviderSelectable)
    }

    @Test
    fun transitionGateAddsNoProviderImplementationRegistryFactoryDispatcherExecutorTargetOrExecution() {
        val gate = gate()

        assertFalse(gate.providerRegistryEntryPresent)
        assertFalse(gate.providerFactoryPresent)
        assertFalse(gate.providerDispatcherPresent)
        assertFalse(gate.executorTargetPresent)
        assertFalse(gate.providerImplementationPresent)
        assertFalse(gate.providerOperationExecuted)
        assertFalse(gate.cryptoExecuted)
        assertFalse(gate.katRunnerPresent)
        assertFalse(gate.katExecutorPresent)
        assertFalse(gate.providerKatExecutorPresent)
    }

    @Test
    fun transitionGateAddsNoRawMaterialTracePayloadVaultSyncUiEndpointOrMainnetPath() {
        val gate = gate()

        assertFalse(gate.tracePayloadPresent)
        assertFalse(gate.rawKatMaterialPresent)
        assertFalse(gate.publicVectorBytesPresent)
        assertFalse(gate.publicVectorHexPresent)
        assertFalse(gate.vaultLifecyclePresent)
        assertFalse(gate.vaultPersistencePresent)
        assertFalse(gate.productionSyncPresent)
        assertFalse(gate.signingBroadcastingPresent)
        assertFalse(gate.uiPresent)
        assertFalse(gate.endpointPresent)
        assertFalse(gate.mainnetPresent)
    }

    @Test
    fun everyAuthorizationReachabilityExecutionAndPayloadBooleanRemainsFalse() {
        val gate = gate()
        val blockedBooleans = listOf(
            gate.implementationAuthorized,
            gate.providerImplementationAuthorized,
            gate.providerOperationExecutionAuthorized,
            gate.cryptoExecutionAuthorized,
            gate.katExecutorAuthorized,
            gate.mainnetAuthorized,
            gate.productionAuthorizationPresent,
            gate.providerSelectionAuthorizationPresent,
            gate.providerOperationAuthorizationPresent,
            gate.cryptoAuthorizationPresent,
            gate.katRunnerAuthorizationPresent,
            gate.katExecutorAuthorizationPresent,
            gate.providerKatExecutorAuthorizationPresent,
            gate.vaultPersistenceAuthorizationPresent,
            gate.syncAuthorizationPresent,
            gate.signingBroadcastingAuthorizationPresent,
            gate.uiAuthorizationPresent,
            gate.endpointAuthorizationPresent,
            gate.mainnetAuthorizationPresent,
            gate.productionProviderSelectable,
            gate.providerRegistryEntryPresent,
            gate.providerFactoryPresent,
            gate.providerDispatcherPresent,
            gate.executorTargetPresent,
            gate.providerImplementationPresent,
            gate.providerOperationExecuted,
            gate.cryptoExecuted,
            gate.katRunnerPresent,
            gate.katExecutorPresent,
            gate.providerKatExecutorPresent,
            gate.tracePayloadPresent,
            gate.rawKatMaterialPresent,
            gate.publicVectorBytesPresent,
            gate.publicVectorHexPresent,
            gate.vaultLifecyclePresent,
            gate.vaultPersistencePresent,
            gate.productionSyncPresent,
            gate.signingBroadcastingPresent,
            gate.uiPresent,
            gate.endpointPresent,
            gate.mainnetPresent,
        )

        assertTrue(blockedBooleans.none { enabled -> enabled })
    }

    @Test
    fun transitionGateChecksAreRepresentedExactlyOnceAndNoFailuresWarningsOrBlockersArePresent() {
        val gate = gate()

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGateCheck.entries.size,
            gate.transitionCheckCount,
        )
        assertEquals(gate.transitionCheckCount, gate.transitionChecks.size)
        SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGateCheck.entries.forEach { check ->
            assertEquals(1, gate.transitionChecks.count { represented -> represented == check })
        }
        assertTrue(gate.failureLabels.isEmpty())
        assertEquals(0, gate.blockerCount)
        assertEquals(0, gate.warningCount)
        assertTrue(gate.blockers.isEmpty())
        assertTrue(gate.warnings.isEmpty())
    }

    @Test
    fun sourceMaterialCorpusRulesRemainRepresentedByTheTransitionGate() {
        val gate = gate()

        assertTrue(gate.sourceMaterialCorpusRulesPresent)
        assertTrue(gate.buildHistoryExcludedFromNormalSourceMaterialCorpus)
        assertTrue(gate.docsReadmeSeparateCorpus)
    }

    @Test
    fun transitionGateOutputIsRedactedAndContainsNoUnsafeMaterialTerms() {
        val gate = gate()
        val outputs = listOf(
            gate.toString(),
            gate.gateId.toString(),
            gate.displayLabel.toString(),
        )
        val rejectedTerms = listOf(
            "skald-test-only",
            "raw KAT material",
            "vector bytes",
            "vector hex",
            "public vector bytes",
            "public vector hex",
            "trace payload",
            "secret",
            "provider handle",
            "source location",
            "stack trace",
            "diagnostic payload",
            "analytics payload",
            "crash report",
            "support export",
            "endpoint value",
            "filesystem path",
            "txid",
            "descriptor",
            "address",
            "PSBT",
            "transaction hex",
            "Nostr nsec",
            "Lightning credential",
            "Cashu proof",
            "backend credential",
        )

        outputs.forEach { output ->
            rejectedTerms.forEach { term ->
                assertFalse(output.contains(term, ignoreCase = true), "safe output leaked $term")
            }
        }
    }

    private fun gate() =
        SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGatePolicy
            .currentProviderIdentityImplementationTransitionGate()
}
