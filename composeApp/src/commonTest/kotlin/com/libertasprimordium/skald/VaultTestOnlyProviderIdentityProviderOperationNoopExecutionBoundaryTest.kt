package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundarySourceSet
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryState
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryTest {
    @Test
    fun noopProviderOperationExecutionBoundaryExistsOnlyAsCommonTestBoundaryEvidence() {
        val boundary = boundary()

        assertTrue(boundary.boundaryIsCommonTestOnly)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundarySourceSet.CommonTest,
            boundary.sourceSet,
        )
        assertTrue(boundary.noopExecutionBoundaryModeled)
        assertTrue(boundary.syntheticNoopEvaluationPermitted)
        assertFalse(boundary.productionProviderSelectable)
    }

    @Test
    fun boundaryReadsNoopProviderOperationKatSuiteValidationKatAdmissionAndMetadataSuiteChain() {
        val boundary = boundary()

        assertEquals(1, boundary.markerCount)
        assertEquals(1, boundary.caseBindingCount)
        assertEquals(1, boundary.providerOperationMetadataKatCount)
        assertEquals(1, boundary.providerOperationMetadataKatValidationCount)
        assertEquals(1, boundary.providerOperationMetadataKatSuiteReportCount)
        assertEquals(1, boundary.providerOperationNoopKatAdmissionCount)
        assertEquals(1, boundary.providerOperationNoopKatCount)
        assertEquals(1, boundary.providerOperationNoopKatValidationCount)
        assertEquals(1, boundary.providerOperationNoopKatSuiteReportCount)
    }

    @Test
    fun boundaryConfirmsNoopKatSuiteValidationAndSyntheticNoopEvidence() {
        val boundary = boundary()

        assertTrue(boundary.providerOperationNoopKatPassed)
        assertTrue(boundary.providerOperationNoopKatValidationPassed)
        assertTrue(boundary.providerOperationNoopKatSuitePassed)
        assertTrue(boundary.syntheticNoopResultPresent)
    }

    @Test
    fun boundaryConfirmsExpectedMarkerFixtureVectorCaseKatAndBoundaryIds() {
        val boundary = boundary()

        assertTrue(boundary.expectedSafeIdMatched)
        assertTrue(boundary.expectedFixtureIdMatched)
        assertTrue(boundary.expectedVectorIdMatched)
        assertTrue(boundary.expectedCaseIdMatched)
        assertTrue(boundary.expectedProviderOperationMetadataKatIdMatched)
        assertTrue(boundary.expectedProviderOperationNoopKatIdMatched)
        assertTrue(boundary.expectedProviderOperationNoopExecutionBoundaryIdMatched)
    }

    @Test
    fun allNoopProviderOperationExecutionBoundaryStatesAreRepresentedExactlyOnce() {
        val boundary = boundary()

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryState.entries.size,
            boundary.boundaryStates.size,
        )
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryState.entries.forEach { state ->
            assertEquals(1, boundary.boundaryStates.count { represented -> represented == state })
        }
    }

    @Test
    fun boundaryEvidenceFlagsAreCommonTestOnlyAndNonAuthorizing() {
        val boundary = boundary()

        assertTrue(boundary.noopExecutionBoundaryModeled)
        assertTrue(boundary.syntheticNoopEvaluationPermitted)
        assertTrue(boundary.boundaryIsCommonTestOnly)
        assertFalse(boundary.boundaryIsProductionAuthorization)
        assertFalse(boundary.boundaryIsProviderSelectionAuthorization)
        assertFalse(boundary.boundaryIsProviderOperationAuthorization)
        assertFalse(boundary.boundaryIsKatExecutorAuthorization)
        assertFalse(boundary.boundaryIsCryptoAuthorization)
        assertFalse(boundary.boundaryIsVaultPersistenceAuthorization)
        assertFalse(boundary.boundaryIsMainnetAuthorization)
    }

    @Test
    fun boundaryDoesNotPermitRealProviderOperationCryptoKatRunnerKatExecutorVaultPersistenceOrMainnet() {
        val boundary = boundary()

        assertFalse(boundary.realProviderOperationExecutionPermitted)
        assertFalse(boundary.cryptoExecutionPermitted)
        assertFalse(boundary.katRunnerPermitted)
        assertFalse(boundary.katExecutorPermitted)
        assertFalse(boundary.vaultPersistencePermitted)
        assertFalse(boundary.mainnetPermitted)
    }

    @Test
    fun boundaryEvaluatesSyntheticNoopOnlyAndNoRuntimeOperations() {
        val boundary = boundary()

        assertTrue(boundary.evaluatesSyntheticNoopOnly)
        assertFalse(boundary.evaluatesProviderOperationExecution)
        assertFalse(boundary.evaluatesCryptoOperation)
        assertFalse(boundary.evaluatesVaultLifecycle)
        assertFalse(boundary.evaluatesPersistence)
        assertFalse(boundary.providerOperationKatExecutorPresent)
        assertFalse(boundary.providerOperationKatRunnerPresent)
        assertFalse(boundary.providerOperationExecutionPresent)
        assertFalse(boundary.cryptoExecutionPresent)
    }

    @Test
    fun boundaryHasNoRawVectorOrPublicVectorByteOrHexMaterial() {
        val boundary = boundary()

        assertFalse(boundary.rawKatMaterialPresent)
        assertFalse(boundary.rawVectorBytesPresent)
        assertFalse(boundary.rawVectorHexPresent)
        assertFalse(boundary.publicVectorBytesPresent)
        assertFalse(boundary.publicVectorHexPresent)
    }

    @Test
    fun boundaryDoesNotImplementOrExposeVaultCryptoProvider() {
        val boundary = boundary()

        assertFalse(boundary.implementsVaultCryptoProvider)
        assertFalse(boundary.containsVaultCryptoProvider)
    }

    @Test
    fun boundaryIsNotSelectedByVaultCryptoProviderSelectionRegistry() {
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
        assertFalse(selection.safeDetail.contains(providerOperationNoopExecutionBoundaryId()))
        assertFalse(
            selection.safeDetail.contains(
                "SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundary",
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
        val boundary = boundary()

        assertFalse(boundary.productionProviderSelectable)
        assertFalse(VaultCryptoProviderSelectionRegistry.select().productionProviderSelectable)
    }

    @Test
    fun everyCapabilityAndReachabilityBooleanRemainsFalse() {
        val boundary = boundary()
        val blockedBooleans = listOf(
            boundary.runtimeSelectable,
            boundary.registrySelectable,
            boundary.factoryReachable,
            boundary.dispatcherReachable,
            boundary.executorTargetable,
            boundary.providerKatExecutorReachable,
            boundary.providerOperationReachable,
            boundary.cryptoExecutionReachable,
            boundary.vaultLifecycleReachable,
            boundary.persistenceReachable,
            boundary.productionSyncReachable,
            boundary.backendClientReachable,
            boundary.bdkWalletStateReachable,
            boundary.settingsCodecReachable,
            boundary.uiSurfaceReachable,
            boundary.signingBroadcastingReachable,
            boundary.publicEndpointReachable,
            boundary.mainnetReachable,
            boundary.implementsVaultCryptoProvider,
            boundary.containsVaultCryptoProvider,
            boundary.canExecuteProviderOperations,
            boundary.canExecuteCrypto,
            boundary.canUseForVaultLifecycle,
            boundary.canUseForPersistence,
            boundary.canUseForSync,
            boundary.canUseForSigning,
            boundary.canUseForBroadcasting,
            boundary.canUseForMainnet,
            boundary.productionProviderSelectable,
            boundary.boundaryIsProductionAuthorization,
            boundary.boundaryIsProviderSelectionAuthorization,
            boundary.boundaryIsProviderOperationAuthorization,
            boundary.boundaryIsKatExecutorAuthorization,
            boundary.boundaryIsCryptoAuthorization,
            boundary.boundaryIsVaultPersistenceAuthorization,
            boundary.boundaryIsMainnetAuthorization,
            boundary.realProviderOperationExecutionPermitted,
            boundary.cryptoExecutionPermitted,
            boundary.katRunnerPermitted,
            boundary.katExecutorPermitted,
            boundary.vaultPersistencePermitted,
            boundary.mainnetPermitted,
            boundary.evaluatesProviderOperationExecution,
            boundary.evaluatesCryptoOperation,
            boundary.evaluatesVaultLifecycle,
            boundary.evaluatesPersistence,
            boundary.providerOperationKatExecutorPresent,
            boundary.providerOperationKatRunnerPresent,
            boundary.providerOperationExecutionPresent,
            boundary.cryptoExecutionPresent,
            boundary.rawKatMaterialPresent,
            boundary.rawVectorBytesPresent,
            boundary.rawVectorHexPresent,
            boundary.publicVectorBytesPresent,
            boundary.publicVectorHexPresent,
        )

        assertTrue(blockedBooleans.none { enabled -> enabled })
    }

    @Test
    fun boundaryIsNotRegistryFactoryDispatcherExecutorOrKatExecutorReachable() {
        val boundary = boundary()

        assertFalse(boundary.registrySelectable)
        assertFalse(boundary.factoryReachable)
        assertFalse(boundary.dispatcherReachable)
        assertFalse(boundary.executorTargetable)
        assertFalse(boundary.providerKatExecutorReachable)
    }

    @Test
    fun boundaryCannotExecuteProviderOperationsOrCrypto() {
        val boundary = boundary()

        assertFalse(boundary.canExecuteProviderOperations)
        assertFalse(boundary.canExecuteCrypto)
        assertFalse(boundary.providerOperationReachable)
        assertFalse(boundary.cryptoExecutionReachable)
        assertFalse(boundary.providerOperationExecutionPresent)
        assertFalse(boundary.cryptoExecutionPresent)
        assertFalse(boundary.realProviderOperationExecutionPermitted)
        assertFalse(boundary.cryptoExecutionPermitted)
    }

    @Test
    fun boundaryCannotParticipateInVaultLifecyclePersistenceOrProductionSync() {
        val boundary = boundary()

        assertFalse(boundary.canUseForVaultLifecycle)
        assertFalse(boundary.canUseForPersistence)
        assertFalse(boundary.canUseForSync)
        assertFalse(boundary.vaultLifecycleReachable)
        assertFalse(boundary.persistenceReachable)
        assertFalse(boundary.productionSyncReachable)
        assertFalse(boundary.vaultPersistencePermitted)
    }

    @Test
    fun boundaryCannotSignBroadcastOrEnableMainnet() {
        val boundary = boundary()

        assertFalse(boundary.canUseForSigning)
        assertFalse(boundary.canUseForBroadcasting)
        assertFalse(boundary.canUseForMainnet)
        assertFalse(boundary.signingBroadcastingReachable)
        assertFalse(boundary.mainnetReachable)
        assertFalse(boundary.mainnetPermitted)
    }

    @Test
    fun boundaryDoesNotExposeRawKatVectorsRuntimeReferencesRawIdsOrMaterialInToString() {
        val boundary = boundary()
        val outputs = listOf(
            boundary.toString(),
            boundary.displayLabel.toString(),
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
            assertFalse(output.contains(providerOperationNoopExecutionBoundaryId()))
            rejectedTerms.forEach { term ->
                assertFalse(output.contains(term, ignoreCase = true), "safe output leaked $term")
            }
        }
    }

    @Test
    fun noopProviderOperationExecutionBoundaryClassAndUpstreamChainAreCommonTestOnlyBySourceGuardContract() {
        val boundary = boundary()

        assertTrue(boundary.boundaryIsCommonTestOnly)
        assertTrue(boundary.expectedSafeIdMatched)
        assertTrue(boundary.expectedFixtureIdMatched)
        assertTrue(boundary.expectedVectorIdMatched)
        assertTrue(boundary.expectedCaseIdMatched)
        assertTrue(boundary.expectedProviderOperationMetadataKatIdMatched)
        assertTrue(boundary.expectedProviderOperationNoopKatIdMatched)
        assertTrue(boundary.expectedProviderOperationNoopExecutionBoundaryIdMatched)
        assertTrue(boundary.providerOperationNoopKatPassed)
        assertTrue(boundary.providerOperationNoopKatValidationPassed)
        assertTrue(boundary.providerOperationNoopKatSuitePassed)
        assertTrue(boundary.syntheticNoopResultPresent)
        assertTrue(boundary.noopExecutionBoundaryModeled)
        assertTrue(boundary.syntheticNoopEvaluationPermitted)
        assertFalse(boundary.boundaryIsProviderOperationAuthorization)
        assertFalse(boundary.realProviderOperationExecutionPermitted)
        assertFalse(boundary.providerOperationExecutionPresent)
        assertFalse(boundary.cryptoExecutionPresent)
    }

    private fun boundary() =
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundaryPolicy
            .currentProviderOperationNoopExecutionBoundary()

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

    private fun providerOperationNoopExecutionBoundaryId(): String =
        "skald-test-only-provider-identity-provider-operation-noop-execution-boundary-v1-inert-identity"
}
