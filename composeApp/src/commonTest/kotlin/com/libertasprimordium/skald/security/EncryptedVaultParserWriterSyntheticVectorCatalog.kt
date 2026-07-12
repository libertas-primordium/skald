package com.libertasprimordium.skald.security

enum class EncryptedVaultParserWriterSyntheticVectorCatalogKind(val label: String) {
    EncryptedLocalVaultParserWriterSyntheticVectorCatalog(
        "ENCRYPTED_LOCAL_VAULT_PARSER_WRITER_SYNTHETIC_VECTOR_CATALOG",
    ),
}

enum class EncryptedVaultParserWriterSyntheticVectorCatalogSourceSet(val label: String) {
    TestSourceOnly("TEST_SOURCE_ONLY"),
}

enum class EncryptedVaultParserWriterSyntheticVectorCatalogClass(val safeLabel: String) {
    MinimalHeaderOnlySyntheticVector("minimal-header-only-synthetic-vector"),
    HeaderAndKdfSectionSyntheticVector("header-and-kdf-section-synthetic-vector"),
    HeaderKeyEnvelopeDirectorySyntheticVector("header-key-envelope-directory-synthetic-vector"),
    SingleRecordEnvelopeSyntheticVector("single-record-envelope-synthetic-vector"),
    MultiRecordDirectorySyntheticVector("multi-record-directory-synthetic-vector"),
    UnsupportedVersionSyntheticVector("unsupported-version-synthetic-vector"),
    UnknownCriticalFeatureSyntheticVector("unknown-critical-feature-synthetic-vector"),
    TruncatedHeaderSyntheticVector("truncated-header-synthetic-vector"),
    TruncatedRecordEnvelopeSyntheticVector("truncated-record-envelope-synthetic-vector"),
    RedactedDiagnosticsSyntheticVector("redacted-diagnostics-synthetic-vector"),
    MigrationRequiredSyntheticVector("migration-required-synthetic-vector"),
    CorruptionSuspectedSyntheticVector("corruption-suspected-synthetic-vector"),
}

class EncryptedVaultParserWriterSyntheticVector private constructor(
    val vectorClass: EncryptedVaultParserWriterSyntheticVectorCatalogClass,
    private val testOnlyMarker: String,
) {
    val byteCount: Int = testOnlyMarker.encodeToByteArray().size
    val sourceSet: EncryptedVaultParserWriterSyntheticVectorCatalogSourceSet =
        EncryptedVaultParserWriterSyntheticVectorCatalogSourceSet.TestSourceOnly
    val syntheticTestOnly: Boolean = true
    val shortSyntheticBytes: Boolean = true
    val nonWallet: Boolean = true
    val nonSecret: Boolean = true
    val nonNetwork: Boolean = true
    val validBitcoinData: Boolean = false
    val realVaultData: Boolean = false
    val productionSerializedVaultBytes: Boolean = false
    val logged: Boolean = false
    val displayed: Boolean = false
    val persisted: Boolean = false
    val usedByWorkingParserOrWriter: Boolean = false

    fun bytesForDisabledScaffoldRequestOnly(): ByteArray =
        testOnlyMarker.encodeToByteArray()

    fun markerForTestSourceConfinementAssertionOnly(): String =
        testOnlyMarker

    fun parserRequestForDisabledScaffold(): EncryptedVaultParserRequest =
        EncryptedVaultParserRequest(
            declaredInputByteCount = byteCount,
            testSourceSyntheticVectorBytesPresent = true,
        )

    fun writerRequestForDisabledScaffold(): EncryptedVaultWriterRequest =
        EncryptedVaultWriterRequest(
            requestedOutputByteCount = byteCount,
            testSourceSyntheticVectorBytesPresent = true,
        )

    override fun toString(): String =
        "EncryptedVaultParserWriterSyntheticVector(" +
            "REDACTED, TEST_SOURCE_ONLY, ${vectorClass.name}, BYTE_COUNT=$byteCount, " +
            "NO_PRODUCTION_BYTES, NOT_EXECUTED)"

    companion object {
        fun testOnly(
            vectorClass: EncryptedVaultParserWriterSyntheticVectorCatalogClass,
            testOnlyMarker: String,
        ): EncryptedVaultParserWriterSyntheticVector =
            EncryptedVaultParserWriterSyntheticVector(vectorClass, testOnlyMarker)
    }
}

