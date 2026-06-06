package com.libertasprimordium.skald.domain.onchain.bdk

import com.libertasprimordium.skald.domain.core.NetworkEnvironment

data class BdkAdapterVersion(
    val version: String,
    val androidArtifact: String,
    val desktopArtifact: String,
) {
    companion object {
        val Pinned: BdkAdapterVersion = BdkAdapterVersion(
            version = "2.3.1",
            androidArtifact = "org.bitcoindevkit:bdk-android:2.3.1",
            desktopArtifact = "org.bitcoindevkit:bdk-jvm:2.3.1",
        )
    }
}

enum class BdkAdapterPlatform(val label: String) {
    Android("Android"),
    DesktopJvm("Linux desktop JVM"),
}

enum class BdkAdapterProbeState(val label: String) {
    Linked("platform dependency linked"),
    Failed("platform dependency probe failed"),
}

enum class BdkAdapterCapability(
    val label: String,
    val enabledInThisPass: Boolean,
    val isWalletOperation: Boolean,
    val touchesWalletMaterial: Boolean,
) {
    PlatformDependencyLinked(
        label = "BDK platform dependency linked",
        enabledInThisPass = true,
        isWalletOperation = false,
        touchesWalletMaterial = false,
    ),
    DevelopmentNetworkEnumProbe(
        label = "development-network enum probe",
        enabledInThisPass = true,
        isWalletOperation = false,
        touchesWalletMaterial = false,
    ),
    SkaldOwnedErrorTranslation(
        label = "Skald-owned error translation",
        enabledInThisPass = true,
        isWalletOperation = false,
        touchesWalletMaterial = false,
    ),
    WalletCreation(
        label = "wallet creation",
        enabledInThisPass = false,
        isWalletOperation = true,
        touchesWalletMaterial = true,
    ),
    KeyGeneration(
        label = "key generation",
        enabledInThisPass = false,
        isWalletOperation = true,
        touchesWalletMaterial = true,
    ),
    DescriptorParsing(
        label = "descriptor parsing",
        enabledInThisPass = false,
        isWalletOperation = true,
        touchesWalletMaterial = true,
    ),
    AddressDerivation(
        label = "address derivation",
        enabledInThisPass = false,
        isWalletOperation = true,
        touchesWalletMaterial = false,
    ),
    WalletSync(
        label = "wallet sync",
        enabledInThisPass = false,
        isWalletOperation = true,
        touchesWalletMaterial = false,
    ),
    PsbtConstruction(
        label = "PSBT construction",
        enabledInThisPass = false,
        isWalletOperation = true,
        touchesWalletMaterial = false,
    ),
    Signing(
        label = "signing",
        enabledInThisPass = false,
        isWalletOperation = true,
        touchesWalletMaterial = true,
    ),
    Broadcasting(
        label = "broadcasting",
        enabledInThisPass = false,
        isWalletOperation = true,
        touchesWalletMaterial = false,
    ),
    BdkPersistence(
        label = "BDK persistence",
        enabledInThisPass = false,
        isWalletOperation = true,
        touchesWalletMaterial = true,
    ),
    BackendNetworking(
        label = "backend networking",
        enabledInThisPass = false,
        isWalletOperation = true,
        touchesWalletMaterial = false,
    ),
    MainnetOperation(
        label = "mainnet operation",
        enabledInThisPass = false,
        isWalletOperation = true,
        touchesWalletMaterial = false,
    ),
}

enum class BdkAdapterBlockingIssue(val label: String) {
    WalletFunctionalityNotEnabled("wallet functionality is not enabled"),
    SecureStorageDisabled("secure storage remains disabled"),
    BdkPersistenceDisabled("BDK persistence is disabled"),
    BackendNetworkingDisabled("backend networking is disabled"),
    MainnetDisabled("mainnet remains disabled by Skald policy"),
}

data class BdkAdapterNetworkProbe(
    val skaldNetwork: NetworkEnvironment,
    val adapterNetworkName: String,
    val developmentOnly: Boolean,
) {
    val allowsMainnetOperations: Boolean
        get() = skaldNetwork.allowsMainnetOperations || !developmentOnly
}

sealed interface BdkAdapterError {
    val code: String
    val safeDetail: String

    data class PlatformBindingUnavailable(
        override val safeDetail: String,
    ) : BdkAdapterError {
        override val code: String = "BDK_PLATFORM_BINDING_UNAVAILABLE"
    }

