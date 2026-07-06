package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationSafeLabel(
    val value: String,
) {
    override fun toString(): String =
        "RedactedTestOnlyProviderIdentityProviderOperationSyntheticTraceValidationSafeLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationSourceSet {
    CommonTest,
}

enum class SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationCheck {
    SourceTracePresent,
    SourceTraceCreated,
    SourceTraceCommonTestOnly,
    SourceTracePayloadFree,
    SourceTraceRedacted,
    SourceTraceSafeLabelsOnly,
    SourceTraceEnumEvidenceOnly,
    SourceTraceCountsOnly,
    SourceTraceBooleansOnly,
    TracePayloadAbsent,
    RawKatMaterialAbsent,
    PublicVectorBytesAbsent,
    PublicVectorHexAbsent,
    ProviderHandleAbsent,
    SourceLocationAbsent,
    StackTraceAbsent,
    DiagnosticsPayloadAbsent,
    AnalyticsPayloadAbsent,
    CrashReportPayloadAbsent,
    SupportExportPayloadAbsent,
    SecretsAbsent,
    WalletMaterialAbsent,
    EndpointMaterialAbsent,
    FilesystemPathAbsent,
    VaultCryptoProviderImplementationAbsent,
    VaultCryptoProviderInstanceAbsent,
    ProviderOperationExecutionAbsent,
    CryptoExecutionAbsent,
    KatRunnerAbsent,
    KatExecutorAbsent,
    RegistryFactoryDispatcherExecutorTargetAbsent,
    VaultLifecycleAbsent,
    VaultPersistenceAbsent,
    ProductionSyncAbsent,
    SigningBroadcastingAbsent,
    UiAbsent,
    EndpointAbsent,
    MainnetAbsent,
    ProviderSelectionDisabledOnly,
    ProductionProviderSelectableFalse,
    ProductionAuthorizationAbsent,
    ProviderSelectionAuthorizationAbsent,
    ProviderOperationAuthorizationAbsent,
    CryptoAuthorizationAbsent,
    KatExecutorAuthorizationAbsent,
    VaultPersistenceAuthorizationAbsent,
    SyncAuthorizationAbsent,
    SigningBroadcastingAuthorizationAbsent,
    UiAuthorizationAbsent,
    EndpointAuthorizationAbsent,
    MainnetAuthorizationAbsent,
}

enum class SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationFailureLabel {
    SourceTraceMissing,
    SourceTraceNotCreated,
    SourceTraceNotCommonTestOnly,
    SourceTraceNotPayloadFree,
    SourceTraceOutputNotRedacted,
    SourceTraceContainsPayloadOrMaterial,
    SourceTraceContainsRuntimeReachability,
    SourceTraceContainsExecution,
    SourceTraceContainsAuthorization,
    ProviderSelectionNotDisabledOnly,
}

enum class SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationBlocker {
    ValidationFailed,
    SourceTraceUnsafe,
    RuntimeReachabilityPresent,
    AuthorizationPresent,
}

enum class SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationWarning {
    None,
}

