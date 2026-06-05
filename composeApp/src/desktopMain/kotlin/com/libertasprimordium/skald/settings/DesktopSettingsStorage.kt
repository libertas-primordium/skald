package com.libertasprimordium.skald.settings

import java.io.File

class DesktopSettingsStorage(
    private val settingsDirectory: File = defaultSettingsDirectory(),
) : SettingsStorage {
    override fun readText(key: SettingsStorageKey): String? =
        settingsFile(key).takeIf { it.exists() }?.readText()

    override fun writeText(key: SettingsStorageKey, value: String) {
        settingsDirectory.mkdirs()
        settingsFile(key).writeText(value)
    }

    private fun settingsFile(key: SettingsStorageKey): File =
        File(settingsDirectory, key.fileName)
}

private fun defaultSettingsDirectory(): File {
    val configRoot = System.getenv("XDG_CONFIG_HOME")
        ?.takeIf { it.isNotBlank() }
        ?.let(::File)
        ?: File(System.getProperty("user.home"), ".config")
    return File(configRoot, "skald")
}
