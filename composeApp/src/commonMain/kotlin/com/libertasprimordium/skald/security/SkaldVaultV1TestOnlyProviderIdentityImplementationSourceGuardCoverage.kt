package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageSafeLabel(
    val value: String,
) {
    override fun toString(): String = "RedactedLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageStatus {
    SourceGuardCoverageModeled,
    StillDisabled,
    CurrentCoverageModeled,
    SourceGuardCoverageRequired,
    RuntimeRootsRemainUnlinked,
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
    PriorReadinessGateEvidenceNonAuthorizing,
    PriorRuntimeLinkageGuardEvidenceNonAuthorizing,
    PriorPromotionBlockersEvidenceNonAuthorizing,
    AllIdentityImplementationModelBoundariesRepresented,
    DesktopSourceGuardCoverageModeled,
    RuntimeRootCoverageModeled,
    ForbiddenImportScanModeled,
    MaterialScanModeled,
    PositiveFlagScanModeled,
    RuntimeHookScanModeled,
    PromotionFlagScanModeled,
    PositiveFlagsAbsent,
    ProviderSelectionDisabledProviderOnly,
    SourceGuardCoverageEvidenceNonAuthorizing,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageOutcome {
    CurrentCoverageModeled,
    SourceGuardCoverageRequired,
    RuntimeRootsRemainUnlinked,
    ImplementationNotAuthorized,
    ProductionPromotionNotAuthorized,
    MainnetNotAuthorized,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationGuardedModelBoundary {
    TestOnlyProviderIdentityDecision,
    TestOnlyProviderIdentityIsolationGuard,
    TestOnlyProviderSyntheticIdentityNamespace,
    TestOnlyProviderIdentitySourceSetConfinement,
    TestOnlyProviderIdentityImplementationDecision,
    TestOnlyProviderIdentityImplementationPrerequisiteAudit,
    TestOnlyProviderIdentityImplementationScopeDecision,
    TestOnlyProviderIdentityImplementationContract,
    TestOnlyProviderIdentityImplementationReadinessGate,
    TestOnlyProviderIdentityImplementationRuntimeLinkageGuard,
    TestOnlyProviderIdentityImplementationPromotionBlockers,
    TestOnlyProviderIdentityImplementationSourceGuardCoverage,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationGuardCoverageCategory {
    ModelOnlyBoundaryCoverage,
    IdentityImplementationChainCoverage,
    DesktopSourceGuardCoverage,
    ProductionRuntimeRootCoverage,
    ProviderSelectionRootCoverage,
    RegistryFactoryDispatcherRootCoverage,
    ExecutorTargetRootCoverage,
    BackendBdkRootCoverage,
    SettingsUiRootCoverage,
    AndroidDesktopRuntimeRootCoverage,
    PositiveFlagScanCoverage,
    MaterialScanCoverage,
    ForbiddenImportScanCoverage,
    RuntimeHookScanCoverage,
    PromotionFlagScanCoverage,
    DocumentationCrossLinkCoverage,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeRoot {
    AndroidMain,
    DesktopMain,
    CommonMainUi,
    CommonMainSettings,
    CommonMainDomain,
    ProviderSelectionSource,
    ProviderRegistrySource,
    ProviderFactorySource,
    ProviderDispatcherSource,
    BackendSource,
    BdkSource,
    ProductionAcceptanceSource,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenPatternClass {
    ProviderIdentityImplementationFixture,
    TestOnlyIdentityImplementationFixture,
    ProductionIdentityImplementationFixture,
    PositiveImplementationFlag,
    PositivePromotionFlag,
    PositiveRuntimeBridgeFlag,
    PositiveProviderSelectionFlag,
    NonDisabledRegistryEntry,
    FactoryReachabilityHook,
    DispatcherReachabilityHook,
    ExecutorTargetHook,
    ProviderKatExecutorHook,
    ProviderOperationHook,
    CryptoExecutionHook,
    VaultPersistenceHook,
    ProductionSyncHook,
    SettingsPersistenceHook,
    UiEntryPointHook,
    BackendClientHook,
    BdkWalletStateHook,
    SigningBroadcastingHook,
    PublicEndpointDefaultHook,
    MainnetHook,
    RawMaterialPayload,
    WalletFixture,
    SecretLookingFixture,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker {
    SourceGuardCoverageModelOnly,
    CurrentCoverageDoesNotAuthorizeImplementation,
    RuntimeRootCoverageDoesNotAuthorizeImplementation,
    SourceGuardPassingDoesNotAuthorizeImplementation,
    NoTestOnlyProviderIdentityImplementation,
    NoProductionProviderIdentityImplementation,
    NoProviderImplementation,
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
    CoverageClaimNonAuthorizing,
    RuntimeRootClaimRejected,
    PositiveFlagClaimRejected,
    PromotionClaimRejected,
    ProviderImplementationClaimRejected,
    ProviderSelectionClaimRejected,
    RegistryEntryClaimRejected,
    FactoryReachabilityClaimRejected,
    DispatcherReachabilityClaimRejected,
    ExecutorTargetClaimRejected,
    ProviderKatExecutorClaimRejected,
    ProviderOperationClaimRejected,
    CryptoExecutionClaimRejected,
    PersistenceReachabilityClaimRejected,
    SettingsReachabilityClaimRejected,
    UiReachabilityClaimRejected,
    MainnetReachabilityClaimRejected,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationCoverageGap {
    MissingModelBoundaryInDesktopSourceGuard,
    MissingForbiddenImportScan,
    MissingMaterialScan,
    MissingPositiveFlagScan,
    MissingRuntimeRootScan,
    MissingPromotionFlagScan,
    MissingRuntimeHookScan,
    MissingDocCrossLink,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageForbiddenPromotionPath {
    SourceGuardCoverageToProviderImplementation,
    SourceGuardCoverageToTestOnlyProviderIdentityImplementation,
    SourceGuardCoverageToProductionProviderIdentityImplementation,
    SourceGuardCoverageToProviderSelection,
    SourceGuardCoverageToProductionProviderSelectable,
    SourceGuardCoverageToRegistryEntry,
    SourceGuardCoverageToFactory,
    SourceGuardCoverageToDispatcher,
    SourceGuardCoverageToExecutorTarget,
    SourceGuardCoverageToProviderKatExecutor,
    SourceGuardCoverageToProviderOperation,
    SourceGuardCoverageToRuntimeRandomness,
    SourceGuardCoverageToKdf,
    SourceGuardCoverageToHkdf,
    SourceGuardCoverageToHmac,
    SourceGuardCoverageToAead,
    SourceGuardCoverageToKeyGeneration,
    SourceGuardCoverageToKeysetStorage,
    SourceGuardCoverageToVaultCreation,
    SourceGuardCoverageToVaultUnlock,
    SourceGuardCoverageToVaultSession,
    SourceGuardCoverageToVaultPersistence,
    SourceGuardCoverageToSecureStorageSuccess,
    SourceGuardCoverageToSecureMetadataSuccess,
    SourceGuardCoverageToManifestReadWrite,
    SourceGuardCoverageToMigration,
    SourceGuardCoverageToProductionSync,
    SourceGuardCoverageToBackendClient,
    SourceGuardCoverageToBdkWalletState,
    SourceGuardCoverageToSettingsCodec,
    SourceGuardCoverageToUiSurface,
    SourceGuardCoverageToSigning,
    SourceGuardCoverageToBroadcasting,
    SourceGuardCoverageToTorTransport,
    SourceGuardCoverageToNostrParsing,
    SourceGuardCoverageToPublicEndpointDefault,
    SourceGuardCoverageToMainnet,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageRequiredEvidenceClass {
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
    DesktopSourceGuardCoverageEvidence,
    RuntimeRootCoverageEvidence,
    ForbiddenImportScanEvidence,
    MaterialScanEvidence,
    PositiveFlagScanEvidence,
    RuntimeHookScanEvidence,
    PromotionFlagScanEvidence,
    DocumentationCrossLinkEvidence,
    ProviderSelectionFailClosedEvidence,
    DisabledProviderEvidence,
    ProductionProviderSelectableFalseEvidence,
    RedactionEvidence,
    AbsenceEvidence,
    NonAuthorizationEvidence,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageEvidenceSource {
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
    DesktopSourceGuardCoverage,
    RuntimeRootScan,
    ForbiddenImportScan,
    MaterialScan,
    PositiveFlagScan,
    RuntimeHookScan,
    PromotionFlagScan,
    DocumentationCrossLink,
    ProviderSelectionBoundary,
    DisabledProviderBoundary,
    RedactionEvidence,
    AbsenceEvidence,
    NonAuthorizationEvidence,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageRedactionClass {
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
    SafePolicyIdOnly,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageFutureReviewRequirement {
    ExplicitFutureBranchApproval,
    SourceGuardCoverageReview,
    RuntimeRootScanReview,
    ForbiddenImportScanReview,
    MaterialScanReview,
    PositiveFlagScanReview,
    RuntimeHookScanReview,
    PromotionFlagScanReview,
    DocumentationCrossLinkReview,
    ProviderSelectionAbsenceReview,
    RegistryFactoryDispatcherAbsenceReview,
    BackendBdkSettingsUiAbsenceReview,
    RedactionReview,
    NonAuthorizationReview,
    MainnetNonAuthorizationReview,
}

data class SkaldVaultV1TestOnlyProviderIdentityImplementationGuardedModelBoundaryRow(
    val boundary: SkaldVaultV1TestOnlyProviderIdentityImplementationGuardedModelBoundary,
    val modeled: Boolean,
    val represented: Boolean,
    val coveredByDesktopSourceGuard: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationGuardCoverageCategoryRow(
    val category: SkaldVaultV1TestOnlyProviderIdentityImplementationGuardCoverageCategory,
    val modeled: Boolean,
    val nonAuthorizing: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeRootRow(
    val root: SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeRoot,
    val coveredByRuntimeRootScan: Boolean,
    val positiveFlagPresent: Boolean,
    val runtimeHookPresent: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenPatternClassRow(
    val patternClass: SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenPatternClass,
    val modeled: Boolean,
    val presentInRuntimeRoots: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationCoverageBlockerRow(
    val blocker: SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker,
    val active: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationCoverageGapRow(
    val gap: SkaldVaultV1TestOnlyProviderIdentityImplementationCoverageGap,
    val modeled: Boolean,
    val currentGapPresent: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageForbiddenPromotionPathRow(
    val promotionRoute: SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageForbiddenPromotionPath,
    val forbidden: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageRequiredEvidenceRow(
    val evidenceClass: SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageRequiredEvidenceClass,
    val modeled: Boolean,
    val presentAsModelEvidence: Boolean,
    val futureRequired: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageEvidenceSourceRow(
    val evidenceSource: SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageEvidenceSource,
    val included: Boolean,
    val modeled: Boolean,
    val nonAuthorizing: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageFutureReviewRow(
    val requirement: SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageFutureReviewRequirement,
    val futureRequired: Boolean,
    val satisfiedNow: Boolean,
    val authorizesCurrentImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageCapabilities(
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
        val Current = SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageCapabilities(
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

data class SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageRequest(
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
    val userConsentOverrideRequested: Boolean = false,
    val warningOnlyEvidenceClaimed: Boolean = false,
    val testOnlyEvidenceClaimedAsProductionPromotion: Boolean = false,
    val releaseEvidenceClaimed: Boolean = false,
    val sourceGuardCoverageClaimed: Boolean = false,
    val sourceGuardCoverageClaimedAsImplementationAuthorization: Boolean = false,
    val runtimeRootCoverageClaimed: Boolean = false,
    val positiveFlagScanClaimed: Boolean = false,
    val materialScanClaimed: Boolean = false,
    val docCrossLinkClaimed: Boolean = false,
    val providerImplementationClaimed: Boolean = false,
    val providerSelectionClaimed: Boolean = false,
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
    val promotionClaimed: Boolean = false,
    val mainnetReachabilityClaimed: Boolean = false,
) {
    override fun toString(): String = "RedactedRequest(claims=modeled)"
}

data class SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageEvidence(
    val policyId: String,
    val policyVersion: Int,
    val sourceGuardCoverageModeled: Boolean,
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
    val allIdentityImplementationModelBoundariesRepresented: Boolean,
    val desktopSourceGuardCoverageModeled: Boolean,
    val runtimeRootCoverageModeled: Boolean,
    val forbiddenImportScanModeled: Boolean,
    val materialScanModeled: Boolean,
    val positiveFlagScanModeled: Boolean,
    val runtimeHookScanModeled: Boolean,
    val promotionFlagScanModeled: Boolean,
    val docsMayDescribeCoverage: Boolean,
    val testsMayAssertBlockedSourceGuardCoverage: Boolean,
    val positiveImplementationFlagPresent: Boolean,
    val positivePromotionFlagPresent: Boolean,
    val positiveRuntimeBridgeFlagPresent: Boolean,
    val positiveProviderSelectionFlagPresent: Boolean,
    val positiveRegistryFactoryDispatcherFlagPresent: Boolean,
    val positiveExecutorTargetFlagPresent: Boolean,
    val positiveProviderKatExecutorFlagPresent: Boolean,
    val positiveProviderOperationFlagPresent: Boolean,
    val positiveCryptoExecutionFlagPresent: Boolean,
    val positiveVaultPersistenceFlagPresent: Boolean,
    val positiveProductionSyncFlagPresent: Boolean,
    val positiveSettingsUiFlagPresent: Boolean,
    val positiveBackendBdkFlagPresent: Boolean,
    val positiveSigningBroadcastingFlagPresent: Boolean,
    val positivePublicEndpointFlagPresent: Boolean,
    val positiveMainnetFlagPresent: Boolean,
    val statuses: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageStatus>,
    val outcomes: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageOutcome>,
    val guardedModelBoundaryRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationGuardedModelBoundaryRow>,
    val guardCoverageCategoryRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationGuardCoverageCategoryRow>,
    val forbiddenRuntimeRootRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeRootRow>,
    val forbiddenPatternClassRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenPatternClassRow>,
    val coverageBlockerRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationCoverageBlockerRow>,
    val coverageGapRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationCoverageGapRow>,
    val forbiddenPromotionPathRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageForbiddenPromotionPathRow>,
    val requiredEvidenceRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageRequiredEvidenceRow>,
    val evidenceSourceRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageEvidenceSourceRow>,
    val futureReviewRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageFutureReviewRow>,
    val blockers: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker>,
    val redactionClasses: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageRedactionClass>,
    val capabilities: SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageCapabilities,
)

object SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoveragePolicy {
    const val POLICY_ID: String =
        "skald-vault-v1-test-only-provider-identity-implementation-source-guard-coverage-v1"
    const val POLICY_VERSION: Int = 1

    fun currentSourceGuardCoverageEvidence():
        SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageEvidence =
        evaluateSourceGuardCoverage()

    fun evaluateSourceGuardCoverage(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageRequest =
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageRequest(),
    ): SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageEvidence {
        val blockers = baseBlockers() + requestBlockers(request)
        return SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageEvidence(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            sourceGuardCoverageModeled = true,
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
            allIdentityImplementationModelBoundariesRepresented = true,
            desktopSourceGuardCoverageModeled = true,
            runtimeRootCoverageModeled = true,
            forbiddenImportScanModeled = true,
            materialScanModeled = true,
            positiveFlagScanModeled = true,
            runtimeHookScanModeled = true,
            promotionFlagScanModeled = true,
            docsMayDescribeCoverage = true,
            testsMayAssertBlockedSourceGuardCoverage = true,
            positiveImplementationFlagPresent = false,
            positivePromotionFlagPresent = false,
            positiveRuntimeBridgeFlagPresent = false,
            positiveProviderSelectionFlagPresent = false,
            positiveRegistryFactoryDispatcherFlagPresent = false,
            positiveExecutorTargetFlagPresent = false,
            positiveProviderKatExecutorFlagPresent = false,
            positiveProviderOperationFlagPresent = false,
            positiveCryptoExecutionFlagPresent = false,
            positiveVaultPersistenceFlagPresent = false,
            positiveProductionSyncFlagPresent = false,
            positiveSettingsUiFlagPresent = false,
            positiveBackendBdkFlagPresent = false,
            positiveSigningBroadcastingFlagPresent = false,
            positivePublicEndpointFlagPresent = false,
            positiveMainnetFlagPresent = false,
            statuses = SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageStatus.entries.toSet(),
            outcomes = SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageOutcome.entries.toSet(),
            guardedModelBoundaryRows = currentGuardedModelBoundaryRows(),
            guardCoverageCategoryRows = currentGuardCoverageCategoryRows(),
            forbiddenRuntimeRootRows = currentForbiddenRuntimeRootRows(),
            forbiddenPatternClassRows = currentForbiddenPatternClassRows(),
            coverageBlockerRows = currentCoverageBlockerRows(blockers),
            coverageGapRows = currentCoverageGapRows(),
            forbiddenPromotionPathRows = currentForbiddenPromotionPathRows(),
            requiredEvidenceRows = currentRequiredEvidenceRows(request),
            evidenceSourceRows = currentEvidenceSourceRows(request),
            futureReviewRows = currentFutureReviewRows(),
            blockers = blockers,
            redactionClasses =
                SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageRedactionClass.entries.toSet(),
            capabilities =
                SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageCapabilities.Current,
        )
    }

    private fun currentGuardedModelBoundaryRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationGuardedModelBoundaryRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationGuardedModelBoundary.entries.map { boundary ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationGuardedModelBoundaryRow(
                boundary = boundary,
                modeled = true,
                represented = true,
                coveredByDesktopSourceGuard = true,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentGuardCoverageCategoryRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationGuardCoverageCategoryRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationGuardCoverageCategory.entries.map { category ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationGuardCoverageCategoryRow(
                category = category,
                modeled = true,
                nonAuthorizing = true,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentForbiddenRuntimeRootRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeRootRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeRoot.entries.map { root ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeRootRow(
                root = root,
                coveredByRuntimeRootScan = true,
                positiveFlagPresent = false,
                runtimeHookPresent = false,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentForbiddenPatternClassRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenPatternClassRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenPatternClass.entries.map { patternClass ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenPatternClassRow(
                patternClass = patternClass,
                modeled = true,
                presentInRuntimeRoots = false,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentCoverageBlockerRows(
        blockers: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker>,
    ): List<SkaldVaultV1TestOnlyProviderIdentityImplementationCoverageBlockerRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker.entries.map { blocker ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationCoverageBlockerRow(
                blocker = blocker,
                active = blocker in blockers,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentCoverageGapRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationCoverageGapRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationCoverageGap.entries.map { gap ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationCoverageGapRow(
                gap = gap,
                modeled = true,
                currentGapPresent = false,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentForbiddenPromotionPathRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageForbiddenPromotionPathRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageForbiddenPromotionPath.entries
            .map { promotionRoute ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageForbiddenPromotionPathRow(
                    promotionRoute = promotionRoute,
                    forbidden = true,
                    authorizesImplementation = false,
                    label = redactedLabel(),
                )
            }

    private fun currentRequiredEvidenceRows(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageRequest,
    ): List<SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageRequiredEvidenceRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageRequiredEvidenceClass.entries
            .map { evidenceClass ->
                val presentAsModelEvidence = when (evidenceClass) {
                    SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageRequiredEvidenceClass
                        .IdentityDecisionEvidence ->
                        request.includePriorIdentityDecisionEvidence
                    SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageRequiredEvidenceClass
                        .IdentityIsolationEvidence ->
                        request.includePriorIsolationEvidence
                    SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageRequiredEvidenceClass
                        .SyntheticNamespaceEvidence ->
                        request.includePriorNamespaceEvidence
                    SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageRequiredEvidenceClass
                        .SourceSetConfinementEvidence ->
                        request.includePriorSourceSetConfinementEvidence
                    SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageRequiredEvidenceClass
                        .ImplementationDecisionEvidence ->
                        request.includePriorImplementationDecisionEvidence
                    SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageRequiredEvidenceClass
                        .PrerequisiteAuditEvidence ->
                        request.includePriorPrerequisiteAuditEvidence
                    SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageRequiredEvidenceClass
                        .ScopeDecisionEvidence ->
                        request.includePriorScopeDecisionEvidence
                    SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageRequiredEvidenceClass
                        .ImplementationContractEvidence ->
                        request.includePriorImplementationContractEvidence
                    SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageRequiredEvidenceClass
                        .ReadinessGateEvidence ->
                        request.includePriorReadinessGateEvidence
                    SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageRequiredEvidenceClass
                        .RuntimeLinkageGuardEvidence ->
                        request.includePriorRuntimeLinkageGuardEvidence
                    SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageRequiredEvidenceClass
                        .PromotionBlockersEvidence ->
                        request.includePriorPromotionBlockersEvidence
                    else -> true
                }
                SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageRequiredEvidenceRow(
                    evidenceClass = evidenceClass,
                    modeled = true,
                    presentAsModelEvidence = presentAsModelEvidence,
                    futureRequired = !presentAsModelEvidence || evidenceClass in futureReviewEvidenceClasses(),
                    authorizesImplementation = false,
                    label = redactedLabel(),
                )
            }

    private fun currentEvidenceSourceRows(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageRequest,
    ): List<SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageEvidenceSourceRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageEvidenceSource.entries.map { source ->
            val included = when (source) {
                SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageEvidenceSource
                    .TestOnlyProviderIdentityDecision ->
                    request.includePriorIdentityDecisionEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageEvidenceSource
                    .TestOnlyProviderIdentityIsolationGuard ->
                    request.includePriorIsolationEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageEvidenceSource
                    .SyntheticIdentityNamespace ->
                    request.includePriorNamespaceEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageEvidenceSource
                    .TestOnlyProviderIdentitySourceSetConfinement ->
                    request.includePriorSourceSetConfinementEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageEvidenceSource
                    .TestOnlyProviderIdentityImplementationDecision ->
                    request.includePriorImplementationDecisionEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageEvidenceSource
                    .TestOnlyProviderIdentityImplementationPrerequisiteAudit ->
                    request.includePriorPrerequisiteAuditEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageEvidenceSource
                    .TestOnlyProviderIdentityImplementationScopeDecision ->
                    request.includePriorScopeDecisionEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageEvidenceSource
                    .TestOnlyProviderIdentityImplementationContract ->
                    request.includePriorImplementationContractEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageEvidenceSource
                    .TestOnlyProviderIdentityImplementationReadinessGate ->
                    request.includePriorReadinessGateEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageEvidenceSource
                    .TestOnlyProviderIdentityImplementationRuntimeLinkageGuard ->
                    request.includePriorRuntimeLinkageGuardEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageEvidenceSource
                    .TestOnlyProviderIdentityImplementationPromotionBlockers ->
                    request.includePriorPromotionBlockersEvidence
                else -> true
            }
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageEvidenceSourceRow(
                evidenceSource = source,
                included = included,
                modeled = true,
                nonAuthorizing = true,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentFutureReviewRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageFutureReviewRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageFutureReviewRequirement.entries.map {
                requirement ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageFutureReviewRow(
                requirement = requirement,
                futureRequired = true,
                satisfiedNow = false,
                authorizesCurrentImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun futureReviewEvidenceClasses():
        Set<SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageRequiredEvidenceClass> =
        setOf(
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageRequiredEvidenceClass
                .RedactionEvidence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageRequiredEvidenceClass
                .DocumentationCrossLinkEvidence,
        )

    private fun baseBlockers():
        Set<SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker> =
        setOf(
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                .SourceGuardCoverageModelOnly,
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                .CurrentCoverageDoesNotAuthorizeImplementation,
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                .RuntimeRootCoverageDoesNotAuthorizeImplementation,
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                .SourceGuardPassingDoesNotAuthorizeImplementation,
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                .NoTestOnlyProviderIdentityImplementation,
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                .NoProductionProviderIdentityImplementation,
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker.NoProviderImplementation,
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker.NoProviderFactory,
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker.NoProviderDispatcher,
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker.NoNonDisabledRegistryEntry,
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker.NoExecutorTarget,
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker.NoProviderKatExecutor,
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                .ProviderSelectionDisabledProviderOnly,
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                .ProductionProviderSelectableFalse,
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                .ProviderOperationAuthorizationBlocked,
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker.CryptoExecutionBlocked,
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker.VaultLifecycleDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker.PersistenceDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker.ProductionSyncDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker.MainnetDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker.UserConsentCannotOverride,
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                .WarningOnlyEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                .TestOnlyEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                .ReleaseEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                .CoverageClaimNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                .RuntimeRootClaimRejected,
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                .PositiveFlagClaimRejected,
            SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker.PromotionClaimRejected,
        )

    private fun requestBlockers(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageRequest,
    ): Set<SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker> =
        buildSet {
            if (request.userConsentOverrideRequested) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                        .UserConsentCannotOverride,
                )
            }
            if (request.warningOnlyEvidenceClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                        .WarningOnlyEvidenceNonAuthorizing,
                )
            }
            if (request.testOnlyEvidenceClaimedAsProductionPromotion) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                        .TestOnlyEvidenceNonAuthorizing,
                )
            }
            if (request.releaseEvidenceClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                        .ReleaseEvidenceNonAuthorizing,
                )
            }
            if (
                request.sourceGuardCoverageClaimed ||
                request.sourceGuardCoverageClaimedAsImplementationAuthorization ||
                request.materialScanClaimed ||
                request.docCrossLinkClaimed
            ) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                        .CoverageClaimNonAuthorizing,
                )
            }
            if (request.runtimeRootCoverageClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                        .RuntimeRootClaimRejected,
                )
            }
            if (request.positiveFlagScanClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                        .PositiveFlagClaimRejected,
                )
            }
            if (request.providerImplementationClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                        .ProviderImplementationClaimRejected,
                )
            }
            if (request.providerSelectionClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                        .ProviderSelectionClaimRejected,
                )
            }
            if (request.registryEntryClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                        .RegistryEntryClaimRejected,
                )
            }
            if (request.factoryReachabilityClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                        .FactoryReachabilityClaimRejected,
                )
            }
            if (request.dispatcherReachabilityClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                        .DispatcherReachabilityClaimRejected,
                )
            }
            if (request.executorTargetClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                        .ExecutorTargetClaimRejected,
                )
            }
            if (request.providerKatExecutorClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                        .ProviderKatExecutorClaimRejected,
                )
            }
            if (request.providerOperationClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                        .ProviderOperationClaimRejected,
                )
            }
            if (request.cryptoExecutionClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                        .CryptoExecutionClaimRejected,
                )
            }
            if (request.persistenceReachabilityClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                        .PersistenceReachabilityClaimRejected,
                )
            }
            if (request.settingsReachabilityClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                        .SettingsReachabilityClaimRejected,
                )
            }
            if (request.uiReachabilityClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                        .UiReachabilityClaimRejected,
                )
            }
            if (request.promotionClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                        .PromotionClaimRejected,
                )
            }
            if (request.mainnetReachabilityClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageBlocker
                        .MainnetReachabilityClaimRejected,
                )
            }
        }

    private fun redactedLabel():
        SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageSafeLabel =
        SkaldVaultV1TestOnlyProviderIdentityImplementationSourceGuardCoverageSafeLabel("redacted")
}
