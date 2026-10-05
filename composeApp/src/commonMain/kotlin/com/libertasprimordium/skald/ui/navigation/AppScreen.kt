package com.libertasprimordium.skald.ui.navigation

enum class AppScreen(val label: String) {
    Overview("Overview"),
    Wallet("Wallet"),
    Recovery("Recovery"),
    Connection("Connection"),
    Settings("Settings"),
}

enum class NavigationIconSpec {
    Eye,
    LinkedBoxes,
}

data class PrimaryTabSpec(
    val screen: AppScreen,
    val icon: NavigationIconSpec,
    val accessibilityLabel: String,
)

object SkaldNavigationModel {
    val PrimaryTabs: List<PrimaryTabSpec> = listOf(
        PrimaryTabSpec(AppScreen.Overview, NavigationIconSpec.Eye, "Overview"),
        PrimaryTabSpec(AppScreen.Wallet, NavigationIconSpec.LinkedBoxes, "Wallet"),
    )
    val PrimaryScreens: List<AppScreen> = PrimaryTabs.map { it.screen }
    val MenuScreens: List<AppScreen> = listOf(AppScreen.Recovery, AppScreen.Connection, AppScreen.Settings)
}
