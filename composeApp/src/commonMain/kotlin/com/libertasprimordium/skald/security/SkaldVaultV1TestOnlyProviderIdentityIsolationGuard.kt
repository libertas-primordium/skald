package com.libertasprimordium.skald.security

data class SkaldVaultV1TestOnlyProviderIdentityIsolationGuardId(
    val value: String,
) {
    init {
        require(value.isNotBlank()) { "identity isolation guard id must not be blank" }
    }

    override fun toString(): String = "SkaldVaultV1TestOnlyProviderIdentityIsolationGuardId(redacted)"
}

data class SkaldVaultV1TestOnlyProviderIdentityIsolationSafeLabel(
    val value: String,
) {
    init {
        require(value.isNotBlank()) { "identity isolation label must not be blank" }
    }

    override fun toString(): String = "SkaldVaultV1TestOnlyProviderIdentityIsolationSafeLabel(redacted)"
}

enum class SkaldVaultV1TestOnlyProviderIdentityIsolationStatus(val label: String) {
    IsolationGuardModeled("isolation guard modeled"),
    StillDisabled("still disabled"),
    IdentityCategoriesIsolated("identity categories isolated"),
    NoTestOnlyProviderImplementation("no test-only provider implementation"),
    NoProductionProviderImplementation("no production provider implementation"),
    NoInstantiableIdentity("no instantiable identity"),
    NoRegistrySelectableIdentity("no registry-selectable identity"),
    NoFactoryReachableIdentity("no factory-reachable identity"),
    NoDispatcherReachableIdentity("no dispatcher-reachable identity"),
    NoExecutorTargetableIdentity("no executor-targetable identity"),
    NoVaultLifecycleReachableIdentity("no vault-lifecycle-reachable identity"),
    NoPersistenceReachableIdentity("no persistence-reachable identity"),
    ProductionProviderForbidden("production provider forbidden"),
    ProviderSelectionAuthorizationBlocked("provider selection authorization blocked"),
    ProductionProviderSelectableFalse("productionProviderSelectable false"),
    ProductionSyncBlocked("production sync blocked"),
    MainnetBlocked("mainnet blocked"),
}

enum class SkaldVaultV1TestOnlyProviderIsolatedIdentityCategory(
    val label: String,
    val futureOnly: Boolean,
    val testOnlyCategory: Boolean,
    val productionCategory: Boolean,
    val unsupported: Boolean,
) {
    FutureTestOnlyDeterministicIdentityIsolated(
        label = "future test-only deterministic identity isolated",
        futureOnly = true,
        testOnlyCategory = true,
        productionCategory = false,
        unsupported = false,
    ),
    FutureTestOnlyRandomizedBehaviorIdentityIsolated(
        label = "future test-only randomized behavior identity isolated",
        futureOnly = true,
        testOnlyCategory = true,
        productionCategory = false,
        unsupported = false,
    ),
    FutureTestOnlyPlatformRuntimeIdentityIsolated(
        label = "future test-only platform runtime identity isolated",
        futureOnly = true,
        testOnlyCategory = true,
        productionCategory = false,
        unsupported = false,
    ),
    FutureProductionCandidateIdentityIsolatedAndNonSelectable(
        label = "future production candidate identity isolated and non-selectable",
        futureOnly = true,
        testOnlyCategory = false,
        productionCategory = true,
        unsupported = false,
    ),
    AndroidWrappingIdentityIsolatedAndFutureOnly(
        label = "Android wrapping identity isolated and future-only",
        futureOnly = true,
        testOnlyCategory = false,
        productionCategory = true,
        unsupported = false,
    ),
    LinuxPassphraseFirstIdentityIsolatedAndFutureOnly(
        label = "Linux credential-first identity isolated and future-only",
        futureOnly = true,
        testOnlyCategory = false,
        productionCategory = true,
        unsupported = false,
    ),
    UnknownIdentityIsolatedAndUnsupported(
        label = "unknown identity isolated and unsupported",
        futureOnly = false,
        testOnlyCategory = false,
        productionCategory = false,
        unsupported = true,
    ),
    RejectedIdentityIsolatedAndUnsupported(
        label = "rejected identity isolated and unsupported",
        futureOnly = false,
        testOnlyCategory = false,
        productionCategory = false,
        unsupported = true,
    ),
}

