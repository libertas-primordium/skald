package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.AndroidArgon2idBaselineAcceptanceGate
import com.libertasprimordium.skald.security.AndroidArgon2idBaselineBlocker
import com.libertasprimordium.skald.security.AndroidArgon2idBuildProfile
import com.libertasprimordium.skald.security.AndroidArgon2idCalibrationEvidencePolicy
import com.libertasprimordium.skald.security.AndroidArgon2idCalibrationEvidenceRejectionReason
import com.libertasprimordium.skald.security.AndroidArgon2idCalibrationEvidenceResult
import com.libertasprimordium.skald.security.AndroidArgon2idCalibrationRunResult
import com.libertasprimordium.skald.security.AndroidArgon2idDeviceClass
import com.libertasprimordium.skald.security.AndroidArgon2idRuntimeEnvironment
import com.libertasprimordium.skald.security.Argon2idFutureCalibrationRequirement
import com.libertasprimordium.skald.security.Argon2idVersion
import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.EncryptedVaultCapability
import com.libertasprimordium.skald.security.EncryptedVaultReadinessPolicy
import com.libertasprimordium.skald.security.VaultCryptoDependencyCandidate
import com.libertasprimordium.skald.security.VaultCryptoDependencyCapability
import com.libertasprimordium.skald.security.VaultCryptoDependencyProbeCatalog
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import com.libertasprimordium.skald.security.commonArgon2idCalibrationPolicy
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class AndroidArgon2idCalibrationEvidenceTest {
    @Test
    fun pixelEvidenceClassifiesOnlyAsHighEndEvidence() {
        val evidence = AndroidArgon2idCalibrationEvidencePolicy.currentPixel10ProXlAndroid16HighEndEvidence()
        val assessment = AndroidArgon2idCalibrationEvidencePolicy.assess(listOf(evidence))

        assertContains(AndroidArgon2idDeviceClass.entries, AndroidArgon2idDeviceClass.LowEnd)
        assertContains(AndroidArgon2idDeviceClass.entries, AndroidArgon2idDeviceClass.MidRange)
        assertContains(AndroidArgon2idDeviceClass.entries, AndroidArgon2idDeviceClass.HighEnd)
        assertContains(AndroidArgon2idDeviceClass.entries, AndroidArgon2idDeviceClass.Unknown)
        assertEquals(AndroidArgon2idDeviceClass.HighEnd, evidence.deviceClass)
        assertEquals("Android 16", evidence.runtimeEnvironment.androidVersion)
        assertEquals(36, evidence.runtimeEnvironment.apiLevel)
        assertEquals("Google", evidence.runtimeEnvironment.manufacturer)
        assertEquals("Pixel 10 Pro XL", evidence.runtimeEnvironment.model)
        assertEquals(AndroidArgon2idBuildProfile.DebugInstrumented, evidence.runtimeEnvironment.buildProfile)
        assertFalse(evidence.releaseLikeEvidence)
        assertFalse(evidence.thermalLoadRepeatabilityChecked)
        assertTrue(evidence.publicNonSecretFixture)
        assertFalse(evidence.productionKdfApproved)
        assertEquals(listOf(16, 32), evidence.runResults.map { it.memoryMiB })
        assertTrue(evidence.runResults.all { it.version == Argon2idVersion.Version19 })

        assertTrue(assessment.highEndEvidencePresent)
        assertFalse(assessment.midRangeEvidencePresent)
        assertFalse(assessment.lowEndEvidencePresent)
        assertFalse(assessment.androidBaselineSatisfied)
        assertFalse(AndroidArgon2idBaselineBlocker.HighEndEvidenceMissing in assessment.blockers)
    }

    @Test
    fun highEndEvidenceAloneLeavesAndroidBaselineBlockers() {
        val assessment = AndroidArgon2idCalibrationEvidencePolicy.assess()

        assertContains(assessment.blockers, AndroidArgon2idBaselineBlocker.MidRangeEvidenceMissing)
        assertContains(assessment.blockers, AndroidArgon2idBaselineBlocker.LowEndEvidenceMissing)
        assertContains(assessment.blockers, AndroidArgon2idBaselineBlocker.ReleaseLikeEvidenceMissing)
        assertContains(assessment.blockers, AndroidArgon2idBaselineBlocker.ThermalLoadRepeatabilityMissing)
        assertFalse(assessment.releaseLikeEvidencePresent)
        assertFalse(assessment.thermalLoadRepeatabilityPresent)
        assertFalse(assessment.androidBaselineSatisfied)
    }

    @Test
    fun debugInstrumentedEvidenceIsNotReleaseLikeEvidence() {
        val assessment = AndroidArgon2idCalibrationEvidencePolicy.assess()
        val releaseGate = assessment.gates.single {
            it.gate == AndroidArgon2idBaselineAcceptanceGate.ReleaseLikeEvidencePresent
        }
        val manualEvidenceGate = assessment.gates.single {
            it.gate == AndroidArgon2idBaselineAcceptanceGate.ManualEvidenceDoesNotApproveProductionKdf
        }

        assertFalse(releaseGate.satisfied)
        assertTrue(manualEvidenceGate.satisfied)
        assertFalse(assessment.releaseLikeEvidencePresent)
    }

    @Test
    fun ambiguousOrNonMibMemoryUnitsAreRejected() {
        val noUnit = assertIs<AndroidArgon2idCalibrationEvidenceResult.Rejected>(
            runResult(memoryLabel = "32"),
        )
        val decimalUnit = assertIs<AndroidArgon2idCalibrationEvidenceResult.Rejected>(
            runResult(memoryLabel = "32 MB"),
        )
        val kibUnit = assertIs<AndroidArgon2idCalibrationEvidenceResult.Rejected>(
            runResult(memoryLabel = "32768 KiB"),
        )

        assertEquals(AndroidArgon2idCalibrationEvidenceRejectionReason.AmbiguousMemoryUnit, noUnit.reason)
        assertEquals(AndroidArgon2idCalibrationEvidenceRejectionReason.AmbiguousMemoryUnit, decimalUnit.reason)
        assertEquals(AndroidArgon2idCalibrationEvidenceRejectionReason.MemoryUnitMustBeMib, kibUnit.reason)
    }

    @Test
    fun zeroOrNegativeElapsedTimeIsRejected() {
        val zero = assertIs<AndroidArgon2idCalibrationEvidenceResult.Rejected>(
            runResult(elapsedMillis = 0),
        )
        val negative = assertIs<AndroidArgon2idCalibrationEvidenceResult.Rejected>(
            runResult(elapsedMillis = -1),
        )

        assertEquals(AndroidArgon2idCalibrationEvidenceRejectionReason.InvalidElapsedMillis, zero.reason)
        assertEquals(AndroidArgon2idCalibrationEvidenceRejectionReason.InvalidElapsedMillis, negative.reason)
    }

    @Test
    fun repeatedEvidenceRequiresPositiveOrderedTimingSummary() {
        val missingSummary = assertIs<AndroidArgon2idCalibrationEvidenceResult.Rejected>(
            runResult(runCount = 3),
        )
        val unorderedSummary = assertIs<AndroidArgon2idCalibrationEvidenceResult.Rejected>(
            runResult(
                runCount = 3,
                minElapsedMillis = 900,
                medianElapsedMillis = 700,
                maxElapsedMillis = 800,
            ),
        )
        val accepted = assertIs<AndroidArgon2idCalibrationEvidenceResult.Accepted<AndroidArgon2idCalibrationRunResult>>(
            runResult(
                runCount = 3,
                minElapsedMillis = 690,
                medianElapsedMillis = 707,
                maxElapsedMillis = 730,
            ),
        )

        assertEquals(AndroidArgon2idCalibrationEvidenceRejectionReason.InvalidRepeatedTiming, missingSummary.reason)
        assertEquals(AndroidArgon2idCalibrationEvidenceRejectionReason.InvalidRepeatedTiming, unorderedSummary.reason)
        assertEquals(3, accepted.value.runCount)
        assertEquals(707, accepted.value.medianElapsedMillis)
    }

    @Test
    fun secretLikeOrPersonalEvidenceFieldsAreRejected() {
        val secretLikeNote = assertIs<AndroidArgon2idCalibrationEvidenceResult.Rejected>(
            AndroidArgon2idRuntimeEnvironment.recorded(
                androidVersion = "Android 16",
                apiLevel = 36,
                manufacturer = "Google",
                model = "Pixel 10 Pro XL",
                buildProfile = AndroidArgon2idBuildProfile.DebugInstrumented,
                thermalStateNote = "do not record passphrase label here",
                foregroundBackgroundNote = "foreground",
                batteryChargingNote = "not recorded",
                memoryPressureNote = "not recorded",
            ),
        )
        val personalIdentifier = assertIs<AndroidArgon2idCalibrationEvidenceResult.Rejected>(
            AndroidArgon2idRuntimeEnvironment.recorded(
                androidVersion = "Android 16",
                apiLevel = 36,
                manufacturer = "Google",
                model = "device serial omitted",
                buildProfile = AndroidArgon2idBuildProfile.DebugInstrumented,
                thermalStateNote = "normal",
                foregroundBackgroundNote = "foreground",
                batteryChargingNote = "not recorded",
                memoryPressureNote = "not recorded",
            ),
        )

        assertEquals(
            AndroidArgon2idCalibrationEvidenceRejectionReason.SecretLikeFieldRejected,
            secretLikeNote.reason,
        )
        assertEquals(
            AndroidArgon2idCalibrationEvidenceRejectionReason.SecretLikeFieldRejected,
            personalIdentifier.reason,
        )
    }

    @Test
    fun manualEvidenceCannotApproveProductionKdfOrProviderSelection() {
        val policy = commonArgon2idCalibrationPolicy()
        val assessment = AndroidArgon2idCalibrationEvidencePolicy.assess()
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertContains(
            policy.candidateParameterPolicy.futureCalibrationRequirements,
            Argon2idFutureCalibrationRequirement.AndroidManualEvidenceCaptureProtocol,
        )
        assertFalse(policy.productionKdfEnabled)
        assertFalse(assessment.productionKdfApproved)
        assertFalse(assessment.finalProductionParametersApproved)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertTrue(selection.selectedProvider is DisabledVaultCryptoProvider)
        assertTrue(selection.selectedProviderIsDisabled)
        assertFalse(selection.productionProviderSelectable)
    }

    @Test
    fun readinessAndDependencyModelsExposeCapturePolicyWithoutApprovingStorage() {
        val readiness = EncryptedVaultReadinessPolicy.disabled()
        val selected = VaultCryptoDependencyProbeCatalog.currentSpikeResults()
            .single { it.candidate == VaultCryptoDependencyCandidate.TinkBouncyCastleSplit }

        assertContains(
            readiness.capabilities,
            EncryptedVaultCapability.AndroidArgon2idCalibrationEvidenceCaptureModel,
        )
        assertContains(
            selected.capabilities,
            VaultCryptoDependencyCapability.AndroidArgon2idCalibrationEvidenceCaptureModeled,
        )
        assertFalse(readiness.readyForProductionPersistence)
        assertFalse(readiness.secureSecretStorageAvailable)
        assertFalse(readiness.secureMetadataStorageAvailable)
        assertFalse(selected.implementationEnabled)
        assertFalse(selected.storageEnabled)
        assertFalse(selected.productionPersistenceEnabled)
    }

    private fun runResult(
        memoryLabel: String = "32 MiB",
        elapsedMillis: Long = 707,
        runCount: Int = 1,
        minElapsedMillis: Long? = null,
        medianElapsedMillis: Long? = null,
        maxElapsedMillis: Long? = null,
    ): AndroidArgon2idCalibrationEvidenceResult<AndroidArgon2idCalibrationRunResult> =
        AndroidArgon2idCalibrationRunResult.recorded(
            memoryLabel = memoryLabel,
            passes = 3,
            lanes = 1,
            outputBytes = 32,
            elapsedMillis = elapsedMillis,
            runCount = runCount,
            minElapsedMillis = minElapsedMillis,
            medianElapsedMillis = medianElapsedMillis,
            maxElapsedMillis = maxElapsedMillis,
        )
}
