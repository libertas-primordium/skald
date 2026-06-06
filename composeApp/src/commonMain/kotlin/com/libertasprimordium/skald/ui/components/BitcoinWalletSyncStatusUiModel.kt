package com.libertasprimordium.skald.ui.components

import com.libertasprimordium.skald.domain.onchain.BitcoinWalletSyncBlocker
import com.libertasprimordium.skald.domain.onchain.BitcoinWalletSyncResult
import com.libertasprimordium.skald.domain.onchain.BitcoinWalletSyncWarning

data class BitcoinWalletSyncStatusUiModel(
    val title: String,
    val state: String,
    val summary: String,
    val backendProfile: String,
    val walletProfile: String,
    val requestedNetwork: String,
    val blockers: List<String>,
    val warnings: List<String>,
    val lockedActionLabel: String,
    val canRunSync: Boolean,
    val networkAttempted: Boolean,
)

object BitcoinWalletSyncStatusUiMapper {
    fun from(result: BitcoinWalletSyncResult): BitcoinWalletSyncStatusUiModel =
        BitcoinWalletSyncStatusUiModel(
            title = "Production sync preflight",
            state = result.status.label,
            summary = "Preflight only. Sync is not implemented and no network connection is attempted.",
            backendProfile = result.preflight.backendProfileId?.value ?: "BACKEND_NOT_CONFIGURED",
            walletProfile = result.preflight.walletProfileId?.value ?: "WALLET_NOT_OPERATIONAL",
            requestedNetwork = result.preflight.requestedNetwork?.label ?: "NETWORK_NOT_SELECTED",
            blockers = result.blockers.sortedBy { it.ordinal }.map(::blockerText),
            warnings = result.warnings.sortedBy { it.ordinal }.map(::warningText),
            lockedActionLabel = "SYNC_NOT_IMPLEMENTED - production wallet sync, backend observation persistence, signing, and broadcast remain disabled.",
            canRunSync = false,
            networkAttempted = false,
        )

    private fun blockerText(blocker: BitcoinWalletSyncBlocker): String =
        when (blocker) {
            BitcoinWalletSyncBlocker.SyncDisabled -> "Production sync is not implemented."
            BitcoinWalletSyncBlocker.BackendNotConfigured -> "No backend profile is selected."
            BitcoinWalletSyncBlocker.EndpointInvalid -> "Selected endpoint metadata is invalid."
            BitcoinWalletSyncBlocker.CredentialMaterialRejected -> "Credential or userinfo material was rejected."
            BitcoinWalletSyncBlocker.MainnetDisabled -> "Mainnet is disabled."
            BitcoinWalletSyncBlocker.NoOperationalWallet -> "No operational descriptor wallet is available."
            BitcoinWalletSyncBlocker.WalletContextMissing -> "Wallet metadata context is missing."
            BitcoinWalletSyncBlocker.AddressCandidateMissing -> "Receive-address candidate is unavailable."
            BitcoinWalletSyncBlocker.SecureStorageUnavailable -> "Secure storage is unavailable."
            BitcoinWalletSyncBlocker.ProductionBackendDisabled -> "Production backend adapter is disabled."
            BitcoinWalletSyncBlocker.CredentialsUnavailable -> "Backend credentials are unavailable."
            BitcoinWalletSyncBlocker.SecureMetadataPersistenceUnavailable -> "Secure metadata persistence is unavailable."
            BitcoinWalletSyncBlocker.ObservationPersistenceUnavailable -> "Observation persistence is unavailable."
            BitcoinWalletSyncBlocker.AddressIndexPersistenceUnavailable -> "Address index persistence is unavailable."
            BitcoinWalletSyncBlocker.ReceiveAddressPolicyBlocked -> "Receive-address policy blocks sync preflight."
        }

    private fun warningText(warning: BitcoinWalletSyncWarning): String =
        when (warning) {
            BitcoinWalletSyncWarning.ProductionSyncDisabled -> "Production sync remains disabled."
            BitcoinWalletSyncWarning.NoNetworkAttempted -> "No network connection is attempted."
            BitcoinWalletSyncWarning.NoObservationPersisted -> "No backend observation is persisted."
            BitcoinWalletSyncWarning.NoSecureMetadataPersisted -> "No sensitive wallet metadata is persisted."
            BitcoinWalletSyncWarning.EndpointPolicyOnly -> "Endpoint parsing is policy-only."
            BitcoinWalletSyncWarning.PublicBackendPrivacyLeak -> "Public backends can observe wallet queries."
            BitcoinWalletSyncWarning.BackendCanLinkWalletQueries -> "The selected backend can link wallet queries."
            BitcoinWalletSyncWarning.OnionTorLabelPreserved -> "Onion/Tor endpoint labeling is preserved."
            BitcoinWalletSyncWarning.TorTransportNotImplemented -> "Tor transport is not implemented."
            BitcoinWalletSyncWarning.UserOwnedNodePreferred -> "A user-owned node is preferred."
            BitcoinWalletSyncWarning.CredentialsRequireSecureStorage -> "Credential references require secure storage."
            BitcoinWalletSyncWarning.MetadataRequiresEncryptedVault -> "Sensitive wallet metadata requires encrypted vault storage."
            BitcoinWalletSyncWarning.ReceiveAddressPolicyRequired -> "Receive-address policy is required before sync."
            BitcoinWalletSyncWarning.CoinControlRequiredBeforeSpend -> "Coin control is required before any future spend."
            BitcoinWalletSyncWarning.NoSkaldManagedInfrastructure -> "No Skald-managed infrastructure is configured."
        }
}
