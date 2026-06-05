package com.libertasprimordium.skald.ui.screens

import androidx.compose.runtime.Composable
import com.libertasprimordium.skald.domain.core.NetworkEnvironment
import com.libertasprimordium.skald.security.SecureStorageUiStatus
import com.libertasprimordium.skald.ui.components.BulletList
import com.libertasprimordium.skald.ui.components.CardGrid
import com.libertasprimordium.skald.ui.components.RailBalanceRow
import com.libertasprimordium.skald.ui.components.ScreenTitle
import com.libertasprimordium.skald.ui.components.SecureStorageStatusCard
import com.libertasprimordium.skald.ui.components.SkaldCard

@Composable
fun SettingsScreen(
    networks: List<NetworkEnvironment>,
    secureStorageStatus: SecureStorageUiStatus,
) {
    ScreenTitle("Settings", "Non-functional settings placeholders.")
    CardGrid {
        SkaldCard(title = "Network", state = "regtest/signet/testnet only") {
            networks.forEach { network ->
                val state = if (network.isDevelopmentSelectable) "available for development" else "disabled"
                RailBalanceRow(network.label, 0, state)
            }
        }
        SecureStorageStatusCard(secureStorageStatus)
        SkaldCard(title = "Theme", state = "Skald dark") {
            BulletList(
                listOf(
                    "Black background",
                    "Orange accents",
                    "White text on black",
                    "Black text on orange controls",
                    "Subtle dark gray gradients",
                ),
            )
        }
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
