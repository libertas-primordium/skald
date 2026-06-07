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
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.BouncyCastleArgon2idApiRisksDocumented)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.SplitProviderBoundaryReviewDocumented)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.FutureProviderBoundaryPrerequisitesDocumented)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.DisabledProviderBoundaryModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.Argon2idCalibrationPolicyModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.Argon2idCandidateParameterPolicyModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.Argon2idCalibrationProbeOnly)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.PureJvmNoNativeLibraries)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.SplitProviderStack)
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.KnownAnswerVectorTestsMissing))
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.AndroidKnownAnswerVectorRuntimeMissing))
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.AndroidKnownAnswerVectorRuntimeUnverified))
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.AndroidRuntimeInstallConflict))
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.DependencyReviewIncomplete))
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.LicenseReviewIncomplete))
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.KdfCalibrationMissing)
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.KdfFinalParameterApprovalMissing)
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.RequiresSplitProviderDesign)
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.ProductionProviderBoundaryMissing))
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.ProductionProviderImplementationMissing)
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.TinkKeysetStorageHandlingReviewMissing))
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.SplitProviderBoundaryReviewMissing))
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.LockSessionLifecycleTestsMissing)
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
