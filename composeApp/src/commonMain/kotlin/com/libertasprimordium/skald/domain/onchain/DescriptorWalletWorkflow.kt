package com.libertasprimordium.skald.domain.onchain

import com.libertasprimordium.skald.domain.core.NetworkEnvironment
import com.libertasprimordium.skald.security.SecureStorageCapability

data class EditableDescriptorWalletProfileInput(
    val id: DescriptorWalletProfileId? = null,
    val label: String,
    val intent: DescriptorWalletCreationIntent,
    val network: NetworkEnvironment,
    val acknowledgements: Set<DescriptorWalletRiskAcknowledgement>,
)

data class DescriptorWalletWorkflowReview(
    val profile: DescriptorWalletMetadataProfile?,
    val state: DescriptorWalletWorkflowState,
    val errors: List<DescriptorWalletValidationError>,
    val warnings: List<String>,
    val requiredAcknowledgements: Set<DescriptorWalletRiskAcknowledgement>,
) {
    val canCreateProfileMetadata: Boolean
        get() = profile != null && errors.isEmpty()
}

data class DescriptorWalletSettingsState(
    val profiles: List<DescriptorWalletMetadataProfile>,
    val selectedProfileId: DescriptorWalletProfileId?,
) {
    val selectedProfile: DescriptorWalletMetadataProfile?
        get() = profiles.firstOrNull { it.id == selectedProfileId }

    companion object {
        val Empty: DescriptorWalletSettingsState = DescriptorWalletSettingsState(
            profiles = emptyList(),
            selectedProfileId = null,
        )
    }
}

enum class DescriptorWalletValidationError(val message: String) {
    BlankLabel("Profile label must not be blank."),
    MainnetDisabled("Mainnet is disabled during development."),
    UnsupportedOrigin("Wallet origin is not supported by this metadata workflow."),
    MissingRequiredAcknowledgement("Required risk or backup acknowledgement is missing."),
}

object DescriptorWalletWorkflow {
    fun reduce(
        state: DescriptorWalletWorkflowState,
        event: DescriptorWalletWorkflowEvent,
    ): DescriptorWalletWorkflowState =
        when (event) {
            DescriptorWalletWorkflowEvent.Start -> DescriptorWalletWorkflowState.ChoosingWalletOrigin
            is DescriptorWalletWorkflowEvent.ChooseOrigin -> DescriptorWalletWorkflowState.EnteringNonSecretProfileMetadata
            DescriptorWalletWorkflowEvent.EnterMetadata -> DescriptorWalletWorkflowState.EnteringNonSecretProfileMetadata
            is DescriptorWalletWorkflowEvent.AcknowledgeRisk -> when (state) {
                DescriptorWalletWorkflowState.NeedsRiskAcknowledgement,
                DescriptorWalletWorkflowState.NeedsBackupWarningAcknowledgement,
                -> DescriptorWalletWorkflowState.ReadyToCreateProfileMetadata
                else -> state
            }
            DescriptorWalletWorkflowEvent.ReviewProfileMetadata -> DescriptorWalletWorkflowState.ReadyToCreateProfileMetadata
            DescriptorWalletWorkflowEvent.Cancel -> DescriptorWalletWorkflowState.Cancelled
        }

