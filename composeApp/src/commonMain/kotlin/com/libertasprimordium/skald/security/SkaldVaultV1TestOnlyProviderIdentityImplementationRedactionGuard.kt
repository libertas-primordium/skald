package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionSafeLabel(
    val value: String,
) {
    override fun toString(): String = "RedactedLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardStatus {
    RedactionGuardModeled,
    StillDisabled,
    SafeOutputsOnly,
    LeakageBlocked,
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
    PriorSourceGuardCoverageEvidenceNonAuthorizing,
    PriorRedactionBoundaryEvidenceNonAuthorizing,
    RedactionSurfacesModeled,
    SensitiveReferenceClassesModeled,
    AllowedOutputClassesModeled,
    ForbiddenOutputClassesModeled,
    ForbiddenLeakagePathsModeled,
    ProviderSelectionDisabledProviderOnly,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionOutcome {
    CurrentRedactionGuardModeled,
    RedactionReviewRequired,
    SafeOutputsOnly,
    LeakageBlocked,
    ImplementationNotAuthorized,
    ProductionPromotionNotAuthorized,
    MainnetNotAuthorized,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionSurface {
    PolicyIdOutput,
    StatusOutput,
    OutcomeOutput,
    BoundaryNameOutput,
    EvidenceSourceOutput,
    RowLabelOutput,
    SafeDecisionSummaryOutput,
    SourceGuardSummaryOutput,
    FutureReviewSummaryOutput,
    BlockerSummaryOutput,
    TestAssertionOutput,
    DocumentationOutput,
    BuildHistoryOutput,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationSensitiveReferenceClass {
    CandidateIdentityId,
    SyntheticSafeId,
    SourceSetReference,
    PlacementReference,
    ProviderReference,
    ProviderHandle,
    CryptoObjectReference,
    StorageReference,
    StoragePathReference,
    BackendReference,
    EndpointReference,
    WalletReference,
    DescriptorReference,
    CredentialReference,
    SourceLocationReference,
    FutureApprovalReference,
    ImplementationPayloadReference,
    PrerequisitePayloadReference,
    ScopePayloadReference,
    ContractPayloadReference,
    ReadinessPayloadReference,
    RuntimeLinkagePayloadReference,
    PromotionPayloadReference,
    SourceGuardPayloadReference,
    RawMaterialReference,
    SecretLookingReference,
    WalletFixtureReference,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationAllowedOutputClass {
    PolicyIdOnly,
    PolicyVersionOnly,
    EnumNameOnly,
    SafeLabelOnly,
    RedactedLabelOnly,
    BooleanEvidenceOnly,
    CountEvidenceOnly,
    NonAuthorizingSummaryOnly,
    BlockedStatusOnly,
    FutureReviewRequiredOnly,
    NoCurrentImplementationOnly,
    NoRuntimeLinkageOnly,
    NoPromotionOnly,
    SourceGuardCoverageSummaryOnly,
    DocumentationCrossLinkOnly,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenOutputClass {
    RawCandidateIdentityId,
    RawSyntheticSafeId,
    RawSourceSetPath,
    RawProviderHandle,
    RawCryptoObject,
    RawStoragePath,
    RawBackendEndpoint,
    RawWalletDescriptor,
    RawWalletCredential,
    RawSourceLocation,
    RawFutureApprovalPayload,
    RawImplementationPayload,
    RawPrerequisitePayload,
    RawScopePayload,
    RawContractPayload,
    RawReadinessPayload,
    RawRuntimeLinkagePayload,
    RawPromotionPayload,
    RawSourceGuardPayload,
    RawSecretMaterial,
    RawWalletFixture,
    RawDiagnosticPayload,
    HashOfSecretMaterial,
    FingerprintOfSecretMaterial,
    CrashReportPayload,
    AnalyticsPayload,
    SupportExportPayload,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker {
    RedactionGuardModelOnly,
    CurrentRedactionEvidenceNonAuthorizing,
    SafeOutputEvidenceNonAuthorizing,
    RedactionPassingDoesNotAuthorizeImplementation,
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
    RawIdentityOutputRejected,
    RawSafeIdOutputRejected,
    RawProviderReferenceOutputRejected,
    RawCryptoReferenceOutputRejected,
    RawStorageReferenceOutputRejected,
    RawBackendReferenceOutputRejected,
    RawEndpointReferenceOutputRejected,
    RawWalletReferenceOutputRejected,
    RawSourceLocationOutputRejected,
    RawFutureApprovalOutputRejected,
    RawImplementationPayloadOutputRejected,
    RawRuntimeLinkagePayloadOutputRejected,
    RawPromotionPayloadOutputRejected,
    RawSourceGuardPayloadOutputRejected,
    SecretHashOrFingerprintOutputRejected,
    CrashReportOutputRejected,
    AnalyticsOutputRejected,
    SupportExportOutputRejected,
    RedactionEvidenceAuthorizationRejected,
    ProviderImplementationClaimRejected,
    ProviderSelectionClaimRejected,
    ProductionProviderSelectableClaimRejected,
    PromotionClaimRejected,
    MainnetReachabilityClaimRejected,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenLeakagePath {
    RedactionGuardToProviderSelectionDiagnostics,
    RedactionGuardToProviderRegistryDiagnostics,
    RedactionGuardToProviderFactoryDiagnostics,
    RedactionGuardToProviderDispatcherDiagnostics,
    RedactionGuardToExecutorTargetDiagnostics,
    RedactionGuardToProviderKatExecutorDiagnostics,
    RedactionGuardToProviderOperationDiagnostics,
    RedactionGuardToCryptoDiagnostics,
    RedactionGuardToVaultLifecycleDiagnostics,
    RedactionGuardToPersistenceDiagnostics,
    RedactionGuardToSecureStorageDiagnostics,
    RedactionGuardToSecureMetadataDiagnostics,
    RedactionGuardToBackendDiagnostics,
    RedactionGuardToBdkDiagnostics,
    RedactionGuardToSettingsDiagnostics,
    RedactionGuardToUiDiagnostics,
    RedactionGuardToSigningDiagnostics,
    RedactionGuardToBroadcastingDiagnostics,
    RedactionGuardToTorDiagnostics,
    RedactionGuardToNostrDiagnostics,
    RedactionGuardToPublicEndpointDiagnostics,
    RedactionGuardToMainnetDiagnostics,
    RedactionGuardToCrashReport,
    RedactionGuardToAnalytics,
    RedactionGuardToSupportExport,
    RedactionGuardToBuildLogMaterial,
    RedactionGuardToTestFailureMaterial,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardForbiddenPromotionPath {
    RedactionGuardToProviderImplementation,
    RedactionGuardToTestOnlyProviderIdentityImplementation,
    RedactionGuardToProductionProviderIdentityImplementation,
    RedactionGuardToProviderSelection,
    RedactionGuardToProductionProviderSelectable,
    RedactionGuardToRegistryEntry,
    RedactionGuardToFactory,
    RedactionGuardToDispatcher,
    RedactionGuardToExecutorTarget,
    RedactionGuardToProviderKatExecutor,
    RedactionGuardToProviderOperation,
    RedactionGuardToRuntimeRandomness,
    RedactionGuardToKdf,
    RedactionGuardToHkdf,
    RedactionGuardToHmac,
    RedactionGuardToAead,
    RedactionGuardToKeyGeneration,
    RedactionGuardToKeysetStorage,
    RedactionGuardToVaultCreation,
    RedactionGuardToVaultUnlock,
    RedactionGuardToVaultSession,
    RedactionGuardToVaultPersistence,
    RedactionGuardToSecureStorageSuccess,
    RedactionGuardToSecureMetadataSuccess,
    RedactionGuardToManifestReadWrite,
    RedactionGuardToMigration,
    RedactionGuardToProductionSync,
    RedactionGuardToBackendClient,
    RedactionGuardToBdkWalletState,
    RedactionGuardToSettingsCodec,
    RedactionGuardToUiSurface,
    RedactionGuardToSigning,
    RedactionGuardToBroadcasting,
    RedactionGuardToTorTransport,
    RedactionGuardToNostrParsing,
    RedactionGuardToPublicEndpointDefault,
    RedactionGuardToMainnet,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardRequiredEvidenceClass {
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
    VaultRedactionBoundaryEvidence,
    SafeOutputEvidence,
    ForbiddenOutputAbsenceEvidence,
    LeakageAbsenceEvidence,
    ProviderSelectionFailClosedEvidence,
    DisabledProviderEvidence,
    ProductionProviderSelectableFalseEvidence,
    NonAuthorizationEvidence,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidenceSource {
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
    VaultRedactionLeakageBoundary,
    RedactionGuardEvidence,
    SourceGuardEvidence,
    DocumentationCrossLink,
    ProviderSelectionBoundary,
    DisabledProviderBoundary,
    AbsenceEvidence,
    NonAuthorizationEvidence,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardRedactionClass {
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
    RedactedDiagnosticReference,
    SafePolicyIdOnly,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardFutureReviewRequirement {
    ExplicitFutureBranchApproval,
    RedactionReview,
    SafeOutputReview,
    SourceGuardOutputReview,
    RuntimeLinkageOutputReview,
    PromotionOutputReview,
    DiagnosticOutputReview,
    CrashReportOutputReview,
    AnalyticsOutputReview,
    SupportExportOutputReview,
    ProviderSelectionAbsenceReview,
    RuntimeRootAbsenceReview,
    NonAuthorizationReview,
    MainnetNonAuthorizationReview,
}

data class SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionSurfaceRow(
    val surface: SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionSurface,
    val modeled: Boolean,
    val safeOutputOnly: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationSensitiveReferenceClassRow(
    val referenceClass: SkaldVaultV1TestOnlyProviderIdentityImplementationSensitiveReferenceClass,
    val modeled: Boolean,
    val rawOutputAllowed: Boolean,
    val leakagePresent: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationAllowedOutputClassRow(
    val outputClass: SkaldVaultV1TestOnlyProviderIdentityImplementationAllowedOutputClass,
    val modeled: Boolean,
    val safeForCurrentEvidence: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenOutputClassRow(
    val outputClass: SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenOutputClass,
    val modeled: Boolean,
    val present: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlockerRow(
    val blocker: SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker,
    val active: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenLeakagePathRow(
    val leakagePath: SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenLeakagePath,
    val forbidden: Boolean,
    val leakagePresent: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardForbiddenPromotionPathRow(
    val promotionRoute: SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardForbiddenPromotionPath,
    val forbidden: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardRequiredEvidenceRow(
    val evidenceClass: SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardRequiredEvidenceClass,
    val modeled: Boolean,
    val presentAsModelEvidence: Boolean,
    val futureRequired: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidenceSourceRow(
    val evidenceSource: SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidenceSource,
    val included: Boolean,
    val modeled: Boolean,
    val nonAuthorizing: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardFutureReviewRow(
    val requirement: SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardFutureReviewRequirement,
    val futureRequired: Boolean,
    val satisfiedNow: Boolean,
    val authorizesCurrentImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardCapabilities(
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
        val Current = SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardCapabilities(
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

data class SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardRequest(
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
    val includePriorRedactionBoundaryEvidence: Boolean = true,
    val userConsentOverrideRequested: Boolean = false,
    val warningOnlyEvidenceClaimed: Boolean = false,
    val testOnlyEvidenceClaimedAsProductionPromotion: Boolean = false,
    val releaseEvidenceClaimed: Boolean = false,
    val rawIdentityOutputClaimed: Boolean = false,
    val rawSafeIdOutputClaimed: Boolean = false,
    val rawProviderReferenceOutputClaimed: Boolean = false,
    val rawCryptoReferenceOutputClaimed: Boolean = false,
    val rawStorageReferenceOutputClaimed: Boolean = false,
    val rawBackendReferenceOutputClaimed: Boolean = false,
    val rawEndpointReferenceOutputClaimed: Boolean = false,
    val rawWalletReferenceOutputClaimed: Boolean = false,
    val rawSourceLocationOutputClaimed: Boolean = false,
    val rawFutureApprovalOutputClaimed: Boolean = false,
    val rawImplementationPayloadOutputClaimed: Boolean = false,
    val rawRuntimeLinkagePayloadOutputClaimed: Boolean = false,
    val rawPromotionPayloadOutputClaimed: Boolean = false,
    val rawSourceGuardPayloadOutputClaimed: Boolean = false,
    val secretHashOrFingerprintOutputClaimed: Boolean = false,
    val crashReportOutputClaimed: Boolean = false,
    val analyticsOutputClaimed: Boolean = false,
    val supportExportOutputClaimed: Boolean = false,
    val redactionEvidenceClaimedAsImplementationAuthorization: Boolean = false,
    val providerImplementationClaimed: Boolean = false,
    val providerSelectionClaimed: Boolean = false,
    val productionProviderSelectableClaimed: Boolean = false,
    val promotionClaimed: Boolean = false,
    val mainnetReachabilityClaimed: Boolean = false,
) {
    override fun toString(): String = "RedactedRequest(claims=modeled)"
}

data class SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidence(
    val policyId: String,
    val policyVersion: Int,
    val redactionGuardModeled: Boolean,
    val stillDisabled: Boolean,
    val safeOutputsOnly: Boolean,
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
    val priorRedactionBoundaryEvidenceIncluded: Boolean,
    val redactionSurfacesModeled: Boolean,
    val sensitiveReferenceClassesModeled: Boolean,
    val allowedOutputClassesModeled: Boolean,
    val forbiddenOutputClassesModeled: Boolean,
    val forbiddenLeakagePathsModeled: Boolean,
    val docsMayDescribeRedaction: Boolean,
    val testsMayAssertBlockedLeakage: Boolean,
    val rawIdentityOutputPresent: Boolean,
    val rawSafeIdOutputPresent: Boolean,
    val rawProviderReferenceOutputPresent: Boolean,
    val rawCryptoReferenceOutputPresent: Boolean,
    val rawStorageReferenceOutputPresent: Boolean,
    val rawBackendReferenceOutputPresent: Boolean,
    val rawEndpointReferenceOutputPresent: Boolean,
    val rawWalletReferenceOutputPresent: Boolean,
    val rawSourceLocationOutputPresent: Boolean,
    val rawFutureApprovalPayloadOutputPresent: Boolean,
    val rawImplementationPayloadOutputPresent: Boolean,
    val rawPrerequisitePayloadOutputPresent: Boolean,
    val rawScopePayloadOutputPresent: Boolean,
    val rawContractPayloadOutputPresent: Boolean,
    val rawReadinessPayloadOutputPresent: Boolean,
    val rawRuntimeLinkagePayloadOutputPresent: Boolean,
    val rawPromotionPayloadOutputPresent: Boolean,
    val rawSourceGuardPayloadOutputPresent: Boolean,
    val rawDiagnosticPayloadOutputPresent: Boolean,
    val secretHashOrFingerprintOutputPresent: Boolean,
    val crashReportOutputPresent: Boolean,
    val analyticsOutputPresent: Boolean,
    val supportExportOutputPresent: Boolean,
    val statuses: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardStatus>,
    val outcomes: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionOutcome>,
    val redactionSurfaceRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionSurfaceRow>,
    val sensitiveReferenceClassRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationSensitiveReferenceClassRow>,
    val allowedOutputClassRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationAllowedOutputClassRow>,
    val forbiddenOutputClassRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenOutputClassRow>,
    val redactionBlockerRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlockerRow>,
    val forbiddenLeakagePathRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenLeakagePathRow>,
    val forbiddenPromotionPathRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardForbiddenPromotionPathRow>,
    val requiredEvidenceRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardRequiredEvidenceRow>,
    val evidenceSourceRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidenceSourceRow>,
    val futureReviewRows:
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardFutureReviewRow>,
    val blockers: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker>,
    val redactionClasses: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardRedactionClass>,
    val capabilities: SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardCapabilities,
)

object SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardPolicy {
    const val POLICY_ID: String =
        "skald-vault-v1-test-only-provider-identity-implementation-redaction-guard-v1"
    const val POLICY_VERSION: Int = 1

    fun currentRedactionGuardEvidence():
        SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidence =
        evaluateRedactionGuard()

    fun evaluateRedactionGuard(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardRequest =
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardRequest(),
    ): SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidence {
        val blockers = baseBlockers() + requestBlockers(request)
        return SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidence(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            redactionGuardModeled = true,
            stillDisabled = true,
            safeOutputsOnly = true,
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
            priorRedactionBoundaryEvidenceIncluded = request.includePriorRedactionBoundaryEvidence,
            redactionSurfacesModeled = true,
            sensitiveReferenceClassesModeled = true,
            allowedOutputClassesModeled = true,
            forbiddenOutputClassesModeled = true,
            forbiddenLeakagePathsModeled = true,
            docsMayDescribeRedaction = true,
            testsMayAssertBlockedLeakage = true,
            rawIdentityOutputPresent = false,
            rawSafeIdOutputPresent = false,
            rawProviderReferenceOutputPresent = false,
            rawCryptoReferenceOutputPresent = false,
            rawStorageReferenceOutputPresent = false,
            rawBackendReferenceOutputPresent = false,
            rawEndpointReferenceOutputPresent = false,
            rawWalletReferenceOutputPresent = false,
            rawSourceLocationOutputPresent = false,
            rawFutureApprovalPayloadOutputPresent = false,
            rawImplementationPayloadOutputPresent = false,
            rawPrerequisitePayloadOutputPresent = false,
            rawScopePayloadOutputPresent = false,
            rawContractPayloadOutputPresent = false,
            rawReadinessPayloadOutputPresent = false,
            rawRuntimeLinkagePayloadOutputPresent = false,
            rawPromotionPayloadOutputPresent = false,
            rawSourceGuardPayloadOutputPresent = false,
            rawDiagnosticPayloadOutputPresent = false,
            secretHashOrFingerprintOutputPresent = false,
            crashReportOutputPresent = false,
            analyticsOutputPresent = false,
            supportExportOutputPresent = false,
            statuses = SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardStatus.entries.toSet(),
            outcomes = SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionOutcome.entries.toSet(),
            redactionSurfaceRows = currentRedactionSurfaceRows(),
            sensitiveReferenceClassRows = currentSensitiveReferenceClassRows(),
            allowedOutputClassRows = currentAllowedOutputClassRows(),
            forbiddenOutputClassRows = currentForbiddenOutputClassRows(),
            redactionBlockerRows = currentRedactionBlockerRows(blockers),
            forbiddenLeakagePathRows = currentForbiddenLeakagePathRows(),
            forbiddenPromotionPathRows = currentForbiddenPromotionPathRows(),
            requiredEvidenceRows = currentRequiredEvidenceRows(request),
            evidenceSourceRows = currentEvidenceSourceRows(request),
            futureReviewRows = currentFutureReviewRows(),
            blockers = blockers,
            redactionClasses =
                SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardRedactionClass.entries.toSet(),
            capabilities =
                SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardCapabilities.Current,
        )
    }

    private fun currentRedactionSurfaceRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionSurfaceRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionSurface.entries.map { surface ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionSurfaceRow(
                surface = surface,
                modeled = true,
                safeOutputOnly = true,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentSensitiveReferenceClassRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationSensitiveReferenceClassRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationSensitiveReferenceClass.entries.map { referenceClass ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationSensitiveReferenceClassRow(
                referenceClass = referenceClass,
                modeled = true,
                rawOutputAllowed = false,
                leakagePresent = false,
                label = redactedLabel(),
            )
        }

    private fun currentAllowedOutputClassRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationAllowedOutputClassRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationAllowedOutputClass.entries.map { outputClass ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationAllowedOutputClassRow(
                outputClass = outputClass,
                modeled = true,
                safeForCurrentEvidence = true,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentForbiddenOutputClassRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenOutputClassRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenOutputClass.entries.map { outputClass ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenOutputClassRow(
                outputClass = outputClass,
                modeled = true,
                present = false,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentRedactionBlockerRows(
        blockers: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker>,
    ): List<SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlockerRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker.entries.map { blocker ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlockerRow(
                blocker = blocker,
                active = blocker in blockers,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentForbiddenLeakagePathRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenLeakagePathRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenLeakagePath.entries.map { leakagePath ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationForbiddenLeakagePathRow(
                leakagePath = leakagePath,
                forbidden = true,
                leakagePresent = false,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentForbiddenPromotionPathRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardForbiddenPromotionPathRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardForbiddenPromotionPath.entries
            .map { promotionRoute ->
                SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardForbiddenPromotionPathRow(
                    promotionRoute = promotionRoute,
                    forbidden = true,
                    authorizesImplementation = false,
                    label = redactedLabel(),
                )
            }

    private fun currentRequiredEvidenceRows(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardRequest,
    ): List<SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardRequiredEvidenceRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardRequiredEvidenceClass.entries
            .map { evidenceClass ->
                val presentAsModelEvidence = when (evidenceClass) {
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardRequiredEvidenceClass
                        .IdentityDecisionEvidence ->
                        request.includePriorIdentityDecisionEvidence
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardRequiredEvidenceClass
                        .IdentityIsolationEvidence ->
                        request.includePriorIsolationEvidence
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardRequiredEvidenceClass
                        .SyntheticNamespaceEvidence ->
                        request.includePriorNamespaceEvidence
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardRequiredEvidenceClass
                        .SourceSetConfinementEvidence ->
                        request.includePriorSourceSetConfinementEvidence
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardRequiredEvidenceClass
                        .ImplementationDecisionEvidence ->
                        request.includePriorImplementationDecisionEvidence
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardRequiredEvidenceClass
                        .PrerequisiteAuditEvidence ->
                        request.includePriorPrerequisiteAuditEvidence
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardRequiredEvidenceClass
                        .ScopeDecisionEvidence ->
                        request.includePriorScopeDecisionEvidence
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardRequiredEvidenceClass
                        .ImplementationContractEvidence ->
                        request.includePriorImplementationContractEvidence
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardRequiredEvidenceClass
                        .ReadinessGateEvidence ->
                        request.includePriorReadinessGateEvidence
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardRequiredEvidenceClass
                        .RuntimeLinkageGuardEvidence ->
                        request.includePriorRuntimeLinkageGuardEvidence
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardRequiredEvidenceClass
                        .PromotionBlockersEvidence ->
                        request.includePriorPromotionBlockersEvidence
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardRequiredEvidenceClass
                        .SourceGuardCoverageEvidence ->
                        request.includePriorSourceGuardCoverageEvidence
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardRequiredEvidenceClass
                        .VaultRedactionBoundaryEvidence ->
                        request.includePriorRedactionBoundaryEvidence
                    else -> true
                }
                SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardRequiredEvidenceRow(
                    evidenceClass = evidenceClass,
                    modeled = true,
                    presentAsModelEvidence = presentAsModelEvidence,
                    futureRequired = !presentAsModelEvidence || evidenceClass in futureReviewEvidenceClasses(),
                    authorizesImplementation = false,
                    label = redactedLabel(),
                )
            }

    private fun currentEvidenceSourceRows(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardRequest,
    ): List<SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidenceSourceRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidenceSource.entries.map { source ->
            val included = when (source) {
                SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidenceSource
                    .TestOnlyProviderIdentityDecision ->
                    request.includePriorIdentityDecisionEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidenceSource
                    .TestOnlyProviderIdentityIsolationGuard ->
                    request.includePriorIsolationEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidenceSource
                    .SyntheticIdentityNamespace ->
                    request.includePriorNamespaceEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidenceSource
                    .TestOnlyProviderIdentitySourceSetConfinement ->
                    request.includePriorSourceSetConfinementEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidenceSource
                    .TestOnlyProviderIdentityImplementationDecision ->
                    request.includePriorImplementationDecisionEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidenceSource
                    .TestOnlyProviderIdentityImplementationPrerequisiteAudit ->
                    request.includePriorPrerequisiteAuditEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidenceSource
                    .TestOnlyProviderIdentityImplementationScopeDecision ->
                    request.includePriorScopeDecisionEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidenceSource
                    .TestOnlyProviderIdentityImplementationContract ->
                    request.includePriorImplementationContractEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidenceSource
                    .TestOnlyProviderIdentityImplementationReadinessGate ->
                    request.includePriorReadinessGateEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidenceSource
                    .TestOnlyProviderIdentityImplementationRuntimeLinkageGuard ->
                    request.includePriorRuntimeLinkageGuardEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidenceSource
                    .TestOnlyProviderIdentityImplementationPromotionBlockers ->
                    request.includePriorPromotionBlockersEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidenceSource
                    .TestOnlyProviderIdentityImplementationSourceGuardCoverage ->
                    request.includePriorSourceGuardCoverageEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidenceSource
                    .VaultRedactionLeakageBoundary ->
                    request.includePriorRedactionBoundaryEvidence
                else -> true
            }
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardEvidenceSourceRow(
                evidenceSource = source,
                included = included,
                modeled = true,
                nonAuthorizing = true,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentFutureReviewRows():
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardFutureReviewRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardFutureReviewRequirement.entries.map {
                requirement ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardFutureReviewRow(
                requirement = requirement,
                futureRequired = true,
                satisfiedNow = false,
                authorizesCurrentImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun futureReviewEvidenceClasses():
        Set<SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardRequiredEvidenceClass> =
        setOf(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardRequiredEvidenceClass
                .SafeOutputEvidence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardRequiredEvidenceClass
                .ForbiddenOutputAbsenceEvidence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardRequiredEvidenceClass
                .LeakageAbsenceEvidence,
        )

    private fun baseBlockers():
        Set<SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker> =
        setOf(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker.RedactionGuardModelOnly,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
                .CurrentRedactionEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker.SafeOutputEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
                .RedactionPassingDoesNotAuthorizeImplementation,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
                .NoTestOnlyProviderIdentityImplementation,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
                .NoProductionProviderIdentityImplementation,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker.NoProviderImplementation,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker.NoProviderFactory,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker.NoProviderDispatcher,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker.NoNonDisabledRegistryEntry,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker.NoExecutorTarget,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker.NoProviderKatExecutor,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
                .ProviderSelectionDisabledProviderOnly,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker.ProductionProviderSelectableFalse,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
                .ProviderOperationAuthorizationBlocked,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker.CryptoExecutionBlocked,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker.VaultLifecycleDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker.PersistenceDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker.ProductionSyncDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker.MainnetDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker.UserConsentCannotOverride,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker.WarningOnlyEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker.TestOnlyEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker.ReleaseEvidenceNonAuthorizing,
        )

    private fun requestBlockers(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionGuardRequest,
    ): Set<SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker> =
        buildSet {
            if (request.userConsentOverrideRequested) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker.UserConsentCannotOverride)
            }
            if (request.warningOnlyEvidenceClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
                        .WarningOnlyEvidenceNonAuthorizing,
                )
            }
            if (request.testOnlyEvidenceClaimedAsProductionPromotion) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
                        .TestOnlyEvidenceNonAuthorizing,
                )
            }
            if (request.releaseEvidenceClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker.ReleaseEvidenceNonAuthorizing)
            }
            if (request.rawIdentityOutputClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker.RawIdentityOutputRejected)
            }
            if (request.rawSafeIdOutputClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker.RawSafeIdOutputRejected)
            }
            if (request.rawProviderReferenceOutputClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
                        .RawProviderReferenceOutputRejected,
                )
            }
            if (request.rawCryptoReferenceOutputClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
                        .RawCryptoReferenceOutputRejected,
                )
            }
            if (request.rawStorageReferenceOutputClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
                        .RawStorageReferenceOutputRejected,
                )
            }
            if (request.rawBackendReferenceOutputClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
                        .RawBackendReferenceOutputRejected,
                )
            }
            if (request.rawEndpointReferenceOutputClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
                        .RawEndpointReferenceOutputRejected,
                )
            }
            if (request.rawWalletReferenceOutputClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
                        .RawWalletReferenceOutputRejected,
                )
            }
            if (request.rawSourceLocationOutputClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
                        .RawSourceLocationOutputRejected,
                )
            }
            if (request.rawFutureApprovalOutputClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
                        .RawFutureApprovalOutputRejected,
                )
            }
            if (request.rawImplementationPayloadOutputClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
                        .RawImplementationPayloadOutputRejected,
                )
            }
            if (request.rawRuntimeLinkagePayloadOutputClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
                        .RawRuntimeLinkagePayloadOutputRejected,
                )
            }
            if (request.rawPromotionPayloadOutputClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
                        .RawPromotionPayloadOutputRejected,
                )
            }
            if (request.rawSourceGuardPayloadOutputClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
                        .RawSourceGuardPayloadOutputRejected,
                )
            }
            if (request.secretHashOrFingerprintOutputClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
                        .SecretHashOrFingerprintOutputRejected,
                )
            }
            if (request.crashReportOutputClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker.CrashReportOutputRejected)
            }
            if (request.analyticsOutputClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker.AnalyticsOutputRejected)
            }
            if (request.supportExportOutputClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker.SupportExportOutputRejected)
            }
            if (request.redactionEvidenceClaimedAsImplementationAuthorization) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
                        .RedactionEvidenceAuthorizationRejected,
                )
            }
            if (request.providerImplementationClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
                        .ProviderImplementationClaimRejected,
                )
            }
            if (request.providerSelectionClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker.ProviderSelectionClaimRejected)
            }
            if (request.productionProviderSelectableClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
                        .ProductionProviderSelectableClaimRejected,
                )
            }
            if (request.promotionClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker.PromotionClaimRejected)
            }
            if (request.mainnetReachabilityClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionBlocker
                        .MainnetReachabilityClaimRejected,
                )
            }
        }

    private fun redactedLabel():
        SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionSafeLabel =
        SkaldVaultV1TestOnlyProviderIdentityImplementationRedactionSafeLabel("redacted")
}
