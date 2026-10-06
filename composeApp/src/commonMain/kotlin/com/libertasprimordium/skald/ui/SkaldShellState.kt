package com.libertasprimordium.skald.ui

import com.libertasprimordium.skald.security.SecureSecretStorage
import com.libertasprimordium.skald.security.SecureStorageUiStatus
import com.libertasprimordium.skald.security.toUiStatus
import com.libertasprimordium.skald.ui.navigation.AppScreen

/** Presentation only: startup reads capability metadata and retains no storage or service handle. */
class SkaldShellState private constructor(
    val selectedScreen: AppScreen,
    val secureStorageStatus: SecureStorageUiStatus,
) {
    fun navigate(screen: AppScreen): SkaldShellState =
        SkaldShellState(screen, secureStorageStatus)

    val page: ShellPage
        get() = when (selectedScreen) {
            AppScreen.Wallet -> ShellPage(
                title = "Wallet",
                subtitle = "Monero wallet",
                sections = listOf(
                    ShellSection(
                        title = "Wallet unavailable",
                        paragraphs = listOf(
                            "Wallet functionality is not implemented yet. Balances and transaction history are unavailable.",
                        ),
                    ),
                ),
            )
            AppScreen.Settings -> ShellPage(
                title = "Settings",
                subtitle = "Application information",
                sections = listOf(
                    ShellSection(
                        title = "Vault",
                        status = "Unavailable",
                        paragraphs = listOf(
                            "This build cannot create, unlock, or store data in a vault.",
                        ),
                    ),
                    ShellSection(
                        title = "Connection",
                        status = "Not configured",
                        paragraphs = listOf(
                            "The planned default is your private full node, with wallet scanning on this device. No endpoint is configured or contacted.",
                            "There is no public default or automatic fallback. Skald does not provide a connection service.",
                            "Custom light wallet server (LWS) support is a future option. Server scanning discloses private view-key material and will require explicit privacy consent for the wallet and endpoint. Turning LWS off cannot undo an earlier disclosure.",
                        ),
                    ),
                    ShellSection(
                        title = "About",
                        paragraphs = listOf(
                            "Skald Vault is a Monero wallet project for Android and Linux.",
                        ),
                    ),
                ),
            )
        }

    companion object {
        fun start(secureStorage: SecureSecretStorage): SkaldShellState =
            SkaldShellState(AppScreen.Wallet, secureStorage.capability.toUiStatus())
    }
}

/** Read-only presentation values, without commands, operation payloads or live wallet state. */
data class ShellPage(
    val title: String,
    val subtitle: String,
    val sections: List<ShellSection>,
)

data class ShellSection(
    val title: String,
    val status: String? = null,
    val paragraphs: List<String>,
)
