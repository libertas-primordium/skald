package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.EncryptedVaultContainerFormatV1DecisionPolicy
import com.libertasprimordium.skald.security.EncryptedVaultMigrationCorruptionPolicyDecisionPolicy
import com.libertasprimordium.skald.security.EncryptedVaultParserWriterAdmissionCheck
import com.libertasprimordium.skald.security.EncryptedVaultParserWriterAdmissionGatePolicy
import com.libertasprimordium.skald.security.EncryptedVaultParserWriterAdmissionKind
import com.libertasprimordium.skald.security.EncryptedVaultParserWriterAdmissionPolicyLabel
import com.libertasprimordium.skald.security.EncryptedVaultParserWriterAdmissionSourceSet
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

class EncryptedVaultParserWriterAdmissionGateTest {
    private fun gate() =
        EncryptedVaultParserWriterAdmissionGatePolicy.currentParserWriterAdmissionGate()

    @Test
    fun parserWriterAdmissionGateExistsAsCommonMainPolicyOnly() {
        val gate = gate()

        assertEquals(1, gate.admissionVersion)
        assertEquals(
            EncryptedVaultParserWriterAdmissionKind.EncryptedLocalVaultParserWriterAdmissionGate,
            gate.admissionKind,
        )
        assertEquals(
            "ENCRYPTED_LOCAL_VAULT_PARSER_WRITER_ADMISSION_GATE",
            gate.admissionKind.label,
        )
        assertEquals(EncryptedVaultParserWriterAdmissionSourceSet.CommonMainPolicy, gate.sourceSet)
        assertEquals("COMMON_MAIN_POLICY", gate.sourceSet.label)
        assertTrue(gate.vaultParserWriterAdmissionGatePassed)
        assertTrue(gate.parserWriterAdmissionGateDecisionPassed)
        assertEquals(0, gate.failureLabels.size)
        assertEquals(0, gate.blockerCount)
        assertEquals(0, gate.warningCount)
        assertEquals(EncryptedVaultParserWriterAdmissionCheck.entries.size, gate.admissionCheckCount)
        EncryptedVaultParserWriterAdmissionCheck.entries.forEach { check ->
            assertContains(gate.admissionChecks, check)
        }
    }

    @Test
    fun parserWriterAdmissionGateReflectsPriorEvidence() {
        val gate = gate()
        val storageReadiness = EncryptedVaultStorageReadinessDecisionPolicy.currentStorageReadinessDecision()
        val containerDecision = EncryptedVaultContainerFormatV1DecisionPolicy.currentContainerFormatV1Decision()
        val storagePathDecision =
            EncryptedVaultStoragePathSessionLifecycleDecisionPolicy
                .currentStoragePathSessionLifecycleDecision()
        val migrationDecision =
            EncryptedVaultMigrationCorruptionPolicyDecisionPolicy
                .currentMigrationCorruptionPolicyDecision()

        assertTrue(storageReadiness.storageReadinessDecisionPassed)
        assertTrue(containerDecision.containerFormatV1DecisionPassed)
        assertTrue(storagePathDecision.storagePathSessionLifecycleDecisionPassed)
        assertTrue(migrationDecision.migrationCorruptionPolicyDecisionPassed)
        assertEquals(storageReadiness.storageReadinessDecisionPassed, gate.storageReadinessDecisionPresent)
        assertEquals(containerDecision.containerFormatV1DecisionPassed, gate.containerFormatV1DecisionPresent)
        assertEquals(
            storagePathDecision.storagePathSessionLifecycleDecisionPassed,
            gate.storagePathSessionLifecycleDecisionPresent,
        )
        assertEquals(
            migrationDecision.migrationCorruptionPolicyDecisionPassed,
            gate.migrationCorruptionPolicyDecisionPresent,
        )
        assertEquals(storageReadiness.encryptedVaultDesignPresent, gate.encryptedVaultDesignPresent)
        assertEquals(containerDecision.cryptoDecisionPresent, gate.cryptoDecisionPresent)
        assertEquals(storagePathDecision.dependencyReviewPresent, gate.dependencyReviewPresent)
        assertEquals(migrationDecision.providerBoundaryPresent, gate.providerBoundaryPresent)
        assertEquals(migrationDecision.secureStorageBoundaryPresent, gate.secureStorageBoundaryPresent)
        assertEquals(migrationDecision.secureMetadataBoundaryPresent, gate.secureMetadataBoundaryPresent)
        assertEquals(10, gate.evidenceCount)
    }

