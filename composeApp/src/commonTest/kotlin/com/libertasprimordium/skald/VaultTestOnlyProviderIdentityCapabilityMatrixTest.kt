package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityCapabilityCategory
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityCapabilityMatrixPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityCapabilityMatrixSourceSet
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProfilePolicy
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityCapabilityMatrixTest {
    @Test
    fun capabilityMatrixExistsOnlyAsCommonTestInertNegativeCapabilityEvidence() {
        val matrix = matrix()

        assertTrue(matrix.matrixIsCommonTestOnly)
        assertEquals(SkaldVaultV1TestOnlyProviderIdentityCapabilityMatrixSourceSet.CommonTest, matrix.sourceSet)
        assertFalse(matrix.runtimeSelectable)
        assertFalse(matrix.productionProviderSelectable)
    }

    @Test
    fun capabilityMatrixReadsExactlyOneMarkerInventoryProfileValidationReportAndReachabilityProof() {
        val matrix = matrix()

        assertEquals(1, matrix.markerCount)
        assertEquals(1, matrix.inventoryCount)
        assertEquals(1, matrix.profileCount)
        assertEquals(1, matrix.validationReportCount)
        assertEquals(1, matrix.reachabilityProofCount)
    }

    @Test
    fun capabilityMatrixConfirmsMarkerSafeIdIsExpected() {
        val matrix = matrix()

        assertTrue(matrix.expectedSafeIdMatched)
        assertEquals(
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            SkaldVaultV1TestOnlyProviderIdentityProfilePolicy.currentProfile().marker.safeId.value,
        )
    }

    @Test
    fun capabilityMatrixEveryCapabilityBlockedIsOnlyNonAuthorizingCommonTestEvidence() {
        val matrix = matrix()

        assertTrue(matrix.everyCapabilityBlocked)
        assertTrue(matrix.matrixIsCommonTestOnly)
        assertFalse(matrix.matrixIsProductionAuthorization)
        assertFalse(matrix.matrixIsProviderSelectionAuthorization)
        assertFalse(matrix.canExecuteCrypto)
        assertFalse(matrix.canUseForPersistence)
        assertFalse(matrix.canUseForMainnet)
    }

    @Test
    fun capabilityMatrixIsNotProductionAuthorization() {
        assertFalse(matrix().matrixIsProductionAuthorization)
    }

    @Test
    fun capabilityMatrixIsNotProviderSelectionAuthorization() {
        assertFalse(matrix().matrixIsProviderSelectionAuthorization)
    }

    @Test
    fun capabilityMatrixIsNotRegistryAuthorization() {
        assertFalse(matrix().matrixIsRegistryAuthorization)
    }

    @Test
    fun capabilityMatrixIsNotFactoryAuthorization() {
        assertFalse(matrix().matrixIsFactoryAuthorization)
    }

    @Test
    fun capabilityMatrixIsNotDispatcherAuthorization() {
        assertFalse(matrix().matrixIsDispatcherAuthorization)
    }

    @Test
    fun capabilityMatrixIsNotExecutorAuthorization() {
        assertFalse(matrix().matrixIsExecutorAuthorization)
    }

    @Test
    fun capabilityMatrixIsNotCryptoAuthorization() {
        assertFalse(matrix().matrixIsCryptoAuthorization)
    }

    @Test
    fun capabilityMatrixIsNotVaultPersistenceAuthorization() {
        assertFalse(matrix().matrixIsVaultPersistenceAuthorization)
    }

    @Test
    fun capabilityMatrixIsNotMainnetAuthorization() {
        assertFalse(matrix().matrixIsMainnetAuthorization)
    }

    @Test
    fun capabilityMatrixDoesNotImplementVaultCryptoProvider() {
        assertFalse(matrix().implementsVaultCryptoProvider)
    }

    @Test
    fun capabilityMatrixDoesNotExposeVaultCryptoProviderInstance() {
        assertFalse(matrix().containsVaultCryptoProvider)
    }

    @Test
    fun capabilityMatrixIsNotSelectedByVaultCryptoProviderSelectionRegistry() {
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
        val matrix = matrix()

        assertFalse(matrix.productionProviderSelectable)
        assertFalse(VaultCryptoProviderSelectionRegistry.select().productionProviderSelectable)
    }

    @Test
    fun allRequiredCapabilityCategoriesAreRepresentedExactlyOnce() {
        val matrix = matrix()

        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityCapabilityCategory.entries.toSet(),
            matrix.capabilityRows.map { row -> row.category }.toSet(),
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityCapabilityCategory.entries.size,
            matrix.capabilityRows.size,
        )
    }

    @Test
    fun everyCapabilityAndReachabilityBooleanRemainsFalse() {
        val matrix = matrix()
        val blockedBooleans = listOf(
            matrix.runtimeSelectable,
            matrix.registrySelectable,
            matrix.factoryReachable,
            matrix.dispatcherReachable,
            matrix.executorTargetable,
            matrix.providerKatExecutorReachable,
            matrix.providerOperationReachable,
            matrix.cryptoExecutionReachable,
            matrix.vaultLifecycleReachable,
            matrix.persistenceReachable,
            matrix.productionSyncReachable,
            matrix.backendClientReachable,
            matrix.bdkWalletStateReachable,
            matrix.settingsCodecReachable,
            matrix.uiSurfaceReachable,
            matrix.signingBroadcastingReachable,
            matrix.publicEndpointReachable,
            matrix.mainnetReachable,
            matrix.implementsVaultCryptoProvider,
            matrix.containsVaultCryptoProvider,
            matrix.canExecuteProviderOperations,
            matrix.canExecuteCrypto,
            matrix.canUseForVaultLifecycle,
            matrix.canUseForPersistence,
            matrix.canUseForSync,
            matrix.canUseForSigning,
            matrix.canUseForBroadcasting,
            matrix.canUseForMainnet,
            matrix.productionProviderSelectable,
        )

        assertTrue(blockedBooleans.none { enabled -> enabled })
        assertTrue(
            matrix.capabilityRows.all { row ->
                !row.capabilityPresent && !row.reachable && !row.authorizesRuntimeUse
            },
        )
    }

    @Test
    fun capabilityMatrixIsNotRegistryFactoryDispatcherExecutorOrKatExecutorReachable() {
        val matrix = matrix()

        assertFalse(matrix.registrySelectable)
        assertFalse(matrix.factoryReachable)
        assertFalse(matrix.dispatcherReachable)
        assertFalse(matrix.executorTargetable)
        assertFalse(matrix.providerKatExecutorReachable)
    }

    @Test
    fun capabilityMatrixCannotExecuteProviderOperationsOrCrypto() {
        val matrix = matrix()

        assertFalse(matrix.canExecuteProviderOperations)
        assertFalse(matrix.canExecuteCrypto)
    }

    @Test
    fun capabilityMatrixCannotParticipateInVaultLifecyclePersistenceOrProductionSync() {
        val matrix = matrix()

        assertFalse(matrix.canUseForVaultLifecycle)
        assertFalse(matrix.canUseForPersistence)
        assertFalse(matrix.canUseForSync)
    }

    @Test
    fun capabilityMatrixCannotSignBroadcastOrEnableMainnet() {
        val matrix = matrix()

        assertFalse(matrix.canUseForSigning)
        assertFalse(matrix.canUseForBroadcasting)
        assertFalse(matrix.canUseForMainnet)
    }

    @Test
    fun capabilityMatrixDoesNotExposeRawMaterialRuntimeReferencesOrRawSafeIdsInToString() {
        val matrix = matrix()
        val output = matrix.toString()
        val labelOutput = matrix.displayLabel.toString()
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
    fun capabilityMatrixClassAndSafeIdAreCommonTestOnlyBySourceGuardContract() {
        val matrix = matrix()

        assertTrue(matrix.matrixIsCommonTestOnly)
        assertTrue(matrix.everyCapabilityBlocked)
        assertEquals(
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            SkaldVaultV1TestOnlyProviderIdentityProfilePolicy.currentProfile().marker.safeId.value,
        )
    }

    private fun matrix() =
        SkaldVaultV1TestOnlyProviderIdentityCapabilityMatrixPolicy.currentCapabilityMatrix()
}
