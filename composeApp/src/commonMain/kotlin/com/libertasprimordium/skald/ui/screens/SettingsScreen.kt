package com.libertasprimordium.skald.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.libertasprimordium.skald.domain.core.NetworkEnvironment
import com.libertasprimordium.skald.domain.onchain.bdk.BdkAdapterProbeResult
import com.libertasprimordium.skald.security.SecureStorageUiStatus
import com.libertasprimordium.skald.ui.components.BulletList
import com.libertasprimordium.skald.ui.components.CardGrid
import com.libertasprimordium.skald.ui.components.RailBalanceRow
import com.libertasprimordium.skald.ui.components.ScreenTitle
import com.libertasprimordium.skald.ui.components.SecureStorageStatusCard
import com.libertasprimordium.skald.ui.components.SkaldCard
import com.libertasprimordium.skald.ui.theme.SkaldWarning

@Composable
fun SettingsScreen(
    networks: List<NetworkEnvironment>,
    secureStorageStatus: SecureStorageUiStatus,
    bdkAdapterProbeResult: BdkAdapterProbeResult,
) {
    ScreenTitle("Settings", "Non-functional settings placeholders.")
    CardGrid {
        SkaldCard(title = "Network", state = "regtest/signet/testnet only") {
            Text(
                text = "Development networks only. Mainnet is disabled and unavailable.",
                color = SkaldWarning,
                lineHeight = 20.sp,
            )
            networks.forEach { network ->
                val state = if (network.isDevelopmentSelectable) "available for development" else "disabled"
                RailBalanceRow(network.label, 0, state)
            }
        }
        BdkAdapterStatusCard(bdkAdapterProbeResult)
        SecureStorageStatusCard(secureStorageStatus)
        SkaldCard(title = "Security", state = "planned") {
            BulletList(
                listOf(
                    "Security lock planned",
                    "Backup/export planned",
                    "Advanced warnings always enabled",
                    "Developer/testnet mode enabled",
                    "Secret storage boundary exists but rejects all writes, reads, and deletes",
                ),
            )
        }
    }
}

@Composable
private fun BdkAdapterStatusCard(result: BdkAdapterProbeResult) {
    SkaldCard(title = "BDK adapter", state = result.statusLabel) {
        Text(
            text = "Pinned ${result.version.version} platform dependencies are present behind Skald-owned adapter models.",
            color = SkaldWarning,
            lineHeight = 20.sp,
        )
        Text(
            text = result.diagnostic,
            color = SkaldWarning,
            lineHeight = 20.sp,
        )
        BulletList(
            listOf(
                "Android artifact: ${result.version.androidArtifact}",
                "Desktop artifact: ${result.version.desktopArtifact}",
                "Platform: ${result.platform.label}",
                "Development network probe: ${result.networkProbes.joinToString { it.skaldNetwork.label }}",
                "Wallet creation, key generation, descriptors, address derivation, sync, PSBTs, signing, broadcasting, persistence, networking, and mainnet remain disabled.",
            ),
        )
        result.error?.let { error ->
            Text(
                text = "${error.code}: ${error.safeDetail}",
                color = SkaldWarning,
                lineHeight = 20.sp,
            )
        }
    }
}
