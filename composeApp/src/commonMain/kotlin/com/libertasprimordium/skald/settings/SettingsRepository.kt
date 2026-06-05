package com.libertasprimordium.skald.settings

import com.libertasprimordium.skald.domain.onchain.BitcoinBackendProfile
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendProfileId
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendSettingsState
import com.libertasprimordium.skald.domain.onchain.BroadcastState
import com.libertasprimordium.skald.domain.onchain.CoinControlDraft
import com.libertasprimordium.skald.domain.onchain.CoinControlDraftId
import com.libertasprimordium.skald.domain.onchain.CoinControlDraftSettingsState
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletMetadataProfile
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletProfileId
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletSettingsState
import com.libertasprimordium.skald.domain.onchain.PsbtConstructionState
import com.libertasprimordium.skald.domain.onchain.PsbtDraftState
import com.libertasprimordium.skald.domain.onchain.SigningState

interface SettingsRepository {
    fun loadBitcoinBackendSettings(): BitcoinBackendSettingsState
    fun saveBitcoinBackendProfile(profile: BitcoinBackendProfile): SettingsWriteResult
    fun selectBitcoinBackendProfile(id: BitcoinBackendProfileId): SettingsWriteResult
    fun deleteBitcoinBackendProfile(id: BitcoinBackendProfileId): SettingsWriteResult
}

interface DescriptorWalletSettingsRepository {
    fun loadDescriptorWalletSettings(): DescriptorWalletSettingsState
    fun saveDescriptorWalletProfile(profile: DescriptorWalletMetadataProfile): DescriptorWalletSettingsWriteResult
    fun selectDescriptorWalletProfile(id: DescriptorWalletProfileId): DescriptorWalletSettingsWriteResult
    fun deleteDescriptorWalletProfile(id: DescriptorWalletProfileId): DescriptorWalletSettingsWriteResult
}

interface CoinControlDraftSettingsRepository {
    fun loadCoinControlDraftSettings(): CoinControlDraftSettingsState
    fun saveCoinControlDraft(draft: CoinControlDraft): CoinControlDraftSettingsWriteResult
    fun selectCoinControlDraft(id: CoinControlDraftId): CoinControlDraftSettingsWriteResult
    fun deleteCoinControlDraft(id: CoinControlDraftId): CoinControlDraftSettingsWriteResult
}

interface SkaldSettingsRepository : SettingsRepository,
    DescriptorWalletSettingsRepository,
    CoinControlDraftSettingsRepository

sealed interface SettingsWriteResult {
    data class Saved(val state: BitcoinBackendSettingsState) : SettingsWriteResult
    data class Rejected(val reason: String) : SettingsWriteResult
}

sealed interface DescriptorWalletSettingsWriteResult {
    data class Saved(val state: DescriptorWalletSettingsState) : DescriptorWalletSettingsWriteResult
    data class Rejected(val reason: String) : DescriptorWalletSettingsWriteResult
}

sealed interface CoinControlDraftSettingsWriteResult {
    data class Saved(val state: CoinControlDraftSettingsState) : CoinControlDraftSettingsWriteResult
    data class Rejected(val reason: String) : CoinControlDraftSettingsWriteResult
}

enum class SettingsStorageKey(
    val preferenceKey: String,
    val fileName: String,
) {
    BitcoinBackendSettings("bitcoin_backend_settings_v1", "backend-settings.txt"),
    DescriptorWalletSettings("descriptor_wallet_settings_v1", "descriptor-wallet-settings.txt"),
    CoinControlDraftSettings("coin_control_draft_settings_v1", "coin-control-draft-settings.txt"),
}

interface SettingsStorage {
    fun readText(key: SettingsStorageKey): String?
    fun writeText(key: SettingsStorageKey, value: String)

    fun readText(): String? =
        readText(SettingsStorageKey.BitcoinBackendSettings)

    fun writeText(value: String) {
        writeText(SettingsStorageKey.BitcoinBackendSettings, value)
    }
}

class InMemorySettingsStorage(
    initialValue: String? = null,
) : SettingsStorage {
    private val values: MutableMap<SettingsStorageKey, String> = mutableMapOf<SettingsStorageKey, String>().apply {
        if (initialValue != null) {
            put(SettingsStorageKey.BitcoinBackendSettings, initialValue)
        }
    }

    override fun readText(key: SettingsStorageKey): String? =
        values[key]

    override fun writeText(key: SettingsStorageKey, value: String) {
        values[key] = value
    }
}

