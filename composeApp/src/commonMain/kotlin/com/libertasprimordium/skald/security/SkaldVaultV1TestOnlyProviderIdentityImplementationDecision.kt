package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionSafeLabel(
    val value: String,
) {
    override fun toString(): String = "RedactedImplementationDecisionLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionStatus {
    ImplementationDecisionModeled,
    StillDisabled,
    CurrentDecisionBlocked,
    PriorIdentityDecisionEvidenceNonAuthorizing,
    PriorIsolationEvidenceNonAuthorizing,
    PriorNamespaceEvidenceNonAuthorizing,
    PriorSourceSetConfinementEvidenceNonAuthorizing,
    NegativeAbsenceEvidenceModeled,
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

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionOutcome {
    CurrentDecisionBlocked,
    FutureBranchReviewRequired,
    ImplementationNotAuthorized,
    ProductionPromotionNotAuthorized,
    MainnetNotAuthorized,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate {
    ExplicitFutureBranchApproval,
    PriorIdentityDecisionEvidenceReviewed,
    PriorIdentityIsolationEvidenceReviewed,
    PriorSyntheticNamespaceEvidenceReviewed,
    PriorSourceSetConfinementEvidenceReviewed,
    SourceSetPlacementApproved,
    SyntheticSafeIdApproved,
    TestOnlyNamespaceApproved,
    NonProductionSourceSetPlacementApproved,
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
    RedactionAndLeakageReviewComplete,
    SourceGuardCoverageComplete,
    FutureTestOnlyImplementationReviewComplete,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker {
    CurrentDecisionBlocked,
    FutureBranchApprovalMissing,
    FutureImplementationReviewIncomplete,
    SourceSetPlacementNotApproved,
    SyntheticSafeIdNotApproved,
    TestOnlyNamespaceNonAuthorizing,
    PriorEvidenceNonAuthorizing,
    NegativeAbsenceEvidenceNonAuthorizing,
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
    SourceSetPlacementApprovalClaimRejected,
    SyntheticSafeIdApprovalClaimRejected,
    ImplementationReviewCompleteClaimRejected,
    ProviderImplementationClaimRejected,
    RegistryEntryClaimRejected,
    FactoryReachabilityRejected,
    DispatcherReachabilityRejected,
    ExecutorTargetRejected,
    ProviderKatExecutorRejected,
    PersistenceReachabilityRejected,
    SettingsReachabilityRejected,
    UiReachabilityRejected,
    MainnetReachabilityRejected,
    MainnetDisabled,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationRequiredEvidenceClass {
    IdentityDecisionEvidence,
    IdentityIsolationEvidence,
    SyntheticNamespaceEvidence,
    SourceSetConfinementEvidence,
    FutureBranchApprovalEvidence,
    SourceSetPlacementApprovalEvidence,
    SyntheticSafeIdApprovalEvidence,
    TestOnlyNamespaceApprovalEvidence,
    ProductionSourceAbsenceEvidence,
    RuntimeReachabilityAbsenceEvidence,
    ProviderSelectionAbsenceEvidence,
    RegistryFactoryDispatcherAbsenceEvidence,
    ExecutorAndKatAbsenceEvidence,
    VaultStorageSyncAbsenceEvidence,
    BackendSettingsUiAbsenceEvidence,
    SigningBroadcastingNetworkAbsenceEvidence,
    RedactionLeakageReviewEvidence,
    SourceGuardCoverageEvidence,
    FutureImplementationReviewEvidence,
    NonAuthorizationEvidence,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationEvidenceSource {
    TestOnlyProviderIdentityDecision,
    TestOnlyProviderIdentityIsolationGuard,
    SyntheticIdentityNamespace,
    TestOnlyProviderIdentitySourceSetConfinement,
    ProviderSelectionBoundary,
    ProviderRegistryIsolationGuard,
    ProviderFactoryIsolationBoundary,
    ProviderOperationDispatchIsolation,
    TestOnlyProviderKatSourceSetConfinement,
    TestOnlyProviderKatExecutorReadinessGate,
    ProviderSelectionPromotionBlockers,
    ProductionProviderAcceptanceContract,
}

enum class SkaldVaultV1TestOnlyProviderIdentityForbiddenImplementationEffect {
    CreatesTestOnlyProviderIdentityImplementation,
    CreatesProductionProviderIdentityImplementation,
    ImplementsVaultCryptoProvider,
    CreatesProviderFactory,
    CreatesProviderDispatcher,
    CreatesProviderRegistryEntry,
    CreatesExecutorTarget,
    CreatesProviderKatExecutor,
    CreatesRunnableExecutorInterface,
    ExecutesProviderOperations,
    ExecutesRuntimeRandomness,
    ExecutesKdf,
    ExecutesHkdf,
    ExecutesHmac,
    ExecutesAead,
    GeneratesKeys,
    StoresKeysets,
    CreatesVault,
    UnlocksVault,
    CreatesVaultSession,
    PersistsVault,
    SucceedsSecureStorage,
    SucceedsSecureMetadataStorage,
    WritesStorageNamespace,
    WritesStoragePath,
    ReadsOrWritesManifest,
    RunsMigration,
    StartsProductionSync,
    CreatesBackendClient,
    CreatesBdkWalletState,
    WritesSettingsCodec,
    ExposesUiSurface,
    SignsTransactions,
    BroadcastsTransactions,
    StartsTorTransport,
    ParsesNostrSecrets,
    AddsPublicEndpointDefault,
    EnablesMainnet,
}

enum class SkaldVaultV1TestOnlyProviderIdentityForbiddenPromotionPath {
    IdentityDecisionEvidenceToImplementation,
    IdentityIsolationEvidenceToImplementation,
    SyntheticNamespaceEvidenceToImplementation,
    SourceSetConfinementEvidenceToImplementation,
    DocumentationEvidenceToImplementation,
    PublicVectorEvidenceToImplementation,
    TestOnlyKatEvidenceToImplementation,
    UserConsentToImplementation,
    WarningOnlyEvidenceToImplementation,
    ReleaseClaimToImplementation,
    FutureReviewLabelToImplementation,
    SafeIdSyntaxAcceptanceToImplementation,
    TestOnlyImplementationToProductionProvider,
    TestOnlyImplementationToProductionProviderSelectable,
    TestOnlyImplementationToVaultPersistence,
    TestOnlyImplementationToProductionSync,
    TestOnlyImplementationToSigning,
    TestOnlyImplementationToBroadcasting,
    TestOnlyImplementationToMainnet,
}

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionRedactionClass {
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

enum class SkaldVaultV1TestOnlyProviderIdentityImplementationFutureReviewRequirement {
    ExplicitFutureBranchApproval,
    TestOnlyProviderIdentityImplementationDesign,
    SourceSetPlacementApproval,
    SyntheticSafeIdApproval,
    TestOnlyNamespaceApproval,
    ProductionSourceAbsenceProof,
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

data class SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGateRow(
    val gate: SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate,
    val modeled: Boolean,
    val reviewedEvidencePresent: Boolean,
    val satisfiedNow: Boolean,
    val negativeAbsenceEvidence: Boolean,
    val implementationAuthorization: Boolean,
    val productionPromotionAuthorization: Boolean,
    val blockers: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker>,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationRequiredEvidenceRow(
    val evidenceClass: SkaldVaultV1TestOnlyProviderIdentityImplementationRequiredEvidenceClass,
    val modeled: Boolean,
    val presentAsModelEvidence: Boolean,
    val futureRequired: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityForbiddenImplementationEffectRow(
    val effect: SkaldVaultV1TestOnlyProviderIdentityForbiddenImplementationEffect,
    val forbidden: Boolean,
    val presentNow: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityForbiddenPromotionPathRow(
    val promotionPath: SkaldVaultV1TestOnlyProviderIdentityForbiddenPromotionPath,
    val forbidden: Boolean,
    val currentBridgePresent: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationEvidenceSourceRow(
    val evidenceSource: SkaldVaultV1TestOnlyProviderIdentityImplementationEvidenceSource,
    val included: Boolean,
    val modeled: Boolean,
    val nonAuthorizing: Boolean,
    val authorizesImplementation: Boolean,
    val authorizesProductionPromotion: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationFutureReviewRow(
    val requirement: SkaldVaultV1TestOnlyProviderIdentityImplementationFutureReviewRequirement,
    val futureRequired: Boolean,
    val satisfiedNow: Boolean,
    val authorizesCurrentImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionCapabilities(
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
        val Current = SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionCapabilities(
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

data class SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionRequest(
    val includePriorIdentityDecisionEvidence: Boolean = true,
    val includePriorIsolationEvidence: Boolean = true,
    val includePriorNamespaceEvidence: Boolean = true,
    val includePriorSourceSetConfinementEvidence: Boolean = true,
    val userConsentOverrideRequested: Boolean = false,
    val warningOnlyEvidenceClaimed: Boolean = false,
    val testOnlyEvidenceClaimedAsProductionPromotion: Boolean = false,
    val releaseEvidenceClaimed: Boolean = false,
    val futureBranchApprovalClaimed: Boolean = false,
    val sourceSetPlacementApprovalClaimed: Boolean = false,
    val syntheticSafeIdApprovalClaimed: Boolean = false,
    val implementationReviewCompleteClaimed: Boolean = false,
    val providerImplementationClaimed: Boolean = false,
    val registryEntryClaimed: Boolean = false,
    val factoryReachabilityClaimed: Boolean = false,
    val dispatcherReachabilityClaimed: Boolean = false,
    val executorTargetClaimed: Boolean = false,
    val providerKatExecutorClaimed: Boolean = false,
    val persistenceReachabilityClaimed: Boolean = false,
    val settingsReachabilityClaimed: Boolean = false,
    val uiReachabilityClaimed: Boolean = false,
    val mainnetReachabilityClaimed: Boolean = false,
) {
    override fun toString(): String = "ImplementationDecisionRequest(redacted=true, claims=modeled)"
}

data class SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionEvidence(
    val policyId: String,
    val policyVersion: Int,
    val implementationDecisionModeled: Boolean,
    val stillDisabled: Boolean,
    val priorIdentityDecisionEvidenceIncluded: Boolean,
    val priorIsolationEvidenceIncluded: Boolean,
    val priorNamespaceEvidenceIncluded: Boolean,
    val priorSourceSetConfinementEvidenceIncluded: Boolean,
    val negativeAbsenceEvidenceModeled: Boolean,
    val negativeAbsenceEvidenceAuthorizesImplementation: Boolean,
    val docsMayDescribeFutureReview: Boolean,
    val testsMayAssertBlockedDecision: Boolean,
    val statuses: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionStatus>,
    val outcomes: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionOutcome>,
    val decisionGateRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGateRow>,
    val requiredEvidenceRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationRequiredEvidenceRow>,
    val forbiddenImplementationEffectRows:
        List<SkaldVaultV1TestOnlyProviderIdentityForbiddenImplementationEffectRow>,
    val forbiddenPromotionPathRows: List<SkaldVaultV1TestOnlyProviderIdentityForbiddenPromotionPathRow>,
    val evidenceSourceRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationEvidenceSourceRow>,
    val futureReviewRows: List<SkaldVaultV1TestOnlyProviderIdentityImplementationFutureReviewRow>,
    val blockers: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker>,
    val redactionClasses: Set<SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionRedactionClass>,
    val capabilities: SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionCapabilities,
)

object SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionPolicy {
    const val POLICY_ID: String = "skald-vault-v1-test-only-provider-identity-implementation-decision-v1"
    const val POLICY_VERSION: Int = 1

    fun currentImplementationDecisionEvidence():
        SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionEvidence =
        evaluateImplementationDecision()

    fun evaluateImplementationDecision(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionRequest =
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionRequest(),
    ): SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionEvidence {
        val blockers = baseBlockers() + requestBlockers(request)
        return SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionEvidence(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            implementationDecisionModeled = true,
            stillDisabled = true,
            priorIdentityDecisionEvidenceIncluded = request.includePriorIdentityDecisionEvidence,
            priorIsolationEvidenceIncluded = request.includePriorIsolationEvidence,
            priorNamespaceEvidenceIncluded = request.includePriorNamespaceEvidence,
            priorSourceSetConfinementEvidenceIncluded = request.includePriorSourceSetConfinementEvidence,
            negativeAbsenceEvidenceModeled = true,
            negativeAbsenceEvidenceAuthorizesImplementation = false,
            docsMayDescribeFutureReview = true,
            testsMayAssertBlockedDecision = true,
            statuses = SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionStatus.entries.toSet(),
            outcomes = SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionOutcome.entries.toSet(),
            decisionGateRows = currentDecisionGateRows(request),
            requiredEvidenceRows = currentRequiredEvidenceRows(request),
            forbiddenImplementationEffectRows = currentForbiddenImplementationEffectRows(),
            forbiddenPromotionPathRows = currentForbiddenPromotionPathRows(),
            evidenceSourceRows = currentEvidenceSourceRows(request),
            futureReviewRows = currentFutureReviewRows(),
            blockers = blockers,
            redactionClasses =
                SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionRedactionClass.entries.toSet(),
            capabilities = SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionCapabilities.Current,
        )
    }

    private fun currentDecisionGateRows(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionRequest,
    ): List<SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGateRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.entries.map { gate ->
            val priorEvidenceSatisfied = when (gate) {
                SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.PriorIdentityDecisionEvidenceReviewed ->
                    request.includePriorIdentityDecisionEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.PriorIdentityIsolationEvidenceReviewed ->
                    request.includePriorIsolationEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.PriorSyntheticNamespaceEvidenceReviewed ->
                    request.includePriorNamespaceEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.PriorSourceSetConfinementEvidenceReviewed ->
                    request.includePriorSourceSetConfinementEvidence
                else -> false
            }
            val negativeAbsenceEvidence = gate in negativeAbsenceGates()
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGateRow(
                gate = gate,
                modeled = true,
                reviewedEvidencePresent = priorEvidenceSatisfied || negativeAbsenceEvidence,
                satisfiedNow = priorEvidenceSatisfied || negativeAbsenceEvidence,
                negativeAbsenceEvidence = negativeAbsenceEvidence,
                implementationAuthorization = false,
                productionPromotionAuthorization = false,
                blockers = gateBlockers(gate),
                label = redactedLabel(),
            )
        }

    private fun currentRequiredEvidenceRows(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionRequest,
    ): List<SkaldVaultV1TestOnlyProviderIdentityImplementationRequiredEvidenceRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationRequiredEvidenceClass.entries.map { evidenceClass ->
            val presentAsModelEvidence = when (evidenceClass) {
                SkaldVaultV1TestOnlyProviderIdentityImplementationRequiredEvidenceClass.IdentityDecisionEvidence ->
                    request.includePriorIdentityDecisionEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationRequiredEvidenceClass.IdentityIsolationEvidence ->
                    request.includePriorIsolationEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationRequiredEvidenceClass.SyntheticNamespaceEvidence ->
                    request.includePriorNamespaceEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationRequiredEvidenceClass.SourceSetConfinementEvidence ->
                    request.includePriorSourceSetConfinementEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationRequiredEvidenceClass.ProductionSourceAbsenceEvidence,
                SkaldVaultV1TestOnlyProviderIdentityImplementationRequiredEvidenceClass.RuntimeReachabilityAbsenceEvidence,
                SkaldVaultV1TestOnlyProviderIdentityImplementationRequiredEvidenceClass.ProviderSelectionAbsenceEvidence,
                SkaldVaultV1TestOnlyProviderIdentityImplementationRequiredEvidenceClass.RegistryFactoryDispatcherAbsenceEvidence,
                SkaldVaultV1TestOnlyProviderIdentityImplementationRequiredEvidenceClass.ExecutorAndKatAbsenceEvidence,
                SkaldVaultV1TestOnlyProviderIdentityImplementationRequiredEvidenceClass.VaultStorageSyncAbsenceEvidence,
                SkaldVaultV1TestOnlyProviderIdentityImplementationRequiredEvidenceClass.BackendSettingsUiAbsenceEvidence,
                SkaldVaultV1TestOnlyProviderIdentityImplementationRequiredEvidenceClass.SigningBroadcastingNetworkAbsenceEvidence,
                SkaldVaultV1TestOnlyProviderIdentityImplementationRequiredEvidenceClass.NonAuthorizationEvidence -> true
                else -> false
            }
            SkaldVaultV1TestOnlyProviderIdentityImplementationRequiredEvidenceRow(
                evidenceClass = evidenceClass,
                modeled = true,
                presentAsModelEvidence = presentAsModelEvidence,
                futureRequired = !presentAsModelEvidence ||
                    evidenceClass in futureApprovalEvidenceClasses(),
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentForbiddenImplementationEffectRows():
        List<SkaldVaultV1TestOnlyProviderIdentityForbiddenImplementationEffectRow> =
        SkaldVaultV1TestOnlyProviderIdentityForbiddenImplementationEffect.entries.map { effect ->
            SkaldVaultV1TestOnlyProviderIdentityForbiddenImplementationEffectRow(
                effect = effect,
                forbidden = true,
                presentNow = false,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentForbiddenPromotionPathRows():
        List<SkaldVaultV1TestOnlyProviderIdentityForbiddenPromotionPathRow> =
        SkaldVaultV1TestOnlyProviderIdentityForbiddenPromotionPath.entries.map { promotionPath ->
            SkaldVaultV1TestOnlyProviderIdentityForbiddenPromotionPathRow(
                promotionPath = promotionPath,
                forbidden = true,
                currentBridgePresent = false,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentEvidenceSourceRows(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionRequest,
    ): List<SkaldVaultV1TestOnlyProviderIdentityImplementationEvidenceSourceRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationEvidenceSource.entries.map { source ->
            val included = when (source) {
                SkaldVaultV1TestOnlyProviderIdentityImplementationEvidenceSource.TestOnlyProviderIdentityDecision ->
                    request.includePriorIdentityDecisionEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationEvidenceSource.TestOnlyProviderIdentityIsolationGuard ->
                    request.includePriorIsolationEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationEvidenceSource.SyntheticIdentityNamespace ->
                    request.includePriorNamespaceEvidence
                SkaldVaultV1TestOnlyProviderIdentityImplementationEvidenceSource.TestOnlyProviderIdentitySourceSetConfinement ->
                    request.includePriorSourceSetConfinementEvidence
                else -> true
            }
            SkaldVaultV1TestOnlyProviderIdentityImplementationEvidenceSourceRow(
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
        List<SkaldVaultV1TestOnlyProviderIdentityImplementationFutureReviewRow> =
        SkaldVaultV1TestOnlyProviderIdentityImplementationFutureReviewRequirement.entries.map { requirement ->
            SkaldVaultV1TestOnlyProviderIdentityImplementationFutureReviewRow(
                requirement = requirement,
                futureRequired = true,
                satisfiedNow = false,
                authorizesCurrentImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun negativeAbsenceGates(): Set<SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate> =
        setOf(
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.ProductionSourceAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.ProviderSelectionAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.RegistryAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.FactoryAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.DispatcherAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.ExecutorTargetAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.ProviderKatExecutorAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.ProviderOperationAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.VaultLifecycleAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.PersistenceAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.SecureStorageAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.SecureMetadataAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.BackendClientAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.BdkWalletStateAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.SettingsCodecAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.UiSurfaceAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.SigningAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.BroadcastingAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.TorAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.NostrAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.PublicEndpointDefaultAbsenceProven,
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.MainnetAbsenceProven,
        )

    private fun futureApprovalEvidenceClasses():
        Set<SkaldVaultV1TestOnlyProviderIdentityImplementationRequiredEvidenceClass> =
        setOf(
            SkaldVaultV1TestOnlyProviderIdentityImplementationRequiredEvidenceClass.FutureBranchApprovalEvidence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRequiredEvidenceClass.SourceSetPlacementApprovalEvidence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRequiredEvidenceClass.SyntheticSafeIdApprovalEvidence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRequiredEvidenceClass.TestOnlyNamespaceApprovalEvidence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRequiredEvidenceClass.RedactionLeakageReviewEvidence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRequiredEvidenceClass.SourceGuardCoverageEvidence,
            SkaldVaultV1TestOnlyProviderIdentityImplementationRequiredEvidenceClass.FutureImplementationReviewEvidence,
        )

    private fun gateBlockers(
        gate: SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate,
    ): Set<SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker> =
        when (gate) {
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.ExplicitFutureBranchApproval ->
                setOf(SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.FutureBranchApprovalMissing)
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.SourceSetPlacementApproved,
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.NonProductionSourceSetPlacementApproved ->
                setOf(SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.SourceSetPlacementNotApproved)
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.SyntheticSafeIdApproved ->
                setOf(SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.SyntheticSafeIdNotApproved)
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.TestOnlyNamespaceApproved ->
                setOf(SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.TestOnlyNamespaceNonAuthorizing)
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.FutureTestOnlyImplementationReviewComplete,
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.RedactionAndLeakageReviewComplete,
            SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionGate.SourceGuardCoverageComplete ->
                setOf(SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.FutureImplementationReviewIncomplete)
            else -> setOf(
                SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.PriorEvidenceNonAuthorizing,
                SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.NegativeAbsenceEvidenceNonAuthorizing,
            )
        }

    private fun baseBlockers(): Set<SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker> =
        setOf(
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.CurrentDecisionBlocked,
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.FutureBranchApprovalMissing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.FutureImplementationReviewIncomplete,
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.SourceSetPlacementNotApproved,
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.SyntheticSafeIdNotApproved,
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.TestOnlyNamespaceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.PriorEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.NegativeAbsenceEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.NoTestOnlyProviderIdentityImplementation,
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.NoProductionProviderIdentityImplementation,
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.NoProviderImplementation,
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.NoProviderFactory,
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.NoProviderDispatcher,
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.NoNonDisabledRegistryEntry,
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.NoExecutorTarget,
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.NoProviderKatExecutor,
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.NoRunnableExecutorInterface,
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.ProviderSelectionDisabledProviderOnly,
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.ProductionProviderSelectableFalse,
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.ProviderOperationAuthorizationBlocked,
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.VaultLifecycleDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.PersistenceDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.ProductionSyncDisabled,
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.UserConsentCannotOverride,
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.WarningOnlyEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.TestOnlyEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.ReleaseEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.MainnetDisabled,
        )

    private fun requestBlockers(
        request: SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionRequest,
    ): Set<SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker> =
        buildSet {
            if (request.userConsentOverrideRequested) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.UserConsentCannotOverride)
            }
            if (request.warningOnlyEvidenceClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.WarningOnlyEvidenceNonAuthorizing)
            }
            if (request.testOnlyEvidenceClaimedAsProductionPromotion) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.TestOnlyEvidenceNonAuthorizing)
            }
            if (request.releaseEvidenceClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.ReleaseEvidenceNonAuthorizing)
            }
            if (request.futureBranchApprovalClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.FutureBranchApprovalClaimRejected)
            }
            if (request.sourceSetPlacementApprovalClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.SourceSetPlacementApprovalClaimRejected)
            }
            if (request.syntheticSafeIdApprovalClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.SyntheticSafeIdApprovalClaimRejected)
            }
            if (request.implementationReviewCompleteClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.ImplementationReviewCompleteClaimRejected)
            }
            if (request.providerImplementationClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.ProviderImplementationClaimRejected)
            }
            if (request.registryEntryClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.RegistryEntryClaimRejected)
            }
            if (request.factoryReachabilityClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.FactoryReachabilityRejected)
            }
            if (request.dispatcherReachabilityClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.DispatcherReachabilityRejected)
            }
            if (request.executorTargetClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.ExecutorTargetRejected)
            }
            if (request.providerKatExecutorClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.ProviderKatExecutorRejected)
            }
            if (request.persistenceReachabilityClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.PersistenceReachabilityRejected)
            }
            if (request.settingsReachabilityClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.SettingsReachabilityRejected)
            }
            if (request.uiReachabilityClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.UiReachabilityRejected)
            }
            if (request.mainnetReachabilityClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityImplementationGateBlocker.MainnetReachabilityRejected)
            }
        }

    private fun redactedLabel(): SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionSafeLabel =
        SkaldVaultV1TestOnlyProviderIdentityImplementationDecisionSafeLabel("redacted")
}
