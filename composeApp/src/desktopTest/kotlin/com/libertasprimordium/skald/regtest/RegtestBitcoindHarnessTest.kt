package com.libertasprimordium.skald.regtest

import java.io.File
import java.nio.file.Files
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class RegtestBitcoindHarnessTest {
    @Test
    fun binaryDiscoveryReportsUnavailableWhenBinariesAreMissing() {
        val availability = RegtestBinaryDiscovery.discover(
            environment = emptyMap(),
            pathValue = "",
        )

        val unavailable = assertIs<RegtestHarnessAvailability.Unavailable>(availability)
        assertContains(unavailable.missingBinaries, "bitcoind")
        assertContains(unavailable.missingBinaries, "bitcoin-cli")
        assertTrue(unavailable.safeDetail.contains(RegtestBinaryDiscovery.BitcoindEnvironmentVariable))
        assertTrue(unavailable.safeDetail.contains(RegtestBinaryDiscovery.BitcoinCliEnvironmentVariable))
    }

    @Test
    fun binaryDiscoveryUsesExplicitEnvironmentOverrides() {
        val tempDirectory = Files.createTempDirectory("skald-regtest-discovery-test-").toFile()
        val bitcoind = executableFile(tempDirectory, "bitcoind")
        val bitcoinCli = executableFile(tempDirectory, "bitcoin-cli")

        try {
            val availability = RegtestBinaryDiscovery.discover(
                environment = mapOf(
                    RegtestBinaryDiscovery.BitcoindEnvironmentVariable to bitcoind.absolutePath,
                    RegtestBinaryDiscovery.BitcoinCliEnvironmentVariable to bitcoinCli.absolutePath,
                ),
                pathValue = "",
            )

            val available = assertIs<RegtestHarnessAvailability.Available>(availability)
            assertEquals(bitcoind.absolutePath, available.paths.bitcoind.absolutePath)
            assertEquals(bitcoinCli.absolutePath, available.paths.bitcoinCli.absolutePath)
        } finally {
            tempDirectory.deleteRecursively()
        }
    }

    @Test
    fun bitcoindCommandUsesRegtestTempDatadirAndLocalOnlyFlags() {
        val datadir = File("/tmp/skald-regtest-command-test")
        val paths = RegtestBinaryPaths(
            bitcoind = File("/usr/bin/bitcoind"),
            bitcoinCli = File("/usr/bin/bitcoin-cli"),
        )
        val command = RegtestBitcoindCommands.bitcoind(
            paths = paths,
            config = RegtestBitcoindLaunchConfig(
                datadir = datadir,
                rpcPort = 18443,
                p2pPort = 18444,
            ),
        )

        assertEquals("bitcoind", command.commandName)
        assertContains(command.arguments, "-regtest")
        assertContains(command.arguments, "-datadir=${datadir.absolutePath}")
        assertContains(command.arguments, "-server=1")
        assertContains(command.arguments, "-listen=0")
        assertContains(command.arguments, "-dnsseed=0")
        assertContains(command.arguments, "-fixedseeds=0")
        assertContains(command.arguments, "-rpcbind=127.0.0.1")
        assertContains(command.arguments, "-rpcallowip=127.0.0.1")
        assertContains(command.arguments, "-rpcport=18443")
        assertContains(command.arguments, "-port=18444")
        assertFalse(command.arguments.any { it.contains("mainnet", ignoreCase = true) })
        assertFalse(command.arguments.any { it == "-testnet" || it.startsWith("-testnet=") })
    }

    @Test
    fun bitcoinCliCommandUsesRegtestAndTempDatadir() {
        val datadir = File("/tmp/skald-regtest-cli-test")
        val paths = RegtestBinaryPaths(
            bitcoind = File("/usr/bin/bitcoind"),
            bitcoinCli = File("/usr/bin/bitcoin-cli"),
        )
        val command = RegtestBitcoindCommands.bitcoinCli(
            paths = paths,
            config = RegtestBitcoindLaunchConfig(
                datadir = datadir,
                rpcPort = 18443,
                p2pPort = 18444,
            ),
            commandArguments = listOf("getblockchaininfo"),
        )

        assertEquals("bitcoin-cli", command.commandName)
        assertContains(command.arguments, "-regtest")
        assertContains(command.arguments, "-datadir=${datadir.absolutePath}")
        assertContains(command.arguments, "-rpcport=18443")
        assertContains(command.arguments, "getblockchaininfo")
    }

    @Test
    fun commandDisplayRedactsTemporaryDatadir() {
        val datadir = "/tmp/skald-regtest-display-test"
        val command = RegtestHarnessCommand(
            executable = "/usr/bin/bitcoin-cli",
            arguments = listOf("-regtest", "-datadir=$datadir", "getblockchaininfo"),
        )
        val display = command.safeDisplay(redactions = listOf(datadir))

        assertTrue(display.startsWith("bitcoin-cli "))
        assertFalse(display.contains(datadir))
        assertTrue(display.contains("<REDACTED>"))
    }

    @Test
    fun integrationSmokeIsDisabledUnlessExplicitlyRequested() {
        val result = RegtestBitcoindSmoke.runFromEnvironment(environment = emptyMap())

        assertEquals(RegtestHarnessState.Disabled, result.state)
        assertContains(result.checks, RegtestHarnessCheck.IntegrationOptIn)
        assertNotNull(result.error)
        assertTrue(result.error.safeDetail.contains(RegtestIntegrationPolicy.EnvironmentVariable))
    }

    @Test
    fun processExecutionAndBinaryNamesStayInDesktopRegtestHarnessOnly() {
        val root = repositoryRoot()
        val sourceRoot = File(root, "composeApp/src")
        val patterns = listOf(
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bServerSocket\b"""),
            Regex("""\bbitcoind\b"""),
            Regex("""\bbitcoin-cli\b"""),
        )
        val offenders = sourceRoot
            .walkTopDown()
            .filter { it.isFile && it.extension == "kt" }
            .filter { file -> patterns.any { pattern -> pattern.containsMatchIn(file.readText()) } }
            .map { it.relativeTo(root).invariantSeparatorsPath }
            .filterNot { path -> path.startsWith("composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/regtest/") }
            .toList()

        assertTrue(offenders.isEmpty(), "Regtest process harness leaked outside desktop test code: $offenders")
    }

    @Test
    fun directBdkImportsAreNotAddedToRegtestHarness() {
        val root = repositoryRoot()
        val regtestRoot = File(root, "composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/regtest")
        val directBdkImport = Regex("""import\s+org\.bitcoindevkit""")
        val offenders = regtestRoot
            .walkTopDown()
            .filter { it.isFile && it.extension == "kt" }
            .filter { directBdkImport.containsMatchIn(it.readText()) }
            .map { it.relativeTo(root).invariantSeparatorsPath }
            .toList()

        assertTrue(offenders.isEmpty(), "Regtest harness must not import BDK wallet APIs: $offenders")
    }

    @Test
    fun optInRegtestLifecycleRunsOrReportsUnavailable() {
        val environment = System.getenv()
        if (RegtestIntegrationPolicy.fromEnvironment(environment).enabled.not()) {
            val result = RegtestBitcoindSmoke.runFromEnvironment(environment)

            assertEquals(RegtestHarnessState.Disabled, result.state)
            return
        }

        val result = RegtestBitcoindSmoke.runFromEnvironment(environment)
        if (result.state == RegtestHarnessState.Unavailable) {
            assertNotNull(result.error)
            assertTrue(result.error.safeDetail.contains("bitcoind") || result.error.safeDetail.contains("bitcoin-cli"))
            return
        }

        assertEquals(
            RegtestHarnessState.Completed,
            result.state,
            result.error?.safeDetail ?: result.checks.joinToString { it.label },
        )
        assertContains(result.checks, RegtestHarnessCheck.IntegrationOptIn)
        assertContains(result.checks, RegtestHarnessCheck.BitcoindDiscovered)
        assertContains(result.checks, RegtestHarnessCheck.BitcoinCliDiscovered)
        assertContains(result.checks, RegtestHarnessCheck.NodeReady)
        assertContains(result.checks, RegtestHarnessCheck.BlockchainInfoRead)
        assertContains(result.checks, RegtestHarnessCheck.MiningWalletCreated)
        assertContains(result.checks, RegtestHarnessCheck.BlockGenerated)
        assertContains(result.checks, RegtestHarnessCheck.CleanShutdown)
        assertContains(result.checks, RegtestHarnessCheck.TemporaryDatadirCleaned)
        assertContains(result.capabilities, RegtestHarnessCapability.NoSkaldWalletStorage)
    }

    private fun executableFile(
        directory: File,
        name: String,
    ): File =
        File(directory, name).apply {
            writeText("#!/bin/sh\nexit 0\n")
            setExecutable(true)
        }

    private fun repositoryRoot(): File =
        generateSequence(File(".").absoluteFile) { file -> file.parentFile }
            .first { candidate -> File(candidate, "settings.gradle.kts").exists() }
}
