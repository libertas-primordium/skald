package com.libertasprimordium.skald.security

@JvmInline
value class EncryptedVaultContainerFormatV1SafeLabel(val value: String) {
    override fun toString(): String = "RedactedEncryptedVaultContainerFormatV1SafeLabel"
}

enum class EncryptedVaultContainerFormatV1DecisionKind(val label: String) {
    EncryptedLocalVaultContainerFormatV1Decision("ENCRYPTED_LOCAL_VAULT_CONTAINER_FORMAT_V1_DECISION"),
}

enum class EncryptedVaultContainerFormatV1DecisionSourceSet(val label: String) {
    CommonMainPolicy("COMMON_MAIN_POLICY"),
}

enum class EncryptedVaultContainerFormatV1Section(val label: EncryptedVaultContainerFormatV1SafeLabel) {
    Header(EncryptedVaultContainerFormatV1SafeLabel("skald-vault-v1-header")),
    Kdf(EncryptedVaultContainerFormatV1SafeLabel("skald-vault-v1-kdf-section")),
    KeyEnvelope(EncryptedVaultContainerFormatV1SafeLabel("skald-vault-v1-key-envelope-section")),
    RecordDirectory(EncryptedVaultContainerFormatV1SafeLabel("skald-vault-v1-record-directory-section")),
    RecordEnvelope(EncryptedVaultContainerFormatV1SafeLabel("skald-vault-v1-record-envelope-section")),
    IntegrityMetadata(EncryptedVaultContainerFormatV1SafeLabel("skald-vault-v1-integrity-metadata-section")),
}

enum class EncryptedVaultContainerFormatV1RecordClass(val label: EncryptedVaultContainerFormatV1SafeLabel) {
    SecretPayloadRecord(EncryptedVaultContainerFormatV1SafeLabel("SecretPayloadRecord")),
    SensitiveMetadataRecord(EncryptedVaultContainerFormatV1SafeLabel("SensitiveMetadataRecord")),
    AddressIndexRecord(EncryptedVaultContainerFormatV1SafeLabel("AddressIndexRecord")),
    BackendObservationRecord(EncryptedVaultContainerFormatV1SafeLabel("BackendObservationRecord")),
    ObservedUtxoRecord(EncryptedVaultContainerFormatV1SafeLabel("ObservedUtxoRecord")),
    WalletHistoryRecord(EncryptedVaultContainerFormatV1SafeLabel("WalletHistoryRecord")),
    RecoveryMetadataRecord(EncryptedVaultContainerFormatV1SafeLabel("RecoveryMetadataRecord")),
    PrivacyAnalyzerMetadataRecord(EncryptedVaultContainerFormatV1SafeLabel("PrivacyAnalyzerMetadataRecord")),
    LabelRecord(EncryptedVaultContainerFormatV1SafeLabel("LabelRecord")),
    TransactionNoteRecord(EncryptedVaultContainerFormatV1SafeLabel("TransactionNoteRecord")),
    BackendMetadataRecord(EncryptedVaultContainerFormatV1SafeLabel("BackendMetadataRecord")),
    TorRoutingMetadataRecord(EncryptedVaultContainerFormatV1SafeLabel("TorRoutingMetadataRecord")),
    NostrIdentityLinkageMetadataRecord(
        EncryptedVaultContainerFormatV1SafeLabel("NostrIdentityLinkageMetadataRecord"),
    ),
    BackupExportManifestRecord(EncryptedVaultContainerFormatV1SafeLabel("BackupExportManifestRecord")),
}

enum class EncryptedVaultContainerFormatV1AssociatedDataField {
    FormatVersionLabel,
    RecordPurposeLabel,
    AlgorithmSuiteLabel,
    MigrationVersionLabel,
    CanonicalRecordClassLabel,
}

enum class EncryptedVaultContainerFormatV1AssociatedDataForbiddenField {
    WalletNames,
    Labels,
    TransactionNotes,
    EndpointValues,
    Descriptors,
    Addresses,
    Txids,
    Psbts,
    NostrIdentifiersOrSecretKeys,
    LightningCredentials,
    CashuProofs,
    BackendCredentials,
    FilesystemPaths,
    SourceLocations,
    StackTraces,
}

