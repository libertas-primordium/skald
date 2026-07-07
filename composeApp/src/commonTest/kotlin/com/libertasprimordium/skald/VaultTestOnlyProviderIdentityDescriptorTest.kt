package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDescriptorCapability
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDescriptorCheck
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDescriptorKind
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDescriptorPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDescriptorSourceSet
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

class VaultTestOnlyProviderIdentityDescriptorTest {
    private fun descriptor() =
        SkaldVaultV1TestOnlyProviderIdentityDescriptorPolicy.currentProviderIdentityDescriptor()

    @Test
    fun providerIdentityDescriptorIsPresentAndCommonTestOnly() {
        val descriptor = descriptor()

        assertEquals(1, descriptor.descriptorVersion)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityDescriptorKind.InertTestOnlyProviderIdentityDescriptor,
            descriptor.descriptorKind,
        )
        assertEquals(SkaldVaultV1TestOnlyProviderIdentityDescriptorSourceSet.CommonTest, descriptor.sourceSet)
        assertTrue(descriptor.descriptorCreated)
        assertTrue(descriptor.descriptorCreatedIsCommonTestOnlyEvidence)
        assertTrue(descriptor.descriptorCommonTestOnly)
        assertEquals(0, descriptor.failureLabels.size)
        assertEquals(0, descriptor.blockerCount)
        assertEquals(0, descriptor.warningCount)
        assertEquals(SkaldVaultV1TestOnlyProviderIdentityDescriptorCheck.entries.size, descriptor.descriptorCheckCount)
    }

    @Test
    fun providerIdentityDescriptorReadsTransitionMarkerValidationSuiteAndCompletionAuditEvidence() {
        val descriptor = descriptor()
        val transitionGate =
            SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGatePolicy
                .currentProviderIdentityImplementationTransitionGate()
        val marker = SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy.currentImplementationMarker()
        val validation =
            SkaldVaultV1TestOnlyProviderIdentityMarkerValidationPolicy.currentProviderIdentityMarkerValidation()
        val suite =
            SkaldVaultV1TestOnlyProviderIdentityMarkerSuiteReportPolicy.currentProviderIdentityMarkerSuiteReport()
        val completionAudit =
            SkaldVaultV1TestOnlyProviderIdentityMarkerCompletionAuditPolicy
                .currentProviderIdentityMarkerCompletionAudit()

        assertTrue(descriptor.transitionGatePresent)
        assertTrue(descriptor.transitionGateHumanReviewReady)
        assertTrue(transitionGate.reviewReadyForHumanDecision)
        assertTrue(descriptor.markerPresent)
        assertTrue(descriptor.markerCreated)
        assertTrue(descriptor.implementationMarkerPresent)
        assertEquals(marker.markerCreated, descriptor.markerCreated)
        assertTrue(descriptor.markerValidationPresent)
        assertTrue(descriptor.markerValidationPassed)
        assertEquals(validation.markerValidationPassed, descriptor.markerValidationPassed)
        assertTrue(descriptor.markerSuiteReportPresent)
        assertTrue(descriptor.markerSuiteReportPassed)
        assertEquals(suite.markerSuiteReportPassed, descriptor.markerSuiteReportPassed)
        assertTrue(descriptor.markerCompletionAuditPresent)
        assertTrue(descriptor.markerCompletionAuditPassed)
        assertEquals(completionAudit.markerCompletionAuditPassed, descriptor.markerCompletionAuditPassed)
    }

    @Test
    fun providerIdentityDescriptorTreatsUpstreamTrueBooleansAsEvidenceOnly() {
        val descriptor = descriptor()

        assertTrue(descriptor.markerCreated)
        assertTrue(descriptor.implementationMarkerPresent)
        assertTrue(descriptor.markerValidationPassed)
        assertTrue(descriptor.markerSuiteReportPassed)
        assertTrue(descriptor.markerCompletionAuditPassed)
        assertTrue(descriptor.markerCreatedIsInertMarkerEvidenceOnly)
        assertTrue(descriptor.implementationMarkerPresentIsInertMarkerEvidenceOnly)
        assertTrue(descriptor.markerValidationPassedIsValidationEvidenceOnly)
        assertTrue(descriptor.markerSuiteReportPassedIsSuiteReportEvidenceOnly)
        assertTrue(descriptor.markerCompletionAuditPassedIsCompletionAuditEvidenceOnly)
        assertTrue(descriptor.userApprovalScopedToInertProviderIdentityChain)
        assertFalse(descriptor.implementationAuthorizationPresent)
        assertFalse(descriptor.productionAuthorizationPresent)
    }

    @Test
    fun providerIdentityDescriptorUsesExactSafeSyntheticIdentityLabel() {
        val descriptor = descriptor()
        val marker = SkaldVaultV1TestOnlyProviderIdentityMarkerPolicy.currentImplementationMarker()
        val expected = "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"

        assertEquals("skald-test-only-provider-identity-v1", descriptor.namespaceLabel.value)
        assertEquals(expected, descriptor.providerIdentityLabel.value)
        assertEquals(expected, marker.markerId.value)
        assertEquals(expected, marker.safeId.value)
        assertEquals(expected, marker.syntheticIdentityLabel.value)
        assertTrue(descriptor.descriptorSafeLabelOnly)
        assertTrue(descriptor.descriptorDeterministic)
        assertTrue(descriptor.descriptorPayloadFree)
        assertFalse(descriptor.toString().contains(expected))
    }

    @Test
    fun providerIdentityDescriptorLabelIsDeterministicAndNotWalletMaterialLike() {
        val descriptor = descriptor()
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
    fun providerIdentityDescriptorDeclaresOnlyInertFalseExecutableCapabilities() {
        val descriptor = descriptor()
        val manifest = descriptor.capabilityManifest

        assertTrue(descriptor.providerCapabilitiesDeclared)
        assertTrue(manifest.providerCapabilitiesDeclared)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityDescriptorCapability.entries.size,
            manifest.capabilityCount,
        )
        assertFalse(descriptor.providerExecutableCapabilitiesPresent)
        assertFalse(manifest.providerExecutableCapabilitiesPresent)
        assertFalse(descriptor.canDeriveKdf)
        assertFalse(descriptor.canEncrypt)
        assertFalse(descriptor.canDecrypt)
        assertFalse(descriptor.canGenerateKeys)
        assertFalse(descriptor.canWrapKeys)
        assertFalse(descriptor.canUnwrapKeys)
        assertFalse(descriptor.canRunProviderOperations)
        assertFalse(descriptor.canRunKat)
        assertFalse(descriptor.canPersistVault)
        assertFalse(descriptor.canAccessSecureStorage)
        assertFalse(descriptor.canAccessSecureMetadata)
        assertFalse(descriptor.canSelectInProduction)
        assertFalse(descriptor.canReachMainnet)
    }

    @Test
    fun providerIdentityDescriptorDoesNotImplementExposeOrHandleVaultCryptoProvider() {
        val descriptor = descriptor()

        assertTrue(descriptor.descriptorInert)
        assertFalse(descriptor.implementsVaultCryptoProvider)
        assertFalse(descriptor.containsVaultCryptoProvider)
        assertFalse(descriptor.vaultCryptoProviderInstanceExposed)
        assertFalse(descriptor.providerHandlePresent)
        assertFalse(descriptor.providerImplementationPresent)
        assertFalse(descriptor.productionProviderIdentityPresent)
    }

    @Test
    fun providerIdentityDescriptorAddsNoRegistryFactoryDispatcherExecutorOrExecution() {
        val descriptor = descriptor()

        assertFalse(descriptor.providerRegistryEntryPresent)
        assertFalse(descriptor.providerFactoryPresent)
        assertFalse(descriptor.providerDispatcherPresent)
        assertFalse(descriptor.executorTargetPresent)
        assertFalse(descriptor.providerOperationExecuted)
        assertFalse(descriptor.cryptoExecuted)
        assertFalse(descriptor.providerKatExecutorPresent)
    }

    @Test
    fun providerIdentityDescriptorAddsNoKatRunnerExecutorRawMaterialPublicVectorsOrTracePayload() {
        val descriptor = descriptor()

        assertFalse(descriptor.katRunnerPresent)
        assertFalse(descriptor.katExecutorPresent)
        assertFalse(descriptor.rawKatMaterialPresent)
        assertFalse(descriptor.publicVectorBytesPresent)
        assertFalse(descriptor.publicVectorHexPresent)
        assertFalse(descriptor.tracePayloadPresent)
    }

    @Test
    fun providerIdentityDescriptorAddsNoVaultStorageSyncSigningUiEndpointOrMainnetPath() {
        val descriptor = descriptor()

        assertFalse(descriptor.vaultLifecyclePresent)
        assertFalse(descriptor.vaultPersistencePresent)
        assertFalse(descriptor.secureSecretStorageSuccessPresent)
        assertFalse(descriptor.secureMetadataStorageSuccessPresent)
        assertFalse(descriptor.productionSyncPresent)
        assertFalse(descriptor.signingBroadcastingPresent)
        assertFalse(descriptor.uiPresent)
        assertFalse(descriptor.endpointPresent)
        assertFalse(descriptor.mainnetPresent)
    }

    @Test
    fun providerIdentityDescriptorKeepsProductionProviderUnselectableAndDisabledProviderOnly() {
        val descriptor = descriptor()
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertFalse(descriptor.productionProviderSelectable)
        assertTrue(descriptor.disabledProviderOnly)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertTrue(selection.selectedProviderIsDisabled)
        assertTrue(selection.selectedProvider is DisabledVaultCryptoProvider)
        assertEquals(VaultCryptoProviderSelectionDecision.DisabledProviderSelected, selection.decision)
        assertFalse(selection.productionProviderSelectable)
    }

    @Test
    fun providerIdentityDescriptorCreatedIsNotAnyAuthorization() {
        val descriptor = descriptor()

        assertTrue(descriptor.descriptorCreated)
        assertFalse(descriptor.implementationAuthorizationPresent)
        assertFalse(descriptor.productionAuthorizationPresent)
        assertFalse(descriptor.providerSelectionAuthorizationPresent)
        assertFalse(descriptor.providerOperationAuthorizationPresent)
        assertFalse(descriptor.cryptoAuthorizationPresent)
        assertFalse(descriptor.katRunnerAuthorizationPresent)
        assertFalse(descriptor.katExecutorAuthorizationPresent)
        assertFalse(descriptor.providerKatExecutorAuthorizationPresent)
        assertFalse(descriptor.vaultPersistenceAuthorizationPresent)
        assertFalse(descriptor.syncAuthorizationPresent)
        assertFalse(descriptor.signingBroadcastingAuthorizationPresent)
        assertFalse(descriptor.uiAuthorizationPresent)
        assertFalse(descriptor.endpointAuthorizationPresent)
        assertFalse(descriptor.mainnetAuthorizationPresent)
    }

    @Test
    fun providerIdentityDescriptorOutputIsRedactedAndMaterialFree() {
        val descriptor = descriptor()
        val output = listOf(
            descriptor.toString(),
            descriptor.descriptorId.toString(),
            descriptor.namespaceLabel.toString(),
            descriptor.providerIdentityLabel.toString(),
            descriptor.capabilityManifest.toString(),
            descriptor.displayLabel.toString(),
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
        assertContains(output, "PROVIDER_IDENTITY_DESCRIPTOR_ONLY")
        assertContains(output, "PAYLOAD_FREE")
        assertContains(output, "NOT_AUTHORIZATION")
        assertContains(output, "DISABLED_PROVIDER_ONLY")
        forbiddenText.forEach { forbidden ->
            assertFalse(output.contains(forbidden, ignoreCase = true), "Output leaked forbidden text: $forbidden")
        }
        assertFalse(Regex("\\b[0-9a-fA-F]{64}\\b").containsMatchIn(output))
        assertFalse(Regex("\\b(?:bc1|tb1|bcrt1)[a-z0-9]{20,}\\b", RegexOption.IGNORE_CASE).containsMatchIn(output))
    }

    @Test
    fun providerIdentityDescriptorKeepsNormalSourceMaterialCorpusBoundaries() {
        val descriptor = descriptor()

        assertTrue(descriptor.buildHistoryExcludedFromNormalSourceMaterialCorpus)
        assertTrue(descriptor.localArtifactRootExcludedFromNormalSourceMaterialCorpus)
        assertTrue(descriptor.docsReadmeUseDedicatedCorpus)
    }
}
