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
    fun selectedSplitStackIsPackagingProbeOnly() {
        val selected = VaultCryptoDependencyProbeCatalog.currentSpikeResults()
            .single { it.candidate == VaultCryptoDependencyCandidate.TinkBouncyCastleSplit }

        assertTrue(selected.pinnedArtifacts.contains("com.google.crypto.tink:tink-android:1.21.0"))
        assertTrue(selected.pinnedArtifacts.contains("com.google.crypto.tink:tink:1.21.0"))
        assertTrue(selected.pinnedArtifacts.contains("org.bouncycastle:bcprov-jdk18on:1.84"))
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.Argon2idApiPresent)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.XChaCha20Poly1305ApiPresent)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.DesktopKnownAnswerVectorsPass)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.KnownAnswerVectorReviewRequired)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.PureJvmNoNativeLibraries)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.SplitProviderStack)
        assertFalse(selected.blockers.contains(VaultCryptoDependencyBlocker.KnownAnswerVectorTestsMissing))
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.AndroidKnownAnswerVectorRuntimeMissing)
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.RequiresSplitProviderDesign)
        assertContains(selected.blockers, VaultCryptoDependencyBlocker.VaultImplementationStillDisabled)
        assertFalse(selected.implementationEnabled)
        assertFalse(selected.storageEnabled)
        assertFalse(selected.productionPersistenceEnabled)
        assertFalse(selected.readyForVaultImplementation)
    }

    @Test
    fun otherCandidatesRemainDeferredOrRejected() {
        val results = VaultCryptoDependencyProbeCatalog.currentSpikeResults().associateBy { it.candidate }

        assertTrue(results.getValue(VaultCryptoDependencyCandidate.LibsodiumKmp).status == VaultCryptoDependencyProbeStatus.DeferredPendingReview)
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
