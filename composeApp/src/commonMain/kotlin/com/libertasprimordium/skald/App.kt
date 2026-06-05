package com.libertasprimordium.skald

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.libertasprimordium.skald.demo.DemoPortfolioRepository
import com.libertasprimordium.skald.domain.onchain.BackendProfileValidationResult
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendProfileId
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendValidator
import com.libertasprimordium.skald.domain.onchain.EditableBitcoinBackendProfileInput
import com.libertasprimordium.skald.security.DisabledSecureSecretStorage
import com.libertasprimordium.skald.security.SecureSecretStorage
import com.libertasprimordium.skald.security.toUiStatus
import com.libertasprimordium.skald.settings.InMemorySettingsStorage
import com.libertasprimordium.skald.settings.PersistentSettingsRepository
import com.libertasprimordium.skald.settings.SettingsRepository
import com.libertasprimordium.skald.settings.SettingsWriteResult
import com.libertasprimordium.skald.ui.components.SkaldAppScaffold
import com.libertasprimordium.skald.ui.navigation.AppScreen
import com.libertasprimordium.skald.ui.screens.CashuScreen
import com.libertasprimordium.skald.ui.screens.LightningScreen
import com.libertasprimordium.skald.ui.screens.NodesScreen
import com.libertasprimordium.skald.ui.screens.NostrScreen
import com.libertasprimordium.skald.ui.screens.OnChainScreen
import com.libertasprimordium.skald.ui.screens.OverviewScreen
import com.libertasprimordium.skald.ui.screens.RecoveryScreen
import com.libertasprimordium.skald.ui.screens.SettingsScreen

@Composable
fun SkaldApp(
    settingsRepository: SettingsRepository = PersistentSettingsRepository(InMemorySettingsStorage()),
    secureStorage: SecureSecretStorage = DisabledSecureSecretStorage(),
) {
    val repository = remember { DemoPortfolioRepository() }
    val snapshot = remember { repository.loadPortfolioSnapshot() }
    val onChainProfile = remember { repository.onChainProfile() }
    val lightningConnectors = remember { repository.lightningConnectors() }
    val cashuMints = remember { repository.cashuMints() }
    val nostrIntents = remember { repository.nostrPaymentIntents() }
    val networks = remember { repository.developmentNetworks() }
    val secureStorageStatus = remember { secureStorage.capability.toUiStatus() }
    var selectedScreen by remember { mutableStateOf(AppScreen.Overview) }
    var backendSettings by remember { mutableStateOf(settingsRepository.loadBitcoinBackendSettings()) }
    var backendValidation by remember { mutableStateOf<BackendProfileValidationResult?>(null) }
    var backendMessage by remember {
        mutableStateOf("Backend profiles are local non-secret settings only. Connection testing is disabled.")
    }

    fun reloadBackendSettings() {
        backendSettings = settingsRepository.loadBitcoinBackendSettings()
    }

    fun saveBackendProfile(input: EditableBitcoinBackendProfileInput) {
        val validation = BitcoinBackendValidator.validate(input)
        backendValidation = validation
        val profile = validation.normalizedProfile
        if (profile == null) {
            backendMessage = "Profile not saved. Fix validation errors first."
            return
        }
        when (val result = settingsRepository.saveBitcoinBackendProfile(profile)) {
            is SettingsWriteResult.Saved -> {
                backendSettings = result.state
                backendMessage = "Saved non-secret backend profile. Connection testing and wallet sync remain disabled."
            }
            is SettingsWriteResult.Rejected -> {
                backendMessage = result.reason
                reloadBackendSettings()
            }
        }
    }

    fun selectBackendProfile(id: BitcoinBackendProfileId) {
        when (val result = settingsRepository.selectBitcoinBackendProfile(id)) {
            is SettingsWriteResult.Saved -> {
                backendSettings = result.state
                backendMessage = "Selected backend profile for future testnet-only use. No connection was attempted."
            }
            is SettingsWriteResult.Rejected -> {
                backendMessage = result.reason
                reloadBackendSettings()
            }
        }
    }

    fun deleteBackendProfile(id: BitcoinBackendProfileId) {
        when (val result = settingsRepository.deleteBitcoinBackendProfile(id)) {
            is SettingsWriteResult.Saved -> {
                backendSettings = result.state
                backendMessage = "Deleted backend profile. Selected profile was cleared if needed."
            }
            is SettingsWriteResult.Rejected -> {
                backendMessage = result.reason
                reloadBackendSettings()
            }
        }
    }

    SkaldAppScaffold(
        selectedScreen = selectedScreen,
        onSelectedScreen = { selectedScreen = it },
    ) { screen ->
        when (screen) {
            AppScreen.Overview -> OverviewScreen(snapshot)
            AppScreen.OnChain -> OnChainScreen(onChainProfile, backendSettings)
            AppScreen.Lightning -> LightningScreen(lightningConnectors)
            AppScreen.Cashu -> CashuScreen(cashuMints)
            AppScreen.Nostr -> NostrScreen(nostrIntents)
            AppScreen.Recovery -> RecoveryScreen(
                recovery = snapshot.recoveryStatus,
                secureStorageStatus = secureStorageStatus,
            )
            AppScreen.Nodes -> NodesScreen(
                settings = backendSettings,
                validation = backendValidation,
                message = backendMessage,
                secureStorageStatus = secureStorageStatus,
                onSaveProfile = ::saveBackendProfile,
                onSelectProfile = ::selectBackendProfile,
                onDeleteProfile = ::deleteBackendProfile,
            )
            AppScreen.Settings -> SettingsScreen(
                networks = networks,
                secureStorageStatus = secureStorageStatus,
            )
        }
    }
}
