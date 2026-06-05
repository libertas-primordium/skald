package com.libertasprimordium.skald.domain.onchain

import com.libertasprimordium.skald.domain.privacy.PrivacyRiskLevel

data class EditableCoinControlDraftInput(
    val id: CoinControlDraftId? = null,
    val label: String,
    val walletProfileId: DescriptorWalletProfileId?,
    val selectedDemoUtxoIds: Set<DemoUtxoId>,
    val recipientNote: String,
    val amountSatsText: String,
    val acknowledgements: Set<CoinControlAcknowledgement>,
)

data class CoinControlDraftWorkflowReview(
    val draft: CoinControlDraft?,
    val state: CoinControlDraftState,
    val errors: List<CoinControlDraftValidationError>,
    val warnings: List<String>,
    val requiredAcknowledgements: Set<CoinControlAcknowledgement>,
) {
    val canSaveDraftMetadata: Boolean
        get() = draft != null && errors.isEmpty()
}

data class CoinControlDraftSettingsState(
    val drafts: List<CoinControlDraft>,
    val selectedDraftId: CoinControlDraftId?,
) {
    val selectedDraft: CoinControlDraft?
        get() = drafts.firstOrNull { it.id == selectedDraftId }

    companion object {
        val Empty: CoinControlDraftSettingsState = CoinControlDraftSettingsState(
            drafts = emptyList(),
            selectedDraftId = null,
        )
    }
}

enum class CoinControlDraftValidationError(val message: String) {
    MissingWalletProfile("Select a descriptor wallet metadata profile first."),
    UnknownWalletProfile("Selected descriptor wallet profile is not saved."),
    MissingSelectedDemoUtxos("Select at least one demo UTXO placeholder."),
    UnknownDemoUtxo("Selected demo UTXO placeholder is not available."),
    CrossWalletSelectionDisabled("Cross-wallet input selection is disabled."),
    BlankDraftLabel("Draft label must not be blank."),
    InvalidAmount("Amount must be a positive sats value."),
    AmountExceedsSelectedDemoTotal("Amount exceeds selected demo UTXO placeholder total."),
    MissingRequiredAcknowledgement("Required coin-control, fee/change, signing-disabled, or broadcast-disabled acknowledgement is missing."),
}

object CoinControlDraftWorkflow {
    val RequiredAcknowledgements: Set<CoinControlAcknowledgement> = setOf(
        CoinControlAcknowledgement.ManualInputReviewAcknowledged,
        CoinControlAcknowledgement.FeeChangeReviewAcknowledged,
        CoinControlAcknowledgement.SigningDisabledAcknowledged,
        CoinControlAcknowledgement.BroadcastDisabledAcknowledged,
    )

    fun reduce(
        state: CoinControlDraftState,
        event: CoinControlDraftEvent,
    ): CoinControlDraftState =
        when (event) {
            CoinControlDraftEvent.Start -> CoinControlDraftState.ChoosingWalletProfile
            is CoinControlDraftEvent.ChooseWalletProfile -> CoinControlDraftState.SelectingInputs
            is CoinControlDraftEvent.ToggleDemoUtxo -> CoinControlDraftState.SelectingInputs
            CoinControlDraftEvent.EnterOutputMetadata -> CoinControlDraftState.EnteringOutputs
            CoinControlDraftEvent.ReviewChangeAndFees -> CoinControlDraftState.ReviewingChangeAndFees
            CoinControlDraftEvent.ReviewPrivacyWarnings -> CoinControlDraftState.ReviewingPrivacyWarnings
            is CoinControlDraftEvent.Acknowledge -> when (state) {
                CoinControlDraftState.NeedsCoinControlAcknowledgement,
                CoinControlDraftState.NeedsFeeChangeAcknowledgement,
                CoinControlDraftState.BlockedBySigningDisabled,
                CoinControlDraftState.BlockedByBroadcastDisabled,
                -> CoinControlDraftState.ReadyForPsbtConstruction
                else -> state
            }
            CoinControlDraftEvent.SaveDraftMetadata -> CoinControlDraftState.DraftMetadataSaved
            CoinControlDraftEvent.Cancel -> CoinControlDraftState.Cancelled
        }

