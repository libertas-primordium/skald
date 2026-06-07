package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.AndroidArgon2idCalibrationEvidencePolicy
import com.libertasprimordium.skald.security.AndroidVaultCompatibilityAssessment
import com.libertasprimordium.skald.security.AndroidVaultCompatibilityAssessmentRequest
import com.libertasprimordium.skald.security.AndroidVaultCompatibilityEvidence
import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.EncryptedVaultCapability
import com.libertasprimordium.skald.security.EncryptedVaultPlatform
import com.libertasprimordium.skald.security.EncryptedVaultReadinessPolicy
import com.libertasprimordium.skald.security.PlatformKeyProtectionAvailability
import com.libertasprimordium.skald.security.VaultCreationFailClosedReason
import com.libertasprimordium.skald.security.VaultCryptoDependencyCandidate
import com.libertasprimordium.skald.security.VaultCryptoDependencyCapability
import com.libertasprimordium.skald.security.VaultCryptoDependencyProbeCatalog
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRequest
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionUse
import com.libertasprimordium.skald.security.VaultRandomnessSourceKind
import com.libertasprimordium.skald.security.VaultRandomnessUse
import com.libertasprimordium.skald.security.commonAndroidVaultCompatibilityPolicy
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class AndroidVaultCompatibilityEntropyPolicyTest {
    @Test
    fun lowEndAndMidRangeModelTestingAreNotRequiredForCompatibilityPlanning() {
        val policy = commonAndroidVaultCompatibilityPolicy()
        val assessment = planningAssessment()

        assertFalse(policy.minimumCompatibility.veryOldAndroidCompatibilityGoal)
        assertFalse(policy.minimumCompatibility.lowEndModelTestingRequired)
        assertFalse(policy.minimumCompatibility.midRangeModelTestingRequired)
        assertFalse(assessment.lowEndModelTestingRequired)
        assertFalse(assessment.midRangeModelTestingRequired)
        assertTrue(assessment.compatibilityPlanningSatisfied)
        assertFalse(assessment.exhaustiveDevicePerformanceProven)
    }

    @Test
    fun pixelEvidenceRemainsHighEndOnlyAndDoesNotProveAllDevicePerformance() {
        val assessment = AndroidArgon2idCalibrationEvidencePolicy.assess()

        assertTrue(assessment.highEndEvidencePresent)
        assertFalse(assessment.lowEndEvidencePresent)
        assertFalse(assessment.midRangeEvidencePresent)
        assertTrue(assessment.compatibilityPlanningSatisfied)
        assertFalse(assessment.exhaustiveDevicePerformanceProven)
        assertFalse(assessment.finalProductionParametersApproved)
    }

    @Test
    fun supportedBaselineAndRuntimeChecksSatisfyPlanningButNotProviderApproval() {
        val assessment = planningAssessment(
            hardwareBackedKeyProtection = PlatformKeyProtectionAvailability.Unavailable,
            strongBox = PlatformKeyProtectionAvailability.Unavailable,
        )

        assertTrue(assessment.compatibilityPlanningSatisfied)
        assertFalse(assessment.vaultCreationAllowed)
        assertFalse(assessment.productionProviderApproved)
        assertFalse(assessment.productionKdfApproved)
        assertContains(assessment.failClosedReasons, VaultCreationFailClosedReason.ProductionProviderMissing)
        assertContains(assessment.failClosedReasons, VaultCreationFailClosedReason.ProviderSelectionDisabled)
        assertContains(assessment.failClosedReasons, VaultCreationFailClosedReason.SecureSecretStorageDisabled)
        assertContains(assessment.failClosedReasons, VaultCreationFailClosedReason.SecureMetadataStorageDisabled)
        assertTrue(assessment.requiresUserFacingWarning)
    }

    @Test
    fun unsupportedOutdatedAndroidBlocksVaultCreation() {
        val assessment = planningAssessment(apiLevel = commonAndroidVaultCompatibilityPolicy().minimumCompatibility.minimumApiLevel - 1)

        assertFalse(assessment.compatibilityPlanningSatisfied)
        assertFalse(assessment.vaultCreationAllowed)
        assertContains(assessment.failClosedReasons, VaultCreationFailClosedReason.UnsupportedOutdatedAndroid)
        assertTrue(assessment.userFacingWarnings.any { it.reason == VaultCreationFailClosedReason.UnsupportedOutdatedAndroid })
    }

    @Test
    fun osCryptographicRandomnessFallbackIsAllowedWhenHardwareProtectionUnavailable() {
        val assessment = planningAssessment(
            randomnessSource = VaultRandomnessSourceKind.OsCryptographicRandomness,
            hardwareBackedKeyProtection = PlatformKeyProtectionAvailability.Unavailable,
            strongBox = PlatformKeyProtectionAvailability.Unavailable,
        )
        val entropyPolicy = commonAndroidVaultCompatibilityPolicy().entropySourcePolicy

        assertTrue(entropyPolicy.osCryptographicRandomnessAllowed)
        assertTrue(entropyPolicy.softwareOsCsprngFallbackAllowed)
        assertTrue(assessment.randomnessAcceptedForVaultMaterial)
        assertTrue(assessment.compatibilityPlanningSatisfied)
        assertFalse(assessment.hardwareBackedKeyProtectionRequired)
        assertFalse(assessment.strongBoxRequired)
        assertFalse(VaultCreationFailClosedReason.RuntimeRandomnessCheckMissing in assessment.failClosedReasons)
    }

    @Test
    fun hardwareBackedKeyProtectionIsPreferredButOptionalAndSeparateFromRandomness() {
        val entropyPolicy = commonAndroidVaultCompatibilityPolicy().entropySourcePolicy
        val mistakenRandomness = planningAssessment(
            randomnessSource = VaultRandomnessSourceKind.HardwareBackedKeyProtectionOnly,
            hardwareBackedKeyProtection = PlatformKeyProtectionAvailability.HardwareBackedAvailable,
        )

        assertTrue(entropyPolicy.hardwareBackedKeyProtectionPreferred)
        assertFalse(entropyPolicy.hardwareBackedKeyProtectionRequired)
        assertTrue(entropyPolicy.strongBoxOptionalPreferredIfAvailable)
        assertFalse(mistakenRandomness.compatibilityPlanningSatisfied)
        assertContains(
            mistakenRandomness.failClosedReasons,
            VaultCreationFailClosedReason.HardwareKeyProtectionMistakenForRandomness,
        )
    }

    @Test
    fun unknownProviderOrEntropyStateBlocksVaultCreationWithWarning() {
        val assessment = commonAndroidVaultCompatibilityPolicy().assess(
            AndroidVaultCompatibilityAssessmentRequest(
                evidence = com.libertasprimordium.skald.security.commonAndroidVaultCompatibilityUnknownAssessment().evidence,
            ),
        )

        assertFalse(assessment.compatibilityPlanningSatisfied)
        assertFalse(assessment.vaultCreationAllowed)
        assertContains(assessment.failClosedReasons, VaultCreationFailClosedReason.UnknownProviderState)
        assertContains(assessment.failClosedReasons, VaultCreationFailClosedReason.UnknownEntropyState)
        assertTrue(assessment.userFacingWarnings.any { it.reason == VaultCreationFailClosedReason.UnknownProviderState })
        assertTrue(assessment.userFacingWarnings.any { it.reason == VaultCreationFailClosedReason.UnknownEntropyState })
    }

    @Test
    fun languageAndAdHocRandomnessSourcesAreForbiddenForVaultMaterial() {
        val entropyPolicy = commonAndroidVaultCompatibilityPolicy().entropySourcePolicy
        val forbiddenSources = setOf(
            VaultRandomnessSourceKind.KotlinRandom,
            VaultRandomnessSourceKind.JavaUtilRandom,
            VaultRandomnessSourceKind.MathRandom,
            VaultRandomnessSourceKind.TimestampDerived,
            VaultRandomnessSourceKind.UuidDerived,
            VaultRandomnessSourceKind.AdHocPrng,
        )

        forbiddenSources.forEach { source ->
            val assessment = planningAssessment(randomnessSource = source)
            assertContains(entropyPolicy.forbiddenRandomnessSources, source)
            assertFalse(entropyPolicy.accepts(source))
            assertFalse(assessment.randomnessAcceptedForVaultMaterial)
            assertContains(assessment.failClosedReasons, VaultCreationFailClosedReason.ForbiddenRandomnessSource)
        }
        assertContains(entropyPolicy.protectedUses, VaultRandomnessUse.VaultRootKey)
        assertContains(entropyPolicy.protectedUses, VaultRandomnessUse.Salt)
        assertContains(entropyPolicy.protectedUses, VaultRandomnessUse.Nonce)
        assertContains(entropyPolicy.protectedUses, VaultRandomnessUse.UnlockMaterial)
    }

    @Test
    fun providerSelectionAndReadinessRemainDisabledOnly() {
        val compatibility = planningAssessment()
        val selection = VaultCryptoProviderSelectionRegistry.select(
            request = VaultCryptoProviderSelectionRequest(
                requestedCandidate = VaultCryptoProviderCandidateId.TinkBouncyCastleSplit,
                platform = EncryptedVaultPlatform.Android,
                use = VaultCryptoProviderSelectionUse.ProductionPersistence,
                requireUniversalAndroidParameterPolicy = true,
            ),
            androidCompatibilityAssessment = compatibility,
        )
        val readiness = EncryptedVaultReadinessPolicy.disabled()
        val selected = VaultCryptoDependencyProbeCatalog.currentSpikeResults()
            .single { it.candidate == VaultCryptoDependencyCandidate.TinkBouncyCastleSplit }

        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertTrue(selection.selectedProvider is DisabledVaultCryptoProvider)
        assertTrue(selection.selectedProviderIsDisabled)
        assertFalse(selection.productionProviderSelectable)
        assertTrue(selection.requestedCandidate.evidence.platformCoverage.androidCompatibilityPlanningSatisfied)
        assertFalse(selection.requestedCandidate.evidence.platformCoverage.lowEndModelTestingRequired)
        assertFalse(selection.requestedCandidate.evidence.platformCoverage.midRangeModelTestingRequired)
        assertFalse(readiness.readyForProductionPersistence)
        assertContains(readiness.capabilities, EncryptedVaultCapability.AndroidCompatibilityEntropyPolicyModel)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.AndroidCompatibilityEntropyPolicyModeled)
        assertFalse(selected.implementationEnabled)
        assertFalse(selected.storageEnabled)
        assertFalse(selected.productionPersistenceEnabled)
    }

    private fun planningAssessment(
        apiLevel: Int = commonAndroidVaultCompatibilityPolicy().minimumCompatibility.minimumApiLevel,
        randomnessSource: VaultRandomnessSourceKind = VaultRandomnessSourceKind.OsCryptographicRandomness,
        hardwareBackedKeyProtection: PlatformKeyProtectionAvailability =
            PlatformKeyProtectionAvailability.SoftwareOrOsOnly,
        strongBox: PlatformKeyProtectionAvailability = PlatformKeyProtectionAvailability.Unavailable,
    ): AndroidVaultCompatibilityAssessment =
        commonAndroidVaultCompatibilityPolicy().assess(
            AndroidVaultCompatibilityAssessmentRequest(
                evidence = AndroidVaultCompatibilityEvidence.supportedPlanningEvidence(
                    apiLevel = apiLevel,
                    randomnessSource = randomnessSource,
                    hardwareBackedKeyProtection = hardwareBackedKeyProtection,
                    strongBox = strongBox,
                ),
            ),
        )
}
