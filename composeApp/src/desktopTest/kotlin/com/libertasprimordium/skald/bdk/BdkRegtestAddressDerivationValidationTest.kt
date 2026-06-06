package com.libertasprimordium.skald.bdk

import java.io.File
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class BdkRegtestAddressDerivationValidationTest {
    @Test
    fun addressDerivationValidationIsDisabledUnlessExplicitlyRequested() {
        val result = BdkRegtestAddressDerivationValidation.runFromEnvironment(environment = emptyMap())

        assertEquals(BdkAddressDerivationValidationState.Disabled, result.state)
        assertFalse(result.completed)
        assertNull(result.firstAddress)
        assertNull(result.recoveredFirstAddress)
        assertNull(result.secondAddress)
        assertNotNull(result.error)
        assertTrue(result.error.safeDetail.contains(BdkAddressDerivationValidationPolicy.EnvironmentVariable))
        assertFalse(result.exposesSecretMaterial)
        assertFalse(result.producesMainnetAddress)
    }

    @Test
    fun mainnetAddressDerivationIsRejectedBeforeWalletMaterialExists() {
        val result = BdkRegtestAddressDerivationValidation.run(
            BdkAddressDerivationValidationRequest(
                enabled = true,
                network = BdkAddressDerivationValidationNetwork.MainnetDisabled,
            ),
        )

        assertEquals(BdkAddressDerivationValidationState.RejectedMainnet, result.state)
        assertContains(result.checks, BdkAddressDerivationValidationCheck.ExplicitOptIn)
        assertNull(result.firstAddress)
        assertNull(result.recoveredFirstAddress)
        assertNull(result.secondAddress)
        assertNotNull(result.error)
        assertTrue(result.error.safeDetail.contains("Mainnet is disabled"))
        assertFalse(result.exposesSecretMaterial)
        assertFalse(result.producesMainnetAddress)
        assertRedacted(result.toString())
    }

    @Test
    fun optInRegtestDerivationProducesDeterministicReceiveAddress() {
        val result = BdkRegtestAddressDerivationValidation.runFromEnvironment(System.getenv())
        if (result.state == BdkAddressDerivationValidationState.Disabled) {
            assertFalse(result.completed)
            return
        }
        if (result.state == BdkAddressDerivationValidationState.BlockedByBdkJvmNativeBinding) {
            assertFalse(result.completed)
            assertFalse(result.usesProductionStorage)
            assertFalse(result.exposesSecretMaterial)
            assertNull(result.firstAddress)
            assertNotNull(result.error)
            assertEquals("BDK_ADDRESS_DERIVATION_NATIVE_BINDING_UNAVAILABLE", result.error.code)
            assertRedacted(result.toString())
            return
        }

        assertCompletedDerivation(result, BdkAddressDerivationValidationNetwork.Regtest)
    }

    @Test
    fun optInSignetDerivationCanRunOfflineWhenExplicitlyRequested() {
        if (!BdkAddressDerivationValidationPolicy.fromEnvironment(System.getenv()).enabled) {
            val result = BdkRegtestAddressDerivationValidation.run(
                BdkAddressDerivationValidationRequest(
                    enabled = false,
                    network = BdkAddressDerivationValidationNetwork.Signet,
                ),
            )
            assertEquals(BdkAddressDerivationValidationState.Disabled, result.state)
            return
        }

        val result = BdkRegtestAddressDerivationValidation.run(
            BdkAddressDerivationValidationRequest(
                enabled = true,
                network = BdkAddressDerivationValidationNetwork.Signet,
            ),
        )

        assertCompletedDerivation(result, BdkAddressDerivationValidationNetwork.Signet)
    }

    @Test
    fun addressResultModelsRedactRuntimeAddressTextInToString() {
        val address = SanitizedDerivedAddress(
            network = BdkAddressDerivationValidationNetwork.Regtest,
            keychain = "EXTERNAL",
            derivationIndex = 0,
            addressText = "RUNTIME_TEST_ADDRESS_PLACEHOLDER",
            validForNetwork = true,
            runtimeGenerated = true,
        )
        val result = BdkAddressDerivationValidationResult(
            state = BdkAddressDerivationValidationState.Completed,
            network = BdkAddressDerivationValidationNetwork.Regtest,
            checks = listOf(BdkAddressDerivationValidationCheck.DeterministicFirstAddressMatched),
            capabilities = setOf(BdkAddressDerivationValidationCapability.SkaldOwnedResultTypes),
            firstAddress = address,
            recoveredFirstAddress = address,
            secondAddress = null,
            deterministicFirstAddressMatched = true,
            differentIndexProducedDifferentAddress = false,
            error = null,
            diagnostic = "redacted address derivation result",
        )

        assertFalse(result.exposesSecretMaterial)
        assertFalse(result.toString().contains(address.addressText))
        assertFalse(address.toString().contains(address.addressText))
        assertRedacted(result.toString(), runtimeAddresses = listOf(address.addressText))
        assertFalse(result.toString().contains("org.bitcoindevkit"))
    }

    @Test
    fun bdkAddressApisStayOutOfProductionSourceSets() {
        val root = repositoryRoot()
        val sourceRoot = File(root, "composeApp/src")
        val addressApiPatterns = listOf(
            Regex("""\bpeekAddress\b"""),
            Regex("""\bnextUnusedAddress\b"""),
            Regex("""\brevealNextAddress\b"""),
            Regex("""\brevealAddressesTo\b"""),
            Regex("""\bAddressInfo\b"""),
        )
        val offenders = sourceRoot
            .walkTopDown()
            .filter { it.isFile && it.extension == "kt" }
            .filter { file -> addressApiPatterns.any { pattern -> pattern.containsMatchIn(file.readText()) } }
            .map { it.relativeTo(root).invariantSeparatorsPath }
            .filterNot { path -> path.startsWith("composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/bdk/") }
            .toList()

        assertTrue(offenders.isEmpty(), "BDK address API usage leaked outside desktop validation: $offenders")
    }

    @Test
    fun addressValidationDoesNotSyncSignBroadcastPersistFilesOrUseBackendClients() {
        val root = repositoryRoot()
        val validation = File(root, ValidationFile).readText()
        val forbiddenRuntimeCalls = listOf(
            Regex("""\bstartFullScan\b"""),
            Regex("""\bstartSync"""),
            Regex("""\bapplyUpdate\b"""),
            Regex("""\bsign\("""),
            Regex("""\bfinalizePsbt\b"""),
            Regex("""\bpersist\("""),
            Regex("""\bnewSqlite\b"""),
            Regex("""\bElectrumClient\b"""),
            Regex("""\bEsploraClient\b"""),
            Regex("""\bBlockchain\b"""),
            Regex("""\bRpc\b"""),
        )

        assertTrue(
            forbiddenRuntimeCalls.none { it.containsMatchIn(validation) },
            "Address validation must not sync, sign, broadcast, use backend clients, or use file persistence.",
        )
        assertTrue(validation.contains("Persister.newInMemory"))
    }

    @Test
    fun sourceDocsAndTestsContainNoHardcodedAddressFixtures() {
        val root = repositoryRoot()
        val roots = listOf(
            File(root, "README.md"),
            File(root, "docs"),
            File(root, "composeApp/src"),
        )
        val hardcodedAddress = Regex("""\b(?:bc1|tb1|bcrt1)[a-z0-9]{20,}\b""", RegexOption.IGNORE_CASE)
        val offenders = roots
            .asSequence()
            .flatMap { rootFile ->
                if (rootFile.isDirectory) {
                    rootFile.walkTopDown().asSequence()
                } else {
                    sequenceOf(rootFile)
                }
            }
            .filter { it.isFile && (it.extension in setOf("md", "kt", "kts") || it.name == "README.md") }
            .filter { file -> hardcodedAddress.containsMatchIn(file.readText()) }
            .map { it.relativeTo(root).invariantSeparatorsPath }
            .toList()

        assertTrue(offenders.isEmpty(), "Hardcoded address-like fixtures found: $offenders")
    }

    private fun assertCompletedDerivation(
        result: BdkAddressDerivationValidationResult,
        network: BdkAddressDerivationValidationNetwork,
    ) {
        assertEquals(
            BdkAddressDerivationValidationState.Completed,
            result.state,
            result.error?.safeDetail ?: result.diagnostic,
        )
        assertTrue(result.completed)
        assertEquals(network, result.network)
        assertFalse(result.usesProductionStorage)
        assertFalse(result.exposesSecretMaterial)
        assertFalse(result.producesMainnetAddress)
        assertTrue(result.deterministicFirstAddressMatched)
        assertTrue(result.differentIndexProducedDifferentAddress)
        assertContains(result.checks, BdkAddressDerivationValidationCheck.RuntimeEntropyCreated)
        assertContains(result.checks, BdkAddressDerivationValidationCheck.RuntimeMnemonicCreated)
        assertContains(result.checks, BdkAddressDerivationValidationCheck.RuntimeDescriptorSecretCreated)
        assertContains(result.checks, BdkAddressDerivationValidationCheck.Bip86DescriptorsCreated)
        assertContains(result.checks, BdkAddressDerivationValidationCheck.InMemoryPersisterCreated)
        assertContains(result.checks, BdkAddressDerivationValidationCheck.BdkWalletCreated)
        assertContains(result.checks, BdkAddressDerivationValidationCheck.FirstReceiveAddressPeeked)
        assertContains(result.checks, BdkAddressDerivationValidationCheck.SecondReceiveAddressPeeked)
        assertContains(result.checks, BdkAddressDerivationValidationCheck.RecoveredFirstReceiveAddressPeeked)
        assertContains(result.checks, BdkAddressDerivationValidationCheck.DeterministicFirstAddressMatched)
        assertContains(result.checks, BdkAddressDerivationValidationCheck.DifferentIndexProducedDifferentAddress)
        assertContains(result.checks, BdkAddressDerivationValidationCheck.NoProductionStorageUsed)
        assertContains(result.capabilities, BdkAddressDerivationValidationCapability.TestOnlyAddressDerivation)
        assertContains(result.capabilities, BdkAddressDerivationValidationCapability.DevelopmentNetworksOnly)
        assertContains(result.capabilities, BdkAddressDerivationValidationCapability.InMemoryBdkPersister)
        assertContains(result.capabilities, BdkAddressDerivationValidationCapability.NoBackendNetworking)
        assertContains(result.capabilities, BdkAddressDerivationValidationCapability.NoWalletSync)
        assertContains(result.capabilities, BdkAddressDerivationValidationCapability.NoSigning)
        assertContains(result.capabilities, BdkAddressDerivationValidationCapability.NoBroadcasting)

        val first = requireNotNull(result.firstAddress)
        val recoveredFirst = requireNotNull(result.recoveredFirstAddress)
        val second = requireNotNull(result.secondAddress)
        assertEquals(network, first.network)
        assertEquals(network, recoveredFirst.network)
        assertEquals(network, second.network)
        assertEquals(0, first.derivationIndex)
        assertEquals(0, recoveredFirst.derivationIndex)
        assertEquals(1, second.derivationIndex)
        assertTrue(first.runtimeGenerated)
        assertTrue(recoveredFirst.runtimeGenerated)
        assertTrue(second.runtimeGenerated)
        assertTrue(first.validForNetwork)
        assertTrue(recoveredFirst.validForNetwork)
        assertTrue(second.validForNetwork)
        assertEquals(first.addressText, recoveredFirst.addressText)
        assertNotEquals(first.addressText, second.addressText)
        assertFalse(first.secretMaterialExposed)
        assertFalse(recoveredFirst.secretMaterialExposed)
        assertFalse(second.secretMaterialExposed)
        assertRedacted(
            result.toString(),
            runtimeAddresses = listOf(first.addressText, recoveredFirst.addressText, second.addressText),
        )
    }

    private fun assertRedacted(
        value: String,
        runtimeAddresses: List<String> = emptyList(),
    ) {
        runtimeAddresses
            .filter { it.isNotBlank() }
            .forEach { address ->
                assertFalse(value.contains(address), "Result toString exposed a runtime-generated test address.")
            }
        val forbiddenPatterns = listOf(
            Regex("""(?i)\bxprv[A-Za-z0-9]+"""),
            Regex("""(?i)\bxpub[A-Za-z0-9]+"""),
            Regex("""(?i)\btprv[A-Za-z0-9]+"""),
            Regex("""(?i)\btpub[A-Za-z0-9]+"""),
            Regex("""(?i)\bnsec1[A-Za-z0-9]+"""),
            Regex("""(?i)\bnpub1[A-Za-z0-9]+"""),
            Regex("""(?i)\bpsbt[A-Za-z0-9+/=]+"""),
            Regex("""\b[0-9a-fA-F]{64}\b"""),
        )
        forbiddenPatterns.forEach { pattern ->
            assertFalse(pattern.containsMatchIn(value), "Result exposed wallet-like material matching ${pattern.pattern}")
        }
    }

    private fun repositoryRoot(): File =
        generateSequence(File(".").absoluteFile) { file -> file.parentFile }
            .first { candidate -> File(candidate, "settings.gradle.kts").exists() }

    private companion object {
        const val ValidationFile =
            "composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/bdk/BdkRegtestAddressDerivationValidation.kt"
    }
}
