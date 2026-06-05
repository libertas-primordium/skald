package com.libertasprimordium.skald

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.libertasprimordium.skald.demo.DemoPortfolioRepository
import com.libertasprimordium.skald.domain.BackendConnectionStatus
import com.libertasprimordium.skald.domain.CashuMintProfile
import com.libertasprimordium.skald.domain.LightningConnectorProfile
import com.libertasprimordium.skald.domain.NetworkEnvironment
import com.libertasprimordium.skald.domain.NostrPaymentIntent
import com.libertasprimordium.skald.domain.OnChainWalletProfile
import com.libertasprimordium.skald.domain.OperationQuote
import com.libertasprimordium.skald.domain.PortfolioSnapshot
import com.libertasprimordium.skald.domain.PrivacyRisk
import com.libertasprimordium.skald.domain.PrivacyRiskLevel
import com.libertasprimordium.skald.domain.RecoveryStatus
import com.libertasprimordium.skald.ui.theme.SkaldBlack
import com.libertasprimordium.skald.ui.theme.SkaldCharcoal
import com.libertasprimordium.skald.ui.theme.SkaldDanger
import com.libertasprimordium.skald.ui.theme.SkaldDarkGray
import com.libertasprimordium.skald.ui.theme.SkaldMutedText
import com.libertasprimordium.skald.ui.theme.SkaldNearBlack
import com.libertasprimordium.skald.ui.theme.SkaldOrange
import com.libertasprimordium.skald.ui.theme.SkaldOrangeSoft
import com.libertasprimordium.skald.ui.theme.SkaldSuccess
import com.libertasprimordium.skald.ui.theme.SkaldTheme
import com.libertasprimordium.skald.ui.theme.SkaldWarning
import com.libertasprimordium.skald.ui.theme.SkaldWhite

@Composable
fun SkaldApp() {
    val repository = remember { DemoPortfolioRepository() }
    val snapshot = remember { repository.loadPortfolioSnapshot() }
    val onChainProfile = remember { repository.onChainProfile() }
    val lightningConnectors = remember { repository.lightningConnectors() }
    val cashuMints = remember { repository.cashuMints() }
    val nostrIntents = remember { repository.nostrPaymentIntents() }
    val networks = remember { repository.developmentNetworks() }
    var selectedScreen by remember { mutableStateOf(AppScreen.Overview) }

    SkaldTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(SkaldBlack, SkaldNearBlack, SkaldCharcoal),
                    ),
                ),
        ) {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
            ) {
                val compact = maxWidth < 760.dp
                Column(
                    verticalArrangement = Arrangement.spacedBy(18.dp),
                    modifier = Modifier.fillMaxSize(),
                ) {
                    SkaldHeader(compact = compact)
                    ScreenTabs(
                        selected = selectedScreen,
                        onSelected = { selectedScreen = it },
                    )
                    WarningStrip(
                        text = "No real wallet functionality exists in this scaffold. Demo portfolio - testnet/regtest/signet only.",
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                            .padding(bottom = 40.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp),
                    ) {
                        when (selectedScreen) {
                            AppScreen.Overview -> OverviewScreen(snapshot)
                            AppScreen.OnChain -> OnChainScreen(onChainProfile)
                            AppScreen.Lightning -> LightningScreen(lightningConnectors)
                            AppScreen.Cashu -> CashuScreen(cashuMints)
                            AppScreen.Nostr -> NostrScreen(nostrIntents)
                            AppScreen.Recovery -> RecoveryScreen(snapshot.recoveryStatus)
                            AppScreen.Nodes -> NodesScreen()
                            AppScreen.Settings -> SettingsScreen(networks)
                        }
                    }
                }
            }
        }
    }
}

private enum class AppScreen(val label: String) {
    Overview("Overview"),
    OnChain("On-chain"),
    Lightning("Lightning"),
    Cashu("Cashu"),
    Nostr("Nostr"),
    Recovery("Recovery"),
    Nodes("Nodes"),
    Settings("Settings"),
}

@Composable
private fun SkaldHeader(compact: Boolean) {
    if (compact) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Wordmark()
            StatusPill("DEVELOPMENT TESTNET")
        }
    } else {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Wordmark()
            Spacer(Modifier.weight(1f))
            StatusPill("DEVELOPMENT TESTNET")
        }
    }
}

