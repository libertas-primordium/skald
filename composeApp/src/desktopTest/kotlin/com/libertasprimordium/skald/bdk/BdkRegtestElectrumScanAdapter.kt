package com.libertasprimordium.skald.bdk

import com.libertasprimordium.skald.domain.core.NetworkEnvironment
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletProfileId
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressDerivationIndex
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressLifecycleState
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressPolicyDecisionState
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressSource
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressState
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressWalletContext
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressWalletOperationalState
import com.libertasprimordium.skald.regtest.RegtestBinaryDiscovery
import com.libertasprimordium.skald.regtest.RegtestElectrumIndexerAvailability
import com.libertasprimordium.skald.regtest.RegtestElectrumIndexerBinaryDiscovery
import com.libertasprimordium.skald.regtest.RegtestElectrumIndexerCheck
import com.libertasprimordium.skald.regtest.RegtestElectrumIndexerHarness
import com.libertasprimordium.skald.regtest.RegtestElectrumIndexerIntegrationPolicy
import com.libertasprimordium.skald.regtest.RegtestElectrumIndexerSession
import com.libertasprimordium.skald.regtest.RegtestElectrumIndexerState
import com.libertasprimordium.skald.regtest.RegtestHarnessAvailability
import org.bitcoindevkit.AddressInfo
import org.bitcoindevkit.ChainPosition
import org.bitcoindevkit.Descriptor
import org.bitcoindevkit.DescriptorSecretKey
import org.bitcoindevkit.ElectrumClient
import org.bitcoindevkit.KeychainKind
import org.bitcoindevkit.LocalOutput
import org.bitcoindevkit.Mnemonic
import org.bitcoindevkit.Network
import org.bitcoindevkit.Persister
import org.bitcoindevkit.Update
import org.bitcoindevkit.Wallet
import java.security.SecureRandom

enum class BdkRegtestElectrumScanState(val label: String) {
    Disabled("disabled"),
    Unavailable("unavailable"),
    Completed("completed"),
    RejectedMainnet("rejected mainnet"),
    Failed("failed"),
}

enum class BdkRegtestElectrumScanCheck(val label: String) {
    ExplicitUtxoScanOptIn("explicit UTXO scan opt-in"),
    LocalElectrumOptIn("local Electrum regtest opt-in"),
    RegtestNetworkSelected("regtest network selected"),
    LocalRegtestBinariesDiscovered("local regtest binaries discovered"),
    LocalElectrumHarnessReady("local Electrum regtest harness ready"),
    RuntimeEntropyCreated("runtime-only entropy created"),
    RuntimeMnemonicCreated("runtime-only mnemonic created"),
    RuntimeDescriptorSecretCreated("runtime-only descriptor secret created"),
    Bip86DescriptorsCreated("test-only BIP86 descriptors created"),
    InMemoryPersisterCreated("BDK in-memory persister created"),
    BdkWalletCreated("test-only BDK wallet created"),
    ReceiveAddressRevealed("runtime receive address revealed"),
    ReceiveAddressModeledAsDisplayed("receive-address state modeled as displayed"),
    LocalRegtestAddressFunded("runtime regtest address funded by harness"),
    ElectrumClientCreated("BDK Electrum client created for local harness endpoint"),
    ElectrumPingCompleted("local Electrum ping completed"),
    FullScanRequestBuilt("BDK full-scan request built"),
    ElectrumFullScanCompleted("BDK Electrum full scan completed"),
    WalletUpdateApplied("BDK wallet update applied"),
    UtxosObserved("BDK wallet observed unspent outputs"),
    ReceivePolicyTransitionApplied("receive-address policy transition applied"),
    NoProductionStorageUsed("no production storage used"),
}

enum class BdkRegtestElectrumScanCapability(val label: String) {
    DesktopTestOnly("desktop test only"),
    LocalRegtestOnly("local regtest only"),
    LocalElectrumOnly("local Electrum endpoint only"),
    BdkElectrumFullScanApi("BDK Electrum full-scan API"),
    InMemoryBdkPersister("BDK in-memory persister"),
    SkaldOwnedResultTypes("Skald-owned result types"),
    RedactedDiagnostics("redacted diagnostics"),
    ReceiveAddressPolicyIntegration("receive-address policy integration"),
    NoPublicEndpoint("no public endpoint"),
    NoProductionStorage("no production storage"),
    NoProductionNetworking("no production networking"),
    NoSigning("no signing"),
    NoBroadcasting("no broadcasting"),
    NoMainnet("no mainnet"),
}

