package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportSafeLabel(
    val value: String,
) {
    override fun toString(): String =
        "RedactedTestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportSafeLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportSourceSet {
    CommonTest,
}

enum class SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportChain {
    PayloadFreeSyntheticProviderOperationTrace,
}

enum class SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportCheck {
    AdmissionGatePresent,
    SyntheticTracePresent,
    SyntheticTraceCreated,
    SyntheticTraceValidationPresent,
    SyntheticTraceValidationPassed,
    PriorNoopExecutionBoundarySuitePresent,
    SyntheticTraceCommonTestOnly,
    SyntheticTracePayloadFree,
    SyntheticTraceValidationCommonTestOnly,
    SyntheticTraceValidationPayloadFree,
    SuiteReportCommonTestOnly,
    SuiteReportPayloadFree,
    SafeLabelEnumCountBooleanEvidenceOnly,
    RawKatMaterialAbsent,
    PublicVectorBytesAbsent,
    PublicVectorHexAbsent,
    TracePayloadAbsent,
    ProviderHandleAbsent,
    SourceLocationAbsent,
    StackTraceAbsent,
    DiagnosticsPayloadAbsent,
    AnalyticsPayloadAbsent,
    CrashReportPayloadAbsent,
    SupportExportPayloadAbsent,
    ProviderOperationExecutionAbsent,
    CryptoExecutionAbsent,
    KatRunnerAbsent,
    KatExecutorAbsent,
    ProviderImplementationAbsent,
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
    KatRunnerAuthorizationAbsent,
    KatExecutorAuthorizationAbsent,
    VaultPersistenceAuthorizationAbsent,
    SyncAuthorizationAbsent,
    SigningBroadcastingAuthorizationAbsent,
    EndpointAuthorizationAbsent,
    UiAuthorizationAbsent,
    MainnetAuthorizationAbsent,
    SecondTraceAbsent,
    ExecutableProviderOperationPathAbsent,
    OutputRedacted,
}

enum class SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportFailureLabel {
    AdmissionGateMissing,
    SyntheticTraceMissing,
    SyntheticTraceNotCreated,
    SyntheticTraceValidationMissing,
    SyntheticTraceValidationFailed,
    PriorNoopExecutionBoundarySuiteMissing,
    UpstreamTraceUnsafe,
    PayloadOrMaterialPresent,
    RuntimeReachabilityPresent,
    ExecutionPresent,
    AuthorizationPresent,
    ProviderSelectionNotDisabledOnly,
    SecondTraceCreated,
    ExecutableProviderOperationPathPresent,
}

enum class SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportBlocker {
    SuiteReportFailed,
    UpstreamEvidenceUnsafe,
    RuntimeReachabilityPresent,
    ExecutionPresent,
    AuthorizationPresent,
}

enum class SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportWarning {
    None,
}

