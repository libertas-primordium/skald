package com.libertasprimordium.skald

import com.libertasprimordium.skald.domain.core.NetworkEnvironment
import com.libertasprimordium.skald.ui.navigation.AppScreen
import com.libertasprimordium.skald.ui.navigation.RailIconSpec
import com.libertasprimordium.skald.ui.navigation.RailTabOptionId
import com.libertasprimordium.skald.ui.navigation.SkaldNavigationModel
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class NavigationModelTest {
    @Test
    fun primaryScreensRemainTheFourVisibleTabs() {
        assertEquals(
            listOf(
                AppScreen.Overview,
                AppScreen.OnChain,
                AppScreen.Lightning,
                AppScreen.Cashu,
            ),
            SkaldNavigationModel.PrimaryScreens,
        )
    }

    @Test
    fun menuScreensRemainInOverflowMenu() {
        assertEquals(
            listOf(
                AppScreen.Nostr,
                AppScreen.Recovery,
                AppScreen.Nodes,
                AppScreen.Settings,
            ),
            SkaldNavigationModel.MenuScreens,
        )
    }

    @Test
    fun navigationGroupsCoverEveryScreenExactlyOnce() {
        val groupedScreens = SkaldNavigationModel.PrimaryScreens + SkaldNavigationModel.MenuScreens

        assertEquals(AppScreen.entries.toSet(), groupedScreens.toSet())
        assertEquals(AppScreen.entries.size, groupedScreens.size)
        assertEquals(groupedScreens.size, groupedScreens.distinct().size)
    }

    @Test
    fun primaryRailTabsHaveIconsAndAccessibilityLabels() {
        val tabs = SkaldNavigationModel.PrimaryRailTabs
        val iconsByScreen = tabs.associate { it.screen to it.icon }

        assertEquals(SkaldNavigationModel.PrimaryScreens, tabs.map { it.screen })
        assertEquals(RailIconSpec.Eye, iconsByScreen[AppScreen.Overview])
        assertEquals(RailIconSpec.LinkedBoxes, iconsByScreen[AppScreen.OnChain])
        assertEquals(RailIconSpec.LightningBolt, iconsByScreen[AppScreen.Lightning])
        assertEquals(RailIconSpec.CashuNut, iconsByScreen[AppScreen.Cashu])
        assertTrue(tabs.all { it.accessibilityLabel == it.screen.label })
    }

    @Test
    fun everyPrimaryRailHasOptionsMenuEntries() {
        SkaldNavigationModel.PrimaryScreens.forEach { screen ->
            val options = SkaldNavigationModel.optionsFor(screen)

            assertTrue(options.isNotEmpty(), "${screen.label} should expose rail options")
            assertContains(options.map { it.id }, RailTabOptionId.DefaultView)
        }
    }

    @Test
    fun secondaryScreensRemainReachableFromGlobalMenu() {
        val globalScreens = SkaldNavigationModel.MenuScreens.toSet()

        assertContains(globalScreens, AppScreen.Nostr)
        assertContains(globalScreens, AppScreen.Recovery)
        assertContains(globalScreens, AppScreen.Nodes)
        assertContains(globalScreens, AppScreen.Settings)
    }

    @Test
    fun recoveryScreenIsNotLinkedFromPrimaryRailOptions() {
        val railDestinations = SkaldNavigationModel.PrimaryScreens
            .flatMap { screen -> SkaldNavigationModel.optionsFor(screen).mapNotNull { it.destination } }

        assertTrue(railDestinations.none { it == AppScreen.Recovery })
    }

    @Test
    fun networkSelectorIsNotDuplicatedInGlobalMenu() {
        assertTrue(SkaldNavigationModel.MenuScreens.none { it.label.contains("Network") })
    }

    @Test
    fun settingsRemainsGlobalDestinationForDevelopmentNetworkStatus() {
        assertContains(SkaldNavigationModel.MenuScreens, AppScreen.Settings)
        assertTrue(NetworkEnvironment.entries.none { it.allowsMainnetOperations })
        assertTrue(NetworkEnvironment.MainnetDisabled.isDevelopmentSelectable.not())
    }

    @Test
    fun onChainOptionsExposeMovedAdvancedPanels() {
        val options = SkaldNavigationModel.optionsFor(AppScreen.OnChain).map { it.id }

        assertContains(options, RailTabOptionId.OnChainWalletProfileManager)
        assertContains(options, RailTabOptionId.OnChainBackendSettings)
        assertContains(options, RailTabOptionId.OnChainCoinControlPlanner)
        assertContains(options, RailTabOptionId.OnChainRecoveryRequirements)
        assertContains(options, RailTabOptionId.OnChainPrivacyWarnings)
        assertContains(options, RailTabOptionId.OnChainLockedActions)
    }

    @Test
    fun lightningAndCashuOptionsExposeSetupAndRiskPanels() {
        val lightningOptions = SkaldNavigationModel.optionsFor(AppScreen.Lightning).map { it.id }
        val cashuOptions = SkaldNavigationModel.optionsFor(AppScreen.Cashu).map { it.id }

        assertContains(lightningOptions, RailTabOptionId.LightningConnectorSetup)
        assertContains(lightningOptions, RailTabOptionId.LightningBolt12Capabilities)
        assertContains(lightningOptions, RailTabOptionId.LightningPermissionModel)
        assertContains(lightningOptions, RailTabOptionId.LightningBackgroundWarnings)
        assertContains(cashuOptions, RailTabOptionId.CashuMintSettings)
        assertContains(cashuOptions, RailTabOptionId.CashuCapabilityMatrix)
        assertContains(cashuOptions, RailTabOptionId.CashuExposureLimits)
        assertContains(cashuOptions, RailTabOptionId.CashuRecoveryWarnings)
    }
}
