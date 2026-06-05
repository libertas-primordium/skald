package com.libertasprimordium.skald.domain.onchain

@JvmInline
value class UtxoId(val value: String)

@JvmInline
value class UtxoLabel(val value: String)

@JvmInline
value class UtxoClusterId(val value: String)

data class OutPointRef(
    val txidDisplay: String,
    val voutDisplay: String,
)

enum class UtxoStatus(val label: String) {
    Confirmed("confirmed"),
    Unconfirmed("unconfirmed"),
    Immature("immature"),
    Frozen("frozen"),
    ReservedForDraft("reserved for draft"),
    SpentPlaceholder("spent placeholder"),
}

enum class UtxoSource(val label: String) {
    NativeDescriptor("native descriptor"),
    ImportedDescriptor("imported descriptor"),
    ImportedSingleKey("imported single key"),
    NostrNpubWatchOnly("Nostr npub watch-only"),
    NostrNsecImportedSpend("Nostr nsec imported spend"),
    ExternalSigner("external signer"),
    DemoPlaceholder("demo placeholder"),
}

enum class UtxoAge(val label: String) {
    Unknown("unknown"),
    Unconfirmed("unconfirmed"),
    Immature("immature"),
    ConfirmedFew("few confirmations"),
    ConfirmedDeep("deeply confirmed"),
}

enum class UtxoPrivacyState(val label: String) {
    IdentityLinked("identity-linked"),
    ClusterLinked("cluster-linked"),
    ChangeSuspected("change suspected"),
    CleanUnknown("clean unknown"),
    ImportedKeyLinked("imported-key linked"),
    NostrLinked("Nostr linked"),
}

enum class SpendabilityState(val label: String) {
    SpendableAfterImplementation("spendable after implementation"),
    WatchOnlyCannotSpend("watch-only cannot spend"),
    FrozenByPolicy("frozen by policy"),
    ReservedForDraft("reserved for draft"),
    DisabledPlaceholder("disabled placeholder"),
}

data class UtxoView(
    val id: UtxoId,
    val outPoint: OutPointRef,
    val amountSats: Long,
    val status: UtxoStatus,
    val label: UtxoLabel,
    val clusterId: UtxoClusterId,
    val source: UtxoSource,
    val age: UtxoAge,
    val privacyState: UtxoPrivacyState,
    val spendabilityState: SpendabilityState,
    val walletId: DescriptorWalletId,
)
