package com.libertasprimordium.skald

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import com.libertasprimordium.skald.domain.cashu.CashuMintProfile
import com.libertasprimordium.skald.domain.core.BackendConnectionStatus
import com.libertasprimordium.skald.domain.core.NetworkEnvironment
import com.libertasprimordium.skald.domain.lightning.LightningConnectorProfile
import com.libertasprimordium.skald.domain.nostr.NostrPaymentIntent
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendProfile
import com.libertasprimordium.skald.domain.onchain.CoinControlPolicy
import com.libertasprimordium.skald.domain.onchain.CoinSelectionDraft
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletProfile
import com.libertasprimordium.skald.domain.onchain.OnChainRecoveryStatus
import com.libertasprimordium.skald.domain.onchain.OnChainWalletProfile
import com.libertasprimordium.skald.domain.onchain.PsbtWorkflowPlan
import com.libertasprimordium.skald.domain.portfolio.PortfolioSnapshot
import com.libertasprimordium.skald.domain.privacy.PrivacyRisk
import com.libertasprimordium.skald.domain.privacy.PrivacyRiskLevel
import com.libertasprimordium.skald.domain.quote.OperationQuote
import com.libertasprimordium.skald.domain.recovery.RecoveryStatus
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
                )
                .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Top)),
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
                    SkaldHeader(
                        compact = compact,
                        selected = selectedScreen,
                        onSelected = { selectedScreen = it },
                    )
                    PrimaryNavigation(
                        selected = selectedScreen,
                        compact = compact,
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

private val primaryScreens = listOf(
    AppScreen.Overview,
    AppScreen.OnChain,
    AppScreen.Lightning,
    AppScreen.Cashu,
)

private val menuScreens = listOf(
    AppScreen.Nostr,
    AppScreen.Recovery,
    AppScreen.Nodes,
    AppScreen.Settings,
)

@Composable
private fun SkaldHeader(
    compact: Boolean,
    selected: AppScreen,
    onSelected: (AppScreen) -> Unit,
) {
    if (compact) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.Top,
            ) {
                Wordmark(Modifier.weight(1f))
                OverflowMenuNavigation(
                    selected = selected,
                    onSelected = onSelected,
                )
            }
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
            OverflowMenuNavigation(
                selected = selected,
                onSelected = onSelected,
            )
        }
    }
}

@Composable
private fun Wordmark(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
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
private fun PrimaryNavigation(
    selected: AppScreen,
    compact: Boolean,
    onSelected: (AppScreen) -> Unit,
) {
    if (compact) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            primaryScreens.chunked(2).forEach { rowScreens ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    rowScreens.forEach { screen ->
                        PrimaryTabButton(
                            screen = screen,
                            selected = selected == screen,
                            onSelected = onSelected,
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp),
                        )
                    }
                    if (rowScreens.size == 1) {
                        Spacer(Modifier.weight(1f))
                    }
                }
            }
        }
    } else {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            primaryScreens.forEach { screen ->
                PrimaryTabButton(
                    screen = screen,
                    selected = selected == screen,
                    onSelected = onSelected,
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                )
            }
        }
    }
}