data class BdkRegtestElectrumScanError(
    val code: String,
    val safeDetail: String,
)

data class BdkRegtestElectrumScanRequest(
    val enabled: Boolean,
    val localElectrumEnabled: Boolean,
    val network: BdkRegtestUtxoScanValidationNetwork,
)

data class BdkRegtestElectrumScanResult(
    val state: BdkRegtestElectrumScanState,
    val network: BdkRegtestUtxoScanValidationNetwork?,
    val checks: List<BdkRegtestElectrumScanCheck>,
    val capabilities: Set<BdkRegtestElectrumScanCapability>,
    val harnessState: RegtestElectrumIndexerState?,
    val harnessChecks: List<RegtestElectrumIndexerCheck>,
    val scanSummary: SanitizedRegtestScanSummary?,
    val observedUtxoCount: Int,
    val observedAmountSats: Long,
    val error: BdkRegtestElectrumScanError?,
    val diagnostic: String,
) {
    val completed: Boolean
        get() = state == BdkRegtestElectrumScanState.Completed

    val usesPublicEndpoint: Boolean
        get() = state != BdkRegtestElectrumScanState.Disabled &&
            BdkRegtestElectrumScanCapability.NoPublicEndpoint !in capabilities

    val usesProductionStorage: Boolean
        get() = state != BdkRegtestElectrumScanState.Disabled &&
            BdkRegtestElectrumScanCapability.NoProductionStorage !in capabilities

    val mainnetScanEnabled: Boolean
        get() = false
}

object BdkRegtestElectrumScanAdapter {
    private const val FundingAmountBtc = "0.00025000"

    fun runFromEnvironment(
        environment: Map<String, String> = System.getenv(),
    ): BdkRegtestElectrumScanResult =
        run(
            request = BdkRegtestElectrumScanRequest(
                enabled = BdkRegtestUtxoScanValidationPolicy.fromEnvironment(environment).enabled,
                localElectrumEnabled = RegtestElectrumIndexerIntegrationPolicy.fromEnvironment(environment).enabled,
                network = BdkRegtestUtxoScanValidationNetwork.Regtest,
            ),
            environment = environment,
        )

