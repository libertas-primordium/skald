package com.libertasprimordium.skald.regtest

import java.io.File
import java.net.InetSocketAddress
import java.net.ServerSocket
import java.net.Socket
import java.nio.file.Files
import java.util.concurrent.TimeUnit

enum class RegtestElectrumIndexerState(val label: String) {
    Disabled("disabled"),
    Unavailable("unavailable"),
    Completed("completed"),
    Failed("failed"),
}

enum class RegtestElectrumIndexerCapability(val label: String) {
    BinaryDiscovery("electrs binary discovery"),
    BitcoindHarnessDependency("temporary bitcoind regtest dependency"),
    TemporaryDatadirs("temporary bitcoind and indexer datadirs"),
    LocalRegtestOnly("local regtest only"),
    LocalhostOnly("localhost-only Electrum endpoint"),
    ElectrumReadinessCheck("Electrum TCP readiness check"),
    TestHarnessBlockGeneration("test-harness block generation"),
    CleanShutdown("clean shutdown"),
    NoPublicEndpoint("no public endpoint"),
    NoProductionBackend("no production backend"),
    NoSkaldWalletStorage("no Skald wallet storage"),
}

enum class RegtestElectrumIndexerCheck(val label: String) {
    IntegrationOptIn("local Electrum regtest opt-in"),
    BitcoindDiscovered("bitcoind discovered"),
    BitcoinCliDiscovered("bitcoin-cli discovered"),
    ElectrumIndexerDiscovered("electrs discovered"),
    TemporaryDatadirsCreated("temporary datadirs created"),
    BitcoindStarted("bitcoind started in regtest"),
    NodeReady("regtest node ready"),
    ElectrumIndexerStarted("local Electrum indexer started"),
    ElectrumIndexerReady("local Electrum indexer ready"),
    MiningWalletCreated("temporary bitcoind mining wallet created"),
    BlockGenerated("regtest block generated while indexer was running"),
    FundingTransactionSent("regtest funding transaction sent by harness wallet"),
    FundingConfirmationMined("regtest funding confirmation mined"),
    CleanShutdown("clean shutdown"),
    TemporaryDatadirsCleaned("temporary datadirs cleaned"),
}

data class RegtestElectrumIndexerError(
    val code: String,
    val safeDetail: String,
)

data class RegtestElectrumIndexerResult(
    val state: RegtestElectrumIndexerState,
    val checks: List<RegtestElectrumIndexerCheck>,
    val capabilities: Set<RegtestElectrumIndexerCapability>,
    val error: RegtestElectrumIndexerError?,
) {
    val completed: Boolean
        get() = state == RegtestElectrumIndexerState.Completed

    val usesPublicEndpoint: Boolean
        get() = RegtestElectrumIndexerCapability.NoPublicEndpoint !in capabilities &&
            state != RegtestElectrumIndexerState.Disabled &&
            state != RegtestElectrumIndexerState.Unavailable

    val usesProductionBackend: Boolean
        get() = RegtestElectrumIndexerCapability.NoProductionBackend !in capabilities &&
            state != RegtestElectrumIndexerState.Disabled &&
            state != RegtestElectrumIndexerState.Unavailable

    val mainnetEnabled: Boolean
        get() = false
}

data class RegtestElectrumIndexerBinaryPaths(
    val electrs: File,
)

sealed interface RegtestElectrumIndexerAvailability {
    data class Available(
        val paths: RegtestElectrumIndexerBinaryPaths,
    ) : RegtestElectrumIndexerAvailability

    data class Unavailable(
        val missingBinaries: Set<String>,
        val safeDetail: String,
    ) : RegtestElectrumIndexerAvailability
}

data class RegtestElectrumIndexerIntegrationPolicy(
    val enabled: Boolean,
) {
    companion object {
        const val EnvironmentVariable = "SKALD_RUN_LOCAL_ELECTRUM_REGTEST"

        fun fromEnvironment(
            environment: Map<String, String> = System.getenv(),
        ): RegtestElectrumIndexerIntegrationPolicy =
            RegtestElectrumIndexerIntegrationPolicy(enabled = environment[EnvironmentVariable] == "1")
    }
}

object RegtestElectrumIndexerBinaryDiscovery {
    const val ElectrsEnvironmentVariable = "SKALD_ELECTRS"

