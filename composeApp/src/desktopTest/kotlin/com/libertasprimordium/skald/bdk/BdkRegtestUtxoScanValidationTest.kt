package com.libertasprimordium.skald.bdk

import com.libertasprimordium.skald.domain.core.NetworkEnvironment
import com.libertasprimordium.skald.domain.onchain.BackendObservationStatus
import com.libertasprimordium.skald.domain.onchain.BackendObservationWarning
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletProfileId
import com.libertasprimordium.skald.domain.onchain.ObservedUtxoSpendReadiness
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressDerivationIndex
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressLifecycleState
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressPolicyBlockingIssue
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressPolicyDecisionState
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressPolicyWarning
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressSource
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressState
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressWalletContext
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressWalletOperationalState
import com.libertasprimordium.skald.regtest.RegtestElectrumIndexerCheck
import java.io.File
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class BdkRegtestUtxoScanValidationTest {
    @Test
    fun utxoScanValidationIsDisabledUnlessExplicitlyRequested() {
        val result = BdkRegtestUtxoScanValidation.runFromEnvironment(environment = emptyMap())

        assertEquals(BdkRegtestUtxoScanValidationState.Disabled, result.state)
        assertFalse(result.completed)
        assertNull(result.backendInventory)
        assertNull(result.scanSummary)
        assertNull(result.electrumScanResult)
        assertNotNull(result.error)
        assertTrue(result.error.safeDetail.contains(BdkRegtestUtxoScanValidationPolicy.EnvironmentVariable))
        assertFalse(result.usesProductionStorage)
        assertFalse(result.usesProductionNetworking)
        assertFalse(result.mainnetScanEnabled)
    }

    @Test
    fun mainnetUtxoScanValidationIsRejectedBeforeWalletOrBackendMaterialExists() {
        val result = BdkRegtestUtxoScanValidation.run(
            BdkRegtestUtxoScanValidationRequest(
                enabled = true,
                network = BdkRegtestUtxoScanValidationNetwork.MainnetDisabled,
            ),
        )

        assertEquals(BdkRegtestUtxoScanValidationState.RejectedMainnet, result.state)
        assertContains(result.checks, BdkRegtestUtxoScanValidationCheck.ExplicitOptIn)
        assertNull(result.backendInventory)
        assertNull(result.scanSummary)
        assertNull(result.electrumScanResult)
        assertNotNull(result.error)
        assertTrue(result.error.safeDetail.contains("Mainnet is disabled"))
        assertFalse(result.usesProductionStorage)
        assertFalse(result.usesProductionNetworking)
        assertFalse(result.mainnetScanEnabled)
        assertRedacted(result.toString())
    }

    @Test
    fun optInUtxoScanValidationReportsLocalIndexerBlockerOrCompletesThroughLocalElectrum() {
        val result = BdkRegtestUtxoScanValidation.runFromEnvironment(System.getenv())
        if (result.state == BdkRegtestUtxoScanValidationState.Disabled) {
            assertFalse(result.completed)
            return
        }

        if (result.state == BdkRegtestUtxoScanValidationState.BlockedLocalElectrumUnavailable) {
            assertFalse(result.completed)
            assertTrue(result.requiresLocalIndexer)
            assertFalse(result.usesProductionStorage)
            assertFalse(result.usesProductionNetworking)
            assertFalse(result.mainnetScanEnabled)
            assertContains(result.checks, BdkRegtestUtxoScanValidationCheck.LocalElectrumAdapterRequested)
            assertContains(result.checks, BdkRegtestUtxoScanValidationCheck.LocalElectrumUnavailable)
            assertContains(result.capabilities, BdkRegtestUtxoScanValidationCapability.LocalElectrumScanAdapter)
            assertContains(result.capabilities, BdkRegtestUtxoScanValidationCapability.BlockedWithoutLocalElectrumBinary)
            val electrumScan = requireNotNull(result.electrumScanResult)
            assertEquals(BdkRegtestElectrumScanState.Unavailable, electrumScan.state)
            assertNotNull(result.error)
            assertRedacted(result.toString())
            return
        }

        if (result.state == BdkRegtestUtxoScanValidationState.Completed) {
            assertTrue(result.completed)
            assertFalse(result.requiresLocalIndexer)
            assertFalse(result.usesProductionStorage)
            assertFalse(result.usesProductionNetworking)
            assertFalse(result.mainnetScanEnabled)
            assertContains(result.checks, BdkRegtestUtxoScanValidationCheck.LocalElectrumAdapterRequested)
            assertContains(result.checks, BdkRegtestUtxoScanValidationCheck.LocalElectrumScanCompleted)
            val electrumScan = requireNotNull(result.electrumScanResult)
            assertEquals(BdkRegtestElectrumScanState.Completed, electrumScan.state)
            assertTrue(electrumScan.completed)
            assertContains(electrumScan.harnessChecks, RegtestElectrumIndexerCheck.BitcoindStarted)
            assertContains(electrumScan.harnessChecks, RegtestElectrumIndexerCheck.NodeReady)
            assertContains(electrumScan.harnessChecks, RegtestElectrumIndexerCheck.ElectrumIndexerStarted)
            assertContains(electrumScan.harnessChecks, RegtestElectrumIndexerCheck.ElectrumIndexerReady)
            assertContains(electrumScan.harnessChecks, RegtestElectrumIndexerCheck.FundingTransactionSent)
            assertContains(electrumScan.harnessChecks, RegtestElectrumIndexerCheck.FundingConfirmationMined)
            assertContains(electrumScan.harnessChecks, RegtestElectrumIndexerCheck.CleanShutdown)
            assertContains(electrumScan.harnessChecks, RegtestElectrumIndexerCheck.TemporaryDatadirsCleaned)
            assertContains(electrumScan.checks, BdkRegtestElectrumScanCheck.LocalElectrumHarnessReady)
            assertContains(electrumScan.checks, BdkRegtestElectrumScanCheck.RuntimeEntropyCreated)
            assertContains(electrumScan.checks, BdkRegtestElectrumScanCheck.BdkWalletCreated)
            assertContains(electrumScan.checks, BdkRegtestElectrumScanCheck.ReceiveAddressRevealed)
            assertContains(electrumScan.checks, BdkRegtestElectrumScanCheck.LocalRegtestAddressFunded)
            assertContains(electrumScan.checks, BdkRegtestElectrumScanCheck.ElectrumClientCreated)
            assertContains(electrumScan.checks, BdkRegtestElectrumScanCheck.ElectrumPingCompleted)
            assertContains(electrumScan.checks, BdkRegtestElectrumScanCheck.FullScanRequestBuilt)
            assertContains(electrumScan.checks, BdkRegtestElectrumScanCheck.ElectrumFullScanCompleted)
            assertContains(electrumScan.checks, BdkRegtestElectrumScanCheck.WalletUpdateApplied)
            assertContains(electrumScan.checks, BdkRegtestElectrumScanCheck.UtxosObserved)
            assertContains(electrumScan.checks, BdkRegtestElectrumScanCheck.ReceivePolicyTransitionApplied)
            assertTrue(electrumScan.observedUtxoCount > 0)
            assertTrue(electrumScan.observedAmountSats > 0)
            val summary = requireNotNull(result.scanSummary)
            assertTrue(summary.addressMarkedUsed)
            assertEquals(ReceiveAddressPolicyDecisionState.WarningRequired, summary.reuseDecision.state)
            assertEquals(BackendObservationStatus.Completed, summary.backendObservationSummary.status)
            assertEquals(1, summary.backendObservationSummary.observedUtxoCount)
            assertTrue(summary.backendObservationSummary.totalAmount.value > 0)
            assertFalse(summary.backendObservationSummary.anySpendableWithoutCoinControl)
            assertContains(
                summary.backendObservationSummary.warnings,
                BackendObservationWarning.ObservationDoesNotAuthorizeSpending,
            )
            assertContains(
                summary.backendObservationSummary.warnings,
                BackendObservationWarning.CoinControlRequiredBeforeSpend,
            )
            assertNull(result.error)
            assertRedacted(result.toString())
            return
        }

        assertEquals(
            BdkRegtestUtxoScanValidationState.BlockedRequiresLocalIndexer,
            result.state,
            result.error?.safeDetail ?: result.diagnostic,
        )
        assertFalse(result.completed)
        assertTrue(result.requiresLocalIndexer)
        assertFalse(result.usesProductionStorage)
        assertFalse(result.usesProductionNetworking)
        assertFalse(result.mainnetScanEnabled)
        assertContains(result.checks, BdkRegtestUtxoScanValidationCheck.ExplicitOptIn)
        assertContains(result.checks, BdkRegtestUtxoScanValidationCheck.RegtestNetworkSelected)
        assertContains(result.checks, BdkRegtestUtxoScanValidationCheck.BdkJvmArtifactInventoryInspected)
        assertContains(result.checks, BdkRegtestUtxoScanValidationCheck.DirectLocalNodeScanBackendAbsent)
        assertContains(result.checks, BdkRegtestUtxoScanValidationCheck.IndexedBackendsDetected)
        assertContains(result.checks, BdkRegtestUtxoScanValidationCheck.ReceivePolicyTransitionModelAvailable)
        assertContains(result.checks, BdkRegtestUtxoScanValidationCheck.NoProductionStorageUsed)
        assertContains(result.capabilities, BdkRegtestUtxoScanValidationCapability.TestOnlyRegtestUtxoScanValidation)
        assertContains(result.capabilities, BdkRegtestUtxoScanValidationCapability.BlockedWithoutLocalIndexer)
        assertContains(result.capabilities, BdkRegtestUtxoScanValidationCapability.NoProductionStorage)
        assertContains(result.capabilities, BdkRegtestUtxoScanValidationCapability.NoProductionNetworking)
        assertContains(result.capabilities, BdkRegtestUtxoScanValidationCapability.NoSigning)
        assertContains(result.capabilities, BdkRegtestUtxoScanValidationCapability.NoBroadcasting)
        assertContains(result.capabilities, BdkRegtestUtxoScanValidationCapability.NoMainnet)

        val inventory = requireNotNull(result.backendInventory)
        assertTrue(inventory.fullScanRequestAvailable)
        assertTrue(inventory.syncRequestAvailable)
        assertFalse(inventory.directLocalNodeRpcClientAvailable)
        assertTrue(inventory.electrumClientAvailable)
        assertTrue(inventory.esploraClientAvailable)
        assertTrue(inventory.compactFilterClientAvailable)
        assertTrue(inventory.hasIndexedScanBackend)
        assertNull(result.scanSummary)
        assertNull(result.electrumScanResult)
        assertNotNull(result.error)
        assertEquals("BDK_REGTEST_UTXO_SCAN_REQUIRES_LOCAL_INDEXER", result.error.code)
        assertRedacted(result.toString())
    }

    @Test
    fun localElectrumAdapterReportsUnavailableWhenElectrsIsMissing() {
        val result = BdkRegtestUtxoScanValidation.runFromEnvironment(
            environment = mapOf(
                BdkRegtestUtxoScanValidationPolicy.EnvironmentVariable to "1",
                com.libertasprimordium.skald.regtest.RegtestElectrumIndexerIntegrationPolicy.EnvironmentVariable to "1",
                "PATH" to "",
            ),
        )

        assertEquals(BdkRegtestUtxoScanValidationState.BlockedLocalElectrumUnavailable, result.state)
        assertFalse(result.completed)
        assertTrue(result.requiresLocalIndexer)
        assertContains(result.checks, BdkRegtestUtxoScanValidationCheck.LocalElectrumAdapterRequested)
        assertContains(result.checks, BdkRegtestUtxoScanValidationCheck.LocalElectrumUnavailable)
        assertNull(result.scanSummary)
        val electrumScan = requireNotNull(result.electrumScanResult)
        assertEquals(BdkRegtestElectrumScanState.Unavailable, electrumScan.state)
        assertFalse(electrumScan.usesPublicEndpoint)
        assertFalse(electrumScan.usesProductionStorage)
        assertFalse(electrumScan.mainnetScanEnabled)
        assertNotNull(result.error)
        assertTrue(result.error.safeDetail.contains("electrs"))
        assertRedacted(result.toString())
    }

    @Test
    fun bdkElectrumScanAdapterRejectsMainnetBeforeWalletOrEndpointMaterialExists() {
        val result = BdkRegtestElectrumScanAdapter.run(
            request = BdkRegtestElectrumScanRequest(
                enabled = true,
                localElectrumEnabled = true,
                network = BdkRegtestUtxoScanValidationNetwork.MainnetDisabled,
            ),
            environment = emptyMap(),
        )

        assertEquals(BdkRegtestElectrumScanState.RejectedMainnet, result.state)
        assertFalse(result.completed)
        assertNull(result.scanSummary)
        assertNotNull(result.error)
        assertTrue(result.error.safeDetail.contains("Mainnet is disabled"))
        assertFalse(result.usesPublicEndpoint)
        assertFalse(result.usesProductionStorage)
        assertFalse(result.mainnetScanEnabled)
        assertRedacted(result.toString())
    }

    @Test
    fun sanitizedObservationTransitionsReceiveAddressToUsedOnlyAfterBackendObservation() {
        val wallet = operationalWallet()
        val displayed = ReceiveAddressState.placeholderReserved(
            wallet = wallet,
            derivationIndex = ReceiveAddressDerivationIndex(0),
        ).markDisplayed()
        assertEquals(ReceiveAddressLifecycleState.Displayed, displayed.lifecycleState)
        assertFalse(displayed.isUsed)

        val summary = BdkRegtestUtxoScanValidation.applyObservedUtxoToReceivePolicy(
            wallet = wallet,
            candidate = displayed,
            observation = SanitizedRegtestUtxoObservation(
                amountSats = 25_000,
                confirmations = 1,
            ),
        )

        assertEquals(BdkRegtestUtxoScanValidationNetwork.Regtest, summary.network)
        assertEquals(1, summary.observedUtxoCount)
        assertEquals(25_000, summary.totalAmountSats)
        assertEquals(ReceiveAddressLifecycleState.Displayed, summary.addressLifecycleBefore)
        assertEquals(ReceiveAddressLifecycleState.ObservedConfirmed, summary.addressLifecycleAfter)
        assertTrue(summary.addressMarkedUsed)
        assertEquals(ReceiveAddressPolicyDecisionState.WarningRequired, summary.reuseDecision.state)
        assertFalse(summary.reuseDecision.allowsAction)
        assertTrue(summary.reuseDecision.requiresExplicitConfirmation)
        assertContains(summary.reuseDecision.warnings, ReceiveAddressPolicyWarning.AddressReuseRequiresExplicitConfirmation)
        assertContains(summary.reuseDecision.warnings, ReceiveAddressPolicyWarning.ObservedAddressReuseIsUnsafe)
        assertContains(summary.reuseDecision.blockingIssues, ReceiveAddressPolicyBlockingIssue.AddressAlreadyUsed)
        assertEquals(BackendObservationStatus.Completed, summary.backendObservationSummary.status)
        assertEquals(1, summary.backendObservationSummary.observedUtxoCount)
        assertEquals(25_000, summary.backendObservationSummary.totalAmount.value)
        assertFalse(summary.backendObservationSummary.productionSyncEnabled)
        assertFalse(summary.backendObservationSummary.signingOrBroadcastEnabled)
        assertFalse(summary.backendObservationSummary.anySpendableWithoutCoinControl)
        assertEquals(
            ObservedUtxoSpendReadiness.CoinControlRequired,
            summary.backendObservationSummary.observedUtxos.single().spendReadiness,
        )
        assertContains(
            summary.backendObservationSummary.warnings,
            BackendObservationWarning.ObservationDoesNotAuthorizeSpending,
        )
        assertRedacted(summary.toString())
    }

    @Test
    fun unconfirmedSanitizedObservationAlsoMarksAddressUsed() {
        val wallet = operationalWallet()
        val displayed = ReceiveAddressState.placeholderReserved(wallet).markDisplayed()

        val summary = BdkRegtestUtxoScanValidation.applyObservedUtxoToReceivePolicy(
            wallet = wallet,
            candidate = displayed,
            observation = SanitizedRegtestUtxoObservation(
                amountSats = 7_500,
                confirmations = 0,
            ),
        )

        assertEquals(ReceiveAddressLifecycleState.ObservedUnconfirmed, summary.addressLifecycleAfter)
        assertTrue(summary.addressMarkedUsed)
        assertEquals(ReceiveAddressPolicyDecisionState.WarningRequired, summary.reuseDecision.state)
        assertEquals(BackendObservationStatus.Completed, summary.backendObservationSummary.status)
        assertEquals(0, summary.backendObservationSummary.observedUtxos.single().confirmationState.depth)
        assertRedacted(summary.toString())
    }

    @Test
    fun sanitizedObservationRejectsNonRegtestPolicyContext() {
        val wallet = operationalWallet(network = NetworkEnvironment.Signet)
        val displayed = ReceiveAddressState.placeholderReserved(wallet).markDisplayed()

        val rejected = runCatching {
            BdkRegtestUtxoScanValidation.applyObservedUtxoToReceivePolicy(
                wallet = wallet,
                candidate = displayed,
                observation = SanitizedRegtestUtxoObservation(
                    amountSats = 1,
                    confirmations = 1,
                ),
            )
        }

        assertTrue(rejected.isFailure)
        assertFalse(rejected.exceptionOrNull().toString().contains("org.bitcoindevkit"))
        assertRedacted(rejected.exceptionOrNull().toString())
    }

    @Test
    fun utxoScanValidationResultModelsRedactRuntimeIdentifiers() {
        val observation = SanitizedRegtestUtxoObservation(
            amountSats = 10_000,
            confirmations = 2,
            txidDisplay = "RUNTIME_GENERATED_TXID_PLACEHOLDER",
            outpointDisplay = "RUNTIME_GENERATED_OUTPOINT_PLACEHOLDER",
        )

        assertFalse(observation.toString().contains(observation.txidDisplay))
        assertFalse(observation.toString().contains(observation.outpointDisplay))
        assertRedacted(observation.toString())
    }

    @Test
    fun bdkScanAndBackendApisStayOutOfProductionSourceSets() {
        val root = repositoryRoot()
        val sourceRoot = File(root, "composeApp/src")
        val scanApiPatterns = listOf(
            Regex("""\bstartFullScan\b"""),
            Regex("""\bstartSyncWithRevealedSpks\b"""),
            Regex("""\bapplyUpdate\b"""),
            Regex("""\blistUnspent\b"""),
            Regex("""\bElectrumClient\b"""),
            Regex("""\bEsploraClient\b"""),
            Regex("""\bCbfClient\b"""),
            Regex("""\bCbfBuilder\b"""),
        )
        val offenders = sourceRoot
            .walkTopDown()
            .filter { it.isFile && it.extension == "kt" }
            .filter { file -> scanApiPatterns.any { pattern -> pattern.containsMatchIn(file.readText()) } }
            .map { it.relativeTo(root).invariantSeparatorsPath }
            .filterNot { path -> path.startsWith("composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/bdk/") }
            .toList()

        assertTrue(offenders.isEmpty(), "BDK scan/backend API usage leaked outside desktop validation: $offenders")
    }

    @Test
    fun bdkElectrumScanAdapterUsesOnlyApprovedTestOnlyBdkScanApis() {
        val root = repositoryRoot()
        val adapter = File(root, ElectrumScanAdapterFile).readText()

        assertContains(adapter, "ElectrumClient")
        assertContains(adapter, "startFullScan")
        assertContains(adapter, "fullScan")
        assertContains(adapter, "applyUpdate")
        assertContains(adapter, "listUnspent")
        assertFalse(adapter.contains("transactionBroadcast"))
        assertFalse(Regex("""\bsign\(""").containsMatchIn(adapter))
        assertFalse(adapter.contains("finalizePsbt"))
        assertFalse(adapter.contains("TxBuilder"))
        assertFalse(adapter.contains("Psbt"))
        assertFalse(adapter.contains("Persister.newSqlite"))
        assertFalse(adapter.contains("persist("))
        assertFalse(adapter.contains("EsploraClient"))
        assertFalse(adapter.contains("CbfClient"))
    }

    @Test
    fun sourceDocsAndTestsContainNoHardcodedUtxoScanAddressOrTxidFixtures() {
        val root = repositoryRoot()
        val offenders = utxoScanFixtureGuardTargets(root)
            .asSequence()
            .filter { it.exists() }
            .flatMap { rootFile ->
                if (rootFile.isDirectory) {
                    rootFile.walkTopDown().asSequence()
                } else {
                    sequenceOf(rootFile)
                }
            }
            .filter { it.isFile && (it.extension in setOf("md", "kt", "kts") || it.name == "README.md") }
            .filterNot { file -> file.relativeTo(root).invariantSeparatorsPath == ThisGuardFile }
            .flatMap { file ->
                val text = file.readText()
                forbiddenUtxoScanFixturePatterns().mapNotNull { pattern ->
                    if (pattern.regex.containsMatchIn(text)) {
                        "${file.relativeTo(root).invariantSeparatorsPath} (${pattern.label})"
                    } else {
                        null
                    }
                }.asSequence()
            }
            .toList()

        assertTrue(
            offenders.isEmpty(),
            "Hardcoded UTXO scan wallet/address/txid-like fixtures found: $offenders",
        )
    }

    private fun operationalWallet(
        network: NetworkEnvironment = NetworkEnvironment.Regtest,
    ): ReceiveAddressWalletContext =
        ReceiveAddressWalletContext(
            profileId = DescriptorWalletProfileId("utxo-scan-policy-${network.name.lowercase()}"),
            profileLabel = "UTXO scan policy placeholder",
            network = network,
            source = ReceiveAddressSource.NativeDescriptor,
            operationalState = ReceiveAddressWalletOperationalState.OperationalDevelopmentWallet,
            canDeriveReceiveAddresses = true,
        )

    private fun assertRedacted(value: String) {
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
            Regex("""(?i)\bmnemonic\b.+\b[a-z]+\b"""),
        )
        forbiddenPatterns.forEach { pattern ->
            assertFalse(pattern.containsMatchIn(value), "Result exposed wallet-like material matching ${pattern.pattern}")
        }
    }

    private fun repositoryRoot(): File =
        generateSequence(File(".").absoluteFile) { file -> file.parentFile }
            .first { candidate -> File(candidate, "settings.gradle.kts").exists() }

    private data class ForbiddenUtxoScanFixturePattern(
        val label: String,
        val regex: Regex,
    )

    private fun utxoScanFixtureGuardTargets(root: File): List<File> =
        listOf(
            File(root, "docs/BDK_REGTEST_UTXO_SCAN_VALIDATION.md"),
            File(root, "docs/BDK_REGTEST_WALLET_VALIDATION.md"),
            File(root, "docs/BDK_REGTEST_ADDRESS_DERIVATION.md"),
            File(root, "docs/LOCAL_ELECTRUM_REGTEST_HARNESS.md"),
            File(root, "docs/REGTEST_HARNESS.md"),
            File(root, "docs/BACKEND_OBSERVATION_STATE.md"),
            File(root, "docs/RECEIVE_ADDRESS_POLICY.md"),
            File(root, "docs/PRODUCTION_BACKEND_ADAPTER_BOUNDARY.md"),
            File(root, "docs/PRODUCTION_SYNC_SERVICE_BOUNDARY.md"),
            File(root, "docs/RECOVERY_PRIVACY_SYNC_STATUS.md"),
            File(root, "composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/domain/onchain"),
            File(root, "composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/BackendEndpointPolicyTest.kt"),
            File(root, "composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/BackendObservationStateTest.kt"),
            File(root, "composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/BackendSettingsTest.kt"),
            File(root, "composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/BitcoinBackendConnectionHarnessTest.kt"),
            File(root, "composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/BitcoinWalletSyncServiceTest.kt"),
            File(root, "composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/BitcoinWalletSyncStatusUiModelTest.kt"),
            File(root, "composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/BdkAdapterBoundaryTest.kt"),
            File(root, "composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/DescriptorWalletWorkflowTest.kt"),
            File(root, "composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/ProductionBackendAdapterBoundaryTest.kt"),
            File(root, "composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/ReceiveAddressPolicyTest.kt"),
            File(root, "composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/RecoveryPrivacySyncStatusTest.kt"),
            File(root, "composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/BackendObservationSourceGuardTest.kt"),
            File(root, "composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/BdkDesktopAdapterProbeTest.kt"),
            File(root, "composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/ProductionBackendAdapterSourceGuardTest.kt"),
            File(root, "composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/ReceiveAddressPolicySourceGuardTest.kt"),
            File(root, "composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/bdk"),
            File(root, "composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/regtest"),
        )

    private fun forbiddenUtxoScanFixturePatterns(): List<ForbiddenUtxoScanFixturePattern> =
        listOf(
            ForbiddenUtxoScanFixturePattern(
                label = "Bitcoin address",
                regex = Regex("""\b(?:bc1|tb1|bcrt1)[a-z0-9]{20,}\b""", RegexOption.IGNORE_CASE),
            ),
            ForbiddenUtxoScanFixturePattern(
                label = "64-hex txid/outpoint-like fixture",
                regex = Regex("""\b[0-9a-fA-F]{64}\b"""),
            ),
            ForbiddenUtxoScanFixturePattern(
                label = "raw transaction hex-like fixture",
                regex = Regex("""\b0[12][0-9a-fA-F]{80,}\b"""),
            ),
            ForbiddenUtxoScanFixturePattern(
                label = "serialized PSBT-like fixture",
                regex = Regex("""\bcHNidP8[A-Za-z0-9+/=]{16,}\b"""),
            ),
            ForbiddenUtxoScanFixturePattern(
                label = "extended private key-like fixture",
                regex = Regex("""\b[xt]prv[A-Za-z0-9]{20,}\b""", RegexOption.IGNORE_CASE),
            ),
            ForbiddenUtxoScanFixturePattern(
                label = "WIF-like private key fixture",
                regex = Regex("""\b(?:K|L|5)[1-9A-HJ-NP-Za-km-z]{50,51}\b"""),
            ),
            ForbiddenUtxoScanFixturePattern(
                label = "Nostr secret key-like fixture",
                regex = Regex("""\bnsec1[A-Za-z0-9]{20,}\b""", RegexOption.IGNORE_CASE),
            ),
            ForbiddenUtxoScanFixturePattern(
                label = "backend credential-like fixture",
                regex = Regex("""(?i)\b(?:rpcpassword|rpcuser|rpccookie|cookie|macaroon|token|api[_-]?key)\s*[:=]\s*[A-Za-z0-9+/]{20,}\b"""),
            ),
            ForbiddenUtxoScanFixturePattern(
                label = "mnemonic-like fixture",
                regex = Regex("""(?i)\b(?:abandon\s+){2,}[a-z]+(?:\s+[a-z]+){8,}\b"""),
            ),
        )

    private companion object {
        const val ThisGuardFile =
            "composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/bdk/BdkRegtestUtxoScanValidationTest.kt"

        const val ElectrumScanAdapterFile =
            "composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/bdk/BdkRegtestElectrumScanAdapter.kt"
    }
}
