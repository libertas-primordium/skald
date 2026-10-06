package com.libertasprimordium.skald.ui.navigation

enum class AppScreen(val label: String) {
    Wallet("Wallet"),
    Settings("Settings"),
}

object SkaldNavigationModel {
    val PrimaryScreens: List<AppScreen> = listOf(AppScreen.Wallet, AppScreen.Settings)
}
