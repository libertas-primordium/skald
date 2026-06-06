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
        val bitcoindDatadir = Files.createTempDirectory("skald-regtest-bitcoind-electrum-").toFile()
        val indexerDatadir = Files.createTempDirectory("skald-regtest-electrum-indexer-").toFile()
        val config = RegtestElectrumIndexerLaunchConfig(
            bitcoind = RegtestBitcoindLaunchConfig(
                datadir = bitcoindDatadir,
                rpcPort = availableLocalPort(),
                p2pPort = availableLocalPort(),
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
                    if (generateOneBlock(config.bitcoind, checks)) {
                        checks += RegtestElectrumIndexerCheck.BlockGenerated
                        state = RegtestElectrumIndexerState.Completed
                    } else {
                        error = error(
                            code = "LOCAL_ELECTRUM_REGTEST_BLOCK_GENERATION_FAILED",
                            safeDetail = "Regtest block generation failed while the local Electrum indexer was running.",
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

    private fun generateOneBlock(
        config: RegtestBitcoindLaunchConfig,
        checks: MutableList<RegtestElectrumIndexerCheck>,
    ): Boolean {
        val walletName = "skald_regtest_electrum_mining"
        val walletCreated = runCli(config, listOf("createwallet", walletName))
        if (!walletCreated.succeeded) return false
        checks += RegtestElectrumIndexerCheck.MiningWalletCreated

        val address = runCli(
            config = config,
            commandArguments = listOf("-rpcwallet=$walletName", "getnewaddress", "skald-regtest-electrum-mining", "bech32"),
        ).stdout.trim()
        if (address.isBlank()) return false

        return runCli(
            config = config,
            commandArguments = listOf("-rpcwallet=$walletName", "generatetoaddress", "1", address),
            timeoutOverrideMillis = 10_000L,
        ).succeeded
    }

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
