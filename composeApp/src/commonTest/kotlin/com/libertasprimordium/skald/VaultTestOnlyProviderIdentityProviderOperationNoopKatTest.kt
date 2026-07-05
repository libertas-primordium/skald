package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatResultCategory
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatSourceSet
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityProviderOperationNoopKatTest {
    @Test
    fun noopProviderOperationKatExistsOnlyAsCommonTestTestEvidence() {
        val result = result()

        assertTrue(result.noopKatIsCommonTestOnly)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatSourceSet.CommonTest,
            result.sourceSet,
        )
        assertTrue(result.providerOperationNoopKatEvaluated)
        assertTrue(result.providerOperationNoopKatPassed)
        assertTrue(result.syntheticNoopResultPresent)
        assertFalse(result.productionProviderSelectable)
    }

    @Test
    fun noopProviderOperationKatReadsNoopAdmissionAndProviderOperationMetadataSuiteChain() {
        val result = result()

        assertEquals(1, result.markerCount)
        assertEquals(1, result.caseBindingCount)
        assertEquals(1, result.executableMetadataKatSuiteReportCount)
        assertEquals(1, result.providerOperationKatAdmissionCount)
        assertEquals(1, result.providerOperationMetadataKatCount)
        assertEquals(1, result.providerOperationMetadataKatValidationCount)
        assertEquals(1, result.providerOperationMetadataKatSuiteReportCount)
        assertEquals(1, result.providerOperationNoopKatAdmissionCount)
    }

    @Test
    fun noopProviderOperationKatConfirmsProviderOperationMetadataSuiteEvidenceAndAdmissionModeled() {
        val result = result()

        assertTrue(result.metadataKatSuitePassed)
        assertTrue(result.providerOperationMetadataKatPassed)
        assertTrue(result.providerOperationMetadataKatValidationPassed)
        assertTrue(result.providerOperationMetadataKatSuitePassed)
        assertTrue(result.providerOperationNoopKatAdmissionModeled)
    }

    @Test
    fun noopProviderOperationKatConfirmsExpectedMarkerFixtureVectorCaseAndKatIds() {
        val result = result()

        assertTrue(result.expectedSafeIdMatched)
        assertTrue(result.expectedFixtureIdMatched)
        assertTrue(result.expectedVectorIdMatched)
        assertTrue(result.expectedCaseIdMatched)
        assertTrue(result.expectedProviderOperationMetadataKatIdMatched)
        assertTrue(result.expectedProviderOperationNoopKatIdMatched)
    }

    @Test
    fun allNoopProviderOperationKatResultCategoriesAreRepresentedExactlyOnce() {
        val categories = result().resultCategories

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatResultCategory.entries.size,
            categories.size,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatResultCategory.entries.toSet(),
            categories.toSet(),
        )
    }

    @Test
    fun noopProviderOperationKatEvidenceFlagsAreCommonTestOnlyAndNonAuthorizing() {
        val result = result()

        assertTrue(result.providerOperationNoopKatEvaluated)
        assertTrue(result.providerOperationNoopKatPassed)
        assertTrue(result.syntheticNoopResultPresent)
        assertFalse(result.noopKatIsProductionAuthorization)
        assertFalse(result.noopKatIsProviderSelectionAuthorization)
        assertFalse(result.noopKatIsProviderOperationAuthorization)
        assertFalse(result.noopKatIsKatExecutorAuthorization)
        assertFalse(result.noopKatIsCryptoAuthorization)
        assertFalse(result.noopKatIsVaultPersistenceAuthorization)
        assertFalse(result.noopKatIsMainnetAuthorization)
    }

    @Test
    fun noopProviderOperationKatEvaluatesSyntheticNoopOnlyAndNoRuntimeOperations() {
        val result = result()

        assertTrue(result.evaluatesSyntheticNoopOnly)
        assertFalse(result.evaluatesProviderOperationExecution)
        assertFalse(result.evaluatesCryptoOperation)
        assertFalse(result.evaluatesVaultLifecycle)
        assertFalse(result.evaluatesPersistence)
        assertFalse(result.providerOperationExecutionPresent)
        assertFalse(result.cryptoExecutionPresent)
        assertFalse(result.providerOperationKatExecutorPresent)
        assertFalse(result.providerOperationKatRunnerPresent)
    }

    @Test
    fun noopProviderOperationKatHasNoRawVectorOrPublicVectorByteOrHexMaterial() {
        val result = result()

        assertFalse(result.rawKatMaterialPresent)
        assertFalse(result.rawVectorBytesPresent)
        assertFalse(result.rawVectorHexPresent)
        assertFalse(result.publicVectorBytesPresent)
        assertFalse(result.publicVectorHexPresent)
    }

    @Test
    fun noopProviderOperationKatDoesNotImplementOrExposeVaultCryptoProvider() {
        val result = result()

        assertFalse(result.implementsVaultCryptoProvider)
        assertFalse(result.containsVaultCryptoProvider)
    }

    @Test
    fun noopProviderOperationKatIsNotSelectedByVaultCryptoProviderSelectionRegistry() {
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertIs<DisabledVaultCryptoProvider>(selection.selectedProvider)
        assertTrue(selection.selectedProviderIsDisabled)
        assertFalse(selection.productionProviderSelectable)
        assertFalse(selection.safeDetail.contains(markerSafeId()))
        assertFalse(selection.safeDetail.contains(fixtureId()))
        assertFalse(selection.safeDetail.contains(vectorId()))
        assertFalse(selection.safeDetail.contains(caseId()))
        assertFalse(selection.safeDetail.contains(providerOperationMetadataKatId()))
        assertFalse(selection.safeDetail.contains(providerOperationNoopKatId()))
        assertFalse(
            selection.safeDetail.contains(
                "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKat",
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
            result.noopKatIsProductionAuthorization,
            result.noopKatIsProviderSelectionAuthorization,
            result.noopKatIsProviderOperationAuthorization,
            result.noopKatIsKatExecutorAuthorization,
            result.noopKatIsCryptoAuthorization,
            result.noopKatIsVaultPersistenceAuthorization,
            result.noopKatIsMainnetAuthorization,
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
    fun noopProviderOperationKatIsNotRegistryFactoryDispatcherExecutorOrKatExecutorReachable() {
        val result = result()

        assertFalse(result.registrySelectable)
        assertFalse(result.factoryReachable)
        assertFalse(result.dispatcherReachable)
        assertFalse(result.executorTargetable)
        assertFalse(result.providerKatExecutorReachable)
    }

    @Test
    fun noopProviderOperationKatCannotExecuteProviderOperationsOrCrypto() {
        val result = result()

        assertFalse(result.canExecuteProviderOperations)
        assertFalse(result.canExecuteCrypto)
        assertFalse(result.providerOperationReachable)
        assertFalse(result.cryptoExecutionReachable)
        assertFalse(result.providerOperationExecutionPresent)
        assertFalse(result.cryptoExecutionPresent)
    }

    @Test
    fun noopProviderOperationKatCannotParticipateInVaultLifecyclePersistenceOrProductionSync() {
        val result = result()

        assertFalse(result.canUseForVaultLifecycle)
        assertFalse(result.canUseForPersistence)
        assertFalse(result.canUseForSync)
        assertFalse(result.vaultLifecycleReachable)
        assertFalse(result.persistenceReachable)
        assertFalse(result.productionSyncReachable)
    }

    @Test
    fun noopProviderOperationKatCannotSignBroadcastOrEnableMainnet() {
        val result = result()

        assertFalse(result.canUseForSigning)
        assertFalse(result.canUseForBroadcasting)
        assertFalse(result.canUseForMainnet)
        assertFalse(result.signingBroadcastingReachable)
        assertFalse(result.mainnetReachable)
    }

    @Test
    fun noopProviderOperationKatDoesNotExposeRawKatVectorsRuntimeReferencesRawIdsOrMaterialInToString() {
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
            assertFalse(output.contains(providerOperationNoopKatId()))
            rejectedTerms.forEach { term ->
                assertFalse(output.contains(term, ignoreCase = true), "safe output leaked $term")
            }
        }
    }

    @Test
    fun noopProviderOperationKatClassAdmissionMetadataSuiteChainAndIdsAreCommonTestOnlyBySourceGuardContract() {
        val result = result()

        assertTrue(result.noopKatIsCommonTestOnly)
        assertTrue(result.expectedSafeIdMatched)
        assertTrue(result.expectedFixtureIdMatched)
        assertTrue(result.expectedVectorIdMatched)
        assertTrue(result.expectedCaseIdMatched)
        assertTrue(result.expectedProviderOperationMetadataKatIdMatched)
        assertTrue(result.expectedProviderOperationNoopKatIdMatched)
        assertTrue(result.providerOperationNoopKatEvaluated)
        assertTrue(result.providerOperationNoopKatPassed)
        assertTrue(result.syntheticNoopResultPresent)
        assertFalse(result.noopKatIsProviderOperationAuthorization)
        assertFalse(result.providerOperationExecutionPresent)
        assertFalse(result.cryptoExecutionPresent)
    }

    private fun result() =
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatPolicy
            .evaluateCurrentProviderOperationNoopKat()

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
