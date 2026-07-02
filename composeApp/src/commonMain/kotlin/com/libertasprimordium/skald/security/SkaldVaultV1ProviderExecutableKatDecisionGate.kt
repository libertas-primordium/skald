package com.libertasprimordium.skald.security

interface SkaldVaultV1ProviderExecutableKatDecisionGate {
    fun evaluate(
        request: SkaldVaultV1ProviderExecutableKatDecisionGateRequest,
    ): SkaldVaultV1ProviderExecutableKatDecisionGateResult<
        SkaldVaultV1ProviderExecutableKatDecisionGateEvidence,
    >
}

enum class SkaldVaultV1ProviderExecutableKatDecisionGateSource(val label: String) {
    CurrentTypedEvidence("current provider executable KAT decision-gate evidence"),
    RequiredEvidenceAudit("provider executable KAT required-evidence audit"),
    FutureStageAudit("provider executable KAT future-stage audit"),
    ExternalEvidenceAudit("provider executable KAT external-evidence audit"),
    CapabilityAudit("provider executable KAT capability audit"),
}

enum class SkaldVaultV1ProviderExecutableKatDecisionGateStatus(val label: String) {
    DecisionGateModeled("decision gate modeled"),
    FutureEvidenceListDefined("future evidence list defined"),
    StillDisabled("still disabled"),
    EvidenceOnly("evidence only"),
    ExecutableKatNotAuthorized("executable KAT not authorized"),
    TestOnlyExecutableKatNotAuthorized("test-only executable KAT not authorized"),
    ProductionExecutableKatNotAuthorized("production executable KAT not authorized"),
    ProviderSelectionAuthorizationBlocked("provider selection authorization blocked"),
    ProductionProviderSelectabilityBlocked("production provider selectability blocked"),
    VaultCreationUnlockPersistenceBlocked("vault creation/unlock/persistence blocked"),
    StorageReadinessBlocked("storage readiness blocked"),
    MainnetBlocked("mainnet blocked"),
}

enum class SkaldVaultV1ProviderExecutableKatRequiredEvidence(val label: String) {
    ProviderBoundaryReviewed("provider boundary reviewed"),
    ProviderInterfaceContractAudited("provider interface contract audited"),
    ProviderImplementationExistsButRemainsNonProduction(
        "provider implementation exists but remains non-production",
    ),
    ProviderFactoryIsolationReviewed("provider factory isolation reviewed"),
    ProviderRegistryIsolationReviewed("provider registry isolation reviewed"),
    ProviderOperationDispatchIsolationReviewed("provider operation dispatch isolation reviewed"),
    ProviderKatExecutionIsolationReviewed("provider KAT execution isolation reviewed"),
    ProviderOperationAuthorizationReviewed("provider operation authorization reviewed"),
    RuntimeRandomnessAuthorizationReviewed("runtime randomness authorization reviewed"),
    KdfCalibrationPolicyReviewed("KDF calibration policy reviewed"),
    PassphrasePolicyReviewed("passphrase policy reviewed"),
    ClearWipeStrategyReviewed("clear/wipe strategy reviewed"),
    RedactionLeakagePolicyReviewed("redaction/leakage policy reviewed"),
    MigrationCorruptionPolicyReviewed("migration/corruption policy reviewed"),
    SecureStorageBoundaryReviewed("secure storage boundary reviewed"),
    SecureMetadataBoundaryReviewed("secure metadata boundary reviewed"),
    VaultCreationAuthorizationReviewed("vault creation authorization reviewed"),
    VaultUnlockAuthorizationReviewed("vault unlock authorization reviewed"),
    PersistenceReadinessReviewed("persistence readiness reviewed"),
    SourceSetConfinementReviewed("source-set confinement reviewed"),
    ProviderLevelPositiveKatsDefined("provider-level positive KATs defined"),
    ProviderLevelNegativeKatsDefined("provider-level negative KATs defined"),
    ProviderLevelRedactionKatsDefined("provider-level redaction KATs defined"),
    ProviderLevelRandomnessKatsDefinedWhereApplicable(
        "provider-level randomness KATs defined where applicable",
    ),
    ProviderLevelStorageSeparationKatsDefinedWhereApplicable(
        "provider-level storage-separation KATs defined where applicable",
    ),
    ProviderLevelPlatformChecksDefinedWhereApplicable(
        "provider-level platform checks defined where applicable",
    ),
    PublicNonWalletVectorProvenanceReviewed("public non-wallet vector provenance reviewed"),
    CanonicalSkaldVectorProvenanceReviewed("canonical Skald vector provenance reviewed"),
    AndroidRuntimeTestStrategyReviewed("Android runtime test strategy reviewed"),
    LinuxJvmRuntimeTestStrategyReviewed("Linux/JVM runtime test strategy reviewed"),
}

enum class SkaldVaultV1ProviderExecutableKatFutureStage(val label: String) {
    NoExecutableKat("no executable KAT"),
    ModelOnlyDecisionGate("model-only decision gate"),
    TestOnlyDeterministicProviderKatPrototype("test-only deterministic provider KAT prototype"),
    TestOnlyRandomizedBehavioralProviderKatPrototype(
        "test-only randomized behavioral provider KAT prototype",
    ),
    NonProductionProviderSelfTest("non-production provider self-test"),
    ProductionProviderStartupSelfTest("production provider startup self-test"),
    ReleaseValidationKat("release validation KAT"),
    MainnetReleaseValidation("mainnet release validation"),
}

