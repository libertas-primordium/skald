package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityInventory
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProfileKind
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProfilePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProfilePurpose
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProfileSourceSet
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertSame
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityProfileTest {
    @Test
    fun profileExistsOnlyAsCommonTestInertIdentityProfile() {
        val profile = profile()

        assertTrue(profile.implementedNow)
        assertTrue(profile.profileIsCommonTestOnly)
        assertEquals(SkaldVaultV1TestOnlyProviderIdentityProfileSourceSet.CommonTest, profile.sourceSet)
        assertEquals(SkaldVaultV1TestOnlyProviderIdentityProfileKind.InertTestOnlyIdentityProfile, profile.profileKind)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityProfilePurpose.FutureValidationMetadataOnly,
            profile.profilePurpose,
        )
        assertFalse(profile.runtimeSelectable)
        assertFalse(profile.productionProviderSelectable)
    }

    @Test
    fun profileIsBuiltFromTheInertInventory() {
        val profile = profile()
        val inventory = SkaldVaultV1TestOnlyProviderIdentityInventory

        assertEquals(inventory.markerCount, profile.inventorySize)
        assertSame(inventory.markers.single(), profile.marker)
    }

    @Test
    fun profileReferencesExactlyOneInertMarker() {
        val profile = profile()

        assertEquals(1, profile.inventorySize)
        assertTrue(profile.marker.implementedNow)
        assertFalse(profile.marker.runtimeSelectable)
        assertFalse(profile.marker.registrySelectable)
        assertFalse(profile.marker.factoryReachable)
        assertFalse(profile.marker.dispatcherReachable)
        assertFalse(profile.marker.executorTargetable)
        assertFalse(profile.marker.providerKatExecutorReachable)
    }

    @Test
    fun profileMarkerSafeIdIsTheExistingInertMarkerSafeId() {
        assertEquals(
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            profile().marker.safeId.value,
        )
    }

    @Test
    fun profileToStringRedactsMarkerIds() {
        val profile = profile()
        val safeId = profile.marker.safeId.value

        assertFalse(profile.toString().contains(safeId))
        assertFalse(profile.displayLabel.toString().contains(safeId))
        assertTrue(profile.toString().contains("redacted", ignoreCase = true))
    }

    @Test
    fun profileIsNotAProductionProviderProfile() {
        assertFalse(profile().profileIsProductionProviderProfile)
    }

    @Test
    fun profileIsNotAProductionProviderRegistry() {
        assertFalse(profile().profileIsProviderRegistry)
    }

    @Test
    fun profileIsNotAProviderFactoryInput() {
        assertFalse(profile().profileIsProviderFactoryInput)
    }

    @Test
    fun profileIsNotAProviderDispatcherInput() {
        assertFalse(profile().profileIsProviderDispatcherInput)
    }

    @Test
    fun profileIsNotAnExecutorTargetInput() {
        assertFalse(profile().profileIsExecutorTargetInput)
    }

    @Test
    fun profileDoesNotImplementVaultCryptoProvider() {
        assertFalse(profile().implementsVaultCryptoProvider)
    }

    @Test
    fun profileDoesNotExposeVaultCryptoProviderInstance() {
        assertFalse(profile().containsVaultCryptoProvider)
    }

    @Test
    fun profileIsNotSelectedByVaultCryptoProviderSelectionRegistry() {
        val profile = profile()
        val result = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, result.selectedCandidateId)
        assertIs<DisabledVaultCryptoProvider>(result.selectedProvider)
        assertFalse(result.safeDetail.contains(profile.marker.safeId.value))
        assertFalse(result.candidates.any { candidate ->
            candidate.safeDetail.contains(profile.marker.safeId.value)
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
        assertFalse(profile().productionProviderSelectable)
        assertFalse(VaultCryptoProviderSelectionRegistry.select().productionProviderSelectable)
    }

    @Test
    fun profileIsNotRegistryFactoryDispatcherExecutorOrKatExecutorReachable() {
        val profile = profile()

        assertFalse(profile.registrySelectable)
        assertFalse(profile.factoryReachable)
        assertFalse(profile.dispatcherReachable)
        assertFalse(profile.executorTargetable)
        assertFalse(profile.providerKatExecutorReachable)
    }

    @Test
    fun profileCannotExecuteProviderOperationsOrCrypto() {
        val profile = profile()

        assertFalse(profile.canExecuteProviderOperations)
        assertFalse(profile.canExecuteCrypto)
    }

    @Test
    fun profileCannotParticipateInVaultLifecyclePersistenceOrProductionSync() {
        val profile = profile()

        assertFalse(profile.canUseForVaultLifecycle)
        assertFalse(profile.canUseForPersistence)
        assertFalse(profile.canUseForSync)
    }

    @Test
    fun profileCannotSignBroadcastOrEnableMainnet() {
        val profile = profile()

        assertFalse(profile.canUseForSigning)
        assertFalse(profile.canUseForBroadcasting)
        assertFalse(profile.canUseForMainnet)
    }

    @Test
    fun profileDoesNotExposeRawMaterialRuntimeReferencesOrRawSafeIdsInToString() {
        val profile = profile()
        val output = profile.toString()
        val safeId = profile.marker.safeId.value
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
    fun profileClassAndSafeIdAreCommonTestOnlyByConstruction() {
        val profile = profile()

        assertTrue(profile.profileIsCommonTestOnly)
        assertFalse(profile.profileIsProductionProviderProfile)
        assertFalse(profile.profileIsProviderRegistry)
        assertEquals(
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            profile.marker.safeId.value,
        )
    }

    private fun profile() = SkaldVaultV1TestOnlyProviderIdentityProfilePolicy.currentProfile()
}