    fun review(
        input: EditableCoinControlDraftInput,
        walletSettings: DescriptorWalletSettingsState,
        demoUtxos: List<DemoUtxo>,
    ): CoinControlDraftWorkflowReview {
        val errors = mutableListOf<CoinControlDraftValidationError>()
        val normalizedLabel = input.label.trim()
        val normalizedNote = input.recipientNote.trim()
        val selectedProfile = input.walletProfileId?.let { id ->
            walletSettings.profiles.firstOrNull { it.id == id }
        }

        if (normalizedLabel.isBlank()) {
            errors += CoinControlDraftValidationError.BlankDraftLabel
        }
        if (input.walletProfileId == null) {
            errors += CoinControlDraftValidationError.MissingWalletProfile
        } else if (selectedProfile == null) {
            errors += CoinControlDraftValidationError.UnknownWalletProfile
        }
        if (input.selectedDemoUtxoIds.isEmpty()) {
            errors += CoinControlDraftValidationError.MissingSelectedDemoUtxos
        }

        val selectedDemoUtxos = demoUtxos.filter { input.selectedDemoUtxoIds.contains(it.id) }
        if (selectedDemoUtxos.size != input.selectedDemoUtxoIds.size) {
            errors += CoinControlDraftValidationError.UnknownDemoUtxo
        }
        if (
            selectedProfile != null &&
            selectedDemoUtxos.any { it.walletProfileId != selectedProfile.id }
        ) {
            errors += CoinControlDraftValidationError.CrossWalletSelectionDisabled
        }

        val amountSats = parseAmount(input.amountSatsText)
        if (amountSats == null) {
            errors += CoinControlDraftValidationError.InvalidAmount
        } else if (selectedDemoUtxos.isNotEmpty() && amountSats > selectedDemoUtxos.sumOf { it.amountSats }) {
            errors += CoinControlDraftValidationError.AmountExceedsSelectedDemoTotal
        }

        val missingAcknowledgements = RequiredAcknowledgements - input.acknowledgements
        if (missingAcknowledgements.isNotEmpty()) {
            errors += CoinControlDraftValidationError.MissingRequiredAcknowledgement
        }

        if (errors.isNotEmpty() || selectedProfile == null || amountSats == null) {
            return CoinControlDraftWorkflowReview(
                draft = null,
                state = stateForErrors(errors, missingAcknowledgements),
                errors = errors.distinct(),
                warnings = warningsFor(selectedProfile, selectedDemoUtxos),
                requiredAcknowledgements = RequiredAcknowledgements,
            )
        }

        val coinControlIssues = coinControlBlockingIssuesFor(selectedProfile, selectedDemoUtxos)
        val psbtIssues = psbtBlockingIssues()
        val draft = CoinControlDraft(
            id = input.id ?: CoinControlDraftId(generateDraftId(normalizedLabel, selectedProfile.id)),
            label = CoinControlDraftLabel(normalizedLabel),
            walletProfileId = selectedProfile.id,
            walletProfileLabel = selectedProfile.label.value,
            selectedDemoUtxoIds = selectedDemoUtxos.map { it.id }.toSet(),
            selectedInputs = selectedDemoUtxos,
            outputDraft = OutputDraft(
                recipientNote = normalizedNote.ifBlank { "Metadata-only recipient note not set" },
                amountSats = amountSats,
                isAddressFieldAvailable = false,
                warning = "Metadata only. Do not paste a Bitcoin address here yet.",
            ),
            changeDraft = ChangeDraft(
                state = ChangeOutputState.DisabledPlaceholder,
                amountSats = null,
                addressDisplay = "DEMO_CHANGE_ADDRESS_NOT_DERIVED",
                warning = "Change calculation is placeholder-only. No transaction output is created.",
            ),
            feeDraft = FeeDraft(
                networkFeeSats = null,
                feeRateSatPerVbyte = null,
                reviewState = FeeReviewState.Acknowledged,
                reason = "Fee estimation requires a real transaction builder and backend fee source.",
            ),
            state = stateForBlockingIssues(coinControlIssues, psbtIssues),
            selectionState = CoinControlSelectionState.DemoInputsSelected,
            reviewState = CoinControlReviewState.Acknowledged,
            feeReviewState = FeeReviewState.Acknowledged,
            privacyReviewState = PrivacyReviewState.RealAnalysisNotPerformed,
            psbtDraftState = PsbtDraftState.PsbtConstructionBlocked,
            psbtConstructionState = PsbtConstructionState.NotImplemented,
            signingState = SigningState.Disabled,
            broadcastState = BroadcastState.Disabled,
            acknowledgements = input.acknowledgements.intersect(RequiredAcknowledgements),
            coinControlBlockingIssues = coinControlIssues,
            psbtBlockingIssues = psbtIssues,
            psbtCapabilities = disabledPsbtCapabilities(),
            privacyWarnings = privacyWarningsFor(selectedProfile, selectedDemoUtxos),
            placeholderPsbt = "PSBT_NOT_CREATED",
            isNonOperationalDraft = true,
            isExecutable = false,
        )

        return CoinControlDraftWorkflowReview(
            draft = draft,
            state = draft.state,
            errors = emptyList(),
            warnings = warningsFor(selectedProfile, selectedDemoUtxos),
            requiredAcknowledgements = RequiredAcknowledgements,
        )
    }

