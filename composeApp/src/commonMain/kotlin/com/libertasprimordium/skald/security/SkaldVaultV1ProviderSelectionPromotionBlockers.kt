package com.libertasprimordium.skald.security

interface SkaldVaultV1ProviderSelectionPromotionBlockerBoundary {
    fun evaluate(
        request: SkaldVaultV1ProviderSelectionPromotionRequest,
    ): SkaldVaultV1ProviderSelectionPromotionResult<SkaldVaultV1ProviderSelectionPromotionEvidence>
}

enum class SkaldVaultV1ProviderSelectionPromotionSource(val label: String) {
    CurrentTypedEvidence("current provider selection promotion evidence"),
    CandidateAudit("provider selection promotion candidate audit"),
    StageAudit("provider selection promotion stage audit"),
    PromotionGateAudit("provider selection promotion gate audit"),
}

enum class SkaldVaultV1ProviderSelectionPromotionStatus(val label: String) {
    BoundaryModeled("provider selection promotion blocker boundary modeled"),
    StillDisabled("still disabled"),
    EvidenceOnly("evidence only"),
    CandidateDescribedBuildEvidenceOnly("candidate described/build evidence only"),
    PromotionBlocked("promotion blocked"),
    ImplementationBlocked("implementation blocked"),
    FactoryBlocked("factory blocked"),
    RegistryBlocked("registry blocked"),
    ProviderSelectionBlocked("provider selection blocked"),
    ProductionSelectionBlocked("production provider selection blocked"),
    OperationAuthorizationBlocked("provider operation authorization blocked"),
    TestOnlyRejectedForProduction("test-only evidence rejected for production"),
    WarningOnlyCannotAuthorize("warning-only evidence cannot authorize"),
    UserConsentCannotOverride("user consent cannot override"),
    UnsupportedFailClosed("unsupported fail closed"),
}

enum class SkaldVaultV1ProviderSelectionPromotionStage(val label: String) {
    CandidateDescribed("candidate described"),
    DependencyAbsent("dependency absent"),
    DependencyDeclaredBuildOnly("dependency declared build-only"),
    DependencyReviewed("dependency reviewed"),
    SourceSetPlacementReviewed("source-set placement reviewed"),
    ImplementationAbsent("implementation absent"),
    ImplementationPresentButDisabled("implementation present but disabled"),
    FactoryAbsent("factory absent"),
    FactoryPresentButDisabled("factory present but disabled"),
    RegistryAbsent("registry absent"),
    RegistryPresentButDisabled("registry present but disabled"),
    TestScopeSelectable("test-scope selectable"),
    ProductionSelectable("production selectable"),
    ProviderOperationAuthorizationRequested("provider operation authorization requested"),
    ProviderOperationAuthorizationApproved("provider operation authorization approved"),
    KatExecutionRequested("KAT execution requested"),
    KatApproved("KAT approved"),
    RuntimeRandomnessApproved("runtime randomness approved"),
    KdfProviderSupportApproved("KDF provider support approved"),
    AeadProviderSupportApproved("AEAD provider support approved"),
    KeyWrappingSupportApproved("key wrapping support approved"),
    CreationUnlockIntegrationApproved("creation/unlock integration approved"),
    PersistenceIntegrationApproved("persistence integration approved"),
    ReleaseValidationApproved("release validation approved"),
    MainnetApproved("mainnet approved"),
}

enum class SkaldVaultV1ProviderSelectionPromotionCandidateFamily(val label: String) {
    DisabledProvider("disabled provider"),
    TinkJvmCandidate("Tink JVM candidate"),
    BouncyCastleJvmCandidate("Bouncy Castle JVM candidate"),
    AndroidKeystoreWrapperCandidate("Android Keystore wrapper candidate"),
    PlatformOsCsprngCandidate("platform OS CSPRNG candidate"),
    TestOnlyDeterministicCandidate("test-only deterministic candidate"),
    UnknownCandidate("unknown candidate"),
    UnsupportedCandidate("unsupported candidate"),
}

enum class SkaldVaultV1ProviderSelectionPromotionBlocker(val label: String) {
    CandidateEvidenceAbsent("candidate evidence absent"),
    DependencyAbsent("dependency absent"),
    DependencyBuildOnly("dependency build-only"),
    DependencyNotReviewed("dependency not reviewed"),
    SourceSetPlacementNotReviewed("source-set placement not reviewed"),
    ImplementationAbsent("implementation absent"),
    ImplementationDisabled("implementation disabled"),
    FactoryAbsent("factory absent"),
    FactoryDisabled("factory disabled"),
    RegistryDisabled("registry disabled"),
    ProviderSelectionLockedToDisabledProvider("provider selection locked to disabled provider"),
    ProductionProviderSelectableFalse("productionProviderSelectable is false"),
    ProviderOperationAuthorizationBlocked("provider operation authorization blocked"),
    ProviderCandidatePackagingBlocked("provider candidate packaging blocked"),
    DependencyBuildBoundaryStillDisabled("dependency build boundary still disabled"),
    AuthorizationReadinessMatrixBlocked("authorization/readiness matrix blocked"),
    ProviderAcceptanceMissing("provider acceptance missing"),
    DependencyProbeInsufficient("dependency probe insufficient"),
    KatContractNotApproved("KAT contract not approved"),
    KatExecutionUnavailable("KAT execution unavailable"),
    RuntimeRandomnessBlocked("runtime randomness blocked"),
    KdfCalibrationBlocked("KDF calibration blocked"),
    SecureStorageAuthorizationBlocked("secure-storage authorization blocked"),
    CreationAuthorizationBlocked("creation authorization blocked"),
    UnlockAuthorizationBlocked("unlock authorization blocked"),
    RedactionLeakageReviewMissing("redaction/leakage review missing"),
    ClearWipeReviewMissing("clear/wipe review missing"),
    MigrationCorruptionReviewMissing("migration/corruption review missing"),
    ProductionReleaseReviewMissing("production release review missing"),
    MainnetDisabled("mainnet disabled"),
    WarningOnlyEvidenceCannotAuthorize("warning-only evidence cannot authorize"),
    UserConsentCannotOverride("user consent cannot override"),
    TestOnlyEvidenceCannotAuthorizeProduction("test-only evidence cannot authorize production"),
}

