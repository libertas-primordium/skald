package com.libertasprimordium.skald.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendProfile
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendSettingsState
import com.libertasprimordium.skald.domain.onchain.CoinControlPolicy
import com.libertasprimordium.skald.domain.onchain.CoinSelectionDraft
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletProfile
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletProfileId
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletSettingsState
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletWorkflowReview
import com.libertasprimordium.skald.domain.onchain.EditableDescriptorWalletProfileInput
import com.libertasprimordium.skald.domain.onchain.OnChainRecoveryStatus
import com.libertasprimordium.skald.domain.onchain.OnChainWalletProfile
import com.libertasprimordium.skald.domain.onchain.PsbtWorkflowPlan
import com.libertasprimordium.skald.security.SecureStorageUiStatus
import com.libertasprimordium.skald.ui.components.BackendSettingsSummary
import com.libertasprimordium.skald.ui.components.BulletList
import com.libertasprimordium.skald.ui.components.DetailLine
import com.libertasprimordium.skald.ui.components.DisabledActionArea
import com.libertasprimordium.skald.ui.components.InfoBlock
import com.libertasprimordium.skald.ui.components.ScreenTitle
import com.libertasprimordium.skald.ui.components.SkaldCard
import com.libertasprimordium.skald.ui.components.WarningStrip
import com.libertasprimordium.skald.ui.components.riskColor
import com.libertasprimordium.skald.ui.theme.SkaldMutedText
import com.libertasprimordium.skald.ui.theme.SkaldWarning
import com.libertasprimordium.skald.ui.theme.SkaldWhite

@Composable
fun OnChainScreen(
    profile: OnChainWalletProfile,
    backendSettings: BitcoinBackendSettingsState,
    descriptorWalletSettings: DescriptorWalletSettingsState,
    descriptorWalletReview: DescriptorWalletWorkflowReview?,
    descriptorWalletMessage: String,
    secureStorageStatus: SecureStorageUiStatus,
    onSaveDescriptorWalletProfile: (EditableDescriptorWalletProfileInput) -> Unit,
    onSelectDescriptorWalletProfile: (DescriptorWalletProfileId) -> Unit,
    onDeleteDescriptorWalletProfile: (DescriptorWalletProfileId) -> Unit,
) {
    ScreenTitle("On-chain", "Phase 1 descriptor-native foundation model.")
    WarningStrip("No real keys, descriptors, addresses, UTXOs, PSBTs, signatures, or transactions are created in this pass.")
    SkaldCard(
        title = profile.label,
        state = profile.status.label,
    ) {
        BulletList(profile.plannedCapabilities)
    }

    DescriptorWalletProfilesSection(
        settings = descriptorWalletSettings,
        review = descriptorWalletReview,
        message = descriptorWalletMessage,
        secureStorageStatus = secureStorageStatus,
        onSaveProfile = onSaveDescriptorWalletProfile,
        onSelectProfile = onSelectDescriptorWalletProfile,
        onDeleteProfile = onDeleteDescriptorWalletProfile,
    )

    SkaldCard(title = "Descriptor wallet architecture templates", state = "static placeholders") {
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
fun OnChainRecoverySection(recovery: OnChainRecoveryStatus) {
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
