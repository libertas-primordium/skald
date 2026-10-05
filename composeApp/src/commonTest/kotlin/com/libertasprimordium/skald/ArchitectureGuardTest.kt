package com.libertasprimordium.skald

import com.libertasprimordium.skald.domain.security.SecretId
import com.libertasprimordium.skald.domain.security.SecretMetadata
import com.libertasprimordium.skald.security.SecureSecretStorage
import com.libertasprimordium.skald.security.SecureStorageCapability
import com.libertasprimordium.skald.security.SecureStorageResult
import com.libertasprimordium.skald.security.SecretPayload
import com.libertasprimordium.skald.security.commonDisabledSecureStorageCapability
import com.libertasprimordium.skald.ui.SkaldShellState
import com.libertasprimordium.skald.ui.navigation.AppScreen
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame
import kotlin.test.assertTrue

class ArchitectureGuardTest {
    @Test
    fun actualShellStartupReadsOnlyCapabilityMetadataAndRetainsNoStorageAccess() {
        val storage = OperationRejectingStorage()
        val initial = SkaldShellState.start(storage)
        assertEquals(1, storage.capabilityReads)
        assertEquals(AppScreen.Overview, initial.selectedScreen)
        assertEquals("Wallet unavailable", initial.page.status)

        var state = initial
        repeat(3) {
            AppScreen.entries.forEach { destination ->
                state = state.navigate(destination)
                assertEquals(destination, state.selectedScreen)
                assertEquals(destination.label, state.page.title)
                assertSame(initial.secureStorageStatus, state.secureStorageStatus)
            }
        }
        assertEquals(1, storage.capabilityReads)
        assertEquals(0, storage.operationAttempts)
        assertEquals(AppScreen.Overview, initial.selectedScreen)
    }

    @Test
    fun noPagePresentsAnAmountOrLiveWalletState() {
        val shell = SkaldShellState.start(OperationRejectingStorage())
        AppScreen.entries.forEach { destination ->
            val page = shell.navigate(destination).page
            val text = listOf(page.title, page.subtitle, page.status) + page.details + page.unavailableActions
            assertTrue(text.none { value -> value.any(Char::isDigit) })
            assertTrue(page.status in setOf("Wallet unavailable", "Unavailable", "Endpoint unconfigured", "Offline scaffold"))
        }
        assertTrue(shell.page.details.any { it.contains("Balance and transaction history are unavailable") })
        assertTrue(shell.page.details.any { it.contains("mainnet are disabled") })
    }

    @Test
    fun unavailableWalletActionsRemainPresentationWithoutApprovalsOrOperations() {
        val storage = OperationRejectingStorage()
        val shell = SkaldShellState.start(storage)
        val wallet = shell.navigate(AppScreen.Wallet).page
        assertEquals(listOf("Create wallet", "Import wallet", "Receive", "Send"), wallet.unavailableActions)
        assertTrue(wallet.details.any { it.contains("separate signing and relay approvals") })
        assertTrue(wallet.details.any { it.contains("input, recipient, fee, change and privacy review") })
        assertTrue(wallet.details.any { it.contains("Watch-only") && it.contains("never imply signing") })
        assertTrue(shell.navigate(AppScreen.Connection).page.unavailableActions.isEmpty())
        assertEquals(0, storage.operationAttempts)
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