data class SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationReport(
    val validationId:
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationSafeLabel,
    val sourceTracePresent: Boolean,
    val sourceTraceCreated: Boolean,
    val sourceTraceCommonTestOnly: Boolean,
    val sourceTracePayloadFree: Boolean,
    val sourceTraceRedacted: Boolean,
    val sourceTraceSafeLabelEvidenceOnly: Boolean,
    val sourceTraceEnumEvidenceOnly: Boolean,
    val sourceTraceCountEvidenceOnly: Boolean,
    val sourceTraceBooleanEvidenceOnly: Boolean,
    val safeLabelCount: Int,
    val enumEvidenceCount: Int,
    val countEvidenceCount: Int,
    val booleanEvidenceCount: Int,
    val validationCheckCount: Int,
    val validationPassed: Boolean,
    val syntheticTraceValidationPassed: Boolean,
    val failureLabels:
        List<SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationFailureLabel>,
    val blockers:
        List<SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationBlocker>,
    val warnings:
        List<SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationWarning>,
    val validationIsCommonTestOnly: Boolean,
    val validationIsValidationOnly: Boolean,
    val validationCreatesTrace: Boolean,
    val validationCreatesTracePayloads: Boolean,
    val productionProviderSelectable: Boolean,
    val disabledProviderOnly: Boolean,
    val productionRuntimeReachable: Boolean,
    val providerOperationExecuted: Boolean,
    val cryptoExecuted: Boolean,
    val katRunnerPresent: Boolean,
    val katExecutorPresent: Boolean,
    val providerKatExecutorPresent: Boolean,
    val providerImplementationPresent: Boolean,
    val registryReachable: Boolean,
    val factoryReachable: Boolean,
    val dispatcherReachable: Boolean,
    val executorTargetReachable: Boolean,
    val providerKatExecutorReachable: Boolean,
    val tracePayloadPresent: Boolean,
    val providerOperationPayloadPresent: Boolean,
    val rawKatMaterialPresent: Boolean,
    val vectorBytesPresent: Boolean,
    val vectorHexPresent: Boolean,
    val publicVectorBytesPresent: Boolean,
    val publicVectorHexPresent: Boolean,
    val providerHandlePresent: Boolean,
    val sourceLocationPresent: Boolean,
    val stackTracePresent: Boolean,
    val diagnosticsPayloadPresent: Boolean,
    val analyticsPayloadPresent: Boolean,
    val crashReportPayloadPresent: Boolean,
    val supportExportPayloadPresent: Boolean,
    val vaultPersistencePresent: Boolean,
    val vaultLifecyclePresent: Boolean,
    val syncPresent: Boolean,
    val signingBroadcastingPresent: Boolean,
    val uiPresent: Boolean,
    val endpointPresent: Boolean,
    val mainnetPresent: Boolean,
    val productionAuthorizationPresent: Boolean,
    val providerSelectionAuthorizationPresent: Boolean,
    val providerOperationAuthorizationPresent: Boolean,
    val cryptoAuthorizationPresent: Boolean,
    val katRunnerAuthorizationPresent: Boolean,
    val katExecutorAuthorizationPresent: Boolean,
    val providerKatExecutorAuthorizationPresent: Boolean,
    val vaultPersistenceAuthorizationPresent: Boolean,
    val syncAuthorizationPresent: Boolean,
    val signingBroadcastingAuthorizationPresent: Boolean,
    val uiAuthorizationPresent: Boolean,
    val endpointAuthorizationPresent: Boolean,
    val mainnetAuthorizationPresent: Boolean,
    val validationChecks:
        List<SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationCheck>,
    val sourceSet:
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationSourceSet,
    val displayLabel:
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationSafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationReport(REDACTED, PAYLOAD_FREE, COMMON_TEST_ONLY, DISABLED_PROVIDER_ONLY, NOT_AUTHORIZATION)"
}

object SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationPolicy {
    fun currentProviderOperationSyntheticTraceValidationReport():
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationReport {
        val sourceTrace =
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTracePolicy
                .currentProviderOperationSyntheticTrace()
        val validationChecks =
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationCheck
                .entries
                .toList()

        val sourceTracePresent = true
        val sourceTraceCreated = sourceTrace.syntheticTraceCreated
        val sourceTraceCommonTestOnly =
            sourceTrace.traceIsCommonTestOnly &&
                sourceTrace.sourceSet ==
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSourceSet.CommonTest
        val sourceTracePayloadFree =
            sourceTrace.syntheticTracePayloadFree &&
                !sourceTrace.syntheticTracePayloadPresent &&
                !sourceTrace.syntheticTraceContainsRawBytes &&
                !sourceTrace.syntheticTraceContainsHex &&
                !sourceTrace.syntheticTraceContainsProviderHandles &&
                !sourceTrace.syntheticTraceContainsCryptoObjects &&
                !sourceTrace.syntheticTraceContainsWalletMaterial &&
                !sourceTrace.syntheticTraceContainsEndpointMaterial &&
                !sourceTrace.syntheticTraceContainsSourceLocation &&
                !sourceTrace.syntheticTraceContainsDiagnosticsPayload &&
                !sourceTrace.syntheticTraceContainsAnalyticsPayload &&
                !sourceTrace.syntheticTraceContainsCrashReportPayload &&
                !sourceTrace.syntheticTraceContainsSupportExportPayload
        val sourceTraceRedacted =
            outputIsRedacted(sourceTrace.toString()) &&
                outputIsRedacted(sourceTrace.displayLabel.toString())
        val sourceTraceSafeLabelEvidenceOnly = sourceTrace.displayLabel.value.isNotBlank()
        val sourceTraceEnumEvidenceOnly =
            sourceTrace.traceEvents.size ==
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceEvent.entries.size
        val sourceTraceCountEvidenceOnly =
            sourceTrace.markerCount == 1 &&
                sourceTrace.caseBindingCount == 1 &&
                sourceTrace.providerOperationMetadataKatCount == 1 &&
                sourceTrace.providerOperationNoopKatCount == 1 &&
                sourceTrace.providerOperationNoopKatValidationCount == 1 &&
                sourceTrace.providerOperationNoopKatSuiteReportCount == 1 &&
                sourceTrace.providerOperationNoopExecutionBoundaryCount == 1 &&
                sourceTrace.providerOperationNoopExecutionBoundaryValidationCount == 1 &&
                sourceTrace.providerOperationNoopExecutionBoundarySuiteReportCount == 1 &&
                sourceTrace.providerOperationSyntheticTraceAdmissionCount == 1
        val sourceTraceBooleanEvidenceOnly =
            sourceTrace.expectedSafeIdMatched &&
                sourceTrace.expectedFixtureIdMatched &&
                sourceTrace.expectedVectorIdMatched &&
                sourceTrace.expectedCaseIdMatched &&
                sourceTrace.expectedProviderOperationMetadataKatIdMatched &&
                sourceTrace.expectedProviderOperationNoopKatIdMatched &&
                sourceTrace.expectedProviderOperationNoopExecutionBoundaryIdMatched &&
                sourceTrace.expectedProviderOperationNoopExecutionBoundarySuiteReportIdMatched &&
                sourceTrace.expectedProviderOperationSyntheticTraceIdMatched &&
                sourceTrace.providerOperationNoopKatPassed &&
                sourceTrace.providerOperationNoopKatValidationPassed &&
                sourceTrace.providerOperationNoopKatSuitePassed &&
                sourceTrace.syntheticNoopResultPresent &&
                sourceTrace.noopExecutionBoundaryModeled &&
                sourceTrace.syntheticNoopEvaluationPermitted &&
                sourceTrace.noopExecutionBoundaryValidationPassed &&
                sourceTrace.noopExecutionBoundarySuitePassed &&
                sourceTrace.futureSyntheticTraceCriteriaModeled

        val productionProviderSelectable = sourceTrace.productionProviderSelectable
        val disabledProviderOnly = sourceTraceCommonTestOnly && !productionProviderSelectable && !sourceTrace.runtimeSelectable
        val tracePayloadPresent = sourceTrace.syntheticTracePayloadPresent
        val providerOperationPayloadPresent = false
        val rawKatMaterialPresent = sourceTrace.rawKatMaterialPresent
        val vectorBytesPresent = sourceTrace.rawVectorBytesPresent
        val vectorHexPresent = sourceTrace.rawVectorHexPresent || sourceTrace.syntheticTraceContainsHex
        val publicVectorBytesPresent = sourceTrace.publicVectorBytesPresent
        val publicVectorHexPresent = sourceTrace.publicVectorHexPresent
        val providerHandlePresent = sourceTrace.syntheticTraceContainsProviderHandles
        val sourceLocationPresent = sourceTrace.syntheticTraceContainsSourceLocation
        val stackTracePresent = false
        val diagnosticsPayloadPresent = sourceTrace.syntheticTraceContainsDiagnosticsPayload
        val analyticsPayloadPresent = sourceTrace.syntheticTraceContainsAnalyticsPayload
        val crashReportPayloadPresent = sourceTrace.syntheticTraceContainsCrashReportPayload
        val supportExportPayloadPresent = sourceTrace.syntheticTraceContainsSupportExportPayload
        val providerOperationExecuted =
            sourceTrace.providerOperationExecutionPresent ||
                sourceTrace.traceReportsProviderOperationExecution ||
                sourceTrace.realProviderOperationExecutionPermitted ||
                sourceTrace.canExecuteProviderOperations ||
                sourceTrace.providerOperationReachable
        val cryptoExecuted =
            sourceTrace.cryptoExecutionPresent ||
                sourceTrace.traceReportsCryptoOperation ||
                sourceTrace.cryptoExecutionPermitted ||
                sourceTrace.canExecuteCrypto ||
                sourceTrace.cryptoExecutionReachable
        val katRunnerPresent =
            sourceTrace.providerOperationKatRunnerPresent ||
                sourceTrace.katRunnerPermitted
        val katExecutorPresent =
            sourceTrace.providerOperationKatExecutorPresent ||
                sourceTrace.katExecutorPermitted
        val providerKatExecutorPresent = sourceTrace.providerOperationKatExecutorPresent
        val providerImplementationPresent =
            sourceTrace.implementsVaultCryptoProvider ||
                sourceTrace.containsVaultCryptoProvider
        val registryReachable = sourceTrace.registrySelectable
        val factoryReachable = sourceTrace.factoryReachable
        val dispatcherReachable = sourceTrace.dispatcherReachable
        val executorTargetReachable = sourceTrace.executorTargetable
        val providerKatExecutorReachable = sourceTrace.providerKatExecutorReachable
        val vaultPersistencePresent =
            sourceTrace.vaultPersistencePermitted ||
                sourceTrace.traceReportsPersistence ||
                sourceTrace.persistenceReachable ||
                sourceTrace.canUseForPersistence
        val vaultLifecyclePresent =
            sourceTrace.traceReportsVaultLifecycle ||
                sourceTrace.vaultLifecycleReachable ||
                sourceTrace.canUseForVaultLifecycle
        val syncPresent = sourceTrace.productionSyncReachable || sourceTrace.canUseForSync
        val signingBroadcastingPresent =
            sourceTrace.signingBroadcastingReachable ||
                sourceTrace.canUseForSigning ||
                sourceTrace.canUseForBroadcasting
        val uiPresent = sourceTrace.uiSurfaceReachable
        val endpointPresent =
            sourceTrace.publicEndpointReachable ||
                sourceTrace.syntheticTraceContainsEndpointMaterial ||
                sourceTrace.backendClientReachable
        val mainnetPresent =
            sourceTrace.mainnetReachable ||
                sourceTrace.mainnetPermitted ||
                sourceTrace.canUseForMainnet
        val productionRuntimeReachable =
            sourceTrace.runtimeSelectable ||
                registryReachable ||
                factoryReachable ||
                dispatcherReachable ||
                executorTargetReachable ||
                providerKatExecutorReachable ||
                sourceTrace.backendClientReachable ||
                sourceTrace.bdkWalletStateReachable ||
                sourceTrace.settingsCodecReachable ||
                uiPresent ||
                endpointPresent ||
                mainnetPresent

        val productionAuthorizationPresent = sourceTrace.traceIsProductionAuthorization
        val providerSelectionAuthorizationPresent = sourceTrace.traceIsProviderSelectionAuthorization
        val providerOperationAuthorizationPresent = sourceTrace.traceIsProviderOperationAuthorization
        val cryptoAuthorizationPresent = sourceTrace.traceIsCryptoAuthorization
        val katRunnerAuthorizationPresent = sourceTrace.katRunnerPermitted
        val katExecutorAuthorizationPresent = sourceTrace.traceIsKatExecutorAuthorization
        val providerKatExecutorAuthorizationPresent =
            sourceTrace.traceIsKatExecutorAuthorization ||
                providerKatExecutorPresent ||
                providerKatExecutorReachable
        val vaultPersistenceAuthorizationPresent = sourceTrace.traceIsVaultPersistenceAuthorization
        val syncAuthorizationPresent = syncPresent
        val signingBroadcastingAuthorizationPresent = signingBroadcastingPresent
        val uiAuthorizationPresent = uiPresent
        val endpointAuthorizationPresent = endpointPresent
        val mainnetAuthorizationPresent = sourceTrace.traceIsMainnetAuthorization || mainnetPresent

        val failureLabels =
            mutableListOf<
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationFailureLabel
            >()
        if (!sourceTracePresent) {
            failureLabels +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationFailureLabel
                    .SourceTraceMissing
        }
        if (!sourceTraceCreated) {
            failureLabels +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationFailureLabel
                    .SourceTraceNotCreated
        }
        if (!sourceTraceCommonTestOnly) {
            failureLabels +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationFailureLabel
                    .SourceTraceNotCommonTestOnly
        }
        if (!sourceTracePayloadFree) {
            failureLabels +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationFailureLabel
                    .SourceTraceNotPayloadFree
        }
        if (!sourceTraceRedacted) {
            failureLabels +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationFailureLabel
                    .SourceTraceOutputNotRedacted
        }
        if (
            tracePayloadPresent ||
            providerOperationPayloadPresent ||
            rawKatMaterialPresent ||
            vectorBytesPresent ||
            vectorHexPresent ||
            publicVectorBytesPresent ||
            publicVectorHexPresent ||
            providerHandlePresent ||
            sourceLocationPresent ||
            stackTracePresent ||
            diagnosticsPayloadPresent ||
            analyticsPayloadPresent ||
            crashReportPayloadPresent ||
            supportExportPayloadPresent
        ) {
            failureLabels +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationFailureLabel
                    .SourceTraceContainsPayloadOrMaterial
        }
        if (productionRuntimeReachable || vaultLifecyclePresent || vaultPersistencePresent || syncPresent) {
            failureLabels +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationFailureLabel
                    .SourceTraceContainsRuntimeReachability
        }
        if (providerOperationExecuted || cryptoExecuted || katRunnerPresent || katExecutorPresent) {
            failureLabels +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationFailureLabel
                    .SourceTraceContainsExecution
        }
        if (
            productionAuthorizationPresent ||
            providerSelectionAuthorizationPresent ||
            providerOperationAuthorizationPresent ||
            cryptoAuthorizationPresent ||
            katRunnerAuthorizationPresent ||
            katExecutorAuthorizationPresent ||
            providerKatExecutorAuthorizationPresent ||
            vaultPersistenceAuthorizationPresent ||
            syncAuthorizationPresent ||
            signingBroadcastingAuthorizationPresent ||
            uiAuthorizationPresent ||
            endpointAuthorizationPresent ||
            mainnetAuthorizationPresent
        ) {
            failureLabels +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationFailureLabel
                    .SourceTraceContainsAuthorization
        }
        if (!disabledProviderOnly) {
            failureLabels +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationFailureLabel
                    .ProviderSelectionNotDisabledOnly
        }

        val blockers =
            mutableListOf<
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationBlocker
            >()
        if (failureLabels.isNotEmpty()) {
            blockers +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationBlocker
                    .ValidationFailed
        }
        if (!sourceTracePayloadFree || !sourceTraceRedacted) {
            blockers +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationBlocker
                    .SourceTraceUnsafe
        }
        if (productionRuntimeReachable) {
            blockers +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationBlocker
                    .RuntimeReachabilityPresent
        }
        if (
            productionAuthorizationPresent ||
            providerSelectionAuthorizationPresent ||
            providerOperationAuthorizationPresent ||
            cryptoAuthorizationPresent ||
            katExecutorAuthorizationPresent ||
            vaultPersistenceAuthorizationPresent ||
            mainnetAuthorizationPresent
        ) {
            blockers +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationBlocker
                    .AuthorizationPresent
        }

        val validationPassed =
            sourceTracePresent &&
                sourceTraceCreated &&
                sourceTraceCommonTestOnly &&
                sourceTracePayloadFree &&
                sourceTraceRedacted &&
                sourceTraceSafeLabelEvidenceOnly &&
                sourceTraceEnumEvidenceOnly &&
                sourceTraceCountEvidenceOnly &&
                sourceTraceBooleanEvidenceOnly &&
                disabledProviderOnly &&
                !productionProviderSelectable &&
                !productionRuntimeReachable &&
                !providerOperationExecuted &&
                !cryptoExecuted &&
                !katRunnerPresent &&
                !katExecutorPresent &&
                !providerKatExecutorPresent &&
                !providerImplementationPresent &&
                !registryReachable &&
                !factoryReachable &&
                !dispatcherReachable &&
                !executorTargetReachable &&
                !providerKatExecutorReachable &&
                !tracePayloadPresent &&
                !providerOperationPayloadPresent &&
                !rawKatMaterialPresent &&
                !vectorBytesPresent &&
                !vectorHexPresent &&
                !publicVectorBytesPresent &&
                !publicVectorHexPresent &&
                !providerHandlePresent &&
                !sourceLocationPresent &&
                !stackTracePresent &&
                !diagnosticsPayloadPresent &&
                !analyticsPayloadPresent &&
                !crashReportPayloadPresent &&
                !supportExportPayloadPresent &&
                !vaultPersistencePresent &&
                !vaultLifecyclePresent &&
                !syncPresent &&
                !signingBroadcastingPresent &&
                !uiPresent &&
                !endpointPresent &&
                !mainnetPresent &&
                !productionAuthorizationPresent &&
                !providerSelectionAuthorizationPresent &&
                !providerOperationAuthorizationPresent &&
                !cryptoAuthorizationPresent &&
                !katRunnerAuthorizationPresent &&
                !katExecutorAuthorizationPresent &&
                !providerKatExecutorAuthorizationPresent &&
                !vaultPersistenceAuthorizationPresent &&
                !syncAuthorizationPresent &&
                !signingBroadcastingAuthorizationPresent &&
                !uiAuthorizationPresent &&
                !endpointAuthorizationPresent &&
                !mainnetAuthorizationPresent &&
                failureLabels.isEmpty() &&
                blockers.isEmpty()

        return SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationReport(
            validationId = SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationSafeLabel(
                "PAYLOAD_FREE_SYNTHETIC_TRACE_VALIDATION_COMMON_TEST_ONLY",
            ),
            sourceTracePresent = sourceTracePresent,
            sourceTraceCreated = sourceTraceCreated,
            sourceTraceCommonTestOnly = sourceTraceCommonTestOnly,
            sourceTracePayloadFree = sourceTracePayloadFree,
            sourceTraceRedacted = sourceTraceRedacted,
            sourceTraceSafeLabelEvidenceOnly = sourceTraceSafeLabelEvidenceOnly,
            sourceTraceEnumEvidenceOnly = sourceTraceEnumEvidenceOnly,
            sourceTraceCountEvidenceOnly = sourceTraceCountEvidenceOnly,
            sourceTraceBooleanEvidenceOnly = sourceTraceBooleanEvidenceOnly,
            safeLabelCount = 2,
            enumEvidenceCount = sourceTrace.traceEvents.size + validationChecks.size,
            countEvidenceCount = 10,
            booleanEvidenceCount = 83,
            validationCheckCount = validationChecks.size,
            validationPassed = validationPassed,
            syntheticTraceValidationPassed = validationPassed,
            failureLabels = failureLabels,
            blockers = blockers,
            warnings = emptyList(),
            validationIsCommonTestOnly = true,
            validationIsValidationOnly = true,
            validationCreatesTrace = false,
            validationCreatesTracePayloads = false,
            productionProviderSelectable = productionProviderSelectable,
            disabledProviderOnly = disabledProviderOnly,
            productionRuntimeReachable = productionRuntimeReachable,
            providerOperationExecuted = providerOperationExecuted,
            cryptoExecuted = cryptoExecuted,
            katRunnerPresent = katRunnerPresent,
            katExecutorPresent = katExecutorPresent,
            providerKatExecutorPresent = providerKatExecutorPresent,
            providerImplementationPresent = providerImplementationPresent,
            registryReachable = registryReachable,
            factoryReachable = factoryReachable,
            dispatcherReachable = dispatcherReachable,
            executorTargetReachable = executorTargetReachable,
            providerKatExecutorReachable = providerKatExecutorReachable,
            tracePayloadPresent = tracePayloadPresent,
            providerOperationPayloadPresent = providerOperationPayloadPresent,
            rawKatMaterialPresent = rawKatMaterialPresent,
            vectorBytesPresent = vectorBytesPresent,
            vectorHexPresent = vectorHexPresent,
            publicVectorBytesPresent = publicVectorBytesPresent,
            publicVectorHexPresent = publicVectorHexPresent,
            providerHandlePresent = providerHandlePresent,
            sourceLocationPresent = sourceLocationPresent,
            stackTracePresent = stackTracePresent,
            diagnosticsPayloadPresent = diagnosticsPayloadPresent,
            analyticsPayloadPresent = analyticsPayloadPresent,
            crashReportPayloadPresent = crashReportPayloadPresent,
            supportExportPayloadPresent = supportExportPayloadPresent,
            vaultPersistencePresent = vaultPersistencePresent,
            vaultLifecyclePresent = vaultLifecyclePresent,
            syncPresent = syncPresent,
            signingBroadcastingPresent = signingBroadcastingPresent,
            uiPresent = uiPresent,
            endpointPresent = endpointPresent,
            mainnetPresent = mainnetPresent,
            productionAuthorizationPresent = productionAuthorizationPresent,
            providerSelectionAuthorizationPresent = providerSelectionAuthorizationPresent,
            providerOperationAuthorizationPresent = providerOperationAuthorizationPresent,
            cryptoAuthorizationPresent = cryptoAuthorizationPresent,
            katRunnerAuthorizationPresent = katRunnerAuthorizationPresent,
            katExecutorAuthorizationPresent = katExecutorAuthorizationPresent,
            providerKatExecutorAuthorizationPresent = providerKatExecutorAuthorizationPresent,
            vaultPersistenceAuthorizationPresent = vaultPersistenceAuthorizationPresent,
            syncAuthorizationPresent = syncAuthorizationPresent,
            signingBroadcastingAuthorizationPresent = signingBroadcastingAuthorizationPresent,
            uiAuthorizationPresent = uiAuthorizationPresent,
            endpointAuthorizationPresent = endpointAuthorizationPresent,
            mainnetAuthorizationPresent = mainnetAuthorizationPresent,
            validationChecks = validationChecks,
            sourceSet =
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationSourceSet.CommonTest,
            displayLabel = SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationSafeLabel(
                "PAYLOAD_FREE_SYNTHETIC_TRACE_VALIDATION_REPORT",
            ),
        )
    }

    private fun outputIsRedacted(output: String): Boolean =
        "skald-test-only" !in output &&
            "trace payload" !in output.lowercase() &&
            "raw kat" !in output.lowercase() &&
            "public vector bytes" !in output.lowercase() &&
            "public vector hex" !in output.lowercase() &&
            "provider handle" !in output.lowercase() &&
            "source location" !in output.lowercase() &&
            "stack trace" !in output.lowercase() &&
            "diagnostic payload" !in output.lowercase() &&
            "analytics payload" !in output.lowercase() &&
            "crash report" !in output.lowercase() &&
            "support export" !in output.lowercase()
}