enum class EncryptedVaultContainerFormatV1DecisionCheck {
    StorageReadinessDecisionPresent,
    StorageReadinessDecisionAdmitted,
    EncryptedVaultDesignPresent,
    CryptoDecisionPresent,
    DependencyReviewPresent,
    DependencyKatPassed,
    AndroidRuntimeKatPassed,
    Argon2idCalibrationProbePresent,
    VaultCryptoProviderBoundaryPresent,
    ProviderSelectionValidationCompletionAuditPresent,
    VaultContainerFormatV1DecisionAdmitted,
    VaultContainerHeaderModelAdmitted,
    VaultKdfSectionModelAdmitted,
    VaultKeyEnvelopeSectionModelAdmitted,
    VaultRecordDirectoryModelAdmitted,
    VaultRecordEnvelopeModelAdmitted,
    VaultAssociatedDataPolicyAdmitted,
    VaultNoncePolicyAdmitted,
    VaultRecordClassPolicyAdmitted,
    VaultMigrationCorruptionPolicyAdmitted,
    VaultBackupExportSeparationPolicyAdmitted,
    FutureVaultContainerParserRequiresSeparatePass,
    FutureVaultContainerWriterRequiresSeparatePass,
    FutureVaultStorageRepositoryRequiresSeparatePass,
    FutureSecureStorageSuccessRequiresSeparatePass,
    FutureSecureMetadataSuccessRequiresSeparatePass,
    FutureProductionSyncRequiresSeparatePass,
    FutureProductionProviderSelectionRequiresSeparatePass,
    VaultContainerParserAbsent,
    VaultContainerWriterAbsent,
    VaultContainerSerializationAbsent,
    VaultContainerParsingAbsent,
    VaultContainerBytesNotProduced,
    VaultFileReadAbsent,
    VaultFileWriteAbsent,
    EncryptedVaultFileFormatNotImplemented,
    EncryptedVaultRepositorySuccessAbsent,
    SecureSecretStorageSuccessPathAbsent,
    SecureMetadataStorageSuccessPathAbsent,
    ProductionObservationPersistenceAbsent,
    ProductionAddressIndexPersistenceAbsent,
    ProductionUtxoPersistenceAbsent,
    ProductionWalletHistoryPersistenceAbsent,
    ProductionSyncAbsent,
    ProductionBackendClientAbsent,
    ProductionProviderSelectionDisabled,
    ProductionProviderNotSelectable,
    ProductionSelectionStillDisabledProviderOnly,
    SigningBroadcastingAbsent,
    UiActionEnablementAbsent,
    EndpointAbsent,
    MainnetAbsent,
    VaultContainerImplementationAuthorizationAbsent,
    VaultContainerParserAuthorizationAbsent,
    VaultContainerWriterAuthorizationAbsent,
    ProductionStorageAuthorizationAbsent,
    ProductionSecretStorageAuthorizationAbsent,
    ProductionMetadataStorageAuthorizationAbsent,
    ProductionSyncAuthorizationAbsent,
    ProductionProviderSelectionAuthorizationAbsent,
    ProductionProviderImplementationAuthorizationAbsent,
    SigningBroadcastingAuthorizationAbsent,
    UiAuthorizationAbsent,
    EndpointAuthorizationAbsent,
    MainnetAuthorizationAbsent,
    AssociatedDataExcludesSensitiveWalletMetadata,
    NoncePolicyRequiresFutureRandomPerRecordXChaChaValue,
    FixedNonceSupportRemainsKatOnly,
    RecordClassesAreSafeEnumLabelsOnly,
    BuildHistoryExcludedFromNormalSourceMaterialCorpus,
    LocalArtifactRootExcludedFromNormalSourceMaterialCorpus,
}

enum class EncryptedVaultContainerFormatV1FailureLabel {
    ExistingEvidenceMissing,
    ContainerFormatAdmissionMissing,
    ConceptualSectionModelMissing,
    RecordClassPolicyMissing,
    AssociatedDataPolicyMissing,
    NoncePolicyMissing,
    FutureSeparatePassGateMissing,
    ContainerRuntimeSurfacePresent,
    ProductionStorageSurfacePresent,
    ProductionSyncSurfacePresent,
    ProductionProviderSelectionSurfacePresent,
    SigningBroadcastingUiEndpointOrMainnetSurfacePresent,
    ProductionAuthorizationPresent,
    CorpusBoundaryMissing,
}

data class EncryptedVaultContainerFormatV1SectionModel(
    val section: EncryptedVaultContainerFormatV1Section,
    val safeLabel: EncryptedVaultContainerFormatV1SafeLabel,
    val modelAdmitted: Boolean,
    val rawBytesPresent: Boolean,
    val serializationPresent: Boolean,
    val parsingPresent: Boolean,
)