enum class SkaldVaultV1ProviderSelectionPromotionRequiredGate(val label: String) {
    CandidateEvidenceReviewed("candidate evidence reviewed"),
    DependencyReviewApproved("dependency review approved"),
    SourceSetPlacementReviewApproved("source-set placement review approved"),
    ProviderImplementationAddedInLaterBranch("provider implementation added in a later branch"),
    ProviderFactoryAddedInLaterBranch("provider factory added in a later branch"),
    ProviderRegistryReviewedAndEnabledInLaterBranch("provider registry reviewed and enabled later"),
    NonProductionTestSelectabilityReviewed("non-production test selectability reviewed"),
    ProductionProviderSelectableExplicitLaterChange("explicit later productionProviderSelectable change"),
    ProviderOperationAuthorizationApproved("provider operation authorization approved"),
    ProviderKatContractApproved("provider KAT contract approved"),
    ProviderKatExecutionApproved("provider KAT execution approved"),
    RuntimeRandomnessAuthorizationApproved("runtime randomness authorization approved"),
    KdfCalibrationAuthorizationApproved("KDF calibration authorization approved"),
    AeadSupportApproved("AEAD support approved"),
    KeyWrappingSupportApproved("key wrapping support approved"),
    SecureStorageAuthorizationApproved("secure-storage authorization approved"),
    CreationAuthorizationApproved("creation authorization approved"),
    UnlockAuthorizationApproved("unlock authorization approved"),
    PersistenceReadinessApproved("persistence readiness approved"),
    AuthorizationReadinessMatrixAllowsPromotion("authorization/readiness matrix allows promotion"),
    ProductionProviderAcceptanceApproved("production provider acceptance approved"),
    DependencyProbeSufficient("dependency probe sufficient"),
    RedactionLeakageReviewApproved("redaction/leakage review approved"),
    ClearWipeReviewApproved("clear/wipe review approved"),
    MigrationCorruptionReviewApproved("migration/corruption review approved"),
    ReleaseValidationApproved("release validation approved"),
    MainnetReleaseApproved("mainnet release approved"),
}

enum class SkaldVaultV1ProviderSelectionPromotionRedactionClass(val label: String) {
    PolicyIdsOnly("policy ids only"),
    CandidateFamilyOnly("candidate family only"),
    PromotionStageOnly("promotion stage only"),
    BoundaryAndBlockerClassesOnly("boundary and blocker classes only"),
    NoDiagnosticPayload("no diagnostic payload"),
}

data class SkaldVaultV1ProviderSelectionPromotionCapability(
    val candidateDescribed: Boolean,
    val dependencyBuildEvidencePresent: Boolean,
    val dependencyReviewed: Boolean,
    val sourceSetPlacementReviewed: Boolean,
    val implementationPresent: Boolean,
    val implementationEnabled: Boolean,
    val factoryPresent: Boolean,
    val factoryEnabled: Boolean,
    val registryEnabled: Boolean,
    val testScopeSelectable: Boolean,
    val productionSelectable: Boolean,
    val productionProviderSelectable: Boolean,
    val providerSelectable: Boolean,
    val providerOperationAuthorized: Boolean,
    val providerKatApproved: Boolean,
    val providerKatExecutionAvailable: Boolean,
    val runtimeRandomnessAuthorized: Boolean,
    val kdfProviderSupportApproved: Boolean,
    val aeadProviderSupportApproved: Boolean,
    val keyWrappingSupportApproved: Boolean,
    val creationIntegrationApproved: Boolean,
    val unlockIntegrationApproved: Boolean,
    val persistenceIntegrationApproved: Boolean,
    val releaseValidationApproved: Boolean,
    val mainnetApproved: Boolean,
) {
    companion object {
        val CurrentFailClosed = SkaldVaultV1ProviderSelectionPromotionCapability(
            candidateDescribed = true,
            dependencyBuildEvidencePresent = true,
            dependencyReviewed = false,
            sourceSetPlacementReviewed = false,
            implementationPresent = false,
            implementationEnabled = false,
            factoryPresent = false,
            factoryEnabled = false,
            registryEnabled = false,
            testScopeSelectable = false,
            productionSelectable = false,
            productionProviderSelectable = false,
            providerSelectable = false,
            providerOperationAuthorized = false,
            providerKatApproved = false,
            providerKatExecutionAvailable = false,
            runtimeRandomnessAuthorized = false,
            kdfProviderSupportApproved = false,
            aeadProviderSupportApproved = false,
            keyWrappingSupportApproved = false,
            creationIntegrationApproved = false,
            unlockIntegrationApproved = false,
            persistenceIntegrationApproved = false,
            releaseValidationApproved = false,
            mainnetApproved = false,
        )
    }
}

class SkaldVaultV1ProviderSelectionPromotionPolicyToken private constructor(
    val policyId: String,
    val candidateFamily: SkaldVaultV1ProviderSelectionPromotionCandidateFamily?,
    val stage: SkaldVaultV1ProviderSelectionPromotionStage?,
) {
    val containsProviderHandle: Boolean = false
    val containsCryptoObject: Boolean = false
    val containsByteMaterial: Boolean = false
    val containsPathOrRootText: Boolean = false
    val containsStorageIdentifier: Boolean = false
    val containsSecretMaterial: Boolean = false

    override fun toString(): String =
        "SkaldVaultV1ProviderSelectionPromotionPolicyToken(" +
            "policyId=$policyId, " +
            "candidateFamily=$candidateFamily, " +
            "stage=$stage, " +
            "providerHandle=<redacted>, " +
            "cryptoObject=<redacted>, " +
            "byteMaterial=<redacted>, " +
            "pathOrRoot=<redacted>, " +
            "storageIdentifier=<redacted>, " +
            "secretMaterial=<redacted>" +
            ")"

    companion object {
        fun redacted(
            policyId: String,
            candidateFamily: SkaldVaultV1ProviderSelectionPromotionCandidateFamily?,
            stage: SkaldVaultV1ProviderSelectionPromotionStage?,
        ): SkaldVaultV1ProviderSelectionPromotionPolicyToken =
            SkaldVaultV1ProviderSelectionPromotionPolicyToken(policyId, candidateFamily, stage)
    }
}

