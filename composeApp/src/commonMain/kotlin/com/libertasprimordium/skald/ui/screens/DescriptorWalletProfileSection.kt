package com.libertasprimordium.skald.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.libertasprimordium.skald.domain.core.NetworkEnvironment
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletBlockingIssue
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletCreationIntent
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletMetadataProfile
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletOrigin
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletProfileId
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletRiskAcknowledgement
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletSettingsState
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletWorkflow
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletWorkflowReview
import com.libertasprimordium.skald.domain.onchain.EditableDescriptorWalletProfileInput
import com.libertasprimordium.skald.security.SecureStorageUiStatus
import com.libertasprimordium.skald.ui.components.BulletList
import com.libertasprimordium.skald.ui.components.DetailLine
import com.libertasprimordium.skald.ui.components.InfoBlock
import com.libertasprimordium.skald.ui.components.LockedAction
import com.libertasprimordium.skald.ui.components.OptionGrid
import com.libertasprimordium.skald.ui.components.SkaldCard
import com.libertasprimordium.skald.ui.components.SkaldSmallButton
import com.libertasprimordium.skald.ui.components.SkaldTextField
import com.libertasprimordium.skald.ui.theme.SkaldDanger
import com.libertasprimordium.skald.ui.theme.SkaldMutedText
import com.libertasprimordium.skald.ui.theme.SkaldOrangeSoft
import com.libertasprimordium.skald.ui.theme.SkaldSuccess
import com.libertasprimordium.skald.ui.theme.SkaldWarning
import com.libertasprimordium.skald.ui.theme.SkaldWhite

@Composable
fun DescriptorWalletProfilesSection(
    settings: DescriptorWalletSettingsState,
    review: DescriptorWalletWorkflowReview?,
    message: String,
    secureStorageStatus: SecureStorageUiStatus,
    onSaveProfile: (EditableDescriptorWalletProfileInput) -> Unit,
    onSelectProfile: (DescriptorWalletProfileId) -> Unit,
    onDeleteProfile: (DescriptorWalletProfileId) -> Unit,
) {
    var form by remember { mutableStateOf(DescriptorWalletProfileFormState.blank()) }

    SkaldCard(title = "Descriptor wallet profiles", state = "metadata only") {
        Text(message, color = SkaldOrangeSoft, lineHeight = 20.sp)
        Text(
            text = "This creates profile metadata only. No keys, descriptors, addresses, or wallet funds are created.",
            color = SkaldWarning,
            lineHeight = 20.sp,
        )
        DetailLine("Secret storage", secureStorageStatus.state)
        DescriptorWalletProfileList(
            profiles = settings.profiles,
            onEdit = { profile -> form = profile.toFormState() },
            onSelect = onSelectProfile,
            onDelete = onDeleteProfile,
        )
        DescriptorWalletProfileForm(
            form = form,
            review = review,
            onFormChanged = { form = it },
            onSave = { onSaveProfile(form.toInput()) },
            onReset = { form = DescriptorWalletProfileFormState.blank() },
        )
    }
}

