package com.libertasprimordium.skald.security

interface SkaldVaultV1ProviderFactoryIsolationBoundary {
    fun evaluate(
        request: SkaldVaultV1ProviderFactoryIsolationRequest,
    ): SkaldVaultV1ProviderFactoryIsolationResult<SkaldVaultV1ProviderFactoryIsolationEvidence>
}

enum class SkaldVaultV1ProviderFactoryIsolationSource(val label: String) {
    CurrentTypedEvidence("current provider factory isolation evidence"),
    FactoryTopicAudit("provider factory isolation topic audit"),
    FactoryRiskAudit("provider factory isolation risk audit"),
    RequiredPropertyAudit("provider factory isolation required-property audit"),
    EvidenceInteractionAudit("provider factory isolation evidence interaction audit"),
}

enum class SkaldVaultV1ProviderFactoryIsolationStatus(val label: String) {
    BoundaryModeled("provider factory isolation boundary modeled"),
    StillDisabled("still disabled"),
    EvidenceOnly("evidence only"),
    NoFactoryAvailable("no provider factory available"),
    FactoryIsolated("provider factory isolated"),
    ConstructionExcluded("provider construction excluded"),
    RuntimeConstructionBlocked("provider runtime construction blocked"),
    ProviderSelectionLockedToDisabledProvider("provider selection locked to disabled provider"),
    OperationExecutionBlocked("provider operation execution blocked"),
    WarningOnlyCannotAuthorize("warning-only evidence cannot authorize"),
    UserConsentCannotOverride("user consent cannot override"),
    TestOnlyRejectedForProduction("test-only evidence rejected for production"),
    MainnetBlocked("mainnet blocked"),
}

enum class SkaldVaultV1ProviderFactoryIsolationTopic(val label: String) {
    ActiveProviderFactorySurface("active provider factory surface"),
    DisabledProviderConstructionSurface("disabled provider construction surface"),
    NonDisabledProviderConstructionExcluded("non-disabled provider construction excluded"),
    NonSelectableSkeletonConstructionExcluded("non-selectable skeleton construction excluded"),
    CandidateProviderConstructionExcluded("candidate provider construction excluded"),
    DependencyBuildEvidenceConstructionExcluded("dependency/build evidence construction excluded"),
    CandidatePackagingConstructionExcluded("candidate-packaging construction excluded"),
    InterfaceAuditConstructionExcluded("interface-audit construction excluded"),
    PromotionBlockerConstructionExcluded("promotion-blocker construction excluded"),
    AuthorizationReadinessMatrixConstructionExcluded("authorization/readiness matrix construction excluded"),
    ProviderRegistryConstructionExcluded("provider registry construction excluded"),
    ProviderOperationAuthorizationConstructionExcluded("provider operation authorization construction excluded"),
    RuntimeRandomnessConstructionExcluded("runtime-randomness construction excluded"),
    KdfCalibrationConstructionExcluded("KDF-calibration construction excluded"),
    SecureStorageConstructionExcluded("secure-storage construction excluded"),
    CreationUnlockConstructionExcluded("creation/unlock construction excluded"),
    TestOnlyFactoryEvidenceExcludedFromProductionConstruction(
        "test-only factory evidence excluded from production construction",
    ),
    WarningOnlyEvidenceExcludedFromConstruction("warning-only evidence excluded from construction"),
    UserConsentEvidenceExcludedFromConstruction("user-consent evidence excluded from construction"),
    ReleaseMainnetEvidenceFutureExplicitReviewOnly("release/mainnet evidence requires future explicit review"),
}

enum class SkaldVaultV1ProviderFactoryIsolationRisk(val label: String) {
    FactoryCreatesNonDisabledProvider("factory creates non-disabled provider"),
    FactoryCreatesSkeletonProvider("factory creates skeleton provider"),
    FactoryCreatesCandidateProvider("factory creates candidate provider"),
    FactoryTreatsDependencyBuildEvidenceAsConstructionEvidence(
        "factory treats dependency/build evidence as construction evidence",
    ),
    FactoryTreatsPackagingEvidenceAsConstructionEvidence("factory treats packaging evidence as construction evidence"),
    FactoryTreatsInterfaceAuditEvidenceAsConstructionEvidence(
        "factory treats interface audit evidence as construction evidence",
    ),
    FactoryTreatsPromotionBlockerEvidenceAsConstructionEvidence(
        "factory treats promotion blocker evidence as construction evidence",
    ),
    FactoryTreatsMatrixEvidenceAsConstructionEvidence("factory treats matrix evidence as construction evidence"),
    FactoryAcceptsProviderHandles("factory accepts provider handles"),
    FactoryAcceptsCryptoObjects("factory accepts crypto objects"),
    FactoryAcceptsByteMaterial("factory accepts byte material"),
    FactoryAcceptsPlatformCryptoApis("factory accepts platform crypto APIs"),
    FactoryImportsTinkBouncyJavaxCrypto("factory imports Tink/Bouncy/Javax crypto"),
    FactoryCallsSecureRandom("factory calls SecureRandom"),
    FactoryRunsKats("factory runs KATs"),
    FactoryCallsProviderOperations("factory calls provider operations"),
    FactoryReachesRandomnessKdfAead("factory reaches randomness/KDF/AEAD"),
    FactoryWrapsKeys("factory wraps keys"),
    FactoryEnablesVaultCreationUnlockPersistence("factory enables vault creation/unlock/persistence"),
    FactoryEnablesMainnet("factory enables mainnet"),
}

enum class SkaldVaultV1ProviderFactoryIsolationRequiredProperty(val label: String) {
    NoNonDisabledProviderFactoryExists("no non-disabled provider factory exists"),
    NoNonDisabledProviderConstructorExists("no non-disabled provider constructor exists"),
    NoProviderFactoryReachableFromProviderSelection("no provider factory is reachable from provider selection"),
    NoProviderFactoryReachableFromRegistryIsolation("no provider factory is reachable from registry isolation"),
    NoProviderFactoryReachableFromSkeletonEvidence("no provider factory is reachable from skeleton evidence"),
    NoProviderFactoryReachableFromCandidatePackagingEvidence(
        "no provider factory is reachable from candidate packaging evidence",
    ),
    NoProviderFactoryReachableFromDependencyBuildEvidence(
        "no provider factory is reachable from dependency/build evidence",
    ),
    NoProviderFactoryExposesProviderHandles("no provider factory exposes provider handles"),
    NoProviderFactoryExposesCryptoObjects("no provider factory exposes crypto objects"),
    NoProviderFactoryAcceptsByteMaterial("no provider factory accepts byte material"),
    NoProviderFactoryHasPlatformCryptoImports("no provider factory has platform crypto imports"),
    NoProviderFactoryCanExecuteProviderOperations("no provider factory can execute provider operations"),
    NoProviderFactoryCanRunKats("no provider factory can run KATs"),
    NoProviderFactoryCanCallRandomness("no provider factory can call randomness"),
    NoProviderFactoryCanRunKdfAeadHkdfHmac("no provider factory can run KDF/AEAD/HKDF/HMAC"),
    NoProviderFactoryCanPromoteDependencyBuildEvidence(
        "no provider factory can promote dependency/build evidence",
    ),
    NoProviderFactoryCanPromotePackagingSkeletonAuditMatrixEvidence(
        "no provider factory can promote packaging/skeleton/audit/matrix evidence",
    ),
    WarningUserConsentTestOnlyCannotOverride("warning/user-consent/test-only evidence cannot override"),
    ProviderSelectionDisabledProviderOnly("provider selection remains disabled-provider-only"),
    ProductionProviderSelectableFalse("productionProviderSelectable remains false"),
    MainnetDisabled("mainnet remains disabled"),
}