    fun run(
        request: BdkRegtestElectrumScanRequest,
        environment: Map<String, String> = System.getenv(),
    ): BdkRegtestElectrumScanResult {
        if (!request.enabled) {
            return disabled(
                code = "BDK_REGTEST_ELECTRUM_SCAN_DISABLED",
                safeDetail = "Set ${BdkRegtestUtxoScanValidationPolicy.EnvironmentVariable}=1 to run the test-only BDK Electrum scan adapter.",
            )
        }
        if (!request.localElectrumEnabled) {
            return disabled(
                code = "BDK_REGTEST_ELECTRUM_SCAN_LOCAL_INDEXER_DISABLED",
                safeDetail = "Set ${RegtestElectrumIndexerIntegrationPolicy.EnvironmentVariable}=1 with ${BdkRegtestUtxoScanValidationPolicy.EnvironmentVariable}=1 to run the local Electrum-backed regtest scan adapter.",
            )
        }
        if (request.network != BdkRegtestUtxoScanValidationNetwork.Regtest) {
            return BdkRegtestElectrumScanResult(
                state = BdkRegtestElectrumScanState.RejectedMainnet,
                network = request.network,
                checks = listOf(BdkRegtestElectrumScanCheck.ExplicitUtxoScanOptIn),
                capabilities = defaultCapabilities(),
                harnessState = null,
                harnessChecks = emptyList(),
                scanSummary = null,
                observedUtxoCount = 0,
                observedAmountSats = 0,
                error = BdkRegtestElectrumScanError(
                    code = "BDK_REGTEST_ELECTRUM_SCAN_MAINNET_REJECTED",
                    safeDetail = "Mainnet is disabled. Test-only BDK Electrum scan validation is allowed only for local regtest.",
                ),
                diagnostic = "BDK Electrum scan adapter refused mainnet before creating wallet material or a backend client.",
            )
        }

        val checks = mutableListOf(
            BdkRegtestElectrumScanCheck.ExplicitUtxoScanOptIn,
            BdkRegtestElectrumScanCheck.LocalElectrumOptIn,
            BdkRegtestElectrumScanCheck.RegtestNetworkSelected,
        )
        val bitcoinAvailability = RegtestBinaryDiscovery.discover(environment)
        val indexerAvailability = RegtestElectrumIndexerBinaryDiscovery.discover(environment)
        val missing = buildSet {
            if (bitcoinAvailability is RegtestHarnessAvailability.Unavailable) {
                addAll(bitcoinAvailability.missingBinaries)
            }
            if (indexerAvailability is RegtestElectrumIndexerAvailability.Unavailable) {
                addAll(indexerAvailability.missingBinaries)
            }
        }
        if (missing.isNotEmpty()) {
            return BdkRegtestElectrumScanResult(
                state = BdkRegtestElectrumScanState.Unavailable,
                network = BdkRegtestUtxoScanValidationNetwork.Regtest,
                checks = checks.distinct(),
                capabilities = defaultCapabilities(),
                harnessState = RegtestElectrumIndexerState.Unavailable,
                harnessChecks = emptyList(),
                scanSummary = null,
                observedUtxoCount = 0,
                observedAmountSats = 0,
                error = BdkRegtestElectrumScanError(
                    code = "BDK_REGTEST_ELECTRUM_SCAN_BINARIES_UNAVAILABLE",
                    safeDetail = "Missing local regtest binaries: ${missing.joinToString()}. Set ${RegtestBinaryDiscovery.BitcoindEnvironmentVariable}, ${RegtestBinaryDiscovery.BitcoinCliEnvironmentVariable}, and ${RegtestElectrumIndexerBinaryDiscovery.ElectrsEnvironmentVariable} or add them to PATH.",
                ),
                diagnostic = "BDK Electrum scan adapter did not use public endpoints or create wallet material because local regtest binaries were unavailable.",
            )
        }

        checks += BdkRegtestElectrumScanCheck.LocalRegtestBinariesDiscovered
        val bitcoinPaths = (bitcoinAvailability as RegtestHarnessAvailability.Available).paths
        val indexerPaths = (indexerAvailability as RegtestElectrumIndexerAvailability.Available).paths
        var adapterResult: BdkRegtestElectrumScanResult? = null
        val harnessResult = RegtestElectrumIndexerHarness(
            bitcoinPaths = bitcoinPaths,
            indexerPaths = indexerPaths,
        ).runWithReadyIndexer { session, harnessChecks ->
            checks += BdkRegtestElectrumScanCheck.LocalElectrumHarnessReady
            adapterResult = runCatching {
                runScan(session, harnessChecks, checks)
            }.getOrElse { failure ->
                failed(
                    checks = checks,
                    harnessState = RegtestElectrumIndexerState.Completed,
                    harnessChecks = harnessChecks,
                    code = "BDK_REGTEST_ELECTRUM_SCAN_FAILED",
                    safeDetail = "BDK Electrum scan adapter failed after ${safeLastCheck(checks)} during ${safeFailureStage(failure)}. Raw library, endpoint, and wallet details were not exposed.",
                )
            }
            adapterResult?.completed == true
        }

        return when {
            adapterResult != null -> adapterResult.copy(
                harnessState = harnessResult.state,
                harnessChecks = harnessResult.checks,
                checks = adapterResult.checks.distinct(),
            )
            harnessResult.state != RegtestElectrumIndexerState.Completed -> BdkRegtestElectrumScanResult(
                state = BdkRegtestElectrumScanState.Failed,
                network = BdkRegtestUtxoScanValidationNetwork.Regtest,
                checks = checks.distinct(),
                capabilities = defaultCapabilities(),
                harnessState = harnessResult.state,
                harnessChecks = harnessResult.checks,
                scanSummary = null,
                observedUtxoCount = 0,
                observedAmountSats = 0,
                error = BdkRegtestElectrumScanError(
                    code = "BDK_REGTEST_ELECTRUM_SCAN_HARNESS_FAILED",
                    safeDetail = "Local Electrum regtest harness did not complete before the BDK scan adapter could observe UTXOs.",
                ),
                diagnostic = "BDK Electrum scan adapter stayed inside local regtest harness boundaries and produced a redacted failure.",
            )
            else -> adapterResult ?: failed(
                checks = checks,
                harnessState = harnessResult.state,
                code = "BDK_REGTEST_ELECTRUM_SCAN_NO_RESULT",
                safeDetail = "BDK Electrum scan adapter ended without a sanitized scan result.",
            )
        }
    }

