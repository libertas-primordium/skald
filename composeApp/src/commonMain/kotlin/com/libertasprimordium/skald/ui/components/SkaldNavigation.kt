package com.libertasprimordium.skald.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.libertasprimordium.skald.ui.navigation.AppScreen
import com.libertasprimordium.skald.ui.navigation.SkaldNavigationModel
import com.libertasprimordium.skald.ui.theme.SkaldBlack
import com.libertasprimordium.skald.ui.theme.SkaldMutedText
import com.libertasprimordium.skald.ui.theme.SkaldOrange
import com.libertasprimordium.skald.ui.theme.SkaldOrangeSoft
import com.libertasprimordium.skald.ui.theme.SkaldWhite

@Composable
fun SkaldHeader(compact: Boolean) {
    Column {
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
        SkaldNavigationModel.PrimaryScreens.forEach { screen ->
            NavigationButton(
                screen = screen,
                selected = selected == screen,
                onSelected = onSelected,
                modifier = Modifier.weight(1f).heightIn(min = 48.dp),
            )
        }
    }
}

@Composable
private fun NavigationButton(
    screen: AppScreen,
    selected: Boolean,
    onSelected: (AppScreen) -> Unit,
    modifier: Modifier = Modifier,
) {
    var focused by remember { mutableStateOf(false) }
    Button(
        onClick = { onSelected(screen) },
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(
            if (focused) 2.dp else 1.dp,
            if (focused) SkaldOrangeSoft else SkaldOrange,
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) SkaldOrange else SkaldBlack,
            contentColor = if (selected) SkaldBlack else SkaldOrange,
        ),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 12.dp),
        modifier = modifier
            .onFocusChanged { focused = it.isFocused }
            .semantics { this.selected = selected },
    ) {
        Text(screen.label, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
    }
}
