package com.libertasprimordium.skald.domain.onchain

import com.libertasprimordium.skald.domain.core.NetworkEnvironment
import com.libertasprimordium.skald.domain.core.SatsAmount

@JvmInline
value class BackendObservationSessionId(val value: String) {
    init {
        require(value.isNotBlank()) { "Backend observation session id must not be blank." }
    }
}

@JvmInline
value class ObservedUtxoId(val value: String) {
    init {
        require(value.isNotBlank()) { "Observed UTXO id must not be blank." }
    }
}

enum class BackendObservationSource(val label: String) {
    LocalRegtestElectrumHarness("local regtest Electrum harness"),
    UserSelectedBitcoinCoreRpc("user-selected Bitcoin Core RPC"),
    UserSelectedElectrum("user-selected Electrum"),
    UserSelectedEsplora("user-selected Esplora"),
    UserSelectedCompactFilters("user-selected compact filters"),
    Unknown("unknown backend source"),
}

enum class BackendObservationTrust(val label: String) {
    LocalDevelopment("local development harness"),
    UserOwnedNode("user-owned node"),
    UserSelectedPrivateBackend("user-selected private backend"),
    UserSelectedPublicBackend("user-selected public backend"),
    UserSelectedOnionBackend("user-selected onion backend"),
    Unknown("unknown backend trust"),
}

enum class BackendObservationStatus(val label: String) {
    NotStarted("not started"),
    Blocked("blocked"),
    Observing("observing"),
    Completed("completed"),
    Failed("failed"),
    Stale("stale"),
}

enum class BackendObservationCapability(val label: String) {
    AddressUsageObservation("address usage observation"),
    UtxoObservation("UTXO observation"),
    ConfirmationDepth("confirmation depth"),
    BackendTrustMetadata("backend trust metadata"),
    FutureBitcoinCoreRpc("future Bitcoin Core RPC"),
    FutureElectrum("future Electrum"),
    FutureEsplora("future Esplora"),
    FutureCompactFilters("future compact filters"),
    NoProductionSync("no production sync"),
    NoSigning("no signing"),
    NoBroadcasting("no broadcasting"),
    NoMainnet("no mainnet"),
}

enum class BackendObservationWarning(val label: String) {
    LocalRegtestOnly("local regtest observation only"),
    PublicBackendPrivacyLeak("public backend can observe wallet queries"),
    BackendCanLinkWalletQueries("backend can link wallet query metadata"),
    OnionBackendSelected("onion backend selected"),
    UserOwnedNodePreferred("user-owned node preferred"),
    CoinControlRequiredBeforeSpend("coin control required before spending"),
    ObservationDoesNotAuthorizeSpending("backend observation does not authorize spending"),
    StaleObservationNotSafe("stale observation is not safe"),
    ReorgOrConflictRisk("reorg or conflict risk"),
    IdentityLinkedUtxo("identity-linked UTXO warning required"),
    ImportedKeyBackupRisk("imported-key backup warning required"),
    MainnetDisabled("mainnet disabled"),
    ProductionSyncDisabled("production sync disabled"),
}

enum class BackendObservationBlockingIssue(val label: String) {
    MainnetDisabled("mainnet disabled"),
    WalletMissing("wallet context missing"),
    AddressCandidateMissing("receive address candidate missing"),
    NetworkMismatch("observation network mismatch"),
    StaleObservation("stale observation"),
    ConflictingObservation("conflicting observation"),
    NoReceiveObservation("no receive observation"),
}

