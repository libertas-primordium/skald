package com.libertasprimordium.skald.domain.recovery

import com.libertasprimordium.skald.domain.onchain.BitcoinWalletSyncBlocker
import com.libertasprimordium.skald.domain.onchain.BitcoinWalletSyncResult
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletSettingsState
import com.libertasprimordium.skald.domain.privacy.PrivacyRiskLevel
import com.libertasprimordium.skald.security.SecureStorageCapability

enum class RecoverySyncItemState(val label: String) {
    Disabled("disabled"),
    DeferredUntilEncryptedVault("deferred until encrypted vault"),
    Unavailable("unavailable"),
    MetadataOnly("metadata only"),
    TestOnlyNotRecoverable("test-only; not recoverable production state"),
}

data class RecoverySyncStatusItem(
    val label: String,
    val state: RecoverySyncItemState,
    val detail: String,
    val riskLevel: PrivacyRiskLevel,
)

data class RecoverySyncStatus(
    val title: String,
    val state: String,
    val summary: String,
    val items: List<RecoverySyncStatusItem>,
    val lockedActionLabel: String,
    val productionSyncEnabled: Boolean,
    val productionObservationPersistenceEnabled: Boolean,
    val testValidationCountsAsProductionRecovery: Boolean,
)

object RecoverySyncStatusPolicy {
    fun from(
        syncResult: BitcoinWalletSyncResult,
        descriptorWalletSettings: DescriptorWalletSettingsState,
        secureStorageCapability: SecureStorageCapability,
    ): RecoverySyncStatus =
        RecoverySyncStatus(
            title = "Sync and observation recovery",
            state = "blocked / deferred",
            summary = "Production sync is disabled. Backend observations are test-only or policy-only, and no production observation metadata is recoverable until encrypted vault storage exists.",
            items = recoveryItems(
                syncResult = syncResult,
                descriptorWalletSettings = descriptorWalletSettings,
                secureStorageCapability = secureStorageCapability,
            ),
            lockedActionLabel = "RECOVERY_SYNC_STATUS_ONLY - no production sync, observation persistence, wallet activation, signing, or broadcast path exists.",
            productionSyncEnabled = false,
            productionObservationPersistenceEnabled = false,
            testValidationCountsAsProductionRecovery = false,
        )

    private fun recoveryItems(
        syncResult: BitcoinWalletSyncResult,
        descriptorWalletSettings: DescriptorWalletSettingsState,
        secureStorageCapability: SecureStorageCapability,
    ): List<RecoverySyncStatusItem> =
        buildList {
            if (BitcoinWalletSyncBlocker.SyncDisabled in syncResult.blockers || !syncResult.productionSyncEnabled) {
                add(
                    RecoverySyncStatusItem(
                        label = "Production wallet sync",
                        state = RecoverySyncItemState.Disabled,
                        detail = "The sync facade is fail-closed. No backend client, BDK sync, UTXO scan, signing, or broadcast is attempted.",
                        riskLevel = PrivacyRiskLevel.Warning,
                    ),
                )
            }
            if (
                BitcoinWalletSyncBlocker.ObservationPersistenceUnavailable in syncResult.blockers ||
                !syncResult.observationPersistenceEnabled
            ) {
                add(
                    RecoverySyncStatusItem(
                        label = "Observation and UTXO persistence",
                        state = RecoverySyncItemState.DeferredUntilEncryptedVault,
                        detail = "Observed addresses, UTXOs, labels, transaction notes, backend metadata, and wallet history are sensitive metadata and must wait for encrypted vault storage.",
                        riskLevel = PrivacyRiskLevel.Danger,
                    ),
                )
            }
            if (!secureStorageCapability.status.availableForSecretMaterial) {
                add(
                    RecoverySyncStatusItem(
                        label = "Secure storage",
                        state = RecoverySyncItemState.Unavailable,
                        detail = "Secure storage is unavailable, so seeds, credential references, private descriptors, address indexes, and backup encryption keys cannot be persisted.",
                        riskLevel = PrivacyRiskLevel.Danger,
                    ),
                )
            }
            add(
                RecoverySyncStatusItem(
                    label = "Descriptor wallet profiles",
                    state = RecoverySyncItemState.MetadataOnly,
                    detail = if (descriptorWalletSettings.profiles.isEmpty()) {
                        "No descriptor wallet metadata profiles are saved. Test-only BDK validation does not create a production wallet profile."
                    } else {
                        "${descriptorWalletSettings.profiles.size} descriptor wallet metadata profile(s) exist, but they remain non-operational and do not store descriptors, keys, addresses, or funds."
                    },
                    riskLevel = PrivacyRiskLevel.Warning,
                ),
            )
            add(
                RecoverySyncStatusItem(
                    label = "Address index state",
                    state = RecoverySyncItemState.DeferredUntilEncryptedVault,
                    detail = "Production address index state is not persisted. Future receive recovery must preserve displayed versus backend-observed used state in encrypted metadata.",
                    riskLevel = PrivacyRiskLevel.Warning,
                ),
            )
            add(
                RecoverySyncStatusItem(
                    label = "Desktop regtest BDK validation",
                    state = RecoverySyncItemState.TestOnlyNotRecoverable,
                    detail = "Opt-in local regtest validation proves adapter behavior only. It does not create recoverable production seed, wallet, address, UTXO, or metadata state.",
                    riskLevel = PrivacyRiskLevel.Info,
                ),
            )
        }
}
