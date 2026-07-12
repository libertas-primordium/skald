package com.libertasprimordium.skald.security

@JvmInline
value class EncryptedVaultV1CanonicalArchitectureSafeLabel(val value: String) {
    override fun toString(): String =
        "EncryptedVaultV1CanonicalArchitectureSafeLabel(REDACTED)"
}

enum class EncryptedVaultV1CanonicalArchitectureDecisionKind(val label: String) {
    EncryptedLocalVaultV1CanonicalArchitectureDecision(
        "ENCRYPTED_LOCAL_VAULT_V1_CANONICAL_ARCHITECTURE_DECISION",
    ),
}

enum class EncryptedVaultV1CanonicalArchitectureDecisionSourceSet(val label: String) {
    CommonMainPolicy("COMMON_MAIN_POLICY"),
}

enum class EncryptedVaultV1CanonicalArchitectureDecisionStatus(val label: String) {
    CanonicalArchitectureSelectedImplementationBlocked(
        "CANONICAL_ARCHITECTURE_SELECTED_IMPLEMENTATION_BLOCKED",
    ),
}

enum class EncryptedVaultV1PrototypeDisposition(val label: String) {
    PreReleasePrototypeP0Unsupported("PRE_RELEASE_PROTOTYPE_P0_UNSUPPORTED"),
}

enum class EncryptedVaultV1SyntheticParserDisposition(val label: String) {
    SyntheticTestContractClassifierOnly("SYNTHETIC_TEST_CONTRACT_CLASSIFIER_ONLY"),
}

enum class EncryptedVaultV1Topology(val label: String) {
    MultiArtifactAppControlledVaultDirectory(
        "MULTI_ARTIFACT_APP_CONTROLLED_VAULT_DIRECTORY",
    ),
}

enum class EncryptedVaultV1ArtifactRole(
    val safeLabel: EncryptedVaultV1CanonicalArchitectureSafeLabel,
) {
    PublicHeader(
        EncryptedVaultV1CanonicalArchitectureSafeLabel("skald-vault-v1-public-header-role"),
    ),
    AuthoritativeEncryptedManifest(
        EncryptedVaultV1CanonicalArchitectureSafeLabel(
            "skald-vault-v1-authoritative-encrypted-manifest-role",
        ),
    ),
    EncryptedRecord(
        EncryptedVaultV1CanonicalArchitectureSafeLabel("skald-vault-v1-encrypted-record-role"),
    ),
    TemporaryStaging(
        EncryptedVaultV1CanonicalArchitectureSafeLabel("skald-vault-v1-temporary-staging-role"),
    ),
    QuarantineRecovery(
        EncryptedVaultV1CanonicalArchitectureSafeLabel(
            "skald-vault-v1-quarantine-recovery-role",
        ),
    ),
    SeparateBackupExport(
        EncryptedVaultV1CanonicalArchitectureSafeLabel(
            "skald-vault-v1-separate-backup-export-role",
        ),
    ),
}

enum class EncryptedVaultV1HeaderAuthenticationPolicy(val label: String) {
    WrappedRootAeadAssociatedDataNoSeparateHeaderHmac(
        "WRAPPED_ROOT_AEAD_ASSOCIATED_DATA_NO_SEPARATE_HEADER_HMAC",
    ),
}

enum class EncryptedVaultV1KeyHierarchyPolicy(val label: String) {
    Argon2idKekWrappedRandomVaultRootHkdfSeparatedRootsPerRecordKeys(
        "ARGON2ID_KEK_WRAPPED_RANDOM_VAULT_ROOT_HKDF_SEPARATED_ROOTS_PER_RECORD_KEYS",
    ),
}

enum class EncryptedVaultV1ManifestPolicy(val label: String) {
    SingleAuthoritativeEncryptedAuthenticatedManifest(
        "SINGLE_AUTHORITATIVE_ENCRYPTED_AUTHENTICATED_MANIFEST",
    ),
}

enum class EncryptedVaultV1RecordPolicy(val label: String) {
    PerRecordKeyFreshXChaChaNonceMinimalPublicEnvelope(
        "PER_RECORD_KEY_FRESH_XCHACHA_NONCE_MINIMAL_PUBLIC_ENVELOPE",
    ),
}

enum class EncryptedVaultV1ProviderRoutingPolicy(val label: String) {
    MandatorySelectedSkaldVaultCryptoProvider(
        "MANDATORY_SELECTED_SKALD_VAULT_CRYPTO_PROVIDER",
    ),
}

enum class EncryptedVaultV1EncodingPolicy(val label: String) {
    FixedBigEndianStrictOrderedFieldsNoUnknownNoDuplicatesNoTrailingNonRecursive(
        "FIXED_BIG_ENDIAN_STRICT_ORDERED_FIELDS_NO_UNKNOWN_NO_DUPLICATES_NO_TRAILING_NON_RECURSIVE",
    ),
}

enum class EncryptedVaultV1RollbackPolicy(val label: String) {
    LocalEvidenceWithoutCompleteWholeSnapshotRollbackPrevention(
        "LOCAL_EVIDENCE_WITHOUT_COMPLETE_WHOLE_SNAPSHOT_ROLLBACK_PREVENTION",
    ),
}

enum class EncryptedVaultV1FixturePolicy(val label: String) {
    TestSourceOnlyFixedFixtures("TEST_SOURCE_ONLY_FIXED_FIXTURES"),
}

enum class EncryptedVaultV1ArrayOwnershipPolicy(val label: String) {
    OwnedCopyOpaqueRedactedNoSensitiveDataClass(
        "OWNED_COPY_OPAQUE_REDACTED_NO_SENSITIVE_DATA_CLASS",
    ),
}

enum class EncryptedVaultV1CanonicalAlgorithm(val label: String) {
    Argon2id("ARGON2ID"),
    XChaCha20Poly1305("XCHACHA20_POLY1305"),
    HkdfSha256("HKDF_SHA256"),
}

enum class EncryptedVaultV1PublicHeaderConcept {
    FutureFixedMagic,
    CanonicalFormatVersion,
    ProviderSuiteIdAndVersion,
    KdfIdAndVersion,
    Argon2idMemoryPassAndLaneParameters,
    KdfSalt,
    StableRandomVaultId,
    KeyEnvelopeVersion,
    WrappedRootNonceCiphertextTagEnvelopeRepresentation,
}

enum class EncryptedVaultV1AtomicCommitStep {
    WriteRecordTemporary,
    FlushRecordContents,
    AtomicallyInstallRecord,
    WriteManifestTemporary,
    FlushManifestContents,
    AtomicallyReplaceAuthoritativeManifest,
    FlushContainingDirectoryWhereSupported,
    QuarantineOrCollectUnreferencedArtifactsLater,
}

data class EncryptedVaultV1ResourceLimits(
    val publicHeaderMaxBytes: Int,
    val encryptedManifestMaxBytes: Int,
    val manifestRecordCountMax: Int,
    val recordPlaintextMaxBytes: Int,
    val recordCiphertextPayloadMaxBytes: Int,
    val stringMaxUtf8Bytes: Int,
    val passphraseEncodedMaxBytes: Int,
    val argon2SaltMinBytes: Int,
    val argon2SaltMaxBytes: Int,
    val argon2MemoryMinKiB: Int,
    val argon2MemoryMaxKiB: Int,
    val argon2PassesMin: Int,
    val argon2PassesMax: Int,
    val argon2LanesMin: Int,
    val argon2LanesMax: Int,
    val derivedKekBytes: Int,
    val vaultRootKeyBytes: Int,
    val xChaChaNonceBytes: Int,
    val structuralNestingFixedNonRecursive: Boolean,
)

