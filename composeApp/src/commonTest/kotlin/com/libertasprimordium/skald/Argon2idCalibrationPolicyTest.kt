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
import com.libertasprimordium.skald.security.SkaldVaultV1Argon2idCalibrationLatencyClass
import com.libertasprimordium.skald.security.SkaldVaultV1Argon2idCalibrationObservation
import com.libertasprimordium.skald.security.SkaldVaultV1Argon2idCalibrationParameterStrength
import com.libertasprimordium.skald.security.SkaldVaultV1Argon2idCalibrationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1Argon2idCalibrationResult
import com.libertasprimordium.skald.security.SkaldVaultV1Argon2idParameters
import com.libertasprimordium.skald.security.SkaldVaultV1Argon2idType
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

        assertEquals(Argon2idCalibrationImplementationStatus.StillDisabledBuildingBlockImplemented, policy.status)
        assertEquals(EncryptedVaultKdfAlgorithm.Argon2id, policy.targetKdf)
        assertEquals(Argon2idVersion.Version19, policy.version)
        assertEquals(19, policy.version.numericVersion)
        assertFalse(policy.calibrationComplete)
        assertFalse(policy.productionKdfEnabled)
        assertTrue(policy.status.stillDisabledBuildingBlockImplemented)
        assertTrue(policy.status.candidateSelectionImplemented)
        assertTrue(policy.status.memoryFailureHandlingImplemented)
        assertEquals(
            Argon2idParameterPolicyStatus.SharedFloorPolicyImplementedStillDisabled,
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

        assertContains(candidateIds, "argon2id-v1-floor-64mib-t3-p1-root64")
        assertContains(candidateIds, "argon2id-v1-desktop-stronger-96mib-t3-p1-root64")
        assertTrue(policy.candidateParameters.all { it.version == Argon2idVersion.Version19 })
        assertTrue(policy.candidateParameters.all { it.outputLength.bytes == 64 })
        assertTrue(policy.candidateParameters.all { it.memoryCost.kib >= 64 * 1024 })
        assertTrue(policy.candidateParameters.all { it.passes.value >= 3 })
        assertTrue(policy.candidateParameters.all { it.lanes.value == 1 })
        assertTrue(policy.candidateParameters.all { !it.productionRecommendation })
        assertTrue(policy.candidateParameters.all { it.memoryCost.label.endsWith("MiB") })
        assertEquals(1, policy.androidRuntimeProbeCandidates.size)
        assertEquals(2, policy.desktopProbeCandidates.size)
    }

    @Test
    fun candidateParameterTiersAreExplicitAndNeverFinal() {
        val policy = commonArgon2idCalibrationPolicy().candidateParameterPolicy
        val desktop = policy.tier(Argon2idParameterTierKind.DesktopCandidate)
        val highEndAndroid = policy.tier(Argon2idParameterTierKind.HighEndAndroidCandidate)
        val floor = policy.tier(Argon2idParameterTierKind.MobileFallbackProbeFloor)
        val androidCompatibility = policy.tier(Argon2idParameterTierKind.AndroidSupportedCompatibilityPlanning)

        assertEquals("argon2id-v1-desktop-stronger-96mib-t3-p1-root64", desktop.candidateId)
        assertEquals("96 MiB", desktop.candidate?.memoryCost?.label)
        assertEquals(3, desktop.candidate?.passes?.value)
        assertEquals(1, desktop.candidate?.lanes?.value)
        assertEquals(64, desktop.candidate?.outputLength?.bytes)
        assertContains(desktop.evidence, Argon2idDeviceClassEvidenceStatus.DesktopJvmProbeMeasured)

        assertEquals("argon2id-v1-floor-64mib-t3-p1-root64", highEndAndroid.candidateId)
        assertEquals("64 MiB", highEndAndroid.candidate?.memoryCost?.label)
        assertContains(
            highEndAndroid.evidence,
            Argon2idDeviceClassEvidenceStatus.Pixel10ProXlAndroid16ProbeMeasured,
        )
        assertFalse(highEndAndroid.universalAndroidPolicy)

        assertEquals("argon2id-v1-floor-64mib-t3-p1-root64", floor.candidateId)
        assertEquals(policy.minimumProbeFloorCandidateId, floor.candidateId)
        assertEquals("64 MiB", floor.candidate?.memoryCost?.label)
        assertEquals(3, floor.candidate?.passes?.value)
        assertEquals(1, floor.candidate?.lanes?.value)
        assertEquals(64, floor.candidate?.outputLength?.bytes)
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
        assertContains(policy.finalApprovalBlockers, Argon2idParameterApprovalBlocker.FinalProductionCalibrationApprovalMissing)
        assertFalse(policy.finalApprovalBlockers.contains(Argon2idParameterApprovalBlocker.ProductionKdfImplementationMissing))
        assertFalse(policy.finalApprovalBlockers.contains(Argon2idParameterApprovalBlocker.MemoryPressureFailureBehaviorMissing))
        assertContains(policy.finalApprovalBlockers, Argon2idParameterApprovalBlocker.SecureStorageStillDisabled)
        assertFalse(policy.finalProductionParametersApproved)
    }

    @Test
    fun sharedFloorAndroidProbeWarningsRemainExplicitWithoutBelowFloorApproval() {
        val policy = commonArgon2idCalibrationPolicy()
        val androidFloor = policy.candidateParameters.single { it.id == "argon2id-v1-floor-64mib-t3-p1-root64" }
        val warnings = policy.warningsFor(androidFloor)

        assertContains(androidFloor.platformClasses, Argon2idCalibrationPlatformClass.AndroidRuntime)
        assertContains(warnings, Argon2idCalibrationWarning.ProbeOnlyNotProductionSetting)
        assertContains(warnings, Argon2idCalibrationWarning.CandidateParameterPolicyNotFinal)
        assertFalse(warnings.contains(Argon2idCalibrationWarning.LowMemoryProbeCandidate))
        assertFalse(warnings.contains(Argon2idCalibrationWarning.TooFastSettingWouldBeWeak))
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

    @Test
    fun v1CalibrationEvidenceCapturesFloorLatencyAndNoDowngradePolicy() {
        val evidence = SkaldVaultV1Argon2idCalibrationPolicy.evidence

        assertEquals(Argon2idCalibrationImplementationStatus.StillDisabledBuildingBlockImplemented, evidence.implementationStatus)
        assertEquals(64, evidence.minimumMemoryMiB)
        assertEquals(3, evidence.minimumIterations)
        assertEquals(1, evidence.requiredParallelism)
        assertEquals(16, evidence.minimumSaltBytes)
        assertEquals(32, evidence.preferredNewVaultSaltBytes)
        assertEquals(64, evidence.outputRootMaterialBytes)
        assertEquals(1_000, evidence.preferredUnlockMillis)
        assertEquals(2_000, evidence.acceptableUnlockMillis)
        assertFalse(evidence.twoSecondsIsFailureCondition)
        assertFalse(evidence.weakenToForceSubOneSecondAllowed)
        assertTrue(evidence.existingStoredParametersAuthoritative)
        assertFalse(evidence.silentDowngradeAllowed)
        assertFalse(evidence.productionKdfEnabled)
    }

    @Test
    fun v1CalibrationValidationAcceptsOnlyAtOrAboveTheSharedFloor() {
        val floor = acceptedCalibration(
            SkaldVaultV1Argon2idCalibrationPolicy.validateCreationParameters(
                parameters = floorParameters(),
                saltLengthBytes = 16,
            ),
        )
        val stronger = acceptedCalibration(
            SkaldVaultV1Argon2idCalibrationPolicy.validateCreationParameters(
                parameters = strongerParameters(),
                saltLengthBytes = 32,
            ),
        )

        assertEquals(SkaldVaultV1Argon2idCalibrationParameterStrength.Floor, floor.strength)
        assertEquals(SkaldVaultV1Argon2idCalibrationParameterStrength.StrongerThanFloor, stronger.strength)
        assertFalse(floor.downgradeAllowed)
        assertFalse(stronger.downgradeAllowed)

        assertRejectedCalibration(
            SkaldVaultV1Argon2idCalibrationPolicy.validateCreationParameters(
                parameters = floorParameters(memoryKiB = 64 * 1024 - 1),
                saltLengthBytes = 16,
            ),
            Argon2idCalibrationRejectionReason.MemoryBelowV1Floor,
        )
        assertRejectedCalibration(
            SkaldVaultV1Argon2idCalibrationPolicy.validateCreationParameters(
                parameters = floorParameters(iterations = 2),
                saltLengthBytes = 16,
            ),
            Argon2idCalibrationRejectionReason.IterationsBelowV1Floor,
        )
        assertRejectedCalibration(
            SkaldVaultV1Argon2idCalibrationPolicy.validateCreationParameters(
                parameters = floorParameters(parallelism = 0),
                saltLengthBytes = 16,
            ),
            Argon2idCalibrationRejectionReason.UnsupportedParallelism,
        )
        assertRejectedCalibration(
            SkaldVaultV1Argon2idCalibrationPolicy.validateCreationParameters(
                parameters = floorParameters(parallelism = 2),
                saltLengthBytes = 16,
            ),
            Argon2idCalibrationRejectionReason.UnsupportedParallelism,
        )
        assertRejectedCalibration(
            SkaldVaultV1Argon2idCalibrationPolicy.validateCreationParameters(
                parameters = floorParameters(),
                saltLengthBytes = 15,
            ),
            Argon2idCalibrationRejectionReason.SaltTooShort,
        )
        assertRejectedCalibration(
            SkaldVaultV1Argon2idCalibrationPolicy.validateCreationParameters(
                parameters = floorParameters(outputBytes = 32),
                saltLengthBytes = 16,
            ),
            Argon2idCalibrationRejectionReason.UnsupportedOutputLength,
        )
        assertRejectedCalibration(
            SkaldVaultV1Argon2idCalibrationPolicy.validateCreationParameters(
                parameters = floorParameters(type = SkaldVaultV1Argon2idType.Argon2i),
                saltLengthBytes = 16,
            ),
            Argon2idCalibrationRejectionReason.UnsupportedArgon2Type,
        )
        assertRejectedCalibration(
            SkaldVaultV1Argon2idCalibrationPolicy.validateCreationParameters(
                parameters = floorParameters(version = 16),
                saltLengthBytes = 16,
            ),
            Argon2idCalibrationRejectionReason.UnsupportedArgon2Version,
        )
    }

    @Test
    fun candidateSelectionAllowsOneToTwoSecondsAndDoesNotWeakenBelowFloor() {
        val selected = acceptedCalibration(
            SkaldVaultV1Argon2idCalibrationPolicy.selectCandidate(
                listOf(
                    observation("below-floor-fast", floorParameters(memoryKiB = 32 * 1024), 500),
                    observation("floor-preferred", floorParameters(), 900),
                    observation("desktop-stronger-allowed", strongerParameters(), 1_500),
                ),
            ),
        )
        val twoSecondFloor = acceptedCalibration(
            SkaldVaultV1Argon2idCalibrationPolicy.assessCreationExecution(
                observation("floor-two-second", floorParameters(), 2_000),
            ),
        )

        assertEquals("desktop-stronger-allowed", selected.candidateId)
        assertEquals(SkaldVaultV1Argon2idCalibrationParameterStrength.StrongerThanFloor, selected.strength)
        assertEquals(SkaldVaultV1Argon2idCalibrationLatencyClass.AcceptableAtOrBelowTwoSeconds, selected.latencyClass)
        assertFalse(selected.selectedBelowFloor)
        assertFalse(selected.selectedToForceSubOneSecond)
        assertFalse(selected.productionKdfEnabled)

        assertEquals(SkaldVaultV1Argon2idCalibrationLatencyClass.AcceptableAtOrBelowTwoSeconds, twoSecondFloor.latencyClass)
        assertFalse(twoSecondFloor.selectedToForceSubOneSecond)
    }

    @Test
    fun floorExecutionFailureFailsClosed() {
        val failedFloor = SkaldVaultV1Argon2idCalibrationPolicy.assessCreationExecution(
            observation("floor-failed", floorParameters(), elapsedMillis = 1_000, executionSucceeded = false),
        )
        val noCandidate = SkaldVaultV1Argon2idCalibrationPolicy.selectCandidate(
            listOf(
                observation("below-floor-fast", floorParameters(memoryKiB = 32 * 1024), 500),
            ),
        )

        assertRejectedCalibration(failedFloor, Argon2idCalibrationRejectionReason.CalibrationFailedAtFloor)
        assertRejectedCalibration(noCandidate, Argon2idCalibrationRejectionReason.NoSuccessfulCandidateAtOrAboveFloor)
    }

    @Test
    fun storedVaultParametersAreAuthoritativeAndNeverSilentlyDowngraded() {
        val accepted = acceptedCalibration(
            SkaldVaultV1Argon2idCalibrationPolicy.assessStoredVaultExecution(
                parameters = strongerParameters(memoryKiB = 128 * 1024),
                saltLengthBytes = 32,
                executionSucceeded = true,
            ),
        )

        assertTrue(accepted.authoritative)
        assertTrue(accepted.unlockAllowed)
        assertFalse(accepted.downgradeAttempted)
        assertRejectedCalibration(
            SkaldVaultV1Argon2idCalibrationPolicy.assessStoredVaultExecution(
                parameters = strongerParameters(memoryKiB = 128 * 1024),
                saltLengthBytes = 32,
                executionSucceeded = false,
            ),
            Argon2idCalibrationRejectionReason.StoredParametersUnsupportedOnDevice,
        )
        assertRejectedCalibration(
            SkaldVaultV1Argon2idCalibrationPolicy.assessStoredVaultExecution(
                parameters = floorParameters(memoryKiB = 32 * 1024),
                saltLengthBytes = 32,
                executionSucceeded = true,
            ),
            Argon2idCalibrationRejectionReason.MemoryBelowV1Floor,
        )
        assertRejectedCalibration(
            SkaldVaultV1Argon2idCalibrationPolicy.rejectDowngradeAttempt(),
            Argon2idCalibrationRejectionReason.DowngradeNotAllowed,
        )
    }

    private fun floorParameters(
        type: SkaldVaultV1Argon2idType = SkaldVaultV1Argon2idType.Argon2id,
        version: Int = 19,
        memoryKiB: Int = 64 * 1024,
        iterations: Int = 3,
        parallelism: Int = 1,
        outputBytes: Int = 64,
    ): SkaldVaultV1Argon2idParameters =
        SkaldVaultV1Argon2idParameters(
            type = type,
            version = version,
            memoryKiB = memoryKiB,
            iterations = iterations,
            parallelism = parallelism,
            outputBytes = outputBytes,
        )

    private fun strongerParameters(
        memoryKiB: Int = 96 * 1024,
        iterations: Int = 3,
    ): SkaldVaultV1Argon2idParameters =
        floorParameters(memoryKiB = memoryKiB, iterations = iterations)

    private fun observation(
        id: String,
        parameters: SkaldVaultV1Argon2idParameters,
        elapsedMillis: Long,
        executionSucceeded: Boolean = true,
        saltLengthBytes: Int = 32,
    ): SkaldVaultV1Argon2idCalibrationObservation =
        SkaldVaultV1Argon2idCalibrationObservation(
            candidateId = id,
            parameters = parameters,
            saltLengthBytes = saltLengthBytes,
            elapsedMillis = elapsedMillis,
            executionSucceeded = executionSucceeded,
        )

    private fun <T> acceptedCalibration(result: SkaldVaultV1Argon2idCalibrationResult<T>): T =
        when (result) {
            is SkaldVaultV1Argon2idCalibrationResult.Accepted -> result.value
            is SkaldVaultV1Argon2idCalibrationResult.Rejected -> error(result.safeMessage)
        }

    private fun assertRejectedCalibration(
        result: SkaldVaultV1Argon2idCalibrationResult<*>,
        reason: Argon2idCalibrationRejectionReason,
    ) {
        val rejected = when (result) {
            is SkaldVaultV1Argon2idCalibrationResult.Accepted -> error("Expected calibration rejection.")
            is SkaldVaultV1Argon2idCalibrationResult.Rejected -> result
        }

        assertEquals(reason, rejected.reason)
        assertFalse(rejected.safeMessage.contains("Skald-Vault.Test_Fixture-01"))
    }
}