data class BackendObservationSession(
    val id: BackendObservationSessionId,
    val network: NetworkEnvironment,
    val source: BackendObservationSource,
    val trust: BackendObservationTrust,
    val status: BackendObservationStatus,
    val capabilities: Set<BackendObservationCapability>,
) {
    val isDevelopmentOnly: Boolean
        get() = network.isDevelopmentSelectable && !network.allowsMainnetOperations

    val mainnetEnabled: Boolean
        get() = false

    val usesPublicBackend: Boolean
        get() = trust == BackendObservationTrust.UserSelectedPublicBackend

    val usesSkaldManagedInfrastructure: Boolean
        get() = false

    companion object {
        fun localRegtestElectrumHarness(
            id: BackendObservationSessionId = BackendObservationSessionId("local-regtest-electrum-observation"),
            status: BackendObservationStatus = BackendObservationStatus.Completed,
        ): BackendObservationSession =
            BackendObservationSession(
                id = id,
                network = NetworkEnvironment.Regtest,
                source = BackendObservationSource.LocalRegtestElectrumHarness,
                trust = BackendObservationTrust.LocalDevelopment,
                status = status,
                capabilities = setOf(
                    BackendObservationCapability.AddressUsageObservation,
                    BackendObservationCapability.UtxoObservation,
                    BackendObservationCapability.ConfirmationDepth,
                    BackendObservationCapability.BackendTrustMetadata,
                    BackendObservationCapability.FutureElectrum,
                    BackendObservationCapability.NoProductionSync,
                    BackendObservationCapability.NoSigning,
                    BackendObservationCapability.NoBroadcasting,
                    BackendObservationCapability.NoMainnet,
                ),
            )
    }
}

class ObservedOutpoint private constructor(
    val txidDisplay: String,
    val voutDisplay: String,
    val isPlaceholder: Boolean,
    val isRuntimeGeneratedTestValue: Boolean,
) {
    override fun toString(): String =
        when {
            isPlaceholder -> "$txidDisplay:$voutDisplay"
            isRuntimeGeneratedTestValue -> "RUNTIME_REGTEST_OUTPOINT_REDACTED"
            else -> "OBSERVED_OUTPOINT_REDACTED"
        }

    companion object {
        val NotReal: ObservedOutpoint = ObservedOutpoint(
            txidDisplay = "TXID_NOT_REAL",
            voutDisplay = "VOUT_NOT_REAL",
            isPlaceholder = true,
            isRuntimeGeneratedTestValue = false,
        )

        val RuntimeRegtestRedacted: ObservedOutpoint = ObservedOutpoint(
            txidDisplay = "RUNTIME_REGTEST_TXID_REDACTED",
            voutDisplay = "RUNTIME_REGTEST_VOUT_REDACTED",
            isPlaceholder = false,
            isRuntimeGeneratedTestValue = true,
        )
    }
}

enum class ObservedConfirmationKind(val label: String) {
    Unconfirmed("unconfirmed"),
    Confirmed("confirmed"),
    SpentOrRemoved("spent or removed"),
    ConflictingOrReorgRisk("conflicting or reorg risk"),
    UnknownStale("unknown or stale"),
}

data class ObservedConfirmationState(
    val kind: ObservedConfirmationKind,
    val depth: Int = 0,
) {
    init {
        require(depth >= 0) { "Confirmation depth must not be negative." }
        require(kind == ObservedConfirmationKind.Confirmed || depth == 0) {
            "Only confirmed observations may carry positive confirmation depth."
        }
    }

    val receiveObservation: ReceiveAddressObservation?
        get() = when (kind) {
            ObservedConfirmationKind.Unconfirmed -> ReceiveAddressObservation.UnconfirmedReceive
            ObservedConfirmationKind.Confirmed -> ReceiveAddressObservation.ConfirmedReceive
            ObservedConfirmationKind.SpentOrRemoved,
            ObservedConfirmationKind.ConflictingOrReorgRisk,
            ObservedConfirmationKind.UnknownStale,
            -> null
        }

    companion object {
        fun unconfirmed(): ObservedConfirmationState =
            ObservedConfirmationState(ObservedConfirmationKind.Unconfirmed)

        fun confirmed(depth: Int): ObservedConfirmationState =
            ObservedConfirmationState(ObservedConfirmationKind.Confirmed, depth = depth)
    }
}

