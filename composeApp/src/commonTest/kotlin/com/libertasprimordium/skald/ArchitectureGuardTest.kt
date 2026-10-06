package com.libertasprimordium.skald

import com.libertasprimordium.skald.domain.security.SecretId
import com.libertasprimordium.skald.domain.security.SecretMetadata
import com.libertasprimordium.skald.security.SecureSecretStorage
import com.libertasprimordium.skald.security.SecureStorageCapability
import com.libertasprimordium.skald.security.SecureStorageResult
import com.libertasprimordium.skald.security.SecretPayload
import com.libertasprimordium.skald.security.commonDisabledSecureStorageCapability
import com.libertasprimordium.skald.security.toUiStatus
import com.libertasprimordium.skald.ui.SkaldShellState
import com.libertasprimordium.skald.ui.navigation.AppScreen
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame
import kotlin.test.assertNull
import kotlin.test.assertTrue

class ArchitectureGuardTest {
    @Test
    fun actualShellStartupReadsOnlyCapabilityMetadataAndRetainsNoStorageAccess() {
        val storage = OperationRejectingStorage()
        val initial = SkaldShellState.start(storage)
        assertEquals(1, storage.capabilityReads)
        assertEquals(AppScreen.Wallet, initial.selectedScreen)
        assertEquals(commonDisabledSecureStorageCapability().toUiStatus(), initial.secureStorageStatus)

        var state = initial
        repeat(3) {
            AppScreen.entries.forEach { destination ->
                state = state.navigate(destination)
                assertEquals(destination, state.selectedScreen)
                assertEquals(destination.label, state.page.title)
                assertSame(initial.secureStorageStatus, state.secureStorageStatus)
                assertEquals(state.page, state.page)
            }
        }
        assertEquals(1, storage.capabilityReads)
        assertEquals(0, storage.operationAttempts)
        assertEquals(AppScreen.Wallet, initial.selectedScreen)
    }

    @Test
    fun walletPresentsAnUnavailableEmptyStateWithoutRepresentingAnAmountOrHistory() {
        val shell = SkaldShellState.start(OperationRejectingStorage())
        val page = shell.page
        assertEquals("Wallet", page.title)
        assertEquals("Monero wallet", page.subtitle)
        val emptyState = page.sections.single()
        assertEquals("Wallet unavailable", emptyState.title)
        assertNull(emptyState.status)
        assertEquals(
            listOf("Wallet functionality is not implemented yet. Balances and transaction history are unavailable."),
            emptyState.paragraphs,
        )
        assertEquals("disabled / not implemented", shell.secureStorageStatus.state)
    }

    @Test
    fun repeatedPageReadsKeepTheCapabilitySnapshotAndNeverAttemptStorage() {
        val storage = OperationRejectingStorage()
        val shell = SkaldShellState.start(storage)
        repeat(5) {
            val wallet = shell.navigate(AppScreen.Wallet)
            val settings = wallet.navigate(AppScreen.Settings)
            assertEquals(listOf("Wallet unavailable"), wallet.page.sections.map { it.title })
            assertEquals(listOf("Vault", "Connection", "About"), settings.page.sections.map { it.title })
            assertSame(shell.secureStorageStatus, settings.secureStorageStatus)
            assertEquals(shell.secureStorageStatus.plannedSecretClasses, settings.secureStorageStatus.plannedSecretClasses)
            assertEquals(shell.secureStorageStatus.disabledActions, settings.secureStorageStatus.disabledActions)
        }
        assertEquals(1, storage.capabilityReads)
        assertEquals(0, storage.operationAttempts)
        assertTrue(shell.secureStorageStatus.disabledActions.isNotEmpty())
    }

}

private class OperationRejectingStorage : SecureSecretStorage {
    var capabilityReads = 0
    var operationAttempts = 0
    override val capability: SecureStorageCapability
        get() {
            capabilityReads += 1
            return commonDisabledSecureStorageCapability()
        }

    private fun forbidden(): Nothing {
        operationAttempts += 1
        error("Shell attempted a storage operation")
    }

    override fun listMetadata(): SecureStorageResult<List<SecretMetadata>> = forbidden()
    override fun putSecret(metadata: SecretMetadata, secret: SecretPayload): SecureStorageResult<SecretId> = forbidden()
    override fun getSecret(id: SecretId): SecureStorageResult<SecretPayload> = forbidden()
    override fun deleteSecret(id: SecretId): SecureStorageResult<Unit> = forbidden()
}
