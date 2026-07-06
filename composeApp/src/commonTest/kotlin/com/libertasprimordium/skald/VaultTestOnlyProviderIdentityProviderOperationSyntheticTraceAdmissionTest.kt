package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityForbiddenCurrentSyntheticTraceState
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityFutureSyntheticTraceCriterion
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceAdmissionOutcome
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceAdmissionPolicy
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityProviderOperationSyntheticTraceAdmissionTest {
    @Test
    fun syntheticProviderOperationTraceAdmissionExistsOnlyAsCommonTestAdmissionEvidence() {
        val admission = admission()

        assertTrue(admission.admissionIsCommonTestOnly)
        assertTrue(admission.futureSyntheticTraceCriteriaModeled)
        assertFalse(admission.futureSyntheticTraceCriteriaAuthorizeCurrentTrace)
        assertFalse(admission.productionProviderSelectable)
    }

    @Test
    fun admissionReadsNoopExecutionBoundarySuiteReportAndNoopChain() {
        val admission = admission()

        assertEquals(1, admission.markerCount)
        assertEquals(1, admission.caseBindingCount)
        assertEquals(1, admission.providerOperationMetadataKatCount)
        assertEquals(1, admission.providerOperationNoopKatCount)
        assertEquals(1, admission.providerOperationNoopKatValidationCount)
        assertEquals(1, admission.providerOperationNoopKatSuiteReportCount)
        assertEquals(1, admission.providerOperationNoopExecutionBoundaryCount)
        assertEquals(1, admission.providerOperationNoopExecutionBoundaryValidationCount)
        assertEquals(1, admission.providerOperationNoopExecutionBoundarySuiteReportCount)
    }

    @Test
    fun admissionConfirmsNoopBoundarySuiteAndNoopKatEvidence() {
        val admission = admission()

        assertTrue(admission.noopExecutionBoundarySuitePassed)
        assertTrue(admission.noopExecutionBoundaryValidationPassed)
        assertTrue(admission.noopExecutionBoundaryModeled)
        assertTrue(admission.syntheticNoopEvaluationPermitted)
        assertTrue(admission.syntheticNoopResultPresent)
        assertTrue(admission.providerOperationNoopKatPassed)
        assertTrue(admission.providerOperationNoopKatValidationPassed)
        assertTrue(admission.providerOperationNoopKatSuitePassed)
    }

    @Test
    fun admissionConfirmsExpectedMarkerFixtureVectorCaseKatBoundarySuiteAndSyntheticTraceIds() {
        val admission = admission()

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
        assertTrue(admission.expectedSafeIdMatched)
        assertTrue(admission.expectedFixtureIdMatched)
        assertTrue(admission.expectedVectorIdMatched)
        assertTrue(admission.expectedCaseIdMatched)
        assertTrue(admission.expectedProviderOperationMetadataKatIdMatched)
        assertTrue(admission.expectedProviderOperationNoopKatIdMatched)
        assertTrue(admission.expectedProviderOperationNoopExecutionBoundaryIdMatched)
        assertTrue(admission.expectedProviderOperationNoopExecutionBoundarySuiteReportIdMatched)
        assertTrue(admission.expectedProviderOperationSyntheticTraceIdMatched)
    }

    @Test
    fun allAdmissionOutcomesFutureCriteriaAndForbiddenCurrentStatesAreRepresentedExactlyOnce() {
        val admission = admission()

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceAdmissionOutcome.entries.toSet(),
            admission.admissionOutcomes.toSet(),
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceAdmissionOutcome.entries.size,
            admission.admissionOutcomes.size,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityFutureSyntheticTraceCriterion.entries.toSet(),
            admission.futureSyntheticTraceCriteria.toSet(),
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityFutureSyntheticTraceCriterion.entries.size,
            admission.futureSyntheticTraceCriteria.size,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityForbiddenCurrentSyntheticTraceState.entries.toSet(),
            admission.forbiddenCurrentSyntheticTraceStates.toSet(),
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityForbiddenCurrentSyntheticTraceState.entries.size,
            admission.forbiddenCurrentSyntheticTraceStates.size,
        )
    }

    @Test
    fun futureSyntheticTraceCriteriaAreCommonTestOnlyAndDoNotAuthorizeCurrentTraceCreation() {
        val admission = admission()

        assertTrue(admission.futureSyntheticTraceCriteriaModeled)
        assertFalse(admission.futureSyntheticTraceCriteriaAuthorizeCurrentTrace)
        assertFalse(admission.currentSyntheticTracePresent)
        assertFalse(admission.currentTracePayloadPresent)
        assertFalse(admission.currentNoopProviderOperationExecutionPresent)
        assertFalse(admission.currentRealProviderOperationExecutionPresent)
        assertFalse(admission.currentCryptoExecutionPresent)
        assertFalse(admission.currentKatRunnerPresent)
        assertFalse(admission.currentKatExecutorPresent)
    }

    @Test
    fun admissionIsNotProductionProviderSelectionProviderOperationKatExecutorCryptoVaultPersistenceOrMainnetAuthorization() {
        val admission = admission()

        assertFalse(admission.admissionIsProductionAuthorization)
        assertFalse(admission.admissionIsProviderSelectionAuthorization)
        assertFalse(admission.admissionIsProviderOperationAuthorization)
        assertFalse(admission.admissionIsKatExecutorAuthorization)
        assertFalse(admission.admissionIsCryptoAuthorization)
        assertFalse(admission.admissionIsVaultPersistenceAuthorization)
        assertFalse(admission.admissionIsMainnetAuthorization)
    }

    @Test
    fun admissionDoesNotPermitExecutionRunnerPersistenceOrMainnet() {
        val admission = admission()

        assertFalse(admission.realProviderOperationExecutionPermitted)
        assertFalse(admission.cryptoExecutionPermitted)
        assertFalse(admission.katRunnerPermitted)
        assertFalse(admission.katExecutorPermitted)
        assertFalse(admission.vaultPersistencePermitted)
        assertFalse(admission.mainnetPermitted)
    }

    @Test
    fun admissionHasNoRawVectorPublicVectorOrSyntheticTracePayloadMaterial() {
        val admission = admission()

        assertFalse(admission.rawKatMaterialPresent)
        assertFalse(admission.rawVectorBytesPresent)
        assertFalse(admission.rawVectorHexPresent)
        assertFalse(admission.publicVectorBytesPresent)
        assertFalse(admission.publicVectorHexPresent)
        assertFalse(admission.currentTracePayloadPresent)
    }

    @Test
    fun admissionDoesNotImplementOrExposeVaultCryptoProvider() {
        val admission = admission()

        assertFalse(admission.implementsVaultCryptoProvider)
        assertFalse(admission.containsVaultCryptoProvider)
    }

    @Test
    fun admissionIsNotSelectedByVaultCryptoProviderSelectionRegistry() {
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
                "SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceAdmission",
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
        val admission = admission()

        assertFalse(admission.productionProviderSelectable)
        assertFalse(VaultCryptoProviderSelectionRegistry.select().productionProviderSelectable)
    }

    @Test
    fun everyCapabilityReachabilityAuthorizationExecutionAndTraceBooleanRemainsFalse() {
        val admission = admission()
        val blockedBooleans = listOf(
            admission.futureSyntheticTraceCriteriaAuthorizeCurrentTrace,
            admission.admissionIsProductionAuthorization,
            admission.admissionIsProviderSelectionAuthorization,
            admission.admissionIsProviderOperationAuthorization,
            admission.admissionIsKatExecutorAuthorization,
            admission.admissionIsCryptoAuthorization,
            admission.admissionIsVaultPersistenceAuthorization,
            admission.admissionIsMainnetAuthorization,
            admission.currentSyntheticTracePresent,
            admission.currentTracePayloadPresent,
            admission.currentNoopProviderOperationExecutionPresent,
            admission.currentRealProviderOperationExecutionPresent,
            admission.currentCryptoExecutionPresent,
            admission.currentKatRunnerPresent,
            admission.currentKatExecutorPresent,
            admission.realProviderOperationExecutionPermitted,
            admission.cryptoExecutionPermitted,
            admission.katRunnerPermitted,
            admission.katExecutorPermitted,
            admission.vaultPersistencePermitted,
            admission.mainnetPermitted,
            admission.rawKatMaterialPresent,
            admission.rawVectorBytesPresent,
            admission.rawVectorHexPresent,
            admission.publicVectorBytesPresent,
            admission.publicVectorHexPresent,
            admission.runtimeSelectable,
            admission.registrySelectable,
            admission.factoryReachable,
            admission.dispatcherReachable,
            admission.executorTargetable,
            admission.providerKatExecutorReachable,
            admission.providerOperationReachable,
            admission.cryptoExecutionReachable,
            admission.vaultLifecycleReachable,
            admission.persistenceReachable,
            admission.productionSyncReachable,
            admission.backendClientReachable,
            admission.bdkWalletStateReachable,
            admission.settingsCodecReachable,
            admission.uiSurfaceReachable,
            admission.signingBroadcastingReachable,
            admission.publicEndpointReachable,
            admission.mainnetReachable,
            admission.implementsVaultCryptoProvider,
            admission.containsVaultCryptoProvider,
            admission.canExecuteProviderOperations,
            admission.canExecuteCrypto,
            admission.canUseForVaultLifecycle,
            admission.canUseForPersistence,
            admission.canUseForSync,
            admission.canUseForSigning,
            admission.canUseForBroadcasting,
            admission.canUseForMainnet,
            admission.productionProviderSelectable,
        )

        assertTrue(blockedBooleans.none { enabled -> enabled })
    }

    @Test
    fun admissionIsNotRegistryFactoryDispatcherExecutorOrKatExecutorReachable() {
        val admission = admission()

        assertFalse(admission.registrySelectable)
        assertFalse(admission.factoryReachable)
        assertFalse(admission.dispatcherReachable)
        assertFalse(admission.executorTargetable)
        assertFalse(admission.providerKatExecutorReachable)
    }

    @Test
    fun admissionCannotExecuteProviderOperationsOrCrypto() {
        val admission = admission()

        assertFalse(admission.canExecuteProviderOperations)
        assertFalse(admission.canExecuteCrypto)
        assertFalse(admission.providerOperationReachable)
        assertFalse(admission.cryptoExecutionReachable)
        assertFalse(admission.currentNoopProviderOperationExecutionPresent)
        assertFalse(admission.currentRealProviderOperationExecutionPresent)
        assertFalse(admission.currentCryptoExecutionPresent)
    }

    @Test
    fun admissionCannotParticipateInVaultLifecyclePersistenceOrProductionSync() {
        val admission = admission()

        assertFalse(admission.canUseForVaultLifecycle)
        assertFalse(admission.canUseForPersistence)
        assertFalse(admission.canUseForSync)
        assertFalse(admission.vaultLifecycleReachable)
        assertFalse(admission.persistenceReachable)
        assertFalse(admission.productionSyncReachable)
    }

    @Test
    fun admissionCannotSignBroadcastOrEnableMainnet() {
        val admission = admission()

        assertFalse(admission.canUseForSigning)
        assertFalse(admission.canUseForBroadcasting)
        assertFalse(admission.canUseForMainnet)
        assertFalse(admission.signingBroadcastingReachable)
        assertFalse(admission.mainnetReachable)
        assertFalse(admission.mainnetPermitted)
    }

    @Test
    fun admissionDoesNotExposeRawKatVectorsRuntimeReferencesRawIdsOrPayloadTermsInToString() {
        val admission = admission()
        val outputs = listOf(
            admission.toString(),
            admission.displayLabel.toString(),
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
            "path",
            "descriptor",
            "source location",
            "implementation payload",
            "diagnostic payload",
            "trace payload",
            "public vector bytes",
            "public vector hex",
            "KAT vector bytes",
            "KAT vector hex",
            "fingerprint",
            "secret hash",
            "crash report",
            "analytics",
            "support export",
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
    fun syntheticTraceAdmissionAndUpstreamNoopBoundaryChainAreCommonTestOnlyBySourceGuardContract() {
        val admission = admission()

        assertTrue(admission.admissionIsCommonTestOnly)
        assertTrue(admission.futureSyntheticTraceCriteriaModeled)
        assertTrue(admission.expectedSafeIdMatched)
        assertTrue(admission.expectedFixtureIdMatched)
        assertTrue(admission.expectedVectorIdMatched)
        assertTrue(admission.expectedCaseIdMatched)
        assertTrue(admission.expectedProviderOperationMetadataKatIdMatched)
        assertTrue(admission.expectedProviderOperationNoopKatIdMatched)
        assertTrue(admission.expectedProviderOperationNoopExecutionBoundaryIdMatched)
        assertTrue(admission.expectedProviderOperationNoopExecutionBoundarySuiteReportIdMatched)
        assertTrue(admission.expectedProviderOperationSyntheticTraceIdMatched)
        assertFalse(admission.currentSyntheticTracePresent)
        assertFalse(admission.currentTracePayloadPresent)
        assertFalse(admission.admissionIsProviderOperationAuthorization)
        assertFalse(admission.realProviderOperationExecutionPermitted)
        assertFalse(admission.currentRealProviderOperationExecutionPresent)
        assertFalse(admission.currentCryptoExecutionPresent)
    }

    private fun admission() =
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceAdmissionPolicy
            .currentProviderOperationSyntheticTraceAdmission()

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
