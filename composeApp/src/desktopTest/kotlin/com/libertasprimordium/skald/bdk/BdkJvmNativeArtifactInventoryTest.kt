package com.libertasprimordium.skald.bdk

import com.libertasprimordium.skald.domain.onchain.bdk.BdkAdapterVersion
import java.io.File
import java.util.zip.ZipFile
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BdkJvmNativeArtifactInventoryTest {
    @Test
    fun pinnedBdkJvmArtifactContainsLinuxNativeBinding() {
        val inventory = BdkJvmNativeArtifactInspector.currentClasspathInventory()

        assertEquals("bdk-jvm-${BdkAdapterVersion.Pinned.version}.jar", inventory.artifactName)
        assertContains(inventory.nativeEntries, "linux-x86-64/libbdkffi.so")
        assertContains(inventory.nativeEntries, "darwin-aarch64/libbdkffi.dylib")
        assertContains(inventory.nativeEntries, "darwin-x86-64/libbdkffi.dylib")
        assertContains(inventory.nativeEntries, "win32-x86-64/bdkffi.dll")
        assertFalse(inventory.nativeEntries.any { it.startsWith("jni/") })
        assertTrue(inventory.nativeEntries.all { it.contains("bdkffi") })
    }

    @Test
    fun artifactInventoryDoesNotUseBdkWalletApis() {
        val inventoryFile = File(repositoryRoot(), InventoryFile).readText()
        val forbiddenRuntimeCalls = listOf(
            Regex("""import\s+org\.bitcoindevkit"""),
            Regex("""\bMnemonic\b"""),
            Regex("""\bDescriptor\b"""),
            Regex("""\bWallet\b"""),
            Regex("""\bAddress\b"""),
            Regex("""\bPsbt\b"""),
            Regex("""\bsign\("""),
            Regex("""\bbroadcast\b"""),
            Regex("""\bsync\b"""),
        )

        assertTrue(
            forbiddenRuntimeCalls.none { it.containsMatchIn(inventoryFile) },
            "BDK artifact inventory must inspect archive entries only.",
        )
    }

    private fun repositoryRoot(): File =
        generateSequence(File(".").absoluteFile) { file -> file.parentFile }
            .first { candidate -> File(candidate, "settings.gradle.kts").exists() }

    private companion object {
        const val InventoryFile =
            "composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/bdk/BdkJvmNativeArtifactInventoryTest.kt"
    }
}

private data class BdkJvmNativeArtifactInventory(
    val artifactName: String,
    val nativeEntries: List<String>,
)

private object BdkJvmNativeArtifactInspector {
    fun currentClasspathInventory(): BdkJvmNativeArtifactInventory {
        val artifact = System.getProperty("java.class.path")
            .split(File.pathSeparator)
            .map(::File)
            .filter { it.name == "bdk-jvm-${BdkAdapterVersion.Pinned.version}.jar" }
            .distinctBy { it.absolutePath }
            .singleOrNull()
            ?: error("Expected one pinned BDK JVM artifact on the desktop test classpath.")

        return BdkJvmNativeArtifactInventory(
            artifactName = artifact.name,
            nativeEntries = nativeEntries(artifact),
        )
    }

    private fun nativeEntries(artifact: File): List<String> =
        ZipFile(artifact).use { zip ->
            zip.entries()
                .asSequence()
                .map { it.name }
                .filter { entry ->
                    entry.endsWith(".so") ||
                        entry.endsWith(".dylib") ||
                        entry.endsWith(".dll")
                }
                .sorted()
                .toList()
        }
}
