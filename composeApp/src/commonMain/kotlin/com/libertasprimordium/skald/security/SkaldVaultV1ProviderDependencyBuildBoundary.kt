package com.libertasprimordium.skald.security

interface SkaldVaultV1ProviderDependencyBuildBoundary {
    fun evaluate(
        request: SkaldVaultV1ProviderDependencyBuildRequest,
    ): SkaldVaultV1ProviderDependencyBuildResult<SkaldVaultV1ProviderDependencyBuildEvidence>
}

enum class SkaldVaultV1ProviderDependencyBuildSource(val label: String) {
    CurrentBuildEvidence("current provider dependency build evidence"),
    CandidateAudit("provider dependency candidate audit"),
    SourceSetPlacementAudit("provider dependency source-set placement audit"),
    PromotionGateAudit("provider dependency promotion gate audit"),
}

enum class SkaldVaultV1ProviderDependencyBuildStatus(val label: String) {
    BoundaryModeled("provider dependency build boundary modeled"),
    StillDisabled("still disabled"),
    EvidenceOnly("evidence only"),
    DeclaredResolvableBuildEvidenceOnly("declared/resolvable build evidence only"),
    DependencyActivationDeferred("dependency activation deferred"),
    NonExecutable("non-executable"),
    NonSelectable("non-selectable"),
    OperationUnauthorized("provider operation unauthorized"),
    TestOnlyRejectedForProduction("test-only evidence rejected for production"),
    WarningOnlyCannotAuthorize("warning-only evidence cannot authorize"),
    UserConsentCannotOverride("user consent cannot override"),
    UnsupportedFailClosed("unsupported fail closed"),
}

enum class SkaldVaultV1ProviderDependencyCandidateFamily(val label: String) {
    TinkJvmCandidate("Tink JVM candidate"),
    BouncyCastleJvmCandidate("Bouncy Castle JVM candidate"),
    AndroidPlatformWrapperCandidate("Android platform wrapper candidate"),
    OsCsprngPlatformCandidate("OS CSPRNG/platform candidate"),
    TestOnlyDeterministicCandidate("test-only deterministic candidate"),
    UnknownCandidate("unknown candidate"),
    UnsupportedCandidate("unsupported candidate"),
}

enum class SkaldVaultV1ProviderDependencyDeclarationStatus(val label: String) {
    Absent("absent"),
    DeclaredButUnused("declared but unused"),
    VersionCatalogDeclaredOnly("version catalog declared only"),
    SourceSetDependencyDeclaredOnly("source-set dependency declared only"),
    TestOnlyDeclaredOnly("test-only declared only"),
    BlockedBySourceSetIncompatibility("blocked by source-set incompatibility"),
    BlockedByUnclearProviderDecision("blocked by unclear provider decision"),
    BlockedByDependencyReview("blocked by dependency review"),
    Forbidden("forbidden"),
    Unknown("unknown"),
}

enum class SkaldVaultV1ProviderDependencySourceSetPlacement(val label: String) {
    CommonModelEvidenceOnly("common model/evidence only"),
    CommonProductionRuntimeImportsForbidden("common production provider runtime imports forbidden"),
    AndroidPlatformDependencyDeclaredOnly("Android platform dependency declared only"),
    DesktopPlatformDependencyDeclaredOnly("desktop platform dependency declared only"),
    AndroidProductionExecutionForbidden("Android production execution forbidden"),
    DesktopProductionExecutionForbidden("desktop production execution forbidden"),
    TestCompileEvidenceFutureReviewedOnly("test compile evidence future-reviewed only"),
    NoNewSourceSetAdded("no new source set added"),
}

enum class SkaldVaultV1ProviderDependencyBuildGate(val label: String) {
    BuildSpikeModeled("provider dependency build spike modeled"),
    DependencyDecisionReviewed("dependency decision reviewed"),
    SourceSetScopeReviewed("source-set scope reviewed"),
    ProviderCandidatePackagingApproved("provider candidate packaging approved"),
    ProviderImplementationAddedLater("provider implementation added in a later branch"),
    ProviderOperationAuthorizationApproved("provider operation authorization approved"),
    RuntimeRandomnessAuthorizationApproved("runtime randomness authorization approved"),
    KdfCalibrationAuthorizationApproved("KDF calibration authorization approved"),
    SecureStorageAuthorizationApproved("secure-storage authorization approved"),
    CreationAuthorizationApproved("creation authorization approved"),
    UnlockAuthorizationApproved("unlock authorization approved"),
    ProviderKatContractApproved("provider KAT contract approved"),
    AuthorizationReadinessMatrixAllowsPromotion("authorization/readiness matrix allows promotion"),
    ProviderSelectionRegistryReviewed("provider selection registry reviewed"),
    LaterProductionProviderSelectableChangeRequired("later productionProviderSelectable change required"),
    MainnetReleaseReviewRequired("mainnet release review required"),
}

