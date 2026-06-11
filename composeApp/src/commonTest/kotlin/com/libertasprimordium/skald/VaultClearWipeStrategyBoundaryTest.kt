package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.EncryptedVaultBlockingIssue
import com.libertasprimordium.skald.security.EncryptedVaultCapability
import com.libertasprimordium.skald.security.EncryptedVaultReadinessPolicy
import com.libertasprimordium.skald.security.EncryptedVaultRequirement
import com.libertasprimordium.skald.security.EncryptedVaultRequirementStatus
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidence
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidenceState
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceGate
import com.libertasprimordium.skald.security.ProductionProviderClearWipeStrategyBoundaryRule
import com.libertasprimordium.skald.security.ProductionProviderConstructionContractStatus
import com.libertasprimordium.skald.security.SkaldVaultV1ClearWipeStrategyPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1DisabledStorageServiceFacade
import com.libertasprimordium.skald.security.SkaldVaultV1LockSessionLifecyclePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1PassphrasePolicyGate
import com.libertasprimordium.skald.security.SkaldVaultV1PersistenceReadinessGate
import com.libertasprimordium.skald.security.SkaldVaultV1RedactionLeakagePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1StillDisabledProviderFacade
import com.libertasprimordium.skald.security.SkaldVaultV1VaultClearWipeBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1VaultClearWipeCapability
import com.libertasprimordium.skald.security.SkaldVaultV1VaultClearWipeDecision
import com.libertasprimordium.skald.security.SkaldVaultV1VaultClearWipeEventKind
import com.libertasprimordium.skald.security.SkaldVaultV1VaultClearWipeEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultClearWipeFailureReason
import com.libertasprimordium.skald.security.SkaldVaultV1VaultClearWipeLimitation
import com.libertasprimordium.skald.security.SkaldVaultV1VaultClearWipeRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultClearWipeRequirement
import com.libertasprimordium.skald.security.SkaldVaultV1VaultClearWipeResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultClearWipeStatus
import com.libertasprimordium.skald.security.SkaldVaultV1VaultClearWipeStrategyClass
import com.libertasprimordium.skald.security.SkaldVaultV1VaultClearWipeValueKind
import com.libertasprimordium.skald.security.SkaldVaultV1VaultLockSessionEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultLockSessionRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultLockSessionResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPassphrasePolicyEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPassphrasePolicyRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPassphrasePolicyResult
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

class VaultClearWipeStrategyBoundaryTest {
    private val rawSecretFixture = "pass" + "phrase-fixture"
    private val rawKeyFixture = "provider-key-material"
    private val rawBufferFixture = "bytearray-fixture"
    private val rawRootFixture = "/vault-root-fixture"
    private val rawRecordFixture = "decrypted-record-fixture"
    private val rawLongHexFixture = "a".repeat(64)

    @Test
    fun defaultClearWipePolicyIsBlockedFailClosedAndStillDisabled() {
        val evidence = blocked(
            SkaldVaultV1ClearWipeStrategyPolicy.evaluate(
                SkaldVaultV1VaultClearWipeRequest.noEvidence(),
            ),
        )
        val capability = evidence.capability

        assertEquals("skald-vault-v1-clear-wipe-strategy-boundary-v1", SkaldVaultV1ClearWipeStrategyPolicy.POLICY_ID)
        assertEquals(1, SkaldVaultV1ClearWipeStrategyPolicy.POLICY_VERSION)
        assertEquals(SkaldVaultV1VaultClearWipeStatus.NoEvidenceAvailable, evidence.status)
        assertEquals(SkaldVaultV1VaultClearWipeDecision.BlockedFailClosed, evidence.decision)
        assertFalse(evidence.actualClearReady)
        assertFalse(evidence.wipeReady)
        assertFalse(evidence.zeroizationReady)
        assertFalse(evidence.keyMaterialClearReady)
        assertFalse(evidence.passphraseClearReady)
        assertDisabledCapabilities(capability)
        assertContains(evidence.blockers, SkaldVaultV1VaultClearWipeBlocker.ClearWipeStrategyStillDisabled)
        assertContains(evidence.blockers, SkaldVaultV1VaultClearWipeBlocker.ActualClearImplementationMissing)
        assertContains(evidence.blockers, SkaldVaultV1VaultClearWipeBlocker.ActualZeroizationImplementationMissing)
        assertContains(evidence.blockers, SkaldVaultV1VaultClearWipeBlocker.JvmZeroizationUnproven)
        assertContains(evidence.blockers, SkaldVaultV1VaultClearWipeBlocker.MainnetUnavailable)
    }

