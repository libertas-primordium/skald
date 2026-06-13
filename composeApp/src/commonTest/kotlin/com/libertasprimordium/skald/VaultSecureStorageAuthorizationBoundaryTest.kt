package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.EncryptedVaultBlockingIssue
import com.libertasprimordium.skald.security.EncryptedVaultCapability
import com.libertasprimordium.skald.security.EncryptedVaultReadinessPolicy
import com.libertasprimordium.skald.security.EncryptedVaultRequirement
import com.libertasprimordium.skald.security.EncryptedVaultRequirementStatus
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidence
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidenceState
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceGate
import com.libertasprimordium.skald.security.ProductionProviderConstructionContractStatus
import com.libertasprimordium.skald.security.ProductionProviderSecureStorageAuthorizationBoundaryRule
import com.libertasprimordium.skald.security.SkaldVaultV1ClearWipeStrategyPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1KdfCalibrationAuthorizationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1LockSessionLifecyclePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1MigrationCorruptionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1PassphrasePolicyGate
import com.libertasprimordium.skald.security.SkaldVaultV1PersistenceReadinessGate
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderOperationAuthorizationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1RedactionLeakagePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1RuntimeRandomnessAuthorizationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1SecureStorageAuthorizationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1VaultClearWipeEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultClearWipeRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultClearWipeResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultKdfCalibrationAuthorizationEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultKdfCalibrationAuthorizationRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultKdfCalibrationAuthorizationResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultKdfOperationKind
import com.libertasprimordium.skald.security.SkaldVaultV1VaultLockSessionEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultLockSessionRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultLockSessionResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultMigrationCorruptionEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultMigrationCorruptionRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultMigrationCorruptionResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPassphrasePolicyEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPassphrasePolicyRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPassphrasePolicyResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPersistenceReadinessEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPersistenceReadinessRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPersistenceReadinessResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultProviderOperationAuthorizationEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultProviderOperationAuthorizationRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultProviderOperationAuthorizationResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultProviderOperationKind
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRandomnessOperationKind
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRedactionEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRedactionRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRedactionResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRedactionValueKind
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRuntimeRandomnessAuthorizationEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRuntimeRandomnessAuthorizationResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultSecureStorageAuthorizationDecision
import com.libertasprimordium.skald.security.SkaldVaultV1VaultSecureStorageAuthorizationEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultSecureStorageAuthorizationRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultSecureStorageAuthorizationResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultSecureStorageAuthorizationStatus
import com.libertasprimordium.skald.security.SkaldVaultV1VaultSecureStorageBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1VaultSecureStorageCapability
import com.libertasprimordium.skald.security.SkaldVaultV1VaultSecureStorageFailureReason
import com.libertasprimordium.skald.security.SkaldVaultV1VaultSecureStorageOperationKind
import com.libertasprimordium.skald.security.SkaldVaultV1VaultSecureStorageRequiredGate
import com.libertasprimordium.skald.security.SkaldVaultV1VaultSecureStorageTargetKind
import com.libertasprimordium.skald.security.SkaldVaultV1VaultSecureStorageValueKind
import com.libertasprimordium.skald.security.SkaldVaultV1VaultSecureStorageWarning
import com.libertasprimordium.skald.security.SkaldVaultV1VaultStorageDisabledEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultStorageOperation
import com.libertasprimordium.skald.security.SkaldVaultV1VaultStorageOperationRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultStorageOperationResult
import com.libertasprimordium.skald.security.SkaldVaultV1DisabledStorageServiceFacade
import com.libertasprimordium.skald.security.VaultCryptoDependencyBlocker
import com.libertasprimordium.skald.security.VaultCryptoDependencyCandidate
import com.libertasprimordium.skald.security.VaultCryptoDependencyCapability
import com.libertasprimordium.skald.security.VaultCryptoDependencyProbeCatalog
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import com.libertasprimordium.skald.security.commonDisabledSecureMetadataCapability
import com.libertasprimordium.skald.security.commonDisabledSecureStorageCapability
import com.libertasprimordium.skald.security.commonProductionProviderAcceptanceContract
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultSecureStorageAuthorizationBoundaryTest {
    @Test
    fun defaultPolicyIsBlockedFailClosedAndCapabilitiesRemainDisabled() {
        val evidence = blocked(
            SkaldVaultV1SecureStorageAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultSecureStorageAuthorizationRequest.noEvidence(),
            ),
        )

        assertEquals(
            "skald-vault-v1-secure-storage-authorization-boundary-v1",
            SkaldVaultV1SecureStorageAuthorizationPolicy.POLICY_ID,
        )
        assertEquals(1, SkaldVaultV1SecureStorageAuthorizationPolicy.POLICY_VERSION)
        assertEquals(
            SkaldVaultV1VaultSecureStorageAuthorizationStatus.NoEvidenceAvailable,
            evidence.status,
        )
        assertEquals(
            SkaldVaultV1VaultSecureStorageAuthorizationDecision.BlockedFailClosed,
            evidence.decision,
        )
        assertContains(
            evidence.blockers,
            SkaldVaultV1VaultSecureStorageBlocker.SecureStorageAuthorizationStillDisabled,
        )
        assertContains(evidence.blockers, SkaldVaultV1VaultSecureStorageBlocker.PersistenceReadinessBlocked)
        assertContains(evidence.blockers, SkaldVaultV1VaultSecureStorageBlocker.SecureSecretStorageUnavailable)
        assertContains(evidence.blockers, SkaldVaultV1VaultSecureStorageBlocker.SecureMetadataStorageUnavailable)
        assertContains(evidence.blockers, SkaldVaultV1VaultSecureStorageBlocker.ProviderOperationAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1VaultSecureStorageBlocker.RuntimeRandomnessAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1VaultSecureStorageBlocker.KdfCalibrationAuthorizationBlocked)
        assertContains(evidence.warnings, SkaldVaultV1VaultSecureStorageWarning.NoStorageImplementation)
        assertTrue(evidence.secureStorageAuthorizationBoundaryModeled)
        assertTrue(evidence.secureStorageAuthorizationStillDisabled)
        assertTrue(evidence.secureStorageAuthorizationBlocksAllOperations)
        assertTrue(evidence.secureStorageAuthorizationDoesNotStoreSecrets)
        assertTrue(evidence.secureStorageAuthorizationDoesNotUseOsKeyrings)
        assertTrue(evidence.secureStorageAuthorizationDoesNotUsePasswordManagers)
        assertTrue(evidence.secureStorageAuthorizationDoesNotUseSettingsStorage)
        assertTrue(evidence.secureStorageAuthorizationDoesNotEnableUnlock)
        assertTrue(evidence.secureStorageAuthorizationDoesNotEnablePersistence)
        assertTrue(evidence.secureStorageAuthorizationDoesNotEnableProviderSelection)
        assertTrue(evidence.secureStorageFailureVocabularyModeled)
        assertFalse(evidence.secureStorageReady)
        assertFalse(evidence.secureSecretStorageReady)
        assertFalse(evidence.secureMetadataStorageReady)
        assertFalse(evidence.encryptedLocalVaultStorageReady)
        assertFalse(evidence.androidKeystoreWrappingReady)
        assertFalse(evidence.linuxOptionalKeyWrappingReady)
        assertFalse(evidence.osKeyringStorageReady)
        assertFalse(evidence.passwordManagerStorageReady)
        assertFalse(evidence.settingsSecretStorageReady)
        assertFalse(evidence.storeSecretReady)
        assertFalse(evidence.retrieveSecretReady)
        assertFalse(evidence.wrapKeyReady)
        assertFalse(evidence.unwrapKeyReady)
        assertFalse(evidence.metadataStorageReady)
        assertFalse(evidence.backupExportReady)
        assertDisabled(evidence.capability)
    }

    @Test
    fun everyModeledSecureStorageOperationIsUnauthorized() {
        val kinds = SkaldVaultV1VaultSecureStorageOperationKind.entries.toSet()

        listOf(
            SkaldVaultV1VaultSecureStorageOperationKind.SecureStorageAvailabilityCheck,
            SkaldVaultV1VaultSecureStorageOperationKind.SecureMetadataAvailabilityCheck,
            SkaldVaultV1VaultSecureStorageOperationKind.StoreSecret,
            SkaldVaultV1VaultSecureStorageOperationKind.RetrieveSecret,
            SkaldVaultV1VaultSecureStorageOperationKind.DeleteSecret,
            SkaldVaultV1VaultSecureStorageOperationKind.RotateSecret,
            SkaldVaultV1VaultSecureStorageOperationKind.WrapKey,
            SkaldVaultV1VaultSecureStorageOperationKind.UnwrapKey,
            SkaldVaultV1VaultSecureStorageOperationKind.StoreWrappedKey,
            SkaldVaultV1VaultSecureStorageOperationKind.RetrieveWrappedKey,
            SkaldVaultV1VaultSecureStorageOperationKind.StoreSensitiveMetadata,
            SkaldVaultV1VaultSecureStorageOperationKind.RetrieveSensitiveMetadata,
            SkaldVaultV1VaultSecureStorageOperationKind.DeleteSensitiveMetadata,
            SkaldVaultV1VaultSecureStorageOperationKind.StoreManifestMetadata,
            SkaldVaultV1VaultSecureStorageOperationKind.RetrieveManifestMetadata,
            SkaldVaultV1VaultSecureStorageOperationKind.StoreRecoveryMetadata,
            SkaldVaultV1VaultSecureStorageOperationKind.RetrieveRecoveryMetadata,
            SkaldVaultV1VaultSecureStorageOperationKind.StoreSessionAdjacentState,
            SkaldVaultV1VaultSecureStorageOperationKind.RetrieveSessionAdjacentState,
            SkaldVaultV1VaultSecureStorageOperationKind.ExportBackupMaterial,
            SkaldVaultV1VaultSecureStorageOperationKind.ImportBackupMaterial,
            SkaldVaultV1VaultSecureStorageOperationKind.MigrateSecureStorage,
            SkaldVaultV1VaultSecureStorageOperationKind.ValidateSecureStorageIntegrity,
            SkaldVaultV1VaultSecureStorageOperationKind.PurgeSecureStorage,
            SkaldVaultV1VaultSecureStorageOperationKind.UseOsKeyring,
            SkaldVaultV1VaultSecureStorageOperationKind.UsePasswordManager,
            SkaldVaultV1VaultSecureStorageOperationKind.UseAndroidKeystore,
            SkaldVaultV1VaultSecureStorageOperationKind.UseAndroidCredentialManager,
            SkaldVaultV1VaultSecureStorageOperationKind.UseEncryptedLocalVaultStorage,
            SkaldVaultV1VaultSecureStorageOperationKind.UseSettingsStorage,
            SkaldVaultV1VaultSecureStorageOperationKind.UsePlaintextStorage,
            SkaldVaultV1VaultSecureStorageOperationKind.UseTestOnlyFakeStorage,
            SkaldVaultV1VaultSecureStorageOperationKind.ProductionRuntimeSecureStorage,
            SkaldVaultV1VaultSecureStorageOperationKind.ReleaseValidationSecureStorage,
            SkaldVaultV1VaultSecureStorageOperationKind.MainnetSecureStorage,
        ).forEach { kind ->
            assertContains(kinds, kind)
            val evidence = blocked(
                SkaldVaultV1SecureStorageAuthorizationPolicy.evaluate(
                    SkaldVaultV1VaultSecureStorageAuthorizationRequest.forOperationKind(kind),
                ),
            )
            assertFalse(evidence.capability.secureStorageAuthorized)
            assertFalse(evidence.capability.storeSecretAvailable)
            assertFalse(evidence.capability.retrieveSecretAvailable)
            assertFalse(evidence.decision.secureStorageAllowed)
            assertFalse(evidence.decision.secretStorageAllowed)
            assertFalse(evidence.decision.wrapperAllowed)
        }
    }

    @Test
    fun valueTargetAndGateVocabularyAreModeled() {
        val summary = SkaldVaultV1SecureStorageAuthorizationPolicy.currentPolicySummary()

        listOf(
            SkaldVaultV1VaultSecureStorageValueKind.SkaldManagedVaultPassphrase,
            SkaldVaultV1VaultSecureStorageValueKind.PassphraseRetryThrottleState,
            SkaldVaultV1VaultSecureStorageValueKind.PassphrasePolicyState,
            SkaldVaultV1VaultSecureStorageValueKind.VaultRootKey,
            SkaldVaultV1VaultSecureStorageValueKind.MetadataEncryptionKey,
            SkaldVaultV1VaultSecureStorageValueKind.RecordEncryptionKey,
            SkaldVaultV1VaultSecureStorageValueKind.KeyWrappingKey,
            SkaldVaultV1VaultSecureStorageValueKind.ProviderRootKey,
            SkaldVaultV1VaultSecureStorageValueKind.ProviderKeyHandle,
            SkaldVaultV1VaultSecureStorageValueKind.AndroidHardwareWrappedKeyHandle,
            SkaldVaultV1VaultSecureStorageValueKind.FutureLinuxOptionalKeyWrappingHandle,
            SkaldVaultV1VaultSecureStorageValueKind.EncryptedLocalVaultContainerKeyMaterial,
            SkaldVaultV1VaultSecureStorageValueKind.WrappedVaultKeyMaterial,
            SkaldVaultV1VaultSecureStorageValueKind.SecureMetadataRecord,
            SkaldVaultV1VaultSecureStorageValueKind.SensitiveMetadataRecord,
            SkaldVaultV1VaultSecureStorageValueKind.ManifestMetadata,
            SkaldVaultV1VaultSecureStorageValueKind.StorageIndexMetadata,
            SkaldVaultV1VaultSecureStorageValueKind.RecordDescriptorMetadata,
            SkaldVaultV1VaultSecureStorageValueKind.RecoveryMetadata,
            SkaldVaultV1VaultSecureStorageValueKind.BackupExportKeyMaterial,
            SkaldVaultV1VaultSecureStorageValueKind.MigrationState,
            SkaldVaultV1VaultSecureStorageValueKind.CrashRecoveryState,
            SkaldVaultV1VaultSecureStorageValueKind.LockSessionToken,
            SkaldVaultV1VaultSecureStorageValueKind.ActiveSessionState,
            SkaldVaultV1VaultSecureStorageValueKind.RedactionDiagnosticState,
            SkaldVaultV1VaultSecureStorageValueKind.WalletLabel,
            SkaldVaultV1VaultSecureStorageValueKind.TransactionNote,
            SkaldVaultV1VaultSecureStorageValueKind.BackendCredential,
            SkaldVaultV1VaultSecureStorageValueKind.LightningMacaroonRuneNwcSecret,
            SkaldVaultV1VaultSecureStorageValueKind.PhoenixdToken,
            SkaldVaultV1VaultSecureStorageValueKind.CashuProofMaterial,
            SkaldVaultV1VaultSecureStorageValueKind.NostrNsecPrivateMaterial,
            SkaldVaultV1VaultSecureStorageValueKind.BdkPersistenceHandle,
            SkaldVaultV1VaultSecureStorageValueKind.PublicPolicyEvidence,
            SkaldVaultV1VaultSecureStorageValueKind.PublicNonWalletKatVectorEvidence,
            SkaldVaultV1VaultSecureStorageValueKind.EnumStatusCapabilityEvidence,
            SkaldVaultV1VaultSecureStorageValueKind.AggregateCountStatistic,
        ).forEach { valueKind -> assertContains(summary.valueKinds, valueKind) }

        listOf(
            SkaldVaultV1VaultSecureStorageTargetKind.AppControlledEncryptedLocalVault,
            SkaldVaultV1VaultSecureStorageTargetKind.AppPrivateAndroidInternalStorageFutureEncryptedVaultOnly,
            SkaldVaultV1VaultSecureStorageTargetKind.LinuxUserDataEncryptedVaultFutureEncryptedVaultOnly,
            SkaldVaultV1VaultSecureStorageTargetKind.AndroidKeystoreKeyWrappingFutureOptionalWrapperOnly,
            SkaldVaultV1VaultSecureStorageTargetKind.HardwareBackedAndroidWrapperFutureOptionalWrapperOnly,
            SkaldVaultV1VaultSecureStorageTargetKind.LinuxOsKeyringRejectedAsPrimaryStorage,
            SkaldVaultV1VaultSecureStorageTargetKind.LinuxOsKeyringFutureOptionalWrapperOnlyAfterReview,
            SkaldVaultV1VaultSecureStorageTargetKind.PasswordManagerRejectedForSkaldManagedPassphraseStorage,
            SkaldVaultV1VaultSecureStorageTargetKind.SettingsPreferencesStorageRejectedForSecretsAndSensitiveMetadata,
            SkaldVaultV1VaultSecureStorageTargetKind.PlaintextFileStorageRejected,
            SkaldVaultV1VaultSecureStorageTargetKind.DatabasePlaintextStorageRejected,
            SkaldVaultV1VaultSecureStorageTargetKind.DebugLogStorageRejected,
            SkaldVaultV1VaultSecureStorageTargetKind.CrashReportStorageRejected,
            SkaldVaultV1VaultSecureStorageTargetKind.AnalyticsStorageRejected,
            SkaldVaultV1VaultSecureStorageTargetKind.SupportExportStorageRejectedUnlessFutureRedactedReview,
            SkaldVaultV1VaultSecureStorageTargetKind.BackupExportPackageFutureReviewOnly,
            SkaldVaultV1VaultSecureStorageTargetKind.TestOnlyFakeStorageNotProduction,
            SkaldVaultV1VaultSecureStorageTargetKind.UnknownTarget,
            SkaldVaultV1VaultSecureStorageTargetKind.UnsupportedTarget,
        ).forEach { targetKind -> assertContains(summary.targetKinds, targetKind) }

        SkaldVaultV1VaultSecureStorageRequiredGate.entries.forEach { gate ->
            assertContains(summary.requiredGates, gate)
        }
    }

    @Test
    fun evidenceInteractionsRemainBlockedAndFailClosed() {
        val composed = blocked(
            SkaldVaultV1SecureStorageAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultSecureStorageAuthorizationRequest.fromEvidence(
                    secureStorageCapability = commonDisabledSecureStorageCapability(),
                    secureMetadataCapability = commonDisabledSecureMetadataCapability(),
                    persistenceReadinessEvidence = persistenceReadinessEvidence(),
                    disabledStorageFacadeEvidence = disabledStorageFacadeEvidence(),
                    providerOperationAuthorizationEvidence = providerOperationEvidence(),
                    runtimeRandomnessAuthorizationEvidence = runtimeRandomnessEvidence(),
                    kdfCalibrationAuthorizationEvidence = kdfCalibrationEvidence(),
                    passphrasePolicyEvidence = passphraseEvidence(),
                    redactionLeakageEvidence = redactionEvidence(),
                    clearWipeStrategyEvidence = clearWipeEvidence(),
                    lockSessionLifecycleEvidence = lockSessionEvidence(),
                    migrationCorruptionEvidence = migrationCorruptionEvidence(),
                    providerSelectionResult = VaultCryptoProviderSelectionRegistry.select(),
                    providerAcceptanceAssessment = commonProductionProviderAcceptanceContract().assess(),
                    dependencyProbeResult = dependencyProbeResult(),
                    encryptedVaultReadiness = EncryptedVaultReadinessPolicy.disabled(),
                    operationKind = SkaldVaultV1VaultSecureStorageOperationKind.StoreSecret,
                    valueKind = SkaldVaultV1VaultSecureStorageValueKind.VaultRootKey,
                    targetKind = SkaldVaultV1VaultSecureStorageTargetKind.AppControlledEncryptedLocalVault,
                ),
            ),
        )

        assertTrue(composed.secureStorageCapabilityEvidenceConsumed)
        assertTrue(composed.secureMetadataCapabilityEvidenceConsumed)
        assertTrue(composed.persistenceReadinessEvidenceConsumed)
        assertTrue(composed.disabledStorageFacadeEvidenceConsumed)
        assertTrue(composed.providerOperationAuthorizationEvidenceConsumed)
        assertTrue(composed.runtimeRandomnessAuthorizationEvidenceConsumed)
        assertTrue(composed.kdfCalibrationAuthorizationEvidenceConsumed)
        assertTrue(composed.passphrasePolicyEvidenceConsumed)
        assertTrue(composed.redactionLeakageEvidenceConsumed)
        assertTrue(composed.clearWipeStrategyEvidenceConsumed)
        assertTrue(composed.lockSessionLifecycleEvidenceConsumed)
        assertTrue(composed.migrationCorruptionEvidenceConsumed)
        assertTrue(composed.providerSelectionEvidenceConsumed)
        assertTrue(composed.providerAcceptanceEvidenceConsumed)
        assertTrue(composed.dependencyProbeEvidenceConsumed)
        assertTrue(composed.encryptedVaultReadinessEvidenceConsumed)
        assertEquals(SkaldVaultV1VaultSecureStorageAuthorizationDecision.BlockedFailClosed, composed.decision)
        assertContains(composed.blockers, SkaldVaultV1VaultSecureStorageBlocker.PersistenceReadinessBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultSecureStorageBlocker.DisabledStorageFacadeBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultSecureStorageBlocker.StorageSafetyPreflightBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultSecureStorageBlocker.PlatformRootPathSafetyBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultSecureStorageBlocker.ProviderOperationAuthorizationBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultSecureStorageBlocker.RuntimeRandomnessAuthorizationBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultSecureStorageBlocker.KdfCalibrationAuthorizationBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultSecureStorageBlocker.PassphrasePolicyBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultSecureStorageBlocker.LockSessionLifecycleBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultSecureStorageBlocker.RedactionLeakageUnsafe)
        assertContains(composed.blockers, SkaldVaultV1VaultSecureStorageBlocker.ClearWipeStrategyBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultSecureStorageBlocker.MigrationCorruptionBoundaryBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultSecureStorageBlocker.SecureSecretStorageUnavailable)
        assertContains(composed.blockers, SkaldVaultV1VaultSecureStorageBlocker.SecureMetadataStorageUnavailable)
        assertContains(composed.blockers, SkaldVaultV1VaultSecureStorageBlocker.WarningOnlyEvidenceRejected)
        assertContains(composed.blockers, SkaldVaultV1VaultSecureStorageBlocker.UserConsentOverrideRejected)
        assertDisabled(composed.capability)
    }

    @Test
    fun targetScopeDecisionsRemainRejectedOrUnsupported() {
        val osKeyring = blockedTarget(SkaldVaultV1VaultSecureStorageTargetKind.LinuxOsKeyringRejectedAsPrimaryStorage)
        val passwordManager = blockedTarget(
            SkaldVaultV1VaultSecureStorageTargetKind.PasswordManagerRejectedForSkaldManagedPassphraseStorage,
        )
        val settings = blockedTarget(
            SkaldVaultV1VaultSecureStorageTargetKind.SettingsPreferencesStorageRejectedForSecretsAndSensitiveMetadata,
        )
        val plaintext = blockedTarget(SkaldVaultV1VaultSecureStorageTargetKind.PlaintextFileStorageRejected)
        val testOnly = blockedTarget(SkaldVaultV1VaultSecureStorageTargetKind.TestOnlyFakeStorageNotProduction)
        val unknown = blockedTarget(SkaldVaultV1VaultSecureStorageTargetKind.UnknownTarget)
        val mainnet = blocked(
            SkaldVaultV1SecureStorageAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultSecureStorageAuthorizationRequest.forOperationKind(
                    SkaldVaultV1VaultSecureStorageOperationKind.MainnetSecureStorage,
                ),
            ),
        )

        assertEquals(SkaldVaultV1VaultSecureStorageAuthorizationDecision.RejectTarget, osKeyring.decision)
        assertContains(osKeyring.blockers, SkaldVaultV1VaultSecureStorageBlocker.OsKeyringPrimaryStorageRejected)
        assertEquals(SkaldVaultV1VaultSecureStorageAuthorizationDecision.RejectTarget, passwordManager.decision)
        assertContains(
            passwordManager.blockers,
            SkaldVaultV1VaultSecureStorageBlocker.PasswordManagerPassphraseStorageRejected,
        )
        assertEquals(SkaldVaultV1VaultSecureStorageAuthorizationDecision.RejectTarget, settings.decision)
        assertContains(settings.blockers, SkaldVaultV1VaultSecureStorageBlocker.SettingsSecretStorageRejected)
        assertEquals(SkaldVaultV1VaultSecureStorageAuthorizationDecision.RejectTarget, plaintext.decision)
        assertContains(plaintext.blockers, SkaldVaultV1VaultSecureStorageBlocker.PlaintextStorageRejected)
        assertEquals(
            SkaldVaultV1VaultSecureStorageAuthorizationDecision.TestOnlyTargetRejectedForProduction,
            testOnly.decision,
        )
        assertContains(testOnly.blockers, SkaldVaultV1VaultSecureStorageBlocker.TestOnlyFakeStorageRejectedForProduction)
        assertEquals(SkaldVaultV1VaultSecureStorageAuthorizationDecision.UnsupportedFailClosed, unknown.decision)
        assertContains(unknown.blockers, SkaldVaultV1VaultSecureStorageBlocker.UnknownUnsupportedTarget)
        assertEquals(SkaldVaultV1VaultSecureStorageAuthorizationDecision.RejectTarget, mainnet.decision)
        assertContains(mainnet.blockers, SkaldVaultV1VaultSecureStorageBlocker.MainnetUnavailable)
    }

    @Test
    fun renderingAndRawCandidateRejectionRemainRedacted() {
        val raw = "wrapped-key-fixture"
        val request = SkaldVaultV1VaultSecureStorageAuthorizationRequest.rawSecureStorageCandidate(raw)
        val result = SkaldVaultV1SecureStorageAuthorizationPolicy.evaluate(request)
        val rejected = assertIs<SkaldVaultV1VaultSecureStorageAuthorizationResult.Rejected>(result)
        val evidence = blocked(
            SkaldVaultV1SecureStorageAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultSecureStorageAuthorizationRequest.summary(),
            ),
        )

        assertEquals(SkaldVaultV1VaultSecureStorageFailureReason.WrappedKeyBytesRejected, rejected.reason)
        assertFalse(request.toString().contains(raw))
        assertFalse(result.toString().contains(raw))
        assertFalse(evidence.toString().contains(raw))
        assertFalse(evidence.policyTokenEvidence.toString().contains(raw))
        assertFalse(evidence.policyTokenEvidence.containsPassphrase)
        assertFalse(evidence.policyTokenEvidence.containsKeyMaterial)
        assertFalse(evidence.policyTokenEvidence.containsWrappedKeyBytes)
        assertFalse(evidence.policyTokenEvidence.containsProviderHandle)
        assertFalse(evidence.policyTokenEvidence.containsOsKeyringHandle)
        assertFalse(evidence.policyTokenEvidence.containsPasswordManagerEntry)
        assertFalse(evidence.policyTokenEvidence.containsAndroidKeystoreHandle)
        assertFalse(evidence.policyTokenEvidence.containsCredentialManagerCredential)
        assertFalse(evidence.policyTokenEvidence.containsMetadataPayload)
        assertFalse(evidence.policyTokenEvidence.containsManifestStorageIndexRecordBytes)
        assertFalse(evidence.policyTokenEvidence.containsCiphertextPlaintextTagNonceSaltKdfRandomBytes)
        assertFalse(evidence.policyTokenEvidence.containsRecordIdentifier)
        assertFalse(evidence.policyTokenEvidence.containsRootOrPathText)
        assertFalse(evidence.policyTokenEvidence.containsPayload)

        assertRejected("pass" + "phrase-fixture", SkaldVaultV1VaultSecureStorageFailureReason.ActualPassphraseRejected)
        assertRejected("key-bytes", SkaldVaultV1VaultSecureStorageFailureReason.KeyBytesRejected)
        assertRejected("wrapped-key-bytes", SkaldVaultV1VaultSecureStorageFailureReason.WrappedKeyBytesRejected)
        assertRejected(
            "provider-key-material",
            SkaldVaultV1VaultSecureStorageFailureReason.ProviderKeyMaterialRejected,
        )
        assertRejected("provider-handle", SkaldVaultV1VaultSecureStorageFailureReason.ProviderHandleRejected)
        assertRejected(
            "android-keystore-key",
            SkaldVaultV1VaultSecureStorageFailureReason.AndroidKeystoreKeyRejected,
        )
        assertRejected("os-keyring-handle", SkaldVaultV1VaultSecureStorageFailureReason.OsKeyringHandleRejected)
        assertRejected(
            "password-manager-entry",
            SkaldVaultV1VaultSecureStorageFailureReason.PasswordManagerEntryRejected,
        )
        assertRejected(
            "credential-manager-credential",
            SkaldVaultV1VaultSecureStorageFailureReason.CredentialManagerCredentialRejected,
        )
        assertRejected(
            "secure-storage-handle",
            SkaldVaultV1VaultSecureStorageFailureReason.SecureStorageHandleRejected,
        )
        assertRejected("database-handle", SkaldVaultV1VaultSecureStorageFailureReason.DatabaseHandleRejected)
        assertRejected("file-handle", SkaldVaultV1VaultSecureStorageFailureReason.FileHandleRejected)
        assertRejected(
            "metadata-bytes",
            SkaldVaultV1VaultSecureStorageFailureReason.RawManifestMetadataRecordContainerBytesRejected,
        )
        assertRejected(
            "ciphertext-fixture",
            SkaldVaultV1VaultSecureStorageFailureReason.CiphertextPlaintextTagNonceSaltKdfRandomBytesRejected,
        )
        assertRejected("bytearray-fixture", SkaldVaultV1VaultSecureStorageFailureReason.ByteArrayInputRejected)
        assertRejected("chararray-fixture", SkaldVaultV1VaultSecureStorageFailureReason.CharArrayInputRejected)
        assertRejected("rng-object", SkaldVaultV1VaultSecureStorageFailureReason.RandomObjectInputRejected)
        assertRejected("settings-value", SkaldVaultV1VaultSecureStorageFailureReason.RawSettingsValueRejected)
        assertRejected("/vault/root", SkaldVaultV1VaultSecureStorageFailureReason.RawAbsoluteLocationInputRejected)
        assertRejected("vault/root", SkaldVaultV1VaultSecureStorageFailureReason.RawRelativeLocationInputRejected)
        assertRejected("https://example.invalid", SkaldVaultV1VaultSecureStorageFailureReason.LinkLikeInputRejected)
        assertRejected("file-object", SkaldVaultV1VaultSecureStorageFailureReason.PlatformObjectLikeInputRejected)
        assertRejected("a".repeat(64), SkaldVaultV1VaultSecureStorageFailureReason.TransactionLikeEvidenceRejected)
        assertRejected(
            "bc" + "1q" + "a".repeat(24),
            SkaldVaultV1VaultSecureStorageFailureReason.BitcoinAddressLikeEvidenceRejected,
        )
        assertRejected("ns" + "ec1fixture", SkaldVaultV1VaultSecureStorageFailureReason.WalletMaterialRejected)
        assertRejected("xp" + "rvfixture", SkaldVaultV1VaultSecureStorageFailureReason.WalletMaterialRejected)
        assertRejected("tp" + "rvfixture", SkaldVaultV1VaultSecureStorageFailureReason.WalletMaterialRejected)
        assertRejected("K" + "a".repeat(50), SkaldVaultV1VaultSecureStorageFailureReason.WalletMaterialRejected)
        assertRejected("../vault", SkaldVaultV1VaultSecureStorageFailureReason.TraversalRejected)
        assertRejected("unsupported#chars", SkaldVaultV1VaultSecureStorageFailureReason.UnsupportedCharactersRejected)
    }

    @Test
    fun allCurrentCapabilitiesRemainDisabled() {
        assertDisabled(
            blocked(
                SkaldVaultV1SecureStorageAuthorizationPolicy.evaluate(
                    SkaldVaultV1VaultSecureStorageAuthorizationRequest.summary(),
                ),
            ).capability,
        )
    }

    @Test
    fun readinessProviderAcceptanceAndDependencyRecordBoundaryWithoutAuthorization() {
        val contract = commonProductionProviderAcceptanceContract()
        val storageContract = contract.containerManifestStorageContract
        val readiness = EncryptedVaultReadinessPolicy.disabled()
        val dependency = dependencyProbeResult()
        val providerSelection = VaultCryptoProviderSelectionRegistry.select()
        val evidence = ProductionProviderAcceptanceEvidence.currentDesignOnly()

        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            evidence.stateFor(
                ProductionProviderAcceptanceGate.SecureStorageAuthorizationBoundaryImplementedAndTested,
            ),
        )
        assertEquals(
            SkaldVaultV1SecureStorageAuthorizationPolicy.POLICY_ID,
            storageContract.secureStorageAuthorizationBoundaryPolicyId,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            storageContract.secureStorageAuthorizationBoundaryStatus,
        )
        assertEquals(
            ProductionProviderSecureStorageAuthorizationBoundaryRule.entries.toSet(),
            storageContract.secureStorageAuthorizationBoundaryRules,
        )
        assertTrue(storageContract.secureStorageAuthorizationBoundaryModeled)
        assertTrue(storageContract.secureStorageAuthorizationStillDisabled)
        assertTrue(storageContract.secureStorageAuthorizationBlocksAllOperations)
        assertTrue(storageContract.secureStorageAuthorizationDoesNotStoreSecrets)
        assertTrue(storageContract.secureStorageAuthorizationDoesNotUseOsKeyrings)
        assertTrue(storageContract.secureStorageAuthorizationDoesNotUsePasswordManagers)
        assertTrue(storageContract.secureStorageAuthorizationDoesNotUseSettingsStorage)
        assertTrue(storageContract.secureStorageAuthorizationDoesNotEnableUnlock)
        assertTrue(storageContract.secureStorageAuthorizationDoesNotEnablePersistence)
        assertTrue(storageContract.secureStorageAuthorizationDoesNotEnableProviderSelection)
        assertTrue(storageContract.secureStorageFailureVocabularyModeled)
        assertFalse(storageContract.vaultPersistenceImplemented)
        assertContains(readiness.capabilities, EncryptedVaultCapability.SecureStorageAuthorizationBoundaryBuildingBlock)
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.SecureStorageAuthorizationBoundaryImplementedAndTested
            ],
        )
        assertContains(readiness.blockers, EncryptedVaultBlockingIssue.SecureStorageAuthorizationBoundaryStillDisabled)
        assertContains(
            dependency.capabilities,
            VaultCryptoDependencyCapability.SecureStorageAuthorizationBoundaryImplementedTested,
        )
        assertContains(
            dependency.capabilities,
            VaultCryptoDependencyCapability.SecureStorageAuthorizationFailureVocabularyModeled,
        )
        assertContains(
            dependency.blockers,
            VaultCryptoDependencyBlocker.SecureStorageAuthorizationBoundaryStillDisabled,
        )
        assertContains(
            dependency.blockers,
            VaultCryptoDependencyBlocker.SecureStorageAuthorizationRuntimeReviewMissing,
        )
        assertContains(
            dependency.blockers,
            VaultCryptoDependencyBlocker.SecureStorageAuthorizationTestsMissing,
        )
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, providerSelection.selectedCandidateId)
        assertFalse(providerSelection.productionProviderSelectable)
        assertFalse(providerSelection.requestedCandidate.productionSelectable)
    }

    private fun blockedTarget(
        targetKind: SkaldVaultV1VaultSecureStorageTargetKind,
    ): SkaldVaultV1VaultSecureStorageAuthorizationEvidence =
        blocked(
            SkaldVaultV1SecureStorageAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultSecureStorageAuthorizationRequest.forTargetKind(targetKind),
            ),
        )

    private fun providerOperationEvidence(): SkaldVaultV1VaultProviderOperationAuthorizationEvidence =
        assertIs<
            SkaldVaultV1VaultProviderOperationAuthorizationResult.Blocked<
                SkaldVaultV1VaultProviderOperationAuthorizationEvidence,
            >,
        >(
            SkaldVaultV1ProviderOperationAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultProviderOperationAuthorizationRequest.forOperationKind(
                    SkaldVaultV1VaultProviderOperationKind.KeyWrapping,
                ),
            ),
        ).value

    private fun runtimeRandomnessEvidence(): SkaldVaultV1VaultRuntimeRandomnessAuthorizationEvidence =
        assertIs<
            SkaldVaultV1VaultRuntimeRandomnessAuthorizationResult.Blocked<
                SkaldVaultV1VaultRuntimeRandomnessAuthorizationEvidence,
            >,
        >(
            SkaldVaultV1RuntimeRandomnessAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest.forOperationKind(
                    SkaldVaultV1VaultRandomnessOperationKind.KeyGenerationEntropyRequest,
                ),
            ),
        ).value

    private fun kdfCalibrationEvidence(): SkaldVaultV1VaultKdfCalibrationAuthorizationEvidence =
        assertIs<
            SkaldVaultV1VaultKdfCalibrationAuthorizationResult.Blocked<
                SkaldVaultV1VaultKdfCalibrationAuthorizationEvidence,
            >,
        >(
            SkaldVaultV1KdfCalibrationAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultKdfCalibrationAuthorizationRequest.forOperationKind(
                    SkaldVaultV1VaultKdfOperationKind.KdfExecutionAuthorization,
                ),
            ),
        ).value

    private fun persistenceReadinessEvidence(): SkaldVaultV1VaultPersistenceReadinessEvidence =
        assertIs<
            SkaldVaultV1VaultPersistenceReadinessResult.Blocked<SkaldVaultV1VaultPersistenceReadinessEvidence>,
        >(
            SkaldVaultV1PersistenceReadinessGate.evaluate(
                SkaldVaultV1VaultPersistenceReadinessRequest.noEvidence(),
            ),
        ).value

    private fun disabledStorageFacadeEvidence(): SkaldVaultV1VaultStorageDisabledEvidence =
        assertIs<
            SkaldVaultV1VaultStorageOperationResult.Disabled<SkaldVaultV1VaultStorageDisabledEvidence>,
        >(
            SkaldVaultV1DisabledStorageServiceFacade.perform(
                SkaldVaultV1VaultStorageOperationRequest.noEvidence(
                    SkaldVaultV1VaultStorageOperation.WriteCurrentContainer,
                ),
            ),
        ).value

    private fun lockSessionEvidence(): SkaldVaultV1VaultLockSessionEvidence =
        assertIs<SkaldVaultV1VaultLockSessionResult.Blocked<SkaldVaultV1VaultLockSessionEvidence>>(
            SkaldVaultV1LockSessionLifecyclePolicy.evaluate(
                SkaldVaultV1VaultLockSessionRequest.noEvidence(),
            ),
        ).value

    private fun passphraseEvidence(): SkaldVaultV1VaultPassphrasePolicyEvidence =
        assertIs<SkaldVaultV1VaultPassphrasePolicyResult.Blocked<SkaldVaultV1VaultPassphrasePolicyEvidence>>(
            SkaldVaultV1PassphrasePolicyGate.evaluate(
                SkaldVaultV1VaultPassphrasePolicyRequest.summary(),
            ),
        ).value

    private fun redactionEvidence(): SkaldVaultV1VaultRedactionEvidence =
        assertIs<SkaldVaultV1VaultRedactionResult.Classified<SkaldVaultV1VaultRedactionEvidence>>(
            SkaldVaultV1RedactionLeakagePolicy.evaluate(
                SkaldVaultV1VaultRedactionRequest.classify(
                    SkaldVaultV1VaultRedactionValueKind.ProviderRootKey,
                ),
            ),
        ).value

    private fun clearWipeEvidence(): SkaldVaultV1VaultClearWipeEvidence =
        assertIs<SkaldVaultV1VaultClearWipeResult.Blocked<SkaldVaultV1VaultClearWipeEvidence>>(
            SkaldVaultV1ClearWipeStrategyPolicy.evaluate(
                SkaldVaultV1VaultClearWipeRequest.summary(),
            ),
        ).value

    private fun migrationCorruptionEvidence(): SkaldVaultV1VaultMigrationCorruptionEvidence =
        assertIs<
            SkaldVaultV1VaultMigrationCorruptionResult.Blocked<SkaldVaultV1VaultMigrationCorruptionEvidence>,
        >(
            SkaldVaultV1MigrationCorruptionPolicy.evaluate(
                SkaldVaultV1VaultMigrationCorruptionRequest.summary(),
            ),
        ).value

    private fun dependencyProbeResult() =
        VaultCryptoDependencyProbeCatalog.currentSpikeResults()
            .single { it.candidate == VaultCryptoDependencyCandidate.TinkBouncyCastleSplit }

    private fun assertRejected(
        candidate: String?,
        reason: SkaldVaultV1VaultSecureStorageFailureReason,
    ) {
        val request = SkaldVaultV1VaultSecureStorageAuthorizationRequest.rawSecureStorageCandidate(candidate)
        val result = SkaldVaultV1SecureStorageAuthorizationPolicy.evaluate(request)
        val rejected = assertIs<SkaldVaultV1VaultSecureStorageAuthorizationResult.Rejected>(result)

        assertTrue(request.rawCandidateRejected)
        assertEquals(reason, rejected.reason)
        assertFalse(result.toString().contains(candidate ?: ""))
    }

    private fun blocked(
        result: SkaldVaultV1VaultSecureStorageAuthorizationResult<
            SkaldVaultV1VaultSecureStorageAuthorizationEvidence,
        >,
    ): SkaldVaultV1VaultSecureStorageAuthorizationEvidence =
        assertIs<
            SkaldVaultV1VaultSecureStorageAuthorizationResult.Blocked<
                SkaldVaultV1VaultSecureStorageAuthorizationEvidence,
            >,
        >(result).value

    private fun assertDisabled(capability: SkaldVaultV1VaultSecureStorageCapability) {
        assertFalse(capability.secureStorageAuthorized)
        assertFalse(capability.secureSecretStorageAvailable)
        assertFalse(capability.secureMetadataStorageAvailable)
        assertFalse(capability.encryptedLocalVaultStorageAvailable)
        assertFalse(capability.osKeyringPrimaryStorageAvailable)
        assertFalse(capability.osKeyringOptionalWrappingAvailable)
        assertFalse(capability.passwordManagerPassphraseStorageAvailable)
        assertFalse(capability.androidKeystoreWrappingAvailable)
        assertFalse(capability.androidHardwareBackedWrappingAvailable)
        assertFalse(capability.linuxOptionalKeyWrappingAvailable)
        assertFalse(capability.settingsSecretStorageAvailable)
        assertFalse(capability.plaintextStorageAvailable)
        assertFalse(capability.storeSecretAvailable)
        assertFalse(capability.retrieveSecretAvailable)
        assertFalse(capability.deleteSecretAvailable)
        assertFalse(capability.wrapKeyAvailable)
        assertFalse(capability.unwrapKeyAvailable)
        assertFalse(capability.storeMetadataAvailable)
        assertFalse(capability.retrieveMetadataAvailable)
        assertFalse(capability.exportBackupMaterialAvailable)
        assertFalse(capability.importBackupMaterialAvailable)
        assertFalse(capability.secureStorageMigrationAvailable)
        assertFalse(capability.secureStoragePurgeAvailable)
        assertFalse(capability.providerOperationAuthorized)
        assertFalse(capability.runtimeRandomnessAuthorized)
        assertFalse(capability.kdfCalibrationAuthorized)
        assertFalse(capability.providerSelectable)
        assertFalse(capability.vaultCreationAvailable)
        assertFalse(capability.vaultUnlockAvailable)
        assertFalse(capability.vaultPersistenceAvailable)
        assertFalse(capability.mainnetAvailable)
    }
}
