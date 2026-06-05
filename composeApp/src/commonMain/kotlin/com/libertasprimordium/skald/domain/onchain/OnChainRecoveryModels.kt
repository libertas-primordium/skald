package com.libertasprimordium.skald.domain.onchain

enum class BackupArtifactType(val label: String) {
    AppSeed("app seed"),
    DescriptorExport("descriptor export"),
    WatchOnlyDescriptorExport("watch-only descriptor export"),
    ImportedKeyBackup("imported key backup"),
    WalletMetadataBackup("wallet metadata backup"),
    LabelBackup("label backup"),
    ExternalSignerPolicyBackup("external signer policy backup"),
    NostrIdentityKeyBackup("Nostr identity key backup"),
}

enum class RecoveryRequirement(val label: String) {
    Required("required"),
    StronglyRecommended("strongly recommended"),
    SeparateBackupRequired("separate backup required"),
    SpendUnavailable("spend unavailable"),
    IdentityKeyRiskAcknowledgementRequired("identity-key risk acknowledgement required"),
    ExternalSystemRequired("external system required"),
}

enum class RecoveryChecklistState(val label: String, val isCovered: Boolean) {
    NotCreated("not created", isCovered = false),
    NotExported("not exported", isCovered = false),
    SeparateBackupRequired("separate backup required", isCovered = false),
    WatchOnlyCannotSpend("watch-only cannot spend", isCovered = false),
    RiskAcknowledgementRequired("risk acknowledgement required", isCovered = false),
    Planned("planned", isCovered = false),
    CoveredPlaceholder("covered placeholder", isCovered = true),
}

enum class RecoveryTransition(val label: String) {
    SeedCreatedToDescriptorExportRequired("SeedCreated -> DescriptorExportRequired"),
    DescriptorExportedToOnChainRecoveryCovered("DescriptorExported -> OnChainRecoveryCovered"),
    ImportedKeyAddedToSeparateKeyBackupRequired("ImportedKeyAdded -> SeparateKeyBackupRequired"),
    WatchOnlyDescriptorAddedToSpendUnavailable("WatchOnlyDescriptorAdded -> SpendUnavailable"),
    NostrNsecImportedToIdentityKeyRiskAcknowledgementRequired("NostrNsecImported -> IdentityKeyRiskAcknowledgementRequired"),
}

enum class RecoveryBlockingIssue(val label: String) {
    SeedNotCreated("seed not created"),
    DescriptorNotExported("descriptor not exported"),
    ImportedKeyRequiresSeparateBackup("imported key requires separate backup"),
    WatchOnlyWalletCannotSpend("watch-only wallet cannot spend"),
    NostrIdentityKeyRiskUnacknowledged("Nostr identity key risk unacknowledged"),
    MetadataBackupNotConfigured("metadata backup not configured"),
}

data class RecoveryChecklistItem(
    val artifactType: BackupArtifactType,
    val requirement: RecoveryRequirement,
    val state: RecoveryChecklistState,
    val detail: String,
    val blockingIssues: List<RecoveryBlockingIssue>,
)

data class OnChainRecoveryStatus(
    val headline: String,
    val checklistItems: List<RecoveryChecklistItem>,
    val transitions: List<RecoveryTransition>,
    val blockingIssues: List<RecoveryBlockingIssue>,
)