    fun discover(
        environment: Map<String, String> = System.getenv(),
        pathValue: String? = environment["PATH"],
    ): RegtestElectrumIndexerAvailability {
        val electrs = findExecutable(
            environmentValue = environment[ElectrsEnvironmentVariable],
            executableName = "electrs",
            pathValue = pathValue,
        )
        if (electrs == null) {
            return RegtestElectrumIndexerAvailability.Unavailable(
                missingBinaries = setOf("electrs"),
                safeDetail = "Missing local Electrum-compatible regtest indexer binary: electrs. Set $ElectrsEnvironmentVariable or add electrs to PATH.",
            )
        }

        return RegtestElectrumIndexerAvailability.Available(
            paths = RegtestElectrumIndexerBinaryPaths(electrs = electrs),
        )
    }

    private fun findExecutable(
        environmentValue: String?,
        executableName: String,
        pathValue: String?,
    ): File? {
        environmentValue
            ?.takeIf { it.isNotBlank() }
            ?.let(::File)
            ?.takeIf { it.isFile && it.canExecute() }
            ?.let { return it }

        return pathValue
            .orEmpty()
            .split(File.pathSeparator)
            .asSequence()
            .filter { it.isNotBlank() }
            .map { File(it, executableName) }
            .firstOrNull { it.isFile && it.canExecute() }
    }
}

data class RegtestElectrumIndexerLaunchConfig(
    val bitcoind: RegtestBitcoindLaunchConfig,
    val indexerDatadir: File,
    val electrumPort: Int,
)

data class RegtestElectrumIndexerEndpoint(
    val host: String,
    val port: Int,
) {
    val bdkServerUrl: String
        get() = "tcp://$host:$port"

    val bdkServerUrlCandidates: List<String>
        get() = listOf(
            "tcp://$host:$port",
            "$host:$port",
        )

    override fun toString(): String = "LOCAL_REGTEST_ELECTRUM_ENDPOINT_REDACTED"
}

object RegtestElectrumIndexerCommands {
    fun electrs(
        paths: RegtestElectrumIndexerBinaryPaths,
        config: RegtestElectrumIndexerLaunchConfig,
    ): RegtestHarnessCommand =
        RegtestHarnessCommand(
            executable = paths.electrs.absolutePath,
            arguments = listOf(
                "--network=regtest",
                "--daemon-dir=${config.bitcoind.datadir.absolutePath}",
                "--daemon-rpc-addr=127.0.0.1:${config.bitcoind.rpcPort}",
                "--daemon-p2p-addr=127.0.0.1:${config.bitcoind.p2pPort}",
                "--db-dir=${config.indexerDatadir.absolutePath}",
                "--electrum-rpc-addr=127.0.0.1:${config.electrumPort}",
            ),
        )
}

