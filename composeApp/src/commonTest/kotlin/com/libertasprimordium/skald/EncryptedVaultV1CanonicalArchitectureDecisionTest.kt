package com.libertasprimordium.skald

import com.libertasprimordium.skald.security.DisabledVaultCryptoProvider
import com.libertasprimordium.skald.security.EncryptedVaultV1ArrayOwnershipPolicy
import com.libertasprimordium.skald.security.EncryptedVaultV1ArtifactRole
import com.libertasprimordium.skald.security.EncryptedVaultV1AtomicCommitStep
import com.libertasprimordium.skald.security.EncryptedVaultV1CanonicalAlgorithm
import com.libertasprimordium.skald.security.EncryptedVaultV1CanonicalArchitectureDecision
import com.libertasprimordium.skald.security.EncryptedVaultV1CanonicalArchitectureDecisionKind
import com.libertasprimordium.skald.security.EncryptedVaultV1CanonicalArchitectureDecisionSourceSet
import com.libertasprimordium.skald.security.EncryptedVaultV1CanonicalArchitectureDecisionStatus
import com.libertasprimordium.skald.security.EncryptedVaultV1CanonicalArchitecturePolicy
import com.libertasprimordium.skald.security.EncryptedVaultV1EncodingPolicy
import com.libertasprimordium.skald.security.EncryptedVaultV1FixturePolicy
import com.libertasprimordium.skald.security.EncryptedVaultV1HeaderAuthenticationPolicy
import com.libertasprimordium.skald.security.EncryptedVaultV1KeyHierarchyPolicy
import com.libertasprimordium.skald.security.EncryptedVaultV1ManifestPolicy
import com.libertasprimordium.skald.security.EncryptedVaultV1PrototypeDisposition
import com.libertasprimordium.skald.security.EncryptedVaultV1ProviderRoutingPolicy
import com.libertasprimordium.skald.security.EncryptedVaultV1PublicHeaderConcept
import com.libertasprimordium.skald.security.EncryptedVaultV1RecordPolicy
import com.libertasprimordium.skald.security.EncryptedVaultV1RollbackPolicy
import com.libertasprimordium.skald.security.EncryptedVaultV1SyntheticParserDisposition
import com.libertasprimordium.skald.security.EncryptedVaultV1Topology
import com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class EncryptedVaultV1CanonicalArchitectureDecisionTest {
    private fun decision(): EncryptedVaultV1CanonicalArchitectureDecision =
        EncryptedVaultV1CanonicalArchitecturePolicy.currentDecision()

    @Test
    fun decisionIdentityRecordsHumanApprovalWithoutProductionAuthorization() {
        val decision = decision()

        assertEquals(
            "skald-encrypted-local-vault-v1-canonical-architecture-decision",
            decision.decisionId.value,
        )
        assertEquals(1, decision.decisionVersion)
        assertEquals(
            EncryptedVaultV1CanonicalArchitectureDecisionKind
                .EncryptedLocalVaultV1CanonicalArchitectureDecision,
            decision.decisionKind,
        )
        assertEquals(
            "ENCRYPTED_LOCAL_VAULT_V1_CANONICAL_ARCHITECTURE_DECISION",
            decision.decisionKind.label,
        )
        assertEquals(
            EncryptedVaultV1CanonicalArchitectureDecisionSourceSet.CommonMainPolicy,
            decision.sourceSet,
        )
        assertEquals("COMMON_MAIN_POLICY", decision.sourceSet.label)
        assertEquals(
            EncryptedVaultV1CanonicalArchitectureDecisionStatus
                .CanonicalArchitectureSelectedImplementationBlocked,
            decision.decisionStatus,
        )
        assertEquals(
            "CANONICAL_ARCHITECTURE_SELECTED_IMPLEMENTATION_BLOCKED",
            decision.decisionStatus.label,
        )
        assertEquals("2026-07-12", decision.humanApprovalDate.value)
        assertTrue(decision.humanApprovalRecorded)
        assertTrue(decision.reconciliationAuditReviewed)
        assertTrue(decision.canonicalArchitectureDecisionPassed)
        assertEquals(0, decision.decisionBlockerCount)
        assertTrue(decision.implementationBlockerCount > 0)
        assertEquals(decision.implementationGates.implementationBlockerCount, decision.implementationBlockerCount)
        assertFalse(decision.canonicalImplementationReady)
        assertFalse(decision.productionAuthorizationPresent)

        assertAllTrue(
            "canonicalPersistedFormatVersionSelected" to decision.canonicalPersistedFormatVersionSelected,
            "multiArtifactTopologySelected" to decision.multiArtifactTopologySelected,
            "publicHeaderPolicySelected" to decision.publicHeaderPolicySelected,
            "wrappedRootAeadHeaderAuthenticationSelected" to
                decision.wrappedRootAeadHeaderAuthenticationSelected,
            "noSeparateHeaderHmacSelected" to decision.noSeparateHeaderHmacSelected,
            "randomWrappedVaultRootSelected" to decision.randomWrappedVaultRootSelected,
            "hkdfDomainSeparationSelected" to decision.hkdfDomainSeparationSelected,
            "perRecordKeysSelected" to decision.perRecordKeysSelected,
            "authoritativeEncryptedManifestSelected" to decision.authoritativeEncryptedManifestSelected,
            "mandatoryVaultCryptoProviderRoutingSelected" to
                decision.mandatoryVaultCryptoProviderRoutingSelected,
            "strictV1EncodingSelected" to decision.strictV1EncodingSelected,
            "hardResourceLimitsSelected" to decision.hardResourceLimitsSelected,
            "atomicCommitPolicySelected" to decision.atomicCommitPolicySelected,
            "rollbackLimitationRecorded" to decision.rollbackLimitationRecorded,
            "ownedArrayRedactionPolicySelected" to decision.ownedArrayRedactionPolicySelected,
            "testSourceFixturePolicySelected" to decision.testSourceFixturePolicySelected,
        )
    }

    @Test
    fun prototypeAndSyntheticParserDispositionsAreExactAndNonMigrating() {
        val legacy = decision().legacyDisposition
        val prototype = EncryptedVaultV1PrototypeDisposition.PreReleasePrototypeP0Unsupported

        assertEquals("PRE_RELEASE_PROTOTYPE_P0_UNSUPPORTED", prototype.label)
        assertEquals(prototype, legacy.containerFormatDisposition)
        assertEquals(prototype, legacy.manifestFormatDisposition)
        assertEquals(prototype, legacy.headerCommitmentDisposition)
        assertEquals(prototype, legacy.recordAeadDisposition)
        assertEquals(prototype, legacy.supportingPrototypeArtifactsDisposition)
        assertEquals(
            EncryptedVaultV1SyntheticParserDisposition.SyntheticTestContractClassifierOnly,
            legacy.syntheticParserDisposition,
        )
        assertEquals("SYNTHETIC_TEST_CONTRACT_CLASSIFIER_ONLY", legacy.syntheticParserDisposition.label)
        assertEquals(1, legacy.canonicalPersistedFormatVersion)
        assertFalse(legacy.prototypeP0BytesAreCanonical)
        assertFalse(legacy.prototypeP0CompatibilityPromised)
        assertFalse(legacy.prototypeP0MigrationSupported)
        assertFalse(legacy.prototypeP0ProductionIntegrationAllowed)
        assertTrue(legacy.prototypeP0DeveloperDataMustBeRecreated)
        assertFalse(legacy.prototypeP0DeletionPresent)
        assertFalse(legacy.prototypeP0DeprecationAnnotationPresent)
        assertFalse(legacy.prototypeP0SemanticModificationPresent)
        assertFalse(legacy.syntheticParserIsCanonicalVaultParser)
        assertFalse(legacy.syntheticParserProductionVaultParsingAllowed)
        assertFalse(legacy.syntheticParserRealWalletParsingAllowed)
        assertFalse(legacy.syntheticParserRemovalPresent)
        assertTrue(legacy.futurePrototypeQuarantineRequiresSeparatePass)
        assertTrue(legacy.futurePrototypeFixtureRelocationRequiresSeparatePass)
        assertTrue(legacy.futureSyntheticParserRelocationOrRemovalRequiresSeparatePass)
    }

    @Test
    fun topologySelectsOneManifestAndIndependentArtifactsWithoutFileOperations() {
        val topology = decision().topology

        assertEquals(
            EncryptedVaultV1Topology.MultiArtifactAppControlledVaultDirectory,
            topology.topology,
        )
        assertEquals("MULTI_ARTIFACT_APP_CONTROLLED_VAULT_DIRECTORY", topology.topology.label)
        assertEquals(EncryptedVaultV1ArtifactRole.entries.toList(), topology.artifactRoles)
        assertEquals(
            listOf(
                "skald-vault-v1-public-header-role",
                "skald-vault-v1-authoritative-encrypted-manifest-role",
                "skald-vault-v1-encrypted-record-role",
                "skald-vault-v1-temporary-staging-role",
                "skald-vault-v1-quarantine-recovery-role",
                "skald-vault-v1-separate-backup-export-role",
            ),
            topology.artifactRoles.map { role -> role.safeLabel.value },
        )
        assertTrue(topology.appControlledVaultDirectoryRequired)
        assertFalse(topology.monolithicLiveVaultFileSelected)
        assertEquals(1, topology.publicHeaderArtifactCount)
        assertEquals(1, topology.authoritativeManifestArtifactCount)
        assertTrue(topology.recordArtifactsAreIndependent)
        assertTrue(topology.temporaryArtifactRolePresent)
        assertTrue(topology.quarantineArtifactRolePresent)
        assertTrue(topology.backupExportIsSeparateFormat)
        assertFalse(topology.backupExportIsLiveVaultArtifact)
        assertFalse(topology.filenamesAreSemanticAuthority)
        assertFalse(topology.directoryMetadataIsSemanticAuthority)
        assertTrue(topology.manifestIsSemanticAuthority)
        assertAllFalse(
            "concreteFileNamesDefined" to topology.concreteFileNamesDefined,
            "concreteDirectoryNamesDefined" to topology.concreteDirectoryNamesDefined,
            "concreteStoragePathsDefined" to topology.concreteStoragePathsDefined,
            "directoryCreationPresent" to topology.directoryCreationPresent,
            "fileReadPresent" to topology.fileReadPresent,
            "fileWritePresent" to topology.fileWritePresent,
            "fileDeletePresent" to topology.fileDeletePresent,
        )
    }

    @Test
    fun publicHeaderPolicyUsesWrappedRootAadWithoutSeparateCommitment() {
        val header = decision().publicHeader

        assertEquals(
            EncryptedVaultV1HeaderAuthenticationPolicy
                .WrappedRootAeadAssociatedDataNoSeparateHeaderHmac,
            header.authenticationPolicy,
        )
        assertEquals(
            "WRAPPED_ROOT_AEAD_ASSOCIATED_DATA_NO_SEPARATE_HEADER_HMAC",
            header.authenticationPolicy.label,
        )
        assertEquals(EncryptedVaultV1PublicHeaderConcept.entries.toList(), header.futureStructuralConcepts)
        assertEquals(9, header.futureStructuralConcepts.size)
        assertEquals(65_536, header.publicHeaderMaxBytes)
        assertFalse(header.publicHeaderContainsSensitiveWalletMetadata)
        assertFalse(header.publicHeaderContainsCredentialMaterial)
        assertFalse(header.separatePersistedHeaderHmacPresent)
        assertFalse(header.separateHeaderCommitmentSelected)
        assertTrue(header.wrappedRootAeadAuthenticatesHeaderContext)
        assertTrue(header.headerAadMustBeCanonical)
        assertTrue(header.headerAadMustBeNonCircular)
        assertTrue(header.headerAadExcludesEnvelopeCiphertextAndTag)
        assertTrue(header.wrongPassphraseAndEnvelopeDamageShareGenericUnlockFailure)
        assertAllFalse(
            "exactMagicBytesDefined" to header.exactMagicBytesDefined,
            "exactHeaderFieldIdsDefined" to header.exactHeaderFieldIdsDefined,
            "exactHeaderByteFramingDefined" to header.exactHeaderByteFramingDefined,
            "exactHeaderAadByteEncodingDefined" to header.exactHeaderAadByteEncodingDefined,
            "headerSerializationPresent" to header.headerSerializationPresent,
            "headerParsingPresent" to header.headerParsingPresent,
            "headerAuthenticationExecutionPresent" to header.headerAuthenticationExecutionPresent,
        )
    }

    @Test
    fun keyHierarchyRequiresRandomWrappedRootSeparatedRootsAndPerRecordKeys() {
        val keys = decision().keyHierarchy

        assertEquals(
            EncryptedVaultV1KeyHierarchyPolicy
                .Argon2idKekWrappedRandomVaultRootHkdfSeparatedRootsPerRecordKeys,
            keys.policy,
        )
        assertEquals(
            "ARGON2ID_KEK_WRAPPED_RANDOM_VAULT_ROOT_HKDF_SEPARATED_ROOTS_PER_RECORD_KEYS",
            keys.policy.label,
        )
        assertEquals(EncryptedVaultV1CanonicalAlgorithm.Argon2id, keys.passphraseKdfTarget)
        assertEquals(EncryptedVaultV1CanonicalAlgorithm.XChaCha20Poly1305, keys.wrappedRootAeadTarget)
        assertEquals(EncryptedVaultV1CanonicalAlgorithm.HkdfSha256, keys.keyExpansionTarget)
        assertEquals(EncryptedVaultV1CanonicalAlgorithm.XChaCha20Poly1305, keys.manifestAeadTarget)
        assertEquals(EncryptedVaultV1CanonicalAlgorithm.XChaCha20Poly1305, keys.recordAeadTarget)
        assertEquals(32, keys.keyEncryptionKeyBytes)
        assertEquals(32, keys.vaultRootKeyBytes)
        assertEquals(24, keys.recordNonceBytes)
        assertTrue(keys.keyEncryptionKeyDerivedFromPassphrase)
        assertTrue(keys.randomVaultRootKeyRequired)
        assertTrue(keys.vaultRootWrappedByKeyEncryptionKey)
        assertTrue(keys.passphraseRotationRewrapsVaultRoot)
        assertFalse(keys.passphraseRotationRequiresRecordReencryption)
        assertFalse(keys.vaultRootUsedDirectlyForAead)
        assertTrue(keys.hkdfSha256Required)
        assertTrue(keys.manifestRootKeySeparated)
        assertTrue(keys.metadataRecordRootKeySeparated)
        assertTrue(keys.secretRecordRootKeySeparated)
        assertEquals(3, keys.liveDerivedRootKeyCount)
        assertFalse(keys.sharedRecordAeadKeyAllowed)
        assertTrue(keys.perRecordKeyRequired)
        assertTrue(keys.perRecordKeyContextIncludesRecordClass)
        assertTrue(keys.perRecordKeyContextIncludesRecordId)
        assertTrue(keys.perRecordKeyContextIncludesSchemaVersion)
        assertTrue(keys.perRecordKeyContextIncludesRecordVersion)
        assertTrue(keys.backupExportUsesIndependentHierarchy)
        assertFalse(keys.backupExportReusesLiveRecordRoots)
        assertTrue(keys.platformWrappingOptional)
        assertTrue(keys.androidPlatformWrappingMayBeReviewedLater)
        assertTrue(keys.linuxV1PassphraseFirst)
        assertFalse(keys.linuxOsKeyringPrimaryStorage)
        assertAllFalse(
            "keyGenerationPresent" to keys.keyGenerationPresent,
            "keyWrappingPresent" to keys.keyWrappingPresent,
            "keyUnwrappingPresent" to keys.keyUnwrappingPresent,
            "hkdfExecutionPresent" to keys.hkdfExecutionPresent,
            "kdfExecutionPresent" to keys.kdfExecutionPresent,
            "aeadExecutionPresent" to keys.aeadExecutionPresent,
            "runtimeKeyHandlePresent" to keys.runtimeKeyHandlePresent,
        )
    }

    @Test
    fun manifestIsOneEncryptedAuthenticatedAuthorityWithoutCodecOrPersistence() {
        val manifest = decision().manifest

        assertEquals(
            EncryptedVaultV1ManifestPolicy.SingleAuthoritativeEncryptedAuthenticatedManifest,
            manifest.policy,
        )
        assertEquals("SINGLE_AUTHORITATIVE_ENCRYPTED_AUTHENTICATED_MANIFEST", manifest.policy.label)
        assertEquals(16_777_216, manifest.encryptedManifestMaxBytes)
        assertEquals(65_536, manifest.manifestRecordCountMax)
        assertEquals(1, manifest.authoritativeManifestCount)
        assertTrue(manifest.manifestEncrypted)
        assertTrue(manifest.manifestAuthenticated)
        assertTrue(manifest.propertiesDescribeArchitectureRequirementsOnly)
        assertFalse(manifest.plaintextStandaloneManifestAllowed)
        assertFalse(manifest.duplicatedEmbeddedManifestAllowed)
        assertAllTrue(
            "manifestContainsVaultIdConfirmation" to manifest.manifestContainsVaultIdConfirmation,
            "manifestContainsSchemaVersion" to manifest.manifestContainsSchemaVersion,
            "manifestContainsGenerationCounter" to manifest.manifestContainsGenerationCounter,
            "manifestContainsRecordDirectory" to manifest.manifestContainsRecordDirectory,
            "manifestContainsRecordIds" to manifest.manifestContainsRecordIds,
            "manifestContainsRecordClasses" to manifest.manifestContainsRecordClasses,
            "manifestContainsRecordSchemaVersions" to manifest.manifestContainsRecordSchemaVersions,
            "manifestContainsLatestRecordVersionCounters" to
                manifest.manifestContainsLatestRecordVersionCounters,
            "manifestContainsOpaqueStorageReferences" to manifest.manifestContainsOpaqueStorageReferences,
            "manifestContainsTombstoneState" to manifest.manifestContainsTombstoneState,
            "manifestContainsRecoveryCommitMetadata" to manifest.manifestContainsRecoveryCommitMetadata,
            "manifestContainsPreviousManifestCommitment" to
                manifest.manifestContainsPreviousManifestCommitment,
            "manifestContainsSensitiveWalletMetadata" to manifest.manifestContainsSensitiveWalletMetadata,
            "filenamesAreNotManifestReplacement" to manifest.filenamesAreNotManifestReplacement,
        )
        assertAllFalse(
            "manifestSerializationPresent" to manifest.manifestSerializationPresent,
            "manifestParsingPresent" to manifest.manifestParsingPresent,
            "manifestEncryptionExecutionPresent" to manifest.manifestEncryptionExecutionPresent,
            "manifestAuthenticationExecutionPresent" to manifest.manifestAuthenticationExecutionPresent,
            "manifestPersistencePresent" to manifest.manifestPersistencePresent,
        )
    }

    @Test
    fun recordPolicyKeepsIdentityEncryptedAndBindsOnlyApprovedAadContext() {
        val record = decision().record

        assertEquals(
            EncryptedVaultV1RecordPolicy.PerRecordKeyFreshXChaChaNonceMinimalPublicEnvelope,
            record.policy,
        )
        assertEquals("PER_RECORD_KEY_FRESH_XCHACHA_NONCE_MINIMAL_PUBLIC_ENVELOPE", record.policy.label)
        assertEquals(4_194_304, record.recordPlaintextMaxBytes)
        assertEquals(4_194_304, record.recordCiphertextPayloadMaxBytes)
        assertTrue(record.recordEnvelopeOverheadBoundRequired)
        assertFalse(record.exactRecordEnvelopeOverheadMaxDefined)
        assertFalse(record.exactRecordEnvelopeByteRepresentationDefined)
        assertTrue(record.freshRandomNoncePerEncryptionRequired)
        assertEquals(24, record.nonceBytes)
        assertTrue(record.perRecordKeyRequired)
        assertFalse(record.sharedRecordKeyAllowed)
        assertAllTrue(
            "recordMetadataEncrypted" to record.recordMetadataEncrypted,
            "recordIdEncrypted" to record.recordIdEncrypted,
            "recordClassEncrypted" to record.recordClassEncrypted,
            "recordVersionEncrypted" to record.recordVersionEncrypted,
            "recordLabelsEncrypted" to record.recordLabelsEncrypted,
            "recordReferencesEncrypted" to record.recordReferencesEncrypted,
            "publicRecordFramingMinimal" to record.publicRecordFramingMinimal,
            "associatedDataIncludesVaultId" to record.associatedDataIncludesVaultId,
            "associatedDataIncludesRecordId" to record.associatedDataIncludesRecordId,
            "associatedDataIncludesRecordClass" to record.associatedDataIncludesRecordClass,
            "associatedDataIncludesRecordSchemaVersion" to record.associatedDataIncludesRecordSchemaVersion,
            "associatedDataIncludesRecordVersion" to record.associatedDataIncludesRecordVersion,
            "associatedDataIncludesFormatAndSuite" to record.associatedDataIncludesFormatAndSuite,
            "associatedDataExcludesSensitiveLabelsAndNotes" to
                record.associatedDataExcludesSensitiveLabelsAndNotes,
            "associatedDataExcludesDescriptors" to record.associatedDataExcludesDescriptors,
            "associatedDataExcludesAddresses" to record.associatedDataExcludesAddresses,
            "associatedDataExcludesTxids" to record.associatedDataExcludesTxids,
        )
        assertFalse(record.recordFileNameCarriesSemanticIdentity)
        assertAllFalse(
            "recordSerializationPresent" to record.recordSerializationPresent,
            "recordParsingPresent" to record.recordParsingPresent,
            "recordEncryptionExecutionPresent" to record.recordEncryptionExecutionPresent,
            "recordDecryptionExecutionPresent" to record.recordDecryptionExecutionPresent,
            "nonceGenerationPresent" to record.nonceGenerationPresent,
        )
    }

    @Test
    fun providerRoutingIsMandatoryWhileDisabledSelectionStillBlocksOperations() {
        val provider = decision().provider
        val selection = VaultCryptoProviderSelectionRegistry.select()

        assertEquals(
            EncryptedVaultV1ProviderRoutingPolicy.MandatorySelectedSkaldVaultCryptoProvider,
            provider.policy,
        )
        assertEquals("MANDATORY_SELECTED_SKALD_VAULT_CRYPTO_PROVIDER", provider.policy.label)
        assertAllTrue(
            "allCanonicalProductionCryptoUsesVaultCryptoProvider" to
                provider.allCanonicalProductionCryptoUsesVaultCryptoProvider,
            "selectedProviderRequiredForVaultCreate" to provider.selectedProviderRequiredForVaultCreate,
            "selectedProviderRequiredForVaultOpen" to provider.selectedProviderRequiredForVaultOpen,
            "selectedProviderRequiredForVaultEncrypt" to provider.selectedProviderRequiredForVaultEncrypt,
            "selectedProviderRequiredForVaultDecrypt" to provider.selectedProviderRequiredForVaultDecrypt,
            "disabledSelectedProviderBlocksVaultOperations" to
                provider.disabledSelectedProviderBlocksVaultOperations,
            "opaqueProviderKeyHandlesRequired" to provider.opaqueProviderKeyHandlesRequired,
            "providerLevelKatRequired" to provider.providerLevelKatRequired,
            "productionProviderAcceptanceRequired" to provider.productionProviderAcceptanceRequired,
            "productionSelectionStillDisabledProviderOnly" to
                provider.productionSelectionStillDisabledProviderOnly,
            "existingDirectPrototypeCryptoClassifiedAsNoncanonical" to
                provider.existingDirectPrototypeCryptoClassifiedAsNoncanonical,
        )
        assertAllFalse(
            "directBouncyCastleCallsAllowedInCanonicalImplementation" to
                provider.directBouncyCastleCallsAllowedInCanonicalImplementation,
            "directTinkCallsAllowedInCanonicalImplementation" to
                provider.directTinkCallsAllowedInCanonicalImplementation,
            "directJcaJceCallsAllowedInCanonicalImplementation" to
                provider.directJcaJceCallsAllowedInCanonicalImplementation,
            "directPlatformRandomCallsAllowedInCanonicalImplementation" to
                provider.directPlatformRandomCallsAllowedInCanonicalImplementation,
            "directSkaldVaultV1PrototypeCryptoCallsAllowedInCanonicalImplementation" to
                provider.directSkaldVaultV1PrototypeCryptoCallsAllowedInCanonicalImplementation,
            "providerImplementationPresent" to provider.providerImplementationPresent,
            "productionProviderSelectable" to provider.productionProviderSelectable,
            "providerSelectionEnabled" to provider.providerSelectionEnabled,
            "providerCryptoExecutionPresent" to provider.providerCryptoExecutionPresent,
            "existingDirectPrototypeCryptoModifiedInThisPass" to
                provider.existingDirectPrototypeCryptoModifiedInThisPass,
        )
        assertTrue(selection.selectedProviderIsDisabled)
        assertIs<DisabledVaultCryptoProvider>(selection.selectedProvider)
        assertFalse(selection.productionProviderSelectable)
    }

    @Test
    fun strictEncodingAndAllApprovedResourceLimitsAreExact() {
        val encoding = decision().encodingAndLimits
        val limits = encoding.resourceLimits

        assertEquals(
            EncryptedVaultV1EncodingPolicy
                .FixedBigEndianStrictOrderedFieldsNoUnknownNoDuplicatesNoTrailingNonRecursive,
            encoding.policy,
        )
        assertEquals(
            "FIXED_BIG_ENDIAN_STRICT_ORDERED_FIELDS_NO_UNKNOWN_NO_DUPLICATES_NO_TRAILING_NON_RECURSIVE",
            encoding.policy.label,
        )
        assertAllTrue(
            "fixedBigEndianIntegers" to encoding.fixedBigEndianIntegers,
            "fixedFieldIdentifiersRequired" to encoding.fixedFieldIdentifiersRequired,
            "explicitLengthsRequired" to encoding.explicitLengthsRequired,
            "fieldsStrictlyIncreasingByNumericId" to encoding.fieldsStrictlyIncreasingByNumericId,
            "duplicateFieldsRejected" to encoding.duplicateFieldsRejected,
            "unknownFieldsRejected" to encoding.unknownFieldsRejected,
            "unknownCriticalFeaturesRejected" to encoding.unknownCriticalFeaturesRejected,
            "unknownFeatureBitsRejected" to encoding.unknownFeatureBitsRejected,
            "trailingBytesRejected" to encoding.trailingBytesRejected,
            "checkedIntegerArithmeticBeforeAllocation" to
                encoding.checkedIntegerArithmeticBeforeAllocation,
            "fixedStructuralNestingRequired" to encoding.fixedStructuralNestingRequired,
            "stringUtf8EncodingRequired" to encoding.stringUtf8EncodingRequired,
        )
        assertFalse(encoding.unknownNonCriticalFieldsAccepted)
        assertFalse(encoding.recursiveStructuresAllowed)
        assertEquals(4_096, encoding.stringMaxUtf8Bytes)
        assertEquals(1_024, encoding.passphraseEncodedMaxBytes)
        assertAllFalse(
            "passphraseNormalizationAlgorithmDefined" to encoding.passphraseNormalizationAlgorithmDefined,
            "exactMagicBytesDefined" to encoding.exactMagicBytesDefined,
            "exactNumericFieldIdsDefined" to encoding.exactNumericFieldIdsDefined,
            "exactFieldFramingDefined" to encoding.exactFieldFramingDefined,
            "exactRecordEnvelopeOverheadMaxDefined" to encoding.exactRecordEnvelopeOverheadMaxDefined,
            "exactCodecImplemented" to encoding.exactCodecImplemented,
            "finalPerDeviceArgon2idParametersSelected" to encoding.finalPerDeviceArgon2idParametersSelected,
        )

        assertEquals(65_536, limits.publicHeaderMaxBytes)
        assertEquals(16_777_216, limits.encryptedManifestMaxBytes)
        assertEquals(65_536, limits.manifestRecordCountMax)
        assertEquals(4_194_304, limits.recordPlaintextMaxBytes)
        assertEquals(4_194_304, limits.recordCiphertextPayloadMaxBytes)
        assertEquals(4_096, limits.stringMaxUtf8Bytes)
        assertEquals(1_024, limits.passphraseEncodedMaxBytes)
        assertEquals(16, limits.argon2SaltMinBytes)
        assertEquals(64, limits.argon2SaltMaxBytes)
        assertEquals(8_192, limits.argon2MemoryMinKiB)
        assertEquals(262_144, limits.argon2MemoryMaxKiB)
        assertEquals(1, limits.argon2PassesMin)
        assertEquals(10, limits.argon2PassesMax)
        assertEquals(1, limits.argon2LanesMin)
        assertEquals(4, limits.argon2LanesMax)
        assertEquals(32, limits.derivedKekBytes)
        assertEquals(32, limits.vaultRootKeyBytes)
        assertEquals(24, limits.xChaChaNonceBytes)
        assertTrue(limits.structuralNestingFixedNonRecursive)
    }

    @Test
    fun atomicCommitOrderAndRollbackLimitationAreExact() {
        val atomicity = decision().atomicityRollback

        assertEquals(
            EncryptedVaultV1RollbackPolicy.LocalEvidenceWithoutCompleteWholeSnapshotRollbackPrevention,
            atomicity.policy,
        )
        assertEquals(
            listOf(
                EncryptedVaultV1AtomicCommitStep.WriteRecordTemporary,
                EncryptedVaultV1AtomicCommitStep.FlushRecordContents,
                EncryptedVaultV1AtomicCommitStep.AtomicallyInstallRecord,
                EncryptedVaultV1AtomicCommitStep.WriteManifestTemporary,
                EncryptedVaultV1AtomicCommitStep.FlushManifestContents,
                EncryptedVaultV1AtomicCommitStep.AtomicallyReplaceAuthoritativeManifest,
                EncryptedVaultV1AtomicCommitStep.FlushContainingDirectoryWhereSupported,
                EncryptedVaultV1AtomicCommitStep.QuarantineOrCollectUnreferencedArtifactsLater,
            ),
            atomicity.orderedCommitSteps,
        )
        assertTrue(atomicity.recordsInstalledBeforeManifestCommit)
        assertTrue(atomicity.manifestIsFinalCommitPoint)
        assertTrue(atomicity.temporaryArtifactsRequired)
        assertTrue(atomicity.quarantineSupportedByFutureDesign)
        assertFalse(atomicity.destructiveRepairDefaultAllowed)
        assertTrue(atomicity.manifestGenerationCounterRequired)
        assertTrue(atomicity.previousManifestCommitmentRequired)
        assertTrue(atomicity.localInconsistentStateDetectionRequired)
        assertFalse(atomicity.wholeSnapshotRollbackPreventionGuaranteed)
        assertFalse(atomicity.trustedExternalMonotonicAnchorPresent)
        assertFalse(atomicity.appClaimsCompleteRollbackPrevention)
        assertAllFalse(
            "atomicReplaceImplementationPresent" to atomicity.atomicReplaceImplementationPresent,
            "directoryFlushImplementationPresent" to atomicity.directoryFlushImplementationPresent,
            "quarantineImplementationPresent" to atomicity.quarantineImplementationPresent,
            "rollbackDetectionImplementationPresent" to atomicity.rollbackDetectionImplementationPresent,
        )
    }

    @Test
    fun sensitiveOwnershipAndFixtureConfinementPoliciesAreSelectedButNotImplemented() {
        val ownership = decision().ownershipFixture

        assertEquals(
            EncryptedVaultV1ArrayOwnershipPolicy.OwnedCopyOpaqueRedactedNoSensitiveDataClass,
            ownership.arrayOwnershipPolicy,
        )
        assertEquals(
            "OWNED_COPY_OPAQUE_REDACTED_NO_SENSITIVE_DATA_CLASS",
            ownership.arrayOwnershipPolicy.label,
        )
        assertTrue(ownership.sensitiveByteTypesMustBeOrdinaryClasses)
        assertFalse(ownership.sensitiveByteTypesMayBeDataClasses)
        assertTrue(ownership.constructorInputCopied)
        assertFalse(ownership.mutableArrayGetterAllowed)
        assertFalse(ownership.generatedSensitiveArrayCopyAllowed)
        assertFalse(ownership.defaultSensitiveArrayEqualityAllowed)
        assertTrue(ownership.explicitRedactedToStringRequired)
        assertTrue(ownership.opaqueKeyHandlesRequired)
        assertFalse(ownership.rawKeyGetterAllowed)
        assertTrue(ownership.bestEffortClearOnCloseOrLockRequired)
        assertFalse(ownership.globalSecretCachingAllowed)
        assertFalse(ownership.secretMemoizationAllowed)
        assertFalse(ownership.parserResultsRetainPassphraseInput)
        assertFalse(ownership.codecResultsRetainRawKeyMaterial)
        assertEquals(EncryptedVaultV1FixturePolicy.TestSourceOnlyFixedFixtures, ownership.fixturePolicy)
        assertEquals("TEST_SOURCE_ONLY_FIXED_FIXTURES", ownership.fixturePolicy.label)
        assertAllFalse(
            "fixedKatKeysAllowedInProductionSource" to ownership.fixedKatKeysAllowedInProductionSource,
            "fixedKatPlaintextAllowedInProductionSource" to ownership.fixedKatPlaintextAllowedInProductionSource,
            "fixedKatCiphertextAllowedInProductionSource" to ownership.fixedKatCiphertextAllowedInProductionSource,
            "fixedSaltsAllowedInProductionSource" to ownership.fixedSaltsAllowedInProductionSource,
            "fixedVaultIdsAllowedInProductionSource" to ownership.fixedVaultIdsAllowedInProductionSource,
            "fixedRecordIdsAllowedInProductionSource" to ownership.fixedRecordIdsAllowedInProductionSource,
            "fixedPrototypeHeadersAllowedInProductionSource" to
                ownership.fixedPrototypeHeadersAllowedInProductionSource,
            "fixedPrototypeManifestsAllowedInProductionSource" to
                ownership.fixedPrototypeManifestsAllowedInProductionSource,
            "fixedRecordReferencesAllowedInProductionSource" to
                ownership.fixedRecordReferencesAllowedInProductionSource,
            "syntheticParserMarkersAllowedInProductionSource" to
                ownership.syntheticParserMarkersAllowedInProductionSource,
            "fixtureRelocationPresent" to ownership.fixtureRelocationPresent,
            "fixtureRemovalPresent" to ownership.fixtureRemovalPresent,
        )
        assertTrue(ownership.safeProtocolConstantsAllowedInProductionSource)
        assertTrue(ownership.futureFixtureRelocationRequiresSeparatePass)
    }

    @Test
    fun everyImplementationGateRemainsClosedAndEveryFuturePassGateRemainsOpen() {
        val gates = decision().implementationGates

        assertAllTrue(
            "futurePrototypeQuarantineRequiresSeparatePass" to
                gates.futurePrototypeQuarantineRequiresSeparatePass,
            "futurePrototypeDeprecationRequiresSeparatePass" to
                gates.futurePrototypeDeprecationRequiresSeparatePass,
            "futurePrototypeFixtureRelocationRequiresSeparatePass" to
                gates.futurePrototypeFixtureRelocationRequiresSeparatePass,
            "futureSyntheticParserRelocationOrRemovalRequiresSeparatePass" to
                gates.futureSyntheticParserRelocationOrRemovalRequiresSeparatePass,
            "futureCanonicalBinaryLayoutRequiresSeparatePass" to
                gates.futureCanonicalBinaryLayoutRequiresSeparatePass,
            "futureCanonicalMagicAndFieldIdsRequireSeparatePass" to
                gates.futureCanonicalMagicAndFieldIdsRequireSeparatePass,
            "futureHeaderAadEncodingRequiresSeparatePass" to
                gates.futureHeaderAadEncodingRequiresSeparatePass,
            "futurePassphraseNormalizationDecisionRequiresSeparatePass" to
                gates.futurePassphraseNormalizationDecisionRequiresSeparatePass,
            "futureRecordEnvelopeOverheadLimitRequiresSeparatePass" to
                gates.futureRecordEnvelopeOverheadLimitRequiresSeparatePass,
            "futureProviderApiRevisionRequiresSeparatePass" to
                gates.futureProviderApiRevisionRequiresSeparatePass,
            "futureProductionProviderImplementationRequiresSeparatePass" to
                gates.futureProductionProviderImplementationRequiresSeparatePass,
            "futureProviderSelectionEnablementRequiresSeparatePass" to
                gates.futureProviderSelectionEnablementRequiresSeparatePass,
            "futureCanonicalCodecImplementationRequiresSeparatePass" to
                gates.futureCanonicalCodecImplementationRequiresSeparatePass,
            "futureCanonicalVectorExecutionRequiresSeparatePass" to
                gates.futureCanonicalVectorExecutionRequiresSeparatePass,
            "futureStoragePathImplementationRequiresSeparatePass" to
                gates.futureStoragePathImplementationRequiresSeparatePass,
            "futureAtomicStorageImplementationRequiresSeparatePass" to
                gates.futureAtomicStorageImplementationRequiresSeparatePass,
            "futureLockSessionImplementationRequiresSeparatePass" to
                gates.futureLockSessionImplementationRequiresSeparatePass,
            "futureBackupExportFormatRequiresSeparatePass" to
                gates.futureBackupExportFormatRequiresSeparatePass,
            "futurePlatformWrappingRequiresSeparatePass" to
                gates.futurePlatformWrappingRequiresSeparatePass,
            "futureSecureStorageSuccessRequiresSeparatePass" to
                gates.futureSecureStorageSuccessRequiresSeparatePass,
            "futureSecureMetadataSuccessRequiresSeparatePass" to
                gates.futureSecureMetadataSuccessRequiresSeparatePass,
            "futureProductionPersistenceRequiresSeparatePass" to
                gates.futureProductionPersistenceRequiresSeparatePass,
            "futureProductionSyncRequiresSeparatePass" to
                gates.futureProductionSyncRequiresSeparatePass,
            "futureSigningBroadcastingRequiresSeparatePass" to
                gates.futureSigningBroadcastingRequiresSeparatePass,
            "futureUiActionEnablementRequiresSeparatePass" to
                gates.futureUiActionEnablementRequiresSeparatePass,
            "futureEndpointRequiresSeparatePass" to gates.futureEndpointRequiresSeparatePass,
            "futureMainnetRequiresExplicitReleaseHardening" to
                gates.futureMainnetRequiresExplicitReleaseHardening,
        )
        assertEquals(27, gates.implementationBlockerCount)
        assertTrue(gates.allFutureSeparatePassRequirementsPresent)

        assertAllFalse(
            "canonicalBinaryLayoutImplemented" to gates.canonicalBinaryLayoutImplemented,
            "canonicalHeaderImplemented" to gates.canonicalHeaderImplemented,
            "canonicalKeyEnvelopeImplemented" to gates.canonicalKeyEnvelopeImplemented,
            "canonicalManifestImplemented" to gates.canonicalManifestImplemented,
            "canonicalRecordEnvelopeImplemented" to gates.canonicalRecordEnvelopeImplemented,
            "canonicalParserImplemented" to gates.canonicalParserImplemented,
            "canonicalWriterImplemented" to gates.canonicalWriterImplemented,
            "canonicalCodecImplemented" to gates.canonicalCodecImplemented,
            "canonicalVectorExecutionPresent" to gates.canonicalVectorExecutionPresent,
            "vaultDirectoryCreationPresent" to gates.vaultDirectoryCreationPresent,
            "vaultFileReadPresent" to gates.vaultFileReadPresent,
            "vaultFileWritePresent" to gates.vaultFileWritePresent,
            "vaultFileDeletePresent" to gates.vaultFileDeletePresent,
            "atomicStoragePresent" to gates.atomicStoragePresent,
            "keyGenerationPresent" to gates.keyGenerationPresent,
            "nonceGenerationPresent" to gates.nonceGenerationPresent,
            "kdfExecutionPresent" to gates.kdfExecutionPresent,
            "hkdfExecutionPresent" to gates.hkdfExecutionPresent,
            "aeadExecutionPresent" to gates.aeadExecutionPresent,
            "encryptionExecutionPresent" to gates.encryptionExecutionPresent,
            "decryptionExecutionPresent" to gates.decryptionExecutionPresent,
            "authenticationExecutionPresent" to gates.authenticationExecutionPresent,
            "providerImplementationPresent" to gates.providerImplementationPresent,
            "productionProviderSelectionEnabled" to gates.productionProviderSelectionEnabled,
            "productionProviderSelectable" to gates.productionProviderSelectable,
            "lockSessionImplementationPresent" to gates.lockSessionImplementationPresent,
            "unlockImplementationPresent" to gates.unlockImplementationPresent,
            "runtimeVaultRootKeyPresent" to gates.runtimeVaultRootKeyPresent,
            "runtimeSessionKeyPresent" to gates.runtimeSessionKeyPresent,
            "secureSecretStorageSuccessPathPresent" to gates.secureSecretStorageSuccessPathPresent,
            "secureMetadataStorageSuccessPathPresent" to gates.secureMetadataStorageSuccessPathPresent,
            "productionObservationPersistencePresent" to gates.productionObservationPersistencePresent,
            "productionAddressIndexPersistencePresent" to gates.productionAddressIndexPersistencePresent,
            "productionUtxoPersistencePresent" to gates.productionUtxoPersistencePresent,
            "productionWalletHistoryPersistencePresent" to gates.productionWalletHistoryPersistencePresent,
            "productionSyncPresent" to gates.productionSyncPresent,
            "productionBackendClientPresent" to gates.productionBackendClientPresent,
            "signingBroadcastingPresent" to gates.signingBroadcastingPresent,
            "uiActionEnablementPresent" to gates.uiActionEnablementPresent,
            "endpointPresent" to gates.endpointPresent,
            "mainnetPresent" to gates.mainnetPresent,
        )
        assertTrue(gates.allCurrentImplementationStateAbsent)
    }

    @Test
    fun decisionAndSafeLabelsUseDeterministicRedactedDisplay() {
        val decision = decision()
        val display = decision.toString()

        assertEquals(
            "EncryptedVaultV1CanonicalArchitectureDecision(" +
                "REDACTED, COMMON_MAIN_POLICY, ARCHITECTURE_DECISION_ONLY, " +
                "IMPLEMENTATION_BLOCKED, NO_PRODUCTION_AUTHORIZATION" +
                ")",
            display,
        )
        assertEquals(
            "EncryptedVaultV1CanonicalArchitectureSafeLabel(REDACTED)",
            decision.decisionId.toString(),
        )
        decision.topology.artifactRoles.forEach { role ->
            assertEquals(
                "EncryptedVaultV1CanonicalArchitectureSafeLabel(REDACTED)",
                role.safeLabel.toString(),
            )
        }
        assertFalse(display.contains('@'))
        assertFalse(Regex("""\b[0-9a-fA-F]{64,}\b""").containsMatchIn(display))
        assertFalse(Regex("""(?:^|\s)(?:/|[A-Za-z]:\\)\S+""").containsMatchIn(display))
        assertFalse(display.contains("payload", ignoreCase = true))
        assertFalse(display.contains("credential", ignoreCase = true))
        assertFalse(display.contains("vector", ignoreCase = true))
    }

    private fun assertAllTrue(vararg values: Pair<String, Boolean>) {
        values.forEach { (name, value) -> assertTrue(value, "$name must be true") }
    }

    private fun assertAllFalse(vararg values: Pair<String, Boolean>) {
        values.forEach { (name, value) -> assertFalse(value, "$name must be false") }
    }
}
