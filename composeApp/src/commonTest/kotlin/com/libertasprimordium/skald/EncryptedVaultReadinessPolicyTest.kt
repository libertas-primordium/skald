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
import com.libertasprimordium.skald.security.EncryptedVaultBlockingIssue
import com.libertasprimordium.skald.security.EncryptedVaultCapability
import com.libertasprimordium.skald.security.EncryptedVaultDecisionRole
import com.libertasprimordium.skald.security.EncryptedVaultImplementationStatus
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
        assertContains(decision.blockers, EncryptedVaultBlockingIssue.ProviderKnownAnswerVectorsMissing)
        assertContains(decision.blockers, EncryptedVaultBlockingIssue.ProductionPersistenceDisabled)
        assertContains(decision.blockers, EncryptedVaultBlockingIssue.MainnetDisabled)
        assertContains(decision.warnings, EncryptedVaultWarning.ReadinessOnlyNoEncryption)
        assertContains(decision.warnings, EncryptedVaultWarning.KdfCalibrationProbeOnly)
        assertContains(decision.warnings, EncryptedVaultWarning.KdfCandidateParameterPolicyNotFinal)
        assertContains(readiness.capabilities, EncryptedVaultCapability.DisabledCryptoProviderBoundary)
        assertContains(readiness.capabilities, EncryptedVaultCapability.ProviderSelectionBoundaryModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.ProviderKatContractModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.TestOnlyProviderKatHarnessModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.Argon2idCalibrationPolicyModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.Argon2idCandidateParameterPolicyModel)
        assertContains(readiness.capabilities, EncryptedVaultCapability.AndroidCompatibilityEntropyPolicyModel)
        assertEquals(
            Argon2idCalibrationImplementationStatus.PolicyPresentProbeOnly,
            readiness.argon2idCalibrationPolicy.status,
        )
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
    }

    @Test
    fun productionReadinessRequiresAllReviewedImplementationGates() {
        val readiness = EncryptedVaultReadinessPolicy.disabled()
        val requiredGates = setOf(
            EncryptedVaultRequirement.DependencySelectionReviewed,
            EncryptedVaultRequirement.DisabledProviderBoundaryModeled,
            EncryptedVaultRequirement.ProviderSelectionBoundaryModeled,
            EncryptedVaultRequirement.ProviderKatContractModeled,
            EncryptedVaultRequirement.KdfCalibrationPolicyModeled,
            EncryptedVaultRequirement.KdfCandidateParameterPolicyModeled,
            EncryptedVaultRequirement.AndroidCompatibilityEntropyPolicyModeled,
            EncryptedVaultRequirement.RuntimeCryptoProviderChecksModeled,
            EncryptedVaultRequirement.RuntimeEntropyChecksModeled,
            EncryptedVaultRequirement.VaultCreationFailClosedWarningModeled,
            EncryptedVaultRequirement.KdfParametersCalibrated,
            EncryptedVaultRequirement.AeadImplementationVerified,
            EncryptedVaultRequirement.ProviderBoundaryKnownAnswerVectorsPassed,
            EncryptedVaultRequirement.KnownAnswerVectorsIdentified,
            EncryptedVaultRequirement.VaultContainerFormatImplemented,
            EncryptedVaultRequirement.VaultContainerParserImplemented,
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
            readiness.requirementStatuses[EncryptedVaultRequirement.KdfCalibrationPolicyModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.KdfCandidateParameterPolicyModeled],
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
            readiness.requirementStatuses[EncryptedVaultRequirement.RuntimeEntropyChecksModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.CandidateReviewedOnly,
            readiness.requirementStatuses[EncryptedVaultRequirement.VaultCreationFailClosedWarningModeled],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.Unresolved,
            readiness.requirementStatuses[EncryptedVaultRequirement.KdfParametersCalibrated],
        )
        assertEquals(
            EncryptedVaultRequirementStatus.Absent,
            readiness.requirementStatuses[EncryptedVaultRequirement.ProviderBoundaryKnownAnswerVectorsPassed],
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
