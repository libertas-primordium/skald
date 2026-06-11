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
import com.libertasprimordium.skald.security.ProductionProviderOperationAuthorizationBoundaryRule
import com.libertasprimordium.skald.security.SkaldVaultV1ClearWipeStrategyPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1DisabledStorageServiceFacade
import com.libertasprimordium.skald.security.SkaldVaultV1LockSessionLifecyclePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1MigrationCorruptionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1PassphrasePolicyGate
import com.libertasprimordium.skald.security.SkaldVaultV1ProviderOperationAuthorizationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1RedactionLeakagePolicy
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
import com.libertasprimordium.skald.security.SkaldVaultV1VaultProviderOperationAuthorizationDecision
import com.libertasprimordium.skald.security.SkaldVaultV1VaultProviderOperationAuthorizationEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultProviderOperationAuthorizationRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultProviderOperationAuthorizationResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultProviderOperationAuthorizationStatus
import com.libertasprimordium.skald.security.SkaldVaultV1VaultProviderOperationBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1VaultProviderOperationCapability
import com.libertasprimordium.skald.security.SkaldVaultV1VaultProviderOperationFailureReason
import com.libertasprimordium.skald.security.SkaldVaultV1VaultProviderOperationKind
import com.libertasprimordium.skald.security.SkaldVaultV1VaultProviderOperationPurpose
import com.libertasprimordium.skald.security.SkaldVaultV1VaultProviderOperationRequiredGate
import com.libertasprimordium.skald.security.SkaldVaultV1VaultProviderOperationWarning
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

class VaultProviderOperationAuthorizationBoundaryTest {
    @Test
    fun defaultPolicyIsBlockedFailClosedAndCapabilitiesRemainDisabled() {
        val evidence = blocked(
            SkaldVaultV1ProviderOperationAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultProviderOperationAuthorizationRequest.noEvidence(),
            ),
        )

