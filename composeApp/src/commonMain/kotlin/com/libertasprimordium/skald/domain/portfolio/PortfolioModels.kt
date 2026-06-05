package com.libertasprimordium.skald.domain.portfolio

import com.libertasprimordium.skald.domain.core.NetworkEnvironment
import com.libertasprimordium.skald.domain.core.WalletRail
import com.libertasprimordium.skald.domain.privacy.PrivacyRisk
import com.libertasprimordium.skald.domain.quote.OperationQuote
import com.libertasprimordium.skald.domain.recovery.RecoveryStatus

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
