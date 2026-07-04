package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityKatForbiddenCurrentVectorState
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityKatFuturePublicVectorCriterion
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorAdmissionOutcome
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorAdmissionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorAdmissionSourceSet
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityKatPublicVectorAdmissionTest {
    @Test
    fun publicVectorAdmissionExistsOnlyAsCommonTestInertAdmissionEvidence() {
        val admission = admission()

        assertTrue(admission.admissionIsCommonTestOnly)
        assertEquals(SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorAdmissionSourceSet.CommonTest, admission.sourceSet)
        assertTrue(admission.futureVectorCriteriaModeled)
        assertFalse(admission.currentVectorMaterialPresent)
        assertFalse(admission.runtimeSelectable)
        assertFalse(admission.productionProviderSelectable)
    }

    @Test
    fun publicVectorAdmissionReadsExactlyOnePriorIdentityChainArtifactAndFixtureValidationReport() {
        val admission = admission()

        assertEquals(1, admission.markerCount)
        assertEquals(1, admission.inventoryCount)
        assertEquals(1, admission.profileCount)
        assertEquals(1, admission.validationReportCount)
        assertEquals(1, admission.reachabilityProofCount)
        assertEquals(1, admission.capabilityMatrixCount)
        assertEquals(1, admission.fixtureScopeCount)
        assertEquals(1, admission.fixtureCatalogCount)
        assertEquals(1, admission.fixtureValidationCount)
    }

    @Test
    fun publicVectorAdmissionConfirmsCatalogContainsExactlyOneFixtureMetadataRow() {
        val admission = admission()

        assertEquals(1, admission.fixtureRowCount)
        assertTrue(admission.expectedSafeIdMatched)
        assertTrue(admission.expectedFixtureIdMatched)
    }

    @Test
    fun publicVectorAdmissionConfirmsMetadataRowReferencesExpectedMarkerSafeId() {
        val admission = admission()

        assertTrue(admission.expectedSafeIdMatched)
        assertEquals(
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            markerSafeId(),
        )
    }

    @Test
    fun publicVectorAdmissionConfirmsMetadataRowUsesExpectedFixtureId() {
        val admission = admission()

        assertTrue(admission.expectedFixtureIdMatched)
        assertEquals(
            "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata",
            fixtureId(),
        )
    }

    @Test
    fun allPublicVectorAdmissionOutcomesAreRepresentedExactlyOnce() {
        val admission = admission()

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorAdmissionOutcome.entries.toSet(),
            admission.admissionOutcomeRows.map { row -> row.outcome }.toSet(),
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorAdmissionOutcome.entries.size,
            admission.admissionOutcomeRows.size,
        )
        assertTrue(admission.admissionOutcomeRows.all { row -> row.modeled && !row.authorizesCurrentVectors })
    }

    @Test
    fun allFuturePublicVectorCriteriaAreRepresentedExactlyOnce() {
        val admission = admission()

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityKatFuturePublicVectorCriterion.entries.toSet(),
            admission.futurePublicVectorCriteriaRows.map { row -> row.criterion }.toSet(),
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityKatFuturePublicVectorCriterion.entries.size,
            admission.futurePublicVectorCriteriaRows.size,
        )
        assertTrue(
            admission.futurePublicVectorCriteriaRows.all { row ->
                row.requiredForFutureBranch && !row.satisfiedNow && !row.authorizesCurrentVectors
            },
        )
    }

    @Test
    fun allForbiddenCurrentVectorStatesAreRepresentedExactlyOnce() {
        val admission = admission()

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityKatForbiddenCurrentVectorState.entries.toSet(),
            admission.forbiddenCurrentVectorStateRows.map { row -> row.state }.toSet(),
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityKatForbiddenCurrentVectorState.entries.size,
            admission.forbiddenCurrentVectorStateRows.size,
        )
        assertTrue(admission.forbiddenCurrentVectorStateRows.all { row -> row.forbiddenNow && !row.presentNow })
    }

    @Test
    fun publicVectorAdmissionIsNotProductionAuthorization() {
        assertFalse(admission().admissionIsProductionAuthorization)
    }

    @Test
    fun publicVectorAdmissionIsNotProviderSelectionAuthorization() {
        assertFalse(admission().admissionIsProviderSelectionAuthorization)
    }

    @Test
    fun publicVectorAdmissionIsNotKatExecutionAuthorization() {
        assertFalse(admission().admissionIsKatExecutionAuthorization)
    }

    @Test
    fun publicVectorAdmissionIsNotCryptoAuthorization() {
        assertFalse(admission().admissionIsCryptoAuthorization)
    }

    @Test
    fun publicVectorAdmissionIsNotVaultPersistenceAuthorization() {
        assertFalse(admission().admissionIsVaultPersistenceAuthorization)
    }

    @Test
    fun publicVectorAdmissionIsNotMainnetAuthorization() {
        assertFalse(admission().admissionIsMainnetAuthorization)
    }

    @Test
    fun futureVectorCriteriaDoNotAuthorizeCurrentVectors() {
        val admission = admission()

        assertTrue(admission.futureVectorCriteriaModeled)
        assertFalse(admission.futureVectorCriteriaAuthorizeCurrentVectors)
        assertTrue(admission.futurePublicVectorCriteriaRows.none { row -> row.authorizesCurrentVectors })
    }

    @Test
    fun publicVectorAdmissionHasNoCurrentVectorMaterialRawKatMaterialPublicBytesPublicHexExecutableKatOrKatExecutor() {
        val admission = admission()

        assertFalse(admission.currentVectorMaterialPresent)
        assertFalse(admission.rawKatMaterialPresent)
        assertFalse(admission.publicVectorBytesPresent)
        assertFalse(admission.publicVectorHexPresent)
        assertFalse(admission.executableKatPresent)
        assertFalse(admission.katExecutorPresent)
    }

    @Test
    fun publicVectorAdmissionDoesNotImplementVaultCryptoProvider() {
        assertFalse(admission().implementsVaultCryptoProvider)
    }

    @Test
    fun publicVectorAdmissionDoesNotExposeVaultCryptoProviderInstance() {
        assertFalse(admission().containsVaultCryptoProvider)
    }

    @Test
    fun publicVectorAdmissionIsNotSelectedByVaultCryptoProviderSelectionRegistry() {
        val result = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, result.selectedCandidateId)
        assertIs<DisabledVaultCryptoProvider>(result.selectedProvider)
        assertFalse(result.safeDetail.contains(markerSafeId()))
        assertFalse(result.safeDetail.contains(fixtureId()))
        assertFalse(result.candidates.any { candidate -> candidate.safeDetail.contains(markerSafeId()) })
        assertFalse(result.candidates.any { candidate -> candidate.safeDetail.contains(fixtureId()) })
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
        val admission = admission()

        assertFalse(admission.productionProviderSelectable)
        assertFalse(VaultCryptoProviderSelectionRegistry.select().productionProviderSelectable)
    }

    @Test
    fun everyCapabilityAndReachabilityBooleanRemainsFalse() {
        val admission = admission()
        val blockedBooleans = listOf(
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
            admission.currentVectorMaterialPresent,
            admission.rawKatMaterialPresent,
            admission.publicVectorBytesPresent,
            admission.publicVectorHexPresent,
            admission.executableKatPresent,
            admission.katExecutorPresent,
        )

        assertTrue(blockedBooleans.none { enabled -> enabled })
    }

    @Test
    fun publicVectorAdmissionIsNotRegistryFactoryDispatcherExecutorOrKatExecutorReachable() {
        val admission = admission()

        assertFalse(admission.registrySelectable)
        assertFalse(admission.factoryReachable)
        assertFalse(admission.dispatcherReachable)
        assertFalse(admission.executorTargetable)
        assertFalse(admission.providerKatExecutorReachable)
    }

    @Test
    fun publicVectorAdmissionCannotExecuteProviderOperationsOrCrypto() {
        val admission = admission()

        assertFalse(admission.canExecuteProviderOperations)
        assertFalse(admission.canExecuteCrypto)
    }

    @Test
    fun publicVectorAdmissionCannotParticipateInVaultLifecyclePersistenceOrProductionSync() {
        val admission = admission()

        assertFalse(admission.canUseForVaultLifecycle)
        assertFalse(admission.canUseForPersistence)
        assertFalse(admission.canUseForSync)
    }

    @Test
    fun publicVectorAdmissionCannotSignBroadcastOrEnableMainnet() {
        val admission = admission()

        assertFalse(admission.canUseForSigning)
        assertFalse(admission.canUseForBroadcasting)
        assertFalse(admission.canUseForMainnet)
    }

    @Test
    fun publicVectorAdmissionDoesNotExposeRawKatVectorsRuntimeReferencesRawSafeIdsOrFixtureIdsInToString() {
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
            "public vector bytes",
            "public vector hex",
            "vector hex",
            "hex string",
            "fingerprint",
            "secret hash",
            "crash report",
            "analytics",
            "support export",
        )

        outputs.forEach { output ->
            assertFalse(output.contains(markerSafeId()))
            assertFalse(output.contains(fixtureId()))
            rejectedTerms.forEach { term ->
                assertFalse(output.contains(term, ignoreCase = true), "safe output leaked $term")
            }
        }
    }

    @Test
    fun publicVectorAdmissionClassSafeIdAndFixtureIdAreCommonTestOnlyBySourceGuardContract() {
        val admission = admission()

        assertTrue(admission.admissionIsCommonTestOnly)
        assertEquals(1, admission.fixtureRowCount)
        assertTrue(admission.expectedSafeIdMatched)
        assertTrue(admission.expectedFixtureIdMatched)
        assertFalse(admission.currentVectorMaterialPresent)
        assertFalse(admission.admissionIsKatExecutionAuthorization)
    }

    private fun admission() =
        SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorAdmissionPolicy.currentPublicVectorAdmission()

    private fun markerSafeId(): String =
        "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"

    private fun fixtureId(): String =
        "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata"
}