    private fun parseAmount(raw: String): Long? {
        val normalized = raw.trim().replace(",", "")
        val amount = normalized.toLongOrNull() ?: return null
        return amount.takeIf { it > 0L }
    }

    private fun stateForErrors(
        errors: List<CoinControlDraftValidationError>,
        missingAcknowledgements: Set<CoinControlAcknowledgement>,
    ): CoinControlDraftState =
        when {
            errors.contains(CoinControlDraftValidationError.MissingWalletProfile) ||
                errors.contains(CoinControlDraftValidationError.UnknownWalletProfile) ->
                CoinControlDraftState.ChoosingWalletProfile
            errors.contains(CoinControlDraftValidationError.MissingSelectedDemoUtxos) ||
                errors.contains(CoinControlDraftValidationError.UnknownDemoUtxo) ||
                errors.contains(CoinControlDraftValidationError.CrossWalletSelectionDisabled) ->
                CoinControlDraftState.SelectingInputs
            errors.contains(CoinControlDraftValidationError.InvalidAmount) ||
                errors.contains(CoinControlDraftValidationError.AmountExceedsSelectedDemoTotal) ||
                errors.contains(CoinControlDraftValidationError.BlankDraftLabel) ->
                CoinControlDraftState.EnteringOutputs
            missingAcknowledgements.contains(CoinControlAcknowledgement.ManualInputReviewAcknowledged) ->
                CoinControlDraftState.NeedsCoinControlAcknowledgement
            missingAcknowledgements.contains(CoinControlAcknowledgement.FeeChangeReviewAcknowledged) ->
                CoinControlDraftState.NeedsFeeChangeAcknowledgement
            missingAcknowledgements.contains(CoinControlAcknowledgement.SigningDisabledAcknowledged) ->
                CoinControlDraftState.BlockedBySigningDisabled
            missingAcknowledgements.contains(CoinControlAcknowledgement.BroadcastDisabledAcknowledged) ->
                CoinControlDraftState.BlockedByBroadcastDisabled
            else -> CoinControlDraftState.NotStarted
        }

    private fun stateForBlockingIssues(
        coinControlIssues: Set<CoinControlBlockingIssue>,
        psbtIssues: Set<PsbtDraftBlockingIssue>,
    ): CoinControlDraftState =
        when {
            coinControlIssues.contains(CoinControlBlockingIssue.DescriptorWalletNonOperational) ->
                CoinControlDraftState.BlockedByDescriptorWalletNonOperational
            coinControlIssues.contains(CoinControlBlockingIssue.NoBackendConnection) ->
                CoinControlDraftState.BlockedByNoBackendConnection
            coinControlIssues.contains(CoinControlBlockingIssue.NoRealUtxoScan) ->
                CoinControlDraftState.BlockedByNoRealUtxoScan
            psbtIssues.contains(PsbtDraftBlockingIssue.PsbtConstructionNotImplemented) ->
                CoinControlDraftState.BlockedByPsbtConstructionNotImplemented
            psbtIssues.contains(PsbtDraftBlockingIssue.SigningDisabled) ->
                CoinControlDraftState.BlockedBySigningDisabled
            psbtIssues.contains(PsbtDraftBlockingIssue.BroadcastDisabled) ->
                CoinControlDraftState.BlockedByBroadcastDisabled
            else -> CoinControlDraftState.DraftMetadataSaved
        }

