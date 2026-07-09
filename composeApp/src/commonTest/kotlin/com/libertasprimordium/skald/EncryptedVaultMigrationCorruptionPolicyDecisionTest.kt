package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.EncryptedVaultContainerFormatV1DecisionPolicy
import com.libertasprimordium.skald.security.EncryptedVaultMigrationCorruptionPolicyDecisionCheck
import com.libertasprimordium.skald.security.EncryptedVaultMigrationCorruptionPolicyDecisionKind
import com.libertasprimordium.skald.security.EncryptedVaultMigrationCorruptionPolicyDecisionPolicy
import com.libertasprimordium.skald.security.EncryptedVaultMigrationCorruptionPolicyDecisionSourceSet
import com.libertasprimordium.skald.security.EncryptedVaultMigrationCorruptionPolicyLabel
import com.libertasprimordium.skald.security.EncryptedVaultStoragePathSessionLifecycleDecisionPolicy
import com.libertasprimordium.skald.security.EncryptedVaultStorageReadinessDecisionPolicy
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionDecision
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class EncryptedVaultMigrationCorruptionPolicyDecisionTest {
    private fun decision() =
        EncryptedVaultMigrationCorruptionPolicyDecisionPolicy
            .currentMigrationCorruptionPolicyDecision()

    @Test
    fun migrationCorruptionPolicyDecisionExistsAsCommonMainPolicyOnly() {
        val decision = decision()

        assertEquals(1, decision.decisionVersion)
        assertEquals(
            EncryptedVaultMigrationCorruptionPolicyDecisionKind
                .EncryptedLocalVaultMigrationCorruptionPolicyDecision,
            decision.decisionKind,
        )
        assertEquals(
            "ENCRYPTED_LOCAL_VAULT_MIGRATION_CORRUPTION_POLICY_DECISION",
            decision.decisionKind.label,
        )
        assertEquals(
            EncryptedVaultMigrationCorruptionPolicyDecisionSourceSet.CommonMainPolicy,
            decision.sourceSet,
        )
        assertEquals("COMMON_MAIN_POLICY", decision.sourceSet.label)
        assertTrue(decision.migrationCorruptionPolicyDecisionPassed)
        assertEquals(0, decision.failureLabels.size)
        assertEquals(0, decision.blockerCount)
        assertEquals(0, decision.warningCount)
        assertEquals(
            EncryptedVaultMigrationCorruptionPolicyDecisionCheck.entries.size,
            decision.decisionCheckCount,
        )
        EncryptedVaultMigrationCorruptionPolicyDecisionCheck.entries.forEach { check ->
            assertContains(decision.decisionChecks, check)
        }
    }

    @Test
    fun migrationCorruptionPolicyDecisionReflectsPriorEvidence() {
        val decision = decision()
        val storageReadiness = EncryptedVaultStorageReadinessDecisionPolicy.currentStorageReadinessDecision()
        val containerDecision = EncryptedVaultContainerFormatV1DecisionPolicy.currentContainerFormatV1Decision()
        val storagePathDecision =
            EncryptedVaultStoragePathSessionLifecycleDecisionPolicy
                .currentStoragePathSessionLifecycleDecision()

        assertTrue(storageReadiness.storageReadinessDecisionPassed)
        assertTrue(containerDecision.containerFormatV1DecisionPassed)
        assertTrue(storagePathDecision.storagePathSessionLifecycleDecisionPassed)
        assertEquals(storageReadiness.storageReadinessDecisionPassed, decision.storageReadinessDecisionPresent)
        assertEquals(containerDecision.containerFormatV1DecisionPassed, decision.containerFormatV1DecisionPresent)
        assertEquals(
            storagePathDecision.storagePathSessionLifecycleDecisionPassed,
            decision.storagePathSessionLifecycleDecisionPresent,
        )
        assertEquals(storageReadiness.encryptedVaultDesignPresent, decision.encryptedVaultDesignPresent)
        assertEquals(containerDecision.cryptoDecisionPresent, decision.cryptoDecisionPresent)
        assertEquals(storagePathDecision.dependencyReviewPresent, decision.dependencyReviewPresent)
        assertEquals(storageReadiness.disabledVaultCryptoProviderBoundaryPresent, decision.providerBoundaryPresent)
        assertEquals(storageReadiness.secureStorageBoundaryPresent, decision.secureStorageBoundaryPresent)
        assertEquals(storageReadiness.secureMetadataBoundaryPresent, decision.secureMetadataBoundaryPresent)
        assertEquals(9, decision.evidenceCount)
    }

    @Test
    fun migrationCorruptionPolicyDecisionAdmitsOnlySafePolicyLabelsAndLaterBranches() {
        val decision = decision()
        val labels = decision.policyLabels.associate { label -> label to label.safeLabel.value }

        assertTrue(decision.vaultMigrationCorruptionPolicyDecisionAdmitted)
        assertTrue(decision.authenticateBeforeMigrationPolicyAdmitted)
        assertTrue(decision.migrationDryRunPolicyAdmitted)
        assertTrue(decision.migrationBackupPreconditionPolicyAdmitted)
        assertTrue(decision.migrationRollbackPolicyAdmitted)
        assertTrue(decision.failClosedCorruptionPolicyAdmitted)
        assertTrue(decision.noDestructiveRepairDefaultPolicyAdmitted)
        assertTrue(decision.redactedCorruptionDiagnosticsPolicyAdmitted)
        assertTrue(decision.atomicReplacePolicyReferenceAdmitted)
        assertTrue(decision.partialWriteDetectionPolicyAdmitted)
        assertEquals(
            "skald-encrypted-local-vault-migration-policy-v1",
            labels[EncryptedVaultMigrationCorruptionPolicyLabel.MigrationPolicy],
        )
        assertEquals(
            "skald-encrypted-local-vault-corruption-policy-v1",
            labels[EncryptedVaultMigrationCorruptionPolicyLabel.CorruptionPolicy],
        )
        assertEquals(
            "skald-vault-v1-authenticate-before-migration-policy",
            labels[EncryptedVaultMigrationCorruptionPolicyLabel.AuthenticateBeforeMigrationPolicy],
        )
        assertEquals(
            "skald-vault-v1-migration-dry-run-required-policy",
            labels[EncryptedVaultMigrationCorruptionPolicyLabel.MigrationDryRunRequiredPolicy],
        )
        assertEquals(
            "skald-vault-v1-fail-closed-corruption-policy",
            labels[EncryptedVaultMigrationCorruptionPolicyLabel.FailClosedCorruptionPolicy],
        )
        assertEquals(
            "skald-vault-v1-redacted-corruption-diagnostics-policy",
            labels[EncryptedVaultMigrationCorruptionPolicyLabel.RedactedCorruptionDiagnosticsPolicy],
        )
        assertTrue(decision.futureMigrationImplementationRequiresSeparatePass)
        assertTrue(decision.futureCorruptionDetectionImplementationRequiresSeparatePass)
        assertTrue(decision.futureVaultContainerParserRequiresSeparatePass)
        assertTrue(decision.futureVaultContainerWriterRequiresSeparatePass)
        assertTrue(decision.futureVaultStorageRepositoryRequiresSeparatePass)
        assertTrue(decision.futureSecureStorageSuccessRequiresSeparatePass)
        assertTrue(decision.futureSecureMetadataSuccessRequiresSeparatePass)
        assertTrue(decision.futureProductionSyncRequiresSeparatePass)
        assertTrue(decision.futureProductionProviderSelectionRequiresSeparatePass)
    }

    @Test
    fun admittedMigrationCorruptionLabelsAreNotImplementations() {
        val decision = decision()

        assertTrue(decision.vaultMigrationCorruptionPolicyDecisionAdmittedIsLaterBranchOnly)
        assertTrue(decision.authenticateBeforeMigrationPolicyAdmittedIsNotAuthenticatedParserImplementation)
        assertTrue(decision.migrationDryRunPolicyAdmittedIsNotMigrationExecution)
        assertTrue(decision.migrationBackupPreconditionPolicyAdmittedIsNotBackupExportImplementation)
        assertTrue(decision.migrationRollbackPolicyAdmittedIsNotRollbackImplementation)
        assertTrue(decision.failClosedCorruptionPolicyAdmittedIsNotCorruptionDetectionImplementation)
        assertTrue(decision.noDestructiveRepairDefaultPolicyAdmittedIsNotRepairImplementation)
        assertTrue(decision.redactedCorruptionDiagnosticsPolicyAdmittedIsNotDiagnosticsImplementation)
        assertTrue(decision.atomicReplacePolicyReferenceAdmittedIsNotFileWriteImplementation)
        assertTrue(decision.partialWriteDetectionPolicyAdmittedIsNotPartialWriteDetectionImplementation)
    }

    @Test
    fun migrationPolicyCreatesNoMigrationExecutionOrPayloadPlan() {
        val decision = decision()

        assertFalse(decision.migrationImplementationPresent)
        assertFalse(decision.migrationExecutionPresent)
        assertTrue(decision.futureMigrationAuthenticatesSourceVaultBeforeTransformingRecords)
        assertTrue(decision.futureMigrationDryRunBeforeWriting)
        assertTrue(decision.futureMigrationRequiresBackupOrRecoveryPrecondition)
        assertTrue(decision.futureMigrationRejectsUnknownCriticalRecordClasses)
        assertTrue(decision.futureMigrationFailsClosedOnUnknownUnsupportedVersions)
        assertTrue(decision.futureMigrationPreservesRecordClassAndKeySeparation)
        assertTrue(decision.futureMigrationDiagnosticsRedacted)
        assertTrue(decision.noMigrationRunsInThisBranch)
        assertTrue(decision.migrationPolicyCreatesNoPayloadPlan)
    }

    @Test
    fun corruptionPolicyCreatesNoDetectionRepairOrRepairObject() {
        val decision = decision()

        assertFalse(decision.corruptionDetectionImplementationPresent)
        assertFalse(decision.corruptionRepairImplementationPresent)
        assertTrue(decision.futureCorruptionAuthenticatesBeforeTrustingMetadata)
        assertTrue(decision.futureCorruptionHandlingFailsClosed)
        assertTrue(decision.futureDestructiveRepairOptInOnly)
        assertTrue(decision.futureCorruptionDiagnosticsSafeLabelsOnly)
        assertTrue(decision.futurePartialWriteDiagnosticsDoNotExposeMaterial)
        assertTrue(decision.noCorruptionDetectionOrRepairRunsInThisBranch)
        assertTrue(decision.corruptionPolicyCreatesNoRepairObject)
        assertTrue(decision.diagnosticsPolicyContainsNoSensitiveMaterial)
    }

    @Test
    fun backupRollbackAtomicAndPartialWritePoliciesAreNonExecutable() {
        val decision = decision()

        assertFalse(decision.backupCreationPresent)
        assertFalse(decision.rollbackImplementationPresent)
        assertFalse(decision.atomicReplaceImplementationPresent)
        assertFalse(decision.partialWriteDetectionImplementationPresent)
        assertTrue(decision.futureWriterRequiresReviewedAtomicReplaceStrategy)
        assertTrue(decision.futureWriterDoesNotExposeTempNamesOrPaths)
        assertTrue(decision.futureWriterDistinguishesFailureClasses)
        assertTrue(decision.noWriterOrAtomicReplaceInThisBranch)
        assertTrue(decision.atomicReplacePolicyImplementsNoFileIo)
    }

    @Test
    fun migrationCorruptionPolicyKeepsStorageFileContainerAndRepositoryRuntimeAbsent() {
        val decision = decision()

        assertFalse(decision.vaultStoragePathImplementationPresent)
        assertFalse(decision.vaultDirectoryCreated)
        assertFalse(decision.vaultFileReadPresent)
        assertFalse(decision.vaultFileWritePresent)
        assertFalse(decision.vaultFileDeletePresent)
        assertFalse(decision.vaultContainerParserPresent)
        assertFalse(decision.vaultContainerWriterPresent)
        assertFalse(decision.vaultContainerSerializationPresent)
        assertFalse(decision.vaultContainerParsingPresent)
        assertFalse(decision.vaultContainerBytesProduced)
        assertFalse(decision.encryptedVaultFileFormatImplemented)
        assertFalse(decision.encryptedVaultRepositorySuccessPresent)
    }

    @Test
    fun migrationCorruptionPolicyKeepsUnlockLockRuntimeSessionAndCachesAbsent() {
        val decision = decision()

        assertFalse(decision.lockSessionImplementationPresent)
        assertFalse(decision.unlockImplementationPresent)
        assertFalse(decision.runtimeSessionKeyPresent)
        assertFalse(decision.sessionKeyCached)
        assertFalse(decision.plaintextCachePresent)
    }

    @Test
    fun migrationCorruptionPolicyKeepsPersistenceSyncProviderAndMainnetBlocked() {
        val decision = decision()

        assertFalse(decision.secureSecretStorageSuccessPathPresent)
        assertFalse(decision.secureMetadataStorageSuccessPathPresent)
        assertFalse(decision.productionObservationPersistencePresent)
        assertFalse(decision.productionAddressIndexPersistencePresent)
        assertFalse(decision.productionUtxoPersistencePresent)
        assertFalse(decision.productionWalletHistoryPersistencePresent)
        assertFalse(decision.productionSyncPresent)
        assertFalse(decision.productionBackendClientPresent)
        assertFalse(decision.productionProviderSelectionEnabled)
        assertFalse(decision.productionProviderSelectable)
        assertTrue(decision.productionSelectionStillDisabledProviderOnly)
        assertFalse(decision.signingBroadcastingPresent)
        assertFalse(decision.uiActionEnablementPresent)
        assertFalse(decision.endpointPresent)
        assertFalse(decision.mainnetPresent)
    }

    @Test
    fun migrationCorruptionPolicyKeepsAllProductionAuthorizationsAbsent() {
        val decision = decision()

        assertFalse(decision.migrationImplementationAuthorizationPresent)
        assertFalse(decision.corruptionDetectionAuthorizationPresent)
        assertFalse(decision.corruptionRepairAuthorizationPresent)
        assertFalse(decision.backupCreationAuthorizationPresent)
        assertFalse(decision.rollbackAuthorizationPresent)
        assertFalse(decision.atomicReplaceAuthorizationPresent)
        assertFalse(decision.partialWriteDetectionAuthorizationPresent)
        assertFalse(decision.vaultStoragePathImplementationAuthorizationPresent)
        assertFalse(decision.vaultDirectoryCreationAuthorizationPresent)
        assertFalse(decision.vaultFileReadAuthorizationPresent)
        assertFalse(decision.vaultFileWriteAuthorizationPresent)
        assertFalse(decision.vaultFileDeleteAuthorizationPresent)
        assertFalse(decision.vaultSessionImplementationAuthorizationPresent)
        assertFalse(decision.vaultUnlockAuthorizationPresent)
        assertFalse(decision.vaultLockAuthorizationPresent)
        assertFalse(decision.vaultContainerImplementationAuthorizationPresent)
        assertFalse(decision.vaultContainerParserAuthorizationPresent)
        assertFalse(decision.vaultContainerWriterAuthorizationPresent)
        assertFalse(decision.productionStorageAuthorizationPresent)
        assertFalse(decision.productionSecretStorageAuthorizationPresent)
        assertFalse(decision.productionMetadataStorageAuthorizationPresent)
        assertFalse(decision.productionSyncAuthorizationPresent)
        assertFalse(decision.productionProviderSelectionAuthorizationPresent)
        assertFalse(decision.productionProviderImplementationAuthorizationPresent)
        assertFalse(decision.signingBroadcastingAuthorizationPresent)
        assertFalse(decision.uiAuthorizationPresent)
        assertFalse(decision.endpointAuthorizationPresent)
        assertFalse(decision.mainnetAuthorizationPresent)
    }

    @Test
    fun productionProviderSelectionStillResolvesOnlyToDisabledProvider() {
        val decision = decision()
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertFalse(decision.productionProviderSelectionEnabled)
        assertFalse(decision.productionProviderSelectable)
        assertTrue(decision.productionSelectionStillDisabledProviderOnly)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertEquals(VaultCryptoProviderSelectionDecision.DisabledProviderSelected, selection.decision)
        assertTrue(selection.selectedProviderIsDisabled)
        assertTrue(selection.selectedProvider is DisabledVaultCryptoProvider)
        assertFalse(selection.productionProviderSelectable)
    }

    @Test
    fun migrationCorruptionPolicyLabelsContainNoSensitiveMaterialOrStorageStrings() {
        val decision = decision()

        decision.policyLabels.map { label -> label.safeLabel.value }.forEach { label ->
            assertFalse("/" in label, "Policy label must not contain path separator: $label")
            assertFalse("\\" in label, "Policy label must not contain path separator: $label")
            assertFalse("." in label, "Policy label must not contain file extension separator: $label")
            assertFalse("~" in label, "Policy label must not contain home shorthand: $label")
            assertFalse(label.contains("path", ignoreCase = true))
            assertFalse(label.contains("filename", ignoreCase = true))
            assertFalse(label.contains("database", ignoreCase = true))
            assertFalse(label.contains("SharedPreferences", ignoreCase = true))
            assertFalse(label.contains("checksum", ignoreCase = true))
            assertFalse(label.contains("mac", ignoreCase = true))
            assertFalse(label.contains("stack", ignoreCase = true))
            assertFalse(label.contains("source", ignoreCase = true))
            assertFalse(Regex("\\b[0-9a-fA-F]{32,}\\b").containsMatchIn(label))
        }
        assertTrue(decision.diagnosticsPolicyContainsNoSensitiveMaterial)
    }

    @Test
    fun migrationCorruptionPolicyKeepsSourceMaterialCorpusBoundaries() {
        val decision = decision()

        assertTrue(decision.normalSourceMaterialGuardExcludesBuildHistory)
        assertTrue(decision.localArtifactRootExcludedFromNormalSourceMaterialCorpus)
    }

    @Test
    fun migrationCorruptionPolicyOutputIsRedactedAndMaterialFree() {
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
            "salt",
            "nonce",
            "tag",
            "mac",
            "hash",
            "key material",
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
            "storage key",
            "directory name",
            "file name",
            "migration payload",
            "corruption hash",
            "repair diagnostics",
            "wpkh(",
            "tr(",
            "xpub",
            "psbt",
        )

        assertContains(output, "REDACTED")
        assertContains(output, "COMMON_MAIN_POLICY")
        assertContains(output, "MIGRATION_CORRUPTION_POLICY_DECISION_ONLY")
        assertContains(output, "LATER_BRANCHES_ONLY")
        assertContains(output, "POLICY_LABELS_ONLY")
        assertContains(output, "NO_MIGRATION_EXECUTION")
        assertContains(output, "NO_CORRUPTION_REPAIR")
        assertContains(output, "NO_BACKUP_ROLLBACK_ATOMIC_REPLACE")
        assertContains(output, "NO_STORAGE_IO")
        assertContains(output, "NO_CONTAINER_IO")
        assertContains(output, "DISABLED_PROVIDER_ONLY")
        forbiddenText.forEach { forbidden ->
            assertFalse(output.contains(forbidden, ignoreCase = true), "Output leaked forbidden text: $forbidden")
        }
        assertFalse(Regex("\\b[0-9a-fA-F]{64}\\b").containsMatchIn(output))
        assertFalse(Regex("\\b(?:bc1|tb1|bcrt1)[a-z0-9]{20,}\\b", RegexOption.IGNORE_CASE).containsMatchIn(output))
        assertFalse(Regex("""(?:^|\s)/(?:[A-Za-z0-9._-]+/?)+""").containsMatchIn(output))
    }
}