enum class ObservedUtxoLifecycle(val label: String, val backendObserved: Boolean) {
    UnobservedPlaceholder("unobserved placeholder", backendObserved = false),
    BackendObservedUnconfirmed("backend-observed unconfirmed", backendObserved = true),
    BackendObservedConfirmed("backend-observed confirmed", backendObserved = true),
    SpentOrRemoved("spent or removed observation", backendObserved = true),
    ConflictingOrReorgRisk("conflicting or reorg-risk observation", backendObserved = true),
    UnknownStale("unknown or stale observation", backendObserved = true),
}

enum class ObservedScriptClass(val label: String) {
    Bip86P2tr("BIP86 P2TR"),
    NativeSegwit("native SegWit"),
    Legacy("legacy script"),
    Unknown("unknown script class"),
}

enum class ObservedWalletScope(val label: String) {
    NativeDescriptor("native descriptor"),
    ImportedDescriptor("imported descriptor"),
    WatchOnlyDescriptor("watch-only descriptor"),
    ImportedSingleKey("imported single key"),
    NostrIdentityLinked("Nostr identity-linked"),
    ExternalSigner("external signer"),
    Unknown("unknown wallet scope"),
}

enum class ObservedUtxoRiskFlag(val label: String) {
    PublicBackendObserved("public backend observed wallet query"),
    BackendCanLinkWalletQueries("backend can link wallet queries"),
    IdentityLinkedSource("identity-linked source"),
    ImportedKeySource("imported-key source"),
    WatchOnlyCannotSpend("watch-only cannot spend"),
    ReorgOrConflictRisk("reorg or conflict risk"),
    StaleObservation("stale observation"),
    CoinControlRequired("coin control required"),
}

enum class ObservedUtxoSpendReadiness(val label: String, val spendableByDefault: Boolean) {
    CoinControlRequired("coin control review required", spendableByDefault = false),
    WatchOnlyCannotSpend("watch-only cannot spend", spendableByDefault = false),
    StaleOrConflictingBlocked("stale or conflicting observation blocked", spendableByDefault = false),
    ProductionSpendDisabled("production spend disabled", spendableByDefault = false),
}

data class ObservedUtxo(
    val id: ObservedUtxoId,
    val walletProfileId: DescriptorWalletProfileId,
    val sourceAddressId: ReceiveAddressId?,
    val network: NetworkEnvironment,
    val amount: SatsAmount,
    val outpoint: ObservedOutpoint,
    val lifecycle: ObservedUtxoLifecycle,
    val confirmationState: ObservedConfirmationState,
    val scriptClass: ObservedScriptClass,
    val walletScope: ObservedWalletScope,
    val riskFlags: Set<ObservedUtxoRiskFlag>,
    val spendReadiness: ObservedUtxoSpendReadiness,
) {
    val isSpendableWithoutCoinControl: Boolean
        get() = spendReadiness.spendableByDefault

    val canMarkReceiveAddressUsed: Boolean
        get() = amount.value > 0 && (
            lifecycle == ObservedUtxoLifecycle.BackendObservedConfirmed ||
                lifecycle == ObservedUtxoLifecycle.BackendObservedUnconfirmed
            )

    companion object {
        fun runtimeRegtestReceive(
            wallet: ReceiveAddressWalletContext,
            address: ReceiveAddressState,
            amountSats: Long,
            confirmations: Int,
        ): ObservedUtxo {
            val lifecycle = if (confirmations > 0) {
                ObservedUtxoLifecycle.BackendObservedConfirmed
            } else {
                ObservedUtxoLifecycle.BackendObservedUnconfirmed
            }
            val confirmationState = if (confirmations > 0) {
                ObservedConfirmationState.confirmed(confirmations)
            } else {
                ObservedConfirmationState.unconfirmed()
            }
            return ObservedUtxo(
                id = ObservedUtxoId("RUNTIME_REGTEST_UTXO_REDACTED"),
                walletProfileId = wallet.profileId,
                sourceAddressId = address.id,
                network = NetworkEnvironment.Regtest,
                amount = SatsAmount(amountSats),
                outpoint = ObservedOutpoint.RuntimeRegtestRedacted,
                lifecycle = lifecycle,
                confirmationState = confirmationState,
                scriptClass = ObservedScriptClass.Bip86P2tr,
                walletScope = wallet.source.toObservedWalletScope(),
                riskFlags = riskFlagsForSource(wallet.source) + ObservedUtxoRiskFlag.CoinControlRequired,
                spendReadiness = ObservedUtxoSpendReadiness.CoinControlRequired,
            )
        }
    }
}

