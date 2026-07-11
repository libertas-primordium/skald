package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledEncryptedVaultParserScaffold
import com.libertasprimordium.skald.security.DisabledEncryptedVaultWriterScaffold
import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.EncryptedVaultContainerFormatV1DecisionPolicy
import com.libertasprimordium.skald.security.EncryptedVaultMigrationCorruptionPolicyDecisionPolicy
import com.libertasprimordium.skald.security.EncryptedVaultParserRequest
import com.libertasprimordium.skald.security.EncryptedVaultParserWriterAdmissionGatePolicy
import com.libertasprimordium.skald.security.EncryptedVaultParserWriterScaffoldCheck
import com.libertasprimordium.skald.security.EncryptedVaultParserWriterScaffoldKind
import com.libertasprimordium.skald.security.EncryptedVaultParserWriterScaffoldPolicy
import com.libertasprimordium.skald.security.EncryptedVaultParserWriterScaffoldPolicyLabel
import com.libertasprimordium.skald.security.EncryptedVaultParserWriterScaffoldSourceSet
import com.libertasprimordium.skald.security.EncryptedVaultParserWriterScaffoldStatus
import com.libertasprimordium.skald.security.EncryptedVaultParserWriterTestVectorAdmissionPolicy
import com.libertasprimordium.skald.security.EncryptedVaultStoragePathSessionLifecycleDecisionPolicy
import com.libertasprimordium.skald.security.EncryptedVaultStorageReadinessDecisionPolicy
import com.libertasprimordium.skald.security.EncryptedVaultWriterRequest
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionDecision
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class EncryptedVaultParserWriterScaffoldTest {
    private fun scaffold() =
        EncryptedVaultParserWriterScaffoldPolicy.currentParserWriterScaffold()

    @Test
    fun parserWriterScaffoldExistsAsCommonMainPolicyModelInterfaceOnly() {
        val scaffold = scaffold()

        assertEquals(1, scaffold.scaffoldVersion)
        assertEquals(
            EncryptedVaultParserWriterScaffoldKind
                .EncryptedLocalVaultParserWriterImplementationScaffold,
            scaffold.scaffoldKind,
        )
        assertEquals(
            "ENCRYPTED_LOCAL_VAULT_PARSER_WRITER_IMPLEMENTATION_SCAFFOLD",
            scaffold.scaffoldKind.label,
        )
        assertEquals(EncryptedVaultParserWriterScaffoldSourceSet.CommonMainPolicy, scaffold.sourceSet)
        assertEquals("COMMON_MAIN_POLICY", scaffold.sourceSet.label)
        assertTrue(scaffold.parserWriterScaffoldDecisionPassed)
        assertEquals(0, scaffold.failureLabels.size)
        assertEquals(0, scaffold.blockerCount)
        assertEquals(0, scaffold.warningCount)
        assertEquals(EncryptedVaultParserWriterScaffoldCheck.entries.size, scaffold.scaffoldCheckCount)
        EncryptedVaultParserWriterScaffoldCheck.entries.forEach { check ->
            assertContains(scaffold.scaffoldChecks, check)
        }
    }

    @Test
    fun parserWriterScaffoldReflectsPriorEvidence() {
        val scaffold = scaffold()
        val storageReadiness =
            EncryptedVaultStorageReadinessDecisionPolicy.currentStorageReadinessDecision()
        val containerDecision =
            EncryptedVaultContainerFormatV1DecisionPolicy.currentContainerFormatV1Decision()
        val storagePathDecision =
            EncryptedVaultStoragePathSessionLifecycleDecisionPolicy
                .currentStoragePathSessionLifecycleDecision()
        val migrationDecision =
            EncryptedVaultMigrationCorruptionPolicyDecisionPolicy
                .currentMigrationCorruptionPolicyDecision()
        val parserWriterGate =
            EncryptedVaultParserWriterAdmissionGatePolicy.currentParserWriterAdmissionGate()
        val testVectorAdmission =
            EncryptedVaultParserWriterTestVectorAdmissionPolicy
                .currentParserWriterTestVectorAdmission()

        assertEquals(storageReadiness.storageReadinessDecisionPassed, scaffold.storageReadinessDecisionPresent)
        assertEquals(containerDecision.containerFormatV1DecisionPassed, scaffold.containerFormatV1DecisionPresent)
        assertEquals(
            storagePathDecision.storagePathSessionLifecycleDecisionPassed,
            scaffold.storagePathSessionLifecycleDecisionPresent,
        )
        assertEquals(
            migrationDecision.migrationCorruptionPolicyDecisionPassed,
            scaffold.migrationCorruptionPolicyDecisionPresent,
        )
        assertEquals(
            parserWriterGate.parserWriterAdmissionGateDecisionPassed,
            scaffold.parserWriterAdmissionGatePresent,
        )
        assertEquals(
            testVectorAdmission.parserWriterTestVectorAdmissionDecisionPassed,
            scaffold.parserWriterTestVectorAdmissionPresent,
        )
        assertTrue(scaffold.encryptedVaultDesignPresent)
        assertTrue(scaffold.cryptoDecisionPresent)
        assertTrue(scaffold.dependencyReviewPresent)
        assertTrue(scaffold.providerBoundaryPresent)
        assertTrue(scaffold.secureStorageBoundaryPresent)
        assertTrue(scaffold.secureMetadataBoundaryPresent)
        assertEquals(12, scaffold.evidenceCount)
    }

    @Test
    fun parserWriterScaffoldAdmitsOnlyDisabledScaffoldAndFutureSeparatePasses() {
        val scaffold = scaffold()

        assertTrue(scaffold.parserWriterImplementationScaffoldPresent)
        assertTrue(scaffold.disabledParserScaffoldPresent)
        assertTrue(scaffold.disabledWriterScaffoldPresent)
        assertTrue(scaffold.parserInterfacePresent)
        assertTrue(scaffold.writerInterfacePresent)
        assertTrue(scaffold.parserResultModelPresent)
        assertTrue(scaffold.writerResultModelPresent)
        assertTrue(scaffold.redactedDiagnosticsModelPresent)
        assertTrue(scaffold.testSourceSyntheticVectorCatalogAdmitted)
        assertTrue(scaffold.testSourceSyntheticVectorBytesAllowed)
        assertTrue(scaffold.futureParserImplementationRequiresSeparatePass)
        assertTrue(scaffold.futureWriterImplementationRequiresSeparatePass)
        assertTrue(scaffold.futureParserWriterVectorExecutionRequiresSeparatePass)
        assertTrue(scaffold.futureParserWriterKatExecutionRequiresSeparatePass)
        assertTrue(scaffold.futureVaultStorageRepositoryRequiresSeparatePass)
        assertTrue(scaffold.futureSecureStorageSuccessRequiresSeparatePass)
        assertTrue(scaffold.futureSecureMetadataSuccessRequiresSeparatePass)
        assertTrue(scaffold.futureProductionSyncRequiresSeparatePass)
        assertTrue(scaffold.futureProductionProviderSelectionRequiresSeparatePass)
        assertTrue(scaffold.parserWriterImplementationScaffoldPresentIsEvidenceOnly)
        assertTrue(scaffold.disabledParserScaffoldPresentIsNotParserImplementation)
        assertTrue(scaffold.disabledWriterScaffoldPresentIsNotWriterImplementation)
        assertTrue(scaffold.parserInterfacePresentIsNotParserExecution)
        assertTrue(scaffold.writerInterfacePresentIsNotWriterExecution)
        assertTrue(scaffold.testSourceSyntheticVectorBytesAllowedIsNotProductionVectorAuthorization)
        assertTrue(scaffold.futureTestSourceSyntheticBytesAreNotProductionVaultBytes)
    }

    @Test
    fun parserWriterScaffoldHasNoRuntimeOrProductionSurfaces() {
        val scaffold = scaffold()

        assertFalse(scaffold.workingParserImplementationPresent)
        assertFalse(scaffold.workingWriterImplementationPresent)
        assertFalse(scaffold.productionVectorBytesPresent)
        assertFalse(scaffold.productionParserInputBytesPresent)
        assertFalse(scaffold.productionWriterOutputBytesPresent)
        assertFalse(scaffold.parserVectorExecutionPresent)
        assertFalse(scaffold.writerVectorExecutionPresent)
        assertFalse(scaffold.roundTripVectorExecutionPresent)
        assertFalse(scaffold.negativeVectorExecutionPresent)
        assertFalse(scaffold.vaultContainerSerializationPresent)
        assertFalse(scaffold.vaultContainerParsingPresent)
        assertFalse(scaffold.vaultContainerBytesProduced)
        assertFalse(scaffold.vaultContainerBytesConsumed)
        assertFalse(scaffold.vaultHeaderSerialized)
        assertFalse(scaffold.vaultHeaderParsed)
        assertFalse(scaffold.vaultRecordDirectorySerialized)
        assertFalse(scaffold.vaultRecordDirectoryParsed)
        assertFalse(scaffold.vaultRecordEnvelopeSerialized)
        assertFalse(scaffold.vaultRecordEnvelopeParsed)
        assertFalse(scaffold.vaultFileReadPresent)
        assertFalse(scaffold.vaultFileWritePresent)
        assertFalse(scaffold.vaultFileDeletePresent)
        assertFalse(scaffold.vaultDirectoryCreated)
        assertFalse(scaffold.atomicReplaceImplementationPresent)
        assertFalse(scaffold.partialWriteDetectionImplementationPresent)
        assertFalse(scaffold.migrationImplementationPresent)
        assertFalse(scaffold.migrationExecutionPresent)
        assertFalse(scaffold.corruptionDetectionImplementationPresent)
        assertFalse(scaffold.corruptionRepairImplementationPresent)
        assertFalse(scaffold.backupCreationPresent)
        assertFalse(scaffold.rollbackImplementationPresent)
        assertFalse(scaffold.kdfExecutionPresent)
        assertFalse(scaffold.aeadExecutionPresent)
        assertFalse(scaffold.encryptionExecutionPresent)
        assertFalse(scaffold.decryptionExecutionPresent)
        assertFalse(scaffold.keyGenerationPresent)
        assertFalse(scaffold.nonceGenerationPresent)
        assertFalse(scaffold.tinkKeysetCreationPresent)
        assertFalse(scaffold.tinkKeysetPersistencePresent)
        assertFalse(scaffold.vaultStoragePathImplementationPresent)
        assertFalse(scaffold.encryptedVaultFileFormatImplemented)
        assertFalse(scaffold.encryptedVaultRepositorySuccessPresent)
        assertFalse(scaffold.lockSessionImplementationPresent)
        assertFalse(scaffold.unlockImplementationPresent)
        assertFalse(scaffold.runtimeSessionKeyPresent)
        assertFalse(scaffold.sessionKeyCached)
        assertFalse(scaffold.plaintextCachePresent)
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
    }

    @Test
    fun disabledParserAndWriterRejectWithoutConsumingOrProducingBytes() {
        val parserResult = DisabledEncryptedVaultParserScaffold.parse(
            EncryptedVaultParserRequest(declaredInputByteCount = 7),
        )
        val writerResult = DisabledEncryptedVaultWriterScaffold.write(
            EncryptedVaultWriterRequest(requestedOutputByteCount = 7),
        )

        assertEquals(EncryptedVaultParserWriterScaffoldStatus.RejectedByScaffold, parserResult.status)
        assertFalse(parserResult.accepted)
        assertEquals(0, parserResult.consumedByteCount)
        assertEquals(0, parserResult.producedByteCount)
        assertEquals(0, parserResult.diagnostics.consumedByteCount)
        assertEquals(0, parserResult.diagnostics.producedByteCount)
        assertTrue(parserResult.diagnostics.safeLabelsOnly)
        assertTrue(parserResult.diagnostics.payloadFree)
        assertEquals(EncryptedVaultParserWriterScaffoldStatus.RejectedByScaffold, writerResult.status)
        assertFalse(writerResult.accepted)
        assertEquals(0, writerResult.consumedByteCount)
        assertEquals(0, writerResult.producedByteCount)
        assertEquals(0, writerResult.diagnostics.consumedByteCount)
        assertEquals(0, writerResult.diagnostics.producedByteCount)
        assertTrue(writerResult.diagnostics.safeLabelsOnly)
        assertTrue(writerResult.diagnostics.payloadFree)

        val scaffold = scaffold()
        assertTrue(scaffold.disabledParserRejectsWithoutConsumingBytes)
        assertTrue(scaffold.disabledWriterRejectsWithoutProducingBytes)
    }

    @Test
    fun parserWriterScaffoldHasNoImplementationAuthorizations() {
        val scaffold = scaffold()

        assertFalse(scaffold.parserImplementationAuthorizationPresent)
        assertFalse(scaffold.writerImplementationAuthorizationPresent)
        assertFalse(scaffold.productionVectorCreationAuthorizationPresent)
        assertFalse(scaffold.parserVectorExecutionAuthorizationPresent)
        assertFalse(scaffold.writerVectorExecutionAuthorizationPresent)
        assertFalse(scaffold.vaultContainerSerializationAuthorizationPresent)
        assertFalse(scaffold.vaultContainerParsingAuthorizationPresent)
        assertFalse(scaffold.vaultFileReadAuthorizationPresent)
        assertFalse(scaffold.vaultFileWriteAuthorizationPresent)
        assertFalse(scaffold.vaultFileDeleteAuthorizationPresent)
        assertFalse(scaffold.vaultDirectoryCreationAuthorizationPresent)
        assertFalse(scaffold.kdfExecutionAuthorizationPresent)
        assertFalse(scaffold.aeadExecutionAuthorizationPresent)
        assertFalse(scaffold.encryptionAuthorizationPresent)
        assertFalse(scaffold.decryptionAuthorizationPresent)
        assertFalse(scaffold.keyGenerationAuthorizationPresent)
        assertFalse(scaffold.nonceGenerationAuthorizationPresent)
        assertFalse(scaffold.tinkKeysetCreationAuthorizationPresent)
        assertFalse(scaffold.tinkKeysetPersistenceAuthorizationPresent)
        assertFalse(scaffold.migrationImplementationAuthorizationPresent)
        assertFalse(scaffold.corruptionDetectionAuthorizationPresent)
        assertFalse(scaffold.corruptionRepairAuthorizationPresent)
        assertFalse(scaffold.backupCreationAuthorizationPresent)
        assertFalse(scaffold.rollbackAuthorizationPresent)
        assertFalse(scaffold.atomicReplaceAuthorizationPresent)
        assertFalse(scaffold.partialWriteDetectionAuthorizationPresent)
        assertFalse(scaffold.productionStorageAuthorizationPresent)
        assertFalse(scaffold.productionSecretStorageAuthorizationPresent)
        assertFalse(scaffold.productionMetadataStorageAuthorizationPresent)
        assertFalse(scaffold.productionSyncAuthorizationPresent)
        assertFalse(scaffold.productionProviderSelectionAuthorizationPresent)
        assertFalse(scaffold.productionProviderImplementationAuthorizationPresent)
        assertFalse(scaffold.signingBroadcastingAuthorizationPresent)
        assertFalse(scaffold.uiAuthorizationPresent)
        assertFalse(scaffold.endpointAuthorizationPresent)
        assertFalse(scaffold.mainnetAuthorizationPresent)
        assertFalse(scaffold.vaultSessionImplementationAuthorizationPresent)
        assertFalse(scaffold.vaultUnlockAuthorizationPresent)
        assertFalse(scaffold.vaultLockAuthorizationPresent)
    }

    @Test
    fun productionProviderSelectionStillResolvesOnlyToDisabledProvider() {
        val scaffold = scaffold()
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertTrue(selection.selectedProviderIsDisabled)
        assertTrue(selection.selectedProvider is DisabledVaultCryptoProvider)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertEquals(VaultCryptoProviderSelectionDecision.DisabledProviderSelected, selection.decision)
        assertFalse(selection.productionProviderSelectable)
        assertFalse(scaffold.productionProviderSelectable)
        assertFalse(scaffold.productionProviderSelectionEnabled)
        assertTrue(scaffold.productionSelectionStillDisabledProviderOnly)
    }

    @Test
    fun noSyntheticVectorCatalogOrBytesAreCreatedInThisBranch() {
        val scaffold = scaffold()

        assertTrue(scaffold.testSourceSyntheticVectorCatalogAdmitted)
        assertTrue(scaffold.testSourceSyntheticVectorBytesAllowed)
        assertFalse(scaffold.testSourceSyntheticVectorCatalogCreated)
        assertFalse(scaffold.testSourceSyntheticVectorBytesCreated)
        assertFalse(scaffold.testSourceSyntheticVectorBytesExecuted)
        assertFalse(scaffold.testSourceSyntheticVectorBytesLogged)
        assertTrue(scaffold.testSourceSyntheticVectorCatalogConfinedToTestSource)
    }

    @Test
    fun displayAndDebugOutputAreRedactedOrSafeLabelOnly() {
        val scaffold = scaffold()
        val rendered = listOf(
            scaffold.toString(),
            DisabledEncryptedVaultParserScaffold.parse(EncryptedVaultParserRequest()).toString(),
            DisabledEncryptedVaultWriterScaffold.write(EncryptedVaultWriterRequest()).toString(),
            DisabledEncryptedVaultParserScaffold.parse(EncryptedVaultParserRequest()).diagnostics.toString(),
            EncryptedVaultParserRequest().toString(),
            EncryptedVaultWriterRequest().toString(),
        ).joinToString(separator = " | ")
        val lowerRendered = rendered.lowercase()

        assertContains(rendered, "REDACTED")
        assertContains(rendered, "COMMON_MAIN_POLICY")
        assertContains(rendered, "SCAFFOLD_ONLY")
        assertContains(rendered, "DISABLED_PARSER")
        assertContains(rendered, "DISABLED_WRITER")
        assertContains(rendered, "NO_IO")
        assertContains(rendered, "DISABLED_PROVIDER_ONLY")

        listOf(
            "public vector bytes",
            "public vector hex",
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
        ).forEach { forbidden ->
            assertFalse(
                forbidden in lowerRendered,
                "Scaffold display output must not contain $forbidden: $rendered",
            )
        }

        scaffold.policyLabels.map { it.safeLabel.value }
            .plus(scaffold.displayLabel.value)
            .forEach(::assertSafePolicyLabel)
        assertTrue(scaffold.diagnosticsPolicyContainsNoSensitiveMaterial)
        assertEquals(
            EncryptedVaultParserWriterScaffoldPolicyLabel.entries.toSet(),
            scaffold.policyLabels.toSet(),
        )
    }

    @Test
    fun sourceMaterialGuardCorpusBoundariesRemainExcluded() {
        val scaffold = scaffold()

        assertTrue(scaffold.normalSourceMaterialGuardExcludesBuildHistory)
        assertTrue(scaffold.localArtifactRootExcludedFromNormalSourceMaterialCorpus)
    }

    private fun assertSafePolicyLabel(label: String) {
        assertTrue(label.isNotBlank())
        assertFalse('/' in label, "Safe label must not be a path: $label")
        assertFalse('\\' in label, "Safe label must not be a path: $label")
        assertFalse('.' in label, "Safe label must not be a filename or endpoint: $label")
        assertFalse('~' in label, "Safe label must not be a home-relative path: $label")
        listOf(
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
        ).forEach { forbidden ->
            assertFalse(
                label.contains(forbidden, ignoreCase = true),
                "Safe label contains forbidden material class $forbidden: $label",
            )
        }
    }
}