enum class SkaldVaultV1ProviderDependencyBuildBlocker(val label: String) {
    ExistingSplitStackDeclarationsObserved("existing split-stack declarations observed"),
    NoNewSingleDependencyAddedThisBranch("no new single dependency added this branch"),
    ProviderDecisionIsSplitStackNotSingleFamily("provider decision is split-stack, not one single family"),
    DependencyActivationDeferred("dependency activation deferred"),
    ProviderImplementationMissing("provider implementation missing"),
    ProviderFactoryMissing("provider factory missing"),
    ProviderRuntimeNotInstantiable("provider runtime not instantiable"),
    ProviderRegistryUnchanged("provider registry unchanged"),
    DisabledProviderSelection("disabled provider selection"),
    ProductionProviderSelectableFalse("productionProviderSelectable is false"),
    ProviderOperationAuthorizationBlocked("provider operation authorization blocked"),
    ProviderKatExecutionBlocked("provider KAT execution blocked"),
    RuntimeRandomnessAuthorizationBlocked("runtime randomness authorization blocked"),
    KdfCalibrationAuthorizationBlocked("KDF calibration authorization blocked"),
    SecureStorageAuthorizationBlocked("secure-storage authorization blocked"),
    CreationAuthorizationBlocked("creation authorization blocked"),
    UnlockAuthorizationBlocked("unlock authorization blocked"),
    AuthorizationReadinessMatrixBlocksPromotion("authorization/readiness matrix blocks promotion"),
    WarningOnlyEvidenceCannotAuthorize("warning-only evidence cannot authorize"),
    UserConsentCannotOverride("user consent cannot override"),
    TestOnlyEvidenceRejectedForProduction("test-only evidence rejected for production"),
    MainnetDisabled("mainnet disabled"),
    UnknownCandidateRejected("unknown candidate rejected"),
    UnsupportedCandidateRejected("unsupported candidate rejected"),
}

enum class SkaldVaultV1ProviderDependencyRedactionClass(val label: String) {
    PolicyIdsOnly("policy ids only"),
    CandidateFamilyOnly("candidate family only"),
    SourceSetCategoryOnly("source-set category only"),
    BoundaryAndBlockerClassesOnly("boundary and blocker classes only"),
    NoDiagnosticPayload("no diagnostic payload"),
}

data class SkaldVaultV1ProviderDependencyBuildCapability(
    val preExistingProviderDependencyDeclarationsObserved: Boolean,
    val newDependencyDeclaredThisBranch: Boolean,
    val singleCandidateDependencyAddedThisBranch: Boolean,
    val providerDependencyDeclaredForBuildOnly: Boolean,
    val providerDependencyDeclaredForRuntime: Boolean,
    val providerDependencyActivated: Boolean,
    val providerDependencyImportedInProduction: Boolean,
    val providerDependencyImportedInTests: Boolean,
    val providerImplementationAdded: Boolean,
    val providerFactoryAdded: Boolean,
    val providerRuntimeInstantiable: Boolean,
    val providerSelectable: Boolean,
    val productionProviderSelectable: Boolean,
    val providerOperationAuthorized: Boolean,
    val providerKatExecutionAvailable: Boolean,
    val providerCryptoAvailable: Boolean,
    val runtimeRandomnessAvailable: Boolean,
    val kdfExecutionAvailable: Boolean,
    val aeadExecutionAvailable: Boolean,
    val hkdfHmacExecutionAvailable: Boolean,
    val keyWrappingAvailable: Boolean,
    val vaultCreationAvailable: Boolean,
    val vaultUnlockAvailable: Boolean,
    val vaultPersistenceAvailable: Boolean,
    val mainnetAvailable: Boolean,
) {
    companion object {
        val CurrentBuildOnlyEvidence = SkaldVaultV1ProviderDependencyBuildCapability(
            preExistingProviderDependencyDeclarationsObserved = true,
            newDependencyDeclaredThisBranch = false,
            singleCandidateDependencyAddedThisBranch = false,
            providerDependencyDeclaredForBuildOnly = true,
            providerDependencyDeclaredForRuntime = false,
            providerDependencyActivated = false,
            providerDependencyImportedInProduction = false,
            providerDependencyImportedInTests = false,
            providerImplementationAdded = false,
            providerFactoryAdded = false,
            providerRuntimeInstantiable = false,
            providerSelectable = false,
            productionProviderSelectable = false,
            providerOperationAuthorized = false,
            providerKatExecutionAvailable = false,
            providerCryptoAvailable = false,
            runtimeRandomnessAvailable = false,
            kdfExecutionAvailable = false,
            aeadExecutionAvailable = false,
            hkdfHmacExecutionAvailable = false,
            keyWrappingAvailable = false,
            vaultCreationAvailable = false,
            vaultUnlockAvailable = false,
            vaultPersistenceAvailable = false,
            mainnetAvailable = false,
        )
    }
}

