package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.AndroidArgon2idCalibrationEvidencePolicy
import com.libertasprimordium.skald.security.EncryptedVaultBlockingIssue
import com.libertasprimordium.skald.security.EncryptedVaultCapability
import com.libertasprimordium.skald.security.EncryptedVaultReadinessPolicy
import com.libertasprimordium.skald.security.EncryptedVaultRequirement
import com.libertasprimordium.skald.security.EncryptedVaultRequirementStatus
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidence
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidenceState
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceGate
import com.libertasprimordium.skald.security.ProductionProviderConstructionContractStatus
import com.libertasprimordium.skald.security.ProductionProviderKdfCalibrationAuthorizationBoundaryRule
import com.libertasprimordium.skald.security.SkaldVaultV1ClearWipeStrategyPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1KdfCalibrationAuthorizationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1LockSessionLifecyclePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1MigrationCorruptionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1PassphrasePolicyGate
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderOperationAuthorizationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1RedactionLeakagePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1RuntimeRandomnessAuthorizationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1VaultClearWipeEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultClearWipeRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultClearWipeResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultKdfBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1VaultKdfCalibrationAuthorizationDecision
import com.libertasprimordium.skald.security.SkaldVaultV1VaultKdfCalibrationAuthorizationEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultKdfCalibrationAuthorizationRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultKdfCalibrationAuthorizationResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultKdfCalibrationAuthorizationStatus
import com.libertasprimordium.skald.security.SkaldVaultV1VaultKdfCapability
import com.libertasprimordium.skald.security.SkaldVaultV1VaultKdfFailureReason
import com.libertasprimordium.skald.security.SkaldVaultV1VaultKdfOperationKind
import com.libertasprimordium.skald.security.SkaldVaultV1VaultKdfParameterKind
import com.libertasprimordium.skald.security.SkaldVaultV1VaultKdfPlatformClass
import com.libertasprimordium.skald.security.SkaldVaultV1VaultKdfPurpose
import com.libertasprimordium.skald.security.SkaldVaultV1VaultKdfRequiredGate
import com.libertasprimordium.skald.security.SkaldVaultV1VaultKdfWarning
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
import com.libertasprimordium.skald.security.SkaldVaultV1PersistenceReadinessGate
import com.libertasprimordium.skald.security.VaultCryptoDependencyBlocker
import com.libertasprimordium.skald.security.VaultCryptoDependencyCandidate
import com.libertasprimordium.skald.security.VaultCryptoDependencyCapability
import com.libertasprimordium.skald.security.VaultCryptoDependencyProbeCatalog
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import com.libertasprimordium.skald.security.commonArgon2idCalibrationPolicy
import com.libertasprimordium.skald.security.commonDisabledSecureMetadataCapability
import com.libertasprimordium.skald.security.commonDisabledSecureStorageCapability
import com.libertasprimordium.skald.security.commonProductionProviderAcceptanceContract
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultKdfCalibrationAuthorizationBoundaryTest {
    @Test
    fun defaultPolicyIsBlockedFailClosedAndCapabilitiesRemainDisabled() {
        val evidence = blocked(
            SkaldVaultV1KdfCalibrationAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultKdfCalibrationAuthorizationRequest.noEvidence(),
            ),
        )

        assertEquals(
            "skald-vault-v1-kdf-calibration-authorization-boundary-v1",
            SkaldVaultV1KdfCalibrationAuthorizationPolicy.POLICY_ID,
        )
        assertEquals(1, SkaldVaultV1KdfCalibrationAuthorizationPolicy.POLICY_VERSION)
        assertEquals(
            SkaldVaultV1VaultKdfCalibrationAuthorizationStatus.NoEvidenceAvailable,
            evidence.status,
        )
        assertEquals(
            SkaldVaultV1VaultKdfCalibrationAuthorizationDecision.BlockedFailClosed,
            evidence.decision,
        )
        assertContains(evidence.blockers, SkaldVaultV1VaultKdfBlocker.KdfCalibrationAuthorizationStillDisabled)
        assertContains(evidence.blockers, SkaldVaultV1VaultKdfBlocker.ProviderOperationAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1VaultKdfBlocker.RuntimeRandomnessAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1VaultKdfBlocker.PassphrasePolicyBlocked)
        assertContains(evidence.warnings, SkaldVaultV1VaultKdfWarning.NoArgon2idRun)
        assertTrue(evidence.kdfCalibrationAuthorizationBoundaryModeled)
        assertTrue(evidence.kdfCalibrationAuthorizationStillDisabled)
        assertTrue(evidence.kdfCalibrationAuthorizationBlocksAllOperations)
        assertTrue(evidence.kdfCalibrationAuthorizationDoesNotRunArgon2id)
        assertTrue(evidence.kdfCalibrationAuthorizationDoesNotRunCalibration)
        assertTrue(evidence.kdfCalibrationAuthorizationDoesNotApproveFinalParameters)
        assertTrue(evidence.kdfCalibrationAuthorizationDoesNotEnableUnlock)
        assertTrue(evidence.kdfCalibrationAuthorizationDoesNotEnablePersistence)
        assertTrue(evidence.kdfCalibrationAuthorizationDoesNotEnableProviderSelection)
        assertTrue(evidence.kdfCalibrationFailureVocabularyModeled)
        assertFalse(evidence.kdfCalibrationReady)
        assertFalse(evidence.kdfParameterApprovalReady)
        assertFalse(evidence.finalKdfParametersApproved)
        assertFalse(evidence.argon2idReady)
        assertFalse(evidence.argon2idExecutionReady)
        assertDisabled(evidence.capability)
    }

    @Test
    fun everyModeledKdfOperationIsUnauthorized() {
        val kinds = SkaldVaultV1VaultKdfOperationKind.entries.toSet()

        listOf(
            SkaldVaultV1VaultKdfOperationKind.KdfAvailabilityCheck,
            SkaldVaultV1VaultKdfOperationKind.Argon2idCalibrationRequest,
            SkaldVaultV1VaultKdfOperationKind.Argon2idParameterFinalizationRequest,
            SkaldVaultV1VaultKdfOperationKind.AndroidCalibrationCaptureReview,
            SkaldVaultV1VaultKdfOperationKind.LinuxCalibrationReview,
            SkaldVaultV1VaultKdfOperationKind.DesktopCalibrationReview,
            SkaldVaultV1VaultKdfOperationKind.LowMemoryDeviceClassReview,
            SkaldVaultV1VaultKdfOperationKind.HighMemoryDesktopClassReview,
            SkaldVaultV1VaultKdfOperationKind.InteractiveUnlockCostReview,
            SkaldVaultV1VaultKdfOperationKind.DosUxCostReview,
            SkaldVaultV1VaultKdfOperationKind.MemoryCostApproval,
            SkaldVaultV1VaultKdfOperationKind.IterationTimeCostApproval,
            SkaldVaultV1VaultKdfOperationKind.ParallelismApproval,
            SkaldVaultV1VaultKdfOperationKind.SaltLengthApproval,
            SkaldVaultV1VaultKdfOperationKind.OutputLengthApproval,
            SkaldVaultV1VaultKdfOperationKind.VersionIdApproval,
            SkaldVaultV1VaultKdfOperationKind.ParameterBoundsValidation,
            SkaldVaultV1VaultKdfOperationKind.TestVectorParameterReview,
            SkaldVaultV1VaultKdfOperationKind.ProductionRuntimeParameterReview,
            SkaldVaultV1VaultKdfOperationKind.ReleaseValidationParameterReview,
            SkaldVaultV1VaultKdfOperationKind.MainnetParameterReview,
            SkaldVaultV1VaultKdfOperationKind.KdfExecutionAuthorization,
        ).forEach { kind ->
            assertContains(kinds, kind)
            val evidence = blocked(
                SkaldVaultV1KdfCalibrationAuthorizationPolicy.evaluate(
                    SkaldVaultV1VaultKdfCalibrationAuthorizationRequest.forOperationKind(kind),
                ),
            )
            assertFalse(evidence.capability.kdfCalibrationAuthorized)
            assertFalse(evidence.capability.kdfExecutionAuthorized)
            assertFalse(evidence.decision.kdfCalibrationAllowed)
            assertFalse(evidence.decision.kdfExecutionAllowed)
        }
    }

    @Test
    fun purposeParameterPlatformAndGateVocabularyAreModeled() {
        val summary = SkaldVaultV1KdfCalibrationAuthorizationPolicy.currentPolicySummary()

        listOf(
            SkaldVaultV1VaultKdfPurpose.CreateVault,
            SkaldVaultV1VaultKdfPurpose.UnlockVault,
            SkaldVaultV1VaultKdfPurpose.DeriveRootVaultKey,
            SkaldVaultV1VaultKdfPurpose.DeriveMetadataKey,
            SkaldVaultV1VaultKdfPurpose.DeriveRecordKeyMaterial,
            SkaldVaultV1VaultKdfPurpose.DeriveWrappingMaterial,
            SkaldVaultV1VaultKdfPurpose.ValidateProviderKat,
            SkaldVaultV1VaultKdfPurpose.ValidateDeterministicKatVector,
            SkaldVaultV1VaultKdfPurpose.CalibrateAndroidClass,
            SkaldVaultV1VaultKdfPurpose.CalibrateLinuxDesktopClass,
            SkaldVaultV1VaultKdfPurpose.CalibrateReleaseProfile,
            SkaldVaultV1VaultKdfPurpose.ReviewProductionRuntimeProfile,
            SkaldVaultV1VaultKdfPurpose.ReviewMigrationCompatibility,
            SkaldVaultV1VaultKdfPurpose.BackupExportPreparation,
            SkaldVaultV1VaultKdfPurpose.RestoreImportPreparation,
            SkaldVaultV1VaultKdfPurpose.TestOnlyDeterministicVector,
            SkaldVaultV1VaultKdfPurpose.ReleaseValidation,
            SkaldVaultV1VaultKdfPurpose.MainnetValidation,
        ).forEach { purpose -> assertContains(summary.purposes, purpose) }

        listOf(
            SkaldVaultV1VaultKdfParameterKind.AlgorithmId,
            SkaldVaultV1VaultKdfParameterKind.Argon2idVersion,
            SkaldVaultV1VaultKdfParameterKind.MemoryCost,
            SkaldVaultV1VaultKdfParameterKind.IterationTimeCost,
            SkaldVaultV1VaultKdfParameterKind.Parallelism,
            SkaldVaultV1VaultKdfParameterKind.SaltLength,
            SkaldVaultV1VaultKdfParameterKind.OutputLength,
            SkaldVaultV1VaultKdfParameterKind.AssociatedProviderSuiteId,
            SkaldVaultV1VaultKdfParameterKind.PlatformClass,
            SkaldVaultV1VaultKdfParameterKind.DeviceClass,
            SkaldVaultV1VaultKdfParameterKind.CalibrationTimestampCategory,
            SkaldVaultV1VaultKdfParameterKind.CalibrationEnvironmentCategory,
            SkaldVaultV1VaultKdfParameterKind.CalibrationResultSummary,
            SkaldVaultV1VaultKdfParameterKind.AndroidCalibrationCaptureEvidence,
            SkaldVaultV1VaultKdfParameterKind.LinuxDesktopCalibrationEvidence,
            SkaldVaultV1VaultKdfParameterKind.ReleaseProfileEvidence,
            SkaldVaultV1VaultKdfParameterKind.TestVectorProfileEvidence,
            SkaldVaultV1VaultKdfParameterKind.MigrationCompatibilityEvidence,
            SkaldVaultV1VaultKdfParameterKind.UserExperienceThresholdEvidence,
            SkaldVaultV1VaultKdfParameterKind.DosThresholdEvidence,
            SkaldVaultV1VaultKdfParameterKind.FinalApprovalEvidence,
            SkaldVaultV1VaultKdfParameterKind.ManualReviewEvidence,
            SkaldVaultV1VaultKdfParameterKind.FailClosedEvidence,
        ).forEach { parameterKind -> assertContains(summary.parameterKinds, parameterKind) }

        listOf(
            SkaldVaultV1VaultKdfPlatformClass.AndroidModernSupportedDevice,
            SkaldVaultV1VaultKdfPlatformClass.AndroidLowMemoryUnsupportedFailClosed,
            SkaldVaultV1VaultKdfPlatformClass.AndroidHardwareBackedCapabilityPresentNotKdfApproval,
            SkaldVaultV1VaultKdfPlatformClass.AndroidHardwareBackedCapabilityAbsentNotKdfApproval,
            SkaldVaultV1VaultKdfPlatformClass.LinuxDesktop,
            SkaldVaultV1VaultKdfPlatformClass.LinuxLowMemoryUnsupportedFailClosed,
            SkaldVaultV1VaultKdfPlatformClass.DesktopHighMemoryProfile,
            SkaldVaultV1VaultKdfPlatformClass.TestOnlyDeterministicProfile,
            SkaldVaultV1VaultKdfPlatformClass.ReleaseValidationProfile,
            SkaldVaultV1VaultKdfPlatformClass.MainnetProfileBlocked,
            SkaldVaultV1VaultKdfPlatformClass.UnknownPlatform,
            SkaldVaultV1VaultKdfPlatformClass.UnsupportedPlatform,
        ).forEach { platformClass -> assertContains(summary.platformClasses, platformClass) }

        listOf(
            SkaldVaultV1VaultKdfRequiredGate.AlgorithmIsArgon2id,
            SkaldVaultV1VaultKdfRequiredGate.AlgorithmVersionIdApproved,
            SkaldVaultV1VaultKdfRequiredGate.ParameterBoundsReviewed,
            SkaldVaultV1VaultKdfRequiredGate.MemoryCostReviewed,
            SkaldVaultV1VaultKdfRequiredGate.IterationTimeCostReviewed,
            SkaldVaultV1VaultKdfRequiredGate.ParallelismReviewed,
            SkaldVaultV1VaultKdfRequiredGate.SaltLengthReviewed,
            SkaldVaultV1VaultKdfRequiredGate.OutputLengthReviewed,
            SkaldVaultV1VaultKdfRequiredGate.ProviderSuiteIdReviewed,
            SkaldVaultV1VaultKdfRequiredGate.ProviderOperationAuthorizationApproved,
            SkaldVaultV1VaultKdfRequiredGate.ProviderSelectableWhereProviderKdfIsUsed,
            SkaldVaultV1VaultKdfRequiredGate.ProviderKatsApprovedWhereProviderKdfIsUsed,
            SkaldVaultV1VaultKdfRequiredGate.RuntimeRandomnessAuthorizationApprovedForSaltGeneration,
            SkaldVaultV1VaultKdfRequiredGate.PassphrasePolicyApproved,
            SkaldVaultV1VaultKdfRequiredGate.PassphraseNormalizationEncodingPolicyApproved,
            SkaldVaultV1VaultKdfRequiredGate.ClearWipePolicyApproved,
            SkaldVaultV1VaultKdfRequiredGate.RedactionLeakagePolicyApproved,
            SkaldVaultV1VaultKdfRequiredGate.LockSessionLifecycleApproved,
            SkaldVaultV1VaultKdfRequiredGate.PersistenceReadinessApprovedWhereStorageUnlockIsInvolved,
            SkaldVaultV1VaultKdfRequiredGate.SecureSecretStorageApproved,
            SkaldVaultV1VaultKdfRequiredGate.SecureMetadataStorageApproved,
            SkaldVaultV1VaultKdfRequiredGate.AndroidCalibrationCaptureApprovedForAndroidRuntime,
            SkaldVaultV1VaultKdfRequiredGate.LinuxCalibrationApprovedForLinuxRuntime,
            SkaldVaultV1VaultKdfRequiredGate.DosUxThresholdsApproved,
            SkaldVaultV1VaultKdfRequiredGate.MigrationCompatibilityApprovedWhereOldVaultsAreInvolved,
            SkaldVaultV1VaultKdfRequiredGate.NoRawPassphraseKdfMaterialInDiagnostics,
            SkaldVaultV1VaultKdfRequiredGate.TestVectorProfileNotUsedForProductionRuntime,
            SkaldVaultV1VaultKdfRequiredGate.MainnetReleaseReviewApproved,
        ).forEach { gate -> assertContains(summary.requiredGates, gate) }
    }

    @Test
    fun evidenceInteractionsRemainBlockedAndFailClosed() {
        val composed = blocked(
            SkaldVaultV1KdfCalibrationAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultKdfCalibrationAuthorizationRequest.fromEvidence(
                    passphrasePolicyEvidence = passphraseEvidence(),
                    runtimeRandomnessAuthorizationEvidence = runtimeRandomnessEvidence(),
                    providerOperationAuthorizationEvidence = providerOperationEvidence(),
                    providerSelectionResult = VaultCryptoProviderSelectionRegistry.select(),
                    providerAcceptanceAssessment = commonProductionProviderAcceptanceContract().assess(),
                    dependencyProbeResult = dependencyProbeResult(),
                    argon2idCalibrationPolicy = commonArgon2idCalibrationPolicy(),
                    androidCalibrationAssessment = AndroidArgon2idCalibrationEvidencePolicy.assess(),
                    encryptedVaultReadiness = EncryptedVaultReadinessPolicy.disabled(),
                    persistenceReadinessEvidence = persistenceReadinessEvidence(),
                    lockSessionLifecycleEvidence = lockSessionEvidence(),
                    redactionLeakageEvidence = redactionEvidence(),
                    clearWipeStrategyEvidence = clearWipeEvidence(),
                    migrationCorruptionEvidence = migrationCorruptionEvidence(),
                    secureStorageCapability = commonDisabledSecureStorageCapability(),
                    secureMetadataCapability = commonDisabledSecureMetadataCapability(),
                    operationKind = SkaldVaultV1VaultKdfOperationKind.KdfExecutionAuthorization,
                    purpose = SkaldVaultV1VaultKdfPurpose.UnlockVault,
                    parameterKind = SkaldVaultV1VaultKdfParameterKind.MemoryCost,
                    platformClass = SkaldVaultV1VaultKdfPlatformClass.LinuxDesktop,
                ),
            ),
        )

        assertTrue(composed.passphrasePolicyEvidenceConsumed)
        assertTrue(composed.runtimeRandomnessAuthorizationEvidenceConsumed)
        assertTrue(composed.providerOperationAuthorizationEvidenceConsumed)
        assertTrue(composed.providerSelectionEvidenceConsumed)
        assertTrue(composed.providerAcceptanceEvidenceConsumed)
        assertTrue(composed.dependencyProbeEvidenceConsumed)
        assertTrue(composed.argon2idCalibrationPolicyEvidenceConsumed)
        assertTrue(composed.androidCalibrationEvidenceConsumed)
        assertTrue(composed.encryptedVaultReadinessEvidenceConsumed)
        assertTrue(composed.persistenceReadinessEvidenceConsumed)
        assertTrue(composed.lockSessionLifecycleEvidenceConsumed)
        assertTrue(composed.redactionLeakageEvidenceConsumed)
        assertTrue(composed.clearWipeStrategyEvidenceConsumed)
        assertTrue(composed.migrationCorruptionEvidenceConsumed)
        assertTrue(composed.secureStorageEvidenceConsumed)
        assertTrue(composed.secureMetadataEvidenceConsumed)
        assertEquals(SkaldVaultV1VaultKdfCalibrationAuthorizationDecision.BlockedFailClosed, composed.decision)
        assertContains(composed.blockers, SkaldVaultV1VaultKdfBlocker.ProviderOperationAuthorizationBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultKdfBlocker.RuntimeRandomnessAuthorizationBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultKdfBlocker.DisabledProviderSelected)
        assertContains(composed.blockers, SkaldVaultV1VaultKdfBlocker.ProductionProviderSelectableFalse)
        assertContains(composed.blockers, SkaldVaultV1VaultKdfBlocker.ProviderKatApprovalMissing)
        assertContains(composed.blockers, SkaldVaultV1VaultKdfBlocker.PassphrasePolicyBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultKdfBlocker.LockSessionLifecycleBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultKdfBlocker.PersistenceReadinessBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultKdfBlocker.RedactionLeakageUnsafe)
        assertContains(composed.blockers, SkaldVaultV1VaultKdfBlocker.ClearWipeStrategyBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultKdfBlocker.SecureSecretStorageUnavailable)
        assertContains(composed.blockers, SkaldVaultV1VaultKdfBlocker.SecureMetadataStorageUnavailable)
        assertContains(composed.blockers, SkaldVaultV1VaultKdfBlocker.LinuxCalibrationMissing)
        assertContains(composed.blockers, SkaldVaultV1VaultKdfBlocker.WarningOnlyEvidenceRejected)
        assertContains(composed.blockers, SkaldVaultV1VaultKdfBlocker.UserConsentOverrideRejected)
        assertDisabled(composed.capability)
    }

    @Test
    fun platformScopeDecisionsRemainRejectedOrUnsupported() {
        val testOnly = blocked(
            SkaldVaultV1KdfCalibrationAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultKdfCalibrationAuthorizationRequest.forPlatformClass(
                    platformClass = SkaldVaultV1VaultKdfPlatformClass.TestOnlyDeterministicProfile,
                    purpose = SkaldVaultV1VaultKdfPurpose.ReviewProductionRuntimeProfile,
                ),
            ),
        )
        val mainnet = blocked(
            SkaldVaultV1KdfCalibrationAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultKdfCalibrationAuthorizationRequest.forOperationKind(
                    SkaldVaultV1VaultKdfOperationKind.MainnetParameterReview,
                ),
            ),
        )
        val unknown = blocked(
            SkaldVaultV1KdfCalibrationAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultKdfCalibrationAuthorizationRequest.forPlatformClass(
                    SkaldVaultV1VaultKdfPlatformClass.UnknownPlatform,
                ),
            ),
        )

        assertEquals(
            SkaldVaultV1VaultKdfCalibrationAuthorizationDecision.TestOnlyScopeRejectedForProduction,
            testOnly.decision,
        )
        assertContains(testOnly.blockers, SkaldVaultV1VaultKdfBlocker.TestVectorProfileRejectedForProduction)
        assertEquals(SkaldVaultV1VaultKdfCalibrationAuthorizationDecision.RejectOperation, mainnet.decision)
        assertContains(mainnet.blockers, SkaldVaultV1VaultKdfBlocker.MainnetUnavailable)
        assertEquals(SkaldVaultV1VaultKdfCalibrationAuthorizationDecision.UnsupportedFailClosed, unknown.decision)
        assertContains(unknown.blockers, SkaldVaultV1VaultKdfBlocker.UnknownUnsupportedPlatformClass)
    }

    @Test
    fun renderingAndRawCandidateRejectionRemainRedacted() {
        val raw = "kdf-input-fixture"
        val request = SkaldVaultV1VaultKdfCalibrationAuthorizationRequest.rawKdfCandidate(raw)
        val result = SkaldVaultV1KdfCalibrationAuthorizationPolicy.evaluate(request)
        val rejected = assertIs<SkaldVaultV1VaultKdfCalibrationAuthorizationResult.Rejected>(result)
        val evidence = blocked(
            SkaldVaultV1KdfCalibrationAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultKdfCalibrationAuthorizationRequest.summary(),
            ),
        )

        assertEquals(SkaldVaultV1VaultKdfFailureReason.KdfInputBytesRejected, rejected.reason)
        assertFalse(request.toString().contains(raw))
        assertFalse(result.toString().contains(raw))
        assertFalse(evidence.toString().contains(raw))
        assertFalse(evidence.policyTokenEvidence.toString().contains(raw))
        assertFalse(evidence.policyTokenEvidence.containsPassphrase)
        assertFalse(evidence.policyTokenEvidence.containsSaltBytes)
        assertFalse(evidence.policyTokenEvidence.containsKdfInput)
        assertFalse(evidence.policyTokenEvidence.containsKdfOutput)
        assertFalse(evidence.policyTokenEvidence.containsBenchmarkLogs)
        assertFalse(evidence.policyTokenEvidence.containsHostOrDeviceDetails)
        assertFalse(evidence.policyTokenEvidence.containsRandomBytes)
        assertFalse(evidence.policyTokenEvidence.containsEntropyBytes)
        assertFalse(evidence.policyTokenEvidence.containsKeyMaterial)
        assertFalse(evidence.policyTokenEvidence.containsProviderHandle)
        assertFalse(evidence.policyTokenEvidence.containsCiphertext)
        assertFalse(evidence.policyTokenEvidence.containsPlaintext)
        assertFalse(evidence.policyTokenEvidence.containsTagOrHeaderCommitmentBytes)
        assertFalse(evidence.policyTokenEvidence.containsRecordIdentifier)
        assertFalse(evidence.policyTokenEvidence.containsRootOrPathText)
        assertFalse(evidence.policyTokenEvidence.containsPayload)

        assertRejected("pass" + "phrase-fixture", SkaldVaultV1VaultKdfFailureReason.ActualPassphraseRejected)
        assertRejected("salt-bytes", SkaldVaultV1VaultKdfFailureReason.SaltBytesRejected)
        assertRejected("kdf-input", SkaldVaultV1VaultKdfFailureReason.KdfInputBytesRejected)
        assertRejected("kdf-output", SkaldVaultV1VaultKdfFailureReason.KdfOutputBytesRejected)
        assertRejected("argon2id-output", SkaldVaultV1VaultKdfFailureReason.Argon2idOutputRejected)
        assertRejected("benchmark-log", SkaldVaultV1VaultKdfFailureReason.BenchmarkLogsRejected)
        assertRejected("host-details", SkaldVaultV1VaultKdfFailureReason.HostDetailsRejected)
        assertRejected("device-identifier", SkaldVaultV1VaultKdfFailureReason.DeviceIdentifierRejected)
        assertRejected("random-bytes", SkaldVaultV1VaultKdfFailureReason.RawRandomBytesRejected)
        assertRejected("entropy-bytes", SkaldVaultV1VaultKdfFailureReason.EntropyBytesRejected)
        assertRejected("provider-handle", SkaldVaultV1VaultKdfFailureReason.ProviderHandleRejected)
        assertRejected("crypto-provider-instance", SkaldVaultV1VaultKdfFailureReason.ProviderImplementationInstanceRejected)
        assertRejected("bytearray-fixture", SkaldVaultV1VaultKdfFailureReason.ByteArrayInputRejected)
        assertRejected("chararray-fixture", SkaldVaultV1VaultKdfFailureReason.CharArrayInputRejected)
        assertRejected("rng-object", SkaldVaultV1VaultKdfFailureReason.RandomObjectInputRejected)
        assertRejected("/vault/root", SkaldVaultV1VaultKdfFailureReason.RawAbsoluteLocationInputRejected)
        assertRejected("vault/root", SkaldVaultV1VaultKdfFailureReason.RawRelativeLocationInputRejected)
        assertRejected("https://example.invalid", SkaldVaultV1VaultKdfFailureReason.LinkLikeInputRejected)
        assertRejected("file-object", SkaldVaultV1VaultKdfFailureReason.PlatformObjectLikeInputRejected)
        assertRejected("a".repeat(64), SkaldVaultV1VaultKdfFailureReason.TransactionLikeEvidenceRejected)
        assertRejected("bc" + "1q" + "a".repeat(24), SkaldVaultV1VaultKdfFailureReason.BitcoinAddressLikeEvidenceRejected)
        assertRejected("ns" + "ec1fixture", SkaldVaultV1VaultKdfFailureReason.WalletMaterialRejected)
        assertRejected("xp" + "rvfixture", SkaldVaultV1VaultKdfFailureReason.WalletMaterialRejected)
        assertRejected("tp" + "rvfixture", SkaldVaultV1VaultKdfFailureReason.WalletMaterialRejected)
        assertRejected("K" + "a".repeat(50), SkaldVaultV1VaultKdfFailureReason.WalletMaterialRejected)
        assertRejected("../vault", SkaldVaultV1VaultKdfFailureReason.TraversalRejected)
        assertRejected("unsupported#chars", SkaldVaultV1VaultKdfFailureReason.UnsupportedCharactersRejected)
    }

    @Test
    fun allCurrentCapabilitiesRemainDisabled() {
        assertDisabled(
            blocked(
                SkaldVaultV1KdfCalibrationAuthorizationPolicy.evaluate(
                    SkaldVaultV1VaultKdfCalibrationAuthorizationRequest.summary(),
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
                ProductionProviderAcceptanceGate.KdfCalibrationAuthorizationBoundaryImplementedAndTested,
            ),
        )
        assertEquals(
            SkaldVaultV1KdfCalibrationAuthorizationPolicy.POLICY_ID,
            storageContract.kdfCalibrationAuthorizationBoundaryPolicyId,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            storageContract.kdfCalibrationAuthorizationBoundaryStatus,
        )
        assertEquals(
            ProductionProviderKdfCalibrationAuthorizationBoundaryRule.entries.toSet(),
            storageContract.kdfCalibrationAuthorizationBoundaryRules,
        )
        assertTrue(storageContract.kdfCalibrationAuthorizationBoundaryModeled)
        assertTrue(storageContract.kdfCalibrationAuthorizationStillDisabled)
        assertTrue(storageContract.kdfCalibrationAuthorizationBlocksAllOperations)
        assertTrue(storageContract.kdfCalibrationAuthorizationDoesNotRunArgon2id)
        assertTrue(storageContract.kdfCalibrationAuthorizationDoesNotRunCalibration)
        assertTrue(storageContract.kdfCalibrationAuthorizationDoesNotApproveFinalParameters)
        assertTrue(storageContract.kdfCalibrationAuthorizationDoesNotEnableUnlock)
        assertTrue(storageContract.kdfCalibrationAuthorizationDoesNotEnablePersistence)
        assertTrue(storageContract.kdfCalibrationAuthorizationDoesNotEnableProviderSelection)
        assertTrue(storageContract.kdfCalibrationFailureVocabularyModeled)
        assertFalse(storageContract.vaultPersistenceImplemented)
        assertContains(readiness.capabilities, EncryptedVaultCapability.KdfCalibrationAuthorizationBoundaryBuildingBlock)
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.KdfCalibrationAuthorizationBoundaryImplementedAndTested
            ],
        )
        assertContains(readiness.blockers, EncryptedVaultBlockingIssue.KdfCalibrationAuthorizationBoundaryStillDisabled)
        assertContains(
            dependency.capabilities,
            VaultCryptoDependencyCapability.KdfCalibrationAuthorizationBoundaryImplementedTested,
        )
        assertContains(
            dependency.capabilities,
            VaultCryptoDependencyCapability.KdfCalibrationAuthorizationFailureVocabularyModeled,
        )
        assertContains(
            dependency.blockers,
            VaultCryptoDependencyBlocker.KdfCalibrationAuthorizationBoundaryStillDisabled,
        )
        assertContains(
            dependency.blockers,
            VaultCryptoDependencyBlocker.KdfCalibrationAuthorizationRuntimeReviewMissing,
        )
        assertContains(
            dependency.blockers,
            VaultCryptoDependencyBlocker.KdfCalibrationAuthorizationTestsMissing,
        )
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, providerSelection.selectedCandidateId)
        assertFalse(providerSelection.productionProviderSelectable)
        assertFalse(providerSelection.requestedCandidate.productionSelectable)
    }

    private fun providerOperationEvidence(): SkaldVaultV1VaultProviderOperationAuthorizationEvidence =
        assertIs<
            SkaldVaultV1VaultProviderOperationAuthorizationResult.Blocked<
                SkaldVaultV1VaultProviderOperationAuthorizationEvidence,
            >,
        >(
            SkaldVaultV1ProviderOperationAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultProviderOperationAuthorizationRequest.forOperationKind(
                    SkaldVaultV1VaultProviderOperationKind.Argon2idKdfExecution,
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

    private fun persistenceReadinessEvidence(): SkaldVaultV1VaultPersistenceReadinessEvidence =
        assertIs<
            SkaldVaultV1VaultPersistenceReadinessResult.Blocked<SkaldVaultV1VaultPersistenceReadinessEvidence>,
        >(
            SkaldVaultV1PersistenceReadinessGate.evaluate(
                SkaldVaultV1VaultPersistenceReadinessRequest.noEvidence(),
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
                    SkaldVaultV1VaultRedactionValueKind.RawKdfOutput,
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
        reason: SkaldVaultV1VaultKdfFailureReason,
    ) {
        val request = SkaldVaultV1VaultKdfCalibrationAuthorizationRequest.rawKdfCandidate(candidate)
        val result = SkaldVaultV1KdfCalibrationAuthorizationPolicy.evaluate(request)
        val rejected = assertIs<SkaldVaultV1VaultKdfCalibrationAuthorizationResult.Rejected>(result)

        assertTrue(request.rawCandidateRejected)
        assertEquals(reason, rejected.reason)
        assertFalse(result.toString().contains(candidate ?: ""))
    }

    private fun blocked(
        result: SkaldVaultV1VaultKdfCalibrationAuthorizationResult<
            SkaldVaultV1VaultKdfCalibrationAuthorizationEvidence,
        >,
    ): SkaldVaultV1VaultKdfCalibrationAuthorizationEvidence =
        assertIs<
            SkaldVaultV1VaultKdfCalibrationAuthorizationResult.Blocked<
                SkaldVaultV1VaultKdfCalibrationAuthorizationEvidence,
            >,
        >(result).value

    private fun assertDisabled(capability: SkaldVaultV1VaultKdfCapability) {
        assertFalse(capability.kdfCalibrationAuthorized)
        assertFalse(capability.kdfExecutionAuthorized)
        assertFalse(capability.argon2idExecutionAvailable)
        assertFalse(capability.argon2idCalibrationAvailable)
        assertFalse(capability.finalKdfParametersApproved)
        assertFalse(capability.androidCalibrationApproved)
        assertFalse(capability.linuxCalibrationApproved)
        assertFalse(capability.memoryCostApproved)
        assertFalse(capability.iterationCostApproved)
        assertFalse(capability.parallelismApproved)
        assertFalse(capability.saltLengthApproved)
        assertFalse(capability.outputLengthApproved)
        assertFalse(capability.providerOperationAuthorized)
        assertFalse(capability.runtimeRandomnessAuthorized)
        assertFalse(capability.saltGenerationAvailable)
        assertFalse(capability.passphraseInputAccepted)
        assertFalse(capability.passphraseNormalized)
        assertFalse(capability.passphraseEncoded)
        assertFalse(capability.clearWipeApproved)
        assertFalse(capability.redactionApproved)
        assertFalse(capability.lockSessionApproved)
        assertFalse(capability.productionProviderSelected)
        assertFalse(capability.providerSelectable)
        assertFalse(capability.vaultCreationAvailable)
        assertFalse(capability.vaultUnlockAvailable)
        assertFalse(capability.vaultPersistenceAvailable)
        assertFalse(capability.secureSecretStorageAvailable)
        assertFalse(capability.secureMetadataStorageAvailable)
        assertFalse(capability.mainnetAvailable)
    }
}
