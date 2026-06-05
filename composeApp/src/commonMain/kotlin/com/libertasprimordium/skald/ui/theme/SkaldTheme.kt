package com.libertasprimordium.skald.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val SkaldBlack = Color(0xFF030303)
val SkaldNearBlack = Color(0xFF050505)
val SkaldCharcoal = Color(0xFF0B0B0B)
val SkaldDarkGray = Color(0xFF181818)
val SkaldOrange = Color(0xFFFF8A00)
val SkaldOrangeSoft = Color(0xFFFFB25C)
val SkaldWhite = Color(0xFFF5F5F5)
val SkaldMutedText = Color(0xFFA7A7A7)
val SkaldWarning = Color(0xFFFFA726)
val SkaldDanger = Color(0xFFFF5252)
val SkaldSuccess = Color(0xFF6BBF8A)

private val SkaldColorScheme = darkColorScheme(
    primary = SkaldOrange,
    onPrimary = SkaldBlack,
    secondary = SkaldOrangeSoft,
    onSecondary = SkaldBlack,
    tertiary = SkaldSuccess,
    background = SkaldBlack,
    onBackground = SkaldWhite,
    surface = SkaldCharcoal,
    onSurface = SkaldWhite,
    surfaceVariant = SkaldDarkGray,
    onSurfaceVariant = SkaldMutedText,
    outline = SkaldDarkGray,
    error = SkaldDanger,
    onError = SkaldWhite,
)

@Composable
fun SkaldTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = SkaldColorScheme,
        content = content,
    )
}