data class SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReport(
    val suiteReportId:
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportSafeLabel,
    val chainName: SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportChain,
    val sourceSet:
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportSourceSet,
    val admissionGatePresent: Boolean,
    val syntheticTracePresent: Boolean,
    val syntheticTraceCreated: Boolean,
    val syntheticTraceValidationPresent: Boolean,
    val syntheticTraceValidationPassed: Boolean,
    val priorNoopExecutionBoundarySuitePresent: Boolean,
    val upstreamEvidenceCount: Int,
    val validationCheckCount: Int,
    val suiteCheckCount: Int,
    val safeLabelCount: Int,
    val enumEvidenceCount: Int,
    val booleanEvidenceCount: Int,
    val blockerCount: Int,
    val warningCount: Int,
    val suitePassed: Boolean,
    val syntheticTraceSuitePassed: Boolean,
    val suiteIsCommonTestOnly: Boolean,
    val suiteIsSuiteReportOnly: Boolean,
    val suiteCreatesTrace: Boolean,
    val suiteCreatesTracePayloads: Boolean,
    val suitePayloadFree: Boolean,
    val productionProviderSelectable: Boolean,
    val disabledProviderOnly: Boolean,
    val productionRuntimeReachable: Boolean,
    val tracePayloadPresent: Boolean,
    val providerOperationPayloadPresent: Boolean,
    val providerOperationExecuted: Boolean,
    val cryptoExecuted: Boolean,
    val katRunnerPresent: Boolean,
    val katExecutorPresent: Boolean,
    val providerKatExecutorPresent: Boolean,
    val providerImplementationPresent: Boolean,
    val registryEntryPresent: Boolean,
    val factoryEntryPresent: Boolean,
    val dispatcherEntryPresent: Boolean,
    val executorTargetPresent: Boolean,
    val providerKatExecutorReachable: Boolean,
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
    val vaultLifecyclePresent: Boolean,
    val vaultPersistencePresent: Boolean,
    val productionSyncPresent: Boolean,
    val signingBroadcastingPresent: Boolean,
    val uiPresent: Boolean,
    val endpointPresent: Boolean,
    val mainnetPresent: Boolean,
    val secondTraceCreated: Boolean,
    val executableProviderOperationPathPresent: Boolean,
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
    val suiteChecks:
        List<SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportCheck>,
    val failureLabels:
        List<SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportFailureLabel>,
    val blockers:
        List<SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportBlocker>,
    val warnings:
        List<SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportWarning>,
    val displayLabel:
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportSafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReport(REDACTED, PAYLOAD_FREE, COMMON_TEST_ONLY, DISABLED_PROVIDER_ONLY, NOT_AUTHORIZATION, SUITE_EVIDENCE_ONLY)"
}

object SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportPolicy {
    private val currentSuiteReport:
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReport by lazy {
            buildProviderOperationSyntheticTraceSuiteReport()
        }

    fun currentProviderOperationSyntheticTraceSuiteReport():
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReport =
        currentSuiteReport

