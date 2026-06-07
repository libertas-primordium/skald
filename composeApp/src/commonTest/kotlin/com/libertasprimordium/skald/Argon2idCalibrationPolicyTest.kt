package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.Argon2idCalibrationImplementationStatus
import com.libertasprimordium.skald.security.Argon2idCalibrationPlatformClass
import com.libertasprimordium.skald.security.Argon2idCalibrationPolicyResult
import com.libertasprimordium.skald.security.Argon2idCalibrationRejectionReason
import com.libertasprimordium.skald.security.Argon2idCalibrationWarning
import com.libertasprimordium.skald.security.Argon2idMemoryCost
import com.libertasprimordium.skald.security.Argon2idMemoryUnit
import com.libertasprimordium.skald.security.Argon2idOutputLength
import com.libertasprimordium.skald.security.Argon2idVersion
import com.libertasprimordium.skald.security.EncryptedVaultKdfAlgorithm
import com.libertasprimordium.skald.security.commonArgon2idCalibrationPolicy
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
    fun lowMemoryAndAndroidProbeWarningsRemainExplicit() {
        val policy = commonArgon2idCalibrationPolicy()
        val lowMemoryAndroid = policy.candidateParameters.single { it.id == "argon2id-probe-16mib-2p-1lane" }
        val warnings = policy.warningsFor(lowMemoryAndroid)

        assertContains(lowMemoryAndroid.platformClasses, Argon2idCalibrationPlatformClass.AndroidRuntime)
        assertContains(warnings, Argon2idCalibrationWarning.ProbeOnlyNotProductionSetting)
        assertContains(warnings, Argon2idCalibrationWarning.LowMemoryProbeCandidate)
        assertContains(warnings, Argon2idCalibrationWarning.TooFastSettingWouldBeWeak)
        assertContains(warnings, Argon2idCalibrationWarning.AndroidDeviceVariance)
        assertContains(warnings, Argon2idCalibrationWarning.TimingIsNotBenchmark)
        assertContains(warnings, Argon2idCalibrationWarning.MemoryZeroizationUnresolved)
    }
}
