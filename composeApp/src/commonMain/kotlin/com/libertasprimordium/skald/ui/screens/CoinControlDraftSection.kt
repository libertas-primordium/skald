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
import com.libertasprimordium.skald.domain.onchain.CoinControlAcknowledgement
import com.libertasprimordium.skald.domain.onchain.CoinControlDraft
import com.libertasprimordium.skald.domain.onchain.CoinControlDraftId
import com.libertasprimordium.skald.domain.onchain.CoinControlDraftSettingsState
import com.libertasprimordium.skald.domain.onchain.CoinControlDraftWorkflow
import com.libertasprimordium.skald.domain.onchain.CoinControlDraftWorkflowReview
import com.libertasprimordium.skald.domain.onchain.DemoUtxo
import com.libertasprimordium.skald.domain.onchain.DemoUtxoId
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletMetadataProfile
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletProfileId
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletSettingsState
import com.libertasprimordium.skald.domain.onchain.EditableCoinControlDraftInput
import com.libertasprimordium.skald.ui.components.BulletList
import com.libertasprimordium.skald.ui.components.DetailLine
import com.libertasprimordium.skald.ui.components.InfoBlock
import com.libertasprimordium.skald.ui.components.LockedAction
import com.libertasprimordium.skald.ui.components.OptionGrid
import com.libertasprimordium.skald.ui.components.SkaldCard
import com.libertasprimordium.skald.ui.components.SkaldSmallButton
import com.libertasprimordium.skald.ui.components.SkaldTextField
import com.libertasprimordium.skald.ui.components.riskColor
import com.libertasprimordium.skald.ui.components.toSatsText
import com.libertasprimordium.skald.ui.theme.SkaldDanger
import com.libertasprimordium.skald.ui.theme.SkaldMutedText
import com.libertasprimordium.skald.ui.theme.SkaldOrangeSoft
import com.libertasprimordium.skald.ui.theme.SkaldSuccess
import com.libertasprimordium.skald.ui.theme.SkaldWarning
import com.libertasprimordium.skald.ui.theme.SkaldWhite

@Composable
fun CoinControlDraftPlannerSection(
    settings: CoinControlDraftSettingsState,
    descriptorWalletSettings: DescriptorWalletSettingsState,
    review: CoinControlDraftWorkflowReview?,
    message: String,
    demoUtxos: List<DemoUtxo>,
    onSaveDraft: (EditableCoinControlDraftInput) -> Unit,
    onSelectDraft: (CoinControlDraftId) -> Unit,
    onDeleteDraft: (CoinControlDraftId) -> Unit,
) {
    var form by remember { mutableStateOf(CoinControlDraftFormState.blank()) }

    SkaldCard(title = "Coin-control and PSBT draft planner", state = "non-operational draft") {
        Text(message, color = SkaldOrangeSoft, lineHeight = 20.sp)
        Text(
            text = "This planner uses demo UTXO placeholders only. No backend scan has occurred and no coins can be spent.",
            color = SkaldWarning,
            lineHeight = 20.sp,
        )
        Text("No transaction or PSBT will be created in this pass.", color = SkaldWarning, lineHeight = 20.sp)
        Text("Signing and broadcast are disabled.", color = SkaldWarning, lineHeight = 20.sp)
        CoinControlDraftList(
            drafts = settings.drafts,
            selectedDraftId = settings.selectedDraftId,
            onEdit = { draft -> form = draft.toFormState() },
            onSelect = onSelectDraft,
            onDelete = onDeleteDraft,
        )
        CoinControlDraftForm(
            form = form,
            descriptorProfiles = descriptorWalletSettings.profiles,
            demoUtxos = demoUtxos,
            review = review,
            onFormChanged = { form = it },
            onSave = { onSaveDraft(form.toInput()) },
            onReset = { form = CoinControlDraftFormState.blank() },
        )
    }
}

