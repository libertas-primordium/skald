package com.libertasprimordium.skald.settings

import android.content.Context

class AndroidSettingsStorage(
    context: Context,
) : SettingsStorage {
    private val preferences = context.applicationContext.getSharedPreferences(
        "skald_non_secret_settings",
        Context.MODE_PRIVATE,
    )

    override fun readText(key: SettingsStorageKey): String? =
        preferences.getString(key.preferenceKey, null)

    override fun writeText(key: SettingsStorageKey, value: String) {
        preferences.edit()
            .putString(key.preferenceKey, value)
            .apply()
    }
}
