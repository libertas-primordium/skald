package com.libertasprimordium.skald

import com.libertasprimordium.skald.demo.DemoPortfolioRepository
import com.libertasprimordium.skald.domain.BackendConnectionStatus
import com.libertasprimordium.skald.domain.NetworkEnvironment
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ArchitectureGuardTest {
    private val repository = DemoPortfolioRepository()

    @Test
    fun demoPortfolioIsExplicitlyMarkedAsNonLiveState() {
        val snapshot = repository.loadPortfolioSnapshot()

        assertTrue(snapshot.isDemoData)
        assertEquals(NetworkEnvironment.Testnet4, snapshot.network)
        assertFalse(snapshot.network.allowsMainnetOperations)
        assertTrue(snapshot.railBalances.all { it.isDemoBalance })
    }

    @Test
    fun developmentNetworksDoNotEnableMainnetOperations() {
        assertTrue(NetworkEnvironment.entries.none { it.allowsMainnetOperations })
        assertFalse(NetworkEnvironment.MainnetDisabled.isDevelopmentSelectable)
    }

    @Test
    fun placeholdersDoNotExposeExecutableWalletActions() {
        val snapshot = repository.loadPortfolioSnapshot()

        assertTrue(snapshot.sampleQuotes.all { it.isPlaceholder })
        assertTrue(snapshot.sampleQuotes.all { it.amountOutSats == 0L })
        assertTrue(repository.onChainProfile().disabledActions.isNotEmpty())
        assertTrue(repository.lightningConnectors().all { it.status == BackendConnectionStatus.NotConfigured })
        assertTrue(repository.cashuMints().all { it.isDemoMint })
    }
}