    @Test
    fun sensitiveValueVocabularyIsModeled() {
        val kinds = SkaldVaultV1VaultClearWipeValueKind.entries.toSet()

        listOf(
            SkaldVaultV1VaultClearWipeValueKind.PassphraseInputBuffer,
            SkaldVaultV1VaultClearWipeValueKind.PinInputBuffer,
            SkaldVaultV1VaultClearWipeValueKind.MnemonicText,
            SkaldVaultV1VaultClearWipeValueKind.SeedBytes,
            SkaldVaultV1VaultClearWipeValueKind.PrivateKeyBytes,
            SkaldVaultV1VaultClearWipeValueKind.XprvTprvWifText,
            SkaldVaultV1VaultClearWipeValueKind.NostrNsecPrivateKeyMaterial,
            SkaldVaultV1VaultClearWipeValueKind.DescriptorPrivateMaterial,
            SkaldVaultV1VaultClearWipeValueKind.ProviderRootKey,
            SkaldVaultV1VaultClearWipeValueKind.VaultRootKey,
            SkaldVaultV1VaultClearWipeValueKind.MetadataEncryptionKey,
            SkaldVaultV1VaultClearWipeValueKind.RecordEncryptionKey,
            SkaldVaultV1VaultClearWipeValueKind.BackupExportKey,
            SkaldVaultV1VaultClearWipeValueKind.KeyWrappingKey,
            SkaldVaultV1VaultClearWipeValueKind.RawKdfInput,
            SkaldVaultV1VaultClearWipeValueKind.RawKdfOutput,
            SkaldVaultV1VaultClearWipeValueKind.RawAeadKey,
            SkaldVaultV1VaultClearWipeValueKind.RandomEntropyBuffer,
            SkaldVaultV1VaultClearWipeValueKind.DecryptedVaultRecord,
            SkaldVaultV1VaultClearWipeValueKind.DecryptedMetadataRecord,
            SkaldVaultV1VaultClearWipeValueKind.EncryptedRecordStagingBuffer,
            SkaldVaultV1VaultClearWipeValueKind.ManifestStagingBuffer,
            SkaldVaultV1VaultClearWipeValueKind.StorageIndexStagingBuffer,
            SkaldVaultV1VaultClearWipeValueKind.ProviderSessionHandle,
            SkaldVaultV1VaultClearWipeValueKind.StorageSessionHandle,
            SkaldVaultV1VaultClearWipeValueKind.LockSessionToken,
            SkaldVaultV1VaultClearWipeValueKind.PassphraseRetryThrottleState,
            SkaldVaultV1VaultClearWipeValueKind.RedactionDiagnosticStagingValue,
            SkaldVaultV1VaultClearWipeValueKind.WalletLabelTransactionNoteSensitiveMetadata,
            SkaldVaultV1VaultClearWipeValueKind.BackendCredentialStagingValue,
            SkaldVaultV1VaultClearWipeValueKind.LightningCashuNostrCredentialStagingValue,
            SkaldVaultV1VaultClearWipeValueKind.FuturePlatformWrappedKeyReference,
            SkaldVaultV1VaultClearWipeValueKind.FutureAndroidHardwareWrappedKeyHandle,
            SkaldVaultV1VaultClearWipeValueKind.FutureLinuxOptionalKeyWrappingHandle,
        ).forEach { kind -> assertContains(kinds, kind) }
    }

