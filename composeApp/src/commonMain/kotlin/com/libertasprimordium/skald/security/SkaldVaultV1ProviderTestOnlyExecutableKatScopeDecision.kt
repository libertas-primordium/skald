package com.libertasprimordium.skald.security

interface SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecision {
    fun decide(
        request: SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionRequest =
            SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionRequest.currentScope(),
    ): SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionResult<
        SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionEvidence,
    >
}

enum class SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionStatus(val label: String) {
    ScopeDecisionModeled("scope decision modeled"),
    StillDisabled("still disabled"),
    FutureTestOnlyPathPermittedInPrinciple("future test-only path permitted in principle"),
    CurrentBranchExecutionNotAuthorized("current branch execution not authorized"),
    TestOnlyExecutorMissing("test-only executor missing"),
    ProductionExecutorForbidden("production executor forbidden"),
    ProductionProviderSelectionForbidden("production provider selection forbidden"),
    ProductionProviderSelectableFalse("productionProviderSelectable false"),
    VaultLifecycleBlocked("vault lifecycle blocked"),
    PersistenceBlocked("persistence blocked"),
    ProductionSyncBlocked("production sync blocked"),
    MainnetBlocked("mainnet blocked"),
}

enum class SkaldVaultV1ProviderTestOnlyExecutableKatSourceSetCategory(
    val label: String,
    val futureScopeAllowedInPrinciple: Boolean,
    val productionSource: Boolean,
) {
    DesktopTestOnly("desktopTest only", futureScopeAllowedInPrinciple = true, productionSource = false),
    AndroidInstrumentedTestOnly(
        "androidInstrumentedTest only",
        futureScopeAllowedInPrinciple = true,
        productionSource = false,
    ),
    CommonTestModelAssertionsOnly(
        "commonTest only for model assertions",
        futureScopeAllowedInPrinciple = true,
        productionSource = false,
    ),
    ProductionCommonMainForbidden(
        "production commonMain forbidden",
        futureScopeAllowedInPrinciple = false,
        productionSource = true,
    ),
    ProductionAndroidMainForbidden(
        "production androidMain forbidden",
        futureScopeAllowedInPrinciple = false,
        productionSource = true,
    ),
    ProductionDesktopMainForbidden(
        "production desktopMain forbidden",
        futureScopeAllowedInPrinciple = false,
        productionSource = true,
    ),
    BuildScriptsForbiddenExceptExplicitDependencyDeclarations(
        "build scripts forbidden except explicit dependency declarations",
        futureScopeAllowedInPrinciple = false,
        productionSource = false,
    ),
    DocsAllowedAsNonAuthorizingEvidence(
        "docs allowed as non-authorizing evidence",
        futureScopeAllowedInPrinciple = true,
        productionSource = false,
    ),
}

