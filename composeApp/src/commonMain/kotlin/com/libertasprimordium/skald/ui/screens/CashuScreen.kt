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
import com.libertasprimordium.skald.domain.cashu.CashuMintProfile
import com.libertasprimordium.skald.ui.components.BulletList
import com.libertasprimordium.skald.ui.components.CardGrid
import com.libertasprimordium.skald.ui.components.DetailLine
import com.libertasprimordium.skald.ui.components.InfoBlock
import com.libertasprimordium.skald.ui.components.RailScreenHeader
import com.libertasprimordium.skald.ui.components.ScreenTitle
import com.libertasprimordium.skald.ui.components.SkaldCard
import com.libertasprimordium.skald.ui.components.toSatsText
import com.libertasprimordium.skald.ui.navigation.AppScreen
import com.libertasprimordium.skald.ui.navigation.RailTabOptionId
import com.libertasprimordium.skald.ui.theme.SkaldMutedText
import com.libertasprimordium.skald.ui.theme.SkaldWarning
import com.libertasprimordium.skald.ui.theme.SkaldWhite

@Composable
fun CashuScreen(
    mints: List<CashuMintProfile>,
    onNavigate: (AppScreen) -> Unit,
) {
    var selectedOption by remember { mutableStateOf(RailTabOptionId.DefaultView) }

    RailScreenHeader(
        screen = AppScreen.Cashu,
        subtitle = "Mint balances and exposure placeholders.",
        selectedOptionId = selectedOption,
        onOptionSelected = { selectedOption = it },
        onNavigate = onNavigate,
    )

    when (selectedOption) {
        RailTabOptionId.CashuMintSettings -> CashuMintSettingsPlaceholder()
        RailTabOptionId.CashuCapabilityMatrix -> CashuCapabilityMatrix(mints)
        RailTabOptionId.CashuExposureLimits -> CashuExposureDetails(mints)
        RailTabOptionId.CashuMintMeltPlanning -> CashuMintMeltPlanning()
        RailTabOptionId.CashuRecoveryWarnings -> CashuRecoveryWarnings(mints)
        RailTabOptionId.CashuTrustPolicy -> CashuTrustPolicy()
        else -> CashuDefaultView(mints)
    }
}

@Composable
private fun CashuDefaultView(mints: List<CashuMintProfile>) {
    SkaldCard(title = "MVP rail boundary", state = "Lightning only") {
        BulletList(
            listOf(
                "Lightning-to-Cashu only",
                "Cashu-to-Lightning only",
                "Manual mint selection",
                "Per-mint balances",
                "Mint and melt are unavailable in this scaffold",
                "Cashu proofs and seed material are not stored",
                "No direct on-chain Cashu flow for MVP",
            ),
        )
    }
    CardGrid {
        if (mints.isEmpty()) {
            SkaldCard(title = "Cashu mints", state = "none configured") {
                Text("Add mint remains a placeholder in Cashu options. No mint URL, proof, seed, or credential is accepted.", color = SkaldMutedText, lineHeight = 20.sp)
            }
        }
        mints.forEach { mint ->
            SkaldCard(
                title = mint.label,
                state = if (mint.isDemoMint) "demo placeholder" else mint.status.label,
            ) {
                Text("${mint.balanceSats.toSatsText()} sats", color = SkaldWhite, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text("Trust model: ${mint.trustModel.label}", color = SkaldMutedText, lineHeight = 20.sp)
                Text("Exposure limit: ${mint.exposureLimitSats?.let { "${it.toSatsText()} sats" } ?: "not configured"}", color = SkaldMutedText, lineHeight = 20.sp)
                Text(mint.recoverySummary, color = SkaldWarning, lineHeight = 20.sp)
                Spacer(Modifier.height(10.dp))
                BulletList(mint.plannedCapabilities)
            }
        }
    }
}

@Composable
private fun CashuMintSettingsPlaceholder() {
    SkaldCard(title = "Mint settings", state = "not implemented") {
        Text("Add/edit mint configuration is a placeholder only. No mint URL, proof, seed, credential, melt, or quote flow is stored.", color = SkaldMutedText, lineHeight = 20.sp)
    }
}

@Composable
private fun CashuCapabilityMatrix(mints: List<CashuMintProfile>) {
    ScreenTitle("Cashu capabilities", "Mint claims remain unverified placeholders.")
    CardGrid {
        mints.forEach { mint ->
            SkaldCard(title = mint.label, state = if (mint.isDemoMint) "demo placeholder" else mint.status.label) {
                BulletList(mint.plannedCapabilities)
                Text("No mint API call or capability probe exists.", color = SkaldWarning, lineHeight = 20.sp)
            }
        }
    }
}

@Composable
private fun CashuExposureDetails(mints: List<CashuMintProfile>) {
    SkaldCard(title = "Exposure limits", state = "planned") {
        mints.forEach { mint ->
            InfoBlock(
                title = mint.label,
                state = mint.trustModel.label,
            ) {
                DetailLine("Balance", "${mint.balanceSats.toSatsText()} sats")
                DetailLine("Exposure limit", mint.exposureLimitSats?.let { "${it.toSatsText()} sats" } ?: "not configured")
                DetailLine("Demo mint", mint.isDemoMint.toString())
            }
        }
        Text("Per-mint exposure limits are not enforced yet.", color = SkaldWarning, lineHeight = 20.sp)
    }
}

@Composable
private fun CashuMintMeltPlanning() {
    SkaldCard(title = "Mint/melt planning", state = "disabled") {
        BulletList(
            listOf(
                "Minting requires a Lightning quote and explicit user approval.",
                "Melting requires fee disclosure and mint trust warnings.",
                "No Lightning-to-Cashu or Cashu-to-Lightning operation exists yet.",
                "No Cashu proofs are created, accepted, stored, or spent.",
            ),
        )
    }
}

@Composable
private fun CashuRecoveryWarnings(mints: List<CashuMintProfile>) {
    SkaldCard(title = "Cashu recovery warnings", state = "mint-dependent") {
        mints.forEach { mint ->
            Text(mint.label, color = SkaldWhite, fontWeight = FontWeight.Bold)
            Text(mint.recoverySummary, color = SkaldWarning, lineHeight = 20.sp)
            Spacer(Modifier.height(8.dp))
        }
        Text("A Skald app seed alone does not restore Cashu proof state.", color = SkaldWarning, lineHeight = 20.sp)
    }
}

@Composable
private fun CashuTrustPolicy() {
    SkaldCard(title = "Trustless claim policy", state = "claim requires verification") {
        Text(
            text = "Mint trust, fees, melt limits, and any trustless or un-ruggable claims must be shown as mint-specific claims and verified before users rely on them.",
            color = SkaldMutedText,
            lineHeight = 20.sp,
        )
        Text("No Skald-operated mint or managed mint default exists.", color = SkaldWarning, lineHeight = 20.sp)
    }
}
