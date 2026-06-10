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
import com.libertasprimordium.skald.security.ProductionProviderPersistenceReadinessGateRule
import com.libertasprimordium.skald.security.SkaldVaultV1DisabledStorageServiceFacade
import com.libertasprimordium.skald.security.SkaldVaultV1LinuxRootResolverInputSnapshot
import com.libertasprimordium.skald.security.SkaldVaultV1PersistenceReadinessGate
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformPathConstructionEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformPathConstructionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformPathConstructionRequest
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformPathConstructionResult
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformRootResolverEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformRootResolverFailureReason
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
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPersistenceGateStatus
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPersistenceGateEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPersistenceReadinessBlocker
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPersistenceReadinessCapability
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPersistenceReadinessDecision
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPersistenceReadinessEvidence
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPersistenceReadinessFailureReason
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPersistenceReadinessRequest
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPersistenceReadinessResult
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPersistenceReadinessSource
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPersistenceReadinessStatus
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPersistenceReadinessWarning
import com.libertasprimordium.skald.security.SkaldVaultV1VaultPersistenceRequiredGate
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
import com.libertasprimordium.skald.security.commonProductionProviderAcceptanceContract
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class VaultPersistenceReadinessGateTest {
    private val fixedVaultId = (0x20..0x2f).map { it.toByte() }.toByteArray()
    private val fixedRecordId = (0x40..0x4f).map { it.toByte() }.toByteArray()
    private val linuxFixtureRoot = "/home/skald-test-user/.local/share"

    @Test
    fun persistenceReadinessGatePolicyIdIsStable() {
        assertEquals(
            "skald-vault-v1-persistence-readiness-gate-v1",
            SkaldVaultV1PersistenceReadinessGate.POLICY_ID,
        )
        assertEquals(1, SkaldVaultV1PersistenceReadinessGate.POLICY_VERSION)
    }

    @Test
    fun defaultCurrentReadinessIsBlockedFailClosed() {
        val evidence = assertBlocked(
            SkaldVaultV1PersistenceReadinessGate.evaluate(
                SkaldVaultV1VaultPersistenceReadinessRequest.noEvidence(),
            ),
        )

        assertEquals(SkaldVaultV1VaultPersistenceReadinessStatus.NoEvidenceAvailable, evidence.status)
        assertEquals(SkaldVaultV1VaultPersistenceReadinessDecision.BlockedFailClosed, evidence.decision)
        assertFalse(evidence.readyForPersistence)
        assertFalse(evidence.productionReady)
        assertFalse(evidence.capability.readyForPersistence)
        assertFalse(evidence.capability.providerSelectable)
        assertFalse(evidence.capability.vaultPersistenceAvailable)
        assertContains(evidence.blockers, SkaldVaultV1VaultPersistenceReadinessBlocker.MissingRootEvidence)
        assertContains(evidence.blockers, SkaldVaultV1VaultPersistenceReadinessBlocker.MissingPathEvidence)
        assertContains(evidence.blockers, SkaldVaultV1VaultPersistenceReadinessBlocker.MissingStorageSafetyPreflightEvidence)
        assertContains(evidence.blockers, SkaldVaultV1VaultPersistenceReadinessBlocker.MissingStorageServiceEvidence)
        assertContains(evidence.blockers, SkaldVaultV1VaultPersistenceReadinessBlocker.ProductionProviderNotSelectable)
        assertContains(evidence.blockers, SkaldVaultV1VaultPersistenceReadinessBlocker.MainnetDisabled)
        assertContains(evidence.warnings, SkaldVaultV1VaultPersistenceReadinessWarning.EvidenceOnly)
    }

    @Test
    fun requiredGateVocabularyIsModeled() {
        val gates = SkaldVaultV1VaultPersistenceRequiredGate.entries.toSet()

        assertContains(gates, SkaldVaultV1VaultPersistenceRequiredGate.ProviderSelectedAndProductionSelectable)
        assertContains(gates, SkaldVaultV1VaultPersistenceRequiredGate.ProviderKatsApproved)
        assertContains(gates, SkaldVaultV1VaultPersistenceRequiredGate.FinalKdfCalibrationApproved)
        assertContains(gates, SkaldVaultV1VaultPersistenceRequiredGate.RuntimeRandomnessProviderChecksApproved)
        assertContains(gates, SkaldVaultV1VaultPersistenceRequiredGate.SecureSecretStorageAvailable)
        assertContains(gates, SkaldVaultV1VaultPersistenceRequiredGate.SecureMetadataStorageAvailable)
        assertContains(gates, SkaldVaultV1VaultPersistenceRequiredGate.LockSessionLifecycleApproved)
        assertContains(gates, SkaldVaultV1VaultPersistenceRequiredGate.RedactionLeakageReviewApproved)
        assertContains(gates, SkaldVaultV1VaultPersistenceRequiredGate.MigrationCorruptionHandlingApproved)
        assertContains(gates, SkaldVaultV1VaultPersistenceRequiredGate.PlatformRootEvidenceApproved)
        assertContains(gates, SkaldVaultV1VaultPersistenceRequiredGate.PathConstructionApproved)
        assertContains(gates, SkaldVaultV1VaultPersistenceRequiredGate.PathContainmentApproved)
        assertContains(gates, SkaldVaultV1VaultPersistenceRequiredGate.SymlinkSafetyApproved)
        assertContains(gates, SkaldVaultV1VaultPersistenceRequiredGate.PermissionOwnershipApproved)
        assertContains(gates, SkaldVaultV1VaultPersistenceRequiredGate.DurabilityApproved)
        assertContains(gates, SkaldVaultV1VaultPersistenceRequiredGate.StorageSafetyPreflightApproved)
        assertContains(gates, SkaldVaultV1VaultPersistenceRequiredGate.StorageServiceOperationsImplemented)
        assertContains(gates, SkaldVaultV1VaultPersistenceRequiredGate.ManifestReadWriteImplemented)
        assertContains(gates, SkaldVaultV1VaultPersistenceRequiredGate.StorageIndexReadWriteImplemented)
        assertContains(gates, SkaldVaultV1VaultPersistenceRequiredGate.RecordReadWriteImplemented)
        assertContains(gates, SkaldVaultV1VaultPersistenceRequiredGate.AtomicWriteImplemented)
        assertContains(gates, SkaldVaultV1VaultPersistenceRequiredGate.CrashRecoveryImplemented)
        assertContains(gates, SkaldVaultV1VaultPersistenceRequiredGate.AntiRollbackLimitationReviewed)
        assertContains(gates, SkaldVaultV1VaultPersistenceRequiredGate.SettingsRootPersistenceApproved)
        assertContains(gates, SkaldVaultV1VaultPersistenceRequiredGate.AndroidAppPrivateStoragePolicyPreserved)
        assertContains(gates, SkaldVaultV1VaultPersistenceRequiredGate.LinuxCustomRootPolicyReviewed)
        assertContains(gates, SkaldVaultV1VaultPersistenceRequiredGate.ProviderOperationsConnectedWithoutSecretExposure)
        assertContains(gates, SkaldVaultV1VaultPersistenceRequiredGate.NoBdkProductionPersistenceBypass)
        assertContains(gates, SkaldVaultV1VaultPersistenceRequiredGate.NoManagedInfrastructureDependency)
        assertContains(gates, SkaldVaultV1VaultPersistenceRequiredGate.MainnetReleaseHardeningApproved)
    }

    @Test
    fun typedEvidenceComposesButStillBlocksPersistence() {
        val evidenceBundle = composedEvidenceBundle()
        val evidence = assertBlocked(
            SkaldVaultV1PersistenceReadinessGate.evaluate(
                SkaldVaultV1VaultPersistenceReadinessRequest.fromEvidence(
                    rootEvidence = evidenceBundle.root,
                    pathEvidence = evidenceBundle.path,
                    storageSafetyPreflightEvidence = evidenceBundle.preflight,
                    storageServiceEvidence = evidenceBundle.storage,
                    gateEvidence = SkaldVaultV1VaultPersistenceRequiredGate.entries.map {
                        SkaldVaultV1VaultPersistenceGateEvidence.modelOnly(it)
                    },
                ),
            ),
        )

        assertEquals(
            SkaldVaultV1VaultPersistenceReadinessSource.ComposedTypedEvidence,
            evidence.source,
        )
        assertEquals(
            SkaldVaultV1VaultPersistenceReadinessStatus.PersistenceReadinessBlockedStillDisabled,
            evidence.status,
        )
        assertTrue(evidence.rootEvidenceConsumed)
        assertTrue(evidence.plannedArtifactLocationEvidenceConsumed)
        assertTrue(evidence.storageSafetyPreflightEvidenceConsumed)
        assertTrue(evidence.disabledStorageServiceFacadeEvidenceConsumed)
        assertTrue(evidence.providerEvidenceConsumed)
        assertTrue(evidence.secureStorageEvidenceConsumed)
        assertTrue(evidence.secureMetadataEvidenceConsumed)
        assertTrue(evidence.persistenceReadinessGateModeled)
        assertTrue(evidence.persistenceReadinessGateStillBlocked)
        assertTrue(evidence.persistenceReadinessGateComposesStorageEvidence)
        assertTrue(evidence.persistenceReadinessGateDoesNotUseFilesystem)
        assertTrue(evidence.persistenceReadinessGateDoesNotEnablePersistence)
        assertTrue(evidence.persistenceReadinessGateDoesNotEnableProviderSelection)
        assertTrue(evidence.persistenceReadinessFailureVocabularyModeled)
        assertFalse(evidence.platformPathObjectReturned)
        assertFalse(evidence.payloadExposedByDefault)
        assertFalse(evidence.readyForPersistence)
        assertFalse(evidence.capability.providerSelectable)
        assertContains(evidence.blockers, SkaldVaultV1VaultPersistenceReadinessBlocker.DisabledProviderSelection)
        assertContains(evidence.blockers, SkaldVaultV1VaultPersistenceReadinessBlocker.ProductionProviderNotSelectable)
        assertContains(evidence.blockers, SkaldVaultV1VaultPersistenceReadinessBlocker.DisabledStorageServiceFacadeStillDisabled)
        assertContains(evidence.blockers, SkaldVaultV1VaultPersistenceReadinessBlocker.UnapprovedRequiredGate)
        assertContains(evidence.blockers, SkaldVaultV1VaultPersistenceReadinessBlocker.SecureSecretStorageMissing)
        assertContains(evidence.blockers, SkaldVaultV1VaultPersistenceReadinessBlocker.SecureMetadataStorageMissing)
        assertContains(evidence.blockers, SkaldVaultV1VaultPersistenceReadinessBlocker.MainnetDisabled)
    }

    @Test
    fun missingAndRejectedEvidenceStayBlocked() {
        val evidenceBundle = composedEvidenceBundle()

        val missingPath = assertBlocked(
            SkaldVaultV1PersistenceReadinessGate.evaluate(
                SkaldVaultV1VaultPersistenceReadinessRequest.fromEvidence(
                    rootEvidence = evidenceBundle.root,
                    pathEvidence = null,
                    storageSafetyPreflightEvidence = evidenceBundle.preflight,
                    storageServiceEvidence = evidenceBundle.storage,
                ),
            ),
        )
        assertEquals(SkaldVaultV1VaultPersistenceReadinessSource.MissingPathEvidence, missingPath.source)
        assertContains(missingPath.blockers, SkaldVaultV1VaultPersistenceReadinessBlocker.MissingPathEvidence)

        val missingPreflight = assertBlocked(
            SkaldVaultV1PersistenceReadinessGate.evaluate(
                SkaldVaultV1VaultPersistenceReadinessRequest.fromEvidence(
                    rootEvidence = evidenceBundle.root,
                    pathEvidence = evidenceBundle.path,
                    storageSafetyPreflightEvidence = null,
                    storageServiceEvidence = evidenceBundle.storage,
                ),
            ),
        )
        assertEquals(
            SkaldVaultV1VaultPersistenceReadinessSource.MissingStorageSafetyPreflightEvidence,
            missingPreflight.source,
        )
        assertContains(
            missingPreflight.blockers,
            SkaldVaultV1VaultPersistenceReadinessBlocker.MissingStorageSafetyPreflightEvidence,
        )

        val missingStorage = assertBlocked(
            SkaldVaultV1PersistenceReadinessGate.evaluate(
                SkaldVaultV1VaultPersistenceReadinessRequest.fromEvidence(
                    rootEvidence = evidenceBundle.root,
                    pathEvidence = evidenceBundle.path,
                    storageSafetyPreflightEvidence = evidenceBundle.preflight,
                    storageServiceEvidence = null,
                ),
            ),
        )
        assertEquals(
            SkaldVaultV1VaultPersistenceReadinessSource.MissingStorageServiceEvidence,
            missingStorage.source,
        )
        assertContains(
            missingStorage.blockers,
            SkaldVaultV1VaultPersistenceReadinessBlocker.MissingStorageServiceEvidence,
        )

        val rejected = rejectedTypedEvidence()
        assertEquals(SkaldVaultV1VaultPersistenceReadinessSource.RejectedTypedEvidence, rejected.source)
        assertContains(rejected.blockers, SkaldVaultV1VaultPersistenceReadinessBlocker.UpstreamEvidenceRejected)
        assertFalse(rejected.readyForPersistence)
    }

    @Test
    fun warningOnlyAndUserConsentEvidenceCannotEnablePersistence() {
        val evidenceBundle = composedEvidenceBundle()
        val warningOnly = SkaldVaultV1VaultPersistenceGateEvidence.warningOnly(
            SkaldVaultV1VaultPersistenceRequiredGate.DurabilityApproved,
        )
        val userConsentOnly = SkaldVaultV1VaultPersistenceGateEvidence.userConsentOnly(
            SkaldVaultV1VaultPersistenceRequiredGate.StorageServiceOperationsImplemented,
        )
        val evidence = assertBlocked(
            SkaldVaultV1PersistenceReadinessGate.evaluate(
                SkaldVaultV1VaultPersistenceReadinessRequest.fromEvidence(
                    rootEvidence = evidenceBundle.root,
                    pathEvidence = evidenceBundle.path,
                    storageSafetyPreflightEvidence = evidenceBundle.preflight,
                    storageServiceEvidence = evidenceBundle.storage,
                    gateEvidence = listOf(warningOnly, userConsentOnly),
                ),
            ),
        )

        assertContains(evidence.blockers, SkaldVaultV1VaultPersistenceReadinessBlocker.WarningOnlyEvidenceRejected)
        assertContains(evidence.blockers, SkaldVaultV1VaultPersistenceReadinessBlocker.UserConsentOverrideRejected)
        assertFalse(evidence.readyForPersistence)
    }

    @Test
    fun redactionProtectsRootLocationRecordPayloadProviderAndPassphraseMaterial() {
        val evidenceBundle = composedEvidenceBundle()
        val request = SkaldVaultV1VaultPersistenceReadinessRequest.fromEvidence(
            rootEvidence = evidenceBundle.root,
            pathEvidence = evidenceBundle.path,
            storageSafetyPreflightEvidence = evidenceBundle.preflight,
            storageServiceEvidence = evidenceBundle.storage,
        )
        val evidence = assertBlocked(SkaldVaultV1PersistenceReadinessGate.evaluate(request))
        val forbiddenValues = listOf(
            linuxFixtureRoot,
            "safe_record_fixture",
            "provider-key-material",
            "passphrase fixture",
            "vault_202122232425262728292a2b2c2d2e2f",
            "a".repeat(64),
        )

        assertFalse(evidence.token.containsRootText)
        assertFalse(evidence.token.containsPlannedLocationText)
        assertFalse(evidence.token.containsRecordIdentifier)
        assertFalse(evidence.token.containsPayload)
        assertFalse(evidence.token.containsProviderKeyMaterial)
        assertFalse(evidence.token.containsPassphraseMaterial)
        assertFalse(evidence.token.readyForPersistence)
        forbiddenValues.forEach { raw ->
            assertRedacted(request.toString(), raw)
            assertRedacted(evidence.toString(), raw)
            assertRedacted(evidence.token.toString(), raw)
            evidence.blockers.forEach { blocker -> assertRedacted(blocker.label, raw) }
            evidence.warnings.forEach { warning -> assertRedacted(warning.label, raw) }
        }
    }

    @Test
    fun allPersistenceCapabilitiesRemainFalse() {
        val capability = assertBlocked(
            SkaldVaultV1PersistenceReadinessGate.evaluate(
                SkaldVaultV1VaultPersistenceReadinessRequest.fromEvidence(
                    rootEvidence = composedEvidenceBundle().root,
                    pathEvidence = composedEvidenceBundle().path,
                    storageSafetyPreflightEvidence = composedEvidenceBundle().preflight,
                    storageServiceEvidence = composedEvidenceBundle().storage,
                ),
            ),
        ).capability

        assertDisabledCapabilities(capability)
    }

    @Test
    fun rawReadinessInputsAreRejected() {
        val longHex = "a".repeat(64)
        val bitcoinAddressLike = "bc" + "1" + "q".repeat(24)
        val nostrSecretLike = "nsec" + "1" + "q".repeat(24)
        val extendedPrivatePrefixFixture = "xprv" + "9".repeat(32)
        val testPrivatePrefixFixture = "tprv" + "9".repeat(32)
        val wifLike = "K" + "1".repeat(50)
        val cases = listOf(
            null to SkaldVaultV1VaultPersistenceReadinessFailureReason.EmptyEvidenceRejected,
            "" to SkaldVaultV1VaultPersistenceReadinessFailureReason.EmptyEvidenceRejected,
            "   " to SkaldVaultV1VaultPersistenceReadinessFailureReason.EmptyEvidenceRejected,
            linuxFixtureRoot to SkaldVaultV1VaultPersistenceReadinessFailureReason.RawAbsoluteLocationInputRejected,
            "relative/path" to SkaldVaultV1VaultPersistenceReadinessFailureReason.RawRelativeLocationInputRejected,
            "file://skald-vault" to SkaldVaultV1VaultPersistenceReadinessFailureReason.LinkLikeInputRejected,
            "https://example.invalid/skald" to SkaldVaultV1VaultPersistenceReadinessFailureReason.LinkLikeInputRejected,
            "content://example/skald" to SkaldVaultV1VaultPersistenceReadinessFailureReason.LinkLikeInputRejected,
            "C:\\skald\\vault" to SkaldVaultV1VaultPersistenceReadinessFailureReason.RawAbsoluteLocationInputRejected,
            "\\\\server\\share" to SkaldVaultV1VaultPersistenceReadinessFailureReason.RawAbsoluteLocationInputRejected,
            "path-object-fixture" to SkaldVaultV1VaultPersistenceReadinessFailureReason.PlatformObjectLikeInputRejected,
            "segment..traversal" to SkaldVaultV1VaultPersistenceReadinessFailureReason.TraversalRejected,
            "unsupported:colon" to SkaldVaultV1VaultPersistenceReadinessFailureReason.UnsupportedEvidenceRejected,
            "password-fixture" to SkaldVaultV1VaultPersistenceReadinessFailureReason.SecretMaterialRejected,
            "token-fixture" to SkaldVaultV1VaultPersistenceReadinessFailureReason.SecretMaterialRejected,
            "passphrase-fixture" to SkaldVaultV1VaultPersistenceReadinessFailureReason.PassphraseMaterialRejected,
            "provider-key-material" to SkaldVaultV1VaultPersistenceReadinessFailureReason.ProviderKeyMaterialRejected,
            longHex to SkaldVaultV1VaultPersistenceReadinessFailureReason.TransactionLikeEvidenceRejected,
            bitcoinAddressLike to SkaldVaultV1VaultPersistenceReadinessFailureReason.BitcoinAddressLikeEvidenceRejected,
            nostrSecretLike to SkaldVaultV1VaultPersistenceReadinessFailureReason.WalletMaterialRejected,
            extendedPrivatePrefixFixture to SkaldVaultV1VaultPersistenceReadinessFailureReason.WalletMaterialRejected,
            testPrivatePrefixFixture to SkaldVaultV1VaultPersistenceReadinessFailureReason.WalletMaterialRejected,
            wifLike to SkaldVaultV1VaultPersistenceReadinessFailureReason.WalletMaterialRejected,
        )

        cases.forEach { (raw, expectedReason) ->
            val rejected = assertRejected(
                SkaldVaultV1PersistenceReadinessGate.evaluate(
                    SkaldVaultV1VaultPersistenceReadinessRequest.rawReadinessEvidenceCandidate(raw),
                ),
            )
            assertEquals(expectedReason, rejected.reason, "raw candidate should be rejected without echoing it")
            assertEquals(SkaldVaultV1VaultPersistenceReadinessStatus.RawReadinessEvidenceRejected, rejected.status)
            if (!raw.isNullOrBlank()) {
                assertFalse(rejected.toString().contains(raw))
            }
        }
    }

    @Test
    fun readinessProviderAndDependencyEvidenceRecordGateButRemainDisabled() {
        val contract = commonProductionProviderAcceptanceContract()
        val storage = contract.containerManifestStorageContract
        val acceptanceEvidence = ProductionProviderAcceptanceEvidence.currentDesignOnly()
        val assessment = contract.assess()
        val readiness = EncryptedVaultReadinessPolicy.disabled()
        val dependency = VaultCryptoDependencyProbeCatalog.currentSpikeResults()
            .single { it.candidate == VaultCryptoDependencyCandidate.TinkBouncyCastleSplit }
        val providerSelection = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(SkaldVaultV1PersistenceReadinessGate.POLICY_ID, storage.persistenceReadinessGatePolicyId)
        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            storage.persistenceReadinessGateStatus,
        )
        assertEquals(
            ProductionProviderPersistenceReadinessGateRule.entries.toSet(),
            storage.persistenceReadinessGateRules,
        )
        assertTrue(storage.persistenceReadinessGateModeled)
        assertTrue(storage.persistenceReadinessGateStillBlocked)
        assertTrue(storage.persistenceReadinessGateComposesStorageEvidence)
        assertTrue(storage.persistenceReadinessGateDoesNotUseFilesystem)
        assertTrue(storage.persistenceReadinessGateDoesNotEnablePersistence)
        assertTrue(storage.persistenceReadinessGateDoesNotEnableProviderSelection)
        assertTrue(storage.persistenceReadinessFailureVocabularyModeled)
        assertFalse(storage.vaultPersistenceImplemented)
        assertFalse(storage.storageServiceOperationSuccessPathImplemented)
        assertFalse(storage.secureSecretStorageSuccessPathImplemented)
        assertFalse(storage.secureMetadataStorageSuccessPathImplemented)

        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            acceptanceEvidence.stateFor(ProductionProviderAcceptanceGate.PersistenceReadinessGateImplementedAndTested),
        )
        assertFalse(assessment.productionProviderSelectable)
        assertFalse(assessment.productionPersistenceAllowed)

        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[EncryptedVaultRequirement.PersistenceReadinessGateImplementedAndTested],
        )
        assertContains(readiness.capabilities, EncryptedVaultCapability.PersistenceReadinessGateBoundaryBuildingBlock)
        assertContains(readiness.blockers, EncryptedVaultBlockingIssue.PersistenceReadinessGateStillBlocked)
        assertFalse(readiness.productionPersistenceEnabled)

        assertContains(dependency.capabilities, VaultCryptoDependencyCapability.PersistenceReadinessGateImplementedTested)
        assertContains(dependency.capabilities, VaultCryptoDependencyCapability.PersistenceReadinessFailureVocabularyModeled)
        assertContains(dependency.blockers, VaultCryptoDependencyBlocker.PersistenceReadinessGateStillBlocked)
        assertFalse(dependency.storageEnabled)
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
        return ComposedEvidenceBundle(root, path, preflight, storage)
    }

    private fun rejectedTypedEvidence(): SkaldVaultV1VaultPersistenceReadinessEvidence {
        val rootResult = SkaldVaultV1PlatformRootResolverPolicy.resolve(
            SkaldVaultV1PlatformRootResolverRequest.androidExternalSharedEvidence(
                "android-external-shared-root-evidence-fixture",
            ),
        )
        val pathResult = SkaldVaultV1PlatformPathConstructionPolicy.plan(
            SkaldVaultV1PlatformPathConstructionRequest.fromRootResultAndLayout(
                rootResult = rootResult,
                layoutPlan = fixedLayout(),
            ),
        )
        val preflightResult = SkaldVaultV1StorageSafetyPreflightPolicy.evaluate(
            SkaldVaultV1StorageSafetyPreflightRequest.fromPathConstructionResult(pathResult),
        )
        val storageResult = SkaldVaultV1DisabledStorageServiceFacade.perform(
            SkaldVaultV1VaultStorageOperationRequest.fromBoundaryResults(
                operation = SkaldVaultV1VaultStorageOperation.ReadRecord,
                artifactResult = pathResult,
                preflightResult = preflightResult,
            ),
        )
        val evidence = assertBlocked(
            SkaldVaultV1PersistenceReadinessGate.evaluate(
                SkaldVaultV1VaultPersistenceReadinessRequest.fromBoundaryResults(
                    rootResult = rootResult,
                    pathResult = pathResult,
                    storageSafetyPreflightResult = preflightResult,
                    storageServiceResult = storageResult,
                ),
            ),
        )
        val rejectedRoot = rootResult as SkaldVaultV1PlatformRootResolverResult.Rejected
        assertEquals(SkaldVaultV1PlatformRootResolverFailureReason.AndroidExternalSharedRootRejected, rejectedRoot.reason)
        return evidence
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

    private fun assertBlocked(
        result:
            SkaldVaultV1VaultPersistenceReadinessResult<SkaldVaultV1VaultPersistenceReadinessEvidence>,
    ): SkaldVaultV1VaultPersistenceReadinessEvidence =
        when (result) {
            is SkaldVaultV1VaultPersistenceReadinessResult.Blocked -> result.value
            is SkaldVaultV1VaultPersistenceReadinessResult.Rejected ->
                error("expected blocked readiness evidence but got ${result.reason}")
        }

    private fun assertRejected(
        result: SkaldVaultV1VaultPersistenceReadinessResult<*>,
    ): SkaldVaultV1VaultPersistenceReadinessResult.Rejected =
        when (result) {
            is SkaldVaultV1VaultPersistenceReadinessResult.Blocked ->
                error("expected rejected readiness evidence but got blocked")
            is SkaldVaultV1VaultPersistenceReadinessResult.Rejected -> result
        }

    private fun assertDisabledCapabilities(capability: SkaldVaultV1VaultPersistenceReadinessCapability) {
        assertFalse(capability.readyForPersistence)
        assertFalse(capability.usableForFileIo)
        assertFalse(capability.providerSelectable)
        assertFalse(capability.productionProviderSelected)
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
    )
}
