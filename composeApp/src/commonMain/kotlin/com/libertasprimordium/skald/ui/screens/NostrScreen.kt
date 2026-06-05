package com.libertasprimordium.skald.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.libertasprimordium.skald.domain.nostr.NostrPaymentIntent
import com.libertasprimordium.skald.ui.components.CardGrid
import com.libertasprimordium.skald.ui.components.LockedAction
import com.libertasprimordium.skald.ui.components.ScreenTitle
import com.libertasprimordium.skald.ui.components.SkaldCard
import com.libertasprimordium.skald.ui.components.WarningStrip
import com.libertasprimordium.skald.ui.theme.SkaldWarning

@Composable
fun NostrScreen(intents: List<NostrPaymentIntent>) {
    ScreenTitle("Nostr", "Identity-linked payment and recovery intent.")
    WarningStrip("Nostr-derived Bitcoin payments are identity-linked by design. Use public npub payments only when public association is intentional. Recovery tools can reduce additional linkage but cannot erase public history.")
    CardGrid {
        intents.forEach { intent ->
            SkaldCard(
                title = intent.title,
                state = intent.mode.label,
            ) {
                Text(intent.publicLinkage, color = SkaldWarning, lineHeight = 20.sp)
                Spacer(Modifier.height(10.dp))
                LockedAction(intent.disabledReason)
            }
        }
    }
}