    fun review(
        input: EditableDescriptorWalletProfileInput,
        secureStorageCapability: SecureStorageCapability,
    ): DescriptorWalletWorkflowReview {
        val errors = mutableListOf<DescriptorWalletValidationError>()
        val normalizedLabel = input.label.trim()
        val requiredAcknowledgements = requiredAcknowledgementsFor(input.intent)

        if (normalizedLabel.isBlank()) {
            errors += DescriptorWalletValidationError.BlankLabel
        }
        if (!input.network.isDevelopmentSelectable || input.network.allowsMainnetOperations) {
            errors += DescriptorWalletValidationError.MainnetDisabled
        }
        val missingAcknowledgements = requiredAcknowledgements - input.acknowledgements
        if (missingAcknowledgements.isNotEmpty()) {
            errors += DescriptorWalletValidationError.MissingRequiredAcknowledgement
        }

        if (errors.isNotEmpty()) {
            return DescriptorWalletWorkflowReview(
                profile = null,
                state = stateForValidationErrors(errors, missingAcknowledgements),
                errors = errors.distinct(),
                warnings = warningsFor(input.intent, secureStorageCapability),
                requiredAcknowledgements = requiredAcknowledgements,
            )
        }

        val blockingIssues = blockingIssuesFor(input.intent, secureStorageCapability)
        val state = stateForBlockingIssues(blockingIssues)
        val profile = DescriptorWalletMetadataProfile(
            id = input.id ?: DescriptorWalletProfileId(generateProfileId(input.intent, normalizedLabel, input.network)),
            label = DescriptorWalletProfileLabel(normalizedLabel),
            origin = input.intent.origin,
            network = input.network,
            profileStatus = profileStatusFor(input.intent),
            workflowState = state,
            spendPolicy = spendPolicyFor(input.intent, secureStorageCapability),
            backupRequirement = backupRequirementFor(input.intent),
            riskAcknowledgements = input.acknowledgements.intersect(requiredAcknowledgements),
            blockingIssues = blockingIssues,
            capabilities = capabilitiesFor(input.intent),
            descriptorTextState = "DESCRIPTOR_TEXT_NOT_STORED",
            keyMaterialState = "KEY_MATERIAL_NOT_CREATED",
            isSelected = false,
            isOperational = false,
        )

        return DescriptorWalletWorkflowReview(
            profile = profile,
            state = state,
            errors = emptyList(),
            warnings = warningsFor(input.intent, secureStorageCapability),
            requiredAcknowledgements = requiredAcknowledgements,
        )
    }

    fun requiredAcknowledgementsFor(
        intent: DescriptorWalletCreationIntent,
    ): Set<DescriptorWalletRiskAcknowledgement> =
        when (intent) {
            DescriptorWalletCreationIntent.NativeDescriptorFromAppSeed -> emptySet()
            DescriptorWalletCreationIntent.ImportedDescriptor -> setOf(
                DescriptorWalletRiskAcknowledgement.DescriptorMaterialNotStoredAcknowledged,
            )
            DescriptorWalletCreationIntent.WatchOnlyDescriptor -> setOf(
                DescriptorWalletRiskAcknowledgement.DescriptorMaterialNotStoredAcknowledged,
                DescriptorWalletRiskAcknowledgement.WatchOnlyCannotSpendAcknowledged,
            )
            DescriptorWalletCreationIntent.ImportedSingleKeyTaproot -> setOf(
                DescriptorWalletRiskAcknowledgement.SeparateImportedKeyBackupAcknowledged,
            )
            DescriptorWalletCreationIntent.NostrNpubWatchOnly -> setOf(
                DescriptorWalletRiskAcknowledgement.WatchOnlyCannotSpendAcknowledged,
            )
            DescriptorWalletCreationIntent.NostrNsecSpend -> setOf(
                DescriptorWalletRiskAcknowledgement.IdentityKeyReuseWarningAcknowledged,
                DescriptorWalletRiskAcknowledgement.SeparateImportedKeyBackupAcknowledged,
            )
            DescriptorWalletCreationIntent.ExternalSigner,
            DescriptorWalletCreationIntent.HardwareSigner,
            -> setOf(
                DescriptorWalletRiskAcknowledgement.ExternalSignerControlAcknowledged,
            )
        }