enum class SkaldVaultV1TestOnlyProviderIdentityIsolationSurface(val label: String) {
    ProviderSelectionRegistrySurface("provider selection registry"),
    ProviderFactorySurface("provider factory"),
    ProviderDispatcherSurface("provider dispatcher"),
    ProviderOperationAuthorizationSurface("provider operation authorization"),
    ProviderKatExecutorSurface("provider KAT executor"),
    ExecutorTargetCatalogSurface("executor target catalog"),
    VaultCreationAuthorizationSurface("vault creation authorization"),
    VaultUnlockAuthorizationSurface("vault unlock authorization"),
    VaultPersistenceReadinessSurface("vault persistence readiness"),
    SecureStorageSurface("secure storage"),
    SecureMetadataStorageSurface("secure metadata storage"),
    ProductionSyncServiceSurface("production sync service"),
    WalletDomainServicesSurface("wallet domain services"),
    BdkAdapterPathsSurface("BDK adapter routes"),
    SettingsCodecsSurface("settings codecs"),
    AppUiSurface("app UI"),
    AndroidProductionSourceSurface("Android production source"),
    LinuxDesktopProductionSourceSurface("Linux desktop production source"),
    CommonProductionSourceSurface("common production source"),
    MainnetPolicySurface("mainnet policy"),
}

enum class SkaldVaultV1TestOnlyProviderIdentityIsolationRule(val label: String) {
    IdentityCategoryIsLabelOnly("identity category is label-only"),
    IdentityCategoryIsNotInstantiable("identity category is not instantiable"),
    IdentityCategoryIsNotRegistrySelectable("identity category is not registry-selectable"),
    IdentityCategoryIsNotFactoryReachable("identity category is not factory-reachable"),
    IdentityCategoryIsNotDispatcherReachable("identity category is not dispatcher-reachable"),
    IdentityCategoryIsNotExecutorTargetable("identity category is not executor-targetable"),
    IdentityCategoryIsNotVaultLifecycleReachable("identity category is not vault-lifecycle-reachable"),
    IdentityCategoryIsNotStorageReachable("identity category is not storage-reachable"),
    IdentityCategoryIsNotSyncReachable("identity category is not sync-reachable"),
    IdentityCategoryIsNotWalletServiceReachable("identity category is not wallet-service-reachable"),
    IdentityCategoryIsNotSettingsPersistent("identity category is not settings-persistent"),
    IdentityCategoryCannotCarryProviderReferences("identity category cannot carry provider references"),
    IdentityCategoryCannotCarryCryptoReferences("identity category cannot carry crypto references"),
    IdentityCategoryCannotCarryByteMaterial("identity category cannot carry byte material"),
    IdentityCategoryCannotAuthorizeProduction("identity category cannot authorize production"),
    IdentityCategoryCannotAuthorizeMainnet("identity category cannot authorize mainnet"),
}

enum class SkaldVaultV1TestOnlyProviderIdentityIsolationEvidenceSource(val label: String) {
    TestOnlyProviderIdentityDecision("test-only provider identity decision"),
    ProviderRegistryIsolationGuard("provider registry isolation guard"),
    ProviderFactoryIsolationBoundary("provider factory isolation boundary"),
    ProviderOperationDispatchIsolation("provider operation dispatch isolation"),
    ProviderKatExecutionIsolation("provider KAT execution isolation"),
    SourceSetConfinementBoundary("source-set confinement boundary"),
    ExecutorReadinessGate("executor readiness gate"),
    ExecutorContract("executor contract"),
    VectorCatalog("vector catalog"),
    ProviderSelectionBoundary("provider selection boundary"),
    ProductionProviderAcceptanceContract("production provider acceptance contract"),
    AuthorizationReadinessMatrix("authorization/readiness matrix"),
    SecureStorageBoundary("secure storage boundary"),
    SecureMetadataBoundary("secure metadata boundary"),
}

