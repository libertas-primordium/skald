package com.libertasprimordium.skald.security

data class SkaldVaultV1TestOnlyProviderKatExecutorReadinessId(val value: String)

data class SkaldVaultV1TestOnlyProviderKatExecutorReadinessSafeLabel(val value: String)

enum class SkaldVaultV1TestOnlyProviderKatExecutorReadinessStatus(val label: String) {
    ReadinessGateModeled("readiness gate modeled"),
    StillDisabled("still disabled"),
    NotReadyForExecutorImplementation("not ready for executor implementation"),
    ExecutorImplementationDeferred("executor implementation deferred"),
    ExecutorSurfaceUnavailable("executor surface unavailable"),
    SourceSetPolicyModeled("source-set policy modeled"),
    SourceSetPolicyNotSufficientForImplementation("source-set policy not sufficient for implementation"),
    ContractModeled("contract modeled"),
    ContractNotSufficientForImplementation("contract not sufficient for implementation"),
    VectorCatalogModeled("vector catalog modeled"),
    VectorCatalogNotSufficientForImplementation("vector catalog not sufficient for implementation"),
    RedactionPolicyModeled("redaction policy modeled"),
    RedactionPolicyNotExecutable("redaction policy not executable"),
    MaterialRestrictionsModeled("material restrictions modeled"),
    ProductionAuthorizationBlocked("production authorization blocked"),
    MainnetBlocked("mainnet blocked"),
}

enum class SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource(val label: String) {
    ExecutableKatDecisionGate("executable KAT decision gate"),
    PrerequisiteAudit("prerequisite audit"),
    TestOnlyScopeDecision("test-only scope decision"),
    ExecutorContract("executor contract"),
    VectorCatalog("vector catalog"),
    ProviderKatExecutionIsolation("provider KAT execution isolation"),
    ProviderKatContract("provider KAT contract"),
    ProviderSelectionBoundary("provider selection boundary"),
    ProviderRegistryIsolation("provider registry isolation"),
    ProviderFactoryIsolation("provider factory isolation"),
    ProviderOperationDispatchIsolation("provider operation dispatch isolation"),
    RuntimeRandomnessPolicy("runtime randomness policy"),
    Argon2idCalibrationPolicy("Argon2id calibration policy"),
    SecureStorageBoundary("secure storage boundary"),
    SecureMetadataBoundary("secure metadata boundary"),
    EncryptedVaultReadinessPolicy("encrypted vault readiness policy"),
    ProductionProviderAcceptanceContract("production provider acceptance contract"),
}

enum class SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement(val label: String) {
    DecisionGateExists("decision gate exists"),
    PrerequisiteAuditExists("prerequisite audit exists"),
    TestOnlyScopeDecisionExists("test-only scope decision exists"),
    ExecutorContractExists("executor contract exists"),
    VectorCatalogExists("vector catalog exists"),
    SourceSetAllowlistFinalized("source-set allowlist finalized"),
    ProductionSourceSetDenylistFinalized("production source-set denylist finalized"),
    FutureAllowedInputLabelsFinalized("future allowed input labels finalized"),
    ForbiddenInputLabelsFinalized("forbidden input labels finalized"),
    FutureAllowedOperationLabelsFinalized("future allowed operation labels finalized"),
    ForbiddenOperationLabelsFinalized("forbidden operation labels finalized"),
    PositiveVectorReferencesCataloged("positive vector references cataloged"),
    NegativeVectorReferencesCataloged("negative vector references cataloged"),
    RedactionVectorReferencesCataloged("redaction vector references cataloged"),
    PlatformCheckReferencesCataloged("platform check references cataloged"),
    ProvenancePolicyCataloged("provenance policy cataloged"),
    NoRawVectorMaterialInCommonMain("no raw vector material in commonMain"),
    NoWalletLikeFixtures("no wallet-like fixtures"),
    ResultRedactionPolicyCataloged("result redaction policy cataloged"),
    ResultNonAuthorizationPolicyCataloged("result non-authorization policy cataloged"),
    ProviderSelectionIsolationAsserted("provider selection isolation asserted"),
    ProductionProviderSelectableFalseAsserted("productionProviderSelectable false asserted"),
    VaultLifecycleRemainsBlocked("vault lifecycle remains blocked"),
    PersistenceRemainsBlocked("persistence remains blocked"),
    ProductionSyncRemainsBlocked("production sync remains blocked"),
    MainnetRemainsBlocked("mainnet remains blocked"),
    SourceGuardsUpdated("source guards updated"),
    FutureExecutorImplementationBranchRequired("future executor implementation branch required"),
}