    private fun stateForValidationErrors(
        errors: List<DescriptorWalletValidationError>,
        missingAcknowledgements: Set<DescriptorWalletRiskAcknowledgement>,
    ): DescriptorWalletWorkflowState =
        when {
            errors.contains(DescriptorWalletValidationError.MainnetDisabled) ->
                DescriptorWalletWorkflowState.BlockedByMainnetDisabled
            missingAcknowledgements.any {
                it == DescriptorWalletRiskAcknowledgement.IdentityKeyReuseWarningAcknowledged ||
                    it == DescriptorWalletRiskAcknowledgement.WatchOnlyCannotSpendAcknowledged
            } -> DescriptorWalletWorkflowState.NeedsRiskAcknowledgement
            missingAcknowledgements.isNotEmpty() ->
                DescriptorWalletWorkflowState.NeedsBackupWarningAcknowledgement
            else -> DescriptorWalletWorkflowState.EnteringNonSecretProfileMetadata
        }

    private fun stateForBlockingIssues(
        issues: Set<DescriptorWalletBlockingIssue>,
    ): DescriptorWalletWorkflowState =
        when {
            issues.contains(DescriptorWalletBlockingIssue.SecretStorageDisabled) ->
                DescriptorWalletWorkflowState.BlockedBySecretStorageDisabled
            issues.contains(DescriptorWalletBlockingIssue.DescriptorValidationNotImplemented) ->
                DescriptorWalletWorkflowState.BlockedByDescriptorValidationNotImplemented
            issues.contains(DescriptorWalletBlockingIssue.KeyMaterialNotImplemented) ->
                DescriptorWalletWorkflowState.BlockedByKeyMaterialNotImplemented
            else -> DescriptorWalletWorkflowState.ProfileMetadataCreated
        }

    private fun blockingIssuesFor(
        intent: DescriptorWalletCreationIntent,
        secureStorageCapability: SecureStorageCapability,
    ): Set<DescriptorWalletBlockingIssue> {
        val common = mutableSetOf(
            DescriptorWalletBlockingIssue.BackendNotConnected,
        )
        when (intent) {
            DescriptorWalletCreationIntent.NativeDescriptorFromAppSeed -> {
                if (!secureStorageCapability.canStoreSecrets) {
                    common += DescriptorWalletBlockingIssue.SecretStorageDisabled
                }
                common += DescriptorWalletBlockingIssue.KeyMaterialNotImplemented
                common += DescriptorWalletBlockingIssue.DescriptorValidationNotImplemented
            }
            DescriptorWalletCreationIntent.ImportedDescriptor -> {
                common += DescriptorWalletBlockingIssue.DescriptorValidationNotImplemented
            }
            DescriptorWalletCreationIntent.WatchOnlyDescriptor -> {
                common += DescriptorWalletBlockingIssue.DescriptorValidationNotImplemented
                common += DescriptorWalletBlockingIssue.WatchOnlyCannotSpend
            }
            DescriptorWalletCreationIntent.ImportedSingleKeyTaproot -> {
                if (!secureStorageCapability.canStoreSecrets) {
                    common += DescriptorWalletBlockingIssue.SecretStorageDisabled
                }
                common += DescriptorWalletBlockingIssue.KeyMaterialNotImplemented
            }
            DescriptorWalletCreationIntent.NostrNpubWatchOnly -> {
                common += DescriptorWalletBlockingIssue.DescriptorValidationNotImplemented
                common += DescriptorWalletBlockingIssue.WatchOnlyCannotSpend
            }
            DescriptorWalletCreationIntent.NostrNsecSpend -> {
                if (!secureStorageCapability.canStoreSecrets) {
                    common += DescriptorWalletBlockingIssue.SecretStorageDisabled
                }
                common += DescriptorWalletBlockingIssue.KeyMaterialNotImplemented
            }
            DescriptorWalletCreationIntent.ExternalSigner,
            DescriptorWalletCreationIntent.HardwareSigner,
            -> {
                common += DescriptorWalletBlockingIssue.ExternalSignerNotImplemented
                common += DescriptorWalletBlockingIssue.DescriptorValidationNotImplemented
            }
        }
        return common
    }

