package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditChain
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditCheck
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditSourceSet
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditTest {
    @Test
    fun completionAuditIsPresentAndCommonTestOnly() {
        val audit = audit()

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditSourceSet.CommonTest,
            audit.sourceSet,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditChain
                .PayloadFreeSyntheticProviderOperationTrace,
            audit.chainName,
        )
        assertTrue(audit.auditIsCommonTestOnly)
        assertTrue(audit.auditIsAuditOnly)
        assertTrue(audit.completionAuditPassed)
        assertTrue(audit.syntheticTraceCompletionAuditPassed)
    }

    @Test
    fun completionAuditReadsAdmissionTraceValidationSuiteAndPriorBoundaryEvidence() {
        val audit = audit()

        assertTrue(audit.admissionGatePresent)
        assertTrue(audit.syntheticTracePresent)
        assertTrue(audit.syntheticTraceCreated)
        assertTrue(audit.validationReportPresent)
        assertTrue(audit.validationPassed)
        assertTrue(audit.suiteReportPresent)
        assertTrue(audit.suiteReportPassed)
        assertTrue(audit.priorNoopExecutionBoundarySuitePresent)
        assertTrue(audit.chainComplete)
        assertEquals(8, audit.upstreamEvidenceCount)
    }

    @Test
    fun auditTreatsUpstreamPassingStatesOnlyAsCommonTestAuditEvidence() {
        val audit = audit()

        assertTrue(audit.syntheticTraceCreated)
        assertTrue(audit.validationPassed)
        assertTrue(audit.suiteReportPassed)
        assertTrue(audit.syntheticTraceCompletionAuditPassed)
        assertTrue(audit.auditIsCommonTestOnly)
        assertFalse(audit.productionAuthorizationPresent)
        assertFalse(audit.providerSelectionAuthorizationPresent)
        assertFalse(audit.providerOperationAuthorizationPresent)
    }

    @Test
    fun auditAddsNoPayloadsAndCreatesNoSecondTrace() {
        val audit = audit()

        assertTrue(audit.auditPayloadFree)
        assertFalse(audit.auditCreatesTrace)
        assertFalse(audit.auditCreatesTracePayloads)
        assertFalse(audit.tracePayloadPresent)
        assertFalse(audit.providerOperationPayloadPresent)
        assertFalse(audit.secondTraceCreated)
    }

    @Test
    fun auditValidatesNoRawKatPublicVectorProviderHandleLocationOrExportPayloadMaterial() {
        val audit = audit()

        assertFalse(audit.rawKatMaterialPresent)
        assertFalse(audit.vectorBytesPresent)
        assertFalse(audit.vectorHexPresent)
        assertFalse(audit.publicVectorBytesPresent)
        assertFalse(audit.publicVectorHexPresent)
        assertFalse(audit.providerHandlePresent)
        assertFalse(audit.sourceLocationPresent)
        assertFalse(audit.stackTracePresent)
        assertFalse(audit.diagnosticsPayloadPresent)
        assertFalse(audit.analyticsPayloadPresent)
        assertFalse(audit.crashReportPayloadPresent)
        assertFalse(audit.supportExportPayloadPresent)
    }

    @Test
    fun auditValidatesNoProviderOperationCryptoKatRunnerKatExecutorOrProviderImplementation() {
        val audit = audit()

        assertFalse(audit.providerOperationExecuted)
        assertFalse(audit.cryptoExecuted)
        assertFalse(audit.katRunnerPresent)
        assertFalse(audit.katExecutorPresent)
        assertFalse(audit.providerKatExecutorPresent)
        assertFalse(audit.providerImplementationPresent)
    }

    @Test
    fun auditValidatesNoRegistryFactoryDispatcherOrExecutorTargetReachability() {
        val audit = audit()

        assertFalse(audit.registryEntryPresent)
        assertFalse(audit.factoryEntryPresent)
        assertFalse(audit.dispatcherEntryPresent)
        assertFalse(audit.executorTargetPresent)
        assertFalse(audit.providerKatExecutorReachable)
        assertFalse(audit.productionRuntimeReachable)
    }

    @Test
    fun auditValidatesNoVaultLifecyclePersistenceSyncSigningBroadcastingUiEndpointOrMainnetPath() {
        val audit = audit()

        assertFalse(audit.vaultLifecyclePresent)
        assertFalse(audit.vaultPersistencePresent)
        assertFalse(audit.productionSyncPresent)
        assertFalse(audit.signingBroadcastingPresent)
        assertFalse(audit.uiPresent)
        assertFalse(audit.endpointPresent)
        assertFalse(audit.mainnetPresent)
    }

    @Test
    fun productionProviderSelectableFalseAndProviderSelectionResolvesDisabledProviderOnly() {
        val audit = audit()
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertTrue(audit.disabledProviderOnly)
        assertFalse(audit.productionProviderSelectable)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertIs<DisabledVaultCryptoProvider>(selection.selectedProvider)
        assertTrue(selection.selectedProviderIsDisabled)
        assertFalse(selection.productionProviderSelectable)
    }

    @Test
    fun auditPassingIsNotAnyProductionProviderSelectionOperationCryptoKatPersistenceSyncUiEndpointOrMainnetAuthorization() {
        val audit = audit()

        assertTrue(audit.syntheticTraceCompletionAuditPassed)
        assertFalse(audit.productionAuthorizationPresent)
        assertFalse(audit.providerSelectionAuthorizationPresent)
        assertFalse(audit.providerOperationAuthorizationPresent)
        assertFalse(audit.cryptoAuthorizationPresent)
        assertFalse(audit.katRunnerAuthorizationPresent)
        assertFalse(audit.katExecutorAuthorizationPresent)
        assertFalse(audit.providerKatExecutorAuthorizationPresent)
        assertFalse(audit.vaultPersistenceAuthorizationPresent)
        assertFalse(audit.syncAuthorizationPresent)
        assertFalse(audit.signingBroadcastingAuthorizationPresent)
        assertFalse(audit.endpointAuthorizationPresent)
        assertFalse(audit.uiAuthorizationPresent)
        assertFalse(audit.mainnetAuthorizationPresent)
    }

    @Test
    fun auditChecksAreRepresentedExactlyOnceAndNoFailuresBlockersOrWarningsArePresent() {
        val audit = audit()

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditCheck.entries.size,
            audit.auditCheckCount,
        )
        assertEquals(audit.auditCheckCount, audit.auditChecks.size)
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditCheck.entries.forEach { check ->
            assertEquals(1, audit.auditChecks.count { represented -> represented == check })
        }
        assertEquals(0, audit.blockerCount)
        assertEquals(0, audit.warningCount)
        assertTrue(audit.failureLabels.isEmpty())
        assertTrue(audit.blockers.isEmpty())
        assertTrue(audit.warnings.isEmpty())
    }

    @Test
    fun everyAuditAuthorizationReachabilityExecutionAndPayloadBooleanRemainsFalse() {
        val audit = audit()
        val blockedBooleans = listOf(
            audit.auditCreatesTrace,
            audit.auditCreatesTracePayloads,
            audit.productionProviderSelectable,
            audit.productionRuntimeReachable,
            audit.tracePayloadPresent,
            audit.providerOperationPayloadPresent,
            audit.rawKatMaterialPresent,
            audit.vectorBytesPresent,
            audit.vectorHexPresent,
            audit.publicVectorBytesPresent,
            audit.publicVectorHexPresent,
            audit.providerHandlePresent,
            audit.sourceLocationPresent,
            audit.stackTracePresent,
            audit.diagnosticsPayloadPresent,
            audit.analyticsPayloadPresent,
            audit.crashReportPayloadPresent,
            audit.supportExportPayloadPresent,
            audit.secondTraceCreated,
            audit.providerOperationExecuted,
            audit.cryptoExecuted,
            audit.katRunnerPresent,
            audit.katExecutorPresent,
            audit.providerKatExecutorPresent,
            audit.providerImplementationPresent,
            audit.registryEntryPresent,
            audit.factoryEntryPresent,
            audit.dispatcherEntryPresent,
            audit.executorTargetPresent,
            audit.providerKatExecutorReachable,
            audit.vaultLifecyclePresent,
            audit.vaultPersistencePresent,
            audit.productionSyncPresent,
            audit.signingBroadcastingPresent,
            audit.uiPresent,
            audit.endpointPresent,
            audit.mainnetPresent,
            audit.productionAuthorizationPresent,
            audit.providerSelectionAuthorizationPresent,
            audit.providerOperationAuthorizationPresent,
            audit.cryptoAuthorizationPresent,
            audit.katRunnerAuthorizationPresent,
            audit.katExecutorAuthorizationPresent,
            audit.providerKatExecutorAuthorizationPresent,
            audit.vaultPersistenceAuthorizationPresent,
            audit.syncAuthorizationPresent,
            audit.signingBroadcastingAuthorizationPresent,
            audit.uiAuthorizationPresent,
            audit.endpointAuthorizationPresent,
            audit.mainnetAuthorizationPresent,
        )

        assertTrue(blockedBooleans.none { enabled -> enabled })
    }

    @Test
    fun auditOutputIsRedactedAndContainsNoUnsafeMaterialTerms() {
        val audit = audit()
        val outputs = listOf(
            audit.toString(),
            audit.auditId.toString(),
            audit.displayLabel.toString(),
        )
        val rejectedTerms = listOf(
            "skald-test-only",
            "raw KAT material",
            "vector bytes",
            "vector hex",
            "public vector bytes",
            "public vector hex",
            "trace payload",
            "provider-operation payload",
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

    private fun audit() =
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditPolicy
            .currentProviderOperationSyntheticTraceCompletionAudit()
}
