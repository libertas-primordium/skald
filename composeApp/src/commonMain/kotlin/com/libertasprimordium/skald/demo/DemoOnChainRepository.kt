package com.libertasprimordium.skald.demo

import com.libertasprimordium.skald.domain.onchain.BackendCredentialPolicy
import com.libertasprimordium.skald.domain.onchain.BackendEndpointValidationState
import com.libertasprimordium.skald.domain.onchain.BackupArtifactType
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendCapability
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendPrivacyLevel
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendProfile
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendRepository
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendStatus
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendTrustModel
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendType
import com.libertasprimordium.skald.domain.onchain.ChangeOutputPlan
import com.libertasprimordium.skald.domain.onchain.ChangeOutputState
import com.libertasprimordium.skald.domain.onchain.CoinControlApprovalState
import com.libertasprimordium.skald.domain.onchain.CoinControlPolicy
import com.libertasprimordium.skald.domain.onchain.CoinControlReview
import com.libertasprimordium.skald.domain.onchain.CoinControlWarning
import com.libertasprimordium.skald.domain.onchain.CoinSelectionDraft
import com.libertasprimordium.skald.domain.onchain.CoinSelectionIntent
import com.libertasprimordium.skald.domain.onchain.DescriptorBackupStatus
import com.libertasprimordium.skald.domain.onchain.DescriptorDisplayState
import com.libertasprimordium.skald.domain.onchain.DescriptorExportState
import com.libertasprimordium.skald.domain.onchain.DescriptorScriptPolicy
import com.libertasprimordium.skald.domain.onchain.DescriptorSetStatus
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletId
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletLabel
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletOrigin
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletProfile
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletRepository
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletStatus
import com.libertasprimordium.skald.domain.onchain.DisabledWalletAction
import com.libertasprimordium.skald.domain.onchain.FeePlaceholder
import com.libertasprimordium.skald.domain.onchain.ImportedKeyPolicy
import com.libertasprimordium.skald.domain.onchain.OnChainRecoveryPlanner
import com.libertasprimordium.skald.domain.onchain.OnChainRecoveryStatus
import com.libertasprimordium.skald.domain.onchain.OnChainWalletProfile
import com.libertasprimordium.skald.domain.onchain.OutPointRef
import com.libertasprimordium.skald.domain.onchain.PsbtBroadcastPolicy
import com.libertasprimordium.skald.domain.onchain.PsbtDraft
import com.libertasprimordium.skald.domain.onchain.PsbtFailureMode
import com.libertasprimordium.skald.domain.onchain.PsbtFeeSummary
import com.libertasprimordium.skald.domain.onchain.PsbtInputSummary
import com.libertasprimordium.skald.domain.onchain.PsbtOutputSummary
import com.libertasprimordium.skald.domain.onchain.PsbtReview
import com.libertasprimordium.skald.domain.onchain.PsbtSigningPolicy
import com.libertasprimordium.skald.domain.onchain.PsbtWorkflowId
import com.libertasprimordium.skald.domain.onchain.PsbtWorkflowPlan
import com.libertasprimordium.skald.domain.onchain.PsbtWorkflowState
import com.libertasprimordium.skald.domain.onchain.RecipientOutputPlan
import com.libertasprimordium.skald.domain.onchain.RecoveryBlockingIssue
import com.libertasprimordium.skald.domain.onchain.RecoveryChecklistItem
import com.libertasprimordium.skald.domain.onchain.RecoveryChecklistState
import com.libertasprimordium.skald.domain.onchain.RecoveryRequirement
import com.libertasprimordium.skald.domain.onchain.RecoveryTransition
import com.libertasprimordium.skald.domain.onchain.SelectedUtxo
import com.libertasprimordium.skald.domain.onchain.SpendabilityState
import com.libertasprimordium.skald.domain.onchain.UtxoAge
import com.libertasprimordium.skald.domain.onchain.UtxoClusterId
import com.libertasprimordium.skald.domain.onchain.UtxoId
import com.libertasprimordium.skald.domain.onchain.UtxoLabel
import com.libertasprimordium.skald.domain.onchain.UtxoPrivacyState
import com.libertasprimordium.skald.domain.onchain.UtxoRepository
import com.libertasprimordium.skald.domain.onchain.UtxoSource
import com.libertasprimordium.skald.domain.onchain.UtxoStatus
import com.libertasprimordium.skald.domain.onchain.UtxoView
import com.libertasprimordium.skald.domain.onchain.WalletOperationResult
import com.libertasprimordium.skald.domain.onchain.WatchOnlyPolicy
import com.libertasprimordium.skald.domain.privacy.PrivacyRiskLevel