enum class SkaldVaultV1ProviderExecutableKatDecisionBlocker(val label: String) {
    DecisionGateStillDisabled("decision gate still disabled"),
    ModelEvidenceOnly("model evidence only"),
    NoExecutableProviderImplementation("no executable provider implementation"),
    NoProviderKatExecutor("no provider KAT executor"),
    ProviderSelectionDisabledProviderOnly("provider selection disabled-provider-only"),
    ProductionProviderSelectableFalse("productionProviderSelectable is false"),
    ProviderOperationAuthorizationBlocked("provider operation authorization blocked"),
    RuntimeRandomnessAuthorizationBlocked("runtime randomness authorization blocked"),
    KdfCalibrationNotFinal("KDF calibration not final"),
    PassphrasePolicyNotExecutable("passphrase policy not executable"),
    SecureStorageDisabled("secure storage disabled"),
    SecureMetadataStorageDisabled("secure metadata storage disabled"),
    VaultCreationBlocked("vault creation blocked"),
    VaultUnlockBlocked("vault unlock blocked"),
    PersistenceReadinessBlocked("persistence readiness blocked"),
    StorageReadinessBlocked("storage readiness blocked"),
    LifecycleLockPolicyNotExecutable("lifecycle/lock policy not executable"),
    RedactionLeakageGatesIncomplete("redaction/leakage gates incomplete"),
    ClearWipeGatesIncomplete("clear/wipe gates incomplete"),
    MigrationCorruptionGatesIncomplete("migration/corruption gates incomplete"),
    DependencyLevelKatsCannotAuthorizeProviderKats(
        "dependency-level KATs cannot authorize provider-level executable KATs",
    ),
    PublicVectorDocumentationCannotAuthorizeExecution(
        "public vector documentation cannot authorize execution",
    ),
    TestOnlyHarnessCannotAuthorizeProduction("test-only harness cannot authorize production"),
    TestOnlyEvidenceCannotAuthorizeProduction("test-only evidence cannot authorize production"),
    WarningOnlyEvidenceCannotAuthorize("warning-only evidence cannot authorize"),
    UserConsentCannotOverrideMissingHardGates("user consent cannot override missing hard gates"),
    ReleaseReviewMissing("release review missing"),
    MainnetDisabled("mainnet disabled"),
}

enum class SkaldVaultV1ProviderExecutableKatExternalEvidenceKind(val label: String) {
    DependencyLevelDesktopAndroidKatEvidence("dependency-level desktop/Android KAT evidence"),
    PublicVectorDocumentation("public vector documentation"),
    TestOnlyProviderKatHarnessEvidence("test-only provider KAT harness evidence"),
    WarningOnlyEvidence("warning-only evidence"),
    UserConsentOverride("user-consent override request"),
    ReleaseOrMainnetEvidence("release/mainnet evidence"),
    ProviderImplementationEvidence("provider implementation evidence"),
    ProviderKatExecutorEvidence("provider KAT executor evidence"),
}

enum class SkaldVaultV1ProviderExecutableKatRedactionClass(val label: String) {
    PolicyIdsOnly("policy IDs only"),
    EvidenceClassNamesOnly("evidence class names only"),
    BlockerLabelsOnly("blocker labels only"),
    NoRawMaterial("no raw material"),
    NoProviderHandles("no provider handles"),
    NoCryptoObjects("no crypto objects"),
    NoDiagnosticPayloads("no diagnostic payloads"),
    NoFilesystemPaths("no filesystem paths"),
    NoBackendHandles("no backend handles"),
}

data class SkaldVaultV1ProviderExecutableKatExternalEvidence(
    val kind: SkaldVaultV1ProviderExecutableKatExternalEvidenceKind,
    val evidenceOnly: Boolean = true,
    val productionAuthorizingClaimed: Boolean = false,
    val userConsentOverrideRequested: Boolean = false,
    val safeEvidenceId: String,
) {
    override fun toString(): String =
        "SkaldVaultV1ProviderExecutableKatExternalEvidence(" +
            "kind=$kind, " +
            "evidenceOnly=$evidenceOnly, " +
            "productionAuthorizingClaimed=$productionAuthorizingClaimed, " +
            "userConsentOverrideRequested=$userConsentOverrideRequested, " +
            "safeEvidenceId=<redacted>, " +
            "providerHandle=<redacted>, " +
            "cryptoObject=<redacted>, " +
            "rawMaterial=<redacted>, " +
            "diagnosticPayload=<redacted>, " +
            "filesystemPath=<redacted>, " +
            "backendHandle=<redacted>" +
            ")"
}

data class SkaldVaultV1ProviderExecutableKatDecisionCapability(
    val decisionGateModeled: Boolean,
    val futureEvidenceListDefined: Boolean,
    val canIntroduceExecutableKatNow: Boolean,
    val canIntroduceTestOnlyExecutableKatNow: Boolean,
    val canIntroduceProductionExecutableKatNow: Boolean,
    val canRunProviderOperations: Boolean,
    val canRunRandomness: Boolean,
    val canRunKdf: Boolean,
    val canRunAead: Boolean,
    val canRunHkdf: Boolean,
    val canRunHmac: Boolean,
    val canGenerateKeys: Boolean,
    val canStoreKeysets: Boolean,
    val canAuthorizeProviderSelection: Boolean,
    val canSetProductionProviderSelectable: Boolean,
    val canAuthorizeVaultCreation: Boolean,
    val canAuthorizeVaultUnlock: Boolean,
    val canAuthorizeVaultPersistence: Boolean,
    val canAuthorizeMainnet: Boolean,
) {
    companion object {
        val StillDisabled = SkaldVaultV1ProviderExecutableKatDecisionCapability(
            decisionGateModeled = true,
            futureEvidenceListDefined = true,
            canIntroduceExecutableKatNow = false,
            canIntroduceTestOnlyExecutableKatNow = false,
            canIntroduceProductionExecutableKatNow = false,
            canRunProviderOperations = false,
            canRunRandomness = false,
            canRunKdf = false,
            canRunAead = false,
            canRunHkdf = false,
            canRunHmac = false,
            canGenerateKeys = false,
            canStoreKeysets = false,
            canAuthorizeProviderSelection = false,
            canSetProductionProviderSelectable = false,
            canAuthorizeVaultCreation = false,
            canAuthorizeVaultUnlock = false,
            canAuthorizeVaultPersistence = false,
            canAuthorizeMainnet = false,
        )
    }
}

