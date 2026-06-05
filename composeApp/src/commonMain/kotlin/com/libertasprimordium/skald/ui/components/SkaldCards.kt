package com.libertasprimordium.skald.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.libertasprimordium.skald.domain.privacy.PrivacyRisk
import com.libertasprimordium.skald.domain.privacy.PrivacyRiskLevel
import com.libertasprimordium.skald.domain.quote.OperationQuote
import com.libertasprimordium.skald.ui.theme.SkaldBlack
import com.libertasprimordium.skald.ui.theme.SkaldCharcoal
import com.libertasprimordium.skald.ui.theme.SkaldDanger
import com.libertasprimordium.skald.ui.theme.SkaldDarkGray
import com.libertasprimordium.skald.ui.theme.SkaldMutedText
import com.libertasprimordium.skald.ui.theme.SkaldNearBlack
import com.libertasprimordium.skald.ui.theme.SkaldOrange
import com.libertasprimordium.skald.ui.theme.SkaldOrangeSoft
import com.libertasprimordium.skald.ui.theme.SkaldSuccess
import com.libertasprimordium.skald.ui.theme.SkaldWarning
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.Top,
                ) {
                    if (title != null) {
                        Text(
                            text = title,
                            color = SkaldWhite,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f),
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
fun CardGrid(content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        content = content,
    )
}

@Composable
fun RailBalanceRow(label: String, sats: Long, detail: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, SkaldDarkGray, RoundedCornerShape(8.dp))
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(Modifier.weight(1f)) {
            Text(label, color = SkaldWhite, fontWeight = FontWeight.Bold)
            Text(detail, color = SkaldMutedText, fontSize = 13.sp, lineHeight = 18.sp)
        }
        if (sats > 0) {
            Text("${sats.toSatsText()} sats", color = SkaldOrangeSoft, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun InfoBlock(
    title: String,
    state: String,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, SkaldDarkGray, RoundedCornerShape(8.dp))
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.Top,
        ) {
            Text(title, color = SkaldWhite, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
            Text(state, color = SkaldOrangeSoft, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
        content()
    }
}

@Composable
fun DetailLine(label: String, detail: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.Top,
    ) {
        Text(label, color = SkaldWhite, fontSize = 13.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.42f))
        Text(detail, color = SkaldMutedText, fontSize = 13.sp, lineHeight = 18.sp, modifier = Modifier.weight(0.58f))
    }
}

@Composable
fun BulletList(items: List<String>) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        items.forEach { item ->
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("-", color = SkaldOrange)
                Text(item, color = SkaldMutedText, lineHeight = 20.sp)
            }
        }
    }
}

@Composable
fun DisabledActionArea(
    title: String,
    actions: List<Pair<String, String>>,
) {
    SkaldCard(title = title, state = "not implemented yet") {
        actions.forEach { (label, reason) ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, SkaldDarkGray, RoundedCornerShape(8.dp))
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = label,
                    color = SkaldWhite,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 20.sp,
                )
                LockedAction(reason, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@Composable
fun LockedAction(
    reason: String,
    modifier: Modifier = Modifier,
) {
    Surface(
        color = SkaldBlack,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, SkaldOrange),
        modifier = modifier,
    ) {
        Column(Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
            Text("Not implemented yet", color = SkaldOrange, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            Text(reason, color = SkaldMutedText, fontSize = 12.sp, lineHeight = 16.sp)
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

@Composable
fun QuoteCard(quote: OperationQuote) {
    SkaldCard(title = "Quote Engine", state = if (quote.isPlaceholder) "placeholder" else "active") {
        Text(
            "${quote.operation}: ${quote.sourceRail.label} to ${quote.destinationRail.label}",
            color = SkaldWhite,
            fontWeight = FontWeight.Bold,
        )
        Text("Amount in: ${quote.amountInSats.toSatsText()} sats", color = SkaldMutedText)
        Text("Amount out: ${quote.amountOutSats.toSatsText()} sats", color = SkaldMutedText)
        Text("Fees: not estimated", color = SkaldWarning)
        Text("Expiry: ${quote.expiry}", color = SkaldMutedText)
        Text("${quote.trustBoundary.title}: ${quote.trustBoundary.detail}", color = SkaldMutedText, lineHeight = 20.sp)
        BulletList(quote.failureModes)
    }
}

@Composable
fun PrivacyRiskList(risks: List<PrivacyRisk>) {
    SkaldCard(title = "Privacy Analyzer", state = "placeholder") {
        risks.forEach { risk ->
            Text(
                text = "${risk.level.label.uppercase()} - ${risk.title}",
                color = riskColor(risk.level),
                fontWeight = FontWeight.Bold,
            )
            Text(risk.detail, color = SkaldMutedText, lineHeight = 20.sp)
        }
    }
}

fun riskColor(level: PrivacyRiskLevel): Color =
    when (level) {
        PrivacyRiskLevel.Info -> SkaldSuccess
        PrivacyRiskLevel.Warning -> SkaldWarning
        PrivacyRiskLevel.Danger -> SkaldDanger
    }

fun Long.toSatsText(): String =
    toString()
        .reversed()
        .chunked(3)
        .joinToString(",")
        .reversed()
