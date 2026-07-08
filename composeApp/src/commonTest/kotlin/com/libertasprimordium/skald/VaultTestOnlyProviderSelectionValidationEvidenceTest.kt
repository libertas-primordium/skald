package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyExecutableProviderSourceScope
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderSelectionValidationCheck
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderSelectionValidationKind
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderSelectionValidationPolicy
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionDecision
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class VaultTestOnlyProviderSelectionValidationEvidenceTest {
    private fun evidence() =
        SkaldVaultV1TestOnlyProviderSelectionValidationPolicy.currentProviderSelectionValidation()

    @Test
    fun providerSelectionValidationEvidenceExistsAndIsTestSourceOnly() {
        val evidence = evidence()

        assertEquals(1, evidence.validationVersion)
        assertEquals(
            SkaldVaultV1TestOnlyProviderSelectionValidationKind.TestOnlyProviderSelectionValidation,
            evidence.validationKind,
        )
        assertEquals("TEST_ONLY_PROVIDER_SELECTION_VALIDATION", evidence.validationKind.label)
        assertEquals(SkaldVaultV1TestOnlyExecutableProviderSourceScope.TestSourceOnly, evidence.sourceScope)
        assertEquals(0, evidence.failureLabels.size)
        assertEquals(0, evidence.blockerCount)
        assertEquals(0, evidence.warningCount)
        assertEquals(
            SkaldVaultV1TestOnlyProviderSelectionValidationCheck.entries.size,
            evidence.validationCheckCount,
        )
        SkaldVaultV1TestOnlyProviderSelectionValidationCheck.entries.forEach { check ->
            assertContains(evidence.validationChecks, check)
        }
    }

    @Test
    fun providerSelectionValidationReadsProviderLevelKatEvidenceAndRequiresExplicitScope() {
        val evidence = evidence()

        assertTrue(evidence.providerLevelPublicKatEvidencePresent)
        assertTrue(evidence.providerLevelPublicKatEvidencePassed)
        assertTrue(evidence.explicitValidationScopeRequired)
        assertTrue(evidence.testOnlyProviderSelectionValidationPassed)
        assertTrue(evidence.testOnlyProviderSelectionForValidationEnabled)
        assertTrue(evidence.testOnlyProviderSelectedForPublicKat)
        assertTrue(evidence.desktopTestOnlyProviderSelected)
        assertTrue(evidence.androidTestOnlyProviderSelected)
        assertTrue(evidence.selectedProviderKdfPublicKatPassed)
        assertTrue(evidence.selectedProviderAeadPublicKatPassed)
    }

    @Test
    fun selectedProviderKatSuccessDoesNotAuthorizeProductionSelection() {
        val evidence = evidence()
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertFalse(evidence.productionProviderSelectable)
        assertFalse(evidence.productionProviderSelectionEnabled)
        assertTrue(evidence.productionSelectionStillDisabledProviderOnly)
        assertFalse(evidence.productionProviderImplementationPresent)
        assertFalse(evidence.productionRegistryEntryPresent)
        assertFalse(evidence.productionFactoryPresent)
        assertFalse(evidence.productionDispatcherPresent)
        assertFalse(evidence.productionExecutorTargetPresent)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertEquals(VaultCryptoProviderSelectionDecision.DisabledProviderSelected, selection.decision)
        assertTrue(selection.selectedProviderIsDisabled)
        assertTrue(selection.selectedProvider is DisabledVaultCryptoProvider)
        assertFalse(selection.productionProviderSelectable)
    }

    @Test
    fun providerSelectionValidationPersistsNoChoiceAndAddsNoUiOrRuntimeSurface() {
        val evidence = evidence()

        assertFalse(evidence.providerChoicePersisted)
        assertFalse(evidence.providerSelectionUiPresent)
        assertFalse(evidence.tracePayloadPresent)
        assertFalse(evidence.providerHandlesExposed)
        assertFalse(evidence.vaultPersistencePresent)
        assertFalse(evidence.secureStorageSuccessPathPresent)
        assertFalse(evidence.secureMetadataSuccessPathPresent)
        assertFalse(evidence.productionSyncPresent)
        assertFalse(evidence.signingBroadcastingPresent)
        assertFalse(evidence.uiPresent)
        assertFalse(evidence.endpointPresent)
        assertFalse(evidence.mainnetPresent)
        assertTrue(evidence.futureProductionProviderSelectionRequiresSeparatePass)
        assertTrue(evidence.futureVaultPersistenceRequiresSeparatePass)
    }

    @Test
    fun providerSelectionValidationKeepsSourceMaterialCorpusBoundaries() {
        val evidence = evidence()

        assertTrue(evidence.buildHistoryExcludedFromNormalSourceMaterialCorpus)
        assertTrue(evidence.localArtifactRootExcludedFromNormalSourceMaterialCorpus)
    }

    @Test
    fun providerSelectionValidationOutputIsRedactedAndSafeLabelOnly() {
        val evidence = evidence()
        val output = listOf(
            evidence.toString(),
            evidence.validationId.toString(),
            evidence.displayLabel.toString(),
        ).joinToString(separator = " ")
        val forbiddenText = listOf(
            "ByteArray",
            "UByteArray",
            "CharArray",
            "passphrase",
            "mnemonic",
            "seed phrase",
            "private key",
            "xprv",
            "tprv",
            "WIF",
            "nsec",
            "ciphertext",
            "plaintext",
            "nonce",
            "tag",
            "provider handle",
            "source location",
            "stack trace",
            "diagnostics payload",
            "analytics payload",
            "crash-report payload",
            "support-export payload",
            "endpoint value",
            "filesystem path",
            "public vector bytes",
            "public vector hex",
            "wpkh(",
            "tr(",
            "xpub",
            "psbt",
        )

        assertContains(output, "REDACTED")
        assertContains(output, "TEST_SOURCE_ONLY")
        assertContains(output, "PROVIDER_SELECTION_VALIDATION_ONLY")
        assertContains(output, "PUBLIC_KAT_SCOPE_ONLY")
        assertContains(output, "NO_PRODUCTION_SELECTION")
        assertContains(output, "DISABLED_PROVIDER_ONLY")
        forbiddenText.forEach { forbidden ->
            assertFalse(output.contains(forbidden, ignoreCase = true), "Output leaked forbidden text: $forbidden")
        }
        assertFalse(Regex("\\b[0-9a-fA-F]{64}\\b").containsMatchIn(output))
        assertFalse(Regex("\\b(?:bc1|tb1|bcrt1)[a-z0-9]{20,}\\b", RegexOption.IGNORE_CASE).containsMatchIn(output))
    }
}