data class EncryptedVaultContainerFormatV1Decision(
    val decisionId: EncryptedVaultContainerFormatV1SafeLabel,
    val decisionVersion: Int,
    val decisionKind: EncryptedVaultContainerFormatV1DecisionKind,
    val sourceSet: EncryptedVaultContainerFormatV1DecisionSourceSet,
    val storageReadinessDecisionPresent: Boolean,
    val storageReadinessDecisionAdmitted: Boolean,
    val encryptedVaultDesignPresent: Boolean,
    val cryptoDecisionPresent: Boolean,
    val dependencyReviewPresent: Boolean,
    val dependencyKatPassed: Boolean,
    val androidRuntimeKatPassed: Boolean,
    val argon2idCalibrationProbePresent: Boolean,
    val vaultCryptoProviderBoundaryPresent: Boolean,
    val providerSelectionValidationCompletionAuditPresent: Boolean,
    val vaultContainerFormatV1DecisionAdmitted: Boolean,
    val vaultContainerHeaderModelAdmitted: Boolean,
    val vaultKdfSectionModelAdmitted: Boolean,
    val vaultKeyEnvelopeSectionModelAdmitted: Boolean,
    val vaultRecordDirectoryModelAdmitted: Boolean,
    val vaultRecordEnvelopeModelAdmitted: Boolean,
    val vaultAssociatedDataPolicyAdmitted: Boolean,
    val vaultNoncePolicyAdmitted: Boolean,
    val vaultRecordClassPolicyAdmitted: Boolean,
    val vaultMigrationCorruptionPolicyAdmitted: Boolean,
    val vaultBackupExportSeparationPolicyAdmitted: Boolean,
    val futureVaultContainerParserRequiresSeparatePass: Boolean,
    val futureVaultContainerWriterRequiresSeparatePass: Boolean,
    val futureVaultStorageRepositoryRequiresSeparatePass: Boolean,
    val futureSecureStorageSuccessRequiresSeparatePass: Boolean,
    val futureSecureMetadataSuccessRequiresSeparatePass: Boolean,
    val futureProductionSyncRequiresSeparatePass: Boolean,
    val futureProductionProviderSelectionRequiresSeparatePass: Boolean,
    val vaultContainerParserPresent: Boolean,
    val vaultContainerWriterPresent: Boolean,
    val vaultContainerSerializationPresent: Boolean,
    val vaultContainerParsingPresent: Boolean,
    val vaultContainerBytesProduced: Boolean,
    val vaultFileReadPresent: Boolean,
    val vaultFileWritePresent: Boolean,
    val encryptedVaultFileFormatImplemented: Boolean,
    val encryptedVaultRepositorySuccessPresent: Boolean,
    val secureSecretStorageSuccessPathPresent: Boolean,
    val secureMetadataStorageSuccessPathPresent: Boolean,
    val productionObservationPersistencePresent: Boolean,
    val productionAddressIndexPersistencePresent: Boolean,
    val productionUtxoPersistencePresent: Boolean,
    val productionWalletHistoryPersistencePresent: Boolean,
    val productionSyncPresent: Boolean,
    val productionBackendClientPresent: Boolean,
    val productionProviderSelectionEnabled: Boolean,
    val productionProviderSelectable: Boolean,
    val productionSelectionStillDisabledProviderOnly: Boolean,
    val signingBroadcastingPresent: Boolean,
    val uiActionEnablementPresent: Boolean,
    val endpointPresent: Boolean,
    val mainnetPresent: Boolean,
    val vaultContainerImplementationAuthorizationPresent: Boolean,
    val vaultContainerParserAuthorizationPresent: Boolean,
    val vaultContainerWriterAuthorizationPresent: Boolean,
    val productionStorageAuthorizationPresent: Boolean,
    val productionSecretStorageAuthorizationPresent: Boolean,
    val productionMetadataStorageAuthorizationPresent: Boolean,
    val productionSyncAuthorizationPresent: Boolean,
    val productionProviderSelectionAuthorizationPresent: Boolean,
    val productionProviderImplementationAuthorizationPresent: Boolean,
    val signingBroadcastingAuthorizationPresent: Boolean,
    val uiAuthorizationPresent: Boolean,
    val endpointAuthorizationPresent: Boolean,
    val mainnetAuthorizationPresent: Boolean,
    val vaultContainerFormatV1DecisionAdmittedIsLaterBranchOnly: Boolean,
    val vaultContainerHeaderModelAdmittedIsNotHeaderSerialization: Boolean,
    val vaultKdfSectionModelAdmittedIsNotKdfExecution: Boolean,
    val vaultKeyEnvelopeSectionModelAdmittedIsNotKeyWrappingOrUnwrapping: Boolean,
    val vaultRecordDirectoryModelAdmittedIsNotRecordPersistence: Boolean,
    val vaultRecordEnvelopeModelAdmittedIsNotEncryptionOrDecryption: Boolean,
    val vaultAssociatedDataPolicyAdmittedIsNotRuntimeAssociatedDataConstruction: Boolean,
    val vaultNoncePolicyAdmittedIsNotNonceGeneration: Boolean,
    val vaultMigrationCorruptionPolicyAdmittedIsNotMigrationExecution: Boolean,
    val vaultBackupExportSeparationPolicyAdmittedIsNotBackupExportImplementation: Boolean,
    val futureAssociatedDataContainsOnlyNonSecretStructuralContext: Boolean,
    val futureAssociatedDataExcludesSensitiveWalletMetadata: Boolean,
    val futureXChaChaRecordNonceRequiresRandom24BytePerRecord: Boolean,
    val nonceGenerationPresent: Boolean,
    val callerProvidedProductionNonceSupportPresent: Boolean,
    val existingExplicitNonceKatApiRemainsTestProbeOnly: Boolean,
    val recordClassesSafeEnumLabelsOnly: Boolean,
    val normalSourceMaterialGuardExcludesBuildHistory: Boolean,
    val localArtifactRootExcludedFromNormalSourceMaterialCorpus: Boolean,
    val containerFormatV1DecisionPassed: Boolean,
    val evidenceCount: Int,
    val decisionCheckCount: Int,
    val blockerCount: Int,
    val warningCount: Int,
    val sections: List<EncryptedVaultContainerFormatV1SectionModel>,
    val recordClasses: List<EncryptedVaultContainerFormatV1RecordClass>,
    val associatedDataAllowedFields: List<EncryptedVaultContainerFormatV1AssociatedDataField>,
    val associatedDataForbiddenFields: List<EncryptedVaultContainerFormatV1AssociatedDataForbiddenField>,
    val decisionChecks: List<EncryptedVaultContainerFormatV1DecisionCheck>,
    val failureLabels: List<EncryptedVaultContainerFormatV1FailureLabel>,
    val displayLabel: EncryptedVaultContainerFormatV1SafeLabel,
) {
    override fun toString(): String =
        "EncryptedVaultContainerFormatV1Decision(" +
            "REDACTED, COMMON_MAIN_POLICY, CONTAINER_FORMAT_V1_DECISION_ONLY, " +
            "LATER_BRANCHES_ONLY, MODEL_LABELS_ONLY, NO_CONTAINER_IO, " +
            "NO_SERIALIZATION_PARSING, NO_CRYPTO_EXECUTION, NO_SECURE_STORAGE_SUCCESS, " +
            "NO_PRODUCTION_SYNC, DISABLED_PROVIDER_ONLY, " +
            "NO_SIGNING_BROADCASTING_UI_ENDPOINT_MAINNET" +
            ")"
}

