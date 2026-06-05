package com.libertasprimordium.skald.demo

import com.libertasprimordium.skald.domain.privacy.PrivacyRiskLevel
import com.libertasprimordium.skald.domain.recovery.RecoveryItem
import com.libertasprimordium.skald.domain.recovery.RecoveryItemState
import com.libertasprimordium.skald.domain.recovery.RecoveryStatus

class DemoRecoveryRepository(
    private val onChainRepository: DemoOnChainRepository = DemoOnChainRepository(),
) {
    fun recoveryStatus(): RecoveryStatus =
        RecoveryStatus(
            headline = "Recovery Center incomplete",
            seedWarning = "A seed phrase alone cannot restore every rail.",
            items = listOf(
                RecoveryItem(
                    label = "App seed",
                    state = RecoveryItemState.NotCreated,
                    detail = "Native on-chain keys may be seed-restorable after a real seed system exists.",
                    riskLevel = PrivacyRiskLevel.Warning,
                ),
                RecoveryItem(
                    label = "On-chain descriptors",
                    state = RecoveryItemState.NotExported,
                    detail = "Descriptor export is required for watch-only and external signing workflows.",
                    riskLevel = PrivacyRiskLevel.Warning,
                ),
                RecoveryItem(
                    label = "Imported keys",
                    state = RecoveryItemState.SeparateBackupRequired,
                    detail = "Imported keys must be backed up separately.",
                    riskLevel = PrivacyRiskLevel.Danger,
                ),
                RecoveryItem(
                    label = "Lightning channel state",
                    state = RecoveryItemState.NotConfigured,
                    detail = "Lightning recovery requires current channel state or remote-node backups.",
                    riskLevel = PrivacyRiskLevel.Danger,
                ),
                RecoveryItem(
                    label = "Cashu recovery",
                    state = RecoveryItemState.MintDependent,
                    detail = "Cashu recovery depends on mint support and proof state.",
                    riskLevel = PrivacyRiskLevel.Warning,
                ),
                RecoveryItem(
                    label = "Remote node balances",
                    state = RecoveryItemState.ExternalBackupRequired,
                    detail = "Remote-node funds are not backed up by Skald Vault.",
                    riskLevel = PrivacyRiskLevel.Danger,
                ),
                RecoveryItem(
                    label = "Metadata backup",
                    state = RecoveryItemState.NotConfigured,
                    detail = "Encrypted metadata backup is planned; plaintext export is not implemented.",
                    riskLevel = PrivacyRiskLevel.Warning,
                ),
            ),
            onChainRecoveryStatus = onChainRepository.loadOnChainProfile().recoveryStatus,
        )
}
