package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityInventory
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProfilePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProfileValidationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityProfileValidationSourceSet
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityProfileValidationTest {
    @Test
    fun validationReportExistsOnlyAsCommonTestInertValidationEvidence() {
        val report = report()

        assertTrue(report.validationIsCommonTestOnly)
        assertEquals(SkaldVaultV1TestOnlyProviderIdentityProfileValidationSourceSet.CommonTest, report.sourceSet)
        assertTrue(report.allChecksPassed)
        assertFalse(report.runtimeSelectable)
        assertFalse(report.productionProviderSelectable)
    }

    @Test
    fun validationReportValidatesExactlyOneMarkerOneInventoryAndOneProfile() {
        val report = report()

        assertEquals(1, report.markerCount)
        assertEquals(1, report.inventoryCount)
        assertEquals(1, report.profileCount)
        assertTrue(report.markerPresentExactlyOnce)
        assertTrue(report.inventoryPresentExactlyOnce)
        assertTrue(report.profilePresentExactlyOnce)
    }

    @Test
    fun validationReportConfirmsProfileIsBuiltFromTheInertInventory() {
        val report = report()
        val inventory = SkaldVaultV1TestOnlyProviderIdentityInventory
        val profile = SkaldVaultV1TestOnlyProviderIdentityProfilePolicy.currentProfile()

        assertTrue(report.profileBuiltFromInventory)
        assertEquals(inventory.markers.single().safeId.value, profile.marker.safeId.value)
        assertEquals(inventory.markerCount, profile.inventorySize)
    }

    @Test
    fun validationReportConfirmsMarkerSafeIdIsExpected() {
        val report = report()

        assertTrue(report.expectedSafeIdMatched)
        assertTrue(report.markerSafeIdMatchesExpected)
        assertEquals(
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            SkaldVaultV1TestOnlyProviderIdentityProfilePolicy.currentProfile().marker.safeId.value,
        )
    }

    @Test
    fun validationReportAllChecksPassedIsOnlyNonAuthorizingCommonTestEvidence() {
        val report = report()

        assertTrue(report.allChecksPassed)
        assertTrue(report.validationIsCommonTestOnly)
        assertFalse(report.validationIsProductionAuthorization)
        assertFalse(report.validationIsProviderSelectionAuthorization)
        assertFalse(report.validationIsRegistryAuthorization)
        assertFalse(report.validationIsFactoryAuthorization)
        assertFalse(report.validationIsDispatcherAuthorization)
        assertFalse(report.validationIsExecutorAuthorization)
        assertFalse(report.validationIsCryptoAuthorization)
        assertFalse(report.validationIsVaultPersistenceAuthorization)
        assertFalse(report.validationIsMainnetAuthorization)
    }

    @Test
    fun validationReportIsNotProductionAuthorization() {
        assertFalse(report().validationIsProductionAuthorization)
    }

    @Test
    fun validationReportIsNotProviderSelectionAuthorization() {
        assertFalse(report().validationIsProviderSelectionAuthorization)
    }

    @Test
    fun validationReportIsNotRegistryAuthorization() {
        assertFalse(report().validationIsRegistryAuthorization)
    }

    @Test
    fun validationReportIsNotFactoryAuthorization() {
        assertFalse(report().validationIsFactoryAuthorization)
    }

    @Test
    fun validationReportIsNotDispatcherAuthorization() {
        assertFalse(report().validationIsDispatcherAuthorization)
    }

    @Test
    fun validationReportIsNotExecutorAuthorization() {
        assertFalse(report().validationIsExecutorAuthorization)
    }

    @Test
    fun validationReportIsNotCryptoAuthorization() {
        assertFalse(report().validationIsCryptoAuthorization)
    }

    @Test
    fun validationReportIsNotVaultPersistenceAuthorization() {
        assertFalse(report().validationIsVaultPersistenceAuthorization)
    }

    @Test
    fun validationReportIsNotMainnetAuthorization() {
        assertFalse(report().validationIsMainnetAuthorization)
    }

    @Test
    fun validationReportDoesNotImplementVaultCryptoProvider() {
        assertFalse(report().implementsVaultCryptoProvider)
    }

    @Test
    fun validationReportDoesNotExposeVaultCryptoProviderInstance() {
        assertFalse(report().containsVaultCryptoProvider)
    }

    @Test
    fun validationReportIsNotSelectedByVaultCryptoProviderSelectionRegistry() {
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
        val report = report()

        assertTrue(report.productionProviderSelectableRemainsFalse)
        assertFalse(report.productionProviderSelectable)
        assertFalse(VaultCryptoProviderSelectionRegistry.select().productionProviderSelectable)
    }

    @Test
    fun validationReportIsNotRegistryFactoryDispatcherExecutorOrKatExecutorReachable() {
        val report = report()

        assertFalse(report.registrySelectable)
        assertFalse(report.factoryReachable)
        assertFalse(report.dispatcherReachable)
        assertFalse(report.executorTargetable)
        assertFalse(report.providerKatExecutorReachable)
    }

    @Test
    fun validationReportCannotExecuteProviderOperationsOrCrypto() {
        val report = report()

        assertFalse(report.canExecuteProviderOperations)
        assertFalse(report.canExecuteCrypto)
    }

    @Test
    fun validationReportCannotParticipateInVaultLifecyclePersistenceOrProductionSync() {
        val report = report()

        assertFalse(report.canUseForVaultLifecycle)
        assertFalse(report.canUseForPersistence)
        assertFalse(report.canUseForSync)
    }

    @Test
    fun validationReportCannotSignBroadcastOrEnableMainnet() {
        val report = report()

        assertFalse(report.canUseForSigning)
        assertFalse(report.canUseForBroadcasting)
        assertFalse(report.canUseForMainnet)
    }

    @Test
    fun validationReportRecordsAllRequiredSafetyChecksAsSatisfiedByTestOnlyEvidence() {
        val report = report()

        assertTrue(report.markerSourceSetIsCommonTest)
        assertTrue(report.inventorySourceSetIsCommonTest)
        assertTrue(report.profileSourceSetIsCommonTest)
        assertTrue(report.markerInventoryAndProfileAreInert)
        assertTrue(report.markerInventoryAndProfileAreNonRuntime)
        assertTrue(report.markerInventoryAndProfileAreNonSelectable)
        assertTrue(report.markerInventoryAndProfileAreNotProductionRegistry)
        assertTrue(report.markerInventoryAndProfileAreNotFactoryInput)
        assertTrue(report.markerInventoryAndProfileAreNotDispatcherInput)
        assertTrue(report.markerInventoryAndProfileAreNotExecutorTarget)
        assertTrue(report.markerInventoryAndProfileDoNotImplementVaultCryptoProvider)
        assertTrue(report.markerInventoryAndProfileDoNotContainVaultCryptoProvider)
        assertTrue(report.markerInventoryAndProfileCannotExecuteProviderOperations)
        assertTrue(report.markerInventoryAndProfileCannotExecuteCrypto)
        assertTrue(report.markerInventoryAndProfileCannotUseVaultLifecycle)
        assertTrue(report.markerInventoryAndProfileCannotPersist)
        assertTrue(report.markerInventoryAndProfileCannotSync)
        assertTrue(report.markerInventoryAndProfileCannotSignOrBroadcast)
        assertTrue(report.markerInventoryAndProfileCannotUseMainnet)
        assertTrue(report.providerSelectionRemainsDisabledProviderOnly)
        assertTrue(report.productionRuntimeSourceAbsenceSatisfied)
    }

    @Test
    fun validationReportDoesNotExposeRawMaterialRuntimeReferencesOrRawSafeIdsInToString() {
        val report = report()
        val output = report.toString()
        val labelOutput = report.displayLabel.toString()
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
    fun validationClassAndSafeIdAreCommonTestOnlyBySourceGuardContract() {
        val report = report()

        assertTrue(report.validationIsCommonTestOnly)
        assertTrue(report.productionRuntimeSourceAbsenceSatisfied)
        assertEquals(
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            SkaldVaultV1TestOnlyProviderIdentityProfilePolicy.currentProfile().marker.safeId.value,
        )
    }

    private fun report() =
        SkaldVaultV1TestOnlyProviderIdentityProfileValidationPolicy.currentValidationReport()
}