enum class SkaldVaultV1ProviderFactoryIsolationBlocker(val label: String) {
    FactoryIsolationBoundaryStillDisabled("factory isolation boundary still disabled"),
    ModelEvidenceOnly("model evidence only"),
    NoNonDisabledProviderFactory("no non-disabled provider factory"),
    NoNonDisabledProviderConstructor("no non-disabled provider constructor"),
    ProviderSelectionLockedToDisabledProvider("provider selection locked to disabled provider"),
    ProductionProviderSelectableFalse("productionProviderSelectable is false"),
    ProviderRegistryIsolationBlocked("provider registry isolation blocked"),
    NonSelectableProviderSkeletonStillDisabled("non-selectable provider skeleton still disabled"),
    SkeletonConstructionExcluded("skeleton construction excluded"),
    CandidateConstructionExcluded("candidate construction excluded"),
    DependencyBuildConstructionExcluded("dependency/build construction excluded"),
    CandidatePackagingConstructionExcluded("candidate packaging construction excluded"),
    ProviderInterfaceAuditEvidenceOnly("provider interface audit evidence only"),
    ProviderSelectionPromotionBlocked("provider selection promotion blocked"),
    AuthorizationReadinessMatrixBlocked("authorization/readiness matrix blocked"),
    ProviderOperationAuthorizationBlocked("provider operation authorization blocked"),
    RuntimeRandomnessAuthorizationBlocked("runtime randomness authorization blocked"),
    KdfCalibrationAuthorizationBlocked("KDF calibration authorization blocked"),
    SecureStorageAuthorizationBlocked("secure-storage authorization blocked"),
    CreationAuthorizationBlocked("creation authorization blocked"),
    UnlockAuthorizationBlocked("unlock authorization blocked"),
    ProviderAcceptanceMissing("provider acceptance missing"),
    DependencyProbeInsufficient("dependency probe insufficient"),
    FactoryObjectsRejected("factory objects rejected"),
    ProviderHandlesRejected("provider handles rejected"),
    CryptoObjectsRejected("crypto objects rejected"),
    ByteMaterialRejected("byte material rejected"),
    SecretMaterialRejected("secret material rejected"),
    PlatformCryptoImportsForbidden("platform crypto imports forbidden"),
    TinkBouncyJavaxImportsForbidden("Tink/Bouncy/Javax imports forbidden"),
    SecureRandomForbidden("SecureRandom forbidden"),
    ProviderOperationExecutionForbidden("provider operation execution forbidden"),
    KatExecutionUnavailable("KAT execution unavailable"),
    KeyWrappingUnavailable("key wrapping unavailable"),
    WarningOnlyEvidenceCannotAuthorize("warning-only evidence cannot authorize"),
    UserConsentCannotOverride("user consent cannot override"),
    TestOnlyEvidenceCannotAuthorizeProduction("test-only evidence cannot authorize production"),
    ReleaseReviewMissing("release review missing"),
    MainnetDisabled("mainnet disabled"),
}

enum class SkaldVaultV1ProviderFactoryIsolationRedactionClass(val label: String) {
    PolicyIdsOnly("policy ids only"),
    FactoryTopicOnly("factory topic only"),
    RiskCategoryOnly("risk category only"),
    RequiredPropertyOnly("required property only"),
    BoundaryAndBlockerClassesOnly("boundary and blocker classes only"),
    NoDiagnosticPayload("no diagnostic payload"),
}

data class SkaldVaultV1ProviderFactoryIsolationCapability(
    val providerFactoryIsolationModeled: Boolean,
    val providerFactoryExistsForNonDisabledProvider: Boolean,
    val providerFactoryReachableFromSelection: Boolean,
    val providerFactoryReachableFromRegistry: Boolean,
    val providerFactoryCanConstructSkeleton: Boolean,
    val providerFactoryCanConstructCandidate: Boolean,
    val providerFactoryCanConstructProviderRuntime: Boolean,
    val providerFactoryCanExposeProviderHandle: Boolean,
    val providerFactoryCanExposeCryptoObject: Boolean,
    val providerFactoryAcceptsByteMaterial: Boolean,
    val providerFactoryCanExecuteProviderOperations: Boolean,
    val providerFactoryCanRunKat: Boolean,
    val providerFactoryCanUseRandomness: Boolean,
    val providerFactoryCanRunKdf: Boolean,
    val providerFactoryCanRunAead: Boolean,
    val providerFactoryCanWrapKeys: Boolean,
    val providerFactoryCanCreateVault: Boolean,
    val providerFactoryCanUnlockVault: Boolean,
    val providerFactoryCanPersistVault: Boolean,
    val providerFactoryAdded: Boolean,
    val providerRegistryEnabled: Boolean,
    val providerRuntimeInstantiable: Boolean,
    val providerSelectable: Boolean,
    val productionProviderSelectable: Boolean,
    val providerOperationAuthorized: Boolean,
    val providerKatExecutionAvailable: Boolean,
    val runtimeRandomnessAvailable: Boolean,
    val kdfExecutionAvailable: Boolean,
    val aeadExecutionAvailable: Boolean,
    val vaultCreationAvailable: Boolean,
    val vaultUnlockAvailable: Boolean,
    val vaultPersistenceAvailable: Boolean,
    val mainnetAvailable: Boolean,
) {
    companion object {
        val StillDisabled = SkaldVaultV1ProviderFactoryIsolationCapability(
            providerFactoryIsolationModeled = true,
            providerFactoryExistsForNonDisabledProvider = false,
            providerFactoryReachableFromSelection = false,
            providerFactoryReachableFromRegistry = false,
            providerFactoryCanConstructSkeleton = false,
            providerFactoryCanConstructCandidate = false,
            providerFactoryCanConstructProviderRuntime = false,
            providerFactoryCanExposeProviderHandle = false,
            providerFactoryCanExposeCryptoObject = false,
            providerFactoryAcceptsByteMaterial = false,
            providerFactoryCanExecuteProviderOperations = false,
            providerFactoryCanRunKat = false,
            providerFactoryCanUseRandomness = false,
            providerFactoryCanRunKdf = false,
            providerFactoryCanRunAead = false,
            providerFactoryCanWrapKeys = false,
            providerFactoryCanCreateVault = false,
            providerFactoryCanUnlockVault = false,
            providerFactoryCanPersistVault = false,
            providerFactoryAdded = false,
            providerRegistryEnabled = false,
            providerRuntimeInstantiable = false,
            providerSelectable = false,
            productionProviderSelectable = false,
            providerOperationAuthorized = false,
            providerKatExecutionAvailable = false,
            runtimeRandomnessAvailable = false,
            kdfExecutionAvailable = false,
            aeadExecutionAvailable = false,
            vaultCreationAvailable = false,
            vaultUnlockAvailable = false,
            vaultPersistenceAvailable = false,
            mainnetAvailable = false,
        )
    }
}

class SkaldVaultV1ProviderFactoryIsolationPolicyToken private constructor(
    val policyId: String,
    val topic: SkaldVaultV1ProviderFactoryIsolationTopic?,
    val risk: SkaldVaultV1ProviderFactoryIsolationRisk?,
    val requiredProperty: SkaldVaultV1ProviderFactoryIsolationRequiredProperty?,
) {
    val containsProviderHandle: Boolean = false
    val containsFactoryObject: Boolean = false
    val containsRegistryObject: Boolean = false
    val containsCryptoObject: Boolean = false
    val containsByteMaterial: Boolean = false
    val containsPathOrRootText: Boolean = false
    val containsStorageIdentifier: Boolean = false
    val containsSecretMaterial: Boolean = false

    override fun toString(): String =
        "SkaldVaultV1ProviderFactoryIsolationPolicyToken(" +
            "policyId=$policyId, " +
            "topic=$topic, " +
            "risk=$risk, " +
            "requiredProperty=$requiredProperty, " +
            "providerHandle=<redacted>, " +
            "factoryObject=<redacted>, " +
            "registryObject=<redacted>, " +
            "cryptoObject=<redacted>, " +
            "byteMaterial=<redacted>, " +
            "pathOrRoot=<redacted>, " +
            "storageIdentifier=<redacted>, " +
            "secretMaterial=<redacted>" +
            ")"

    companion object {
        fun redacted(
            policyId: String,
            topic: SkaldVaultV1ProviderFactoryIsolationTopic?,
            risk: SkaldVaultV1ProviderFactoryIsolationRisk?,
            requiredProperty: SkaldVaultV1ProviderFactoryIsolationRequiredProperty?,
        ): SkaldVaultV1ProviderFactoryIsolationPolicyToken {
            return SkaldVaultV1ProviderFactoryIsolationPolicyToken(
                policyId = policyId,
                topic = topic,
                risk = risk,
                requiredProperty = requiredProperty,
            )
        }
    }
}

