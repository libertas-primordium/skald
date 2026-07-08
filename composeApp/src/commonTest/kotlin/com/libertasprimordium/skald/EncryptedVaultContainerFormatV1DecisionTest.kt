package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.EncryptedVaultContainerFormatV1AssociatedDataField
import com.libertasprimordium.skald.security.EncryptedVaultContainerFormatV1AssociatedDataForbiddenField
import com.libertasprimordium.skald.security.EncryptedVaultContainerFormatV1DecisionCheck
import com.libertasprimordium.skald.security.EncryptedVaultContainerFormatV1DecisionKind
import com.libertasprimordium.skald.security.EncryptedVaultContainerFormatV1DecisionPolicy
import com.libertasprimordium.skald.security.EncryptedVaultContainerFormatV1DecisionSourceSet
import com.libertasprimordium.skald.security.EncryptedVaultContainerFormatV1RecordClass
import com.libertasprimordium.skald.security.EncryptedVaultContainerFormatV1Section
import com.libertasprimordium.skald.security.EncryptedVaultStorageReadinessDecisionPolicy
import com.libertasprimordium.skald.security.VaultCryptoProviderCandidateId
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionDecision
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class EncryptedVaultContainerFormatV1DecisionTest {
    private fun decision() =
        EncryptedVaultContainerFormatV1DecisionPolicy.currentContainerFormatV1Decision()

    @Test
    fun containerFormatV1DecisionExistsAsCommonMainPolicyOnly() {
        val decision = decision()

        assertEquals(1, decision.decisionVersion)
        assertEquals(
            EncryptedVaultContainerFormatV1DecisionKind.EncryptedLocalVaultContainerFormatV1Decision,
            decision.decisionKind,
        )
        assertEquals(
            "ENCRYPTED_LOCAL_VAULT_CONTAINER_FORMAT_V1_DECISION",
            decision.decisionKind.label,
        )
        assertEquals(EncryptedVaultContainerFormatV1DecisionSourceSet.CommonMainPolicy, decision.sourceSet)
        assertEquals("COMMON_MAIN_POLICY", decision.sourceSet.label)
        assertTrue(decision.containerFormatV1DecisionPassed)
        assertEquals(0, decision.failureLabels.size)
        assertEquals(0, decision.blockerCount)
        assertEquals(0, decision.warningCount)
        assertEquals(EncryptedVaultContainerFormatV1DecisionCheck.entries.size, decision.decisionCheckCount)
        EncryptedVaultContainerFormatV1DecisionCheck.entries.forEach { check ->
            assertContains(decision.decisionChecks, check)
        }
    }

    @Test
    fun containerFormatV1DecisionReflectsReadinessAndProviderEvidence() {
        val decision = decision()
        val readiness = EncryptedVaultStorageReadinessDecisionPolicy.currentStorageReadinessDecision()

        assertTrue(readiness.storageReadinessDecisionPassed)
        assertTrue(decision.storageReadinessDecisionPresent)
        assertTrue(decision.storageReadinessDecisionAdmitted)
        assertEquals(readiness.encryptedVaultDesignPresent, decision.encryptedVaultDesignPresent)
        assertEquals(readiness.cryptoDecisionPresent, decision.cryptoDecisionPresent)
        assertEquals(readiness.dependencyReviewPresent, decision.dependencyReviewPresent)
        assertEquals(readiness.desktopDependencyKatPassed, decision.dependencyKatPassed)
        assertEquals(readiness.androidDependencyKatPassed, decision.androidRuntimeKatPassed)
        assertEquals(readiness.argon2idCalibrationProbePresent, decision.argon2idCalibrationProbePresent)
        assertEquals(readiness.disabledVaultCryptoProviderBoundaryPresent, decision.vaultCryptoProviderBoundaryPresent)
        assertEquals(
            readiness.providerSelectionValidationCompletionAuditPassed,
            decision.providerSelectionValidationCompletionAuditPresent,
        )
        assertEquals(10, decision.evidenceCount)
    }

    @Test
    fun containerFormatV1DecisionAdmitsOnlyModelSectionsAndLaterBranches() {
        val decision = decision()
        val sectionLabels = decision.sections.associate { section -> section.section to section.safeLabel.value }

        assertTrue(decision.vaultContainerFormatV1DecisionAdmitted)
        assertTrue(decision.vaultContainerHeaderModelAdmitted)
        assertTrue(decision.vaultKdfSectionModelAdmitted)
        assertTrue(decision.vaultKeyEnvelopeSectionModelAdmitted)
        assertTrue(decision.vaultRecordDirectoryModelAdmitted)
        assertTrue(decision.vaultRecordEnvelopeModelAdmitted)
        assertTrue(decision.vaultAssociatedDataPolicyAdmitted)
        assertTrue(decision.vaultNoncePolicyAdmitted)
        assertTrue(decision.vaultRecordClassPolicyAdmitted)
        assertTrue(decision.vaultMigrationCorruptionPolicyAdmitted)
        assertTrue(decision.vaultBackupExportSeparationPolicyAdmitted)
        assertEquals("skald-vault-v1-header", sectionLabels[EncryptedVaultContainerFormatV1Section.Header])
        assertEquals("skald-vault-v1-kdf-section", sectionLabels[EncryptedVaultContainerFormatV1Section.Kdf])
        assertEquals(
            "skald-vault-v1-key-envelope-section",
            sectionLabels[EncryptedVaultContainerFormatV1Section.KeyEnvelope],
        )
        assertEquals(
            "skald-vault-v1-record-directory-section",
            sectionLabels[EncryptedVaultContainerFormatV1Section.RecordDirectory],
        )
        assertEquals(
            "skald-vault-v1-record-envelope-section",
            sectionLabels[EncryptedVaultContainerFormatV1Section.RecordEnvelope],
        )
        assertEquals(
            "skald-vault-v1-integrity-metadata-section",
            sectionLabels[EncryptedVaultContainerFormatV1Section.IntegrityMetadata],
        )
        decision.sections.forEach { section ->
            assertTrue(section.modelAdmitted)
            assertFalse(section.rawBytesPresent)
            assertFalse(section.serializationPresent)
            assertFalse(section.parsingPresent)
        }
        assertTrue(decision.futureVaultContainerParserRequiresSeparatePass)
        assertTrue(decision.futureVaultContainerWriterRequiresSeparatePass)
        assertTrue(decision.futureVaultStorageRepositoryRequiresSeparatePass)
        assertTrue(decision.futureSecureStorageSuccessRequiresSeparatePass)
        assertTrue(decision.futureSecureMetadataSuccessRequiresSeparatePass)
        assertTrue(decision.futureProductionSyncRequiresSeparatePass)
        assertTrue(decision.futureProductionProviderSelectionRequiresSeparatePass)
    }

    @Test
    fun containerFormatV1DecisionAdmittedLabelsAreNotImplementationOrExecution() {
        val decision = decision()

        assertTrue(decision.vaultContainerFormatV1DecisionAdmittedIsLaterBranchOnly)
        assertTrue(decision.vaultContainerHeaderModelAdmittedIsNotHeaderSerialization)
        assertTrue(decision.vaultKdfSectionModelAdmittedIsNotKdfExecution)
        assertTrue(decision.vaultKeyEnvelopeSectionModelAdmittedIsNotKeyWrappingOrUnwrapping)
        assertTrue(decision.vaultRecordDirectoryModelAdmittedIsNotRecordPersistence)
        assertTrue(decision.vaultRecordEnvelopeModelAdmittedIsNotEncryptionOrDecryption)
        assertTrue(decision.vaultAssociatedDataPolicyAdmittedIsNotRuntimeAssociatedDataConstruction)
        assertTrue(decision.vaultNoncePolicyAdmittedIsNotNonceGeneration)
        assertTrue(decision.vaultMigrationCorruptionPolicyAdmittedIsNotMigrationExecution)
        assertTrue(decision.vaultBackupExportSeparationPolicyAdmittedIsNotBackupExportImplementation)
    }

    @Test
    fun containerFormatV1DecisionKeepsParserWriterFileIoSerializationAndCryptoAbsent() {
        val decision = decision()

        assertFalse(decision.vaultContainerParserPresent)
        assertFalse(decision.vaultContainerWriterPresent)
        assertFalse(decision.vaultContainerSerializationPresent)
        assertFalse(decision.vaultContainerParsingPresent)
        assertFalse(decision.vaultContainerBytesProduced)
        assertFalse(decision.vaultFileReadPresent)
        assertFalse(decision.vaultFileWritePresent)
        assertFalse(decision.encryptedVaultFileFormatImplemented)
        assertFalse(decision.vaultContainerImplementationAuthorizationPresent)
        assertFalse(decision.vaultContainerParserAuthorizationPresent)
        assertFalse(decision.vaultContainerWriterAuthorizationPresent)
    }

    @Test
    fun containerFormatV1DecisionKeepsStoragePersistenceSyncProviderAndMainnetBlocked() {
        val decision = decision()
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertFalse(decision.encryptedVaultRepositorySuccessPresent)
        assertFalse(decision.secureSecretStorageSuccessPathPresent)
        assertFalse(decision.secureMetadataStorageSuccessPathPresent)
        assertFalse(decision.productionObservationPersistencePresent)
        assertFalse(decision.productionAddressIndexPersistencePresent)
        assertFalse(decision.productionUtxoPersistencePresent)
        assertFalse(decision.productionWalletHistoryPersistencePresent)
        assertFalse(decision.productionSyncPresent)
        assertFalse(decision.productionBackendClientPresent)
        assertFalse(decision.productionProviderSelectionEnabled)
        assertFalse(decision.productionProviderSelectable)
        assertTrue(decision.productionSelectionStillDisabledProviderOnly)
        assertFalse(decision.signingBroadcastingPresent)
        assertFalse(decision.uiActionEnablementPresent)
        assertFalse(decision.endpointPresent)
        assertFalse(decision.mainnetPresent)
        assertFalse(decision.productionStorageAuthorizationPresent)
        assertFalse(decision.productionSecretStorageAuthorizationPresent)
        assertFalse(decision.productionMetadataStorageAuthorizationPresent)
        assertFalse(decision.productionSyncAuthorizationPresent)
        assertFalse(decision.productionProviderSelectionAuthorizationPresent)
        assertFalse(decision.productionProviderImplementationAuthorizationPresent)
        assertFalse(decision.signingBroadcastingAuthorizationPresent)
        assertFalse(decision.uiAuthorizationPresent)
        assertFalse(decision.endpointAuthorizationPresent)
        assertFalse(decision.mainnetAuthorizationPresent)
        assertEquals(VaultCryptoProviderCandidateId.DisabledFailClosed, selection.selectedCandidateId)
        assertEquals(VaultCryptoProviderSelectionDecision.DisabledProviderSelected, selection.decision)
        assertTrue(selection.selectedProviderIsDisabled)
        assertTrue(selection.selectedProvider is DisabledVaultCryptoProvider)
        assertFalse(selection.productionProviderSelectable)
    }

    @Test
    fun containerFormatV1DecisionRecordClassesAreSafeEnumLabelsOnly() {
        val decision = decision()
        val requiredClasses = setOf(
            EncryptedVaultContainerFormatV1RecordClass.SecretPayloadRecord,
            EncryptedVaultContainerFormatV1RecordClass.SensitiveMetadataRecord,
            EncryptedVaultContainerFormatV1RecordClass.AddressIndexRecord,
            EncryptedVaultContainerFormatV1RecordClass.BackendObservationRecord,
            EncryptedVaultContainerFormatV1RecordClass.ObservedUtxoRecord,
            EncryptedVaultContainerFormatV1RecordClass.WalletHistoryRecord,
            EncryptedVaultContainerFormatV1RecordClass.RecoveryMetadataRecord,
            EncryptedVaultContainerFormatV1RecordClass.PrivacyAnalyzerMetadataRecord,
            EncryptedVaultContainerFormatV1RecordClass.LabelRecord,
            EncryptedVaultContainerFormatV1RecordClass.TransactionNoteRecord,
            EncryptedVaultContainerFormatV1RecordClass.BackendMetadataRecord,
            EncryptedVaultContainerFormatV1RecordClass.TorRoutingMetadataRecord,
            EncryptedVaultContainerFormatV1RecordClass.NostrIdentityLinkageMetadataRecord,
            EncryptedVaultContainerFormatV1RecordClass.BackupExportManifestRecord,
        )
        val labels = decision.recordClasses.map { recordClass -> recordClass.label.value }

        assertEquals(requiredClasses, decision.recordClasses.toSet())
        assertEquals(14, decision.recordClasses.size)
        assertTrue(decision.recordClassesSafeEnumLabelsOnly)
        labels.forEach { label ->
            assertFalse(Regex("[0-9a-fA-F]{32,}").containsMatchIn(label))
            assertFalse(Regex("(?i)\\b(?:bc1|tb1|bcrt1)[a-z0-9]{20,}\\b").containsMatchIn(label))
            assertFalse(Regex("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-").containsMatchIn(label))
            assertFalse(label.contains("wpkh(", ignoreCase = true))
            assertFalse(label.contains("tr(", ignoreCase = true))
        }
    }

    @Test
    fun containerFormatV1DecisionAssociatedDataPolicyExcludesSensitiveWalletMetadata() {
        val decision = decision()

        assertTrue(decision.futureAssociatedDataContainsOnlyNonSecretStructuralContext)
        assertTrue(decision.futureAssociatedDataExcludesSensitiveWalletMetadata)
        assertContains(
            decision.associatedDataAllowedFields,
            EncryptedVaultContainerFormatV1AssociatedDataField.FormatVersionLabel,
        )
        assertContains(
            decision.associatedDataAllowedFields,
            EncryptedVaultContainerFormatV1AssociatedDataField.RecordPurposeLabel,
        )
        assertContains(
            decision.associatedDataAllowedFields,
            EncryptedVaultContainerFormatV1AssociatedDataField.AlgorithmSuiteLabel,
        )
        assertContains(
            decision.associatedDataAllowedFields,
            EncryptedVaultContainerFormatV1AssociatedDataField.MigrationVersionLabel,
        )
        assertContains(
            decision.associatedDataAllowedFields,
            EncryptedVaultContainerFormatV1AssociatedDataField.CanonicalRecordClassLabel,
        )
        assertContains(
            decision.associatedDataForbiddenFields,
            EncryptedVaultContainerFormatV1AssociatedDataForbiddenField.WalletNames,
        )
        assertContains(
            decision.associatedDataForbiddenFields,
            EncryptedVaultContainerFormatV1AssociatedDataForbiddenField.Labels,
        )
        assertContains(
            decision.associatedDataForbiddenFields,
            EncryptedVaultContainerFormatV1AssociatedDataForbiddenField.TransactionNotes,
        )
        assertContains(
            decision.associatedDataForbiddenFields,
            EncryptedVaultContainerFormatV1AssociatedDataForbiddenField.EndpointValues,
        )
        assertContains(
            decision.associatedDataForbiddenFields,
            EncryptedVaultContainerFormatV1AssociatedDataForbiddenField.Descriptors,
        )
        assertContains(
            decision.associatedDataForbiddenFields,
            EncryptedVaultContainerFormatV1AssociatedDataForbiddenField.Addresses,
        )
        assertContains(
            decision.associatedDataForbiddenFields,
            EncryptedVaultContainerFormatV1AssociatedDataForbiddenField.Txids,
        )
        assertContains(
            decision.associatedDataForbiddenFields,
            EncryptedVaultContainerFormatV1AssociatedDataForbiddenField.Psbts,
        )
        assertContains(
            decision.associatedDataForbiddenFields,
            EncryptedVaultContainerFormatV1AssociatedDataForbiddenField.NostrIdentifiersOrSecretKeys,
        )
        assertContains(
            decision.associatedDataForbiddenFields,
            EncryptedVaultContainerFormatV1AssociatedDataForbiddenField.LightningCredentials,
        )
        assertContains(
            decision.associatedDataForbiddenFields,
            EncryptedVaultContainerFormatV1AssociatedDataForbiddenField.CashuProofs,
        )
        assertContains(
            decision.associatedDataForbiddenFields,
            EncryptedVaultContainerFormatV1AssociatedDataForbiddenField.BackendCredentials,
        )
        assertContains(
            decision.associatedDataForbiddenFields,
            EncryptedVaultContainerFormatV1AssociatedDataForbiddenField.FilesystemPaths,
        )
        assertContains(
            decision.associatedDataForbiddenFields,
            EncryptedVaultContainerFormatV1AssociatedDataForbiddenField.SourceLocations,
        )
        assertContains(
            decision.associatedDataForbiddenFields,
            EncryptedVaultContainerFormatV1AssociatedDataForbiddenField.StackTraces,
        )
    }

    @Test
    fun containerFormatV1DecisionNoncePolicyRequiresFutureRandomXChaChaRecordValues() {
        val decision = decision()

        assertTrue(decision.futureXChaChaRecordNonceRequiresRandom24BytePerRecord)
        assertFalse(decision.nonceGenerationPresent)
        assertFalse(decision.callerProvidedProductionNonceSupportPresent)
        assertTrue(decision.existingExplicitNonceKatApiRemainsTestProbeOnly)
    }

    @Test
    fun containerFormatV1DecisionKeepsSourceMaterialCorpusBoundaries() {
        val decision = decision()

        assertTrue(decision.normalSourceMaterialGuardExcludesBuildHistory)
        assertTrue(decision.localArtifactRootExcludedFromNormalSourceMaterialCorpus)
    }

    @Test
    fun containerFormatV1DecisionOutputIsRedactedAndMaterialFree() {
        val decision = decision()
        val output = listOf(
            decision.toString(),
            decision.decisionId.toString(),
            decision.displayLabel.toString(),
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
            "wpkh(",
            "tr(",
            "xpub",
            "psbt",
        )

        assertContains(output, "REDACTED")
        assertContains(output, "COMMON_MAIN_POLICY")
        assertContains(output, "CONTAINER_FORMAT_V1_DECISION_ONLY")
        assertContains(output, "LATER_BRANCHES_ONLY")
        assertContains(output, "MODEL_LABELS_ONLY")
        assertContains(output, "NO_CONTAINER_IO")
        assertContains(output, "NO_SERIALIZATION_PARSING")
        assertContains(output, "DISABLED_PROVIDER_ONLY")
        forbiddenText.forEach { forbidden ->
            assertFalse(output.contains(forbidden, ignoreCase = true), "Output leaked forbidden text: $forbidden")
        }
        assertFalse(Regex("\\b[0-9a-fA-F]{64}\\b").containsMatchIn(output))
        assertFalse(Regex("\\b(?:bc1|tb1|bcrt1)[a-z0-9]{20,}\\b", RegexOption.IGNORE_CASE).containsMatchIn(output))
    }
}
