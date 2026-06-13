package com.libertasprimordium.skald.security

interface SkaldVaultV1ProviderCandidatePackagingBoundary {
    fun evaluate(
        request: SkaldVaultV1ProviderCandidatePackagingRequest,
    ): SkaldVaultV1ProviderCandidatePackagingResult<SkaldVaultV1ProviderCandidatePackagingEvidence>
}

enum class SkaldVaultV1ProviderCandidatePackagingSource(val label: String) {
    CurrentTypedEvidence("current typed provider-candidate packaging evidence"),
    CandidateAudit("candidate packaging audit request"),
    SourceSetPlacementAudit("source-set placement audit request"),
    PromotionGateAudit("promotion gate audit request"),
}

enum class SkaldVaultV1ProviderCandidatePackagingStatus(val label: String) {
    BoundaryModeled("provider candidate packaging boundary modeled"),
    StillDisabled("still disabled"),
    EvidenceOnly("evidence only"),
    FutureReviewRequired("future review required"),
    NonSelectable("non-selectable"),
    NonExecutable("non-executable"),
    OperationUnauthorized("provider operation unauthorized"),
    TestOnlyRejectedForProduction("test-only evidence rejected for production"),
    WarningOnlyCannotAuthorize("warning-only evidence cannot authorize"),
    UserConsentCannotOverride("user consent cannot override"),
    UnsupportedFailClosed("unsupported fail closed"),
}

enum class SkaldVaultV1ProviderCandidateFamily(val label: String) {
    TinkJvmProviderCandidate("Tink JVM provider candidate"),
    BouncyCastleJvmProviderCandidate("Bouncy Castle JVM provider candidate"),
    AndroidKeystoreWrapperCandidate("Android Keystore wrapper candidate"),
    PlatformOsCsprngCandidate("platform OS CSPRNG candidate"),
    TestOnlyDeterministicProviderCandidate("test-only deterministic provider candidate"),
    UnknownCandidate("unknown candidate"),
    UnsupportedCandidate("unsupported candidate"),
}

enum class SkaldVaultV1ProviderCandidateImplementationStatus(val label: String) {
    FutureOnlyNotImplemented("future-only, not implemented"),
    WrapperOnlyFutureReview("wrapper-only future review"),
    RandomnessSourceOnlyFutureReview("randomness-source-only future review"),
    TestOnlyRejectedForProduction("test-only rejected for production"),
    UnknownFailClosed("unknown fail closed"),
    UnsupportedFailClosed("unsupported fail closed"),
}

enum class SkaldVaultV1ProviderCandidateDependencyCategory(val label: String) {
    DependencyAbsent("dependency absent"),
    DependencyDeclaredButUnused("dependency declared but unused"),
    DependencyCompileOnlyFutureReview("dependency compile-only future review"),
    DependencyTestOnly("dependency test-only"),
    DependencyRuntimeProductionCandidateFutureReview("dependency runtime production candidate future review"),
    DependencyForbidden("dependency forbidden"),
    DependencyUnknown("dependency unknown"),
}

enum class SkaldVaultV1ProviderCandidateSourceSetPlacement(val label: String) {
    CommonModelEvidenceOnly("common model/evidence only allowed"),
    CommonProductionExecutionForbidden("common production provider execution forbidden"),
    AndroidProductionExecutionForbiddenThisBranch("Android production execution forbidden in this branch"),
    DesktopProductionExecutionForbiddenThisBranch("desktop production execution forbidden in this branch"),
    TestKatVectorScaffoldingFutureReviewedOnly("test KAT/vector scaffolding future-reviewed only"),
    ProductionProviderExecutionForbidden("production provider execution forbidden"),
    ProductionProviderSelectionForbidden("production provider selection forbidden"),
}

enum class SkaldVaultV1ProviderCandidateOperationSurfaceStatus(val label: String) {
    ProviderNeutralEvidenceOnly("provider-neutral evidence only"),
    ProviderOperationSurfaceBlocked("provider operation surface blocked"),
    RuntimeExecutionForbidden("runtime execution forbidden"),
    KatExecutionForbidden("KAT execution forbidden"),
    ProductionSelectionForbidden("production selection forbidden"),
}

enum class SkaldVaultV1ProviderCandidateSupportStatus(val label: String) {
    FutureReviewRequired("future review required"),
    BlockedStillDisabled("blocked still disabled"),
    WrapperOnlyFutureReview("wrapper-only future review"),
    RandomnessSourceOnlyFutureReview("randomness-source-only future review"),
    TestOnlyRejectedForProduction("test-only rejected for production"),
    UnsupportedFailClosed("unsupported fail closed"),
}

enum class SkaldVaultV1ProviderCandidatePlatformSupportClass(val label: String) {
    CommonEvidenceOnly("common evidence only"),
    AndroidFutureReviewedWrapperOnly("Android future-reviewed wrapper only"),
    DesktopJvmFutureReviewedOnly("desktop JVM future-reviewed only"),
    CrossPlatformSplitProviderFutureReview("cross-platform split provider future review"),
    TestOnlyNotProduction("test-only, not production"),
    UnknownFailClosed("unknown fail closed"),
    UnsupportedFailClosed("unsupported fail closed"),
}

