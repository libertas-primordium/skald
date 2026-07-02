package com.libertasprimordium.skald.security

data class SkaldVaultV1TestOnlyProviderKatSourceSetConfinementId(val value: String)

data class SkaldVaultV1TestOnlyProviderKatSourceSetSafeLabel(val value: String)

enum class SkaldVaultV1TestOnlyProviderKatSourceSetConfinementStatus(val label: String) {
    ConfinementBoundaryModeled("confinement boundary modeled"),
    StillDisabled("still disabled"),
    SourceSetPolicyModeled("source-set policy modeled"),
    FutureTestOnlySourceSetCategoryDescribed("future test-only source-set category described"),
    ProductionSourceSetDenylistModeled("production source-set denylist modeled"),
    TestSourceSetAllowlistNotImplementationAuthorization("test source-set allowlist not implementation authorization"),
    ExecutorImplementationUnauthorized("executor implementation unauthorized"),
    ProductionExecutionForbidden("production execution forbidden"),
    ProviderSelectionAuthorizationBlocked("provider selection authorization blocked"),
    ProductionProviderSelectableFalse("productionProviderSelectable false"),
    VaultLifecycleBlocked("vault lifecycle blocked"),
    PersistenceBlocked("persistence blocked"),
    ProductionSyncBlocked("production sync blocked"),
    MainnetBlocked("mainnet blocked"),
}

enum class SkaldVaultV1TestOnlyProviderKatSourceSetCategory(
    val label: String,
    val productionForbidden: Boolean,
    val modelOnly: Boolean,
    val futureReviewRequired: Boolean,
    val evidenceOnly: Boolean,
    val rawMaterialForbidden: Boolean,
    val currentImplementationAuthorized: Boolean,
    val executorImplementationForbidden: Boolean,
    val runtimeArtifactNonAuthorizing: Boolean,
) {
    CommonMainProductionSource(
        "commonMain production source",
        productionForbidden = true,
        modelOnly = true,
        futureReviewRequired = false,
        evidenceOnly = false,
        rawMaterialForbidden = true,
        currentImplementationAuthorized = false,
        executorImplementationForbidden = true,
        runtimeArtifactNonAuthorizing = false,
    ),
    AndroidMainProductionSource(
        "androidMain production source",
        productionForbidden = true,
        modelOnly = false,
        futureReviewRequired = false,
        evidenceOnly = false,
        rawMaterialForbidden = true,
        currentImplementationAuthorized = false,
        executorImplementationForbidden = true,
        runtimeArtifactNonAuthorizing = false,
    ),
    DesktopMainProductionSource(
        "desktopMain production source",
        productionForbidden = true,
        modelOnly = false,
        futureReviewRequired = false,
        evidenceOnly = false,
        rawMaterialForbidden = true,
        currentImplementationAuthorized = false,
        executorImplementationForbidden = true,
        runtimeArtifactNonAuthorizing = false,
    ),
    CommonTestModelOnlyAssertions(
        "commonTest model-only assertions",
        productionForbidden = false,
        modelOnly = true,
        futureReviewRequired = false,
        evidenceOnly = false,
        rawMaterialForbidden = true,
        currentImplementationAuthorized = false,
        executorImplementationForbidden = false,
        runtimeArtifactNonAuthorizing = false,
    ),
    DesktopTestFutureExecutorCandidate(
        "desktopTest future executor candidate",
        productionForbidden = false,
        modelOnly = false,
        futureReviewRequired = true,
        evidenceOnly = false,
        rawMaterialForbidden = true,
        currentImplementationAuthorized = false,
        executorImplementationForbidden = false,
        runtimeArtifactNonAuthorizing = false,
    ),
    AndroidInstrumentedTestFutureExecutorCandidate(
        "androidInstrumentedTest future executor candidate",
        productionForbidden = false,
        modelOnly = false,
        futureReviewRequired = true,
        evidenceOnly = false,
        rawMaterialForbidden = true,
        currentImplementationAuthorized = false,
        executorImplementationForbidden = false,
        runtimeArtifactNonAuthorizing = false,
    ),
    BuildScriptsDependencyDeclarationOnly(
        "build scripts dependency declaration only",
        productionForbidden = true,
        modelOnly = false,
        futureReviewRequired = true,
        evidenceOnly = false,
        rawMaterialForbidden = true,
        currentImplementationAuthorized = false,
        executorImplementationForbidden = true,
        runtimeArtifactNonAuthorizing = false,
    ),
    DocsNonAuthorizingEvidence(
        "docs non-authorizing evidence",
        productionForbidden = false,
        modelOnly = false,
        futureReviewRequired = false,
        evidenceOnly = true,
        rawMaterialForbidden = true,
        currentImplementationAuthorized = false,
        executorImplementationForbidden = false,
        runtimeArtifactNonAuthorizing = false,
    ),
    GeneratedSourcesForbidden(
        "generated sources forbidden",
        productionForbidden = true,
        modelOnly = false,
        futureReviewRequired = false,
        evidenceOnly = false,
        rawMaterialForbidden = true,
        currentImplementationAuthorized = false,
        executorImplementationForbidden = true,
        runtimeArtifactNonAuthorizing = false,
    ),
    ResourcesForbiddenForRawMaterial(
        "resources forbidden for raw material",
        productionForbidden = true,
        modelOnly = false,
        futureReviewRequired = false,
        evidenceOnly = false,
        rawMaterialForbidden = true,
        currentImplementationAuthorized = false,
        executorImplementationForbidden = true,
        runtimeArtifactNonAuthorizing = false,
    ),
    PackagingOutputsForbidden(
        "packaging outputs forbidden",
        productionForbidden = true,
        modelOnly = false,
        futureReviewRequired = false,
        evidenceOnly = false,
        rawMaterialForbidden = true,
        currentImplementationAuthorized = false,
        executorImplementationForbidden = true,
        runtimeArtifactNonAuthorizing = true,
    ),
}