    @Test
    fun lifecycleTriggerVocabularyIsModeled() {
        val events = SkaldVaultV1VaultClearWipeEventKind.entries.toSet()

        listOf(
            SkaldVaultV1VaultClearWipeEventKind.UserRequestsLock,
            SkaldVaultV1VaultClearWipeEventKind.TimeoutReached,
            SkaldVaultV1VaultClearWipeEventKind.AppBackgrounded,
            SkaldVaultV1VaultClearWipeEventKind.AppForegroundedAfterLockRequiredState,
            SkaldVaultV1VaultClearWipeEventKind.AppCloseShutdown,
            SkaldVaultV1VaultClearWipeEventKind.ErrorFault,
            SkaldVaultV1VaultClearWipeEventKind.UnlockAttemptFails,
            SkaldVaultV1VaultClearWipeEventKind.UnlockCancelled,
            SkaldVaultV1VaultClearWipeEventKind.ProviderSelectionChanges,
            SkaldVaultV1VaultClearWipeEventKind.ProviderKatStatusChanges,
            SkaldVaultV1VaultClearWipeEventKind.PersistenceReadinessChanges,
            SkaldVaultV1VaultClearWipeEventKind.StorageSafetyChanges,
            SkaldVaultV1VaultClearWipeEventKind.StorageServiceFailure,
            SkaldVaultV1VaultClearWipeEventKind.SecureStorageCapabilityChanges,
            SkaldVaultV1VaultClearWipeEventKind.SecureMetadataCapabilityChanges,
            SkaldVaultV1VaultClearWipeEventKind.RootPathEvidenceChanges,
            SkaldVaultV1VaultClearWipeEventKind.PlatformSecurityPostureChanges,
            SkaldVaultV1VaultClearWipeEventKind.MainnetRequestAttempted,
            SkaldVaultV1VaultClearWipeEventKind.MigrationCorruptionDetected,
            SkaldVaultV1VaultClearWipeEventKind.CrashRecoveryBegins,
            SkaldVaultV1VaultClearWipeEventKind.BackupExportCompletes,
            SkaldVaultV1VaultClearWipeEventKind.SupportDebugDiagnosticRequested,
            SkaldVaultV1VaultClearWipeEventKind.RecordDecryptCompletes,
            SkaldVaultV1VaultClearWipeEventKind.RecordEncryptCompletes,
            SkaldVaultV1VaultClearWipeEventKind.SessionQueried,
        ).forEach { event -> assertContains(events, event) }
    }

    @Test
    fun strategyAndRequirementVocabularyIsModeled() {
        val strategies = SkaldVaultV1VaultClearWipeStrategyClass.entries.toSet()
        val requirements = SkaldVaultV1ClearWipeStrategyPolicy.currentPolicySummary().requirements
        val limitations = SkaldVaultV1ClearWipeStrategyPolicy.currentPolicySummary().limitations

        listOf(
            SkaldVaultV1VaultClearWipeStrategyClass.ForbiddenValueMustNeverBeAccepted,
            SkaldVaultV1VaultClearWipeStrategyClass.NoRealValuePresentNoWipePossible,
            SkaldVaultV1VaultClearWipeStrategyClass.ModelOnlyRequirement,
            SkaldVaultV1VaultClearWipeStrategyClass.BestEffortFutureJvmClearRequired,
            SkaldVaultV1VaultClearWipeStrategyClass.BestEffortFutureNativePlatformClearRequired,
            SkaldVaultV1VaultClearWipeStrategyClass.ProviderOwnedClearRequired,
            SkaldVaultV1VaultClearWipeStrategyClass.StorageServiceClearRequired,
            SkaldVaultV1VaultClearWipeStrategyClass.SessionTokenInvalidationRequired,
            SkaldVaultV1VaultClearWipeStrategyClass.RedactionOnlyClearRequired,
            SkaldVaultV1VaultClearWipeStrategyClass.DeleteStagedReferenceRequired,
            SkaldVaultV1VaultClearWipeStrategyClass.UnsupportedFailClosed,
            SkaldVaultV1VaultClearWipeStrategyClass.ImpossibleToProveJvmZeroizationLimitation,
        ).forEach { strategy -> assertContains(strategies, strategy) }

        assertContains(requirements, SkaldVaultV1VaultClearWipeRequirement.ActualClearImplementationRequiredFutureOnly)
        assertContains(requirements, SkaldVaultV1VaultClearWipeRequirement.ActualZeroizationImplementationRequiredFutureOnly)
        assertContains(requirements, SkaldVaultV1VaultClearWipeRequirement.JvmZeroizationCannotBeProvenByModel)
        assertContains(requirements, SkaldVaultV1VaultClearWipeRequirement.ProviderOwnedClearReviewRequired)
        assertContains(requirements, SkaldVaultV1VaultClearWipeRequirement.StorageServiceClearReviewRequired)
        assertContains(requirements, SkaldVaultV1VaultClearWipeRequirement.SessionInvalidationReviewRequired)
        assertContains(requirements, SkaldVaultV1VaultClearWipeRequirement.RedactionDiagnosticClearReviewRequired)
        assertContains(requirements, SkaldVaultV1VaultClearWipeRequirement.FailClosedLockRequired)
        assertContains(limitations, SkaldVaultV1VaultClearWipeLimitation.JvmCopiesCannotBeProvenCleared)
        assertContains(limitations, SkaldVaultV1VaultClearWipeLimitation.NoActualMemoryZeroization)
    }

