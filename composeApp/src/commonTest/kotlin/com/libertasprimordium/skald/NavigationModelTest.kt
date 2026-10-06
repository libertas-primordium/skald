package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledSecureSecretStorage
import com.libertasprimordium.skald.ui.SkaldShellState
import com.libertasprimordium.skald.ui.navigation.AppScreen
import com.libertasprimordium.skald.ui.navigation.SkaldNavigationModel
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame

class NavigationModelTest {
    @Test
    fun walletAndSettingsAreTheOnlyPeerDestinations() {
        assertEquals(listOf(AppScreen.Wallet, AppScreen.Settings), AppScreen.entries)
        assertEquals(AppScreen.entries, SkaldNavigationModel.PrimaryScreens)
        assertEquals(listOf("Wallet", "Settings"), SkaldNavigationModel.PrimaryScreens.map { it.label })
    }

    @Test
    fun everyDestinationDrivesItsPageAndReturnsToWalletWithoutChangingTheSnapshot() {
        val start = SkaldShellState.start(DisabledSecureSecretStorage())
        SkaldNavigationModel.PrimaryScreens.forEach { destination ->
            val selected = start.navigate(destination)
            assertEquals(destination, selected.selectedScreen)
            assertEquals(destination.label, selected.page.title)
            assertEquals(AppScreen.Wallet, selected.navigate(AppScreen.Wallet).selectedScreen)
            assertEquals("Wallet", selected.navigate(AppScreen.Wallet).page.title)
            assertSame(start.secureStorageStatus, selected.secureStorageStatus)
        }
        assertEquals(AppScreen.Wallet, start.selectedScreen)
    }

    @Test
    fun selectingTheSameDestinationIsHarmlessAndDoesNotMutateEarlierState() {
        val start = SkaldShellState.start(DisabledSecureSecretStorage())
        AppScreen.entries.forEach { destination ->
            val selected = start.navigate(destination)
            val repeated = selected.navigate(destination)
            assertEquals(destination, repeated.selectedScreen)
            assertEquals(selected.page, repeated.page)
            assertSame(selected.secureStorageStatus, repeated.secureStorageStatus)
        }
        assertEquals(AppScreen.Wallet, start.selectedScreen)
    }
}
