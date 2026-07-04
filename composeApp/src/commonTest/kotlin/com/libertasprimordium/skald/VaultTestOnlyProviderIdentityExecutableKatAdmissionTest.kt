package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityExecutableKatAdmissionOutcome
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityExecutableKatAdmissionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityExecutableKatAdmissionSourceSet
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityForbiddenCurrentExecutableKatState
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityFutureExecutableKatCriterion
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityExecutableKatAdmissionTest {
    @Test
    fun executableKatAdmissionExistsOnlyAsCommonTestInertAdmissionEvidence() {
        val admission = admission()

        assertTrue(admission.admissionIsCommonTestOnly)
        assertEquals(SkaldVaultV1TestOnlyProviderIdentityExecutableKatAdmissionSourceSet.CommonTest, admission.sourceSet)
        assertTrue(admission.futureExecutableKatCriteriaModeled)
        assertFalse(admission.currentExecutableKatPresent)
        assertFalse(admission.productionProviderSelectable)
    }

    @Test
    fun executableKatAdmissionReadsExistingKatCaseBindingAndCaseBindingValidationReport() {
        val admission = admission()

        assertEquals(1, admission.markerCount)
        assertEquals(1, admission.fixtureRowCount)
        assertEquals(1, admission.publicVectorRowCount)
        assertEquals(1, admission.caseBindingCount)
        assertEquals(1, admission.caseBindingValidationCount)
    }

    @Test
    fun executableKatAdmissionConfirmsCaseBindingCountIsOne() {
        assertEquals(1, admission().caseBindingCount)
    }

    @Test
    fun executableKatAdmissionConfirmsExpectedMarkerFixtureVectorAndCaseIds() {
        val admission = admission()

        assertTrue(admission.expectedSafeIdMatched)
        assertTrue(admission.expectedFixtureIdMatched)
        assertTrue(admission.expectedVectorIdMatched)
        assertTrue(admission.expectedCaseIdMatched)
    }

    @Test
    fun allExecutableKatAdmissionOutcomesAreRepresentedExactlyOnce() {
        val outcomes = admission().admissionOutcomes

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityExecutableKatAdmissionOutcome.entries.size, outcomes.size)
        assertEquals(SkaldVaultV1TestOnlyProviderIdentityExecutableKatAdmissionOutcome.entries.toSet(), outcomes.toSet())
    }

    @Test
    fun allFutureExecutableKatCriteriaAreRepresentedExactlyOnce() {
        val criteria = admission().futureExecutableKatCriteria

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityFutureExecutableKatCriterion.entries.size, criteria.size)
        assertEquals(SkaldVaultV1TestOnlyProviderIdentityFutureExecutableKatCriterion.entries.toSet(), criteria.toSet())
        assertTrue(admission().futureExecutableKatCriteriaModeled)
    }

    @Test
    fun allForbiddenCurrentExecutableKatStatesAreRepresentedExactlyOnce() {
        val states = admission().forbiddenCurrentExecutableKatStates

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityForbiddenCurrentExecutableKatState.entries.size, states.size)
        assertEquals(SkaldVaultV1TestOnlyProviderIdentityForbiddenCurrentExecutableKatState.entries.toSet(), states.toSet())
    }

    @Test
    fun futureExecutableKatCriteriaDoNotAuthorizeCurrentExecution() {
        val admission = admission()

        assertTrue(admission.futureExecutableKatCriteriaModeled)
        assertFalse(admission.futureExecutableKatCriteriaAuthorizeCurrentExecution)
    }

    @Test
    fun currentExecutableKatRunnerExecutorAndVectorMaterialRemainAbsent() {
        val admission = admission()

        assertFalse(admission.currentExecutableKatPresent)
        assertFalse(admission.currentKatRunnerPresent)
        assertFalse(admission.currentKatExecutorPresent)
        assertFalse(admission.rawKatMaterialPresent)
        assertFalse(admission.rawVectorBytesPresent)
        assertFalse(admission.rawVectorHexPresent)
        assertFalse(admission.publicVectorBytesPresent)
        assertFalse(admission.publicVectorHexPresent)
        assertFalse(admission.executableKatPresent)
        assertFalse(admission.katExecutorPresent)
    }

    @Test
    fun executableKatAdmissionIsNotProductionProviderSelectionKatCryptoVaultPersistenceOrMainnetAuthorization() {
        val admission = admission()

        assertFalse(admission.admissionIsProductionAuthorization)
        assertFalse(admission.admissionIsProviderSelectionAuthorization)
        assertFalse(admission.admissionIsKatExecutionAuthorization)
        assertFalse(admission.admissionIsCryptoAuthorization)
        assertFalse(admission.admissionIsVaultPersistenceAuthorization)
        assertFalse(admission.admissionIsMainnetAuthorization)
    }

    @Test
    fun executableKatAdmissionDoesNotImplementOrExposeVaultCryptoProvider() {
        val admission = admission()

        assertFalse(admission.implementsVaultCryptoProvider)
        assertFalse(admission.containsVaultCryptoProvider)
    }

    @Test
    fun executableKatAdmissionIsNotSelectedByVaultCryptoProviderSelectionRegistry() {
        val result = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, result.selectedCandidateId)
        assertIs<DisabledVaultCryptoProvider>(result.selectedProvider)
        assertFalse(result.safeDetail.contains(markerSafeId()))
        assertFalse(result.safeDetail.contains(fixtureId()))
        assertFalse(result.safeDetail.contains(vectorId()))
        assertFalse(result.safeDetail.contains(caseId()))
        assertFalse(result.safeDetail.contains("SkaldVaultV1TestOnlyProviderIdentityExecutableKatAdmission"))
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
            admission.currentExecutableKatPresent,
            admission.currentKatRunnerPresent,
            admission.currentKatExecutorPresent,
            admission.futureExecutableKatCriteriaAuthorizeCurrentExecution,
            admission.rawKatMaterialPresent,
            admission.rawVectorBytesPresent,
            admission.rawVectorHexPresent,
            admission.publicVectorBytesPresent,
            admission.publicVectorHexPresent,
            admission.executableKatPresent,
            admission.katExecutorPresent,
            admission.admissionIsProductionAuthorization,
            admission.admissionIsProviderSelectionAuthorization,
            admission.admissionIsKatExecutionAuthorization,
            admission.admissionIsCryptoAuthorization,
            admission.admissionIsVaultPersistenceAuthorization,
            admission.admissionIsMainnetAuthorization,
        )

        assertTrue(blockedBooleans.none { enabled -> enabled })
    }

    @Test
    fun executableKatAdmissionIsNotRegistryFactoryDispatcherExecutorOrKatExecutorReachable() {
        val admission = admission()

        assertFalse(admission.registrySelectable)
        assertFalse(admission.factoryReachable)
        assertFalse(admission.dispatcherReachable)
        assertFalse(admission.executorTargetable)
        assertFalse(admission.providerKatExecutorReachable)
    }

    @Test
    fun executableKatAdmissionCannotExecuteProviderOperationsOrCrypto() {
        val admission = admission()

        assertFalse(admission.canExecuteProviderOperations)
        assertFalse(admission.canExecuteCrypto)
    }

    @Test
    fun executableKatAdmissionCannotParticipateInVaultLifecyclePersistenceOrProductionSync() {
        val admission = admission()

        assertFalse(admission.canUseForVaultLifecycle)
        assertFalse(admission.canUseForPersistence)
        assertFalse(admission.canUseForSync)
    }

    @Test
    fun executableKatAdmissionCannotSignBroadcastOrEnableMainnet() {
        val admission = admission()

        assertFalse(admission.canUseForSigning)
        assertFalse(admission.canUseForBroadcasting)
        assertFalse(admission.canUseForMainnet)
    }

    @Test
    fun executableKatAdmissionDoesNotExposeRawKatVectorsRuntimeReferencesRawIdsOrMaterialInToString() {
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
    fun executableKatAdmissionClassIdsAndCaseIdAreCommonTestOnlyBySourceGuardContract() {
        val admission = admission()

        assertTrue(admission.admissionIsCommonTestOnly)
        assertTrue(admission.expectedSafeIdMatched)
        assertTrue(admission.expectedFixtureIdMatched)
        assertTrue(admission.expectedVectorIdMatched)
        assertTrue(admission.expectedCaseIdMatched)
        assertFalse(admission.admissionIsKatExecutionAuthorization)
        assertFalse(admission.currentExecutableKatPresent)
        assertFalse(admission.currentKatRunnerPresent)
        assertFalse(admission.currentKatExecutorPresent)
    }

    private fun admission() =
        SkaldVaultV1TestOnlyProviderIdentityExecutableKatAdmissionPolicy.currentExecutableKatAdmission()

    private fun markerSafeId(): String =
        "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"

    private fun fixtureId(): String =
        "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata"

    private fun vectorId(): String =
        "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata"

    private fun caseId(): String =
        "skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata"
}
