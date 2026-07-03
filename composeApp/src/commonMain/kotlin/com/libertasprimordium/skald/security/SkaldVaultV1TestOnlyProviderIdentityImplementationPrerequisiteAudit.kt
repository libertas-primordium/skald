package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditSafeLabel(
    val value: String,
) {
    override fun toString(): String = "RedactedAuditLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditStatus {
    PrerequisiteAuditModeled,
    StillDisabled,
    CurrentAuditBlocked,
    PrerequisitesIncomplete,
    PriorIdentityDecisionEvidenceNonAuthorizing,
    PriorIsolationEvidenceNonAuthorizing,
    PriorNamespaceEvidenceNonAuthorizing,
    PriorSourceSetConfinementEvidenceNonAuthorizing,
    PriorImplementationDecisionEvidenceNonAuthorizing,
    NegativeAbsenceEvidenceModeled,
    SourceGuardEvidenceNonAuthorizing,
    ExplicitFutureBranchApprovalMissing,
    FutureImplementationReviewIncomplete,
    ImplementationNotAuthorized,
    ProductionPromotionNotAuthorized,
    ProviderSelectionDisabledProviderOnly,
    ProductionProviderSelectableFalse,
    VaultLifecycleBlocked,
    PersistenceBlocked,
    ProductionSyncBlocked,
    MainnetNotAuthorized,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditOutcome {
    CurrentAuditBlocked,
    PrerequisitesIncomplete,
    FutureBranchReviewRequired,
    ImplementationNotAuthorized,
    ProductionPromotionNotAuthorized,
    MainnetNotAuthorized,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteCategory {
    BranchApproval,
    PriorEvidenceReview,
    SyntheticIdentityNamespace,
    SourceSetPlacement,
    ProductionSourceAbsence,
    RuntimeReachabilityAbsence,
    ProviderSelectionAbsence,
    RegistryAbsence,
    FactoryAbsence,
    DispatcherAbsence,
    ExecutorTargetAbsence,
    ProviderKatExecutorAbsence,
    ProviderOperationAbsence,
    VaultLifecycleAbsence,
    PersistenceAbsence,
    SecureStorageAbsence,
    SecureMetadataAbsence,
    BackendClientAbsence,
    BdkWalletStateAbsence,
    SettingsCodecAbsence,
    UiSurfaceAbsence,
    SigningBroadcastingAbsence,
    TorNostrAbsence,
    PublicEndpointAbsence,
    MainnetAbsence,
    SourceGuardCoverage,
    RedactionLeakageReview,
    FutureImplementationReview,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem {
    ExplicitFutureBranchApprovalRecorded,
    IdentityDecisionEvidenceReviewed,
    IdentityIsolationEvidenceReviewed,
    SyntheticNamespaceEvidenceReviewed,
    SourceSetConfinementEvidenceReviewed,
    ImplementationDecisionEvidenceReviewed,
    SyntheticSafeIdApprovedForReview,
    TestOnlyNamespaceApprovedForReview,
    NonProductionSourceSetPlacementApprovedForReview,
    ProductionSourceAbsenceProven,
    ProviderSelectionAbsenceProven,
    RegistryAbsenceProven,
    FactoryAbsenceProven,
    DispatcherAbsenceProven,
    ExecutorTargetAbsenceProven,
    ProviderKatExecutorAbsenceProven,
    ProviderOperationAbsenceProven,
    VaultLifecycleAbsenceProven,
    PersistenceAbsenceProven,
    SecureStorageAbsenceProven,
    SecureMetadataAbsenceProven,
    BackendClientAbsenceProven,
    BdkWalletStateAbsenceProven,
    SettingsCodecAbsenceProven,
    UiSurfaceAbsenceProven,
    SigningAbsenceProven,
    BroadcastingAbsenceProven,
    TorAbsenceProven,
    NostrAbsenceProven,
    PublicEndpointDefaultAbsenceProven,
    MainnetAbsenceProven,
    SourceGuardCoverageComplete,
    RedactionAndLeakageReviewComplete,
    FutureTestOnlyImplementationReviewComplete,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteState {
    Blocked,
    FutureReviewRequired,
    PriorEvidencePresentNonAuthorizing,
    NegativeAbsenceEvidenceOnly,
    SourceGuardEvidenceOnly,
    Missing,
    NotImplementationAuthorization,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker {
    CurrentAuditBlocked,
    PrerequisitesIncomplete,
    FutureBranchApprovalMissing,
    SyntheticSafeIdNotApproved,
    TestOnlyNamespaceNotApproved,
    NonProductionSourceSetPlacementNotApproved,
    RedactionReviewIncomplete,
    FutureImplementationReviewIncomplete,
    PriorEvidenceNonAuthorizing,
    NegativeAbsenceEvidenceNonAuthorizing,
    SourceGuardEvidenceNonAuthorizing,
    ImplementationDecisionNonAuthorizing,
    NoTestOnlyProviderIdentityImplementation,
    NoProductionProviderIdentityImplementation,
    NoProviderImplementation,
    NoProviderFactory,
    NoProviderDispatcher,
    NoNonDisabledRegistryEntry,
    NoExecutorTarget,
    NoProviderKatExecutor,
    NoRunnableExecutorInterface,
    ProviderSelectionDisabledProviderOnly,
    ProductionProviderSelectableFalse,
    ProviderOperationAuthorizationBlocked,
    VaultLifecycleDisabled,
    PersistenceDisabled,
    ProductionSyncDisabled,
    UserConsentCannotOverride,
    WarningOnlyEvidenceNonAuthorizing,
    TestOnlyEvidenceNonAuthorizing,
    ReleaseEvidenceNonAuthorizing,
    FutureBranchApprovalClaimRejected,
    SyntheticSafeIdApprovalClaimRejected,
    SourceSetPlacementApprovalClaimRejected,
    SourceGuardCoverageClaimRejected,
    RedactionReviewClaimRejected,
    ImplementationReviewCompleteClaimRejected,
    ProviderImplementationClaimRejected,
    RegistryEntryClaimRejected,
    FactoryReachabilityRejected,
    DispatcherReachabilityRejected,
    ExecutorTargetRejected,
    ProviderKatExecutorRejected,
    ProviderOperationRejected,
    PersistenceReachabilityRejected,
    SettingsReachabilityRejected,
    UiReachabilityRejected,
    MainnetReachabilityRejected,
    MainnetDisabled,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteRequiredEvidenceClass {
    IdentityDecisionEvidence,
    IdentityIsolationEvidence,
    SyntheticNamespaceEvidence,
    SourceSetConfinementEvidence,
    ImplementationDecisionEvidence,
    FutureBranchApprovalEvidence,
    SyntheticSafeIdReviewEvidence,
    TestOnlyNamespaceReviewEvidence,
    SourceSetPlacementReviewEvidence,
    ProductionSourceAbsenceEvidence,
    RuntimeReachabilityAbsenceEvidence,
    ProviderSelectionAbsenceEvidence,
    RegistryFactoryDispatcherAbsenceEvidence,
    ExecutorAndKatAbsenceEvidence,
    ProviderOperationAbsenceEvidence,
    VaultStorageSyncAbsenceEvidence,
    BackendSettingsUiAbsenceEvidence,
    SigningBroadcastingNetworkAbsenceEvidence,
    SourceGuardCoverageEvidence,
    RedactionLeakageReviewEvidence,
    FutureImplementationReviewEvidence,
    NonAuthorizationEvidence,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenShortcut {
    BranchNameImpliesApproval,
    PriorIdentityDecisionImpliesImplementation,
    PriorIsolationGuardImpliesImplementation,
    SyntheticSafeIdImpliesImplementation,
    SourceSetConfinementImpliesImplementation,
    ImplementationDecisionImpliesApproval,
    SourceGuardPassingImpliesImplementation,
    DocumentationImpliesImplementation,
    TestOnlyEvidenceImpliesProductionAuthorization,
    UserConsentImpliesImplementation,
    WarningOnlyEvidenceImpliesImplementation,
    ReleaseClaimImpliesImplementation,
    AbsenceEvidenceImpliesImplementation,
    FutureReviewLabelImpliesImplementation,
    ProviderSelectionFallbackImpliesImplementation,
    DisabledProviderImpliesTestProvider,
    PublicVectorEvidenceImpliesProviderReadiness,
    KATHarnessEvidenceImpliesProviderReadiness,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteForbiddenPromotionPath {
    PrerequisiteAuditToProviderImplementation,
    PrerequisiteAuditToTestOnlyProviderIdentityImplementation,
    PrerequisiteAuditToProductionProviderIdentityImplementation,
    PrerequisiteAuditToProviderSelection,
    PrerequisiteAuditToProductionProviderSelectable,
    PrerequisiteAuditToRegistryEntry,
    PrerequisiteAuditToFactory,
    PrerequisiteAuditToDispatcher,
    PrerequisiteAuditToExecutorTarget,
    PrerequisiteAuditToProviderKatExecutor,
    PrerequisiteAuditToProviderOperation,
    PrerequisiteAuditToRuntimeRandomness,
    PrerequisiteAuditToKdf,
    PrerequisiteAuditToHkdf,
    PrerequisiteAuditToHmac,
    PrerequisiteAuditToAead,
    PrerequisiteAuditToKeyGeneration,
    PrerequisiteAuditToKeysetStorage,
    PrerequisiteAuditToVaultCreation,
    PrerequisiteAuditToVaultUnlock,
    PrerequisiteAuditToVaultSession,
    PrerequisiteAuditToVaultPersistence,
    PrerequisiteAuditToSecureStorageSuccess,
    PrerequisiteAuditToSecureMetadataSuccess,
    PrerequisiteAuditToManifestReadWrite,
    PrerequisiteAuditToMigration,
    PrerequisiteAuditToProductionSync,
    PrerequisiteAuditToBackendClient,
    PrerequisiteAuditToBdkWalletState,
    PrerequisiteAuditToSettingsCodec,
    PrerequisiteAuditToUiSurface,
    PrerequisiteAuditToSigning,
    PrerequisiteAuditToBroadcasting,
    PrerequisiteAuditToTorTransport,
    PrerequisiteAuditToNostrParsing,
    PrerequisiteAuditToPublicEndpointDefault,
    PrerequisiteAuditToMainnet,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteEvidenceSource {
    TestOnlyProviderIdentityDecision,
    TestOnlyProviderIdentityIsolationGuard,
    SyntheticIdentityNamespace,
    TestOnlyProviderIdentitySourceSetConfinement,
    TestOnlyProviderIdentityImplementationDecision,
    ProviderSelectionBoundary,
    ProviderRegistryIsolationGuard,
    ProviderFactoryIsolationBoundary,
    ProviderOperationDispatchIsolation,
    ProviderKatExecutionIsolation,
    ProviderSelectionPromotionBlockers,
    ProductionProviderAcceptanceContract,
    SourceGuardEvidence,
    RedactionLeakageBoundary,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteRedactionClass {
    RedactedSourceSetReference,
    RedactedPlacementReference,
    RedactedIdentityReference,
    RedactedProviderReference,
    RedactedStorageReference,
    RedactedBackendReference,
    RedactedEndpointReference,
    RedactedWalletReference,
    RedactedCryptoReference,
    RedactedFutureReviewReference,
    SafePolicyIdOnly,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteFutureReviewRequirement {
    ExplicitFutureBranchApproval,
    TestOnlyProviderIdentityImplementationDesign,
    SyntheticSafeIdReview,
    TestOnlyNamespaceReview,
    SourceSetPlacementReview,
    ProductionSourceAbsenceProof,
    ProviderSelectionAbsenceProof,
    RegistryFactoryDispatcherAbsenceProof,
    ExecutorTargetAndKatExecutorAbsenceProof,
    ProviderOperationAbsenceProof,
    VaultLifecycleAndPersistenceAbsenceProof,
    SecureStorageAndMetadataAbsenceProof,
    BackendBdkSettingsUiAbsenceProof,
    SigningBroadcastingTorNostrEndpointAbsenceProof,
    SourceGuardCoverageReview,
    RedactionAndLeakageReview,
    NonAuthorizationReview,
    MainnetNonAuthorizationReview,
}

data class SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteCategoryRow(
    val category: SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteCategory,
    val modeled: Boolean,
    val state: SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteState,
    val nonAuthorizing: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItemRow(
    val item: SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem,
    val category: SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteCategory,
    val state: SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteState,
    val modeled: Boolean,
    val satisfiedNow: Boolean,
    val priorEvidencePresent: Boolean,
    val negativeAbsenceEvidence: Boolean,
    val sourceGuardEvidenceOnly: Boolean,
    val implementationAuthorization: Boolean,
    val productionPromotionAuthorization: Boolean,
    val blockers: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker>,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteRequiredEvidenceRow(
    val evidenceClass: SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteRequiredEvidenceClass,
    val modeled: Boolean,
    val presentAsModelEvidence: Boolean,
    val futureRequired: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenShortcutRow(
    val shortcut: SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenShortcut,
    val forbidden: Boolean,
    val currentBridgePresent: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteForbiddenPromotionPathRow(
    val promotionRoute: SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteForbiddenPromotionPath,
    val forbidden: Boolean,
    val currentBridgePresent: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteEvidenceSourceRow(
    val evidenceSource: SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteEvidenceSource,
    val included: Boolean,
    val modeled: Boolean,
    val nonAuthorizing: Boolean,
    val authorizesImplementation: Boolean,
    val authorizesProductionPromotion: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteFutureReviewRow(
    val requirement: SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteFutureReviewRequirement,
    val futureRequired: Boolean,
    val satisfiedNow: Boolean,
    val authorizesCurrentImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditCapabilities(
    val prerequisiteAuditAuthorizesImplementation: Boolean,
    val implementationDecisionAuthorizesImplementation: Boolean,
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
    val canUseForStorageNamespace: Boolean,
    val canUseForStoragePath: Boolean,
    val canUseForManifestReadWrite: Boolean,
    val canUseForMigration: Boolean,
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
        val Current = SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditCapabilities(
            prerequisiteAuditAuthorizesImplementation = false,
            implementationDecisionAuthorizesImplementation = false,
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
            canUseForStorageNamespace = false,
            canUseForStoragePath = false,
            canUseForManifestReadWrite = false,
            canUseForMigration = false,
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

data class SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditRequest(
    val includePriorIdentityDecisionEvidence: Boolean = true,
    val includePriorIsolationEvidence: Boolean = true,
    val includePriorNamespaceEvidence: Boolean = true,
    val includePriorSourceSetConfinementEvidence: Boolean = true,
    val includePriorImplementationDecisionEvidence: Boolean = true,
    val userConsentOverrideRequested: Boolean = false,
    val warningOnlyEvidenceClaimed: Boolean = false,
    val testOnlyEvidenceClaimedAsProductionPromotion: Boolean = false,
    val releaseEvidenceClaimed: Boolean = false,
    val futureBranchApprovalClaimed: Boolean = false,
    val syntheticSafeIdApprovalClaimed: Boolean = false,
    val sourceSetPlacementApprovalClaimed: Boolean = false,
    val sourceGuardCoverageClaimed: Boolean = false,
    val redactionReviewCompleteClaimed: Boolean = false,
    val implementationReviewCompleteClaimed: Boolean = false,
    val providerImplementationClaimed: Boolean = false,
    val registryEntryClaimed: Boolean = false,
    val factoryReachabilityClaimed: Boolean = false,
    val dispatcherReachabilityClaimed: Boolean = false,
    val executorTargetClaimed: Boolean = false,
    val providerKatExecutorClaimed: Boolean = false,
    val providerOperationClaimed: Boolean = false,
    val persistenceReachabilityClaimed: Boolean = false,
    val settingsReachabilityClaimed: Boolean = false,
    val uiReachabilityClaimed: Boolean = false,
    val mainnetReachabilityClaimed: Boolean = false,
) {
    override fun toString(): String = "PrerequisiteAuditRequest(redacted=true, claims=modeled)"
}

data class SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditEvidence(
    val policyId: String,
    val policyVersion: Int,
    val prerequisiteAuditModeled: Boolean,
    val stillDisabled: Boolean,
    val priorIdentityDecisionEvidenceIncluded: Boolean,
    val priorIsolationEvidenceIncluded: Boolean,
    val priorNamespaceEvidenceIncluded: Boolean,
    val priorSourceSetConfinementEvidenceIncluded: Boolean,
    val priorImplementationDecisionEvidenceIncluded: Boolean,
    val negativeAbsenceEvidenceModeled: Boolean,
    val negativeAbsenceEvidenceAuthorizesImplementation: Boolean,
    val docsMayDescribeFutureReview: Boolean,
    val testsMayAssertBlockedAudit: Boolean,
    val statuses: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditStatus>,
    val outcomes: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditOutcome>,
    val categoryRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteCategoryRow>,
    val prerequisiteItemRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItemRow>,
    val requiredEvidenceRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteRequiredEvidenceRow>,
    val forbiddenShortcutRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenShortcutRow>,
    val forbiddenPromotionPathRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteForbiddenPromotionPathRow>,
    val evidenceSourceRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteEvidenceSourceRow>,
    val futureReviewRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteFutureReviewRow>,
    val blockers: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker>,
    val redactionClasses: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteRedactionClass>,
    val capabilities: SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditCapabilities,
)

object SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditPolicy {
    const val POLICY_ID: String =
        "skald-vault-v1-test-only-provider-identity-implementation-prerequisite-audit-v1"
    const val POLICY_VERSION: Int = 1

    fun currentPrerequisiteAuditEvidence():
        SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditEvidence =
        evaluatePrerequisiteAudit()

    fun evaluatePrerequisiteAudit(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditRequest =
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditRequest(),
    ): SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditEvidence {
        val itemRows = currentPrerequisiteItemRows(request)
        return SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditEvidence(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            prerequisiteAuditModeled = true,
            stillDisabled = true,
            priorIdentityDecisionEvidenceIncluded = request.includePriorIdentityDecisionEvidence,
            priorIsolationEvidenceIncluded = request.includePriorIsolationEvidence,
            priorNamespaceEvidenceIncluded = request.includePriorNamespaceEvidence,
            priorSourceSetConfinementEvidenceIncluded = request.includePriorSourceSetConfinementEvidence,
            priorImplementationDecisionEvidenceIncluded = request.includePriorImplementationDecisionEvidence,
            negativeAbsenceEvidenceModeled = true,
            negativeAbsenceEvidenceAuthorizesImplementation = false,
            docsMayDescribeFutureReview = true,
            testsMayAssertBlockedAudit = true,
            statuses = SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditStatus.entries.toSet(),
            outcomes = SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditOutcome.entries.toSet(),
            categoryRows = currentCategoryRows(itemRows),
            prerequisiteItemRows = itemRows,
            requiredEvidenceRows = currentRequiredEvidenceRows(request),
            forbiddenShortcutRows = currentForbiddenShortcutRows(),
            forbiddenPromotionPathRows = currentForbiddenPromotionPathRows(),
            evidenceSourceRows = currentEvidenceSourceRows(request),
            futureReviewRows = currentFutureReviewRows(),
            blockers = baseBlockers() + requestBlockers(request),
            redactionClasses =
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteRedactionClass.entries.toSet(),
            capabilities = SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditCapabilities.Current,
        )
    }

    private fun currentCategoryRows(
        itemRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItemRow>,
    ): List<SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteCategoryRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteCategory.entries.map { category ->
            val rows = itemRows.filter { it.category == category }
            val state = when {
                rows.all { it.negativeAbsenceEvidence } ->
                    SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteState.NegativeAbsenceEvidenceOnly
                rows.all { it.priorEvidencePresent } ->
                    SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteState.PriorEvidencePresentNonAuthorizing
                rows.all { it.sourceGuardEvidenceOnly } ->
                    SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteState.SourceGuardEvidenceOnly
                rows.any { it.state == SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteState.Blocked } ->
                    SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteState.Blocked
                else -> SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteState.FutureReviewRequired
            }
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteCategoryRow(
                category = category,
                modeled = true,
                state = state,
                nonAuthorizing = true,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentPrerequisiteItemRows(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditRequest,
    ): List<SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItemRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.entries.map { item ->
            val priorEvidencePresent = priorEvidencePresent(item, request)
            val negativeAbsenceEvidence = item in negativeAbsenceItems()
            val sourceGuardEvidenceOnly =
                item == SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.SourceGuardCoverageComplete
            val satisfiedNow = priorEvidencePresent || negativeAbsenceEvidence || sourceGuardEvidenceOnly
            val state = when {
                priorEvidencePresent ->
                    SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteState.PriorEvidencePresentNonAuthorizing
                negativeAbsenceEvidence ->
                    SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteState.NegativeAbsenceEvidenceOnly
                sourceGuardEvidenceOnly ->
                    SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteState.SourceGuardEvidenceOnly
                item in blockedApprovalItems() ->
                    SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteState.Blocked
                else -> SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteState.FutureReviewRequired
            }
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItemRow(
                item = item,
                category = categoryFor(item),
                state = state,
                modeled = true,
                satisfiedNow = satisfiedNow,
                priorEvidencePresent = priorEvidencePresent,
                negativeAbsenceEvidence = negativeAbsenceEvidence,
                sourceGuardEvidenceOnly = sourceGuardEvidenceOnly,
                implementationAuthorization = false,
                productionPromotionAuthorization = false,
                blockers = itemBlockers(item),
                label = redactedLabel(),
            )
        }

    private fun currentRequiredEvidenceRows(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditRequest,
    ): List<SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteRequiredEvidenceRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteRequiredEvidenceClass.entries.map { evidenceClass ->
            val presentAsModelEvidence = when (evidenceClass) {
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteRequiredEvidenceClass.IdentityDecisionEvidence ->
                    request.includePriorIdentityDecisionEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteRequiredEvidenceClass.IdentityIsolationEvidence ->
                    request.includePriorIsolationEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteRequiredEvidenceClass.SyntheticNamespaceEvidence ->
                    request.includePriorNamespaceEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteRequiredEvidenceClass.SourceSetConfinementEvidence ->
                    request.includePriorSourceSetConfinementEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteRequiredEvidenceClass.ImplementationDecisionEvidence ->
                    request.includePriorImplementationDecisionEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteRequiredEvidenceClass.ProductionSourceAbsenceEvidence,
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteRequiredEvidenceClass.RuntimeReachabilityAbsenceEvidence,
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteRequiredEvidenceClass.ProviderSelectionAbsenceEvidence,
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteRequiredEvidenceClass.RegistryFactoryDispatcherAbsenceEvidence,
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteRequiredEvidenceClass.ExecutorAndKatAbsenceEvidence,
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteRequiredEvidenceClass.ProviderOperationAbsenceEvidence,
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteRequiredEvidenceClass.VaultStorageSyncAbsenceEvidence,
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteRequiredEvidenceClass.BackendSettingsUiAbsenceEvidence,
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteRequiredEvidenceClass.SigningBroadcastingNetworkAbsenceEvidence,
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteRequiredEvidenceClass.SourceGuardCoverageEvidence,
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteRequiredEvidenceClass.NonAuthorizationEvidence -> true
                else -> false
            }
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteRequiredEvidenceRow(
                evidenceClass = evidenceClass,
                modeled = true,
                presentAsModelEvidence = presentAsModelEvidence,
                futureRequired = !presentAsModelEvidence ||
                    evidenceClass in futureReviewEvidenceClasses(),
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentForbiddenShortcutRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenShortcutRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenShortcut.entries.map { shortcut ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenShortcutRow(
                shortcut = shortcut,
                forbidden = true,
                currentBridgePresent = false,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentForbiddenPromotionPathRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteForbiddenPromotionPathRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteForbiddenPromotionPath.entries.map { route ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteForbiddenPromotionPathRow(
                promotionRoute = route,
                forbidden = true,
                currentBridgePresent = false,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentEvidenceSourceRows(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditRequest,
    ): List<SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteEvidenceSourceRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteEvidenceSource.entries.map { source ->
            val included = when (source) {
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteEvidenceSource.TestOnlyProviderIdentityDecision ->
                    request.includePriorIdentityDecisionEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteEvidenceSource.TestOnlyProviderIdentityIsolationGuard ->
                    request.includePriorIsolationEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteEvidenceSource.SyntheticIdentityNamespace ->
                    request.includePriorNamespaceEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteEvidenceSource.TestOnlyProviderIdentitySourceSetConfinement ->
                    request.includePriorSourceSetConfinementEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteEvidenceSource.TestOnlyProviderIdentityImplementationDecision ->
                    request.includePriorImplementationDecisionEvidence
                else -> true
            }
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteEvidenceSourceRow(
                evidenceSource = source,
                included = included,
                modeled = true,
                nonAuthorizing = true,
                authorizesImplementation = false,
                authorizesProductionPromotion = false,
                label = redactedLabel(),
            )
        }

    private fun currentFutureReviewRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteFutureReviewRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteFutureReviewRequirement.entries.map { requirement ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteFutureReviewRow(
                requirement = requirement,
                futureRequired = true,
                satisfiedNow = false,
                authorizesCurrentImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun priorEvidencePresent(
        item: SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem,
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditRequest,
    ): Boolean =
        when (item) {
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.IdentityDecisionEvidenceReviewed ->
                request.includePriorIdentityDecisionEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.IdentityIsolationEvidenceReviewed ->
                request.includePriorIsolationEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.SyntheticNamespaceEvidenceReviewed ->
                request.includePriorNamespaceEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.SourceSetConfinementEvidenceReviewed ->
                request.includePriorSourceSetConfinementEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.ImplementationDecisionEvidenceReviewed ->
                request.includePriorImplementationDecisionEvidence
            else -> false
        }

    private fun negativeAbsenceItems(): Set<SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem> =
        setOf(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.ProductionSourceAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.ProviderSelectionAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.RegistryAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.FactoryAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.DispatcherAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.ExecutorTargetAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.ProviderKatExecutorAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.ProviderOperationAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.VaultLifecycleAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.PersistenceAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.SecureStorageAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.SecureMetadataAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.BackendClientAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.BdkWalletStateAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.SettingsCodecAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.UiSurfaceAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.SigningAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.BroadcastingAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.TorAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.NostrAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.PublicEndpointDefaultAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.MainnetAbsenceProven,
        )

    private fun blockedApprovalItems(): Set<SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem> =
        setOf(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.ExplicitFutureBranchApprovalRecorded,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.SyntheticSafeIdApprovedForReview,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.TestOnlyNamespaceApprovedForReview,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.NonProductionSourceSetPlacementApprovedForReview,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.RedactionAndLeakageReviewComplete,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.FutureTestOnlyImplementationReviewComplete,
        )

    private fun futureReviewEvidenceClasses():
        Set<SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteRequiredEvidenceClass> =
        setOf(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteRequiredEvidenceClass.FutureBranchApprovalEvidence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteRequiredEvidenceClass.SyntheticSafeIdReviewEvidence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteRequiredEvidenceClass.TestOnlyNamespaceReviewEvidence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteRequiredEvidenceClass.SourceSetPlacementReviewEvidence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteRequiredEvidenceClass.RedactionLeakageReviewEvidence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteRequiredEvidenceClass.FutureImplementationReviewEvidence,
        )

    private fun itemBlockers(
        item: SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem,
    ): Set<SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker> =
        when (item) {
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.ExplicitFutureBranchApprovalRecorded ->
                setOf(SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.FutureBranchApprovalMissing)
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.SyntheticSafeIdApprovedForReview ->
                setOf(SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.SyntheticSafeIdNotApproved)
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.TestOnlyNamespaceApprovedForReview ->
                setOf(SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.TestOnlyNamespaceNotApproved)
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.NonProductionSourceSetPlacementApprovedForReview ->
                setOf(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker
                        .NonProductionSourceSetPlacementNotApproved,
                )
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.RedactionAndLeakageReviewComplete ->
                setOf(SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.RedactionReviewIncomplete)
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.FutureTestOnlyImplementationReviewComplete ->
                setOf(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker
                        .FutureImplementationReviewIncomplete,
                )
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.SourceGuardCoverageComplete ->
                setOf(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker
                        .SourceGuardEvidenceNonAuthorizing,
                )
            else -> setOf(
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.PriorEvidenceNonAuthorizing,
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.NegativeAbsenceEvidenceNonAuthorizing,
            )
        }

    private fun categoryFor(
        item: SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem,
    ): SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteCategory =
        when (item) {
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.ExplicitFutureBranchApprovalRecorded ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteCategory.BranchApproval
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.IdentityDecisionEvidenceReviewed,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.IdentityIsolationEvidenceReviewed,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.SyntheticNamespaceEvidenceReviewed,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.SourceSetConfinementEvidenceReviewed,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.ImplementationDecisionEvidenceReviewed ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteCategory.PriorEvidenceReview
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.SyntheticSafeIdApprovedForReview,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.TestOnlyNamespaceApprovedForReview ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteCategory.SyntheticIdentityNamespace
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.NonProductionSourceSetPlacementApprovedForReview ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteCategory.SourceSetPlacement
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.ProductionSourceAbsenceProven ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteCategory.ProductionSourceAbsence
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.ProviderSelectionAbsenceProven ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteCategory.ProviderSelectionAbsence
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.RegistryAbsenceProven ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteCategory.RegistryAbsence
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.FactoryAbsenceProven ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteCategory.FactoryAbsence
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.DispatcherAbsenceProven ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteCategory.DispatcherAbsence
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.ExecutorTargetAbsenceProven ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteCategory.ExecutorTargetAbsence
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.ProviderKatExecutorAbsenceProven ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteCategory.ProviderKatExecutorAbsence
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.ProviderOperationAbsenceProven ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteCategory.ProviderOperationAbsence
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.VaultLifecycleAbsenceProven ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteCategory.VaultLifecycleAbsence
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.PersistenceAbsenceProven ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteCategory.PersistenceAbsence
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.SecureStorageAbsenceProven ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteCategory.SecureStorageAbsence
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.SecureMetadataAbsenceProven ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteCategory.SecureMetadataAbsence
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.BackendClientAbsenceProven ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteCategory.BackendClientAbsence
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.BdkWalletStateAbsenceProven ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteCategory.BdkWalletStateAbsence
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.SettingsCodecAbsenceProven ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteCategory.SettingsCodecAbsence
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.UiSurfaceAbsenceProven ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteCategory.UiSurfaceAbsence
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.SigningAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.BroadcastingAbsenceProven ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteCategory.SigningBroadcastingAbsence
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.TorAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.NostrAbsenceProven ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteCategory.TorNostrAbsence
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.PublicEndpointDefaultAbsenceProven ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteCategory.PublicEndpointAbsence
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.MainnetAbsenceProven ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteCategory.MainnetAbsence
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.SourceGuardCoverageComplete ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteCategory.SourceGuardCoverage
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.RedactionAndLeakageReviewComplete ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteCategory.RedactionLeakageReview
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteItem.FutureTestOnlyImplementationReviewComplete ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteCategory.FutureImplementationReview
        }

    private fun baseBlockers(): Set<SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker> =
        setOf(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.CurrentAuditBlocked,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.PrerequisitesIncomplete,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.FutureBranchApprovalMissing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.SyntheticSafeIdNotApproved,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.TestOnlyNamespaceNotApproved,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.NonProductionSourceSetPlacementNotApproved,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.RedactionReviewIncomplete,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.FutureImplementationReviewIncomplete,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.PriorEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.NegativeAbsenceEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.SourceGuardEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.ImplementationDecisionNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.NoTestOnlyProviderIdentityImplementation,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.NoProductionProviderIdentityImplementation,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.NoProviderImplementation,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.NoProviderFactory,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.NoProviderDispatcher,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.NoNonDisabledRegistryEntry,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.NoExecutorTarget,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.NoProviderKatExecutor,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.NoRunnableExecutorInterface,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.ProviderSelectionDisabledProviderOnly,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.ProductionProviderSelectableFalse,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.ProviderOperationAuthorizationBlocked,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.VaultLifecycleDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.PersistenceDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.ProductionSyncDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.UserConsentCannotOverride,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.WarningOnlyEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.TestOnlyEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.ReleaseEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.MainnetDisabled,
        )

    private fun requestBlockers(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditRequest,
    ): Set<SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker> =
        buildSet {
            if (request.userConsentOverrideRequested) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.UserConsentCannotOverride)
            }
            if (request.warningOnlyEvidenceClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker
                        .WarningOnlyEvidenceNonAuthorizing,
                )
            }
            if (request.testOnlyEvidenceClaimedAsProductionPromotion) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker
                        .TestOnlyEvidenceNonAuthorizing,
                )
            }
            if (request.releaseEvidenceClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.ReleaseEvidenceNonAuthorizing)
            }
            if (request.futureBranchApprovalClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.FutureBranchApprovalClaimRejected)
            }
            if (request.syntheticSafeIdApprovalClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.SyntheticSafeIdApprovalClaimRejected)
            }
            if (request.sourceSetPlacementApprovalClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker
                        .SourceSetPlacementApprovalClaimRejected,
                )
            }
            if (request.sourceGuardCoverageClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.SourceGuardCoverageClaimRejected)
            }
            if (request.redactionReviewCompleteClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.RedactionReviewClaimRejected)
            }
            if (request.implementationReviewCompleteClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker
                        .ImplementationReviewCompleteClaimRejected,
                )
            }
            if (request.providerImplementationClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.ProviderImplementationClaimRejected)
            }
            if (request.registryEntryClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.RegistryEntryClaimRejected)
            }
            if (request.factoryReachabilityClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.FactoryReachabilityRejected)
            }
            if (request.dispatcherReachabilityClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.DispatcherReachabilityRejected)
            }
            if (request.executorTargetClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.ExecutorTargetRejected)
            }
            if (request.providerKatExecutorClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.ProviderKatExecutorRejected)
            }
            if (request.providerOperationClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.ProviderOperationRejected)
            }
            if (request.persistenceReachabilityClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.PersistenceReachabilityRejected)
            }
            if (request.settingsReachabilityClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.SettingsReachabilityRejected)
            }
            if (request.uiReachabilityClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.UiReachabilityRejected)
            }
            if (request.mainnetReachabilityClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteBlocker.MainnetReachabilityRejected)
            }
        }

    private fun redactedLabel(): SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditSafeLabel =
        SkaldVaultV1TestOnlyProviderIdentityImplementationPrerequisiteAuditSafeLabel("redacted")
}