data class ObservedAddressUsage(
    val addressId: ReceiveAddressId,
    val lifecycleBefore: ReceiveAddressLifecycleState,
    val lifecycleAfter: ReceiveAddressLifecycleState,
    val addressMarkedUsed: Boolean,
    val reuseDecision: ReceiveAddressPolicyDecision,
)

data class BackendObservationSummary(
    val session: BackendObservationSession,
    val status: BackendObservationStatus,
    val observedUtxos: List<ObservedUtxo>,
    val addressUsage: ObservedAddressUsage?,
    val warnings: Set<BackendObservationWarning>,
    val blockingIssues: Set<BackendObservationBlockingIssue>,
) {
    val totalAmount: SatsAmount
        get() = SatsAmount(observedUtxos.sumOf { it.amount.value })

    val observedUtxoCount: Int
        get() = observedUtxos.size

    val productionSyncEnabled: Boolean
        get() = false

    val signingOrBroadcastEnabled: Boolean
        get() = false

    val anySpendableWithoutCoinControl: Boolean
        get() = observedUtxos.any { it.isSpendableWithoutCoinControl }

    val completedWithoutBlockingIssues: Boolean
        get() = status == BackendObservationStatus.Completed && blockingIssues.isEmpty()
}

data class BackendObservationPolicyRequest(
    val session: BackendObservationSession,
    val wallet: ReceiveAddressWalletContext?,
    val candidate: ReceiveAddressState?,
    val observedUtxos: List<ObservedUtxo>,
)

object BackendObservationPolicy {
    fun evaluate(request: BackendObservationPolicyRequest): BackendObservationSummary {
        val warnings = mutableSetOf(
            BackendObservationWarning.ObservationDoesNotAuthorizeSpending,
            BackendObservationWarning.ProductionSyncDisabled,
        )
        val issues = mutableSetOf<BackendObservationBlockingIssue>()

        warnings += warningsForSession(request.session)
        if (!ReceiveAddressPolicy.isDevelopmentReceiveNetworkAllowed(request.session.network)) {
            issues += BackendObservationBlockingIssue.MainnetDisabled
            warnings += BackendObservationWarning.MainnetDisabled
        }
        request.wallet?.let { wallet ->
            if (wallet.network != request.session.network) {
                issues += BackendObservationBlockingIssue.NetworkMismatch
            }
            warnings += warningsForWallet(wallet)
        } ?: run {
            issues += BackendObservationBlockingIssue.WalletMissing
        }
        request.candidate?.let { candidate ->
            if (candidate.network != request.session.network) {
                issues += BackendObservationBlockingIssue.NetworkMismatch
            }
        } ?: run {
            issues += BackendObservationBlockingIssue.AddressCandidateMissing
        }

        val observationIssues = request.observedUtxos.flatMap { it.blockingIssues() }.toSet()
        issues += observationIssues
        warnings += request.observedUtxos.flatMap { it.warnings() }

        val positiveReceiveObservation = request.observedUtxos
            .filter { it.network == request.session.network }
            .filter { it.walletProfileId == request.wallet?.profileId }
            .filter { it.sourceAddressId == request.candidate?.id }
            .filter { it.canMarkReceiveAddressUsed }
            .maxByOrNull { it.confirmationState.depth }

        val addressUsage = if (
            request.wallet != null &&
            request.candidate != null &&
            positiveReceiveObservation != null &&
            BackendObservationBlockingIssue.MainnetDisabled !in issues &&
            BackendObservationBlockingIssue.NetworkMismatch !in issues
        ) {
            applyReceiveAddressObservation(
                wallet = request.wallet,
                candidate = request.candidate,
                observation = positiveReceiveObservation,
            )
        } else {
            if (positiveReceiveObservation == null) {
                issues += BackendObservationBlockingIssue.NoReceiveObservation
            }
            null
        }

        val status = when {
            BackendObservationBlockingIssue.MainnetDisabled in issues -> BackendObservationStatus.Blocked
            BackendObservationBlockingIssue.ConflictingObservation in issues -> BackendObservationStatus.Blocked
            BackendObservationBlockingIssue.StaleObservation in issues -> BackendObservationStatus.Stale
            BackendObservationBlockingIssue.NetworkMismatch in issues -> BackendObservationStatus.Blocked
            addressUsage != null || request.observedUtxos.isNotEmpty() -> BackendObservationStatus.Completed
            else -> BackendObservationStatus.NotStarted
        }

        return BackendObservationSummary(
            session = request.session,
            status = status,
            observedUtxos = request.observedUtxos,
            addressUsage = addressUsage,
            warnings = warnings,
            blockingIssues = issues,
        )
    }