object EncryptedVaultContainerFormatV1DecisionPolicy {
    fun currentContainerFormatV1Decision(): EncryptedVaultContainerFormatV1Decision {
        val storageDecision =
            EncryptedVaultStorageReadinessDecisionPolicy.currentStorageReadinessDecision()
        val providerSelection = VaultCryptoProviderSelectionRegistry.select()
        val checks = EncryptedVaultContainerFormatV1DecisionCheck.entries.toList()
        val sections = EncryptedVaultContainerFormatV1Section.entries.map { section ->
            EncryptedVaultContainerFormatV1SectionModel(
                section = section,
                safeLabel = section.label,
                modelAdmitted = true,
                rawBytesPresent = false,
                serializationPresent = false,
                parsingPresent = false,
            )
        }

        val storageReadinessDecisionPresent = storageDecision.storageReadinessDecisionPassed
        val storageReadinessDecisionAdmitted =
            storageDecision.storageReadinessDecisionPassed &&
                storageDecision.vaultContainerFormatDecisionAdmitted
        val encryptedVaultDesignPresent = storageDecision.encryptedVaultDesignPresent
        val cryptoDecisionPresent = storageDecision.cryptoDecisionPresent
        val dependencyReviewPresent = storageDecision.dependencyReviewPresent
        val dependencyKatPassed = storageDecision.desktopDependencyKatPassed
        val androidRuntimeKatPassed = storageDecision.androidDependencyKatPassed
        val argon2idCalibrationProbePresent = storageDecision.argon2idCalibrationProbePresent
        val vaultCryptoProviderBoundaryPresent = storageDecision.disabledVaultCryptoProviderBoundaryPresent
        val providerSelectionValidationCompletionAuditPresent =
            storageDecision.providerSelectionValidationCompletionAuditPassed

        val vaultContainerFormatV1DecisionAdmitted = true
        val vaultContainerHeaderModelAdmitted = true
        val vaultKdfSectionModelAdmitted = true
        val vaultKeyEnvelopeSectionModelAdmitted = true
        val vaultRecordDirectoryModelAdmitted = true
        val vaultRecordEnvelopeModelAdmitted = true
        val vaultAssociatedDataPolicyAdmitted = true
        val vaultNoncePolicyAdmitted = true
        val vaultRecordClassPolicyAdmitted = true
        val vaultMigrationCorruptionPolicyAdmitted = true
        val vaultBackupExportSeparationPolicyAdmitted = true

        val futureVaultContainerParserRequiresSeparatePass = true
        val futureVaultContainerWriterRequiresSeparatePass = true
        val futureVaultStorageRepositoryRequiresSeparatePass = true
        val futureSecureStorageSuccessRequiresSeparatePass = true
        val futureSecureMetadataSuccessRequiresSeparatePass = true
        val futureProductionSyncRequiresSeparatePass = true
        val futureProductionProviderSelectionRequiresSeparatePass = true

        val vaultContainerParserPresent = false
        val vaultContainerWriterPresent = false
        val vaultContainerSerializationPresent = false
        val vaultContainerParsingPresent = false
        val vaultContainerBytesProduced = false
        val vaultFileReadPresent = false
        val vaultFileWritePresent = false
        val encryptedVaultFileFormatImplemented = false
        val encryptedVaultRepositorySuccessPresent = false
        val secureSecretStorageSuccessPathPresent = storageDecision.secureSecretStorageSuccessPathPresent
        val secureMetadataStorageSuccessPathPresent = storageDecision.secureMetadataStorageSuccessPathPresent
        val productionObservationPersistencePresent = storageDecision.productionObservationPersistencePresent
        val productionAddressIndexPersistencePresent = storageDecision.productionAddressIndexPersistencePresent
        val productionUtxoPersistencePresent = storageDecision.productionUtxoPersistencePresent
        val productionWalletHistoryPersistencePresent = storageDecision.productionWalletHistoryPersistencePresent
        val productionSyncPresent = storageDecision.productionSyncPresent
        val productionBackendClientPresent = storageDecision.productionBackendClientPresent
        val productionProviderSelectionEnabled = !providerSelection.selectedProviderIsDisabled
        val productionProviderSelectable = providerSelection.productionProviderSelectable
        val productionSelectionStillDisabledProviderOnly =
            providerSelection.selectedProviderIsDisabled &&
                !productionProviderSelectionEnabled &&
                !productionProviderSelectable
        val signingBroadcastingPresent = storageDecision.signingBroadcastingPresent
        val uiActionEnablementPresent = storageDecision.uiActionEnablementPresent
        val endpointPresent = storageDecision.endpointPresent
        val mainnetPresent = storageDecision.mainnetPresent

        val vaultContainerImplementationAuthorizationPresent = false
        val vaultContainerParserAuthorizationPresent = false
        val vaultContainerWriterAuthorizationPresent = false
        val productionStorageAuthorizationPresent = false
        val productionSecretStorageAuthorizationPresent = false
        val productionMetadataStorageAuthorizationPresent = false
        val productionSyncAuthorizationPresent = false
        val productionProviderSelectionAuthorizationPresent = false
        val productionProviderImplementationAuthorizationPresent = false
        val signingBroadcastingAuthorizationPresent = false
        val uiAuthorizationPresent = false
        val endpointAuthorizationPresent = false
        val mainnetAuthorizationPresent = false

        val vaultContainerFormatV1DecisionAdmittedIsLaterBranchOnly =
            vaultContainerFormatV1DecisionAdmitted &&
                futureVaultContainerParserRequiresSeparatePass &&
                futureVaultContainerWriterRequiresSeparatePass &&
                futureVaultStorageRepositoryRequiresSeparatePass &&
                !vaultContainerSerializationPresent &&
                !vaultContainerParsingPresent &&
                !encryptedVaultRepositorySuccessPresent
        val vaultContainerHeaderModelAdmittedIsNotHeaderSerialization =
            vaultContainerHeaderModelAdmitted &&
                !vaultContainerSerializationPresent &&
                !vaultContainerBytesProduced
        val vaultKdfSectionModelAdmittedIsNotKdfExecution =
            vaultKdfSectionModelAdmitted &&
                !productionStorageAuthorizationPresent &&
                !vaultContainerBytesProduced
        val vaultKeyEnvelopeSectionModelAdmittedIsNotKeyWrappingOrUnwrapping =
            vaultKeyEnvelopeSectionModelAdmitted &&
                !productionSecretStorageAuthorizationPresent &&
                !productionMetadataStorageAuthorizationPresent
        val vaultRecordDirectoryModelAdmittedIsNotRecordPersistence =
            vaultRecordDirectoryModelAdmitted &&
                !encryptedVaultRepositorySuccessPresent &&
                !secureMetadataStorageSuccessPathPresent
        val vaultRecordEnvelopeModelAdmittedIsNotEncryptionOrDecryption =
            vaultRecordEnvelopeModelAdmitted &&
                !secureSecretStorageSuccessPathPresent &&
                !secureMetadataStorageSuccessPathPresent
        val vaultAssociatedDataPolicyAdmittedIsNotRuntimeAssociatedDataConstruction =
            vaultAssociatedDataPolicyAdmitted &&
                !vaultContainerSerializationPresent &&
                !vaultContainerParsingPresent
        val vaultNoncePolicyAdmittedIsNotNonceGeneration =
            vaultNoncePolicyAdmitted &&
                !vaultContainerSerializationPresent &&
                !vaultContainerBytesProduced
        val vaultMigrationCorruptionPolicyAdmittedIsNotMigrationExecution =
            vaultMigrationCorruptionPolicyAdmitted &&
                !vaultContainerParsingPresent &&
                !encryptedVaultRepositorySuccessPresent
        val vaultBackupExportSeparationPolicyAdmittedIsNotBackupExportImplementation =
            vaultBackupExportSeparationPolicyAdmitted &&
                !secureSecretStorageSuccessPathPresent &&
                !secureMetadataStorageSuccessPathPresent

        val futureAssociatedDataContainsOnlyNonSecretStructuralContext = true
        val futureAssociatedDataExcludesSensitiveWalletMetadata = true
        val futureXChaChaRecordNonceRequiresRandom24BytePerRecord = true
        val nonceGenerationPresent = false
        val callerProvidedProductionNonceSupportPresent = false
        val existingExplicitNonceKatApiRemainsTestProbeOnly = true
        val recordClasses = EncryptedVaultContainerFormatV1RecordClass.entries.toList()
        val associatedDataAllowedFields =
            EncryptedVaultContainerFormatV1AssociatedDataField.entries.toList()
        val associatedDataForbiddenFields =
            EncryptedVaultContainerFormatV1AssociatedDataForbiddenField.entries.toList()
        val recordClassesSafeEnumLabelsOnly = recordClasses.size == 14
        val normalSourceMaterialGuardExcludesBuildHistory = true
        val localArtifactRootExcludedFromNormalSourceMaterialCorpus = true

        val existingEvidencePresent =
            storageReadinessDecisionPresent &&
                storageReadinessDecisionAdmitted &&
                encryptedVaultDesignPresent &&
                cryptoDecisionPresent &&
                dependencyReviewPresent &&
                dependencyKatPassed &&
                androidRuntimeKatPassed &&
                argon2idCalibrationProbePresent &&
                vaultCryptoProviderBoundaryPresent &&
                providerSelectionValidationCompletionAuditPresent
        val containerFormatAdmissionPresent =
            vaultContainerFormatV1DecisionAdmitted &&
                vaultContainerHeaderModelAdmitted &&
                vaultKdfSectionModelAdmitted &&
                vaultKeyEnvelopeSectionModelAdmitted &&
                vaultRecordDirectoryModelAdmitted &&
                vaultRecordEnvelopeModelAdmitted &&
                vaultAssociatedDataPolicyAdmitted &&
                vaultNoncePolicyAdmitted &&
                vaultRecordClassPolicyAdmitted &&
                vaultMigrationCorruptionPolicyAdmitted &&
                vaultBackupExportSeparationPolicyAdmitted
        val conceptualSectionsPresent =
            sections.size == EncryptedVaultContainerFormatV1Section.entries.size &&
                sections.all { section ->
                    section.modelAdmitted &&
                        !section.rawBytesPresent &&
                        !section.serializationPresent &&
                        !section.parsingPresent
                }
        val associatedDataPolicyPresent =
            futureAssociatedDataContainsOnlyNonSecretStructuralContext &&
                futureAssociatedDataExcludesSensitiveWalletMetadata &&
                associatedDataAllowedFields.size == EncryptedVaultContainerFormatV1AssociatedDataField.entries.size &&
                associatedDataForbiddenFields.size ==
                    EncryptedVaultContainerFormatV1AssociatedDataForbiddenField.entries.size
        val noncePolicyPresent =
            futureXChaChaRecordNonceRequiresRandom24BytePerRecord &&
                !nonceGenerationPresent &&
                !callerProvidedProductionNonceSupportPresent &&
                existingExplicitNonceKatApiRemainsTestProbeOnly
        val futureSeparatePassGatesPresent =
            futureVaultContainerParserRequiresSeparatePass &&
                futureVaultContainerWriterRequiresSeparatePass &&
                futureVaultStorageRepositoryRequiresSeparatePass &&
                futureSecureStorageSuccessRequiresSeparatePass &&
                futureSecureMetadataSuccessRequiresSeparatePass &&
                futureProductionSyncRequiresSeparatePass &&
                futureProductionProviderSelectionRequiresSeparatePass
        val containerRuntimeSurfacePresent =
            vaultContainerParserPresent ||
                vaultContainerWriterPresent ||
                vaultContainerSerializationPresent ||
                vaultContainerParsingPresent ||
                vaultContainerBytesProduced ||
                vaultFileReadPresent ||
                vaultFileWritePresent ||
                encryptedVaultFileFormatImplemented
        val productionStorageSurfacePresent =
            encryptedVaultRepositorySuccessPresent ||
                secureSecretStorageSuccessPathPresent ||
                secureMetadataStorageSuccessPathPresent ||
                productionObservationPersistencePresent ||
                productionAddressIndexPersistencePresent ||
                productionUtxoPersistencePresent ||
                productionWalletHistoryPersistencePresent
        val productionSyncSurfacePresent =
            productionSyncPresent || productionBackendClientPresent
        val productionProviderSelectionSurfacePresent =
            productionProviderSelectionEnabled || productionProviderSelectable
        val signingBroadcastingUiEndpointOrMainnetSurfacePresent =
            signingBroadcastingPresent ||
                uiActionEnablementPresent ||
                endpointPresent ||
                mainnetPresent
        val productionAuthorizationPresent =
            vaultContainerImplementationAuthorizationPresent ||
                vaultContainerParserAuthorizationPresent ||
                vaultContainerWriterAuthorizationPresent ||
                productionStorageAuthorizationPresent ||
                productionSecretStorageAuthorizationPresent ||
                productionMetadataStorageAuthorizationPresent ||
                productionSyncAuthorizationPresent ||
                productionProviderSelectionAuthorizationPresent ||
                productionProviderImplementationAuthorizationPresent ||
                signingBroadcastingAuthorizationPresent ||
                uiAuthorizationPresent ||
                endpointAuthorizationPresent ||
                mainnetAuthorizationPresent
        val corpusBoundaryPresent =
            normalSourceMaterialGuardExcludesBuildHistory &&
                localArtifactRootExcludedFromNormalSourceMaterialCorpus

        val failures = buildList {
            if (!existingEvidencePresent) {
                add(EncryptedVaultContainerFormatV1FailureLabel.ExistingEvidenceMissing)
            }
            if (!containerFormatAdmissionPresent) {
                add(EncryptedVaultContainerFormatV1FailureLabel.ContainerFormatAdmissionMissing)
            }
            if (!conceptualSectionsPresent) {
                add(EncryptedVaultContainerFormatV1FailureLabel.ConceptualSectionModelMissing)
            }
            if (!recordClassesSafeEnumLabelsOnly) {
                add(EncryptedVaultContainerFormatV1FailureLabel.RecordClassPolicyMissing)
            }
            if (!associatedDataPolicyPresent) {
                add(EncryptedVaultContainerFormatV1FailureLabel.AssociatedDataPolicyMissing)
            }
            if (!noncePolicyPresent) {
                add(EncryptedVaultContainerFormatV1FailureLabel.NoncePolicyMissing)
            }
            if (!futureSeparatePassGatesPresent) {
                add(EncryptedVaultContainerFormatV1FailureLabel.FutureSeparatePassGateMissing)
            }
            if (containerRuntimeSurfacePresent) {
                add(EncryptedVaultContainerFormatV1FailureLabel.ContainerRuntimeSurfacePresent)
            }
            if (productionStorageSurfacePresent) {
                add(EncryptedVaultContainerFormatV1FailureLabel.ProductionStorageSurfacePresent)
            }
            if (productionSyncSurfacePresent) {
                add(EncryptedVaultContainerFormatV1FailureLabel.ProductionSyncSurfacePresent)
            }
            if (productionProviderSelectionSurfacePresent || !productionSelectionStillDisabledProviderOnly) {
                add(EncryptedVaultContainerFormatV1FailureLabel.ProductionProviderSelectionSurfacePresent)
            }
            if (signingBroadcastingUiEndpointOrMainnetSurfacePresent) {
                add(
                    EncryptedVaultContainerFormatV1FailureLabel
                        .SigningBroadcastingUiEndpointOrMainnetSurfacePresent,
                )
            }
            if (productionAuthorizationPresent) {
                add(EncryptedVaultContainerFormatV1FailureLabel.ProductionAuthorizationPresent)
            }
            if (!corpusBoundaryPresent) {
                add(EncryptedVaultContainerFormatV1FailureLabel.CorpusBoundaryMissing)
            }
        }

        val containerFormatV1DecisionPassed =
            failures.isEmpty() &&
                vaultContainerFormatV1DecisionAdmittedIsLaterBranchOnly &&
                vaultContainerHeaderModelAdmittedIsNotHeaderSerialization &&
                vaultKdfSectionModelAdmittedIsNotKdfExecution &&
                vaultKeyEnvelopeSectionModelAdmittedIsNotKeyWrappingOrUnwrapping &&
                vaultRecordDirectoryModelAdmittedIsNotRecordPersistence &&
                vaultRecordEnvelopeModelAdmittedIsNotEncryptionOrDecryption &&
                vaultAssociatedDataPolicyAdmittedIsNotRuntimeAssociatedDataConstruction &&
                vaultNoncePolicyAdmittedIsNotNonceGeneration

        return EncryptedVaultContainerFormatV1Decision(
            decisionId = EncryptedVaultContainerFormatV1SafeLabel(
                "skald-encrypted-local-vault-container-v1",
            ),
            decisionVersion = 1,
            decisionKind =
                EncryptedVaultContainerFormatV1DecisionKind.EncryptedLocalVaultContainerFormatV1Decision,
            sourceSet = EncryptedVaultContainerFormatV1DecisionSourceSet.CommonMainPolicy,
            storageReadinessDecisionPresent = storageReadinessDecisionPresent,
            storageReadinessDecisionAdmitted = storageReadinessDecisionAdmitted,
            encryptedVaultDesignPresent = encryptedVaultDesignPresent,
            cryptoDecisionPresent = cryptoDecisionPresent,
            dependencyReviewPresent = dependencyReviewPresent,
            dependencyKatPassed = dependencyKatPassed,
            androidRuntimeKatPassed = androidRuntimeKatPassed,
            argon2idCalibrationProbePresent = argon2idCalibrationProbePresent,
            vaultCryptoProviderBoundaryPresent = vaultCryptoProviderBoundaryPresent,
            providerSelectionValidationCompletionAuditPresent =
                providerSelectionValidationCompletionAuditPresent,
            vaultContainerFormatV1DecisionAdmitted = vaultContainerFormatV1DecisionAdmitted,
            vaultContainerHeaderModelAdmitted = vaultContainerHeaderModelAdmitted,
            vaultKdfSectionModelAdmitted = vaultKdfSectionModelAdmitted,
            vaultKeyEnvelopeSectionModelAdmitted = vaultKeyEnvelopeSectionModelAdmitted,
            vaultRecordDirectoryModelAdmitted = vaultRecordDirectoryModelAdmitted,
            vaultRecordEnvelopeModelAdmitted = vaultRecordEnvelopeModelAdmitted,
            vaultAssociatedDataPolicyAdmitted = vaultAssociatedDataPolicyAdmitted,
            vaultNoncePolicyAdmitted = vaultNoncePolicyAdmitted,
            vaultRecordClassPolicyAdmitted = vaultRecordClassPolicyAdmitted,
            vaultMigrationCorruptionPolicyAdmitted = vaultMigrationCorruptionPolicyAdmitted,
            vaultBackupExportSeparationPolicyAdmitted = vaultBackupExportSeparationPolicyAdmitted,
            futureVaultContainerParserRequiresSeparatePass =
                futureVaultContainerParserRequiresSeparatePass,
            futureVaultContainerWriterRequiresSeparatePass =
                futureVaultContainerWriterRequiresSeparatePass,
            futureVaultStorageRepositoryRequiresSeparatePass =
                futureVaultStorageRepositoryRequiresSeparatePass,
            futureSecureStorageSuccessRequiresSeparatePass = futureSecureStorageSuccessRequiresSeparatePass,
            futureSecureMetadataSuccessRequiresSeparatePass = futureSecureMetadataSuccessRequiresSeparatePass,
            futureProductionSyncRequiresSeparatePass = futureProductionSyncRequiresSeparatePass,
            futureProductionProviderSelectionRequiresSeparatePass =
                futureProductionProviderSelectionRequiresSeparatePass,
            vaultContainerParserPresent = vaultContainerParserPresent,
            vaultContainerWriterPresent = vaultContainerWriterPresent,
            vaultContainerSerializationPresent = vaultContainerSerializationPresent,
            vaultContainerParsingPresent = vaultContainerParsingPresent,
            vaultContainerBytesProduced = vaultContainerBytesProduced,
            vaultFileReadPresent = vaultFileReadPresent,
            vaultFileWritePresent = vaultFileWritePresent,
            encryptedVaultFileFormatImplemented = encryptedVaultFileFormatImplemented,
            encryptedVaultRepositorySuccessPresent = encryptedVaultRepositorySuccessPresent,
            secureSecretStorageSuccessPathPresent = secureSecretStorageSuccessPathPresent,
            secureMetadataStorageSuccessPathPresent = secureMetadataStorageSuccessPathPresent,
            productionObservationPersistencePresent = productionObservationPersistencePresent,
            productionAddressIndexPersistencePresent = productionAddressIndexPersistencePresent,
            productionUtxoPersistencePresent = productionUtxoPersistencePresent,
            productionWalletHistoryPersistencePresent = productionWalletHistoryPersistencePresent,
            productionSyncPresent = productionSyncPresent,
            productionBackendClientPresent = productionBackendClientPresent,
            productionProviderSelectionEnabled = productionProviderSelectionEnabled,
            productionProviderSelectable = productionProviderSelectable,
            productionSelectionStillDisabledProviderOnly = productionSelectionStillDisabledProviderOnly,
            signingBroadcastingPresent = signingBroadcastingPresent,
            uiActionEnablementPresent = uiActionEnablementPresent,
            endpointPresent = endpointPresent,
            mainnetPresent = mainnetPresent,
            vaultContainerImplementationAuthorizationPresent =
                vaultContainerImplementationAuthorizationPresent,
            vaultContainerParserAuthorizationPresent = vaultContainerParserAuthorizationPresent,
            vaultContainerWriterAuthorizationPresent = vaultContainerWriterAuthorizationPresent,
            productionStorageAuthorizationPresent = productionStorageAuthorizationPresent,
            productionSecretStorageAuthorizationPresent = productionSecretStorageAuthorizationPresent,
            productionMetadataStorageAuthorizationPresent = productionMetadataStorageAuthorizationPresent,
            productionSyncAuthorizationPresent = productionSyncAuthorizationPresent,
            productionProviderSelectionAuthorizationPresent =
                productionProviderSelectionAuthorizationPresent,
            productionProviderImplementationAuthorizationPresent =
                productionProviderImplementationAuthorizationPresent,
            signingBroadcastingAuthorizationPresent = signingBroadcastingAuthorizationPresent,
            uiAuthorizationPresent = uiAuthorizationPresent,
            endpointAuthorizationPresent = endpointAuthorizationPresent,
            mainnetAuthorizationPresent = mainnetAuthorizationPresent,
            vaultContainerFormatV1DecisionAdmittedIsLaterBranchOnly =
                vaultContainerFormatV1DecisionAdmittedIsLaterBranchOnly,
            vaultContainerHeaderModelAdmittedIsNotHeaderSerialization =
                vaultContainerHeaderModelAdmittedIsNotHeaderSerialization,
            vaultKdfSectionModelAdmittedIsNotKdfExecution =
                vaultKdfSectionModelAdmittedIsNotKdfExecution,
            vaultKeyEnvelopeSectionModelAdmittedIsNotKeyWrappingOrUnwrapping =
                vaultKeyEnvelopeSectionModelAdmittedIsNotKeyWrappingOrUnwrapping,
            vaultRecordDirectoryModelAdmittedIsNotRecordPersistence =
                vaultRecordDirectoryModelAdmittedIsNotRecordPersistence,
            vaultRecordEnvelopeModelAdmittedIsNotEncryptionOrDecryption =
                vaultRecordEnvelopeModelAdmittedIsNotEncryptionOrDecryption,
            vaultAssociatedDataPolicyAdmittedIsNotRuntimeAssociatedDataConstruction =
                vaultAssociatedDataPolicyAdmittedIsNotRuntimeAssociatedDataConstruction,
            vaultNoncePolicyAdmittedIsNotNonceGeneration =
                vaultNoncePolicyAdmittedIsNotNonceGeneration,
            vaultMigrationCorruptionPolicyAdmittedIsNotMigrationExecution =
                vaultMigrationCorruptionPolicyAdmittedIsNotMigrationExecution,
            vaultBackupExportSeparationPolicyAdmittedIsNotBackupExportImplementation =
                vaultBackupExportSeparationPolicyAdmittedIsNotBackupExportImplementation,
            futureAssociatedDataContainsOnlyNonSecretStructuralContext =
                futureAssociatedDataContainsOnlyNonSecretStructuralContext,
            futureAssociatedDataExcludesSensitiveWalletMetadata =
                futureAssociatedDataExcludesSensitiveWalletMetadata,
            futureXChaChaRecordNonceRequiresRandom24BytePerRecord =
                futureXChaChaRecordNonceRequiresRandom24BytePerRecord,
            nonceGenerationPresent = nonceGenerationPresent,
            callerProvidedProductionNonceSupportPresent = callerProvidedProductionNonceSupportPresent,
            existingExplicitNonceKatApiRemainsTestProbeOnly = existingExplicitNonceKatApiRemainsTestProbeOnly,
            recordClassesSafeEnumLabelsOnly = recordClassesSafeEnumLabelsOnly,
            normalSourceMaterialGuardExcludesBuildHistory = normalSourceMaterialGuardExcludesBuildHistory,
            localArtifactRootExcludedFromNormalSourceMaterialCorpus =
                localArtifactRootExcludedFromNormalSourceMaterialCorpus,
            containerFormatV1DecisionPassed = containerFormatV1DecisionPassed,
            evidenceCount = 10,
            decisionCheckCount = checks.size,
            blockerCount = failures.size,
            warningCount = 0,
            sections = sections,
            recordClasses = recordClasses,
            associatedDataAllowedFields = associatedDataAllowedFields,
            associatedDataForbiddenFields = associatedDataForbiddenFields,
            decisionChecks = checks,
            failureLabels = failures,
            displayLabel = EncryptedVaultContainerFormatV1SafeLabel(
                "encrypted vault container format v1 decision",
            ),
        )
    }
}
