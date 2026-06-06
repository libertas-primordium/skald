package com.libertasprimordium.skald.bdk

import com.libertasprimordium.skald.domain.core.NetworkEnvironment
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressLifecycleState
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressObservation
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressPolicy
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressPolicyAction
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressPolicyDecision
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressPolicyRequest
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressState
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressWalletContext
import com.libertasprimordium.skald.domain.onchain.bdk.BdkAdapterVersion
import com.libertasprimordium.skald.regtest.RegtestElectrumIndexerIntegrationPolicy
import java.io.File
import java.util.zip.ZipFile

enum class BdkRegtestUtxoScanValidationNetwork(val label: String) {
    Regtest("regtest"),
    MainnetDisabled("mainnet disabled"),
}

enum class BdkRegtestUtxoScanValidationState(val label: String) {
    Disabled("disabled"),
    BlockedRequiresLocalIndexer("blocked: local indexer required"),
    BlockedLocalElectrumUnavailable("blocked: local Electrum unavailable"),
    Completed("completed"),
    RejectedMainnet("rejected mainnet"),
    Failed("failed"),
}

enum class BdkRegtestUtxoScanValidationCheck(val label: String) {
    ExplicitOptIn("explicit UTXO scan validation opt-in"),
    RegtestNetworkSelected("regtest network selected"),
    BdkJvmArtifactInventoryInspected("BDK JVM artifact inventory inspected"),
    DirectLocalNodeScanBackendAbsent("direct local-node scan backend absent"),
    IndexedBackendsDetected("indexed scan backends detected"),
    LocalElectrumAdapterRequested("local Electrum scan adapter requested"),
    LocalElectrumUnavailable("local Electrum scan adapter unavailable"),
    LocalElectrumScanCompleted("local Electrum scan completed"),
    ReceivePolicyTransitionModelAvailable("receive-address policy transition model available"),
    NoProductionStorageUsed("no production storage used"),
}

enum class BdkRegtestUtxoScanValidationCapability(val label: String) {
    TestOnlyRegtestUtxoScanValidation("test-only regtest UTXO scan validation boundary"),
    LocalRegtestOnly("local regtest only"),
    BdkWalletAddressValidationAlreadyAvailable("BDK wallet/address validation already available"),
    SkaldOwnedResultTypes("Skald-owned result types"),
    RedactedDiagnostics("redacted diagnostics"),
    ReceiveAddressPolicyIntegration("receive-address policy integration"),
    BlockedWithoutLocalIndexer("blocked without a local indexed scan backend"),
    LocalElectrumScanAdapter("local Electrum scan adapter"),
    BlockedWithoutLocalElectrumBinary("blocked without local electrs binary"),
    NoProductionStorage("no production storage"),
    NoProductionNetworking("no production networking"),
    NoSigning("no signing"),
    NoBroadcasting("no broadcasting"),
    NoMainnet("no mainnet"),
}

data class BdkRegtestUtxoScanBackendInventory(
    val artifactName: String,
    val fullScanRequestAvailable: Boolean,
    val syncRequestAvailable: Boolean,
    val directLocalNodeRpcClientAvailable: Boolean,
    val electrumClientAvailable: Boolean,
    val esploraClientAvailable: Boolean,
    val compactFilterClientAvailable: Boolean,
) {
    val hasIndexedScanBackend: Boolean
        get() = electrumClientAvailable || esploraClientAvailable || compactFilterClientAvailable
}

data class SanitizedRegtestUtxoObservation(
    val amountSats: Long,
    val confirmations: Int,
    val txidDisplay: String = "RUNTIME_REGTEST_TXID_REDACTED",
    val outpointDisplay: String = "RUNTIME_REGTEST_OUTPOINT_REDACTED",
    val runtimeGenerated: Boolean = true,
) {
    val confirmed: Boolean
        get() = confirmations > 0

    override fun toString(): String =
        "SanitizedRegtestUtxoObservation(amountSats=$amountSats, confirmations=$confirmations, txidDisplay=RUNTIME_REGTEST_TXID_REDACTED, outpointDisplay=RUNTIME_REGTEST_OUTPOINT_REDACTED, runtimeGenerated=$runtimeGenerated)"
}

