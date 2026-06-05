package com.libertasprimordium.skald.settings

import com.libertasprimordium.skald.domain.onchain.BitcoinBackendProfile
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendProfileId
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendSettingsState

interface SettingsRepository {
    fun loadBitcoinBackendSettings(): BitcoinBackendSettingsState
    fun saveBitcoinBackendProfile(profile: BitcoinBackendProfile): SettingsWriteResult
    fun selectBitcoinBackendProfile(id: BitcoinBackendProfileId): SettingsWriteResult
    fun deleteBitcoinBackendProfile(id: BitcoinBackendProfileId): SettingsWriteResult
}

sealed interface SettingsWriteResult {
    data class Saved(val state: BitcoinBackendSettingsState) : SettingsWriteResult
    data class Rejected(val reason: String) : SettingsWriteResult
}

interface SettingsStorage {
    fun readText(): String?
    fun writeText(value: String)
}

class InMemorySettingsStorage(
    initialValue: String? = null,
) : SettingsStorage {
    private var value: String? = initialValue

    override fun readText(): String? = value

    override fun writeText(value: String) {
        this.value = value
    }
}

class PersistentSettingsRepository(
    private val storage: SettingsStorage,
) : SettingsRepository {
    override fun loadBitcoinBackendSettings(): BitcoinBackendSettingsState =
        BitcoinBackendSettingsCodec.decode(storage.readText()).withConsistentSelection()

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
            storage.writeText(BitcoinBackendSettingsCodec.encode(state.withConsistentSelection()))
            SettingsWriteResult.Saved(loadBitcoinBackendSettings())
        } catch (error: Exception) {
            SettingsWriteResult.Rejected(error.message ?: "Settings write failed.")
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