data class EncryptedVaultV1LegacyDispositionDecision(
    val containerFormatDisposition: EncryptedVaultV1PrototypeDisposition,
    val manifestFormatDisposition: EncryptedVaultV1PrototypeDisposition,
    val headerCommitmentDisposition: EncryptedVaultV1PrototypeDisposition,
    val recordAeadDisposition: EncryptedVaultV1PrototypeDisposition,
    val supportingPrototypeArtifactsDisposition: EncryptedVaultV1PrototypeDisposition,
    val syntheticParserDisposition: EncryptedVaultV1SyntheticParserDisposition,
    val canonicalPersistedFormatVersion: Int,
    val prototypeP0BytesAreCanonical: Boolean,
    val prototypeP0CompatibilityPromised: Boolean,
    val prototypeP0MigrationSupported: Boolean,
    val prototypeP0ProductionIntegrationAllowed: Boolean,
    val prototypeP0DeveloperDataMustBeRecreated: Boolean,
    val prototypeP0DeletionPresent: Boolean,
    val prototypeP0DeprecationAnnotationPresent: Boolean,
    val prototypeP0SemanticModificationPresent: Boolean,
    val syntheticParserIsCanonicalVaultParser: Boolean,
    val syntheticParserProductionVaultParsingAllowed: Boolean,
    val syntheticParserRealWalletParsingAllowed: Boolean,
    val syntheticParserRemovalPresent: Boolean,
    val futurePrototypeQuarantineRequiresSeparatePass: Boolean,
    val futurePrototypeFixtureRelocationRequiresSeparatePass: Boolean,
    val futureSyntheticParserRelocationOrRemovalRequiresSeparatePass: Boolean,
)

data class EncryptedVaultV1TopologyDecision(
    val topology: EncryptedVaultV1Topology,
    val artifactRoles: List<EncryptedVaultV1ArtifactRole>,
    val appControlledVaultDirectoryRequired: Boolean,
    val monolithicLiveVaultFileSelected: Boolean,
    val publicHeaderArtifactCount: Int,
    val authoritativeManifestArtifactCount: Int,
    val recordArtifactsAreIndependent: Boolean,
    val temporaryArtifactRolePresent: Boolean,
    val quarantineArtifactRolePresent: Boolean,
    val backupExportIsSeparateFormat: Boolean,
    val backupExportIsLiveVaultArtifact: Boolean,
    val filenamesAreSemanticAuthority: Boolean,
    val directoryMetadataIsSemanticAuthority: Boolean,
    val manifestIsSemanticAuthority: Boolean,
    val concreteFileNamesDefined: Boolean,
    val concreteDirectoryNamesDefined: Boolean,
    val concreteStoragePathsDefined: Boolean,
    val directoryCreationPresent: Boolean,
    val fileReadPresent: Boolean,
    val fileWritePresent: Boolean,
    val fileDeletePresent: Boolean,
)

data class EncryptedVaultV1PublicHeaderDecision(
    val authenticationPolicy: EncryptedVaultV1HeaderAuthenticationPolicy,
    val futureStructuralConcepts: List<EncryptedVaultV1PublicHeaderConcept>,
    val publicHeaderMaxBytes: Int,
    val publicHeaderContainsSensitiveWalletMetadata: Boolean,
    val publicHeaderContainsCredentialMaterial: Boolean,
    val separatePersistedHeaderHmacPresent: Boolean,
    val separateHeaderCommitmentSelected: Boolean,
    val wrappedRootAeadAuthenticatesHeaderContext: Boolean,
    val headerAadMustBeCanonical: Boolean,
    val headerAadMustBeNonCircular: Boolean,
    val headerAadExcludesEnvelopeCiphertextAndTag: Boolean,
    val wrongPassphraseAndEnvelopeDamageShareGenericUnlockFailure: Boolean,
    val exactMagicBytesDefined: Boolean,
    val exactHeaderFieldIdsDefined: Boolean,
    val exactHeaderByteFramingDefined: Boolean,
    val exactHeaderAadByteEncodingDefined: Boolean,
    val headerSerializationPresent: Boolean,
    val headerParsingPresent: Boolean,
    val headerAuthenticationExecutionPresent: Boolean,
)

data class EncryptedVaultV1KeyHierarchyDecision(
    val policy: EncryptedVaultV1KeyHierarchyPolicy,
    val passphraseKdfTarget: EncryptedVaultV1CanonicalAlgorithm,
    val wrappedRootAeadTarget: EncryptedVaultV1CanonicalAlgorithm,
    val keyExpansionTarget: EncryptedVaultV1CanonicalAlgorithm,
    val manifestAeadTarget: EncryptedVaultV1CanonicalAlgorithm,
    val recordAeadTarget: EncryptedVaultV1CanonicalAlgorithm,
    val keyEncryptionKeyBytes: Int,
    val vaultRootKeyBytes: Int,
    val recordNonceBytes: Int,
    val keyEncryptionKeyDerivedFromPassphrase: Boolean,
    val randomVaultRootKeyRequired: Boolean,
    val vaultRootWrappedByKeyEncryptionKey: Boolean,
    val passphraseRotationRewrapsVaultRoot: Boolean,
    val passphraseRotationRequiresRecordReencryption: Boolean,
    val vaultRootUsedDirectlyForAead: Boolean,
    val hkdfSha256Required: Boolean,
    val manifestRootKeySeparated: Boolean,
    val metadataRecordRootKeySeparated: Boolean,
    val secretRecordRootKeySeparated: Boolean,
    val liveDerivedRootKeyCount: Int,
    val sharedRecordAeadKeyAllowed: Boolean,
    val perRecordKeyRequired: Boolean,
    val perRecordKeyContextIncludesRecordClass: Boolean,
    val perRecordKeyContextIncludesRecordId: Boolean,
    val perRecordKeyContextIncludesSchemaVersion: Boolean,
    val perRecordKeyContextIncludesRecordVersion: Boolean,
    val backupExportUsesIndependentHierarchy: Boolean,
    val backupExportReusesLiveRecordRoots: Boolean,
    val platformWrappingOptional: Boolean,
    val androidPlatformWrappingMayBeReviewedLater: Boolean,
    val linuxV1PassphraseFirst: Boolean,
    val linuxOsKeyringPrimaryStorage: Boolean,
    val keyGenerationPresent: Boolean,
    val keyWrappingPresent: Boolean,
    val keyUnwrappingPresent: Boolean,
    val hkdfExecutionPresent: Boolean,
    val kdfExecutionPresent: Boolean,
    val aeadExecutionPresent: Boolean,
    val runtimeKeyHandlePresent: Boolean,
)

data class EncryptedVaultV1ManifestDecision(
    val policy: EncryptedVaultV1ManifestPolicy,
    val encryptedManifestMaxBytes: Int,
    val manifestRecordCountMax: Int,
    val authoritativeManifestCount: Int,
    val manifestEncrypted: Boolean,
    val manifestAuthenticated: Boolean,
    val propertiesDescribeArchitectureRequirementsOnly: Boolean,
    val plaintextStandaloneManifestAllowed: Boolean,
    val duplicatedEmbeddedManifestAllowed: Boolean,
    val manifestContainsVaultIdConfirmation: Boolean,
    val manifestContainsSchemaVersion: Boolean,
    val manifestContainsGenerationCounter: Boolean,
    val manifestContainsRecordDirectory: Boolean,
    val manifestContainsRecordIds: Boolean,
    val manifestContainsRecordClasses: Boolean,
    val manifestContainsRecordSchemaVersions: Boolean,
    val manifestContainsLatestRecordVersionCounters: Boolean,
    val manifestContainsOpaqueStorageReferences: Boolean,
    val manifestContainsTombstoneState: Boolean,
    val manifestContainsRecoveryCommitMetadata: Boolean,
    val manifestContainsPreviousManifestCommitment: Boolean,
    val manifestContainsSensitiveWalletMetadata: Boolean,
    val filenamesAreNotManifestReplacement: Boolean,
    val manifestSerializationPresent: Boolean,
    val manifestParsingPresent: Boolean,
    val manifestEncryptionExecutionPresent: Boolean,
    val manifestAuthenticationExecutionPresent: Boolean,
    val manifestPersistencePresent: Boolean,
)

