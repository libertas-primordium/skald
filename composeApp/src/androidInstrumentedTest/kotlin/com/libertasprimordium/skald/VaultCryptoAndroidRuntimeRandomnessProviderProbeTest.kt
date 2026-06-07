package com.libertasprimordium.skald

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.libertasprimordium.skald.security.EncryptedVaultPlatform
import com.libertasprimordium.skald.security.RuntimeProviderPrimitiveCheckResult
import com.libertasprimordium.skald.security.RuntimeRandomnessAvailabilityCheck
import com.libertasprimordium.skald.security.RuntimeRandomnessCheckState
import com.libertasprimordium.skald.security.RuntimeRandomnessSampleEvidence
import com.libertasprimordium.skald.security.RuntimeRandomnessSourceKind
import com.libertasprimordium.skald.security.commonRuntimeRandomnessProviderPolicy
import java.security.SecureRandom
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VaultCryptoAndroidRuntimeRandomnessProviderProbeTest {
    @Test
    fun androidSecureRandomAvailabilityProbeUsesNonSecretSampleOnly() {
        val secureRandom = SecureRandom()
        val sample = ByteArray(16)
        secureRandom.nextBytes(sample)

        val check = RuntimeRandomnessAvailabilityCheck(
            platform = EncryptedVaultPlatform.Android,
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
            platform = EncryptedVaultPlatform.Android,
            providerRuntimeCheck = RuntimeRandomnessCheckState.Passed,
            primitiveRuntimeCheck = RuntimeRandomnessCheckState.Passed,
            randomnessCheck = randomnessResult,
            safeDetail = "Android runtime randomness provider availability probe; not production entropy collection.",
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