    @Test
    fun valueAndEventMappingModelsRequirementsButNeverActualClear() {
        val provider = blocked(
            SkaldVaultV1ClearWipeStrategyPolicy.evaluate(
                SkaldVaultV1VaultClearWipeRequest.forValueAndEvent(
                    valueKind = SkaldVaultV1VaultClearWipeValueKind.ProviderRootKey,
                    eventKind = SkaldVaultV1VaultClearWipeEventKind.TimeoutReached,
                ),
            ),
        )
        val storage = blocked(
            SkaldVaultV1ClearWipeStrategyPolicy.evaluate(
                SkaldVaultV1VaultClearWipeRequest.forValueAndEvent(
                    valueKind = SkaldVaultV1VaultClearWipeValueKind.ManifestStagingBuffer,
                    eventKind = SkaldVaultV1VaultClearWipeEventKind.StorageServiceFailure,
                ),
            ),
        )
        val session = blocked(
            SkaldVaultV1ClearWipeStrategyPolicy.evaluate(
                SkaldVaultV1VaultClearWipeRequest.forValueAndEvent(
                    valueKind = SkaldVaultV1VaultClearWipeValueKind.LockSessionToken,
                    eventKind = SkaldVaultV1VaultClearWipeEventKind.UserRequestsLock,
                ),
            ),
        )
        val diagnostic = blocked(
            SkaldVaultV1ClearWipeStrategyPolicy.evaluate(
                SkaldVaultV1VaultClearWipeRequest.forValueAndEvent(
                    valueKind = SkaldVaultV1VaultClearWipeValueKind.RedactionDiagnosticStagingValue,
                    eventKind = SkaldVaultV1VaultClearWipeEventKind.SupportDebugDiagnosticRequested,
                ),
            ),
        )

        assertEquals(SkaldVaultV1VaultClearWipeStrategyClass.ProviderOwnedClearRequired, provider.strategyClass)
        assertContains(provider.blockers, SkaldVaultV1VaultClearWipeBlocker.ProviderClearUnavailable)
        assertEquals(SkaldVaultV1VaultClearWipeStrategyClass.StorageServiceClearRequired, storage.strategyClass)
        assertContains(storage.blockers, SkaldVaultV1VaultClearWipeBlocker.StorageClearUnavailable)
        assertEquals(SkaldVaultV1VaultClearWipeStrategyClass.SessionTokenInvalidationRequired, session.strategyClass)
        assertContains(session.blockers, SkaldVaultV1VaultClearWipeBlocker.SessionInvalidationUnavailable)
        assertEquals(SkaldVaultV1VaultClearWipeStrategyClass.RedactionOnlyClearRequired, diagnostic.strategyClass)
        listOf(provider, storage, session, diagnostic).forEach { evidence ->
            assertFalse(evidence.capability.actualClearImplemented)
            assertFalse(evidence.capability.actualZeroizationImplemented)
            assertFalse(evidence.capability.jvmZeroizationProven)
            assertFalse(evidence.capability.unlockAvailable)
            assertFalse(evidence.capability.vaultPersistenceAvailable)
        }
    }