    fun warningsForSession(session: BackendObservationSession): Set<BackendObservationWarning> =
        buildSet {
            when (session.source) {
                BackendObservationSource.LocalRegtestElectrumHarness -> add(BackendObservationWarning.LocalRegtestOnly)
                BackendObservationSource.UserSelectedBitcoinCoreRpc,
                BackendObservationSource.UserSelectedElectrum,
                BackendObservationSource.UserSelectedEsplora,
                BackendObservationSource.UserSelectedCompactFilters,
                BackendObservationSource.Unknown,
                -> Unit
            }
            when (session.trust) {
                BackendObservationTrust.LocalDevelopment -> Unit
                BackendObservationTrust.UserOwnedNode -> add(BackendObservationWarning.UserOwnedNodePreferred)
                BackendObservationTrust.UserSelectedPrivateBackend,
                BackendObservationTrust.Unknown,
                -> add(BackendObservationWarning.BackendCanLinkWalletQueries)
                BackendObservationTrust.UserSelectedPublicBackend -> {
                    add(BackendObservationWarning.PublicBackendPrivacyLeak)
                    add(BackendObservationWarning.BackendCanLinkWalletQueries)
                }
                BackendObservationTrust.UserSelectedOnionBackend -> {
                    add(BackendObservationWarning.OnionBackendSelected)
                    add(BackendObservationWarning.BackendCanLinkWalletQueries)
                }
            }
        }

    private fun applyReceiveAddressObservation(
        wallet: ReceiveAddressWalletContext,
        candidate: ReceiveAddressState,
        observation: ObservedUtxo,
    ): ObservedAddressUsage {
        val receiveObservation = requireNotNull(observation.confirmationState.receiveObservation) {
            "Only positive receive observations can update receive-address used state."
        }
        val before = candidate.lifecycleState
        val observed = candidate.markObserved(receiveObservation)
        val reuseDecision = ReceiveAddressPolicy.evaluate(
            ReceiveAddressPolicyRequest(
                action = ReceiveAddressPolicyAction.AttemptAddressReuse,
                wallet = wallet,
                candidate = observed,
            ),
        )

        return ObservedAddressUsage(
            addressId = candidate.id,
            lifecycleBefore = before,
            lifecycleAfter = observed.lifecycleState,
            addressMarkedUsed = observed.isUsed,
            reuseDecision = reuseDecision,
        )
    }