@Composable
private fun CoinControlDraftList(
    drafts: List<CoinControlDraft>,
    selectedDraftId: CoinControlDraftId?,
    onEdit: (CoinControlDraft) -> Unit,
    onSelect: (CoinControlDraftId) -> Unit,
    onDelete: (CoinControlDraftId) -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Saved draft metadata", color = SkaldWhite, fontWeight = FontWeight.Bold)
        if (drafts.isEmpty()) {
            Text(
                text = "No coin-control draft metadata is saved. Create a non-operational planner draft below.",
                color = SkaldMutedText,
                lineHeight = 20.sp,
            )
        }
        drafts.forEach { draft ->
            val selected = draft.id == selectedDraftId
            InfoBlock(
                title = draft.label.value,
                state = if (selected) "selected draft metadata" else draft.state.label,
            ) {
                DetailLine("Wallet profile", draft.walletProfileLabel)
                DetailLine("Selected demo inputs", draft.selectedDemoUtxoIds.joinToString { it.value })
                DetailLine("Recipient note", draft.outputDraft.recipientNote)
                DetailLine("Amount", "${draft.outputDraft.amountSats.toSatsText()} sats")
                DetailLine("PSBT", draft.placeholderPsbt)
                DetailLine("Signing", draft.signingState.label)
                DetailLine("Broadcast", draft.broadcastState.label)
                DetailLine("Executable", draft.isExecutable.toString())
                if (draft.coinControlBlockingIssues.isNotEmpty()) {
                    Text("Coin-control blockers", color = SkaldWarning, fontWeight = FontWeight.Bold)
                    BulletList(draft.coinControlBlockingIssues.map { it.label })
                }
                if (draft.psbtBlockingIssues.isNotEmpty()) {
                    Text("PSBT blockers", color = SkaldWarning, fontWeight = FontWeight.Bold)
                    BulletList(draft.psbtBlockingIssues.map { it.label })
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    SkaldSmallButton(
                        label = if (selected) "Selected" else "Select",
                        selected = selected,
                        enabled = !selected,
                        onClick = { onSelect(draft.id) },
                        modifier = Modifier.weight(1f),
                    )
                    SkaldSmallButton(
                        label = "Edit",
                        selected = false,
                        onClick = { onEdit(draft) },
                        modifier = Modifier.weight(1f),
                    )
                    SkaldSmallButton(
                        label = "Delete",
                        selected = false,
                        danger = true,
                        onClick = { onDelete(draft.id) },
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }
    }
}

@Composable
private fun CoinControlDraftForm(
    form: CoinControlDraftFormState,
    descriptorProfiles: List<DescriptorWalletMetadataProfile>,
    demoUtxos: List<DemoUtxo>,
    review: CoinControlDraftWorkflowReview?,
    onFormChanged: (CoinControlDraftFormState) -> Unit,
    onSave: () -> Unit,
    onReset: () -> Unit,
) {
    InfoBlock(
        title = if (form.editingId == null) "Create draft metadata" else "Edit draft metadata",
        state = "planner only",
    ) {
        WalletProfileSelector(
            profiles = descriptorProfiles,
            selectedProfileId = form.walletProfileId,
            onSelected = { profile ->
                val visibleDemoIds = demoUtxos
                    .filter { it.walletProfileId == profile.id }
                    .map { it.id }
                    .toSet()
                onFormChanged(
                    form.copy(
                        walletProfileId = profile.id,
                        selectedDemoUtxoIds = form.selectedDemoUtxoIds.intersect(visibleDemoIds),
                    ),
                )
            },
        )
        DemoUtxoSelector(
            walletProfileId = form.walletProfileId,
            demoUtxos = demoUtxos,
            selectedDemoUtxoIds = form.selectedDemoUtxoIds,
            onToggle = { demoUtxoId ->
                val updated = if (form.selectedDemoUtxoIds.contains(demoUtxoId)) {
                    form.selectedDemoUtxoIds - demoUtxoId
                } else {
                    form.selectedDemoUtxoIds + demoUtxoId
                }
                onFormChanged(form.copy(selectedDemoUtxoIds = updated))
            },
        )
        SkaldTextField(
            label = "Draft label",
            value = form.label,
            onValueChange = { onFormChanged(form.copy(label = it)) },
        )
        SkaldTextField(
            label = "Draft recipient note",
            value = form.recipientNote,
            onValueChange = { onFormChanged(form.copy(recipientNote = it)) },
        )
        Text(
            text = "Metadata only. Do not paste a Bitcoin address here yet.",
            color = SkaldWarning,
            lineHeight = 20.sp,
        )
        SkaldTextField(
            label = "Amount sats",
            value = form.amountSatsText,
            onValueChange = { onFormChanged(form.copy(amountSatsText = it)) },
        )
        DraftAcknowledgementControls(
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
        DraftReviewBlock(review)
        LockedAction("PSBT_CONSTRUCTION_NOT_IMPLEMENTED - no transaction, PSBT, signature, or broadcast path exists.")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            SkaldSmallButton(
                label = "Save draft",
                selected = true,
                onClick = onSave,
                modifier = Modifier.weight(1f),
            )
            SkaldSmallButton(
                label = "Reset draft",
                selected = false,
                onClick = onReset,
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Composable
private fun WalletProfileSelector(
    profiles: List<DescriptorWalletMetadataProfile>,
    selectedProfileId: DescriptorWalletProfileId?,
    onSelected: (DescriptorWalletMetadataProfile) -> Unit,
) {
    if (profiles.isEmpty()) {
        Text(
            text = "Create a descriptor wallet metadata profile before planning a coin-control draft.",
            color = SkaldWarning,
            lineHeight = 20.sp,
        )
        return
    }
    val selected = profiles.firstOrNull { it.id == selectedProfileId } ?: profiles.first()
    OptionGrid(
        title = "Descriptor wallet profile",
        options = profiles,
        selected = selected,
        label = { profile -> profile.label.value.compactLabel() },
        onSelected = onSelected,
    )
}

@Composable
private fun DemoUtxoSelector(
    walletProfileId: DescriptorWalletProfileId?,
    demoUtxos: List<DemoUtxo>,
    selectedDemoUtxoIds: Set<DemoUtxoId>,
    onToggle: (DemoUtxoId) -> Unit,
) {
    val visibleUtxos = demoUtxos.filter { it.walletProfileId == walletProfileId }
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Demo UTXO placeholders", color = SkaldWhite, fontWeight = FontWeight.Bold)
        if (walletProfileId == null) {
            Text("Select a wallet profile before selecting demo UTXO placeholders.", color = SkaldMutedText, lineHeight = 20.sp)
            return@Column
        }
        if (visibleUtxos.isEmpty()) {
            Text("No demo UTXO placeholders are available for this profile.", color = SkaldMutedText, lineHeight = 20.sp)
        }
        visibleUtxos.forEach { demoUtxo ->
            val selected = selectedDemoUtxoIds.contains(demoUtxo.id)
            InfoBlock(
                title = demoUtxo.label.value,
                state = if (selected) "selected demo input" else "demo only",
            ) {
                DetailLine("ID", demoUtxo.id.value)
                DetailLine("Outpoint", demoUtxo.outPoint.displayText)
                DetailLine("Amount", "${demoUtxo.amountSats.toSatsText()} sats")
                DetailLine("Spendability", demoUtxo.spendabilityState.label)
                DetailLine("Privacy", demoUtxo.privacyState.label)
                Text(demoUtxo.originNote, color = SkaldWarning, lineHeight = 20.sp)
                SkaldSmallButton(
                    label = if (selected) "Remove placeholder" else "Select placeholder",
                    selected = selected,
                    onClick = { onToggle(demoUtxo.id) },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}

@Composable
private fun DraftAcknowledgementControls(
    acknowledgements: Set<CoinControlAcknowledgement>,
    onToggle: (CoinControlAcknowledgement) -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Required draft acknowledgements", color = SkaldWhite, fontWeight = FontWeight.Bold)
        CoinControlDraftWorkflow.RequiredAcknowledgements.forEach { acknowledgement ->
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
private fun DraftReviewBlock(review: CoinControlDraftWorkflowReview?) {
    if (review == null) {
        Text(
            text = "Validation runs before save. Draft metadata is stored only after all required reviews are acknowledged.",
            color = SkaldMutedText,
            lineHeight = 20.sp,
        )
        return
    }

    Text("Draft state: ${review.state.label}", color = SkaldOrangeSoft, fontWeight = FontWeight.Bold)
    if (review.errors.isEmpty()) {
        Text("Draft metadata validation passed. It remains non-operational.", color = SkaldSuccess, fontWeight = FontWeight.Bold)
    } else {
        review.errors.forEach { error ->
            Text(error.message, color = SkaldDanger, lineHeight = 20.sp)
        }
    }
    if (review.requiredAcknowledgements.isNotEmpty()) {
        Text("Required acknowledgements", color = SkaldWhite, fontWeight = FontWeight.Bold)
        BulletList(review.requiredAcknowledgements.map { it.label })
    }
    review.draft?.let { draft ->
        DetailLine("PSBT", draft.placeholderPsbt)
        DetailLine("PSBT state", draft.psbtDraftState.label)
        DetailLine("Signing", draft.signingState.label)
        DetailLine("Broadcast", draft.broadcastState.label)
        Text("Selected inputs", color = SkaldWhite, fontWeight = FontWeight.Bold)
        draft.selectedInputs.forEach { input ->
            DetailLine(input.id.value, "${input.amountSats.toSatsText()} sats - ${input.outPoint.displayText}")
        }
        if (draft.coinControlBlockingIssues.isNotEmpty()) {
            Text("Coin-control blockers", color = SkaldWarning, fontWeight = FontWeight.Bold)
            BulletList(draft.coinControlBlockingIssues.map { it.label })
        }
        if (draft.psbtBlockingIssues.isNotEmpty()) {
            Text("PSBT blockers", color = SkaldWarning, fontWeight = FontWeight.Bold)
            BulletList(draft.psbtBlockingIssues.map { it.label })
        }
        draft.privacyWarnings.forEach { warning ->
            Text(
                text = "${warning.level.label.uppercase()} - ${warning.title}",
                color = riskColor(warning.level),
                fontWeight = FontWeight.Bold,
            )
            Text(warning.detail, color = SkaldMutedText, lineHeight = 20.sp)
        }
    }
    review.warnings.forEach { warning ->
        Text(warning, color = SkaldWarning, lineHeight = 20.sp)
    }
}

private data class CoinControlDraftFormState(
    val editingId: CoinControlDraftId?,
    val label: String,
    val walletProfileId: DescriptorWalletProfileId?,
    val selectedDemoUtxoIds: Set<DemoUtxoId>,
    val recipientNote: String,
    val amountSatsText: String,
    val acknowledgements: Set<CoinControlAcknowledgement>,
) {
    fun toInput(): EditableCoinControlDraftInput =
        EditableCoinControlDraftInput(
            id = editingId,
            label = label,
            walletProfileId = walletProfileId,
            selectedDemoUtxoIds = selectedDemoUtxoIds,
            recipientNote = recipientNote,
            amountSatsText = amountSatsText,
            acknowledgements = acknowledgements,
        )

    companion object {
        fun blank(): CoinControlDraftFormState =
            CoinControlDraftFormState(
                editingId = null,
                label = "",
                walletProfileId = null,
                selectedDemoUtxoIds = emptySet(),
                recipientNote = "",
                amountSatsText = "",
                acknowledgements = emptySet(),
            )
    }
}

private fun CoinControlDraft.toFormState(): CoinControlDraftFormState =
    CoinControlDraftFormState(
        editingId = id,
        label = label.value,
        walletProfileId = walletProfileId,
        selectedDemoUtxoIds = selectedDemoUtxoIds,
        recipientNote = outputDraft.recipientNote,
        amountSatsText = outputDraft.amountSats.toString(),
        acknowledgements = acknowledgements,
    )

private fun String.compactLabel(): String =
    if (length <= 18) this else take(15).trimEnd() + "..."
