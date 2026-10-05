package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledSecureSecretStorage
import com.libertasprimordium.skald.ui.SkaldShellState
import com.libertasprimordium.skald.ui.navigation.AppScreen
import com.libertasprimordium.skald.ui.navigation.NavigationIconSpec
import com.libertasprimordium.skald.ui.navigation.SkaldNavigationModel
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class NavigationModelTest {
    @Test
    fun temporaryDestinationsPreservePrimaryAndOverflowPlacement() {
        assertEquals(listOf(AppScreen.Overview, AppScreen.Wallet), SkaldNavigationModel.PrimaryScreens)
        assertEquals(listOf(AppScreen.Recovery, AppScreen.Connection, AppScreen.Settings), SkaldNavigationModel.MenuScreens)
        val grouped = SkaldNavigationModel.PrimaryScreens + SkaldNavigationModel.MenuScreens
        assertEquals(AppScreen.entries.toSet(), grouped.toSet())
        assertEquals(5, grouped.size)
        assertEquals(grouped.size, grouped.distinct().size)
    }

    @Test
    fun everyNavigationDestinationDrivesTheActualShellPageAndCanReturnToOverview() {
        val start = SkaldShellState.start(DisabledSecureSecretStorage())
        val destinations = SkaldNavigationModel.PrimaryScreens + SkaldNavigationModel.MenuScreens
        destinations.forEach { destination ->
            val selected = start.navigate(destination)
            assertEquals(destination, selected.selectedScreen)
            assertEquals(destination.label, selected.page.title)
            assertEquals(AppScreen.Overview, selected.navigate(AppScreen.Overview).selectedScreen)
            assertEquals("Overview", selected.navigate(AppScreen.Overview).page.title)
        }
        assertEquals(AppScreen.Overview, start.selectedScreen)
    }

    @Test
    fun survivingPrimaryArtworkAndAccessibilityRemainMappedToTheirDestinations() {
        val tabs = SkaldNavigationModel.PrimaryTabs
        assertEquals(SkaldNavigationModel.PrimaryScreens, tabs.map { it.screen })
        assertEquals(listOf(NavigationIconSpec.Eye, NavigationIconSpec.LinkedBoxes), tabs.map { it.icon })
        assertTrue(tabs.all { it.accessibilityLabel == it.screen.label })
    }
}
