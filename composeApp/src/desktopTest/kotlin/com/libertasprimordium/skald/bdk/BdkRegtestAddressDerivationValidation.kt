package com.libertasprimordium.skald.bdk

import org.bitcoindevkit.AddressInfo
import org.bitcoindevkit.Descriptor
import org.bitcoindevkit.DescriptorSecretKey
import org.bitcoindevkit.KeychainKind
import org.bitcoindevkit.Mnemonic
import org.bitcoindevkit.Network
import org.bitcoindevkit.Persister
import org.bitcoindevkit.Wallet
import java.security.SecureRandom

enum class BdkAddressDerivationValidationNetwork(val label: String) {
    Regtest("regtest"),
    Signet("signet"),
    MainnetDisabled("mainnet disabled"),
}

enum class BdkAddressDerivationValidationState(val label: String) {
    Disabled("disabled"),
    Completed("completed"),
    BlockedByBdkJvmNativeBinding("blocked by BDK JVM native binding"),
    RejectedMainnet("rejected mainnet"),
    Failed("failed"),
}

enum class BdkAddressDerivationValidationCheck(val label: String) {
    ExplicitOptIn("explicit address derivation opt-in"),
    DevelopmentNetworkSelected("development network selected"),
    RuntimeEntropyCreated("runtime-only entropy created"),
    RuntimeMnemonicCreated("runtime-only mnemonic created"),
    RuntimeDescriptorSecretCreated("runtime-only descriptor secret created"),
    Bip86DescriptorsCreated("test-only BIP86 descriptors created"),
    InMemoryPersisterCreated("BDK in-memory persister created"),
    BdkWalletCreated("test-only BDK wallet created"),
    FirstReceiveAddressPeeked("first receive address peeked"),
    SecondReceiveAddressPeeked("second receive address peeked"),
    RecoveredFirstReceiveAddressPeeked("recovered first receive address peeked"),
    DeterministicFirstAddressMatched("deterministic first receive address matched"),
    DifferentIndexProducedDifferentAddress("different receive index produced different address"),
    NoProductionStorageUsed("no production storage used"),
}

enum class BdkAddressDerivationValidationCapability(val label: String) {
    TestOnlyBdkWalletCreation("test-only BDK wallet creation"),
    TestOnlyAddressDerivation("test-only address derivation"),
    TestOnlySeedRecovery("test-only seed recovery validation"),
    DevelopmentNetworksOnly("regtest/signet only"),
    InMemoryBdkPersister("BDK in-memory persister"),
    SkaldOwnedResultTypes("Skald-owned result types"),
    RedactedDiagnostics("redacted diagnostics"),
    NoProductionStorage("no production storage"),
    NoBackendNetworking("no backend networking"),
    NoWalletSync("no wallet sync"),
    NoUtxoScan("no UTXO scan"),
    NoSigning("no signing"),
    NoBroadcasting("no broadcasting"),
}

data class SanitizedDerivedAddress(
    val network: BdkAddressDerivationValidationNetwork,
    val keychain: String,
    val derivationIndex: Int,
    val addressText: String,
    val validForNetwork: Boolean,
    val runtimeGenerated: Boolean,
    val secretMaterialExposed: Boolean = false,
) {
    override fun toString(): String =
        "SanitizedDerivedAddress(network=${network.label}, keychain=$keychain, derivationIndex=$derivationIndex, addressText=RUNTIME_TEST_ADDRESS_REDACTED, validForNetwork=$validForNetwork, runtimeGenerated=$runtimeGenerated, secretMaterialExposed=$secretMaterialExposed)"
}

data class BdkAddressDerivationValidationError(
    val code: String,
    val safeDetail: String,
)

data class BdkAddressDerivationValidationRequest(
    val enabled: Boolean,
    val network: BdkAddressDerivationValidationNetwork,
)

data class BdkAddressDerivationValidationResult(
    val state: BdkAddressDerivationValidationState,
    val network: BdkAddressDerivationValidationNetwork?,
    val checks: List<BdkAddressDerivationValidationCheck>,
    val capabilities: Set<BdkAddressDerivationValidationCapability>,
    val firstAddress: SanitizedDerivedAddress?,
    val recoveredFirstAddress: SanitizedDerivedAddress?,
    val secondAddress: SanitizedDerivedAddress?,
    val deterministicFirstAddressMatched: Boolean,
    val differentIndexProducedDifferentAddress: Boolean,
    val error: BdkAddressDerivationValidationError?,
    val diagnostic: String,
) {
    val completed: Boolean
        get() = state == BdkAddressDerivationValidationState.Completed

    val exposesSecretMaterial: Boolean
        get() = listOfNotNull(firstAddress, recoveredFirstAddress, secondAddress)
            .any { it.secretMaterialExposed }

    val usesProductionStorage: Boolean
        get() = state != BdkAddressDerivationValidationState.Disabled &&
            state != BdkAddressDerivationValidationState.RejectedMainnet &&
            BdkAddressDerivationValidationCapability.NoProductionStorage !in capabilities

    val producesMainnetAddress: Boolean
        get() = listOfNotNull(firstAddress, recoveredFirstAddress, secondAddress)
            .any { it.network == BdkAddressDerivationValidationNetwork.MainnetDisabled }
}

