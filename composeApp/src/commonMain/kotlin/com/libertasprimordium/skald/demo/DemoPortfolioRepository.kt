package com.libertasprimordium.skald.demo

import com.libertasprimordium.skald.domain.cashu.CashuMintProfile
import com.libertasprimordium.skald.domain.cashu.CashuMintTrustModel
import com.libertasprimordium.skald.domain.core.BackendConnectionStatus
import com.libertasprimordium.skald.domain.core.NetworkEnvironment
import com.libertasprimordium.skald.domain.core.WalletRail
import com.libertasprimordium.skald.domain.lightning.LightningConnectorProfile
import com.libertasprimordium.skald.domain.lightning.LightningConnectorType
import com.libertasprimordium.skald.domain.lightning.LightningPermissionScope
import com.libertasprimordium.skald.domain.lightning.SetupComplexity
import com.libertasprimordium.skald.domain.nostr.NostrKeyMode
import com.libertasprimordium.skald.domain.nostr.NostrPaymentIntent
import com.libertasprimordium.skald.domain.onchain.OnChainWalletProfile
import com.libertasprimordium.skald.domain.portfolio.PortfolioSnapshot
import com.libertasprimordium.skald.domain.portfolio.RailBalance
import com.libertasprimordium.skald.domain.portfolio.SecuritySummary
import com.libertasprimordium.skald.domain.privacy.PlaceholderPrivacyAnalyzer
import com.libertasprimordium.skald.domain.quote.PlaceholderQuoteEngine
import com.libertasprimordium.skald.domain.quote.QuoteRequest
import com.libertasprimordium.skald.domain.recovery.RecoveryStatus

class DemoPortfolioRepository(
    private val onChainRepository: DemoOnChainRepository = DemoOnChainRepository(),
    private val recoveryRepository: DemoRecoveryRepository = DemoRecoveryRepository(onChainRepository),
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
        onChainRepository.loadOnChainProfile()

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
        recoveryRepository.recoveryStatus()

    fun developmentNetworks(): List<NetworkEnvironment> =
        listOf(
            NetworkEnvironment.Regtest,
            NetworkEnvironment.Signet,
            NetworkEnvironment.Testnet,
            NetworkEnvironment.Testnet4,
            NetworkEnvironment.MainnetDisabled,
        )
}
