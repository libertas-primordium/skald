package com.libertasprimordium.skald.bdk

import org.bitcoindevkit.Descriptor
import org.bitcoindevkit.DescriptorSecretKey
import org.bitcoindevkit.KeychainKind
import org.bitcoindevkit.Mnemonic
import org.bitcoindevkit.Network
import org.bitcoindevkit.Persister
import org.bitcoindevkit.Wallet
import java.security.SecureRandom

enum class BdkRegtestWalletValidationNetwork(val label: String) {
    Regtest("regtest"),
    MainnetDisabled("mainnet disabled"),
}

enum class BdkRegtestWalletValidationState(val label: String) {
    Disabled("disabled"),
    Completed("completed"),
    BlockedByBdkJvmNativeBinding("blocked by BDK JVM native binding"),
    RejectedMainnet("rejected mainnet"),
    Failed("failed"),
}

enum class BdkRegtestWalletValidationCheck(val label: String) {
    ExplicitOptIn("explicit validation opt-in"),
    RegtestNetworkSelected("regtest network selected"),
    RuntimeEntropyCreated("runtime-only entropy created"),
    RuntimeMnemonicCreated("runtime-only mnemonic created"),
    RuntimeDescriptorSecretCreated("runtime-only descriptor secret created"),
    Bip86DescriptorsCreated("test-only BIP86 descriptors created"),
    InMemoryPersisterCreated("BDK in-memory persister created"),
    BdkWalletCreated("test-only BDK wallet created"),
    BdkWalletRecovered("test-only BDK wallet recovered from same runtime seed"),
    SanitizedIdentityMatched("sanitized wallet identity matched"),
    NoProductionStorageUsed("no production storage used"),
}

enum class BdkRegtestWalletValidationCapability(val label: String) {
    TestOnlyBdkWalletCreation("test-only BDK wallet creation"),
    TestOnlySeedRecovery("test-only seed recovery validation"),
    RegtestOnly("regtest only"),
    InMemoryBdkPersister("BDK in-memory persister"),
    SkaldOwnedResultTypes("Skald-owned result types"),
    RedactedDiagnostics("redacted diagnostics"),
    NoProductionStorage("no production storage"),
    NoBackendNetworking("no backend networking"),
    NoAddressDerivation("no address derivation"),
    NoSigning("no signing"),
    NoBroadcasting("no broadcasting"),
}

data class SanitizedRegtestWalletIdentity(
    val network: BdkRegtestWalletValidationNetwork,
    val recoveredMatchesCreated: Boolean,
    val descriptorMaterialExposed: Boolean,
    val secretMaterialExposed: Boolean,
    val publicAddressExposed: Boolean,
    val identityDisplay: String = "REDACTED_TEST_ONLY_WALLET_IDENTITY",
)

data class BdkRegtestWalletValidationError(
    val code: String,
    val safeDetail: String,
)

data class BdkRegtestWalletValidationRequest(
    val enabled: Boolean,
    val network: BdkRegtestWalletValidationNetwork,
)

data class BdkRegtestWalletValidationResult(
    val state: BdkRegtestWalletValidationState,
    val checks: List<BdkRegtestWalletValidationCheck>,
    val capabilities: Set<BdkRegtestWalletValidationCapability>,
    val identity: SanitizedRegtestWalletIdentity?,
    val error: BdkRegtestWalletValidationError?,
    val diagnostic: String,
) {
    val completed: Boolean
        get() = state == BdkRegtestWalletValidationState.Completed

    val usesProductionStorage: Boolean
        get() = state != BdkRegtestWalletValidationState.Disabled &&
            state != BdkRegtestWalletValidationState.RejectedMainnet &&
            BdkRegtestWalletValidationCapability.NoProductionStorage !in capabilities

    val exposesWalletMaterial: Boolean
        get() = identity?.let {
            it.descriptorMaterialExposed || it.secretMaterialExposed || it.publicAddressExposed
        } ?: false
}

data class BdkRegtestWalletValidationPolicy(
    val enabled: Boolean,
) {
    companion object {
        const val EnvironmentVariable = "SKALD_RUN_BDK_REGTEST_WALLET_VALIDATION"

        fun fromEnvironment(
            environment: Map<String, String> = System.getenv(),
        ): BdkRegtestWalletValidationPolicy =
            BdkRegtestWalletValidationPolicy(enabled = environment[EnvironmentVariable] == "1")
    }
}

object BdkRegtestSeedWalletValidation {
    fun runFromEnvironment(
        environment: Map<String, String> = System.getenv(),
    ): BdkRegtestWalletValidationResult =
        run(
            BdkRegtestWalletValidationRequest(
                enabled = BdkRegtestWalletValidationPolicy.fromEnvironment(environment).enabled,
                network = BdkRegtestWalletValidationNetwork.Regtest,
            ),
        )

