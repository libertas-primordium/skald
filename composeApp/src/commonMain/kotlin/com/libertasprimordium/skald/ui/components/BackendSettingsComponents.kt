package com.libertasprimordium.skald.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendSettingsState
import com.libertasprimordium.skald.ui.theme.SkaldMutedText

@Composable
fun BackendSettingsSummary(settings: BitcoinBackendSettingsState) {
    val selected = settings.selectedProfile
    InfoBlock(
        title = "Saved non-secret backend profiles",
        state = if (selected == null) "none selected" else "selected",
    ) {
        DetailLine("Saved profiles", settings.profiles.size.toString())
        DetailLine("Selected profile", selected?.label ?: "BACKEND_NOT_CONFIGURED")
        if (selected != null) {
            DetailLine("Selected endpoint", selected.endpointDisplay)
            DetailLine("Trust", selected.trustModel.label)
            DetailLine("Connection test", "simulated validation only - real networking disabled")
        }
        Text(
            text = "Use Nodes to add, edit, select, remove, or run simulated validation on local non-secret backend profiles. No real connection is attempted.",
            color = SkaldMutedText,
            lineHeight = 20.sp,
        )
    }
}