class RegtestElectrumIndexerHarness(
    private val bitcoinPaths: RegtestBinaryPaths,
    private val indexerPaths: RegtestElectrumIndexerBinaryPaths,
    private val commandRunner: RegtestCommandRunner = RegtestCommandRunner(),
) {
    fun runSmoke(): RegtestElectrumIndexerResult {
        var blockGenerated = false
        return runWithReadyIndexer { session, checks ->
            blockGenerated = session.generateOneBlock(checks)
            blockGenerated
        }.let { result ->
            if (blockGenerated) {
                result
            } else if (result.state == RegtestElectrumIndexerState.Completed) {
                result.copy(
                    state = RegtestElectrumIndexerState.Failed,
                    error = error(
                        code = "LOCAL_ELECTRUM_REGTEST_BLOCK_GENERATION_FAILED",
                        safeDetail = "Regtest block generation failed while the local Electrum indexer was running.",
                    ),
                )
            } else {
                result
            }
        }
    }

    fun runWithReadyIndexer(
        action: (RegtestElectrumIndexerSession, MutableList<RegtestElectrumIndexerCheck>) -> Boolean,
    ): RegtestElectrumIndexerResult {
        val bitcoindDatadir = Files.createTempDirectory("skald-regtest-bitcoind-electrum-").toFile()
        val indexerDatadir = Files.createTempDirectory("skald-regtest-electrum-indexer-").toFile()
        val config = RegtestElectrumIndexerLaunchConfig(
            bitcoind = RegtestBitcoindLaunchConfig(
                datadir = bitcoindDatadir,
                rpcPort = availableLocalPort(),
                p2pPort = availableLocalPort(),
                p2pListen = true,
            ),
            indexerDatadir = indexerDatadir,
            electrumPort = availableLocalPort(),
        )
        val checks = mutableListOf<RegtestElectrumIndexerCheck>()
        var bitcoindProcess: Process? = null
        var indexerProcess: Process? = null
        var state = RegtestElectrumIndexerState.Failed
        var error: RegtestElectrumIndexerError? = null

        try {
            checks += RegtestElectrumIndexerCheck.TemporaryDatadirsCreated
            bitcoindProcess = startProcess(RegtestBitcoindCommands.bitcoind(bitcoinPaths, config.bitcoind))
            checks += RegtestElectrumIndexerCheck.BitcoindStarted

            val ready = waitUntilBitcoindReady(config.bitcoind)
            if (!ready.succeeded) {
                error = error(
                    code = "LOCAL_ELECTRUM_REGTEST_BITCOIND_NOT_READY",
                    safeDetail = "Temporary regtest bitcoind did not become ready before timeout.",
                )
            } else {
                checks += RegtestElectrumIndexerCheck.NodeReady
                indexerProcess = startProcess(RegtestElectrumIndexerCommands.electrs(indexerPaths, config))
                checks += RegtestElectrumIndexerCheck.ElectrumIndexerStarted

                if (!waitUntilElectrumReady(config.electrumPort)) {
                    error = error(
                        code = "LOCAL_ELECTRUM_REGTEST_INDEXER_NOT_READY",
                        safeDetail = "Local electrs did not accept a localhost Electrum connection before timeout.",
                    )
                } else {
                    checks += RegtestElectrumIndexerCheck.ElectrumIndexerReady
                    val session = RegtestElectrumIndexerSession(
                        endpoint = RegtestElectrumIndexerEndpoint("127.0.0.1", config.electrumPort),
                        bitcoinPaths = bitcoinPaths,
                        config = config.bitcoind,
                        commandRunner = commandRunner,
                    )
                    if (action(session, checks)) {
                        state = RegtestElectrumIndexerState.Completed
                    } else {
                        error = error(
                            code = "LOCAL_ELECTRUM_REGTEST_ACTION_FAILED",
                            safeDetail = "Local Electrum regtest harness action failed after the indexer became ready.",
                        )
                    }
                }
            }
        } catch (failure: Exception) {
            state = RegtestElectrumIndexerState.Failed
            error = error(
                code = "LOCAL_ELECTRUM_REGTEST_PROCESS_FAILED",
                safeDetail = "Local Electrum regtest harness failed before completing the smoke lifecycle.",
            )
        } finally {
            val indexerStopped = stopIndexer(indexerProcess)
            val bitcoindStopped = stopBitcoind(config.bitcoind, bitcoindProcess)
            if (indexerStopped || bitcoindStopped) {
                checks += RegtestElectrumIndexerCheck.CleanShutdown
            }
            bitcoindDatadir.deleteRecursively()
            indexerDatadir.deleteRecursively()
            checks += RegtestElectrumIndexerCheck.TemporaryDatadirsCleaned
        }

        return result(state, checks, error)
    }

    private fun startProcess(command: RegtestHarnessCommand): Process =
        ProcessBuilder(listOf(command.executable) + command.arguments)
            .redirectOutput(ProcessBuilder.Redirect.DISCARD)
            .redirectError(ProcessBuilder.Redirect.DISCARD)
            .start()

    private fun waitUntilBitcoindReady(config: RegtestBitcoindLaunchConfig): RegtestProcessResult {
        var lastResult = RegtestProcessResult(
            exitCode = null,
            timedOut = true,
            stdout = "",
            stderr = "Node readiness was not checked.",
        )
        repeat(60) {
            lastResult = runCli(config, listOf("getblockchaininfo"), timeoutOverrideMillis = 1_000L)
            if (lastResult.succeeded) return lastResult
            Thread.sleep(250L)
        }
        return lastResult
    }

    private fun waitUntilElectrumReady(port: Int): Boolean {
        repeat(80) {
            if (canConnectLocalhost(port)) return true
            Thread.sleep(250L)
        }
        return false
    }

    private fun canConnectLocalhost(port: Int): Boolean =
        runCatching {
            Socket().use { socket ->
                socket.connect(InetSocketAddress("127.0.0.1", port), 500)
            }
        }.isSuccess

    private fun stopIndexer(process: Process?): Boolean {
        if (process == null) return false
        process.destroy()
        val stopped = process.waitFor(5_000L, TimeUnit.MILLISECONDS)
        if (!stopped) {
            process.destroyForcibly()
        }
        return stopped || !process.isAlive
    }

    private fun stopBitcoind(
        config: RegtestBitcoindLaunchConfig,
        process: Process?,
    ): Boolean {
        if (process == null) return false
        return try {
            runCli(config, listOf("stop"), timeoutOverrideMillis = 2_000L)
            val stopped = process.waitFor(5_000L, TimeUnit.MILLISECONDS)
            if (!stopped) {
                process.destroyForcibly()
            }
            stopped || !process.isAlive
        } catch (failure: Exception) {
            process.destroyForcibly()
            false
        }
    }

    private fun runCli(
        config: RegtestBitcoindLaunchConfig,
        commandArguments: List<String>,
        timeoutOverrideMillis: Long = 5_000L,
    ): RegtestProcessResult =
        commandRunner.run(
            command = RegtestBitcoindCommands.bitcoinCli(bitcoinPaths, config, commandArguments),
            redactions = listOf(config.datadir.absolutePath),
            timeoutOverrideMillis = timeoutOverrideMillis,
        )

    private fun error(
        code: String,
        safeDetail: String,
    ): RegtestElectrumIndexerError =
        RegtestElectrumIndexerError(code = code, safeDetail = safeDetail)

    private fun result(
        state: RegtestElectrumIndexerState,
        checks: List<RegtestElectrumIndexerCheck>,
        error: RegtestElectrumIndexerError?,
    ): RegtestElectrumIndexerResult =
        RegtestElectrumIndexerResult(
            state = state,
            checks = checks.distinct(),
            capabilities = defaultCapabilities(),
            error = error,
        )

    private fun defaultCapabilities(): Set<RegtestElectrumIndexerCapability> =
        setOf(
            RegtestElectrumIndexerCapability.BinaryDiscovery,
            RegtestElectrumIndexerCapability.BitcoindHarnessDependency,
            RegtestElectrumIndexerCapability.TemporaryDatadirs,
            RegtestElectrumIndexerCapability.LocalRegtestOnly,
            RegtestElectrumIndexerCapability.LocalhostOnly,
            RegtestElectrumIndexerCapability.ElectrumReadinessCheck,
            RegtestElectrumIndexerCapability.TestHarnessBlockGeneration,
            RegtestElectrumIndexerCapability.CleanShutdown,
            RegtestElectrumIndexerCapability.NoPublicEndpoint,
            RegtestElectrumIndexerCapability.NoProductionBackend,
            RegtestElectrumIndexerCapability.NoSkaldWalletStorage,
        )
}

