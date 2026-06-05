package com.libertasprimordium.skald.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.libertasprimordium.skald.ui.navigation.AppScreen
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
    if (compact) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.Top,
            ) {
                Wordmark(Modifier.weight(1f))
                OverflowMenuNavigation(
                    selected = selected,
                    onSelected = onSelected,
                )
            }
            StatusPill("DEVELOPMENT TESTNET")
        }
    } else {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Wordmark()
            Spacer(Modifier.weight(1f))
            StatusPill("DEVELOPMENT TESTNET")
            OverflowMenuNavigation(
                selected = selected,
                onSelected = onSelected,
            )
        }
    }
}

@Composable
private fun Wordmark(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "sk\u00e4ld",
            color = SkaldWhite,
            fontSize = 36.sp,
            fontWeight = FontWeight.Black,
        )
        Text(
            text = "Skald Vault",
            color = SkaldMutedText,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
        )
    }
}

@Composable
fun PrimaryNavigation(
    selected: AppScreen,
    compact: Boolean,
    onSelected: (AppScreen) -> Unit,
) {
    if (compact) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            SkaldNavigationModel.PrimaryScreens.chunked(2).forEach { rowScreens ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    rowScreens.forEach { screen ->
                        PrimaryTabButton(
                            screen = screen,
                            selected = selected == screen,
                            onSelected = onSelected,
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp),
                        )
                    }
                    if (rowScreens.size == 1) {
                        Spacer(Modifier.weight(1f))
                    }
                }
            }
        }
    } else {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            SkaldNavigationModel.PrimaryScreens.forEach { screen ->
                PrimaryTabButton(
                    screen = screen,
                    selected = selected == screen,
                    onSelected = onSelected,
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                )
            }
        }
    }
}

@Composable
private fun PrimaryTabButton(
    screen: AppScreen,
    selected: Boolean,
    onSelected: (AppScreen) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (selected) {
        Button(
            onClick = { onSelected(screen) },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = SkaldOrange,
                contentColor = SkaldBlack,
            ),
            modifier = modifier,
        ) {
            Text(screen.label, fontWeight = FontWeight.Bold)
        }
    } else {
        OutlinedButton(
            onClick = { onSelected(screen) },
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, SkaldOrange),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = SkaldBlack,
                contentColor = SkaldOrange,
            ),
            modifier = modifier,
        ) {
            Text(screen.label, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
private fun OverflowMenuNavigation(
    selected: AppScreen,
    onSelected: (AppScreen) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    val activeMenuScreen = selected.takeIf { it in SkaldNavigationModel.MenuScreens }
    val buttonLabel = activeMenuScreen?.let { "Menu: ${it.label}" } ?: "Menu"

    Box {
        OutlinedButton(
            onClick = { expanded = true },
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, SkaldOrange),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = SkaldBlack,
                contentColor = SkaldOrange,
            ),
            modifier = Modifier.widthIn(min = 96.dp),
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
