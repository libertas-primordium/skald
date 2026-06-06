package com.libertasprimordium.skald.bdk

import java.io.File
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class BdkRegtestSeedWalletValidationTest {
    @Test
    fun seedBackedValidationIsDisabledUnlessExplicitlyRequested() {
        val result = BdkRegtestSeedWalletValidation.runFromEnvironment(environment = emptyMap())

        assertEquals(BdkRegtestWalletValidationState.Disabled, result.state)
        assertFalse(result.completed)
        assertNull(result.identity)
        assertNotNull(result.error)
        assertTrue(result.error.safeDetail.contains(BdkRegtestWalletValidationPolicy.EnvironmentVariable))
        assertFalse(result.exposesWalletMaterial)
    }

    @Test
    fun mainnetValidationIsRejectedBeforeWalletMaterialExists() {
        val result = BdkRegtestSeedWalletValidation.run(
            BdkRegtestWalletValidationRequest(
                enabled = true,
                network = BdkRegtestWalletValidationNetwork.MainnetDisabled,
            ),
        )

        assertEquals(BdkRegtestWalletValidationState.RejectedMainnet, result.state)
        assertContains(result.checks, BdkRegtestWalletValidationCheck.ExplicitOptIn)
        assertNull(result.identity)
        assertNotNull(result.error)
        assertTrue(result.error.safeDetail.contains("Mainnet is disabled"))
        assertFalse(result.exposesWalletMaterial)
    }

    @Test
    fun optInValidationCreatesAndRecoversTestOnlyRegtestWallet() {
        val result = BdkRegtestSeedWalletValidation.runFromEnvironment(System.getenv())
        if (result.state == BdkRegtestWalletValidationState.Disabled) {
            assertFalse(result.completed)
            return
        }
        if (result.state == BdkRegtestWalletValidationState.BlockedByBdkJvmNativeBinding) {
            assertFalse(result.completed)
            assertFalse(result.usesProductionStorage)
            assertFalse(result.exposesWalletMaterial)
            assertContains(result.checks, BdkRegtestWalletValidationCheck.RuntimeEntropyCreated)
            assertContains(result.capabilities, BdkRegtestWalletValidationCapability.RegtestOnly)
            assertContains(result.capabilities, BdkRegtestWalletValidationCapability.InMemoryBdkPersister)
            assertNull(result.identity)
            assertNotNull(result.error)
            assertEquals(
                "BDK_REGTEST_WALLET_VALIDATION_NATIVE_BINDING_UNAVAILABLE",
                result.error.code,
            )
            assertTrue(result.error.safeDetail.contains("BDK JVM native binding linkage"))
            assertSanitized(result.toString())
            return
        }

        assertEquals(
            BdkRegtestWalletValidationState.Completed,
            result.state,
            result.error?.safeDetail ?: result.diagnostic,
        )
        assertTrue(result.completed)
        assertFalse(result.usesProductionStorage)
        assertFalse(result.exposesWalletMaterial)
        assertContains(result.checks, BdkRegtestWalletValidationCheck.RuntimeEntropyCreated)
        assertContains(result.checks, BdkRegtestWalletValidationCheck.RuntimeMnemonicCreated)
        assertContains(result.checks, BdkRegtestWalletValidationCheck.RuntimeDescriptorSecretCreated)
        assertContains(result.checks, BdkRegtestWalletValidationCheck.Bip86DescriptorsCreated)
        assertContains(result.checks, BdkRegtestWalletValidationCheck.InMemoryPersisterCreated)
        assertContains(result.checks, BdkRegtestWalletValidationCheck.BdkWalletCreated)
        assertContains(result.checks, BdkRegtestWalletValidationCheck.BdkWalletRecovered)
        assertContains(result.checks, BdkRegtestWalletValidationCheck.SanitizedIdentityMatched)
        assertContains(result.checks, BdkRegtestWalletValidationCheck.NoProductionStorageUsed)
        assertContains(result.capabilities, BdkRegtestWalletValidationCapability.RegtestOnly)
        assertContains(result.capabilities, BdkRegtestWalletValidationCapability.InMemoryBdkPersister)
        assertContains(result.capabilities, BdkRegtestWalletValidationCapability.NoBackendNetworking)
        assertContains(result.capabilities, BdkRegtestWalletValidationCapability.NoAddressDerivation)
        assertContains(result.capabilities, BdkRegtestWalletValidationCapability.NoSigning)
        assertContains(result.capabilities, BdkRegtestWalletValidationCapability.NoBroadcasting)

        val identity = requireNotNull(result.identity)
        assertEquals(BdkRegtestWalletValidationNetwork.Regtest, identity.network)
        assertTrue(identity.recoveredMatchesCreated)
        assertFalse(identity.descriptorMaterialExposed)
        assertFalse(identity.secretMaterialExposed)
        assertFalse(identity.publicAddressExposed)
        assertEquals("REDACTED_TEST_ONLY_WALLET_IDENTITY", identity.identityDisplay)
        assertSanitized(result.toString())
    }

    @Test
    fun validationResultModelsExposeOnlySkaldOwnedRedactedStatus() {
        val result = BdkRegtestWalletValidationResult(
            state = BdkRegtestWalletValidationState.Completed,
            checks = listOf(BdkRegtestWalletValidationCheck.SanitizedIdentityMatched),
            capabilities = setOf(BdkRegtestWalletValidationCapability.SkaldOwnedResultTypes),
            identity = SanitizedRegtestWalletIdentity(
                network = BdkRegtestWalletValidationNetwork.Regtest,
                recoveredMatchesCreated = true,
                descriptorMaterialExposed = false,
                secretMaterialExposed = false,
                publicAddressExposed = false,
            ),
            error = null,
            diagnostic = "redacted validation result",
        )

        assertFalse(result.exposesWalletMaterial)
        assertSanitized(result.toString())
        assertFalse(result.toString().contains("org.bitcoindevkit"))
    }

    @Test
    fun directBdkImportsStayInsidePlatformProbeOrDesktopValidationFiles() {
        val root = repositoryRoot()
        val sourceRoot = File(root, "composeApp/src")
        val directBdkImport = Regex("""import\s+org\.bitcoindevkit""")
        val offenders = sourceRoot
            .walkTopDown()
            .filter { it.isFile && it.extension == "kt" }
            .filter { file -> directBdkImport.containsMatchIn(file.readText()) }
            .map { it.relativeTo(root).invariantSeparatorsPath }
            .filterNot(::isAllowedBdkImportPath)
            .toList()

        assertTrue(offenders.isEmpty(), "Unexpected direct BDK imports: $offenders")
    }

    @Test
    fun bdkWalletApisStayOutOfProductionSourceSets() {
        val root = repositoryRoot()
        val sourceRoot = File(root, "composeApp/src")
        val walletApiPatterns = listOf(
            Regex("""\bMnemonic\."""),
            Regex("""\bMnemonic\("""),
            Regex("""\bDescriptorSecretKey\("""),
            Regex("""\bDescriptor\.newBip86\b"""),
            Regex("""\bPersister\.newInMemory\b"""),
            Regex("""\bPersister\.newSqlite\b"""),
            Regex("""\bWallet\("""),
            Regex("""\btoStringWithSecret\b"""),
            Regex("""\bsecretBytes\b"""),
        )
        val offenders = sourceRoot
            .walkTopDown()
            .filter { it.isFile && it.extension == "kt" }
            .filter { file -> walletApiPatterns.any { pattern -> pattern.containsMatchIn(file.readText()) } }
            .map { it.relativeTo(root).invariantSeparatorsPath }
            .filterNot { path -> path.startsWith("composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/bdk/") }
            .toList()

        assertTrue(offenders.isEmpty(), "BDK wallet API usage leaked outside desktop validation: $offenders")
    }

    @Test
    fun validationDoesNotDeriveAddressesSyncSignBroadcastOrPersistFiles() {
        val root = repositoryRoot()
        val validation = File(root, ValidationFile).readText()
        val forbiddenRuntimeCalls = listOf(
            Regex("""\bderiveAddress\b"""),
            Regex("""\bnextUnusedAddress\b"""),
            Regex("""\brevealNextAddress\b"""),
            Regex("""\bpeekAddress\b"""),
            Regex("""\bstartFullScan\b"""),
            Regex("""\bstartSync"""),
            Regex("""\bapplyUpdate\b"""),
            Regex("""\bsign\("""),
            Regex("""\bfinalizePsbt\b"""),
            Regex("""\bpersist\("""),
            Regex("""\bnewSqlite\b"""),
            Regex("""\bElectrumClient\b"""),
            Regex("""\bEsploraClient\b"""),
        )

        assertTrue(
            forbiddenRuntimeCalls.none { it.containsMatchIn(validation) },
            "Validation must not derive addresses, sync, sign, broadcast, or use file persistence.",
        )
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
        )
        forbiddenPatterns.forEach { pattern ->
            assertFalse(pattern.containsMatchIn(value), "Result exposed wallet-like material matching ${pattern.pattern}")
        }
    }

    private fun isAllowedBdkImportPath(path: String): Boolean =
        path == "composeApp/src/androidMain/kotlin/com/libertasprimordium/skald/domain/onchain/bdk/AndroidBdkAdapterProbe.kt" ||
            path == "composeApp/src/desktopMain/kotlin/com/libertasprimordium/skald/domain/onchain/bdk/DesktopBdkAdapterProbe.kt" ||
            path.startsWith("composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/bdk/")

    private fun repositoryRoot(): File =
        generateSequence(File(".").absoluteFile) { file -> file.parentFile }
            .first { candidate -> File(candidate, "settings.gradle.kts").exists() }

    private companion object {
        const val ValidationFile =
            "composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/bdk/BdkRegtestSeedWalletValidation.kt"
    }
}
