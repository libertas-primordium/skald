package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalogPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalogSourceSet
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityKatFixtureCategory
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityKatFixtureMaterialKind
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityKatFixturePurpose
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProfilePolicy
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityKatFixtureCatalogTest {
    @Test
    fun katFixtureCatalogExistsOnlyAsCommonTestInertMetadata() {
        val catalog = catalog()

        assertTrue(catalog.catalogIsCommonTestOnly)
        assertEquals(SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalogSourceSet.CommonTest, catalog.sourceSet)
        assertTrue(catalog.fixtureMetadataOnly)
        assertFalse(catalog.runtimeSelectable)
        assertFalse(catalog.productionProviderSelectable)
    }

    @Test
    fun katFixtureCatalogReadsExactlyOnePriorIdentityChainArtifactAndFixtureScope() {
        val catalog = catalog()

        assertEquals(1, catalog.markerCount)
        assertEquals(1, catalog.inventoryCount)
        assertEquals(1, catalog.profileCount)
        assertEquals(1, catalog.validationReportCount)
        assertEquals(1, catalog.reachabilityProofCount)
        assertEquals(1, catalog.capabilityMatrixCount)
        assertEquals(1, catalog.fixtureScopeCount)
    }

    @Test
    fun katFixtureCatalogContainsExactlyOneFixtureMetadataRow() {
        val catalog = catalog()

        assertTrue(catalog.catalogContainsExactlyOneFixture)
        assertEquals(1, catalog.fixtureRows.size)
        assertTrue(catalog.fixtureRows.single().catalogContainsExactlyOneFixture)
    }

    @Test
    fun katFixtureMetadataRowReferencesExpectedMarkerSafeId() {
        val row = catalog().fixtureRows.single()

        assertTrue(row.expectedSafeIdMatched)
        assertEquals(
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            row.markerSafeId.value,
        )
        assertEquals(
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            SkaldVaultV1TestOnlyProviderIdentityProfilePolicy.currentProfile().marker.safeId.value,
        )
    }

    @Test
    fun katFixtureCatalogUsesExpectedFixtureId() {
        val row = catalog().fixtureRows.single()

        assertEquals(
            "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata",
            row.fixtureId.value,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityKatFixturePurpose.InertIdentityMetadataOnly,
            row.fixturePurpose,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityKatFixtureCategory.IdentityMarkerFixtureMetadata,
            row.fixtureCategory,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityKatFixtureMaterialKind.NoRawKatMaterial,
            row.fixtureMaterialKind,
        )
    }

    @Test
    fun katFixtureCatalogIsNotProductionAuthorization() {
        assertFalse(catalog().catalogIsProductionAuthorization)
        assertFalse(catalog().fixtureRows.single().catalogIsProductionAuthorization)
    }

    @Test
    fun katFixtureCatalogIsNotProviderSelectionAuthorization() {
        assertFalse(catalog().catalogIsProviderSelectionAuthorization)
        assertFalse(catalog().fixtureRows.single().catalogIsProviderSelectionAuthorization)
    }

    @Test
    fun katFixtureCatalogIsNotKatExecutionAuthorization() {
        assertFalse(catalog().catalogIsKatExecutionAuthorization)
        assertFalse(catalog().fixtureRows.single().catalogIsKatExecutionAuthorization)
    }

    @Test
    fun katFixtureCatalogIsNotCryptoAuthorization() {
        assertFalse(catalog().catalogIsCryptoAuthorization)
        assertFalse(catalog().fixtureRows.single().catalogIsCryptoAuthorization)
    }

    @Test
    fun katFixtureCatalogIsNotVaultPersistenceAuthorization() {
        assertFalse(catalog().catalogIsVaultPersistenceAuthorization)
        assertFalse(catalog().fixtureRows.single().catalogIsVaultPersistenceAuthorization)
    }

    @Test
    fun katFixtureCatalogIsNotMainnetAuthorization() {
        assertFalse(catalog().catalogIsMainnetAuthorization)
        assertFalse(catalog().fixtureRows.single().catalogIsMainnetAuthorization)
    }

    @Test
    fun katFixtureCatalogHasNoRawKatMaterialVectorsExecutableKatOrKatExecutor() {
        val catalog = catalog()
        val row = catalog.fixtureRows.single()

        assertFalse(catalog.rawKatMaterialPresent)
        assertFalse(catalog.rawVectorBytesPresent)
        assertFalse(catalog.rawVectorHexPresent)
        assertFalse(catalog.executableKatPresent)
        assertFalse(catalog.katExecutorPresent)
        assertFalse(row.rawKatMaterialPresent)
        assertFalse(row.rawVectorBytesPresent)
        assertFalse(row.rawVectorHexPresent)
        assertFalse(row.executableKatPresent)
        assertFalse(row.katExecutorPresent)
    }

    @Test
    fun katFixtureCatalogDoesNotImplementVaultCryptoProvider() {
        assertFalse(catalog().implementsVaultCryptoProvider)
        assertFalse(catalog().fixtureRows.single().implementsVaultCryptoProvider)
    }

    @Test
    fun katFixtureCatalogDoesNotExposeVaultCryptoProviderInstance() {
        assertFalse(catalog().containsVaultCryptoProvider)
        assertFalse(catalog().fixtureRows.single().containsVaultCryptoProvider)
    }

    @Test
    fun katFixtureCatalogIsNotSelectedByVaultCryptoProviderSelectionRegistry() {
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
        val catalog = catalog()

        assertFalse(catalog.productionProviderSelectable)
        assertFalse(catalog.fixtureRows.single().productionProviderSelectable)
        assertFalse(VaultCryptoProviderSelectionRegistry.select().productionProviderSelectable)
    }

    @Test
    fun everyCapabilityAndReachabilityBooleanRemainsFalse() {
        val catalog = catalog()
        val row = catalog.fixtureRows.single()
        val blockedBooleans = listOf(
            catalog.runtimeSelectable,
            catalog.registrySelectable,
            catalog.factoryReachable,
            catalog.dispatcherReachable,
            catalog.executorTargetable,
            catalog.providerKatExecutorReachable,
            catalog.providerOperationReachable,
            catalog.cryptoExecutionReachable,
            catalog.vaultLifecycleReachable,
            catalog.persistenceReachable,
            catalog.productionSyncReachable,
            catalog.backendClientReachable,
            catalog.bdkWalletStateReachable,
            catalog.settingsCodecReachable,
            catalog.uiSurfaceReachable,
            catalog.signingBroadcastingReachable,
            catalog.publicEndpointReachable,
            catalog.mainnetReachable,
            catalog.implementsVaultCryptoProvider,
            catalog.containsVaultCryptoProvider,
            catalog.canExecuteProviderOperations,
            catalog.canExecuteCrypto,
            catalog.canUseForVaultLifecycle,
            catalog.canUseForPersistence,
            catalog.canUseForSync,
            catalog.canUseForSigning,
            catalog.canUseForBroadcasting,
            catalog.canUseForMainnet,
            catalog.productionProviderSelectable,
            row.runtimeSelectable,
            row.registrySelectable,
            row.factoryReachable,
            row.dispatcherReachable,
            row.executorTargetable,
            row.providerKatExecutorReachable,
            row.providerOperationReachable,
            row.cryptoExecutionReachable,
            row.vaultLifecycleReachable,
            row.persistenceReachable,
            row.productionSyncReachable,
            row.backendClientReachable,
            row.bdkWalletStateReachable,
            row.settingsCodecReachable,
            row.uiSurfaceReachable,
            row.signingBroadcastingReachable,
            row.publicEndpointReachable,
            row.mainnetReachable,
            row.implementsVaultCryptoProvider,
            row.containsVaultCryptoProvider,
            row.canExecuteProviderOperations,
            row.canExecuteCrypto,
            row.canUseForVaultLifecycle,
            row.canUseForPersistence,
            row.canUseForSync,
            row.canUseForSigning,
            row.canUseForBroadcasting,
            row.canUseForMainnet,
            row.productionProviderSelectable,
        )

        assertTrue(blockedBooleans.none { enabled -> enabled })
    }

    @Test
    fun katFixtureCatalogIsNotRegistryFactoryDispatcherExecutorOrKatExecutorReachable() {
        val catalog = catalog()

        assertFalse(catalog.registrySelectable)
        assertFalse(catalog.factoryReachable)
        assertFalse(catalog.dispatcherReachable)
        assertFalse(catalog.executorTargetable)
        assertFalse(catalog.providerKatExecutorReachable)
    }

    @Test
    fun katFixtureCatalogCannotExecuteProviderOperationsOrCrypto() {
        val catalog = catalog()

        assertFalse(catalog.canExecuteProviderOperations)
        assertFalse(catalog.canExecuteCrypto)
    }

    @Test
    fun katFixtureCatalogCannotParticipateInVaultLifecyclePersistenceOrProductionSync() {
        val catalog = catalog()

        assertFalse(catalog.canUseForVaultLifecycle)
        assertFalse(catalog.canUseForPersistence)
        assertFalse(catalog.canUseForSync)
    }

    @Test
    fun katFixtureCatalogCannotSignBroadcastOrEnableMainnet() {
        val catalog = catalog()

        assertFalse(catalog.canUseForSigning)
        assertFalse(catalog.canUseForBroadcasting)
        assertFalse(catalog.canUseForMainnet)
    }

    @Test
    fun katFixtureCatalogDoesNotExposeRawKatVectorsRuntimeReferencesRawSafeIdsOrFixtureIdsInToString() {
        val catalog = catalog()
        val row = catalog.fixtureRows.single()
        val outputs = listOf(
            catalog.toString(),
            catalog.displayLabel.toString(),
            row.toString(),
            row.displayLabel.toString(),
            row.fixtureId.toString(),
            row.markerSafeId.toString(),
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
    fun katFixtureCatalogClassSafeIdAndFixtureIdAreCommonTestOnlyBySourceGuardContract() {
        val catalog = catalog()

        assertTrue(catalog.catalogIsCommonTestOnly)
        assertTrue(catalog.catalogContainsExactlyOneFixture)
        assertTrue(catalog.fixtureMetadataOnly)
        assertFalse(catalog.catalogIsKatExecutionAuthorization)
        assertEquals(
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            catalog.fixtureRows.single().markerSafeId.value,
        )
        assertEquals(
            "skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata",
            catalog.fixtureRows.single().fixtureId.value,
        )
    }

    private fun catalog() =
        SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalogPolicy.currentKatFixtureCatalog()
}
