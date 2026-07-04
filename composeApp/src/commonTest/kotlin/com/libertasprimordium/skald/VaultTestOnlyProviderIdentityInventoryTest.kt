package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityInventory
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityMarkerSafeId
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertSame
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityInventoryTest {
    @Test
    fun inventoryExistsOnlyAsCommonTestTestOnlyInertIdentityInventory() {
        val inventory = inventory()

        assertTrue(inventory.implementedNow)
        assertTrue(inventory.inventoryIsCommonTestOnly)
        assertTrue(inventory.inventoryContainsOnlyInertMarkers)
        assertFalse(inventory.runtimeSelectable)
        assertFalse(inventory.productionProviderSelectable)
    }

    @Test
    fun inventoryContainsExactlyOneMarker() {
        val inventory = inventory()

        assertEquals(1, inventory.markerCount)
        assertEquals(1, inventory.markers.size)
    }

    @Test
    fun inventoryContainsOnlyTheExistingInertMarker() {
        val inventory = inventory()
        val marker = SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy.currentMarker()
        val inventoryMarker = inventory.markers.single()

        assertEquals(marker.safeId.value, inventoryMarker.safeId.value)
        assertTrue(inventoryMarker.implementedNow)
        assertFalse(inventoryMarker.runtimeSelectable)
        assertFalse(inventoryMarker.registrySelectable)
        assertFalse(inventoryMarker.factoryReachable)
        assertFalse(inventoryMarker.dispatcherReachable)
        assertFalse(inventoryMarker.executorTargetable)
        assertFalse(inventoryMarker.providerKatExecutorReachable)
    }

    @Test
    fun inventorySafeIdIsTheExistingInertMarkerSafeId() {
        assertEquals(
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            inventory().markers.single().safeId.value,
        )
    }

    @Test
    fun inventoryLookupReturnsOnlyTheExistingInertMarker() {
        val inventory = inventory()
        val marker = inventory.markers.single()

        assertSame(marker, inventory.markerForSafeId(SkaldVaultV1TestOnlyProviderIdentityMarkerSafeId(marker.safeId.value)))
        assertEquals(
            null,
            inventory.markerForSafeId(
                SkaldVaultV1TestOnlyProviderIdentityMarkerSafeId(
                    "skald-test-only-provider-identity-v1-deterministic-kat-absent-marker",
                ),
            ),
        )
    }

    @Test
    fun inventoryToStringRedactsMarkerIds() {
        val inventory = inventory()
        val safeId = inventory.markers.single().safeId.value

        assertFalse(inventory.toString().contains(safeId))
        assertTrue(inventory.toString().contains("redacted", ignoreCase = true))
    }

    @Test
    fun inventoryIsNotAProductionProviderRegistry() {
        assertFalse(inventory().inventoryIsProductionRegistry)
    }

    @Test
    fun inventoryDoesNotImplementVaultCryptoProvider() {
        assertFalse(inventory().implementsVaultCryptoProvider)
    }

    @Test
    fun inventoryDoesNotExposeVaultCryptoProviderInstance() {
        assertFalse(inventory().containsVaultCryptoProvider)
    }

    @Test
    fun inventoryIsNotSelectedByVaultCryptoProviderSelectionRegistry() {
        val inventory = inventory()
        val markerSafeId = inventory.markers.single().safeId.value
        val result = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, result.selectedCandidateId)
        assertIs<DisabledVaultCryptoProvider>(result.selectedProvider)
        assertFalse(result.safeDetail.contains(markerSafeId))
        assertFalse(result.candidates.any { candidate ->
            candidate.safeDetail.contains(markerSafeId)
        })
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
        assertFalse(inventory().productionProviderSelectable)
        assertFalse(VaultCryptoProviderSelectionRegistry.select().productionProviderSelectable)
    }

    @Test
    fun inventoryIsNotRegistryFactoryDispatcherExecutorOrKatExecutorReachable() {
        val inventory = inventory()

        assertFalse(inventory.registrySelectable)
        assertFalse(inventory.factoryReachable)
        assertFalse(inventory.dispatcherReachable)
        assertFalse(inventory.executorTargetable)
        assertFalse(inventory.providerKatExecutorReachable)
    }

    @Test
    fun inventoryCannotExecuteProviderOperationsOrCrypto() {
        val inventory = inventory()

        assertFalse(inventory.canExecuteProviderOperations)
        assertFalse(inventory.canExecuteCrypto)
    }

    @Test
    fun inventoryCannotParticipateInVaultLifecyclePersistenceOrProductionSync() {
        val inventory = inventory()

        assertFalse(inventory.canUseForVaultLifecycle)
        assertFalse(inventory.canUseForPersistence)
        assertFalse(inventory.canUseForSync)
    }

    @Test
    fun inventoryCannotSignBroadcastOrEnableMainnet() {
        val inventory = inventory()

        assertFalse(inventory.canUseForSigning)
        assertFalse(inventory.canUseForBroadcasting)
        assertFalse(inventory.canUseForMainnet)
    }

    @Test
    fun inventoryDoesNotExposeRawMaterialRuntimeReferencesOrRawSafeIdsInToString() {
        val inventory = inventory()
        val output = inventory.toString()
        val safeId = inventory.markers.single().safeId.value
        val rejectedTerms = listOf(
            "raw material",
            "provider handle",
            "crypto object",
            "storage reference",
            "backend reference",
            "endpoint reference",
            "wallet reference",
            "source location",
            "implementation payload",
            "diagnostic payload",
            "descriptor",
        )

        assertFalse(output.contains(safeId))
        rejectedTerms.forEach { term ->
            assertFalse(output.contains(term, ignoreCase = true), "safe output leaked $term")
        }
    }

    @Test
    fun inventoryClassAndSafeIdAreCommonTestOnlyByConstruction() {
        val inventory = inventory()

        assertTrue(inventory.inventoryIsCommonTestOnly)
        assertTrue(inventory.inventoryContainsOnlyInertMarkers)
        assertFalse(inventory.inventoryIsProductionRegistry)
        assertEquals(
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            inventory.markers.single().safeId.value,
        )
    }

    private fun inventory() = SkaldVaultV1TestOnlyProviderIdentityInventory
}
