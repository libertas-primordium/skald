package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGatePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditCheck
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditKind
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditSourceSet
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityMarkerValidationPolicy
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionDecision
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class VaultTestOnlyProviderIdentityMarkerCompletionAuditTest {
    private fun completionAudit() =
        SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditPolicy
            .currentProviderIdentityMarkerCompletionAudit()

    @Test
    fun markerCompletionAuditIsPresentAndCommonTestOnly() {
        val audit = completionAudit()

        assertEquals(1, audit.completionAuditVersion)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditKind
                .InertTestOnlyProviderIdentityMarkerCompletionAudit,
            audit.completionAuditKind,
        )
        assertEquals(SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditSourceSet.CommonTest, audit.sourceSet)
        assertTrue(audit.markerCompletionAuditPassed)
        assertTrue(audit.markerCompletionAuditIsCommonTestOnlyEvidence)
        assertEquals(0, audit.failureLabels.size)
        assertEquals(0, audit.blockerCount)
        assertEquals(0, audit.warningCount)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditCheck.entries.size,
            audit.completionCheckCount,
        )
    }

    @Test
    fun markerCompletionAuditReadsTransitionMarkerValidationAndSuiteEvidence() {
        val audit = completionAudit()
        val transitionGate =
            SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGatePolicy
                .currentProviderIdentityImplementationTransitionGate()
        val marker = SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy.currentImplementationMarker()
        val validation =
            SkaldVaultV1TestOnlyProviderIdentityMarkerValidationPolicy.currentProviderIdentityMarkerValidation()
        val suite =
            SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportPolicy.currentProviderIdentityMarkerSuiteReport()

        assertTrue(audit.transitionGatePresent)
        assertTrue(audit.transitionGateHumanReviewReady)
        assertTrue(transitionGate.reviewReadyForHumanDecision)
        assertTrue(audit.markerPresent)
        assertTrue(audit.markerCreated)
        assertTrue(audit.implementationMarkerPresent)
        assertEquals(SkaldVaultV1TestOnlyProviderIdentityMarkerSourceSet.CommonTest, marker.sourceSet)
        assertTrue(audit.markerValidationPresent)
        assertTrue(audit.markerValidationPassed)
        assertTrue(validation.markerValidationPassed)
        assertTrue(audit.markerSuiteReportPresent)
        assertTrue(audit.markerSuiteReportPassed)
        assertTrue(suite.markerSuiteReportPassed)
    }

    @Test
    fun markerCompletionAuditMarksTheMarkerChainComplete() {
        val audit = completionAudit()

        assertTrue(audit.markerChainComplete)
        assertTrue(audit.markerCommonTestOnly)
        assertTrue(audit.markerValidationCommonTestOnly)
        assertTrue(audit.markerSuiteReportCommonTestOnly)
        assertTrue(audit.markerInert)
        assertTrue(audit.userApprovalScopedToInertMarkerChain)
    }

    @Test
    fun markerCompletionAuditTreatsUpstreamTrueBooleansAsEvidenceOnly() {
        val audit = completionAudit()

        assertTrue(audit.markerCreated)
        assertTrue(audit.implementationMarkerPresent)
        assertTrue(audit.markerValidationPassed)
        assertTrue(audit.markerSuiteReportPassed)
        assertTrue(audit.markerCreatedIsInertMarkerEvidenceOnly)
        assertTrue(audit.implementationMarkerPresentIsInertMarkerEvidenceOnly)
        assertTrue(audit.markerValidationPassedIsValidationEvidenceOnly)
        assertTrue(audit.markerSuiteReportPassedIsSuiteReportEvidenceOnly)
        assertFalse(audit.implementationAuthorizationPresent)
        assertFalse(audit.productionAuthorizationPresent)
    }

    @Test
    fun markerCompletionAuditValidatesExactSafeSyntheticIdentityLabel() {
        val audit = completionAudit()
        val marker = SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy.currentImplementationMarker()
        val expected = "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"

        assertTrue(audit.markerIdentityLabelMatchesExpected)
        assertTrue(audit.markerIdentityLabelSafe)
        assertEquals(expected, marker.markerId.value)
        assertEquals(expected, marker.safeId.value)
        assertEquals(expected, marker.syntheticIdentityLabel.value)
        assertFalse(marker.toString().contains(expected))
        assertFalse(audit.toString().contains(expected))
    }

    @Test
    fun markerCompletionAuditConfirmsMarkerLabelShapeIsDeterministicAndNotWalletMaterialLike() {
        val audit = completionAudit()
        val label = SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy.currentImplementationMarker().safeId.value

        assertTrue(audit.markerDeterministic)
        assertTrue(audit.markerInert)
        assertTrue(audit.markerSafeLabelOnly)
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
    fun markerCompletionAuditConfirmsNoProviderImplementationInstanceOrHandles() {
        val audit = completionAudit()
        val marker = SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy.currentImplementationMarker()

        assertFalse(audit.implementsVaultCryptoProvider)
        assertFalse(audit.containsVaultCryptoProvider)
        assertFalse(audit.providerHandlePresent)
        assertFalse(marker.vaultCryptoProviderInstanceExposed)
        assertFalse(marker.runtimeSelectable)
        assertFalse(marker.registrySelectable)
        assertFalse(marker.factoryReachable)
        assertFalse(marker.dispatcherReachable)
    }

    @Test
    fun markerCompletionAuditConfirmsNoRegistryFactoryDispatcherExecutorOrExecution() {
        val audit = completionAudit()

        assertFalse(audit.providerRegistryEntryPresent)
        assertFalse(audit.providerFactoryPresent)
        assertFalse(audit.providerDispatcherPresent)
        assertFalse(audit.executorTargetPresent)
        assertFalse(audit.providerImplementationPresent)
        assertFalse(audit.productionProviderIdentityPresent)
        assertFalse(audit.providerOperationExecuted)
        assertFalse(audit.cryptoExecuted)
        assertFalse(audit.providerKatExecutorPresent)
    }

    @Test
    fun markerCompletionAuditConfirmsNoKatRunnerExecutorRawMaterialPublicVectorsOrTracePayload() {
        val audit = completionAudit()

        assertFalse(audit.katRunnerPresent)
        assertFalse(audit.katExecutorPresent)
        assertFalse(audit.rawKatMaterialPresent)
        assertFalse(audit.publicVectorBytesPresent)
        assertFalse(audit.publicVectorHexPresent)
        assertFalse(audit.tracePayloadPresent)
    }

    @Test
    fun markerCompletionAuditConfirmsNoVaultStorageSyncSigningUiEndpointOrMainnetPath() {
        val audit = completionAudit()

        assertFalse(audit.vaultLifecyclePresent)
        assertFalse(audit.vaultPersistencePresent)
        assertFalse(audit.secureSecretStorageSuccessPresent)
        assertFalse(audit.secureMetadataStorageSuccessPresent)
        assertFalse(audit.productionSyncPresent)
        assertFalse(audit.signingBroadcastingPresent)
        assertFalse(audit.uiPresent)
        assertFalse(audit.endpointPresent)
        assertFalse(audit.mainnetPresent)
    }

    @Test
    fun markerCompletionAuditKeepsProductionProviderUnselectableAndDisabledProviderOnly() {
        val audit = completionAudit()
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertFalse(audit.productionProviderSelectable)
        assertTrue(audit.disabledProviderOnly)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertTrue(selection.selectedProviderIsDisabled)
        assertTrue(selection.selectedProvider is DisabledVaultCryptoProvider)
        assertEquals(VaultCryptoProviderSelectionDecision.DisabledProviderSelected, selection.decision)
        assertFalse(selection.productionProviderSelectable)
    }

    @Test
    fun markerCompletionAuditPassingIsNotAnyAuthorization() {
        val audit = completionAudit()

        assertTrue(audit.markerCompletionAuditPassed)
        assertFalse(audit.implementationAuthorizationPresent)
        assertFalse(audit.productionAuthorizationPresent)
        assertFalse(audit.providerSelectionAuthorizationPresent)
        assertFalse(audit.providerOperationAuthorizationPresent)
        assertFalse(audit.cryptoAuthorizationPresent)
        assertFalse(audit.katRunnerAuthorizationPresent)
        assertFalse(audit.katExecutorAuthorizationPresent)
        assertFalse(audit.providerKatExecutorAuthorizationPresent)
        assertFalse(audit.vaultPersistenceAuthorizationPresent)
        assertFalse(audit.syncAuthorizationPresent)
        assertFalse(audit.signingBroadcastingAuthorizationPresent)
        assertFalse(audit.uiAuthorizationPresent)
        assertFalse(audit.endpointAuthorizationPresent)
        assertFalse(audit.mainnetAuthorizationPresent)
    }

    @Test
    fun markerCompletionAuditOutputIsRedactedAndMaterialFree() {
        val audit = completionAudit()
        val output = listOf(
            audit.toString(),
            audit.completionAuditId.toString(),
            audit.displayLabel.toString(),
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
        assertContains(output, "MARKER_COMPLETION_AUDIT_ONLY")
        assertContains(output, "NOT_AUTHORIZATION")
        assertContains(output, "DISABLED_PROVIDER_ONLY")
        forbiddenText.forEach { assertFalse(output.contains(it, ignoreCase = true), it) }
    }

    @Test
    fun markerCompletionAuditPreservesSourceMaterialCorpusBoundaries() {
        val audit = completionAudit()

        assertTrue(audit.buildHistoryExcludedFromNormalSourceMaterialCorpus)
        assertTrue(audit.localArtifactRootExcludedFromNormalSourceMaterialCorpus)
        assertTrue(audit.docsReadmeUseDedicatedCorpus)
    }
}
