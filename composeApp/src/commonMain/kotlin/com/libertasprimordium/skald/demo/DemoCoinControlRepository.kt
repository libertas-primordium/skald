package com.libertasprimordium.skald.demo

import com.libertasprimordium.skald.domain.onchain.DemoOutPointRef
import com.libertasprimordium.skald.domain.onchain.DemoUtxo
import com.libertasprimordium.skald.domain.onchain.DemoUtxoId
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletMetadataProfile
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletOrigin
import com.libertasprimordium.skald.domain.onchain.SpendabilityState
import com.libertasprimordium.skald.domain.onchain.UtxoAge
import com.libertasprimordium.skald.domain.onchain.UtxoClusterId
import com.libertasprimordium.skald.domain.onchain.UtxoLabel
import com.libertasprimordium.skald.domain.onchain.UtxoPrivacyState
import com.libertasprimordium.skald.domain.onchain.UtxoSource
import com.libertasprimordium.skald.domain.onchain.UtxoStatus

class DemoCoinControlRepository {
    fun demoUtxosFor(
        profiles: List<DescriptorWalletMetadataProfile>,
    ): List<DemoUtxo> =
        profiles
            .sortedBy { it.id.value }
            .flatMapIndexed { index, profile ->
                listOf(
                    demoUtxo(
                        profile = profile,
                        profileIndex = index + 1,
                        slot = 1,
                        amountSats = 55_000,
                        privacyState = privacyStateFor(profile),
                    ),
                    demoUtxo(
                        profile = profile,
                        profileIndex = index + 1,
                        slot = 2,
                        amountSats = 33_000,
                        privacyState = UtxoPrivacyState.ChangeSuspected,
                    ),
                )
            }

    private fun demoUtxo(
        profile: DescriptorWalletMetadataProfile,
        profileIndex: Int,
        slot: Int,
        amountSats: Long,
        privacyState: UtxoPrivacyState,
    ): DemoUtxo {
        val id = "DEMO_UTXO_ID_${profileIndex.toString().padStart(3, '0')}_${slot.toString().padStart(3, '0')}"
        return DemoUtxo(
            id = DemoUtxoId(id),
            walletProfileId = profile.id,
            outPoint = DemoOutPointRef(
                displayText = "DEMO_OUTPOINT_NOT_REAL_${profileIndex.toString().padStart(3, '0')}_${slot.toString().padStart(3, '0')}",
            ),
            amountSats = amountSats,
            status = UtxoStatus.Frozen,
            label = UtxoLabel("Demo UTXO placeholder $slot"),
            clusterId = UtxoClusterId("DEMO_CLUSTER_${profileIndex.toString().padStart(3, '0')}"),
            source = sourceFor(profile),
            age = UtxoAge.Unknown,
            privacyState = privacyState,
            spendabilityState = SpendabilityState.DisabledPlaceholder,
            originNote = "Demo only - not scanned from a backend - not spendable.",
            warnings = listOf(
                "Demo only",
                "Not scanned from a backend",
                "Not spendable",
            ),
        )
    }

    private fun sourceFor(profile: DescriptorWalletMetadataProfile): UtxoSource =
        when (profile.origin) {
            DescriptorWalletOrigin.ImportedDescriptorPlanned -> UtxoSource.ImportedDescriptor
            DescriptorWalletOrigin.ImportedSingleKeyPlanned -> UtxoSource.ImportedSingleKey
            DescriptorWalletOrigin.NostrNpubWatchOnlyPlanned -> UtxoSource.NostrNpubWatchOnly
            DescriptorWalletOrigin.NostrNsecSpendPlanned -> UtxoSource.NostrNsecImportedSpend
            DescriptorWalletOrigin.ExternalSignerPlanned,
            DescriptorWalletOrigin.HardwareSignerPlanned,
            -> UtxoSource.ExternalSigner
            else -> UtxoSource.DemoPlaceholder
        }

    private fun privacyStateFor(profile: DescriptorWalletMetadataProfile): UtxoPrivacyState =
        when (profile.origin) {
            DescriptorWalletOrigin.ImportedSingleKeyPlanned -> UtxoPrivacyState.ImportedKeyLinked
            DescriptorWalletOrigin.NostrNpubWatchOnlyPlanned,
            DescriptorWalletOrigin.NostrNsecSpendPlanned,
            -> UtxoPrivacyState.NostrLinked
            else -> UtxoPrivacyState.CleanUnknown
        }
}
