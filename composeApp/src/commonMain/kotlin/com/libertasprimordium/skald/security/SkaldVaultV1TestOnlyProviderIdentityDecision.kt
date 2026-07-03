package com.libertasprimordium.skald.security

data class SkaldVaultV1TestOnlyProviderIdentityDecisionId(
    val value: String,
) {
    init {
        require(value.isNotBlank()) { "identity decision id must not be blank" }
    }

    override fun toString(): String = "SkaldVaultV1TestOnlyProviderIdentityDecisionId(redacted)"
}

data class SkaldVaultV1TestOnlyProviderIdentitySafeLabel(
    val value: String,
) {
    init {
        require(value.isNotBlank()) { "identity decision label must not be blank" }
    }

    override fun toString(): String = "SkaldVaultV1TestOnlyProviderIdentitySafeLabel(redacted)"
}

enum class SkaldVaultV1TestOnlyProviderIdentityDecisionStatus(val label: String) {
    IdentityDecisionModeled("identity decision modeled"),
    StillDisabled("still disabled"),
    TestOnlyProviderIdentityCategoryDescribed("test-only provider identity category described"),
    NoTestOnlyProviderImplemented("no test-only provider implemented"),
    NoProductionProviderImplemented("no production provider implemented"),
    NoProviderFactory("no provider factory"),
    NoProviderRegistryEntry("no provider registry entry"),
    NoProviderDispatcherEntry("no provider dispatcher entry"),
    NoExecutorTarget("no executor target"),
    ProductionProviderForbidden("production provider forbidden"),
    ProviderSelectionAuthorizationBlocked("provider selection authorization blocked"),
    ProductionProviderSelectableFalse("productionProviderSelectable false"),
    VaultLifecycleBlocked("vault lifecycle blocked"),
    PersistenceBlocked("persistence blocked"),
    ProductionSyncBlocked("production sync blocked"),
    MainnetBlocked("mainnet blocked"),
}

enum class SkaldVaultV1TestOnlyProviderIdentityCategory(
    val label: String,
    val testOnlyIdentity: Boolean,
    val productionIdentity: Boolean,
) {
    DisabledProviderIdentity(
        label = "disabled provider identity",
        testOnlyIdentity = false,
        productionIdentity = false,
    ),
    FutureTestOnlyDeterministicProviderIdentity(
        label = "future test-only deterministic provider identity",
        testOnlyIdentity = true,
        productionIdentity = false,
    ),
    FutureTestOnlyRandomizedBehaviorProviderIdentity(
        label = "future test-only randomized behavior provider identity",
        testOnlyIdentity = true,
        productionIdentity = false,
    ),
    FutureTestOnlyPlatformRuntimeProviderIdentity(
        label = "future test-only platform runtime provider identity",
        testOnlyIdentity = true,
        productionIdentity = false,
    ),
    FutureProductionCandidateProviderIdentity(
        label = "future production candidate provider identity",
        testOnlyIdentity = false,
        productionIdentity = true,
    ),
    FutureAndroidWrappingProviderIdentity(
        label = "future Android wrapping provider identity",
        testOnlyIdentity = false,
        productionIdentity = true,
    ),
    FutureLinuxPassphraseFirstProviderIdentity(
        label = "future Linux credential-first provider identity",
        testOnlyIdentity = false,
        productionIdentity = true,
    ),
    RejectedProductionProviderIdentity(
        label = "rejected production provider identity",
        testOnlyIdentity = false,
        productionIdentity = true,
    ),
    UnsupportedProviderIdentity(
        label = "unsupported provider identity",
        testOnlyIdentity = false,
        productionIdentity = false,
    ),
    UnknownProviderIdentity(
        label = "unknown provider identity",
        testOnlyIdentity = false,
        productionIdentity = false,
    ),
}

