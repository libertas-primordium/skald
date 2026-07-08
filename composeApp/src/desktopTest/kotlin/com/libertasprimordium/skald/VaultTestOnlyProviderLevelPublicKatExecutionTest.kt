package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DesktopTestOnlyVaultCryptoProvider
import com.libertasprimordium.skald.security.DesktopTestOnlyVaultCryptoProviderExecutionMode
import com.libertasprimordium.skald.security.EncryptedVaultAeadAlgorithm
import com.libertasprimordium.skald.security.EncryptedVaultKdfAlgorithm
import com.libertasprimordium.skald.security.EncryptedVaultNoncePolicy
import com.libertasprimordium.skald.security.VaultCryptoAeadDecryptRequest
import com.libertasprimordium.skald.security.VaultCryptoAeadEncryptRequest
import com.libertasprimordium.skald.security.VaultCryptoAssociatedDataContext
import com.libertasprimordium.skald.security.VaultCryptoKeyGenerationRequest
import com.libertasprimordium.skald.security.VaultCryptoKeyRole
import com.libertasprimordium.skald.security.VaultCryptoKeysetStorageRequest
import com.libertasprimordium.skald.security.VaultCryptoKdfRequest
import com.libertasprimordium.skald.security.VaultCryptoMaterialRef
import com.libertasprimordium.skald.security.VaultCryptoNonceMode
import com.libertasprimordium.skald.security.VaultCryptoProviderBlocker
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderKatEvidence
import com.libertasprimordium.skald.security.VaultCryptoProviderKatExecutionScope
import com.libertasprimordium.skald.security.VaultCryptoProviderKatRequest
import com.libertasprimordium.skald.security.VaultCryptoProviderKatVectorId
import com.libertasprimordium.skald.security.VaultCryptoProviderResult
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionDecision
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import com.libertasprimordium.skald.security.VaultCryptoRecordPurpose
import com.libertasprimordium.skald.security.commonProviderKatContract
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class VaultTestOnlyProviderLevelPublicKatExecutionTest {
    private fun provider() =
        DesktopTestOnlyVaultCryptoProvider(
            executionMode = DesktopTestOnlyVaultCryptoProviderExecutionMode.ProviderLevelPublicKatExecution,
        )

    @Test
    fun desktopProviderLevelKdfPublicKatExecutesThroughTestOnlyProvider() {
        val provider = provider()
        val result = provider.validateKat(katRequest(VaultCryptoProviderKatVectorId.Argon2idRfc9106Section53))

        result.assertKatSuccess(VaultCryptoProviderKatVectorId.Argon2idRfc9106Section53)
        assertTrue(provider.providerLevelKatExecutionEnabled)
        assertFalse(provider.providerOperationsEnabled)
        assertTrue(provider.implementationReport.providerLevelKatExecutedInThisBranch)
        assertTrue(provider.implementationReport.kdfExecutedInThisBranch)
        assertTrue(provider.implementationReport.aeadExecutedInThisBranch)
        assertFalse(provider.implementationReport.providerOperationExecutedInThisBranch)
    }

    @Test
    fun desktopProviderLevelAeadPublicKatExecutesThroughTestOnlyProvider() {
        val provider = provider()
        val result = provider.validateKat(
            katRequest(VaultCryptoProviderKatVectorId.XChaCha20Poly1305DraftAppendixA1),
        )

        result.assertKatSuccess(VaultCryptoProviderKatVectorId.XChaCha20Poly1305DraftAppendixA1)
        assertTrue(provider.providerLevelKatExecutionEnabled)
        assertFalse(provider.providerOperationsEnabled)
    }

    @Test
    fun providerLevelPublicKatsUseOnlyPublicNonWalletVectorsAndCreateNoTraceOrStorage() {
        val provider = provider()
        val identity = provider.identity

        assertTrue(identity.publicKatScopeOnly)
        assertTrue(identity.canCoverKdfPublicKat)
        assertTrue(identity.canCoverAeadPublicKat)
        assertFalse(identity.tracePayloadPresent)
        assertFalse(identity.rawKatMaterialExposed)
        assertFalse(identity.providerHandlesExposed)
        assertFalse(identity.vaultPersistencePresent)
        assertFalse(identity.secureStorageSuccessPathPresent)
        assertFalse(identity.secureMetadataSuccessPathPresent)
        assertFalse(identity.productionSyncPresent)
        assertFalse(identity.signingBroadcastingPresent)
        assertFalse(identity.uiPresent)
        assertFalse(identity.endpointPresent)
        assertFalse(identity.mainnetPresent)
    }

    @Test
    fun providerLevelPublicKatsDoNotEnableOperationsSelectionOrProductionSurfaces() {
        val provider = provider()
        val selection = VaultCryptoProviderSelectionRegistry.select()

        provider.deriveKey(kdfRequest()).assertBlocked(VaultCryptoProviderBlocker.KdfExecutionDisabled)
        provider.encryptRecord(encryptRequest()).assertBlocked(VaultCryptoProviderBlocker.AeadEncryptionDisabled)
        provider.decryptRecord(decryptRequest()).assertBlocked(VaultCryptoProviderBlocker.AeadDecryptionDisabled)
        provider.generateKey(keyGenerationRequest()).assertBlocked(VaultCryptoProviderBlocker.KeyGenerationDisabled)
        provider.storeKeyset(keysetStorageRequest()).assertBlocked(VaultCryptoProviderBlocker.KeysetStorageDisabled)
        provider.validateKat(katRequest(VaultCryptoProviderKatVectorId.VaultStorageApprovalSeparated))
            .assertBlocked(VaultCryptoProviderBlocker.VaultContainerUnavailable)

        assertFalse(provider.implementationReport.providerSelectionEnabled)
        assertFalse(provider.implementationReport.productionProviderSelectable)
        assertFalse(provider.implementationReport.productionImplementationPresent)
        assertFalse(provider.implementationReport.providerRegistryEntryPresent)
        assertFalse(provider.implementationReport.providerFactoryPresent)
        assertFalse(provider.implementationReport.providerDispatcherPresent)
        assertFalse(provider.implementationReport.executorTargetPresent)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertEquals(VaultCryptoProviderSelectionDecision.DisabledProviderSelected, selection.decision)
        assertTrue(selection.selectedProviderIsDisabled)
        assertFalse(selection.productionProviderSelectable)
    }

    @Test
    fun providerLevelPublicKatSuccessIsNotProductionOrSelectionAuthorization() {
        val provider = provider()
        val kdf = provider.validateKat(katRequest(VaultCryptoProviderKatVectorId.Argon2idRfc9106Section53))
        val aead = provider.validateKat(katRequest(VaultCryptoProviderKatVectorId.XChaCha20Poly1305DraftAppendixA1))

        kdf.assertKatSuccess(VaultCryptoProviderKatVectorId.Argon2idRfc9106Section53)
        aead.assertKatSuccess(VaultCryptoProviderKatVectorId.XChaCha20Poly1305DraftAppendixA1)
        assertFalse(provider.identity.providerSelectionEnabled)
        assertFalse(provider.identity.productionProviderSelectable)
        assertFalse(provider.identity.productionImplementationPresent)
        assertTrue(provider.identity.futureProviderSelectionEnablementRequiresSeparatePass)
    }

    @Test
    fun providerLevelPublicKatOutputIsRedactedAndDoesNotExposeVectorMaterial() {
        val provider = provider()
        val success = provider.validateKat(katRequest(VaultCryptoProviderKatVectorId.XChaCha20Poly1305DraftAppendixA1))
        val blocked = provider.validateKat(katRequest(VaultCryptoProviderKatVectorId.VaultStorageApprovalSeparated))
        val successEvidence = assertIs<VaultCryptoProviderResult.Success<VaultCryptoProviderKatEvidence>>(success).value
        val blockedResult = assertIs<VaultCryptoProviderResult.Blocked>(blocked)
        val output = listOf(
            provider.toString(),
            provider.implementationReport.toString(),
            success.toString(),
            successEvidence.toString(),
            blocked.toString(),
            blockedResult.error.toString(),
            blockedResult.error.diagnostic.toString(),
        ).joinToString(separator = " ")
        val forbiddenText = listOf(
            "0d640df5",
            "bd6d179d",
            "80818283",
            "40414243",
            "4c616469",
            "ciphertext",
            "plaintext",
            "nonce",
            "tag",
            "provider handle",
            "source location",
            "stack trace",
            "diagnostics payload",
            "analytics payload",
            "crash-report payload",
            "support-export payload",
            "endpoint value",
            "filesystem path",
            "wpkh(",
            "tr(",
            "xpub",
            "xprv",
            "tprv",
            "psbt",
            "nsec",
        )

        assertContains(output, "REDACTED")
        assertContains(output, "TEST_SOURCE_ONLY")
        assertContains(output, "PROVIDER_LEVEL_PUBLIC_KAT_EXECUTION_ONLY")
        assertContains(output, "NO_PROVIDER_SELECTION")
        assertContains(output, "NO_PRODUCTION_PROVIDER")
        forbiddenText.forEach { forbidden ->
            assertFalse(output.contains(forbidden, ignoreCase = true), "Output leaked forbidden text: $forbidden")
        }
        assertFalse(Regex("\\b[0-9a-fA-F]{64}\\b").containsMatchIn(output))
        assertFalse(Regex("\\b(?:bc1|tb1|bcrt1)[a-z0-9]{20,}\\b", RegexOption.IGNORE_CASE).containsMatchIn(output))
    }

    private fun katRequest(vectorId: VaultCryptoProviderKatVectorId): VaultCryptoProviderKatRequest =
        VaultCryptoProviderKatRequest(
            requirement = commonProviderKatContract().requirements.single { it.vectorId == vectorId },
            context = context,
        )

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

    private fun VaultCryptoProviderResult<VaultCryptoProviderKatEvidence>.assertKatSuccess(
        vectorId: VaultCryptoProviderKatVectorId,
    ): VaultCryptoProviderKatEvidence {
        val success = assertIs<VaultCryptoProviderResult.Success<VaultCryptoProviderKatEvidence>>(this)
        assertEquals(vectorId, success.value.vectorId)
        assertEquals(VaultCryptoProviderKatExecutionScope.TestHarnessOnly, success.value.executionScope)
        return success.value
    }

    private fun VaultCryptoProviderResult<*>.assertBlocked(blocker: VaultCryptoProviderBlocker) {
        val blocked = assertIs<VaultCryptoProviderResult.Blocked>(this)
        assertContains(blocked.blockers, blocker)
        assertContains(blocked.blockers, VaultCryptoProviderBlocker.ProviderDisabledByPolicy)
    }

    private companion object {
        val context = VaultCryptoAssociatedDataContext(
            containerVersion = 1,
            recordPurpose = VaultCryptoRecordPurpose.ProviderKatTestRecord,
            recordSchemaVersion = 1,
            keyVersion = 1,
        )
    }
}