    @Test
    fun composedEvidenceCannotEnableClearWipeUnlockOrPersistence() {
        val evidence = blocked(
            SkaldVaultV1ClearWipeStrategyPolicy.evaluate(
                SkaldVaultV1VaultClearWipeRequest.fromEvidence(
                    passphrasePolicyEvidence = passphraseEvidence(),
                    redactionLeakageEvidence = redactionEvidence(),
                    lockSessionLifecycleEvidence = lockEvidence(),
                    persistenceReadinessEvidence = persistenceEvidence(),
                    disabledProviderFacadeMetadata = SkaldVaultV1StillDisabledProviderFacade.metadata(),
                    disabledStorageServiceEvidence = storageEvidence(),
                    secureStorageCapability = commonDisabledSecureStorageCapability(),
                    secureMetadataCapability = commonDisabledSecureMetadataCapability(),
                    dependencyProbeResult = dependencyEvidence(),
                    valueKind = SkaldVaultV1VaultClearWipeValueKind.VaultRootKey,
                    eventKind = SkaldVaultV1VaultClearWipeEventKind.ErrorFault,
                ),
            ),
        )

        assertEquals(SkaldVaultV1VaultClearWipeDecision.BlockedFailClosed, evidence.decision)
        assertTrue(evidence.passphrasePolicyEvidenceConsumed)
        assertTrue(evidence.redactionLeakageEvidenceConsumed)
        assertTrue(evidence.lockSessionLifecycleEvidenceConsumed)
        assertTrue(evidence.persistenceReadinessEvidenceConsumed)
        assertTrue(evidence.disabledProviderFacadeEvidenceConsumed)
        assertTrue(evidence.disabledStorageServiceEvidenceConsumed)
        assertTrue(evidence.secureStorageEvidenceConsumed)
        assertTrue(evidence.secureMetadataEvidenceConsumed)
        assertTrue(evidence.dependencyReadinessEvidenceConsumed)
        assertContains(evidence.blockers, SkaldVaultV1VaultClearWipeBlocker.PassphrasePolicyInputBlocked)
        assertContains(evidence.blockers, SkaldVaultV1VaultClearWipeBlocker.RedactionLeakageNonLogging)
        assertContains(evidence.blockers, SkaldVaultV1VaultClearWipeBlocker.LockSessionUnlockBlocked)
        assertContains(evidence.blockers, SkaldVaultV1VaultClearWipeBlocker.PersistenceReadinessBlocked)
        assertContains(evidence.blockers, SkaldVaultV1VaultClearWipeBlocker.DisabledStorageServiceFacadeStillDisabled)
        assertContains(evidence.blockers, SkaldVaultV1VaultClearWipeBlocker.WarningOnlyEvidenceRejected)
        assertContains(evidence.blockers, SkaldVaultV1VaultClearWipeBlocker.UserConsentOverrideRejected)
        assertFalse(evidence.capability.providerClearAvailable)
        assertFalse(evidence.capability.storageClearAvailable)
        assertFalse(evidence.capability.sessionInvalidationAvailable)
        assertFalse(evidence.capability.providerSelectable)
        assertFalse(evidence.capability.mainnetAvailable)
    }

