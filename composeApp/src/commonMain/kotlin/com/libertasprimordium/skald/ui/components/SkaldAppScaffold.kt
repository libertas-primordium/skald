package com.libertasprimordium.skald.ui.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.libertasprimordium.skald.ui.navigation.AppScreen
import com.libertasprimordium.skald.ui.theme.SkaldBlack
import com.libertasprimordium.skald.ui.theme.SkaldCharcoal
import com.libertasprimordium.skald.ui.theme.SkaldNearBlack
import com.libertasprimordium.skald.ui.theme.SkaldTheme

@Composable
fun SkaldAppScaffold(
    selectedScreen: AppScreen,
    onSelectedScreen: (AppScreen) -> Unit,
    content: @Composable (AppScreen) -> Unit,
) {
    SkaldTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(SkaldBlack, SkaldNearBlack, SkaldCharcoal),
                    ),
                )
                .windowInsetsPadding(WindowInsets.safeDrawing),
        ) {
            BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                val compact = maxWidth < 760.dp
                // Ephemeral per-page position: navigation always starts the new page at the top.
                val scrollState = remember(selectedScreen) { ScrollState(0) }
                Column(
                    verticalArrangement = Arrangement.spacedBy(if (compact) 12.dp else 16.dp),
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .widthIn(max = 960.dp)
                        .fillMaxWidth()
                        .verticalScroll(scrollState)
                        .padding(20.dp),
                ) {
                    SkaldHeader(compact = compact)
                    PrimaryNavigation(
                        selected = selectedScreen,
                        compact = compact,
                        onSelected = onSelectedScreen,
                    )
                    WarningStrip(
                        text = "Development build. Wallet and vault features are unavailable. Do not use real funds.",
                    )
                    content(selectedScreen)
                }
            }
        }
    }
}