class RegtestElectrumIndexerSession internal constructor(
    val endpoint: RegtestElectrumIndexerEndpoint,
    private val bitcoinPaths: RegtestBinaryPaths,
    private val config: RegtestBitcoindLaunchConfig,
    private val commandRunner: RegtestCommandRunner,
) {
    private var miningWalletCreated = false
    private var matureCoinsPrepared = false

    fun generateOneBlock(
        checks: MutableList<RegtestElectrumIndexerCheck>,
    ): Boolean {
        if (!ensureMiningWallet(checks)) return false
        return mineBlocks(1, checks, RegtestElectrumIndexerCheck.BlockGenerated)
    }

    fun fundAddress(
        address: String,
        amountBtc: String,
        checks: MutableList<RegtestElectrumIndexerCheck>,
    ): Boolean {
        if (address.isBlank()) return false
        if (!ensureMatureCoins(checks)) return false
        val sent = runCli(
            commandArguments = listOf("-rpcwallet=$MiningWalletName", "sendtoaddress", address, amountBtc),
            timeoutOverrideMillis = 10_000L,
        )
        if (!sent.succeeded) return false
        checks += RegtestElectrumIndexerCheck.FundingTransactionSent
        return mineBlocks(1, checks, RegtestElectrumIndexerCheck.FundingConfirmationMined)
    }

    private fun ensureMatureCoins(checks: MutableList<RegtestElectrumIndexerCheck>): Boolean {
        if (matureCoinsPrepared) return true
        if (!ensureMiningWallet(checks)) return false
        if (!mineBlocks(101, checks, null)) return false
        matureCoinsPrepared = true
        return true
    }

    private fun ensureMiningWallet(checks: MutableList<RegtestElectrumIndexerCheck>): Boolean {
        if (miningWalletCreated) return true
        val walletCreated = runCli(listOf("createwallet", MiningWalletName))
        if (!walletCreated.succeeded) return false
        checks += RegtestElectrumIndexerCheck.MiningWalletCreated
        miningWalletCreated = true
        return true
    }

    private fun mineBlocks(
        count: Int,
        checks: MutableList<RegtestElectrumIndexerCheck>,
        completionCheck: RegtestElectrumIndexerCheck?,
    ): Boolean {
        val address = runCli(
            commandArguments = listOf("-rpcwallet=$MiningWalletName", "getnewaddress", "skald-regtest-electrum-mining", "bech32"),
        ).stdout.trim()
        if (address.isBlank()) return false

        val mined = runCli(
            commandArguments = listOf("-rpcwallet=$MiningWalletName", "generatetoaddress", count.toString(), address),
            timeoutOverrideMillis = 30_000L,
        ).succeeded
        if (mined && completionCheck != null) {
            checks += completionCheck
        }
        return mined
    }

    private fun runCli(
        commandArguments: List<String>,
        timeoutOverrideMillis: Long = 5_000L,
    ): RegtestProcessResult =
        commandRunner.run(
            command = RegtestBitcoindCommands.bitcoinCli(bitcoinPaths, config, commandArguments),
            redactions = listOf(config.datadir.absolutePath),
            timeoutOverrideMillis = timeoutOverrideMillis,
        )

    private companion object {
        const val MiningWalletName = "skald_regtest_electrum_mining"
    }
}

