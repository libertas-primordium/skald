package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceEvent
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTracePolicy
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityProviderOperationSyntheticTraceTest {
    @Test
    fun syntheticProviderOperationTraceExistsOnlyAsCommonTestTraceEvidence() {
        val trace = trace()

        assertTrue(trace.traceIsCommonTestOnly)
        assertTrue(trace.syntheticTraceCreated)
        assertTrue(trace.syntheticTracePayloadFree)
        assertFalse(trace.productionProviderSelectable)
    }

    @Test
    fun syntheticTraceReadsSyntheticTraceAdmissionAndNoopExecutionBoundarySuiteChain() {
        val trace = trace()

        assertEquals(1, trace.markerCount)
        assertEquals(1, trace.caseBindingCount)
        assertEquals(1, trace.providerOperationMetadataKatCount)
        assertEquals(1, trace.providerOperationNoopKatCount)
        assertEquals(1, trace.providerOperationNoopKatValidationCount)
        assertEquals(1, trace.providerOperationNoopKatSuiteReportCount)
        assertEquals(1, trace.providerOperationNoopExecutionBoundaryCount)
        assertEquals(1, trace.providerOperationNoopExecutionBoundaryValidationCount)
        assertEquals(1, trace.providerOperationNoopExecutionBoundarySuiteReportCount)
        assertEquals(1, trace.providerOperationSyntheticTraceAdmissionCount)
    }

    @Test
    fun syntheticTraceCreatesExactlyOnePayloadFreeSyntheticTraceArtifact() {
        val traces = listOf(trace())
        val trace = traces.single()

        assertEquals(1, traces.size)
        assertTrue(trace.syntheticTraceCreated)
        assertTrue(trace.syntheticTracePayloadFree)
        assertFalse(trace.syntheticTracePayloadPresent)
    }

    @Test
    fun syntheticTraceConfirmsAdmissionBoundarySuiteAndNoopKatEvidence() {
        val trace = trace()

        assertTrue(trace.futureSyntheticTraceCriteriaModeled)
        assertTrue(trace.noopExecutionBoundarySuitePassed)
        assertTrue(trace.noopExecutionBoundaryValidationPassed)
        assertTrue(trace.noopExecutionBoundaryModeled)
        assertTrue(trace.syntheticNoopEvaluationPermitted)
        assertTrue(trace.syntheticNoopResultPresent)
        assertTrue(trace.providerOperationNoopKatPassed)
        assertTrue(trace.providerOperationNoopKatValidationPassed)
        assertTrue(trace.providerOperationNoopKatSuitePassed)
    }

    @Test
    fun syntheticTraceConfirmsExpectedMarkerFixtureVectorCaseKatBoundarySuiteAndTraceIds() {
        val trace = trace()

        assertEquals("skald-test-only-provider-identity-v1-deterministic-kat-inert-marker", markerSafeId())
        assertEquals("skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata", fixtureId())
        assertEquals("skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata", vectorId())
        assertEquals("skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata", caseId())
        assertEquals(
            "skald-test-only-provider-identity-provider-operation-metadata-kat-v1-inert-identity",
            providerOperationMetadataKatId(),
        )
        assertEquals(
            "skald-test-only-provider-identity-provider-operation-noop-kat-v1-inert-identity",
            providerOperationNoopKatId(),
        )
        assertEquals(
            "skald-test-only-provider-identity-provider-operation-noop-execution-boundary-v1-inert-identity",
            providerOperationNoopExecutionBoundaryId(),
        )
        assertEquals(
            "skald-test-only-provider-identity-provider-operation-noop-execution-boundary-suite-report-v1-inert-identity",
            providerOperationNoopExecutionBoundarySuiteReportId(),
        )
        assertEquals(
            "skald-test-only-provider-identity-provider-operation-synthetic-trace-v1-inert-identity",
            providerOperationSyntheticTraceId(),
        )
        assertTrue(trace.expectedSafeIdMatched)
        assertTrue(trace.expectedFixtureIdMatched)
        assertTrue(trace.expectedVectorIdMatched)
        assertTrue(trace.expectedCaseIdMatched)
        assertTrue(trace.expectedProviderOperationMetadataKatIdMatched)
        assertTrue(trace.expectedProviderOperationNoopKatIdMatched)
        assertTrue(trace.expectedProviderOperationNoopExecutionBoundaryIdMatched)
        assertTrue(trace.expectedProviderOperationNoopExecutionBoundarySuiteReportIdMatched)
        assertTrue(trace.expectedProviderOperationSyntheticTraceIdMatched)
    }

    @Test
    fun allSyntheticTraceEventsAreRepresentedExactlyOnce() {
        val trace = trace()

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceEvent.entries.toSet(),
            trace.traceEvents.toSet(),
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceEvent.entries.size,
            trace.traceEvents.size,
        )
    }

    @Test
    fun syntheticTraceCreatedIsCommonTestOnlyPayloadFreeTraceEvidenceOnly() {
        val trace = trace()

        assertTrue(trace.syntheticTraceCreated)
        assertTrue(trace.traceIsCommonTestOnly)
        assertTrue(trace.syntheticTracePayloadFree)
        assertFalse(trace.traceIsProductionAuthorization)
        assertFalse(trace.traceIsProviderSelectionAuthorization)
        assertFalse(trace.traceIsProviderOperationAuthorization)
    }

    @Test
    fun syntheticTraceContainsNoPayloadRawBytesHexHandlesObjectsWalletEndpointOrDiagnosticMaterial() {
        val trace = trace()

        assertFalse(trace.syntheticTracePayloadPresent)
        assertFalse(trace.syntheticTraceContainsRawBytes)
        assertFalse(trace.syntheticTraceContainsHex)
        assertFalse(trace.syntheticTraceContainsProviderHandles)
        assertFalse(trace.syntheticTraceContainsCryptoObjects)
        assertFalse(trace.syntheticTraceContainsWalletMaterial)
        assertFalse(trace.syntheticTraceContainsEndpointMaterial)
        assertFalse(trace.syntheticTraceContainsSourceLocation)
        assertFalse(trace.syntheticTraceContainsDiagnosticsPayload)
        assertFalse(trace.syntheticTraceContainsAnalyticsPayload)
        assertFalse(trace.syntheticTraceContainsCrashReportPayload)
        assertFalse(trace.syntheticTraceContainsSupportExportPayload)
    }

    @Test
    fun syntheticTraceIsNotProductionProviderSelectionProviderOperationKatExecutorCryptoVaultPersistenceOrMainnetAuthorization() {
        val trace = trace()

        assertFalse(trace.traceIsProductionAuthorization)
        assertFalse(trace.traceIsProviderSelectionAuthorization)
        assertFalse(trace.traceIsProviderOperationAuthorization)
        assertFalse(trace.traceIsKatExecutorAuthorization)
        assertFalse(trace.traceIsCryptoAuthorization)
        assertFalse(trace.traceIsVaultPersistenceAuthorization)
        assertFalse(trace.traceIsMainnetAuthorization)
    }

    @Test
    fun syntheticTraceReportsSyntheticNoopOnlyAndNoExecutionLifecycleOrPersistence() {
        val trace = trace()

        assertTrue(trace.traceReportsSyntheticNoopOnly)
        assertFalse(trace.traceReportsProviderOperationExecution)
        assertFalse(trace.traceReportsCryptoOperation)
        assertFalse(trace.traceReportsVaultLifecycle)
        assertFalse(trace.traceReportsPersistence)
    }

    @Test
    fun syntheticTraceDoesNotPermitExecutionRunnerPersistenceOrMainnet() {
        val trace = trace()

        assertFalse(trace.realProviderOperationExecutionPermitted)
        assertFalse(trace.cryptoExecutionPermitted)
        assertFalse(trace.katRunnerPermitted)
        assertFalse(trace.katExecutorPermitted)
        assertFalse(trace.vaultPersistencePermitted)
        assertFalse(trace.mainnetPermitted)
    }

    @Test
    fun syntheticTraceHasNoProviderOperationKatRunnerExecutorExecutionCryptoOrVectorMaterial() {
        val trace = trace()

        assertFalse(trace.providerOperationKatExecutorPresent)
        assertFalse(trace.providerOperationKatRunnerPresent)
        assertFalse(trace.providerOperationExecutionPresent)
        assertFalse(trace.cryptoExecutionPresent)
        assertFalse(trace.rawKatMaterialPresent)
        assertFalse(trace.rawVectorBytesPresent)
        assertFalse(trace.rawVectorHexPresent)
        assertFalse(trace.publicVectorBytesPresent)
        assertFalse(trace.publicVectorHexPresent)
    }

    @Test
    fun syntheticTraceDoesNotImplementOrExposeVaultCryptoProvider() {
        val trace = trace()

        assertFalse(trace.implementsVaultCryptoProvider)
        assertFalse(trace.containsVaultCryptoProvider)
    }

    @Test
    fun syntheticTraceIsNotSelectedByVaultCryptoProviderSelectionRegistry() {
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertIs<DisabledVaultCryptoProvider>(selection.selectedProvider)
        assertTrue(selection.selectedProviderIsDisabled)
        assertFalse(selection.productionProviderSelectable)
        assertFalse(selection.safeDetail.contains(markerSafeId()))
        assertFalse(selection.safeDetail.contains(fixtureId()))
        assertFalse(selection.safeDetail.contains(vectorId()))
        assertFalse(selection.safeDetail.contains(caseId()))
        assertFalse(selection.safeDetail.contains(providerOperationMetadataKatId()))
        assertFalse(selection.safeDetail.contains(providerOperationNoopKatId()))
        assertFalse(selection.safeDetail.contains(providerOperationNoopExecutionBoundaryId()))
        assertFalse(selection.safeDetail.contains(providerOperationNoopExecutionBoundarySuiteReportId()))
        assertFalse(selection.safeDetail.contains(providerOperationSyntheticTraceId()))
        assertFalse(
            selection.safeDetail.contains(
                "SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTrace",
            ),
        )
    }

    @Test
    fun providerSelectionRemainsDisabledProviderOnly() {
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertTrue(selection.selectedProviderIsDisabled)
        assertIs<DisabledVaultCryptoProvider>(selection.selectedProvider)
        assertFalse(selection.productionProviderSelectable)
    }

    @Test
    fun productionProviderSelectableRemainsFalse() {
        val trace = trace()

        assertFalse(trace.productionProviderSelectable)
        assertFalse(VaultCryptoProviderSelectionRegistry.select().productionProviderSelectable)
    }

    @Test
    fun everyCapabilityReachabilityAuthorizationExecutionAndPayloadBooleanRemainsFalse() {
        val trace = trace()
        val blockedBooleans = listOf(
            trace.syntheticTracePayloadPresent,
            trace.syntheticTraceContainsRawBytes,
            trace.syntheticTraceContainsHex,
            trace.syntheticTraceContainsProviderHandles,
            trace.syntheticTraceContainsCryptoObjects,
            trace.syntheticTraceContainsWalletMaterial,
            trace.syntheticTraceContainsEndpointMaterial,
            trace.syntheticTraceContainsSourceLocation,
            trace.syntheticTraceContainsDiagnosticsPayload,
            trace.syntheticTraceContainsAnalyticsPayload,
            trace.syntheticTraceContainsCrashReportPayload,
            trace.syntheticTraceContainsSupportExportPayload,
            trace.traceIsProductionAuthorization,
            trace.traceIsProviderSelectionAuthorization,
            trace.traceIsProviderOperationAuthorization,
            trace.traceIsKatExecutorAuthorization,
            trace.traceIsCryptoAuthorization,
            trace.traceIsVaultPersistenceAuthorization,
            trace.traceIsMainnetAuthorization,
            trace.traceReportsProviderOperationExecution,
            trace.traceReportsCryptoOperation,
            trace.traceReportsVaultLifecycle,
            trace.traceReportsPersistence,
            trace.realProviderOperationExecutionPermitted,
            trace.cryptoExecutionPermitted,
            trace.katRunnerPermitted,
            trace.katExecutorPermitted,
            trace.vaultPersistencePermitted,
            trace.mainnetPermitted,
            trace.providerOperationKatExecutorPresent,
            trace.providerOperationKatRunnerPresent,
            trace.providerOperationExecutionPresent,
            trace.cryptoExecutionPresent,
            trace.rawKatMaterialPresent,
            trace.rawVectorBytesPresent,
            trace.rawVectorHexPresent,
            trace.publicVectorBytesPresent,
            trace.publicVectorHexPresent,
            trace.runtimeSelectable,
            trace.registrySelectable,
            trace.factoryReachable,
            trace.dispatcherReachable,
            trace.executorTargetable,
            trace.providerKatExecutorReachable,
            trace.providerOperationReachable,
            trace.cryptoExecutionReachable,
            trace.vaultLifecycleReachable,
            trace.persistenceReachable,
            trace.productionSyncReachable,
            trace.backendClientReachable,
            trace.bdkWalletStateReachable,
            trace.settingsCodecReachable,
            trace.uiSurfaceReachable,
            trace.signingBroadcastingReachable,
            trace.publicEndpointReachable,
            trace.mainnetReachable,
            trace.implementsVaultCryptoProvider,
            trace.containsVaultCryptoProvider,
            trace.canExecuteProviderOperations,
            trace.canExecuteCrypto,
            trace.canUseForVaultLifecycle,
            trace.canUseForPersistence,
            trace.canUseForSync,
            trace.canUseForSigning,
            trace.canUseForBroadcasting,
            trace.canUseForMainnet,
            trace.productionProviderSelectable,
        )

        assertTrue(blockedBooleans.none { enabled -> enabled })
    }

    @Test
    fun syntheticTraceIsNotRegistryFactoryDispatcherExecutorOrKatExecutorReachable() {
        val trace = trace()

        assertFalse(trace.registrySelectable)
        assertFalse(trace.factoryReachable)
        assertFalse(trace.dispatcherReachable)
        assertFalse(trace.executorTargetable)
        assertFalse(trace.providerKatExecutorReachable)
    }

    @Test
    fun syntheticTraceCannotExecuteProviderOperationsOrCrypto() {
        val trace = trace()

        assertFalse(trace.canExecuteProviderOperations)
        assertFalse(trace.canExecuteCrypto)
        assertFalse(trace.providerOperationReachable)
        assertFalse(trace.cryptoExecutionReachable)
        assertFalse(trace.providerOperationExecutionPresent)
        assertFalse(trace.cryptoExecutionPresent)
    }

    @Test
    fun syntheticTraceCannotParticipateInVaultLifecyclePersistenceOrProductionSync() {
        val trace = trace()

        assertFalse(trace.canUseForVaultLifecycle)
        assertFalse(trace.canUseForPersistence)
        assertFalse(trace.canUseForSync)
        assertFalse(trace.vaultLifecycleReachable)
        assertFalse(trace.persistenceReachable)
        assertFalse(trace.productionSyncReachable)
    }

    @Test
    fun syntheticTraceCannotSignBroadcastOrEnableMainnet() {
        val trace = trace()

        assertFalse(trace.canUseForSigning)
        assertFalse(trace.canUseForBroadcasting)
        assertFalse(trace.canUseForMainnet)
        assertFalse(trace.signingBroadcastingReachable)
        assertFalse(trace.mainnetReachable)
        assertFalse(trace.mainnetPermitted)
    }

    @Test
    fun syntheticTraceDoesNotExposeRawVectorsRuntimeReferencesRawIdsPayloadsLocationsOrDiagnosticsInToString() {
        val trace = trace()
        val outputs = listOf(
            trace.toString(),
            trace.displayLabel.toString(),
        )
        val rejectedTerms = listOf(
            "raw KAT vector",
            "raw material",
            "provider handle",
            "crypto object",
            "storage reference",
            "backend reference",
            "endpoint reference",
            "wallet reference",
            "paths",
            "descriptor text",
            "source location",
            "stack trace",
            "implementation payload",
            "diagnostic payload",
            "analytics payload",
            "crash report",
            "support export",
            "trace payload",
            "public vector bytes",
            "public vector hex",
            "KAT vector bytes",
            "KAT vector hex",
            "fingerprint",
            "secret hash",
        )

        outputs.forEach { output ->
            assertFalse(output.contains(markerSafeId()))
            assertFalse(output.contains(fixtureId()))
            assertFalse(output.contains(vectorId()))
            assertFalse(output.contains(caseId()))
            assertFalse(output.contains(providerOperationMetadataKatId()))
            assertFalse(output.contains(providerOperationNoopKatId()))
            assertFalse(output.contains(providerOperationNoopExecutionBoundaryId()))
            assertFalse(output.contains(providerOperationNoopExecutionBoundarySuiteReportId()))
            assertFalse(output.contains(providerOperationSyntheticTraceId()))
            rejectedTerms.forEach { term ->
                assertFalse(output.contains(term, ignoreCase = true), "safe output leaked $term")
            }
        }
    }

    @Test
    fun syntheticTraceAndUpstreamBoundaryChainAreCommonTestOnlyBySourceGuardContract() {
        val trace = trace()

        assertTrue(trace.traceIsCommonTestOnly)
        assertTrue(trace.syntheticTraceCreated)
        assertTrue(trace.expectedSafeIdMatched)
        assertTrue(trace.expectedFixtureIdMatched)
        assertTrue(trace.expectedVectorIdMatched)
        assertTrue(trace.expectedCaseIdMatched)
        assertTrue(trace.expectedProviderOperationMetadataKatIdMatched)
        assertTrue(trace.expectedProviderOperationNoopKatIdMatched)
        assertTrue(trace.expectedProviderOperationNoopExecutionBoundaryIdMatched)
        assertTrue(trace.expectedProviderOperationNoopExecutionBoundarySuiteReportIdMatched)
        assertTrue(trace.expectedProviderOperationSyntheticTraceIdMatched)
        assertTrue(trace.syntheticTracePayloadFree)
        assertFalse(trace.syntheticTracePayloadPresent)
        assertFalse(trace.traceIsProviderOperationAuthorization)
        assertFalse(trace.realProviderOperationExecutionPermitted)
        assertFalse(trace.providerOperationExecutionPresent)
        assertFalse(trace.cryptoExecutionPresent)
    }

    private fun trace() =
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTracePolicy
            .currentProviderOperationSyntheticTrace()

    private fun markerSafeId(): String =
        "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"

    private fun fixtureId(): String =
        "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata"

    private fun vectorId(): String =
        "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata"

    private fun caseId(): String =
        "skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata"

    private fun providerOperationMetadataKatId(): String =
        "skald-test-only-provider-identity-provider-operation-metadata-kat-v1-inert-identity"

    private fun providerOperationNoopKatId(): String =
        "skald-test-only-provider-identity-provider-operation-noop-kat-v1-inert-identity"

    private fun providerOperationNoopExecutionBoundaryId(): String =
        "skald-test-only-provider-identity-provider-operation-noop-execution-boundary-v1-inert-identity"

    private fun providerOperationNoopExecutionBoundarySuiteReportId(): String =
        "skald-test-only-provider-identity-provider-operation-noop-execution-boundary-suite-report-v1-inert-identity"

    private fun providerOperationSyntheticTraceId(): String =
        "skald-test-only-provider-identity-provider-operation-synthetic-trace-v1-inert-identity"
}
