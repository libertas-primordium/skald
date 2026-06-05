package com.libertasprimordium.skald.domain.onchain

sealed interface WalletOperationResult<out T> {
    data class Disabled(
        val reason: String,
    ) : WalletOperationResult<Nothing>

    data class Placeholder<T>(
        val value: T,
        val warning: String,
    ) : WalletOperationResult<T>
}

interface DescriptorWalletRepository {
    fun walletProfiles(): WalletOperationResult<List<DescriptorWalletProfile>>
}

interface DescriptorWalletService {
    fun createNativeDescriptorWallet(label: DescriptorWalletLabel): WalletOperationResult<DescriptorWalletProfile>
    fun importDescriptor(redactedDescriptor: String): WalletOperationResult<DescriptorWalletProfile>
}

interface BitcoinBackendRepository {
    fun backendProfiles(): WalletOperationResult<List<BitcoinBackendProfile>>
}

interface BitcoinBackendConnectionTester {
    fun testConnection(profile: BitcoinBackendProfile): WalletOperationResult<BitcoinBackendStatus>
}

interface UtxoRepository {
    fun utxos(walletId: DescriptorWalletId): WalletOperationResult<List<UtxoView>>
}

interface CoinControlPlanner {
    fun plan(intent: CoinSelectionIntent): WalletOperationResult<CoinSelectionDraft>
}

interface PsbtWorkflowCoordinator {
    fun startWorkflow(draft: CoinSelectionDraft): WalletOperationResult<PsbtWorkflowPlan>
}

interface OnChainRecoveryPlanner {
    fun recoveryStatus(wallets: List<DescriptorWalletProfile>): WalletOperationResult<OnChainRecoveryStatus>
}

class PlaceholderDescriptorWalletService : DescriptorWalletService {
    override fun createNativeDescriptorWallet(label: DescriptorWalletLabel): WalletOperationResult<DescriptorWalletProfile> =
        WalletOperationResult.Disabled("Requires descriptor derivation, seed handling, and secure storage implementation.")

    override fun importDescriptor(redactedDescriptor: String): WalletOperationResult<DescriptorWalletProfile> =
        WalletOperationResult.Disabled("Requires descriptor parsing, validation, and recovery tracking implementation.")
}

class PlaceholderBitcoinBackendConnectionTester : BitcoinBackendConnectionTester {
    override fun testConnection(profile: BitcoinBackendProfile): WalletOperationResult<BitcoinBackendStatus> =
        WalletOperationResult.Disabled("Requires user-supplied endpoint validation and network transport implementation.")
}

class PlaceholderCoinControlPlanner(
    private val disabledDraft: CoinSelectionDraft,
) : CoinControlPlanner {
    override fun plan(intent: CoinSelectionIntent): WalletOperationResult<CoinSelectionDraft> =
        WalletOperationResult.Placeholder(
            value = disabledDraft.copy(intent = intent),
            warning = "Demo coin-control draft only; no real UTXOs or transaction outputs exist.",
        )
}

class NotImplementedPsbtWorkflow : PsbtWorkflowCoordinator {
    override fun startWorkflow(draft: CoinSelectionDraft): WalletOperationResult<PsbtWorkflowPlan> =
        WalletOperationResult.Disabled("PSBT construction, import, export, signing, and broadcast are not implemented.")
}
