package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityInventory
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProfilePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProfileValidationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityReachabilityProofPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityReachabilityProofSourceSet
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityReachabilitySurface
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityReachabilityProofTest {
    @Test
    fun reachabilityProofExistsOnlyAsCommonTestInertNegativeReachabilityEvidence() {
        val proof = proof()

        assertTrue(proof.proofIsCommonTestOnly)
        assertEquals(SkaldVaultV1TestOnlyProviderIdentityReachabilityProofSourceSet.CommonTest, proof.sourceSet)
        assertFalse(proof.runtimeSelectable)
        assertFalse(proof.productionProviderSelectable)
    }

    @Test
    fun reachabilityProofReadsExactlyOneMarkerOneInventoryOneProfileAndOneValidationReport() {
        val proof = proof()
        val inventory = SkaldVaultV1TestOnlyProviderIdentityInventory
        val profile = SkaldVaultV1TestOnlyProviderIdentityProfilePolicy.currentProfile()
        val validationReport =
            SkaldVaultV1TestOnlyProviderIdentityProfileValidationPolicy.currentValidationReport()

        assertEquals(1, proof.markerCount)
        assertEquals(1, proof.inventoryCount)
        assertEquals(1, proof.profileCount)
        assertEquals(1, proof.validationReportCount)
        assertEquals(inventory.markers.single().safeId.value, profile.marker.safeId.value)
        assertTrue(validationReport.allChecksPassed)
    }

    @Test
    fun reachabilityProofConfirmsMarkerSafeIdIsExpected() {
        val proof = proof()

        assertTrue(proof.expectedSafeIdMatched)
        assertEquals(
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            SkaldVaultV1TestOnlyProviderIdentityProfilePolicy.currentProfile().marker.safeId.value,
        )
    }

    @Test
    fun reachabilityProofIsNotProductionAuthorization() {
        assertFalse(proof().proofIsProductionAuthorization)
    }

    @Test
    fun reachabilityProofIsNotProviderSelectionAuthorization() {
        assertFalse(proof().proofIsProviderSelectionAuthorization)
    }

    @Test
    fun reachabilityProofIsNotRegistryAuthorization() {
        assertFalse(proof().proofIsRegistryAuthorization)
    }

    @Test
    fun reachabilityProofIsNotFactoryAuthorization() {
        assertFalse(proof().proofIsFactoryAuthorization)
    }

    @Test
    fun reachabilityProofIsNotDispatcherAuthorization() {
        assertFalse(proof().proofIsDispatcherAuthorization)
    }

    @Test
    fun reachabilityProofIsNotExecutorAuthorization() {
        assertFalse(proof().proofIsExecutorAuthorization)
    }

    @Test
    fun reachabilityProofIsNotCryptoAuthorization() {
        assertFalse(proof().proofIsCryptoAuthorization)
    }

    @Test
    fun reachabilityProofIsNotVaultPersistenceAuthorization() {
        assertFalse(proof().proofIsVaultPersistenceAuthorization)
    }

    @Test
    fun reachabilityProofIsNotMainnetAuthorization() {
        assertFalse(proof().proofIsMainnetAuthorization)
    }

    @Test
    fun reachabilityProofDoesNotImplementVaultCryptoProvider() {
        assertFalse(proof().implementsVaultCryptoProvider)
    }

    @Test
    fun reachabilityProofDoesNotExposeVaultCryptoProviderInstance() {
        assertFalse(proof().containsVaultCryptoProvider)
    }

    @Test
    fun reachabilityProofIsNotSelectedByVaultCryptoProviderSelectionRegistry() {
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
        val proof = proof()

        assertFalse(proof.productionProviderSelectable)
        assertFalse(VaultCryptoProviderSelectionRegistry.select().productionProviderSelectable)
    }

    @Test
    fun allReachabilitySurfaceRowsAreRepresentedExactlyOnceAndRemainBlocked() {
        val proof = proof()

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityReachabilitySurface.entries.toSet(),
            proof.reachabilitySurfaceRows.map { row -> row.surface }.toSet(),
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityReachabilitySurface.entries.size,
            proof.reachabilitySurfaceRows.size,
        )
        assertTrue(proof.reachabilitySurfaceRows.all { row -> !row.reachable && !row.authorizesRuntimeUse })
    }

    @Test
    fun allRequiredReachabilitySurfaceBooleansRemainFalse() {
        val proof = proof()
        val reachabilityBooleans = listOf(
            proof.providerSelectionReachable,
            proof.providerRegistryReachable,
            proof.providerFactoryReachable,
            proof.providerDispatcherReachable,
            proof.executorTargetReachable,
            proof.providerKatExecutorReachable,
            proof.providerOperationReachable,
            proof.cryptoExecutionReachable,
            proof.vaultLifecycleReachable,
            proof.persistenceReachable,
            proof.productionSyncReachable,
            proof.backendClientReachable,
            proof.bdkWalletStateReachable,
            proof.settingsCodecReachable,
            proof.uiSurfaceReachable,
            proof.signingBroadcastingReachable,
            proof.publicEndpointReachable,
            proof.mainnetReachable,
        )

        assertTrue(reachabilityBooleans.none { reachable -> reachable })
    }

    @Test
    fun reachabilityProofIsNotRegistryFactoryDispatcherExecutorOrKatExecutorTargetable() {
        val proof = proof()

        assertFalse(proof.registrySelectable)
        assertFalse(proof.factoryReachable)
        assertFalse(proof.dispatcherReachable)
        assertFalse(proof.executorTargetable)
        assertFalse(proof.providerKatExecutorTargetable)
    }

    @Test
    fun reachabilityProofCannotExecuteProviderOperationsOrCrypto() {
        val proof = proof()

        assertFalse(proof.canExecuteProviderOperations)
        assertFalse(proof.canExecuteCrypto)
    }

    @Test
    fun reachabilityProofCannotParticipateInVaultLifecyclePersistenceOrProductionSync() {
        val proof = proof()

        assertFalse(proof.canUseForVaultLifecycle)
        assertFalse(proof.canUseForPersistence)
        assertFalse(proof.canUseForSync)
    }

    @Test
    fun reachabilityProofCannotSignBroadcastOrEnableMainnet() {
        val proof = proof()

        assertFalse(proof.canUseForSigning)
        assertFalse(proof.canUseForBroadcasting)
        assertFalse(proof.canUseForMainnet)
    }

    @Test
    fun reachabilityProofDoesNotExposeRawMaterialRuntimeReferencesOrRawSafeIdsInToString() {
        val proof = proof()
        val output = proof.toString()
        val labelOutput = proof.displayLabel.toString()
        val safeId = "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"
        val rejectedTerms = listOf(
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
    fun reachabilityProofClassAndSafeIdAreCommonTestOnlyBySourceGuardContract() {
        val proof = proof()

        assertTrue(proof.proofIsCommonTestOnly)
        assertTrue(
            SkaldVaultV1TestOnlyProviderIdentityProfileValidationPolicy.currentValidationReport()
                .productionRuntimeSourceAbsenceSatisfied,
        )
        assertEquals(
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            SkaldVaultV1TestOnlyProviderIdentityProfilePolicy.currentProfile().marker.safeId.value,
        )
    }

    private fun proof() =
        SkaldVaultV1TestOnlyProviderIdentityReachabilityProofPolicy.currentReachabilityProof()
}
