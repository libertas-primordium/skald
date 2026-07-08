package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDescriptorCapability
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditCheck
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditKind
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditSourceSet
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDescriptorPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportPolicy
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

class VaultTestOnlyProviderIdentityDescriptorCompletionAuditTest {
    private fun completionAudit() =
        SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditPolicy
            .currentProviderIdentityDescriptorCompletionAudit()

    @Test
    fun providerIdentityDescriptorCompletionAuditIsPresentAndCommonTestOnly() {
        val completionAudit = completionAudit()

        assertEquals(1, completionAudit.descriptorCompletionAuditVersion)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditKind
                .InertTestOnlyProviderIdentityDescriptorCompletionAudit,
            completionAudit.descriptorCompletionAuditKind,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditSourceSet.CommonTest,
            completionAudit.sourceSet,
        )
        assertTrue(completionAudit.descriptorCompletionAuditPassed)
        assertTrue(completionAudit.descriptorCompletionAuditPassedIsCommonTestOnlyEvidence)
        assertTrue(completionAudit.descriptorCommonTestOnly)
        assertTrue(completionAudit.descriptorValidationCommonTestOnly)
        assertTrue(completionAudit.descriptorSuiteReportCommonTestOnly)
        assertEquals(0, completionAudit.failureLabels.size)
        assertEquals(0, completionAudit.blockerCount)
        assertEquals(0, completionAudit.warningCount)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditCheck.entries.size,
            completionAudit.completionCheckCount,
        )
    }

    @Test
    fun providerIdentityDescriptorCompletionAuditReadsTransitionMarkerDescriptorValidationAndSuiteEvidence() {
        val completionAudit = completionAudit()
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
        val descriptorSuiteReport =
            SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportPolicy
                .currentProviderIdentityDescriptorSuiteReport()

        assertTrue(completionAudit.transitionGatePresent)
        assertTrue(completionAudit.transitionGateHumanReviewReady)
        assertTrue(transitionGate.reviewReadyForHumanDecision)
        assertTrue(completionAudit.markerPresent)
        assertTrue(completionAudit.markerCreated)
        assertTrue(completionAudit.implementationMarkerPresent)
        assertEquals(marker.markerCreated, completionAudit.markerCreated)
        assertTrue(completionAudit.markerValidationPresent)
        assertTrue(completionAudit.markerValidationPassed)
        assertEquals(markerValidation.markerValidationPassed, completionAudit.markerValidationPassed)
        assertTrue(completionAudit.markerSuiteReportPresent)
        assertTrue(completionAudit.markerSuiteReportPassed)
        assertEquals(markerSuite.markerSuiteReportPassed, completionAudit.markerSuiteReportPassed)
        assertTrue(completionAudit.markerCompletionAuditPresent)
        assertTrue(completionAudit.markerCompletionAuditPassed)
        assertEquals(markerCompletionAudit.markerCompletionAuditPassed, completionAudit.markerCompletionAuditPassed)
        assertTrue(completionAudit.descriptorPresent)
        assertTrue(completionAudit.descriptorCreated)
        assertEquals(descriptor.descriptorCreated, completionAudit.descriptorCreated)
        assertTrue(completionAudit.providerCapabilitiesDeclared)
        assertEquals(descriptor.providerCapabilitiesDeclared, completionAudit.providerCapabilitiesDeclared)
        assertTrue(completionAudit.descriptorValidationPresent)
        assertTrue(completionAudit.descriptorValidationPassed)
        assertEquals(descriptorValidation.descriptorValidationPassed, completionAudit.descriptorValidationPassed)
        assertTrue(completionAudit.descriptorSuiteReportPresent)
        assertTrue(completionAudit.descriptorSuiteReportPassed)
        assertEquals(descriptorSuiteReport.descriptorSuiteReportPassed, completionAudit.descriptorSuiteReportPassed)
        assertTrue(completionAudit.descriptorChainComplete)
    }

    @Test
    fun providerIdentityDescriptorCompletionAuditTreatsScopedTrueBooleansAsEvidenceOnly() {
        val completionAudit = completionAudit()

        assertTrue(completionAudit.markerCreated)
        assertTrue(completionAudit.implementationMarkerPresent)
        assertTrue(completionAudit.markerValidationPassed)
        assertTrue(completionAudit.markerSuiteReportPassed)
        assertTrue(completionAudit.markerCompletionAuditPassed)
        assertTrue(completionAudit.descriptorCreated)
        assertTrue(completionAudit.providerCapabilitiesDeclared)
        assertTrue(completionAudit.descriptorValidationPassed)
        assertTrue(completionAudit.descriptorSuiteReportPassed)
        assertTrue(completionAudit.descriptorCompletionAuditPassed)
        assertTrue(completionAudit.markerCreatedIsInertMarkerEvidenceOnly)
        assertTrue(completionAudit.implementationMarkerPresentIsInertMarkerEvidenceOnly)
        assertTrue(completionAudit.markerValidationPassedIsValidationEvidenceOnly)
        assertTrue(completionAudit.markerSuiteReportPassedIsSuiteReportEvidenceOnly)
        assertTrue(completionAudit.markerCompletionAuditPassedIsCompletionAuditEvidenceOnly)
        assertTrue(completionAudit.descriptorCreatedIsDescriptorEvidenceOnly)
        assertTrue(completionAudit.providerCapabilitiesDeclaredIsDescriptorEvidenceOnly)
        assertTrue(completionAudit.descriptorValidationPassedIsValidationEvidenceOnly)
        assertTrue(completionAudit.descriptorSuiteReportPassedIsSuiteReportEvidenceOnly)
        assertTrue(completionAudit.userApprovalScopedToInertProviderIdentityChain)
        assertFalse(completionAudit.implementationAuthorizationPresent)
        assertFalse(completionAudit.productionAuthorizationPresent)
    }

    @Test
    fun providerIdentityDescriptorCompletionAuditUsesExactSafeSyntheticIdentityLabel() {
        val completionAudit = completionAudit()
        val descriptor = SkaldVaultV1TestOnlyProviderIdentityDescriptorPolicy.currentProviderIdentityDescriptor()
        val expected = "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"

        assertEquals(expected, descriptor.providerIdentityLabel.value)
        assertTrue(completionAudit.markerIdentityLabelMatchesExpected)
        assertTrue(completionAudit.markerIdentityLabelSafe)
        assertTrue(completionAudit.descriptorSafeLabelOnly)
        assertTrue(completionAudit.descriptorDeterministic)
        assertTrue(completionAudit.descriptorPayloadFree)
        assertFalse(completionAudit.toString().contains(expected))
    }

    @Test
    fun providerIdentityDescriptorCompletionAuditLabelIsDeterministicAndNotWalletMaterialLike() {
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
    fun providerIdentityDescriptorCompletionAuditConfirmsPayloadFreeSafeEvidenceShape() {
        val completionAudit = completionAudit()

        assertTrue(completionAudit.descriptorPayloadFree)
        assertTrue(completionAudit.descriptorSafeLabelOnly)
        assertTrue(
            completionAudit.completionChecks.all {
                it in SkaldVaultV1TestOnlyProviderIdentityDescriptorCompletionAuditCheck.entries
            },
        )
        assertTrue(completionAudit.evidenceCount > 0)
        assertTrue(completionAudit.completionCheckCount > 0)
        assertFalse(completionAudit.tracePayloadPresent)
        assertFalse(completionAudit.rawKatMaterialPresent)
        assertFalse(completionAudit.publicVectorBytesPresent)
        assertFalse(completionAudit.publicVectorHexPresent)
    }

    @Test
    fun providerIdentityDescriptorCompletionAuditConfirmsEveryExecutableCapabilityFalse() {
        val completionAudit = completionAudit()
        val descriptor = SkaldVaultV1TestOnlyProviderIdentityDescriptorPolicy.currentProviderIdentityDescriptor()
        val manifest = descriptor.capabilityManifest

        assertTrue(completionAudit.descriptorCapabilityManifestPresent)
        assertTrue(completionAudit.descriptorExecutableCapabilitiesAllFalse)
        assertTrue(completionAudit.providerCapabilitiesDeclared)
        assertTrue(manifest.providerCapabilitiesDeclared)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityDescriptorCapability.entries.size,
            manifest.capabilityCount,
        )
        assertFalse(completionAudit.providerExecutableCapabilitiesPresent)
        assertFalse(manifest.providerExecutableCapabilitiesPresent)
        assertFalse(completionAudit.canDeriveKdf)
        assertFalse(completionAudit.canEncrypt)
        assertFalse(completionAudit.canDecrypt)
        assertFalse(completionAudit.canGenerateKeys)
        assertFalse(completionAudit.canWrapKeys)
        assertFalse(completionAudit.canUnwrapKeys)
        assertFalse(completionAudit.canRunProviderOperations)
        assertFalse(completionAudit.canRunKat)
        assertFalse(completionAudit.canPersistVault)
        assertFalse(completionAudit.canAccessSecureStorage)
        assertFalse(completionAudit.canAccessSecureMetadata)
        assertFalse(completionAudit.canSelectInProduction)
        assertFalse(completionAudit.canReachMainnet)
    }

    @Test
    fun providerIdentityDescriptorCompletionAuditConfirmsNoVaultCryptoProviderImplementationInstanceOrHandle() {
        val completionAudit = completionAudit()

        assertTrue(completionAudit.descriptorInert)
        assertFalse(completionAudit.implementsVaultCryptoProvider)
        assertFalse(completionAudit.containsVaultCryptoProvider)
        assertFalse(completionAudit.vaultCryptoProviderInstanceExposed)
        assertFalse(completionAudit.providerHandlePresent)
        assertFalse(completionAudit.providerImplementationPresent)
        assertFalse(completionAudit.productionProviderIdentityPresent)
    }

    @Test
    fun providerIdentityDescriptorCompletionAuditConfirmsNoRegistryFactoryDispatcherExecutorOrExecution() {
        val completionAudit = completionAudit()

        assertFalse(completionAudit.providerRegistryEntryPresent)
        assertFalse(completionAudit.providerFactoryPresent)
        assertFalse(completionAudit.providerDispatcherPresent)
        assertFalse(completionAudit.executorTargetPresent)
        assertFalse(completionAudit.providerOperationExecuted)
        assertFalse(completionAudit.cryptoExecuted)
        assertFalse(completionAudit.providerKatExecutorPresent)
    }

    @Test
    fun providerIdentityDescriptorCompletionAuditConfirmsNoKatRunnerExecutorRawMaterialPublicVectorsOrTracePayload() {
        val completionAudit = completionAudit()

        assertFalse(completionAudit.katRunnerPresent)
        assertFalse(completionAudit.katExecutorPresent)
        assertFalse(completionAudit.rawKatMaterialPresent)
        assertFalse(completionAudit.publicVectorBytesPresent)
        assertFalse(completionAudit.publicVectorHexPresent)
        assertFalse(completionAudit.tracePayloadPresent)
    }

    @Test
    fun providerIdentityDescriptorCompletionAuditConfirmsNoVaultStorageSyncSigningUiEndpointOrMainnetPath() {
        val completionAudit = completionAudit()

        assertFalse(completionAudit.vaultLifecyclePresent)
        assertFalse(completionAudit.vaultPersistencePresent)
        assertFalse(completionAudit.secureSecretStorageSuccessPresent)
        assertFalse(completionAudit.secureMetadataStorageSuccessPresent)
        assertFalse(completionAudit.productionSyncPresent)
        assertFalse(completionAudit.signingBroadcastingPresent)
        assertFalse(completionAudit.uiPresent)
        assertFalse(completionAudit.endpointPresent)
        assertFalse(completionAudit.mainnetPresent)
    }

    @Test
    fun providerIdentityDescriptorCompletionAuditKeepsProductionProviderUnselectableAndDisabledProviderOnly() {
        val completionAudit = completionAudit()
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertFalse(completionAudit.productionProviderSelectable)
        assertTrue(completionAudit.disabledProviderOnly)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertTrue(selection.selectedProviderIsDisabled)
        assertTrue(selection.selectedProvider is DisabledVaultCryptoProvider)
        assertEquals(VaultCryptoProviderSelectionDecision.DisabledProviderSelected, selection.decision)
        assertFalse(selection.productionProviderSelectable)
    }

    @Test
    fun providerIdentityDescriptorCompletionAuditPassedIsNotAnyAuthorization() {
        val completionAudit = completionAudit()

        assertTrue(completionAudit.descriptorCompletionAuditPassed)
        assertFalse(completionAudit.implementationAuthorizationPresent)
        assertFalse(completionAudit.productionAuthorizationPresent)
        assertFalse(completionAudit.providerSelectionAuthorizationPresent)
        assertFalse(completionAudit.providerOperationAuthorizationPresent)
        assertFalse(completionAudit.cryptoAuthorizationPresent)
        assertFalse(completionAudit.katRunnerAuthorizationPresent)
        assertFalse(completionAudit.katExecutorAuthorizationPresent)
        assertFalse(completionAudit.providerKatExecutorAuthorizationPresent)
        assertFalse(completionAudit.vaultPersistenceAuthorizationPresent)
        assertFalse(completionAudit.syncAuthorizationPresent)
        assertFalse(completionAudit.signingBroadcastingAuthorizationPresent)
        assertFalse(completionAudit.uiAuthorizationPresent)
        assertFalse(completionAudit.endpointAuthorizationPresent)
        assertFalse(completionAudit.mainnetAuthorizationPresent)
    }

    @Test
    fun providerIdentityDescriptorCompletionAuditOutputIsRedactedAndMaterialFree() {
        val completionAudit = completionAudit()
        val descriptor = SkaldVaultV1TestOnlyProviderIdentityDescriptorPolicy.currentProviderIdentityDescriptor()
        val descriptorValidation =
            SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationPolicy
                .currentProviderIdentityDescriptorValidation()
        val descriptorSuiteReport =
            SkaldVaultV1TestOnlyProviderIdentityDescriptorSuiteReportPolicy
                .currentProviderIdentityDescriptorSuiteReport()
        val output = listOf(
            completionAudit.toString(),
            completionAudit.descriptorCompletionAuditId.toString(),
            completionAudit.displayLabel.toString(),
            descriptorSuiteReport.toString(),
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
        assertContains(output, "PROVIDER_IDENTITY_DESCRIPTOR_COMPLETION_AUDIT_ONLY")
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
    fun providerIdentityDescriptorCompletionAuditKeepsNormalSourceMaterialCorpusBoundaries() {
        val completionAudit = completionAudit()

        assertTrue(completionAudit.buildHistoryExcludedFromNormalSourceMaterialCorpus)
        assertTrue(completionAudit.localArtifactRootExcludedFromNormalSourceMaterialCorpus)
        assertTrue(completionAudit.docsReadmeUseDedicatedCorpus)
    }
}