data class SkaldVaultV1ProviderExecutableKatRequiredEvidenceRow(
    val evidence: SkaldVaultV1ProviderExecutableKatRequiredEvidence,
    val statuses: Set<SkaldVaultV1ProviderExecutableKatDecisionGateStatus>,
    val blockers: Set<SkaldVaultV1ProviderExecutableKatDecisionBlocker>,
    val futureRequiredBeforeTestOnlyExecutableKat: Boolean,
    val currentlySatisfied: Boolean,
    val canAuthorizeExecutableKatNow: Boolean,
    val canAuthorizeProduction: Boolean,
    val redactionClass: SkaldVaultV1ProviderExecutableKatRedactionClass,
)

data class SkaldVaultV1ProviderExecutableKatStageRow(
    val stage: SkaldVaultV1ProviderExecutableKatFutureStage,
    val statuses: Set<SkaldVaultV1ProviderExecutableKatDecisionGateStatus>,
    val blockers: Set<SkaldVaultV1ProviderExecutableKatDecisionBlocker>,
    val currentStage: Boolean,
    val executableKatPresent: Boolean,
    val testOnlyExecutableKatAllowed: Boolean,
    val productionExecutableKatAllowed: Boolean,
    val canAuthorizeProviderSelection: Boolean,
    val canAuthorizeStorageOrVaultLifecycle: Boolean,
    val canAuthorizeMainnet: Boolean,
    val redactionClass: SkaldVaultV1ProviderExecutableKatRedactionClass,
)

data class SkaldVaultV1ProviderExecutableKatExternalEvidenceRow(
    val kind: SkaldVaultV1ProviderExecutableKatExternalEvidenceKind,
    val statuses: Set<SkaldVaultV1ProviderExecutableKatDecisionGateStatus>,
    val blockers: Set<SkaldVaultV1ProviderExecutableKatDecisionBlocker>,
    val evidenceOnly: Boolean,
    val rejectedForExecutableKatAuthorization: Boolean,
    val rejectedForProductionAuthorization: Boolean,
    val canAuthorizeProviderSelection: Boolean,
    val canSetProductionProviderSelectable: Boolean,
    val canAuthorizeVaultCreationUnlockPersistence: Boolean,
    val canAuthorizeMainnet: Boolean,
    val redactionClass: SkaldVaultV1ProviderExecutableKatRedactionClass,
)

class SkaldVaultV1ProviderExecutableKatDecisionGatePolicyToken private constructor(
    val policyId: String,
    val requiredEvidence: SkaldVaultV1ProviderExecutableKatRequiredEvidence?,
    val futureStage: SkaldVaultV1ProviderExecutableKatFutureStage?,
    val externalEvidenceKind: SkaldVaultV1ProviderExecutableKatExternalEvidenceKind?,
) {
    val containsRawMaterial: Boolean = false
    val containsProviderHandle: Boolean = false
    val containsCryptoObject: Boolean = false
    val containsDiagnosticPayload: Boolean = false
    val containsFilesystemPath: Boolean = false
    val containsBackendHandle: Boolean = false

    override fun toString(): String =
        "SkaldVaultV1ProviderExecutableKatDecisionGatePolicyToken(" +
            "policyId=$policyId, " +
            "requiredEvidence=$requiredEvidence, " +
            "futureStage=$futureStage, " +
            "externalEvidenceKind=$externalEvidenceKind, " +
            "rawMaterial=<redacted>, " +
            "providerHandle=<redacted>, " +
            "cryptoObject=<redacted>, " +
            "diagnosticPayload=<redacted>, " +
            "filesystemPath=<redacted>, " +
            "backendHandle=<redacted>" +
            ")"

    companion object {
        fun redacted(
            policyId: String,
            requiredEvidence: SkaldVaultV1ProviderExecutableKatRequiredEvidence?,
            futureStage: SkaldVaultV1ProviderExecutableKatFutureStage?,
            externalEvidenceKind: SkaldVaultV1ProviderExecutableKatExternalEvidenceKind?,
        ): SkaldVaultV1ProviderExecutableKatDecisionGatePolicyToken {
            return SkaldVaultV1ProviderExecutableKatDecisionGatePolicyToken(
                policyId = policyId,
                requiredEvidence = requiredEvidence,
                futureStage = futureStage,
                externalEvidenceKind = externalEvidenceKind,
            )
        }
    }
}

