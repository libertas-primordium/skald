package com.libertasprimordium.skald

import com.libertasprimordium.skald.ui.navigation.AppScreen
import com.libertasprimordium.skald.ui.navigation.SkaldNavigationModel
import kotlin.test.Test
import kotlin.test.assertEquals

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
}
