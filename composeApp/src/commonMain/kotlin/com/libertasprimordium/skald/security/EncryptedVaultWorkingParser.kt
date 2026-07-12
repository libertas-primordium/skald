package com.libertasprimordium.skald.security

@JvmInline
value class EncryptedVaultWorkingParserSafeLabel(val value: String) {
    override fun toString(): String = "EncryptedVaultWorkingParserSafeLabel(REDACTED)"
}

enum class EncryptedVaultWorkingParserKind(val label: String) {
    EncryptedLocalVaultWorkingParserSyntheticVectorExecution(
        "ENCRYPTED_LOCAL_VAULT_WORKING_PARSER_SYNTHETIC_VECTOR_EXECUTION",
    ),
}

enum class EncryptedVaultWorkingParserSourceSet(val label: String) {
    CommonMainInMemoryParser("COMMON_MAIN_IN_MEMORY_PARSER"),
    TestSourceSyntheticVector("TEST_SOURCE_SYNTHETIC_VECTOR"),
}

enum class EncryptedVaultWorkingParserPolicyLabel(
    val safeLabel: EncryptedVaultWorkingParserSafeLabel,
) {
    WorkingParser(
        EncryptedVaultWorkingParserSafeLabel(
            "skald-encrypted-local-vault-working-parser-v1",
        ),
    ),
    InMemoryParser(
        EncryptedVaultWorkingParserSafeLabel("skald-vault-v1-in-memory-parser"),
    ),
    SyntheticVectorParser(
        EncryptedVaultWorkingParserSafeLabel("skald-vault-v1-synthetic-vector-parser"),
    ),
    FailClosedResult(
        EncryptedVaultWorkingParserSafeLabel("skald-vault-v1-parser-fail-closed-result"),
    ),
    RedactedDiagnostics(
        EncryptedVaultWorkingParserSafeLabel("skald-vault-v1-parser-redacted-diagnostics"),
    ),
    ErrorTaxonomy(
        EncryptedVaultWorkingParserSafeLabel("skald-vault-v1-parser-error-taxonomy"),
    ),
}

enum class EncryptedVaultWorkingParserStatus(val label: String) {
    ParsedSyntheticVector("parsed synthetic vector"),
    FailedClosed("failed closed"),
}

enum class EncryptedVaultWorkingParserSyntheticClassification(val safeLabel: String) {
    SyntheticHeaderOnly("synthetic-header-only"),
    SyntheticKdfSection("synthetic-kdf-section"),
    SyntheticDirectory("synthetic-directory"),
    SyntheticSingleRecordEnvelope("synthetic-single-record-envelope"),
    SyntheticMultiRecordDirectory("synthetic-multi-record-directory"),
    RedactedDiagnosticsOnly("redacted-diagnostics-only"),
    NotClassified("not-classified"),
}

enum class EncryptedVaultWorkingParserBlocker(val safeLabel: String) {
    ProductionInputNotAuthorized("production-input-not-authorized"),
    EmptyInput("empty-input"),
    UnsupportedVersion("unsupported-version"),
    UnsupportedCriticalFeature("unsupported-critical-feature"),
    TruncatedHeader("truncated-header"),
    TruncatedRecordEnvelope("truncated-record-envelope"),
    RedactedDiagnosticsOnly("redacted-diagnostics-only"),
    MigrationRequired("migration-required"),
    CorruptionSuspected("corruption-suspected"),
    MalformedSyntheticSectionOrder("malformed-synthetic-section-order"),
    UnrecognizedSyntheticVector("unrecognized-synthetic-vector"),
}