class SkaldVaultV1ProviderDependencyBuildPolicyToken private constructor(
    val policyId: String,
    val candidateFamily: SkaldVaultV1ProviderDependencyCandidateFamily?,
) {
    val containsProviderHandle: Boolean = false
    val containsCryptoObject: Boolean = false
    val containsByteMaterial: Boolean = false
    val containsPathOrRootText: Boolean = false
    val containsStorageIdentifier: Boolean = false
    val containsSecretMaterial: Boolean = false

    override fun toString(): String =
        "SkaldVaultV1ProviderDependencyBuildPolicyToken(" +
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
            candidateFamily: SkaldVaultV1ProviderDependencyCandidateFamily?,
        ): SkaldVaultV1ProviderDependencyBuildPolicyToken {
            return SkaldVaultV1ProviderDependencyBuildPolicyToken(policyId, candidateFamily)
        }
    }
}

class SkaldVaultV1ProviderDependencyBuildRequest private constructor(
    val source: SkaldVaultV1ProviderDependencyBuildSource,
    val candidateFamily: SkaldVaultV1ProviderDependencyCandidateFamily?,
    val declarationStatus: SkaldVaultV1ProviderDependencyDeclarationStatus?,
    val sourceSetPlacement: SkaldVaultV1ProviderDependencySourceSetPlacement?,
    private val providerCandidatePackagingEvidence: SkaldVaultV1ProviderCandidatePackagingEvidence?,
    private val authorizationReadinessMatrixEvidence: SkaldVaultV1AuthorizationReadinessMatrixEvidence?,
    private val dependencyProbeResult: VaultCryptoDependencyProbeResult?,
    private val providerSelectionResult: VaultCryptoProviderSelectionResult?,
) {
    val providerCandidatePackagingEvidenceSupplied: Boolean
        get() = providerCandidatePackagingEvidence != null

    val authorizationReadinessMatrixEvidenceSupplied: Boolean
        get() = authorizationReadinessMatrixEvidence != null

    val dependencyProbeEvidenceSupplied: Boolean
        get() = dependencyProbeResult != null

    val providerSelectionEvidenceSupplied: Boolean
        get() = providerSelectionResult != null

    override fun toString(): String =
        "SkaldVaultV1ProviderDependencyBuildRequest(" +
            "source=$source, " +
            "candidateFamily=$candidateFamily, " +
            "declarationStatus=$declarationStatus, " +
            "sourceSetPlacement=$sourceSetPlacement, " +
            "providerCandidatePackaging=<redacted>, " +
            "authorizationReadinessMatrix=<redacted>, " +
            "dependencyProbe=<redacted>, " +
            "providerSelection=<redacted>" +
            ")"

    companion object {
        fun currentEvidence(
            candidateFamily: SkaldVaultV1ProviderDependencyCandidateFamily? = null,
            declarationStatus: SkaldVaultV1ProviderDependencyDeclarationStatus? = null,
            sourceSetPlacement: SkaldVaultV1ProviderDependencySourceSetPlacement? = null,
            providerCandidatePackagingEvidence: SkaldVaultV1ProviderCandidatePackagingEvidence? = null,
            authorizationReadinessMatrixEvidence: SkaldVaultV1AuthorizationReadinessMatrixEvidence? = null,
            dependencyProbeResult: VaultCryptoDependencyProbeResult? = null,
            providerSelectionResult: VaultCryptoProviderSelectionResult? = null,
        ): SkaldVaultV1ProviderDependencyBuildRequest =
            SkaldVaultV1ProviderDependencyBuildRequest(
                source = SkaldVaultV1ProviderDependencyBuildSource.CurrentBuildEvidence,
                candidateFamily = candidateFamily,
                declarationStatus = declarationStatus,
                sourceSetPlacement = sourceSetPlacement,
                providerCandidatePackagingEvidence = providerCandidatePackagingEvidence,
                authorizationReadinessMatrixEvidence = authorizationReadinessMatrixEvidence,
                dependencyProbeResult = dependencyProbeResult,
                providerSelectionResult = providerSelectionResult,
            )

        fun forCandidate(
            candidateFamily: SkaldVaultV1ProviderDependencyCandidateFamily,
        ): SkaldVaultV1ProviderDependencyBuildRequest =
            SkaldVaultV1ProviderDependencyBuildRequest(
                source = SkaldVaultV1ProviderDependencyBuildSource.CandidateAudit,
                candidateFamily = candidateFamily,
                declarationStatus = null,
                sourceSetPlacement = null,
                providerCandidatePackagingEvidence = null,
                authorizationReadinessMatrixEvidence = null,
                dependencyProbeResult = null,
                providerSelectionResult = null,
            )

        fun sourceSetPlacementAudit(): SkaldVaultV1ProviderDependencyBuildRequest =
            SkaldVaultV1ProviderDependencyBuildRequest(
                source = SkaldVaultV1ProviderDependencyBuildSource.SourceSetPlacementAudit,
                candidateFamily = null,
                declarationStatus = null,
                sourceSetPlacement = SkaldVaultV1ProviderDependencySourceSetPlacement.CommonModelEvidenceOnly,
                providerCandidatePackagingEvidence = null,
                authorizationReadinessMatrixEvidence = null,
                dependencyProbeResult = null,
                providerSelectionResult = null,
            )

        fun promotionGateAudit(): SkaldVaultV1ProviderDependencyBuildRequest =
            SkaldVaultV1ProviderDependencyBuildRequest(
                source = SkaldVaultV1ProviderDependencyBuildSource.PromotionGateAudit,
                candidateFamily = null,
                declarationStatus = null,
                sourceSetPlacement = null,
                providerCandidatePackagingEvidence = null,
                authorizationReadinessMatrixEvidence = null,
                dependencyProbeResult = null,
                providerSelectionResult = null,
            )
    }
}