data class EncryptedVaultV1RecordDecision(
    val policy: EncryptedVaultV1RecordPolicy,
    val recordPlaintextMaxBytes: Int,
    val recordCiphertextPayloadMaxBytes: Int,
    val recordEnvelopeOverheadBoundRequired: Boolean,
    val exactRecordEnvelopeOverheadMaxDefined: Boolean,
    val exactRecordEnvelopeByteRepresentationDefined: Boolean,
    val freshRandomNoncePerEncryptionRequired: Boolean,
    val nonceBytes: Int,
    val perRecordKeyRequired: Boolean,
    val sharedRecordKeyAllowed: Boolean,
    val recordMetadataEncrypted: Boolean,
    val recordIdEncrypted: Boolean,
    val recordClassEncrypted: Boolean,
    val recordVersionEncrypted: Boolean,
    val recordLabelsEncrypted: Boolean,
    val recordReferencesEncrypted: Boolean,
    val publicRecordFramingMinimal: Boolean,
    val recordFileNameCarriesSemanticIdentity: Boolean,
    val associatedDataIncludesVaultId: Boolean,
    val associatedDataIncludesRecordId: Boolean,
    val associatedDataIncludesRecordClass: Boolean,
    val associatedDataIncludesRecordSchemaVersion: Boolean,
    val associatedDataIncludesRecordVersion: Boolean,
    val associatedDataIncludesFormatAndSuite: Boolean,
    val associatedDataExcludesSensitiveLabelsAndNotes: Boolean,
    val associatedDataExcludesDescriptors: Boolean,
    val associatedDataExcludesAddresses: Boolean,
    val associatedDataExcludesTxids: Boolean,
    val recordSerializationPresent: Boolean,
    val recordParsingPresent: Boolean,
    val recordEncryptionExecutionPresent: Boolean,
    val recordDecryptionExecutionPresent: Boolean,
    val nonceGenerationPresent: Boolean,
)

data class EncryptedVaultV1ProviderDecision(
    val policy: EncryptedVaultV1ProviderRoutingPolicy,
    val allCanonicalProductionCryptoUsesVaultCryptoProvider: Boolean,
    val selectedProviderRequiredForVaultCreate: Boolean,
    val selectedProviderRequiredForVaultOpen: Boolean,
    val selectedProviderRequiredForVaultEncrypt: Boolean,
    val selectedProviderRequiredForVaultDecrypt: Boolean,
    val disabledSelectedProviderBlocksVaultOperations: Boolean,
    val directBouncyCastleCallsAllowedInCanonicalImplementation: Boolean,
    val directTinkCallsAllowedInCanonicalImplementation: Boolean,
    val directJcaJceCallsAllowedInCanonicalImplementation: Boolean,
    val directPlatformRandomCallsAllowedInCanonicalImplementation: Boolean,
    val directSkaldVaultV1PrototypeCryptoCallsAllowedInCanonicalImplementation: Boolean,
    val opaqueProviderKeyHandlesRequired: Boolean,
    val providerLevelKatRequired: Boolean,
    val productionProviderAcceptanceRequired: Boolean,
    val providerImplementationPresent: Boolean,
    val productionProviderSelectable: Boolean,
    val productionSelectionStillDisabledProviderOnly: Boolean,
    val providerSelectionEnabled: Boolean,
    val providerCryptoExecutionPresent: Boolean,
    val existingDirectPrototypeCryptoClassifiedAsNoncanonical: Boolean,
    val existingDirectPrototypeCryptoModifiedInThisPass: Boolean,
)

data class EncryptedVaultV1EncodingAndLimitsDecision(
    val policy: EncryptedVaultV1EncodingPolicy,
    val resourceLimits: EncryptedVaultV1ResourceLimits,
    val fixedBigEndianIntegers: Boolean,
    val fixedFieldIdentifiersRequired: Boolean,
    val explicitLengthsRequired: Boolean,
    val fieldsStrictlyIncreasingByNumericId: Boolean,
    val duplicateFieldsRejected: Boolean,
    val unknownFieldsRejected: Boolean,
    val unknownCriticalFeaturesRejected: Boolean,
    val unknownFeatureBitsRejected: Boolean,
    val unknownNonCriticalFieldsAccepted: Boolean,
    val trailingBytesRejected: Boolean,
    val checkedIntegerArithmeticBeforeAllocation: Boolean,
    val recursiveStructuresAllowed: Boolean,
    val fixedStructuralNestingRequired: Boolean,
    val stringUtf8EncodingRequired: Boolean,
    val stringMaxUtf8Bytes: Int,
    val passphraseEncodedMaxBytes: Int,
    val passphraseNormalizationAlgorithmDefined: Boolean,
    val exactMagicBytesDefined: Boolean,
    val exactNumericFieldIdsDefined: Boolean,
    val exactFieldFramingDefined: Boolean,
    val exactRecordEnvelopeOverheadMaxDefined: Boolean,
    val exactCodecImplemented: Boolean,
    val finalPerDeviceArgon2idParametersSelected: Boolean,
)

data class EncryptedVaultV1AtomicityRollbackDecision(
    val policy: EncryptedVaultV1RollbackPolicy,
    val orderedCommitSteps: List<EncryptedVaultV1AtomicCommitStep>,
    val recordsInstalledBeforeManifestCommit: Boolean,
    val manifestIsFinalCommitPoint: Boolean,
    val temporaryArtifactsRequired: Boolean,
    val quarantineSupportedByFutureDesign: Boolean,
    val destructiveRepairDefaultAllowed: Boolean,
    val manifestGenerationCounterRequired: Boolean,
    val previousManifestCommitmentRequired: Boolean,
    val localInconsistentStateDetectionRequired: Boolean,
    val wholeSnapshotRollbackPreventionGuaranteed: Boolean,
    val trustedExternalMonotonicAnchorPresent: Boolean,
    val appClaimsCompleteRollbackPrevention: Boolean,
    val atomicReplaceImplementationPresent: Boolean,
    val directoryFlushImplementationPresent: Boolean,
    val quarantineImplementationPresent: Boolean,
    val rollbackDetectionImplementationPresent: Boolean,
)

