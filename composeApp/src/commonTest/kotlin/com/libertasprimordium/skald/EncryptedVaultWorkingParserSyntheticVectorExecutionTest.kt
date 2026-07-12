package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.EncryptedVaultParserWriterScaffoldPolicy
import com.libertasprimordium.skald.security.EncryptedVaultParserWriterSyntheticVectorCatalog
import com.libertasprimordium.skald.security.EncryptedVaultParserWriterSyntheticVectorCatalogClass
import com.libertasprimordium.skald.security.EncryptedVaultWorkingParser
import com.libertasprimordium.skald.security.EncryptedVaultWorkingParserBlocker
import com.libertasprimordium.skald.security.EncryptedVaultWorkingParserKind
import com.libertasprimordium.skald.security.EncryptedVaultWorkingParserPolicyLabel
import com.libertasprimordium.skald.security.EncryptedVaultWorkingParserRequest
import com.libertasprimordium.skald.security.EncryptedVaultWorkingParserSourceSet
import com.libertasprimordium.skald.security.EncryptedVaultWorkingParserStatus
import com.libertasprimordium.skald.security.EncryptedVaultWorkingParserSyntheticClassification
import com.libertasprimordium.skald.security.EncryptedVaultWorkingParserAdmissionGatePolicy
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionDecision
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class EncryptedVaultWorkingParserSyntheticVectorExecutionTest {
    private fun evidence() =
        EncryptedVaultWorkingParser.currentParserEvidence()

    private fun fixtures() =
        EncryptedVaultParserWriterSyntheticVectorCatalog.fixtures

    @Test
    fun workingParserExistsAsCommonMainInMemorySyntheticVectorParser() {
        val evidence = evidence()

        assertEquals(1, evidence.parserVersion)
        assertEquals(
            EncryptedVaultWorkingParserKind.EncryptedLocalVaultWorkingParserSyntheticVectorExecution,
            evidence.parserKind,
        )
        assertEquals(
            "ENCRYPTED_LOCAL_VAULT_WORKING_PARSER_SYNTHETIC_VECTOR_EXECUTION",
            evidence.parserKind.label,
        )
        assertEquals(EncryptedVaultWorkingParserSourceSet.CommonMainInMemoryParser, evidence.sourceSet)
        assertEquals("COMMON_MAIN_IN_MEMORY_PARSER", evidence.sourceSet.label)
        assertTrue(evidence.workingParserImplementationPresent)
        assertTrue(evidence.commonMainInMemoryParserPresent)
        assertTrue(evidence.syntheticVectorParserExecutionSupported)
        assertTrue(evidence.parserFailClosedPolicyPresent)
        assertTrue(evidence.parserRedactedResultPolicyPresent)
        assertTrue(evidence.parserErrorTaxonomyPresent)
        assertEquals(EncryptedVaultWorkingParserPolicyLabel.entries.toSet(), evidence.policyLabels.toSet())
        assertEquals(EncryptedVaultWorkingParserBlocker.entries.toSet(), evidence.parserBlockers.toSet())
        assertEquals(5, evidence.evidenceCount)
        assertEquals(0, evidence.blockerCount)
        assertEquals(0, evidence.warningCount)
    }

    @Test
    fun workingParserReflectsPriorAdmissionCatalogScaffoldAndDecisionEvidence() {
        val evidence = evidence()
        val admission = EncryptedVaultWorkingParserAdmissionGatePolicy.currentWorkingParserAdmissionGate()
        val scaffold = EncryptedVaultParserWriterScaffoldPolicy.currentParserWriterScaffold()
        val catalog = EncryptedVaultParserWriterSyntheticVectorCatalog.currentCatalogReport()

        assertTrue(admission.workingParserAdmissionGatePassed)
        assertTrue(scaffold.parserWriterScaffoldDecisionPassed)
        assertTrue(catalog.syntheticVectorCatalogPresent)
        assertTrue(evidence.workingParserAdmissionEvidencePresent)
        assertTrue(evidence.parserWriterSyntheticVectorCatalogEvidencePresent)
        assertTrue(evidence.parserWriterImplementationScaffoldEvidencePresent)
        assertTrue(evidence.containerFormatV1DecisionEvidencePresent)
        assertTrue(evidence.migrationCorruptionPolicyEvidencePresent)
    }

    @Test
    fun parserClassifiesAllPositiveSyntheticVectorsSafely() {
        assertClassifies(
            EncryptedVaultParserWriterSyntheticVectorCatalogClass.MinimalHeaderOnlySyntheticVector,
            EncryptedVaultWorkingParserSyntheticClassification.SyntheticHeaderOnly,
            expectedSectionCount = 1,
        )
        assertClassifies(
            EncryptedVaultParserWriterSyntheticVectorCatalogClass.HeaderAndKdfSectionSyntheticVector,
            EncryptedVaultWorkingParserSyntheticClassification.SyntheticKdfSection,
            expectedSectionCount = 2,
        )
        assertClassifies(
            EncryptedVaultParserWriterSyntheticVectorCatalogClass.HeaderKeyEnvelopeDirectorySyntheticVector,
            EncryptedVaultWorkingParserSyntheticClassification.SyntheticDirectory,
            expectedSectionCount = 3,
        )
        assertClassifies(
            EncryptedVaultParserWriterSyntheticVectorCatalogClass.SingleRecordEnvelopeSyntheticVector,
            EncryptedVaultWorkingParserSyntheticClassification.SyntheticSingleRecordEnvelope,
            expectedSectionCount = 4,
        )
        assertClassifies(
            EncryptedVaultParserWriterSyntheticVectorCatalogClass.MultiRecordDirectorySyntheticVector,
            EncryptedVaultWorkingParserSyntheticClassification.SyntheticMultiRecordDirectory,
            expectedSectionCount = 5,
        )
    }

    @Test
    fun parserFailsClosedForAllNegativeSyntheticVectors() {
        assertFailsClosed(
            EncryptedVaultParserWriterSyntheticVectorCatalogClass.UnsupportedVersionSyntheticVector,
            EncryptedVaultWorkingParserBlocker.UnsupportedVersion,
        )
        assertFailsClosed(
            EncryptedVaultParserWriterSyntheticVectorCatalogClass.UnknownCriticalFeatureSyntheticVector,
            EncryptedVaultWorkingParserBlocker.UnsupportedCriticalFeature,
        )
        assertFailsClosed(
            EncryptedVaultParserWriterSyntheticVectorCatalogClass.TruncatedHeaderSyntheticVector,
            EncryptedVaultWorkingParserBlocker.TruncatedHeader,
        )
        assertFailsClosed(
            EncryptedVaultParserWriterSyntheticVectorCatalogClass.TruncatedRecordEnvelopeSyntheticVector,
            EncryptedVaultWorkingParserBlocker.TruncatedRecordEnvelope,
        )
        assertFailsClosed(
            EncryptedVaultParserWriterSyntheticVectorCatalogClass.RedactedDiagnosticsSyntheticVector,
            EncryptedVaultWorkingParserBlocker.RedactedDiagnosticsOnly,
            EncryptedVaultWorkingParserSyntheticClassification.RedactedDiagnosticsOnly,
        )
        assertFailsClosed(
            EncryptedVaultParserWriterSyntheticVectorCatalogClass.MigrationRequiredSyntheticVector,
            EncryptedVaultWorkingParserBlocker.MigrationRequired,
        )
        assertFailsClosed(
            EncryptedVaultParserWriterSyntheticVectorCatalogClass.CorruptionSuspectedSyntheticVector,
            EncryptedVaultWorkingParserBlocker.CorruptionSuspected,
        )
    }

    @Test
    fun parserFailsClosedForEmptyUnknownMalformedAndProductionLikeRequests() {
        val empty = EncryptedVaultWorkingParser.parse(
            EncryptedVaultWorkingParserRequest.testSourceSyntheticVector(ByteArray(0)),
        )
        val unknown = EncryptedVaultWorkingParser.parse(
            EncryptedVaultWorkingParserRequest.testSourceSyntheticVector("skv-unk-z".encodeToByteArray()),
        )
        val malformed = EncryptedVaultWorkingParser.parse(
            EncryptedVaultWorkingParserRequest.testSourceSyntheticVector("skvbad".encodeToByteArray()),
        )
        val malformedSectionOrder = EncryptedVaultWorkingParser.parse(
            EncryptedVaultWorkingParserRequest.testSourceSyntheticVector("skv--z".encodeToByteArray()),
        )
        assertClosed(empty, EncryptedVaultWorkingParserBlocker.EmptyInput)
        assertClosed(unknown, EncryptedVaultWorkingParserBlocker.UnrecognizedSyntheticVector)
        assertClosed(malformed, EncryptedVaultWorkingParserBlocker.UnrecognizedSyntheticVector)
        assertClosed(
            malformedSectionOrder,
            EncryptedVaultWorkingParserBlocker.MalformedSyntheticSectionOrder,
        )
    }

    @Test
    fun parserConsumesOnlyTestSourceSyntheticBytesAndExposesNoBytes() {
        fixtures().forEach { fixture ->
            val material = fixture.bytesForDisabledScaffoldRequestOnly()
            val request = EncryptedVaultWorkingParserRequest.testSourceSyntheticVector(material)
            val result = EncryptedVaultWorkingParser.parse(request)
            val rendered = listOf(
                request.toString(),
                result.toString(),
                result.diagnostics.toString(),
                evidence().toString(),
            ).joinToString(separator = " | ")

            assertEquals(EncryptedVaultWorkingParserSourceSet.TestSourceSyntheticVector, request.sourceSet)
            assertTrue(request.testSourceSyntheticVectorBytesPresent)
            assertFalse(request.productionParserInputBytesPresent)
            assertFalse(request.productionVaultBytesPresent)
            assertEquals(material.size, request.declaredInputByteCount)
            assertEquals(material.size, result.consumedByteCount)
            assertEquals(0, result.producedByteCount)
            assertTrue(result.testSourceSyntheticVectorBytesOnly)
            assertFalse(result.inputBytesExposed)
            assertFalse(result.inputBytesCopiedToResult)
            assertFalse(result.sectionBytesExposed)
            assertFalse(result.parsedPayloadByteArraysCreated)
            assertFalse(result.repositoryObjectCreated)
            assertFalse(result.storageStateCreated)
            assertFalse(result.writerObjectCreated)
            assertFalse(result.fileIoUsed)
            assertFalse(result.cryptoAuthenticationExecuted)
            assertTrue(result.diagnostics.safeLabelsOnly)
            assertTrue(result.diagnostics.payloadFree)
            assertTrue(result.diagnostics.rawBytesFree)
            assertTrue(result.diagnostics.filePathFree)
            assertTrue(result.diagnostics.cryptoMaterialFree)
            assertFalse(rendered.contains(fixture.markerForTestSourceConfinementAssertionOnly()))
            assertFalse(rendered.contains(material.decodeToString()))
        }
    }

    @Test
    fun parserEvidenceDoesNotAuthorizeWriterProductionVectorsStorageCryptoSyncOrMainnet() {
        val evidence = evidence()

        assertFalse(evidence.workingWriterImplementationPresent)
        assertFalse(evidence.productionVectorBytesPresent)
        assertFalse(evidence.productionParserInputBytesPresent)
        assertFalse(evidence.productionWriterOutputBytesPresent)
        assertFalse(evidence.productionVaultBytesPresent)
        assertFalse(evidence.vaultContainerSerializationPresent)
        assertFalse(evidence.vaultFileReadPresent)
        assertFalse(evidence.vaultFileWritePresent)
        assertFalse(evidence.vaultFileDeletePresent)
        assertFalse(evidence.vaultDirectoryCreated)
        assertFalse(evidence.atomicReplaceImplementationPresent)
        assertFalse(evidence.backupCreationPresent)
        assertFalse(evidence.rollbackImplementationPresent)
        assertFalse(evidence.migrationImplementationPresent)
        assertFalse(evidence.migrationExecutionPresent)
        assertFalse(evidence.corruptionRepairImplementationPresent)
        assertFalse(evidence.kdfExecutionPresent)
        assertFalse(evidence.aeadExecutionPresent)
        assertFalse(evidence.encryptionExecutionPresent)
        assertFalse(evidence.decryptionExecutionPresent)
        assertFalse(evidence.authenticationExecutionPresent)
        assertFalse(evidence.keyGenerationPresent)
        assertFalse(evidence.nonceGenerationPresent)
        assertFalse(evidence.tinkKeysetCreationPresent)
        assertFalse(evidence.tinkKeysetPersistencePresent)
        assertFalse(evidence.vaultStoragePathImplementationPresent)
        assertFalse(evidence.encryptedVaultRepositorySuccessPresent)
        assertFalse(evidence.lockSessionImplementationPresent)
        assertFalse(evidence.unlockImplementationPresent)
        assertFalse(evidence.runtimeSessionKeyPresent)
        assertFalse(evidence.sessionKeyCached)
        assertFalse(evidence.plaintextCachePresent)
        assertFalse(evidence.secureSecretStorageSuccessPathPresent)
        assertFalse(evidence.secureMetadataStorageSuccessPathPresent)
        assertFalse(evidence.productionObservationPersistencePresent)
        assertFalse(evidence.productionAddressIndexPersistencePresent)
        assertFalse(evidence.productionUtxoPersistencePresent)
        assertFalse(evidence.productionWalletHistoryPersistencePresent)
        assertFalse(evidence.productionSyncPresent)
        assertFalse(evidence.productionBackendClientPresent)
        assertFalse(evidence.productionProviderSelectionEnabled)
        assertFalse(evidence.productionProviderSelectable)
        assertTrue(evidence.productionSelectionStillDisabledProviderOnly)
        assertFalse(evidence.signingBroadcastingPresent)
        assertFalse(evidence.uiActionEnablementPresent)
        assertFalse(evidence.endpointPresent)
        assertFalse(evidence.mainnetPresent)
    }

    @Test
    fun parserEvidenceHasNoProductionAuthorizationsAndKeepsFutureWorkSeparate() {
        val evidence = evidence()

        assertTrue(evidence.futureWorkingWriterImplementationRequiresSeparatePass)
        assertTrue(evidence.futureParserWriterRoundTripRequiresSeparatePass)
        assertTrue(evidence.futureVaultStorageRepositoryRequiresSeparatePass)
        assertTrue(evidence.futureSecureStorageSuccessRequiresSeparatePass)
        assertTrue(evidence.futureSecureMetadataSuccessRequiresSeparatePass)
        assertTrue(evidence.futureProductionSyncRequiresSeparatePass)
        assertTrue(evidence.futureProductionProviderSelectionRequiresSeparatePass)
        assertFalse(evidence.workingWriterImplementationAuthorizationPresent)
        assertFalse(evidence.productionVectorCreationAuthorizationPresent)
        assertFalse(evidence.productionParserInputAuthorizationPresent)
        assertFalse(evidence.productionWriterOutputAuthorizationPresent)
        assertFalse(evidence.vaultContainerSerializationAuthorizationPresent)
        assertFalse(evidence.vaultFileReadAuthorizationPresent)
        assertFalse(evidence.vaultFileWriteAuthorizationPresent)
        assertFalse(evidence.vaultFileDeleteAuthorizationPresent)
        assertFalse(evidence.vaultDirectoryCreationAuthorizationPresent)
        assertFalse(evidence.kdfExecutionAuthorizationPresent)
        assertFalse(evidence.aeadExecutionAuthorizationPresent)
        assertFalse(evidence.encryptionAuthorizationPresent)
        assertFalse(evidence.decryptionAuthorizationPresent)
        assertFalse(evidence.authenticationAuthorizationPresent)
        assertFalse(evidence.keyGenerationAuthorizationPresent)
        assertFalse(evidence.nonceGenerationAuthorizationPresent)
        assertFalse(evidence.tinkKeysetCreationAuthorizationPresent)
        assertFalse(evidence.tinkKeysetPersistenceAuthorizationPresent)
        assertFalse(evidence.vaultSessionImplementationAuthorizationPresent)
        assertFalse(evidence.vaultUnlockAuthorizationPresent)
        assertFalse(evidence.vaultLockAuthorizationPresent)
        assertFalse(evidence.productionStorageAuthorizationPresent)
        assertFalse(evidence.productionSecretStorageAuthorizationPresent)
        assertFalse(evidence.productionMetadataStorageAuthorizationPresent)
        assertFalse(evidence.productionSyncAuthorizationPresent)
        assertFalse(evidence.productionProviderSelectionAuthorizationPresent)
        assertFalse(evidence.productionProviderImplementationAuthorizationPresent)
        assertFalse(evidence.signingBroadcastingAuthorizationPresent)
        assertFalse(evidence.uiAuthorizationPresent)
        assertFalse(evidence.endpointAuthorizationPresent)
        assertFalse(evidence.mainnetAuthorizationPresent)
    }

    @Test
    fun productionProviderSelectionStillResolvesOnlyToDisabledProvider() {
        val evidence = evidence()
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertTrue(selection.selectedProviderIsDisabled)
        assertTrue(selection.selectedProvider is DisabledVaultCryptoProvider)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertEquals(VaultCryptoProviderSelectionDecision.DisabledProviderSelected, selection.decision)
        assertFalse(selection.productionProviderSelectable)
        assertFalse(evidence.productionProviderSelectable)
        assertFalse(evidence.productionProviderSelectionEnabled)
        assertTrue(evidence.productionSelectionStillDisabledProviderOnly)
    }

    @Test
    fun displayAndDebugOutputAreRedactedSafeLabelOnlyAndContainNoSensitiveMaterial() {
        val fixture = fixtures().first {
            it.vectorClass ==
                EncryptedVaultParserWriterSyntheticVectorCatalogClass.MinimalHeaderOnlySyntheticVector
        }
        val request = EncryptedVaultWorkingParserRequest.testSourceSyntheticVector(
            fixture.bytesForDisabledScaffoldRequestOnly(),
        )
        val result = EncryptedVaultWorkingParser.parse(request)
        val rendered = listOf(
            evidence().toString(),
            evidence().parserId.toString(),
            request.toString(),
            result.toString(),
            result.diagnostics.toString(),
        ).joinToString(separator = " | ")
        val lowerRendered = rendered.lowercase()

        assertContains(rendered, "REDACTED")
        assertContains(rendered, "COMMON_MAIN_IN_MEMORY_PARSER")
        assertContains(rendered, "SYNTHETIC_ONLY")
        assertContains(rendered, "NO_WRITER")
        assertContains(rendered, "NO_IO")
        assertContains(rendered, "NO_CRYPTO_AUTH_EXECUTION")
        assertContains(rendered, "NO_MAINNET")
        fixtures().forEach { vector ->
            assertFalse(rendered.contains(vector.markerForTestSourceConfinementAssertionOnly()))
            assertFalse(rendered.contains(vector.bytesForDisabledScaffoldRequestOnly().decodeToString()))
        }
        forbiddenOutputText.forEach { forbidden ->
            assertFalse(
                forbidden in lowerRendered,
                "Working parser output must not contain $forbidden: $rendered",
            )
        }
        assertFalse(Regex("\\b[0-9a-fA-F]{64}\\b").containsMatchIn(rendered))
        assertFalse(
            Regex("\\b(?:bc1|tb1|bcrt1)[a-z0-9]{20,}\\b", RegexOption.IGNORE_CASE)
                .containsMatchIn(rendered),
        )
        evidence().policyLabels.map { it.safeLabel.value }.forEach(::assertSafePolicyLabel)
        assertTrue(evidence().diagnosticsPolicyContainsNoSensitiveMaterial)
        assertTrue(evidence().normalSourceMaterialGuardExcludesBuildHistory)
        assertTrue(evidence().localArtifactRootExcludedFromNormalSourceMaterialCorpus)
    }

    private fun assertClassifies(
        vectorClass: EncryptedVaultParserWriterSyntheticVectorCatalogClass,
        expectedClassification: EncryptedVaultWorkingParserSyntheticClassification,
        expectedSectionCount: Int,
    ) {
        val fixture = fixtures().single { it.vectorClass == vectorClass }
        val material = fixture.bytesForDisabledScaffoldRequestOnly()
        val result = EncryptedVaultWorkingParser.parse(
            EncryptedVaultWorkingParserRequest.testSourceSyntheticVector(material),
        )

        assertEquals(EncryptedVaultWorkingParserStatus.ParsedSyntheticVector, result.status)
        assertTrue(result.accepted)
        assertEquals(expectedClassification, result.classification)
        assertEquals(expectedSectionCount, result.sectionCount)
        assertEquals(material.size, result.consumedByteCount)
        assertEquals(0, result.producedByteCount)
        assertTrue(result.blockers.isEmpty())
        assertEquals(0, result.blockerCount)
        assertEquals(0, result.warningCount)
    }

    private fun assertFailsClosed(
        vectorClass: EncryptedVaultParserWriterSyntheticVectorCatalogClass,
        expectedBlocker: EncryptedVaultWorkingParserBlocker,
        expectedClassification: EncryptedVaultWorkingParserSyntheticClassification =
            EncryptedVaultWorkingParserSyntheticClassification.NotClassified,
    ) {
        val fixture = fixtures().single { it.vectorClass == vectorClass }
        val material = fixture.bytesForDisabledScaffoldRequestOnly()
        val result = EncryptedVaultWorkingParser.parse(
            EncryptedVaultWorkingParserRequest.testSourceSyntheticVector(material),
        )

        assertClosed(result, expectedBlocker)
        assertEquals(expectedClassification, result.classification)
        assertEquals(material.size, result.consumedByteCount)
    }

    private fun assertClosed(
        result: com.libertasprimordium.skald.security.EncryptedVaultWorkingParserResult,
        expectedBlocker: EncryptedVaultWorkingParserBlocker,
    ) {
        assertEquals(EncryptedVaultWorkingParserStatus.FailedClosed, result.status)
        assertFalse(result.accepted)
        assertEquals(0, result.sectionCount)
        assertContains(result.blockers, expectedBlocker)
        assertEquals(1, result.blockerCount)
        assertEquals(0, result.warningCount)
        assertEquals(0, result.producedByteCount)
        assertFalse(result.inputBytesExposed)
        assertFalse(result.inputBytesCopiedToResult)
        assertFalse(result.sectionBytesExposed)
        assertFalse(result.parsedPayloadByteArraysCreated)
        assertFalse(result.repositoryObjectCreated)
        assertFalse(result.storageStateCreated)
        assertFalse(result.writerObjectCreated)
        assertFalse(result.fileIoUsed)
        assertFalse(result.cryptoAuthenticationExecuted)
        assertTrue(result.diagnostics.safeLabelsOnly)
        assertTrue(result.diagnostics.payloadFree)
        assertTrue(result.diagnostics.rawBytesFree)
    }

    private fun assertSafePolicyLabel(label: String) {
        assertTrue(label.isNotBlank())
        assertFalse('/' in label, "Safe label must not be a path: $label")
        assertFalse('\\' in label, "Safe label must not be a path: $label")
        assertFalse('.' in label, "Safe label must not be a filename or endpoint: $label")
        assertFalse('~' in label, "Safe label must not be a home-relative path: $label")
        forbiddenLabelText.forEach { forbidden ->
            assertFalse(
                label.contains(forbidden, ignoreCase = true),
                "Safe label contains forbidden material class $forbidden: $label",
            )
        }
    }

    private companion object {
        val forbiddenLabelText = listOf(
            "nsec",
            "psbt",
            "xprv",
            "tprv",
            "wif",
            "mnemonic",
            "private-key",
            "credential",
            "descriptor",
            "address",
            "txid",
            "transaction-hex",
            "filesystem",
            "stack-trace",
            "provider-handle",
            "support-export",
            "payload",
            "hash",
            "mac",
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
