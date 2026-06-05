package com.libertasprimordium.skald.settings

import java.io.File

class DesktopSettingsStorage(
    private val settingsFile: File = defaultSettingsFile(),
) : SettingsStorage {
    override fun readText(): String? =
        settingsFile.takeIf { it.exists() }?.readText()

    override fun writeText(value: String) {
        settingsFile.parentFile?.mkdirs()
        settingsFile.writeText(value)
    }
}

private fun defaultSettingsFile(): File {
    val configRoot = System.getenv("XDG_CONFIG_HOME")
        ?.takeIf { it.isNotBlank() }
        ?.let(::File)
        ?: File(System.getProperty("user.home"), ".config")
    return File(configRoot, "skald/backend-settings.txt")
}
