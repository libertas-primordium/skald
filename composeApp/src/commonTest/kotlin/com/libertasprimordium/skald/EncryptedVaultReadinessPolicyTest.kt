package com.libertasprimordium.skald

import com.libertasprimordium.skald.domain.onchain.BitcoinWalletSyncBlocker
import com.libertasprimordium.skald.domain.onchain.BitcoinWalletSyncRequest
import com.libertasprimordium.skald.domain.onchain.BitcoinWalletSyncCapability
import com.libertasprimordium.skald.domain.onchain.BitcoinWalletSyncWarning
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletProfileId
import com.libertasprimordium.skald.domain.onchain.DisabledBitcoinWalletSyncService
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressDerivationIndex
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressSource
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressState
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressWalletContext
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressWalletOperationalState
import com.libertasprimordium.skald.domain.core.NetworkEnvironment
import com.libertasprimordium.skald.security.Argon2idCalibrationImplementationStatus
import com.libertasprimordium.skald.security.DisabledSecureSecretStorage
import com.libertasprimordium.skald.security.DisabledSecureWalletMetadataRepository
import com.libertasprimordium.skald.security.EncryptedVaultAeadAlgorithm
import com.libertasprimordium.skald.security.EncryptedVaultAssociatedDataRequirement
import com.libertasprimordium.skald.security.EncryptedVaultBlockingIssue
import com.libertasprimordium.skald.security.EncryptedVaultCapability
import com.libertasprimordium.skald.security.EncryptedVaultDecisionRole
import com.libertasprimordium.skald.security.EncryptedVaultImplementationStatus
import com.libertasprimordium.skald.security.EncryptedVaultKeyHierarchyRequirement
import com.libertasprimordium.skald.security.EncryptedVaultKdfAlgorithm
import com.libertasprimordium.skald.security.EncryptedVaultPlatform
import com.libertasprimordium.skald.security.EncryptedVaultReadinessPolicy
import com.libertasprimordium.skald.security.EncryptedVaultRequirement
import com.libertasprimordium.skald.security.EncryptedVaultRequirementStatus
import com.libertasprimordium.skald.security.EncryptedVaultWarning
import com.libertasprimordium.skald.security.SecureMetadataPersistencePolicy
import com.libertasprimordium.skald.security.SensitiveMetadataKind
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class EncryptedVaultReadinessPolicyTest {
    private val secureStorage = DisabledSecureSecretStorage().capability
    private val secureMetadata = DisabledSecureWalletMetadataRepository().capability

    @Test
    fun disabledVaultReadinessReportsNotImplementedAndFailClosed() {
        val readiness = EncryptedVaultReadinessPolicy.disabled()
        val decision = EncryptedVaultReadinessPolicy.evaluate(
            readiness = readiness,
            secureStorageCapability = secureStorage,
            secureMetadataCapability = secureMetadata,
        )

        assertEquals(EncryptedVaultImplementationStatus.NotImplemented, readiness.implementationStatus)
        assertFalse(readiness.readyForProductionPersistence)
        assertFalse(readiness.productionPersistenceEnabled)
        assertFalse(readiness.mainnetEnabled)
        assertFalse(decision.canEnableProductionPersistence)
        assertContains(decision.blockers, EncryptedVaultBlockingIssue.VaultImplementationUnavailable)
        assertContains(decision.blockers, EncryptedVaultBlockingIssue.ProductionProviderImplementationUnavailable)
        assertContains(decision.blockers, EncryptedVaultBlockingIssue.ProviderSelectionProductionBlocked)
        assertContains(decision.blockers, EncryptedVaultBlockingIssue.ProductionProviderAcceptanceContractIncomplete)
        assertContains(decision.blockers, EncryptedVaultBlockingIssue.StillDisabledProviderIntegrationHarnessNotSelectable)
        assertContains(decision.blockers, EncryptedVaultBlockingIssue.StillDisabledProviderFacadeNotSelectable)
        assertContains(decision.blockers, EncryptedVaultBlockingIssue.StaleRecordManifestIntegrationMissing)
        assertContains(decision.blockers, EncryptedVaultBlockingIssue.VaultContainerPersistenceImplementationMissing)
        assertContains(decision.blockers, EncryptedVaultBlockingIssue.ManifestReadWriteImplementationMissing)
        assertContains(decision.blockers, EncryptedVaultBlockingIssue.StorageSuccessPathAbsent)
        assertContains(decision.blockers, EncryptedVaultBlockingIssue.DisabledStorageServiceFacadeStillDisabled)
        assertContains(decision.blockers, EncryptedVaultBlockingIssue.PersistenceReadinessGateStillBlocked)
        assertContains(decision.blockers, EncryptedVaultBlockingIssue.LockSessionLifecycleStillDisabled)
        assertContains(decision.blockers, EncryptedVaultBlockingIssue.RedactionLeakageBoundaryStillDisabled)
        assertContains(decision.blockers, EncryptedVaultBlockingIssue.PassphrasePolicyBoundaryStillDisabled)
        assertContains(decision.blockers, EncryptedVaultBlockingIssue.ClearWipeStrategyBoundaryStillDisabled)
        assertContains(decision.blockers, EncryptedVaultBlockingIssue.MigrationCorruptionBoundaryStillDisabled)
        assertContains(decision.blockers, EncryptedVaultBlockingIssue.ProviderOperationAuthorizationBoundaryStillDisabled)
        assertContains(decision.blockers, EncryptedVaultBlockingIssue.RuntimeRandomnessAuthorizationBoundaryStillDisabled)
        assertContains(decision.blockers, EncryptedVaultBlockingIssue.KdfCalibrationAuthorizationBoundaryStillDisabled)
        assertContains(decision.blockers, EncryptedVaultBlockingIssue.SecureStorageAuthorizationBoundaryStillDisabled)
        assertContains(decision.blockers, EncryptedVaultBlockingIssue.PlatformStorageImplementationMissing)
        assertContains(decision.blockers, EncryptedVaultBlockingIssue.AtomicWriteImplementationMissing)
        assertContains(decision.blockers, EncryptedVaultBlockingIssue.CrashRecoveryImplementationMissing)
        assertContains(decision.blockers, EncryptedVaultBlockingIssue.StorageInterruptionTestsMissing)
        assertContains(decision.blockers, EncryptedVaultBlockingIssue.StorageFailureRuntimeMappingMissing)
        assertFalse(decision.blockers.contains(EncryptedVaultBlockingIssue.StorageNamespacePathImplementationMissing))
        assertContains(decision.blockers, EncryptedVaultBlockingIssue.StoragePathConstructionImplementationMissing)
        assertContains(decision.blockers, EncryptedVaultBlockingIssue.PlatformRootSettingsImplementationMissing)
        assertContains(decision.blockers, EncryptedVaultBlockingIssue.SettingsUiMissing)
        assertContains(decision.blockers, EncryptedVaultBlockingIssue.SettingsPersistenceMissing)
        assertFalse(decision.blockers.contains(EncryptedVaultBlockingIssue.LinuxCustomRootValidationMissing))
        assertContains(decision.blockers, EncryptedVaultBlockingIssue.DurabilityFailClosedRuntimeEvidenceMissing)
        assertContains(decision.blockers, EncryptedVaultBlockingIssue.WarningOnlyDurabilityPersistenceRejected)
        assertFalse(decision.blockers.contains(EncryptedVaultBlockingIssue.StaleRecordManifestPolicyImplementationMissing))
        assertContains(decision.blockers, EncryptedVaultBlockingIssue.AntiRollbackAnchorAbsentNoFullRollbackClaim)
        assertContains(decision.blockers, EncryptedVaultBlockingIssue.ManifestStorageAtomicityReviewMissing)
        assertFalse(decision.blockers.contains(EncryptedVaultBlockingIssue.PassphraseEncodingPolicyUnapproved))
        assertFalse(decision.blockers.contains(EncryptedVaultBlockingIssue.PassphrasePolicyProviderIntegrationMissing))
        assertFalse(decision.blockers.contains(EncryptedVaultBlockingIssue.Argon2idRootDerivationProviderIntegrationMissing))
        assertContains(decision.blockers, EncryptedVaultBlockingIssue.TinkRawKeyFeasibilityProbeOnly)
        assertContains(decision.blockers, EncryptedVaultBlockingIssue.Argon2idBoundedCalibrationUnapproved)
        assertFalse(decision.blockers.contains(EncryptedVaultBlockingIssue.ProviderKnownAnswerVectorsMissing))
        assertContains(decision.blockers, EncryptedVaultBlockingIssue.ProductionPersistenceDisabled)
        assertContains(decision.blockers, EncryptedVaultBlockingIssue.MainnetDisabled)
        assertContains(decision.warnings, EncryptedVaultWarning.ReadinessOnlyNoEncryption)
        assertContains(decision.warnings, EncryptedVaultWarning.KdfCalibrationProbeOnly)
        assertContains(decision.warnings, EncryptedVaultWarning.KdfCandidateParameterPolicyNotFinal)
        assertContains(readiness.capabilities, EncryptedVaultCapability.DisabledCryptoProviderBoundary)
        assertContains(readiness.capabilities, EncryptedVaultCapability.ProviderSelectionBoundaryModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.ProviderKatContractModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.ProviderLevelKatStrategyContractModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.RandomizedAeadBehavioralKatPolicyModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.IntegratedVerificationOrderKatPolicyModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.StillDisabledProviderIntegrationHarness)
        assertContains(readiness.capabilities, EncryptedVaultCapability.StillDisabledProviderLevelKatExecution)
        assertContains(readiness.capabilities, EncryptedVaultCapability.StillDisabledRandomizedAeadBehavioralKatExecution)
        assertContains(readiness.capabilities, EncryptedVaultCapability.StillDisabledIntegratedVerificationOrderKatExecution)
        assertContains(readiness.capabilities, EncryptedVaultCapability.StillDisabledProviderFacadeBoundary)
        assertContains(readiness.capabilities, EncryptedVaultCapability.VaultContainerContractModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.InMemoryVaultContainerParserWriterBuildingBlock)
        assertContains(readiness.capabilities, EncryptedVaultCapability.ManifestContractModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.StaleRecordManifestPolicyModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.InMemoryManifestParserWriterBuildingBlock)
        assertContains(
            readiness.capabilities,
            EncryptedVaultCapability.LocalManifestStaleRecordDecisionPolicyBuildingBlock,
        )
        assertContains(readiness.capabilities, EncryptedVaultCapability.StoragePolicyContractModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.PlatformStorageBoundaryContractModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.AtomicityCrashRecoveryContractModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.AtomicWriteStrategyContractModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.CrashRecoveryContractModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.StorageInterruptionTestContractModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.InMemoryStorageAtomicityCrashSimulator)
        assertContains(readiness.capabilities, EncryptedVaultCapability.StorageFailureModelContractModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.StorageNamespacePathHygieneContractModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.StorageNamespacePathPolicyBuildingBlock)
        assertContains(readiness.capabilities, EncryptedVaultCapability.StorageLayoutPlanBuildingBlock)
        assertContains(readiness.capabilities, EncryptedVaultCapability.PathContainmentPlannerBuildingBlock)
        assertContains(readiness.capabilities, EncryptedVaultCapability.PlatformRootSettingsPolicyModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.AndroidAppPrivateRootPolicyModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.LinuxRootSettingsPolicyModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.LinuxCustomRootValidationPolicyBuildingBlock)
        assertContains(readiness.capabilities, EncryptedVaultCapability.LinuxRootResolutionPolicyBuildingBlock)
        assertContains(readiness.capabilities, EncryptedVaultCapability.PlatformRootResolverBoundaryBuildingBlock)
        assertContains(readiness.capabilities, EncryptedVaultCapability.PlatformPathConstructionBoundaryBuildingBlock)
        assertContains(readiness.capabilities, EncryptedVaultCapability.StorageSafetyPreflightBoundaryBuildingBlock)
        assertContains(readiness.capabilities, EncryptedVaultCapability.DisabledStorageServiceFacadeBoundaryBuildingBlock)
        assertContains(readiness.capabilities, EncryptedVaultCapability.PersistenceReadinessGateBoundaryBuildingBlock)
        assertContains(readiness.capabilities, EncryptedVaultCapability.LockSessionLifecycleBoundaryBuildingBlock)
        assertContains(readiness.capabilities, EncryptedVaultCapability.RedactionLeakageBoundaryBuildingBlock)
        assertContains(readiness.capabilities, EncryptedVaultCapability.PassphrasePolicyBoundaryBuildingBlock)
        assertContains(readiness.capabilities, EncryptedVaultCapability.ClearWipeStrategyBoundaryBuildingBlock)
        assertContains(readiness.capabilities, EncryptedVaultCapability.MigrationCorruptionBoundaryBuildingBlock)
        assertContains(readiness.capabilities, EncryptedVaultCapability.ProviderOperationAuthorizationBoundaryBuildingBlock)
        assertContains(readiness.capabilities, EncryptedVaultCapability.RuntimeRandomnessAuthorizationBoundaryBuildingBlock)
        assertContains(readiness.capabilities, EncryptedVaultCapability.KdfCalibrationAuthorizationBoundaryBuildingBlock)
        assertContains(readiness.capabilities, EncryptedVaultCapability.SecureStorageAuthorizationBoundaryBuildingBlock)
        assertContains(readiness.capabilities, EncryptedVaultCapability.OsKeyringPassphraseRejectionModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.PasswordManagerIntegrationRejectionModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.PassphraseFirstDefaultModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.DurabilityFailClosedPolicyModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.WarningOnlyDurabilityRejectionModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.RollbackLimitationAntiRollbackAnchorModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.SecureStorageBoundaryContractModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.TestOnlyProviderKatHarnessModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.Argon2idCalibrationPolicyModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.Argon2idCandidateParameterPolicyModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.AndroidCompatibilityEntropyPolicyModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.RuntimeRandomnessProviderCheckModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.ProductionProviderAcceptanceContractModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.VaultHeaderCommitmentPolicyModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.HkdfSha256KeyExpansionPolicyModel)
        assertContains(
            readiness.capabilities,
            EncryptedVaultCapability.HmacSha256HeaderCommitmentPrimitivePolicyModel,
        )
        assertContains(readiness.capabilities, EncryptedVaultCapability.KeyExpansionOutputLayoutPolicyModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.PrimitiveThreatModelRationaleModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.CanonicalHeaderByteVectorContractModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.HkdfSha256VectorContractModel)
        assertContains(
            readiness.capabilities,
            EncryptedVaultCapability.HmacSha256HeaderCommitmentVectorContractModel,
        )
        assertContains(readiness.capabilities, EncryptedVaultCapability.CanonicalHeaderEncodingPolicyModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.KeySeparationLabelsPolicyModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.CanonicalHeaderSerializerBuildingBlock)
        assertContains(readiness.capabilities, EncryptedVaultCapability.HkdfSha256KeyExpansionBuildingBlock)
        assertContains(readiness.capabilities, EncryptedVaultCapability.HmacSha256HeaderCommitmentBuildingBlock)
        assertContains(readiness.capabilities, EncryptedVaultCapability.StrictAadContractPolicyModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.StrictAadSerializationBuildingBlock)
        assertContains(readiness.capabilities, EncryptedVaultCapability.TinkRecordAeadBuildingBlock)
        assertContains(readiness.capabilities, EncryptedVaultCapability.TinkNonKeyCommitmentMitigationModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.PassphraseEncodingPolicyModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.PassphrasePolicyValidationBuildingBlock)
        assertContains(readiness.capabilities, EncryptedVaultCapability.Argon2idPassphraseRootDerivationBuildingBlock)
        assertContains(readiness.capabilities, EncryptedVaultCapability.TinkRawKeyFeasibilityPolicyModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.Argon2idBoundedCalibrationPolicyModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.Argon2idCalibrationPolicyBuildingBlock)
        assertContains(readiness.capabilities, EncryptedVaultCapability.Argon2idCandidateSelectionPolicyBuildingBlock)
        assertContains(readiness.capabilities, EncryptedVaultCapability.Argon2idMemoryFailureHandlingModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.Argon2idStoredParameterNoDowngradeModel)
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.KdfCalibrationAuthorizationBoundaryImplementedAndTested
            ],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.SecureStorageAuthorizationBoundaryImplementedAndTested
            ],
        )
        assertEquals(
            Argon2idCalibrationImplementationStatus.StillDisabledBuildingBlockImplemented,
            readiness.argon2idCalibrationPolicy.status,
        )
        assertTrue(readiness.argon2idCalibrationPolicy.status.stillDisabledBuildingBlockImplemented)
        assertTrue(readiness.argon2idCalibrationPolicy.status.candidateSelectionImplemented)
        assertTrue(readiness.argon2idCalibrationPolicy.status.memoryFailureHandlingImplemented)
        assertFalse(readiness.argon2idCalibrationPolicy.productionKdfEnabled)
        assertFalse(readiness.argon2idCalibrationPolicy.calibrationComplete)
        assertFalse(readiness.argon2idCalibrationPolicy.candidateParameterPolicy.finalProductionParametersApproved)
        assertTrue(readiness.argon2idCalibrationPolicy.candidateParameterPolicy.androidBaselineCoverageSatisfied)
        assertFalse(readiness.androidCompatibilityPolicy.minimumCompatibility.lowEndModelTestingRequired)
        assertFalse(readiness.androidCompatibilityPolicy.minimumCompatibility.midRangeModelTestingRequired)
    }

    @Test
    fun algorithmChoicesAreDesignTargetsOnly() {
        val policy = EncryptedVaultReadinessPolicy.disabled().algorithmPolicy

        assertEquals(EncryptedVaultKdfAlgorithm.Argon2id, policy.targetKdf)
        assertEquals(EncryptedVaultDecisionRole.DesignTarget, policy.targetKdf.role)
        assertContains(policy.fallbackKdfs, EncryptedVaultKdfAlgorithm.Scrypt)
        assertContains(policy.rejectedDefaultKdfs, EncryptedVaultKdfAlgorithm.Pbkdf2)
        assertFalse(policy.acceptsDefaultProductionKdf(EncryptedVaultKdfAlgorithm.Argon2id))
        assertFalse(policy.acceptsDefaultProductionKdf(EncryptedVaultKdfAlgorithm.Pbkdf2))
        assertEquals(EncryptedVaultAeadAlgorithm.XChaCha20Poly1305, policy.preferredRecordAead)
        assertTrue(policy.preferredRecordAead.preferredRecordEnvelope)
        assertEquals(24, policy.noncePolicy.byteLength)
        assertTrue(policy.noncePolicy.randomPerRecord)
        assertTrue(policy.noncePolicy.designOnly)
        assertContains(
            policy.associatedDataRequirements,
            EncryptedVaultAssociatedDataRequirement.BindVaultMagicDomainMarker,
        )
        assertContains(
            policy.associatedDataRequirements,
            EncryptedVaultAssociatedDataRequirement.BindProviderSuiteId,
        )
        assertContains(
            policy.associatedDataRequirements,
            EncryptedVaultAssociatedDataRequirement.BindHeaderCommitmentContext,
        )
        assertContains(
            policy.keyHierarchyRequirements,
            EncryptedVaultKeyHierarchyRequirement.PassphraseDerivedRootMaterial,
        )
        assertContains(
            policy.keyHierarchyRequirements,
            EncryptedVaultKeyHierarchyRequirement.Argon2idRootMaterial64Bytes,
        )
        assertContains(
            policy.keyHierarchyRequirements,
            EncryptedVaultKeyHierarchyRequirement.HkdfSha256KeyExpansionSelected,
        )
        assertContains(
            policy.keyHierarchyRequirements,
            EncryptedVaultKeyHierarchyRequirement.HmacSha256HeaderCommitmentSelected,
        )
        assertContains(
            policy.keyHierarchyRequirements,
            EncryptedVaultKeyHierarchyRequirement.HeaderCommitmentKey32Bytes,
        )
        assertContains(
            policy.keyHierarchyRequirements,
            EncryptedVaultKeyHierarchyRequirement.RecordAeadKey32Bytes,
        )
        assertContains(
            policy.keyHierarchyRequirements,
            EncryptedVaultKeyHierarchyRequirement.VersionedKeySeparationLabelsRequired,
        )
        assertContains(
            policy.keyHierarchyRequirements,
            EncryptedVaultKeyHierarchyRequirement.HeaderCommitmentKeySeparated,
        )
        assertContains(
            policy.keyHierarchyRequirements,
            EncryptedVaultKeyHierarchyRequirement.RecordAeadKeyMaterialDerivedFromRoot,
        )
        assertContains(
            policy.keyHierarchyRequirements,
            EncryptedVaultKeyHierarchyRequirement.NoRandomTinkVaultKeyInV1,
        )
        assertContains(
            policy.keyHierarchyRequirements,
            EncryptedVaultKeyHierarchyRequirement.NoPersistedTinkKeysetInV1,
        )
    }

    @Test
    fun productionReadinessRequiresAllReviewedImplementationGates() {
        val readiness = EncryptedVaultReadinessPolicy.disabled()
        val requiredGates = setOf(
            EncryptedVaultRequirement.DependencySelectionReviewed,
            EncryptedVaultRequirement.DisabledProviderBoundaryModeled,
            EncryptedVaultRequirement.ProviderSelectionBoundaryModeled,
            EncryptedVaultRequirement.ProviderKatContractModeled,
            EncryptedVaultRequirement.ProviderLevelKatStrategyContractModeled,
            EncryptedVaultRequirement.RandomizedAeadBehavioralKatPolicyModeled,
            EncryptedVaultRequirement.IntegratedVerificationOrderKatPolicyModeled,
            EncryptedVaultRequirement.StillDisabledProviderIntegrationHarnessImplementedAndTested,
            EncryptedVaultRequirement.ProviderLevelKatsExecutedInStillDisabledHarness,
            EncryptedVaultRequirement.RandomizedAeadBehavioralKatsExecutedInStillDisabledHarness,
            EncryptedVaultRequirement.IntegratedVerificationOrderKatsExecutedInStillDisabledHarness,
            EncryptedVaultRequirement.StillDisabledProviderFacadeImplementedAndTested,
            EncryptedVaultRequirement.StillDisabledProviderFacadeMetadataOnly,
            EncryptedVaultRequirement.StillDisabledProviderFacadeOperationsDisabled,
            EncryptedVaultRequirement.VaultContainerContractModeled,
            EncryptedVaultRequirement.ManifestContractModeled,
            EncryptedVaultRequirement.StaleRecordManifestPolicyModeled,
            EncryptedVaultRequirement.StoragePolicyContractModeled,
            EncryptedVaultRequirement.PlatformStorageBoundaryContractModeled,
            EncryptedVaultRequirement.AtomicityCrashRecoveryContractModeled,
            EncryptedVaultRequirement.AtomicWriteStrategyContractModeled,
            EncryptedVaultRequirement.CrashRecoveryContractModeled,
            EncryptedVaultRequirement.StorageInterruptionTestContractModeled,
            EncryptedVaultRequirement.StorageAtomicityCrashSimulatorImplementedAndTested,
            EncryptedVaultRequirement.StorageFailureModelModeled,
            EncryptedVaultRequirement.StorageNamespacePathHygieneModeled,
            EncryptedVaultRequirement.StorageNamespacePathPolicyImplementedAndTested,
            EncryptedVaultRequirement.StorageLayoutPlanImplementedAndTested,
            EncryptedVaultRequirement.PathContainmentPlannerImplementedAndTested,
            EncryptedVaultRequirement.PlatformStorageRootContractModeled,
            EncryptedVaultRequirement.PlatformRootSettingsPolicyModeled,
            EncryptedVaultRequirement.AndroidAppPrivateRootPolicyModeled,
            EncryptedVaultRequirement.LinuxRootSettingsPolicyModeled,
            EncryptedVaultRequirement.LinuxCustomRootValidationPolicyImplementedAndTested,
            EncryptedVaultRequirement.LinuxRootResolutionPolicyImplementedAndTested,
            EncryptedVaultRequirement.PlatformRootResolverBoundaryImplementedAndTested,
            EncryptedVaultRequirement.PlatformPathConstructionBoundaryImplementedAndTested,
            EncryptedVaultRequirement.StorageSafetyPreflightBoundaryImplementedAndTested,
            EncryptedVaultRequirement.DisabledStorageServiceFacadeImplementedAndTested,
            EncryptedVaultRequirement.PersistenceReadinessGateImplementedAndTested,
            EncryptedVaultRequirement.LockSessionLifecycleBoundaryImplementedAndTested,
            EncryptedVaultRequirement.RedactionLeakageBoundaryImplementedAndTested,
            EncryptedVaultRequirement.PassphrasePolicyBoundaryImplementedAndTested,
            EncryptedVaultRequirement.ClearWipeStrategyBoundaryImplementedAndTested,
            EncryptedVaultRequirement.MigrationCorruptionBoundaryImplementedAndTested,
            EncryptedVaultRequirement.OsKeyringPassphraseStorageRejected,
            EncryptedVaultRequirement.PasswordManagerIntegrationRejected,
            EncryptedVaultRequirement.PassphraseFirstDefaultModeled,
            EncryptedVaultRequirement.SettingsUiAbsent,
            EncryptedVaultRequirement.SettingsPersistenceAbsent,
            EncryptedVaultRequirement.SafePathConstructionContractModeled,
            EncryptedVaultRequirement.SymlinkTraversalContractModeled,
            EncryptedVaultRequirement.StoragePermissionOwnershipContractModeled,
            EncryptedVaultRequirement.DurabilityCapabilityContractModeled,
            EncryptedVaultRequirement.DurabilityFailClosedPolicyModeled,
            EncryptedVaultRequirement.WarningOnlyDurabilityPersistenceRejected,
            EncryptedVaultRequirement.SecureStorageBoundaryContractModeled,
            EncryptedVaultRequirement.RollbackLimitationAndAntiRollbackAnchorModeled,
            EncryptedVaultRequirement.KdfCalibrationPolicyModeled,
            EncryptedVaultRequirement.KdfCandidateParameterPolicyModeled,
            EncryptedVaultRequirement.Argon2idCalibrationPolicyImplementedAndTested,
            EncryptedVaultRequirement.Argon2idCandidateSelectionPolicyImplementedAndTested,
            EncryptedVaultRequirement.Argon2idMemoryFailureHandlingModeledAndTested,
            EncryptedVaultRequirement.Argon2idStoredParameterNoDowngradeModeledAndTested,
            EncryptedVaultRequirement.AndroidCompatibilityEntropyPolicyModeled,
            EncryptedVaultRequirement.RuntimeCryptoProviderChecksModeled,
            EncryptedVaultRequirement.RuntimeRandomnessProviderChecksModeled,
            EncryptedVaultRequirement.RuntimeEntropyChecksModeled,
            EncryptedVaultRequirement.VaultCreationFailClosedWarningModeled,
            EncryptedVaultRequirement.ProductionProviderAcceptanceContractModeled,
            EncryptedVaultRequirement.VaultHeaderCommitmentPolicyModeled,
            EncryptedVaultRequirement.HkdfSha256KeyExpansionPolicyModeled,
            EncryptedVaultRequirement.HmacSha256HeaderCommitmentPrimitiveModeled,
            EncryptedVaultRequirement.KeyExpansionOutputLayoutModeled,
            EncryptedVaultRequirement.PrimitiveThreatModelRationaleModeled,
            EncryptedVaultRequirement.CanonicalHeaderByteVectorContractModeled,
            EncryptedVaultRequirement.HkdfSha256VectorContractModeled,
            EncryptedVaultRequirement.HmacSha256HeaderCommitmentVectorContractModeled,
            EncryptedVaultRequirement.CanonicalHeaderEncodingPolicyModeled,
            EncryptedVaultRequirement.KeySeparationLabelsPolicyModeled,
            EncryptedVaultRequirement.CanonicalHeaderSerializerImplementedAndVectorTested,
            EncryptedVaultRequirement.HkdfSha256KeyExpansionImplementedAndVectorTested,
            EncryptedVaultRequirement.HmacSha256HeaderCommitmentImplementedAndVectorTested,
            EncryptedVaultRequirement.StrictAadContractModeled,
            EncryptedVaultRequirement.StrictAadSerializationImplementedAndTested,
            EncryptedVaultRequirement.TinkRecordAeadBuildingBlockImplementedAndTested,
            EncryptedVaultRequirement.TinkNonKeyCommitmentMitigationModeled,
            EncryptedVaultRequirement.PassphraseEncodingPolicyModeled,
            EncryptedVaultRequirement.PassphrasePolicyValidationImplementedAndTested,
            EncryptedVaultRequirement.Argon2idPassphraseRootDerivationImplementedAndTested,
            EncryptedVaultRequirement.TinkRawKeyFeasibilityPolicyModeled,
            EncryptedVaultRequirement.Argon2idBoundedCalibrationPolicyModeled,
            EncryptedVaultRequirement.KdfParametersCalibrated,
            EncryptedVaultRequirement.AeadImplementationVerified,
            EncryptedVaultRequirement.ProviderBoundaryKnownAnswerVectorsPassed,
            EncryptedVaultRequirement.KnownAnswerVectorsIdentified,
            EncryptedVaultRequirement.VaultContainerFormatImplemented,
            EncryptedVaultRequirement.VaultContainerParserImplemented,
            EncryptedVaultRequirement.VaultContainerWriterImplemented,
            EncryptedVaultRequirement.ManifestParserImplemented,
            EncryptedVaultRequirement.ManifestWriterImplemented,
            EncryptedVaultRequirement.StaleRecordDecisionPolicyImplemented,
            EncryptedVaultRequirement.LockSessionLifecycleTested,
            EncryptedVaultRequirement.RedactionTestsPassed,
            EncryptedVaultRequirement.MigrationAndCorruptionTestsPassed,
            EncryptedVaultRequirement.SecureSecretStorageAvailable,
            EncryptedVaultRequirement.SecureMetadataStorageAvailable,
            EncryptedVaultRequirement.ProductionPersistenceApproved,
        )

        assertTrue(requiredGates.all { it in readiness.requirementStatuses.keys })
        assertTrue(
            requiredGates.all {
                readiness.requirementStatuses[it]?.satisfiedForProductionPersistence == false
            },
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.DependencySelectionReviewed],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.DisabledProviderBoundaryModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.ProviderSelectionBoundaryModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.ProviderKatContractModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.ProviderLevelKatStrategyContractModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.RandomizedAeadBehavioralKatPolicyModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.IntegratedVerificationOrderKatPolicyModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.StillDisabledProviderFacadeImplementedAndTested
            ],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[EncryptedVaultRequirement.StillDisabledProviderFacadeMetadataOnly],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.StillDisabledProviderFacadeOperationsDisabled
            ],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.StaleRecordManifestPolicyModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.VaultContainerContractModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.ManifestContractModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.StoragePolicyContractModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.PlatformStorageBoundaryContractModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.AtomicityCrashRecoveryContractModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.AtomicWriteStrategyContractModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.CrashRecoveryContractModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.StorageInterruptionTestContractModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.StorageAtomicityCrashSimulatorImplementedAndTested
            ],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.StorageFailureModelModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.StorageNamespacePathHygieneModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.StorageNamespacePathPolicyImplementedAndTested
            ],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.StorageLayoutPlanImplementedAndTested
            ],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.PathContainmentPlannerImplementedAndTested
            ],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.PlatformStorageRootContractModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.PlatformRootSettingsPolicyModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.AndroidAppPrivateRootPolicyModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.LinuxRootSettingsPolicyModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.LinuxCustomRootValidationPolicyImplementedAndTested
            ],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.LinuxRootResolutionPolicyImplementedAndTested
            ],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.PlatformRootResolverBoundaryImplementedAndTested
            ],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.PlatformPathConstructionBoundaryImplementedAndTested
            ],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.StorageSafetyPreflightBoundaryImplementedAndTested
            ],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.DisabledStorageServiceFacadeImplementedAndTested
            ],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.PersistenceReadinessGateImplementedAndTested
            ],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.LockSessionLifecycleBoundaryImplementedAndTested
            ],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.RedactionLeakageBoundaryImplementedAndTested
            ],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.PassphrasePolicyBoundaryImplementedAndTested
            ],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.ClearWipeStrategyBoundaryImplementedAndTested
            ],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.MigrationCorruptionBoundaryImplementedAndTested
            ],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[EncryptedVaultRequirement.LockSessionLifecycleTested],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.Absent,
            readiness.requirementStatuses[EncryptedVaultRequirement.RedactionTestsPassed],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.OsKeyringPassphraseStorageRejected],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.PasswordManagerIntegrationRejected],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.PassphraseFirstDefaultModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.DisabledByPolicy,
            readiness.requirementStatuses[EncryptedVaultRequirement.SettingsUiAbsent],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.DisabledByPolicy,
            readiness.requirementStatuses[EncryptedVaultRequirement.SettingsPersistenceAbsent],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.SafePathConstructionContractModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.SymlinkTraversalContractModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.StoragePermissionOwnershipContractModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.DurabilityCapabilityContractModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.DurabilityFailClosedPolicyModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.WarningOnlyDurabilityPersistenceRejected],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.SecureStorageBoundaryContractModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.RollbackLimitationAndAntiRollbackAnchorModeled
            ],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.KdfCalibrationPolicyModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.KdfCandidateParameterPolicyModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.Argon2idCalibrationPolicyImplementedAndTested
            ],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.Argon2idCandidateSelectionPolicyImplementedAndTested
            ],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.Argon2idMemoryFailureHandlingModeledAndTested
            ],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.Argon2idStoredParameterNoDowngradeModeledAndTested
            ],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.AndroidCompatibilityEntropyPolicyModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.RuntimeCryptoProviderChecksModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.RuntimeRandomnessProviderChecksModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.RuntimeEntropyChecksModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.VaultCreationFailClosedWarningModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.ProductionProviderAcceptanceContractModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.VaultHeaderCommitmentPolicyModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.HkdfSha256KeyExpansionPolicyModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.HmacSha256HeaderCommitmentPrimitiveModeled
            ],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.KeyExpansionOutputLayoutModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.PrimitiveThreatModelRationaleModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.CanonicalHeaderByteVectorContractModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.HkdfSha256VectorContractModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.HmacSha256HeaderCommitmentVectorContractModeled
            ],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.CanonicalHeaderEncodingPolicyModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.KeySeparationLabelsPolicyModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.CanonicalHeaderSerializerImplementedAndVectorTested
            ],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.HkdfSha256KeyExpansionImplementedAndVectorTested
            ],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.HmacSha256HeaderCommitmentImplementedAndVectorTested
            ],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.StrictAadContractModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.StrictAadSerializationImplementedAndTested
            ],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.TinkRecordAeadBuildingBlockImplementedAndTested
            ],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.TinkNonKeyCommitmentMitigationModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.PassphraseEncodingPolicyModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.PassphrasePolicyValidationImplementedAndTested
            ],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[
                EncryptedVaultRequirement.Argon2idPassphraseRootDerivationImplementedAndTested
            ],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.TinkRawKeyFeasibilityPolicyModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.Argon2idBoundedCalibrationPolicyModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.Unresolved,
            readiness.requirementStatuses[EncryptedVaultRequirement.KdfParametersCalibrated],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.Absent,
            readiness.requirementStatuses[EncryptedVaultRequirement.ProviderBoundaryKnownAnswerVectorsPassed],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[EncryptedVaultRequirement.ManifestParserImplemented],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[EncryptedVaultRequirement.ManifestWriterImplemented],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.ImplementedStillDisabled,
            readiness.requirementStatuses[EncryptedVaultRequirement.StaleRecordDecisionPolicyImplemented],
        )
        assertTrue(EncryptedVaultRequirement.SecureSecretStorageAvailable in readiness.requirementStatuses)
        assertEquals(
            EncryptedVaultRequirementStatus.DisabledByPolicy,
            readiness.requirementStatuses[EncryptedVaultRequirement.SecureSecretStorageAvailable],
        )
        assertTrue(EncryptedVaultRequirement.SecureMetadataStorageAvailable in readiness.requirementStatuses)
        assertEquals(
            EncryptedVaultRequirementStatus.DisabledByPolicy,
            readiness.requirementStatuses[EncryptedVaultRequirement.SecureMetadataStorageAvailable],
        )
    }

    @Test
    fun platformPoliciesKeepAppControlledVaultPrimary() {
        val readiness = EncryptedVaultReadinessPolicy.disabled()
        val android = readiness.platformPolicies.single { it.platform == EncryptedVaultPlatform.Android }
        val linux = readiness.platformPolicies.single { it.platform == EncryptedVaultPlatform.LinuxDesktop }

        assertTrue(android.appControlledVaultIsPrimary)
        assertTrue(android.passphraseUnlockRequired)
        assertTrue(android.hardwareBackedWrappingOptional)
        assertFalse(android.osKeyringPrimaryStorageAllowed)
        assertFalse(android.platformWrappingRole.primaryStorage)
        assertTrue(readiness.androidCompatibilityPolicy.entropySourcePolicy.osCryptographicRandomnessAllowed)
        assertTrue(readiness.androidCompatibilityPolicy.entropySourcePolicy.hardwareBackedKeyProtectionPreferred)
        assertFalse(readiness.androidCompatibilityPolicy.entropySourcePolicy.hardwareBackedKeyProtectionRequired)

        assertTrue(linux.appControlledVaultIsPrimary)
        assertTrue(linux.passphraseUnlockRequired)
        assertFalse(linux.osKeyringPrimaryStorageAllowed)
        assertFalse(linux.platformWrappingRole.primaryStorage)
        assertTrue(linux.implementationNote.contains("passphrase-first"))
    }

    @Test
    fun torRoutingMetadataIsSensitiveMetadata() {
        assertContains(SensitiveMetadataKind.entries, SensitiveMetadataKind.TorRoutingMetadata)
        assertTrue(
            SecureMetadataPersistencePolicy.requiresEncryptedMetadataStorage(
                SensitiveMetadataKind.TorRoutingMetadata,
            ),
        )
    }

    @Test
    fun syncPreflightIncludesEncryptedVaultReadinessBlocker() {
        val wallet = wallet()
        val result = DisabledBitcoinWalletSyncService().sync(
            BitcoinWalletSyncRequest(
                backendProfile = null,
                wallet = wallet,
                candidate = displayedAddress(wallet),
                secureStorageCapability = secureStorage,
                encryptedVaultReadiness = EncryptedVaultReadinessPolicy.disabled(),
                secureMetadataCapability = secureMetadata,
            ),
        )

        assertContains(result.blockers, BitcoinWalletSyncBlocker.EncryptedVaultUnavailable)
        assertContains(result.blockers, BitcoinWalletSyncBlocker.SecureStorageUnavailable)
        assertContains(result.blockers, BitcoinWalletSyncBlocker.SecureMetadataPersistenceUnavailable)
        assertContains(result.warnings, BitcoinWalletSyncWarning.EncryptedVaultUnavailable)
        assertContains(result.warnings, BitcoinWalletSyncWarning.EncryptedVaultReadinessOnly)
        assertContains(result.capabilities, BitcoinWalletSyncCapability.EncryptedVaultReadinessBoundary)
        assertFalse(result.productionSyncEnabled)
        assertFalse(result.observationPersistenceEnabled)
    }

    private fun wallet(): ReceiveAddressWalletContext =
        ReceiveAddressWalletContext(
            profileId = DescriptorWalletProfileId("vault-readiness-wallet"),
            profileLabel = "Vault readiness placeholder",
            network = NetworkEnvironment.Regtest,
            source = ReceiveAddressSource.NativeDescriptor,
            operationalState = ReceiveAddressWalletOperationalState.OperationalDevelopmentWallet,
            canDeriveReceiveAddresses = true,
        )

    private fun displayedAddress(wallet: ReceiveAddressWalletContext): ReceiveAddressState =
        ReceiveAddressState.placeholderReserved(
            wallet = wallet,
            derivationIndex = ReceiveAddressDerivationIndex(0),
        ).markDisplayed()
}
