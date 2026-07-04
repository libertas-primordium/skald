package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityKatFixtureForbiddenMaterialClass
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityKatFixtureScopeCategory
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityKatFixtureScopePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityKatFixtureScopeSourceSet
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProfilePolicy
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityKatFixtureScopeTest {
    @Test
    fun katFixtureScopeExistsOnlyAsCommonTestInertMetadata() {
        val scope = scope()

        assertTrue(scope.fixtureScopeIsCommonTestOnly)
        assertEquals(SkaldVaultV1TestOnlyProviderIdentityKatFixtureScopeSourceSet.CommonTest, scope.sourceSet)
        assertTrue(scope.futureFixtureMetadataOnly)
        assertFalse(scope.runtimeSelectable)
        assertFalse(scope.productionProviderSelectable)
    }

    @Test
    fun katFixtureScopeReadsExactlyOneMarkerInventoryProfileValidationReportReachabilityProofAndCapabilityMatrix() {
        val scope = scope()

        assertEquals(1, scope.markerCount)
        assertEquals(1, scope.inventoryCount)
        assertEquals(1, scope.profileCount)
        assertEquals(1, scope.validationReportCount)
        assertEquals(1, scope.reachabilityProofCount)
        assertEquals(1, scope.capabilityMatrixCount)
    }

    @Test
    fun katFixtureScopeConfirmsMarkerSafeIdIsExpected() {
        val scope = scope()

        assertTrue(scope.expectedSafeIdMatched)
        assertEquals(
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            SkaldVaultV1TestOnlyProviderIdentityProfilePolicy.currentProfile().marker.safeId.value,
        )
    }

    @Test
    fun allFixtureScopeCategoriesAreRepresentedExactlyOnce() {
        val scope = scope()

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityKatFixtureScopeCategory.entries.toSet(),
            scope.fixtureScopeCategoryRows.map { row -> row.category }.toSet(),
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityKatFixtureScopeCategory.entries.size,
            scope.fixtureScopeCategoryRows.size,
        )
        assertTrue(scope.fixtureScopeCategoryRows.all { row -> row.metadataOnly && !row.authorizesExecution })
    }

    @Test
    fun allForbiddenFixtureMaterialClassesAreRepresentedExactlyOnce() {
        val scope = scope()

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityKatFixtureForbiddenMaterialClass.entries.toSet(),
            scope.forbiddenMaterialRows.map { row -> row.materialClass }.toSet(),
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityKatFixtureForbiddenMaterialClass.entries.size,
            scope.forbiddenMaterialRows.size,
        )
        assertTrue(scope.forbiddenMaterialRows.all { row -> row.forbidden && !row.present })
    }

    @Test
    fun katFixtureScopeIsNotProductionAuthorization() {
        assertFalse(scope().fixtureScopeIsProductionAuthorization)
    }

    @Test
    fun katFixtureScopeIsNotProviderSelectionAuthorization() {
        assertFalse(scope().fixtureScopeIsProviderSelectionAuthorization)
    }

    @Test
    fun katFixtureScopeIsNotKatExecutionAuthorization() {
        assertFalse(scope().fixtureScopeIsKatExecutionAuthorization)
    }

    @Test
    fun katFixtureScopeIsNotCryptoAuthorization() {
        assertFalse(scope().fixtureScopeIsCryptoAuthorization)
    }

    @Test
    fun katFixtureScopeIsNotVaultPersistenceAuthorization() {
        assertFalse(scope().fixtureScopeIsVaultPersistenceAuthorization)
    }

    @Test
    fun katFixtureScopeIsNotMainnetAuthorization() {
        assertFalse(scope().fixtureScopeIsMainnetAuthorization)
    }

    @Test
    fun katFixtureScopeHasNoRawKatMaterialExecutableKatOrKatExecutor() {
        val scope = scope()

        assertFalse(scope.rawKatMaterialPresent)
        assertFalse(scope.executableKatPresent)
        assertFalse(scope.katExecutorPresent)
    }

    @Test
    fun katFixtureScopeDoesNotImplementVaultCryptoProvider() {
        assertFalse(scope().implementsVaultCryptoProvider)
    }

    @Test
    fun katFixtureScopeDoesNotExposeVaultCryptoProviderInstance() {
        assertFalse(scope().containsVaultCryptoProvider)
    }

    @Test
    fun katFixtureScopeIsNotSelectedByVaultCryptoProviderSelectionRegistry() {
        val result = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, result.selectedCandidateId)
        assertIs<DisabledVaultCryptoProvider>(result.selectedProvider)
        assertFalse(
            result.safeDetail.contains(
                "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
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
        val scope = scope()

        assertFalse(scope.productionProviderSelectable)
        assertFalse(VaultCryptoProviderSelectionRegistry.select().productionProviderSelectable)
    }

    @Test
    fun everyCapabilityAndReachabilityBooleanRemainsFalse() {
        val scope = scope()
        val blockedBooleans = listOf(
            scope.runtimeSelectable,
            scope.registrySelectable,
            scope.factoryReachable,
            scope.dispatcherReachable,
            scope.executorTargetable,
            scope.providerKatExecutorReachable,
            scope.providerOperationReachable,
            scope.cryptoExecutionReachable,
            scope.vaultLifecycleReachable,
            scope.persistenceReachable,
            scope.productionSyncReachable,
            scope.backendClientReachable,
            scope.bdkWalletStateReachable,
            scope.settingsCodecReachable,
            scope.uiSurfaceReachable,
            scope.signingBroadcastingReachable,
            scope.publicEndpointReachable,
            scope.mainnetReachable,
            scope.implementsVaultCryptoProvider,
            scope.containsVaultCryptoProvider,
            scope.canExecuteProviderOperations,
            scope.canExecuteCrypto,
            scope.canUseForVaultLifecycle,
            scope.canUseForPersistence,
            scope.canUseForSync,
            scope.canUseForSigning,
            scope.canUseForBroadcasting,
            scope.canUseForMainnet,
            scope.productionProviderSelectable,
        )

        assertTrue(blockedBooleans.none { enabled -> enabled })
    }

    @Test
    fun katFixtureScopeIsNotRegistryFactoryDispatcherExecutorOrKatExecutorReachable() {
        val scope = scope()

        assertFalse(scope.registrySelectable)
        assertFalse(scope.factoryReachable)
        assertFalse(scope.dispatcherReachable)
        assertFalse(scope.executorTargetable)
        assertFalse(scope.providerKatExecutorReachable)
    }

    @Test
    fun katFixtureScopeCannotExecuteProviderOperationsOrCrypto() {
        val scope = scope()

        assertFalse(scope.canExecuteProviderOperations)
        assertFalse(scope.canExecuteCrypto)
    }

    @Test
    fun katFixtureScopeCannotParticipateInVaultLifecyclePersistenceOrProductionSync() {
        val scope = scope()

        assertFalse(scope.canUseForVaultLifecycle)
        assertFalse(scope.canUseForPersistence)
        assertFalse(scope.canUseForSync)
    }

    @Test
    fun katFixtureScopeCannotSignBroadcastOrEnableMainnet() {
        val scope = scope()

        assertFalse(scope.canUseForSigning)
        assertFalse(scope.canUseForBroadcasting)
        assertFalse(scope.canUseForMainnet)
    }

    @Test
    fun katFixtureScopeDoesNotExposeRawKatVectorsRuntimeReferencesOrRawSafeIdsInToString() {
        val scope = scope()
        val output = scope.toString()
        val labelOutput = scope.displayLabel.toString()
        val safeId = "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"
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

        assertFalse(output.contains(safeId))
        assertFalse(labelOutput.contains(safeId))
        rejectedTerms.forEach { term ->
            assertFalse(output.contains(term, ignoreCase = true), "safe output leaked $term")
            assertFalse(labelOutput.contains(term, ignoreCase = true), "safe label leaked $term")
        }
    }

    @Test
    fun katFixtureScopeClassAndSafeIdAreCommonTestOnlyBySourceGuardContract() {
        val scope = scope()

        assertTrue(scope.fixtureScopeIsCommonTestOnly)
        assertTrue(scope.futureFixtureMetadataOnly)
        assertFalse(scope.fixtureScopeIsKatExecutionAuthorization)
        assertEquals(
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            SkaldVaultV1TestOnlyProviderIdentityProfilePolicy.currentProfile().marker.safeId.value,
        )
    }

    private fun scope() =
        SkaldVaultV1TestOnlyProviderIdentityKatFixtureScopePolicy.currentKatFixtureScope()
}
