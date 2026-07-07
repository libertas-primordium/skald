package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityMarkerFamily
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityMarkerKind
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
        assertTrue(marker.markerCreated)
        assertTrue(marker.implementationMarkerPresent)
        assertTrue(marker.inertMarkerOnly)
        assertTrue(marker.commonTestOnly)
        assertEquals(SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet.CommonTest, marker.sourceSet)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityMarkerNamespace.TestOnlyProviderIdentityV1,
            marker.namespace,
        )
        assertEquals(1, marker.markerVersion)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityMarkerKind.InertTestOnlyProviderIdentityMarker,
            marker.markerKind,
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
        assertEquals(marker.safeId.value, marker.markerId.value)
        assertEquals(marker.safeId.value, marker.syntheticIdentityLabel.value)
    }

    @Test
    fun markerSafeIdUsesDeterministicKatFamilyTokenAndSafeNamespace() {
        val marker = marker()
        val evidence = namespaceEvidence(marker.safeId.value)

        assertEquals(SkaldVaultV1TestOnlyProviderIdentityMarkerFamily.DeterministicKat, marker.family)
        assertEquals(SkaldVaultV1TestOnlyProviderSyntheticIdentityFamily.DeterministicKat, evidence.candidateFamily)
        assertTrue(marker.safeId.value.contains("-deterministic-kat-"))
        assertFalse(evidence.candidateLabelRejected)
        assertTrue(marker.deterministicSafeIdentity)
        assertTrue(marker.testOnlyNamespaceConformant)
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
    fun markerSafeIdContainsNoUuidRandomTimestampOrHashLookingMaterial() {
        val safeId = marker().safeId.value
        val uuidLike = Regex("""[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}""")
        val timestampLike = Regex("""\d{4}-\d{2}-\d{2}|\d{10,}""")
        val longHexToken = Regex("""\b[0-9a-f]{64}\b""")

        assertFalse(uuidLike.containsMatchIn(safeId))
        assertFalse(timestampLike.containsMatchIn(safeId))
        assertFalse(longHexToken.containsMatchIn(safeId))
        assertFalse(safeId.contains("random", ignoreCase = true))
        assertFalse(safeId.contains("hash", ignoreCase = true))
    }

    @Test
    fun markerSafeIdIsNotAddressTxidDescriptorXpubNsecPsbtOrTransactionHexLike() {
        val safeId = marker().safeId.value
        val compact = safeId.replace("-", "")

        assertFalse(Regex("""(?i)\b(?:bc1|tb1|bcrt1)[a-z0-9]{20,}\b""").containsMatchIn(safeId))
        assertFalse(Regex("""\b[0-9a-fA-F]{64}\b""").containsMatchIn(safeId))
        assertFalse(safeId.contains("descriptor", ignoreCase = true))
        assertFalse(safeId.contains("wpkh", ignoreCase = true))
        assertFalse(safeId.contains("tr(", ignoreCase = true))
        assertFalse(Regex("""(?i)\b[xt]prv\b|\b[xt]pub\b""").containsMatchIn(safeId))
        assertFalse(Regex("""(?i)\bnsec1[a-z0-9]+""").containsMatchIn(safeId))
        assertFalse(Regex("""(?i)\bpsbt""").containsMatchIn(safeId))
        assertFalse(compact.length > 20 && compact.all { character -> character in '0'..'9' || character in 'a'..'f' })
    }

    @Test
    fun markerReadsTransitionGateEvidenceAndTreatsHumanApprovalAsInertMarkerOnly() {
        val marker = marker()

        assertTrue(marker.transitionGateReviewed)
        assertTrue(marker.transitionGateHumanReviewReady)
        assertTrue(marker.userApprovedInertMarkerPass)
        assertTrue(marker.markerCreated)
        assertTrue(marker.inertMarkerOnly)
        assertFalse(marker.implementationAuthorizationPresent)
        assertFalse(marker.providerImplementationPresent)
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
    fun markerDoesNotExposeProviderHandlesOrRuntimeProviderSurfaces() {
        val marker = marker()

        assertFalse(marker.providerRegistryEntryPresent)
        assertFalse(marker.providerFactoryPresent)
        assertFalse(marker.providerDispatcherPresent)
        assertFalse(marker.executorTargetPresent)
        assertFalse(marker.providerImplementationPresent)
        assertFalse(marker.productionProviderIdentityPresent)
        assertFalse(marker.registrySelectable)
        assertFalse(marker.factoryReachable)
        assertFalse(marker.dispatcherReachable)
        assertFalse(marker.executorTargetable)
        assertFalse(marker.providerKatExecutorReachable)
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
        assertTrue(marker().disabledProviderOnly)
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
        assertFalse(marker.providerOperationExecuted)
        assertFalse(marker.cryptoExecuted)
        assertFalse(marker.katRunnerPresent)
        assertFalse(marker.katExecutorPresent)
    }

    @Test
    fun markerCannotParticipateInVaultLifecyclePersistenceOrProductionSync() {
        val marker = marker()

        assertFalse(marker.canUseForVaultLifecycle)
        assertFalse(marker.canUseForPersistence)
        assertFalse(marker.canUseForSync)
        assertFalse(marker.vaultPersistencePresent)
        assertFalse(marker.productionSyncPresent)
    }

    @Test
    fun markerCannotSignBroadcastOrEnableMainnet() {
        val marker = marker()

        assertFalse(marker.canUseForSigning)
        assertFalse(marker.canUseForBroadcasting)
        assertFalse(marker.canUseForMainnet)
        assertFalse(marker.signingBroadcastingPresent)
        assertFalse(marker.uiPresent)
        assertFalse(marker.endpointPresent)
        assertFalse(marker.mainnetPresent)
    }

    @Test
    fun markerContainsNoRawKatPublicVectorOrTracePayloadMaterial() {
        val marker = marker()

        assertFalse(marker.rawKatMaterialPresent)
        assertFalse(marker.publicVectorBytesPresent)
        assertFalse(marker.publicVectorHexPresent)
        assertFalse(marker.tracePayloadPresent)
    }

    @Test
    fun markerCreatedIsNotProviderSelectionOperationCryptoKatVaultSyncUiEndpointOrMainnetAuthorization() {
        val marker = marker()

        assertTrue(marker.markerCreated)
        assertFalse(marker.implementationAuthorizationPresent)
        assertFalse(marker.productionAuthorizationPresent)
        assertFalse(marker.providerSelectionAuthorizationPresent)
        assertFalse(marker.providerOperationAuthorizationPresent)
        assertFalse(marker.cryptoAuthorizationPresent)
        assertFalse(marker.katRunnerAuthorizationPresent)
        assertFalse(marker.katExecutorAuthorizationPresent)
        assertFalse(marker.providerKatExecutorAuthorizationPresent)
        assertFalse(marker.vaultPersistenceAuthorizationPresent)
        assertFalse(marker.syncAuthorizationPresent)
        assertFalse(marker.signingBroadcastingAuthorizationPresent)
        assertFalse(marker.uiAuthorizationPresent)
        assertFalse(marker.endpointAuthorizationPresent)
        assertFalse(marker.mainnetAuthorizationPresent)
    }

    @Test
    fun markerEvidenceCountsAreSafeAndNoWarningsOrBlockersArePresent() {
        val marker = marker()

        assertEquals(8, marker.evidenceCount)
        assertEquals(0, marker.blockerCount)
        assertEquals(0, marker.warningCount)
    }

    @Test
    fun markerDoesNotExposeRawMaterialOrRuntimeReferences() {
        val marker = marker()
        val safeOutputs = listOf(
            marker.toString(),
            marker.safeId.toString(),
            marker.markerId.toString(),
            marker.syntheticIdentityLabel.toString(),
            marker.displayLabel.toString(),
        )
        val rejectedTerms = listOf(
            "raw material",
            "raw KAT material",
            "vector bytes",
            "vector hex",
            "public vector bytes",
            "public vector hex",
            "trace payload",
            "secret",
            "provider handle",
            "crypto object",
            "storage reference",
            "backend reference",
            "endpoint reference",
            "wallet reference",
            "source location",
            "stack trace",
            "implementation payload",
            "diagnostic payload",
            "analytics payload",
            "crash report",
            "support export",
            "endpoint value",
            "filesystem path",
            "txid",
            "descriptor",
            "address",
            "PSBT",
            "transaction hex",
            "Nostr nsec",
            "Lightning credential",
            "Cashu proof",
            "backend credential",
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

    private fun marker() = SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy.currentImplementationMarker()

    private fun namespaceEvidence(candidate: String) =
        SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespacePolicy.evaluateNamespace(
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceRequest(
                candidateSafeId = SkaldVaultV1TestOnlyProviderSyntheticIdentitySafeId(candidate),
            ),
        )
}
