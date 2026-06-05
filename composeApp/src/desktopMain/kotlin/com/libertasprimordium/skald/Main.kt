package com.libertasprimordium.skald

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.libertasprimordium.skald.security.DesktopSecureStorage
import com.libertasprimordium.skald.settings.DesktopSettingsStorage
import com.libertasprimordium.skald.settings.PersistentSettingsRepository

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Skald Vault",
    ) {
        SkaldApp(
            settingsRepository = PersistentSettingsRepository(DesktopSettingsStorage()),
            secureStorage = DesktopSecureStorage(),
        )
    }
}
