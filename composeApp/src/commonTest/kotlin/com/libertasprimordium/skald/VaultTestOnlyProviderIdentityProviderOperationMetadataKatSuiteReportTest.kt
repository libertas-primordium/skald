package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatSuiteReportPolicy
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityProviderOperationMetadataKatSuiteReportTest {
    @Test
    fun providerOperationMetadataKatSuiteReportExistsOnlyAsCommonTestSuiteEvidence() {
        val report = report()

        assertTrue(report.suiteIsCommonTestOnly)
        assertTrue(report.suiteReportGenerated)
        assertTrue(report.providerOperationMetadataKatSuitePassed)
        assertFalse(report.productionProviderSelectable)
    }

    @Test
    fun suiteReportReadsExistingProviderOperationMetadataKatResultAndValidationReport() {
        val report = report()

        assertEquals(1, report.providerOperationMetadataKatCount)
        assertEquals(1, report.providerOperationMetadataKatValidationCount)
        assertTrue(report.providerOperationMetadataKatEvaluated)
        assertTrue(report.providerOperationMetadataKatPassed)
        assertTrue(report.providerOperationMetadataKatValidationPassed)
    }

    @Test
    fun suiteReportConfirmsProviderOperationMetadataKatValidationAndAdmissionEvidenceFlagsAreTrue() {
        val report = report()

        assertTrue(report.providerOperationMetadataKatEvaluated)
        assertTrue(report.providerOperationMetadataKatPassed)
        assertTrue(report.providerOperationMetadataKatValidationPassed)
        assertTrue(report.metadataKatSuitePassed)
        assertTrue(report.providerOperationAdmissionModeled)
    }

    @Test
    fun suiteReportConfirmsExactlyOneProviderOperationShapedMetadataCase() {
        val report = report()

        assertEquals(1, report.markerCount)
        assertEquals(1, report.fixtureRowCount)
        assertEquals(1, report.publicVectorRowCount)
        assertEquals(1, report.caseBindingCount)
        assertEquals(1, report.executableMetadataKatSuiteReportCount)
        assertEquals(1, report.providerOperationKatAdmissionCount)
        assertEquals(1, report.providerOperationMetadataKatCount)
        assertEquals(1, report.providerOperationMetadataKatValidationCount)
    }

    @Test
    fun suiteReportConfirmsExpectedMarkerFixtureVectorCaseAndProviderOperationMetadataKatIds() {
        val report = report()

        assertTrue(report.expectedSafeIdMatched)
        assertTrue(report.expectedFixtureIdMatched)
        assertTrue(report.expectedVectorIdMatched)
        assertTrue(report.expectedCaseIdMatched)
        assertTrue(report.expectedProviderOperationMetadataKatIdMatched)
    }

    @Test
    fun suiteReportGeneratedTrueIsCommonTestOnlySuiteEvidenceOnly() {
        val report = report()

        assertTrue(report.suiteReportGenerated)
        assertTrue(report.suiteIsCommonTestOnly)
        assertFalse(report.suiteIsProductionAuthorization)
        assertFalse(report.suiteIsProviderSelectionAuthorization)
        assertFalse(report.suiteIsProviderOperationAuthorization)
    }

    @Test
    fun providerOperationMetadataKatSuitePassedTrueIsCommonTestOnlySuiteEvidenceOnly() {
        val report = report()

        assertTrue(report.providerOperationMetadataKatSuitePassed)
        assertTrue(report.suiteIsCommonTestOnly)
        assertFalse(report.suiteIsKatExecutorAuthorization)
        assertFalse(report.suiteIsCryptoAuthorization)
        assertFalse(report.suiteIsVaultPersistenceAuthorization)
        assertFalse(report.suiteIsMainnetAuthorization)
    }

    @Test
    fun suiteReportIsNotProductionProviderSelectionProviderOperationKatExecutorCryptoVaultOrMainnetAuthorization() {
        val report = report()

        assertFalse(report.suiteIsProductionAuthorization)
        assertFalse(report.suiteIsProviderSelectionAuthorization)
        assertFalse(report.suiteIsProviderOperationAuthorization)
        assertFalse(report.suiteIsKatExecutorAuthorization)
        assertFalse(report.suiteIsCryptoAuthorization)
        assertFalse(report.suiteIsVaultPersistenceAuthorization)
        assertFalse(report.suiteIsMainnetAuthorization)
    }

    @Test
    fun suiteReportReportsProviderOperationShapeOnlyAndDoesNotReportExecution() {
        val report = report()

        assertTrue(report.suiteReportsProviderOperationShapeOnly)
        assertFalse(report.suiteReportsProviderOperationExecution)
        assertFalse(report.suiteReportsCryptoOperation)
        assertFalse(report.suiteReportsVaultLifecycle)
        assertFalse(report.suiteReportsPersistence)
    }

    @Test
    fun suiteReportDoesNotExposeExecutorRunnerProviderOperationCryptoOrVectorMaterial() {
        val report = report()

        assertFalse(report.providerOperationKatExecutorPresent)
        assertFalse(report.providerOperationKatRunnerPresent)
        assertFalse(report.providerOperationExecutionPresent)
        assertFalse(report.cryptoExecutionPresent)
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
        assertFalse(selection.safeDetail.contains(markerSafeId()))
        assertFalse(selection.safeDetail.contains(fixtureId()))
        assertFalse(selection.safeDetail.contains(vectorId()))
        assertFalse(selection.safeDetail.contains(caseId()))
        assertFalse(selection.safeDetail.contains(providerOperationMetadataKatId()))
        assertFalse(
            selection.safeDetail.contains(
                "SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatSuiteReport",
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
    fun suiteReportIsNotRegistryFactoryDispatcherExecutorOrProviderKatExecutorReachable() {
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
        assertFalse(report.providerOperationExecutionPresent)
        assertFalse(report.cryptoExecutionPresent)
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
    }

    @Test
    fun suiteReportCannotSignBroadcastOrEnableMainnet() {
        val report = report()

        assertFalse(report.canUseForSigning)
        assertFalse(report.canUseForBroadcasting)
        assertFalse(report.canUseForMainnet)
        assertFalse(report.signingBroadcastingReachable)
        assertFalse(report.mainnetReachable)
    }

    @Test
    fun suiteReportDoesNotExposeRawKatVectorsRuntimeReferencesRawIdsOrMaterialInToString() {
        val output = report().toString()
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

        assertFalse(output.contains(markerSafeId()))
        assertFalse(output.contains(fixtureId()))
        assertFalse(output.contains(vectorId()))
        assertFalse(output.contains(caseId()))
        assertFalse(output.contains(providerOperationMetadataKatId()))
        rejectedTerms.forEach { term ->
            assertFalse(output.contains(term, ignoreCase = true), "safe output leaked $term")
        }
    }

    @Test
    fun providerOperationMetadataKatSuiteReportClassChainAndIdsRemainRuntimeRootAbsentBySourceGuardContract() {
        val report = report()

        assertTrue(report.suiteIsCommonTestOnly)
        assertTrue(report.expectedSafeIdMatched)
        assertTrue(report.expectedFixtureIdMatched)
        assertTrue(report.expectedVectorIdMatched)
        assertTrue(report.expectedCaseIdMatched)
        assertTrue(report.expectedProviderOperationMetadataKatIdMatched)
        assertTrue(report.metadataKatSuitePassed)
        assertTrue(report.providerOperationAdmissionModeled)
        assertTrue(report.providerOperationMetadataKatEvaluated)
        assertTrue(report.providerOperationMetadataKatPassed)
        assertTrue(report.providerOperationMetadataKatValidationPassed)
        assertTrue(report.providerOperationMetadataKatSuitePassed)
        assertFalse(report.suiteIsProviderOperationAuthorization)
        assertFalse(report.suiteReportsProviderOperationExecution)
        assertFalse(report.suiteReportsCryptoOperation)
    }

    private fun report() =
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatSuiteReportPolicy
            .currentProviderOperationMetadataKatSuiteReport()

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
}