enum class SkaldVaultV1ProviderTestOnlyExecutableKatOperationCategory(
    val label: String,
    val futureTestOnlyAllowedInPrinciple: Boolean,
    val forbiddenEvenForTestOnly: Boolean,
) {
    PublicNonWalletPositiveVectors(
        "execute public non-wallet positive vectors",
        futureTestOnlyAllowedInPrinciple = true,
        forbiddenEvenForTestOnly = false,
    ),
    PublicNonWalletNegativeVectors(
        "execute public non-wallet negative vectors",
        futureTestOnlyAllowedInPrinciple = true,
        forbiddenEvenForTestOnly = false,
    ),
    FixedDeterministicProviderVectors(
        "execute fixed deterministic provider vectors",
        futureTestOnlyAllowedInPrinciple = true,
        forbiddenEvenForTestOnly = false,
    ),
    RandomizedBehavioralAeadChecksWithTestOnlyGeneratedMaterial(
        "execute randomized behavioral AEAD checks with test-only generated material",
        futureTestOnlyAllowedInPrinciple = true,
        forbiddenEvenForTestOnly = false,
    ),
    RedactionAssertions(
        "execute redaction assertions",
        futureTestOnlyAllowedInPrinciple = true,
        forbiddenEvenForTestOnly = false,
    ),
    ProviderSelfTestRoutingAssertions(
        "execute provider self-test routing assertions",
        futureTestOnlyAllowedInPrinciple = true,
        forbiddenEvenForTestOnly = false,
    ),
    PlatformRuntimeChecksInTestSourceSetsOnly(
        "execute platform runtime checks in test source sets only",
        futureTestOnlyAllowedInPrinciple = true,
        forbiddenEvenForTestOnly = false,
    ),
    ProductionProviderExecution(
        "production provider execution",
        futureTestOnlyAllowedInPrinciple = false,
        forbiddenEvenForTestOnly = true,
    ),
    ProductionProviderSelectionAuthorization(
        "production provider selection authorization",
        futureTestOnlyAllowedInPrinciple = false,
        forbiddenEvenForTestOnly = true,
    ),
    ProductionProviderSelectableTrue(
        "productionProviderSelectable true",
        futureTestOnlyAllowedInPrinciple = false,
        forbiddenEvenForTestOnly = true,
    ),
    ProductionVaultCreation(
        "production vault creation",
        futureTestOnlyAllowedInPrinciple = false,
        forbiddenEvenForTestOnly = true,
    ),
    ProductionVaultUnlock(
        "production vault unlock",
        futureTestOnlyAllowedInPrinciple = false,
        forbiddenEvenForTestOnly = true,
    ),
    ProductionVaultPersistence(
        "production vault persistence",
        futureTestOnlyAllowedInPrinciple = false,
        forbiddenEvenForTestOnly = true,
    ),
    ProductionSecureStorageSuccess(
        "production secure storage success",
        futureTestOnlyAllowedInPrinciple = false,
        forbiddenEvenForTestOnly = true,
    ),
    ProductionSecureMetadataSuccess(
        "production secure metadata success",
        futureTestOnlyAllowedInPrinciple = false,
        forbiddenEvenForTestOnly = true,
    ),
    RealCredentialInput(
        "real credential input",
        futureTestOnlyAllowedInPrinciple = false,
        forbiddenEvenForTestOnly = true,
    ),
    RealWalletMaterial(
        "real wallet material",
        futureTestOnlyAllowedInPrinciple = false,
        forbiddenEvenForTestOnly = true,
    ),
    RealUserStateDerivationInputs(
        "real user-state derivation inputs",
        futureTestOnlyAllowedInPrinciple = false,
        forbiddenEvenForTestOnly = true,
    ),
    RealBackendState(
        "real backend state",
        futureTestOnlyAllowedInPrinciple = false,
        forbiddenEvenForTestOnly = true,
    ),
    BdkWalletMaterial(
        "BDK wallet material",
        futureTestOnlyAllowedInPrinciple = false,
        forbiddenEvenForTestOnly = true,
    ),
    Mainnet(
        "mainnet",
        futureTestOnlyAllowedInPrinciple = false,
        forbiddenEvenForTestOnly = true,
    ),
}

enum class SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate(val label: String) {
    PrerequisiteAuditComplete("prerequisite audit complete"),
    SourceSetConfinementPolicyComplete("source-set confinement policy complete"),
    TestOnlyExecutorDesignReviewed("test-only executor design reviewed"),
    ProviderOperationAuthorizationStillBlocksProduction(
        "provider operation authorization still blocks production",
    ),
    ProviderSelectionStillDisabledProviderOnly("provider selection still disabled-provider-only"),
    ProductionProviderSelectableFalseAsserted("productionProviderSelectable false asserted"),
    PublicNonWalletVectorProvenanceComplete("public non-wallet vector provenance complete"),
    CanonicalSkaldVectorProvenanceComplete("canonical Skald vector provenance complete"),
    NegativeKatPlanComplete("negative KAT plan complete"),
    RedactionKatPlanComplete("redaction KAT plan complete"),
    RandomnessPolicyForTestOnlyMaterialComplete("randomness policy for test-only material complete"),
    NoWalletMaterialFixturePolicyComplete("no wallet-material fixture policy complete"),
    AndroidInstrumentationTargetingPlanComplete("Android instrumentation targeting plan complete"),
    LinuxJvmRuntimeTargetingPlanComplete("Linux/JVM runtime targeting plan complete"),
    TestResultNonAuthorizingPolicyComplete("test-result non-authorizing policy complete"),
    CiLocalOnlyLimitationsDocumented("CI/local-only limitations documented"),
    ReleaseMainnetNonAuthorizingPolicyComplete("release/mainnet non-authorizing policy complete"),
}

enum class SkaldVaultV1ProviderTestOnlyExecutableKatGateClassification(val label: String) {
    ModeledNonAuthorizing("modeled non-authorizing evidence"),
    PartiallyModeled("partially modeled evidence"),
    DocumentationOnly("documentation-only evidence"),
    FutureRequired("future required evidence"),
    BlockedByHardGate("blocked by hard gate"),
}

