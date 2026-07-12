package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.EncryptedVaultContainerFormatV1DecisionPolicy
import com.libertasprimordium.skald.security.EncryptedVaultMigrationCorruptionPolicyDecisionPolicy
import com.libertasprimordium.skald.security.EncryptedVaultParserWriterAdmissionGatePolicy
import com.libertasprimordium.skald.security.EncryptedVaultParserWriterScaffoldPolicy
import com.libertasprimordium.skald.security.EncryptedVaultParserWriterSyntheticVectorCatalog
import com.libertasprimordium.skald.security.EncryptedVaultParserWriterTestVectorAdmissionPolicy
import com.libertasprimordium.skald.security.EncryptedVaultStoragePathSessionLifecycleDecisionPolicy
import com.libertasprimordium.skald.security.EncryptedVaultStorageReadinessDecisionPolicy
import com.libertasprimordium.skald.security.EncryptedVaultWorkingParserAdmissionCheck
import com.libertasprimordium.skald.security.EncryptedVaultWorkingParserAdmissionErrorTaxonomyLabel
import com.libertasprimordium.skald.security.EncryptedVaultWorkingParserAdmissionGatePolicy
import com.libertasprimordium.skald.security.EncryptedVaultWorkingParserAdmissionKind
import com.libertasprimordium.skald.security.EncryptedVaultWorkingParserAdmissionPolicyLabel
import com.libertasprimordium.skald.security.EncryptedVaultWorkingParserAdmissionSourceSet
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionDecision
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class EncryptedVaultWorkingParserAdmissionGateTest {
    private fun admission() =
        EncryptedVaultWorkingParserAdmissionGatePolicy.currentWorkingParserAdmissionGate()

    @Test
    fun workingParserAdmissionGateExistsAsCommonMainPolicyModelOnly() {
        val admission = admission()

        assertEquals(1, admission.admissionVersion)
        assertEquals(
            EncryptedVaultWorkingParserAdmissionKind.EncryptedLocalVaultWorkingParserAdmissionGate,
            admission.admissionKind,
        )
        assertEquals(
            "ENCRYPTED_LOCAL_VAULT_WORKING_PARSER_ADMISSION_GATE",
            admission.admissionKind.label,
        )
        assertEquals(EncryptedVaultWorkingParserAdmissionSourceSet.CommonMainPolicy, admission.sourceSet)
        assertEquals("COMMON_MAIN_POLICY", admission.sourceSet.label)
        assertTrue(admission.workingParserAdmissionGatePassed)
        assertTrue(admission.workingParserAdmissionGateDecisionPassed)
        assertEquals(0, admission.failureLabels.size)
        assertEquals(0, admission.blockerCount)
        assertEquals(0, admission.warningCount)
        assertEquals(
            EncryptedVaultWorkingParserAdmissionCheck.entries.size,
            admission.admissionCheckCount,
        )
        EncryptedVaultWorkingParserAdmissionCheck.entries.forEach { check ->
            assertContains(admission.admissionChecks, check)
        }
    }

    @Test
    fun workingParserAdmissionReflectsPriorEncryptedVaultEvidence() {
        val admission = admission()
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
        val scaffold = EncryptedVaultParserWriterScaffoldPolicy.currentParserWriterScaffold()
        val catalog = EncryptedVaultParserWriterSyntheticVectorCatalog.currentCatalogReport()

        assertEquals(storageReadiness.storageReadinessDecisionPassed, admission.storageReadinessDecisionPresent)
        assertEquals(containerDecision.containerFormatV1DecisionPassed, admission.containerFormatV1DecisionPresent)
        assertEquals(
            storagePathDecision.storagePathSessionLifecycleDecisionPassed,
            admission.storagePathSessionLifecycleDecisionPresent,
        )
        assertEquals(
            migrationDecision.migrationCorruptionPolicyDecisionPassed,
            admission.migrationCorruptionPolicyDecisionPresent,
        )
        assertEquals(
            parserWriterGate.parserWriterAdmissionGateDecisionPassed,
            admission.parserWriterAdmissionGatePresent,
        )
        assertEquals(
            testVectorAdmission.parserWriterTestVectorAdmissionDecisionPassed,
            admission.parserWriterTestVectorAdmissionPresent,
        )
        assertEquals(scaffold.parserWriterScaffoldDecisionPassed, admission.parserWriterImplementationScaffoldPresent)
        assertEquals(catalog.syntheticVectorCatalogPresent, admission.parserWriterSyntheticVectorCatalogPresent)
        assertTrue(admission.encryptedVaultDesignPresent)
        assertTrue(admission.cryptoDecisionPresent)
        assertTrue(admission.dependencyReviewPresent)
        assertTrue(admission.providerBoundaryPresent)
        assertTrue(admission.secureStorageBoundaryPresent)
        assertTrue(admission.secureMetadataBoundaryPresent)
        assertEquals(14, admission.evidenceCount)
    }

    @Test
    fun workingParserAdmissionAdmitsOnlyLaterInMemorySyntheticVectorParserWork() {
        val admission = admission()

        assertTrue(admission.workingParserAdmissionGatePassed)
        assertTrue(admission.futureCommonMainInMemoryParserAdmitted)
        assertTrue(admission.futureSyntheticVectorParserExecutionAdmitted)
        assertTrue(admission.futureParserFailClosedPolicyAdmitted)
        assertTrue(admission.futureParserRedactedResultPolicyAdmitted)
        assertTrue(admission.futureParserErrorTaxonomyAdmitted)
        assertTrue(admission.futureUnsupportedVersionPolicyAdmitted)
        assertTrue(admission.futureUnsupportedCriticalFeaturePolicyAdmitted)
        assertTrue(admission.futureTruncatedInputPolicyAdmitted)
        assertTrue(admission.futureMigrationRequiredPolicyAdmitted)
        assertTrue(admission.futureCorruptionSuspectedPolicyAdmitted)
        assertTrue(admission.futureDesktopParserVectorExecutionRequired)
        assertTrue(admission.futureAndroidParserVectorExecutionRequired)
        assertTrue(admission.futureWorkingParserImplementationRequiresSeparatePass)
        assertTrue(admission.futureWorkingWriterImplementationRequiresSeparatePass)
        assertTrue(admission.futureParserWriterRoundTripRequiresSeparatePass)
        assertTrue(admission.futureVaultStorageRepositoryRequiresSeparatePass)
        assertTrue(admission.futureSecureStorageSuccessRequiresSeparatePass)
        assertTrue(admission.futureSecureMetadataSuccessRequiresSeparatePass)
        assertTrue(admission.futureProductionSyncRequiresSeparatePass)
        assertTrue(admission.futureProductionProviderSelectionRequiresSeparatePass)
        assertTrue(admission.workingParserAdmissionGatePassedIsAdmissionEvidenceOnly)
        assertTrue(admission.futureCommonMainInMemoryParserAdmittedIsNotParserImplementation)
        assertTrue(admission.futureSyntheticVectorParserExecutionAdmittedIsNotParserExecution)
        assertTrue(admission.futureParserFailClosedPolicyAdmittedIsNotParserImplementation)
        assertTrue(admission.futureParserRedactedResultPolicyAdmittedIsNotDiagnosticsImplementation)
        assertTrue(admission.futureParserErrorTaxonomyAdmittedIsNotParserImplementation)
        assertTrue(admission.futureWorkingWriterImplementationRequiresSeparatePassKeepsWriterSeparate)
    }

    @Test
    fun futureWorkingParserPolicyRemainsInMemoryRedactedFailClosedAndWriterSeparate() {
        val admission = admission()

        assertTrue(admission.futureParserImplementationCommonMainOnly)
        assertTrue(admission.futureParserImplementationInMemoryOnly)
        assertTrue(admission.futureParserImplementationNoFileIo)
        assertTrue(admission.futureParserImplementationNoDirectoryCreation)
        assertTrue(admission.futureParserImplementationNoSharedPreferencesOrSettingsStorage)
        assertTrue(admission.futureParserImplementationNoKdfAeadEncryptionDecryptionAuthentication)
        assertTrue(admission.futureParserImplementationNoKeyNonceTink)
        assertTrue(admission.futureParserImplementationNoPersistenceOrRepositorySuccess)
        assertTrue(admission.futureParserImplementationSyntheticVectorsOnlyUntilSeparateApproval)
        assertTrue(admission.futureParserImplementationReturnsRedactedSkaldModelsOnly)
        assertTrue(admission.futureParserImplementationFailClosedOnRequiredFailures)
        assertTrue(admission.futureWriterImplementationRemainsSeparate)
        assertEquals(
            EncryptedVaultWorkingParserAdmissionErrorTaxonomyLabel.entries.toSet(),
            admission.errorTaxonomyLabels.toSet(),
        )
        listOf(
            EncryptedVaultWorkingParserAdmissionErrorTaxonomyLabel.UnsupportedVersion,
            EncryptedVaultWorkingParserAdmissionErrorTaxonomyLabel.UnsupportedCriticalFeature,
            EncryptedVaultWorkingParserAdmissionErrorTaxonomyLabel.TruncatedInput,
            EncryptedVaultWorkingParserAdmissionErrorTaxonomyLabel.MalformedSectionOrder,
            EncryptedVaultWorkingParserAdmissionErrorTaxonomyLabel.MigrationRequired,
            EncryptedVaultWorkingParserAdmissionErrorTaxonomyLabel.CorruptionSuspected,
            EncryptedVaultWorkingParserAdmissionErrorTaxonomyLabel.StorageUnavailable,
        ).forEach { expected ->
            assertContains(admission.errorTaxonomyLabels, expected)
        }
    }

    @Test
    fun workingParserAdmissionHasNoRuntimeOrProductionSurfaces() {
        val admission = admission()

        assertFalse(admission.workingParserImplementationPresent)
        assertFalse(admission.workingWriterImplementationPresent)
        assertFalse(admission.parserVectorExecutionPresent)
        assertFalse(admission.writerVectorExecutionPresent)
        assertFalse(admission.roundTripVectorExecutionPresent)
        assertFalse(admission.negativeVectorExecutionPresent)
        assertFalse(admission.productionVectorBytesPresent)
        assertFalse(admission.productionParserInputBytesPresent)
        assertFalse(admission.productionWriterOutputBytesPresent)
        assertFalse(admission.vaultContainerSerializationPresent)
        assertFalse(admission.vaultContainerParsingPresent)
        assertFalse(admission.vaultContainerBytesProduced)
        assertFalse(admission.vaultContainerBytesConsumed)
        assertFalse(admission.vaultHeaderSerialized)
        assertFalse(admission.vaultHeaderParsed)
        assertFalse(admission.vaultRecordDirectorySerialized)
        assertFalse(admission.vaultRecordDirectoryParsed)
        assertFalse(admission.vaultRecordEnvelopeSerialized)
        assertFalse(admission.vaultRecordEnvelopeParsed)
        assertFalse(admission.vaultFileReadPresent)
        assertFalse(admission.vaultFileWritePresent)
        assertFalse(admission.vaultFileDeletePresent)
        assertFalse(admission.vaultDirectoryCreated)
        assertFalse(admission.atomicReplaceImplementationPresent)
        assertFalse(admission.partialWriteDetectionImplementationPresent)
        assertFalse(admission.migrationImplementationPresent)
        assertFalse(admission.migrationExecutionPresent)
        assertFalse(admission.corruptionDetectionImplementationPresent)
        assertFalse(admission.corruptionRepairImplementationPresent)
        assertFalse(admission.backupCreationPresent)
        assertFalse(admission.rollbackImplementationPresent)
        assertFalse(admission.kdfExecutionPresent)
        assertFalse(admission.aeadExecutionPresent)
        assertFalse(admission.encryptionExecutionPresent)
        assertFalse(admission.decryptionExecutionPresent)
        assertFalse(admission.authenticationExecutionPresent)
        assertFalse(admission.keyGenerationPresent)
        assertFalse(admission.nonceGenerationPresent)
        assertFalse(admission.tinkKeysetCreationPresent)
        assertFalse(admission.tinkKeysetPersistencePresent)
        assertFalse(admission.vaultStoragePathImplementationPresent)
        assertFalse(admission.encryptedVaultFileFormatImplemented)
        assertFalse(admission.encryptedVaultRepositorySuccessPresent)
        assertFalse(admission.lockSessionImplementationPresent)
        assertFalse(admission.unlockImplementationPresent)
        assertFalse(admission.runtimeSessionKeyPresent)
        assertFalse(admission.sessionKeyCached)
        assertFalse(admission.plaintextCachePresent)
        assertFalse(admission.secureSecretStorageSuccessPathPresent)
        assertFalse(admission.secureMetadataStorageSuccessPathPresent)
        assertFalse(admission.productionObservationPersistencePresent)
        assertFalse(admission.productionAddressIndexPersistencePresent)
        assertFalse(admission.productionUtxoPersistencePresent)
        assertFalse(admission.productionWalletHistoryPersistencePresent)
        assertFalse(admission.productionSyncPresent)
        assertFalse(admission.productionBackendClientPresent)
        assertFalse(admission.productionProviderSelectionEnabled)
        assertFalse(admission.productionProviderSelectable)
        assertTrue(admission.productionSelectionStillDisabledProviderOnly)
        assertFalse(admission.signingBroadcastingPresent)
        assertFalse(admission.uiActionEnablementPresent)
        assertFalse(admission.endpointPresent)
        assertFalse(admission.mainnetPresent)
    }

    @Test
    fun workingParserAdmissionHasNoImplementationOrProductionAuthorizations() {
        val admission = admission()

        assertFalse(admission.workingParserImplementationAuthorizationPresent)
        assertFalse(admission.workingWriterImplementationAuthorizationPresent)
        assertFalse(admission.parserVectorExecutionAuthorizationPresent)
        assertFalse(admission.writerVectorExecutionAuthorizationPresent)
        assertFalse(admission.productionVectorCreationAuthorizationPresent)
        assertFalse(admission.vaultContainerSerializationAuthorizationPresent)
        assertFalse(admission.vaultContainerParsingAuthorizationPresent)
        assertFalse(admission.vaultFileReadAuthorizationPresent)
        assertFalse(admission.vaultFileWriteAuthorizationPresent)
        assertFalse(admission.vaultFileDeleteAuthorizationPresent)
        assertFalse(admission.vaultDirectoryCreationAuthorizationPresent)
        assertFalse(admission.atomicReplaceAuthorizationPresent)
        assertFalse(admission.partialWriteDetectionAuthorizationPresent)
        assertFalse(admission.migrationImplementationAuthorizationPresent)
        assertFalse(admission.corruptionDetectionAuthorizationPresent)
        assertFalse(admission.corruptionRepairAuthorizationPresent)
        assertFalse(admission.backupCreationAuthorizationPresent)
        assertFalse(admission.rollbackAuthorizationPresent)
        assertFalse(admission.kdfExecutionAuthorizationPresent)
        assertFalse(admission.aeadExecutionAuthorizationPresent)
        assertFalse(admission.encryptionAuthorizationPresent)
        assertFalse(admission.decryptionAuthorizationPresent)
        assertFalse(admission.authenticationAuthorizationPresent)
        assertFalse(admission.keyGenerationAuthorizationPresent)
        assertFalse(admission.nonceGenerationAuthorizationPresent)
        assertFalse(admission.tinkKeysetCreationAuthorizationPresent)
        assertFalse(admission.tinkKeysetPersistenceAuthorizationPresent)
        assertFalse(admission.vaultSessionImplementationAuthorizationPresent)
        assertFalse(admission.vaultUnlockAuthorizationPresent)
        assertFalse(admission.vaultLockAuthorizationPresent)
        assertFalse(admission.productionStorageAuthorizationPresent)
        assertFalse(admission.productionSecretStorageAuthorizationPresent)
        assertFalse(admission.productionMetadataStorageAuthorizationPresent)
        assertFalse(admission.productionSyncAuthorizationPresent)
        assertFalse(admission.productionProviderSelectionAuthorizationPresent)
        assertFalse(admission.productionProviderImplementationAuthorizationPresent)
        assertFalse(admission.signingBroadcastingAuthorizationPresent)
        assertFalse(admission.uiAuthorizationPresent)
        assertFalse(admission.endpointAuthorizationPresent)
        assertFalse(admission.mainnetAuthorizationPresent)
    }

    @Test
    fun productionProviderSelectionStillResolvesOnlyToDisabledProvider() {
        val admission = admission()
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertTrue(selection.selectedProviderIsDisabled)
        assertTrue(selection.selectedProvider is DisabledVaultCryptoProvider)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertEquals(VaultCryptoProviderSelectionDecision.DisabledProviderSelected, selection.decision)
        assertFalse(selection.productionProviderSelectable)
        assertFalse(admission.productionProviderSelectable)
        assertFalse(admission.productionProviderSelectionEnabled)
        assertTrue(admission.productionSelectionStillDisabledProviderOnly)
    }

    @Test
    fun workingParserAdmissionCreatesNoParserObjectAndConsumesNoBytes() {
        val admission = admission()

        assertFalse(admission.workingParserAdmissionCreatesParserObject)
        assertFalse(admission.workingParserAdmissionConsumesBytes)
        assertFalse(admission.workingParserAdmissionCreatesParsedHeader)
        assertFalse(admission.workingParserAdmissionCreatesParsedDirectory)
        assertFalse(admission.workingParserAdmissionCreatesParsedEnvelope)
        assertFalse(admission.parserVectorExecutionPresent)
        assertFalse(admission.vaultHeaderParsed)
        assertFalse(admission.vaultRecordDirectoryParsed)
        assertFalse(admission.vaultRecordEnvelopeParsed)
        assertFalse(admission.vaultContainerBytesConsumed)
    }

    @Test
    fun displayAndDebugOutputAreRedactedOrSafeLabelOnly() {
        val admission = admission()
        val rendered = listOf(
            admission.toString(),
            admission.admissionId.toString(),
            admission.displayLabel.toString(),
        ).joinToString(separator = " | ")
        val lowerRendered = rendered.lowercase()

        assertContains(rendered, "REDACTED")
        assertContains(rendered, "COMMON_MAIN_POLICY")
        assertContains(rendered, "WORKING_PARSER_ADMISSION_ONLY")
        assertContains(rendered, "NO_PARSER_EXECUTION")
        assertContains(rendered, "NO_IO")
        assertContains(rendered, "NO_CRYPTO_AUTH_EXECUTION")
        assertContains(rendered, "DISABLED_PROVIDER_ONLY")
        forbiddenOutputText.forEach { forbidden ->
            assertFalse(
                forbidden in lowerRendered,
                "Working parser admission output must not contain $forbidden: $rendered",
            )
        }
        assertFalse(Regex("\\b[0-9a-fA-F]{64}\\b").containsMatchIn(rendered))
        assertFalse(
            Regex("\\b(?:bc1|tb1|bcrt1)[a-z0-9]{20,}\\b", RegexOption.IGNORE_CASE)
                .containsMatchIn(rendered),
        )
        admission.policyLabels.map { it.safeLabel.value }
            .plus(admission.errorTaxonomyLabels.map { it.safeLabel.value })
            .plus(admission.displayLabel.value)
            .forEach(::assertSafePolicyLabel)
        assertTrue(admission.diagnosticsPolicyContainsNoSensitiveMaterial)
        assertEquals(
            EncryptedVaultWorkingParserAdmissionPolicyLabel.entries.toSet(),
            admission.policyLabels.toSet(),
        )
    }

    @Test
    fun sourceMaterialGuardCorpusBoundariesRemainExcluded() {
        val admission = admission()

        assertTrue(admission.normalSourceMaterialGuardExcludesBuildHistory)
        assertTrue(admission.localArtifactRootExcludedFromNormalSourceMaterialCorpus)
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

    private companion object {
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