    @Test
    fun rawClearWipeInputsAreRejectedWithoutEchoingRawValues() {
        val bitcoinAddressLike = "bc" + "1" + "q".repeat(24)
        val nostrSecretLike = "ns" + "ec" + "1" + "q".repeat(24)
        val extendedPrivatePrefixFixture = "xp" + "rv" + "9".repeat(32)
        val testPrivatePrefixFixture = "tp" + "rv" + "9".repeat(32)
        val wifLike = "K" + "1".repeat(50)
        val cases = listOf(
            null to SkaldVaultV1VaultClearWipeFailureReason.EmptyEvidenceRejected,
            "" to SkaldVaultV1VaultClearWipeFailureReason.EmptyEvidenceRejected,
            "   " to SkaldVaultV1VaultClearWipeFailureReason.EmptyEvidenceRejected,
            rawSecretFixture to SkaldVaultV1VaultClearWipeFailureReason.PassphraseInputRejected,
            "pin-fixture" to SkaldVaultV1VaultClearWipeFailureReason.PinInputRejected,
            "mne" + "monic-fixture" to SkaldVaultV1VaultClearWipeFailureReason.MnemonicInputRejected,
            "seed-fixture" to SkaldVaultV1VaultClearWipeFailureReason.SeedMaterialRejected,
            "raw-key-fixture" to SkaldVaultV1VaultClearWipeFailureReason.RawKeyInputRejected,
            "raw-entropy-fixture" to SkaldVaultV1VaultClearWipeFailureReason.RawEntropyInputRejected,
            rawRecordFixture to SkaldVaultV1VaultClearWipeFailureReason.RawRecordInputRejected,
            "provider-handle-fixture" to SkaldVaultV1VaultClearWipeFailureReason.ProviderHandleInputRejected,
            "storage-handle-fixture" to SkaldVaultV1VaultClearWipeFailureReason.StorageHandleInputRejected,
            rawBufferFixture to SkaldVaultV1VaultClearWipeFailureReason.MutableBufferInputRejected,
            "chararray-fixture" to SkaldVaultV1VaultClearWipeFailureReason.MutableBufferInputRejected,
            rawRootFixture to SkaldVaultV1VaultClearWipeFailureReason.RawAbsoluteLocationInputRejected,
            "relative/path" to SkaldVaultV1VaultClearWipeFailureReason.RawRelativeLocationInputRejected,
            "file://skald-vault" to SkaldVaultV1VaultClearWipeFailureReason.LinkLikeInputRejected,
            "https://example.invalid/skald" to SkaldVaultV1VaultClearWipeFailureReason.LinkLikeInputRejected,
            "file-object-fixture" to SkaldVaultV1VaultClearWipeFailureReason.PlatformObjectLikeInputRejected,
            "path-object-fixture" to SkaldVaultV1VaultClearWipeFailureReason.PlatformObjectLikeInputRejected,
            "secret-fixture" to SkaldVaultV1VaultClearWipeFailureReason.SecretMaterialRejected,
            rawLongHexFixture to SkaldVaultV1VaultClearWipeFailureReason.TransactionLikeEvidenceRejected,
            bitcoinAddressLike to SkaldVaultV1VaultClearWipeFailureReason.BitcoinAddressLikeEvidenceRejected,
            nostrSecretLike to SkaldVaultV1VaultClearWipeFailureReason.WalletMaterialRejected,
            extendedPrivatePrefixFixture to SkaldVaultV1VaultClearWipeFailureReason.WalletMaterialRejected,
            testPrivatePrefixFixture to SkaldVaultV1VaultClearWipeFailureReason.WalletMaterialRejected,
            wifLike to SkaldVaultV1VaultClearWipeFailureReason.WalletMaterialRejected,
            "../vault" to SkaldVaultV1VaultClearWipeFailureReason.TraversalRejected,
            "unsupported#chars" to SkaldVaultV1VaultClearWipeFailureReason.UnsupportedCharactersRejected,
        )

        cases.forEach { (raw, expectedReason) ->
            val request = SkaldVaultV1VaultClearWipeRequest.rawClearWipeCandidate(raw)
            val rejected = rejected(SkaldVaultV1ClearWipeStrategyPolicy.evaluate(request))

            assertEquals(expectedReason, rejected.reason)
            assertTrue(request.rawCandidateRejected)
            if (!raw.isNullOrBlank()) {
                assertFalse(request.toString().contains(raw))
                assertFalse(rejected.toString().contains(raw))
            }
        }
    }

    @Test
    fun resultRenderingRedactsSensitiveLookingValuesAndDoesNotClaimZeroization() {
        val request = SkaldVaultV1VaultClearWipeRequest.forValueAndEvent(
            valueKind = SkaldVaultV1VaultClearWipeValueKind.ProviderRootKey,
            eventKind = SkaldVaultV1VaultClearWipeEventKind.MainnetRequestAttempted,
        )
        val evidence = blocked(SkaldVaultV1ClearWipeStrategyPolicy.evaluate(request))
        val forbiddenValues = listOf(
            rawSecretFixture,
            rawKeyFixture,
            rawBufferFixture,
            rawRootFixture,
            rawRecordFixture,
            rawLongHexFixture,
        )

        assertFalse(evidence.policyTokenEvidence.containsRawSensitiveValue)
        assertFalse(evidence.policyTokenEvidence.containsPassphraseMaterial)
        assertFalse(evidence.policyTokenEvidence.containsKeyMaterial)
        assertFalse(evidence.policyTokenEvidence.containsProviderKeyMaterial)
        assertFalse(evidence.policyTokenEvidence.containsRawBuffer)
        assertFalse(evidence.policyTokenEvidence.containsRootText)
        assertFalse(evidence.policyTokenEvidence.containsPlannedLocationText)
        assertFalse(evidence.policyTokenEvidence.containsRecordIdentifier)
        assertFalse(evidence.policyTokenEvidence.containsPayload)
        assertFalse(evidence.policyTokenEvidence.containsRawBytes)
        assertFalse(evidence.capability.actualClearImplemented)
        assertFalse(evidence.capability.actualZeroizationImplemented)
        assertFalse(evidence.capability.jvmZeroizationProven)
        forbiddenValues.forEach { raw ->
            assertRedacted(request.toString(), raw)
            assertRedacted(evidence.toString(), raw)
            assertRedacted(evidence.policyTokenEvidence.toString(), raw)
            evidence.blockers.forEach { blocker -> assertRedacted(blocker.label, raw) }
            evidence.warnings.forEach { warning -> assertRedacted(warning.label, raw) }
        }
    }

