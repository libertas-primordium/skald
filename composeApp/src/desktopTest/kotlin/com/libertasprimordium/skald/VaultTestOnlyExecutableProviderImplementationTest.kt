package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DesktopTestOnlyVaultCryptoProvider
import com.libertasprimordium.skald.security.DesktopTestOnlyVaultCryptoProviderExecutionMode
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyExecutableProviderIdentityPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyExecutableProviderSourceScope
import com.libertasprimordium.skald.security.VaultCryptoProvider
import com.libertasprimordium.skald.security.VaultCryptoProviderBlocker
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderImplementationStatus
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class VaultTestOnlyExecutableProviderImplementationTest {
    @Test
    fun desktopProviderImplementationExistsOnlyInTestSource() {
        val provider = DesktopTestOnlyVaultCryptoProvider()
        val asAny: Any = provider
        val identity = provider.identity

        assertTrue(asAny is VaultCryptoProvider)
        assertEquals(
            SkaldVaultV1TestOnlyExecutableProviderSourceScope.TestSourceOnly,
            identity.sourceScope,
        )
        assertTrue(identity.implementationPresent)
        assertFalse(identity.productionImplementationPresent)
        assertEquals(
            SkaldVaultV1TestOnlyExecutableProviderIdentityPolicy
                .currentTestOnlyExecutableProviderIdentity()
                .providerImplementationId
                .value,
            identity.providerImplementationId.value,
        )
    }

    @Test
    fun desktopProviderImplementationIsStaticOnlyAndDoesNotExecuteKdfAeadOrProviderKats() {
        val provider = DesktopTestOnlyVaultCryptoProvider()
        val report = provider.implementationReport

        assertEquals(DesktopTestOnlyVaultCryptoProviderExecutionMode.StaticImplementationOnly, provider.executionMode)
        assertFalse(provider.providerLevelKatExecutionEnabled)
        assertFalse(provider.providerOperationsEnabled)
        assertTrue(report.implementsVaultCryptoProviderInTestSource)
        assertFalse(report.providerLevelKatExecutedInThisBranch)
        assertFalse(report.kdfExecutedInThisBranch)
        assertFalse(report.aeadExecutedInThisBranch)
        assertFalse(report.providerOperationExecutedInThisBranch)
        assertFalse(report.productionImplementationPresent)
    }

    @Test
    fun desktopProviderImplementationKeepsDisabledStatusAndSelectionBoundary() {
        val provider = DesktopTestOnlyVaultCryptoProvider()
        val status = provider.statusReport
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(VaultCryptoProviderImplementationStatus.DisabledBoundaryOnly, status.implementationStatus)
        assertFalse(status.implementationStatus.canExecuteCrypto)
        assertFalse(status.implementationStatus.productionApproved)
        assertFalse(status.canDeriveKeys)
        assertFalse(status.canEncryptRecords)
        assertFalse(status.canDecryptRecords)
        assertFalse(status.canGenerateKeys)
        assertFalse(status.canStoreKeysets)
        assertFalse(status.productionPersistenceEnabled)
        assertFalse(status.mainnetEnabled)
        assertContains(status.blockers, VaultCryptoProviderBlocker.ProviderImplementationMissing)
        assertContains(status.blockers, VaultCryptoProviderBlocker.ProviderDisabledByPolicy)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertTrue(selection.selectedProviderIsDisabled)
        assertFalse(selection.productionProviderSelectable)
    }

    @Test
    fun desktopProviderImplementationAddsNoRegistryFactoryDispatcherExecutorStorageOrRuntimeSurface() {
        val provider = DesktopTestOnlyVaultCryptoProvider()
        val identity = provider.identity
        val report = provider.implementationReport

        assertFalse(report.providerSelectionEnabled)
        assertFalse(report.productionProviderSelectable)
        assertFalse(report.providerRegistryEntryPresent)
        assertFalse(report.providerFactoryPresent)
        assertFalse(report.providerDispatcherPresent)
        assertFalse(report.executorTargetPresent)
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
    fun desktopProviderImplementationOutputIsRedactedAndSafeLabelOnly() {
        val provider = DesktopTestOnlyVaultCryptoProvider()
        val output = listOf(
            provider.toString(),
            provider.identity.toString(),
            provider.implementationReport.toString(),
            provider.identity.providerImplementationId.toString(),
            provider.identity.providerIdentityLabel.toString(),
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
        forbiddenText.forEach { forbidden ->
            assertFalse(output.contains(forbidden, ignoreCase = true), "Output leaked forbidden text: $forbidden")
        }
        assertFalse(Regex("\\b[0-9a-fA-F]{64}\\b").containsMatchIn(output))
        assertFalse(Regex("\\b(?:bc1|tb1|bcrt1)[a-z0-9]{20,}\\b", RegexOption.IGNORE_CASE).containsMatchIn(output))
    }
}
