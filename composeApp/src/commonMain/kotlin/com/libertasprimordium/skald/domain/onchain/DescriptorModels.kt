package com.libertasprimordium.skald.domain.onchain

@JvmInline
value class DescriptorWalletId(val value: String)

@JvmInline
value class DescriptorWalletLabel(val value: String)

enum class DescriptorWalletStatus(val label: String) {
    NotCreated("not created"),
    Planned("planned"),
    Draft("draft"),
    WatchOnly("watch-only"),
    ImportedDescriptor("imported descriptor"),
    ImportedSingleKey("imported single key"),
    DescriptorExportRequired("descriptor export required"),
    ReadyForTestnetImplementation("ready for testnet implementation"),
    DisabledPlaceholder("disabled placeholder"),
}

enum class DescriptorWalletOrigin(val label: String) {
    NativeAppSeed("native app seed"),
    ImportedDescriptor("imported descriptor"),
    ImportedWatchOnlyDescriptor("imported watch-only descriptor"),
    ImportedSingleKey("imported single key"),
    NostrNpubWatchOnly("Nostr npub watch-only"),
    NostrNsecImportedSpend("Nostr nsec imported spend"),
    ExternalSigner("external signer"),
    HardwareSignerPlanned("hardware signer planned"),
}

enum class DescriptorScriptPolicy(val label: String) {
    Bip86P2tr("BIP86 P2TR"),
    P2wpkhLegacySupportPlanned("P2WPKH legacy support planned"),
    MultisigDescriptorPlanned("multisig descriptor planned"),
    MiniscriptPolicyPlanned("Miniscript policy planned"),
    SilentPaymentPlanned("silent payment planned"),
}

enum class DescriptorSetStatus(val label: String) {
    NotCreated("not created"),
    Planned("planned"),
    RedactedDescriptorOnly("redacted descriptor only"),
    WatchOnlyDescriptorOnly("watch-only descriptor only"),
    PrivateMaterialUnavailable("private material unavailable"),
    ReadyForTestnetImplementation("ready for testnet implementation"),
    DisabledPlaceholder("disabled placeholder"),
}

enum class DescriptorBackupStatus(val label: String) {
    NotAvailable("not available"),
    DescriptorExportRequired("descriptor export required"),
    DescriptorExportRecommended("descriptor export recommended"),
    SeparateKeyBackupRequired("separate key backup required"),
    WatchOnlyExportRecommended("watch-only export recommended"),
    IdentityKeyRiskWarningRequired("identity-key risk warning required"),
    NotRecoverableByAppSeed("not recoverable by app seed"),
}

enum class DescriptorExportState(val label: String) {
    Unavailable("unavailable"),
    RequiredBeforeUse("required before use"),
    Planned("planned"),
    ExportedPlaceholder("exported placeholder"),
    Disabled("disabled"),
}

enum class ImportedKeyPolicy(val label: String) {
    SeparateBackupRequired("separate backup required"),
    TreatAsSeparateWallet("treat as separate wallet"),
    NeverMergeWithoutWarning("never merge without warning"),
    DisabledPlaceholder("disabled placeholder"),
}

enum class WatchOnlyPolicy(val label: String) {
    CannotSpend("cannot spend"),
    RequiresExternalSigner("requires external signer"),
    DescriptorExportRecommended("descriptor export recommended"),
    DisabledPlaceholder("disabled placeholder"),
}

data class DescriptorDisplayState(
    val redactedDescriptor: String,
    val descriptorSetStatus: DescriptorSetStatus,
    val fingerprintDisplay: String,
    val containsPrivateMaterial: Boolean,
)

data class DescriptorWalletProfile(
    val id: DescriptorWalletId,
    val label: DescriptorWalletLabel,
    val status: DescriptorWalletStatus,
    val origin: DescriptorWalletOrigin,
    val scriptPolicy: DescriptorScriptPolicy,
    val descriptorDisplay: DescriptorDisplayState,
    val backupStatus: DescriptorBackupStatus,
    val exportState: DescriptorExportState,
    val canSpend: Boolean,
    val importedKeyPolicy: ImportedKeyPolicy?,
    val watchOnlyPolicy: WatchOnlyPolicy?,
    val warnings: List<String>,
)
