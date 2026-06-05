package com.libertasprimordium.skald.settings

import com.libertasprimordium.skald.domain.onchain.BroadcastState
import com.libertasprimordium.skald.domain.onchain.ChangeDraft
import com.libertasprimordium.skald.domain.onchain.ChangeOutputState
import com.libertasprimordium.skald.domain.onchain.CoinControlAcknowledgement
import com.libertasprimordium.skald.domain.onchain.CoinControlBlockingIssue
import com.libertasprimordium.skald.domain.onchain.CoinControlDraft
import com.libertasprimordium.skald.domain.onchain.CoinControlDraftId
import com.libertasprimordium.skald.domain.onchain.CoinControlDraftLabel
import com.libertasprimordium.skald.domain.onchain.CoinControlDraftSettingsState
import com.libertasprimordium.skald.domain.onchain.CoinControlDraftState
import com.libertasprimordium.skald.domain.onchain.CoinControlReviewState
import com.libertasprimordium.skald.domain.onchain.CoinControlSelectionState
import com.libertasprimordium.skald.domain.onchain.DemoUtxoId
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletProfileId
import com.libertasprimordium.skald.domain.onchain.FeeDraft
import com.libertasprimordium.skald.domain.onchain.FeeReviewState
import com.libertasprimordium.skald.domain.onchain.OutputDraft
import com.libertasprimordium.skald.domain.onchain.PrivacyReviewState
import com.libertasprimordium.skald.domain.onchain.PsbtConstructionState
import com.libertasprimordium.skald.domain.onchain.PsbtDraftBlockingIssue
import com.libertasprimordium.skald.domain.onchain.PsbtDraftCapability
import com.libertasprimordium.skald.domain.onchain.PsbtDraftState
import com.libertasprimordium.skald.domain.onchain.SigningState

object CoinControlDraftSettingsCodec {
    private const val Version = "skald.coin-control-draft-settings.v1"
    private const val Empty = "-"

    fun encode(state: CoinControlDraftSettingsState): String {
        val consistent = state.withConsistentSelection()
        return buildString {
            appendLine(Version)
            appendLine("selected|${encodePart(consistent.selectedDraftId?.value ?: Empty)}")
            consistent.drafts.forEach { draft ->
                appendLine(encodeDraft(draft))
            }
        }
    }

    fun decode(raw: String?): CoinControlDraftSettingsState {
        if (raw.isNullOrBlank()) return CoinControlDraftSettingsState.Empty
        return runCatching {
            val lines = raw.lineSequence()
                .map { it.trimEnd() }
                .filter { it.isNotBlank() }
                .toList()
            if (lines.firstOrNull() != Version) {
                return@runCatching CoinControlDraftSettingsState.Empty
            }

            val selectedId = lines
                .firstOrNull { it.startsWith("selected|") }
                ?.split("|")
                ?.getOrNull(1)
                ?.let(::decodePart)
                ?.takeIf { it != Empty }
                ?.let(::CoinControlDraftId)

            val drafts = lines
                .filter { it.startsWith("draft|") }
                .mapNotNull(::decodeDraft)

            CoinControlDraftSettingsState(
                drafts = drafts,
                selectedDraftId = selectedId,
            ).withConsistentSelection()
        }.getOrElse {
            CoinControlDraftSettingsState.Empty
        }
    }

    private fun encodeDraft(draft: CoinControlDraft): String {
        val parts = listOf(
            "draft",
            draft.id.value,
            draft.label.value,
            draft.walletProfileId.value,
            draft.walletProfileLabel,
            draft.selectedDemoUtxoIds.joinToString(",") { it.value }.ifBlank { Empty },
            draft.outputDraft.recipientNote,
            draft.outputDraft.amountSats.toString(),
            draft.acknowledgements.joinToString(",") { it.name }.ifBlank { Empty },
            draft.state.name,
            draft.selectionState.name,
            draft.reviewState.name,
            draft.feeReviewState.name,
            draft.privacyReviewState.name,
            draft.psbtDraftState.name,
            draft.psbtConstructionState.name,
            draft.signingState.name,
            draft.broadcastState.name,
            draft.coinControlBlockingIssues.joinToString(",") { it.name }.ifBlank { Empty },
            draft.psbtBlockingIssues.joinToString(",") { it.name }.ifBlank { Empty },
            draft.psbtCapabilities.joinToString(",") { it.name }.ifBlank { Empty },
        )
        return parts.joinToString("|") { encodePart(it) }
    }

