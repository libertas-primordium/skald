package com.libertasprimordium.skald.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.libertasprimordium.skald.domain.cashu.CashuMintProfile
import com.libertasprimordium.skald.ui.components.BulletList
import com.libertasprimordium.skald.ui.components.CardGrid
import com.libertasprimordium.skald.ui.components.ScreenTitle
import com.libertasprimordium.skald.ui.components.SkaldCard
import com.libertasprimordium.skald.ui.components.toSatsText
import com.libertasprimordium.skald.ui.theme.SkaldMutedText
import com.libertasprimordium.skald.ui.theme.SkaldWarning
import com.libertasprimordium.skald.ui.theme.SkaldWhite

@Composable
fun CashuScreen(mints: List<CashuMintProfile>) {
    ScreenTitle("Cashu", "Separate mint-trusted ecash rail.")
    SkaldCard(title = "MVP rail boundary", state = "Lightning only") {
        BulletList(
            listOf(
                "Lightning-to-Cashu only",
                "Cashu-to-Lightning only",
                "Manual mint selection",
                "Per-mint balances",
                "Mint capability matrix planned",
                "Exposure limits planned",
                "No direct on-chain Cashu flow for MVP",
            ),
        )
    }
    CardGrid {
        mints.forEach { mint ->
            SkaldCard(
                title = mint.label,
                state = if (mint.isDemoMint) "demo placeholder" else mint.status.label,
            ) {
                Text("${mint.balanceSats.toSatsText()} sats", color = SkaldWhite, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text("Trust model: ${mint.trustModel.label}", color = SkaldMutedText, lineHeight = 20.sp)
                Text(mint.recoverySummary, color = SkaldWarning, lineHeight = 20.sp)
                Spacer(Modifier.height(10.dp))
                BulletList(mint.plannedCapabilities)
            }
        }
    }
}