enum class SkaldVaultV1TestOnlyProviderKatSourceSetDecision(val label: String) {
    ProductionSourceForbidden("production source forbidden"),
    ModelOnlyCommonSourceAllowed("model-only common source allowed"),
    CommonTestAssertionsAllowed("commonTest assertions allowed"),
    DesktopTestFutureReviewRequired("desktopTest future review required"),
    AndroidInstrumentedTestFutureReviewRequired("androidInstrumentedTest future review required"),
    BuildScriptExecutionForbidden("build script execution forbidden"),
    DocsEvidenceOnly("docs evidence only"),
    RawFixtureResourcesForbidden("raw fixture resources forbidden"),
    RuntimeOutputArtifactsNonAuthorizing("runtime output artifacts non-authorizing"),
    NotCurrentImplementationAuthorization("not current implementation authorization"),
}

enum class SkaldVaultV1TestOnlyProviderKatConfinementRule(val label: String) {
    FutureExecutorAbsentFromCommonMain("future executor must be absent from commonMain"),
    FutureExecutorAbsentFromAndroidMain("future executor must be absent from androidMain"),
    FutureExecutorAbsentFromDesktopMain("future executor must be absent from desktopMain"),
    FutureExecutorAbsentFromAppUiSource("future executor must be absent from app UI source"),
    FutureExecutorAbsentFromSettingsCodecs("future executor must be absent from settings codecs"),
    FutureExecutorAbsentFromSecureStorageRepositories("future executor must be absent from secure storage repositories"),
    FutureExecutorAbsentFromSecureMetadataRepositories("future executor must be absent from secure metadata repositories"),
    FutureExecutorAbsentFromBdkAdapterProductionPaths("future executor must be absent from BDK adapter production routes"),
    FutureExecutorMayBeConsideredOnlyInDesktopTestAfterExplicitBranchApproval(
        "future executor may be considered only in desktopTest after explicit branch approval",
    ),
    FutureExecutorMayBeConsideredOnlyInAndroidInstrumentedTestAfterExplicitBranchApproval(
        "future executor may be considered only in androidInstrumentedTest after explicit branch approval",
    ),
    FutureCommonTestParticipationMustRemainModelOnly("future commonTest participation must remain model-only"),
    FutureVectorMaterialMustNotLiveInCommonMain("future vector material must not live in commonMain"),
    FutureRawMaterialMustNotLiveInProductionResources("future raw material must not live in production resources"),
    FutureTestResultsMustRemainNonAuthorizing("future test results must remain non-authorizing"),
    FutureTaskWiringMustNotMakeProductionRuntimeDependOnExecutor(
        "future task wiring must not make production runtime depend on executor",
    ),
}

enum class SkaldVaultV1TestOnlyProviderKatForbiddenProductionTokenClass(val label: String) {
    ProviderKatExecutorImplementation("provider KAT executor implementation"),
    RunnableExecutorInterface("runnable executor interface"),
    ProviderOperationRunner("provider operation runner"),
    VectorRunner("vector runner"),
    CryptoProviderReference("crypto provider reference"),
    TinkPrimitiveConstruction("Tink primitive construction"),
    BouncyCastleKdfConstruction("Bouncy Castle KDF construction"),
    JcaJceCryptoConstruction("JCA/JCE crypto construction"),
    PlatformCsprngRuntimeCall("platform CSPRNG runtime call"),
    RawVectorMaterial("raw vector material"),
    WalletLikeFixtures("wallet-like fixtures"),
    KeysetStorage("keyset storage"),
    VaultLifecycleHook("vault lifecycle hook"),
    ProviderSelectionPromotionHook("provider selection promotion hook"),
    ProductionSyncHook("production sync hook"),
    MainnetHook("mainnet hook"),
}

