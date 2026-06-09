package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.Argon2idVersion
import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.EncryptedVaultAeadAlgorithm
import com.libertasprimordium.skald.security.ProductionProviderAadBindingField
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceBlocker
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidence
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceEvidenceState
import com.libertasprimordium.skald.security.ProductionProviderAcceptanceGate
import com.libertasprimordium.skald.security.ProductionProviderAndroidTinkRawKeyFeasibilityStatus
import com.libertasprimordium.skald.security.ProductionProviderHeaderCommitmentFailClosedCondition
import com.libertasprimordium.skald.security.ProductionProviderHeaderCommitmentField
import com.libertasprimordium.skald.security.ProductionProviderPassphraseAllowedClass
import com.libertasprimordium.skald.security.ProductionProviderPassphraseForbiddenClass
import com.libertasprimordium.skald.security.ProductionProviderPassphraseNoTransformRule
import com.libertasprimordium.skald.security.ProductionProviderPrimitiveRole
import com.libertasprimordium.skald.security.ProductionProviderSuiteModel
import com.libertasprimordium.skald.security.ProductionProviderTamperCoverage
import com.libertasprimordium.skald.security.ProductionProviderTinkRawKeyFeasibilityStatus
import com.libertasprimordium.skald.security.ProductionProviderWeakDeviceFailureMode
import com.libertasprimordium.skald.security.RuntimeRandomnessSourceKind
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
        val missing = contract.assess(
            evidenceWith(
                ProductionProviderAcceptanceGate.HeaderCommitmentPolicyApproved,
                ProductionProviderAcceptanceEvidenceState.Missing,
            ),
        )
        val unknown = contract.assess(
            evidenceWith(
                ProductionProviderAcceptanceGate.HeaderCommitmentPolicyApproved,
                ProductionProviderAcceptanceEvidenceState.Unknown,
            ),
        )

        assertFalse(missing.allRequiredGatesSatisfied)
        assertContains(missing.blockers, ProductionProviderAcceptanceBlocker.MissingGateEvidence)
        assertFalse(missing.productionProviderSelectable)
        assertFalse(missing.productionPersistenceAllowed)

        assertFalse(unknown.allRequiredGatesSatisfied)
        assertContains(unknown.blockers, ProductionProviderAcceptanceBlocker.UnknownGateEvidence)
        assertFalse(unknown.productionProviderSelectable)
        assertFalse(unknown.productionPersistenceAllowed)
    }

    @Test
    fun passphraseEncodingEvidenceMustBePresentAndSupported() {
        val missing = contract.assess(
            evidenceWith(
                ProductionProviderAcceptanceGate.PassphraseEncodingPolicyApproved,
                ProductionProviderAcceptanceEvidenceState.Missing,
            ),
        )
        val unsupported = contract.assess(
            evidenceWith(
                ProductionProviderAcceptanceGate.PassphraseEncodingPolicyApproved,
                ProductionProviderAcceptanceEvidenceState.Unsupported,
            ),
        )

        assertFalse(missing.allRequiredGatesSatisfied)
        assertContains(missing.blockers, ProductionProviderAcceptanceBlocker.MissingGateEvidence)
        assertFalse(missing.productionProviderSelectable)
        assertFalse(missing.productionPersistenceAllowed)

        assertFalse(unsupported.allRequiredGatesSatisfied)
        assertContains(unsupported.blockers, ProductionProviderAcceptanceBlocker.UnsupportedGateEvidence)
        assertFalse(unsupported.productionProviderSelectable)
        assertFalse(unsupported.productionPersistenceAllowed)
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
        val missing = contract.assess(
            evidenceWith(
                ProductionProviderAcceptanceGate.Argon2idBoundedCalibrationApproved,
                ProductionProviderAcceptanceEvidenceState.Missing,
            ),
        )
        val unknown = contract.assess(
            evidenceWith(
                ProductionProviderAcceptanceGate.Argon2idBoundedCalibrationApproved,
                ProductionProviderAcceptanceEvidenceState.Unknown,
            ),
        )
        val failed = contract.assess(
            evidenceWith(
                ProductionProviderAcceptanceGate.Argon2idBoundedCalibrationApproved,
                ProductionProviderAcceptanceEvidenceState.Failed,
            ),
        )

        assertContains(missing.blockers, ProductionProviderAcceptanceBlocker.MissingGateEvidence)
        assertContains(unknown.blockers, ProductionProviderAcceptanceBlocker.UnknownGateEvidence)
        assertContains(failed.blockers, ProductionProviderAcceptanceBlocker.FailedGateEvidence)
        assertFalse(missing.allRequiredGatesSatisfied)
        assertFalse(unknown.allRequiredGatesSatisfied)
        assertFalse(failed.allRequiredGatesSatisfied)
        assertFalse(missing.productionProviderSelectable)
        assertFalse(unknown.productionProviderSelectable)
        assertFalse(failed.productionProviderSelectable)
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

        assertTrue(policy.requiredBeforeRecordDecrypt)
        assertFalse(policy.recordDecryptAllowedBeforeVerification)
        assertTrue(policy.commitmentKeyMaterialSeparatedFromRecordAeadKeyMaterial)
        assertFalse(policy.productionExecutionImplemented)
        assertEquals(
            ProductionProviderHeaderCommitmentField.entries.toSet(),
            policy.canonicalHeaderFields,
        )
        assertContains(policy.canonicalHeaderFields, ProductionProviderHeaderCommitmentField.Salt)
        assertContains(policy.canonicalHeaderFields, ProductionProviderHeaderCommitmentField.VaultId)
        assertContains(
            policy.canonicalHeaderFields,
            ProductionProviderHeaderCommitmentField.PassphraseEncodingPolicyId,
        )
        assertContains(
            policy.canonicalHeaderFields,
            ProductionProviderHeaderCommitmentField.KeyCommitmentPolicyVersion,
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
    }

    @Test
    fun passphraseEncodingPolicyCapturesNfcUtf8AndRejectionRules() {
        val policy = contract.passphraseEncodingPolicy

        assertEquals("unicode-nfc-utf8-no-controls-no-whitespace-v1", policy.policyId)
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
        assertTrue(policy.nonKeyCommitting)
        assertFalse(policy.successfulDecryptAloneProvesCorrectVaultKey)
        assertTrue(policy.vaultLevelKeyCommitmentRequiredBeforeRecordDecrypt)
        assertTrue(policy.headerAuthenticationRequiredBeforeRecordDecrypt)
        assertEquals(ProductionProviderAadBindingField.entries.toSet(), policy.strictAadBindingFields)
        assertEquals(ProductionProviderTamperCoverage.entries.toSet(), policy.requiredTamperCoverage)
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
}
