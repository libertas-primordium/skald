package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.Argon2idCalibrationRejectionReason
import com.libertasprimordium.skald.security.SkaldVaultV1Argon2idCalibrationParameterStrength
import com.libertasprimordium.skald.security.SkaldVaultV1Argon2idCalibrationPolicy
import com.libertasprimordium.skald.security.SkaldVaultV1Argon2idCalibrationResult
import com.libertasprimordium.skald.security.SkaldVaultV1Argon2idParameters
import com.libertasprimordium.skald.security.SkaldVaultV1Argon2idType
import com.libertasprimordium.skald.security.SkaldVaultV1Argon2idValidatedParameters
import com.libertasprimordium.skald.security.SkaldVaultV1PrototypeKdfCompatibility
import com.libertasprimordium.skald.security.commonProductionProviderAcceptanceContract
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class PrototypeKdfCompatibilityTest {
    @Test
    fun extractedDefaultsAndEnumOrderPreserveUnsupportedPrototypeValues() {
        val parameters = SkaldVaultV1Argon2idParameters()

        assertEquals(listOf("Argon2id", "Argon2i", "Argon2d"), SkaldVaultV1Argon2idType.entries.map { it.name })
        assertEquals(listOf("Argon2id", "Argon2i", "Argon2d"), SkaldVaultV1Argon2idType.entries.map { it.label })
        assertEquals(listOf(0, 1, 2), SkaldVaultV1Argon2idType.entries.map { it.ordinal })
        assertEquals(SkaldVaultV1Argon2idType.Argon2id, parameters.type)
        assertEquals(19, parameters.version)
        assertEquals(65_536, parameters.memoryKiB)
        assertEquals(3, parameters.iterations)
        assertEquals(1, parameters.parallelism)
        // This is historical P0 compatibility, not the canonical 32-byte KEK/root decision.
        assertEquals(64, parameters.outputBytes)
        assertEquals(19, SkaldVaultV1PrototypeKdfCompatibility.ARGON2_VERSION_19)
        assertEquals(65_536, SkaldVaultV1PrototypeKdfCompatibility.MEMORY_KIB)
        assertEquals(3, SkaldVaultV1PrototypeKdfCompatibility.ITERATIONS)
        assertEquals(1, SkaldVaultV1PrototypeKdfCompatibility.PARALLELISM)
        assertEquals(64, SkaldVaultV1PrototypeKdfCompatibility.OUTPUT_BYTES)
    }

    @Test
    fun extractedParametersPreserveConstructorOrderAndDataClassBehavior() {
        val original = SkaldVaultV1Argon2idParameters()
        val same = SkaldVaultV1Argon2idParameters(SkaldVaultV1Argon2idType.Argon2id, 19, 65_536, 3, 1, 64)
        val changed = original.copy(memoryKiB = 98_304, iterations = 4)
        val (type, version, memoryKiB, iterations, parallelism, outputBytes) = changed

        assertEquals(original, same)
        assertEquals(original.hashCode(), same.hashCode())
        assertEquals(original, original.copy())
        assertEquals(SkaldVaultV1Argon2idType.Argon2id, type)
        assertEquals(19, version)
        assertEquals(98_304, memoryKiB)
        assertEquals(4, iterations)
        assertEquals(1, parallelism)
        assertEquals(64, outputBytes)
        assertEquals(65_536, original.memoryKiB)
        assertEquals(3, original.iterations)
        assertEquals(
            "SkaldVaultV1Argon2idParameters(type=Argon2id, version=19, memoryKiB=65536, " +
                "iterations=3, parallelism=1, outputBytes=64)",
            original.toString(),
        )
    }

    @Test
    fun compatibilityDefaultsAndVersionRejectionsKeepBothCalibrationPathsUnchanged() {
        val parameters = SkaldVaultV1Argon2idParameters()
        val creation = assertIs<SkaldVaultV1Argon2idCalibrationResult.Accepted<SkaldVaultV1Argon2idValidatedParameters>>(
            SkaldVaultV1Argon2idCalibrationPolicy.validateCreationParameters(parameters, 16),
        ).value
        val stored = assertIs<SkaldVaultV1Argon2idCalibrationResult.Accepted<SkaldVaultV1Argon2idValidatedParameters>>(
            SkaldVaultV1Argon2idCalibrationPolicy.validateStoredVaultParameters(parameters, 16),
        ).value
        assertEquals(parameters, creation.parameters)
        assertEquals(parameters, stored.parameters)
        assertEquals(SkaldVaultV1Argon2idCalibrationParameterStrength.Floor, creation.strength)
        assertEquals(SkaldVaultV1Argon2idCalibrationParameterStrength.Floor, stored.strength)
        assertFalse(creation.existingStoredParametersAuthoritative)
        assertTrue(stored.existingStoredParametersAuthoritative)
        assertFalse(creation.downgradeAllowed)
        assertFalse(stored.downgradeAllowed)

        val rejected = listOf(
            parameters.copy(version = 18) to Argon2idCalibrationRejectionReason.UnsupportedArgon2Version,
            parameters.copy(version = 20) to Argon2idCalibrationRejectionReason.UnsupportedArgon2Version,
            parameters.copy(type = SkaldVaultV1Argon2idType.Argon2i) to Argon2idCalibrationRejectionReason.UnsupportedArgon2Type,
            parameters.copy(type = SkaldVaultV1Argon2idType.Argon2d) to Argon2idCalibrationRejectionReason.UnsupportedArgon2Type,
        )
        rejected.forEach { (candidate, reason) ->
            listOf(
                SkaldVaultV1Argon2idCalibrationPolicy.validateCreationParameters(candidate, 16),
                SkaldVaultV1Argon2idCalibrationPolicy.validateStoredVaultParameters(candidate, 16),
            ).forEach { result ->
                assertEquals(reason, assertIs<SkaldVaultV1Argon2idCalibrationResult.Rejected>(result).reason)
            }
        }
        assertFalse(SkaldVaultV1Argon2idCalibrationPolicy.evidence.productionKdfEnabled)
    }

    @Test
    fun compatibilityTypeStillSupportsHistoricalContractWithoutProviderOrPersistenceApproval() {
        val contract = commonProductionProviderAcceptanceContract()
        val policy = contract.argon2idRootDerivationPolicy
        val assessment = contract.assess()

        assertEquals(SkaldVaultV1Argon2idType.Argon2id, policy.type)
        assertEquals(19, policy.version.numericVersion)
        assertEquals(64, policy.minimumMemoryMiB)
        assertEquals(3, policy.minimumIterations)
        assertEquals(1, policy.parallelism)
        assertEquals(64, policy.outputRootMaterialBytes)
        assertFalse(policy.parameterDowngradeImplemented)
        assertFalse(policy.productionVaultCreationWired)
        assertFalse(assessment.productionProviderSelectable)
        assertFalse(assessment.productionPersistenceAllowed)
    }
}
