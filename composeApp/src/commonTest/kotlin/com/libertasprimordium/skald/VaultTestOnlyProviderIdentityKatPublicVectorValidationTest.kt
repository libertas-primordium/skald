package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationSourceSet
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityKatPublicVectorValidationTest {
    @Test
    fun publicVectorValidationReportExistsOnlyAsCommonTestInertValidationEvidence() {
        val report = report()

        assertTrue(report.validationIsCommonTestOnly)
        assertEquals(SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationSourceSet.CommonTest, report.sourceSet)
        assertTrue(report.allValidationChecksPassed)
        assertFalse(report.runtimeSelectable)
        assertFalse(report.productionProviderSelectable)
    }

    @Test
    fun publicVectorValidationReadsExistingFixtureAdmissionFixtureValidationCatalogScopeMarkerAndCapabilityMatrix() {
        val report = report()

        assertEquals(1, report.markerCount)
        assertEquals(1, report.fixtureRowCount)
        assertEquals(1, report.publicVectorRowCount)
        assertTrue(report.expectedSafeIdMatched)
        assertTrue(report.expectedFixtureIdMatched)
        assertTrue(report.expectedVectorIdMatched)
    }

    @Test
    fun publicVectorValidationConfirmsExactlyOnePublicVectorRow() {
        val report = report()

        assertEquals(1, report.publicVectorRowCount)
        assertTrue(checkPassed(SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck.VectorRowPresentExactlyOnce))
    }

    @Test
    fun publicVectorValidationConfirmsExpectedMarkerSafeId() {
        val report = report()

        assertTrue(report.expectedSafeIdMatched)
        assertTrue(checkPassed(SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck.VectorRowReferencesExpectedMarkerSafeId))
    }

    @Test
    fun publicVectorValidationConfirmsExpectedFixtureId() {
        val report = report()

        assertTrue(report.expectedFixtureIdMatched)
        assertTrue(checkPassed(SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck.VectorRowReferencesExpectedFixtureId))
    }

    @Test
    fun publicVectorValidationConfirmsExpectedVectorId() {
        val report = report()

        assertTrue(report.expectedVectorIdMatched)
        assertTrue(checkPassed(SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck.VectorRowUsesExpectedVectorId))
    }

    @Test
    fun allValidationChecksAreRepresentedExactlyOnce() {
        val checks = report().validationCheckRows.map { row -> row.check }

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck.entries.size, checks.size)
        assertEquals(SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck.entries.toSet(), checks.toSet())
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
    fun publicVectorValidationIsNotProductionProviderSelectionKatCryptoVaultPersistenceOrMainnetAuthorization() {
        val report = report()

        assertFalse(report.validationIsProductionAuthorization)
        assertFalse(report.validationIsProviderSelectionAuthorization)
        assertFalse(report.validationIsKatExecutionAuthorization)
        assertFalse(report.validationIsCryptoAuthorization)
        assertFalse(report.validationIsVaultPersistenceAuthorization)
        assertFalse(report.validationIsMainnetAuthorization)
    }

    @Test
    fun publicVectorValidationConfirmsVectorIsPublicNonSecretTextOnlyAndNotExecutable() {
        val report = report()

        assertTrue(report.vectorIsPublicAndNonSecret)
        assertTrue(report.vectorIsTextOnly)
        assertFalse(report.vectorIsExecutable)
        assertTrue(checkPassed(SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck.VectorRowIsPublicAndNonSecret))
        assertTrue(checkPassed(SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck.VectorRowIsTextOnly))
        assertTrue(checkPassed(SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck.VectorRowIsNotExecutable))
    }

    @Test
    fun publicVectorValidationConfirmsVectorContainsNoRawBytesHexCryptoWalletEndpointOrProviderHandles() {
        val report = report()

        assertFalse(report.vectorContainsRawBytes)
        assertFalse(report.vectorContainsHex)
        assertFalse(report.vectorContainsCryptoMaterial)
        assertFalse(report.vectorContainsWalletMaterial)
        assertFalse(report.vectorContainsEndpointMaterial)
        assertFalse(report.vectorContainsProviderHandle)
        assertTrue(checkPassed(SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck.VectorRowContainsNoRawBytes))
        assertTrue(checkPassed(SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck.VectorRowContainsNoHex))
        assertTrue(checkPassed(SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck.VectorRowContainsNoCryptoMaterial))
        assertTrue(checkPassed(SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck.VectorRowContainsNoWalletMaterial))
        assertTrue(checkPassed(SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck.VectorRowContainsNoEndpointMaterial))
        assertTrue(checkPassed(SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck.VectorRowContainsNoProviderHandles))
    }

    @Test
    fun publicVectorValidationHasNoRawKatVectorMaterialExecutableKatOrKatExecutor() {
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
    fun publicVectorValidationDoesNotImplementOrExposeVaultCryptoProvider() {
        val report = report()

        assertFalse(report.implementsVaultCryptoProvider)
        assertFalse(report.containsVaultCryptoProvider)
    }

    @Test
    fun publicVectorValidationIsNotSelectedByVaultCryptoProviderSelectionRegistry() {
        val result = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, result.selectedCandidateId)
        assertIs<DisabledVaultCryptoProvider>(result.selectedProvider)
        assertFalse(result.safeDetail.contains(markerSafeId()))
        assertFalse(result.safeDetail.contains(fixtureId()))
        assertFalse(result.safeDetail.contains(vectorId()))
        assertFalse(result.safeDetail.contains("SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidation"))
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
                SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck
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
            report.vectorIsExecutable,
            report.vectorContainsRawBytes,
            report.vectorContainsHex,
            report.vectorContainsCryptoMaterial,
            report.vectorContainsWalletMaterial,
            report.vectorContainsEndpointMaterial,
            report.vectorContainsProviderHandle,
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
    fun publicVectorValidationIsNotRegistryFactoryDispatcherExecutorOrKatExecutorReachable() {
        val report = report()

        assertFalse(report.registrySelectable)
        assertFalse(report.factoryReachable)
        assertFalse(report.dispatcherReachable)
        assertFalse(report.executorTargetable)
        assertFalse(report.providerKatExecutorReachable)
    }

    @Test
    fun publicVectorValidationCannotExecuteProviderOperationsOrCrypto() {
        val report = report()

        assertFalse(report.canExecuteProviderOperations)
        assertFalse(report.canExecuteCrypto)
    }

    @Test
    fun publicVectorValidationCannotParticipateInVaultLifecyclePersistenceOrProductionSync() {
        val report = report()

        assertFalse(report.canUseForVaultLifecycle)
        assertFalse(report.canUseForPersistence)
        assertFalse(report.canUseForSync)
    }

    @Test
    fun publicVectorValidationCannotSignBroadcastOrEnableMainnet() {
        val report = report()

        assertFalse(report.canUseForSigning)
        assertFalse(report.canUseForBroadcasting)
        assertFalse(report.canUseForMainnet)
    }

    @Test
    fun publicVectorValidationDoesNotExposeRawKatVectorsRuntimeReferencesRawIdsOrMaterialInToString() {
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
            rejectedTerms.forEach { term ->
                assertFalse(output.contains(term, ignoreCase = true), "safe output leaked $term")
            }
        }
    }

    @Test
    fun publicVectorValidationRuntimeSourceAbsenceIsEnforcedBySourceGuardContract() {
        val report = report()

        assertTrue(report.validationIsCommonTestOnly)
        assertTrue(
            checkPassed(
                SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck
                    .ProductionRuntimeSourceAbsenceSatisfied,
            ),
        )
        assertTrue(checkPassed(SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck.SafeOutputRedactionSatisfied))
    }

    private fun report() =
        SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationPolicy.currentPublicVectorValidationReport()

    private fun checkPassed(check: SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorValidationCheck): Boolean =
        report().validationCheckRows.single { row -> row.check == check }.passed

    private fun markerSafeId(): String =
        "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"

    private fun fixtureId(): String =
        "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata"

    private fun vectorId(): String =
        "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata"
}