data class BdkAddressDerivationValidationPolicy(
    val enabled: Boolean,
) {
    companion object {
        const val EnvironmentVariable = "SKALD_RUN_BDK_REGTEST_ADDRESS_DERIVATION"

        fun fromEnvironment(
            environment: Map<String, String> = System.getenv(),
        ): BdkAddressDerivationValidationPolicy =
            BdkAddressDerivationValidationPolicy(enabled = environment[EnvironmentVariable] == "1")
    }
}

object BdkRegtestAddressDerivationValidation {
    fun runFromEnvironment(
        environment: Map<String, String> = System.getenv(),
    ): BdkAddressDerivationValidationResult =
        run(
            BdkAddressDerivationValidationRequest(
                enabled = BdkAddressDerivationValidationPolicy.fromEnvironment(environment).enabled,
                network = BdkAddressDerivationValidationNetwork.Regtest,
            ),
        )

    fun run(
        request: BdkAddressDerivationValidationRequest,
    ): BdkAddressDerivationValidationResult {
        if (!request.enabled) {
            return BdkAddressDerivationValidationResult(
                state = BdkAddressDerivationValidationState.Disabled,
                network = null,
                checks = emptyList(),
                capabilities = emptySet(),
                firstAddress = null,
                recoveredFirstAddress = null,
                secondAddress = null,
                deterministicFirstAddressMatched = false,
                differentIndexProducedDifferentAddress = false,
                error = BdkAddressDerivationValidationError(
                    code = "BDK_REGTEST_ADDRESS_DERIVATION_DISABLED",
                    safeDetail = "Set ${BdkAddressDerivationValidationPolicy.EnvironmentVariable}=1 to run the test-only BDK regtest address derivation validation.",
                ),
                diagnostic = "BDK address derivation validation is disabled unless explicitly requested for desktop tests.",
            )
        }

        if (request.network == BdkAddressDerivationValidationNetwork.MainnetDisabled) {
            return BdkAddressDerivationValidationResult(
                state = BdkAddressDerivationValidationState.RejectedMainnet,
                network = request.network,
                checks = listOf(BdkAddressDerivationValidationCheck.ExplicitOptIn),
                capabilities = defaultCapabilities(),
                firstAddress = null,
                recoveredFirstAddress = null,
                secondAddress = null,
                deterministicFirstAddressMatched = false,
                differentIndexProducedDifferentAddress = false,
                error = BdkAddressDerivationValidationError(
                    code = "BDK_ADDRESS_DERIVATION_MAINNET_REJECTED",
                    safeDetail = "Mainnet is disabled. Test-only BDK address derivation is allowed only for development networks.",
                ),
                diagnostic = "BDK address derivation refused mainnet before creating runtime wallet material.",
            )
        }

        val checks = mutableListOf(
            BdkAddressDerivationValidationCheck.ExplicitOptIn,
            BdkAddressDerivationValidationCheck.DevelopmentNetworkSelected,
        )
        return runCatching {
            val entropy = runtimeEntropy().also {
                checks += BdkAddressDerivationValidationCheck.RuntimeEntropyCreated
            }
            try {
                val createdEntropy = entropy.copyOf()
                val recoveredEntropy = entropy.copyOf()
                try {
                    val created = deriveAddresses(
                        entropy = createdEntropy,
                        network = request.network,
                        checks = checks,
                        recovered = false,
                    )
                    val recovered = deriveAddresses(
                        entropy = recoveredEntropy,
                        network = request.network,
                        checks = checks,
                        recovered = true,
                    )
                    val firstMatched = created.first.addressText == recovered.first.addressText
                    val secondDifferent = created.first.addressText != created.second.addressText
                    if (firstMatched) {
                        checks += BdkAddressDerivationValidationCheck.DeterministicFirstAddressMatched
                    }
                    if (secondDifferent) {
                        checks += BdkAddressDerivationValidationCheck.DifferentIndexProducedDifferentAddress
                    }
                    checks += BdkAddressDerivationValidationCheck.NoProductionStorageUsed

                    BdkAddressDerivationValidationResult(
                        state = if (firstMatched && secondDifferent) {
                            BdkAddressDerivationValidationState.Completed
                        } else {
                            BdkAddressDerivationValidationState.Failed
                        },
                        network = request.network,
                        checks = checks.distinct(),
                        capabilities = defaultCapabilities(),
                        firstAddress = created.first.toSanitized(request.network),
                        recoveredFirstAddress = recovered.first.toSanitized(request.network),
                        secondAddress = created.second.toSanitized(request.network),
                        deterministicFirstAddressMatched = firstMatched,
                        differentIndexProducedDifferentAddress = secondDifferent,
                        error = if (firstMatched && secondDifferent) {
                            null
                        } else {
                            BdkAddressDerivationValidationError(
                                code = "BDK_ADDRESS_DERIVATION_DETERMINISM_MISMATCH",
                                safeDetail = "Test-only BDK address derivation did not match the expected deterministic comparison. Address values were not included in this diagnostic.",
                            )
                        },
                        diagnostic = "Test-only BDK address derivation completed offline with redacted Skald-owned status.",
                    )
                } finally {
                    createdEntropy.fill(0)
                    recoveredEntropy.fill(0)
                }
            } finally {
                entropy.fill(0)
            }
        }.getOrElse {
            val stage = safeFailureStage(it)
            val nativeBindingUnavailable = it is UnsatisfiedLinkError
            BdkAddressDerivationValidationResult(
                state = if (nativeBindingUnavailable) {
                    BdkAddressDerivationValidationState.BlockedByBdkJvmNativeBinding
                } else {
                    BdkAddressDerivationValidationState.Failed
                },
                network = request.network,
                checks = checks.distinct(),
                capabilities = defaultCapabilities(),
                firstAddress = null,
                recoveredFirstAddress = null,
                secondAddress = null,
                deterministicFirstAddressMatched = false,
                differentIndexProducedDifferentAddress = false,
                error = BdkAddressDerivationValidationError(
                    code = if (nativeBindingUnavailable) {
                        "BDK_ADDRESS_DERIVATION_NATIVE_BINDING_UNAVAILABLE"
                    } else {
                        "BDK_ADDRESS_DERIVATION_FAILED"
                    },
                    safeDetail = "BDK address derivation validation failed during $stage. Raw library error text was not exposed.",
                ),
                diagnostic = if (nativeBindingUnavailable) {
                    "Test-only BDK address derivation is blocked because the resolved BDK JVM artifact cannot load a Linux native binding."
                } else {
                    "Test-only BDK address derivation failed with a redacted Skald-owned error."
                },
            )
        }
    }