class EncryptedVaultWorkingParserRequest private constructor(
    private val material: ByteArray?,
    val sourceSet: EncryptedVaultWorkingParserSourceSet,
    val declaredInputByteCount: Int,
    val testSourceSyntheticVectorBytesPresent: Boolean,
    val productionParserInputBytesPresent: Boolean,
    val productionVaultBytesPresent: Boolean,
) {
    internal fun inspectMaterial(
        block: (ByteArray) -> EncryptedVaultWorkingParserResult,
    ): EncryptedVaultWorkingParserResult {
        val availableMaterial = material
            ?: return EncryptedVaultWorkingParser.rejectedInternalMaterialUnavailable()
        return block(availableMaterial)
    }

    override fun toString(): String =
        "EncryptedVaultWorkingParserRequest(REDACTED, TEST_SOURCE_SYNTHETIC_ONLY, NO_BYTES_DISPLAYED)"

    companion object {
        fun testSourceSyntheticVector(material: ByteArray): EncryptedVaultWorkingParserRequest =
            EncryptedVaultWorkingParserRequest(
                material = material.copyOf(),
                sourceSet = EncryptedVaultWorkingParserSourceSet.TestSourceSyntheticVector,
                declaredInputByteCount = material.size,
                testSourceSyntheticVectorBytesPresent = true,
                productionParserInputBytesPresent = false,
                productionVaultBytesPresent = false,
            )

    }
}

data class EncryptedVaultWorkingParserRedactedDiagnostics(
    val status: EncryptedVaultWorkingParserStatus,
    val classification: EncryptedVaultWorkingParserSyntheticClassification,
    val blockerCount: Int,
    val warningCount: Int,
    val safeLabelsOnly: Boolean = true,
    val payloadFree: Boolean = true,
    val rawBytesFree: Boolean = true,
    val filePathFree: Boolean = true,
    val cryptoMaterialFree: Boolean = true,
) {
    override fun toString(): String =
        "EncryptedVaultWorkingParserRedactedDiagnostics(REDACTED, SAFE_LABELS_ONLY, NO_BYTES_EXPOSED)"
}

data class EncryptedVaultWorkingParserResult(
    val status: EncryptedVaultWorkingParserStatus,
    val classification: EncryptedVaultWorkingParserSyntheticClassification,
    val accepted: Boolean,
    val sectionCount: Int,
    val consumedByteCount: Int,
    val producedByteCount: Int,
    val blockers: List<EncryptedVaultWorkingParserBlocker>,
    val blockerCount: Int,
    val warningCount: Int,
    val diagnostics: EncryptedVaultWorkingParserRedactedDiagnostics,
    val testSourceSyntheticVectorBytesOnly: Boolean,
    val inputBytesExposed: Boolean,
    val inputBytesCopiedToResult: Boolean,
    val sectionBytesExposed: Boolean,
    val parsedPayloadByteArraysCreated: Boolean,
    val repositoryObjectCreated: Boolean,
    val storageStateCreated: Boolean,
    val writerObjectCreated: Boolean,
    val fileIoUsed: Boolean,
    val cryptoAuthenticationExecuted: Boolean,
) {
    override fun toString(): String =
        "EncryptedVaultWorkingParserResult(REDACTED, SAFE_LABELS_ONLY, NO_BYTES_EXPOSED, " +
            "NO_WRITER, NO_IO, NO_CRYPTO_AUTH_EXECUTION)"
}

