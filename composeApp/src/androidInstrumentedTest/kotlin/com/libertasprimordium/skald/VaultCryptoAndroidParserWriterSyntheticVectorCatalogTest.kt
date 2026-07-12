package com.libertasprimordium.skald

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.libertasprimordium.skald.security.DisabledEncryptedVaultParserScaffold
import com.libertasprimordium.skald.security.DisabledEncryptedVaultWriterScaffold
import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.EncryptedVaultParserRequest
import com.libertasprimordium.skald.security.EncryptedVaultParserWriterScaffoldPolicy
import com.libertasprimordium.skald.security.EncryptedVaultParserWriterScaffoldStatus
import com.libertasprimordium.skald.security.EncryptedVaultWriterRequest
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionDecision
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VaultCryptoAndroidParserWriterSyntheticVectorCatalogTest {
    @Test
    fun androidTestSourceSyntheticBytesAreRejectedByDisabledScaffoldsWithoutExposure() {
        val androidTestSourceOnlyMarkers = listOf("skv-android-a", "skv-android-b")

        androidTestSourceOnlyMarkers.forEach { marker ->
            val bytes = marker.encodeToByteArray()
            val parserRequest = EncryptedVaultParserRequest(
                declaredInputByteCount = bytes.size,
                testSourceSyntheticVectorBytesPresent = true,
            )
            val writerRequest = EncryptedVaultWriterRequest(
                requestedOutputByteCount = bytes.size,
                testSourceSyntheticVectorBytesPresent = true,
            )
            val parserResult = DisabledEncryptedVaultParserScaffold.parse(parserRequest)
            val writerResult = DisabledEncryptedVaultWriterScaffold.write(writerRequest)
            val rendered = listOf(
                parserRequest.toString(),
                writerRequest.toString(),
                parserResult.toString(),
                parserResult.diagnostics.toString(),
                writerResult.toString(),
                writerResult.diagnostics.toString(),
            ).joinToString(separator = " ")

            assertTrue(bytes.isNotEmpty())
            assertTrue(bytes.size <= 16)
            assertTrue(parserRequest.testSourceSyntheticVectorBytesPresent)
            assertTrue(writerRequest.testSourceSyntheticVectorBytesPresent)
            assertFalse(parserRequest.productionParserInputBytesPresent)
            assertFalse(writerRequest.productionWriterOutputBytesPresent)
            assertEquals(EncryptedVaultParserWriterScaffoldStatus.RejectedByScaffold, parserResult.status)
            assertFalse(parserResult.accepted)
            assertEquals(0, parserResult.consumedByteCount)
            assertEquals(0, parserResult.producedByteCount)
            assertTrue(parserResult.diagnostics.safeLabelsOnly)
            assertTrue(parserResult.diagnostics.payloadFree)
            assertEquals(EncryptedVaultParserWriterScaffoldStatus.RejectedByScaffold, writerResult.status)
            assertFalse(writerResult.accepted)
            assertEquals(0, writerResult.consumedByteCount)
            assertEquals(0, writerResult.producedByteCount)
            assertTrue(writerResult.diagnostics.safeLabelsOnly)
            assertTrue(writerResult.diagnostics.payloadFree)
            assertFalse(rendered.contains(marker))
            assertFalse(rendered.contains(bytes.decodeToString()))
        }
    }

    @Test
    fun androidSyntheticVectorRejectionDoesNotAuthorizeProductionSurfaces() {
        val scaffold = EncryptedVaultParserWriterScaffoldPolicy.currentParserWriterScaffold()
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertFalse(scaffold.workingParserImplementationPresent)
        assertFalse(scaffold.workingWriterImplementationPresent)
        assertFalse(scaffold.productionVectorBytesPresent)
        assertFalse(scaffold.productionParserInputBytesPresent)
        assertFalse(scaffold.productionWriterOutputBytesPresent)
        assertFalse(scaffold.parserVectorExecutionPresent)
        assertFalse(scaffold.writerVectorExecutionPresent)
        assertFalse(scaffold.vaultContainerSerializationPresent)
        assertFalse(scaffold.vaultContainerParsingPresent)
        assertFalse(scaffold.vaultContainerBytesProduced)
        assertFalse(scaffold.vaultContainerBytesConsumed)
        assertFalse(scaffold.vaultFileReadPresent)
        assertFalse(scaffold.vaultFileWritePresent)
        assertFalse(scaffold.vaultFileDeletePresent)
        assertFalse(scaffold.vaultDirectoryCreated)
        assertFalse(scaffold.kdfExecutionPresent)
        assertFalse(scaffold.aeadExecutionPresent)
        assertFalse(scaffold.encryptionExecutionPresent)
        assertFalse(scaffold.decryptionExecutionPresent)
        assertFalse(scaffold.keyGenerationPresent)
        assertFalse(scaffold.nonceGenerationPresent)
        assertFalse(scaffold.tinkKeysetCreationPresent)
        assertFalse(scaffold.tinkKeysetPersistencePresent)
        assertFalse(scaffold.lockSessionImplementationPresent)
        assertFalse(scaffold.unlockImplementationPresent)
        assertFalse(scaffold.runtimeSessionKeyPresent)
        assertFalse(scaffold.secureSecretStorageSuccessPathPresent)
        assertFalse(scaffold.secureMetadataStorageSuccessPathPresent)
        assertFalse(scaffold.productionObservationPersistencePresent)
        assertFalse(scaffold.productionAddressIndexPersistencePresent)
        assertFalse(scaffold.productionUtxoPersistencePresent)
        assertFalse(scaffold.productionWalletHistoryPersistencePresent)
        assertFalse(scaffold.productionSyncPresent)
        assertFalse(scaffold.productionBackendClientPresent)
        assertFalse(scaffold.productionProviderSelectionEnabled)
        assertFalse(scaffold.productionProviderSelectable)
        assertTrue(scaffold.productionSelectionStillDisabledProviderOnly)
        assertFalse(scaffold.signingBroadcastingPresent)
        assertFalse(scaffold.uiActionEnablementPresent)
        assertFalse(scaffold.endpointPresent)
        assertFalse(scaffold.mainnetPresent)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertEquals(VaultCryptoProviderSelectionDecision.DisabledProviderSelected, selection.decision)
        assertTrue(selection.selectedProviderIsDisabled)
        assertTrue(selection.selectedProvider is DisabledVaultCryptoProvider)
        assertFalse(selection.productionProviderSelectable)
    }
}