class SkaldVaultV1ProviderExecutableKatDecisionGateRequest private constructor(
    val source: SkaldVaultV1ProviderExecutableKatDecisionGateSource,
    val requiredEvidence: SkaldVaultV1ProviderExecutableKatRequiredEvidence?,
    val futureStage: SkaldVaultV1ProviderExecutableKatFutureStage?,
    val externalEvidenceKind: SkaldVaultV1ProviderExecutableKatExternalEvidenceKind?,
    private val externalEvidence: List<SkaldVaultV1ProviderExecutableKatExternalEvidence>,
) {
    val externalEvidenceSupplied: Boolean
        get() = externalEvidence.isNotEmpty()

    val providerImplementationEvidenceSupplied: Boolean
        get() = externalEvidence.any {
            it.kind == SkaldVaultV1ProviderExecutableKatExternalEvidenceKind.ProviderImplementationEvidence
        }

    val providerKatExecutorEvidenceSupplied: Boolean
        get() = externalEvidence.any {
            it.kind == SkaldVaultV1ProviderExecutableKatExternalEvidenceKind.ProviderKatExecutorEvidence
        }

    val externalEvidenceKinds: Set<SkaldVaultV1ProviderExecutableKatExternalEvidenceKind>
        get() = externalEvidence.map { it.kind }.toSet()

    override fun toString(): String =
        "SkaldVaultV1ProviderExecutableKatDecisionGateRequest(" +
            "source=$source, " +
            "requiredEvidence=$requiredEvidence, " +
            "futureStage=$futureStage, " +
            "externalEvidenceKind=$externalEvidenceKind, " +
            "externalEvidence=<redacted>, " +
            "rawMaterial=<redacted>, " +
            "providerHandle=<redacted>, " +
            "cryptoObject=<redacted>, " +
            "diagnosticPayload=<redacted>, " +
            "filesystemPath=<redacted>, " +
            "backendHandle=<redacted>" +
            ")"

    companion object {
        fun currentEvidence(
            externalEvidence: List<SkaldVaultV1ProviderExecutableKatExternalEvidence> = emptyList(),
        ): SkaldVaultV1ProviderExecutableKatDecisionGateRequest =
            SkaldVaultV1ProviderExecutableKatDecisionGateRequest(
                source = SkaldVaultV1ProviderExecutableKatDecisionGateSource.CurrentTypedEvidence,
                requiredEvidence = null,
                futureStage = null,
                externalEvidenceKind = null,
                externalEvidence = externalEvidence,
            )

        fun forRequiredEvidence(
            evidence: SkaldVaultV1ProviderExecutableKatRequiredEvidence,
        ): SkaldVaultV1ProviderExecutableKatDecisionGateRequest =
            SkaldVaultV1ProviderExecutableKatDecisionGateRequest(
                source = SkaldVaultV1ProviderExecutableKatDecisionGateSource.RequiredEvidenceAudit,
                requiredEvidence = evidence,
                futureStage = null,
                externalEvidenceKind = null,
                externalEvidence = emptyList(),
            )

        fun forStage(
            stage: SkaldVaultV1ProviderExecutableKatFutureStage,
        ): SkaldVaultV1ProviderExecutableKatDecisionGateRequest =
            SkaldVaultV1ProviderExecutableKatDecisionGateRequest(
                source = SkaldVaultV1ProviderExecutableKatDecisionGateSource.FutureStageAudit,
                requiredEvidence = null,
                futureStage = stage,
                externalEvidenceKind = null,
                externalEvidence = emptyList(),
            )

        fun forExternalEvidence(
            kind: SkaldVaultV1ProviderExecutableKatExternalEvidenceKind,
            externalEvidence: List<SkaldVaultV1ProviderExecutableKatExternalEvidence> = emptyList(),
        ): SkaldVaultV1ProviderExecutableKatDecisionGateRequest =
            SkaldVaultV1ProviderExecutableKatDecisionGateRequest(
                source = SkaldVaultV1ProviderExecutableKatDecisionGateSource.ExternalEvidenceAudit,
                requiredEvidence = null,
                futureStage = null,
                externalEvidenceKind = kind,
                externalEvidence = externalEvidence,
            )

        fun externalEvidence(
            kind: SkaldVaultV1ProviderExecutableKatExternalEvidenceKind,
            safeEvidenceId: String,
            evidenceOnly: Boolean = true,
            productionAuthorizingClaimed: Boolean = false,
            userConsentOverrideRequested: Boolean = false,
        ): SkaldVaultV1ProviderExecutableKatExternalEvidence =
            SkaldVaultV1ProviderExecutableKatExternalEvidence(
                kind = kind,
                evidenceOnly = evidenceOnly,
                productionAuthorizingClaimed = productionAuthorizingClaimed,
                userConsentOverrideRequested = userConsentOverrideRequested,
                safeEvidenceId = safeEvidenceId,
            )
    }
}

data class SkaldVaultV1ProviderExecutableKatDecisionGateEvidence(
    val status: SkaldVaultV1ProviderExecutableKatDecisionGateStatus,
    val source: SkaldVaultV1ProviderExecutableKatDecisionGateSource,
    val currentStage: SkaldVaultV1ProviderExecutableKatFutureStage,
    val statuses: Set<SkaldVaultV1ProviderExecutableKatDecisionGateStatus>,
    val blockers: Set<SkaldVaultV1ProviderExecutableKatDecisionBlocker>,
    val requiredEvidenceRows: List<SkaldVaultV1ProviderExecutableKatRequiredEvidenceRow>,
    val stageRows: List<SkaldVaultV1ProviderExecutableKatStageRow>,
    val externalEvidenceRows: List<SkaldVaultV1ProviderExecutableKatExternalEvidenceRow>,
    val redactionClasses: Set<SkaldVaultV1ProviderExecutableKatRedactionClass>,
    val capability: SkaldVaultV1ProviderExecutableKatDecisionCapability,
    val policyTokenEvidence: SkaldVaultV1ProviderExecutableKatDecisionGatePolicyToken,
    val decisionGateModeled: Boolean = true,
    val futureEvidenceListDefined: Boolean = true,
    val executableKatAllowedNow: Boolean = false,
    val testOnlyExecutableKatAllowedNow: Boolean = false,
    val productionExecutableKatAllowedNow: Boolean = false,
    val katResultCanAuthorizeProviderSelection: Boolean = false,
    val katResultCanSetProductionProviderSelectable: Boolean = false,
    val katResultCanAuthorizeVaultCreation: Boolean = false,
    val katResultCanAuthorizeVaultUnlock: Boolean = false,
    val katResultCanAuthorizeVaultPersistence: Boolean = false,
    val katResultCanAuthorizeMainnet: Boolean = false,
    val providerSelectionDisabledProviderOnly: Boolean = true,
    val productionProviderSelectable: Boolean = false,
    val providerOperationsExecute: Boolean = false,
    val randomnessExecutes: Boolean = false,
    val kdfExecutes: Boolean = false,
    val aeadExecutes: Boolean = false,
    val hkdfExecutes: Boolean = false,
    val hmacExecutes: Boolean = false,
    val keyGenerationExecutes: Boolean = false,
    val keysetStorageExecutes: Boolean = false,
    val storageReadinessApproved: Boolean = false,
    val secureSecretStorageAvailable: Boolean = false,
    val secureMetadataStorageAvailable: Boolean = false,
    val productionSyncEnabled: Boolean = false,
    val mainnetEnabled: Boolean = false,
)

sealed interface SkaldVaultV1ProviderExecutableKatDecisionGateResult<out T> {
    data class Blocked<T>(
        val value: T,
    ) : SkaldVaultV1ProviderExecutableKatDecisionGateResult<T>
}