        assertEquals(
            "skald-vault-v1-provider-operation-authorization-boundary-v1",
            SkaldVaultV1ProviderOperationAuthorizationPolicy.POLICY_ID,
        )
        assertEquals(1, SkaldVaultV1ProviderOperationAuthorizationPolicy.POLICY_VERSION)
        assertEquals(
            SkaldVaultV1VaultProviderOperationAuthorizationStatus.NoEvidenceAvailable,
            evidence.status,
        )
        assertEquals(SkaldVaultV1VaultProviderOperationAuthorizationDecision.BlockedFailClosed, evidence.decision)
        assertContains(
            evidence.blockers,
            SkaldVaultV1VaultProviderOperationBlocker.ProviderOperationAuthorizationStillDisabled,
        )
        assertContains(evidence.blockers, SkaldVaultV1VaultProviderOperationBlocker.DisabledProviderSelected)
        assertContains(evidence.blockers, SkaldVaultV1VaultProviderOperationBlocker.ProductionProviderSelectableFalse)
        assertContains(evidence.warnings, SkaldVaultV1VaultProviderOperationWarning.NoProviderOperationExecuted)
        assertTrue(evidence.providerOperationAuthorizationBlocksAllOperations)
        assertTrue(evidence.providerOperationAuthorizationDoesNotRunCrypto)
        assertTrue(evidence.providerOperationAuthorizationDoesNotRunKat)
        assertTrue(evidence.providerOperationAuthorizationDoesNotGenerateRandomness)
        assertDisabled(evidence.capability)
    }

    @Test
    fun everyModeledOperationKindIsUnauthorized() {
        val kinds = SkaldVaultV1VaultProviderOperationKind.entries.toSet()

        listOf(
            SkaldVaultV1VaultProviderOperationKind.ProviderAvailabilityCheck,
            SkaldVaultV1VaultProviderOperationKind.ProviderKatExecution,
            SkaldVaultV1VaultProviderOperationKind.RuntimeRandomnessCheck,
            SkaldVaultV1VaultProviderOperationKind.EntropyNonceGeneration,
            SkaldVaultV1VaultProviderOperationKind.SaltGeneration,
            SkaldVaultV1VaultProviderOperationKind.KeyGeneration,
            SkaldVaultV1VaultProviderOperationKind.Argon2idKdfExecution,
            SkaldVaultV1VaultProviderOperationKind.HkdfExtractionExpansion,
            SkaldVaultV1VaultProviderOperationKind.HmacComputation,
            SkaldVaultV1VaultProviderOperationKind.HeaderCommitmentComputation,
            SkaldVaultV1VaultProviderOperationKind.HeaderCommitmentVerification,
            SkaldVaultV1VaultProviderOperationKind.AeadEncrypt,
            SkaldVaultV1VaultProviderOperationKind.AeadDecrypt,
            SkaldVaultV1VaultProviderOperationKind.RecordEncrypt,
            SkaldVaultV1VaultProviderOperationKind.RecordDecrypt,
            SkaldVaultV1VaultProviderOperationKind.ManifestAuthentication,
            SkaldVaultV1VaultProviderOperationKind.StorageIndexAuthentication,
            SkaldVaultV1VaultProviderOperationKind.KeyWrapping,
            SkaldVaultV1VaultProviderOperationKind.KeyUnwrapping,
            SkaldVaultV1VaultProviderOperationKind.ProviderClearDispose,
            SkaldVaultV1VaultProviderOperationKind.ProviderSelfTest,
            SkaldVaultV1VaultProviderOperationKind.ProductionProviderSelection,
            SkaldVaultV1VaultProviderOperationKind.VaultCreate,
            SkaldVaultV1VaultProviderOperationKind.VaultUnlock,
            SkaldVaultV1VaultProviderOperationKind.VaultPersistence,
            SkaldVaultV1VaultProviderOperationKind.MainnetOperation,
        ).forEach { kind ->
            assertContains(kinds, kind)
            val evidence = blocked(
                SkaldVaultV1ProviderOperationAuthorizationPolicy.evaluate(
                    SkaldVaultV1VaultProviderOperationAuthorizationRequest.forOperationKind(kind),
                ),
            )
            assertFalse(evidence.capability.providerOperationAuthorized)
            assertFalse(evidence.decision.providerOperationAllowed)
        }
    }

    @Test
    fun operationPurposeVocabularyIsModeled() {
        val purposes = SkaldVaultV1VaultProviderOperationPurpose.entries.toSet()

        listOf(
            SkaldVaultV1VaultProviderOperationPurpose.CreateVault,
            SkaldVaultV1VaultProviderOperationPurpose.UnlockVault,
            SkaldVaultV1VaultProviderOperationPurpose.RotateKeyMaterial,
            SkaldVaultV1VaultProviderOperationPurpose.EncryptCurrentContainer,
            SkaldVaultV1VaultProviderOperationPurpose.DecryptCurrentContainer,
            SkaldVaultV1VaultProviderOperationPurpose.EncryptMetadata,
            SkaldVaultV1VaultProviderOperationPurpose.DecryptMetadata,
            SkaldVaultV1VaultProviderOperationPurpose.EncryptRecord,
            SkaldVaultV1VaultProviderOperationPurpose.DecryptRecord,
            SkaldVaultV1VaultProviderOperationPurpose.AuthenticateManifest,
            SkaldVaultV1VaultProviderOperationPurpose.AuthenticateStorageIndex,
            SkaldVaultV1VaultProviderOperationPurpose.VerifyMigrationCandidate,
            SkaldVaultV1VaultProviderOperationPurpose.VerifyCorruptionEvidence,
            SkaldVaultV1VaultProviderOperationPurpose.RunProviderKat,
            SkaldVaultV1VaultProviderOperationPurpose.RunRuntimeHealthCheck,
            SkaldVaultV1VaultProviderOperationPurpose.PrepareBackupExport,
            SkaldVaultV1VaultProviderOperationPurpose.RestoreImport,
            SkaldVaultV1VaultProviderOperationPurpose.TestOnlyDeterministicVector,
            SkaldVaultV1VaultProviderOperationPurpose.ProductionRuntime,
            SkaldVaultV1VaultProviderOperationPurpose.ReleaseValidation,
            SkaldVaultV1VaultProviderOperationPurpose.MainnetValidation,
        ).forEach { purpose -> assertContains(purposes, purpose) }
    }

    @Test
    fun requiredGateVocabularyIsModeled() {
        val gates = SkaldVaultV1ProviderOperationAuthorizationPolicy.currentPolicySummary().requiredGates

        listOf(
            SkaldVaultV1VaultProviderOperationRequiredGate.ProviderIsNotDisabled,
            SkaldVaultV1VaultProviderOperationRequiredGate.ProviderSelectedThroughRegistry,
            SkaldVaultV1VaultProviderOperationRequiredGate.ProductionProviderSelectableTrue,
            SkaldVaultV1VaultProviderOperationRequiredGate.ProductionProviderImplementationExists,
            SkaldVaultV1VaultProviderOperationRequiredGate.ProviderKatsApproved,
            SkaldVaultV1VaultProviderOperationRequiredGate.DependencyProbeApproved,
            SkaldVaultV1VaultProviderOperationRequiredGate.RuntimeRandomnessCheckApproved,
            SkaldVaultV1VaultProviderOperationRequiredGate.EntropyPolicyApproved,
            SkaldVaultV1VaultProviderOperationRequiredGate.FinalKdfCalibrationApproved,
            SkaldVaultV1VaultProviderOperationRequiredGate.Argon2idParameterPolicyApproved,
            SkaldVaultV1VaultProviderOperationRequiredGate.PassphrasePolicyApproved,
            SkaldVaultV1VaultProviderOperationRequiredGate.LockSessionLifecycleApproved,
            SkaldVaultV1VaultProviderOperationRequiredGate.RedactionLeakagePolicyApproved,
            SkaldVaultV1VaultProviderOperationRequiredGate.ClearWipeStrategyApproved,
            SkaldVaultV1VaultProviderOperationRequiredGate.PersistenceReadinessApprovedForStorage,
            SkaldVaultV1VaultProviderOperationRequiredGate.StorageSafetyApprovedForStorage,
            SkaldVaultV1VaultProviderOperationRequiredGate.MigrationCorruptionPolicyApprovedForParsingMigration,
            SkaldVaultV1VaultProviderOperationRequiredGate.SecureSecretStorageApproved,
            SkaldVaultV1VaultProviderOperationRequiredGate.SecureMetadataStorageApproved,
            SkaldVaultV1VaultProviderOperationRequiredGate.NoRawSecretDiagnosticExposure,
            SkaldVaultV1VaultProviderOperationRequiredGate.NoProviderOperationInUiOrDomainPolicyDirectly,
            SkaldVaultV1VaultProviderOperationRequiredGate.NoBdkPersistenceBypass,
            SkaldVaultV1VaultProviderOperationRequiredGate.OperationAllowedForNetworkMode,
            SkaldVaultV1VaultProviderOperationRequiredGate.MainnetReleaseReviewApproved,
        ).forEach { gate -> assertContains(gates, gate) }
    }

    @Test
    fun operationSpecificRequiredGatesRemainFailClosed() {
        val unlock = blocked(
            SkaldVaultV1ProviderOperationAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultProviderOperationAuthorizationRequest.forOperationKind(
                    SkaldVaultV1VaultProviderOperationKind.VaultUnlock,
                ),
            ),
        )
        val storage = blocked(
            SkaldVaultV1ProviderOperationAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultProviderOperationAuthorizationRequest.forOperationKind(
                    SkaldVaultV1VaultProviderOperationKind.VaultPersistence,
                ),
            ),
        )
        val migration = blocked(
            SkaldVaultV1ProviderOperationAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultProviderOperationAuthorizationRequest.forOperationPurpose(
                    SkaldVaultV1VaultProviderOperationPurpose.VerifyMigrationCandidate,
                ),
            ),
        )
        val mainnet = blocked(
            SkaldVaultV1ProviderOperationAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultProviderOperationAuthorizationRequest.forOperationKind(
                    SkaldVaultV1VaultProviderOperationKind.MainnetOperation,
                ),
            ),
        )

        assertContains(unlock.requiredGates, SkaldVaultV1VaultProviderOperationRequiredGate.PassphrasePolicyApproved)
        assertContains(unlock.requiredGates, SkaldVaultV1VaultProviderOperationRequiredGate.LockSessionLifecycleApproved)
        assertContains(storage.requiredGates, SkaldVaultV1VaultProviderOperationRequiredGate.PersistenceReadinessApprovedForStorage)
        assertContains(storage.requiredGates, SkaldVaultV1VaultProviderOperationRequiredGate.StorageSafetyApprovedForStorage)
        assertContains(migration.requiredGates, SkaldVaultV1VaultProviderOperationRequiredGate.MigrationCorruptionPolicyApprovedForParsingMigration)
        assertEquals(SkaldVaultV1VaultProviderOperationAuthorizationDecision.RejectOperation, mainnet.decision)
        assertContains(mainnet.blockers, SkaldVaultV1VaultProviderOperationBlocker.MainnetUnavailable)
    }

    @Test
    fun typedEvidenceCompositionConsumesDisabledBoundariesButCannotAuthorizeProviderOperations() {
        val composed = blocked(
            SkaldVaultV1ProviderOperationAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultProviderOperationAuthorizationRequest.fromEvidence(
                    providerSelectionResult = VaultCryptoProviderSelectionRegistry.select(),
                    providerAcceptanceAssessment = commonProductionProviderAcceptanceContract().assess(),
                    dependencyProbeResult = dependencyProbeResult(),
                    encryptedVaultReadiness = EncryptedVaultReadinessPolicy.disabled(),
                    persistenceReadinessEvidence = persistenceReadinessEvidence(),
                    lockSessionLifecycleEvidence = lockSessionEvidence(),
                    passphrasePolicyEvidence = passphraseEvidence(),
                    redactionLeakageEvidence = redactionEvidence(),
                    clearWipeStrategyEvidence = clearWipeEvidence(),
                    migrationCorruptionEvidence = migrationCorruptionEvidence(),
                    disabledStorageServiceEvidence = disabledStorageEvidence(),
                    secureStorageCapability = commonDisabledSecureStorageCapability(),
                    secureMetadataCapability = commonDisabledSecureMetadataCapability(),
                    operationKind = SkaldVaultV1VaultProviderOperationKind.RecordDecrypt,
                    operationPurpose = SkaldVaultV1VaultProviderOperationPurpose.DecryptRecord,
                ),
            ),
        )

        assertTrue(composed.providerSelectionEvidenceConsumed)
        assertTrue(composed.providerAcceptanceEvidenceConsumed)
        assertTrue(composed.dependencyProbeEvidenceConsumed)
        assertTrue(composed.encryptedVaultReadinessEvidenceConsumed)
        assertTrue(composed.persistenceReadinessEvidenceConsumed)
        assertTrue(composed.lockSessionLifecycleEvidenceConsumed)
        assertTrue(composed.passphrasePolicyEvidenceConsumed)
        assertTrue(composed.redactionLeakageEvidenceConsumed)
        assertTrue(composed.clearWipeStrategyEvidenceConsumed)
        assertTrue(composed.migrationCorruptionEvidenceConsumed)
        assertTrue(composed.disabledStorageServiceEvidenceConsumed)
        assertTrue(composed.secureStorageEvidenceConsumed)
        assertTrue(composed.secureMetadataEvidenceConsumed)
        assertFalse(composed.storageSafetyPreflightEvidenceConsumed)
        assertEquals(SkaldVaultV1VaultProviderOperationAuthorizationDecision.BlockedFailClosed, composed.decision)
        assertContains(composed.blockers, SkaldVaultV1VaultProviderOperationBlocker.ProviderSelectionDisabled)
        assertContains(composed.blockers, SkaldVaultV1VaultProviderOperationBlocker.ProductionProviderSelectableFalse)
        assertContains(composed.blockers, SkaldVaultV1VaultProviderOperationBlocker.PassphrasePolicyBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultProviderOperationBlocker.LockSessionLifecycleBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultProviderOperationBlocker.PersistenceReadinessBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultProviderOperationBlocker.MigrationCorruptionBoundaryDisabled)
        assertContains(composed.blockers, SkaldVaultV1VaultProviderOperationBlocker.ClearWipeStrategyBlocked)
        assertContains(composed.blockers, SkaldVaultV1VaultProviderOperationBlocker.RedactionLeakageUnsafe)
        assertContains(composed.blockers, SkaldVaultV1VaultProviderOperationBlocker.SecureSecretStorageUnavailable)
        assertContains(composed.blockers, SkaldVaultV1VaultProviderOperationBlocker.SecureMetadataStorageUnavailable)
        assertContains(composed.blockers, SkaldVaultV1VaultProviderOperationBlocker.WarningOnlyEvidenceRejected)
        assertContains(composed.blockers, SkaldVaultV1VaultProviderOperationBlocker.UserConsentOverrideRejected)
        assertDisabled(composed.capability)
    }

    @Test
    fun renderingAndRawCandidateRejectionRemainRedacted() {
        val raw = "cipher" + "text-fixture"
        val request = SkaldVaultV1VaultProviderOperationAuthorizationRequest.rawOperationCandidate(raw)
        val result = SkaldVaultV1ProviderOperationAuthorizationPolicy.evaluate(request)
        val evidence = blocked(
            SkaldVaultV1ProviderOperationAuthorizationPolicy.evaluate(
                SkaldVaultV1VaultProviderOperationAuthorizationRequest.summary(),
            ),
        )

        assertFalse(request.toString().contains(raw))
        assertFalse(result.toString().contains(raw))
        assertFalse(evidence.toString().contains(raw))
        assertFalse(evidence.policyTokenEvidence.toString().contains(raw))
        assertFalse(evidence.policyTokenEvidence.containsPassphrase)
        assertFalse(evidence.policyTokenEvidence.containsKeyMaterial)
        assertFalse(evidence.policyTokenEvidence.containsProviderHandle)
        assertFalse(evidence.policyTokenEvidence.containsEntropyRandomSaltNonceBytes)
        assertFalse(evidence.policyTokenEvidence.containsKdfOutput)
        assertFalse(evidence.policyTokenEvidence.containsHeaderCommitmentBytes)
        assertFalse(evidence.policyTokenEvidence.containsAeadTagBytes)
        assertFalse(evidence.policyTokenEvidence.containsCiphertext)
        assertFalse(evidence.policyTokenEvidence.containsPlaintext)
        assertFalse(evidence.policyTokenEvidence.containsRecordIdentifier)
        assertFalse(evidence.policyTokenEvidence.containsRootOrPathText)
        assertFalse(evidence.policyTokenEvidence.containsPayload)

        assertRejected("provider-handle", SkaldVaultV1VaultProviderOperationFailureReason.ProviderHandleRejected)
        assertRejected(
            "provider-implementation",
            SkaldVaultV1VaultProviderOperationFailureReason.ProviderImplementationInstanceRejected,
        )
        assertRejected("pass" + "phrase-fixture", SkaldVaultV1VaultProviderOperationFailureReason.RawPassphraseRejected)
        assertRejected("key-material", SkaldVaultV1VaultProviderOperationFailureReason.KeyMaterialRejected)
        assertRejected("kdf-input", SkaldVaultV1VaultProviderOperationFailureReason.KdfInputRejected)
        assertRejected("kdf-output", SkaldVaultV1VaultProviderOperationFailureReason.KdfOutputRejected)
        assertRejected("entropy-fixture", SkaldVaultV1VaultProviderOperationFailureReason.EntropyRandomSaltNonceBytesRejected)
        assertRejected("random-fixture", SkaldVaultV1VaultProviderOperationFailureReason.EntropyRandomSaltNonceBytesRejected)
        assertRejected("salt-fixture", SkaldVaultV1VaultProviderOperationFailureReason.EntropyRandomSaltNonceBytesRejected)
        assertRejected("nonce-fixture", SkaldVaultV1VaultProviderOperationFailureReason.EntropyRandomSaltNonceBytesRejected)
        assertRejected("aead-tag", SkaldVaultV1VaultProviderOperationFailureReason.AeadKeyTagCiphertextPlaintextRejected)
        assertRejected(raw, SkaldVaultV1VaultProviderOperationFailureReason.AeadKeyTagCiphertextPlaintextRejected)
        assertRejected("plain" + "text-fixture", SkaldVaultV1VaultProviderOperationFailureReason.AeadKeyTagCiphertextPlaintextRejected)
        assertRejected("record-bytes", SkaldVaultV1VaultProviderOperationFailureReason.RecordBytesRejected)
        assertRejected("container-bytes", SkaldVaultV1VaultProviderOperationFailureReason.RawPersistedContainerBytesRejected)
        assertRejected("bytearray-fixture", SkaldVaultV1VaultProviderOperationFailureReason.ByteArrayInputRejected)
        assertRejected("chararray-fixture", SkaldVaultV1VaultProviderOperationFailureReason.CharArrayInputRejected)
        assertRejected("/vault/root", SkaldVaultV1VaultProviderOperationFailureReason.RawAbsoluteLocationInputRejected)
        assertRejected("vault/root", SkaldVaultV1VaultProviderOperationFailureReason.RawRelativeLocationInputRejected)
        assertRejected("https://example.invalid", SkaldVaultV1VaultProviderOperationFailureReason.LinkLikeInputRejected)
        assertRejected("file-object", SkaldVaultV1VaultProviderOperationFailureReason.PlatformObjectLikeInputRejected)
        assertRejected("a".repeat(64), SkaldVaultV1VaultProviderOperationFailureReason.TransactionLikeEvidenceRejected)
        assertRejected("bc" + "1q" + "a".repeat(24), SkaldVaultV1VaultProviderOperationFailureReason.BitcoinAddressLikeEvidenceRejected)
        assertRejected("ns" + "ec1fixture", SkaldVaultV1VaultProviderOperationFailureReason.WalletMaterialRejected)
        assertRejected("xp" + "rvfixture", SkaldVaultV1VaultProviderOperationFailureReason.WalletMaterialRejected)
        assertRejected("tp" + "rvfixture", SkaldVaultV1VaultProviderOperationFailureReason.WalletMaterialRejected)
        assertRejected("K" + "a".repeat(50), SkaldVaultV1VaultProviderOperationFailureReason.WalletMaterialRejected)
        assertRejected("../vault", SkaldVaultV1VaultProviderOperationFailureReason.TraversalRejected)
        assertRejected("unsupported#chars", SkaldVaultV1VaultProviderOperationFailureReason.UnsupportedCharactersRejected)
    }

    @Test
    fun allCurrentCapabilitiesRemainDisabled() {
        assertDisabled(
            blocked(
                SkaldVaultV1ProviderOperationAuthorizationPolicy.evaluate(
                    SkaldVaultV1VaultProviderOperationAuthorizationRequest.summary(),
                ),
            ).capability,
        )
    }

    @Test
    fun readinessProviderAcceptanceAndDependencyRecordsBoundaryWithoutAuthorization() {
        val contract = commonProductionProviderAcceptanceContract()
        val storageContract = contract.containerManifestStorageContract
        val readiness = EncryptedVaultReadinessPolicy.disabled()
        val dependency = dependencyProbeResult()
        val providerSelection = VaultCryptoProviderSelectionRegistry.select()
        val evidence = ProductionProviderAcceptanceEvidence.currentDesignOnly()

        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            evidence.stateFor(ProductionProviderAcceptanceGate.ProviderOperationAuthorizationBoundaryImplementedAndTested),
        )
        assertEquals(
            SkaldVaultV1ProviderOperationAuthorizationPolicy.POLICY_ID,
            storageContract.providerOperationAuthorizationBoundaryPolicyId,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            storageContract.providerOperationAuthorizationBoundaryStatus,
        )
        assertEquals(
            ProductionProviderOperationAuthorizationBoundaryRule.entries.toSet(),
            storageContract.providerOperationAuthorizationBoundaryRules,
        )
        assertTrue(storageContract.providerOperationAuthorizationBoundaryModeled)
        assertTrue(storageContract.providerOperationAuthorizationStillDisabled)
        assertTrue(storageContract.providerOperationAuthorizationBlocksAllOperations)
        assertTrue(storageContract.providerOperationAuthorizationDoesNotRunCrypto)
        assertTrue(storageContract.providerOperationAuthorizationDoesNotRunKat)
        assertTrue(storageContract.providerOperationAuthorizationDoesNotGenerateRandomness)
        assertTrue(storageContract.providerOperationAuthorizationDoesNotEnableUnlock)
        assertTrue(storageContract.providerOperationAuthorizationDoesNotEnablePersistence)
        assertTrue(storageContract.providerOperationAuthorizationDoesNotEnableProviderSelection)
        assertTrue(storageContract.providerOperationFailureVocabularyModeled)
        assertFalse(storageContract.vaultPersistenceImplemented)
        assertContains(readiness.capabilities, EncryptedVaultCapability.ProviderOperationAuthorizationBoundaryBuildingBlock)
        assertContains(
            readiness.requirementStatuses,
            EncryptedVaultRequirement.ProviderOperationAuthorizationBoundaryImplementedAndTested,
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.ProviderOperationAuthorizationBoundaryImplementedAndTested
            ],
        )
        assertContains(readiness.blockers, EncryptedVaultBlockingIssue.ProviderOperationAuthorizationBoundaryStillDisabled)
        assertContains(
            dependency.capabilities,
            VaultCryptoDependencyCapability.ProviderOperationAuthorizationBoundaryImplementedTested,
        )
        assertContains(
            dependency.capabilities,
            VaultCryptoDependencyCapability.ProviderOperationFailureVocabularyModeled,
        )
        assertContains(
            dependency.blockers,
            VaultCryptoDependencyBlocker.ProviderOperationAuthorizationBoundaryStillDisabled,
        )
        assertContains(
            dependency.blockers,
            VaultCryptoDependencyBlocker.ProviderOperationAuthorizationRuntimeReviewMissing,
        )
        assertContains(
            dependency.blockers,
            VaultCryptoDependencyBlocker.ProviderOperationAuthorizationTestsMissing,
        )
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, providerSelection.selectedCandidateId)
        assertFalse(providerSelection.productionProviderSelectable)
        assertFalse(providerSelection.requestedCandidate.productionSelectable)
    }

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
        reason: SkaldVaultV1VaultProviderOperationFailureReason,
    ) {
        val request = SkaldVaultV1VaultProviderOperationAuthorizationRequest.rawOperationCandidate(candidate)
        val result = SkaldVaultV1ProviderOperationAuthorizationPolicy.evaluate(request)
        val rejected = assertIs<SkaldVaultV1VaultProviderOperationAuthorizationResult.Rejected>(result)

        assertTrue(request.rawCandidateRejected)
        assertEquals(reason, rejected.reason)
        assertFalse(result.toString().contains(candidate ?: ""))
        if (!candidate.isNullOrEmpty()) {
            assertFalse(request.toString().contains(candidate))
            assertFalse(result.toString().contains(candidate))
        }
    }

    private fun blocked(
        result: SkaldVaultV1VaultProviderOperationAuthorizationResult<
            SkaldVaultV1VaultProviderOperationAuthorizationEvidence,
        >,
    ): SkaldVaultV1VaultProviderOperationAuthorizationEvidence =
        assertIs<
            SkaldVaultV1VaultProviderOperationAuthorizationResult.Blocked<
                SkaldVaultV1VaultProviderOperationAuthorizationEvidence,
            >,
        >(result).value

    private fun assertDisabled(capability: SkaldVaultV1VaultProviderOperationCapability) {
        assertFalse(capability.providerOperationAuthorized)
        assertFalse(capability.providerCryptoAvailable)
        assertFalse(capability.providerKatExecutionAvailable)
        assertFalse(capability.runtimeRandomnessCheckAvailable)
        assertFalse(capability.entropyGenerationAvailable)
        assertFalse(capability.saltGenerationAvailable)
        assertFalse(capability.nonceGenerationAvailable)
        assertFalse(capability.keyGenerationAvailable)
        assertFalse(capability.kdfExecutionAvailable)
        assertFalse(capability.argon2idExecutionAvailable)
        assertFalse(capability.hkdfExecutionAvailable)
        assertFalse(capability.hmacExecutionAvailable)
        assertFalse(capability.headerCommitmentComputationAvailable)
        assertFalse(capability.headerCommitmentVerificationAvailable)
        assertFalse(capability.aeadEncryptAvailable)
        assertFalse(capability.aeadDecryptAvailable)
        assertFalse(capability.recordEncryptAvailable)
        assertFalse(capability.recordDecryptAvailable)
        assertFalse(capability.keyWrappingAvailable)
        assertFalse(capability.keyUnwrappingAvailable)
        assertFalse(capability.providerClearAvailable)
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