@Composable
private fun PrimaryTabButton(
    screen: AppScreen,
    selected: Boolean,
    onSelected: (AppScreen) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (selected) {
        Button(
            onClick = { onSelected(screen) },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = SkaldOrange,
                contentColor = SkaldBlack,
            ),
            modifier = modifier,
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
            modifier = modifier,
        ) {
            Text(screen.label, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
private fun OverflowMenuNavigation(
    selected: AppScreen,
    onSelected: (AppScreen) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    val activeMenuScreen = selected.takeIf { it in menuScreens }
    val buttonLabel = activeMenuScreen?.let { "Menu: ${it.label}" } ?: "Menu"

    Box {
        OutlinedButton(
            onClick = { expanded = true },
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, SkaldOrange),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = SkaldBlack,
                contentColor = SkaldOrange,
            ),
            modifier = Modifier.widthIn(min = 96.dp),
        ) {
            Text(buttonLabel, fontWeight = FontWeight.Bold)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = SkaldCharcoal,
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, SkaldOrange),
        ) {
            menuScreens.forEach { screen ->
                val isActive = selected == screen
                DropdownMenuItem(
                    text = {
                        Text(
                            text = screen.label,
                            color = if (isActive) SkaldBlack else SkaldWhite,
                            fontWeight = if (isActive) FontWeight.Black else FontWeight.Medium,
                        )
                    },
                    onClick = {
                        onSelected(screen)
                        expanded = false
                    },
                    modifier = Modifier.background(if (isActive) SkaldOrange else SkaldCharcoal),
                )
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
    ScreenTitle("On-chain", "Phase 1 descriptor-native foundation model.")
    WarningStrip("No real keys, descriptors, addresses, UTXOs, PSBTs, signatures, or transactions are created in this pass.")
    SkaldCard(
        title = profile.label,
        state = profile.status.label,
    ) {
        BulletList(profile.plannedCapabilities)
    }

    SkaldCard(title = "Descriptor wallets", state = "demo/planned profiles") {
        profile.descriptorWallets.forEach { wallet ->
            DescriptorWalletBlock(wallet)
        }
    }

    SkaldCard(title = "Backend configuration", state = "no default endpoint") {
        profile.backendProfiles.forEach { backend ->
            BackendProfileBlock(backend)
        }
    }

    CoinControlSection(
        draft = profile.coinSelectionDraft,
        policy = profile.coinControlPolicy,
    )

    PsbtWorkflowSection(profile.psbtWorkflow)

    OnChainRecoverySection(profile.recoveryStatus)

    DisabledActionArea(
        title = "Locked on-chain actions",
        actions = profile.disabledActions.map { it.label to it.reason },
    )
}

@Composable
private fun DescriptorWalletBlock(wallet: DescriptorWalletProfile) {
    InfoBlock(
        title = wallet.label.value,
        state = wallet.status.label,
    ) {
        DetailLine("Origin", wallet.origin.label)
        DetailLine("Script policy", wallet.scriptPolicy.label)
        DetailLine("Descriptor", wallet.descriptorDisplay.redactedDescriptor)
        DetailLine("Backup", wallet.backupStatus.label)
        DetailLine("Export", wallet.exportState.label)
        DetailLine("Spending", if (wallet.canSpend) "available after implementation" else "disabled or unavailable")
        wallet.importedKeyPolicy?.let { DetailLine("Imported-key policy", it.label) }
        wallet.watchOnlyPolicy?.let { DetailLine("Watch-only policy", it.label) }
        if (wallet.warnings.isNotEmpty()) {
            BulletList(wallet.warnings)
        }
    }
}

@Composable
private fun BackendProfileBlock(backend: BitcoinBackendProfile) {
    InfoBlock(
        title = backend.type.label,
        state = backend.status.label,
    ) {
        DetailLine("Endpoint", backend.endpointDisplay)
        DetailLine("Trust", backend.trustModel.label)
        DetailLine("Privacy", backend.privacyLevel.label)
        DetailLine("Credential policy", backend.credentialPolicy.label)
        DetailLine("Endpoint validation", backend.endpointValidationState.label)
        DetailLine("Default endpoint", if (backend.noDefaultEndpoint) "none" else "configured")
        BulletList(backend.capabilities.map { it.label })
        if (backend.warnings.isNotEmpty()) {
            backend.warnings.forEach { warning ->
                Text(warning, color = SkaldWarning, lineHeight = 20.sp)
            }
        }
    }
}

@Composable
private fun CoinControlSection(
    draft: CoinSelectionDraft,
    policy: CoinControlPolicy,
) {
    SkaldCard(title = "UTXO / coin-control model", state = "strict manual review") {
        BulletList(
            listOf(
                "Manual input selection review: ${policy.requiresManualInputReview}",
                "Fee and change review: ${policy.requiresFeeAndChangeReview}",
                "Explicit signing approval: ${policy.requiresExplicitSigningApproval}",
                "Explicit broadcast approval: ${policy.requiresExplicitBroadcastApproval}",
                "Cross-wallet selection: ${policy.crossWalletSelectionPolicy.label}",
            ),
        )
        Text(policy.note, color = SkaldMutedText, lineHeight = 20.sp)
        InfoBlock(
            title = "Demo coin-control draft - no real UTXOs",
            state = if (draft.isExecutable) "executable" else "disabled placeholder",
        ) {
            DetailLine("Intent", draft.intent.label)
            DetailLine("Approval state", draft.review.approvalState.label)
            draft.review.selectedInputs.forEach { selected ->
                DetailLine(
                    selected.utxo.label.value,
                    "${selected.utxo.outPoint.txidDisplay}:${selected.utxo.outPoint.voutDisplay} - ${selected.utxo.spendabilityState.label}",
                )
            }
            draft.review.outputPlan.forEach { output ->
                DetailLine(output.label, output.addressDisplay)
            }
            DetailLine("Change", "${draft.review.changePlan.state.label} - ${draft.review.changePlan.warning}")
            DetailLine("Fee", draft.review.feePlaceholder.reason)
            draft.review.privacyWarnings.forEach { warning ->
                Text(
                    text = "${warning.level.label.uppercase()} - ${warning.title}",
                    color = riskColor(warning.level),
                    fontWeight = FontWeight.Bold,
                )
                Text(warning.detail, color = SkaldMutedText, lineHeight = 20.sp)
            }
        }
    }
}

@Composable
private fun PsbtWorkflowSection(workflow: PsbtWorkflowPlan) {
    SkaldCard(title = "PSBT workflow", state = workflow.currentState.label) {
        BulletList(
            listOf(
                "Draft",
                "Coin review",
                "Fee review",
                "PSBT construction",
                "External signing",
                "Final review",
                "Broadcast",
            ),
        )
        DetailLine("Placeholder PSBT", workflow.review.draft.placeholderPsbt)
        DetailLine("Signing policy", workflow.signingPolicy.label)
        DetailLine("Broadcast policy", workflow.broadcastPolicy.label)
        DetailLine("Export/import", if (workflow.exportImportPlanned) "planned, not implemented" else "unavailable")
        workflow.review.failureModes.forEach { mode ->
            Text(mode.label, color = SkaldWarning, lineHeight = 20.sp)
        }
    }
}

@Composable
private fun OnChainRecoverySection(recovery: OnChainRecoveryStatus) {
    SkaldCard(title = "On-chain recovery requirements", state = recovery.headline) {
        recovery.checklistItems.forEach { item ->
            InfoBlock(
                title = item.artifactType.label,
                state = item.state.label,
            ) {
                DetailLine("Requirement", item.requirement.label)
                Text(item.detail, color = SkaldMutedText, lineHeight = 20.sp)
                if (item.blockingIssues.isNotEmpty()) {
                    BulletList(item.blockingIssues.map { it.label })
                }
            }
        }
        Text("Recovery transitions", color = SkaldWhite, fontWeight = FontWeight.Bold)
        BulletList(recovery.transitions.map { it.label })
    }
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
    recovery.onChainRecoveryStatus?.let { onChainRecovery ->
        OnChainRecoverySection(onChainRecovery)
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
private fun InfoBlock(
    title: String,
    state: String,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, SkaldDarkGray, RoundedCornerShape(8.dp))
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.Top,
        ) {
            Text(title, color = SkaldWhite, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
            Text(state, color = SkaldOrangeSoft, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
        content()
    }
}

@Composable
private fun DetailLine(label: String, detail: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.Top,
    ) {
        Text(label, color = SkaldWhite, fontSize = 13.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.42f))
        Text(detail, color = SkaldMutedText, fontSize = 13.sp, lineHeight = 18.sp, modifier = Modifier.weight(0.58f))
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