data class EncryptedVaultWorkingParserEvidence(
    val parserId: EncryptedVaultWorkingParserSafeLabel,
    val parserVersion: Int,
    val parserKind: EncryptedVaultWorkingParserKind,
    val sourceSet: EncryptedVaultWorkingParserSourceSet,
    val workingParserAdmissionEvidencePresent: Boolean,
    val parserWriterSyntheticVectorCatalogEvidencePresent: Boolean,
    val parserWriterImplementationScaffoldEvidencePresent: Boolean,
    val containerFormatV1DecisionEvidencePresent: Boolean,
    val migrationCorruptionPolicyEvidencePresent: Boolean,
    val workingParserImplementationPresent: Boolean,
    val commonMainInMemoryParserPresent: Boolean,
    val syntheticVectorParserExecutionSupported: Boolean,
    val parserFailClosedPolicyPresent: Boolean,
    val parserRedactedResultPolicyPresent: Boolean,
    val parserErrorTaxonomyPresent: Boolean,
    val workingWriterImplementationPresent: Boolean,
    val productionVectorBytesPresent: Boolean,
    val productionParserInputBytesPresent: Boolean,
    val productionWriterOutputBytesPresent: Boolean,
    val productionVaultBytesPresent: Boolean,
    val vaultContainerSerializationPresent: Boolean,
    val vaultFileReadPresent: Boolean,
    val vaultFileWritePresent: Boolean,
    val vaultFileDeletePresent: Boolean,
    val vaultDirectoryCreated: Boolean,
    val atomicReplaceImplementationPresent: Boolean,
    val backupCreationPresent: Boolean,
    val rollbackImplementationPresent: Boolean,
    val migrationImplementationPresent: Boolean,
    val migrationExecutionPresent: Boolean,
    val corruptionRepairImplementationPresent: Boolean,
    val kdfExecutionPresent: Boolean,
    val aeadExecutionPresent: Boolean,
    val encryptionExecutionPresent: Boolean,
    val decryptionExecutionPresent: Boolean,
    val authenticationExecutionPresent: Boolean,
    val keyGenerationPresent: Boolean,
    val nonceGenerationPresent: Boolean,
    val tinkKeysetCreationPresent: Boolean,
    val tinkKeysetPersistencePresent: Boolean,
    val vaultStoragePathImplementationPresent: Boolean,
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
    val futureWorkingWriterImplementationRequiresSeparatePass: Boolean,
    val futureParserWriterRoundTripRequiresSeparatePass: Boolean,
    val futureVaultStorageRepositoryRequiresSeparatePass: Boolean,
    val futureSecureStorageSuccessRequiresSeparatePass: Boolean,
    val futureSecureMetadataSuccessRequiresSeparatePass: Boolean,
    val futureProductionSyncRequiresSeparatePass: Boolean,
    val futureProductionProviderSelectionRequiresSeparatePass: Boolean,
    val workingWriterImplementationAuthorizationPresent: Boolean,
    val productionVectorCreationAuthorizationPresent: Boolean,
    val productionParserInputAuthorizationPresent: Boolean,
    val productionWriterOutputAuthorizationPresent: Boolean,
    val vaultContainerSerializationAuthorizationPresent: Boolean,
    val vaultFileReadAuthorizationPresent: Boolean,
    val vaultFileWriteAuthorizationPresent: Boolean,
    val vaultFileDeleteAuthorizationPresent: Boolean,
    val vaultDirectoryCreationAuthorizationPresent: Boolean,
    val kdfExecutionAuthorizationPresent: Boolean,
    val aeadExecutionAuthorizationPresent: Boolean,
    val encryptionAuthorizationPresent: Boolean,
    val decryptionAuthorizationPresent: Boolean,
    val authenticationAuthorizationPresent: Boolean,
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
    val diagnosticsPolicyContainsNoSensitiveMaterial: Boolean,
    val normalSourceMaterialGuardExcludesBuildHistory: Boolean,
    val localArtifactRootExcludedFromNormalSourceMaterialCorpus: Boolean,
    val policyLabels: List<EncryptedVaultWorkingParserPolicyLabel>,
    val parserBlockers: List<EncryptedVaultWorkingParserBlocker>,
    val evidenceCount: Int,
    val blockerCount: Int,
    val warningCount: Int,
) {
    override fun toString(): String =
        "EncryptedVaultWorkingParserEvidence(REDACTED, COMMON_MAIN_IN_MEMORY_PARSER, " +
            "SYNTHETIC_ONLY, NO_WRITER, NO_PRODUCTION_MATERIAL, NO_IO, " +
            "NO_CRYPTO_AUTH_EXECUTION, DISABLED_PROVIDER_ONLY, NO_MAINNET)"
}