class DemoOnChainRepository :
    DescriptorWalletRepository,
    BitcoinBackendRepository,
    UtxoRepository,
    OnChainRecoveryPlanner {

    fun loadOnChainProfile(): OnChainWalletProfile =
        OnChainWalletProfile(
            label = "Descriptor-native on-chain foundation",
            status = DescriptorWalletStatus.NotCreated,
            descriptorWallets = demoWalletProfiles(),
            backendProfiles = demoBackendProfiles(),
            coinControlPolicy = strictCoinControlPolicy(),
            coinSelectionDraft = demoCoinSelectionDraft(),
            psbtWorkflow = demoPsbtWorkflow(),
            recoveryStatus = demoOnChainRecoveryStatus(),
            plannedCapabilities = listOf(
                "Descriptor-native wallet architecture",
                "Strict manual coin control",
                "PSBT import/export and external signing workflow",
                "Payjoin with user-selected endpoint or directory",
                "Silent payments later",
                "Nostr-derived Taproot watch/spend wallet modeling",
                "User-selected Bitcoin Core, Electrum, or Esplora backend",
            ),
            disabledActions = listOf(
                DisabledWalletAction("Create native descriptor wallet", "Requires descriptor wallet implementation"),
                DisabledWalletAction("Import descriptor", "Requires descriptor parser and recovery tracking"),
                DisabledWalletAction("Import watch-only descriptor", "Requires watch-only descriptor implementation"),
                DisabledWalletAction("Import npub as watch wallet", "Requires Nostr npub parser and watch descriptor implementation"),
                DisabledWalletAction("Import nsec as spend wallet", "Requires secure secret handling and identity-key risk flow"),
                DisabledWalletAction("Export descriptor", "Requires descriptor export implementation"),
                DisabledWalletAction("Open coin control", "Requires real UTXO set, quote, and approval flow"),
                DisabledWalletAction("Build PSBT", "Requires transaction construction and PSBT serializer implementation"),
            ),
        )

    override fun walletProfiles(): WalletOperationResult<List<DescriptorWalletProfile>> =
        WalletOperationResult.Placeholder(
            value = demoWalletProfiles(),
            warning = "Static demo descriptor profiles only; no descriptors, keys, or addresses exist.",
        )

    override fun backendProfiles(): WalletOperationResult<List<BitcoinBackendProfile>> =
        WalletOperationResult.Placeholder(
            value = demoBackendProfiles(),
            warning = "Static demo backend profiles only; no endpoints or credentials are configured.",
        )

    override fun utxos(walletId: DescriptorWalletId): WalletOperationResult<List<UtxoView>> =
        WalletOperationResult.Placeholder(
            value = demoUtxos().filter { it.walletId == walletId },
            warning = "Static demo UTXO views only; no chain scan or backend sync exists.",
        )

    override fun recoveryStatus(wallets: List<DescriptorWalletProfile>): WalletOperationResult<OnChainRecoveryStatus> =
        WalletOperationResult.Placeholder(
            value = demoOnChainRecoveryStatus(),
            warning = "Static demo recovery checklist only; no seed, descriptor export, or backup exists.",
        )

    private fun demoWalletProfiles(): List<DescriptorWalletProfile> =
        listOf(
            DescriptorWalletProfile(
                id = DescriptorWalletId("demo-native-descriptor"),
                label = DescriptorWalletLabel("Native descriptor wallet"),
                status = DescriptorWalletStatus.NotCreated,
                origin = DescriptorWalletOrigin.NativeAppSeed,
                scriptPolicy = DescriptorScriptPolicy.Bip86P2tr,
                descriptorDisplay = descriptorNotCreated(),
                backupStatus = DescriptorBackupStatus.DescriptorExportRequired,
                exportState = DescriptorExportState.RequiredBeforeUse,
                canSpend = false,
                importedKeyPolicy = null,
                watchOnlyPolicy = null,
                warnings = listOf(
                    "App seed is not created.",
                    "Descriptor export must be available before any real wallet flow.",
                ),
            ),
            DescriptorWalletProfile(
                id = DescriptorWalletId("demo-watch-only-descriptor"),
                label = DescriptorWalletLabel("Watch-only descriptor import"),
                status = DescriptorWalletStatus.Planned,
                origin = DescriptorWalletOrigin.ImportedWatchOnlyDescriptor,
                scriptPolicy = DescriptorScriptPolicy.Bip86P2tr,
                descriptorDisplay = descriptorNotCreated(),
                backupStatus = DescriptorBackupStatus.WatchOnlyExportRecommended,
                exportState = DescriptorExportState.Planned,
                canSpend = false,
                importedKeyPolicy = null,
                watchOnlyPolicy = WatchOnlyPolicy.CannotSpend,
                warnings = listOf("Watch-only wallets cannot spend without an external signer or private descriptor."),
            ),
            DescriptorWalletProfile(
                id = DescriptorWalletId("demo-npub-watch"),
                label = DescriptorWalletLabel("Nostr npub Taproot watch wallet"),
                status = DescriptorWalletStatus.Planned,
                origin = DescriptorWalletOrigin.NostrNpubWatchOnly,
                scriptPolicy = DescriptorScriptPolicy.Bip86P2tr,
                descriptorDisplay = descriptorNotCreated(),
                backupStatus = DescriptorBackupStatus.WatchOnlyExportRecommended,
                exportState = DescriptorExportState.Planned,
                canSpend = false,
                importedKeyPolicy = null,
                watchOnlyPolicy = WatchOnlyPolicy.CannotSpend,
                warnings = listOf("Nostr npub-derived watches are public identity-linked by design."),
            ),
            DescriptorWalletProfile(
                id = DescriptorWalletId("demo-imported-single-key"),
                label = DescriptorWalletLabel("Imported single-key Taproot wallet"),
                status = DescriptorWalletStatus.ImportedSingleKey,
                origin = DescriptorWalletOrigin.ImportedSingleKey,
                scriptPolicy = DescriptorScriptPolicy.Bip86P2tr,
                descriptorDisplay = descriptorNotCreated(),
                backupStatus = DescriptorBackupStatus.SeparateKeyBackupRequired,
                exportState = DescriptorExportState.Disabled,
                canSpend = false,
                importedKeyPolicy = ImportedKeyPolicy.SeparateBackupRequired,
                watchOnlyPolicy = null,
                warnings = listOf(
                    "Imported single keys are separate wallets.",
                    "Imported keys are not recoverable from the app seed and require separate backup.",
                ),
            ),
            DescriptorWalletProfile(
                id = DescriptorWalletId("demo-nsec-spend"),
                label = DescriptorWalletLabel("Nostr nsec imported spend wallet"),
                status = DescriptorWalletStatus.Planned,
                origin = DescriptorWalletOrigin.NostrNsecImportedSpend,
                scriptPolicy = DescriptorScriptPolicy.Bip86P2tr,
                descriptorDisplay = descriptorNotCreated(),
                backupStatus = DescriptorBackupStatus.IdentityKeyRiskWarningRequired,
                exportState = DescriptorExportState.Disabled,
                canSpend = false,
                importedKeyPolicy = ImportedKeyPolicy.SeparateBackupRequired,
                watchOnlyPolicy = null,
                warnings = listOf(
                    "Nostr identity-key reuse can compromise identity and funds.",
                    "Imported Nostr private-key material requires a separate backup and secure-storage design.",
                ),
            ),
            DescriptorWalletProfile(
                id = DescriptorWalletId("demo-external-signer"),
                label = DescriptorWalletLabel("External signer / hardware signer"),
                status = DescriptorWalletStatus.Planned,
                origin = DescriptorWalletOrigin.HardwareSignerPlanned,
                scriptPolicy = DescriptorScriptPolicy.MultisigDescriptorPlanned,
                descriptorDisplay = descriptorNotCreated(),
                backupStatus = DescriptorBackupStatus.DescriptorExportRecommended,
                exportState = DescriptorExportState.Planned,
                canSpend = false,
                importedKeyPolicy = null,
                watchOnlyPolicy = WatchOnlyPolicy.RequiresExternalSigner,
                warnings = listOf("External signer policy backup and PSBT round-trip support are planned only."),
            ),
        )

    private fun descriptorNotCreated(): DescriptorDisplayState =
        DescriptorDisplayState(
            redactedDescriptor = "DESCRIPTOR_NOT_CREATED",
            descriptorSetStatus = DescriptorSetStatus.NotCreated,
            fingerprintDisplay = "PLACEHOLDER_ONLY",
            containsPrivateMaterial = false,
        )

    private fun demoBackendProfiles(): List<BitcoinBackendProfile> =
        listOf(
            BitcoinBackendProfile(
                id = "bitcoin-core-rpc-placeholder",
                type = BitcoinBackendType.BitcoinCoreRpc,
                status = BitcoinBackendStatus.NotConfigured,
                trustModel = BitcoinBackendTrustModel.UserOwnedNode,
                privacyLevel = BitcoinBackendPrivacyLevel.UserOwnedNodePreferred,
                credentialPolicy = BackendCredentialPolicy.RequiresSecureStorageBeforeUse,
                endpointValidationState = BackendEndpointValidationState.Empty,
                credentialReference = null,
                endpointDisplay = "BACKEND_NOT_CONFIGURED",
                capabilities = listOf(
                    BitcoinBackendCapability.SyncWallet,
                    BitcoinBackendCapability.BroadcastTransaction,
                    BitcoinBackendCapability.FeeEstimates,
                    BitcoinBackendCapability.DescriptorWalletRpc,
                ),
                noDefaultEndpoint = true,
                warnings = listOf("No RPC endpoint or credential is configured."),
            ),
            BitcoinBackendProfile(
                id = "electrum-placeholder",
                type = BitcoinBackendType.Electrum,
                status = BitcoinBackendStatus.NotConfigured,
                trustModel = BitcoinBackendTrustModel.TrustedThirdParty,
                privacyLevel = BitcoinBackendPrivacyLevel.TrustedNodeStillSeesQueries,
                credentialPolicy = BackendCredentialPolicy.NoCredentialStored,
                endpointValidationState = BackendEndpointValidationState.Empty,
                credentialReference = null,
                endpointDisplay = "BACKEND_NOT_CONFIGURED",
                capabilities = listOf(
                    BitcoinBackendCapability.SyncWallet,
                    BitcoinBackendCapability.BroadcastTransaction,
                    BitcoinBackendCapability.FeeEstimates,
                ),
                noDefaultEndpoint = true,
                warnings = listOf("A trusted Electrum server still observes wallet queries."),
            ),
            BitcoinBackendProfile(
                id = "esplora-placeholder",
                type = BitcoinBackendType.Esplora,
                status = BitcoinBackendStatus.NotConfigured,
                trustModel = BitcoinBackendTrustModel.PublicBackend,
                privacyLevel = BitcoinBackendPrivacyLevel.PublicBackendLeaksWalletQueries,
                credentialPolicy = BackendCredentialPolicy.PublicEndpointNoCredentialStillLeaks,
                endpointValidationState = BackendEndpointValidationState.Empty,
                credentialReference = null,
                endpointDisplay = "BACKEND_NOT_CONFIGURED",
                capabilities = listOf(
                    BitcoinBackendCapability.SyncWallet,
                    BitcoinBackendCapability.BroadcastTransaction,
                    BitcoinBackendCapability.MempoolView,
                ),
                noDefaultEndpoint = true,
                warnings = listOf("Public Esplora backends can link wallet queries unless mitigated by future transport/privacy design."),
            ),
        )

    private fun strictCoinControlPolicy(): CoinControlPolicy =
        CoinControlPolicy.strictPlaceholder(
            note = "Future sends must show selected UTXOs, labels, change, fee rate, backend, PSBT export, and privacy warnings before signing.",
        )

    private fun demoCoinSelectionDraft(): CoinSelectionDraft {
        val selected = demoUtxos().map { SelectedUtxo(utxo = it, requiresUserReview = true) }
        return CoinSelectionDraft(
            id = "demo-coin-control-draft",
            intent = CoinSelectionIntent.DemoDisabledDraft,
            review = CoinControlReview(
                selectedInputs = selected,
                outputPlan = listOf(
                    RecipientOutputPlan(
                        label = "Demo recipient output",
                        amountSats = null,
                        addressDisplay = "DEMO_ADDRESS_NOT_DERIVED",
                    ),
                ),
                changePlan = ChangeOutputPlan(
                    state = ChangeOutputState.DisabledPlaceholder,
                    addressDisplay = "DEMO_CHANGE_ADDRESS_NOT_DERIVED",
                    amountSats = null,
                    warning = "Change output review is mandatory and not implemented.",
                ),
                feePlaceholder = FeePlaceholder(
                    networkFeeSats = null,
                    feeRateSatPerVbyte = null,
                    reason = "Fee estimation requires backend and transaction construction implementation.",
                ),
                privacyWarnings = listOf(
                    CoinControlWarning(
                        title = "Demo coin-control draft - no real UTXOs",
                        detail = "Selected inputs use non-transaction sentinels and cannot be signed or broadcast.",
                        level = PrivacyRiskLevel.Warning,
                    ),
                    CoinControlWarning(
                        title = "Cross-wallet input selection disabled",
                        detail = "Future cross-wallet spending requires an explicit advanced flow and privacy warnings.",
                        level = PrivacyRiskLevel.Danger,
                    ),
                ),
                approvalState = CoinControlApprovalState.DisabledPlaceholder,
            ),
            isDemoDraft = true,
            isExecutable = false,
        )
    }

    private fun demoUtxos(): List<UtxoView> =
        listOf(
            UtxoView(
                id = UtxoId("demo-utxo-001"),
                outPoint = OutPointRef(
                    txidDisplay = "DEMO_TXID_001",
                    voutDisplay = "DEMO_VOUT_0",
                ),
                amountSats = 82_000,
                status = UtxoStatus.Frozen,
                label = UtxoLabel("Demo UTXO placeholder"),
                clusterId = UtxoClusterId("demo-cluster-placeholder"),
                source = UtxoSource.DemoPlaceholder,
                age = UtxoAge.Unknown,
                privacyState = UtxoPrivacyState.CleanUnknown,
                spendabilityState = SpendabilityState.DisabledPlaceholder,
                walletId = DescriptorWalletId("demo-native-descriptor"),
            ),
        )

    private fun demoPsbtWorkflow(): PsbtWorkflowPlan =
        PsbtWorkflowPlan(
            id = PsbtWorkflowId("demo-psbt-workflow"),
            currentState = PsbtWorkflowState.PsbtConstructionDisabled,
            review = PsbtReview(
                draft = PsbtDraft(
                    placeholderPsbt = "PSBT_NOT_CREATED",
                    inputs = listOf(
                        PsbtInputSummary(
                            label = "Demo input placeholder",
                            amountSats = 82_000,
                            sourceWallet = DescriptorWalletLabel("Native descriptor wallet"),
                            requiresCoinControlReview = true,
                        ),
                    ),
                    outputs = listOf(
                        PsbtOutputSummary(
                            label = "Recipient output placeholder",
                            amountSats = null,
                            isChange = false,
                            addressDisplay = "DEMO_ADDRESS_NOT_DERIVED",
                        ),
                        PsbtOutputSummary(
                            label = "Change output placeholder",
                            amountSats = null,
                            isChange = true,
                            addressDisplay = "DEMO_CHANGE_ADDRESS_NOT_DERIVED",
                        ),
                    ),
                    feeSummary = PsbtFeeSummary(
                        networkFeeSats = null,
                        feeRateSatPerVbyte = null,
                        requiresReview = true,
                    ),
                ),
                privacyWarnings = listOf(
                    CoinControlWarning(
                        title = "PSBT construction disabled",
                        detail = "No PSBT string, transaction, signature, or broadcast path exists in this pass.",
                        level = PrivacyRiskLevel.Warning,
                    ),
                ),
                failureModes = listOf(
                    PsbtFailureMode.BackendNotConfigured,
                    PsbtFailureMode.UtxoSetUnavailable,
                    PsbtFailureMode.FeeEstimatorUnavailable,
                    PsbtFailureMode.SigningNotImplemented,
                    PsbtFailureMode.BroadcastNotImplemented,
                    PsbtFailureMode.ExternalSignerNotImplemented,
                ),
            ),
            signingPolicy = PsbtSigningPolicy.SigningDisabledThisPass,
            broadcastPolicy = PsbtBroadcastPolicy.BroadcastDisabledThisPass,
            exportImportPlanned = true,
        )

    private fun demoOnChainRecoveryStatus(): OnChainRecoveryStatus =
        OnChainRecoveryStatus(
            headline = "On-chain recovery incomplete",
            checklistItems = listOf(
                RecoveryChecklistItem(
                    artifactType = BackupArtifactType.AppSeed,
                    requirement = RecoveryRequirement.Required,
                    state = RecoveryChecklistState.NotCreated,
                    detail = "Native on-chain keys may be seed-restorable after real seed handling exists.",
                    blockingIssues = listOf(RecoveryBlockingIssue.SeedNotCreated),
                ),
                RecoveryChecklistItem(
                    artifactType = BackupArtifactType.DescriptorExport,
                    requirement = RecoveryRequirement.StronglyRecommended,
                    state = RecoveryChecklistState.NotExported,
                    detail = "Descriptor export remains required for durable wallet recovery and watch-only workflows.",
                    blockingIssues = listOf(RecoveryBlockingIssue.DescriptorNotExported),
                ),
                RecoveryChecklistItem(
                    artifactType = BackupArtifactType.ImportedKeyBackup,
                    requirement = RecoveryRequirement.SeparateBackupRequired,
                    state = RecoveryChecklistState.SeparateBackupRequired,
                    detail = "Imported single-key and Nostr-derived spend wallets require separate key backup.",
                    blockingIssues = listOf(RecoveryBlockingIssue.ImportedKeyRequiresSeparateBackup),
                ),
                RecoveryChecklistItem(
                    artifactType = BackupArtifactType.WatchOnlyDescriptorExport,
                    requirement = RecoveryRequirement.SpendUnavailable,
                    state = RecoveryChecklistState.WatchOnlyCannotSpend,
                    detail = "Watch-only npub or descriptor wallets cannot spend from Skald Vault.",
                    blockingIssues = listOf(RecoveryBlockingIssue.WatchOnlyWalletCannotSpend),
                ),
                RecoveryChecklistItem(
                    artifactType = BackupArtifactType.NostrIdentityKeyBackup,
                    requirement = RecoveryRequirement.IdentityKeyRiskAcknowledgementRequired,
                    state = RecoveryChecklistState.RiskAcknowledgementRequired,
                    detail = "Nostr nsec-derived spend wallets require identity-key risk acknowledgement and separate backup.",
                    blockingIssues = listOf(RecoveryBlockingIssue.NostrIdentityKeyRiskUnacknowledged),
                ),
                RecoveryChecklistItem(
                    artifactType = BackupArtifactType.WalletMetadataBackup,
                    requirement = RecoveryRequirement.StronglyRecommended,
                    state = RecoveryChecklistState.Planned,
                    detail = "Wallet labels, clusters, and metadata backup are planned and not configured.",
                    blockingIssues = listOf(RecoveryBlockingIssue.MetadataBackupNotConfigured),
                ),
            ),
            transitions = listOf(
                RecoveryTransition.SeedCreatedToDescriptorExportRequired,
                RecoveryTransition.DescriptorExportedToOnChainRecoveryCovered,
                RecoveryTransition.ImportedKeyAddedToSeparateKeyBackupRequired,
                RecoveryTransition.WatchOnlyDescriptorAddedToSpendUnavailable,
                RecoveryTransition.NostrNsecImportedToIdentityKeyRiskAcknowledgementRequired,
            ),
            blockingIssues = listOf(
                RecoveryBlockingIssue.SeedNotCreated,
                RecoveryBlockingIssue.DescriptorNotExported,
                RecoveryBlockingIssue.ImportedKeyRequiresSeparateBackup,
                RecoveryBlockingIssue.WatchOnlyWalletCannotSpend,
                RecoveryBlockingIssue.NostrIdentityKeyRiskUnacknowledged,
                RecoveryBlockingIssue.MetadataBackupNotConfigured,
            ),
        )
}
