package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.EncryptedVaultContainerFormatV1DecisionPolicy
import com.libertasprimordium.skald.security.EncryptedVaultMigrationCorruptionPolicyDecisionPolicy
import com.libertasprimordium.skald.security.EncryptedVaultParserWriterAdmissionGatePolicy
import com.libertasprimordium.skald.security.EncryptedVaultParserWriterSyntheticVectorClass
import com.libertasprimordium.skald.security.EncryptedVaultParserWriterTestVectorAdmissionCheck
import com.libertasprimordium.skald.security.EncryptedVaultParserWriterTestVectorAdmissionKind
import com.libertasprimordium.skald.security.EncryptedVaultParserWriterTestVectorAdmissionPolicy
import com.libertasprimordium.skald.security.EncryptedVaultParserWriterTestVectorAdmissionPolicyLabel
import com.libertasprimordium.skald.security.EncryptedVaultParserWriterTestVectorAdmissionSourceSet
import com.libertasprimordium.skald.security.EncryptedVaultStoragePathSessionLifecycleDecisionPolicy
import com.libertasprimordium.skald.security.EncryptedVaultStorageReadinessDecisionPolicy
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionDecision
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class EncryptedVaultParserWriterTestVectorAdmissionTest {
    private fun admission() =
        EncryptedVaultParserWriterTestVectorAdmissionPolicy
            .currentParserWriterTestVectorAdmission()

    @Test
    fun parserWriterTestVectorAdmissionExistsAsCommonMainPolicyOnly() {
        val admission = admission()

        assertEquals(1, admission.admissionVersion)
        assertEquals(
            EncryptedVaultParserWriterTestVectorAdmissionKind
                .EncryptedLocalVaultParserWriterTestVectorAdmission,
            admission.admissionKind,
        )
        assertEquals(
            "ENCRYPTED_LOCAL_VAULT_PARSER_WRITER_TEST_VECTOR_ADMISSION",
            admission.admissionKind.label,
        )
        assertEquals(
            EncryptedVaultParserWriterTestVectorAdmissionSourceSet.CommonMainPolicy,
            admission.sourceSet,
        )
        assertEquals("COMMON_MAIN_POLICY", admission.sourceSet.label)
        assertTrue(admission.parserWriterTestVectorAdmissionPassed)
        assertTrue(admission.parserWriterTestVectorAdmissionDecisionPassed)
        assertEquals(0, admission.failureLabels.size)
        assertEquals(0, admission.blockerCount)
        assertEquals(0, admission.warningCount)
        assertEquals(
            EncryptedVaultParserWriterTestVectorAdmissionCheck.entries.size,
            admission.admissionCheckCount,
        )
        EncryptedVaultParserWriterTestVectorAdmissionCheck.entries.forEach { check ->
            assertContains(admission.admissionChecks, check)
        }
    }

    @Test
    fun parserWriterTestVectorAdmissionReflectsPriorEvidence() {
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

        assertTrue(storageReadiness.storageReadinessDecisionPassed)
        assertTrue(containerDecision.containerFormatV1DecisionPassed)
        assertTrue(storagePathDecision.storagePathSessionLifecycleDecisionPassed)
        assertTrue(migrationDecision.migrationCorruptionPolicyDecisionPassed)
        assertTrue(parserWriterGate.parserWriterAdmissionGateDecisionPassed)
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
        assertEquals(storageReadiness.encryptedVaultDesignPresent, admission.encryptedVaultDesignPresent)
        assertEquals(containerDecision.cryptoDecisionPresent, admission.cryptoDecisionPresent)
        assertEquals(storagePathDecision.dependencyReviewPresent, admission.dependencyReviewPresent)
        assertEquals(parserWriterGate.providerBoundaryPresent, admission.providerBoundaryPresent)
        assertEquals(parserWriterGate.secureStorageBoundaryPresent, admission.secureStorageBoundaryPresent)
        assertEquals(
            parserWriterGate.secureMetadataBoundaryPresent,
            admission.secureMetadataBoundaryPresent,
        )
        assertEquals(11, admission.evidenceCount)
    }

    @Test
    fun parserWriterTestVectorAdmissionAdmitsOnlyFutureSeparateBranches() {
        val admission = admission()

        assertTrue(admission.parserWriterTestVectorAdmissionPassed)
        assertTrue(admission.futureSyntheticVectorCreationAdmitted)
        assertTrue(admission.futureInMemoryVectorExecutionAdmitted)
        assertTrue(admission.futureParserVectorCoverageAdmitted)
        assertTrue(admission.futureWriterVectorCoverageAdmitted)
        assertTrue(admission.futureRoundTripVectorCoverageAdmitted)
        assertTrue(admission.futureNegativeVectorCoverageAdmitted)
        assertTrue(admission.futureRedactedVectorDiagnosticsAdmitted)
        assertTrue(admission.futureDesktopParserWriterVectorExecutionRequired)
        assertTrue(admission.futureAndroidParserWriterVectorExecutionRequired)
        assertTrue(admission.futureParserImplementationRequiresSeparatePass)
        assertTrue(admission.futureWriterImplementationRequiresSeparatePass)
        assertTrue(admission.futureParserWriterKatExecutionRequiresSeparatePass)
        assertTrue(admission.futureVaultStorageRepositoryRequiresSeparatePass)
        assertTrue(admission.futureSecureStorageSuccessRequiresSeparatePass)
        assertTrue(admission.futureSecureMetadataSuccessRequiresSeparatePass)
        assertTrue(admission.futureProductionSyncRequiresSeparatePass)
        assertTrue(admission.futureProductionProviderSelectionRequiresSeparatePass)
        assertTrue(admission.parserWriterTestVectorAdmissionPassedIsLaterBranchOnly)
        assertTrue(admission.futureSyntheticVectorCreationAdmittedIsNotVectorCreation)
        assertTrue(admission.futureInMemoryVectorExecutionAdmittedIsNotVectorExecution)
        assertTrue(admission.futureParserVectorCoverageAdmittedIsNotParserImplementation)
        assertTrue(admission.futureWriterVectorCoverageAdmittedIsNotWriterImplementation)
        assertTrue(admission.futureRoundTripVectorCoverageAdmittedIsNotRoundTripExecution)
        assertTrue(admission.futureNegativeVectorCoverageAdmittedIsNotNegativeVectorExecution)
        assertTrue(admission.futureRedactedVectorDiagnosticsAdmittedIsNotDiagnosticsImplementation)
    }

    @Test
    fun parserWriterTestVectorAdmissionHasNoVectorsOrRuntimeSurfaces() {
        val admission = admission()

        assertFalse(admission.syntheticVectorBytesPresent)
        assertFalse(admission.inMemoryVectorBytesPresent)
        assertFalse(admission.productionVectorBytesPresent)
        assertFalse(admission.parserVectorExecutionPresent)
        assertFalse(admission.writerVectorExecutionPresent)
        assertFalse(admission.roundTripVectorExecutionPresent)
        assertFalse(admission.negativeVectorExecutionPresent)
        assertFalse(admission.vaultParserImplementationPresent)
        assertFalse(admission.vaultWriterImplementationPresent)
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
    fun parserWriterTestVectorAdmissionHasNoImplementationAuthorizations() {
        val admission = admission()

        assertFalse(admission.syntheticVectorCreationAuthorizationPresent)
        assertFalse(admission.inMemoryVectorExecutionAuthorizationPresent)
        assertFalse(admission.parserVectorExecutionAuthorizationPresent)
        assertFalse(admission.writerVectorExecutionAuthorizationPresent)
        assertFalse(admission.parserImplementationAuthorizationPresent)
        assertFalse(admission.writerImplementationAuthorizationPresent)
        assertFalse(admission.vaultContainerSerializationAuthorizationPresent)
        assertFalse(admission.vaultContainerParsingAuthorizationPresent)
        assertFalse(admission.vaultFileReadAuthorizationPresent)
        assertFalse(admission.vaultFileWriteAuthorizationPresent)
        assertFalse(admission.vaultFileDeleteAuthorizationPresent)
        assertFalse(admission.vaultDirectoryCreationAuthorizationPresent)
        assertFalse(admission.kdfExecutionAuthorizationPresent)
        assertFalse(admission.aeadExecutionAuthorizationPresent)
        assertFalse(admission.encryptionAuthorizationPresent)
        assertFalse(admission.decryptionAuthorizationPresent)
        assertFalse(admission.keyGenerationAuthorizationPresent)
        assertFalse(admission.nonceGenerationAuthorizationPresent)
        assertFalse(admission.tinkKeysetCreationAuthorizationPresent)
        assertFalse(admission.tinkKeysetPersistenceAuthorizationPresent)
        assertFalse(admission.migrationImplementationAuthorizationPresent)
        assertFalse(admission.corruptionDetectionAuthorizationPresent)
        assertFalse(admission.corruptionRepairAuthorizationPresent)
        assertFalse(admission.backupCreationAuthorizationPresent)
        assertFalse(admission.rollbackAuthorizationPresent)
        assertFalse(admission.atomicReplaceAuthorizationPresent)
        assertFalse(admission.partialWriteDetectionAuthorizationPresent)
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
        assertFalse(admission.vaultSessionImplementationAuthorizationPresent)
        assertFalse(admission.vaultUnlockAuthorizationPresent)
        assertFalse(admission.vaultLockAuthorizationPresent)
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
    fun testVectorPolicyKeepsFutureVectorsSyntheticAndTestSourceOnly() {
        val admission = admission()

        assertTrue(admission.noVectorsCreatedInThisBranch)
        assertTrue(admission.noParserWriterKatRunsInThisBranch)
        assertTrue(admission.testVectorPolicyCreatesNoVectorBytes)
        assertTrue(admission.testVectorPolicyCreatesNoParserInputBytes)
        assertTrue(admission.testVectorPolicyCreatesNoWriterOutputBytes)
        assertTrue(admission.testVectorPolicyExecutesNoKat)
        assertTrue(admission.futureVectorsSyntheticNonWalletNonUserNonNetworkNonSecret)
        assertTrue(admission.futureVectorsTestSourceOnly)
        assertTrue(admission.futureVectorsContainNoWalletOrInfrastructureMaterial)
        assertTrue(admission.futureVectorsMayUseSafeStructuralLabelsAndCounters)
        assertTrue(admission.futureVectorsAvoidProductionContinuousHex)
        assertTrue(admission.futureVectorBytesNotInProductionSourceByDefault)
        assertTrue(admission.futureVectorBytesNotInDisplayOutput)
        assertTrue(admission.futureVectorDiagnosticsSafeLabelOnly)
        assertTrue(admission.futureDesktopJvmVectorExecutionBeforeRepositorySuccess)
        assertTrue(admission.futureAndroidRuntimeVectorExecutionBeforeRepositorySuccess)
        assertTrue(admission.futureNegativeVectorsDistinguishFailureClasses)
        assertEquals(
            EncryptedVaultParserWriterSyntheticVectorClass.entries.toSet(),
            admission.syntheticVectorClasses.toSet(),
        )
        assertContains(
            admission.syntheticVectorClasses,
            EncryptedVaultParserWriterSyntheticVectorClass.MinimalHeaderOnlySyntheticVector,
        )
        assertContains(
            admission.syntheticVectorClasses,
            EncryptedVaultParserWriterSyntheticVectorClass.CorruptionSuspectedSyntheticVector,
        )
        assertEquals(
            EncryptedVaultParserWriterTestVectorAdmissionPolicyLabel.entries.toSet(),
            admission.policyLabels.toSet(),
        )
        assertContains(
            admission.policyLabels,
            EncryptedVaultParserWriterTestVectorAdmissionPolicyLabel
                .ParserWriterTestVectorAdmission,
        )
        assertContains(
            admission.policyLabels,
            EncryptedVaultParserWriterTestVectorAdmissionPolicyLabel
                .NoProductionVectorBytesPolicy,
        )
    }

    @Test
    fun displayAndDebugOutputAreRedactedOrSafeLabelOnly() {
        val admission = admission()
        val rendered = admission.toString()
        val lowerRendered = rendered.lowercase()

        assertContains(rendered, "REDACTED")
        assertContains(rendered, "COMMON_MAIN_POLICY")
        assertContains(rendered, "TEST_VECTOR_ADMISSION_ONLY")
        assertContains(rendered, "NO_VECTOR_CREATION")
        assertContains(rendered, "NO_IO")
        assertContains(rendered, "DISABLED_PROVIDER_ONLY")

        listOf(
            "public vector bytes",
            "public vector hex",
            "kat vector bytes",
            "kat vector hex",
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
                "Admission display output must not contain $forbidden: $rendered",
            )
        }

        admission.policyLabels.map { it.safeLabel.value }
            .plus(admission.syntheticVectorClasses.map { it.safeLabel.value })
            .plus(admission.displayLabel.value)
            .forEach(::assertSafePolicyLabel)
        assertTrue(admission.diagnosticsPolicyContainsNoSensitiveMaterial)
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
}
