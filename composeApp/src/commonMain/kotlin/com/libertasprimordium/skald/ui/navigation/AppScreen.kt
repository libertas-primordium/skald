package com.libertasprimordium.skald.ui.navigation

enum class AppScreen(val label: String) {
    Overview("Overview"),
    OnChain("On-chain"),
    Lightning("Lightning"),
    Cashu("Cashu"),
    Nostr("Nostr"),
    Recovery("Recovery"),
    Nodes("Nodes"),
    Settings("Settings"),
}

object SkaldNavigationModel {
    val PrimaryScreens: List<AppScreen> = listOf(
        AppScreen.Overview,
        AppScreen.OnChain,
        AppScreen.Lightning,
        AppScreen.Cashu,
    )

    val MenuScreens: List<AppScreen> = listOf(
        AppScreen.Nostr,
        AppScreen.Recovery,
        AppScreen.Nodes,
        AppScreen.Settings,
    )
}