enum class SkaldVaultV1ProviderCandidateReviewRequirement(val label: String) {
    PackagingBoundaryModeled("provider candidate packaging boundary modeled"),
    ProviderDependencyReviewed("provider dependency reviewed"),
    CorrectSourceSetImplementation("provider implementation added in correct source set"),
    ProviderOperationAuthorizationApproved("provider operation authorization approved"),
    RuntimeRandomnessAuthorizationApproved("runtime randomness authorization approved"),
    KdfCalibrationAuthorizationApproved("KDF calibration authorization approved"),
    SecureStorageAuthorizationApprovedWhereWrappingOrStorageInvolved(
        "secure-storage authorization approved where wrapping or storage is involved",
    ),
    CreationAuthorizationApprovedWhereCreationIsInvolved(
        "creation authorization approved where creation is involved",
    ),
    UnlockAuthorizationApprovedWhereUnlockIsInvolved(
        "unlock authorization approved where unlock is involved",
    ),
    ProviderKatContractApproved("provider KAT contract approved"),
    RuntimeProviderRandomnessChecksApproved("runtime provider/randomness checks approved"),
    RedactionLeakagePolicyApproved("redaction/leakage policy approved"),
    ClearWipeStrategyApproved("clear/wipe strategy approved"),
    MigrationCorruptionPolicyApprovedWherePersistedMaterialInvolved(
        "migration/corruption policy approved where persisted material is involved",
    ),
    ProductionProviderAcceptanceContractApproved("production provider acceptance contract approved"),
    AuthorizationReadinessMatrixAllowsPromotion("authorization/readiness matrix allows promotion"),
    ProviderSelectionRegistryReviewed("provider selection registry reviewed"),
    LaterProductionProviderSelectableChangeRequired(
        "productionProviderSelectable explicitly changed in a later branch",
    ),
    MainnetReleaseReviewRequired("mainnet remains disabled until release review"),
}

enum class SkaldVaultV1ProviderCandidateBlocker(val label: String) {
    DisabledProviderSelection("disabled provider selection"),
    ProductionProviderSelectableFalse("productionProviderSelectable is false"),
    ProviderImplementationMissing("provider implementation missing"),
    ProviderDependencyInactive("provider dependency inactive"),
    ProviderRuntimeNotInstantiable("provider runtime not instantiable"),
    ProviderOperationAuthorizationBlocked("provider operation authorization blocked"),
    RuntimeRandomnessAuthorizationBlocked("runtime randomness authorization blocked"),
    KdfCalibrationAuthorizationBlocked("KDF calibration authorization blocked"),
    SecureStorageAuthorizationBlocked("secure-storage authorization blocked"),
    CreationAuthorizationBlocked("creation authorization blocked"),
    UnlockAuthorizationBlocked("unlock authorization blocked"),
    AuthorizationReadinessMatrixBlocksPromotion("authorization/readiness matrix blocks promotion"),
    ProviderKatApprovalMissing("provider KAT approval missing"),
    RuntimeProviderChecksMissing("runtime provider/randomness checks missing"),
    RedactionReviewMissing("redaction/leakage review missing"),
    ClearWipeReviewMissing("clear/wipe review missing"),
    MigrationCorruptionReviewMissing("migration/corruption review missing"),
    ProductionProviderAcceptanceIncomplete("production provider acceptance incomplete"),
    ProviderSelectionRegistryReviewMissing("provider selection registry review missing"),
    LaterProductionProviderSelectableChangeRequired(
        "later productionProviderSelectable change required",
    ),
    MainnetDisabled("mainnet disabled"),
    WarningOnlyEvidenceCannotAuthorize("warning-only evidence cannot authorize"),
    UserConsentCannotOverride("user consent cannot override"),
    TestOnlyEvidenceRejectedForProduction("test-only evidence rejected for production"),
    SourceSetPlacementReviewMissing("source-set placement review missing"),
    DependencyReviewMissing("dependency review missing"),
    PlatformWrapperReviewMissing("platform wrapper review missing"),
    UnsupportedCandidateRejected("unsupported candidate rejected"),
    UnknownCandidateRejected("unknown candidate rejected"),
}

enum class SkaldVaultV1ProviderCandidateRedactionClass(val label: String) {
    PolicyIdsOnly("policy ids only"),
    CandidateFamilyOnly("candidate family only"),
    BoundaryAndBlockerClassesOnly("boundary and blocker classes only"),
    SourceSetCategoryOnly("source-set category only"),
    NoDiagnosticPayload("no diagnostic payload"),
}