object EncryptedVaultWorkingParser {
    fun currentParserEvidence(): EncryptedVaultWorkingParserEvidence =
        EncryptedVaultWorkingParserEvidence(
            parserId = EncryptedVaultWorkingParserPolicyLabel.WorkingParser.safeLabel,
            parserVersion = 1,
            parserKind =
                EncryptedVaultWorkingParserKind
                    .EncryptedLocalVaultWorkingParserSyntheticVectorExecution,
            sourceSet = EncryptedVaultWorkingParserSourceSet.CommonMainInMemoryParser,
            workingParserAdmissionEvidencePresent = true,
            parserWriterSyntheticVectorCatalogEvidencePresent = true,
            parserWriterImplementationScaffoldEvidencePresent = true,
            containerFormatV1DecisionEvidencePresent = true,
            migrationCorruptionPolicyEvidencePresent = true,
            workingParserImplementationPresent = true,
            commonMainInMemoryParserPresent = true,
            syntheticVectorParserExecutionSupported = true,
            parserFailClosedPolicyPresent = true,
            parserRedactedResultPolicyPresent = true,
            parserErrorTaxonomyPresent = true,
            workingWriterImplementationPresent = false,
            productionVectorBytesPresent = false,
            productionParserInputBytesPresent = false,
            productionWriterOutputBytesPresent = false,
            productionVaultBytesPresent = false,
            vaultContainerSerializationPresent = false,
            vaultFileReadPresent = false,
            vaultFileWritePresent = false,
            vaultFileDeletePresent = false,
            vaultDirectoryCreated = false,
            atomicReplaceImplementationPresent = false,
            backupCreationPresent = false,
            rollbackImplementationPresent = false,
            migrationImplementationPresent = false,
            migrationExecutionPresent = false,
            corruptionRepairImplementationPresent = false,
            kdfExecutionPresent = false,
            aeadExecutionPresent = false,
            encryptionExecutionPresent = false,
            decryptionExecutionPresent = false,
            authenticationExecutionPresent = false,
            keyGenerationPresent = false,
            nonceGenerationPresent = false,
            tinkKeysetCreationPresent = false,
            tinkKeysetPersistencePresent = false,
            vaultStoragePathImplementationPresent = false,
            encryptedVaultRepositorySuccessPresent = false,
            lockSessionImplementationPresent = false,
            unlockImplementationPresent = false,
            runtimeSessionKeyPresent = false,
            sessionKeyCached = false,
            plaintextCachePresent = false,
            secureSecretStorageSuccessPathPresent = false,
            secureMetadataStorageSuccessPathPresent = false,
            productionObservationPersistencePresent = false,
            productionAddressIndexPersistencePresent = false,
            productionUtxoPersistencePresent = false,
            productionWalletHistoryPersistencePresent = false,
            productionSyncPresent = false,
            productionBackendClientPresent = false,
            productionProviderSelectionEnabled = false,
            productionProviderSelectable = false,
            productionSelectionStillDisabledProviderOnly = true,
            signingBroadcastingPresent = false,
            uiActionEnablementPresent = false,
            endpointPresent = false,
            mainnetPresent = false,
            futureWorkingWriterImplementationRequiresSeparatePass = true,
            futureParserWriterRoundTripRequiresSeparatePass = true,
            futureVaultStorageRepositoryRequiresSeparatePass = true,
            futureSecureStorageSuccessRequiresSeparatePass = true,
            futureSecureMetadataSuccessRequiresSeparatePass = true,
            futureProductionSyncRequiresSeparatePass = true,
            futureProductionProviderSelectionRequiresSeparatePass = true,
            workingWriterImplementationAuthorizationPresent = false,
            productionVectorCreationAuthorizationPresent = false,
            productionParserInputAuthorizationPresent = false,
            productionWriterOutputAuthorizationPresent = false,
            vaultContainerSerializationAuthorizationPresent = false,
            vaultFileReadAuthorizationPresent = false,
            vaultFileWriteAuthorizationPresent = false,
            vaultFileDeleteAuthorizationPresent = false,
            vaultDirectoryCreationAuthorizationPresent = false,
            kdfExecutionAuthorizationPresent = false,
            aeadExecutionAuthorizationPresent = false,
            encryptionAuthorizationPresent = false,
            decryptionAuthorizationPresent = false,
            authenticationAuthorizationPresent = false,
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
            diagnosticsPolicyContainsNoSensitiveMaterial = true,
            normalSourceMaterialGuardExcludesBuildHistory = true,
            localArtifactRootExcludedFromNormalSourceMaterialCorpus = true,
            policyLabels = EncryptedVaultWorkingParserPolicyLabel.entries.toList(),
            parserBlockers = EncryptedVaultWorkingParserBlocker.entries.toList(),
            evidenceCount = 5,
            blockerCount = 0,
            warningCount = 0,
        )

