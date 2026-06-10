package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.EncryptedVaultReadinessPolicy
import com.libertasprimordium.skald.security.VaultCryptoDependencyBlocker
import com.libertasprimordium.skald.security.VaultCryptoDependencyCandidate
import com.libertasprimordium.skald.security.VaultCryptoDependencyCapability
import com.libertasprimordium.skald.security.VaultCryptoDependencyProbeCatalog
import com.libertasprimordium.skald.security.VaultCryptoDependencyProbeStatus
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class VaultCryptoDependencyProbeTest {
    @Test
    fun selectedSplitStackIsReviewedCandidateOnly() {
        val selected = VaultCryptoDependencyProbeCatalog.currentSpikeResults()
            .single { it.candidate == VaultCryptoDependencyCandidate.TinkBouncyCastleSplit }

        assertTrue(selected.status == VaultCryptoDependencyProbeStatus.DependencyLicenseAndKeysetReviewCompleteCandidate)
        assertTrue(selected.pinnedArtifacts.contains("com.google.crypto.tink:tink-android:1.21.0"))
        assertTrue(selected.pinnedArtifacts.contains("com.google.crypto.tink:tink:1.21.0"))
        assertTrue(selected.pinnedArtifacts.contains("org.bouncycastle:bcprov-jdk18on:1.84"))
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.Argon2idApiPresent)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.XChaCha20Poly1305ApiPresent)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.DesktopKnownAnswerVectorsPass)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.DesktopRuntimeProbePass)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.AndroidKnownAnswerVectorTestsPresent)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.AndroidKnownAnswerVectorsPass)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.AndroidTestApkAssemblyPass)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.KnownAnswerVectorReviewRequired)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.DependencyInventoryReviewed)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.LicenseDeclarationsInspected)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.PackageInventoriesReviewed)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.TinkKeysetStorageReviewDocumented)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.TinkRawKeyFeasibilityPublicApiProbePass)
        assertContains(
            selected.capabilities,
            VaultCryptoDependencyCapability.AndroidTinkRawKeyFeasibilityPublicApiProbePresent,
        )
        assertContains(
            selected.capabilities,
            VaultCryptoDependencyCapability.AndroidTinkRawKeyFeasibilityPublicApiProbePass,
        )
        assertContains(
            selected.capabilities,
            VaultCryptoDependencyCapability.CrossPlatformTinkRawKeyFeasibilityPublicApiProbePass,
        )
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.BouncyCastleArgon2idApiRisksDocumented)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.SplitProviderBoundaryReviewDocumented)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.FutureProviderBoundaryPrerequisitesDocumented)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.DisabledProviderBoundaryModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.ProviderSelectionBoundaryModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.ProviderKatContractModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.ProviderLevelKatStrategyContractModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.RandomizedAeadBehavioralKatPolicyModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.IntegratedVerificationOrderKatPolicyModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.StaleRecordManifestPolicyModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.TestOnlyProviderKatHarnessPresent)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.StillDisabledProviderIntegrationHarnessTested)
        assertContains(
            selected.capabilities,
            VaultCryptoDependencyCapability.ProviderLevelKatsExecutedInStillDisabledHarness,
        )
        assertContains(
            selected.capabilities,
            VaultCryptoDependencyCapability.RandomizedAeadBehavioralKatsExecutedInStillDisabledHarness,
        )
        assertContains(
            selected.capabilities,
            VaultCryptoDependencyCapability.IntegratedVerificationOrderKatsExecutedInStillDisabledHarness,
        )
        assertContains(
            selected.capabilities,
            VaultCryptoDependencyCapability.StillDisabledProviderFacadeBoundaryModeled,
        )
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.VaultContainerContractModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.InMemoryVaultContainerParserWriterTested)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.ManifestContractModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.InMemoryManifestParserWriterTested)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.LocalManifestStaleRecordDecisionPolicyTested)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.StoragePolicyContractModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.PlatformStorageBoundaryContractModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.AtomicityCrashRecoveryContractModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.AtomicWriteStrategyContractModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.CrashRecoveryContractModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.StorageInterruptionTestContractModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.InMemoryStorageAtomicityCrashSimulatorTested)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.StorageFailureModelContractModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.StorageNamespacePathHygieneContractModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.StorageNamespacePathPolicyImplementedTested)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.StorageLayoutPlanImplementedTested)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.PathContainmentPlannerImplementedTested)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.PlatformRootSettingsPolicyModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.AndroidAppPrivateRootPolicyModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.LinuxRootSettingsPolicyModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.LinuxCustomRootValidationPolicyImplementedTested)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.LinuxRootResolutionPolicyImplementedTested)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.PlatformRootResolverBoundaryImplementedTested)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.PlatformPathConstructionBoundaryImplementedTested)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.StorageSafetyPreflightBoundaryImplementedTested)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.DisabledStorageServiceFacadeImplementedTested)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.StorageOperationFailureVocabularyModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.PersistenceReadinessGateImplementedTested)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.PersistenceReadinessFailureVocabularyModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.LockSessionLifecycleBoundaryImplementedTested)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.LockSessionFailureVocabularyModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.OsKeyringPassphraseStorageRejected)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.PasswordManagerIntegrationRejected)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.PassphraseFirstVaultAuthorityModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.DurabilityFailClosedPolicyModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.WarningOnlyDurabilityRejectionModeled)
        assertContains(
            selected.capabilities,
            VaultCryptoDependencyCapability.RollbackLimitationAntiRollbackAnchorModeled,
        )
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.SecureStorageBoundaryContractModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.Argon2idCalibrationPolicyModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.Argon2idCandidateParameterPolicyModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.AndroidCompatibilityEntropyPolicyModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.RuntimeRandomnessProviderChecksModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.ProductionProviderAcceptanceContractModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.VaultHeaderCommitmentPolicyModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.HkdfSha256KeyExpansionPolicyModeled)
        assertContains(
            selected.capabilities,
            VaultCryptoDependencyCapability.HmacSha256HeaderCommitmentPrimitiveModeled,
        )
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.KeyExpansionOutputLayoutModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.PrimitiveThreatModelRationaleModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.CanonicalHeaderByteVectorContractModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.HkdfSha256VectorContractModeled)
        assertContains(
            selected.capabilities,
            VaultCryptoDependencyCapability.HmacSha256HeaderCommitmentVectorContractModeled,
        )
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.CanonicalHeaderEncodingPolicyModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.KeySeparationLabelsPolicyModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.CanonicalHeaderSerializerVectorTested)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.HkdfSha256KeyExpansionVectorTested)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.HmacSha256HeaderCommitmentVectorTested)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.StrictAadContractPolicyModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.StrictAadSerializationVectorTested)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.TinkRecordAeadBuildingBlockTested)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.TinkNonKeyCommitmentMitigationModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.PassphraseEncodingPolicyModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.PassphrasePolicyValidationImplemented)
        assertContains(
            selected.capabilities,
            VaultCryptoDependencyCapability.Argon2idPassphraseRootDerivationVectorTested,
        )
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.TinkRawKeyFeasibilityPolicyModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.Argon2idBoundedCalibrationPolicyModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.Argon2idCalibrationPolicyImplementedTested)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.Argon2idCandidateSelectionPolicyImplementedTested)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.Argon2idMemoryFailureHandlingModeledTested)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.Argon2idStoredParameterNoDowngradeModeledTested)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.Argon2idCalibrationProbeOnly)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.PureJvmNoNativeLibraries)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.SplitProviderStack)
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.KnownAnswerVectorTestsMissing))
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.AndroidKnownAnswerVectorRuntimeMissing))
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.AndroidKnownAnswerVectorRuntimeUnverified))
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.AndroidRuntimeInstallConflict))
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.DependencyReviewIncomplete))
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.LicenseReviewIncomplete))
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.KdfCalibrationMissing))
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.KdfFinalParameterApprovalMissing)
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.RequiresSplitProviderDesign)
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.ProductionProviderBoundaryMissing))
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.ProductionProviderImplementationMissing)
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.ProviderSelectionProductionBlocked)
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.ProductionProviderAcceptanceContractIncomplete)
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.ProviderLevelKatExecutionMissing))
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.ProviderLevelKatStrategyContractOnly))
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.RandomizedAeadBehavioralKatExecutionMissing))
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.IntegratedVerificationOrderKatExecutionMissing))
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.StillDisabledProviderIntegrationHarnessNotSelectable)
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.StillDisabledProviderFacadeNotSelectable)
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.TinkRawKeyFeasibilityApprovalMissing))
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.VaultHeaderCommitmentImplementationMissing))
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.HkdfSha256KeyExpansionImplementationMissing))
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.HmacSha256HeaderCommitmentImplementationMissing))
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.KeyExpansionOutputLayoutImplementationMissing))
        assertFalse(
            selected.blockers.contains(
                VaultCryptoDependencyBlocker.CanonicalHeaderByteVectorProductionImplementationMissing,
            ),
        )
        assertFalse(
            selected.blockers.contains(
                VaultCryptoDependencyBlocker.HkdfSha256VectorProductionImplementationMissing,
            ),
        )
        assertFalse(
            selected.blockers.contains(
                VaultCryptoDependencyBlocker.HmacSha256HeaderCommitmentVectorProductionImplementationMissing,
            ),
        )
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.CanonicalHeaderEncodingImplementationMissing))
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.KeySeparationImplementationMissing))
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.VaultHeaderCommitmentProviderIntegrationMissing))
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.HkdfSha256KeyExpansionProviderIntegrationMissing))
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.HmacSha256HeaderCommitmentProviderIntegrationMissing))
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.KeyExpansionOutputLayoutProviderIntegrationMissing))
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.CanonicalHeaderSerializerProviderIntegrationMissing))
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.KeySeparationProviderIntegrationMissing))
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.StrictAadProviderIntegrationMissing))
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.TinkRecordAeadProviderIntegrationMissing))
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.StaleRecordManifestIntegrationMissing)
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.StaleRecordManifestPolicyImplementationMissing))
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.VaultContainerPersistenceImplementationMissing)
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.ManifestReadWriteImplementationMissing)
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.StorageSuccessPathAbsent)
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.DisabledStorageServiceFacadeStillDisabled)
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.PersistenceReadinessGateStillBlocked)
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.PlatformStorageImplementationMissing)
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.AtomicWriteImplementationMissing)
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.CrashRecoveryImplementationMissing)
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.StorageInterruptionTestsMissing)
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.StorageFailureRuntimeMappingMissing)
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.StorageNamespacePathImplementationMissing))
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.StoragePathConstructionImplementationMissing)
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.PlatformRootSettingsImplementationMissing)
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.SettingsUiImplementationMissing)
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.SettingsPersistenceImplementationMissing)
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.LinuxCustomRootValidationMissing))
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.DurabilityFailClosedRuntimeEvidenceMissing)
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.WarningOnlyDurabilityPersistenceRejected)
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.AntiRollbackAnchorAbsentNoFullRollbackClaim)
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.ManifestStorageAtomicityReviewMissing)
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.PassphraseEncodingProductionValidationMissing))
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.PassphrasePolicyProviderIntegrationMissing))
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.Argon2idRootDerivationProviderIntegrationMissing))
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.Argon2idBoundedCalibrationApprovalMissing)
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.TinkKeysetStorageHandlingReviewMissing))
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.SplitProviderBoundaryReviewMissing))
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.LockSessionLifecycleStillDisabled)
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.LockSessionLifecycleTestsMissing))
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.MigrationCorruptionTestsMissing)
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.ProductionStorageReviewMissing)
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.MainnetReleaseHardeningReviewMissing)
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.VaultImplementationStillDisabled)
        assertFalse(selected.implementationEnabled)
        assertFalse(selected.storageEnabled)
        assertFalse(selected.productionPersistenceEnabled)
        assertFalse(selected.readyForVaultImplementation)
    }

    @Test
    fun libsodiumJavaAndroidComparisonIsRejectedForCurrentVaultPackaging() {
        val result = VaultCryptoDependencyProbeCatalog.currentSpikeResults()
            .single { it.candidate == VaultCryptoDependencyCandidate.LazysodiumJavaAndroid }

        assertTrue(result.status == VaultCryptoDependencyProbeStatus.RejectedForCurrentVault)
        assertTrue(result.pinnedArtifacts.contains("com.goterl:lazysodium-java:5.2.0"))
        assertTrue(result.pinnedArtifacts.contains("com.goterl:lazysodium-android:5.2.0"))
        assertContains(result.capabilities, VaultCryptoDependencyCapability.Argon2idApiPresent)
        assertContains(result.capabilities, VaultCryptoDependencyCapability.XChaCha20Poly1305ApiPresent)
        assertContains(result.capabilities, VaultCryptoDependencyCapability.SecretStreamApiPresent)
        assertContains(result.capabilities, VaultCryptoDependencyCapability.SinglePrimitiveFamily)
        assertContains(result.capabilities, VaultCryptoDependencyCapability.NativeLibrariesRequired)
        assertContains(result.capabilities, VaultCryptoDependencyCapability.AndroidNativeAbiLibrariesPresent)
        assertContains(result.capabilities, VaultCryptoDependencyCapability.LinuxNativeLibrariesPresent)
        assertContains(result.blockers, VaultCryptoDependencyBlocker.KdfFinalParameterApprovalMissing)
        assertContains(result.blockers, VaultCryptoDependencyBlocker.AndroidDuplicateJnaClasspath)
        assertContains(result.blockers, VaultCryptoDependencyBlocker.NativePackagingUnverified)
        assertContains(result.blockers, VaultCryptoDependencyBlocker.NativePackagingReviewRequired)
        assertContains(result.blockers, VaultCryptoDependencyBlocker.NativeLoaderRuntimeRisk)
        assertContains(result.blockers, VaultCryptoDependencyBlocker.KnownAnswerVectorTestsMissing)
        assertContains(result.blockers, VaultCryptoDependencyBlocker.AndroidKnownAnswerVectorRuntimeMissing)
        assertContains(result.blockers, VaultCryptoDependencyBlocker.ProductionProviderBoundaryMissing)
        assertFalse(result.implementationEnabled)
        assertFalse(result.storageEnabled)
        assertFalse(result.productionPersistenceEnabled)
        assertFalse(result.readyForVaultImplementation)
    }

    @Test
    fun otherCandidatesRemainDeferredOrRejected() {
        val results = VaultCryptoDependencyProbeCatalog.currentSpikeResults().associateBy { it.candidate }

        assertTrue(results.getValue(VaultCryptoDependencyCandidate.IonSpinKmpLibsodium).status == VaultCryptoDependencyProbeStatus.DeferredAfterComparison)
        assertContains(
            results.getValue(VaultCryptoDependencyCandidate.IonSpinKmpLibsodium).blockers,
            VaultCryptoDependencyBlocker.KotlinMultiplatformCompatibilityUnverified,
        )
        assertTrue(results.getValue(VaultCryptoDependencyCandidate.BouncyCastleOnly).status == VaultCryptoDependencyProbeStatus.InsufficientAsPrimaryVaultStack)
        assertTrue(results.getValue(VaultCryptoDependencyCandidate.PlatformCryptoOnly).status == VaultCryptoDependencyProbeStatus.RejectedAsDefaultVaultStack)
        assertContains(
            results.getValue(VaultCryptoDependencyCandidate.PlatformCryptoOnly).blockers,
            VaultCryptoDependencyBlocker.DoesNotProvideArgon2id,
        )
        assertContains(
            results.getValue(VaultCryptoDependencyCandidate.BouncyCastleOnly).blockers,
            VaultCryptoDependencyBlocker.DoesNotProvidePreferredAead,
        )
    }

    @Test
    fun dependencyProbeDoesNotMakeVaultReady() {
        val readiness = EncryptedVaultReadinessPolicy.disabled()
        val selected = VaultCryptoDependencyProbeCatalog.currentSpikeResults()
            .single { it.candidate == VaultCryptoDependencyCandidate.TinkBouncyCastleSplit }

        assertFalse(readiness.readyForProductionPersistence)
        assertFalse(readiness.secureSecretStorageAvailable)
        assertFalse(readiness.secureMetadataStorageAvailable)
        assertFalse(selected.readyForVaultImplementation)
    }
}
