package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityImplementationContractSafeLabel(
    val value: String,
) {
    override fun toString(): String = "RedactedLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationContractStatus {
    ImplementationContractModeled,
    StillDisabled,
    CurrentContractBlocked,
    ContractReviewRequired,
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
    ContractRequirementsModeled,
    ContractRequirementsNonAuthorizing,
    ForbiddenImplementationClausesModeled,
    ForbiddenRuntimeLinkagesModeled,
    ProviderSelectionDisabledProviderOnly,
    ProductionProviderSelectableFalse,
    VaultLifecycleBlocked,
    PersistenceBlocked,
    ProductionSyncBlocked,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationContractOutcome {
    CurrentContractBlocked,
    ContractReviewRequired,
    ImplementationNotAuthorized,
    ProductionPromotionNotAuthorized,
    MainnetNotAuthorized,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection {
    ModelOnlyContract,
    FutureReviewOnlyContract,
    IdentityLabelContract,
    SourceSetPlacementContract,
    ConstructionAbsenceContract,
    RuntimeReachabilityAbsenceContract,
    ProviderSelectionAbsenceContract,
    RegistryAbsenceContract,
    FactoryAbsenceContract,
    DispatcherAbsenceContract,
    ExecutorTargetAbsenceContract,
    ProviderKatExecutorAbsenceContract,
    ProviderOperationAbsenceContract,
    CryptoExecutionAbsenceContract,
    VaultLifecycleAbsenceContract,
    PersistenceAbsenceContract,
    SecureStorageAbsenceContract,
    SecureMetadataAbsenceContract,
    BackendClientAbsenceContract,
    BdkWalletStateAbsenceContract,
    SettingsCodecAbsenceContract,
    UiSurfaceAbsenceContract,
    SigningBroadcastingAbsenceContract,
    TorNostrAbsenceContract,
    PublicEndpointAbsenceContract,
    MainnetAbsenceContract,
    RedactionContract,
    SourceGuardContract,
    FutureImplementationReviewContract,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement {
    MustBeModelOnlyInCurrentBranch,
    MustBeStillDisabled,
    MustRequireExplicitFutureBranchApproval,
    MustUseSyntheticSafeIdOnly,
    MustUseTestOnlyNamespaceOnly,
    MustRequireNonProductionSourceSetPlacement,
    MustRemainAbsentFromCommonMainRuntimeConstruction,
    MustRemainAbsentFromAndroidMainRuntimeConstruction,
    MustRemainAbsentFromDesktopMainRuntimeConstruction,
    MustRemainAbsentFromProviderSelection,
    MustRemainAbsentFromProviderRegistry,
    MustRemainAbsentFromProviderFactory,
    MustRemainAbsentFromProviderDispatcher,
    MustRemainAbsentFromExecutorTargeting,
    MustRemainAbsentFromProviderKatExecutor,
    MustRemainAbsentFromProviderOperations,
    MustRemainAbsentFromRandomness,
    MustRemainAbsentFromKdf,
    MustRemainAbsentFromHkdf,
    MustRemainAbsentFromHmac,
    MustRemainAbsentFromAead,
    MustRemainAbsentFromKeyGeneration,
    MustRemainAbsentFromKeysetStorage,
    MustRemainAbsentFromVaultCreation,
    MustRemainAbsentFromVaultUnlock,
    MustRemainAbsentFromVaultSession,
    MustRemainAbsentFromVaultPersistence,
    MustRemainAbsentFromSecureStorageSuccess,
    MustRemainAbsentFromSecureMetadataSuccess,
    MustRemainAbsentFromStorageNamespaceWrites,
    MustRemainAbsentFromStoragePathWrites,
    MustRemainAbsentFromManifestReadWrite,
    MustRemainAbsentFromMigration,
    MustRemainAbsentFromProductionSync,
    MustRemainAbsentFromBackendClients,
    MustRemainAbsentFromBdkWalletState,
    MustRemainAbsentFromSettingsCodecs,
    MustRemainAbsentFromUiSurface,
    MustRemainAbsentFromSigning,
    MustRemainAbsentFromBroadcasting,
    MustRemainAbsentFromTorTransport,
    MustRemainAbsentFromNostrParsing,
    MustRemainAbsentFromPublicEndpointDefaults,
    MustRemainAbsentFromMainnet,
    MustExposeOnlyRedactedSafeLabels,
    MustNotAcceptRawMaterial,
    MustNotHoldProviderHandles,
    MustNotHoldCryptoObjects,
    MustNotHoldStorageReferences,
    MustNotHoldBackendReferences,
    MustNotHoldWalletReferences,
    MustNotHoldEndpointReferences,
    MustNotHoldSourceLocationPayloads,
    MustNotHoldFutureApprovalPayloads,
    MustNotHoldImplementationPayloads,
    MustNotHoldScopePayloads,
    MustTreatPriorEvidenceAsNonAuthorizing,
    MustTreatAllowedFutureScopeAsNonAuthorizing,
    MustTreatAbsenceEvidenceAsNonAuthorizing,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker {
    CurrentContractBlocked,
    ContractReviewRequired,
    ImplementationNotAuthorized,
    ProductionPromotionNotAuthorized,
    MainnetNotAuthorized,
    ContractRequirementsNonAuthorizing,
    PriorIdentityDecisionEvidenceNonAuthorizing,
    PriorIsolationEvidenceNonAuthorizing,
    PriorNamespaceEvidenceNonAuthorizing,
    PriorSourceSetConfinementEvidenceNonAuthorizing,
    PriorImplementationDecisionEvidenceNonAuthorizing,
    PriorPrerequisiteAuditEvidenceNonAuthorizing,
    PriorScopeDecisionEvidenceNonAuthorizing,
    PrerequisiteCompletionMissing,
    ScopeApprovalMissing,
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
    ContractAsImplementationAuthorizationRejected,
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

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenClause {
    CurrentBranchImplementsProviderIdentity,
    CurrentBranchImplementsProductionIdentity,
    CurrentBranchImplementsVaultCryptoProvider,
    CurrentBranchCreatesProviderFactory,
    CurrentBranchCreatesProviderDispatcher,
    CurrentBranchCreatesProviderRegistryEntry,
    CurrentBranchCreatesExecutorTarget,
    CurrentBranchCreatesProviderKatExecutor,
    CurrentBranchCreatesRunnableExecutorInterface,
    CurrentBranchExecutesProviderOperations,
    CurrentBranchExecutesCrypto,
    CurrentBranchExecutesVaultLifecycle,
    CurrentBranchPersistsVault,
    CurrentBranchWritesSettings,
    CurrentBranchExposesUi,
    CurrentBranchCreatesBackendClient,
    CurrentBranchCreatesBdkWalletState,
    CurrentBranchSignsOrBroadcasts,
    CurrentBranchEnablesNetworkTransport,
    CurrentBranchParsesNostrSecrets,
    CurrentBranchAddsPublicEndpoint,
    CurrentBranchEnablesMainnet,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage {
    ContractToProviderSelectionRuntime,
    ContractToProviderRegistryRuntime,
    ContractToProviderFactoryRuntime,
    ContractToProviderDispatcherRuntime,
    ContractToExecutorTargetRuntime,
    ContractToProviderKatExecutorRuntime,
    ContractToProviderOperationRuntime,
    ContractToRandomnessRuntime,
    ContractToKdfRuntime,
    ContractToHkdfRuntime,
    ContractToHmacRuntime,
    ContractToAeadRuntime,
    ContractToKeyGenerationRuntime,
    ContractToKeysetStorageRuntime,
    ContractToVaultCreationRuntime,
    ContractToVaultUnlockRuntime,
    ContractToVaultSessionRuntime,
    ContractToVaultPersistenceRuntime,
    ContractToSecureStorageRuntime,
    ContractToSecureMetadataRuntime,
    ContractToStorageNamespaceRuntime,
    ContractToStoragePathRuntime,
    ContractToManifestRuntime,
    ContractToMigrationRuntime,
    ContractToProductionSyncRuntime,
    ContractToBackendClientRuntime,
    ContractToBdkWalletStateRuntime,
    ContractToSettingsCodecRuntime,
    ContractToUiRuntime,
    ContractToSigningRuntime,
    ContractToBroadcastingRuntime,
    ContractToTorRuntime,
    ContractToNostrRuntime,
    ContractToPublicEndpointRuntime,
    ContractToMainnetRuntime,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationContractForbiddenPromotionPath {
    ContractToProviderImplementation,
    ContractToTestOnlyProviderIdentityImplementation,
    ContractToProductionProviderIdentityImplementation,
    ContractToProviderSelection,
    ContractToProductionProviderSelectable,
    ContractToRegistryEntry,
    ContractToFactory,
    ContractToDispatcher,
    ContractToExecutorTarget,
    ContractToProviderKatExecutor,
    ContractToProviderOperation,
    ContractToRuntimeRandomness,
    ContractToKdf,
    ContractToHkdf,
    ContractToHmac,
    ContractToAead,
    ContractToKeyGeneration,
    ContractToKeysetStorage,
    ContractToVaultCreation,
    ContractToVaultUnlock,
    ContractToVaultSession,
    ContractToVaultPersistence,
    ContractToSecureStorageSuccess,
    ContractToSecureMetadataSuccess,
    ContractToManifestReadWrite,
    ContractToMigration,
    ContractToProductionSync,
    ContractToBackendClient,
    ContractToBdkWalletState,
    ContractToSettingsCodec,
    ContractToUiSurface,
    ContractToSigning,
    ContractToBroadcasting,
    ContractToTorTransport,
    ContractToNostrParsing,
    ContractToPublicEndpointDefault,
    ContractToMainnet,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequiredEvidenceClass {
    IdentityDecisionEvidence,
    IdentityIsolationEvidence,
    SyntheticNamespaceEvidence,
    SourceSetConfinementEvidence,
    ImplementationDecisionEvidence,
    PrerequisiteAuditEvidence,
    ScopeDecisionEvidence,
    FutureBranchApprovalEvidence,
    ContractReviewEvidence,
    SyntheticSafeIdReviewEvidence,
    TestOnlyNamespaceReviewEvidence,
    SourceSetPlacementReviewEvidence,
    ConstructionAbsenceEvidence,
    RuntimeReachabilityAbsenceEvidence,
    ProviderSelectionAbsenceEvidence,
    RegistryFactoryDispatcherAbsenceEvidence,
    ExecutorAndKatAbsenceEvidence,
    ProviderOperationAbsenceEvidence,
    CryptoExecutionAbsenceEvidence,
    VaultStorageSyncAbsenceEvidence,
    BackendSettingsUiAbsenceEvidence,
    SigningBroadcastingNetworkAbsenceEvidence,
    SourceGuardCoverageEvidence,
    RedactionLeakageReviewEvidence,
    FutureImplementationReviewEvidence,
    NonAuthorizationEvidence,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationContractEvidenceSource {
    TestOnlyProviderIdentityDecision,
    TestOnlyProviderIdentityIsolationGuard,
    SyntheticIdentityNamespace,
    TestOnlyProviderIdentitySourceSetConfinement,
    TestOnlyProviderIdentityImplementationDecision,
    TestOnlyProviderIdentityImplementationPrerequisiteAudit,
    TestOnlyProviderIdentityImplementationScopeDecision,
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

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationContractRedactionClass {
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
    SafePolicyIdOnly,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationContractFutureReviewRequirement {
    ExplicitFutureBranchApproval,
    ContractReviewRequest,
    TestOnlyProviderIdentityImplementationDesign,
    SyntheticSafeIdReview,
    TestOnlyNamespaceReview,
    SourceSetPlacementReview,
    ConstructionAbsenceReview,
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
    RedactionAndLeakageReview,
    SourceGuardCoverageReview,
    NonAuthorizationReview,
    MainnetNonAuthorizationReview,
}

data class SkaldVaultV1TestOnlyProviderIdentityImplementationContractSectionRow(
    val section: SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection,
    val modeled: Boolean,
    val futureReviewOnly: Boolean,
    val currentImplementationAllowed: Boolean,
    val nonAuthorizing: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationContractSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirementRow(
    val requirement: SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement,
    val section: SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection,
    val modeled: Boolean,
    val currentEvidenceOnly: Boolean,
    val futureReviewOnly: Boolean,
    val authorizesImplementation: Boolean,
    val nonAuthorizing: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationContractSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenClauseRow(
    val clause: SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenClause,
    val section: SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection,
    val forbidden: Boolean,
    val currentBridgePresent: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationContractSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkageRow(
    val runtimeLinkage: SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage,
    val section: SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection,
    val forbidden: Boolean,
    val currentBridgePresent: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationContractSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationContractForbiddenPromotionPathRow(
    val promotionRoute: SkaldVaultV1TestOnlyProviderIdentityImplementationContractForbiddenPromotionPath,
    val forbidden: Boolean,
    val currentBridgePresent: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationContractSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequiredEvidenceRow(
    val evidenceClass: SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequiredEvidenceClass,
    val modeled: Boolean,
    val presentAsModelEvidence: Boolean,
    val futureRequired: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationContractSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationContractEvidenceSourceRow(
    val evidenceSource: SkaldVaultV1TestOnlyProviderIdentityImplementationContractEvidenceSource,
    val included: Boolean,
    val modeled: Boolean,
    val nonAuthorizing: Boolean,
    val authorizesImplementation: Boolean,
    val authorizesProductionPromotion: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationContractSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationContractFutureReviewRow(
    val requirement: SkaldVaultV1TestOnlyProviderIdentityImplementationContractFutureReviewRequirement,
    val futureRequired: Boolean,
    val satisfiedNow: Boolean,
    val authorizesCurrentImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationContractSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationContractCapabilities(
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
        val Current = SkaldVaultV1TestOnlyProviderIdentityImplementationContractCapabilities(
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

data class SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequest(
    val includePriorIdentityDecisionEvidence: Boolean = true,
    val includePriorIsolationEvidence: Boolean = true,
    val includePriorNamespaceEvidence: Boolean = true,
    val includePriorSourceSetConfinementEvidence: Boolean = true,
    val includePriorImplementationDecisionEvidence: Boolean = true,
    val includePriorPrerequisiteAuditEvidence: Boolean = true,
    val includePriorScopeDecisionEvidence: Boolean = true,
    val userConsentOverrideRequested: Boolean = false,
    val warningOnlyEvidenceClaimed: Boolean = false,
    val testOnlyEvidenceClaimedAsProductionPromotion: Boolean = false,
    val releaseEvidenceClaimed: Boolean = false,
    val futureBranchApprovalClaimed: Boolean = false,
    val prerequisiteCompletionClaimed: Boolean = false,
    val scopeApprovalClaimed: Boolean = false,
    val contractSatisfactionClaimed: Boolean = false,
    val contractClaimedAsImplementationAuthorization: Boolean = false,
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

data class SkaldVaultV1TestOnlyProviderIdentityImplementationContractEvidence(
    val policyId: String,
    val policyVersion: Int,
    val implementationContractModeled: Boolean,
    val stillDisabled: Boolean,
    val priorIdentityDecisionEvidenceIncluded: Boolean,
    val priorIsolationEvidenceIncluded: Boolean,
    val priorNamespaceEvidenceIncluded: Boolean,
    val priorSourceSetConfinementEvidenceIncluded: Boolean,
    val priorImplementationDecisionEvidenceIncluded: Boolean,
    val priorPrerequisiteAuditEvidenceIncluded: Boolean,
    val priorScopeDecisionEvidenceIncluded: Boolean,
    val contractRequirementsModeled: Boolean,
    val forbiddenImplementationClausesModeled: Boolean,
    val forbiddenRuntimeLinkagesModeled: Boolean,
    val docsMayDescribeFutureReview: Boolean,
    val testsMayAssertBlockedContract: Boolean,
    val statuses: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationContractStatus>,
    val outcomes: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationContractOutcome>,
    val sectionRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationContractSectionRow>,
    val requirementRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirementRow>,
    val forbiddenImplementationClauseRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenClauseRow>,
    val forbiddenRuntimeLinkageRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkageRow>,
    val forbiddenPromotionPathRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationContractForbiddenPromotionPathRow>,
    val requiredEvidenceRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequiredEvidenceRow>,
    val evidenceSourceRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationContractEvidenceSourceRow>,
    val futureReviewRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationContractFutureReviewRow>,
    val blockers: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker>,
    val redactionClasses: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationContractRedactionClass>,
    val capabilities: SkaldVaultV1TestOnlyProviderIdentityImplementationContractCapabilities,
)

object SkaldVaultV1TestOnlyProviderIdentityImplementationContractPolicy {
    const val POLICY_ID: String = "skald-vault-v1-test-only-provider-identity-implementation-contract-v1"
    const val POLICY_VERSION: Int = 1

    fun currentImplementationContractEvidence():
        SkaldVaultV1TestOnlyProviderIdentityImplementationContractEvidence =
        evaluateImplementationContract()

    fun evaluateImplementationContract(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequest =
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequest(),
    ): SkaldVaultV1TestOnlyProviderIdentityImplementationContractEvidence =
        SkaldVaultV1TestOnlyProviderIdentityImplementationContractEvidence(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            implementationContractModeled = true,
            stillDisabled = true,
            priorIdentityDecisionEvidenceIncluded = request.includePriorIdentityDecisionEvidence,
            priorIsolationEvidenceIncluded = request.includePriorIsolationEvidence,
            priorNamespaceEvidenceIncluded = request.includePriorNamespaceEvidence,
            priorSourceSetConfinementEvidenceIncluded = request.includePriorSourceSetConfinementEvidence,
            priorImplementationDecisionEvidenceIncluded = request.includePriorImplementationDecisionEvidence,
            priorPrerequisiteAuditEvidenceIncluded = request.includePriorPrerequisiteAuditEvidence,
            priorScopeDecisionEvidenceIncluded = request.includePriorScopeDecisionEvidence,
            contractRequirementsModeled = true,
            forbiddenImplementationClausesModeled = true,
            forbiddenRuntimeLinkagesModeled = true,
            docsMayDescribeFutureReview = true,
            testsMayAssertBlockedContract = true,
            statuses = SkaldVaultV1TestOnlyProviderIdentityImplementationContractStatus.entries.toSet(),
            outcomes = SkaldVaultV1TestOnlyProviderIdentityImplementationContractOutcome.entries.toSet(),
            sectionRows = currentSectionRows(),
            requirementRows = currentRequirementRows(),
            forbiddenImplementationClauseRows = currentForbiddenImplementationClauseRows(),
            forbiddenRuntimeLinkageRows = currentForbiddenRuntimeLinkageRows(),
            forbiddenPromotionPathRows = currentForbiddenPromotionPathRows(),
            requiredEvidenceRows = currentRequiredEvidenceRows(request),
            evidenceSourceRows = currentEvidenceSourceRows(request),
            futureReviewRows = currentFutureReviewRows(),
            blockers = baseBlockers() + requestBlockers(request),
            redactionClasses =
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractRedactionClass.entries.toSet(),
            capabilities = SkaldVaultV1TestOnlyProviderIdentityImplementationContractCapabilities.Current,
        )

    private fun currentSectionRows(): List<SkaldVaultV1TestOnlyProviderIdentityImplementationContractSectionRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.entries.map { section ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractSectionRow(
                section = section,
                modeled = true,
                futureReviewOnly = section in futureReviewSections(),
                currentImplementationAllowed = false,
                nonAuthorizing = true,
                label = redactedLabel(),
            )
        }

    private fun currentRequirementRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirementRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.entries.map { requirement ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirementRow(
                requirement = requirement,
                section = sectionFor(requirement),
                modeled = true,
                currentEvidenceOnly = requirement in currentEvidenceOnlyRequirements(),
                futureReviewOnly = requirement !in currentEvidenceOnlyRequirements(),
                authorizesImplementation = false,
                nonAuthorizing = true,
                label = redactedLabel(),
            )
        }

    private fun currentForbiddenImplementationClauseRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenClauseRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenClause.entries.map { clause ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenClauseRow(
                clause = clause,
                section = sectionFor(clause),
                forbidden = true,
                currentBridgePresent = false,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentForbiddenRuntimeLinkageRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkageRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage.entries.map { linkage ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkageRow(
                runtimeLinkage = linkage,
                section = sectionFor(linkage),
                forbidden = true,
                currentBridgePresent = false,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentForbiddenPromotionPathRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationContractForbiddenPromotionPathRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationContractForbiddenPromotionPath.entries.map { route ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractForbiddenPromotionPathRow(
                promotionRoute = route,
                forbidden = true,
                currentBridgePresent = false,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentRequiredEvidenceRows(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequest,
    ): List<SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequiredEvidenceRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequiredEvidenceClass.entries.map { evidenceClass ->
            val presentAsModelEvidence = when (evidenceClass) {
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequiredEvidenceClass.IdentityDecisionEvidence ->
                    request.includePriorIdentityDecisionEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequiredEvidenceClass.IdentityIsolationEvidence ->
                    request.includePriorIsolationEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequiredEvidenceClass.SyntheticNamespaceEvidence ->
                    request.includePriorNamespaceEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequiredEvidenceClass.SourceSetConfinementEvidence ->
                    request.includePriorSourceSetConfinementEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequiredEvidenceClass.ImplementationDecisionEvidence ->
                    request.includePriorImplementationDecisionEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequiredEvidenceClass.PrerequisiteAuditEvidence ->
                    request.includePriorPrerequisiteAuditEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequiredEvidenceClass.ScopeDecisionEvidence ->
                    request.includePriorScopeDecisionEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequiredEvidenceClass.NonAuthorizationEvidence -> true
                else -> false
            }
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequiredEvidenceRow(
                evidenceClass = evidenceClass,
                modeled = true,
                presentAsModelEvidence = presentAsModelEvidence,
                futureRequired = !presentAsModelEvidence || evidenceClass in futureReviewEvidenceClasses(),
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentEvidenceSourceRows(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequest,
    ): List<SkaldVaultV1TestOnlyProviderIdentityImplementationContractEvidenceSourceRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationContractEvidenceSource.entries.map { source ->
            val included = when (source) {
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractEvidenceSource.TestOnlyProviderIdentityDecision ->
                    request.includePriorIdentityDecisionEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractEvidenceSource.TestOnlyProviderIdentityIsolationGuard ->
                    request.includePriorIsolationEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractEvidenceSource.SyntheticIdentityNamespace ->
                    request.includePriorNamespaceEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractEvidenceSource.TestOnlyProviderIdentitySourceSetConfinement ->
                    request.includePriorSourceSetConfinementEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractEvidenceSource
                    .TestOnlyProviderIdentityImplementationDecision ->
                    request.includePriorImplementationDecisionEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractEvidenceSource
                    .TestOnlyProviderIdentityImplementationPrerequisiteAudit ->
                    request.includePriorPrerequisiteAuditEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractEvidenceSource
                    .TestOnlyProviderIdentityImplementationScopeDecision ->
                    request.includePriorScopeDecisionEvidence
                else -> true
            }
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractEvidenceSourceRow(
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
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationContractFutureReviewRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationContractFutureReviewRequirement.entries.map { requirement ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractFutureReviewRow(
                requirement = requirement,
                futureRequired = true,
                satisfiedNow = false,
                authorizesCurrentImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun futureReviewSections(): Set<SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection> =
        setOf(
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.FutureReviewOnlyContract,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.IdentityLabelContract,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.SourceSetPlacementContract,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.RedactionContract,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.SourceGuardContract,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.FutureImplementationReviewContract,
        )

    private fun currentEvidenceOnlyRequirements():
        Set<SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement> =
        setOf(
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustBeModelOnlyInCurrentBranch,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustBeStillDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustExposeOnlyRedactedSafeLabels,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustNotAcceptRawMaterial,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustNotHoldProviderHandles,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustNotHoldCryptoObjects,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustNotHoldStorageReferences,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustNotHoldBackendReferences,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustNotHoldWalletReferences,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustNotHoldEndpointReferences,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustNotHoldSourceLocationPayloads,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustNotHoldFutureApprovalPayloads,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustNotHoldImplementationPayloads,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustNotHoldScopePayloads,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustTreatPriorEvidenceAsNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustTreatAllowedFutureScopeAsNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustTreatAbsenceEvidenceAsNonAuthorizing,
        )

    private fun futureReviewEvidenceClasses():
        Set<SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequiredEvidenceClass> =
        setOf(
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequiredEvidenceClass.FutureBranchApprovalEvidence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequiredEvidenceClass.ContractReviewEvidence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequiredEvidenceClass.SyntheticSafeIdReviewEvidence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequiredEvidenceClass.TestOnlyNamespaceReviewEvidence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequiredEvidenceClass.SourceSetPlacementReviewEvidence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequiredEvidenceClass.RedactionLeakageReviewEvidence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequiredEvidenceClass.FutureImplementationReviewEvidence,
        )

    private fun sectionFor(
        requirement: SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement,
    ): SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection =
        when (requirement) {
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustBeModelOnlyInCurrentBranch,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustBeStillDisabled ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.ModelOnlyContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustRequireExplicitFutureBranchApproval ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.FutureReviewOnlyContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustUseSyntheticSafeIdOnly,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustUseTestOnlyNamespaceOnly ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.IdentityLabelContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustRequireNonProductionSourceSetPlacement ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.SourceSetPlacementContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement
                .MustRemainAbsentFromCommonMainRuntimeConstruction,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement
                .MustRemainAbsentFromAndroidMainRuntimeConstruction,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement
                .MustRemainAbsentFromDesktopMainRuntimeConstruction ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.ConstructionAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustRemainAbsentFromProviderSelection ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.ProviderSelectionAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustRemainAbsentFromProviderRegistry ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.RegistryAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustRemainAbsentFromProviderFactory ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.FactoryAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustRemainAbsentFromProviderDispatcher ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.DispatcherAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustRemainAbsentFromExecutorTargeting ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.ExecutorTargetAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustRemainAbsentFromProviderKatExecutor ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.ProviderKatExecutorAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustRemainAbsentFromProviderOperations ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.ProviderOperationAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustRemainAbsentFromRandomness,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustRemainAbsentFromKdf,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustRemainAbsentFromHkdf,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustRemainAbsentFromHmac,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustRemainAbsentFromAead,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustRemainAbsentFromKeyGeneration,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustRemainAbsentFromKeysetStorage ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.CryptoExecutionAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustRemainAbsentFromVaultCreation,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustRemainAbsentFromVaultUnlock,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustRemainAbsentFromVaultSession ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.VaultLifecycleAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustRemainAbsentFromVaultPersistence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustRemainAbsentFromStorageNamespaceWrites,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustRemainAbsentFromStoragePathWrites,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustRemainAbsentFromManifestReadWrite,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustRemainAbsentFromMigration ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.PersistenceAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustRemainAbsentFromSecureStorageSuccess ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.SecureStorageAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustRemainAbsentFromSecureMetadataSuccess ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.SecureMetadataAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustRemainAbsentFromProductionSync ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.RuntimeReachabilityAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustRemainAbsentFromBackendClients ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.BackendClientAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustRemainAbsentFromBdkWalletState ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.BdkWalletStateAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustRemainAbsentFromSettingsCodecs ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.SettingsCodecAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustRemainAbsentFromUiSurface ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.UiSurfaceAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustRemainAbsentFromSigning,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustRemainAbsentFromBroadcasting ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.SigningBroadcastingAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustRemainAbsentFromTorTransport,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustRemainAbsentFromNostrParsing ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.TorNostrAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustRemainAbsentFromPublicEndpointDefaults ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.PublicEndpointAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustRemainAbsentFromMainnet ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.MainnetAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustExposeOnlyRedactedSafeLabels,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustNotAcceptRawMaterial,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustNotHoldProviderHandles,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustNotHoldCryptoObjects,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustNotHoldStorageReferences,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustNotHoldBackendReferences,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustNotHoldWalletReferences,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustNotHoldEndpointReferences,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustNotHoldSourceLocationPayloads,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustNotHoldFutureApprovalPayloads,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustNotHoldImplementationPayloads,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustNotHoldScopePayloads ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.RedactionContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustTreatPriorEvidenceAsNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustTreatAllowedFutureScopeAsNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequirement.MustTreatAbsenceEvidenceAsNonAuthorizing ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.FutureImplementationReviewContract
        }

    private fun sectionFor(
        clause: SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenClause,
    ): SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection =
        when (clause) {
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenClause.CurrentBranchImplementsProviderIdentity,
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenClause.CurrentBranchImplementsProductionIdentity,
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenClause.CurrentBranchImplementsVaultCryptoProvider ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.ConstructionAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenClause.CurrentBranchCreatesProviderRegistryEntry ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.RegistryAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenClause.CurrentBranchCreatesProviderFactory ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.FactoryAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenClause.CurrentBranchCreatesProviderDispatcher ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.DispatcherAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenClause.CurrentBranchCreatesExecutorTarget ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.ExecutorTargetAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenClause.CurrentBranchCreatesProviderKatExecutor,
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenClause
                .CurrentBranchCreatesRunnableExecutorInterface ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.ProviderKatExecutorAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenClause.CurrentBranchExecutesProviderOperations ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.ProviderOperationAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenClause.CurrentBranchExecutesCrypto ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.CryptoExecutionAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenClause.CurrentBranchExecutesVaultLifecycle ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.VaultLifecycleAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenClause.CurrentBranchPersistsVault ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.PersistenceAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenClause.CurrentBranchWritesSettings ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.SettingsCodecAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenClause.CurrentBranchExposesUi ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.UiSurfaceAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenClause.CurrentBranchCreatesBackendClient ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.BackendClientAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenClause.CurrentBranchCreatesBdkWalletState ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.BdkWalletStateAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenClause.CurrentBranchSignsOrBroadcasts ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.SigningBroadcastingAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenClause.CurrentBranchEnablesNetworkTransport,
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenClause.CurrentBranchParsesNostrSecrets ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.TorNostrAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenClause.CurrentBranchAddsPublicEndpoint ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.PublicEndpointAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenClause.CurrentBranchEnablesMainnet ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.MainnetAbsenceContract
        }

    private fun sectionFor(
        linkage: SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage,
    ): SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection =
        when (linkage) {
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage
                .ContractToProviderSelectionRuntime ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.ProviderSelectionAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage.ContractToProviderRegistryRuntime ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.RegistryAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage.ContractToProviderFactoryRuntime ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.FactoryAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage.ContractToProviderDispatcherRuntime ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.DispatcherAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage.ContractToExecutorTargetRuntime ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.ExecutorTargetAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage.ContractToProviderKatExecutorRuntime ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.ProviderKatExecutorAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage.ContractToProviderOperationRuntime ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.ProviderOperationAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage.ContractToRandomnessRuntime,
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage.ContractToKdfRuntime,
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage.ContractToHkdfRuntime,
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage.ContractToHmacRuntime,
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage.ContractToAeadRuntime,
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage.ContractToKeyGenerationRuntime,
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage.ContractToKeysetStorageRuntime ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.CryptoExecutionAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage.ContractToVaultCreationRuntime,
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage.ContractToVaultUnlockRuntime,
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage.ContractToVaultSessionRuntime ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.VaultLifecycleAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage.ContractToVaultPersistenceRuntime,
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage.ContractToStorageNamespaceRuntime,
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage.ContractToStoragePathRuntime,
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage.ContractToManifestRuntime,
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage.ContractToMigrationRuntime ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.PersistenceAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage.ContractToSecureStorageRuntime ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.SecureStorageAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage.ContractToSecureMetadataRuntime ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.SecureMetadataAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage.ContractToProductionSyncRuntime ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.RuntimeReachabilityAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage.ContractToBackendClientRuntime ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.BackendClientAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage.ContractToBdkWalletStateRuntime ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.BdkWalletStateAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage.ContractToSettingsCodecRuntime ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.SettingsCodecAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage.ContractToUiRuntime ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.UiSurfaceAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage.ContractToSigningRuntime,
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage.ContractToBroadcastingRuntime ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.SigningBroadcastingAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage.ContractToTorRuntime,
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage.ContractToNostrRuntime ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.TorNostrAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage.ContractToPublicEndpointRuntime ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.PublicEndpointAbsenceContract
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenRuntimeLinkage.ContractToMainnetRuntime ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationContractSection.MainnetAbsenceContract
        }

    private fun baseBlockers(): Set<SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker> =
        setOf(
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.CurrentContractBlocked,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.ContractReviewRequired,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.ImplementationNotAuthorized,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.ProductionPromotionNotAuthorized,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.MainnetNotAuthorized,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.ContractRequirementsNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.PriorIdentityDecisionEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.PriorIsolationEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.PriorNamespaceEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker
                .PriorSourceSetConfinementEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker
                .PriorImplementationDecisionEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.PriorPrerequisiteAuditEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.PriorScopeDecisionEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.PrerequisiteCompletionMissing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.ScopeApprovalMissing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.FutureBranchApprovalMissing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.NoTestOnlyProviderIdentityImplementation,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.NoProductionProviderIdentityImplementation,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.NoProviderImplementation,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.NoProviderFactory,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.NoProviderDispatcher,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.NoNonDisabledRegistryEntry,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.NoExecutorTarget,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.NoProviderKatExecutor,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.NoRunnableExecutorInterface,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.ProviderSelectionDisabledProviderOnly,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.ProductionProviderSelectableFalse,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.ProviderOperationAuthorizationBlocked,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.CryptoExecutionBlocked,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.VaultLifecycleDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.PersistenceDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.ProductionSyncDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.UserConsentCannotOverride,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.WarningOnlyEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.TestOnlyEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.ReleaseEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.MainnetDisabled,
        )

    private fun requestBlockers(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationContractRequest,
    ): Set<SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker> =
        buildSet {
            if (request.userConsentOverrideRequested) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.UserConsentCannotOverride)
            }
            if (request.warningOnlyEvidenceClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.WarningOnlyEvidenceNonAuthorizing)
            }
            if (request.testOnlyEvidenceClaimedAsProductionPromotion) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.TestOnlyEvidenceNonAuthorizing)
            }
            if (request.releaseEvidenceClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.ReleaseEvidenceNonAuthorizing)
            }
            if (request.futureBranchApprovalClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.FutureBranchApprovalClaimRejected)
            }
            if (request.prerequisiteCompletionClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker
                        .PrerequisiteCompletionClaimRejected,
                )
            }
            if (request.scopeApprovalClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.ScopeApprovalClaimRejected)
            }
            if (request.contractSatisfactionClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker
                        .ContractSatisfactionClaimRejected,
                )
            }
            if (request.contractClaimedAsImplementationAuthorization) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker
                        .ContractAsImplementationAuthorizationRejected,
                )
            }
            if (request.providerImplementationClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker
                        .ProviderImplementationClaimRejected,
                )
            }
            if (request.registryEntryClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.RegistryEntryClaimRejected)
            }
            if (request.factoryReachabilityClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.FactoryReachabilityRejected)
            }
            if (request.dispatcherReachabilityClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.DispatcherReachabilityRejected)
            }
            if (request.executorTargetClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.ExecutorTargetRejected)
            }
            if (request.providerKatExecutorClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.ProviderKatExecutorRejected)
            }
            if (request.providerOperationClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.ProviderOperationRejected)
            }
            if (request.cryptoExecutionClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.CryptoExecutionRejected)
            }
            if (request.persistenceReachabilityClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.PersistenceReachabilityRejected)
            }
            if (request.settingsReachabilityClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.SettingsReachabilityRejected)
            }
            if (request.uiReachabilityClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.UiReachabilityRejected)
            }
            if (request.mainnetReachabilityClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationContractBlocker.MainnetReachabilityRejected)
            }
        }

    private fun redactedLabel(): SkaldVaultV1TestOnlyProviderIdentityImplementationContractSafeLabel =
        SkaldVaultV1TestOnlyProviderIdentityImplementationContractSafeLabel("redacted")
}