    fun run(
        request: BdkRegtestWalletValidationRequest,
    ): BdkRegtestWalletValidationResult {
        if (!request.enabled) {
            return BdkRegtestWalletValidationResult(
                state = BdkRegtestWalletValidationState.Disabled,
                checks = emptyList(),
                capabilities = emptySet(),
                identity = null,
                error = BdkRegtestWalletValidationError(
                    code = "BDK_REGTEST_WALLET_VALIDATION_DISABLED",
                    safeDetail = "Set ${BdkRegtestWalletValidationPolicy.EnvironmentVariable}=1 to run the test-only seed-backed BDK regtest wallet validation.",
                ),
                diagnostic = "Seed-backed BDK wallet validation is disabled unless explicitly requested for desktop tests.",
            )
        }

        if (request.network != BdkRegtestWalletValidationNetwork.Regtest) {
            return BdkRegtestWalletValidationResult(
                state = BdkRegtestWalletValidationState.RejectedMainnet,
                checks = listOf(BdkRegtestWalletValidationCheck.ExplicitOptIn),
                capabilities = defaultCapabilities(),
                identity = null,
                error = BdkRegtestWalletValidationError(
                    code = "BDK_REGTEST_WALLET_VALIDATION_MAINNET_REJECTED",
                    safeDetail = "Mainnet is disabled. Seed-backed BDK validation is allowed only for test-only regtest.",
                ),
                diagnostic = "BDK wallet validation refused a non-regtest network before creating runtime wallet material.",
            )
        }

        val checks = mutableListOf(
            BdkRegtestWalletValidationCheck.ExplicitOptIn,
            BdkRegtestWalletValidationCheck.RegtestNetworkSelected,
        )
        return runCatching {
            val entropy = runtimeEntropy().also {
                checks += BdkRegtestWalletValidationCheck.RuntimeEntropyCreated
            }
            try {
                val created = createIdentity(entropy, checks, recovered = false)
                val recovered = createIdentity(entropy.copyOf(), checks, recovered = true)
                val matched = created == recovered
                if (matched) {
                    checks += BdkRegtestWalletValidationCheck.SanitizedIdentityMatched
                }
                checks += BdkRegtestWalletValidationCheck.NoProductionStorageUsed

                BdkRegtestWalletValidationResult(
                    state = if (matched) {
                        BdkRegtestWalletValidationState.Completed
                    } else {
                        BdkRegtestWalletValidationState.Failed
                    },
                    checks = checks.distinct(),
                    capabilities = defaultCapabilities(),
                    identity = SanitizedRegtestWalletIdentity(
                        network = BdkRegtestWalletValidationNetwork.Regtest,
                        recoveredMatchesCreated = matched,
                        descriptorMaterialExposed = false,
                        secretMaterialExposed = false,
                        publicAddressExposed = false,
                    ),
                    error = if (matched) {
                        null
                    } else {
                        BdkRegtestWalletValidationError(
                            code = "BDK_REGTEST_WALLET_IDENTITY_MISMATCH",
                            safeDetail = "Recovered test-only BDK wallet identity did not match the created identity.",
                        )
                    },
                    diagnostic = "Test-only seed-backed BDK regtest wallet creation and recovery validation completed without exposing wallet material.",
                )
            } finally {
                entropy.fill(0)
            }
        }.getOrElse {
            val stage = safeFailureStage(it)
            val nativeBindingUnavailable = it is UnsatisfiedLinkError
            BdkRegtestWalletValidationResult(
                state = if (nativeBindingUnavailable) {
                    BdkRegtestWalletValidationState.BlockedByBdkJvmNativeBinding
                } else {
                    BdkRegtestWalletValidationState.Failed
                },
                checks = checks.distinct(),
                capabilities = defaultCapabilities(),
                identity = null,
                error = BdkRegtestWalletValidationError(
                    code = if (nativeBindingUnavailable) {
                        "BDK_REGTEST_WALLET_VALIDATION_NATIVE_BINDING_UNAVAILABLE"
                    } else {
                        "BDK_REGTEST_WALLET_VALIDATION_FAILED"
                    },
                    safeDetail = "BDK regtest wallet validation failed during $stage. Raw library error text was not exposed.",
                ),
                diagnostic = if (nativeBindingUnavailable) {
                    "Test-only BDK wallet validation is blocked because the resolved BDK JVM artifact cannot load a Linux native binding."
                } else {
                    "Test-only BDK wallet validation failed with a redacted Skald-owned error."
                },
            )
        }
    }

    private fun runtimeEntropy(): ByteArray =
        ByteArray(16).also { SecureRandom().nextBytes(it) }