data class SkaldVaultV1ProviderCandidateCapability(
    val providerCandidateImplemented: Boolean,
    val providerDependencyActive: Boolean,
    val providerRuntimeInstantiable: Boolean,
    val providerSelectable: Boolean,
    val productionProviderSelectable: Boolean,
    val providerOperationAuthorized: Boolean,
    val providerKatExecutionAvailable: Boolean,
    val runtimeRandomnessAvailable: Boolean,
    val kdfExecutionAvailable: Boolean,
    val aeadExecutionAvailable: Boolean,
    val hkdfHmacExecutionAvailable: Boolean,
    val headerCommitmentAvailable: Boolean,
    val keyWrappingAvailable: Boolean,
    val providerClearAvailable: Boolean,
    val vaultCreationAvailable: Boolean,
    val vaultUnlockAvailable: Boolean,
    val vaultPersistenceAvailable: Boolean,
    val mainnetAvailable: Boolean,
) {
    companion object {
        val StillDisabled = SkaldVaultV1ProviderCandidateCapability(
            providerCandidateImplemented = false,
            providerDependencyActive = false,
            providerRuntimeInstantiable = false,
            providerSelectable = false,
            productionProviderSelectable = false,
            providerOperationAuthorized = false,
            providerKatExecutionAvailable = false,
            runtimeRandomnessAvailable = false,
            kdfExecutionAvailable = false,
            aeadExecutionAvailable = false,
            hkdfHmacExecutionAvailable = false,
            headerCommitmentAvailable = false,
            keyWrappingAvailable = false,
            providerClearAvailable = false,
            vaultCreationAvailable = false,
            vaultUnlockAvailable = false,
            vaultPersistenceAvailable = false,
            mainnetAvailable = false,
        )
    }
}

class SkaldVaultV1ProviderCandidatePolicyToken private constructor(
    val policyId: String,
    val candidateFamily: SkaldVaultV1ProviderCandidateFamily?,
) {
    val containsProviderHandle: Boolean = false
    val containsCryptoObject: Boolean = false
    val containsByteMaterial: Boolean = false
    val containsPathOrRootText: Boolean = false
    val containsStorageIdentifier: Boolean = false
    val containsSecretMaterial: Boolean = false

    override fun toString(): String =
        "SkaldVaultV1ProviderCandidatePolicyToken(" +
            "policyId=$policyId, " +
            "candidateFamily=$candidateFamily, " +
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
            candidateFamily: SkaldVaultV1ProviderCandidateFamily?,
        ): SkaldVaultV1ProviderCandidatePolicyToken {
            return SkaldVaultV1ProviderCandidatePolicyToken(policyId, candidateFamily)
        }
    }
}