object RegtestElectrumIndexerSmoke {
    fun runFromEnvironment(
        environment: Map<String, String> = System.getenv(),
    ): RegtestElectrumIndexerResult {
        if (!RegtestElectrumIndexerIntegrationPolicy.fromEnvironment(environment).enabled) {
            return RegtestElectrumIndexerResult(
                state = RegtestElectrumIndexerState.Disabled,
                checks = listOf(RegtestElectrumIndexerCheck.IntegrationOptIn),
                capabilities = emptySet(),
                error = RegtestElectrumIndexerError(
                    code = "LOCAL_ELECTRUM_REGTEST_DISABLED",
                    safeDetail = "Set ${RegtestElectrumIndexerIntegrationPolicy.EnvironmentVariable}=1 to run the local Electrum-compatible regtest harness.",
                ),
            )
        }

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
            return RegtestElectrumIndexerResult(
                state = RegtestElectrumIndexerState.Unavailable,
                checks = listOf(RegtestElectrumIndexerCheck.IntegrationOptIn),
                capabilities = setOf(RegtestElectrumIndexerCapability.BinaryDiscovery),
                error = RegtestElectrumIndexerError(
                    code = "LOCAL_ELECTRUM_REGTEST_BINARIES_UNAVAILABLE",
                    safeDetail = "Missing local regtest binaries: ${missing.joinToString()}. Set ${RegtestBinaryDiscovery.BitcoindEnvironmentVariable}, ${RegtestBinaryDiscovery.BitcoinCliEnvironmentVariable}, and ${RegtestElectrumIndexerBinaryDiscovery.ElectrsEnvironmentVariable} or add them to PATH.",
                ),
            )
        }

        val bitcoinPaths = (bitcoinAvailability as RegtestHarnessAvailability.Available).paths
        val indexerPaths = (indexerAvailability as RegtestElectrumIndexerAvailability.Available).paths
        return RegtestElectrumIndexerHarness(
            bitcoinPaths = bitcoinPaths,
            indexerPaths = indexerPaths,
        ).runSmoke().withPrependedChecks(
            RegtestElectrumIndexerCheck.IntegrationOptIn,
            RegtestElectrumIndexerCheck.BitcoindDiscovered,
            RegtestElectrumIndexerCheck.BitcoinCliDiscovered,
            RegtestElectrumIndexerCheck.ElectrumIndexerDiscovered,
        )
    }
}

private fun RegtestElectrumIndexerResult.withPrependedChecks(
    vararg prependedChecks: RegtestElectrumIndexerCheck,
): RegtestElectrumIndexerResult =
    copy(checks = (prependedChecks.toList() + checks).distinct())

private fun availableLocalPort(): Int =
    ServerSocket(0).use { socket ->
        socket.reuseAddress = true
        socket.localPort
    }