data class SkaldVaultV1ProviderDependencyBuildRow(
    val family: SkaldVaultV1ProviderDependencyCandidateFamily,
    val declarationStatus: SkaldVaultV1ProviderDependencyDeclarationStatus,
    val sourceSetPlacements: Set<SkaldVaultV1ProviderDependencySourceSetPlacement>,
    val statuses: Set<SkaldVaultV1ProviderDependencyBuildStatus>,
    val blockers: Set<SkaldVaultV1ProviderDependencyBuildBlocker>,
    val requiredGates: Set<SkaldVaultV1ProviderDependencyBuildGate>,
    val buildEvidenceOnly: Boolean,
    val declaredByThisBranch: Boolean,
    val runtimeDeclaredByThisBranch: Boolean,
    val importedInProduction: Boolean,
    val importedInTests: Boolean,
    val executable: Boolean,
    val selectable: Boolean,
    val productionAuthorized: Boolean,
    val redactionClass: SkaldVaultV1ProviderDependencyRedactionClass,
)

data class SkaldVaultV1ProviderDependencyBuildSummary(
    val policyId: String,
    val policyVersion: Int,
    val statuses: Set<SkaldVaultV1ProviderDependencyBuildStatus>,
    val candidateFamilies: Set<SkaldVaultV1ProviderDependencyCandidateFamily>,
    val declarationStatuses: Set<SkaldVaultV1ProviderDependencyDeclarationStatus>,
    val sourceSetPlacements: Set<SkaldVaultV1ProviderDependencySourceSetPlacement>,
    val requiredGates: Set<SkaldVaultV1ProviderDependencyBuildGate>,
    val blockers: Set<SkaldVaultV1ProviderDependencyBuildBlocker>,
    val capability: SkaldVaultV1ProviderDependencyBuildCapability,
    val stillDisabled: Boolean,
    val evidenceOnly: Boolean,
    val noRuntimeProviderDependencyAddedThisBranch: Boolean,
    val noCandidateSelectable: Boolean,
)

