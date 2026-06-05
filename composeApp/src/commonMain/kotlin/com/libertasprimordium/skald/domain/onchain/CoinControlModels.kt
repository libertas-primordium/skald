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
