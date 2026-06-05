package com.libertasprimordium.skald.domain.nostr

enum class NostrKeyMode(val label: String) {
    NpubWatchOnly("npub to Taproot watch wallet"),
    NsecImportedSpend("nsec to imported Taproot spend wallet"),
    PublicIdentityPayment("public identity-bound payment"),
    RecoverySweep("privacy-preserving recovery/sweep attempt"),
}

data class NostrPaymentIntent(
    val title: String,
    val mode: NostrKeyMode,
    val publicLinkage: String,
    val disabledReason: String,
)
