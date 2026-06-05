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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import com.libertasprimordium.skald.domain.onchain.BackendNotConfigured
import com.libertasprimordium.skald.domain.onchain.BackendProfileValidationResult
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendProfile
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendProfileId
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendSettingsState
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendTrustModel
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendType
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendValidator
import com.libertasprimordium.skald.domain.onchain.CoinControlPolicy
import com.libertasprimordium.skald.domain.onchain.CoinSelectionDraft
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletProfile
import com.libertasprimordium.skald.domain.onchain.EditableBitcoinBackendProfileInput
import com.libertasprimordium.skald.domain.onchain.HttpEndpoint
import com.libertasprimordium.skald.domain.onchain.OnChainRecoveryStatus
import com.libertasprimordium.skald.domain.onchain.OnChainWalletProfile
import com.libertasprimordium.skald.domain.onchain.PsbtWorkflowPlan
import com.libertasprimordium.skald.domain.onchain.TcpEndpoint
import com.libertasprimordium.skald.domain.portfolio.PortfolioSnapshot
import com.libertasprimordium.skald.domain.privacy.PrivacyRisk
import com.libertasprimordium.skald.domain.privacy.PrivacyRiskLevel
import com.libertasprimordium.skald.domain.quote.OperationQuote
import com.libertasprimordium.skald.domain.recovery.RecoveryStatus
import com.libertasprimordium.skald.settings.InMemorySettingsStorage
import com.libertasprimordium.skald.settings.PersistentSettingsRepository
import com.libertasprimordium.skald.settings.SettingsRepository
import com.libertasprimordium.skald.settings.SettingsWriteResult
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
fun SkaldApp(
    settingsRepository: SettingsRepository = PersistentSettingsRepository(InMemorySettingsStorage()),
) {
    val repository = remember { DemoPortfolioRepository() }
    val snapshot = remember { repository.loadPortfolioSnapshot() }
    val onChainProfile = remember { repository.onChainProfile() }
    val lightningConnectors = remember { repository.lightningConnectors() }
    val cashuMints = remember { repository.cashuMints() }
    val nostrIntents = remember { repository.nostrPaymentIntents() }
    val networks = remember { repository.developmentNetworks() }
    var selectedScreen by remember { mutableStateOf(AppScreen.Overview) }
    var backendSettings by remember { mutableStateOf(settingsRepository.loadBitcoinBackendSettings()) }
    var backendValidation by remember { mutableStateOf<BackendProfileValidationResult?>(null) }
    var backendMessage by remember {
        mutableStateOf("Backend profiles are local non-secret settings only. Connection testing is disabled.")
    }

    fun reloadBackendSettings() {
        backendSettings = settingsRepository.loadBitcoinBackendSettings()
    }

    fun saveBackendProfile(input: EditableBitcoinBackendProfileInput) {
        val validation = BitcoinBackendValidator.validate(input)
        backendValidation = validation
        val profile = validation.normalizedProfile
        if (profile == null) {
            backendMessage = "Profile not saved. Fix validation errors first."
            return
        }
        when (val result = settingsRepository.saveBitcoinBackendProfile(profile)) {
            is SettingsWriteResult.Saved -> {
                backendSettings = result.state
                backendMessage = "Saved non-secret backend profile. Connection testing and wallet sync remain disabled."
            }
            is SettingsWriteResult.Rejected -> {
                backendMessage = result.reason
                reloadBackendSettings()
            }
        }
    }

    fun selectBackendProfile(id: BitcoinBackendProfileId) {
        when (val result = settingsRepository.selectBitcoinBackendProfile(id)) {
            is SettingsWriteResult.Saved -> {
                backendSettings = result.state
                backendMessage = "Selected backend profile for future testnet-only use. No connection was attempted."
            }
            is SettingsWriteResult.Rejected -> {
                backendMessage = result.reason
                reloadBackendSettings()
            }
        }
    }

    fun deleteBackendProfile(id: BitcoinBackendProfileId) {
        when (val result = settingsRepository.deleteBitcoinBackendProfile(id)) {
            is SettingsWriteResult.Saved -> {
                backendSettings = result.state
                backendMessage = "Deleted backend profile. Selected profile was cleared if needed."
            }
            is SettingsWriteResult.Rejected -> {
                backendMessage = result.reason
                reloadBackendSettings()
            }
        }
    }

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
                            AppScreen.OnChain -> OnChainScreen(onChainProfile, backendSettings)
                            AppScreen.Lightning -> LightningScreen(lightningConnectors)
                            AppScreen.Cashu -> CashuScreen(cashuMints)
                            AppScreen.Nostr -> NostrScreen(nostrIntents)
                            AppScreen.Recovery -> RecoveryScreen(snapshot.recoveryStatus)
                            AppScreen.Nodes -> NodesScreen(
                                settings = backendSettings,
                                validation = backendValidation,
                                message = backendMessage,
                                onSaveProfile = ::saveBackendProfile,
                                onSelectProfile = ::selectBackendProfile,
                                onDeleteProfile = ::deleteBackendProfile,
                            )
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
private fun OnChainScreen(
    profile: OnChainWalletProfile,
    backendSettings: BitcoinBackendSettingsState,
) {
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
        BackendSettingsSummary(backendSettings)
        Spacer(Modifier.height(8.dp))
        Text("Architecture placeholders", color = SkaldWhite, fontWeight = FontWeight.Bold)
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
        title = backend.label,
        state = if (backend.isSelected) "selected - ${backend.status.label}" else backend.status.label,
    ) {
        DetailLine("Type", backend.type.label)
        DetailLine("Network", backend.network.label)
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
private fun BackendSettingsSummary(settings: BitcoinBackendSettingsState) {
    val selected = settings.selectedProfile
    InfoBlock(
        title = "Saved non-secret backend profiles",
        state = if (selected == null) "none selected" else "selected",
    ) {
        DetailLine("Saved profiles", settings.profiles.size.toString())
        DetailLine("Selected profile", selected?.label ?: "BACKEND_NOT_CONFIGURED")
        if (selected != null) {
            DetailLine("Selected endpoint", selected.endpointDisplay)
            DetailLine("Trust", selected.trustModel.label)
            DetailLine("Connection test", "CONNECTION_TEST_NOT_IMPLEMENTED")
        }
        Text(
            text = "Use Nodes to add, edit, select, or remove local non-secret backend profiles. No connection is attempted.",
            color = SkaldMutedText,
            lineHeight = 20.sp,
        )
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
private fun NodesScreen(
    settings: BitcoinBackendSettingsState,
    validation: BackendProfileValidationResult?,
    message: String,
    onSaveProfile: (EditableBitcoinBackendProfileInput) -> Unit,
    onSelectProfile: (BitcoinBackendProfileId) -> Unit,
    onDeleteProfile: (BitcoinBackendProfileId) -> Unit,
) {
    var form by remember { mutableStateOf(BackendSettingsFormState.blank()) }

    ScreenTitle("Nodes", "User-selected infrastructure only.")
    WarningStrip("No Skald-operated backend exists. Configure your own Bitcoin Core, Electrum, or Esplora endpoint.")
    WarningStrip("Credentials are intentionally out of scope in this pass. Do not paste RPC passwords, cookies, tokens, macaroons, runes, NWC secrets, or other secrets here.")
    SkaldCard(title = "Bitcoin backend settings", state = "non-secret local configuration") {
        Text(message, color = SkaldOrangeSoft, lineHeight = 20.sp)
        BackendSettingsSummary(settings)
        BackendProfileList(
            profiles = settings.profiles,
            onEdit = { profile -> form = profile.toFormState() },
            onSelect = onSelectProfile,
            onDelete = onDeleteProfile,
        )
        BackendProfileForm(
            form = form,
            validation = validation,
            onFormChanged = { form = it },
            onSave = { onSaveProfile(form.toInput()) },
            onReset = { form = BackendSettingsFormState.blank() },
        )
        LockedAction("CONNECTION_TEST_NOT_IMPLEMENTED - connection testing and wallet sync are not implemented yet.")
    }

    CardGrid {
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
        SkaldCard(title = "No managed infrastructure", state = "mandatory") {
            BulletList(
                listOf(
                    "No Skald-operated Bitcoin backend",
                    "No Skald-operated Payjoin directory or coordinator",
                    "No Skald-operated Lightning node, LSP, Cashu mint, relay, analytics, or notification service",
                    "Public backends can observe wallet queries. A user-owned node is preferred.",
                ),
            )
        }
    }
}

@Composable
private fun BackendProfileList(
    profiles: List<BitcoinBackendProfile>,
    onEdit: (BitcoinBackendProfile) -> Unit,
    onSelect: (BitcoinBackendProfileId) -> Unit,
    onDelete: (BitcoinBackendProfileId) -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Saved profiles", color = SkaldWhite, fontWeight = FontWeight.Bold)
        if (profiles.isEmpty()) {
            Text(
                text = "No backend profile is saved. Add a non-secret Bitcoin Core, Electrum, or Esplora profile below.",
                color = SkaldMutedText,
                lineHeight = 20.sp,
            )
        }
        profiles.forEach { profile ->
            InfoBlock(
                title = profile.label,
                state = if (profile.isSelected) "selected" else "saved",
            ) {
                DetailLine("Type", profile.type.label)
                DetailLine("Network", profile.network.label)
                DetailLine("Endpoint", profile.endpointDisplay)
                DetailLine("Trust", profile.trustModel.label)
                DetailLine("Connection test", "CONNECTION_TEST_NOT_IMPLEMENTED")
                if (profile.warnings.isNotEmpty()) {
                    profile.warnings.forEach { warning ->
                        Text(warning, color = SkaldWarning, lineHeight = 20.sp)
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    SkaldSmallButton(
                        label = if (profile.isSelected) "Selected" else "Select",
                        selected = profile.isSelected,
                        enabled = !profile.isSelected,
                        onClick = { onSelect(profile.id) },
                        modifier = Modifier.weight(1f),
                    )
                    SkaldSmallButton(
                        label = "Edit",
                        selected = false,
                        onClick = { onEdit(profile) },
                        modifier = Modifier.weight(1f),
                    )
                    SkaldSmallButton(
                        label = "Delete",
                        selected = false,
                        danger = true,
                        onClick = { onDelete(profile.id) },
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }
    }
}

@Composable
private fun BackendProfileForm(
    form: BackendSettingsFormState,
    validation: BackendProfileValidationResult?,
    onFormChanged: (BackendSettingsFormState) -> Unit,
    onSave: () -> Unit,
    onReset: () -> Unit,
) {
    InfoBlock(
        title = if (form.editingId == null) "Add backend profile" else "Edit backend profile",
        state = "non-secret fields only",
    ) {
        Text(
            text = "Connection testing and wallet sync are not implemented yet.",
            color = SkaldWarning,
            lineHeight = 20.sp,
        )
        BackendTypeSelector(
            selected = form.type,
            onSelected = { type ->
                onFormChanged(form.copy(type = type))
            },
        )
        DevelopmentNetworkSelector(
            selected = form.network,
            onSelected = { network -> onFormChanged(form.copy(network = network)) },
        )
        TrustModelSelector(
            selected = form.trustModel,
            onSelected = { trustModel -> onFormChanged(form.copy(trustModel = trustModel)) },
        )
        SkaldTextField(
            label = "Profile label",
            value = form.label,
            onValueChange = { onFormChanged(form.copy(label = it)) },
        )
        SkaldTextField(
            label = "Host",
            value = form.host,
            onValueChange = { onFormChanged(form.copy(host = it)) },
        )
        SkaldTextField(
            label = if (form.type == BitcoinBackendType.Esplora) "Port (optional)" else "Port",
            value = form.portText,
            onValueChange = { onFormChanged(form.copy(portText = it)) },
        )
        if (form.type == BitcoinBackendType.Esplora) {
            SkaldTextField(
                label = "Path (optional)",
                value = form.path,
                onValueChange = { onFormChanged(form.copy(path = it)) },
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            SkaldSmallButton(
                label = if (form.useTls) "TLS enabled" else "TLS disabled",
                selected = form.useTls,
                onClick = { onFormChanged(form.copy(useTls = !form.useTls)) },
                modifier = Modifier.weight(1f),
            )
            SkaldSmallButton(
                label = "Reset form",
                selected = false,
                onClick = onReset,
                modifier = Modifier.weight(1f),
            )
        }
        ValidationResultBlock(validation)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Button(
                onClick = onSave,
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = SkaldOrange,
                    contentColor = SkaldBlack,
                ),
                modifier = Modifier.weight(1f),
            ) {
                Text("Save profile", fontWeight = FontWeight.Black)
            }
            OutlinedButton(
                onClick = {},
                enabled = false,
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, SkaldDarkGray),
                colors = ButtonDefaults.outlinedButtonColors(
                    disabledContainerColor = SkaldBlack,
                    disabledContentColor = SkaldMutedText,
                ),
                modifier = Modifier.weight(1f),
            ) {
                Text("Test disabled")
            }
        }
    }
}

@Composable
private fun BackendTypeSelector(
    selected: BitcoinBackendType,
    onSelected: (BitcoinBackendType) -> Unit,
) {
    OptionGrid(
        title = "Backend type",
        options = BitcoinBackendType.entries,
        selected = selected,
        label = { it.label },
        onSelected = onSelected,
    )
}

@Composable
private fun DevelopmentNetworkSelector(
    selected: NetworkEnvironment,
    onSelected: (NetworkEnvironment) -> Unit,
) {
    OptionGrid(
        title = "Network",
        options = listOf(
            NetworkEnvironment.Regtest,
            NetworkEnvironment.Signet,
            NetworkEnvironment.Testnet,
            NetworkEnvironment.Testnet4,
        ),
        selected = selected,
        label = { it.label },
        onSelected = onSelected,
    )
    Text("Mainnet is disabled during development.", color = SkaldWarning, lineHeight = 20.sp)
}

@Composable
private fun TrustModelSelector(
    selected: BitcoinBackendTrustModel,
    onSelected: (BitcoinBackendTrustModel) -> Unit,
) {
    OptionGrid(
        title = "Trust model",
        options = listOf(
            BitcoinBackendTrustModel.UserOwnedNode,
            BitcoinBackendTrustModel.TrustedThirdParty,
            BitcoinBackendTrustModel.PublicBackend,
            BitcoinBackendTrustModel.Unknown,
        ),
        selected = selected,
        label = { it.label },
        onSelected = onSelected,
    )
}

@Composable
private fun <T> OptionGrid(
    title: String,
    options: List<T>,
    selected: T,
    label: (T) -> String,
    onSelected: (T) -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(title, color = SkaldWhite, fontWeight = FontWeight.Bold)
        options.chunked(2).forEach { rowOptions ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                rowOptions.forEach { option ->
                    SkaldSmallButton(
                        label = label(option),
                        selected = option == selected,
                        onClick = { onSelected(option) },
                        modifier = Modifier.weight(1f),
                    )
                }
                if (rowOptions.size == 1) {
                    Spacer(Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun SkaldTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = SkaldWhite,
            unfocusedTextColor = SkaldWhite,
            focusedContainerColor = SkaldBlack,
            unfocusedContainerColor = SkaldBlack,
            cursorColor = SkaldOrange,
            focusedBorderColor = SkaldOrange,
            unfocusedBorderColor = SkaldDarkGray,
            focusedLabelColor = SkaldOrange,
            unfocusedLabelColor = SkaldMutedText,
        ),
        modifier = Modifier.fillMaxWidth(),
    )
}

@Composable
private fun ValidationResultBlock(validation: BackendProfileValidationResult?) {
    if (validation == null) {
        Text(
            text = "Validation runs before save. Profiles are stored only after passing non-secret field checks.",
            color = SkaldMutedText,
            lineHeight = 20.sp,
        )
        return
    }

    if (validation.errors.isEmpty()) {
        Text("Validation passed for non-secret fields.", color = SkaldSuccess, fontWeight = FontWeight.Bold)
    } else {
        validation.errors.forEach { error ->
            Text(error.message, color = SkaldDanger, lineHeight = 20.sp)
        }
    }
    validation.warnings.forEach { warning ->
        Text(warning, color = SkaldWarning, lineHeight = 20.sp)
    }
}

@Composable
private fun SkaldSmallButton(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    danger: Boolean = false,
) {
    if (selected) {
        Button(
            onClick = onClick,
            enabled = enabled,
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = SkaldOrange,
                contentColor = SkaldBlack,
                disabledContainerColor = SkaldDarkGray,
                disabledContentColor = SkaldMutedText,
            ),
            modifier = modifier.height(44.dp),
        ) {
            Text(label, fontWeight = FontWeight.Bold)
        }
    } else {
        OutlinedButton(
            onClick = onClick,
            enabled = enabled,
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, if (danger) SkaldDanger else SkaldOrange),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = SkaldBlack,
                contentColor = if (danger) SkaldDanger else SkaldOrange,
                disabledContainerColor = SkaldBlack,
                disabledContentColor = SkaldMutedText,
            ),
            modifier = modifier.height(44.dp),
        ) {
            Text(label, fontWeight = FontWeight.Bold)
        }
    }
}

private data class BackendSettingsFormState(
    val editingId: BitcoinBackendProfileId?,
    val label: String,
    val type: BitcoinBackendType,
    val network: NetworkEnvironment,
    val host: String,
    val portText: String,
    val useTls: Boolean,
    val path: String,
    val trustModel: BitcoinBackendTrustModel,
) {
    fun toInput(): EditableBitcoinBackendProfileInput =
        EditableBitcoinBackendProfileInput(
            id = editingId,
            label = label,
            type = type,
            network = network,
            host = host,
            portText = portText,
            useTls = useTls,
            path = path,
            trustModel = trustModel,
        )

    companion object {
        fun blank(): BackendSettingsFormState =
            BackendSettingsFormState(
                editingId = null,
                label = "",
                type = BitcoinBackendType.BitcoinCoreRpc,
                network = NetworkEnvironment.Testnet4,
                host = "",
                portText = "",
                useTls = false,
                path = "",
                trustModel = BitcoinBackendTrustModel.UserOwnedNode,
            )
    }
}

private fun BitcoinBackendProfile.toFormState(): BackendSettingsFormState {
    val endpoint = endpoint
    return BackendSettingsFormState(
        editingId = id,
        label = label,
        type = type,
        network = network,
        host = when (endpoint) {
            BackendNotConfigured -> ""
            is HttpEndpoint -> endpoint.host
            is TcpEndpoint -> endpoint.host
        },
        portText = when (endpoint) {
            BackendNotConfigured -> ""
            is HttpEndpoint -> endpoint.port?.toString().orEmpty()
            is TcpEndpoint -> endpoint.port.toString()
        },
        useTls = when (endpoint) {
            BackendNotConfigured -> false
            is HttpEndpoint -> endpoint.useTls
            is TcpEndpoint -> endpoint.useTls
        },
        path = when (endpoint) {
            BackendNotConfigured -> ""
            is HttpEndpoint -> endpoint.path.orEmpty()
            is TcpEndpoint -> ""
        },
        trustModel = trustModel,
    )
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
