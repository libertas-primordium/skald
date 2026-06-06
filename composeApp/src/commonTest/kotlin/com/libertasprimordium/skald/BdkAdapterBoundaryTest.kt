package com.libertasprimordium.skald

import com.libertasprimordium.skald.domain.core.NetworkEnvironment
import com.libertasprimordium.skald.domain.onchain.bdk.BdkAdapterBlockingIssue
import com.libertasprimordium.skald.domain.onchain.bdk.BdkAdapterCapability
import com.libertasprimordium.skald.domain.onchain.bdk.BdkAdapterError
import com.libertasprimordium.skald.domain.onchain.bdk.BdkAdapterNetworkProbe
import com.libertasprimordium.skald.domain.onchain.bdk.BdkAdapterPlatform
import com.libertasprimordium.skald.domain.onchain.bdk.BdkAdapterProbeResult
import com.libertasprimordium.skald.domain.onchain.bdk.BdkAdapterProbeState
import com.libertasprimordium.skald.domain.onchain.bdk.BdkAdapterVersion
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BdkAdapterBoundaryTest {
    @Test
    fun pinnedBdkVersionMetadataIsExplicit() {
        val version = BdkAdapterVersion.Pinned

        assertEquals("2.3.0", version.version)
        assertEquals("org.bitcoindevkit:bdk-android:2.3.0", version.androidArtifact)
        assertEquals("org.bitcoindevkit:bdk-jvm:2.3.0", version.desktopArtifact)
    }

    @Test
    fun packagingProbeCapabilitiesDoNotEnableWalletOperations() {
        val result = linkedResult()

        assertEquals(BdkAdapterProbeState.Linked, result.state)
        assertFalse(result.walletOperationsEnabled)
        assertFalse(result.mainnetEnabled)
        assertContains(result.activeCapabilities, BdkAdapterCapability.PlatformDependencyLinked)
        assertContains(result.activeCapabilities, BdkAdapterCapability.DevelopmentNetworkEnumProbe)
        assertContains(result.activeCapabilities, BdkAdapterCapability.SkaldOwnedErrorTranslation)
        assertTrue(result.activeCapabilities.none { it.touchesWalletMaterial })
        assertTrue(result.activeCapabilities.none { it.isWalletOperation })
        assertTrue(BdkAdapterCapability.entries.none { it.enabledInThisPass && it.isWalletOperation })
    }

    @Test
    fun walletProtocolCapabilitiesRemainDisabled() {
        val result = linkedResult()
        val disabled = result.disabledCapabilities

        assertContains(disabled, BdkAdapterCapability.WalletCreation)
        assertContains(disabled, BdkAdapterCapability.KeyGeneration)
        assertContains(disabled, BdkAdapterCapability.DescriptorParsing)
        assertContains(disabled, BdkAdapterCapability.AddressDerivation)
        assertContains(disabled, BdkAdapterCapability.WalletSync)
        assertContains(disabled, BdkAdapterCapability.PsbtConstruction)
        assertContains(disabled, BdkAdapterCapability.Signing)
        assertContains(disabled, BdkAdapterCapability.Broadcasting)
        assertContains(disabled, BdkAdapterCapability.BdkPersistence)
        assertContains(disabled, BdkAdapterCapability.BackendNetworking)
        assertContains(disabled, BdkAdapterCapability.MainnetOperation)
    }

    @Test
    fun bdkProbeUsesDevelopmentNetworksOnly() {
        val result = linkedResult()

        assertEquals(
            listOf(
                NetworkEnvironment.Regtest,
                NetworkEnvironment.Signet,
                NetworkEnvironment.Testnet,
                NetworkEnvironment.Testnet4,
            ),
            result.networkProbes.map { it.skaldNetwork },
        )
        assertTrue(result.networkProbes.all { it.developmentOnly })
        assertTrue(result.networkProbes.none { it.skaldNetwork == NetworkEnvironment.MainnetDisabled })
        assertTrue(result.networkProbes.none { it.adapterNetworkName == "BITCOIN" })
        assertFalse(result.mainnetEnabled)
    }

    @Test
    fun defaultBlockersExplainWhyBdkIsNotWalletReady() {
        val result = linkedResult()

        assertContains(result.blockingIssues, BdkAdapterBlockingIssue.WalletFunctionalityNotEnabled)
        assertContains(result.blockingIssues, BdkAdapterBlockingIssue.SecureStorageDisabled)
        assertContains(result.blockingIssues, BdkAdapterBlockingIssue.BdkPersistenceDisabled)
        assertContains(result.blockingIssues, BdkAdapterBlockingIssue.BackendNetworkingDisabled)
        assertContains(result.blockingIssues, BdkAdapterBlockingIssue.MainnetDisabled)
    }

    @Test
    fun translatedProbeErrorsAreSkaldOwnedAndNonSensitive() {
        val result = BdkAdapterProbeResult.failed(
            platform = BdkAdapterPlatform.DesktopJvm,
            error = BdkAdapterError.PlatformBindingUnavailable(
                safeDetail = "Desktop BDK enum probe failed before any wallet operation was attempted.",
            ),
        )
        val error = requireNotNull(result.error)

        assertEquals("BDK_PLATFORM_BINDING_UNAVAILABLE", error.code)
        assertFalse(error.safeDetail.contains("org.bitcoindevkit"))
        assertFalse(error.safeDetail.contains("/"))
        assertFalse(error.safeDetail.contains("\\"))
        assertFalse(result.walletOperationsEnabled)
    }

    private fun linkedResult(): BdkAdapterProbeResult =
        BdkAdapterProbeResult.linked(
            platform = BdkAdapterPlatform.DesktopJvm,
            networkProbes = listOf(
                BdkAdapterNetworkProbe(NetworkEnvironment.Regtest, "REGTEST", developmentOnly = true),
                BdkAdapterNetworkProbe(NetworkEnvironment.Signet, "SIGNET", developmentOnly = true),
                BdkAdapterNetworkProbe(NetworkEnvironment.Testnet, "TESTNET", developmentOnly = true),
                BdkAdapterNetworkProbe(NetworkEnvironment.Testnet4, "TESTNET4", developmentOnly = true),
            ),
        )
}
