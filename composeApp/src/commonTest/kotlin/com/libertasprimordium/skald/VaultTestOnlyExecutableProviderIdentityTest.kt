package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyExecutableProviderIdentityCheck
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyExecutableProviderIdentityPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyExecutableProviderKind
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyExecutableProviderSourceScope
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityDescriptorPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionPolicy
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionDecision
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class VaultTestOnlyExecutableProviderIdentityTest {
    private fun identity() =
        SkaldVaultV1TestOnlyExecutableProviderIdentityPolicy.currentTestOnlyExecutableProviderIdentity()

    @Test
    fun testOnlyExecutableProviderIdentityExistsInTestSourceOnly() {
        val identity = identity()
        val descriptor = SkaldVaultV1TestOnlyProviderIdentityDescriptorPolicy.currentProviderIdentityDescriptor()

        assertEquals(
            SkaldVaultV1TestOnlyExecutableProviderKind.TestOnlyExecutablePublicKatProvider,
            identity.providerKind,
        )
        assertEquals(
            SkaldVaultV1TestOnlyExecutableProviderSourceScope.TestSourceOnly,
            identity.sourceScope,
        )
        assertEquals(descriptor.providerIdentityLabel.value, identity.providerIdentityLabel.value)
        assertTrue(identity.implementationPresent)
        assertFalse(identity.productionImplementationPresent)
        assertEquals(0, identity.failureLabels.size)
        assertEquals(0, identity.blockerCount)
        assertEquals(0, identity.warningCount)
        assertEquals(
            SkaldVaultV1TestOnlyExecutableProviderIdentityCheck.entries.size,
            identity.implementationCheckCount,
        )
    }

    @Test
    fun testOnlyExecutableProviderIdentityReadsExecutableScopeAdmission() {
        val identity = identity()
        val admission =
            SkaldVaultV1TestOnlyProviderIdentityExecutableScopeAdmissionPolicy
                .currentProviderIdentityExecutableScopeAdmission()

        assertTrue(identity.executableScopeAdmissionPresent)
        assertTrue(identity.executableScopeAdmissionPassed)
        assertTrue(admission.executableScopeAdmissionPassed)
        assertTrue(identity.descriptorCompletionAuditPresent)
        assertTrue(identity.descriptorCompletionAuditPassed)
        assertEquals(admission.descriptorCompletionAuditPassed, identity.descriptorCompletionAuditPassed)
        assertTrue(identity.testOnlyExecutableProviderImplementationAdmitted)
        assertTrue(identity.publicKatProviderImplementationScopeAdmitted)
        assertTrue(identity.plannedProviderImplementationMustRemainTestSourceOnly)
        assertTrue(identity.futureProviderLevelKatExecutionRequiresSeparatePass)
        assertTrue(identity.futureProviderSelectionEnablementRequiresSeparatePass)
    }

    @Test
    fun testOnlyExecutableProviderIdentityLabelsPublicKatCoverageOnly() {
        val identity = identity()

        assertEquals("TEST_ONLY_EXECUTABLE_PUBLIC_KAT_PROVIDER", identity.providerKind.label)
        assertEquals("TEST_SOURCE_ONLY", identity.sourceScope.label)
        assertTrue(identity.publicKatScopeOnly)
        assertTrue(identity.canCoverKdfPublicKat)
        assertTrue(identity.canCoverAeadPublicKat)
        assertFalse(identity.providerLevelKatExecutedInThisBranch)
        assertFalse(identity.kdfExecutedInThisBranch)
        assertFalse(identity.aeadExecutedInThisBranch)
    }

    @Test
    fun testOnlyExecutableProviderIdentityDoesNotEnableProductionSelectionOrProviderRegistration() {
        val identity = identity()
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertFalse(identity.providerSelectionEnabled)
        assertFalse(identity.productionProviderSelectable)
        assertFalse(identity.providerRegistryEntryPresent)
        assertFalse(identity.providerFactoryPresent)
        assertFalse(identity.providerDispatcherPresent)
        assertFalse(identity.executorTargetPresent)
        assertTrue(identity.disabledProviderOnly)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertEquals(VaultCryptoProviderSelectionDecision.DisabledProviderSelected, selection.decision)
        assertTrue(selection.selectedProviderIsDisabled)
        assertTrue(selection.selectedProvider is DisabledVaultCryptoProvider)
        assertFalse(selection.productionProviderSelectable)
    }

    @Test
    fun testOnlyExecutableProviderIdentityAddsNoStorageSyncSigningUiEndpointMainnetOrMaterialSurface() {
        val identity = identity()

        assertFalse(identity.vaultPersistencePresent)
        assertFalse(identity.secureStorageSuccessPathPresent)
        assertFalse(identity.secureMetadataSuccessPathPresent)
        assertFalse(identity.productionSyncPresent)
        assertFalse(identity.signingBroadcastingPresent)
        assertFalse(identity.uiPresent)
        assertFalse(identity.endpointPresent)
        assertFalse(identity.mainnetPresent)
        assertFalse(identity.providerHandlesExposed)
        assertFalse(identity.rawKatMaterialExposed)
        assertFalse(identity.tracePayloadPresent)
    }

    @Test
    fun testOnlyExecutableProviderIdentityKeepsSourceMaterialCorpusBoundaries() {
        val identity = identity()

        assertTrue(identity.buildHistoryExcludedFromNormalSourceMaterialCorpus)
        assertTrue(identity.localArtifactRootExcludedFromNormalSourceMaterialCorpus)
    }

    @Test
    fun testOnlyExecutableProviderIdentityOutputIsRedactedAndSafeLabelOnly() {
        val identity = identity()
        val output = listOf(
            identity.toString(),
            identity.providerImplementationId.toString(),
            identity.providerIdentityLabel.toString(),
            identity.displayLabel.toString(),
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
            "public vector bytes",
            "public vector hex",
            "wpkh(",
            "tr(",
            "xpub",
            "xprv",
            "tprv",
            "psbt",
        )

        assertContains(output, "REDACTED")
        assertContains(output, "TEST_SOURCE_ONLY")
        assertContains(output, "PROVIDER_IMPLEMENTATION_ONLY")
        assertContains(output, "PUBLIC_KAT_SCOPE_ONLY")
        assertContains(output, "NO_PROVIDER_LEVEL_KAT_EXECUTION")
        assertContains(output, "NO_PROVIDER_SELECTION")
        assertContains(output, "DISABLED_PROVIDER_ONLY")
        forbiddenText.forEach { forbidden ->
            assertFalse(output.contains(forbidden, ignoreCase = true), "Output leaked forbidden text: $forbidden")
        }
        assertFalse(Regex("\\b[0-9a-fA-F]{64}\\b").containsMatchIn(output))
        assertFalse(Regex("\\b(?:bc1|tb1|bcrt1)[a-z0-9]{20,}\\b", RegexOption.IGNORE_CASE).containsMatchIn(output))
    }
}
