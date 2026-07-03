package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionSafeLabel(
    val value: String,
) {
    override fun toString(): String = "RedactedLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionStatus {
    ScopeDecisionModeled,
    StillDisabled,
    CurrentScopeBlocked,
    ScopeReviewRequired,
    ImplementationNotAuthorized,
    ProductionPromotionNotAuthorized,
    MainnetNotAuthorized,
    PriorIdentityDecisionEvidenceNonAuthorizing,
    PriorIsolationEvidenceNonAuthorizing,
    PriorNamespaceEvidenceNonAuthorizing,
    PriorSourceSetConfinementEvidenceNonAuthorizing,
    PriorImplementationDecisionEvidenceNonAuthorizing,
    PriorPrerequisiteAuditEvidenceNonAuthorizing,
    AllowedFutureScopeItemsModeled,
    AllowedFutureScopeItemsNonAuthorizing,
    ForbiddenScopeItemsModeled,
    ProviderSelectionDisabledProviderOnly,
    ProductionProviderSelectableFalse,
    VaultLifecycleBlocked,
    PersistenceBlocked,
    ProductionSyncBlocked,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationScopeOutcome {
    CurrentScopeBlocked,
    ScopeReviewRequired,
    ImplementationNotAuthorized,
    ProductionPromotionNotAuthorized,
    MainnetNotAuthorized,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategory {
    ModelOnlyScope,
    FutureTestOnlyIdentityScope,
    SourceSetPlacementScope,
    SyntheticNamespaceScope,
    NonRuntimeEvidenceScope,
    ProductionForbiddenScope,
    RuntimeReachabilityForbiddenScope,
    ProviderSelectionForbiddenScope,
    RegistryFactoryDispatcherForbiddenScope,
    ExecutorTargetForbiddenScope,
    ProviderKatExecutorForbiddenScope,
    ProviderOperationForbiddenScope,
    VaultLifecycleForbiddenScope,
    PersistenceForbiddenScope,
    SecureStorageForbiddenScope,
    SecureMetadataForbiddenScope,
    BackendClientForbiddenScope,
    BdkWalletStateForbiddenScope,
    SettingsCodecForbiddenScope,
    UiSurfaceForbiddenScope,
    SigningBroadcastingForbiddenScope,
    TorNostrForbiddenScope,
    PublicEndpointForbiddenScope,
    MainnetForbiddenScope,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationAllowedFutureScopeItem {
    FutureBranchMayRequestTestOnlyIdentityImplementationReview,
    FutureBranchMayProposeSyntheticSafeId,
    FutureBranchMayProposeTestOnlyNamespace,
    FutureBranchMayProposeNonProductionSourceSetPlacement,
    FutureBranchMayAddTestSourceOnlyFixturesForReview,
    FutureBranchMayAddModelOnlyEvidenceRows,
    FutureBranchMayAddSourceGuards,
    FutureBranchMayAddRedactionTests,
    FutureBranchMayDocumentBlockedImplementation,
    FutureBranchMayProveProductionAbsence,
    FutureBranchMayProveRuntimeReachabilityAbsence,
    FutureBranchMayProveProviderSelectionAbsence,
    FutureBranchMayProveRegistryFactoryDispatcherAbsence,
    FutureBranchMayProveExecutorTargetAbsence,
    FutureBranchMayProveVaultLifecycleAbsence,
    FutureBranchMayProvePersistenceAbsence,
    FutureBranchMayProveMainnetAbsence,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem {
    ImplementProviderIdentityNow,
    ImplementProductionProviderIdentity,
    ImplementVaultCryptoProvider,
    AddProviderFactory,
    AddProviderDispatcher,
    AddProviderRegistryEntry,
    AddExecutorTarget,
    AddProviderKatExecutor,
    AddRunnableExecutorInterface,
    ExecuteProviderOperations,
    ExecuteRuntimeRandomness,
    ExecuteKdf,
    ExecuteHkdf,
    ExecuteHmac,
    ExecuteAead,
    GenerateKeys,
    StoreKeysets,
    CreateVault,
    UnlockVault,
    CreateVaultSession,
    PersistVault,
    SucceedSecureStorage,
    SucceedSecureMetadataStorage,
    WriteStorageNamespace,
    WriteStoragePath,
    ReadOrWriteManifest,
    RunMigration,
    StartProductionSync,
    CreateBackendClient,
    CreateBdkWalletState,
    WriteSettingsCodec,
    ExposeUiSurface,
    SignTransactions,
    BroadcastTransactions,
    StartTorTransport,
    ParseNostrSecrets,
    AddPublicEndpointDefault,
    EnableMainnet,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker {
    CurrentScopeBlocked,
    ScopeReviewRequired,
    ImplementationNotAuthorized,
    ProductionPromotionNotAuthorized,
    MainnetNotAuthorized,
    AllowedFutureScopeNonAuthorizing,
    PriorIdentityDecisionEvidenceNonAuthorizing,
    PriorIsolationEvidenceNonAuthorizing,
    PriorNamespaceEvidenceNonAuthorizing,
    PriorSourceSetConfinementEvidenceNonAuthorizing,
    PriorImplementationDecisionEvidenceNonAuthorizing,
    PriorPrerequisiteAuditEvidenceNonAuthorizing,
    PrerequisiteCompletionMissing,
    FutureBranchApprovalMissing,
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
    PrerequisiteCompletionClaimRejected,
    ImplementationScopeApprovalClaimRejected,
    AllowedFutureScopeAsCurrentAuthorizationRejected,
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

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationScopeRequiredEvidenceClass {
    IdentityDecisionEvidence,
    IdentityIsolationEvidence,
    SyntheticNamespaceEvidence,
    SourceSetConfinementEvidence,
    ImplementationDecisionEvidence,
    PrerequisiteAuditEvidence,
    FutureBranchApprovalEvidence,
    ScopeReviewEvidence,
    SyntheticSafeIdReviewEvidence,
    TestOnlyNamespaceReviewEvidence,
    SourceSetPlacementReviewEvidence,
    ProductionAbsenceEvidence,
    RuntimeReachabilityAbsenceEvidence,
    ProviderSelectionAbsenceEvidence,
    RegistryFactoryDispatcherAbsenceEvidence,
    ExecutorAndKatAbsenceEvidence,
    ProviderOperationAbsenceEvidence,
    VaultStorageSyncAbsenceEvidence,
    BackendSettingsUiAbsenceEvidence,
    SigningBroadcastingNetworkAbsenceEvidence,
    MainnetAbsenceEvidence,
    NonAuthorizationEvidence,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationScopeForbiddenShortcut {
    ScopeDecisionImpliesImplementation,
    ScopeDecisionImpliesPrerequisitesSatisfied,
    ScopeDecisionImpliesFutureBranchApproved,
    AllowedFutureScopeImpliesCurrentAuthorization,
    PriorIdentityDecisionImpliesScopeApproval,
    PriorIsolationGuardImpliesScopeApproval,
    SyntheticNamespaceImpliesScopeApproval,
    SourceSetConfinementImpliesScopeApproval,
    ImplementationDecisionImpliesScopeApproval,
    PrerequisiteAuditImpliesScopeApproval,
    DocumentationImpliesScopeApproval,
    TestOnlyEvidenceImpliesScopeApproval,
    UserConsentImpliesScopeApproval,
    WarningOnlyEvidenceImpliesScopeApproval,
    ReleaseClaimImpliesScopeApproval,
    AbsenceEvidenceImpliesScopeApproval,
    DisabledProviderImpliesTestProvider,
    KATHarnessEvidenceImpliesIdentityImplementationReadiness,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationScopeForbiddenPromotionPath {
    ScopeDecisionToProviderImplementation,
    ScopeDecisionToTestOnlyProviderIdentityImplementation,
    ScopeDecisionToProductionProviderIdentityImplementation,
    ScopeDecisionToProviderSelection,
    ScopeDecisionToProductionProviderSelectable,
    ScopeDecisionToRegistryEntry,
    ScopeDecisionToFactory,
    ScopeDecisionToDispatcher,
    ScopeDecisionToExecutorTarget,
    ScopeDecisionToProviderKatExecutor,
    ScopeDecisionToProviderOperation,
    ScopeDecisionToRuntimeRandomness,
    ScopeDecisionToKdf,
    ScopeDecisionToHkdf,
    ScopeDecisionToHmac,
    ScopeDecisionToAead,
    ScopeDecisionToKeyGeneration,
    ScopeDecisionToKeysetStorage,
    ScopeDecisionToVaultCreation,
    ScopeDecisionToVaultUnlock,
    ScopeDecisionToVaultSession,
    ScopeDecisionToVaultPersistence,
    ScopeDecisionToSecureStorageSuccess,
    ScopeDecisionToSecureMetadataSuccess,
    ScopeDecisionToManifestReadWrite,
    ScopeDecisionToMigration,
    ScopeDecisionToProductionSync,
    ScopeDecisionToBackendClient,
    ScopeDecisionToBdkWalletState,
    ScopeDecisionToSettingsCodec,
    ScopeDecisionToUiSurface,
    ScopeDecisionToSigning,
    ScopeDecisionToBroadcasting,
    ScopeDecisionToTorTransport,
    ScopeDecisionToNostrParsing,
    ScopeDecisionToPublicEndpointDefault,
    ScopeDecisionToMainnet,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationScopeEvidenceSource {
    TestOnlyProviderIdentityDecision,
    TestOnlyProviderIdentityIsolationGuard,
    SyntheticIdentityNamespace,
    TestOnlyProviderIdentitySourceSetConfinement,
    TestOnlyProviderIdentityImplementationDecision,
    TestOnlyProviderIdentityImplementationPrerequisiteAudit,
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

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationScopeRedactionClass {
    RedactedSourceSetReference,
    RedactedPlacementReference,
    RedactedIdentityReference,
    RedactedProviderReference,
    RedactedStorageReference,
    RedactedBackendReference,
    RedactedEndpointReference,
    RedactedWalletReference,
    RedactedCryptoReference,
    RedactedPrerequisiteReference,
    RedactedFutureReviewReference,
    RedactedScopeReference,
    SafePolicyIdOnly,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationScopeFutureReviewRequirement {
    ExplicitFutureBranchApproval,
    ScopeReviewRequest,
    TestOnlyProviderIdentityImplementationDesign,
    SyntheticSafeIdReview,
    TestOnlyNamespaceReview,
    SourceSetPlacementReview,
    TestSourceFixtureReview,
    ProductionAbsenceProof,
    RuntimeReachabilityAbsenceProof,
    ProviderSelectionAbsenceProof,
    RegistryFactoryDispatcherAbsenceProof,
    ExecutorTargetAndKatExecutorAbsenceProof,
    ProviderOperationAbsenceProof,
    VaultLifecycleAndPersistenceAbsenceProof,
    SecureStorageAndMetadataAbsenceProof,
    BackendBdkSettingsUiAbsenceProof,
    SigningBroadcastingTorNostrEndpointAbsenceProof,
    RedactionAndLeakageReview,
    SourceGuardCoverageReview,
    NonAuthorizationReview,
    MainnetNonAuthorizationReview,
}

data class SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategoryRow(
    val category: SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategory,
    val modeled: Boolean,
    val currentImplementationAllowed: Boolean,
    val futureReviewOnly: Boolean,
    val nonAuthorizing: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationAllowedFutureScopeItemRow(
    val item: SkaldVaultV1TestOnlyProviderIdentityImplementationAllowedFutureScopeItem,
    val category: SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategory,
    val modeled: Boolean,
    val allowedForFutureReview: Boolean,
    val currentAuthorization: Boolean,
    val authorizesImplementation: Boolean,
    val nonAuthorizing: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItemRow(
    val item: SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem,
    val category: SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategory,
    val modeled: Boolean,
    val forbiddenNow: Boolean,
    val currentBridgePresent: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationScopeRequiredEvidenceRow(
    val evidenceClass: SkaldVaultV1TestOnlyProviderIdentityImplementationScopeRequiredEvidenceClass,
    val modeled: Boolean,
    val presentAsModelEvidence: Boolean,
    val futureRequired: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationScopeForbiddenShortcutRow(
    val shortcut: SkaldVaultV1TestOnlyProviderIdentityImplementationScopeForbiddenShortcut,
    val forbidden: Boolean,
    val currentBridgePresent: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationScopeForbiddenPromotionPathRow(
    val promotionRoute: SkaldVaultV1TestOnlyProviderIdentityImplementationScopeForbiddenPromotionPath,
    val forbidden: Boolean,
    val currentBridgePresent: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationScopeEvidenceSourceRow(
    val evidenceSource: SkaldVaultV1TestOnlyProviderIdentityImplementationScopeEvidenceSource,
    val included: Boolean,
    val modeled: Boolean,
    val nonAuthorizing: Boolean,
    val authorizesImplementation: Boolean,
    val authorizesProductionPromotion: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationScopeFutureReviewRow(
    val requirement: SkaldVaultV1TestOnlyProviderIdentityImplementationScopeFutureReviewRequirement,
    val futureRequired: Boolean,
    val satisfiedNow: Boolean,
    val authorizesCurrentImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionCapabilities(
    val scopeDecisionAuthorizesImplementation: Boolean,
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
        val Current = SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionCapabilities(
            scopeDecisionAuthorizesImplementation = false,
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

data class SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionRequest(
    val includePriorIdentityDecisionEvidence: Boolean = true,
    val includePriorIsolationEvidence: Boolean = true,
    val includePriorNamespaceEvidence: Boolean = true,
    val includePriorSourceSetConfinementEvidence: Boolean = true,
    val includePriorImplementationDecisionEvidence: Boolean = true,
    val includePriorPrerequisiteAuditEvidence: Boolean = true,
    val userConsentOverrideRequested: Boolean = false,
    val warningOnlyEvidenceClaimed: Boolean = false,
    val testOnlyEvidenceClaimedAsProductionPromotion: Boolean = false,
    val releaseEvidenceClaimed: Boolean = false,
    val futureBranchApprovalClaimed: Boolean = false,
    val prerequisiteCompletionClaimed: Boolean = false,
    val implementationScopeApprovalClaimed: Boolean = false,
    val allowedFutureScopeClaimedAsCurrentAuthorization: Boolean = false,
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
    override fun toString(): String = "RedactedRequest(claims=modeled)"
}

data class SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionEvidence(
    val policyId: String,
    val policyVersion: Int,
    val scopeDecisionModeled: Boolean,
    val stillDisabled: Boolean,
    val priorIdentityDecisionEvidenceIncluded: Boolean,
    val priorIsolationEvidenceIncluded: Boolean,
    val priorNamespaceEvidenceIncluded: Boolean,
    val priorSourceSetConfinementEvidenceIncluded: Boolean,
    val priorImplementationDecisionEvidenceIncluded: Boolean,
    val priorPrerequisiteAuditEvidenceIncluded: Boolean,
    val allowedFutureScopeItemsModeled: Boolean,
    val forbiddenScopeItemsModeled: Boolean,
    val docsMayDescribeFutureReview: Boolean,
    val testsMayAssertBlockedScope: Boolean,
    val statuses: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionStatus>,
    val outcomes: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationScopeOutcome>,
    val categoryRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategoryRow>,
    val allowedFutureScopeItemRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationAllowedFutureScopeItemRow>,
    val forbiddenScopeItemRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItemRow>,
    val requiredEvidenceRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationScopeRequiredEvidenceRow>,
    val forbiddenShortcutRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationScopeForbiddenShortcutRow>,
    val forbiddenPromotionPathRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationScopeForbiddenPromotionPathRow>,
    val evidenceSourceRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationScopeEvidenceSourceRow>,
    val futureReviewRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationScopeFutureReviewRow>,
    val blockers: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker>,
    val redactionClasses: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationScopeRedactionClass>,
    val capabilities: SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionCapabilities,
)

object SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionPolicy {
    const val POLICY_ID: String = "skald-vault-v1-test-only-provider-identity-implementation-scope-decision-v1"
    const val POLICY_VERSION: Int = 1

    fun currentScopeDecisionEvidence(): SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionEvidence =
        evaluateScopeDecision()

    fun evaluateScopeDecision(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionRequest =
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionRequest(),
    ): SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionEvidence =
        SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionEvidence(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            scopeDecisionModeled = true,
            stillDisabled = true,
            priorIdentityDecisionEvidenceIncluded = request.includePriorIdentityDecisionEvidence,
            priorIsolationEvidenceIncluded = request.includePriorIsolationEvidence,
            priorNamespaceEvidenceIncluded = request.includePriorNamespaceEvidence,
            priorSourceSetConfinementEvidenceIncluded = request.includePriorSourceSetConfinementEvidence,
            priorImplementationDecisionEvidenceIncluded = request.includePriorImplementationDecisionEvidence,
            priorPrerequisiteAuditEvidenceIncluded = request.includePriorPrerequisiteAuditEvidence,
            allowedFutureScopeItemsModeled = true,
            forbiddenScopeItemsModeled = true,
            docsMayDescribeFutureReview = true,
            testsMayAssertBlockedScope = true,
            statuses = SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionStatus.entries.toSet(),
            outcomes = SkaldVaultV1TestOnlyProviderIdentityImplementationScopeOutcome.entries.toSet(),
            categoryRows = currentCategoryRows(),
            allowedFutureScopeItemRows = currentAllowedFutureScopeItemRows(),
            forbiddenScopeItemRows = currentForbiddenScopeItemRows(),
            requiredEvidenceRows = currentRequiredEvidenceRows(request),
            forbiddenShortcutRows = currentForbiddenShortcutRows(),
            forbiddenPromotionPathRows = currentForbiddenPromotionPathRows(),
            evidenceSourceRows = currentEvidenceSourceRows(request),
            futureReviewRows = currentFutureReviewRows(),
            blockers = baseBlockers() + requestBlockers(request),
            redactionClasses =
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeRedactionClass.entries.toSet(),
            capabilities = SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionCapabilities.Current,
        )

    private fun currentCategoryRows(): List<SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategoryRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategory.entries.map { category ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategoryRow(
                category = category,
                modeled = true,
                currentImplementationAllowed = false,
                futureReviewOnly = category in futureReviewCategories(),
                nonAuthorizing = true,
                label = redactedLabel(),
            )
        }

    private fun currentAllowedFutureScopeItemRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationAllowedFutureScopeItemRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationAllowedFutureScopeItem.entries.map { item ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationAllowedFutureScopeItemRow(
                item = item,
                category = allowedFutureScopeCategoryFor(item),
                modeled = true,
                allowedForFutureReview = true,
                currentAuthorization = false,
                authorizesImplementation = false,
                nonAuthorizing = true,
                label = redactedLabel(),
            )
        }

    private fun currentForbiddenScopeItemRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItemRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem.entries.map { item ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItemRow(
                item = item,
                category = forbiddenScopeCategoryFor(item),
                modeled = true,
                forbiddenNow = true,
                currentBridgePresent = false,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentRequiredEvidenceRows(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionRequest,
    ): List<SkaldVaultV1TestOnlyProviderIdentityImplementationScopeRequiredEvidenceRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationScopeRequiredEvidenceClass.entries.map { evidenceClass ->
            val presentAsModelEvidence = when (evidenceClass) {
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeRequiredEvidenceClass.IdentityDecisionEvidence ->
                    request.includePriorIdentityDecisionEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeRequiredEvidenceClass.IdentityIsolationEvidence ->
                    request.includePriorIsolationEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeRequiredEvidenceClass.SyntheticNamespaceEvidence ->
                    request.includePriorNamespaceEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeRequiredEvidenceClass.SourceSetConfinementEvidence ->
                    request.includePriorSourceSetConfinementEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeRequiredEvidenceClass.ImplementationDecisionEvidence ->
                    request.includePriorImplementationDecisionEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeRequiredEvidenceClass.PrerequisiteAuditEvidence ->
                    request.includePriorPrerequisiteAuditEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeRequiredEvidenceClass.NonAuthorizationEvidence -> true
                else -> false
            }
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeRequiredEvidenceRow(
                evidenceClass = evidenceClass,
                modeled = true,
                presentAsModelEvidence = presentAsModelEvidence,
                futureRequired = !presentAsModelEvidence || evidenceClass in futureReviewEvidenceClasses(),
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentForbiddenShortcutRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationScopeForbiddenShortcutRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationScopeForbiddenShortcut.entries.map { shortcut ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeForbiddenShortcutRow(
                shortcut = shortcut,
                forbidden = true,
                currentBridgePresent = false,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentForbiddenPromotionPathRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationScopeForbiddenPromotionPathRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationScopeForbiddenPromotionPath.entries.map { route ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeForbiddenPromotionPathRow(
                promotionRoute = route,
                forbidden = true,
                currentBridgePresent = false,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentEvidenceSourceRows(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionRequest,
    ): List<SkaldVaultV1TestOnlyProviderIdentityImplementationScopeEvidenceSourceRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationScopeEvidenceSource.entries.map { source ->
            val included = when (source) {
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeEvidenceSource.TestOnlyProviderIdentityDecision ->
                    request.includePriorIdentityDecisionEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeEvidenceSource.TestOnlyProviderIdentityIsolationGuard ->
                    request.includePriorIsolationEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeEvidenceSource.SyntheticIdentityNamespace ->
                    request.includePriorNamespaceEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeEvidenceSource.TestOnlyProviderIdentitySourceSetConfinement ->
                    request.includePriorSourceSetConfinementEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeEvidenceSource.TestOnlyProviderIdentityImplementationDecision ->
                    request.includePriorImplementationDecisionEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeEvidenceSource.TestOnlyProviderIdentityImplementationPrerequisiteAudit ->
                    request.includePriorPrerequisiteAuditEvidence
                else -> true
            }
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeEvidenceSourceRow(
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
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationScopeFutureReviewRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationScopeFutureReviewRequirement.entries.map { requirement ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeFutureReviewRow(
                requirement = requirement,
                futureRequired = true,
                satisfiedNow = false,
                authorizesCurrentImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun futureReviewCategories(): Set<SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategory> =
        setOf(
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategory.ModelOnlyScope,
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategory.FutureTestOnlyIdentityScope,
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategory.SourceSetPlacementScope,
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategory.SyntheticNamespaceScope,
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategory.NonRuntimeEvidenceScope,
        )

    private fun futureReviewEvidenceClasses():
        Set<SkaldVaultV1TestOnlyProviderIdentityImplementationScopeRequiredEvidenceClass> =
        setOf(
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeRequiredEvidenceClass.FutureBranchApprovalEvidence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeRequiredEvidenceClass.ScopeReviewEvidence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeRequiredEvidenceClass.SyntheticSafeIdReviewEvidence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeRequiredEvidenceClass.TestOnlyNamespaceReviewEvidence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeRequiredEvidenceClass.SourceSetPlacementReviewEvidence,
        )

    private fun allowedFutureScopeCategoryFor(
        item: SkaldVaultV1TestOnlyProviderIdentityImplementationAllowedFutureScopeItem,
    ): SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategory =
        when (item) {
            SkaldVaultV1TestOnlyProviderIdentityImplementationAllowedFutureScopeItem
                .FutureBranchMayRequestTestOnlyIdentityImplementationReview ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategory.FutureTestOnlyIdentityScope
            SkaldVaultV1TestOnlyProviderIdentityImplementationAllowedFutureScopeItem.FutureBranchMayProposeSyntheticSafeId,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAllowedFutureScopeItem.FutureBranchMayProposeTestOnlyNamespace ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategory.SyntheticNamespaceScope
            SkaldVaultV1TestOnlyProviderIdentityImplementationAllowedFutureScopeItem
                .FutureBranchMayProposeNonProductionSourceSetPlacement,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAllowedFutureScopeItem.FutureBranchMayAddTestSourceOnlyFixturesForReview ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategory.SourceSetPlacementScope
            SkaldVaultV1TestOnlyProviderIdentityImplementationAllowedFutureScopeItem.FutureBranchMayAddModelOnlyEvidenceRows,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAllowedFutureScopeItem.FutureBranchMayAddSourceGuards,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAllowedFutureScopeItem.FutureBranchMayAddRedactionTests,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAllowedFutureScopeItem.FutureBranchMayDocumentBlockedImplementation ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategory.NonRuntimeEvidenceScope
            SkaldVaultV1TestOnlyProviderIdentityImplementationAllowedFutureScopeItem.FutureBranchMayProveProductionAbsence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAllowedFutureScopeItem.FutureBranchMayProveRuntimeReachabilityAbsence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAllowedFutureScopeItem.FutureBranchMayProveProviderSelectionAbsence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAllowedFutureScopeItem
                .FutureBranchMayProveRegistryFactoryDispatcherAbsence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAllowedFutureScopeItem.FutureBranchMayProveExecutorTargetAbsence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAllowedFutureScopeItem.FutureBranchMayProveVaultLifecycleAbsence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAllowedFutureScopeItem.FutureBranchMayProvePersistenceAbsence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAllowedFutureScopeItem.FutureBranchMayProveMainnetAbsence ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategory.ProductionForbiddenScope
        }

    private fun forbiddenScopeCategoryFor(
        item: SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem,
    ): SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategory =
        when (item) {
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem.ImplementProviderIdentityNow,
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem.ImplementProductionProviderIdentity,
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem.ImplementVaultCryptoProvider ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategory.ProductionForbiddenScope
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem.AddProviderRegistryEntry ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategory.ProviderSelectionForbiddenScope
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem.AddProviderFactory,
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem.AddProviderDispatcher ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategory.RegistryFactoryDispatcherForbiddenScope
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem.AddExecutorTarget ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategory.ExecutorTargetForbiddenScope
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem.AddProviderKatExecutor,
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem.AddRunnableExecutorInterface ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategory.ProviderKatExecutorForbiddenScope
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem.ExecuteProviderOperations,
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem.ExecuteRuntimeRandomness,
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem.ExecuteKdf,
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem.ExecuteHkdf,
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem.ExecuteHmac,
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem.ExecuteAead,
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem.GenerateKeys,
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem.StoreKeysets ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategory.ProviderOperationForbiddenScope
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem.CreateVault,
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem.UnlockVault,
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem.CreateVaultSession ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategory.VaultLifecycleForbiddenScope
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem.PersistVault,
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem.WriteStorageNamespace,
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem.WriteStoragePath,
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem.ReadOrWriteManifest,
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem.RunMigration ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategory.PersistenceForbiddenScope
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem.SucceedSecureStorage ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategory.SecureStorageForbiddenScope
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem.SucceedSecureMetadataStorage ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategory.SecureMetadataForbiddenScope
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem.StartProductionSync ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategory.RuntimeReachabilityForbiddenScope
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem.CreateBackendClient ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategory.BackendClientForbiddenScope
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem.CreateBdkWalletState ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategory.BdkWalletStateForbiddenScope
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem.WriteSettingsCodec ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategory.SettingsCodecForbiddenScope
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem.ExposeUiSurface ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategory.UiSurfaceForbiddenScope
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem.SignTransactions,
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem.BroadcastTransactions ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategory.SigningBroadcastingForbiddenScope
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem.StartTorTransport,
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem.ParseNostrSecrets ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategory.TorNostrForbiddenScope
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem.AddPublicEndpointDefault ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategory.PublicEndpointForbiddenScope
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenScopeItem.EnableMainnet ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationScopeCategory.MainnetForbiddenScope
        }

    private fun baseBlockers(): Set<SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker> =
        setOf(
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.CurrentScopeBlocked,
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.ScopeReviewRequired,
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.ImplementationNotAuthorized,
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.ProductionPromotionNotAuthorized,
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.MainnetNotAuthorized,
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.AllowedFutureScopeNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.PriorIdentityDecisionEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.PriorIsolationEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.PriorNamespaceEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.PriorSourceSetConfinementEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.PriorImplementationDecisionEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.PriorPrerequisiteAuditEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.PrerequisiteCompletionMissing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.FutureBranchApprovalMissing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.NoTestOnlyProviderIdentityImplementation,
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.NoProductionProviderIdentityImplementation,
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.NoProviderImplementation,
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.NoProviderFactory,
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.NoProviderDispatcher,
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.NoNonDisabledRegistryEntry,
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.NoExecutorTarget,
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.NoProviderKatExecutor,
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.NoRunnableExecutorInterface,
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.ProviderSelectionDisabledProviderOnly,
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.ProductionProviderSelectableFalse,
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.ProviderOperationAuthorizationBlocked,
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.VaultLifecycleDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.PersistenceDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.ProductionSyncDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.UserConsentCannotOverride,
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.WarningOnlyEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.TestOnlyEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.ReleaseEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.MainnetDisabled,
        )

    private fun requestBlockers(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionRequest,
    ): Set<SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker> =
        buildSet {
            if (request.userConsentOverrideRequested) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.UserConsentCannotOverride)
            }
            if (request.warningOnlyEvidenceClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.WarningOnlyEvidenceNonAuthorizing)
            }
            if (request.testOnlyEvidenceClaimedAsProductionPromotion) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.TestOnlyEvidenceNonAuthorizing)
            }
            if (request.releaseEvidenceClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.ReleaseEvidenceNonAuthorizing)
            }
            if (request.futureBranchApprovalClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.FutureBranchApprovalClaimRejected)
            }
            if (request.prerequisiteCompletionClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker
                        .PrerequisiteCompletionClaimRejected,
                )
            }
            if (request.implementationScopeApprovalClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker
                        .ImplementationScopeApprovalClaimRejected,
                )
            }
            if (request.allowedFutureScopeClaimedAsCurrentAuthorization) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker
                        .AllowedFutureScopeAsCurrentAuthorizationRejected,
                )
            }
            if (request.providerImplementationClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.ProviderImplementationClaimRejected)
            }
            if (request.registryEntryClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.RegistryEntryClaimRejected)
            }
            if (request.factoryReachabilityClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.FactoryReachabilityRejected)
            }
            if (request.dispatcherReachabilityClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.DispatcherReachabilityRejected)
            }
            if (request.executorTargetClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.ExecutorTargetRejected)
            }
            if (request.providerKatExecutorClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.ProviderKatExecutorRejected)
            }
            if (request.providerOperationClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.ProviderOperationRejected)
            }
            if (request.persistenceReachabilityClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.PersistenceReachabilityRejected)
            }
            if (request.settingsReachabilityClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.SettingsReachabilityRejected)
            }
            if (request.uiReachabilityClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.UiReachabilityRejected)
            }
            if (request.mainnetReachabilityClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationScopeBlocker.MainnetReachabilityRejected)
            }
        }

    private fun redactedLabel(): SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionSafeLabel =
        SkaldVaultV1TestOnlyProviderIdentityImplementationScopeDecisionSafeLabel("redacted")
}
