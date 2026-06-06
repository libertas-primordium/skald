package com.libertasprimordium.skald

import java.io.File
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ReceiveAddressPolicySourceGuardTest {
    @Test
    fun receiveAddressPolicyFilesDoNotImportBdkOrCallBdkAddressApis() {
        val root = repositoryRoot()
        val files = receivePolicyFiles(root)
        val forbiddenPatterns = listOf(
            Regex("""import\s+org\.bitcoindevkit"""),
            Regex("""\bpeekAddress\b"""),
            Regex("""\bnextUnusedAddress\b"""),
            Regex("""\brevealNextAddress\b"""),
            Regex("""\brevealAddressesTo\b"""),
            Regex("""\bAddressInfo\b"""),
            Regex("""\bWallet\("""),
            Regex("""\bMnemonic\."""),
            Regex("""\bDescriptorSecretKey\("""),
        )
        val offenders = files
            .filter { file -> forbiddenPatterns.any { it.containsMatchIn(file.readText()) } }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(offenders.isEmpty(), "Receive policy must remain BDK-free: $offenders")
    }

    @Test
    fun receiveAddressPolicyDoesNotAddPersistenceCodeOrRealAddressFixtures() {
        val root = repositoryRoot()
        val combined = receivePolicyFiles(root).joinToString("\n") { it.readText() }
        val hardcodedAddress = Regex("""\b(?:bc1|tb1|bcrt1)[a-z0-9]{20,}\b""", RegexOption.IGNORE_CASE)
        val persistencePatterns = listOf(
            Regex("""SettingsStorageKey"""),
            Regex("""writeText\("""),
            Regex("""readText\("""),
            Regex("""Persister\.newSqlite"""),
            Regex("""File\("""),
        )

        assertFalse(hardcodedAddress.containsMatchIn(combined))
        assertTrue(
            persistencePatterns.none { it.containsMatchIn(combined) },
            "Receive policy must not add address persistence in this pass.",
        )
    }

    private fun receivePolicyFiles(root: File): List<File> =
        File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/domain/onchain")
            .walkTopDown()
            .filter { it.isFile && it.name.startsWith("ReceiveAddress") && it.extension == "kt" }
            .toList()

    private fun repositoryRoot(): File =
        generateSequence(File(".").absoluteFile) { file -> file.parentFile }
            .first { candidate -> File(candidate, "settings.gradle.kts").exists() }
}
