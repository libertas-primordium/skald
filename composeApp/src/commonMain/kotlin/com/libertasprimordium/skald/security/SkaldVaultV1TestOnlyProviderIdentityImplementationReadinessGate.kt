package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateSafeLabel(
    val value: String,
) {
    override fun toString(): String = "RedactedLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateStatus {
    ReadinessGateModeled,
    StillDisabled,
    CurrentReadinessBlocked,
    ReadinessReviewRequired,
    DependenciesIncomplete,
    ContractNotSatisfiedForImplementation,
    ImplementationNotAuthorized,
    ProductionPromotionNotAuthorized,
    MainnetNotAuthorized,
    PriorIdentityDecisionEvidenceNonAuthorizing,
    PriorIsolationEvidenceNonAuthorizing,
    PriorNamespaceEvidenceNonAuthorizing,
    PriorSourceSetConfinementEvidenceNonAuthorizing,
    PriorImplementationDecisionEvidenceNonAuthorizing,
    PriorPrerequisiteAuditEvidenceNonAuthorizing,
    PriorScopeDecisionEvidenceNonAuthorizing,
    PriorImplementationContractEvidenceNonAuthorizing,
    ReadinessDependenciesModeled,
    ReadinessChecksModeled,
    ReadinessChecksNonAuthorizing,
    ForbiddenRuntimeLinkagesModeled,
    ProviderSelectionDisabledProviderOnly,
    ProductionProviderSelectableFalse,
    VaultLifecycleBlocked,
    PersistenceBlocked,
    ProductionSyncBlocked,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessOutcome {
    CurrentReadinessBlocked,
    ReadinessReviewRequired,
    DependenciesIncomplete,
    ContractNotSatisfiedForImplementation,
    ImplementationNotAuthorized,
    ProductionPromotionNotAuthorized,
    MainnetNotAuthorized,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection {
    ModelOnlyReadiness,
    FutureReviewOnlyReadiness,
    PriorEvidenceReadiness,
    DecisionGateReadiness,
    PrerequisiteAuditReadiness,
    ScopeDecisionReadiness,
    ContractReadiness,
    SourceGuardReadiness,
    RedactionReadiness,
    ProductionAbsenceReadiness,
    RuntimeReachabilityAbsenceReadiness,
    ProviderSelectionAbsenceReadiness,
    RegistryFactoryDispatcherAbsenceReadiness,
    ExecutorTargetAbsenceReadiness,
    ProviderKatExecutorAbsenceReadiness,
    ProviderOperationAbsenceReadiness,
    CryptoExecutionAbsenceReadiness,
    VaultLifecycleAbsenceReadiness,
    PersistenceAbsenceReadiness,
    SecureStorageAbsenceReadiness,
    SecureMetadataAbsenceReadiness,
    BackendClientAbsenceReadiness,
    BdkWalletStateAbsenceReadiness,
    SettingsCodecAbsenceReadiness,
    UiSurfaceAbsenceReadiness,
    SigningBroadcastingAbsenceReadiness,
    TorNostrAbsenceReadiness,
    PublicEndpointAbsenceReadiness,
    MainnetAbsenceReadiness,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessDependency {
    IdentityDecisionBoundary,
    IdentityIsolationGuard,
    SyntheticIdentityNamespaceContract,
    IdentitySourceSetConfinementBoundary,
    ImplementationDecisionGate,
    ImplementationPrerequisiteAudit,
    ImplementationScopeDecision,
    ImplementationContract,
    ProviderSelectionFailClosedBoundary,
    DisabledProviderBoundary,
    SourceGuardCoverage,
    RedactionBoundary,
    ProductionProviderAcceptanceContract,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck {
    CurrentBranchModelOnly,
    CurrentBranchStillDisabled,
    PriorIdentityDecisionEvidenceIncluded,
    PriorIdentityIsolationEvidenceIncluded,
    PriorSyntheticNamespaceEvidenceIncluded,
    PriorSourceSetConfinementEvidenceIncluded,
    PriorImplementationDecisionEvidenceIncluded,
    PriorPrerequisiteAuditEvidenceIncluded,
    PriorScopeDecisionEvidenceIncluded,
    PriorImplementationContractEvidenceIncluded,
    PriorEvidenceNonAuthorizing,
    DecisionGateBlocked,
    PrerequisitesIncomplete,
    ScopeDecisionBlocked,
    ContractNonAuthorizing,
    ExplicitFutureBranchApprovalMissing,
    FutureImplementationReviewIncomplete,
    SourceGuardCoverageNonAuthorizing,
    RedactionReviewNonAuthorizing,
    ProductionSourceAbsenceNonAuthorizing,
    RuntimeReachabilityAbsenceNonAuthorizing,
    ProviderSelectionAbsenceNonAuthorizing,
    RegistryFactoryDispatcherAbsenceNonAuthorizing,
    ExecutorTargetAbsenceNonAuthorizing,
    ProviderKatExecutorAbsenceNonAuthorizing,
    ProviderOperationAbsenceNonAuthorizing,
    CryptoExecutionAbsenceNonAuthorizing,
    VaultLifecycleAbsenceNonAuthorizing,
    PersistenceAbsenceNonAuthorizing,
    SecureStorageAbsenceNonAuthorizing,
    SecureMetadataAbsenceNonAuthorizing,
    BackendClientAbsenceNonAuthorizing,
    BdkWalletStateAbsenceNonAuthorizing,
    SettingsCodecAbsenceNonAuthorizing,
    UiSurfaceAbsenceNonAuthorizing,
    SigningBroadcastingAbsenceNonAuthorizing,
    TorNostrAbsenceNonAuthorizing,
    PublicEndpointAbsenceNonAuthorizing,
    MainnetAbsenceNonAuthorizing,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker {
    CurrentReadinessBlocked,
    ReadinessReviewRequired,
    DependenciesIncomplete,
    ContractNotSatisfiedForImplementation,
    ImplementationNotAuthorized,
    ProductionPromotionNotAuthorized,
    MainnetNotAuthorized,
    PriorEvidenceNonAuthorizing,
    DecisionGateBlocked,
    PrerequisitesIncomplete,
    ScopeDecisionBlocked,
    ContractNonAuthorizing,
    FutureBranchApprovalMissing,
    FutureImplementationReviewIncomplete,
    SourceGuardCoverageNonAuthorizing,
    RedactionEvidenceNonAuthorizing,
    AbsenceEvidenceNonAuthorizing,
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
    CryptoExecutionBlocked,
    VaultLifecycleDisabled,
    PersistenceDisabled,
    ProductionSyncDisabled,
    UserConsentCannotOverride,
    WarningOnlyEvidenceNonAuthorizing,
    TestOnlyEvidenceNonAuthorizing,
    ReleaseEvidenceNonAuthorizing,
    FutureBranchApprovalClaimRejected,
    PrerequisiteCompletionClaimRejected,
    ScopeApprovalClaimRejected,
    ContractSatisfactionClaimRejected,
    ReadinessClaimRejected,
    ReadinessAsImplementationAuthorizationRejected,
    ProviderImplementationClaimRejected,
    RegistryEntryClaimRejected,
    FactoryReachabilityRejected,
    DispatcherReachabilityRejected,
    ExecutorTargetRejected,
    ProviderKatExecutorRejected,
    ProviderOperationRejected,
    CryptoExecutionRejected,
    PersistenceReachabilityRejected,
    SettingsReachabilityRejected,
    UiReachabilityRejected,
    MainnetReachabilityRejected,
    MainnetDisabled,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessRequiredEvidenceClass {
    IdentityDecisionEvidence,
    IdentityIsolationEvidence,
    SyntheticNamespaceEvidence,
    SourceSetConfinementEvidence,
    ImplementationDecisionEvidence,
    PrerequisiteAuditEvidence,
    ScopeDecisionEvidence,
    ImplementationContractEvidence,
    ProviderSelectionFailClosedEvidence,
    DisabledProviderEvidence,
    ProductionProviderAcceptanceEvidence,
    SourceGuardCoverageEvidence,
    RedactionLeakageReviewEvidence,
    FutureBranchApprovalEvidence,
    FutureImplementationReviewEvidence,
    ProductionAbsenceEvidence,
    RuntimeReachabilityAbsenceEvidence,
    ProviderSelectionAbsenceEvidence,
    RegistryFactoryDispatcherAbsenceEvidence,
    ExecutorAndKatAbsenceEvidence,
    ProviderOperationAbsenceEvidence,
    CryptoExecutionAbsenceEvidence,
    VaultStorageSyncAbsenceEvidence,
    BackendSettingsUiAbsenceEvidence,
    SigningBroadcastingNetworkAbsenceEvidence,
    MainnetAbsenceEvidence,
    NonAuthorizationEvidence,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenReadinessShortcut {
    ReadinessGateImpliesImplementation,
    ReadinessGateImpliesContractSatisfied,
    ReadinessGateImpliesPrerequisitesSatisfied,
    ReadinessGateImpliesFutureBranchApproved,
    PriorEvidenceImpliesReadiness,
    SourceGuardPassingImpliesReadiness,
    RedactionEvidenceImpliesReadiness,
    AbsenceEvidenceImpliesReadiness,
    DocumentationImpliesReadiness,
    TestOnlyEvidenceImpliesReadiness,
    PublicVectorEvidenceImpliesReadiness,
    KATHarnessEvidenceImpliesReadiness,
    UserConsentImpliesReadiness,
    WarningOnlyEvidenceImpliesReadiness,
    ReleaseClaimImpliesReadiness,
    DisabledProviderImpliesTestProvider,
    ProviderSelectionFallbackImpliesIdentityImplementation,
    ContractSatisfactionClaimImpliesImplementation,
    FutureReviewLabelImpliesImplementation,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage {
    ReadinessGateToProviderSelectionRuntime,
    ReadinessGateToProviderRegistryRuntime,
    ReadinessGateToProviderFactoryRuntime,
    ReadinessGateToProviderDispatcherRuntime,
    ReadinessGateToExecutorTargetRuntime,
    ReadinessGateToProviderKatExecutorRuntime,
    ReadinessGateToProviderOperationRuntime,
    ReadinessGateToRandomnessRuntime,
    ReadinessGateToKdfRuntime,
    ReadinessGateToHkdfRuntime,
    ReadinessGateToHmacRuntime,
    ReadinessGateToAeadRuntime,
    ReadinessGateToKeyGenerationRuntime,
    ReadinessGateToKeysetStorageRuntime,
    ReadinessGateToVaultCreationRuntime,
    ReadinessGateToVaultUnlockRuntime,
    ReadinessGateToVaultSessionRuntime,
    ReadinessGateToVaultPersistenceRuntime,
    ReadinessGateToSecureStorageRuntime,
    ReadinessGateToSecureMetadataRuntime,
    ReadinessGateToStorageNamespaceRuntime,
    ReadinessGateToStoragePathRuntime,
    ReadinessGateToManifestRuntime,
    ReadinessGateToMigrationRuntime,
    ReadinessGateToProductionSyncRuntime,
    ReadinessGateToBackendClientRuntime,
    ReadinessGateToBdkWalletStateRuntime,
    ReadinessGateToSettingsCodecRuntime,
    ReadinessGateToUiRuntime,
    ReadinessGateToSigningRuntime,
    ReadinessGateToBroadcastingRuntime,
    ReadinessGateToTorRuntime,
    ReadinessGateToNostrRuntime,
    ReadinessGateToPublicEndpointRuntime,
    ReadinessGateToMainnetRuntime,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenPromotionPath {
    ReadinessGateToProviderImplementation,
    ReadinessGateToTestOnlyProviderIdentityImplementation,
    ReadinessGateToProductionProviderIdentityImplementation,
    ReadinessGateToProviderSelection,
    ReadinessGateToProductionProviderSelectable,
    ReadinessGateToRegistryEntry,
    ReadinessGateToFactory,
    ReadinessGateToDispatcher,
    ReadinessGateToExecutorTarget,
    ReadinessGateToProviderKatExecutor,
    ReadinessGateToProviderOperation,
    ReadinessGateToRuntimeRandomness,
    ReadinessGateToKdf,
    ReadinessGateToHkdf,
    ReadinessGateToHmac,
    ReadinessGateToAead,
    ReadinessGateToKeyGeneration,
    ReadinessGateToKeysetStorage,
    ReadinessGateToVaultCreation,
    ReadinessGateToVaultUnlock,
    ReadinessGateToVaultSession,
    ReadinessGateToVaultPersistence,
    ReadinessGateToSecureStorageSuccess,
    ReadinessGateToSecureMetadataSuccess,
    ReadinessGateToManifestReadWrite,
    ReadinessGateToMigration,
    ReadinessGateToProductionSync,
    ReadinessGateToBackendClient,
    ReadinessGateToBdkWalletState,
    ReadinessGateToSettingsCodec,
    ReadinessGateToUiSurface,
    ReadinessGateToSigning,
    ReadinessGateToBroadcasting,
    ReadinessGateToTorTransport,
    ReadinessGateToNostrParsing,
    ReadinessGateToPublicEndpointDefault,
    ReadinessGateToMainnet,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessEvidenceSource {
    TestOnlyProviderIdentityDecision,
    TestOnlyProviderIdentityIsolationGuard,
    SyntheticIdentityNamespace,
    TestOnlyProviderIdentitySourceSetConfinement,
    TestOnlyProviderIdentityImplementationDecision,
    TestOnlyProviderIdentityImplementationPrerequisiteAudit,
    TestOnlyProviderIdentityImplementationScopeDecision,
    TestOnlyProviderIdentityImplementationContract,
    ProviderSelectionBoundary,
    DisabledProviderBoundary,
    ProviderRegistryIsolationGuard,
    ProviderFactoryIsolationBoundary,
    ProviderOperationDispatchIsolation,
    ProviderKatExecutionIsolation,
    ProviderSelectionPromotionBlockers,
    ProductionProviderAcceptanceContract,
    SourceGuardEvidence,
    RedactionLeakageBoundary,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessRedactionClass {
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
    SafePolicyIdOnly,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessFutureReviewRequirement {
    ExplicitFutureBranchApproval,
    ReadinessReviewRequest,
    DependencyEvidenceReview,
    ContractSatisfactionReview,
    SourceGuardCoverageReview,
    RedactionAndLeakageReview,
    ProductionAbsenceReview,
    RuntimeReachabilityAbsenceReview,
    ProviderSelectionAbsenceReview,
    RegistryFactoryDispatcherAbsenceReview,
    ExecutorTargetAndKatExecutorAbsenceReview,
    ProviderOperationAbsenceReview,
    CryptoExecutionAbsenceReview,
    VaultLifecycleAndPersistenceAbsenceReview,
    SecureStorageAndMetadataAbsenceReview,
    BackendBdkSettingsUiAbsenceReview,
    SigningBroadcastingTorNostrEndpointAbsenceReview,
    NonAuthorizationReview,
    FutureImplementationReview,
    MainnetNonAuthorizationReview,
}

data class SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSectionRow(
    val section: SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection,
    val modeled: Boolean,
    val futureReviewOnly: Boolean,
    val currentImplementationAllowed: Boolean,
    val nonAuthorizing: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessDependencyRow(
    val dependency: SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessDependency,
    val included: Boolean,
    val modeled: Boolean,
    val nonAuthorizing: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheckRow(
    val check: SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck,
    val section: SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection,
    val modeled: Boolean,
    val currentEvidenceOnly: Boolean,
    val futureReviewRequired: Boolean,
    val authorizesImplementation: Boolean,
    val nonAuthorizing: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessRequiredEvidenceRow(
    val evidenceClass: SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessRequiredEvidenceClass,
    val modeled: Boolean,
    val presentAsModelEvidence: Boolean,
    val futureRequired: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenReadinessShortcutRow(
    val shortcut: SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenReadinessShortcut,
    val forbidden: Boolean,
    val currentBridgePresent: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkageRow(
    val runtimeLinkage: SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage,
    val section: SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection,
    val forbidden: Boolean,
    val currentBridgePresent: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenPromotionPathRow(
    val promotionRoute: SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenPromotionPath,
    val forbidden: Boolean,
    val currentBridgePresent: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessEvidenceSourceRow(
    val evidenceSource: SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessEvidenceSource,
    val included: Boolean,
    val modeled: Boolean,
    val nonAuthorizing: Boolean,
    val authorizesImplementation: Boolean,
    val authorizesProductionPromotion: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessFutureReviewRow(
    val requirement: SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessFutureReviewRequirement,
    val futureRequired: Boolean,
    val satisfiedNow: Boolean,
    val authorizesCurrentImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateCapabilities(
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
        val Current = SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateCapabilities(
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

data class SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateRequest(
    val includePriorIdentityDecisionEvidence: Boolean = true,
    val includePriorIsolationEvidence: Boolean = true,
    val includePriorNamespaceEvidence: Boolean = true,
    val includePriorSourceSetConfinementEvidence: Boolean = true,
    val includePriorImplementationDecisionEvidence: Boolean = true,
    val includePriorPrerequisiteAuditEvidence: Boolean = true,
    val includePriorScopeDecisionEvidence: Boolean = true,
    val includePriorImplementationContractEvidence: Boolean = true,
    val userConsentOverrideRequested: Boolean = false,
    val warningOnlyEvidenceClaimed: Boolean = false,
    val testOnlyEvidenceClaimedAsProductionPromotion: Boolean = false,
    val releaseEvidenceClaimed: Boolean = false,
    val futureBranchApprovalClaimed: Boolean = false,
    val prerequisiteCompletionClaimed: Boolean = false,
    val scopeApprovalClaimed: Boolean = false,
    val contractSatisfactionClaimed: Boolean = false,
    val readinessClaimed: Boolean = false,
    val readinessClaimedAsImplementationAuthorization: Boolean = false,
    val providerImplementationClaimed: Boolean = false,
    val registryEntryClaimed: Boolean = false,
    val factoryReachabilityClaimed: Boolean = false,
    val dispatcherReachabilityClaimed: Boolean = false,
    val executorTargetClaimed: Boolean = false,
    val providerKatExecutorClaimed: Boolean = false,
    val providerOperationClaimed: Boolean = false,
    val cryptoExecutionClaimed: Boolean = false,
    val persistenceReachabilityClaimed: Boolean = false,
    val settingsReachabilityClaimed: Boolean = false,
    val uiReachabilityClaimed: Boolean = false,
    val mainnetReachabilityClaimed: Boolean = false,
) {
    override fun toString(): String = "RedactedRequest(claims=modeled)"
}

data class SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateEvidence(
    val policyId: String,
    val policyVersion: Int,
    val readinessGateModeled: Boolean,
    val stillDisabled: Boolean,
    val priorIdentityDecisionEvidenceIncluded: Boolean,
    val priorIsolationEvidenceIncluded: Boolean,
    val priorNamespaceEvidenceIncluded: Boolean,
    val priorSourceSetConfinementEvidenceIncluded: Boolean,
    val priorImplementationDecisionEvidenceIncluded: Boolean,
    val priorPrerequisiteAuditEvidenceIncluded: Boolean,
    val priorScopeDecisionEvidenceIncluded: Boolean,
    val priorImplementationContractEvidenceIncluded: Boolean,
    val readinessDependenciesModeled: Boolean,
    val readinessChecksModeled: Boolean,
    val forbiddenRuntimeLinkagesModeled: Boolean,
    val docsMayDescribeFutureReview: Boolean,
    val testsMayAssertBlockedReadiness: Boolean,
    val statuses: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateStatus>,
    val outcomes: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessOutcome>,
    val sectionRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSectionRow>,
    val dependencyRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessDependencyRow>,
    val checkRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheckRow>,
    val requiredEvidenceRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessRequiredEvidenceRow>,
    val forbiddenReadinessShortcutRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenReadinessShortcutRow>,
    val forbiddenRuntimeLinkageRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkageRow>,
    val forbiddenPromotionPathRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenPromotionPathRow>,
    val evidenceSourceRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessEvidenceSourceRow>,
    val futureReviewRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessFutureReviewRow>,
    val blockers: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker>,
    val redactionClasses: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessRedactionClass>,
    val capabilities: SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateCapabilities,
)

object SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGatePolicy {
    const val POLICY_ID: String =
        "skald-vault-v1-test-only-provider-identity-implementation-readiness-gate-v1"
    const val POLICY_VERSION: Int = 1

    fun currentReadinessGateEvidence():
        SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateEvidence =
        evaluateReadinessGate()

    fun evaluateReadinessGate(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateRequest =
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateRequest(),
    ): SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateEvidence =
        SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateEvidence(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            readinessGateModeled = true,
            stillDisabled = true,
            priorIdentityDecisionEvidenceIncluded = request.includePriorIdentityDecisionEvidence,
            priorIsolationEvidenceIncluded = request.includePriorIsolationEvidence,
            priorNamespaceEvidenceIncluded = request.includePriorNamespaceEvidence,
            priorSourceSetConfinementEvidenceIncluded = request.includePriorSourceSetConfinementEvidence,
            priorImplementationDecisionEvidenceIncluded = request.includePriorImplementationDecisionEvidence,
            priorPrerequisiteAuditEvidenceIncluded = request.includePriorPrerequisiteAuditEvidence,
            priorScopeDecisionEvidenceIncluded = request.includePriorScopeDecisionEvidence,
            priorImplementationContractEvidenceIncluded = request.includePriorImplementationContractEvidence,
            readinessDependenciesModeled = true,
            readinessChecksModeled = true,
            forbiddenRuntimeLinkagesModeled = true,
            docsMayDescribeFutureReview = true,
            testsMayAssertBlockedReadiness = true,
            statuses = SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateStatus.entries.toSet(),
            outcomes = SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessOutcome.entries.toSet(),
            sectionRows = currentSectionRows(),
            dependencyRows = currentDependencyRows(request),
            checkRows = currentCheckRows(),
            requiredEvidenceRows = currentRequiredEvidenceRows(request),
            forbiddenReadinessShortcutRows = currentForbiddenReadinessShortcutRows(),
            forbiddenRuntimeLinkageRows = currentForbiddenRuntimeLinkageRows(),
            forbiddenPromotionPathRows = currentForbiddenPromotionPathRows(),
            evidenceSourceRows = currentEvidenceSourceRows(request),
            futureReviewRows = currentFutureReviewRows(),
            blockers = baseBlockers() + requestBlockers(request),
            redactionClasses =
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessRedactionClass.entries.toSet(),
            capabilities = SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateCapabilities.Current,
        )

    private fun currentSectionRows(): List<SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSectionRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.entries.map { section ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSectionRow(
                section = section,
                modeled = true,
                futureReviewOnly = section in futureReviewSections(),
                currentImplementationAllowed = false,
                nonAuthorizing = true,
                label = redactedLabel(),
            )
        }

    private fun currentDependencyRows(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateRequest,
    ): List<SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessDependencyRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessDependency.entries.map { dependency ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessDependencyRow(
                dependency = dependency,
                included = dependencyIncluded(dependency, request),
                modeled = true,
                nonAuthorizing = true,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentCheckRows(): List<SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheckRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.entries.map { check ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheckRow(
                check = check,
                section = sectionFor(check),
                modeled = true,
                currentEvidenceOnly = check in currentEvidenceOnlyChecks(),
                futureReviewRequired = check !in currentEvidenceOnlyChecks(),
                authorizesImplementation = false,
                nonAuthorizing = true,
                label = redactedLabel(),
            )
        }

    private fun currentRequiredEvidenceRows(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateRequest,
    ): List<SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessRequiredEvidenceRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessRequiredEvidenceClass.entries.map { evidenceClass ->
            val presentAsModelEvidence = when (evidenceClass) {
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessRequiredEvidenceClass
                    .IdentityDecisionEvidence ->
                    request.includePriorIdentityDecisionEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessRequiredEvidenceClass
                    .IdentityIsolationEvidence ->
                    request.includePriorIsolationEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessRequiredEvidenceClass
                    .SyntheticNamespaceEvidence ->
                    request.includePriorNamespaceEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessRequiredEvidenceClass
                    .SourceSetConfinementEvidence ->
                    request.includePriorSourceSetConfinementEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessRequiredEvidenceClass
                    .ImplementationDecisionEvidence ->
                    request.includePriorImplementationDecisionEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessRequiredEvidenceClass
                    .PrerequisiteAuditEvidence ->
                    request.includePriorPrerequisiteAuditEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessRequiredEvidenceClass.ScopeDecisionEvidence ->
                    request.includePriorScopeDecisionEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessRequiredEvidenceClass
                    .ImplementationContractEvidence ->
                    request.includePriorImplementationContractEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessRequiredEvidenceClass
                    .ProviderSelectionFailClosedEvidence,
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessRequiredEvidenceClass
                    .DisabledProviderEvidence,
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessRequiredEvidenceClass
                    .ProductionProviderAcceptanceEvidence,
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessRequiredEvidenceClass
                    .SourceGuardCoverageEvidence,
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessRequiredEvidenceClass.NonAuthorizationEvidence ->
                    true
                else -> false
            }
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessRequiredEvidenceRow(
                evidenceClass = evidenceClass,
                modeled = true,
                presentAsModelEvidence = presentAsModelEvidence,
                futureRequired = !presentAsModelEvidence || evidenceClass in futureReviewEvidenceClasses(),
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentForbiddenReadinessShortcutRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenReadinessShortcutRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenReadinessShortcut.entries.map { shortcut ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenReadinessShortcutRow(
                shortcut = shortcut,
                forbidden = true,
                currentBridgePresent = false,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentForbiddenRuntimeLinkageRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkageRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage.entries.map { linkage ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkageRow(
                runtimeLinkage = linkage,
                section = sectionFor(linkage),
                forbidden = true,
                currentBridgePresent = false,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentForbiddenPromotionPathRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenPromotionPathRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenPromotionPath.entries.map { route ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenPromotionPathRow(
                promotionRoute = route,
                forbidden = true,
                currentBridgePresent = false,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentEvidenceSourceRows(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateRequest,
    ): List<SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessEvidenceSourceRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessEvidenceSource.entries.map { source ->
            val included = when (source) {
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessEvidenceSource
                    .TestOnlyProviderIdentityDecision ->
                    request.includePriorIdentityDecisionEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessEvidenceSource
                    .TestOnlyProviderIdentityIsolationGuard ->
                    request.includePriorIsolationEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessEvidenceSource.SyntheticIdentityNamespace ->
                    request.includePriorNamespaceEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessEvidenceSource
                    .TestOnlyProviderIdentitySourceSetConfinement ->
                    request.includePriorSourceSetConfinementEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessEvidenceSource
                    .TestOnlyProviderIdentityImplementationDecision ->
                    request.includePriorImplementationDecisionEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessEvidenceSource
                    .TestOnlyProviderIdentityImplementationPrerequisiteAudit ->
                    request.includePriorPrerequisiteAuditEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessEvidenceSource
                    .TestOnlyProviderIdentityImplementationScopeDecision ->
                    request.includePriorScopeDecisionEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessEvidenceSource
                    .TestOnlyProviderIdentityImplementationContract ->
                    request.includePriorImplementationContractEvidence
                else -> true
            }
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessEvidenceSourceRow(
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
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessFutureReviewRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessFutureReviewRequirement.entries.map { requirement ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessFutureReviewRow(
                requirement = requirement,
                futureRequired = true,
                satisfiedNow = false,
                authorizesCurrentImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun dependencyIncluded(
        dependency: SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessDependency,
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateRequest,
    ): Boolean =
        when (dependency) {
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessDependency.IdentityDecisionBoundary ->
                request.includePriorIdentityDecisionEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessDependency.IdentityIsolationGuard ->
                request.includePriorIsolationEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessDependency
                .SyntheticIdentityNamespaceContract ->
                request.includePriorNamespaceEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessDependency
                .IdentitySourceSetConfinementBoundary ->
                request.includePriorSourceSetConfinementEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessDependency.ImplementationDecisionGate ->
                request.includePriorImplementationDecisionEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessDependency.ImplementationPrerequisiteAudit ->
                request.includePriorPrerequisiteAuditEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessDependency.ImplementationScopeDecision ->
                request.includePriorScopeDecisionEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessDependency.ImplementationContract ->
                request.includePriorImplementationContractEvidence
            else -> true
        }

    private fun futureReviewSections(): Set<SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection> =
        setOf(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.FutureReviewOnlyReadiness,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.DecisionGateReadiness,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.PrerequisiteAuditReadiness,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.ScopeDecisionReadiness,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.ContractReadiness,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.SourceGuardReadiness,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.RedactionReadiness,
        )

    private fun currentEvidenceOnlyChecks(): Set<SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck> =
        setOf(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.CurrentBranchModelOnly,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.CurrentBranchStillDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.PriorIdentityDecisionEvidenceIncluded,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.PriorIdentityIsolationEvidenceIncluded,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.PriorSyntheticNamespaceEvidenceIncluded,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.PriorSourceSetConfinementEvidenceIncluded,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.PriorImplementationDecisionEvidenceIncluded,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.PriorPrerequisiteAuditEvidenceIncluded,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.PriorScopeDecisionEvidenceIncluded,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck
                .PriorImplementationContractEvidenceIncluded,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.PriorEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.DecisionGateBlocked,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.PrerequisitesIncomplete,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.ScopeDecisionBlocked,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.ContractNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.SourceGuardCoverageNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.RedactionReviewNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.ProductionSourceAbsenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck
                .RuntimeReachabilityAbsenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.ProviderSelectionAbsenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck
                .RegistryFactoryDispatcherAbsenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.ExecutorTargetAbsenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.ProviderKatExecutorAbsenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.ProviderOperationAbsenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.CryptoExecutionAbsenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.VaultLifecycleAbsenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.PersistenceAbsenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.SecureStorageAbsenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.SecureMetadataAbsenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.BackendClientAbsenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.BdkWalletStateAbsenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.SettingsCodecAbsenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.UiSurfaceAbsenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.SigningBroadcastingAbsenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.TorNostrAbsenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.PublicEndpointAbsenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.MainnetAbsenceNonAuthorizing,
        )

    private fun futureReviewEvidenceClasses():
        Set<SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessRequiredEvidenceClass> =
        setOf(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessRequiredEvidenceClass
                .RedactionLeakageReviewEvidence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessRequiredEvidenceClass
                .FutureBranchApprovalEvidence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessRequiredEvidenceClass
                .FutureImplementationReviewEvidence,
        )

    private fun sectionFor(
        check: SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck,
    ): SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection =
        when (check) {
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.CurrentBranchModelOnly,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.CurrentBranchStillDisabled ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.ModelOnlyReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck
                .PriorIdentityDecisionEvidenceIncluded,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck
                .PriorIdentityIsolationEvidenceIncluded,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck
                .PriorSyntheticNamespaceEvidenceIncluded,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck
                .PriorSourceSetConfinementEvidenceIncluded,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck
                .PriorImplementationDecisionEvidenceIncluded,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck
                .PriorPrerequisiteAuditEvidenceIncluded,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.PriorScopeDecisionEvidenceIncluded,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck
                .PriorImplementationContractEvidenceIncluded,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.PriorEvidenceNonAuthorizing ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.PriorEvidenceReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.DecisionGateBlocked,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.ExplicitFutureBranchApprovalMissing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck
                .FutureImplementationReviewIncomplete ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.DecisionGateReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.PrerequisitesIncomplete ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.PrerequisiteAuditReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.ScopeDecisionBlocked ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.ScopeDecisionReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.ContractNonAuthorizing ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.ContractReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.SourceGuardCoverageNonAuthorizing ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.SourceGuardReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.RedactionReviewNonAuthorizing ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.RedactionReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.ProductionSourceAbsenceNonAuthorizing ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.ProductionAbsenceReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck
                .RuntimeReachabilityAbsenceNonAuthorizing ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection
                    .RuntimeReachabilityAbsenceReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.ProviderSelectionAbsenceNonAuthorizing ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.ProviderSelectionAbsenceReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck
                .RegistryFactoryDispatcherAbsenceNonAuthorizing ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection
                    .RegistryFactoryDispatcherAbsenceReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.ExecutorTargetAbsenceNonAuthorizing ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.ExecutorTargetAbsenceReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck
                .ProviderKatExecutorAbsenceNonAuthorizing ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.ProviderKatExecutorAbsenceReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.ProviderOperationAbsenceNonAuthorizing ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.ProviderOperationAbsenceReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.CryptoExecutionAbsenceNonAuthorizing ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.CryptoExecutionAbsenceReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.VaultLifecycleAbsenceNonAuthorizing ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.VaultLifecycleAbsenceReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.PersistenceAbsenceNonAuthorizing ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.PersistenceAbsenceReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.SecureStorageAbsenceNonAuthorizing ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.SecureStorageAbsenceReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.SecureMetadataAbsenceNonAuthorizing ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.SecureMetadataAbsenceReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.BackendClientAbsenceNonAuthorizing ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.BackendClientAbsenceReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.BdkWalletStateAbsenceNonAuthorizing ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.BdkWalletStateAbsenceReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.SettingsCodecAbsenceNonAuthorizing ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.SettingsCodecAbsenceReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.UiSurfaceAbsenceNonAuthorizing ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.UiSurfaceAbsenceReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck
                .SigningBroadcastingAbsenceNonAuthorizing ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection
                    .SigningBroadcastingAbsenceReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.TorNostrAbsenceNonAuthorizing ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.TorNostrAbsenceReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.PublicEndpointAbsenceNonAuthorizing ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.PublicEndpointAbsenceReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessCheck.MainnetAbsenceNonAuthorizing ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.MainnetAbsenceReadiness
        }

    private fun sectionFor(
        linkage: SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage,
    ): SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection =
        when (linkage) {
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage
                .ReadinessGateToProviderSelectionRuntime ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.ProviderSelectionAbsenceReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage
                .ReadinessGateToProviderRegistryRuntime,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage
                .ReadinessGateToProviderFactoryRuntime,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage
                .ReadinessGateToProviderDispatcherRuntime ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection
                    .RegistryFactoryDispatcherAbsenceReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage
                .ReadinessGateToExecutorTargetRuntime ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.ExecutorTargetAbsenceReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage
                .ReadinessGateToProviderKatExecutorRuntime ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.ProviderKatExecutorAbsenceReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage
                .ReadinessGateToProviderOperationRuntime ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.ProviderOperationAbsenceReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage
                .ReadinessGateToRandomnessRuntime,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage
                .ReadinessGateToKdfRuntime,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage
                .ReadinessGateToHkdfRuntime,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage
                .ReadinessGateToHmacRuntime,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage
                .ReadinessGateToAeadRuntime,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage
                .ReadinessGateToKeyGenerationRuntime,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage
                .ReadinessGateToKeysetStorageRuntime ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.CryptoExecutionAbsenceReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage
                .ReadinessGateToVaultCreationRuntime,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage
                .ReadinessGateToVaultUnlockRuntime,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage
                .ReadinessGateToVaultSessionRuntime ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.VaultLifecycleAbsenceReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage
                .ReadinessGateToVaultPersistenceRuntime,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage
                .ReadinessGateToStorageNamespaceRuntime,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage
                .ReadinessGateToStoragePathRuntime,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage
                .ReadinessGateToManifestRuntime,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage
                .ReadinessGateToMigrationRuntime ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.PersistenceAbsenceReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage
                .ReadinessGateToSecureStorageRuntime ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.SecureStorageAbsenceReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage
                .ReadinessGateToSecureMetadataRuntime ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.SecureMetadataAbsenceReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage
                .ReadinessGateToProductionSyncRuntime ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection
                    .RuntimeReachabilityAbsenceReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage
                .ReadinessGateToBackendClientRuntime ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.BackendClientAbsenceReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage
                .ReadinessGateToBdkWalletStateRuntime ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.BdkWalletStateAbsenceReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage
                .ReadinessGateToSettingsCodecRuntime ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.SettingsCodecAbsenceReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage
                .ReadinessGateToUiRuntime ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.UiSurfaceAbsenceReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage
                .ReadinessGateToSigningRuntime,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage
                .ReadinessGateToBroadcastingRuntime ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection
                    .SigningBroadcastingAbsenceReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage
                .ReadinessGateToTorRuntime,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage
                .ReadinessGateToNostrRuntime ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.TorNostrAbsenceReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage
                .ReadinessGateToPublicEndpointRuntime ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.PublicEndpointAbsenceReadiness
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessForbiddenRuntimeLinkage
                .ReadinessGateToMainnetRuntime ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessSection.MainnetAbsenceReadiness
        }

    private fun baseBlockers(): Set<SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker> =
        setOf(
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.CurrentReadinessBlocked,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.ReadinessReviewRequired,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.DependenciesIncomplete,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker
                .ContractNotSatisfiedForImplementation,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.ImplementationNotAuthorized,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.ProductionPromotionNotAuthorized,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.MainnetNotAuthorized,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.PriorEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.DecisionGateBlocked,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.PrerequisitesIncomplete,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.ScopeDecisionBlocked,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.ContractNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.FutureBranchApprovalMissing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.FutureImplementationReviewIncomplete,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.SourceGuardCoverageNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.RedactionEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.AbsenceEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.NoTestOnlyProviderIdentityImplementation,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.NoProductionProviderIdentityImplementation,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.NoProviderImplementation,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.NoProviderFactory,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.NoProviderDispatcher,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.NoNonDisabledRegistryEntry,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.NoExecutorTarget,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.NoProviderKatExecutor,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.NoRunnableExecutorInterface,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.ProviderSelectionDisabledProviderOnly,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.ProductionProviderSelectableFalse,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.ProviderOperationAuthorizationBlocked,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.CryptoExecutionBlocked,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.VaultLifecycleDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.PersistenceDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.ProductionSyncDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.UserConsentCannotOverride,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.WarningOnlyEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.TestOnlyEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.ReleaseEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.MainnetDisabled,
        )

    private fun requestBlockers(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateRequest,
    ): Set<SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker> =
        buildSet {
            if (request.userConsentOverrideRequested) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.UserConsentCannotOverride)
            }
            if (request.warningOnlyEvidenceClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker
                        .WarningOnlyEvidenceNonAuthorizing,
                )
            }
            if (request.testOnlyEvidenceClaimedAsProductionPromotion) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker
                        .TestOnlyEvidenceNonAuthorizing,
                )
            }
            if (request.releaseEvidenceClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.ReleaseEvidenceNonAuthorizing)
            }
            if (request.futureBranchApprovalClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker
                        .FutureBranchApprovalClaimRejected,
                )
            }
            if (request.prerequisiteCompletionClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker
                        .PrerequisiteCompletionClaimRejected,
                )
            }
            if (request.scopeApprovalClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.ScopeApprovalClaimRejected)
            }
            if (request.contractSatisfactionClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker
                        .ContractSatisfactionClaimRejected,
                )
            }
            if (request.readinessClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.ReadinessClaimRejected)
            }
            if (request.readinessClaimedAsImplementationAuthorization) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker
                        .ReadinessAsImplementationAuthorizationRejected,
                )
            }
            if (request.providerImplementationClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker
                        .ProviderImplementationClaimRejected,
                )
            }
            if (request.registryEntryClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.RegistryEntryClaimRejected)
            }
            if (request.factoryReachabilityClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.FactoryReachabilityRejected)
            }
            if (request.dispatcherReachabilityClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.DispatcherReachabilityRejected)
            }
            if (request.executorTargetClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.ExecutorTargetRejected)
            }
            if (request.providerKatExecutorClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.ProviderKatExecutorRejected)
            }
            if (request.providerOperationClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.ProviderOperationRejected)
            }
            if (request.cryptoExecutionClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.CryptoExecutionRejected)
            }
            if (request.persistenceReachabilityClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker
                        .PersistenceReachabilityRejected,
                )
            }
            if (request.settingsReachabilityClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.SettingsReachabilityRejected)
            }
            if (request.uiReachabilityClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.UiReachabilityRejected)
            }
            if (request.mainnetReachabilityClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessBlocker.MainnetReachabilityRejected)
            }
        }

    private fun redactedLabel(): SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateSafeLabel =
        SkaldVaultV1TestOnlyProviderIdentityImplementationReadinessGateSafeLabel("redacted")
}