data class SkaldVaultV1ProviderDependencyBuildEvidence(
    val policyId: String,
    val policyVersion: Int,
    val source: SkaldVaultV1ProviderDependencyBuildSource,
    val status: SkaldVaultV1ProviderDependencyBuildStatus,
    val candidateRows: List<SkaldVaultV1ProviderDependencyBuildRow>,
    val declarationStatuses: Set<SkaldVaultV1ProviderDependencyDeclarationStatus>,
    val sourceSetPlacements: Set<SkaldVaultV1ProviderDependencySourceSetPlacement>,
    val requiredGates: Set<SkaldVaultV1ProviderDependencyBuildGate>,
    val blockers: Set<SkaldVaultV1ProviderDependencyBuildBlocker>,
    val capability: SkaldVaultV1ProviderDependencyBuildCapability,
    val policyTokenEvidence: SkaldVaultV1ProviderDependencyBuildPolicyToken,
    val providerCandidatePackagingEvidenceConsumed: Boolean,
    val authorizationReadinessMatrixEvidenceConsumed: Boolean,
    val dependencyProbeEvidenceConsumed: Boolean,
    val providerSelectionEvidenceConsumed: Boolean,
    val providerDependencyBuildSpikeModeled: Boolean = true,
    val providerDependencyBuildSpikeStillDisabled: Boolean = true,
    val providerDependencyBuildSpikeDoesNotImplementProvider: Boolean = true,
    val providerDependencyBuildSpikeDoesNotEnableProviderSelection: Boolean = true,
    val providerDependencyBuildSpikeDoesNotRunCrypto: Boolean = true,
    val providerDependencyBuildSpikeDoesNotRunKat: Boolean = true,
    val providerDependencyBuildSpikeDoesNotEnableCreation: Boolean = true,
    val providerDependencyBuildSpikeDoesNotEnableUnlock: Boolean = true,
    val providerDependencyBuildSpikeDoesNotEnablePersistence: Boolean = true,
    val providerDependencyDeclaredForBuildOnly: Boolean = true,
    val providerDependencyDeclaredForRuntime: Boolean = false,
    val providerDependencyActivated: Boolean = false,
    val providerDependencyImportedInProduction: Boolean = false,
    val providerDependencyImportedInTests: Boolean = false,
    val providerImplementationAdded: Boolean = false,
    val providerFactoryAdded: Boolean = false,
    val providerRuntimeInstantiable: Boolean = false,
    val providerSelectable: Boolean = false,
    val productionProviderSelectable: Boolean = false,
    val providerOperationAuthorized: Boolean = false,
    val providerKatExecutionAvailable: Boolean = false,
    val providerCryptoAvailable: Boolean = false,
    val runtimeRandomnessAvailable: Boolean = false,
    val kdfExecutionAvailable: Boolean = false,
    val aeadExecutionAvailable: Boolean = false,
    val hkdfHmacExecutionAvailable: Boolean = false,
    val keyWrappingAvailable: Boolean = false,
    val vaultCreationAvailable: Boolean = false,
    val vaultUnlockAvailable: Boolean = false,
    val vaultPersistenceAvailable: Boolean = false,
    val mainnetAvailable: Boolean = false,
) {
    override fun toString(): String =
        "SkaldVaultV1ProviderDependencyBuildEvidence(" +
            "policyId=$policyId, " +
            "policyVersion=$policyVersion, " +
            "source=$source, " +
            "status=$status, " +
            "candidateRows=${candidateRows.size}, " +
            "declarationStatuses=${declarationStatuses.size}, " +
            "sourceSetPlacements=${sourceSetPlacements.size}, " +
            "requiredGates=${requiredGates.size}, " +
            "blockers=${blockers.size}, " +
            "policyToken=<redacted>" +
            ")"
}

sealed class SkaldVaultV1ProviderDependencyBuildResult<out T> {
    data class Blocked<out T>(
        val value: T,
    ) : SkaldVaultV1ProviderDependencyBuildResult<T>() {
        override fun toString(): String =
            "SkaldVaultV1ProviderDependencyBuildResult.Blocked(value=<redacted-provider-dependency-build-evidence>)"
    }
}

