package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditSafeLabel(
    val value: String,
) {
    override fun toString(): String =
        "RedactedTestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditSafeLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditSourceSet {
    CommonTest,
}

enum class SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditChain {
    PayloadFreeSyntheticProviderOperationTrace,
}

enum class SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditCheck {
    AdmissionGatePresent,
    SyntheticTracePresent,
    SyntheticTraceCreated,
    ValidationReportPresent,
    ValidationPassed,
    SuiteReportPresent,
    SuiteReportPassed,
    PriorNoopExecutionBoundarySuitePresent,
    ChainComplete,
    CommonTestOnly,
    PayloadFree,
    Redacted,
    SafeLabelEnumCountBooleanEvidenceOnly,
    TracePayloadAbsent,
    SecondTraceAbsent,
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
}

enum class SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditFailureLabel {
    AdmissionGateMissing,
    SyntheticTraceMissing,
    SyntheticTraceNotCreated,
    ValidationReportMissing,
    ValidationFailed,
    SuiteReportMissing,
    SuiteReportFailed,
    PriorNoopExecutionBoundarySuiteMissing,
    ChainIncomplete,
    PayloadOrMaterialPresent,
    RuntimeReachabilityPresent,
    ExecutionPresent,
    AuthorizationPresent,
    ProviderSelectionNotDisabledOnly,
    SecondTraceCreated,
    OutputNotRedacted,
}

enum class SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditBlocker {
    CompletionAuditFailed,
    UpstreamEvidenceUnsafe,
    RuntimeReachabilityPresent,
    ExecutionPresent,
    AuthorizationPresent,
}

enum class SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditWarning {
    None,
}

data class SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAudit(
    val auditId:
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditSafeLabel,
    val chainName:
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditChain,
    val sourceSet:
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditSourceSet,
    val admissionGatePresent: Boolean,
    val syntheticTracePresent: Boolean,
    val syntheticTraceCreated: Boolean,
    val validationReportPresent: Boolean,
    val validationPassed: Boolean,
    val suiteReportPresent: Boolean,
    val suiteReportPassed: Boolean,
    val priorNoopExecutionBoundarySuitePresent: Boolean,
    val chainComplete: Boolean,
    val completionAuditPassed: Boolean,
    val syntheticTraceCompletionAuditPassed: Boolean,
    val upstreamEvidenceCount: Int,
    val auditCheckCount: Int,
    val safeLabelCount: Int,
    val enumEvidenceCount: Int,
    val booleanEvidenceCount: Int,
    val warningCount: Int,
    val blockerCount: Int,
    val auditIsCommonTestOnly: Boolean,
    val auditIsAuditOnly: Boolean,
    val auditCreatesTrace: Boolean,
    val auditCreatesTracePayloads: Boolean,
    val auditPayloadFree: Boolean,
    val auditRedacted: Boolean,
    val safeLabelEnumCountBooleanEvidenceOnly: Boolean,
    val productionProviderSelectable: Boolean,
    val disabledProviderOnly: Boolean,
    val productionRuntimeReachable: Boolean,
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
    val secondTraceCreated: Boolean,
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
    val vaultLifecyclePresent: Boolean,
    val vaultPersistencePresent: Boolean,
    val productionSyncPresent: Boolean,
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
    val auditChecks:
        List<SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditCheck>,
    val failureLabels:
        List<SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditFailureLabel>,
    val blockers:
        List<SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditBlocker>,
    val warnings:
        List<SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditWarning>,
    val displayLabel:
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditSafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAudit(REDACTED, PAYLOAD_FREE, COMMON_TEST_ONLY, DISABLED_PROVIDER_ONLY, NOT_AUTHORIZATION, AUDIT_EVIDENCE_ONLY)"
}

object SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditPolicy {
    private val currentAudit:
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAudit by lazy {
            buildProviderOperationSyntheticTraceCompletionAudit()
        }