data class SkaldVaultV1ProviderExecutableKatDecisionGateSummary(
    val policyId: String,
    val policyVersion: Int,
    val statuses: Set<SkaldVaultV1ProviderExecutableKatDecisionGateStatus>,
    val requiredEvidence: Set<SkaldVaultV1ProviderExecutableKatRequiredEvidence>,
    val futureStages: Set<SkaldVaultV1ProviderExecutableKatFutureStage>,
    val blockers: Set<SkaldVaultV1ProviderExecutableKatDecisionBlocker>,
    val externalEvidenceKinds: Set<SkaldVaultV1ProviderExecutableKatExternalEvidenceKind>,
    val redactionClasses: Set<SkaldVaultV1ProviderExecutableKatRedactionClass>,
    val capability: SkaldVaultV1ProviderExecutableKatDecisionCapability,
    val stillDisabled: Boolean,
    val evidenceOnly: Boolean,
    val blocksExecutableKat: Boolean,
    val blocksProductionKat: Boolean,
    val blocksProviderSelection: Boolean,
    val blocksVaultCreationUnlockPersistence: Boolean,
    val blocksMainnet: Boolean,
)

object SkaldVaultV1ProviderExecutableKatDecisionGatePolicy :
    SkaldVaultV1ProviderExecutableKatDecisionGate {
    const val POLICY_ID: String = "skald-vault-v1-provider-executable-kat-decision-gate-v1"
    const val POLICY_VERSION: Int = 1

    override fun evaluate(
        request: SkaldVaultV1ProviderExecutableKatDecisionGateRequest,
    ): SkaldVaultV1ProviderExecutableKatDecisionGateResult.Blocked<
        SkaldVaultV1ProviderExecutableKatDecisionGateEvidence,
    > {
        val externalRows = externalRowsFor(request)
        val blockers = baseBlockers + externalRows.flatMap { it.blockers }.toSet()
        val evidence = SkaldVaultV1ProviderExecutableKatDecisionGateEvidence(
            status = SkaldVaultV1ProviderExecutableKatDecisionGateStatus.StillDisabled,
            source = request.source,
            currentStage = SkaldVaultV1ProviderExecutableKatFutureStage.ModelOnlyDecisionGate,
            statuses = baseStatuses,
            blockers = blockers,
            requiredEvidenceRows = requiredEvidenceRowsFor(request.requiredEvidence),
            stageRows = stageRowsFor(request.futureStage),
            externalEvidenceRows = externalRows,
            redactionClasses = SkaldVaultV1ProviderExecutableKatRedactionClass.entries.toSet(),
            capability = SkaldVaultV1ProviderExecutableKatDecisionCapability.StillDisabled,
            policyTokenEvidence = SkaldVaultV1ProviderExecutableKatDecisionGatePolicyToken.redacted(
                policyId = POLICY_ID,
                requiredEvidence = request.requiredEvidence,
                futureStage = request.futureStage,
                externalEvidenceKind = request.externalEvidenceKind,
            ),
        )
        return SkaldVaultV1ProviderExecutableKatDecisionGateResult.Blocked(evidence)
    }

    fun currentPolicySummary(): SkaldVaultV1ProviderExecutableKatDecisionGateSummary =
        SkaldVaultV1ProviderExecutableKatDecisionGateSummary(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            statuses = SkaldVaultV1ProviderExecutableKatDecisionGateStatus.entries.toSet(),
            requiredEvidence = SkaldVaultV1ProviderExecutableKatRequiredEvidence.entries.toSet(),
            futureStages = SkaldVaultV1ProviderExecutableKatFutureStage.entries.toSet(),
            blockers = SkaldVaultV1ProviderExecutableKatDecisionBlocker.entries.toSet(),
            externalEvidenceKinds = SkaldVaultV1ProviderExecutableKatExternalEvidenceKind.entries.toSet(),
            redactionClasses = SkaldVaultV1ProviderExecutableKatRedactionClass.entries.toSet(),
            capability = SkaldVaultV1ProviderExecutableKatDecisionCapability.StillDisabled,
            stillDisabled = true,
            evidenceOnly = true,
            blocksExecutableKat = true,
            blocksProductionKat = true,
            blocksProviderSelection = true,
            blocksVaultCreationUnlockPersistence = true,
            blocksMainnet = true,
        )

    private fun requiredEvidenceRowsFor(
        requiredEvidence: SkaldVaultV1ProviderExecutableKatRequiredEvidence?,
    ): List<SkaldVaultV1ProviderExecutableKatRequiredEvidenceRow> {
        val rows = SkaldVaultV1ProviderExecutableKatRequiredEvidence.entries.map { evidence ->
            requiredEvidenceRow(evidence)
        }
        return requiredEvidence?.let { requested -> rows.filter { it.evidence == requested } } ?: rows
    }

    private fun stageRowsFor(
        futureStage: SkaldVaultV1ProviderExecutableKatFutureStage?,
    ): List<SkaldVaultV1ProviderExecutableKatStageRow> {
        val rows = SkaldVaultV1ProviderExecutableKatFutureStage.entries.map { stage -> stageRow(stage) }
        return futureStage?.let { requested -> rows.filter { it.stage == requested } } ?: rows
    }

    private fun externalRowsFor(
        request: SkaldVaultV1ProviderExecutableKatDecisionGateRequest,
    ): List<SkaldVaultV1ProviderExecutableKatExternalEvidenceRow> {
        val requestedKinds = if (request.externalEvidenceKind != null) {
            setOf(request.externalEvidenceKind)
        } else if (request.externalEvidenceKinds.isNotEmpty()) {
            request.externalEvidenceKinds
        } else {
            SkaldVaultV1ProviderExecutableKatExternalEvidenceKind.entries.toSet()
        }
        return requestedKinds.map { kind -> externalEvidenceRow(kind) }
    }

    private fun requiredEvidenceRow(
        evidence: SkaldVaultV1ProviderExecutableKatRequiredEvidence,
    ): SkaldVaultV1ProviderExecutableKatRequiredEvidenceRow =
        SkaldVaultV1ProviderExecutableKatRequiredEvidenceRow(
            evidence = evidence,
            statuses = baseStatuses + SkaldVaultV1ProviderExecutableKatDecisionGateStatus.ExecutableKatNotAuthorized,
            blockers = baseBlockers + requiredEvidenceBlockers(evidence),
            futureRequiredBeforeTestOnlyExecutableKat = true,
            currentlySatisfied = false,
            canAuthorizeExecutableKatNow = false,
            canAuthorizeProduction = false,
            redactionClass = SkaldVaultV1ProviderExecutableKatRedactionClass.EvidenceClassNamesOnly,
        )

    private fun stageRow(
        stage: SkaldVaultV1ProviderExecutableKatFutureStage,
    ): SkaldVaultV1ProviderExecutableKatStageRow =
        SkaldVaultV1ProviderExecutableKatStageRow(
            stage = stage,
            statuses = baseStatuses + stageStatus(stage),
            blockers = baseBlockers + stageBlockers(stage),
            currentStage = stage == SkaldVaultV1ProviderExecutableKatFutureStage.ModelOnlyDecisionGate,
            executableKatPresent = false,
            testOnlyExecutableKatAllowed = false,
            productionExecutableKatAllowed = false,
            canAuthorizeProviderSelection = false,
            canAuthorizeStorageOrVaultLifecycle = false,
            canAuthorizeMainnet = false,
            redactionClass = SkaldVaultV1ProviderExecutableKatRedactionClass.PolicyIdsOnly,
        )

    private fun externalEvidenceRow(
        kind: SkaldVaultV1ProviderExecutableKatExternalEvidenceKind,
    ): SkaldVaultV1ProviderExecutableKatExternalEvidenceRow =
        SkaldVaultV1ProviderExecutableKatExternalEvidenceRow(
            kind = kind,
            statuses = baseStatuses + externalEvidenceStatus(kind),
            blockers = baseBlockers + externalEvidenceBlockers(kind),
            evidenceOnly = true,
            rejectedForExecutableKatAuthorization = true,
            rejectedForProductionAuthorization = true,
            canAuthorizeProviderSelection = false,
            canSetProductionProviderSelectable = false,
            canAuthorizeVaultCreationUnlockPersistence = false,
            canAuthorizeMainnet = false,
            redactionClass = SkaldVaultV1ProviderExecutableKatRedactionClass.EvidenceClassNamesOnly,
        )

    private fun requiredEvidenceBlockers(
        evidence: SkaldVaultV1ProviderExecutableKatRequiredEvidence,
    ): Set<SkaldVaultV1ProviderExecutableKatDecisionBlocker> =
        when (evidence) {
            SkaldVaultV1ProviderExecutableKatRequiredEvidence.ProviderImplementationExistsButRemainsNonProduction ->
                setOf(SkaldVaultV1ProviderExecutableKatDecisionBlocker.NoExecutableProviderImplementation)
            SkaldVaultV1ProviderExecutableKatRequiredEvidence.ProviderKatExecutionIsolationReviewed,
            SkaldVaultV1ProviderExecutableKatRequiredEvidence.ProviderLevelPositiveKatsDefined,
            SkaldVaultV1ProviderExecutableKatRequiredEvidence.ProviderLevelNegativeKatsDefined,
            SkaldVaultV1ProviderExecutableKatRequiredEvidence.ProviderLevelRedactionKatsDefined,
            SkaldVaultV1ProviderExecutableKatRequiredEvidence.ProviderLevelRandomnessKatsDefinedWhereApplicable,
            SkaldVaultV1ProviderExecutableKatRequiredEvidence
                .ProviderLevelStorageSeparationKatsDefinedWhereApplicable,
            SkaldVaultV1ProviderExecutableKatRequiredEvidence.ProviderLevelPlatformChecksDefinedWhereApplicable ->
                setOf(SkaldVaultV1ProviderExecutableKatDecisionBlocker.NoProviderKatExecutor)
            SkaldVaultV1ProviderExecutableKatRequiredEvidence.RuntimeRandomnessAuthorizationReviewed ->
                setOf(SkaldVaultV1ProviderExecutableKatDecisionBlocker.RuntimeRandomnessAuthorizationBlocked)
            SkaldVaultV1ProviderExecutableKatRequiredEvidence.KdfCalibrationPolicyReviewed ->
                setOf(SkaldVaultV1ProviderExecutableKatDecisionBlocker.KdfCalibrationNotFinal)
            SkaldVaultV1ProviderExecutableKatRequiredEvidence.PassphrasePolicyReviewed ->
                setOf(SkaldVaultV1ProviderExecutableKatDecisionBlocker.PassphrasePolicyNotExecutable)
            SkaldVaultV1ProviderExecutableKatRequiredEvidence.ClearWipeStrategyReviewed ->
                setOf(SkaldVaultV1ProviderExecutableKatDecisionBlocker.ClearWipeGatesIncomplete)
            SkaldVaultV1ProviderExecutableKatRequiredEvidence.RedactionLeakagePolicyReviewed ->
                setOf(SkaldVaultV1ProviderExecutableKatDecisionBlocker.RedactionLeakageGatesIncomplete)
            SkaldVaultV1ProviderExecutableKatRequiredEvidence.MigrationCorruptionPolicyReviewed ->
                setOf(SkaldVaultV1ProviderExecutableKatDecisionBlocker.MigrationCorruptionGatesIncomplete)
            SkaldVaultV1ProviderExecutableKatRequiredEvidence.SecureStorageBoundaryReviewed ->
                setOf(SkaldVaultV1ProviderExecutableKatDecisionBlocker.SecureStorageDisabled)
            SkaldVaultV1ProviderExecutableKatRequiredEvidence.SecureMetadataBoundaryReviewed ->
                setOf(SkaldVaultV1ProviderExecutableKatDecisionBlocker.SecureMetadataStorageDisabled)
            SkaldVaultV1ProviderExecutableKatRequiredEvidence.VaultCreationAuthorizationReviewed ->
                setOf(SkaldVaultV1ProviderExecutableKatDecisionBlocker.VaultCreationBlocked)
            SkaldVaultV1ProviderExecutableKatRequiredEvidence.VaultUnlockAuthorizationReviewed ->
                setOf(SkaldVaultV1ProviderExecutableKatDecisionBlocker.VaultUnlockBlocked)
            SkaldVaultV1ProviderExecutableKatRequiredEvidence.PersistenceReadinessReviewed ->
                setOf(SkaldVaultV1ProviderExecutableKatDecisionBlocker.PersistenceReadinessBlocked)
            else -> emptySet()
        }

    private fun stageStatus(
        stage: SkaldVaultV1ProviderExecutableKatFutureStage,
    ): SkaldVaultV1ProviderExecutableKatDecisionGateStatus =
        when (stage) {
            SkaldVaultV1ProviderExecutableKatFutureStage.NoExecutableKat,
            SkaldVaultV1ProviderExecutableKatFutureStage.ModelOnlyDecisionGate ->
                SkaldVaultV1ProviderExecutableKatDecisionGateStatus.StillDisabled
            SkaldVaultV1ProviderExecutableKatFutureStage.TestOnlyDeterministicProviderKatPrototype,
            SkaldVaultV1ProviderExecutableKatFutureStage.TestOnlyRandomizedBehavioralProviderKatPrototype ->
                SkaldVaultV1ProviderExecutableKatDecisionGateStatus.TestOnlyExecutableKatNotAuthorized
            SkaldVaultV1ProviderExecutableKatFutureStage.NonProductionProviderSelfTest,
            SkaldVaultV1ProviderExecutableKatFutureStage.ProductionProviderStartupSelfTest,
            SkaldVaultV1ProviderExecutableKatFutureStage.ReleaseValidationKat ->
                SkaldVaultV1ProviderExecutableKatDecisionGateStatus.ProductionExecutableKatNotAuthorized
            SkaldVaultV1ProviderExecutableKatFutureStage.MainnetReleaseValidation ->
                SkaldVaultV1ProviderExecutableKatDecisionGateStatus.MainnetBlocked
        }

    private fun stageBlockers(
        stage: SkaldVaultV1ProviderExecutableKatFutureStage,
    ): Set<SkaldVaultV1ProviderExecutableKatDecisionBlocker> =
        when (stage) {
            SkaldVaultV1ProviderExecutableKatFutureStage.NoExecutableKat,
            SkaldVaultV1ProviderExecutableKatFutureStage.ModelOnlyDecisionGate ->
                setOf(
                    SkaldVaultV1ProviderExecutableKatDecisionBlocker.ModelEvidenceOnly,
                    SkaldVaultV1ProviderExecutableKatDecisionBlocker.NoProviderKatExecutor,
                )
            SkaldVaultV1ProviderExecutableKatFutureStage.TestOnlyDeterministicProviderKatPrototype,
            SkaldVaultV1ProviderExecutableKatFutureStage.TestOnlyRandomizedBehavioralProviderKatPrototype ->
                setOf(
                    SkaldVaultV1ProviderExecutableKatDecisionBlocker.NoExecutableProviderImplementation,
                    SkaldVaultV1ProviderExecutableKatDecisionBlocker.NoProviderKatExecutor,
                    SkaldVaultV1ProviderExecutableKatDecisionBlocker.TestOnlyEvidenceCannotAuthorizeProduction,
                )
            SkaldVaultV1ProviderExecutableKatFutureStage.NonProductionProviderSelfTest,
            SkaldVaultV1ProviderExecutableKatFutureStage.ProductionProviderStartupSelfTest,
            SkaldVaultV1ProviderExecutableKatFutureStage.ReleaseValidationKat ->
                setOf(
                    SkaldVaultV1ProviderExecutableKatDecisionBlocker.NoExecutableProviderImplementation,
                    SkaldVaultV1ProviderExecutableKatDecisionBlocker.NoProviderKatExecutor,
                    SkaldVaultV1ProviderExecutableKatDecisionBlocker.ProductionProviderSelectableFalse,
                    SkaldVaultV1ProviderExecutableKatDecisionBlocker.ReleaseReviewMissing,
                )
            SkaldVaultV1ProviderExecutableKatFutureStage.MainnetReleaseValidation ->
                setOf(
                    SkaldVaultV1ProviderExecutableKatDecisionBlocker.MainnetDisabled,
                    SkaldVaultV1ProviderExecutableKatDecisionBlocker.ReleaseReviewMissing,
                )
        }

    private fun externalEvidenceStatus(
        kind: SkaldVaultV1ProviderExecutableKatExternalEvidenceKind,
    ): SkaldVaultV1ProviderExecutableKatDecisionGateStatus =
        when (kind) {
            SkaldVaultV1ProviderExecutableKatExternalEvidenceKind.DependencyLevelDesktopAndroidKatEvidence,
            SkaldVaultV1ProviderExecutableKatExternalEvidenceKind.PublicVectorDocumentation,
            SkaldVaultV1ProviderExecutableKatExternalEvidenceKind.TestOnlyProviderKatHarnessEvidence,
            SkaldVaultV1ProviderExecutableKatExternalEvidenceKind.WarningOnlyEvidence,
            SkaldVaultV1ProviderExecutableKatExternalEvidenceKind.UserConsentOverride,
            SkaldVaultV1ProviderExecutableKatExternalEvidenceKind.ReleaseOrMainnetEvidence ->
                SkaldVaultV1ProviderExecutableKatDecisionGateStatus.ExecutableKatNotAuthorized
            SkaldVaultV1ProviderExecutableKatExternalEvidenceKind.ProviderImplementationEvidence,
            SkaldVaultV1ProviderExecutableKatExternalEvidenceKind.ProviderKatExecutorEvidence ->
                SkaldVaultV1ProviderExecutableKatDecisionGateStatus.StillDisabled
        }

    private fun externalEvidenceBlockers(
        kind: SkaldVaultV1ProviderExecutableKatExternalEvidenceKind,
    ): Set<SkaldVaultV1ProviderExecutableKatDecisionBlocker> =
        when (kind) {
            SkaldVaultV1ProviderExecutableKatExternalEvidenceKind.DependencyLevelDesktopAndroidKatEvidence ->
                setOf(SkaldVaultV1ProviderExecutableKatDecisionBlocker.DependencyLevelKatsCannotAuthorizeProviderKats)
            SkaldVaultV1ProviderExecutableKatExternalEvidenceKind.PublicVectorDocumentation ->
                setOf(
                    SkaldVaultV1ProviderExecutableKatDecisionBlocker.PublicVectorDocumentationCannotAuthorizeExecution,
                )
            SkaldVaultV1ProviderExecutableKatExternalEvidenceKind.TestOnlyProviderKatHarnessEvidence ->
                setOf(SkaldVaultV1ProviderExecutableKatDecisionBlocker.TestOnlyHarnessCannotAuthorizeProduction)
            SkaldVaultV1ProviderExecutableKatExternalEvidenceKind.WarningOnlyEvidence ->
                setOf(SkaldVaultV1ProviderExecutableKatDecisionBlocker.WarningOnlyEvidenceCannotAuthorize)
            SkaldVaultV1ProviderExecutableKatExternalEvidenceKind.UserConsentOverride ->
                setOf(SkaldVaultV1ProviderExecutableKatDecisionBlocker.UserConsentCannotOverrideMissingHardGates)
            SkaldVaultV1ProviderExecutableKatExternalEvidenceKind.ReleaseOrMainnetEvidence ->
                setOf(
                    SkaldVaultV1ProviderExecutableKatDecisionBlocker.ReleaseReviewMissing,
                    SkaldVaultV1ProviderExecutableKatDecisionBlocker.MainnetDisabled,
                )
            SkaldVaultV1ProviderExecutableKatExternalEvidenceKind.ProviderImplementationEvidence ->
                setOf(SkaldVaultV1ProviderExecutableKatDecisionBlocker.NoExecutableProviderImplementation)
            SkaldVaultV1ProviderExecutableKatExternalEvidenceKind.ProviderKatExecutorEvidence ->
                setOf(SkaldVaultV1ProviderExecutableKatDecisionBlocker.NoProviderKatExecutor)
        }

    private val baseStatuses: Set<SkaldVaultV1ProviderExecutableKatDecisionGateStatus> =
        setOf(
            SkaldVaultV1ProviderExecutableKatDecisionGateStatus.DecisionGateModeled,
            SkaldVaultV1ProviderExecutableKatDecisionGateStatus.FutureEvidenceListDefined,
            SkaldVaultV1ProviderExecutableKatDecisionGateStatus.StillDisabled,
            SkaldVaultV1ProviderExecutableKatDecisionGateStatus.EvidenceOnly,
            SkaldVaultV1ProviderExecutableKatDecisionGateStatus.ExecutableKatNotAuthorized,
            SkaldVaultV1ProviderExecutableKatDecisionGateStatus.TestOnlyExecutableKatNotAuthorized,
            SkaldVaultV1ProviderExecutableKatDecisionGateStatus.ProductionExecutableKatNotAuthorized,
            SkaldVaultV1ProviderExecutableKatDecisionGateStatus.ProviderSelectionAuthorizationBlocked,
            SkaldVaultV1ProviderExecutableKatDecisionGateStatus.ProductionProviderSelectabilityBlocked,
            SkaldVaultV1ProviderExecutableKatDecisionGateStatus.VaultCreationUnlockPersistenceBlocked,
            SkaldVaultV1ProviderExecutableKatDecisionGateStatus.StorageReadinessBlocked,
            SkaldVaultV1ProviderExecutableKatDecisionGateStatus.MainnetBlocked,
        )

    private val baseBlockers: Set<SkaldVaultV1ProviderExecutableKatDecisionBlocker> =
        setOf(
            SkaldVaultV1ProviderExecutableKatDecisionBlocker.DecisionGateStillDisabled,
            SkaldVaultV1ProviderExecutableKatDecisionBlocker.ModelEvidenceOnly,
            SkaldVaultV1ProviderExecutableKatDecisionBlocker.NoExecutableProviderImplementation,
            SkaldVaultV1ProviderExecutableKatDecisionBlocker.NoProviderKatExecutor,
            SkaldVaultV1ProviderExecutableKatDecisionBlocker.ProviderSelectionDisabledProviderOnly,
            SkaldVaultV1ProviderExecutableKatDecisionBlocker.ProductionProviderSelectableFalse,
            SkaldVaultV1ProviderExecutableKatDecisionBlocker.ProviderOperationAuthorizationBlocked,
            SkaldVaultV1ProviderExecutableKatDecisionBlocker.RuntimeRandomnessAuthorizationBlocked,
            SkaldVaultV1ProviderExecutableKatDecisionBlocker.KdfCalibrationNotFinal,
            SkaldVaultV1ProviderExecutableKatDecisionBlocker.PassphrasePolicyNotExecutable,
            SkaldVaultV1ProviderExecutableKatDecisionBlocker.SecureStorageDisabled,
            SkaldVaultV1ProviderExecutableKatDecisionBlocker.SecureMetadataStorageDisabled,
            SkaldVaultV1ProviderExecutableKatDecisionBlocker.VaultCreationBlocked,
            SkaldVaultV1ProviderExecutableKatDecisionBlocker.VaultUnlockBlocked,
            SkaldVaultV1ProviderExecutableKatDecisionBlocker.PersistenceReadinessBlocked,
            SkaldVaultV1ProviderExecutableKatDecisionBlocker.StorageReadinessBlocked,
            SkaldVaultV1ProviderExecutableKatDecisionBlocker.LifecycleLockPolicyNotExecutable,
            SkaldVaultV1ProviderExecutableKatDecisionBlocker.RedactionLeakageGatesIncomplete,
            SkaldVaultV1ProviderExecutableKatDecisionBlocker.ClearWipeGatesIncomplete,
            SkaldVaultV1ProviderExecutableKatDecisionBlocker.MigrationCorruptionGatesIncomplete,
            SkaldVaultV1ProviderExecutableKatDecisionBlocker.WarningOnlyEvidenceCannotAuthorize,
            SkaldVaultV1ProviderExecutableKatDecisionBlocker.UserConsentCannotOverrideMissingHardGates,
            SkaldVaultV1ProviderExecutableKatDecisionBlocker.TestOnlyEvidenceCannotAuthorizeProduction,
            SkaldVaultV1ProviderExecutableKatDecisionBlocker.MainnetDisabled,
        )
}
