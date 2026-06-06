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

class RegtestElectrumIndexerHarnessTest {
    @Test
    fun electrumIndexerDiscoveryReportsUnavailableWhenElectrsIsMissing() {
        val availability = RegtestElectrumIndexerBinaryDiscovery.discover(
            environment = emptyMap(),
            pathValue = "",
        )

        val unavailable = assertIs<RegtestElectrumIndexerAvailability.Unavailable>(availability)
        assertContains(unavailable.missingBinaries, "electrs")
        assertTrue(unavailable.safeDetail.contains(RegtestElectrumIndexerBinaryDiscovery.ElectrsEnvironmentVariable))
    }

    @Test
    fun electrumIndexerDiscoveryUsesExplicitEnvironmentOverride() {
        val tempDirectory = Files.createTempDirectory("skald-electrum-indexer-discovery-test-").toFile()
        val electrs = executableFile(tempDirectory, "electrs")

        try {
            val availability = RegtestElectrumIndexerBinaryDiscovery.discover(
                environment = mapOf(
                    RegtestElectrumIndexerBinaryDiscovery.ElectrsEnvironmentVariable to electrs.absolutePath,
                ),
                pathValue = "",
            )

            val available = assertIs<RegtestElectrumIndexerAvailability.Available>(availability)
            assertEquals(electrs.absolutePath, available.paths.electrs.absolutePath)
        } finally {
            tempDirectory.deleteRecursively()
        }
    }

    @Test
    fun electrsCommandUsesRegtestTemporaryDirectoriesAndLocalhostOnlyEndpoint() {
        val bitcoindDatadir = File("/tmp/skald-regtest-bitcoind-electrum-command-test")
        val indexerDatadir = File("/tmp/skald-regtest-electrum-indexer-command-test")
        val command = RegtestElectrumIndexerCommands.electrs(
            paths = RegtestElectrumIndexerBinaryPaths(electrs = File("/usr/bin/electrs")),
            config = RegtestElectrumIndexerLaunchConfig(
                bitcoind = RegtestBitcoindLaunchConfig(
                    datadir = bitcoindDatadir,
                    rpcPort = 18443,
                    p2pPort = 18444,
                ),
                indexerDatadir = indexerDatadir,
                electrumPort = 50001,
            ),
        )

        assertEquals("electrs", command.commandName)
        assertContains(command.arguments, "--network=regtest")
        assertContains(command.arguments, "--daemon-dir=${bitcoindDatadir.absolutePath}")
        assertContains(command.arguments, "--daemon-rpc-addr=127.0.0.1:18443")
        assertContains(command.arguments, "--db-dir=${indexerDatadir.absolutePath}")
        assertContains(command.arguments, "--electrum-rpc-addr=127.0.0.1:50001")
        assertFalse(command.arguments.any { it.contains("mainnet", ignoreCase = true) })
        assertFalse(command.arguments.any { it.contains("testnet", ignoreCase = true) })
        assertFalse(command.arguments.any { it.contains("signet", ignoreCase = true) })
        assertFalse(command.arguments.any { it.contains("://") })
    }

    @Test
    fun electrsCommandDisplayRedactsTemporaryDirectories() {
        val bitcoindDatadir = "/tmp/skald-regtest-bitcoind-electrum-display-test"
        val indexerDatadir = "/tmp/skald-regtest-electrum-indexer-display-test"
        val command = RegtestHarnessCommand(
            executable = "/usr/bin/electrs",
            arguments = listOf(
                "--network=regtest",
                "--daemon-dir=$bitcoindDatadir",
                "--db-dir=$indexerDatadir",
                "--electrum-rpc-addr=127.0.0.1:50001",
            ),
        )
        val display = command.safeDisplay(redactions = listOf(bitcoindDatadir, indexerDatadir))

        assertTrue(display.startsWith("electrs "))
        assertFalse(display.contains(bitcoindDatadir))
        assertFalse(display.contains(indexerDatadir))
        assertTrue(display.contains("<REDACTED>"))
    }

    @Test
    fun localElectrumHarnessIsDisabledUnlessExplicitlyRequested() {
        val result = RegtestElectrumIndexerSmoke.runFromEnvironment(environment = emptyMap())

        assertEquals(RegtestElectrumIndexerState.Disabled, result.state)
        assertContains(result.checks, RegtestElectrumIndexerCheck.IntegrationOptIn)
        assertNotNull(result.error)
        assertTrue(result.error.safeDetail.contains(RegtestElectrumIndexerIntegrationPolicy.EnvironmentVariable))
        assertFalse(result.usesPublicEndpoint)
        assertFalse(result.usesProductionBackend)
        assertFalse(result.mainnetEnabled)
    }

