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
import com.libertasprimordium.skald.security.ProductionProviderCreationAuthorizationBoundaryRule
import com.libertasprimordium.skald.security.SkaldVaultV1ClearWipeStrategyPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1CreationAuthorizationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1DisabledStorageServiceFacade
import com.libertasprimordium.skald.security.SkaldVaultV1KdfCalibrationAuthorizationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1LockSessionLifecyclePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1MigrationCorruptionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1PassphrasePolicyGate
import com.libertasprimordium.skald.security.SkaldVaultV1PersistenceReadinessGate
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderOperationAuthorizationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1RedactionLeakagePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1RuntimeRandomnessAuthorizationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1SecureStorageAuthorizationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1UnlockAuthorizationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1VaultClearWipeEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultClearWipeRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultClearWipeResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultCreationAuthorizationBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1VaultCreationAuthorizationCapability
import com.libertasprimordium.skald.security.SkaldVaultV1VaultCreationAuthorizationDecision
import com.libertasprimordium.skald.security.SkaldVaultV1VaultCreationAuthorizationEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultCreationAuthorizationRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultCreationAuthorizationResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultCreationAuthorizationStatus
import com.libertasprimordium.skald.security.SkaldVaultV1VaultCreationAuthorizationWarning
import com.libertasprimordium.skald.security.SkaldVaultV1VaultCreationFailureReason
import com.libertasprimordium.skald.security.SkaldVaultV1VaultCreationInitializerClass
import com.libertasprimordium.skald.security.SkaldVaultV1VaultCreationOperationKind
import com.libertasprimordium.skald.security.SkaldVaultV1VaultCreationPurpose
import com.libertasprimordium.skald.security.SkaldVaultV1VaultCreationRequiredGate
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
import com.libertasprimordium.skald.security.SkaldVaultV1VaultSecureStorageAuthorizationEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultSecureStorageAuthorizationRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultSecureStorageAuthorizationResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultSecureStorageOperationKind
import com.libertasprimordium.skald.security.SkaldVaultV1VaultStorageDisabledEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultStorageOperation
import com.libertasprimordium.skald.security.SkaldVaultV1VaultStorageOperationRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultStorageOperationResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultUnlockAuthorizationEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultUnlockAuthorizationRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultUnlockAuthorizationResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultUnlockOperationKind
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

class VaultCreationAuthorizationBoundaryTest {
    @Test
    fun defaultPolicyIsBlockedFailClosedAndCapabilitiesRemainDisabled() {
        val evidence = blocked(
            SkaldVaultV1CreationAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultCreationAuthorizationRequest.noEvidence(),
            ),
        )