    private fun runScan(
        session: RegtestElectrumIndexerSession,
        harnessChecks: MutableList<RegtestElectrumIndexerCheck>,
        checks: MutableList<BdkRegtestElectrumScanCheck>,
    ): BdkRegtestElectrumScanResult {
        val closeBag = BdkElectrumScanCloseBag()
        val entropy = ByteArray(16).also {
            SecureRandom().nextBytes(it)
            checks += BdkRegtestElectrumScanCheck.RuntimeEntropyCreated
        }
        var update: Update? = null
        val outputs = mutableListOf<LocalOutput>()
        var addressInfo: AddressInfo? = null
        return try {
            val mnemonicEntropy = entropy.copyOf()
            val mnemonic = closeBag.track(
                try {
                    Mnemonic.fromEntropy(mnemonicEntropy)
                } finally {
                    mnemonicEntropy.fill(0)
                },
            ).also {
                checks += BdkRegtestElectrumScanCheck.RuntimeMnemonicCreated
            }
            val rootSecret = closeBag.track(DescriptorSecretKey(Network.REGTEST, mnemonic, "")).also {
                checks += BdkRegtestElectrumScanCheck.RuntimeDescriptorSecretCreated
            }
            val externalDescriptor = closeBag.track(
                Descriptor.newBip86(rootSecret, KeychainKind.EXTERNAL, Network.REGTEST),
            )
            val internalDescriptor = closeBag.track(
                Descriptor.newBip86(rootSecret, KeychainKind.INTERNAL, Network.REGTEST),
            )
            checks += BdkRegtestElectrumScanCheck.Bip86DescriptorsCreated
            val persister = closeBag.track(Persister.newInMemory()).also {
                checks += BdkRegtestElectrumScanCheck.InMemoryPersisterCreated
            }
            val wallet = closeBag.track(
                Wallet(
                    descriptor = externalDescriptor,
                    changeDescriptor = internalDescriptor,
                    network = Network.REGTEST,
                    persister = persister,
                ),
            ).also {
                checks += BdkRegtestElectrumScanCheck.BdkWalletCreated
            }
            addressInfo = wallet.revealNextAddress(KeychainKind.EXTERNAL).also {
                checks += BdkRegtestElectrumScanCheck.ReceiveAddressRevealed
            }
            val receiveAddress = addressInfo.address.toString()
            val walletContext = receiveAddressWalletContext()
            val displayedAddress = ReceiveAddressState.placeholderReserved(
                wallet = walletContext,
                derivationIndex = ReceiveAddressDerivationIndex(addressInfo.index.toInt()),
            ).markDisplayed()
            require(displayedAddress.lifecycleState == ReceiveAddressLifecycleState.Displayed)
            checks += BdkRegtestElectrumScanCheck.ReceiveAddressModeledAsDisplayed

            if (!session.fundAddress(receiveAddress, FundingAmountBtc, harnessChecks)) {
                return failed(
                    checks = checks,
                    harnessState = RegtestElectrumIndexerState.Completed,
                    harnessChecks = harnessChecks,
                    code = "BDK_REGTEST_ELECTRUM_SCAN_FUNDING_FAILED",
                    safeDetail = "Local regtest harness could not fund the runtime-derived address. Address and transaction details were not exposed.",
                )
            }
            checks += BdkRegtestElectrumScanCheck.LocalRegtestAddressFunded

            val electrum = closeBag.track(createReadyElectrumClient(session)).also {
                checks += BdkRegtestElectrumScanCheck.ElectrumClientCreated
            }
            checks += BdkRegtestElectrumScanCheck.ElectrumPingCompleted
            val fullScanRequest = closeBag.track(wallet.startFullScan()).build().also {
                checks += BdkRegtestElectrumScanCheck.FullScanRequestBuilt
            }
            update = electrum.fullScan(fullScanRequest, 20uL, 1uL, true).also {
                checks += BdkRegtestElectrumScanCheck.ElectrumFullScanCompleted
            }
            wallet.applyUpdate(update)
            checks += BdkRegtestElectrumScanCheck.WalletUpdateApplied

            outputs += wallet.listUnspent()
            val unspentOutputs = outputs.filterNot { it.isSpent }
            val observedAmountSats = unspentOutputs.sumOf { it.txout.value.toSat().toLong() }
            if (unspentOutputs.isEmpty() || observedAmountSats <= 0) {
                return failed(
                    checks = checks,
                    harnessState = RegtestElectrumIndexerState.Completed,
                    code = "BDK_REGTEST_ELECTRUM_SCAN_NO_UTXO_OBSERVED",
                    safeDetail = "BDK Electrum scan completed but did not produce a positive regtest UTXO observation.",
                )
            }
            checks += BdkRegtestElectrumScanCheck.UtxosObserved
            val confirmations = if (unspentOutputs.any { it.chainPosition is ChainPosition.Confirmed }) {
                1
            } else {
                0
            }
            val summary = BdkRegtestUtxoScanValidation.applyObservedUtxoToReceivePolicy(
                wallet = walletContext,
                candidate = displayedAddress,
                observation = SanitizedRegtestUtxoObservation(
                    amountSats = observedAmountSats,
                    confirmations = confirmations,
                ),
            )
            require(summary.totalAmountSats == observedAmountSats)
            require(summary.observedUtxoCount == 1)
            require(summary.addressMarkedUsed)
            require(summary.reuseDecision.state == ReceiveAddressPolicyDecisionState.WarningRequired)
            checks += BdkRegtestElectrumScanCheck.ReceivePolicyTransitionApplied
            checks += BdkRegtestElectrumScanCheck.NoProductionStorageUsed

            BdkRegtestElectrumScanResult(
                state = BdkRegtestElectrumScanState.Completed,
                network = BdkRegtestUtxoScanValidationNetwork.Regtest,
                checks = checks.distinct(),
                capabilities = defaultCapabilities(),
                harnessState = RegtestElectrumIndexerState.Completed,
                harnessChecks = harnessChecks.distinct(),
                scanSummary = summary,
                observedUtxoCount = unspentOutputs.size,
                observedAmountSats = observedAmountSats,
                error = null,
                diagnostic = "Test-only BDK Electrum regtest scan completed with sanitized UTXO observation and no production wallet sync.",
            )
        } finally {
            outputs.forEach { runCatching { it.destroy() } }
            runCatching { update?.close() }
            runCatching { addressInfo?.destroy() }
            entropy.fill(0)
            closeBag.closeAll()
        }
    }