class SkaldVaultV1ProviderSelectionPromotionRequest private constructor(
    val source: SkaldVaultV1ProviderSelectionPromotionSource,
    val candidateFamily: SkaldVaultV1ProviderSelectionPromotionCandidateFamily?,
    val stage: SkaldVaultV1ProviderSelectionPromotionStage?,
    private val dependencyBuildEvidence: SkaldVaultV1ProviderDependencyBuildEvidence?,
    private val providerCandidatePackagingEvidence: SkaldVaultV1ProviderCandidatePackagingEvidence?,
    private val authorizationReadinessMatrixEvidence: SkaldVaultV1AuthorizationReadinessMatrixEvidence?,
    private val providerSelectionResult: VaultCryptoProviderSelectionResult?,
    private val providerAcceptanceAssessment: ProductionProviderAcceptanceAssessment?,
    private val dependencyProbeResult: VaultCryptoDependencyProbeResult?,
    private val providerOperationEvidence: SkaldVaultV1VaultProviderOperationAuthorizationEvidence?,
    private val runtimeRandomnessEvidence: SkaldVaultV1VaultRuntimeRandomnessAuthorizationEvidence?,
    private val kdfCalibrationEvidence: SkaldVaultV1VaultKdfCalibrationAuthorizationEvidence?,
    private val secureStorageEvidence: SkaldVaultV1VaultSecureStorageAuthorizationEvidence?,
    private val creationAuthorizationEvidence: SkaldVaultV1VaultCreationAuthorizationEvidence?,
    private val unlockAuthorizationEvidence: SkaldVaultV1VaultUnlockAuthorizationEvidence?,
) {
    val dependencyBuildEvidenceSupplied: Boolean
        get() = dependencyBuildEvidence != null

    val providerCandidatePackagingEvidenceSupplied: Boolean
        get() = providerCandidatePackagingEvidence != null

    val authorizationReadinessMatrixEvidenceSupplied: Boolean
        get() = authorizationReadinessMatrixEvidence != null

    val providerSelectionEvidenceSupplied: Boolean
        get() = providerSelectionResult != null

    val providerAcceptanceEvidenceSupplied: Boolean
        get() = providerAcceptanceAssessment != null

    val dependencyProbeEvidenceSupplied: Boolean
        get() = dependencyProbeResult != null

    val providerOperationEvidenceSupplied: Boolean
        get() = providerOperationEvidence != null

    val runtimeRandomnessEvidenceSupplied: Boolean
        get() = runtimeRandomnessEvidence != null

    val kdfCalibrationEvidenceSupplied: Boolean
        get() = kdfCalibrationEvidence != null

    val secureStorageEvidenceSupplied: Boolean
        get() = secureStorageEvidence != null

    val creationAuthorizationEvidenceSupplied: Boolean
        get() = creationAuthorizationEvidence != null

    val unlockAuthorizationEvidenceSupplied: Boolean
        get() = unlockAuthorizationEvidence != null

    override fun toString(): String =
        "SkaldVaultV1ProviderSelectionPromotionRequest(" +
            "source=$source, " +
            "candidateFamily=$candidateFamily, " +
            "stage=$stage, " +
            "dependencyBuild=<redacted>, " +
            "providerCandidatePackaging=<redacted>, " +
            "authorizationReadinessMatrix=<redacted>, " +
            "providerSelection=<redacted>, " +
            "providerAcceptance=<redacted>, " +
            "dependencyProbe=<redacted>, " +
            "providerOperation=<redacted>, " +
            "runtimeRandomness=<redacted>, " +
            "kdfCalibration=<redacted>, " +
            "secureStorage=<redacted>, " +
            "creationAuthorization=<redacted>, " +
            "unlockAuthorization=<redacted>" +
            ")"

    companion object {
        fun currentEvidence(
            candidateFamily: SkaldVaultV1ProviderSelectionPromotionCandidateFamily? = null,
            stage: SkaldVaultV1ProviderSelectionPromotionStage? = null,
            dependencyBuildEvidence: SkaldVaultV1ProviderDependencyBuildEvidence? = null,
            providerCandidatePackagingEvidence: SkaldVaultV1ProviderCandidatePackagingEvidence? = null,
            authorizationReadinessMatrixEvidence: SkaldVaultV1AuthorizationReadinessMatrixEvidence? = null,
            providerSelectionResult: VaultCryptoProviderSelectionResult? = null,
            providerAcceptanceAssessment: ProductionProviderAcceptanceAssessment? = null,
            dependencyProbeResult: VaultCryptoDependencyProbeResult? = null,
            providerOperationEvidence: SkaldVaultV1VaultProviderOperationAuthorizationEvidence? = null,
            runtimeRandomnessEvidence: SkaldVaultV1VaultRuntimeRandomnessAuthorizationEvidence? = null,
            kdfCalibrationEvidence: SkaldVaultV1VaultKdfCalibrationAuthorizationEvidence? = null,
            secureStorageEvidence: SkaldVaultV1VaultSecureStorageAuthorizationEvidence? = null,
            creationAuthorizationEvidence: SkaldVaultV1VaultCreationAuthorizationEvidence? = null,
            unlockAuthorizationEvidence: SkaldVaultV1VaultUnlockAuthorizationEvidence? = null,
        ): SkaldVaultV1ProviderSelectionPromotionRequest =
            SkaldVaultV1ProviderSelectionPromotionRequest(
                source = SkaldVaultV1ProviderSelectionPromotionSource.CurrentTypedEvidence,
                candidateFamily = candidateFamily,
                stage = stage,
                dependencyBuildEvidence = dependencyBuildEvidence,
                providerCandidatePackagingEvidence = providerCandidatePackagingEvidence,
                authorizationReadinessMatrixEvidence = authorizationReadinessMatrixEvidence,
                providerSelectionResult = providerSelectionResult,
                providerAcceptanceAssessment = providerAcceptanceAssessment,
                dependencyProbeResult = dependencyProbeResult,
                providerOperationEvidence = providerOperationEvidence,
                runtimeRandomnessEvidence = runtimeRandomnessEvidence,
                kdfCalibrationEvidence = kdfCalibrationEvidence,
                secureStorageEvidence = secureStorageEvidence,
                creationAuthorizationEvidence = creationAuthorizationEvidence,
                unlockAuthorizationEvidence = unlockAuthorizationEvidence,
            )

        fun forCandidate(
            candidateFamily: SkaldVaultV1ProviderSelectionPromotionCandidateFamily,
        ): SkaldVaultV1ProviderSelectionPromotionRequest =
            SkaldVaultV1ProviderSelectionPromotionRequest(
                source = SkaldVaultV1ProviderSelectionPromotionSource.CandidateAudit,
                candidateFamily = candidateFamily,
                stage = null,
                dependencyBuildEvidence = null,
                providerCandidatePackagingEvidence = null,
                authorizationReadinessMatrixEvidence = null,
                providerSelectionResult = null,
                providerAcceptanceAssessment = null,
                dependencyProbeResult = null,
                providerOperationEvidence = null,
                runtimeRandomnessEvidence = null,
                kdfCalibrationEvidence = null,
                secureStorageEvidence = null,
                creationAuthorizationEvidence = null,
                unlockAuthorizationEvidence = null,
            )

        fun forStage(
            stage: SkaldVaultV1ProviderSelectionPromotionStage,
        ): SkaldVaultV1ProviderSelectionPromotionRequest =
            SkaldVaultV1ProviderSelectionPromotionRequest(
                source = SkaldVaultV1ProviderSelectionPromotionSource.StageAudit,
                candidateFamily = null,
                stage = stage,
                dependencyBuildEvidence = null,
                providerCandidatePackagingEvidence = null,
                authorizationReadinessMatrixEvidence = null,
                providerSelectionResult = null,
                providerAcceptanceAssessment = null,
                dependencyProbeResult = null,
                providerOperationEvidence = null,
                runtimeRandomnessEvidence = null,
                kdfCalibrationEvidence = null,
                secureStorageEvidence = null,
                creationAuthorizationEvidence = null,
                unlockAuthorizationEvidence = null,
            )

        fun promotionGateAudit(): SkaldVaultV1ProviderSelectionPromotionRequest =
            SkaldVaultV1ProviderSelectionPromotionRequest(
                source = SkaldVaultV1ProviderSelectionPromotionSource.PromotionGateAudit,
                candidateFamily = null,
                stage = null,
                dependencyBuildEvidence = null,
                providerCandidatePackagingEvidence = null,
                authorizationReadinessMatrixEvidence = null,
                providerSelectionResult = null,
                providerAcceptanceAssessment = null,
                dependencyProbeResult = null,
                providerOperationEvidence = null,
                runtimeRandomnessEvidence = null,
                kdfCalibrationEvidence = null,
                secureStorageEvidence = null,
                creationAuthorizationEvidence = null,
                unlockAuthorizationEvidence = null,
            )
    }
}