class PersistentSettingsRepository(
    private val storage: SettingsStorage,
) : SkaldSettingsRepository {
    override fun loadBitcoinBackendSettings(): BitcoinBackendSettingsState =
        BitcoinBackendSettingsCodec.decode(storage.readText(SettingsStorageKey.BitcoinBackendSettings))
            .withConsistentSelection()

    override fun saveBitcoinBackendProfile(profile: BitcoinBackendProfile): SettingsWriteResult {
        val current = loadBitcoinBackendSettings()
        val sanitizedProfile = profile.copy(credentialReference = null)
        val updatedProfiles = current.profiles
            .filterNot { it.id == profile.id } + sanitizedProfile
        val updated = current.copy(profiles = updatedProfiles).withConsistentSelection()
        return write(updated)
    }

    override fun selectBitcoinBackendProfile(id: BitcoinBackendProfileId): SettingsWriteResult {
        val current = loadBitcoinBackendSettings()
        if (current.profiles.none { it.id == id }) {
            return SettingsWriteResult.Rejected("Backend profile is not saved.")
        }
        return write(current.copy(selectedProfileId = id).withConsistentSelection())
    }

    override fun deleteBitcoinBackendProfile(id: BitcoinBackendProfileId): SettingsWriteResult {
        val current = loadBitcoinBackendSettings()
        val updatedProfiles = current.profiles.filterNot { it.id == id }
        val updatedSelected = current.selectedProfileId?.takeIf { selected ->
            selected != id && updatedProfiles.any { it.id == selected }
        }
        return write(
            current.copy(
                profiles = updatedProfiles,
                selectedProfileId = updatedSelected,
            ).withConsistentSelection(),
        )
    }

    private fun write(state: BitcoinBackendSettingsState): SettingsWriteResult =
        try {
            storage.writeText(
                SettingsStorageKey.BitcoinBackendSettings,
                BitcoinBackendSettingsCodec.encode(state.withConsistentSelection()),
            )
            SettingsWriteResult.Saved(loadBitcoinBackendSettings())
        } catch (error: Exception) {
            SettingsWriteResult.Rejected(error.message ?: "Settings write failed.")
        }

    override fun loadDescriptorWalletSettings(): DescriptorWalletSettingsState =
        DescriptorWalletSettingsCodec.decode(storage.readText(SettingsStorageKey.DescriptorWalletSettings))
            .withConsistentSelection()

    override fun saveDescriptorWalletProfile(
        profile: DescriptorWalletMetadataProfile,
    ): DescriptorWalletSettingsWriteResult {
        val current = loadDescriptorWalletSettings()
        val sanitizedProfile = profile.copy(
            descriptorTextState = "DESCRIPTOR_TEXT_NOT_STORED",
            keyMaterialState = "KEY_MATERIAL_NOT_CREATED",
            isOperational = false,
        )
        val updatedProfiles = current.profiles
            .filterNot { it.id == profile.id } + sanitizedProfile
        val updated = current.copy(profiles = updatedProfiles).withConsistentSelection()
        return writeDescriptorWalletSettings(updated)
    }

    override fun selectDescriptorWalletProfile(
        id: DescriptorWalletProfileId,
    ): DescriptorWalletSettingsWriteResult {
        val current = loadDescriptorWalletSettings()
        if (current.profiles.none { it.id == id }) {
            return DescriptorWalletSettingsWriteResult.Rejected("Descriptor wallet profile is not saved.")
        }
        return writeDescriptorWalletSettings(current.copy(selectedProfileId = id).withConsistentSelection())
    }

    override fun deleteDescriptorWalletProfile(
        id: DescriptorWalletProfileId,
    ): DescriptorWalletSettingsWriteResult {
        val current = loadDescriptorWalletSettings()
        val updatedProfiles = current.profiles.filterNot { it.id == id }
        val updatedSelected = current.selectedProfileId?.takeIf { selected ->
            selected != id && updatedProfiles.any { it.id == selected }
        }
        return writeDescriptorWalletSettings(
            current.copy(
                profiles = updatedProfiles,
                selectedProfileId = updatedSelected,
            ).withConsistentSelection(),
        )
    }

    private fun writeDescriptorWalletSettings(
        state: DescriptorWalletSettingsState,
    ): DescriptorWalletSettingsWriteResult =
        try {
            storage.writeText(
                SettingsStorageKey.DescriptorWalletSettings,
                DescriptorWalletSettingsCodec.encode(state.withConsistentSelection()),
            )
            DescriptorWalletSettingsWriteResult.Saved(loadDescriptorWalletSettings())
        } catch (error: Exception) {
            DescriptorWalletSettingsWriteResult.Rejected(error.message ?: "Descriptor wallet settings write failed.")
        }

    override fun loadCoinControlDraftSettings(): CoinControlDraftSettingsState =
        CoinControlDraftSettingsCodec.decode(storage.readText(SettingsStorageKey.CoinControlDraftSettings))
            .withConsistentSelection()

    override fun saveCoinControlDraft(
        draft: CoinControlDraft,
    ): CoinControlDraftSettingsWriteResult {
        val current = loadCoinControlDraftSettings()
        val sanitizedDraft = draft.copy(
            selectedInputs = emptyList(),
            privacyWarnings = emptyList(),
            placeholderPsbt = "PSBT_NOT_CREATED",
            psbtDraftState = PsbtDraftState.PsbtConstructionBlocked,
            psbtConstructionState = PsbtConstructionState.NotImplemented,
            signingState = SigningState.Disabled,
            broadcastState = BroadcastState.Disabled,
            isNonOperationalDraft = true,
            isExecutable = false,
        )
        val updatedDrafts = current.drafts
            .filterNot { it.id == draft.id } + sanitizedDraft
        return writeCoinControlDraftSettings(
            current.copy(drafts = updatedDrafts).withConsistentSelection(),
        )
    }

    override fun selectCoinControlDraft(
        id: CoinControlDraftId,
    ): CoinControlDraftSettingsWriteResult {
        val current = loadCoinControlDraftSettings()
        if (current.drafts.none { it.id == id }) {
            return CoinControlDraftSettingsWriteResult.Rejected("Coin-control draft metadata is not saved.")
        }
        return writeCoinControlDraftSettings(current.copy(selectedDraftId = id).withConsistentSelection())
    }

    override fun deleteCoinControlDraft(
        id: CoinControlDraftId,
    ): CoinControlDraftSettingsWriteResult {
        val current = loadCoinControlDraftSettings()
        val updatedDrafts = current.drafts.filterNot { it.id == id }
        val updatedSelected = current.selectedDraftId?.takeIf { selected ->
            selected != id && updatedDrafts.any { it.id == selected }
        }
        return writeCoinControlDraftSettings(
            current.copy(
                drafts = updatedDrafts,
                selectedDraftId = updatedSelected,
            ).withConsistentSelection(),
        )
    }

    private fun writeCoinControlDraftSettings(
        state: CoinControlDraftSettingsState,
    ): CoinControlDraftSettingsWriteResult =
        try {
            storage.writeText(
                SettingsStorageKey.CoinControlDraftSettings,
                CoinControlDraftSettingsCodec.encode(state.withConsistentSelection()),
            )
            CoinControlDraftSettingsWriteResult.Saved(loadCoinControlDraftSettings())
        } catch (error: Exception) {
            CoinControlDraftSettingsWriteResult.Rejected(error.message ?: "Coin-control draft settings write failed.")
        }
}

fun BitcoinBackendSettingsState.withConsistentSelection(): BitcoinBackendSettingsState {
    val validSelectedId = selectedProfileId?.takeIf { selected ->
        profiles.any { it.id == selected }
    }
    return copy(
        selectedProfileId = validSelectedId,
        profiles = profiles.map { profile ->
            profile.copy(isSelected = profile.id == validSelectedId)
        },
    )
}

fun DescriptorWalletSettingsState.withConsistentSelection(): DescriptorWalletSettingsState {
    val validSelectedId = selectedProfileId?.takeIf { selected ->
        profiles.any { it.id == selected }
    }
    return copy(
        selectedProfileId = validSelectedId,
        profiles = profiles.map { profile ->
            profile.copy(isSelected = profile.id == validSelectedId)
        },
    )
}

fun CoinControlDraftSettingsState.withConsistentSelection(): CoinControlDraftSettingsState {
    val validSelectedId = selectedDraftId?.takeIf { selected ->
        drafts.any { it.id == selected }
    }
    return copy(
        selectedDraftId = validSelectedId,
        drafts = drafts,
    )
}
