package com.libertasprimordium.skald.domain.onchain

import com.libertasprimordium.skald.domain.privacy.PrivacyRiskLevel

enum class CrossWalletSelectionPolicy(val label: String, val allowsCrossWalletSelection: Boolean) {
    Disabled("cross-wallet selection disabled", allowsCrossWalletSelection = false),
    FutureAdvancedFlow("future advanced flow", allowsCrossWalletSelection = false),
}

data class CoinControlPolicy(
    val requiresManualInputReview: Boolean,
    val requiresFeeAndChangeReview: Boolean,
    val requiresExplicitSigningApproval: Boolean,
    val requiresExplicitBroadcastApproval: Boolean,
    val crossWalletSelectionPolicy: CrossWalletSelectionPolicy,
    val note: String,
) {
    val isStrict: Boolean
        get() = requiresManualInputReview &&
            requiresFeeAndChangeReview &&
            requiresExplicitSigningApproval &&
            requiresExplicitBroadcastApproval &&
            !crossWalletSelectionPolicy.allowsCrossWalletSelection

    companion object {
        fun strictPlaceholder(note: String): CoinControlPolicy =
            CoinControlPolicy(
                requiresManualInputReview = true,
                requiresFeeAndChangeReview = true,
                requiresExplicitSigningApproval = true,
                requiresExplicitBroadcastApproval = true,
                crossWalletSelectionPolicy = CrossWalletSelectionPolicy.Disabled,
                note = note,
            )
    }
}

enum class CoinSelectionIntent(val label: String) {
    ManualSpendReview("manual spend review"),
    RecoverySweep("recovery sweep"),
    PayjoinSend("Payjoin send"),
    PublicNostrPayment("public Nostr payment"),
    DemoDisabledDraft("demo disabled draft"),
}

data class SelectedUtxo(
    val utxo: UtxoView,
    val requiresUserReview: Boolean,
)

data class RecipientOutputPlan(
    val label: String,
    val amountSats: Long?,
    val addressDisplay: String,
)

enum class ChangeOutputState(val label: String) {
    NotCalculated("not calculated"),
    RequiredReview("required review"),
    DisabledPlaceholder("disabled placeholder"),
}

data class ChangeOutputPlan(
    val state: ChangeOutputState,
    val addressDisplay: String,
    val amountSats: Long?,
    val warning: String,
)

data class FeePlaceholder(
    val networkFeeSats: Long?,
    val feeRateSatPerVbyte: Long?,
    val reason: String,
)

data class CoinControlWarning(
    val title: String,
    val detail: String,
    val level: PrivacyRiskLevel,
)

enum class CoinControlApprovalState(val label: String, val permitsSigning: Boolean, val permitsBroadcast: Boolean) {
    ManualInputReviewRequired("manual input review required", permitsSigning = false, permitsBroadcast = false),
    FeeAndChangeReviewRequired("fee and change review required", permitsSigning = false, permitsBroadcast = false),
    SigningApprovalRequired("explicit signing approval required", permitsSigning = false, permitsBroadcast = false),
    BroadcastApprovalRequired("explicit broadcast approval required", permitsSigning = false, permitsBroadcast = false),
    DisabledPlaceholder("disabled placeholder", permitsSigning = false, permitsBroadcast = false),
}

data class CoinControlReview(
    val selectedInputs: List<SelectedUtxo>,
    val outputPlan: List<RecipientOutputPlan>,
    val changePlan: ChangeOutputPlan,
    val feePlaceholder: FeePlaceholder,
    val privacyWarnings: List<CoinControlWarning>,
    val approvalState: CoinControlApprovalState,
)

data class CoinSelectionDraft(
    val id: String,
    val intent: CoinSelectionIntent,
    val review: CoinControlReview,
    val isDemoDraft: Boolean,
    val isExecutable: Boolean,
)

@JvmInline
value class CoinControlDraftId(val value: String)

@JvmInline
value class CoinControlDraftLabel(val value: String)

@JvmInline
value class DemoUtxoId(val value: String)

data class DemoOutPointRef(
    val displayText: String,
) {
    val isRealOutPoint: Boolean = false
}

data class DemoUtxo(
    val id: DemoUtxoId,
    val walletProfileId: DescriptorWalletProfileId,
    val outPoint: DemoOutPointRef,
    val amountSats: Long,
    val status: UtxoStatus,
    val label: UtxoLabel,
    val clusterId: UtxoClusterId,
    val source: UtxoSource,
    val age: UtxoAge,
    val privacyState: UtxoPrivacyState,
    val spendabilityState: SpendabilityState,
    val originNote: String,
    val warnings: List<String>,
) {
    val isReal: Boolean = false
    val isSpendable: Boolean = false
}