@Composable
private fun DescriptorWalletProfileList(
    profiles: List<DescriptorWalletMetadataProfile>,
    onEdit: (DescriptorWalletMetadataProfile) -> Unit,
    onSelect: (DescriptorWalletProfileId) -> Unit,
    onDelete: (DescriptorWalletProfileId) -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Saved metadata profiles", color = SkaldWhite, fontWeight = FontWeight.Bold)
        if (profiles.isEmpty()) {
            Text(
                text = "No descriptor wallet metadata profile is saved. Add a non-operational planned wallet profile below.",
                color = SkaldMutedText,
                lineHeight = 20.sp,
            )
        }
        profiles.forEach { profile ->
            InfoBlock(
                title = profile.label.value,
                state = if (profile.isSelected) "selected metadata" else profile.profileStatus.label,
            ) {
                DetailLine("Origin", profile.origin.label)
                DetailLine("Network", profile.network.label)
                DetailLine("Workflow", profile.workflowState.label)
                DetailLine("Spend policy", profile.spendPolicy.label)
                DetailLine("Backup", profile.backupRequirement.label)
                DetailLine("Descriptor", profile.descriptorTextState)
                DetailLine("Key material", profile.keyMaterialState)
                DetailLine("Operational", profile.isOperational.toString())
                if (profile.riskAcknowledgements.isNotEmpty()) {
                    BulletList(profile.riskAcknowledgements.map { it.label })
                }
                if (profile.blockingIssues.isNotEmpty()) {
                    Text("Blocking issues", color = SkaldWarning, fontWeight = FontWeight.Bold)
                    BulletList(profile.blockingIssues.map { it.label })
                }
                Text("Current capabilities", color = SkaldWhite, fontWeight = FontWeight.Bold)
                BulletList(profile.capabilities.map { it.label })
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
private fun DescriptorWalletProfileForm(
    form: DescriptorWalletProfileFormState,
    review: DescriptorWalletWorkflowReview?,
    onFormChanged: (DescriptorWalletProfileFormState) -> Unit,
    onSave: () -> Unit,
    onReset: () -> Unit,
) {
    InfoBlock(
        title = if (form.editingId == null) "Add wallet profile metadata" else "Edit wallet profile metadata",
        state = "non-secret fields only",
    ) {
        WalletIntentSelector(
            selected = form.intent,
            onSelected = { intent ->
                val allowedAcknowledgements = DescriptorWalletWorkflow.requiredAcknowledgementsFor(intent)
                onFormChanged(
                    form.copy(
                        intent = intent,
                        acknowledgements = form.acknowledgements.intersect(allowedAcknowledgements),
                    ),
                )
            },
        )
        DevelopmentNetworkSelector(
            selected = form.network,
            onSelected = { network -> onFormChanged(form.copy(network = network)) },
        )
        SkaldTextField(
            label = "Profile label",
            value = form.label,
            onValueChange = { onFormChanged(form.copy(label = it)) },
        )
        LockedFutureInputs()
        RequiredAcknowledgementControls(
            intent = form.intent,
            acknowledgements = form.acknowledgements,
            onToggle = { acknowledgement ->
                val updated = if (form.acknowledgements.contains(acknowledgement)) {
                    form.acknowledgements - acknowledgement
                } else {
                    form.acknowledgements + acknowledgement
                }
                onFormChanged(form.copy(acknowledgements = updated))
            },
        )
        WorkflowReviewBlock(review)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            SkaldSmallButton(
                label = "Save metadata",
                selected = true,
                onClick = onSave,
                modifier = Modifier.weight(1f),
            )
            SkaldSmallButton(
                label = "Reset form",
                selected = false,
                onClick = onReset,
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Composable
private fun WalletIntentSelector(
    selected: DescriptorWalletCreationIntent,
    onSelected: (DescriptorWalletCreationIntent) -> Unit,
) {
    OptionGrid(
        title = "Planned wallet origin",
        options = DescriptorWalletCreationIntent.entries,
        selected = selected,
        label = { it.shortLabel },
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
private fun LockedFutureInputs() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Future material inputs", color = SkaldWhite, fontWeight = FontWeight.Bold)
        LockedAction("Descriptor text input: DESCRIPTOR_VALIDATION_NOT_IMPLEMENTED")
        LockedAction("Nostr npub input: descriptor watch implementation not available")
        LockedAction("Nostr nsec input: SECRET_STORAGE_DISABLED")
    }
}

@Composable
private fun RequiredAcknowledgementControls(
    intent: DescriptorWalletCreationIntent,
    acknowledgements: Set<DescriptorWalletRiskAcknowledgement>,
    onToggle: (DescriptorWalletRiskAcknowledgement) -> Unit,
) {
    val required = DescriptorWalletWorkflow.requiredAcknowledgementsFor(intent)
    if (required.isEmpty()) {
        Text(
            text = "No extra acknowledgement is required to save metadata for this planned profile. It still remains non-operational.",
            color = SkaldMutedText,
            lineHeight = 20.sp,
        )
        return
    }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Required acknowledgements", color = SkaldWhite, fontWeight = FontWeight.Bold)
        required.forEach { acknowledgement ->
            val acknowledged = acknowledgements.contains(acknowledgement)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = acknowledgement.label,
                    color = if (acknowledged) SkaldSuccess else SkaldWarning,
                    lineHeight = 20.sp,
                    modifier = Modifier.weight(1f),
                )
                SkaldSmallButton(
                    label = if (acknowledged) "Acknowledged" else "Acknowledge",
                    selected = acknowledged,
                    onClick = { onToggle(acknowledgement) },
                )
            }
        }
    }
}

@Composable
private fun WorkflowReviewBlock(review: DescriptorWalletWorkflowReview?) {
    if (review == null) {
        Text(
            text = "Validation runs before save. Metadata profiles are stored only after required warnings are acknowledged.",
            color = SkaldMutedText,
            lineHeight = 20.sp,
        )
        return
    }

    Text("Workflow state: ${review.state.label}", color = SkaldOrangeSoft, fontWeight = FontWeight.Bold)
    if (review.errors.isEmpty()) {
        Text("Metadata validation passed. Profile remains non-operational.", color = SkaldSuccess, fontWeight = FontWeight.Bold)
    } else {
        review.errors.forEach { error ->
            Text(error.message, color = SkaldDanger, lineHeight = 20.sp)
        }
    }
    if (review.requiredAcknowledgements.isNotEmpty()) {
        Text("Required acknowledgements", color = SkaldWhite, fontWeight = FontWeight.Bold)
        BulletList(review.requiredAcknowledgements.map { it.label })
    }
    review.profile?.let { profile ->
        if (profile.blockingIssues.isNotEmpty()) {
            Text("Operational blockers", color = SkaldWarning, fontWeight = FontWeight.Bold)
            BulletList(profile.blockingIssues.map { it.label })
        }
    }
    review.warnings.forEach { warning ->
        Text(warning, color = SkaldWarning, lineHeight = 20.sp)
    }
}

private data class DescriptorWalletProfileFormState(
    val editingId: DescriptorWalletProfileId?,
    val label: String,
    val intent: DescriptorWalletCreationIntent,
    val network: NetworkEnvironment,
    val acknowledgements: Set<DescriptorWalletRiskAcknowledgement>,
) {
    fun toInput(): EditableDescriptorWalletProfileInput =
        EditableDescriptorWalletProfileInput(
            id = editingId,
            label = label,
            intent = intent,
            network = network,
            acknowledgements = acknowledgements,
        )

    companion object {
        fun blank(): DescriptorWalletProfileFormState =
            DescriptorWalletProfileFormState(
                editingId = null,
                label = "",
                intent = DescriptorWalletCreationIntent.NativeDescriptorFromAppSeed,
                network = NetworkEnvironment.Testnet4,
                acknowledgements = emptySet(),
            )
    }
}

private fun DescriptorWalletMetadataProfile.toFormState(): DescriptorWalletProfileFormState =
    DescriptorWalletProfileFormState(
        editingId = id,
        label = label.value,
        intent = origin.toCreationIntent(),
        network = network,
        acknowledgements = riskAcknowledgements,
    )

private fun DescriptorWalletOrigin.toCreationIntent(): DescriptorWalletCreationIntent =
    when (this) {
        DescriptorWalletOrigin.NativeAppSeed,
        DescriptorWalletOrigin.NativeAppSeedPlanned,
        -> DescriptorWalletCreationIntent.NativeDescriptorFromAppSeed
        DescriptorWalletOrigin.ImportedDescriptor,
        DescriptorWalletOrigin.ImportedDescriptorPlanned,
        -> DescriptorWalletCreationIntent.ImportedDescriptor
        DescriptorWalletOrigin.ImportedWatchOnlyDescriptor,
        DescriptorWalletOrigin.WatchOnlyDescriptorPlanned,
        -> DescriptorWalletCreationIntent.WatchOnlyDescriptor
        DescriptorWalletOrigin.ImportedSingleKey,
        DescriptorWalletOrigin.ImportedSingleKeyPlanned,
        -> DescriptorWalletCreationIntent.ImportedSingleKeyTaproot
        DescriptorWalletOrigin.NostrNpubWatchOnly,
        DescriptorWalletOrigin.NostrNpubWatchOnlyPlanned,
        -> DescriptorWalletCreationIntent.NostrNpubWatchOnly
        DescriptorWalletOrigin.NostrNsecImportedSpend,
        DescriptorWalletOrigin.NostrNsecSpendPlanned,
        -> DescriptorWalletCreationIntent.NostrNsecSpend
        DescriptorWalletOrigin.ExternalSigner,
        DescriptorWalletOrigin.ExternalSignerPlanned,
        -> DescriptorWalletCreationIntent.ExternalSigner
        DescriptorWalletOrigin.HardwareSignerPlanned -> DescriptorWalletCreationIntent.HardwareSigner
    }

private val DescriptorWalletCreationIntent.shortLabel: String
    get() = when (this) {
        DescriptorWalletCreationIntent.NativeDescriptorFromAppSeed -> "Native seed"
        DescriptorWalletCreationIntent.ImportedDescriptor -> "Import desc"
        DescriptorWalletCreationIntent.WatchOnlyDescriptor -> "Watch-only"
        DescriptorWalletCreationIntent.ImportedSingleKeyTaproot -> "Single key"
        DescriptorWalletCreationIntent.NostrNpubWatchOnly -> "Nostr npub"
        DescriptorWalletCreationIntent.NostrNsecSpend -> "Nostr nsec"
        DescriptorWalletCreationIntent.ExternalSigner -> "External signer"
        DescriptorWalletCreationIntent.HardwareSigner -> "Hardware signer"
    }
