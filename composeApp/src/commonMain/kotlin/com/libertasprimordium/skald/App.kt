package com.libertasprimordium.skald

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.libertasprimordium.skald.security.DisabledSecureSecretStorage
import com.libertasprimordium.skald.security.SecureSecretStorage
import com.libertasprimordium.skald.ui.SkaldShellState
import com.libertasprimordium.skald.ui.components.SkaldAppScaffold
import com.libertasprimordium.skald.ui.navigation.AppScreen
import com.libertasprimordium.skald.ui.screens.SettingsScreen
import com.libertasprimordium.skald.ui.screens.WalletScreen

@Composable
fun SkaldApp(
    secureStorage: SecureSecretStorage = DisabledSecureSecretStorage(),
) {
    var state by remember(secureStorage) { mutableStateOf(SkaldShellState.start(secureStorage)) }

    SkaldAppScaffold(
        selectedScreen = state.selectedScreen,
        onSelectedScreen = { state = state.navigate(it) },
    ) { screen ->
        val page = state.page
        when (screen) {
            AppScreen.Wallet -> WalletScreen(page)
            AppScreen.Settings -> SettingsScreen(page)
        }
    }
}
