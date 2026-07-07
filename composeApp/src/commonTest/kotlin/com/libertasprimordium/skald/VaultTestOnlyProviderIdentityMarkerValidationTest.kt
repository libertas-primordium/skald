package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGatePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityMarkerValidationCheck
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityMarkerValidationKind
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityMarkerValidationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityMarkerValidationSourceSet
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionDecision
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityMarkerValidationTest {
    private fun validation() =
        SkaldVaultV1TestOnlyProviderIdentityMarkerValidationPolicy.currentProviderIdentityMarkerValidation()

    @Test
    fun markerValidationReportIsPresentAndCommonTestOnly() {
        val validation = validation()

        assertEquals(1, validation.validationVersion)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityMarkerValidationKind
                .InertTestOnlyProviderIdentityMarkerValidation,
            validation.validationKind,
        )
        assertEquals(SkaldVaultV1TestOnlyProviderIdentityMarkerValidationSourceSet.CommonTest, validation.sourceSet)
        assertTrue(validation.markerValidationPassed)
        assertTrue(validation.markerValidationIsCommonTestOnlyEvidence)
        assertEquals(0, validation.failureLabels.size)
        assertEquals(0, validation.blockerCount)
        assertEquals(0, validation.warningCount)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityMarkerValidationCheck.entries.size,
            validation.validationCheckCount,
        )
    }

    @Test
    fun markerValidationReadsInertMarkerAndTransitionGateEvidence() {
        val validation = validation()
        val marker = SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy.currentImplementationMarker()
        val transitionGate =
            SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGatePolicy
                .currentProviderIdentityImplementationTransitionGate()

        assertTrue(validation.markerPresent)
        assertTrue(validation.markerCreated)
        assertTrue(validation.implementationMarkerPresent)
        assertTrue(validation.markerCommonTestOnly)
        assertEquals(SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet.CommonTest, marker.sourceSet)
        assertTrue(validation.transitionGatePresent)
        assertTrue(validation.transitionGateHumanReviewReady)
        assertTrue(transitionGate.reviewReadyForHumanDecision)
        assertFalse(transitionGate.implementationAuthorized)
    }

    @Test
    fun markerValidationTreatsMarkerCreationAndImplementationMarkerAsEvidenceOnly() {
        val validation = validation()

        assertTrue(validation.markerCreated)
        assertTrue(validation.implementationMarkerPresent)
        assertTrue(validation.markerCreatedIsInertMarkerEvidenceOnly)
        assertTrue(validation.implementationMarkerPresentIsInertMarkerEvidenceOnly)
        assertTrue(validation.userApprovalScopedToInertMarker)
        assertFalse(validation.implementationAuthorizationPresent)
        assertFalse(validation.productionAuthorizationPresent)
    }

    @Test
    fun markerValidationUsesExactSafeSyntheticIdentityLabel() {
        val validation = validation()
        val marker = SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy.currentImplementationMarker()
        val expected = "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"

        assertTrue(validation.markerIdentityLabelMatchesExpected)
        assertTrue(validation.markerIdentityLabelSafe)
        assertEquals(expected, marker.markerId.value)
        assertEquals(expected, marker.safeId.value)
        assertEquals(expected, marker.syntheticIdentityLabel.value)
        assertFalse(marker.toString().contains(expected))
        assertFalse(validation.toString().contains(expected))
    }

    @Test
    fun markerValidationConfirmsMarkerLabelIsDeterministicAndNotWalletMaterialLike() {
        val validation = validation()
        val label = SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy.currentImplementationMarker().safeId.value

        assertTrue(validation.markerDeterministic)
        assertTrue(validation.markerInert)
        assertTrue(validation.markerSafeLabelOnly)
        assertFalse(label.contains(Regex("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}")))
        assertFalse(label.contains("random", ignoreCase = true))
        assertFalse(label.contains("timestamp", ignoreCase = true))
        assertFalse(label.contains("hash", ignoreCase = true))
        assertFalse(label.startsWith("bc1", ignoreCase = true))
        assertFalse(label.startsWith("tb1", ignoreCase = true))
        assertFalse(label.startsWith("bcrt1", ignoreCase = true))
        assertFalse(label.contains("wpkh(", ignoreCase = true))
        assertFalse(label.contains("tr(", ignoreCase = true))
        assertFalse(label.contains("xpub", ignoreCase = true))
        assertFalse(label.contains("xprv", ignoreCase = true))
        assertFalse(label.contains("tprv", ignoreCase = true))
        assertFalse(label.startsWith("nsec", ignoreCase = true))
        assertFalse(label.startsWith("psbt", ignoreCase = true))
        assertFalse(Regex("^[0-9a-fA-F]{64}$").matches(label))
        assertFalse(Regex("^[0-9a-fA-F]{120,}$").matches(label))
    }

    @Test
    fun markerValidationConfirmsNoProviderImplementationInstanceOrHandles() {
        val validation = validation()
        val marker = SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy.currentImplementationMarker()

        assertFalse(validation.implementsVaultCryptoProvider)
        assertFalse(validation.containsVaultCryptoProvider)
        assertFalse(validation.providerHandlePresent)
        assertFalse(marker.vaultCryptoProviderInstanceExposed)
        assertFalse(marker.runtimeSelectable)
        assertFalse(marker.registrySelectable)
        assertFalse(marker.factoryReachable)
        assertFalse(marker.dispatcherReachable)
    }

    @Test
    fun markerValidationConfirmsNoRegistryFactoryDispatcherExecutorOrExecution() {
        val validation = validation()

        assertFalse(validation.providerRegistryEntryPresent)
        assertFalse(validation.providerFactoryPresent)
        assertFalse(validation.providerDispatcherPresent)
        assertFalse(validation.executorTargetPresent)
        assertFalse(validation.providerImplementationPresent)
        assertFalse(validation.productionProviderIdentityPresent)
        assertFalse(validation.providerOperationExecuted)
        assertFalse(validation.cryptoExecuted)
        assertFalse(validation.providerKatExecutorPresent)
    }

    @Test
    fun markerValidationConfirmsNoKatRunnerExecutorRawMaterialPublicVectorsOrTracePayload() {
        val validation = validation()

        assertFalse(validation.katRunnerPresent)
        assertFalse(validation.katExecutorPresent)
        assertFalse(validation.rawKatMaterialPresent)
        assertFalse(validation.publicVectorBytesPresent)
        assertFalse(validation.publicVectorHexPresent)
        assertFalse(validation.tracePayloadPresent)
    }

    @Test
    fun markerValidationConfirmsNoVaultStorageSyncSigningUiEndpointOrMainnetPath() {
        val validation = validation()

        assertFalse(validation.vaultLifecyclePresent)
        assertFalse(validation.vaultPersistencePresent)
        assertFalse(validation.secureSecretStorageSuccessPresent)
        assertFalse(validation.secureMetadataStorageSuccessPresent)
        assertFalse(validation.productionSyncPresent)
        assertFalse(validation.signingBroadcastingPresent)
        assertFalse(validation.uiPresent)
        assertFalse(validation.endpointPresent)
        assertFalse(validation.mainnetPresent)
    }

    @Test
    fun markerValidationKeepsProductionProviderUnselectableAndDisabledProviderOnly() {
        val validation = validation()
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertFalse(validation.productionProviderSelectable)
        assertTrue(validation.disabledProviderOnly)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertTrue(selection.selectedProviderIsDisabled)
        assertTrue(selection.selectedProvider is DisabledVaultCryptoProvider)
        assertEquals(VaultCryptoProviderSelectionDecision.DisabledProviderSelected, selection.decision)
        assertFalse(selection.productionProviderSelectable)
    }

    @Test
    fun markerValidationPassingIsNotAnyAuthorization() {
        val validation = validation()

        assertTrue(validation.markerValidationPassed)
        assertFalse(validation.implementationAuthorizationPresent)
        assertFalse(validation.productionAuthorizationPresent)
        assertFalse(validation.providerSelectionAuthorizationPresent)
        assertFalse(validation.providerOperationAuthorizationPresent)
        assertFalse(validation.cryptoAuthorizationPresent)
        assertFalse(validation.katRunnerAuthorizationPresent)
        assertFalse(validation.katExecutorAuthorizationPresent)
        assertFalse(validation.providerKatExecutorAuthorizationPresent)
        assertFalse(validation.vaultPersistenceAuthorizationPresent)
        assertFalse(validation.syncAuthorizationPresent)
        assertFalse(validation.signingBroadcastingAuthorizationPresent)
        assertFalse(validation.uiAuthorizationPresent)
        assertFalse(validation.endpointAuthorizationPresent)
        assertFalse(validation.mainnetAuthorizationPresent)
    }

    @Test
    fun markerValidationOutputIsRedactedAndMaterialFree() {
        val validation = validation()
        val output = listOf(
            validation.toString(),
            validation.validationId.toString(),
            validation.displayLabel.toString(),
        ).joinToString(separator = " ")
        val forbiddenText = listOf(
            "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker",
            "ByteArray",
            "UByteArray",
            "CharArray",
            "passphrase",
            "mnemonic",
            "seed phrase",
            "private key",
            "nsec",
            "ciphertext",
            "plaintext",
            "provider handle",
            "source location",
            "stack trace",
            "diagnostics payload",
            "analytics payload",
            "crash-report payload",
            "support-export payload",
            "endpoint value",
            "filesystem path",
            "txid",
            "descriptor",
            "address",
            "PSBT",
            "transaction hex",
            "Lightning credential",
            "Cashu proof",
            "backend credential",
        )

        assertContains(output, "REDACTED")
        assertContains(output, "COMMON_TEST_ONLY")
        assertContains(output, "MARKER_VALIDATION_ONLY")
        assertContains(output, "NOT_AUTHORIZATION")
        assertContains(output, "DISABLED_PROVIDER_ONLY")
        forbiddenText.forEach { assertFalse(output.contains(it, ignoreCase = true), it) }
    }

    @Test
    fun markerValidationPreservesSourceMaterialCorpusBoundaries() {
        val validation = validation()

        assertTrue(validation.buildHistoryExcludedFromNormalSourceMaterialCorpus)
        assertTrue(validation.docsReadmeUseDedicatedCorpus)
    }
}