enum class SkaldVaultV1ProviderTestOnlyExecutableKatBlocker(val label: String) {
    NoTestOnlyExecutorImplementationInThisBranch("no test-only executor implementation in this branch"),
    ExecutableKatImplementationDeferred("executable KAT implementation deferred"),
    SourceSetConfinementNotImplemented("source-set confinement not implemented"),
    ProviderOperationAuthorizationStillBlocked("provider operation authorization still blocked"),
    ProviderSelectionDisabledProviderOnly("provider selection disabled-provider-only"),
    ProductionProviderSelectableFalse("productionProviderSelectable false"),
    ProviderImplementationMissing("provider implementation missing"),
    ProviderFactoryMissing("provider factory missing"),
    ProviderDispatcherMissing("provider dispatcher missing"),
    NonDisabledRegistryEntryMissing("non-disabled registry entry missing"),
    KdfCalibrationNotFinal("KDF calibration not final"),
    ProductionProviderAcceptanceIncomplete("production provider acceptance incomplete"),
    SecureStorageDisabled("secure storage disabled"),
    SecureMetadataDisabled("secure metadata disabled"),
    VaultLifecycleDisabled("vault lifecycle disabled"),
    PersistenceDisabled("persistence disabled"),
    TestOnlyEvidenceNonAuthorizing("test-only evidence non-authorizing"),
    WarningOnlyEvidenceNonAuthorizing("warning-only evidence non-authorizing"),
    UserConsentCannotOverride("user consent cannot override"),
    MainnetDisabled("mainnet disabled"),
}

enum class SkaldVaultV1ProviderTestOnlyExecutableKatMaterialClass(
    val label: String,
    val futureTestOnlyAllowedInPrinciple: Boolean,
) {
    PublicNonWalletVectors("public non-wallet vectors", futureTestOnlyAllowedInPrinciple = true),
    ExplicitTestOnlyGeneratedMaterial(
        "explicitly test-only generated material",
        futureTestOnlyAllowedInPrinciple = true,
    ),
    DeterministicNonWalletFixtures(
        "deterministic non-wallet fixtures",
        futureTestOnlyAllowedInPrinciple = true,
    ),
    RedactedDiagnostics("redacted diagnostics", futureTestOnlyAllowedInPrinciple = true),
    SyntheticSafeIds("synthetic safe IDs", futureTestOnlyAllowedInPrinciple = true),
    UserWalletMaterial("user wallet material", futureTestOnlyAllowedInPrinciple = false),
    RealSeeds("real seeds", futureTestOnlyAllowedInPrinciple = false),
    RealMnemonics("real mnemonics", futureTestOnlyAllowedInPrinciple = false),
    RealDescriptors("real descriptors", futureTestOnlyAllowedInPrinciple = false),
    RealXprvTprvWifMaterial("real xprv/tprv/WIF material", futureTestOnlyAllowedInPrinciple = false),
    NostrNsecValues("Nostr nsec values", futureTestOnlyAllowedInPrinciple = false),
    LightningCredentials("Lightning credentials", futureTestOnlyAllowedInPrinciple = false),
    CashuProofs("Cashu proofs", futureTestOnlyAllowedInPrinciple = false),
    BackendCredentials("backend credentials", futureTestOnlyAllowedInPrinciple = false),
    RealWalletLabels("real wallet labels", futureTestOnlyAllowedInPrinciple = false),
    RealUtxoLabels("real UTXO labels", futureTestOnlyAllowedInPrinciple = false),
    TransactionNotes("transaction notes", futureTestOnlyAllowedInPrinciple = false),
    BackendObservationMetadata("backend observation metadata", futureTestOnlyAllowedInPrinciple = false),
    SecureMetadataRecords("secure metadata records", futureTestOnlyAllowedInPrinciple = false),
    ProductionVaultRecords("production vault records", futureTestOnlyAllowedInPrinciple = false),
}

enum class SkaldVaultV1ProviderTestOnlyExecutableKatRedactionClass(val label: String) {
    PolicyIdsOnly("policy IDs only"),
    StatusLabelsOnly("status labels only"),
    SourceSetLabelsOnly("source-set labels only"),
    OperationLabelsOnly("operation labels only"),
    GateLabelsOnly("gate labels only"),
    BlockerLabelsOnly("blocker labels only"),
    MaterialClassLabelsOnly("material class labels only"),
    SafeIdsOnly("safe IDs only"),
    NoRawMaterial("no raw material"),
    NoProviderReferences("no provider references"),
    NoCryptoReferences("no crypto references"),
    NoDiagnosticPayloads("no diagnostic payloads"),
    NoFilesystemLocations("no filesystem locations"),
    NoStorageReferences("no storage references"),
    NoBackendReferences("no backend references"),
}

