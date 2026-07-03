package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersSafeLabel(
    val value: String,
) {
    override fun toString(): String = "RedactedLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionStatus {
    PromotionBlockersModeled,
    StillDisabled,
    CurrentPromotionBlocked,
    PromotionReviewRequired,
    TestOnlyIdentityNonPromotable,
    ProductionIdentityNotAuthorized,
    ProviderSelectionNotAuthorized,
    ProductionProviderSelectableFalse,
    VaultPersistenceNotAuthorized,
    ProductionSyncNotAuthorized,
    MainnetNotAuthorized,
    PriorIdentityDecisionEvidenceNonAuthorizing,
    PriorIsolationEvidenceNonAuthorizing,
    PriorNamespaceEvidenceNonAuthorizing,
    PriorSourceSetConfinementEvidenceNonAuthorizing,
    PriorImplementationDecisionEvidenceNonAuthorizing,
    PriorPrerequisiteAuditEvidenceNonAuthorizing,
    PriorScopeDecisionEvidenceNonAuthorizing,
    PriorImplementationContractEvidenceNonAuthorizing,
    PriorReadinessGateEvidenceNonAuthorizing,
    PriorRuntimeLinkageGuardEvidenceNonAuthorizing,
    PromotionStagesModeled,
    ForbiddenPromotionSourcesModeled,
    ForbiddenPromotionTargetsModeled,
    ForbiddenPromotionPathsModeled,
    AllCurrentPromotionsAbsent,
    ProviderSelectionDisabledProviderOnly,
    PromotionBlockerEvidenceNonAuthorizing,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionOutcome {
    CurrentPromotionBlocked,
    PromotionReviewRequired,
    TestOnlyIdentityNonPromotable,
    ProductionIdentityNotAuthorized,
    ProviderSelectionNotAuthorized,
    ProductionProviderSelectableFalse,
    VaultPersistenceNotAuthorized,
    ProductionSyncNotAuthorized,
    MainnetNotAuthorized,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionStage {
    ModelEvidenceToImplementation,
    ImplementationToTestOnlyIdentity,
    TestOnlyIdentityToProviderSelection,
    TestOnlyIdentityToRegistryEntry,
    TestOnlyIdentityToProviderFactory,
    TestOnlyIdentityToProviderDispatcher,
    TestOnlyIdentityToExecutorTarget,
    TestOnlyIdentityToProviderKatExecutor,
    TestOnlyIdentityToProviderOperation,
    TestOnlyIdentityToProductionIdentity,
    ProductionIdentityToProductionProvider,
    ProductionProviderToProductionProviderSelectable,
    ProductionProviderSelectableToVaultPersistence,
    VaultPersistenceToProductionSync,
    ProductionSyncToSigning,
    SigningToBroadcasting,
    BroadcastingToMainnet,
    EvidenceToPublicEndpointDefault,
    EvidenceToUiSurface,
    EvidenceToBackendClient,
    EvidenceToBdkWalletState,
    EvidenceToSettingsCodec,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker {
    CurrentPromotionBlocked,
    PromotionReviewRequired,
    TestOnlyIdentityNonPromotable,
    ProductionIdentityNotAuthorized,
    ProviderSelectionNotAuthorized,
    ProductionProviderSelectableFalse,
    VaultPersistenceNotAuthorized,
    ProductionSyncNotAuthorized,
    MainnetNotAuthorized,
    PriorEvidenceNonAuthorizing,
    RuntimeLinkageEvidenceNonAuthorizing,
    PromotionBlockerEvidenceNonAuthorizing,
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
    ProviderOperationAuthorizationBlocked,
    CryptoExecutionBlocked,
    VaultLifecycleDisabled,
    PersistenceDisabled,
    SecureStorageDisabled,
    SecureMetadataDisabled,
    ProductionSyncDisabled,
    BackendClientDisabled,
    BdkWalletStateDisabled,
    SettingsCodecDisabled,
    UiSurfaceDisabled,
    SigningBroadcastingDisabled,
    TorNostrDisabled,
    PublicEndpointDisabled,
    MainnetDisabled,
    UserConsentCannotOverride,
    WarningOnlyEvidenceNonAuthorizing,
    TestOnlyEvidenceNonAuthorizing,
    ReleaseEvidenceNonAuthorizing,
    FutureBranchApprovalClaimRejected,
    PromotionClaimRejected,
    TestOnlyIdentityPromotionClaimRejected,
    ProductionIdentityClaimRejected,
    ProductionProviderClaimRejected,
    ProductionProviderSelectableClaimRejected,
    ProviderSelectionPromotionRejected,
    RegistryPromotionRejected,
    FactoryPromotionRejected,
    DispatcherPromotionRejected,
    ExecutorTargetPromotionRejected,
    ProviderKatExecutorPromotionRejected,
    ProviderOperationPromotionRejected,
    CryptoExecutionPromotionRejected,
    VaultPersistencePromotionRejected,
    ProductionSyncPromotionRejected,
    SettingsPromotionRejected,
    UiPromotionRejected,
    SigningBroadcastingPromotionRejected,
    PublicEndpointPromotionRejected,
    MainnetPromotionRejected,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenPromotionSource {
    IdentityDecisionEvidence,
    IdentityIsolationEvidence,
    SyntheticNamespaceEvidence,
    SourceSetConfinementEvidence,
    ImplementationDecisionEvidence,
    PrerequisiteAuditEvidence,
    ScopeDecisionEvidence,
    ImplementationContractEvidence,
    ReadinessGateEvidence,
    RuntimeLinkageGuardEvidence,
    DocumentationEvidence,
    PublicVectorEvidence,
    TestOnlyKatEvidence,
    SourceGuardEvidence,
    RedactionEvidence,
    AbsenceEvidence,
    UserConsent,
    WarningOnlyEvidence,
    ReleaseClaim,
    FutureBranchName,
    FutureReviewLabel,
    SafeIdSyntaxAcceptance,
    DisabledProviderSelection,
    DisabledProviderRuntime,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenPromotionTarget {
    TestOnlyProviderIdentityImplementationNow,
    ProductionProviderIdentityImplementation,
    VaultCryptoProviderImplementation,
    ProviderSelectionEntry,
    NonDisabledRegistryEntry,
    ProviderFactoryEntry,
    ProviderDispatcherEntry,
    ExecutorTarget,
    ProviderKatExecutor,
    ProviderOperationExecution,
    RuntimeRandomnessExecution,
    KdfExecution,
    HkdfExecution,
    HmacExecution,
    AeadExecution,
    KeyGeneration,
    KeysetStorage,
    VaultCreation,
    VaultUnlock,
    VaultSession,
    VaultPersistence,
    SecureStorageSuccess,
    SecureMetadataStorageSuccess,
    ManifestReadWrite,
    Migration,
    ProductionSync,
    BackendClient,
    BdkWalletState,
    SettingsCodecPersistence,
    UiSurface,
    Signing,
    Broadcasting,
    TorTransport,
    NostrParsing,
    PublicEndpointDefault,
    Mainnet,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenPromotionPath {
    PromotionBlockersToProviderImplementation,
    PromotionBlockersToTestOnlyProviderIdentityImplementation,
    PromotionBlockersToProductionProviderIdentityImplementation,
    PromotionBlockersToProviderSelection,
    PromotionBlockersToProductionProviderSelectable,
    PromotionBlockersToRegistryEntry,
    PromotionBlockersToFactory,
    PromotionBlockersToDispatcher,
    PromotionBlockersToExecutorTarget,
    PromotionBlockersToProviderKatExecutor,
    PromotionBlockersToProviderOperation,
    PromotionBlockersToRuntimeRandomness,
    PromotionBlockersToKdf,
    PromotionBlockersToHkdf,
    PromotionBlockersToHmac,
    PromotionBlockersToAead,
    PromotionBlockersToKeyGeneration,
    PromotionBlockersToKeysetStorage,
    PromotionBlockersToVaultCreation,
    PromotionBlockersToVaultUnlock,
    PromotionBlockersToVaultSession,
    PromotionBlockersToVaultPersistence,
    PromotionBlockersToSecureStorageSuccess,
    PromotionBlockersToSecureMetadataSuccess,
    PromotionBlockersToManifestReadWrite,
    PromotionBlockersToMigration,
    PromotionBlockersToProductionSync,
    PromotionBlockersToBackendClient,
    PromotionBlockersToBdkWalletState,
    PromotionBlockersToSettingsCodec,
    PromotionBlockersToUiSurface,
    PromotionBlockersToSigning,
    PromotionBlockersToBroadcasting,
    PromotionBlockersToTorTransport,
    PromotionBlockersToNostrParsing,
    PromotionBlockersToPublicEndpointDefault,
    PromotionBlockersToMainnet,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionRequiredEvidenceClass {
    IdentityDecisionEvidence,
    IdentityIsolationEvidence,
    SyntheticNamespaceEvidence,
    SourceSetConfinementEvidence,
    ImplementationDecisionEvidence,
    PrerequisiteAuditEvidence,
    ScopeDecisionEvidence,
    ImplementationContractEvidence,
    ReadinessGateEvidence,
    RuntimeLinkageGuardEvidence,
    ProviderSelectionFailClosedEvidence,
    DisabledProviderEvidence,
    ForbiddenPromotionSourceEvidence,
    ForbiddenPromotionTargetEvidence,
    ForbiddenPromotionPathEvidence,
    ProductionProviderSelectableFalseEvidence,
    SourceGuardCoverageEvidence,
    RedactionLeakageReviewEvidence,
    FutureBranchApprovalEvidence,
    FutureImplementationReviewEvidence,
    ProductionAbsenceEvidence,
    MainnetAbsenceEvidence,
    NonAuthorizationEvidence,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionEvidenceSource {
    TestOnlyProviderIdentityDecision,
    TestOnlyProviderIdentityIsolationGuard,
    SyntheticIdentityNamespace,
    TestOnlyProviderIdentitySourceSetConfinement,
    TestOnlyProviderIdentityImplementationDecision,
    TestOnlyProviderIdentityImplementationPrerequisiteAudit,
    TestOnlyProviderIdentityImplementationScopeDecision,
    TestOnlyProviderIdentityImplementationContract,
    TestOnlyProviderIdentityImplementationReadinessGate,
    TestOnlyProviderIdentityImplementationRuntimeLinkageGuard,
    ProviderSelectionBoundary,
    DisabledProviderBoundary,
    ProviderSelectionPromotionBlockers,
    ProductionProviderAcceptanceContract,
    SourceGuardEvidence,
    RedactionLeakageBoundary,
    AbsenceEvidence,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionRedactionClass {
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
    RedactedPrerequisiteReference,
    RedactedScopeReference,
    RedactedContractReference,
    RedactedReadinessReference,
    RedactedRuntimeLinkageReference,
    RedactedPromotionReference,
    SafePolicyIdOnly,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionFutureReviewRequirement {
    ExplicitFutureBranchApproval,
    PromotionReviewRequest,
    TestOnlyIdentityImplementationReview,
    ProductionIdentityReview,
    ProviderSelectionAbsenceReview,
    RegistryFactoryDispatcherAbsenceReview,
    ExecutorTargetAndKatExecutorAbsenceReview,
    ProviderOperationAbsenceReview,
    CryptoExecutionAbsenceReview,
    VaultPersistenceAbsenceReview,
    ProductionSyncAbsenceReview,
    BackendBdkSettingsUiAbsenceReview,
    SigningBroadcastingEndpointAbsenceReview,
    SourceGuardCoverageReview,
    RedactionAndLeakageReview,
    NonAuthorizationReview,
    MainnetNonAuthorizationReview,
}

data class SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionStageRow(
    val stage: SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionStage,
    val modeled: Boolean,
    val currentPromotionPresent: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockerRow(
    val blocker: SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker,
    val active: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenPromotionSourceRow(
    val source: SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenPromotionSource,
    val forbidden: Boolean,
    val authorizesImplementation: Boolean,
    val authorizesProductionPromotion: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenPromotionTargetRow(
    val target: SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenPromotionTarget,
    val forbidden: Boolean,
    val currentPromotionPresent: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenPromotionPathRow(
    val promotionRoute: SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenPromotionPath,
    val forbidden: Boolean,
    val currentPromotionPresent: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionRequiredEvidenceRow(
    val evidenceClass: SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionRequiredEvidenceClass,
    val modeled: Boolean,
    val presentAsModelEvidence: Boolean,
    val futureRequired: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionEvidenceSourceRow(
    val evidenceSource: SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionEvidenceSource,
    val included: Boolean,
    val modeled: Boolean,
    val nonAuthorizing: Boolean,
    val authorizesImplementation: Boolean,
    val authorizesProductionPromotion: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionFutureReviewRow(
    val requirement: SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionFutureReviewRequirement,
    val futureRequired: Boolean,
    val satisfiedNow: Boolean,
    val authorizesCurrentImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersCapabilities(
    val promotionBlockersAuthorizeImplementation: Boolean,
    val promotionBlockersAuthorizeProductionIdentity: Boolean,
    val promotionBlockersAuthorizeProviderSelection: Boolean,
    val promotionBlockersAuthorizeProductionProviderSelectable: Boolean,
    val runtimeLinkageGuardAuthorizesImplementation: Boolean,
    val readinessGateAuthorizesImplementation: Boolean,
    val implementationContractAuthorizesImplementation: Boolean,
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
        val Current = SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersCapabilities(
            promotionBlockersAuthorizeImplementation = false,
            promotionBlockersAuthorizeProductionIdentity = false,
            promotionBlockersAuthorizeProviderSelection = false,
            promotionBlockersAuthorizeProductionProviderSelectable = false,
            runtimeLinkageGuardAuthorizesImplementation = false,
            readinessGateAuthorizesImplementation = false,
            implementationContractAuthorizesImplementation = false,
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

data class SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersRequest(
    val includePriorIdentityDecisionEvidence: Boolean = true,
    val includePriorIsolationEvidence: Boolean = true,
    val includePriorNamespaceEvidence: Boolean = true,
    val includePriorSourceSetConfinementEvidence: Boolean = true,
    val includePriorImplementationDecisionEvidence: Boolean = true,
    val includePriorPrerequisiteAuditEvidence: Boolean = true,
    val includePriorScopeDecisionEvidence: Boolean = true,
    val includePriorImplementationContractEvidence: Boolean = true,
    val includePriorReadinessGateEvidence: Boolean = true,
    val includePriorRuntimeLinkageGuardEvidence: Boolean = true,
    val userConsentOverrideRequested: Boolean = false,
    val warningOnlyEvidenceClaimed: Boolean = false,
    val testOnlyEvidenceClaimedAsProductionPromotion: Boolean = false,
    val releaseEvidenceClaimed: Boolean = false,
    val futureBranchApprovalClaimed: Boolean = false,
    val promotionClaimed: Boolean = false,
    val testOnlyIdentityPromotionClaimed: Boolean = false,
    val productionIdentityClaimed: Boolean = false,
    val productionProviderClaimed: Boolean = false,
    val productionProviderSelectableClaimed: Boolean = false,
    val providerSelectionPromotionClaimed: Boolean = false,
    val registryPromotionClaimed: Boolean = false,
    val factoryPromotionClaimed: Boolean = false,
    val dispatcherPromotionClaimed: Boolean = false,
    val executorTargetPromotionClaimed: Boolean = false,
    val providerKatExecutorPromotionClaimed: Boolean = false,
    val providerOperationPromotionClaimed: Boolean = false,
    val cryptoExecutionPromotionClaimed: Boolean = false,
    val vaultPersistencePromotionClaimed: Boolean = false,
    val productionSyncPromotionClaimed: Boolean = false,
    val settingsPromotionClaimed: Boolean = false,
    val uiPromotionClaimed: Boolean = false,
    val signingBroadcastingPromotionClaimed: Boolean = false,
    val publicEndpointPromotionClaimed: Boolean = false,
    val mainnetPromotionClaimed: Boolean = false,
) {
    override fun toString(): String = "RedactedRequest(claims=modeled)"
}

data class SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersEvidence(
    val policyId: String,
    val policyVersion: Int,
    val promotionBlockersModeled: Boolean,
    val stillDisabled: Boolean,
    val priorIdentityDecisionEvidenceIncluded: Boolean,
    val priorIsolationEvidenceIncluded: Boolean,
    val priorNamespaceEvidenceIncluded: Boolean,
    val priorSourceSetConfinementEvidenceIncluded: Boolean,
    val priorImplementationDecisionEvidenceIncluded: Boolean,
    val priorPrerequisiteAuditEvidenceIncluded: Boolean,
    val priorScopeDecisionEvidenceIncluded: Boolean,
    val priorImplementationContractEvidenceIncluded: Boolean,
    val priorReadinessGateEvidenceIncluded: Boolean,
    val priorRuntimeLinkageGuardEvidenceIncluded: Boolean,
    val promotionStagesModeled: Boolean,
    val forbiddenPromotionSourcesModeled: Boolean,
    val forbiddenPromotionTargetsModeled: Boolean,
    val forbiddenPromotionPathsModeled: Boolean,
    val docsMayDescribeFutureReview: Boolean,
    val testsMayAssertBlockedPromotion: Boolean,
    val currentPromotionToTestOnlyIdentityImplementationPresent: Boolean,
    val currentPromotionToProductionIdentityPresent: Boolean,
    val currentPromotionToProviderSelectionPresent: Boolean,
    val currentPromotionToRegistryPresent: Boolean,
    val currentPromotionToFactoryPresent: Boolean,
    val currentPromotionToDispatcherPresent: Boolean,
    val currentPromotionToExecutorTargetPresent: Boolean,
    val currentPromotionToProviderKatExecutorPresent: Boolean,
    val currentPromotionToProviderOperationPresent: Boolean,
    val currentPromotionToCryptoExecutionPresent: Boolean,
    val currentPromotionToVaultPersistencePresent: Boolean,
    val currentPromotionToProductionSyncPresent: Boolean,
    val currentPromotionToSettingsPresent: Boolean,
    val currentPromotionToUiPresent: Boolean,
    val currentPromotionToSigningBroadcastingPresent: Boolean,
    val currentPromotionToPublicEndpointPresent: Boolean,
    val currentPromotionToMainnetPresent: Boolean,
    val statuses: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionStatus>,
    val outcomes: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionOutcome>,
    val promotionStageRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionStageRow>,
    val promotionBlockerRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockerRow>,
    val forbiddenPromotionSourceRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenPromotionSourceRow>,
    val forbiddenPromotionTargetRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenPromotionTargetRow>,
    val forbiddenPromotionPathRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenPromotionPathRow>,
    val requiredEvidenceRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionRequiredEvidenceRow>,
    val evidenceSourceRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionEvidenceSourceRow>,
    val futureReviewRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionFutureReviewRow>,
    val blockers: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker>,
    val redactionClasses: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionRedactionClass>,
    val capabilities: SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersCapabilities,
)

object SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersPolicy {
    const val POLICY_ID: String =
        "skald-vault-v1-test-only-provider-identity-implementation-promotion-blockers-v1"
    const val POLICY_VERSION: Int = 1

    fun currentPromotionBlockersEvidence():
        SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersEvidence =
        evaluatePromotionBlockers()

    fun evaluatePromotionBlockers(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersRequest =
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersRequest(),
    ): SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersEvidence {
        val blockers = baseBlockers() + requestBlockers(request)
        return SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersEvidence(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            promotionBlockersModeled = true,
            stillDisabled = true,
            priorIdentityDecisionEvidenceIncluded = request.includePriorIdentityDecisionEvidence,
            priorIsolationEvidenceIncluded = request.includePriorIsolationEvidence,
            priorNamespaceEvidenceIncluded = request.includePriorNamespaceEvidence,
            priorSourceSetConfinementEvidenceIncluded = request.includePriorSourceSetConfinementEvidence,
            priorImplementationDecisionEvidenceIncluded = request.includePriorImplementationDecisionEvidence,
            priorPrerequisiteAuditEvidenceIncluded = request.includePriorPrerequisiteAuditEvidence,
            priorScopeDecisionEvidenceIncluded = request.includePriorScopeDecisionEvidence,
            priorImplementationContractEvidenceIncluded = request.includePriorImplementationContractEvidence,
            priorReadinessGateEvidenceIncluded = request.includePriorReadinessGateEvidence,
            priorRuntimeLinkageGuardEvidenceIncluded = request.includePriorRuntimeLinkageGuardEvidence,
            promotionStagesModeled = true,
            forbiddenPromotionSourcesModeled = true,
            forbiddenPromotionTargetsModeled = true,
            forbiddenPromotionPathsModeled = true,
            docsMayDescribeFutureReview = true,
            testsMayAssertBlockedPromotion = true,
            currentPromotionToTestOnlyIdentityImplementationPresent = false,
            currentPromotionToProductionIdentityPresent = false,
            currentPromotionToProviderSelectionPresent = false,
            currentPromotionToRegistryPresent = false,
            currentPromotionToFactoryPresent = false,
            currentPromotionToDispatcherPresent = false,
            currentPromotionToExecutorTargetPresent = false,
            currentPromotionToProviderKatExecutorPresent = false,
            currentPromotionToProviderOperationPresent = false,
            currentPromotionToCryptoExecutionPresent = false,
            currentPromotionToVaultPersistencePresent = false,
            currentPromotionToProductionSyncPresent = false,
            currentPromotionToSettingsPresent = false,
            currentPromotionToUiPresent = false,
            currentPromotionToSigningBroadcastingPresent = false,
            currentPromotionToPublicEndpointPresent = false,
            currentPromotionToMainnetPresent = false,
            statuses = SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionStatus.entries.toSet(),
            outcomes = SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionOutcome.entries.toSet(),
            promotionStageRows = currentPromotionStageRows(),
            promotionBlockerRows = currentPromotionBlockerRows(blockers),
            forbiddenPromotionSourceRows = currentForbiddenPromotionSourceRows(),
            forbiddenPromotionTargetRows = currentForbiddenPromotionTargetRows(),
            forbiddenPromotionPathRows = currentForbiddenPromotionPathRows(),
            requiredEvidenceRows = currentRequiredEvidenceRows(request),
            evidenceSourceRows = currentEvidenceSourceRows(request),
            futureReviewRows = currentFutureReviewRows(),
            blockers = blockers,
            redactionClasses =
                SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionRedactionClass.entries.toSet(),
            capabilities = SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersCapabilities.Current,
        )
    }

    private fun currentPromotionStageRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionStageRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionStage.entries.map { stage ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionStageRow(
                stage = stage,
                modeled = true,
                currentPromotionPresent = false,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentPromotionBlockerRows(
        blockers: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker>,
    ): List<SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockerRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.entries.map { blocker ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockerRow(
                blocker = blocker,
                active = blocker in blockers,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentForbiddenPromotionSourceRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenPromotionSourceRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenPromotionSource.entries.map { source ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenPromotionSourceRow(
                source = source,
                forbidden = true,
                authorizesImplementation = false,
                authorizesProductionPromotion = false,
                label = redactedLabel(),
            )
        }

    private fun currentForbiddenPromotionTargetRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenPromotionTargetRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenPromotionTarget.entries.map { target ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenPromotionTargetRow(
                target = target,
                forbidden = true,
                currentPromotionPresent = false,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentForbiddenPromotionPathRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenPromotionPathRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenPromotionPath.entries.map { route ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenPromotionPathRow(
                promotionRoute = route,
                forbidden = true,
                currentPromotionPresent = false,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentRequiredEvidenceRows(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersRequest,
    ): List<SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionRequiredEvidenceRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionRequiredEvidenceClass.entries.map { evidenceClass ->
            val presentAsModelEvidence = when (evidenceClass) {
                SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionRequiredEvidenceClass
                    .IdentityDecisionEvidence ->
                    request.includePriorIdentityDecisionEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionRequiredEvidenceClass
                    .IdentityIsolationEvidence ->
                    request.includePriorIsolationEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionRequiredEvidenceClass
                    .SyntheticNamespaceEvidence ->
                    request.includePriorNamespaceEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionRequiredEvidenceClass
                    .SourceSetConfinementEvidence ->
                    request.includePriorSourceSetConfinementEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionRequiredEvidenceClass
                    .ImplementationDecisionEvidence ->
                    request.includePriorImplementationDecisionEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionRequiredEvidenceClass
                    .PrerequisiteAuditEvidence ->
                    request.includePriorPrerequisiteAuditEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionRequiredEvidenceClass
                    .ScopeDecisionEvidence ->
                    request.includePriorScopeDecisionEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionRequiredEvidenceClass
                    .ImplementationContractEvidence ->
                    request.includePriorImplementationContractEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionRequiredEvidenceClass
                    .ReadinessGateEvidence ->
                    request.includePriorReadinessGateEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionRequiredEvidenceClass
                    .RuntimeLinkageGuardEvidence ->
                    request.includePriorRuntimeLinkageGuardEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionRequiredEvidenceClass
                    .ProviderSelectionFailClosedEvidence,
                SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionRequiredEvidenceClass
                    .DisabledProviderEvidence,
                SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionRequiredEvidenceClass
                    .ForbiddenPromotionSourceEvidence,
                SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionRequiredEvidenceClass
                    .ForbiddenPromotionTargetEvidence,
                SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionRequiredEvidenceClass
                    .ForbiddenPromotionPathEvidence,
                SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionRequiredEvidenceClass
                    .ProductionProviderSelectableFalseEvidence,
                SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionRequiredEvidenceClass
                    .SourceGuardCoverageEvidence,
                SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionRequiredEvidenceClass
                    .NonAuthorizationEvidence ->
                    true
                else -> false
            }
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionRequiredEvidenceRow(
                evidenceClass = evidenceClass,
                modeled = true,
                presentAsModelEvidence = presentAsModelEvidence,
                futureRequired = !presentAsModelEvidence || evidenceClass in futureReviewEvidenceClasses(),
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentEvidenceSourceRows(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersRequest,
    ): List<SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionEvidenceSourceRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionEvidenceSource.entries.map { source ->
            val included = when (source) {
                SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionEvidenceSource
                    .TestOnlyProviderIdentityDecision ->
                    request.includePriorIdentityDecisionEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionEvidenceSource
                    .TestOnlyProviderIdentityIsolationGuard ->
                    request.includePriorIsolationEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionEvidenceSource
                    .SyntheticIdentityNamespace ->
                    request.includePriorNamespaceEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionEvidenceSource
                    .TestOnlyProviderIdentitySourceSetConfinement ->
                    request.includePriorSourceSetConfinementEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionEvidenceSource
                    .TestOnlyProviderIdentityImplementationDecision ->
                    request.includePriorImplementationDecisionEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionEvidenceSource
                    .TestOnlyProviderIdentityImplementationPrerequisiteAudit ->
                    request.includePriorPrerequisiteAuditEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionEvidenceSource
                    .TestOnlyProviderIdentityImplementationScopeDecision ->
                    request.includePriorScopeDecisionEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionEvidenceSource
                    .TestOnlyProviderIdentityImplementationContract ->
                    request.includePriorImplementationContractEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionEvidenceSource
                    .TestOnlyProviderIdentityImplementationReadinessGate ->
                    request.includePriorReadinessGateEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionEvidenceSource
                    .TestOnlyProviderIdentityImplementationRuntimeLinkageGuard ->
                    request.includePriorRuntimeLinkageGuardEvidence
                else -> true
            }
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionEvidenceSourceRow(
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
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionFutureReviewRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionFutureReviewRequirement.entries.map {
                requirement ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionFutureReviewRow(
                requirement = requirement,
                futureRequired = true,
                satisfiedNow = false,
                authorizesCurrentImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun futureReviewEvidenceClasses():
        Set<SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionRequiredEvidenceClass> =
        setOf(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionRequiredEvidenceClass
                .RedactionLeakageReviewEvidence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionRequiredEvidenceClass
                .FutureBranchApprovalEvidence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionRequiredEvidenceClass
                .FutureImplementationReviewEvidence,
        )

    private fun baseBlockers(): Set<SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker> =
        setOf(
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.CurrentPromotionBlocked,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.PromotionReviewRequired,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.TestOnlyIdentityNonPromotable,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.ProductionIdentityNotAuthorized,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.ProviderSelectionNotAuthorized,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.ProductionProviderSelectableFalse,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.VaultPersistenceNotAuthorized,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.ProductionSyncNotAuthorized,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.MainnetNotAuthorized,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.PriorEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.RuntimeLinkageEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.PromotionBlockerEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker
                .NoTestOnlyProviderIdentityImplementation,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker
                .NoProductionProviderIdentityImplementation,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.NoProviderImplementation,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.NoProviderFactory,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.NoProviderDispatcher,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.NoNonDisabledRegistryEntry,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.NoExecutorTarget,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.NoProviderKatExecutor,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.NoRunnableExecutorInterface,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.ProviderSelectionDisabledProviderOnly,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker
                .ProviderOperationAuthorizationBlocked,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.CryptoExecutionBlocked,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.VaultLifecycleDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.PersistenceDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.SecureStorageDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.SecureMetadataDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.ProductionSyncDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.BackendClientDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.BdkWalletStateDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.SettingsCodecDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.UiSurfaceDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.SigningBroadcastingDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.TorNostrDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.PublicEndpointDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.MainnetDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.UserConsentCannotOverride,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.WarningOnlyEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.TestOnlyEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.ReleaseEvidenceNonAuthorizing,
        )

    private fun requestBlockers(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersRequest,
    ): Set<SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker> =
        buildSet {
            if (request.userConsentOverrideRequested) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.UserConsentCannotOverride)
            }
            if (request.warningOnlyEvidenceClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker
                        .WarningOnlyEvidenceNonAuthorizing,
                )
            }
            if (request.testOnlyEvidenceClaimedAsProductionPromotion) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker
                        .TestOnlyEvidenceNonAuthorizing,
                )
            }
            if (request.releaseEvidenceClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.ReleaseEvidenceNonAuthorizing)
            }
            if (request.futureBranchApprovalClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker
                        .FutureBranchApprovalClaimRejected,
                )
            }
            if (request.promotionClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.PromotionClaimRejected)
            }
            if (request.testOnlyIdentityPromotionClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker
                        .TestOnlyIdentityPromotionClaimRejected,
                )
            }
            if (request.productionIdentityClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.ProductionIdentityClaimRejected)
            }
            if (request.productionProviderClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.ProductionProviderClaimRejected)
            }
            if (request.productionProviderSelectableClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker
                        .ProductionProviderSelectableClaimRejected,
                )
            }
            if (request.providerSelectionPromotionClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker
                        .ProviderSelectionPromotionRejected,
                )
            }
            if (request.registryPromotionClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.RegistryPromotionRejected)
            }
            if (request.factoryPromotionClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.FactoryPromotionRejected)
            }
            if (request.dispatcherPromotionClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.DispatcherPromotionRejected)
            }
            if (request.executorTargetPromotionClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker
                        .ExecutorTargetPromotionRejected,
                )
            }
            if (request.providerKatExecutorPromotionClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker
                        .ProviderKatExecutorPromotionRejected,
                )
            }
            if (request.providerOperationPromotionClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker
                        .ProviderOperationPromotionRejected,
                )
            }
            if (request.cryptoExecutionPromotionClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker
                        .CryptoExecutionPromotionRejected,
                )
            }
            if (request.vaultPersistencePromotionClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker
                        .VaultPersistencePromotionRejected,
                )
            }
            if (request.productionSyncPromotionClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker
                        .ProductionSyncPromotionRejected,
                )
            }
            if (request.settingsPromotionClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.SettingsPromotionRejected)
            }
            if (request.uiPromotionClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.UiPromotionRejected)
            }
            if (request.signingBroadcastingPromotionClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker
                        .SigningBroadcastingPromotionRejected,
                )
            }
            if (request.publicEndpointPromotionClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker
                        .PublicEndpointPromotionRejected,
                )
            }
            if (request.mainnetPromotionClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlocker.MainnetPromotionRejected)
            }
        }

    private fun redactedLabel(): SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersSafeLabel =
        SkaldVaultV1TestOnlyProviderIdentityImplementationPromotionBlockersSafeLabel("redacted")
}