enum class SkaldVaultV1TestOnlyProviderKatSourceGuardRequirement(val label: String) {
    CryptoImportsAbsentFromCommonMainSecurityBoundaries(
        "crypto imports absent from commonMain security boundaries",
    ),
    BdkImportsAbsentFromCommonMainSecurityBoundaries("BDK imports absent from commonMain security boundaries"),
    ProcessNetworkApisAbsentFromCommonMainSecurityBoundaries(
        "process/network APIs absent from commonMain security boundaries",
    ),
    FileSettingsApisAbsentFromCommonMainSecurityBoundaries(
        "file/settings APIs absent from commonMain security boundaries",
    ),
    RawMaterialTypesAbsentFromDecisionModelFiles("raw material types absent from decision model files"),
    RunnableExecutorMethodNamesAbsent("runnable executor method names absent"),
    RawVectorFieldNamesAbsent("raw vector field names absent"),
    ProductionEnablementFlagsAbsent("production enablement flags absent"),
    WalletMaterialFixturesAbsent("wallet material fixtures absent"),
    ProviderSelectionRemainsDisabled("provider selection remains disabled"),
    ProductionProviderSelectableFalseAsserted("productionProviderSelectable false asserted"),
}

enum class SkaldVaultV1TestOnlyProviderKatSourceSetAuthorizationLimit(val label: String) {
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

enum class SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker(val label: String) {
    SourceSetConfinementModeledOnly("source-set confinement modeled only"),
    ExecutorImplementationBranchNotAuthorized("executor implementation branch not authorized"),
    NoExecutorImplementation("no executor implementation"),
    NoRunnableExecutorSurface("no runnable executor surface"),
    DesktopTestExecutorReviewMissing("desktopTest executor review missing"),
    AndroidInstrumentedTestExecutorReviewMissing("androidInstrumentedTest executor review missing"),
    SourceGuardsStillRequired("source guards still required"),
    ProviderImplementationMissing("provider implementation missing"),
    ProviderFactoryMissing("provider factory missing"),
    ProviderDispatcherMissing("provider dispatcher missing"),
    NonDisabledRegistryEntryMissing("non-disabled registry entry missing"),
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

enum class SkaldVaultV1TestOnlyProviderKatSourceSetRedactionClass(val label: String) {
    PolicyIdsOnly("policy IDs only"),
    ConfinementIdsOnly("confinement IDs only"),
    SourceSetLabelsOnly("source-set labels only"),
    DecisionLabelsOnly("decision labels only"),
    RuleLabelsOnly("rule labels only"),
    TokenClassLabelsOnly("token class labels only"),
    SourceGuardLabelsOnly("source-guard labels only"),
    AuthorizationLimitLabelsOnly("authorization-limit labels only"),
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

data class SkaldVaultV1TestOnlyProviderKatSourceSetConfinementCapability(
    val confinementModeled: Boolean,
    val executorImplementationAuthorizedNow: Boolean,
    val executorCallableNow: Boolean,
    val canUseCommonMainExecution: Boolean,
    val canUseAndroidMainExecution: Boolean,
    val canUseDesktopMainExecution: Boolean,
    val canUseCommonTestExecution: Boolean,
    val canUseDesktopTestExecutionNow: Boolean,
    val canUseAndroidInstrumentedTestExecutionNow: Boolean,
    val canAddRunnableInterfaceNow: Boolean,
    val canAddRawVectorMaterialNow: Boolean,
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
        val Current = SkaldVaultV1TestOnlyProviderKatSourceSetConfinementCapability(
            confinementModeled = true,
            executorImplementationAuthorizedNow = false,
            executorCallableNow = false,
            canUseCommonMainExecution = false,
            canUseAndroidMainExecution = false,
            canUseDesktopMainExecution = false,
            canUseCommonTestExecution = false,
            canUseDesktopTestExecutionNow = false,
            canUseAndroidInstrumentedTestExecutionNow = false,
            canAddRunnableInterfaceNow = false,
            canAddRawVectorMaterialNow = false,
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

data class SkaldVaultV1TestOnlyProviderKatSourceSetCategoryRow(
    val category: SkaldVaultV1TestOnlyProviderKatSourceSetCategory,
    val decisions: Set<SkaldVaultV1TestOnlyProviderKatSourceSetDecision>,
    val currentImplementationAuthorized: Boolean,
    val currentExecutionAuthorized: Boolean,
    val productionForbidden: Boolean,
    val modelOnly: Boolean,
    val futureReviewRequired: Boolean,
    val evidenceOnly: Boolean,
    val rawMaterialForbidden: Boolean,
    val runtimeArtifactNonAuthorizing: Boolean,
    val blockers: Set<SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker>,
    val safeSourceSetId: SkaldVaultV1TestOnlyProviderKatSourceSetConfinementId,
    val redactionClass: SkaldVaultV1TestOnlyProviderKatSourceSetRedactionClass,
)

data class SkaldVaultV1TestOnlyProviderKatConfinementRuleRow(
    val rule: SkaldVaultV1TestOnlyProviderKatConfinementRule,
    val modeled: Boolean,
    val currentBranchSatisfiedForModelOnlyEvidence: Boolean,
    val currentBranchAuthorizesImplementation: Boolean,
    val futureReviewRequired: Boolean,
    val blockers: Set<SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker>,
    val safeRuleId: SkaldVaultV1TestOnlyProviderKatSourceSetConfinementId,
    val redactionClass: SkaldVaultV1TestOnlyProviderKatSourceSetRedactionClass,
)

data class SkaldVaultV1TestOnlyProviderKatForbiddenProductionTokenRow(
    val tokenClass: SkaldVaultV1TestOnlyProviderKatForbiddenProductionTokenClass,
    val productionSourceSetForbidden: Boolean,
    val currentSourcePresent: Boolean,
    val canAuthorizeImplementation: Boolean,
    val blockers: Set<SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker>,
    val safeTokenId: SkaldVaultV1TestOnlyProviderKatSourceSetConfinementId,
    val redactionClass: SkaldVaultV1TestOnlyProviderKatSourceSetRedactionClass,
)

data class SkaldVaultV1TestOnlyProviderKatSourceGuardRequirementRow(
    val requirement: SkaldVaultV1TestOnlyProviderKatSourceGuardRequirement,
    val modeled: Boolean,
    val requiredForFutureExecutorBranch: Boolean,
    val currentBranchCanAuthorizeImplementation: Boolean,
    val blockers: Set<SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker>,
    val safeGuardId: SkaldVaultV1TestOnlyProviderKatSourceSetConfinementId,
    val redactionClass: SkaldVaultV1TestOnlyProviderKatSourceSetRedactionClass,
)

data class SkaldVaultV1TestOnlyProviderKatSourceSetAuthorizationLimitRow(
    val limit: SkaldVaultV1TestOnlyProviderKatSourceSetAuthorizationLimit,
    val sourceSetConfinementEvidenceCanAuthorize: Boolean,
    val currentConfinementCanAuthorize: Boolean,
    val blockers: Set<SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker>,
    val redactionClass: SkaldVaultV1TestOnlyProviderKatSourceSetRedactionClass,
)

data class SkaldVaultV1TestOnlyProviderKatSourceSetConfinementRequest(
    val includeSourceSetEvidence: Boolean,
    val warningOnlyEvidenceClaimed: Boolean,
    val userConsentOverrideRequested: Boolean,
    val releaseEvidenceClaimed: Boolean,
    val safeConfinementId: String,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderKatSourceSetConfinementRequest(" +
            "includeSourceSetEvidence=$includeSourceSetEvidence, " +
            "warningOnlyEvidenceClaimed=$warningOnlyEvidenceClaimed, " +
            "userConsentOverrideRequested=$userConsentOverrideRequested, " +
            "releaseEvidenceClaimed=$releaseEvidenceClaimed, " +
            "safeConfinementId=<redacted>, " +
            "rawMaterial=<redacted>, " +
            "payload=<redacted>, " +
            "providerReference=<redacted>, " +
            "cryptoReference=<redacted>, " +
            "locationReference=<redacted>, " +
            "storageReference=<redacted>, " +
            "backendReference=<redacted>" +
            ")"

    companion object {
        fun currentConfinement(
            includeSourceSetEvidence: Boolean = true,
            safeConfinementId: String = "current-test-only-provider-kat-source-set-confinement",
        ): SkaldVaultV1TestOnlyProviderKatSourceSetConfinementRequest =
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementRequest(
                includeSourceSetEvidence = includeSourceSetEvidence,
                warningOnlyEvidenceClaimed = false,
                userConsentOverrideRequested = false,
                releaseEvidenceClaimed = false,
                safeConfinementId = safeConfinementId,
            )
    }
}

data class SkaldVaultV1TestOnlyProviderKatSourceSetConfinementEvidence(
    val status: SkaldVaultV1TestOnlyProviderKatSourceSetConfinementStatus,
    val statuses: Set<SkaldVaultV1TestOnlyProviderKatSourceSetConfinementStatus>,
    val sourceSetRows: List<SkaldVaultV1TestOnlyProviderKatSourceSetCategoryRow>,
    val confinementRuleRows: List<SkaldVaultV1TestOnlyProviderKatConfinementRuleRow>,
    val forbiddenProductionTokenRows: List<SkaldVaultV1TestOnlyProviderKatForbiddenProductionTokenRow>,
    val sourceGuardRequirementRows: List<SkaldVaultV1TestOnlyProviderKatSourceGuardRequirementRow>,
    val authorizationLimitRows: List<SkaldVaultV1TestOnlyProviderKatSourceSetAuthorizationLimitRow>,
    val blockers: Set<SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker>,
    val redactionClasses: Set<SkaldVaultV1TestOnlyProviderKatSourceSetRedactionClass>,
    val disabledCapabilities: SkaldVaultV1TestOnlyProviderKatSourceSetConfinementCapability,
    val confinementModeled: Boolean,
    val stillDisabled: Boolean,
    val sourceSetCategoryModeled: Boolean,
    val sourceSetModelingAuthorizesImplementation: Boolean,
    val executorImplementationAuthorizedNow: Boolean,
    val executorCallableNow: Boolean,
    val commonTestModelOnly: Boolean,
    val docsEvidenceOnly: Boolean,
    val resourcesRawMaterialForbidden: Boolean,
    val packagingOutputsNonAuthorizing: Boolean,
    val providerSelectionDisabledProviderOnly: Boolean,
    val productionProviderSelectable: Boolean,
    val vaultLifecycleBlocked: Boolean,
    val persistenceBlocked: Boolean,
    val productionSyncBlocked: Boolean,
    val mainnetBlocked: Boolean,
)

sealed interface SkaldVaultV1TestOnlyProviderKatSourceSetConfinementResult<out T> {
    val value: T

    data class Blocked<out T>(
        override val value: T,
    ) : SkaldVaultV1TestOnlyProviderKatSourceSetConfinementResult<T>
}

data class SkaldVaultV1TestOnlyProviderKatSourceSetConfinementSummary(
    val policyId: String,
    val policyVersion: Int,
    val statuses: Set<SkaldVaultV1TestOnlyProviderKatSourceSetConfinementStatus>,
    val sourceSetCategories: Set<SkaldVaultV1TestOnlyProviderKatSourceSetCategory>,
    val decisions: Set<SkaldVaultV1TestOnlyProviderKatSourceSetDecision>,
    val confinementRules: Set<SkaldVaultV1TestOnlyProviderKatConfinementRule>,
    val forbiddenProductionTokenClasses: Set<SkaldVaultV1TestOnlyProviderKatForbiddenProductionTokenClass>,
    val sourceGuardRequirements: Set<SkaldVaultV1TestOnlyProviderKatSourceGuardRequirement>,
    val authorizationLimits: Set<SkaldVaultV1TestOnlyProviderKatSourceSetAuthorizationLimit>,
    val blockers: Set<SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker>,
    val redactionClasses: Set<SkaldVaultV1TestOnlyProviderKatSourceSetRedactionClass>,
    val capability: SkaldVaultV1TestOnlyProviderKatSourceSetConfinementCapability,
)

object SkaldVaultV1TestOnlyProviderKatSourceSetConfinementPolicy {
    const val POLICY_ID: String = "skald-vault-v1-test-only-provider-kat-source-set-confinement-v1"
    const val POLICY_VERSION: Int = 1

    fun evaluateConfinement(
        request: SkaldVaultV1TestOnlyProviderKatSourceSetConfinementRequest =
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementRequest.currentConfinement(),
    ): SkaldVaultV1TestOnlyProviderKatSourceSetConfinementResult.Blocked<
        SkaldVaultV1TestOnlyProviderKatSourceSetConfinementEvidence,
    > =
        SkaldVaultV1TestOnlyProviderKatSourceSetConfinementResult.Blocked(
            currentConfinementEvidence(request),
        )

    fun currentPolicySummary(): SkaldVaultV1TestOnlyProviderKatSourceSetConfinementSummary =
        SkaldVaultV1TestOnlyProviderKatSourceSetConfinementSummary(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            statuses = SkaldVaultV1TestOnlyProviderKatSourceSetConfinementStatus.entries.toSet(),
            sourceSetCategories = SkaldVaultV1TestOnlyProviderKatSourceSetCategory.entries.toSet(),
            decisions = SkaldVaultV1TestOnlyProviderKatSourceSetDecision.entries.toSet(),
            confinementRules = SkaldVaultV1TestOnlyProviderKatConfinementRule.entries.toSet(),
            forbiddenProductionTokenClasses =
                SkaldVaultV1TestOnlyProviderKatForbiddenProductionTokenClass.entries.toSet(),
            sourceGuardRequirements = SkaldVaultV1TestOnlyProviderKatSourceGuardRequirement.entries.toSet(),
            authorizationLimits = SkaldVaultV1TestOnlyProviderKatSourceSetAuthorizationLimit.entries.toSet(),
            blockers = currentBlockers(),
            redactionClasses = SkaldVaultV1TestOnlyProviderKatSourceSetRedactionClass.entries.toSet(),
            capability = SkaldVaultV1TestOnlyProviderKatSourceSetConfinementCapability.Current,
        )

    fun currentConfinementEvidence(
        request: SkaldVaultV1TestOnlyProviderKatSourceSetConfinementRequest =
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementRequest.currentConfinement(),
    ): SkaldVaultV1TestOnlyProviderKatSourceSetConfinementEvidence {
        val sourceSetRows = currentSourceSetRows()
        val ruleRows = currentConfinementRuleRows()
        val tokenRows = currentForbiddenProductionTokenRows()
        val guardRows = currentSourceGuardRequirementRows()
        val authorizationRows = currentAuthorizationLimitRows()
        val blockers = (
            currentBlockers() +
                sourceSetRows.flatMap { it.blockers } +
                ruleRows.flatMap { it.blockers } +
                tokenRows.flatMap { it.blockers } +
                guardRows.flatMap { it.blockers } +
                authorizationRows.flatMap { it.blockers } +
                requestBlockers(request)
            ).toSet()
        return SkaldVaultV1TestOnlyProviderKatSourceSetConfinementEvidence(
            status = SkaldVaultV1TestOnlyProviderKatSourceSetConfinementStatus.StillDisabled,
            statuses = currentStatuses(),
            sourceSetRows = sourceSetRows,
            confinementRuleRows = ruleRows,
            forbiddenProductionTokenRows = tokenRows,
            sourceGuardRequirementRows = guardRows,
            authorizationLimitRows = authorizationRows,
            blockers = blockers,
            redactionClasses = SkaldVaultV1TestOnlyProviderKatSourceSetRedactionClass.entries.toSet(),
            disabledCapabilities = SkaldVaultV1TestOnlyProviderKatSourceSetConfinementCapability.Current,
            confinementModeled = true,
            stillDisabled = true,
            sourceSetCategoryModeled = true,
            sourceSetModelingAuthorizesImplementation = false,
            executorImplementationAuthorizedNow = false,
            executorCallableNow = false,
            commonTestModelOnly = true,
            docsEvidenceOnly = true,
            resourcesRawMaterialForbidden = true,
            packagingOutputsNonAuthorizing = true,
            providerSelectionDisabledProviderOnly = true,
            productionProviderSelectable = false,
            vaultLifecycleBlocked = true,
            persistenceBlocked = true,
            productionSyncBlocked = true,
            mainnetBlocked = true,
        )
    }

    fun currentStatuses(): Set<SkaldVaultV1TestOnlyProviderKatSourceSetConfinementStatus> =
        setOf(
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementStatus.ConfinementBoundaryModeled,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementStatus.StillDisabled,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementStatus.SourceSetPolicyModeled,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementStatus
                .FutureTestOnlySourceSetCategoryDescribed,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementStatus.ProductionSourceSetDenylistModeled,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementStatus
                .TestSourceSetAllowlistNotImplementationAuthorization,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementStatus.ExecutorImplementationUnauthorized,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementStatus.ProductionExecutionForbidden,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementStatus.ProviderSelectionAuthorizationBlocked,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementStatus.ProductionProviderSelectableFalse,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementStatus.VaultLifecycleBlocked,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementStatus.PersistenceBlocked,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementStatus.ProductionSyncBlocked,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementStatus.MainnetBlocked,
        )

    fun currentBlockers(): Set<SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker> =
        setOf(
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.SourceSetConfinementModeledOnly,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.ExecutorImplementationBranchNotAuthorized,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.NoExecutorImplementation,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.NoRunnableExecutorSurface,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.DesktopTestExecutorReviewMissing,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker
                .AndroidInstrumentedTestExecutorReviewMissing,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.SourceGuardsStillRequired,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.ProviderImplementationMissing,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.ProviderFactoryMissing,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.ProviderDispatcherMissing,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.NonDisabledRegistryEntryMissing,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.ProviderSelectionDisabledProviderOnly,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.ProductionProviderSelectableFalse,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.ProviderOperationAuthorizationBlocked,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.RuntimeRandomnessAuthorizationBlocked,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.KdfCalibrationNonFinal,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.ProductionProviderAcceptanceIncomplete,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.SecureStorageDisabled,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.SecureMetadataDisabled,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.VaultLifecycleDisabled,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.PersistenceDisabled,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.ProductionSyncDisabled,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.TestOnlyEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.WarningOnlyEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.UserConsentCannotOverride,
            SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.MainnetDisabled,
        )

    fun requestBlockers(
        request: SkaldVaultV1TestOnlyProviderKatSourceSetConfinementRequest,
    ): Set<SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker> =
        buildSet {
            if (request.warningOnlyEvidenceClaimed) {
                add(SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.WarningOnlyEvidenceNonAuthorizing)
            }
            if (request.userConsentOverrideRequested) {
                add(SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.UserConsentCannotOverride)
            }
            if (request.releaseEvidenceClaimed) {
                add(SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.MainnetDisabled)
                add(SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.ProductionSyncDisabled)
            }
        }

    fun currentSourceSetRows(): List<SkaldVaultV1TestOnlyProviderKatSourceSetCategoryRow> =
        SkaldVaultV1TestOnlyProviderKatSourceSetCategory.entries.map { category ->
            SkaldVaultV1TestOnlyProviderKatSourceSetCategoryRow(
                category = category,
                decisions = sourceSetDecisions(category),
                currentImplementationAuthorized = false,
                currentExecutionAuthorized = false,
                productionForbidden = category.productionForbidden,
                modelOnly = category.modelOnly,
                futureReviewRequired = category.futureReviewRequired,
                evidenceOnly = category.evidenceOnly,
                rawMaterialForbidden = category.rawMaterialForbidden,
                runtimeArtifactNonAuthorizing = category.runtimeArtifactNonAuthorizing,
                blockers = sourceSetBlockers(category),
                safeSourceSetId = SkaldVaultV1TestOnlyProviderKatSourceSetConfinementId(
                    "source-set-${category.name.safeConfinementToken()}",
                ),
                redactionClass = SkaldVaultV1TestOnlyProviderKatSourceSetRedactionClass.SourceSetLabelsOnly,
            )
        }

    fun sourceSetDecisions(
        category: SkaldVaultV1TestOnlyProviderKatSourceSetCategory,
    ): Set<SkaldVaultV1TestOnlyProviderKatSourceSetDecision> =
        buildSet {
            add(SkaldVaultV1TestOnlyProviderKatSourceSetDecision.NotCurrentImplementationAuthorization)
            if (category.productionForbidden) {
                add(SkaldVaultV1TestOnlyProviderKatSourceSetDecision.ProductionSourceForbidden)
            }
            if (category == SkaldVaultV1TestOnlyProviderKatSourceSetCategory.CommonMainProductionSource) {
                add(SkaldVaultV1TestOnlyProviderKatSourceSetDecision.ModelOnlyCommonSourceAllowed)
            }
            if (category == SkaldVaultV1TestOnlyProviderKatSourceSetCategory.CommonTestModelOnlyAssertions) {
                add(SkaldVaultV1TestOnlyProviderKatSourceSetDecision.CommonTestAssertionsAllowed)
            }
            if (category == SkaldVaultV1TestOnlyProviderKatSourceSetCategory.DesktopTestFutureExecutorCandidate) {
                add(SkaldVaultV1TestOnlyProviderKatSourceSetDecision.DesktopTestFutureReviewRequired)
            }
            if (
                category ==
                SkaldVaultV1TestOnlyProviderKatSourceSetCategory.AndroidInstrumentedTestFutureExecutorCandidate
            ) {
                add(SkaldVaultV1TestOnlyProviderKatSourceSetDecision.AndroidInstrumentedTestFutureReviewRequired)
            }
            if (category == SkaldVaultV1TestOnlyProviderKatSourceSetCategory.BuildScriptsDependencyDeclarationOnly) {
                add(SkaldVaultV1TestOnlyProviderKatSourceSetDecision.BuildScriptExecutionForbidden)
            }
            if (category.evidenceOnly) {
                add(SkaldVaultV1TestOnlyProviderKatSourceSetDecision.DocsEvidenceOnly)
            }
            if (category == SkaldVaultV1TestOnlyProviderKatSourceSetCategory.ResourcesForbiddenForRawMaterial) {
                add(SkaldVaultV1TestOnlyProviderKatSourceSetDecision.RawFixtureResourcesForbidden)
            }
            if (category.runtimeArtifactNonAuthorizing) {
                add(SkaldVaultV1TestOnlyProviderKatSourceSetDecision.RuntimeOutputArtifactsNonAuthorizing)
            }
        }

    fun sourceSetBlockers(
        category: SkaldVaultV1TestOnlyProviderKatSourceSetCategory,
    ): Set<SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker> =
        buildSet {
            add(SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.SourceSetConfinementModeledOnly)
            add(SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.NoExecutorImplementation)
            add(SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.NoRunnableExecutorSurface)
            add(SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.TestOnlyEvidenceNonAuthorizing)
            if (category.productionForbidden || category.executorImplementationForbidden) {
                add(SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.ProviderSelectionDisabledProviderOnly)
                add(SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.ProductionProviderSelectableFalse)
                add(SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.ProviderOperationAuthorizationBlocked)
            }
            if (category == SkaldVaultV1TestOnlyProviderKatSourceSetCategory.DesktopTestFutureExecutorCandidate) {
                add(SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.DesktopTestExecutorReviewMissing)
            }
            if (
                category ==
                SkaldVaultV1TestOnlyProviderKatSourceSetCategory.AndroidInstrumentedTestFutureExecutorCandidate
            ) {
                add(
                    SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker
                        .AndroidInstrumentedTestExecutorReviewMissing,
                )
            }
        }

    fun currentConfinementRuleRows(): List<SkaldVaultV1TestOnlyProviderKatConfinementRuleRow> =
        SkaldVaultV1TestOnlyProviderKatConfinementRule.entries.map { rule ->
            SkaldVaultV1TestOnlyProviderKatConfinementRuleRow(
                rule = rule,
                modeled = true,
                currentBranchSatisfiedForModelOnlyEvidence = true,
                currentBranchAuthorizesImplementation = false,
                futureReviewRequired = futureReviewRequired(rule),
                blockers = ruleBlockers(rule),
                safeRuleId = SkaldVaultV1TestOnlyProviderKatSourceSetConfinementId(
                    "confinement-rule-${rule.name.safeConfinementToken()}",
                ),
                redactionClass = SkaldVaultV1TestOnlyProviderKatSourceSetRedactionClass.RuleLabelsOnly,
            )
        }

    fun futureReviewRequired(rule: SkaldVaultV1TestOnlyProviderKatConfinementRule): Boolean =
        when (rule) {
            SkaldVaultV1TestOnlyProviderKatConfinementRule
                .FutureExecutorMayBeConsideredOnlyInDesktopTestAfterExplicitBranchApproval,
            SkaldVaultV1TestOnlyProviderKatConfinementRule
                .FutureExecutorMayBeConsideredOnlyInAndroidInstrumentedTestAfterExplicitBranchApproval,
            SkaldVaultV1TestOnlyProviderKatConfinementRule.FutureTaskWiringMustNotMakeProductionRuntimeDependOnExecutor,
            -> true
            else -> false
        }

    fun ruleBlockers(
        rule: SkaldVaultV1TestOnlyProviderKatConfinementRule,
    ): Set<SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker> =
        buildSet {
            add(SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.ExecutorImplementationBranchNotAuthorized)
            add(SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.NoExecutorImplementation)
            add(SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.TestOnlyEvidenceNonAuthorizing)
            if (futureReviewRequired(rule)) {
                add(SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.SourceGuardsStillRequired)
            }
        }

    fun currentForbiddenProductionTokenRows(): List<SkaldVaultV1TestOnlyProviderKatForbiddenProductionTokenRow> =
        SkaldVaultV1TestOnlyProviderKatForbiddenProductionTokenClass.entries.map { tokenClass ->
            SkaldVaultV1TestOnlyProviderKatForbiddenProductionTokenRow(
                tokenClass = tokenClass,
                productionSourceSetForbidden = true,
                currentSourcePresent = false,
                canAuthorizeImplementation = false,
                blockers = setOf(
                    SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker
                        .ExecutorImplementationBranchNotAuthorized,
                    SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.ProviderSelectionDisabledProviderOnly,
                    SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.ProductionProviderSelectableFalse,
                ),
                safeTokenId = SkaldVaultV1TestOnlyProviderKatSourceSetConfinementId(
                    "forbidden-token-${tokenClass.name.safeConfinementToken()}",
                ),
                redactionClass = SkaldVaultV1TestOnlyProviderKatSourceSetRedactionClass.TokenClassLabelsOnly,
            )
        }

    fun currentSourceGuardRequirementRows(): List<SkaldVaultV1TestOnlyProviderKatSourceGuardRequirementRow> =
        SkaldVaultV1TestOnlyProviderKatSourceGuardRequirement.entries.map { requirement ->
            SkaldVaultV1TestOnlyProviderKatSourceGuardRequirementRow(
                requirement = requirement,
                modeled = true,
                requiredForFutureExecutorBranch = true,
                currentBranchCanAuthorizeImplementation = false,
                blockers = setOf(
                    SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.SourceGuardsStillRequired,
                    SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker
                        .ExecutorImplementationBranchNotAuthorized,
                ),
                safeGuardId = SkaldVaultV1TestOnlyProviderKatSourceSetConfinementId(
                    "source-guard-${requirement.name.safeConfinementToken()}",
                ),
                redactionClass = SkaldVaultV1TestOnlyProviderKatSourceSetRedactionClass.SourceGuardLabelsOnly,
            )
        }

    fun currentAuthorizationLimitRows(): List<SkaldVaultV1TestOnlyProviderKatSourceSetAuthorizationLimitRow> =
        SkaldVaultV1TestOnlyProviderKatSourceSetAuthorizationLimit.entries.map { limit ->
            SkaldVaultV1TestOnlyProviderKatSourceSetAuthorizationLimitRow(
                limit = limit,
                sourceSetConfinementEvidenceCanAuthorize = false,
                currentConfinementCanAuthorize = false,
                blockers = setOf(
                    SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker
                        .SourceSetConfinementModeledOnly,
                    SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.TestOnlyEvidenceNonAuthorizing,
                    SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.ProviderSelectionDisabledProviderOnly,
                    SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.ProductionProviderSelectableFalse,
                    SkaldVaultV1TestOnlyProviderKatSourceSetConfinementBlocker.MainnetDisabled,
                ),
                redactionClass =
                    SkaldVaultV1TestOnlyProviderKatSourceSetRedactionClass.AuthorizationLimitLabelsOnly,
            )
        }

    fun String.safeConfinementToken(): String =
        fold(StringBuilder()) { builder, character ->
            if (character.isUpperCase() && builder.isNotEmpty()) {
                builder.append('-')
            }
            builder.append(character.lowercaseChar())
        }.toString()
}
