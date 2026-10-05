package com.libertasprimordium.skald.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.libertasprimordium.skald.ui.navigation.NavigationIconSpec
import com.libertasprimordium.skald.ui.theme.SkaldBlack
import com.libertasprimordium.skald.ui.theme.SkaldOrange

@Composable
fun NavigationIcon(
    icon: NavigationIconSpec,
    selected: Boolean,
    modifier: Modifier = Modifier,
) {
    val color = if (selected) SkaldBlack else SkaldOrange
    when (icon) {
        NavigationIconSpec.Eye -> OverviewEyeIcon(color = color, modifier = modifier)
        NavigationIconSpec.LinkedBoxes -> WalletLinkedBoxesIcon(color = color, modifier = modifier)
    }
}

@Composable
private fun OverviewEyeIcon(
    color: Color,
    modifier: Modifier,
) {
    Canvas(modifier = modifier) {
        val stroke = 2.dp.toPx()
        val eyeWidth = size.width * 0.76f
        val eyeHeight = size.height * 0.44f
        val topLeft = Offset(
            x = (size.width - eyeWidth) / 2f,
            y = (size.height - eyeHeight) / 2f,
        )
        drawOval(
            color = color,
            topLeft = topLeft,
            size = Size(eyeWidth, eyeHeight),
            style = Stroke(width = stroke),
        )
        drawCircle(
            color = color,
            radius = size.minDimension * 0.12f,
            center = Offset(size.width / 2f, size.height / 2f),
        )
    }
}

@Composable
private fun WalletLinkedBoxesIcon(
    color: Color,
    modifier: Modifier,
) {
    Canvas(modifier = modifier) {
        val stroke = 2.dp.toPx()
        val box = Size(size.width * 0.22f, size.height * 0.24f)
        val y = (size.height - box.height) / 2f
        val leftX = size.width * 0.08f
        val centerX = (size.width - box.width) / 2f
        val rightX = size.width - leftX - box.width
        val boxes = listOf(leftX, centerX, rightX)

        boxes.zipWithNext().forEach { (startX, endX) ->
            drawLine(
                color = color,
                start = Offset(startX + box.width, size.height / 2f),
                end = Offset(endX, size.height / 2f),
                strokeWidth = stroke,
            )
        }
        boxes.forEach { x ->
            drawRoundRect(
                color = color,
                topLeft = Offset(x, y),
                size = box,
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(3.dp.toPx()),
                style = Stroke(width = stroke),
            )
        }
    }
}

