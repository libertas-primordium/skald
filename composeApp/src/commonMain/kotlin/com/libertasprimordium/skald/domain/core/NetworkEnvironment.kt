package com.libertasprimordium.skald.domain.core

enum class NetworkEnvironment(
    val label: String,
    val isDevelopmentSelectable: Boolean,
    val allowsMainnetOperations: Boolean,
) {
    Regtest("regtest", isDevelopmentSelectable = true, allowsMainnetOperations = false),
    Signet("signet", isDevelopmentSelectable = true, allowsMainnetOperations = false),
    Testnet("testnet", isDevelopmentSelectable = true, allowsMainnetOperations = false),
    Testnet4("testnet4", isDevelopmentSelectable = true, allowsMainnetOperations = false),
    MainnetDisabled("mainnet disabled", isDevelopmentSelectable = false, allowsMainnetOperations = false),
}
