package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionSafeLabel(
    val value: String,
) {
    override fun toString(): String = "RedactedLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionStatus {
    AdmissionGateModeled,
    StillDisabled,
    CurrentAdmissionDenied,
    PriorEvidenceNonAuthorizing,
    DependenciesIncomplete,
    ImplementationNotAuthorized,
    ProductionPromotionNotAuthorized,
    MainnetNotAuthorized,
    ProviderSelectionDisabledProviderOnly,
    ProductionProviderSelectableFalse,
    NoRuntimeReachability,
    NoPromotionReachability,
    NoLeakageOutputs,
    NoPositiveRuntimeFlags,
    NoPositivePromotionFlags,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionOutcome {
    CurrentAdmissionDenied,
    AdmissionReviewRequired,
    DependenciesIncomplete,
    ImplementationNotAuthorized,
    ProductionPromotionNotAuthorized,
    MainnetNotAuthorized,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionSection {
    ModelOnlyAdmission,
    PriorEvidenceAdmission,
    DecisionGateAdmission,
    PrerequisiteAuditAdmission,
    ScopeDecisionAdmission,
    ImplementationContractAdmission,
    ReadinessGateAdmission,
    RuntimeLinkageAdmission,
    PromotionBlockerAdmission,
    SourceGuardCoverageAdmission,
    RedactionGuardAdmission,
    ProviderSelectionAdmission,
    ProductionPromotionAdmission,
    VaultPersistenceAdmission,
    SyncSigningBroadcastingAdmission,
    MainnetAdmission,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionCheck {
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
    PriorReadinessGateEvidenceIncluded,
    PriorRuntimeLinkageGuardEvidenceIncluded,
    PriorPromotionBlockersEvidenceIncluded,
    PriorSourceGuardCoverageEvidenceIncluded,
    PriorRedactionGuardEvidenceIncluded,
    AllPriorEvidenceNonAuthorizing,
    NoFutureBranchApproval,
    NoImplementationAdmission,
    NoTestOnlyIdentityImplementation,
    NoProductionIdentityImplementation,
    ProviderSelectionDisabledProviderOnly,
    ProductionProviderSelectableFalse,
    NoRegistryFactoryDispatcherReachability,
    NoExecutorTargetReachability,
    NoProviderKatExecutorReachability,
    NoProviderOperationExecution,
    NoCryptoExecution,
    NoVaultLifecycleExecution,
    NoVaultPersistence,
    NoSecureStorageSuccess,
    NoSecureMetadataSuccess,
    NoProductionSync,
    NoBackendClient,
    NoBdkWalletState,
    NoSettingsPersistence,
    NoUiSurface,
    NoSigningBroadcasting,
    NoTorNostrPublicEndpoint,
    MainnetDisabled,
    NoLeakageOutputs,
    NoPositiveRuntimeFlags,
    NoPositivePromotionFlags,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker {
    AdmissionGateModelOnly,
    CurrentAdmissionDenied,
    AdmissionEvidenceNonAuthorizing,
    PriorEvidenceNonAuthorizing,
    SourceGuardCoverageNonAuthorizing,
    RedactionGuardNonAuthorizing,
    NoFutureBranchApproval,
    NoTestOnlyProviderIdentityImplementation,
    NoProductionProviderIdentityImplementation,
    NoProviderImplementation,
    NoProviderSelectionAdmission,
    NoProviderFactory,
    NoProviderDispatcher,
    NoNonDisabledRegistryEntry,
    NoExecutorTarget,
    NoProviderKatExecutor,
    ProviderSelectionDisabledProviderOnly,
    ProductionProviderSelectableFalse,
    ProviderOperationAuthorizationBlocked,
    CryptoExecutionBlocked,
    VaultLifecycleDisabled,
    PersistenceDisabled,
    ProductionSyncDisabled,
    MainnetDisabled,
    UserConsentCannotOverride,
    WarningOnlyEvidenceNonAuthorizing,
    TestOnlyEvidenceNonAuthorizing,
    ReleaseEvidenceNonAuthorizing,
    AdmissionClaimRejected,
    AdmissionAuthorizationClaimRejected,
    FutureBranchApprovalClaimRejected,
    ImplementationClaimRejected,
    ProductionIdentityClaimRejected,
    ProviderSelectionClaimRejected,
    ProductionProviderSelectableClaimRejected,
    RegistryEntryClaimRejected,
    FactoryReachabilityClaimRejected,
    DispatcherReachabilityClaimRejected,
    ExecutorTargetClaimRejected,
    ProviderKatExecutorClaimRejected,
    ProviderOperationClaimRejected,
    CryptoExecutionClaimRejected,
    VaultPersistenceClaimRejected,
    ProductionSyncClaimRejected,
    SettingsPersistenceClaimRejected,
    UiSurfaceClaimRejected,
    SigningBroadcastingClaimRejected,
    PublicEndpointClaimRejected,
    MainnetReachabilityClaimRejected,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionDependency {
    IdentityDecisionBoundary,
    IdentityIsolationGuard,
    SyntheticIdentityNamespaceContract,
    IdentitySourceSetConfinementBoundary,
    ImplementationDecisionGate,
    ImplementationPrerequisiteAudit,
    ImplementationScopeDecision,
    ImplementationContract,
    ImplementationReadinessGate,
    ImplementationRuntimeLinkageGuard,
    ImplementationPromotionBlockers,
    ImplementationSourceGuardCoverage,
    ImplementationRedactionGuard,
    ProviderSelectionFailClosedBoundary,
    DisabledProviderBoundary,
    VaultRedactionLeakageBoundary,
    SourceGuardCoverage,
    ProductionProviderAcceptanceContract,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenAdmissionShortcut {
    AdmissionGateImpliesImplementation,
    AdmissionGateImpliesFutureBranchApproval,
    AdmissionGateImpliesProviderSelection,
    AdmissionGateImpliesProductionProviderSelectable,
    AdmissionGateImpliesRuntimeLinkage,
    AdmissionGateImpliesPromotion,
    PriorEvidenceImpliesAdmission,
    SourceGuardCoverageImpliesAdmission,
    RedactionGuardImpliesAdmission,
    ReadinessGateImpliesAdmission,
    RuntimeLinkageGuardImpliesAdmission,
    PromotionBlockersImpliesAdmission,
    UserConsentImpliesAdmission,
    WarningOnlyEvidenceImpliesAdmission,
    TestOnlyEvidenceImpliesAdmission,
    ReleaseClaimImpliesAdmission,
    DocumentationImpliesAdmission,
    PublicVectorEvidenceImpliesAdmission,
    KATHarnessEvidenceImpliesAdmission,
    SafeLabelSyntaxImpliesAdmission,
    AbsenceEvidenceImpliesAdmission,
    FutureReviewLabelImpliesAdmission,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenAdmissionTarget {
    TestOnlyProviderIdentityImplementationNow,
    ProductionProviderIdentityImplementation,
    VaultCryptoProviderImplementation,
    ProviderSelectionEntry,
    ProductionProviderSelectableTrue,
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

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateForbiddenPromotionPath {
    AdmissionGateToProviderImplementation,
    AdmissionGateToTestOnlyProviderIdentityImplementation,
    AdmissionGateToProductionProviderIdentityImplementation,
    AdmissionGateToProviderSelection,
    AdmissionGateToProductionProviderSelectable,
    AdmissionGateToRegistryEntry,
    AdmissionGateToFactory,
    AdmissionGateToDispatcher,
    AdmissionGateToExecutorTarget,
    AdmissionGateToProviderKatExecutor,
    AdmissionGateToProviderOperation,
    AdmissionGateToRuntimeRandomness,
    AdmissionGateToKdf,
    AdmissionGateToHkdf,
    AdmissionGateToHmac,
    AdmissionGateToAead,
    AdmissionGateToKeyGeneration,
    AdmissionGateToKeysetStorage,
    AdmissionGateToVaultCreation,
    AdmissionGateToVaultUnlock,
    AdmissionGateToVaultSession,
    AdmissionGateToVaultPersistence,
    AdmissionGateToSecureStorageSuccess,
    AdmissionGateToSecureMetadataSuccess,
    AdmissionGateToManifestReadWrite,
    AdmissionGateToMigration,
    AdmissionGateToProductionSync,
    AdmissionGateToBackendClient,
    AdmissionGateToBdkWalletState,
    AdmissionGateToSettingsCodec,
    AdmissionGateToUiSurface,
    AdmissionGateToSigning,
    AdmissionGateToBroadcasting,
    AdmissionGateToTorTransport,
    AdmissionGateToNostrParsing,
    AdmissionGateToPublicEndpointDefault,
    AdmissionGateToMainnet,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequiredEvidenceClass {
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
    PromotionBlockersEvidence,
    SourceGuardCoverageEvidence,
    RedactionGuardEvidence,
    ProviderSelectionFailClosedEvidence,
    DisabledProviderEvidence,
    VaultRedactionBoundaryEvidence,
    ProductionProviderSelectableFalseEvidence,
    RuntimeAbsenceEvidence,
    PromotionAbsenceEvidence,
    LeakageAbsenceEvidence,
    PositiveFlagAbsenceEvidence,
    NonAuthorizationEvidence,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidenceSource {
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
    TestOnlyProviderIdentityImplementationPromotionBlockers,
    TestOnlyProviderIdentityImplementationSourceGuardCoverage,
    TestOnlyProviderIdentityImplementationRedactionGuard,
    VaultRedactionLeakageBoundary,
    ProviderSelectionBoundary,
    DisabledProviderBoundary,
    ProductionProviderAcceptanceContract,
    SourceGuardEvidence,
    DocumentationCrossLink,
    AbsenceEvidence,
    NonAuthorizationEvidence,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRedactionClass {
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
    RedactedSourceGuardReference,
    RedactedRedactionReference,
    RedactedAdmissionReference,
    RedactedDiagnosticReference,
    SafePolicyIdOnly,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateFutureReviewRequirement {
    ExplicitFutureBranchApproval,
    AdmissionReview,
    ImplementationDecisionReview,
    PrerequisiteCompletionReview,
    ScopeReview,
    ContractReview,
    ReadinessReview,
    RuntimeLinkageReview,
    PromotionReview,
    SourceGuardReview,
    RedactionReview,
    ProviderSelectionReview,
    ProductionProviderAcceptanceReview,
    RuntimeRootAbsenceReview,
    NonAuthorizationReview,
    MainnetNonAuthorizationReview,
}

data class SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionSectionRow(
    val section: SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionSection,
    val modeled: Boolean,
    val currentAdmissionGranted: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionCheckRow(
    val check: SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionCheck,
    val modeled: Boolean,
    val currentEvidenceSatisfied: Boolean,
    val nonAuthorizing: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionDependencyRow(
    val dependency: SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionDependency,
    val included: Boolean,
    val modeled: Boolean,
    val nonAuthorizing: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlockerRow(
    val blocker: SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker,
    val active: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenAdmissionShortcutRow(
    val shortcut: SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenAdmissionShortcut,
    val forbidden: Boolean,
    val authorizesAdmission: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenAdmissionTargetRow(
    val target: SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenAdmissionTarget,
    val forbidden: Boolean,
    val admittedNow: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateForbiddenPromotionPathRow(
    val promotionRoute: SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateForbiddenPromotionPath,
    val forbidden: Boolean,
    val admittedNow: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequiredEvidenceRow(
    val evidenceClass: SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequiredEvidenceClass,
    val modeled: Boolean,
    val presentAsModelEvidence: Boolean,
    val futureRequired: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidenceSourceRow(
    val evidenceSource: SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidenceSource,
    val included: Boolean,
    val modeled: Boolean,
    val nonAuthorizing: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateFutureReviewRow(
    val requirement: SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateFutureReviewRequirement,
    val futureRequired: Boolean,
    val satisfiedNow: Boolean,
    val authorizesCurrentImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateCapabilities(
    val admissionGateAuthorizesImplementation: Boolean,
    val redactionGuardAuthorizesImplementation: Boolean,
    val sourceGuardCoverageAuthorizesImplementation: Boolean,
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
        val Current = SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateCapabilities(
            admissionGateAuthorizesImplementation = false,
            redactionGuardAuthorizesImplementation = false,
            sourceGuardCoverageAuthorizesImplementation = false,
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

data class SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequest(
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
    val includePriorPromotionBlockersEvidence: Boolean = true,
    val includePriorSourceGuardCoverageEvidence: Boolean = true,
    val includePriorRedactionGuardEvidence: Boolean = true,
    val userConsentOverrideRequested: Boolean = false,
    val warningOnlyEvidenceClaimed: Boolean = false,
    val testOnlyEvidenceClaimedAsProductionPromotion: Boolean = false,
    val releaseEvidenceClaimed: Boolean = false,
    val admissionClaimed: Boolean = false,
    val admissionClaimedAsImplementationAuthorization: Boolean = false,
    val futureBranchApprovalClaimed: Boolean = false,
    val implementationClaimed: Boolean = false,
    val productionIdentityClaimed: Boolean = false,
    val providerSelectionClaimed: Boolean = false,
    val productionProviderSelectableClaimed: Boolean = false,
    val registryEntryClaimed: Boolean = false,
    val factoryReachabilityClaimed: Boolean = false,
    val dispatcherReachabilityClaimed: Boolean = false,
    val executorTargetClaimed: Boolean = false,
    val providerKatExecutorClaimed: Boolean = false,
    val providerOperationClaimed: Boolean = false,
    val cryptoExecutionClaimed: Boolean = false,
    val vaultPersistenceClaimed: Boolean = false,
    val productionSyncClaimed: Boolean = false,
    val settingsPersistenceClaimed: Boolean = false,
    val uiSurfaceClaimed: Boolean = false,
    val signingBroadcastingClaimed: Boolean = false,
    val publicEndpointClaimed: Boolean = false,
    val mainnetReachabilityClaimed: Boolean = false,
) {
    override fun toString(): String = "RedactedRequest(claims=modeled)"
}

data class SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidence(
    val policyId: String,
    val policyVersion: Int,
    val admissionGateModeled: Boolean,
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
    val priorPromotionBlockersEvidenceIncluded: Boolean,
    val priorSourceGuardCoverageEvidenceIncluded: Boolean,
    val priorRedactionGuardEvidenceIncluded: Boolean,
    val admissionDependenciesModeled: Boolean,
    val admissionChecksModeled: Boolean,
    val forbiddenAdmissionTargetsModeled: Boolean,
    val forbiddenPromotionPathsModeled: Boolean,
    val docsMayDescribeAdmission: Boolean,
    val testsMayAssertBlockedAdmission: Boolean,
    val currentAdmissionGranted: Boolean,
    val currentFutureBranchApproved: Boolean,
    val currentImplementationAdmitted: Boolean,
    val currentProviderSelectionAdmitted: Boolean,
    val currentProductionProviderSelectableAdmitted: Boolean,
    val currentRuntimeLinkageAdmitted: Boolean,
    val currentPromotionAdmitted: Boolean,
    val currentVaultPersistenceAdmitted: Boolean,
    val currentProductionSyncAdmitted: Boolean,
    val currentSigningBroadcastingAdmitted: Boolean,
    val currentUiSurfaceAdmitted: Boolean,
    val currentPublicEndpointAdmitted: Boolean,
    val currentMainnetAdmitted: Boolean,
    val statuses: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionStatus>,
    val outcomes: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionOutcome>,
    val admissionSectionRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionSectionRow>,
    val admissionDependencyRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionDependencyRow>,
    val admissionCheckRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionCheckRow>,
    val admissionBlockerRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlockerRow>,
    val forbiddenAdmissionShortcutRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenAdmissionShortcutRow>,
    val forbiddenAdmissionTargetRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenAdmissionTargetRow>,
    val forbiddenPromotionPathRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateForbiddenPromotionPathRow>,
    val requiredEvidenceRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequiredEvidenceRow>,
    val evidenceSourceRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidenceSourceRow>,
    val futureReviewRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateFutureReviewRow>,
    val blockers: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker>,
    val redactionClasses: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRedactionClass>,
    val capabilities: SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateCapabilities,
)

object SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGatePolicy {
    const val POLICY_ID: String =
        "skald-vault-v1-test-only-provider-identity-implementation-admission-gate-v1"
    const val POLICY_VERSION: Int = 1

    fun currentAdmissionGateEvidence():
        SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidence =
        evaluateAdmissionGate()

    fun evaluateAdmissionGate(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequest =
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequest(),
    ): SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidence {
        val blockers = baseBlockers() + requestBlockers(request)
        return SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidence(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            admissionGateModeled = true,
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
            priorPromotionBlockersEvidenceIncluded = request.includePriorPromotionBlockersEvidence,
            priorSourceGuardCoverageEvidenceIncluded = request.includePriorSourceGuardCoverageEvidence,
            priorRedactionGuardEvidenceIncluded = request.includePriorRedactionGuardEvidence,
            admissionDependenciesModeled = true,
            admissionChecksModeled = true,
            forbiddenAdmissionTargetsModeled = true,
            forbiddenPromotionPathsModeled = true,
            docsMayDescribeAdmission = true,
            testsMayAssertBlockedAdmission = true,
            currentAdmissionGranted = false,
            currentFutureBranchApproved = false,
            currentImplementationAdmitted = false,
            currentProviderSelectionAdmitted = false,
            currentProductionProviderSelectableAdmitted = false,
            currentRuntimeLinkageAdmitted = false,
            currentPromotionAdmitted = false,
            currentVaultPersistenceAdmitted = false,
            currentProductionSyncAdmitted = false,
            currentSigningBroadcastingAdmitted = false,
            currentUiSurfaceAdmitted = false,
            currentPublicEndpointAdmitted = false,
            currentMainnetAdmitted = false,
            statuses = SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionStatus.entries.toSet(),
            outcomes = SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionOutcome.entries.toSet(),
            admissionSectionRows = currentSectionRows(),
            admissionDependencyRows = currentDependencyRows(request),
            admissionCheckRows = currentCheckRows(request),
            admissionBlockerRows = currentBlockerRows(blockers),
            forbiddenAdmissionShortcutRows = currentForbiddenShortcutRows(),
            forbiddenAdmissionTargetRows = currentForbiddenTargetRows(),
            forbiddenPromotionPathRows = currentForbiddenPromotionPathRows(),
            requiredEvidenceRows = currentRequiredEvidenceRows(request),
            evidenceSourceRows = currentEvidenceSourceRows(request),
            futureReviewRows = currentFutureReviewRows(),
            blockers = blockers,
            redactionClasses =
                SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRedactionClass.entries.toSet(),
            capabilities =
                SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateCapabilities.Current,
        )
    }

    private fun currentSectionRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionSectionRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionSection.entries.map { section ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionSectionRow(
                section = section,
                modeled = true,
                currentAdmissionGranted = false,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentDependencyRows(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequest,
    ): List<SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionDependencyRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionDependency.entries.map { dependency ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionDependencyRow(
                dependency = dependency,
                included = dependencyIncluded(dependency, request),
                modeled = true,
                nonAuthorizing = true,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentCheckRows(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequest,
    ): List<SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionCheckRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionCheck.entries.map { check ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionCheckRow(
                check = check,
                modeled = true,
                currentEvidenceSatisfied = checkSatisfied(check, request),
                nonAuthorizing = true,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentBlockerRows(
        blockers: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker>,
    ): List<SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlockerRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.entries.map { blocker ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlockerRow(
                blocker = blocker,
                active = blocker in blockers,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentForbiddenShortcutRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenAdmissionShortcutRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenAdmissionShortcut.entries.map { shortcut ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenAdmissionShortcutRow(
                shortcut = shortcut,
                forbidden = true,
                authorizesAdmission = false,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentForbiddenTargetRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenAdmissionTargetRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenAdmissionTarget.entries.map { target ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenAdmissionTargetRow(
                target = target,
                forbidden = true,
                admittedNow = false,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentForbiddenPromotionPathRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateForbiddenPromotionPathRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateForbiddenPromotionPath.entries
            .map { promotionRoute ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateForbiddenPromotionPathRow(
                    promotionRoute = promotionRoute,
                    forbidden = true,
                    admittedNow = false,
                    authorizesImplementation = false,
                    label = redactedLabel(),
                )
            }

    private fun currentRequiredEvidenceRows(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequest,
    ): List<SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequiredEvidenceRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequiredEvidenceClass.entries
            .map { evidenceClass ->
                val presentAsModelEvidence = requiredEvidencePresent(evidenceClass, request)
                SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequiredEvidenceRow(
                    evidenceClass = evidenceClass,
                    modeled = true,
                    presentAsModelEvidence = presentAsModelEvidence,
                    futureRequired = !presentAsModelEvidence || evidenceClass in futureReviewEvidenceClasses(),
                    authorizesImplementation = false,
                    label = redactedLabel(),
                )
            }

    private fun currentEvidenceSourceRows(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequest,
    ): List<SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidenceSourceRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidenceSource.entries.map { source ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidenceSourceRow(
                evidenceSource = source,
                included = evidenceSourceIncluded(source, request),
                modeled = true,
                nonAuthorizing = true,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentFutureReviewRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateFutureReviewRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateFutureReviewRequirement.entries.map {
                requirement ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateFutureReviewRow(
                requirement = requirement,
                futureRequired = true,
                satisfiedNow = false,
                authorizesCurrentImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun dependencyIncluded(
        dependency: SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionDependency,
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequest,
    ): Boolean =
        when (dependency) {
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionDependency.IdentityDecisionBoundary ->
                request.includePriorIdentityDecisionEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionDependency.IdentityIsolationGuard ->
                request.includePriorIsolationEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionDependency
                .SyntheticIdentityNamespaceContract ->
                request.includePriorNamespaceEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionDependency
                .IdentitySourceSetConfinementBoundary ->
                request.includePriorSourceSetConfinementEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionDependency.ImplementationDecisionGate ->
                request.includePriorImplementationDecisionEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionDependency.ImplementationPrerequisiteAudit ->
                request.includePriorPrerequisiteAuditEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionDependency.ImplementationScopeDecision ->
                request.includePriorScopeDecisionEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionDependency.ImplementationContract ->
                request.includePriorImplementationContractEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionDependency.ImplementationReadinessGate ->
                request.includePriorReadinessGateEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionDependency.ImplementationRuntimeLinkageGuard ->
                request.includePriorRuntimeLinkageGuardEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionDependency.ImplementationPromotionBlockers ->
                request.includePriorPromotionBlockersEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionDependency.ImplementationSourceGuardCoverage ->
                request.includePriorSourceGuardCoverageEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionDependency.ImplementationRedactionGuard ->
                request.includePriorRedactionGuardEvidence
            else -> true
        }

    private fun checkSatisfied(
        check: SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionCheck,
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequest,
    ): Boolean =
        when (check) {
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionCheck.PriorIdentityDecisionEvidenceIncluded ->
                request.includePriorIdentityDecisionEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionCheck.PriorIdentityIsolationEvidenceIncluded ->
                request.includePriorIsolationEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionCheck.PriorSyntheticNamespaceEvidenceIncluded ->
                request.includePriorNamespaceEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionCheck
                .PriorSourceSetConfinementEvidenceIncluded ->
                request.includePriorSourceSetConfinementEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionCheck
                .PriorImplementationDecisionEvidenceIncluded ->
                request.includePriorImplementationDecisionEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionCheck.PriorPrerequisiteAuditEvidenceIncluded ->
                request.includePriorPrerequisiteAuditEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionCheck.PriorScopeDecisionEvidenceIncluded ->
                request.includePriorScopeDecisionEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionCheck.PriorImplementationContractEvidenceIncluded ->
                request.includePriorImplementationContractEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionCheck.PriorReadinessGateEvidenceIncluded ->
                request.includePriorReadinessGateEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionCheck
                .PriorRuntimeLinkageGuardEvidenceIncluded ->
                request.includePriorRuntimeLinkageGuardEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionCheck.PriorPromotionBlockersEvidenceIncluded ->
                request.includePriorPromotionBlockersEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionCheck
                .PriorSourceGuardCoverageEvidenceIncluded ->
                request.includePriorSourceGuardCoverageEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionCheck.PriorRedactionGuardEvidenceIncluded ->
                request.includePriorRedactionGuardEvidence
            else -> true
        }

    private fun requiredEvidencePresent(
        evidenceClass: SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequiredEvidenceClass,
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequest,
    ): Boolean =
        when (evidenceClass) {
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequiredEvidenceClass
                .IdentityDecisionEvidence ->
                request.includePriorIdentityDecisionEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequiredEvidenceClass
                .IdentityIsolationEvidence ->
                request.includePriorIsolationEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequiredEvidenceClass
                .SyntheticNamespaceEvidence ->
                request.includePriorNamespaceEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequiredEvidenceClass
                .SourceSetConfinementEvidence ->
                request.includePriorSourceSetConfinementEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequiredEvidenceClass
                .ImplementationDecisionEvidence ->
                request.includePriorImplementationDecisionEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequiredEvidenceClass
                .PrerequisiteAuditEvidence ->
                request.includePriorPrerequisiteAuditEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequiredEvidenceClass
                .ScopeDecisionEvidence ->
                request.includePriorScopeDecisionEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequiredEvidenceClass
                .ImplementationContractEvidence ->
                request.includePriorImplementationContractEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequiredEvidenceClass
                .ReadinessGateEvidence ->
                request.includePriorReadinessGateEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequiredEvidenceClass
                .RuntimeLinkageGuardEvidence ->
                request.includePriorRuntimeLinkageGuardEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequiredEvidenceClass
                .PromotionBlockersEvidence ->
                request.includePriorPromotionBlockersEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequiredEvidenceClass
                .SourceGuardCoverageEvidence ->
                request.includePriorSourceGuardCoverageEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequiredEvidenceClass
                .RedactionGuardEvidence ->
                request.includePriorRedactionGuardEvidence
            else -> true
        }

    private fun evidenceSourceIncluded(
        source: SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidenceSource,
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequest,
    ): Boolean =
        when (source) {
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidenceSource
                .TestOnlyProviderIdentityDecision ->
                request.includePriorIdentityDecisionEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidenceSource
                .TestOnlyProviderIdentityIsolationGuard ->
                request.includePriorIsolationEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidenceSource
                .SyntheticIdentityNamespace ->
                request.includePriorNamespaceEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidenceSource
                .TestOnlyProviderIdentitySourceSetConfinement ->
                request.includePriorSourceSetConfinementEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidenceSource
                .TestOnlyProviderIdentityImplementationDecision ->
                request.includePriorImplementationDecisionEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidenceSource
                .TestOnlyProviderIdentityImplementationPrerequisiteAudit ->
                request.includePriorPrerequisiteAuditEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidenceSource
                .TestOnlyProviderIdentityImplementationScopeDecision ->
                request.includePriorScopeDecisionEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidenceSource
                .TestOnlyProviderIdentityImplementationContract ->
                request.includePriorImplementationContractEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidenceSource
                .TestOnlyProviderIdentityImplementationReadinessGate ->
                request.includePriorReadinessGateEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidenceSource
                .TestOnlyProviderIdentityImplementationRuntimeLinkageGuard ->
                request.includePriorRuntimeLinkageGuardEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidenceSource
                .TestOnlyProviderIdentityImplementationPromotionBlockers ->
                request.includePriorPromotionBlockersEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidenceSource
                .TestOnlyProviderIdentityImplementationSourceGuardCoverage ->
                request.includePriorSourceGuardCoverageEvidence
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateEvidenceSource
                .TestOnlyProviderIdentityImplementationRedactionGuard ->
                request.includePriorRedactionGuardEvidence
            else -> true
        }

    private fun futureReviewEvidenceClasses():
        Set<SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequiredEvidenceClass> =
        setOf(
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequiredEvidenceClass
                .ProviderSelectionFailClosedEvidence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequiredEvidenceClass
                .RuntimeAbsenceEvidence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequiredEvidenceClass
                .PromotionAbsenceEvidence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequiredEvidenceClass
                .LeakageAbsenceEvidence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequiredEvidenceClass
                .PositiveFlagAbsenceEvidence,
        )

    private fun baseBlockers():
        Set<SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker> =
        setOf(
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.AdmissionGateModelOnly,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.CurrentAdmissionDenied,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.AdmissionEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.PriorEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker
                .SourceGuardCoverageNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.RedactionGuardNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.NoFutureBranchApproval,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker
                .NoTestOnlyProviderIdentityImplementation,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker
                .NoProductionProviderIdentityImplementation,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.NoProviderImplementation,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.NoProviderSelectionAdmission,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.NoProviderFactory,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.NoProviderDispatcher,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.NoNonDisabledRegistryEntry,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.NoExecutorTarget,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.NoProviderKatExecutor,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker
                .ProviderSelectionDisabledProviderOnly,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.ProductionProviderSelectableFalse,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker
                .ProviderOperationAuthorizationBlocked,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.CryptoExecutionBlocked,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.VaultLifecycleDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.PersistenceDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.ProductionSyncDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.MainnetDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.UserConsentCannotOverride,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.WarningOnlyEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.TestOnlyEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.ReleaseEvidenceNonAuthorizing,
        )

    private fun requestBlockers(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionGateRequest,
    ): Set<SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker> =
        buildSet {
            if (request.userConsentOverrideRequested) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.UserConsentCannotOverride)
            }
            if (request.warningOnlyEvidenceClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker
                        .WarningOnlyEvidenceNonAuthorizing,
                )
            }
            if (request.testOnlyEvidenceClaimedAsProductionPromotion) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker
                        .TestOnlyEvidenceNonAuthorizing,
                )
            }
            if (request.releaseEvidenceClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.ReleaseEvidenceNonAuthorizing)
            }
            if (request.admissionClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.AdmissionClaimRejected)
            }
            if (request.admissionClaimedAsImplementationAuthorization) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker
                        .AdmissionAuthorizationClaimRejected,
                )
            }
            if (request.futureBranchApprovalClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker
                        .FutureBranchApprovalClaimRejected,
                )
            }
            if (request.implementationClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.ImplementationClaimRejected)
            }
            if (request.productionIdentityClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.ProductionIdentityClaimRejected)
            }
            if (request.providerSelectionClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.ProviderSelectionClaimRejected)
            }
            if (request.productionProviderSelectableClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker
                        .ProductionProviderSelectableClaimRejected,
                )
            }
            if (request.registryEntryClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.RegistryEntryClaimRejected)
            }
            if (request.factoryReachabilityClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker
                        .FactoryReachabilityClaimRejected,
                )
            }
            if (request.dispatcherReachabilityClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker
                        .DispatcherReachabilityClaimRejected,
                )
            }
            if (request.executorTargetClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.ExecutorTargetClaimRejected)
            }
            if (request.providerKatExecutorClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker
                        .ProviderKatExecutorClaimRejected,
                )
            }
            if (request.providerOperationClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.ProviderOperationClaimRejected)
            }
            if (request.cryptoExecutionClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.CryptoExecutionClaimRejected)
            }
            if (request.vaultPersistenceClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.VaultPersistenceClaimRejected)
            }
            if (request.productionSyncClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.ProductionSyncClaimRejected)
            }
            if (request.settingsPersistenceClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker
                        .SettingsPersistenceClaimRejected,
                )
            }
            if (request.uiSurfaceClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.UiSurfaceClaimRejected)
            }
            if (request.signingBroadcastingClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker
                        .SigningBroadcastingClaimRejected,
                )
            }
            if (request.publicEndpointClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker.PublicEndpointClaimRejected)
            }
            if (request.mainnetReachabilityClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionBlocker
                        .MainnetReachabilityClaimRejected,
                )
            }
        }

    private fun redactedLabel():
        SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionSafeLabel =
        SkaldVaultV1TestOnlyProviderIdentityImplementationAdmissionSafeLabel("redacted")
}