    data class UnexpectedProbeBehavior(
        override val safeDetail: String,
    ) : BdkAdapterError {
        override val code: String = "BDK_PROBE_UNEXPECTED_BEHAVIOR"
    }
}

data class BdkAdapterProbeResult(
    val platform: BdkAdapterPlatform,
    val version: BdkAdapterVersion,
    val state: BdkAdapterProbeState,
    val activeCapabilities: Set<BdkAdapterCapability>,
    val disabledCapabilities: Set<BdkAdapterCapability>,
    val blockingIssues: Set<BdkAdapterBlockingIssue>,
    val networkProbes: List<BdkAdapterNetworkProbe>,
    val error: BdkAdapterError?,
    val diagnostic: String,
) {
    val walletOperationsEnabled: Boolean
        get() = (activeCapabilities + disabledCapabilities).any {
            it.enabledInThisPass && it.isWalletOperation
        }

    val mainnetEnabled: Boolean
        get() = networkProbes.any { it.allowsMainnetOperations } ||
            BdkAdapterCapability.MainnetOperation in activeCapabilities

    val statusLabel: String
        get() = when (state) {
            BdkAdapterProbeState.Linked -> "packaging probe linked"
            BdkAdapterProbeState.Failed -> "packaging probe failed"
        }

    companion object {
        fun linked(
            platform: BdkAdapterPlatform,
            networkProbes: List<BdkAdapterNetworkProbe>,
        ): BdkAdapterProbeResult =
            BdkAdapterProbeResult(
                platform = platform,
                version = BdkAdapterVersion.Pinned,
                state = BdkAdapterProbeState.Linked,
                activeCapabilities = setOf(
                    BdkAdapterCapability.PlatformDependencyLinked,
                    BdkAdapterCapability.DevelopmentNetworkEnumProbe,
                    BdkAdapterCapability.SkaldOwnedErrorTranslation,
                ),
                disabledCapabilities = disabledWalletCapabilities,
                blockingIssues = defaultBlockingIssues,
                networkProbes = networkProbes,
                error = null,
                diagnostic = "BDK adapter package probe linked. No wallet, descriptor, backend, storage, signing, broadcast, or mainnet behavior is enabled.",
            )

        fun failed(
            platform: BdkAdapterPlatform,
            error: BdkAdapterError,
        ): BdkAdapterProbeResult =
            BdkAdapterProbeResult(
                platform = platform,
                version = BdkAdapterVersion.Pinned,
                state = BdkAdapterProbeState.Failed,
                activeCapabilities = setOf(BdkAdapterCapability.SkaldOwnedErrorTranslation),
                disabledCapabilities = disabledWalletCapabilities,
                blockingIssues = defaultBlockingIssues,
                networkProbes = emptyList(),
                error = error,
                diagnostic = "BDK adapter package probe failed. Error details are reduced to Skald-owned non-sensitive status.",
            )

        private val disabledWalletCapabilities: Set<BdkAdapterCapability> = setOf(
            BdkAdapterCapability.WalletCreation,
            BdkAdapterCapability.KeyGeneration,
            BdkAdapterCapability.DescriptorParsing,
            BdkAdapterCapability.AddressDerivation,
            BdkAdapterCapability.WalletSync,
            BdkAdapterCapability.PsbtConstruction,
            BdkAdapterCapability.Signing,
            BdkAdapterCapability.Broadcasting,
            BdkAdapterCapability.BdkPersistence,
            BdkAdapterCapability.BackendNetworking,
            BdkAdapterCapability.MainnetOperation,
        )

        private val defaultBlockingIssues: Set<BdkAdapterBlockingIssue> = setOf(
            BdkAdapterBlockingIssue.WalletFunctionalityNotEnabled,
            BdkAdapterBlockingIssue.SecureStorageDisabled,
            BdkAdapterBlockingIssue.BdkPersistenceDisabled,
            BdkAdapterBlockingIssue.BackendNetworkingDisabled,
            BdkAdapterBlockingIssue.MainnetDisabled,
        )
    }
}

object BdkAdapterProbe {
    fun run(): BdkAdapterProbeResult =
        runPlatformBdkAdapterProbe()
}

expect fun runPlatformBdkAdapterProbe(): BdkAdapterProbeResult