    @Test
    fun optInLocalElectrumHarnessRunsOrReportsUnavailable() {
        val environment = System.getenv()
        if (!RegtestElectrumIndexerIntegrationPolicy.fromEnvironment(environment).enabled) {
            val result = RegtestElectrumIndexerSmoke.runFromEnvironment(environment)

            assertEquals(RegtestElectrumIndexerState.Disabled, result.state)
            return
        }

        val result = RegtestElectrumIndexerSmoke.runFromEnvironment(environment)
        if (result.state == RegtestElectrumIndexerState.Unavailable) {
            assertNotNull(result.error)
            assertTrue(
                result.error.safeDetail.contains("electrs") ||
                    result.error.safeDetail.contains("bitcoind") ||
                    result.error.safeDetail.contains("bitcoin-cli"),
            )
            assertFalse(result.usesPublicEndpoint)
            assertFalse(result.usesProductionBackend)
            assertFalse(result.mainnetEnabled)
            assertSanitized(result.toString())
            return
        }

        assertEquals(
            RegtestElectrumIndexerState.Completed,
            result.state,
            result.error?.safeDetail ?: result.checks.joinToString { it.label },
        )
        assertTrue(result.completed)
        assertContains(result.checks, RegtestElectrumIndexerCheck.IntegrationOptIn)
        assertContains(result.checks, RegtestElectrumIndexerCheck.BitcoindDiscovered)
        assertContains(result.checks, RegtestElectrumIndexerCheck.BitcoinCliDiscovered)
        assertContains(result.checks, RegtestElectrumIndexerCheck.ElectrumIndexerDiscovered)
        assertContains(result.checks, RegtestElectrumIndexerCheck.NodeReady)
        assertContains(result.checks, RegtestElectrumIndexerCheck.ElectrumIndexerReady)
        assertContains(result.checks, RegtestElectrumIndexerCheck.BlockGenerated)
        assertContains(result.checks, RegtestElectrumIndexerCheck.CleanShutdown)
        assertContains(result.checks, RegtestElectrumIndexerCheck.TemporaryDatadirsCleaned)
        assertContains(result.capabilities, RegtestElectrumIndexerCapability.LocalRegtestOnly)
        assertContains(result.capabilities, RegtestElectrumIndexerCapability.LocalhostOnly)
        assertContains(result.capabilities, RegtestElectrumIndexerCapability.NoPublicEndpoint)
        assertContains(result.capabilities, RegtestElectrumIndexerCapability.NoProductionBackend)
        assertFalse(result.usesPublicEndpoint)
        assertFalse(result.usesProductionBackend)
        assertFalse(result.mainnetEnabled)
        assertSanitized(result.toString())
    }

    @Test
    fun electrumProcessExecutionAndBinaryNamesStayInDesktopRegtestHarnessOnly() {
        val root = repositoryRoot()
        val sourceRoot = File(root, "composeApp/src")
        val patterns = listOf(
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bServerSocket\b"""),
            Regex("""\bSocket\b"""),
            Regex("""\bbitcoind\b"""),
            Regex("""\bbitcoin-cli\b"""),
            Regex("""\belectrs\b"""),
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
    fun electrumHarnessDoesNotImportBdkOrUseWalletScanApis() {
        val root = repositoryRoot()
        val harness = File(root, HarnessFile).readText()
        val forbiddenPatterns = listOf(
            Regex("""import\s+org\.bitcoindevkit"""),
            Regex("""\bMnemonic\b"""),
            Regex("""\bDescriptor\b"""),
            Regex("""\bWallet\("""),
            Regex("""\bstartFullScan\b"""),
            Regex("""\bstartSync"""),
            Regex("""\bapplyUpdate\b"""),
            Regex("""\blistUnspent\b"""),
            Regex("""\bsign\("""),
            Regex("""\bbroadcast\b"""),
        )

        assertTrue(
            forbiddenPatterns.none { it.containsMatchIn(harness) },
            "Local Electrum harness must not import BDK or create wallet/sync/sign behavior.",
        )
    }

    @Test
    fun localElectrumResultModelsAreSanitized() {
        val result = RegtestElectrumIndexerResult(
            state = RegtestElectrumIndexerState.Unavailable,
            checks = listOf(RegtestElectrumIndexerCheck.IntegrationOptIn),
            capabilities = setOf(RegtestElectrumIndexerCapability.BinaryDiscovery),
            error = RegtestElectrumIndexerError(
                code = "LOCAL_ELECTRUM_REGTEST_BINARIES_UNAVAILABLE",
                safeDetail = "Missing local regtest binaries: electrs.",
            ),
        )

        assertFalse(result.usesPublicEndpoint)
        assertFalse(result.usesProductionBackend)
        assertFalse(result.mainnetEnabled)
        assertSanitized(result.toString())
    }

    private fun executableFile(
        directory: File,
        name: String,
    ): File =
        File(directory, name).apply {
            writeText("#!/bin/sh\nexit 0\n")
            setExecutable(true)
        }

    private fun assertSanitized(value: String) {
        val forbiddenPatterns = listOf(
            Regex("""(?i)\bxprv[A-Za-z0-9]+"""),
            Regex("""(?i)\bxpub[A-Za-z0-9]+"""),
            Regex("""(?i)\btprv[A-Za-z0-9]+"""),
            Regex("""(?i)\btpub[A-Za-z0-9]+"""),
            Regex("""(?i)\bnsec1[A-Za-z0-9]+"""),
            Regex("""(?i)\bnpub1[A-Za-z0-9]+"""),
            Regex("""(?i)\bpsbt[A-Za-z0-9+/=]+"""),
            Regex("""(?i)\bbcrt1[A-Za-z0-9]+"""),
            Regex("""(?i)\btc1[A-Za-z0-9]+"""),
            Regex("""(?i)\btb1[A-Za-z0-9]+"""),
            Regex("""\b[0-9a-fA-F]{64}\b"""),
            Regex("""(?i)(rpcpassword|rpcauth|cookie)=\S+"""),
        )
        forbiddenPatterns.forEach { pattern ->
            assertFalse(pattern.containsMatchIn(value), "Result exposed wallet-like material matching ${pattern.pattern}")
        }
    }

    private fun repositoryRoot(): File =
        generateSequence(File(".").absoluteFile) { file -> file.parentFile }
            .first { candidate -> File(candidate, "settings.gradle.kts").exists() }

    private companion object {
        const val HarnessFile =
            "composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/regtest/RegtestElectrumIndexerHarness.kt"
    }
}