    @Test
    fun parserWriterAdmissionGateAdmitsOnlyPolicyLabelsAndLaterBranches() {
        val gate = gate()
        val labels = gate.policyLabels.associate { label -> label to label.safeLabel.value }

        assertTrue(gate.vaultParserWriterAdmissionGatePassed)
        assertTrue(gate.vaultParserImplementationPathAdmitted)
        assertTrue(gate.vaultWriterImplementationPathAdmitted)
        assertTrue(gate.canonicalSerializationPolicyAdmitted)
        assertTrue(gate.authenticateBeforeParsePolicyAdmitted)
        assertTrue(gate.redactedParserErrorPolicyAdmitted)
        assertTrue(gate.redactedWriterErrorPolicyAdmitted)
        assertTrue(gate.noPayloadDiagnosticsPolicyAdmitted)
        assertTrue(gate.parserKatRequirementAdmitted)
        assertTrue(gate.writerKatRequirementAdmitted)
        assertEquals(
            "skald-encrypted-local-vault-parser-writer-admission-v1",
            labels[EncryptedVaultParserWriterAdmissionPolicyLabel.ParserWriterAdmission],
        )
        assertEquals(
            "skald-vault-v1-parser-admission-policy",
            labels[EncryptedVaultParserWriterAdmissionPolicyLabel.ParserAdmissionPolicy],
        )
        assertEquals(
            "skald-vault-v1-writer-admission-policy",
            labels[EncryptedVaultParserWriterAdmissionPolicyLabel.WriterAdmissionPolicy],
        )
        assertEquals(
            "skald-vault-v1-canonical-serialization-policy",
            labels[EncryptedVaultParserWriterAdmissionPolicyLabel.CanonicalSerializationPolicy],
        )
        assertEquals(
            "skald-vault-v1-authenticate-before-parse-policy",
            labels[EncryptedVaultParserWriterAdmissionPolicyLabel.AuthenticateBeforeParsePolicy],
        )
        assertEquals(
            "skald-vault-v1-no-payload-diagnostics-policy",
            labels[EncryptedVaultParserWriterAdmissionPolicyLabel.NoPayloadDiagnosticsPolicy],
        )
        assertTrue(gate.futureVaultParserImplementationRequiresSeparatePass)
        assertTrue(gate.futureVaultWriterImplementationRequiresSeparatePass)
        assertTrue(gate.futureParserWriterKatExecutionRequiresSeparatePass)
        assertTrue(gate.futureVaultStorageRepositoryRequiresSeparatePass)
        assertTrue(gate.futureSecureStorageSuccessRequiresSeparatePass)
        assertTrue(gate.futureSecureMetadataSuccessRequiresSeparatePass)
        assertTrue(gate.futureProductionSyncRequiresSeparatePass)
        assertTrue(gate.futureProductionProviderSelectionRequiresSeparatePass)
    }

    @Test
    fun admittedParserWriterLabelsAreNotImplementationsOrKatExecution() {
        val gate = gate()

        assertTrue(gate.vaultParserWriterAdmissionGatePassedIsLaterBranchOnly)
        assertTrue(gate.vaultParserImplementationPathAdmittedIsNotParserImplementation)
        assertTrue(gate.vaultWriterImplementationPathAdmittedIsNotWriterImplementation)
        assertTrue(gate.canonicalSerializationPolicyAdmittedIsNotSerialization)
        assertTrue(gate.authenticateBeforeParsePolicyAdmittedIsNotAuthenticatedParserImplementation)
        assertTrue(gate.redactedParserErrorPolicyAdmittedIsNotParserDiagnosticsImplementation)
        assertTrue(gate.redactedWriterErrorPolicyAdmittedIsNotWriterDiagnosticsImplementation)
        assertTrue(gate.parserKatRequirementAdmittedIsNotParserKatExecution)
        assertTrue(gate.writerKatRequirementAdmittedIsNotWriterKatExecution)
    }

