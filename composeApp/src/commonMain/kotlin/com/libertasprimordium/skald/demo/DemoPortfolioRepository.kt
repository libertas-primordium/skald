package com.libertasprimordium.skald.demo

import com.libertasprimordium.skald.domain.BackendConnectionStatus
import com.libertasprimordium.skald.domain.CashuMintProfile
import com.libertasprimordium.skald.domain.CashuMintTrustModel
import com.libertasprimordium.skald.domain.CoinControlPolicy
import com.libertasprimordium.skald.domain.DescriptorWalletStatus
import com.libertasprimordium.skald.domain.DisabledWalletAction
import com.libertasprimordium.skald.domain.LightningConnectorProfile
import com.libertasprimordium.skald.domain.LightningConnectorType
import com.libertasprimordium.skald.domain.LightningPermissionScope
import com.libertasprimordium.skald.domain.NetworkEnvironment
import com.libertasprimordium.skald.domain.NostrKeyMode
import com.libertasprimordium.skald.domain.NostrPaymentIntent
import com.libertasprimordium.skald.domain.OnChainWalletProfile
import com.libertasprimordium.skald.domain.PlaceholderPrivacyAnalyzer
import com.libertasprimordium.skald.domain.PlaceholderQuoteEngine
import com.libertasprimordium.skald.domain.PortfolioSnapshot
import com.libertasprimordium.skald.domain.PrivacyRiskLevel
import com.libertasprimordium.skald.domain.QuoteRequest
import com.libertasprimordium.skald.domain.RailBalance
import com.libertasprimordium.skald.domain.RecoveryItem
import com.libertasprimordium.skald.domain.RecoveryItemState
import com.libertasprimordium.skald.domain.RecoveryStatus
import com.libertasprimordium.skald.domain.SecuritySummary
import com.libertasprimordium.skald.domain.SetupComplexity
import com.libertasprimordium.skald.domain.UtxoSummary
import com.libertasprimordium.skald.domain.WalletRail

