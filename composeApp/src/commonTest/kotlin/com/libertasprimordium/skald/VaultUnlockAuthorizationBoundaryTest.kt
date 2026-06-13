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
import com.libertasprimordium.skald.security.ProductionProviderUnlockAuthorizationBoundaryRule
import com.libertasprimordium.skald.security.SkaldVaultV1ClearWipeStrategyPolicy
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
import com.libertasprimordium.skald.security.SkaldVaultV1VaultUnlockAuthorizationBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1VaultUnlockAuthorizationCapability
import com.libertasprimordium.skald.security.SkaldVaultV1VaultUnlockAuthorizationDecision
import com.libertasprimordium.skald.security.SkaldVaultV1VaultUnlockAuthorizationEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultUnlockAuthorizationRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultUnlockAuthorizationResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultUnlockAuthorizationStatus
import com.libertasprimordium.skald.security.SkaldVaultV1VaultUnlockAuthorizationWarning
import com.libertasprimordium.skald.security.SkaldVaultV1VaultUnlockCredentialClass
import com.libertasprimordium.skald.security.SkaldVaultV1VaultUnlockFailureReason
import com.libertasprimordium.skald.security.SkaldVaultV1VaultUnlockOperationKind
import com.libertasprimordium.skald.security.SkaldVaultV1VaultUnlockPurpose
import com.libertasprimordium.skald.security.SkaldVaultV1VaultUnlockRequiredGate
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

class VaultUnlockAuthorizationBoundaryTest {
    @Test
    fun defaultPolicyIsBlockedFailClosedAndCapabilitiesRemainDisabled() {
        val evidence = blocked(
            SkaldVaultV1UnlockAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultUnlockAuthorizationRequest.noEvidence(),
            ),
        )