    private fun warningsForWallet(wallet: ReceiveAddressWalletContext): Set<BackendObservationWarning> =
        buildSet {
            if (wallet.source.requiresIdentityLinkageWarning) {
                add(BackendObservationWarning.IdentityLinkedUtxo)
            }
            if (wallet.source.requiresSeparateBackupWarning) {
                add(BackendObservationWarning.ImportedKeyBackupRisk)
            }
        }

    private fun ObservedUtxo.warnings(): Set<BackendObservationWarning> =
        buildSet {
            add(BackendObservationWarning.CoinControlRequiredBeforeSpend)
            if (ObservedUtxoRiskFlag.PublicBackendObserved in riskFlags) {
                add(BackendObservationWarning.PublicBackendPrivacyLeak)
            }
            if (ObservedUtxoRiskFlag.BackendCanLinkWalletQueries in riskFlags) {
                add(BackendObservationWarning.BackendCanLinkWalletQueries)
            }
            if (ObservedUtxoRiskFlag.IdentityLinkedSource in riskFlags) {
                add(BackendObservationWarning.IdentityLinkedUtxo)
            }
            if (ObservedUtxoRiskFlag.ImportedKeySource in riskFlags) {
                add(BackendObservationWarning.ImportedKeyBackupRisk)
            }
            if (ObservedUtxoRiskFlag.StaleObservation in riskFlags) {
                add(BackendObservationWarning.StaleObservationNotSafe)
            }
            if (ObservedUtxoRiskFlag.ReorgOrConflictRisk in riskFlags) {
                add(BackendObservationWarning.ReorgOrConflictRisk)
            }
        }

    private fun ObservedUtxo.blockingIssues(): Set<BackendObservationBlockingIssue> =
        buildSet {
            if (!ReceiveAddressPolicy.isDevelopmentReceiveNetworkAllowed(network)) {
                add(BackendObservationBlockingIssue.MainnetDisabled)
            }
            when (lifecycle) {
                ObservedUtxoLifecycle.ConflictingOrReorgRisk -> add(
                    BackendObservationBlockingIssue.ConflictingObservation,
                )
                ObservedUtxoLifecycle.UnknownStale -> add(BackendObservationBlockingIssue.StaleObservation)
                ObservedUtxoLifecycle.UnobservedPlaceholder,
                ObservedUtxoLifecycle.BackendObservedUnconfirmed,
                ObservedUtxoLifecycle.BackendObservedConfirmed,
                ObservedUtxoLifecycle.SpentOrRemoved,
                -> Unit
            }
        }
}

fun ReceiveAddressSource.toObservedWalletScope(): ObservedWalletScope =
    when (this) {
        ReceiveAddressSource.NativeDescriptor -> ObservedWalletScope.NativeDescriptor
        ReceiveAddressSource.ImportedDescriptor -> ObservedWalletScope.ImportedDescriptor
        ReceiveAddressSource.WatchOnlyDescriptor -> ObservedWalletScope.WatchOnlyDescriptor
        ReceiveAddressSource.ImportedSingleKey -> ObservedWalletScope.ImportedSingleKey
        ReceiveAddressSource.NostrNpubWatchOnly,
        ReceiveAddressSource.NostrNsecImportedSpend,
        -> ObservedWalletScope.NostrIdentityLinked
        ReceiveAddressSource.ExternalSigner,
        ReceiveAddressSource.HardwareSigner,
        -> ObservedWalletScope.ExternalSigner
    }

fun riskFlagsForSource(source: ReceiveAddressSource): Set<ObservedUtxoRiskFlag> =
    buildSet {
        if (source.requiresIdentityLinkageWarning) {
            add(ObservedUtxoRiskFlag.IdentityLinkedSource)
        }
        if (source.requiresSeparateBackupWarning) {
            add(ObservedUtxoRiskFlag.ImportedKeySource)
        }
        if (
            source == ReceiveAddressSource.WatchOnlyDescriptor ||
            source == ReceiveAddressSource.NostrNpubWatchOnly
        ) {
            add(ObservedUtxoRiskFlag.WatchOnlyCannotSpend)
        }
    }
