package com.libertasprimordium.skald.ui.navigation

enum class AppScreen(val label: String) {
    Overview("Overview"),
    OnChain("On-chain"),
    Lightning("Lightning"),
    Cashu("Cashu"),
    Nostr("Nostr"),
    Recovery("Recovery"),
    Nodes("Nodes"),
    Settings("Settings"),
}

enum class RailIconSpec(val label: String) {
    Eye("eye"),
    LinkedBoxes("linked boxes"),
    LightningBolt("lightning bolt"),
    CashuNut("Cashu nut"),
}

enum class RailTabOptionId(val label: String) {
    DefaultView("Default rail view"),
    OverviewPrivacyAnalyzer("Privacy analyzer summary"),
    OverviewQuoteEngine("Quote Engine status"),
    OverviewNodes("Nodes / backend configuration"),
    OverviewSecureStorage("Secure storage status"),
    OverviewPhaseOneStatus("Phase 1 audit status"),
    OnChainWalletProfileManager("Wallet profile manager / import"),
    OnChainBackendSettings("Backend configuration"),
    OnChainCoinControlPlanner("Coin-control / PSBT planner"),
    OnChainRecoveryRequirements("Recovery requirements"),
    OnChainPrivacyWarnings("Privacy / coin-control warnings"),
    OnChainLockedActions("Locked action details"),
    OnChainArchitecturePlaceholders("Architecture placeholders"),
    LightningConnectorSetup("Connector setup details"),
    LightningBolt12Capabilities("BOLT12 capability details"),
    LightningBackgroundWarnings("Background service warnings"),
    LightningPermissionModel("Remote-node permission model"),
    LightningEmbeddedMode("Embedded Lightning planned mode"),
    CashuMintSettings("Add/edit mint placeholder"),
    CashuCapabilityMatrix("Mint capability matrix"),
    CashuExposureLimits("Exposure limits"),
    CashuMintMeltPlanning("Mint/melt planning"),
    CashuRecoveryWarnings("Recovery warnings"),
    CashuTrustPolicy("Trustless claim policy"),
}

data class PrimaryRailTabSpec(
    val screen: AppScreen,
    val icon: RailIconSpec,
    val accessibilityLabel: String,
)

data class RailTabOption(
    val id: RailTabOptionId,
    val destination: AppScreen? = null,
)

object SkaldNavigationModel {
    val PrimaryRailTabs: List<PrimaryRailTabSpec> = listOf(
        PrimaryRailTabSpec(
            screen = AppScreen.Overview,
            icon = RailIconSpec.Eye,
            accessibilityLabel = "Overview",
        ),
        PrimaryRailTabSpec(
            screen = AppScreen.OnChain,
            icon = RailIconSpec.LinkedBoxes,
            accessibilityLabel = "On-chain",
        ),
        PrimaryRailTabSpec(
            screen = AppScreen.Lightning,
            icon = RailIconSpec.LightningBolt,
            accessibilityLabel = "Lightning",
        ),
        PrimaryRailTabSpec(
            screen = AppScreen.Cashu,
            icon = RailIconSpec.CashuNut,
            accessibilityLabel = "Cashu",
        ),
    )

    val PrimaryScreens: List<AppScreen> = PrimaryRailTabs.map { it.screen }

    val MenuScreens: List<AppScreen> = listOf(
        AppScreen.Nostr,
        AppScreen.Recovery,
        AppScreen.Nodes,
        AppScreen.Settings,
    )

    fun optionsFor(screen: AppScreen): List<RailTabOption> =
        RailOptionsByScreen[screen].orEmpty()

    private val RailOptionsByScreen: Map<AppScreen, List<RailTabOption>> = mapOf(
        AppScreen.Overview to listOf(
            RailTabOption(RailTabOptionId.DefaultView),
            RailTabOption(RailTabOptionId.OverviewNodes, destination = AppScreen.Nodes),
            RailTabOption(RailTabOptionId.OverviewSecureStorage, destination = AppScreen.Settings),
            RailTabOption(RailTabOptionId.OverviewPrivacyAnalyzer),
            RailTabOption(RailTabOptionId.OverviewQuoteEngine),
            RailTabOption(RailTabOptionId.OverviewPhaseOneStatus),
        ),
        AppScreen.OnChain to listOf(
            RailTabOption(RailTabOptionId.DefaultView),
            RailTabOption(RailTabOptionId.OnChainWalletProfileManager),
            RailTabOption(RailTabOptionId.OnChainBackendSettings, destination = AppScreen.Nodes),
            RailTabOption(RailTabOptionId.OnChainCoinControlPlanner),
            RailTabOption(RailTabOptionId.OnChainRecoveryRequirements),
            RailTabOption(RailTabOptionId.OnChainPrivacyWarnings),
            RailTabOption(RailTabOptionId.OnChainLockedActions),
            RailTabOption(RailTabOptionId.OnChainArchitecturePlaceholders),
        ),
        AppScreen.Lightning to listOf(
            RailTabOption(RailTabOptionId.DefaultView),
            RailTabOption(RailTabOptionId.LightningConnectorSetup),
            RailTabOption(RailTabOptionId.LightningBolt12Capabilities),
            RailTabOption(RailTabOptionId.LightningBackgroundWarnings),
            RailTabOption(RailTabOptionId.LightningPermissionModel),
            RailTabOption(RailTabOptionId.LightningEmbeddedMode),
        ),
        AppScreen.Cashu to listOf(
            RailTabOption(RailTabOptionId.DefaultView),
            RailTabOption(RailTabOptionId.CashuMintSettings),
            RailTabOption(RailTabOptionId.CashuCapabilityMatrix),
            RailTabOption(RailTabOptionId.CashuExposureLimits),
            RailTabOption(RailTabOptionId.CashuMintMeltPlanning),
            RailTabOption(RailTabOptionId.CashuRecoveryWarnings),
            RailTabOption(RailTabOptionId.CashuTrustPolicy),
        ),
    )
}
