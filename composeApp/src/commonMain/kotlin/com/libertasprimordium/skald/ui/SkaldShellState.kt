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
            AppScreen.Overview -> ShellPage(
                title = "Overview",
                subtitle = "Offline Monero wallet scaffold.",
                status = "Wallet unavailable",
                details = listOf(
                    "No operational wallet is available. Balance and transaction history are unavailable.",
                    "Wallet operations and mainnet are disabled. Do not use real funds.",
                ),
                unavailableActions = emptyList(),
            )
            AppScreen.Wallet -> ShellPage(
                title = "Wallet",
                subtitle = "Wallet functionality is not implemented.",
                status = "Unavailable",
                details = listOf(
                    "No wallet, address, account or subaddress is created by this scaffold.",
                    "Spending will require input, recipient, fee, change and privacy review, with separate signing and relay approvals.",
                    "Watch-only capability must never imply signing ability.",
                ),
                unavailableActions = listOf("Create wallet", "Import wallet", "Receive", "Send"),
            )
            AppScreen.Recovery -> ShellPage(
                title = "Recovery",
                subtitle = "Recovery and backup are unavailable.",
                status = "Unavailable",
                details = listOf(
                    "No recovery material is accepted or stored. Recovery formats remain undecided.",
                    "A recovery seed alone may not restore wallet metadata. Imported material requires its own recovery plan.",
                    "Wallet and sensitive metadata storage remain disabled until encrypted vault acceptance.",
                ),
                unavailableActions = listOf("Recover wallet", "Create backup", "Export backup"),
            )
            AppScreen.Connection -> ShellPage(
                title = "Connection",
                subtitle = "User-selected infrastructure only.",
                status = "Endpoint unconfigured",
                details = listOf(
                    "The intended default is your private full node with local wallet scanning. No endpoint is configured or contacted.",
                    "There is no public default, automatic fallback or Skald-operated service.",
                    "Optional custom LWS is a future feature. Server scanning discloses private view-key material and requires explicit wallet- and endpoint-bound privacy consent.",
                    "Disabling LWS cannot undo earlier disclosure. Server visibility and scan completeness must be reviewed before use.",
                ),
                unavailableActions = emptyList(),
            )
            AppScreen.Settings -> ShellPage(
                title = "Settings",
                subtitle = "Static application and vault status.",
                status = "Offline scaffold",
                details = listOf(
                    "Skald targets native Android and Linux.",
                    "App-controlled encrypted vault operations remain disabled. Mainnet remains disabled.",
                ),
                unavailableActions = emptyList(),
            )
        }

    companion object {
        fun start(secureStorage: SecureSecretStorage): SkaldShellState =
            SkaldShellState(AppScreen.Overview, secureStorage.capability.toUiStatus())
    }
}

/** Unavailable action names are text, with no callback, command, approval or operation payload. */
data class ShellPage(
    val title: String,
    val subtitle: String,
    val status: String,
    val details: List<String>,
    val unavailableActions: List<String>,
)
