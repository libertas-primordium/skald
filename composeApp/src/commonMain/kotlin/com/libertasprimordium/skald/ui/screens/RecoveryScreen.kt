package com.libertasprimordium.skald.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletSettingsState
import com.libertasprimordium.skald.domain.recovery.RecoverySyncStatus
import com.libertasprimordium.skald.domain.recovery.RecoveryStatus
import com.libertasprimordium.skald.security.SecureStorageUiStatus
import com.libertasprimordium.skald.ui.components.BulletList
import com.libertasprimordium.skald.ui.components.CardGrid
import com.libertasprimordium.skald.ui.components.DetailLine
import com.libertasprimordium.skald.ui.components.InfoBlock
import com.libertasprimordium.skald.ui.components.LockedAction
import com.libertasprimordium.skald.ui.components.ScreenTitle
import com.libertasprimordium.skald.ui.components.SecureStorageStatusCard
import com.libertasprimordium.skald.ui.components.SkaldCard
import com.libertasprimordium.skald.ui.components.WarningStrip
import com.libertasprimordium.skald.ui.components.riskColor
import com.libertasprimordium.skald.ui.theme.SkaldMutedText

@Composable
fun RecoveryScreen(
    recovery: RecoveryStatus,
    secureStorageStatus: SecureStorageUiStatus,
    descriptorWalletSettings: DescriptorWalletSettingsState,
    syncStatus: RecoverySyncStatus,
) {
    ScreenTitle("Recovery", "First-class recovery status model.")
    WarningStrip(recovery.seedWarning)
    WarningStrip("Secret storage is disabled. No seeds, imported keys, Lightning credentials, Cashu proof material, or backup encryption keys can be stored yet.")
    SkaldCard(title = recovery.headline, state = "required before real wallet flows") {
        BulletList(
            listOf(
                "Native on-chain keys may be seed-restorable.",
                "Lightning recovery requires current channel state or remote-node backups.",
                "Cashu recovery depends on mint support and proof state.",
                "Imported keys must be backed up separately.",
                "Remote-node funds are not backed up by Skald Vault.",
            ),
        )
    }
    CardGrid {
        recovery.items.forEach { item ->
            SkaldCard(
                title = item.label,
                state = item.state.label,
            ) {
                Text(item.detail, color = riskColor(item.riskLevel), lineHeight = 20.sp)
            }
        }
    }
    SecureStorageStatusCard(secureStorageStatus)
    RecoverySyncStatusCard(syncStatus)
    DescriptorWalletMetadataRecoverySection(descriptorWalletSettings)
    recovery.onChainRecoveryStatus?.let { onChainRecovery ->
        OnChainRecoverySection(onChainRecovery)
    }
}

@Composable
private fun RecoverySyncStatusCard(status: RecoverySyncStatus) {
    SkaldCard(title = status.title, state = status.state) {
        Text(status.summary, color = SkaldMutedText, lineHeight = 20.sp)
        DetailLine("Production sync", if (status.productionSyncEnabled) "enabled" else "disabled")
        DetailLine(
            "Observation persistence",
            if (status.productionObservationPersistenceEnabled) "enabled" else "deferred until encrypted vault",
        )
        DetailLine(
            "Secure metadata",
            if (status.secureMetadataPersistenceEnabled) "enabled" else "disabled / encrypted vault unavailable",
        )
        DetailLine(
            "Test validation",
            if (status.testValidationCountsAsProductionRecovery) {
                "production recovery state"
            } else {
                "test-only, not recoverable production state"
            },
        )
        status.items.forEach { item ->
            InfoBlock(
                title = item.label,
                state = item.state.label,
            ) {
                Text(item.detail, color = riskColor(item.riskLevel), lineHeight = 20.sp)
            }
        }
        LockedAction(status.lockedActionLabel)
    }
}

@Composable
private fun DescriptorWalletMetadataRecoverySection(
    settings: DescriptorWalletSettingsState,
) {
    SkaldCard(
        title = "Saved descriptor profile recovery",
        state = if (settings.profiles.isEmpty()) "no saved metadata" else "${settings.profiles.size} metadata profile(s)",
    ) {
        if (settings.profiles.isEmpty()) {
            Text(
                text = "No user-created descriptor wallet metadata profiles exist yet.",
                color = SkaldMutedText,
                lineHeight = 20.sp,
            )
            return@SkaldCard
        }
        settings.profiles.forEach { profile ->
            InfoBlock(
                title = profile.label.value,
                state = profile.backupRequirement.label,
            ) {
                DetailLine("Origin", profile.origin.label)
                DetailLine("Workflow", profile.workflowState.label)
                DetailLine("Operational", profile.isOperational.toString())
                DetailLine("Descriptor", profile.descriptorTextState)
                DetailLine("Key material", profile.keyMaterialState)
                if (profile.riskAcknowledgements.isNotEmpty()) {
                    BulletList(profile.riskAcknowledgements.map { it.label })
                }
                if (profile.blockingIssues.isNotEmpty()) {
                    BulletList(profile.blockingIssues.map { it.label })
                }
            }
        }
    }
}
