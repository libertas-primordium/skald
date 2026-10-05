package com.libertasprimordium.skald.ui.screens

import androidx.compose.runtime.Composable
import com.libertasprimordium.skald.security.SecureStorageUiStatus
import com.libertasprimordium.skald.ui.ShellPage
import com.libertasprimordium.skald.ui.components.BulletList
import com.libertasprimordium.skald.ui.components.DisabledActionArea
import com.libertasprimordium.skald.ui.components.ScreenTitle
import com.libertasprimordium.skald.ui.components.SecureStorageStatusCard
import com.libertasprimordium.skald.ui.components.SkaldCard

@Composable
fun RecoveryScreen(page: ShellPage, secureStorageStatus: SecureStorageUiStatus) {
    ScreenTitle(page.title, page.subtitle)
    SkaldCard(title = page.title, state = page.status) {
        BulletList(page.details)
    }
    if (page.unavailableActions.isNotEmpty()) {
        DisabledActionArea(
            title = "Unavailable actions",
            actions = page.unavailableActions.map { it to "Unavailable in the offline scaffold." },
        )
    }
    SecureStorageStatusCard(secureStorageStatus)
}
