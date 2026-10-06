package com.libertasprimordium.skald.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.libertasprimordium.skald.ui.theme.SkaldBlack
import com.libertasprimordium.skald.ui.theme.SkaldCharcoal
import com.libertasprimordium.skald.ui.theme.SkaldDarkGray
import com.libertasprimordium.skald.ui.theme.SkaldMutedText
import com.libertasprimordium.skald.ui.theme.SkaldNearBlack
import com.libertasprimordium.skald.ui.theme.SkaldOrange
import com.libertasprimordium.skald.ui.theme.SkaldOrangeSoft
import com.libertasprimordium.skald.ui.theme.SkaldWhite

@Composable
fun ScreenTitle(title: String, subtitle: String) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(title, color = SkaldWhite, fontSize = 28.sp, fontWeight = FontWeight.Black)
        Text(subtitle, color = SkaldMutedText, fontSize = 14.sp)
    }
}

@Composable
fun SkaldCard(
    title: String? = null,
    state: String? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    Surface(
        color = Color.Transparent,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, SkaldDarkGray),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .background(
                    Brush.linearGradient(
                        colors = listOf(SkaldCharcoal, SkaldNearBlack, SkaldDarkGray),
                    ),
                )
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            if (title != null || state != null) {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    if (title != null) {
                        Text(
                            text = title,
                            color = SkaldWhite,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    if (state != null) {
                        StatusPill(state)
                    }
                }
            }
            content()
        }
    }
}

@Composable
fun WarningStrip(text: String) {
    Surface(
        color = SkaldBlack,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, SkaldOrange),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = text,
            color = SkaldOrangeSoft,
            lineHeight = 20.sp,
            modifier = Modifier.padding(12.dp),
        )
    }
}

@Composable
fun StatusPill(text: String) {
    Surface(
        color = SkaldOrange,
        shape = RoundedCornerShape(8.dp),
    ) {
        Text(
            text = text,
            color = SkaldBlack,
            fontSize = 12.sp,
            fontWeight = FontWeight.Black,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
        )
    }
}