        assertEquals(
            "skald-vault-v1-creation-authorization-boundary-v1",
            SkaldVaultV1CreationAuthorizationPolicy.POLICY_ID,
        )
        assertEquals(1, SkaldVaultV1CreationAuthorizationPolicy.POLICY_VERSION)
        assertEquals(SkaldVaultV1VaultCreationAuthorizationStatus.NoEvidenceAvailable, evidence.status)
        assertEquals(SkaldVaultV1VaultCreationAuthorizationDecision.BlockedFailClosed, evidence.decision)
        assertContains(evidence.blockers, SkaldVaultV1VaultCreationAuthorizationBlocker.CreationAuthorizationStillDisabled)
        assertContains(evidence.blockers, SkaldVaultV1VaultCreationAuthorizationBlocker.PassphrasePolicyBlocked)
        assertContains(evidence.blockers, SkaldVaultV1VaultCreationAuthorizationBlocker.KdfCalibrationAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1VaultCreationAuthorizationBlocker.RuntimeRandomnessAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1VaultCreationAuthorizationBlocker.ProviderOperationAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1VaultCreationAuthorizationBlocker.SecureStorageAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1VaultCreationAuthorizationBlocker.DisabledStorageFacadeBlocked)
        assertContains(evidence.blockers, SkaldVaultV1VaultCreationAuthorizationBlocker.StorageSafetyPreflightBlocked)
        assertContains(evidence.blockers, SkaldVaultV1VaultCreationAuthorizationBlocker.PersistenceReadinessBlocked)
        assertContains(evidence.blockers, SkaldVaultV1VaultCreationAuthorizationBlocker.PostCreateUnlockUnavailable)
        assertContains(evidence.blockers, SkaldVaultV1VaultCreationAuthorizationBlocker.DisabledProviderSelected)
        assertContains(evidence.warnings, SkaldVaultV1VaultCreationAuthorizationWarning.NoCreationImplementation)
        assertTrue(evidence.creationAuthorizationBoundaryModeled)
        assertTrue(evidence.creationAuthorizationStillDisabled)
        assertTrue(evidence.creationAuthorizationBlocksAllOperations)
        assertTrue(evidence.creationAuthorizationDoesNotAcceptPassphrases)
        assertTrue(evidence.creationAuthorizationDoesNotGenerateKeys)
        assertTrue(evidence.creationAuthorizationDoesNotRunKdf)
        assertTrue(evidence.creationAuthorizationDoesNotWriteStorage)
        assertTrue(evidence.creationAuthorizationDoesNotCreateSession)
        assertTrue(evidence.creationAuthorizationDoesNotEnablePersistence)
        assertTrue(evidence.creationAuthorizationDoesNotEnableProviderSelection)
        assertTrue(evidence.creationAuthorizationFailureVocabularyModeled)
        assertFalse(evidence.creationReady)
        assertFalse(evidence.vaultCreationReady)
        assertFalse(evidence.creationAuthorized)
        assertFalse(evidence.initialPassphraseAccepted)
        assertFalse(evidence.initialKeyMaterialAvailable)
        assertFalse(evidence.initialKdfExecutionReady)
        assertFalse(evidence.headerCreationReady)
        assertFalse(evidence.containerCreationReady)
        assertFalse(evidence.manifestCreationReady)
        assertFalse(evidence.storageIndexCreationReady)
        assertFalse(evidence.recordCreationReady)
        assertFalse(evidence.initialPersistenceReady)
        assertFalse(evidence.unlockReady)
        assertFalse(evidence.vaultUnlockReady)
        assertFalse(evidence.activeSessionReady)
        assertFalse(evidence.providerSelectable)
        assertFalse(evidence.vaultStorageAvailable)
        assertFalse(evidence.persistenceReady)
        assertFalse(evidence.vaultPersistenceReady)
        assertDisabled(evidence.capability)
    }

    @Test
    fun everyModeledCreationOperationIsUnauthorized() {
        val kinds = SkaldVaultV1VaultCreationOperationKind.entries.toSet()

        listOf(
            SkaldVaultV1VaultCreationOperationKind.CreationAvailabilityCheck,
            SkaldVaultV1VaultCreationOperationKind.NewVaultCreationAttempt,
            SkaldVaultV1VaultCreationOperationKind.InitialPassphraseCreationAttempt,
            SkaldVaultV1VaultCreationOperationKind.InitialKeyMaterialCreationAttempt,
            SkaldVaultV1VaultCreationOperationKind.InitialSaltNonceCreationAttempt,
            SkaldVaultV1VaultCreationOperationKind.InitialKdfExecutionAuthorization,
            SkaldVaultV1VaultCreationOperationKind.InitialProviderOperationAuthorization,
            SkaldVaultV1VaultCreationOperationKind.InitialHeaderCreationAuthorization,
            SkaldVaultV1VaultCreationOperationKind.InitialHeaderCommitmentAuthorization,
            SkaldVaultV1VaultCreationOperationKind.InitialContainerCreationAuthorization,
            SkaldVaultV1VaultCreationOperationKind.InitialManifestCreationAuthorization,
            SkaldVaultV1VaultCreationOperationKind.InitialStorageIndexCreationAuthorization,
            SkaldVaultV1VaultCreationOperationKind.InitialRecordCreationAuthorization,
            SkaldVaultV1VaultCreationOperationKind.InitialSecureMetadataCreationAuthorization,
            SkaldVaultV1VaultCreationOperationKind.InitialWrappedKeyStorageAuthorization,
            SkaldVaultV1VaultCreationOperationKind.InitialStorageNamespaceCreationAuthorization,
            SkaldVaultV1VaultCreationOperationKind.InitialStorageSafetyCheckAuthorization,
            SkaldVaultV1VaultCreationOperationKind.InitialPersistenceCommitAuthorization,
            SkaldVaultV1VaultCreationOperationKind.PostCreateUnlockSessionAuthorization,
            SkaldVaultV1VaultCreationOperationKind.CreationRollbackFailureCleanupAuthorization,
            SkaldVaultV1VaultCreationOperationKind.TestOnlyCreationSimulation,
            SkaldVaultV1VaultCreationOperationKind.ReleaseValidationCreationAttempt,
            SkaldVaultV1VaultCreationOperationKind.ProductionRuntimeCreationAttempt,
            SkaldVaultV1VaultCreationOperationKind.MainnetCreationAttempt,
        ).forEach { kind ->
            assertContains(kinds, kind)
            val evidence = blocked(
                SkaldVaultV1CreationAuthorizationPolicy.evaluate(
                    SkaldVaultV1VaultCreationAuthorizationRequest.forOperationKind(kind),
                ),
            )
            assertFalse(evidence.capability.creationAuthorized)
            assertFalse(evidence.capability.creationAttemptAvailable)
            assertFalse(evidence.capability.initialPersistenceCommitAvailable)
            assertFalse(evidence.decision.creationAllowed)
            assertFalse(evidence.decision.creationAttemptAllowed)
            assertFalse(evidence.decision.storageWriteAllowed)
            assertFalse(evidence.decision.persistenceAllowed)
        }
    }

    @Test
    fun purposeInitializerAndGateVocabularyAreModeled() {
        val summary = SkaldVaultV1CreationAuthorizationPolicy.currentPolicySummary()

        listOf(
            SkaldVaultV1VaultCreationPurpose.CreateNewLocalVault,
            SkaldVaultV1VaultCreationPurpose.CreateVaultForRestoreImport,
            SkaldVaultV1VaultCreationPurpose.CreateVaultForMigrationTarget,
            SkaldVaultV1VaultCreationPurpose.CreateVaultForBackupExportStaging,
            SkaldVaultV1VaultCreationPurpose.CreateVaultForRecoveryCenterReadiness,
            SkaldVaultV1VaultCreationPurpose.CreateVaultForPrivacyAnalyzerReadiness,
            SkaldVaultV1VaultCreationPurpose.CreateVaultBeforeWalletActivation,
            SkaldVaultV1VaultCreationPurpose.CreateVaultBeforeWalletSync,
            SkaldVaultV1VaultCreationPurpose.CreateVaultBeforeSigningSupport,
            SkaldVaultV1VaultCreationPurpose.CreateTestOnlyDeterministicSimulation,
            SkaldVaultV1VaultCreationPurpose.CreateReleaseValidationVault,
            SkaldVaultV1VaultCreationPurpose.CreateProductionRuntimeVault,
            SkaldVaultV1VaultCreationPurpose.CreateMainnetVault,
        ).forEach { purpose -> assertContains(summary.purposes, purpose) }

        listOf(
            SkaldVaultV1VaultCreationInitializerClass.NoInitializerSupplied,
            SkaldVaultV1VaultCreationInitializerClass.PassphraseInitializerBlocked,
            SkaldVaultV1VaultCreationInitializerClass.PassphrasePolicyEvidenceOnly,
            SkaldVaultV1VaultCreationInitializerClass.GeneratedKeyInitializerBlocked,
            SkaldVaultV1VaultCreationInitializerClass.HardwareWrappedKeyInitializerFutureOnly,
            SkaldVaultV1VaultCreationInitializerClass.AndroidKeystoreWrapperEvidenceFutureOnly,
            SkaldVaultV1VaultCreationInitializerClass.LinuxOptionalKeyWrapperEvidenceFutureOnly,
            SkaldVaultV1VaultCreationInitializerClass.OsKeyringInitializerRejectedAsPrimaryStorage,
            SkaldVaultV1VaultCreationInitializerClass.PasswordManagerInitializerRejectedForSkaldManagedPassphraseStorage,
            SkaldVaultV1VaultCreationInitializerClass.ImportedBackupInitializerFutureOnly,
            SkaldVaultV1VaultCreationInitializerClass.MigratedVaultInitializerFutureOnly,
            SkaldVaultV1VaultCreationInitializerClass.TestOnlyPlaceholderInitializerRejectedForProduction,
            SkaldVaultV1VaultCreationInitializerClass.UnknownInitializer,
            SkaldVaultV1VaultCreationInitializerClass.UnsupportedInitializer,
        ).forEach { initializerClass -> assertContains(summary.initializerClasses, initializerClass) }

        SkaldVaultV1VaultCreationRequiredGate.entries.forEach { gate ->
            assertContains(summary.requiredGates, gate)
        }
    }

    @Test
    fun evidenceInteractionsRemainBlockedAndFailClosed() {
        val composed = blocked(
            SkaldVaultV1CreationAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultCreationAuthorizationRequest.fromEvidence(
                    unlockAuthorizationEvidence = unlockAuthorizationEvidence(),
                    secureStorageAuthorizationEvidence = secureStorageAuthorizationEvidence(),
                    kdfCalibrationAuthorizationEvidence = kdfCalibrationEvidence(),
                    runtimeRandomnessAuthorizationEvidence = runtimeRandomnessEvidence(),
                    providerOperationAuthorizationEvidence = providerOperationEvidence(),
                    passphrasePolicyEvidence = passphraseEvidence(),
                    secureStorageCapability = commonDisabledSecureStorageCapability(),
                    secureMetadataCapability = commonDisabledSecureMetadataCapability(),
                    lockSessionLifecycleEvidence = lockSessionEvidence(),
                    redactionLeakageEvidence = redactionEvidence(),
                    clearWipeStrategyEvidence = clearWipeEvidence(),
                    migrationCorruptionEvidence = migrationCorruptionEvidence(),
                    persistenceReadinessEvidence = persistenceReadinessEvidence(),
                    disabledStorageFacadeEvidence = disabledStorageFacadeEvidence(),
                    providerSelectionResult = VaultCryptoProviderSelectionRegistry.select(),
                    providerAcceptanceAssessment = commonProductionProviderAcceptanceContract().assess(),
                    dependencyProbeResult = dependencyProbeResult(),
                    encryptedVaultReadiness = EncryptedVaultReadinessPolicy.disabled(),
                    operationKind = SkaldVaultV1VaultCreationOperationKind.NewVaultCreationAttempt,
                    purpose = SkaldVaultV1VaultCreationPurpose.CreateNewLocalVault,
                    initializerClass = SkaldVaultV1VaultCreationInitializerClass.PassphrasePolicyEvidenceOnly,
                ),
            ),
        )

        assertTrue(composed.unlockAuthorizationEvidenceConsumed)
        assertTrue(composed.secureStorageAuthorizationEvidenceConsumed)
        assertTrue(composed.kdfCalibrationAuthorizationEvidenceConsumed)
        assertTrue(composed.runtimeRandomnessAuthorizationEvidenceConsumed)
        assertTrue(composed.providerOperationAuthorizationEvidenceConsumed)
        assertTrue(composed.passphrasePolicyEvidenceConsumed)
        assertTrue(composed.secureStorageCapabilityEvidenceConsumed)
        assertTrue(composed.secureMetadataCapabilityEvidenceConsumed)
        assertTrue(composed.lockSessionLifecycleEvidenceConsumed)
        assertTrue(composed.redactionLeakageEvidenceConsumed)
        assertTrue(composed.clearWipeStrategyEvidenceConsumed)
        assertTrue(composed.migrationCorruptionEvidenceConsumed)
        assertTrue(composed.persistenceReadinessEvidenceConsumed)
        assertTrue(composed.disabledStorageFacadeEvidenceConsumed)
        assertTrue(composed.providerSelectionEvidenceConsumed)
        assertTrue(composed.providerAcceptanceEvidenceConsumed)
        assertTrue(composed.dependencyProbeEvidenceConsumed)
        assertTrue(composed.encryptedVaultReadinessEvidenceConsumed)
        assertEquals(SkaldVaultV1VaultCreationAuthorizationDecision.BlockedFailClosed, composed.decision)
        assertContains(composed.blockers, SkaldVaultV1VaultCreationAuthorizationBlocker.PostCreateUnlockUnavailable)
        assertContains(composed.blockers, SkaldVaultV1VaultCreationAuthorizationBlocker.SecureStorageAuthorizationBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultCreationAuthorizationBlocker.KdfCalibrationAuthorizationBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultCreationAuthorizationBlocker.RuntimeRandomnessAuthorizationBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultCreationAuthorizationBlocker.ProviderOperationAuthorizationBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultCreationAuthorizationBlocker.PassphrasePolicyBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultCreationAuthorizationBlocker.SecureSecretStorageUnavailable)
        assertContains(composed.blockers, SkaldVaultV1VaultCreationAuthorizationBlocker.SecureMetadataStorageUnavailable)
        assertContains(composed.blockers, SkaldVaultV1VaultCreationAuthorizationBlocker.DisabledStorageFacadeBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultCreationAuthorizationBlocker.StorageSafetyPreflightBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultCreationAuthorizationBlocker.PlatformRootPathSafetyBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultCreationAuthorizationBlocker.MigrationCorruptionBoundaryBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultCreationAuthorizationBlocker.ClearWipeStrategyBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultCreationAuthorizationBlocker.LockSessionLifecycleBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultCreationAuthorizationBlocker.RedactionLeakageUnsafe)
        assertContains(composed.blockers, SkaldVaultV1VaultCreationAuthorizationBlocker.PersistenceReadinessBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultCreationAuthorizationBlocker.DisabledProviderSelected)
        assertContains(composed.blockers, SkaldVaultV1VaultCreationAuthorizationBlocker.ProductionProviderSelectableFalse)
        assertContains(composed.blockers, SkaldVaultV1VaultCreationAuthorizationBlocker.WarningOnlyEvidenceRejected)
        assertContains(composed.blockers, SkaldVaultV1VaultCreationAuthorizationBlocker.UserConsentOverrideRejected)
        assertDisabled(composed.capability)
    }

    @Test
    fun initializerScopeDecisionsRemainRejectedOrUnsupported() {
        val noInitializer = blockedInitializer(SkaldVaultV1VaultCreationInitializerClass.NoInitializerSupplied)
        val generatedKey = blockedInitializer(SkaldVaultV1VaultCreationInitializerClass.GeneratedKeyInitializerBlocked)
        val osKeyring = blockedInitializer(SkaldVaultV1VaultCreationInitializerClass.OsKeyringInitializerRejectedAsPrimaryStorage)
        val passwordManager = blockedInitializer(
            SkaldVaultV1VaultCreationInitializerClass.PasswordManagerInitializerRejectedForSkaldManagedPassphraseStorage,
        )
        val testOnly = blockedInitializer(
            SkaldVaultV1VaultCreationInitializerClass.TestOnlyPlaceholderInitializerRejectedForProduction,
        )
        val unknown = blockedInitializer(SkaldVaultV1VaultCreationInitializerClass.UnknownInitializer)
        val mainnet = blocked(
            SkaldVaultV1CreationAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultCreationAuthorizationRequest.forOperationKind(
                    SkaldVaultV1VaultCreationOperationKind.MainnetCreationAttempt,
                ),
            ),
        )

        assertEquals(SkaldVaultV1VaultCreationAuthorizationDecision.RejectInitializer, noInitializer.decision)
        assertEquals(SkaldVaultV1VaultCreationAuthorizationDecision.RejectInitializer, generatedKey.decision)
        assertContains(generatedKey.blockers, SkaldVaultV1VaultCreationAuthorizationBlocker.InitialKeyMaterialUnavailable)
        assertEquals(SkaldVaultV1VaultCreationAuthorizationDecision.RejectInitializer, osKeyring.decision)
        assertContains(osKeyring.blockers, SkaldVaultV1VaultCreationAuthorizationBlocker.OsKeyringPrimaryStorageRejected)
        assertEquals(SkaldVaultV1VaultCreationAuthorizationDecision.RejectInitializer, passwordManager.decision)
        assertContains(
            passwordManager.blockers,
            SkaldVaultV1VaultCreationAuthorizationBlocker.PasswordManagerPassphraseStorageRejected,
        )
        assertEquals(
            SkaldVaultV1VaultCreationAuthorizationDecision.TestOnlyInitializerRejectedForProduction,
            testOnly.decision,
        )
        assertContains(testOnly.blockers, SkaldVaultV1VaultCreationAuthorizationBlocker.TestOnlySimulationRejectedForProduction)
        assertEquals(SkaldVaultV1VaultCreationAuthorizationDecision.UnsupportedFailClosed, unknown.decision)
        assertContains(unknown.blockers, SkaldVaultV1VaultCreationAuthorizationBlocker.UnknownUnsupportedInitializer)
        assertEquals(SkaldVaultV1VaultCreationAuthorizationDecision.RejectMainnet, mainnet.decision)
        assertContains(mainnet.blockers, SkaldVaultV1VaultCreationAuthorizationBlocker.MainnetUnavailable)
    }

    @Test
    fun renderingAndRawCandidateRejectionRemainRedacted() {
        val raw = "wrapped-key-candidate"
        val request = SkaldVaultV1VaultCreationAuthorizationRequest.rawCreationCandidate(raw)
        val result = SkaldVaultV1CreationAuthorizationPolicy.evaluate(request)
        val rejected = assertIs<SkaldVaultV1VaultCreationAuthorizationResult.Rejected>(result)
        val evidence = blocked(
            SkaldVaultV1CreationAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultCreationAuthorizationRequest.summary(),
            ),
        )

        assertEquals(SkaldVaultV1VaultCreationFailureReason.WrappedKeyBytesRejected, rejected.reason)
        assertFalse(request.toString().contains(raw))
        assertFalse(result.toString().contains(raw))
        assertFalse(evidence.toString().contains(raw))
        assertFalse(evidence.policyTokenEvidence.toString().contains(raw))
        assertFalse(evidence.policyTokenEvidence.containsPassphrase)
        assertFalse(evidence.policyTokenEvidence.containsPin)
        assertFalse(evidence.policyTokenEvidence.containsBiometricDetails)
        assertFalse(evidence.policyTokenEvidence.containsKeyMaterial)
        assertFalse(evidence.policyTokenEvidence.containsWrappedKeyBytes)
        assertFalse(evidence.policyTokenEvidence.containsProviderHandle)
        assertFalse(evidence.policyTokenEvidence.containsSecureStorageHandle)
        assertFalse(evidence.policyTokenEvidence.containsOsKeyringHandle)
        assertFalse(evidence.policyTokenEvidence.containsPasswordManagerEntry)
        assertFalse(evidence.policyTokenEvidence.containsAndroidKeystoreHandle)
        assertFalse(evidence.policyTokenEvidence.containsCredentialManagerCredential)
        assertFalse(evidence.policyTokenEvidence.containsKdfInputOutput)
        assertFalse(evidence.policyTokenEvidence.containsSaltNonceRandomBytes)
        assertFalse(evidence.policyTokenEvidence.containsHeaderCommitmentBytes)
        assertFalse(evidence.policyTokenEvidence.containsHeaderContainerManifestIndexRecordBytes)
        assertFalse(evidence.policyTokenEvidence.containsMetadataPayload)
        assertFalse(evidence.policyTokenEvidence.containsCiphertextPlaintextTagBytes)
        assertFalse(evidence.policyTokenEvidence.containsRecordIdentifier)
        assertFalse(evidence.policyTokenEvidence.containsRootOrPathText)
        assertFalse(evidence.policyTokenEvidence.containsPayload)

        assertRejected("pass" + "phrase-candidate", SkaldVaultV1VaultCreationFailureReason.ActualPassphraseRejected)
        assertRejected("pin-string", SkaldVaultV1VaultCreationFailureReason.PinStringRejected)
        assertRejected("biometric-result", SkaldVaultV1VaultCreationFailureReason.BiometricResultRejected)
        assertRejected("credential-bytes", SkaldVaultV1VaultCreationFailureReason.CredentialBytesRejected)
        assertRejected("generated-key-bytes", SkaldVaultV1VaultCreationFailureReason.GeneratedKeyBytesRejected)
        assertRejected("android-keystore-key", SkaldVaultV1VaultCreationFailureReason.AndroidKeystoreKeyRejected)
        assertRejected("os-keyring-handle", SkaldVaultV1VaultCreationFailureReason.OsKeyringHandleRejected)
        assertRejected(
            "password-manager-entry",
            SkaldVaultV1VaultCreationFailureReason.PasswordManagerEntryRejected,
        )
        assertRejected("wrapped-key-bytes", SkaldVaultV1VaultCreationFailureReason.WrappedKeyBytesRejected)
        assertRejected("secure-storage-handle", SkaldVaultV1VaultCreationFailureReason.SecureStorageHandleRejected)
        assertRejected("provider-handle", SkaldVaultV1VaultCreationFailureReason.ProviderHandleRejected)
        assertRejected("decrypted-key-material", SkaldVaultV1VaultCreationFailureReason.DecryptedKeyMaterialRejected)
        assertRejected("session-key", SkaldVaultV1VaultCreationFailureReason.SessionKeyRejected)
        assertRejected("root-key", SkaldVaultV1VaultCreationFailureReason.RootRecordMetadataKeyRejected)
        assertRejected("seed-bytes", SkaldVaultV1VaultCreationFailureReason.SeedPrivateMnemonicRejected)
        assertRejected("kdf-input", SkaldVaultV1VaultCreationFailureReason.KdfInputOutputRejected)
        assertRejected("salt-bytes", SkaldVaultV1VaultCreationFailureReason.SaltNonceRandomBytesRejected)
        assertRejected("ciphertext-candidate", SkaldVaultV1VaultCreationFailureReason.CiphertextPlaintextRecordBytesRejected)
        assertRejected("header-commitment-bytes", SkaldVaultV1VaultCreationFailureReason.HeaderCommitmentAadBytesRejected)
        assertRejected(
            "manifest-bytes",
            SkaldVaultV1VaultCreationFailureReason.RawHeaderContainerManifestStorageIndexRecordBytesRejected,
        )
        assertRejected("bytearray-candidate", SkaldVaultV1VaultCreationFailureReason.ByteArrayInputRejected)
        assertRejected("chararray-candidate", SkaldVaultV1VaultCreationFailureReason.CharArrayInputRejected)
        assertRejected("random-object", SkaldVaultV1VaultCreationFailureReason.RandomObjectInputRejected)
        assertRejected("settings-value", SkaldVaultV1VaultCreationFailureReason.RawSettingsValueRejected)
        assertRejected("/vault/root", SkaldVaultV1VaultCreationFailureReason.RawAbsoluteLocationInputRejected)
        assertRejected("vault/root", SkaldVaultV1VaultCreationFailureReason.RawRelativeLocationInputRejected)
        assertRejected("https://example.invalid", SkaldVaultV1VaultCreationFailureReason.LinkLikeInputRejected)
        assertRejected("file-object", SkaldVaultV1VaultCreationFailureReason.PlatformObjectLikeInputRejected)
        assertRejected("a".repeat(64), SkaldVaultV1VaultCreationFailureReason.TransactionLikeEvidenceRejected)
        assertRejected(
            "bc" + "1q" + "a".repeat(24),
            SkaldVaultV1VaultCreationFailureReason.BitcoinAddressLikeEvidenceRejected,
        )
        assertRejected("ns" + "ec1candidate", SkaldVaultV1VaultCreationFailureReason.WalletMaterialRejected)
        assertRejected("xp" + "rvcandidate", SkaldVaultV1VaultCreationFailureReason.WalletMaterialRejected)
        assertRejected("tp" + "rvcandidate", SkaldVaultV1VaultCreationFailureReason.WalletMaterialRejected)
        assertRejected("K" + "a".repeat(50), SkaldVaultV1VaultCreationFailureReason.WalletMaterialRejected)
        assertRejected("../vault", SkaldVaultV1VaultCreationFailureReason.TraversalRejected)
        assertRejected("unsupported#chars", SkaldVaultV1VaultCreationFailureReason.UnsupportedCharactersRejected)
    }

    @Test
    fun allCurrentCapabilitiesRemainDisabled() {
        assertDisabled(
            blocked(
                SkaldVaultV1CreationAuthorizationPolicy.evaluate(
                    SkaldVaultV1VaultCreationAuthorizationRequest.summary(),
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
            evidence.stateFor(ProductionProviderAcceptanceGate.CreationAuthorizationBoundaryImplementedAndTested),
        )
        assertEquals(SkaldVaultV1CreationAuthorizationPolicy.POLICY_ID, storageContract.creationAuthorizationBoundaryPolicyId)
        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            storageContract.creationAuthorizationBoundaryStatus,
        )
        assertEquals(
            ProductionProviderCreationAuthorizationBoundaryRule.entries.toSet(),
            storageContract.creationAuthorizationBoundaryRules,
        )
        assertTrue(storageContract.creationAuthorizationBoundaryModeled)
        assertTrue(storageContract.creationAuthorizationStillDisabled)
        assertTrue(storageContract.creationAuthorizationBlocksAllOperations)
        assertTrue(storageContract.creationAuthorizationDoesNotAcceptPassphrases)
        assertTrue(storageContract.creationAuthorizationDoesNotGenerateKeys)
        assertTrue(storageContract.creationAuthorizationDoesNotRunKdf)
        assertTrue(storageContract.creationAuthorizationDoesNotWriteStorage)
        assertTrue(storageContract.creationAuthorizationDoesNotCreateSession)
        assertTrue(storageContract.creationAuthorizationDoesNotEnablePersistence)
        assertTrue(storageContract.creationAuthorizationDoesNotEnableProviderSelection)
        assertTrue(storageContract.creationAuthorizationFailureVocabularyModeled)
        assertFalse(storageContract.vaultPersistenceImplemented)
        assertContains(readiness.capabilities, EncryptedVaultCapability.CreationAuthorizationBoundaryBuildingBlock)
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.CreationAuthorizationBoundaryImplementedAndTested
            ],
        )
        assertContains(readiness.blockers, EncryptedVaultBlockingIssue.CreationAuthorizationBoundaryStillDisabled)
        assertContains(
            dependency.capabilities,
            VaultCryptoDependencyCapability.CreationAuthorizationBoundaryImplementedTested,
        )
        assertContains(
            dependency.capabilities,
            VaultCryptoDependencyCapability.CreationAuthorizationFailureVocabularyModeled,
        )
        assertContains(
            dependency.blockers,
            VaultCryptoDependencyBlocker.CreationAuthorizationBoundaryStillDisabled,
        )
        assertContains(
            dependency.blockers,
            VaultCryptoDependencyBlocker.CreationAuthorizationRuntimeReviewMissing,
        )
        assertContains(
            dependency.blockers,
            VaultCryptoDependencyBlocker.CreationAuthorizationTestsMissing,
        )
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, providerSelection.selectedCandidateId)
        assertFalse(providerSelection.productionProviderSelectable)
        assertFalse(providerSelection.requestedCandidate.productionSelectable)
    }

    private fun blockedInitializer(
        initializerClass: SkaldVaultV1VaultCreationInitializerClass,
    ): SkaldVaultV1VaultCreationAuthorizationEvidence =
        blocked(
            SkaldVaultV1CreationAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultCreationAuthorizationRequest.forInitializerClass(initializerClass),
            ),
        )

    private fun unlockAuthorizationEvidence(): SkaldVaultV1VaultUnlockAuthorizationEvidence =
        assertIs<
            SkaldVaultV1VaultUnlockAuthorizationResult.Blocked<
                SkaldVaultV1VaultUnlockAuthorizationEvidence,
            >,
        >(
            SkaldVaultV1UnlockAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultUnlockAuthorizationRequest.forOperationKind(
                    SkaldVaultV1VaultUnlockOperationKind.PassphraseUnlockAttempt,
                ),
            ),
        ).value

    private fun providerOperationEvidence(): SkaldVaultV1VaultProviderOperationAuthorizationEvidence =
        assertIs<
            SkaldVaultV1VaultProviderOperationAuthorizationResult.Blocked<
                SkaldVaultV1VaultProviderOperationAuthorizationEvidence,
            >,
        >(
            SkaldVaultV1ProviderOperationAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultProviderOperationAuthorizationRequest.forOperationKind(
                    SkaldVaultV1VaultProviderOperationKind.VaultCreate,
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

    private fun secureStorageAuthorizationEvidence(): SkaldVaultV1VaultSecureStorageAuthorizationEvidence =
        assertIs<
            SkaldVaultV1VaultSecureStorageAuthorizationResult.Blocked<
                SkaldVaultV1VaultSecureStorageAuthorizationEvidence,
            >,
        >(
            SkaldVaultV1SecureStorageAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultSecureStorageAuthorizationRequest.forOperationKind(
                    SkaldVaultV1VaultSecureStorageOperationKind.StoreWrappedKey,
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
        candidate: String,
        reason: SkaldVaultV1VaultCreationFailureReason,
    ) {
        val request = SkaldVaultV1VaultCreationAuthorizationRequest.rawCreationCandidate(candidate)
        val result = SkaldVaultV1CreationAuthorizationPolicy.evaluate(request)
        val rejected = assertIs<SkaldVaultV1VaultCreationAuthorizationResult.Rejected>(result)

        assertTrue(request.rawCandidateRejected)
        assertEquals(reason, rejected.reason)
        assertFalse(result.toString().contains(candidate))
    }

    private fun blocked(
        result: SkaldVaultV1VaultCreationAuthorizationResult<
            SkaldVaultV1VaultCreationAuthorizationEvidence,
        >,
    ): SkaldVaultV1VaultCreationAuthorizationEvidence =
        assertIs<
            SkaldVaultV1VaultCreationAuthorizationResult.Blocked<
                SkaldVaultV1VaultCreationAuthorizationEvidence,
            >,
        >(result).value

    private fun assertDisabled(capability: SkaldVaultV1VaultCreationAuthorizationCapability) {
        assertFalse(capability.creationAuthorized)
        assertFalse(capability.creationAttemptAvailable)
        assertFalse(capability.initialPassphraseAccepted)
        assertFalse(capability.initialKeyMaterialAvailable)
        assertFalse(capability.initialSaltGenerationAvailable)
        assertFalse(capability.initialNonceGenerationAvailable)
        assertFalse(capability.initialKdfExecutionAvailable)
        assertFalse(capability.initialProviderOperationAvailable)
        assertFalse(capability.headerCreationAvailable)
        assertFalse(capability.headerCommitmentAvailable)
        assertFalse(capability.containerCreationAvailable)
        assertFalse(capability.manifestCreationAvailable)
        assertFalse(capability.storageIndexCreationAvailable)
        assertFalse(capability.recordCreationAvailable)
        assertFalse(capability.secureMetadataCreationAvailable)
        assertFalse(capability.wrappedKeyStorageAvailable)
        assertFalse(capability.storageNamespaceCreationAvailable)
        assertFalse(capability.initialPersistenceCommitAvailable)
        assertFalse(capability.postCreateUnlockAvailable)
        assertFalse(capability.creationRollbackAvailable)
        assertFalse(capability.creationFailureCleanupAvailable)
        assertFalse(capability.unlockAuthorized)
        assertFalse(capability.kdfCalibrationAuthorized)
        assertFalse(capability.runtimeRandomnessAuthorized)
        assertFalse(capability.providerOperationAuthorized)
        assertFalse(capability.secureStorageAuthorized)
        assertFalse(capability.secureSecretStorageAvailable)
        assertFalse(capability.secureMetadataStorageAvailable)
        assertFalse(capability.encryptedLocalVaultStorageAvailable)
        assertFalse(capability.storageServiceAvailable)
        assertFalse(capability.activeSessionAvailable)
        assertFalse(capability.decryptedKeyMaterialPresent)
        assertFalse(capability.providerSelectable)
        assertFalse(capability.productionProviderSelected)
        assertFalse(capability.providerCryptoAvailable)
        assertFalse(capability.vaultCreationAvailable)
        assertFalse(capability.vaultUnlockAvailable)
        assertFalse(capability.vaultPersistenceAvailable)
        assertFalse(capability.mainnetAvailable)
    }
}
