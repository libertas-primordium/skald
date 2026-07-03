package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityMarkerFamily
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityMarkerNamespace
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityMarkerPurpose
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentitySourceSetCategory
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentitySourceSetRole
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderSyntheticIdentityFamily
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespacePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceRequest
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderSyntheticIdentitySafeId
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityMarkerTest {
    @Test
    fun markerExistsOnlyAsCommonTestTestOnlyInertIdentityMarker() {
        val marker = marker()

        assertTrue(marker.implementedNow)
        assertEquals(SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet.CommonTest, marker.sourceSet)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityMarkerNamespace.TestOnlyProviderIdentityV1,
            marker.namespace,
        )
        assertFalse(marker.runtimeSelectable)
        assertFalse(marker.productionProviderSelectable)
    }

    @Test
    fun markerSafeIdUsesExactApprovedPrefix() {
        val marker = marker()

        assertTrue(
            marker.safeId.value.startsWith(
                SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespacePolicy.ALLOWED_NAMESPACE_PREFIX,
            ),
        )
        assertEquals(
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            marker.safeId.value,
        )
    }

    @Test
    fun markerSafeIdUsesDeterministicKatFamilyToken() {
        val marker = marker()
        val evidence = namespaceEvidence(marker.safeId.value)

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityMarkerFamily.DeterministicKat, marker.family)
        assertEquals(SkaldVaultV1TestOnlyProviderSyntheticIdentityFamily.DeterministicKat, evidence.candidateFamily)
        assertTrue(marker.safeId.value.contains("-deterministic-kat-"))
        assertFalse(evidence.candidateLabelRejected)
    }

    @Test
    fun markerSafeIdUsesInertMarkerPurposeToken() {
        val marker = marker()
        val purposeTokens = SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespacePolicy.purposeTokens(
            SkaldVaultV1TestOnlyProviderSyntheticIdentitySafeId(marker.safeId.value),
        )

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityMarkerPurpose.InertMarker, marker.purpose)
        assertEquals(listOf("inert", "marker"), purposeTokens)
    }

    @Test
    fun markerToStringRedactsTheSafeId() {
        val marker = marker()

        assertFalse(marker.toString().contains(marker.safeId.value))
        assertFalse(marker.safeId.toString().contains(marker.safeId.value))
        assertTrue(marker.toString().contains("redacted", ignoreCase = true))
        assertTrue(marker.safeId.toString().contains("redacted", ignoreCase = true))
    }

    @Test
    fun markerDoesNotImplementVaultCryptoProvider() {
        val marker = marker()

        assertFalse(marker.implementsVaultCryptoProvider)
    }

    @Test
    fun markerDoesNotExposeVaultCryptoProviderInstance() {
        val marker = marker()

        assertFalse(marker.vaultCryptoProviderInstanceExposed)
    }

    @Test
    fun markerIsNotSelectedByVaultCryptoProviderSelectionRegistry() {
        val marker = marker()
        val result = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, result.selectedCandidateId)
        assertIs<DisabledVaultCryptoProvider>(result.selectedProvider)
        assertFalse(result.safeDetail.contains(marker.safeId.value))
        assertFalse(result.candidates.any { candidate ->
            candidate.safeDetail.contains(marker.safeId.value)
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
        assertFalse(marker().productionProviderSelectable)
        assertFalse(VaultCryptoProviderSelectionRegistry.select().productionProviderSelectable)
    }

    @Test
    fun markerIsNotRegistryFactoryDispatcherExecutorOrKatExecutorReachable() {
        val marker = marker()

        assertFalse(marker.registrySelectable)
        assertFalse(marker.factoryReachable)
        assertFalse(marker.dispatcherReachable)
        assertFalse(marker.executorTargetable)
        assertFalse(marker.providerKatExecutorReachable)
    }

    @Test
    fun markerCannotExecuteProviderOperationsOrCrypto() {
        val marker = marker()

        assertFalse(marker.canExecuteProviderOperations)
        assertFalse(marker.canExecuteCrypto)
    }

    @Test
    fun markerCannotParticipateInVaultLifecyclePersistenceOrProductionSync() {
        val marker = marker()

        assertFalse(marker.canUseForVaultLifecycle)
        assertFalse(marker.canUseForPersistence)
        assertFalse(marker.canUseForSync)
    }

    @Test
    fun markerCannotSignBroadcastOrEnableMainnet() {
        val marker = marker()

        assertFalse(marker.canUseForSigning)
        assertFalse(marker.canUseForBroadcasting)
        assertFalse(marker.canUseForMainnet)
    }

    @Test
    fun markerDoesNotExposeRawMaterialOrRuntimeReferences() {
        val marker = marker()
        val safeOutputs = listOf(marker.toString(), marker.safeId.toString(), marker.displayLabel.toString())
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

        safeOutputs.forEach { output ->
            assertFalse(output.contains(marker.safeId.value))
            rejectedTerms.forEach { term ->
                assertFalse(output.contains(term, ignoreCase = true), "safe output leaked $term")
            }
        }
    }

    @Test
    fun markerSourceSetEvidenceIsCommonTestOnlyAndRuntimeSourceSetsRemainForbidden() {
        val evidence =
            SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementPolicy.currentSourceSetConfinementEvidence()
        val commonTest = evidence.sourceSetCategories.single {
            it.category == SkaldVaultV1TestOnlyProviderIdentitySourceSetCategory.CommonTestSource
        }
        val androidMain = evidence.sourceSetCategories.single {
            it.category == SkaldVaultV1TestOnlyProviderIdentitySourceSetCategory.AndroidMainProductionSource
        }
        val desktopMain = evidence.sourceSetCategories.single {
            it.category == SkaldVaultV1TestOnlyProviderIdentitySourceSetCategory.DesktopMainProductionSource
        }

        assertEquals(SkaldVaultV1TestOnlyProviderIdentitySourceSetRole.TestOnlySource, commonTest.role)
        assertTrue(commonTest.futureReviewOnly)
        assertFalse(commonTest.authorizesImplementation)
        assertTrue(androidMain.productionRuntimeForbidden)
        assertTrue(desktopMain.productionRuntimeForbidden)
        assertFalse(androidMain.authorizesImplementation)
        assertFalse(desktopMain.authorizesImplementation)
    }

    private fun marker() = SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy.currentMarker()

    private fun namespaceEvidence(candidate: String) =
        SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespacePolicy.evaluateNamespace(
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceRequest(
                candidateSafeId = SkaldVaultV1TestOnlyProviderSyntheticIdentitySafeId(candidate),
            ),
        )
}
