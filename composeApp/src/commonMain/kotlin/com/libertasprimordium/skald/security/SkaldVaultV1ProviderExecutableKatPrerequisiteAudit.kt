package com.libertasprimordium.skald.security

interface SkaldVaultV1ProviderExecutableKatPrerequisiteAudit {
    fun audit(
        request: SkaldVaultV1ProviderExecutableKatPrerequisiteAuditRequest =
            SkaldVaultV1ProviderExecutableKatPrerequisiteAuditRequest.currentEvidence(),
    ): SkaldVaultV1ProviderExecutableKatPrerequisiteAuditResult<
        SkaldVaultV1ProviderExecutableKatPrerequisiteAuditEvidence,
    >
}

enum class SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource(val label: String) {
    ExecutableKatDecisionGate("executable KAT decision gate"),
    ProviderKatExecutionIsolation("provider KAT execution isolation"),
    ProviderBoundary("provider boundary"),
    ProviderKatContract("provider KAT contract"),
    TestProviderKatHarness("test provider KAT harness"),
    ProviderSelectionBoundary("provider selection boundary"),
    RegistryIsolation("provider registry isolation"),
    FactoryIsolation("provider factory isolation"),
    OperationDispatchIsolation("provider operation dispatch isolation"),
    ProviderInterfaceContractAudit("provider interface contract audit"),
    ProviderPromotionBlockers("provider promotion blockers"),
    ProductionProviderAcceptanceContract("production provider acceptance contract"),
    RuntimeRandomnessProviderChecks("runtime randomness provider checks"),
    Argon2idCalibrationPolicy("Argon2id calibration policy"),
    Argon2idParameterPolicy("Argon2id parameter policy"),
    AndroidCompatibilityEntropyPolicy("Android compatibility entropy policy"),
    HeaderCommitmentAadContract("header commitment AAD contract"),
    KeyExpansionCommitmentPolicy("key expansion commitment policy"),
    CanonicalHeaderHkdfHmacVectors("canonical header HKDF HMAC vectors"),
    SecureStorageBoundary("secure storage boundary"),
    SecureMetadataBoundary("secure metadata boundary"),
    EncryptedVaultReadinessPolicy("encrypted vault readiness policy"),
    ReadmeSecurityStatus("README security status"),
    BuildHistory("build history"),
    DependencyLevelKatEvidence("dependency-level KAT evidence"),
    PublicVectorDocumentation("public vector documentation"),
    WarningOnlyEvidence("warning-only evidence"),
    UserConsentRequest("user consent request"),
    ReleaseMainnetReviewEvidence("release/mainnet review evidence"),
}

enum class SkaldVaultV1ProviderExecutableKatPrerequisiteCategory(val label: String) {
    ProviderBoundaryReviewed("provider boundary reviewed"),
    ProviderInterfaceContractAudited("provider interface contract audited"),
    ProviderImplementationExists("provider implementation exists"),
    ProviderImplementationIsStillNonProduction("provider implementation remains non-production"),
    ProviderFactoryIsolationReviewed("provider factory isolation reviewed"),
    ProviderRegistryIsolationReviewed("provider registry isolation reviewed"),
    ProviderOperationDispatchIsolationReviewed("provider operation dispatch isolation reviewed"),
    ProviderKatExecutionIsolationReviewed("provider KAT execution isolation reviewed"),
    ProviderOperationAuthorizationReviewed("provider operation authorization reviewed"),
    RuntimeRandomnessAuthorizationReviewed("runtime randomness authorization reviewed"),
    KdfCalibrationReviewed("KDF calibration reviewed"),
    KdfParametersSelected("KDF parameters selected"),
    PassphrasePolicyReviewed("credential policy reviewed"),
    ClearWipeStrategyReviewed("clear/wipe strategy reviewed"),
    RedactionLeakagePolicyReviewed("redaction/leakage policy reviewed"),
    MigrationCorruptionPolicyReviewed("migration/corruption policy reviewed"),
    SecureStorageReviewed("secure storage reviewed"),
    SecureMetadataReviewed("secure metadata reviewed"),
    VaultCreationAuthorizationReviewed("vault creation authorization reviewed"),
    VaultUnlockAuthorizationReviewed("vault unlock authorization reviewed"),
    PersistenceReadinessReviewed("persistence readiness reviewed"),
    SourceSetConfinementReviewed("source-set confinement reviewed"),
    ProviderLevelPositiveKatsDefined("provider-level positive KATs defined"),
    ProviderLevelNegativeKatsDefined("provider-level negative KATs defined"),
    ProviderLevelRedactionKatsDefined("provider-level redaction KATs defined"),
    ProviderLevelRandomnessChecksDefined("provider-level randomness checks defined"),
    ProviderLevelStorageSeparationChecksDefined("provider-level storage-separation checks defined"),
    ProviderLevelPlatformChecksDefined("provider-level platform checks defined"),
    PublicVectorProvenanceReviewed("public vector provenance reviewed"),
    CanonicalSkaldVectorProvenanceReviewed("canonical Skald vector provenance reviewed"),
    AndroidRuntimeTestStrategyReviewed("Android runtime test strategy reviewed"),
    LinuxJvmRuntimeTestStrategyReviewed("Linux/JVM runtime test strategy reviewed"),
    ProductionProviderAcceptanceReviewed("production provider acceptance reviewed"),
    ReleaseMainnetReviewCompleted("release/mainnet review completed"),
}

enum class SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification(val label: String) {
    ExplicitlyModeledEvidenceExists("explicitly modeled evidence exists"),
    PartialModelOnlyEvidenceExists("partial model-only evidence exists"),
    DocumentationOnlyEvidenceExists("documentation-only evidence exists"),
    TestOnlyEvidenceExists("test-only evidence exists"),
    FutureRequiredEvidenceMissing("future required evidence missing"),
    BlockedByHardGate("blocked by hard gate"),
    NonAuthorizingEvidenceOnly("non-authorizing evidence only"),
    IntentionallyOutOfScope("intentionally out of scope"),
}

