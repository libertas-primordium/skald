package com.libertasprimordium.skald.domain.recovery

import com.libertasprimordium.skald.domain.onchain.OnChainRecoveryStatus
import com.libertasprimordium.skald.domain.privacy.PrivacyRiskLevel

enum class RecoveryItemState(val label: String) {
    NotCreated("not created"),
    NotExported("not exported"),
    SeparateBackupRequired("separate backup required"),
    NotConfigured("not configured"),
    MintDependent("mint-dependent"),
    ExternalBackupRequired("external backup required"),
    Planned("planned"),
}

data class RecoveryStatus(
    val headline: String,
    val seedWarning: String,
    val items: List<RecoveryItem>,
    val onChainRecoveryStatus: OnChainRecoveryStatus?,
)

data class RecoveryItem(
    val label: String,
    val state: RecoveryItemState,
    val detail: String,
    val riskLevel: PrivacyRiskLevel,
)
