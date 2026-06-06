package com.libertasprimordium.skald.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.libertasprimordium.skald.ui.navigation.RailIconSpec
import com.libertasprimordium.skald.ui.theme.SkaldBlack
import com.libertasprimordium.skald.ui.theme.SkaldOrange
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun RailIcon(
    icon: RailIconSpec,
    selected: Boolean,
    modifier: Modifier = Modifier,
) {
    val color = if (selected) SkaldBlack else SkaldOrange
    when (icon) {
        RailIconSpec.Eye -> OverviewEyeIcon(color = color, modifier = modifier)
        RailIconSpec.LinkedBoxes -> OnChainLinkedBoxesIcon(color = color, modifier = modifier)
        RailIconSpec.LightningBolt -> LightningBoltIcon(color = color, modifier = modifier)
        RailIconSpec.CashuNut -> CashuNutIcon(color = color, modifier = modifier)
    }
}

@Composable
fun GearIcon(
    color: Color,
    modifier: Modifier = Modifier,
) {
    Canvas(modifier = modifier) {
        val stroke = 2.dp.toPx()
        val center = Offset(size.width / 2f, size.height / 2f)
        val outer = size.minDimension * 0.34f
        val inner = size.minDimension * 0.13f

        repeat(8) { index ->
            val angle = (PI.toFloat() * 2f * index) / 8f
            val start = Offset(
                x = center.x + cos(angle) * outer,
                y = center.y + sin(angle) * outer,
            )
            val end = Offset(
                x = center.x + cos(angle) * size.minDimension * 0.45f,
                y = center.y + sin(angle) * size.minDimension * 0.45f,
            )
            drawLine(color = color, start = start, end = end, strokeWidth = stroke)
        }
        drawCircle(color = color, radius = outer, center = center, style = Stroke(width = stroke))
        drawCircle(color = color, radius = inner, center = center, style = Stroke(width = stroke))
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
private fun OnChainLinkedBoxesIcon(
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

@Composable
private fun LightningBoltIcon(
    color: Color,
    modifier: Modifier,
) {
    Canvas(modifier = modifier) {
        val path = Path().apply {
            moveTo(size.width * 0.57f, size.height * 0.08f)
            lineTo(size.width * 0.30f, size.height * 0.54f)
            lineTo(size.width * 0.49f, size.height * 0.54f)
            lineTo(size.width * 0.39f, size.height * 0.92f)
            lineTo(size.width * 0.72f, size.height * 0.43f)
            lineTo(size.width * 0.52f, size.height * 0.43f)
            close()
        }
        drawPath(path = path, color = color)
    }
}

@Composable
private fun CashuNutIcon(
    color: Color,
    modifier: Modifier,
) {
    Canvas(modifier = modifier) {
        val stroke = 2.dp.toPx()
        val center = Offset(size.width / 2f, size.height / 2f)
        val radius = size.minDimension * 0.34f
        val path = Path()
        repeat(6) { index ->
            val angle = PI.toFloat() / 6f + (PI.toFloat() * 2f * index) / 6f
            val point = Offset(
                x = center.x + cos(angle) * radius,
                y = center.y + sin(angle) * radius,
            )
            if (index == 0) {
                path.moveTo(point.x, point.y)
            } else {
                path.lineTo(point.x, point.y)
            }
        }
        path.close()
        drawPath(path = path, color = color, style = Stroke(width = stroke))
        drawCircle(
            color = color,
            radius = size.minDimension * 0.11f,
            center = center,
            style = Stroke(width = stroke),
        )
    }
}
