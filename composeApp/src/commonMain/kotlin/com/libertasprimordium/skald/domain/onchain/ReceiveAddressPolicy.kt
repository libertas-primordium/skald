package com.libertasprimordium.skald.domain.onchain

import com.libertasprimordium.skald.domain.core.NetworkEnvironment

@JvmInline
value class ReceiveAddressId(val value: String)

@JvmInline
value class ReceiveAddressDerivationIndex(val value: Int) {
    init {
        require(value >= 0) { "Receive address derivation index must be non-negative." }
    }
}

enum class ReceiveAddressKeychainKind(val label: String) {
    ExternalReceive("external receive"),
    InternalChange("internal change"),
}

enum class ReceiveAddressScriptType(val label: String) {
    Bip86P2tr("BIP86 P2TR"),
    UnknownNotDerived("unknown - address not derived"),
}

class ReceiveAddressDisplayValue private constructor(
    val value: String,
    val isPlaceholder: Boolean,
    val isRuntimeGeneratedTestValue: Boolean,
) {
    val isRealProductionAddress: Boolean
        get() = !isPlaceholder && !isRuntimeGeneratedTestValue

    override fun toString(): String =
        when {
            isPlaceholder -> value
            isRuntimeGeneratedTestValue -> "RUNTIME_TEST_ADDRESS_REDACTED"
            else -> "RECEIVE_ADDRESS_REDACTED"
        }

    companion object {
        val NotDerived: ReceiveAddressDisplayValue = ReceiveAddressDisplayValue(
            value = "ADDRESS_NOT_DERIVED",
            isPlaceholder = true,
            isRuntimeGeneratedTestValue = false,
        )

        val RuntimeTestRedacted: ReceiveAddressDisplayValue = ReceiveAddressDisplayValue(
            value = "RUNTIME_TEST_ADDRESS_REDACTED",
            isPlaceholder = true,
            isRuntimeGeneratedTestValue = true,
        )
    }
}

enum class ReceiveAddressLifecycleState(
    val label: String,
    val displayedToUser: Boolean,
    val observedByBackend: Boolean,
    val usedForReceive: Boolean,
) {
    NotDerived("not derived", displayedToUser = false, observedByBackend = false, usedForReceive = false),
    Reserved("reserved", displayedToUser = false, observedByBackend = false, usedForReceive = false),
    Displayed("displayed", displayedToUser = true, observedByBackend = false, usedForReceive = false),
    ObservedUnconfirmed(
        "observed unconfirmed receive",
        displayedToUser = true,
        observedByBackend = true,
        usedForReceive = true,
    ),
    ObservedConfirmed(
        "observed confirmed receive",
        displayedToUser = true,
        observedByBackend = true,
        usedForReceive = true,
    ),
    Retired("retired", displayedToUser = true, observedByBackend = true, usedForReceive = true),
}

enum class ReceiveAddressObservation(val label: String) {
    UnconfirmedReceive("unconfirmed receive observed"),
    ConfirmedReceive("confirmed receive observed"),
}

enum class ReceiveAddressProvenance(val label: String) {
    ProductionDerivationDisabled("production derivation disabled"),
    PlaceholderMetadata("placeholder metadata"),
    RuntimeTestValidation("runtime test validation"),
}

enum class ReceiveAddressSource(
    val label: String,
    val requiresIdentityLinkageWarning: Boolean,
    val requiresSeparateBackupWarning: Boolean,
) {
    NativeDescriptor("native descriptor", requiresIdentityLinkageWarning = false, requiresSeparateBackupWarning = false),
    ImportedDescriptor("imported descriptor", requiresIdentityLinkageWarning = false, requiresSeparateBackupWarning = false),
    WatchOnlyDescriptor("watch-only descriptor", requiresIdentityLinkageWarning = false, requiresSeparateBackupWarning = false),
    ImportedSingleKey("imported single key", requiresIdentityLinkageWarning = false, requiresSeparateBackupWarning = true),
    NostrNpubWatchOnly("Nostr npub watch-only", requiresIdentityLinkageWarning = true, requiresSeparateBackupWarning = false),
    NostrNsecImportedSpend("Nostr nsec imported spend", requiresIdentityLinkageWarning = true, requiresSeparateBackupWarning = true),
    ExternalSigner("external signer", requiresIdentityLinkageWarning = false, requiresSeparateBackupWarning = false),
    HardwareSigner("hardware signer", requiresIdentityLinkageWarning = false, requiresSeparateBackupWarning = false),
}

enum class ReceiveAddressWalletOperationalState(val label: String) {
    NonOperationalMetadata("non-operational metadata"),
    OperationalDevelopmentWallet("operational development wallet"),
}