enum class CoinControlDraftState(val label: String) {
    NotStarted("not started"),
    ChoosingWalletProfile("choosing wallet profile"),
    SelectingInputs("selecting inputs"),
    EnteringOutputs("entering output metadata"),
    ReviewingChangeAndFees("reviewing change and fees"),
    ReviewingPrivacyWarnings("reviewing privacy warnings"),
    NeedsCoinControlAcknowledgement("needs coin-control acknowledgement"),
    NeedsFeeChangeAcknowledgement("needs fee/change acknowledgement"),
    ReadyForPsbtConstruction("ready for PSBT construction"),
    BlockedByDescriptorWalletNonOperational("blocked by descriptor wallet non-operational"),
    BlockedByNoBackendConnection("blocked by no backend connection"),
    BlockedByNoRealUtxoScan("blocked by no real UTXO scan"),
    BlockedByPsbtConstructionNotImplemented("blocked by PSBT construction not implemented"),
    BlockedBySigningDisabled("blocked by signing disabled"),
    BlockedByBroadcastDisabled("blocked by broadcast disabled"),
    DraftMetadataSaved("draft metadata saved"),
    Cancelled("cancelled"),
}

sealed interface CoinControlDraftEvent {
    data object Start : CoinControlDraftEvent
    data class ChooseWalletProfile(val profileId: DescriptorWalletProfileId) : CoinControlDraftEvent
    data class ToggleDemoUtxo(val demoUtxoId: DemoUtxoId) : CoinControlDraftEvent
    data object EnterOutputMetadata : CoinControlDraftEvent
    data object ReviewChangeAndFees : CoinControlDraftEvent
    data object ReviewPrivacyWarnings : CoinControlDraftEvent
    data class Acknowledge(val acknowledgement: CoinControlAcknowledgement) : CoinControlDraftEvent
    data object SaveDraftMetadata : CoinControlDraftEvent
    data object Cancel : CoinControlDraftEvent
}

sealed interface CoinControlDraftResult {
    data class Reviewed(val review: CoinControlDraftWorkflowReview) : CoinControlDraftResult
    data class Saved(val draft: CoinControlDraft) : CoinControlDraftResult
    data class Rejected(val errors: List<CoinControlDraftValidationError>) : CoinControlDraftResult
}

enum class CoinControlSelectionState(val label: String) {
    NoWalletSelected("no wallet selected"),
    WalletSelected("wallet selected"),
    NoInputsSelected("no demo inputs selected"),
    DemoInputsSelected("demo inputs selected"),
    CrossWalletSelectionBlocked("cross-wallet selection blocked"),
}

enum class CoinControlReviewState(val label: String) {
    NotReady("not ready"),
    SelectedInputsVisible("selected inputs visible"),
    ManualReviewRequired("manual input review required"),
    Acknowledged("coin-control review acknowledged"),
}

enum class CoinControlAcknowledgement(val label: String) {
    ManualInputReviewAcknowledged("manual selected-input review acknowledged"),
    FeeChangeReviewAcknowledged("fee and change placeholder review acknowledged"),
    SigningDisabledAcknowledged("signing disabled acknowledged"),
    BroadcastDisabledAcknowledged("broadcast disabled acknowledged"),
}

enum class CoinControlBlockingIssue(val label: String) {
    MissingWalletProfile("missing wallet profile"),
    DescriptorWalletNonOperational("descriptor wallet profile is non-operational"),
    MissingSelectedDemoUtxos("missing selected demo UTXOs"),
    DemoUtxosNotReal("demo UTXOs are not real"),
    DemoUtxosNotSpendable("demo UTXOs are not spendable"),
    CrossWalletSelectionDisabled("cross-wallet input selection disabled"),
    NoBackendConnection("backend not connected"),
    NoRealUtxoScan("real UTXO scan not implemented"),
    CoinControlReviewRequired("coin-control review required"),
    FeeChangeReviewRequired("fee/change review required"),
    PsbtConstructionNotImplemented("PSBT construction not implemented"),
    SigningDisabled("signing disabled"),
    BroadcastDisabled("broadcast disabled"),
}

data class OutputDraft(
    val recipientNote: String,
    val amountSats: Long,
    val isAddressFieldAvailable: Boolean,
    val warning: String,
)

data class ChangeDraft(
    val state: ChangeOutputState,
    val amountSats: Long?,
    val addressDisplay: String,
    val warning: String,
)

data class FeeDraft(
    val networkFeeSats: Long?,
    val feeRateSatPerVbyte: Long?,
    val reviewState: FeeReviewState,
    val reason: String,
)

enum class FeeReviewState(val label: String) {
    NotCalculated("not calculated"),
    PlaceholderReviewRequired("placeholder review required"),
    Acknowledged("fee/change review acknowledged"),
}