data class EncryptedVaultParserWriterSyntheticVectorCatalogReport(
    val vectorCatalogId: String,
    val vectorCatalogVersion: Int,
    val vectorCatalogKind: EncryptedVaultParserWriterSyntheticVectorCatalogKind,
    val sourceSet: EncryptedVaultParserWriterSyntheticVectorCatalogSourceSet,
    val syntheticVectorCatalogPresent: Boolean,
    val syntheticVectorBytesPresent: Boolean,
    val testSourceSyntheticVectorBytesPresent: Boolean,
    val productionVectorBytesPresent: Boolean,
    val productionParserInputBytesPresent: Boolean,
    val productionWriterOutputBytesPresent: Boolean,
    val parserVectorExecutionPresent: Boolean,
    val writerVectorExecutionPresent: Boolean,
    val roundTripVectorExecutionPresent: Boolean,
    val negativeVectorExecutionPresent: Boolean,
    val workingParserImplementationPresent: Boolean,
    val workingWriterImplementationPresent: Boolean,
    val disabledParserRejectedSyntheticBytes: Boolean,
    val disabledWriterRejectedWithoutProducingBytes: Boolean,
    val syntheticBytesLogged: Boolean,
    val syntheticBytesDisplayed: Boolean,
    val syntheticBytesPersisted: Boolean,
    val syntheticBytesInDocs: Boolean,
    val syntheticBytesInBuildHistory: Boolean,
    val futureParserImplementationRequiresSeparatePass: Boolean,
    val futureWriterImplementationRequiresSeparatePass: Boolean,
    val futureParserWriterVectorExecutionRequiresSeparatePass: Boolean,
    val futureVaultStorageRepositoryRequiresSeparatePass: Boolean,
    val futureSecureStorageSuccessRequiresSeparatePass: Boolean,
    val futureSecureMetadataSuccessRequiresSeparatePass: Boolean,
    val futureProductionSyncRequiresSeparatePass: Boolean,
    val futureProductionProviderSelectionRequiresSeparatePass: Boolean,
    val vaultContainerSerializationPresent: Boolean,
    val vaultContainerParsingPresent: Boolean,
    val vaultContainerBytesProduced: Boolean,
    val vaultContainerBytesConsumed: Boolean,
    val vaultHeaderSerialized: Boolean,
    val vaultHeaderParsed: Boolean,
    val vaultRecordDirectorySerialized: Boolean,
    val vaultRecordDirectoryParsed: Boolean,
    val vaultRecordEnvelopeSerialized: Boolean,
    val vaultRecordEnvelopeParsed: Boolean,
    val vaultFileReadPresent: Boolean,
    val vaultFileWritePresent: Boolean,
    val vaultFileDeletePresent: Boolean,
    val vaultDirectoryCreated: Boolean,
    val atomicReplaceImplementationPresent: Boolean,
    val partialWriteDetectionImplementationPresent: Boolean,
    val migrationImplementationPresent: Boolean,
    val migrationExecutionPresent: Boolean,
    val corruptionDetectionImplementationPresent: Boolean,
    val corruptionRepairImplementationPresent: Boolean,
    val backupCreationPresent: Boolean,
    val rollbackImplementationPresent: Boolean,
    val kdfExecutionPresent: Boolean,
    val aeadExecutionPresent: Boolean,
    val encryptionExecutionPresent: Boolean,
    val decryptionExecutionPresent: Boolean,
    val keyGenerationPresent: Boolean,
    val nonceGenerationPresent: Boolean,
    val tinkKeysetCreationPresent: Boolean,
    val tinkKeysetPersistencePresent: Boolean,
    val vaultStoragePathImplementationPresent: Boolean,
    val encryptedVaultFileFormatImplemented: Boolean,
    val encryptedVaultRepositorySuccessPresent: Boolean,
    val lockSessionImplementationPresent: Boolean,
    val unlockImplementationPresent: Boolean,
    val runtimeSessionKeyPresent: Boolean,
    val sessionKeyCached: Boolean,
    val plaintextCachePresent: Boolean,
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
    val productionVectorCreationAuthorizationPresent: Boolean,
    val parserVectorExecutionAuthorizationPresent: Boolean,
    val writerVectorExecutionAuthorizationPresent: Boolean,
    val parserImplementationAuthorizationPresent: Boolean,
    val writerImplementationAuthorizationPresent: Boolean,
    val vaultContainerSerializationAuthorizationPresent: Boolean,
    val vaultContainerParsingAuthorizationPresent: Boolean,
    val vaultFileReadAuthorizationPresent: Boolean,
    val vaultFileWriteAuthorizationPresent: Boolean,
    val vaultFileDeleteAuthorizationPresent: Boolean,
    val vaultDirectoryCreationAuthorizationPresent: Boolean,
    val atomicReplaceAuthorizationPresent: Boolean,
    val partialWriteDetectionAuthorizationPresent: Boolean,
    val migrationImplementationAuthorizationPresent: Boolean,
    val corruptionDetectionAuthorizationPresent: Boolean,
    val corruptionRepairAuthorizationPresent: Boolean,
    val backupCreationAuthorizationPresent: Boolean,
    val rollbackAuthorizationPresent: Boolean,
    val kdfExecutionAuthorizationPresent: Boolean,
    val aeadExecutionAuthorizationPresent: Boolean,
    val encryptionAuthorizationPresent: Boolean,
    val decryptionAuthorizationPresent: Boolean,
    val keyGenerationAuthorizationPresent: Boolean,
    val nonceGenerationAuthorizationPresent: Boolean,
    val tinkKeysetCreationAuthorizationPresent: Boolean,
    val tinkKeysetPersistenceAuthorizationPresent: Boolean,
    val vaultSessionImplementationAuthorizationPresent: Boolean,
    val vaultUnlockAuthorizationPresent: Boolean,
    val vaultLockAuthorizationPresent: Boolean,
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
    val vectorClassCount: Int,
    val syntheticByteFixtureCount: Int,
    val blockerCount: Int,
    val warningCount: Int,
    val vectorClasses: List<EncryptedVaultParserWriterSyntheticVectorCatalogClass>,
) {
    override fun toString(): String =
        "EncryptedVaultParserWriterSyntheticVectorCatalogReport(" +
            "REDACTED, TEST_SOURCE_ONLY, SYNTHETIC_BYTES_REDACTED, " +
            "DISABLED_REJECTION_ONLY, NO_PRODUCTION_BYTES, NO_PARSER_WRITER, " +
            "NO_SERIALIZATION_PARSING, NO_IO, NO_CRYPTO_EXECUTION, " +
            "DISABLED_PROVIDER_ONLY, NO_MAINNET)"
}