class SkaldVaultV1ProviderCandidatePackagingRequest private constructor(
    val source: SkaldVaultV1ProviderCandidatePackagingSource,
    val candidateFamily: SkaldVaultV1ProviderCandidateFamily?,
    val dependencyCategory: SkaldVaultV1ProviderCandidateDependencyCategory?,
    val sourceSetPlacement: SkaldVaultV1ProviderCandidateSourceSetPlacement?,
    val operationSurfaceStatus: SkaldVaultV1ProviderCandidateOperationSurfaceStatus?,
    private val providerSelectionResult: VaultCryptoProviderSelectionResult?,
    private val providerOperationEvidence: SkaldVaultV1VaultProviderOperationAuthorizationEvidence?,
    private val runtimeRandomnessEvidence: SkaldVaultV1VaultRuntimeRandomnessAuthorizationEvidence?,
    private val kdfCalibrationEvidence: SkaldVaultV1VaultKdfCalibrationAuthorizationEvidence?,
    private val secureStorageEvidence: SkaldVaultV1VaultSecureStorageAuthorizationEvidence?,
    private val creationAuthorizationEvidence: SkaldVaultV1VaultCreationAuthorizationEvidence?,
    private val unlockAuthorizationEvidence: SkaldVaultV1VaultUnlockAuthorizationEvidence?,
    private val authorizationReadinessMatrixEvidence: SkaldVaultV1AuthorizationReadinessMatrixEvidence?,
    private val providerAcceptanceAssessment: ProductionProviderAcceptanceAssessment?,
    private val dependencyProbeResult: VaultCryptoDependencyProbeResult?,
) {
    val providerSelectionEvidenceSupplied: Boolean
        get() = providerSelectionResult != null

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

    val authorizationReadinessMatrixEvidenceSupplied: Boolean
        get() = authorizationReadinessMatrixEvidence != null

    val providerAcceptanceEvidenceSupplied: Boolean
        get() = providerAcceptanceAssessment != null

    val dependencyProbeEvidenceSupplied: Boolean
        get() = dependencyProbeResult != null

    override fun toString(): String =
        "SkaldVaultV1ProviderCandidatePackagingRequest(" +
            "source=$source, " +
            "candidateFamily=$candidateFamily, " +
            "dependencyCategory=$dependencyCategory, " +
            "sourceSetPlacement=$sourceSetPlacement, " +
            "operationSurfaceStatus=$operationSurfaceStatus, " +
            "providerSelection=<redacted>, " +
            "providerOperation=<redacted>, " +
            "runtimeRandomness=<redacted>, " +
            "kdfCalibration=<redacted>, " +
            "secureStorage=<redacted>, " +
            "creationAuthorization=<redacted>, " +
            "unlockAuthorization=<redacted>, " +
            "authorizationReadinessMatrix=<redacted>, " +
            "providerAcceptance=<redacted>, " +
            "dependencyProbe=<redacted>" +
            ")"

    companion object {
        fun currentEvidence(
            candidateFamily: SkaldVaultV1ProviderCandidateFamily? = null,
            dependencyCategory: SkaldVaultV1ProviderCandidateDependencyCategory? = null,
            sourceSetPlacement: SkaldVaultV1ProviderCandidateSourceSetPlacement? = null,
            operationSurfaceStatus: SkaldVaultV1ProviderCandidateOperationSurfaceStatus? = null,
            providerSelectionResult: VaultCryptoProviderSelectionResult? = null,
            providerOperationEvidence: SkaldVaultV1VaultProviderOperationAuthorizationEvidence? = null,
            runtimeRandomnessEvidence: SkaldVaultV1VaultRuntimeRandomnessAuthorizationEvidence? = null,
            kdfCalibrationEvidence: SkaldVaultV1VaultKdfCalibrationAuthorizationEvidence? = null,
            secureStorageEvidence: SkaldVaultV1VaultSecureStorageAuthorizationEvidence? = null,
            creationAuthorizationEvidence: SkaldVaultV1VaultCreationAuthorizationEvidence? = null,
            unlockAuthorizationEvidence: SkaldVaultV1VaultUnlockAuthorizationEvidence? = null,
            authorizationReadinessMatrixEvidence: SkaldVaultV1AuthorizationReadinessMatrixEvidence? = null,
            providerAcceptanceAssessment: ProductionProviderAcceptanceAssessment? = null,
            dependencyProbeResult: VaultCryptoDependencyProbeResult? = null,
        ): SkaldVaultV1ProviderCandidatePackagingRequest =
            SkaldVaultV1ProviderCandidatePackagingRequest(
                source = SkaldVaultV1ProviderCandidatePackagingSource.CurrentTypedEvidence,
                candidateFamily = candidateFamily,
                dependencyCategory = dependencyCategory,
                sourceSetPlacement = sourceSetPlacement,
                operationSurfaceStatus = operationSurfaceStatus,
                providerSelectionResult = providerSelectionResult,
                providerOperationEvidence = providerOperationEvidence,
                runtimeRandomnessEvidence = runtimeRandomnessEvidence,
                kdfCalibrationEvidence = kdfCalibrationEvidence,
                secureStorageEvidence = secureStorageEvidence,
                creationAuthorizationEvidence = creationAuthorizationEvidence,
                unlockAuthorizationEvidence = unlockAuthorizationEvidence,
                authorizationReadinessMatrixEvidence = authorizationReadinessMatrixEvidence,
                providerAcceptanceAssessment = providerAcceptanceAssessment,
                dependencyProbeResult = dependencyProbeResult,
            )

        fun forCandidate(
            candidateFamily: SkaldVaultV1ProviderCandidateFamily,
        ): SkaldVaultV1ProviderCandidatePackagingRequest =
            SkaldVaultV1ProviderCandidatePackagingRequest(
                source = SkaldVaultV1ProviderCandidatePackagingSource.CandidateAudit,
                candidateFamily = candidateFamily,
                dependencyCategory = null,
                sourceSetPlacement = null,
                operationSurfaceStatus = null,
                providerSelectionResult = null,
                providerOperationEvidence = null,
                runtimeRandomnessEvidence = null,
                kdfCalibrationEvidence = null,
                secureStorageEvidence = null,
                creationAuthorizationEvidence = null,
                unlockAuthorizationEvidence = null,
                authorizationReadinessMatrixEvidence = null,
                providerAcceptanceAssessment = null,
                dependencyProbeResult = null,
            )

        fun sourceSetPlacementAudit(): SkaldVaultV1ProviderCandidatePackagingRequest =
            SkaldVaultV1ProviderCandidatePackagingRequest(
                source = SkaldVaultV1ProviderCandidatePackagingSource.SourceSetPlacementAudit,
                candidateFamily = null,
                dependencyCategory = null,
                sourceSetPlacement =
                    SkaldVaultV1ProviderCandidateSourceSetPlacement.CommonModelEvidenceOnly,
                operationSurfaceStatus = null,
                providerSelectionResult = null,
                providerOperationEvidence = null,
                runtimeRandomnessEvidence = null,
                kdfCalibrationEvidence = null,
                secureStorageEvidence = null,
                creationAuthorizationEvidence = null,
                unlockAuthorizationEvidence = null,
                authorizationReadinessMatrixEvidence = null,
                providerAcceptanceAssessment = null,
                dependencyProbeResult = null,
            )

        fun promotionGateAudit(): SkaldVaultV1ProviderCandidatePackagingRequest =
            SkaldVaultV1ProviderCandidatePackagingRequest(
                source = SkaldVaultV1ProviderCandidatePackagingSource.PromotionGateAudit,
                candidateFamily = null,
                dependencyCategory = null,
                sourceSetPlacement = null,
                operationSurfaceStatus = null,
                providerSelectionResult = null,
                providerOperationEvidence = null,
                runtimeRandomnessEvidence = null,
                kdfCalibrationEvidence = null,
                secureStorageEvidence = null,
                creationAuthorizationEvidence = null,
                unlockAuthorizationEvidence = null,
                authorizationReadinessMatrixEvidence = null,
                providerAcceptanceAssessment = null,
                dependencyProbeResult = null,
            )
    }
}

