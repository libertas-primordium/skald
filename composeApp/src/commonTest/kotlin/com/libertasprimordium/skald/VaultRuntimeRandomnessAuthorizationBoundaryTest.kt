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
import com.libertasprimordium.skald.security.ProductionProviderRuntimeRandomnessAuthorizationBoundaryRule
import com.libertasprimordium.skald.security.SkaldVaultV1ClearWipeStrategyPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1DisabledStorageServiceFacade
import com.libertasprimordium.skald.security.SkaldVaultV1LockSessionLifecyclePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1MigrationCorruptionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1PassphrasePolicyGate
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderOperationAuthorizationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1RedactionLeakagePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1RuntimeRandomnessAuthorizationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1VaultClearWipeEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultClearWipeRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultClearWipeResult
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
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRandomnessBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRandomnessCapability
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRandomnessFailureReason
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRandomnessOperationKind
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRandomnessPurpose
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRandomnessRequiredGate
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRandomnessSourceKind
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRandomnessWarning
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRedactionEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRedactionRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRedactionResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRedactionValueKind
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRuntimeRandomnessAuthorizationDecision
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRuntimeRandomnessAuthorizationEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRuntimeRandomnessAuthorizationResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRuntimeRandomnessAuthorizationStatus
import com.libertasprimordium.skald.security.SkaldVaultV1VaultStorageDisabledEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultStorageOperation
import com.libertasprimordium.skald.security.SkaldVaultV1VaultStorageOperationRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultStorageOperationResult
import com.libertasprimordium.skald.security.SkaldVaultV1PersistenceReadinessGate
import com.libertasprimordium.skald.security.VaultCryptoDependencyBlocker
import com.libertasprimordium.skald.security.VaultCryptoDependencyCandidate
import com.libertasprimordium.skald.security.VaultCryptoDependencyCapability
import com.libertasprimordium.skald.security.VaultCryptoDependencyProbeCatalog
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import com.libertasprimordium.skald.security.commonDisabledSecureMetadataCapability
import com.libertasprimordium.skald.security.commonDisabledSecureStorageCapability
import com.libertasprimordium.skald.security.commonProductionProviderAcceptanceContract
import com.libertasprimordium.skald.security.commonRuntimeRandomnessProviderPolicy
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultRuntimeRandomnessAuthorizationBoundaryTest {
    @Test
    fun defaultPolicyIsBlockedFailClosedAndCapabilitiesRemainDisabled() {
        val evidence = blocked(
            SkaldVaultV1RuntimeRandomnessAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest.noEvidence(),
            ),
        )

        assertEquals(
            "skald-vault-v1-runtime-randomness-authorization-boundary-v1",
            SkaldVaultV1RuntimeRandomnessAuthorizationPolicy.POLICY_ID,
        )
        assertEquals(1, SkaldVaultV1RuntimeRandomnessAuthorizationPolicy.POLICY_VERSION)
        assertEquals(
            SkaldVaultV1VaultRuntimeRandomnessAuthorizationStatus.NoEvidenceAvailable,
            evidence.status,
        )
        assertEquals(
            SkaldVaultV1VaultRuntimeRandomnessAuthorizationDecision.BlockedFailClosed,
            evidence.decision,
        )
        assertContains(evidence.blockers, SkaldVaultV1VaultRandomnessBlocker.RuntimeRandomnessAuthorizationStillDisabled)
        assertContains(evidence.blockers, SkaldVaultV1VaultRandomnessBlocker.ProviderOperationAuthorizationBlocked)
        assertContains(evidence.blockers, SkaldVaultV1VaultRandomnessBlocker.PlatformRandomnessSourceReviewMissing)
        assertContains(evidence.warnings, SkaldVaultV1VaultRandomnessWarning.NoRandomnessApiCalled)
        assertTrue(evidence.runtimeRandomnessAuthorizationBoundaryModeled)
        assertTrue(evidence.runtimeRandomnessAuthorizationStillDisabled)
        assertTrue(evidence.runtimeRandomnessAuthorizationBlocksAllOperations)
        assertTrue(evidence.runtimeRandomnessAuthorizationDoesNotCallRandomApis)
        assertTrue(evidence.runtimeRandomnessAuthorizationDoesNotGenerateEntropy)
        assertTrue(evidence.runtimeRandomnessAuthorizationDoesNotGenerateSaltOrNonce)
        assertTrue(evidence.runtimeRandomnessAuthorizationDoesNotGenerateKeys)
        assertDisabled(evidence.capability)
    }

    @Test
    fun everyModeledRandomnessOperationIsUnauthorized() {
        val kinds = SkaldVaultV1VaultRandomnessOperationKind.entries.toSet()

        listOf(
            SkaldVaultV1VaultRandomnessOperationKind.RuntimeRandomnessAvailabilityCheck,
            SkaldVaultV1VaultRandomnessOperationKind.OsCryptographicRandomnessRequest,
            SkaldVaultV1VaultRandomnessOperationKind.ProviderRandomnessRequest,
            SkaldVaultV1VaultRandomnessOperationKind.HardwareBackedEntropyEvidenceRequest,
            SkaldVaultV1VaultRandomnessOperationKind.SaltGeneration,
            SkaldVaultV1VaultRandomnessOperationKind.NonceGeneration,
            SkaldVaultV1VaultRandomnessOperationKind.KeyGenerationEntropyRequest,
            SkaldVaultV1VaultRandomnessOperationKind.KdfSaltRequest,
            SkaldVaultV1VaultRandomnessOperationKind.AeadNonceRequest,
            SkaldVaultV1VaultRandomnessOperationKind.RecordNonceCounterSeedRequest,
            SkaldVaultV1VaultRandomnessOperationKind.ManifestNonceCounterSeedRequest,
            SkaldVaultV1VaultRandomnessOperationKind.StorageIndexNonceCounterSeedRequest,
            SkaldVaultV1VaultRandomnessOperationKind.BackupExportNonceSaltRequest,
            SkaldVaultV1VaultRandomnessOperationKind.MigrationNonceSaltRequest,
            SkaldVaultV1VaultRandomnessOperationKind.ProviderKatRandomnessRequest,
            SkaldVaultV1VaultRandomnessOperationKind.DeterministicTestVectorRandomnessRequest,
            SkaldVaultV1VaultRandomnessOperationKind.ProductionRuntimeRandomnessRequest,
            SkaldVaultV1VaultRandomnessOperationKind.ReleaseValidationRandomnessRequest,
            SkaldVaultV1VaultRandomnessOperationKind.MainnetRandomnessRequest,
        ).forEach { kind ->
            assertContains(kinds, kind)
            val evidence = blocked(
                SkaldVaultV1RuntimeRandomnessAuthorizationPolicy.evaluate(
                    SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest.forOperationKind(kind),
                ),
            )
            assertFalse(evidence.capability.randomnessOperationAuthorized)
            assertFalse(evidence.decision.randomnessOperationAllowed)
        }
    }

    @Test
    fun purposeSourceAndGateVocabularyAreModeled() {
        val summary = SkaldVaultV1RuntimeRandomnessAuthorizationPolicy.currentPolicySummary()

        listOf(
            SkaldVaultV1VaultRandomnessPurpose.CreateVault,
            SkaldVaultV1VaultRandomnessPurpose.UnlockVault,
            SkaldVaultV1VaultRandomnessPurpose.DeriveVaultKeys,
            SkaldVaultV1VaultRandomnessPurpose.GenerateSalt,
            SkaldVaultV1VaultRandomnessPurpose.GenerateAeadNonce,
            SkaldVaultV1VaultRandomnessPurpose.GenerateRecordNonce,
            SkaldVaultV1VaultRandomnessPurpose.GenerateMetadataNonce,
            SkaldVaultV1VaultRandomnessPurpose.GenerateStorageIndexNonce,
            SkaldVaultV1VaultRandomnessPurpose.GenerateManifestNonce,
            SkaldVaultV1VaultRandomnessPurpose.GenerateKeyWrappingMaterial,
            SkaldVaultV1VaultRandomnessPurpose.GenerateBackupExportMaterial,
            SkaldVaultV1VaultRandomnessPurpose.RunProviderKat,
            SkaldVaultV1VaultRandomnessPurpose.RunRuntimeHealthCheck,
            SkaldVaultV1VaultRandomnessPurpose.VerifyEntropySourceAvailability,
            SkaldVaultV1VaultRandomnessPurpose.MigrationRecoveryPlanning,
            SkaldVaultV1VaultRandomnessPurpose.TestOnlyDeterministicVector,
            SkaldVaultV1VaultRandomnessPurpose.ProductionRuntime,
            SkaldVaultV1VaultRandomnessPurpose.ReleaseValidation,
            SkaldVaultV1VaultRandomnessPurpose.MainnetValidation,
        ).forEach { purpose -> assertContains(summary.purposes, purpose) }

        listOf(
            SkaldVaultV1VaultRandomnessSourceKind.OsCryptographicRandomnessCsprng,
            SkaldVaultV1VaultRandomnessSourceKind.ProviderOwnedCryptographicRandomness,
            SkaldVaultV1VaultRandomnessSourceKind.HardwareBackedEntropyEvidence,
            SkaldVaultV1VaultRandomnessSourceKind.AndroidOsCsprngEvidence,
            SkaldVaultV1VaultRandomnessSourceKind.AndroidHardwareBackedCapabilityEvidence,
            SkaldVaultV1VaultRandomnessSourceKind.LinuxOsCsprngEvidence,
            SkaldVaultV1VaultRandomnessSourceKind.LinuxHardwareRngEvidence,
            SkaldVaultV1VaultRandomnessSourceKind.DeterministicTestVectorSource,
            SkaldVaultV1VaultRandomnessSourceKind.ForbiddenKotlinRandom,
            SkaldVaultV1VaultRandomnessSourceKind.ForbiddenJavaRandom,
            SkaldVaultV1VaultRandomnessSourceKind.ForbiddenTimeBasedRandomness,
            SkaldVaultV1VaultRandomnessSourceKind.ForbiddenMathRandom,
            SkaldVaultV1VaultRandomnessSourceKind.ForbiddenUserSuppliedRandomness,
            SkaldVaultV1VaultRandomnessSourceKind.ForbiddenNetworkSuppliedRandomness,
            SkaldVaultV1VaultRandomnessSourceKind.ForbiddenPersistedRandomness,
            SkaldVaultV1VaultRandomnessSourceKind.UnknownSource,
            SkaldVaultV1VaultRandomnessSourceKind.UnsupportedSource,
        ).forEach { source -> assertContains(summary.sourceKinds, source) }

        listOf(
            SkaldVaultV1VaultRandomnessRequiredGate.SourceIsNotForbidden,
            SkaldVaultV1VaultRandomnessRequiredGate.SourceIsOsCsprngOrReviewedProviderRandomness,
            SkaldVaultV1VaultRandomnessRequiredGate.SourceIsNotKotlinRandom,
            SkaldVaultV1VaultRandomnessRequiredGate.SourceIsNotJavaRandom,
            SkaldVaultV1VaultRandomnessRequiredGate.SourceIsNotMathRandom,
            SkaldVaultV1VaultRandomnessRequiredGate.SourceIsNotTimestampTimeBased,
            SkaldVaultV1VaultRandomnessRequiredGate.SourceIsNotUserSupplied,
            SkaldVaultV1VaultRandomnessRequiredGate.SourceIsNotNetworkSupplied,
            SkaldVaultV1VaultRandomnessRequiredGate.SourceIsNotPersistedOrReused,
            SkaldVaultV1VaultRandomnessRequiredGate.SourceReviewCompletedForPlatform,
            SkaldVaultV1VaultRandomnessRequiredGate.LinuxEntropyReviewCompletedWhereLinuxRuntimeIsUsed,
            SkaldVaultV1VaultRandomnessRequiredGate.AndroidEntropyReviewCompletedWhereAndroidRuntimeIsUsed,
            SkaldVaultV1VaultRandomnessRequiredGate.ProviderOperationAuthorizationApproved,
            SkaldVaultV1VaultRandomnessRequiredGate.ProviderSelectableWhereProviderRandomnessIsUsed,
            SkaldVaultV1VaultRandomnessRequiredGate.ProviderKatsApprovedWhereProviderRandomnessIsUsed,
            SkaldVaultV1VaultRandomnessRequiredGate.RuntimeRandomnessHealthCheckApproved,
            SkaldVaultV1VaultRandomnessRequiredGate.RedactionLeakagePolicyApproved,
            SkaldVaultV1VaultRandomnessRequiredGate.ClearWipePolicyApprovedWhereBuffersAreInvolved,
            SkaldVaultV1VaultRandomnessRequiredGate.PassphrasePolicyApprovedWhereKdfSaltIsUsedForUnlock,
            SkaldVaultV1VaultRandomnessRequiredGate.LockSessionApprovedWhereUnlockSessionUsesRandomness,
            SkaldVaultV1VaultRandomnessRequiredGate.PersistenceReadinessApprovedWhereStorageUsesRandomness,
            SkaldVaultV1VaultRandomnessRequiredGate.StorageSafetyApprovedWhereStorageUsesRandomness,
            SkaldVaultV1VaultRandomnessRequiredGate.MigrationCorruptionPolicyApprovedWhereMigrationRecoveryUsesRandomness,
            SkaldVaultV1VaultRandomnessRequiredGate.NoRawRandomEntropyBytesInDiagnostics,
            SkaldVaultV1VaultRandomnessRequiredGate.NoDeterministicTestVectorSourceInProductionRuntime,
            SkaldVaultV1VaultRandomnessRequiredGate.MainnetReleaseReviewApproved,
        ).forEach { gate -> assertContains(summary.requiredGates, gate) }
    }

    @Test
    fun evidenceInteractionsRemainBlockedAndFailClosed() {
        val composed = blocked(
            SkaldVaultV1RuntimeRandomnessAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest.fromEvidence(
                    providerOperationAuthorizationEvidence = providerOperationEvidence(),
                    providerSelectionResult = VaultCryptoProviderSelectionRegistry.select(),
                    providerAcceptanceAssessment = commonProductionProviderAcceptanceContract().assess(),
                    dependencyProbeResult = dependencyProbeResult(),
                    runtimeRandomnessPolicy = commonRuntimeRandomnessProviderPolicy(),
                    redactionLeakageEvidence = redactionEvidence(),
                    clearWipeStrategyEvidence = clearWipeEvidence(),
                    passphrasePolicyEvidence = passphraseEvidence(),
                    lockSessionLifecycleEvidence = lockSessionEvidence(),
                    persistenceReadinessEvidence = persistenceReadinessEvidence(),
                    disabledStorageServiceEvidence = disabledStorageEvidence(),
                    migrationCorruptionEvidence = migrationCorruptionEvidence(),
                    secureStorageCapability = commonDisabledSecureStorageCapability(),
                    secureMetadataCapability = commonDisabledSecureMetadataCapability(),
                    operationKind = SkaldVaultV1VaultRandomnessOperationKind.ProviderRandomnessRequest,
                    purpose = SkaldVaultV1VaultRandomnessPurpose.GenerateRecordNonce,
                    sourceKind = SkaldVaultV1VaultRandomnessSourceKind.ProviderOwnedCryptographicRandomness,
                ),
            ),
        )

        assertTrue(composed.providerOperationAuthorizationEvidenceConsumed)
        assertTrue(composed.providerSelectionEvidenceConsumed)
        assertTrue(composed.providerAcceptanceEvidenceConsumed)
        assertTrue(composed.dependencyProbeEvidenceConsumed)
        assertTrue(composed.runtimeRandomnessPolicyEvidenceConsumed)
        assertTrue(composed.redactionLeakageEvidenceConsumed)
        assertTrue(composed.clearWipeStrategyEvidenceConsumed)
        assertTrue(composed.passphrasePolicyEvidenceConsumed)
        assertTrue(composed.lockSessionLifecycleEvidenceConsumed)
        assertTrue(composed.persistenceReadinessEvidenceConsumed)
        assertFalse(composed.storageSafetyPreflightEvidenceConsumed)
        assertTrue(composed.disabledStorageServiceEvidenceConsumed)
        assertTrue(composed.migrationCorruptionEvidenceConsumed)
        assertTrue(composed.secureStorageEvidenceConsumed)
        assertTrue(composed.secureMetadataEvidenceConsumed)
        assertEquals(SkaldVaultV1VaultRuntimeRandomnessAuthorizationDecision.BlockedFailClosed, composed.decision)
        assertContains(composed.blockers, SkaldVaultV1VaultRandomnessBlocker.ProviderOperationAuthorizationBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultRandomnessBlocker.ProviderSelectionDisabled)
        assertContains(composed.blockers, SkaldVaultV1VaultRandomnessBlocker.ProductionProviderSelectableFalse)
        assertContains(composed.blockers, SkaldVaultV1VaultRandomnessBlocker.ProviderKatApprovalMissing)
        assertContains(composed.blockers, SkaldVaultV1VaultRandomnessBlocker.PassphrasePolicyBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultRandomnessBlocker.LockSessionLifecycleBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultRandomnessBlocker.PersistenceReadinessBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultRandomnessBlocker.StorageSafetyPreflightBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultRandomnessBlocker.DisabledStorageServiceUnavailable)
        assertContains(composed.blockers, SkaldVaultV1VaultRandomnessBlocker.MigrationCorruptionBoundaryDisabled)
        assertContains(composed.blockers, SkaldVaultV1VaultRandomnessBlocker.ClearWipeStrategyBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultRandomnessBlocker.RedactionLeakageUnsafe)
        assertContains(composed.blockers, SkaldVaultV1VaultRandomnessBlocker.SecureSecretStorageUnavailable)
        assertContains(composed.blockers, SkaldVaultV1VaultRandomnessBlocker.SecureMetadataStorageUnavailable)
        assertContains(composed.blockers, SkaldVaultV1VaultRandomnessBlocker.WarningOnlyEvidenceRejected)
        assertContains(composed.blockers, SkaldVaultV1VaultRandomnessBlocker.UserConsentOverrideRejected)
        assertDisabled(composed.capability)
    }

    @Test
    fun sourceSpecificDecisionsRemainRejectedOrUnauthorized() {
        val kotlinSource = blocked(
            SkaldVaultV1RuntimeRandomnessAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest.forSourceKind(
                    SkaldVaultV1VaultRandomnessSourceKind.ForbiddenKotlinRandom,
                ),
            ),
        )
        val javaSource = blocked(
            SkaldVaultV1RuntimeRandomnessAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest.forSourceKind(
                    SkaldVaultV1VaultRandomnessSourceKind.ForbiddenJavaRandom,
                ),
            ),
        )
        val mathSource = blocked(
            SkaldVaultV1RuntimeRandomnessAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest.forSourceKind(
                    SkaldVaultV1VaultRandomnessSourceKind.ForbiddenMathRandom,
                ),
            ),
        )
        val deterministicProduction = blocked(
            SkaldVaultV1RuntimeRandomnessAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest.forSourceKind(
                    sourceKind = SkaldVaultV1VaultRandomnessSourceKind.DeterministicTestVectorSource,
                    purpose = SkaldVaultV1VaultRandomnessPurpose.ProductionRuntime,
                ),
            ),
        )
        val mainnet = blocked(
            SkaldVaultV1RuntimeRandomnessAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest.forOperationKind(
                    SkaldVaultV1VaultRandomnessOperationKind.MainnetRandomnessRequest,
                ),
            ),
        )
        val unknownSource = blocked(
            SkaldVaultV1RuntimeRandomnessAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest.forSourceKind(
                    SkaldVaultV1VaultRandomnessSourceKind.UnknownSource,
                ),
            ),
        )
        val unsupportedSource = blocked(
            SkaldVaultV1RuntimeRandomnessAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest.forSourceKind(
                    SkaldVaultV1VaultRandomnessSourceKind.UnsupportedSource,
                ),
            ),
        )

        assertEquals(SkaldVaultV1VaultRuntimeRandomnessAuthorizationDecision.ForbiddenSourceRejected, kotlinSource.decision)
        assertEquals(SkaldVaultV1VaultRuntimeRandomnessAuthorizationDecision.ForbiddenSourceRejected, javaSource.decision)
        assertEquals(SkaldVaultV1VaultRuntimeRandomnessAuthorizationDecision.ForbiddenSourceRejected, mathSource.decision)
        assertContains(kotlinSource.blockers, SkaldVaultV1VaultRandomnessBlocker.SourceForbidden)
        assertContains(javaSource.blockers, SkaldVaultV1VaultRandomnessBlocker.GeneralPurposeRandomRejected)
        assertContains(mathSource.blockers, SkaldVaultV1VaultRandomnessBlocker.GeneralPurposeRandomRejected)
        assertEquals(
            SkaldVaultV1VaultRuntimeRandomnessAuthorizationDecision.TestOnlyScopeRejectedForProduction,
            deterministicProduction.decision,
        )
        assertContains(
            deterministicProduction.blockers,
            SkaldVaultV1VaultRandomnessBlocker.DeterministicTestVectorRejectedForProduction,
        )
        assertEquals(SkaldVaultV1VaultRuntimeRandomnessAuthorizationDecision.ForbiddenSourceRejected, mainnet.decision)
        assertContains(mainnet.blockers, SkaldVaultV1VaultRandomnessBlocker.MainnetUnavailable)
        assertEquals(
            SkaldVaultV1VaultRuntimeRandomnessAuthorizationDecision.UnsupportedFailClosed,
            unknownSource.decision,
        )
        assertEquals(
            SkaldVaultV1VaultRuntimeRandomnessAuthorizationDecision.UnsupportedFailClosed,
            unsupportedSource.decision,
        )
        assertContains(unknownSource.blockers, SkaldVaultV1VaultRandomnessBlocker.SourceUnknownOrUnsupported)
        assertContains(unsupportedSource.blockers, SkaldVaultV1VaultRandomnessBlocker.SourceUnknownOrUnsupported)
    }

    @Test
    fun renderingAndRawCandidateRejectionRemainRedacted() {
        val raw = "salt-bytes-fixture"
        val request = SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest.rawRandomnessCandidate(raw)
        val result = SkaldVaultV1RuntimeRandomnessAuthorizationPolicy.evaluate(request)
        val rejected = assertIs<SkaldVaultV1VaultRuntimeRandomnessAuthorizationResult.Rejected>(result)
        val evidence = blocked(
            SkaldVaultV1RuntimeRandomnessAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest.summary(),
            ),
        )

        assertEquals(SkaldVaultV1VaultRandomnessFailureReason.SaltBytesRejected, rejected.reason)
        assertFalse(request.toString().contains(raw))
        assertFalse(result.toString().contains(raw))
        assertFalse(evidence.toString().contains(raw))
        assertFalse(evidence.policyTokenEvidence.toString().contains(raw))
        assertFalse(evidence.policyTokenEvidence.containsRandomBytes)
        assertFalse(evidence.policyTokenEvidence.containsEntropyBytes)
        assertFalse(evidence.policyTokenEvidence.containsSaltBytes)
        assertFalse(evidence.policyTokenEvidence.containsNonceBytes)
        assertFalse(evidence.policyTokenEvidence.containsKeyMaterial)
        assertFalse(evidence.policyTokenEvidence.containsProviderHandle)
        assertFalse(evidence.policyTokenEvidence.containsKdfMaterial)
        assertFalse(evidence.policyTokenEvidence.containsCiphertext)
        assertFalse(evidence.policyTokenEvidence.containsPlaintext)
        assertFalse(evidence.policyTokenEvidence.containsTagOrHeaderCommitmentBytes)
        assertFalse(evidence.policyTokenEvidence.containsRecordIdentifier)
        assertFalse(evidence.policyTokenEvidence.containsRootOrPathText)
        assertFalse(evidence.policyTokenEvidence.containsPayload)

        assertRejected("random-bytes", SkaldVaultV1VaultRandomnessFailureReason.RandomBytesRejected)
        assertRejected("entropy-bytes", SkaldVaultV1VaultRandomnessFailureReason.EntropyBytesRejected)
        assertRejected("nonce-bytes", SkaldVaultV1VaultRandomnessFailureReason.NonceBytesRejected)
        assertRejected("key-bytes", SkaldVaultV1VaultRandomnessFailureReason.KeyBytesRejected)
        assertRejected("seed-bytes", SkaldVaultV1VaultRandomnessFailureReason.SeedBytesRejected)
        assertRejected("provider-handle", SkaldVaultV1VaultRandomnessFailureReason.ProviderHandleRejected)
        assertRejected("crypto-provider-instance", SkaldVaultV1VaultRandomnessFailureReason.ProviderImplementationInstanceRejected)
        assertRejected("pass" + "phrase-fixture", SkaldVaultV1VaultRandomnessFailureReason.RawPassphraseRejected)
        assertRejected("kdf-input", SkaldVaultV1VaultRandomnessFailureReason.KdfInputRejected)
        assertRejected("kdf-output", SkaldVaultV1VaultRandomnessFailureReason.KdfOutputRejected)
        assertRejected("aead-tag", SkaldVaultV1VaultRandomnessFailureReason.AeadKeyTagCiphertextPlaintextRejected)
        assertRejected("cipher" + "text-fixture", SkaldVaultV1VaultRandomnessFailureReason.AeadKeyTagCiphertextPlaintextRejected)
        assertRejected("plain" + "text-fixture", SkaldVaultV1VaultRandomnessFailureReason.AeadKeyTagCiphertextPlaintextRejected)
        assertRejected("record-bytes", SkaldVaultV1VaultRandomnessFailureReason.RecordBytesRejected)
        assertRejected("container-bytes", SkaldVaultV1VaultRandomnessFailureReason.RawPersistedContainerBytesRejected)
        assertRejected("bytearray-fixture", SkaldVaultV1VaultRandomnessFailureReason.ByteArrayInputRejected)
        assertRejected("chararray-fixture", SkaldVaultV1VaultRandomnessFailureReason.CharArrayInputRejected)
        assertRejected("secure-random-object", SkaldVaultV1VaultRandomnessFailureReason.RandomObjectInputRejected)
        assertRejected("/vault/root", SkaldVaultV1VaultRandomnessFailureReason.RawAbsoluteLocationInputRejected)
        assertRejected("vault/root", SkaldVaultV1VaultRandomnessFailureReason.RawRelativeLocationInputRejected)
        assertRejected("https://example.invalid", SkaldVaultV1VaultRandomnessFailureReason.LinkLikeInputRejected)
        assertRejected("file-object", SkaldVaultV1VaultRandomnessFailureReason.PlatformObjectLikeInputRejected)
        assertRejected("a".repeat(64), SkaldVaultV1VaultRandomnessFailureReason.TransactionLikeEvidenceRejected)
        assertRejected("bc" + "1q" + "a".repeat(24), SkaldVaultV1VaultRandomnessFailureReason.BitcoinAddressLikeEvidenceRejected)
        assertRejected("ns" + "ec1fixture", SkaldVaultV1VaultRandomnessFailureReason.WalletMaterialRejected)
        assertRejected("xp" + "rvfixture", SkaldVaultV1VaultRandomnessFailureReason.WalletMaterialRejected)
        assertRejected("tp" + "rvfixture", SkaldVaultV1VaultRandomnessFailureReason.WalletMaterialRejected)
        assertRejected("K" + "a".repeat(50), SkaldVaultV1VaultRandomnessFailureReason.WalletMaterialRejected)
        assertRejected("../vault", SkaldVaultV1VaultRandomnessFailureReason.TraversalRejected)
        assertRejected("unsupported#chars", SkaldVaultV1VaultRandomnessFailureReason.UnsupportedCharactersRejected)
    }

    @Test
    fun allCurrentCapabilitiesRemainDisabled() {
        assertDisabled(
            blocked(
                SkaldVaultV1RuntimeRandomnessAuthorizationPolicy.evaluate(
                    SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest.summary(),
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
                ProductionProviderAcceptanceGate.RuntimeRandomnessAuthorizationBoundaryImplementedAndTested,
            ),
        )
        assertEquals(
            SkaldVaultV1RuntimeRandomnessAuthorizationPolicy.POLICY_ID,
            storageContract.runtimeRandomnessAuthorizationBoundaryPolicyId,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            storageContract.runtimeRandomnessAuthorizationBoundaryStatus,
        )
        assertEquals(
            ProductionProviderRuntimeRandomnessAuthorizationBoundaryRule.entries.toSet(),
            storageContract.runtimeRandomnessAuthorizationBoundaryRules,
        )
        assertTrue(storageContract.runtimeRandomnessAuthorizationBoundaryModeled)
        assertTrue(storageContract.runtimeRandomnessAuthorizationStillDisabled)
        assertTrue(storageContract.runtimeRandomnessAuthorizationBlocksAllOperations)
        assertTrue(storageContract.runtimeRandomnessAuthorizationDoesNotCallSecureRandom)
        assertTrue(storageContract.runtimeRandomnessAuthorizationDoesNotGenerateEntropy)
        assertTrue(storageContract.runtimeRandomnessAuthorizationDoesNotGenerateSaltOrNonce)
        assertTrue(storageContract.runtimeRandomnessAuthorizationDoesNotGenerateKeys)
        assertTrue(storageContract.runtimeRandomnessAuthorizationDoesNotEnableProviderOperations)
        assertTrue(storageContract.runtimeRandomnessAuthorizationDoesNotEnableUnlock)
        assertTrue(storageContract.runtimeRandomnessAuthorizationDoesNotEnablePersistence)
        assertTrue(storageContract.runtimeRandomnessAuthorizationDoesNotEnableProviderSelection)
        assertTrue(storageContract.runtimeRandomnessFailureVocabularyModeled)
        assertFalse(storageContract.vaultPersistenceImplemented)
        assertContains(readiness.capabilities, EncryptedVaultCapability.RuntimeRandomnessAuthorizationBoundaryBuildingBlock)
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.RuntimeRandomnessAuthorizationBoundaryImplementedAndTested
            ],
        )
        assertContains(readiness.blockers, EncryptedVaultBlockingIssue.RuntimeRandomnessAuthorizationBoundaryStillDisabled)
        assertContains(
            dependency.capabilities,
            VaultCryptoDependencyCapability.RuntimeRandomnessAuthorizationBoundaryImplementedTested,
        )
        assertContains(
            dependency.capabilities,
            VaultCryptoDependencyCapability.RuntimeRandomnessFailureVocabularyModeled,
        )
        assertContains(
            dependency.blockers,
            VaultCryptoDependencyBlocker.RuntimeRandomnessAuthorizationBoundaryStillDisabled,
        )
        assertContains(
            dependency.blockers,
            VaultCryptoDependencyBlocker.RuntimeRandomnessAuthorizationRuntimeReviewMissing,
        )
        assertContains(
            dependency.blockers,
            VaultCryptoDependencyBlocker.RuntimeRandomnessAuthorizationTestsMissing,
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
                    SkaldVaultV1VaultProviderOperationKind.RuntimeRandomnessCheck,
                ),
            ),
        ).value

    private fun disabledStorageEvidence(): SkaldVaultV1VaultStorageDisabledEvidence =
        assertIs<SkaldVaultV1VaultStorageOperationResult.Disabled<SkaldVaultV1VaultStorageDisabledEvidence>>(
            SkaldVaultV1DisabledStorageServiceFacade.perform(
                SkaldVaultV1VaultStorageOperationRequest.noEvidence(
                    SkaldVaultV1VaultStorageOperation.CloseLockStorageSession,
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
                    SkaldVaultV1VaultRedactionValueKind.RandomEntropySample,
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
        reason: SkaldVaultV1VaultRandomnessFailureReason,
    ) {
        val request = SkaldVaultV1VaultRuntimeRandomnessAuthorizationRequest.rawRandomnessCandidate(candidate)
        val result = SkaldVaultV1RuntimeRandomnessAuthorizationPolicy.evaluate(request)
        val rejected = assertIs<SkaldVaultV1VaultRuntimeRandomnessAuthorizationResult.Rejected>(result)

        assertTrue(request.rawCandidateRejected)
        assertEquals(reason, rejected.reason)
        assertFalse(result.toString().contains(candidate ?: ""))
    }

    private fun blocked(
        result: SkaldVaultV1VaultRuntimeRandomnessAuthorizationResult<
            SkaldVaultV1VaultRuntimeRandomnessAuthorizationEvidence,
        >,
    ): SkaldVaultV1VaultRuntimeRandomnessAuthorizationEvidence =
        assertIs<
            SkaldVaultV1VaultRuntimeRandomnessAuthorizationResult.Blocked<
                SkaldVaultV1VaultRuntimeRandomnessAuthorizationEvidence,
            >,
        >(result).value

    private fun assertDisabled(capability: SkaldVaultV1VaultRandomnessCapability) {
        assertFalse(capability.randomnessOperationAuthorized)
        assertFalse(capability.runtimeRandomnessAvailable)
        assertFalse(capability.osCryptographicRandomnessAvailable)
        assertFalse(capability.providerRandomnessAvailable)
        assertFalse(capability.hardwareBackedEntropyAvailable)
        assertFalse(capability.androidOsCsprngAvailable)
        assertFalse(capability.androidHardwareBackedEntropyAvailable)
        assertFalse(capability.linuxOsCsprngAvailable)
        assertFalse(capability.linuxHardwareEntropyAvailable)
        assertFalse(capability.saltGenerationAvailable)
        assertFalse(capability.nonceGenerationAvailable)
        assertFalse(capability.keyGenerationEntropyAvailable)
        assertFalse(capability.kdfSaltAvailable)
        assertFalse(capability.aeadNonceAvailable)
        assertFalse(capability.recordNonceAvailable)
        assertFalse(capability.metadataNonceAvailable)
        assertFalse(capability.manifestNonceAvailable)
        assertFalse(capability.storageIndexNonceAvailable)
        assertFalse(capability.deterministicTestVectorRandomnessAvailable)
        assertFalse(capability.productionRuntimeRandomnessAvailable)
        assertFalse(capability.providerOperationAuthorized)
        assertFalse(capability.providerCryptoAvailable)
        assertFalse(capability.providerSelectable)
        assertFalse(capability.vaultCreationAvailable)
        assertFalse(capability.vaultUnlockAvailable)
        assertFalse(capability.vaultPersistenceAvailable)
        assertFalse(capability.secureSecretStorageAvailable)
        assertFalse(capability.secureMetadataStorageAvailable)
        assertFalse(capability.mainnetAvailable)
    }
}