    private fun runtimeEntropy(): ByteArray =
        ByteArray(16).also { SecureRandom().nextBytes(it) }

    private fun deriveAddresses(
        entropy: ByteArray,
        network: BdkAddressDerivationValidationNetwork,
        checks: MutableList<BdkAddressDerivationValidationCheck>,
        recovered: Boolean,
    ): InternalDerivedAddressSet {
        val closeBag = BdkAddressValidationCloseBag()
        var stage = "creating runtime mnemonic"
        return try {
            val bdkNetwork = network.toBdkNetwork()
            val mnemonicEntropy = entropy.copyOf()
            val mnemonic = closeBag.track(
                try {
                    Mnemonic.fromEntropy(mnemonicEntropy)
                } finally {
                    mnemonicEntropy.fill(0)
                },
            ).also {
                checks += BdkAddressDerivationValidationCheck.RuntimeMnemonicCreated
            }
            stage = "creating runtime descriptor secret"
            val rootSecret = closeBag.track(DescriptorSecretKey(bdkNetwork, mnemonic, "")).also {
                checks += BdkAddressDerivationValidationCheck.RuntimeDescriptorSecretCreated
            }
            stage = "creating BIP86 descriptors"
            val externalDescriptor = closeBag.track(
                Descriptor.newBip86(rootSecret, KeychainKind.EXTERNAL, bdkNetwork),
            )
            val internalDescriptor = closeBag.track(
                Descriptor.newBip86(rootSecret, KeychainKind.INTERNAL, bdkNetwork),
            )
            checks += BdkAddressDerivationValidationCheck.Bip86DescriptorsCreated

            stage = "creating in-memory BDK persister"
            val persister = closeBag.track(Persister.newInMemory()).also {
                checks += BdkAddressDerivationValidationCheck.InMemoryPersisterCreated
            }
            stage = "creating test-only BDK wallet"
            val wallet = closeBag.track(
                Wallet(
                    descriptor = externalDescriptor,
                    changeDescriptor = internalDescriptor,
                    network = bdkNetwork,
                    persister = persister,
                ),
            ).also {
                checks += BdkAddressDerivationValidationCheck.BdkWalletCreated
            }

            stage = "peeking first receive address"
            val first = wallet.peekReceiveAddress(
                network = bdkNetwork,
                index = 0,
                validationNetwork = network,
                check = if (recovered) {
                    BdkAddressDerivationValidationCheck.RecoveredFirstReceiveAddressPeeked
                } else {
                    BdkAddressDerivationValidationCheck.FirstReceiveAddressPeeked
                },
                checks = checks,
            )
            stage = "peeking second receive address"
            val second = wallet.peekReceiveAddress(
                network = bdkNetwork,
                index = 1,
                validationNetwork = network,
                check = BdkAddressDerivationValidationCheck.SecondReceiveAddressPeeked,
                checks = checks,
            )
            InternalDerivedAddressSet(first = first, second = second)
        } catch (failure: Exception) {
            throw BdkAddressDerivationStageException(stage)
        } finally {
            entropy.fill(0)
            closeBag.closeAll()
        }
    }

