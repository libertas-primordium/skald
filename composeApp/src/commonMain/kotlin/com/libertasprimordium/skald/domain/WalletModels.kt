package com.libertasprimordium.skald.domain

enum class WalletRail(val label: String) {
    OnChain("On-chain"),
    Lightning("Lightning"),
    Cashu("Cashu"),
    Nostr("Nostr"),
    RemoteNode("Remote node"),
}

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

data class RailBalance(
    val rail: WalletRail,
    val sats: Long,
    val custodySummary: String,
    val recoverySummary: String,
    val isDemoBalance: Boolean,
)

data class PortfolioSnapshot(
    val totalBalanceSats: Long,
    val network: NetworkEnvironment,
    val isDemoData: Boolean,
    val railBalances: List<RailBalance>,
    val securitySummaries: List<SecuritySummary>,
    val recoveryStatus: RecoveryStatus,
    val privacyRisks: List<PrivacyRisk>,
    val sampleQuotes: List<OperationQuote>,
)

data class SecuritySummary(
    val title: String,
    val state: String,
    val detail: String,
)

enum class DescriptorWalletStatus(val label: String) {
    NotCreated("not created"),
    DescriptorPlanned("descriptor wallet planned"),
    WatchOnlyPlanned("watch-only planned"),
    ImportRequired("import required"),
    DisabledPlaceholder("disabled placeholder"),
}

data class OnChainWalletProfile(
    val label: String,
    val status: DescriptorWalletStatus,
    val coinControlPolicy: CoinControlPolicy,
    val backendStatus: BackendConnectionStatus,
    val utxoSummary: UtxoSummary,
    val plannedCapabilities: List<String>,
    val disabledActions: List<DisabledWalletAction>,
)

data class CoinControlPolicy(
    val requiresManualInputReview: Boolean,
    val requiresFeeAndChangeReview: Boolean,
    val requiresExplicitSigningApproval: Boolean,
    val requiresExplicitBroadcastApproval: Boolean,
    val note: String,
)

data class UtxoSummary(
    val confirmedUtxos: Int,
    val confirmedSats: Long,
    val unconfirmedSats: Long,
    val note: String,
)

data class DisabledWalletAction(
    val label: String,
    val reason: String,
)

enum class LightningConnectorType(val label: String) {
    AlbyHubNwc("Alby Hub / NWC"),
    Phoenixd("Phoenixd"),
    Lnd("LND"),
    CoreLightning("Core Lightning"),
    EmbeddedNodePlanned("Embedded node later"),
}

enum class SetupComplexity(val label: String) {
    Low("low"),
    Medium("medium"),
    High("high"),
    Advanced("advanced"),
}

enum class LightningPermissionScope(val label: String) {
    ReadNode("read node"),
    CreateInvoice("create invoices"),
    SendWithAmountCap("send with amount cap"),
    KeysendOrZap("keysend/zap send"),
    ChannelOpen("channel open"),
    ChannelClose("channel close"),
    PeerManagement("peer management"),
    OnChainWithdraw("on-chain withdraw"),
    AdminHighRisk("admin/high-risk"),
    CustomPolicy("custom policy"),
}

data class LightningConnectorProfile(
    val type: LightningConnectorType,
    val status: BackendConnectionStatus,
    val setupComplexity: SetupComplexity,
    val permissionModel: List<LightningPermissionScope>,
    val plannedCapabilities: List<String>,
    val recoveryWarning: String,
)

enum class CashuMintTrustModel(val label: String) {
    ManualMintSelection("manual mint selection"),
    MintTrustedEcash("mint-trusted ecash"),
    CapabilityClaimUnverified("capability claim unverified"),
}

data class CashuMintProfile(
    val label: String,
    val balanceSats: Long,
    val status: BackendConnectionStatus,
    val trustModel: CashuMintTrustModel,
    val exposureLimitSats: Long?,
    val plannedCapabilities: List<String>,
    val recoverySummary: String,
    val isDemoMint: Boolean,
)

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

enum class BackendConnectionStatus(val label: String) {
    NotConfigured("not configured"),
    Planned("planned"),
    DisabledPlaceholder("disabled placeholder"),
    UserConfiguredTestnet("user-configured testnet"),
    Unavailable("unavailable"),
}