    internal fun rejectedInternalMaterialUnavailable(): EncryptedVaultWorkingParserResult =
        failed(
            classification = EncryptedVaultWorkingParserSyntheticClassification.NotClassified,
            blocker = EncryptedVaultWorkingParserBlocker.ProductionInputNotAuthorized,
            consumedByteCount = 0,
            testSourceSyntheticVectorBytesOnly = false,
        )

    fun parse(request: EncryptedVaultWorkingParserRequest): EncryptedVaultWorkingParserResult {
        if (
            !request.testSourceSyntheticVectorBytesPresent ||
            request.productionParserInputBytesPresent ||
            request.productionVaultBytesPresent
        ) {
            return failed(
                classification = EncryptedVaultWorkingParserSyntheticClassification.NotClassified,
                blocker = EncryptedVaultWorkingParserBlocker.ProductionInputNotAuthorized,
                consumedByteCount = 0,
                testSourceSyntheticVectorBytesOnly = false,
            )
        }

        return request.inspectMaterial(::parseSyntheticMaterial)
    }

    private fun parseSyntheticMaterial(material: ByteArray): EncryptedVaultWorkingParserResult {
        if (material.isEmpty()) {
            return failed(
                classification = EncryptedVaultWorkingParserSyntheticClassification.NotClassified,
                blocker = EncryptedVaultWorkingParserBlocker.EmptyInput,
                consumedByteCount = 0,
            )
        }
        if (!hasSyntheticPrefix(material)) {
            return failed(
                classification = EncryptedVaultWorkingParserSyntheticClassification.NotClassified,
                blocker = EncryptedVaultWorkingParserBlocker.UnrecognizedSyntheticVector,
                consumedByteCount = material.size,
            )
        }

        val tokenStart = SYNTHETIC_TOKEN_START_INDEX
        val tokenEnd = secondSeparatorIndex(material)
        if (tokenEnd <= tokenStart || tokenEnd >= material.lastIndex) {
            return failed(
                classification = EncryptedVaultWorkingParserSyntheticClassification.NotClassified,
                blocker = EncryptedVaultWorkingParserBlocker.MalformedSyntheticSectionOrder,
                consumedByteCount = material.size,
            )
        }

        val suffix = codeAt(material, material.lastIndex)
        return when {
            tokenMatches(material, tokenStart, tokenEnd, LOWER_M, LOWER_I, LOWER_N) ->
                parsed(
                    classification =
                        EncryptedVaultWorkingParserSyntheticClassification.SyntheticHeaderOnly,
                    sectionCount = 1,
                    consumedByteCount = material.size,
                )
            tokenMatches(material, tokenStart, tokenEnd, LOWER_K, LOWER_D, LOWER_F) ->
                parsed(
                    classification =
                        EncryptedVaultWorkingParserSyntheticClassification.SyntheticKdfSection,
                    sectionCount = 2,
                    consumedByteCount = material.size,
                )
            tokenMatches(material, tokenStart, tokenEnd, LOWER_E, LOWER_N, LOWER_V) ->
                parsed(
                    classification =
                        EncryptedVaultWorkingParserSyntheticClassification.SyntheticDirectory,
                    sectionCount = 3,
                    consumedByteCount = material.size,
                )
            tokenMatches(material, tokenStart, tokenEnd, LOWER_O, LOWER_N, LOWER_E) ->
                parsed(
                    classification =
                        EncryptedVaultWorkingParserSyntheticClassification
                            .SyntheticSingleRecordEnvelope,
                    sectionCount = 4,
                    consumedByteCount = material.size,
                )
            tokenMatches(material, tokenStart, tokenEnd, LOWER_M, LOWER_A, LOWER_N, LOWER_Y) ->
                parsed(
                    classification =
                        EncryptedVaultWorkingParserSyntheticClassification
                            .SyntheticMultiRecordDirectory,
                    sectionCount = 5,
                    consumedByteCount = material.size,
                )
            tokenMatches(material, tokenStart, tokenEnd, LOWER_V, LOWER_E, LOWER_R) ->
                failed(
                    classification =
                        EncryptedVaultWorkingParserSyntheticClassification.NotClassified,
                    blocker = EncryptedVaultWorkingParserBlocker.UnsupportedVersion,
                    consumedByteCount = material.size,
                )
            tokenMatches(material, tokenStart, tokenEnd, LOWER_C, LOWER_R, LOWER_I, LOWER_T) ->
                failed(
                    classification =
                        EncryptedVaultWorkingParserSyntheticClassification.NotClassified,
                    blocker = EncryptedVaultWorkingParserBlocker.UnsupportedCriticalFeature,
                    consumedByteCount = material.size,
                )
            tokenMatches(material, tokenStart, tokenEnd, LOWER_C, LOWER_U, LOWER_T) &&
                suffix == LOWER_H ->
                failed(
                    classification =
                        EncryptedVaultWorkingParserSyntheticClassification.NotClassified,
                    blocker = EncryptedVaultWorkingParserBlocker.TruncatedHeader,
                    consumedByteCount = material.size,
                )
            tokenMatches(material, tokenStart, tokenEnd, LOWER_C, LOWER_U, LOWER_T) &&
                suffix == LOWER_I ->
                failed(
                    classification =
                        EncryptedVaultWorkingParserSyntheticClassification.NotClassified,
                    blocker = EncryptedVaultWorkingParserBlocker.TruncatedRecordEnvelope,
                    consumedByteCount = material.size,
                )
            tokenMatches(material, tokenStart, tokenEnd, LOWER_R, LOWER_E, LOWER_D) ->
                failed(
                    classification =
                        EncryptedVaultWorkingParserSyntheticClassification.RedactedDiagnosticsOnly,
                    blocker = EncryptedVaultWorkingParserBlocker.RedactedDiagnosticsOnly,
                    consumedByteCount = material.size,
                )
            tokenMatches(material, tokenStart, tokenEnd, LOWER_M, LOWER_I, LOWER_G) ->
                failed(
                    classification =
                        EncryptedVaultWorkingParserSyntheticClassification.NotClassified,
                    blocker = EncryptedVaultWorkingParserBlocker.MigrationRequired,
                    consumedByteCount = material.size,
                )
            tokenMatches(material, tokenStart, tokenEnd, LOWER_C, LOWER_O, LOWER_R) ->
                failed(
                    classification =
                        EncryptedVaultWorkingParserSyntheticClassification.NotClassified,
                    blocker = EncryptedVaultWorkingParserBlocker.CorruptionSuspected,
                    consumedByteCount = material.size,
                )
            else ->
                failed(
                    classification =
                        EncryptedVaultWorkingParserSyntheticClassification.NotClassified,
                    blocker = EncryptedVaultWorkingParserBlocker.UnrecognizedSyntheticVector,
                    consumedByteCount = material.size,
                )
        }
    }

