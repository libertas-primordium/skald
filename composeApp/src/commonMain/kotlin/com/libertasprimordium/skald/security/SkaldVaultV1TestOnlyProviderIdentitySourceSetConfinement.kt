package com.libertasprimordium.skald.security

@JvmInline
value class SkaldVaultV1TestOnlyProviderIdentitySourceSetSafeLabel(
    val value: String,
) {
    override fun toString(): String = "RedactedConfinementLabel"
}

enum class SkaldVaultV1TestOnlyProviderIdentitySourceSetStatus {
    SourceSetConfinementModeled,
    StillDisabled,
    CommonMainModelOnlyPolicyAllowed,
    ProductionSourceSetsForbidden,
    TestSourceSetsFutureReviewOnly,
    DocumentationOnlyBlockedPlacement,
    NoTestOnlyProviderIdentityImplementation,
    NoProductionProviderIdentityImplementation,
    NoRegistryKey,
    NoFactoryInput,
    NoDispatcherInput,
    NoExecutorTarget,
    ProviderSelectionAuthorizationBlocked,
    ProductionProviderSelectableFalse,
    VaultLifecycleBlocked,
    PersistenceBlocked,
    ProductionSyncBlocked,
    MainnetBlocked,
}

enum class SkaldVaultV1TestOnlyProviderIdentitySourceSetCategory {
    CommonMainModelPolicy,
    AndroidMainProductionSource,
    DesktopMainProductionSource,
    CommonTestSource,
    DesktopTestSource,
    AndroidInstrumentedTestSource,
    BuildScriptSource,
    DocumentationSource,
}

enum class SkaldVaultV1TestOnlyProviderIdentitySourceSetRole {
    ModelOnlyPolicySource,
    ProductionRuntimeSource,
    TestOnlySource,
    InstrumentedTestOnlySource,
    BuildConfigurationSource,
    DocumentationOnlySource,
}

enum class SkaldVaultV1TestOnlyProviderIdentityPlacementRule {
    CommonMainMayContainModelOnlyPolicyEvidence,
    CommonMainMustNotContainTestOnlyIdentityImplementation,
    AndroidMainMustNotContainTestOnlyIdentityImplementation,
    DesktopMainMustNotContainTestOnlyIdentityImplementation,
    ProductionSourcesMustNotContainSyntheticIdentityAliases,
    ProductionSourcesMustNotContainProviderIdentityFixtures,
    TestSourcesMayOnlyContainFutureReviewedTestOnlyIdentityImplementation,
    TestSourcesDoNotAuthorizeProductionReachability,
    DocumentationMayOnlyDescribeBlockedPlacement,
    BuildScriptsMustNotActivateProviderIdentityDependencies,
    SourceSetEvidenceMustNotAuthorizeProviderSelection,
    SourceSetEvidenceMustNotAuthorizeExecutorTargeting,
    SourceSetEvidenceMustNotAuthorizeVaultLifecycle,
    SourceSetEvidenceMustNotAuthorizePersistence,
    SourceSetEvidenceMustNotAuthorizeProductionSync,
    SourceSetEvidenceMustNotAuthorizeMainnet,
}

enum class SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker {
    SourceSetConfinementModelOnly,
    NoTestOnlyProviderIdentityImplementation,
    NoProductionProviderIdentityImplementation,
    ProviderImplementationBranchNotAuthorized,
    NoProviderImplementation,
    NoProviderFactory,
    NoProviderDispatcher,
    NoNonDisabledRegistryEntry,
    NoExecutorTarget,
    ProviderSelectionDisabledProviderOnly,
    ProductionProviderSelectableFalse,
    ProviderOperationAuthorizationBlocked,
    RuntimeRandomnessAuthorizationBlocked,
    KdfCalibrationNonFinal,
    SecureStorageDisabled,
    SecureMetadataDisabled,
    VaultLifecycleDisabled,
    PersistenceDisabled,
    ProductionSyncDisabled,
    TestOnlyEvidenceNonAuthorizing,
    NamespaceLabelEvidenceNonAuthorizing,
    WarningOnlyEvidenceNonAuthorizing,
    UserConsentCannotOverride,
    MainnetDisabled,
    CommonMainImplementationClaimRejected,
    AndroidMainImplementationClaimRejected,
    DesktopMainImplementationClaimRejected,
    ProductionSourceAliasRejected,
    RegistryReachabilityRejected,
    FactoryReachabilityRejected,
    DispatcherReachabilityRejected,
    ExecutorTargetReachabilityRejected,
    PersistenceReachabilityRejected,
    SettingsReachabilityRejected,
    UiReachabilityRejected,
    MainnetReachabilityRejected,
}