enum class SkaldVaultV1TestOnlyProviderIdentityDecisionClass(val label: String) {
    CurrentSelectableIdentityIsDisabledProviderOnly("current selectable identity is disabled provider only"),
    FutureTestOnlyIdentityRequiresExplicitBranchApproval(
        "future test-only identity requires explicit branch approval",
    ),
    FutureTestOnlyIdentityMustNotBeRegistrySelectable(
        "future test-only identity must not be registry-selectable",
    ),
    FutureTestOnlyIdentityMustNotBeProductionSelectable(
        "future test-only identity must not be production-selectable",
    ),
    FutureTestOnlyIdentityMustNotBeUsedForVaultCreation(
        "future test-only identity must not be used for vault creation",
    ),
    FutureTestOnlyIdentityMustNotBeUsedForVaultUnlock(
        "future test-only identity must not be used for vault unlock",
    ),
    FutureTestOnlyIdentityMustNotBeUsedForVaultPersistence(
        "future test-only identity must not be used for vault persistence",
    ),
    FutureTestOnlyIdentityMustNotBeUsedForSecureStorage(
        "future test-only identity must not be used for secure storage",
    ),
    FutureTestOnlyIdentityMustNotBeUsedForSecureMetadataStorage(
        "future test-only identity must not be used for secure metadata storage",
    ),
    FutureTestOnlyIdentityMustNotBeUsedForProductionSync(
        "future test-only identity must not be used for production sync",
    ),
    FutureTestOnlyIdentityMustNotBeUsedForSigning("future test-only identity must not be used for signing"),
    FutureTestOnlyIdentityMustNotBeUsedForBroadcasting(
        "future test-only identity must not be used for broadcasting",
    ),
    FutureTestOnlyIdentityMustNotBeUsedForMainnet("future test-only identity must not be used for mainnet"),
    ProductionCandidateIdentityRemainsNonSelectable("production candidate identity remains non-selectable"),
    UserConsentCannotPromoteIdentity("user consent cannot promote identity"),
    WarningOnlyEvidenceCannotPromoteIdentity("warning-only evidence cannot promote identity"),
    TestOnlyEvidenceCannotPromoteProductionIdentity(
        "test-only evidence cannot promote production identity",
    ),
}

enum class SkaldVaultV1TestOnlyProviderIdentityConstraint(val label: String) {
    SyntheticSafeProviderIdOnly("must use synthetic safe provider ID only"),
    TestOnlyNamespace("must use test-only namespace"),
    ExplicitSourceSetConfinement("must use explicit source-set confinement"),
    AbsentFromProductionSourceSets("must be absent from production source sets"),
    AbsentFromProductionRegistry("must be absent from production registry"),
    AbsentFromProviderFactory("must be absent from provider factory"),
    AbsentFromProviderDispatcher("must be absent from provider dispatcher"),
    NoProductionProviderInterfaceInProductionSource(
        "must not implement production provider interface in production source",
    ),
    NoProviderReferences("must not hold provider references"),
    NoCryptoReferences("must not expose crypto references"),
    NoRawMaterialAcceptance("must not accept raw material"),
    NoStatePersistence("must not persist state"),
    NoVaultRecordCreation("must not create vault records"),
    NoKeysetCreation("must not create keysets"),
    NoFileWrites("must not write files"),
    NoSecureStorageAccess("must not access secure storage"),
    NoSecureMetadataStorageAccess("must not access secure metadata storage"),
    NoWalletMetadataAccess("must not access wallet metadata"),
    NoBackendMetadataAccess("must not access backend metadata"),
    NoProductionAuthorization("must not authorize production"),
}

enum class SkaldVaultV1TestOnlyProviderIdentityForbiddenLinkage(val label: String) {
    ProductionProviderSelection("linked to production provider selection"),
    ProductionRegistry("linked to production registry"),
    ProviderFactoryLinkage("linked to provider factory"),
    ProviderDispatcherLinkage("linked to provider dispatcher"),
    VaultCreation("linked to vault creation"),
    VaultUnlock("linked to vault unlock"),
    VaultPersistence("linked to vault persistence"),
    SecureStorageSuccess("linked to secure storage success"),
    SecureMetadataSuccess("linked to secure metadata success"),
    ProductionSync("linked to production sync"),
    BdkWalletState("linked to BDK wallet state"),
    WalletLabels("linked to wallet labels"),
    UtxoLabels("linked to UTXO labels"),
    BackendObservationState("linked to backend observation state"),
    Mainnet("linked to mainnet"),
}

