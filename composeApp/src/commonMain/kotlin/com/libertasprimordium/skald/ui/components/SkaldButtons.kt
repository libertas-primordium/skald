package com.libertasprimordium.skald.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.libertasprimordium.skald.ui.theme.SkaldBlack
import com.libertasprimordium.skald.ui.theme.SkaldDanger
import com.libertasprimordium.skald.ui.theme.SkaldDarkGray
import com.libertasprimordium.skald.ui.theme.SkaldMutedText
import com.libertasprimordium.skald.ui.theme.SkaldOrange

@Composable
fun SkaldSmallButton(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    danger: Boolean = false,
) {
    if (selected) {
        Button(
            onClick = onClick,
            enabled = enabled,
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = SkaldOrange,
                contentColor = SkaldBlack,
                disabledContainerColor = SkaldDarkGray,
                disabledContentColor = SkaldMutedText,
            ),
            modifier = modifier.height(44.dp),
        ) {
            Text(label, fontWeight = FontWeight.Bold)
        }
    } else {
        OutlinedButton(
            onClick = onClick,
            enabled = enabled,
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, if (danger) SkaldDanger else SkaldOrange),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = SkaldBlack,
                contentColor = if (danger) SkaldDanger else SkaldOrange,
                disabledContainerColor = SkaldBlack,
                disabledContentColor = SkaldMutedText,
            ),
            modifier = modifier.height(44.dp),
        ) {
            Text(label, fontWeight = FontWeight.Bold)
        }
    }
}
