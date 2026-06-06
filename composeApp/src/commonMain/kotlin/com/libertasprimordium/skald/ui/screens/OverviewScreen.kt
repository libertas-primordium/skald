package com.libertasprimordium.skald.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.libertasprimordium.skald.domain.portfolio.PortfolioSnapshot
import com.libertasprimordium.skald.domain.privacy.PrivacySyncStatus
import com.libertasprimordium.skald.ui.components.CardGrid
import com.libertasprimordium.skald.ui.components.DetailLine
import com.libertasprimordium.skald.ui.components.LockedAction
import com.libertasprimordium.skald.ui.components.PrivacyRiskList
import com.libertasprimordium.skald.ui.components.QuoteCard
import com.libertasprimordium.skald.ui.components.RailBalanceRow
import com.libertasprimordium.skald.ui.components.RailScreenHeader
import com.libertasprimordium.skald.ui.components.SkaldCard
import com.libertasprimordium.skald.ui.components.WarningStrip
import com.libertasprimordium.skald.ui.components.riskColor
import com.libertasprimordium.skald.ui.components.toSatsText
import com.libertasprimordium.skald.ui.navigation.AppScreen
import com.libertasprimordium.skald.ui.navigation.RailTabOptionId
import com.libertasprimordium.skald.ui.theme.SkaldMutedText
import com.libertasprimordium.skald.ui.theme.SkaldOrangeSoft
import com.libertasprimordium.skald.ui.theme.SkaldWhite

private const val RecoverySummaryTitle = "Recovery"

@Composable
fun OverviewScreen(
    snapshot: PortfolioSnapshot,
    privacySyncStatus: PrivacySyncStatus,
    onNavigate: (AppScreen) -> Unit,
) {
    var selectedOption by remember { mutableStateOf(RailTabOptionId.DefaultView) }

    RailScreenHeader(
        screen = AppScreen.Overview,
        subtitle = "Sats-first rail summary with advanced status behind options.",
        selectedOptionId = selectedOption,
        onOptionSelected = { selectedOption = it },
        onNavigate = onNavigate,
    )

    when (selectedOption) {
        RailTabOptionId.OverviewPrivacyAnalyzer -> {
            PrivacySyncStatusCard(privacySyncStatus)
            PrivacyRiskList(snapshot.privacyRisks)
        }
        RailTabOptionId.OverviewQuoteEngine -> snapshot.sampleQuotes.forEach { quote -> QuoteCard(quote) }
        RailTabOptionId.OverviewPhaseOneStatus -> PhaseOneStatusCard()
        else -> OverviewDefaultView(snapshot)
    }
}

@Composable
private fun PrivacySyncStatusCard(status: PrivacySyncStatus) {
    SkaldCard(title = status.title, state = status.state) {
        Text(status.summary, color = SkaldMutedText, lineHeight = 20.sp)
        DetailLine("Analysis path", if (status.canRunAnalysis) "enabled" else "disabled / status only")
        DetailLine(
            "Observation persistence",
            if (status.productionObservationPersistenceEnabled) "enabled" else "deferred until encrypted vault",
        )
        DetailLine("Tor transport", if (status.torTransportImplemented) "implemented" else "not implemented")
        status.findings.forEach { finding ->
            Text(
                text = "${finding.level.label.uppercase()} - ${finding.title}",
                color = riskColor(finding.level),
                fontWeight = FontWeight.Bold,
            )
            Text(finding.detail, color = SkaldMutedText, lineHeight = 20.sp)
        }
        LockedAction("PRIVACY_STATUS_ONLY - no production backend query, real cluster analysis, address derivation, persistence, Nostr parsing, signing, or broadcast exists.")
    }
}

@Composable
private fun OverviewDefaultView(snapshot: PortfolioSnapshot) {
    SkaldCard {
        Text("Total balance", color = SkaldMutedText, fontSize = 13.sp)
        Text(
            text = "${snapshot.totalBalanceSats.toSatsText()} sats",
            color = SkaldWhite,
            fontSize = 38.sp,
            fontWeight = FontWeight.Black,
        )
        Text(
            text = "Demo portfolio - ${snapshot.network.label} only",
            color = SkaldOrangeSoft,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(Modifier.height(14.dp))
        snapshot.railBalances.forEach { balance ->
            RailBalanceRow(
                label = balance.rail.label,
                sats = balance.sats,
                detail = "${balance.custodySummary} ${balance.recoverySummary}",
            )
        }
    }

    CardGrid {
        snapshot.securitySummaries
            .filterNot { summary -> summary.title == RecoverySummaryTitle }
            .forEach { summary ->
                SkaldCard(
                    title = summary.title,
                    state = summary.state,
                ) {
                    Text(summary.detail, color = SkaldMutedText, lineHeight = 20.sp)
                }
            }
    }

    WarningStrip("All visible balances are static demo/testnet placeholders. No keys, addresses, transactions, invoices, proofs, or credentials exist.")
}

@Composable
private fun PhaseOneStatusCard() {
    SkaldCard(title = "Phase 1 status", state = "non-operational") {
        Text(
            text = "Phase 1 completed architecture, metadata, simulated validation, and safety-boundary work only.",
            color = SkaldMutedText,
            lineHeight = 20.sp,
        )
        Text(
            text = "No real wallet, key, descriptor, address, transaction, PSBT, signing, broadcast, backend networking, secure storage, or mainnet behavior is enabled.",
            color = SkaldOrangeSoft,
            lineHeight = 20.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}
