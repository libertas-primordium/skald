package com.libertasprimordium.skald.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
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
                .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Top)),
        ) {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
            ) {
                val compact = maxWidth < 760.dp
                Column(
                    verticalArrangement = Arrangement.spacedBy(if (compact) 12.dp else 16.dp),
                    modifier = Modifier.fillMaxSize(),
                ) {
                    SkaldHeader(
                        compact = compact,
                        selected = selectedScreen,
                        onSelected = onSelectedScreen,
                    )
                    PrimaryNavigation(
                        selected = selectedScreen,
                        compact = compact,
                        onSelected = onSelectedScreen,
                    )
                    WarningStrip(
                        text = "No real wallet functionality exists in this scaffold. Demo portfolio - testnet/regtest/signet only.",
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                            .padding(bottom = 40.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        content(selectedScreen)
                    }
                }
            }
        }
    }
}