    private fun receiveAddressWalletContext(): ReceiveAddressWalletContext =
        ReceiveAddressWalletContext(
            profileId = DescriptorWalletProfileId("bdk-regtest-electrum-scan-runtime-wallet"),
            profileLabel = "BDK regtest Electrum scan runtime wallet",
            network = NetworkEnvironment.Regtest,
            source = ReceiveAddressSource.NativeDescriptor,
            operationalState = ReceiveAddressWalletOperationalState.OperationalDevelopmentWallet,
            canDeriveReceiveAddresses = true,
        )

    private fun disabled(
        code: String,
        safeDetail: String,
    ): BdkRegtestElectrumScanResult =
        BdkRegtestElectrumScanResult(
            state = BdkRegtestElectrumScanState.Disabled,
            network = null,
            checks = emptyList(),
            capabilities = emptySet(),
            harnessState = null,
            harnessChecks = emptyList(),
            scanSummary = null,
            observedUtxoCount = 0,
            observedAmountSats = 0,
            error = BdkRegtestElectrumScanError(code = code, safeDetail = safeDetail),
            diagnostic = "BDK Electrum scan adapter is disabled unless explicitly requested for desktop tests.",
        )

    private fun failed(
        checks: List<BdkRegtestElectrumScanCheck>,
        harnessState: RegtestElectrumIndexerState?,
        harnessChecks: List<RegtestElectrumIndexerCheck> = emptyList(),
        code: String,
        safeDetail: String,
    ): BdkRegtestElectrumScanResult =
        BdkRegtestElectrumScanResult(
            state = BdkRegtestElectrumScanState.Failed,
            network = BdkRegtestUtxoScanValidationNetwork.Regtest,
            checks = checks.distinct(),
            capabilities = defaultCapabilities(),
            harnessState = harnessState,
            harnessChecks = harnessChecks.distinct(),
            scanSummary = null,
            observedUtxoCount = 0,
            observedAmountSats = 0,
            error = BdkRegtestElectrumScanError(code = code, safeDetail = safeDetail),
            diagnostic = "BDK Electrum scan adapter failed with a redacted Skald-owned error.",
        )