    private fun parsed(
        classification: EncryptedVaultWorkingParserSyntheticClassification,
        sectionCount: Int,
        consumedByteCount: Int,
    ): EncryptedVaultWorkingParserResult =
        result(
            status = EncryptedVaultWorkingParserStatus.ParsedSyntheticVector,
            classification = classification,
            accepted = true,
            sectionCount = sectionCount,
            consumedByteCount = consumedByteCount,
            blockers = emptyList(),
        )

    private fun failed(
        classification: EncryptedVaultWorkingParserSyntheticClassification,
        blocker: EncryptedVaultWorkingParserBlocker,
        consumedByteCount: Int,
        testSourceSyntheticVectorBytesOnly: Boolean = true,
    ): EncryptedVaultWorkingParserResult =
        result(
            status = EncryptedVaultWorkingParserStatus.FailedClosed,
            classification = classification,
            accepted = false,
            sectionCount = 0,
            consumedByteCount = consumedByteCount,
            blockers = listOf(blocker),
            testSourceSyntheticVectorBytesOnly = testSourceSyntheticVectorBytesOnly,
        )

    private fun result(
        status: EncryptedVaultWorkingParserStatus,
        classification: EncryptedVaultWorkingParserSyntheticClassification,
        accepted: Boolean,
        sectionCount: Int,
        consumedByteCount: Int,
        blockers: List<EncryptedVaultWorkingParserBlocker>,
        testSourceSyntheticVectorBytesOnly: Boolean = true,
    ): EncryptedVaultWorkingParserResult {
        val diagnostics = EncryptedVaultWorkingParserRedactedDiagnostics(
            status = status,
            classification = classification,
            blockerCount = blockers.size,
            warningCount = 0,
        )
        return EncryptedVaultWorkingParserResult(
            status = status,
            classification = classification,
            accepted = accepted,
            sectionCount = sectionCount,
            consumedByteCount = consumedByteCount,
            producedByteCount = 0,
            blockers = blockers,
            blockerCount = blockers.size,
            warningCount = 0,
            diagnostics = diagnostics,
            testSourceSyntheticVectorBytesOnly = testSourceSyntheticVectorBytesOnly,
            inputBytesExposed = false,
            inputBytesCopiedToResult = false,
            sectionBytesExposed = false,
            parsedPayloadByteArraysCreated = false,
            repositoryObjectCreated = false,
            storageStateCreated = false,
            writerObjectCreated = false,
            fileIoUsed = false,
            cryptoAuthenticationExecuted = false,
        )
    }