data class ReceiveAddressWalletContext(
    val profileId: DescriptorWalletProfileId,
    val profileLabel: String,
    val network: NetworkEnvironment,
    val source: ReceiveAddressSource,
    val operationalState: ReceiveAddressWalletOperationalState,
    val canDeriveReceiveAddresses: Boolean,
) {
    val isOperational: Boolean
        get() = operationalState == ReceiveAddressWalletOperationalState.OperationalDevelopmentWallet &&
            canDeriveReceiveAddresses

    companion object {
        fun fromProfile(profile: DescriptorWalletMetadataProfile): ReceiveAddressWalletContext =
            ReceiveAddressWalletContext(
                profileId = profile.id,
                profileLabel = profile.label.value,
                network = profile.network,
                source = profile.origin.toReceiveAddressSource(),
                operationalState = if (profile.isOperational && profile.canReceiveNow) {
                    ReceiveAddressWalletOperationalState.OperationalDevelopmentWallet
                } else {
                    ReceiveAddressWalletOperationalState.NonOperationalMetadata
                },
                canDeriveReceiveAddresses = profile.isOperational && profile.canReceiveNow,
            )
    }
}

data class ReceiveAddressState(
    val id: ReceiveAddressId,
    val walletProfileId: DescriptorWalletProfileId,
    val walletProfileLabel: String,
    val network: NetworkEnvironment,
    val keychain: ReceiveAddressKeychainKind,
    val derivationIndex: ReceiveAddressDerivationIndex,
    val displayValue: ReceiveAddressDisplayValue,
    val scriptType: ReceiveAddressScriptType,
    val lifecycleState: ReceiveAddressLifecycleState,
    val provenance: ReceiveAddressProvenance,
    val source: ReceiveAddressSource,
) {
    val isUsed: Boolean
        get() = lifecycleState.usedForReceive

    val mayBeShownAgain: Boolean
        get() = lifecycleState == ReceiveAddressLifecycleState.Reserved ||
            lifecycleState == ReceiveAddressLifecycleState.Displayed

    val safeForNormalReceiveUse: Boolean
        get() = mayBeShownAgain && !isUsed

    fun markDisplayed(): ReceiveAddressState =
        if (lifecycleState == ReceiveAddressLifecycleState.Reserved) {
            copy(lifecycleState = ReceiveAddressLifecycleState.Displayed)
        } else {
            this
        }

    fun markObserved(observation: ReceiveAddressObservation): ReceiveAddressState =
        copy(
            lifecycleState = when (observation) {
                ReceiveAddressObservation.UnconfirmedReceive -> ReceiveAddressLifecycleState.ObservedUnconfirmed
                ReceiveAddressObservation.ConfirmedReceive -> ReceiveAddressLifecycleState.ObservedConfirmed
            },
        )

    companion object {
        fun placeholderReserved(
            wallet: ReceiveAddressWalletContext,
            derivationIndex: ReceiveAddressDerivationIndex = ReceiveAddressDerivationIndex(0),
        ): ReceiveAddressState =
            ReceiveAddressState(
                id = ReceiveAddressId("${wallet.profileId.value}-receive-${derivationIndex.value}"),
                walletProfileId = wallet.profileId,
                walletProfileLabel = wallet.profileLabel,
                network = wallet.network,
                keychain = ReceiveAddressKeychainKind.ExternalReceive,
                derivationIndex = derivationIndex,
                displayValue = ReceiveAddressDisplayValue.NotDerived,
                scriptType = ReceiveAddressScriptType.UnknownNotDerived,
                lifecycleState = ReceiveAddressLifecycleState.Reserved,
                provenance = ReceiveAddressProvenance.ProductionDerivationDisabled,
                source = wallet.source,
            )
    }
}

enum class ReceiveAddressPolicyAction(val label: String) {
    RequestFreshReceiveAddress("request fresh receive address"),
    ReshowReservedOrDisplayedAddress("re-show reserved or displayed address"),
    AttemptAddressReuse("attempt address reuse"),
}

enum class ReceiveAddressPolicyDecisionState(
    val label: String,
    val allowsAction: Boolean,
    val requiresExplicitConfirmation: Boolean,
) {
    Allowed("allowed", allowsAction = true, requiresExplicitConfirmation = false),
    WarningRequired("warning required", allowsAction = false, requiresExplicitConfirmation = true),
    Blocked("blocked", allowsAction = false, requiresExplicitConfirmation = false),
}

enum class ReceiveAddressPolicyWarning(val label: String) {
    BackendObservationRequiredBeforeMarkingUsed(
        "backend observation is required before an address is considered used",
    ),
    ReservedAddressMayBeShownAgainIfUnused("reserved/displayed address may be re-shown only while unused"),
    AddressReuseRequiresExplicitConfirmation("address reuse requires explicit confirmation"),
    ObservedAddressReuseIsUnsafe("observed receive address reuse is unsafe"),
    NostrIdentityLinkageWarningRequired("Nostr identity-linked address warning required"),
    ImportedKeyBackupWarningRequired("imported-key backup warning required"),
}

