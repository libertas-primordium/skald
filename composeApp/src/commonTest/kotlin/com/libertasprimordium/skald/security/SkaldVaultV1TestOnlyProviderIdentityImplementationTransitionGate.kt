package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGateSafeLabel(
    val value: String,
) {
    override fun toString(): String =
        "RedactedTestOnlyProviderIdentityImplementationTransitionGateSafeLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGateSourceSet {
    CommonTest,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGateChain {
    TestOnlyProviderIdentityImplementationPrerequisiteChain,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGateCheck {
    IdentityDecisionPresent,
    IsolationGuardPresent,
    SyntheticNamespacePresent,
    SourceSetConfinementPresent,
    ImplementationDecisionPresent,
    PrerequisiteAuditPresent,
    ScopeDecisionPresent,
    ImplementationContractPresent,
    ReadinessGatePresent,
    RuntimeLinkageGuardPresent,
    PromotionBlockersPresent,
    ImplementationAdmissionGatePresent,
    ImplementationPlanPresent,
    KatFixtureChainPresent,
    ProviderOperationMetadataChainPresent,
    ProviderOperationNoopKatChainPresent,
    ProviderOperationNoopExecutionBoundaryChainPresent,
    ProviderOperationSyntheticTraceChainPresent,
    SyntheticTraceCompletionAuditPresent,
    SyntheticTraceCompletionAuditPassed,
    SourceGuardCoveragePresent,
    ProductionRuntimeAbsenceProven,
    ReviewReadyForHumanDecision,
    ImplementationAuthorizationAbsent,
    ProviderImplementationAuthorizationAbsent,
    ProviderOperationExecutionAuthorizationAbsent,
    CryptoExecutionAuthorizationAbsent,
    KatExecutorAuthorizationAbsent,
    ProductionAuthorizationAbsent,
    ProviderSelectionAuthorizationAbsent,
    VaultPersistenceAuthorizationAbsent,
    SyncAuthorizationAbsent,
    SigningBroadcastingAuthorizationAbsent,
    UiAuthorizationAbsent,
    EndpointAuthorizationAbsent,
    MainnetAuthorizationAbsent,
    ProviderSelectionDisabledOnly,
    ProductionProviderSelectableFalse,
    ProviderRegistryFactoryDispatcherExecutorTargetAbsent,
    ProviderImplementationAbsent,
    ProviderOperationExecutionAbsent,
    CryptoExecutionAbsent,
    TracePayloadAbsent,
    RawKatMaterialAbsent,
    PublicVectorBytesAbsent,
    PublicVectorHexAbsent,
    VaultPersistenceAbsent,
    ProductionSyncAbsent,
    SigningBroadcastingAbsent,
    UiAbsent,
    EndpointAbsent,
    MainnetAbsent,
    SourceMaterialCorpusRulesPreserved,
    OutputRedacted,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGateFailureLabel {
    PrerequisiteEvidenceMissing,
    CompletionAuditMissing,
    CompletionAuditFailed,
    RuntimeReachabilityPresent,
    ExecutionPresent,
    AuthorizationPresent,
    MaterialPresent,
    ProviderSelectionNotDisabledOnly,
    ProductionProviderSelectable,
    OutputNotRedacted,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGateBlocker {
    HumanReviewRequiredBeforeImplementation,
    ImplementationAuthorizationMissing,
    ProviderImplementationAuthorizationMissing,
    ProviderOperationAuthorizationMissing,
    CryptoAuthorizationMissing,
    KatExecutorAuthorizationMissing,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGateWarning {
    None,
}

data class SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGate(
    val gateId: SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGateSafeLabel,
    val chainName: SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGateChain,
    val sourceSet: SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGateSourceSet,
    val transitionGateIsCommonTestOnly: Boolean,
    val transitionGateOnly: Boolean,
    val identityDecisionPresent: Boolean,
    val isolationGuardPresent: Boolean,
    val syntheticNamespacePresent: Boolean,
    val sourceSetConfinementPresent: Boolean,
    val implementationDecisionPresent: Boolean,
    val prerequisiteAuditPresent: Boolean,
    val scopeDecisionPresent: Boolean,
    val implementationContractPresent: Boolean,
    val readinessGatePresent: Boolean,
    val runtimeLinkageGuardPresent: Boolean,
    val promotionBlockersPresent: Boolean,
    val implementationAdmissionGatePresent: Boolean,
    val implementationPlanPresent: Boolean,
    val katFixtureChainPresent: Boolean,
    val providerOperationMetadataChainPresent: Boolean,
    val providerOperationNoopKatChainPresent: Boolean,
    val providerOperationNoopExecutionBoundaryChainPresent: Boolean,
    val providerOperationSyntheticTraceChainPresent: Boolean,
    val syntheticTraceCompletionAuditPresent: Boolean,
    val syntheticTraceCompletionAuditPassed: Boolean,
    val sourceGuardCoveragePresent: Boolean,
    val productionRuntimeAbsenceProven: Boolean,
    val sourceMaterialCorpusRulesPresent: Boolean,
    val buildHistoryExcludedFromNormalSourceMaterialCorpus: Boolean,
    val docsReadmeSeparateCorpus: Boolean,
    val reviewReadyForHumanDecision: Boolean,
    val reviewReadyIsHumanDecisionOnly: Boolean,
    val implementationAuthorized: Boolean,
    val providerImplementationAuthorized: Boolean,
    val providerOperationExecutionAuthorized: Boolean,
    val cryptoExecutionAuthorized: Boolean,
    val katExecutorAuthorized: Boolean,
    val mainnetAuthorized: Boolean,
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
    val productionProviderSelectable: Boolean,
    val disabledProviderOnly: Boolean,
    val providerRegistryEntryPresent: Boolean,
    val providerFactoryPresent: Boolean,
    val providerDispatcherPresent: Boolean,
    val executorTargetPresent: Boolean,
    val providerImplementationPresent: Boolean,
    val providerOperationExecuted: Boolean,
    val cryptoExecuted: Boolean,
    val katRunnerPresent: Boolean,
    val katExecutorPresent: Boolean,
    val providerKatExecutorPresent: Boolean,
    val tracePayloadPresent: Boolean,
    val rawKatMaterialPresent: Boolean,
    val publicVectorBytesPresent: Boolean,
    val publicVectorHexPresent: Boolean,
    val vaultLifecyclePresent: Boolean,
    val vaultPersistencePresent: Boolean,
    val productionSyncPresent: Boolean,
    val signingBroadcastingPresent: Boolean,
    val uiPresent: Boolean,
    val endpointPresent: Boolean,
    val mainnetPresent: Boolean,
    val evidenceCount: Int,
    val transitionCheckCount: Int,
    val safeLabelCount: Int,
    val enumEvidenceCount: Int,
    val booleanEvidenceCount: Int,
    val blockerCount: Int,
    val warningCount: Int,
    val transitionChecks: List<SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGateCheck>,
    val failureLabels: List<SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGateFailureLabel>,
    val blockers: List<SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGateBlocker>,
    val warnings: List<SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGateWarning>,
    val displayLabel: SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGateSafeLabel,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGate(REDACTED, COMMON_TEST_ONLY, HUMAN_REVIEW_READY, NOT_AUTHORIZATION, DISABLED_PROVIDER_ONLY, TRANSITION_GATE_ONLY)"
}

object SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGatePolicy {
    private val currentGate:
        SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGate by lazy {
            buildTransitionGate()
        }

    fun currentProviderIdentityImplementationTransitionGate():
        SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGate =
        currentGate

    private fun buildTransitionGate():
        SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGate {
        val identityDecision =
            SkaldVaultV1TestOnlyProviderIdentityDecisionPolicy.currentIdentityDecisionEvidence()
        val isolationGuard =
            SkaldVaultV1TestOnlyProviderIdentityIsolationGuardPolicy.currentIsolationEvidence()
        val namespace =
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespacePolicy.currentNamespaceEvidence()
        val sourceSetConfinement =
            SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementPolicy
                .currentSourceSetConfinementEvidence()
        val implementationDecision =
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionPolicy
                .currentImplementationDecisionEvidence()
        val prerequisiteAudit =
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditPolicy
                .currentPrerequisiteAuditEvidence()
        val scopeDecision =
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionPolicy
                .currentScopeDecisionEvidence()
        val implementationContract =
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractPolicy
                .currentImplementationContractEvidence()
        val readinessGate =
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGatePolicy
                .currentReadinessGateEvidence()
        val runtimeLinkageGuard =
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageGuardPolicy
                .currentRuntimeLinkageGuardEvidence()
        val promotionBlockers =
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersPolicy
                .currentPromotionBlockersEvidence()
        val sourceGuardCoverage =
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoveragePolicy
                .currentSourceGuardCoverageEvidence()
        val redactionGuard =
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardPolicy
                .currentRedactionGuardEvidence()
        val admissionGate =
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGatePolicy
                .currentAdmissionGateEvidence()
        val implementationPlan =
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanPolicy
                .currentImplementationPlanEvidence()
        val katFixtureScope =
            SkaldVaultV1TestOnlyProviderIdentityKatFixtureScopePolicy.currentKatFixtureScope()
        val katFixtureCatalog =
            SkaldVaultV1TestOnlyProviderIdentityKatFixtureCatalogPolicy.currentKatFixtureCatalog()
        val katFixtureValidation =
            SkaldVaultV1TestOnlyProviderIdentityKatFixtureValidationPolicy
                .currentKatFixtureValidationReport()
        val publicVectorAdmission =
            SkaldVaultV1TestOnlyProviderIdentityKatPublicVectorAdmissionPolicy
                .currentPublicVectorAdmission()
        val metadataSuite =
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationMetadataKatSuiteReportPolicy
                .currentProviderOperationMetadataKatSuiteReport()
        val noopKatSuite =
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopKatSuiteReportPolicy
                .currentProviderOperationNoopKatSuiteReport()
        val noopExecutionBoundarySuite =
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationNoopExecutionBoundarySuiteReportPolicy
                .currentProviderOperationNoopExecutionBoundarySuiteReport()
        val syntheticTraceCompletionAudit =
            SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceCompletionAuditPolicy
                .currentProviderOperationSyntheticTraceCompletionAudit()
        val checks =
            SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGateCheck.entries.toList()

        val identityDecisionPresent =
            identityDecision.futureTestOnlyIdentityAllowedInPrinciple &&
                identityDecision.currentSelectableIdentityDisabledProviderOnly
        val isolationGuardPresent =
            isolationGuard.isolationGuardModeled &&
                isolationGuard.providerSelectionDisabledProviderOnly
        val syntheticNamespacePresent =
            namespace.namespaceContractModeled &&
                namespace.modeledButStillDisabled &&
                namespace.allEvidenceNonAuthorizing
        val sourceSetConfinementPresent =
            sourceSetConfinement.sourceSetConfinementModeled &&
                sourceSetConfinement.stillDisabled
        val implementationDecisionPresent =
            implementationDecision.implementationDecisionModeled &&
                implementationDecision.stillDisabled
        val prerequisiteAuditPresent =
            prerequisiteAudit.prerequisiteAuditModeled &&
                prerequisiteAudit.stillDisabled
        val scopeDecisionPresent =
            scopeDecision.scopeDecisionModeled &&
                scopeDecision.stillDisabled
        val implementationContractPresent =
            implementationContract.implementationContractModeled &&
                implementationContract.stillDisabled
        val readinessGatePresent =
            readinessGate.readinessGateModeled &&
                readinessGate.stillDisabled
        val runtimeLinkageGuardPresent =
            runtimeLinkageGuard.runtimeLinkageGuardModeled &&
                runtimeLinkageGuard.stillDisabled
        val promotionBlockersPresent =
            promotionBlockers.promotionBlockersModeled &&
                promotionBlockers.stillDisabled
        val implementationAdmissionGatePresent =
            admissionGate.admissionGateModeled &&
                admissionGate.stillDisabled
        val implementationPlanPresent =
            implementationPlan.implementationPlanModeled &&
                implementationPlan.planningOnlyCurrentBranch
        val katFixtureChainPresent =
            katFixtureScope.fixtureScopeIsCommonTestOnly &&
                katFixtureCatalog.catalogIsCommonTestOnly &&
                katFixtureValidation.validationIsCommonTestOnly &&
                katFixtureValidation.allValidationChecksPassed &&
                publicVectorAdmission.admissionIsCommonTestOnly &&
                publicVectorAdmission.futureVectorCriteriaModeled
        val providerOperationMetadataChainPresent =
            metadataSuite.suiteIsCommonTestOnly &&
                metadataSuite.providerOperationMetadataKatSuitePassed
        val providerOperationNoopKatChainPresent =
            noopKatSuite.suiteIsCommonTestOnly &&
                noopKatSuite.providerOperationNoopKatSuitePassed
        val providerOperationNoopExecutionBoundaryChainPresent =
            noopExecutionBoundarySuite.suiteIsCommonTestOnly &&
                noopExecutionBoundarySuite.noopExecutionBoundarySuitePassed
        val providerOperationSyntheticTraceChainPresent =
            syntheticTraceCompletionAudit.auditIsCommonTestOnly &&
                syntheticTraceCompletionAudit.chainComplete
        val syntheticTraceCompletionAuditPresent =
            syntheticTraceCompletionAudit.auditIsAuditOnly
        val syntheticTraceCompletionAuditPassed =
            syntheticTraceCompletionAudit.syntheticTraceCompletionAuditPassed
        val sourceGuardCoveragePresent =
            sourceGuardCoverage.sourceGuardCoverageModeled &&
                sourceGuardCoverage.desktopSourceGuardCoverageModeled
        val productionRuntimeAbsenceProven =
            sourceGuardCoveragePresent &&
                sourceGuardCoverage.runtimeRootCoverageModeled &&
                !sourceGuardCoverage.positiveRuntimeBridgeFlagPresent

        val productionProviderSelectable =
            identityDecision.productionProviderSelectable ||
                isolationGuard.productionProviderSelectable ||
                namespace.productionProviderSelectable ||
                sourceSetConfinement.capabilities.productionProviderSelectable ||
                implementationDecision.capabilities.productionProviderSelectable ||
                prerequisiteAudit.capabilities.productionProviderSelectable ||
                scopeDecision.capabilities.productionProviderSelectable ||
                implementationContract.capabilities.productionProviderSelectable ||
                readinessGate.capabilities.productionProviderSelectable ||
                runtimeLinkageGuard.capabilities.productionProviderSelectable ||
                promotionBlockers.capabilities.productionProviderSelectable ||
                sourceGuardCoverage.capabilities.productionProviderSelectable ||
                redactionGuard.capabilities.productionProviderSelectable ||
                admissionGate.capabilities.productionProviderSelectable ||
                implementationPlan.capabilities.productionProviderSelectable ||
                metadataSuite.productionProviderSelectable ||
                noopKatSuite.productionProviderSelectable ||
                noopExecutionBoundarySuite.productionProviderSelectable ||
                syntheticTraceCompletionAudit.productionProviderSelectable
        val disabledProviderOnly =
            identityDecision.currentSelectableIdentityDisabledProviderOnly &&
                isolationGuard.providerSelectionDisabledProviderOnly &&
                namespace.providerSelectionDisabledProviderOnly &&
                syntheticTraceCompletionAudit.disabledProviderOnly &&
                !productionProviderSelectable

        val providerRegistryEntryPresent =
            runtimeLinkageGuard.currentRegistryBridgePresent ||
                promotionBlockers.currentPromotionToRegistryPresent ||
                syntheticTraceCompletionAudit.registryEntryPresent
        val providerFactoryPresent =
            runtimeLinkageGuard.currentFactoryBridgePresent ||
                promotionBlockers.currentPromotionToFactoryPresent ||
                syntheticTraceCompletionAudit.factoryEntryPresent
        val providerDispatcherPresent =
            runtimeLinkageGuard.currentDispatcherBridgePresent ||
                promotionBlockers.currentPromotionToDispatcherPresent ||
                syntheticTraceCompletionAudit.dispatcherEntryPresent
        val executorTargetPresent =
            runtimeLinkageGuard.currentExecutorTargetBridgePresent ||
                promotionBlockers.currentPromotionToExecutorTargetPresent ||
                syntheticTraceCompletionAudit.executorTargetPresent
        val providerImplementationPresent =
            identityDecision.testOnlyProviderIdentityImplemented ||
                identityDecision.productionProviderIdentityImplemented ||
                namespace.testOnlyProviderIdentityImplemented ||
                namespace.productionProviderIdentityImplemented ||
                promotionBlockers.currentPromotionToTestOnlyIdentityImplementationPresent ||
                promotionBlockers.currentPromotionToProductionIdentityPresent ||
                syntheticTraceCompletionAudit.providerImplementationPresent
        val providerOperationExecuted =
            runtimeLinkageGuard.currentProviderOperationBridgePresent ||
                promotionBlockers.currentPromotionToProviderOperationPresent ||
                syntheticTraceCompletionAudit.providerOperationExecuted
        val cryptoExecuted =
            runtimeLinkageGuard.currentCryptoExecutionBridgePresent ||
                promotionBlockers.currentPromotionToCryptoExecutionPresent ||
                syntheticTraceCompletionAudit.cryptoExecuted
        val katRunnerPresent = syntheticTraceCompletionAudit.katRunnerPresent
        val katExecutorPresent = syntheticTraceCompletionAudit.katExecutorPresent
        val providerKatExecutorPresent =
            runtimeLinkageGuard.currentProviderKatExecutorBridgePresent ||
                promotionBlockers.currentPromotionToProviderKatExecutorPresent ||
                syntheticTraceCompletionAudit.providerKatExecutorPresent
        val tracePayloadPresent = syntheticTraceCompletionAudit.tracePayloadPresent
        val rawKatMaterialPresent =
            katFixtureScope.rawKatMaterialPresent ||
                katFixtureCatalog.rawKatMaterialPresent ||
                katFixtureValidation.rawKatMaterialPresent ||
                publicVectorAdmission.rawKatMaterialPresent ||
                metadataSuite.rawKatMaterialPresent ||
                noopKatSuite.rawKatMaterialPresent ||
                noopExecutionBoundarySuite.rawKatMaterialPresent ||
                syntheticTraceCompletionAudit.rawKatMaterialPresent
        val publicVectorBytesPresent =
            publicVectorAdmission.publicVectorBytesPresent ||
                metadataSuite.publicVectorBytesPresent ||
                noopKatSuite.publicVectorBytesPresent ||
                noopExecutionBoundarySuite.publicVectorBytesPresent ||
                syntheticTraceCompletionAudit.publicVectorBytesPresent
        val publicVectorHexPresent =
            publicVectorAdmission.publicVectorHexPresent ||
                metadataSuite.publicVectorHexPresent ||
                noopKatSuite.publicVectorHexPresent ||
                noopExecutionBoundarySuite.publicVectorHexPresent ||
                syntheticTraceCompletionAudit.publicVectorHexPresent
        val vaultLifecyclePresent =
            runtimeLinkageGuard.currentVaultLifecycleBridgePresent ||
                syntheticTraceCompletionAudit.vaultLifecyclePresent
        val vaultPersistencePresent =
            runtimeLinkageGuard.currentPersistenceBridgePresent ||
                promotionBlockers.currentPromotionToVaultPersistencePresent ||
                syntheticTraceCompletionAudit.vaultPersistencePresent
        val productionSyncPresent =
            promotionBlockers.currentPromotionToProductionSyncPresent ||
                syntheticTraceCompletionAudit.productionSyncPresent
        val signingBroadcastingPresent =
            runtimeLinkageGuard.currentSigningBroadcastingBridgePresent ||
                promotionBlockers.currentPromotionToSigningBroadcastingPresent ||
                syntheticTraceCompletionAudit.signingBroadcastingPresent
        val uiPresent =
            runtimeLinkageGuard.currentUiSurfaceBridgePresent ||
                promotionBlockers.currentPromotionToUiPresent ||
                syntheticTraceCompletionAudit.uiPresent
        val endpointPresent =
            runtimeLinkageGuard.currentPublicEndpointBridgePresent ||
                promotionBlockers.currentPromotionToPublicEndpointPresent ||
                syntheticTraceCompletionAudit.endpointPresent
        val mainnetPresent =
            runtimeLinkageGuard.currentMainnetBridgePresent ||
                promotionBlockers.currentPromotionToMainnetPresent ||
                syntheticTraceCompletionAudit.mainnetPresent

        val implementationAuthorized = false
        val providerImplementationAuthorized = false
        val providerOperationExecutionAuthorized = false
        val cryptoExecutionAuthorized = false
        val katExecutorAuthorized = false
        val mainnetAuthorized = false

        val productionAuthorizationPresent = false
        val providerSelectionAuthorizationPresent =
            admissionGate.currentProviderSelectionAdmitted ||
                syntheticTraceCompletionAudit.providerSelectionAuthorizationPresent
        val providerOperationAuthorizationPresent =
            syntheticTraceCompletionAudit.providerOperationAuthorizationPresent
        val cryptoAuthorizationPresent =
            syntheticTraceCompletionAudit.cryptoAuthorizationPresent
        val katRunnerAuthorizationPresent =
            syntheticTraceCompletionAudit.katRunnerAuthorizationPresent
        val katExecutorAuthorizationPresent =
            syntheticTraceCompletionAudit.katExecutorAuthorizationPresent
        val providerKatExecutorAuthorizationPresent =
            syntheticTraceCompletionAudit.providerKatExecutorAuthorizationPresent
        val vaultPersistenceAuthorizationPresent =
            admissionGate.currentVaultPersistenceAdmitted ||
                syntheticTraceCompletionAudit.vaultPersistenceAuthorizationPresent
        val syncAuthorizationPresent =
            admissionGate.currentProductionSyncAdmitted ||
                syntheticTraceCompletionAudit.syncAuthorizationPresent
        val signingBroadcastingAuthorizationPresent =
            admissionGate.currentSigningBroadcastingAdmitted ||
                syntheticTraceCompletionAudit.signingBroadcastingAuthorizationPresent
        val uiAuthorizationPresent =
            admissionGate.currentUiSurfaceAdmitted ||
                syntheticTraceCompletionAudit.uiAuthorizationPresent
        val endpointAuthorizationPresent =
            admissionGate.currentPublicEndpointAdmitted ||
                syntheticTraceCompletionAudit.endpointAuthorizationPresent
        val mainnetAuthorizationPresent =
            admissionGate.currentMainnetAdmitted ||
                syntheticTraceCompletionAudit.mainnetAuthorizationPresent

        val authorizationsAbsent =
            !implementationAuthorized &&
                !providerImplementationAuthorized &&
                !providerOperationExecutionAuthorized &&
                !cryptoExecutionAuthorized &&
                !katExecutorAuthorized &&
                !mainnetAuthorized &&
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
                !mainnetAuthorizationPresent
        val runtimeAbsent =
            !providerRegistryEntryPresent &&
                !providerFactoryPresent &&
                !providerDispatcherPresent &&
                !executorTargetPresent &&
                !providerImplementationPresent &&
                !providerOperationExecuted &&
                !cryptoExecuted &&
                !katRunnerPresent &&
                !katExecutorPresent &&
                !providerKatExecutorPresent &&
                !vaultLifecyclePresent &&
                !vaultPersistencePresent &&
                !productionSyncPresent &&
                !signingBroadcastingPresent &&
                !uiPresent &&
                !endpointPresent &&
                !mainnetPresent
        val materialAbsent =
            !tracePayloadPresent &&
                !rawKatMaterialPresent &&
                !publicVectorBytesPresent &&
                !publicVectorHexPresent
        val evidencePresent =
            listOf(
                identityDecisionPresent,
                isolationGuardPresent,
                syntheticNamespacePresent,
                sourceSetConfinementPresent,
                implementationDecisionPresent,
                prerequisiteAuditPresent,
                scopeDecisionPresent,
                implementationContractPresent,
                readinessGatePresent,
                runtimeLinkageGuardPresent,
                promotionBlockersPresent,
                implementationAdmissionGatePresent,
                implementationPlanPresent,
                katFixtureChainPresent,
                providerOperationMetadataChainPresent,
                providerOperationNoopKatChainPresent,
                providerOperationNoopExecutionBoundaryChainPresent,
                providerOperationSyntheticTraceChainPresent,
                syntheticTraceCompletionAuditPresent,
                syntheticTraceCompletionAuditPassed,
                sourceGuardCoveragePresent,
                productionRuntimeAbsenceProven,
            ).all { present -> present }
        val reviewReadyForHumanDecision =
            evidencePresent &&
                authorizationsAbsent &&
                runtimeAbsent &&
                materialAbsent &&
                disabledProviderOnly &&
                !productionProviderSelectable

        val failures =
            buildList {
                if (!evidencePresent) {
                    add(SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGateFailureLabel.PrerequisiteEvidenceMissing)
                }
                if (!syntheticTraceCompletionAuditPresent) {
                    add(SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGateFailureLabel.CompletionAuditMissing)
                }
                if (!syntheticTraceCompletionAuditPassed) {
                    add(SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGateFailureLabel.CompletionAuditFailed)
                }
                if (!runtimeAbsent) {
                    add(SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGateFailureLabel.RuntimeReachabilityPresent)
                }
                if (providerOperationExecuted || cryptoExecuted || katRunnerPresent || katExecutorPresent) {
                    add(SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGateFailureLabel.ExecutionPresent)
                }
                if (!authorizationsAbsent) {
                    add(SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGateFailureLabel.AuthorizationPresent)
                }
                if (!materialAbsent) {
                    add(SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGateFailureLabel.MaterialPresent)
                }
                if (!disabledProviderOnly) {
                    add(
                        SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGateFailureLabel
                            .ProviderSelectionNotDisabledOnly,
                    )
                }
                if (productionProviderSelectable) {
                    add(
                        SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGateFailureLabel
                            .ProductionProviderSelectable,
                    )
                }
            }
        val blockers =
            if (failures.isEmpty()) {
                emptyList()
            } else {
                SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGateBlocker.entries.toList()
            }

        return SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGate(
            gateId =
                SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGateSafeLabel(
                    "TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_TRANSITION_GATE_COMMON_TEST_ONLY",
                ),
            chainName =
                SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGateChain
                    .TestOnlyProviderIdentityImplementationPrerequisiteChain,
            sourceSet =
                SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGateSourceSet.CommonTest,
            transitionGateIsCommonTestOnly = true,
            transitionGateOnly = true,
            identityDecisionPresent = identityDecisionPresent,
            isolationGuardPresent = isolationGuardPresent,
            syntheticNamespacePresent = syntheticNamespacePresent,
            sourceSetConfinementPresent = sourceSetConfinementPresent,
            implementationDecisionPresent = implementationDecisionPresent,
            prerequisiteAuditPresent = prerequisiteAuditPresent,
            scopeDecisionPresent = scopeDecisionPresent,
            implementationContractPresent = implementationContractPresent,
            readinessGatePresent = readinessGatePresent,
            runtimeLinkageGuardPresent = runtimeLinkageGuardPresent,
            promotionBlockersPresent = promotionBlockersPresent,
            implementationAdmissionGatePresent = implementationAdmissionGatePresent,
            implementationPlanPresent = implementationPlanPresent,
            katFixtureChainPresent = katFixtureChainPresent,
            providerOperationMetadataChainPresent = providerOperationMetadataChainPresent,
            providerOperationNoopKatChainPresent = providerOperationNoopKatChainPresent,
            providerOperationNoopExecutionBoundaryChainPresent =
                providerOperationNoopExecutionBoundaryChainPresent,
            providerOperationSyntheticTraceChainPresent = providerOperationSyntheticTraceChainPresent,
            syntheticTraceCompletionAuditPresent = syntheticTraceCompletionAuditPresent,
            syntheticTraceCompletionAuditPassed = syntheticTraceCompletionAuditPassed,
            sourceGuardCoveragePresent = sourceGuardCoveragePresent,
            productionRuntimeAbsenceProven = productionRuntimeAbsenceProven,
            sourceMaterialCorpusRulesPresent = true,
            buildHistoryExcludedFromNormalSourceMaterialCorpus = true,
            docsReadmeSeparateCorpus = true,
            reviewReadyForHumanDecision = reviewReadyForHumanDecision,
            reviewReadyIsHumanDecisionOnly = true,
            implementationAuthorized = implementationAuthorized,
            providerImplementationAuthorized = providerImplementationAuthorized,
            providerOperationExecutionAuthorized = providerOperationExecutionAuthorized,
            cryptoExecutionAuthorized = cryptoExecutionAuthorized,
            katExecutorAuthorized = katExecutorAuthorized,
            mainnetAuthorized = mainnetAuthorized,
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
            productionProviderSelectable = productionProviderSelectable,
            disabledProviderOnly = disabledProviderOnly,
            providerRegistryEntryPresent = providerRegistryEntryPresent,
            providerFactoryPresent = providerFactoryPresent,
            providerDispatcherPresent = providerDispatcherPresent,
            executorTargetPresent = executorTargetPresent,
            providerImplementationPresent = providerImplementationPresent,
            providerOperationExecuted = providerOperationExecuted,
            cryptoExecuted = cryptoExecuted,
            katRunnerPresent = katRunnerPresent,
            katExecutorPresent = katExecutorPresent,
            providerKatExecutorPresent = providerKatExecutorPresent,
            tracePayloadPresent = tracePayloadPresent,
            rawKatMaterialPresent = rawKatMaterialPresent,
            publicVectorBytesPresent = publicVectorBytesPresent,
            publicVectorHexPresent = publicVectorHexPresent,
            vaultLifecyclePresent = vaultLifecyclePresent,
            vaultPersistencePresent = vaultPersistencePresent,
            productionSyncPresent = productionSyncPresent,
            signingBroadcastingPresent = signingBroadcastingPresent,
            uiPresent = uiPresent,
            endpointPresent = endpointPresent,
            mainnetPresent = mainnetPresent,
            evidenceCount = 22,
            transitionCheckCount = checks.size,
            safeLabelCount = 2,
            enumEvidenceCount = 3,
            booleanEvidenceCount = 74,
            blockerCount = blockers.size,
            warningCount = 0,
            transitionChecks = checks,
            failureLabels = failures,
            blockers = blockers,
            warnings = emptyList(),
            displayLabel =
                SkaldVaultV1TestOnlyProviderIdentityImplementationTransitionGateSafeLabel(
                    "IMPLEMENTATION_TRANSITION_GATE_HUMAN_REVIEW_READY_NOT_AUTHORIZATION",
                ),
        )
    }
}