@Composable
private fun Wordmark() {
    Column {
        Text(
            text = "sk\u00e4ld",
            color = SkaldWhite,
            fontSize = 36.sp,
            fontWeight = FontWeight.Black,
        )
        Text(
            text = "Skald Vault",
            color = SkaldMutedText,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
        )
    }
}

@Composable
private fun ScreenTabs(
    selected: AppScreen,
    onSelected: (AppScreen) -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
    ) {
        AppScreen.entries.forEach { screen ->
            if (screen == selected) {
                Button(
                    onClick = { onSelected(screen) },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = SkaldOrange,
                        contentColor = SkaldBlack,
                    ),
                    modifier = Modifier.widthIn(min = 112.dp),
                ) {
                    Text(screen.label, fontWeight = FontWeight.Bold)
                }
            } else {
                OutlinedButton(
                    onClick = { onSelected(screen) },
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, SkaldOrange),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = SkaldBlack,
                        contentColor = SkaldOrange,
                    ),
                    modifier = Modifier.widthIn(min = 112.dp),
                ) {
                    Text(screen.label, fontWeight = FontWeight.Medium)
                }
            }
        }
    }
}

@Composable
private fun OverviewScreen(snapshot: PortfolioSnapshot) {
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

@Composable
private fun OnChainScreen(profile: OnChainWalletProfile) {
    ScreenTitle("On-chain", "Descriptor-native architecture placeholder.")
    SkaldCard(
        title = profile.label,
        state = profile.status.label,
    ) {
        Text(
            text = "No real keys, descriptors, addresses, UTXOs, PSBTs, signatures, or transactions are created in this pass.",
            color = SkaldWarning,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 20.sp,
        )
        Spacer(Modifier.height(12.dp))
        BulletList(profile.plannedCapabilities)
    }

    SkaldCard(title = "Coin control invariant", state = "mandatory") {
        BulletList(
            listOf(
                "Manual input selection review: ${profile.coinControlPolicy.requiresManualInputReview}",
                "Fee and change review: ${profile.coinControlPolicy.requiresFeeAndChangeReview}",
                "Explicit signing approval: ${profile.coinControlPolicy.requiresExplicitSigningApproval}",
                "Explicit broadcast approval: ${profile.coinControlPolicy.requiresExplicitBroadcastApproval}",
            ),
        )
        Text(profile.coinControlPolicy.note, color = SkaldMutedText, lineHeight = 20.sp)
    }

    SkaldCard(title = "Backend", state = profile.backendStatus.label) {
        Text(profile.utxoSummary.note, color = SkaldMutedText, lineHeight = 20.sp)
    }

    DisabledActionArea(
        title = "Locked action area",
        actions = profile.disabledActions.map { it.label to it.reason },
    )
}

@Composable
private fun LightningScreen(connectors: List<LightningConnectorProfile>) {
    ScreenTitle("Lightning", "Connector-oriented architecture in setup-ease order.")
    WarningStrip("No Lightning credentials are accepted or stored. Background service, liquidity, BOLT12, and LSP warnings are planned only.")
    CardGrid {
        connectors.forEach { connector ->
            SkaldCard(
                title = connector.type.label,
                state = "${connector.status.label} - setup ${connector.setupComplexity.label}",
            ) {
                Text("Permission model placeholder", color = SkaldOrangeSoft, fontWeight = FontWeight.SemiBold)
                Text(
                    connector.permissionModel.joinToString(", ") { it.label },
                    color = SkaldMutedText,
                    lineHeight = 20.sp,
                )
                Spacer(Modifier.height(10.dp))
                BulletList(connector.plannedCapabilities)
                Text(connector.recoveryWarning, color = SkaldWarning, lineHeight = 20.sp)
            }
        }
    }
}

@Composable
private fun CashuScreen(mints: List<CashuMintProfile>) {
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

@Composable
private fun NostrScreen(intents: List<NostrPaymentIntent>) {
    ScreenTitle("Nostr", "Identity-linked payment and recovery intent.")
    WarningStrip("Nostr-derived Bitcoin payments are identity-linked by design. Use public npub payments only when public association is intentional. Recovery tools can reduce additional linkage but cannot erase public history.")
    CardGrid {
        intents.forEach { intent ->
            SkaldCard(
                title = intent.title,
                state = intent.mode.label,
            ) {
                Text(intent.publicLinkage, color = SkaldWarning, lineHeight = 20.sp)
                Spacer(Modifier.height(10.dp))
                LockedAction(intent.disabledReason)
            }
        }
    }
}

@Composable
private fun RecoveryScreen(recovery: RecoveryStatus) {
    ScreenTitle("Recovery", "First-class recovery status model.")
    WarningStrip(recovery.seedWarning)
    SkaldCard(title = recovery.headline, state = "required before real wallet flows") {
        BulletList(
            listOf(
                "Native on-chain keys may be seed-restorable.",
                "Lightning recovery requires current channel state or remote-node backups.",
                "Cashu recovery depends on mint support and proof state.",
                "Imported keys must be backed up separately.",
                "Remote-node funds are not backed up by Skald Vault.",
            ),
        )
    }
    CardGrid {
        recovery.items.forEach { item ->
            SkaldCard(
                title = item.label,
                state = item.state.label,
            ) {
                Text(item.detail, color = riskColor(item.riskLevel), lineHeight = 20.sp)
            }
        }
    }
}

@Composable
private fun NodesScreen() {
    ScreenTitle("Nodes", "User-selected infrastructure only.")
    WarningStrip("Skald Vault does not operate wallet infrastructure. Configure your own nodes, mints, LSPs, relays, and Payjoin directories.")
    CardGrid {
        SkaldCard(title = "Bitcoin backend", state = BackendConnectionStatus.NotConfigured.label) {
            BulletList(
                listOf(
                    "Bitcoin Core planned",
                    "Electrum planned",
                    "Esplora planned",
                    "Backend trust/privacy class planned",
                    "No Skald-operated backend",
                ),
            )
        }
        SkaldCard(title = "Remote Lightning", state = BackendConnectionStatus.NotConfigured.label) {
            BulletList(
                listOf(
                    "Alby Hub / NWC planned",
                    "Phoenixd planned",
                    "LND planned",
                    "Core Lightning planned",
                    "Explicit permission model required",
                ),
            )
        }
        SkaldCard(title = "Privacy transport", state = "planned") {
            BulletList(
                listOf(
                    "Tor/proxy configuration planned",
                    "User-selected Nostr relays planned",
                    "User-selected Payjoin directories planned",
                    "No notification relay or analytics service",
                ),
            )
        }
    }
}

@Composable
private fun SettingsScreen(networks: List<NetworkEnvironment>) {
    ScreenTitle("Settings", "Non-functional settings placeholders.")
    CardGrid {
        SkaldCard(title = "Network", state = "regtest/signet/testnet only") {
            networks.forEach { network ->
                val state = if (network.isDevelopmentSelectable) "available for development" else "disabled"
                RailBalanceRow(network.label, 0, state)
            }
        }
        SkaldCard(title = "Theme", state = "Skald dark") {
            BulletList(
                listOf(
                    "Black background",
                    "Orange accents",
                    "White text on black",
                    "Black text on orange controls",
                    "Subtle dark gray gradients",
                ),
            )
        }
        SkaldCard(title = "Security", state = "planned") {
            BulletList(
                listOf(
                    "Security lock planned",
                    "Backup/export planned",
                    "Advanced warnings always enabled",
                    "Developer/testnet mode enabled",
                    "No plaintext secret storage implemented",
                ),
            )
        }
    }
}

@Composable
private fun ScreenTitle(title: String, subtitle: String) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(title, color = SkaldWhite, fontSize = 28.sp, fontWeight = FontWeight.Black)
        Text(subtitle, color = SkaldMutedText, fontSize = 14.sp)
    }
}