    private fun Wallet.peekReceiveAddress(
        network: Network,
        index: Int,
        validationNetwork: BdkAddressDerivationValidationNetwork,
        check: BdkAddressDerivationValidationCheck,
        checks: MutableList<BdkAddressDerivationValidationCheck>,
    ): InternalDerivedAddress {
        var addressInfo: AddressInfo? = null
        return try {
            addressInfo = peekAddress(KeychainKind.EXTERNAL, index.toUInt()).also {
                checks += check
            }
            InternalDerivedAddress(
                keychain = addressInfo.keychain.name,
                derivationIndex = addressInfo.index.toInt(),
                addressText = addressInfo.address.toString(),
                validForNetwork = addressInfo.address.isValidForNetwork(network),
                network = validationNetwork,
            )
        } finally {
            addressInfo?.destroy()
        }
    }

    private fun BdkAddressDerivationValidationNetwork.toBdkNetwork(): Network =
        when (this) {
            BdkAddressDerivationValidationNetwork.Regtest -> Network.REGTEST
            BdkAddressDerivationValidationNetwork.Signet -> Network.SIGNET
            BdkAddressDerivationValidationNetwork.MainnetDisabled ->
                error("Mainnet must be rejected before BDK network mapping.")
        }

    private fun defaultCapabilities(): Set<BdkAddressDerivationValidationCapability> =
        setOf(
            BdkAddressDerivationValidationCapability.TestOnlyBdkWalletCreation,
            BdkAddressDerivationValidationCapability.TestOnlyAddressDerivation,
            BdkAddressDerivationValidationCapability.TestOnlySeedRecovery,
            BdkAddressDerivationValidationCapability.DevelopmentNetworksOnly,
            BdkAddressDerivationValidationCapability.InMemoryBdkPersister,
            BdkAddressDerivationValidationCapability.SkaldOwnedResultTypes,
            BdkAddressDerivationValidationCapability.RedactedDiagnostics,
            BdkAddressDerivationValidationCapability.NoProductionStorage,
            BdkAddressDerivationValidationCapability.NoBackendNetworking,
            BdkAddressDerivationValidationCapability.NoWalletSync,
            BdkAddressDerivationValidationCapability.NoUtxoScan,
            BdkAddressDerivationValidationCapability.NoSigning,
            BdkAddressDerivationValidationCapability.NoBroadcasting,
        )

    private fun safeFailureStage(failure: Throwable): String =
        when (failure) {
            is BdkAddressDerivationStageException -> failure.safeStage
            is UnsatisfiedLinkError -> "BDK JVM native binding linkage"
            is NoSuchMethodError -> "BDK JVM method linkage"
            is LinkageError -> "BDK JVM linkage"
            is SecurityException -> "JVM security policy"
            else -> "unknown test-only BDK address derivation stage"
        }
}

private data class InternalDerivedAddressSet(
    val first: InternalDerivedAddress,
    val second: InternalDerivedAddress,
)

private data class InternalDerivedAddress(
    val network: BdkAddressDerivationValidationNetwork,
    val keychain: String,
    val derivationIndex: Int,
    val addressText: String,
    val validForNetwork: Boolean,
) {
    fun toSanitized(network: BdkAddressDerivationValidationNetwork): SanitizedDerivedAddress =
        SanitizedDerivedAddress(
            network = network,
            keychain = keychain,
            derivationIndex = derivationIndex,
            addressText = addressText,
            validForNetwork = validForNetwork,
            runtimeGenerated = true,
            secretMaterialExposed = false,
        )
}

private class BdkAddressDerivationStageException(
    val safeStage: String,
) : RuntimeException()

private class BdkAddressValidationCloseBag {
    private val closeables = mutableListOf<AutoCloseable>()

    fun <T : AutoCloseable> track(closeable: T): T =
        closeable.also { closeables += it }

    fun closeAll() {
        closeables.asReversed().forEach { closeable ->
            runCatching { closeable.close() }
        }
    }
}
