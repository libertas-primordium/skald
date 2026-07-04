package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityForbiddenCurrentProviderOperationKatState
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityFutureProviderOperationKatCriterion
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProviderOperationKatAdmissionOutcome
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProviderOperationKatAdmissionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProviderOperationKatAdmissionSourceSet
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityProviderOperationKatAdmissionTest {
    @Test
    fun providerOperationKatAdmissionExistsOnlyAsCommonTestInertAdmissionEvidence() {
        val admission = admission()

        assertTrue(admission.admissionIsCommonTestOnly)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationKatAdmissionSourceSet.CommonTest,
            admission.sourceSet,
        )
        assertTrue(admission.futureProviderOperationKatCriteriaModeled)
        assertFalse(admission.currentProviderOperationKatPresent)
        assertFalse(admission.productionProviderSelectable)
    }

    @Test
    fun providerOperationKatAdmissionReadsExecutableMetadataKatSuiteReportAndValidationChain() {
        val admission = admission()

        assertEquals(1, admission.markerCount)
        assertEquals(1, admission.caseBindingCount)
        assertEquals(1, admission.executableMetadataKatCount)
        assertEquals(1, admission.executableMetadataKatValidationCount)
        assertEquals(1, admission.executableMetadataKatSuiteReportCount)
    }

    @Test
    fun providerOperationKatAdmissionConfirmsMetadataKatEvaluatedTrue() {
        assertTrue(admission().metadataKatEvaluated)
    }

    @Test
    fun providerOperationKatAdmissionConfirmsMetadataKatPassedTrue() {
        assertTrue(admission().metadataKatPassed)
    }

    @Test
    fun providerOperationKatAdmissionConfirmsExecutableMetadataKatValidationPassedTrue() {
        assertTrue(admission().executableMetadataKatValidationPassed)
    }

    @Test
    fun providerOperationKatAdmissionConfirmsMetadataKatSuitePassedTrue() {
        assertTrue(admission().metadataKatSuitePassed)
    }

    @Test
    fun providerOperationKatAdmissionConfirmsExpectedMarkerFixtureVectorAndCaseIds() {
        val admission = admission()

        assertTrue(admission.expectedSafeIdMatched)
        assertTrue(admission.expectedFixtureIdMatched)
        assertTrue(admission.expectedVectorIdMatched)
        assertTrue(admission.expectedCaseIdMatched)
    }

    @Test
    fun allProviderOperationKatAdmissionOutcomesAreRepresentedExactlyOnce() {
        val outcomes = admission().admissionOutcomes

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationKatAdmissionOutcome.entries.size,
            outcomes.size,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationKatAdmissionOutcome.entries.toSet(),
            outcomes.toSet(),
        )
    }

    @Test
    fun allFutureProviderOperationKatCriteriaAreRepresentedExactlyOnce() {
        val criteria = admission().futureProviderOperationKatCriteria

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityFutureProviderOperationKatCriterion.entries.size,
            criteria.size,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityFutureProviderOperationKatCriterion.entries.toSet(),
            criteria.toSet(),
        )
        assertTrue(admission().futureProviderOperationKatCriteriaModeled)
    }

    @Test
    fun allForbiddenCurrentProviderOperationKatStatesAreRepresentedExactlyOnce() {
        val states = admission().forbiddenCurrentProviderOperationKatStates

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityForbiddenCurrentProviderOperationKatState.entries.size,
            states.size,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityForbiddenCurrentProviderOperationKatState.entries.toSet(),
            states.toSet(),
        )
    }

    @Test
    fun futureProviderOperationKatCriteriaDoNotAuthorizeCurrentExecution() {
        val admission = admission()

        assertTrue(admission.futureProviderOperationKatCriteriaModeled)
        assertFalse(admission.futureProviderOperationKatCriteriaAuthorizeCurrentExecution)
    }

    @Test
    fun currentProviderOperationKatExecutionCryptoRunnerAndExecutorRemainAbsent() {
        val admission = admission()

        assertFalse(admission.currentProviderOperationKatPresent)
        assertFalse(admission.currentProviderOperationExecutionPresent)
        assertFalse(admission.currentCryptoExecutionPresent)
        assertFalse(admission.currentKatRunnerPresent)
        assertFalse(admission.currentKatExecutorPresent)
    }

    @Test
    fun providerOperationKatAdmissionHasNoRawVectorMaterial() {
        val admission = admission()

        assertFalse(admission.rawKatMaterialPresent)
        assertFalse(admission.rawVectorBytesPresent)
        assertFalse(admission.rawVectorHexPresent)
        assertFalse(admission.publicVectorBytesPresent)
        assertFalse(admission.publicVectorHexPresent)
    }

    @Test
    fun providerOperationKatAdmissionIsNotProductionProviderSelectionProviderOperationKatExecutorCryptoVaultOrMainnetAuthorization() {
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
    fun providerOperationKatAdmissionDoesNotImplementOrExposeVaultCryptoProvider() {
        val admission = admission()

        assertFalse(admission.implementsVaultCryptoProvider)
        assertFalse(admission.containsVaultCryptoProvider)
    }

    @Test
    fun providerOperationKatAdmissionIsNotSelectedByVaultCryptoProviderSelectionRegistry() {
        val result = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, result.selectedCandidateId)
        assertIs<DisabledVaultCryptoProvider>(result.selectedProvider)
        assertFalse(result.safeDetail.contains(markerSafeId()))
        assertFalse(result.safeDetail.contains(fixtureId()))
        assertFalse(result.safeDetail.contains(vectorId()))
        assertFalse(result.safeDetail.contains(caseId()))
        assertFalse(result.safeDetail.contains("SkaldVaultV1TestOnlyProviderIdentityProviderOperationKatAdmission"))
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
            admission.providerOperationReachable,
            admission.cryptoExecutionReachable,
            admission.runtimeSelectable,
            admission.registrySelectable,
            admission.factoryReachable,
            admission.dispatcherReachable,
            admission.executorTargetable,
            admission.providerKatExecutorReachable,
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
            admission.futureProviderOperationKatCriteriaAuthorizeCurrentExecution,
            admission.currentProviderOperationKatPresent,
            admission.currentProviderOperationExecutionPresent,
            admission.currentCryptoExecutionPresent,
            admission.currentKatRunnerPresent,
            admission.currentKatExecutorPresent,
            admission.rawKatMaterialPresent,
            admission.rawVectorBytesPresent,
            admission.rawVectorHexPresent,
            admission.publicVectorBytesPresent,
            admission.publicVectorHexPresent,
            admission.admissionIsProductionAuthorization,
            admission.admissionIsProviderSelectionAuthorization,
            admission.admissionIsProviderOperationAuthorization,
            admission.admissionIsKatExecutorAuthorization,
            admission.admissionIsCryptoAuthorization,
            admission.admissionIsVaultPersistenceAuthorization,
            admission.admissionIsMainnetAuthorization,
        )

        assertTrue(blockedBooleans.none { enabled -> enabled })
    }

    @Test
    fun providerOperationKatAdmissionIsNotRegistryFactoryDispatcherExecutorOrKatExecutorReachable() {
        val admission = admission()

        assertFalse(admission.registrySelectable)
        assertFalse(admission.factoryReachable)
        assertFalse(admission.dispatcherReachable)
        assertFalse(admission.executorTargetable)
        assertFalse(admission.providerKatExecutorReachable)
    }

    @Test
    fun providerOperationKatAdmissionCannotExecuteProviderOperationsOrCrypto() {
        val admission = admission()

        assertFalse(admission.canExecuteProviderOperations)
        assertFalse(admission.canExecuteCrypto)
        assertFalse(admission.providerOperationReachable)
        assertFalse(admission.cryptoExecutionReachable)
    }

    @Test
    fun providerOperationKatAdmissionCannotParticipateInVaultLifecyclePersistenceOrProductionSync() {
        val admission = admission()

        assertFalse(admission.canUseForVaultLifecycle)
        assertFalse(admission.canUseForPersistence)
        assertFalse(admission.canUseForSync)
        assertFalse(admission.vaultLifecycleReachable)
        assertFalse(admission.persistenceReachable)
        assertFalse(admission.productionSyncReachable)
    }

    @Test
    fun providerOperationKatAdmissionCannotSignBroadcastOrEnableMainnet() {
        val admission = admission()

        assertFalse(admission.canUseForSigning)
        assertFalse(admission.canUseForBroadcasting)
        assertFalse(admission.canUseForMainnet)
        assertFalse(admission.mainnetReachable)
    }

    @Test
    fun providerOperationKatAdmissionDoesNotExposeRawKatVectorsRuntimeReferencesRawIdsOrMaterialInToString() {
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
    fun providerOperationKatAdmissionClassSuiteValidationMetadataKatAndIdsAreCommonTestOnlyBySourceGuardContract() {
        val admission = admission()

        assertTrue(admission.admissionIsCommonTestOnly)
        assertTrue(admission.expectedSafeIdMatched)
        assertTrue(admission.expectedFixtureIdMatched)
        assertTrue(admission.expectedVectorIdMatched)
        assertTrue(admission.expectedCaseIdMatched)
        assertTrue(admission.metadataKatEvaluated)
        assertTrue(admission.metadataKatPassed)
        assertTrue(admission.executableMetadataKatValidationPassed)
        assertTrue(admission.metadataKatSuitePassed)
        assertFalse(admission.admissionIsProviderOperationAuthorization)
        assertFalse(admission.currentProviderOperationKatPresent)
        assertFalse(admission.currentProviderOperationExecutionPresent)
    }

    private fun admission() =
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationKatAdmissionPolicy
            .currentProviderOperationKatAdmission()

    private fun markerSafeId(): String =
        "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"

    private fun fixtureId(): String =
        "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata"

    private fun vectorId(): String =
        "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata"

    private fun caseId(): String =
        "skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata"
}