@Composable
private fun SkaldCard(
    title: String? = null,
    state: String? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    Surface(
        color = Color.Transparent,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, SkaldDarkGray),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .background(
                    Brush.linearGradient(
                        colors = listOf(SkaldCharcoal, SkaldNearBlack, SkaldDarkGray),
                    ),
                )
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            if (title != null || state != null) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.Top,
                ) {
                    if (title != null) {
                        Text(
                            text = title,
                            color = SkaldWhite,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f),
                        )
                    }
                    if (state != null) {
                        StatusPill(state)
                    }
                }
            }
            content()
        }
    }
}

@Composable
private fun CardGrid(content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        content = content,
    )
}

@Composable
private fun RailBalanceRow(label: String, sats: Long, detail: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, SkaldDarkGray, RoundedCornerShape(8.dp))
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(Modifier.weight(1f)) {
            Text(label, color = SkaldWhite, fontWeight = FontWeight.Bold)
            Text(detail, color = SkaldMutedText, fontSize = 13.sp, lineHeight = 18.sp)
        }
        if (sats > 0) {
            Text("${sats.toSatsText()} sats", color = SkaldOrangeSoft, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun BulletList(items: List<String>) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        items.forEach { item ->
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("-", color = SkaldOrange)
                Text(item, color = SkaldMutedText, lineHeight = 20.sp)
            }
        }
    }
}

