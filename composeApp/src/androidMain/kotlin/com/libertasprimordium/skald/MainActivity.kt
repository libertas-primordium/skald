package com.libertasprimordium.skald

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.libertasprimordium.skald.settings.AndroidSettingsStorage
import com.libertasprimordium.skald.settings.PersistentSettingsRepository

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SkaldApp(
                settingsRepository = PersistentSettingsRepository(AndroidSettingsStorage(this)),
            )
        }
    }
}
