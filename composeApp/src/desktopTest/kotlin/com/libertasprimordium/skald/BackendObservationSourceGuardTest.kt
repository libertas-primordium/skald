package com.libertasprimordium.skald

import java.io.File
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BackendObservationSourceGuardTest {
    @Test
    fun backendObservationModelsDoNotImportBdkOrBackendClientApis() {
        val root = repositoryRoot()
        val files = backendObservationFiles(root)
        val forbiddenPatterns = listOf(
            Regex("""import\s+org\.bitcoindevkit"""),
            Regex("""\bElectrumClient\b"""),
            Regex("""\bEsploraClient\b"""),
            Regex("""\bCbfClient\b"""),
            Regex("""\bstartFullScan\b"""),
            Regex("""\bstartSyncWithRevealedSpks\b"""),
            Regex("""\bapplyUpdate\b"""),
            Regex("""\blistUnspent\b"""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
        )
        val offenders = files
            .filter { file -> forbiddenPatterns.any { it.containsMatchIn(file.readText()) } }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(offenders.isEmpty(), "Backend observation models must remain BDK/backend-process free: $offenders")
    }

    @Test
    fun backendObservationModelsDoNotAddPersistenceOrRealWalletFixtures() {
        val root = repositoryRoot()
        val combined = backendObservationFiles(root).joinToString("\n") { it.readText() }
        val hardcodedAddress = Regex("""\b(?:bc1|tb1|bcrt1)[a-z0-9]{20,}\b""", RegexOption.IGNORE_CASE)
        val hardcodedTxid = Regex("""\b[0-9a-fA-F]{64}\b""")
        val secretLikePatterns = listOf(
            Regex("""(?i)\bxprv[A-Za-z0-9]+"""),
            Regex("""(?i)\btprv[A-Za-z0-9]+"""),
            Regex("""(?i)\bWIF\b"""),
            Regex("""(?i)\bnsec1[A-Za-z0-9]+"""),
            Regex("""(?i)\bmnemonic\s+words\b"""),
        )
        val persistencePatterns = listOf(
            Regex("""SettingsStorageKey"""),
            Regex("""writeText\("""),
            Regex("""readText\("""),
            Regex("""Persister\.newSqlite"""),
            Regex("""File\("""),
        )

        assertFalse(hardcodedAddress.containsMatchIn(combined))
        assertFalse(hardcodedTxid.containsMatchIn(combined))
        secretLikePatterns.forEach { pattern ->
            assertFalse(pattern.containsMatchIn(combined), "Backend observation model contains secret-like fixture text.")
        }
        assertTrue(
            persistencePatterns.none { it.containsMatchIn(combined) },
            "Backend observation models must not add persistence in this pass.",
        )
    }

    @Test
    fun productionSourceSetsDoNotUseBdkScanApisForBackendObservation() {
        val root = repositoryRoot()
        val productionRoots = listOf(
            File(root, "composeApp/src/commonMain"),
            File(root, "composeApp/src/androidMain"),
            File(root, "composeApp/src/desktopMain"),
        )
        val scanApiPatterns = listOf(
            Regex("""\bstartFullScan\b"""),
            Regex("""\bstartSyncWithRevealedSpks\b"""),
            Regex("""\bapplyUpdate\b"""),
            Regex("""\blistUnspent\b"""),
            Regex("""\bElectrumClient\b"""),
            Regex("""\bEsploraClient\b"""),
            Regex("""\bCbfClient\b"""),
        )
        val offenders = productionRoots
            .asSequence()
            .flatMap { it.walkTopDown().asSequence() }
            .filter { it.isFile && it.extension == "kt" }
            .filter { file -> scanApiPatterns.any { it.containsMatchIn(file.readText()) } }
            .map { it.relativeTo(root).invariantSeparatorsPath }
            .toList()

        assertTrue(offenders.isEmpty(), "BDK scan API usage leaked into production source sets: $offenders")
    }

    private fun backendObservationFiles(root: File): List<File> =
        File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/domain/onchain")
            .walkTopDown()
            .filter { it.isFile && it.name.startsWith("BackendObservation") && it.extension == "kt" }
            .toList()

    private fun repositoryRoot(): File =
        generateSequence(File(".").absoluteFile) { file -> file.parentFile }
            .first { candidate -> File(candidate, "settings.gradle.kts").exists() }
}
