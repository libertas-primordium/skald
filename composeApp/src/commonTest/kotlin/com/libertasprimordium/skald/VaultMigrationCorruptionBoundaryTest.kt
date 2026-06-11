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
import com.libertasprimordium.skald.security.ProductionProviderMigrationCorruptionBoundaryRule
import com.libertasprimordium.skald.security.SkaldVaultV1ClearWipeStrategyPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1DisabledStorageServiceFacade
import com.libertasprimordium.skald.security.SkaldVaultV1LockSessionLifecyclePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1MigrationCorruptionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformPathConstructionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1RedactionLeakagePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1StillDisabledProviderFacade
import com.libertasprimordium.skald.security.SkaldVaultV1StorageSafetyPreflightPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1VaultClearWipeEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultClearWipeRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultClearWipeResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultLockSessionEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultLockSessionRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultLockSessionResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultMigrationCorruptionBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1VaultMigrationCorruptionDecision
import com.libertasprimordium.skald.security.SkaldVaultV1VaultMigrationCorruptionEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultMigrationCorruptionEvidenceKind
import com.libertasprimordium.skald.security.SkaldVaultV1VaultMigrationCorruptionFailureClass
import com.libertasprimordium.skald.security.SkaldVaultV1VaultMigrationCorruptionFailureReason
import com.libertasprimordium.skald.security.SkaldVaultV1VaultMigrationCorruptionLimitation
import com.libertasprimordium.skald.security.SkaldVaultV1VaultMigrationCorruptionRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultMigrationCorruptionRequiredAction
import com.libertasprimordium.skald.security.SkaldVaultV1VaultMigrationCorruptionResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultMigrationCorruptionSeverity
import com.libertasprimordium.skald.security.SkaldVaultV1VaultMigrationCorruptionStatus
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPersistenceReadinessEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPersistenceReadinessRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPersistenceReadinessResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRedactionEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRedactionRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRedactionResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultRedactionValueKind
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
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultMigrationCorruptionBoundaryTest {
    @Test
    fun defaultPolicyIsBlockedFailClosedAndCapabilitiesRemainDisabled() {
        val evidence = blocked(
            SkaldVaultV1MigrationCorruptionPolicy.evaluate(
                SkaldVaultV1VaultMigrationCorruptionRequest.noEvidence(),
            ),
        )

        assertEquals(
            "skald-vault-v1-migration-corruption-boundary-v1",
            SkaldVaultV1MigrationCorruptionPolicy.POLICY_ID,
        )
        assertEquals(1, SkaldVaultV1MigrationCorruptionPolicy.POLICY_VERSION)
        assertEquals(
            SkaldVaultV1VaultMigrationCorruptionStatus.NoEvidenceAvailable,
            evidence.status,
        )
        assertEquals(SkaldVaultV1VaultMigrationCorruptionDecision.BlockedFailClosed, evidence.decision)
        assertContains(evidence.blockers, SkaldVaultV1VaultMigrationCorruptionBlocker.NoEvidenceAvailable)
        assertContains(
            evidence.blockers,
            SkaldVaultV1VaultMigrationCorruptionBlocker.MigrationCorruptionBoundaryStillDisabled,
        )
        assertContains(
            evidence.limitations,
            SkaldVaultV1VaultMigrationCorruptionLimitation.DoesNotParseRealStorage,
        )
        assertDisabled(evidence.capability)
    }

    @Test
    fun evidenceCategoryVocabularyIsModeled() {
        val kinds = SkaldVaultV1VaultMigrationCorruptionEvidenceKind.entries.toSet()

        listOf(
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.VaultContainerHeaderEvidence,
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.ContainerVersionEvidence,
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.ProviderSuiteIdEvidence,
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.KdfParameterEvidence,
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.HeaderCommitmentEvidence,
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.AadContractEvidence,
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.ManifestVersionEvidence,
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.ManifestCurrentRecordReferenceEvidence,
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.StorageIndexVersionEvidence,
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.StorageIndexRecordReferenceEvidence,
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.RecordDescriptorEvidence,
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.RecordPurposeEvidence,
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.RecordNonceCounterEvidence,
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.RecordCiphertextPlaceholderEvidence,
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.RecordAuthenticationFailureEvidence,
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.StaleRecordEvidence,
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.RollbackSuspicionEvidence,
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.InterruptedWriteEvidence,
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.PartialManifestWriteEvidence,
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.PartialStorageIndexWriteEvidence,
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.OrphanRecordEvidence,
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.MissingRecordEvidence,
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.DuplicateRecordEvidence,
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.ConflictingRecordEvidence,
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.UnknownFutureVersionEvidence,
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.UnsupportedOldVersionEvidence,
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.MigrationRequiredEvidence,
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.MigrationPlanEvidence,
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.CorruptionDetectedEvidence,
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.RecoveryAttemptEvidence,
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.QuarantineRequiredEvidence,
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.ManualReviewRequiredEvidence,
            SkaldVaultV1VaultMigrationCorruptionEvidenceKind.FailClosedRequiredEvidence,
        ).forEach { kind -> assertContains(kinds, kind) }
    }

    @Test
    fun failureClassAndActionVocabularyIsModeled() {
        val failures = SkaldVaultV1VaultMigrationCorruptionFailureClass.entries.toSet()
        val actions = SkaldVaultV1VaultMigrationCorruptionRequiredAction.entries.toSet()

        listOf(
            SkaldVaultV1VaultMigrationCorruptionFailureClass.NoEvidence,
            SkaldVaultV1VaultMigrationCorruptionFailureClass.UnsupportedVersion,
            SkaldVaultV1VaultMigrationCorruptionFailureClass.UnknownFutureVersion,
            SkaldVaultV1VaultMigrationCorruptionFailureClass.MalformedHeader,
            SkaldVaultV1VaultMigrationCorruptionFailureClass.HeaderCommitmentMismatch,
            SkaldVaultV1VaultMigrationCorruptionFailureClass.ProviderSuiteMismatch,
            SkaldVaultV1VaultMigrationCorruptionFailureClass.KdfParameterMismatch,
            SkaldVaultV1VaultMigrationCorruptionFailureClass.AadMismatch,
            SkaldVaultV1VaultMigrationCorruptionFailureClass.ManifestMissing,
            SkaldVaultV1VaultMigrationCorruptionFailureClass.ManifestMalformed,
            SkaldVaultV1VaultMigrationCorruptionFailureClass.StorageIndexMissing,
            SkaldVaultV1VaultMigrationCorruptionFailureClass.StorageIndexMalformed,
            SkaldVaultV1VaultMigrationCorruptionFailureClass.RecordDescriptorMalformed,
            SkaldVaultV1VaultMigrationCorruptionFailureClass.RecordMissing,
            SkaldVaultV1VaultMigrationCorruptionFailureClass.RecordOrphaned,
            SkaldVaultV1VaultMigrationCorruptionFailureClass.DuplicateRecord,
            SkaldVaultV1VaultMigrationCorruptionFailureClass.ConflictingRecord,
            SkaldVaultV1VaultMigrationCorruptionFailureClass.StaleRecord,
            SkaldVaultV1VaultMigrationCorruptionFailureClass.RollbackSuspected,
            SkaldVaultV1VaultMigrationCorruptionFailureClass.PartialWriteSuspected,
            SkaldVaultV1VaultMigrationCorruptionFailureClass.CrashRecoveryRequired,
            SkaldVaultV1VaultMigrationCorruptionFailureClass.AuthenticationFailed,
            SkaldVaultV1VaultMigrationCorruptionFailureClass.CorruptionDetected,
            SkaldVaultV1VaultMigrationCorruptionFailureClass.MigrationRequired,
            SkaldVaultV1VaultMigrationCorruptionFailureClass.MigrationUnsafe,
            SkaldVaultV1VaultMigrationCorruptionFailureClass.QuarantineRequired,
            SkaldVaultV1VaultMigrationCorruptionFailureClass.SecureStorageUnavailable,
            SkaldVaultV1VaultMigrationCorruptionFailureClass.ProviderUnavailable,
            SkaldVaultV1VaultMigrationCorruptionFailureClass.StorageServiceDisabled,
            SkaldVaultV1VaultMigrationCorruptionFailureClass.RedactionUnsafe,
            SkaldVaultV1VaultMigrationCorruptionFailureClass.ClearWipeStrategyUnavailable,
            SkaldVaultV1VaultMigrationCorruptionFailureClass.ManualReviewRequired,
            SkaldVaultV1VaultMigrationCorruptionFailureClass.MainnetBlocked,
        ).forEach { failure -> assertContains(failures, failure) }

        listOf(
            SkaldVaultV1VaultMigrationCorruptionRequiredAction.FailClosed,
            SkaldVaultV1VaultMigrationCorruptionRequiredAction.RejectOperation,
            SkaldVaultV1VaultMigrationCorruptionRequiredAction.RequireManualReview,
            SkaldVaultV1VaultMigrationCorruptionRequiredAction.RequireBackupBeforeMigration,
            SkaldVaultV1VaultMigrationCorruptionRequiredAction.RequireDryRunMigrationFirst,
            SkaldVaultV1VaultMigrationCorruptionRequiredAction.RequireProviderValidation,
            SkaldVaultV1VaultMigrationCorruptionRequiredAction.RequireStorageSafetyValidation,
            SkaldVaultV1VaultMigrationCorruptionRequiredAction.RequireManifestValidation,
            SkaldVaultV1VaultMigrationCorruptionRequiredAction.RequireStorageIndexValidation,
            SkaldVaultV1VaultMigrationCorruptionRequiredAction.RequireRecordQuarantine,
            SkaldVaultV1VaultMigrationCorruptionRequiredAction.RequireStaleRecordReview,
            SkaldVaultV1VaultMigrationCorruptionRequiredAction.RequireRollbackReview,
            SkaldVaultV1VaultMigrationCorruptionRequiredAction.RequireCorruptionReview,
            SkaldVaultV1VaultMigrationCorruptionRequiredAction.RequireClearWipeHandling,
            SkaldVaultV1VaultMigrationCorruptionRequiredAction.RequireRedactedDiagnosticOnly,
            SkaldVaultV1VaultMigrationCorruptionRequiredAction.RequireUserVisibleWarning,
            SkaldVaultV1VaultMigrationCorruptionRequiredAction.RequireReleaseHardeningReview,
            SkaldVaultV1VaultMigrationCorruptionRequiredAction.UnsupportedNoActionAvailable,
        ).forEach { action -> assertContains(actions, action) }
    }

    @Test
    fun classificationsMapToFailClosedRequiredActions() {
        val migration = blocked(
            SkaldVaultV1MigrationCorruptionPolicy.evaluate(
                SkaldVaultV1VaultMigrationCorruptionRequest.forFailureClass(
                    SkaldVaultV1VaultMigrationCorruptionFailureClass.MigrationRequired,
                ),
            ),
        )
        val rollback = blocked(
            SkaldVaultV1MigrationCorruptionPolicy.evaluate(
                SkaldVaultV1VaultMigrationCorruptionRequest.forEvidenceKind(
                    SkaldVaultV1VaultMigrationCorruptionEvidenceKind.RollbackSuspicionEvidence,
                ),
            ),
        )
        val quarantine = blocked(
            SkaldVaultV1MigrationCorruptionPolicy.evaluate(
                SkaldVaultV1VaultMigrationCorruptionRequest.forFailureClass(
                    SkaldVaultV1VaultMigrationCorruptionFailureClass.QuarantineRequired,
                ),
            ),
        )
        val unsupported = blocked(
            SkaldVaultV1MigrationCorruptionPolicy.evaluate(
                SkaldVaultV1VaultMigrationCorruptionRequest.forFailureClass(
                    SkaldVaultV1VaultMigrationCorruptionFailureClass.UnknownFutureVersion,
                ),
            ),
        )

        assertEquals(SkaldVaultV1VaultMigrationCorruptionDecision.ModelOnlyClassification, migration.decision)
        assertContains(
            migration.requiredActions,
            SkaldVaultV1VaultMigrationCorruptionRequiredAction.RequireBackupBeforeMigration,
        )
        assertContains(
            migration.requiredActions,
            SkaldVaultV1VaultMigrationCorruptionRequiredAction.RequireDryRunMigrationFirst,
        )
        assertEquals(SkaldVaultV1VaultMigrationCorruptionDecision.RequireManualReview, rollback.decision)
        assertEquals(SkaldVaultV1VaultMigrationCorruptionSeverity.CriticalManualReview, rollback.severity)
        assertContains(rollback.requiredActions, SkaldVaultV1VaultMigrationCorruptionRequiredAction.RequireRollbackReview)
        assertContains(
            quarantine.requiredActions,
            SkaldVaultV1VaultMigrationCorruptionRequiredAction.RequireRecordQuarantine,
        )
        assertEquals(SkaldVaultV1VaultMigrationCorruptionDecision.RejectOperation, unsupported.decision)
        assertContains(unsupported.requiredActions, SkaldVaultV1VaultMigrationCorruptionRequiredAction.RejectOperation)
    }

    @Test
    fun typedEvidenceCompositionConsumesExistingDisabledBoundariesButCannotEnableMigration() {
        val storageEvidence = disabledStorageEvidence()
        val persistenceEvidence = persistenceReadinessEvidence()
        val redactionEvidence = redactionEvidence()
        val clearWipeEvidence = clearWipeEvidence()
        val lockEvidence = lockSessionEvidence()
        val providerSelection = VaultCryptoProviderSelectionRegistry.select()
        val providerFacadeMetadata = SkaldVaultV1StillDisabledProviderFacade.metadata()

        val composed = blocked(
            SkaldVaultV1MigrationCorruptionPolicy.evaluate(
                SkaldVaultV1VaultMigrationCorruptionRequest.fromEvidence(
                    disabledStorageServiceEvidence = storageEvidence,
                    storageSafetyPreflightEvidence = null,
                    platformPathConstructionEvidence = null,
                    persistenceReadinessEvidence = persistenceEvidence,
                    redactionLeakageEvidence = redactionEvidence,
                    clearWipeStrategyEvidence = clearWipeEvidence,
                    lockSessionLifecycleEvidence = lockEvidence,
                    providerSelectionResult = providerSelection,
                    disabledProviderFacadeMetadata = providerFacadeMetadata,
                    secureStorageCapability = commonDisabledSecureStorageCapability(),
                    secureMetadataCapability = commonDisabledSecureMetadataCapability(),
                    evidenceKind = SkaldVaultV1VaultMigrationCorruptionEvidenceKind.InterruptedWriteEvidence,
                    failureClass = SkaldVaultV1VaultMigrationCorruptionFailureClass.PartialWriteSuspected,
                ),
            ),
        )

        assertTrue(composed.disabledStorageServiceEvidenceConsumed)
        assertFalse(composed.storageSafetyPreflightEvidenceConsumed)
        assertFalse(composed.platformPathConstructionEvidenceConsumed)
        assertTrue(composed.persistenceReadinessEvidenceConsumed)
        assertTrue(composed.redactionLeakageEvidenceConsumed)
        assertTrue(composed.clearWipeStrategyEvidenceConsumed)
        assertTrue(composed.lockSessionLifecycleEvidenceConsumed)
        assertTrue(composed.providerSelectionEvidenceConsumed)
        assertTrue(composed.disabledProviderFacadeEvidenceConsumed)
        assertTrue(composed.secureStorageEvidenceConsumed)
        assertTrue(composed.secureMetadataEvidenceConsumed)
        assertEquals(SkaldVaultV1VaultMigrationCorruptionDecision.BlockedFailClosed, composed.decision)
        assertContains(composed.blockers, SkaldVaultV1VaultMigrationCorruptionBlocker.StorageServiceUnavailable)
        assertContains(composed.blockers, SkaldVaultV1VaultMigrationCorruptionBlocker.ProviderSelectionDisabled)
        assertContains(composed.blockers, SkaldVaultV1VaultMigrationCorruptionBlocker.RedactionUnsafe)
        assertContains(composed.blockers, SkaldVaultV1VaultMigrationCorruptionBlocker.ClearWipeStrategyUnavailable)
        assertContains(composed.blockers, SkaldVaultV1VaultMigrationCorruptionBlocker.WarningOnlyEvidenceRejected)
        assertContains(composed.blockers, SkaldVaultV1VaultMigrationCorruptionBlocker.UserConsentOverrideRejected)
        assertFalse(composed.capability.migrationAvailable)
        assertFalse(composed.capability.repairAvailable)
        assertFalse(composed.capability.quarantineAvailable)
        assertFalse(composed.capability.mainnetAvailable)
    }

    @Test
    fun renderingAndRawCandidateRejectionRemainRedacted() {
        val raw = "cipher" + "text-fixture"
        val request = SkaldVaultV1VaultMigrationCorruptionRequest.rawStorageCandidate(raw)
        val result = SkaldVaultV1MigrationCorruptionPolicy.evaluate(request)
        val evidence = blocked(
            SkaldVaultV1MigrationCorruptionPolicy.evaluate(
                SkaldVaultV1VaultMigrationCorruptionRequest.summary(),
            ),
        )

        assertFalse(request.toString().contains(raw))
        assertFalse(result.toString().contains(raw))
        assertFalse(evidence.toString().contains(raw))
        assertFalse(evidence.policyTokenEvidence.toString().contains(raw))
        assertFalse(evidence.policyTokenEvidence.containsRawPersistedBytes)
        assertFalse(evidence.policyTokenEvidence.containsCiphertext)
        assertFalse(evidence.policyTokenEvidence.containsPlaintext)
        assertFalse(evidence.policyTokenEvidence.containsNonceOrTagBytes)
        assertFalse(evidence.policyTokenEvidence.containsHeaderCommitmentBytes)
        assertFalse(evidence.policyTokenEvidence.containsKeyMaterial)
        assertRejected(raw, SkaldVaultV1VaultMigrationCorruptionFailureReason.RawCiphertextRejected)
        assertRejected("plain" + "text-fixture", SkaldVaultV1VaultMigrationCorruptionFailureReason.RawPlaintextRejected)
        assertRejected("container-bytes", SkaldVaultV1VaultMigrationCorruptionFailureReason.RawPersistedContainerBytesRejected)
        assertRejected("manifest-bytes", SkaldVaultV1VaultMigrationCorruptionFailureReason.RawManifestBytesRejected)
        assertRejected("storage-index-bytes", SkaldVaultV1VaultMigrationCorruptionFailureReason.RawStorageIndexBytesRejected)
        assertRejected("record-bytes", SkaldVaultV1VaultMigrationCorruptionFailureReason.RawRecordBytesRejected)
        assertRejected("aead-tag", SkaldVaultV1VaultMigrationCorruptionFailureReason.AeadTagBytesRejected)
        assertRejected("nonce-fixture", SkaldVaultV1VaultMigrationCorruptionFailureReason.NonceBytesRejected)
        assertRejected("header-commitment", SkaldVaultV1VaultMigrationCorruptionFailureReason.HeaderCommitmentBytesRejected)
        assertRejected("kdf-output", SkaldVaultV1VaultMigrationCorruptionFailureReason.KdfOutputRejected)
        assertRejected("provider-key-material", SkaldVaultV1VaultMigrationCorruptionFailureReason.ProviderKeyMaterialRejected)
        assertRejected("pass" + "phrase-fixture", SkaldVaultV1VaultMigrationCorruptionFailureReason.PassphraseInputRejected)
        assertRejected("bytearray-fixture", SkaldVaultV1VaultMigrationCorruptionFailureReason.ByteArrayInputRejected)
        assertRejected("/vault/root", SkaldVaultV1VaultMigrationCorruptionFailureReason.RawAbsoluteLocationInputRejected)
        assertRejected("vault/root", SkaldVaultV1VaultMigrationCorruptionFailureReason.RawRelativeLocationInputRejected)
        assertRejected("file-object", SkaldVaultV1VaultMigrationCorruptionFailureReason.PlatformObjectLikeInputRejected)
        assertRejected("https://example.invalid", SkaldVaultV1VaultMigrationCorruptionFailureReason.LinkLikeInputRejected)
        assertRejected("a".repeat(64), SkaldVaultV1VaultMigrationCorruptionFailureReason.TransactionLikeEvidenceRejected)
        assertRejected("bc" + "1q" + "a".repeat(24), SkaldVaultV1VaultMigrationCorruptionFailureReason.BitcoinAddressLikeEvidenceRejected)
        assertRejected("ns" + "ec1fixture", SkaldVaultV1VaultMigrationCorruptionFailureReason.WalletMaterialRejected)
        assertRejected("xp" + "rvfixture", SkaldVaultV1VaultMigrationCorruptionFailureReason.WalletMaterialRejected)
        assertRejected("tp" + "rvfixture", SkaldVaultV1VaultMigrationCorruptionFailureReason.WalletMaterialRejected)
        assertRejected("K" + "a".repeat(50), SkaldVaultV1VaultMigrationCorruptionFailureReason.WalletMaterialRejected)
        assertRejected("../vault", SkaldVaultV1VaultMigrationCorruptionFailureReason.TraversalRejected)
        assertRejected("unsupported#chars", SkaldVaultV1VaultMigrationCorruptionFailureReason.UnsupportedCharactersRejected)
    }

    @Test
    fun allCurrentCapabilitiesRemainDisabled() {
        assertDisabled(
            blocked(
                SkaldVaultV1MigrationCorruptionPolicy.evaluate(
                    SkaldVaultV1VaultMigrationCorruptionRequest.summary(),
                ),
            ).capability,
        )
    }

    @Test
    fun readinessProviderAcceptanceAndDependencyRecordsBoundaryWithoutReadiness() {
        val contract = commonProductionProviderAcceptanceContract()
        val storageContract = contract.containerManifestStorageContract
        val readiness = EncryptedVaultReadinessPolicy.disabled()
        val dependency = VaultCryptoDependencyProbeCatalog.currentSpikeResults()
            .single { it.candidate == VaultCryptoDependencyCandidate.TinkBouncyCastleSplit }
        val providerSelection = VaultCryptoProviderSelectionRegistry.select()
        val evidence = ProductionProviderAcceptanceEvidence.currentDesignOnly()

        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            evidence.stateFor(ProductionProviderAcceptanceGate.MigrationCorruptionBoundaryImplementedAndTested),
        )
        assertEquals(SkaldVaultV1MigrationCorruptionPolicy.POLICY_ID, storageContract.migrationCorruptionBoundaryPolicyId)
        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            storageContract.migrationCorruptionBoundaryStatus,
        )
        assertEquals(
            ProductionProviderMigrationCorruptionBoundaryRule.entries.toSet(),
            storageContract.migrationCorruptionBoundaryRules,
        )
        assertTrue(storageContract.migrationCorruptionBoundaryModeled)
        assertTrue(storageContract.migrationCorruptionBoundaryStillDisabled)
        assertTrue(storageContract.migrationCorruptionClassifiesFailureKinds)
        assertTrue(storageContract.migrationCorruptionDoesNotParseRealStorage)
        assertTrue(storageContract.migrationCorruptionDoesNotRepairStorage)
        assertTrue(storageContract.migrationCorruptionDoesNotRunMigration)
        assertTrue(storageContract.migrationCorruptionDoesNotEnableUnlock)
        assertTrue(storageContract.migrationCorruptionDoesNotEnablePersistence)
        assertTrue(storageContract.migrationCorruptionDoesNotEnableProviderSelection)
        assertTrue(storageContract.migrationCorruptionFailureVocabularyModeled)
        assertFalse(storageContract.vaultPersistenceImplemented)
        assertFalse(storageContract.manifestReadWriteImplemented)
        assertFalse(storageContract.storageIndexReadWriteImplemented)
        assertFalse(storageContract.crashRecoveryImplementationAdded)
        assertFalse(storageContract.atomicWriteRecoveryImplementationAdded)
        assertContains(readiness.capabilities, EncryptedVaultCapability.MigrationCorruptionBoundaryBuildingBlock)
        assertContains(
            readiness.requirementStatuses,
            EncryptedVaultRequirement.MigrationCorruptionBoundaryImplementedAndTested,
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[EncryptedVaultRequirement.MigrationCorruptionBoundaryImplementedAndTested],
        )
        assertContains(readiness.blockers, EncryptedVaultBlockingIssue.MigrationCorruptionBoundaryStillDisabled)
        assertContains(readiness.blockers, EncryptedVaultBlockingIssue.MigrationAndCorruptionTestsMissing)
        assertContains(
            dependency.capabilities,
            VaultCryptoDependencyCapability.MigrationCorruptionBoundaryImplementedTested,
        )
        assertContains(
            dependency.capabilities,
            VaultCryptoDependencyCapability.MigrationCorruptionFailureVocabularyModeled,
        )
        assertContains(dependency.blockers, VaultCryptoDependencyBlocker.MigrationCorruptionBoundaryStillDisabled)
        assertContains(dependency.blockers, VaultCryptoDependencyBlocker.MigrationCorruptionRuntimeReviewMissing)
        assertContains(dependency.blockers, VaultCryptoDependencyBlocker.MigrationCorruptionTestsMissing)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, providerSelection.selectedCandidateId)
        assertFalse(providerSelection.productionProviderSelectable)
        assertFalse(providerSelection.requestedCandidate.productionSelectable)
    }

    private fun disabledStorageEvidence(): SkaldVaultV1VaultStorageDisabledEvidence =
        assertIs<SkaldVaultV1VaultStorageOperationResult.Disabled<SkaldVaultV1VaultStorageDisabledEvidence>>(
            SkaldVaultV1DisabledStorageServiceFacade.perform(
                SkaldVaultV1VaultStorageOperationRequest.noEvidence(
                    SkaldVaultV1VaultStorageOperation.RecoverInterruptedWrite,
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

    private fun redactionEvidence(): SkaldVaultV1VaultRedactionEvidence =
        assertIs<SkaldVaultV1VaultRedactionResult.Classified<SkaldVaultV1VaultRedactionEvidence>>(
            SkaldVaultV1RedactionLeakagePolicy.evaluate(
                SkaldVaultV1VaultRedactionRequest.classify(
                    SkaldVaultV1VaultRedactionValueKind.EncryptedVaultRecordBytes,
                ),
            ),
        ).value

    private fun clearWipeEvidence(): SkaldVaultV1VaultClearWipeEvidence =
        assertIs<SkaldVaultV1VaultClearWipeResult.Blocked<SkaldVaultV1VaultClearWipeEvidence>>(
            SkaldVaultV1ClearWipeStrategyPolicy.evaluate(
                SkaldVaultV1VaultClearWipeRequest.summary(),
            ),
        ).value

    private fun lockSessionEvidence(): SkaldVaultV1VaultLockSessionEvidence =
        assertIs<SkaldVaultV1VaultLockSessionResult.Blocked<SkaldVaultV1VaultLockSessionEvidence>>(
            SkaldVaultV1LockSessionLifecyclePolicy.evaluate(
                SkaldVaultV1VaultLockSessionRequest.noEvidence(),
            ),
        ).value

    private fun assertRejected(
        candidate: String?,
        reason: SkaldVaultV1VaultMigrationCorruptionFailureReason,
    ) {
        val request = SkaldVaultV1VaultMigrationCorruptionRequest.rawStorageCandidate(candidate)
        val result = SkaldVaultV1MigrationCorruptionPolicy.evaluate(request)
        val rejected = assertIs<SkaldVaultV1VaultMigrationCorruptionResult.Rejected>(result)

        assertTrue(request.rawCandidateRejected)
        assertEquals(reason, rejected.reason)
        assertFalse(result.toString().contains(candidate ?: ""))
        if (!candidate.isNullOrEmpty()) {
            assertFalse(request.toString().contains(candidate))
            assertFalse(result.toString().contains(candidate))
        }
    }

    private fun blocked(
        result: SkaldVaultV1VaultMigrationCorruptionResult<SkaldVaultV1VaultMigrationCorruptionEvidence>,
    ): SkaldVaultV1VaultMigrationCorruptionEvidence =
        assertIs<
            SkaldVaultV1VaultMigrationCorruptionResult.Blocked<SkaldVaultV1VaultMigrationCorruptionEvidence>,
        >(result).value

    private fun assertDisabled(
        capability: com.libertasprimordium.skald.security.SkaldVaultV1VaultMigrationCorruptionCapability,
    ) {
        assertFalse(capability.migrationAvailable)
        assertFalse(capability.migrationDryRunAvailable)
        assertFalse(capability.repairAvailable)
        assertFalse(capability.quarantineAvailable)
        assertFalse(capability.crashRecoveryAvailable)
        assertFalse(capability.rollbackProtectionAvailable)
        assertFalse(capability.realCorruptionDetectionAvailable)
        assertFalse(capability.realHeaderCommitmentVerificationAvailable)
        assertFalse(capability.realAeadAuthenticationAvailable)
        assertFalse(capability.recordReadAvailable)
        assertFalse(capability.recordWriteAvailable)
        assertFalse(capability.manifestReadWriteAvailable)
        assertFalse(capability.storageIndexReadWriteAvailable)
        assertFalse(capability.providerCryptoAvailable)
        assertFalse(capability.storageServiceAvailable)
        assertFalse(capability.secureSecretStorageAvailable)
        assertFalse(capability.secureMetadataStorageAvailable)
        assertFalse(capability.unlockAvailable)
        assertFalse(capability.activeSessionAvailable)
        assertFalse(capability.providerSelectable)
        assertFalse(capability.vaultCreationAvailable)
        assertFalse(capability.vaultUnlockAvailable)
        assertFalse(capability.vaultPersistenceAvailable)
        assertFalse(capability.mainnetAvailable)
    }
}