    private fun coinControlBlockingIssuesFor(
        profile: DescriptorWalletMetadataProfile,
        selectedDemoUtxos: List<DemoUtxo>,
    ): Set<CoinControlBlockingIssue> =
        buildSet {
            if (!profile.isOperational) add(CoinControlBlockingIssue.DescriptorWalletNonOperational)
            if (selectedDemoUtxos.isEmpty()) add(CoinControlBlockingIssue.MissingSelectedDemoUtxos)
            if (selectedDemoUtxos.any { !it.isReal }) add(CoinControlBlockingIssue.DemoUtxosNotReal)
            if (selectedDemoUtxos.any { !it.isSpendable }) add(CoinControlBlockingIssue.DemoUtxosNotSpendable)
            if (selectedDemoUtxos.any { it.walletProfileId != profile.id }) {
                add(CoinControlBlockingIssue.CrossWalletSelectionDisabled)
            }
            add(CoinControlBlockingIssue.NoBackendConnection)
            add(CoinControlBlockingIssue.NoRealUtxoScan)
            add(CoinControlBlockingIssue.PsbtConstructionNotImplemented)
            add(CoinControlBlockingIssue.SigningDisabled)
            add(CoinControlBlockingIssue.BroadcastDisabled)
        }

    private fun psbtBlockingIssues(): Set<PsbtDraftBlockingIssue> =
        setOf(
            PsbtDraftBlockingIssue.PsbtConstructionNotImplemented,
            PsbtDraftBlockingIssue.PsbtSerializationNotImplemented,
            PsbtDraftBlockingIssue.PsbtImportNotImplemented,
            PsbtDraftBlockingIssue.SigningDisabled,
            PsbtDraftBlockingIssue.BroadcastDisabled,
            PsbtDraftBlockingIssue.FinalizationDisabled,
            PsbtDraftBlockingIssue.NoRealTransaction,
            PsbtDraftBlockingIssue.NoBackendConnection,
        )

    private fun disabledPsbtCapabilities(): Set<PsbtDraftCapability> =
        setOf(
            PsbtDraftCapability.CanDisplayDraftMetadata,
            PsbtDraftCapability.CanDisplaySelectedDemoInputs,
            PsbtDraftCapability.PsbtExportDisabled,
            PsbtDraftCapability.PsbtImportDisabled,
            PsbtDraftCapability.SigningDisabled,
            PsbtDraftCapability.BroadcastDisabled,
            PsbtDraftCapability.RequiresRealUtxoScan,
            PsbtDraftCapability.RequiresTransactionConstruction,
            PsbtDraftCapability.RequiresBackendConnection,
        )

    private fun warningsFor(
        profile: DescriptorWalletMetadataProfile?,
        selectedDemoUtxos: List<DemoUtxo>,
    ): List<String> =
        listOf(
            "This planner uses demo UTXO placeholders only. No backend scan has occurred and no coins can be spent.",
            "No transaction or PSBT will be created in this pass.",
            "Signing and broadcast are disabled.",
            "Coin-control review is mandatory before future signing can exist.",
        ) + profileSpecificWarnings(profile) + selectedDemoUtxos.flatMap { it.warnings }

