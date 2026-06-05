package com.libertasprimordium.skald.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.libertasprimordium.skald.domain.lightning.LightningConnectorProfile
import com.libertasprimordium.skald.ui.components.BulletList
import com.libertasprimordium.skald.ui.components.CardGrid
import com.libertasprimordium.skald.ui.components.ScreenTitle
import com.libertasprimordium.skald.ui.components.SkaldCard
import com.libertasprimordium.skald.ui.components.WarningStrip
import com.libertasprimordium.skald.ui.theme.SkaldMutedText
import com.libertasprimordium.skald.ui.theme.SkaldOrangeSoft
import com.libertasprimordium.skald.ui.theme.SkaldWarning

@Composable
fun LightningScreen(connectors: List<LightningConnectorProfile>) {
    ScreenTitle("Lightning", "Connector-oriented architecture in setup-ease order.")
    WarningStrip("No Lightning credentials are accepted or stored. Background service, liquidity, BOLT12, and LSP warnings are planned only.")
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
