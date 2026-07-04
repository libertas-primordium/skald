package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatPolicy
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityExecutableMetadataKatTest {
    @Test
    fun executableMetadataKatExistsOnlyAsCommonTestTestEvidence() {
        val result = result()

        assertTrue(result.evaluationIsCommonTestOnly)
        assertTrue(result.metadataKatEvaluated)
        assertTrue(result.metadataKatPassed)
        assertFalse(result.productionProviderSelectable)
    }

    @Test
    fun executableMetadataKatEvaluatesExactlyOneMetadataCaseBinding() {
        val result = result()

        assertEquals(1, result.markerCount)
        assertEquals(1, result.fixtureRowCount)
        assertEquals(1, result.publicVectorRowCount)
        assertEquals(1, result.caseBindingCount)
        assertEquals(1, result.caseBindingValidationCount)
        assertTrue(result.caseBindingIsMetadataOnly)
        assertFalse(result.caseBindingIsExecutable)
    }

    @Test
    fun executableMetadataKatReadsExecutableKatAdmissionGateAndCaseBindingValidationReport() {
        val result = result()

        assertEquals(1, result.executableKatAdmissionCount)
        assertEquals(1, result.caseBindingValidationCount)
        assertTrue(result.metadataKatEvaluated)
        assertTrue(result.metadataKatPassed)
    }

    @Test
    fun executableMetadataKatConfirmsExpectedMarkerFixtureVectorAndCaseIds() {
        val result = result()

        assertTrue(result.expectedSafeIdMatched)
        assertTrue(result.expectedFixtureIdMatched)
        assertTrue(result.expectedVectorIdMatched)
        assertTrue(result.expectedCaseIdMatched)
    }

    @Test
    fun metadataKatEvaluatedIsCommonTestOnlyTestEvidence() {
        val result = result()

        assertTrue(result.metadataKatEvaluated)
        assertTrue(result.evaluationIsCommonTestOnly)
        assertFalse(result.evaluationIsProductionAuthorization)
        assertFalse(result.evaluationIsProviderSelectionAuthorization)
        assertFalse(result.evaluationIsKatExecutionAuthorization)
    }

    @Test
    fun metadataKatPassedIsCommonTestOnlyTestEvidence() {
        val result = result()

        assertTrue(result.metadataKatPassed)
        assertTrue(result.evaluationIsCommonTestOnly)
        assertFalse(result.evaluationIsCryptoAuthorization)
        assertFalse(result.evaluationIsVaultPersistenceAuthorization)
        assertFalse(result.evaluationIsMainnetAuthorization)
    }

    @Test
    fun executableMetadataKatIsNotProductionProviderSelectionKatCryptoVaultPersistenceOrMainnetAuthorization() {
        val result = result()

        assertFalse(result.evaluationIsProductionAuthorization)
        assertFalse(result.evaluationIsProviderSelectionAuthorization)
        assertFalse(result.evaluationIsKatExecutionAuthorization)
        assertFalse(result.evaluationIsCryptoAuthorization)
        assertFalse(result.evaluationIsVaultPersistenceAuthorization)
        assertFalse(result.evaluationIsMainnetAuthorization)
    }

    @Test
    fun executableMetadataKatEvaluatesMetadataAndPublicNonSecretTextOnlyVectorOnly() {
        val result = result()

        assertTrue(result.evaluatesMetadataOnly)
        assertTrue(result.evaluatesPublicNonSecretTextOnlyVector)
        assertFalse(result.caseBindingContainsRawBytes)
        assertFalse(result.caseBindingContainsHex)
        assertFalse(result.caseBindingContainsCryptoMaterial)
        assertFalse(result.caseBindingContainsWalletMaterial)
        assertFalse(result.caseBindingContainsEndpointMaterial)
        assertFalse(result.caseBindingContainsProviderHandle)
    }

    @Test
    fun executableMetadataKatDoesNotEvaluateProviderCryptoVaultLifecycleOrPersistenceOperations() {
        val result = result()

        assertFalse(result.evaluatesProviderOperation)
        assertFalse(result.evaluatesCryptoOperation)
        assertFalse(result.evaluatesVaultLifecycle)
        assertFalse(result.evaluatesPersistence)
    }

    @Test
    fun executableMetadataKatHasNoRawVectorMaterialExecutorRunnerOrExecutor() {
        val result = result()

        assertFalse(result.rawKatMaterialPresent)
        assertFalse(result.rawVectorBytesPresent)
        assertFalse(result.rawVectorHexPresent)
        assertFalse(result.publicVectorBytesPresent)
        assertFalse(result.publicVectorHexPresent)
        assertFalse(result.executableKatExecutorPresent)
        assertFalse(result.katRunnerPresent)
        assertFalse(result.katExecutorPresent)
    }

    @Test
    fun executableMetadataKatDoesNotImplementOrExposeVaultCryptoProvider() {
        val result = result()

        assertFalse(result.implementsVaultCryptoProvider)
        assertFalse(result.containsVaultCryptoProvider)
    }

    @Test
    fun executableMetadataKatIsNotSelectedByVaultCryptoProviderSelectionRegistry() {
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertIs<DisabledVaultCryptoProvider>(selection.selectedProvider)
        assertFalse(selection.safeDetail.contains(markerSafeId()))
        assertFalse(selection.safeDetail.contains(fixtureId()))
        assertFalse(selection.safeDetail.contains(vectorId()))
        assertFalse(selection.safeDetail.contains(caseId()))
        assertFalse(selection.safeDetail.contains("SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKat"))
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
        val result = result()

        assertFalse(result.productionProviderSelectable)
        assertFalse(VaultCryptoProviderSelectionRegistry.select().productionProviderSelectable)
    }

    @Test
    fun everyCapabilityAndReachabilityBooleanRemainsFalse() {
        val result = result()
        val blockedBooleans = listOf(
            result.runtimeSelectable,
            result.registrySelectable,
            result.factoryReachable,
            result.dispatcherReachable,
            result.executorTargetable,
            result.providerKatExecutorReachable,
            result.providerOperationReachable,
            result.cryptoExecutionReachable,
            result.vaultLifecycleReachable,
            result.persistenceReachable,
            result.productionSyncReachable,
            result.backendClientReachable,
            result.bdkWalletStateReachable,
            result.settingsCodecReachable,
            result.uiSurfaceReachable,
            result.signingBroadcastingReachable,
            result.publicEndpointReachable,
            result.mainnetReachable,
            result.implementsVaultCryptoProvider,
            result.containsVaultCryptoProvider,
            result.canExecuteProviderOperations,
            result.canExecuteCrypto,
            result.canUseForVaultLifecycle,
            result.canUseForPersistence,
            result.canUseForSync,
            result.canUseForSigning,
            result.canUseForBroadcasting,
            result.canUseForMainnet,
            result.productionProviderSelectable,
            result.evaluationIsProductionAuthorization,
            result.evaluationIsProviderSelectionAuthorization,
            result.evaluationIsKatExecutionAuthorization,
            result.evaluationIsCryptoAuthorization,
            result.evaluationIsVaultPersistenceAuthorization,
            result.evaluationIsMainnetAuthorization,
            result.evaluatesProviderOperation,
            result.evaluatesCryptoOperation,
            result.evaluatesVaultLifecycle,
            result.evaluatesPersistence,
            result.caseBindingIsExecutable,
            result.caseBindingContainsRawBytes,
            result.caseBindingContainsHex,
            result.caseBindingContainsCryptoMaterial,
            result.caseBindingContainsWalletMaterial,
            result.caseBindingContainsEndpointMaterial,
            result.caseBindingContainsProviderHandle,
            result.rawKatMaterialPresent,
            result.rawVectorBytesPresent,
            result.rawVectorHexPresent,
            result.publicVectorBytesPresent,
            result.publicVectorHexPresent,
            result.executableKatExecutorPresent,
            result.katRunnerPresent,
            result.katExecutorPresent,
        )

        assertTrue(blockedBooleans.none { enabled -> enabled })
    }

    @Test
    fun executableMetadataKatIsNotRegistryFactoryDispatcherExecutorOrKatExecutorReachable() {
        val result = result()

        assertFalse(result.registrySelectable)
        assertFalse(result.factoryReachable)
        assertFalse(result.dispatcherReachable)
        assertFalse(result.executorTargetable)
        assertFalse(result.providerKatExecutorReachable)
    }

    @Test
    fun executableMetadataKatCannotExecuteProviderOperationsOrCrypto() {
        val result = result()

        assertFalse(result.canExecuteProviderOperations)
        assertFalse(result.canExecuteCrypto)
    }

    @Test
    fun executableMetadataKatCannotParticipateInVaultLifecyclePersistenceOrProductionSync() {
        val result = result()

        assertFalse(result.canUseForVaultLifecycle)
        assertFalse(result.canUseForPersistence)
        assertFalse(result.canUseForSync)
    }

    @Test
    fun executableMetadataKatCannotSignBroadcastOrEnableMainnet() {
        val result = result()

        assertFalse(result.canUseForSigning)
        assertFalse(result.canUseForBroadcasting)
        assertFalse(result.canUseForMainnet)
    }

    @Test
    fun executableMetadataKatDoesNotExposeRawKatVectorsRuntimeReferencesRawIdsOrMaterialInToString() {
        val output = result().toString()
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
    fun executableMetadataKatClassIdsAndCaseIdAreCommonTestOnlyBySourceGuardContract() {
        val result = result()

        assertTrue(result.evaluationIsCommonTestOnly)
        assertTrue(result.expectedSafeIdMatched)
        assertTrue(result.expectedFixtureIdMatched)
        assertTrue(result.expectedVectorIdMatched)
        assertTrue(result.expectedCaseIdMatched)
        assertTrue(result.metadataKatEvaluated)
        assertTrue(result.metadataKatPassed)
        assertFalse(result.evaluationIsKatExecutionAuthorization)
        assertFalse(result.evaluatesProviderOperation)
        assertFalse(result.evaluatesCryptoOperation)
    }

    private fun result() =
        SkaldVaultV1TestOnlyProviderIdentityExecutableMetadataKatPolicy.evaluateCurrentMetadataKat()

    private fun markerSafeId(): String =
        "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"

    private fun fixtureId(): String =
        "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata"

    private fun vectorId(): String =
        "skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata"

    private fun caseId(): String =
        "skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata"
}
