package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.Argon2idCalibrationImplementationStatus
import com.libertasprimordium.skald.security.Argon2idCalibrationPlatformClass
import com.libertasprimordium.skald.security.Argon2idCalibrationPolicyResult
import com.libertasprimordium.skald.security.Argon2idCalibrationRejectionReason
import com.libertasprimordium.skald.security.Argon2idCalibrationWarning
import com.libertasprimordium.skald.security.Argon2idDeviceClassEvidenceStatus
import com.libertasprimordium.skald.security.Argon2idParameterApprovalBlocker
import com.libertasprimordium.skald.security.Argon2idParameterPolicyStatus
import com.libertasprimordium.skald.security.Argon2idParameterTierKind
import com.libertasprimordium.skald.security.Argon2idMemoryCost
import com.libertasprimordium.skald.security.Argon2idMemoryUnit
import com.libertasprimordium.skald.security.Argon2idOutputLength
import com.libertasprimordium.skald.security.Argon2idVersion
import com.libertasprimordium.skald.security.EncryptedVaultKdfAlgorithm
import com.libertasprimordium.skald.security.VaultCryptoProviderBlocker
import com.libertasprimordium.skald.security.commonArgon2idCalibrationPolicy
import com.libertasprimordium.skald.security.commonDisabledVaultCryptoProviderStatus
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class Argon2idCalibrationPolicyTest {
    @Test
    fun calibrationPolicyIsProbeOnlyAndProductionKdfRemainsDisabled() {
        val policy = commonArgon2idCalibrationPolicy()

        assertEquals(Argon2idCalibrationImplementationStatus.PolicyPresentProbeOnly, policy.status)
        assertEquals(EncryptedVaultKdfAlgorithm.Argon2id, policy.targetKdf)
        assertEquals(Argon2idVersion.Version19, policy.version)
        assertEquals(19, policy.version.numericVersion)
        assertFalse(policy.calibrationComplete)
        assertFalse(policy.productionKdfEnabled)
        assertEquals(
            Argon2idParameterPolicyStatus.CandidatePolicyPresentNotFinal,
            policy.candidateParameterPolicy.status,
        )
        assertFalse(policy.candidateParameterPolicy.finalProductionParametersApproved)
        assertFalse(policy.candidateParameterPolicy.productionKdfEnabled)
        assertFalse(policy.acceptsProductionKdf(EncryptedVaultKdfAlgorithm.Argon2id))
        assertIs<Argon2idCalibrationPolicyResult.Rejected>(
            policy.evaluateAlgorithm(EncryptedVaultKdfAlgorithm.Argon2id),
        ).also { rejected ->
            assertEquals(Argon2idCalibrationRejectionReason.ProductionKdfDisabled, rejected.reason)
        }
    }

    @Test
    fun memoryCostRequiresPositiveExplicitUnits() {
        val zero = assertIs<Argon2idCalibrationPolicyResult.Rejected>(
            Argon2idMemoryCost.fromExplicit(0, Argon2idMemoryUnit.MiB),
        )
        val ambiguousShortUnit = assertIs<Argon2idCalibrationPolicyResult.Rejected>(
            Argon2idMemoryCost.fromExplicitLabel("32M"),
        )
        val ambiguousBareUnit = assertIs<Argon2idCalibrationPolicyResult.Rejected>(
            Argon2idMemoryCost.fromExplicitLabel("64 K"),
        )
        val accepted = assertIs<Argon2idCalibrationPolicyResult.Accepted<Argon2idMemoryCost>>(
            Argon2idMemoryCost.fromExplicitLabel("32 MiB"),
        )

        assertEquals(Argon2idCalibrationRejectionReason.InvalidMemoryCost, zero.reason)
        assertEquals(Argon2idCalibrationRejectionReason.AmbiguousMemoryUnit, ambiguousShortUnit.reason)
        assertEquals(Argon2idCalibrationRejectionReason.AmbiguousMemoryUnit, ambiguousBareUnit.reason)
        assertEquals(32 * 1024, accepted.value.kib)
        assertEquals("32 MiB", accepted.value.label)
    }

    @Test
    fun outputLengthRejectsTooShortVaultKdfOutput() {
        val rejected = assertIs<Argon2idCalibrationPolicyResult.Rejected>(
            Argon2idOutputLength.ofBytes(16),
        )
        val accepted = assertIs<Argon2idCalibrationPolicyResult.Accepted<Argon2idOutputLength>>(
            Argon2idOutputLength.ofBytes(32),
        )

        assertEquals(Argon2idCalibrationRejectionReason.TooShortOutputLength, rejected.reason)
        assertEquals(32, accepted.value.bytes)
    }

    @Test
    fun pbkdf2IsRejectedAndScryptFallbackIsNotSelected() {
        val policy = commonArgon2idCalibrationPolicy()

        assertContains(policy.rejectedDefaultKdfs, EncryptedVaultKdfAlgorithm.Pbkdf2)
        assertEquals(EncryptedVaultKdfAlgorithm.Scrypt, policy.fallbackKdf)
        assertFalse(policy.fallbackSelected)
        assertFalse(policy.fallbackIsSelected(EncryptedVaultKdfAlgorithm.Scrypt))
        assertFalse(policy.acceptsProductionKdf(EncryptedVaultKdfAlgorithm.Pbkdf2))
    }

    @Test
    fun defaultProbeCandidatesUseExplicitUnitsAndAreNotProductionRecommendations() {
        val policy = commonArgon2idCalibrationPolicy()
        val candidateIds = policy.candidateParameters.map { it.id }.toSet()

        assertContains(candidateIds, "argon2id-probe-16mib-2p-1lane")
        assertContains(candidateIds, "argon2id-probe-32mib-3p-1lane")
        assertContains(candidateIds, "argon2id-probe-64mib-3p-1lane")
        assertTrue(policy.candidateParameters.all { it.version == Argon2idVersion.Version19 })
        assertTrue(policy.candidateParameters.all { it.outputLength.bytes == 32 })
        assertTrue(policy.candidateParameters.all { !it.productionRecommendation })
        assertTrue(policy.candidateParameters.all { it.memoryCost.label.endsWith("MiB") })
        assertTrue(policy.androidRuntimeProbeCandidates.size == 2)
        assertTrue(policy.desktopProbeCandidates.size == 3)
    }

    @Test
    fun candidateParameterTiersAreExplicitAndNeverFinal() {
        val policy = commonArgon2idCalibrationPolicy().candidateParameterPolicy
        val desktop = policy.tier(Argon2idParameterTierKind.DesktopCandidate)
        val highEndAndroid = policy.tier(Argon2idParameterTierKind.HighEndAndroidCandidate)
        val floor = policy.tier(Argon2idParameterTierKind.MobileFallbackProbeFloor)
        val androidCompatibility = policy.tier(Argon2idParameterTierKind.AndroidSupportedCompatibilityPlanning)

        assertEquals("argon2id-probe-64mib-3p-1lane", desktop.candidateId)
        assertEquals("64 MiB", desktop.candidate?.memoryCost?.label)
        assertEquals(3, desktop.candidate?.passes?.value)
        assertEquals(1, desktop.candidate?.lanes?.value)
        assertContains(desktop.evidence, Argon2idDeviceClassEvidenceStatus.DesktopJvmProbeMeasured)

        assertEquals("argon2id-probe-32mib-3p-1lane", highEndAndroid.candidateId)
        assertEquals("32 MiB", highEndAndroid.candidate?.memoryCost?.label)
        assertContains(
            highEndAndroid.evidence,
            Argon2idDeviceClassEvidenceStatus.Pixel10ProXlAndroid16ProbeMeasured,
        )
        assertFalse(highEndAndroid.universalAndroidPolicy)

        assertEquals("argon2id-probe-16mib-2p-1lane", floor.candidateId)
        assertEquals(policy.minimumProbeFloorCandidateId, floor.candidateId)
        assertEquals("16 MiB", floor.candidate?.memoryCost?.label)
        assertFalse(floor.finalProductionApproved)

        assertEquals("unresolved", androidCompatibility.candidateId)
        assertContains(
            androidCompatibility.evidence,
            Argon2idDeviceClassEvidenceStatus.AndroidSupportedCompatibilityPolicyModeled,
        )
        assertFalse(
            Argon2idDeviceClassEvidenceStatus.LowEndAndroidCoverageMissing in androidCompatibility.evidence,
        )
        assertFalse(
            Argon2idDeviceClassEvidenceStatus.MidRangeAndroidCoverageMissing in androidCompatibility.evidence,
        )
        assertTrue(policy.androidBaselineCoverageSatisfied)
        assertTrue(policy.tiers.all { !it.finalProductionApproved })
    }

    @Test
    fun finalApprovalBlockersKeepCandidatePolicyNonFinal() {
        val policy = commonArgon2idCalibrationPolicy().candidateParameterPolicy

        assertFalse(Argon2idParameterApprovalBlocker.LowEndAndroidProbeMissing in policy.finalApprovalBlockers)
        assertFalse(Argon2idParameterApprovalBlocker.MidRangeAndroidProbeMissing in policy.finalApprovalBlockers)
        assertFalse(Argon2idParameterApprovalBlocker.ThermalLoadRepeatabilityMissing in policy.finalApprovalBlockers)
        assertContains(policy.finalApprovalBlockers, Argon2idParameterApprovalBlocker.AndroidSupportedCompatibilityReviewMissing)
        assertContains(policy.finalApprovalBlockers, Argon2idParameterApprovalBlocker.RuntimeCryptoProviderCheckMissing)
        assertContains(policy.finalApprovalBlockers, Argon2idParameterApprovalBlocker.RuntimeEntropyCheckMissing)
        assertContains(policy.finalApprovalBlockers, Argon2idParameterApprovalBlocker.ApprovedRandomnessSourceMissing)
        assertContains(policy.finalApprovalBlockers, Argon2idParameterApprovalBlocker.UnlockUxMeasurementMissing)
        assertContains(policy.finalApprovalBlockers, Argon2idParameterApprovalBlocker.ProviderBoundaryKnownAnswerVectorsMissing)
        assertContains(policy.finalApprovalBlockers, Argon2idParameterApprovalBlocker.ProductionKdfImplementationMissing)
        assertContains(policy.finalApprovalBlockers, Argon2idParameterApprovalBlocker.SecureStorageStillDisabled)
        assertFalse(policy.finalProductionParametersApproved)
    }

    @Test
    fun lowMemoryAndAndroidProbeWarningsRemainExplicit() {
        val policy = commonArgon2idCalibrationPolicy()
        val lowMemoryAndroid = policy.candidateParameters.single { it.id == "argon2id-probe-16mib-2p-1lane" }
        val warnings = policy.warningsFor(lowMemoryAndroid)

        assertContains(lowMemoryAndroid.platformClasses, Argon2idCalibrationPlatformClass.AndroidRuntime)
        assertContains(warnings, Argon2idCalibrationWarning.ProbeOnlyNotProductionSetting)
        assertContains(warnings, Argon2idCalibrationWarning.CandidateParameterPolicyNotFinal)
        assertContains(warnings, Argon2idCalibrationWarning.LowMemoryProbeCandidate)
        assertContains(warnings, Argon2idCalibrationWarning.TooFastSettingWouldBeWeak)
        assertContains(warnings, Argon2idCalibrationWarning.PixelEvidenceHighEndOnly)
        assertContains(warnings, Argon2idCalibrationWarning.AndroidCompatibilityRuntimeChecksRequired)
        assertContains(warnings, Argon2idCalibrationWarning.ThermalLoadRepeatabilityDesirable)
        assertContains(warnings, Argon2idCalibrationWarning.AndroidDeviceVariance)
        assertContains(warnings, Argon2idCalibrationWarning.TimingIsNotBenchmark)
        assertContains(warnings, Argon2idCalibrationWarning.MemoryZeroizationUnresolved)
    }

    @Test
    fun disabledProviderStillReportsKdfParametersUncalibrated() {
        val providerStatus = commonDisabledVaultCryptoProviderStatus()

        assertFalse(providerStatus.implementationStatus.canExecuteCrypto)
        assertFalse(providerStatus.implementationStatus.productionApproved)
        assertContains(providerStatus.blockers, VaultCryptoProviderBlocker.KdfParametersUncalibrated)
        assertContains(providerStatus.blockers, VaultCryptoProviderBlocker.ProviderDisabledByPolicy)
        assertContains(providerStatus.blockers, VaultCryptoProviderBlocker.ProductionPersistenceDisabled)
        assertContains(providerStatus.blockers, VaultCryptoProviderBlocker.MainnetDisabled)
    }
}
