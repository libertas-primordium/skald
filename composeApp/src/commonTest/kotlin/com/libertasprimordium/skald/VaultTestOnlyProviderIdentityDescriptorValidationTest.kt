package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDescriptorCapability
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDescriptorPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationCheck
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationKind
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationSourceSet
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

class VaultTestOnlyProviderIdentityDescriptorValidationTest {
    private fun validation() =
        SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationPolicy
            .currentProviderIdentityDescriptorValidation()

    @Test
    fun providerIdentityDescriptorValidationReportIsPresentAndCommonTestOnly() {
        val validation = validation()

        assertEquals(1, validation.descriptorValidationVersion)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationKind
                .InertTestOnlyProviderIdentityDescriptorValidation,
            validation.descriptorValidationKind,
        )
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationSourceSet.CommonTest,
            validation.sourceSet,
        )
        assertTrue(validation.descriptorValidationPassed)
        assertTrue(validation.descriptorValidationPassedIsCommonTestOnlyEvidence)
        assertTrue(validation.descriptorCommonTestOnly)
        assertEquals(0, validation.failureLabels.size)
        assertEquals(0, validation.blockerCount)
        assertEquals(0, validation.warningCount)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationCheck.entries.size,
            validation.validationCheckCount,
        )
    }

    @Test
    fun providerIdentityDescriptorValidationReadsTransitionMarkerChainAndDescriptorEvidence() {
        val validation = validation()
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

        assertTrue(validation.transitionGatePresent)
        assertTrue(validation.transitionGateHumanReviewReady)
        assertTrue(transitionGate.reviewReadyForHumanDecision)
        assertTrue(validation.markerPresent)
        assertTrue(validation.markerCreated)
        assertTrue(validation.implementationMarkerPresent)
        assertEquals(marker.markerCreated, validation.markerCreated)
        assertTrue(validation.markerValidationPresent)
        assertTrue(validation.markerValidationPassed)
        assertEquals(markerValidation.markerValidationPassed, validation.markerValidationPassed)
        assertTrue(validation.markerSuiteReportPresent)
        assertTrue(validation.markerSuiteReportPassed)
        assertEquals(markerSuite.markerSuiteReportPassed, validation.markerSuiteReportPassed)
        assertTrue(validation.markerCompletionAuditPresent)
        assertTrue(validation.markerCompletionAuditPassed)
        assertEquals(markerCompletionAudit.markerCompletionAuditPassed, validation.markerCompletionAuditPassed)
        assertTrue(validation.descriptorPresent)
        assertTrue(validation.descriptorCreated)
        assertEquals(descriptor.descriptorCreated, validation.descriptorCreated)
        assertTrue(validation.providerCapabilitiesDeclared)
        assertEquals(descriptor.providerCapabilitiesDeclared, validation.providerCapabilitiesDeclared)
    }

    @Test
    fun providerIdentityDescriptorValidationTreatsScopedTrueBooleansAsEvidenceOnly() {
        val validation = validation()

        assertTrue(validation.markerCreated)
        assertTrue(validation.implementationMarkerPresent)
        assertTrue(validation.markerValidationPassed)
        assertTrue(validation.markerSuiteReportPassed)
        assertTrue(validation.markerCompletionAuditPassed)
        assertTrue(validation.descriptorCreated)
        assertTrue(validation.providerCapabilitiesDeclared)
        assertTrue(validation.markerCreatedIsInertMarkerEvidenceOnly)
        assertTrue(validation.implementationMarkerPresentIsInertMarkerEvidenceOnly)
        assertTrue(validation.markerValidationPassedIsValidationEvidenceOnly)
        assertTrue(validation.markerSuiteReportPassedIsSuiteReportEvidenceOnly)
        assertTrue(validation.markerCompletionAuditPassedIsCompletionAuditEvidenceOnly)
        assertTrue(validation.descriptorCreatedIsDescriptorEvidenceOnly)
        assertTrue(validation.providerCapabilitiesDeclaredIsDescriptorEvidenceOnly)
        assertTrue(validation.userApprovalScopedToInertProviderIdentityChain)
        assertFalse(validation.implementationAuthorizationPresent)
        assertFalse(validation.productionAuthorizationPresent)
    }

    @Test
    fun providerIdentityDescriptorValidationUsesExactSafeSyntheticIdentityLabel() {
        val validation = validation()
        val descriptor = SkaldVaultV1TestOnlyProviderIdentityDescriptorPolicy.currentProviderIdentityDescriptor()
        val expected = "skald-test-only-provider-identity-v1-deterministic-kat-inert-marker"

        assertEquals(expected, descriptor.providerIdentityLabel.value)
        assertTrue(validation.markerIdentityLabelMatchesExpected)
        assertTrue(validation.markerIdentityLabelSafe)
        assertTrue(validation.descriptorSafeLabelOnly)
        assertTrue(validation.descriptorDeterministic)
        assertTrue(validation.descriptorPayloadFree)
        assertFalse(validation.toString().contains(expected))
    }

    @Test
    fun providerIdentityDescriptorValidationLabelIsDeterministicAndNotWalletMaterialLike() {
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
    fun providerIdentityDescriptorValidationConfirmsPayloadFreeSafeEvidenceShape() {
        val validation = validation()

        assertTrue(validation.descriptorPayloadFree)
        assertTrue(validation.descriptorSafeLabelOnly)
        assertTrue(validation.validationChecks.all { it in SkaldVaultV1TestOnlyProviderIdentityDescriptorValidationCheck.entries })
        assertTrue(validation.evidenceCount > 0)
        assertTrue(validation.validationCheckCount > 0)
        assertFalse(validation.tracePayloadPresent)
        assertFalse(validation.rawKatMaterialPresent)
        assertFalse(validation.publicVectorBytesPresent)
        assertFalse(validation.publicVectorHexPresent)
    }

    @Test
    fun providerIdentityDescriptorValidationConfirmsEveryExecutableCapabilityFalse() {
        val validation = validation()
        val descriptor = SkaldVaultV1TestOnlyProviderIdentityDescriptorPolicy.currentProviderIdentityDescriptor()
        val manifest = descriptor.capabilityManifest

        assertTrue(validation.descriptorCapabilityManifestPresent)
        assertTrue(validation.descriptorExecutableCapabilitiesAllFalse)
        assertTrue(validation.providerCapabilitiesDeclared)
        assertTrue(manifest.providerCapabilitiesDeclared)
        assertEquals(
            SkaldVaultV1TestOnlyProviderIdentityDescriptorCapability.entries.size,
            manifest.capabilityCount,
        )
        assertFalse(validation.providerExecutableCapabilitiesPresent)
        assertFalse(manifest.providerExecutableCapabilitiesPresent)
        assertFalse(validation.canDeriveKdf)
        assertFalse(validation.canEncrypt)
        assertFalse(validation.canDecrypt)
        assertFalse(validation.canGenerateKeys)
        assertFalse(validation.canWrapKeys)
        assertFalse(validation.canUnwrapKeys)
        assertFalse(validation.canRunProviderOperations)
        assertFalse(validation.canRunKat)
        assertFalse(validation.canPersistVault)
        assertFalse(validation.canAccessSecureStorage)
        assertFalse(validation.canAccessSecureMetadata)
        assertFalse(validation.canSelectInProduction)
        assertFalse(validation.canReachMainnet)
    }

    @Test
    fun providerIdentityDescriptorValidationConfirmsNoVaultCryptoProviderImplementationInstanceOrHandle() {
        val validation = validation()

        assertTrue(validation.descriptorInert)
        assertFalse(validation.implementsVaultCryptoProvider)
        assertFalse(validation.containsVaultCryptoProvider)
        assertFalse(validation.vaultCryptoProviderInstanceExposed)
        assertFalse(validation.providerHandlePresent)
        assertFalse(validation.providerImplementationPresent)
        assertFalse(validation.productionProviderIdentityPresent)
    }

    @Test
    fun providerIdentityDescriptorValidationConfirmsNoRegistryFactoryDispatcherExecutorOrExecution() {
        val validation = validation()

        assertFalse(validation.providerRegistryEntryPresent)
        assertFalse(validation.providerFactoryPresent)
        assertFalse(validation.providerDispatcherPresent)
        assertFalse(validation.executorTargetPresent)
        assertFalse(validation.providerOperationExecuted)
        assertFalse(validation.cryptoExecuted)
        assertFalse(validation.providerKatExecutorPresent)
    }

    @Test
    fun providerIdentityDescriptorValidationConfirmsNoKatRunnerExecutorRawMaterialPublicVectorsOrTracePayload() {
        val validation = validation()

        assertFalse(validation.katRunnerPresent)
        assertFalse(validation.katExecutorPresent)
        assertFalse(validation.rawKatMaterialPresent)
        assertFalse(validation.publicVectorBytesPresent)
        assertFalse(validation.publicVectorHexPresent)
        assertFalse(validation.tracePayloadPresent)
    }

    @Test
    fun providerIdentityDescriptorValidationConfirmsNoVaultStorageSyncSigningUiEndpointOrMainnetPath() {
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
    fun providerIdentityDescriptorValidationKeepsProductionProviderUnselectableAndDisabledProviderOnly() {
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
    fun providerIdentityDescriptorValidationPassedIsNotAnyAuthorization() {
        val validation = validation()

        assertTrue(validation.descriptorValidationPassed)
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
    fun providerIdentityDescriptorValidationOutputIsRedactedAndMaterialFree() {
        val validation = validation()
        val descriptor = SkaldVaultV1TestOnlyProviderIdentityDescriptorPolicy.currentProviderIdentityDescriptor()
        val output = listOf(
            validation.toString(),
            validation.descriptorValidationId.toString(),
            validation.displayLabel.toString(),
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
        assertContains(output, "PROVIDER_IDENTITY_DESCRIPTOR_VALIDATION_ONLY")
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
    fun providerIdentityDescriptorValidationKeepsNormalSourceMaterialCorpusBoundaries() {
        val validation = validation()

        assertTrue(validation.buildHistoryExcludedFromNormalSourceMaterialCorpus)
        assertTrue(validation.localArtifactRootExcludedFromNormalSourceMaterialCorpus)
        assertTrue(validation.docsReadmeUseDedicatedCorpus)
    }
}