    @Test
    fun parserAdmissionCreatesNoParserObjectAndConsumesNoVaultBytes() {
        val gate = gate()

        assertFalse(gate.vaultParserImplementationPresent)
        assertFalse(gate.vaultContainerParsingPresent)
        assertFalse(gate.vaultContainerBytesConsumed)
        assertFalse(gate.vaultHeaderParsed)
        assertFalse(gate.vaultRecordDirectoryParsed)
        assertFalse(gate.vaultRecordEnvelopeParsed)
        assertTrue(gate.futureParserAuthenticatesBeforeTrustingRecordMetadata)
        assertTrue(gate.futureParserFailsClosedOnUnsupportedCriticalFeatures)
        assertTrue(gate.futureParserFailsClosedOnUnknownUnsupportedVersions)
        assertTrue(gate.futureParserDistinguishesFailureClasses)
        assertTrue(gate.futureParserDiagnosticsRedactedSafeLabelsOnly)
        assertTrue(gate.futureParserDiagnosticsExposeNoMaterial)
        assertTrue(gate.noParserInThisBranch)
        assertTrue(gate.noVaultBytesConsumedInThisBranch)
        assertTrue(gate.parserPolicyCreatesNoParserObject)
    }

    @Test
    fun writerAdmissionCreatesNoWriterObjectAndProducesNoVaultBytes() {
        val gate = gate()

        assertFalse(gate.vaultWriterImplementationPresent)
        assertFalse(gate.vaultContainerSerializationPresent)
        assertFalse(gate.vaultContainerBytesProduced)
        assertFalse(gate.vaultHeaderSerialized)
        assertFalse(gate.vaultRecordDirectorySerialized)
        assertFalse(gate.vaultRecordEnvelopeSerialized)
        assertTrue(gate.futureWriterCanonicalDeterministicStructuralSerialization)
        assertTrue(gate.futureWriterUsesRandomizedPerRecordNoncePolicyForEncryption)
        assertTrue(gate.futureWriterDoesNotWriteUnauthenticatedSensitiveMetadata)
        assertTrue(gate.futureWriterDoesNotSerializePlaintextSecretsOrMetadata)
        assertTrue(gate.futureWriterDistinguishesFailureClasses)
        assertTrue(gate.futureWriterDiagnosticsRedactedSafeLabelsOnly)
        assertTrue(gate.futureWriterDiagnosticsExposeNoMaterial)
        assertTrue(gate.noWriterInThisBranch)
        assertTrue(gate.noVaultBytesProducedInThisBranch)
        assertTrue(gate.writerPolicyCreatesNoWriterObject)
    }

    @Test
    fun parserWriterKatPolicyCreatesNoVectorBytesAndExecutesNoKat() {
        val gate = gate()

        assertTrue(gate.futureParserWriterKatRequiresPublicSyntheticVectors)
        assertTrue(gate.futureParserWriterVectorsTestSourceOnlyByDefault)
        assertTrue(gate.futureParserWriterVectorsContainNoWalletMaterial)
        assertTrue(gate.futureParserWriterVectorsAvoidProductionContinuousHex)
        assertTrue(gate.futureParserWriterKatExecutionRequiresSeparatePass)
        assertTrue(gate.noParserWriterKatRunsInThisBranch)
        assertTrue(gate.parserWriterKatPolicyCreatesNoVectorBytes)
        assertTrue(gate.parserWriterKatPolicyExecutesNoKat)
    }

    @Test
    fun parserWriterAdmissionKeepsFileStorageMigrationAndCorruptionRuntimeAbsent() {
        val gate = gate()

        assertFalse(gate.vaultFileReadPresent)
        assertFalse(gate.vaultFileWritePresent)
        assertFalse(gate.vaultFileDeletePresent)
        assertFalse(gate.vaultDirectoryCreated)
        assertFalse(gate.atomicReplaceImplementationPresent)
        assertFalse(gate.partialWriteDetectionImplementationPresent)
        assertFalse(gate.migrationImplementationPresent)
        assertFalse(gate.migrationExecutionPresent)
        assertFalse(gate.corruptionDetectionImplementationPresent)
        assertFalse(gate.corruptionRepairImplementationPresent)
        assertFalse(gate.backupCreationPresent)
        assertFalse(gate.rollbackImplementationPresent)
        assertFalse(gate.vaultStoragePathImplementationPresent)
        assertFalse(gate.encryptedVaultFileFormatImplemented)
        assertFalse(gate.encryptedVaultRepositorySuccessPresent)
    }

    @Test
    fun parserWriterAdmissionKeepsCryptoKeyNonceAndTinkRuntimeAbsent() {
        val gate = gate()

        assertFalse(gate.kdfExecutionPresent)
        assertFalse(gate.aeadExecutionPresent)
        assertFalse(gate.encryptionExecutionPresent)
        assertFalse(gate.decryptionExecutionPresent)
        assertFalse(gate.keyGenerationPresent)
        assertFalse(gate.nonceGenerationPresent)
        assertFalse(gate.tinkKeysetCreationPresent)
        assertFalse(gate.tinkKeysetPersistencePresent)
    }