enum class PrivacyReviewState(val label: String) {
    NotReady("not ready"),
    PlaceholderWarningsVisible("placeholder warnings visible"),
    RealAnalysisNotPerformed("real privacy analysis not performed"),
}

enum class PsbtDraftState(val label: String) {
    NotStarted("not started"),
    DraftMetadataOnly("draft metadata only"),
    PsbtConstructionBlocked("PSBT construction blocked"),
    PsbtNotCreated("PSBT not created"),
}

enum class PsbtDraftCapability(
    val label: String,
    val psbtExportEnabled: Boolean = false,
    val psbtImportEnabled: Boolean = false,
    val signingEnabled: Boolean = false,
    val broadcastEnabled: Boolean = false,
) {
    CanDisplayDraftMetadata("can display draft metadata"),
    CanDisplaySelectedDemoInputs("can display selected demo inputs"),
    PsbtExportDisabled("PSBT export disabled"),
    PsbtImportDisabled("PSBT import disabled"),
    SigningDisabled("signing disabled"),
    BroadcastDisabled("broadcast disabled"),
    RequiresRealUtxoScan("requires real UTXO scan"),
    RequiresTransactionConstruction("requires transaction construction"),
    RequiresBackendConnection("requires backend connection"),
}

enum class PsbtDraftBlockingIssue(val label: String) {
    PsbtConstructionNotImplemented("PSBT construction not implemented"),
    PsbtSerializationNotImplemented("PSBT serialization not implemented"),
    PsbtImportNotImplemented("PSBT import not implemented"),
    SigningDisabled("signing disabled"),
    BroadcastDisabled("broadcast disabled"),
    FinalizationDisabled("finalization disabled"),
    NoRealTransaction("no real transaction constructed"),
    NoBackendConnection("backend not connected"),
}

enum class PsbtConstructionState(val label: String, val psbtCreated: Boolean) {
    NotStarted("not started", psbtCreated = false),
    NotImplemented("PSBT construction not implemented", psbtCreated = false),
    Blocked("blocked", psbtCreated = false),
}

enum class SigningState(val label: String, val signingEnabled: Boolean) {
    Disabled("SIGNING_DISABLED", signingEnabled = false),
    RequiresExplicitFutureApproval("requires explicit future approval", signingEnabled = false),
}

enum class BroadcastState(val label: String, val broadcastEnabled: Boolean) {
    Disabled("BROADCAST_DISABLED", broadcastEnabled = false),
    RequiresExplicitFutureApproval("requires explicit future approval", broadcastEnabled = false),
}

data class CoinControlDraft(
    val id: CoinControlDraftId,
    val label: CoinControlDraftLabel,
    val walletProfileId: DescriptorWalletProfileId,
    val walletProfileLabel: String,
    val selectedDemoUtxoIds: Set<DemoUtxoId>,
    val selectedInputs: List<DemoUtxo>,
    val outputDraft: OutputDraft,
    val changeDraft: ChangeDraft,
    val feeDraft: FeeDraft,
    val state: CoinControlDraftState,
    val selectionState: CoinControlSelectionState,
    val reviewState: CoinControlReviewState,
    val feeReviewState: FeeReviewState,
    val privacyReviewState: PrivacyReviewState,
    val psbtDraftState: PsbtDraftState,
    val psbtConstructionState: PsbtConstructionState,
    val signingState: SigningState,
    val broadcastState: BroadcastState,
    val acknowledgements: Set<CoinControlAcknowledgement>,
    val coinControlBlockingIssues: Set<CoinControlBlockingIssue>,
    val psbtBlockingIssues: Set<PsbtDraftBlockingIssue>,
    val psbtCapabilities: Set<PsbtDraftCapability>,
    val privacyWarnings: List<CoinControlWarning>,
    val placeholderPsbt: String,
    val isNonOperationalDraft: Boolean,
    val isExecutable: Boolean,
) {
    val hasRealInputs: Boolean
        get() = selectedInputs.any { it.isReal }

    val hasSpendableInputs: Boolean
        get() = selectedInputs.any { it.isSpendable }

    val psbtCreated: Boolean
        get() = psbtConstructionState.psbtCreated || placeholderPsbt != "PSBT_NOT_CREATED"

    val signingEnabled: Boolean
        get() = signingState.signingEnabled || psbtCapabilities.any { it.signingEnabled }

    val broadcastEnabled: Boolean
        get() = broadcastState.broadcastEnabled || psbtCapabilities.any { it.broadcastEnabled }

    val exportEnabled: Boolean
        get() = psbtCapabilities.any { it.psbtExportEnabled }
}
