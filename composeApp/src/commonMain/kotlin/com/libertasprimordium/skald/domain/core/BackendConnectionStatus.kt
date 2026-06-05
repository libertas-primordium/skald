package com.libertasprimordium.skald.domain.core

enum class BackendConnectionStatus(val label: String) {
    NotConfigured("not configured"),
    Planned("planned"),
    DisabledPlaceholder("disabled placeholder"),
    UserConfiguredTestnet("user-configured testnet"),
    Unavailable("unavailable"),
}