data class EncryptedVaultV1OwnershipFixtureDecision(
    val arrayOwnershipPolicy: EncryptedVaultV1ArrayOwnershipPolicy,
    val sensitiveByteTypesMustBeOrdinaryClasses: Boolean,
    val sensitiveByteTypesMayBeDataClasses: Boolean,
    val constructorInputCopied: Boolean,
    val mutableArrayGetterAllowed: Boolean,
    val generatedSensitiveArrayCopyAllowed: Boolean,
    val defaultSensitiveArrayEqualityAllowed: Boolean,
    val explicitRedactedToStringRequired: Boolean,
    val opaqueKeyHandlesRequired: Boolean,
    val rawKeyGetterAllowed: Boolean,
    val bestEffortClearOnCloseOrLockRequired: Boolean,
    val globalSecretCachingAllowed: Boolean,
    val secretMemoizationAllowed: Boolean,
    val parserResultsRetainPassphraseInput: Boolean,
    val codecResultsRetainRawKeyMaterial: Boolean,
    val fixturePolicy: EncryptedVaultV1FixturePolicy,
    val fixedKatKeysAllowedInProductionSource: Boolean,
    val fixedKatPlaintextAllowedInProductionSource: Boolean,
    val fixedKatCiphertextAllowedInProductionSource: Boolean,
    val fixedSaltsAllowedInProductionSource: Boolean,
    val fixedVaultIdsAllowedInProductionSource: Boolean,
    val fixedRecordIdsAllowedInProductionSource: Boolean,
    val fixedPrototypeHeadersAllowedInProductionSource: Boolean,
    val fixedPrototypeManifestsAllowedInProductionSource: Boolean,
    val fixedRecordReferencesAllowedInProductionSource: Boolean,
    val syntheticParserMarkersAllowedInProductionSource: Boolean,
    val safeProtocolConstantsAllowedInProductionSource: Boolean,
    val fixtureRelocationPresent: Boolean,
    val fixtureRemovalPresent: Boolean,
    val futureFixtureRelocationRequiresSeparatePass: Boolean,
)

data class EncryptedVaultV1ImplementationGates(
    val futurePrototypeQuarantineRequiresSeparatePass: Boolean,
    val futurePrototypeDeprecationRequiresSeparatePass: Boolean,
    val futurePrototypeFixtureRelocationRequiresSeparatePass: Boolean,
    val futureSyntheticParserRelocationOrRemovalRequiresSeparatePass: Boolean,
    val futureCanonicalBinaryLayoutRequiresSeparatePass: Boolean,
    val futureCanonicalMagicAndFieldIdsRequireSeparatePass: Boolean,
    val futureHeaderAadEncodingRequiresSeparatePass: Boolean,
    val futurePassphraseNormalizationDecisionRequiresSeparatePass: Boolean,
    val futureRecordEnvelopeOverheadLimitRequiresSeparatePass: Boolean,
    val futureProviderApiRevisionRequiresSeparatePass: Boolean,
    val futureProductionProviderImplementationRequiresSeparatePass: Boolean,
    val futureProviderSelectionEnablementRequiresSeparatePass: Boolean,
    val futureCanonicalCodecImplementationRequiresSeparatePass: Boolean,
    val futureCanonicalVectorExecutionRequiresSeparatePass: Boolean,
    val futureStoragePathImplementationRequiresSeparatePass: Boolean,
    val futureAtomicStorageImplementationRequiresSeparatePass: Boolean,
    val futureLockSessionImplementationRequiresSeparatePass: Boolean,
    val futureBackupExportFormatRequiresSeparatePass: Boolean,
    val futurePlatformWrappingRequiresSeparatePass: Boolean,
    val futureSecureStorageSuccessRequiresSeparatePass: Boolean,
    val futureSecureMetadataSuccessRequiresSeparatePass: Boolean,
    val futureProductionPersistenceRequiresSeparatePass: Boolean,
    val futureProductionSyncRequiresSeparatePass: Boolean,
    val futureSigningBroadcastingRequiresSeparatePass: Boolean,
    val futureUiActionEnablementRequiresSeparatePass: Boolean,
    val futureEndpointRequiresSeparatePass: Boolean,
    val futureMainnetRequiresExplicitReleaseHardening: Boolean,
    val canonicalBinaryLayoutImplemented: Boolean,
    val canonicalHeaderImplemented: Boolean,
    val canonicalKeyEnvelopeImplemented: Boolean,
    val canonicalManifestImplemented: Boolean,
    val canonicalRecordEnvelopeImplemented: Boolean,
    val canonicalParserImplemented: Boolean,
    val canonicalWriterImplemented: Boolean,
    val canonicalCodecImplemented: Boolean,
    val canonicalVectorExecutionPresent: Boolean,
    val vaultDirectoryCreationPresent: Boolean,
    val vaultFileReadPresent: Boolean,
    val vaultFileWritePresent: Boolean,
    val vaultFileDeletePresent: Boolean,
    val atomicStoragePresent: Boolean,
    val keyGenerationPresent: Boolean,
    val nonceGenerationPresent: Boolean,
    val kdfExecutionPresent: Boolean,
    val hkdfExecutionPresent: Boolean,
    val aeadExecutionPresent: Boolean,
    val encryptionExecutionPresent: Boolean,
    val decryptionExecutionPresent: Boolean,
    val authenticationExecutionPresent: Boolean,
    val providerImplementationPresent: Boolean,
    val productionProviderSelectionEnabled: Boolean,
    val productionProviderSelectable: Boolean,
    val lockSessionImplementationPresent: Boolean,
    val unlockImplementationPresent: Boolean,
    val runtimeVaultRootKeyPresent: Boolean,
    val runtimeSessionKeyPresent: Boolean,
    val secureSecretStorageSuccessPathPresent: Boolean,
    val secureMetadataStorageSuccessPathPresent: Boolean,
    val productionObservationPersistencePresent: Boolean,
    val productionAddressIndexPersistencePresent: Boolean,
    val productionUtxoPersistencePresent: Boolean,
    val productionWalletHistoryPersistencePresent: Boolean,
    val productionSyncPresent: Boolean,
    val productionBackendClientPresent: Boolean,
    val signingBroadcastingPresent: Boolean,
    val uiActionEnablementPresent: Boolean,
    val endpointPresent: Boolean,
    val mainnetPresent: Boolean,
) {
    val implementationBlockerCount: Int
        get() = listOf(
            futurePrototypeQuarantineRequiresSeparatePass,
            futurePrototypeDeprecationRequiresSeparatePass,
            futurePrototypeFixtureRelocationRequiresSeparatePass,
            futureSyntheticParserRelocationOrRemovalRequiresSeparatePass,
            futureCanonicalBinaryLayoutRequiresSeparatePass,
            futureCanonicalMagicAndFieldIdsRequireSeparatePass,
            futureHeaderAadEncodingRequiresSeparatePass,
            futurePassphraseNormalizationDecisionRequiresSeparatePass,
            futureRecordEnvelopeOverheadLimitRequiresSeparatePass,
            futureProviderApiRevisionRequiresSeparatePass,
            futureProductionProviderImplementationRequiresSeparatePass,
            futureProviderSelectionEnablementRequiresSeparatePass,
            futureCanonicalCodecImplementationRequiresSeparatePass,
            futureCanonicalVectorExecutionRequiresSeparatePass,
            futureStoragePathImplementationRequiresSeparatePass,
            futureAtomicStorageImplementationRequiresSeparatePass,
            futureLockSessionImplementationRequiresSeparatePass,
            futureBackupExportFormatRequiresSeparatePass,
            futurePlatformWrappingRequiresSeparatePass,
            futureSecureStorageSuccessRequiresSeparatePass,
            futureSecureMetadataSuccessRequiresSeparatePass,
            futureProductionPersistenceRequiresSeparatePass,
            futureProductionSyncRequiresSeparatePass,
            futureSigningBroadcastingRequiresSeparatePass,
            futureUiActionEnablementRequiresSeparatePass,
            futureEndpointRequiresSeparatePass,
            futureMainnetRequiresExplicitReleaseHardening,
        ).count { it }

    val allFutureSeparatePassRequirementsPresent: Boolean
        get() = implementationBlockerCount == FUTURE_REQUIREMENT_COUNT

    val allCurrentImplementationStateAbsent: Boolean
        get() = listOf(
            canonicalBinaryLayoutImplemented,
            canonicalHeaderImplemented,
            canonicalKeyEnvelopeImplemented,
            canonicalManifestImplemented,
            canonicalRecordEnvelopeImplemented,
            canonicalParserImplemented,
            canonicalWriterImplemented,
            canonicalCodecImplemented,
            canonicalVectorExecutionPresent,
            vaultDirectoryCreationPresent,
            vaultFileReadPresent,
            vaultFileWritePresent,
            vaultFileDeletePresent,
            atomicStoragePresent,
            keyGenerationPresent,
            nonceGenerationPresent,
            kdfExecutionPresent,
            hkdfExecutionPresent,
            aeadExecutionPresent,
            encryptionExecutionPresent,
            decryptionExecutionPresent,
            authenticationExecutionPresent,
            providerImplementationPresent,
            productionProviderSelectionEnabled,
            productionProviderSelectable,
            lockSessionImplementationPresent,
            unlockImplementationPresent,
            runtimeVaultRootKeyPresent,
            runtimeSessionKeyPresent,
            secureSecretStorageSuccessPathPresent,
            secureMetadataStorageSuccessPathPresent,
            productionObservationPersistencePresent,
            productionAddressIndexPersistencePresent,
            productionUtxoPersistencePresent,
            productionWalletHistoryPersistencePresent,
            productionSyncPresent,
            productionBackendClientPresent,
            signingBroadcastingPresent,
            uiActionEnablementPresent,
            endpointPresent,
            mainnetPresent,
        ).none { it }

    private companion object {
        const val FUTURE_REQUIREMENT_COUNT = 27
    }
}