    @Test
    fun allCurrentCapabilitiesRemainFalse() {
        val capability = blocked(
            SkaldVaultV1ClearWipeStrategyPolicy.evaluate(
                SkaldVaultV1VaultClearWipeRequest.summary(),
            ),
        ).capability

        assertDisabledCapabilities(capability)
    }

    @Test
    fun readinessProviderAcceptanceDependencyAndSelectionRecordBoundaryButRemainDisabled() {
        val contract = commonProductionProviderAcceptanceContract()
        val storage = contract.containerManifestStorageContract
        val acceptanceEvidence = ProductionProviderAcceptanceEvidence.currentDesignOnly()
        val assessment = contract.assess()
        val readiness = EncryptedVaultReadinessPolicy.disabled()
        val dependency = dependencyEvidence()
        val providerSelection = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(SkaldVaultV1ClearWipeStrategyPolicy.POLICY_ID, storage.clearWipeStrategyBoundaryPolicyId)
        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            storage.clearWipeStrategyBoundaryStatus,
        )
        assertEquals(
            ProductionProviderClearWipeStrategyBoundaryRule.entries.toSet(),
            storage.clearWipeStrategyBoundaryRules,
        )
        assertTrue(storage.clearWipeStrategyBoundaryModeled)
        assertTrue(storage.clearWipeStrategyStillDisabled)
        assertTrue(storage.clearWipeStrategyDoesNotAcceptRawSensitiveValues)
        assertTrue(storage.clearWipeStrategyDoesNotClearRealMemory)
        assertTrue(storage.clearWipeStrategyDoesNotProveJvmZeroization)
        assertTrue(storage.clearWipeStrategyDoesNotEnableUnlock)
        assertTrue(storage.clearWipeStrategyDoesNotEnablePersistence)
        assertTrue(storage.clearWipeStrategyDoesNotEnableProviderSelection)
        assertTrue(storage.clearWipeFailureVocabularyModeled)
        assertFalse(storage.vaultPersistenceImplemented)
        assertFalse(storage.secureSecretStorageSuccessPathImplemented)

        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            acceptanceEvidence.stateFor(ProductionProviderAcceptanceGate.ClearWipeStrategyBoundaryImplementedAndTested),
        )
        assertFalse(assessment.productionProviderSelectable)
        assertFalse(assessment.productionPersistenceAllowed)

        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[EncryptedVaultRequirement.ClearWipeStrategyBoundaryImplementedAndTested],
        )
        assertContains(readiness.capabilities, EncryptedVaultCapability.ClearWipeStrategyBoundaryBuildingBlock)
        assertContains(readiness.blockers, EncryptedVaultBlockingIssue.ClearWipeStrategyBoundaryStillDisabled)
        assertFalse(readiness.productionPersistenceEnabled)

        assertContains(dependency.capabilities, VaultCryptoDependencyCapability.ClearWipeStrategyBoundaryImplementedTested)
        assertContains(dependency.capabilities, VaultCryptoDependencyCapability.ClearWipeFailureVocabularyModeled)
        assertContains(dependency.blockers, VaultCryptoDependencyBlocker.ClearWipeStrategyBoundaryStillDisabled)
        assertContains(dependency.blockers, VaultCryptoDependencyBlocker.ClearWipeStrategyRuntimeReviewMissing)
        assertFalse(dependency.productionPersistenceEnabled)

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, providerSelection.selectedCandidateId)
        assertTrue(providerSelection.selectedProviderIsDisabled)
        assertFalse(providerSelection.productionProviderSelectable)
    }

    private fun passphraseEvidence(): SkaldVaultV1VaultPassphrasePolicyEvidence =
        assertIs<
            SkaldVaultV1VaultPassphrasePolicyResult.Blocked<SkaldVaultV1VaultPassphrasePolicyEvidence>,
        >(
            SkaldVaultV1PassphrasePolicyGate.evaluate(
                SkaldVaultV1VaultPassphrasePolicyRequest.summary(),
            ),
        ).value

    private fun redactionEvidence(): SkaldVaultV1VaultRedactionEvidence =
        assertIs<SkaldVaultV1VaultRedactionResult.Classified<SkaldVaultV1VaultRedactionEvidence>>(
            SkaldVaultV1RedactionLeakagePolicy.evaluate(
                SkaldVaultV1VaultRedactionRequest.classify(SkaldVaultV1VaultRedactionValueKind.Passphrase),
            ),
        ).value

    private fun lockEvidence(): SkaldVaultV1VaultLockSessionEvidence =
        assertIs<SkaldVaultV1VaultLockSessionResult.Blocked<SkaldVaultV1VaultLockSessionEvidence>>(
            SkaldVaultV1LockSessionLifecyclePolicy.evaluate(
                SkaldVaultV1VaultLockSessionRequest.noEvidence(),
            ),
        ).value

    private fun persistenceEvidence(): SkaldVaultV1VaultPersistenceReadinessEvidence =
        assertIs<
            SkaldVaultV1VaultPersistenceReadinessResult.Blocked<SkaldVaultV1VaultPersistenceReadinessEvidence>,
        >(
            SkaldVaultV1PersistenceReadinessGate.evaluate(
                SkaldVaultV1VaultPersistenceReadinessRequest.noEvidence(),
            ),
        ).value

    private fun storageEvidence(): SkaldVaultV1VaultStorageDisabledEvidence =
        assertIs<SkaldVaultV1VaultStorageOperationResult.Disabled<SkaldVaultV1VaultStorageDisabledEvidence>>(
            SkaldVaultV1DisabledStorageServiceFacade.perform(
                SkaldVaultV1VaultStorageOperationRequest.noEvidence(
                    SkaldVaultV1VaultStorageOperation.ReadRecord,
                ),
            ),
        ).value

    private fun dependencyEvidence() =
        VaultCryptoDependencyProbeCatalog.currentSpikeResults()
            .single { it.candidate == VaultCryptoDependencyCandidate.TinkBouncyCastleSplit }

    private fun blocked(
        result: SkaldVaultV1VaultClearWipeResult<SkaldVaultV1VaultClearWipeEvidence>,
    ): SkaldVaultV1VaultClearWipeEvidence =
        assertIs<SkaldVaultV1VaultClearWipeResult.Blocked<SkaldVaultV1VaultClearWipeEvidence>>(result).value

    private fun rejected(
        result: SkaldVaultV1VaultClearWipeResult<*>,
    ): SkaldVaultV1VaultClearWipeResult.Rejected =
        assertIs<SkaldVaultV1VaultClearWipeResult.Rejected>(result)

    private fun assertDisabledCapabilities(capability: SkaldVaultV1VaultClearWipeCapability) {
        assertFalse(capability.actualClearImplemented)
        assertFalse(capability.actualZeroizationImplemented)
        assertFalse(capability.jvmZeroizationProven)
        assertFalse(capability.nativeZeroizationAvailable)
        assertFalse(capability.providerClearAvailable)
        assertFalse(capability.storageClearAvailable)
        assertFalse(capability.sessionInvalidationAvailable)
        assertFalse(capability.passphraseClearAvailable)
        assertFalse(capability.keyMaterialClearAvailable)
        assertFalse(capability.decryptedRecordClearAvailable)
        assertFalse(capability.diagnosticBufferClearAvailable)
        assertFalse(capability.unlockAvailable)
        assertFalse(capability.activeSessionAvailable)
        assertFalse(capability.decryptedKeyMaterialPresent)
        assertFalse(capability.providerSelectable)
        assertFalse(capability.productionProviderSelected)
        assertFalse(capability.providerCryptoAvailable)
        assertFalse(capability.vaultCreationAvailable)
        assertFalse(capability.vaultUnlockAvailable)
        assertFalse(capability.vaultPersistenceAvailable)
        assertFalse(capability.secureSecretStorageAvailable)
        assertFalse(capability.secureMetadataStorageAvailable)
        assertFalse(capability.mainnetAvailable)
    }

    private fun assertRedacted(rendered: String, raw: String) {
        assertFalse(rendered.contains(raw), "clear/wipe rendering leaked raw fixture: $rendered")
    }
}