class SkaldVaultV1ProviderFactoryIsolationRequest private constructor(
    val source: SkaldVaultV1ProviderFactoryIsolationSource,
    val topic: SkaldVaultV1ProviderFactoryIsolationTopic?,
    val risk: SkaldVaultV1ProviderFactoryIsolationRisk?,
    val requiredProperty: SkaldVaultV1ProviderFactoryIsolationRequiredProperty?,
    private val providerSelectionResult: VaultCryptoProviderSelectionResult?,
    private val providerRegistryIsolationEvidence: SkaldVaultV1ProviderRegistryIsolationEvidence?,
    private val nonSelectableSkeletonEvidence: SkaldVaultV1NonSelectableProviderSkeletonEvidence?,
    private val providerInterfaceAuditEvidence: SkaldVaultV1ProviderInterfaceContractAuditEvidence?,
    private val providerSelectionPromotionEvidence: SkaldVaultV1ProviderSelectionPromotionEvidence?,
    private val dependencyBuildEvidence: SkaldVaultV1ProviderDependencyBuildEvidence?,
    private val providerCandidatePackagingEvidence: SkaldVaultV1ProviderCandidatePackagingEvidence?,
    private val authorizationReadinessMatrixEvidence: SkaldVaultV1AuthorizationReadinessMatrixEvidence?,
    private val providerOperationEvidence: SkaldVaultV1VaultProviderOperationAuthorizationEvidence?,
    private val runtimeRandomnessEvidence: SkaldVaultV1VaultRuntimeRandomnessAuthorizationEvidence?,
    private val kdfCalibrationEvidence: SkaldVaultV1VaultKdfCalibrationAuthorizationEvidence?,
    private val secureStorageEvidence: SkaldVaultV1VaultSecureStorageAuthorizationEvidence?,
    private val creationAuthorizationEvidence: SkaldVaultV1VaultCreationAuthorizationEvidence?,
    private val unlockAuthorizationEvidence: SkaldVaultV1VaultUnlockAuthorizationEvidence?,
) {
    val providerSelectionEvidenceSupplied: Boolean
        get() = providerSelectionResult != null

    val providerRegistryIsolationEvidenceSupplied: Boolean
        get() = providerRegistryIsolationEvidence != null

    val nonSelectableSkeletonEvidenceSupplied: Boolean
        get() = nonSelectableSkeletonEvidence != null

    val providerInterfaceAuditEvidenceSupplied: Boolean
        get() = providerInterfaceAuditEvidence != null

    val providerSelectionPromotionEvidenceSupplied: Boolean
        get() = providerSelectionPromotionEvidence != null

    val dependencyBuildEvidenceSupplied: Boolean
        get() = dependencyBuildEvidence != null

    val providerCandidatePackagingEvidenceSupplied: Boolean
        get() = providerCandidatePackagingEvidence != null

    val authorizationReadinessMatrixEvidenceSupplied: Boolean
        get() = authorizationReadinessMatrixEvidence != null

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
        "SkaldVaultV1ProviderFactoryIsolationRequest(" +
            "source=$source, " +
            "topic=$topic, " +
            "risk=$risk, " +
            "requiredProperty=$requiredProperty, " +
            "providerSelection=<redacted>, " +
            "providerRegistryIsolation=<redacted>, " +
            "nonSelectableSkeleton=<redacted>, " +
            "providerInterfaceAudit=<redacted>, " +
            "providerSelectionPromotion=<redacted>, " +
            "dependencyBuild=<redacted>, " +
            "providerCandidatePackaging=<redacted>, " +
            "authorizationReadinessMatrix=<redacted>, " +
            "providerOperation=<redacted>, " +
            "runtimeRandomness=<redacted>, " +
            "kdfCalibration=<redacted>, " +
            "secureStorage=<redacted>, " +
            "creationAuthorization=<redacted>, " +
            "unlockAuthorization=<redacted>" +
            ")"

    companion object {
        fun currentEvidence(
            providerSelectionResult: VaultCryptoProviderSelectionResult? = null,
            providerRegistryIsolationEvidence: SkaldVaultV1ProviderRegistryIsolationEvidence? = null,
            nonSelectableSkeletonEvidence: SkaldVaultV1NonSelectableProviderSkeletonEvidence? = null,
            providerInterfaceAuditEvidence: SkaldVaultV1ProviderInterfaceContractAuditEvidence? = null,
            providerSelectionPromotionEvidence: SkaldVaultV1ProviderSelectionPromotionEvidence? = null,
            dependencyBuildEvidence: SkaldVaultV1ProviderDependencyBuildEvidence? = null,
            providerCandidatePackagingEvidence: SkaldVaultV1ProviderCandidatePackagingEvidence? = null,
            authorizationReadinessMatrixEvidence: SkaldVaultV1AuthorizationReadinessMatrixEvidence? = null,
            providerOperationEvidence: SkaldVaultV1VaultProviderOperationAuthorizationEvidence? = null,
            runtimeRandomnessEvidence: SkaldVaultV1VaultRuntimeRandomnessAuthorizationEvidence? = null,
            kdfCalibrationEvidence: SkaldVaultV1VaultKdfCalibrationAuthorizationEvidence? = null,
            secureStorageEvidence: SkaldVaultV1VaultSecureStorageAuthorizationEvidence? = null,
            creationAuthorizationEvidence: SkaldVaultV1VaultCreationAuthorizationEvidence? = null,
            unlockAuthorizationEvidence: SkaldVaultV1VaultUnlockAuthorizationEvidence? = null,
        ): SkaldVaultV1ProviderFactoryIsolationRequest =
            SkaldVaultV1ProviderFactoryIsolationRequest(
                source = SkaldVaultV1ProviderFactoryIsolationSource.CurrentTypedEvidence,
                topic = null,
                risk = null,
                requiredProperty = null,
                providerSelectionResult = providerSelectionResult,
                providerRegistryIsolationEvidence = providerRegistryIsolationEvidence,
                nonSelectableSkeletonEvidence = nonSelectableSkeletonEvidence,
                providerInterfaceAuditEvidence = providerInterfaceAuditEvidence,
                providerSelectionPromotionEvidence = providerSelectionPromotionEvidence,
                dependencyBuildEvidence = dependencyBuildEvidence,
                providerCandidatePackagingEvidence = providerCandidatePackagingEvidence,
                authorizationReadinessMatrixEvidence = authorizationReadinessMatrixEvidence,
                providerOperationEvidence = providerOperationEvidence,
                runtimeRandomnessEvidence = runtimeRandomnessEvidence,
                kdfCalibrationEvidence = kdfCalibrationEvidence,
                secureStorageEvidence = secureStorageEvidence,
                creationAuthorizationEvidence = creationAuthorizationEvidence,
                unlockAuthorizationEvidence = unlockAuthorizationEvidence,
            )

        fun forTopic(
            topic: SkaldVaultV1ProviderFactoryIsolationTopic,
        ): SkaldVaultV1ProviderFactoryIsolationRequest =
            SkaldVaultV1ProviderFactoryIsolationRequest(
                source = SkaldVaultV1ProviderFactoryIsolationSource.FactoryTopicAudit,
                topic = topic,
                risk = null,
                requiredProperty = null,
                providerSelectionResult = null,
                providerRegistryIsolationEvidence = null,
                nonSelectableSkeletonEvidence = null,
                providerInterfaceAuditEvidence = null,
                providerSelectionPromotionEvidence = null,
                dependencyBuildEvidence = null,
                providerCandidatePackagingEvidence = null,
                authorizationReadinessMatrixEvidence = null,
                providerOperationEvidence = null,
                runtimeRandomnessEvidence = null,
                kdfCalibrationEvidence = null,
                secureStorageEvidence = null,
                creationAuthorizationEvidence = null,
                unlockAuthorizationEvidence = null,
            )

        fun forRisk(
            risk: SkaldVaultV1ProviderFactoryIsolationRisk,
        ): SkaldVaultV1ProviderFactoryIsolationRequest =
            SkaldVaultV1ProviderFactoryIsolationRequest(
                source = SkaldVaultV1ProviderFactoryIsolationSource.FactoryRiskAudit,
                topic = null,
                risk = risk,
                requiredProperty = null,
                providerSelectionResult = null,
                providerRegistryIsolationEvidence = null,
                nonSelectableSkeletonEvidence = null,
                providerInterfaceAuditEvidence = null,
                providerSelectionPromotionEvidence = null,
                dependencyBuildEvidence = null,
                providerCandidatePackagingEvidence = null,
                authorizationReadinessMatrixEvidence = null,
                providerOperationEvidence = null,
                runtimeRandomnessEvidence = null,
                kdfCalibrationEvidence = null,
                secureStorageEvidence = null,
                creationAuthorizationEvidence = null,
                unlockAuthorizationEvidence = null,
            )

        fun forRequiredProperty(
            requiredProperty: SkaldVaultV1ProviderFactoryIsolationRequiredProperty,
        ): SkaldVaultV1ProviderFactoryIsolationRequest =
            SkaldVaultV1ProviderFactoryIsolationRequest(
                source = SkaldVaultV1ProviderFactoryIsolationSource.RequiredPropertyAudit,
                topic = null,
                risk = null,
                requiredProperty = requiredProperty,
                providerSelectionResult = null,
                providerRegistryIsolationEvidence = null,
                nonSelectableSkeletonEvidence = null,
                providerInterfaceAuditEvidence = null,
                providerSelectionPromotionEvidence = null,
                dependencyBuildEvidence = null,
                providerCandidatePackagingEvidence = null,
                authorizationReadinessMatrixEvidence = null,
                providerOperationEvidence = null,
                runtimeRandomnessEvidence = null,
                kdfCalibrationEvidence = null,
                secureStorageEvidence = null,
                creationAuthorizationEvidence = null,
                unlockAuthorizationEvidence = null,
            )

        fun evidenceInteractionAudit(): SkaldVaultV1ProviderFactoryIsolationRequest =
            SkaldVaultV1ProviderFactoryIsolationRequest(
                source = SkaldVaultV1ProviderFactoryIsolationSource.EvidenceInteractionAudit,
                topic = null,
                risk = null,
                requiredProperty = null,
                providerSelectionResult = null,
                providerRegistryIsolationEvidence = null,
                nonSelectableSkeletonEvidence = null,
                providerInterfaceAuditEvidence = null,
                providerSelectionPromotionEvidence = null,
                dependencyBuildEvidence = null,
                providerCandidatePackagingEvidence = null,
                authorizationReadinessMatrixEvidence = null,
                providerOperationEvidence = null,
                runtimeRandomnessEvidence = null,
                kdfCalibrationEvidence = null,
                secureStorageEvidence = null,
                creationAuthorizationEvidence = null,
                unlockAuthorizationEvidence = null,
            )
    }
}

