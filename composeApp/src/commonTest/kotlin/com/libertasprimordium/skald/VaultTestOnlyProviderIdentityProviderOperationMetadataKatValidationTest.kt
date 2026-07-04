package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationCheck
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationSourceSet
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityProviderOperationMetadataKatValidationTest {
    @Test
    fun providerOperationMetadataKatValidationReportExistsOnlyAsCommonTestValidationEvidence() {
        val report = report()

        assertTrue(report.validationIsCommonTestOnly)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationSourceSet.CommonTest,
            report.sourceSet,
        )
        assertTrue(report.allValidationChecksPassed)
        assertFalse(report.productionProviderSelectable)
    }

    @Test
    fun validationReportReadsExistingProviderOperationMetadataKatResultAndProviderOperationKatAdmissionGate() {
        val report = report()

        assertEquals(1, report.providerOperationMetadataKatCount)
        assertEquals(1, report.providerOperationKatAdmissionCount)
        assertTrue(report.providerOperationMetadataKatEvaluated)
        assertTrue(report.providerOperationMetadataKatPassed)
        assertTrue(report.providerOperationAdmissionModeled)
    }

    @Test
    fun validationReportConfirmsProviderOperationMetadataKatEvidenceFlagsAreTrue() {
        val report = report()

        assertTrue(report.providerOperationMetadataKatEvaluated)
        assertTrue(report.providerOperationMetadataKatPassed)
        assertTrue(report.metadataKatSuitePassed)
        assertTrue(report.providerOperationAdmissionModeled)
    }

    @Test
    fun validationReportConfirmsExactlyOneProviderOperationShapedMetadataCase() {
        val report = report()

        assertEquals(1, report.markerCount)
        assertEquals(1, report.fixtureRowCount)
        assertEquals(1, report.publicVectorRowCount)
        assertEquals(1, report.caseBindingCount)
        assertEquals(1, report.executableMetadataKatSuiteReportCount)
        assertEquals(1, report.providerOperationKatAdmissionCount)
        assertEquals(1, report.providerOperationMetadataKatCount)
    }

    @Test
    fun validationReportConfirmsExpectedMarkerFixtureVectorCaseAndProviderOperationMetadataKatIds() {
        val report = report()

        assertTrue(report.expectedSafeIdMatched)
        assertTrue(report.expectedFixtureIdMatched)
        assertTrue(report.expectedVectorIdMatched)
        assertTrue(report.expectedCaseIdMatched)
        assertTrue(report.expectedProviderOperationMetadataKatIdMatched)
    }

    @Test
    fun allValidationChecksAreRepresentedExactlyOnce() {
        val rows = report().validationCheckRows
        val checks = rows.map { row -> row.check }

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationCheck.entries.size,
            rows.size,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationCheck.entries.toSet(),
            checks.toSet(),
        )
        assertTrue(rows.all { row -> rows.count { candidate -> candidate.check == row.check } == 1 })
        assertTrue(rows.all { row -> row.passed })
        assertTrue(rows.all { row -> !row.authorizesRuntimeUse })
    }

    @Test
    fun allValidationChecksPassedTrueIsCommonTestOnlyValidationEvidenceOnly() {
        val report = report()

        assertTrue(report.allValidationChecksPassed)
        assertTrue(report.validationIsCommonTestOnly)
        assertFalse(report.validationIsProductionAuthorization)
        assertFalse(report.validationIsProviderSelectionAuthorization)
        assertFalse(report.validationIsProviderOperationAuthorization)
    }

    @Test
    fun validationReportIsNotProductionProviderSelectionProviderOperationKatExecutorCryptoVaultOrMainnetAuthorization() {
        val report = report()

        assertFalse(report.validationIsProductionAuthorization)
        assertFalse(report.validationIsProviderSelectionAuthorization)
        assertFalse(report.validationIsProviderOperationAuthorization)
        assertFalse(report.validationIsKatExecutorAuthorization)
        assertFalse(report.validationIsCryptoAuthorization)
        assertFalse(report.validationIsVaultPersistenceAuthorization)
        assertFalse(report.validationIsMainnetAuthorization)
    }

    @Test
    fun validationReportValidatesProviderOperationShapeOnlyAndDoesNotValidateExecution() {
        val report = report()

        assertTrue(report.validatesProviderOperationShapeOnly)
        assertFalse(report.validatesProviderOperationExecution)
        assertFalse(report.validatesCryptoOperation)
        assertFalse(report.validatesVaultLifecycle)
        assertFalse(report.validatesPersistence)
    }

    @Test
    fun validationReportDoesNotExposeExecutorRunnerProviderOperationCryptoOrVectorMaterial() {
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
        assertFalse(selection.safeDetail.contains(providerOperationMetadataKatId()))
        assertFalse(
            selection.safeDetail.contains(
                "SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidation",
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
            report.validationIsProductionAuthorization,
            report.validationIsProviderSelectionAuthorization,
            report.validationIsProviderOperationAuthorization,
            report.validationIsKatExecutorAuthorization,
            report.validationIsCryptoAuthorization,
            report.validationIsVaultPersistenceAuthorization,
            report.validationIsMainnetAuthorization,
            report.validatesProviderOperationExecution,
            report.validatesCryptoOperation,
            report.validatesVaultLifecycle,
            report.validatesPersistence,
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
    fun validationReportIsNotRegistryFactoryDispatcherExecutorOrProviderKatExecutorReachable() {
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
        assertFalse(report.providerOperationExecutionPresent)
        assertFalse(report.cryptoExecutionPresent)
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
        assertFalse(report.signingBroadcastingReachable)
        assertFalse(report.mainnetReachable)
    }

    @Test
    fun validationReportDoesNotExposeRawKatVectorsRuntimeReferencesRawIdsOrMaterialInToString() {
        val report = report()
        val outputs = listOf(
            report.toString(),
            report.displayLabel.toString(),
            report.validationCheckRows.joinToString { row -> row.label.toString() },
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
            rejectedTerms.forEach { term ->
                assertFalse(output.contains(term, ignoreCase = true), "safe output leaked $term")
            }
        }
    }

    @Test
    fun providerOperationMetadataKatValidationClassChainAndIdsRemainRuntimeRootAbsentBySourceGuardContract() {
        val report = report()

        assertTrue(report.validationIsCommonTestOnly)
        assertTrue(report.expectedSafeIdMatched)
        assertTrue(report.expectedFixtureIdMatched)
        assertTrue(report.expectedVectorIdMatched)
        assertTrue(report.expectedCaseIdMatched)
        assertTrue(report.expectedProviderOperationMetadataKatIdMatched)
        assertTrue(report.metadataKatSuitePassed)
        assertTrue(report.providerOperationAdmissionModeled)
        assertTrue(report.providerOperationMetadataKatEvaluated)
        assertTrue(report.providerOperationMetadataKatPassed)
        assertTrue(report.allValidationChecksPassed)
        assertFalse(report.validationIsProviderOperationAuthorization)
        assertFalse(report.validatesProviderOperationExecution)
        assertFalse(report.validatesCryptoOperation)
    }

    private fun report() =
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatValidationPolicy
            .currentProviderOperationMetadataKatValidationReport()

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