@Composable
private fun DisabledActionArea(
    title: String,
    actions: List<Pair<String, String>>,
) {
    SkaldCard(title = title, state = "not implemented yet") {
        actions.forEach { (label, reason) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, SkaldDarkGray, RoundedCornerShape(8.dp))
                    .padding(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(label, color = SkaldWhite, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                LockedAction(reason)
            }
        }
    }
}

@Composable
private fun LockedAction(reason: String) {
    Surface(
        color = SkaldBlack,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, SkaldOrange),
    ) {
        Column(Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
            Text("Not implemented yet", color = SkaldOrange, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            Text(reason, color = SkaldMutedText, fontSize = 12.sp, lineHeight = 16.sp)
        }
    }
}

@Composable
private fun WarningStrip(text: String) {
    Surface(
        color = SkaldBlack,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, SkaldOrange),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = text,
            color = SkaldOrangeSoft,
            lineHeight = 20.sp,
            modifier = Modifier.padding(12.dp),
        )
    }
}

@Composable
private fun StatusPill(text: String) {
    Surface(
        color = SkaldOrange,
        shape = RoundedCornerShape(8.dp),
    ) {
        Text(
            text = text,
            color = SkaldBlack,
            fontSize = 12.sp,
            fontWeight = FontWeight.Black,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
        )
    }
}

@Composable
private fun QuoteCard(quote: OperationQuote) {
    SkaldCard(title = "Quote Engine", state = if (quote.isPlaceholder) "placeholder" else "active") {
        Text(
            "${quote.operation}: ${quote.sourceRail.label} to ${quote.destinationRail.label}",
            color = SkaldWhite,
            fontWeight = FontWeight.Bold,
        )
        Text("Amount in: ${quote.amountInSats.toSatsText()} sats", color = SkaldMutedText)
        Text("Amount out: ${quote.amountOutSats.toSatsText()} sats", color = SkaldMutedText)
        Text("Fees: not estimated", color = SkaldWarning)
        Text("Expiry: ${quote.expiry}", color = SkaldMutedText)
        Text("${quote.trustBoundary.title}: ${quote.trustBoundary.detail}", color = SkaldMutedText, lineHeight = 20.sp)
        BulletList(quote.failureModes)
    }
}

@Composable
private fun PrivacyRiskList(risks: List<PrivacyRisk>) {
    SkaldCard(title = "Privacy Analyzer", state = "placeholder") {
        risks.forEach { risk ->
            Text(
                text = "${risk.level.label.uppercase()} - ${risk.title}",
                color = riskColor(risk.level),
                fontWeight = FontWeight.Bold,
            )
            Text(risk.detail, color = SkaldMutedText, lineHeight = 20.sp)
        }
    }
}

private fun riskColor(level: PrivacyRiskLevel): Color =
    when (level) {
        PrivacyRiskLevel.Info -> SkaldSuccess
        PrivacyRiskLevel.Warning -> SkaldWarning
        PrivacyRiskLevel.Danger -> SkaldDanger
    }

private fun Long.toSatsText(): String =
    toString()
        .reversed()
        .chunked(3)
        .joinToString(",")
        .reversed()
