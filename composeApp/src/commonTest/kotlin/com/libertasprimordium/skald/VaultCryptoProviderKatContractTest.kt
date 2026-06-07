package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.VaultCryptoKatRequirementStatus
import com.libertasprimordium.skald.security.VaultCryptoAssociatedDataContext
import com.libertasprimordium.skald.security.VaultCryptoProviderKatBlocker
import com.libertasprimordium.skald.security.VaultCryptoProviderKatCategory
import com.libertasprimordium.skald.security.VaultCryptoProviderKatContractStatus
import com.libertasprimordium.skald.security.VaultCryptoProviderKatEvidence
import com.libertasprimordium.skald.security.VaultCryptoProviderKatExecutionScope
import com.libertasprimordium.skald.security.VaultCryptoProviderKatRequest
import com.libertasprimordium.skald.security.VaultCryptoProviderKatVectorId
import com.libertasprimordium.skald.security.VaultCryptoRecordPurpose
import com.libertasprimordium.skald.security.commonProviderKatContract
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class VaultCryptoProviderKatContractTest {
    @Test
    fun providerKatContractRequiresPositiveDependencyVectorsThroughProviderBoundary() {
        val contract = commonProviderKatContract()
        val positive = contract.requirements.filter { it.positiveTest }

        assertEquals(VaultCryptoProviderKatContractStatus.ContractModeledProviderMissing, contract.status)
        assertContains(positive.map { it.vectorId }, VaultCryptoProviderKatVectorId.Argon2idRfc9106Section53)
        assertContains(positive.map { it.vectorId }, VaultCryptoProviderKatVectorId.XChaCha20Poly1305DraftAppendixA1)
        assertTrue(positive.all { it.requiresDesktopRuntime })
        assertTrue(positive.all { it.requiresAndroidRuntime })
        assertTrue(positive.all { it.status == VaultCryptoKatRequirementStatus.DependencyLevelPassedProviderLevelMissing })
        assertFalse(contract.dependencyLevelKatsSatisfyProviderContract)
        assertFalse(contract.providerLevelKatsPassed)
    }

    @Test
    fun providerKatContractRequiresNegativeMisuseVectorsBeforeApproval() {
        val contract = commonProviderKatContract()
        val negativeVectorIds = contract.requirements
            .filter { it.category == VaultCryptoProviderKatCategory.NegativeMisuseVector }
            .map { it.vectorId }

        assertContains(negativeVectorIds, VaultCryptoProviderKatVectorId.AssociatedDataMismatchFails)
        assertContains(negativeVectorIds, VaultCryptoProviderKatVectorId.CiphertextTamperingFails)
        assertContains(negativeVectorIds, VaultCryptoProviderKatVectorId.TagTamperingFails)
        assertContains(negativeVectorIds, VaultCryptoProviderKatVectorId.WrongKeyFails)
        assertTrue(
            contract.requirements
                .filter { it.category == VaultCryptoProviderKatCategory.NegativeMisuseVector }
                .all { !it.positiveTest && it.status == VaultCryptoKatRequirementStatus.NegativeProviderTestRequired },
        )
        assertContains(contract.blockers, VaultCryptoProviderKatBlocker.NegativeMisuseProviderKatsMissing)
    }

    @Test
    fun providerKatContractRequiresAlgorithmAndNoncePolicyRejectionTests() {
        val contract = commonProviderKatContract()
        val ids = contract.requirements.map { it.vectorId }
        val noncePolicy = contract.requirements.filter { it.category == VaultCryptoProviderKatCategory.NoncePolicy }

        assertContains(ids, VaultCryptoProviderKatVectorId.UnsupportedAlgorithmRejected)
        assertContains(ids, VaultCryptoProviderKatVectorId.Pbkdf2DefaultRejected)
        assertContains(ids, VaultCryptoProviderKatVectorId.ScryptFallbackNotSelected)
        assertContains(ids, VaultCryptoProviderKatVectorId.ProductionNonceBypassRejected)
        assertContains(ids, VaultCryptoProviderKatVectorId.CallerProvidedProductionNonceRejected)
        assertTrue(noncePolicy.all { !it.productionCallerNonceAllowed })
        assertContains(contract.blockers, VaultCryptoProviderKatBlocker.UnsupportedAlgorithmRejectionUntested)
        assertContains(contract.blockers, VaultCryptoProviderKatBlocker.NoncePolicyBypassUntested)
    }

    @Test
    fun providerKatContractRequiresRuntimeCoverageAndRedaction() {
        val contract = commonProviderKatContract()
        val ids = contract.requirements.map { it.vectorId }
        val redaction = contract.requirements.filter { it.category == VaultCryptoProviderKatCategory.Redaction }

        assertContains(ids, VaultCryptoProviderKatVectorId.DesktopProviderRuntimeCoverage)
        assertContains(ids, VaultCryptoProviderKatVectorId.AndroidProviderRuntimeCoverage)
        assertContains(ids, VaultCryptoProviderKatVectorId.ReleaseLikeProviderRuntimeCoverage)
        assertContains(ids, VaultCryptoProviderKatVectorId.DiagnosticsExcludePlaintext)
        assertContains(ids, VaultCryptoProviderKatVectorId.DiagnosticsExcludeDerivedKeys)
        assertContains(ids, VaultCryptoProviderKatVectorId.DiagnosticsExcludeUnlockMaterial)
        assertContains(ids, VaultCryptoProviderKatVectorId.DiagnosticsExcludeSecretKeys)
        assertContains(ids, VaultCryptoProviderKatVectorId.DiagnosticsExcludeDecryptedPayloads)
        assertTrue(redaction.all { it.status == VaultCryptoKatRequirementStatus.RedactionTestRequired })
        assertContains(contract.blockers, VaultCryptoProviderKatBlocker.DesktopProviderRuntimeKatMissing)
        assertContains(contract.blockers, VaultCryptoProviderKatBlocker.AndroidProviderRuntimeKatMissing)
        assertContains(contract.blockers, VaultCryptoProviderKatBlocker.ReleaseLikeProviderRuntimeKatMissing)
        assertContains(contract.blockers, VaultCryptoProviderKatBlocker.ProviderRedactionTestsMissing)
    }

    @Test
    fun providerKatContractDoesNotApproveStorageOrProductionProvider() {
        val contract = commonProviderKatContract()
        val result = contract.validationResult

        assertContains(
            contract.requirements.map { it.vectorId },
            VaultCryptoProviderKatVectorId.VaultStorageApprovalSeparated,
        )
        assertContains(contract.blockers, VaultCryptoProviderKatBlocker.VaultStorageApprovalSeparate)
        assertContains(contract.blockers, VaultCryptoProviderKatBlocker.ExecutableProviderMissing)
        assertTrue(result.dependencyLevelEvidenceOnly)
        assertFalse(result.providerLevelKatsPassed)
        assertFalse(result.canApproveProductionProvider)
    }

    @Test
    fun providerKatRequestAndEvidenceUseOnlySkaldOwnedRedactedTypes() {
        val contract = commonProviderKatContract()
        val requirement = contract.requirements.single {
            it.vectorId == VaultCryptoProviderKatVectorId.Argon2idRfc9106Section53
        }
        val request = VaultCryptoProviderKatRequest(
            requirement = requirement,
            context = VaultCryptoAssociatedDataContext(
                containerVersion = 1,
                recordPurpose = VaultCryptoRecordPurpose.ProviderKatTestRecord,
                recordSchemaVersion = 1,
                keyVersion = 1,
            ),
        )
        val evidence = VaultCryptoProviderKatEvidence.redacted(
            vectorId = request.vectorId,
            category = request.category,
            executionScope = VaultCryptoProviderKatExecutionScope.TestHarnessOnly,
        )

        assertEquals(VaultCryptoProviderKatVectorId.Argon2idRfc9106Section53, request.vectorId)
        assertEquals(VaultCryptoProviderKatCategory.PositiveKdfVector, request.category)
        assertEquals(VaultCryptoProviderKatExecutionScope.TestHarnessOnly, evidence.executionScope)
        assertFalse(evidence.executionScope.productionProvider)
        assertTrue(evidence.toString().contains("REDACTED"))
        assertFalse(evidence.toString().contains("Argon2idRfc9106Section53"))
    }
}
