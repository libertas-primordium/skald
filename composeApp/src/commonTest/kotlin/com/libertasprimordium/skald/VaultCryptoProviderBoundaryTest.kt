package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.EncryptedVaultAeadAlgorithm
import com.libertasprimordium.skald.security.EncryptedVaultKdfAlgorithm
import com.libertasprimordium.skald.security.EncryptedVaultNoncePolicy
import com.libertasprimordium.skald.security.VaultCryptoAeadDecryptRequest
import com.libertasprimordium.skald.security.VaultCryptoAeadEncryptRequest
import com.libertasprimordium.skald.security.VaultCryptoAssociatedDataContext
import com.libertasprimordium.skald.security.VaultCryptoKatRequirementStatus
import com.libertasprimordium.skald.security.VaultCryptoKdfRequest
import com.libertasprimordium.skald.security.VaultCryptoKeyGenerationRequest
import com.libertasprimordium.skald.security.VaultCryptoKeyRole
import com.libertasprimordium.skald.security.VaultCryptoKeysetStorageRequest
import com.libertasprimordium.skald.security.VaultCryptoMaterialRef
import com.libertasprimordium.skald.security.VaultCryptoNonceMode
import com.libertasprimordium.skald.security.VaultCryptoOperation
import com.libertasprimordium.skald.security.VaultCryptoProviderBlocker
import com.libertasprimordium.skald.security.VaultCryptoProviderCapability
import com.libertasprimordium.skald.security.VaultCryptoProviderKatBlocker
import com.libertasprimordium.skald.security.VaultCryptoProviderKatContractStatus
import com.libertasprimordium.skald.security.VaultCryptoProviderKatRequest
import com.libertasprimordium.skald.security.VaultCryptoProviderKatVectorId
import com.libertasprimordium.skald.security.VaultCryptoProviderImplementationStatus
import com.libertasprimordium.skald.security.VaultCryptoProviderResult
import com.libertasprimordium.skald.security.VaultCryptoRecordPurpose
import com.libertasprimordium.skald.security.commonDisabledVaultCryptoProviderStatus
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultCryptoProviderBoundaryTest {
    private val context = VaultCryptoAssociatedDataContext(
        containerVersion = 1,
        recordPurpose = VaultCryptoRecordPurpose.ProviderKatTestRecord,
        recordSchemaVersion = 1,
        keyVersion = 1,
    )

    @Test
    fun disabledProviderReportsBoundaryOnlyStatus() {
        val status = commonDisabledVaultCryptoProviderStatus()

        assertEquals(VaultCryptoProviderImplementationStatus.DisabledBoundaryOnly, status.implementationStatus)
        assertContains(status.capabilities, VaultCryptoProviderCapability.SkaldOwnedBoundary)
        assertContains(status.capabilities, VaultCryptoProviderCapability.TypedAlgorithmPolicy)
        assertContains(status.capabilities, VaultCryptoProviderCapability.ProviderLevelKatRequirements)
        assertContains(status.capabilities, VaultCryptoProviderCapability.ProviderLevelKatContract)
        assertContains(status.capabilities, VaultCryptoProviderCapability.RedactedDiagnostics)
        assertFalse(status.canDeriveKeys)
        assertFalse(status.canEncryptRecords)
        assertFalse(status.canDecryptRecords)
        assertFalse(status.canGenerateKeys)
        assertFalse(status.canStoreKeysets)
        assertFalse(status.productionPersistenceEnabled)
        assertFalse(status.mainnetEnabled)
        assertContains(status.blockers, VaultCryptoProviderBlocker.ProviderImplementationMissing)
        assertContains(status.blockers, VaultCryptoProviderBlocker.ProviderLevelKnownAnswerVectorsMissing)
        assertContains(status.blockers, VaultCryptoProviderBlocker.ProviderLevelKatContractUnsatisfied)
        assertContains(status.blockers, VaultCryptoProviderBlocker.ProductionPersistenceDisabled)
        assertContains(status.blockers, VaultCryptoProviderBlocker.MainnetDisabled)
    }

    @Test
    fun disabledProviderRejectsEveryCryptoAndStorageOperation() {
        val provider = DisabledVaultCryptoProvider()

        provider.deriveKey(kdfRequest()).assertBlocked(VaultCryptoProviderBlocker.KdfExecutionDisabled)
        provider.encryptRecord(encryptRequest()).assertBlocked(VaultCryptoProviderBlocker.AeadEncryptionDisabled)
        provider.decryptRecord(decryptRequest()).assertBlocked(VaultCryptoProviderBlocker.AeadDecryptionDisabled)
        provider.generateKey(keyGenerationRequest()).assertBlocked(VaultCryptoProviderBlocker.KeyGenerationDisabled)
        provider.storeKeyset(keysetStorageRequest()).assertBlocked(VaultCryptoProviderBlocker.KeysetStorageDisabled)
        provider.validateKat(katRequest()).assertBlocked(VaultCryptoProviderBlocker.ProviderLevelKnownAnswerVectorsMissing)
    }

    @Test
    fun providerPolicyUsesTypedAlgorithmsAndRejectsPbkdf2Default() {
        val status = commonDisabledVaultCryptoProviderStatus()
        val kdfRequest = kdfRequest()
        val encryptRequest = encryptRequest()

        assertEquals(EncryptedVaultKdfAlgorithm.Argon2id, kdfRequest.algorithm)
        assertEquals(EncryptedVaultAeadAlgorithm.XChaCha20Poly1305, encryptRequest.algorithm)
        assertEquals(24, encryptRequest.noncePolicy.byteLength)
        assertTrue(encryptRequest.noncePolicy.randomPerRecord)
        assertFalse(encryptRequest.nonceMode.callerProvidedProductionNonceAllowed)
        assertFalse(status.algorithmPolicy.acceptsDefaultProductionKdf(EncryptedVaultKdfAlgorithm.Pbkdf2))
        assertTrue(status.algorithmPolicy.designOnly)
    }

    @Test
    fun associatedDataContextRequiresNonSecretEnvelopeContext() {
        assertTrue(context.excludesSensitiveWalletMetadata)
        assertEquals(VaultCryptoRecordPurpose.ProviderKatTestRecord, context.recordPurpose)
        assertTrue(context.requirements.isNotEmpty())
    }

    @Test
    fun providerLevelKatRequirementsAreModeledButUnsatisfied() {
        val status = commonDisabledVaultCryptoProviderStatus()
        val positiveRequirements = status.katRequirements.filter { it.positiveTest }

        assertEquals(VaultCryptoProviderKatContractStatus.ContractModeledProviderMissing, status.katContract.status)
        assertFalse(status.katContract.dependencyLevelKatsSatisfyProviderContract)
        assertFalse(status.katContract.providerLevelKatsPassed)
        assertFalse(status.katContract.validationResult.canApproveProductionProvider)
        assertContains(status.katContract.blockers, VaultCryptoProviderKatBlocker.ExecutableProviderMissing)
        assertContains(
            status.katContract.blockers,
            VaultCryptoProviderKatBlocker.DependencyLevelKatsDoNotSatisfyProviderContract,
        )
        assertTrue(status.katRequirements.size > 2)
        assertContains(
            status.katRequirements.map { it.vectorId },
            VaultCryptoProviderKatVectorId.Argon2idRfc9106Section53,
        )
        assertContains(
            status.katRequirements.map { it.vectorId },
            VaultCryptoProviderKatVectorId.XChaCha20Poly1305DraftAppendixA1,
        )
        assertTrue(positiveRequirements.any { it.operation == VaultCryptoOperation.DeriveKey })
        assertTrue(positiveRequirements.any { it.operation == VaultCryptoOperation.EncryptRecord })
        assertTrue(positiveRequirements.all { it.requiresAndroidRuntime })
        assertTrue(positiveRequirements.all { it.requiresDesktopRuntime })
        assertTrue(
            positiveRequirements.all {
                it.status == VaultCryptoKatRequirementStatus.DependencyLevelPassedProviderLevelMissing
            },
        )
    }

    @Test
    fun diagnosticsAndMaterialRefsAreRedacted() {
        val provider = DisabledVaultCryptoProvider()
        val blocked = assertIs<VaultCryptoProviderResult.Blocked>(provider.deriveKey(kdfRequest()))
        val combined = listOf(
            blocked.toString(),
            blocked.error.toString(),
            blocked.error.diagnostic.toString(),
            VaultCryptoMaterialRef.futureUnlockMaterial().toString(),
            VaultCryptoMaterialRef.futurePlaintextRecord().toString(),
            VaultCryptoMaterialRef.futureCiphertextRecord().toString(),
            VaultCryptoMaterialRef.futureKeyMaterial().toString(),
        ).joinToString("\n")

        assertContains(combined, "REDACTED")
        assertFalse(combined.contains("FutureUnlockMaterial"))
        assertFalse(combined.contains("FuturePlaintextRecord"))
        assertFalse(combined.contains("FutureCiphertextRecord"))
        assertFalse(combined.contains("FutureKeyMaterial"))
    }

    private fun kdfRequest(): VaultCryptoKdfRequest =
        VaultCryptoKdfRequest(
            algorithm = EncryptedVaultKdfAlgorithm.Argon2id,
            inputMaterialRef = VaultCryptoMaterialRef.publicKatVector(),
            outputKeyRole = VaultCryptoKeyRole.VaultKeyEncryptionKey,
            context = context,
        )

    private fun encryptRequest(): VaultCryptoAeadEncryptRequest =
        VaultCryptoAeadEncryptRequest(
            algorithm = EncryptedVaultAeadAlgorithm.XChaCha20Poly1305,
            keyRole = VaultCryptoKeyRole.SecretPayloadEncryptionKey,
            plaintextRef = VaultCryptoMaterialRef.publicKatVector(),
            context = context,
            noncePolicy = EncryptedVaultNoncePolicy.XChaCha20Random24ByteRecordNonce,
            nonceMode = VaultCryptoNonceMode.ProviderKatFixedNonceOnly,
        )

    private fun decryptRequest(): VaultCryptoAeadDecryptRequest =
        VaultCryptoAeadDecryptRequest(
            algorithm = EncryptedVaultAeadAlgorithm.XChaCha20Poly1305,
            keyRole = VaultCryptoKeyRole.SecretPayloadEncryptionKey,
            ciphertextRef = VaultCryptoMaterialRef.publicKatVector(),
            context = context,
            noncePolicy = EncryptedVaultNoncePolicy.XChaCha20Random24ByteRecordNonce,
        )

    private fun keyGenerationRequest(): VaultCryptoKeyGenerationRequest =
        VaultCryptoKeyGenerationRequest(
            keyRole = VaultCryptoKeyRole.MetadataEncryptionKey,
            context = context,
        )

    private fun keysetStorageRequest(): VaultCryptoKeysetStorageRequest =
        VaultCryptoKeysetStorageRequest(
            keyRole = VaultCryptoKeyRole.SecretPayloadEncryptionKey,
            keyMaterialRef = VaultCryptoMaterialRef.futureKeyMaterial(),
            context = context,
        )

    private fun katRequest(): VaultCryptoProviderKatRequest =
        VaultCryptoProviderKatRequest(
            requirement = commonDisabledVaultCryptoProviderStatus().katRequirements.first(),
            context = context,
        )

    private fun VaultCryptoProviderResult<*>.assertBlocked(blocker: VaultCryptoProviderBlocker) {
        val blocked = assertIs<VaultCryptoProviderResult.Blocked>(this)
        assertContains(blocked.blockers, blocker)
        assertContains(blocked.blockers, VaultCryptoProviderBlocker.ProviderDisabledByPolicy)
    }
}
