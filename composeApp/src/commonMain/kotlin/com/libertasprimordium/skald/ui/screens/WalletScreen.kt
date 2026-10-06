package com.libertasprimordium.skald.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.libertasprimordium.skald.ui.ShellPage
import com.libertasprimordium.skald.ui.components.ScreenTitle
import com.libertasprimordium.skald.ui.components.SkaldCard
import com.libertasprimordium.skald.ui.theme.SkaldMutedText

@Composable
fun WalletScreen(page: ShellPage) {
    ScreenTitle(page.title, page.subtitle)
    page.sections.forEach { section ->
        SkaldCard(title = section.title, state = section.status) {
            section.paragraphs.forEach { paragraph ->
                Text(paragraph, color = SkaldMutedText, lineHeight = 24.sp)
            }
        }
    }
}