enum class SkaldVaultV1TestOnlyProviderIdentityEscapeRisk(val label: String) {
    IdentityBecomesRegistryEntry("identity becomes registry entry"),
    IdentityBecomesFactoryProduct("identity becomes factory product"),
    IdentityBecomesDispatcherTarget("identity becomes dispatcher target"),
    IdentityBecomesExecutorTargetRisk("identity becomes executor target"),
    IdentityBecomesProviderOperationTarget("identity becomes provider operation target"),
    IdentityBecomesVaultCreationDependency("identity becomes vault creation dependency"),
    IdentityBecomesUnlockDependency("identity becomes unlock dependency"),
    IdentityBecomesPersistenceDependency("identity becomes persistence dependency"),
    IdentityBecomesSecureStorageDependency("identity becomes secure storage dependency"),
    IdentityBecomesSecureMetadataDependency("identity becomes secure metadata dependency"),
    IdentityBecomesProductionSyncDependency("identity becomes production sync dependency"),
    IdentityBecomesSettingsState("identity becomes settings state"),
    IdentityBecomesUiState("identity becomes UI state"),
    IdentityReachesBdkAdapter("identity reaches BDK adapter"),
    IdentityReachesMainnetPolicy("identity reaches mainnet policy"),
    IdentityCarriesProviderReference("identity carries provider reference"),
    IdentityCarriesCryptoReference("identity carries crypto reference"),
    IdentityCarriesRawMaterial("identity carries raw material"),
}

enum class SkaldVaultV1TestOnlyProviderIdentityIsolationAuthorizationLimit(val label: String) {
    ProviderImplementation("provider implementation"),
    ProviderFactoryAuthorization("provider factory"),
    ProviderDispatcherAuthorization("provider dispatcher"),
    ProviderRegistryEntry("provider registry entry"),
    ExecutorTargetAuthorization("executor target"),
    ExecutorImplementation("executor implementation"),
    ExecutorExecution("executor execution"),
    ProviderOperationExecution("provider operation execution"),
    ProviderSelection("provider selection"),
    ProductionProviderSelectableTrue("productionProviderSelectable true"),
    VaultCreation("vault creation"),
    VaultUnlock("vault unlock"),
    VaultPersistence("vault persistence"),
    SecureStorageSuccess("secure storage success"),
    SecureMetadataSuccess("secure metadata success"),
    ProductionSync("production sync"),
    Signing("signing"),
    Broadcasting("broadcasting"),
    Mainnet("mainnet"),
}

enum class SkaldVaultV1TestOnlyProviderIdentityIsolationBlocker(val label: String) {
    IdentityIsolationModelOnly("identity isolation model-only"),
    NoTestOnlyProviderIdentityImplemented("no test-only provider identity implemented"),
    NoProductionProviderIdentityImplemented("no production provider identity implemented"),
    NoInstantiableIdentity("no instantiable identity"),
    NoRegistrySelectableIdentity("no registry-selectable identity"),
    NoFactoryReachableIdentity("no factory-reachable identity"),
    NoDispatcherReachableIdentity("no dispatcher-reachable identity"),
    NoExecutorTargetableIdentity("no executor-targetable identity"),
    NoVaultLifecycleReachableIdentity("no vault-lifecycle-reachable identity"),
    NoPersistenceReachableIdentity("no persistence-reachable identity"),
    ProviderImplementationBranchNotAuthorized("provider implementation branch not authorized"),
    ProviderSelectionDisabledProviderOnly("provider selection disabled-provider-only"),
    ProductionProviderSelectableFalse("productionProviderSelectable false"),
    ProviderOperationAuthorizationBlocked("provider operation authorization blocked"),
    RuntimeRandomnessAuthorizationBlocked("runtime randomness authorization blocked"),
    KdfCalibrationNonFinal("KDF calibration non-final"),
    ProductionProviderAcceptanceIncomplete("production provider acceptance incomplete"),
    SecureStorageDisabled("secure storage disabled"),
    SecureMetadataDisabled("secure metadata disabled"),
    VaultLifecycleDisabled("vault lifecycle disabled"),
    PersistenceDisabled("persistence disabled"),
    ProductionSyncDisabled("production sync disabled"),
    TestOnlyEvidenceNonAuthorizing("test-only evidence non-authorizing"),
    WarningOnlyEvidenceNonAuthorizing("warning-only evidence non-authorizing"),
    UserConsentCannotOverride("user consent cannot override"),
    MainnetDisabled("mainnet disabled"),
}