    fun currentProviderOperationSyntheticTraceCompletionAudit():
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAudit =
        currentAudit

    private fun buildProviderOperationSyntheticTraceCompletionAudit():
        SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAudit {
        val suite =
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceSuiteReportPolicy
                .currentProviderOperationSyntheticTraceSuiteReport()
        val noopExecutionBoundarySuite =
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundarySuiteReportPolicy
                .currentProviderOperationNoopExecutionBoundarySuiteReport()
        val auditChecks =
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditCheck
                .entries
                .toList()

        val admissionGatePresent =
            suite.admissionGatePresent
        val syntheticTracePresent = suite.syntheticTracePresent
        val syntheticTraceCreated = suite.syntheticTraceCreated
        val validationReportPresent = suite.syntheticTraceValidationPresent
        val validationPassed =
            suite.syntheticTraceValidationPassed
        val suiteReportPresent = true
        val suiteReportPassed = suite.syntheticTraceSuitePassed && suite.suitePassed
        val priorNoopExecutionBoundarySuitePresent =
            suite.priorNoopExecutionBoundarySuitePresent &&
                noopExecutionBoundarySuite.noopExecutionBoundarySuitePassed

        val auditIsCommonTestOnly =
            suite.suiteIsCommonTestOnly &&
                noopExecutionBoundarySuite.suiteIsCommonTestOnly
        val auditIsAuditOnly = true
        val auditCreatesTrace = false
        val auditCreatesTracePayloads = false

        val tracePayloadPresent =
            suite.tracePayloadPresent
        val providerOperationPayloadPresent =
            suite.providerOperationPayloadPresent
        val rawKatMaterialPresent =
            suite.rawKatMaterialPresent ||
                noopExecutionBoundarySuite.rawKatMaterialPresent
        val vectorBytesPresent =
            suite.vectorBytesPresent ||
                noopExecutionBoundarySuite.rawVectorBytesPresent
        val vectorHexPresent =
            suite.vectorHexPresent ||
                noopExecutionBoundarySuite.rawVectorHexPresent
        val publicVectorBytesPresent =
            suite.publicVectorBytesPresent ||
                noopExecutionBoundarySuite.publicVectorBytesPresent
        val publicVectorHexPresent =
            suite.publicVectorHexPresent ||
                noopExecutionBoundarySuite.publicVectorHexPresent
        val providerHandlePresent = suite.providerHandlePresent
        val sourceLocationPresent = suite.sourceLocationPresent
        val stackTracePresent = suite.stackTracePresent
        val diagnosticsPayloadPresent = suite.diagnosticsPayloadPresent
        val analyticsPayloadPresent = suite.analyticsPayloadPresent
        val crashReportPayloadPresent = suite.crashReportPayloadPresent
        val supportExportPayloadPresent = suite.supportExportPayloadPresent
        val secondTraceCreated =
            suite.secondTraceCreated ||
                suite.suiteCreatesTrace ||
                auditCreatesTrace

        val providerOperationExecuted = suite.providerOperationExecuted
        val cryptoExecuted = suite.cryptoExecuted
        val katRunnerPresent = suite.katRunnerPresent
        val katExecutorPresent = suite.katExecutorPresent
        val providerKatExecutorPresent = suite.providerKatExecutorPresent
        val providerImplementationPresent =
            suite.providerImplementationPresent ||
                noopExecutionBoundarySuite.implementsVaultCryptoProvider ||
                noopExecutionBoundarySuite.containsVaultCryptoProvider
        val registryEntryPresent =
            suite.registryEntryPresent ||
                noopExecutionBoundarySuite.registrySelectable
        val factoryEntryPresent =
            suite.factoryEntryPresent ||
                noopExecutionBoundarySuite.factoryReachable
        val dispatcherEntryPresent =
            suite.dispatcherEntryPresent ||
                noopExecutionBoundarySuite.dispatcherReachable
        val executorTargetPresent =
            suite.executorTargetPresent ||
                noopExecutionBoundarySuite.executorTargetable
        val providerKatExecutorReachable =
            suite.providerKatExecutorReachable ||
                noopExecutionBoundarySuite.providerKatExecutorReachable
        val vaultLifecyclePresent =
            suite.vaultLifecyclePresent ||
                noopExecutionBoundarySuite.vaultLifecycleReachable
        val vaultPersistencePresent =
            suite.vaultPersistencePresent ||
                noopExecutionBoundarySuite.vaultPersistencePermitted ||
                noopExecutionBoundarySuite.persistenceReachable
        val productionSyncPresent =
            suite.productionSyncPresent ||
                noopExecutionBoundarySuite.productionSyncReachable
        val signingBroadcastingPresent =
            suite.signingBroadcastingPresent ||
                noopExecutionBoundarySuite.signingBroadcastingReachable
        val uiPresent =
            suite.uiPresent ||
                noopExecutionBoundarySuite.uiSurfaceReachable
        val endpointPresent =
            suite.endpointPresent ||
                noopExecutionBoundarySuite.publicEndpointReachable
        val mainnetPresent =
            suite.mainnetPresent ||
                noopExecutionBoundarySuite.mainnetReachable

        val productionProviderSelectable =
            suite.productionProviderSelectable ||
                noopExecutionBoundarySuite.productionProviderSelectable
        val disabledProviderOnly =
            suite.disabledProviderOnly &&
                !productionProviderSelectable &&
                !noopExecutionBoundarySuite.runtimeSelectable
        val productionRuntimeReachable =
            suite.productionRuntimeReachable ||
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
            suite.productionAuthorizationPresent ||
                noopExecutionBoundarySuite.suiteIsProductionAuthorization
        val providerSelectionAuthorizationPresent =
            suite.providerSelectionAuthorizationPresent ||
                noopExecutionBoundarySuite.suiteIsProviderSelectionAuthorization
        val providerOperationAuthorizationPresent =
            suite.providerOperationAuthorizationPresent ||
                noopExecutionBoundarySuite.suiteIsProviderOperationAuthorization
        val cryptoAuthorizationPresent =
            suite.cryptoAuthorizationPresent ||
                noopExecutionBoundarySuite.suiteIsCryptoAuthorization
        val katRunnerAuthorizationPresent =
            suite.katRunnerAuthorizationPresent ||
                noopExecutionBoundarySuite.katRunnerPermitted
        val katExecutorAuthorizationPresent =
            suite.katExecutorAuthorizationPresent ||
                noopExecutionBoundarySuite.suiteIsKatExecutorAuthorization ||
                noopExecutionBoundarySuite.katExecutorPermitted
        val providerKatExecutorAuthorizationPresent =
            suite.providerKatExecutorAuthorizationPresent ||
                providerKatExecutorPresent ||
                providerKatExecutorReachable
        val vaultPersistenceAuthorizationPresent =
            suite.vaultPersistenceAuthorizationPresent ||
                noopExecutionBoundarySuite.suiteIsVaultPersistenceAuthorization ||
                vaultPersistencePresent
        val syncAuthorizationPresent =
            suite.syncAuthorizationPresent ||
                productionSyncPresent
        val signingBroadcastingAuthorizationPresent =
            suite.signingBroadcastingAuthorizationPresent ||
                signingBroadcastingPresent
        val uiAuthorizationPresent =
            suite.uiAuthorizationPresent ||
                uiPresent
        val endpointAuthorizationPresent =
            suite.endpointAuthorizationPresent ||
                endpointPresent
        val mainnetAuthorizationPresent =
            suite.mainnetAuthorizationPresent ||
                noopExecutionBoundarySuite.suiteIsMainnetAuthorization ||
                mainnetPresent

        val auditPayloadFree =
            suite.suitePayloadFree &&
                !auditCreatesTracePayloads &&
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
        val auditRedacted =
            listOf(
                suite.toString(),
                suite.displayLabel.toString(),
                suite.suiteReportId.toString(),
                noopExecutionBoundarySuite.toString(),
                noopExecutionBoundarySuite.displayLabel.toString(),
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditSafeLabel(
                    "PAYLOAD_FREE_SYNTHETIC_TRACE_COMPLETION_AUDIT_COMMON_TEST_ONLY",
                ).toString(),
            ).all(::outputIsRedacted)
        val safeLabelEnumCountBooleanEvidenceOnly =
            auditRedacted &&
                auditChecks.isNotEmpty() &&
                suite.suiteCheckCount == suite.suiteChecks.size &&
                suite.validationCheckCount > 0 &&
                suite.enumEvidenceCount > 0

        val chainComplete =
            admissionGatePresent &&
                syntheticTracePresent &&
                syntheticTraceCreated &&
                validationReportPresent &&
                validationPassed &&
                suiteReportPresent &&
                suiteReportPassed &&
                priorNoopExecutionBoundarySuitePresent

        val failureLabels =
            mutableListOf<
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditFailureLabel
            >()
        if (!admissionGatePresent) {
            failureLabels +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditFailureLabel
                    .AdmissionGateMissing
        }
        if (!syntheticTracePresent) {
            failureLabels +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditFailureLabel
                    .SyntheticTraceMissing
        }
        if (!syntheticTraceCreated) {
            failureLabels +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditFailureLabel
                    .SyntheticTraceNotCreated
        }
        if (!validationReportPresent) {
            failureLabels +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditFailureLabel
                    .ValidationReportMissing
        }
        if (!validationPassed) {
            failureLabels +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditFailureLabel
                    .ValidationFailed
        }
        if (!suiteReportPresent) {
            failureLabels +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditFailureLabel
                    .SuiteReportMissing
        }
        if (!suiteReportPassed) {
            failureLabels +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditFailureLabel
                    .SuiteReportFailed
        }
        if (!priorNoopExecutionBoundarySuitePresent) {
            failureLabels +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditFailureLabel
                    .PriorNoopExecutionBoundarySuiteMissing
        }
        if (!chainComplete) {
            failureLabels +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditFailureLabel
                    .ChainIncomplete
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
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditFailureLabel
                    .PayloadOrMaterialPresent
        }
        if (
            productionRuntimeReachable ||
            registryEntryPresent ||
            factoryEntryPresent ||
            dispatcherEntryPresent ||
            executorTargetPresent ||
            providerKatExecutorReachable ||
            vaultLifecyclePresent ||
            vaultPersistencePresent ||
            productionSyncPresent ||
            signingBroadcastingPresent ||
            uiPresent ||
            endpointPresent ||
            mainnetPresent
        ) {
            failureLabels +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditFailureLabel
                    .RuntimeReachabilityPresent
        }
        if (providerOperationExecuted || cryptoExecuted || katRunnerPresent || katExecutorPresent) {
            failureLabels +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditFailureLabel
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
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditFailureLabel
                    .AuthorizationPresent
        }
        if (!disabledProviderOnly || productionProviderSelectable) {
            failureLabels +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditFailureLabel
                    .ProviderSelectionNotDisabledOnly
        }
        if (secondTraceCreated) {
            failureLabels +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditFailureLabel
                    .SecondTraceCreated
        }
        if (!auditRedacted) {
            failureLabels +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditFailureLabel
                    .OutputNotRedacted
        }

        val blockers =
            mutableListOf<SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditBlocker>()
        if (failureLabels.isNotEmpty()) {
            blockers +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditBlocker
                    .CompletionAuditFailed
        }
        if (!auditPayloadFree || !auditRedacted) {
            blockers +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditBlocker
                    .UpstreamEvidenceUnsafe
        }
        if (productionRuntimeReachable) {
            blockers +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditBlocker
                    .RuntimeReachabilityPresent
        }
        if (providerOperationExecuted || cryptoExecuted || katRunnerPresent || katExecutorPresent) {
            blockers +=
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditBlocker
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
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditBlocker
                    .AuthorizationPresent
        }

        val upstreamEvidenceCount =
            listOf(
                admissionGatePresent,
                syntheticTracePresent,
                syntheticTraceCreated,
                validationReportPresent,
                validationPassed,
                suiteReportPresent,
                suiteReportPassed,
                priorNoopExecutionBoundarySuitePresent,
            ).count { present -> present }
        val completionAuditPassed =
            chainComplete &&
                auditIsCommonTestOnly &&
                auditIsAuditOnly &&
                auditPayloadFree &&
                auditRedacted &&
                safeLabelEnumCountBooleanEvidenceOnly &&
                disabledProviderOnly &&
                !auditCreatesTrace &&
                !auditCreatesTracePayloads &&
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
                !secondTraceCreated &&
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

        return SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAudit(
            auditId =
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditSafeLabel(
                    "PAYLOAD_FREE_SYNTHETIC_TRACE_COMPLETION_AUDIT_COMMON_TEST_ONLY",
                ),
            chainName =
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditChain
                    .PayloadFreeSyntheticProviderOperationTrace,
            sourceSet =
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditSourceSet.CommonTest,
            admissionGatePresent = admissionGatePresent,
            syntheticTracePresent = syntheticTracePresent,
            syntheticTraceCreated = syntheticTraceCreated,
            validationReportPresent = validationReportPresent,
            validationPassed = validationPassed,
            suiteReportPresent = suiteReportPresent,
            suiteReportPassed = suiteReportPassed,
            priorNoopExecutionBoundarySuitePresent = priorNoopExecutionBoundarySuitePresent,
            chainComplete = chainComplete,
            completionAuditPassed = completionAuditPassed,
            syntheticTraceCompletionAuditPassed = completionAuditPassed,
            upstreamEvidenceCount = upstreamEvidenceCount,
            auditCheckCount = auditChecks.size,
            safeLabelCount = 3,
            enumEvidenceCount = auditChecks.size + suite.enumEvidenceCount + 1,
            booleanEvidenceCount = suite.booleanEvidenceCount + 90,
            warningCount = 0,
            blockerCount = blockers.size,
            auditIsCommonTestOnly = auditIsCommonTestOnly,
            auditIsAuditOnly = auditIsAuditOnly,
            auditCreatesTrace = auditCreatesTrace,
            auditCreatesTracePayloads = auditCreatesTracePayloads,
            auditPayloadFree = auditPayloadFree,
            auditRedacted = auditRedacted,
            safeLabelEnumCountBooleanEvidenceOnly = safeLabelEnumCountBooleanEvidenceOnly,
            productionProviderSelectable = productionProviderSelectable,
            disabledProviderOnly = disabledProviderOnly,
            productionRuntimeReachable = productionRuntimeReachable,
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
            secondTraceCreated = secondTraceCreated,
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
            vaultLifecyclePresent = vaultLifecyclePresent,
            vaultPersistencePresent = vaultPersistencePresent,
            productionSyncPresent = productionSyncPresent,
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
            auditChecks = auditChecks,
            failureLabels = failureLabels,
            blockers = blockers,
            warnings = emptyList(),
            displayLabel =
                SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditSafeLabel(
                    "PAYLOAD_FREE_SYNTHETIC_TRACE_COMPLETION_AUDIT",
                ),
        )
    }

    private fun outputIsRedacted(output: String): Boolean =
        "skald-test-only" !in output &&
            "trace payload" !in output.lowercase() &&
            "raw kat" !in output.lowercase() &&
            "vector bytes" !in output.lowercase() &&
            "vector hex" !in output.lowercase() &&
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
