package com.libertasprimordium.skald.domain.onchain.bdk

import com.libertasprimordium.skald.domain.core.NetworkEnvironment
import org.bitcoindevkit.Network

actual fun runPlatformBdkAdapterProbe(): BdkAdapterProbeResult =
    runCatching {
        BdkAdapterProbeResult.linked(
            platform = BdkAdapterPlatform.DesktopJvm,
            networkProbes = developmentNetworkProbes(),
        )
    }.getOrElse {
        BdkAdapterProbeResult.failed(
            platform = BdkAdapterPlatform.DesktopJvm,
            error = BdkAdapterError.PlatformBindingUnavailable(
                safeDetail = "Desktop BDK enum probe failed before any wallet operation was attempted.",
            ),
        )
    }

private fun developmentNetworkProbes(): List<BdkAdapterNetworkProbe> =
    listOf(
        BdkAdapterNetworkProbe(NetworkEnvironment.Regtest, Network.REGTEST.name, developmentOnly = true),
        BdkAdapterNetworkProbe(NetworkEnvironment.Signet, Network.SIGNET.name, developmentOnly = true),
        BdkAdapterNetworkProbe(NetworkEnvironment.Testnet, Network.TESTNET.name, developmentOnly = true),
        BdkAdapterNetworkProbe(NetworkEnvironment.Testnet4, Network.TESTNET4.name, developmentOnly = true),
    )