data class SanitizedRegtestScanSummary(
    val network: BdkRegtestUtxoScanValidationNetwork,
    val observedUtxoCount: Int,
    val totalAmountSats: Long,
    val addressLifecycleBefore: ReceiveAddressLifecycleState,
    val addressLifecycleAfter: ReceiveAddressLifecycleState,
    val addressMarkedUsed: Boolean,
    val reuseDecision: ReceiveAddressPolicyDecision,
    val observations: List<SanitizedRegtestUtxoObservation>,
)

data class BdkRegtestUtxoScanValidationError(
    val code: String,
    val safeDetail: String,
)

data class BdkRegtestUtxoScanValidationRequest(
    val enabled: Boolean,
    val network: BdkRegtestUtxoScanValidationNetwork,
)

data class BdkRegtestUtxoScanValidationResult(
    val state: BdkRegtestUtxoScanValidationState,
    val network: BdkRegtestUtxoScanValidationNetwork?,
    val checks: List<BdkRegtestUtxoScanValidationCheck>,
    val capabilities: Set<BdkRegtestUtxoScanValidationCapability>,
    val backendInventory: BdkRegtestUtxoScanBackendInventory?,
    val scanSummary: SanitizedRegtestScanSummary?,
    val electrumScanResult: BdkRegtestElectrumScanResult?,
    val error: BdkRegtestUtxoScanValidationError?,
    val diagnostic: String,
) {
    val completed: Boolean
        get() = state == BdkRegtestUtxoScanValidationState.Completed

    val requiresLocalIndexer: Boolean
        get() = state == BdkRegtestUtxoScanValidationState.BlockedRequiresLocalIndexer ||
            state == BdkRegtestUtxoScanValidationState.BlockedLocalElectrumUnavailable

    val usesProductionStorage: Boolean
        get() = state != BdkRegtestUtxoScanValidationState.Disabled &&
            state != BdkRegtestUtxoScanValidationState.RejectedMainnet &&
            BdkRegtestUtxoScanValidationCapability.NoProductionStorage !in capabilities

    val usesProductionNetworking: Boolean
        get() = state != BdkRegtestUtxoScanValidationState.Disabled &&
            BdkRegtestUtxoScanValidationCapability.NoProductionNetworking !in capabilities

    val mainnetScanEnabled: Boolean
        get() = false
}

data class BdkRegtestUtxoScanValidationPolicy(
    val enabled: Boolean,
) {
    companion object {
        const val EnvironmentVariable = "SKALD_RUN_BDK_REGTEST_UTXO_SCAN"

        fun fromEnvironment(
            environment: Map<String, String> = System.getenv(),
        ): BdkRegtestUtxoScanValidationPolicy =
            BdkRegtestUtxoScanValidationPolicy(enabled = environment[EnvironmentVariable] == "1")
    }
}

object BdkRegtestUtxoScanValidation {
    fun runFromEnvironment(
        environment: Map<String, String> = System.getenv(),
    ): BdkRegtestUtxoScanValidationResult =
        run(
            BdkRegtestUtxoScanValidationRequest(
                enabled = BdkRegtestUtxoScanValidationPolicy.fromEnvironment(environment).enabled,
                network = BdkRegtestUtxoScanValidationNetwork.Regtest,
            ),
            environment = environment,
        )

