package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledEncryptedVaultParserScaffold
import com.libertasprimordium.skald.security.DisabledEncryptedVaultWriterScaffold
import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.EncryptedVaultParserWriterScaffoldStatus
import com.libertasprimordium.skald.security.EncryptedVaultParserWriterSyntheticVectorCatalog
import com.libertasprimordium.skald.security.EncryptedVaultParserWriterSyntheticVectorCatalogClass
import com.libertasprimordium.skald.security.EncryptedVaultParserWriterSyntheticVectorCatalogKind
import com.libertasprimordium.skald.security.EncryptedVaultParserWriterSyntheticVectorCatalogSourceSet
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionDecision
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class EncryptedVaultParserWriterSyntheticVectorCatalogTest {
    private fun report() =
        EncryptedVaultParserWriterSyntheticVectorCatalog.currentCatalogReport()

    private fun fixtures() =
        EncryptedVaultParserWriterSyntheticVectorCatalog.fixtures

    @Test
    fun syntheticVectorCatalogExistsAsTestSourceOnlyCatalog() {
        val report = report()

        assertEquals(1, report.vectorCatalogVersion)
        assertEquals(
            EncryptedVaultParserWriterSyntheticVectorCatalogKind
                .EncryptedLocalVaultParserWriterSyntheticVectorCatalog,
            report.vectorCatalogKind,
        )
        assertEquals(
            "ENCRYPTED_LOCAL_VAULT_PARSER_WRITER_SYNTHETIC_VECTOR_CATALOG",
            report.vectorCatalogKind.label,
        )
        assertEquals(EncryptedVaultParserWriterSyntheticVectorCatalogSourceSet.TestSourceOnly, report.sourceSet)
        assertEquals("TEST_SOURCE_ONLY", report.sourceSet.label)
        assertTrue(report.syntheticVectorCatalogPresent)
        assertTrue(report.syntheticVectorBytesPresent)
        assertTrue(report.testSourceSyntheticVectorBytesPresent)
        assertFalse(report.productionVectorBytesPresent)
        assertFalse(report.productionParserInputBytesPresent)
        assertFalse(report.productionWriterOutputBytesPresent)
        assertEquals(EncryptedVaultParserWriterSyntheticVectorCatalogClass.entries.size, report.vectorClassCount)
        assertEquals(report.vectorClassCount, report.syntheticByteFixtureCount)
        assertEquals(0, report.blockerCount)
        assertEquals(0, report.warningCount)
    }

    @Test
    fun syntheticVectorCoverageIncludesEveryRequiredMetadataClass() {
        val report = report()

        assertEquals(
            EncryptedVaultParserWriterSyntheticVectorCatalogClass.entries.toSet(),
            report.vectorClasses.toSet(),
        )
        assertEquals(report.vectorClassCount, report.vectorClasses.distinct().size)
        EncryptedVaultParserWriterSyntheticVectorCatalogClass.entries.forEach { vectorClass ->
            assertContains(report.vectorClasses, vectorClass)
            assertTrue(vectorClass.safeLabel.isNotBlank())
        }
    }

    @Test
    fun syntheticVectorBytesAreShortNonWalletNonSecretNonNetworkAndTestOnly() {
        fixtures().forEach { fixture ->
            val bytes = fixture.bytesForDisabledScaffoldRequestOnly()
            val marker = fixture.markerForTestSourceConfinementAssertionOnly()

            assertEquals(fixture.byteCount, bytes.size)
            assertTrue(bytes.isNotEmpty())
            assertTrue(bytes.size <= 16, "Synthetic byte fixtures must stay short.")
            assertTrue(bytes.all { byte -> byte.toInt() in 0x20..0x7E })
            assertTrue(fixture.syntheticTestOnly)
            assertTrue(fixture.shortSyntheticBytes)
            assertTrue(fixture.nonWallet)
            assertTrue(fixture.nonSecret)
            assertTrue(fixture.nonNetwork)
            assertFalse(fixture.validBitcoinData)
            assertFalse(fixture.realVaultData)
            assertFalse(fixture.productionSerializedVaultBytes)
            assertFalse(fixture.logged)
            assertFalse(fixture.displayed)
            assertFalse(fixture.persisted)
            assertFalse(fixture.usedByWorkingParserOrWriter)
            assertEquals(EncryptedVaultParserWriterSyntheticVectorCatalogSourceSet.TestSourceOnly, fixture.sourceSet)
            assertSafeSyntheticMarker(marker)
        }
    }

    @Test
    fun disabledParserRejectsSyntheticBytesWithoutConsumingOrExposingBytes() {
        fixtures().forEach { fixture ->
            val marker = fixture.markerForTestSourceConfinementAssertionOnly()
            val request = fixture.parserRequestForDisabledScaffold()
            val result = DisabledEncryptedVaultParserScaffold.parse(request)
            val rendered = listOf(request.toString(), result.toString(), result.diagnostics.toString())
                .joinToString(separator = " ")

            assertTrue(request.testSourceSyntheticVectorBytesPresent)
            assertFalse(request.productionParserInputBytesPresent)
            assertEquals(fixture.byteCount, request.declaredInputByteCount)
            assertEquals(EncryptedVaultParserWriterScaffoldStatus.RejectedByScaffold, result.status)
            assertFalse(result.accepted)
            assertEquals(0, result.consumedByteCount)
            assertEquals(0, result.producedByteCount)
            assertEquals(0, result.diagnostics.consumedByteCount)
            assertEquals(0, result.diagnostics.producedByteCount)
            assertTrue(result.diagnostics.safeLabelsOnly)
            assertTrue(result.diagnostics.payloadFree)
            assertFalse(rendered.contains(marker), "Disabled parser output must not display synthetic bytes.")
            assertFalse(rendered.contains(bytesAsDisplayProbe(fixture)), "Disabled parser output must not display bytes.")
        }

        assertTrue(report().disabledParserRejectedSyntheticBytes)
    }

    @Test
    fun disabledWriterRejectsSyntheticRequestsWithoutProducingOrExposingBytes() {
        fixtures().forEach { fixture ->
            val marker = fixture.markerForTestSourceConfinementAssertionOnly()
            val request = fixture.writerRequestForDisabledScaffold()
            val result = DisabledEncryptedVaultWriterScaffold.write(request)
            val rendered = listOf(request.toString(), result.toString(), result.diagnostics.toString())
                .joinToString(separator = " ")

            assertTrue(request.testSourceSyntheticVectorBytesPresent)
            assertFalse(request.productionWriterOutputBytesPresent)
            assertEquals(fixture.byteCount, request.requestedOutputByteCount)
            assertEquals(EncryptedVaultParserWriterScaffoldStatus.RejectedByScaffold, result.status)
            assertFalse(result.accepted)
            assertEquals(0, result.consumedByteCount)
            assertEquals(0, result.producedByteCount)
            assertEquals(0, result.diagnostics.consumedByteCount)
            assertEquals(0, result.diagnostics.producedByteCount)
            assertTrue(result.diagnostics.safeLabelsOnly)
            assertTrue(result.diagnostics.payloadFree)
            assertFalse(rendered.contains(marker), "Disabled writer output must not display synthetic bytes.")
            assertFalse(rendered.contains(bytesAsDisplayProbe(fixture)), "Disabled writer output must not display bytes.")
        }

        assertTrue(report().disabledWriterRejectedWithoutProducingBytes)
    }

    @Test
    fun syntheticCatalogDoesNotEnableRuntimeParserWriterStorageCryptoSyncOrMainnet() {
        val report = report()

        assertFalse(report.parserVectorExecutionPresent)
        assertFalse(report.writerVectorExecutionPresent)
        assertFalse(report.roundTripVectorExecutionPresent)
        assertFalse(report.negativeVectorExecutionPresent)
        assertFalse(report.workingParserImplementationPresent)
        assertFalse(report.workingWriterImplementationPresent)
        assertFalse(report.vaultContainerSerializationPresent)
        assertFalse(report.vaultContainerParsingPresent)
        assertFalse(report.vaultContainerBytesProduced)
        assertFalse(report.vaultContainerBytesConsumed)
        assertFalse(report.vaultHeaderSerialized)
        assertFalse(report.vaultHeaderParsed)
        assertFalse(report.vaultRecordDirectorySerialized)
        assertFalse(report.vaultRecordDirectoryParsed)
        assertFalse(report.vaultRecordEnvelopeSerialized)
        assertFalse(report.vaultRecordEnvelopeParsed)
        assertFalse(report.vaultFileReadPresent)
        assertFalse(report.vaultFileWritePresent)
        assertFalse(report.vaultFileDeletePresent)
        assertFalse(report.vaultDirectoryCreated)
        assertFalse(report.atomicReplaceImplementationPresent)
        assertFalse(report.partialWriteDetectionImplementationPresent)
        assertFalse(report.migrationImplementationPresent)
        assertFalse(report.migrationExecutionPresent)
        assertFalse(report.corruptionDetectionImplementationPresent)
        assertFalse(report.corruptionRepairImplementationPresent)
        assertFalse(report.backupCreationPresent)
        assertFalse(report.rollbackImplementationPresent)
        assertFalse(report.kdfExecutionPresent)
        assertFalse(report.aeadExecutionPresent)
        assertFalse(report.encryptionExecutionPresent)
        assertFalse(report.decryptionExecutionPresent)
        assertFalse(report.keyGenerationPresent)
        assertFalse(report.nonceGenerationPresent)
        assertFalse(report.tinkKeysetCreationPresent)
        assertFalse(report.tinkKeysetPersistencePresent)
        assertFalse(report.vaultStoragePathImplementationPresent)
        assertFalse(report.encryptedVaultFileFormatImplemented)
        assertFalse(report.encryptedVaultRepositorySuccessPresent)
        assertFalse(report.lockSessionImplementationPresent)
        assertFalse(report.unlockImplementationPresent)
        assertFalse(report.runtimeSessionKeyPresent)
        assertFalse(report.sessionKeyCached)
        assertFalse(report.plaintextCachePresent)
        assertFalse(report.secureSecretStorageSuccessPathPresent)
        assertFalse(report.secureMetadataStorageSuccessPathPresent)
        assertFalse(report.productionObservationPersistencePresent)
        assertFalse(report.productionAddressIndexPersistencePresent)
        assertFalse(report.productionUtxoPersistencePresent)
        assertFalse(report.productionWalletHistoryPersistencePresent)
        assertFalse(report.productionSyncPresent)
        assertFalse(report.productionBackendClientPresent)
        assertFalse(report.productionProviderSelectionEnabled)
        assertFalse(report.productionProviderSelectable)
        assertTrue(report.productionSelectionStillDisabledProviderOnly)
        assertFalse(report.signingBroadcastingPresent)
        assertFalse(report.uiActionEnablementPresent)
        assertFalse(report.endpointPresent)
        assertFalse(report.mainnetPresent)
    }

    @Test
    fun syntheticCatalogHasNoProductionImplementationAuthorizations() {
        val report = report()

        assertFalse(report.parserImplementationAuthorizationPresent)
        assertFalse(report.writerImplementationAuthorizationPresent)
        assertFalse(report.productionVectorCreationAuthorizationPresent)
        assertFalse(report.parserVectorExecutionAuthorizationPresent)
        assertFalse(report.writerVectorExecutionAuthorizationPresent)
        assertFalse(report.vaultContainerSerializationAuthorizationPresent)
        assertFalse(report.vaultContainerParsingAuthorizationPresent)
        assertFalse(report.vaultFileReadAuthorizationPresent)
        assertFalse(report.vaultFileWriteAuthorizationPresent)
        assertFalse(report.vaultFileDeleteAuthorizationPresent)
        assertFalse(report.vaultDirectoryCreationAuthorizationPresent)
        assertFalse(report.kdfExecutionAuthorizationPresent)
        assertFalse(report.aeadExecutionAuthorizationPresent)
        assertFalse(report.encryptionAuthorizationPresent)
        assertFalse(report.decryptionAuthorizationPresent)
        assertFalse(report.keyGenerationAuthorizationPresent)
        assertFalse(report.nonceGenerationAuthorizationPresent)
        assertFalse(report.tinkKeysetCreationAuthorizationPresent)
        assertFalse(report.tinkKeysetPersistenceAuthorizationPresent)
        assertFalse(report.migrationImplementationAuthorizationPresent)
        assertFalse(report.corruptionDetectionAuthorizationPresent)
        assertFalse(report.corruptionRepairAuthorizationPresent)
        assertFalse(report.backupCreationAuthorizationPresent)
        assertFalse(report.rollbackAuthorizationPresent)
        assertFalse(report.atomicReplaceAuthorizationPresent)
        assertFalse(report.partialWriteDetectionAuthorizationPresent)
        assertFalse(report.productionStorageAuthorizationPresent)
        assertFalse(report.productionSecretStorageAuthorizationPresent)
        assertFalse(report.productionMetadataStorageAuthorizationPresent)
        assertFalse(report.productionSyncAuthorizationPresent)
        assertFalse(report.productionProviderSelectionAuthorizationPresent)
        assertFalse(report.productionProviderImplementationAuthorizationPresent)
        assertFalse(report.signingBroadcastingAuthorizationPresent)
        assertFalse(report.uiAuthorizationPresent)
        assertFalse(report.endpointAuthorizationPresent)
        assertFalse(report.mainnetAuthorizationPresent)
        assertFalse(report.vaultSessionImplementationAuthorizationPresent)
        assertFalse(report.vaultUnlockAuthorizationPresent)
        assertFalse(report.vaultLockAuthorizationPresent)
    }

    @Test
    fun productionProviderSelectionStillResolvesOnlyToDisabledProvider() {
        val report = report()
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertTrue(selection.selectedProviderIsDisabled)
        assertTrue(selection.selectedProvider is DisabledVaultCryptoProvider)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertEquals(VaultCryptoProviderSelectionDecision.DisabledProviderSelected, selection.decision)
        assertFalse(selection.productionProviderSelectable)
        assertFalse(report.productionProviderSelectable)
        assertFalse(report.productionProviderSelectionEnabled)
        assertTrue(report.productionSelectionStillDisabledProviderOnly)
    }

    @Test
    fun displayAndDebugOutputAreRedactedSafeLabelOnlyAndDoNotExposeSyntheticBytes() {
        val report = report()
        val rendered = buildList {
            add(report.toString())
            fixtures().forEach { fixture ->
                add(fixture.toString())
                add(fixture.parserRequestForDisabledScaffold().toString())
                add(fixture.writerRequestForDisabledScaffold().toString())
                add(DisabledEncryptedVaultParserScaffold.parse(fixture.parserRequestForDisabledScaffold()).toString())
                add(DisabledEncryptedVaultWriterScaffold.write(fixture.writerRequestForDisabledScaffold()).toString())
            }
        }.joinToString(separator = " | ")
        val lowerRendered = rendered.lowercase()

        assertContains(rendered, "REDACTED")
        assertContains(rendered, "TEST_SOURCE_ONLY")
        assertContains(rendered, "NO_PRODUCTION_BYTES")
        assertContains(rendered, "NO_PARSER_WRITER")
        assertContains(rendered, "NO_IO")
        fixtures().forEach { fixture ->
            assertFalse(
                rendered.contains(fixture.markerForTestSourceConfinementAssertionOnly()),
                "Display/debug output must not expose synthetic bytes.",
            )
            assertFalse(
                rendered.contains(bytesAsDisplayProbe(fixture)),
                "Display/debug output must not expose synthetic bytes.",
            )
        }
        forbiddenOutputText.forEach { forbidden ->
            assertFalse(
                forbidden in lowerRendered,
                "Synthetic catalog display output must not contain $forbidden: $rendered",
            )
        }
        assertFalse(Regex("\\b[0-9a-fA-F]{64}\\b").containsMatchIn(rendered))
        assertFalse(
            Regex("\\b(?:bc1|tb1|bcrt1)[a-z0-9]{20,}\\b", RegexOption.IGNORE_CASE)
                .containsMatchIn(rendered),
        )
        assertFalse(report.syntheticBytesLogged)
        assertFalse(report.syntheticBytesDisplayed)
        assertFalse(report.syntheticBytesPersisted)
        assertFalse(report.syntheticBytesInDocs)
        assertFalse(report.syntheticBytesInBuildHistory)
    }

    @Test
    fun futureWorkStillRequiresSeparateBranches() {
        val report = report()

        assertTrue(report.futureParserImplementationRequiresSeparatePass)
        assertTrue(report.futureWriterImplementationRequiresSeparatePass)
        assertTrue(report.futureParserWriterVectorExecutionRequiresSeparatePass)
        assertTrue(report.futureVaultStorageRepositoryRequiresSeparatePass)
        assertTrue(report.futureSecureStorageSuccessRequiresSeparatePass)
        assertTrue(report.futureSecureMetadataSuccessRequiresSeparatePass)
        assertTrue(report.futureProductionSyncRequiresSeparatePass)
        assertTrue(report.futureProductionProviderSelectionRequiresSeparatePass)
    }

    private fun bytesAsDisplayProbe(
        fixture: com.libertasprimordium.skald.security.EncryptedVaultParserWriterSyntheticVector,
    ): String =
        fixture.bytesForDisabledScaffoldRequestOnly().decodeToString()

    private fun assertSafeSyntheticMarker(marker: String) {
        val lower = marker.lowercase()

        assertTrue(marker.isNotBlank())
        assertFalse('/' in marker, "Synthetic marker must not look like path material.")
        assertFalse('\\' in marker, "Synthetic marker must not look like path material.")
        assertFalse('.' in marker, "Synthetic marker must not look like a file or endpoint.")
        assertFalse('~' in marker, "Synthetic marker must not look like path material.")
        assertFalse(':' in marker, "Synthetic marker must not look like endpoint or drive material.")
        assertFalse(Regex("\\b[0-9a-fA-F]{64}\\b").containsMatchIn(marker))
        assertFalse(Regex("\\b(?:bc1|tb1|bcrt1)[a-z0-9]{20,}\\b", RegexOption.IGNORE_CASE).containsMatchIn(marker))
        forbiddenMarkerText.forEach { forbidden ->
            assertFalse(forbidden in lower, "Synthetic marker contains forbidden material label: $forbidden")
        }
    }

    private companion object {
        val forbiddenMarkerText = listOf(
            "nsec",
            "psbt",
            "xprv",
            "tprv",
            "wif",
            "seed",
            "mnemonic",
            "private",
            "secret",
            "credential",
            "descriptor",
            "address",
            "txid",
            "transaction",
            "cashu",
            "lightning",
            "macaroon",
            "backend",
            "http",
            "onion",
            "source",
            "stack",
            "support",
        )

        val forbiddenOutputText = listOf(
            "public vector bytes",
            "public vector hex",
            "synthetic vector bytes",
            "key material",
            "salts",
            "nonces",
            "plaintext",
            "ciphertext",
            "tags",
            "macs",
            "hashes",
            "trace payloads",
            "provider handles",
            "source locations",
            "stack traces",
            "diagnostics payloads",
            "analytics payloads",
            "crash-report payloads",
            "support-export payloads",
            "endpoint values",
            "filesystem paths",
            "txids",
            "descriptors",
            "addresses",
            "psbts",
            "transaction hex",
            "nostr nsecs",
            "lightning credentials",
            "cashu proofs",
            "backend credentials",
            "seeds",
            "mnemonics",
            "private keys",
            "xprvs",
            "tprvs",
            "wifs",
            "wallet database material",
            "directory names",
            "file names",
            "storage keys",
            "migration payloads",
            "corruption hashes",
            "repair diagnostics",
            "parser inputs",
            "writer outputs",
            "serialized vault bytes",
        )
    }
}
