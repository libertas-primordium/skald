package com.libertasprimordium.skald.settings

import android.content.Context

class AndroidSettingsStorage(
    context: Context,
) : SettingsStorage {
    private val preferences = context.applicationContext.getSharedPreferences(
        "skald_non_secret_settings",
        Context.MODE_PRIVATE,
    )

    override fun readText(): String? =
        preferences.getString(BitcoinBackendSettingsKey, null)

    override fun writeText(value: String) {
        preferences.edit()
            .putString(BitcoinBackendSettingsKey, value)
            .apply()
    }

    private companion object {
        const val BitcoinBackendSettingsKey = "bitcoin_backend_settings_v1"
    }
}
