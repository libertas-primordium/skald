package com.libertasprimordium.skald.security

interface SkaldVaultV1ProviderRegistryIsolationGuardBoundary {
    fun evaluate(
        request: SkaldVaultV1ProviderRegistryIsolationGuardRequest,
    ): SkaldVaultV1ProviderRegistryIsolationGuardResult<SkaldVaultV1ProviderRegistryIsolationEvidence>
}

enum class SkaldVaultV1ProviderRegistryIsolationSource(val label: String) {
    CurrentTypedEvidence("current provider registry isolation evidence"),
    RegistryTopicAudit("provider registry isolation topic audit"),
    RegistryRiskAudit("provider registry isolation risk audit"),
    RequiredPropertyAudit("provider registry isolation required-property audit"),
    EvidenceInteractionAudit("provider registry isolation evidence interaction audit"),
}

enum class SkaldVaultV1ProviderRegistryIsolationStatus(val label: String) {
    GuardModeled("provider registry isolation guard modeled"),
    StillDisabled("still disabled"),
    EvidenceOnly("evidence only"),
    DisabledProviderOnly("disabled provider only"),
    RegistryIsolated("registry isolated"),
    CandidateExcluded("candidate evidence excluded"),
    SkeletonExcluded("skeleton evidence excluded"),
    FactoryExcluded("factory evidence excluded"),
    PromotionBlocked("registry promotion blocked"),
    RuntimeInstantiationBlocked("runtime instantiation blocked"),
    OperationExecutionBlocked("provider operation execution blocked"),
    WarningOnlyCannotAuthorize("warning-only evidence cannot authorize"),
    UserConsentCannotOverride("user consent cannot override"),
    TestOnlyRejectedForProduction("test-only evidence rejected for production"),
    MainnetBlocked("mainnet blocked"),
}

enum class SkaldVaultV1ProviderRegistryIsolationTopic(val label: String) {
    ActiveProviderSelectionRegistry("active provider-selection registry"),
    DisabledProviderSelected("disabled provider selected"),
    NonSelectableSkeletonExcluded("non-selectable skeleton excluded"),
    CandidatePackagingEvidenceExcludedFromRegistry("candidate-packaging evidence excluded from registry"),
    DependencyBuildEvidenceExcludedFromRegistry("dependency-build evidence excluded from registry"),
    InterfaceAuditEvidenceExcludedFromRegistry("interface-audit evidence excluded from registry"),
    PromotionBlockerEvidenceExcludedFromRegistry("promotion-blocker evidence excluded from registry"),
    AuthorizationReadinessMatrixEvidenceExcludedFromRegistry(
        "authorization/readiness matrix evidence excluded from registry",
    ),
    ProviderOperationAuthorizationExcludedFromRegistryPromotion(
        "provider-operation authorization excluded from registry promotion",
    ),
    RuntimeRandomnessAuthorizationExcludedFromRegistryPromotion(
        "runtime-randomness authorization excluded from registry promotion",
    ),
    KdfCalibrationAuthorizationExcludedFromRegistryPromotion(
        "KDF-calibration authorization excluded from registry promotion",
    ),
    SecureStorageAuthorizationExcludedFromRegistryPromotion(
        "secure-storage authorization excluded from registry promotion",
    ),
    CreationUnlockAuthorizationExcludedFromRegistryPromotion(
        "creation/unlock authorization excluded from registry promotion",
    ),
    TestOnlyEvidenceExcludedFromProductionRegistry("test-only evidence excluded from production registry"),
    WarningOnlyEvidenceExcludedFromRegistryPromotion("warning-only evidence excluded from registry promotion"),
    UserConsentEvidenceExcludedFromRegistryPromotion("user-consent evidence excluded from registry promotion"),
    ReleaseMainnetEvidenceFutureExplicitReviewOnly("release/mainnet evidence requires future explicit review"),
}

enum class SkaldVaultV1ProviderRegistryIsolationRisk(val label: String) {
    SkeletonReferencedBySelectionCode("skeleton referenced by provider-selection code"),
    CandidateFamilyReferencedBySelectionCode("candidate family referenced by provider-selection code"),
    DependencyBuildEvidenceTreatedAsRegistryEntry("dependency/build evidence treated as registry entry"),
    PackagingEvidenceTreatedAsRegistryEntry("packaging evidence treated as registry entry"),
    InterfaceAuditTreatedAsRegistryEntry("interface audit treated as registry entry"),
    PromotionBlockerTreatedAsRegistryEntry("promotion blocker treated as registry entry"),
    MatrixEvidenceTreatedAsRegistryEntry("matrix evidence treated as registry entry"),
    TestOnlyProviderReferencedByProductionSelection("test-only provider referenced by production selection"),
    ProductionProviderSelectableAccidentallyTrue("productionProviderSelectable accidentally true"),
    ProviderSelectableAccidentallyTrue("providerSelectable accidentally true"),
    RegistryContainsNonDisabledProviderId("provider registry contains non-disabled provider id"),
    RegistryCreatesProviderInstance("provider registry creates provider instance"),
    RegistryExposesProviderHandles("provider registry exposes provider handles"),
    RegistryExposesCryptoObjects("provider registry exposes crypto objects"),
    RegistryImportsPlatformCryptoApis("provider registry imports platform crypto APIs"),
    RegistryCallsProviderOperations("provider registry calls provider operations"),
    RegistryRunsKats("provider registry runs KATs"),
    RegistryReachesRandomnessKdfAead("provider registry reaches randomness/KDF/AEAD"),
    RegistryEnablesVaultCreationUnlockPersistence("provider registry enables vault creation/unlock/persistence"),
    RegistryEnablesMainnet("provider registry enables mainnet"),
}

enum class SkaldVaultV1ProviderRegistryIsolationRequiredProperty(val label: String) {
    CurrentRegistrySelectsOnlyDisabledProvider("current registry selects only disabled provider"),
    CurrentRegistryContainsNoCandidateEntries("current registry contains no candidate entries"),
    CurrentRegistryContainsNoSkeletonEntries("current registry contains no skeleton entries"),
    CurrentRegistryContainsNoProviderFactories("current registry contains no provider factories"),
    CurrentRegistryContainsNoProviderConstructors("current registry contains no provider constructors"),
    CurrentRegistryExposesNoProviderHandles("current registry exposes no provider handles"),
    CurrentRegistryExposesNoCryptoObjects("current registry exposes no crypto objects"),
    CurrentRegistryHasNoPlatformCryptoImports("current registry has no platform crypto imports"),
    CurrentRegistryCannotExecuteProviderOperations("current registry cannot execute provider operations"),
    CurrentRegistryCannotRunKats("current registry cannot run KATs"),
    CurrentRegistryCannotCallRandomness("current registry cannot call randomness"),
    CurrentRegistryCannotCallKdfAeadHkdfHmac("current registry cannot call KDF/AEAD/HKDF/HMAC"),
    CurrentRegistryCannotPromoteDependencyBuildEvidence(
        "current registry cannot promote dependency/build evidence",
    ),
    CurrentRegistryCannotPromotePackagingSkeletonAuditMatrixEvidence(
        "current registry cannot promote packaging/skeleton/audit/matrix evidence",
    ),
    WarningUserConsentTestOnlyCannotOverride("warning/user-consent/test-only evidence cannot override"),
    ProductionProviderSelectableFalse("productionProviderSelectable remains false"),
    MainnetDisabled("mainnet remains disabled"),
}