enum class SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification(val label: String) {
    SatisfiedModelOnly("satisfied model-only"),
    PartiallySatisfiedModelOnly("partially satisfied model-only"),
    DocumentationOnly("documentation-only"),
    TestOnly("test-only"),
    Missing("missing"),
    Blocked("blocked"),
    NotSufficientForImplementation("not sufficient for implementation"),
    FutureBranchRequired("future branch required"),
    NonAuthorizing("non-authorizing"),
}

enum class SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker(val label: String) {
    ExecutorImplementationBranchNotAuthorized("executor implementation branch not authorized"),
    NoExecutorImplementation("no executor implementation"),
    NoRunnableExecutorSurface("no runnable executor surface"),
    SourceSetImplementationReviewMissing("source-set implementation review missing"),
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

enum class SkaldVaultV1TestOnlyProviderKatExecutorReadinessRedactionClass(val label: String) {
    PolicyIdsOnly("policy IDs only"),
    ReadinessIdsOnly("readiness IDs only"),
    EvidenceSourceLabelsOnly("evidence-source labels only"),
    RequirementLabelsOnly("requirement labels only"),
    ClassificationLabelsOnly("classification labels only"),
    BlockerLabelsOnly("blocker labels only"),
    GuidanceLabelsOnly("guidance labels only"),
    SafeLabelsOnly("safe labels only"),
    NoRawMaterial("no raw material"),
    NoRawInput("no raw input"),
    NoRawOutput("no raw output"),
    NoKeyMaterial("no key material"),
    NoClearPayload("no clear payload"),
    NoEncryptedPayload("no encrypted payload"),
    NoProviderReferences("no provider references"),
    NoCryptoReferences("no crypto references"),
    NoFileLocations("no file locations"),
    NoStorageReferences("no storage references"),
    NoBackendReferences("no backend references"),
    NoDiagnosticPayloads("no diagnostic payloads"),
}

enum class SkaldVaultV1TestOnlyProviderKatExecutorReadinessFutureBranchGuidance(val label: String) {
    SourceSetSpecificExecutorDesign("source-set-specific executor design"),
    TestOnlyProviderIdentityDecision("test-only provider identity decision"),
    TestOnlyProviderImplementationDecision("test-only provider implementation decision"),
    TestOnlyVectorMaterialPlacementDecision("test-only vector material placement decision"),
    TestResultRedactionImplementation("test result redaction implementation"),
    NonAuthorizationEnforcementTest("non-authorization enforcement test"),
    AndroidRuntimeExecutorTargetingPlan("Android runtime executor-targeting plan"),
    LinuxJvmExecutorTargetingPlan("Linux/JVM executor-targeting plan"),
    CiLocalExecutionPolicy("CI/local execution policy"),
    NoWalletMaterialFixtureGuard("no-wallet-material fixture guard"),
}

data class SkaldVaultV1TestOnlyProviderKatExecutorReadinessCapability(
    val readinessGateModeled: Boolean,
    val readyForExecutorImplementationNow: Boolean,
    val executorImplementationAuthorizedNow: Boolean,
    val executorCallableNow: Boolean,
    val canAddRunnableInterfaceNow: Boolean,
    val canUseDesktopTestExecutionNow: Boolean,
    val canUseAndroidInstrumentedTestExecutionNow: Boolean,
    val canUseCommonMainExecution: Boolean,
    val canUseAndroidMainExecution: Boolean,
    val canUseDesktopMainExecution: Boolean,
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
        val Current = SkaldVaultV1TestOnlyProviderKatExecutorReadinessCapability(
            readinessGateModeled = true,
            readyForExecutorImplementationNow = false,
            executorImplementationAuthorizedNow = false,
            executorCallableNow = false,
            canAddRunnableInterfaceNow = false,
            canUseDesktopTestExecutionNow = false,
            canUseAndroidInstrumentedTestExecutionNow = false,
            canUseCommonMainExecution = false,
            canUseAndroidMainExecution = false,
            canUseDesktopMainExecution = false,
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

data class SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirementRow(
    val requirement: SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement,
    val evidenceSources: Set<SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource>,
    val classification: SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification,
    val modeledEvidenceExists: Boolean,
    val implementationSufficient: Boolean,
    val currentBranchAuthorizesImplementation: Boolean,
    val canAuthorizeProduction: Boolean,
    val blockers: Set<SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker>,
    val redactionClass: SkaldVaultV1TestOnlyProviderKatExecutorReadinessRedactionClass,
    val safeLabel: SkaldVaultV1TestOnlyProviderKatExecutorReadinessSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSourceRow(
    val evidenceSource: SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource,
    val classification: SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification,
    val modeledEvidenceExists: Boolean,
    val implementationAuthorization: Boolean,
    val productionAuthorization: Boolean,
    val blockers: Set<SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker>,
    val redactionClass: SkaldVaultV1TestOnlyProviderKatExecutorReadinessRedactionClass,
)

data class SkaldVaultV1TestOnlyProviderKatExecutorReadinessFutureBranchGuidanceRow(
    val guidance: SkaldVaultV1TestOnlyProviderKatExecutorReadinessFutureBranchGuidance,
    val requiredBeforeExecutorImplementationBranch: Boolean,
    val completedInCurrentBranch: Boolean,
    val canAuthorizeCurrentImplementation: Boolean,
    val safeLabel: SkaldVaultV1TestOnlyProviderKatExecutorReadinessSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequest(
    val includePriorModelEvidence: Boolean,
    val warningOnlyEvidenceClaimed: Boolean,
    val userConsentOverrideRequested: Boolean,
    val releaseEvidenceClaimed: Boolean,
    val safeReadinessId: String,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequest(" +
            "includePriorModelEvidence=$includePriorModelEvidence, " +
            "warningOnlyEvidenceClaimed=$warningOnlyEvidenceClaimed, " +
            "userConsentOverrideRequested=$userConsentOverrideRequested, " +
            "releaseEvidenceClaimed=$releaseEvidenceClaimed, " +
            "safeReadinessId=<redacted>, " +
            "rawMaterial=<redacted>, " +
            "payload=<redacted>, " +
            "providerReference=<redacted>, " +
            "cryptoReference=<redacted>, " +
            "fileLocation=<redacted>, " +
            "storageReference=<redacted>, " +
            "backendReference=<redacted>" +
            ")"

    companion object {
        fun currentReadiness(
            includePriorModelEvidence: Boolean = true,
            safeReadinessId: String = "current-test-only-provider-kat-executor-readiness-gate",
        ): SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequest =
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequest(
                includePriorModelEvidence = includePriorModelEvidence,
                warningOnlyEvidenceClaimed = false,
                userConsentOverrideRequested = false,
                releaseEvidenceClaimed = false,
                safeReadinessId = safeReadinessId,
            )
    }
}

data class SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidence(
    val status: SkaldVaultV1TestOnlyProviderKatExecutorReadinessStatus,
    val statuses: Set<SkaldVaultV1TestOnlyProviderKatExecutorReadinessStatus>,
    val evidenceSourceRows: List<SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSourceRow>,
    val requirementRows: List<SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirementRow>,
    val blockers: Set<SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker>,
    val redactionClasses: Set<SkaldVaultV1TestOnlyProviderKatExecutorReadinessRedactionClass>,
    val disabledCapabilities: SkaldVaultV1TestOnlyProviderKatExecutorReadinessCapability,
    val futureBranchGuidance: List<SkaldVaultV1TestOnlyProviderKatExecutorReadinessFutureBranchGuidanceRow>,
    val readinessGateModeled: Boolean,
    val readyForExecutorImplementationNow: Boolean,
    val executorImplementationAuthorizedNow: Boolean,
    val executorCallableNow: Boolean,
    val canAddRunnableInterfaceNow: Boolean,
    val sourceSetPolicyModeled: Boolean,
    val sourceSetPolicySufficientForImplementation: Boolean,
    val futureInputCategoriesModeled: Boolean,
    val futureOperationCategoriesModeled: Boolean,
    val vectorReferencesCataloged: Boolean,
    val negativeCasesCataloged: Boolean,
    val redactionRulesCataloged: Boolean,
    val materialRestrictionsCataloged: Boolean,
    val resultNonAuthorizationRulesModeled: Boolean,
    val providerSelectionDisabledProviderOnly: Boolean,
    val productionProviderSelectable: Boolean,
    val vaultLifecycleBlocked: Boolean,
    val persistenceBlocked: Boolean,
    val productionSyncBlocked: Boolean,
    val mainnetBlocked: Boolean,
)

sealed interface SkaldVaultV1TestOnlyProviderKatExecutorReadinessGateResult<out T> {
    val value: T

    data class Blocked<out T>(
        override val value: T,
    ) : SkaldVaultV1TestOnlyProviderKatExecutorReadinessGateResult<T>
}

data class SkaldVaultV1TestOnlyProviderKatExecutorReadinessSummary(
    val policyId: String,
    val policyVersion: Int,
    val statuses: Set<SkaldVaultV1TestOnlyProviderKatExecutorReadinessStatus>,
    val evidenceSources: Set<SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource>,
    val requirements: Set<SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement>,
    val classifications: Set<SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification>,
    val blockers: Set<SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker>,
    val redactionClasses: Set<SkaldVaultV1TestOnlyProviderKatExecutorReadinessRedactionClass>,
    val futureBranchGuidance: Set<SkaldVaultV1TestOnlyProviderKatExecutorReadinessFutureBranchGuidance>,
    val capability: SkaldVaultV1TestOnlyProviderKatExecutorReadinessCapability,
)

object SkaldVaultV1TestOnlyProviderKatExecutorReadinessGatePolicy {
    const val POLICY_ID: String = "skald-vault-v1-test-only-provider-kat-executor-readiness-gate-v1"
    const val POLICY_VERSION: Int = 1

    fun evaluateReadiness(
        request: SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequest =
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequest.currentReadiness(),
    ): SkaldVaultV1TestOnlyProviderKatExecutorReadinessGateResult.Blocked<
        SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidence,
    > =
        SkaldVaultV1TestOnlyProviderKatExecutorReadinessGateResult.Blocked(
            currentReadinessEvidence(request),
        )

    fun currentPolicySummary(): SkaldVaultV1TestOnlyProviderKatExecutorReadinessSummary =
        SkaldVaultV1TestOnlyProviderKatExecutorReadinessSummary(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            statuses = SkaldVaultV1TestOnlyProviderKatExecutorReadinessStatus.entries.toSet(),
            evidenceSources = SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.entries.toSet(),
            requirements = SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.entries.toSet(),
            classifications = SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.entries.toSet(),
            blockers = currentBlockers(),
            redactionClasses = SkaldVaultV1TestOnlyProviderKatExecutorReadinessRedactionClass.entries.toSet(),
            futureBranchGuidance =
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessFutureBranchGuidance.entries.toSet(),
            capability = SkaldVaultV1TestOnlyProviderKatExecutorReadinessCapability.Current,
        )

    fun currentReadinessEvidence(
        request: SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequest =
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequest.currentReadiness(),
    ): SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidence {
        val sourceRows = currentEvidenceSourceRows()
        val requirementRows = currentRequirementRows()
        val blockers = (
            currentBlockers() +
                sourceRows.flatMap { it.blockers } +
                requirementRows.flatMap { it.blockers } +
                requestBlockers(request)
            ).toSet()
        return SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidence(
            status = SkaldVaultV1TestOnlyProviderKatExecutorReadinessStatus.StillDisabled,
            statuses = currentStatuses(),
            evidenceSourceRows = sourceRows,
            requirementRows = requirementRows,
            blockers = blockers,
            redactionClasses = SkaldVaultV1TestOnlyProviderKatExecutorReadinessRedactionClass.entries.toSet(),
            disabledCapabilities = SkaldVaultV1TestOnlyProviderKatExecutorReadinessCapability.Current,
            futureBranchGuidance = currentFutureBranchGuidanceRows(),
            readinessGateModeled = true,
            readyForExecutorImplementationNow = false,
            executorImplementationAuthorizedNow = false,
            executorCallableNow = false,
            canAddRunnableInterfaceNow = false,
            sourceSetPolicyModeled = true,
            sourceSetPolicySufficientForImplementation = false,
            futureInputCategoriesModeled = true,
            futureOperationCategoriesModeled = true,
            vectorReferencesCataloged = true,
            negativeCasesCataloged = true,
            redactionRulesCataloged = true,
            materialRestrictionsCataloged = true,
            resultNonAuthorizationRulesModeled = true,
            providerSelectionDisabledProviderOnly = true,
            productionProviderSelectable = false,
            vaultLifecycleBlocked = true,
            persistenceBlocked = true,
            productionSyncBlocked = true,
            mainnetBlocked = true,
        )
    }

    fun currentStatuses(): Set<SkaldVaultV1TestOnlyProviderKatExecutorReadinessStatus> =
        setOf(
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessStatus.ReadinessGateModeled,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessStatus.StillDisabled,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessStatus.NotReadyForExecutorImplementation,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessStatus.ExecutorImplementationDeferred,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessStatus.ExecutorSurfaceUnavailable,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessStatus.SourceSetPolicyModeled,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessStatus.SourceSetPolicyNotSufficientForImplementation,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessStatus.ContractModeled,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessStatus.ContractNotSufficientForImplementation,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessStatus.VectorCatalogModeled,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessStatus.VectorCatalogNotSufficientForImplementation,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessStatus.RedactionPolicyModeled,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessStatus.RedactionPolicyNotExecutable,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessStatus.MaterialRestrictionsModeled,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessStatus.ProductionAuthorizationBlocked,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessStatus.MainnetBlocked,
        )

    fun currentBlockers(): Set<SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker> =
        setOf(
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.ExecutorImplementationBranchNotAuthorized,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.NoExecutorImplementation,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.NoRunnableExecutorSurface,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.SourceSetImplementationReviewMissing,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.ProviderImplementationMissing,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.ProviderFactoryMissing,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.ProviderDispatcherMissing,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.NonDisabledRegistryEntryMissing,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.ProviderSelectionDisabledProviderOnly,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.ProductionProviderSelectableFalse,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.ProviderOperationAuthorizationBlocked,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.RuntimeRandomnessAuthorizationBlocked,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.KdfCalibrationNonFinal,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.ProductionProviderAcceptanceIncomplete,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.SecureStorageDisabled,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.SecureMetadataDisabled,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.VaultLifecycleDisabled,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.PersistenceDisabled,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.ProductionSyncDisabled,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.TestOnlyEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.WarningOnlyEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.UserConsentCannotOverride,
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.MainnetDisabled,
        )

    fun requestBlockers(
        request: SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequest,
    ): Set<SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker> =
        buildSet {
            if (request.warningOnlyEvidenceClaimed) {
                add(SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.WarningOnlyEvidenceNonAuthorizing)
            }
            if (request.userConsentOverrideRequested) {
                add(SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.UserConsentCannotOverride)
            }
            if (request.releaseEvidenceClaimed) {
                add(SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.MainnetDisabled)
                add(SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.ProductionSyncDisabled)
            }
        }

    fun currentEvidenceSourceRows(): List<SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSourceRow> =
        listOf(
            sourceRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.ExecutableKatDecisionGate,
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.SatisfiedModelOnly,
            ),
            sourceRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.PrerequisiteAudit,
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.SatisfiedModelOnly,
            ),
            sourceRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.TestOnlyScopeDecision,
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.SatisfiedModelOnly,
            ),
            sourceRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.ExecutorContract,
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.SatisfiedModelOnly,
            ),
            sourceRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.VectorCatalog,
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.SatisfiedModelOnly,
            ),
            sourceRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.ProviderKatExecutionIsolation,
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.SatisfiedModelOnly,
            ),
            sourceRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.ProviderKatContract,
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.PartiallySatisfiedModelOnly,
            ),
            sourceRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.ProviderSelectionBoundary,
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.SatisfiedModelOnly,
            ),
            sourceRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.ProviderRegistryIsolation,
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.SatisfiedModelOnly,
            ),
            sourceRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.ProviderFactoryIsolation,
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.SatisfiedModelOnly,
            ),
            sourceRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.ProviderOperationDispatchIsolation,
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.SatisfiedModelOnly,
            ),
            sourceRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.RuntimeRandomnessPolicy,
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.PartiallySatisfiedModelOnly,
            ),
            sourceRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.Argon2idCalibrationPolicy,
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.PartiallySatisfiedModelOnly,
            ),
            sourceRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.SecureStorageBoundary,
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.Blocked,
            ),
            sourceRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.SecureMetadataBoundary,
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.Blocked,
            ),
            sourceRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.EncryptedVaultReadinessPolicy,
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.NonAuthorizing,
            ),
            sourceRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.ProductionProviderAcceptanceContract,
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.DocumentationOnly,
            ),
        )

    fun currentRequirementRows(): List<SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirementRow> =
        listOf(
            requirementRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.DecisionGateExists,
                setOf(SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.ExecutableKatDecisionGate),
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.SatisfiedModelOnly,
            ),
            requirementRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.PrerequisiteAuditExists,
                setOf(SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.PrerequisiteAudit),
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.SatisfiedModelOnly,
            ),
            requirementRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.TestOnlyScopeDecisionExists,
                setOf(SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.TestOnlyScopeDecision),
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.SatisfiedModelOnly,
            ),
            requirementRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.ExecutorContractExists,
                setOf(SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.ExecutorContract),
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.SatisfiedModelOnly,
            ),
            requirementRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.VectorCatalogExists,
                setOf(SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.VectorCatalog),
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.SatisfiedModelOnly,
            ),
            requirementRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.SourceSetAllowlistFinalized,
                setOf(SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.TestOnlyScopeDecision),
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.NotSufficientForImplementation,
            ),
            requirementRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.ProductionSourceSetDenylistFinalized,
                setOf(
                    SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.TestOnlyScopeDecision,
                    SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.ExecutorContract,
                ),
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.NotSufficientForImplementation,
            ),
            requirementRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.FutureAllowedInputLabelsFinalized,
                setOf(SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.ExecutorContract),
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.SatisfiedModelOnly,
            ),
            requirementRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.ForbiddenInputLabelsFinalized,
                setOf(SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.ExecutorContract),
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.SatisfiedModelOnly,
            ),
            requirementRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.FutureAllowedOperationLabelsFinalized,
                setOf(SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.ExecutorContract),
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.SatisfiedModelOnly,
            ),
            requirementRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.ForbiddenOperationLabelsFinalized,
                setOf(SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.ExecutorContract),
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.SatisfiedModelOnly,
            ),
            requirementRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.PositiveVectorReferencesCataloged,
                setOf(SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.VectorCatalog),
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.SatisfiedModelOnly,
            ),
            requirementRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.NegativeVectorReferencesCataloged,
                setOf(SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.VectorCatalog),
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.SatisfiedModelOnly,
            ),
            requirementRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.RedactionVectorReferencesCataloged,
                setOf(SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.VectorCatalog),
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.SatisfiedModelOnly,
            ),
            requirementRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.PlatformCheckReferencesCataloged,
                setOf(SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.VectorCatalog),
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.SatisfiedModelOnly,
            ),
            requirementRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.ProvenancePolicyCataloged,
                setOf(SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.VectorCatalog),
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.SatisfiedModelOnly,
            ),
            requirementRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.NoRawVectorMaterialInCommonMain,
                setOf(SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.VectorCatalog),
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.SatisfiedModelOnly,
            ),
            requirementRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.NoWalletLikeFixtures,
                setOf(SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.VectorCatalog),
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.SatisfiedModelOnly,
            ),
            requirementRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.ResultRedactionPolicyCataloged,
                setOf(SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.ExecutorContract),
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.SatisfiedModelOnly,
            ),
            requirementRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.ResultNonAuthorizationPolicyCataloged,
                setOf(SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.ExecutorContract),
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.SatisfiedModelOnly,
            ),
            requirementRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.ProviderSelectionIsolationAsserted,
                setOf(
                    SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.ProviderSelectionBoundary,
                    SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.ProviderRegistryIsolation,
                ),
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.SatisfiedModelOnly,
            ),
            requirementRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.ProductionProviderSelectableFalseAsserted,
                setOf(SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.ProviderSelectionBoundary),
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.SatisfiedModelOnly,
            ),
            requirementRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.VaultLifecycleRemainsBlocked,
                setOf(SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.EncryptedVaultReadinessPolicy),
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.Blocked,
            ),
            requirementRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.PersistenceRemainsBlocked,
                setOf(
                    SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.SecureStorageBoundary,
                    SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.SecureMetadataBoundary,
                ),
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.Blocked,
            ),
            requirementRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.ProductionSyncRemainsBlocked,
                setOf(SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.EncryptedVaultReadinessPolicy),
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.Blocked,
            ),
            requirementRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.MainnetRemainsBlocked,
                setOf(SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.ProductionProviderAcceptanceContract),
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.Blocked,
            ),
            requirementRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.SourceGuardsUpdated,
                setOf(SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.VectorCatalog),
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.PartiallySatisfiedModelOnly,
            ),
            requirementRow(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.FutureExecutorImplementationBranchRequired,
                setOf(
                    SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.TestOnlyScopeDecision,
                    SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.ExecutorContract,
                    SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource.VectorCatalog,
                ),
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification.FutureBranchRequired,
            ),
        )

    fun currentFutureBranchGuidanceRows():
        List<SkaldVaultV1TestOnlyProviderKatExecutorReadinessFutureBranchGuidanceRow> =
        SkaldVaultV1TestOnlyProviderKatExecutorReadinessFutureBranchGuidance.entries.map { guidance ->
            SkaldVaultV1TestOnlyProviderKatExecutorReadinessFutureBranchGuidanceRow(
                guidance = guidance,
                requiredBeforeExecutorImplementationBranch = true,
                completedInCurrentBranch = false,
                canAuthorizeCurrentImplementation = false,
                safeLabel = SkaldVaultV1TestOnlyProviderKatExecutorReadinessSafeLabel(guidance.label),
            )
        }

    fun sourceRow(
        source: SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource,
        classification: SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification,
    ): SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSourceRow =
        SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSourceRow(
            evidenceSource = source,
            classification = classification,
            modeledEvidenceExists = true,
            implementationAuthorization = false,
            productionAuthorization = false,
            blockers = setOf(
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker
                    .ExecutorImplementationBranchNotAuthorized,
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.NoRunnableExecutorSurface,
                SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.TestOnlyEvidenceNonAuthorizing,
            ),
            redactionClass = SkaldVaultV1TestOnlyProviderKatExecutorReadinessRedactionClass
                .EvidenceSourceLabelsOnly,
        )

    fun requirementRow(
        requirement: SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement,
        sources: Set<SkaldVaultV1TestOnlyProviderKatExecutorReadinessEvidenceSource>,
        classification: SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification,
    ): SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirementRow =
        SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirementRow(
            requirement = requirement,
            evidenceSources = sources,
            classification = classification,
            modeledEvidenceExists = classification != SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification
                .Missing,
            implementationSufficient = false,
            currentBranchAuthorizesImplementation = false,
            canAuthorizeProduction = false,
            blockers = blockersFor(requirement, classification),
            redactionClass = SkaldVaultV1TestOnlyProviderKatExecutorReadinessRedactionClass.RequirementLabelsOnly,
            safeLabel = SkaldVaultV1TestOnlyProviderKatExecutorReadinessSafeLabel(requirement.label),
        )

    fun blockersFor(
        requirement: SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement,
        classification: SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification,
    ): Set<SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker> =
        buildSet {
            add(SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.ExecutorImplementationBranchNotAuthorized)
            add(SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.NoExecutorImplementation)
            add(SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.NoRunnableExecutorSurface)
            add(SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.TestOnlyEvidenceNonAuthorizing)
            if (
                requirement == SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.SourceSetAllowlistFinalized ||
                requirement == SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.ProductionSourceSetDenylistFinalized ||
                classification == SkaldVaultV1TestOnlyProviderKatExecutorReadinessClassification
                    .NotSufficientForImplementation
            ) {
                add(SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.SourceSetImplementationReviewMissing)
            }
            if (
                requirement == SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.ProviderSelectionIsolationAsserted ||
                requirement == SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement
                    .ProductionProviderSelectableFalseAsserted
            ) {
                add(SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.ProviderSelectionDisabledProviderOnly)
                add(SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.ProductionProviderSelectableFalse)
            }
            if (
                requirement == SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.VaultLifecycleRemainsBlocked ||
                requirement == SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.PersistenceRemainsBlocked
            ) {
                add(SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.VaultLifecycleDisabled)
                add(SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.PersistenceDisabled)
            }
            if (requirement == SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.ProductionSyncRemainsBlocked) {
                add(SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.ProductionSyncDisabled)
            }
            if (requirement == SkaldVaultV1TestOnlyProviderKatExecutorReadinessRequirement.MainnetRemainsBlocked) {
                add(SkaldVaultV1TestOnlyProviderKatExecutorReadinessBlocker.MainnetDisabled)
            }
        }
}
