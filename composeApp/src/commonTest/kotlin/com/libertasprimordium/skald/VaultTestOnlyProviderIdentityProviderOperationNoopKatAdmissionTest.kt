package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityForbiddenCurrentNoopProviderOperationKatState
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityFutureNoopProviderOperationKatCriterion
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatAdmissionOutcome
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatAdmissionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatAdmissionSourceSet
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityProviderOperationNoopKatAdmissionTest {
    @Test
    fun noopProviderOperationKatAdmissionExistsOnlyAsCommonTestInertAdmissionEvidence() {
        val admission = admission()

        assertTrue(admission.admissionIsCommonTestOnly)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatAdmissionSourceSet.CommonTest,
            admission.sourceSet,
        )
        assertTrue(admission.futureNoopProviderOperationKatCriteriaModeled)
        assertFalse(admission.futureNoopProviderOperationKatCriteriaAuthorizeCurrentExecution)
        assertFalse(admission.currentNoopProviderOperationKatPresent)
        assertFalse(admission.productionProviderSelectable)
    }

    @Test
    fun noopProviderOperationKatAdmissionReadsProviderOperationMetadataKatSuiteReportAndValidationChain() {
        val admission = admission()

        assertEquals(1, admission.markerCount)
        assertEquals(1, admission.caseBindingCount)
        assertEquals(1, admission.executableMetadataKatSuiteReportCount)
        assertEquals(1, admission.providerOperationKatAdmissionCount)
        assertEquals(1, admission.providerOperationMetadataKatCount)
        assertEquals(1, admission.providerOperationMetadataKatValidationCount)
        assertEquals(1, admission.providerOperationMetadataKatSuiteReportCount)
    }

    @Test
    fun noopProviderOperationKatAdmissionConfirmsProviderOperationMetadataKatPassedTrue() {
        assertTrue(admission().providerOperationMetadataKatPassed)
    }

    @Test
    fun noopProviderOperationKatAdmissionConfirmsProviderOperationMetadataKatValidationPassedTrue() {
        assertTrue(admission().providerOperationMetadataKatValidationPassed)
    }

    @Test
    fun noopProviderOperationKatAdmissionConfirmsProviderOperationMetadataKatSuitePassedTrue() {
        assertTrue(admission().providerOperationMetadataKatSuitePassed)
    }

    @Test
    fun noopProviderOperationKatAdmissionConfirmsExpectedMarkerFixtureVectorCaseAndKatIds() {
        val admission = admission()

        assertTrue(admission.expectedSafeIdMatched)
        assertTrue(admission.expectedFixtureIdMatched)
        assertTrue(admission.expectedVectorIdMatched)
        assertTrue(admission.expectedCaseIdMatched)
        assertTrue(admission.expectedProviderOperationMetadataKatIdMatched)
        assertTrue(admission.expectedProviderOperationNoopKatIdMatched)
    }

    @Test
    fun allNoopProviderOperationKatAdmissionOutcomesAreRepresentedExactlyOnce() {
        val outcomes = admission().admissionOutcomes

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatAdmissionOutcome.entries.size,
            outcomes.size,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatAdmissionOutcome.entries.toSet(),
            outcomes.toSet(),
        )
    }

    @Test
    fun allFutureNoopProviderOperationKatCriteriaAreRepresentedExactlyOnce() {
        val criteria = admission().futureNoopProviderOperationKatCriteria

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityFutureNoopProviderOperationKatCriterion.entries.size,
            criteria.size,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityFutureNoopProviderOperationKatCriterion.entries.toSet(),
            criteria.toSet(),
        )
        assertTrue(admission().futureNoopProviderOperationKatCriteriaModeled)
    }

    @Test
    fun allForbiddenCurrentNoopProviderOperationKatStatesAreRepresentedExactlyOnce() {
        val states = admission().forbiddenCurrentNoopProviderOperationKatStates

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityForbiddenCurrentNoopProviderOperationKatState.entries.size,
            states.size,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityForbiddenCurrentNoopProviderOperationKatState.entries.toSet(),
            states.toSet(),
        )
    }

    @Test
    fun futureNoopProviderOperationKatCriteriaDoNotAuthorizeCurrentExecution() {
        val admission = admission()

        assertTrue(admission.futureNoopProviderOperationKatCriteriaModeled)
        assertFalse(admission.futureNoopProviderOperationKatCriteriaAuthorizeCurrentExecution)
    }

    @Test
    fun currentNoopProviderOperationKatExecutionProviderExecutionCryptoRunnerAndExecutorRemainAbsent() {
        val admission = admission()

        assertFalse(admission.currentNoopProviderOperationKatPresent)
        assertFalse(admission.currentNoopProviderOperationExecutionPresent)
        assertFalse(admission.currentProviderOperationExecutionPresent)
        assertFalse(admission.currentCryptoExecutionPresent)
        assertFalse(admission.currentKatRunnerPresent)
        assertFalse(admission.currentKatExecutorPresent)
        assertFalse(admission.syntheticNoopResultPresent)
    }

    @Test
    fun noopProviderOperationKatAdmissionHasNoRawVectorMaterial() {
        val admission = admission()

        assertFalse(admission.rawKatMaterialPresent)
        assertFalse(admission.rawVectorBytesPresent)
        assertFalse(admission.rawVectorHexPresent)
        assertFalse(admission.publicVectorBytesPresent)
        assertFalse(admission.publicVectorHexPresent)
    }

    @Test
    fun noopProviderOperationKatAdmissionIsNotProductionProviderSelectionProviderOperationKatExecutorCryptoVaultOrMainnetAuthorization() {
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
    fun noopProviderOperationKatAdmissionDoesNotImplementOrExposeVaultCryptoProvider() {
        val admission = admission()

        assertFalse(admission.implementsVaultCryptoProvider)
        assertFalse(admission.containsVaultCryptoProvider)
    }

    @Test
    fun noopProviderOperationKatAdmissionIsNotSelectedByVaultCryptoProviderSelectionRegistry() {
        val result = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, result.selectedCandidateId)
        assertIs<DisabledVaultCryptoProvider>(result.selectedProvider)
        assertFalse(result.safeDetail.contains(markerSafeId()))
        assertFalse(result.safeDetail.contains(fixtureId()))
        assertFalse(result.safeDetail.contains(vectorId()))
        assertFalse(result.safeDetail.contains(caseId()))
        assertFalse(result.safeDetail.contains(providerOperationMetadataKatId()))
        assertFalse(result.safeDetail.contains(providerOperationNoopKatId()))
        assertFalse(
            result.safeDetail.contains(
                "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatAdmission",
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
            admission.futureNoopProviderOperationKatCriteriaAuthorizeCurrentExecution,
            admission.currentNoopProviderOperationKatPresent,
            admission.currentNoopProviderOperationExecutionPresent,
            admission.currentProviderOperationExecutionPresent,
            admission.currentCryptoExecutionPresent,
            admission.currentKatRunnerPresent,
            admission.currentKatExecutorPresent,
            admission.syntheticNoopResultPresent,
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
    fun noopProviderOperationKatAdmissionIsNotRegistryFactoryDispatcherExecutorOrKatExecutorReachable() {
        val admission = admission()

        assertFalse(admission.registrySelectable)
        assertFalse(admission.factoryReachable)
        assertFalse(admission.dispatcherReachable)
        assertFalse(admission.executorTargetable)
        assertFalse(admission.providerKatExecutorReachable)
    }

    @Test
    fun noopProviderOperationKatAdmissionCannotExecuteProviderOperationsOrCrypto() {
        val admission = admission()

        assertFalse(admission.canExecuteProviderOperations)
        assertFalse(admission.canExecuteCrypto)
        assertFalse(admission.providerOperationReachable)
        assertFalse(admission.cryptoExecutionReachable)
        assertFalse(admission.currentNoopProviderOperationExecutionPresent)
        assertFalse(admission.currentProviderOperationExecutionPresent)
        assertFalse(admission.currentCryptoExecutionPresent)
    }

    @Test
    fun noopProviderOperationKatAdmissionCannotParticipateInVaultLifecyclePersistenceOrProductionSync() {
        val admission = admission()

        assertFalse(admission.canUseForVaultLifecycle)
        assertFalse(admission.canUseForPersistence)
        assertFalse(admission.canUseForSync)
        assertFalse(admission.vaultLifecycleReachable)
        assertFalse(admission.persistenceReachable)
        assertFalse(admission.productionSyncReachable)
    }

    @Test
    fun noopProviderOperationKatAdmissionCannotSignBroadcastOrEnableMainnet() {
        val admission = admission()

        assertFalse(admission.canUseForSigning)
        assertFalse(admission.canUseForBroadcasting)
        assertFalse(admission.canUseForMainnet)
        assertFalse(admission.signingBroadcastingReachable)
        assertFalse(admission.mainnetReachable)
    }

    @Test
    fun noopProviderOperationKatAdmissionDoesNotExposeRawKatVectorsRuntimeReferencesRawIdsOrMaterialInToString() {
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
            assertFalse(output.contains(providerOperationMetadataKatId()))
            assertFalse(output.contains(providerOperationNoopKatId()))
            rejectedTerms.forEach { term ->
                assertFalse(output.contains(term, ignoreCase = true), "safe output leaked $term")
            }
        }
    }

    @Test
    fun noopProviderOperationKatAdmissionClassMetadataSuiteChainAndIdsAreCommonTestOnlyBySourceGuardContract() {
        val admission = admission()

        assertTrue(admission.admissionIsCommonTestOnly)
        assertTrue(admission.expectedSafeIdMatched)
        assertTrue(admission.expectedFixtureIdMatched)
        assertTrue(admission.expectedVectorIdMatched)
        assertTrue(admission.expectedCaseIdMatched)
        assertTrue(admission.expectedProviderOperationMetadataKatIdMatched)
        assertTrue(admission.expectedProviderOperationNoopKatIdMatched)
        assertTrue(admission.providerOperationMetadataKatPassed)
        assertTrue(admission.providerOperationMetadataKatValidationPassed)
        assertTrue(admission.providerOperationMetadataKatSuitePassed)
        assertFalse(admission.admissionIsProviderOperationAuthorization)
        assertFalse(admission.currentNoopProviderOperationKatPresent)
        assertFalse(admission.currentNoopProviderOperationExecutionPresent)
        assertFalse(admission.currentProviderOperationExecutionPresent)
    }

    private fun admission() =
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatAdmissionPolicy
            .currentProviderOperationNoopKatAdmission()

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
}