object EncryptedVaultParserWriterSyntheticVectorCatalog {
    val fixtures: List<EncryptedVaultParserWriterSyntheticVector> =
        listOf(
            EncryptedVaultParserWriterSyntheticVector.testOnly(
                EncryptedVaultParserWriterSyntheticVectorCatalogClass.MinimalHeaderOnlySyntheticVector,
                "skv-min-a",
            ),
            EncryptedVaultParserWriterSyntheticVector.testOnly(
                EncryptedVaultParserWriterSyntheticVectorCatalogClass.HeaderAndKdfSectionSyntheticVector,
                "skv-kdf-b",
            ),
            EncryptedVaultParserWriterSyntheticVector.testOnly(
                EncryptedVaultParserWriterSyntheticVectorCatalogClass.HeaderKeyEnvelopeDirectorySyntheticVector,
                "skv-env-c",
            ),
            EncryptedVaultParserWriterSyntheticVector.testOnly(
                EncryptedVaultParserWriterSyntheticVectorCatalogClass.SingleRecordEnvelopeSyntheticVector,
                "skv-one-d",
            ),
            EncryptedVaultParserWriterSyntheticVector.testOnly(
                EncryptedVaultParserWriterSyntheticVectorCatalogClass.MultiRecordDirectorySyntheticVector,
                "skv-many-e",
            ),
            EncryptedVaultParserWriterSyntheticVector.testOnly(
                EncryptedVaultParserWriterSyntheticVectorCatalogClass.UnsupportedVersionSyntheticVector,
                "skv-ver-f",
            ),
            EncryptedVaultParserWriterSyntheticVector.testOnly(
                EncryptedVaultParserWriterSyntheticVectorCatalogClass.UnknownCriticalFeatureSyntheticVector,
                "skv-crit-g",
            ),
            EncryptedVaultParserWriterSyntheticVector.testOnly(
                EncryptedVaultParserWriterSyntheticVectorCatalogClass.TruncatedHeaderSyntheticVector,
                "skv-cut-h",
            ),
            EncryptedVaultParserWriterSyntheticVector.testOnly(
                EncryptedVaultParserWriterSyntheticVectorCatalogClass.TruncatedRecordEnvelopeSyntheticVector,
                "skv-cut-i",
            ),
            EncryptedVaultParserWriterSyntheticVector.testOnly(
                EncryptedVaultParserWriterSyntheticVectorCatalogClass.RedactedDiagnosticsSyntheticVector,
                "skv-red-j",
            ),
            EncryptedVaultParserWriterSyntheticVector.testOnly(
                EncryptedVaultParserWriterSyntheticVectorCatalogClass.MigrationRequiredSyntheticVector,
                "skv-mig-k",
            ),
            EncryptedVaultParserWriterSyntheticVector.testOnly(
                EncryptedVaultParserWriterSyntheticVectorCatalogClass.CorruptionSuspectedSyntheticVector,
                "skv-cor-l",
            ),
        )