enum class ReceiveAddressPolicyBlockingIssue(val label: String) {
    MissingWalletProfile("missing wallet profile"),
    MissingAddressCandidate("missing receive address candidate"),
    MainnetDisabled("mainnet disabled"),
    WalletProfileNonOperational("wallet profile is non-operational"),
    ReceiveDerivationDisabled("receive address derivation is disabled"),
    AddressAlreadyUsed("address has observed receive activity"),
    AddressNetworkMismatch("address network does not match wallet network"),
}

data class ReceiveAddressPolicyRequest(
    val action: ReceiveAddressPolicyAction,
    val wallet: ReceiveAddressWalletContext?,
    val candidate: ReceiveAddressState? = null,
    val explicitReuseAcknowledged: Boolean = false,
)

data class ReceiveAddressPolicyDecision(
    val state: ReceiveAddressPolicyDecisionState,
    val warnings: Set<ReceiveAddressPolicyWarning>,
    val blockingIssues: Set<ReceiveAddressPolicyBlockingIssue>,
) {
    val allowsAction: Boolean
        get() = state.allowsAction

    val requiresExplicitConfirmation: Boolean
        get() = state.requiresExplicitConfirmation
}

object ReceiveAddressPolicy {
    fun evaluate(request: ReceiveAddressPolicyRequest): ReceiveAddressPolicyDecision =
        when (request.action) {
            ReceiveAddressPolicyAction.RequestFreshReceiveAddress -> evaluateFreshRequest(request.wallet)
            ReceiveAddressPolicyAction.ReshowReservedOrDisplayedAddress -> evaluateReshow(request.wallet, request.candidate)
            ReceiveAddressPolicyAction.AttemptAddressReuse -> evaluateReuse(request)
        }

    fun isDevelopmentReceiveNetworkAllowed(network: NetworkEnvironment): Boolean =
        network.isDevelopmentSelectable && !network.allowsMainnetOperations

    fun warningsForSource(source: ReceiveAddressSource): Set<ReceiveAddressPolicyWarning> =
        buildSet {
            if (source.requiresIdentityLinkageWarning) {
                add(ReceiveAddressPolicyWarning.NostrIdentityLinkageWarningRequired)
            }
            if (source.requiresSeparateBackupWarning) {
                add(ReceiveAddressPolicyWarning.ImportedKeyBackupWarningRequired)
            }
        }

    private fun evaluateFreshRequest(
        wallet: ReceiveAddressWalletContext?,
    ): ReceiveAddressPolicyDecision {
        val issues = mutableSetOf<ReceiveAddressPolicyBlockingIssue>()
        val warnings = mutableSetOf(
            ReceiveAddressPolicyWarning.BackendObservationRequiredBeforeMarkingUsed,
        )

        if (wallet == null) {
            issues += ReceiveAddressPolicyBlockingIssue.MissingWalletProfile
            return blocked(issues, warnings)
        }
        warnings += warningsForSource(wallet.source)
        issues += commonWalletIssues(wallet)
        if (!wallet.isOperational) {
            issues += ReceiveAddressPolicyBlockingIssue.WalletProfileNonOperational
            issues += ReceiveAddressPolicyBlockingIssue.ReceiveDerivationDisabled
        }

        return if (issues.isEmpty()) {
            ReceiveAddressPolicyDecision(ReceiveAddressPolicyDecisionState.Allowed, warnings, emptySet())
        } else {
            blocked(issues, warnings)
        }
    }

    private fun evaluateReshow(
        wallet: ReceiveAddressWalletContext?,
        candidate: ReceiveAddressState?,
    ): ReceiveAddressPolicyDecision {
        val issues = mutableSetOf<ReceiveAddressPolicyBlockingIssue>()
        val warnings = mutableSetOf(
            ReceiveAddressPolicyWarning.BackendObservationRequiredBeforeMarkingUsed,
            ReceiveAddressPolicyWarning.ReservedAddressMayBeShownAgainIfUnused,
        )

        if (wallet == null) {
            issues += ReceiveAddressPolicyBlockingIssue.MissingWalletProfile
        } else {
            warnings += warningsForSource(wallet.source)
            issues += commonWalletIssues(wallet)
        }
        if (candidate == null) {
            issues += ReceiveAddressPolicyBlockingIssue.MissingAddressCandidate
        } else {
            issues += commonCandidateIssues(wallet, candidate)
            if (candidate.isUsed || !candidate.mayBeShownAgain) {
                issues += ReceiveAddressPolicyBlockingIssue.AddressAlreadyUsed
            }
        }

        return if (issues.isEmpty()) {
            ReceiveAddressPolicyDecision(ReceiveAddressPolicyDecisionState.Allowed, warnings, emptySet())
        } else {
            blocked(issues, warnings)
        }
    }