enum class SkaldVaultV1ProviderRegistryIsolationBlocker(val label: String) {
    RegistryIsolationGuardStillDisabled("registry isolation guard still disabled"),
    ModelEvidenceOnly("model evidence only"),
    ProviderSelectionLockedToDisabledProvider("provider selection locked to disabled provider"),
    ProductionProviderSelectableFalse("productionProviderSelectable is false"),
    SkeletonExcludedFromRegistry("skeleton excluded from registry"),
    CandidateProvidersExcludedFromRegistry("candidate providers excluded from registry"),
    FactoriesExcludedFromRegistry("factories excluded from registry"),
    ProviderConstructorsExcludedFromRegistry("provider constructors excluded from registry"),
    RegistryPromotionBlocked("registry promotion blocked"),
    RegistryRuntimeInstantiationBlocked("registry runtime instantiation blocked"),
    ProviderOperationAuthorizationBlocked("provider operation authorization blocked"),
    RuntimeRandomnessAuthorizationBlocked("runtime randomness authorization blocked"),
    KdfCalibrationAuthorizationBlocked("KDF calibration authorization blocked"),
    SecureStorageAuthorizationBlocked("secure-storage authorization blocked"),
    CreationAuthorizationBlocked("creation authorization blocked"),
    UnlockAuthorizationBlocked("unlock authorization blocked"),
    ProviderCandidatePackagingStillDisabled("provider candidate packaging still disabled"),
    ProviderDependencyBuildStillDisabled("provider dependency build still disabled"),
    ProviderInterfaceAuditEvidenceOnly("provider interface audit evidence only"),
    NonSelectableProviderSkeletonStillDisabled("non-selectable provider skeleton still disabled"),
    ProviderSelectionPromotionBlocked("provider selection promotion blocked"),
    AuthorizationReadinessMatrixBlocked("authorization/readiness matrix blocked"),
    ProviderAcceptanceMissing("provider acceptance missing"),
    DependencyProbeInsufficient("dependency probe insufficient"),
    KatExecutionUnavailable("KAT execution unavailable"),
    RegistryHandlesRejected("registry handles rejected"),
    RegistryObjectsRejected("registry objects rejected"),
    CryptoObjectsRejected("crypto objects rejected"),
    ByteMaterialRejected("byte material rejected"),
    SecretMaterialRejected("secret material rejected"),
    PlatformCryptoImportsForbidden("platform crypto imports forbidden"),
    ProviderOperationExecutionForbidden("provider operation execution forbidden"),
    WarningOnlyEvidenceCannotAuthorize("warning-only evidence cannot authorize"),
    UserConsentCannotOverride("user consent cannot override"),
    TestOnlyEvidenceCannotAuthorizeProduction("test-only evidence cannot authorize production"),
    ReleaseReviewMissing("release review missing"),
    MainnetDisabled("mainnet disabled"),
}

enum class SkaldVaultV1ProviderRegistryIsolationRedactionClass(val label: String) {
    PolicyIdsOnly("policy ids only"),
    RegistryTopicOnly("registry topic only"),
    RiskCategoryOnly("risk category only"),
    RequiredPropertyOnly("required property only"),
    BoundaryAndBlockerClassesOnly("boundary and blocker classes only"),
    NoDiagnosticPayload("no diagnostic payload"),
}

