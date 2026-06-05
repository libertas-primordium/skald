package com.libertasprimordium.skald.domain.onchain

import com.libertasprimordium.skald.domain.core.NetworkEnvironment

@JvmInline
value class DescriptorWalletId(val value: String)

@JvmInline
value class DescriptorWalletLabel(val value: String)

typealias DescriptorWalletProfileId = DescriptorWalletId
typealias DescriptorWalletProfileLabel = DescriptorWalletLabel

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
    NativeAppSeedPlanned("native app seed planned"),
    ImportedDescriptorPlanned("imported descriptor planned"),
    WatchOnlyDescriptorPlanned("watch-only descriptor planned"),
    ImportedSingleKeyPlanned("imported single-key planned"),
    NostrNpubWatchOnlyPlanned("Nostr npub watch-only planned"),
    NostrNsecSpendPlanned("Nostr nsec spend planned"),
    ExternalSignerPlanned("external signer planned"),
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

enum class DescriptorWalletCreationIntent(
    val label: String,
    val origin: DescriptorWalletOrigin,
) {
    NativeDescriptorFromAppSeed("native descriptor wallet metadata", DescriptorWalletOrigin.NativeAppSeedPlanned),
    ImportedDescriptor("imported descriptor metadata", DescriptorWalletOrigin.ImportedDescriptorPlanned),
    WatchOnlyDescriptor("watch-only descriptor metadata", DescriptorWalletOrigin.WatchOnlyDescriptorPlanned),
    ImportedSingleKeyTaproot("imported single-key Taproot metadata", DescriptorWalletOrigin.ImportedSingleKeyPlanned),
    NostrNpubWatchOnly("Nostr npub watch-only metadata", DescriptorWalletOrigin.NostrNpubWatchOnlyPlanned),
    NostrNsecSpend("Nostr nsec spend metadata", DescriptorWalletOrigin.NostrNsecSpendPlanned),
    ExternalSigner("external signer metadata", DescriptorWalletOrigin.ExternalSignerPlanned),
    HardwareSigner("hardware signer metadata", DescriptorWalletOrigin.HardwareSignerPlanned),
}

enum class DescriptorWalletProfileStatus(val label: String) {
    DraftMetadata("draft metadata"),
    NonOperationalMetadata("non-operational metadata"),
    MissingDescriptorMaterial("missing descriptor material"),
    MissingKeyMaterial("missing key material"),
    DisabledPlaceholder("disabled placeholder"),
}

enum class DescriptorWalletWorkflowState(val label: String) {
    NotStarted("not started"),
    ChoosingWalletOrigin("choosing wallet origin"),
    EnteringNonSecretProfileMetadata("entering non-secret profile metadata"),
    NeedsRiskAcknowledgement("needs risk acknowledgement"),
    NeedsBackupWarningAcknowledgement("needs backup warning acknowledgement"),
    ReadyToCreateProfileMetadata("ready to create profile metadata"),
    ProfileMetadataCreated("profile metadata created"),
    BlockedBySecretStorageDisabled("blocked by secret storage disabled"),
    BlockedByDescriptorValidationNotImplemented("blocked by descriptor validation not implemented"),
    BlockedByKeyMaterialNotImplemented("blocked by key material not implemented"),
    BlockedByMainnetDisabled("blocked by mainnet disabled"),
    Cancelled("cancelled"),
}

sealed interface DescriptorWalletWorkflowEvent {
    data object Start : DescriptorWalletWorkflowEvent
    data class ChooseOrigin(val intent: DescriptorWalletCreationIntent) : DescriptorWalletWorkflowEvent
    data object EnterMetadata : DescriptorWalletWorkflowEvent
    data class AcknowledgeRisk(val acknowledgement: DescriptorWalletRiskAcknowledgement) : DescriptorWalletWorkflowEvent
    data object ReviewProfileMetadata : DescriptorWalletWorkflowEvent
    data object Cancel : DescriptorWalletWorkflowEvent
}

enum class DescriptorWalletSpendPolicy(val label: String) {
    DisabledSecretStorage("spending disabled - secret storage unavailable"),
    DisabledDescriptorValidation("spending disabled - descriptor validation not implemented"),
    DisabledKeyMaterialMissing("spending disabled - key material not created"),
    WatchOnlyCannotSpend("watch-only profile cannot spend"),
    RequiresExternalSigner("requires external signer implementation"),
    DisabledPlaceholder("spending disabled placeholder"),
}

