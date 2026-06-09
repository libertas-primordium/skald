package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.EncryptedVaultCapability
import com.libertasprimordium.skald.security.EncryptedVaultPlatform
import com.libertasprimordium.skald.security.EncryptedVaultReadinessPolicy
import com.libertasprimordium.skald.security.EncryptedVaultRequirement
import com.libertasprimordium.skald.security.ForbiddenRandomApi
import com.libertasprimordium.skald.security.RuntimeProviderPrimitiveCheckResult
import com.libertasprimordium.skald.security.RuntimeRandomnessAvailabilityCheck
import com.libertasprimordium.skald.security.RuntimeRandomnessBlocker
import com.libertasprimordium.skald.security.RuntimeRandomnessCheckState
import com.libertasprimordium.skald.security.RuntimeRandomnessSampleEvidence
import com.libertasprimordium.skald.security.RuntimeRandomnessSourceKind
import com.libertasprimordium.skald.security.VaultCreationFailClosedReason
import com.libertasprimordium.skald.security.VaultCryptoDependencyCandidate
import com.libertasprimordium.skald.security.VaultCryptoDependencyCapability
import com.libertasprimordium.skald.security.VaultCryptoDependencyProbeCatalog
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import com.libertasprimordium.skald.security.VaultRandomnessUse
import com.libertasprimordium.skald.security.commonArgon2idCalibrationPolicy
import com.libertasprimordium.skald.security.commonRuntimeRandomnessProviderPolicy
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RuntimeRandomnessProviderPolicyTest {
    private val policy = commonRuntimeRandomnessProviderPolicy()

    @Test
    fun osAndReviewedProviderRandomnessAreAcceptedForCompatibilityEvidence() {
        val osResult = policy.evaluate(check(RuntimeRandomnessSourceKind.OsCryptographicRandomness))
        val providerResult = policy.evaluate(check(RuntimeRandomnessSourceKind.ReviewedCryptoProviderRandomness))

        assertTrue(osResult.acceptedForCompatibilityPlanning)
        assertTrue(osResult.acceptedForVaultMaterial)
        assertTrue(providerResult.acceptedForCompatibilityPlanning)
        assertTrue(providerResult.acceptedForVaultMaterial)
        assertContains(policy.acceptedSources, RuntimeRandomnessSourceKind.OsCryptographicRandomness)
        assertContains(policy.acceptedSources, RuntimeRandomnessSourceKind.ReviewedCryptoProviderRandomness)
        assertContains(policy.protectedUses, VaultRandomnessUse.Salt)
        assertContains(policy.protectedUses, VaultRandomnessUse.Nonce)
        assertContains(policy.protectedUses, VaultRandomnessUse.FutureReviewedRandomVaultMaterial)
        assertContains(policy.protectedUses, VaultRandomnessUse.FutureReviewedBackupMaterial)
        assertContains(policy.protectedUses, VaultRandomnessUse.FutureReviewedUnlockMaterial)
    }

    @Test
    fun hardwareBackedKeyProtectionAloneIsNotRandomness() {
        val result = policy.evaluate(check(RuntimeRandomnessSourceKind.HardwareBackedKeyProtectionOnly))

        assertFalse(result.acceptedForCompatibilityPlanning)
        assertFalse(result.acceptedForVaultMaterial)
        assertContains(result.blockers, RuntimeRandomnessBlocker.HardwareKeyProtectionMistakenForRandomness)
        assertTrue(policy.hardwareBackedKeyProtectionPreferred)
        assertFalse(policy.hardwareBackedKeyProtectionRequired)
    }

    @Test
    fun forbiddenLanguageRandomApisAreRejectedByPolicy() {
        assertEquals(ForbiddenRandomApi.entries.toSet(), policy.forbiddenApis)

        ForbiddenRandomApi.entries.forEach { api ->
            val result = policy.evaluate(check(api.sourceKind))

            assertEquals(RuntimeRandomnessSourceKind.ForbiddenLanguageRandom, api.sourceKind)
            assertFalse(result.acceptedForVaultMaterial)
            assertContains(result.blockers, RuntimeRandomnessBlocker.ForbiddenLanguageRandomApi)
        }
    }

    @Test
    fun unknownRandomnessOrProviderStateBlocksVaultCreationWithWarning() {
        val randomness = policy.evaluate(
            check(
                sourceKind = RuntimeRandomnessSourceKind.Unknown,
                state = RuntimeRandomnessCheckState.Unknown,
            ),
        )
        val providerPrimitive = RuntimeProviderPrimitiveCheckResult(
            platform = EncryptedVaultPlatform.Android,
            providerRuntimeCheck = RuntimeRandomnessCheckState.Unknown,
            primitiveRuntimeCheck = RuntimeRandomnessCheckState.Unknown,
            randomnessCheck = randomness,
            safeDetail = "Unknown runtime provider state is not sufficient for vault creation.",
        )
        val gate = policy.gate(providerPrimitive)

        assertFalse(randomness.acceptedForCompatibilityPlanning)
        assertContains(randomness.blockers, RuntimeRandomnessBlocker.UnknownProviderState)
        assertContains(randomness.blockers, RuntimeRandomnessBlocker.UnknownRandomnessSource)
        assertFalse(providerPrimitive.compatibilityPlanningSatisfied)
        assertFalse(gate.randomnessGateSatisfied)
        assertFalse(gate.vaultCreationAllowed)
        assertContains(gate.failClosedReasons, VaultCreationFailClosedReason.UnknownProviderState)
        assertContains(gate.failClosedReasons, VaultCreationFailClosedReason.UnknownEntropyState)
        assertTrue(gate.userFacingWarnings.isNotEmpty())
    }

    @Test
    fun failedApprovedRandomnessAvailabilityCheckBlocksVaultCreation() {
        val randomness = policy.evaluate(
            check(
                sourceKind = RuntimeRandomnessSourceKind.OsCryptographicRandomness,
                state = RuntimeRandomnessCheckState.Failed,
            ),
        )
        val providerPrimitive = RuntimeProviderPrimitiveCheckResult(
            platform = EncryptedVaultPlatform.LinuxDesktop,
            providerRuntimeCheck = RuntimeRandomnessCheckState.Passed,
            primitiveRuntimeCheck = RuntimeRandomnessCheckState.Passed,
            randomnessCheck = randomness,
            safeDetail = "Approved randomness path failed to instantiate.",
        )
        val gate = policy.gate(providerPrimitive)

        assertFalse(randomness.acceptedForCompatibilityPlanning)
        assertContains(randomness.blockers, RuntimeRandomnessBlocker.ApprovedRandomnessUnavailable)
        assertContains(gate.failClosedReasons, VaultCreationFailClosedReason.RuntimeRandomnessCheckMissing)
        assertTrue(gate.userFacingWarnings.isNotEmpty())
        assertFalse(gate.vaultCreationAllowed)
    }

    @Test
    fun tinyNonSecretSampleIsAvailabilityEvidenceOnly() {
        val result = policy.evaluate(
            check(
                sourceKind = RuntimeRandomnessSourceKind.OsCryptographicRandomness,
                sampleEvidence = RuntimeRandomnessSampleEvidence.nonSecretAvailabilitySample(sampleByteCount = 16),
            ),
        )
        val overclaimed = policy.evaluate(
            check(
                sourceKind = RuntimeRandomnessSourceKind.OsCryptographicRandomness,
                sampleEvidence = RuntimeRandomnessSampleEvidence(
                    nonSecretSampleGenerated = true,
                    sampleByteCount = 16,
                    samplePersisted = false,
                    sampleLogged = false,
                    sampleUsedAsVaultMaterial = false,
                    entropyQualityProven = true,
                ),
            ),
        )

        assertTrue(result.acceptedForCompatibilityPlanning)
        assertFalse(result.entropyQualityProven)
        assertFalse(result.productionEntropyCollection)
        assertFalse(result.check.sampleEvidence.samplePersisted)
        assertFalse(result.check.sampleEvidence.sampleLogged)
        assertFalse(result.check.sampleEvidence.sampleUsedAsVaultMaterial)
        assertContains(
            overclaimed.blockers,
            RuntimeRandomnessBlocker.NonSecretSampleMisrepresentedAsEntropyQualityProof,
        )
        assertFalse(overclaimed.acceptedForVaultMaterial)
    }

    @Test
    fun runtimeProviderPrimitivePlanningDoesNotApproveProductionProviderOrKdf() {
        val randomness = policy.evaluate(check(RuntimeRandomnessSourceKind.OsCryptographicRandomness))
        val providerPrimitive = RuntimeProviderPrimitiveCheckResult(
            platform = EncryptedVaultPlatform.Android,
            providerRuntimeCheck = RuntimeRandomnessCheckState.Passed,
            primitiveRuntimeCheck = RuntimeRandomnessCheckState.Passed,
            randomnessCheck = randomness,
            safeDetail = "Runtime provider and primitive checks can satisfy compatibility planning only.",
        )
        val selection = VaultCryptoProviderSelectionRegistry.select()
        val argon2id = commonArgon2idCalibrationPolicy()

        assertTrue(providerPrimitive.compatibilityPlanningSatisfied)
        assertFalse(providerPrimitive.productionProviderApproved)
        assertFalse(providerPrimitive.productionProviderSelectable)
        assertFalse(argon2id.productionKdfEnabled)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertTrue(selection.selectedProvider is DisabledVaultCryptoProvider)
        assertTrue(selection.selectedProviderIsDisabled)
        assertFalse(selection.productionProviderSelectable)
    }

    @Test
    fun readinessAndDependencyExposeRuntimeRandomnessModelWithoutStorageReadiness() {
        val readiness = EncryptedVaultReadinessPolicy.disabled()
        val selected = VaultCryptoDependencyProbeCatalog.currentSpikeResults()
            .single { it.candidate == VaultCryptoDependencyCandidate.TinkBouncyCastleSplit }

        assertContains(readiness.capabilities, EncryptedVaultCapability.RuntimeRandomnessProviderCheckModel)
        assertContains(readiness.requirementStatuses.keys, EncryptedVaultRequirement.RuntimeRandomnessProviderChecksModeled)
        assertContains(selected.capabilities, VaultCryptoDependencyCapability.RuntimeRandomnessProviderChecksModeled)
        assertFalse(readiness.readyForProductionPersistence)
        assertFalse(readiness.secureSecretStorageAvailable)
        assertFalse(readiness.secureMetadataStorageAvailable)
        assertFalse(selected.implementationEnabled)
        assertFalse(selected.storageEnabled)
        assertFalse(selected.productionPersistenceEnabled)
    }

    private fun check(
        sourceKind: RuntimeRandomnessSourceKind,
        state: RuntimeRandomnessCheckState = RuntimeRandomnessCheckState.Passed,
        sampleEvidence: RuntimeRandomnessSampleEvidence = RuntimeRandomnessSampleEvidence.noSample(),
    ): RuntimeRandomnessAvailabilityCheck =
        RuntimeRandomnessAvailabilityCheck(
            platform = EncryptedVaultPlatform.LinuxDesktop,
            sourceKind = sourceKind,
            state = state,
            providerName = "reviewed-runtime-provider",
            algorithmName = "reviewed-runtime-random",
            sampleEvidence = sampleEvidence,
            productionEntropyCollection = false,
        )
}