    private fun defaultCapabilities(): Set<BdkRegtestElectrumScanCapability> =
        setOf(
            BdkRegtestElectrumScanCapability.DesktopTestOnly,
            BdkRegtestElectrumScanCapability.LocalRegtestOnly,
            BdkRegtestElectrumScanCapability.LocalElectrumOnly,
            BdkRegtestElectrumScanCapability.BdkElectrumFullScanApi,
            BdkRegtestElectrumScanCapability.InMemoryBdkPersister,
            BdkRegtestElectrumScanCapability.SkaldOwnedResultTypes,
            BdkRegtestElectrumScanCapability.RedactedDiagnostics,
            BdkRegtestElectrumScanCapability.ReceiveAddressPolicyIntegration,
            BdkRegtestElectrumScanCapability.NoPublicEndpoint,
            BdkRegtestElectrumScanCapability.NoProductionStorage,
            BdkRegtestElectrumScanCapability.NoProductionNetworking,
            BdkRegtestElectrumScanCapability.NoSigning,
            BdkRegtestElectrumScanCapability.NoBroadcasting,
            BdkRegtestElectrumScanCapability.NoMainnet,
        )

    private fun safeFailureStage(failure: Throwable): String =
        when (failure) {
            is UnsatisfiedLinkError -> "BDK JVM native binding linkage"
            is NoSuchMethodError -> "BDK JVM method linkage"
            is LinkageError -> "BDK JVM linkage"
            is SecurityException -> "JVM security policy"
            is LocalElectrumClientReadinessException -> "local Electrum client readiness (${failure.safeReason})"
            else -> "test-only BDK Electrum scan"
        }

    private fun safeLastCheck(checks: List<BdkRegtestElectrumScanCheck>): String =
        checks.lastOrNull()?.label ?: "adapter start"

    private fun createReadyElectrumClient(session: RegtestElectrumIndexerSession): ElectrumClient {
        val constructorFailures = mutableMapOf<String, Int>()
        val pingFailures = mutableMapOf<String, Int>()
        repeat(ElectrumClientConnectionAttempts) { attempt ->
            session.endpoint.bdkServerUrlCandidates.forEach { localEndpoint ->
                val client = runCatching {
                    ElectrumClient(localEndpoint)
                }.getOrElse { failure ->
                    constructorFailures.increment(failure.safeFailureDescriptor())
                    return@forEach
                }
                val ping = runCatching { client.ping() }
                if (ping.isSuccess) {
                    return client
                }
                ping.exceptionOrNull()?.let { pingFailures.increment(it.safeFailureDescriptor()) }
                runCatching { client.close() }
            }
            if (attempt < ElectrumClientConnectionAttempts - 1) {
                Thread.sleep(ElectrumClientConnectionRetryDelayMillis)
            }
        }
        throw LocalElectrumClientReadinessException(
            safeReason = "constructor=${constructorFailures.safeSummary()}, ping=${pingFailures.safeSummary()}",
        )
    }

    private const val ElectrumClientConnectionAttempts = 24
    private const val ElectrumClientConnectionRetryDelayMillis = 250L
}

private class LocalElectrumClientReadinessException(
    val safeReason: String,
) : Exception()

private fun MutableMap<String, Int>.increment(key: String) {
    this[key] = (this[key] ?: 0) + 1
}

private fun Map<String, Int>.safeSummary(): String =
    if (isEmpty()) {
        "none"
    } else {
        entries
            .sortedBy { it.key }
            .joinToString(separator = "|") { "${it.key}:${it.value}" }
    }

private fun Throwable.safeFailureDescriptor(): String {
    val className = this::class.simpleName
        ?.replace(Regex("""[^A-Za-z0-9_.-]"""), "_")
        ?: "UnknownThrowable"
    val message = message
        ?.replace(Regex("""127\.0\.0\.1:\d+"""), "LOCALHOST_PORT")
        ?.replace(Regex("""\b\d{2,}\b"""), "#")
        ?.replace(Regex("""/[^ ,;:)]+"""), "<PATH>")
        ?.replace(Regex("""[^A-Za-z0-9_.:<> -]"""), "_")
        ?.take(96)
        ?.ifBlank { null }
    return if (message == null) className else "$className:$message"
}

private class BdkElectrumScanCloseBag {
    private val closeables = mutableListOf<AutoCloseable>()

    fun <T : AutoCloseable> track(closeable: T): T =
        closeable.also { closeables += it }

    fun closeAll() {
        closeables.asReversed().forEach { closeable ->
            runCatching { closeable.close() }
        }
    }
}