    @Test
    fun parserWriterAdmissionKeepsUnlockLockRuntimeSessionAndCachesAbsent() {
        val gate = gate()

        assertFalse(gate.lockSessionImplementationPresent)
        assertFalse(gate.unlockImplementationPresent)
        assertFalse(gate.runtimeSessionKeyPresent)
        assertFalse(gate.sessionKeyCached)
        assertFalse(gate.plaintextCachePresent)
    }

    @Test
    fun parserWriterAdmissionKeepsPersistenceSyncProviderAndMainnetBlocked() {
        val gate = gate()

        assertFalse(gate.secureSecretStorageSuccessPathPresent)
        assertFalse(gate.secureMetadataStorageSuccessPathPresent)
        assertFalse(gate.productionObservationPersistencePresent)
        assertFalse(gate.productionAddressIndexPersistencePresent)
        assertFalse(gate.productionUtxoPersistencePresent)
        assertFalse(gate.productionWalletHistoryPersistencePresent)
        assertFalse(gate.productionSyncPresent)
        assertFalse(gate.productionBackendClientPresent)
        assertFalse(gate.productionProviderSelectionEnabled)
        assertFalse(gate.productionProviderSelectable)
        assertTrue(gate.productionSelectionStillDisabledProviderOnly)
        assertFalse(gate.signingBroadcastingPresent)
        assertFalse(gate.uiActionEnablementPresent)
        assertFalse(gate.endpointPresent)
        assertFalse(gate.mainnetPresent)
    }

    @Test
    fun parserWriterAdmissionKeepsAllProductionAuthorizationsAbsent() {
        val gate = gate()

        assertFalse(gate.vaultParserImplementationAuthorizationPresent)
        assertFalse(gate.vaultWriterImplementationAuthorizationPresent)
        assertFalse(gate.vaultContainerSerializationAuthorizationPresent)
        assertFalse(gate.vaultContainerParsingAuthorizationPresent)
        assertFalse(gate.vaultFileReadAuthorizationPresent)
        assertFalse(gate.vaultFileWriteAuthorizationPresent)
        assertFalse(gate.vaultFileDeleteAuthorizationPresent)
        assertFalse(gate.vaultDirectoryCreationAuthorizationPresent)
        assertFalse(gate.atomicReplaceAuthorizationPresent)
        assertFalse(gate.partialWriteDetectionAuthorizationPresent)
        assertFalse(gate.migrationImplementationAuthorizationPresent)
        assertFalse(gate.corruptionDetectionAuthorizationPresent)
        assertFalse(gate.corruptionRepairAuthorizationPresent)
        assertFalse(gate.backupCreationAuthorizationPresent)
        assertFalse(gate.rollbackAuthorizationPresent)
        assertFalse(gate.kdfExecutionAuthorizationPresent)
        assertFalse(gate.aeadExecutionAuthorizationPresent)
        assertFalse(gate.encryptionAuthorizationPresent)
        assertFalse(gate.decryptionAuthorizationPresent)
        assertFalse(gate.keyGenerationAuthorizationPresent)
        assertFalse(gate.nonceGenerationAuthorizationPresent)
        assertFalse(gate.tinkKeysetCreationAuthorizationPresent)
        assertFalse(gate.tinkKeysetPersistenceAuthorizationPresent)
        assertFalse(gate.vaultSessionImplementationAuthorizationPresent)
        assertFalse(gate.vaultUnlockAuthorizationPresent)
        assertFalse(gate.vaultLockAuthorizationPresent)
        assertFalse(gate.productionStorageAuthorizationPresent)
        assertFalse(gate.productionSecretStorageAuthorizationPresent)
        assertFalse(gate.productionMetadataStorageAuthorizationPresent)
        assertFalse(gate.productionSyncAuthorizationPresent)
        assertFalse(gate.productionProviderSelectionAuthorizationPresent)
        assertFalse(gate.productionProviderImplementationAuthorizationPresent)
        assertFalse(gate.signingBroadcastingAuthorizationPresent)
        assertFalse(gate.uiAuthorizationPresent)
        assertFalse(gate.endpointAuthorizationPresent)
        assertFalse(gate.mainnetAuthorizationPresent)
    }