data class SkaldVaultV1ProviderRegistryIsolationCapability(
    val providerRegistryIsolationModeled: Boolean,
    val registryContainsOnlyDisabledProvider: Boolean,
    val registryContainsProviderSkeleton: Boolean,
    val registryContainsCandidateProvider: Boolean,
    val registryContainsProviderFactory: Boolean,
    val registryCreatesProviderInstances: Boolean,
    val registryCanSelectNonDisabledProvider: Boolean,
    val registryCanSetProductionProviderSelectable: Boolean,
    val registryCanPromoteCandidate: Boolean,
    val registryCanExecuteProviderOperations: Boolean,
    val registryCanRunKat: Boolean,
    val registryCanUseRandomness: Boolean,
    val registryCanRunKdf: Boolean,
    val registryCanRunAead: Boolean,
    val registryCanWrapKeys: Boolean,
    val registryCanCreateVault: Boolean,
    val registryCanUnlockVault: Boolean,
    val registryCanPersistVault: Boolean,
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
        val StillDisabled = SkaldVaultV1ProviderRegistryIsolationCapability(
            providerRegistryIsolationModeled = true,
            registryContainsOnlyDisabledProvider = true,
            registryContainsProviderSkeleton = false,
            registryContainsCandidateProvider = false,
            registryContainsProviderFactory = false,
            registryCreatesProviderInstances = false,
            registryCanSelectNonDisabledProvider = false,
            registryCanSetProductionProviderSelectable = false,
            registryCanPromoteCandidate = false,
            registryCanExecuteProviderOperations = false,
            registryCanRunKat = false,
            registryCanUseRandomness = false,
            registryCanRunKdf = false,
            registryCanRunAead = false,
            registryCanWrapKeys = false,
            registryCanCreateVault = false,
            registryCanUnlockVault = false,
            registryCanPersistVault = false,
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

class SkaldVaultV1ProviderRegistryIsolationPolicyToken private constructor(
    val policyId: String,
    val topic: SkaldVaultV1ProviderRegistryIsolationTopic?,
    val risk: SkaldVaultV1ProviderRegistryIsolationRisk?,
    val requiredProperty: SkaldVaultV1ProviderRegistryIsolationRequiredProperty?,
) {
    val containsProviderHandle: Boolean = false
    val containsRegistryObject: Boolean = false
    val containsFactoryObject: Boolean = false
    val containsCryptoObject: Boolean = false
    val containsByteMaterial: Boolean = false
    val containsPathOrRootText: Boolean = false
    val containsStorageIdentifier: Boolean = false
    val containsSecretMaterial: Boolean = false

    override fun toString(): String =
        "SkaldVaultV1ProviderRegistryIsolationPolicyToken(" +
            "policyId=$policyId, " +
            "topic=$topic, " +
            "risk=$risk, " +
            "requiredProperty=$requiredProperty, " +
            "providerHandle=<redacted>, " +
            "registryObject=<redacted>, " +
            "factoryObject=<redacted>, " +
            "cryptoObject=<redacted>, " +
            "byteMaterial=<redacted>, " +
            "pathOrRoot=<redacted>, " +
            "storageIdentifier=<redacted>, " +
            "secretMaterial=<redacted>" +
            ")"

    companion object {
        fun redacted(
            policyId: String,
            topic: SkaldVaultV1ProviderRegistryIsolationTopic?,
            risk: SkaldVaultV1ProviderRegistryIsolationRisk?,
            requiredProperty: SkaldVaultV1ProviderRegistryIsolationRequiredProperty?,
        ): SkaldVaultV1ProviderRegistryIsolationPolicyToken {
            return SkaldVaultV1ProviderRegistryIsolationPolicyToken(
                policyId = policyId,
                topic = topic,
                risk = risk,
                requiredProperty = requiredProperty,
            )
        }
    }
}

class SkaldVaultV1ProviderRegistryIsolationGuardRequest private constructor(
    val source: SkaldVaultV1ProviderRegistryIsolationSource,
    val topic: SkaldVaultV1ProviderRegistryIsolationTopic?,
    val risk: SkaldVaultV1ProviderRegistryIsolationRisk?,
    val requiredProperty: SkaldVaultV1ProviderRegistryIsolationRequiredProperty?,
    private val providerSelectionResult: VaultCryptoProviderSelectionResult?,
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
        "SkaldVaultV1ProviderRegistryIsolationGuardRequest(" +
            "source=$source, " +
            "topic=$topic, " +
            "risk=$risk, " +
            "requiredProperty=$requiredProperty, " +
            "providerSelection=<redacted>, " +
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
        ): SkaldVaultV1ProviderRegistryIsolationGuardRequest =
            SkaldVaultV1ProviderRegistryIsolationGuardRequest(
                source = SkaldVaultV1ProviderRegistryIsolationSource.CurrentTypedEvidence,
                topic = null,
                risk = null,
                requiredProperty = null,
                providerSelectionResult = providerSelectionResult,
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
            topic: SkaldVaultV1ProviderRegistryIsolationTopic,
        ): SkaldVaultV1ProviderRegistryIsolationGuardRequest =
            SkaldVaultV1ProviderRegistryIsolationGuardRequest(
                source = SkaldVaultV1ProviderRegistryIsolationSource.RegistryTopicAudit,
                topic = topic,
                risk = null,
                requiredProperty = null,
                providerSelectionResult = null,
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
            risk: SkaldVaultV1ProviderRegistryIsolationRisk,
        ): SkaldVaultV1ProviderRegistryIsolationGuardRequest =
            SkaldVaultV1ProviderRegistryIsolationGuardRequest(
                source = SkaldVaultV1ProviderRegistryIsolationSource.RegistryRiskAudit,
                topic = null,
                risk = risk,
                requiredProperty = null,
                providerSelectionResult = null,
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
            requiredProperty: SkaldVaultV1ProviderRegistryIsolationRequiredProperty,
        ): SkaldVaultV1ProviderRegistryIsolationGuardRequest =
            SkaldVaultV1ProviderRegistryIsolationGuardRequest(
                source = SkaldVaultV1ProviderRegistryIsolationSource.RequiredPropertyAudit,
                topic = null,
                risk = null,
                requiredProperty = requiredProperty,
                providerSelectionResult = null,
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

        fun evidenceInteractionAudit(): SkaldVaultV1ProviderRegistryIsolationGuardRequest =
            SkaldVaultV1ProviderRegistryIsolationGuardRequest(
                source = SkaldVaultV1ProviderRegistryIsolationSource.EvidenceInteractionAudit,
                topic = null,
                risk = null,
                requiredProperty = null,
                providerSelectionResult = null,
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

data class SkaldVaultV1ProviderRegistryIsolationTopicRow(
    val topic: SkaldVaultV1ProviderRegistryIsolationTopic,
    val statuses: Set<SkaldVaultV1ProviderRegistryIsolationStatus>,
    val blockers: Set<SkaldVaultV1ProviderRegistryIsolationBlocker>,
    val modeled: Boolean,
    val evidenceOnly: Boolean,
    val excludedFromRegistry: Boolean,
    val registryEntryAllowed: Boolean,
    val nonDisabledSelectionAllowed: Boolean,
    val runtimeInstantiationAllowed: Boolean,
    val redactionClass: SkaldVaultV1ProviderRegistryIsolationRedactionClass,
)

data class SkaldVaultV1ProviderRegistryIsolationRiskRow(
    val risk: SkaldVaultV1ProviderRegistryIsolationRisk,
    val statuses: Set<SkaldVaultV1ProviderRegistryIsolationStatus>,
    val blockers: Set<SkaldVaultV1ProviderRegistryIsolationBlocker>,
    val rejected: Boolean,
    val accepted: Boolean,
    val canCreateRegistryEntry: Boolean,
    val canInstantiate: Boolean,
    val canExecute: Boolean,
    val canChangeSelectability: Boolean,
    val redactionClass: SkaldVaultV1ProviderRegistryIsolationRedactionClass,
)

data class SkaldVaultV1ProviderRegistryIsolationRequiredPropertyRow(
    val requiredProperty: SkaldVaultV1ProviderRegistryIsolationRequiredProperty,
    val statuses: Set<SkaldVaultV1ProviderRegistryIsolationStatus>,
    val blockers: Set<SkaldVaultV1ProviderRegistryIsolationBlocker>,
    val satisfiedForCurrentRegistry: Boolean,
    val futureExplicitReviewRequired: Boolean,
    val runtimeAvailable: Boolean,
    val nonDisabledSelectionAllowed: Boolean,
    val redactionClass: SkaldVaultV1ProviderRegistryIsolationRedactionClass,
)

data class SkaldVaultV1ProviderRegistryIsolationSummary(
    val policyId: String,
    val policyVersion: Int,
    val statuses: Set<SkaldVaultV1ProviderRegistryIsolationStatus>,
    val topics: Set<SkaldVaultV1ProviderRegistryIsolationTopic>,
    val risks: Set<SkaldVaultV1ProviderRegistryIsolationRisk>,
    val requiredProperties: Set<SkaldVaultV1ProviderRegistryIsolationRequiredProperty>,
    val blockers: Set<SkaldVaultV1ProviderRegistryIsolationBlocker>,
    val redactionClasses: Set<SkaldVaultV1ProviderRegistryIsolationRedactionClass>,
    val capability: SkaldVaultV1ProviderRegistryIsolationCapability,
    val stillDisabled: Boolean,
    val evidenceOnly: Boolean,
    val disabledProviderOnly: Boolean,
    val excludesSkeletonAndCandidates: Boolean,
    val blocksRegistryPromotion: Boolean,
)

data class SkaldVaultV1ProviderRegistryIsolationEvidence(
    val policyId: String,
    val policyVersion: Int,
    val source: SkaldVaultV1ProviderRegistryIsolationSource,
    val status: SkaldVaultV1ProviderRegistryIsolationStatus,
    val topicRows: List<SkaldVaultV1ProviderRegistryIsolationTopicRow>,
    val riskRows: List<SkaldVaultV1ProviderRegistryIsolationRiskRow>,
    val requiredPropertyRows: List<SkaldVaultV1ProviderRegistryIsolationRequiredPropertyRow>,
    val blockers: Set<SkaldVaultV1ProviderRegistryIsolationBlocker>,
    val capability: SkaldVaultV1ProviderRegistryIsolationCapability,
    val policyTokenEvidence: SkaldVaultV1ProviderRegistryIsolationPolicyToken,
    val providerSelectionEvidenceConsumed: Boolean,
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
    val providerRegistryIsolationGuardModeled: Boolean = true,
    val providerRegistryIsolationGuardStillDisabled: Boolean = true,
    val providerRegistryIsolationConfirmsDisabledProviderOnly: Boolean = true,
    val providerRegistryIsolationExcludesSkeleton: Boolean = true,
    val providerRegistryIsolationExcludesCandidateProviders: Boolean = true,
    val providerRegistryIsolationExcludesFactories: Boolean = true,
    val providerRegistryIsolationDoesNotRegisterProvider: Boolean = true,
    val providerRegistryIsolationDoesNotEnableProviderSelection: Boolean = true,
    val providerRegistryIsolationDoesNotRunCrypto: Boolean = true,
    val providerRegistryIsolationDoesNotEnableCreation: Boolean = true,
    val providerRegistryIsolationDoesNotEnableUnlock: Boolean = true,
    val providerRegistryIsolationDoesNotEnablePersistence: Boolean = true,
    val providerRegistryIsolationModeled: Boolean = true,
    val registryContainsOnlyDisabledProvider: Boolean = true,
    val registryContainsProviderSkeleton: Boolean = false,
    val registryContainsCandidateProvider: Boolean = false,
    val registryContainsProviderFactory: Boolean = false,
    val registryCreatesProviderInstances: Boolean = false,
    val registryCanSelectNonDisabledProvider: Boolean = false,
    val registryCanSetProductionProviderSelectable: Boolean = false,
    val registryCanPromoteCandidate: Boolean = false,
    val registryCanExecuteProviderOperations: Boolean = false,
    val registryCanRunKat: Boolean = false,
    val registryCanUseRandomness: Boolean = false,
    val registryCanRunKdf: Boolean = false,
    val registryCanRunAead: Boolean = false,
    val registryCanWrapKeys: Boolean = false,
    val registryCanCreateVault: Boolean = false,
    val registryCanUnlockVault: Boolean = false,
    val registryCanPersistVault: Boolean = false,
    val providerFactoryAdded: Boolean = false,
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
        "SkaldVaultV1ProviderRegistryIsolationEvidence(" +
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

sealed class SkaldVaultV1ProviderRegistryIsolationGuardResult<out T> {
    data class Blocked<out T>(
        val value: T,
    ) : SkaldVaultV1ProviderRegistryIsolationGuardResult<T>() {
        override fun toString(): String =
            "SkaldVaultV1ProviderRegistryIsolationGuardResult.Blocked(" +
                "value=<redacted-provider-registry-isolation-evidence>)"
    }
}

object SkaldVaultV1ProviderRegistryIsolationGuardPolicy :
    SkaldVaultV1ProviderRegistryIsolationGuardBoundary {
    const val POLICY_ID = "skald-vault-v1-provider-registry-isolation-guard-v1"
    const val POLICY_VERSION = 1

    override fun evaluate(
        request: SkaldVaultV1ProviderRegistryIsolationGuardRequest,
    ): SkaldVaultV1ProviderRegistryIsolationGuardResult<SkaldVaultV1ProviderRegistryIsolationEvidence> {
        val topicRows = topicRowsFor(request.topic)
        val riskRows = riskRowsFor(request.risk)
        val requiredPropertyRows = requiredPropertyRowsFor(request.requiredProperty)
        return SkaldVaultV1ProviderRegistryIsolationGuardResult.Blocked(
            SkaldVaultV1ProviderRegistryIsolationEvidence(
                policyId = POLICY_ID,
                policyVersion = POLICY_VERSION,
                source = request.source,
                status = SkaldVaultV1ProviderRegistryIsolationStatus.StillDisabled,
                topicRows = topicRows,
                riskRows = riskRows,
                requiredPropertyRows = requiredPropertyRows,
                blockers = topicRows.flatMap { it.blockers }.toSet() +
                    riskRows.flatMap { it.blockers }.toSet() +
                    requiredPropertyRows.flatMap { it.blockers }.toSet() +
                    baseBlockers,
                capability = SkaldVaultV1ProviderRegistryIsolationCapability.StillDisabled,
                policyTokenEvidence = SkaldVaultV1ProviderRegistryIsolationPolicyToken.redacted(
                    policyId = POLICY_ID,
                    topic = request.topic,
                    risk = request.risk,
                    requiredProperty = request.requiredProperty,
                ),
                providerSelectionEvidenceConsumed = request.providerSelectionEvidenceSupplied,
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

    fun currentPolicySummary(): SkaldVaultV1ProviderRegistryIsolationSummary =
        SkaldVaultV1ProviderRegistryIsolationSummary(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            statuses = SkaldVaultV1ProviderRegistryIsolationStatus.entries.toSet(),
            topics = SkaldVaultV1ProviderRegistryIsolationTopic.entries.toSet(),
            risks = SkaldVaultV1ProviderRegistryIsolationRisk.entries.toSet(),
            requiredProperties = SkaldVaultV1ProviderRegistryIsolationRequiredProperty.entries.toSet(),
            blockers = SkaldVaultV1ProviderRegistryIsolationBlocker.entries.toSet(),
            redactionClasses = SkaldVaultV1ProviderRegistryIsolationRedactionClass.entries.toSet(),
            capability = SkaldVaultV1ProviderRegistryIsolationCapability.StillDisabled,
            stillDisabled = true,
            evidenceOnly = true,
            disabledProviderOnly = true,
            excludesSkeletonAndCandidates = true,
            blocksRegistryPromotion = true,
        )

    private fun topicRowsFor(
        topic: SkaldVaultV1ProviderRegistryIsolationTopic?,
    ): List<SkaldVaultV1ProviderRegistryIsolationTopicRow> {
        val rows = SkaldVaultV1ProviderRegistryIsolationTopic.entries.map { currentTopic ->
            topicRow(currentTopic)
        }
        return topic?.let { requested -> rows.filter { it.topic == requested } } ?: rows
    }

    private fun riskRowsFor(
        risk: SkaldVaultV1ProviderRegistryIsolationRisk?,
    ): List<SkaldVaultV1ProviderRegistryIsolationRiskRow> {
        val rows = SkaldVaultV1ProviderRegistryIsolationRisk.entries.map { currentRisk ->
            riskRow(currentRisk)
        }
        return risk?.let { requested -> rows.filter { it.risk == requested } } ?: rows
    }

    private fun requiredPropertyRowsFor(
        requiredProperty: SkaldVaultV1ProviderRegistryIsolationRequiredProperty?,
    ): List<SkaldVaultV1ProviderRegistryIsolationRequiredPropertyRow> {
        val rows = SkaldVaultV1ProviderRegistryIsolationRequiredProperty.entries.map { currentProperty ->
            requiredPropertyRow(currentProperty)
        }
        return requiredProperty?.let { requested ->
            rows.filter { it.requiredProperty == requested }
        } ?: rows
    }

    private fun topicRow(
        topic: SkaldVaultV1ProviderRegistryIsolationTopic,
    ): SkaldVaultV1ProviderRegistryIsolationTopicRow =
        SkaldVaultV1ProviderRegistryIsolationTopicRow(
            topic = topic,
            statuses = baseStatuses + topicStatus(topic),
            blockers = topicBlockers(topic) + baseBlockers,
            modeled = true,
            evidenceOnly = true,
            excludedFromRegistry =
                topic != SkaldVaultV1ProviderRegistryIsolationTopic.ActiveProviderSelectionRegistry &&
                    topic != SkaldVaultV1ProviderRegistryIsolationTopic.DisabledProviderSelected,
            registryEntryAllowed = topic == SkaldVaultV1ProviderRegistryIsolationTopic.DisabledProviderSelected,
            nonDisabledSelectionAllowed = false,
            runtimeInstantiationAllowed = false,
            redactionClass = SkaldVaultV1ProviderRegistryIsolationRedactionClass.RegistryTopicOnly,
        )

    private fun riskRow(
        risk: SkaldVaultV1ProviderRegistryIsolationRisk,
    ): SkaldVaultV1ProviderRegistryIsolationRiskRow =
        SkaldVaultV1ProviderRegistryIsolationRiskRow(
            risk = risk,
            statuses = baseStatuses + riskStatus(risk),
            blockers = riskBlockers(risk) + baseBlockers,
            rejected = true,
            accepted = false,
            canCreateRegistryEntry = false,
            canInstantiate = false,
            canExecute = false,
            canChangeSelectability = false,
            redactionClass = SkaldVaultV1ProviderRegistryIsolationRedactionClass.RiskCategoryOnly,
        )

    private fun requiredPropertyRow(
        requiredProperty: SkaldVaultV1ProviderRegistryIsolationRequiredProperty,
    ): SkaldVaultV1ProviderRegistryIsolationRequiredPropertyRow =
        SkaldVaultV1ProviderRegistryIsolationRequiredPropertyRow(
            requiredProperty = requiredProperty,
            statuses = baseStatuses + SkaldVaultV1ProviderRegistryIsolationStatus.RegistryIsolated,
            blockers = requiredPropertyBlockers(requiredProperty) + baseBlockers,
            satisfiedForCurrentRegistry = true,
            futureExplicitReviewRequired = true,
            runtimeAvailable = false,
            nonDisabledSelectionAllowed = false,
            redactionClass = SkaldVaultV1ProviderRegistryIsolationRedactionClass.RequiredPropertyOnly,
        )

    private fun topicStatus(
        topic: SkaldVaultV1ProviderRegistryIsolationTopic,
    ): SkaldVaultV1ProviderRegistryIsolationStatus =
        when (topic) {
            SkaldVaultV1ProviderRegistryIsolationTopic.ActiveProviderSelectionRegistry,
            SkaldVaultV1ProviderRegistryIsolationTopic.DisabledProviderSelected ->
                SkaldVaultV1ProviderRegistryIsolationStatus.DisabledProviderOnly
            SkaldVaultV1ProviderRegistryIsolationTopic.NonSelectableSkeletonExcluded ->
                SkaldVaultV1ProviderRegistryIsolationStatus.SkeletonExcluded
            SkaldVaultV1ProviderRegistryIsolationTopic.CandidatePackagingEvidenceExcludedFromRegistry,
            SkaldVaultV1ProviderRegistryIsolationTopic.DependencyBuildEvidenceExcludedFromRegistry ->
                SkaldVaultV1ProviderRegistryIsolationStatus.CandidateExcluded
            else -> SkaldVaultV1ProviderRegistryIsolationStatus.PromotionBlocked
        }

    private fun riskStatus(
        risk: SkaldVaultV1ProviderRegistryIsolationRisk,
    ): SkaldVaultV1ProviderRegistryIsolationStatus =
        when (risk) {
            SkaldVaultV1ProviderRegistryIsolationRisk.RegistryCreatesProviderInstance ->
                SkaldVaultV1ProviderRegistryIsolationStatus.RuntimeInstantiationBlocked
            SkaldVaultV1ProviderRegistryIsolationRisk.RegistryCallsProviderOperations,
            SkaldVaultV1ProviderRegistryIsolationRisk.RegistryRunsKats,
            SkaldVaultV1ProviderRegistryIsolationRisk.RegistryReachesRandomnessKdfAead ->
                SkaldVaultV1ProviderRegistryIsolationStatus.OperationExecutionBlocked
            SkaldVaultV1ProviderRegistryIsolationRisk.RegistryEnablesMainnet ->
                SkaldVaultV1ProviderRegistryIsolationStatus.MainnetBlocked
            SkaldVaultV1ProviderRegistryIsolationRisk.DependencyBuildEvidenceTreatedAsRegistryEntry,
            SkaldVaultV1ProviderRegistryIsolationRisk.PackagingEvidenceTreatedAsRegistryEntry,
            SkaldVaultV1ProviderRegistryIsolationRisk.InterfaceAuditTreatedAsRegistryEntry,
            SkaldVaultV1ProviderRegistryIsolationRisk.PromotionBlockerTreatedAsRegistryEntry,
            SkaldVaultV1ProviderRegistryIsolationRisk.MatrixEvidenceTreatedAsRegistryEntry ->
                SkaldVaultV1ProviderRegistryIsolationStatus.CandidateExcluded
            else -> SkaldVaultV1ProviderRegistryIsolationStatus.PromotionBlocked
        }

    private fun topicBlockers(
        topic: SkaldVaultV1ProviderRegistryIsolationTopic,
    ): Set<SkaldVaultV1ProviderRegistryIsolationBlocker> =
        when (topic) {
            SkaldVaultV1ProviderRegistryIsolationTopic.ActiveProviderSelectionRegistry,
            SkaldVaultV1ProviderRegistryIsolationTopic.DisabledProviderSelected ->
                setOf(SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderSelectionLockedToDisabledProvider)
            SkaldVaultV1ProviderRegistryIsolationTopic.NonSelectableSkeletonExcluded ->
                setOf(
                    SkaldVaultV1ProviderRegistryIsolationBlocker.SkeletonExcludedFromRegistry,
                    SkaldVaultV1ProviderRegistryIsolationBlocker.NonSelectableProviderSkeletonStillDisabled,
                )
            SkaldVaultV1ProviderRegistryIsolationTopic.CandidatePackagingEvidenceExcludedFromRegistry ->
                setOf(
                    SkaldVaultV1ProviderRegistryIsolationBlocker.CandidateProvidersExcludedFromRegistry,
                    SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderCandidatePackagingStillDisabled,
                )
            SkaldVaultV1ProviderRegistryIsolationTopic.DependencyBuildEvidenceExcludedFromRegistry ->
                setOf(
                    SkaldVaultV1ProviderRegistryIsolationBlocker.CandidateProvidersExcludedFromRegistry,
                    SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderDependencyBuildStillDisabled,
                )
            SkaldVaultV1ProviderRegistryIsolationTopic.InterfaceAuditEvidenceExcludedFromRegistry ->
                setOf(SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderInterfaceAuditEvidenceOnly)
            SkaldVaultV1ProviderRegistryIsolationTopic.PromotionBlockerEvidenceExcludedFromRegistry ->
                setOf(SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderSelectionPromotionBlocked)
            SkaldVaultV1ProviderRegistryIsolationTopic.AuthorizationReadinessMatrixEvidenceExcludedFromRegistry ->
                setOf(SkaldVaultV1ProviderRegistryIsolationBlocker.AuthorizationReadinessMatrixBlocked)
            SkaldVaultV1ProviderRegistryIsolationTopic.ProviderOperationAuthorizationExcludedFromRegistryPromotion ->
                setOf(SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderOperationAuthorizationBlocked)
            SkaldVaultV1ProviderRegistryIsolationTopic.RuntimeRandomnessAuthorizationExcludedFromRegistryPromotion ->
                setOf(SkaldVaultV1ProviderRegistryIsolationBlocker.RuntimeRandomnessAuthorizationBlocked)
            SkaldVaultV1ProviderRegistryIsolationTopic.KdfCalibrationAuthorizationExcludedFromRegistryPromotion ->
                setOf(SkaldVaultV1ProviderRegistryIsolationBlocker.KdfCalibrationAuthorizationBlocked)
            SkaldVaultV1ProviderRegistryIsolationTopic.SecureStorageAuthorizationExcludedFromRegistryPromotion ->
                setOf(SkaldVaultV1ProviderRegistryIsolationBlocker.SecureStorageAuthorizationBlocked)
            SkaldVaultV1ProviderRegistryIsolationTopic.CreationUnlockAuthorizationExcludedFromRegistryPromotion ->
                setOf(
                    SkaldVaultV1ProviderRegistryIsolationBlocker.CreationAuthorizationBlocked,
                    SkaldVaultV1ProviderRegistryIsolationBlocker.UnlockAuthorizationBlocked,
                )
            SkaldVaultV1ProviderRegistryIsolationTopic.TestOnlyEvidenceExcludedFromProductionRegistry ->
                setOf(SkaldVaultV1ProviderRegistryIsolationBlocker.TestOnlyEvidenceCannotAuthorizeProduction)
            SkaldVaultV1ProviderRegistryIsolationTopic.WarningOnlyEvidenceExcludedFromRegistryPromotion ->
                setOf(SkaldVaultV1ProviderRegistryIsolationBlocker.WarningOnlyEvidenceCannotAuthorize)
            SkaldVaultV1ProviderRegistryIsolationTopic.UserConsentEvidenceExcludedFromRegistryPromotion ->
                setOf(SkaldVaultV1ProviderRegistryIsolationBlocker.UserConsentCannotOverride)
            SkaldVaultV1ProviderRegistryIsolationTopic.ReleaseMainnetEvidenceFutureExplicitReviewOnly ->
                setOf(
                    SkaldVaultV1ProviderRegistryIsolationBlocker.ReleaseReviewMissing,
                    SkaldVaultV1ProviderRegistryIsolationBlocker.MainnetDisabled,
                )
        }

    private fun riskBlockers(
        risk: SkaldVaultV1ProviderRegistryIsolationRisk,
    ): Set<SkaldVaultV1ProviderRegistryIsolationBlocker> =
        when (risk) {
            SkaldVaultV1ProviderRegistryIsolationRisk.SkeletonReferencedBySelectionCode ->
                setOf(SkaldVaultV1ProviderRegistryIsolationBlocker.SkeletonExcludedFromRegistry)
            SkaldVaultV1ProviderRegistryIsolationRisk.CandidateFamilyReferencedBySelectionCode,
            SkaldVaultV1ProviderRegistryIsolationRisk.RegistryContainsNonDisabledProviderId ->
                setOf(SkaldVaultV1ProviderRegistryIsolationBlocker.CandidateProvidersExcludedFromRegistry)
            SkaldVaultV1ProviderRegistryIsolationRisk.DependencyBuildEvidenceTreatedAsRegistryEntry ->
                setOf(SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderDependencyBuildStillDisabled)
            SkaldVaultV1ProviderRegistryIsolationRisk.PackagingEvidenceTreatedAsRegistryEntry ->
                setOf(SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderCandidatePackagingStillDisabled)
            SkaldVaultV1ProviderRegistryIsolationRisk.InterfaceAuditTreatedAsRegistryEntry ->
                setOf(SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderInterfaceAuditEvidenceOnly)
            SkaldVaultV1ProviderRegistryIsolationRisk.PromotionBlockerTreatedAsRegistryEntry ->
                setOf(SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderSelectionPromotionBlocked)
            SkaldVaultV1ProviderRegistryIsolationRisk.MatrixEvidenceTreatedAsRegistryEntry ->
                setOf(SkaldVaultV1ProviderRegistryIsolationBlocker.AuthorizationReadinessMatrixBlocked)
            SkaldVaultV1ProviderRegistryIsolationRisk.TestOnlyProviderReferencedByProductionSelection ->
                setOf(SkaldVaultV1ProviderRegistryIsolationBlocker.TestOnlyEvidenceCannotAuthorizeProduction)
            SkaldVaultV1ProviderRegistryIsolationRisk.ProductionProviderSelectableAccidentallyTrue,
            SkaldVaultV1ProviderRegistryIsolationRisk.ProviderSelectableAccidentallyTrue ->
                setOf(SkaldVaultV1ProviderRegistryIsolationBlocker.ProductionProviderSelectableFalse)
            SkaldVaultV1ProviderRegistryIsolationRisk.RegistryCreatesProviderInstance ->
                setOf(
                    SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderConstructorsExcludedFromRegistry,
                    SkaldVaultV1ProviderRegistryIsolationBlocker.RegistryRuntimeInstantiationBlocked,
                )
            SkaldVaultV1ProviderRegistryIsolationRisk.RegistryExposesProviderHandles ->
                setOf(
                    SkaldVaultV1ProviderRegistryIsolationBlocker.RegistryHandlesRejected,
                    SkaldVaultV1ProviderRegistryIsolationBlocker.RegistryObjectsRejected,
                )
            SkaldVaultV1ProviderRegistryIsolationRisk.RegistryExposesCryptoObjects ->
                setOf(SkaldVaultV1ProviderRegistryIsolationBlocker.CryptoObjectsRejected)
            SkaldVaultV1ProviderRegistryIsolationRisk.RegistryImportsPlatformCryptoApis ->
                setOf(SkaldVaultV1ProviderRegistryIsolationBlocker.PlatformCryptoImportsForbidden)
            SkaldVaultV1ProviderRegistryIsolationRisk.RegistryCallsProviderOperations ->
                setOf(
                    SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderOperationAuthorizationBlocked,
                    SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderOperationExecutionForbidden,
                )
            SkaldVaultV1ProviderRegistryIsolationRisk.RegistryRunsKats ->
                setOf(SkaldVaultV1ProviderRegistryIsolationBlocker.KatExecutionUnavailable)
            SkaldVaultV1ProviderRegistryIsolationRisk.RegistryReachesRandomnessKdfAead ->
                setOf(
                    SkaldVaultV1ProviderRegistryIsolationBlocker.RuntimeRandomnessAuthorizationBlocked,
                    SkaldVaultV1ProviderRegistryIsolationBlocker.KdfCalibrationAuthorizationBlocked,
                    SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderOperationAuthorizationBlocked,
                )
            SkaldVaultV1ProviderRegistryIsolationRisk.RegistryEnablesVaultCreationUnlockPersistence ->
                setOf(
                    SkaldVaultV1ProviderRegistryIsolationBlocker.CreationAuthorizationBlocked,
                    SkaldVaultV1ProviderRegistryIsolationBlocker.UnlockAuthorizationBlocked,
                    SkaldVaultV1ProviderRegistryIsolationBlocker.SecureStorageAuthorizationBlocked,
                )
            SkaldVaultV1ProviderRegistryIsolationRisk.RegistryEnablesMainnet ->
                setOf(SkaldVaultV1ProviderRegistryIsolationBlocker.MainnetDisabled)
        }

    private fun requiredPropertyBlockers(
        requiredProperty: SkaldVaultV1ProviderRegistryIsolationRequiredProperty,
    ): Set<SkaldVaultV1ProviderRegistryIsolationBlocker> =
        when (requiredProperty) {
            SkaldVaultV1ProviderRegistryIsolationRequiredProperty.CurrentRegistrySelectsOnlyDisabledProvider ->
                setOf(SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderSelectionLockedToDisabledProvider)
            SkaldVaultV1ProviderRegistryIsolationRequiredProperty.CurrentRegistryContainsNoCandidateEntries ->
                setOf(SkaldVaultV1ProviderRegistryIsolationBlocker.CandidateProvidersExcludedFromRegistry)
            SkaldVaultV1ProviderRegistryIsolationRequiredProperty.CurrentRegistryContainsNoSkeletonEntries ->
                setOf(SkaldVaultV1ProviderRegistryIsolationBlocker.SkeletonExcludedFromRegistry)
            SkaldVaultV1ProviderRegistryIsolationRequiredProperty.CurrentRegistryContainsNoProviderFactories ->
                setOf(SkaldVaultV1ProviderRegistryIsolationBlocker.FactoriesExcludedFromRegistry)
            SkaldVaultV1ProviderRegistryIsolationRequiredProperty.CurrentRegistryContainsNoProviderConstructors ->
                setOf(SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderConstructorsExcludedFromRegistry)
            SkaldVaultV1ProviderRegistryIsolationRequiredProperty.CurrentRegistryExposesNoProviderHandles ->
                setOf(SkaldVaultV1ProviderRegistryIsolationBlocker.RegistryHandlesRejected)
            SkaldVaultV1ProviderRegistryIsolationRequiredProperty.CurrentRegistryExposesNoCryptoObjects ->
                setOf(SkaldVaultV1ProviderRegistryIsolationBlocker.CryptoObjectsRejected)
            SkaldVaultV1ProviderRegistryIsolationRequiredProperty.CurrentRegistryHasNoPlatformCryptoImports ->
                setOf(SkaldVaultV1ProviderRegistryIsolationBlocker.PlatformCryptoImportsForbidden)
            SkaldVaultV1ProviderRegistryIsolationRequiredProperty.CurrentRegistryCannotExecuteProviderOperations ->
                setOf(SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderOperationExecutionForbidden)
            SkaldVaultV1ProviderRegistryIsolationRequiredProperty.CurrentRegistryCannotRunKats ->
                setOf(SkaldVaultV1ProviderRegistryIsolationBlocker.KatExecutionUnavailable)
            SkaldVaultV1ProviderRegistryIsolationRequiredProperty.CurrentRegistryCannotCallRandomness ->
                setOf(SkaldVaultV1ProviderRegistryIsolationBlocker.RuntimeRandomnessAuthorizationBlocked)
            SkaldVaultV1ProviderRegistryIsolationRequiredProperty.CurrentRegistryCannotCallKdfAeadHkdfHmac ->
                setOf(
                    SkaldVaultV1ProviderRegistryIsolationBlocker.KdfCalibrationAuthorizationBlocked,
                    SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderOperationAuthorizationBlocked,
                )
            SkaldVaultV1ProviderRegistryIsolationRequiredProperty.CurrentRegistryCannotPromoteDependencyBuildEvidence ->
                setOf(SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderDependencyBuildStillDisabled)
            SkaldVaultV1ProviderRegistryIsolationRequiredProperty
                .CurrentRegistryCannotPromotePackagingSkeletonAuditMatrixEvidence ->
                setOf(
                    SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderCandidatePackagingStillDisabled,
                    SkaldVaultV1ProviderRegistryIsolationBlocker.NonSelectableProviderSkeletonStillDisabled,
                    SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderInterfaceAuditEvidenceOnly,
                    SkaldVaultV1ProviderRegistryIsolationBlocker.AuthorizationReadinessMatrixBlocked,
                )
            SkaldVaultV1ProviderRegistryIsolationRequiredProperty.WarningUserConsentTestOnlyCannotOverride ->
                setOf(
                    SkaldVaultV1ProviderRegistryIsolationBlocker.WarningOnlyEvidenceCannotAuthorize,
                    SkaldVaultV1ProviderRegistryIsolationBlocker.UserConsentCannotOverride,
                    SkaldVaultV1ProviderRegistryIsolationBlocker.TestOnlyEvidenceCannotAuthorizeProduction,
                )
            SkaldVaultV1ProviderRegistryIsolationRequiredProperty.ProductionProviderSelectableFalse ->
                setOf(SkaldVaultV1ProviderRegistryIsolationBlocker.ProductionProviderSelectableFalse)
            SkaldVaultV1ProviderRegistryIsolationRequiredProperty.MainnetDisabled ->
                setOf(SkaldVaultV1ProviderRegistryIsolationBlocker.MainnetDisabled)
        }

    private val baseStatuses: Set<SkaldVaultV1ProviderRegistryIsolationStatus> = setOf(
        SkaldVaultV1ProviderRegistryIsolationStatus.GuardModeled,
        SkaldVaultV1ProviderRegistryIsolationStatus.StillDisabled,
        SkaldVaultV1ProviderRegistryIsolationStatus.EvidenceOnly,
        SkaldVaultV1ProviderRegistryIsolationStatus.RegistryIsolated,
        SkaldVaultV1ProviderRegistryIsolationStatus.PromotionBlocked,
        SkaldVaultV1ProviderRegistryIsolationStatus.WarningOnlyCannotAuthorize,
        SkaldVaultV1ProviderRegistryIsolationStatus.UserConsentCannotOverride,
        SkaldVaultV1ProviderRegistryIsolationStatus.TestOnlyRejectedForProduction,
        SkaldVaultV1ProviderRegistryIsolationStatus.MainnetBlocked,
    )

    private val baseBlockers: Set<SkaldVaultV1ProviderRegistryIsolationBlocker> = setOf(
        SkaldVaultV1ProviderRegistryIsolationBlocker.RegistryIsolationGuardStillDisabled,
        SkaldVaultV1ProviderRegistryIsolationBlocker.ModelEvidenceOnly,
        SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderSelectionLockedToDisabledProvider,
        SkaldVaultV1ProviderRegistryIsolationBlocker.ProductionProviderSelectableFalse,
        SkaldVaultV1ProviderRegistryIsolationBlocker.SkeletonExcludedFromRegistry,
        SkaldVaultV1ProviderRegistryIsolationBlocker.CandidateProvidersExcludedFromRegistry,
        SkaldVaultV1ProviderRegistryIsolationBlocker.FactoriesExcludedFromRegistry,
        SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderConstructorsExcludedFromRegistry,
        SkaldVaultV1ProviderRegistryIsolationBlocker.RegistryPromotionBlocked,
        SkaldVaultV1ProviderRegistryIsolationBlocker.RegistryRuntimeInstantiationBlocked,
        SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderOperationAuthorizationBlocked,
        SkaldVaultV1ProviderRegistryIsolationBlocker.RuntimeRandomnessAuthorizationBlocked,
        SkaldVaultV1ProviderRegistryIsolationBlocker.KdfCalibrationAuthorizationBlocked,
        SkaldVaultV1ProviderRegistryIsolationBlocker.SecureStorageAuthorizationBlocked,
        SkaldVaultV1ProviderRegistryIsolationBlocker.CreationAuthorizationBlocked,
        SkaldVaultV1ProviderRegistryIsolationBlocker.UnlockAuthorizationBlocked,
        SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderCandidatePackagingStillDisabled,
        SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderDependencyBuildStillDisabled,
        SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderInterfaceAuditEvidenceOnly,
        SkaldVaultV1ProviderRegistryIsolationBlocker.NonSelectableProviderSkeletonStillDisabled,
        SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderSelectionPromotionBlocked,
        SkaldVaultV1ProviderRegistryIsolationBlocker.AuthorizationReadinessMatrixBlocked,
        SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderAcceptanceMissing,
        SkaldVaultV1ProviderRegistryIsolationBlocker.DependencyProbeInsufficient,
        SkaldVaultV1ProviderRegistryIsolationBlocker.KatExecutionUnavailable,
        SkaldVaultV1ProviderRegistryIsolationBlocker.RegistryHandlesRejected,
        SkaldVaultV1ProviderRegistryIsolationBlocker.RegistryObjectsRejected,
        SkaldVaultV1ProviderRegistryIsolationBlocker.CryptoObjectsRejected,
        SkaldVaultV1ProviderRegistryIsolationBlocker.ByteMaterialRejected,
        SkaldVaultV1ProviderRegistryIsolationBlocker.SecretMaterialRejected,
        SkaldVaultV1ProviderRegistryIsolationBlocker.PlatformCryptoImportsForbidden,
        SkaldVaultV1ProviderRegistryIsolationBlocker.ProviderOperationExecutionForbidden,
        SkaldVaultV1ProviderRegistryIsolationBlocker.WarningOnlyEvidenceCannotAuthorize,
        SkaldVaultV1ProviderRegistryIsolationBlocker.UserConsentCannotOverride,
        SkaldVaultV1ProviderRegistryIsolationBlocker.TestOnlyEvidenceCannotAuthorizeProduction,
        SkaldVaultV1ProviderRegistryIsolationBlocker.ReleaseReviewMissing,
        SkaldVaultV1ProviderRegistryIsolationBlocker.MainnetDisabled,
    )
}
