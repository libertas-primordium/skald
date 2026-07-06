package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationCheck
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationSourceSet
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityProviderOperationSyntheticTraceValidationTest {
    @Test
    fun validationReportSeesSyntheticTraceAsPresent() {
        val report = report()

        assertTrue(report.sourceTracePresent)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationSourceSet.CommonTest,
            report.sourceSet,
        )
        assertTrue(report.validationIsCommonTestOnly)
        assertTrue(report.validationIsValidationOnly)
    }

    @Test
    fun validationReportSeesSyntheticTraceCreatedOnlyAsCommonTestEvidence() {
        val report = report()

        assertTrue(report.sourceTraceCreated)
        assertTrue(report.syntheticTraceValidationPassed)
        assertTrue(report.validationPassed)
        assertFalse(report.productionAuthorizationPresent)
        assertFalse(report.providerSelectionAuthorizationPresent)
        assertFalse(report.providerOperationAuthorizationPresent)
    }

    @Test
    fun validationReportValidatesSourceTraceAsPayloadFree() {
        val report = report()

        assertTrue(report.sourceTracePayloadFree)
        assertTrue(report.sourceTraceSafeLabelEvidenceOnly)
        assertTrue(report.sourceTraceEnumEvidenceOnly)
        assertTrue(report.sourceTraceCountEvidenceOnly)
        assertTrue(report.sourceTraceBooleanEvidenceOnly)
        assertTrue(report.safeLabelCount > 0)
        assertTrue(report.enumEvidenceCount > 0)
        assertTrue(report.countEvidenceCount > 0)
        assertTrue(report.booleanEvidenceCount > 0)
    }

    @Test
    fun validationReportValidatesNoTraceProviderOperationRawKatOrPublicVectorPayloadMaterial() {
        val report = report()

        assertFalse(report.tracePayloadPresent)
        assertFalse(report.providerOperationPayloadPresent)
        assertFalse(report.rawKatMaterialPresent)
        assertFalse(report.vectorBytesPresent)
        assertFalse(report.vectorHexPresent)
        assertFalse(report.publicVectorBytesPresent)
        assertFalse(report.publicVectorHexPresent)
        assertFalse(report.providerHandlePresent)
    }

    @Test
    fun validationReportValidatesNoSourceLocationStackTraceOrExportPayloads() {
        val report = report()

        assertFalse(report.sourceLocationPresent)
        assertFalse(report.stackTracePresent)
        assertFalse(report.diagnosticsPayloadPresent)
        assertFalse(report.analyticsPayloadPresent)
        assertFalse(report.crashReportPayloadPresent)
        assertFalse(report.supportExportPayloadPresent)
    }

    @Test
    fun validationReportValidatesNoProviderOperationCryptoKatRunnerKatExecutorOrProviderImplementation() {
        val report = report()

        assertFalse(report.providerOperationExecuted)
        assertFalse(report.cryptoExecuted)
        assertFalse(report.katRunnerPresent)
        assertFalse(report.katExecutorPresent)
        assertFalse(report.providerKatExecutorPresent)
        assertFalse(report.providerImplementationPresent)
    }

    @Test
    fun validationReportValidatesNoRegistryFactoryDispatcherOrExecutorTargetReachability() {
        val report = report()

        assertFalse(report.registryReachable)
        assertFalse(report.factoryReachable)
        assertFalse(report.dispatcherReachable)
        assertFalse(report.executorTargetReachable)
        assertFalse(report.providerKatExecutorReachable)
        assertFalse(report.productionRuntimeReachable)
    }

    @Test
    fun validationReportValidatesNoVaultPersistenceSyncSigningBroadcastingUiEndpointOrMainnetPath() {
        val report = report()

        assertFalse(report.vaultLifecyclePresent)
        assertFalse(report.vaultPersistencePresent)
        assertFalse(report.syncPresent)
        assertFalse(report.signingBroadcastingPresent)
        assertFalse(report.uiPresent)
        assertFalse(report.endpointPresent)
        assertFalse(report.mainnetPresent)
    }

    @Test
    fun providerSelectionRemainsDisabledProviderOnlyAndProductionProviderSelectableFalse() {
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
    fun validationPassingIsNotAnyProductionProviderSelectionOperationCryptoKatPersistenceSyncUiEndpointOrMainnetAuthorization() {
        val report = report()

        assertTrue(report.syntheticTraceValidationPassed)
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
    fun validationChecksAreRepresentedExactlyOnceAndNoFailuresBlockersOrWarningsArePresent() {
        val report = report()

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationCheck.entries.size,
            report.validationCheckCount,
        )
        assertEquals(report.validationCheckCount, report.validationChecks.size)
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationCheck.entries.forEach { check ->
            assertEquals(1, report.validationChecks.count { represented -> represented == check })
        }
        assertTrue(report.failureLabels.isEmpty())
        assertTrue(report.blockers.isEmpty())
        assertTrue(report.warnings.isEmpty())
    }

    @Test
    fun everyValidationAuthorizationReachabilityExecutionAndPayloadBooleanRemainsFalse() {
        val report = report()
        val blockedBooleans = listOf(
            report.validationCreatesTrace,
            report.validationCreatesTracePayloads,
            report.productionProviderSelectable,
            report.productionRuntimeReachable,
            report.providerOperationExecuted,
            report.cryptoExecuted,
            report.katRunnerPresent,
            report.katExecutorPresent,
            report.providerKatExecutorPresent,
            report.providerImplementationPresent,
            report.registryReachable,
            report.factoryReachable,
            report.dispatcherReachable,
            report.executorTargetReachable,
            report.providerKatExecutorReachable,
            report.tracePayloadPresent,
            report.providerOperationPayloadPresent,
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
            report.vaultPersistencePresent,
            report.vaultLifecyclePresent,
            report.syncPresent,
            report.signingBroadcastingPresent,
            report.uiPresent,
            report.endpointPresent,
            report.mainnetPresent,
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
    fun validationReportOutputIsRedactedAndContainsNoUnsafeMaterialTerms() {
        val report = report()
        val outputs = listOf(
            report.toString(),
            report.displayLabel.toString(),
            report.validationId.toString(),
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
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationPolicy
            .currentProviderOperationSyntheticTraceValidationReport()
}
