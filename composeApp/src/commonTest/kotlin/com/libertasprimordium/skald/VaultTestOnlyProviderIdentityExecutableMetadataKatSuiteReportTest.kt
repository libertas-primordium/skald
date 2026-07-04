package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatSuiteReportPolicy
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityExecutableMetadataKatSuiteReportTest {
    @Test
    fun executableMetadataKatSuiteReportExistsOnlyAsCommonTestReportEvidence() {
        val report = report()

        assertTrue(report.suiteIsCommonTestOnly)
        assertTrue(report.suiteReportGenerated)
        assertTrue(report.suitePassed)
        assertFalse(report.productionProviderSelectable)
        assertFalse(report.suiteIsProductionAuthorization)
    }

    @Test
    fun suiteReportReadsExistingExecutableMetadataKatResultAndValidationReport() {
        val report = report()

        assertEquals(1, report.executableMetadataKatCount)
        assertEquals(1, report.executableMetadataKatValidationCount)
        assertTrue(report.metadataKatEvaluated)
        assertTrue(report.metadataKatPassed)
        assertTrue(report.executableMetadataKatValidationPassed)
    }

    @Test
    fun suiteReportConfirmsMetadataKatEvaluatedTrue() {
        assertTrue(report().metadataKatEvaluated)
    }

    @Test
    fun suiteReportConfirmsMetadataKatPassedTrue() {
        assertTrue(report().metadataKatPassed)
    }

    @Test
    fun suiteReportConfirmsExecutableMetadataKatValidationPassedTrue() {
        assertTrue(report().executableMetadataKatValidationPassed)
    }

    @Test
    fun suiteReportConfirmsExactlyOneMetadataCaseBinding() {
        val report = report()

        assertEquals(1, report.markerCount)
        assertEquals(1, report.fixtureRowCount)
        assertEquals(1, report.publicVectorRowCount)
        assertEquals(1, report.caseBindingCount)
        assertEquals(1, report.executableKatAdmissionCount)
    }

    @Test
    fun suiteReportConfirmsExpectedMarkerFixtureVectorAndCaseIds() {
        val report = report()

        assertTrue(report.expectedSafeIdMatched)
        assertTrue(report.expectedFixtureIdMatched)
        assertTrue(report.expectedVectorIdMatched)
        assertTrue(report.expectedCaseIdMatched)
    }

    @Test
    fun suiteReportGeneratedTrueIsNonAuthorizingCommonTestReportEvidenceOnly() {
        val report = report()

        assertTrue(report.suiteReportGenerated)
        assertTrue(report.suiteIsCommonTestOnly)
        assertFalse(report.suiteIsProductionAuthorization)
        assertFalse(report.suiteIsProviderSelectionAuthorization)
        assertFalse(report.suiteIsKatExecutionAuthorization)
    }

    @Test
    fun suitePassedTrueIsNonAuthorizingCommonTestReportEvidenceOnly() {
        val report = report()

        assertTrue(report.suitePassed)
        assertTrue(report.suiteIsCommonTestOnly)
        assertFalse(report.suiteIsCryptoAuthorization)
        assertFalse(report.suiteIsVaultPersistenceAuthorization)
        assertFalse(report.suiteIsMainnetAuthorization)
    }

    @Test
    fun suiteReportIsNotProductionProviderSelectionKatCryptoVaultPersistenceOrMainnetAuthorization() {
        val report = report()

        assertFalse(report.suiteIsProductionAuthorization)
        assertFalse(report.suiteIsProviderSelectionAuthorization)
        assertFalse(report.suiteIsKatExecutionAuthorization)
        assertFalse(report.suiteIsCryptoAuthorization)
        assertFalse(report.suiteIsVaultPersistenceAuthorization)
        assertFalse(report.suiteIsMainnetAuthorization)
    }

    @Test
    fun suiteReportReportsMetadataAndPublicNonSecretTextOnlyVectorOnly() {
        val report = report()

        assertTrue(report.suiteReportsMetadataOnly)
        assertTrue(report.suiteReportsPublicNonSecretTextOnlyVector)
        assertFalse(report.suiteReportsProviderOperation)
        assertFalse(report.suiteReportsCryptoOperation)
        assertFalse(report.suiteReportsVaultLifecycle)
        assertFalse(report.suiteReportsPersistence)
    }

    @Test
    fun suiteReportHasNoRawVectorMaterialRunnerOrExecutor() {
        val report = report()

        assertFalse(report.rawKatMaterialPresent)
        assertFalse(report.rawVectorBytesPresent)
        assertFalse(report.rawVectorHexPresent)
        assertFalse(report.publicVectorBytesPresent)
        assertFalse(report.publicVectorHexPresent)
        assertFalse(report.executableKatExecutorPresent)
        assertFalse(report.katRunnerPresent)
        assertFalse(report.katExecutorPresent)
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
        assertFalse(
            selection.safeDetail.contains(
                "SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatSuiteReport",
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
            report.providerOperationExecutionReachable,
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
            report.suiteIsKatExecutionAuthorization,
            report.suiteIsCryptoAuthorization,
            report.suiteIsVaultPersistenceAuthorization,
            report.suiteIsMainnetAuthorization,
            report.suiteReportsProviderOperation,
            report.suiteReportsCryptoOperation,
            report.suiteReportsVaultLifecycle,
            report.suiteReportsPersistence,
            report.rawKatMaterialPresent,
            report.rawVectorBytesPresent,
            report.rawVectorHexPresent,
            report.publicVectorBytesPresent,
            report.publicVectorHexPresent,
            report.executableKatExecutorPresent,
            report.katRunnerPresent,
            report.katExecutorPresent,
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
        assertFalse(report.providerOperationExecutionReachable)
        assertFalse(report.cryptoExecutionReachable)
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
        rejectedTerms.forEach { term ->
            assertFalse(output.contains(term, ignoreCase = true), "safe output leaked $term")
        }
    }

    @Test
    fun executableMetadataKatSuiteReportValidationMetadataKatAndIdsAreCommonTestOnlyBySourceGuardContract() {
        val report = report()

        assertTrue(report.suiteIsCommonTestOnly)
        assertTrue(report.expectedSafeIdMatched)
        assertTrue(report.expectedFixtureIdMatched)
        assertTrue(report.expectedVectorIdMatched)
        assertTrue(report.expectedCaseIdMatched)
        assertTrue(report.metadataKatEvaluated)
        assertTrue(report.metadataKatPassed)
        assertTrue(report.executableMetadataKatValidationPassed)
        assertTrue(report.suiteReportGenerated)
        assertTrue(report.suitePassed)
        assertFalse(report.suiteIsKatExecutionAuthorization)
        assertFalse(report.suiteReportsProviderOperation)
        assertFalse(report.suiteReportsCryptoOperation)
    }

    private fun report() =
        SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatSuiteReportPolicy
            .currentExecutableMetadataKatSuiteReport()

    private fun markerSafeId(): String =
        "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"

    private fun fixtureId(): String =
        "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata"

    private fun vectorId(): String =
        "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata"

    private fun caseId(): String =
        "skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata"
}