    private fun profileStatusFor(intent: DescriptorWalletCreationIntent): DescriptorWalletProfileStatus =
        when (intent) {
            DescriptorWalletCreationIntent.NativeDescriptorFromAppSeed,
            DescriptorWalletCreationIntent.ImportedSingleKeyTaproot,
            DescriptorWalletCreationIntent.NostrNsecSpend,
            -> DescriptorWalletProfileStatus.MissingKeyMaterial
            DescriptorWalletCreationIntent.ImportedDescriptor,
            DescriptorWalletCreationIntent.WatchOnlyDescriptor,
            DescriptorWalletCreationIntent.NostrNpubWatchOnly,
            DescriptorWalletCreationIntent.ExternalSigner,
            DescriptorWalletCreationIntent.HardwareSigner,
            -> DescriptorWalletProfileStatus.MissingDescriptorMaterial
        }

    private fun spendPolicyFor(
        intent: DescriptorWalletCreationIntent,
        secureStorageCapability: SecureStorageCapability,
    ): DescriptorWalletSpendPolicy =
        when (intent) {
            DescriptorWalletCreationIntent.NativeDescriptorFromAppSeed ->
                if (secureStorageCapability.canStoreSecrets) {
                    DescriptorWalletSpendPolicy.DisabledKeyMaterialMissing
                } else {
                    DescriptorWalletSpendPolicy.DisabledSecretStorage
                }
            DescriptorWalletCreationIntent.ImportedDescriptor ->
                DescriptorWalletSpendPolicy.DisabledDescriptorValidation
            DescriptorWalletCreationIntent.WatchOnlyDescriptor,
            DescriptorWalletCreationIntent.NostrNpubWatchOnly,
            -> DescriptorWalletSpendPolicy.WatchOnlyCannotSpend
            DescriptorWalletCreationIntent.ImportedSingleKeyTaproot,
            DescriptorWalletCreationIntent.NostrNsecSpend,
            -> DescriptorWalletSpendPolicy.DisabledKeyMaterialMissing
            DescriptorWalletCreationIntent.ExternalSigner,
            DescriptorWalletCreationIntent.HardwareSigner,
            -> DescriptorWalletSpendPolicy.RequiresExternalSigner
        }

    private fun backupRequirementFor(
        intent: DescriptorWalletCreationIntent,
    ): DescriptorWalletBackupRequirement =
        when (intent) {
            DescriptorWalletCreationIntent.NativeDescriptorFromAppSeed ->
                DescriptorWalletBackupRequirement.NativeSeedAndDescriptorExportRequired
            DescriptorWalletCreationIntent.ImportedDescriptor ->
                DescriptorWalletBackupRequirement.DescriptorMaterialBackupRequired
            DescriptorWalletCreationIntent.WatchOnlyDescriptor,
            DescriptorWalletCreationIntent.NostrNpubWatchOnly,
            -> DescriptorWalletBackupRequirement.WatchOnlyDescriptorExportRecommended
            DescriptorWalletCreationIntent.ImportedSingleKeyTaproot ->
                DescriptorWalletBackupRequirement.ImportedKeySeparateBackupRequired
            DescriptorWalletCreationIntent.NostrNsecSpend ->
                DescriptorWalletBackupRequirement.NostrIdentityKeyBackupRequired
            DescriptorWalletCreationIntent.ExternalSigner,
            DescriptorWalletCreationIntent.HardwareSigner,
            -> DescriptorWalletBackupRequirement.ExternalSignerControlBackupRequired
        }