enum class SkaldVaultV1TestOnlyProviderIdentityIsolationRedactionClass(val label: String) {
    PolicyIdsOnly("policy IDs only"),
    IsolationRuleIdsOnly("isolation rule IDs only"),
    IdentityCategoryLabelsOnly("identity category labels only"),
    SurfaceLabelsOnly("surface labels only"),
    EscapeRiskLabelsOnly("escape risk labels only"),
    BlockerLabelsOnly("blocker labels only"),
    SafeLabelsOnly("safe labels only"),
    NoRawMaterial("no raw material"),
    NoProviderReferences("no provider references"),
    NoCryptoReferences("no crypto references"),
    NoLocationReferences("no location references"),
    NoStorageReferences("no storage references"),
    NoBackendReferences("no backend references"),
    NoDiagnosticPayloads("no diagnostic payloads"),
}

enum class SkaldVaultV1TestOnlyProviderIdentityIsolationFutureBranchGuidance(val label: String) {
    ExplicitIdentityImplementationDecision("explicit identity implementation decision"),
    SourceSetPlacementReview("source-set placement review"),
    SyntheticSafeProviderIdReview("synthetic safe provider ID review"),
    RegistryExclusionProof("registry exclusion proof"),
    FactoryExclusionProof("factory exclusion proof"),
    DispatcherExclusionProof("dispatcher exclusion proof"),
    ExecutorTargetExclusionProof("executor target exclusion proof"),
    VaultLifecycleExclusionProof("vault lifecycle exclusion proof"),
    PersistenceExclusionProof("persistence exclusion proof"),
    StorageExclusionProof("storage exclusion proof"),
    SyncExclusionProof("sync exclusion proof"),
    WalletServiceExclusionProof("wallet service exclusion proof"),
    BdkAdapterExclusionProof("BDK adapter exclusion proof"),
    SettingsAndUiExclusionProof("settings and UI exclusion proof"),
    MainnetNonAuthorizationReview("mainnet non-authorization review"),
}

data class SkaldVaultV1TestOnlyProviderIdentityIsolationCapability(
    val isolationGuardModeled: Boolean,
    val identityCategoryIsLabelOnly: Boolean,
    val testOnlyProviderIdentityImplemented: Boolean,
    val productionProviderIdentityImplemented: Boolean,
    val instantiableProviderIdentityAvailable: Boolean,
    val registrySelectableIdentityAvailable: Boolean,
    val factoryReachableIdentityAvailable: Boolean,
    val dispatcherReachableIdentityAvailable: Boolean,
    val executorTargetableIdentityAvailable: Boolean,
    val vaultLifecycleReachableIdentityAvailable: Boolean,
    val persistenceReachableIdentityAvailable: Boolean,
    val canImplementProviderNow: Boolean,
    val canInstantiateProviderNow: Boolean,
    val canRegisterProviderNow: Boolean,
    val canDispatchProviderNow: Boolean,
    val canTargetProviderWithExecutorNow: Boolean,
    val canUseProviderForKatNow: Boolean,
    val canUseProviderForVaultCreation: Boolean,
    val canUseProviderForVaultUnlock: Boolean,
    val canUseProviderForVaultPersistence: Boolean,
    val canAcceptRawMaterial: Boolean,
    val canAcceptProviderHandles: Boolean,
    val canAcceptCryptoObjects: Boolean,
    val canExecuteProviderOperations: Boolean,
    val canExecuteRandomness: Boolean,
    val canExecuteKdf: Boolean,
    val canExecuteAead: Boolean,
    val canExecuteHkdf: Boolean,
    val canExecuteHmac: Boolean,
    val canGenerateKeys: Boolean,
    val canStoreKeysets: Boolean,
    val canAuthorizeProviderSelection: Boolean,
    val canSetProductionProviderSelectable: Boolean,
    val canAuthorizeVaultCreation: Boolean,
    val canAuthorizeVaultUnlock: Boolean,
    val canAuthorizeVaultPersistence: Boolean,
    val canAuthorizeProductionSync: Boolean,
    val canAuthorizeMainnet: Boolean,
) {
    companion object {
        val Current = SkaldVaultV1TestOnlyProviderIdentityIsolationCapability(
            isolationGuardModeled = true,
            identityCategoryIsLabelOnly = true,
            testOnlyProviderIdentityImplemented = false,
            productionProviderIdentityImplemented = false,
            instantiableProviderIdentityAvailable = false,
            registrySelectableIdentityAvailable = false,
            factoryReachableIdentityAvailable = false,
            dispatcherReachableIdentityAvailable = false,
            executorTargetableIdentityAvailable = false,
            vaultLifecycleReachableIdentityAvailable = false,
            persistenceReachableIdentityAvailable = false,
            canImplementProviderNow = false,
            canInstantiateProviderNow = false,
            canRegisterProviderNow = false,
            canDispatchProviderNow = false,
            canTargetProviderWithExecutorNow = false,
            canUseProviderForKatNow = false,
            canUseProviderForVaultCreation = false,
            canUseProviderForVaultUnlock = false,
            canUseProviderForVaultPersistence = false,
            canAcceptRawMaterial = false,
            canAcceptProviderHandles = false,
            canAcceptCryptoObjects = false,
            canExecuteProviderOperations = false,
            canExecuteRandomness = false,
            canExecuteKdf = false,
            canExecuteAead = false,
            canExecuteHkdf = false,
            canExecuteHmac = false,
            canGenerateKeys = false,
            canStoreKeysets = false,
            canAuthorizeProviderSelection = false,
            canSetProductionProviderSelectable = false,
            canAuthorizeVaultCreation = false,
            canAuthorizeVaultUnlock = false,
            canAuthorizeVaultPersistence = false,
            canAuthorizeProductionSync = false,
            canAuthorizeMainnet = false,
        )
    }
}

