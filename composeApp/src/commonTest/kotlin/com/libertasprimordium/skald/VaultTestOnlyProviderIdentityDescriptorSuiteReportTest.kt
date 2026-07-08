package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDescriptorCapability
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDescriptorPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportCheck
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportKind
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportSourceSet
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGatePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy
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

class VaultTestOnlyProviderIdentityDescriptorSuiteReportTest {
    private fun suiteReport() =
        SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportPolicy
            .currentProviderIdentityDescriptorSuiteReport()

    @Test
    fun providerIdentityDescriptorSuiteReportIsPresentAndCommonTestOnly() {
        val suiteReport = suiteReport()

        assertEquals(1, suiteReport.descriptorSuiteReportVersion)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportKind
                .InertTestOnlyProviderIdentityDescriptorSuiteReport,
            suiteReport.descriptorSuiteReportKind,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportSourceSet.CommonTest,
            suiteReport.sourceSet,
        )
        assertTrue(suiteReport.descriptorSuiteReportPassed)
        assertTrue(suiteReport.descriptorSuiteReportPassedIsCommonTestOnlyEvidence)
        assertTrue(suiteReport.descriptorCommonTestOnly)
        assertTrue(suiteReport.descriptorValidationCommonTestOnly)
        assertEquals(0, suiteReport.failureLabels.size)
        assertEquals(0, suiteReport.blockerCount)
        assertEquals(0, suiteReport.warningCount)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportCheck.entries.size,
            suiteReport.suiteCheckCount,
        )
    }

    @Test
    fun providerIdentityDescriptorSuiteReportReadsTransitionMarkerDescriptorAndValidationEvidence() {
        val suiteReport = suiteReport()
        val transitionGate =
            SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGatePolicy
                .currentProviderIdentityImplementationTransitionGate()
        val marker = SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy.currentImplementationMarker()
        val markerValidation =
            SkaldVaultV1TestOnlyProviderIdentityMarkerValidationPolicy.currentProviderIdentityMarkerValidation()
        val markerSuite =
            SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportPolicy.currentProviderIdentityMarkerSuiteReport()
        val markerCompletionAudit =
            SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditPolicy
                .currentProviderIdentityMarkerCompletionAudit()
        val descriptor = SkaldVaultV1TestOnlyProviderIdentityDescriptorPolicy.currentProviderIdentityDescriptor()
        val descriptorValidation =
            SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationPolicy
                .currentProviderIdentityDescriptorValidation()

        assertTrue(suiteReport.transitionGatePresent)
        assertTrue(suiteReport.transitionGateHumanReviewReady)
        assertTrue(transitionGate.reviewReadyForHumanDecision)
        assertTrue(suiteReport.markerPresent)
        assertTrue(suiteReport.markerCreated)
        assertTrue(suiteReport.implementationMarkerPresent)
        assertEquals(marker.markerCreated, suiteReport.markerCreated)
        assertTrue(suiteReport.markerValidationPresent)
        assertTrue(suiteReport.markerValidationPassed)
        assertEquals(markerValidation.markerValidationPassed, suiteReport.markerValidationPassed)
        assertTrue(suiteReport.markerSuiteReportPresent)
        assertTrue(suiteReport.markerSuiteReportPassed)
        assertEquals(markerSuite.markerSuiteReportPassed, suiteReport.markerSuiteReportPassed)
        assertTrue(suiteReport.markerCompletionAuditPresent)
        assertTrue(suiteReport.markerCompletionAuditPassed)
        assertEquals(markerCompletionAudit.markerCompletionAuditPassed, suiteReport.markerCompletionAuditPassed)
        assertTrue(suiteReport.descriptorPresent)
        assertTrue(suiteReport.descriptorCreated)
        assertEquals(descriptor.descriptorCreated, suiteReport.descriptorCreated)
        assertTrue(suiteReport.providerCapabilitiesDeclared)
        assertEquals(descriptor.providerCapabilitiesDeclared, suiteReport.providerCapabilitiesDeclared)
        assertTrue(suiteReport.descriptorValidationPresent)
        assertTrue(suiteReport.descriptorValidationPassed)
        assertEquals(descriptorValidation.descriptorValidationPassed, suiteReport.descriptorValidationPassed)
        assertTrue(suiteReport.descriptorChainComplete)
    }

    @Test
    fun providerIdentityDescriptorSuiteReportTreatsScopedTrueBooleansAsEvidenceOnly() {
        val suiteReport = suiteReport()

        assertTrue(suiteReport.markerCreated)
        assertTrue(suiteReport.implementationMarkerPresent)
        assertTrue(suiteReport.markerValidationPassed)
        assertTrue(suiteReport.markerSuiteReportPassed)
        assertTrue(suiteReport.markerCompletionAuditPassed)
        assertTrue(suiteReport.descriptorCreated)
        assertTrue(suiteReport.providerCapabilitiesDeclared)
        assertTrue(suiteReport.descriptorValidationPassed)
        assertTrue(suiteReport.descriptorSuiteReportPassed)
        assertTrue(suiteReport.markerCreatedIsInertMarkerEvidenceOnly)
        assertTrue(suiteReport.implementationMarkerPresentIsInertMarkerEvidenceOnly)
        assertTrue(suiteReport.markerValidationPassedIsValidationEvidenceOnly)
        assertTrue(suiteReport.markerSuiteReportPassedIsSuiteReportEvidenceOnly)
        assertTrue(suiteReport.markerCompletionAuditPassedIsCompletionAuditEvidenceOnly)
        assertTrue(suiteReport.descriptorCreatedIsDescriptorEvidenceOnly)
        assertTrue(suiteReport.providerCapabilitiesDeclaredIsDescriptorEvidenceOnly)
        assertTrue(suiteReport.descriptorValidationPassedIsValidationEvidenceOnly)
        assertTrue(suiteReport.userApprovalScopedToInertProviderIdentityChain)
        assertFalse(suiteReport.implementationAuthorizationPresent)
        assertFalse(suiteReport.productionAuthorizationPresent)
    }

    @Test
    fun providerIdentityDescriptorSuiteReportUsesExactSafeSyntheticIdentityLabel() {
        val suiteReport = suiteReport()
        val descriptor = SkaldVaultV1TestOnlyProviderIdentityDescriptorPolicy.currentProviderIdentityDescriptor()
        val expected = "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"

        assertEquals(expected, descriptor.providerIdentityLabel.value)
        assertTrue(suiteReport.markerIdentityLabelMatchesExpected)
        assertTrue(suiteReport.markerIdentityLabelSafe)
        assertTrue(suiteReport.descriptorSafeLabelOnly)
        assertTrue(suiteReport.descriptorDeterministic)
        assertTrue(suiteReport.descriptorPayloadFree)
        assertFalse(suiteReport.toString().contains(expected))
    }

    @Test
    fun providerIdentityDescriptorSuiteReportLabelIsDeterministicAndNotWalletMaterialLike() {
        val descriptor = SkaldVaultV1TestOnlyProviderIdentityDescriptorPolicy.currentProviderIdentityDescriptor()
        val label = descriptor.providerIdentityLabel.value

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
    fun providerIdentityDescriptorSuiteReportConfirmsPayloadFreeSafeEvidenceShape() {
        val suiteReport = suiteReport()

        assertTrue(suiteReport.descriptorPayloadFree)
        assertTrue(suiteReport.descriptorSafeLabelOnly)
        assertTrue(suiteReport.suiteChecks.all { it in SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportCheck.entries })
        assertTrue(suiteReport.evidenceCount > 0)
        assertTrue(suiteReport.suiteCheckCount > 0)
        assertFalse(suiteReport.tracePayloadPresent)
        assertFalse(suiteReport.rawKatMaterialPresent)
        assertFalse(suiteReport.publicVectorBytesPresent)
        assertFalse(suiteReport.publicVectorHexPresent)
    }

    @Test
    fun providerIdentityDescriptorSuiteReportConfirmsEveryExecutableCapabilityFalse() {
        val suiteReport = suiteReport()
        val descriptor = SkaldVaultV1TestOnlyProviderIdentityDescriptorPolicy.currentProviderIdentityDescriptor()
        val manifest = descriptor.capabilityManifest

        assertTrue(suiteReport.descriptorCapabilityManifestPresent)
        assertTrue(suiteReport.descriptorExecutableCapabilitiesAllFalse)
        assertTrue(suiteReport.providerCapabilitiesDeclared)
        assertTrue(manifest.providerCapabilitiesDeclared)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityDescriptorCapability.entries.size,
            manifest.capabilityCount,
        )
        assertFalse(suiteReport.providerExecutableCapabilitiesPresent)
        assertFalse(manifest.providerExecutableCapabilitiesPresent)
        assertFalse(suiteReport.canDeriveKdf)
        assertFalse(suiteReport.canEncrypt)
        assertFalse(suiteReport.canDecrypt)
        assertFalse(suiteReport.canGenerateKeys)
        assertFalse(suiteReport.canWrapKeys)
        assertFalse(suiteReport.canUnwrapKeys)
        assertFalse(suiteReport.canRunProviderOperations)
        assertFalse(suiteReport.canRunKat)
        assertFalse(suiteReport.canPersistVault)
        assertFalse(suiteReport.canAccessSecureStorage)
        assertFalse(suiteReport.canAccessSecureMetadata)
        assertFalse(suiteReport.canSelectInProduction)
        assertFalse(suiteReport.canReachMainnet)
    }

    @Test
    fun providerIdentityDescriptorSuiteReportConfirmsNoVaultCryptoProviderImplementationInstanceOrHandle() {
        val suiteReport = suiteReport()

        assertTrue(suiteReport.descriptorInert)
        assertFalse(suiteReport.implementsVaultCryptoProvider)
        assertFalse(suiteReport.containsVaultCryptoProvider)
        assertFalse(suiteReport.vaultCryptoProviderInstanceExposed)
        assertFalse(suiteReport.providerHandlePresent)
        assertFalse(suiteReport.providerImplementationPresent)
        assertFalse(suiteReport.productionProviderIdentityPresent)
    }

    @Test
    fun providerIdentityDescriptorSuiteReportConfirmsNoRegistryFactoryDispatcherExecutorOrExecution() {
        val suiteReport = suiteReport()

        assertFalse(suiteReport.providerRegistryEntryPresent)
        assertFalse(suiteReport.providerFactoryPresent)
        assertFalse(suiteReport.providerDispatcherPresent)
        assertFalse(suiteReport.executorTargetPresent)
        assertFalse(suiteReport.providerOperationExecuted)
        assertFalse(suiteReport.cryptoExecuted)
        assertFalse(suiteReport.providerKatExecutorPresent)
    }

    @Test
    fun providerIdentityDescriptorSuiteReportConfirmsNoKatRunnerExecutorRawMaterialPublicVectorsOrTracePayload() {
        val suiteReport = suiteReport()

        assertFalse(suiteReport.katRunnerPresent)
        assertFalse(suiteReport.katExecutorPresent)
        assertFalse(suiteReport.rawKatMaterialPresent)
        assertFalse(suiteReport.publicVectorBytesPresent)
        assertFalse(suiteReport.publicVectorHexPresent)
        assertFalse(suiteReport.tracePayloadPresent)
    }

    @Test
    fun providerIdentityDescriptorSuiteReportConfirmsNoVaultStorageSyncSigningUiEndpointOrMainnetPath() {
        val suiteReport = suiteReport()

        assertFalse(suiteReport.vaultLifecyclePresent)
        assertFalse(suiteReport.vaultPersistencePresent)
        assertFalse(suiteReport.secureSecretStorageSuccessPresent)
        assertFalse(suiteReport.secureMetadataStorageSuccessPresent)
        assertFalse(suiteReport.productionSyncPresent)
        assertFalse(suiteReport.signingBroadcastingPresent)
        assertFalse(suiteReport.uiPresent)
        assertFalse(suiteReport.endpointPresent)
        assertFalse(suiteReport.mainnetPresent)
    }

    @Test
    fun providerIdentityDescriptorSuiteReportKeepsProductionProviderUnselectableAndDisabledProviderOnly() {
        val suiteReport = suiteReport()
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertFalse(suiteReport.productionProviderSelectable)
        assertTrue(suiteReport.disabledProviderOnly)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertTrue(selection.selectedProviderIsDisabled)
        assertTrue(selection.selectedProvider is DisabledVaultCryptoProvider)
        assertEquals(VaultCryptoProviderSelectionDecision.DisabledProviderSelected, selection.decision)
        assertFalse(selection.productionProviderSelectable)
    }

    @Test
    fun providerIdentityDescriptorSuiteReportPassedIsNotAnyAuthorization() {
        val suiteReport = suiteReport()

        assertTrue(suiteReport.descriptorSuiteReportPassed)
        assertFalse(suiteReport.implementationAuthorizationPresent)
        assertFalse(suiteReport.productionAuthorizationPresent)
        assertFalse(suiteReport.providerSelectionAuthorizationPresent)
        assertFalse(suiteReport.providerOperationAuthorizationPresent)
        assertFalse(suiteReport.cryptoAuthorizationPresent)
        assertFalse(suiteReport.katRunnerAuthorizationPresent)
        assertFalse(suiteReport.katExecutorAuthorizationPresent)
        assertFalse(suiteReport.providerKatExecutorAuthorizationPresent)
        assertFalse(suiteReport.vaultPersistenceAuthorizationPresent)
        assertFalse(suiteReport.syncAuthorizationPresent)
        assertFalse(suiteReport.signingBroadcastingAuthorizationPresent)
        assertFalse(suiteReport.uiAuthorizationPresent)
        assertFalse(suiteReport.endpointAuthorizationPresent)
        assertFalse(suiteReport.mainnetAuthorizationPresent)
    }

    @Test
    fun providerIdentityDescriptorSuiteReportOutputIsRedactedAndMaterialFree() {
        val suiteReport = suiteReport()
        val descriptor = SkaldVaultV1TestOnlyProviderIdentityDescriptorPolicy.currentProviderIdentityDescriptor()
        val descriptorValidation =
            SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationPolicy
                .currentProviderIdentityDescriptorValidation()
        val output = listOf(
            suiteReport.toString(),
            suiteReport.descriptorSuiteReportId.toString(),
            suiteReport.displayLabel.toString(),
            descriptorValidation.toString(),
            descriptor.toString(),
            descriptor.descriptorId.toString(),
            descriptor.providerIdentityLabel.toString(),
            descriptor.capabilityManifest.toString(),
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
            "wpkh(",
            "tr(",
            "xpub",
            "xprv",
            "tprv",
            "psbt",
        )

        assertContains(output, "REDACTED")
        assertContains(output, "COMMON_TEST_ONLY")
        assertContains(output, "PROVIDER_IDENTITY_DESCRIPTOR_SUITE_REPORT_ONLY")
        assertContains(output, "PAYLOAD_FREE")
        assertContains(output, "ALL_EXECUTABLE_CAPABILITIES_FALSE")
        assertContains(output, "NOT_AUTHORIZATION")
        assertContains(output, "DISABLED_PROVIDER_ONLY")
        forbiddenText.forEach { forbidden ->
            assertFalse(output.contains(forbidden, ignoreCase = true), "Output leaked forbidden text: $forbidden")
        }
        assertFalse(Regex("\\b[0-9a-fA-F]{64}\\b").containsMatchIn(output))
        assertFalse(Regex("\\b(?:bc1|tb1|bcrt1)[a-z0-9]{20,}\\b", RegexOption.IGNORE_CASE).containsMatchIn(output))
    }

    @Test
    fun providerIdentityDescriptorSuiteReportKeepsNormalSourceMaterialCorpusBoundaries() {
        val suiteReport = suiteReport()

        assertTrue(suiteReport.buildHistoryExcludedFromNormalSourceMaterialCorpus)
        assertTrue(suiteReport.localArtifactRootExcludedFromNormalSourceMaterialCorpus)
        assertTrue(suiteReport.docsReadmeUseDedicatedCorpus)
    }
}
