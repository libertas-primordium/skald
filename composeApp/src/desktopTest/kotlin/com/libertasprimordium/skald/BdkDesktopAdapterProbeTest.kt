package com.libertasprimordium.skald

import com.libertasprimordium.skald.domain.core.NetworkEnvironment
import com.libertasprimordium.skald.domain.onchain.bdk.BdkAdapterPlatform
import com.libertasprimordium.skald.domain.onchain.bdk.BdkAdapterProbe
import com.libertasprimordium.skald.domain.onchain.bdk.BdkAdapterProbeState
import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BdkDesktopAdapterProbeTest {
    @Test
    fun desktopProbeLinksBdkJvmWithoutWalletOperations() {
        val result = BdkAdapterProbe.run()

        assertEquals(BdkAdapterPlatform.DesktopJvm, result.platform)
        assertEquals(BdkAdapterProbeState.Linked, result.state)
        assertFalse(result.walletOperationsEnabled)
        assertFalse(result.mainnetEnabled)
        assertEquals(
            listOf(
                NetworkEnvironment.Regtest,
                NetworkEnvironment.Signet,
                NetworkEnvironment.Testnet,
                NetworkEnvironment.Testnet4,
            ),
            result.networkProbes.map { it.skaldNetwork },
        )
    }

    @Test
    fun directBdkImportsStayInsidePlatformAdapterFiles() {
        val root = repositoryRoot()
        val sourceRoot = File(root, "composeApp/src")
        val directBdkImport = Regex("""import\s+org\.bitcoindevkit""")
        val offenders = sourceRoot
            .walkTopDown()
            .filter { it.isFile && it.extension == "kt" }
            .filter { file -> directBdkImport.containsMatchIn(file.readText()) }
            .map { it.relativeTo(root).invariantSeparatorsPath }
            .filterNot { path ->
                path == "composeApp/src/androidMain/kotlin/com/libertasprimordium/skald/domain/onchain/bdk/AndroidBdkAdapterProbe.kt" ||
                    path == "composeApp/src/desktopMain/kotlin/com/libertasprimordium/skald/domain/onchain/bdk/DesktopBdkAdapterProbe.kt" ||
                    path.startsWith("composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/bdk/")
            }
            .toList()

        assertTrue(offenders.isEmpty(), "Unexpected direct BDK imports: $offenders")
    }

    private fun repositoryRoot(): File =
        generateSequence(File(".").absoluteFile) { file -> file.parentFile }
            .first { candidate -> File(candidate, "settings.gradle.kts").exists() }
}