    fun run(
        request: BdkRegtestUtxoScanValidationRequest,
        environment: Map<String, String> = System.getenv(),
    ): BdkRegtestUtxoScanValidationResult {
        if (!request.enabled) {
            return BdkRegtestUtxoScanValidationResult(
                state = BdkRegtestUtxoScanValidationState.Disabled,
                network = null,
                checks = emptyList(),
                capabilities = emptySet(),
                backendInventory = null,
                scanSummary = null,
                electrumScanResult = null,
                error = BdkRegtestUtxoScanValidationError(
                    code = "BDK_REGTEST_UTXO_SCAN_VALIDATION_DISABLED",
                    safeDetail = "Set ${BdkRegtestUtxoScanValidationPolicy.EnvironmentVariable}=1 to run the test-only regtest UTXO scan validation boundary.",
                ),
                diagnostic = "BDK UTXO scan validation is disabled unless explicitly requested for desktop tests.",
            )
        }

        if (request.network != BdkRegtestUtxoScanValidationNetwork.Regtest) {
            return BdkRegtestUtxoScanValidationResult(
                state = BdkRegtestUtxoScanValidationState.RejectedMainnet,
                network = request.network,
                checks = listOf(BdkRegtestUtxoScanValidationCheck.ExplicitOptIn),
                capabilities = defaultCapabilities(),
                backendInventory = null,
                scanSummary = null,
                electrumScanResult = null,
                error = BdkRegtestUtxoScanValidationError(
                    code = "BDK_REGTEST_UTXO_SCAN_MAINNET_REJECTED",
                    safeDetail = "Mainnet is disabled. Test-only BDK UTXO scan validation is allowed only for regtest.",
                ),
                diagnostic = "BDK UTXO scan validation refused a non-regtest network before wallet or backend material was created.",
            )
        }

        val checks = mutableListOf(
            BdkRegtestUtxoScanValidationCheck.ExplicitOptIn,
            BdkRegtestUtxoScanValidationCheck.RegtestNetworkSelected,
        )
        return runCatching {
            val inventory = BdkRegtestUtxoScanBackendInventoryInspector.currentClasspathInventory()
            checks += BdkRegtestUtxoScanValidationCheck.BdkJvmArtifactInventoryInspected
            if (!inventory.directLocalNodeRpcClientAvailable) {
                checks += BdkRegtestUtxoScanValidationCheck.DirectLocalNodeScanBackendAbsent
            }
            if (inventory.hasIndexedScanBackend) {
                checks += BdkRegtestUtxoScanValidationCheck.IndexedBackendsDetected
            }
            checks += BdkRegtestUtxoScanValidationCheck.ReceivePolicyTransitionModelAvailable
            checks += BdkRegtestUtxoScanValidationCheck.NoProductionStorageUsed

            if (RegtestElectrumIndexerIntegrationPolicy.fromEnvironment(environment).enabled) {
                checks += BdkRegtestUtxoScanValidationCheck.LocalElectrumAdapterRequested
                val electrumScan = BdkRegtestElectrumScanAdapter.run(
                    request = BdkRegtestElectrumScanRequest(
                        enabled = true,
                        localElectrumEnabled = true,
                        network = BdkRegtestUtxoScanValidationNetwork.Regtest,
                    ),
                    environment = environment,
                )
                return@runCatching electrumScan.toValidationResult(
                    checks = checks,
                    inventory = inventory,
                )
            }

            BdkRegtestUtxoScanValidationResult(
                state = BdkRegtestUtxoScanValidationState.BlockedRequiresLocalIndexer,
                network = BdkRegtestUtxoScanValidationNetwork.Regtest,
                checks = checks.distinct(),
                capabilities = defaultCapabilities(),
                backendInventory = inventory,
                scanSummary = null,
                electrumScanResult = null,
                error = BdkRegtestUtxoScanValidationError(
                    code = "BDK_REGTEST_UTXO_SCAN_REQUIRES_LOCAL_INDEXER",
                    safeDetail = "BDK ${BdkAdapterVersion.Pinned.version} exposes wallet scan requests and indexed backends, but the local Electrum scan adapter is not enabled. Set ${RegtestElectrumIndexerIntegrationPolicy.EnvironmentVariable}=1 with ${BdkRegtestUtxoScanValidationPolicy.EnvironmentVariable}=1 to attempt local regtest UTXO observation.",
                ),
                diagnostic = "Test-only UTXO scan validation stopped before creating wallet material, funding addresses, syncing, signing, broadcasting, or using production storage.",
            )
        }.getOrElse {
            BdkRegtestUtxoScanValidationResult(
                state = BdkRegtestUtxoScanValidationState.Failed,
                network = BdkRegtestUtxoScanValidationNetwork.Regtest,
                checks = checks.distinct(),
                capabilities = defaultCapabilities(),
                backendInventory = null,
                scanSummary = null,
                electrumScanResult = null,
                error = BdkRegtestUtxoScanValidationError(
                    code = "BDK_REGTEST_UTXO_SCAN_VALIDATION_FAILED",
                    safeDetail = "BDK UTXO scan validation failed while inspecting the Skald-owned backend inventory. Raw library and environment details were not exposed.",
                ),
                diagnostic = "Test-only UTXO scan validation failed with a redacted Skald-owned error.",
            )
        }
    }

