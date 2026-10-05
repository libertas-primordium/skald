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
        assertContains(classNames, "com.google.crypto.tink.aead.internal.InsecureNonceXChaCha20Poly1305")
        assertContains(classNames, "org.bouncycastle.crypto.generators.Argon2BytesGenerator")
        assertContains(classNames, "org.bouncycastle.crypto.modes.ChaCha20Poly1305")
        classNames.forEach { className ->
            Class.forName(className)
        }
        assertFalse(DesktopVaultCryptoDependencyCompileProbe.note.contains("storage enabled"))
    }

    @Test
    fun desktopClasspathDoesNotCarryRejectedLazysodiumCandidate() {
        val classpathEntries = System.getProperty("java.class.path")
            .split(File.pathSeparator)

        assertFalse(
            classpathEntries.any { it.endsWith("lazysodium-java-5.2.0.jar") },
            "Rejected Lazysodium candidate must not remain on the desktop runtime classpath.",
        )
    }

    @Test
    fun dependencyProbeSourcesDoNotImplementVaultStorage() {
        val root = repositoryRoot()
        val files = listOf(
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/VaultCryptoDependencyProbe.kt"),
            File(root, "composeApp/src/androidMain/kotlin/com/libertasprimordium/skald/security/AndroidVaultCryptoDependencyCompileProbe.kt"),
            File(root, "composeApp/src/androidInstrumentedTest/kotlin/com/libertasprimordium/skald/VaultCryptoAndroidKatValidationTest.kt"),
            File(root, "composeApp/src/desktopMain/kotlin/com/libertasprimordium/skald/security/DesktopVaultCryptoDependencyCompileProbe.kt"),
            File(root, "composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/VaultCryptoKnownAnswerVectorTest.kt"),
        )
        val reviewedImports = mapOf(
            "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/VaultCryptoDependencyProbe.kt" to emptySet(),
            "composeApp/src/androidMain/kotlin/com/libertasprimordium/skald/security/AndroidVaultCryptoDependencyCompileProbe.kt" to setOf("com.google.crypto.tink.aead.XChaCha20Poly1305Key", "com.google.crypto.tink.aead.internal.InsecureNonceXChaCha20Poly1305", "org.bouncycastle.crypto.generators.Argon2BytesGenerator", "org.bouncycastle.crypto.modes.ChaCha20Poly1305"),
            "composeApp/src/androidInstrumentedTest/kotlin/com/libertasprimordium/skald/VaultCryptoAndroidKatValidationTest.kt" to setOf("androidx.test.ext.junit.runners.AndroidJUnit4", "com.google.crypto.tink.aead.internal.InsecureNonceXChaCha20Poly1305", "org.bouncycastle.crypto.generators.Argon2BytesGenerator", "org.bouncycastle.crypto.params.Argon2Parameters", "org.junit.Assert.assertArrayEquals", "org.junit.Test", "org.junit.runner.RunWith"),
            "composeApp/src/desktopMain/kotlin/com/libertasprimordium/skald/security/DesktopVaultCryptoDependencyCompileProbe.kt" to setOf("com.google.crypto.tink.aead.XChaCha20Poly1305Key", "com.google.crypto.tink.aead.internal.InsecureNonceXChaCha20Poly1305", "org.bouncycastle.crypto.generators.Argon2BytesGenerator", "org.bouncycastle.crypto.modes.ChaCha20Poly1305"),
            "composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/VaultCryptoKnownAnswerVectorTest.kt" to setOf("com.google.crypto.tink.aead.internal.InsecureNonceXChaCha20Poly1305", "org.bouncycastle.crypto.generators.Argon2BytesGenerator", "org.bouncycastle.crypto.params.Argon2Parameters", "kotlin.test.Test", "kotlin.test.assertContentEquals"),
        )
        files.forEach { file ->
            val source = file.readText()
            val path = file.relativeTo(root).invariantSeparatorsPath
            val actualImports = Regex("""(?m)^import ([^\n]+)$""").findAll(source).map { it.groupValues[1] }.toSet()
            kotlin.test.assertEquals(reviewedImports.getValue(path), actualImports, "Unreviewed probe or KAT import in $path")
        }
        val forbiddenPatterns = listOf(
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
            Regex("""\bPath\("""),
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
