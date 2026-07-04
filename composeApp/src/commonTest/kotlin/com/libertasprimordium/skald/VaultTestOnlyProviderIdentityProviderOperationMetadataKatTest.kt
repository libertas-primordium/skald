package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataCategory
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatSourceSet
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProviderOperationShapeLabel
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityProviderOperationMetadataKatTest {
    @Test
    fun providerOperationMetadataKatExistsOnlyAsCommonTestTestEvidence() {
        val result = result()

        assertTrue(result.evaluationIsCommonTestOnly)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatSourceSet.CommonTest,
            result.sourceSet,
        )
        assertTrue(result.providerOperationMetadataKatEvaluated)
        assertTrue(result.providerOperationMetadataKatPassed)
        assertFalse(result.productionProviderSelectable)
    }

    @Test
    fun providerOperationMetadataKatEvaluatesExactlyOneProviderOperationShapedMetadataCase() {
        val result = result()

        assertEquals(1, result.markerCount)
        assertEquals(1, result.fixtureRowCount)
        assertEquals(1, result.publicVectorRowCount)
        assertEquals(1, result.caseBindingCount)
        assertEquals(1, result.executableMetadataKatSuiteReportCount)
        assertEquals(1, result.providerOperationKatAdmissionCount)
        assertTrue(result.evaluatesProviderOperationShapeOnly)
        assertFalse(result.evaluatesProviderOperationExecution)
    }

    @Test
    fun providerOperationMetadataKatReadsProviderOperationKatAdmissionGateAndExecutableMetadataKatSuiteReport() {
        val result = result()

        assertEquals(1, result.providerOperationKatAdmissionCount)
        assertEquals(1, result.executableMetadataKatSuiteReportCount)
        assertTrue(result.metadataKatSuitePassed)
        assertTrue(result.providerOperationAdmissionModeled)
    }

    @Test
    fun providerOperationMetadataKatConfirmsMetadataKatSuitePassedTrue() {
        assertTrue(result().metadataKatSuitePassed)
    }

    @Test
    fun providerOperationMetadataKatConfirmsProviderOperationAdmissionIsModeled() {
        assertTrue(result().providerOperationAdmissionModeled)
    }

    @Test
    fun providerOperationMetadataKatConfirmsExpectedMarkerFixtureVectorCaseAndProviderOperationMetadataKatIds() {
        val result = result()

        assertTrue(result.expectedSafeIdMatched)
        assertTrue(result.expectedFixtureIdMatched)
        assertTrue(result.expectedVectorIdMatched)
        assertTrue(result.expectedCaseIdMatched)
        assertTrue(result.expectedProviderOperationMetadataKatIdMatched)
    }

    @Test
    fun allProviderOperationMetadataCategoriesAreRepresentedExactlyOnce() {
        val categories = result().metadataCategories

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataCategory.entries.size,
            categories.size,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataCategory.entries.toSet(),
            categories.toSet(),
        )
    }

    @Test
    fun allProviderOperationShapeLabelsAreRepresentedExactlyOnce() {
        val shapeLabels = result().providerOperationShapeLabels

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationShapeLabel.entries.size,
            shapeLabels.size,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationShapeLabel.entries.toSet(),
            shapeLabels.toSet(),
        )
    }

    @Test
    fun providerOperationMetadataKatEvaluatedTrueIsCommonTestOnlyTestEvidenceOnly() {
        val result = result()

        assertTrue(result.providerOperationMetadataKatEvaluated)
        assertTrue(result.evaluationIsCommonTestOnly)
        assertFalse(result.evaluationIsProductionAuthorization)
        assertFalse(result.evaluationIsProviderSelectionAuthorization)
        assertFalse(result.evaluationIsProviderOperationAuthorization)
    }

    @Test
    fun providerOperationMetadataKatPassedTrueIsCommonTestOnlyTestEvidenceOnly() {
        val result = result()

        assertTrue(result.providerOperationMetadataKatPassed)
        assertTrue(result.evaluationIsCommonTestOnly)
        assertFalse(result.evaluationIsKatExecutorAuthorization)
        assertFalse(result.evaluationIsCryptoAuthorization)
        assertFalse(result.evaluationIsVaultPersistenceAuthorization)
        assertFalse(result.evaluationIsMainnetAuthorization)
    }

    @Test
    fun providerOperationMetadataKatIsNotProductionProviderSelectionProviderOperationKatExecutorCryptoVaultOrMainnetAuthorization() {
        val result = result()

        assertFalse(result.evaluationIsProductionAuthorization)
        assertFalse(result.evaluationIsProviderSelectionAuthorization)
        assertFalse(result.evaluationIsProviderOperationAuthorization)
        assertFalse(result.evaluationIsKatExecutorAuthorization)
        assertFalse(result.evaluationIsCryptoAuthorization)
        assertFalse(result.evaluationIsVaultPersistenceAuthorization)
        assertFalse(result.evaluationIsMainnetAuthorization)
    }

    @Test
    fun providerOperationMetadataKatEvaluatesProviderOperationShapeOnly() {
        val result = result()

        assertTrue(result.evaluatesProviderOperationShapeOnly)
        assertFalse(result.evaluatesProviderOperationExecution)
        assertFalse(result.evaluatesCryptoOperation)
        assertFalse(result.evaluatesVaultLifecycle)
        assertFalse(result.evaluatesPersistence)
    }

    @Test
    fun providerOperationMetadataKatDoesNotExposeExecutorRunnerProviderOperationCryptoOrVectorMaterial() {
        val result = result()

        assertFalse(result.providerOperationKatExecutorPresent)
        assertFalse(result.providerOperationKatRunnerPresent)
        assertFalse(result.providerOperationExecutionPresent)
        assertFalse(result.cryptoExecutionPresent)
        assertFalse(result.rawKatMaterialPresent)
        assertFalse(result.rawVectorBytesPresent)
        assertFalse(result.rawVectorHexPresent)
        assertFalse(result.publicVectorBytesPresent)
        assertFalse(result.publicVectorHexPresent)
    }

    @Test
    fun providerOperationMetadataKatDoesNotImplementOrExposeVaultCryptoProvider() {
        val result = result()

        assertFalse(result.implementsVaultCryptoProvider)
        assertFalse(result.containsVaultCryptoProvider)
    }

    @Test
    fun providerOperationMetadataKatIsNotSelectedByVaultCryptoProviderSelectionRegistry() {
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertIs<DisabledVaultCryptoProvider>(selection.selectedProvider)
        assertFalse(selection.safeDetail.contains(markerSafeId()))
        assertFalse(selection.safeDetail.contains(fixtureId()))
        assertFalse(selection.safeDetail.contains(vectorId()))
        assertFalse(selection.safeDetail.contains(caseId()))
        assertFalse(selection.safeDetail.contains(providerOperationMetadataKatId()))
        assertFalse(selection.safeDetail.contains("SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKat"))
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
            result.evaluationIsProviderOperationAuthorization,
            result.evaluationIsKatExecutorAuthorization,
            result.evaluationIsCryptoAuthorization,
            result.evaluationIsVaultPersistenceAuthorization,
            result.evaluationIsMainnetAuthorization,
            result.evaluatesProviderOperationExecution,
            result.evaluatesCryptoOperation,
            result.evaluatesVaultLifecycle,
            result.evaluatesPersistence,
            result.providerOperationKatExecutorPresent,
            result.providerOperationKatRunnerPresent,
            result.providerOperationExecutionPresent,
            result.cryptoExecutionPresent,
            result.rawKatMaterialPresent,
            result.rawVectorBytesPresent,
            result.rawVectorHexPresent,
            result.publicVectorBytesPresent,
            result.publicVectorHexPresent,
        )

        assertTrue(blockedBooleans.none { enabled -> enabled })
    }

    @Test
    fun providerOperationMetadataKatIsNotRegistryFactoryDispatcherExecutorOrProviderKatExecutorReachable() {
        val result = result()

        assertFalse(result.registrySelectable)
        assertFalse(result.factoryReachable)
        assertFalse(result.dispatcherReachable)
        assertFalse(result.executorTargetable)
        assertFalse(result.providerKatExecutorReachable)
    }

    @Test
    fun providerOperationMetadataKatCannotExecuteProviderOperationsOrCrypto() {
        val result = result()

        assertFalse(result.canExecuteProviderOperations)
        assertFalse(result.canExecuteCrypto)
        assertFalse(result.providerOperationExecutionPresent)
        assertFalse(result.cryptoExecutionPresent)
    }

    @Test
    fun providerOperationMetadataKatCannotParticipateInVaultLifecyclePersistenceOrProductionSync() {
        val result = result()

        assertFalse(result.canUseForVaultLifecycle)
        assertFalse(result.canUseForPersistence)
        assertFalse(result.canUseForSync)
        assertFalse(result.vaultLifecycleReachable)
        assertFalse(result.persistenceReachable)
        assertFalse(result.productionSyncReachable)
    }

    @Test
    fun providerOperationMetadataKatCannotSignBroadcastOrEnableMainnet() {
        val result = result()

        assertFalse(result.canUseForSigning)
        assertFalse(result.canUseForBroadcasting)
        assertFalse(result.canUseForMainnet)
        assertFalse(result.signingBroadcastingReachable)
        assertFalse(result.mainnetReachable)
    }

    @Test
    fun providerOperationMetadataKatDoesNotExposeRawKatVectorsRuntimeReferencesRawIdsOrMaterialInToString() {
        val result = result()
        val outputs = listOf(
            result.toString(),
            result.displayLabel.toString(),
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
    fun providerOperationMetadataKatClassAdmissionSuiteAndIdsAreCommonTestOnlyBySourceGuardContract() {
        val result = result()

        assertTrue(result.evaluationIsCommonTestOnly)
        assertTrue(result.expectedSafeIdMatched)
        assertTrue(result.expectedFixtureIdMatched)
        assertTrue(result.expectedVectorIdMatched)
        assertTrue(result.expectedCaseIdMatched)
        assertTrue(result.expectedProviderOperationMetadataKatIdMatched)
        assertTrue(result.metadataKatSuitePassed)
        assertTrue(result.providerOperationAdmissionModeled)
        assertTrue(result.providerOperationMetadataKatEvaluated)
        assertTrue(result.providerOperationMetadataKatPassed)
        assertFalse(result.evaluationIsProviderOperationAuthorization)
        assertFalse(result.evaluatesProviderOperationExecution)
        assertFalse(result.evaluatesCryptoOperation)
    }

    private fun result() =
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatPolicy
            .evaluateCurrentProviderOperationMetadataKat()

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