    private fun decodeDraft(line: String): CoinControlDraft? {
        val parts = line.split("|").map(::decodePart)
        if (parts.size != 21 || parts[0] != "draft") return null

        val id = CoinControlDraftId(parts[1].takeIf { it.isNotBlank() } ?: return null)
        val label = parts[2].trim().takeIf { it.isNotBlank() } ?: return null
        val walletProfileId = DescriptorWalletProfileId(parts[3].takeIf { it.isNotBlank() } ?: return null)
        val walletProfileLabel = parts[4].trim().takeIf { it.isNotBlank() } ?: return null
        val selectedIds = decodeDemoUtxoIds(parts[5]).takeIf { it.isNotEmpty() } ?: return null
        val recipientNote = parts[6].trim().takeIf { it.isNotBlank() } ?: return null
        val amountSats = parts[7].toLongOrNull()?.takeIf { it > 0L } ?: return null
        val acknowledgements = decodeEnumSet<CoinControlAcknowledgement>(parts[8])
        val state = enumValueOrNull<CoinControlDraftState>(parts[9]) ?: return null
        val selectionState = enumValueOrNull<CoinControlSelectionState>(parts[10]) ?: return null
        val reviewState = enumValueOrNull<CoinControlReviewState>(parts[11]) ?: return null
        val feeReviewState = enumValueOrNull<FeeReviewState>(parts[12]) ?: return null
        val privacyReviewState = enumValueOrNull<PrivacyReviewState>(parts[13]) ?: return null
        val psbtDraftState = enumValueOrNull<PsbtDraftState>(parts[14]) ?: return null
        val psbtConstructionState = enumValueOrNull<PsbtConstructionState>(parts[15]) ?: return null
        val signingState = enumValueOrNull<SigningState>(parts[16]) ?: return null
        val broadcastState = enumValueOrNull<BroadcastState>(parts[17]) ?: return null
        val coinControlIssues = decodeEnumSet<CoinControlBlockingIssue>(parts[18])
        val psbtIssues = decodeEnumSet<PsbtDraftBlockingIssue>(parts[19])
        val capabilities = decodeEnumSet<PsbtDraftCapability>(parts[20])
            .takeIf { it.isNotEmpty() }
            ?: return null

        return CoinControlDraft(
            id = id,
            label = CoinControlDraftLabel(label),
            walletProfileId = walletProfileId,
            walletProfileLabel = walletProfileLabel,
            selectedDemoUtxoIds = selectedIds,
            selectedInputs = emptyList(),
            outputDraft = OutputDraft(
                recipientNote = recipientNote,
                amountSats = amountSats,
                isAddressFieldAvailable = false,
                warning = "Metadata only. Do not paste a Bitcoin address here yet.",
            ),
            changeDraft = ChangeDraft(
                state = ChangeOutputState.DisabledPlaceholder,
                amountSats = null,
                addressDisplay = "DEMO_CHANGE_ADDRESS_NOT_DERIVED",
                warning = "Change calculation is placeholder-only. No transaction output is created.",
            ),
            feeDraft = FeeDraft(
                networkFeeSats = null,
                feeRateSatPerVbyte = null,
                reviewState = feeReviewState,
                reason = "Fee estimation requires a real transaction builder and backend fee source.",
            ),
            state = state,
            selectionState = selectionState,
            reviewState = reviewState,
            feeReviewState = feeReviewState,
            privacyReviewState = privacyReviewState,
            psbtDraftState = psbtDraftState,
            psbtConstructionState = psbtConstructionState,
            signingState = signingState,
            broadcastState = broadcastState,
            acknowledgements = acknowledgements,
            coinControlBlockingIssues = coinControlIssues,
            psbtBlockingIssues = psbtIssues,
            psbtCapabilities = capabilities,
            privacyWarnings = emptyList(),
            placeholderPsbt = "PSBT_NOT_CREATED",
            isNonOperationalDraft = true,
            isExecutable = false,
        )
    }

    private fun decodeDemoUtxoIds(raw: String): Set<DemoUtxoId> {
        if (raw == Empty || raw.isBlank()) return emptySet()
        return raw.split(",")
            .mapNotNull { value ->
                value
                    .takeIf { it.startsWith("DEMO_UTXO_ID_") }
                    ?.takeUnless(::looksLikeRealTxid)
                    ?.let(::DemoUtxoId)
            }
            .toSet()
    }

    private inline fun <reified T : Enum<T>> decodeEnumSet(raw: String): Set<T> {
        if (raw == Empty || raw.isBlank()) return emptySet()
        return raw.split(",")
            .mapNotNull { enumValueOrNull<T>(it) }
            .toSet()
    }

    private inline fun <reified T : Enum<T>> enumValueOrNull(value: String): T? =
        enumValues<T>().firstOrNull { it.name == value }

    private fun looksLikeRealTxid(value: String): Boolean =
        value.length == 64 && value.all { character ->
            character in '0'..'9' || character in 'a'..'f' || character in 'A'..'F'
        }

    private fun encodePart(value: String): String =
        value
            .replace("%", "%25")
            .replace("|", "%7C")
            .replace("\n", "%0A")
            .replace("\r", "%0D")

    private fun decodePart(value: String): String =
        value
            .replace("%0D", "\r")
            .replace("%0A", "\n")
            .replace("%7C", "|")
            .replace("%25", "%")
}
