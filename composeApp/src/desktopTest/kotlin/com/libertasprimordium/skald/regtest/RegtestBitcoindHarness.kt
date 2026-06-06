package com.libertasprimordium.skald.regtest

import java.io.File
import java.net.ServerSocket
import java.nio.charset.Charset
import java.nio.file.Files
import java.util.concurrent.TimeUnit

enum class RegtestHarnessState(val label: String) {
    Disabled("disabled"),
    Unavailable("unavailable"),
    Completed("completed"),
    Failed("failed"),
}

enum class RegtestHarnessCapability(val label: String) {
    BinaryDiscovery("bitcoind and bitcoin-cli binary discovery"),
    TemporaryDatadir("temporary regtest datadir"),
    LocalRegtestOnly("local regtest only"),
    NoDiscoveryOrListening("peer discovery and listening disabled"),
    ReadinessCheck("bitcoin-cli readiness check"),
    BlockchainInfoRead("regtest blockchain info read"),
    TestHarnessBlockGeneration("test-harness block generation"),
    CleanShutdown("clean shutdown"),
    NoSkaldWalletStorage("no Skald wallet storage"),
}

enum class RegtestHarnessCheck(val label: String) {
    IntegrationOptIn("integration opt-in"),
    BitcoindDiscovered("bitcoind discovered"),
    BitcoinCliDiscovered("bitcoin-cli discovered"),
    TemporaryDatadirCreated("temporary datadir created"),
    BitcoindStarted("bitcoind started in regtest"),
    NodeReady("regtest node ready"),
    BlockchainInfoRead("blockchain info read"),
    MiningWalletCreated("temporary bitcoind mining wallet created"),
    BlockGenerated("regtest block generated"),
    CleanShutdown("clean shutdown"),
    TemporaryDatadirCleaned("temporary datadir cleaned"),
}

data class RegtestHarnessError(
    val code: String,
    val safeDetail: String,
)

data class RegtestHarnessResult(
    val state: RegtestHarnessState,
    val checks: List<RegtestHarnessCheck>,
    val capabilities: Set<RegtestHarnessCapability>,
    val error: RegtestHarnessError?,
) {
    val completed: Boolean
        get() = state == RegtestHarnessState.Completed
}

data class RegtestBinaryPaths(
    val bitcoind: File,
    val bitcoinCli: File,
)

sealed interface RegtestHarnessAvailability {
    data class Available(
        val paths: RegtestBinaryPaths,
    ) : RegtestHarnessAvailability

    data class Unavailable(
        val missingBinaries: Set<String>,
        val safeDetail: String,
    ) : RegtestHarnessAvailability
}

data class RegtestIntegrationPolicy(
    val enabled: Boolean,
) {
    companion object {
        const val EnvironmentVariable = "SKALD_RUN_REGTEST_INTEGRATION"

        fun fromEnvironment(environment: Map<String, String> = System.getenv()): RegtestIntegrationPolicy =
            RegtestIntegrationPolicy(enabled = environment[EnvironmentVariable] == "1")
    }
}

object RegtestBinaryDiscovery {
    const val BitcoindEnvironmentVariable = "SKALD_BITCOIND"
    const val BitcoinCliEnvironmentVariable = "SKALD_BITCOIN_CLI"