data class SkaldVaultV1TestOnlyProviderIdentityIsolationGuardRequest(
    val guardId: SkaldVaultV1TestOnlyProviderIdentityIsolationGuardId =
        SkaldVaultV1TestOnlyProviderIdentityIsolationGuardId(
            "skald-vault-v1-test-only-provider-identity-isolation-guard",
        ),
    val includePriorEvidence: Boolean = true,
    val userConsentOverrideRequested: Boolean = false,
    val warningOnlyEvidenceClaimed: Boolean = false,
    val testOnlyEvidenceClaimedAsProductionPromotion: Boolean = false,
    val releaseEvidenceClaimed: Boolean = false,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityIsolationGuardRequest(" +
            "guardId=redacted, " +
            "includePriorEvidence=$includePriorEvidence, " +
            "userConsentOverrideRequested=$userConsentOverrideRequested, " +
            "warningOnlyEvidenceClaimed=$warningOnlyEvidenceClaimed, " +
            "testOnlyEvidenceClaimedAsProductionPromotion=$testOnlyEvidenceClaimedAsProductionPromotion, " +
            "releaseEvidenceClaimed=$releaseEvidenceClaimed, " +
            "rawMaterial=redacted, providerReference=redacted, cryptoReference=redacted, " +
            "locationReference=redacted, storageReference=redacted, backendReference=redacted" +
            ")"
}

data class SkaldVaultV1TestOnlyProviderIdentityCategoryIsolationRow(
    val category: SkaldVaultV1TestOnlyProviderIsolatedIdentityCategory,
    val labelOnly: Boolean,
    val isolated: Boolean,
    val implementedNow: Boolean,
    val instantiable: Boolean,
    val registrySelectable: Boolean,
    val factoryReachable: Boolean,
    val dispatcherReachable: Boolean,
    val executorTargetable: Boolean,
    val vaultLifecycleReachable: Boolean,
    val persistenceReachable: Boolean,
    val authorizesProduction: Boolean,
    val authorizesMainnet: Boolean,
    val blockers: Set<SkaldVaultV1TestOnlyProviderIdentityIsolationBlocker>,
)

data class SkaldVaultV1TestOnlyProviderIdentityIsolationSurfaceRow(
    val surface: SkaldVaultV1TestOnlyProviderIdentityIsolationSurface,
    val isolated: Boolean,
    val currentReachable: Boolean,
    val authorizesIdentityImplementation: Boolean,
    val authorizesProductionSelection: Boolean,
    val blockers: Set<SkaldVaultV1TestOnlyProviderIdentityIsolationBlocker>,
)

data class SkaldVaultV1TestOnlyProviderIdentityIsolationRuleRow(
    val rule: SkaldVaultV1TestOnlyProviderIdentityIsolationRule,
    val modeled: Boolean,
    val satisfiedForModelOnlyBoundary: Boolean,
    val authorizesImplementation: Boolean,
    val blockers: Set<SkaldVaultV1TestOnlyProviderIdentityIsolationBlocker>,
)

data class SkaldVaultV1TestOnlyProviderIdentityIsolationEvidenceSourceRow(
    val evidenceSource: SkaldVaultV1TestOnlyProviderIdentityIsolationEvidenceSource,
    val modeled: Boolean,
    val nonAuthorizing: Boolean,
    val authorizesImplementation: Boolean,
    val authorizesProduction: Boolean,
    val blockers: Set<SkaldVaultV1TestOnlyProviderIdentityIsolationBlocker>,
)

data class SkaldVaultV1TestOnlyProviderIdentityEscapeRiskRow(
    val risk: SkaldVaultV1TestOnlyProviderIdentityEscapeRisk,
    val modeled: Boolean,
    val currentRiskPresent: Boolean,
    val blocked: Boolean,
    val canAuthorize: Boolean,
    val blockers: Set<SkaldVaultV1TestOnlyProviderIdentityIsolationBlocker>,
)

data class SkaldVaultV1TestOnlyProviderIdentityIsolationAuthorizationLimitRow(
    val limit: SkaldVaultV1TestOnlyProviderIdentityIsolationAuthorizationLimit,
    val blocked: Boolean,
    val canAuthorize: Boolean,
    val blockers: Set<SkaldVaultV1TestOnlyProviderIdentityIsolationBlocker>,
)

data class SkaldVaultV1TestOnlyProviderIdentityIsolationFutureBranchGuidanceRow(
    val guidance: SkaldVaultV1TestOnlyProviderIdentityIsolationFutureBranchGuidance,
    val futureRequired: Boolean,
    val authorizesCurrentImplementation: Boolean,
)

data class SkaldVaultV1TestOnlyProviderIdentityIsolationEvidence(
    val policyId: String,
    val policyVersion: Int,
    val statuses: Set<SkaldVaultV1TestOnlyProviderIdentityIsolationStatus>,
    val identityCategoryRows: List<SkaldVaultV1TestOnlyProviderIdentityCategoryIsolationRow>,
    val surfaceRows: List<SkaldVaultV1TestOnlyProviderIdentityIsolationSurfaceRow>,
    val ruleRows: List<SkaldVaultV1TestOnlyProviderIdentityIsolationRuleRow>,
    val evidenceSourceRows: List<SkaldVaultV1TestOnlyProviderIdentityIsolationEvidenceSourceRow>,
    val escapeRiskRows: List<SkaldVaultV1TestOnlyProviderIdentityEscapeRiskRow>,
    val authorizationLimitRows: List<SkaldVaultV1TestOnlyProviderIdentityIsolationAuthorizationLimitRow>,
    val futureBranchGuidanceRows: List<SkaldVaultV1TestOnlyProviderIdentityIsolationFutureBranchGuidanceRow>,
    val blockers: Set<SkaldVaultV1TestOnlyProviderIdentityIsolationBlocker>,
    val redactionClasses: Set<SkaldVaultV1TestOnlyProviderIdentityIsolationRedactionClass>,
    val disabledCapabilities: SkaldVaultV1TestOnlyProviderIdentityIsolationCapability,
    val isolationGuardModeled: Boolean,
    val identityCategoryIsLabelOnly: Boolean,
    val stillDisabled: Boolean,
    val testOnlyProviderIdentityImplemented: Boolean,
    val productionProviderIdentityImplemented: Boolean,
    val instantiableProviderIdentityAvailable: Boolean,
    val registrySelectableIdentityAvailable: Boolean,
    val factoryReachableIdentityAvailable: Boolean,
    val dispatcherReachableIdentityAvailable: Boolean,
    val executorTargetableIdentityAvailable: Boolean,
    val vaultLifecycleReachableIdentityAvailable: Boolean,
    val persistenceReachableIdentityAvailable: Boolean,
    val secureStorageReachable: Boolean,
    val secureMetadataReachable: Boolean,
    val productionSyncReachable: Boolean,
    val walletServicesReachable: Boolean,
    val bdkAdapterReachable: Boolean,
    val settingsCodecsReachable: Boolean,
    val appUiReachable: Boolean,
    val providerSelectionDisabledProviderOnly: Boolean,
    val productionProviderSelectable: Boolean,
    val vaultLifecycleBlocked: Boolean,
    val persistenceBlocked: Boolean,
    val productionSyncBlocked: Boolean,
    val mainnetBlocked: Boolean,
) {
    val modeledButStillDisabled: Boolean =
        isolationGuardModeled &&
            stillDisabled &&
            SkaldVaultV1TestOnlyProviderIdentityIsolationStatus.IsolationGuardModeled in statuses &&
            SkaldVaultV1TestOnlyProviderIdentityIsolationStatus.StillDisabled in statuses
}

object SkaldVaultV1TestOnlyProviderIdentityIsolationGuardPolicy {
    const val POLICY_ID: String = "skald-vault-v1-test-only-provider-identity-isolation-guard-v1"
    const val POLICY_VERSION: Int = 1

    fun evaluateIsolation(
        request: SkaldVaultV1TestOnlyProviderIdentityIsolationGuardRequest =
            SkaldVaultV1TestOnlyProviderIdentityIsolationGuardRequest(),
    ): SkaldVaultV1TestOnlyProviderIdentityIsolationEvidence {
        val requestBlockers = buildSet {
            if (request.userConsentOverrideRequested) {
                add(SkaldVaultV1TestOnlyProviderIdentityIsolationBlocker.UserConsentCannotOverride)
            }
            if (request.warningOnlyEvidenceClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityIsolationBlocker.WarningOnlyEvidenceNonAuthorizing)
            }
            if (request.testOnlyEvidenceClaimedAsProductionPromotion) {
                add(SkaldVaultV1TestOnlyProviderIdentityIsolationBlocker.TestOnlyEvidenceNonAuthorizing)
            }
            if (request.releaseEvidenceClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityIsolationBlocker.ProductionProviderAcceptanceIncomplete)
            }
        }

        return SkaldVaultV1TestOnlyProviderIdentityIsolationEvidence(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            statuses = currentStatuses(),
            identityCategoryRows = currentIdentityCategoryRows(),
            surfaceRows = currentSurfaceRows(),
            ruleRows = currentRuleRows(),
            evidenceSourceRows = currentEvidenceSourceRows(),
            escapeRiskRows = currentEscapeRiskRows(),
            authorizationLimitRows = currentAuthorizationLimitRows(),
            futureBranchGuidanceRows = currentFutureBranchGuidanceRows(),
            blockers = currentBlockers() + requestBlockers,
            redactionClasses = currentRedactionClasses(),
            disabledCapabilities = SkaldVaultV1TestOnlyProviderIdentityIsolationCapability.Current,
            isolationGuardModeled = true,
            identityCategoryIsLabelOnly = true,
            stillDisabled = true,
            testOnlyProviderIdentityImplemented = false,
            productionProviderIdentityImplemented = false,
            instantiableProviderIdentityAvailable = false,
            registrySelectableIdentityAvailable = false,
            factoryReachableIdentityAvailable = false,
            dispatcherReachableIdentityAvailable = false,
            executorTargetableIdentityAvailable = false,
            vaultLifecycleReachableIdentityAvailable = false,
            persistenceReachableIdentityAvailable = false,
            secureStorageReachable = false,
            secureMetadataReachable = false,
            productionSyncReachable = false,
            walletServicesReachable = false,
            bdkAdapterReachable = false,
            settingsCodecsReachable = false,
            appUiReachable = false,
            providerSelectionDisabledProviderOnly = true,
            productionProviderSelectable = false,
            vaultLifecycleBlocked = true,
            persistenceBlocked = true,
            productionSyncBlocked = true,
            mainnetBlocked = true,
        )
    }

    fun currentIsolationEvidence(): SkaldVaultV1TestOnlyProviderIdentityIsolationEvidence =
        evaluateIsolation()

    fun currentPolicySummary(): SkaldVaultV1TestOnlyProviderIdentityIsolationSafeLabel =
        SkaldVaultV1TestOnlyProviderIdentityIsolationSafeLabel(
            "Model-only identity isolation guard; identities remain label-only and unreachable.",
        )

    fun currentStatuses(): Set<SkaldVaultV1TestOnlyProviderIdentityIsolationStatus> =
        SkaldVaultV1TestOnlyProviderIdentityIsolationStatus.entries.toSet()

    fun currentBlockers(): Set<SkaldVaultV1TestOnlyProviderIdentityIsolationBlocker> =
        SkaldVaultV1TestOnlyProviderIdentityIsolationBlocker.entries.toSet()

    fun currentRedactionClasses(): Set<SkaldVaultV1TestOnlyProviderIdentityIsolationRedactionClass> =
        SkaldVaultV1TestOnlyProviderIdentityIsolationRedactionClass.entries.toSet()

    fun currentIdentityCategoryRows(): List<SkaldVaultV1TestOnlyProviderIdentityCategoryIsolationRow> =
        SkaldVaultV1TestOnlyProviderIsolatedIdentityCategory.entries.map { category ->
            SkaldVaultV1TestOnlyProviderIdentityCategoryIsolationRow(
                category = category,
                labelOnly = true,
                isolated = true,
                implementedNow = false,
                instantiable = false,
                registrySelectable = false,
                factoryReachable = false,
                dispatcherReachable = false,
                executorTargetable = false,
                vaultLifecycleReachable = false,
                persistenceReachable = false,
                authorizesProduction = false,
                authorizesMainnet = false,
                blockers = currentBlockers(),
            )
        }

    fun currentSurfaceRows(): List<SkaldVaultV1TestOnlyProviderIdentityIsolationSurfaceRow> =
        SkaldVaultV1TestOnlyProviderIdentityIsolationSurface.entries.map { surface ->
            SkaldVaultV1TestOnlyProviderIdentityIsolationSurfaceRow(
                surface = surface,
                isolated = true,
                currentReachable = false,
                authorizesIdentityImplementation = false,
                authorizesProductionSelection = false,
                blockers = currentBlockers(),
            )
        }

    fun currentRuleRows(): List<SkaldVaultV1TestOnlyProviderIdentityIsolationRuleRow> =
        SkaldVaultV1TestOnlyProviderIdentityIsolationRule.entries.map { rule ->
            SkaldVaultV1TestOnlyProviderIdentityIsolationRuleRow(
                rule = rule,
                modeled = true,
                satisfiedForModelOnlyBoundary = true,
                authorizesImplementation = false,
                blockers = currentBlockers(),
            )
        }

    fun currentEvidenceSourceRows(): List<SkaldVaultV1TestOnlyProviderIdentityIsolationEvidenceSourceRow> =
        SkaldVaultV1TestOnlyProviderIdentityIsolationEvidenceSource.entries.map { source ->
            SkaldVaultV1TestOnlyProviderIdentityIsolationEvidenceSourceRow(
                evidenceSource = source,
                modeled = true,
                nonAuthorizing = true,
                authorizesImplementation = false,
                authorizesProduction = false,
                blockers = currentBlockers(),
            )
        }

    fun currentEscapeRiskRows(): List<SkaldVaultV1TestOnlyProviderIdentityEscapeRiskRow> =
        SkaldVaultV1TestOnlyProviderIdentityEscapeRisk.entries.map { risk ->
            SkaldVaultV1TestOnlyProviderIdentityEscapeRiskRow(
                risk = risk,
                modeled = true,
                currentRiskPresent = false,
                blocked = true,
                canAuthorize = false,
                blockers = currentBlockers(),
            )
        }

    fun currentAuthorizationLimitRows():
        List<SkaldVaultV1TestOnlyProviderIdentityIsolationAuthorizationLimitRow> =
        SkaldVaultV1TestOnlyProviderIdentityIsolationAuthorizationLimit.entries.map { limit ->
            SkaldVaultV1TestOnlyProviderIdentityIsolationAuthorizationLimitRow(
                limit = limit,
                blocked = true,
                canAuthorize = false,
                blockers = currentBlockers(),
            )
        }

    fun currentFutureBranchGuidanceRows():
        List<SkaldVaultV1TestOnlyProviderIdentityIsolationFutureBranchGuidanceRow> =
        SkaldVaultV1TestOnlyProviderIdentityIsolationFutureBranchGuidance.entries.map { guidance ->
            SkaldVaultV1TestOnlyProviderIdentityIsolationFutureBranchGuidanceRow(
                guidance = guidance,
                futureRequired = true,
                authorizesCurrentImplementation = false,
            )
        }
}