    fun applyObservedUtxoToReceivePolicy(
        wallet: ReceiveAddressWalletContext,
        candidate: ReceiveAddressState,
        observation: SanitizedRegtestUtxoObservation,
    ): SanitizedRegtestScanSummary {
        require(wallet.network == NetworkEnvironment.Regtest) {
            "UTXO scan policy validation is regtest-only."
        }
        require(candidate.network == NetworkEnvironment.Regtest) {
            "UTXO scan policy validation accepts only regtest receive-address state."
        }

        val before = candidate.lifecycleState
        val observed = candidate.markObserved(
            if (observation.confirmed) {
                ReceiveAddressObservation.ConfirmedReceive
            } else {
                ReceiveAddressObservation.UnconfirmedReceive
            },
        )
        val reuseDecision = ReceiveAddressPolicy.evaluate(
            ReceiveAddressPolicyRequest(
                action = ReceiveAddressPolicyAction.AttemptAddressReuse,
                wallet = wallet,
                candidate = observed,
            ),
        )

        return SanitizedRegtestScanSummary(
            network = BdkRegtestUtxoScanValidationNetwork.Regtest,
            observedUtxoCount = 1,
            totalAmountSats = observation.amountSats,
            addressLifecycleBefore = before,
            addressLifecycleAfter = observed.lifecycleState,
            addressMarkedUsed = observed.isUsed,
            reuseDecision = reuseDecision,
            observations = listOf(observation),
        )
    }

    private fun defaultCapabilities(): Set<BdkRegtestUtxoScanValidationCapability> =
        setOf(
            BdkRegtestUtxoScanValidationCapability.TestOnlyRegtestUtxoScanValidation,
            BdkRegtestUtxoScanValidationCapability.LocalRegtestOnly,
            BdkRegtestUtxoScanValidationCapability.BdkWalletAddressValidationAlreadyAvailable,
            BdkRegtestUtxoScanValidationCapability.SkaldOwnedResultTypes,
            BdkRegtestUtxoScanValidationCapability.RedactedDiagnostics,
            BdkRegtestUtxoScanValidationCapability.ReceiveAddressPolicyIntegration,
            BdkRegtestUtxoScanValidationCapability.BlockedWithoutLocalIndexer,
            BdkRegtestUtxoScanValidationCapability.LocalElectrumScanAdapter,
            BdkRegtestUtxoScanValidationCapability.BlockedWithoutLocalElectrumBinary,
            BdkRegtestUtxoScanValidationCapability.NoProductionStorage,
            BdkRegtestUtxoScanValidationCapability.NoProductionNetworking,
            BdkRegtestUtxoScanValidationCapability.NoSigning,
            BdkRegtestUtxoScanValidationCapability.NoBroadcasting,
            BdkRegtestUtxoScanValidationCapability.NoMainnet,
        )
}

private fun BdkRegtestElectrumScanResult.toValidationResult(
    checks: List<BdkRegtestUtxoScanValidationCheck>,
    inventory: BdkRegtestUtxoScanBackendInventory,
): BdkRegtestUtxoScanValidationResult {
    val extraChecks = when (state) {
        BdkRegtestElectrumScanState.Completed ->
            listOf(BdkRegtestUtxoScanValidationCheck.LocalElectrumScanCompleted)
        BdkRegtestElectrumScanState.Unavailable ->
            listOf(BdkRegtestUtxoScanValidationCheck.LocalElectrumUnavailable)
        else -> emptyList()
    }
    val validationState = when (state) {
        BdkRegtestElectrumScanState.Completed -> BdkRegtestUtxoScanValidationState.Completed
        BdkRegtestElectrumScanState.Unavailable -> BdkRegtestUtxoScanValidationState.BlockedLocalElectrumUnavailable
        BdkRegtestElectrumScanState.RejectedMainnet -> BdkRegtestUtxoScanValidationState.RejectedMainnet
        BdkRegtestElectrumScanState.Disabled -> BdkRegtestUtxoScanValidationState.BlockedRequiresLocalIndexer
        BdkRegtestElectrumScanState.Failed -> BdkRegtestUtxoScanValidationState.Failed
    }
    return BdkRegtestUtxoScanValidationResult(
        state = validationState,
        network = BdkRegtestUtxoScanValidationNetwork.Regtest,
        checks = (checks + extraChecks).distinct(),
        capabilities = BdkRegtestUtxoScanValidation.defaultResultCapabilitiesForElectrumAdapter(state),
        backendInventory = inventory,
        scanSummary = scanSummary,
        electrumScanResult = this,
        error = when (state) {
            BdkRegtestElectrumScanState.Completed -> null
            BdkRegtestElectrumScanState.Unavailable -> BdkRegtestUtxoScanValidationError(
                code = error?.code ?: "BDK_REGTEST_ELECTRUM_SCAN_UNAVAILABLE",
                safeDetail = error?.safeDetail
                    ?: "Local Electrum regtest adapter is unavailable. Install electrs or set the local indexer binary environment variable.",
            )
            else -> BdkRegtestUtxoScanValidationError(
                code = error?.code ?: "BDK_REGTEST_ELECTRUM_SCAN_BLOCKED",
                safeDetail = error?.safeDetail
                    ?: "Local Electrum regtest scan adapter did not complete. Raw backend and wallet details were not exposed.",
            )
        },
        diagnostic = diagnostic,
    )
}