data class SkaldVaultV1ProviderCandidateRow(
    val family: SkaldVaultV1ProviderCandidateFamily,
    val implementationStatus: SkaldVaultV1ProviderCandidateImplementationStatus,
    val dependencyCategory: SkaldVaultV1ProviderCandidateDependencyCategory,
    val platformSupportClass: SkaldVaultV1ProviderCandidatePlatformSupportClass,
    val statuses: Set<SkaldVaultV1ProviderCandidatePackagingStatus>,
    val blockers: Set<SkaldVaultV1ProviderCandidateBlocker>,
    val requiredGates: Set<SkaldVaultV1ProviderCandidateReviewRequirement>,
    val selectable: Boolean,
    val executable: Boolean,
    val productionAuthorized: Boolean,
    val redactionClass: SkaldVaultV1ProviderCandidateRedactionClass,
)

data class SkaldVaultV1ProviderCandidatePackagingSummary(
    val policyId: String,
    val policyVersion: Int,
    val statuses: Set<SkaldVaultV1ProviderCandidatePackagingStatus>,
    val candidateFamilies: Set<SkaldVaultV1ProviderCandidateFamily>,
    val dependencyCategories: Set<SkaldVaultV1ProviderCandidateDependencyCategory>,
    val sourceSetPlacements: Set<SkaldVaultV1ProviderCandidateSourceSetPlacement>,
    val requiredGates: Set<SkaldVaultV1ProviderCandidateReviewRequirement>,
    val blockers: Set<SkaldVaultV1ProviderCandidateBlocker>,
    val capability: SkaldVaultV1ProviderCandidateCapability,
    val stillDisabled: Boolean,
    val evidenceOnly: Boolean,
    val noCandidateSelectable: Boolean,
)

data class SkaldVaultV1ProviderCandidatePackagingEvidence(
    val policyId: String,
    val policyVersion: Int,
    val source: SkaldVaultV1ProviderCandidatePackagingSource,
    val status: SkaldVaultV1ProviderCandidatePackagingStatus,
    val candidateRows: List<SkaldVaultV1ProviderCandidateRow>,
    val sourceSetPlacements: Set<SkaldVaultV1ProviderCandidateSourceSetPlacement>,
    val operationSurfaceStatuses: Set<SkaldVaultV1ProviderCandidateOperationSurfaceStatus>,
    val supportStatuses: Set<SkaldVaultV1ProviderCandidateSupportStatus>,
    val requiredGates: Set<SkaldVaultV1ProviderCandidateReviewRequirement>,
    val blockers: Set<SkaldVaultV1ProviderCandidateBlocker>,
    val capability: SkaldVaultV1ProviderCandidateCapability,
    val policyTokenEvidence: SkaldVaultV1ProviderCandidatePolicyToken,
    val providerSelectionEvidenceConsumed: Boolean,
    val providerOperationEvidenceConsumed: Boolean,
    val runtimeRandomnessEvidenceConsumed: Boolean,
    val kdfCalibrationEvidenceConsumed: Boolean,
    val secureStorageEvidenceConsumed: Boolean,
    val creationAuthorizationEvidenceConsumed: Boolean,
    val unlockAuthorizationEvidenceConsumed: Boolean,
    val authorizationReadinessMatrixEvidenceConsumed: Boolean,
    val providerAcceptanceEvidenceConsumed: Boolean,
    val dependencyProbeEvidenceConsumed: Boolean,
    val providerCandidatePackagingBoundaryModeled: Boolean = true,
    val providerCandidatePackagingStillDisabled: Boolean = true,
    val providerCandidatePackagingDoesNotImplementProvider: Boolean = true,
    val providerCandidatePackagingDoesNotActivateDependencies: Boolean = true,
    val providerCandidatePackagingDoesNotEnableProviderSelection: Boolean = true,
    val providerCandidatePackagingDoesNotRunCrypto: Boolean = true,
    val providerCandidatePackagingDoesNotEnableCreation: Boolean = true,
    val providerCandidatePackagingDoesNotEnableUnlock: Boolean = true,
    val providerCandidatePackagingDoesNotEnablePersistence: Boolean = true,
    val providerCandidateImplemented: Boolean = false,
    val providerDependencyActive: Boolean = false,
    val providerRuntimeInstantiable: Boolean = false,
    val providerSelectable: Boolean = false,
    val productionProviderSelectable: Boolean = false,
    val providerOperationAuthorized: Boolean = false,
    val providerKatExecutionAvailable: Boolean = false,
    val runtimeRandomnessAvailable: Boolean = false,
    val kdfExecutionAvailable: Boolean = false,
    val aeadExecutionAvailable: Boolean = false,
    val vaultCreationAvailable: Boolean = false,
    val vaultUnlockAvailable: Boolean = false,
    val vaultPersistenceAvailable: Boolean = false,
    val mainnetAvailable: Boolean = false,
) {
    override fun toString(): String =
        "SkaldVaultV1ProviderCandidatePackagingEvidence(" +
            "policyId=$policyId, " +
            "policyVersion=$policyVersion, " +
            "source=$source, " +
            "status=$status, " +
            "candidateRows=${candidateRows.size}, " +
            "sourceSetPlacements=${sourceSetPlacements.size}, " +
            "requiredGates=${requiredGates.size}, " +
            "blockers=${blockers.size}, " +
            "policyToken:<redacted>" +
            ")"
}

