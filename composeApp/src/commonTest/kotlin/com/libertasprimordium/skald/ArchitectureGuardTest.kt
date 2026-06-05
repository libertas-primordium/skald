package com.libertasprimordium.skald

import com.libertasprimordium.skald.demo.DemoOnChainRepository
import com.libertasprimordium.skald.demo.DemoPortfolioRepository
import com.libertasprimordium.skald.domain.core.BackendConnectionStatus
import com.libertasprimordium.skald.domain.core.NetworkEnvironment
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendPrivacyLevel
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendTrustModel
import com.libertasprimordium.skald.domain.onchain.DescriptorBackupStatus
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletLabel
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletOrigin
import com.libertasprimordium.skald.domain.onchain.ImportedKeyPolicy
import com.libertasprimordium.skald.domain.onchain.NotImplementedPsbtWorkflow
import com.libertasprimordium.skald.domain.onchain.PlaceholderBitcoinBackendConnectionTester
import com.libertasprimordium.skald.domain.onchain.PlaceholderDescriptorWalletService
import com.libertasprimordium.skald.domain.onchain.PsbtBroadcastPolicy
import com.libertasprimordium.skald.domain.onchain.PsbtSigningPolicy
import com.libertasprimordium.skald.domain.onchain.PsbtWorkflowState
import com.libertasprimordium.skald.domain.onchain.RecoveryBlockingIssue
import com.libertasprimordium.skald.domain.onchain.WalletOperationResult
import com.libertasprimordium.skald.domain.onchain.WatchOnlyPolicy
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ArchitectureGuardTest {
    private val portfolioRepository = DemoPortfolioRepository()
    private val onChainRepository = DemoOnChainRepository()

    @Test
    fun demoPortfolioIsExplicitlyMarkedAsNonLiveState() {
        val snapshot = portfolioRepository.loadPortfolioSnapshot()

        assertTrue(snapshot.isDemoData)
        assertEquals(NetworkEnvironment.Testnet4, snapshot.network)
        assertFalse(snapshot.network.allowsMainnetOperations)
        assertTrue(snapshot.railBalances.all { it.isDemoBalance })
    }

    @Test
    fun mainnetOperationsRemainDisabled() {
        assertTrue(NetworkEnvironment.entries.none { it.allowsMainnetOperations })
        assertFalse(NetworkEnvironment.MainnetDisabled.isDevelopmentSelectable)
    }

    @Test
    fun coinControlPolicyRequiresManualInputReview() {
        val policy = onChainRepository.loadOnChainProfile().coinControlPolicy

        assertTrue(policy.requiresManualInputReview)
        assertTrue(policy.isStrict)
    }

    @Test
    fun coinControlPolicyRequiresFeeAndChangeReview() {
        val policy = onChainRepository.loadOnChainProfile().coinControlPolicy
        val draft = onChainRepository.loadOnChainProfile().coinSelectionDraft

        assertTrue(policy.requiresFeeAndChangeReview)
        assertTrue(draft.review.feePlaceholder.networkFeeSats == null)
        assertTrue(draft.review.changePlan.amountSats == null)
    }

    @Test
    fun coinControlPolicyRequiresExplicitSigningApproval() {
        val policy = onChainRepository.loadOnChainProfile().coinControlPolicy
        val draft = onChainRepository.loadOnChainProfile().coinSelectionDraft

        assertTrue(policy.requiresExplicitSigningApproval)
        assertFalse(draft.review.approvalState.permitsSigning)
    }

    @Test
    fun coinControlPolicyRequiresExplicitBroadcastApproval() {
        val policy = onChainRepository.loadOnChainProfile().coinControlPolicy
        val draft = onChainRepository.loadOnChainProfile().coinSelectionDraft

        assertTrue(policy.requiresExplicitBroadcastApproval)
        assertFalse(draft.review.approvalState.permitsBroadcast)
    }

    @Test
    fun psbtWorkflowCannotReachSigningOrBroadcastEnabledStatesInThisPass() {
        val workflow = onChainRepository.loadOnChainProfile().psbtWorkflow

        assertTrue(PsbtWorkflowState.entries.none { it.signingEnabled || it.broadcastEnabled })
        assertTrue(PsbtSigningPolicy.entries.none { it.signingEnabled })
        assertTrue(PsbtBroadcastPolicy.entries.none { it.broadcastEnabled })
        assertFalse(workflow.currentState.signingEnabled)
        assertFalse(workflow.currentState.broadcastEnabled)
        assertEquals("PSBT_NOT_CREATED", workflow.review.draft.placeholderPsbt)
    }

    @Test
    fun backendProfilesHaveNoDefaultActiveSkaldManagedEndpoint() {
        val backends = onChainRepository.loadOnChainProfile().backendProfiles

        assertTrue(backends.all { it.noDefaultEndpoint })
        assertTrue(backends.all { it.endpointDisplay == "BACKEND_NOT_CONFIGURED" })
        assertTrue(backends.all { it.status.label == "not configured" })
        assertTrue(backends.all { it.credentialReference == null })
    }

    @Test
    fun publicBackendTrustAndPrivacyWarningIsRepresented() {
        val publicBackend = onChainRepository.loadOnChainProfile()
            .backendProfiles
            .single { it.trustModel == BitcoinBackendTrustModel.PublicBackend }

        assertEquals(BitcoinBackendPrivacyLevel.PublicBackendLeaksWalletQueries, publicBackend.privacyLevel)
        assertTrue(publicBackend.warnings.any { it.contains("Public", ignoreCase = true) })
    }

    @Test
    fun importedSingleKeyWalletsRequireSeparateBackup() {
        val importedSingleKey = onChainRepository.loadOnChainProfile()
            .descriptorWallets
            .single { it.origin == DescriptorWalletOrigin.ImportedSingleKey }

        assertEquals(DescriptorBackupStatus.SeparateKeyBackupRequired, importedSingleKey.backupStatus)
        assertEquals(ImportedKeyPolicy.SeparateBackupRequired, importedSingleKey.importedKeyPolicy)
        assertFalse(importedSingleKey.canSpend)
    }

    @Test
    fun nostrNsecImportedSpendWalletsRequireIdentityKeyRiskWarning() {
        val nostrSpend = onChainRepository.loadOnChainProfile()
            .descriptorWallets
            .single { it.origin == DescriptorWalletOrigin.NostrNsecImportedSpend }

        assertEquals(DescriptorBackupStatus.IdentityKeyRiskWarningRequired, nostrSpend.backupStatus)
        assertTrue(nostrSpend.warnings.any { it.contains("identity", ignoreCase = true) })
        assertFalse(nostrSpend.canSpend)
    }

    @Test
    fun watchOnlyNpubWalletsCannotSpend() {
        val npubWatch = onChainRepository.loadOnChainProfile()
            .descriptorWallets
            .single { it.origin == DescriptorWalletOrigin.NostrNpubWatchOnly }

        assertEquals(WatchOnlyPolicy.CannotSpend, npubWatch.watchOnlyPolicy)
        assertFalse(npubWatch.canSpend)
    }

    @Test
    fun onChainRecoveryModelKeepsRequiredBlockingIssuesVisible() {
        val recovery = requireNotNull(portfolioRepository.recoveryStatus().onChainRecoveryStatus)
        assertTrue(recovery.blockingIssues.contains(RecoveryBlockingIssue.SeedNotCreated))
        assertTrue(recovery.blockingIssues.contains(RecoveryBlockingIssue.DescriptorNotExported))
        assertTrue(recovery.blockingIssues.contains(RecoveryBlockingIssue.ImportedKeyRequiresSeparateBackup))
        assertTrue(recovery.blockingIssues.contains(RecoveryBlockingIssue.NostrIdentityKeyRiskUnacknowledged))
    }

    @Test
    fun placeholderServicesReturnDisabledOrPlaceholderStatesRatherThanSuccess() {
        val profile = onChainRepository.loadOnChainProfile()
        val walletService = PlaceholderDescriptorWalletService()
        val backendTester = PlaceholderBitcoinBackendConnectionTester()
        val psbtWorkflow = NotImplementedPsbtWorkflow()

        val createWalletResult = walletService.createNativeDescriptorWallet(DescriptorWalletLabel("Demo"))
        val testBackendResult = backendTester.testConnection(profile.backendProfiles.first())
        val psbtResult = psbtWorkflow.startWorkflow(profile.coinSelectionDraft)

        assertTrue(createWalletResult is WalletOperationResult.Disabled)
        assertTrue(testBackendResult is WalletOperationResult.Disabled)
        assertTrue(psbtResult is WalletOperationResult.Disabled)
    }

    @Test
    fun placeholdersDoNotExposeExecutableWalletActions() {
        val snapshot = portfolioRepository.loadPortfolioSnapshot()
        val onChain = onChainRepository.loadOnChainProfile()

        assertTrue(snapshot.sampleQuotes.all { it.isPlaceholder })
        assertTrue(snapshot.sampleQuotes.all { it.amountOutSats == 0L })
        assertTrue(onChain.disabledActions.isNotEmpty())
        assertFalse(onChain.coinSelectionDraft.isExecutable)
        assertTrue(portfolioRepository.lightningConnectors().all { it.status == BackendConnectionStatus.NotConfigured })
        assertTrue(portfolioRepository.cashuMints().all { it.isDemoMint })
    }
}