    private fun createIdentity(
        entropy: ByteArray,
        checks: MutableList<BdkRegtestWalletValidationCheck>,
        recovered: Boolean,
    ): InternalWalletIdentity {
        val closeBag = BdkCloseBag()
        var stage = "creating runtime mnemonic"
        return try {
            val mnemonicEntropy = entropy.copyOf()
            val mnemonic = closeBag.track(
                try {
                    Mnemonic.fromEntropy(mnemonicEntropy)
                } finally {
                    mnemonicEntropy.fill(0)
                },
            ).also {
                checks += BdkRegtestWalletValidationCheck.RuntimeMnemonicCreated
            }
            stage = "creating runtime descriptor secret"
            val rootSecret = closeBag.track(DescriptorSecretKey(Network.REGTEST, mnemonic, "")).also {
                checks += BdkRegtestWalletValidationCheck.RuntimeDescriptorSecretCreated
            }
            stage = "creating BIP86 descriptors"
            val externalDescriptor = closeBag.track(
                Descriptor.newBip86(rootSecret, KeychainKind.EXTERNAL, Network.REGTEST),
            )
            val internalDescriptor = closeBag.track(
                Descriptor.newBip86(rootSecret, KeychainKind.INTERNAL, Network.REGTEST),
            )
            checks += BdkRegtestWalletValidationCheck.Bip86DescriptorsCreated

            stage = "creating in-memory BDK persister"
            val persister = closeBag.track(Persister.newInMemory()).also {
                checks += BdkRegtestWalletValidationCheck.InMemoryPersisterCreated
            }
            stage = "creating test-only BDK wallet"
            val wallet = closeBag.track(
                Wallet(
                    descriptor = externalDescriptor,
                    changeDescriptor = internalDescriptor,
                    network = Network.REGTEST,
                    persister = persister,
                ),
            ).also {
                checks += BdkRegtestWalletValidationCheck.BdkWalletCreated
            }
            stage = "calculating sanitized wallet identity"
            val identity = InternalWalletIdentity(
                networkName = wallet.network().name,
                externalDescriptorId = externalDescriptor.descriptorId().toString(),
                internalDescriptorId = internalDescriptor.descriptorId().toString(),
                externalDescriptorChecksum = wallet.descriptorChecksum(KeychainKind.EXTERNAL),
                internalDescriptorChecksum = wallet.descriptorChecksum(KeychainKind.INTERNAL),
            )
            if (recovered) {
                checks += BdkRegtestWalletValidationCheck.BdkWalletRecovered
            }
            identity
        } catch (failure: Exception) {
            throw BdkValidationStageException(stage)
        } finally {
            entropy.fill(0)
            closeBag.closeAll()
        }
    }

    private fun defaultCapabilities(): Set<BdkRegtestWalletValidationCapability> =
        setOf(
            BdkRegtestWalletValidationCapability.TestOnlyBdkWalletCreation,
            BdkRegtestWalletValidationCapability.TestOnlySeedRecovery,
            BdkRegtestWalletValidationCapability.RegtestOnly,
            BdkRegtestWalletValidationCapability.InMemoryBdkPersister,
            BdkRegtestWalletValidationCapability.SkaldOwnedResultTypes,
            BdkRegtestWalletValidationCapability.RedactedDiagnostics,
            BdkRegtestWalletValidationCapability.NoProductionStorage,
            BdkRegtestWalletValidationCapability.NoBackendNetworking,
            BdkRegtestWalletValidationCapability.NoAddressDerivation,
            BdkRegtestWalletValidationCapability.NoSigning,
            BdkRegtestWalletValidationCapability.NoBroadcasting,
        )

    private fun safeFailureStage(failure: Throwable): String =
        when (failure) {
            is BdkValidationStageException -> failure.safeStage
            is UnsatisfiedLinkError -> "BDK JVM native binding linkage"
            is NoSuchMethodError -> "BDK JVM method linkage"
            is LinkageError -> "BDK JVM linkage"
            is SecurityException -> "JVM security policy"
            else -> "unknown test-only BDK validation stage"
        }
}

private data class InternalWalletIdentity(
    val networkName: String,
    val externalDescriptorId: String,
    val internalDescriptorId: String,
    val externalDescriptorChecksum: String,
    val internalDescriptorChecksum: String,
)

private class BdkValidationStageException(
    val safeStage: String,
) : RuntimeException()

private class BdkCloseBag {
    private val closeables = mutableListOf<AutoCloseable>()

    fun <T : AutoCloseable> track(closeable: T): T =
        closeable.also { closeables += it }

    fun closeAll() {
        closeables.asReversed().forEach { closeable ->
            runCatching { closeable.close() }
        }
    }
}
