package com.libertasprimordium.skald.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.libertasprimordium.skald.security.SecureStorageUiStatus
import com.libertasprimordium.skald.ui.theme.SkaldMutedText
import com.libertasprimordium.skald.ui.theme.SkaldWarning
import com.libertasprimordium.skald.ui.theme.SkaldWhite

@Composable
fun SecureStorageStatusCard(status: SecureStorageUiStatus) {
    SkaldCard(title = status.title, state = status.state) {
        Text(status.detail, color = SkaldWarning, lineHeight = 20.sp)
        Text(
            text = "Future secret-bearing features must use the secure storage boundary before they can be enabled.",
            color = SkaldMutedText,
            lineHeight = 20.sp,
        )
        Text("Planned secret classes", color = SkaldWhite, fontWeight = FontWeight.Bold)
        BulletList(status.plannedSecretClasses)
        Text("Locked actions", color = SkaldWhite, fontWeight = FontWeight.Bold)
        status.disabledActions.forEach { action ->
            InfoBlock(
                title = action,
                state = "disabled",
            ) {
                LockedAction("SECRET_STORAGE_NOT_IMPLEMENTED")
            }
        }
    }
}

@Composable
fun SecureStorageInlineStatus(status: SecureStorageUiStatus) {
    InfoBlock(
        title = status.title,
        state = status.state,
    ) {
        Text(status.detail, color = SkaldWarning, lineHeight = 20.sp)
        BulletList(
            listOf(
                "Backend profile settings remain non-secret only.",
                "Credential references cannot unlock connection testing in this pass.",
                "Secret writes, reads, and deletes fail closed.",
            ),
        )
    }
}
