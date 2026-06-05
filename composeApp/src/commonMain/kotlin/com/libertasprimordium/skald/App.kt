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
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletProfileId
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletWorkflow
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletWorkflowReview
import com.libertasprimordium.skald.domain.onchain.EditableBitcoinBackendProfileInput
import com.libertasprimordium.skald.domain.onchain.EditableDescriptorWalletProfileInput
import com.libertasprimordium.skald.security.DisabledSecureSecretStorage
import com.libertasprimordium.skald.security.SecureSecretStorage
import com.libertasprimordium.skald.security.toUiStatus
import com.libertasprimordium.skald.settings.DescriptorWalletSettingsWriteResult
import com.libertasprimordium.skald.settings.InMemorySettingsStorage
import com.libertasprimordium.skald.settings.PersistentSettingsRepository
import com.libertasprimordium.skald.settings.SettingsWriteResult
import com.libertasprimordium.skald.settings.SkaldSettingsRepository
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
    settingsRepository: SkaldSettingsRepository = PersistentSettingsRepository(InMemorySettingsStorage()),
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
    var descriptorWalletSettings by remember { mutableStateOf(settingsRepository.loadDescriptorWalletSettings()) }
    var backendValidation by remember { mutableStateOf<BackendProfileValidationResult?>(null) }
    var descriptorWalletReview by remember { mutableStateOf<DescriptorWalletWorkflowReview?>(null) }
    var backendMessage by remember {
        mutableStateOf("Backend profiles are local non-secret settings only. Connection testing is disabled.")
    }
    var descriptorWalletMessage by remember {
        mutableStateOf("Descriptor wallet profiles are non-secret metadata only. No wallet material is created.")
    }

    fun reloadBackendSettings() {
        backendSettings = settingsRepository.loadBitcoinBackendSettings()
    }

    fun reloadDescriptorWalletSettings() {
        descriptorWalletSettings = settingsRepository.loadDescriptorWalletSettings()
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

    fun saveDescriptorWalletProfile(input: EditableDescriptorWalletProfileInput) {
        val review = DescriptorWalletWorkflow.review(input, secureStorage.capability)
        descriptorWalletReview = review
        val profile = review.profile
        if (profile == null) {
            descriptorWalletMessage = "Profile metadata not saved. Fix validation errors and acknowledgements first."
            return
        }
        when (val result = settingsRepository.saveDescriptorWalletProfile(profile)) {
            is DescriptorWalletSettingsWriteResult.Saved -> {
                descriptorWalletSettings = result.state
                descriptorWalletMessage = "Saved non-operational descriptor wallet metadata. No keys, descriptors, addresses, or funds were created."
            }
            is DescriptorWalletSettingsWriteResult.Rejected -> {
                descriptorWalletMessage = result.reason
                reloadDescriptorWalletSettings()
            }
        }
    }

    fun selectDescriptorWalletProfile(id: DescriptorWalletProfileId) {
        when (val result = settingsRepository.selectDescriptorWalletProfile(id)) {
            is DescriptorWalletSettingsWriteResult.Saved -> {
                descriptorWalletSettings = result.state
                descriptorWalletMessage = "Selected descriptor wallet metadata for review only. No wallet operation was enabled."
            }
            is DescriptorWalletSettingsWriteResult.Rejected -> {
                descriptorWalletMessage = result.reason
                reloadDescriptorWalletSettings()
            }
        }
    }

    fun deleteDescriptorWalletProfile(id: DescriptorWalletProfileId) {
        when (val result = settingsRepository.deleteDescriptorWalletProfile(id)) {
            is DescriptorWalletSettingsWriteResult.Saved -> {
                descriptorWalletSettings = result.state
                descriptorWalletMessage = "Deleted descriptor wallet metadata. Selected profile was cleared if needed."
            }
            is DescriptorWalletSettingsWriteResult.Rejected -> {
                descriptorWalletMessage = result.reason
                reloadDescriptorWalletSettings()
            }
        }
    }

    SkaldAppScaffold(
        selectedScreen = selectedScreen,
        onSelectedScreen = { selectedScreen = it },
    ) { screen ->
        when (screen) {
            AppScreen.Overview -> OverviewScreen(snapshot)
            AppScreen.OnChain -> OnChainScreen(
                profile = onChainProfile,
                backendSettings = backendSettings,
                descriptorWalletSettings = descriptorWalletSettings,
                descriptorWalletReview = descriptorWalletReview,
                descriptorWalletMessage = descriptorWalletMessage,
                secureStorageStatus = secureStorageStatus,
                onSaveDescriptorWalletProfile = ::saveDescriptorWalletProfile,
                onSelectDescriptorWalletProfile = ::selectDescriptorWalletProfile,
                onDeleteDescriptorWalletProfile = ::deleteDescriptorWalletProfile,
            )
            AppScreen.Lightning -> LightningScreen(lightningConnectors)
            AppScreen.Cashu -> CashuScreen(cashuMints)
            AppScreen.Nostr -> NostrScreen(nostrIntents)
            AppScreen.Recovery -> RecoveryScreen(
                recovery = snapshot.recoveryStatus,
                secureStorageStatus = secureStorageStatus,
                descriptorWalletSettings = descriptorWalletSettings,
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