    private fun buildProviderOperationSyntheticTraceSuiteReport():
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReport {
        val admission =
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceAdmissionPolicy
                .currentProviderOperationSyntheticTraceAdmission()
        val trace =
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTracePolicy
                .currentProviderOperationSyntheticTrace()
        val validation =
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationPolicy
                .currentProviderOperationSyntheticTraceValidationReport()
        val noopExecutionBoundarySuite =
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundarySuiteReportPolicy
                .currentProviderOperationNoopExecutionBoundarySuiteReport()
        val suiteChecks =
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportCheck
                .entries
                .toList()

        val admissionGatePresent =
            admission.futureSyntheticTraceCriteriaModeled &&
                !admission.futureSyntheticTraceCriteriaAuthorizeCurrentTrace &&
                !admission.currentSyntheticTracePresent &&
                !admission.currentTracePayloadPresent
        val syntheticTracePresent = true
        val syntheticTraceCreated = trace.syntheticTraceCreated
        val syntheticTraceValidationPresent = true
        val syntheticTraceValidationPassed =
            validation.syntheticTraceValidationPassed &&
                validation.validationPassed &&
                validation.sourceTracePresent &&
                validation.sourceTraceCreated
        val priorNoopExecutionBoundarySuitePresent =
            noopExecutionBoundarySuite.noopExecutionBoundarySuitePassed &&
                admission.noopExecutionBoundarySuitePassed &&
                trace.noopExecutionBoundarySuitePassed

        val traceCommonTestOnly =
            trace.traceIsCommonTestOnly &&
                trace.sourceSet ==
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSourceSet.CommonTest
        val validationCommonTestOnly =
            validation.validationIsCommonTestOnly &&
                validation.sourceSet ==
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceValidationSourceSet.CommonTest
        val suiteIsCommonTestOnly =
            admission.admissionIsCommonTestOnly &&
                traceCommonTestOnly &&
                validationCommonTestOnly &&
                noopExecutionBoundarySuite.suiteIsCommonTestOnly
        val suiteIsSuiteReportOnly = true
        val suiteCreatesTrace = false
        val suiteCreatesTracePayloads = false

        val tracePayloadPresent =
            admission.currentTracePayloadPresent ||
                trace.syntheticTracePayloadPresent ||
                validation.tracePayloadPresent
        val providerOperationPayloadPresent = validation.providerOperationPayloadPresent
        val rawKatMaterialPresent =
            admission.rawKatMaterialPresent ||
                trace.rawKatMaterialPresent ||
                validation.rawKatMaterialPresent ||
                noopExecutionBoundarySuite.rawKatMaterialPresent
        val vectorBytesPresent =
            admission.rawVectorBytesPresent ||
                trace.rawVectorBytesPresent ||
                validation.vectorBytesPresent ||
                noopExecutionBoundarySuite.rawVectorBytesPresent
        val vectorHexPresent =
            admission.rawVectorHexPresent ||
                trace.rawVectorHexPresent ||
                validation.vectorHexPresent ||
                noopExecutionBoundarySuite.rawVectorHexPresent
        val publicVectorBytesPresent =
            admission.publicVectorBytesPresent ||
                trace.publicVectorBytesPresent ||
                validation.publicVectorBytesPresent ||
                noopExecutionBoundarySuite.publicVectorBytesPresent
        val publicVectorHexPresent =
            admission.publicVectorHexPresent ||
                trace.publicVectorHexPresent ||
                validation.publicVectorHexPresent ||
                noopExecutionBoundarySuite.publicVectorHexPresent
        val providerHandlePresent =
            trace.syntheticTraceContainsProviderHandles ||
                validation.providerHandlePresent
        val sourceLocationPresent =
            trace.syntheticTraceContainsSourceLocation ||
                validation.sourceLocationPresent
        val stackTracePresent = validation.stackTracePresent
        val diagnosticsPayloadPresent =
            trace.syntheticTraceContainsDiagnosticsPayload ||
                validation.diagnosticsPayloadPresent
        val analyticsPayloadPresent =
            trace.syntheticTraceContainsAnalyticsPayload ||
                validation.analyticsPayloadPresent
        val crashReportPayloadPresent =
            trace.syntheticTraceContainsCrashReportPayload ||
                validation.crashReportPayloadPresent
        val supportExportPayloadPresent =
            trace.syntheticTraceContainsSupportExportPayload ||
                validation.supportExportPayloadPresent

        val syntheticTracePayloadFree =
            trace.syntheticTracePayloadFree &&
                validation.sourceTracePayloadFree &&
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
                !supportExportPayloadPresent
        val validationPayloadFree =
            validation.sourceTracePayloadFree &&
                !validation.validationCreatesTracePayloads &&
                !validation.tracePayloadPresent &&
                !validation.providerOperationPayloadPresent
        val suitePayloadFree = syntheticTracePayloadFree && validationPayloadFree && !suiteCreatesTracePayloads

        val providerOperationExecuted =
            validation.providerOperationExecuted ||
                trace.providerOperationExecutionPresent ||
                trace.traceReportsProviderOperationExecution ||
                trace.realProviderOperationExecutionPermitted ||
                admission.currentNoopProviderOperationExecutionPresent ||
                admission.currentRealProviderOperationExecutionPresent ||
                noopExecutionBoundarySuite.providerOperationExecutionPresent
        val cryptoExecuted =
            validation.cryptoExecuted ||
                trace.cryptoExecutionPresent ||
                trace.traceReportsCryptoOperation ||
                trace.cryptoExecutionPermitted ||
                admission.currentCryptoExecutionPresent ||
                noopExecutionBoundarySuite.cryptoExecutionPresent
        val katRunnerPresent =
            validation.katRunnerPresent ||
                trace.providerOperationKatRunnerPresent ||
                trace.katRunnerPermitted ||
                admission.currentKatRunnerPresent ||
                noopExecutionBoundarySuite.providerOperationKatRunnerPresent
        val katExecutorPresent =
            validation.katExecutorPresent ||
                trace.providerOperationKatExecutorPresent ||
                trace.katExecutorPermitted ||
                admission.currentKatExecutorPresent ||
                noopExecutionBoundarySuite.providerOperationKatExecutorPresent
        val providerKatExecutorPresent =
            validation.providerKatExecutorPresent ||
                trace.providerOperationKatExecutorPresent ||
                noopExecutionBoundarySuite.providerOperationKatExecutorPresent
        val providerImplementationPresent =
            validation.providerImplementationPresent ||
                trace.implementsVaultCryptoProvider ||
                trace.containsVaultCryptoProvider ||
                admission.implementsVaultCryptoProvider ||
                admission.containsVaultCryptoProvider ||
                noopExecutionBoundarySuite.implementsVaultCryptoProvider ||
                noopExecutionBoundarySuite.containsVaultCryptoProvider
        val registryEntryPresent =
            validation.registryReachable ||
                trace.registrySelectable ||
                admission.registrySelectable ||
                noopExecutionBoundarySuite.registrySelectable
        val factoryEntryPresent =
            validation.factoryReachable ||
                trace.factoryReachable ||
                admission.factoryReachable ||
                noopExecutionBoundarySuite.factoryReachable
        val dispatcherEntryPresent =
            validation.dispatcherReachable ||
                trace.dispatcherReachable ||
                admission.dispatcherReachable ||
                noopExecutionBoundarySuite.dispatcherReachable
        val executorTargetPresent =
            validation.executorTargetReachable ||
                trace.executorTargetable ||
                admission.executorTargetable ||
                noopExecutionBoundarySuite.executorTargetable
        val providerKatExecutorReachable =
            validation.providerKatExecutorReachable ||
                trace.providerKatExecutorReachable ||
                admission.providerKatExecutorReachable ||
                noopExecutionBoundarySuite.providerKatExecutorReachable
        val vaultLifecyclePresent =
            validation.vaultLifecyclePresent ||
                trace.traceReportsVaultLifecycle ||
                trace.vaultLifecycleReachable ||
                trace.canUseForVaultLifecycle ||
                admission.vaultLifecycleReachable ||
                noopExecutionBoundarySuite.vaultLifecycleReachable
        val vaultPersistencePresent =
            validation.vaultPersistencePresent ||
                trace.traceReportsPersistence ||
                trace.vaultPersistencePermitted ||
                trace.persistenceReachable ||
                trace.canUseForPersistence ||
                admission.vaultPersistencePermitted ||
                noopExecutionBoundarySuite.vaultPersistencePermitted ||
                noopExecutionBoundarySuite.persistenceReachable
        val productionSyncPresent =
            validation.syncPresent ||
                trace.productionSyncReachable ||
                trace.canUseForSync ||
                admission.productionSyncReachable ||
                noopExecutionBoundarySuite.productionSyncReachable
        val signingBroadcastingPresent =
            validation.signingBroadcastingPresent ||
                trace.signingBroadcastingReachable ||
                trace.canUseForSigning ||
                trace.canUseForBroadcasting ||
                admission.signingBroadcastingReachable ||
                noopExecutionBoundarySuite.signingBroadcastingReachable
        val uiPresent =
            validation.uiPresent ||
                trace.uiSurfaceReachable ||
                admission.uiSurfaceReachable ||
                noopExecutionBoundarySuite.uiSurfaceReachable
        val endpointPresent =
            validation.endpointPresent ||
                trace.publicEndpointReachable ||
                trace.syntheticTraceContainsEndpointMaterial ||
                admission.publicEndpointReachable ||
                noopExecutionBoundarySuite.publicEndpointReachable
        val mainnetPresent =
            validation.mainnetPresent ||
                trace.mainnetReachable ||
                trace.mainnetPermitted ||
                trace.canUseForMainnet ||
                admission.mainnetReachable ||
                noopExecutionBoundarySuite.mainnetReachable

        val productionProviderSelectable =
            validation.productionProviderSelectable ||
                trace.productionProviderSelectable ||
                admission.productionProviderSelectable ||
                noopExecutionBoundarySuite.productionProviderSelectable
        val disabledProviderOnly =
            validation.disabledProviderOnly &&
                !productionProviderSelectable &&
                !trace.runtimeSelectable &&
                !admission.runtimeSelectable &&
                !noopExecutionBoundarySuite.runtimeSelectable
        val productionRuntimeReachable =
            validation.productionRuntimeReachable ||
                trace.runtimeSelectable ||
                admission.runtimeSelectable ||
                noopExecutionBoundarySuite.runtimeSelectable ||
                registryEntryPresent ||
                factoryEntryPresent ||
                dispatcherEntryPresent ||
                executorTargetPresent ||
                providerKatExecutorReachable ||
                productionSyncPresent ||
                uiPresent ||
                endpointPresent ||
                mainnetPresent

        val productionAuthorizationPresent =
            validation.productionAuthorizationPresent ||
                trace.traceIsProductionAuthorization ||
                admission.admissionIsProductionAuthorization ||
                noopExecutionBoundarySuite.suiteIsProductionAuthorization
        val providerSelectionAuthorizationPresent =
            validation.providerSelectionAuthorizationPresent ||
                trace.traceIsProviderSelectionAuthorization ||
                admission.admissionIsProviderSelectionAuthorization ||
                noopExecutionBoundarySuite.suiteIsProviderSelectionAuthorization
        val providerOperationAuthorizationPresent =
            validation.providerOperationAuthorizationPresent ||
                trace.traceIsProviderOperationAuthorization ||
                admission.admissionIsProviderOperationAuthorization ||
                noopExecutionBoundarySuite.suiteIsProviderOperationAuthorization
        val cryptoAuthorizationPresent =
            validation.cryptoAuthorizationPresent ||
                trace.traceIsCryptoAuthorization ||
                admission.admissionIsCryptoAuthorization ||
                noopExecutionBoundarySuite.suiteIsCryptoAuthorization
        val katRunnerAuthorizationPresent =
            validation.katRunnerAuthorizationPresent ||
                trace.katRunnerPermitted ||
                admission.katRunnerPermitted ||
                noopExecutionBoundarySuite.katRunnerPermitted
        val katExecutorAuthorizationPresent =
            validation.katExecutorAuthorizationPresent ||
                trace.traceIsKatExecutorAuthorization ||
                trace.katExecutorPermitted ||
                admission.admissionIsKatExecutorAuthorization ||
                admission.katExecutorPermitted ||
                noopExecutionBoundarySuite.suiteIsKatExecutorAuthorization ||
                noopExecutionBoundarySuite.katExecutorPermitted
        val providerKatExecutorAuthorizationPresent =
            validation.providerKatExecutorAuthorizationPresent ||
                providerKatExecutorPresent ||
                providerKatExecutorReachable
        val vaultPersistenceAuthorizationPresent =
            validation.vaultPersistenceAuthorizationPresent ||
                trace.traceIsVaultPersistenceAuthorization ||
                admission.admissionIsVaultPersistenceAuthorization ||
                noopExecutionBoundarySuite.suiteIsVaultPersistenceAuthorization ||
                vaultPersistencePresent
        val syncAuthorizationPresent = validation.syncAuthorizationPresent || productionSyncPresent
        val signingBroadcastingAuthorizationPresent =
            validation.signingBroadcastingAuthorizationPresent ||
                signingBroadcastingPresent
        val uiAuthorizationPresent = validation.uiAuthorizationPresent || uiPresent
        val endpointAuthorizationPresent = validation.endpointAuthorizationPresent || endpointPresent
        val mainnetAuthorizationPresent =
            validation.mainnetAuthorizationPresent ||
                trace.traceIsMainnetAuthorization ||
                admission.admissionIsMainnetAuthorization ||
                noopExecutionBoundarySuite.suiteIsMainnetAuthorization ||
                mainnetPresent

        val secondTraceCreated = false
        val executableProviderOperationPathPresent =
            providerOperationExecuted ||
                trace.providerOperationReachable ||
                admission.providerOperationReachable ||
                noopExecutionBoundarySuite.providerOperationReachable
        val outputsAreRedacted =
            listOf(
                admission.toString(),
                admission.displayLabel.toString(),
                trace.toString(),
                trace.displayLabel.toString(),
                validation.toString(),
                validation.displayLabel.toString(),
                validation.validationId.toString(),
                noopExecutionBoundarySuite.toString(),
                noopExecutionBoundarySuite.displayLabel.toString(),
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportSafeLabel(
                    "PAYLOAD_FREE_SYNTHETIC_TRACE_SUITE_REPORT_COMMON_TEST_ONLY",
                ).toString(),
            ).all(::outputIsRedacted)

        val failureLabels =
            mutableListOf<
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportFailureLabel
            >()
        if (!admissionGatePresent) {
            failureLabels +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportFailureLabel
                    .AdmissionGateMissing
        }
        if (!syntheticTracePresent) {
            failureLabels +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportFailureLabel
                    .SyntheticTraceMissing
        }
        if (!syntheticTraceCreated) {
            failureLabels +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportFailureLabel
                    .SyntheticTraceNotCreated
        }
        if (!syntheticTraceValidationPresent) {
            failureLabels +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportFailureLabel
                    .SyntheticTraceValidationMissing
        }
        if (!syntheticTraceValidationPassed) {
            failureLabels +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportFailureLabel
                    .SyntheticTraceValidationFailed
        }
        if (!priorNoopExecutionBoundarySuitePresent) {
            failureLabels +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportFailureLabel
                    .PriorNoopExecutionBoundarySuiteMissing
        }
        if (!syntheticTracePayloadFree || !validationPayloadFree || !outputsAreRedacted) {
            failureLabels +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportFailureLabel
                    .UpstreamTraceUnsafe
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
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportFailureLabel
                    .PayloadOrMaterialPresent
        }
        if (
            productionRuntimeReachable ||
            vaultLifecyclePresent ||
            vaultPersistencePresent ||
            productionSyncPresent ||
            uiPresent ||
            endpointPresent ||
            mainnetPresent
        ) {
            failureLabels +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportFailureLabel
                    .RuntimeReachabilityPresent
        }
        if (providerOperationExecuted || cryptoExecuted || katRunnerPresent || katExecutorPresent) {
            failureLabels +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportFailureLabel
                    .ExecutionPresent
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
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportFailureLabel
                    .AuthorizationPresent
        }
        if (!disabledProviderOnly) {
            failureLabels +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportFailureLabel
                    .ProviderSelectionNotDisabledOnly
        }
        if (secondTraceCreated) {
            failureLabels +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportFailureLabel
                    .SecondTraceCreated
        }
        if (executableProviderOperationPathPresent) {
            failureLabels +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportFailureLabel
                    .ExecutableProviderOperationPathPresent
        }

        val blockers =
            mutableListOf<SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportBlocker>()
        if (failureLabels.isNotEmpty()) {
            blockers +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportBlocker
                    .SuiteReportFailed
        }
        if (!syntheticTracePayloadFree || !validationPayloadFree || !suitePayloadFree || !outputsAreRedacted) {
            blockers +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportBlocker
                    .UpstreamEvidenceUnsafe
        }
        if (productionRuntimeReachable) {
            blockers +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportBlocker
                    .RuntimeReachabilityPresent
        }
        if (providerOperationExecuted || cryptoExecuted || katRunnerPresent || katExecutorPresent) {
            blockers +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportBlocker
                    .ExecutionPresent
        }
        if (
            productionAuthorizationPresent ||
            providerSelectionAuthorizationPresent ||
            providerOperationAuthorizationPresent ||
            cryptoAuthorizationPresent ||
            katRunnerAuthorizationPresent ||
            katExecutorAuthorizationPresent ||
            vaultPersistenceAuthorizationPresent ||
            mainnetAuthorizationPresent
        ) {
            blockers +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportBlocker
                    .AuthorizationPresent
        }

        val upstreamEvidenceCount =
            listOf(
                admissionGatePresent,
                syntheticTracePresent,
                syntheticTraceCreated,
                syntheticTraceValidationPresent,
                syntheticTraceValidationPassed,
                priorNoopExecutionBoundarySuitePresent,
            ).count { present -> present }
        val warningCount = 0
        val suitePassed =
            admissionGatePresent &&
                syntheticTracePresent &&
                syntheticTraceCreated &&
                syntheticTraceValidationPresent &&
                syntheticTraceValidationPassed &&
                priorNoopExecutionBoundarySuitePresent &&
                suiteIsCommonTestOnly &&
                suiteIsSuiteReportOnly &&
                suitePayloadFree &&
                syntheticTracePayloadFree &&
                validationPayloadFree &&
                disabledProviderOnly &&
                !suiteCreatesTrace &&
                !suiteCreatesTracePayloads &&
                !productionProviderSelectable &&
                !productionRuntimeReachable &&
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
                !providerOperationExecuted &&
                !cryptoExecuted &&
                !katRunnerPresent &&
                !katExecutorPresent &&
                !providerKatExecutorPresent &&
                !providerImplementationPresent &&
                !registryEntryPresent &&
                !factoryEntryPresent &&
                !dispatcherEntryPresent &&
                !executorTargetPresent &&
                !providerKatExecutorReachable &&
                !vaultLifecyclePresent &&
                !vaultPersistencePresent &&
                !productionSyncPresent &&
                !signingBroadcastingPresent &&
                !uiPresent &&
                !endpointPresent &&
                !mainnetPresent &&
                !secondTraceCreated &&
                !executableProviderOperationPathPresent &&
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
                outputsAreRedacted &&
                failureLabels.isEmpty() &&
                blockers.isEmpty()

        return SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReport(
            suiteReportId =
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportSafeLabel(
                    "PAYLOAD_FREE_SYNTHETIC_TRACE_SUITE_REPORT_COMMON_TEST_ONLY",
                ),
            chainName =
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportChain
                    .PayloadFreeSyntheticProviderOperationTrace,
            sourceSet =
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportSourceSet.CommonTest,
            admissionGatePresent = admissionGatePresent,
            syntheticTracePresent = syntheticTracePresent,
            syntheticTraceCreated = syntheticTraceCreated,
            syntheticTraceValidationPresent = syntheticTraceValidationPresent,
            syntheticTraceValidationPassed = syntheticTraceValidationPassed,
            priorNoopExecutionBoundarySuitePresent = priorNoopExecutionBoundarySuitePresent,
            upstreamEvidenceCount = upstreamEvidenceCount,
            validationCheckCount = validation.validationCheckCount,
            suiteCheckCount = suiteChecks.size,
            safeLabelCount = 3,
            enumEvidenceCount = suiteChecks.size + trace.traceEvents.size + validation.validationChecks.size + 1,
            booleanEvidenceCount = validation.booleanEvidenceCount + 80,
            blockerCount = blockers.size,
            warningCount = warningCount,
            suitePassed = suitePassed,
            syntheticTraceSuitePassed = suitePassed,
            suiteIsCommonTestOnly = suiteIsCommonTestOnly,
            suiteIsSuiteReportOnly = suiteIsSuiteReportOnly,
            suiteCreatesTrace = suiteCreatesTrace,
            suiteCreatesTracePayloads = suiteCreatesTracePayloads,
            suitePayloadFree = suitePayloadFree,
            productionProviderSelectable = productionProviderSelectable,
            disabledProviderOnly = disabledProviderOnly,
            productionRuntimeReachable = productionRuntimeReachable,
            tracePayloadPresent = tracePayloadPresent,
            providerOperationPayloadPresent = providerOperationPayloadPresent,
            providerOperationExecuted = providerOperationExecuted,
            cryptoExecuted = cryptoExecuted,
            katRunnerPresent = katRunnerPresent,
            katExecutorPresent = katExecutorPresent,
            providerKatExecutorPresent = providerKatExecutorPresent,
            providerImplementationPresent = providerImplementationPresent,
            registryEntryPresent = registryEntryPresent,
            factoryEntryPresent = factoryEntryPresent,
            dispatcherEntryPresent = dispatcherEntryPresent,
            executorTargetPresent = executorTargetPresent,
            providerKatExecutorReachable = providerKatExecutorReachable,
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
            vaultLifecyclePresent = vaultLifecyclePresent,
            vaultPersistencePresent = vaultPersistencePresent,
            productionSyncPresent = productionSyncPresent,
            signingBroadcastingPresent = signingBroadcastingPresent,
            uiPresent = uiPresent,
            endpointPresent = endpointPresent,
            mainnetPresent = mainnetPresent,
            secondTraceCreated = secondTraceCreated,
            executableProviderOperationPathPresent = executableProviderOperationPathPresent,
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
            suiteChecks = suiteChecks,
            failureLabels = failureLabels,
            blockers = blockers,
            warnings = emptyList(),
            displayLabel =
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportSafeLabel(
                    "PAYLOAD_FREE_SYNTHETIC_TRACE_SUITE_REPORT",
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
