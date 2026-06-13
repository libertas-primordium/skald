package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.Argon2idVersion
import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.EncryptedVaultAeadAlgorithm
import com.libertasprimordium.skald.security.ProductionProviderAadFailClosedCondition
import com.libertasprimordium.skald.security.ProductionProviderAadBindingField
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceBlocker
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidence
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidenceState
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceGate
import com.libertasprimordium.skald.security.ProductionProviderAndroidTinkRawKeyFeasibilityStatus
import com.libertasprimordium.skald.security.ProductionProviderCanonicalHeaderEncodingRule
import com.libertasprimordium.skald.security.ProductionProviderClearWipeStrategyBoundaryRule
import com.libertasprimordium.skald.security.ProductionProviderCreationAuthorizationBoundaryRule
import com.libertasprimordium.skald.security.ProductionProviderConstructionContractStatus
import com.libertasprimordium.skald.security.ProductionProviderDisabledStorageServiceFacadeRule
import com.libertasprimordium.skald.security.ProductionProviderDeterministicKatVector
import com.libertasprimordium.skald.security.ProductionProviderDurabilityCapabilityRule
import com.libertasprimordium.skald.security.ProductionProviderDurabilityFailClosedCondition
import com.libertasprimordium.skald.security.ProductionProviderHeaderCommitmentFailClosedCondition
import com.libertasprimordium.skald.security.ProductionProviderHeaderCommitmentField
import com.libertasprimordium.skald.security.ProductionProviderHeaderCommitmentPrimitive
import com.libertasprimordium.skald.security.ProductionProviderIntegratedVerificationOrderKatStep
import com.libertasprimordium.skald.security.ProductionProviderKeyExpansionPrimitive
import com.libertasprimordium.skald.security.ProductionProviderKeySeparationLabel
import com.libertasprimordium.skald.security.ProductionProviderLinuxCustomRootValidationRule
import com.libertasprimordium.skald.security.ProductionProviderLinuxRootResolutionRule
import com.libertasprimordium.skald.security.ProductionProviderLockSessionLifecycleRule
import com.libertasprimordium.skald.security.ProductionProviderManifestField
import com.libertasprimordium.skald.security.ProductionProviderMigrationCorruptionBoundaryRule
import com.libertasprimordium.skald.security.ProductionProviderPassphraseAllowedClass
import com.libertasprimordium.skald.security.ProductionProviderPassphraseForbiddenClass
import com.libertasprimordium.skald.security.ProductionProviderPassphraseNoTransformRule
import com.libertasprimordium.skald.security.ProductionProviderPassphrasePolicyBoundaryRule
import com.libertasprimordium.skald.security.ProductionProviderPathContainmentPlannerRule
import com.libertasprimordium.skald.security.ProductionProviderPlatformPathConstructionRule
import com.libertasprimordium.skald.security.ProductionProviderPlatformRootSettingsRule
import com.libertasprimordium.skald.security.ProductionProviderPlatformRootResolverRule
import com.libertasprimordium.skald.security.ProductionProviderPrimitiveRole
import com.libertasprimordium.skald.security.ProductionProviderRandomizedAeadBehavioralKatCheck
import com.libertasprimordium.skald.security.ProductionProviderRedactionLeakageRule
import com.libertasprimordium.skald.security.ProductionProviderAtomicWritePhase
import com.libertasprimordium.skald.security.ProductionProviderAtomicWriteRequirement
import com.libertasprimordium.skald.security.ProductionProviderCrashRecoveryCheck
import com.libertasprimordium.skald.security.ProductionProviderCrashRecoveryFailClosedState
import com.libertasprimordium.skald.security.ProductionProviderSecureStorageBoundaryRequirement
import com.libertasprimordium.skald.security.ProductionProviderStaleRecordManifestBinding
import com.libertasprimordium.skald.security.ProductionProviderStaleRecordManifestRequirement
import com.libertasprimordium.skald.security.ProductionProviderStorageBoundaryAllowedBytes
import com.libertasprimordium.skald.security.ProductionProviderStorageBoundaryForbiddenMaterial
import com.libertasprimordium.skald.security.ProductionProviderStorageBoundaryRequirement
import com.libertasprimordium.skald.security.ProductionProviderStorageAtomicityRequirement
import com.libertasprimordium.skald.security.ProductionProviderStorageFailureCategory
import com.libertasprimordium.skald.security.ProductionProviderStorageLayoutPlanRule
import com.libertasprimordium.skald.security.ProductionProviderStorageNamespacePathRule
import com.libertasprimordium.skald.security.ProductionProviderStorageSafetyPreflightRule
import com.libertasprimordium.skald.security.ProductionProviderSuiteModel
import com.libertasprimordium.skald.security.ProductionProviderTamperCoverage
import com.libertasprimordium.skald.security.ProductionProviderTestVectorContractStatus
import com.libertasprimordium.skald.security.ProductionProviderTinkRawKeyFeasibilityStatus
import com.libertasprimordium.skald.security.ProductionProviderVaultContainerField
import com.libertasprimordium.skald.security.ProductionProviderVaultContainerRequirement
import com.libertasprimordium.skald.security.ProductionProviderWeakDeviceFailureMode
import com.libertasprimordium.skald.security.ProductionProviderWarningOnlyDurabilityRule
import com.libertasprimordium.skald.security.RuntimeRandomnessSourceKind
import com.libertasprimordium.skald.security.SkaldVaultV1Argon2idRootDerivation
import com.libertasprimordium.skald.security.SkaldVaultV1Argon2idType
import com.libertasprimordium.skald.security.SkaldVaultV1ClearWipeStrategyPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1CreationAuthorizationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1DisabledStorageServiceFacade
import com.libertasprimordium.skald.security.SkaldVaultV1LinuxCustomRootValidationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1LinuxRootResolutionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1LockSessionLifecyclePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1MigrationCorruptionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1PassphrasePolicyGate
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformPathConstructionPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformRootResolverPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1PlatformRootSettingsPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1RedactionLeakagePolicy
import com.libertasprimordium.skald.security.SkaldVaultV1StorageSafetyPreflightPolicy
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderImplementationState
import com.libertasprimordium.skald.security.VaultCryptoProviderProductionApprovalGate
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionBlocker
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRequest
import com.libertasprimordium.skald.security.commonProductionProviderAcceptanceContract
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class ProductionProviderAcceptanceContractTest {
    private val contract = commonProductionProviderAcceptanceContract()

    @Test
    fun v1SuiteIdentityIsPinnedAndNotPluggable() {
        val suite = contract.suite

        assertEquals(
            "skald-vault-v1-bouncycastle-argon2id-tink-xchacha20poly1305-os-securerandom",
            suite.suiteId,
        )
        assertEquals(ProductionProviderSuiteModel.SinglePinnedSuite, suite.model)
        assertFalse(suite.model.providerAgilityAllowedInV1)
        assertEquals(ProductionProviderPrimitiveRole.Kdf, suite.kdf.role)
        assertEquals("Bouncy Castle", suite.kdf.implementation)
        assertEquals("Argon2id", suite.kdf.algorithm)
        assertEquals("Argon2id version 19", suite.kdf.versionOrTemplate)
        assertEquals("org.bouncycastle:bcprov-jdk18on:1.84", suite.kdf.artifact)
        assertEquals(ProductionProviderPrimitiveRole.Aead, suite.aead.role)
        assertEquals("Tink", suite.aead.implementation)
        assertEquals("XChaCha20-Poly1305", suite.aead.algorithm)
        assertEquals("com.google.crypto.tink:tink/tink-android:1.21.0", suite.aead.artifact)
        assertEquals(ProductionProviderPrimitiveRole.RuntimeRandomness, suite.runtimeRandomness.role)
        assertEquals("OS SecureRandom", suite.runtimeRandomness.implementation)
        assertContains(suite.pinnedArtifacts, "org.bouncycastle:bcprov-jdk18on:1.84")
        assertContains(suite.pinnedArtifacts, "com.google.crypto.tink:tink:1.21.0")
        assertContains(suite.pinnedArtifacts, "com.google.crypto.tink:tink-android:1.21.0")
    }

    @Test
    fun currentDesignOnlyEvidenceIsIncompleteAndNonSelectable() {
        val assessment = contract.assess()

        assertFalse(assessment.allRequiredGatesSatisfied)
        assertFalse(assessment.productionProviderSelectable)
        assertFalse(assessment.productionPersistenceAllowed)
        assertContains(assessment.blockers, ProductionProviderAcceptanceBlocker.UnknownGateEvidence)
        assertContains(assessment.blockers, ProductionProviderAcceptanceBlocker.ModelOnlyGateEvidence)
        assertContains(
            assessment.blockers,
            ProductionProviderAcceptanceBlocker.ProductionProviderSelectionStillDisabled,
        )
        assertContains(
            assessment.blockers,
            ProductionProviderAcceptanceBlocker.ProductionPersistenceStillDisabled,
        )
        assertNotNull(assessment.userFacingWarning)
    }

    @Test
    fun creationAuthorizationBoundaryEvidenceIsModeledAndStillDisabled() {
        val evidence = ProductionProviderAcceptanceEvidence.currentDesignOnly()
        val storageContract = contract.containerManifestStorageContract

        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            evidence.stateFor(ProductionProviderAcceptanceGate.CreationAuthorizationBoundaryImplementedAndTested),
        )
        assertEquals(
            SkaldVaultV1CreationAuthorizationPolicy.POLICY_ID,
            storageContract.creationAuthorizationBoundaryPolicyId,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            storageContract.creationAuthorizationBoundaryStatus,
        )
        assertEquals(
            ProductionProviderCreationAuthorizationBoundaryRule.entries.toSet(),
            storageContract.creationAuthorizationBoundaryRules,
        )
        assertTrue(storageContract.creationAuthorizationBoundaryModeled)
        assertTrue(storageContract.creationAuthorizationStillDisabled)
        assertTrue(storageContract.creationAuthorizationBlocksAllOperations)
        assertTrue(storageContract.creationAuthorizationDoesNotAcceptPassphrases)
        assertTrue(storageContract.creationAuthorizationDoesNotGenerateKeys)
        assertTrue(storageContract.creationAuthorizationDoesNotRunKdf)
        assertTrue(storageContract.creationAuthorizationDoesNotWriteStorage)
        assertTrue(storageContract.creationAuthorizationDoesNotCreateSession)
        assertTrue(storageContract.creationAuthorizationDoesNotEnablePersistence)
        assertTrue(storageContract.creationAuthorizationDoesNotEnableProviderSelection)
        assertTrue(storageContract.creationAuthorizationFailureVocabularyModeled)
        assertFalse(storageContract.vaultPersistenceImplemented)
    }

    @Test
    fun failedOrPartialEvidenceBlocksContractSatisfaction() {
        val failedEvidence = ProductionProviderAcceptanceEvidence(
            gateStates = ProductionProviderAcceptanceGate.entries.associateWith {
                ProductionProviderAcceptanceEvidenceState.Satisfied
            } + mapOf(
                ProductionProviderAcceptanceGate.AeadKatsPassed to
                    ProductionProviderAcceptanceEvidenceState.Failed,
            ),
        )
        val partialEvidence = ProductionProviderAcceptanceEvidence(
            gateStates = mapOf(
                ProductionProviderAcceptanceGate.ExactProviderSuitePinned to
                    ProductionProviderAcceptanceEvidenceState.Satisfied,
                ProductionProviderAcceptanceGate.Argon2idPolicyApproved to
                    ProductionProviderAcceptanceEvidenceState.Missing,
            ),
        )

        val failed = contract.assess(failedEvidence)
        val partial = contract.assess(partialEvidence)

        assertFalse(failed.allRequiredGatesSatisfied)
        assertContains(failed.blockers, ProductionProviderAcceptanceBlocker.FailedGateEvidence)
        assertFalse(failed.productionProviderSelectable)
        assertFalse(failed.productionPersistenceAllowed)

        assertFalse(partial.allRequiredGatesSatisfied)
        assertContains(partial.blockers, ProductionProviderAcceptanceBlocker.MissingGateEvidence)
        assertContains(partial.blockers, ProductionProviderAcceptanceBlocker.UnknownGateEvidence)
        assertFalse(partial.productionProviderSelectable)
        assertFalse(partial.productionPersistenceAllowed)
    }

    @Test
    fun headerCommitmentEvidenceMustBeKnownAndSatisfied() {
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.HeaderCommitmentPolicyApproved,
            state = ProductionProviderAcceptanceEvidenceState.Missing,
            blocker = ProductionProviderAcceptanceBlocker.MissingGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.HeaderCommitmentPolicyApproved,
            state = ProductionProviderAcceptanceEvidenceState.Unknown,
            blocker = ProductionProviderAcceptanceBlocker.UnknownGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.HeaderCommitmentPolicyApproved,
            state = ProductionProviderAcceptanceEvidenceState.Failed,
            blocker = ProductionProviderAcceptanceBlocker.FailedGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.HeaderCommitmentPolicyApproved,
            state = ProductionProviderAcceptanceEvidenceState.Unsupported,
            blocker = ProductionProviderAcceptanceBlocker.UnsupportedGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.HeaderCommitmentPolicyApproved,
            state = ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
            blocker = ProductionProviderAcceptanceBlocker.ModelOnlyGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.VaultKeyCommitmentHeaderAuthenticationImplemented,
            state = ProductionProviderAcceptanceEvidenceState.Missing,
            blocker = ProductionProviderAcceptanceBlocker.MissingGateEvidence,
        )
    }

    @Test
    fun canonicalHeaderEncodingEvidenceMustBeKnownImplementedAndSatisfied() {
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.CanonicalHeaderEncodingPolicyApproved,
            state = ProductionProviderAcceptanceEvidenceState.Missing,
            blocker = ProductionProviderAcceptanceBlocker.MissingGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.CanonicalHeaderEncodingPolicyApproved,
            state = ProductionProviderAcceptanceEvidenceState.Unknown,
            blocker = ProductionProviderAcceptanceBlocker.UnknownGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.CanonicalHeaderEncodingPolicyApproved,
            state = ProductionProviderAcceptanceEvidenceState.Failed,
            blocker = ProductionProviderAcceptanceBlocker.FailedGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.CanonicalHeaderEncodingPolicyApproved,
            state = ProductionProviderAcceptanceEvidenceState.Unsupported,
            blocker = ProductionProviderAcceptanceBlocker.UnsupportedGateEvidence,
        )
    }

    @Test
    fun canonicalHeaderVectorEvidenceMustBeCompleteAndStillNonProduction() {
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.CanonicalHeaderByteVectorsApproved,
            state = ProductionProviderAcceptanceEvidenceState.Missing,
            blocker = ProductionProviderAcceptanceBlocker.MissingGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.CanonicalHeaderByteVectorsApproved,
            state = ProductionProviderAcceptanceEvidenceState.Unknown,
            blocker = ProductionProviderAcceptanceBlocker.UnknownGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.CanonicalHeaderByteVectorsApproved,
            state = ProductionProviderAcceptanceEvidenceState.VectorInputsDefinedOutputsPending,
            blocker = ProductionProviderAcceptanceBlocker.PendingVectorEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.CanonicalHeaderByteVectorsApproved,
            state = ProductionProviderAcceptanceEvidenceState.Failed,
            blocker = ProductionProviderAcceptanceBlocker.FailedGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.CanonicalHeaderByteVectorsApproved,
            state = ProductionProviderAcceptanceEvidenceState.Unsupported,
            blocker = ProductionProviderAcceptanceBlocker.UnsupportedGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.CanonicalHeaderByteVectorsApproved,
            state = ProductionProviderAcceptanceEvidenceState.TestScopeVectorsComplete,
            blocker = ProductionProviderAcceptanceBlocker.TestScopeVectorEvidenceOnly,
        )
    }

    @Test
    fun hkdfAndHmacVectorEvidenceMustBeCompleteAndStillNonProduction() {
        listOf(
            ProductionProviderAcceptanceGate.HkdfSha256VectorContractApproved,
            ProductionProviderAcceptanceGate.HmacSha256HeaderCommitmentVectorContractApproved,
        ).forEach { gate ->
            assertGateBlocks(gate, ProductionProviderAcceptanceEvidenceState.Missing, ProductionProviderAcceptanceBlocker.MissingGateEvidence)
            assertGateBlocks(gate, ProductionProviderAcceptanceEvidenceState.Unknown, ProductionProviderAcceptanceBlocker.UnknownGateEvidence)
            assertGateBlocks(
                gate,
                ProductionProviderAcceptanceEvidenceState.VectorInputsDefinedOutputsPending,
                ProductionProviderAcceptanceBlocker.PendingVectorEvidence,
            )
            assertGateBlocks(gate, ProductionProviderAcceptanceEvidenceState.Failed, ProductionProviderAcceptanceBlocker.FailedGateEvidence)
            assertGateBlocks(gate, ProductionProviderAcceptanceEvidenceState.Unsupported, ProductionProviderAcceptanceBlocker.UnsupportedGateEvidence)
            assertGateBlocks(
                gate,
                ProductionProviderAcceptanceEvidenceState.TestScopeVectorsComplete,
                ProductionProviderAcceptanceBlocker.TestScopeVectorEvidenceOnly,
            )
        }
    }

    @Test
    fun keySeparationEvidenceMustBeKnownImplementedAndSatisfied() {
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.KeySeparationLabelsPolicyApproved,
            state = ProductionProviderAcceptanceEvidenceState.Missing,
            blocker = ProductionProviderAcceptanceBlocker.MissingGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.KeySeparationLabelsPolicyApproved,
            state = ProductionProviderAcceptanceEvidenceState.Unknown,
            blocker = ProductionProviderAcceptanceBlocker.UnknownGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.KeySeparationLabelsPolicyApproved,
            state = ProductionProviderAcceptanceEvidenceState.Failed,
            blocker = ProductionProviderAcceptanceBlocker.FailedGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.KeySeparationLabelsPolicyApproved,
            state = ProductionProviderAcceptanceEvidenceState.Unsupported,
            blocker = ProductionProviderAcceptanceBlocker.UnsupportedGateEvidence,
        )
    }

    @Test
    fun hkdfSha256KeyExpansionPrimitiveEvidenceMustBeKnownImplementedAndSatisfied() {
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.HkdfSha256KeyExpansionPrimitiveApproved,
            state = ProductionProviderAcceptanceEvidenceState.Missing,
            blocker = ProductionProviderAcceptanceBlocker.MissingGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.HkdfSha256KeyExpansionPrimitiveApproved,
            state = ProductionProviderAcceptanceEvidenceState.Unknown,
            blocker = ProductionProviderAcceptanceBlocker.UnknownGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.HkdfSha256KeyExpansionPrimitiveApproved,
            state = ProductionProviderAcceptanceEvidenceState.Failed,
            blocker = ProductionProviderAcceptanceBlocker.FailedGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.HkdfSha256KeyExpansionPrimitiveApproved,
            state = ProductionProviderAcceptanceEvidenceState.Unsupported,
            blocker = ProductionProviderAcceptanceBlocker.UnsupportedGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.HkdfSha256KeyExpansionPrimitiveApproved,
            state = ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
            blocker = ProductionProviderAcceptanceBlocker.ModelOnlyGateEvidence,
        )
    }

    @Test
    fun hmacSha256HeaderCommitmentPrimitiveEvidenceMustBeKnownImplementedAndSatisfied() {
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.HmacSha256HeaderCommitmentPrimitiveApproved,
            state = ProductionProviderAcceptanceEvidenceState.Missing,
            blocker = ProductionProviderAcceptanceBlocker.MissingGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.HmacSha256HeaderCommitmentPrimitiveApproved,
            state = ProductionProviderAcceptanceEvidenceState.Unknown,
            blocker = ProductionProviderAcceptanceBlocker.UnknownGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.HmacSha256HeaderCommitmentPrimitiveApproved,
            state = ProductionProviderAcceptanceEvidenceState.Failed,
            blocker = ProductionProviderAcceptanceBlocker.FailedGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.HmacSha256HeaderCommitmentPrimitiveApproved,
            state = ProductionProviderAcceptanceEvidenceState.Unsupported,
            blocker = ProductionProviderAcceptanceBlocker.UnsupportedGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.HmacSha256HeaderCommitmentPrimitiveApproved,
            state = ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
            blocker = ProductionProviderAcceptanceBlocker.ModelOnlyGateEvidence,
        )
    }

    @Test
    fun keyExpansionOutputLayoutEvidenceMustBeKnownImplementedAndSatisfied() {
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.KeyExpansionOutputLayoutApproved,
            state = ProductionProviderAcceptanceEvidenceState.Missing,
            blocker = ProductionProviderAcceptanceBlocker.MissingGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.KeyExpansionOutputLayoutApproved,
            state = ProductionProviderAcceptanceEvidenceState.Unknown,
            blocker = ProductionProviderAcceptanceBlocker.UnknownGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.KeyExpansionOutputLayoutApproved,
            state = ProductionProviderAcceptanceEvidenceState.Failed,
            blocker = ProductionProviderAcceptanceBlocker.FailedGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.KeyExpansionOutputLayoutApproved,
            state = ProductionProviderAcceptanceEvidenceState.Unsupported,
            blocker = ProductionProviderAcceptanceBlocker.UnsupportedGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.KeyExpansionOutputLayoutApproved,
            state = ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
            blocker = ProductionProviderAcceptanceBlocker.ModelOnlyGateEvidence,
        )
    }

    @Test
    fun aadEvidenceMustBeKnownImplementedAndSatisfied() {
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.AeadAadPolicyApproved,
            state = ProductionProviderAcceptanceEvidenceState.Missing,
            blocker = ProductionProviderAcceptanceBlocker.MissingGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.AeadAadPolicyApproved,
            state = ProductionProviderAcceptanceEvidenceState.Unknown,
            blocker = ProductionProviderAcceptanceBlocker.UnknownGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.AeadAadPolicyApproved,
            state = ProductionProviderAcceptanceEvidenceState.Failed,
            blocker = ProductionProviderAcceptanceBlocker.FailedGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.AeadAadPolicyApproved,
            state = ProductionProviderAcceptanceEvidenceState.Unsupported,
            blocker = ProductionProviderAcceptanceBlocker.UnsupportedGateEvidence,
        )
    }

    @Test
    fun passphraseEncodingEvidenceMustBeKnownImplementedAndSatisfied() {
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.PassphraseEncodingPolicyApproved,
            state = ProductionProviderAcceptanceEvidenceState.Missing,
            blocker = ProductionProviderAcceptanceBlocker.MissingGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.PassphraseEncodingPolicyApproved,
            state = ProductionProviderAcceptanceEvidenceState.Unknown,
            blocker = ProductionProviderAcceptanceBlocker.UnknownGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.PassphraseEncodingPolicyApproved,
            state = ProductionProviderAcceptanceEvidenceState.Failed,
            blocker = ProductionProviderAcceptanceBlocker.FailedGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.PassphraseEncodingPolicyApproved,
            state = ProductionProviderAcceptanceEvidenceState.Unsupported,
            blocker = ProductionProviderAcceptanceBlocker.UnsupportedGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.PassphraseEncodingPolicyApproved,
            state = ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
            blocker = ProductionProviderAcceptanceBlocker.ModelOnlyGateEvidence,
        )
    }

    @Test
    fun argon2idRootDerivationEvidenceMustBeKnownImplementedAndSatisfied() {
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.Argon2idPassphraseRootDerivationImplemented,
            state = ProductionProviderAcceptanceEvidenceState.Missing,
            blocker = ProductionProviderAcceptanceBlocker.MissingGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.Argon2idPassphraseRootDerivationImplemented,
            state = ProductionProviderAcceptanceEvidenceState.Unknown,
            blocker = ProductionProviderAcceptanceBlocker.UnknownGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.Argon2idPassphraseRootDerivationImplemented,
            state = ProductionProviderAcceptanceEvidenceState.Failed,
            blocker = ProductionProviderAcceptanceBlocker.FailedGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.Argon2idPassphraseRootDerivationImplemented,
            state = ProductionProviderAcceptanceEvidenceState.Unsupported,
            blocker = ProductionProviderAcceptanceBlocker.UnsupportedGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.Argon2idPassphraseRootDerivationImplemented,
            state = ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
            blocker = ProductionProviderAcceptanceBlocker.ModelOnlyGateEvidence,
        )
    }

    @Test
    fun tinkRawKeyFeasibilityEvidenceMustBeKnownAndSuccessful() {
        val missing = contract.assess(
            evidenceWith(
                ProductionProviderAcceptanceGate.TinkRawKeyFeasibilityApproved,
                ProductionProviderAcceptanceEvidenceState.Missing,
            ),
        )
        val unknown = contract.assess(
            evidenceWith(
                ProductionProviderAcceptanceGate.TinkRawKeyFeasibilityApproved,
                ProductionProviderAcceptanceEvidenceState.Unknown,
            ),
        )
        val failed = contract.assess(
            evidenceWith(
                ProductionProviderAcceptanceGate.TinkRawKeyFeasibilityApproved,
                ProductionProviderAcceptanceEvidenceState.Failed,
            ),
        )
        val inconclusive = contract.assess(
            evidenceWith(
                ProductionProviderAcceptanceGate.TinkRawKeyFeasibilityApproved,
                ProductionProviderAcceptanceEvidenceState.Unsupported,
            ),
        )

        assertFalse(missing.allRequiredGatesSatisfied)
        assertContains(missing.blockers, ProductionProviderAcceptanceBlocker.MissingGateEvidence)
        assertFalse(missing.productionProviderSelectable)

        assertFalse(unknown.allRequiredGatesSatisfied)
        assertContains(unknown.blockers, ProductionProviderAcceptanceBlocker.UnknownGateEvidence)
        assertFalse(unknown.productionProviderSelectable)

        assertFalse(failed.allRequiredGatesSatisfied)
        assertContains(failed.blockers, ProductionProviderAcceptanceBlocker.FailedGateEvidence)
        assertFalse(failed.productionPersistenceAllowed)

        assertFalse(inconclusive.allRequiredGatesSatisfied)
        assertContains(inconclusive.blockers, ProductionProviderAcceptanceBlocker.UnsupportedGateEvidence)
        assertFalse(inconclusive.productionProviderSelectable)
    }

    @Test
    fun feasibleTinkRawKeyEvidenceAloneDoesNotSelectProvider() {
        val assessment = contract.assess(
            ProductionProviderAcceptanceEvidence(
                gateStates = mapOf(
                    ProductionProviderAcceptanceGate.TinkRawKeyFeasibilityApproved to
                        ProductionProviderAcceptanceEvidenceState.Satisfied,
                ),
            ),
        )

        assertFalse(assessment.allRequiredGatesSatisfied)
        assertContains(assessment.blockers, ProductionProviderAcceptanceBlocker.UnknownGateEvidence)
        assertFalse(assessment.productionProviderSelectable)
        assertFalse(assessment.productionPersistenceAllowed)
    }

    @Test
    fun tinkRawKeyFeasibilityDoesNotSatisfyHeaderCommitmentOrAadGates() {
        val assessment = contract.assess(
            ProductionProviderAcceptanceEvidence(
                gateStates = mapOf(
                    ProductionProviderAcceptanceGate.TinkRawKeyFeasibilityApproved to
                        ProductionProviderAcceptanceEvidenceState.Satisfied,
                    ProductionProviderAcceptanceGate.HeaderCommitmentPolicyApproved to
                        ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                    ProductionProviderAcceptanceGate.HkdfSha256KeyExpansionPrimitiveApproved to
                        ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                    ProductionProviderAcceptanceGate.HmacSha256HeaderCommitmentPrimitiveApproved to
                        ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                    ProductionProviderAcceptanceGate.KeyExpansionOutputLayoutApproved to
                        ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                    ProductionProviderAcceptanceGate.PrimitiveThreatModelRationaleDocumented to
                        ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                    ProductionProviderAcceptanceGate.CanonicalHeaderByteVectorsApproved to
                        ProductionProviderAcceptanceEvidenceState.TestScopeVectorsComplete,
                    ProductionProviderAcceptanceGate.HkdfSha256VectorContractApproved to
                        ProductionProviderAcceptanceEvidenceState.TestScopeVectorsComplete,
                    ProductionProviderAcceptanceGate.HmacSha256HeaderCommitmentVectorContractApproved to
                        ProductionProviderAcceptanceEvidenceState.TestScopeVectorsComplete,
                    ProductionProviderAcceptanceGate.CanonicalHeaderEncodingPolicyApproved to
                        ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                    ProductionProviderAcceptanceGate.KeySeparationLabelsPolicyApproved to
                        ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                    ProductionProviderAcceptanceGate.AeadAadPolicyApproved to
                        ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                    ProductionProviderAcceptanceGate.TinkNonKeyCommitmentMitigationApproved to
                        ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                ),
            ),
        )

        assertFalse(assessment.allRequiredGatesSatisfied)
        assertContains(assessment.blockers, ProductionProviderAcceptanceBlocker.ModelOnlyGateEvidence)
        assertContains(assessment.blockers, ProductionProviderAcceptanceBlocker.UnknownGateEvidence)
        assertFalse(assessment.productionProviderSelectable)
        assertFalse(assessment.productionPersistenceAllowed)
    }

    @Test
    fun androidTinkRawKeyFeasibilityMustBeKnownAndSuccessfulForCrossPlatformReadiness() {
        val policy = contract.tinkRawKeyHandlingPolicy

        assertEquals(
            ProductionProviderAndroidTinkRawKeyFeasibilityStatus.ANDROID_FEASIBLE_PUBLIC_RAW_KEY_API,
            policy.androidFeasibilityStatus,
        )
        assertTrue(policy.androidFeasibilityStatus.satisfiesAndroidFeasibilityGate)
        assertTrue(policy.crossPlatformFeasibilitySatisfied)

        val feasible = policy.copy(
            androidFeasibilityStatus =
                ProductionProviderAndroidTinkRawKeyFeasibilityStatus
                    .ANDROID_FEASIBLE_PUBLIC_RAW_KEY_API,
        )

        val infeasible = policy.copy(
            androidFeasibilityStatus =
                ProductionProviderAndroidTinkRawKeyFeasibilityStatus
                    .ANDROID_NOT_FEASIBLE_WITH_CURRENT_TINK_API,
        )
        val inconclusive = policy.copy(
            androidFeasibilityStatus =
                ProductionProviderAndroidTinkRawKeyFeasibilityStatus
                    .ANDROID_INCONCLUSIVE_REQUIRES_HUMAN_REVIEW,
        )

        assertTrue(feasible.androidFeasibilityStatus.satisfiesAndroidFeasibilityGate)
        assertTrue(feasible.crossPlatformFeasibilitySatisfied)
        assertFalse(infeasible.androidFeasibilityStatus.satisfiesAndroidFeasibilityGate)
        assertFalse(infeasible.crossPlatformFeasibilitySatisfied)
        assertFalse(inconclusive.androidFeasibilityStatus.satisfiesAndroidFeasibilityGate)
        assertFalse(inconclusive.crossPlatformFeasibilitySatisfied)
    }

    @Test
    fun androidFeasibleEvidenceAloneDoesNotSelectProvider() {
        val assessment = contract.assess(
            ProductionProviderAcceptanceEvidence(
                gateStates = mapOf(
                    ProductionProviderAcceptanceGate.TinkRawKeyFeasibilityApproved to
                        ProductionProviderAcceptanceEvidenceState.Satisfied,
                ),
            ),
        )
        val result = VaultCryptoProviderSelectionRegistry.select(
            request = VaultCryptoProviderSelectionRequest(
                requestedCandidate = VaultCryptoProviderCandidateId.TinkBouncyCastleSplit,
            ),
            productionProviderAcceptanceAssessment = assessment,
        )
        val feasiblePolicy = contract.tinkRawKeyHandlingPolicy.copy(
            androidFeasibilityStatus =
                ProductionProviderAndroidTinkRawKeyFeasibilityStatus
                    .ANDROID_FEASIBLE_PUBLIC_RAW_KEY_API,
        )

        assertTrue(feasiblePolicy.androidFeasibilityStatus.satisfiesAndroidFeasibilityGate)
        assertTrue(feasiblePolicy.crossPlatformFeasibilitySatisfied)
        assertFalse(assessment.allRequiredGatesSatisfied)
        assertFalse(assessment.productionProviderSelectable)
        assertFalse(assessment.productionPersistenceAllowed)
        assertFalse(result.productionProviderSelectable)
        assertFalse(result.requestedCandidate.productionSelectable)
    }

    @Test
    fun boundedArgon2idCalibrationEvidenceMustBeKnownAndSuccessful() {
        listOf(
            ProductionProviderAcceptanceGate.Argon2idBoundedCalibrationApproved,
            ProductionProviderAcceptanceGate.Argon2idCalibrationAndMemoryFailureApproved,
        ).forEach { gate ->
            assertGateBlocks(
                gate = gate,
                state = ProductionProviderAcceptanceEvidenceState.Missing,
                blocker = ProductionProviderAcceptanceBlocker.MissingGateEvidence,
            )
            assertGateBlocks(
                gate = gate,
                state = ProductionProviderAcceptanceEvidenceState.Unknown,
                blocker = ProductionProviderAcceptanceBlocker.UnknownGateEvidence,
            )
            assertGateBlocks(
                gate = gate,
                state = ProductionProviderAcceptanceEvidenceState.Failed,
                blocker = ProductionProviderAcceptanceBlocker.FailedGateEvidence,
            )
            assertGateBlocks(
                gate = gate,
                state = ProductionProviderAcceptanceEvidenceState.Unsupported,
                blocker = ProductionProviderAcceptanceBlocker.UnsupportedGateEvidence,
            )
            assertGateBlocks(
                gate = gate,
                state = ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                blocker = ProductionProviderAcceptanceBlocker.ModelOnlyGateEvidence,
            )
        }
    }

    @Test
    fun currentEvidenceRepresentsCalibrationBuildingBlockButStillDoesNotSelectProvider() {
        val evidence = ProductionProviderAcceptanceEvidence.currentDesignOnly()

        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            evidence.stateFor(ProductionProviderAcceptanceGate.Argon2idBoundedCalibrationApproved),
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            evidence.stateFor(ProductionProviderAcceptanceGate.Argon2idCalibrationAndMemoryFailureApproved),
        )

        val assessment = contract.assess(evidence)

        assertFalse(assessment.allRequiredGatesSatisfied)
        assertContains(assessment.blockers, ProductionProviderAcceptanceBlocker.UnknownGateEvidence)
        assertContains(
            assessment.blockers,
            ProductionProviderAcceptanceBlocker.ProductionProviderSelectionStillDisabled,
        )
        assertFalse(assessment.productionProviderSelectable)
        assertFalse(assessment.productionPersistenceAllowed)
    }

    @Test
    fun allGateEvidenceStillDoesNotSelectProviderInThisBranch() {
        val assessment = contract.assess(ProductionProviderAcceptanceEvidence.allSatisfiedForReviewOnly())
        val result = VaultCryptoProviderSelectionRegistry.select(
            request = VaultCryptoProviderSelectionRequest(
                requestedCandidate = VaultCryptoProviderCandidateId.TinkBouncyCastleSplit,
            ),
            productionProviderAcceptanceAssessment = assessment,
        )
        val candidate = result.requestedCandidate

        assertTrue(assessment.allRequiredGatesSatisfied)
        assertFalse(assessment.productionProviderSelectable)
        assertFalse(assessment.productionPersistenceAllowed)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, result.selectedCandidateId)
        assertTrue(result.selectedProvider is DisabledVaultCryptoProvider)
        assertFalse(result.productionProviderSelectable)
        assertFalse(candidate.productionSelectable)
        assertFalse(
            VaultCryptoProviderSelectionBlocker.ProductionProviderAcceptanceContractIncomplete in
                candidate.blockers,
        )
        assertContains(candidate.blockers, VaultCryptoProviderSelectionBlocker.ProductionProviderImplementationMissing)
        assertContains(candidate.blockers, VaultCryptoProviderSelectionBlocker.ProductionProviderLevelKatsMissing)
        assertContains(candidate.blockers, VaultCryptoProviderSelectionBlocker.SecureSecretStorageDisabled)
        assertTrue(candidate.evidence.productionAcceptance.allRequiredGatesSatisfied)
        assertFalse(candidate.evidence.productionAcceptance.productionProviderSelectableByContract)
        assertFalse(candidate.evidence.productionAcceptance.productionPersistenceAllowedByContract)
        assertTrue(
            candidate.productionApprovalGates
                .single {
                    it.gate == VaultCryptoProviderProductionApprovalGate
                        .ProductionProviderAcceptanceContractSatisfied
                }
                .satisfied,
        )
    }

    @Test
    fun allConstructionContractEvidenceAtModelOnlyStateDoesNotSelectProvider() {
        val assessment = contract.assess(
            ProductionProviderAcceptanceEvidence(
                gateStates = ProductionProviderAcceptanceGate.entries.associateWith {
                    ProductionProviderAcceptanceEvidenceState.Satisfied
                } + mapOf(
                    ProductionProviderAcceptanceGate.HeaderCommitmentPolicyApproved to
                        ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                    ProductionProviderAcceptanceGate.HkdfSha256KeyExpansionPrimitiveApproved to
                        ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                    ProductionProviderAcceptanceGate.HmacSha256HeaderCommitmentPrimitiveApproved to
                        ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                    ProductionProviderAcceptanceGate.KeyExpansionOutputLayoutApproved to
                        ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                    ProductionProviderAcceptanceGate.PrimitiveThreatModelRationaleDocumented to
                        ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                    ProductionProviderAcceptanceGate.CanonicalHeaderByteVectorsApproved to
                        ProductionProviderAcceptanceEvidenceState.TestScopeVectorsComplete,
                    ProductionProviderAcceptanceGate.HkdfSha256VectorContractApproved to
                        ProductionProviderAcceptanceEvidenceState.TestScopeVectorsComplete,
                    ProductionProviderAcceptanceGate.HmacSha256HeaderCommitmentVectorContractApproved to
                        ProductionProviderAcceptanceEvidenceState.TestScopeVectorsComplete,
                    ProductionProviderAcceptanceGate.CanonicalHeaderEncodingPolicyApproved to
                        ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                    ProductionProviderAcceptanceGate.KeySeparationLabelsPolicyApproved to
                        ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                    ProductionProviderAcceptanceGate.AeadAadPolicyApproved to
                        ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                    ProductionProviderAcceptanceGate.TinkNonKeyCommitmentMitigationApproved to
                        ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                ),
            ),
        )

        assertFalse(assessment.allRequiredGatesSatisfied)
        assertContains(assessment.blockers, ProductionProviderAcceptanceBlocker.ModelOnlyGateEvidence)
        assertContains(assessment.blockers, ProductionProviderAcceptanceBlocker.TestScopeVectorEvidenceOnly)
        assertFalse(assessment.productionProviderSelectable)
        assertFalse(assessment.productionPersistenceAllowed)
    }

    @Test
    fun incompleteAcceptanceEvidenceBlocksProviderSelectionAndPersistence() {
        val result = VaultCryptoProviderSelectionRegistry.select(
            VaultCryptoProviderSelectionRequest(
                requestedCandidate = VaultCryptoProviderCandidateId.TinkBouncyCastleSplit,
            ),
        )
        val candidate = result.requestedCandidate

        assertContains(
            candidate.blockers,
            VaultCryptoProviderSelectionBlocker.ProductionProviderAcceptanceContractIncomplete,
        )
        assertFalse(candidate.evidence.productionAcceptance.allRequiredGatesSatisfied)
        assertFalse(candidate.evidence.productionAcceptance.productionProviderSelectableByContract)
        assertFalse(candidate.evidence.productionAcceptance.productionPersistenceAllowedByContract)
        assertFalse(
            candidate.productionApprovalGates
                .single {
                    it.gate == VaultCryptoProviderProductionApprovalGate
                        .ProductionProviderAcceptanceContractSatisfied
                }
                .satisfied,
        )
        assertFalse(result.productionProviderSelectable)
    }

    @Test
    fun debugOrTestProviderCannotBecomeReleaseSelectable() {
        val assessment = contract.assess(
            evidence = ProductionProviderAcceptanceEvidence.allSatisfiedForReviewOnly(),
            providerImplementationState = VaultCryptoProviderImplementationState.TestOnly,
            releaseReadiness = true,
        )

        assertTrue(assessment.allRequiredGatesSatisfied)
        assertContains(
            assessment.blockers,
            ProductionProviderAcceptanceBlocker.DebugOrTestProviderNotReleaseSelectable,
        )
        assertFalse(assessment.productionProviderSelectable)
        assertFalse(assessment.productionPersistenceAllowed)
    }

    @Test
    fun argon2idPolicyCapturesFloorAndFailClosedDowngradeRules() {
        val policy = contract.argon2idPolicy

        assertEquals(Argon2idVersion.Version19, policy.version)
        assertEquals(64, policy.minimumMemoryMiB)
        assertEquals(3, policy.passes)
        assertEquals(1, policy.lanes)
        assertEquals(16, policy.minimumSaltBytes)
        assertEquals(32, policy.preferredNewVaultSaltBytes)
        assertEquals(64, policy.derivedRootMaterialBytes)
        assertEquals(1_000, policy.preferredUnlockMillis)
        assertEquals(2_000, policy.acceptableUnlockMillis)
        assertFalse(policy.twoSecondsIsFailureCondition)
        assertFalse(policy.weakenToForceSubOneSecondAllowed)
        assertTrue(policy.boundedPerPlatformCalibrationRequired)
        assertTrue(policy.sharedMinimumFloorAcrossPlatforms)
        assertTrue(policy.desktopMaySelectStrongerParametersThanAndroid)
        assertTrue(policy.minimumFloorAllocationFailureBlocksVaultCreation)
        assertTrue(policy.storedParameterAllocationFailureBlocksUnlock)
        assertTrue(policy.existingVaultParametersAuthoritative)
        assertFalse(policy.silentParameterDowngradeAllowed)
        assertTrue(policy.downgradeMigrationRequiresSuccessfulUnlockAndExplicitUserAction)
        assertEquals(
            ProductionProviderWeakDeviceFailureMode.FailClosedWithUserMessage,
            policy.weakerDeviceFailureMode,
        )
    }

    @Test
    fun headerCommitmentPolicyBindsCanonicalHeaderBeforeRecordDecrypt() {
        val policy = contract.headerCommitmentPolicy

        assertEquals("skald-vault-v1-header-commitment-v1", policy.policyId)
        assertEquals(1, policy.policyVersion)
        assertEquals(ProductionProviderConstructionContractStatus.ImplementedTested, policy.contractStatus)
        assertTrue(policy.requiredBeforeRecordDecrypt)
        assertFalse(policy.recordDecryptAllowedBeforeVerification)
        assertTrue(policy.commitmentKeyMaterialSeparatedFromRecordAeadKeyMaterial)
        assertTrue(policy.productionExecutionImplemented)
        assertEquals(
            ProductionProviderHeaderCommitmentField.entries.toSet(),
            policy.canonicalHeaderFields,
        )
        assertContains(policy.canonicalHeaderFields, ProductionProviderHeaderCommitmentField.VaultMagicDomainMarker)
        assertContains(policy.canonicalHeaderFields, ProductionProviderHeaderCommitmentField.ProviderSuiteId)
        assertContains(policy.canonicalHeaderFields, ProductionProviderHeaderCommitmentField.KdfAlgorithmId)
        assertContains(policy.canonicalHeaderFields, ProductionProviderHeaderCommitmentField.SaltLength)
        assertContains(policy.canonicalHeaderFields, ProductionProviderHeaderCommitmentField.SaltBytes)
        assertContains(
            policy.canonicalHeaderFields,
            ProductionProviderHeaderCommitmentField.DerivedRootMaterialLength,
        )
        assertContains(policy.canonicalHeaderFields, ProductionProviderHeaderCommitmentField.VaultId)
        assertContains(
            policy.canonicalHeaderFields,
            ProductionProviderHeaderCommitmentField.PassphraseEncodingPolicyId,
        )
        assertContains(
            policy.canonicalHeaderFields,
            ProductionProviderHeaderCommitmentField.KeyExpansionPolicyId,
        )
        assertContains(
            policy.canonicalHeaderFields,
            ProductionProviderHeaderCommitmentField.KeySeparationPolicyId,
        )
        assertContains(
            policy.canonicalHeaderFields,
            ProductionProviderHeaderCommitmentField.HeaderCommitmentPrimitivePolicyId,
        )
        assertContains(
            policy.canonicalHeaderFields,
            ProductionProviderHeaderCommitmentField.HeaderCommitmentPolicyId,
        )
        assertContains(
            policy.canonicalHeaderFields,
            ProductionProviderHeaderCommitmentField.AadPolicyId,
        )
        assertEquals(
            ProductionProviderHeaderCommitmentFailClosedCondition.entries.toSet(),
            policy.failClosedConditions,
        )
        assertContains(
            policy.failClosedConditions,
            ProductionProviderHeaderCommitmentFailClosedCondition.HeaderNonCanonical,
        )
        assertContains(
            policy.failClosedConditions,
            ProductionProviderHeaderCommitmentFailClosedCondition.UnsupportedKdfParameters,
        )
        assertContains(
            policy.failClosedConditions,
            ProductionProviderHeaderCommitmentFailClosedCondition.RequiredHeaderFieldOmitted,
        )
        assertContains(
            policy.failClosedConditions,
            ProductionProviderHeaderCommitmentFailClosedCondition.RequiredHeaderFieldDuplicated,
        )
        assertContains(
            policy.failClosedConditions,
            ProductionProviderHeaderCommitmentFailClosedCondition.MalformedIntegerEncoding,
        )
        assertContains(
            policy.failClosedConditions,
            ProductionProviderHeaderCommitmentFailClosedCondition.UnsupportedFutureVersion,
        )
    }

    @Test
    fun hkdfSha256KeyExpansionPolicyIsSelectedAndImplementedStillDisabled() {
        val policy = contract.keyExpansionPrimitivePolicy

        assertEquals("skald-vault-v1-hkdf-sha256-key-expansion-v1", policy.policyId)
        assertEquals(ProductionProviderKeyExpansionPrimitive.HkdfSha256, policy.primitive)
        assertEquals(ProductionProviderConstructionContractStatus.ImplementedTested, policy.contractStatus)
        assertTrue(policy.argon2idRemainsPasswordKdf)
        assertTrue(policy.usedOnlyAfterArgon2idRootMaterialExists)
        assertFalse(policy.usedDirectlyOnPassphraseAllowed)
        assertTrue(policy.domainSeparatedByStableAsciiLabels)
        assertTrue(policy.avoidsManualRootMaterialSlicing)
        assertTrue(policy.productionHkdfExecutionImplemented)
    }

    @Test
    fun hmacSha256HeaderCommitmentPrimitivePolicyIsSelectedAndImplementedStillDisabled() {
        val policy = contract.headerCommitmentPrimitivePolicy

        assertEquals("skald-vault-v1-hmac-sha256-header-commitment-v1", policy.policyId)
        assertEquals(ProductionProviderHeaderCommitmentPrimitive.HmacSha256, policy.primitive)
        assertEquals(ProductionProviderConstructionContractStatus.ImplementedTested, policy.contractStatus)
        assertEquals("canonical vault header bytes", policy.inputDescription)
        assertTrue(policy.usesDerivedHeaderCommitmentKey)
        assertTrue(policy.verifiesBeforeRecordDecrypt)
        assertFalse(policy.successfulRecordDecryptAloneProvesCorrectVaultKey)
        assertTrue(policy.productionHmacExecutionImplemented)
        assertTrue(policy.productionHeaderCommitmentComputationImplemented)
    }

    @Test
    fun keyExpansionOutputLayoutIsFixedAndImplementedStillDisabled() {
        val policy = contract.keyExpansionOutputLayoutPolicy

        assertEquals(ProductionProviderConstructionContractStatus.ImplementedTested, policy.contractStatus)
        assertEquals(64, policy.argon2idRootMaterialBytes)
        assertEquals(32, policy.headerCommitmentKeyBytes)
        assertEquals(32, policy.recordAeadKeyBytes)
        assertTrue(policy.recordAeadKeyFeedsTinkRawKeyPath)
        assertFalse(policy.reservedFutureWrappingExportMigrationOutputsImplemented)
    }

    @Test
    fun primitiveThreatModelRationaleIsModelOnlyAndDoesNotOverclaim() {
        val policy = contract.primitiveThreatModelPolicy

        assertEquals(ProductionProviderConstructionContractStatus.DocumentedModelOnly, policy.contractStatus)
        assertTrue(policy.offlineAttackBecomesPassphraseGuessing)
        assertTrue(policy.dependsOnPassphraseEntropyAndArgon2idParameters)
        assertTrue(policy.hkdfAndHmacExpectedNotWeakLinkWhenCorrectlyImplemented)
        assertFalse(policy.liveEndpointCompromiseCovered)
        assertFalse(policy.weakPassphraseCompensatedByHkdfOrHmac)
    }

    @Test
    fun canonicalHeaderHkdfAndHmacVectorContractsAreProductionImplementedAndTested() {
        val canonical = contract.canonicalHeaderVectorContract
        val hkdf = contract.hkdfVectorContract
        val hmac = contract.hmacHeaderCommitmentVectorContract

        assertEquals(
            ProductionProviderTestVectorContractStatus.ProductionImplementedTested,
            canonical.status,
        )
        assertEquals(
            "docs/ENCRYPTED_LOCAL_VAULT_CANONICAL_HEADER_HKDF_HMAC_VECTORS.md",
            canonical.documentPath,
        )
        assertTrue(canonical.logicalFixtureFieldsDefined)
        assertTrue(canonical.canonicalFieldOrderDefined)
        assertTrue(canonical.byteEncodingRulesDefined)
        assertTrue(canonical.finalCanonicalHeaderHexDocumented)
        assertTrue(canonical.testScopeEncoderExists)
        assertTrue(canonical.productionSerializerImplemented)

        assertEquals(ProductionProviderTestVectorContractStatus.ProductionImplementedTested, hkdf.status)
        assertEquals("SHA-256", hkdf.hash)
        assertEquals(64, hkdf.inputKeyingMaterialBytes)
        assertTrue(hkdf.saltDefined)
        assertTrue(hkdf.infoConstructionDefined)
        assertTrue(hkdf.headerCommitmentInfoDefined)
        assertTrue(hkdf.recordAeadInfoDefined)
        assertEquals(32, hkdf.outputBytesPerPurpose)
        assertTrue(hkdf.expectedOutputsDocumented)
        assertTrue(hkdf.testScopeHkdfExecutionExists)
        assertTrue(hkdf.productionHkdfExecutionImplemented)

        assertEquals(ProductionProviderTestVectorContractStatus.ProductionImplementedTested, hmac.status)
        assertEquals("SHA-256", hmac.hash)
        assertEquals("HKDF header commitment key vector output", hmac.hmacKeySource)
        assertEquals("canonical header vector bytes", hmac.messageSource)
        assertTrue(hmac.expectedTagDocumented)
        assertTrue(hmac.testScopeHmacExecutionExists)
        assertTrue(hmac.productionHmacExecutionImplemented)
        assertTrue(hmac.productionHeaderCommitmentExecutionImplemented)
    }

    @Test
    fun canonicalHeaderEncodingPolicyIsDeterministicAndImplementedStillDisabled() {
        val policy = contract.canonicalHeaderEncodingPolicy

        assertEquals("skald-vault-v1-canonical-header-encoding-v1", policy.policyId)
        assertEquals(1, policy.policyVersion)
        assertEquals(ProductionProviderConstructionContractStatus.ImplementedTested, policy.contractStatus)
        assertEquals("big-endian", policy.byteOrder)
        assertEquals("UTF-8", policy.stringEncoding)
        assertEquals("SKALD-VAULT-V1", policy.domainMagic)
        assertEquals(ProductionProviderCanonicalHeaderEncodingRule.entries.toSet(), policy.rules)
        assertContains(policy.rules, ProductionProviderCanonicalHeaderEncodingRule.BigEndianIntegers)
        assertContains(policy.rules, ProductionProviderCanonicalHeaderEncodingRule.ExplicitFieldOrder)
        assertContains(policy.rules, ProductionProviderCanonicalHeaderEncodingRule.LengthPrefixesForVariableFields)
        assertContains(policy.rules, ProductionProviderCanonicalHeaderEncodingRule.NoDefaultObjectSerialization)
        assertContains(policy.rules, ProductionProviderCanonicalHeaderEncodingRule.NoNonCanonicalJson)
        assertContains(policy.rules, ProductionProviderCanonicalHeaderEncodingRule.NoPlatformNativeSerialization)
        assertContains(policy.rules, ProductionProviderCanonicalHeaderEncodingRule.SingleEncodingPerLogicalHeader)
        assertContains(policy.rules, ProductionProviderCanonicalHeaderEncodingRule.TestVectorsRequiredBeforeSelectability)
        assertTrue(policy.productionSerializerImplemented)
    }

    @Test
    fun keySeparationPolicyDefinesStableLabelsForImplementedStillDisabledExpansion() {
        val policy = contract.keySeparationPolicy

        assertEquals("skald-vault-v1-key-separation-labels-v1", policy.policyId)
        assertEquals(1, policy.policyVersion)
        assertEquals(ProductionProviderConstructionContractStatus.ImplementedTested, policy.contractStatus)
        assertEquals(ProductionProviderKeySeparationLabel.entries.toSet(), policy.labels)
        assertEquals(
            "skald-vault/v1/root-domain",
            ProductionProviderKeySeparationLabel.RootDomain.labelValue,
        )
        assertEquals(
            "skald-vault/v1/header-commitment-key",
            ProductionProviderKeySeparationLabel.HeaderCommitmentKey.labelValue,
        )
        assertEquals(
            "skald-vault/v1/record-aead-key",
            ProductionProviderKeySeparationLabel.RecordAeadKey.labelValue,
        )
        assertEquals(
            "skald-vault/v1/reserved/wrapping-metadata",
            ProductionProviderKeySeparationLabel.FutureWrappingMetadata.labelValue,
        )
        assertEquals(
            "skald-vault/v1/reserved/export-migration",
            ProductionProviderKeySeparationLabel.FutureExportMigration.labelValue,
        )
        assertEquals(
            "skald-vault/test-only/raw-key-probe",
            ProductionProviderKeySeparationLabel.TestProbeDomain.labelValue,
        )
        assertFalse(policy.rootMaterialUsedDirectlyForMultiplePurposes)
        assertTrue(policy.recordAeadAndHeaderCommitmentKeyMaterialSeparated)
        assertTrue(policy.reservedFutureLabelsNotImplemented)
        assertTrue(policy.keyExpansionPrimitiveApproved)
        assertTrue(policy.productionKeyDerivationImplemented)
        assertTrue(policy.unknownUnsupportedPolicyBlocksSelectability)
    }

    @Test
    fun passphraseEncodingPolicyCapturesNfcUtf8AndRejectionRules() {
        val policy = contract.passphraseEncodingPolicy

        assertEquals("unicode-nfc-utf8-no-controls-no-whitespace-v1", policy.policyId)
        assertEquals(ProductionProviderConstructionContractStatus.ImplementedTested, policy.contractStatus)
        assertEquals("NFC", policy.normalizationForm)
        assertEquals("UTF-8", policy.encodedForm)
        assertContains(policy.forbiddenClasses, ProductionProviderPassphraseForbiddenClass.EmptyPassphrase)
        assertContains(
            policy.forbiddenClasses,
            ProductionProviderPassphraseForbiddenClass.UnicodeControlCharacters,
        )
        assertContains(
            policy.forbiddenClasses,
            ProductionProviderPassphraseForbiddenClass.UnicodeWhitespaceCharacters,
        )
        assertContains(
            policy.forbiddenClasses,
            ProductionProviderPassphraseForbiddenClass.UnicodeSeparatorCharacters,
        )
        assertContains(
            policy.forbiddenClasses,
            ProductionProviderPassphraseForbiddenClass.InvisibleFormatCharacters,
        )
        assertContains(policy.noTransformRules, ProductionProviderPassphraseNoTransformRule.DoNotTrim)
        assertContains(policy.noTransformRules, ProductionProviderPassphraseNoTransformRule.DoNotLowercase)
        assertContains(policy.noTransformRules, ProductionProviderPassphraseNoTransformRule.DoNotUppercase)
        assertContains(
            policy.noTransformRules,
            ProductionProviderPassphraseNoTransformRule.DoNotApplyLocaleSensitiveTransforms,
        )
        assertTrue(policy.composedAndDecomposedFormsMustCanonicalizeToSameNfcBytes)
        assertContains(policy.allowedClasses, ProductionProviderPassphraseAllowedClass.VisibleUnicodeLetters)
        assertContains(policy.allowedClasses, ProductionProviderPassphraseAllowedClass.VisibleUnicodeNumbers)
        assertContains(policy.allowedClasses, ProductionProviderPassphraseAllowedClass.VisibleUnicodePunctuation)
        assertContains(policy.allowedClasses, ProductionProviderPassphraseAllowedClass.VisibleUnicodeSymbols)
        assertContains(policy.allowedClasses, ProductionProviderPassphraseAllowedClass.EmojiWithoutRejectedCharacters)
        assertContains(policy.visibleSeparatorsSuggestedAsAlternativesToSpaces, "-")
        assertContains(policy.visibleSeparatorsSuggestedAsAlternativesToSpaces, ".")
        assertContains(policy.visibleSeparatorsSuggestedAsAlternativesToSpaces, "_")
        assertTrue(policy.productionValidationImplemented)
        assertFalse(policy.productionVaultCreationWired)
    }

    @Test
    fun argon2idRootDerivationPolicyCapturesExplicitParameterBuildingBlock() {
        val policy = contract.argon2idRootDerivationPolicy

        assertEquals(ProductionProviderConstructionContractStatus.ImplementedTested, policy.contractStatus)
        assertEquals("Bouncy Castle Argon2id explicit-parameter root derivation", policy.implementation)
        assertEquals(SkaldVaultV1Argon2idType.Argon2id, policy.type)
        assertEquals(Argon2idVersion.Version19, policy.version)
        assertEquals(64, policy.minimumMemoryMiB)
        assertEquals(3, policy.minimumIterations)
        assertEquals(1, policy.parallelism)
        assertEquals(16, policy.minimumSaltBytes)
        assertEquals(32, policy.preferredNewVaultSaltBytes)
        assertEquals(SkaldVaultV1Argon2idRootDerivation.ROOT_MATERIAL_BYTES, policy.outputRootMaterialBytes)
        assertTrue(policy.explicitCallerSuppliedParametersRequired)
        assertFalse(policy.automaticCalibrationImplemented)
        assertFalse(policy.parameterDowngradeImplemented)
        assertTrue(policy.productionRootDerivationImplemented)
        assertFalse(policy.productionVaultCreationWired)
    }

    @Test
    fun tinkRawKeyHandlingPolicyBlocksKeysetsRandomVaultKeysAndInternalApis() {
        val policy = contract.tinkRawKeyHandlingPolicy

        assertTrue(policy.preferredCallerSuppliedDerivedRawKeyMaterial)
        assertFalse(policy.persistedPlaintextTinkKeysetsAllowed)
        assertFalse(policy.persistedEncryptedTinkKeysetsAllowedInV1)
        assertFalse(policy.randomTinkVaultKeysAllowed)
        assertFalse(policy.tinkKeyRotationInV1Allowed)
        assertFalse(policy.multipleActiveAeadKeysInV1Allowed)
        assertTrue(policy.publicSupportedApiRequired)
        assertFalse(policy.internalUnsupportedReflectiveApisAllowed)
        assertFalse(policy.fallbackEncryptedKeysetModelImplemented)
        assertFalse(policy.productionAeadExecutionImplemented)
        assertTrue(policy.stillDisabledRecordAeadBuildingBlockImplemented)
        assertEquals(
            ProductionProviderTinkRawKeyFeasibilityStatus.FEASIBLE_PUBLIC_RAW_KEY_API,
            policy.desktopFeasibilityStatus,
        )
        assertEquals(
            ProductionProviderAndroidTinkRawKeyFeasibilityStatus.ANDROID_FEASIBLE_PUBLIC_RAW_KEY_API,
            policy.androidFeasibilityStatus,
        )
        assertTrue(policy.desktopFeasibilityStatus.satisfiesFeasibilityGate)
        assertTrue(policy.androidFeasibilityStatus.satisfiesAndroidFeasibilityGate)
        assertTrue(policy.crossPlatformFeasibilitySatisfied)
        assertEquals(
            "caller-supplied fixed bytes -> public Tink secret-byte wrapper -> " +
                "public Tink XChaCha20-Poly1305 key object -> transient in-memory Tink keyset handle " +
                "import -> public AEAD primitive lookup",
            policy.desktopTestedPublicApiPath,
        )
        assertEquals(
            policy.desktopTestedPublicApiPath,
            policy.androidTestedPublicApiPath,
        )
        assertTrue(policy.androidPathMatchesDesktopPath)
        assertTrue(policy.transientInMemoryTinkKeysetHandleRequired)
        assertFalse(policy.persistedTinkKeysetRequired)
        assertFalse(policy.randomTinkGeneratedVaultKeyRequired)
    }

    @Test
    fun tinkRawKeyFeasibilityOutcomesAreExactAndFailClosedExceptPublicApiSuccess() {
        assertTrue(
            ProductionProviderTinkRawKeyFeasibilityStatus
                .FEASIBLE_PUBLIC_RAW_KEY_API
                .satisfiesFeasibilityGate,
        )
        assertFalse(
            ProductionProviderTinkRawKeyFeasibilityStatus
                .NOT_FEASIBLE_WITH_CURRENT_TINK_API
                .satisfiesFeasibilityGate,
        )
        assertFalse(
            ProductionProviderTinkRawKeyFeasibilityStatus
                .INCONCLUSIVE_REQUIRES_HUMAN_REVIEW
                .satisfiesFeasibilityGate,
        )
        assertEquals(
            setOf(
                "FEASIBLE_PUBLIC_RAW_KEY_API",
                "NOT_FEASIBLE_WITH_CURRENT_TINK_API",
                "INCONCLUSIVE_REQUIRES_HUMAN_REVIEW",
            ),
            ProductionProviderTinkRawKeyFeasibilityStatus.entries.map { it.name }.toSet(),
        )
        assertTrue(
            ProductionProviderAndroidTinkRawKeyFeasibilityStatus
                .ANDROID_FEASIBLE_PUBLIC_RAW_KEY_API
                .satisfiesAndroidFeasibilityGate,
        )
        assertFalse(
            ProductionProviderAndroidTinkRawKeyFeasibilityStatus
                .ANDROID_NOT_FEASIBLE_WITH_CURRENT_TINK_API
                .satisfiesAndroidFeasibilityGate,
        )
        assertFalse(
            ProductionProviderAndroidTinkRawKeyFeasibilityStatus
                .ANDROID_INCONCLUSIVE_REQUIRES_HUMAN_REVIEW
                .satisfiesAndroidFeasibilityGate,
        )
        assertEquals(
            setOf(
                "ANDROID_FEASIBLE_PUBLIC_RAW_KEY_API",
                "ANDROID_NOT_FEASIBLE_WITH_CURRENT_TINK_API",
                "ANDROID_INCONCLUSIVE_REQUIRES_HUMAN_REVIEW",
            ),
            ProductionProviderAndroidTinkRawKeyFeasibilityStatus.entries.map { it.name }.toSet(),
        )
    }

    @Test
    fun nonKeyCommittingAeadRequiresVaultLevelCommitmentAndStrictAad() {
        val policy = contract.aeadPolicy

        assertEquals(EncryptedVaultAeadAlgorithm.XChaCha20Poly1305, policy.primitive)
        assertEquals("skald-vault-v1-record-aad-v1", policy.aadPolicyId)
        assertEquals(1, policy.aadPolicyVersion)
        assertEquals("skald-vault-v1-record-format-v1", policy.recordFormatPolicyId)
        assertEquals(1, policy.recordFormatPolicyVersion)
        assertEquals(ProductionProviderConstructionContractStatus.ImplementedTested, policy.contractStatus)
        assertTrue(policy.nonKeyCommitting)
        assertFalse(policy.successfulDecryptAloneProvesCorrectVaultKey)
        assertTrue(policy.vaultLevelKeyCommitmentRequiredBeforeRecordDecrypt)
        assertTrue(policy.headerAuthenticationRequiredBeforeRecordDecrypt)
        assertEquals(ProductionProviderAadBindingField.entries.toSet(), policy.strictAadBindingFields)
        assertContains(policy.strictAadBindingFields, ProductionProviderAadBindingField.VaultMagicDomainMarker)
        assertContains(policy.strictAadBindingFields, ProductionProviderAadBindingField.VaultFormatVersion)
        assertContains(policy.strictAadBindingFields, ProductionProviderAadBindingField.ProviderSuiteId)
        assertContains(policy.strictAadBindingFields, ProductionProviderAadBindingField.VaultId)
        assertContains(policy.strictAadBindingFields, ProductionProviderAadBindingField.RecordFormatPolicyId)
        assertContains(policy.strictAadBindingFields, ProductionProviderAadBindingField.AadPolicyId)
        assertContains(policy.strictAadBindingFields, ProductionProviderAadBindingField.KeyExpansionPolicyId)
        assertContains(
            policy.strictAadBindingFields,
            ProductionProviderAadBindingField.HeaderCommitmentPrimitivePolicyId,
        )
        assertContains(policy.strictAadBindingFields, ProductionProviderAadBindingField.RecordType)
        assertContains(policy.strictAadBindingFields, ProductionProviderAadBindingField.RecordId)
        assertContains(policy.strictAadBindingFields, ProductionProviderAadBindingField.RecordVersionOrCounter)
        assertContains(
            policy.strictAadBindingFields,
            ProductionProviderAadBindingField.CanonicalHeaderCommitmentValueOrIdentifier,
        )
        assertEquals(
            ProductionProviderAadFailClosedCondition.entries.toSet(),
            policy.aadMismatchFailClosedConditions,
        )
        assertContains(policy.aadMismatchFailClosedConditions, ProductionProviderAadFailClosedCondition.WrongVaultId)
        assertContains(
            policy.aadMismatchFailClosedConditions,
            ProductionProviderAadFailClosedCondition.CiphertextCopiedBetweenVaults,
        )
        assertContains(
            policy.aadMismatchFailClosedConditions,
            ProductionProviderAadFailClosedCondition.CiphertextCopiedBetweenRecordIds,
        )
        assertContains(
            policy.aadMismatchFailClosedConditions,
            ProductionProviderAadFailClosedCondition.ReplayedStaleRecord,
        )
        assertEquals(ProductionProviderTamperCoverage.entries.toSet(), policy.requiredTamperCoverage)
        assertFalse(policy.rawKeyFeasibilityBypassesHeaderCommitment)
        assertTrue(policy.wrongPassphraseResolvedByHeaderCommitmentBeforeRecordDecrypt)
        assertTrue(policy.strictAadSerializationImplemented)
        assertTrue(policy.recordAeadBuildingBlockImplemented)
        assertTrue(policy.recordVersionCounterBoundIntoAad)
        assertTrue(policy.staleRecordEnforcementDeferredToManifestOrStorage)
        assertFalse(policy.productionProviderWired)
        assertFalse(policy.productionAeadExecutionImplemented)
    }

    @Test
    fun providerLevelKatStrategyAndRandomizedAeadPolicyAreStillDisabledHarnessExecuted() {
        val strategy = contract.providerLevelKatStrategyPolicy
        val randomizedAead = contract.randomizedAeadBehavioralKatPolicy

        assertEquals("skald-vault-v1-provider-level-kat-strategy-v1", strategy.policyId)
        assertEquals(ProductionProviderConstructionContractStatus.ImplementedTested, strategy.contractStatus)
        assertEquals(ProductionProviderDeterministicKatVector.entries.toSet(), strategy.deterministicVectorsRequired)
        assertContains(
            strategy.deterministicVectorsRequired,
            ProductionProviderDeterministicKatVector.PassphrasePolicyNormalizationVector,
        )
        assertContains(
            strategy.deterministicVectorsRequired,
            ProductionProviderDeterministicKatVector.Argon2idRootMaterialFixture,
        )
        assertContains(
            strategy.deterministicVectorsRequired,
            ProductionProviderDeterministicKatVector.StrictAadByteVector,
        )
        assertEquals(
            ProductionProviderRandomizedAeadBehavioralKatCheck.entries.toSet(),
            strategy.randomizedAeadBehavioralChecksRequired,
        )
        assertFalse(strategy.fixedCiphertextHexRequiredForRandomizedAead)
        assertTrue(strategy.deterministicAadHexRequired)
        assertTrue(strategy.providerLevelKatsMustRunOnDesktopJvm)
        assertTrue(strategy.providerLevelKatsMustRunOnAndroid)
        assertFalse(strategy.providerKatCompletionImpliesStorageApproval)
        assertTrue(strategy.stillDisabledProviderIntegrationHarnessImplemented)
        assertTrue(strategy.stillDisabledProviderLevelKatExecutionImplemented)
        assertFalse(strategy.productionProviderKatExecutionImplemented)

        assertEquals("skald-vault-v1-randomized-aead-behavioral-kat-v1", randomizedAead.policyId)
        assertEquals(ProductionProviderConstructionContractStatus.ImplementedTested, randomizedAead.contractStatus)
        assertEquals(EncryptedVaultAeadAlgorithm.XChaCha20Poly1305, randomizedAead.primitive)
        assertTrue(randomizedAead.tinkChoosesNonceInternally)
        assertFalse(randomizedAead.fixedCiphertextHexRequired)
        assertTrue(randomizedAead.deterministicAadHexRequired)
        assertContains(
            randomizedAead.behavioralChecksRequired,
            ProductionProviderRandomizedAeadBehavioralKatCheck.EncryptDecryptRoundTrip,
        )
        assertContains(
            randomizedAead.behavioralChecksRequired,
            ProductionProviderRandomizedAeadBehavioralKatCheck.CiphertextNotTreatedAsDeterministic,
        )
        assertContains(
            randomizedAead.behavioralChecksRequired,
            ProductionProviderRandomizedAeadBehavioralKatCheck.WrongHeaderCommitmentContextFails,
        )
        assertFalse(randomizedAead.publicDeterministicNonceTestModeApproved)
        assertTrue(randomizedAead.stillDisabledBehavioralKatExecutionImplemented)
        assertFalse(randomizedAead.productionProviderBehavioralKatExecutionImplemented)
    }

    @Test
    fun verificationOrderKatRequiresHeaderCommitmentBeforeRecordDecrypt() {
        val policy = contract.integratedVerificationOrderKatPolicy

        assertEquals("skald-vault-v1-integrated-verification-order-kat-v1", policy.policyId)
        assertEquals(ProductionProviderConstructionContractStatus.ImplementedTested, policy.contractStatus)
        assertEquals(
            listOf(
                ProductionProviderIntegratedVerificationOrderKatStep.ValidatePassphrasePolicy,
                ProductionProviderIntegratedVerificationOrderKatStep.DeriveArgon2idRootMaterial,
                ProductionProviderIntegratedVerificationOrderKatStep.DeriveHkdfSubkeys,
                ProductionProviderIntegratedVerificationOrderKatStep.CanonicalizeHeaderBytes,
                ProductionProviderIntegratedVerificationOrderKatStep.VerifyHmacHeaderCommitment,
                ProductionProviderIntegratedVerificationOrderKatStep.ConstructRecordAeadAfterHeaderCommitment,
                ProductionProviderIntegratedVerificationOrderKatStep.SerializeStrictAad,
                ProductionProviderIntegratedVerificationOrderKatStep.DecryptRecord,
                ProductionProviderIntegratedVerificationOrderKatStep.RejectRecordDecryptWhenHeaderCommitmentFails,
            ),
            policy.orderedSteps,
        )
        assertTrue(policy.headerCommitmentMustPrecedeRecordDecrypt)
        assertTrue(policy.recordDecryptRejectedWhenHeaderCommitmentFails)
        assertTrue(policy.stillDisabledProviderIntegrationHarnessImplemented)
        assertTrue(policy.stillDisabledVerificationOrderKatExecutionImplemented)
        assertFalse(policy.fullProviderIntegrationImplemented)
        assertFalse(policy.productionVerificationOrderKatsImplemented)
        assertTrue(
            policy.orderedSteps.indexOf(
                ProductionProviderIntegratedVerificationOrderKatStep.VerifyHmacHeaderCommitment,
            ) <
                policy.orderedSteps.indexOf(
                    ProductionProviderIntegratedVerificationOrderKatStep.ConstructRecordAeadAfterHeaderCommitment,
                ),
        )
    }

    @Test
    fun staleRecordManifestPolicyIsImplementedButNotStorageBacked() {
        val policy = contract.staleRecordManifestPolicy

        assertEquals("skald-vault-v1-stale-record-manifest-policy-v1", policy.policyId)
        assertEquals("skald-vault-v1-local-manifest-storage-policy-v1", policy.manifestStoragePolicyId)
        assertEquals(ProductionProviderConstructionContractStatus.ImplementedTested, policy.contractStatus)
        assertTrue(policy.recordVersionCounterBoundIntoAad)
        assertEquals(ProductionProviderStaleRecordManifestBinding.entries.toSet(), policy.bindings)
        assertContains(policy.bindings, ProductionProviderStaleRecordManifestBinding.VaultId)
        assertContains(policy.bindings, ProductionProviderStaleRecordManifestBinding.ProviderSuiteId)
        assertContains(policy.bindings, ProductionProviderStaleRecordManifestBinding.HeaderCommitmentContext)
        assertContains(
            policy.bindings,
            ProductionProviderStaleRecordManifestBinding.LatestTrustedRecordVersionCounterByRecordId,
        )
        assertEquals(ProductionProviderStaleRecordManifestRequirement.entries.toSet(), policy.requirements)
        assertContains(
            policy.requirements,
            ProductionProviderStaleRecordManifestRequirement.TracksLatestTrustedCounterPerRecordId,
        )
        assertContains(
            policy.requirements,
            ProductionProviderStaleRecordManifestRequirement.AtomicUpdateOrCrashSafeRecovery,
        )
        assertContains(
            policy.requirements,
            ProductionProviderStaleRecordManifestRequirement.NoGlobalRollbackClaimWithoutAnchor,
        )
        assertContains(
            policy.requirements,
            ProductionProviderStaleRecordManifestRequirement.NoManifestReadWriteInThisBranch,
        )
        assertTrue(policy.manifestParserImplemented)
        assertTrue(policy.manifestWriterImplemented)
        assertTrue(policy.staleRecordDecisionPolicyImplemented)
        assertFalse(policy.manifestReadWriteImplemented)
        assertFalse(policy.storageIndexReadWriteImplemented)
        assertFalse(policy.staleRecordEnforcementImplemented)
        assertFalse(policy.fullLocalDirectoryRollbackResistanceClaimed)
        assertFalse(policy.externalOrTrustedMonotonicAnchorDesigned)
        assertTrue(policy.antiRollbackAnchorRequiredForGlobalRollbackResistance)
        assertTrue(policy.providerSelectabilityBlockedUntilImplementedAndTested)
    }

    @Test
    fun vaultContainerParserWriterIsImplementedButStorageContractsRemainBlocked() {
        val policy = contract.containerManifestStorageContract

        assertEquals("skald-vault-v1-container-contract-v1", policy.vaultContainerPolicyId)
        assertEquals("skald-vault-v1-manifest-contract-v1", policy.manifestPolicyId)
        assertEquals("skald-vault-v1-local-manifest-storage-policy-v1", policy.storagePolicyId)
        assertEquals(
            "skald-vault-v1-platform-storage-boundary-policy-v1",
            policy.platformStorageBoundaryPolicyId,
        )
        assertEquals("skald-vault-v1-stale-record-manifest-policy-v1", policy.staleRecordPolicyId)
        assertEquals(
            "skald-vault-v1-atomicity-crash-recovery-policy-v1",
            policy.atomicityCrashRecoveryPolicyId,
        )
        assertEquals("skald-vault-v1-atomic-write-strategy-policy-v1", policy.atomicWritePolicyId)
        assertEquals("skald-vault-v1-crash-recovery-policy-v1", policy.crashRecoveryPolicyId)
        assertEquals(
            "skald-vault-v1-storage-interruption-test-policy-v1",
            policy.interruptionTestPolicyId,
        )
        assertEquals(
            "skald-vault-v1-storage-failure-model-policy-v1",
            policy.storageFailureModelPolicyId,
        )
        assertEquals(
            "skald-vault-v1-storage-namespace-path-policy-v1",
            policy.storageNamespacePathPolicyId,
        )
        assertEquals("skald-vault-v1-storage-layout-plan-v1", policy.storageLayoutPlanPolicyId)
        assertEquals("skald-vault-v1-path-containment-planner-v1", policy.pathContainmentPlannerPolicyId)
        assertEquals("skald-vault-v1-platform-storage-root-policy-v1", policy.platformStorageRootPolicyId)
        assertEquals(SkaldVaultV1PlatformRootSettingsPolicy.POLICY_ID, policy.platformRootSettingsPolicyId)
        assertEquals(SkaldVaultV1PlatformRootSettingsPolicy.ANDROID_ROOT_POLICY_ID, policy.androidRootPolicyId)
        assertEquals(SkaldVaultV1PlatformRootSettingsPolicy.LINUX_ROOT_SETTINGS_POLICY_ID, policy.linuxRootSettingsPolicyId)
        assertEquals(
            SkaldVaultV1LinuxCustomRootValidationPolicy.POLICY_ID,
            policy.linuxCustomRootValidationPolicyId,
        )
        assertEquals(
            SkaldVaultV1LinuxRootResolutionPolicy.POLICY_ID,
            policy.linuxRootResolutionPolicyId,
        )
        assertEquals(
            SkaldVaultV1PlatformRootResolverPolicy.POLICY_ID,
            policy.platformRootResolverPolicyId,
        )
        assertEquals(
            SkaldVaultV1PlatformPathConstructionPolicy.POLICY_ID,
            policy.platformPathConstructionBoundaryPolicyId,
        )
        assertEquals(
            SkaldVaultV1StorageSafetyPreflightPolicy.POLICY_ID,
            policy.storageSafetyPreflightBoundaryPolicyId,
        )
        assertEquals(
            SkaldVaultV1DisabledStorageServiceFacade.POLICY_ID,
            policy.disabledStorageServiceFacadePolicyId,
        )
        assertEquals(
            SkaldVaultV1LockSessionLifecyclePolicy.POLICY_ID,
            policy.lockSessionLifecycleBoundaryPolicyId,
        )
        assertEquals(
            SkaldVaultV1RedactionLeakagePolicy.POLICY_ID,
            policy.redactionLeakageBoundaryPolicyId,
        )
        assertEquals(
            SkaldVaultV1PassphrasePolicyGate.POLICY_ID,
            policy.passphrasePolicyBoundaryPolicyId,
        )
        assertEquals(
            SkaldVaultV1ClearWipeStrategyPolicy.POLICY_ID,
            policy.clearWipeStrategyBoundaryPolicyId,
        )
        assertEquals(
            SkaldVaultV1MigrationCorruptionPolicy.POLICY_ID,
            policy.migrationCorruptionBoundaryPolicyId,
        )
        assertEquals(
            SkaldVaultV1PlatformRootSettingsPolicy.OS_KEYRING_PASSPHRASE_POLICY_ID,
            policy.osKeyringPassphrasePolicyId,
        )
        assertEquals(
            SkaldVaultV1PlatformRootSettingsPolicy.PASSWORD_MANAGER_PASSPHRASE_POLICY_ID,
            policy.passwordManagerPassphrasePolicyId,
        )
        assertEquals(SkaldVaultV1PlatformRootSettingsPolicy.PASSPHRASE_FIRST_POLICY_ID, policy.passphraseFirstPolicyId)
        assertEquals("skald-vault-v1-safe-path-construction-policy-v1", policy.safePathConstructionPolicyId)
        assertEquals("skald-vault-v1-symlink-traversal-policy-v1", policy.symlinkTraversalPolicyId)
        assertEquals(
            "skald-vault-v1-storage-permission-ownership-policy-v1",
            policy.storagePermissionOwnershipPolicyId,
        )
        assertEquals("skald-vault-v1-durability-capability-policy-v1", policy.durabilityCapabilityPolicyId)
        assertEquals("skald-vault-v1-durability-fail-closed-policy-v1", policy.durabilityFailClosedPolicyId)
        assertEquals(
            "skald-vault-v1-warning-only-durability-rejection-policy-v1",
            policy.warningOnlyDurabilityRejectionPolicyId,
        )
        assertEquals(
            "skald-vault-v1-in-memory-storage-atomicity-simulator-policy-v1",
            policy.storageAtomicitySimulatorPolicyId,
        )
        assertEquals(
            "skald-vault-v1-secure-storage-boundary-policy-v1",
            policy.secureStorageBoundaryPolicyId,
        )
        assertEquals("skald-vault-v1-anti-rollback-anchor-policy-v1", policy.antiRollbackAnchorPolicyId)
        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            policy.vaultContainerContractStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            policy.manifestContractStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.DocumentedModelOnly,
            policy.storagePolicyContractStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.DocumentedModelOnly,
            policy.platformStorageBoundaryContractStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            policy.staleRecordPolicyStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.DocumentedModelOnly,
            policy.atomicityCrashRecoveryContractStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.DocumentedModelOnly,
            policy.atomicWriteContractStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.DocumentedModelOnly,
            policy.crashRecoveryContractStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.DocumentedModelOnly,
            policy.interruptionTestContractStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.DocumentedModelOnly,
            policy.storageFailureModelStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            policy.storageNamespacePathHygieneStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            policy.storageLayoutPlanStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            policy.pathContainmentPlannerStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.DocumentedModelOnly,
            policy.platformStorageRootContractStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.DocumentedModelOnly,
            policy.platformRootSettingsPolicyStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            policy.linuxCustomRootValidationPolicyStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            policy.linuxRootResolutionPolicyStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            policy.platformRootResolverBoundaryStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            policy.platformPathConstructionBoundaryStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            policy.storageSafetyPreflightBoundaryStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            policy.disabledStorageServiceFacadeStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            policy.persistenceReadinessGateStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            policy.lockSessionLifecycleBoundaryStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            policy.redactionLeakageBoundaryStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            policy.migrationCorruptionBoundaryStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.DocumentedModelOnly,
            policy.safePathConstructionContractStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.DocumentedModelOnly,
            policy.symlinkTraversalContractStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.DocumentedModelOnly,
            policy.storagePermissionOwnershipContractStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.DocumentedModelOnly,
            policy.durabilityCapabilityContractStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.DocumentedModelOnly,
            policy.durabilityFailClosedPolicyStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.DocumentedModelOnly,
            policy.warningOnlyDurabilityRejectionStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.ImplementedTested,
            policy.storageAtomicitySimulatorStatus,
        )
        assertEquals(
            ProductionProviderConstructionContractStatus.DocumentedModelOnly,
            policy.secureStorageBoundaryStatus,
        )

        assertEquals(ProductionProviderVaultContainerField.entries.toSet(), policy.containerFields)
        assertContains(policy.containerFields, ProductionProviderVaultContainerField.VaultMagicDomainMarker)
        assertContains(
            policy.containerFields,
            ProductionProviderVaultContainerField.CanonicalVaultHeaderBytesOrReconstructableFields,
        )
        assertContains(policy.containerFields, ProductionProviderVaultContainerField.HeaderCommitmentTag)
        assertContains(policy.containerFields, ProductionProviderVaultContainerField.ManifestPolicyIdVersion)
        assertContains(policy.containerFields, ProductionProviderVaultContainerField.StoragePolicyIdVersion)
        assertContains(
            policy.containerFields,
            ProductionProviderVaultContainerField.EncryptedRecordsSectionOrReferences,
        )
        assertContains(
            policy.containerFields,
            ProductionProviderVaultContainerField.IntegrityCriticalPreUnlockMetadata,
        )
        assertEquals(ProductionProviderVaultContainerRequirement.entries.toSet(), policy.containerRequirements)
        assertContains(
            policy.containerRequirements,
            ProductionProviderVaultContainerRequirement.NoPlaintextSecretsInContainer,
        )
        assertContains(
            policy.containerRequirements,
            ProductionProviderVaultContainerRequirement.NoRootMaterialSubkeysPassphrasesPlaintextOrTinkKeysets,
        )
        assertContains(
            policy.containerRequirements,
            ProductionProviderVaultContainerRequirement.HeaderCommitmentBeforeRecordAead,
        )
        assertContains(
            policy.containerRequirements,
            ProductionProviderVaultContainerRequirement.NoPersistenceInThisBranch,
        )

        assertEquals(ProductionProviderManifestField.entries.toSet(), policy.manifestFields)
        assertContains(policy.manifestFields, ProductionProviderManifestField.ManifestMagicDomainMarker)
        assertContains(policy.manifestFields, ProductionProviderManifestField.HeaderCommitmentContext)
        assertContains(
            policy.manifestFields,
            ProductionProviderManifestField.LatestTrustedRecordVersionCounterPerRecordId,
        )
        assertContains(policy.manifestFields, ProductionProviderManifestField.CrashRecoveryMetadata)
        assertEquals(ProductionProviderStaleRecordManifestBinding.entries.toSet(), policy.manifestBindings)
        assertEquals(ProductionProviderStaleRecordManifestRequirement.entries.toSet(), policy.manifestRequirements)

        assertEquals(ProductionProviderStorageBoundaryAllowedBytes.entries.toSet(), policy.storageBoundaryAllowedBytes)
        assertContains(
            policy.storageBoundaryAllowedBytes,
            ProductionProviderStorageBoundaryAllowedBytes.EncryptedContainerBytes,
        )
        assertContains(
            policy.storageBoundaryAllowedBytes,
            ProductionProviderStorageBoundaryAllowedBytes.CrashRecoveryTemporaryState,
        )
        assertEquals(
            ProductionProviderStorageBoundaryForbiddenMaterial.entries.toSet(),
            policy.storageBoundaryForbiddenMaterial,
        )
        assertContains(
            policy.storageBoundaryForbiddenMaterial,
            ProductionProviderStorageBoundaryForbiddenMaterial.Passphrases,
        )
        assertContains(
            policy.storageBoundaryForbiddenMaterial,
            ProductionProviderStorageBoundaryForbiddenMaterial.Argon2idRootMaterial,
        )
        assertContains(
            policy.storageBoundaryForbiddenMaterial,
            ProductionProviderStorageBoundaryForbiddenMaterial.TinkKeysets,
        )
        assertContains(
            policy.storageBoundaryForbiddenMaterial,
            ProductionProviderStorageBoundaryForbiddenMaterial.WalletSeedMaterial,
        )
        assertEquals(
            ProductionProviderStorageBoundaryRequirement.entries.toSet(),
            policy.storageBoundaryRequirements,
        )
        assertContains(
            policy.storageBoundaryRequirements,
            ProductionProviderStorageBoundaryRequirement.AlreadyEncryptedOrNonSecretBytesOnly,
        )
        assertContains(
            policy.storageBoundaryRequirements,
            ProductionProviderStorageBoundaryRequirement.StorageLayerNotEncryptionBoundary,
        )
        assertContains(
            policy.storageBoundaryRequirements,
            ProductionProviderStorageBoundaryRequirement.DoNotLogStoredBytesOrSecretIdentifyingPaths,
        )
        assertContains(
            policy.storageBoundaryRequirements,
            ProductionProviderStorageBoundaryRequirement.NoStorageImplementationInThisBranch,
        )
        assertEquals(ProductionProviderStorageAtomicityRequirement.entries.toSet(), policy.atomicityRequirements)
        assertContains(
            policy.atomicityRequirements,
            ProductionProviderStorageAtomicityRequirement.AtomicAtContainerManifestBoundary,
        )
        assertContains(
            policy.atomicityRequirements,
            ProductionProviderStorageAtomicityRequirement.PartialWritesRejected,
        )
        assertContains(
            policy.atomicityRequirements,
            ProductionProviderStorageAtomicityRequirement.NoNewerRecordWithoutManifestAuthority,
        )
        assertContains(
            policy.atomicityRequirements,
            ProductionProviderStorageAtomicityRequirement.NoWriteRecoveryImplementationInThisBranch,
        )
        assertEquals(ProductionProviderAtomicWritePhase.entries.toSet(), policy.atomicWritePhases)
        assertContains(
            policy.atomicWritePhases,
            ProductionProviderAtomicWritePhase.BeforeTempContainerWrite,
        )
        assertContains(
            policy.atomicWritePhases,
            ProductionProviderAtomicWritePhase.AfterCommittingContainerBeforeManifest,
        )
        assertContains(policy.atomicWritePhases, ProductionProviderAtomicWritePhase.StartupRecovery)
        assertEquals(ProductionProviderAtomicWriteRequirement.entries.toSet(), policy.atomicWriteRequirements)
        assertContains(
            policy.atomicWriteRequirements,
            ProductionProviderAtomicWriteRequirement.RetainPreviousKnownGoodUntilCommitComplete,
        )
        assertContains(
            policy.atomicWriteRequirements,
            ProductionProviderAtomicWriteRequirement.NeverAcceptNewerRecordWithoutManifestAuthority,
        )
        assertContains(
            policy.atomicWriteRequirements,
            ProductionProviderAtomicWriteRequirement.DesktopFilesystemStrategyRequiresReview,
        )
        assertContains(
            policy.atomicWriteRequirements,
            ProductionProviderAtomicWriteRequirement.AndroidAppPrivateFilesystemStrategyRequiresReview,
        )
        assertContains(
            policy.atomicWriteRequirements,
            ProductionProviderAtomicWriteRequirement.NoAtomicWriteImplementationInThisBranch,
        )
        assertEquals(ProductionProviderCrashRecoveryCheck.entries.toSet(), policy.crashRecoveryChecks)
        assertContains(
            policy.crashRecoveryChecks,
            ProductionProviderCrashRecoveryCheck.ValidateContainerParserOutput,
        )
        assertContains(
            policy.crashRecoveryChecks,
            ProductionProviderCrashRecoveryCheck.ApplyStaleRecordPolicyAgainstManifestState,
        )
        assertContains(
            policy.crashRecoveryChecks,
            ProductionProviderCrashRecoveryCheck.QuarantineInconsistentState,
        )
        assertEquals(
            ProductionProviderCrashRecoveryFailClosedState.entries.toSet(),
            policy.crashRecoveryFailClosedStates,
        )
        assertContains(
            policy.crashRecoveryFailClosedStates,
            ProductionProviderCrashRecoveryFailClosedState.MissingManifestWhenRequired,
        )
        assertContains(
            policy.crashRecoveryFailClosedStates,
            ProductionProviderCrashRecoveryFailClosedState.ManifestContainerVaultIdMismatch,
        )
        assertContains(
            policy.crashRecoveryFailClosedStates,
            ProductionProviderCrashRecoveryFailClosedState.UnknownRecoveryState,
        )
        assertEquals(ProductionProviderStorageFailureCategory.entries.toSet(), policy.storageFailureCategories)
        assertContains(policy.storageFailureCategories, ProductionProviderStorageFailureCategory.StorageUnavailable)
        assertContains(policy.storageFailureCategories, ProductionProviderStorageFailureCategory.PermissionDenied)
        assertContains(policy.storageFailureCategories, ProductionProviderStorageFailureCategory.DurabilitySyncFailed)
        assertContains(
            policy.storageFailureCategories,
            ProductionProviderStorageFailureCategory.RecoveryQuarantineRequired,
        )
        assertContains(
            policy.storageFailureCategories,
            ProductionProviderStorageFailureCategory.UnknownStorageState,
        )
        assertContains(
            policy.storageFailureCategories,
            ProductionProviderStorageFailureCategory.WarningOnlyDurabilityRejected,
        )
        assertContains(
            policy.storageFailureCategories,
            ProductionProviderStorageFailureCategory.UserConsentDurabilityOverrideRejected,
        )
        assertContains(
            policy.storageFailureCategories,
            ProductionProviderStorageFailureCategory.EquivalentSafeStrategyUnreviewed,
        )
        assertEquals(ProductionProviderStorageNamespacePathRule.entries.toSet(), policy.storageNamespacePathRules)
        assertContains(
            policy.storageNamespacePathRules,
            ProductionProviderStorageNamespacePathRule.StableAsciiNamespaceIds,
        )
        assertContains(
            policy.storageNamespacePathRules,
            ProductionProviderStorageNamespacePathRule.UserControlledStringsNeverBecomePaths,
        )
        assertContains(
            policy.storageNamespacePathRules,
            ProductionProviderStorageNamespacePathRule.NoPathTraversal,
        )
        assertContains(
            policy.storageNamespacePathRules,
            ProductionProviderStorageNamespacePathRule.NoSecretValuesInPathNames,
        )
        assertContains(
            policy.storageNamespacePathRules,
            ProductionProviderStorageNamespacePathRule.NoPathConstructionInThisBranch,
        )
        assertEquals(ProductionProviderStorageLayoutPlanRule.entries.toSet(), policy.storageLayoutPlanRules)
        assertContains(
            policy.storageLayoutPlanRules,
            ProductionProviderStorageLayoutPlanRule.RootlessRelativeSegmentLists,
        )
        assertContains(
            policy.storageLayoutPlanRules,
            ProductionProviderStorageLayoutPlanRule.NoPlatformRootInLayout,
        )
        assertContains(
            policy.storageLayoutPlanRules,
            ProductionProviderStorageLayoutPlanRule.NoFilesystemObjectInLayout,
        )
        assertContains(
            policy.storageLayoutPlanRules,
            ProductionProviderStorageLayoutPlanRule.IncludesCurrentContainer,
        )
        assertContains(
            policy.storageLayoutPlanRules,
            ProductionProviderStorageLayoutPlanRule.IncludesRecordArtifacts,
        )
        assertContains(
            policy.storageLayoutPlanRules,
            ProductionProviderStorageLayoutPlanRule.FuturePathConstructionRequiresReviewedRoot,
        )
        assertEquals(ProductionProviderPathContainmentPlannerRule.entries.toSet(), policy.pathContainmentPlannerRules)
        assertContains(
            policy.pathContainmentPlannerRules,
            ProductionProviderPathContainmentPlannerRule.ReviewedRootTokensOnly,
        )
        assertContains(
            policy.pathContainmentPlannerRules,
            ProductionProviderPathContainmentPlannerRule.ExternalSharedRootsRejected,
        )
        assertContains(
            policy.pathContainmentPlannerRules,
            ProductionProviderPathContainmentPlannerRule.UserPathRootsRejected,
        )
        assertContains(
            policy.pathContainmentPlannerRules,
            ProductionProviderPathContainmentPlannerRule.PlannedLocationsRootTokenBound,
        )
        assertContains(
            policy.pathContainmentPlannerRules,
            ProductionProviderPathContainmentPlannerRule.PlannedLocationsAreNotPlatformPaths,
        )
        assertContains(
            policy.pathContainmentPlannerRules,
            ProductionProviderPathContainmentPlannerRule.SegmentLevelContainmentOnly,
        )
        assertContains(
            policy.pathContainmentPlannerRules,
            ProductionProviderPathContainmentPlannerRule.RealContainmentChecksAbsent,
        )
        assertEquals(
            ProductionProviderPlatformRootSettingsRule.entries.toSet(),
            policy.platformRootSettingsRules,
        )
        assertContains(
            policy.platformRootSettingsRules,
            ProductionProviderPlatformRootSettingsRule.AndroidAppPrivateInternalRootRequired,
        )
        assertContains(
            policy.platformRootSettingsRules,
            ProductionProviderPlatformRootSettingsRule.LinuxLocalShareFallbackPolicy,
        )
        assertContains(
            policy.platformRootSettingsRules,
            ProductionProviderPlatformRootSettingsRule.LinuxCustomRootSettingsPlanned,
        )
        assertContains(
            policy.platformRootSettingsRules,
            ProductionProviderPlatformRootSettingsRule.LinuxCustomRootValidationPolicyImplemented,
        )
        assertContains(
            policy.platformRootSettingsRules,
            ProductionProviderPlatformRootSettingsRule.OsKeyringPassphraseStorageRejected,
        )
        assertContains(
            policy.platformRootSettingsRules,
            ProductionProviderPlatformRootSettingsRule.PasswordManagerIntegrationRejected,
        )
        assertContains(
            policy.platformRootSettingsRules,
            ProductionProviderPlatformRootSettingsRule.SettingsPersistenceUnimplemented,
        )
        assertEquals(
            ProductionProviderLinuxCustomRootValidationRule.entries.toSet(),
            policy.linuxCustomRootValidationRules,
        )
        assertContains(
            policy.linuxCustomRootValidationRules,
            ProductionProviderLinuxCustomRootValidationRule.StaticStringValidationOnly,
        )
        assertContains(
            policy.linuxCustomRootValidationRules,
            ProductionProviderLinuxCustomRootValidationRule.AcceptedCandidateDoesNotResolvePath,
        )
        assertContains(
            policy.linuxCustomRootValidationRules,
            ProductionProviderLinuxCustomRootValidationRule.ContainmentSymlinkPermissionDurabilityReviewStillRequired,
        )
        assertEquals(
            ProductionProviderLinuxRootResolutionRule.entries.toSet(),
            policy.linuxRootResolutionRules,
        )
        assertContains(
            policy.linuxRootResolutionRules,
            ProductionProviderLinuxRootResolutionRule.CallerSuppliedStaticEvidenceOnly,
        )
        assertContains(
            policy.linuxRootResolutionRules,
            ProductionProviderLinuxRootResolutionRule.CustomRootValidationResultIntegrated,
        )
        assertContains(
            policy.linuxRootResolutionRules,
            ProductionProviderLinuxRootResolutionRule.DoesNotResolveLinuxUserDataRoot,
        )
        assertContains(
            policy.linuxRootResolutionRules,
            ProductionProviderLinuxRootResolutionRule.DoesNotEnablePersistenceOrProviderSelection,
        )
        assertEquals(
            ProductionProviderPlatformRootResolverRule.entries.toSet(),
            policy.platformRootResolverRules,
        )
        assertContains(
            policy.platformRootResolverRules,
            ProductionProviderPlatformRootResolverRule.EvidenceOnly,
        )
        assertContains(
            policy.platformRootResolverRules,
            ProductionProviderPlatformRootResolverRule.AndroidAppPrivateInternalEvidenceOnly,
        )
        assertContains(
            policy.platformRootResolverRules,
            ProductionProviderPlatformRootResolverRule.LinuxCustomRootValidationIntegrated,
        )
        assertContains(
            policy.platformRootResolverRules,
            ProductionProviderPlatformRootResolverRule.DoesNotEnablePersistenceOrProviderSelection,
        )
        assertEquals(
            ProductionProviderPlatformPathConstructionRule.entries.toSet(),
            policy.platformPathConstructionRules,
        )
        assertContains(
            policy.platformPathConstructionRules,
            ProductionProviderPlatformPathConstructionRule.EvidenceOnly,
        )
        assertContains(
            policy.platformPathConstructionRules,
            ProductionProviderPlatformPathConstructionRule.ConsumesRootResolverEvidence,
        )
        assertContains(
            policy.platformPathConstructionRules,
            ProductionProviderPlatformPathConstructionRule.ConsumesLogicalStorageLayout,
        )
        assertContains(
            policy.platformPathConstructionRules,
            ProductionProviderPlatformPathConstructionRule.PlannedLocationsAreNotPlatformPaths,
        )
        assertContains(
            policy.platformPathConstructionRules,
            ProductionProviderPlatformPathConstructionRule.DoesNotConstructRealOrAbsolutePaths,
        )
        assertContains(
            policy.platformPathConstructionRules,
            ProductionProviderPlatformPathConstructionRule.DoesNotEnablePersistenceOrProviderSelection,
        )
        assertEquals(
            ProductionProviderStorageSafetyPreflightRule.entries.toSet(),
            policy.storageSafetyPreflightRules,
        )
        assertContains(
            policy.storageSafetyPreflightRules,
            ProductionProviderStorageSafetyPreflightRule.EvidenceOnly,
        )
        assertContains(
            policy.storageSafetyPreflightRules,
            ProductionProviderStorageSafetyPreflightRule.ConsumesPlannedArtifactLocationEvidence,
        )
        assertContains(
            policy.storageSafetyPreflightRules,
            ProductionProviderStorageSafetyPreflightRule.GateVocabularyModeled,
        )
        assertContains(
            policy.storageSafetyPreflightRules,
            ProductionProviderStorageSafetyPreflightRule.DoesNotRunFilesystemChecks,
        )
        assertContains(
            policy.storageSafetyPreflightRules,
            ProductionProviderStorageSafetyPreflightRule.DoesNotEnablePersistenceOrProviderSelection,
        )
        assertEquals(
            ProductionProviderDisabledStorageServiceFacadeRule.entries.toSet(),
            policy.disabledStorageServiceFacadeRules,
        )
        assertContains(
            policy.disabledStorageServiceFacadeRules,
            ProductionProviderDisabledStorageServiceFacadeRule.OperationsFailClosed,
        )
        assertContains(
            policy.disabledStorageServiceFacadeRules,
            ProductionProviderDisabledStorageServiceFacadeRule.NoOperationReturnsSuccess,
        )
        assertContains(
            policy.disabledStorageServiceFacadeRules,
            ProductionProviderDisabledStorageServiceFacadeRule.DoesNotUseFilePathOrFilesystemApis,
        )
        assertContains(
            policy.disabledStorageServiceFacadeRules,
            ProductionProviderDisabledStorageServiceFacadeRule.DoesNotEnablePersistenceOrProviderSelection,
        )
        assertEquals(
            ProductionProviderLockSessionLifecycleRule.entries.toSet(),
            policy.lockSessionLifecycleRules,
        )
        assertEquals(
            ProductionProviderRedactionLeakageRule.entries.toSet(),
            policy.redactionLeakageRules,
        )
        assertEquals(
            ProductionProviderPassphrasePolicyBoundaryRule.entries.toSet(),
            policy.passphrasePolicyBoundaryRules,
        )
        assertEquals(
            ProductionProviderClearWipeStrategyBoundaryRule.entries.toSet(),
            policy.clearWipeStrategyBoundaryRules,
        )
        assertEquals(
            ProductionProviderMigrationCorruptionBoundaryRule.entries.toSet(),
            policy.migrationCorruptionBoundaryRules,
        )
        assertContains(
            policy.lockSessionLifecycleRules,
            ProductionProviderLockSessionLifecycleRule.DefaultDecisionBlocked,
        )
        assertContains(
            policy.lockSessionLifecycleRules,
            ProductionProviderLockSessionLifecycleRule.DoesNotAcceptPassphrasesOrPins,
        )
        assertContains(
            policy.lockSessionLifecycleRules,
            ProductionProviderLockSessionLifecycleRule.DoesNotHoldOrGenerateKeyMaterial,
        )
        assertContains(
            policy.lockSessionLifecycleRules,
            ProductionProviderLockSessionLifecycleRule.DoesNotEnableUnlockPersistenceOrProviderSelection,
        )
        assertContains(
            policy.redactionLeakageRules,
            ProductionProviderRedactionLeakageRule.DoesNotAcceptRawSecrets,
        )
        assertContains(
            policy.redactionLeakageRules,
            ProductionProviderRedactionLeakageRule.DoesNotHashOrFingerprintSecrets,
        )
        assertContains(
            policy.redactionLeakageRules,
            ProductionProviderRedactionLeakageRule.DoesNotImplementLoggingCrashAnalyticsOrSupportExport,
        )
        assertContains(
            policy.redactionLeakageRules,
            ProductionProviderRedactionLeakageRule.DoesNotEnableUnlockPersistenceOrProviderSelection,
        )
        assertContains(
            policy.passphrasePolicyBoundaryRules,
            ProductionProviderPassphrasePolicyBoundaryRule.DefaultDecisionBlocked,
        )
        assertContains(
            policy.passphrasePolicyBoundaryRules,
            ProductionProviderPassphrasePolicyBoundaryRule.DoesNotAcceptRawPassphrasesOrPins,
        )
        assertContains(
            policy.passphrasePolicyBoundaryRules,
            ProductionProviderPassphrasePolicyBoundaryRule.DoesNotHashFingerprintOrRunKdf,
        )
        assertContains(
            policy.passphrasePolicyBoundaryRules,
            ProductionProviderPassphrasePolicyBoundaryRule.DoesNotEnableUnlockPersistenceOrProviderSelection,
        )
        assertContains(
            policy.clearWipeStrategyBoundaryRules,
            ProductionProviderClearWipeStrategyBoundaryRule.DefaultDecisionBlocked,
        )
        assertContains(
            policy.clearWipeStrategyBoundaryRules,
            ProductionProviderClearWipeStrategyBoundaryRule.DoesNotAcceptRawSensitiveValues,
        )
        assertContains(
            policy.clearWipeStrategyBoundaryRules,
            ProductionProviderClearWipeStrategyBoundaryRule.DoesNotClearRealMemory,
        )
        assertContains(
            policy.clearWipeStrategyBoundaryRules,
            ProductionProviderClearWipeStrategyBoundaryRule.DoesNotProveJvmZeroization,
        )
        assertContains(
            policy.clearWipeStrategyBoundaryRules,
            ProductionProviderClearWipeStrategyBoundaryRule.DoesNotEnableUnlockPersistenceOrProviderSelection,
        )
        assertContains(
            policy.migrationCorruptionBoundaryRules,
            ProductionProviderMigrationCorruptionBoundaryRule.EvidenceOnly,
        )
        assertContains(
            policy.migrationCorruptionBoundaryRules,
            ProductionProviderMigrationCorruptionBoundaryRule.DefaultDecisionBlocked,
        )
        assertContains(
            policy.migrationCorruptionBoundaryRules,
            ProductionProviderMigrationCorruptionBoundaryRule.DoesNotAcceptRawStorageOrRecordBytes,
        )
        assertContains(
            policy.migrationCorruptionBoundaryRules,
            ProductionProviderMigrationCorruptionBoundaryRule.DoesNotParseRealStorage,
        )
        assertContains(
            policy.migrationCorruptionBoundaryRules,
            ProductionProviderMigrationCorruptionBoundaryRule.DoesNotRunMigration,
        )
        assertContains(
            policy.migrationCorruptionBoundaryRules,
            ProductionProviderMigrationCorruptionBoundaryRule.DoesNotEnableUnlockPersistenceOrProviderSelection,
        )
        assertEquals(ProductionProviderDurabilityCapabilityRule.entries.toSet(), policy.durabilityCapabilityRules)
        assertContains(
            policy.durabilityCapabilityRules,
            ProductionProviderDurabilityCapabilityRule.RequiredDurabilityFailuresBlockEncryptedVaultPersistence,
        )
        assertContains(
            policy.durabilityCapabilityRules,
            ProductionProviderDurabilityCapabilityRule.WarningOnlyEncryptedVaultPersistenceRejected,
        )
        assertEquals(
            ProductionProviderDurabilityFailClosedCondition.entries.toSet(),
            policy.durabilityFailClosedConditions,
        )
        assertContains(
            policy.durabilityFailClosedConditions,
            ProductionProviderDurabilityFailClosedCondition.DurabilityCapabilityUnknown,
        )
        assertContains(
            policy.durabilityFailClosedConditions,
            ProductionProviderDurabilityFailClosedCondition.DurabilitySyncUnsupported,
        )
        assertContains(
            policy.durabilityFailClosedConditions,
            ProductionProviderDurabilityFailClosedCondition.AtomicReplaceUnsupported,
        )
        assertEquals(
            ProductionProviderWarningOnlyDurabilityRule.entries.toSet(),
            policy.warningOnlyDurabilityRules,
        )
        assertContains(
            policy.warningOnlyDurabilityRules,
            ProductionProviderWarningOnlyDurabilityRule.UserConsentCannotOverrideDurabilityFailure,
        )
        assertEquals(
            ProductionProviderSecureStorageBoundaryRequirement.entries.toSet(),
            policy.secureStorageBoundaryRequirements,
        )
        assertContains(
            policy.secureStorageBoundaryRequirements,
            ProductionProviderSecureStorageBoundaryRequirement.SecureSecretStorageDisabledFailClosed,
        )
        assertContains(
            policy.secureStorageBoundaryRequirements,
            ProductionProviderSecureStorageBoundaryRequirement.SecureMetadataStorageDisabledFailClosed,
        )
        assertContains(
            policy.secureStorageBoundaryRequirements,
            ProductionProviderSecureStorageBoundaryRequirement.LinuxKeyringsNotPrimaryVaultProtection,
        )

        assertTrue(policy.strictAadSubstitutionProtectionModeled)
        assertFalse(policy.strictAadFreshnessProofClaimed)
        assertTrue(policy.localManifestStaleRecordDetectionModeled)
        assertFalse(policy.fullLocalDirectoryRollbackResistanceClaimed)
        assertFalse(policy.externalOrTrustedMonotonicAntiRollbackAnchorImplemented)
        assertTrue(policy.parserImplemented)
        assertTrue(policy.writerImplemented)
        assertTrue(policy.manifestParserImplemented)
        assertTrue(policy.manifestWriterImplemented)
        assertTrue(policy.staleRecordDecisionPolicyImplemented)
        assertFalse(policy.vaultPersistenceImplemented)
        assertFalse(policy.manifestReadWriteImplemented)
        assertFalse(policy.storageIndexReadWriteImplemented)
        assertFalse(policy.platformStorageImplementationAdded)
        assertFalse(policy.filesystemVaultStorageImplemented)
        assertFalse(policy.databaseVaultStorageImplemented)
        assertFalse(policy.dataStoreVaultStorageImplemented)
        assertFalse(policy.sharedPreferencesVaultStorageImplemented)
        assertFalse(policy.tempFileImplementationAdded)
        assertFalse(policy.journalImplementationAdded)
        assertFalse(policy.atomicReplaceImplementationAdded)
        assertFalse(policy.durabilitySyncImplementationAdded)
        assertFalse(policy.crashRecoveryImplementationAdded)
        assertFalse(policy.interruptionTestRuntimeHooksAdded)
        assertFalse(policy.storageFailureRuntimeMappingImplemented)
        assertFalse(policy.storagePathConstructionImplemented)
        assertFalse(policy.platformRootResolutionImplemented)
        assertFalse(policy.platformRootSelectionImplemented)
        assertTrue(policy.androidAppPrivateInternalRootPolicyModeled)
        assertTrue(policy.androidExternalStorageRejected)
        assertTrue(policy.androidUserSelectedRootRejected)
        assertFalse(policy.androidRootResolutionImplemented)
        assertFalse(policy.androidBackupBehaviorReviewed)
        assertFalse(policy.androidUninstallBehaviorReviewed)
        assertTrue(policy.linuxDefaultUserDataRootPolicyModeled)
        assertTrue(policy.linuxLocalShareFallbackModeled)
        assertTrue(policy.linuxCustomRootSettingsContractModeled)
        assertFalse(policy.linuxCustomRootUsable)
        assertTrue(policy.linuxCustomRootValidationImplemented)
        assertTrue(policy.linuxRootResolutionEvidenceModeled)
        assertTrue(policy.linuxRootResolutionStillDisabled)
        assertTrue(policy.linuxRootResolutionDoesNotResolveFilesystem)
        assertTrue(policy.linuxRootResolutionDoesNotConstructPaths)
        assertTrue(policy.linuxRootResolutionDoesNotEnablePersistence)
        assertTrue(policy.platformRootResolverBoundaryModeled)
        assertTrue(policy.androidAppPrivateRootEvidenceModeled)
        assertTrue(policy.linuxDefaultRootResolverEvidenceModeled)
        assertTrue(policy.platformRootResolverStillDisabled)
        assertTrue(policy.platformRootResolverDoesNotEnablePersistence)
        assertTrue(policy.platformRootResolverDoesNotProveDurability)
        assertTrue(policy.platformRootResolverDoesNotEnableProviderSelection)
        assertTrue(policy.platformPathConstructionBoundaryModeled)
        assertTrue(policy.platformPathConstructionStillDisabled)
        assertTrue(policy.platformPathConstructionDoesNotConstructRealPaths)
        assertTrue(policy.platformPathConstructionDoesNotEnablePersistence)
        assertTrue(policy.platformPathConstructionDoesNotEnableProviderSelection)
        assertTrue(policy.plannedArtifactLocationEvidenceModeled)
        assertTrue(policy.storageSafetyPreflightBoundaryModeled)
        assertTrue(policy.storageSafetyPreflightStillDisabled)
        assertTrue(policy.storageSafetyPreflightDoesNotRunFilesystemChecks)
        assertTrue(policy.storageSafetyPreflightDoesNotEnablePersistence)
        assertTrue(policy.storageSafetyPreflightDoesNotEnableProviderSelection)
        assertTrue(policy.storageSafetyGateVocabularyModeled)
        assertTrue(policy.disabledStorageServiceFacadeModeled)
        assertTrue(policy.storageServiceFacadeStillDisabled)
        assertTrue(policy.storageServiceOperationsFailClosed)
        assertTrue(policy.storageServiceDoesNotUseFilesystem)
        assertTrue(policy.storageServiceDoesNotEnablePersistence)
        assertTrue(policy.storageServiceDoesNotEnableProviderSelection)
        assertTrue(policy.storageOperationFailureVocabularyModeled)
        assertFalse(policy.storageServiceOperationSuccessPathImplemented)
        assertTrue(policy.persistenceReadinessGateModeled)
        assertTrue(policy.persistenceReadinessGateStillBlocked)
        assertTrue(policy.persistenceReadinessGateComposesStorageEvidence)
        assertTrue(policy.persistenceReadinessGateDoesNotUseFilesystem)
        assertTrue(policy.persistenceReadinessGateDoesNotEnablePersistence)
        assertTrue(policy.persistenceReadinessGateDoesNotEnableProviderSelection)
        assertTrue(policy.persistenceReadinessFailureVocabularyModeled)
        assertTrue(policy.lockSessionLifecycleBoundaryModeled)
        assertTrue(policy.lockSessionLifecycleStillDisabled)
        assertTrue(policy.unlockDecisionStillBlocked)
        assertTrue(policy.activeSessionUnavailable)
        assertTrue(policy.lockSessionBoundaryDoesNotAcceptPassphrases)
        assertTrue(policy.lockSessionBoundaryDoesNotHoldKeys)
        assertTrue(policy.lockSessionBoundaryDoesNotEnablePersistence)
        assertTrue(policy.lockSessionBoundaryDoesNotEnableProviderSelection)
        assertTrue(policy.lockSessionFailureVocabularyModeled)
        assertTrue(policy.redactionLeakageBoundaryModeled)
        assertTrue(policy.redactionLeakageBoundaryStillDisabled)
        assertTrue(policy.redactionLeakageClassifiesSensitiveValueKinds)
        assertTrue(policy.redactionLeakageDoesNotAcceptRawSecrets)
        assertTrue(policy.redactionLeakageDoesNotHashSecrets)
        assertTrue(policy.redactionLeakageDoesNotLog)
        assertTrue(policy.redactionLeakageDoesNotEnablePersistence)
        assertTrue(policy.redactionLeakageDoesNotEnableProviderSelection)
        assertTrue(policy.redactionLeakageFailureVocabularyModeled)
        assertTrue(policy.passphrasePolicyBoundaryModeled)
        assertTrue(policy.passphrasePolicyStillDisabled)
        assertTrue(policy.passphraseInputStillRejected)
        assertTrue(policy.passphrasePolicyDoesNotAcceptRawPassphrases)
        assertTrue(policy.passphrasePolicyDoesNotHashOrFingerprint)
        assertTrue(policy.passphrasePolicyDoesNotRunKdf)
        assertTrue(policy.passphrasePolicyDoesNotEnableUnlock)
        assertTrue(policy.passphrasePolicyDoesNotEnablePersistence)
        assertTrue(policy.passphrasePolicyDoesNotEnableProviderSelection)
        assertTrue(policy.passphrasePolicyFailureVocabularyModeled)
        assertTrue(policy.clearWipeStrategyBoundaryModeled)
        assertTrue(policy.clearWipeStrategyStillDisabled)
        assertTrue(policy.clearWipeStrategyDoesNotAcceptRawSensitiveValues)
        assertTrue(policy.clearWipeStrategyDoesNotClearRealMemory)
        assertTrue(policy.clearWipeStrategyDoesNotProveJvmZeroization)
        assertTrue(policy.clearWipeStrategyDoesNotEnableUnlock)
        assertTrue(policy.clearWipeStrategyDoesNotEnablePersistence)
        assertTrue(policy.clearWipeStrategyDoesNotEnableProviderSelection)
        assertTrue(policy.clearWipeFailureVocabularyModeled)
        assertFalse(policy.settingsUiImplemented)
        assertFalse(policy.settingsPersistenceImplemented)
        assertTrue(policy.osKeyringPrimaryStorageRejected)
        assertTrue(policy.osKeyringPassphraseStorageRejected)
        assertTrue(policy.passwordManagerIntegrationRejected)
        assertTrue(policy.passphraseFirstDefaultModeled)
        assertFalse(policy.actualPathConstructionImplemented)
        assertFalse(policy.pathJoinImplementationAdded)
        assertFalse(policy.pathContainmentCheckImplementationAdded)
        assertFalse(policy.directoryCreationImplementationAdded)
        assertFalse(policy.symlinkCheckImplementationAdded)
        assertFalse(policy.permissionCheckImplementationAdded)
        assertFalse(policy.durabilityProbeImplementationAdded)
        assertFalse(policy.warningOnlyEncryptedVaultPersistenceAllowed)
        assertFalse(policy.userConsentDurabilityOverrideAllowed)
        assertFalse(policy.equivalentSafeDurabilityStrategyApproved)
        assertFalse(policy.androidDurabilityReviewed)
        assertFalse(policy.desktopDurabilityReviewed)
        assertTrue(policy.storageNamespacePathPolicyImplemented)
        assertTrue(policy.storagePathSegmentEncodingImplemented)
        assertTrue(policy.storageLayoutPlanImplemented)
        assertTrue(policy.storageLayoutLocationsRootless)
        assertTrue(policy.storageLayoutUsesSafeSegmentsOnly)
        assertTrue(policy.reviewedRootTokenPolicyImplemented)
        assertTrue(policy.pathContainmentPlannerImplemented)
        assertTrue(policy.plannedLocationsRootTokenBound)
        assertFalse(policy.plannedLocationsArePlatformPaths)
        assertTrue(policy.plannedLocationsUseSafeSegmentsOnly)
        assertTrue(policy.inMemoryAtomicityCrashSimulatorImplemented)
        assertTrue(policy.inMemoryAtomicityCrashSimulatorInterruptionTestsExecuted)
        assertTrue(policy.inMemoryAtomicityCrashSimulatorRecoveryDecisionsTested)
        assertFalse(policy.secureSecretStorageSuccessPathImplemented)
        assertFalse(policy.secureMetadataStorageSuccessPathImplemented)
        assertFalse(policy.atomicWriteRecoveryImplementationAdded)
    }

    @Test
    fun providerKatAndManifestEvidenceMustBeImplementedNotMerelyDocumented() {
        val gates = setOf(
            ProductionProviderAcceptanceGate.ProviderLevelKatStrategyApproved,
            ProductionProviderAcceptanceGate.RandomizedAeadBehavioralKatPolicyApproved,
            ProductionProviderAcceptanceGate.IntegratedVerificationOrderKatPolicyApproved,
            ProductionProviderAcceptanceGate.ManifestContractApproved,
            ProductionProviderAcceptanceGate.StaleRecordManifestPolicyApproved,
            ProductionProviderAcceptanceGate.StoragePolicyContractApproved,
            ProductionProviderAcceptanceGate.PlatformStorageBoundaryContractApproved,
            ProductionProviderAcceptanceGate.AtomicityCrashRecoveryContractApproved,
            ProductionProviderAcceptanceGate.AtomicWriteStrategyContractApproved,
            ProductionProviderAcceptanceGate.CrashRecoveryContractApproved,
            ProductionProviderAcceptanceGate.StorageInterruptionTestContractApproved,
            ProductionProviderAcceptanceGate.StorageAtomicityCrashSimulatorExecuted,
            ProductionProviderAcceptanceGate.StorageFailureModelContractApproved,
            ProductionProviderAcceptanceGate.StorageNamespacePathHygieneContractApproved,
            ProductionProviderAcceptanceGate.StorageLayoutPlanImplementedAndTested,
            ProductionProviderAcceptanceGate.PathContainmentPlannerImplementedAndTested,
            ProductionProviderAcceptanceGate.PlatformStorageRootContractApproved,
            ProductionProviderAcceptanceGate.PlatformRootSettingsPolicyApproved,
            ProductionProviderAcceptanceGate.LinuxCustomRootValidationPolicyImplementedAndTested,
            ProductionProviderAcceptanceGate.LinuxRootResolutionPolicyImplementedAndTested,
            ProductionProviderAcceptanceGate.PlatformRootResolverBoundaryImplementedAndTested,
            ProductionProviderAcceptanceGate.PlatformPathConstructionBoundaryImplementedAndTested,
            ProductionProviderAcceptanceGate.StorageSafetyPreflightBoundaryImplementedAndTested,
            ProductionProviderAcceptanceGate.DisabledStorageServiceFacadeImplementedAndTested,
            ProductionProviderAcceptanceGate.PersistenceReadinessGateImplementedAndTested,
            ProductionProviderAcceptanceGate.LockSessionLifecycleBoundaryImplementedAndTested,
            ProductionProviderAcceptanceGate.RedactionLeakageBoundaryImplementedAndTested,
            ProductionProviderAcceptanceGate.PassphrasePolicyBoundaryImplementedAndTested,
            ProductionProviderAcceptanceGate.SafePathConstructionContractApproved,
            ProductionProviderAcceptanceGate.SymlinkTraversalContractApproved,
            ProductionProviderAcceptanceGate.StoragePermissionOwnershipContractApproved,
            ProductionProviderAcceptanceGate.DurabilityCapabilityContractApproved,
            ProductionProviderAcceptanceGate.DurabilityFailClosedPolicyApproved,
            ProductionProviderAcceptanceGate.WarningOnlyDurabilityPersistenceRejected,
            ProductionProviderAcceptanceGate.SecureStorageBoundaryContractApproved,
            ProductionProviderAcceptanceGate.RollbackLimitationAndAntiRollbackAnchorReviewed,
        )
        val states = listOf(
            ProductionProviderAcceptanceEvidenceState.Missing to
                ProductionProviderAcceptanceBlocker.MissingGateEvidence,
            ProductionProviderAcceptanceEvidenceState.Unknown to
                ProductionProviderAcceptanceBlocker.UnknownGateEvidence,
            ProductionProviderAcceptanceEvidenceState.Failed to
                ProductionProviderAcceptanceBlocker.FailedGateEvidence,
            ProductionProviderAcceptanceEvidenceState.Unsupported to
                ProductionProviderAcceptanceBlocker.UnsupportedGateEvidence,
            ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly to
                ProductionProviderAcceptanceBlocker.ModelOnlyGateEvidence,
        )

        gates.forEach { gate ->
            states.forEach { (state, blocker) ->
                assertGateBlocks(gate = gate, state = state, blocker = blocker)
            }
        }
    }

    @Test
    fun currentEvidenceRepresentsStillDisabledProviderKatExecutionButRemainsBlocked() {
        val evidence = ProductionProviderAcceptanceEvidence.currentDesignOnly()

        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            evidence.stateFor(ProductionProviderAcceptanceGate.ProviderLevelKatStrategyApproved),
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            evidence.stateFor(ProductionProviderAcceptanceGate.RandomizedAeadBehavioralKatPolicyApproved),
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            evidence.stateFor(ProductionProviderAcceptanceGate.IntegratedVerificationOrderKatPolicyApproved),
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            evidence.stateFor(ProductionProviderAcceptanceGate.VaultContainerContractApproved),
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            evidence.stateFor(ProductionProviderAcceptanceGate.ManifestContractApproved),
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            evidence.stateFor(ProductionProviderAcceptanceGate.StaleRecordManifestPolicyApproved),
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
            evidence.stateFor(ProductionProviderAcceptanceGate.StoragePolicyContractApproved),
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
            evidence.stateFor(ProductionProviderAcceptanceGate.PlatformStorageBoundaryContractApproved),
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
            evidence.stateFor(ProductionProviderAcceptanceGate.AtomicityCrashRecoveryContractApproved),
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
            evidence.stateFor(ProductionProviderAcceptanceGate.AtomicWriteStrategyContractApproved),
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
            evidence.stateFor(ProductionProviderAcceptanceGate.CrashRecoveryContractApproved),
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
            evidence.stateFor(ProductionProviderAcceptanceGate.StorageInterruptionTestContractApproved),
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            evidence.stateFor(ProductionProviderAcceptanceGate.StorageAtomicityCrashSimulatorExecuted),
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
            evidence.stateFor(ProductionProviderAcceptanceGate.StorageFailureModelContractApproved),
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            evidence.stateFor(ProductionProviderAcceptanceGate.StorageNamespacePathHygieneContractApproved),
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            evidence.stateFor(ProductionProviderAcceptanceGate.StorageLayoutPlanImplementedAndTested),
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            evidence.stateFor(ProductionProviderAcceptanceGate.PathContainmentPlannerImplementedAndTested),
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
            evidence.stateFor(ProductionProviderAcceptanceGate.PlatformStorageRootContractApproved),
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
            evidence.stateFor(ProductionProviderAcceptanceGate.PlatformRootSettingsPolicyApproved),
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            evidence.stateFor(ProductionProviderAcceptanceGate.LinuxCustomRootValidationPolicyImplementedAndTested),
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            evidence.stateFor(ProductionProviderAcceptanceGate.LinuxRootResolutionPolicyImplementedAndTested),
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            evidence.stateFor(ProductionProviderAcceptanceGate.PlatformRootResolverBoundaryImplementedAndTested),
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            evidence.stateFor(ProductionProviderAcceptanceGate.PlatformPathConstructionBoundaryImplementedAndTested),
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            evidence.stateFor(ProductionProviderAcceptanceGate.StorageSafetyPreflightBoundaryImplementedAndTested),
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            evidence.stateFor(ProductionProviderAcceptanceGate.DisabledStorageServiceFacadeImplementedAndTested),
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            evidence.stateFor(ProductionProviderAcceptanceGate.PersistenceReadinessGateImplementedAndTested),
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            evidence.stateFor(ProductionProviderAcceptanceGate.LockSessionLifecycleBoundaryImplementedAndTested),
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            evidence.stateFor(ProductionProviderAcceptanceGate.RedactionLeakageBoundaryImplementedAndTested),
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            evidence.stateFor(ProductionProviderAcceptanceGate.PassphrasePolicyBoundaryImplementedAndTested),
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
            evidence.stateFor(ProductionProviderAcceptanceGate.RedactionLeakageChecksPassed),
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
            evidence.stateFor(ProductionProviderAcceptanceGate.SafePathConstructionContractApproved),
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
            evidence.stateFor(ProductionProviderAcceptanceGate.SymlinkTraversalContractApproved),
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
            evidence.stateFor(ProductionProviderAcceptanceGate.StoragePermissionOwnershipContractApproved),
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
            evidence.stateFor(ProductionProviderAcceptanceGate.DurabilityCapabilityContractApproved),
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
            evidence.stateFor(ProductionProviderAcceptanceGate.DurabilityFailClosedPolicyApproved),
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
            evidence.stateFor(ProductionProviderAcceptanceGate.WarningOnlyDurabilityPersistenceRejected),
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
            evidence.stateFor(ProductionProviderAcceptanceGate.SecureStorageBoundaryContractApproved),
        )
        assertEquals(
            ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
            evidence.stateFor(ProductionProviderAcceptanceGate.RollbackLimitationAndAntiRollbackAnchorReviewed),
        )

        val assessment = contract.assess(evidence)

        assertFalse(assessment.allRequiredGatesSatisfied)
        assertContains(assessment.blockers, ProductionProviderAcceptanceBlocker.ModelOnlyGateEvidence)
        assertContains(assessment.blockers, ProductionProviderAcceptanceBlocker.UnknownGateEvidence)
        assertContains(
            assessment.blockers,
            ProductionProviderAcceptanceBlocker.ProductionProviderSelectionStillDisabled,
        )
        assertFalse(assessment.productionProviderSelectable)
        assertFalse(assessment.productionPersistenceAllowed)
    }

    @Test
    fun stillDisabledProviderFacadePolicyIsMetadataOnlyAndNonSelectable() {
        val policy = contract.stillDisabledProviderFacadePolicy

        assertEquals(ProductionProviderConstructionContractStatus.ImplementedTested, policy.contractStatus)
        assertTrue(policy.facadeImplemented)
        assertTrue(policy.metadataOnly)
        assertTrue(policy.operationsDisabled)
        assertFalse(policy.selectableThroughProviderRegistry)
        assertFalse(policy.debugOrTestFlagCanSelect)
        assertFalse(policy.vaultCreationEnabled)
        assertFalse(policy.vaultUnlockEnabled)
        assertFalse(policy.vaultPersistenceEnabled)
        assertFalse(policy.manifestStorageImplemented)
        assertFalse(policy.secureStorageEnabled)
        assertFalse(policy.katHarnessSuccessImpliesSelectability)
        assertFalse(policy.calibrationEvidenceImpliesSelectability)
        assertFalse(policy.releaseApprovalPresent)
    }

    @Test
    fun stillDisabledProviderFacadeEvidenceMustBeKnownAndImplemented() {
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.StillDisabledProviderFacadeApproved,
            state = ProductionProviderAcceptanceEvidenceState.Missing,
            blocker = ProductionProviderAcceptanceBlocker.MissingGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.StillDisabledProviderFacadeApproved,
            state = ProductionProviderAcceptanceEvidenceState.Unknown,
            blocker = ProductionProviderAcceptanceBlocker.UnknownGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.StillDisabledProviderFacadeApproved,
            state = ProductionProviderAcceptanceEvidenceState.Failed,
            blocker = ProductionProviderAcceptanceBlocker.FailedGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.StillDisabledProviderFacadeApproved,
            state = ProductionProviderAcceptanceEvidenceState.Unsupported,
            blocker = ProductionProviderAcceptanceBlocker.UnsupportedGateEvidence,
        )
        assertGateBlocks(
            gate = ProductionProviderAcceptanceGate.StillDisabledProviderFacadeApproved,
            state = ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
            blocker = ProductionProviderAcceptanceBlocker.ModelOnlyGateEvidence,
        )

        val assessment = contract.assess(ProductionProviderAcceptanceEvidence.currentDesignOnly())

        assertEquals(
            ProductionProviderAcceptanceEvidenceState.ImplementedTested,
            ProductionProviderAcceptanceEvidence.currentDesignOnly()
                .stateFor(ProductionProviderAcceptanceGate.StillDisabledProviderFacadeApproved),
        )
        assertFalse(assessment.productionProviderSelectable)
        assertFalse(assessment.productionPersistenceAllowed)
    }

    @Test
    fun implementedRecordAeadEvidenceDoesNotBypassKatOrManifestGates() {
        val evidence = ProductionProviderAcceptanceEvidence(
            gateStates = ProductionProviderAcceptanceGate.entries.associateWith {
                ProductionProviderAcceptanceEvidenceState.Satisfied
            } + mapOf(
                ProductionProviderAcceptanceGate.AeadAadPolicyApproved to
                    ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                ProductionProviderAcceptanceGate.TinkRawKeyFeasibilityApproved to
                    ProductionProviderAcceptanceEvidenceState.Satisfied,
                ProductionProviderAcceptanceGate.ProviderLevelKatStrategyApproved to
                    ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                ProductionProviderAcceptanceGate.RandomizedAeadBehavioralKatPolicyApproved to
                    ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                ProductionProviderAcceptanceGate.IntegratedVerificationOrderKatPolicyApproved to
                    ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                ProductionProviderAcceptanceGate.StaleRecordManifestPolicyApproved to
                    ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
            ),
        )

        val assessment = contract.assess(evidence)

        assertFalse(assessment.allRequiredGatesSatisfied)
        assertContains(assessment.blockers, ProductionProviderAcceptanceBlocker.ModelOnlyGateEvidence)
        assertFalse(assessment.productionProviderSelectable)
        assertFalse(assessment.productionPersistenceAllowed)
    }

    @Test
    fun constructionContractStatusesAreExactAndOnlyImplementedTestedSatisfiesSelectability() {
        assertEquals(
            setOf(
                "Missing",
                "Unknown",
                "DocumentedModelOnly",
                "FailedUnsupported",
                "ApprovedForFutureImplementation",
                "ImplementedTested",
            ),
            ProductionProviderConstructionContractStatus.entries.map { it.name }.toSet(),
        )
        assertFalse(ProductionProviderConstructionContractStatus.Missing.satisfiesProductionSelectability)
        assertFalse(ProductionProviderConstructionContractStatus.Unknown.satisfiesProductionSelectability)
        assertFalse(ProductionProviderConstructionContractStatus.DocumentedModelOnly.satisfiesProductionSelectability)
        assertFalse(
            ProductionProviderConstructionContractStatus.ApprovedForFutureImplementation
                .satisfiesProductionSelectability,
        )
        assertFalse(ProductionProviderConstructionContractStatus.FailedUnsupported.satisfiesProductionSelectability)
        assertTrue(ProductionProviderConstructionContractStatus.ImplementedTested.satisfiesProductionSelectability)
    }

    @Test
    fun testVectorContractStatusesAreExactAndOnlyProductionImplementedSatisfiesSelectability() {
        assertEquals(
            setOf(
                "Missing",
                "Unknown",
                "DocumentedOnly",
                "InputsDefinedOutputsPending",
                "VectorsCompleteInTestScope",
                "FailedUnsupported",
                "ProductionImplementedTested",
            ),
            ProductionProviderTestVectorContractStatus.entries.map { it.name }.toSet(),
        )
        assertFalse(ProductionProviderTestVectorContractStatus.Missing.satisfiesProductionSelectability)
        assertFalse(ProductionProviderTestVectorContractStatus.Unknown.satisfiesProductionSelectability)
        assertFalse(ProductionProviderTestVectorContractStatus.DocumentedOnly.satisfiesProductionSelectability)
        assertFalse(
            ProductionProviderTestVectorContractStatus.InputsDefinedOutputsPending
                .satisfiesProductionSelectability,
        )
        assertFalse(
            ProductionProviderTestVectorContractStatus.VectorsCompleteInTestScope
                .satisfiesProductionSelectability,
        )
        assertFalse(ProductionProviderTestVectorContractStatus.FailedUnsupported.satisfiesProductionSelectability)
        assertTrue(
            ProductionProviderTestVectorContractStatus.ProductionImplementedTested
                .satisfiesProductionSelectability,
        )
    }

    @Test
    fun runtimeRandomnessAndAndroidWrappingPoliciesStaySeparate() {
        val randomness = contract.runtimeRandomnessPolicy
        val androidWrapping = contract.androidWrappingPolicy

        assertEquals(RuntimeRandomnessSourceKind.OsCryptographicRandomness, randomness.sourceKind)
        assertEquals("OS SecureRandom", randomness.exactProviderPathLabel)
        assertTrue(randomness.unknownProviderStateBlocksVaultCreation)
        assertTrue(randomness.forbiddenRandomApisRejected)
        assertFalse(randomness.tinySampleIsEntropyQualityProof)

        assertTrue(androidWrapping.optionalFutureConvenienceLayerOnly)
        assertFalse(androidWrapping.hardwareOrBiometricWrappingRequired)
        assertTrue(androidWrapping.passphrasePrimaryAuthority)
        assertTrue(androidWrapping.passwordOnlyModeFirstClass)
        assertFalse(androidWrapping.biometricUnlockReplacesPassphrase)
        assertTrue(androidWrapping.roughlyWeeklyPassphrasePromptAfterBiometricUnlockRequired)
    }

    private fun evidenceWith(
        gate: ProductionProviderAcceptanceGate,
        state: ProductionProviderAcceptanceEvidenceState,
    ): ProductionProviderAcceptanceEvidence =
        ProductionProviderAcceptanceEvidence(
            gateStates = ProductionProviderAcceptanceGate.entries.associateWith {
                ProductionProviderAcceptanceEvidenceState.Satisfied
            } + mapOf(gate to state),
        )

    private fun assertGateBlocks(
        gate: ProductionProviderAcceptanceGate,
        state: ProductionProviderAcceptanceEvidenceState,
        blocker: ProductionProviderAcceptanceBlocker,
    ) {
        val assessment = contract.assess(evidenceWith(gate, state))

        assertFalse(assessment.allRequiredGatesSatisfied)
        assertContains(assessment.blockers, blocker)
        assertFalse(assessment.productionProviderSelectable)
        assertFalse(assessment.productionPersistenceAllowed)
    }
}