enum class SkaldVaultV1TestOnlyProviderIdentityForbiddenPlacement {
    TestOnlyIdentityImplementationInCommonMain,
    TestOnlyIdentityImplementationInAndroidMain,
    TestOnlyIdentityImplementationInDesktopMain,
    TestOnlyIdentityImplementationInProviderSelectionSource,
    TestOnlyIdentityImplementationInProviderRegistrySource,
    TestOnlyIdentityImplementationInProviderFactorySource,
    TestOnlyIdentityImplementationInProviderDispatcherSource,
    TestOnlyIdentityImplementationInExecutorTargetSource,
    TestOnlyIdentityImplementationInProviderOperationSource,
    TestOnlyIdentityImplementationInVaultLifecycleSource,
    TestOnlyIdentityImplementationInPersistenceSource,
    TestOnlyIdentityImplementationInSecureStorageSource,
    TestOnlyIdentityImplementationInSecureMetadataSource,
    TestOnlyIdentityImplementationInBackendClientSource,
    TestOnlyIdentityImplementationInBdkSource,
    TestOnlyIdentityImplementationInSettingsCodecSource,
    TestOnlyIdentityImplementationInUiSource,
    TestOnlyIdentityImplementationInSigningSource,
    TestOnlyIdentityImplementationInBroadcastingSource,
    TestOnlyIdentityImplementationInTorSource,
    TestOnlyIdentityImplementationInNostrSource,
    TestOnlyIdentityImplementationInPublicEndpointDefaultSource,
    TestOnlyIdentityImplementationInMainnetSource,
}

enum class SkaldVaultV1TestOnlyProviderIdentityForbiddenReachability {
    ProductionProviderSelectionReachability,
    ProductionRegistryReachability,
    ProviderFactoryReachability,
    ProviderDispatcherReachability,
    ExecutorTargetReachability,
    ProviderKatExecutorReachability,
    ProviderOperationReachability,
    RuntimeRandomnessReachability,
    KdfReachability,
    HkdfReachability,
    HmacReachability,
    AeadReachability,
    KeyGenerationReachability,
    KeysetStorageReachability,
    VaultCreationReachability,
    VaultUnlockReachability,
    VaultSessionReachability,
    VaultPersistenceReachability,
    SecureStorageSuccessReachability,
    SecureMetadataSuccessReachability,
    StorageNamespaceReachability,
    StoragePathReachability,
    ProductionSyncReachability,
    BackendClientReachability,
    BdkWalletStateReachability,
    SettingsCodecReachability,
    UiSurfaceReachability,
    SigningReachability,
    BroadcastingReachability,
    TorTransportReachability,
    NostrParsingReachability,
    PublicEndpointDefaultReachability,
    MainnetReachability,
}

enum class SkaldVaultV1TestOnlyProviderIdentitySourceSetEvidenceSource {
    TestOnlyProviderIdentityDecision,
    TestOnlyProviderIdentityIsolationGuard,
    SyntheticIdentityNamespace,
    TestOnlyProviderKatSourceSetConfinement,
    TestOnlyProviderKatExecutorReadinessGate,
    ProviderSelectionBoundary,
    ProviderRegistryIsolationGuard,
    ProviderFactoryIsolationBoundary,
    ProviderOperationDispatchIsolation,
    ProductionProviderAcceptanceContract,
}