class DemoPortfolioRepository(
    private val quoteEngine: PlaceholderQuoteEngine = PlaceholderQuoteEngine(),
    private val privacyAnalyzer: PlaceholderPrivacyAnalyzer = PlaceholderPrivacyAnalyzer(),
) {
    fun loadPortfolioSnapshot(): PortfolioSnapshot {
        val recovery = recoveryStatus()
        val snapshotWithoutPrivacy = PortfolioSnapshot(
            totalBalanceSats = 123_456,
            network = NetworkEnvironment.Testnet4,
            isDemoData = true,
            railBalances = listOf(
                RailBalance(
                    rail = WalletRail.OnChain,
                    sats = 82_000,
                    custodySummary = "Descriptor wallet planned; no keys exist.",
                    recoverySummary = "Descriptor export not available yet.",
                    isDemoBalance = true,
                ),
                RailBalance(
                    rail = WalletRail.Lightning,
                    sats = 31_000,
                    custodySummary = "Connector required; no node credentials stored.",
                    recoverySummary = "Remote and channel-state backups not configured.",
                    isDemoBalance = true,
                ),
                RailBalance(
                    rail = WalletRail.Cashu,
                    sats = 10_456,
                    custodySummary = "No real mints configured.",
                    recoverySummary = "Mint recovery support not known.",
                    isDemoBalance = true,
                ),
            ),
            securitySummaries = listOf(
                SecuritySummary(
                    title = "On-chain",
                    state = "descriptor wallet planned",
                    detail = "Testnet/regtest only. Strict coin control, PSBT review, fee disclosure, and explicit signing/broadcast approval are required before implementation.",
                ),
                SecuritySummary(
                    title = "Lightning",
                    state = "connector required",
                    detail = "Alby Hub, Phoenixd, LND, and Core Lightning are modeled as connectors only. No credentials are accepted or stored.",
                ),
                SecuritySummary(
                    title = "Cashu",
                    state = "no mints configured",
                    detail = "Cashu remains a separate mint-trusted rail. Demo balances are not spendable.",
                ),
                SecuritySummary(
                    title = "Recovery",
                    state = "incomplete",
                    detail = "A seed phrase alone cannot restore every rail.",
                ),
            ),
            recoveryStatus = recovery,
            privacyRisks = emptyList(),
            sampleQuotes = listOf(
                quoteEngine.quote(
                    QuoteRequest(
                        sourceRail = WalletRail.OnChain,
                        destinationRail = WalletRail.Lightning,
                        amountSats = 50_000,
                        network = NetworkEnvironment.Testnet4,
                    ),
                ),
            ),
        )

        return snapshotWithoutPrivacy.copy(
            privacyRisks = privacyAnalyzer.analyzePortfolio(snapshotWithoutPrivacy),
        )
    }

    fun onChainProfile(): OnChainWalletProfile =
        OnChainWalletProfile(
            label = "Descriptor-native wallet foundation",
            status = DescriptorWalletStatus.NotCreated,
            coinControlPolicy = CoinControlPolicy(
                requiresManualInputReview = true,
                requiresFeeAndChangeReview = true,
                requiresExplicitSigningApproval = true,
                requiresExplicitBroadcastApproval = true,
                note = "Future sends must show selected UTXOs, labels, change, fee rate, backend, PSBT export, and privacy warnings before signing.",
            ),
            backendStatus = BackendConnectionStatus.NotConfigured,
            utxoSummary = UtxoSummary(
                confirmedUtxos = 0,
                confirmedSats = 0,
                unconfirmedSats = 0,
                note = "No real wallet, descriptor, address index, UTXO set, or backend sync exists in this scaffold.",
            ),
            plannedCapabilities = listOf(
                "Descriptor-native wallet architecture",
                "Strict coin control",
                "PSBT import/export",
                "Payjoin with user-selected endpoint or directory",
                "Silent payments later",
                "Nostr-derived Taproot key support",
                "User-selected Bitcoin Core, Electrum, or Esplora backend",
            ),
            disabledActions = listOf(
                DisabledWalletAction("Create descriptor wallet", "Requires descriptor wallet implementation"),
                DisabledWalletAction("Import descriptor", "Requires descriptor parser and recovery tracking"),
                DisabledWalletAction("Import npub/nsec", "Requires isolated Nostr key handling and privacy warnings"),
                DisabledWalletAction("Build PSBT", "Requires UTXO selection, fee quote, and explicit approval flow"),
            ),
        )

    fun lightningConnectors(): List<LightningConnectorProfile> =
        listOf(
            LightningConnectorProfile(
                type = LightningConnectorType.AlbyHubNwc,
                status = BackendConnectionStatus.NotConfigured,
                setupComplexity = SetupComplexity.Low,
                permissionModel = listOf(
                    LightningPermissionScope.ReadNode,
                    LightningPermissionScope.CreateInvoice,
                    LightningPermissionScope.SendWithAmountCap,
                ),
                plannedCapabilities = listOf("NWC permissions", "invoice creation", "capped payments", "Lightning zaps"),
                recoveryWarning = "Remote-node funds are not backed up by Skald Vault.",
            ),
            LightningConnectorProfile(
                type = LightningConnectorType.Phoenixd,
                status = BackendConnectionStatus.NotConfigured,
                setupComplexity = SetupComplexity.Medium,
                permissionModel = listOf(
                    LightningPermissionScope.ReadNode,
                    LightningPermissionScope.CreateInvoice,
                    LightningPermissionScope.SendWithAmountCap,
                ),
                plannedCapabilities = listOf("BOLT11", "BOLT12 where supported", "liquidity visibility", "server backup warnings"),
                recoveryWarning = "Phoenixd backups and credentials remain external until a secure connector is implemented.",
            ),
            LightningConnectorProfile(
                type = LightningConnectorType.Lnd,
                status = BackendConnectionStatus.NotConfigured,
                setupComplexity = SetupComplexity.High,
                permissionModel = listOf(
                    LightningPermissionScope.ReadNode,
                    LightningPermissionScope.CreateInvoice,
                    LightningPermissionScope.SendWithAmountCap,
                    LightningPermissionScope.ChannelOpen,
                    LightningPermissionScope.ChannelClose,
                    LightningPermissionScope.OnChainWithdraw,
                ),
                plannedCapabilities = listOf("macaroon permission display", "TLS transport", "channel controls", "on-chain withdraw warnings"),
                recoveryWarning = "LND channel state and macaroon authority are managed outside this scaffold.",
            ),
            LightningConnectorProfile(
                type = LightningConnectorType.CoreLightning,
                status = BackendConnectionStatus.NotConfigured,
                setupComplexity = SetupComplexity.Advanced,
                permissionModel = listOf(
                    LightningPermissionScope.ReadNode,
                    LightningPermissionScope.CreateInvoice,
                    LightningPermissionScope.SendWithAmountCap,
                    LightningPermissionScope.CustomPolicy,
                ),
                plannedCapabilities = listOf("rune permission display", "BOLT12 capability detection", "plugin-aware warnings"),
                recoveryWarning = "Core Lightning node backups and rune authority are external responsibilities.",
            ),
        )

    fun cashuMints(): List<CashuMintProfile> =
        listOf(
            CashuMintProfile(
                label = "Demo mint placeholder",
                balanceSats = 10_456,
                status = BackendConnectionStatus.DisabledPlaceholder,
                trustModel = CashuMintTrustModel.MintTrustedEcash,
                exposureLimitSats = null,
                plannedCapabilities = listOf(
                    "manual mint selection",
                    "Lightning-to-Cashu only",
                    "Cashu-to-Lightning only",
                    "per-mint balances",
                    "mint capability matrix",
                    "exposure limits",
                ),
                recoverySummary = "Cashu recovery depends on mint support and proof state.",
                isDemoMint = true,
            ),
        )

    fun nostrPaymentIntents(): List<NostrPaymentIntent> =
        listOf(
            NostrPaymentIntent(
                title = "Watch an npub-derived Taproot address",
                mode = NostrKeyMode.NpubWatchOnly,
                publicLinkage = "Observing funds sent to a public identity is identity-linked by design.",
                disabledReason = "Requires npub parser and descriptor watch-wallet implementation",
            ),
            NostrPaymentIntent(
                title = "Import an nsec-derived spend wallet",
                mode = NostrKeyMode.NsecImportedSpend,
                publicLinkage = "The same private key may control social identity and Bitcoin funds.",
                disabledReason = "Requires secure secret handling and isolated imported-key recovery state",
            ),
            NostrPaymentIntent(
                title = "Send public on-chain zap/payment",
                mode = NostrKeyMode.PublicIdentityPayment,
                publicLinkage = "Use only when public association with the selected profile is intentional.",
                disabledReason = "Requires quote, coin control, signing approval, broadcast approval, and event approval",
            ),
            NostrPaymentIntent(
                title = "Recover or sweep identity-linked funds",
                mode = NostrKeyMode.RecoverySweep,
                publicLinkage = "Recovery tools can reduce additional linkage but cannot erase public history.",
                disabledReason = "Requires scan, isolation strategy, privacy analyzer, and explicit sweep approval",
            ),
        )

    fun recoveryStatus(): RecoveryStatus =
        RecoveryStatus(
            headline = "Recovery Center incomplete",
            seedWarning = "A seed phrase alone cannot restore every rail.",
            items = listOf(
                RecoveryItem(
                    label = "App seed",
                    state = RecoveryItemState.NotCreated,
                    detail = "Native on-chain keys may be seed-restorable after a real seed system exists.",
                    riskLevel = PrivacyRiskLevel.Warning,
                ),
                RecoveryItem(
                    label = "On-chain descriptors",
                    state = RecoveryItemState.NotExported,
                    detail = "Descriptor export is required for watch-only and external signing workflows.",
                    riskLevel = PrivacyRiskLevel.Warning,
                ),
                RecoveryItem(
                    label = "Imported keys",
                    state = RecoveryItemState.SeparateBackupRequired,
                    detail = "Imported keys must be backed up separately.",
                    riskLevel = PrivacyRiskLevel.Danger,
                ),
                RecoveryItem(
                    label = "Lightning channel state",
                    state = RecoveryItemState.NotConfigured,
                    detail = "Lightning recovery requires current channel state or remote-node backups.",
                    riskLevel = PrivacyRiskLevel.Danger,
                ),
                RecoveryItem(
                    label = "Cashu recovery",
                    state = RecoveryItemState.MintDependent,
                    detail = "Cashu recovery depends on mint support and proof state.",
                    riskLevel = PrivacyRiskLevel.Warning,
                ),
                RecoveryItem(
                    label = "Remote node balances",
                    state = RecoveryItemState.ExternalBackupRequired,
                    detail = "Remote-node funds are not backed up by Skald Vault.",
                    riskLevel = PrivacyRiskLevel.Danger,
                ),
                RecoveryItem(
                    label = "Metadata backup",
                    state = RecoveryItemState.NotConfigured,
                    detail = "Encrypted metadata backup is planned; plaintext export is not implemented.",
                    riskLevel = PrivacyRiskLevel.Warning,
                ),
            ),
        )

    fun developmentNetworks(): List<NetworkEnvironment> =
        listOf(
            NetworkEnvironment.Regtest,
            NetworkEnvironment.Signet,
            NetworkEnvironment.Testnet,
            NetworkEnvironment.Testnet4,
            NetworkEnvironment.MainnetDisabled,
        )
}
