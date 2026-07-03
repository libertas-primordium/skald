package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityImplementationPlanSafeLabel(val value: String) {
    override fun toString(): String = "RedactedPlanLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationPlanStatus {
    PlanModeled,
    StillDisabled,
    PlanningOnlyCurrentBranch,
    NonAuthorizing,
    ProviderSelectionDisabledProviderOnly,
    ProductionProviderSelectableFalse,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationPlanOutcome {
    CurrentPlanModeled,
    CurrentImplementationNotAuthorized,
    TestOnlyImplementationRequiresNextBranch,
    ProductionImplementationNotAuthorized,
    MainnetNotAuthorized,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationPlanPhase {
    PlanningOnlyCurrentBranch,
    TestOnlyImplementationNextPhase,
    TestOnlyValidationPhase,
    ProductionImplementationReviewPhase,
    ProductionValidationPhase,
    VaultFeatureAccelerationAfterCompletion,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationPlannedTestOnlyArtifact {
    TestOnlyProviderIdentityClassOrObject,
    TestOnlySyntheticSafeIdConstant,
    TestOnlyNamespaceMarker,
    TestOnlySourceSetPlacement,
    TestOnlyNonProductionFixture,
    TestOnlyNoProviderSelectionProof,
    TestOnlyNoRegistryProof,
    TestOnlyNoFactoryProof,
    TestOnlyNoDispatcherProof,
    TestOnlyNoExecutorTargetProof,
    TestOnlyNoKatExecutorProof,
    TestOnlyNoProviderOperationProof,
    TestOnlyNoCryptoExecutionProof,
    TestOnlyNoVaultPersistenceProof,
    TestOnlyNoProductionSyncProof,
    TestOnlyNoSigningBroadcastingProof,
    TestOnlyNoMainnetProof,
    TestOnlyRedactionProof,
    TestOnlySourceGuardProof,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenCurrentArtifact {
    CurrentTestOnlyProviderIdentityImplementation,
    CurrentProductionProviderIdentityImplementation,
    CurrentVaultCryptoProviderImplementation,
    CurrentProviderSelectionEntry,
    CurrentNonDisabledRegistryEntry,
    CurrentProviderFactoryEntry,
    CurrentProviderDispatcherEntry,
    CurrentExecutorTarget,
    CurrentProviderKatExecutor,
    CurrentProviderOperationExecution,
    CurrentCryptoExecution,
    CurrentVaultCreation,
    CurrentVaultUnlock,
    CurrentVaultSession,
    CurrentVaultPersistence,
    CurrentSecureStorageSuccess,
    CurrentSecureMetadataSuccess,
    CurrentProductionSync,
    CurrentBackendClient,
    CurrentBdkWalletState,
    CurrentSettingsPersistence,
    CurrentUiSurface,
    CurrentSigning,
    CurrentBroadcasting,
    CurrentPublicEndpointDefault,
    CurrentMainnet,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationConstraint {
    MustUseTestSourceOnlyPlacementForInitialImplementation,
    MustUseSyntheticSafeIdOnly,
    MustRemainAbsentFromProviderSelection,
    MustRemainAbsentFromProductionRegistry,
    MustRemainAbsentFromProviderFactory,
    MustRemainAbsentFromProviderDispatcher,
    MustRemainAbsentFromExecutorTargets,
    MustNotImplementVaultCryptoProvider,
    MustNotExposeProviderHandles,
    MustNotAcceptCryptoObjects,
    MustNotAcceptRawMaterial,
    MustNotRunProviderOperations,
    MustNotRunRandomness,
    MustNotRunKdfHkdfHmacAead,
    MustNotGenerateKeys,
    MustNotStoreKeysets,
    MustNotTouchVaultLifecycle,
    MustNotTouchPersistence,
    MustNotTouchSecureStorage,
    MustNotTouchSecureMetadata,
    MustNotTouchBackendClients,
    MustNotTouchBdkWalletState,
    MustNotTouchSettings,
    MustNotTouchUi,
    MustNotSignOrBroadcast,
    MustNotAddPublicEndpoints,
    MustNotEnableMainnet,
    MustRemainRedacted,
    MustRemainCoveredBySourceGuards,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationAcceptanceCriterion {
    TestOnlyImplementationCompilesOnlyInApprovedTestSourceSet,
    TestOnlyImplementationHasSyntheticSafeIdOnly,
    TestOnlyImplementationHasNoProviderSelectionReachability,
    TestOnlyImplementationHasNoRegistryReachability,
    TestOnlyImplementationHasNoFactoryReachability,
    TestOnlyImplementationHasNoDispatcherReachability,
    TestOnlyImplementationHasNoExecutorTargetReachability,
    TestOnlyImplementationHasNoKatExecutorReachability,
    TestOnlyImplementationHasNoProviderOperationExecution,
    TestOnlyImplementationHasNoCryptoExecution,
    TestOnlyImplementationHasNoVaultLifecycleReachability,
    TestOnlyImplementationHasNoPersistenceReachability,
    TestOnlyImplementationHasNoBackendBdkSettingsUiReachability,
    TestOnlyImplementationHasNoSigningBroadcastingMainnetReachability,
    TestOnlyImplementationRedactionTestsPass,
    SourceGuardCoveragePasses,
    AllPriorModelOnlyEvidenceRemainsNonAuthorizing,
    ProductionProviderSelectableRemainsFalse,
    DisabledProviderSelectionRemainsOnlyRuntimeSelection,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationEscalationGate {
    TestOnlyImplementationBranchExplicitlyApproved,
    TestOnlyValidationResultsReviewed,
    ProductionImplementationBranchExplicitlyApproved,
    ProductionProviderAcceptanceContractSatisfied,
    ProductionProviderKatsPassSameBehaviorAsTestImplementation,
    VaultPersistenceHardeningReviewed,
    MainnetReleaseHardeningApproved,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationRollbackCriterion {
    AnyProviderSelectionReachabilityAppears,
    AnyNonDisabledRegistryEntryAppears,
    AnyFactoryOrDispatcherReachabilityAppears,
    AnyExecutorTargetAppears,
    AnyProviderOperationExecutionAppears,
    AnyCryptoExecutionAppears,
    AnyVaultPersistenceAppears,
    AnyProductionSyncAppears,
    AnySigningBroadcastingAppears,
    AnyPublicEndpointDefaultAppears,
    AnyMainnetEnablementAppears,
    AnyRawMaterialOrSecretLeakageAppears,
    AnySourceGuardFailureAppears,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationPlanEvidenceSource {
    AdmissionGateEvidence,
    RedactionGuardEvidence,
    SourceGuardCoverageEvidence,
    DocumentationEvidence,
    BuildHistoryEvidence,
    TestEvidence,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationPlanRedactionClass {
    PolicyIdOnly,
    EnumNameOnly,
    RedactedSafeLabelOnly,
    BooleanEvidenceOnly,
    NonAuthorizingSummaryOnly,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationPlanFutureReviewRequirement {
    TestOnlyImplementationBranchApprovalRequired,
    TestOnlyValidationReviewRequired,
    ProductionImplementationBranchApprovalRequired,
    ProductionProviderBehaviorReviewRequired,
    MainnetReleaseHardeningReviewRequired,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker {
    PlanningEvidenceNonAuthorizing,
    CurrentImplementationNotAuthorized,
    TestOnlyImplementationRequiresNextBranch,
    ProductionImplementationNotAuthorized,
    ProviderSelectionDisabledProviderOnly,
    ProductionProviderSelectableFalse,
    MainnetNotAuthorized,
    UserConsentCannotOverride,
    WarningOnlyEvidenceNonAuthorizing,
    TestOnlyEvidenceNonAuthorizing,
    ReleaseEvidenceNonAuthorizing,
    PlanAuthorizationClaimRejected,
    TestOnlyImplementationClaimRejected,
    ProductionImplementationClaimRejected,
    ProviderSelectionClaimRejected,
    ProductionProviderSelectableClaimRejected,
    VaultPersistenceClaimRejected,
    ProductionSyncClaimRejected,
    SigningBroadcastingClaimRejected,
    PublicEndpointClaimRejected,
    MainnetClaimRejected,
}

data class SkaldVaultV1TestOnlyProviderIdentityImplementationPlanPhaseRow(
    val phase: SkaldVaultV1TestOnlyProviderIdentityImplementationPlanPhase,
    val modeled: Boolean,
    val currentBranch: Boolean,
    val futureOnly: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationPlanSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationPlannedTestOnlyArtifactRow(
    val artifact: SkaldVaultV1TestOnlyProviderIdentityImplementationPlannedTestOnlyArtifact,
    val plannedForFuture: Boolean,
    val presentNow: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationPlanSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenCurrentArtifactRow(
    val artifact: SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenCurrentArtifact,
    val forbiddenNow: Boolean,
    val presentNow: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationPlanSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationConstraintRow(
    val constraint: SkaldVaultV1TestOnlyProviderIdentityImplementationConstraint,
    val requiredForFutureImplementation: Boolean,
    val currentBranchSatisfiesByAbsenceOnly: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationPlanSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationAcceptanceCriterionRow(
    val criterion: SkaldVaultV1TestOnlyProviderIdentityImplementationAcceptanceCriterion,
    val requiredForFutureImplementation: Boolean,
    val satisfiedNow: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationPlanSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationEscalationGateRow(
    val gate: SkaldVaultV1TestOnlyProviderIdentityImplementationEscalationGate,
    val requiredBeforeEscalation: Boolean,
    val satisfiedNow: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationPlanSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationRollbackCriterionRow(
    val criterion: SkaldVaultV1TestOnlyProviderIdentityImplementationRollbackCriterion,
    val wouldRequireRollback: Boolean,
    val triggeredNow: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationPlanSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationPlanEvidenceSourceRow(
    val evidenceSource: SkaldVaultV1TestOnlyProviderIdentityImplementationPlanEvidenceSource,
    val included: Boolean,
    val nonAuthorizing: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationPlanSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationPlanFutureReviewRow(
    val requirement: SkaldVaultV1TestOnlyProviderIdentityImplementationPlanFutureReviewRequirement,
    val requiredBeforeImplementation: Boolean,
    val satisfiedNow: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationPlanSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlockerRow(
    val blocker: SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker,
    val active: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationPlanSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationPlanRequest(
    val includePriorAdmissionGateEvidence: Boolean = true,
    val includePriorRedactionGuardEvidence: Boolean = true,
    val userConsentOverrideRequested: Boolean = false,
    val warningOnlyEvidenceClaimed: Boolean = false,
    val testOnlyEvidenceClaimedAsProductionPromotion: Boolean = false,
    val releaseEvidenceClaimed: Boolean = false,
    val planClaimedAsImplementationAuthorization: Boolean = false,
    val testOnlyImplementationClaimedNow: Boolean = false,
    val productionImplementationClaimed: Boolean = false,
    val providerSelectionClaimed: Boolean = false,
    val productionProviderSelectableClaimed: Boolean = false,
    val vaultPersistenceClaimed: Boolean = false,
    val productionSyncClaimed: Boolean = false,
    val signingBroadcastingClaimed: Boolean = false,
    val publicEndpointClaimed: Boolean = false,
    val mainnetClaimed: Boolean = false,
) {
    override fun toString(): String = "RedactedRequest(claims=modeled)"
}

data class SkaldVaultV1TestOnlyProviderIdentityImplementationPlanEvidence(
    val policyId: String,
    val policyVersion: Int,
    val implementationPlanModeled: Boolean,
    val stillDisabled: Boolean,
    val planningOnlyCurrentBranch: Boolean,
    val testOnlyImplementationNextPhaseModeled: Boolean,
    val productionImplementationFuturePhaseModeled: Boolean,
    val acceptanceCriteriaModeled: Boolean,
    val escalationGatesModeled: Boolean,
    val rollbackCriteriaModeled: Boolean,
    val docsMayDescribePlan: Boolean,
    val testsMayAssertBlockedPlan: Boolean,
    val currentPlanAdmitsImplementation: Boolean,
    val currentTestOnlyImplementationStarted: Boolean,
    val currentProductionImplementationStarted: Boolean,
    val currentProviderSelectionChanged: Boolean,
    val currentProductionProviderSelectableChanged: Boolean,
    val currentVaultPersistenceChanged: Boolean,
    val currentProductionSyncChanged: Boolean,
    val currentSigningBroadcastingChanged: Boolean,
    val currentPublicEndpointChanged: Boolean,
    val currentMainnetChanged: Boolean,
    val statuses: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationPlanStatus>,
    val outcomes: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationPlanOutcome>,
    val planPhaseRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationPlanPhaseRow>,
    val plannedTestOnlyArtifactRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationPlannedTestOnlyArtifactRow>,
    val forbiddenCurrentArtifactRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenCurrentArtifactRow>,
    val implementationConstraintRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationConstraintRow>,
    val acceptanceCriterionRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationAcceptanceCriterionRow>,
    val escalationGateRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationEscalationGateRow>,
    val rollbackCriterionRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationRollbackCriterionRow>,
    val evidenceSourceRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationPlanEvidenceSourceRow>,
    val futureReviewRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationPlanFutureReviewRow>,
    val blockerRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlockerRow>,
    val blockers: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker>,
    val redactionClasses: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationPlanRedactionClass>,
    val capabilities: SkaldVaultV1TestOnlyProviderIdentityImplementationPlanCapabilities,
)

object SkaldVaultV1TestOnlyProviderIdentityImplementationPlanPolicy {
    const val POLICY_ID: String =
        "skald-vault-v1-test-only-provider-identity-implementation-plan-v1"
    const val POLICY_VERSION: Int = 1

    fun currentImplementationPlanEvidence():
        SkaldVaultV1TestOnlyProviderIdentityImplementationPlanEvidence =
        evaluateImplementationPlan()

    fun evaluateImplementationPlan(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationPlanRequest =
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanRequest(),
    ): SkaldVaultV1TestOnlyProviderIdentityImplementationPlanEvidence {
        val blockers = baseBlockers() + requestBlockers(request)
        return SkaldVaultV1TestOnlyProviderIdentityImplementationPlanEvidence(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            implementationPlanModeled = true,
            stillDisabled = true,
            planningOnlyCurrentBranch = true,
            testOnlyImplementationNextPhaseModeled = true,
            productionImplementationFuturePhaseModeled = true,
            acceptanceCriteriaModeled = true,
            escalationGatesModeled = true,
            rollbackCriteriaModeled = true,
            docsMayDescribePlan = true,
            testsMayAssertBlockedPlan = true,
            currentPlanAdmitsImplementation = false,
            currentTestOnlyImplementationStarted = false,
            currentProductionImplementationStarted = false,
            currentProviderSelectionChanged = false,
            currentProductionProviderSelectableChanged = false,
            currentVaultPersistenceChanged = false,
            currentProductionSyncChanged = false,
            currentSigningBroadcastingChanged = false,
            currentPublicEndpointChanged = false,
            currentMainnetChanged = false,
            statuses = SkaldVaultV1TestOnlyProviderIdentityImplementationPlanStatus.entries.toSet(),
            outcomes = SkaldVaultV1TestOnlyProviderIdentityImplementationPlanOutcome.entries.toSet(),
            planPhaseRows = currentPlanPhaseRows(),
            plannedTestOnlyArtifactRows = currentPlannedTestOnlyArtifactRows(),
            forbiddenCurrentArtifactRows = currentForbiddenCurrentArtifactRows(),
            implementationConstraintRows = currentImplementationConstraintRows(),
            acceptanceCriterionRows = currentAcceptanceCriterionRows(),
            escalationGateRows = currentEscalationGateRows(),
            rollbackCriterionRows = currentRollbackCriterionRows(),
            evidenceSourceRows = currentEvidenceSourceRows(request),
            futureReviewRows = currentFutureReviewRows(),
            blockerRows = currentBlockerRows(blockers),
            blockers = blockers,
            redactionClasses =
                SkaldVaultV1TestOnlyProviderIdentityImplementationPlanRedactionClass.entries.toSet(),
            capabilities = SkaldVaultV1TestOnlyProviderIdentityImplementationPlanCapabilities.Current,
        )
    }

    private fun currentPlanPhaseRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationPlanPhaseRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationPlanPhase.entries.map { phase ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanPhaseRow(
                phase = phase,
                modeled = true,
                currentBranch =
                    phase == SkaldVaultV1TestOnlyProviderIdentityImplementationPlanPhase.PlanningOnlyCurrentBranch,
                futureOnly =
                    phase != SkaldVaultV1TestOnlyProviderIdentityImplementationPlanPhase.PlanningOnlyCurrentBranch,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentPlannedTestOnlyArtifactRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationPlannedTestOnlyArtifactRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationPlannedTestOnlyArtifact.entries.map { artifact ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlannedTestOnlyArtifactRow(
                artifact = artifact,
                plannedForFuture = true,
                presentNow = false,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentForbiddenCurrentArtifactRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenCurrentArtifactRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenCurrentArtifact.entries.map { artifact ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenCurrentArtifactRow(
                artifact = artifact,
                forbiddenNow = true,
                presentNow = false,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentImplementationConstraintRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationConstraintRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationConstraint.entries.map { constraint ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationConstraintRow(
                constraint = constraint,
                requiredForFutureImplementation = true,
                currentBranchSatisfiesByAbsenceOnly = true,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentAcceptanceCriterionRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationAcceptanceCriterionRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationAcceptanceCriterion.entries.map { criterion ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationAcceptanceCriterionRow(
                criterion = criterion,
                requiredForFutureImplementation = true,
                satisfiedNow = false,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentEscalationGateRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationEscalationGateRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationEscalationGate.entries.map { gate ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationEscalationGateRow(
                gate = gate,
                requiredBeforeEscalation = true,
                satisfiedNow = false,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentRollbackCriterionRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationRollbackCriterionRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationRollbackCriterion.entries.map { criterion ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationRollbackCriterionRow(
                criterion = criterion,
                wouldRequireRollback = true,
                triggeredNow = false,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentEvidenceSourceRows(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationPlanRequest,
    ): List<SkaldVaultV1TestOnlyProviderIdentityImplementationPlanEvidenceSourceRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationPlanEvidenceSource.entries.map { source ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanEvidenceSourceRow(
                evidenceSource = source,
                included = source.includedBy(request),
                nonAuthorizing = true,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentFutureReviewRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationPlanFutureReviewRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationPlanFutureReviewRequirement.entries.map { requirement ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanFutureReviewRow(
                requirement = requirement,
                requiredBeforeImplementation = true,
                satisfiedNow = false,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentBlockerRows(
        blockers: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker>,
    ): List<SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlockerRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker.entries.map { blocker ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlockerRow(
                blocker = blocker,
                active = blocker in blockers,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun SkaldVaultV1TestOnlyProviderIdentityImplementationPlanEvidenceSource.includedBy(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationPlanRequest,
    ): Boolean =
        when (this) {
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanEvidenceSource.AdmissionGateEvidence ->
                request.includePriorAdmissionGateEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanEvidenceSource.RedactionGuardEvidence ->
                request.includePriorRedactionGuardEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanEvidenceSource.SourceGuardCoverageEvidence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanEvidenceSource.DocumentationEvidence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanEvidenceSource.BuildHistoryEvidence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanEvidenceSource.TestEvidence,
            -> true
        }

    private fun baseBlockers(): Set<SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker> =
        setOf(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker.PlanningEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker.CurrentImplementationNotAuthorized,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker.TestOnlyImplementationRequiresNextBranch,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker.ProductionImplementationNotAuthorized,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker.ProviderSelectionDisabledProviderOnly,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker.ProductionProviderSelectableFalse,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker.MainnetNotAuthorized,
        )

    private fun requestBlockers(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationPlanRequest,
    ): Set<SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker> =
        buildSet {
            if (request.userConsentOverrideRequested) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker.UserConsentCannotOverride)
            }
            if (request.warningOnlyEvidenceClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker.WarningOnlyEvidenceNonAuthorizing)
            }
            if (request.testOnlyEvidenceClaimedAsProductionPromotion) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker.TestOnlyEvidenceNonAuthorizing)
            }
            if (request.releaseEvidenceClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker.ReleaseEvidenceNonAuthorizing)
            }
            if (request.planClaimedAsImplementationAuthorization) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker.PlanAuthorizationClaimRejected)
            }
            if (request.testOnlyImplementationClaimedNow) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker.TestOnlyImplementationClaimRejected)
            }
            if (request.productionImplementationClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker.ProductionImplementationClaimRejected)
            }
            if (request.providerSelectionClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker.ProviderSelectionClaimRejected)
            }
            if (request.productionProviderSelectableClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker
                        .ProductionProviderSelectableClaimRejected,
                )
            }
            if (request.vaultPersistenceClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker.VaultPersistenceClaimRejected)
            }
            if (request.productionSyncClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker.ProductionSyncClaimRejected)
            }
            if (request.signingBroadcastingClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker.SigningBroadcastingClaimRejected)
            }
            if (request.publicEndpointClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker.PublicEndpointClaimRejected)
            }
            if (request.mainnetClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationPlanBlocker.MainnetClaimRejected)
            }
        }

    private fun redactedLabel(): SkaldVaultV1TestOnlyProviderIdentityImplementationPlanSafeLabel =
        SkaldVaultV1TestOnlyProviderIdentityImplementationPlanSafeLabel("redacted")
}

data class SkaldVaultV1TestOnlyProviderIdentityImplementationPlanCapabilities(
    val implementationPlanAuthorizesImplementation: Boolean,
    val admissionGateAuthorizesImplementation: Boolean,
    val redactionGuardAuthorizesImplementation: Boolean,
    val sourceGuardCoverageAuthorizesImplementation: Boolean,
    val testOnlyIdentityImplementationPresent: Boolean,
    val productionIdentityImplementationPresent: Boolean,
    val canImplementProviderNow: Boolean,
    val canInstantiateProviderNow: Boolean,
    val canRegisterProviderNow: Boolean,
    val canUseAsProviderSelectionId: Boolean,
    val canUseAsRegistryKey: Boolean,
    val canUseAsFactoryInput: Boolean,
    val canUseAsDispatcherInput: Boolean,
    val canUseAsExecutorTarget: Boolean,
    val canUseForProviderKatExecutor: Boolean,
    val canExecuteProviderOperations: Boolean,
    val canExecuteRandomness: Boolean,
    val canExecuteKdf: Boolean,
    val canExecuteHkdf: Boolean,
    val canExecuteHmac: Boolean,
    val canExecuteAead: Boolean,
    val canGenerateKeys: Boolean,
    val canStoreKeysets: Boolean,
    val canUseForVaultCreation: Boolean,
    val canUseForVaultUnlock: Boolean,
    val canUseForVaultSession: Boolean,
    val canUseForVaultPersistence: Boolean,
    val canUseForSecureStorage: Boolean,
    val canUseForSecureMetadataStorage: Boolean,
    val canUseForProductionSync: Boolean,
    val canUseForBackendClient: Boolean,
    val canUseForBdkWalletState: Boolean,
    val canUseForSettingsCodec: Boolean,
    val canUseForUiSurface: Boolean,
    val canUseForSigning: Boolean,
    val canUseForBroadcasting: Boolean,
    val canUseForTorTransport: Boolean,
    val canUseForNostrParsing: Boolean,
    val canUseForPublicEndpointDefault: Boolean,
    val canUseForMainnet: Boolean,
    val productionProviderSelectable: Boolean,
) {
    companion object {
        val Current =
            SkaldVaultV1TestOnlyProviderIdentityImplementationPlanCapabilities(
                implementationPlanAuthorizesImplementation = false,
                admissionGateAuthorizesImplementation = false,
                redactionGuardAuthorizesImplementation = false,
                sourceGuardCoverageAuthorizesImplementation = false,
                testOnlyIdentityImplementationPresent = false,
                productionIdentityImplementationPresent = false,
                canImplementProviderNow = false,
                canInstantiateProviderNow = false,
                canRegisterProviderNow = false,
                canUseAsProviderSelectionId = false,
                canUseAsRegistryKey = false,
                canUseAsFactoryInput = false,
                canUseAsDispatcherInput = false,
                canUseAsExecutorTarget = false,
                canUseForProviderKatExecutor = false,
                canExecuteProviderOperations = false,
                canExecuteRandomness = false,
                canExecuteKdf = false,
                canExecuteHkdf = false,
                canExecuteHmac = false,
                canExecuteAead = false,
                canGenerateKeys = false,
                canStoreKeysets = false,
                canUseForVaultCreation = false,
                canUseForVaultUnlock = false,
                canUseForVaultSession = false,
                canUseForVaultPersistence = false,
                canUseForSecureStorage = false,
                canUseForSecureMetadataStorage = false,
                canUseForProductionSync = false,
                canUseForBackendClient = false,
                canUseForBdkWalletState = false,
                canUseForSettingsCodec = false,
                canUseForUiSurface = false,
                canUseForSigning = false,
                canUseForBroadcasting = false,
                canUseForTorTransport = false,
                canUseForNostrParsing = false,
                canUseForPublicEndpointDefault = false,
                canUseForMainnet = false,
                productionProviderSelectable = false,
            )
    }
}