enum class SkaldVaultV1ProviderExecutableKatPrerequisiteAuditStatus(val label: String) {
    AuditModeled("audit modeled"),
    PrerequisitesClassified("prerequisites classified"),
    StillDisabled("still disabled"),
    EvidenceOnly("evidence only"),
    BlockedFailClosed("blocked fail-closed"),
    ExecutableKatIntroductionBlocked("executable KAT introduction blocked"),
    TestOnlyExecutableKatIntroductionBlocked("test-only executable KAT introduction blocked"),
    ProductionExecutableKatIntroductionBlocked("production executable KAT introduction blocked"),
    ProviderSelectionBlocked("provider selection blocked"),
    ProductionProviderSelectabilityBlocked("production provider selectability blocked"),
    VaultCreationBlocked("vault creation blocked"),
    VaultUnlockBlocked("vault unlock blocked"),
    VaultPersistenceBlocked("vault persistence blocked"),
    ProductionSyncBlocked("production sync blocked"),
    MainnetBlocked("mainnet blocked"),
}

enum class SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker(val label: String) {
    NoExecutableProviderImplementation("no executable provider implementation"),
    NoExecutableProviderKatExecutor("no executable provider KAT executor"),
    ProviderSelectionDisabledProviderOnly("provider selection disabled-provider-only"),
    ProductionProviderSelectableFalse("productionProviderSelectable false"),
    ProviderOperationAuthorizationBlocked("provider operation authorization blocked"),
    RuntimeRandomnessAuthorizationBlocked("runtime randomness authorization blocked"),
    KdfCalibrationNotFinal("KDF calibration not final"),
    KdfParameterPolicyNonFinal("KDF parameter policy non-final"),
    PassphrasePolicyNotExecutable("credential policy not executable"),
    SecureStorageDisabled("secure storage disabled"),
    SecureMetadataDisabled("secure metadata disabled"),
    VaultCreationBlocked("vault creation blocked"),
    VaultUnlockBlocked("vault unlock blocked"),
    PersistenceReadinessBlocked("persistence readiness blocked"),
    SourceSetConfinementNotExecutableProviderReviewed(
        "source-set confinement not executable-provider-reviewed",
    ),
    ProviderLevelKatExecutorAbsent("provider-level KAT executor absent"),
    TestOnlyEvidenceCannotAuthorizeProduction("test-only evidence cannot authorize production"),
    DependencyLevelKatEvidenceCannotAuthorizeProviderExecution(
        "dependency-level KAT evidence cannot authorize provider execution",
    ),
    PublicVectorDocumentationCannotAuthorizeProviderExecution(
        "public vector documentation cannot authorize provider execution",
    ),
    WarningOnlyEvidenceCannotAuthorize("warning-only evidence cannot authorize"),
    UserConsentCannotOverrideMissingHardGates("user consent cannot override missing hard gates"),
    ReleaseReviewMissing("release review missing"),
    MainnetDisabled("mainnet disabled"),
    ClearWipeReviewNonExecutable("clear/wipe review non-executable"),
    RedactionLeakageReviewNonExecutable("redaction/leakage review non-executable"),
    MigrationCorruptionReviewNonExecutable("migration/corruption review non-executable"),
}

enum class SkaldVaultV1ProviderExecutableKatPrerequisiteWarning(val label: String) {
    ModeledEvidenceDoesNotAuthorizeExecution("modeled evidence does not authorize execution"),
    DocumentationDoesNotAuthorizeExecution("documentation does not authorize execution"),
    TestEvidenceDoesNotAuthorizeProduction("test evidence does not authorize production"),
    DependencyKatEvidenceDoesNotAuthorizeProviderExecution(
        "dependency KAT evidence does not authorize provider execution",
    ),
    PublicVectorEvidenceDoesNotAuthorizeProviderExecution(
        "public vector evidence does not authorize provider execution",
    ),
    ConsentCannotOverrideHardGates("consent cannot override hard gates"),
    WarningOnlyCannotAuthorize("warning-only evidence cannot authorize"),
}

enum class SkaldVaultV1ProviderExecutableKatPrerequisiteRedactionClass(val label: String) {
    PolicyIdsOnly("policy identifiers only"),
    EvidenceClassNamesOnly("evidence class names only"),
    BlockerLabelsOnly("blocker labels only"),
    SafeIdsOnly("safe identifiers only"),
    NoRawMaterial("no raw material"),
    NoProviderReferences("no provider references"),
    NoCryptoReferences("no crypto references"),
    NoDiagnosticPayloads("no diagnostic payloads"),
    NoFilesystemLocations("no filesystem locations"),
    NoStorageReferences("no storage references"),
    NoBackendReferences("no backend references"),
}