    @Test
    fun productionProviderSelectionStillResolvesOnlyToDisabledProvider() {
        val gate = gate()
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertFalse(gate.productionProviderSelectionEnabled)
        assertFalse(gate.productionProviderSelectable)
        assertTrue(gate.productionSelectionStillDisabledProviderOnly)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertEquals(VaultCryptoProviderSelectionDecision.DisabledProviderSelected, selection.decision)
        assertTrue(selection.selectedProviderIsDisabled)
        assertTrue(selection.selectedProvider is DisabledVaultCryptoProvider)
        assertFalse(selection.productionProviderSelectable)
    }

    @Test
    fun parserWriterAdmissionLabelsContainNoSensitiveMaterialOrStorageStrings() {
        val gate = gate()

        gate.policyLabels.map { label -> label.safeLabel.value }.forEach { label ->
            assertFalse("/" in label, "Policy label must not contain path separator: $label")
            assertFalse("\\" in label, "Policy label must not contain path separator: $label")
            assertFalse("." in label, "Policy label must not contain file extension separator: $label")
            assertFalse("~" in label, "Policy label must not contain home shorthand: $label")
            assertFalse(label.contains("path", ignoreCase = true))
            assertFalse(label.contains("filename", ignoreCase = true))
            assertFalse(label.contains("database", ignoreCase = true))
            assertFalse(label.contains("SharedPreferences", ignoreCase = true))
            assertFalse(label.contains("checksum", ignoreCase = true))
            assertFalse(label.contains("mac", ignoreCase = true))
            assertFalse(label.contains("hash", ignoreCase = true))
            assertFalse(label.contains("stack", ignoreCase = true))
            assertFalse(label.contains("source", ignoreCase = true))
            assertFalse(label.contains("raw", ignoreCase = true))
            assertFalse(Regex("\\b[0-9a-fA-F]{32,}\\b").containsMatchIn(label))
        }
        assertTrue(gate.diagnosticsPolicyContainsNoSensitiveMaterial)
    }

    @Test
    fun parserWriterAdmissionKeepsSourceMaterialCorpusBoundaries() {
        val gate = gate()

        assertTrue(gate.normalSourceMaterialGuardExcludesBuildHistory)
        assertTrue(gate.localArtifactRootExcludedFromNormalSourceMaterialCorpus)
    }

    @Test
    fun parserWriterAdmissionOutputIsRedactedAndMaterialFree() {
        val gate = gate()
        val output = listOf(
            gate.toString(),
            gate.admissionId.toString(),
            gate.displayLabel.toString(),
        ).joinToString(separator = " ")
        val forbiddenText = listOf(
            "ByteArray",
            "UByteArray",
            "CharArray",
            "passphrase",
            "mnemonic",
            "seed phrase",
            "private key",
            "xprv",
            "tprv",
            "WIF",
            "nsec",
            "ciphertext",
            "plaintext",
            "salt",
            "nonce",
            "tag",
            "mac",
            "hash",
            "key material",
            "provider handle",
            "source location",
            "stack trace",
            "diagnostics payload",
            "analytics payload",
            "crash-report payload",
            "support-export payload",
            "endpoint value",
            "filesystem path",
            "public vector bytes",
            "public vector hex",
            "storage key",
            "directory name",
            "file name",
            "migration payload",
            "corruption hash",
            "repair diagnostics",
            "parser target",
            "writer output",
            "vault bytes",
            "wpkh(",
            "tr(",
            "xpub",
            "psbt",
        )

        assertContains(output, "REDACTED")
        assertContains(output, "COMMON_MAIN_POLICY")
        assertContains(output, "PARSER_WRITER_ADMISSION_GATE_ONLY")
        assertContains(output, "LATER_BRANCHES_ONLY")
        assertContains(output, "POLICY_LABELS_ONLY")
        assertContains(output, "NO_PARSER")
        assertContains(output, "NO_WRITER")
        assertContains(output, "NO_SERIALIZATION_PARSING")
        assertContains(output, "NO_FILE_IO")
        assertContains(output, "NO_CRYPTO_EXECUTION")
        assertContains(output, "DISABLED_PROVIDER_ONLY")
        forbiddenText.forEach { forbidden ->
            assertFalse(output.contains(forbidden, ignoreCase = true), "Output leaked forbidden text: $forbidden")
        }
        assertFalse(Regex("\\b[0-9a-fA-F]{64}\\b").containsMatchIn(output))
        assertFalse(Regex("\\b(?:bc1|tb1|bcrt1)[a-z0-9]{20,}\\b", RegexOption.IGNORE_CASE).containsMatchIn(output))
        assertFalse(Regex("""(?:^|\s)/(?:[A-Za-z0-9._-]+/?)+""").containsMatchIn(output))
    }
}
