package com.libertasprimordium.skald.domain.privacy

import com.libertasprimordium.skald.domain.core.WalletRail
import com.libertasprimordium.skald.domain.portfolio.PortfolioSnapshot

enum class PrivacyRiskLevel(val label: String) {
    Info("info"),
    Warning("warning"),
    Danger("danger"),
}

data class PrivacyRisk(
    val rail: WalletRail?,
    val level: PrivacyRiskLevel,
    val title: String,
    val detail: String,
)

interface PrivacyAnalyzer {
    fun analyzePortfolio(snapshot: PortfolioSnapshot): List<PrivacyRisk>
}

class PlaceholderPrivacyAnalyzer : PrivacyAnalyzer {
    override fun analyzePortfolio(snapshot: PortfolioSnapshot): List<PrivacyRisk> =
        listOf(
            PrivacyRisk(
                rail = WalletRail.OnChain,
                level = PrivacyRiskLevel.Warning,
                title = "Coin merging rules pending",
                detail = "Future sends must flag UTXO merging, toxic change, address reuse, backend leaks, and cross-wallet cluster linkage.",
            ),
            PrivacyRisk(
                rail = WalletRail.Nostr,
                level = PrivacyRiskLevel.Danger,
                title = "Nostr identity linkage pending",
                detail = "Nostr-derived payments must distinguish intentional public payments from recovery sweeps and cannot erase public history.",
            ),
            PrivacyRisk(
                rail = WalletRail.Cashu,
                level = PrivacyRiskLevel.Warning,
                title = "Mint trust exposure pending",
                detail = "Cashu balances must remain per-mint with exposure limits, melt fees, and recovery limits visible.",
            ),
            PrivacyRisk(
                rail = WalletRail.Lightning,
                level = PrivacyRiskLevel.Info,
                title = "Connector metadata pending",
                detail = "Remote node and LSP metadata exposure must be shown before any connector can send or receive.",
            ),
        )
}