        assertEquals(
            "skald-vault-v1-unlock-authorization-boundary-v1",
            SkaldVaultV1UnlockAuthorizationPolicy.POLICY_ID,
        )
        assertEquals(1, SkaldVaultV1UnlockAuthorizationPolicy.POLICY_VERSION)
        assertEquals(SkaldVaultV1VaultUnlockAuthorizationStatus.NoEvidenceAvailable, evidence.status)
        assertEquals(SkaldVaultV1VaultUnlockAuthorizationDecision.BlockedFailClosed, evidence.decision)
        assertContains(evidence.blockers, SkaldVaultV1VaultUnlockAuthorizationBlocker.UnlockAuthorizationStillDisabled)
        assertContains(evidence.blockers, SkaldVaultV1VaultUnlockAuthorizationBlocker.PassphrasePolicyBlocked)
        assertContains(evidence.blockers, SkaldVaultV1VaultUnlockAuthorizationBlocker.KdfCalibrationAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1VaultUnlockAuthorizationBlocker.RuntimeRandomnessAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1VaultUnlockAuthorizationBlocker.ProviderOperationAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1VaultUnlockAuthorizationBlocker.SecureStorageAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1VaultUnlockAuthorizationBlocker.DisabledStorageFacadeBlocked)
        assertContains(evidence.blockers, SkaldVaultV1VaultUnlockAuthorizationBlocker.LockSessionLifecycleBlocked)
        assertContains(evidence.blockers, SkaldVaultV1VaultUnlockAuthorizationBlocker.PersistenceReadinessBlocked)
        assertContains(evidence.blockers, SkaldVaultV1VaultUnlockAuthorizationBlocker.DisabledProviderSelected)
        assertContains(evidence.warnings, SkaldVaultV1VaultUnlockAuthorizationWarning.NoUnlockImplementation)
        assertTrue(evidence.unlockAuthorizationBoundaryModeled)
        assertTrue(evidence.unlockAuthorizationStillDisabled)
        assertTrue(evidence.unlockAuthorizationBlocksAllOperations)
        assertTrue(evidence.unlockAuthorizationDoesNotAcceptPassphrases)
        assertTrue(evidence.unlockAuthorizationDoesNotRunKdf)
        assertTrue(evidence.unlockAuthorizationDoesNotReadStorage)
        assertTrue(evidence.unlockAuthorizationDoesNotCreateSession)
        assertTrue(evidence.unlockAuthorizationDoesNotEnablePersistence)
        assertTrue(evidence.unlockAuthorizationDoesNotEnableProviderSelection)
        assertTrue(evidence.unlockAuthorizationFailureVocabularyModeled)
        assertFalse(evidence.unlockReady)
        assertFalse(evidence.vaultUnlockReady)
        assertFalse(evidence.unlockAuthorized)
        assertFalse(evidence.activeSessionReady)
        assertFalse(evidence.passphraseAccepted)
        assertFalse(evidence.kdfReady)
        assertFalse(evidence.secureStorageReady)
        assertFalse(evidence.providerOperationAuthorized)
        assertFalse(evidence.providerCryptoReady)
        assertFalse(evidence.providerSelectable)
        assertFalse(evidence.vaultStorageAvailable)
        assertFalse(evidence.persistenceReady)
        assertFalse(evidence.vaultPersistenceReady)
        assertDisabled(evidence.capability)
    }

    @Test
    fun everyModeledUnlockOperationIsUnauthorized() {
        val kinds = SkaldVaultV1VaultUnlockOperationKind.entries.toSet()

        listOf(
            SkaldVaultV1VaultUnlockOperationKind.UnlockAvailabilityCheck,
            SkaldVaultV1VaultUnlockOperationKind.PassphraseUnlockAttempt,
            SkaldVaultV1VaultUnlockOperationKind.PinAssistedUnlockAttempt,
            SkaldVaultV1VaultUnlockOperationKind.BiometricAssistedUnlockAttempt,
            SkaldVaultV1VaultUnlockOperationKind.HardwareWrappedKeyUnlockAttempt,
            SkaldVaultV1VaultUnlockOperationKind.OsKeyringAssistedUnlockAttempt,
            SkaldVaultV1VaultUnlockOperationKind.PasswordManagerAssistedUnlockAttempt,
            SkaldVaultV1VaultUnlockOperationKind.RestoreImportUnlockAttempt,
            SkaldVaultV1VaultUnlockOperationKind.MigrationUnlockAttempt,
            SkaldVaultV1VaultUnlockOperationKind.RecoveryUnlockAttempt,
            SkaldVaultV1VaultUnlockOperationKind.TestOnlyUnlockSimulation,
            SkaldVaultV1VaultUnlockOperationKind.ReleaseValidationUnlockAttempt,
            SkaldVaultV1VaultUnlockOperationKind.ProductionRuntimeUnlockAttempt,
            SkaldVaultV1VaultUnlockOperationKind.MainnetUnlockAttempt,
            SkaldVaultV1VaultUnlockOperationKind.ActiveSessionCreation,
            SkaldVaultV1VaultUnlockOperationKind.ActiveSessionRefresh,
            SkaldVaultV1VaultUnlockOperationKind.ActiveSessionResume,
            SkaldVaultV1VaultUnlockOperationKind.ActiveSessionCloseLock,
            SkaldVaultV1VaultUnlockOperationKind.ActiveSessionFailureCleanup,
            SkaldVaultV1VaultUnlockOperationKind.ActiveSessionStatusQuery,
        ).forEach { kind ->
            assertContains(kinds, kind)
            val evidence = blocked(
                SkaldVaultV1UnlockAuthorizationPolicy.evaluate(
                    SkaldVaultV1VaultUnlockAuthorizationRequest.forOperationKind(kind),
                ),
            )
            assertFalse(evidence.capability.unlockAuthorized)
            assertFalse(evidence.capability.unlockAttemptAvailable)
            assertFalse(evidence.capability.activeSessionAvailable)
            assertFalse(evidence.decision.unlockAllowed)
            assertFalse(evidence.decision.unlockAttemptAllowed)
            assertFalse(evidence.decision.sessionCreationAllowed)
        }
    }

    @Test
    fun purposeCredentialAndGateVocabularyAreModeled() {
        val summary = SkaldVaultV1UnlockAuthorizationPolicy.currentPolicySummary()

        listOf(
            SkaldVaultV1VaultUnlockPurpose.CreateVault,
            SkaldVaultV1VaultUnlockPurpose.UnlockExistingVault,
            SkaldVaultV1VaultUnlockPurpose.InspectVaultStatus,
            SkaldVaultV1VaultUnlockPurpose.MigrateVault,
            SkaldVaultV1VaultUnlockPurpose.RecoverVault,
            SkaldVaultV1VaultUnlockPurpose.ImportBackup,
            SkaldVaultV1VaultUnlockPurpose.ExportBackup,
            SkaldVaultV1VaultUnlockPurpose.DecryptMetadata,
            SkaldVaultV1VaultUnlockPurpose.DecryptRecord,
            SkaldVaultV1VaultUnlockPurpose.PrepareWalletSync,
            SkaldVaultV1VaultUnlockPurpose.PrepareSigningFlow,
            SkaldVaultV1VaultUnlockPurpose.PrepareRecoveryCenterStatus,
            SkaldVaultV1VaultUnlockPurpose.PreparePrivacyAnalyzerStatus,
            SkaldVaultV1VaultUnlockPurpose.TestOnlyDeterministicSimulation,
            SkaldVaultV1VaultUnlockPurpose.ReleaseValidation,
            SkaldVaultV1VaultUnlockPurpose.ProductionRuntime,
            SkaldVaultV1VaultUnlockPurpose.MainnetValidation,
        ).forEach { purpose -> assertContains(summary.purposes, purpose) }

        listOf(
            SkaldVaultV1VaultUnlockCredentialClass.NoCredentialSupplied,
            SkaldVaultV1VaultUnlockCredentialClass.PassphraseNotAccepted,
            SkaldVaultV1VaultUnlockCredentialClass.PassphrasePolicyEvidenceOnly,
            SkaldVaultV1VaultUnlockCredentialClass.PinRejected,
            SkaldVaultV1VaultUnlockCredentialClass.BiometricConvenienceRejectedFutureOnly,
            SkaldVaultV1VaultUnlockCredentialClass.AndroidHardwareWrappedKeyEvidenceOnly,
            SkaldVaultV1VaultUnlockCredentialClass.AndroidKeystoreEvidenceFutureOnly,
            SkaldVaultV1VaultUnlockCredentialClass.OsKeyringEvidenceRejectedAsPrimaryStorage,
            SkaldVaultV1VaultUnlockCredentialClass.OsKeyringOptionalWrappingFutureOnly,
            SkaldVaultV1VaultUnlockCredentialClass.PasswordManagerEvidenceRejectedForSkaldManagedPassphraseStorage,
            SkaldVaultV1VaultUnlockCredentialClass.SecureStorageWrappedKeyEvidenceFutureOnly,
            SkaldVaultV1VaultUnlockCredentialClass.TestOnlyPlaceholderCredentialRejectedForProduction,
            SkaldVaultV1VaultUnlockCredentialClass.UnknownCredential,
            SkaldVaultV1VaultUnlockCredentialClass.UnsupportedCredential,
        ).forEach { credentialClass -> assertContains(summary.credentialClasses, credentialClass) }

        SkaldVaultV1VaultUnlockRequiredGate.entries.forEach { gate ->
            assertContains(summary.requiredGates, gate)
        }
    }

    @Test
    fun evidenceInteractionsRemainBlockedAndFailClosed() {
        val composed = blocked(
            SkaldVaultV1UnlockAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultUnlockAuthorizationRequest.fromEvidence(
                    passphrasePolicyEvidence = passphraseEvidence(),
                    kdfCalibrationAuthorizationEvidence = kdfCalibrationEvidence(),
                    runtimeRandomnessAuthorizationEvidence = runtimeRandomnessEvidence(),
                    providerOperationAuthorizationEvidence = providerOperationEvidence(),
                    secureStorageAuthorizationEvidence = secureStorageAuthorizationEvidence(),
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
                    operationKind = SkaldVaultV1VaultUnlockOperationKind.PassphraseUnlockAttempt,
                    purpose = SkaldVaultV1VaultUnlockPurpose.UnlockExistingVault,
                    credentialClass = SkaldVaultV1VaultUnlockCredentialClass.PassphrasePolicyEvidenceOnly,
                ),
            ),
        )

        assertTrue(composed.passphrasePolicyEvidenceConsumed)
        assertTrue(composed.kdfCalibrationAuthorizationEvidenceConsumed)
        assertTrue(composed.runtimeRandomnessAuthorizationEvidenceConsumed)
        assertTrue(composed.providerOperationAuthorizationEvidenceConsumed)
        assertTrue(composed.secureStorageAuthorizationEvidenceConsumed)
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
        assertEquals(SkaldVaultV1VaultUnlockAuthorizationDecision.BlockedFailClosed, composed.decision)
        assertContains(composed.blockers, SkaldVaultV1VaultUnlockAuthorizationBlocker.PassphrasePolicyBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultUnlockAuthorizationBlocker.KdfCalibrationAuthorizationBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultUnlockAuthorizationBlocker.RuntimeRandomnessAuthorizationBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultUnlockAuthorizationBlocker.ProviderOperationAuthorizationBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultUnlockAuthorizationBlocker.SecureStorageAuthorizationBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultUnlockAuthorizationBlocker.SecureSecretStorageUnavailable)
        assertContains(composed.blockers, SkaldVaultV1VaultUnlockAuthorizationBlocker.SecureMetadataStorageUnavailable)
        assertContains(composed.blockers, SkaldVaultV1VaultUnlockAuthorizationBlocker.DisabledStorageFacadeBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultUnlockAuthorizationBlocker.StorageSafetyPreflightBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultUnlockAuthorizationBlocker.MigrationCorruptionBoundaryBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultUnlockAuthorizationBlocker.ClearWipeStrategyBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultUnlockAuthorizationBlocker.RedactionLeakageUnsafe)
        assertContains(composed.blockers, SkaldVaultV1VaultUnlockAuthorizationBlocker.LockSessionLifecycleBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultUnlockAuthorizationBlocker.PersistenceReadinessBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultUnlockAuthorizationBlocker.DisabledProviderSelected)
        assertContains(composed.blockers, SkaldVaultV1VaultUnlockAuthorizationBlocker.ProductionProviderSelectableFalse)
        assertContains(composed.blockers, SkaldVaultV1VaultUnlockAuthorizationBlocker.WarningOnlyEvidenceRejected)
        assertContains(composed.blockers, SkaldVaultV1VaultUnlockAuthorizationBlocker.UserConsentOverrideRejected)
        assertDisabled(composed.capability)
    }

    @Test
    fun credentialScopeDecisionsRemainRejectedOrUnsupported() {
        val noCredential = blockedCredential(SkaldVaultV1VaultUnlockCredentialClass.NoCredentialSupplied)
        val pin = blockedCredential(SkaldVaultV1VaultUnlockCredentialClass.PinRejected)
        val biometric = blockedCredential(SkaldVaultV1VaultUnlockCredentialClass.BiometricConvenienceRejectedFutureOnly)
        val osKeyring = blockedCredential(SkaldVaultV1VaultUnlockCredentialClass.OsKeyringEvidenceRejectedAsPrimaryStorage)
        val passwordManager = blockedCredential(
            SkaldVaultV1VaultUnlockCredentialClass.PasswordManagerEvidenceRejectedForSkaldManagedPassphraseStorage,
        )
        val testOnly = blockedCredential(
            SkaldVaultV1VaultUnlockCredentialClass.TestOnlyPlaceholderCredentialRejectedForProduction,
        )
        val unknown = blockedCredential(SkaldVaultV1VaultUnlockCredentialClass.UnknownCredential)
        val mainnet = blocked(
            SkaldVaultV1UnlockAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultUnlockAuthorizationRequest.forOperationKind(
                    SkaldVaultV1VaultUnlockOperationKind.MainnetUnlockAttempt,
                ),
            ),
        )

        assertEquals(SkaldVaultV1VaultUnlockAuthorizationDecision.RejectCredential, noCredential.decision)
        assertEquals(SkaldVaultV1VaultUnlockAuthorizationDecision.RejectCredential, pin.decision)
        assertContains(pin.blockers, SkaldVaultV1VaultUnlockAuthorizationBlocker.PassphraseInputMechanismReviewMissing)
        assertEquals(SkaldVaultV1VaultUnlockAuthorizationDecision.RejectCredential, biometric.decision)
        assertContains(biometric.blockers, SkaldVaultV1VaultUnlockAuthorizationBlocker.AndroidLifecyclePolicyMissing)
        assertEquals(SkaldVaultV1VaultUnlockAuthorizationDecision.RejectCredential, osKeyring.decision)
        assertContains(osKeyring.blockers, SkaldVaultV1VaultUnlockAuthorizationBlocker.OsKeyringPrimaryStorageRejected)
        assertEquals(SkaldVaultV1VaultUnlockAuthorizationDecision.RejectCredential, passwordManager.decision)
        assertContains(
            passwordManager.blockers,
            SkaldVaultV1VaultUnlockAuthorizationBlocker.PasswordManagerPassphraseStorageRejected,
        )
        assertEquals(
            SkaldVaultV1VaultUnlockAuthorizationDecision.TestOnlyCredentialRejectedForProduction,
            testOnly.decision,
        )
        assertContains(testOnly.blockers, SkaldVaultV1VaultUnlockAuthorizationBlocker.TestOnlyCredentialRejectedForProduction)
        assertEquals(SkaldVaultV1VaultUnlockAuthorizationDecision.UnsupportedFailClosed, unknown.decision)
        assertContains(unknown.blockers, SkaldVaultV1VaultUnlockAuthorizationBlocker.UnknownUnsupportedCredential)
        assertEquals(SkaldVaultV1VaultUnlockAuthorizationDecision.RejectMainnet, mainnet.decision)
        assertContains(mainnet.blockers, SkaldVaultV1VaultUnlockAuthorizationBlocker.MainnetUnavailable)
    }

    @Test
    fun renderingAndRawCandidateRejectionRemainRedacted() {
        val raw = "wrapped-key-candidate"
        val request = SkaldVaultV1VaultUnlockAuthorizationRequest.rawUnlockCandidate(raw)
        val result = SkaldVaultV1UnlockAuthorizationPolicy.evaluate(request)
        val rejected = assertIs<SkaldVaultV1VaultUnlockAuthorizationResult.Rejected>(result)
        val evidence = blocked(
            SkaldVaultV1UnlockAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultUnlockAuthorizationRequest.summary(),
            ),
        )

        assertEquals(SkaldVaultV1VaultUnlockFailureReason.WrappedKeyBytesRejected, rejected.reason)
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
        assertFalse(evidence.policyTokenEvidence.containsMetadataPayload)
        assertFalse(evidence.policyTokenEvidence.containsManifestStorageIndexRecordBytes)
        assertFalse(evidence.policyTokenEvidence.containsCiphertextPlaintextTagBytes)
        assertFalse(evidence.policyTokenEvidence.containsRecordIdentifier)
        assertFalse(evidence.policyTokenEvidence.containsRootOrPathText)
        assertFalse(evidence.policyTokenEvidence.containsPayload)

        assertRejected("pass" + "phrase-candidate", SkaldVaultV1VaultUnlockFailureReason.ActualPassphraseRejected)
        assertRejected("pin-string", SkaldVaultV1VaultUnlockFailureReason.PinStringRejected)
        assertRejected("biometric-result", SkaldVaultV1VaultUnlockFailureReason.BiometricResultRejected)
        assertRejected("credential-bytes", SkaldVaultV1VaultUnlockFailureReason.CredentialBytesRejected)
        assertRejected("android-keystore-key", SkaldVaultV1VaultUnlockFailureReason.AndroidKeystoreKeyRejected)
        assertRejected("os-keyring-handle", SkaldVaultV1VaultUnlockFailureReason.OsKeyringHandleRejected)
        assertRejected(
            "password-manager-entry",
            SkaldVaultV1VaultUnlockFailureReason.PasswordManagerEntryRejected,
        )
        assertRejected("wrapped-key-bytes", SkaldVaultV1VaultUnlockFailureReason.WrappedKeyBytesRejected)
        assertRejected("secure-storage-handle", SkaldVaultV1VaultUnlockFailureReason.SecureStorageHandleRejected)
        assertRejected("provider-handle", SkaldVaultV1VaultUnlockFailureReason.ProviderHandleRejected)
        assertRejected("decrypted-key-material", SkaldVaultV1VaultUnlockFailureReason.DecryptedKeyMaterialRejected)
        assertRejected("session-key", SkaldVaultV1VaultUnlockFailureReason.SessionKeyRejected)
        assertRejected("root-key", SkaldVaultV1VaultUnlockFailureReason.RootRecordMetadataKeyRejected)
        assertRejected("seed-bytes", SkaldVaultV1VaultUnlockFailureReason.SeedPrivateMnemonicRejected)
        assertRejected("kdf-input", SkaldVaultV1VaultUnlockFailureReason.KdfInputOutputRejected)
        assertRejected("salt-bytes", SkaldVaultV1VaultUnlockFailureReason.SaltNonceRandomBytesRejected)
        assertRejected("ciphertext-candidate", SkaldVaultV1VaultUnlockFailureReason.CiphertextPlaintextRecordBytesRejected)
        assertRejected(
            "manifest-bytes",
            SkaldVaultV1VaultUnlockFailureReason.RawManifestStorageIndexContainerBytesRejected,
        )
        assertRejected("bytearray-candidate", SkaldVaultV1VaultUnlockFailureReason.ByteArrayInputRejected)
        assertRejected("chararray-candidate", SkaldVaultV1VaultUnlockFailureReason.CharArrayInputRejected)
        assertRejected("random-object", SkaldVaultV1VaultUnlockFailureReason.RandomObjectInputRejected)
        assertRejected("settings-value", SkaldVaultV1VaultUnlockFailureReason.RawSettingsValueRejected)
        assertRejected("/vault/root", SkaldVaultV1VaultUnlockFailureReason.RawAbsoluteLocationInputRejected)
        assertRejected("vault/root", SkaldVaultV1VaultUnlockFailureReason.RawRelativeLocationInputRejected)
        assertRejected("https://example.invalid", SkaldVaultV1VaultUnlockFailureReason.LinkLikeInputRejected)
        assertRejected("file-object", SkaldVaultV1VaultUnlockFailureReason.PlatformObjectLikeInputRejected)
        assertRejected("a".repeat(64), SkaldVaultV1VaultUnlockFailureReason.TransactionLikeEvidenceRejected)
        assertRejected(
            "bc" + "1q" + "a".repeat(24),
            SkaldVaultV1VaultUnlockFailureReason.BitcoinAddressLikeEvidenceRejected,
        )
        assertRejected("ns" + "ec1candidate", SkaldVaultV1VaultUnlockFailureReason.WalletMaterialRejected)
        assertRejected("xp" + "rvcandidate", SkaldVaultV1VaultUnlockFailureReason.WalletMaterialRejected)
        assertRejected("tp" + "rvcandidate", SkaldVaultV1VaultUnlockFailureReason.WalletMaterialRejected)
        assertRejected("K" + "a".repeat(50), SkaldVaultV1VaultUnlockFailureReason.WalletMaterialRejected)
        assertRejected("../vault", SkaldVaultV1VaultUnlockFailureReason.TraversalRejected)
        assertRejected("unsupported#chars", SkaldVaultV1VaultUnlockFailureReason.UnsupportedCharactersRejected)
    }

    @Test
    fun allCurrentCapabilitiesRemainDisabled() {
        assertDisabled(
            blocked(
                SkaldVaultV1UnlockAuthorizationPolicy.evaluate(
                    SkaldVaultV1VaultUnlockAuthorizationRequest.summary(),
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
            evidence.stateFor(ProductionProviderAcceptanceGate.UnlockAuthorizationBoundaryImplementedAndTested),
        )
        assertEquals(SkaldVaultV1UnlockAuthorizationPolicy.POLICY_ID, storageContract.unlockAuthorizationBoundaryPolicyId)
        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            storageContract.unlockAuthorizationBoundaryStatus,
        )
        assertEquals(
            ProductionProviderUnlockAuthorizationBoundaryRule.entries.toSet(),
            storageContract.unlockAuthorizationBoundaryRules,
        )
        assertTrue(storageContract.unlockAuthorizationBoundaryModeled)
        assertTrue(storageContract.unlockAuthorizationStillDisabled)
        assertTrue(storageContract.unlockAuthorizationBlocksAllOperations)
        assertTrue(storageContract.unlockAuthorizationDoesNotAcceptPassphrases)
        assertTrue(storageContract.unlockAuthorizationDoesNotRunKdf)
        assertTrue(storageContract.unlockAuthorizationDoesNotReadStorage)
        assertTrue(storageContract.unlockAuthorizationDoesNotCreateSession)
        assertTrue(storageContract.unlockAuthorizationDoesNotEnablePersistence)
        assertTrue(storageContract.unlockAuthorizationDoesNotEnableProviderSelection)
        assertTrue(storageContract.unlockAuthorizationFailureVocabularyModeled)
        assertFalse(storageContract.vaultPersistenceImplemented)
        assertContains(readiness.capabilities, EncryptedVaultCapability.UnlockAuthorizationBoundaryBuildingBlock)
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.UnlockAuthorizationBoundaryImplementedAndTested
            ],
        )
        assertContains(readiness.blockers, EncryptedVaultBlockingIssue.UnlockAuthorizationBoundaryStillDisabled)
        assertContains(
            dependency.capabilities,
            VaultCryptoDependencyCapability.UnlockAuthorizationBoundaryImplementedTested,
        )
        assertContains(
            dependency.capabilities,
            VaultCryptoDependencyCapability.UnlockAuthorizationFailureVocabularyModeled,
        )
        assertContains(
            dependency.blockers,
            VaultCryptoDependencyBlocker.UnlockAuthorizationBoundaryStillDisabled,
        )
        assertContains(
            dependency.blockers,
            VaultCryptoDependencyBlocker.UnlockAuthorizationRuntimeReviewMissing,
        )
        assertContains(
            dependency.blockers,
            VaultCryptoDependencyBlocker.UnlockAuthorizationTestsMissing,
        )
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, providerSelection.selectedCandidateId)
        assertFalse(providerSelection.productionProviderSelectable)
        assertFalse(providerSelection.requestedCandidate.productionSelectable)
    }

    private fun blockedCredential(
        credentialClass: SkaldVaultV1VaultUnlockCredentialClass,
    ): SkaldVaultV1VaultUnlockAuthorizationEvidence =
        blocked(
            SkaldVaultV1UnlockAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultUnlockAuthorizationRequest.forCredentialClass(credentialClass),
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
                    SkaldVaultV1VaultProviderOperationKind.VaultUnlock,
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
                    SkaldVaultV1VaultRandomnessOperationKind.KdfSaltRequest,
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
                    SkaldVaultV1VaultSecureStorageOperationKind.RetrieveWrappedKey,
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
                    SkaldVaultV1VaultStorageOperation.ReadCurrentContainer,
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
        reason: SkaldVaultV1VaultUnlockFailureReason,
    ) {
        val request = SkaldVaultV1VaultUnlockAuthorizationRequest.rawUnlockCandidate(candidate)
        val result = SkaldVaultV1UnlockAuthorizationPolicy.evaluate(request)
        val rejected = assertIs<SkaldVaultV1VaultUnlockAuthorizationResult.Rejected>(result)

        assertTrue(request.rawCandidateRejected)
        assertEquals(reason, rejected.reason)
        assertFalse(result.toString().contains(candidate ?: ""))
    }

    private fun blocked(
        result: SkaldVaultV1VaultUnlockAuthorizationResult<
            SkaldVaultV1VaultUnlockAuthorizationEvidence,
        >,
    ): SkaldVaultV1VaultUnlockAuthorizationEvidence =
        assertIs<
            SkaldVaultV1VaultUnlockAuthorizationResult.Blocked<
                SkaldVaultV1VaultUnlockAuthorizationEvidence,
            >,
        >(result).value

    private fun assertDisabled(capability: SkaldVaultV1VaultUnlockAuthorizationCapability) {
        assertFalse(capability.unlockAuthorized)
        assertFalse(capability.unlockAttemptAvailable)
        assertFalse(capability.passphraseInputAccepted)
        assertFalse(capability.pinInputAccepted)
        assertFalse(capability.biometricUnlockAvailable)
        assertFalse(capability.hardwareWrappedKeyUnlockAvailable)
        assertFalse(capability.osKeyringUnlockAvailable)
        assertFalse(capability.passwordManagerUnlockAvailable)
        assertFalse(capability.kdfCalibrationAuthorized)
        assertFalse(capability.runtimeRandomnessAuthorized)
        assertFalse(capability.providerOperationAuthorized)
        assertFalse(capability.secureStorageAuthorized)
        assertFalse(capability.secureSecretStorageAvailable)
        assertFalse(capability.secureMetadataStorageAvailable)
        assertFalse(capability.encryptedLocalVaultStorageAvailable)
        assertFalse(capability.storageServiceAvailable)
        assertFalse(capability.migrationCorruptionApproved)
        assertFalse(capability.clearWipeApproved)
        assertFalse(capability.redactionApproved)
        assertFalse(capability.lockSessionApproved)
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
