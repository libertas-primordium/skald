package com.libertasprimordium.skald.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.libertasprimordium.skald.domain.core.BackendConnectionStatus
import com.libertasprimordium.skald.domain.core.NetworkEnvironment
import com.libertasprimordium.skald.domain.onchain.BackendNotConfigured
import com.libertasprimordium.skald.domain.onchain.BackendProfileValidationResult
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendConnectionTestFinding
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendConnectionTestFindingLevel
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendConnectionTestResult
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendProfile
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendProfileId
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendSettingsState
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendTrustModel
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendType
import com.libertasprimordium.skald.domain.onchain.EditableBitcoinBackendProfileInput
import com.libertasprimordium.skald.domain.onchain.HttpEndpoint
import com.libertasprimordium.skald.domain.onchain.TcpEndpoint
import com.libertasprimordium.skald.security.SecureStorageUiStatus
import com.libertasprimordium.skald.ui.components.BackendSettingsSummary
import com.libertasprimordium.skald.ui.components.BulletList
import com.libertasprimordium.skald.ui.components.CardGrid
import com.libertasprimordium.skald.ui.components.DetailLine
import com.libertasprimordium.skald.ui.components.InfoBlock
import com.libertasprimordium.skald.ui.components.LockedAction
import com.libertasprimordium.skald.ui.components.OptionGrid
import com.libertasprimordium.skald.ui.components.ScreenTitle
import com.libertasprimordium.skald.ui.components.SecureStorageInlineStatus
import com.libertasprimordium.skald.ui.components.SkaldCard
import com.libertasprimordium.skald.ui.components.SkaldSmallButton
import com.libertasprimordium.skald.ui.components.SkaldTextField
import com.libertasprimordium.skald.ui.components.WarningStrip
import com.libertasprimordium.skald.ui.theme.SkaldBlack
import com.libertasprimordium.skald.ui.theme.SkaldDanger
import com.libertasprimordium.skald.ui.theme.SkaldDarkGray
import com.libertasprimordium.skald.ui.theme.SkaldMutedText
import com.libertasprimordium.skald.ui.theme.SkaldOrange
import com.libertasprimordium.skald.ui.theme.SkaldOrangeSoft
import com.libertasprimordium.skald.ui.theme.SkaldSuccess
import com.libertasprimordium.skald.ui.theme.SkaldWarning
import com.libertasprimordium.skald.ui.theme.SkaldWhite

@Composable
fun NodesScreen(
    settings: BitcoinBackendSettingsState,
    validation: BackendProfileValidationResult?,
    message: String,
    connectionTestResult: BitcoinBackendConnectionTestResult?,
    secureStorageStatus: SecureStorageUiStatus,
    onRunSimulatedConnectionTest: () -> Unit,
    onSaveProfile: (EditableBitcoinBackendProfileInput) -> Unit,
    onSelectProfile: (BitcoinBackendProfileId) -> Unit,
    onDeleteProfile: (BitcoinBackendProfileId) -> Unit,
) {
    var form by remember { mutableStateOf(BackendSettingsFormState.blank()) }

    ScreenTitle("Nodes", "User-selected infrastructure only.")
    WarningStrip("No Skald-operated backend exists. Configure your own Bitcoin Core, Electrum, or Esplora endpoint.")
    WarningStrip("Credential storage is not implemented. Do not paste RPC passwords, cookie contents, macaroons, runes, NWC secrets, Phoenixd tokens, or other secrets into backend settings.")
    SkaldCard(title = "Bitcoin backend settings", state = "non-secret local configuration") {
        Text(message, color = SkaldOrangeSoft, lineHeight = 20.sp)
        SecureStorageInlineStatus(secureStorageStatus)
        BackendSettingsSummary(settings)
        BackendConnectionTestCard(
            selectedProfile = settings.selectedProfile,
            result = connectionTestResult,
            onRunSimulatedConnectionTest = onRunSimulatedConnectionTest,
        )
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
        LockedAction("REAL_NETWORK_DISABLED - real Bitcoin Core, Electrum, and Esplora connection tests are not implemented yet.")
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
                DetailLine("Connection test", "simulated validation only - real networking disabled")
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
            text = "Real connection testing and wallet sync are not implemented yet.",
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
                Text("Real test disabled")
            }
        }
    }
}

@Composable
private fun BackendConnectionTestCard(
    selectedProfile: BitcoinBackendProfile?,
    result: BitcoinBackendConnectionTestResult?,
    onRunSimulatedConnectionTest: () -> Unit,
) {
    InfoBlock(
        title = "Backend connection test",
        state = result?.state?.label ?: "simulated only",
    ) {
        Text(
            text = "Simulated only. No network connection was attempted.",
            color = SkaldWarning,
            lineHeight = 20.sp,
        )
        Text(
            text = "Real backend connection testing is not implemented yet.",
            color = SkaldMutedText,
            lineHeight = 20.sp,
        )
        if (selectedProfile == null) {
            Text(
                text = "Select or create a backend profile before running a simulated test.",
                color = SkaldWarning,
                lineHeight = 20.sp,
            )
        } else {
            DetailLine("Selected profile", selectedProfile.label)
            DetailLine("Backend type", selectedProfile.type.label)
            DetailLine("Endpoint", selectedProfile.endpointDisplay)
            DetailLine("Network", selectedProfile.network.label)
            if (selectedProfile.trustModel == BitcoinBackendTrustModel.PublicBackend ||
                selectedProfile.trustModel == BitcoinBackendTrustModel.TrustedThirdParty
            ) {
                Text(
                    text = "Public backends can observe wallet queries. Prefer a user-owned node.",
                    color = SkaldWarning,
                    lineHeight = 20.sp,
                )
            }
        }
        SkaldSmallButton(
            label = "Run simulated test",
            selected = true,
            onClick = onRunSimulatedConnectionTest,
            modifier = Modifier.fillMaxWidth(),
        )
        if (result != null) {
            DetailLine("Mode", result.mode.label)
            DetailLine("Result", result.state.label)
            DetailLine("Real network", if (result.noRealNetworkAttempted) "not attempted" else "blocked")
            if (result.blockingIssues.isNotEmpty()) {
                Text("Blockers", color = SkaldWarning, fontWeight = FontWeight.Bold)
                BulletList(result.blockingIssues.map { it.label })
            }
            if (result.warnings.isNotEmpty()) {
                Text("Warnings", color = SkaldWarning, fontWeight = FontWeight.Bold)
                result.warnings.forEach { warning ->
                    Text(warning, color = SkaldWarning, lineHeight = 20.sp)
                }
            }
            Text("Simulated findings", color = SkaldWhite, fontWeight = FontWeight.Bold)
            result.findings.forEach { finding ->
                ConnectionFindingLine(finding)
            }
        }
    }
}

@Composable
private fun ConnectionFindingLine(finding: BitcoinBackendConnectionTestFinding) {
    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        Text(
            text = "${finding.level.label.uppercase()} - ${finding.step.label}",
            color = connectionFindingColor(finding.level),
            fontWeight = FontWeight.Bold,
        )
        Text(finding.detail, color = SkaldMutedText, lineHeight = 20.sp)
    }
}

private fun connectionFindingColor(
    level: BitcoinBackendConnectionTestFindingLevel,
) = when (level) {
    BitcoinBackendConnectionTestFindingLevel.Passed -> SkaldSuccess
    BitcoinBackendConnectionTestFindingLevel.Warning -> SkaldWarning
    BitcoinBackendConnectionTestFindingLevel.Blocker -> SkaldDanger
    BitcoinBackendConnectionTestFindingLevel.Planned -> SkaldOrangeSoft
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
