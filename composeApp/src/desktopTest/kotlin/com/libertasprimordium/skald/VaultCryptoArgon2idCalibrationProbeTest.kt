package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.Argon2idCalibrationPlatformClass
import com.libertasprimordium.skald.security.Argon2idCalibrationResultSummary
import com.libertasprimordium.skald.security.Argon2idVersion
import com.libertasprimordium.skald.security.commonArgon2idCalibrationPolicy
import org.bouncycastle.crypto.generators.Argon2BytesGenerator
import org.bouncycastle.crypto.params.Argon2Parameters
import kotlin.system.measureNanoTime
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class VaultCryptoArgon2idCalibrationProbeTest {
    @Test
    fun desktopArgon2idCalibrationProbeRunsBoundedPublicFixtureCandidates() {
        val policy = commonArgon2idCalibrationPolicy()
        val candidates = policy.desktopProbeCandidates

        assertEquals(2, candidates.size)
        assertFalse(policy.productionKdfEnabled)

        val summaries = candidates.map { candidate ->
            val output = ByteArray(candidate.outputLength.bytes)
            val publicInput = publicFixtureBytes(size = 32, start = 0x11)
            val publicSalt = publicFixtureBytes(size = 16, start = 0x31)
            val parameters = Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
                .withVersion(Argon2Parameters.ARGON2_VERSION_13)
                .withMemoryAsKB(candidate.memoryCost.kib)
                .withIterations(candidate.passes.value)
                .withParallelism(candidate.lanes.value)
                .withSalt(publicSalt)
                .build()

            val elapsedNanos = try {
                measureNanoTime {
                    val generator = Argon2BytesGenerator()
                    generator.init(parameters)
                    generator.generateBytes(publicInput, output)
                }
            } finally {
                parameters.clear()
                publicInput.fill(0)
                publicSalt.fill(0)
                output.fill(0)
            }
            val elapsedMillis = elapsedNanos.coerceAtLeast(1_000_000L) / 1_000_000L
            Argon2idCalibrationResultSummary(
                candidateId = candidate.id,
                platformClass = Argon2idCalibrationPlatformClass.LinuxDesktopJvm,
                elapsedMillis = elapsedMillis,
                publicNonSecretFixture = true,
                persisted = false,
                finalProductionSetting = false,
            )
        }

        summaries.forEach { summary ->
            println(summary.safeSummary)
            assertTrue(summary.elapsedMillis >= 1)
            assertTrue(summary.publicNonSecretFixture)
            assertFalse(summary.persisted)
            assertFalse(summary.finalProductionSetting)
        }
        assertTrue(candidates.all { it.version == Argon2idVersion.Version19 })
        assertTrue(candidates.all { !it.productionRecommendation })
    }

    private fun publicFixtureBytes(size: Int, start: Int): ByteArray =
        ByteArray(size) { index -> (start + index).toByte() }
}
