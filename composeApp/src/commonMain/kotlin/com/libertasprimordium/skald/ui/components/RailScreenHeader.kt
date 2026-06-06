package com.libertasprimordium.skald.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Surface
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
import com.libertasprimordium.skald.ui.navigation.RailTabOption
import com.libertasprimordium.skald.ui.navigation.RailTabOptionId
import com.libertasprimordium.skald.ui.navigation.SkaldNavigationModel
import com.libertasprimordium.skald.ui.theme.SkaldBlack
import com.libertasprimordium.skald.ui.theme.SkaldCharcoal
import com.libertasprimordium.skald.ui.theme.SkaldMutedText
import com.libertasprimordium.skald.ui.theme.SkaldOrange
import com.libertasprimordium.skald.ui.theme.SkaldWhite

@Composable
fun RailScreenHeader(
    screen: AppScreen,
    subtitle: String,
    selectedOptionId: RailTabOptionId,
    onOptionSelected: (RailTabOptionId) -> Unit,
    onNavigate: (AppScreen) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.Top,
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(screen.label, color = SkaldWhite, fontSize = 24.sp, fontWeight = FontWeight.Black)
            Text(subtitle, color = SkaldMutedText, fontSize = 13.sp, lineHeight = 18.sp)
            if (selectedOptionId != RailTabOptionId.DefaultView) {
                Text(
                    text = selectedOptionId.label,
                    color = SkaldOrange,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
        RailOptionsMenu(
            screen = screen,
            selectedOptionId = selectedOptionId,
            onOptionSelected = onOptionSelected,
            onNavigate = onNavigate,
        )
    }
}

@Composable
private fun RailOptionsMenu(
    screen: AppScreen,
    selectedOptionId: RailTabOptionId,
    onOptionSelected: (RailTabOptionId) -> Unit,
    onNavigate: (AppScreen) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    val options = SkaldNavigationModel.optionsFor(screen)

    Box {
        Surface(
            color = SkaldBlack,
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, SkaldOrange),
            modifier = Modifier
                .size(44.dp)
                .semantics { contentDescription = "${screen.label} options" }
                .clickable { expanded = true },
        ) {
            Box(contentAlignment = Alignment.Center) {
                GearIcon(
                    color = SkaldOrange,
                    modifier = Modifier.size(24.dp),
                )
            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = SkaldCharcoal,
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, SkaldOrange),
        ) {
            options.forEach { option ->
                RailOptionMenuItem(
                    option = option,
                    selected = option.id == selectedOptionId,
                    onClick = {
                        expanded = false
                        val destination = option.destination
                        if (destination != null) {
                            onNavigate(destination)
                        } else {
                            onOptionSelected(option.id)
                        }
                    },
                )
            }
        }
    }
}

@Composable
private fun RailOptionMenuItem(
    option: RailTabOption,
    selected: Boolean,
    onClick: () -> Unit,
) {
    DropdownMenuItem(
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    text = option.id.label,
                    color = if (selected) SkaldBlack else SkaldWhite,
                    fontWeight = if (selected) FontWeight.Black else FontWeight.Medium,
                )
                option.destination?.let { destination ->
                    Text(
                        text = "Open ${destination.label}",
                        color = if (selected) SkaldBlack else SkaldMutedText,
                        fontSize = 12.sp,
                    )
                }
            }
        },
        onClick = onClick,
        modifier = Modifier
            .background(if (selected) SkaldOrange else SkaldCharcoal)
            .padding(vertical = 2.dp),
    )
}