data class SkaldVaultV1ProviderFactoryIsolationTopicRow(
    val topic: SkaldVaultV1ProviderFactoryIsolationTopic,
    val statuses: Set<SkaldVaultV1ProviderFactoryIsolationStatus>,
    val blockers: Set<SkaldVaultV1ProviderFactoryIsolationBlocker>,
    val modeled: Boolean,
    val evidenceOnly: Boolean,
    val constructionExcluded: Boolean,
    val factoryAvailable: Boolean,
    val runtimeConstructionAllowed: Boolean,
    val providerRuntimeExposed: Boolean,
    val redactionClass: SkaldVaultV1ProviderFactoryIsolationRedactionClass,
)

data class SkaldVaultV1ProviderFactoryIsolationRiskRow(
    val risk: SkaldVaultV1ProviderFactoryIsolationRisk,
    val statuses: Set<SkaldVaultV1ProviderFactoryIsolationStatus>,
    val blockers: Set<SkaldVaultV1ProviderFactoryIsolationBlocker>,
    val rejected: Boolean,
    val accepted: Boolean,
    val canConstruct: Boolean,
    val canExposeRuntime: Boolean,
    val canExecute: Boolean,
    val canChangeSelectability: Boolean,
    val redactionClass: SkaldVaultV1ProviderFactoryIsolationRedactionClass,
)

data class SkaldVaultV1ProviderFactoryIsolationRequiredPropertyRow(
    val requiredProperty: SkaldVaultV1ProviderFactoryIsolationRequiredProperty,
    val statuses: Set<SkaldVaultV1ProviderFactoryIsolationStatus>,
    val blockers: Set<SkaldVaultV1ProviderFactoryIsolationBlocker>,
    val satisfiedForCurrentState: Boolean,
    val futureExplicitReviewRequired: Boolean,
    val factoryRuntimeAvailable: Boolean,
    val nonDisabledConstructionAllowed: Boolean,
    val redactionClass: SkaldVaultV1ProviderFactoryIsolationRedactionClass,
)

data class SkaldVaultV1ProviderFactoryIsolationSummary(
    val policyId: String,
    val policyVersion: Int,
    val statuses: Set<SkaldVaultV1ProviderFactoryIsolationStatus>,
    val topics: Set<SkaldVaultV1ProviderFactoryIsolationTopic>,
    val risks: Set<SkaldVaultV1ProviderFactoryIsolationRisk>,
    val requiredProperties: Set<SkaldVaultV1ProviderFactoryIsolationRequiredProperty>,
    val blockers: Set<SkaldVaultV1ProviderFactoryIsolationBlocker>,
    val redactionClasses: Set<SkaldVaultV1ProviderFactoryIsolationRedactionClass>,
    val capability: SkaldVaultV1ProviderFactoryIsolationCapability,
    val stillDisabled: Boolean,
    val evidenceOnly: Boolean,
    val confirmsNoFactory: Boolean,
    val blocksConstruction: Boolean,
    val blocksProviderSelection: Boolean,
)