    private fun hasSyntheticPrefix(material: ByteArray): Boolean =
        material.size >= SYNTHETIC_MIN_SIZE &&
            codeAt(material, 0) == LOWER_S &&
            codeAt(material, 1) == LOWER_K &&
            codeAt(material, 2) == LOWER_V &&
            codeAt(material, 3) == SEPARATOR

    private fun secondSeparatorIndex(material: ByteArray): Int {
        var index = SYNTHETIC_TOKEN_START_INDEX
        while (index < material.size) {
            if (codeAt(material, index) == SEPARATOR) return index
            index += 1
        }
        return -1
    }

    private fun tokenMatches(
        material: ByteArray,
        startInclusive: Int,
        endExclusive: Int,
        vararg expected: Int,
    ): Boolean {
        if (endExclusive - startInclusive != expected.size) return false
        var index = 0
        while (index < expected.size) {
            if (codeAt(material, startInclusive + index) != expected[index]) return false
            index += 1
        }
        return true
    }

    private fun codeAt(material: ByteArray, index: Int): Int =
        material[index].toInt() and BYTE_MASK

    private const val BYTE_MASK = 0xFF
    private const val SEPARATOR = 45
    private const val SYNTHETIC_MIN_SIZE = 6
    private const val SYNTHETIC_TOKEN_START_INDEX = 4
    private const val LOWER_A = 97
    private const val LOWER_C = 99
    private const val LOWER_D = 100
    private const val LOWER_E = 101
    private const val LOWER_F = 102
    private const val LOWER_G = 103
    private const val LOWER_H = 104
    private const val LOWER_I = 105
    private const val LOWER_K = 107
    private const val LOWER_M = 109
    private const val LOWER_N = 110
    private const val LOWER_O = 111
    private const val LOWER_R = 114
    private const val LOWER_S = 115
    private const val LOWER_T = 116
    private const val LOWER_U = 117
    private const val LOWER_V = 118
    private const val LOWER_Y = 121
}