enum class SkaldVaultV1TestOnlyProviderIdentityEvidenceSource(val label: String) {
    DisabledProviderSelectionEvidence("disabled provider selection evidence"),
    ProviderRegistryIsolationEvidence("provider registry isolation evidence"),
    ProviderFactoryIsolationEvidence("provider factory isolation evidence"),
    ProviderDispatchIsolationEvidence("provider dispatch isolation evidence"),
    TestOnlyKatSourceSetConfinementEvidence("test-only KAT source-set confinement evidence"),
    TestOnlyKatExecutorContractEvidence("test-only KAT executor contract evidence"),
    TestOnlyKatVectorCatalogEvidence("test-only KAT vector catalog evidence"),
    TestOnlyKatReadinessGateEvidence("test-only KAT readiness gate evidence"),
    ProviderExecutableKatDecisionGateEvidence("provider executable KAT decision gate evidence"),
    PrerequisiteAuditEvidence("prerequisite audit evidence"),
    ProductionProviderAcceptanceContractEvidence("production provider acceptance contract evidence"),
}

enum class SkaldVaultV1TestOnlyProviderIdentityAuthorizationLimit(val label: String) {
    ProviderImplementation("provider implementation"),
    ProviderFactoryAuthorization("provider factory"),
    ProviderDispatcherAuthorization("provider dispatcher"),
    ProviderRegistryEntry("provider registry entry"),
    ExecutorTarget("executor target"),
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

enum class SkaldVaultV1TestOnlyProviderIdentityBlocker(val label: String) {
    NoTestOnlyProviderIdentityImplemented("no test-only provider identity implemented"),
    NoProductionProviderIdentityImplemented("no production provider identity implemented"),
    ProviderImplementationBranchNotAuthorized("provider implementation branch not authorized"),
    NoProviderFactory("no provider factory"),
    NoProviderDispatcher("no provider dispatcher"),
    NoNonDisabledRegistryEntry("no non-disabled registry entry"),
    NoExecutorTarget("no executor target"),
    SourceSetConfinementModelOnly("source-set confinement model-only"),
    ExecutorImplementationNotAuthorized("executor implementation not authorized"),
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

enum class SkaldVaultV1TestOnlyProviderIdentityRedactionClass(val label: String) {
    PolicyIdsOnly("policy IDs only"),
    IdentityIdsOnly("identity IDs only"),
    CategoryLabelsOnly("category labels only"),
    DecisionLabelsOnly("decision labels only"),
    ConstraintLabelsOnly("constraint labels only"),
    LinkageLabelsOnly("linkage labels only"),
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

enum class SkaldVaultV1TestOnlyProviderIdentityFutureBranchGuidance(val label: String) {
    TestOnlyIdentityImplementationDecision("test-only identity implementation decision"),
    SourceSetSpecificIdentityPlacementReview("source-set-specific identity placement review"),
    SyntheticSafeProviderIdReview("synthetic safe provider ID review"),
    NonRegistrySelectableIdentityReview("non-registry-selectable identity review"),
    NonFactoryIdentityReview("non-factory identity review"),
    NonDispatcherIdentityReview("non-dispatcher identity review"),
    ExecutorTargetProhibitionReview("executor target prohibition review"),
    ProviderSelectionNonAuthorizationReview("provider selection non-authorization review"),
    VaultLifecycleNonAuthorizationReview("vault lifecycle non-authorization review"),
    ProductionSyncNonAuthorizationReview("production sync non-authorization review"),
    MainnetNonAuthorizationReview("mainnet non-authorization review"),
}

data class SkaldVaultV1TestOnlyProviderIdentityCapability(
    val identityDecisionModeled: Boolean,
    val testOnlyProviderIdentityImplemented: Boolean,
    val productionProviderIdentityImplemented: Boolean,
    val providerFactoryAvailable: Boolean,
    val providerDispatcherAvailable: Boolean,
    val nonDisabledRegistryEntryAvailable: Boolean,
    val executorTargetAvailable: Boolean,
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
        val Current = SkaldVaultV1TestOnlyProviderIdentityCapability(
            identityDecisionModeled = true,
            testOnlyProviderIdentityImplemented = false,
            productionProviderIdentityImplemented = false,
            providerFactoryAvailable = false,
            providerDispatcherAvailable = false,
            nonDisabledRegistryEntryAvailable = false,
            executorTargetAvailable = false,
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

data class SkaldVaultV1TestOnlyProviderIdentityDecisionRequest(
    val decisionId: SkaldVaultV1TestOnlyProviderIdentityDecisionId =
        SkaldVaultV1TestOnlyProviderIdentityDecisionId("skald-vault-v1-test-only-provider-identity-decision"),
    val includePriorEvidence: Boolean = true,
    val userConsentOverrideRequested: Boolean = false,
    val warningOnlyEvidenceClaimed: Boolean = false,
    val testOnlyEvidenceClaimedAsProductionPromotion: Boolean = false,
    val releaseEvidenceClaimed: Boolean = false,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderIdentityDecisionRequest(" +
            "decisionId=redacted, " +
            "includePriorEvidence=$includePriorEvidence, " +
            "userConsentOverrideRequested=$userConsentOverrideRequested, " +
            "warningOnlyEvidenceClaimed=$warningOnlyEvidenceClaimed, " +
            "testOnlyEvidenceClaimedAsProductionPromotion=$testOnlyEvidenceClaimedAsProductionPromotion, " +
            "releaseEvidenceClaimed=$releaseEvidenceClaimed, " +
            "rawMaterial=redacted, providerReference=redacted, cryptoReference=redacted, " +
            "locationReference=redacted, storageReference=redacted, backendReference=redacted" +
            ")"
}

data class SkaldVaultV1TestOnlyProviderIdentityCategoryRow(
    val category: SkaldVaultV1TestOnlyProviderIdentityCategory,
    val modeled: Boolean,
    val implementedNow: Boolean,
    val registrySelectable: Boolean,
    val productionSelectable: Boolean,
    val factoryAvailable: Boolean,
    val dispatcherAvailable: Boolean,
    val executorTargetAvailable: Boolean,
    val canUseForKatNow: Boolean,
    val canUseForVaultCreation: Boolean,
    val canUseForVaultUnlock: Boolean,
    val canUseForVaultPersistence: Boolean,
    val blockers: Set<SkaldVaultV1TestOnlyProviderIdentityBlocker>,
) {
    val authorizesProduction: Boolean = false
}

data class SkaldVaultV1TestOnlyProviderIdentityDecisionClassRow(
    val decisionClass: SkaldVaultV1TestOnlyProviderIdentityDecisionClass,
    val modeled: Boolean,
    val authorizesIdentityPromotion: Boolean,
    val authorizesCurrentImplementation: Boolean,
    val blockers: Set<SkaldVaultV1TestOnlyProviderIdentityBlocker>,
)

data class SkaldVaultV1TestOnlyProviderIdentityConstraintRow(
    val constraint: SkaldVaultV1TestOnlyProviderIdentityConstraint,
    val modeled: Boolean,
    val satisfiedForModelOnlyBoundary: Boolean,
    val futureImplementationReviewRequired: Boolean,
    val authorizesCurrentIdentity: Boolean,
    val blockers: Set<SkaldVaultV1TestOnlyProviderIdentityBlocker>,
)

data class SkaldVaultV1TestOnlyProviderIdentityForbiddenLinkageRow(
    val linkage: SkaldVaultV1TestOnlyProviderIdentityForbiddenLinkage,
    val forbidden: Boolean,
    val currentLinkPresent: Boolean,
    val canAuthorize: Boolean,
    val blockers: Set<SkaldVaultV1TestOnlyProviderIdentityBlocker>,
)

data class SkaldVaultV1TestOnlyProviderIdentityEvidenceSourceRow(
    val evidenceSource: SkaldVaultV1TestOnlyProviderIdentityEvidenceSource,
    val modeled: Boolean,
    val nonAuthorizing: Boolean,
    val authorizesImplementation: Boolean,
    val authorizesProduction: Boolean,
    val blockers: Set<SkaldVaultV1TestOnlyProviderIdentityBlocker>,
)

data class SkaldVaultV1TestOnlyProviderIdentityAuthorizationLimitRow(
    val authorizationLimit: SkaldVaultV1TestOnlyProviderIdentityAuthorizationLimit,
    val blocked: Boolean,
    val canAuthorize: Boolean,
    val blockers: Set<SkaldVaultV1TestOnlyProviderIdentityBlocker>,
)

data class SkaldVaultV1TestOnlyProviderIdentityFutureBranchGuidanceRow(
    val guidance: SkaldVaultV1TestOnlyProviderIdentityFutureBranchGuidance,
    val futureRequired: Boolean,
    val authorizesCurrentImplementation: Boolean,
)

data class SkaldVaultV1TestOnlyProviderIdentityDecisionEvidence(
    val policyId: String,
    val policyVersion: Int,
    val statuses: Set<SkaldVaultV1TestOnlyProviderIdentityDecisionStatus>,
    val identityCategoryRows: List<SkaldVaultV1TestOnlyProviderIdentityCategoryRow>,
    val decisionClassRows: List<SkaldVaultV1TestOnlyProviderIdentityDecisionClassRow>,
    val constraintRows: List<SkaldVaultV1TestOnlyProviderIdentityConstraintRow>,
    val forbiddenLinkageRows: List<SkaldVaultV1TestOnlyProviderIdentityForbiddenLinkageRow>,
    val evidenceSourceRows: List<SkaldVaultV1TestOnlyProviderIdentityEvidenceSourceRow>,
    val authorizationLimitRows: List<SkaldVaultV1TestOnlyProviderIdentityAuthorizationLimitRow>,
    val futureBranchGuidanceRows: List<SkaldVaultV1TestOnlyProviderIdentityFutureBranchGuidanceRow>,
    val blockers: Set<SkaldVaultV1TestOnlyProviderIdentityBlocker>,
    val redactionClasses: Set<SkaldVaultV1TestOnlyProviderIdentityRedactionClass>,
    val disabledCapabilities: SkaldVaultV1TestOnlyProviderIdentityCapability,
    val futureTestOnlyIdentityAllowedInPrinciple: Boolean,
    val testOnlyProviderIdentityImplemented: Boolean,
    val productionProviderIdentityImplemented: Boolean,
    val currentSelectableIdentityDisabledProviderOnly: Boolean,
    val productionProviderSelectable: Boolean,
    val vaultLifecycleBlocked: Boolean,
    val persistenceBlocked: Boolean,
    val productionSyncBlocked: Boolean,
    val mainnetBlocked: Boolean,
) {
    val modeledButStillDisabled: Boolean =
        SkaldVaultV1TestOnlyProviderIdentityDecisionStatus.IdentityDecisionModeled in statuses &&
            SkaldVaultV1TestOnlyProviderIdentityDecisionStatus.StillDisabled in statuses
}

object SkaldVaultV1TestOnlyProviderIdentityDecisionPolicy {
    const val POLICY_ID: String = "skald-vault-v1-test-only-provider-identity-decision-v1"
    const val POLICY_VERSION: Int = 1

    fun evaluateIdentityDecision(
        request: SkaldVaultV1TestOnlyProviderIdentityDecisionRequest =
            SkaldVaultV1TestOnlyProviderIdentityDecisionRequest(),
    ): SkaldVaultV1TestOnlyProviderIdentityDecisionEvidence {
        val requestBlockers = buildSet {
            if (request.userConsentOverrideRequested) {
                add(SkaldVaultV1TestOnlyProviderIdentityBlocker.UserConsentCannotOverride)
            }
            if (request.warningOnlyEvidenceClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityBlocker.WarningOnlyEvidenceNonAuthorizing)
            }
            if (request.testOnlyEvidenceClaimedAsProductionPromotion) {
                add(SkaldVaultV1TestOnlyProviderIdentityBlocker.TestOnlyEvidenceNonAuthorizing)
            }
            if (request.releaseEvidenceClaimed) {
                add(SkaldVaultV1TestOnlyProviderIdentityBlocker.ProductionProviderAcceptanceIncomplete)
            }
        }

        return SkaldVaultV1TestOnlyProviderIdentityDecisionEvidence(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            statuses = currentStatuses(),
            identityCategoryRows = currentIdentityCategoryRows(),
            decisionClassRows = currentDecisionClassRows(),
            constraintRows = currentConstraintRows(),
            forbiddenLinkageRows = currentForbiddenLinkageRows(),
            evidenceSourceRows = currentEvidenceSourceRows(),
            authorizationLimitRows = currentAuthorizationLimitRows(),
            futureBranchGuidanceRows = currentFutureBranchGuidanceRows(),
            blockers = currentBlockers() + requestBlockers,
            redactionClasses = currentRedactionClasses(),
            disabledCapabilities = SkaldVaultV1TestOnlyProviderIdentityCapability.Current,
            futureTestOnlyIdentityAllowedInPrinciple = true,
            testOnlyProviderIdentityImplemented = false,
            productionProviderIdentityImplemented = false,
            currentSelectableIdentityDisabledProviderOnly = true,
            productionProviderSelectable = false,
            vaultLifecycleBlocked = true,
            persistenceBlocked = true,
            productionSyncBlocked = true,
            mainnetBlocked = true,
        )
    }

    fun currentIdentityDecisionEvidence(): SkaldVaultV1TestOnlyProviderIdentityDecisionEvidence =
        evaluateIdentityDecision()

    fun currentPolicySummary(): SkaldVaultV1TestOnlyProviderIdentitySafeLabel =
        SkaldVaultV1TestOnlyProviderIdentitySafeLabel(
            "Model-only identity classification; no test-only or production provider identity is implemented.",
        )

    fun currentStatuses(): Set<SkaldVaultV1TestOnlyProviderIdentityDecisionStatus> =
        SkaldVaultV1TestOnlyProviderIdentityDecisionStatus.entries.toSet()

    fun currentBlockers(): Set<SkaldVaultV1TestOnlyProviderIdentityBlocker> =
        SkaldVaultV1TestOnlyProviderIdentityBlocker.entries.toSet()

    fun currentRedactionClasses(): Set<SkaldVaultV1TestOnlyProviderIdentityRedactionClass> =
        SkaldVaultV1TestOnlyProviderIdentityRedactionClass.entries.toSet()

    fun currentIdentityCategoryRows(): List<SkaldVaultV1TestOnlyProviderIdentityCategoryRow> =
        SkaldVaultV1TestOnlyProviderIdentityCategory.entries.map { category ->
            val implementedNow =
                category == SkaldVaultV1TestOnlyProviderIdentityCategory.DisabledProviderIdentity
            SkaldVaultV1TestOnlyProviderIdentityCategoryRow(
                category = category,
                modeled = true,
                implementedNow = implementedNow,
                registrySelectable = category == SkaldVaultV1TestOnlyProviderIdentityCategory.DisabledProviderIdentity,
                productionSelectable = false,
                factoryAvailable = false,
                dispatcherAvailable = false,
                executorTargetAvailable = false,
                canUseForKatNow = false,
                canUseForVaultCreation = false,
                canUseForVaultUnlock = false,
                canUseForVaultPersistence = false,
                blockers = currentBlockersForCategory(category),
            )
        }

    fun currentDecisionClassRows(): List<SkaldVaultV1TestOnlyProviderIdentityDecisionClassRow> =
        SkaldVaultV1TestOnlyProviderIdentityDecisionClass.entries.map { decisionClass ->
            SkaldVaultV1TestOnlyProviderIdentityDecisionClassRow(
                decisionClass = decisionClass,
                modeled = true,
                authorizesIdentityPromotion = false,
                authorizesCurrentImplementation = false,
                blockers = currentBlockers(),
            )
        }

    fun currentConstraintRows(): List<SkaldVaultV1TestOnlyProviderIdentityConstraintRow> =
        SkaldVaultV1TestOnlyProviderIdentityConstraint.entries.map { constraint ->
            SkaldVaultV1TestOnlyProviderIdentityConstraintRow(
                constraint = constraint,
                modeled = true,
                satisfiedForModelOnlyBoundary = true,
                futureImplementationReviewRequired = true,
                authorizesCurrentIdentity = false,
                blockers = currentBlockers(),
            )
        }

    fun currentForbiddenLinkageRows(): List<SkaldVaultV1TestOnlyProviderIdentityForbiddenLinkageRow> =
        SkaldVaultV1TestOnlyProviderIdentityForbiddenLinkage.entries.map { linkage ->
            SkaldVaultV1TestOnlyProviderIdentityForbiddenLinkageRow(
                linkage = linkage,
                forbidden = true,
                currentLinkPresent = false,
                canAuthorize = false,
                blockers = currentBlockers(),
            )
        }

    fun currentEvidenceSourceRows(): List<SkaldVaultV1TestOnlyProviderIdentityEvidenceSourceRow> =
        SkaldVaultV1TestOnlyProviderIdentityEvidenceSource.entries.map { source ->
            SkaldVaultV1TestOnlyProviderIdentityEvidenceSourceRow(
                evidenceSource = source,
                modeled = true,
                nonAuthorizing = true,
                authorizesImplementation = false,
                authorizesProduction = false,
                blockers = currentBlockers(),
            )
        }

    fun currentAuthorizationLimitRows(): List<SkaldVaultV1TestOnlyProviderIdentityAuthorizationLimitRow> =
        SkaldVaultV1TestOnlyProviderIdentityAuthorizationLimit.entries.map { limit ->
            SkaldVaultV1TestOnlyProviderIdentityAuthorizationLimitRow(
                authorizationLimit = limit,
                blocked = true,
                canAuthorize = false,
                blockers = currentBlockers(),
            )
        }

    fun currentFutureBranchGuidanceRows(): List<SkaldVaultV1TestOnlyProviderIdentityFutureBranchGuidanceRow> =
        SkaldVaultV1TestOnlyProviderIdentityFutureBranchGuidance.entries.map { guidance ->
            SkaldVaultV1TestOnlyProviderIdentityFutureBranchGuidanceRow(
                guidance = guidance,
                futureRequired = true,
                authorizesCurrentImplementation = false,
            )
        }

    fun currentBlockersForCategory(
        category: SkaldVaultV1TestOnlyProviderIdentityCategory,
    ): Set<SkaldVaultV1TestOnlyProviderIdentityBlocker> =
        when (category) {
            SkaldVaultV1TestOnlyProviderIdentityCategory.DisabledProviderIdentity ->
                setOf(
                    SkaldVaultV1TestOnlyProviderIdentityBlocker.ProviderSelectionDisabledProviderOnly,
                    SkaldVaultV1TestOnlyProviderIdentityBlocker.ProductionProviderSelectableFalse,
                    SkaldVaultV1TestOnlyProviderIdentityBlocker.ProviderOperationAuthorizationBlocked,
                    SkaldVaultV1TestOnlyProviderIdentityBlocker.VaultLifecycleDisabled,
                    SkaldVaultV1TestOnlyProviderIdentityBlocker.PersistenceDisabled,
                    SkaldVaultV1TestOnlyProviderIdentityBlocker.ProductionSyncDisabled,
                    SkaldVaultV1TestOnlyProviderIdentityBlocker.MainnetDisabled,
                )

            else -> currentBlockers()
        }
}