    fun currentCatalogReport(): EncryptedVaultParserWriterSyntheticVectorCatalogReport {
        val scaffold = EncryptedVaultParserWriterScaffoldPolicy.currentParserWriterScaffold()
        val providerSelection = VaultCryptoProviderSelectionRegistry.select()
        val disabledParserRejectedSyntheticBytes = fixtures.all { fixture ->
            val result = DisabledEncryptedVaultParserScaffold.parse(
                fixture.parserRequestForDisabledScaffold(),
            )
            result.status == EncryptedVaultParserWriterScaffoldStatus.RejectedByScaffold &&
                result.accepted.not() &&
                result.consumedByteCount == 0 &&
                result.producedByteCount == 0 &&
                result.diagnostics.safeLabelsOnly &&
                result.diagnostics.payloadFree
        }
        val disabledWriterRejectedWithoutProducingBytes = fixtures.all { fixture ->
            val result = DisabledEncryptedVaultWriterScaffold.write(
                fixture.writerRequestForDisabledScaffold(),
            )
            result.status == EncryptedVaultParserWriterScaffoldStatus.RejectedByScaffold &&
                result.accepted.not() &&
                result.consumedByteCount == 0 &&
                result.producedByteCount == 0 &&
                result.diagnostics.safeLabelsOnly &&
                result.diagnostics.payloadFree
        }

        return EncryptedVaultParserWriterSyntheticVectorCatalogReport(
            vectorCatalogId = "skald-encrypted-local-vault-parser-writer-synthetic-vector-catalog-v1",
            vectorCatalogVersion = 1,
            vectorCatalogKind =
                EncryptedVaultParserWriterSyntheticVectorCatalogKind
                    .EncryptedLocalVaultParserWriterSyntheticVectorCatalog,
            sourceSet = EncryptedVaultParserWriterSyntheticVectorCatalogSourceSet.TestSourceOnly,
            syntheticVectorCatalogPresent = true,
            syntheticVectorBytesPresent = true,
            testSourceSyntheticVectorBytesPresent = true,
            productionVectorBytesPresent = false,
            productionParserInputBytesPresent = false,
            productionWriterOutputBytesPresent = false,
            parserVectorExecutionPresent = false,
            writerVectorExecutionPresent = false,
            roundTripVectorExecutionPresent = false,
            negativeVectorExecutionPresent = false,
            workingParserImplementationPresent = scaffold.workingParserImplementationPresent,
            workingWriterImplementationPresent = scaffold.workingWriterImplementationPresent,
            disabledParserRejectedSyntheticBytes = disabledParserRejectedSyntheticBytes,
            disabledWriterRejectedWithoutProducingBytes = disabledWriterRejectedWithoutProducingBytes,
            syntheticBytesLogged = false,
            syntheticBytesDisplayed = false,
            syntheticBytesPersisted = false,
            syntheticBytesInDocs = false,
            syntheticBytesInBuildHistory = false,
            futureParserImplementationRequiresSeparatePass =
                scaffold.futureParserImplementationRequiresSeparatePass,
            futureWriterImplementationRequiresSeparatePass =
                scaffold.futureWriterImplementationRequiresSeparatePass,
            futureParserWriterVectorExecutionRequiresSeparatePass =
                scaffold.futureParserWriterVectorExecutionRequiresSeparatePass,
            futureVaultStorageRepositoryRequiresSeparatePass =
                scaffold.futureVaultStorageRepositoryRequiresSeparatePass,
            futureSecureStorageSuccessRequiresSeparatePass =
                scaffold.futureSecureStorageSuccessRequiresSeparatePass,
            futureSecureMetadataSuccessRequiresSeparatePass =
                scaffold.futureSecureMetadataSuccessRequiresSeparatePass,
            futureProductionSyncRequiresSeparatePass =
                scaffold.futureProductionSyncRequiresSeparatePass,
            futureProductionProviderSelectionRequiresSeparatePass =
                scaffold.futureProductionProviderSelectionRequiresSeparatePass,
            vaultContainerSerializationPresent = false,
            vaultContainerParsingPresent = false,
            vaultContainerBytesProduced = false,
            vaultContainerBytesConsumed = false,
            vaultHeaderSerialized = false,
            vaultHeaderParsed = false,
            vaultRecordDirectorySerialized = false,
            vaultRecordDirectoryParsed = false,
            vaultRecordEnvelopeSerialized = false,
            vaultRecordEnvelopeParsed = false,
            vaultFileReadPresent = false,
            vaultFileWritePresent = false,
            vaultFileDeletePresent = false,
            vaultDirectoryCreated = false,
            atomicReplaceImplementationPresent = false,
            partialWriteDetectionImplementationPresent = false,
            migrationImplementationPresent = false,
            migrationExecutionPresent = false,
            corruptionDetectionImplementationPresent = false,
            corruptionRepairImplementationPresent = false,
            backupCreationPresent = false,
            rollbackImplementationPresent = false,
            kdfExecutionPresent = false,
            aeadExecutionPresent = false,
            encryptionExecutionPresent = false,
            decryptionExecutionPresent = false,
            keyGenerationPresent = false,
            nonceGenerationPresent = false,
            tinkKeysetCreationPresent = false,
            tinkKeysetPersistencePresent = false,
            vaultStoragePathImplementationPresent = false,
            encryptedVaultFileFormatImplemented = false,
            encryptedVaultRepositorySuccessPresent = false,
            lockSessionImplementationPresent = false,
            unlockImplementationPresent = false,
            runtimeSessionKeyPresent = false,
            sessionKeyCached = false,
            plaintextCachePresent = false,
            secureSecretStorageSuccessPathPresent =
                scaffold.secureSecretStorageSuccessPathPresent,
            secureMetadataStorageSuccessPathPresent =
                scaffold.secureMetadataStorageSuccessPathPresent,
            productionObservationPersistencePresent = false,
            productionAddressIndexPersistencePresent = false,
            productionUtxoPersistencePresent = false,
            productionWalletHistoryPersistencePresent = false,
            productionSyncPresent = false,
            productionBackendClientPresent = false,
            productionProviderSelectionEnabled = false,
            productionProviderSelectable = false,
            productionSelectionStillDisabledProviderOnly =
                providerSelection.selectedProviderIsDisabled &&
                    providerSelection.productionProviderSelectable.not(),
            signingBroadcastingPresent = false,
            uiActionEnablementPresent = false,
            endpointPresent = false,
            mainnetPresent = false,
            productionVectorCreationAuthorizationPresent = false,
            parserVectorExecutionAuthorizationPresent = false,
            writerVectorExecutionAuthorizationPresent = false,
            parserImplementationAuthorizationPresent = false,
            writerImplementationAuthorizationPresent = false,
            vaultContainerSerializationAuthorizationPresent = false,
            vaultContainerParsingAuthorizationPresent = false,
            vaultFileReadAuthorizationPresent = false,
            vaultFileWriteAuthorizationPresent = false,
            vaultFileDeleteAuthorizationPresent = false,
            vaultDirectoryCreationAuthorizationPresent = false,
            atomicReplaceAuthorizationPresent = false,
            partialWriteDetectionAuthorizationPresent = false,
            migrationImplementationAuthorizationPresent = false,
            corruptionDetectionAuthorizationPresent = false,
            corruptionRepairAuthorizationPresent = false,
            backupCreationAuthorizationPresent = false,
            rollbackAuthorizationPresent = false,
            kdfExecutionAuthorizationPresent = false,
            aeadExecutionAuthorizationPresent = false,
            encryptionAuthorizationPresent = false,
            decryptionAuthorizationPresent = false,
            keyGenerationAuthorizationPresent = false,
            nonceGenerationAuthorizationPresent = false,
            tinkKeysetCreationAuthorizationPresent = false,
            tinkKeysetPersistenceAuthorizationPresent = false,
            vaultSessionImplementationAuthorizationPresent = false,
            vaultUnlockAuthorizationPresent = false,
            vaultLockAuthorizationPresent = false,
            productionStorageAuthorizationPresent = false,
            productionSecretStorageAuthorizationPresent = false,
            productionMetadataStorageAuthorizationPresent = false,
            productionSyncAuthorizationPresent = false,
            productionProviderSelectionAuthorizationPresent = false,
            productionProviderImplementationAuthorizationPresent = false,
            signingBroadcastingAuthorizationPresent = false,
            uiAuthorizationPresent = false,
            endpointAuthorizationPresent = false,
            mainnetAuthorizationPresent = false,
            vectorClassCount = EncryptedVaultParserWriterSyntheticVectorCatalogClass.entries.size,
            syntheticByteFixtureCount = fixtures.size,
            blockerCount = 0,
            warningCount = 0,
            vectorClasses = fixtures.map { it.vectorClass },
        )
    }
}
