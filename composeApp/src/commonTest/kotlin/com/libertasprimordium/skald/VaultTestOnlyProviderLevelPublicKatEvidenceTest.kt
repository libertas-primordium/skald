package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderLevelPublicKatEvidenceCheck
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderLevelPublicKatEvidencePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderLevelPublicKatExecutionKind
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyExecutableProviderKind
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyExecutableProviderSourceScope
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionDecision
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class VaultTestOnlyProviderLevelPublicKatEvidenceTest {
    private fun evidence() =
        SkaldVaultV1ProviderLevelPublicKatEvidencePolicy.currentProviderLevelPublicKatEvidence()

    @Test
    fun providerLevelPublicKatEvidenceIsPresentAndTestSourceOnly() {
        val evidence = evidence()

        assertEquals(1, evidence.providerLevelKatExecutionVersion)
        assertEquals(
            SkaldVaultV1TestOnlyExecutableProviderKind.TestOnlyExecutablePublicKatProvider,
            evidence.providerKind,
        )
        assertEquals(SkaldVaultV1TestOnlyExecutableProviderSourceScope.TestSourceOnly, evidence.sourceScope)
        assertEquals(0, evidence.failureLabels.size)
        assertEquals(0, evidence.blockerCount)
        assertEquals(0, evidence.warningCount)
        assertEquals(SkaldVaultV1ProviderLevelPublicKatEvidenceCheck.entries.size, evidence.evidenceChecks.size)
        SkaldVaultV1ProviderLevelPublicKatEvidenceCheck.entries.forEach { check ->
            assertContains(evidence.evidenceChecks, check)
        }
        assertEquals(
            "TEST_ONLY_PROVIDER_LEVEL_PUBLIC_KAT_EXECUTION",
            SkaldVaultV1ProviderLevelPublicKatExecutionKind.TestOnlyProviderLevelPublicKatExecution.label,
        )
    }

    @Test
    fun providerLevelPublicKatEvidenceRecordsDesktopAndAndroidKdfAeadSuccess() {
        val evidence = evidence()

        assertTrue(evidence.desktopProviderLevelKatExecuted)
        assertTrue(evidence.androidProviderLevelKatExecuted)
        assertTrue(evidence.kdfProviderKatExecuted)
        assertTrue(evidence.kdfProviderKatPassed)
        assertTrue(evidence.aeadProviderKatExecuted)
        assertTrue(evidence.aeadProviderKatPassed)
        assertTrue(evidence.publicKatScopeOnly)
        assertTrue(evidence.testOnlyProviderLevelKatExecutionPassed)
        assertTrue(evidence.futureProviderSelectionEnablementRequiresSeparatePass)
    }

    @Test
    fun providerLevelPublicKatEvidenceDoesNotAuthorizeSelectionOrProductionProvider() {
        val evidence = evidence()
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertFalse(evidence.providerSelectionEnabled)
        assertFalse(evidence.productionProviderSelectable)
        assertFalse(evidence.productionProviderImplementationPresent)
        assertFalse(evidence.productionProviderRegistryEntryPresent)
        assertFalse(evidence.productionProviderFactoryPresent)
        assertFalse(evidence.productionProviderDispatcherPresent)
        assertFalse(evidence.productionExecutorTargetPresent)
        assertFalse(evidence.providerSelectionAuthorizationPresent)
        assertFalse(evidence.productionAuthorizationPresent)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertEquals(VaultCryptoProviderSelectionDecision.DisabledProviderSelected, selection.decision)
        assertTrue(selection.selectedProviderIsDisabled)
        assertTrue(selection.selectedProvider is DisabledVaultCryptoProvider)
        assertFalse(selection.productionProviderSelectable)
    }

    @Test
    fun providerLevelPublicKatEvidenceAddsNoStorageSyncSigningUiEndpointOrMainnet() {
        val evidence = evidence()

        assertFalse(evidence.vaultPersistencePresent)
        assertFalse(evidence.secureStorageSuccessPathPresent)
        assertFalse(evidence.secureMetadataSuccessPathPresent)
        assertFalse(evidence.productionSyncPresent)
        assertFalse(evidence.signingBroadcastingPresent)
        assertFalse(evidence.uiPresent)
        assertFalse(evidence.endpointPresent)
        assertFalse(evidence.mainnetPresent)
    }

    @Test
    fun providerLevelPublicKatEvidenceKeepsSourceMaterialCorpusBoundaries() {
        val evidence = evidence()

        assertTrue(evidence.buildHistoryExcludedFromNormalSourceMaterialCorpus)
        assertTrue(evidence.localArtifactRootExcludedFromNormalSourceMaterialCorpus)
    }

    @Test
    fun providerLevelPublicKatEvidenceOutputIsRedactedAndMaterialFree() {
        val evidence = evidence()
        val output = listOf(
            evidence.toString(),
            evidence.providerLevelKatEvidenceId.toString(),
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
        assertContains(output, "PROVIDER_LEVEL_PUBLIC_KAT_EXECUTION_ONLY")
        assertContains(output, "PUBLIC_VECTORS_ONLY")
        assertContains(output, "NO_PROVIDER_SELECTION")
        assertContains(output, "NO_PRODUCTION_PROVIDER")
        forbiddenText.forEach { forbidden ->
            assertFalse(output.contains(forbidden, ignoreCase = true), "Output leaked forbidden text: $forbidden")
        }
        assertFalse(Regex("\\b[0-9a-fA-F]{64}\\b").containsMatchIn(output))
        assertFalse(Regex("\\b(?:bc1|tb1|bcrt1)[a-z0-9]{20,}\\b", RegexOption.IGNORE_CASE).containsMatchIn(output))
    }
}
