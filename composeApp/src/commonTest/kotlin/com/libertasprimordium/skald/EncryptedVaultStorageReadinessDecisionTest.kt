package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.EncryptedVaultStorageReadinessDecisionCheck
import com.libertasprimordium.skald.security.EncryptedVaultStorageReadinessDecisionKind
import com.libertasprimordium.skald.security.EncryptedVaultStorageReadinessDecisionPolicy
import com.libertasprimordium.skald.security.EncryptedVaultStorageReadinessDecisionSourceSet
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderLevelPublicKatEvidencePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyExecutableProviderIdentityPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1TestOnlyProviderSelectionValidationPolicy
import com.libertasprimordium.skald.security.VaultCryptoDependencyCapability
import com.libertasprimordium.skald.security.VaultCryptoDependencyCandidate
import com.libertasprimordium.skald.security.VaultCryptoDependencyProbeCatalog
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionDecision
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class EncryptedVaultStorageReadinessDecisionTest {
    private fun decision() =
        EncryptedVaultStorageReadinessDecisionPolicy.currentStorageReadinessDecision()

    @Test
    fun storageReadinessDecisionExistsAsCommonMainPolicyOnly() {
        val decision = decision()

        assertEquals(1, decision.decisionVersion)
        assertEquals(
            EncryptedVaultStorageReadinessDecisionKind.EncryptedLocalVaultStorageReadinessDecision,
            decision.decisionKind,
        )
        assertEquals("ENCRYPTED_LOCAL_VAULT_STORAGE_READINESS_DECISION", decision.decisionKind.label)
        assertEquals(EncryptedVaultStorageReadinessDecisionSourceSet.CommonMainPolicy, decision.sourceSet)
        assertEquals("COMMON_MAIN_POLICY", decision.sourceSet.label)
        assertTrue(decision.storageReadinessDecisionPassed)
        assertEquals(0, decision.failureLabels.size)
        assertEquals(0, decision.blockerCount)
        assertEquals(0, decision.warningCount)
        assertEquals(EncryptedVaultStorageReadinessDecisionCheck.entries.size, decision.readinessCheckCount)
        EncryptedVaultStorageReadinessDecisionCheck.entries.forEach { check ->
            assertContains(decision.readinessChecks, check)
        }
    }

    @Test
    fun storageReadinessDecisionReflectsVaultCryptoAndDependencyEvidence() {
        val decision = decision()
        val dependency = VaultCryptoDependencyProbeCatalog.currentSpikeResults()
            .single { result -> result.candidate == VaultCryptoDependencyCandidate.TinkBouncyCastleSplit }

        assertTrue(decision.encryptedVaultDesignPresent)
        assertTrue(decision.cryptoDecisionPresent)
        assertTrue(decision.dependencyReviewPresent)
        assertTrue(decision.desktopDependencyKatPassed)
        assertTrue(decision.androidDependencyKatPassed)
        assertTrue(decision.argon2idCalibrationProbePresent)
        assertTrue(decision.disabledVaultCryptoProviderBoundaryPresent)
        assertContains(dependency.capabilities, VaultCryptoDependencyCapability.DesktopKnownAnswerVectorsPass)
        assertContains(dependency.capabilities, VaultCryptoDependencyCapability.AndroidKnownAnswerVectorsPass)
        assertContains(dependency.capabilities, VaultCryptoDependencyCapability.DependencyInventoryReviewed)
        assertContains(dependency.capabilities, VaultCryptoDependencyCapability.TinkKeysetStorageReviewDocumented)
    }

    @Test
    fun storageReadinessDecisionReflectsTestOnlyProviderValidationChain() {
        val decision = decision()
        val implementation =
            SkaldVaultV1TestOnlyExecutableProviderIdentityPolicy.currentTestOnlyExecutableProviderIdentity()
        val providerKat =
            SkaldVaultV1ProviderLevelPublicKatEvidencePolicy.currentProviderLevelPublicKatEvidence()
        val selectionValidation =
            SkaldVaultV1TestOnlyProviderSelectionValidationPolicy.currentProviderSelectionValidation()
        val selectionAudit =
            SkaldVaultV1TestOnlyProviderSelectionValidationCompletionAuditPolicy
                .currentProviderSelectionValidationCompletionAudit()

        assertTrue(implementation.implementationPresent)
        assertTrue(implementation.publicKatScopeOnly)
        assertTrue(providerKat.testOnlyProviderLevelKatExecutionPassed)
        assertTrue(providerKat.kdfProviderKatPassed)
        assertTrue(providerKat.aeadProviderKatPassed)
        assertTrue(selectionValidation.testOnlyProviderSelectionValidationPassed)
        assertTrue(selectionAudit.testOnlyProviderSelectionValidationCompletionAuditPassed)
        assertTrue(decision.testOnlyProviderImplementationPresent)
        assertEquals(providerKat.testOnlyProviderLevelKatExecutionPassed, decision.providerLevelPublicKatPassed)
        assertEquals(
            selectionValidation.testOnlyProviderSelectionValidationPassed,
            decision.testOnlyProviderSelectionValidationPassed,
        )
        assertEquals(
            selectionAudit.testOnlyProviderSelectionValidationCompletionAuditPassed,
            decision.providerSelectionValidationCompletionAuditPassed,
        )
        assertTrue(decision.providerLevelKatSuccessIsTestSourceOnlyEvidence)
        assertTrue(decision.testOnlyProviderSelectionValidationIsTestSourceOnlyEvidence)
    }

    @Test
    fun storageReadinessDecisionReflectsStorageMetadataSyncRecoveryAndBackendBoundaries() {
        val decision = decision()

        assertTrue(decision.secureStorageBoundaryPresent)
        assertTrue(decision.secureMetadataBoundaryPresent)
        assertTrue(decision.productionSyncFacadePresent)
        assertTrue(decision.recoveryPrivacyPersistenceBlockersPresent)
        assertTrue(decision.backendObservationStateBoundaryPresent)
        assertTrue(decision.productionBackendAdapterBoundaryPresent)
        assertTrue(decision.receiveAddressPolicyBoundaryPresent)
    }

    @Test
    fun storageReadinessDecisionAdmitsOnlyLaterStorageDesignAndImplementationBranches() {
        val decision = decision()

        assertTrue(decision.encryptedVaultStorageImplementationPathAdmitted)
        assertTrue(decision.vaultContainerFormatDecisionAdmitted)
        assertTrue(decision.storagePathPolicyDecisionAdmitted)
        assertTrue(decision.lockSessionLifecycleDecisionAdmitted)
        assertTrue(decision.migrationCorruptionPolicyDecisionAdmitted)
        assertTrue(decision.backupExportPolicyDecisionAdmitted)
        assertTrue(decision.futureVaultContainerImplementationRequiresSeparatePass)
        assertTrue(decision.futureSecureStorageSuccessRequiresSeparatePass)
        assertTrue(decision.futureSecureMetadataSuccessRequiresSeparatePass)
        assertTrue(decision.futureProductionSyncRequiresSeparatePass)
        assertTrue(decision.futureProductionProviderSelectionRequiresSeparatePass)
        assertTrue(decision.encryptedVaultStorageImplementationPathAdmittedIsLaterBranchOnly)
        assertTrue(decision.vaultContainerFormatDecisionAdmittedIsNotVaultImplementation)
        assertTrue(decision.storagePathPolicyDecisionAdmittedIsNotStorageWriteAuthorization)
        assertTrue(decision.lockSessionLifecycleDecisionAdmittedIsNotUnlockImplementation)
        assertTrue(decision.migrationCorruptionPolicyDecisionAdmittedIsNotMigrationExecution)
        assertTrue(decision.backupExportPolicyDecisionAdmittedIsNotBackupExportImplementation)
    }

    @Test
    fun storageReadinessDecisionDoesNotAddStoragePersistenceOrMetadataSuccess() {
        val decision = decision()

        assertFalse(decision.vaultContainerImplementationPresent)
        assertFalse(decision.encryptedVaultFileFormatImplemented)
        assertFalse(decision.encryptedVaultRepositorySuccessPresent)
        assertFalse(decision.secureSecretStorageSuccessPathPresent)
        assertFalse(decision.secureMetadataStorageSuccessPathPresent)
        assertFalse(decision.productionObservationPersistencePresent)
        assertFalse(decision.productionAddressIndexPersistencePresent)
        assertFalse(decision.productionUtxoPersistencePresent)
        assertFalse(decision.productionWalletHistoryPersistencePresent)
        assertFalse(decision.productionStorageAuthorizationPresent)
        assertFalse(decision.productionSecretStorageAuthorizationPresent)
        assertFalse(decision.productionMetadataStorageAuthorizationPresent)
        assertTrue(decision.selectedProviderKatSuccessIsNotVaultPersistenceAuthorization)
    }

    @Test
    fun storageReadinessDecisionKeepsSyncProviderSelectionSigningUiEndpointAndMainnetDisabled() {
        val decision = decision()
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertFalse(decision.productionSyncPresent)
        assertFalse(decision.productionBackendClientPresent)
        assertFalse(decision.providerChoicePersisted)
        assertFalse(decision.providerSelectionUiPresent)
        assertFalse(decision.productionProviderSelectionEnabled)
        assertFalse(decision.productionProviderSelectable)
        assertTrue(decision.productionSelectionStillDisabledProviderOnly)
        assertFalse(decision.signingBroadcastingPresent)
        assertFalse(decision.uiActionEnablementPresent)
        assertFalse(decision.endpointPresent)
        assertFalse(decision.mainnetPresent)
        assertFalse(decision.productionSyncAuthorizationPresent)
        assertFalse(decision.productionProviderSelectionAuthorizationPresent)
        assertFalse(decision.productionProviderImplementationAuthorizationPresent)
        assertFalse(decision.signingBroadcastingAuthorizationPresent)
        assertFalse(decision.uiAuthorizationPresent)
        assertFalse(decision.endpointAuthorizationPresent)
        assertFalse(decision.mainnetAuthorizationPresent)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertEquals(VaultCryptoProviderSelectionDecision.DisabledProviderSelected, selection.decision)
        assertTrue(selection.selectedProviderIsDisabled)
        assertTrue(selection.selectedProvider is DisabledVaultCryptoProvider)
        assertFalse(selection.productionProviderSelectable)
    }

    @Test
    fun storageReadinessDecisionKeepsSourceMaterialCorpusBoundaries() {
        val decision = decision()

        assertTrue(decision.normalSourceMaterialGuardExcludesBuildHistory)
        assertTrue(decision.localArtifactRootExcludedFromNormalSourceMaterialCorpus)
    }

    @Test
    fun storageReadinessDecisionOutputIsRedactedAndMaterialFree() {
        val decision = decision()
        val output = listOf(
            decision.toString(),
            decision.decisionId.toString(),
            decision.displayLabel.toString(),
        ).joinToString(separator = " ")
        val forbiddenText = listOf(
            "ByteArray",
            "UByteArray",
            "CharArray",
            "passphrase",
            "mnemonic",
            "seed phrase",
            "private key",
            "xprv",
            "tprv",
            "WIF",
            "nsec",
            "ciphertext",
            "plaintext",
            "nonce",
            "tag",
            "provider handle",
            "source location",
            "stack trace",
            "diagnostics payload",
            "analytics payload",
            "crash-report payload",
            "support-export payload",
            "endpoint value",
            "filesystem path",
            "public vector bytes",
            "public vector hex",
            "wpkh(",
            "tr(",
            "xpub",
            "psbt",
        )

        assertContains(output, "REDACTED")
        assertContains(output, "COMMON_MAIN_POLICY")
        assertContains(output, "STORAGE_READINESS_DECISION_ONLY")
        assertContains(output, "LATER_BRANCH_ONLY")
        assertContains(output, "NO_STORAGE_IMPLEMENTATION")
        assertContains(output, "DISABLED_PROVIDER_ONLY")
        forbiddenText.forEach { forbidden ->
            assertFalse(output.contains(forbidden, ignoreCase = true), "Output leaked forbidden text: $forbidden")
        }
        assertFalse(Regex("\\b[0-9a-fA-F]{64}\\b").containsMatchIn(output))
        assertFalse(Regex("\\b(?:bc1|tb1|bcrt1)[a-z0-9]{20,}\\b", RegexOption.IGNORE_CASE).containsMatchIn(output))
    }
}