    private fun privacyWarningsFor(
        profile: DescriptorWalletMetadataProfile,
        selectedDemoUtxos: List<DemoUtxo>,
    ): List<CoinControlWarning> {
        val warnings = mutableListOf(
            CoinControlWarning(
                title = "Demo UTXOs are not real",
                detail = "Selected placeholders were not scanned from a backend and cannot be spent.",
                level = PrivacyRiskLevel.Warning,
            ),
            CoinControlWarning(
                title = "Selected wallet profile is non-operational",
                detail = "Descriptor wallet metadata has no keys, descriptor material, receive addresses, or spend capability.",
                level = PrivacyRiskLevel.Warning,
            ),
            CoinControlWarning(
                title = "Backend is not connected",
                detail = "No Bitcoin Core, Electrum, or Esplora connection has been tested or used.",
                level = PrivacyRiskLevel.Warning,
            ),
            CoinControlWarning(
                title = "No real UTXO scan exists",
                detail = "The planner uses static demo placeholders and performs no chain scan.",
                level = PrivacyRiskLevel.Warning,
            ),
            CoinControlWarning(
                title = "Coin-control review is mandatory",
                detail = "Future signing must show selected inputs, labels, clusters, and wallet source before approval.",
                level = PrivacyRiskLevel.Info,
            ),
            CoinControlWarning(
                title = "Fee/change review is mandatory",
                detail = "Fee and change values are placeholders until a real transaction builder exists.",
                level = PrivacyRiskLevel.Info,
            ),
            CoinControlWarning(
                title = "Address reuse checks are not implemented",
                detail = "No destination address is accepted and no address-reuse analysis has been performed.",
                level = PrivacyRiskLevel.Warning,
            ),
            CoinControlWarning(
                title = "Change detection is placeholder-only",
                detail = "No change output or change address is derived.",
                level = PrivacyRiskLevel.Warning,
            ),
            CoinControlWarning(
                title = "Cluster analysis is placeholder-only",
                detail = "Demo clusters are labels only and do not represent chain analysis.",
                level = PrivacyRiskLevel.Warning,
            ),
        )

        if (profile.origin == DescriptorWalletOrigin.NostrNpubWatchOnlyPlanned ||
            profile.origin == DescriptorWalletOrigin.NostrNsecSpendPlanned
        ) {
            warnings += CoinControlWarning(
                title = "Nostr identity linkage risk",
                detail = "Nostr-related wallet profiles can bind Bitcoin activity to public identity unless deliberately isolated.",
                level = PrivacyRiskLevel.Danger,
            )
        }
        if (profile.origin == DescriptorWalletOrigin.ImportedSingleKeyPlanned ||
            profile.origin == DescriptorWalletOrigin.NostrNsecSpendPlanned
        ) {
            warnings += CoinControlWarning(
                title = "Imported-key profile risk",
                detail = "Imported-key funds require separate backup and must not be merged without explicit warnings.",
                level = PrivacyRiskLevel.Danger,
            )
        }
        if (selectedDemoUtxos.any { it.privacyState == UtxoPrivacyState.NostrLinked }) {
            warnings += CoinControlWarning(
                title = "Selected placeholder is Nostr-linked",
                detail = "This is a model warning only; no real Nostr-derived UTXO was scanned.",
                level = PrivacyRiskLevel.Danger,
            )
        }
        return warnings
    }

    private fun profileSpecificWarnings(profile: DescriptorWalletMetadataProfile?): List<String> =
        when (profile?.origin) {
            DescriptorWalletOrigin.NostrNpubWatchOnlyPlanned ->
                listOf("Nostr npub watch-only profiles are identity-linked and cannot spend.")
            DescriptorWalletOrigin.NostrNsecSpendPlanned ->
                listOf("Nostr nsec spend profiles reuse identity key material; no nsec is accepted or stored.")
            DescriptorWalletOrigin.ImportedSingleKeyPlanned ->
                listOf("Imported single-key profiles require separate backup and secure storage before any future spend.")
            else -> emptyList()
        }

    private fun generateDraftId(
        label: String,
        profileId: DescriptorWalletProfileId,
    ): String {
        val basis = "coin-control-draft-${profileId.value}-$label"
        return basis
            .lowercase()
            .map { character ->
                when {
                    character in 'a'..'z' || character in '0'..'9' -> character
                    else -> '-'
                }
            }
            .joinToString("")
            .trim('-')
            .replace(Regex("-+"), "-")
            .ifBlank { "coin-control-draft-placeholder" }
    }
}
