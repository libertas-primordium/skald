package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledSecureSecretStorage
import com.libertasprimordium.skald.ui.SkaldShellState
import com.libertasprimordium.skald.ui.navigation.AppScreen
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class SettingsPolicyTest {
    @Test
    fun settingsSeparatesVaultConnectionAndAboutWithoutClaimingAnInspectedVault() {
        val shell = SkaldShellState.start(DisabledSecureSecretStorage()).navigate(AppScreen.Settings)
        assertEquals("Application information", shell.page.subtitle)
        assertEquals(listOf("Vault", "Connection", "About"), shell.page.sections.map { it.title })
        val vault = shell.page.sections.single { it.title == "Vault" }
        assertEquals("Unavailable", vault.status)
        assertEquals(listOf("This build cannot create, unlock, or store data in a vault."), vault.paragraphs)
        assertEquals("disabled / not implemented", shell.secureStorageStatus.state)
        val about = shell.page.sections.single { it.title == "About" }
        assertNull(about.status)
        assertEquals(listOf("Skald Vault is a Monero wallet project for Android and Linux."), about.paragraphs)
    }

    @Test
    fun connectionPolicyPreservesPrivateNodeIntentConsentAndIrreversibleDisclosure() {
        val page = SkaldShellState.start(DisabledSecureSecretStorage()).navigate(AppScreen.Settings).page
        val connection = page.sections.single { it.title == "Connection" }
        assertEquals("Not configured", connection.status)
        assertTrue(connection.paragraphs.any { it.contains("planned default is your private full node") && it.contains("wallet scanning on this device") })
        assertTrue(connection.paragraphs.any { it.contains("No endpoint is configured or contacted") })
        assertTrue(connection.paragraphs.any { it.contains("no public default or automatic fallback") && it.contains("does not provide a connection service") })
        assertTrue(connection.paragraphs.any { it.contains("future option") && it.contains("private view-key material") && it.contains("explicit privacy consent for the wallet and endpoint") })
        assertTrue(connection.paragraphs.any { it.contains("Turning LWS off cannot undo an earlier disclosure") })
    }

    @Test
    fun settingsPresentationNeverPromotesTheRetainedDisabledStorageSnapshot() {
        val initial = SkaldShellState.start(DisabledSecureSecretStorage())
        var shell = initial
        repeat(5) {
            shell = shell.navigate(AppScreen.Settings)
            assertEquals("Unavailable", shell.page.sections.single { it.title == "Vault" }.status)
            assertEquals(initial.secureStorageStatus, shell.secureStorageStatus)
            shell = shell.navigate(AppScreen.Wallet)
            assertEquals("Wallet unavailable", shell.page.sections.single().title)
            assertEquals(initial.secureStorageStatus, shell.secureStorageStatus)
        }
    }
}