data class EncryptedVaultV1CanonicalArchitectureDecision(
    val decisionId: EncryptedVaultV1CanonicalArchitectureSafeLabel,
    val decisionVersion: Int,
    val decisionKind: EncryptedVaultV1CanonicalArchitectureDecisionKind,
    val sourceSet: EncryptedVaultV1CanonicalArchitectureDecisionSourceSet,
    val decisionStatus: EncryptedVaultV1CanonicalArchitectureDecisionStatus,
    val humanApprovalDate: EncryptedVaultV1CanonicalArchitectureSafeLabel,
    val humanApprovalRecorded: Boolean,
    val reconciliationAuditReviewed: Boolean,
    val canonicalArchitectureDecisionPassed: Boolean,
    val decisionBlockerCount: Int,
    val implementationBlockerCount: Int,
    val canonicalImplementationReady: Boolean,
    val productionAuthorizationPresent: Boolean,
    val canonicalPersistedFormatVersionSelected: Boolean,
    val multiArtifactTopologySelected: Boolean,
    val publicHeaderPolicySelected: Boolean,
    val wrappedRootAeadHeaderAuthenticationSelected: Boolean,
    val noSeparateHeaderHmacSelected: Boolean,
    val randomWrappedVaultRootSelected: Boolean,
    val hkdfDomainSeparationSelected: Boolean,
    val perRecordKeysSelected: Boolean,
    val authoritativeEncryptedManifestSelected: Boolean,
    val mandatoryVaultCryptoProviderRoutingSelected: Boolean,
    val strictV1EncodingSelected: Boolean,
    val hardResourceLimitsSelected: Boolean,
    val atomicCommitPolicySelected: Boolean,
    val rollbackLimitationRecorded: Boolean,
    val ownedArrayRedactionPolicySelected: Boolean,
    val testSourceFixturePolicySelected: Boolean,
    val legacyDisposition: EncryptedVaultV1LegacyDispositionDecision,
    val topology: EncryptedVaultV1TopologyDecision,
    val publicHeader: EncryptedVaultV1PublicHeaderDecision,
    val keyHierarchy: EncryptedVaultV1KeyHierarchyDecision,
    val manifest: EncryptedVaultV1ManifestDecision,
    val record: EncryptedVaultV1RecordDecision,
    val provider: EncryptedVaultV1ProviderDecision,
    val encodingAndLimits: EncryptedVaultV1EncodingAndLimitsDecision,
    val atomicityRollback: EncryptedVaultV1AtomicityRollbackDecision,
    val ownershipFixture: EncryptedVaultV1OwnershipFixtureDecision,
    val implementationGates: EncryptedVaultV1ImplementationGates,
) {
    override fun toString(): String =
        "EncryptedVaultV1CanonicalArchitectureDecision(" +
            "REDACTED, COMMON_MAIN_POLICY, ARCHITECTURE_DECISION_ONLY, " +
            "IMPLEMENTATION_BLOCKED, NO_PRODUCTION_AUTHORIZATION" +
            ")"
}