data class SkaldVaultV1ProviderFactoryIsolationEvidence(
    val policyId: String,
    val policyVersion: Int,
    val source: SkaldVaultV1ProviderFactoryIsolationSource,
    val status: SkaldVaultV1ProviderFactoryIsolationStatus,
    val topicRows: List<SkaldVaultV1ProviderFactoryIsolationTopicRow>,
    val riskRows: List<SkaldVaultV1ProviderFactoryIsolationRiskRow>,
    val requiredPropertyRows: List<SkaldVaultV1ProviderFactoryIsolationRequiredPropertyRow>,
    val blockers: Set<SkaldVaultV1ProviderFactoryIsolationBlocker>,
    val capability: SkaldVaultV1ProviderFactoryIsolationCapability,
    val policyTokenEvidence: SkaldVaultV1ProviderFactoryIsolationPolicyToken,
    val providerSelectionEvidenceConsumed: Boolean,
    val providerRegistryIsolationEvidenceConsumed: Boolean,
    val nonSelectableSkeletonEvidenceConsumed: Boolean,
    val providerInterfaceAuditEvidenceConsumed: Boolean,
    val providerSelectionPromotionEvidenceConsumed: Boolean,
    val dependencyBuildEvidenceConsumed: Boolean,
    val providerCandidatePackagingEvidenceConsumed: Boolean,
    val authorizationReadinessMatrixEvidenceConsumed: Boolean,
    val providerOperationEvidenceConsumed: Boolean,
    val runtimeRandomnessEvidenceConsumed: Boolean,
    val kdfCalibrationEvidenceConsumed: Boolean,
    val secureStorageEvidenceConsumed: Boolean,
    val creationAuthorizationEvidenceConsumed: Boolean,
    val unlockAuthorizationEvidenceConsumed: Boolean,
    val providerFactoryIsolationBoundaryModeled: Boolean = true,
    val providerFactoryIsolationStillDisabled: Boolean = true,
    val providerFactoryIsolationConfirmsNoFactory: Boolean = true,
    val providerFactoryIsolationExcludesSkeletonConstruction: Boolean = true,
    val providerFactoryIsolationExcludesCandidateConstruction: Boolean = true,
    val providerFactoryIsolationExcludesRuntimeProviderConstruction: Boolean = true,
    val providerFactoryIsolationDoesNotRegisterProvider: Boolean = true,
    val providerFactoryIsolationDoesNotEnableProviderSelection: Boolean = true,
    val providerFactoryIsolationDoesNotRunCrypto: Boolean = true,
    val providerFactoryIsolationDoesNotEnableCreation: Boolean = true,
    val providerFactoryIsolationDoesNotEnableUnlock: Boolean = true,
    val providerFactoryIsolationDoesNotEnablePersistence: Boolean = true,
    val providerFactoryIsolationModeled: Boolean = true,
    val providerFactoryExistsForNonDisabledProvider: Boolean = false,
    val providerFactoryReachableFromSelection: Boolean = false,
    val providerFactoryReachableFromRegistry: Boolean = false,
    val providerFactoryCanConstructSkeleton: Boolean = false,
    val providerFactoryCanConstructCandidate: Boolean = false,
    val providerFactoryCanConstructProviderRuntime: Boolean = false,
    val providerFactoryCanExposeProviderHandle: Boolean = false,
    val providerFactoryCanExposeCryptoObject: Boolean = false,
    val providerFactoryAcceptsByteMaterial: Boolean = false,
    val providerFactoryCanExecuteProviderOperations: Boolean = false,
    val providerFactoryCanRunKat: Boolean = false,
    val providerFactoryCanUseRandomness: Boolean = false,
    val providerFactoryCanRunKdf: Boolean = false,
    val providerFactoryCanRunAead: Boolean = false,
    val providerFactoryCanWrapKeys: Boolean = false,
    val providerFactoryCanCreateVault: Boolean = false,
    val providerFactoryCanUnlockVault: Boolean = false,
    val providerFactoryCanPersistVault: Boolean = false,
    val providerFactoryAdded: Boolean = false,
    val providerRegistryContainsCandidate: Boolean = false,
    val providerRegistryContainsSkeleton: Boolean = false,
    val providerRegistryEnabled: Boolean = false,
    val providerRuntimeInstantiable: Boolean = false,
    val providerSelectable: Boolean = false,
    val productionProviderSelectable: Boolean = false,
    val providerOperationAuthorized: Boolean = false,
    val providerKatExecutionAvailable: Boolean = false,
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
        "SkaldVaultV1ProviderFactoryIsolationEvidence(" +
            "policyId=$policyId, " +
            "policyVersion=$policyVersion, " +
            "source=$source, " +
            "status=$status, " +
            "topicRows=${topicRows.size}, " +
            "riskRows=${riskRows.size}, " +
            "requiredPropertyRows=${requiredPropertyRows.size}, " +
            "blockers=${blockers.size}, " +
            "policyToken:<redacted>" +
            ")"
}

sealed class SkaldVaultV1ProviderFactoryIsolationResult<out T> {
    data class Blocked<out T>(
        val value: T,
    ) : SkaldVaultV1ProviderFactoryIsolationResult<T>() {
        override fun toString(): String =
            "SkaldVaultV1ProviderFactoryIsolationResult.Blocked(" +
                "value=<redacted-provider-factory-isolation-evidence>)"
    }
}

