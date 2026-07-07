package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportCheck
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportChain
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportSourceSet
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportTest {
    @Test
    fun suiteReportIsPresentAndCommonTestOnly() {
        val report = report()

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportSourceSet.CommonTest,
            report.sourceSet,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportChain
                .PayloadFreeSyntheticProviderOperationTrace,
            report.chainName,
        )
        assertTrue(report.suiteIsCommonTestOnly)
        assertTrue(report.suiteIsSuiteReportOnly)
        assertTrue(report.suitePassed)
        assertTrue(report.syntheticTraceSuitePassed)
    }

    @Test
    fun suiteReportReadsAdmissionTraceValidationAndPriorNoopExecutionBoundarySuite() {
        val report = report()

        assertTrue(report.admissionGatePresent)
        assertTrue(report.syntheticTracePresent)
        assertTrue(report.syntheticTraceValidationPresent)
        assertTrue(report.syntheticTraceValidationPassed)
        assertTrue(report.priorNoopExecutionBoundarySuitePresent)
        assertEquals(6, report.upstreamEvidenceCount)
    }

    @Test
    fun suiteReportMarksTraceCreationAndValidationPassingOnlyAsCommonTestSuiteEvidence() {
        val report = report()

        assertTrue(report.syntheticTraceCreated)
        assertTrue(report.syntheticTraceValidationPassed)
        assertTrue(report.syntheticTraceSuitePassed)
        assertTrue(report.suiteIsCommonTestOnly)
        assertTrue(report.suitePayloadFree)
        assertFalse(report.productionAuthorizationPresent)
        assertFalse(report.providerSelectionAuthorizationPresent)
        assertFalse(report.providerOperationAuthorizationPresent)
    }

    @Test
    fun suiteReportValidatesAllUpstreamEvidencePayloadFreeAndAddsNoPayloads() {
        val report = report()

        assertTrue(report.suitePayloadFree)
        assertFalse(report.suiteCreatesTracePayloads)
        assertFalse(report.tracePayloadPresent)
        assertFalse(report.providerOperationPayloadPresent)
    }

    @Test
    fun suiteReportValidatesNoRawKatPublicVectorProviderHandleLocationOrExportPayloadMaterial() {
        val report = report()

        assertFalse(report.rawKatMaterialPresent)
        assertFalse(report.vectorBytesPresent)
        assertFalse(report.vectorHexPresent)
        assertFalse(report.publicVectorBytesPresent)
        assertFalse(report.publicVectorHexPresent)
        assertFalse(report.providerHandlePresent)
        assertFalse(report.sourceLocationPresent)
        assertFalse(report.stackTracePresent)
        assertFalse(report.diagnosticsPayloadPresent)
        assertFalse(report.analyticsPayloadPresent)
        assertFalse(report.crashReportPayloadPresent)
        assertFalse(report.supportExportPayloadPresent)
    }

    @Test
    fun suiteReportValidatesNoProviderOperationCryptoKatRunnerKatExecutorOrProviderImplementation() {
        val report = report()

        assertFalse(report.providerOperationExecuted)
        assertFalse(report.cryptoExecuted)
        assertFalse(report.katRunnerPresent)
        assertFalse(report.katExecutorPresent)
        assertFalse(report.providerKatExecutorPresent)
        assertFalse(report.providerImplementationPresent)
    }

    @Test
    fun suiteReportValidatesNoRegistryFactoryDispatcherOrExecutorTargetReachability() {
        val report = report()

        assertFalse(report.registryEntryPresent)
        assertFalse(report.factoryEntryPresent)
        assertFalse(report.dispatcherEntryPresent)
        assertFalse(report.executorTargetPresent)
        assertFalse(report.providerKatExecutorReachable)
        assertFalse(report.productionRuntimeReachable)
    }

    @Test
    fun suiteReportValidatesNoVaultLifecyclePersistenceSyncSigningBroadcastingUiEndpointOrMainnetPath() {
        val report = report()

        assertFalse(report.vaultLifecyclePresent)
        assertFalse(report.vaultPersistencePresent)
        assertFalse(report.productionSyncPresent)
        assertFalse(report.signingBroadcastingPresent)
        assertFalse(report.uiPresent)
        assertFalse(report.endpointPresent)
        assertFalse(report.mainnetPresent)
    }

    @Test
    fun productionProviderSelectableFalseAndProviderSelectionResolvesDisabledProviderOnly() {
        val report = report()
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertTrue(report.disabledProviderOnly)
        assertFalse(report.productionProviderSelectable)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertIs<DisabledVaultCryptoProvider>(selection.selectedProvider)
        assertTrue(selection.selectedProviderIsDisabled)
        assertFalse(selection.productionProviderSelectable)
    }

    @Test
    fun suitePassingIsNotAnyProductionProviderSelectionOperationCryptoKatPersistenceSyncUiEndpointOrMainnetAuthorization() {
        val report = report()

        assertTrue(report.syntheticTraceSuitePassed)
        assertFalse(report.productionAuthorizationPresent)
        assertFalse(report.providerSelectionAuthorizationPresent)
        assertFalse(report.providerOperationAuthorizationPresent)
        assertFalse(report.cryptoAuthorizationPresent)
        assertFalse(report.katRunnerAuthorizationPresent)
        assertFalse(report.katExecutorAuthorizationPresent)
        assertFalse(report.providerKatExecutorAuthorizationPresent)
        assertFalse(report.vaultPersistenceAuthorizationPresent)
        assertFalse(report.syncAuthorizationPresent)
        assertFalse(report.signingBroadcastingAuthorizationPresent)
        assertFalse(report.endpointAuthorizationPresent)
        assertFalse(report.uiAuthorizationPresent)
        assertFalse(report.mainnetAuthorizationPresent)
    }

    @Test
    fun suiteChecksAreRepresentedExactlyOnceAndNoFailuresBlockersOrWarningsArePresent() {
        val report = report()

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportCheck.entries.size,
            report.suiteCheckCount,
        )
        assertEquals(report.suiteCheckCount, report.suiteChecks.size)
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportCheck.entries.forEach { check ->
            assertEquals(1, report.suiteChecks.count { represented -> represented == check })
        }
        assertEquals(0, report.blockerCount)
        assertEquals(0, report.warningCount)
        assertTrue(report.failureLabels.isEmpty())
        assertTrue(report.blockers.isEmpty())
        assertTrue(report.warnings.isEmpty())
    }

    @Test
    fun suiteReportComposesExistingEvidenceAndDoesNotCreateSecondTraceOrExecutableProviderOperationPath() {
        val report = report()

        assertFalse(report.suiteCreatesTrace)
        assertFalse(report.secondTraceCreated)
        assertFalse(report.executableProviderOperationPathPresent)
        assertTrue(report.syntheticTracePresent)
        assertTrue(report.syntheticTraceCreated)
    }

    @Test
    fun everySuiteAuthorizationReachabilityExecutionAndPayloadBooleanRemainsFalse() {
        val report = report()
        val blockedBooleans = listOf(
            report.suiteCreatesTrace,
            report.suiteCreatesTracePayloads,
            report.productionProviderSelectable,
            report.productionRuntimeReachable,
            report.tracePayloadPresent,
            report.providerOperationPayloadPresent,
            report.providerOperationExecuted,
            report.cryptoExecuted,
            report.katRunnerPresent,
            report.katExecutorPresent,
            report.providerKatExecutorPresent,
            report.providerImplementationPresent,
            report.registryEntryPresent,
            report.factoryEntryPresent,
            report.dispatcherEntryPresent,
            report.executorTargetPresent,
            report.providerKatExecutorReachable,
            report.rawKatMaterialPresent,
            report.vectorBytesPresent,
            report.vectorHexPresent,
            report.publicVectorBytesPresent,
            report.publicVectorHexPresent,
            report.providerHandlePresent,
            report.sourceLocationPresent,
            report.stackTracePresent,
            report.diagnosticsPayloadPresent,
            report.analyticsPayloadPresent,
            report.crashReportPayloadPresent,
            report.supportExportPayloadPresent,
            report.vaultLifecyclePresent,
            report.vaultPersistencePresent,
            report.productionSyncPresent,
            report.signingBroadcastingPresent,
            report.uiPresent,
            report.endpointPresent,
            report.mainnetPresent,
            report.secondTraceCreated,
            report.executableProviderOperationPathPresent,
            report.productionAuthorizationPresent,
            report.providerSelectionAuthorizationPresent,
            report.providerOperationAuthorizationPresent,
            report.cryptoAuthorizationPresent,
            report.katRunnerAuthorizationPresent,
            report.katExecutorAuthorizationPresent,
            report.providerKatExecutorAuthorizationPresent,
            report.vaultPersistenceAuthorizationPresent,
            report.syncAuthorizationPresent,
            report.signingBroadcastingAuthorizationPresent,
            report.uiAuthorizationPresent,
            report.endpointAuthorizationPresent,
            report.mainnetAuthorizationPresent,
        )

        assertTrue(blockedBooleans.none { enabled -> enabled })
    }

    @Test
    fun suiteReportOutputIsRedactedAndContainsNoUnsafeMaterialTerms() {
        val report = report()
        val outputs = listOf(
            report.toString(),
            report.displayLabel.toString(),
            report.suiteReportId.toString(),
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

    private fun report() =
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportPolicy
            .currentProviderOperationSyntheticTraceSuiteReport()
}
