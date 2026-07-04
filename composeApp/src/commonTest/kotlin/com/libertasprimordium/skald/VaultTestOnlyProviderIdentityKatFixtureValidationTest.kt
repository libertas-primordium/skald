package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalogPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityKatFixtureValidationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityKatFixtureValidationSourceSet
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityKatFixtureValidationTest {
    @Test
    fun katFixtureValidationReportExistsOnlyAsCommonTestInertValidationEvidence() {
        val report = report()

        assertTrue(report.validationIsCommonTestOnly)
        assertEquals(SkaldVaultV1TestOnlyProviderIdentityKatFixtureValidationSourceSet.CommonTest, report.sourceSet)
        assertTrue(report.fixtureMetadataOnly)
        assertFalse(report.runtimeSelectable)
        assertFalse(report.productionProviderSelectable)
    }

    @Test
    fun katFixtureValidationReadsExactlyOnePriorIdentityChainArtifactAndCatalog() {
        val report = report()

        assertEquals(1, report.markerCount)
        assertEquals(1, report.inventoryCount)
        assertEquals(1, report.profileCount)
        assertEquals(1, report.validationReportCount)
        assertEquals(1, report.reachabilityProofCount)
        assertEquals(1, report.capabilityMatrixCount)
        assertEquals(1, report.fixtureScopeCount)
        assertEquals(1, report.fixtureCatalogCount)
    }

    @Test
    fun katFixtureValidationConfirmsCatalogContainsExactlyOneFixtureMetadataRow() {
        val report = report()

        assertEquals(1, report.fixtureRowCount)
        assertTrue(report.catalogContainsExactlyOneFixture)
        assertTrue(SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalogPolicy.currentKatFixtureCatalog().catalogContainsExactlyOneFixture)
    }

    @Test
    fun katFixtureValidationConfirmsMetadataRowReferencesExpectedMarkerSafeId() {
        val row = SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalogPolicy.currentKatFixtureCatalog()
            .fixtureRows
            .single()
        val report = report()

        assertTrue(report.expectedSafeIdMatched)
        assertTrue(report.fixtureRowReferencesExpectedMarkerSafeId)
        assertEquals(
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            row.markerSafeId.value,
        )
    }

    @Test
    fun katFixtureValidationConfirmsMetadataRowUsesExpectedFixtureId() {
        val row = SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalogPolicy.currentKatFixtureCatalog()
            .fixtureRows
            .single()
        val report = report()

        assertTrue(report.expectedFixtureIdMatched)
        assertTrue(report.fixtureRowUsesExpectedFixtureId)
        assertEquals(
            "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata",
            row.fixtureId.value,
        )
    }

    @Test
    fun katFixtureValidationAllChecksPassedIsOnlyNonAuthorizingCommonTestEvidence() {
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
    fun katFixtureValidationIsNotProductionAuthorization() {
        assertFalse(report().validationIsProductionAuthorization)
    }

    @Test
    fun katFixtureValidationIsNotProviderSelectionAuthorization() {
        assertFalse(report().validationIsProviderSelectionAuthorization)
    }

    @Test
    fun katFixtureValidationIsNotKatExecutionAuthorization() {
        assertFalse(report().validationIsKatExecutionAuthorization)
    }

    @Test
    fun katFixtureValidationIsNotCryptoAuthorization() {
        assertFalse(report().validationIsCryptoAuthorization)
    }

    @Test
    fun katFixtureValidationIsNotVaultPersistenceAuthorization() {
        assertFalse(report().validationIsVaultPersistenceAuthorization)
    }

    @Test
    fun katFixtureValidationIsNotMainnetAuthorization() {
        assertFalse(report().validationIsMainnetAuthorization)
    }

    @Test
    fun katFixtureValidationHasNoRawKatMaterialVectorsExecutableKatOrKatExecutor() {
        val report = report()

        assertFalse(report.rawKatMaterialPresent)
        assertFalse(report.rawVectorBytesPresent)
        assertFalse(report.rawVectorHexPresent)
        assertFalse(report.executableKatPresent)
        assertFalse(report.katExecutorPresent)
        assertTrue(report.rawKatMaterialAbsent)
        assertTrue(report.rawVectorBytesAbsent)
        assertTrue(report.rawVectorHexAbsent)
        assertTrue(report.executableKatAbsent)
        assertTrue(report.katExecutorAbsent)
    }

    @Test
    fun katFixtureValidationDoesNotImplementVaultCryptoProvider() {
        assertFalse(report().implementsVaultCryptoProvider)
    }

    @Test
    fun katFixtureValidationDoesNotExposeVaultCryptoProviderInstance() {
        assertFalse(report().containsVaultCryptoProvider)
    }

    @Test
    fun katFixtureValidationIsNotSelectedByVaultCryptoProviderSelectionRegistry() {
        val result = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, result.selectedCandidateId)
        assertIs<DisabledVaultCryptoProvider>(result.selectedProvider)
        assertFalse(
            result.safeDetail.contains(
                "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            ),
        )
        assertFalse(
            result.safeDetail.contains(
                "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata",
            ),
        )
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

        assertTrue(report.productionProviderSelectableRemainsFalse)
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
        )

        assertTrue(blockedBooleans.none { enabled -> enabled })
    }

    @Test
    fun katFixtureValidationIsNotRegistryFactoryDispatcherExecutorOrKatExecutorReachable() {
        val report = report()

        assertFalse(report.registrySelectable)
        assertFalse(report.factoryReachable)
        assertFalse(report.dispatcherReachable)
        assertFalse(report.executorTargetable)
        assertFalse(report.providerKatExecutorReachable)
    }

    @Test
    fun katFixtureValidationCannotExecuteProviderOperationsOrCrypto() {
        val report = report()

        assertFalse(report.canExecuteProviderOperations)
        assertFalse(report.canExecuteCrypto)
    }

    @Test
    fun katFixtureValidationCannotParticipateInVaultLifecyclePersistenceOrProductionSync() {
        val report = report()

        assertFalse(report.canUseForVaultLifecycle)
        assertFalse(report.canUseForPersistence)
        assertFalse(report.canUseForSync)
    }

    @Test
    fun katFixtureValidationCannotSignBroadcastOrEnableMainnet() {
        val report = report()

        assertFalse(report.canUseForSigning)
        assertFalse(report.canUseForBroadcasting)
        assertFalse(report.canUseForMainnet)
    }

    @Test
    fun katFixtureValidationRecordsAllRequiredValidationChecksAsSatisfiedByTestOnlyEvidence() {
        val report = report()
        val checks = listOf(
            report.markerPresentExactlyOnce,
            report.inventoryPresentExactlyOnce,
            report.profilePresentExactlyOnce,
            report.validationReportPresentExactlyOnce,
            report.reachabilityProofPresentExactlyOnce,
            report.capabilityMatrixPresentExactlyOnce,
            report.katFixtureScopePresentExactlyOnce,
            report.katFixtureCatalogPresentExactlyOnce,
            report.catalogContainsExactlyOneFixture,
            report.fixtureRowReferencesExpectedMarkerSafeId,
            report.fixtureRowUsesExpectedFixtureId,
            report.fixtureRowIsMetadataOnly,
            report.rawKatMaterialAbsent,
            report.rawVectorBytesAbsent,
            report.rawVectorHexAbsent,
            report.executableKatAbsent,
            report.katExecutorAbsent,
            report.providerSelectionAuthorizationAbsent,
            report.productionAuthorizationAbsent,
            report.katExecutionAuthorizationAbsent,
            report.cryptoAuthorizationAbsent,
            report.vaultPersistenceAuthorizationAbsent,
            report.mainnetAuthorizationAbsent,
            report.providerSelectionRemainsDisabledProviderOnly,
            report.productionProviderSelectableRemainsFalse,
            report.productionRuntimeSourceAbsenceSatisfied,
            report.safeOutputRedactionSatisfied,
        )

        assertTrue(checks.all { passed -> passed })
    }

    @Test
    fun katFixtureValidationDoesNotExposeRawKatVectorsRuntimeReferencesRawSafeIdsOrFixtureIdsInToString() {
        val report = report()
        val outputs = listOf(
            report.toString(),
            report.displayLabel.toString(),
        )
        val markerSafeId = "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"
        val fixtureId = "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata"
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
            "vector hex",
            "hex string",
            "fingerprint",
            "secret hash",
            "crash report",
            "analytics",
            "support export",
        )

        outputs.forEach { output ->
            assertFalse(output.contains(markerSafeId))
            assertFalse(output.contains(fixtureId))
            rejectedTerms.forEach { term ->
                assertFalse(output.contains(term, ignoreCase = true), "safe output leaked $term")
            }
        }
    }

    @Test
    fun katFixtureValidationClassSafeIdAndFixtureIdAreCommonTestOnlyBySourceGuardContract() {
        val report = report()

        assertTrue(report.validationIsCommonTestOnly)
        assertTrue(report.productionRuntimeSourceAbsenceSatisfied)
        assertEquals(1, report.fixtureRowCount)
        assertTrue(report.expectedSafeIdMatched)
        assertTrue(report.expectedFixtureIdMatched)
    }

    private fun report() =
        SkaldVaultV1TestOnlyProviderIdentityKatFixtureValidationPolicy.currentKatFixtureValidationReport()
}
