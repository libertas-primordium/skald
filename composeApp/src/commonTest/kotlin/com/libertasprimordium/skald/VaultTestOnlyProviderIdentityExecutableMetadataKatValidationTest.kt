package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationCheck
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationSourceSet
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityExecutableMetadataKatValidationTest {
    @Test
    fun executableMetadataKatValidationReportExistsOnlyAsCommonTestValidationEvidence() {
        val report = report()

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationSourceSet.CommonTest,
            report.sourceSet,
        )
        assertTrue(report.validationIsCommonTestOnly)
        assertTrue(report.allValidationChecksPassed)
        assertFalse(report.productionProviderSelectable)
        assertFalse(report.validationIsProductionAuthorization)
    }

    @Test
    fun validationReportReadsExistingExecutableMetadataKatResultAndExecutableKatAdmissionGate() {
        val report = report()

        assertEquals(1, report.executableMetadataKatCount)
        assertEquals(1, report.executableKatAdmissionCount)
        assertTrue(report.metadataKatEvaluated)
        assertTrue(report.metadataKatPassed)
    }

    @Test
    fun validationReportConfirmsMetadataKatEvaluatedTrue() {
        assertTrue(report().metadataKatEvaluated)
    }

    @Test
    fun validationReportConfirmsMetadataKatPassedTrue() {
        assertTrue(report().metadataKatPassed)
    }

    @Test
    fun validationReportConfirmsExactlyOneMetadataCaseBinding() {
        val report = report()

        assertEquals(1, report.markerCount)
        assertEquals(1, report.fixtureRowCount)
        assertEquals(1, report.publicVectorRowCount)
        assertEquals(1, report.caseBindingCount)
    }

    @Test
    fun validationReportConfirmsExpectedMarkerFixtureVectorAndCaseIds() {
        val report = report()

        assertTrue(report.expectedSafeIdMatched)
        assertTrue(report.expectedFixtureIdMatched)
        assertTrue(report.expectedVectorIdMatched)
        assertTrue(report.expectedCaseIdMatched)
    }

    @Test
    fun allValidationChecksAreRepresentedExactlyOnce() {
        val rows = report().validationCheckRows

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationCheck.entries.size,
            rows.size,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationCheck.entries.toSet(),
            rows.map { row -> row.check }.toSet(),
        )
        assertTrue(rows.all { row -> row.passed })
        assertTrue(rows.none { row -> row.authorizesRuntimeUse })
    }

    @Test
    fun allValidationChecksPassedTrueIsNonAuthorizingCommonTestValidationEvidenceOnly() {
        val report = report()

        assertTrue(report.allValidationChecksPassed)
        assertTrue(report.validationIsCommonTestOnly)
        assertFalse(report.validationIsProductionAuthorization)
        assertFalse(report.validationIsProviderSelectionAuthorization)
        assertFalse(report.validationIsKatExecutionAuthorization)
    }

    @Test
    fun validationReportIsNotProductionProviderSelectionKatCryptoVaultPersistenceOrMainnetAuthorization() {
        val report = report()

        assertFalse(report.validationIsProductionAuthorization)
        assertFalse(report.validationIsProviderSelectionAuthorization)
        assertFalse(report.validationIsKatExecutionAuthorization)
        assertFalse(report.validationIsCryptoAuthorization)
        assertFalse(report.validationIsVaultPersistenceAuthorization)
        assertFalse(report.validationIsMainnetAuthorization)
    }

    @Test
    fun validationReportValidatesMetadataAndPublicNonSecretTextOnlyVectorOnly() {
        val report = report()

        assertTrue(report.validatesMetadataOnly)
        assertTrue(report.validatesPublicNonSecretTextOnlyVector)
        assertFalse(report.validatesProviderOperation)
        assertFalse(report.validatesCryptoOperation)
        assertFalse(report.validatesVaultLifecycle)
        assertFalse(report.validatesPersistence)
    }

    @Test
    fun validationReportHasNoRawVectorMaterialRunnerOrExecutor() {
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
    fun validationReportDoesNotImplementOrExposeVaultCryptoProvider() {
        val report = report()

        assertFalse(report.implementsVaultCryptoProvider)
        assertFalse(report.containsVaultCryptoProvider)
    }

    @Test
    fun validationReportIsNotSelectedByVaultCryptoProviderSelectionRegistry() {
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertIs<DisabledVaultCryptoProvider>(selection.selectedProvider)
        assertFalse(selection.safeDetail.contains(markerSafeId()))
        assertFalse(selection.safeDetail.contains(fixtureId()))
        assertFalse(selection.safeDetail.contains(vectorId()))
        assertFalse(selection.safeDetail.contains(caseId()))
        assertFalse(
            selection.safeDetail.contains(
                "SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidation",
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
            report.validationIsProductionAuthorization,
            report.validationIsProviderSelectionAuthorization,
            report.validationIsKatExecutionAuthorization,
            report.validationIsCryptoAuthorization,
            report.validationIsVaultPersistenceAuthorization,
            report.validationIsMainnetAuthorization,
            report.validatesProviderOperation,
            report.validatesCryptoOperation,
            report.validatesVaultLifecycle,
            report.validatesPersistence,
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
    fun validationReportIsNotRegistryFactoryDispatcherExecutorOrKatExecutorReachable() {
        val report = report()

        assertFalse(report.registrySelectable)
        assertFalse(report.factoryReachable)
        assertFalse(report.dispatcherReachable)
        assertFalse(report.executorTargetable)
        assertFalse(report.providerKatExecutorReachable)
    }

    @Test
    fun validationReportCannotExecuteProviderOperationsOrCrypto() {
        val report = report()

        assertFalse(report.canExecuteProviderOperations)
        assertFalse(report.canExecuteCrypto)
        assertFalse(report.providerOperationExecutionReachable)
        assertFalse(report.cryptoExecutionReachable)
    }

    @Test
    fun validationReportCannotParticipateInVaultLifecyclePersistenceOrProductionSync() {
        val report = report()

        assertFalse(report.canUseForVaultLifecycle)
        assertFalse(report.canUseForPersistence)
        assertFalse(report.canUseForSync)
        assertFalse(report.vaultLifecycleReachable)
        assertFalse(report.persistenceReachable)
        assertFalse(report.productionSyncReachable)
    }

    @Test
    fun validationReportCannotSignBroadcastOrEnableMainnet() {
        val report = report()

        assertFalse(report.canUseForSigning)
        assertFalse(report.canUseForBroadcasting)
        assertFalse(report.canUseForMainnet)
        assertFalse(report.mainnetReachable)
    }

    @Test
    fun validationReportDoesNotExposeRawKatVectorsRuntimeReferencesRawIdsOrMaterialInToString() {
        val report = report()
        val outputs = listOf(
            report.toString(),
            report.displayLabel.toString(),
            report.validationCheckRows.first().toString(),
            report.validationCheckRows.first().label.toString(),
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
            rejectedTerms.forEach { term ->
                assertFalse(output.contains(term, ignoreCase = true), "safe output leaked $term")
            }
        }
    }

    @Test
    fun executableMetadataKatValidationAndIdsAreCommonTestOnlyBySourceGuardContract() {
        val report = report()

        assertTrue(report.validationIsCommonTestOnly)
        assertTrue(report.expectedSafeIdMatched)
        assertTrue(report.expectedFixtureIdMatched)
        assertTrue(report.expectedVectorIdMatched)
        assertTrue(report.expectedCaseIdMatched)
        assertTrue(report.metadataKatEvaluated)
        assertTrue(report.metadataKatPassed)
        assertTrue(
            report.validationCheckRows.single {
                it.check ==
                    SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationCheck
                        .ProductionRuntimeSourceAbsenceSatisfied
            }.passed,
        )
        assertFalse(report.validationIsKatExecutionAuthorization)
        assertFalse(report.validatesProviderOperation)
        assertFalse(report.validatesCryptoOperation)
    }

    private fun report() =
        SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatValidationPolicy
            .currentExecutableMetadataKatValidationReport()

    private fun markerSafeId(): String =
        "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"

    private fun fixtureId(): String =
        "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata"

    private fun vectorId(): String =
        "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata"

    private fun caseId(): String =
        "skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata"
}
