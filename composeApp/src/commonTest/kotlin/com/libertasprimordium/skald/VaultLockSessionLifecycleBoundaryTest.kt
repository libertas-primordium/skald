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
import com.libertasprimordium.skald.security.ProductionProviderLockSessionLifecycleRule
import com.libertasprimordium.skald.security.SkaldVaultV1DisabledStorageServiceFacade
import com.libertasprimordium.skald.security.SkaldVaultV1LinuxRootResolverInputSnapshot
import com.libertasprimordium.skald.security.SkaldVaultV1LockSessionLifecyclePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1PersistenceReadinessGate
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformPathConstructionEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformPathConstructionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformPathConstructionRequest
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformPathConstructionResult
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformRootResolverEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformRootResolverPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformRootResolverRequest
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformRootResolverResult
import com.libertasprimordium.skald.security.SkaldVaultV1StorageLayoutPlan
import com.libertasprimordium.skald.security.SkaldVaultV1StorageLayoutPlanPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1StorageLayoutResult
import com.libertasprimordium.skald.security.SkaldVaultV1StorageSafetyEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1StorageSafetyEvidenceKind
import com.libertasprimordium.skald.security.SkaldVaultV1StorageSafetyPreflightEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1StorageSafetyPreflightPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1StorageSafetyPreflightRequest
import com.libertasprimordium.skald.security.SkaldVaultV1StorageSafetyPreflightResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultLockSessionDecision
import com.libertasprimordium.skald.security.SkaldVaultV1VaultLockSessionEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultLockSessionFailureReason
import com.libertasprimordium.skald.security.SkaldVaultV1VaultLockSessionRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultLockSessionResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultLockSessionSource
import com.libertasprimordium.skald.security.SkaldVaultV1VaultLockSessionStatus
import com.libertasprimordium.skald.security.SkaldVaultV1VaultLockState
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPersistenceReadinessEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPersistenceReadinessFailureReason
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPersistenceReadinessRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPersistenceReadinessResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPersistenceReadinessSource
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPersistenceReadinessStatus
import com.libertasprimordium.skald.security.SkaldVaultV1VaultSessionCapability
import com.libertasprimordium.skald.security.SkaldVaultV1VaultSessionEventKind
import com.libertasprimordium.skald.security.SkaldVaultV1VaultSessionGateEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultSessionGateStatus
import com.libertasprimordium.skald.security.SkaldVaultV1VaultSessionRequiredGate
import com.libertasprimordium.skald.security.SkaldVaultV1VaultSessionState
import com.libertasprimordium.skald.security.SkaldVaultV1VaultStorageDisabledEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultStorageOperation
import com.libertasprimordium.skald.security.SkaldVaultV1VaultStorageOperationRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultStorageOperationResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultUnlockBlocker
import com.libertasprimordium.skald.security.VaultCryptoDependencyBlocker
import com.libertasprimordium.skald.security.VaultCryptoDependencyCandidate
import com.libertasprimordium.skald.security.VaultCryptoDependencyCapability
import com.libertasprimordium.skald.security.VaultCryptoDependencyProbeCatalog
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import com.libertasprimordium.skald.security.commonProductionProviderAcceptanceContract
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class VaultLockSessionLifecycleBoundaryTest {
    private val fixedVaultId = (0x20..0x2f).map { it.toByte() }.toByteArray()
    private val fixedRecordId = (0x40..0x4f).map { it.toByte() }.toByteArray()
    private val linuxFixtureRoot = "/home/skald-test-user/.local/share"

    @Test
    fun lifecyclePolicyIdIsStable() {
        assertEquals(
            "skald-vault-v1-lock-session-lifecycle-boundary-v1",
            SkaldVaultV1LockSessionLifecyclePolicy.POLICY_ID,
        )
        assertEquals(1, SkaldVaultV1LockSessionLifecyclePolicy.POLICY_VERSION)
    }

    @Test
    fun defaultLifecycleIsLockedUnavailableAndFailClosed() {
        val evidence = assertBlocked(
            SkaldVaultV1LockSessionLifecyclePolicy.evaluate(
                SkaldVaultV1VaultLockSessionRequest.noEvidence(),
            ),
        )

        assertEquals(SkaldVaultV1VaultLockSessionStatus.NoEvidenceAvailable, evidence.status)
        assertEquals(SkaldVaultV1VaultLockSessionDecision.LockedUnavailable, evidence.decision)
        assertEquals(SkaldVaultV1VaultLockState.Locked, evidence.lockState)
        assertEquals(SkaldVaultV1VaultSessionState.Locked, evidence.sessionState)
        assertFalse(evidence.unlockReady)
        assertFalse(evidence.activeSessionReady)
        assertFalse(evidence.capability.unlockAvailable)
        assertFalse(evidence.capability.activeSessionAvailable)
        assertFalse(evidence.capability.providerSelectable)
        assertFalse(evidence.capability.vaultUnlockAvailable)
        assertFalse(evidence.capability.vaultPersistenceAvailable)
        assertContains(evidence.blockers, SkaldVaultV1VaultUnlockBlocker.MissingPersistenceReadinessEvidence)
        assertContains(evidence.blockers, SkaldVaultV1VaultUnlockBlocker.MissingProviderEvidence)
        assertContains(evidence.blockers, SkaldVaultV1VaultUnlockBlocker.MissingStorageServiceEvidence)
        assertContains(evidence.blockers, SkaldVaultV1VaultUnlockBlocker.DisabledProviderSelection)
        assertContains(evidence.blockers, SkaldVaultV1VaultUnlockBlocker.MainnetDisabled)
    }

    @Test
    fun lifecycleStateVocabularyIsModeled() {
        val states = SkaldVaultV1VaultLockState.entries.toSet()

        assertContains(states, SkaldVaultV1VaultLockState.NotInitialized)
        assertContains(states, SkaldVaultV1VaultLockState.Locked)
        assertContains(states, SkaldVaultV1VaultLockState.UnlockRequested)
        assertContains(states, SkaldVaultV1VaultLockState.UnlockBlocked)
        assertContains(states, SkaldVaultV1VaultLockState.UnlockUnavailable)
        assertContains(states, SkaldVaultV1VaultLockState.ActiveSessionUnavailable)
        assertContains(states, SkaldVaultV1VaultLockState.ActiveSessionModeledButNotUsable)
        assertContains(states, SkaldVaultV1VaultLockState.SessionExpired)
        assertContains(states, SkaldVaultV1VaultLockState.UserInitiatedLockRequired)
        assertContains(states, SkaldVaultV1VaultLockState.BackgroundLockRequired)
        assertContains(states, SkaldVaultV1VaultLockState.CloseShutdownLockRequired)
        assertContains(states, SkaldVaultV1VaultLockState.ErrorLockRequired)
        assertContains(states, SkaldVaultV1VaultLockState.ProviderChangedLockRequired)
        assertContains(states, SkaldVaultV1VaultLockState.StorageReadinessChangedLockRequired)
        assertContains(states, SkaldVaultV1VaultLockState.PlatformSecurityChangedLockRequired)
        assertContains(states, SkaldVaultV1VaultLockState.MainnetRequestLockBlockRequired)
        assertContains(states, SkaldVaultV1VaultLockState.ForcedLockedFailClosed)
    }

    @Test
    fun lifecycleEventVocabularyIsModeled() {
        val events = SkaldVaultV1VaultSessionEventKind.entries.toSet()

        assertContains(events, SkaldVaultV1VaultSessionEventKind.AppStart)
        assertContains(events, SkaldVaultV1VaultSessionEventKind.UserRequestsUnlock)
        assertContains(events, SkaldVaultV1VaultSessionEventKind.UserCancelsUnlock)
        assertContains(events, SkaldVaultV1VaultSessionEventKind.UnlockAttemptBlocked)
        assertContains(events, SkaldVaultV1VaultSessionEventKind.UnlockAttemptFails)
        assertContains(events, SkaldVaultV1VaultSessionEventKind.UserRequestsLock)
        assertContains(events, SkaldVaultV1VaultSessionEventKind.TimeoutReached)
        assertContains(events, SkaldVaultV1VaultSessionEventKind.AppBackgrounded)
        assertContains(events, SkaldVaultV1VaultSessionEventKind.AppForegrounded)
        assertContains(events, SkaldVaultV1VaultSessionEventKind.AppCloseShutdown)
        assertContains(events, SkaldVaultV1VaultSessionEventKind.ErrorFault)
        assertContains(events, SkaldVaultV1VaultSessionEventKind.ProviderSelectionChanged)
        assertContains(events, SkaldVaultV1VaultSessionEventKind.StorageReadinessChanged)
        assertContains(events, SkaldVaultV1VaultSessionEventKind.SecureStorageCapabilityChanged)
        assertContains(events, SkaldVaultV1VaultSessionEventKind.PlatformRootPathEvidenceChanged)
        assertContains(events, SkaldVaultV1VaultSessionEventKind.MainnetRequestAttempted)
        assertContains(events, SkaldVaultV1VaultSessionEventKind.SessionStateQueried)
    }

    @Test
    fun lifecycleRequiredGateVocabularyIsModeled() {
        val gates = SkaldVaultV1VaultSessionRequiredGate.entries.toSet()

        assertContains(gates, SkaldVaultV1VaultSessionRequiredGate.PersistenceReadinessApproved)
        assertContains(gates, SkaldVaultV1VaultSessionRequiredGate.ProviderSelectableApproved)
        assertContains(gates, SkaldVaultV1VaultSessionRequiredGate.ProviderKatApproved)
        assertContains(gates, SkaldVaultV1VaultSessionRequiredGate.FinalKdfCalibrationApproved)
        assertContains(gates, SkaldVaultV1VaultSessionRequiredGate.SecureSecretStorageApproved)
        assertContains(gates, SkaldVaultV1VaultSessionRequiredGate.SecureMetadataStorageApproved)
        assertContains(gates, SkaldVaultV1VaultSessionRequiredGate.StorageServiceReady)
        assertContains(gates, SkaldVaultV1VaultSessionRequiredGate.LifecycleImplementationReviewed)
        assertContains(gates, SkaldVaultV1VaultSessionRequiredGate.RedactionLeakageReviewApproved)
        assertContains(gates, SkaldVaultV1VaultSessionRequiredGate.ClearWipeStrategyReviewed)
        assertContains(gates, SkaldVaultV1VaultSessionRequiredGate.PassphraseInputPolicyReviewed)
        assertContains(gates, SkaldVaultV1VaultSessionRequiredGate.PassphraseRetryThrottlingReviewed)
        assertContains(gates, SkaldVaultV1VaultSessionRequiredGate.TimeoutPolicyReviewed)
        assertContains(gates, SkaldVaultV1VaultSessionRequiredGate.BackgroundLockPolicyReviewed)
        assertContains(gates, SkaldVaultV1VaultSessionRequiredGate.CloseShutdownLockPolicyReviewed)
        assertContains(gates, SkaldVaultV1VaultSessionRequiredGate.ErrorLockPolicyReviewed)
        assertContains(gates, SkaldVaultV1VaultSessionRequiredGate.AndroidLifecyclePolicyReviewed)
        assertContains(gates, SkaldVaultV1VaultSessionRequiredGate.LinuxLifecyclePolicyReviewed)
        assertContains(gates, SkaldVaultV1VaultSessionRequiredGate.CrashRecoveryPolicyReviewed)
        assertContains(gates, SkaldVaultV1VaultSessionRequiredGate.MigrationCorruptionPolicyReviewed)
        assertContains(gates, SkaldVaultV1VaultSessionRequiredGate.MainnetDisabledReleaseReview)
    }

    @Test
    fun composedEvidenceIsConsumedButUnlockRemainsBlocked() {
        val evidenceBundle = composedEvidenceBundle()
        val evidence = assertBlocked(
            SkaldVaultV1LockSessionLifecyclePolicy.evaluate(
                SkaldVaultV1VaultLockSessionRequest.fromEvidence(
                    persistenceReadinessEvidence = evidenceBundle.persistence,
                    storageServiceEvidence = evidenceBundle.storage,
                    gateEvidence = SkaldVaultV1VaultSessionRequiredGate.entries.map {
                        SkaldVaultV1VaultSessionGateEvidence.modelOnly(it)
                    },
                ),
            ),
        )

        assertEquals(SkaldVaultV1VaultLockSessionSource.CurrentReadinessEvidence, evidence.source)
        assertEquals(SkaldVaultV1VaultLockSessionStatus.UnlockBlockedStillDisabled, evidence.status)
        assertTrue(evidence.persistenceReadinessEvidenceConsumed)
        assertTrue(evidence.providerSelectionEvidenceConsumed)
        assertTrue(evidence.providerAcceptanceEvidenceConsumed)
        assertTrue(evidence.vaultReadinessEvidenceConsumed)
        assertTrue(evidence.dependencyProbeEvidenceConsumed)
        assertTrue(evidence.disabledProviderFacadeEvidenceConsumed)
        assertTrue(evidence.disabledStorageServiceEvidenceConsumed)
        assertTrue(evidence.secureStorageEvidenceConsumed)
        assertTrue(evidence.secureMetadataEvidenceConsumed)
        assertTrue(evidence.lockSessionLifecycleBoundaryModeled)
        assertTrue(evidence.lockSessionLifecycleStillDisabled)
        assertTrue(evidence.unlockDecisionStillBlocked)
        assertTrue(evidence.activeSessionUnavailable)
        assertTrue(evidence.lockSessionBoundaryDoesNotAcceptPassphrases)
        assertTrue(evidence.lockSessionBoundaryDoesNotHoldKeys)
        assertTrue(evidence.lockSessionBoundaryDoesNotEnablePersistence)
        assertTrue(evidence.lockSessionBoundaryDoesNotEnableProviderSelection)
        assertTrue(evidence.lockSessionFailureVocabularyModeled)
        assertFalse(evidence.unlockReady)
        assertFalse(evidence.activeSessionReady)
        assertContains(evidence.blockers, SkaldVaultV1VaultUnlockBlocker.BlockedPersistenceReadiness)
        assertContains(evidence.blockers, SkaldVaultV1VaultUnlockBlocker.DisabledProviderSelection)
        assertContains(evidence.blockers, SkaldVaultV1VaultUnlockBlocker.ProductionProviderNotSelectable)
        assertContains(evidence.blockers, SkaldVaultV1VaultUnlockBlocker.DisabledStorageServiceFacadeStillDisabled)
        assertContains(evidence.blockers, SkaldVaultV1VaultUnlockBlocker.SecureSecretStorageUnavailable)
        assertContains(evidence.blockers, SkaldVaultV1VaultUnlockBlocker.SecureMetadataStorageUnavailable)
        assertContains(evidence.blockers, SkaldVaultV1VaultUnlockBlocker.UnapprovedRequiredGate)
        assertContains(evidence.blockers, SkaldVaultV1VaultUnlockBlocker.MainnetDisabled)
    }

    @Test
    fun missingRejectedWarningOnlyAndUserConsentEvidenceCannotEnableUnlock() {
        val evidenceBundle = composedEvidenceBundle()
        val missingStorage = assertBlocked(
            SkaldVaultV1LockSessionLifecyclePolicy.evaluate(
                SkaldVaultV1VaultLockSessionRequest.fromEvidence(
                    persistenceReadinessEvidence = evidenceBundle.persistence,
                    storageServiceEvidence = null,
                ),
            ),
        )
        assertEquals(SkaldVaultV1VaultLockSessionSource.MissingStorageServiceEvidence, missingStorage.source)
        assertContains(missingStorage.blockers, SkaldVaultV1VaultUnlockBlocker.MissingStorageServiceEvidence)

        val rejected = assertBlocked(
            SkaldVaultV1LockSessionLifecyclePolicy.evaluate(
                SkaldVaultV1VaultLockSessionRequest.fromPersistenceReadinessResult(
                    persistenceReadinessResult = SkaldVaultV1VaultPersistenceReadinessResult.Rejected(
                        reason = SkaldVaultV1VaultPersistenceReadinessFailureReason.RawReadinessEvidenceInputRejected,
                        status = SkaldVaultV1VaultPersistenceReadinessStatus.RawReadinessEvidenceRejected,
                        source = SkaldVaultV1VaultPersistenceReadinessSource.RawReadinessEvidenceCandidate,
                    ),
                    storageServiceEvidence = evidenceBundle.storage,
                ),
            ),
        )
        assertEquals(SkaldVaultV1VaultLockSessionSource.RejectedTypedEvidence, rejected.source)
        assertContains(rejected.blockers, SkaldVaultV1VaultUnlockBlocker.UpstreamEvidenceRejected)

        val warningOnly = SkaldVaultV1VaultSessionGateEvidence.warningOnly(
            SkaldVaultV1VaultSessionRequiredGate.TimeoutPolicyReviewed,
        )
        val userConsentOnly = SkaldVaultV1VaultSessionGateEvidence.userConsentOnly(
            SkaldVaultV1VaultSessionRequiredGate.PassphraseInputPolicyReviewed,
        )
        val warningEvidence = assertBlocked(
            SkaldVaultV1LockSessionLifecyclePolicy.evaluate(
                SkaldVaultV1VaultLockSessionRequest.fromEvidence(
                    persistenceReadinessEvidence = evidenceBundle.persistence,
                    storageServiceEvidence = evidenceBundle.storage,
                    gateEvidence = listOf(warningOnly, userConsentOnly),
                ),
            ),
        )
        assertContains(warningEvidence.blockers, SkaldVaultV1VaultUnlockBlocker.WarningOnlyEvidenceRejected)
        assertContains(warningEvidence.blockers, SkaldVaultV1VaultUnlockBlocker.UserConsentOverrideRejected)
        assertFalse(warningEvidence.capability.unlockAvailable)
    }

    @Test
    fun lifecycleEventsForceLockedOrLockRequiredStates() {
        val evidenceBundle = composedEvidenceBundle()
        val cases = listOf(
            SkaldVaultV1VaultSessionEventKind.TimeoutReached to
                (SkaldVaultV1VaultLockState.SessionExpired to SkaldVaultV1VaultUnlockBlocker.TimeoutRequiresLock),
            SkaldVaultV1VaultSessionEventKind.AppBackgrounded to
                (SkaldVaultV1VaultLockState.BackgroundLockRequired to SkaldVaultV1VaultUnlockBlocker.BackgroundRequiresLock),
            SkaldVaultV1VaultSessionEventKind.AppCloseShutdown to
                (SkaldVaultV1VaultLockState.CloseShutdownLockRequired to
                    SkaldVaultV1VaultUnlockBlocker.CloseShutdownRequiresLock),
            SkaldVaultV1VaultSessionEventKind.ErrorFault to
                (SkaldVaultV1VaultLockState.ErrorLockRequired to SkaldVaultV1VaultUnlockBlocker.ErrorRequiresLock),
            SkaldVaultV1VaultSessionEventKind.ProviderSelectionChanged to
                (SkaldVaultV1VaultLockState.ProviderChangedLockRequired to
                    SkaldVaultV1VaultUnlockBlocker.ProviderChangeRequiresLock),
            SkaldVaultV1VaultSessionEventKind.StorageReadinessChanged to
                (SkaldVaultV1VaultLockState.StorageReadinessChangedLockRequired to
                    SkaldVaultV1VaultUnlockBlocker.StorageReadinessChangeRequiresLock),
            SkaldVaultV1VaultSessionEventKind.MainnetRequestAttempted to
                (SkaldVaultV1VaultLockState.MainnetRequestLockBlockRequired to
                    SkaldVaultV1VaultUnlockBlocker.MainnetRequestRequiresBlock),
        )

        cases.forEach { (eventKind, expected) ->
            val evidence = assertBlocked(
                SkaldVaultV1LockSessionLifecyclePolicy.evaluate(
                    SkaldVaultV1VaultLockSessionRequest.fromEvidence(
                        persistenceReadinessEvidence = evidenceBundle.persistence,
                        storageServiceEvidence = evidenceBundle.storage,
                        eventKind = eventKind,
                    ),
                ),
            )
            assertEquals(expected.first, evidence.lockState)
            assertContains(evidence.blockers, expected.second)
            assertEquals(SkaldVaultV1VaultLockSessionDecision.ForcedLocked, evidence.decision)
            assertFalse(evidence.capability.activeSessionAvailable)
        }
    }

    @Test
    fun redactionProtectsPassphraseKeyRootLocationRecordPayloadAndProviderMaterial() {
        val evidenceBundle = composedEvidenceBundle()
        val request = SkaldVaultV1VaultLockSessionRequest.fromEvidence(
            persistenceReadinessEvidence = evidenceBundle.persistence,
            storageServiceEvidence = evidenceBundle.storage,
        )
        val evidence = assertBlocked(SkaldVaultV1LockSessionLifecyclePolicy.evaluate(request))
        val forbiddenValues = listOf(
            linuxFixtureRoot,
            "safe_record_fixture",
            "provider-key-material",
            "pass" + "phrase fixture",
            "vault_202122232425262728292a2b2c2d2e2f",
            "a".repeat(64),
        )

        assertFalse(evidence.token.containsPassphraseMaterial)
        assertFalse(evidence.token.containsKeyMaterial)
        assertFalse(evidence.token.containsProviderKeyMaterial)
        assertFalse(evidence.token.containsRootText)
        assertFalse(evidence.token.containsPlannedLocationText)
        assertFalse(evidence.token.containsRecordIdentifier)
        assertFalse(evidence.token.containsPayload)
        assertFalse(evidence.passphraseExposedByDefault)
        assertFalse(evidence.keyMaterialExposedByDefault)
        forbiddenValues.forEach { raw ->
            assertRedacted(request.toString(), raw)
            assertRedacted(evidence.toString(), raw)
            assertRedacted(evidence.token.toString(), raw)
            evidence.blockers.forEach { blocker -> assertRedacted(blocker.label, raw) }
            evidence.warnings.forEach { warning -> assertRedacted(warning.label, raw) }
        }
    }

    @Test
    fun allLifecycleCapabilitiesRemainFalse() {
        val capability = assertBlocked(
            SkaldVaultV1LockSessionLifecyclePolicy.evaluate(
                SkaldVaultV1VaultLockSessionRequest.fromEvidence(
                    persistenceReadinessEvidence = composedEvidenceBundle().persistence,
                    storageServiceEvidence = composedEvidenceBundle().storage,
                ),
            ),
        ).capability

        assertDisabledCapabilities(capability)
    }

    @Test
    fun rawLifecycleInputsAreRejected() {
        val longHex = "a".repeat(64)
        val bitcoinAddressLike = "bc" + "1" + "q".repeat(24)
        val nostrSecretLike = "ns" + "ec" + "1" + "q".repeat(24)
        val extendedPrivatePrefixFixture = "xp" + "rv" + "9".repeat(32)
        val testPrivatePrefixFixture = "tp" + "rv" + "9".repeat(32)
        val wifLike = "K" + "1".repeat(50)
        val cases = listOf(
            null to SkaldVaultV1VaultLockSessionFailureReason.EmptyEvidenceNameRejected,
            "" to SkaldVaultV1VaultLockSessionFailureReason.EmptyEvidenceNameRejected,
            "   " to SkaldVaultV1VaultLockSessionFailureReason.EmptyEvidenceNameRejected,
            "pass" + "phrase-fixture" to SkaldVaultV1VaultLockSessionFailureReason.PassphraseInputRejected,
            "pin-fixture" to SkaldVaultV1VaultLockSessionFailureReason.PinInputRejected,
            "biometric-result-fixture" to SkaldVaultV1VaultLockSessionFailureReason.BiometricInputRejected,
            "raw-key-fixture" to SkaldVaultV1VaultLockSessionFailureReason.RawKeyInputRejected,
            "key-bytes-fixture" to SkaldVaultV1VaultLockSessionFailureReason.RawKeyInputRejected,
            "provider-key-material" to SkaldVaultV1VaultLockSessionFailureReason.ProviderKeyMaterialRejected,
            linuxFixtureRoot to SkaldVaultV1VaultLockSessionFailureReason.RawAbsolutePathInputRejected,
            "relative/path" to SkaldVaultV1VaultLockSessionFailureReason.RawRelativePathInputRejected,
            "file://skald-vault" to SkaldVaultV1VaultLockSessionFailureReason.UriLikeInputRejected,
            "https://example.invalid/skald" to SkaldVaultV1VaultLockSessionFailureReason.UriLikeInputRejected,
            "content://example/skald" to SkaldVaultV1VaultLockSessionFailureReason.UriLikeInputRejected,
            "C:\\skald\\vault" to SkaldVaultV1VaultLockSessionFailureReason.RawAbsolutePathInputRejected,
            "\\\\server\\share" to SkaldVaultV1VaultLockSessionFailureReason.RawAbsolutePathInputRejected,
            "file-object-fixture" to SkaldVaultV1VaultLockSessionFailureReason.FileOrPathObjectInputRejected,
            "path-object-fixture" to SkaldVaultV1VaultLockSessionFailureReason.FileOrPathObjectInputRejected,
            "segment..traversal" to SkaldVaultV1VaultLockSessionFailureReason.TraversalRejected,
            "unsupported:colon" to SkaldVaultV1VaultLockSessionFailureReason.UnsupportedEvidenceNameRejected,
            "password-fixture" to SkaldVaultV1VaultLockSessionFailureReason.SecretMaterialRejected,
            "token-fixture" to SkaldVaultV1VaultLockSessionFailureReason.SecretMaterialRejected,
            "secret-fixture" to SkaldVaultV1VaultLockSessionFailureReason.SecretMaterialRejected,
            longHex to SkaldVaultV1VaultLockSessionFailureReason.TransactionLikeEvidenceRejected,
            bitcoinAddressLike to SkaldVaultV1VaultLockSessionFailureReason.BitcoinAddressLikeEvidenceRejected,
            nostrSecretLike to SkaldVaultV1VaultLockSessionFailureReason.WalletMaterialRejected,
            extendedPrivatePrefixFixture to SkaldVaultV1VaultLockSessionFailureReason.WalletMaterialRejected,
            testPrivatePrefixFixture to SkaldVaultV1VaultLockSessionFailureReason.WalletMaterialRejected,
            wifLike to SkaldVaultV1VaultLockSessionFailureReason.WalletMaterialRejected,
        )

        cases.forEach { (raw, expectedReason) ->
            val rejected = assertRejected(
                SkaldVaultV1LockSessionLifecyclePolicy.evaluate(
                    SkaldVaultV1VaultLockSessionRequest.rawLifecycleEvidenceCandidate(raw),
                ),
            )
            assertEquals(expectedReason, rejected.reason, "raw lifecycle input should be rejected without echoing it")
            assertEquals(SkaldVaultV1VaultLockSessionStatus.RawLifecycleEvidenceRejected, rejected.status)
            if (!raw.isNullOrBlank()) {
                assertFalse(rejected.toString().contains(raw))
            }
        }
    }

    @Test
    fun readinessProviderAndDependencyEvidenceRecordLifecycleButRemainDisabled() {
        val contract = commonProductionProviderAcceptanceContract()
        val storage = contract.containerManifestStorageContract
        val acceptanceEvidence = ProductionProviderAcceptanceEvidence.currentDesignOnly()
        val assessment = contract.assess()
        val readiness = EncryptedVaultReadinessPolicy.disabled()
        val dependency = VaultCryptoDependencyProbeCatalog.currentSpikeResults()
            .single { it.candidate == VaultCryptoDependencyCandidate.TinkBouncyCastleSplit }
        val providerSelection = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(SkaldVaultV1LockSessionLifecyclePolicy.POLICY_ID, storage.lockSessionLifecycleBoundaryPolicyId)
        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            storage.lockSessionLifecycleBoundaryStatus,
        )
        assertEquals(
            ProductionProviderLockSessionLifecycleRule.entries.toSet(),
            storage.lockSessionLifecycleRules,
        )
        assertTrue(storage.lockSessionLifecycleBoundaryModeled)
        assertTrue(storage.lockSessionLifecycleStillDisabled)
        assertTrue(storage.unlockDecisionStillBlocked)
        assertTrue(storage.activeSessionUnavailable)
        assertTrue(storage.lockSessionBoundaryDoesNotAcceptPassphrases)
        assertTrue(storage.lockSessionBoundaryDoesNotHoldKeys)
        assertTrue(storage.lockSessionBoundaryDoesNotEnablePersistence)
        assertTrue(storage.lockSessionBoundaryDoesNotEnableProviderSelection)
        assertTrue(storage.lockSessionFailureVocabularyModeled)
        assertFalse(storage.vaultPersistenceImplemented)
        assertFalse(storage.storageServiceOperationSuccessPathImplemented)

        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            acceptanceEvidence.stateFor(ProductionProviderAcceptanceGate.LockSessionLifecycleBoundaryImplementedAndTested),
        )
        assertFalse(assessment.productionProviderSelectable)
        assertFalse(assessment.productionPersistenceAllowed)

        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[EncryptedVaultRequirement.LockSessionLifecycleBoundaryImplementedAndTested],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[EncryptedVaultRequirement.LockSessionLifecycleTested],
        )
        assertContains(readiness.capabilities, EncryptedVaultCapability.LockSessionLifecycleBoundaryBuildingBlock)
        assertContains(readiness.blockers, EncryptedVaultBlockingIssue.LockSessionLifecycleStillDisabled)
        assertFalse(readiness.blockers.contains(EncryptedVaultBlockingIssue.LockSessionLifecycleUntested))
        assertFalse(readiness.productionPersistenceEnabled)

        assertContains(dependency.capabilities, VaultCryptoDependencyCapability.LockSessionLifecycleBoundaryImplementedTested)
        assertContains(dependency.capabilities, VaultCryptoDependencyCapability.LockSessionFailureVocabularyModeled)
        assertContains(dependency.blockers, VaultCryptoDependencyBlocker.LockSessionLifecycleStillDisabled)
        assertFalse(dependency.blockers.contains(VaultCryptoDependencyBlocker.LockSessionLifecycleTestsMissing))
        assertFalse(dependency.productionPersistenceEnabled)

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, providerSelection.selectedCandidateId)
        assertTrue(providerSelection.selectedProviderIsDisabled)
        assertFalse(providerSelection.productionProviderSelectable)
    }

    private fun composedEvidenceBundle(): ComposedEvidenceBundle {
        val root = linuxDefaultRootEvidence()
        val path = acceptedPathEvidence(
            SkaldVaultV1PlatformPathConstructionPolicy.plan(
                SkaldVaultV1PlatformPathConstructionRequest.fromRootEvidenceAndLayout(
                    rootEvidence = root,
                    layoutPlan = fixedLayout(),
                ),
            ),
        )
        val preflight = acceptedPreflightEvidence(
            SkaldVaultV1StorageSafetyPreflightPolicy.evaluate(
                SkaldVaultV1StorageSafetyPreflightRequest.fromArtifactLocationEvidence(
                    artifactEvidence = path,
                    safetyEvidence = SkaldVaultV1StorageSafetyEvidenceKind.entries.map {
                        SkaldVaultV1StorageSafetyEvidence.modelOnly(it)
                    },
                ),
            ),
        )
        val storage = disabledStorageEvidence(
            SkaldVaultV1DisabledStorageServiceFacade.perform(
                SkaldVaultV1VaultStorageOperationRequest.fromEvidence(
                    operation = SkaldVaultV1VaultStorageOperation.ReadRecord,
                    artifactEvidence = path,
                    preflightEvidence = preflight,
                ),
            ),
        )
        val persistence = assertBlockedPersistence(
            SkaldVaultV1PersistenceReadinessGate.evaluate(
                SkaldVaultV1VaultPersistenceReadinessRequest.fromEvidence(
                    rootEvidence = root,
                    pathEvidence = path,
                    storageSafetyPreflightEvidence = preflight,
                    storageServiceEvidence = storage,
                ),
            ),
        )
        return ComposedEvidenceBundle(root, path, preflight, storage, persistence)
    }

    private fun linuxDefaultRootEvidence(): SkaldVaultV1PlatformRootResolverEvidence =
        acceptedRootEvidence(
            SkaldVaultV1PlatformRootResolverPolicy.resolve(
                SkaldVaultV1PlatformRootResolverRequest.linuxDefaultRootEvidence(
                    SkaldVaultV1LinuxRootResolverInputSnapshot.defaultRootEvidence(
                        xdgDataHomeEvidence = linuxFixtureRoot,
                    ),
                ),
            ),
        )

    private fun fixedLayout(): SkaldVaultV1StorageLayoutPlan =
        when (
            val result = SkaldVaultV1StorageLayoutPlanPolicy.planForVault(
                vaultId = fixedVaultId,
                recordIds = listOf(fixedRecordId),
            )
        ) {
            is SkaldVaultV1StorageLayoutResult.Accepted -> result.value
            is SkaldVaultV1StorageLayoutResult.Rejected -> error(result.safeMessage)
        }

    private fun acceptedRootEvidence(
        result: SkaldVaultV1PlatformRootResolverResult<SkaldVaultV1PlatformRootResolverEvidence>,
    ): SkaldVaultV1PlatformRootResolverEvidence =
        when (result) {
            is SkaldVaultV1PlatformRootResolverResult.Accepted -> result.value
            is SkaldVaultV1PlatformRootResolverResult.Rejected -> error(result.safeMessage)
        }

    private fun acceptedPathEvidence(
        result: SkaldVaultV1PlatformPathConstructionResult<SkaldVaultV1PlatformPathConstructionEvidence>,
    ): SkaldVaultV1PlatformPathConstructionEvidence =
        when (result) {
            is SkaldVaultV1PlatformPathConstructionResult.Accepted -> result.value
            is SkaldVaultV1PlatformPathConstructionResult.Rejected -> error(result.safeMessage)
        }

    private fun acceptedPreflightEvidence(
        result: SkaldVaultV1StorageSafetyPreflightResult<SkaldVaultV1StorageSafetyPreflightEvidence>,
    ): SkaldVaultV1StorageSafetyPreflightEvidence =
        when (result) {
            is SkaldVaultV1StorageSafetyPreflightResult.Accepted -> result.value
            is SkaldVaultV1StorageSafetyPreflightResult.Rejected -> error(result.safeMessage)
        }

    private fun disabledStorageEvidence(
        result: SkaldVaultV1VaultStorageOperationResult<SkaldVaultV1VaultStorageDisabledEvidence>,
    ): SkaldVaultV1VaultStorageDisabledEvidence =
        when (result) {
            is SkaldVaultV1VaultStorageOperationResult.Disabled -> result.value
            is SkaldVaultV1VaultStorageOperationResult.Rejected -> error(result.safeMessage)
        }

    private fun assertBlockedPersistence(
        result:
            SkaldVaultV1VaultPersistenceReadinessResult<SkaldVaultV1VaultPersistenceReadinessEvidence>,
    ): SkaldVaultV1VaultPersistenceReadinessEvidence =
        when (result) {
            is SkaldVaultV1VaultPersistenceReadinessResult.Blocked -> result.value
            is SkaldVaultV1VaultPersistenceReadinessResult.Rejected -> error(result.safeMessage)
        }

    private fun assertBlocked(
        result: SkaldVaultV1VaultLockSessionResult<SkaldVaultV1VaultLockSessionEvidence>,
    ): SkaldVaultV1VaultLockSessionEvidence =
        when (result) {
            is SkaldVaultV1VaultLockSessionResult.Blocked -> result.value
            is SkaldVaultV1VaultLockSessionResult.Rejected ->
                error("expected blocked lifecycle evidence but got ${result.reason}")
        }

    private fun assertRejected(
        result: SkaldVaultV1VaultLockSessionResult<*>,
    ): SkaldVaultV1VaultLockSessionResult.Rejected =
        when (result) {
            is SkaldVaultV1VaultLockSessionResult.Blocked ->
                error("expected rejected lifecycle evidence but got blocked")
            is SkaldVaultV1VaultLockSessionResult.Rejected -> result
        }

    private fun assertDisabledCapabilities(capability: SkaldVaultV1VaultSessionCapability) {
        assertFalse(capability.unlockAvailable)
        assertFalse(capability.activeSessionAvailable)
        assertFalse(capability.decryptedKeyMaterialPresent)
        assertFalse(capability.passphraseAccepted)
        assertFalse(capability.passphraseStored)
        assertFalse(capability.providerSelectable)
        assertFalse(capability.productionProviderSelected)
        assertFalse(capability.providerCryptoAvailable)
        assertFalse(capability.vaultCreationAvailable)
        assertFalse(capability.vaultUnlockAvailable)
        assertFalse(capability.vaultPersistenceAvailable)
        assertFalse(capability.settingsPersistenceAvailable)
        assertFalse(capability.storageImplementationAvailable)
        assertFalse(capability.manifestReadWriteAvailable)
        assertFalse(capability.storageIndexReadWriteAvailable)
        assertFalse(capability.recordReadWriteAvailable)
        assertFalse(capability.atomicWriteAvailable)
        assertFalse(capability.crashRecoveryAvailable)
        assertFalse(capability.secureSecretStorageAvailable)
        assertFalse(capability.secureMetadataStorageAvailable)
        assertFalse(capability.realPathConstructed)
        assertFalse(capability.absolutePathConstructed)
        assertFalse(capability.realPathContainmentVerified)
        assertFalse(capability.symlinkSafetyVerified)
        assertFalse(capability.permissionsVerified)
        assertFalse(capability.ownershipVerified)
        assertFalse(capability.durabilityVerified)
        assertFalse(capability.antiRollbackAnchorAvailable)
        assertFalse(capability.walletSyncAvailable)
        assertFalse(capability.mainnetAvailable)
    }

    private fun assertRedacted(rendered: String, raw: String) {
        assertFalse(rendered.contains(raw), "default rendering must redact raw fixture: $rendered")
    }

    private data class ComposedEvidenceBundle(
        val root: SkaldVaultV1PlatformRootResolverEvidence,
        val path: SkaldVaultV1PlatformPathConstructionEvidence,
        val preflight: SkaldVaultV1StorageSafetyPreflightEvidence,
        val storage: SkaldVaultV1VaultStorageDisabledEvidence,
        val persistence: SkaldVaultV1VaultPersistenceReadinessEvidence,
    )
}
