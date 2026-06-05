package com.libertasprimordium.skald.domain.onchain

@JvmInline
value class PsbtWorkflowId(val value: String)

enum class PsbtWorkflowState(val label: String, val signingEnabled: Boolean, val broadcastEnabled: Boolean) {
    NotStarted("not started", signingEnabled = false, broadcastEnabled = false),
    DraftCreatedPlaceholder("draft created placeholder", signingEnabled = false, broadcastEnabled = false),
    NeedsCoinControlReview("needs coin-control review", signingEnabled = false, broadcastEnabled = false),
    NeedsFeeReview("needs fee review", signingEnabled = false, broadcastEnabled = false),
    ReadyForPsbtConstructionNotImplemented("ready for PSBT construction - not implemented", signingEnabled = false, broadcastEnabled = false),
    PsbtConstructionDisabled("PSBT construction disabled", signingEnabled = false, broadcastEnabled = false),
    NeedsExternalSignature("needs external signature", signingEnabled = false, broadcastEnabled = false),
    NeedsFinalReview("needs final review", signingEnabled = false, broadcastEnabled = false),
    ReadyToBroadcastDisabled("ready to broadcast disabled", signingEnabled = false, broadcastEnabled = false),
    BroadcastDisabled("broadcast disabled", signingEnabled = false, broadcastEnabled = false),
    Cancelled("cancelled", signingEnabled = false, broadcastEnabled = false),
}

data class PsbtInputSummary(
    val label: String,
    val amountSats: Long?,
    val sourceWallet: DescriptorWalletLabel,
    val requiresCoinControlReview: Boolean,
)

data class PsbtOutputSummary(
    val label: String,
    val amountSats: Long?,
    val isChange: Boolean,
    val addressDisplay: String,
)

data class PsbtFeeSummary(
    val networkFeeSats: Long?,
    val feeRateSatPerVbyte: Long?,
    val requiresReview: Boolean,
)

data class PsbtDraft(
    val placeholderPsbt: String,
    val inputs: List<PsbtInputSummary>,
    val outputs: List<PsbtOutputSummary>,
    val feeSummary: PsbtFeeSummary,
)

data class PsbtReview(
    val draft: PsbtDraft,
    val privacyWarnings: List<CoinControlWarning>,
    val failureModes: List<PsbtFailureMode>,
)

enum class PsbtSigningPolicy(val label: String, val signingEnabled: Boolean) {
    SigningDisabledThisPass("signing disabled in this pass", signingEnabled = false),
    ExternalSignerPlanned("external signer planned", signingEnabled = false),
    HardwareSignerPlanned("hardware signer planned", signingEnabled = false),
}

enum class PsbtBroadcastPolicy(val label: String, val broadcastEnabled: Boolean) {
    BroadcastDisabledThisPass("broadcast disabled in this pass", broadcastEnabled = false),
    UserSelectedBackendRequired("user-selected backend required", broadcastEnabled = false),
    FinalReviewRequired("final review required", broadcastEnabled = false),
}

enum class PsbtFailureMode(val label: String) {
    BackendNotConfigured("backend not configured"),
    UtxoSetUnavailable("UTXO set unavailable"),
    FeeEstimatorUnavailable("fee estimator unavailable"),
    SigningNotImplemented("signing not implemented"),
    BroadcastNotImplemented("broadcast not implemented"),
    ExternalSignerNotImplemented("external signer not implemented"),
}

data class PsbtWorkflowPlan(
    val id: PsbtWorkflowId,
    val currentState: PsbtWorkflowState,
    val review: PsbtReview,
    val signingPolicy: PsbtSigningPolicy,
    val broadcastPolicy: PsbtBroadcastPolicy,
    val exportImportPlanned: Boolean,
)
