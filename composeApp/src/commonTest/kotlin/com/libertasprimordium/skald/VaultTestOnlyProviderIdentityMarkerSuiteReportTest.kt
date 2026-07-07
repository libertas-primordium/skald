package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGatePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportCheck
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportKind
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportSourceSet
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityMarkerValidationPolicy
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionDecision
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityMarkerSuiteReportTest {
    private fun suiteReport() =
        SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportPolicy.currentProviderIdentityMarkerSuiteReport()

    @Test
    fun markerSuiteReportIsPresentAndCommonTestOnly() {
        val suite = suiteReport()

        assertEquals(1, suite.suiteReportVersion)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportKind
                .InertTestOnlyProviderIdentityMarkerSuiteReport,
            suite.suiteReportKind,
        )
        assertEquals(SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportSourceSet.CommonTest, suite.sourceSet)
        assertTrue(suite.markerSuiteReportPassed)
        assertTrue(suite.markerSuiteReportIsCommonTestOnlyEvidence)
        assertEquals(0, suite.failureLabels.size)
        assertEquals(0, suite.blockerCount)
        assertEquals(0, suite.warningCount)
        assertEquals(SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportCheck.entries.size, suite.suiteCheckCount)
    }

    @Test
    fun markerSuiteReportReadsTransitionGateMarkerAndValidationEvidence() {
        val suite = suiteReport()
        val transitionGate =
            SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGatePolicy
                .currentProviderIdentityImplementationTransitionGate()
        val marker = SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy.currentImplementationMarker()
        val validation =
            SkaldVaultV1TestOnlyProviderIdentityMarkerValidationPolicy.currentProviderIdentityMarkerValidation()

        assertTrue(suite.transitionGatePresent)
        assertTrue(suite.transitionGateHumanReviewReady)
        assertTrue(transitionGate.reviewReadyForHumanDecision)
        assertTrue(suite.markerPresent)
        assertTrue(suite.markerCreated)
        assertTrue(suite.implementationMarkerPresent)
        assertEquals(SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet.CommonTest, marker.sourceSet)
        assertTrue(suite.markerValidationPresent)
        assertTrue(suite.markerValidationPassed)
        assertTrue(validation.markerValidationPassed)
    }

    @Test
    fun markerSuiteReportTreatsMarkerAndValidationBooleansAsEvidenceOnly() {
        val suite = suiteReport()

        assertTrue(suite.markerCreated)
        assertTrue(suite.implementationMarkerPresent)
        assertTrue(suite.markerValidationPassed)
        assertTrue(suite.markerCreatedIsInertMarkerEvidenceOnly)
        assertTrue(suite.implementationMarkerPresentIsInertMarkerEvidenceOnly)
        assertTrue(suite.markerValidationPassedIsValidationEvidenceOnly)
        assertTrue(suite.userApprovalScopedToInertMarker)
        assertFalse(suite.implementationAuthorizationPresent)
        assertFalse(suite.productionAuthorizationPresent)
    }

    @Test
    fun markerSuiteReportValidatesExactSafeSyntheticIdentityLabel() {
        val suite = suiteReport()
        val marker = SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy.currentImplementationMarker()
        val expected = "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"

        assertTrue(suite.markerIdentityLabelMatchesExpected)
        assertTrue(suite.markerIdentityLabelSafe)
        assertEquals(expected, marker.markerId.value)
        assertEquals(expected, marker.safeId.value)
        assertEquals(expected, marker.syntheticIdentityLabel.value)
        assertFalse(marker.toString().contains(expected))
        assertFalse(suite.toString().contains(expected))
    }

    @Test
    fun markerSuiteReportConfirmsMarkerLabelShapeIsDeterministicAndNotWalletMaterialLike() {
        val suite = suiteReport()
        val label = SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy.currentImplementationMarker().safeId.value

        assertTrue(suite.markerDeterministic)
        assertTrue(suite.markerInert)
        assertTrue(suite.markerSafeLabelOnly)
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
    fun markerSuiteReportConfirmsNoProviderImplementationInstanceOrHandles() {
        val suite = suiteReport()
        val marker = SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy.currentImplementationMarker()

        assertFalse(suite.implementsVaultCryptoProvider)
        assertFalse(suite.containsVaultCryptoProvider)
        assertFalse(suite.providerHandlePresent)
        assertFalse(marker.vaultCryptoProviderInstanceExposed)
        assertFalse(marker.runtimeSelectable)
        assertFalse(marker.registrySelectable)
        assertFalse(marker.factoryReachable)
        assertFalse(marker.dispatcherReachable)
    }

    @Test
    fun markerSuiteReportConfirmsNoRegistryFactoryDispatcherExecutorOrExecution() {
        val suite = suiteReport()

        assertFalse(suite.providerRegistryEntryPresent)
        assertFalse(suite.providerFactoryPresent)
        assertFalse(suite.providerDispatcherPresent)
        assertFalse(suite.executorTargetPresent)
        assertFalse(suite.providerImplementationPresent)
        assertFalse(suite.productionProviderIdentityPresent)
        assertFalse(suite.providerOperationExecuted)
        assertFalse(suite.cryptoExecuted)
        assertFalse(suite.providerKatExecutorPresent)
    }

    @Test
    fun markerSuiteReportConfirmsNoKatRunnerExecutorRawMaterialPublicVectorsOrTracePayload() {
        val suite = suiteReport()

        assertFalse(suite.katRunnerPresent)
        assertFalse(suite.katExecutorPresent)
        assertFalse(suite.rawKatMaterialPresent)
        assertFalse(suite.publicVectorBytesPresent)
        assertFalse(suite.publicVectorHexPresent)
        assertFalse(suite.tracePayloadPresent)
    }

    @Test
    fun markerSuiteReportConfirmsNoVaultStorageSyncSigningUiEndpointOrMainnetPath() {
        val suite = suiteReport()

        assertFalse(suite.vaultLifecyclePresent)
        assertFalse(suite.vaultPersistencePresent)
        assertFalse(suite.secureSecretStorageSuccessPresent)
        assertFalse(suite.secureMetadataStorageSuccessPresent)
        assertFalse(suite.productionSyncPresent)
        assertFalse(suite.signingBroadcastingPresent)
        assertFalse(suite.uiPresent)
        assertFalse(suite.endpointPresent)
        assertFalse(suite.mainnetPresent)
    }

    @Test
    fun markerSuiteReportKeepsProductionProviderUnselectableAndDisabledProviderOnly() {
        val suite = suiteReport()
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertFalse(suite.productionProviderSelectable)
        assertTrue(suite.disabledProviderOnly)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertTrue(selection.selectedProviderIsDisabled)
        assertTrue(selection.selectedProvider is DisabledVaultCryptoProvider)
        assertEquals(VaultCryptoProviderSelectionDecision.DisabledProviderSelected, selection.decision)
        assertFalse(selection.productionProviderSelectable)
    }

    @Test
    fun markerSuiteReportPassingIsNotAnyAuthorization() {
        val suite = suiteReport()

        assertTrue(suite.markerSuiteReportPassed)
        assertFalse(suite.implementationAuthorizationPresent)
        assertFalse(suite.productionAuthorizationPresent)
        assertFalse(suite.providerSelectionAuthorizationPresent)
        assertFalse(suite.providerOperationAuthorizationPresent)
        assertFalse(suite.cryptoAuthorizationPresent)
        assertFalse(suite.katRunnerAuthorizationPresent)
        assertFalse(suite.katExecutorAuthorizationPresent)
        assertFalse(suite.providerKatExecutorAuthorizationPresent)
        assertFalse(suite.vaultPersistenceAuthorizationPresent)
        assertFalse(suite.syncAuthorizationPresent)
        assertFalse(suite.signingBroadcastingAuthorizationPresent)
        assertFalse(suite.uiAuthorizationPresent)
        assertFalse(suite.endpointAuthorizationPresent)
        assertFalse(suite.mainnetAuthorizationPresent)
    }

    @Test
    fun markerSuiteReportOutputIsRedactedAndMaterialFree() {
        val suite = suiteReport()
        val output = listOf(
            suite.toString(),
            suite.suiteReportId.toString(),
            suite.displayLabel.toString(),
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
        assertContains(output, "MARKER_SUITE_REPORT_ONLY")
        assertContains(output, "NOT_AUTHORIZATION")
        assertContains(output, "DISABLED_PROVIDER_ONLY")
        forbiddenText.forEach { assertFalse(output.contains(it, ignoreCase = true), it) }
    }

    @Test
    fun markerSuiteReportPreservesSourceMaterialCorpusBoundaries() {
        val suite = suiteReport()

        assertTrue(suite.buildHistoryExcludedFromNormalSourceMaterialCorpus)
        assertTrue(suite.docsReadmeUseDedicatedCorpus)
    }
}
