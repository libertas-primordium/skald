package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.EncryptedVaultPlatform
import com.libertasprimordium.skald.security.RuntimeProviderPrimitiveCheckResult
import com.libertasprimordium.skald.security.RuntimeRandomnessAvailabilityCheck
import com.libertasprimordium.skald.security.RuntimeRandomnessCheckState
import com.libertasprimordium.skald.security.RuntimeRandomnessSampleEvidence
import com.libertasprimordium.skald.security.RuntimeRandomnessSourceKind
import com.libertasprimordium.skald.security.commonRuntimeRandomnessProviderPolicy
import java.security.SecureRandom
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class VaultRuntimeRandomnessProviderProbeTest {
    @Test
    fun linuxJvmSecureRandomAvailabilityProbeUsesNonSecretSampleOnly() {
        val secureRandom = SecureRandom()
        val sample = ByteArray(16)
        secureRandom.nextBytes(sample)

        val check = RuntimeRandomnessAvailabilityCheck(
            platform = EncryptedVaultPlatform.LinuxDesktop,
            sourceKind = RuntimeRandomnessSourceKind.OsCryptographicRandomness,
            state = RuntimeRandomnessCheckState.Passed,
            providerName = secureRandom.provider?.name,
            algorithmName = secureRandom.algorithm,
            sampleEvidence = RuntimeRandomnessSampleEvidence.nonSecretAvailabilitySample(
                sampleByteCount = sample.size,
            ),
            productionEntropyCollection = false,
        )
        sample.fill(0)

        val randomnessResult = commonRuntimeRandomnessProviderPolicy().evaluate(check)
        val providerPrimitiveResult = RuntimeProviderPrimitiveCheckResult(
            platform = EncryptedVaultPlatform.LinuxDesktop,
            providerRuntimeCheck = RuntimeRandomnessCheckState.Passed,
            primitiveRuntimeCheck = RuntimeRandomnessCheckState.Passed,
            randomnessCheck = randomnessResult,
            safeDetail = "Linux/JVM runtime randomness provider availability probe; not production entropy collection.",
        )

        assertTrue(randomnessResult.acceptedForCompatibilityPlanning)
        assertTrue(randomnessResult.acceptedForVaultMaterial)
        assertTrue(providerPrimitiveResult.compatibilityPlanningSatisfied)
        assertFalse(randomnessResult.entropyQualityProven)
        assertFalse(randomnessResult.productionEntropyCollection)
        assertFalse(randomnessResult.check.sampleEvidence.samplePersisted)
        assertFalse(randomnessResult.check.sampleEvidence.sampleLogged)
        assertFalse(randomnessResult.check.sampleEvidence.sampleUsedAsVaultMaterial)
        assertFalse(providerPrimitiveResult.productionProviderApproved)
        assertFalse(providerPrimitiveResult.productionProviderSelectable)
    }
}