    private fun capabilitiesFor(
        intent: DescriptorWalletCreationIntent,
    ): Set<DescriptorWalletCapability> {
        val capabilities = mutableSetOf(
            DescriptorWalletCapability.CanDisplayMetadata,
            DescriptorWalletCapability.CanReceiveDisabled,
            DescriptorWalletCapability.CanSpendDisabled,
            DescriptorWalletCapability.CanExportDescriptorDisabled,
            DescriptorWalletCapability.CanBuildPsbtDisabled,
            DescriptorWalletCapability.RequiresBackendConnection,
        )
        when (intent) {
            DescriptorWalletCreationIntent.NativeDescriptorFromAppSeed,
            DescriptorWalletCreationIntent.ImportedDescriptor,
            DescriptorWalletCreationIntent.WatchOnlyDescriptor,
            DescriptorWalletCreationIntent.NostrNpubWatchOnly,
            -> capabilities += DescriptorWalletCapability.RequiresDescriptorImplementation
            DescriptorWalletCreationIntent.ImportedSingleKeyTaproot,
            DescriptorWalletCreationIntent.NostrNsecSpend,
            -> capabilities += DescriptorWalletCapability.RequiresSecretStorage
            DescriptorWalletCreationIntent.ExternalSigner,
            DescriptorWalletCreationIntent.HardwareSigner,
            -> capabilities += DescriptorWalletCapability.RequiresExternalSigner
        }
        if (
            intent == DescriptorWalletCreationIntent.WatchOnlyDescriptor ||
            intent == DescriptorWalletCreationIntent.NostrNpubWatchOnly
        ) {
            capabilities += DescriptorWalletCapability.WatchOnlyCannotSpend
        }
        return capabilities
    }

    private fun warningsFor(
        intent: DescriptorWalletCreationIntent,
        secureStorageCapability: SecureStorageCapability,
    ): List<String> {
        val common = listOf(
            "This creates profile metadata only. No keys, descriptors, addresses, or wallet funds are created.",
            "Descriptor text input, key import, address derivation, PSBT construction, signing, and broadcast are not implemented.",
        )
        val specific = when (intent) {
            DescriptorWalletCreationIntent.NativeDescriptorFromAppSeed -> listOf(
                "Secret storage is disabled. Native seed-backed wallets cannot be created yet.",
                secureStorageCapability.implementationNote,
            )
            DescriptorWalletCreationIntent.ImportedDescriptor -> listOf(
                "Descriptor validation is not implemented. Imported descriptors cannot be used yet.",
                "Descriptor text is not accepted or stored in this pass.",
            )
            DescriptorWalletCreationIntent.WatchOnlyDescriptor -> listOf(
                "Watch-only profiles cannot spend.",
                "Descriptor text is not accepted or stored in this pass.",
            )
            DescriptorWalletCreationIntent.ImportedSingleKeyTaproot -> listOf(
                "Imported single keys require separate backup and secure storage before future use.",
                "No private key or descriptor material is accepted or stored.",
            )
            DescriptorWalletCreationIntent.NostrNpubWatchOnly -> listOf(
                "Watch-only Nostr npub profiles cannot spend.",
                "Nostr npub parsing is not implemented and no npub is accepted or stored.",
            )
            DescriptorWalletCreationIntent.NostrNsecSpend -> listOf(
                "Nostr nsec spend wallets reuse identity key material. This is dangerous and requires explicit acknowledgement before future implementation.",
                "No Nostr nsec parsing, signing, or secret storage is implemented.",
            )
            DescriptorWalletCreationIntent.ExternalSigner,
            DescriptorWalletCreationIntent.HardwareSigner,
            -> listOf(
                "External signer control, backup, and PSBT round-trip behavior are planned only.",
                "No signer fingerprint, descriptor, or key material is accepted or stored.",
            )
        }
        return common + specific
    }

    private fun generateProfileId(
        intent: DescriptorWalletCreationIntent,
        label: String,
        network: NetworkEnvironment,
    ): String {
        val basis = "descriptor-profile-${intent.name}-${network.name}-$label"
        return basis
            .lowercase()
            .map { character ->
                when {
                    character in 'a'..'z' || character in '0'..'9' -> character
                    else -> '-'
                }
            }
            .joinToString("")
            .trim('-')
            .replace(Regex("-+"), "-")
            .ifBlank { "descriptor-profile-metadata-placeholder" }
    }
}
