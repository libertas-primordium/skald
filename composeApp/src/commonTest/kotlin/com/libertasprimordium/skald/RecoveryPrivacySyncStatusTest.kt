package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledSecureSecretStorage
import com.libertasprimordium.skald.ui.SkaldShellState
import com.libertasprimordium.skald.ui.navigation.AppScreen
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RecoveryPrivacySyncStatusTest {
    @Test
    fun recoveryPageCannotPresentScaffoldEvidenceAsRecoverableWalletState() {
        val shell = SkaldShellState.start(DisabledSecureSecretStorage()).navigate(AppScreen.Recovery)
        assertEquals("Unavailable", shell.page.status)
        assertEquals(listOf("Recover wallet", "Create backup", "Export backup"), shell.page.unavailableActions)
        assertTrue(shell.page.details.any { it.contains("No recovery material is accepted or stored") })
        assertTrue(shell.page.details.any { it.contains("seed alone may not restore wallet metadata") })
        assertTrue(shell.page.details.any { it.contains("Imported material requires its own recovery plan") })
        assertTrue(shell.page.details.any { it.contains("storage remain disabled") })
        assertEquals("disabled / not implemented", shell.secureStorageStatus.state)
    }

    @Test
    fun connectionPageReportsUnconfiguredPrivateNodeAndIrreversibleFutureDisclosure() {
        val page = SkaldShellState.start(DisabledSecureSecretStorage()).navigate(AppScreen.Connection).page
        assertEquals("Endpoint unconfigured", page.status)
        assertTrue(page.unavailableActions.isEmpty())
        assertTrue(page.details.any { it.contains("private full node with local wallet scanning") })
        assertTrue(page.details.any { it.contains("No endpoint is configured or contacted") })
        assertTrue(page.details.any { it.contains("no public default, automatic fallback") })
        assertTrue(page.details.any { it.contains("private view-key") && it.contains("wallet- and endpoint-bound privacy consent") })
        assertTrue(page.details.any { it.contains("cannot undo earlier disclosure") && it.contains("scan completeness") })
    }

    @Test
    fun navigatingBetweenRecoveryConnectionAndSettingsNeverCreatesOperationalState() {
        var shell = SkaldShellState.start(DisabledSecureSecretStorage())
        repeat(5) {
            shell = shell.navigate(AppScreen.Recovery)
            assertEquals("Unavailable", shell.page.status)
            shell = shell.navigate(AppScreen.Connection)
            assertEquals("Endpoint unconfigured", shell.page.status)
            shell = shell.navigate(AppScreen.Settings)
            assertEquals("Offline scaffold", shell.page.status)
            assertEquals("disabled / not implemented", shell.secureStorageStatus.state)
            assertTrue(shell.page.unavailableActions.isEmpty())
        }
    }
}