data class SkaldVaultV1ProviderSelectionPromotionStageRow(
    val stage: SkaldVaultV1ProviderSelectionPromotionStage,
    val statuses: Set<SkaldVaultV1ProviderSelectionPromotionStatus>,
    val blockers: Set<SkaldVaultV1ProviderSelectionPromotionBlocker>,
    val requiredGates: Set<SkaldVaultV1ProviderSelectionPromotionRequiredGate>,
    val evidenceOnly: Boolean,
    val buildEvidenceOnly: Boolean,
    val promotionAllowed: Boolean,
    val providerRuntimeAvailable: Boolean,
    val productionSelectable: Boolean,
    val operationAuthorized: Boolean,
    val redactionClass: SkaldVaultV1ProviderSelectionPromotionRedactionClass,
)

data class SkaldVaultV1ProviderSelectionPromotionCandidateRow(
    val family: SkaldVaultV1ProviderSelectionPromotionCandidateFamily,
    val statuses: Set<SkaldVaultV1ProviderSelectionPromotionStatus>,
    val blockers: Set<SkaldVaultV1ProviderSelectionPromotionBlocker>,
    val requiredGates: Set<SkaldVaultV1ProviderSelectionPromotionRequiredGate>,
    val candidateDescribed: Boolean,
    val dependencyBuildEvidencePresent: Boolean,
    val selectedByCurrentRegistry: Boolean,
    val disabledBoundaryOnly: Boolean,
    val providerCandidateOnly: Boolean,
    val wrapperOnly: Boolean,
    val randomnessSourceOnly: Boolean,
    val testOnly: Boolean,
    val implementationPresent: Boolean,
    val implementationEnabled: Boolean,
    val factoryPresent: Boolean,
    val factoryEnabled: Boolean,
    val registryEnabled: Boolean,
    val testScopeSelectable: Boolean,
    val productionSelectable: Boolean,
    val providerSelectable: Boolean,
    val productionProviderSelectable: Boolean,
    val operationAuthorized: Boolean,
    val redactionClass: SkaldVaultV1ProviderSelectionPromotionRedactionClass,
)

data class SkaldVaultV1ProviderSelectionPromotionSummary(
    val policyId: String,
    val policyVersion: Int,
    val statuses: Set<SkaldVaultV1ProviderSelectionPromotionStatus>,
    val stages: Set<SkaldVaultV1ProviderSelectionPromotionStage>,
    val candidateFamilies: Set<SkaldVaultV1ProviderSelectionPromotionCandidateFamily>,
    val blockers: Set<SkaldVaultV1ProviderSelectionPromotionBlocker>,
    val requiredGates: Set<SkaldVaultV1ProviderSelectionPromotionRequiredGate>,
    val redactionClasses: Set<SkaldVaultV1ProviderSelectionPromotionRedactionClass>,
    val capability: SkaldVaultV1ProviderSelectionPromotionCapability,
    val stillDisabled: Boolean,
    val evidenceOnly: Boolean,
    val blocksImplementation: Boolean,
    val blocksFactory: Boolean,
    val blocksRegistry: Boolean,
    val blocksProviderSelection: Boolean,
    val blocksProductionProviderSelectable: Boolean,
)

data class SkaldVaultV1ProviderSelectionPromotionEvidence(
    val policyId: String,
    val policyVersion: Int,
    val source: SkaldVaultV1ProviderSelectionPromotionSource,
    val status: SkaldVaultV1ProviderSelectionPromotionStatus,
    val stageRows: List<SkaldVaultV1ProviderSelectionPromotionStageRow>,
    val candidateRows: List<SkaldVaultV1ProviderSelectionPromotionCandidateRow>,
    val blockers: Set<SkaldVaultV1ProviderSelectionPromotionBlocker>,
    val requiredGates: Set<SkaldVaultV1ProviderSelectionPromotionRequiredGate>,
    val capability: SkaldVaultV1ProviderSelectionPromotionCapability,
    val policyTokenEvidence: SkaldVaultV1ProviderSelectionPromotionPolicyToken,
    val dependencyBuildEvidenceConsumed: Boolean,
    val providerCandidatePackagingEvidenceConsumed: Boolean,
    val authorizationReadinessMatrixEvidenceConsumed: Boolean,
    val providerSelectionEvidenceConsumed: Boolean,
    val providerAcceptanceEvidenceConsumed: Boolean,
    val dependencyProbeEvidenceConsumed: Boolean,
    val providerOperationEvidenceConsumed: Boolean,
    val runtimeRandomnessEvidenceConsumed: Boolean,
    val kdfCalibrationEvidenceConsumed: Boolean,
    val secureStorageEvidenceConsumed: Boolean,
    val creationAuthorizationEvidenceConsumed: Boolean,
    val unlockAuthorizationEvidenceConsumed: Boolean,
    val providerSelectionPromotionBlockersModeled: Boolean = true,
    val providerSelectionPromotionBlockersStillDisabled: Boolean = true,
    val providerSelectionPromotionBlocksImplementation: Boolean = true,
    val providerSelectionPromotionBlocksFactory: Boolean = true,
    val providerSelectionPromotionBlocksRegistry: Boolean = true,
    val providerSelectionPromotionBlocksProviderSelection: Boolean = true,
    val providerSelectionPromotionBlocksProductionProviderSelectable: Boolean = true,
    val providerSelectionPromotionDoesNotRunCrypto: Boolean = true,
    val providerSelectionPromotionDoesNotEnableCreation: Boolean = true,
    val providerSelectionPromotionDoesNotEnableUnlock: Boolean = true,
    val providerSelectionPromotionDoesNotEnablePersistence: Boolean = true,
    val candidateDescribed: Boolean = true,
    val dependencyBuildEvidencePresent: Boolean = true,
    val dependencyReviewed: Boolean = false,
    val sourceSetPlacementReviewed: Boolean = false,
    val providerImplementationAdded: Boolean = false,
    val implementationPresent: Boolean = false,
    val implementationEnabled: Boolean = false,
    val providerFactoryAdded: Boolean = false,
    val factoryPresent: Boolean = false,
    val factoryEnabled: Boolean = false,
    val providerRegistryEnabled: Boolean = false,
    val registryEnabled: Boolean = false,
    val providerRuntimeInstantiable: Boolean = false,
    val testScopeSelectable: Boolean = false,
    val productionSelectable: Boolean = false,
    val providerSelectable: Boolean = false,
    val productionProviderSelectable: Boolean = false,
    val providerOperationAuthorized: Boolean = false,
    val providerKatApproved: Boolean = false,
    val providerKatExecutionAvailable: Boolean = false,
    val runtimeRandomnessAvailable: Boolean = false,
    val runtimeRandomnessAuthorized: Boolean = false,
    val kdfExecutionAvailable: Boolean = false,
    val kdfProviderSupportApproved: Boolean = false,
    val aeadExecutionAvailable: Boolean = false,
    val aeadProviderSupportApproved: Boolean = false,
    val hkdfHmacExecutionAvailable: Boolean = false,
    val keyWrappingAvailable: Boolean = false,
    val keyWrappingSupportApproved: Boolean = false,
    val vaultCreationAvailable: Boolean = false,
    val creationIntegrationApproved: Boolean = false,
    val vaultUnlockAvailable: Boolean = false,
    val unlockIntegrationApproved: Boolean = false,
    val vaultPersistenceAvailable: Boolean = false,
    val persistenceIntegrationApproved: Boolean = false,
    val releaseValidationApproved: Boolean = false,
    val mainnetAvailable: Boolean = false,
    val mainnetApproved: Boolean = false,
) {
    override fun toString(): String =
        "SkaldVaultV1ProviderSelectionPromotionEvidence(" +
            "policyId=$policyId, " +
            "policyVersion=$policyVersion, " +
            "source=$source, " +
            "status=$status, " +
            "stageRows=${stageRows.size}, " +
            "candidateRows=${candidateRows.size}, " +
            "blockers=${blockers.size}, " +
            "requiredGates=${requiredGates.size}, " +
            "policyToken=<redacted>" +
            ")"
}

