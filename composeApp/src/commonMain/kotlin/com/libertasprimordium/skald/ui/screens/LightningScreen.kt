package com.libertasprimordium.skald.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.libertasprimordium.skald.domain.lightning.LightningConnectorProfile
import com.libertasprimordium.skald.ui.components.BulletList
import com.libertasprimordium.skald.ui.components.CardGrid
import com.libertasprimordium.skald.ui.components.DetailLine
import com.libertasprimordium.skald.ui.components.InfoBlock
import com.libertasprimordium.skald.ui.components.RailScreenHeader
import com.libertasprimordium.skald.ui.components.ScreenTitle
import com.libertasprimordium.skald.ui.components.SkaldCard
import com.libertasprimordium.skald.ui.components.WarningStrip
import com.libertasprimordium.skald.ui.navigation.AppScreen
import com.libertasprimordium.skald.ui.navigation.RailTabOptionId
import com.libertasprimordium.skald.ui.theme.SkaldMutedText
import com.libertasprimordium.skald.ui.theme.SkaldOrangeSoft
import com.libertasprimordium.skald.ui.theme.SkaldWarning

@Composable
fun LightningScreen(
    connectors: List<LightningConnectorProfile>,
    onNavigate: (AppScreen) -> Unit,
) {
    var selectedOption by remember { mutableStateOf(RailTabOptionId.DefaultView) }

    RailScreenHeader(
        screen = AppScreen.Lightning,
        subtitle = "Connector status and availability summary.",
        selectedOptionId = selectedOption,
        onOptionSelected = { selectedOption = it },
        onNavigate = onNavigate,
    )
    WarningStrip("No Lightning credentials are accepted or stored. Background service, liquidity, BOLT12, and LSP warnings are planned only.")

    when (selectedOption) {
        RailTabOptionId.LightningConnectorSetup -> LightningConnectorSetupDetails(connectors)
        RailTabOptionId.LightningBolt12Capabilities -> LightningCapabilityDetails(connectors, "BOLT12 capability details")
        RailTabOptionId.LightningBackgroundWarnings -> LightningBackgroundWarnings()
        RailTabOptionId.LightningPermissionModel -> LightningPermissionModelDetails(connectors)
        RailTabOptionId.LightningEmbeddedMode -> LightningEmbeddedModeDetails(connectors)
        else -> LightningDefaultView(connectors)
    }
}

@Composable
private fun LightningDefaultView(connectors: List<LightningConnectorProfile>) {
    SkaldCard(title = "Lightning connector summary", state = "not configured") {
        Text("Balances: placeholder only. Send and receive are unavailable.", color = SkaldMutedText, lineHeight = 20.sp)
        connectors.forEach { connector ->
            InfoBlock(
                title = connector.type.label,
                state = connector.status.label,
            ) {
                DetailLine("Setup", connector.setupComplexity.label)
                DetailLine("Send", "disabled - connector credentials not implemented")
                DetailLine("Receive", "disabled - invoice creation not implemented")
            }
        }
    }
}

@Composable
private fun LightningConnectorSetupDetails(connectors: List<LightningConnectorProfile>) {
    ScreenTitle("Lightning setup details", "Non-operational connector planning only.")
    CardGrid {
        connectors.forEach { connector ->
            SkaldCard(
                title = connector.type.label,
                state = "${connector.status.label} - setup ${connector.setupComplexity.label}",
            ) {
                Text("Permission model placeholder", color = SkaldOrangeSoft, fontWeight = FontWeight.SemiBold)
                Text(
                    connector.permissionModel.joinToString(", ") { it.label },
                    color = SkaldMutedText,
                    lineHeight = 20.sp,
                )
                Spacer(Modifier.height(10.dp))
                BulletList(connector.plannedCapabilities)
                Text(connector.recoveryWarning, color = SkaldWarning, lineHeight = 20.sp)
            }
        }
    }
}

@Composable
private fun LightningCapabilityDetails(
    connectors: List<LightningConnectorProfile>,
    title: String,
) {
    SkaldCard(title = title, state = "planned only") {
        connectors.forEach { connector ->
            Text(connector.type.label, color = SkaldOrangeSoft, fontWeight = FontWeight.Bold)
            BulletList(connector.plannedCapabilities)
        }
        Text("No connector API call, invoice, payment, BOLT12 offer, or credential storage exists.", color = SkaldWarning, lineHeight = 20.sp)
    }
}

@Composable
private fun LightningBackgroundWarnings() {
    SkaldCard(title = "Background service warnings", state = "planned") {
        BulletList(
            listOf(
                "Embedded Lightning mode may require a background service later.",
                "Android may kill long-running services.",
                "Background operation can affect battery and network privacy.",
                "No background Lightning service exists in this scaffold.",
            ),
        )
    }
}

@Composable
private fun LightningPermissionModelDetails(connectors: List<LightningConnectorProfile>) {
    SkaldCard(title = "Remote-node permission model", state = "credentials disabled") {
        connectors.forEach { connector ->
            Text(connector.type.label, color = SkaldOrangeSoft, fontWeight = FontWeight.Bold)
            Text(
                connector.permissionModel.joinToString(", ") { it.label },
                color = SkaldMutedText,
                lineHeight = 20.sp,
            )
        }
    }
}

@Composable
private fun LightningEmbeddedModeDetails(connectors: List<LightningConnectorProfile>) {
    val embedded = connectors.firstOrNull { it.type.label.contains("Embedded", ignoreCase = true) }
    SkaldCard(title = "Embedded Lightning planned mode", state = "not implemented") {
        Text(
            text = embedded?.recoveryWarning ?: "Embedded Lightning is a future mode only. No node, channel state, protocol-library integration, or background service exists.",
            color = SkaldMutedText,
            lineHeight = 20.sp,
        )
        Text("Lightning channel recovery is not covered by a single app seed.", color = SkaldWarning, lineHeight = 20.sp)
    }
}