object SkaldVaultV1ProviderDependencyBuildPolicy :
    SkaldVaultV1ProviderDependencyBuildBoundary {
    const val POLICY_ID = "skald-vault-v1-provider-dependency-build-boundary-v1"
    const val POLICY_VERSION = 1

    override fun evaluate(
        request: SkaldVaultV1ProviderDependencyBuildRequest,
    ): SkaldVaultV1ProviderDependencyBuildResult<SkaldVaultV1ProviderDependencyBuildEvidence> {
        val rows = rowsFor(request.candidateFamily)
        return SkaldVaultV1ProviderDependencyBuildResult.Blocked(
            SkaldVaultV1ProviderDependencyBuildEvidence(
                policyId = POLICY_ID,
                policyVersion = POLICY_VERSION,
                source = request.source,
                status = SkaldVaultV1ProviderDependencyBuildStatus.StillDisabled,
                candidateRows = rows,
                declarationStatuses = SkaldVaultV1ProviderDependencyDeclarationStatus.entries.toSet(),
                sourceSetPlacements = SkaldVaultV1ProviderDependencySourceSetPlacement.entries.toSet(),
                requiredGates = SkaldVaultV1ProviderDependencyBuildGate.entries.toSet(),
                blockers = rows.flatMap { it.blockers }.toSet() + baseBlockers,
                capability = SkaldVaultV1ProviderDependencyBuildCapability.CurrentBuildOnlyEvidence,
                policyTokenEvidence = SkaldVaultV1ProviderDependencyBuildPolicyToken.redacted(
                    policyId = POLICY_ID,
                    candidateFamily = request.candidateFamily,
                ),
                providerCandidatePackagingEvidenceConsumed =
                    request.providerCandidatePackagingEvidenceSupplied,
                authorizationReadinessMatrixEvidenceConsumed =
                    request.authorizationReadinessMatrixEvidenceSupplied,
                dependencyProbeEvidenceConsumed = request.dependencyProbeEvidenceSupplied,
                providerSelectionEvidenceConsumed = request.providerSelectionEvidenceSupplied,
            ),
        )
    }

    fun currentPolicySummary(): SkaldVaultV1ProviderDependencyBuildSummary =
        SkaldVaultV1ProviderDependencyBuildSummary(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            statuses = SkaldVaultV1ProviderDependencyBuildStatus.entries.toSet(),
            candidateFamilies = SkaldVaultV1ProviderDependencyCandidateFamily.entries.toSet(),
            declarationStatuses = SkaldVaultV1ProviderDependencyDeclarationStatus.entries.toSet(),
            sourceSetPlacements = SkaldVaultV1ProviderDependencySourceSetPlacement.entries.toSet(),
            requiredGates = SkaldVaultV1ProviderDependencyBuildGate.entries.toSet(),
            blockers = SkaldVaultV1ProviderDependencyBuildBlocker.entries.toSet(),
            capability = SkaldVaultV1ProviderDependencyBuildCapability.CurrentBuildOnlyEvidence,
            stillDisabled = true,
            evidenceOnly = true,
            noRuntimeProviderDependencyAddedThisBranch = true,
            noCandidateSelectable = true,
        )

    private fun rowsFor(
        candidateFamily: SkaldVaultV1ProviderDependencyCandidateFamily?,
    ): List<SkaldVaultV1ProviderDependencyBuildRow> {
        val rows = currentRows()
        return candidateFamily?.let { family -> rows.filter { it.family == family } } ?: rows
    }

    private fun currentRows(): List<SkaldVaultV1ProviderDependencyBuildRow> =
        listOf(
            row(
                family = SkaldVaultV1ProviderDependencyCandidateFamily.TinkJvmCandidate,
                declarationStatus =
                    SkaldVaultV1ProviderDependencyDeclarationStatus.SourceSetDependencyDeclaredOnly,
                sourceSetPlacements = setOf(
                    SkaldVaultV1ProviderDependencySourceSetPlacement.AndroidPlatformDependencyDeclaredOnly,
                    SkaldVaultV1ProviderDependencySourceSetPlacement.DesktopPlatformDependencyDeclaredOnly,
                    SkaldVaultV1ProviderDependencySourceSetPlacement.CommonProductionRuntimeImportsForbidden,
                ),
                blockers = commonRuntimeBlockers +
                    SkaldVaultV1ProviderDependencyBuildBlocker.ExistingSplitStackDeclarationsObserved +
                    SkaldVaultV1ProviderDependencyBuildBlocker.NoNewSingleDependencyAddedThisBranch +
                    SkaldVaultV1ProviderDependencyBuildBlocker.ProviderDecisionIsSplitStackNotSingleFamily,
            ),
            row(
                family = SkaldVaultV1ProviderDependencyCandidateFamily.BouncyCastleJvmCandidate,
                declarationStatus =
                    SkaldVaultV1ProviderDependencyDeclarationStatus.SourceSetDependencyDeclaredOnly,
                sourceSetPlacements = setOf(
                    SkaldVaultV1ProviderDependencySourceSetPlacement.AndroidPlatformDependencyDeclaredOnly,
                    SkaldVaultV1ProviderDependencySourceSetPlacement.DesktopPlatformDependencyDeclaredOnly,
                    SkaldVaultV1ProviderDependencySourceSetPlacement.CommonProductionRuntimeImportsForbidden,
                ),
                blockers = commonRuntimeBlockers +
                    SkaldVaultV1ProviderDependencyBuildBlocker.ExistingSplitStackDeclarationsObserved +
                    SkaldVaultV1ProviderDependencyBuildBlocker.NoNewSingleDependencyAddedThisBranch +
                    SkaldVaultV1ProviderDependencyBuildBlocker.ProviderDecisionIsSplitStackNotSingleFamily,
            ),
            row(
                family = SkaldVaultV1ProviderDependencyCandidateFamily.AndroidPlatformWrapperCandidate,
                declarationStatus = SkaldVaultV1ProviderDependencyDeclarationStatus.Absent,
                sourceSetPlacements = setOf(
                    SkaldVaultV1ProviderDependencySourceSetPlacement.CommonModelEvidenceOnly,
                    SkaldVaultV1ProviderDependencySourceSetPlacement.AndroidProductionExecutionForbidden,
                ),
                blockers = commonRuntimeBlockers +
                    SkaldVaultV1ProviderDependencyBuildBlocker.DependencyActivationDeferred,
            ),
            row(
                family = SkaldVaultV1ProviderDependencyCandidateFamily.OsCsprngPlatformCandidate,
                declarationStatus = SkaldVaultV1ProviderDependencyDeclarationStatus.Absent,
                sourceSetPlacements = setOf(
                    SkaldVaultV1ProviderDependencySourceSetPlacement.CommonModelEvidenceOnly,
                    SkaldVaultV1ProviderDependencySourceSetPlacement.DesktopProductionExecutionForbidden,
                    SkaldVaultV1ProviderDependencySourceSetPlacement.AndroidProductionExecutionForbidden,
                ),
                blockers = commonRuntimeBlockers +
                    SkaldVaultV1ProviderDependencyBuildBlocker.RuntimeRandomnessAuthorizationBlocked,
            ),
            row(
                family = SkaldVaultV1ProviderDependencyCandidateFamily.TestOnlyDeterministicCandidate,
                declarationStatus = SkaldVaultV1ProviderDependencyDeclarationStatus.TestOnlyDeclaredOnly,
                sourceSetPlacements = setOf(
                    SkaldVaultV1ProviderDependencySourceSetPlacement.TestCompileEvidenceFutureReviewedOnly,
                ),
                blockers = commonRuntimeBlockers +
                    SkaldVaultV1ProviderDependencyBuildBlocker.TestOnlyEvidenceRejectedForProduction,
            ),
            row(
                family = SkaldVaultV1ProviderDependencyCandidateFamily.UnknownCandidate,
                declarationStatus = SkaldVaultV1ProviderDependencyDeclarationStatus.Unknown,
                sourceSetPlacements = setOf(SkaldVaultV1ProviderDependencySourceSetPlacement.CommonModelEvidenceOnly),
                blockers = commonRuntimeBlockers +
                    SkaldVaultV1ProviderDependencyBuildBlocker.UnknownCandidateRejected,
                redactionClass = SkaldVaultV1ProviderDependencyRedactionClass.NoDiagnosticPayload,
            ),
            row(
                family = SkaldVaultV1ProviderDependencyCandidateFamily.UnsupportedCandidate,
                declarationStatus = SkaldVaultV1ProviderDependencyDeclarationStatus.Forbidden,
                sourceSetPlacements = setOf(SkaldVaultV1ProviderDependencySourceSetPlacement.CommonModelEvidenceOnly),
                blockers = commonRuntimeBlockers +
                    SkaldVaultV1ProviderDependencyBuildBlocker.UnsupportedCandidateRejected,
                redactionClass = SkaldVaultV1ProviderDependencyRedactionClass.NoDiagnosticPayload,
            ),
        )

    private fun row(
        family: SkaldVaultV1ProviderDependencyCandidateFamily,
        declarationStatus: SkaldVaultV1ProviderDependencyDeclarationStatus,
        sourceSetPlacements: Set<SkaldVaultV1ProviderDependencySourceSetPlacement>,
        blockers: Set<SkaldVaultV1ProviderDependencyBuildBlocker>,
        redactionClass: SkaldVaultV1ProviderDependencyRedactionClass =
            SkaldVaultV1ProviderDependencyRedactionClass.BoundaryAndBlockerClassesOnly,
    ): SkaldVaultV1ProviderDependencyBuildRow =
        SkaldVaultV1ProviderDependencyBuildRow(
            family = family,
            declarationStatus = declarationStatus,
            sourceSetPlacements = sourceSetPlacements +
                SkaldVaultV1ProviderDependencySourceSetPlacement.CommonProductionRuntimeImportsForbidden +
                SkaldVaultV1ProviderDependencySourceSetPlacement.NoNewSourceSetAdded,
            statuses = baseStatuses,
            blockers = blockers + baseBlockers,
            requiredGates = SkaldVaultV1ProviderDependencyBuildGate.entries.toSet(),
            buildEvidenceOnly = true,
            declaredByThisBranch = false,
            runtimeDeclaredByThisBranch = false,
            importedInProduction = false,
            importedInTests = false,
            executable = false,
            selectable = false,
            productionAuthorized = false,
            redactionClass = redactionClass,
        )

    private val baseStatuses: Set<SkaldVaultV1ProviderDependencyBuildStatus> = setOf(
        SkaldVaultV1ProviderDependencyBuildStatus.StillDisabled,
        SkaldVaultV1ProviderDependencyBuildStatus.EvidenceOnly,
        SkaldVaultV1ProviderDependencyBuildStatus.DeclaredResolvableBuildEvidenceOnly,
        SkaldVaultV1ProviderDependencyBuildStatus.DependencyActivationDeferred,
        SkaldVaultV1ProviderDependencyBuildStatus.NonExecutable,
        SkaldVaultV1ProviderDependencyBuildStatus.NonSelectable,
        SkaldVaultV1ProviderDependencyBuildStatus.OperationUnauthorized,
        SkaldVaultV1ProviderDependencyBuildStatus.WarningOnlyCannotAuthorize,
        SkaldVaultV1ProviderDependencyBuildStatus.UserConsentCannotOverride,
        SkaldVaultV1ProviderDependencyBuildStatus.TestOnlyRejectedForProduction,
    )

    private val commonRuntimeBlockers: Set<SkaldVaultV1ProviderDependencyBuildBlocker> = setOf(
        SkaldVaultV1ProviderDependencyBuildBlocker.DependencyActivationDeferred,
        SkaldVaultV1ProviderDependencyBuildBlocker.ProviderImplementationMissing,
        SkaldVaultV1ProviderDependencyBuildBlocker.ProviderFactoryMissing,
        SkaldVaultV1ProviderDependencyBuildBlocker.ProviderRuntimeNotInstantiable,
        SkaldVaultV1ProviderDependencyBuildBlocker.ProviderRegistryUnchanged,
        SkaldVaultV1ProviderDependencyBuildBlocker.DisabledProviderSelection,
        SkaldVaultV1ProviderDependencyBuildBlocker.ProductionProviderSelectableFalse,
        SkaldVaultV1ProviderDependencyBuildBlocker.ProviderOperationAuthorizationBlocked,
        SkaldVaultV1ProviderDependencyBuildBlocker.ProviderKatExecutionBlocked,
        SkaldVaultV1ProviderDependencyBuildBlocker.RuntimeRandomnessAuthorizationBlocked,
        SkaldVaultV1ProviderDependencyBuildBlocker.KdfCalibrationAuthorizationBlocked,
        SkaldVaultV1ProviderDependencyBuildBlocker.SecureStorageAuthorizationBlocked,
        SkaldVaultV1ProviderDependencyBuildBlocker.CreationAuthorizationBlocked,
        SkaldVaultV1ProviderDependencyBuildBlocker.UnlockAuthorizationBlocked,
        SkaldVaultV1ProviderDependencyBuildBlocker.AuthorizationReadinessMatrixBlocksPromotion,
        SkaldVaultV1ProviderDependencyBuildBlocker.MainnetDisabled,
    )

    private val baseBlockers: Set<SkaldVaultV1ProviderDependencyBuildBlocker> = setOf(
        SkaldVaultV1ProviderDependencyBuildBlocker.WarningOnlyEvidenceCannotAuthorize,
        SkaldVaultV1ProviderDependencyBuildBlocker.UserConsentCannotOverride,
        SkaldVaultV1ProviderDependencyBuildBlocker.TestOnlyEvidenceRejectedForProduction,
        SkaldVaultV1ProviderDependencyBuildBlocker.MainnetDisabled,
    )
}
