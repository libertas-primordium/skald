package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationSourceSet
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityKatCaseBindingValidationTest {
    @Test
    fun katCaseBindingValidationReportExistsOnlyAsCommonTestInertValidationEvidence() {
        val report = report()

        assertTrue(report.validationIsCommonTestOnly)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationSourceSet.CommonTest,
            report.sourceSet,
        )
        assertTrue(report.allValidationChecksPassed)
        assertFalse(report.runtimeSelectable)
        assertFalse(report.productionProviderSelectable)
    }

    @Test
    fun katCaseBindingValidationReadsExistingCaseBindingVectorValidationFixtureCatalogScopeMarkerAndCapabilityMatrix() {
        val report = report()

        assertEquals(1, report.markerCount)
        assertEquals(1, report.fixtureRowCount)
        assertEquals(1, report.publicVectorRowCount)
        assertEquals(1, report.caseBindingCount)
        assertTrue(report.expectedSafeIdMatched)
        assertTrue(report.expectedFixtureIdMatched)
        assertTrue(report.expectedVectorIdMatched)
        assertTrue(report.expectedCaseIdMatched)
    }

    @Test
    fun katCaseBindingValidationConfirmsExactlyOneCaseBinding() {
        val report = report()

        assertEquals(1, report.caseBindingCount)
        assertTrue(
            checkPassed(
                SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck
                    .CaseBindingPresentExactlyOnce,
            ),
        )
    }

    @Test
    fun katCaseBindingValidationConfirmsExpectedMarkerSafeId() {
        val report = report()

        assertTrue(report.expectedSafeIdMatched)
        assertTrue(
            checkPassed(
                SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck
                    .CaseBindingReferencesExpectedMarkerSafeId,
            ),
        )
    }

    @Test
    fun katCaseBindingValidationConfirmsExpectedFixtureId() {
        val report = report()

        assertTrue(report.expectedFixtureIdMatched)
        assertTrue(
            checkPassed(
                SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck
                    .CaseBindingReferencesExpectedFixtureId,
            ),
        )
    }

    @Test
    fun katCaseBindingValidationConfirmsExpectedVectorId() {
        val report = report()

        assertTrue(report.expectedVectorIdMatched)
        assertTrue(
            checkPassed(
                SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck
                    .CaseBindingReferencesExpectedVectorId,
            ),
        )
    }

    @Test
    fun katCaseBindingValidationConfirmsExpectedCaseId() {
        val report = report()

        assertTrue(report.expectedCaseIdMatched)
        assertTrue(
            checkPassed(
                SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck.CaseBindingUsesExpectedCaseId,
            ),
        )
    }

    @Test
    fun allValidationChecksAreRepresentedExactlyOnce() {
        val checks = report().validationCheckRows.map { row -> row.check }

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck.entries.size, checks.size)
        assertEquals(SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck.entries.toSet(), checks.toSet())
        assertTrue(report().validationCheckRows.all { row -> row.passed && !row.authorizesRuntimeUse })
    }

    @Test
    fun allValidationChecksPassedIsNonAuthorizingCommonTestEvidenceOnly() {
        val report = report()

        assertTrue(report.allValidationChecksPassed)
        assertTrue(report.validationIsCommonTestOnly)
        assertFalse(report.validationIsProductionAuthorization)
        assertFalse(report.validationIsProviderSelectionAuthorization)
        assertFalse(report.validationIsKatExecutionAuthorization)
        assertFalse(report.validationIsCryptoAuthorization)
        assertFalse(report.validationIsVaultPersistenceAuthorization)
        assertFalse(report.validationIsMainnetAuthorization)
    }

    @Test
    fun katCaseBindingValidationIsNotProductionProviderSelectionKatCryptoVaultPersistenceOrMainnetAuthorization() {
        val report = report()

        assertFalse(report.validationIsProductionAuthorization)
        assertFalse(report.validationIsProviderSelectionAuthorization)
        assertFalse(report.validationIsKatExecutionAuthorization)
        assertFalse(report.validationIsCryptoAuthorization)
        assertFalse(report.validationIsVaultPersistenceAuthorization)
        assertFalse(report.validationIsMainnetAuthorization)
    }

    @Test
    fun katCaseBindingValidationConfirmsBindingIsMetadataOnlyAndNotExecutable() {
        val report = report()

        assertTrue(report.caseBindingIsMetadataOnly)
        assertFalse(report.caseBindingIsExecutable)
        assertTrue(
            checkPassed(
                SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck.CaseBindingIsMetadataOnly,
            ),
        )
        assertTrue(
            checkPassed(
                SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck.CaseBindingIsNotExecutable,
            ),
        )
    }

    @Test
    fun katCaseBindingValidationConfirmsBindingContainsNoRawBytesHexCryptoWalletEndpointOrProviderHandles() {
        val report = report()

        assertFalse(report.caseBindingContainsRawBytes)
        assertFalse(report.caseBindingContainsHex)
        assertFalse(report.caseBindingContainsCryptoMaterial)
        assertFalse(report.caseBindingContainsWalletMaterial)
        assertFalse(report.caseBindingContainsEndpointMaterial)
        assertFalse(report.caseBindingContainsProviderHandle)
        assertTrue(
            checkPassed(
                SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck.CaseBindingContainsNoRawBytes,
            ),
        )
        assertTrue(checkPassed(SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck.CaseBindingContainsNoHex))
        assertTrue(
            checkPassed(
                SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck
                    .CaseBindingContainsNoCryptoMaterial,
            ),
        )
        assertTrue(
            checkPassed(
                SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck
                    .CaseBindingContainsNoWalletMaterial,
            ),
        )
        assertTrue(
            checkPassed(
                SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck
                    .CaseBindingContainsNoEndpointMaterial,
            ),
        )
        assertTrue(
            checkPassed(
                SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck
                    .CaseBindingContainsNoProviderHandles,
            ),
        )
    }

    @Test
    fun katCaseBindingValidationHasNoRawKatVectorMaterialExecutableKatOrKatExecutor() {
        val report = report()

        assertFalse(report.rawKatMaterialPresent)
        assertFalse(report.rawVectorBytesPresent)
        assertFalse(report.rawVectorHexPresent)
        assertFalse(report.publicVectorBytesPresent)
        assertFalse(report.publicVectorHexPresent)
        assertFalse(report.executableKatPresent)
        assertFalse(report.katExecutorPresent)
    }

    @Test
    fun katCaseBindingValidationDoesNotImplementOrExposeVaultCryptoProvider() {
        val report = report()

        assertFalse(report.implementsVaultCryptoProvider)
        assertFalse(report.containsVaultCryptoProvider)
    }

    @Test
    fun katCaseBindingValidationIsNotSelectedByVaultCryptoProviderSelectionRegistry() {
        val result = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, result.selectedCandidateId)
        assertIs<DisabledVaultCryptoProvider>(result.selectedProvider)
        assertFalse(result.safeDetail.contains(markerSafeId()))
        assertFalse(result.safeDetail.contains(fixtureId()))
        assertFalse(result.safeDetail.contains(vectorId()))
        assertFalse(result.safeDetail.contains(caseId()))
        assertFalse(result.safeDetail.contains("SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidation"))
    }

    @Test
    fun providerSelectionRemainsDisabledProviderOnly() {
        val result = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, result.selectedCandidateId)
        assertTrue(result.selectedProviderIsDisabled)
        assertIs<DisabledVaultCryptoProvider>(result.selectedProvider)
        assertFalse(result.productionProviderSelectable)
    }

    @Test
    fun productionProviderSelectableRemainsFalse() {
        val report = report()

        assertFalse(report.productionProviderSelectable)
        assertFalse(VaultCryptoProviderSelectionRegistry.select().productionProviderSelectable)
        assertTrue(
            checkPassed(
                SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck
                    .ProductionProviderSelectableRemainsFalse,
            ),
        )
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
            report.caseBindingIsExecutable,
            report.caseBindingContainsRawBytes,
            report.caseBindingContainsHex,
            report.caseBindingContainsCryptoMaterial,
            report.caseBindingContainsWalletMaterial,
            report.caseBindingContainsEndpointMaterial,
            report.caseBindingContainsProviderHandle,
            report.rawKatMaterialPresent,
            report.rawVectorBytesPresent,
            report.rawVectorHexPresent,
            report.publicVectorBytesPresent,
            report.publicVectorHexPresent,
            report.executableKatPresent,
            report.katExecutorPresent,
        )

        assertTrue(blockedBooleans.none { enabled -> enabled })
    }

    @Test
    fun katCaseBindingValidationIsNotRegistryFactoryDispatcherExecutorOrKatExecutorReachable() {
        val report = report()

        assertFalse(report.registrySelectable)
        assertFalse(report.factoryReachable)
        assertFalse(report.dispatcherReachable)
        assertFalse(report.executorTargetable)
        assertFalse(report.providerKatExecutorReachable)
    }

    @Test
    fun katCaseBindingValidationCannotExecuteProviderOperationsOrCrypto() {
        val report = report()

        assertFalse(report.canExecuteProviderOperations)
        assertFalse(report.canExecuteCrypto)
    }

    @Test
    fun katCaseBindingValidationCannotParticipateInVaultLifecyclePersistenceOrProductionSync() {
        val report = report()

        assertFalse(report.canUseForVaultLifecycle)
        assertFalse(report.canUseForPersistence)
        assertFalse(report.canUseForSync)
    }

    @Test
    fun katCaseBindingValidationCannotSignBroadcastOrEnableMainnet() {
        val report = report()

        assertFalse(report.canUseForSigning)
        assertFalse(report.canUseForBroadcasting)
        assertFalse(report.canUseForMainnet)
    }

    @Test
    fun katCaseBindingValidationDoesNotExposeRawKatVectorsRuntimeReferencesRawIdsOrMaterialInToString() {
        val report = report()
        val outputs = listOf(
            report.toString(),
            report.displayLabel.toString(),
        ) + report.validationCheckRows.flatMap { row ->
            listOf(row.toString(), row.label.toString())
        }
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
    fun katCaseBindingValidationClassIdsAndCaseIdAreCommonTestOnlyBySourceGuardContract() {
        val report = report()

        assertTrue(report.validationIsCommonTestOnly)
        assertTrue(
            checkPassed(
                SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck
                    .ProductionRuntimeSourceAbsenceSatisfied,
            ),
        )
        assertTrue(
            checkPassed(
                SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck.SafeOutputRedactionSatisfied,
            ),
        )
    }

    private fun report() =
        SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationPolicy
            .currentKatCaseBindingValidationReport()

    private fun checkPassed(check: SkaldVaultV1TestOnlyProviderIdentityKatCaseBindingValidationCheck): Boolean =
        report().validationCheckRows.single { row -> row.check == check }.passed

    private fun markerSafeId(): String =
        "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"

    private fun fixtureId(): String =
        "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata"

    private fun vectorId(): String =
        "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata"

    private fun caseId(): String =
        "skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata"
}