enum class DescriptorWalletBackupRequirement(val label: String) {
    NativeSeedAndDescriptorExportRequired("native seed and descriptor export required"),
    DescriptorMaterialBackupRequired("descriptor material backup required"),
    WatchOnlyDescriptorExportRecommended("watch-only descriptor export recommended"),
    ImportedKeySeparateBackupRequired("imported key separate backup required"),
    NostrIdentityKeyBackupRequired("Nostr identity key backup required"),
    ExternalSignerControlBackupRequired("external signer control backup required"),
    WalletMetadataBackupRecommended("wallet metadata backup recommended"),
}

enum class DescriptorWalletRiskAcknowledgement(val label: String) {
    IdentityKeyReuseWarningAcknowledged("Nostr identity-key reuse warning acknowledged"),
    SeparateImportedKeyBackupAcknowledged("separate imported-key backup acknowledged"),
    WatchOnlyCannotSpendAcknowledged("watch-only cannot spend acknowledged"),
    DescriptorMaterialNotStoredAcknowledged("descriptor material not stored acknowledged"),
    ExternalSignerControlAcknowledged("external signer control and backup acknowledged"),
}

enum class DescriptorWalletBlockingIssue(val label: String) {
    SecretStorageDisabled("secret storage disabled"),
    DescriptorValidationNotImplemented("descriptor validation not implemented"),
    KeyMaterialNotImplemented("key material not created"),
    MainnetDisabled("mainnet disabled"),
    MissingRiskAcknowledgement("required risk acknowledgement missing"),
    WatchOnlyCannotSpend("watch-only profile cannot spend"),
    ExternalSignerNotImplemented("external signer implementation missing"),
    BackendNotConnected("backend not connected"),
}

enum class DescriptorWalletCapability(
    val label: String,
    val receiveEnabled: Boolean = false,
    val spendEnabled: Boolean = false,
    val exportEnabled: Boolean = false,
    val psbtEnabled: Boolean = false,
    val signEnabled: Boolean = false,
    val broadcastEnabled: Boolean = false,
) {
    CanDisplayMetadata("can display metadata"),
    CanReceiveDisabled("receive disabled"),
    CanSpendDisabled("spend disabled"),
    CanExportDescriptorDisabled("descriptor export disabled"),
    CanBuildPsbtDisabled("PSBT build disabled"),
    RequiresSecretStorage("requires secure storage"),
    RequiresDescriptorImplementation("requires descriptor implementation"),
    RequiresExternalSigner("requires external signer"),
    RequiresBackendConnection("requires backend connection"),
    WatchOnlyCannotSpend("watch-only cannot spend"),
}

data class DescriptorWalletMetadataProfile(
    val id: DescriptorWalletProfileId,
    val label: DescriptorWalletProfileLabel,
    val origin: DescriptorWalletOrigin,
    val network: NetworkEnvironment,
    val profileStatus: DescriptorWalletProfileStatus,
    val workflowState: DescriptorWalletWorkflowState,
    val spendPolicy: DescriptorWalletSpendPolicy,
    val backupRequirement: DescriptorWalletBackupRequirement,
    val riskAcknowledgements: Set<DescriptorWalletRiskAcknowledgement>,
    val blockingIssues: Set<DescriptorWalletBlockingIssue>,
    val capabilities: Set<DescriptorWalletCapability>,
    val descriptorTextState: String,
    val keyMaterialState: String,
    val isSelected: Boolean,
    val isOperational: Boolean,
) {
    val containsWalletMaterial: Boolean
        get() = descriptorTextState != "DESCRIPTOR_TEXT_NOT_STORED" ||
            keyMaterialState != "KEY_MATERIAL_NOT_CREATED"

    val canReceiveNow: Boolean
        get() = capabilities.any { it.receiveEnabled }

    val canSpendNow: Boolean
        get() = capabilities.any { it.spendEnabled }

    val canExportDescriptorNow: Boolean
        get() = capabilities.any { it.exportEnabled }

    val canBuildPsbtNow: Boolean
        get() = capabilities.any { it.psbtEnabled }

    val canSignNow: Boolean
        get() = capabilities.any { it.signEnabled }

    val canBroadcastNow: Boolean
        get() = capabilities.any { it.broadcastEnabled }
}
