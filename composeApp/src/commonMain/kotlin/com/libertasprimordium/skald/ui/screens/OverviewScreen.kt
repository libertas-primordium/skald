package com.libertasprimordium.skald.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.libertasprimordium.skald.domain.portfolio.PortfolioSnapshot
import com.libertasprimordium.skald.ui.components.CardGrid
import com.libertasprimordium.skald.ui.components.PrivacyRiskList
import com.libertasprimordium.skald.ui.components.QuoteCard
import com.libertasprimordium.skald.ui.components.RailBalanceRow
import com.libertasprimordium.skald.ui.components.ScreenTitle
import com.libertasprimordium.skald.ui.components.SkaldCard
import com.libertasprimordium.skald.ui.components.WarningStrip
import com.libertasprimordium.skald.ui.components.toSatsText
import com.libertasprimordium.skald.ui.theme.SkaldMutedText
import com.libertasprimordium.skald.ui.theme.SkaldOrangeSoft
import com.libertasprimordium.skald.ui.theme.SkaldWhite

@Composable
fun OverviewScreen(snapshot: PortfolioSnapshot) {
    ScreenTitle("Overview", "Sats-first portfolio shell with rails kept separate.")
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
        snapshot.securitySummaries.forEach { summary ->
            SkaldCard(
                title = summary.title,
                state = summary.state,
            ) {
                Text(summary.detail, color = SkaldMutedText, lineHeight = 20.sp)
            }
        }
    }

    WarningStrip("All visible balances are static demo/testnet placeholders. No keys, addresses, transactions, invoices, proofs, or credentials exist.")

    snapshot.sampleQuotes.firstOrNull()?.let { quote ->
        QuoteCard(quote)
    }

    PrivacyRiskList(snapshot.privacyRisks)
}
