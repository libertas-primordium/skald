package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundarySuiteReportPolicy
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityProviderOperationNoopExecutionBoundarySuiteReportTest {
    @Test
    fun noopProviderOperationExecutionBoundarySuiteReportExistsOnlyAsCommonTestSuiteEvidence() {
        val report = report()

        assertTrue(report.suiteIsCommonTestOnly)
        assertTrue(report.suiteReportGenerated)
        assertTrue(report.noopExecutionBoundarySuitePassed)
        assertFalse(report.productionProviderSelectable)
    }

    @Test
    fun suiteReportReadsNoopExecutionBoundaryAndBoundaryValidationReport() {
        val report = report()

        assertEquals(1, report.markerCount)
        assertEquals(1, report.caseBindingCount)
        assertEquals(1, report.providerOperationMetadataKatCount)
        assertEquals(1, report.providerOperationMetadataKatValidationCount)
        assertEquals(1, report.providerOperationMetadataKatSuiteReportCount)
        assertEquals(1, report.providerOperationNoopKatAdmissionCount)
        assertEquals(1, report.providerOperationNoopKatCount)
        assertEquals(1, report.providerOperationNoopKatValidationCount)
        assertEquals(1, report.providerOperationNoopKatSuiteReportCount)
        assertEquals(1, report.providerOperationNoopExecutionBoundaryCount)
        assertEquals(1, report.providerOperationNoopExecutionBoundaryValidationCount)
    }

    @Test
    fun suiteReportConfirmsBoundaryValidationNoopKatSuiteAndSyntheticNoopEvidence() {
        val report = report()

        assertTrue(report.noopExecutionBoundaryModeled)
        assertTrue(report.syntheticNoopEvaluationPermitted)
        assertTrue(report.noopExecutionBoundaryValidationPassed)
        assertTrue(report.providerOperationNoopKatPassed)
        assertTrue(report.providerOperationNoopKatValidationPassed)
        assertTrue(report.providerOperationNoopKatSuitePassed)
        assertTrue(report.syntheticNoopResultPresent)
    }

    @Test
    fun suiteReportConfirmsExpectedMarkerFixtureVectorCaseKatBoundaryAndSuiteIds() {
        val report = report()

        assertTrue(report.expectedSafeIdMatched)
        assertTrue(report.expectedFixtureIdMatched)
        assertTrue(report.expectedVectorIdMatched)
        assertTrue(report.expectedCaseIdMatched)
        assertTrue(report.expectedProviderOperationMetadataKatIdMatched)
        assertTrue(report.expectedProviderOperationNoopKatIdMatched)
        assertTrue(report.expectedProviderOperationNoopExecutionBoundaryIdMatched)
        assertTrue(report.expectedProviderOperationNoopExecutionBoundarySuiteReportIdMatched)
    }

    @Test
    fun suiteEvidenceFlagsAreCommonTestOnlyAndNonAuthorizing() {
        val report = report()

        assertTrue(report.suiteReportGenerated)
        assertTrue(report.noopExecutionBoundarySuitePassed)
        assertTrue(report.suiteIsCommonTestOnly)
        assertFalse(report.suiteIsProductionAuthorization)
        assertFalse(report.suiteIsProviderSelectionAuthorization)
        assertFalse(report.suiteIsProviderOperationAuthorization)
        assertFalse(report.suiteIsKatExecutorAuthorization)
        assertFalse(report.suiteIsCryptoAuthorization)
        assertFalse(report.suiteIsVaultPersistenceAuthorization)
        assertFalse(report.suiteIsMainnetAuthorization)
    }

    @Test
    fun suiteReportReportsSyntheticNoopOnlyAndNoRuntimeOperations() {
        val report = report()

        assertTrue(report.suiteReportsSyntheticNoopOnly)
        assertFalse(report.suiteReportsProviderOperationExecution)
        assertFalse(report.suiteReportsCryptoOperation)
        assertFalse(report.suiteReportsVaultLifecycle)
        assertFalse(report.suiteReportsPersistence)
        assertFalse(report.providerOperationKatExecutorPresent)
        assertFalse(report.providerOperationKatRunnerPresent)
        assertFalse(report.providerOperationExecutionPresent)
        assertFalse(report.cryptoExecutionPresent)
    }

    @Test
    fun suiteReportDoesNotPermitRealProviderOperationCryptoKatRunnerKatExecutorVaultPersistenceOrMainnet() {
        val report = report()

        assertFalse(report.realProviderOperationExecutionPermitted)
        assertFalse(report.cryptoExecutionPermitted)
        assertFalse(report.katRunnerPermitted)
        assertFalse(report.katExecutorPermitted)
        assertFalse(report.vaultPersistencePermitted)
        assertFalse(report.mainnetPermitted)
    }

    @Test
    fun suiteReportHasNoRawVectorOrPublicVectorByteOrHexMaterial() {
        val report = report()

        assertFalse(report.rawKatMaterialPresent)
        assertFalse(report.rawVectorBytesPresent)
        assertFalse(report.rawVectorHexPresent)
        assertFalse(report.publicVectorBytesPresent)
        assertFalse(report.publicVectorHexPresent)
    }

    @Test
    fun suiteReportDoesNotImplementOrExposeVaultCryptoProvider() {
        val report = report()

        assertFalse(report.implementsVaultCryptoProvider)
        assertFalse(report.containsVaultCryptoProvider)
    }

    @Test
    fun suiteReportIsNotSelectedByVaultCryptoProviderSelectionRegistry() {
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
        assertFalse(
            selection.safeDetail.contains(
                "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundarySuiteReport",
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
        val report = report()

        assertFalse(report.productionProviderSelectable)
        assertFalse(VaultCryptoProviderSelectionRegistry.select().productionProviderSelectable)
    }

    @Test
    fun everyCapabilityAndReachabilityBooleanRemainsFalse() {
        val report = report()
        val blockedBooleans = listOf(
            report.runtimeSelectable,
            report.registrySelectable,
            report.factoryReachable,
            report.dispatcherReachable,
            report.executorTargetable,
            report.providerKatExecutorReachable,
            report.providerOperationReachable,
            report.cryptoExecutionReachable,
            report.vaultLifecycleReachable,
            report.persistenceReachable,
            report.productionSyncReachable,
            report.backendClientReachable,
            report.bdkWalletStateReachable,
            report.settingsCodecReachable,
            report.uiSurfaceReachable,
            report.signingBroadcastingReachable,
            report.publicEndpointReachable,
            report.mainnetReachable,
            report.implementsVaultCryptoProvider,
            report.containsVaultCryptoProvider,
            report.canExecuteProviderOperations,
            report.canExecuteCrypto,
            report.canUseForVaultLifecycle,
            report.canUseForPersistence,
            report.canUseForSync,
            report.canUseForSigning,
            report.canUseForBroadcasting,
            report.canUseForMainnet,
            report.productionProviderSelectable,
            report.suiteIsProductionAuthorization,
            report.suiteIsProviderSelectionAuthorization,
            report.suiteIsProviderOperationAuthorization,
            report.suiteIsKatExecutorAuthorization,
            report.suiteIsCryptoAuthorization,
            report.suiteIsVaultPersistenceAuthorization,
            report.suiteIsMainnetAuthorization,
            report.realProviderOperationExecutionPermitted,
            report.cryptoExecutionPermitted,
            report.katRunnerPermitted,
            report.katExecutorPermitted,
            report.vaultPersistencePermitted,
            report.mainnetPermitted,
            report.suiteReportsProviderOperationExecution,
            report.suiteReportsCryptoOperation,
            report.suiteReportsVaultLifecycle,
            report.suiteReportsPersistence,
            report.providerOperationKatExecutorPresent,
            report.providerOperationKatRunnerPresent,
            report.providerOperationExecutionPresent,
            report.cryptoExecutionPresent,
            report.rawKatMaterialPresent,
            report.rawVectorBytesPresent,
            report.rawVectorHexPresent,
            report.publicVectorBytesPresent,
            report.publicVectorHexPresent,
        )

        assertTrue(blockedBooleans.none { enabled -> enabled })
    }

    @Test
    fun suiteReportIsNotRegistryFactoryDispatcherExecutorOrKatExecutorReachable() {
        val report = report()

        assertFalse(report.registrySelectable)
        assertFalse(report.factoryReachable)
        assertFalse(report.dispatcherReachable)
        assertFalse(report.executorTargetable)
        assertFalse(report.providerKatExecutorReachable)
    }

    @Test
    fun suiteReportCannotExecuteProviderOperationsOrCrypto() {
        val report = report()

        assertFalse(report.canExecuteProviderOperations)
        assertFalse(report.canExecuteCrypto)
        assertFalse(report.providerOperationReachable)
        assertFalse(report.cryptoExecutionReachable)
        assertFalse(report.providerOperationExecutionPresent)
        assertFalse(report.cryptoExecutionPresent)
        assertFalse(report.realProviderOperationExecutionPermitted)
        assertFalse(report.cryptoExecutionPermitted)
    }

    @Test
    fun suiteReportCannotParticipateInVaultLifecyclePersistenceOrProductionSync() {
        val report = report()

        assertFalse(report.canUseForVaultLifecycle)
        assertFalse(report.canUseForPersistence)
        assertFalse(report.canUseForSync)
        assertFalse(report.vaultLifecycleReachable)
        assertFalse(report.persistenceReachable)
        assertFalse(report.productionSyncReachable)
        assertFalse(report.vaultPersistencePermitted)
    }

    @Test
    fun suiteReportCannotSignBroadcastOrEnableMainnet() {
        val report = report()

        assertFalse(report.canUseForSigning)
        assertFalse(report.canUseForBroadcasting)
        assertFalse(report.canUseForMainnet)
        assertFalse(report.signingBroadcastingReachable)
        assertFalse(report.mainnetReachable)
        assertFalse(report.mainnetPermitted)
    }

    @Test
    fun suiteReportDoesNotExposeRawKatVectorsRuntimeReferencesRawIdsOrMaterialInToString() {
        val report = report()
        val outputs = listOf(
            report.toString(),
            report.displayLabel.toString(),
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
            rejectedTerms.forEach { term ->
                assertFalse(output.contains(term, ignoreCase = true), "safe output leaked $term")
            }
        }
    }

    @Test
    fun noopProviderOperationExecutionBoundarySuiteReportClassAndUpstreamChainAreCommonTestOnlyBySourceGuardContract() {
        val report = report()

        assertTrue(report.suiteIsCommonTestOnly)
        assertTrue(report.expectedSafeIdMatched)
        assertTrue(report.expectedFixtureIdMatched)
        assertTrue(report.expectedVectorIdMatched)
        assertTrue(report.expectedCaseIdMatched)
        assertTrue(report.expectedProviderOperationMetadataKatIdMatched)
        assertTrue(report.expectedProviderOperationNoopKatIdMatched)
        assertTrue(report.expectedProviderOperationNoopExecutionBoundaryIdMatched)
        assertTrue(report.expectedProviderOperationNoopExecutionBoundarySuiteReportIdMatched)
        assertTrue(report.providerOperationNoopKatPassed)
        assertTrue(report.providerOperationNoopKatValidationPassed)
        assertTrue(report.providerOperationNoopKatSuitePassed)
        assertTrue(report.syntheticNoopResultPresent)
        assertTrue(report.noopExecutionBoundaryModeled)
        assertTrue(report.syntheticNoopEvaluationPermitted)
        assertTrue(report.noopExecutionBoundaryValidationPassed)
        assertTrue(report.noopExecutionBoundarySuitePassed)
        assertFalse(report.suiteIsProviderOperationAuthorization)
        assertFalse(report.realProviderOperationExecutionPermitted)
        assertFalse(report.providerOperationExecutionPresent)
        assertFalse(report.cryptoExecutionPresent)
    }

    private fun report() =
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundarySuiteReportPolicy
            .currentProviderOperationNoopExecutionBoundarySuiteReport()

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
}