data class SkaldVaultV1ProviderExecutableKatPrerequisiteAuthorizationStatus(
    val canAuthorizeProviderExecution: Boolean,
    val canAuthorizeExecutableKatPath: Boolean,
    val canAuthorizeTestOnlyExecutableKatPath: Boolean,
    val canAuthorizeProductionExecutableKatPath: Boolean,
    val canAuthorizeProviderSelection: Boolean,
    val canSetProductionProviderSelectable: Boolean,
    val canAuthorizeVaultCreation: Boolean,
    val canAuthorizeVaultUnlock: Boolean,
    val canAuthorizeVaultPersistence: Boolean,
    val canAuthorizeProductionSync: Boolean,
    val canAuthorizeMainnet: Boolean,
) {
    val anyAuthorizationGranted: Boolean
        get() =
            canAuthorizeProviderExecution ||
                canAuthorizeExecutableKatPath ||
                canAuthorizeTestOnlyExecutableKatPath ||
                canAuthorizeProductionExecutableKatPath ||
                canAuthorizeProviderSelection ||
                canSetProductionProviderSelectable ||
                canAuthorizeVaultCreation ||
                canAuthorizeVaultUnlock ||
                canAuthorizeVaultPersistence ||
                canAuthorizeProductionSync ||
                canAuthorizeMainnet

    companion object {
        val Blocked = SkaldVaultV1ProviderExecutableKatPrerequisiteAuthorizationStatus(
            canAuthorizeProviderExecution = false,
            canAuthorizeExecutableKatPath = false,
            canAuthorizeTestOnlyExecutableKatPath = false,
            canAuthorizeProductionExecutableKatPath = false,
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

data class SkaldVaultV1ProviderExecutableKatPrerequisiteDisabledCapability(
    val auditModeled: Boolean,
    val prerequisitesClassified: Boolean,
    val providerSelectionDisabledProviderOnly: Boolean,
    val productionProviderSelectable: Boolean,
    val canAuthorizeProviderExecution: Boolean,
    val canAuthorizeExecutableKatPath: Boolean,
    val canAuthorizeTestOnlyExecutableKatPath: Boolean,
    val canAuthorizeProductionExecutableKatPath: Boolean,
    val canAuthorizeProviderSelection: Boolean,
    val canSetProductionProviderSelectable: Boolean,
    val canAuthorizeVaultCreation: Boolean,
    val canAuthorizeVaultUnlock: Boolean,
    val canAuthorizeVaultPersistence: Boolean,
    val canAuthorizeProductionSync: Boolean,
    val canAuthorizeMainnet: Boolean,
) {
    companion object {
        val Current = SkaldVaultV1ProviderExecutableKatPrerequisiteDisabledCapability(
            auditModeled = true,
            prerequisitesClassified = true,
            providerSelectionDisabledProviderOnly = true,
            productionProviderSelectable = false,
            canAuthorizeProviderExecution = false,
            canAuthorizeExecutableKatPath = false,
            canAuthorizeTestOnlyExecutableKatPath = false,
            canAuthorizeProductionExecutableKatPath = false,
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

data class SkaldVaultV1ProviderExecutableKatPrerequisiteSourceFinding(
    val source: SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource,
    val classification: SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification,
    val blockers: Set<SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker>,
    val warnings: Set<SkaldVaultV1ProviderExecutableKatPrerequisiteWarning>,
    val authorizationStatus: SkaldVaultV1ProviderExecutableKatPrerequisiteAuthorizationStatus,
    val evidenceOnly: Boolean,
    val safeSourceId: String,
    val redactionClass: SkaldVaultV1ProviderExecutableKatPrerequisiteRedactionClass,
)

data class SkaldVaultV1ProviderExecutableKatPrerequisiteFinding(
    val category: SkaldVaultV1ProviderExecutableKatPrerequisiteCategory,
    val classification: SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification,
    val sources: Set<SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource>,
    val blockers: Set<SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker>,
    val warnings: Set<SkaldVaultV1ProviderExecutableKatPrerequisiteWarning>,
    val authorizationStatus: SkaldVaultV1ProviderExecutableKatPrerequisiteAuthorizationStatus,
    val currentEvidenceExists: Boolean,
    val futureRequiredBeforeTestOnlyExecutableKat: Boolean,
    val safeFindingId: String,
    val redactionClass: SkaldVaultV1ProviderExecutableKatPrerequisiteRedactionClass,
) {
    val authorizesExecution: Boolean
        get() = authorizationStatus.anyAuthorizationGranted
}

data class SkaldVaultV1ProviderExecutableKatPrerequisiteFutureWorkItem(
    val category: SkaldVaultV1ProviderExecutableKatPrerequisiteCategory,
    val safeWorkId: String,
    val blockers: Set<SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker>,
    val requiredBeforeTestOnlyExecutableKat: Boolean,
    val requiredBeforeProductionExecutableKat: Boolean,
    val authorizesCurrentExecution: Boolean,
)

data class SkaldVaultV1ProviderExecutableKatPrerequisiteAuditRequest(
    val categories: Set<SkaldVaultV1ProviderExecutableKatPrerequisiteCategory>,
    val sources: Set<SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource>,
    val warningOnlyEvidenceClaimed: Boolean,
    val userConsentOverrideRequested: Boolean,
    val releaseMainnetEvidenceClaimed: Boolean,
    val safeAuditId: String,
) {
    override fun toString(): String =
        "SkaldVaultV1ProviderExecutableKatPrerequisiteAuditRequest(" +
            "categories=${categories.map { it.name }}, " +
            "sources=${sources.map { it.name }}, " +
            "warningOnlyEvidenceClaimed=$warningOnlyEvidenceClaimed, " +
            "userConsentOverrideRequested=$userConsentOverrideRequested, " +
            "releaseMainnetEvidenceClaimed=$releaseMainnetEvidenceClaimed, " +
            "safeAuditId=<redacted>" +
            ")"

    companion object {
        fun currentEvidence(
            safeAuditId: String = "current-provider-executable-kat-prerequisite-audit",
        ): SkaldVaultV1ProviderExecutableKatPrerequisiteAuditRequest =
            SkaldVaultV1ProviderExecutableKatPrerequisiteAuditRequest(
                categories = SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.entries.toSet(),
                sources = SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.entries.toSet(),
                warningOnlyEvidenceClaimed = false,
                userConsentOverrideRequested = false,
                releaseMainnetEvidenceClaimed = false,
                safeAuditId = safeAuditId,
            )
    }
}

data class SkaldVaultV1ProviderExecutableKatPrerequisiteAuditEvidence(
    val status: SkaldVaultV1ProviderExecutableKatPrerequisiteAuditStatus,
    val statuses: Set<SkaldVaultV1ProviderExecutableKatPrerequisiteAuditStatus>,
    val sourceFindings: List<SkaldVaultV1ProviderExecutableKatPrerequisiteSourceFinding>,
    val prerequisiteFindings: List<SkaldVaultV1ProviderExecutableKatPrerequisiteFinding>,
    val blockers: Set<SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker>,
    val warnings: Set<SkaldVaultV1ProviderExecutableKatPrerequisiteWarning>,
    val redactionClasses: Set<SkaldVaultV1ProviderExecutableKatPrerequisiteRedactionClass>,
    val disabledCapabilities: SkaldVaultV1ProviderExecutableKatPrerequisiteDisabledCapability,
    val authorizationStatus: SkaldVaultV1ProviderExecutableKatPrerequisiteAuthorizationStatus,
    val futureRequiredWork: List<SkaldVaultV1ProviderExecutableKatPrerequisiteFutureWorkItem>,
    val auditModeled: Boolean,
    val blockedFailClosed: Boolean,
    val evidenceOnly: Boolean,
    val providerSelectionDisabledProviderOnly: Boolean,
    val productionProviderSelectable: Boolean,
    val executableProviderImplementationPresent: Boolean,
    val executableProviderKatExecutorPresent: Boolean,
    val modeledEvidenceCanAuthorizeExecution: Boolean,
    val sourceSetConfinementAuthorizesExecution: Boolean,
)

sealed interface SkaldVaultV1ProviderExecutableKatPrerequisiteAuditResult<out T> {
    val value: T

    data class Blocked<out T>(
        override val value: T,
    ) : SkaldVaultV1ProviderExecutableKatPrerequisiteAuditResult<T>
}

data class SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSummary(
    val policyId: String,
    val policyVersion: Int,
    val categories: Set<SkaldVaultV1ProviderExecutableKatPrerequisiteCategory>,
    val sources: Set<SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource>,
    val classifications: Set<SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification>,
    val blockers: Set<SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker>,
    val redactionClasses: Set<SkaldVaultV1ProviderExecutableKatPrerequisiteRedactionClass>,
    val authorizationStatus: SkaldVaultV1ProviderExecutableKatPrerequisiteAuthorizationStatus,
)

object SkaldVaultV1ProviderExecutableKatPrerequisiteAuditPolicy :
    SkaldVaultV1ProviderExecutableKatPrerequisiteAudit {
    const val POLICY_ID: String =
        "skald-vault-v1-provider-executable-kat-prerequisite-audit-v1"
    const val POLICY_VERSION: Int = 1

    override fun audit(
        request: SkaldVaultV1ProviderExecutableKatPrerequisiteAuditRequest,
    ): SkaldVaultV1ProviderExecutableKatPrerequisiteAuditResult<
        SkaldVaultV1ProviderExecutableKatPrerequisiteAuditEvidence,
    > = evaluate(request)

    fun evaluate(
        request: SkaldVaultV1ProviderExecutableKatPrerequisiteAuditRequest =
            SkaldVaultV1ProviderExecutableKatPrerequisiteAuditRequest.currentEvidence(),
    ): SkaldVaultV1ProviderExecutableKatPrerequisiteAuditResult.Blocked<
        SkaldVaultV1ProviderExecutableKatPrerequisiteAuditEvidence,
    > =
        SkaldVaultV1ProviderExecutableKatPrerequisiteAuditResult.Blocked(
            currentAuditEvidence(request),
        )

    fun currentPolicySummary(): SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSummary =
        SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSummary(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            categories = SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.entries.toSet(),
            sources = SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.entries.toSet(),
            classifications =
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.entries.toSet(),
            blockers = currentBlockers(),
            redactionClasses =
                SkaldVaultV1ProviderExecutableKatPrerequisiteRedactionClass.entries.toSet(),
            authorizationStatus =
                SkaldVaultV1ProviderExecutableKatPrerequisiteAuthorizationStatus.Blocked,
        )

    fun currentAuditEvidence(
        request: SkaldVaultV1ProviderExecutableKatPrerequisiteAuditRequest =
            SkaldVaultV1ProviderExecutableKatPrerequisiteAuditRequest.currentEvidence(),
    ): SkaldVaultV1ProviderExecutableKatPrerequisiteAuditEvidence {
        val findings = currentPrerequisiteFindings()
        val sourceFindings = currentSourceFindings()
        val blockers = (
            currentBlockers() +
                findings.flatMap { it.blockers } +
                sourceFindings.flatMap { it.blockers } +
                requestBlockers(request)
            ).toSet()
        val warnings = (
            currentWarnings() +
                findings.flatMap { it.warnings } +
                sourceFindings.flatMap { it.warnings }
            ).toSet()

        return SkaldVaultV1ProviderExecutableKatPrerequisiteAuditEvidence(
            status = SkaldVaultV1ProviderExecutableKatPrerequisiteAuditStatus.BlockedFailClosed,
            statuses = currentStatuses(),
            sourceFindings = sourceFindings,
            prerequisiteFindings = findings,
            blockers = blockers,
            warnings = warnings,
            redactionClasses =
                SkaldVaultV1ProviderExecutableKatPrerequisiteRedactionClass.entries.toSet(),
            disabledCapabilities =
                SkaldVaultV1ProviderExecutableKatPrerequisiteDisabledCapability.Current,
            authorizationStatus =
                SkaldVaultV1ProviderExecutableKatPrerequisiteAuthorizationStatus.Blocked,
            futureRequiredWork = currentFutureRequiredWork(findings),
            auditModeled = true,
            blockedFailClosed = true,
            evidenceOnly = true,
            providerSelectionDisabledProviderOnly = true,
            productionProviderSelectable = false,
            executableProviderImplementationPresent = false,
            executableProviderKatExecutorPresent = false,
            modeledEvidenceCanAuthorizeExecution = false,
            sourceSetConfinementAuthorizesExecution = false,
        )
    }

    fun currentStatuses(): Set<SkaldVaultV1ProviderExecutableKatPrerequisiteAuditStatus> =
        setOf(
            SkaldVaultV1ProviderExecutableKatPrerequisiteAuditStatus.AuditModeled,
            SkaldVaultV1ProviderExecutableKatPrerequisiteAuditStatus.PrerequisitesClassified,
            SkaldVaultV1ProviderExecutableKatPrerequisiteAuditStatus.StillDisabled,
            SkaldVaultV1ProviderExecutableKatPrerequisiteAuditStatus.EvidenceOnly,
            SkaldVaultV1ProviderExecutableKatPrerequisiteAuditStatus.BlockedFailClosed,
            SkaldVaultV1ProviderExecutableKatPrerequisiteAuditStatus.ExecutableKatIntroductionBlocked,
            SkaldVaultV1ProviderExecutableKatPrerequisiteAuditStatus.TestOnlyExecutableKatIntroductionBlocked,
            SkaldVaultV1ProviderExecutableKatPrerequisiteAuditStatus.ProductionExecutableKatIntroductionBlocked,
            SkaldVaultV1ProviderExecutableKatPrerequisiteAuditStatus.ProviderSelectionBlocked,
            SkaldVaultV1ProviderExecutableKatPrerequisiteAuditStatus.ProductionProviderSelectabilityBlocked,
            SkaldVaultV1ProviderExecutableKatPrerequisiteAuditStatus.VaultCreationBlocked,
            SkaldVaultV1ProviderExecutableKatPrerequisiteAuditStatus.VaultUnlockBlocked,
            SkaldVaultV1ProviderExecutableKatPrerequisiteAuditStatus.VaultPersistenceBlocked,
            SkaldVaultV1ProviderExecutableKatPrerequisiteAuditStatus.ProductionSyncBlocked,
            SkaldVaultV1ProviderExecutableKatPrerequisiteAuditStatus.MainnetBlocked,
        )

    fun currentBlockers(): Set<SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker> =
        setOf(
            SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.NoExecutableProviderImplementation,
            SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.NoExecutableProviderKatExecutor,
            SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.ProviderSelectionDisabledProviderOnly,
            SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.ProductionProviderSelectableFalse,
            SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.ProviderOperationAuthorizationBlocked,
            SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.RuntimeRandomnessAuthorizationBlocked,
            SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.KdfCalibrationNotFinal,
            SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.KdfParameterPolicyNonFinal,
            SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.PassphrasePolicyNotExecutable,
            SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.SecureStorageDisabled,
            SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.SecureMetadataDisabled,
            SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.VaultCreationBlocked,
            SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.VaultUnlockBlocked,
            SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.PersistenceReadinessBlocked,
            SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.SourceSetConfinementNotExecutableProviderReviewed,
            SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.ProviderLevelKatExecutorAbsent,
            SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.TestOnlyEvidenceCannotAuthorizeProduction,
            SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.DependencyLevelKatEvidenceCannotAuthorizeProviderExecution,
            SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.PublicVectorDocumentationCannotAuthorizeProviderExecution,
            SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.WarningOnlyEvidenceCannotAuthorize,
            SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.UserConsentCannotOverrideMissingHardGates,
            SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.ReleaseReviewMissing,
            SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.MainnetDisabled,
        )

    fun currentWarnings(): Set<SkaldVaultV1ProviderExecutableKatPrerequisiteWarning> =
        setOf(
            SkaldVaultV1ProviderExecutableKatPrerequisiteWarning.ModeledEvidenceDoesNotAuthorizeExecution,
            SkaldVaultV1ProviderExecutableKatPrerequisiteWarning.DocumentationDoesNotAuthorizeExecution,
            SkaldVaultV1ProviderExecutableKatPrerequisiteWarning.TestEvidenceDoesNotAuthorizeProduction,
            SkaldVaultV1ProviderExecutableKatPrerequisiteWarning.DependencyKatEvidenceDoesNotAuthorizeProviderExecution,
            SkaldVaultV1ProviderExecutableKatPrerequisiteWarning.PublicVectorEvidenceDoesNotAuthorizeProviderExecution,
            SkaldVaultV1ProviderExecutableKatPrerequisiteWarning.ConsentCannotOverrideHardGates,
            SkaldVaultV1ProviderExecutableKatPrerequisiteWarning.WarningOnlyCannotAuthorize,
        )

    fun requestBlockers(
        request: SkaldVaultV1ProviderExecutableKatPrerequisiteAuditRequest,
    ): Set<SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker> =
        buildSet {
            if (request.warningOnlyEvidenceClaimed) {
                add(SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.WarningOnlyEvidenceCannotAuthorize)
            }
            if (request.userConsentOverrideRequested) {
                add(
                    SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.UserConsentCannotOverrideMissingHardGates,
                )
            }
            if (request.releaseMainnetEvidenceClaimed) {
                add(SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.ReleaseReviewMissing)
                add(SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.MainnetDisabled)
            }
        }

    fun currentSourceFindings(): List<SkaldVaultV1ProviderExecutableKatPrerequisiteSourceFinding> =
        listOf(
            source(
                SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.ExecutableKatDecisionGate,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.ExplicitlyModeledEvidenceExists,
            ),
            source(
                SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.ProviderKatExecutionIsolation,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.ExplicitlyModeledEvidenceExists,
            ),
            source(
                SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.ProviderBoundary,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.ExplicitlyModeledEvidenceExists,
            ),
            source(
                SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.ProviderKatContract,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.DocumentationOnlyEvidenceExists,
            ),
            source(
                SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.TestProviderKatHarness,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.TestOnlyEvidenceExists,
                setOf(
                    SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.TestOnlyEvidenceCannotAuthorizeProduction,
                ),
                setOf(
                    SkaldVaultV1ProviderExecutableKatPrerequisiteWarning.TestEvidenceDoesNotAuthorizeProduction,
                ),
            ),
            source(
                SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.ProviderSelectionBoundary,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.ExplicitlyModeledEvidenceExists,
                setOf(
                    SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.ProviderSelectionDisabledProviderOnly,
                ),
            ),
            source(
                SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.RegistryIsolation,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.ExplicitlyModeledEvidenceExists,
            ),
            source(
                SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.FactoryIsolation,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.ExplicitlyModeledEvidenceExists,
            ),
            source(
                SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.OperationDispatchIsolation,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.ExplicitlyModeledEvidenceExists,
            ),
            source(
                SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.ProviderInterfaceContractAudit,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.ExplicitlyModeledEvidenceExists,
            ),
            source(
                SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.ProviderPromotionBlockers,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.ExplicitlyModeledEvidenceExists,
            ),
            source(
                SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.ProductionProviderAcceptanceContract,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.ExplicitlyModeledEvidenceExists,
            ),
            source(
                SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.RuntimeRandomnessProviderChecks,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.PartialModelOnlyEvidenceExists,
                setOf(
                    SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.RuntimeRandomnessAuthorizationBlocked,
                ),
            ),
            source(
                SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.Argon2idCalibrationPolicy,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.PartialModelOnlyEvidenceExists,
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.KdfCalibrationNotFinal),
            ),
            source(
                SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.Argon2idParameterPolicy,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.PartialModelOnlyEvidenceExists,
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.KdfParameterPolicyNonFinal),
            ),
            source(
                SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.AndroidCompatibilityEntropyPolicy,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.DocumentationOnlyEvidenceExists,
            ),
            source(
                SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.HeaderCommitmentAadContract,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.NonAuthorizingEvidenceOnly,
            ),
            source(
                SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.KeyExpansionCommitmentPolicy,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.NonAuthorizingEvidenceOnly,
            ),
            source(
                SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.CanonicalHeaderHkdfHmacVectors,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.NonAuthorizingEvidenceOnly,
            ),
            source(
                SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.SecureStorageBoundary,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.ExplicitlyModeledEvidenceExists,
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.SecureStorageDisabled),
            ),
            source(
                SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.SecureMetadataBoundary,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.ExplicitlyModeledEvidenceExists,
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.SecureMetadataDisabled),
            ),
            source(
                SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.EncryptedVaultReadinessPolicy,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.ExplicitlyModeledEvidenceExists,
            ),
            source(
                SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.ReadmeSecurityStatus,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.DocumentationOnlyEvidenceExists,
            ),
            source(
                SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.BuildHistory,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.DocumentationOnlyEvidenceExists,
            ),
            source(
                SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.DependencyLevelKatEvidence,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.NonAuthorizingEvidenceOnly,
                setOf(
                    SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.DependencyLevelKatEvidenceCannotAuthorizeProviderExecution,
                ),
                setOf(
                    SkaldVaultV1ProviderExecutableKatPrerequisiteWarning.DependencyKatEvidenceDoesNotAuthorizeProviderExecution,
                ),
            ),
            source(
                SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.PublicVectorDocumentation,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.NonAuthorizingEvidenceOnly,
                setOf(
                    SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.PublicVectorDocumentationCannotAuthorizeProviderExecution,
                ),
                setOf(
                    SkaldVaultV1ProviderExecutableKatPrerequisiteWarning.PublicVectorEvidenceDoesNotAuthorizeProviderExecution,
                ),
            ),
            source(
                SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.WarningOnlyEvidence,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.NonAuthorizingEvidenceOnly,
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.WarningOnlyEvidenceCannotAuthorize),
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteWarning.WarningOnlyCannotAuthorize),
            ),
            source(
                SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.UserConsentRequest,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.BlockedByHardGate,
                setOf(
                    SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.UserConsentCannotOverrideMissingHardGates,
                ),
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteWarning.ConsentCannotOverrideHardGates),
            ),
            source(
                SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.ReleaseMainnetReviewEvidence,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.IntentionallyOutOfScope,
                setOf(
                    SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.ReleaseReviewMissing,
                    SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.MainnetDisabled,
                ),
            ),
        )

    fun currentPrerequisiteFindings(): List<SkaldVaultV1ProviderExecutableKatPrerequisiteFinding> =
        listOf(
            prerequisite(
                SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.ProviderBoundaryReviewed,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.ExplicitlyModeledEvidenceExists,
                setOf(
                    SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.ProviderBoundary,
                    SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.ExecutableKatDecisionGate,
                ),
            ),
            prerequisite(
                SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.ProviderInterfaceContractAudited,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.ExplicitlyModeledEvidenceExists,
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.ProviderInterfaceContractAudit),
            ),
            prerequisite(
                SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.ProviderImplementationExists,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.FutureRequiredEvidenceMissing,
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.ExecutableKatDecisionGate),
                setOf(
                    SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.NoExecutableProviderImplementation,
                ),
                currentEvidenceExists = false,
            ),
            prerequisite(
                SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.ProviderImplementationIsStillNonProduction,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.FutureRequiredEvidenceMissing,
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.ExecutableKatDecisionGate),
                setOf(
                    SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.NoExecutableProviderImplementation,
                ),
                currentEvidenceExists = false,
            ),
            prerequisite(
                SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.ProviderFactoryIsolationReviewed,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.ExplicitlyModeledEvidenceExists,
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.FactoryIsolation),
            ),
            prerequisite(
                SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.ProviderRegistryIsolationReviewed,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.ExplicitlyModeledEvidenceExists,
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.RegistryIsolation),
            ),
            prerequisite(
                SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.ProviderOperationDispatchIsolationReviewed,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.ExplicitlyModeledEvidenceExists,
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.OperationDispatchIsolation),
            ),
            prerequisite(
                SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.ProviderKatExecutionIsolationReviewed,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.ExplicitlyModeledEvidenceExists,
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.ProviderKatExecutionIsolation),
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.NoExecutableProviderKatExecutor),
            ),
            prerequisite(
                SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.ProviderOperationAuthorizationReviewed,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.ExplicitlyModeledEvidenceExists,
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.EncryptedVaultReadinessPolicy),
                setOf(
                    SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.ProviderOperationAuthorizationBlocked,
                ),
            ),
            prerequisite(
                SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.RuntimeRandomnessAuthorizationReviewed,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.ExplicitlyModeledEvidenceExists,
                setOf(
                    SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.RuntimeRandomnessProviderChecks,
                    SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.EncryptedVaultReadinessPolicy,
                ),
                setOf(
                    SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.RuntimeRandomnessAuthorizationBlocked,
                ),
            ),
            prerequisite(
                SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.KdfCalibrationReviewed,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.PartialModelOnlyEvidenceExists,
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.Argon2idCalibrationPolicy),
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.KdfCalibrationNotFinal),
            ),
            prerequisite(
                SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.KdfParametersSelected,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.BlockedByHardGate,
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.Argon2idParameterPolicy),
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.KdfParameterPolicyNonFinal),
            ),
            prerequisite(
                SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.PassphrasePolicyReviewed,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.PartialModelOnlyEvidenceExists,
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.EncryptedVaultReadinessPolicy),
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.PassphrasePolicyNotExecutable),
            ),
            prerequisite(
                SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.ClearWipeStrategyReviewed,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.PartialModelOnlyEvidenceExists,
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.EncryptedVaultReadinessPolicy),
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.ClearWipeReviewNonExecutable),
            ),
            prerequisite(
                SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.RedactionLeakagePolicyReviewed,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.PartialModelOnlyEvidenceExists,
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.EncryptedVaultReadinessPolicy),
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.RedactionLeakageReviewNonExecutable),
            ),
            prerequisite(
                SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.MigrationCorruptionPolicyReviewed,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.PartialModelOnlyEvidenceExists,
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.EncryptedVaultReadinessPolicy),
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.MigrationCorruptionReviewNonExecutable),
            ),
            prerequisite(
                SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.SecureStorageReviewed,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.ExplicitlyModeledEvidenceExists,
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.SecureStorageBoundary),
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.SecureStorageDisabled),
            ),
            prerequisite(
                SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.SecureMetadataReviewed,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.ExplicitlyModeledEvidenceExists,
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.SecureMetadataBoundary),
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.SecureMetadataDisabled),
            ),
            prerequisite(
                SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.VaultCreationAuthorizationReviewed,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.ExplicitlyModeledEvidenceExists,
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.EncryptedVaultReadinessPolicy),
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.VaultCreationBlocked),
            ),
            prerequisite(
                SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.VaultUnlockAuthorizationReviewed,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.ExplicitlyModeledEvidenceExists,
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.EncryptedVaultReadinessPolicy),
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.VaultUnlockBlocked),
            ),
            prerequisite(
                SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.PersistenceReadinessReviewed,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.ExplicitlyModeledEvidenceExists,
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.EncryptedVaultReadinessPolicy),
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.PersistenceReadinessBlocked),
            ),
            prerequisite(
                SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.SourceSetConfinementReviewed,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.PartialModelOnlyEvidenceExists,
                setOf(
                    SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.ProviderInterfaceContractAudit,
                    SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.FactoryIsolation,
                    SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.RegistryIsolation,
                ),
                setOf(
                    SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.SourceSetConfinementNotExecutableProviderReviewed,
                ),
            ),
            prerequisite(
                SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.ProviderLevelPositiveKatsDefined,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.TestOnlyEvidenceExists,
                setOf(
                    SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.ProviderKatContract,
                    SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.TestProviderKatHarness,
                ),
                setOf(
                    SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.ProviderLevelKatExecutorAbsent,
                    SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.TestOnlyEvidenceCannotAuthorizeProduction,
                ),
            ),
            prerequisite(
                SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.ProviderLevelNegativeKatsDefined,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.TestOnlyEvidenceExists,
                setOf(
                    SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.ProviderKatContract,
                    SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.TestProviderKatHarness,
                ),
                setOf(
                    SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.ProviderLevelKatExecutorAbsent,
                    SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.TestOnlyEvidenceCannotAuthorizeProduction,
                ),
            ),
            prerequisite(
                SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.ProviderLevelRedactionKatsDefined,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.PartialModelOnlyEvidenceExists,
                setOf(
                    SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.ProviderKatContract,
                    SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.EncryptedVaultReadinessPolicy,
                ),
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.ProviderLevelKatExecutorAbsent),
            ),
            prerequisite(
                SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.ProviderLevelRandomnessChecksDefined,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.DocumentationOnlyEvidenceExists,
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.RuntimeRandomnessProviderChecks),
                setOf(
                    SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.RuntimeRandomnessAuthorizationBlocked,
                    SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.ProviderLevelKatExecutorAbsent,
                ),
            ),
            prerequisite(
                SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.ProviderLevelStorageSeparationChecksDefined,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.DocumentationOnlyEvidenceExists,
                setOf(
                    SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.ProviderKatContract,
                    SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.SecureStorageBoundary,
                ),
                setOf(
                    SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.SecureStorageDisabled,
                    SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.ProviderLevelKatExecutorAbsent,
                ),
            ),
            prerequisite(
                SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.ProviderLevelPlatformChecksDefined,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.DocumentationOnlyEvidenceExists,
                setOf(
                    SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.AndroidCompatibilityEntropyPolicy,
                    SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.RuntimeRandomnessProviderChecks,
                ),
            ),
            prerequisite(
                SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.PublicVectorProvenanceReviewed,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.NonAuthorizingEvidenceOnly,
                setOf(
                    SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.PublicVectorDocumentation,
                    SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.DependencyLevelKatEvidence,
                ),
                setOf(
                    SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.PublicVectorDocumentationCannotAuthorizeProviderExecution,
                    SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.DependencyLevelKatEvidenceCannotAuthorizeProviderExecution,
                ),
            ),
            prerequisite(
                SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.CanonicalSkaldVectorProvenanceReviewed,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.NonAuthorizingEvidenceOnly,
                setOf(
                    SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.CanonicalHeaderHkdfHmacVectors,
                    SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.HeaderCommitmentAadContract,
                    SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.KeyExpansionCommitmentPolicy,
                ),
                setOf(
                    SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.PublicVectorDocumentationCannotAuthorizeProviderExecution,
                ),
            ),
            prerequisite(
                SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.AndroidRuntimeTestStrategyReviewed,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.DocumentationOnlyEvidenceExists,
                setOf(
                    SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.AndroidCompatibilityEntropyPolicy,
                    SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.RuntimeRandomnessProviderChecks,
                ),
            ),
            prerequisite(
                SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.LinuxJvmRuntimeTestStrategyReviewed,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.DocumentationOnlyEvidenceExists,
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.RuntimeRandomnessProviderChecks),
            ),
            prerequisite(
                SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.ProductionProviderAcceptanceReviewed,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.ExplicitlyModeledEvidenceExists,
                setOf(
                    SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.ProductionProviderAcceptanceContract,
                ),
                setOf(
                    SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.NoExecutableProviderImplementation,
                    SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.NoExecutableProviderKatExecutor,
                ),
            ),
            prerequisite(
                SkaldVaultV1ProviderExecutableKatPrerequisiteCategory.ReleaseMainnetReviewCompleted,
                SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.IntentionallyOutOfScope,
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource.ReleaseMainnetReviewEvidence),
                setOf(
                    SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.ReleaseReviewMissing,
                    SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker.MainnetDisabled,
                ),
                currentEvidenceExists = false,
            ),
        )

    fun source(
        source: SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource,
        classification: SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification,
        blockers: Set<SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker> = emptySet(),
        warnings: Set<SkaldVaultV1ProviderExecutableKatPrerequisiteWarning> =
            defaultWarningsForClassification(classification),
    ): SkaldVaultV1ProviderExecutableKatPrerequisiteSourceFinding =
        SkaldVaultV1ProviderExecutableKatPrerequisiteSourceFinding(
            source = source,
            classification = classification,
            blockers = blockers,
            warnings = warnings,
            authorizationStatus =
                SkaldVaultV1ProviderExecutableKatPrerequisiteAuthorizationStatus.Blocked,
            evidenceOnly = true,
            safeSourceId = "source-${source.name}",
            redactionClass = SkaldVaultV1ProviderExecutableKatPrerequisiteRedactionClass.EvidenceClassNamesOnly,
        )

    fun prerequisite(
        category: SkaldVaultV1ProviderExecutableKatPrerequisiteCategory,
        classification: SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification,
        sources: Set<SkaldVaultV1ProviderExecutableKatPrerequisiteAuditSource>,
        blockers: Set<SkaldVaultV1ProviderExecutableKatPrerequisiteBlocker> = emptySet(),
        warnings: Set<SkaldVaultV1ProviderExecutableKatPrerequisiteWarning> =
            defaultWarningsForClassification(classification),
        currentEvidenceExists: Boolean = true,
    ): SkaldVaultV1ProviderExecutableKatPrerequisiteFinding =
        SkaldVaultV1ProviderExecutableKatPrerequisiteFinding(
            category = category,
            classification = classification,
            sources = sources,
            blockers = blockers,
            warnings = warnings,
            authorizationStatus =
                SkaldVaultV1ProviderExecutableKatPrerequisiteAuthorizationStatus.Blocked,
            currentEvidenceExists = currentEvidenceExists,
            futureRequiredBeforeTestOnlyExecutableKat = true,
            safeFindingId = "prerequisite-${category.name}",
            redactionClass = SkaldVaultV1ProviderExecutableKatPrerequisiteRedactionClass.EvidenceClassNamesOnly,
        )

    fun defaultWarningsForClassification(
        classification: SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification,
    ): Set<SkaldVaultV1ProviderExecutableKatPrerequisiteWarning> =
        when (classification) {
            SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.ExplicitlyModeledEvidenceExists,
            SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.PartialModelOnlyEvidenceExists,
            -> setOf(
                SkaldVaultV1ProviderExecutableKatPrerequisiteWarning.ModeledEvidenceDoesNotAuthorizeExecution,
            )
            SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.DocumentationOnlyEvidenceExists ->
                setOf(
                    SkaldVaultV1ProviderExecutableKatPrerequisiteWarning.DocumentationDoesNotAuthorizeExecution,
                )
            SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.TestOnlyEvidenceExists ->
                setOf(SkaldVaultV1ProviderExecutableKatPrerequisiteWarning.TestEvidenceDoesNotAuthorizeProduction)
            SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.NonAuthorizingEvidenceOnly ->
                setOf(
                    SkaldVaultV1ProviderExecutableKatPrerequisiteWarning.ModeledEvidenceDoesNotAuthorizeExecution,
                )
            SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.BlockedByHardGate,
            SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.FutureRequiredEvidenceMissing,
            SkaldVaultV1ProviderExecutableKatPrerequisiteEvidenceClassification.IntentionallyOutOfScope,
            -> emptySet()
        }

    fun currentFutureRequiredWork(
        findings: List<SkaldVaultV1ProviderExecutableKatPrerequisiteFinding> =
            currentPrerequisiteFindings(),
    ): List<SkaldVaultV1ProviderExecutableKatPrerequisiteFutureWorkItem> =
        findings.map { finding ->
            SkaldVaultV1ProviderExecutableKatPrerequisiteFutureWorkItem(
                category = finding.category,
                safeWorkId = "future-work-${finding.category.name}",
                blockers = finding.blockers.ifEmpty { currentBlockers() },
                requiredBeforeTestOnlyExecutableKat = true,
                requiredBeforeProductionExecutableKat = true,
                authorizesCurrentExecution = false,
            )
        }
}