enum class SkaldVaultV1TestOnlyProviderIdentitySourceSetRedactionClass {
    RedactedSourceSetLabel,
    RedactedPlacementClaim,
    RedactedIdentityLabel,
    RedactedProviderReference,
    RedactedStorageReference,
    RedactedBackendReference,
    RedactedEndpointReference,
    RedactedWalletReference,
    RedactedCryptoReference,
    SafePolicyIdOnly,
}

enum class SkaldVaultV1TestOnlyProviderIdentitySourceSetFutureReviewRequirement {
    ExplicitTestOnlyIdentityImplementationBranch,
    SourceSetSpecificImplementationReview,
    TestOnlyIdentityMustRemainAbsentFromProductionSources,
    RegistryFactoryDispatcherIsolationReview,
    ExecutorTargetIsolationReview,
    KatExecutorIsolationReview,
    VaultLifecycleExclusionReview,
    PersistenceExclusionReview,
    SecureStorageExclusionReview,
    SecureMetadataExclusionReview,
    ProductionSyncExclusionReview,
    BackendAndBdkExclusionReview,
    SettingsAndUiExclusionReview,
    SigningBroadcastingTorNostrEndpointExclusionReview,
    MainnetNonAuthorizationReview,
}

data class SkaldVaultV1TestOnlyProviderIdentitySourceSetCategoryRow(
    val category: SkaldVaultV1TestOnlyProviderIdentitySourceSetCategory,
    val role: SkaldVaultV1TestOnlyProviderIdentitySourceSetRole,
    val modeled: Boolean,
    val modelOnlyPolicyAllowed: Boolean,
    val futureReviewOnly: Boolean,
    val productionRuntimeForbidden: Boolean,
    val testOnlyIdentityImplementationPresent: Boolean,
    val authorizesImplementation: Boolean,
    val authorizesProductionReachability: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentitySourceSetSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityPlacementRuleRow(
    val rule: SkaldVaultV1TestOnlyProviderIdentityPlacementRule,
    val modeled: Boolean,
    val satisfiedForModelOnlyBoundary: Boolean,
    val authorizesImplementation: Boolean,
    val authorizesProductionReachability: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentitySourceSetSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityForbiddenPlacementRow(
    val placement: SkaldVaultV1TestOnlyProviderIdentityForbiddenPlacement,
    val forbidden: Boolean,
    val currentPlacementPresent: Boolean,
    val authorizesImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentitySourceSetSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentityForbiddenReachabilityRow(
    val reachability: SkaldVaultV1TestOnlyProviderIdentityForbiddenReachability,
    val forbidden: Boolean,
    val currentReachabilityPresent: Boolean,
    val authorizesProductionReachability: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentitySourceSetSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentitySourceSetEvidenceSourceRow(
    val source: SkaldVaultV1TestOnlyProviderIdentitySourceSetEvidenceSource,
    val included: Boolean,
    val modeled: Boolean,
    val nonAuthorizing: Boolean,
    val authorizesImplementation: Boolean,
    val authorizesProductionReachability: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentitySourceSetSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentitySourceSetFutureReviewRow(
    val requirement: SkaldVaultV1TestOnlyProviderIdentitySourceSetFutureReviewRequirement,
    val futureRequired: Boolean,
    val satisfiedNow: Boolean,
    val authorizesCurrentImplementation: Boolean,
    val label: SkaldVaultV1TestOnlyProviderIdentitySourceSetSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderIdentitySourceSetCapabilities(
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
        val Current = SkaldVaultV1TestOnlyProviderIdentitySourceSetCapabilities(
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

data class SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementRequest(
    val includePriorIdentityDecisionEvidence: Boolean = true,
    val includePriorIsolationEvidence: Boolean = true,
    val includePriorNamespaceEvidence: Boolean = true,
    val userConsentOverrideRequested: Boolean = false,
    val warningOnlyEvidenceClaimed: Boolean = false,
    val testOnlyEvidenceClaimedAsProductionPromotion: Boolean = false,
    val commonMainImplementationClaimed: Boolean = false,
    val androidMainImplementationClaimed: Boolean = false,
    val desktopMainImplementationClaimed: Boolean = false,
    val productionSourceAliasClaimed: Boolean = false,
    val registryReachabilityClaimed: Boolean = false,
    val factoryReachabilityClaimed: Boolean = false,
    val dispatcherReachabilityClaimed: Boolean = false,
    val executorTargetReachabilityClaimed: Boolean = false,
    val persistenceReachabilityClaimed: Boolean = false,
    val settingsReachabilityClaimed: Boolean = false,
    val uiReachabilityClaimed: Boolean = false,
    val mainnetReachabilityClaimed: Boolean = false,
) {
    override fun toString(): String = "ConfinementRequest(redacted=true, claims=modeled)"
}

data class SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementEvidence(
    val policyId: String,
    val policyVersion: Int,
    val sourceSetConfinementModeled: Boolean,
    val stillDisabled: Boolean,
    val commonMainModelOnlyPolicyAllowed: Boolean,
    val docsMayDescribeBlockedPlacement: Boolean,
    val testsMayAssertBlockedPlacement: Boolean,
    val statuses: Set<SkaldVaultV1TestOnlyProviderIdentitySourceSetStatus>,
    val sourceSetCategories: List<SkaldVaultV1TestOnlyProviderIdentitySourceSetCategoryRow>,
    val placementRules: List<SkaldVaultV1TestOnlyProviderIdentityPlacementRuleRow>,
    val forbiddenPlacements: List<SkaldVaultV1TestOnlyProviderIdentityForbiddenPlacementRow>,
    val forbiddenReachabilityPaths: List<SkaldVaultV1TestOnlyProviderIdentityForbiddenReachabilityRow>,
    val evidenceSources: List<SkaldVaultV1TestOnlyProviderIdentitySourceSetEvidenceSourceRow>,
    val futureReviewRequirements: List<SkaldVaultV1TestOnlyProviderIdentitySourceSetFutureReviewRow>,
    val blockers: Set<SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker>,
    val redactionClasses: Set<SkaldVaultV1TestOnlyProviderIdentitySourceSetRedactionClass>,
    val capabilities: SkaldVaultV1TestOnlyProviderIdentitySourceSetCapabilities,
    val priorIdentityDecisionEvidenceIncluded: Boolean,
    val priorIdentityDecisionEvidenceNonAuthorizing: Boolean,
    val priorIsolationEvidenceIncluded: Boolean,
    val priorIsolationEvidenceNonAuthorizing: Boolean,
    val priorNamespaceEvidenceIncluded: Boolean,
    val priorNamespaceEvidenceNonAuthorizing: Boolean,
    val sourceSetEvidenceAuthorizesImplementation: Boolean,
    val sourceSetEvidenceAuthorizesProductionReachability: Boolean,
)

object SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementPolicy {
    const val POLICY_ID: String = "skald-vault-v1-test-only-provider-identity-source-set-confinement-v1"
    const val POLICY_VERSION: Int = 1

    fun currentSourceSetConfinementEvidence():
        SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementEvidence =
        evaluateSourceSetConfinement()

    fun evaluateSourceSetConfinement(
        request: SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementRequest =
            SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementRequest(),
    ): SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementEvidence {
        val requestBlockers = buildSet {
            if (request.userConsentOverrideRequested) {
                add(SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.UserConsentCannotOverride)
            }
            if (request.warningOnlyEvidenceClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.WarningOnlyEvidenceNonAuthorizing)
            }
            if (request.testOnlyEvidenceClaimedAsProductionPromotion) {
                add(SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.TestOnlyEvidenceNonAuthorizing)
            }
            if (request.commonMainImplementationClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.CommonMainImplementationClaimRejected)
            }
            if (request.androidMainImplementationClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.AndroidMainImplementationClaimRejected)
            }
            if (request.desktopMainImplementationClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.DesktopMainImplementationClaimRejected)
            }
            if (request.productionSourceAliasClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.ProductionSourceAliasRejected)
            }
            if (request.registryReachabilityClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.RegistryReachabilityRejected)
            }
            if (request.factoryReachabilityClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.FactoryReachabilityRejected)
            }
            if (request.dispatcherReachabilityClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.DispatcherReachabilityRejected)
            }
            if (request.executorTargetReachabilityClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.ExecutorTargetReachabilityRejected)
            }
            if (request.persistenceReachabilityClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.PersistenceReachabilityRejected)
            }
            if (request.settingsReachabilityClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.SettingsReachabilityRejected)
            }
            if (request.uiReachabilityClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.UiReachabilityRejected)
            }
            if (request.mainnetReachabilityClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.MainnetReachabilityRejected)
            }
        }

        return SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementEvidence(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            sourceSetConfinementModeled = true,
            stillDisabled = true,
            commonMainModelOnlyPolicyAllowed = true,
            docsMayDescribeBlockedPlacement = true,
            testsMayAssertBlockedPlacement = true,
            statuses = SkaldVaultV1TestOnlyProviderIdentitySourceSetStatus.entries.toSet(),
            sourceSetCategories = currentSourceSetCategories(),
            placementRules = currentPlacementRules(),
            forbiddenPlacements = currentForbiddenPlacements(),
            forbiddenReachabilityPaths = currentForbiddenReachabilityPaths(),
            evidenceSources = currentEvidenceSources(request),
            futureReviewRequirements = currentFutureReviewRequirements(),
            blockers = baseBlockers() + requestBlockers,
            redactionClasses = SkaldVaultV1TestOnlyProviderIdentitySourceSetRedactionClass.entries.toSet(),
            capabilities = SkaldVaultV1TestOnlyProviderIdentitySourceSetCapabilities.Current,
            priorIdentityDecisionEvidenceIncluded = request.includePriorIdentityDecisionEvidence,
            priorIdentityDecisionEvidenceNonAuthorizing = true,
            priorIsolationEvidenceIncluded = request.includePriorIsolationEvidence,
            priorIsolationEvidenceNonAuthorizing = true,
            priorNamespaceEvidenceIncluded = request.includePriorNamespaceEvidence,
            priorNamespaceEvidenceNonAuthorizing = true,
            sourceSetEvidenceAuthorizesImplementation = false,
            sourceSetEvidenceAuthorizesProductionReachability = false,
        )
    }

    private fun currentSourceSetCategories():
        List<SkaldVaultV1TestOnlyProviderIdentitySourceSetCategoryRow> =
        listOf(
            sourceSetCategory(
                category = SkaldVaultV1TestOnlyProviderIdentitySourceSetCategory.CommonMainModelPolicy,
                role = SkaldVaultV1TestOnlyProviderIdentitySourceSetRole.ModelOnlyPolicySource,
                modelOnlyPolicyAllowed = true,
                futureReviewOnly = false,
                productionRuntimeForbidden = false,
            ),
            sourceSetCategory(
                category = SkaldVaultV1TestOnlyProviderIdentitySourceSetCategory.AndroidMainProductionSource,
                role = SkaldVaultV1TestOnlyProviderIdentitySourceSetRole.ProductionRuntimeSource,
                modelOnlyPolicyAllowed = false,
                futureReviewOnly = false,
                productionRuntimeForbidden = true,
            ),
            sourceSetCategory(
                category = SkaldVaultV1TestOnlyProviderIdentitySourceSetCategory.DesktopMainProductionSource,
                role = SkaldVaultV1TestOnlyProviderIdentitySourceSetRole.ProductionRuntimeSource,
                modelOnlyPolicyAllowed = false,
                futureReviewOnly = false,
                productionRuntimeForbidden = true,
            ),
            sourceSetCategory(
                category = SkaldVaultV1TestOnlyProviderIdentitySourceSetCategory.CommonTestSource,
                role = SkaldVaultV1TestOnlyProviderIdentitySourceSetRole.TestOnlySource,
                modelOnlyPolicyAllowed = false,
                futureReviewOnly = true,
                productionRuntimeForbidden = false,
            ),
            sourceSetCategory(
                category = SkaldVaultV1TestOnlyProviderIdentitySourceSetCategory.DesktopTestSource,
                role = SkaldVaultV1TestOnlyProviderIdentitySourceSetRole.TestOnlySource,
                modelOnlyPolicyAllowed = false,
                futureReviewOnly = true,
                productionRuntimeForbidden = false,
            ),
            sourceSetCategory(
                category = SkaldVaultV1TestOnlyProviderIdentitySourceSetCategory.AndroidInstrumentedTestSource,
                role = SkaldVaultV1TestOnlyProviderIdentitySourceSetRole.InstrumentedTestOnlySource,
                modelOnlyPolicyAllowed = false,
                futureReviewOnly = true,
                productionRuntimeForbidden = false,
            ),
            sourceSetCategory(
                category = SkaldVaultV1TestOnlyProviderIdentitySourceSetCategory.BuildScriptSource,
                role = SkaldVaultV1TestOnlyProviderIdentitySourceSetRole.BuildConfigurationSource,
                modelOnlyPolicyAllowed = false,
                futureReviewOnly = false,
                productionRuntimeForbidden = true,
            ),
            sourceSetCategory(
                category = SkaldVaultV1TestOnlyProviderIdentitySourceSetCategory.DocumentationSource,
                role = SkaldVaultV1TestOnlyProviderIdentitySourceSetRole.DocumentationOnlySource,
                modelOnlyPolicyAllowed = false,
                futureReviewOnly = false,
                productionRuntimeForbidden = false,
            ),
        )

    private fun sourceSetCategory(
        category: SkaldVaultV1TestOnlyProviderIdentitySourceSetCategory,
        role: SkaldVaultV1TestOnlyProviderIdentitySourceSetRole,
        modelOnlyPolicyAllowed: Boolean,
        futureReviewOnly: Boolean,
        productionRuntimeForbidden: Boolean,
    ): SkaldVaultV1TestOnlyProviderIdentitySourceSetCategoryRow =
        SkaldVaultV1TestOnlyProviderIdentitySourceSetCategoryRow(
            category = category,
            role = role,
            modeled = true,
            modelOnlyPolicyAllowed = modelOnlyPolicyAllowed,
            futureReviewOnly = futureReviewOnly,
            productionRuntimeForbidden = productionRuntimeForbidden,
            testOnlyIdentityImplementationPresent = false,
            authorizesImplementation = false,
            authorizesProductionReachability = false,
            label = redactedLabel(),
        )

    private fun currentPlacementRules():
        List<SkaldVaultV1TestOnlyProviderIdentityPlacementRuleRow> =
        SkaldVaultV1TestOnlyProviderIdentityPlacementRule.entries.map { rule ->
            SkaldVaultV1TestOnlyProviderIdentityPlacementRuleRow(
                rule = rule,
                modeled = true,
                satisfiedForModelOnlyBoundary = true,
                authorizesImplementation = false,
                authorizesProductionReachability = false,
                label = redactedLabel(),
            )
        }

    private fun currentForbiddenPlacements():
        List<SkaldVaultV1TestOnlyProviderIdentityForbiddenPlacementRow> =
        SkaldVaultV1TestOnlyProviderIdentityForbiddenPlacement.entries.map { placement ->
            SkaldVaultV1TestOnlyProviderIdentityForbiddenPlacementRow(
                placement = placement,
                forbidden = true,
                currentPlacementPresent = false,
                authorizesImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun currentForbiddenReachabilityPaths():
        List<SkaldVaultV1TestOnlyProviderIdentityForbiddenReachabilityRow> =
        SkaldVaultV1TestOnlyProviderIdentityForbiddenReachability.entries.map { reachability ->
            SkaldVaultV1TestOnlyProviderIdentityForbiddenReachabilityRow(
                reachability = reachability,
                forbidden = true,
                currentReachabilityPresent = false,
                authorizesProductionReachability = false,
                label = redactedLabel(),
            )
        }

    private fun currentEvidenceSources(
        request: SkaldVaultV1TestOnlyProviderIdentitySourceSetConfinementRequest,
    ): List<SkaldVaultV1TestOnlyProviderIdentitySourceSetEvidenceSourceRow> =
        SkaldVaultV1TestOnlyProviderIdentitySourceSetEvidenceSource.entries.map { source ->
            val included = when (source) {
                SkaldVaultV1TestOnlyProviderIdentitySourceSetEvidenceSource.TestOnlyProviderIdentityDecision ->
                    request.includePriorIdentityDecisionEvidence
                SkaldVaultV1TestOnlyProviderIdentitySourceSetEvidenceSource.TestOnlyProviderIdentityIsolationGuard ->
                    request.includePriorIsolationEvidence
                SkaldVaultV1TestOnlyProviderIdentitySourceSetEvidenceSource.SyntheticIdentityNamespace ->
                    request.includePriorNamespaceEvidence
                else -> true
            }
            SkaldVaultV1TestOnlyProviderIdentitySourceSetEvidenceSourceRow(
                source = source,
                included = included,
                modeled = true,
                nonAuthorizing = true,
                authorizesImplementation = false,
                authorizesProductionReachability = false,
                label = redactedLabel(),
            )
        }

    private fun currentFutureReviewRequirements():
        List<SkaldVaultV1TestOnlyProviderIdentitySourceSetFutureReviewRow> =
        SkaldVaultV1TestOnlyProviderIdentitySourceSetFutureReviewRequirement.entries.map { requirement ->
            SkaldVaultV1TestOnlyProviderIdentitySourceSetFutureReviewRow(
                requirement = requirement,
                futureRequired = true,
                satisfiedNow = false,
                authorizesCurrentImplementation = false,
                label = redactedLabel(),
            )
        }

    private fun baseBlockers(): Set<SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker> =
        setOf(
            SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.SourceSetConfinementModelOnly,
            SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.NoTestOnlyProviderIdentityImplementation,
            SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.NoProductionProviderIdentityImplementation,
            SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.ProviderImplementationBranchNotAuthorized,
            SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.NoProviderImplementation,
            SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.NoProviderFactory,
            SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.NoProviderDispatcher,
            SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.NoNonDisabledRegistryEntry,
            SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.NoExecutorTarget,
            SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.ProviderSelectionDisabledProviderOnly,
            SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.ProductionProviderSelectableFalse,
            SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.ProviderOperationAuthorizationBlocked,
            SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.RuntimeRandomnessAuthorizationBlocked,
            SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.KdfCalibrationNonFinal,
            SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.SecureStorageDisabled,
            SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.SecureMetadataDisabled,
            SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.VaultLifecycleDisabled,
            SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.PersistenceDisabled,
            SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.ProductionSyncDisabled,
            SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.TestOnlyEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.NamespaceLabelEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.WarningOnlyEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.UserConsentCannotOverride,
            SkaldVaultV1TestOnlyProviderIdentityPlacementBlocker.MainnetDisabled,
        )

    private fun redactedLabel(): SkaldVaultV1TestOnlyProviderIdentitySourceSetSafeLabel =
        SkaldVaultV1TestOnlyProviderIdentitySourceSetSafeLabel("redacted")
}