    fun discover(
        environment: Map<String, String> = System.getenv(),
        pathValue: String? = environment["PATH"],
    ): RegtestHarnessAvailability {
        val bitcoind = findExecutable(
            environmentValue = environment[BitcoindEnvironmentVariable],
            executableName = "bitcoind",
            pathValue = pathValue,
        )
        val bitcoinCli = findExecutable(
            environmentValue = environment[BitcoinCliEnvironmentVariable],
            executableName = "bitcoin-cli",
            pathValue = pathValue,
        )
        val missing = buildSet {
            if (bitcoind == null) add("bitcoind")
            if (bitcoinCli == null) add("bitcoin-cli")
        }
        if (missing.isNotEmpty()) {
            return RegtestHarnessAvailability.Unavailable(
                missingBinaries = missing,
                safeDetail = "Missing local Bitcoin regtest binaries: ${missing.joinToString()}. Set $BitcoindEnvironmentVariable and $BitcoinCliEnvironmentVariable or add them to PATH.",
            )
        }
        return RegtestHarnessAvailability.Available(
            RegtestBinaryPaths(
                bitcoind = requireNotNull(bitcoind),
                bitcoinCli = requireNotNull(bitcoinCli),
            ),
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

data class RegtestBitcoindLaunchConfig(
    val datadir: File,
    val rpcPort: Int,
    val p2pPort: Int,
    val p2pListen: Boolean = false,
)

data class RegtestHarnessCommand(
    val executable: String,
    val arguments: List<String>,
) {
    val commandName: String
        get() = File(executable).name

    fun safeDisplay(redactions: List<String> = emptyList()): String =
        (listOf(commandName) + arguments)
            .joinToString(" ")
            .let { display ->
                redactions.fold(display) { current, secret -> current.replace(secret, "<REDACTED>") }
            }
}

object RegtestBitcoindCommands {
    fun bitcoind(
        paths: RegtestBinaryPaths,
        config: RegtestBitcoindLaunchConfig,
    ): RegtestHarnessCommand {
        val p2pFlags = if (config.p2pListen) {
            listOf(
                "-listen=1",
                "-bind=127.0.0.1",
            )
        } else {
            listOf("-listen=0")
        }

        return RegtestHarnessCommand(
            executable = paths.bitcoind.absolutePath,
            arguments = listOf(
                "-regtest",
                "-datadir=${config.datadir.absolutePath}",
                "-server=1",
            ) + p2pFlags + listOf(
                "-discover=0",
                "-dnsseed=0",
                "-fixedseeds=0",
                "-rpcbind=127.0.0.1",
                "-rpcallowip=127.0.0.1",
                "-rpcport=${config.rpcPort}",
                "-port=${config.p2pPort}",
                "-fallbackfee=0.0001",
            ),
        )
    }

    fun bitcoinCli(
        paths: RegtestBinaryPaths,
        config: RegtestBitcoindLaunchConfig,
        commandArguments: List<String>,
    ): RegtestHarnessCommand =
        RegtestHarnessCommand(
            executable = paths.bitcoinCli.absolutePath,
            arguments = listOf(
                "-regtest",
                "-datadir=${config.datadir.absolutePath}",
                "-rpcport=${config.rpcPort}",
            ) + commandArguments,
        )
}

data class RegtestProcessResult(
    val exitCode: Int?,
    val timedOut: Boolean,
    val stdout: String,
    val stderr: String,
) {
    val succeeded: Boolean
        get() = !timedOut && exitCode == 0
}

class RegtestCommandRunner(
    private val timeoutMillis: Long = 5_000L,
    private val outputLimit: Int = 1_200,
) {
    fun run(
        command: RegtestHarnessCommand,
        redactions: List<String> = emptyList(),
        timeoutOverrideMillis: Long = timeoutMillis,
    ): RegtestProcessResult {
        val process = ProcessBuilder(listOf(command.executable) + command.arguments).start()
        val finished = process.waitFor(timeoutOverrideMillis, TimeUnit.MILLISECONDS)
        if (!finished) {
            process.destroyForcibly()
            return RegtestProcessResult(
                exitCode = null,
                timedOut = true,
                stdout = "",
                stderr = "Command ${command.commandName} timed out.",
            )
        }

        return RegtestProcessResult(
            exitCode = process.exitValue(),
            timedOut = false,
            stdout = sanitize(process.inputStream.readBytes().toText(), redactions),
            stderr = sanitize(process.errorStream.readBytes().toText(), redactions),
        )
    }

    private fun sanitize(
        output: String,
        redactions: List<String>,
    ): String =
        redactions
            .filter { it.isNotBlank() }
            .fold(output) { current, secret -> current.replace(secret, "<REDACTED>") }
            .replace(Regex("""(?i)(rpcpassword|rpcauth|cookie)=\S+"""), "$1=<REDACTED>")
            .take(outputLimit)
}

class RegtestBitcoindHarness(
    private val paths: RegtestBinaryPaths,
    private val commandRunner: RegtestCommandRunner = RegtestCommandRunner(),
) {
    fun runSmoke(): RegtestHarnessResult {
        val datadir = Files.createTempDirectory("skald-regtest-bitcoind-").toFile()
        val config = RegtestBitcoindLaunchConfig(
            datadir = datadir,
            rpcPort = availableLocalPort(),
            p2pPort = availableLocalPort(),
        )
        val checks = mutableListOf<RegtestHarnessCheck>()
        var process: Process? = null
        var state = RegtestHarnessState.Failed
        var error: RegtestHarnessError? = null

        try {
            checks += RegtestHarnessCheck.TemporaryDatadirCreated
            val launch = RegtestBitcoindCommands.bitcoind(paths, config)
            process = ProcessBuilder(listOf(launch.executable) + launch.arguments)
                .redirectOutput(ProcessBuilder.Redirect.DISCARD)
                .redirectError(ProcessBuilder.Redirect.DISCARD)
                .start()
            checks += RegtestHarnessCheck.BitcoindStarted

            val ready = waitUntilReady(config)
            if (!ready.succeeded) {
                error = error(
                    code = "REGTEST_BITCOIND_NOT_READY",
                    safeDetail = "Local regtest bitcoind did not become ready before timeout.",
                )
            } else {
                checks += RegtestHarnessCheck.NodeReady

                val info = runCli(config, listOf("getblockchaininfo"))
                if (!info.succeeded) {
                    error = error(
                        code = "REGTEST_BLOCKCHAIN_INFO_FAILED",
                        safeDetail = "bitcoin-cli getblockchaininfo failed in local regtest mode.",
                    )
                } else {
                    checks += RegtestHarnessCheck.BlockchainInfoRead

                    val mined = generateOneBlock(config, checks)
                    if (!mined) {
                        error = error(
                            code = "REGTEST_BLOCK_GENERATION_FAILED",
                            safeDetail = "Regtest block generation failed in the temporary bitcoind test harness.",
                        )
                    } else {
                        checks += RegtestHarnessCheck.BlockGenerated
                        state = RegtestHarnessState.Completed
                    }
                }
            }
        } catch (failure: Exception) {
            state = RegtestHarnessState.Failed
            error = error(
                code = "REGTEST_HARNESS_PROCESS_FAILED",
                safeDetail = "Local regtest process harness failed before completing the smoke lifecycle.",
            )
        } finally {
            val stopped = stopProcess(config, process)
            if (stopped) {
                checks += RegtestHarnessCheck.CleanShutdown
            }
            datadir.deleteRecursively()
            checks += RegtestHarnessCheck.TemporaryDatadirCleaned
        }

        return result(state, checks, error)
    }

    private fun waitUntilReady(config: RegtestBitcoindLaunchConfig): RegtestProcessResult {
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

    private fun generateOneBlock(
        config: RegtestBitcoindLaunchConfig,
        checks: MutableList<RegtestHarnessCheck>,
    ): Boolean {
        val walletName = "skald_regtest_mining"
        val walletCreated = runCli(config, listOf("createwallet", walletName))
        if (!walletCreated.succeeded) return false
        checks += RegtestHarnessCheck.MiningWalletCreated

        val address = runCli(
            config = config,
            commandArguments = listOf("-rpcwallet=$walletName", "getnewaddress", "skald-regtest-mining", "bech32"),
        ).stdout.trim()
        if (address.isBlank()) return false

        return runCli(
            config = config,
            commandArguments = listOf("-rpcwallet=$walletName", "generatetoaddress", "1", address),
            timeoutOverrideMillis = 10_000L,
        ).succeeded
    }

    private fun stopProcess(
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
            command = RegtestBitcoindCommands.bitcoinCli(paths, config, commandArguments),
            redactions = listOf(config.datadir.absolutePath),
            timeoutOverrideMillis = timeoutOverrideMillis,
        )

    private fun error(
        code: String,
        safeDetail: String,
    ): RegtestHarnessError =
        RegtestHarnessError(code = code, safeDetail = safeDetail)

    private fun result(
        state: RegtestHarnessState,
        checks: List<RegtestHarnessCheck>,
        error: RegtestHarnessError?,
    ): RegtestHarnessResult =
        RegtestHarnessResult(
            state = state,
            checks = checks,
            capabilities = defaultCapabilities(),
            error = error,
        )

    private fun defaultCapabilities(): Set<RegtestHarnessCapability> =
        setOf(
            RegtestHarnessCapability.BinaryDiscovery,
            RegtestHarnessCapability.TemporaryDatadir,
            RegtestHarnessCapability.LocalRegtestOnly,
            RegtestHarnessCapability.NoDiscoveryOrListening,
            RegtestHarnessCapability.ReadinessCheck,
            RegtestHarnessCapability.BlockchainInfoRead,
            RegtestHarnessCapability.TestHarnessBlockGeneration,
            RegtestHarnessCapability.CleanShutdown,
            RegtestHarnessCapability.NoSkaldWalletStorage,
        )
}

object RegtestBitcoindSmoke {
    fun runFromEnvironment(
        environment: Map<String, String> = System.getenv(),
    ): RegtestHarnessResult {
        if (!RegtestIntegrationPolicy.fromEnvironment(environment).enabled) {
            return RegtestHarnessResult(
                state = RegtestHarnessState.Disabled,
                checks = listOf(RegtestHarnessCheck.IntegrationOptIn),
                capabilities = emptySet(),
                error = RegtestHarnessError(
                    code = "REGTEST_INTEGRATION_DISABLED",
                    safeDetail = "Set ${RegtestIntegrationPolicy.EnvironmentVariable}=1 to run the local bitcoind regtest smoke test.",
                ),
            )
        }

        return when (val availability = RegtestBinaryDiscovery.discover(environment)) {
            is RegtestHarnessAvailability.Available ->
                RegtestBitcoindHarness(availability.paths).runSmoke().withPrependedChecks(
                    RegtestHarnessCheck.IntegrationOptIn,
                    RegtestHarnessCheck.BitcoindDiscovered,
                    RegtestHarnessCheck.BitcoinCliDiscovered,
                )
            is RegtestHarnessAvailability.Unavailable ->
                RegtestHarnessResult(
                    state = RegtestHarnessState.Unavailable,
                    checks = listOf(RegtestHarnessCheck.IntegrationOptIn),
                    capabilities = setOf(RegtestHarnessCapability.BinaryDiscovery),
                    error = RegtestHarnessError(
                        code = "REGTEST_BINARIES_UNAVAILABLE",
                        safeDetail = availability.safeDetail,
                    ),
                )
        }
    }
}

private fun RegtestHarnessResult.withPrependedChecks(
    vararg prependedChecks: RegtestHarnessCheck,
): RegtestHarnessResult =
    copy(checks = prependedChecks.toList() + checks)

private fun availableLocalPort(): Int =
    ServerSocket(0).use { socket ->
        socket.reuseAddress = true
        socket.localPort
    }

private fun ByteArray.toText(): String =
    String(this, Charset.forName("UTF-8"))
