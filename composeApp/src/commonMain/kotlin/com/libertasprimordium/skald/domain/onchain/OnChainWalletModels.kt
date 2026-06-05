package com.libertasprimordium.skald.domain.onchain

data class DisabledWalletAction(
    val label: String,
    val reason: String,
)

data class OnChainWalletProfile(
    val label: String,
    val status: DescriptorWalletStatus,
    val descriptorWallets: List<DescriptorWalletProfile>,
    val backendProfiles: List<BitcoinBackendProfile>,
    val coinControlPolicy: CoinControlPolicy,
    val coinSelectionDraft: CoinSelectionDraft,
    val psbtWorkflow: PsbtWorkflowPlan,
    val recoveryStatus: OnChainRecoveryStatus,
    val plannedCapabilities: List<String>,
    val disabledActions: List<DisabledWalletAction>,
)