data class SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionCapability(
    val scopeDecisionModeled: Boolean,
    val futureTestOnlyPathAllowedInPrinciple: Boolean,
    val futurePermissionRequiresLaterBranch: Boolean,
    val canImplementTestOnlyExecutorNow: Boolean,
    val canRunTestOnlyProviderKatNow: Boolean,
    val canRunProductionProviderKatNow: Boolean,
    val canUseCommonMainExecution: Boolean,
    val canUseAndroidMainExecution: Boolean,
    val canUseDesktopMainExecution: Boolean,
    val canUseDesktopTestExecutionNow: Boolean,
    val canUseAndroidInstrumentedTestExecutionNow: Boolean,
    val canExecuteProviderOperationsNow: Boolean,
    val canExecuteRandomnessNow: Boolean,
    val canExecuteKdfNow: Boolean,
    val canExecuteAeadNow: Boolean,
    val canAuthorizeProviderSelection: Boolean,
    val canSetProductionProviderSelectable: Boolean,
    val canAuthorizeVaultCreation: Boolean,
    val canAuthorizeVaultUnlock: Boolean,
    val canAuthorizeVaultPersistence: Boolean,
    val canAuthorizeProductionSync: Boolean,
    val canAuthorizeMainnet: Boolean,
) {
    companion object {
        val Current = SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionCapability(
            scopeDecisionModeled = true,
            futureTestOnlyPathAllowedInPrinciple = true,
            futurePermissionRequiresLaterBranch = true,
            canImplementTestOnlyExecutorNow = false,
            canRunTestOnlyProviderKatNow = false,
            canRunProductionProviderKatNow = false,
            canUseCommonMainExecution = false,
            canUseAndroidMainExecution = false,
            canUseDesktopMainExecution = false,
            canUseDesktopTestExecutionNow = false,
            canUseAndroidInstrumentedTestExecutionNow = false,
            canExecuteProviderOperationsNow = false,
            canExecuteRandomnessNow = false,
            canExecuteKdfNow = false,
            canExecuteAeadNow = false,
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

data class SkaldVaultV1ProviderTestOnlyExecutableKatSourceSetScopeRow(
    val category: SkaldVaultV1ProviderTestOnlyExecutableKatSourceSetCategory,
    val futureScopeAllowedInPrinciple: Boolean,
    val currentExecutionAuthorized: Boolean,
    val productionSourceForbidden: Boolean,
    val blockers: Set<SkaldVaultV1ProviderTestOnlyExecutableKatBlocker>,
    val redactionClass: SkaldVaultV1ProviderTestOnlyExecutableKatRedactionClass,
)

data class SkaldVaultV1ProviderTestOnlyExecutableKatOperationScopeRow(
    val category: SkaldVaultV1ProviderTestOnlyExecutableKatOperationCategory,
    val futureTestOnlyAllowedInPrinciple: Boolean,
    val currentExecutionAuthorized: Boolean,
    val productionAllowed: Boolean,
    val forbiddenEvenForTestOnly: Boolean,
    val blockers: Set<SkaldVaultV1ProviderTestOnlyExecutableKatBlocker>,
    val redactionClass: SkaldVaultV1ProviderTestOnlyExecutableKatRedactionClass,
)

data class SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGateRow(
    val gate: SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate,
    val classification: SkaldVaultV1ProviderTestOnlyExecutableKatGateClassification,
    val currentEvidenceExists: Boolean,
    val requiredBeforeFutureExecutor: Boolean,
    val authorizesCurrentExecution: Boolean,
    val authorizesProduction: Boolean,
    val blockers: Set<SkaldVaultV1ProviderTestOnlyExecutableKatBlocker>,
    val safeGateId: String,
    val redactionClass: SkaldVaultV1ProviderTestOnlyExecutableKatRedactionClass,
)

data class SkaldVaultV1ProviderTestOnlyExecutableKatMaterialRuleRow(
    val materialClass: SkaldVaultV1ProviderTestOnlyExecutableKatMaterialClass,
    val futureTestOnlyAllowedInPrinciple: Boolean,
    val currentExecutionAuthorized: Boolean,
    val productionAllowed: Boolean,
    val blockers: Set<SkaldVaultV1ProviderTestOnlyExecutableKatBlocker>,
    val redactionClass: SkaldVaultV1ProviderTestOnlyExecutableKatRedactionClass,
)

data class SkaldVaultV1ProviderTestOnlyExecutableKatFutureWorkItem(
    val gate: SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate,
    val requiredBeforeAnyExecutorBranch: Boolean,
    val requiredBeforeProductionProviderSelection: Boolean,
    val authorizesCurrentExecution: Boolean,
    val safeWorkId: String,
)

data class SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionRequest(
    val includeFutureScope: Boolean,
    val warningOnlyEvidenceClaimed: Boolean,
    val userConsentOverrideRequested: Boolean,
    val releaseMainnetEvidenceClaimed: Boolean,
    val safeDecisionId: String,
) {
    override fun toString(): String =
        "SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionRequest(" +
            "includeFutureScope=$includeFutureScope, " +
            "warningOnlyEvidenceClaimed=$warningOnlyEvidenceClaimed, " +
            "userConsentOverrideRequested=$userConsentOverrideRequested, " +
            "releaseMainnetEvidenceClaimed=$releaseMainnetEvidenceClaimed, " +
            "safeDecisionId=<redacted>, " +
            "providerReference=<redacted>, " +
            "cryptoReference=<redacted>, " +
            "rawMaterial=<redacted>, " +
            "filesystemLocation=<redacted>, " +
            "backendReference=<redacted>" +
            ")"

    companion object {
        fun currentScope(
            includeFutureScope: Boolean = true,
            safeDecisionId: String = "current-test-only-executable-kat-scope-decision",
        ): SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionRequest =
            SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionRequest(
                includeFutureScope = includeFutureScope,
                warningOnlyEvidenceClaimed = false,
                userConsentOverrideRequested = false,
                releaseMainnetEvidenceClaimed = false,
                safeDecisionId = safeDecisionId,
            )
    }
}

data class SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionEvidence(
    val status: SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionStatus,
    val statuses: Set<SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionStatus>,
    val sourceSetRows: List<SkaldVaultV1ProviderTestOnlyExecutableKatSourceSetScopeRow>,
    val operationRows: List<SkaldVaultV1ProviderTestOnlyExecutableKatOperationScopeRow>,
    val evidenceGateRows: List<SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGateRow>,
    val materialRuleRows: List<SkaldVaultV1ProviderTestOnlyExecutableKatMaterialRuleRow>,
    val blockers: Set<SkaldVaultV1ProviderTestOnlyExecutableKatBlocker>,
    val redactionClasses: Set<SkaldVaultV1ProviderTestOnlyExecutableKatRedactionClass>,
    val disabledCapabilities: SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionCapability,
    val futureRequiredWork: List<SkaldVaultV1ProviderTestOnlyExecutableKatFutureWorkItem>,
    val scopeDecisionModeled: Boolean,
    val stillDisabled: Boolean,
    val futureTestOnlyPathAllowedInPrinciple: Boolean,
    val futureTestOnlyPathRequiresLaterBranch: Boolean,
    val currentBranchExecutionAuthorized: Boolean,
    val testOnlyExecutorImplementationPresent: Boolean,
    val productionExecutorAllowed: Boolean,
    val testOnlyKatResultsCanAuthorizeProductionProviderSelection: Boolean,
    val testOnlyKatResultsCanSetProductionProviderSelectable: Boolean,
    val testOnlyKatResultsCanAuthorizeVaultLifecycle: Boolean,
    val testOnlyKatResultsCanAuthorizeProductionSync: Boolean,
    val testOnlyKatResultsCanAuthorizeMainnet: Boolean,
    val providerSelectionDisabledProviderOnly: Boolean,
    val productionProviderSelectable: Boolean,
    val vaultCreationBlocked: Boolean,
    val vaultUnlockBlocked: Boolean,
    val vaultPersistenceBlocked: Boolean,
    val productionSyncBlocked: Boolean,
    val mainnetBlocked: Boolean,
)

sealed interface SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionResult<out T> {
    val value: T

    data class Blocked<out T>(
        override val value: T,
    ) : SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionResult<T>
}

data class SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionSummary(
    val policyId: String,
    val policyVersion: Int,
    val statuses: Set<SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionStatus>,
    val sourceSetCategories: Set<SkaldVaultV1ProviderTestOnlyExecutableKatSourceSetCategory>,
    val operationCategories: Set<SkaldVaultV1ProviderTestOnlyExecutableKatOperationCategory>,
    val evidenceGates: Set<SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate>,
    val materialClasses: Set<SkaldVaultV1ProviderTestOnlyExecutableKatMaterialClass>,
    val blockers: Set<SkaldVaultV1ProviderTestOnlyExecutableKatBlocker>,
    val redactionClasses: Set<SkaldVaultV1ProviderTestOnlyExecutableKatRedactionClass>,
    val capability: SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionCapability,
)

object SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionPolicy :
    SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecision {
    const val POLICY_ID: String =
        "skald-vault-v1-provider-test-only-executable-kat-scope-decision-v1"
    const val POLICY_VERSION: Int = 1

    override fun decide(
        request: SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionRequest,
    ): SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionResult.Blocked<
        SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionEvidence,
    > = evaluate(request)

    fun evaluate(
        request: SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionRequest =
            SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionRequest.currentScope(),
    ): SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionResult.Blocked<
        SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionEvidence,
    > =
        SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionResult.Blocked(
            currentDecisionEvidence(request),
        )

    fun currentPolicySummary(): SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionSummary =
        SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionSummary(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            statuses = SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionStatus.entries.toSet(),
            sourceSetCategories = SkaldVaultV1ProviderTestOnlyExecutableKatSourceSetCategory.entries.toSet(),
            operationCategories = SkaldVaultV1ProviderTestOnlyExecutableKatOperationCategory.entries.toSet(),
            evidenceGates = SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate.entries.toSet(),
            materialClasses = SkaldVaultV1ProviderTestOnlyExecutableKatMaterialClass.entries.toSet(),
            blockers = currentBlockers(),
            redactionClasses = SkaldVaultV1ProviderTestOnlyExecutableKatRedactionClass.entries.toSet(),
            capability = SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionCapability.Current,
        )

    fun currentDecisionEvidence(
        request: SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionRequest =
            SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionRequest.currentScope(),
    ): SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionEvidence {
        val sourceRows = currentSourceSetRows()
        val operationRows = currentOperationRows()
        val gateRows = currentEvidenceGateRows()
        val materialRows = currentMaterialRuleRows()
        val blockers = (
            currentBlockers() +
                sourceRows.flatMap { it.blockers } +
                operationRows.flatMap { it.blockers } +
                gateRows.flatMap { it.blockers } +
                materialRows.flatMap { it.blockers } +
                requestBlockers(request)
            ).toSet()
        return SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionEvidence(
            status = SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionStatus.StillDisabled,
            statuses = currentStatuses(),
            sourceSetRows = sourceRows,
            operationRows = operationRows,
            evidenceGateRows = gateRows,
            materialRuleRows = materialRows,
            blockers = blockers,
            redactionClasses = SkaldVaultV1ProviderTestOnlyExecutableKatRedactionClass.entries.toSet(),
            disabledCapabilities = SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionCapability.Current,
            futureRequiredWork = currentFutureRequiredWork(),
            scopeDecisionModeled = true,
            stillDisabled = true,
            futureTestOnlyPathAllowedInPrinciple = true,
            futureTestOnlyPathRequiresLaterBranch = true,
            currentBranchExecutionAuthorized = false,
            testOnlyExecutorImplementationPresent = false,
            productionExecutorAllowed = false,
            testOnlyKatResultsCanAuthorizeProductionProviderSelection = false,
            testOnlyKatResultsCanSetProductionProviderSelectable = false,
            testOnlyKatResultsCanAuthorizeVaultLifecycle = false,
            testOnlyKatResultsCanAuthorizeProductionSync = false,
            testOnlyKatResultsCanAuthorizeMainnet = false,
            providerSelectionDisabledProviderOnly = true,
            productionProviderSelectable = false,
            vaultCreationBlocked = true,
            vaultUnlockBlocked = true,
            vaultPersistenceBlocked = true,
            productionSyncBlocked = true,
            mainnetBlocked = true,
        )
    }

    fun currentStatuses(): Set<SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionStatus> =
        setOf(
            SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionStatus.ScopeDecisionModeled,
            SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionStatus.StillDisabled,
            SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionStatus
                .FutureTestOnlyPathPermittedInPrinciple,
            SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionStatus.CurrentBranchExecutionNotAuthorized,
            SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionStatus.TestOnlyExecutorMissing,
            SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionStatus.ProductionExecutorForbidden,
            SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionStatus.ProductionProviderSelectionForbidden,
            SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionStatus.ProductionProviderSelectableFalse,
            SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionStatus.VaultLifecycleBlocked,
            SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionStatus.PersistenceBlocked,
            SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionStatus.ProductionSyncBlocked,
            SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionStatus.MainnetBlocked,
        )

    fun currentBlockers(): Set<SkaldVaultV1ProviderTestOnlyExecutableKatBlocker> =
        setOf(
            SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.NoTestOnlyExecutorImplementationInThisBranch,
            SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.ExecutableKatImplementationDeferred,
            SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.SourceSetConfinementNotImplemented,
            SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.ProviderOperationAuthorizationStillBlocked,
            SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.ProviderSelectionDisabledProviderOnly,
            SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.ProductionProviderSelectableFalse,
            SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.ProviderImplementationMissing,
            SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.ProviderFactoryMissing,
            SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.ProviderDispatcherMissing,
            SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.NonDisabledRegistryEntryMissing,
            SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.KdfCalibrationNotFinal,
            SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.ProductionProviderAcceptanceIncomplete,
            SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.SecureStorageDisabled,
            SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.SecureMetadataDisabled,
            SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.VaultLifecycleDisabled,
            SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.PersistenceDisabled,
            SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.TestOnlyEvidenceNonAuthorizing,
            SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.WarningOnlyEvidenceNonAuthorizing,
            SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.UserConsentCannotOverride,
            SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.MainnetDisabled,
        )

    fun requestBlockers(
        request: SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecisionRequest,
    ): Set<SkaldVaultV1ProviderTestOnlyExecutableKatBlocker> =
        buildSet {
            if (request.warningOnlyEvidenceClaimed) {
                add(SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.WarningOnlyEvidenceNonAuthorizing)
            }
            if (request.userConsentOverrideRequested) {
                add(SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.UserConsentCannotOverride)
            }
            if (request.releaseMainnetEvidenceClaimed) {
                add(SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.MainnetDisabled)
            }
        }

    fun currentSourceSetRows(): List<SkaldVaultV1ProviderTestOnlyExecutableKatSourceSetScopeRow> =
        SkaldVaultV1ProviderTestOnlyExecutableKatSourceSetCategory.entries.map { category ->
            val productionForbidden = category.productionSource ||
                category == SkaldVaultV1ProviderTestOnlyExecutableKatSourceSetCategory
                    .BuildScriptsForbiddenExceptExplicitDependencyDeclarations
            SkaldVaultV1ProviderTestOnlyExecutableKatSourceSetScopeRow(
                category = category,
                futureScopeAllowedInPrinciple = category.futureScopeAllowedInPrinciple,
                currentExecutionAuthorized = false,
                productionSourceForbidden = productionForbidden,
                blockers = if (category.futureScopeAllowedInPrinciple && !category.productionSource) {
                    setOf(
                        SkaldVaultV1ProviderTestOnlyExecutableKatBlocker
                            .NoTestOnlyExecutorImplementationInThisBranch,
                        SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.SourceSetConfinementNotImplemented,
                        SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.TestOnlyEvidenceNonAuthorizing,
                    )
                } else {
                    setOf(
                        SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.SourceSetConfinementNotImplemented,
                        SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.ProviderSelectionDisabledProviderOnly,
                        SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.ProductionProviderSelectableFalse,
                    )
                },
                redactionClass =
                    SkaldVaultV1ProviderTestOnlyExecutableKatRedactionClass.SourceSetLabelsOnly,
            )
        }

    fun currentOperationRows(): List<SkaldVaultV1ProviderTestOnlyExecutableKatOperationScopeRow> =
        SkaldVaultV1ProviderTestOnlyExecutableKatOperationCategory.entries.map { category ->
            SkaldVaultV1ProviderTestOnlyExecutableKatOperationScopeRow(
                category = category,
                futureTestOnlyAllowedInPrinciple = category.futureTestOnlyAllowedInPrinciple,
                currentExecutionAuthorized = false,
                productionAllowed = false,
                forbiddenEvenForTestOnly = category.forbiddenEvenForTestOnly,
                blockers = operationBlockers(category),
                redactionClass =
                    SkaldVaultV1ProviderTestOnlyExecutableKatRedactionClass.OperationLabelsOnly,
            )
        }

    fun operationBlockers(
        category: SkaldVaultV1ProviderTestOnlyExecutableKatOperationCategory,
    ): Set<SkaldVaultV1ProviderTestOnlyExecutableKatBlocker> =
        if (category.futureTestOnlyAllowedInPrinciple) {
            setOf(
                SkaldVaultV1ProviderTestOnlyExecutableKatBlocker
                    .NoTestOnlyExecutorImplementationInThisBranch,
                SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.ExecutableKatImplementationDeferred,
                SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.TestOnlyEvidenceNonAuthorizing,
            )
        } else {
            setOf(
                SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.ProviderOperationAuthorizationStillBlocked,
                SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.ProviderSelectionDisabledProviderOnly,
                SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.ProductionProviderSelectableFalse,
                SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.VaultLifecycleDisabled,
                SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.MainnetDisabled,
            )
        }

    fun currentEvidenceGateRows(): List<SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGateRow> =
        SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate.entries.map { gate ->
            SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGateRow(
                gate = gate,
                classification = evidenceGateClassification(gate),
                currentEvidenceExists = evidenceGateCurrentEvidenceExists(gate),
                requiredBeforeFutureExecutor = true,
                authorizesCurrentExecution = false,
                authorizesProduction = false,
                blockers = evidenceGateBlockers(gate),
                safeGateId = "scope-gate-${gate.name}",
                redactionClass = SkaldVaultV1ProviderTestOnlyExecutableKatRedactionClass.GateLabelsOnly,
            )
        }

    fun evidenceGateClassification(
        gate: SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate,
    ): SkaldVaultV1ProviderTestOnlyExecutableKatGateClassification =
        when (gate) {
            SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate.PrerequisiteAuditComplete,
            SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate
                .ProviderOperationAuthorizationStillBlocksProduction,
            SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate.ProviderSelectionStillDisabledProviderOnly,
            SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate.ProductionProviderSelectableFalseAsserted,
            SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate.TestResultNonAuthorizingPolicyComplete,
            SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate
                .ReleaseMainnetNonAuthorizingPolicyComplete,
            -> SkaldVaultV1ProviderTestOnlyExecutableKatGateClassification.ModeledNonAuthorizing
            SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate.SourceSetConfinementPolicyComplete,
            SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate
                .PublicNonWalletVectorProvenanceComplete,
            SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate.CanonicalSkaldVectorProvenanceComplete,
            SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate.RandomnessPolicyForTestOnlyMaterialComplete,
            -> SkaldVaultV1ProviderTestOnlyExecutableKatGateClassification.PartiallyModeled
            SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate.AndroidInstrumentationTargetingPlanComplete,
            SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate.LinuxJvmRuntimeTargetingPlanComplete,
            SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate.CiLocalOnlyLimitationsDocumented,
            -> SkaldVaultV1ProviderTestOnlyExecutableKatGateClassification.DocumentationOnly
            SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate.TestOnlyExecutorDesignReviewed,
            SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate.NegativeKatPlanComplete,
            SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate.RedactionKatPlanComplete,
            SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate.NoWalletMaterialFixturePolicyComplete,
            -> SkaldVaultV1ProviderTestOnlyExecutableKatGateClassification.FutureRequired
        }

    fun evidenceGateCurrentEvidenceExists(
        gate: SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate,
    ): Boolean =
        evidenceGateClassification(gate) !=
            SkaldVaultV1ProviderTestOnlyExecutableKatGateClassification.FutureRequired

    fun evidenceGateBlockers(
        gate: SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate,
    ): Set<SkaldVaultV1ProviderTestOnlyExecutableKatBlocker> =
        when (gate) {
            SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate.TestOnlyExecutorDesignReviewed ->
                setOf(
                    SkaldVaultV1ProviderTestOnlyExecutableKatBlocker
                        .NoTestOnlyExecutorImplementationInThisBranch,
                    SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.ExecutableKatImplementationDeferred,
                )
            SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate.SourceSetConfinementPolicyComplete ->
                setOf(SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.SourceSetConfinementNotImplemented)
            SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate
                .ProviderOperationAuthorizationStillBlocksProduction ->
                setOf(
                    SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.ProviderOperationAuthorizationStillBlocked,
                )
            SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate.ProviderSelectionStillDisabledProviderOnly ->
                setOf(SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.ProviderSelectionDisabledProviderOnly)
            SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate.ProductionProviderSelectableFalseAsserted ->
                setOf(SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.ProductionProviderSelectableFalse)
            SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate.CanonicalSkaldVectorProvenanceComplete,
            SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate.PublicNonWalletVectorProvenanceComplete,
            SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate.NegativeKatPlanComplete,
            SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate.RedactionKatPlanComplete,
            SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate.RandomnessPolicyForTestOnlyMaterialComplete,
            SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate.NoWalletMaterialFixturePolicyComplete,
            SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate.AndroidInstrumentationTargetingPlanComplete,
            SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate.LinuxJvmRuntimeTargetingPlanComplete,
            SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate.TestResultNonAuthorizingPolicyComplete,
            SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate.CiLocalOnlyLimitationsDocumented,
            -> setOf(SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.TestOnlyEvidenceNonAuthorizing)
            SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate
                .ReleaseMainnetNonAuthorizingPolicyComplete ->
                setOf(SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.MainnetDisabled)
            SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate.PrerequisiteAuditComplete ->
                setOf(SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.ExecutableKatImplementationDeferred)
        }

    fun currentMaterialRuleRows(): List<SkaldVaultV1ProviderTestOnlyExecutableKatMaterialRuleRow> =
        SkaldVaultV1ProviderTestOnlyExecutableKatMaterialClass.entries.map { materialClass ->
            SkaldVaultV1ProviderTestOnlyExecutableKatMaterialRuleRow(
                materialClass = materialClass,
                futureTestOnlyAllowedInPrinciple = materialClass.futureTestOnlyAllowedInPrinciple,
                currentExecutionAuthorized = false,
                productionAllowed = false,
                blockers = if (materialClass.futureTestOnlyAllowedInPrinciple) {
                    setOf(
                        SkaldVaultV1ProviderTestOnlyExecutableKatBlocker
                            .NoTestOnlyExecutorImplementationInThisBranch,
                        SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.TestOnlyEvidenceNonAuthorizing,
                    )
                } else {
                    setOf(
                        SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.ProviderOperationAuthorizationStillBlocked,
                        SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.VaultLifecycleDisabled,
                        SkaldVaultV1ProviderTestOnlyExecutableKatBlocker.PersistenceDisabled,
                    )
                },
                redactionClass =
                    SkaldVaultV1ProviderTestOnlyExecutableKatRedactionClass.MaterialClassLabelsOnly,
            )
        }

    fun currentFutureRequiredWork(): List<SkaldVaultV1ProviderTestOnlyExecutableKatFutureWorkItem> =
        SkaldVaultV1ProviderTestOnlyExecutableKatEvidenceGate.entries.map { gate ->
            SkaldVaultV1ProviderTestOnlyExecutableKatFutureWorkItem(
                gate = gate,
                requiredBeforeAnyExecutorBranch = true,
                requiredBeforeProductionProviderSelection = true,
                authorizesCurrentExecution = false,
                safeWorkId = "future-work-${gate.name}",
            )
        }
}