object EncryptedVaultV1CanonicalArchitecturePolicy {
    fun currentDecision(): EncryptedVaultV1CanonicalArchitectureDecision {
        val resourceLimits = EncryptedVaultV1ResourceLimits(
            publicHeaderMaxBytes = 65_536,
            encryptedManifestMaxBytes = 16_777_216,
            manifestRecordCountMax = 65_536,
            recordPlaintextMaxBytes = 4_194_304,
            recordCiphertextPayloadMaxBytes = 4_194_304,
            stringMaxUtf8Bytes = 4_096,
            passphraseEncodedMaxBytes = 1_024,
            argon2SaltMinBytes = 16,
            argon2SaltMaxBytes = 64,
            argon2MemoryMinKiB = 8_192,
            argon2MemoryMaxKiB = 262_144,
            argon2PassesMin = 1,
            argon2PassesMax = 10,
            argon2LanesMin = 1,
            argon2LanesMax = 4,
            derivedKekBytes = 32,
            vaultRootKeyBytes = 32,
            xChaChaNonceBytes = 24,
            structuralNestingFixedNonRecursive = true,
        )
        val legacyDisposition = EncryptedVaultV1LegacyDispositionDecision(
            containerFormatDisposition =
                EncryptedVaultV1PrototypeDisposition.PreReleasePrototypeP0Unsupported,
            manifestFormatDisposition =
                EncryptedVaultV1PrototypeDisposition.PreReleasePrototypeP0Unsupported,
            headerCommitmentDisposition =
                EncryptedVaultV1PrototypeDisposition.PreReleasePrototypeP0Unsupported,
            recordAeadDisposition =
                EncryptedVaultV1PrototypeDisposition.PreReleasePrototypeP0Unsupported,
            supportingPrototypeArtifactsDisposition =
                EncryptedVaultV1PrototypeDisposition.PreReleasePrototypeP0Unsupported,
            syntheticParserDisposition =
                EncryptedVaultV1SyntheticParserDisposition.SyntheticTestContractClassifierOnly,
            canonicalPersistedFormatVersion = 1,
            prototypeP0BytesAreCanonical = false,
            prototypeP0CompatibilityPromised = false,
            prototypeP0MigrationSupported = false,
            prototypeP0ProductionIntegrationAllowed = false,
            prototypeP0DeveloperDataMustBeRecreated = true,
            prototypeP0DeletionPresent = false,
            prototypeP0DeprecationAnnotationPresent = false,
            prototypeP0SemanticModificationPresent = false,
            syntheticParserIsCanonicalVaultParser = false,
            syntheticParserProductionVaultParsingAllowed = false,
            syntheticParserRealWalletParsingAllowed = false,
            syntheticParserRemovalPresent = false,
            futurePrototypeQuarantineRequiresSeparatePass = true,
            futurePrototypeFixtureRelocationRequiresSeparatePass = true,
            futureSyntheticParserRelocationOrRemovalRequiresSeparatePass = true,
        )
        val topology = EncryptedVaultV1TopologyDecision(
            topology = EncryptedVaultV1Topology.MultiArtifactAppControlledVaultDirectory,
            artifactRoles = EncryptedVaultV1ArtifactRole.entries.toList(),
            appControlledVaultDirectoryRequired = true,
            monolithicLiveVaultFileSelected = false,
            publicHeaderArtifactCount = 1,
            authoritativeManifestArtifactCount = 1,
            recordArtifactsAreIndependent = true,
            temporaryArtifactRolePresent = true,
            quarantineArtifactRolePresent = true,
            backupExportIsSeparateFormat = true,
            backupExportIsLiveVaultArtifact = false,
            filenamesAreSemanticAuthority = false,
            directoryMetadataIsSemanticAuthority = false,
            manifestIsSemanticAuthority = true,
            concreteFileNamesDefined = false,
            concreteDirectoryNamesDefined = false,
            concreteStoragePathsDefined = false,
            directoryCreationPresent = false,
            fileReadPresent = false,
            fileWritePresent = false,
            fileDeletePresent = false,
        )
        val publicHeader = EncryptedVaultV1PublicHeaderDecision(
            authenticationPolicy =
                EncryptedVaultV1HeaderAuthenticationPolicy
                    .WrappedRootAeadAssociatedDataNoSeparateHeaderHmac,
            futureStructuralConcepts = EncryptedVaultV1PublicHeaderConcept.entries.toList(),
            publicHeaderMaxBytes = resourceLimits.publicHeaderMaxBytes,
            publicHeaderContainsSensitiveWalletMetadata = false,
            publicHeaderContainsCredentialMaterial = false,
            separatePersistedHeaderHmacPresent = false,
            separateHeaderCommitmentSelected = false,
            wrappedRootAeadAuthenticatesHeaderContext = true,
            headerAadMustBeCanonical = true,
            headerAadMustBeNonCircular = true,
            headerAadExcludesEnvelopeCiphertextAndTag = true,
            wrongPassphraseAndEnvelopeDamageShareGenericUnlockFailure = true,
            exactMagicBytesDefined = false,
            exactHeaderFieldIdsDefined = false,
            exactHeaderByteFramingDefined = false,
            exactHeaderAadByteEncodingDefined = false,
            headerSerializationPresent = false,
            headerParsingPresent = false,
            headerAuthenticationExecutionPresent = false,
        )
        val keyHierarchy = EncryptedVaultV1KeyHierarchyDecision(
            policy =
                EncryptedVaultV1KeyHierarchyPolicy
                    .Argon2idKekWrappedRandomVaultRootHkdfSeparatedRootsPerRecordKeys,
            passphraseKdfTarget = EncryptedVaultV1CanonicalAlgorithm.Argon2id,
            wrappedRootAeadTarget = EncryptedVaultV1CanonicalAlgorithm.XChaCha20Poly1305,
            keyExpansionTarget = EncryptedVaultV1CanonicalAlgorithm.HkdfSha256,
            manifestAeadTarget = EncryptedVaultV1CanonicalAlgorithm.XChaCha20Poly1305,
            recordAeadTarget = EncryptedVaultV1CanonicalAlgorithm.XChaCha20Poly1305,
            keyEncryptionKeyBytes = resourceLimits.derivedKekBytes,
            vaultRootKeyBytes = resourceLimits.vaultRootKeyBytes,
            recordNonceBytes = resourceLimits.xChaChaNonceBytes,
            keyEncryptionKeyDerivedFromPassphrase = true,
            randomVaultRootKeyRequired = true,
            vaultRootWrappedByKeyEncryptionKey = true,
            passphraseRotationRewrapsVaultRoot = true,
            passphraseRotationRequiresRecordReencryption = false,
            vaultRootUsedDirectlyForAead = false,
            hkdfSha256Required = true,
            manifestRootKeySeparated = true,
            metadataRecordRootKeySeparated = true,
            secretRecordRootKeySeparated = true,
            liveDerivedRootKeyCount = 3,
            sharedRecordAeadKeyAllowed = false,
            perRecordKeyRequired = true,
            perRecordKeyContextIncludesRecordClass = true,
            perRecordKeyContextIncludesRecordId = true,
            perRecordKeyContextIncludesSchemaVersion = true,
            perRecordKeyContextIncludesRecordVersion = true,
            backupExportUsesIndependentHierarchy = true,
            backupExportReusesLiveRecordRoots = false,
            platformWrappingOptional = true,
            androidPlatformWrappingMayBeReviewedLater = true,
            linuxV1PassphraseFirst = true,
            linuxOsKeyringPrimaryStorage = false,
            keyGenerationPresent = false,
            keyWrappingPresent = false,
            keyUnwrappingPresent = false,
            hkdfExecutionPresent = false,
            kdfExecutionPresent = false,
            aeadExecutionPresent = false,
            runtimeKeyHandlePresent = false,
        )
        val manifest = EncryptedVaultV1ManifestDecision(
            policy =
                EncryptedVaultV1ManifestPolicy.SingleAuthoritativeEncryptedAuthenticatedManifest,
            encryptedManifestMaxBytes = resourceLimits.encryptedManifestMaxBytes,
            manifestRecordCountMax = resourceLimits.manifestRecordCountMax,
            authoritativeManifestCount = 1,
            manifestEncrypted = true,
            manifestAuthenticated = true,
            propertiesDescribeArchitectureRequirementsOnly = true,
            plaintextStandaloneManifestAllowed = false,
            duplicatedEmbeddedManifestAllowed = false,
            manifestContainsVaultIdConfirmation = true,
            manifestContainsSchemaVersion = true,
            manifestContainsGenerationCounter = true,
            manifestContainsRecordDirectory = true,
            manifestContainsRecordIds = true,
            manifestContainsRecordClasses = true,
            manifestContainsRecordSchemaVersions = true,
            manifestContainsLatestRecordVersionCounters = true,
            manifestContainsOpaqueStorageReferences = true,
            manifestContainsTombstoneState = true,
            manifestContainsRecoveryCommitMetadata = true,
            manifestContainsPreviousManifestCommitment = true,
            manifestContainsSensitiveWalletMetadata = true,
            filenamesAreNotManifestReplacement = true,
            manifestSerializationPresent = false,
            manifestParsingPresent = false,
            manifestEncryptionExecutionPresent = false,
            manifestAuthenticationExecutionPresent = false,
            manifestPersistencePresent = false,
        )
        val record = EncryptedVaultV1RecordDecision(
            policy = EncryptedVaultV1RecordPolicy.PerRecordKeyFreshXChaChaNonceMinimalPublicEnvelope,
            recordPlaintextMaxBytes = resourceLimits.recordPlaintextMaxBytes,
            recordCiphertextPayloadMaxBytes = resourceLimits.recordCiphertextPayloadMaxBytes,
            recordEnvelopeOverheadBoundRequired = true,
            exactRecordEnvelopeOverheadMaxDefined = false,
            exactRecordEnvelopeByteRepresentationDefined = false,
            freshRandomNoncePerEncryptionRequired = true,
            nonceBytes = resourceLimits.xChaChaNonceBytes,
            perRecordKeyRequired = true,
            sharedRecordKeyAllowed = false,
            recordMetadataEncrypted = true,
            recordIdEncrypted = true,
            recordClassEncrypted = true,
            recordVersionEncrypted = true,
            recordLabelsEncrypted = true,
            recordReferencesEncrypted = true,
            publicRecordFramingMinimal = true,
            recordFileNameCarriesSemanticIdentity = false,
            associatedDataIncludesVaultId = true,
            associatedDataIncludesRecordId = true,
            associatedDataIncludesRecordClass = true,
            associatedDataIncludesRecordSchemaVersion = true,
            associatedDataIncludesRecordVersion = true,
            associatedDataIncludesFormatAndSuite = true,
            associatedDataExcludesSensitiveLabelsAndNotes = true,
            associatedDataExcludesDescriptors = true,
            associatedDataExcludesAddresses = true,
            associatedDataExcludesTxids = true,
            recordSerializationPresent = false,
            recordParsingPresent = false,
            recordEncryptionExecutionPresent = false,
            recordDecryptionExecutionPresent = false,
            nonceGenerationPresent = false,
        )
        val provider = EncryptedVaultV1ProviderDecision(
            policy =
                EncryptedVaultV1ProviderRoutingPolicy.MandatorySelectedSkaldVaultCryptoProvider,
            allCanonicalProductionCryptoUsesVaultCryptoProvider = true,
            selectedProviderRequiredForVaultCreate = true,
            selectedProviderRequiredForVaultOpen = true,
            selectedProviderRequiredForVaultEncrypt = true,
            selectedProviderRequiredForVaultDecrypt = true,
            disabledSelectedProviderBlocksVaultOperations = true,
            directBouncyCastleCallsAllowedInCanonicalImplementation = false,
            directTinkCallsAllowedInCanonicalImplementation = false,
            directJcaJceCallsAllowedInCanonicalImplementation = false,
            directPlatformRandomCallsAllowedInCanonicalImplementation = false,
            directSkaldVaultV1PrototypeCryptoCallsAllowedInCanonicalImplementation = false,
            opaqueProviderKeyHandlesRequired = true,
            providerLevelKatRequired = true,
            productionProviderAcceptanceRequired = true,
            providerImplementationPresent = false,
            productionProviderSelectable = false,
            productionSelectionStillDisabledProviderOnly = true,
            providerSelectionEnabled = false,
            providerCryptoExecutionPresent = false,
            existingDirectPrototypeCryptoClassifiedAsNoncanonical = true,
            existingDirectPrototypeCryptoModifiedInThisPass = false,
        )
        val encodingAndLimits = EncryptedVaultV1EncodingAndLimitsDecision(
            policy =
                EncryptedVaultV1EncodingPolicy
                    .FixedBigEndianStrictOrderedFieldsNoUnknownNoDuplicatesNoTrailingNonRecursive,
            resourceLimits = resourceLimits,
            fixedBigEndianIntegers = true,
            fixedFieldIdentifiersRequired = true,
            explicitLengthsRequired = true,
            fieldsStrictlyIncreasingByNumericId = true,
            duplicateFieldsRejected = true,
            unknownFieldsRejected = true,
            unknownCriticalFeaturesRejected = true,
            unknownFeatureBitsRejected = true,
            unknownNonCriticalFieldsAccepted = false,
            trailingBytesRejected = true,
            checkedIntegerArithmeticBeforeAllocation = true,
            recursiveStructuresAllowed = false,
            fixedStructuralNestingRequired = true,
            stringUtf8EncodingRequired = true,
            stringMaxUtf8Bytes = resourceLimits.stringMaxUtf8Bytes,
            passphraseEncodedMaxBytes = resourceLimits.passphraseEncodedMaxBytes,
            passphraseNormalizationAlgorithmDefined = false,
            exactMagicBytesDefined = false,
            exactNumericFieldIdsDefined = false,
            exactFieldFramingDefined = false,
            exactRecordEnvelopeOverheadMaxDefined = false,
            exactCodecImplemented = false,
            finalPerDeviceArgon2idParametersSelected = false,
        )
        val atomicityRollback = EncryptedVaultV1AtomicityRollbackDecision(
            policy =
                EncryptedVaultV1RollbackPolicy
                    .LocalEvidenceWithoutCompleteWholeSnapshotRollbackPrevention,
            orderedCommitSteps = EncryptedVaultV1AtomicCommitStep.entries.toList(),
            recordsInstalledBeforeManifestCommit = true,
            manifestIsFinalCommitPoint = true,
            temporaryArtifactsRequired = true,
            quarantineSupportedByFutureDesign = true,
            destructiveRepairDefaultAllowed = false,
            manifestGenerationCounterRequired = true,
            previousManifestCommitmentRequired = true,
            localInconsistentStateDetectionRequired = true,
            wholeSnapshotRollbackPreventionGuaranteed = false,
            trustedExternalMonotonicAnchorPresent = false,
            appClaimsCompleteRollbackPrevention = false,
            atomicReplaceImplementationPresent = false,
            directoryFlushImplementationPresent = false,
            quarantineImplementationPresent = false,
            rollbackDetectionImplementationPresent = false,
        )
        val ownershipFixture = EncryptedVaultV1OwnershipFixtureDecision(
            arrayOwnershipPolicy =
                EncryptedVaultV1ArrayOwnershipPolicy.OwnedCopyOpaqueRedactedNoSensitiveDataClass,
            sensitiveByteTypesMustBeOrdinaryClasses = true,
            sensitiveByteTypesMayBeDataClasses = false,
            constructorInputCopied = true,
            mutableArrayGetterAllowed = false,
            generatedSensitiveArrayCopyAllowed = false,
            defaultSensitiveArrayEqualityAllowed = false,
            explicitRedactedToStringRequired = true,
            opaqueKeyHandlesRequired = true,
            rawKeyGetterAllowed = false,
            bestEffortClearOnCloseOrLockRequired = true,
            globalSecretCachingAllowed = false,
            secretMemoizationAllowed = false,
            parserResultsRetainPassphraseInput = false,
            codecResultsRetainRawKeyMaterial = false,
            fixturePolicy = EncryptedVaultV1FixturePolicy.TestSourceOnlyFixedFixtures,
            fixedKatKeysAllowedInProductionSource = false,
            fixedKatPlaintextAllowedInProductionSource = false,
            fixedKatCiphertextAllowedInProductionSource = false,
            fixedSaltsAllowedInProductionSource = false,
            fixedVaultIdsAllowedInProductionSource = false,
            fixedRecordIdsAllowedInProductionSource = false,
            fixedPrototypeHeadersAllowedInProductionSource = false,
            fixedPrototypeManifestsAllowedInProductionSource = false,
            fixedRecordReferencesAllowedInProductionSource = false,
            syntheticParserMarkersAllowedInProductionSource = false,
            safeProtocolConstantsAllowedInProductionSource = true,
            fixtureRelocationPresent = false,
            fixtureRemovalPresent = false,
            futureFixtureRelocationRequiresSeparatePass = true,
        )
        val implementationGates = EncryptedVaultV1ImplementationGates(
            futurePrototypeQuarantineRequiresSeparatePass = true,
            futurePrototypeDeprecationRequiresSeparatePass = true,
            futurePrototypeFixtureRelocationRequiresSeparatePass = true,
            futureSyntheticParserRelocationOrRemovalRequiresSeparatePass = true,
            futureCanonicalBinaryLayoutRequiresSeparatePass = true,
            futureCanonicalMagicAndFieldIdsRequireSeparatePass = true,
            futureHeaderAadEncodingRequiresSeparatePass = true,
            futurePassphraseNormalizationDecisionRequiresSeparatePass = true,
            futureRecordEnvelopeOverheadLimitRequiresSeparatePass = true,
            futureProviderApiRevisionRequiresSeparatePass = true,
            futureProductionProviderImplementationRequiresSeparatePass = true,
            futureProviderSelectionEnablementRequiresSeparatePass = true,
            futureCanonicalCodecImplementationRequiresSeparatePass = true,
            futureCanonicalVectorExecutionRequiresSeparatePass = true,
            futureStoragePathImplementationRequiresSeparatePass = true,
            futureAtomicStorageImplementationRequiresSeparatePass = true,
            futureLockSessionImplementationRequiresSeparatePass = true,
            futureBackupExportFormatRequiresSeparatePass = true,
            futurePlatformWrappingRequiresSeparatePass = true,
            futureSecureStorageSuccessRequiresSeparatePass = true,
            futureSecureMetadataSuccessRequiresSeparatePass = true,
            futureProductionPersistenceRequiresSeparatePass = true,
            futureProductionSyncRequiresSeparatePass = true,
            futureSigningBroadcastingRequiresSeparatePass = true,
            futureUiActionEnablementRequiresSeparatePass = true,
            futureEndpointRequiresSeparatePass = true,
            futureMainnetRequiresExplicitReleaseHardening = true,
            canonicalBinaryLayoutImplemented = false,
            canonicalHeaderImplemented = false,
            canonicalKeyEnvelopeImplemented = false,
            canonicalManifestImplemented = false,
            canonicalRecordEnvelopeImplemented = false,
            canonicalParserImplemented = false,
            canonicalWriterImplemented = false,
            canonicalCodecImplemented = false,
            canonicalVectorExecutionPresent = false,
            vaultDirectoryCreationPresent = false,
            vaultFileReadPresent = false,
            vaultFileWritePresent = false,
            vaultFileDeletePresent = false,
            atomicStoragePresent = false,
            keyGenerationPresent = false,
            nonceGenerationPresent = false,
            kdfExecutionPresent = false,
            hkdfExecutionPresent = false,
            aeadExecutionPresent = false,
            encryptionExecutionPresent = false,
            decryptionExecutionPresent = false,
            authenticationExecutionPresent = false,
            providerImplementationPresent = false,
            productionProviderSelectionEnabled = false,
            productionProviderSelectable = false,
            lockSessionImplementationPresent = false,
            unlockImplementationPresent = false,
            runtimeVaultRootKeyPresent = false,
            runtimeSessionKeyPresent = false,
            secureSecretStorageSuccessPathPresent = false,
            secureMetadataStorageSuccessPathPresent = false,
            productionObservationPersistencePresent = false,
            productionAddressIndexPersistencePresent = false,
            productionUtxoPersistencePresent = false,
            productionWalletHistoryPersistencePresent = false,
            productionSyncPresent = false,
            productionBackendClientPresent = false,
            signingBroadcastingPresent = false,
            uiActionEnablementPresent = false,
            endpointPresent = false,
            mainnetPresent = false,
        )
        val humanApprovalRecorded = true
        val reconciliationAuditReviewed = true
        val decisionBlockerCount = 0
        val implementationBlockerCount = implementationGates.implementationBlockerCount
        val approvedArchitectureSelectionsPresent =
            legacyDisposition.canonicalPersistedFormatVersion == 1 &&
                !legacyDisposition.prototypeP0BytesAreCanonical &&
                !legacyDisposition.syntheticParserIsCanonicalVaultParser &&
                topology.topology ==
                EncryptedVaultV1Topology.MultiArtifactAppControlledVaultDirectory &&
                publicHeader.authenticationPolicy ==
                EncryptedVaultV1HeaderAuthenticationPolicy
                    .WrappedRootAeadAssociatedDataNoSeparateHeaderHmac &&
                keyHierarchy.policy ==
                EncryptedVaultV1KeyHierarchyPolicy
                    .Argon2idKekWrappedRandomVaultRootHkdfSeparatedRootsPerRecordKeys &&
                manifest.policy ==
                EncryptedVaultV1ManifestPolicy.SingleAuthoritativeEncryptedAuthenticatedManifest &&
                record.policy ==
                EncryptedVaultV1RecordPolicy.PerRecordKeyFreshXChaChaNonceMinimalPublicEnvelope &&
                provider.policy ==
                EncryptedVaultV1ProviderRoutingPolicy.MandatorySelectedSkaldVaultCryptoProvider &&
                encodingAndLimits.policy ==
                EncryptedVaultV1EncodingPolicy
                    .FixedBigEndianStrictOrderedFieldsNoUnknownNoDuplicatesNoTrailingNonRecursive &&
                resourceLimits.publicHeaderMaxBytes == 65_536 &&
                resourceLimits.encryptedManifestMaxBytes == 16_777_216 &&
                resourceLimits.recordPlaintextMaxBytes == 4_194_304 &&
                resourceLimits.recordCiphertextPayloadMaxBytes == 4_194_304
        val canonicalArchitectureDecisionPassed =
            humanApprovalRecorded &&
                reconciliationAuditReviewed &&
                approvedArchitectureSelectionsPresent &&
                decisionBlockerCount == 0 &&
                implementationBlockerCount > 0 &&
                implementationGates.allFutureSeparatePassRequirementsPresent &&
                implementationGates.allCurrentImplementationStateAbsent

        return EncryptedVaultV1CanonicalArchitectureDecision(
            decisionId = EncryptedVaultV1CanonicalArchitectureSafeLabel(
                "skald-encrypted-local-vault-v1-canonical-architecture-decision",
            ),
            decisionVersion = 1,
            decisionKind =
                EncryptedVaultV1CanonicalArchitectureDecisionKind
                    .EncryptedLocalVaultV1CanonicalArchitectureDecision,
            sourceSet = EncryptedVaultV1CanonicalArchitectureDecisionSourceSet.CommonMainPolicy,
            decisionStatus =
                EncryptedVaultV1CanonicalArchitectureDecisionStatus
                    .CanonicalArchitectureSelectedImplementationBlocked,
            humanApprovalDate = EncryptedVaultV1CanonicalArchitectureSafeLabel("2026-07-12"),
            humanApprovalRecorded = humanApprovalRecorded,
            reconciliationAuditReviewed = reconciliationAuditReviewed,
            canonicalArchitectureDecisionPassed = canonicalArchitectureDecisionPassed,
            decisionBlockerCount = decisionBlockerCount,
            implementationBlockerCount = implementationBlockerCount,
            canonicalImplementationReady = false,
            productionAuthorizationPresent = false,
            canonicalPersistedFormatVersionSelected = true,
            multiArtifactTopologySelected = true,
            publicHeaderPolicySelected = true,
            wrappedRootAeadHeaderAuthenticationSelected = true,
            noSeparateHeaderHmacSelected = true,
            randomWrappedVaultRootSelected = true,
            hkdfDomainSeparationSelected = true,
            perRecordKeysSelected = true,
            authoritativeEncryptedManifestSelected = true,
            mandatoryVaultCryptoProviderRoutingSelected = true,
            strictV1EncodingSelected = true,
            hardResourceLimitsSelected = true,
            atomicCommitPolicySelected = true,
            rollbackLimitationRecorded = true,
            ownedArrayRedactionPolicySelected = true,
            testSourceFixturePolicySelected = true,
            legacyDisposition = legacyDisposition,
            topology = topology,
            publicHeader = publicHeader,
            keyHierarchy = keyHierarchy,
            manifest = manifest,
            record = record,
            provider = provider,
            encodingAndLimits = encodingAndLimits,
            atomicityRollback = atomicityRollback,
            ownershipFixture = ownershipFixture,
            implementationGates = implementationGates,
        )
    }
}