sealed class SkaldVaultV1ProviderCandidatePackagingResult<out T> {
    data class Blocked<out T>(
        val value: T,
    ) : SkaldVaultV1ProviderCandidatePackagingResult<T>() {
        override fun toString(): String =
            "SkaldVaultV1ProviderCandidatePackagingResult.Blocked(value=<redacted-provider-candidate-evidence>)"
    }
}

object SkaldVaultV1ProviderCandidatePackagingPolicy :
    SkaldVaultV1ProviderCandidatePackagingBoundary {
    const val POLICY_ID = "skald-vault-v1-provider-candidate-packaging-boundary-v1"
    const val POLICY_VERSION = 1

    override fun evaluate(
        request: SkaldVaultV1ProviderCandidatePackagingRequest,
    ): SkaldVaultV1ProviderCandidatePackagingResult<SkaldVaultV1ProviderCandidatePackagingEvidence> {
        val rows = rowsFor(request.candidateFamily)
        return SkaldVaultV1ProviderCandidatePackagingResult.Blocked(
            SkaldVaultV1ProviderCandidatePackagingEvidence(
                policyId = POLICY_ID,
                policyVersion = POLICY_VERSION,
                source = request.source,
                status = SkaldVaultV1ProviderCandidatePackagingStatus.StillDisabled,
                candidateRows = rows,
                sourceSetPlacements = SkaldVaultV1ProviderCandidateSourceSetPlacement.entries.toSet(),
                operationSurfaceStatuses = SkaldVaultV1ProviderCandidateOperationSurfaceStatus.entries.toSet(),
                supportStatuses = SkaldVaultV1ProviderCandidateSupportStatus.entries.toSet(),
                requiredGates = SkaldVaultV1ProviderCandidateReviewRequirement.entries.toSet(),
                blockers = rows.flatMap { it.blockers }.toSet() + baseBlockers,
                capability = SkaldVaultV1ProviderCandidateCapability.StillDisabled,
                policyTokenEvidence = SkaldVaultV1ProviderCandidatePolicyToken.redacted(
                    policyId = POLICY_ID,
                    candidateFamily = request.candidateFamily,
                ),
                providerSelectionEvidenceConsumed = request.providerSelectionEvidenceSupplied,
                providerOperationEvidenceConsumed = request.providerOperationEvidenceSupplied,
                runtimeRandomnessEvidenceConsumed = request.runtimeRandomnessEvidenceSupplied,
                kdfCalibrationEvidenceConsumed = request.kdfCalibrationEvidenceSupplied,
                secureStorageEvidenceConsumed = request.secureStorageEvidenceSupplied,
                creationAuthorizationEvidenceConsumed = request.creationAuthorizationEvidenceSupplied,
                unlockAuthorizationEvidenceConsumed = request.unlockAuthorizationEvidenceSupplied,
                authorizationReadinessMatrixEvidenceConsumed =
                    request.authorizationReadinessMatrixEvidenceSupplied,
                providerAcceptanceEvidenceConsumed = request.providerAcceptanceEvidenceSupplied,
                dependencyProbeEvidenceConsumed = request.dependencyProbeEvidenceSupplied,
            ),
        )
    }

    fun currentPolicySummary(): SkaldVaultV1ProviderCandidatePackagingSummary =
        SkaldVaultV1ProviderCandidatePackagingSummary(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            statuses = SkaldVaultV1ProviderCandidatePackagingStatus.entries.toSet(),
            candidateFamilies = SkaldVaultV1ProviderCandidateFamily.entries.toSet(),
            dependencyCategories = SkaldVaultV1ProviderCandidateDependencyCategory.entries.toSet(),
            sourceSetPlacements = SkaldVaultV1ProviderCandidateSourceSetPlacement.entries.toSet(),
            requiredGates = SkaldVaultV1ProviderCandidateReviewRequirement.entries.toSet(),
            blockers = SkaldVaultV1ProviderCandidateBlocker.entries.toSet(),
            capability = SkaldVaultV1ProviderCandidateCapability.StillDisabled,
            stillDisabled = true,
            evidenceOnly = true,
            noCandidateSelectable = true,
        )

    private fun rowsFor(
        candidateFamily: SkaldVaultV1ProviderCandidateFamily?,
    ): List<SkaldVaultV1ProviderCandidateRow> {
        val rows = currentRows()
        return candidateFamily?.let { family -> rows.filter { it.family == family } } ?: rows
    }

    private fun currentRows(): List<SkaldVaultV1ProviderCandidateRow> =
        listOf(
            row(
                family = SkaldVaultV1ProviderCandidateFamily.TinkJvmProviderCandidate,
                implementationStatus = SkaldVaultV1ProviderCandidateImplementationStatus.FutureOnlyNotImplemented,
                dependencyCategory =
                    SkaldVaultV1ProviderCandidateDependencyCategory.DependencyRuntimeProductionCandidateFutureReview,
                platformSupportClass =
                    SkaldVaultV1ProviderCandidatePlatformSupportClass.CrossPlatformSplitProviderFutureReview,
                blockers = commonProviderBlockers,
            ),
            row(
                family = SkaldVaultV1ProviderCandidateFamily.BouncyCastleJvmProviderCandidate,
                implementationStatus = SkaldVaultV1ProviderCandidateImplementationStatus.FutureOnlyNotImplemented,
                dependencyCategory =
                    SkaldVaultV1ProviderCandidateDependencyCategory.DependencyRuntimeProductionCandidateFutureReview,
                platformSupportClass =
                    SkaldVaultV1ProviderCandidatePlatformSupportClass.CrossPlatformSplitProviderFutureReview,
                blockers = commonProviderBlockers,
            ),
            row(
                family = SkaldVaultV1ProviderCandidateFamily.AndroidKeystoreWrapperCandidate,
                implementationStatus = SkaldVaultV1ProviderCandidateImplementationStatus.WrapperOnlyFutureReview,
                dependencyCategory = SkaldVaultV1ProviderCandidateDependencyCategory.DependencyAbsent,
                platformSupportClass =
                    SkaldVaultV1ProviderCandidatePlatformSupportClass.AndroidFutureReviewedWrapperOnly,
                blockers = commonProviderBlockers +
                    SkaldVaultV1ProviderCandidateBlocker.PlatformWrapperReviewMissing,
            ),
            row(
                family = SkaldVaultV1ProviderCandidateFamily.PlatformOsCsprngCandidate,
                implementationStatus =
                    SkaldVaultV1ProviderCandidateImplementationStatus.RandomnessSourceOnlyFutureReview,
                dependencyCategory = SkaldVaultV1ProviderCandidateDependencyCategory.DependencyAbsent,
                platformSupportClass = SkaldVaultV1ProviderCandidatePlatformSupportClass.CommonEvidenceOnly,
                blockers = commonProviderBlockers +
                    SkaldVaultV1ProviderCandidateBlocker.RuntimeRandomnessAuthorizationBlocked,
            ),
            row(
                family = SkaldVaultV1ProviderCandidateFamily.TestOnlyDeterministicProviderCandidate,
                implementationStatus =
                    SkaldVaultV1ProviderCandidateImplementationStatus.TestOnlyRejectedForProduction,
                dependencyCategory = SkaldVaultV1ProviderCandidateDependencyCategory.DependencyTestOnly,
                platformSupportClass = SkaldVaultV1ProviderCandidatePlatformSupportClass.TestOnlyNotProduction,
                blockers = commonProviderBlockers +
                    SkaldVaultV1ProviderCandidateBlocker.TestOnlyEvidenceRejectedForProduction,
            ),
            row(
                family = SkaldVaultV1ProviderCandidateFamily.UnknownCandidate,
                implementationStatus = SkaldVaultV1ProviderCandidateImplementationStatus.UnknownFailClosed,
                dependencyCategory = SkaldVaultV1ProviderCandidateDependencyCategory.DependencyUnknown,
                platformSupportClass = SkaldVaultV1ProviderCandidatePlatformSupportClass.UnknownFailClosed,
                blockers = commonProviderBlockers +
                    SkaldVaultV1ProviderCandidateBlocker.UnknownCandidateRejected,
            ),
            row(
                family = SkaldVaultV1ProviderCandidateFamily.UnsupportedCandidate,
                implementationStatus = SkaldVaultV1ProviderCandidateImplementationStatus.UnsupportedFailClosed,
                dependencyCategory = SkaldVaultV1ProviderCandidateDependencyCategory.DependencyForbidden,
                platformSupportClass = SkaldVaultV1ProviderCandidatePlatformSupportClass.UnsupportedFailClosed,
                blockers = commonProviderBlockers +
                    SkaldVaultV1ProviderCandidateBlocker.UnsupportedCandidateRejected,
            ),
        )

    private fun row(
        family: SkaldVaultV1ProviderCandidateFamily,
        implementationStatus: SkaldVaultV1ProviderCandidateImplementationStatus,
        dependencyCategory: SkaldVaultV1ProviderCandidateDependencyCategory,
        platformSupportClass: SkaldVaultV1ProviderCandidatePlatformSupportClass,
        blockers: Set<SkaldVaultV1ProviderCandidateBlocker>,
    ): SkaldVaultV1ProviderCandidateRow =
        SkaldVaultV1ProviderCandidateRow(
            family = family,
            implementationStatus = implementationStatus,
            dependencyCategory = dependencyCategory,
            platformSupportClass = platformSupportClass,
            statuses = baseStatuses,
            blockers = blockers + baseBlockers,
            requiredGates = SkaldVaultV1ProviderCandidateReviewRequirement.entries.toSet(),
            selectable = false,
            executable = false,
            productionAuthorized = false,
            redactionClass = SkaldVaultV1ProviderCandidateRedactionClass.BoundaryAndBlockerClassesOnly,
        )

    private val commonProviderBlockers: Set<SkaldVaultV1ProviderCandidateBlocker> = setOf(
        SkaldVaultV1ProviderCandidateBlocker.DisabledProviderSelection,
        SkaldVaultV1ProviderCandidateBlocker.ProductionProviderSelectableFalse,
        SkaldVaultV1ProviderCandidateBlocker.ProviderImplementationMissing,
        SkaldVaultV1ProviderCandidateBlocker.ProviderDependencyInactive,
        SkaldVaultV1ProviderCandidateBlocker.ProviderRuntimeNotInstantiable,
        SkaldVaultV1ProviderCandidateBlocker.ProviderOperationAuthorizationBlocked,
        SkaldVaultV1ProviderCandidateBlocker.RuntimeRandomnessAuthorizationBlocked,
        SkaldVaultV1ProviderCandidateBlocker.KdfCalibrationAuthorizationBlocked,
        SkaldVaultV1ProviderCandidateBlocker.SecureStorageAuthorizationBlocked,
        SkaldVaultV1ProviderCandidateBlocker.CreationAuthorizationBlocked,
        SkaldVaultV1ProviderCandidateBlocker.UnlockAuthorizationBlocked,
        SkaldVaultV1ProviderCandidateBlocker.AuthorizationReadinessMatrixBlocksPromotion,
        SkaldVaultV1ProviderCandidateBlocker.ProviderKatApprovalMissing,
        SkaldVaultV1ProviderCandidateBlocker.RuntimeProviderChecksMissing,
        SkaldVaultV1ProviderCandidateBlocker.RedactionReviewMissing,
        SkaldVaultV1ProviderCandidateBlocker.ClearWipeReviewMissing,
        SkaldVaultV1ProviderCandidateBlocker.MigrationCorruptionReviewMissing,
        SkaldVaultV1ProviderCandidateBlocker.ProductionProviderAcceptanceIncomplete,
        SkaldVaultV1ProviderCandidateBlocker.ProviderSelectionRegistryReviewMissing,
        SkaldVaultV1ProviderCandidateBlocker.LaterProductionProviderSelectableChangeRequired,
        SkaldVaultV1ProviderCandidateBlocker.SourceSetPlacementReviewMissing,
        SkaldVaultV1ProviderCandidateBlocker.DependencyReviewMissing,
    )

    private val baseStatuses: Set<SkaldVaultV1ProviderCandidatePackagingStatus> = setOf(
        SkaldVaultV1ProviderCandidatePackagingStatus.BoundaryModeled,
        SkaldVaultV1ProviderCandidatePackagingStatus.StillDisabled,
        SkaldVaultV1ProviderCandidatePackagingStatus.EvidenceOnly,
        SkaldVaultV1ProviderCandidatePackagingStatus.FutureReviewRequired,
        SkaldVaultV1ProviderCandidatePackagingStatus.NonSelectable,
        SkaldVaultV1ProviderCandidatePackagingStatus.NonExecutable,
        SkaldVaultV1ProviderCandidatePackagingStatus.OperationUnauthorized,
        SkaldVaultV1ProviderCandidatePackagingStatus.WarningOnlyCannotAuthorize,
        SkaldVaultV1ProviderCandidatePackagingStatus.UserConsentCannotOverride,
        SkaldVaultV1ProviderCandidatePackagingStatus.TestOnlyRejectedForProduction,
    )

    private val baseBlockers: Set<SkaldVaultV1ProviderCandidateBlocker> = setOf(
        SkaldVaultV1ProviderCandidateBlocker.WarningOnlyEvidenceCannotAuthorize,
        SkaldVaultV1ProviderCandidateBlocker.UserConsentCannotOverride,
        SkaldVaultV1ProviderCandidateBlocker.TestOnlyEvidenceRejectedForProduction,
        SkaldVaultV1ProviderCandidateBlocker.MainnetDisabled,
    )
}