    private fun evaluateReuse(
        request: ReceiveAddressPolicyRequest,
    ): ReceiveAddressPolicyDecision {
        val wallet = request.wallet
        val candidate = request.candidate
        val issues = mutableSetOf<ReceiveAddressPolicyBlockingIssue>()
        val warnings = mutableSetOf(
            ReceiveAddressPolicyWarning.AddressReuseRequiresExplicitConfirmation,
            ReceiveAddressPolicyWarning.ObservedAddressReuseIsUnsafe,
        )

        if (wallet == null) {
            issues += ReceiveAddressPolicyBlockingIssue.MissingWalletProfile
        } else {
            warnings += warningsForSource(wallet.source)
            issues += commonWalletIssues(wallet)
        }
        if (candidate == null) {
            issues += ReceiveAddressPolicyBlockingIssue.MissingAddressCandidate
        } else {
            issues += commonCandidateIssues(wallet, candidate)
            if (candidate.isUsed) {
                issues += ReceiveAddressPolicyBlockingIssue.AddressAlreadyUsed
            }
        }

        return when {
            issues.any { it != ReceiveAddressPolicyBlockingIssue.AddressAlreadyUsed } -> blocked(issues, warnings)
            !request.explicitReuseAcknowledged ->
                ReceiveAddressPolicyDecision(ReceiveAddressPolicyDecisionState.WarningRequired, warnings, issues)
            else -> ReceiveAddressPolicyDecision(ReceiveAddressPolicyDecisionState.Allowed, warnings, issues)
        }
    }

    private fun commonWalletIssues(wallet: ReceiveAddressWalletContext): Set<ReceiveAddressPolicyBlockingIssue> =
        buildSet {
            if (!isDevelopmentReceiveNetworkAllowed(wallet.network)) {
                add(ReceiveAddressPolicyBlockingIssue.MainnetDisabled)
            }
        }

    private fun commonCandidateIssues(
        wallet: ReceiveAddressWalletContext?,
        candidate: ReceiveAddressState,
    ): Set<ReceiveAddressPolicyBlockingIssue> =
        buildSet {
            if (!isDevelopmentReceiveNetworkAllowed(candidate.network)) {
                add(ReceiveAddressPolicyBlockingIssue.MainnetDisabled)
            }
            if (wallet != null && candidate.network != wallet.network) {
                add(ReceiveAddressPolicyBlockingIssue.AddressNetworkMismatch)
            }
        }

    private fun blocked(
        issues: Set<ReceiveAddressPolicyBlockingIssue>,
        warnings: Set<ReceiveAddressPolicyWarning>,
    ): ReceiveAddressPolicyDecision =
        ReceiveAddressPolicyDecision(
            state = ReceiveAddressPolicyDecisionState.Blocked,
            warnings = warnings,
            blockingIssues = issues,
        )
}

fun DescriptorWalletOrigin.toReceiveAddressSource(): ReceiveAddressSource =
    when (this) {
        DescriptorWalletOrigin.NativeAppSeed,
        DescriptorWalletOrigin.NativeAppSeedPlanned,
        -> ReceiveAddressSource.NativeDescriptor
        DescriptorWalletOrigin.ImportedDescriptor,
        DescriptorWalletOrigin.ImportedDescriptorPlanned,
        -> ReceiveAddressSource.ImportedDescriptor
        DescriptorWalletOrigin.ImportedWatchOnlyDescriptor,
        DescriptorWalletOrigin.WatchOnlyDescriptorPlanned,
        -> ReceiveAddressSource.WatchOnlyDescriptor
        DescriptorWalletOrigin.ImportedSingleKey,
        DescriptorWalletOrigin.ImportedSingleKeyPlanned,
        -> ReceiveAddressSource.ImportedSingleKey
        DescriptorWalletOrigin.NostrNpubWatchOnly,
        DescriptorWalletOrigin.NostrNpubWatchOnlyPlanned,
        -> ReceiveAddressSource.NostrNpubWatchOnly
        DescriptorWalletOrigin.NostrNsecImportedSpend,
        DescriptorWalletOrigin.NostrNsecSpendPlanned,
        -> ReceiveAddressSource.NostrNsecImportedSpend
        DescriptorWalletOrigin.ExternalSigner,
        DescriptorWalletOrigin.ExternalSignerPlanned,
        -> ReceiveAddressSource.ExternalSigner
        DescriptorWalletOrigin.HardwareSignerPlanned -> ReceiveAddressSource.HardwareSigner
    }
