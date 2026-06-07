package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DesktopVaultCryptoDependencyCompileProbe
import java.io.File
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class VaultCryptoDependencyArtifactProbeTest {
    @Test
    fun desktopClasspathContainsSelectedCandidateApis() {
        val classNames = DesktopVaultCryptoDependencyCompileProbe.availableApiClassNames

        assertContains(classNames, "com.google.crypto.tink.aead.XChaCha20Poly1305Key")
        assertContains(classNames, "org.bouncycastle.crypto.generators.Argon2BytesGenerator")
        assertContains(classNames, "org.bouncycastle.crypto.modes.ChaCha20Poly1305")
        classNames.forEach { className ->
            Class.forName(className)
        }
        assertFalse(DesktopVaultCryptoDependencyCompileProbe.note.contains("storage enabled"))
    }

    @Test
    fun dependencyProbeSourcesDoNotImplementVaultStorage() {
        val root = repositoryRoot()
        val files = listOf(
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/VaultCryptoDependencyProbe.kt"),
            File(root, "composeApp/src/androidMain/kotlin/com/libertasprimordium/skald/security/AndroidVaultCryptoDependencyCompileProbe.kt"),
            File(root, "composeApp/src/desktopMain/kotlin/com/libertasprimordium/skald/security/DesktopVaultCryptoDependencyCompileProbe.kt"),
        )
        val forbiddenPatterns = listOf(
            Regex("""import\s+org\.bitcoindevkit"""),
            Regex("""\bSecretPayload\("""),
            Regex("""\bSecureMetadataPayload\("""),
            Regex("""\bSecureRandom\b"""),
            Regex("""\bCipher\("""),
            Regex("""\bKeyGenerator\b"""),
            Regex("""\bSecretKeySpec\b"""),
            Regex("""\bMac\("""),
            Regex("""\bProcessBuilder\b"""),
            Regex("""\bSocket\("""),
            Regex("""\bServerSocket\("""),
            Regex("""\bSettingsStorageKey\b"""),
            Regex("""\bSharedPreferences\b"""),
            Regex("""\bFile\("""),
            Regex("""\bwriteText\("""),
            Regex("""\breadText\("""),
            Regex("""\bjava\.io\b"""),
        )
        val offenders = files
            .filter { file -> forbiddenPatterns.any { it.containsMatchIn(file.readText()) } }
            .map { it.relativeTo(root).invariantSeparatorsPath }

        assertTrue(offenders.isEmpty(), "Vault crypto dependency probes must not implement storage or client APIs: $offenders")
    }

    private fun repositoryRoot(): File =
        generateSequence(File(".").absoluteFile) { file -> file.parentFile }
            .first { candidate -> File(candidate, "settings.gradle.kts").exists() }
}
