package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.EncryptedVaultContainerFormatV1DecisionPolicy
import com.libertasprimordium.skald.security.EncryptedVaultStoragePathSessionLifecycleDecisionCheck
import com.libertasprimordium.skald.security.EncryptedVaultStoragePathSessionLifecycleDecisionKind
import com.libertasprimordium.skald.security.EncryptedVaultStoragePathSessionLifecycleDecisionPolicy
import com.libertasprimordium.skald.security.EncryptedVaultStoragePathSessionLifecycleDecisionSourceSet
import com.libertasprimordium.skald.security.EncryptedVaultStoragePathSessionLifecyclePolicyLabel
import com.libertasprimordium.skald.security.EncryptedVaultStorageReadinessDecisionPolicy
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionDecision
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class EncryptedVaultStoragePathSessionLifecycleDecisionTest {
    private fun decision() =
        EncryptedVaultStoragePathSessionLifecycleDecisionPolicy
            .currentStoragePathSessionLifecycleDecision()

    @Test
    fun storagePathSessionLifecycleDecisionExistsAsCommonMainPolicyOnly() {
        val decision = decision()

        assertEquals(1, decision.decisionVersion)
        assertEquals(
            EncryptedVaultStoragePathSessionLifecycleDecisionKind
                .EncryptedLocalVaultStoragePathSessionLifecycleDecision,
            decision.decisionKind,
        )
        assertEquals(
            "ENCRYPTED_LOCAL_VAULT_STORAGE_PATH_SESSION_LIFECYCLE_DECISION",
            decision.decisionKind.label,
        )
        assertEquals(
            EncryptedVaultStoragePathSessionLifecycleDecisionSourceSet.CommonMainPolicy,
            decision.sourceSet,
        )
        assertEquals("COMMON_MAIN_POLICY", decision.sourceSet.label)
        assertTrue(decision.storagePathSessionLifecycleDecisionPassed)
        assertEquals(0, decision.failureLabels.size)
        assertEquals(0, decision.blockerCount)
        assertEquals(0, decision.warningCount)
        assertEquals(
            EncryptedVaultStoragePathSessionLifecycleDecisionCheck.entries.size,
            decision.decisionCheckCount,
        )
        EncryptedVaultStoragePathSessionLifecycleDecisionCheck.entries.forEach { check ->
            assertContains(decision.decisionChecks, check)
        }
    }

    @Test
    fun storagePathSessionLifecycleDecisionReflectsReadinessContainerAndBoundaryEvidence() {
        val decision = decision()
        val storageReadiness = EncryptedVaultStorageReadinessDecisionPolicy.currentStorageReadinessDecision()
        val containerDecision = EncryptedVaultContainerFormatV1DecisionPolicy.currentContainerFormatV1Decision()

        assertTrue(storageReadiness.storageReadinessDecisionPassed)
        assertTrue(containerDecision.containerFormatV1DecisionPassed)
        assertEquals(storageReadiness.storageReadinessDecisionPassed, decision.storageReadinessDecisionPresent)
        assertEquals(containerDecision.containerFormatV1DecisionPassed, decision.containerFormatV1DecisionPresent)
        assertEquals(storageReadiness.encryptedVaultDesignPresent, decision.encryptedVaultDesignPresent)
        assertEquals(containerDecision.cryptoDecisionPresent, decision.cryptoDecisionPresent)
        assertEquals(storageReadiness.dependencyReviewPresent, decision.dependencyReviewPresent)
        assertEquals(storageReadiness.disabledVaultCryptoProviderBoundaryPresent, decision.providerBoundaryPresent)
        assertEquals(
            storageReadiness.providerSelectionValidationCompletionAuditPassed,
            decision.providerSelectionValidationCompletionAuditPresent,
        )
        assertEquals(storageReadiness.secureStorageBoundaryPresent, decision.secureStorageBoundaryPresent)
        assertEquals(storageReadiness.secureMetadataBoundaryPresent, decision.secureMetadataBoundaryPresent)
        assertEquals(9, decision.evidenceCount)
    }

    @Test
    fun storagePathSessionLifecycleDecisionAdmitsOnlyPolicyLabelsAndLaterBranches() {
        val decision = decision()
        val labels = decision.policyLabels.associate { label -> label to label.safeLabel.value }

        assertTrue(decision.vaultStoragePathSessionLifecycleDecisionAdmitted)
        assertTrue(decision.androidAppPrivateStoragePolicyAdmitted)
        assertTrue(decision.linuxUserDataStoragePolicyAdmitted)
        assertTrue(decision.appControlledVaultDirectoryPolicyAdmitted)
        assertTrue(decision.noPlaintextCachePolicyAdmitted)
        assertTrue(decision.noNonSecretSettingsStorageForVaultPolicyAdmitted)
        assertTrue(decision.backupExportStorageSeparationPolicyAdmitted)
        assertTrue(decision.explicitUnlockRequiredPolicyAdmitted)
        assertTrue(decision.lockOnAppBackgroundPolicyAdmitted)
        assertTrue(decision.lockOnProcessDeathPolicyAdmitted)
        assertTrue(decision.lockOnExplicitUserActionPolicyAdmitted)
        assertTrue(decision.sessionKeyMemoryHandlingPolicyAdmitted)
        assertTrue(decision.platformWrappingPolicyReferenceAdmitted)
        assertEquals(
            "skald-encrypted-local-vault-storage-path-policy-v1",
            labels[EncryptedVaultStoragePathSessionLifecyclePolicyLabel.StoragePathPolicy],
        )
        assertEquals(
            "skald-encrypted-local-vault-session-lifecycle-policy-v1",
            labels[EncryptedVaultStoragePathSessionLifecyclePolicyLabel.SessionLifecyclePolicy],
        )
        assertEquals(
            "skald-vault-v1-android-app-private-storage-policy",
            labels[EncryptedVaultStoragePathSessionLifecyclePolicyLabel.AndroidAppPrivateStoragePolicy],
        )
        assertEquals(
            "skald-vault-v1-linux-user-data-storage-policy",
            labels[EncryptedVaultStoragePathSessionLifecyclePolicyLabel.LinuxUserDataStoragePolicy],
        )
        assertEquals(
            "skald-vault-v1-no-plaintext-cache-policy",
            labels[EncryptedVaultStoragePathSessionLifecyclePolicyLabel.NoPlaintextCachePolicy],
        )
        assertEquals(
            "skald-vault-v1-lock-on-background-policy",
            labels[EncryptedVaultStoragePathSessionLifecyclePolicyLabel.LockOnBackgroundPolicy],
        )
        assertEquals(
            "skald-vault-v1-lock-on-process-death-policy",
            labels[EncryptedVaultStoragePathSessionLifecyclePolicyLabel.LockOnProcessDeathPolicy],
        )
        assertEquals(
            "skald-vault-v1-explicit-unlock-required-policy",
            labels[EncryptedVaultStoragePathSessionLifecyclePolicyLabel.ExplicitUnlockRequiredPolicy],
        )
        assertTrue(decision.futureStoragePathImplementationRequiresSeparatePass)
        assertTrue(decision.futureLockSessionImplementationRequiresSeparatePass)
        assertTrue(decision.futureVaultContainerParserRequiresSeparatePass)
        assertTrue(decision.futureVaultContainerWriterRequiresSeparatePass)
        assertTrue(decision.futureVaultStorageRepositoryRequiresSeparatePass)
        assertTrue(decision.futureSecureStorageSuccessRequiresSeparatePass)
        assertTrue(decision.futureSecureMetadataSuccessRequiresSeparatePass)
        assertTrue(decision.futureProductionSyncRequiresSeparatePass)
        assertTrue(decision.futureProductionProviderSelectionRequiresSeparatePass)
    }

    @Test
    fun storagePathSessionLifecycleAdmittedLabelsAreNotImplementations() {
        val decision = decision()

        assertTrue(decision.vaultStoragePathSessionLifecycleDecisionAdmittedIsLaterBranchOnly)
        assertTrue(decision.androidAppPrivateStoragePolicyAdmittedIsNotAndroidStorageImplementation)
        assertTrue(decision.linuxUserDataStoragePolicyAdmittedIsNotLinuxStorageImplementation)
        assertTrue(decision.appControlledVaultDirectoryPolicyAdmittedIsNotDirectoryCreation)
        assertTrue(decision.noPlaintextCachePolicyAdmittedIsNotCacheImplementation)
        assertTrue(decision.explicitUnlockRequiredPolicyAdmittedIsNotUnlockImplementation)
        assertTrue(decision.lockOnAppBackgroundPolicyAdmittedIsNotAndroidLifecycleImplementation)
        assertTrue(decision.lockOnProcessDeathPolicyAdmittedIsNotRuntimeProcessLifecycleCode)
        assertTrue(decision.sessionKeyMemoryHandlingPolicyAdmittedIsNotRuntimeSessionKeyHandling)
        assertTrue(decision.platformWrappingPolicyReferenceAdmittedIsNotKeystoreOrKeyringImplementation)
    }

    @Test
    fun storagePathSessionLifecycleKeepsDirectoryFileContainerAndRepositoryRuntimeAbsent() {
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
        assertTrue(decision.noDirectoryCreationInThisBranch)
        assertTrue(decision.noFileReadWriteDeleteInThisBranch)
    }

    @Test
    fun storagePathSessionLifecycleKeepsUnlockLockRuntimeSessionAndCachesAbsent() {
        val decision = decision()

        assertFalse(decision.lockSessionImplementationPresent)
        assertFalse(decision.unlockImplementationPresent)
        assertFalse(decision.runtimeSessionKeyPresent)
        assertFalse(decision.sessionKeyCached)
        assertFalse(decision.plaintextCachePresent)
        assertFalse(decision.nonSecretSettingsVaultStoragePresent)
        assertFalse(decision.sharedPreferencesVaultStoragePresent)
        assertFalse(decision.desktopConfigVaultStoragePresent)
        assertTrue(decision.noUnlockLockOrSessionImplementationInThisBranch)
        assertTrue(decision.lockSessionPolicyCreatesNoRuntimeSessionObject)
        assertTrue(decision.lockSessionPolicyStoresNoKeyMaterial)
    }

    @Test
    fun storagePathSessionLifecycleKeepsPersistenceSyncProviderAndMainnetBlocked() {
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
    fun storagePathSessionLifecycleKeepsAllProductionAuthorizationsAbsent() {
        val decision = decision()

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
    fun storagePathPolicyContainsNoConcreteStorageStringsOrKeys() {
        val decision = decision()

        assertTrue(decision.policyLabelsContainNoConcreteStorageStrings)
        decision.policyLabels.map { label -> label.safeLabel.value }.forEach { label ->
            assertFalse("/" in label, "Policy label must not contain path separator: $label")
            assertFalse("\\" in label, "Policy label must not contain path separator: $label")
            assertFalse("." in label, "Policy label must not contain file extension separator: $label")
            assertFalse("~" in label, "Policy label must not contain home shorthand: $label")
            assertFalse(label.contains("SharedPreferences", ignoreCase = true))
            assertFalse(label.contains("DataStore", ignoreCase = true))
            assertFalse(label.contains("Room", ignoreCase = true))
            assertFalse(label.contains("sqlite", ignoreCase = true))
            assertFalse(label.contains("database", ignoreCase = true))
            assertFalse(label.endsWith(".db", ignoreCase = true))
            assertFalse(label.endsWith(".json", ignoreCase = true))
            assertFalse(label.endsWith(".xml", ignoreCase = true))
            assertFalse(label.endsWith(".properties", ignoreCase = true))
        }
        assertTrue(decision.futureStoragePathsPlatformResolvedByReviewedCode)
        assertTrue(decision.futureStoragePathsNotHardcodedInCommonCode)
        assertTrue(decision.futureStoragePathsNotLogged)
        assertTrue(decision.futureStoragePathsNotInSupportExports)
        assertTrue(decision.futureStoragePathsNotInDisplayOutput)
        assertTrue(decision.futureEncryptedVaultDataNotInNonSecretSettings)
        assertTrue(decision.futureEncryptedVaultDataNotInAndroidSharedPreferences)
        assertTrue(decision.futureEncryptedVaultDataNotInDesktopPlainConfig)
        assertTrue(decision.futureBackupExportDestinationExplicitAndSeparate)
    }

    @Test
    fun lockSessionAndPlatformWrappingPoliciesStayNonExecutable() {
        val decision = decision()

        assertTrue(decision.futureUnlockMustBeExplicit)
        assertTrue(decision.futureRuntimeSessionKeyMaterialMemoryOnly)
        assertTrue(decision.futureRuntimeSessionKeyNeverSerialized)
        assertTrue(decision.futureSessionLockOnExplicitUserAction)
        assertTrue(decision.futureSessionLockOnProcessDeathOrRestart)
        assertTrue(decision.futureAndroidBackgroundRequiresLockOrRevalidation)
        assertTrue(decision.futureLinuxDesktopRequiresExplicitLockAndReviewedInactivityTimeout)
        assertTrue(decision.androidKeystoreWrappingOptionalFutureOnly)
        assertTrue(decision.linuxOsKeyringWrappingOptionalDeferred)
        assertTrue(decision.platformWrappingDoesNotReplaceAppControlledVaultStorage)
        assertTrue(decision.platformWrappingImplementsNoAndroidKeystoreCall)
        assertTrue(decision.platformWrappingImplementsNoLinuxKeyringCall)
    }

    @Test
    fun storagePathSessionLifecycleKeepsSourceMaterialCorpusBoundaries() {
        val decision = decision()

        assertTrue(decision.normalSourceMaterialGuardExcludesBuildHistory)
        assertTrue(decision.localArtifactRootExcludedFromNormalSourceMaterialCorpus)
    }

    @Test
    fun storagePathSessionLifecycleOutputIsRedactedAndMaterialFree() {
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
            "sharedpreferences",
            "androidkeystore",
            "linux keyring",
            "wpkh(",
            "tr(",
            "xpub",
            "psbt",
        )

        assertContains(output, "REDACTED")
        assertContains(output, "COMMON_MAIN_POLICY")
        assertContains(output, "STORAGE_PATH_SESSION_LIFECYCLE_DECISION_ONLY")
        assertContains(output, "LATER_BRANCHES_ONLY")
        assertContains(output, "POLICY_LABELS_ONLY")
        assertContains(output, "NO_STORAGE_IO")
        assertContains(output, "NO_DIRECTORY_CREATION")
        assertContains(output, "NO_RUNTIME_SESSION")
        assertContains(output, "DISABLED_PROVIDER_ONLY")
        forbiddenText.forEach { forbidden ->
            assertFalse(output.contains(forbidden, ignoreCase = true), "Output leaked forbidden text: $forbidden")
        }
        assertFalse(Regex("\\b[0-9a-fA-F]{64}\\b").containsMatchIn(output))
        assertFalse(Regex("\\b(?:bc1|tb1|bcrt1)[a-z0-9]{20,}\\b", RegexOption.IGNORE_CASE).containsMatchIn(output))
        assertFalse(Regex("""(?:^|\s)/(?:[A-Za-z0-9._-]+/?)+""").containsMatchIn(output))
    }
}
