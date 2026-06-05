package com.libertasprimordium.skald.domain.core

enum class WalletRail(val label: String) {
    OnChain("On-chain"),
    Lightning("Lightning"),
    Cashu("Cashu"),
    Nostr("Nostr"),
    RemoteNode("Remote node"),
}
