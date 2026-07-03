package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageSafeLabel(
    val value: String,
) {
    override fun toString(): String = "RedactedLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageStatus {
    RuntimeLinkageGuardModeled,
    StillDisabled,
    CurrentRuntimeLinkageBlocked,
    RuntimeLinkageReviewRequired,
    RuntimeSurfacesDisconnected,
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
    RuntimeSurfacesModeled,
    ForbiddenRuntimeBridgesModeled,
    ForbiddenPromotionPathsModeled,
    AllCurrentRuntimeBridgesAbsent,
    ProviderSelectionDisabledProviderOnly,
    ProductionProviderSelectableFalse,
    RuntimeLinkageEvidenceNonAuthorizing,
    VaultLifecycleBlocked,
    PersistenceBlocked,
    ProductionSyncBlocked,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageOutcome {
    CurrentRuntimeLinkageBlocked,
    RuntimeLinkageReviewRequired,
    RuntimeSurfacesDisconnected,
    ImplementationNotAuthorized,
    ProductionPromotionNotAuthorized,
    MainnetNotAuthorized,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface {
    ProviderSelection,
    ProviderRegistry,
    ProviderFactory,
    ProviderDispatcher,
    ExecutorTarget,
    ProviderKatExecutor,
    ProviderOperationDispatch,
    RuntimeRandomness,
    Kdf,
    Hkdf,
    Hmac,
    Aead,
    KeyGeneration,
    KeysetStorage,
    VaultCreation,
    VaultUnlock,
    VaultSession,
    VaultPersistence,
    SecureStorage,
    SecureMetadataStorage,
    StorageNamespace,
    StoragePath,
    ManifestReadWrite,
    Migration,
    ProductionSync,
    BackendClient,
    BdkWalletState,
    SettingsCodec,
    UiSurface,
    Signing,
    Broadcasting,
    TorTransport,
    NostrParsing,
    PublicEndpointDefault,
    Mainnet,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageClass {
    NoCurrentLinkage,
    ForbiddenProductionRuntimeLinkage,
    ForbiddenProviderSelectionLinkage,
    ForbiddenRegistryFactoryDispatcherLinkage,
    ForbiddenExecutorTargetLinkage,
    ForbiddenProviderKatExecutorLinkage,
    ForbiddenProviderOperationLinkage,
    ForbiddenCryptoExecutionLinkage,
    ForbiddenVaultLifecycleLinkage,
    ForbiddenPersistenceLinkage,
    ForbiddenStorageLinkage,
    ForbiddenBackendClientLinkage,
    ForbiddenBdkWalletStateLinkage,
    ForbiddenSettingsLinkage,
    ForbiddenUiLinkage,
    ForbiddenSigningBroadcastingLinkage,
    ForbiddenTransportLinkage,
    ForbiddenNostrLinkage,
    ForbiddenPublicEndpointLinkage,
    ForbiddenMainnetLinkage,
    EvidenceOnlyNonAuthorizingLinkage,
    FutureReviewOnlyLinkage,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker {
    CurrentRuntimeLinkageBlocked,
    RuntimeLinkageReviewRequired,
    RuntimeSurfacesDisconnected,
    ImplementationNotAuthorized,
    ProductionPromotionNotAuthorized,
    MainnetNotAuthorized,
    PriorEvidenceNonAuthorizing,
    ReadinessEvidenceNonAuthorizing,
    RuntimeLinkageEvidenceNonAuthorizing,
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
    ReadinessClaimRejected,
    RuntimeLinkageClaimRejected,
    RuntimeLinkageAsImplementationAuthorizationRejected,
    ProviderSelectionLinkRejected,
    RegistryLinkRejected,
    FactoryLinkRejected,
    DispatcherLinkRejected,
    ExecutorTargetLinkRejected,
    ProviderKatExecutorLinkRejected,
    ProviderOperationLinkRejected,
    CryptoExecutionLinkRejected,
    VaultLifecycleLinkRejected,
    PersistenceLinkRejected,
    SecureStorageLinkRejected,
    SecureMetadataLinkRejected,
    BackendClientLinkRejected,
    BdkWalletStateLinkRejected,
    SettingsCodecLinkRejected,
    UiSurfaceLinkRejected,
    SigningBroadcastingLinkRejected,
    TorNostrLinkRejected,
    PublicEndpointLinkRejected,
    MainnetLinkRejected,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge {
    IdentityImplementationToProviderSelection,
    IdentityImplementationToProviderRegistry,
    IdentityImplementationToProviderFactory,
    IdentityImplementationToProviderDispatcher,
    IdentityImplementationToExecutorTarget,
    IdentityImplementationToProviderKatExecutor,
    IdentityImplementationToProviderOperationDispatch,
    IdentityImplementationToRuntimeRandomness,
    IdentityImplementationToKdf,
    IdentityImplementationToHkdf,
    IdentityImplementationToHmac,
    IdentityImplementationToAead,
    IdentityImplementationToKeyGeneration,
    IdentityImplementationToKeysetStorage,
    IdentityImplementationToVaultCreation,
    IdentityImplementationToVaultUnlock,
    IdentityImplementationToVaultSession,
    IdentityImplementationToVaultPersistence,
    IdentityImplementationToSecureStorage,
    IdentityImplementationToSecureMetadataStorage,
    IdentityImplementationToStorageNamespace,
    IdentityImplementationToStoragePath,
    IdentityImplementationToManifestReadWrite,
    IdentityImplementationToMigration,
    IdentityImplementationToProductionSync,
    IdentityImplementationToBackendClient,
    IdentityImplementationToBdkWalletState,
    IdentityImplementationToSettingsCodec,
    IdentityImplementationToUiSurface,
    IdentityImplementationToSigning,
    IdentityImplementationToBroadcasting,
    IdentityImplementationToTorTransport,
    IdentityImplementationToNostrParsing,
    IdentityImplementationToPublicEndpointDefault,
    IdentityImplementationToMainnet,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageForbiddenPromotionPath {
    RuntimeLinkageGuardToProviderImplementation,
    RuntimeLinkageGuardToTestOnlyProviderIdentityImplementation,
    RuntimeLinkageGuardToProductionProviderIdentityImplementation,
    RuntimeLinkageGuardToProviderSelection,
    RuntimeLinkageGuardToProductionProviderSelectable,
    RuntimeLinkageGuardToRegistryEntry,
    RuntimeLinkageGuardToFactory,
    RuntimeLinkageGuardToDispatcher,
    RuntimeLinkageGuardToExecutorTarget,
    RuntimeLinkageGuardToProviderKatExecutor,
    RuntimeLinkageGuardToProviderOperation,
    RuntimeLinkageGuardToRuntimeRandomness,
    RuntimeLinkageGuardToKdf,
    RuntimeLinkageGuardToHkdf,
    RuntimeLinkageGuardToHmac,
    RuntimeLinkageGuardToAead,
    RuntimeLinkageGuardToKeyGeneration,
    RuntimeLinkageGuardToKeysetStorage,
    RuntimeLinkageGuardToVaultCreation,
    RuntimeLinkageGuardToVaultUnlock,
    RuntimeLinkageGuardToVaultSession,
    RuntimeLinkageGuardToVaultPersistence,
    RuntimeLinkageGuardToSecureStorageSuccess,
    RuntimeLinkageGuardToSecureMetadataSuccess,
    RuntimeLinkageGuardToManifestReadWrite,
    RuntimeLinkageGuardToMigration,
    RuntimeLinkageGuardToProductionSync,
    RuntimeLinkageGuardToBackendClient,
    RuntimeLinkageGuardToBdkWalletState,
    RuntimeLinkageGuardToSettingsCodec,
    RuntimeLinkageGuardToUiSurface,
    RuntimeLinkageGuardToSigning,
    RuntimeLinkageGuardToBroadcasting,
    RuntimeLinkageGuardToTorTransport,
    RuntimeLinkageGuardToNostrParsing,
    RuntimeLinkageGuardToPublicEndpointDefault,
    RuntimeLinkageGuardToMainnet,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageRequiredEvidenceClass {
    IdentityDecisionEvidence,
    IdentityIsolationEvidence,
    SyntheticNamespaceEvidence,
    SourceSetConfinementEvidence,
    ImplementationDecisionEvidence,
    PrerequisiteAuditEvidence,
    ScopeDecisionEvidence,
    ImplementationContractEvidence,
    ReadinessGateEvidence,
    ProviderSelectionFailClosedEvidence,
    DisabledProviderEvidence,
    RuntimeSurfaceAbsenceEvidence,
    ForbiddenRuntimeBridgeEvidence,
    ForbiddenPromotionPathEvidence,
    SourceGuardCoverageEvidence,
    RedactionLeakageReviewEvidence,
    FutureBranchApprovalEvidence,
    FutureImplementationReviewEvidence,
    ProductionAbsenceEvidence,
    MainnetAbsenceEvidence,
    NonAuthorizationEvidence,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageEvidenceSource {
    TestOnlyProviderIdentityDecision,
    TestOnlyProviderIdentityIsolationGuard,
    SyntheticIdentityNamespace,
    TestOnlyProviderIdentitySourceSetConfinement,
    TestOnlyProviderIdentityImplementationDecision,
    TestOnlyProviderIdentityImplementationPrerequisiteAudit,
    TestOnlyProviderIdentityImplementationScopeDecision,
    TestOnlyProviderIdentityImplementationContract,
    TestOnlyProviderIdentityImplementationReadinessGate,
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

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageRedactionClass {
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
    SafePolicyIdOnly,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageFutureReviewRequirement {
    ExplicitFutureBranchApproval,
    RuntimeLinkageReviewRequest,
    RuntimeSurfaceAbsenceReview,
    ProviderSelectionAbsenceReview,
    RegistryFactoryDispatcherAbsenceReview,
    ExecutorTargetAndKatExecutorAbsenceReview,
    ProviderOperationAbsenceReview,
    CryptoExecutionAbsenceReview,
    VaultLifecycleAndPersistenceAbsenceReview,
    SecureStorageAndMetadataAbsenceReview,
    BackendBdkSettingsUiAbsenceReview,
    SigningBroadcastingTorNostrEndpointAbsenceReview,
    SourceGuardCoverageReview,
    RedactionAndLeakageReview,
    NonAuthorizationReview,
    FutureImplementationReview,
    MainnetNonAuthorizationReview,
}

data class SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurfaceRow(
    val surface: SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface,
    val modeled: Boolean,
    val disconnectedNow: Boolean,
    val currentBridgePresent: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageClassRow(
    val linkageClass: SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageClass,
    val modeled: Boolean,
    val currentBridgePresent: Boolean,
    val authorizesImplementation: Boolean,
    val nonAuthorizing: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridgeRow(
    val bridge: SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge,
    val surface: SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface,
    val forbidden: Boolean,
    val currentBridgePresent: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageForbiddenPromotionPathRow(
    val promotionRoute: SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageForbiddenPromotionPath,
    val forbidden: Boolean,
    val currentBridgePresent: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageRequiredEvidenceRow(
    val evidenceClass: SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageRequiredEvidenceClass,
    val modeled: Boolean,
    val presentAsModelEvidence: Boolean,
    val futureRequired: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageEvidenceSourceRow(
    val evidenceSource: SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageEvidenceSource,
    val included: Boolean,
    val modeled: Boolean,
    val nonAuthorizing: Boolean,
    val authorizesImplementation: Boolean,
    val authorizesProductionPromotion: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageFutureReviewRow(
    val requirement: SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageFutureReviewRequirement,
    val futureRequired: Boolean,
    val satisfiedNow: Boolean,
    val authorizesCurrentImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageGuardCapabilities(
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
        val Current = SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageGuardCapabilities(
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

data class SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageGuardRequest(
    val includePriorIdentityDecisionEvidence: Boolean = true,
    val includePriorIsolationEvidence: Boolean = true,
    val includePriorNamespaceEvidence: Boolean = true,
    val includePriorSourceSetConfinementEvidence: Boolean = true,
    val includePriorImplementationDecisionEvidence: Boolean = true,
    val includePriorPrerequisiteAuditEvidence: Boolean = true,
    val includePriorScopeDecisionEvidence: Boolean = true,
    val includePriorImplementationContractEvidence: Boolean = true,
    val includePriorReadinessGateEvidence: Boolean = true,
    val userConsentOverrideRequested: Boolean = false,
    val warningOnlyEvidenceClaimed: Boolean = false,
    val testOnlyEvidenceClaimedAsProductionPromotion: Boolean = false,
    val releaseEvidenceClaimed: Boolean = false,
    val futureBranchApprovalClaimed: Boolean = false,
    val readinessClaimed: Boolean = false,
    val runtimeLinkageClaimed: Boolean = false,
    val runtimeLinkageClaimedAsImplementationAuthorization: Boolean = false,
    val providerSelectionLinkClaimed: Boolean = false,
    val registryLinkClaimed: Boolean = false,
    val factoryLinkClaimed: Boolean = false,
    val dispatcherLinkClaimed: Boolean = false,
    val executorTargetLinkClaimed: Boolean = false,
    val providerKatExecutorLinkClaimed: Boolean = false,
    val providerOperationLinkClaimed: Boolean = false,
    val cryptoExecutionLinkClaimed: Boolean = false,
    val vaultLifecycleLinkClaimed: Boolean = false,
    val persistenceLinkClaimed: Boolean = false,
    val secureStorageLinkClaimed: Boolean = false,
    val secureMetadataLinkClaimed: Boolean = false,
    val backendClientLinkClaimed: Boolean = false,
    val bdkWalletStateLinkClaimed: Boolean = false,
    val settingsCodecLinkClaimed: Boolean = false,
    val uiSurfaceLinkClaimed: Boolean = false,
    val signingBroadcastingLinkClaimed: Boolean = false,
    val torNostrLinkClaimed: Boolean = false,
    val publicEndpointLinkClaimed: Boolean = false,
    val mainnetLinkClaimed: Boolean = false,
) {
    override fun toString(): String = "RedactedRequest(claims=modeled)"
}

data class SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageGuardEvidence(
    val policyId: String,
    val policyVersion: Int,
    val runtimeLinkageGuardModeled: Boolean,
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
    val runtimeSurfacesModeled: Boolean,
    val forbiddenRuntimeBridgesModeled: Boolean,
    val forbiddenPromotionPathsModeled: Boolean,
    val docsMayDescribeFutureReview: Boolean,
    val testsMayAssertBlockedRuntimeLinkage: Boolean,
    val currentProviderSelectionBridgePresent: Boolean,
    val currentRegistryBridgePresent: Boolean,
    val currentFactoryBridgePresent: Boolean,
    val currentDispatcherBridgePresent: Boolean,
    val currentExecutorTargetBridgePresent: Boolean,
    val currentProviderKatExecutorBridgePresent: Boolean,
    val currentProviderOperationBridgePresent: Boolean,
    val currentCryptoExecutionBridgePresent: Boolean,
    val currentVaultLifecycleBridgePresent: Boolean,
    val currentPersistenceBridgePresent: Boolean,
    val currentSecureStorageBridgePresent: Boolean,
    val currentSecureMetadataBridgePresent: Boolean,
    val currentBackendClientBridgePresent: Boolean,
    val currentBdkWalletStateBridgePresent: Boolean,
    val currentSettingsCodecBridgePresent: Boolean,
    val currentUiSurfaceBridgePresent: Boolean,
    val currentSigningBroadcastingBridgePresent: Boolean,
    val currentTorNostrBridgePresent: Boolean,
    val currentPublicEndpointBridgePresent: Boolean,
    val currentMainnetBridgePresent: Boolean,
    val statuses: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageStatus>,
    val outcomes: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageOutcome>,
    val runtimeSurfaceRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurfaceRow>,
    val runtimeLinkageClassRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageClassRow>,
    val forbiddenRuntimeBridgeRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridgeRow>,
    val forbiddenPromotionPathRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageForbiddenPromotionPathRow>,
    val requiredEvidenceRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageRequiredEvidenceRow>,
    val evidenceSourceRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageEvidenceSourceRow>,
    val futureReviewRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageFutureReviewRow>,
    val blockers: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker>,
    val redactionClasses: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageRedactionClass>,
    val capabilities: SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageGuardCapabilities,
)

object SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageGuardPolicy {
    const val POLICY_ID: String =
        "skald-vault-v1-test-only-provider-identity-implementation-runtime-linkage-guard-v1"
    const val POLICY_VERSION: Int = 1

    fun currentRuntimeLinkageGuardEvidence():
        SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageGuardEvidence =
        evaluateRuntimeLinkageGuard()

    fun evaluateRuntimeLinkageGuard(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageGuardRequest =
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageGuardRequest(),
    ): SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageGuardEvidence =
        SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageGuardEvidence(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            runtimeLinkageGuardModeled = true,
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
            runtimeSurfacesModeled = true,
            forbiddenRuntimeBridgesModeled = true,
            forbiddenPromotionPathsModeled = true,
            docsMayDescribeFutureReview = true,
            testsMayAssertBlockedRuntimeLinkage = true,
            currentProviderSelectionBridgePresent = false,
            currentRegistryBridgePresent = false,
            currentFactoryBridgePresent = false,
            currentDispatcherBridgePresent = false,
            currentExecutorTargetBridgePresent = false,
            currentProviderKatExecutorBridgePresent = false,
            currentProviderOperationBridgePresent = false,
            currentCryptoExecutionBridgePresent = false,
            currentVaultLifecycleBridgePresent = false,
            currentPersistenceBridgePresent = false,
            currentSecureStorageBridgePresent = false,
            currentSecureMetadataBridgePresent = false,
            currentBackendClientBridgePresent = false,
            currentBdkWalletStateBridgePresent = false,
            currentSettingsCodecBridgePresent = false,
            currentUiSurfaceBridgePresent = false,
            currentSigningBroadcastingBridgePresent = false,
            currentTorNostrBridgePresent = false,
            currentPublicEndpointBridgePresent = false,
            currentMainnetBridgePresent = false,
            statuses = SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageStatus.entries.toSet(),
            outcomes = SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageOutcome.entries.toSet(),
            runtimeSurfaceRows = currentRuntimeSurfaceRows(),
            runtimeLinkageClassRows = currentRuntimeLinkageClassRows(),
            forbiddenRuntimeBridgeRows = currentForbiddenRuntimeBridgeRows(),
            forbiddenPromotionPathRows = currentForbiddenPromotionPathRows(),
            requiredEvidenceRows = currentRequiredEvidenceRows(request),
            evidenceSourceRows = currentEvidenceSourceRows(request),
            futureReviewRows = currentFutureReviewRows(),
            blockers = baseBlockers() + requestBlockers(request),
            redactionClasses =
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageRedactionClass.entries.toSet(),
            capabilities = SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageGuardCapabilities.Current,
        )

    private fun currentRuntimeSurfaceRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurfaceRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface.entries.map { surface ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurfaceRow(
                surface = surface,
                modeled = true,
                disconnectedNow = true,
                currentBridgePresent = false,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentRuntimeLinkageClassRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageClassRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageClass.entries.map { linkageClass ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageClassRow(
                linkageClass = linkageClass,
                modeled = true,
                currentBridgePresent = false,
                authorizesImplementation = false,
                nonAuthorizing = true,
                label = redactedLabel(),
            )
        }

    private fun currentForbiddenRuntimeBridgeRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridgeRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge.entries.map { bridge ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridgeRow(
                bridge = bridge,
                surface = surfaceFor(bridge),
                forbidden = true,
                currentBridgePresent = false,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentForbiddenPromotionPathRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageForbiddenPromotionPathRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageForbiddenPromotionPath.entries.map { route ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageForbiddenPromotionPathRow(
                promotionRoute = route,
                forbidden = true,
                currentBridgePresent = false,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentRequiredEvidenceRows(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageGuardRequest,
    ): List<SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageRequiredEvidenceRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageRequiredEvidenceClass.entries.map {
                evidenceClass ->
            val presentAsModelEvidence = when (evidenceClass) {
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageRequiredEvidenceClass
                    .IdentityDecisionEvidence ->
                    request.includePriorIdentityDecisionEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageRequiredEvidenceClass
                    .IdentityIsolationEvidence ->
                    request.includePriorIsolationEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageRequiredEvidenceClass
                    .SyntheticNamespaceEvidence ->
                    request.includePriorNamespaceEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageRequiredEvidenceClass
                    .SourceSetConfinementEvidence ->
                    request.includePriorSourceSetConfinementEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageRequiredEvidenceClass
                    .ImplementationDecisionEvidence ->
                    request.includePriorImplementationDecisionEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageRequiredEvidenceClass
                    .PrerequisiteAuditEvidence ->
                    request.includePriorPrerequisiteAuditEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageRequiredEvidenceClass
                    .ScopeDecisionEvidence ->
                    request.includePriorScopeDecisionEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageRequiredEvidenceClass
                    .ImplementationContractEvidence ->
                    request.includePriorImplementationContractEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageRequiredEvidenceClass
                    .ReadinessGateEvidence ->
                    request.includePriorReadinessGateEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageRequiredEvidenceClass
                    .ProviderSelectionFailClosedEvidence,
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageRequiredEvidenceClass
                    .DisabledProviderEvidence,
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageRequiredEvidenceClass
                    .RuntimeSurfaceAbsenceEvidence,
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageRequiredEvidenceClass
                    .ForbiddenRuntimeBridgeEvidence,
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageRequiredEvidenceClass
                    .ForbiddenPromotionPathEvidence,
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageRequiredEvidenceClass
                    .SourceGuardCoverageEvidence,
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageRequiredEvidenceClass
                    .NonAuthorizationEvidence ->
                    true
                else -> false
            }
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageRequiredEvidenceRow(
                evidenceClass = evidenceClass,
                modeled = true,
                presentAsModelEvidence = presentAsModelEvidence,
                futureRequired = !presentAsModelEvidence || evidenceClass in futureReviewEvidenceClasses(),
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentEvidenceSourceRows(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageGuardRequest,
    ): List<SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageEvidenceSourceRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageEvidenceSource.entries.map { source ->
            val included = when (source) {
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageEvidenceSource
                    .TestOnlyProviderIdentityDecision ->
                    request.includePriorIdentityDecisionEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageEvidenceSource
                    .TestOnlyProviderIdentityIsolationGuard ->
                    request.includePriorIsolationEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageEvidenceSource
                    .SyntheticIdentityNamespace ->
                    request.includePriorNamespaceEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageEvidenceSource
                    .TestOnlyProviderIdentitySourceSetConfinement ->
                    request.includePriorSourceSetConfinementEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageEvidenceSource
                    .TestOnlyProviderIdentityImplementationDecision ->
                    request.includePriorImplementationDecisionEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageEvidenceSource
                    .TestOnlyProviderIdentityImplementationPrerequisiteAudit ->
                    request.includePriorPrerequisiteAuditEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageEvidenceSource
                    .TestOnlyProviderIdentityImplementationScopeDecision ->
                    request.includePriorScopeDecisionEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageEvidenceSource
                    .TestOnlyProviderIdentityImplementationContract ->
                    request.includePriorImplementationContractEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageEvidenceSource
                    .TestOnlyProviderIdentityImplementationReadinessGate ->
                    request.includePriorReadinessGateEvidence
                else -> true
            }
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageEvidenceSourceRow(
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
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageFutureReviewRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageFutureReviewRequirement.entries.map {
                requirement ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageFutureReviewRow(
                requirement = requirement,
                futureRequired = true,
                satisfiedNow = false,
                authorizesCurrentImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun futureReviewEvidenceClasses():
        Set<SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageRequiredEvidenceClass> =
        setOf(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageRequiredEvidenceClass
                .RedactionLeakageReviewEvidence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageRequiredEvidenceClass
                .FutureBranchApprovalEvidence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageRequiredEvidenceClass
                .FutureImplementationReviewEvidence,
        )

    private fun surfaceFor(
        bridge: SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge,
    ): SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface =
        when (bridge) {
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge
                .IdentityImplementationToProviderSelection ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface.ProviderSelection
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge
                .IdentityImplementationToProviderRegistry ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface.ProviderRegistry
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge
                .IdentityImplementationToProviderFactory ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface.ProviderFactory
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge
                .IdentityImplementationToProviderDispatcher ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface.ProviderDispatcher
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge
                .IdentityImplementationToExecutorTarget ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface.ExecutorTarget
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge
                .IdentityImplementationToProviderKatExecutor ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface.ProviderKatExecutor
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge
                .IdentityImplementationToProviderOperationDispatch ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface.ProviderOperationDispatch
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge
                .IdentityImplementationToRuntimeRandomness ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface.RuntimeRandomness
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge.IdentityImplementationToKdf ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface.Kdf
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge.IdentityImplementationToHkdf ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface.Hkdf
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge.IdentityImplementationToHmac ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface.Hmac
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge.IdentityImplementationToAead ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface.Aead
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge
                .IdentityImplementationToKeyGeneration ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface.KeyGeneration
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge
                .IdentityImplementationToKeysetStorage ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface.KeysetStorage
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge
                .IdentityImplementationToVaultCreation ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface.VaultCreation
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge
                .IdentityImplementationToVaultUnlock ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface.VaultUnlock
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge
                .IdentityImplementationToVaultSession ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface.VaultSession
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge
                .IdentityImplementationToVaultPersistence ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface.VaultPersistence
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge
                .IdentityImplementationToSecureStorage ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface.SecureStorage
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge
                .IdentityImplementationToSecureMetadataStorage ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface.SecureMetadataStorage
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge
                .IdentityImplementationToStorageNamespace ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface.StorageNamespace
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge
                .IdentityImplementationToStoragePath ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface.StoragePath
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge
                .IdentityImplementationToManifestReadWrite ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface.ManifestReadWrite
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge.IdentityImplementationToMigration ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface.Migration
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge
                .IdentityImplementationToProductionSync ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface.ProductionSync
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge
                .IdentityImplementationToBackendClient ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface.BackendClient
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge
                .IdentityImplementationToBdkWalletState ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface.BdkWalletState
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge
                .IdentityImplementationToSettingsCodec ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface.SettingsCodec
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge.IdentityImplementationToUiSurface ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface.UiSurface
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge.IdentityImplementationToSigning ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface.Signing
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge
                .IdentityImplementationToBroadcasting ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface.Broadcasting
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge
                .IdentityImplementationToTorTransport ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface.TorTransport
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge
                .IdentityImplementationToNostrParsing ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface.NostrParsing
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge
                .IdentityImplementationToPublicEndpointDefault ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface.PublicEndpointDefault
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeBridge.IdentityImplementationToMainnet ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeSurface.Mainnet
        }

    private fun baseBlockers(): Set<SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker> =
        setOf(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.CurrentRuntimeLinkageBlocked,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.RuntimeLinkageReviewRequired,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.RuntimeSurfacesDisconnected,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.ImplementationNotAuthorized,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.ProductionPromotionNotAuthorized,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.MainnetNotAuthorized,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.PriorEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.ReadinessEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker
                .RuntimeLinkageEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker
                .NoTestOnlyProviderIdentityImplementation,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker
                .NoProductionProviderIdentityImplementation,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.NoProviderImplementation,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.NoProviderFactory,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.NoProviderDispatcher,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.NoNonDisabledRegistryEntry,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.NoExecutorTarget,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.NoProviderKatExecutor,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.NoRunnableExecutorInterface,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker
                .ProviderSelectionDisabledProviderOnly,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.ProductionProviderSelectableFalse,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker
                .ProviderOperationAuthorizationBlocked,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.CryptoExecutionBlocked,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.VaultLifecycleDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.PersistenceDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.SecureStorageDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.SecureMetadataDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.ProductionSyncDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.BackendClientDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.BdkWalletStateDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.SettingsCodecDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.UiSurfaceDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.SigningBroadcastingDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.TorNostrDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.PublicEndpointDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.MainnetDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.UserConsentCannotOverride,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker
                .WarningOnlyEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.TestOnlyEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.ReleaseEvidenceNonAuthorizing,
        )

    private fun requestBlockers(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageGuardRequest,
    ): Set<SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker> =
        buildSet {
            if (request.userConsentOverrideRequested) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.UserConsentCannotOverride)
            }
            if (request.warningOnlyEvidenceClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker
                        .WarningOnlyEvidenceNonAuthorizing,
                )
            }
            if (request.testOnlyEvidenceClaimedAsProductionPromotion) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker
                        .TestOnlyEvidenceNonAuthorizing,
                )
            }
            if (request.releaseEvidenceClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker
                        .ReleaseEvidenceNonAuthorizing,
                )
            }
            if (request.futureBranchApprovalClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker
                        .FutureBranchApprovalClaimRejected,
                )
            }
            if (request.readinessClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.ReadinessClaimRejected)
            }
            if (request.runtimeLinkageClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker
                        .RuntimeLinkageClaimRejected,
                )
            }
            if (request.runtimeLinkageClaimedAsImplementationAuthorization) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker
                        .RuntimeLinkageAsImplementationAuthorizationRejected,
                )
            }
            if (request.providerSelectionLinkClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker
                        .ProviderSelectionLinkRejected,
                )
            }
            if (request.registryLinkClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.RegistryLinkRejected)
            }
            if (request.factoryLinkClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.FactoryLinkRejected)
            }
            if (request.dispatcherLinkClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.DispatcherLinkRejected)
            }
            if (request.executorTargetLinkClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker
                        .ExecutorTargetLinkRejected,
                )
            }
            if (request.providerKatExecutorLinkClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker
                        .ProviderKatExecutorLinkRejected,
                )
            }
            if (request.providerOperationLinkClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker
                        .ProviderOperationLinkRejected,
                )
            }
            if (request.cryptoExecutionLinkClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker
                        .CryptoExecutionLinkRejected,
                )
            }
            if (request.vaultLifecycleLinkClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker
                        .VaultLifecycleLinkRejected,
                )
            }
            if (request.persistenceLinkClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.PersistenceLinkRejected)
            }
            if (request.secureStorageLinkClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.SecureStorageLinkRejected)
            }
            if (request.secureMetadataLinkClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker
                        .SecureMetadataLinkRejected,
                )
            }
            if (request.backendClientLinkClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.BackendClientLinkRejected)
            }
            if (request.bdkWalletStateLinkClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.BdkWalletStateLinkRejected)
            }
            if (request.settingsCodecLinkClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.SettingsCodecLinkRejected)
            }
            if (request.uiSurfaceLinkClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.UiSurfaceLinkRejected)
            }
            if (request.signingBroadcastingLinkClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker
                        .SigningBroadcastingLinkRejected,
                )
            }
            if (request.torNostrLinkClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.TorNostrLinkRejected)
            }
            if (request.publicEndpointLinkClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.PublicEndpointLinkRejected)
            }
            if (request.mainnetLinkClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageBlocker.MainnetLinkRejected)
            }
        }

    private fun redactedLabel(): SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageSafeLabel =
        SkaldVaultV1TestOnlyProviderIdentityImplementationRuntimeLinkageSafeLabel("redacted")
}
