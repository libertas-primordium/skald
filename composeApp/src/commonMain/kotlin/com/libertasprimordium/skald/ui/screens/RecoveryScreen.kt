package com.libertasprimordium.skald.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.libertasprimordium.skald.domain.recovery.RecoveryStatus
import com.libertasprimordium.skald.security.SecureStorageUiStatus
import com.libertasprimordium.skald.ui.components.BulletList
import com.libertasprimordium.skald.ui.components.CardGrid
import com.libertasprimordium.skald.ui.components.ScreenTitle
import com.libertasprimordium.skald.ui.components.SecureStorageStatusCard
import com.libertasprimordium.skald.ui.components.SkaldCard
import com.libertasprimordium.skald.ui.components.WarningStrip
import com.libertasprimordium.skald.ui.components.riskColor

@Composable
fun RecoveryScreen(
    recovery: RecoveryStatus,
    secureStorageStatus: SecureStorageUiStatus,
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
    recovery.onChainRecoveryStatus?.let { onChainRecovery ->
        OnChainRecoverySection(onChainRecovery)
    }
}
