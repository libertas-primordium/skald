package com.libertasprimordium.skald.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.libertasprimordium.skald.ui.navigation.AppScreen
import com.libertasprimordium.skald.ui.navigation.PrimaryRailTabSpec
import com.libertasprimordium.skald.ui.navigation.SkaldNavigationModel
import com.libertasprimordium.skald.ui.theme.SkaldBlack
import com.libertasprimordium.skald.ui.theme.SkaldCharcoal
import com.libertasprimordium.skald.ui.theme.SkaldMutedText
import com.libertasprimordium.skald.ui.theme.SkaldOrange
import com.libertasprimordium.skald.ui.theme.SkaldWhite

@Composable
fun SkaldHeader(
    compact: Boolean,
    selected: AppScreen,
    onSelected: (AppScreen) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(if (compact) 8.dp else 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = if (compact) Modifier.weight(1f) else Modifier,
            horizontalArrangement = Arrangement.spacedBy(if (compact) 8.dp else 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Wordmark(compact = compact)
            HeaderNetworkText(
                text = "DEVELOPMENT TESTNET",
                compact = compact,
            )
        }
        if (!compact) {
            Spacer(Modifier.weight(1f))
        }
        OverflowMenuNavigation(
            selected = selected,
            onSelected = onSelected,
            compact = compact,
        )
    }
}

@Composable
private fun HeaderNetworkText(
    text: String,
    compact: Boolean,
) {
    Text(
        text = text,
        color = SkaldOrange,
        fontSize = if (compact) 10.sp else 12.sp,
        fontWeight = FontWeight.Black,
        maxLines = 1,
    )
}

@Composable
private fun Wordmark(
    modifier: Modifier = Modifier,
    compact: Boolean,
) {
    Column(modifier = modifier) {
        Text(
            text = "sk\u00e4ld",
            color = SkaldWhite,
            fontSize = if (compact) 28.sp else 36.sp,
            fontWeight = FontWeight.Black,
        )
        if (!compact) {
            Text(
                text = "Skald Vault",
                color = SkaldMutedText,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
            )
        }
    }
}

@Composable
fun PrimaryNavigation(
    selected: AppScreen,
    compact: Boolean,
    onSelected: (AppScreen) -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(if (compact) 6.dp else 8.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        SkaldNavigationModel.PrimaryRailTabs.forEach { tab ->
            PrimaryTabButton(
                tab = tab,
                selected = selected == tab.screen,
                onSelected = onSelected,
                modifier = Modifier
                    .weight(1f)
                    .height(if (compact) 44.dp else 48.dp),
            )
        }
    }
}

@Composable
private fun PrimaryTabButton(
    tab: PrimaryRailTabSpec,
    selected: Boolean,
    onSelected: (AppScreen) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (selected) {
        Button(
            onClick = { onSelected(tab.screen) },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = SkaldOrange,
                contentColor = SkaldBlack,
            ),
            contentPadding = PaddingValues(0.dp),
            modifier = modifier.semantics { contentDescription = tab.accessibilityLabel },
        ) {
            RailIcon(
                icon = tab.icon,
                selected = true,
                modifier = Modifier.size(28.dp),
            )
        }
    } else {
        OutlinedButton(
            onClick = { onSelected(tab.screen) },
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, SkaldOrange),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = SkaldBlack,
                contentColor = SkaldOrange,
            ),
            contentPadding = PaddingValues(0.dp),
            modifier = modifier.semantics { contentDescription = tab.accessibilityLabel },
        ) {
            RailIcon(
                icon = tab.icon,
                selected = false,
                modifier = Modifier.size(28.dp),
            )
        }
    }
}

@Composable
private fun OverflowMenuNavigation(
    selected: AppScreen,
    onSelected: (AppScreen) -> Unit,
    compact: Boolean,
) {
    var expanded by remember { mutableStateOf(false) }
    val activeMenuScreen = selected.takeIf { it in SkaldNavigationModel.MenuScreens }
    val buttonLabel = when {
        activeMenuScreen != null && compact -> activeMenuScreen.label
        activeMenuScreen != null -> "Menu: ${activeMenuScreen.label}"
        compact -> "Menu"
        else -> "Menu"
    }

    Box {
        OutlinedButton(
            onClick = { expanded = true },
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, SkaldOrange),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = SkaldBlack,
                contentColor = SkaldOrange,
            ),
            modifier = Modifier.widthIn(min = if (compact) 72.dp else 96.dp),
        ) {
            Text(buttonLabel, fontWeight = FontWeight.Bold)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = SkaldCharcoal,
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, SkaldOrange),
        ) {
            SkaldNavigationModel.MenuScreens.forEach { screen ->
                val isActive = selected == screen
                DropdownMenuItem(
                    text = {
                        Text(
                            text = screen.label,
                            color = if (isActive) SkaldBlack else SkaldWhite,
                            fontWeight = if (isActive) FontWeight.Black else FontWeight.Medium,
                        )
                    },
                    onClick = {
                        onSelected(screen)
                        expanded = false
                    },
                    modifier = Modifier.background(if (isActive) SkaldOrange else SkaldCharcoal),
                )
            }
        }
    }
}