sealed class SkaldVaultV1ProviderSelectionPromotionResult<out T> {
    data class Blocked<out T>(
        val value: T,
    ) : SkaldVaultV1ProviderSelectionPromotionResult<T>() {
        override fun toString(): String =
            "SkaldVaultV1ProviderSelectionPromotionResult.Blocked(value=<redacted-provider-selection-promotion-evidence>)"
    }
}

object SkaldVaultV1ProviderSelectionPromotionBlockersPolicy :
    SkaldVaultV1ProviderSelectionPromotionBlockerBoundary {
    const val POLICY_ID = "skald-vault-v1-provider-selection-promotion-blockers-v1"
    const val POLICY_VERSION = 1

    override fun evaluate(
        request: SkaldVaultV1ProviderSelectionPromotionRequest,
    ): SkaldVaultV1ProviderSelectionPromotionResult<SkaldVaultV1ProviderSelectionPromotionEvidence> {
        val stageRows = stageRowsFor(request.stage)
        val candidateRows = candidateRowsFor(request.candidateFamily)
        return SkaldVaultV1ProviderSelectionPromotionResult.Blocked(
            SkaldVaultV1ProviderSelectionPromotionEvidence(
                policyId = POLICY_ID,
                policyVersion = POLICY_VERSION,
                source = request.source,
                status = SkaldVaultV1ProviderSelectionPromotionStatus.StillDisabled,
                stageRows = stageRows,
                candidateRows = candidateRows,
                blockers = stageRows.flatMap { it.blockers }.toSet() +
                    candidateRows.flatMap { it.blockers }.toSet() +
                    baseOverrideBlockers,
                requiredGates = SkaldVaultV1ProviderSelectionPromotionRequiredGate.entries.toSet(),
                capability = SkaldVaultV1ProviderSelectionPromotionCapability.CurrentFailClosed,
                policyTokenEvidence = SkaldVaultV1ProviderSelectionPromotionPolicyToken.redacted(
                    policyId = POLICY_ID,
                    candidateFamily = request.candidateFamily,
                    stage = request.stage,
                ),
                dependencyBuildEvidenceConsumed = request.dependencyBuildEvidenceSupplied,
                providerCandidatePackagingEvidenceConsumed =
                    request.providerCandidatePackagingEvidenceSupplied,
                authorizationReadinessMatrixEvidenceConsumed =
                    request.authorizationReadinessMatrixEvidenceSupplied,
                providerSelectionEvidenceConsumed = request.providerSelectionEvidenceSupplied,
                providerAcceptanceEvidenceConsumed = request.providerAcceptanceEvidenceSupplied,
                dependencyProbeEvidenceConsumed = request.dependencyProbeEvidenceSupplied,
                providerOperationEvidenceConsumed = request.providerOperationEvidenceSupplied,
                runtimeRandomnessEvidenceConsumed = request.runtimeRandomnessEvidenceSupplied,
                kdfCalibrationEvidenceConsumed = request.kdfCalibrationEvidenceSupplied,
                secureStorageEvidenceConsumed = request.secureStorageEvidenceSupplied,
                creationAuthorizationEvidenceConsumed = request.creationAuthorizationEvidenceSupplied,
                unlockAuthorizationEvidenceConsumed = request.unlockAuthorizationEvidenceSupplied,
            ),
        )
    }

    fun currentPolicySummary(): SkaldVaultV1ProviderSelectionPromotionSummary =
        SkaldVaultV1ProviderSelectionPromotionSummary(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            statuses = SkaldVaultV1ProviderSelectionPromotionStatus.entries.toSet(),
            stages = SkaldVaultV1ProviderSelectionPromotionStage.entries.toSet(),
            candidateFamilies = SkaldVaultV1ProviderSelectionPromotionCandidateFamily.entries.toSet(),
            blockers = SkaldVaultV1ProviderSelectionPromotionBlocker.entries.toSet(),
            requiredGates = SkaldVaultV1ProviderSelectionPromotionRequiredGate.entries.toSet(),
            redactionClasses = SkaldVaultV1ProviderSelectionPromotionRedactionClass.entries.toSet(),
            capability = SkaldVaultV1ProviderSelectionPromotionCapability.CurrentFailClosed,
            stillDisabled = true,
            evidenceOnly = true,
            blocksImplementation = true,
            blocksFactory = true,
            blocksRegistry = true,
            blocksProviderSelection = true,
            blocksProductionProviderSelectable = true,
        )

    private fun stageRowsFor(
        stage: SkaldVaultV1ProviderSelectionPromotionStage?,
    ): List<SkaldVaultV1ProviderSelectionPromotionStageRow> {
        val rows = currentStageRows()
        return stage?.let { requested -> rows.filter { it.stage == requested } } ?: rows
    }

    private fun candidateRowsFor(
        candidateFamily: SkaldVaultV1ProviderSelectionPromotionCandidateFamily?,
    ): List<SkaldVaultV1ProviderSelectionPromotionCandidateRow> {
        val rows = currentCandidateRows()
        return candidateFamily?.let { requested -> rows.filter { it.family == requested } } ?: rows
    }

    private fun currentStageRows(): List<SkaldVaultV1ProviderSelectionPromotionStageRow> =
        SkaldVaultV1ProviderSelectionPromotionStage.entries.map { stage ->
            stageRow(
                stage = stage,
                blockers = stageBlockers(stage),
                buildEvidenceOnly = stage == SkaldVaultV1ProviderSelectionPromotionStage.CandidateDescribed ||
                    stage == SkaldVaultV1ProviderSelectionPromotionStage.DependencyDeclaredBuildOnly,
            )
        }

    private fun currentCandidateRows(): List<SkaldVaultV1ProviderSelectionPromotionCandidateRow> =
        listOf(
            candidateRow(
                family = SkaldVaultV1ProviderSelectionPromotionCandidateFamily.DisabledProvider,
                selectedByCurrentRegistry = true,
                disabledBoundaryOnly = true,
                blockers = commonCandidateBlockers +
                    SkaldVaultV1ProviderSelectionPromotionBlocker.ImplementationDisabled +
                    SkaldVaultV1ProviderSelectionPromotionBlocker.FactoryDisabled,
            ),
            candidateRow(
                family = SkaldVaultV1ProviderSelectionPromotionCandidateFamily.TinkJvmCandidate,
                dependencyBuildEvidencePresent = true,
                providerCandidateOnly = true,
                blockers = commonCandidateBlockers +
                    SkaldVaultV1ProviderSelectionPromotionBlocker.DependencyBuildOnly,
            ),
            candidateRow(
                family = SkaldVaultV1ProviderSelectionPromotionCandidateFamily.BouncyCastleJvmCandidate,
                dependencyBuildEvidencePresent = true,
                providerCandidateOnly = true,
                blockers = commonCandidateBlockers +
                    SkaldVaultV1ProviderSelectionPromotionBlocker.DependencyBuildOnly,
            ),
            candidateRow(
                family = SkaldVaultV1ProviderSelectionPromotionCandidateFamily.AndroidKeystoreWrapperCandidate,
                wrapperOnly = true,
                blockers = commonCandidateBlockers +
                    SkaldVaultV1ProviderSelectionPromotionBlocker.DependencyAbsent +
                    SkaldVaultV1ProviderSelectionPromotionBlocker.SecureStorageAuthorizationBlocked,
            ),
            candidateRow(
                family = SkaldVaultV1ProviderSelectionPromotionCandidateFamily.PlatformOsCsprngCandidate,
                randomnessSourceOnly = true,
                blockers = commonCandidateBlockers +
                    SkaldVaultV1ProviderSelectionPromotionBlocker.DependencyAbsent +
                    SkaldVaultV1ProviderSelectionPromotionBlocker.RuntimeRandomnessBlocked,
            ),
            candidateRow(
                family = SkaldVaultV1ProviderSelectionPromotionCandidateFamily.TestOnlyDeterministicCandidate,
                testOnly = true,
                blockers = commonCandidateBlockers +
                    SkaldVaultV1ProviderSelectionPromotionBlocker.TestOnlyEvidenceCannotAuthorizeProduction,
            ),
            candidateRow(
                family = SkaldVaultV1ProviderSelectionPromotionCandidateFamily.UnknownCandidate,
                blockers = commonCandidateBlockers +
                    SkaldVaultV1ProviderSelectionPromotionBlocker.CandidateEvidenceAbsent,
                redactionClass = SkaldVaultV1ProviderSelectionPromotionRedactionClass.NoDiagnosticPayload,
            ),
            candidateRow(
                family = SkaldVaultV1ProviderSelectionPromotionCandidateFamily.UnsupportedCandidate,
                blockers = commonCandidateBlockers +
                    SkaldVaultV1ProviderSelectionPromotionBlocker.CandidateEvidenceAbsent,
                redactionClass = SkaldVaultV1ProviderSelectionPromotionRedactionClass.NoDiagnosticPayload,
            ),
        )

    private fun stageRow(
        stage: SkaldVaultV1ProviderSelectionPromotionStage,
        blockers: Set<SkaldVaultV1ProviderSelectionPromotionBlocker>,
        buildEvidenceOnly: Boolean,
    ): SkaldVaultV1ProviderSelectionPromotionStageRow =
        SkaldVaultV1ProviderSelectionPromotionStageRow(
            stage = stage,
            statuses = baseStatuses + stageStatus(stage),
            blockers = blockers + baseOverrideBlockers,
            requiredGates = SkaldVaultV1ProviderSelectionPromotionRequiredGate.entries.toSet(),
            evidenceOnly = true,
            buildEvidenceOnly = buildEvidenceOnly,
            promotionAllowed = false,
            providerRuntimeAvailable = false,
            productionSelectable = false,
            operationAuthorized = false,
            redactionClass = SkaldVaultV1ProviderSelectionPromotionRedactionClass.PromotionStageOnly,
        )

    private fun candidateRow(
        family: SkaldVaultV1ProviderSelectionPromotionCandidateFamily,
        dependencyBuildEvidencePresent: Boolean = false,
        selectedByCurrentRegistry: Boolean = false,
        disabledBoundaryOnly: Boolean = false,
        providerCandidateOnly: Boolean = false,
        wrapperOnly: Boolean = false,
        randomnessSourceOnly: Boolean = false,
        testOnly: Boolean = false,
        blockers: Set<SkaldVaultV1ProviderSelectionPromotionBlocker>,
        redactionClass: SkaldVaultV1ProviderSelectionPromotionRedactionClass =
            SkaldVaultV1ProviderSelectionPromotionRedactionClass.BoundaryAndBlockerClassesOnly,
    ): SkaldVaultV1ProviderSelectionPromotionCandidateRow =
        SkaldVaultV1ProviderSelectionPromotionCandidateRow(
            family = family,
            statuses = baseStatuses + SkaldVaultV1ProviderSelectionPromotionStatus.CandidateDescribedBuildEvidenceOnly,
            blockers = blockers + baseOverrideBlockers,
            requiredGates = SkaldVaultV1ProviderSelectionPromotionRequiredGate.entries.toSet(),
            candidateDescribed = true,
            dependencyBuildEvidencePresent = dependencyBuildEvidencePresent,
            selectedByCurrentRegistry = selectedByCurrentRegistry,
            disabledBoundaryOnly = disabledBoundaryOnly,
            providerCandidateOnly = providerCandidateOnly,
            wrapperOnly = wrapperOnly,
            randomnessSourceOnly = randomnessSourceOnly,
            testOnly = testOnly,
            implementationPresent = false,
            implementationEnabled = false,
            factoryPresent = false,
            factoryEnabled = false,
            registryEnabled = false,
            testScopeSelectable = false,
            productionSelectable = false,
            providerSelectable = false,
            productionProviderSelectable = false,
            operationAuthorized = false,
            redactionClass = redactionClass,
        )

    private fun stageStatus(
        stage: SkaldVaultV1ProviderSelectionPromotionStage,
    ): SkaldVaultV1ProviderSelectionPromotionStatus =
        when (stage) {
            SkaldVaultV1ProviderSelectionPromotionStage.CandidateDescribed,
            SkaldVaultV1ProviderSelectionPromotionStage.DependencyDeclaredBuildOnly ->
                SkaldVaultV1ProviderSelectionPromotionStatus.CandidateDescribedBuildEvidenceOnly
            SkaldVaultV1ProviderSelectionPromotionStage.ImplementationAbsent,
            SkaldVaultV1ProviderSelectionPromotionStage.ImplementationPresentButDisabled ->
                SkaldVaultV1ProviderSelectionPromotionStatus.ImplementationBlocked
            SkaldVaultV1ProviderSelectionPromotionStage.FactoryAbsent,
            SkaldVaultV1ProviderSelectionPromotionStage.FactoryPresentButDisabled ->
                SkaldVaultV1ProviderSelectionPromotionStatus.FactoryBlocked
            SkaldVaultV1ProviderSelectionPromotionStage.RegistryAbsent,
            SkaldVaultV1ProviderSelectionPromotionStage.RegistryPresentButDisabled ->
                SkaldVaultV1ProviderSelectionPromotionStatus.RegistryBlocked
            SkaldVaultV1ProviderSelectionPromotionStage.TestScopeSelectable ->
                SkaldVaultV1ProviderSelectionPromotionStatus.ProviderSelectionBlocked
            SkaldVaultV1ProviderSelectionPromotionStage.ProductionSelectable ->
                SkaldVaultV1ProviderSelectionPromotionStatus.ProductionSelectionBlocked
            SkaldVaultV1ProviderSelectionPromotionStage.ProviderOperationAuthorizationRequested,
            SkaldVaultV1ProviderSelectionPromotionStage.ProviderOperationAuthorizationApproved ->
                SkaldVaultV1ProviderSelectionPromotionStatus.OperationAuthorizationBlocked
            else -> SkaldVaultV1ProviderSelectionPromotionStatus.PromotionBlocked
        }

    private fun stageBlockers(
        stage: SkaldVaultV1ProviderSelectionPromotionStage,
    ): Set<SkaldVaultV1ProviderSelectionPromotionBlocker> =
        when (stage) {
            SkaldVaultV1ProviderSelectionPromotionStage.CandidateDescribed -> setOf(
                SkaldVaultV1ProviderSelectionPromotionBlocker.ProviderSelectionLockedToDisabledProvider,
                SkaldVaultV1ProviderSelectionPromotionBlocker.AuthorizationReadinessMatrixBlocked,
            )
            SkaldVaultV1ProviderSelectionPromotionStage.DependencyAbsent -> setOf(
                SkaldVaultV1ProviderSelectionPromotionBlocker.DependencyAbsent,
            )
            SkaldVaultV1ProviderSelectionPromotionStage.DependencyDeclaredBuildOnly -> setOf(
                SkaldVaultV1ProviderSelectionPromotionBlocker.DependencyBuildOnly,
                SkaldVaultV1ProviderSelectionPromotionBlocker.DependencyBuildBoundaryStillDisabled,
            )
            SkaldVaultV1ProviderSelectionPromotionStage.DependencyReviewed -> setOf(
                SkaldVaultV1ProviderSelectionPromotionBlocker.DependencyNotReviewed,
                SkaldVaultV1ProviderSelectionPromotionBlocker.DependencyProbeInsufficient,
            )
            SkaldVaultV1ProviderSelectionPromotionStage.SourceSetPlacementReviewed -> setOf(
                SkaldVaultV1ProviderSelectionPromotionBlocker.SourceSetPlacementNotReviewed,
                SkaldVaultV1ProviderSelectionPromotionBlocker.ProviderCandidatePackagingBlocked,
            )
            SkaldVaultV1ProviderSelectionPromotionStage.ImplementationAbsent -> setOf(
                SkaldVaultV1ProviderSelectionPromotionBlocker.ImplementationAbsent,
                SkaldVaultV1ProviderSelectionPromotionBlocker.ProviderCandidatePackagingBlocked,
            )
            SkaldVaultV1ProviderSelectionPromotionStage.ImplementationPresentButDisabled -> setOf(
                SkaldVaultV1ProviderSelectionPromotionBlocker.ImplementationDisabled,
                SkaldVaultV1ProviderSelectionPromotionBlocker.ProviderOperationAuthorizationBlocked,
            )
            SkaldVaultV1ProviderSelectionPromotionStage.FactoryAbsent -> setOf(
                SkaldVaultV1ProviderSelectionPromotionBlocker.FactoryAbsent,
            )
            SkaldVaultV1ProviderSelectionPromotionStage.FactoryPresentButDisabled -> setOf(
                SkaldVaultV1ProviderSelectionPromotionBlocker.FactoryDisabled,
            )
            SkaldVaultV1ProviderSelectionPromotionStage.RegistryAbsent -> setOf(
                SkaldVaultV1ProviderSelectionPromotionBlocker.RegistryDisabled,
                SkaldVaultV1ProviderSelectionPromotionBlocker.ProviderSelectionLockedToDisabledProvider,
            )
            SkaldVaultV1ProviderSelectionPromotionStage.RegistryPresentButDisabled -> setOf(
                SkaldVaultV1ProviderSelectionPromotionBlocker.RegistryDisabled,
                SkaldVaultV1ProviderSelectionPromotionBlocker.ProviderSelectionLockedToDisabledProvider,
            )
            SkaldVaultV1ProviderSelectionPromotionStage.TestScopeSelectable -> setOf(
                SkaldVaultV1ProviderSelectionPromotionBlocker.ProviderSelectionLockedToDisabledProvider,
                SkaldVaultV1ProviderSelectionPromotionBlocker.TestOnlyEvidenceCannotAuthorizeProduction,
            )
            SkaldVaultV1ProviderSelectionPromotionStage.ProductionSelectable -> setOf(
                SkaldVaultV1ProviderSelectionPromotionBlocker.ProductionProviderSelectableFalse,
                SkaldVaultV1ProviderSelectionPromotionBlocker.ProviderAcceptanceMissing,
            )
            SkaldVaultV1ProviderSelectionPromotionStage.ProviderOperationAuthorizationRequested -> setOf(
                SkaldVaultV1ProviderSelectionPromotionBlocker.ProviderOperationAuthorizationBlocked,
                SkaldVaultV1ProviderSelectionPromotionBlocker.ProviderSelectionLockedToDisabledProvider,
            )
            SkaldVaultV1ProviderSelectionPromotionStage.ProviderOperationAuthorizationApproved -> setOf(
                SkaldVaultV1ProviderSelectionPromotionBlocker.ProviderOperationAuthorizationBlocked,
                SkaldVaultV1ProviderSelectionPromotionBlocker.AuthorizationReadinessMatrixBlocked,
            )
            SkaldVaultV1ProviderSelectionPromotionStage.KatExecutionRequested -> setOf(
                SkaldVaultV1ProviderSelectionPromotionBlocker.KatContractNotApproved,
                SkaldVaultV1ProviderSelectionPromotionBlocker.KatExecutionUnavailable,
            )
            SkaldVaultV1ProviderSelectionPromotionStage.KatApproved -> setOf(
                SkaldVaultV1ProviderSelectionPromotionBlocker.KatContractNotApproved,
                SkaldVaultV1ProviderSelectionPromotionBlocker.KatExecutionUnavailable,
            )
            SkaldVaultV1ProviderSelectionPromotionStage.RuntimeRandomnessApproved -> setOf(
                SkaldVaultV1ProviderSelectionPromotionBlocker.RuntimeRandomnessBlocked,
            )
            SkaldVaultV1ProviderSelectionPromotionStage.KdfProviderSupportApproved -> setOf(
                SkaldVaultV1ProviderSelectionPromotionBlocker.KdfCalibrationBlocked,
            )
            SkaldVaultV1ProviderSelectionPromotionStage.AeadProviderSupportApproved -> setOf(
                SkaldVaultV1ProviderSelectionPromotionBlocker.ProviderOperationAuthorizationBlocked,
            )
            SkaldVaultV1ProviderSelectionPromotionStage.KeyWrappingSupportApproved -> setOf(
                SkaldVaultV1ProviderSelectionPromotionBlocker.SecureStorageAuthorizationBlocked,
            )
            SkaldVaultV1ProviderSelectionPromotionStage.CreationUnlockIntegrationApproved -> setOf(
                SkaldVaultV1ProviderSelectionPromotionBlocker.CreationAuthorizationBlocked,
                SkaldVaultV1ProviderSelectionPromotionBlocker.UnlockAuthorizationBlocked,
            )
            SkaldVaultV1ProviderSelectionPromotionStage.PersistenceIntegrationApproved -> setOf(
                SkaldVaultV1ProviderSelectionPromotionBlocker.SecureStorageAuthorizationBlocked,
                SkaldVaultV1ProviderSelectionPromotionBlocker.AuthorizationReadinessMatrixBlocked,
            )
            SkaldVaultV1ProviderSelectionPromotionStage.ReleaseValidationApproved -> setOf(
                SkaldVaultV1ProviderSelectionPromotionBlocker.ProductionReleaseReviewMissing,
                SkaldVaultV1ProviderSelectionPromotionBlocker.RedactionLeakageReviewMissing,
                SkaldVaultV1ProviderSelectionPromotionBlocker.ClearWipeReviewMissing,
                SkaldVaultV1ProviderSelectionPromotionBlocker.MigrationCorruptionReviewMissing,
            )
            SkaldVaultV1ProviderSelectionPromotionStage.MainnetApproved -> setOf(
                SkaldVaultV1ProviderSelectionPromotionBlocker.MainnetDisabled,
                SkaldVaultV1ProviderSelectionPromotionBlocker.ProductionReleaseReviewMissing,
            )
        }

    private val baseStatuses: Set<SkaldVaultV1ProviderSelectionPromotionStatus> = setOf(
        SkaldVaultV1ProviderSelectionPromotionStatus.BoundaryModeled,
        SkaldVaultV1ProviderSelectionPromotionStatus.StillDisabled,
        SkaldVaultV1ProviderSelectionPromotionStatus.EvidenceOnly,
        SkaldVaultV1ProviderSelectionPromotionStatus.PromotionBlocked,
        SkaldVaultV1ProviderSelectionPromotionStatus.WarningOnlyCannotAuthorize,
        SkaldVaultV1ProviderSelectionPromotionStatus.UserConsentCannotOverride,
        SkaldVaultV1ProviderSelectionPromotionStatus.TestOnlyRejectedForProduction,
    )

    private val commonCandidateBlockers: Set<SkaldVaultV1ProviderSelectionPromotionBlocker> = setOf(
        SkaldVaultV1ProviderSelectionPromotionBlocker.DependencyNotReviewed,
        SkaldVaultV1ProviderSelectionPromotionBlocker.SourceSetPlacementNotReviewed,
        SkaldVaultV1ProviderSelectionPromotionBlocker.ImplementationAbsent,
        SkaldVaultV1ProviderSelectionPromotionBlocker.FactoryAbsent,
        SkaldVaultV1ProviderSelectionPromotionBlocker.RegistryDisabled,
        SkaldVaultV1ProviderSelectionPromotionBlocker.ProviderSelectionLockedToDisabledProvider,
        SkaldVaultV1ProviderSelectionPromotionBlocker.ProductionProviderSelectableFalse,
        SkaldVaultV1ProviderSelectionPromotionBlocker.ProviderOperationAuthorizationBlocked,
        SkaldVaultV1ProviderSelectionPromotionBlocker.ProviderCandidatePackagingBlocked,
        SkaldVaultV1ProviderSelectionPromotionBlocker.DependencyBuildBoundaryStillDisabled,
        SkaldVaultV1ProviderSelectionPromotionBlocker.AuthorizationReadinessMatrixBlocked,
        SkaldVaultV1ProviderSelectionPromotionBlocker.ProviderAcceptanceMissing,
        SkaldVaultV1ProviderSelectionPromotionBlocker.DependencyProbeInsufficient,
        SkaldVaultV1ProviderSelectionPromotionBlocker.KatContractNotApproved,
        SkaldVaultV1ProviderSelectionPromotionBlocker.KatExecutionUnavailable,
        SkaldVaultV1ProviderSelectionPromotionBlocker.RuntimeRandomnessBlocked,
        SkaldVaultV1ProviderSelectionPromotionBlocker.KdfCalibrationBlocked,
        SkaldVaultV1ProviderSelectionPromotionBlocker.SecureStorageAuthorizationBlocked,
        SkaldVaultV1ProviderSelectionPromotionBlocker.CreationAuthorizationBlocked,
        SkaldVaultV1ProviderSelectionPromotionBlocker.UnlockAuthorizationBlocked,
        SkaldVaultV1ProviderSelectionPromotionBlocker.RedactionLeakageReviewMissing,
        SkaldVaultV1ProviderSelectionPromotionBlocker.ClearWipeReviewMissing,
        SkaldVaultV1ProviderSelectionPromotionBlocker.MigrationCorruptionReviewMissing,
        SkaldVaultV1ProviderSelectionPromotionBlocker.ProductionReleaseReviewMissing,
        SkaldVaultV1ProviderSelectionPromotionBlocker.MainnetDisabled,
    )

    private val baseOverrideBlockers: Set<SkaldVaultV1ProviderSelectionPromotionBlocker> = setOf(
        SkaldVaultV1ProviderSelectionPromotionBlocker.WarningOnlyEvidenceCannotAuthorize,
        SkaldVaultV1ProviderSelectionPromotionBlocker.UserConsentCannotOverride,
        SkaldVaultV1ProviderSelectionPromotionBlocker.TestOnlyEvidenceCannotAuthorizeProduction,
        SkaldVaultV1ProviderSelectionPromotionBlocker.MainnetDisabled,
    )
}