object SkaldVaultV1ProviderFactoryIsolationPolicy :
    SkaldVaultV1ProviderFactoryIsolationBoundary {
    const val POLICY_ID = "skald-vault-v1-provider-factory-isolation-boundary-v1"
    const val POLICY_VERSION = 1

    override fun evaluate(
        request: SkaldVaultV1ProviderFactoryIsolationRequest,
    ): SkaldVaultV1ProviderFactoryIsolationResult<SkaldVaultV1ProviderFactoryIsolationEvidence> {
        val topicRows = topicRowsFor(request.topic)
        val riskRows = riskRowsFor(request.risk)
        val requiredPropertyRows = requiredPropertyRowsFor(request.requiredProperty)
        return SkaldVaultV1ProviderFactoryIsolationResult.Blocked(
            SkaldVaultV1ProviderFactoryIsolationEvidence(
                policyId = POLICY_ID,
                policyVersion = POLICY_VERSION,
                source = request.source,
                status = SkaldVaultV1ProviderFactoryIsolationStatus.StillDisabled,
                topicRows = topicRows,
                riskRows = riskRows,
                requiredPropertyRows = requiredPropertyRows,
                blockers = topicRows.flatMap { it.blockers }.toSet() +
                    riskRows.flatMap { it.blockers }.toSet() +
                    requiredPropertyRows.flatMap { it.blockers }.toSet() +
                    baseBlockers,
                capability = SkaldVaultV1ProviderFactoryIsolationCapability.StillDisabled,
                policyTokenEvidence = SkaldVaultV1ProviderFactoryIsolationPolicyToken.redacted(
                    policyId = POLICY_ID,
                    topic = request.topic,
                    risk = request.risk,
                    requiredProperty = request.requiredProperty,
                ),
                providerSelectionEvidenceConsumed = request.providerSelectionEvidenceSupplied,
                providerRegistryIsolationEvidenceConsumed =
                    request.providerRegistryIsolationEvidenceSupplied,
                nonSelectableSkeletonEvidenceConsumed = request.nonSelectableSkeletonEvidenceSupplied,
                providerInterfaceAuditEvidenceConsumed = request.providerInterfaceAuditEvidenceSupplied,
                providerSelectionPromotionEvidenceConsumed =
                    request.providerSelectionPromotionEvidenceSupplied,
                dependencyBuildEvidenceConsumed = request.dependencyBuildEvidenceSupplied,
                providerCandidatePackagingEvidenceConsumed =
                    request.providerCandidatePackagingEvidenceSupplied,
                authorizationReadinessMatrixEvidenceConsumed =
                    request.authorizationReadinessMatrixEvidenceSupplied,
                providerOperationEvidenceConsumed = request.providerOperationEvidenceSupplied,
                runtimeRandomnessEvidenceConsumed = request.runtimeRandomnessEvidenceSupplied,
                kdfCalibrationEvidenceConsumed = request.kdfCalibrationEvidenceSupplied,
                secureStorageEvidenceConsumed = request.secureStorageEvidenceSupplied,
                creationAuthorizationEvidenceConsumed = request.creationAuthorizationEvidenceSupplied,
                unlockAuthorizationEvidenceConsumed = request.unlockAuthorizationEvidenceSupplied,
            ),
        )
    }

    fun currentPolicySummary(): SkaldVaultV1ProviderFactoryIsolationSummary =
        SkaldVaultV1ProviderFactoryIsolationSummary(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            statuses = SkaldVaultV1ProviderFactoryIsolationStatus.entries.toSet(),
            topics = SkaldVaultV1ProviderFactoryIsolationTopic.entries.toSet(),
            risks = SkaldVaultV1ProviderFactoryIsolationRisk.entries.toSet(),
            requiredProperties = SkaldVaultV1ProviderFactoryIsolationRequiredProperty.entries.toSet(),
            blockers = SkaldVaultV1ProviderFactoryIsolationBlocker.entries.toSet(),
            redactionClasses = SkaldVaultV1ProviderFactoryIsolationRedactionClass.entries.toSet(),
            capability = SkaldVaultV1ProviderFactoryIsolationCapability.StillDisabled,
            stillDisabled = true,
            evidenceOnly = true,
            confirmsNoFactory = true,
            blocksConstruction = true,
            blocksProviderSelection = true,
        )

    private fun topicRowsFor(
        topic: SkaldVaultV1ProviderFactoryIsolationTopic?,
    ): List<SkaldVaultV1ProviderFactoryIsolationTopicRow> {
        val rows = SkaldVaultV1ProviderFactoryIsolationTopic.entries.map { currentTopic ->
            topicRow(currentTopic)
        }
        return topic?.let { requested -> rows.filter { it.topic == requested } } ?: rows
    }

    private fun riskRowsFor(
        risk: SkaldVaultV1ProviderFactoryIsolationRisk?,
    ): List<SkaldVaultV1ProviderFactoryIsolationRiskRow> {
        val rows = SkaldVaultV1ProviderFactoryIsolationRisk.entries.map { currentRisk ->
            riskRow(currentRisk)
        }
        return risk?.let { requested -> rows.filter { it.risk == requested } } ?: rows
    }

    private fun requiredPropertyRowsFor(
        requiredProperty: SkaldVaultV1ProviderFactoryIsolationRequiredProperty?,
    ): List<SkaldVaultV1ProviderFactoryIsolationRequiredPropertyRow> {
        val rows = SkaldVaultV1ProviderFactoryIsolationRequiredProperty.entries.map { currentProperty ->
            requiredPropertyRow(currentProperty)
        }
        return requiredProperty?.let { requested ->
            rows.filter { it.requiredProperty == requested }
        } ?: rows
    }

    private fun topicRow(
        topic: SkaldVaultV1ProviderFactoryIsolationTopic,
    ): SkaldVaultV1ProviderFactoryIsolationTopicRow =
        SkaldVaultV1ProviderFactoryIsolationTopicRow(
            topic = topic,
            statuses = baseStatuses + topicStatus(topic),
            blockers = topicBlockers(topic) + baseBlockers,
            modeled = true,
            evidenceOnly = true,
            constructionExcluded =
                topic != SkaldVaultV1ProviderFactoryIsolationTopic.ActiveProviderFactorySurface &&
                    topic != SkaldVaultV1ProviderFactoryIsolationTopic.DisabledProviderConstructionSurface,
            factoryAvailable = false,
            runtimeConstructionAllowed = false,
            providerRuntimeExposed = false,
            redactionClass = SkaldVaultV1ProviderFactoryIsolationRedactionClass.FactoryTopicOnly,
        )

    private fun riskRow(
        risk: SkaldVaultV1ProviderFactoryIsolationRisk,
    ): SkaldVaultV1ProviderFactoryIsolationRiskRow =
        SkaldVaultV1ProviderFactoryIsolationRiskRow(
            risk = risk,
            statuses = baseStatuses + riskStatus(risk),
            blockers = riskBlockers(risk) + baseBlockers,
            rejected = true,
            accepted = false,
            canConstruct = false,
            canExposeRuntime = false,
            canExecute = false,
            canChangeSelectability = false,
            redactionClass = SkaldVaultV1ProviderFactoryIsolationRedactionClass.RiskCategoryOnly,
        )

    private fun requiredPropertyRow(
        requiredProperty: SkaldVaultV1ProviderFactoryIsolationRequiredProperty,
    ): SkaldVaultV1ProviderFactoryIsolationRequiredPropertyRow =
        SkaldVaultV1ProviderFactoryIsolationRequiredPropertyRow(
            requiredProperty = requiredProperty,
            statuses = baseStatuses + SkaldVaultV1ProviderFactoryIsolationStatus.FactoryIsolated,
            blockers = requiredPropertyBlockers(requiredProperty) + baseBlockers,
            satisfiedForCurrentState = true,
            futureExplicitReviewRequired = true,
            factoryRuntimeAvailable = false,
            nonDisabledConstructionAllowed = false,
            redactionClass = SkaldVaultV1ProviderFactoryIsolationRedactionClass.RequiredPropertyOnly,
        )

    private fun topicStatus(
        topic: SkaldVaultV1ProviderFactoryIsolationTopic,
    ): SkaldVaultV1ProviderFactoryIsolationStatus =
        when (topic) {
            SkaldVaultV1ProviderFactoryIsolationTopic.ActiveProviderFactorySurface,
            SkaldVaultV1ProviderFactoryIsolationTopic.DisabledProviderConstructionSurface ->
                SkaldVaultV1ProviderFactoryIsolationStatus.NoFactoryAvailable
            SkaldVaultV1ProviderFactoryIsolationTopic.NonDisabledProviderConstructionExcluded,
            SkaldVaultV1ProviderFactoryIsolationTopic.NonSelectableSkeletonConstructionExcluded,
            SkaldVaultV1ProviderFactoryIsolationTopic.CandidateProviderConstructionExcluded ->
                SkaldVaultV1ProviderFactoryIsolationStatus.ConstructionExcluded
            SkaldVaultV1ProviderFactoryIsolationTopic.ReleaseMainnetEvidenceFutureExplicitReviewOnly ->
                SkaldVaultV1ProviderFactoryIsolationStatus.MainnetBlocked
            else -> SkaldVaultV1ProviderFactoryIsolationStatus.RuntimeConstructionBlocked
        }

    private fun riskStatus(
        risk: SkaldVaultV1ProviderFactoryIsolationRisk,
    ): SkaldVaultV1ProviderFactoryIsolationStatus =
        when (risk) {
            SkaldVaultV1ProviderFactoryIsolationRisk.FactoryCreatesNonDisabledProvider,
            SkaldVaultV1ProviderFactoryIsolationRisk.FactoryCreatesSkeletonProvider,
            SkaldVaultV1ProviderFactoryIsolationRisk.FactoryCreatesCandidateProvider ->
                SkaldVaultV1ProviderFactoryIsolationStatus.ConstructionExcluded
            SkaldVaultV1ProviderFactoryIsolationRisk.FactoryCallsProviderOperations,
            SkaldVaultV1ProviderFactoryIsolationRisk.FactoryRunsKats,
            SkaldVaultV1ProviderFactoryIsolationRisk.FactoryReachesRandomnessKdfAead,
            SkaldVaultV1ProviderFactoryIsolationRisk.FactoryWrapsKeys ->
                SkaldVaultV1ProviderFactoryIsolationStatus.OperationExecutionBlocked
            SkaldVaultV1ProviderFactoryIsolationRisk.FactoryEnablesMainnet ->
                SkaldVaultV1ProviderFactoryIsolationStatus.MainnetBlocked
            else -> SkaldVaultV1ProviderFactoryIsolationStatus.RuntimeConstructionBlocked
        }

    private fun topicBlockers(
        topic: SkaldVaultV1ProviderFactoryIsolationTopic,
    ): Set<SkaldVaultV1ProviderFactoryIsolationBlocker> =
        when (topic) {
            SkaldVaultV1ProviderFactoryIsolationTopic.ActiveProviderFactorySurface,
            SkaldVaultV1ProviderFactoryIsolationTopic.DisabledProviderConstructionSurface ->
                setOf(
                    SkaldVaultV1ProviderFactoryIsolationBlocker.NoNonDisabledProviderFactory,
                    SkaldVaultV1ProviderFactoryIsolationBlocker.NoNonDisabledProviderConstructor,
                )
            SkaldVaultV1ProviderFactoryIsolationTopic.NonDisabledProviderConstructionExcluded ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.NoNonDisabledProviderFactory)
            SkaldVaultV1ProviderFactoryIsolationTopic.NonSelectableSkeletonConstructionExcluded ->
                setOf(
                    SkaldVaultV1ProviderFactoryIsolationBlocker.SkeletonConstructionExcluded,
                    SkaldVaultV1ProviderFactoryIsolationBlocker.NonSelectableProviderSkeletonStillDisabled,
                )
            SkaldVaultV1ProviderFactoryIsolationTopic.CandidateProviderConstructionExcluded ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.CandidateConstructionExcluded)
            SkaldVaultV1ProviderFactoryIsolationTopic.DependencyBuildEvidenceConstructionExcluded ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.DependencyBuildConstructionExcluded)
            SkaldVaultV1ProviderFactoryIsolationTopic.CandidatePackagingConstructionExcluded ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.CandidatePackagingConstructionExcluded)
            SkaldVaultV1ProviderFactoryIsolationTopic.InterfaceAuditConstructionExcluded ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.ProviderInterfaceAuditEvidenceOnly)
            SkaldVaultV1ProviderFactoryIsolationTopic.PromotionBlockerConstructionExcluded ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.ProviderSelectionPromotionBlocked)
            SkaldVaultV1ProviderFactoryIsolationTopic.AuthorizationReadinessMatrixConstructionExcluded ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.AuthorizationReadinessMatrixBlocked)
            SkaldVaultV1ProviderFactoryIsolationTopic.ProviderRegistryConstructionExcluded ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.ProviderRegistryIsolationBlocked)
            SkaldVaultV1ProviderFactoryIsolationTopic.ProviderOperationAuthorizationConstructionExcluded ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.ProviderOperationAuthorizationBlocked)
            SkaldVaultV1ProviderFactoryIsolationTopic.RuntimeRandomnessConstructionExcluded ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.RuntimeRandomnessAuthorizationBlocked)
            SkaldVaultV1ProviderFactoryIsolationTopic.KdfCalibrationConstructionExcluded ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.KdfCalibrationAuthorizationBlocked)
            SkaldVaultV1ProviderFactoryIsolationTopic.SecureStorageConstructionExcluded ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.SecureStorageAuthorizationBlocked)
            SkaldVaultV1ProviderFactoryIsolationTopic.CreationUnlockConstructionExcluded ->
                setOf(
                    SkaldVaultV1ProviderFactoryIsolationBlocker.CreationAuthorizationBlocked,
                    SkaldVaultV1ProviderFactoryIsolationBlocker.UnlockAuthorizationBlocked,
                )
            SkaldVaultV1ProviderFactoryIsolationTopic.TestOnlyFactoryEvidenceExcludedFromProductionConstruction ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.TestOnlyEvidenceCannotAuthorizeProduction)
            SkaldVaultV1ProviderFactoryIsolationTopic.WarningOnlyEvidenceExcludedFromConstruction ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.WarningOnlyEvidenceCannotAuthorize)
            SkaldVaultV1ProviderFactoryIsolationTopic.UserConsentEvidenceExcludedFromConstruction ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.UserConsentCannotOverride)
            SkaldVaultV1ProviderFactoryIsolationTopic.ReleaseMainnetEvidenceFutureExplicitReviewOnly ->
                setOf(
                    SkaldVaultV1ProviderFactoryIsolationBlocker.ReleaseReviewMissing,
                    SkaldVaultV1ProviderFactoryIsolationBlocker.MainnetDisabled,
                )
        }

    private fun riskBlockers(
        risk: SkaldVaultV1ProviderFactoryIsolationRisk,
    ): Set<SkaldVaultV1ProviderFactoryIsolationBlocker> =
        when (risk) {
            SkaldVaultV1ProviderFactoryIsolationRisk.FactoryCreatesNonDisabledProvider ->
                setOf(
                    SkaldVaultV1ProviderFactoryIsolationBlocker.NoNonDisabledProviderFactory,
                    SkaldVaultV1ProviderFactoryIsolationBlocker.NoNonDisabledProviderConstructor,
                )
            SkaldVaultV1ProviderFactoryIsolationRisk.FactoryCreatesSkeletonProvider ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.SkeletonConstructionExcluded)
            SkaldVaultV1ProviderFactoryIsolationRisk.FactoryCreatesCandidateProvider ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.CandidateConstructionExcluded)
            SkaldVaultV1ProviderFactoryIsolationRisk.FactoryTreatsDependencyBuildEvidenceAsConstructionEvidence ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.DependencyBuildConstructionExcluded)
            SkaldVaultV1ProviderFactoryIsolationRisk.FactoryTreatsPackagingEvidenceAsConstructionEvidence ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.CandidatePackagingConstructionExcluded)
            SkaldVaultV1ProviderFactoryIsolationRisk.FactoryTreatsInterfaceAuditEvidenceAsConstructionEvidence ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.ProviderInterfaceAuditEvidenceOnly)
            SkaldVaultV1ProviderFactoryIsolationRisk.FactoryTreatsPromotionBlockerEvidenceAsConstructionEvidence ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.ProviderSelectionPromotionBlocked)
            SkaldVaultV1ProviderFactoryIsolationRisk.FactoryTreatsMatrixEvidenceAsConstructionEvidence ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.AuthorizationReadinessMatrixBlocked)
            SkaldVaultV1ProviderFactoryIsolationRisk.FactoryAcceptsProviderHandles ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.ProviderHandlesRejected)
            SkaldVaultV1ProviderFactoryIsolationRisk.FactoryAcceptsCryptoObjects ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.CryptoObjectsRejected)
            SkaldVaultV1ProviderFactoryIsolationRisk.FactoryAcceptsByteMaterial ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.ByteMaterialRejected)
            SkaldVaultV1ProviderFactoryIsolationRisk.FactoryAcceptsPlatformCryptoApis ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.PlatformCryptoImportsForbidden)
            SkaldVaultV1ProviderFactoryIsolationRisk.FactoryImportsTinkBouncyJavaxCrypto ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.TinkBouncyJavaxImportsForbidden)
            SkaldVaultV1ProviderFactoryIsolationRisk.FactoryCallsSecureRandom ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.SecureRandomForbidden)
            SkaldVaultV1ProviderFactoryIsolationRisk.FactoryRunsKats ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.KatExecutionUnavailable)
            SkaldVaultV1ProviderFactoryIsolationRisk.FactoryCallsProviderOperations ->
                setOf(
                    SkaldVaultV1ProviderFactoryIsolationBlocker.ProviderOperationAuthorizationBlocked,
                    SkaldVaultV1ProviderFactoryIsolationBlocker.ProviderOperationExecutionForbidden,
                )
            SkaldVaultV1ProviderFactoryIsolationRisk.FactoryReachesRandomnessKdfAead ->
                setOf(
                    SkaldVaultV1ProviderFactoryIsolationBlocker.RuntimeRandomnessAuthorizationBlocked,
                    SkaldVaultV1ProviderFactoryIsolationBlocker.KdfCalibrationAuthorizationBlocked,
                    SkaldVaultV1ProviderFactoryIsolationBlocker.ProviderOperationAuthorizationBlocked,
                )
            SkaldVaultV1ProviderFactoryIsolationRisk.FactoryWrapsKeys ->
                setOf(
                    SkaldVaultV1ProviderFactoryIsolationBlocker.KeyWrappingUnavailable,
                    SkaldVaultV1ProviderFactoryIsolationBlocker.SecureStorageAuthorizationBlocked,
                )
            SkaldVaultV1ProviderFactoryIsolationRisk.FactoryEnablesVaultCreationUnlockPersistence ->
                setOf(
                    SkaldVaultV1ProviderFactoryIsolationBlocker.CreationAuthorizationBlocked,
                    SkaldVaultV1ProviderFactoryIsolationBlocker.UnlockAuthorizationBlocked,
                    SkaldVaultV1ProviderFactoryIsolationBlocker.SecureStorageAuthorizationBlocked,
                )
            SkaldVaultV1ProviderFactoryIsolationRisk.FactoryEnablesMainnet ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.MainnetDisabled)
        }

    private fun requiredPropertyBlockers(
        requiredProperty: SkaldVaultV1ProviderFactoryIsolationRequiredProperty,
    ): Set<SkaldVaultV1ProviderFactoryIsolationBlocker> =
        when (requiredProperty) {
            SkaldVaultV1ProviderFactoryIsolationRequiredProperty.NoNonDisabledProviderFactoryExists ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.NoNonDisabledProviderFactory)
            SkaldVaultV1ProviderFactoryIsolationRequiredProperty.NoNonDisabledProviderConstructorExists ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.NoNonDisabledProviderConstructor)
            SkaldVaultV1ProviderFactoryIsolationRequiredProperty.NoProviderFactoryReachableFromProviderSelection,
            SkaldVaultV1ProviderFactoryIsolationRequiredProperty.ProviderSelectionDisabledProviderOnly ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.ProviderSelectionLockedToDisabledProvider)
            SkaldVaultV1ProviderFactoryIsolationRequiredProperty.NoProviderFactoryReachableFromRegistryIsolation ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.ProviderRegistryIsolationBlocked)
            SkaldVaultV1ProviderFactoryIsolationRequiredProperty.NoProviderFactoryReachableFromSkeletonEvidence ->
                setOf(
                    SkaldVaultV1ProviderFactoryIsolationBlocker.SkeletonConstructionExcluded,
                    SkaldVaultV1ProviderFactoryIsolationBlocker.NonSelectableProviderSkeletonStillDisabled,
                )
            SkaldVaultV1ProviderFactoryIsolationRequiredProperty
                .NoProviderFactoryReachableFromCandidatePackagingEvidence ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.CandidatePackagingConstructionExcluded)
            SkaldVaultV1ProviderFactoryIsolationRequiredProperty
                .NoProviderFactoryReachableFromDependencyBuildEvidence ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.DependencyBuildConstructionExcluded)
            SkaldVaultV1ProviderFactoryIsolationRequiredProperty.NoProviderFactoryExposesProviderHandles ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.ProviderHandlesRejected)
            SkaldVaultV1ProviderFactoryIsolationRequiredProperty.NoProviderFactoryExposesCryptoObjects ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.CryptoObjectsRejected)
            SkaldVaultV1ProviderFactoryIsolationRequiredProperty.NoProviderFactoryAcceptsByteMaterial ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.ByteMaterialRejected)
            SkaldVaultV1ProviderFactoryIsolationRequiredProperty.NoProviderFactoryHasPlatformCryptoImports ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.PlatformCryptoImportsForbidden)
            SkaldVaultV1ProviderFactoryIsolationRequiredProperty.NoProviderFactoryCanExecuteProviderOperations ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.ProviderOperationExecutionForbidden)
            SkaldVaultV1ProviderFactoryIsolationRequiredProperty.NoProviderFactoryCanRunKats ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.KatExecutionUnavailable)
            SkaldVaultV1ProviderFactoryIsolationRequiredProperty.NoProviderFactoryCanCallRandomness ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.RuntimeRandomnessAuthorizationBlocked)
            SkaldVaultV1ProviderFactoryIsolationRequiredProperty.NoProviderFactoryCanRunKdfAeadHkdfHmac ->
                setOf(
                    SkaldVaultV1ProviderFactoryIsolationBlocker.KdfCalibrationAuthorizationBlocked,
                    SkaldVaultV1ProviderFactoryIsolationBlocker.ProviderOperationAuthorizationBlocked,
                )
            SkaldVaultV1ProviderFactoryIsolationRequiredProperty.NoProviderFactoryCanPromoteDependencyBuildEvidence ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.DependencyBuildConstructionExcluded)
            SkaldVaultV1ProviderFactoryIsolationRequiredProperty
                .NoProviderFactoryCanPromotePackagingSkeletonAuditMatrixEvidence ->
                setOf(
                    SkaldVaultV1ProviderFactoryIsolationBlocker.CandidatePackagingConstructionExcluded,
                    SkaldVaultV1ProviderFactoryIsolationBlocker.NonSelectableProviderSkeletonStillDisabled,
                    SkaldVaultV1ProviderFactoryIsolationBlocker.ProviderInterfaceAuditEvidenceOnly,
                    SkaldVaultV1ProviderFactoryIsolationBlocker.AuthorizationReadinessMatrixBlocked,
                )
            SkaldVaultV1ProviderFactoryIsolationRequiredProperty.WarningUserConsentTestOnlyCannotOverride ->
                setOf(
                    SkaldVaultV1ProviderFactoryIsolationBlocker.WarningOnlyEvidenceCannotAuthorize,
                    SkaldVaultV1ProviderFactoryIsolationBlocker.UserConsentCannotOverride,
                    SkaldVaultV1ProviderFactoryIsolationBlocker.TestOnlyEvidenceCannotAuthorizeProduction,
                )
            SkaldVaultV1ProviderFactoryIsolationRequiredProperty.ProductionProviderSelectableFalse ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.ProductionProviderSelectableFalse)
            SkaldVaultV1ProviderFactoryIsolationRequiredProperty.MainnetDisabled ->
                setOf(SkaldVaultV1ProviderFactoryIsolationBlocker.MainnetDisabled)
        }

    private val baseStatuses: Set<SkaldVaultV1ProviderFactoryIsolationStatus> =
        setOf(
            SkaldVaultV1ProviderFactoryIsolationStatus.BoundaryModeled,
            SkaldVaultV1ProviderFactoryIsolationStatus.StillDisabled,
            SkaldVaultV1ProviderFactoryIsolationStatus.EvidenceOnly,
            SkaldVaultV1ProviderFactoryIsolationStatus.WarningOnlyCannotAuthorize,
            SkaldVaultV1ProviderFactoryIsolationStatus.UserConsentCannotOverride,
            SkaldVaultV1ProviderFactoryIsolationStatus.TestOnlyRejectedForProduction,
        )

    private val baseBlockers: Set<SkaldVaultV1ProviderFactoryIsolationBlocker> =
        setOf(
            SkaldVaultV1ProviderFactoryIsolationBlocker.FactoryIsolationBoundaryStillDisabled,
            SkaldVaultV1ProviderFactoryIsolationBlocker.ModelEvidenceOnly,
            SkaldVaultV1ProviderFactoryIsolationBlocker.NoNonDisabledProviderFactory,
            SkaldVaultV1ProviderFactoryIsolationBlocker.ProviderSelectionLockedToDisabledProvider,
            SkaldVaultV1ProviderFactoryIsolationBlocker.ProductionProviderSelectableFalse,
            SkaldVaultV1ProviderFactoryIsolationBlocker.WarningOnlyEvidenceCannotAuthorize,
            SkaldVaultV1ProviderFactoryIsolationBlocker.UserConsentCannotOverride,
            SkaldVaultV1ProviderFactoryIsolationBlocker.TestOnlyEvidenceCannotAuthorizeProduction,
            SkaldVaultV1ProviderFactoryIsolationBlocker.MainnetDisabled,
        )
}