private fun BdkRegtestUtxoScanValidation.defaultResultCapabilitiesForElectrumAdapter(
    scanState: BdkRegtestElectrumScanState,
): Set<BdkRegtestUtxoScanValidationCapability> =
    buildSet {
        add(BdkRegtestUtxoScanValidationCapability.TestOnlyRegtestUtxoScanValidation)
        add(BdkRegtestUtxoScanValidationCapability.LocalRegtestOnly)
        add(BdkRegtestUtxoScanValidationCapability.BdkWalletAddressValidationAlreadyAvailable)
        add(BdkRegtestUtxoScanValidationCapability.SkaldOwnedResultTypes)
        add(BdkRegtestUtxoScanValidationCapability.RedactedDiagnostics)
        add(BdkRegtestUtxoScanValidationCapability.ReceiveAddressPolicyIntegration)
        add(BdkRegtestUtxoScanValidationCapability.LocalElectrumScanAdapter)
        add(BdkRegtestUtxoScanValidationCapability.NoProductionStorage)
        add(BdkRegtestUtxoScanValidationCapability.NoProductionNetworking)
        add(BdkRegtestUtxoScanValidationCapability.NoSigning)
        add(BdkRegtestUtxoScanValidationCapability.NoBroadcasting)
        add(BdkRegtestUtxoScanValidationCapability.NoMainnet)
        if (scanState == BdkRegtestElectrumScanState.Unavailable) {
            add(BdkRegtestUtxoScanValidationCapability.BlockedWithoutLocalElectrumBinary)
        }
    }

private object BdkRegtestUtxoScanBackendInventoryInspector {
    fun currentClasspathInventory(): BdkRegtestUtxoScanBackendInventory {
        val artifact = System.getProperty("java.class.path")
            .split(File.pathSeparator)
            .map(::File)
            .filter { it.name == "bdk-jvm-${BdkAdapterVersion.Pinned.version}.jar" }
            .distinctBy { it.absolutePath }
            .singleOrNull()
            ?: error("Expected one pinned BDK JVM artifact on the desktop test classpath.")
        val entries = ZipFile(artifact).use { zip ->
            zip.entries().asSequence().map { it.name }.toSet()
        }

        return BdkRegtestUtxoScanBackendInventory(
            artifactName = artifact.name,
            fullScanRequestAvailable = entries.hasBdkClass("FullScanRequest"),
            syncRequestAvailable = entries.hasBdkClass("SyncRequest"),
            directLocalNodeRpcClientAvailable = entries.hasAnyBdkClass(
                "RpcClient",
                "BitcoinCoreClient",
                "CoreRpcClient",
            ),
            electrumClientAvailable = entries.hasBdkClass("ElectrumClient"),
            esploraClientAvailable = entries.hasBdkClass("EsploraClient"),
            compactFilterClientAvailable = entries.hasBdkClass("CbfClient"),
        )
    }

    private fun Set<String>.hasBdkClass(className: String): Boolean =
        contains("org/bitcoindevkit/$className.class")

    private fun Set<String>.hasAnyBdkClass(vararg classNames: String): Boolean =
        classNames.any { className -> hasBdkClass(className) }
}
