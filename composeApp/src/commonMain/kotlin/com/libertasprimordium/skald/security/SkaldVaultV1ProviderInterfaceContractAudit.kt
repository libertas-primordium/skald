package com.libertasprimordium.skald.security

interface SkaldVaultV1ProviderInterfaceContractAuditBoundary {
    fun evaluate(
        request: SkaldVaultV1ProviderInterfaceContractAuditRequest,
    ): SkaldVaultV1ProviderInterfaceContractAuditResult<SkaldVaultV1ProviderInterfaceContractAuditEvidence>
}

enum class SkaldVaultV1ProviderInterfaceContractAuditSource(val label: String) {
    CurrentTypedEvidence("current provider interface contract evidence"),
    InterfaceTopicAudit("provider interface topic audit"),
    ContractRiskAudit("provider interface contract risk audit"),
    RequiredPropertyAudit("provider interface required property audit"),
    EvidenceInteractionAudit("provider interface evidence interaction audit"),
}

enum class SkaldVaultV1ProviderInterfaceContractAuditStatus(val label: String) {
    BoundaryModeled("provider interface contract audit modeled"),
    StillDisabled("still disabled"),
    EvidenceOnly("evidence only"),
    ProviderNeutral("provider-neutral"),
    MaterialRejected("runtime material rejected"),
    OperationExecutionBlocked("operation execution blocked"),
    AuthorizationRequired("authorization required"),
    PromotionBlocked("provider promotion blocked"),
    SelectionBlocked("provider selection blocked"),
    WarningOnlyCannotAuthorize("warning-only evidence cannot authorize"),
    UserConsentCannotOverride("user consent cannot override"),
    TestOnlyRejectedForProduction("test-only evidence rejected for production"),
    UnsupportedFailClosed("unsupported fail closed"),
}

enum class SkaldVaultV1ProviderInterfaceContractTopic(val label: String) {
    ProviderNeutralCommonInterfaceBoundary("provider-neutral common interface boundary"),
    DisabledProviderFacadeBoundary("disabled provider facade boundary"),
    ProviderSelectionRegistryBoundary("provider selection registry boundary"),
    ProviderOperationAuthorizationBoundary("provider operation authorization boundary"),
    ProviderCandidatePackagingBoundary("provider candidate packaging boundary"),
    ProviderDependencyBuildEvidenceBoundary("provider dependency build evidence boundary"),
    ProviderPromotionBlockerBoundary("provider promotion blocker boundary"),
    ProviderAcceptanceContractBoundary("provider acceptance contract boundary"),
    KatContractBoundary("KAT contract boundary"),
    RuntimeRandomnessProviderCheckBoundary("runtime randomness/provider check boundary"),
    KdfCalibrationAuthorizationBoundary("KDF calibration authorization boundary"),
    SecureStorageAuthorizationBoundary("secure-storage authorization boundary"),
    CreationAuthorizationBoundary("creation authorization boundary"),
    UnlockAuthorizationBoundary("unlock authorization boundary"),
    AuthorizationReadinessMatrixBoundary("authorization/readiness matrix boundary"),
    RedactionLeakageBoundary("redaction/leakage boundary"),
    ClearWipeStrategyBoundary("clear/wipe strategy boundary"),
    MigrationCorruptionBoundary("migration/corruption boundary"),
}

enum class SkaldVaultV1ProviderInterfaceContractRisk(val label: String) {
    AcceptsRealKeyMaterial("provider interface accepts real key material"),
    AcceptsPassphrases("provider interface accepts passphrases"),
    AcceptsRawKdfMaterial("provider interface accepts raw KDF material"),
    AcceptsRandomSaltNonceMaterial("provider interface accepts random/salt/nonce material"),
    AcceptsCiphertextPlaintext("provider interface accepts ciphertext/plaintext"),
    AcceptsHeaderContainerManifestIndexRecordMaterial(
        "provider interface accepts header/container/manifest/index/record material",
    ),
    AcceptsProviderImplementationObjects("provider interface accepts provider implementation objects"),
    AcceptsProviderHandles("provider interface accepts provider handles"),
    AcceptsPlatformCryptoObjects("provider interface accepts platform crypto objects"),
    ExposesProviderHandlesInDiagnostics("provider interface exposes provider handles in diagnostics"),
    ExposesByteMaterialInToString("provider interface exposes byte material in toString"),
    ExposesSecretLookingValues("provider interface exposes secret-looking values"),
    CanExecuteOperationsDirectly("provider interface can execute operations directly"),
    CanBypassProviderOperationAuthorization("provider interface can bypass provider operation authorization"),
    CanBypassRuntimeRandomnessAuthorization("provider interface can bypass runtime randomness authorization"),
    CanBypassKdfAuthorization("provider interface can bypass KDF authorization"),
    CanBypassSecureStorageAuthorization("provider interface can bypass secure-storage authorization"),
    CanBypassCreationUnlockAuthorization("provider interface can bypass creation/unlock authorization"),
    CanBypassProviderSelectionPromotionBlockers("provider interface can bypass provider selection promotion blockers"),
    CanBypassReadinessMatrix("provider interface can bypass readiness matrix"),
    CanMakeProviderSelectable("provider interface can make a provider selectable"),
    CanChangeProductionProviderSelectable("provider interface can change productionProviderSelectable"),
    CanIntroducePlatformCryptoImportsInCommonSource(
        "provider interface can introduce platform crypto imports in common source",
    ),
}

enum class SkaldVaultV1ProviderInterfaceContractRequiredProperty(val label: String) {
    ProviderFacingCommonContractsProviderNeutral("provider-facing common contracts are provider-neutral"),
    ModelEvidencePathsEnumsStatusesRedactedTokensOnly(
        "model/evidence paths carry enums, statuses, and redacted tokens only",
    ),
    NoRealSecretMaterialAccepted("no real secret material accepted"),
    NoByteMaterialAccepted("no byte material accepted"),
    NoProviderHandlesAccepted("no provider handles accepted"),
    NoCryptoObjectsAccepted("no crypto objects accepted"),
    NoPlatformCryptoApisImportedInCommonProductionSource(
        "no platform crypto APIs imported in common production source",
    ),
    NoProviderOperationExecutableFromModelBoundary(
        "no provider operation executable from model boundary",
    ),
    ProviderOperationAuthorizationRequiredBeforeFutureExecution(
        "provider operation authorization required before future execution",
    ),
    RuntimeRandomnessAuthorizationRequiredBeforeFutureRandomnessUse(
        "runtime randomness authorization required before future randomness use",
    ),
    KdfCalibrationAuthorizationRequiredBeforeFutureKdfUse(
        "KDF calibration authorization required before future KDF use",
    ),
    SecureStorageAuthorizationRequiredBeforeFutureWrappingStorageUse(
        "secure-storage authorization required before future wrapping/storage use",
    ),
    CreationAuthorizationRequiredBeforeFutureCreationUse(
        "creation authorization required before future creation use",
    ),
    UnlockAuthorizationRequiredBeforeFutureUnlockUse(
        "unlock authorization required before future unlock use",
    ),
    PromotionBlockersPreventBuildEvidenceSelectability(
        "promotion blockers prevent build evidence from becoming selectability",
    ),
    ReadinessMatrixPreventsWarningUserConsentTestOnlyPromotion(
        "readiness matrix prevents warning/user-consent/test-only promotion",
    ),
    ProviderSelectionLockedToDisabledProvider("provider selection locked to disabled provider"),
    ProductionProviderSelectableFalse("productionProviderSelectable remains false"),
    DiagnosticsRedacted("diagnostics are redacted"),
}

enum class SkaldVaultV1ProviderInterfaceContractBlocker(val label: String) {
    ProviderInterfaceAuditStillDisabled("provider interface audit still disabled"),
    ModelEvidenceOnly("model evidence only"),
    ProviderSelectionLockedToDisabledProvider("provider selection locked to disabled provider"),
    ProductionProviderSelectableFalse("productionProviderSelectable is false"),
    ProviderOperationAuthorizationBlocked("provider operation authorization blocked"),
    RuntimeRandomnessAuthorizationBlocked("runtime randomness authorization blocked"),
    KdfCalibrationAuthorizationBlocked("KDF calibration authorization blocked"),
    SecureStorageAuthorizationBlocked("secure-storage authorization blocked"),
    CreationAuthorizationBlocked("creation authorization blocked"),
    UnlockAuthorizationBlocked("unlock authorization blocked"),
    ProviderCandidatePackagingStillDisabled("provider candidate packaging still disabled"),
    ProviderDependencyBuildStillDisabled("provider dependency build still disabled"),
    ProviderSelectionPromotionBlocked("provider selection promotion blocked"),
    AuthorizationReadinessMatrixBlocked("authorization/readiness matrix blocked"),
    ProviderAcceptanceMissing("provider acceptance missing"),
    DependencyProbeInsufficient("dependency probe insufficient"),
    KatContractNotApproved("KAT contract not approved"),
    RedactionLeakageReviewMissing("redaction/leakage review missing"),
    ClearWipeReviewMissing("clear/wipe review missing"),
    MigrationCorruptionReviewMissing("migration/corruption review missing"),
    CommonSourcePlatformCryptoImportsForbidden("common source platform crypto imports forbidden"),
    ProviderRuntimeMaterialRejected("provider runtime material rejected"),
    ProviderHandlesRejected("provider handles rejected"),
    CryptoObjectsRejected("crypto objects rejected"),
    ByteMaterialRejected("byte material rejected"),
    SecretMaterialRejected("secret material rejected"),
    DiagnosticsRedacted("diagnostics redacted"),
    WarningOnlyEvidenceCannotAuthorize("warning-only evidence cannot authorize"),
    UserConsentCannotOverride("user consent cannot override"),
    TestOnlyEvidenceCannotAuthorizeProduction("test-only evidence cannot authorize production"),
    MainnetDisabled("mainnet disabled"),
}

enum class SkaldVaultV1ProviderInterfaceContractRedactionClass(val label: String) {
    PolicyIdsOnly("policy ids only"),
    AuditTopicOnly("audit topic only"),
    RiskCategoryOnly("risk category only"),
    RequiredPropertyOnly("required property only"),
    BoundaryAndBlockerClassesOnly("boundary and blocker classes only"),
    NoDiagnosticPayload("no diagnostic payload"),
}

data class SkaldVaultV1ProviderInterfaceContractCapability(
    val providerInterfaceAuditedForSelection: Boolean,
    val providerInterfaceAcceptsSecrets: Boolean,
    val providerInterfaceAcceptsByteMaterial: Boolean,
    val providerInterfaceAcceptsProviderHandles: Boolean,
    val providerInterfaceAcceptsCryptoObjects: Boolean,
    val providerInterfaceCanExecuteOperations: Boolean,
    val providerInterfaceCanBypassAuthorization: Boolean,
    val providerInterfaceCanSelectProvider: Boolean,
    val providerInterfaceCanPromoteProvider: Boolean,
    val providerInterfaceCanSetProductionProviderSelectable: Boolean,
    val providerImplementationAdded: Boolean,
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
        val CurrentFailClosed = SkaldVaultV1ProviderInterfaceContractCapability(
            providerInterfaceAuditedForSelection = true,
            providerInterfaceAcceptsSecrets = false,
            providerInterfaceAcceptsByteMaterial = false,
            providerInterfaceAcceptsProviderHandles = false,
            providerInterfaceAcceptsCryptoObjects = false,
            providerInterfaceCanExecuteOperations = false,
            providerInterfaceCanBypassAuthorization = false,
            providerInterfaceCanSelectProvider = false,
            providerInterfaceCanPromoteProvider = false,
            providerInterfaceCanSetProductionProviderSelectable = false,
            providerImplementationAdded = false,
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

class SkaldVaultV1ProviderInterfaceContractPolicyToken private constructor(
    val policyId: String,
    val topic: SkaldVaultV1ProviderInterfaceContractTopic?,
    val risk: SkaldVaultV1ProviderInterfaceContractRisk?,
    val requiredProperty: SkaldVaultV1ProviderInterfaceContractRequiredProperty?,
) {
    val containsProviderHandle: Boolean = false
    val containsCryptoObject: Boolean = false
    val containsByteMaterial: Boolean = false
    val containsPathOrRootText: Boolean = false
    val containsStorageIdentifier: Boolean = false
    val containsSecretMaterial: Boolean = false

    override fun toString(): String =
        "SkaldVaultV1ProviderInterfaceContractPolicyToken(" +
            "policyId=$policyId, " +
            "topic=$topic, " +
            "risk=$risk, " +
            "requiredProperty=$requiredProperty, " +
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
            topic: SkaldVaultV1ProviderInterfaceContractTopic?,
            risk: SkaldVaultV1ProviderInterfaceContractRisk?,
            requiredProperty: SkaldVaultV1ProviderInterfaceContractRequiredProperty?,
        ): SkaldVaultV1ProviderInterfaceContractPolicyToken {
            return SkaldVaultV1ProviderInterfaceContractPolicyToken(policyId, topic, risk, requiredProperty)
        }
    }
}

class SkaldVaultV1ProviderInterfaceContractAuditRequest private constructor(
    val source: SkaldVaultV1ProviderInterfaceContractAuditSource,
    val topic: SkaldVaultV1ProviderInterfaceContractTopic?,
    val risk: SkaldVaultV1ProviderInterfaceContractRisk?,
    val requiredProperty: SkaldVaultV1ProviderInterfaceContractRequiredProperty?,
    private val providerSelectionPromotionEvidence: SkaldVaultV1ProviderSelectionPromotionEvidence?,
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
    val providerSelectionPromotionEvidenceSupplied: Boolean
        get() = providerSelectionPromotionEvidence != null

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
        "SkaldVaultV1ProviderInterfaceContractAuditRequest(" +
            "source=$source, " +
            "topic=$topic, " +
            "risk=$risk, " +
            "requiredProperty=$requiredProperty, " +
            "providerSelectionPromotion=<redacted>, " +
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
            providerSelectionPromotionEvidence: SkaldVaultV1ProviderSelectionPromotionEvidence? = null,
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
        ): SkaldVaultV1ProviderInterfaceContractAuditRequest =
            SkaldVaultV1ProviderInterfaceContractAuditRequest(
                source = SkaldVaultV1ProviderInterfaceContractAuditSource.CurrentTypedEvidence,
                topic = null,
                risk = null,
                requiredProperty = null,
                providerSelectionPromotionEvidence = providerSelectionPromotionEvidence,
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

        fun forTopic(
            topic: SkaldVaultV1ProviderInterfaceContractTopic,
        ): SkaldVaultV1ProviderInterfaceContractAuditRequest =
            SkaldVaultV1ProviderInterfaceContractAuditRequest(
                source = SkaldVaultV1ProviderInterfaceContractAuditSource.InterfaceTopicAudit,
                topic = topic,
                risk = null,
                requiredProperty = null,
                providerSelectionPromotionEvidence = null,
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

        fun forRisk(
            risk: SkaldVaultV1ProviderInterfaceContractRisk,
        ): SkaldVaultV1ProviderInterfaceContractAuditRequest =
            SkaldVaultV1ProviderInterfaceContractAuditRequest(
                source = SkaldVaultV1ProviderInterfaceContractAuditSource.ContractRiskAudit,
                topic = null,
                risk = risk,
                requiredProperty = null,
                providerSelectionPromotionEvidence = null,
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

        fun forRequiredProperty(
            requiredProperty: SkaldVaultV1ProviderInterfaceContractRequiredProperty,
        ): SkaldVaultV1ProviderInterfaceContractAuditRequest =
            SkaldVaultV1ProviderInterfaceContractAuditRequest(
                source = SkaldVaultV1ProviderInterfaceContractAuditSource.RequiredPropertyAudit,
                topic = null,
                risk = null,
                requiredProperty = requiredProperty,
                providerSelectionPromotionEvidence = null,
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

        fun evidenceInteractionAudit(): SkaldVaultV1ProviderInterfaceContractAuditRequest =
            SkaldVaultV1ProviderInterfaceContractAuditRequest(
                source = SkaldVaultV1ProviderInterfaceContractAuditSource.EvidenceInteractionAudit,
                topic = null,
                risk = null,
                requiredProperty = null,
                providerSelectionPromotionEvidence = null,
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

data class SkaldVaultV1ProviderInterfaceContractTopicRow(
    val topic: SkaldVaultV1ProviderInterfaceContractTopic,
    val statuses: Set<SkaldVaultV1ProviderInterfaceContractAuditStatus>,
    val blockers: Set<SkaldVaultV1ProviderInterfaceContractBlocker>,
    val requiredProperties: Set<SkaldVaultV1ProviderInterfaceContractRequiredProperty>,
    val providerNeutral: Boolean,
    val evidenceOnly: Boolean,
    val runtimeAvailable: Boolean,
    val selectable: Boolean,
    val operationAuthorizationRequired: Boolean,
    val promotionBlocked: Boolean,
    val readinessMatrixRequired: Boolean,
    val redactionClass: SkaldVaultV1ProviderInterfaceContractRedactionClass,
)

data class SkaldVaultV1ProviderInterfaceContractRiskRow(
    val risk: SkaldVaultV1ProviderInterfaceContractRisk,
    val statuses: Set<SkaldVaultV1ProviderInterfaceContractAuditStatus>,
    val blockers: Set<SkaldVaultV1ProviderInterfaceContractBlocker>,
    val rejected: Boolean,
    val accepted: Boolean,
    val canExecute: Boolean,
    val canBypassAuthorization: Boolean,
    val canChangeSelectability: Boolean,
    val exposesDiagnostics: Boolean,
    val redactionClass: SkaldVaultV1ProviderInterfaceContractRedactionClass,
)

data class SkaldVaultV1ProviderInterfaceContractRequiredPropertyRow(
    val requiredProperty: SkaldVaultV1ProviderInterfaceContractRequiredProperty,
    val statuses: Set<SkaldVaultV1ProviderInterfaceContractAuditStatus>,
    val blockers: Set<SkaldVaultV1ProviderInterfaceContractBlocker>,
    val satisfiedForCurrentModel: Boolean,
    val futureRuntimeGateRequired: Boolean,
    val runtimeAvailable: Boolean,
    val selectable: Boolean,
    val redactionClass: SkaldVaultV1ProviderInterfaceContractRedactionClass,
)

data class SkaldVaultV1ProviderInterfaceContractAuditSummary(
    val policyId: String,
    val policyVersion: Int,
    val statuses: Set<SkaldVaultV1ProviderInterfaceContractAuditStatus>,
    val topics: Set<SkaldVaultV1ProviderInterfaceContractTopic>,
    val risks: Set<SkaldVaultV1ProviderInterfaceContractRisk>,
    val requiredProperties: Set<SkaldVaultV1ProviderInterfaceContractRequiredProperty>,
    val blockers: Set<SkaldVaultV1ProviderInterfaceContractBlocker>,
    val redactionClasses: Set<SkaldVaultV1ProviderInterfaceContractRedactionClass>,
    val capability: SkaldVaultV1ProviderInterfaceContractCapability,
    val stillDisabled: Boolean,
    val evidenceOnly: Boolean,
    val providerNeutral: Boolean,
    val rejectsRuntimeMaterial: Boolean,
    val blocksExecution: Boolean,
    val blocksProviderSelection: Boolean,
)

data class SkaldVaultV1ProviderInterfaceContractAuditEvidence(
    val policyId: String,
    val policyVersion: Int,
    val source: SkaldVaultV1ProviderInterfaceContractAuditSource,
    val status: SkaldVaultV1ProviderInterfaceContractAuditStatus,
    val topicRows: List<SkaldVaultV1ProviderInterfaceContractTopicRow>,
    val riskRows: List<SkaldVaultV1ProviderInterfaceContractRiskRow>,
    val requiredPropertyRows: List<SkaldVaultV1ProviderInterfaceContractRequiredPropertyRow>,
    val blockers: Set<SkaldVaultV1ProviderInterfaceContractBlocker>,
    val capability: SkaldVaultV1ProviderInterfaceContractCapability,
    val policyTokenEvidence: SkaldVaultV1ProviderInterfaceContractPolicyToken,
    val providerSelectionPromotionEvidenceConsumed: Boolean,
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
    val providerInterfaceContractAuditModeled: Boolean = true,
    val providerInterfaceContractAuditStillDisabled: Boolean = true,
    val providerInterfaceContractAuditDoesNotImplementProvider: Boolean = true,
    val providerInterfaceContractAuditDoesNotAcceptSecrets: Boolean = true,
    val providerInterfaceContractAuditDoesNotAcceptByteMaterial: Boolean = true,
    val providerInterfaceContractAuditDoesNotAcceptProviderHandles: Boolean = true,
    val providerInterfaceContractAuditDoesNotRunCrypto: Boolean = true,
    val providerInterfaceContractAuditDoesNotEnableProviderSelection: Boolean = true,
    val providerInterfaceContractAuditDoesNotEnableCreation: Boolean = true,
    val providerInterfaceContractAuditDoesNotEnableUnlock: Boolean = true,
    val providerInterfaceContractAuditDoesNotEnablePersistence: Boolean = true,
    val providerInterfaceAuditedForSelection: Boolean = true,
    val providerInterfaceAcceptsSecrets: Boolean = false,
    val providerInterfaceAcceptsByteMaterial: Boolean = false,
    val providerInterfaceAcceptsProviderHandles: Boolean = false,
    val providerInterfaceAcceptsCryptoObjects: Boolean = false,
    val providerInterfaceCanExecuteOperations: Boolean = false,
    val providerInterfaceCanBypassAuthorization: Boolean = false,
    val providerInterfaceCanSelectProvider: Boolean = false,
    val providerInterfaceCanPromoteProvider: Boolean = false,
    val providerInterfaceCanSetProductionProviderSelectable: Boolean = false,
    val providerImplementationAdded: Boolean = false,
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
    val vaultCreationAvailable: Boolean = false,
    val vaultUnlockAvailable: Boolean = false,
    val vaultPersistenceAvailable: Boolean = false,
    val mainnetAvailable: Boolean = false,
) {
    override fun toString(): String =
        "SkaldVaultV1ProviderInterfaceContractAuditEvidence(" +
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

sealed class SkaldVaultV1ProviderInterfaceContractAuditResult<out T> {
    data class Blocked<out T>(
        val value: T,
    ) : SkaldVaultV1ProviderInterfaceContractAuditResult<T>() {
        override fun toString(): String =
            "SkaldVaultV1ProviderInterfaceContractAuditResult.Blocked(" +
                "value=<redacted-provider-interface-contract-audit-evidence>)"
    }
}

object SkaldVaultV1ProviderInterfaceContractAuditPolicy :
    SkaldVaultV1ProviderInterfaceContractAuditBoundary {
    const val POLICY_ID = "skald-vault-v1-provider-interface-contract-audit-v1"
    const val POLICY_VERSION = 1

    override fun evaluate(
        request: SkaldVaultV1ProviderInterfaceContractAuditRequest,
    ): SkaldVaultV1ProviderInterfaceContractAuditResult<SkaldVaultV1ProviderInterfaceContractAuditEvidence> {
        val topicRows = topicRowsFor(request.topic)
        val riskRows = riskRowsFor(request.risk)
        val requiredPropertyRows = requiredPropertyRowsFor(request.requiredProperty)
        return SkaldVaultV1ProviderInterfaceContractAuditResult.Blocked(
            SkaldVaultV1ProviderInterfaceContractAuditEvidence(
                policyId = POLICY_ID,
                policyVersion = POLICY_VERSION,
                source = request.source,
                status = SkaldVaultV1ProviderInterfaceContractAuditStatus.StillDisabled,
                topicRows = topicRows,
                riskRows = riskRows,
                requiredPropertyRows = requiredPropertyRows,
                blockers = topicRows.flatMap { it.blockers }.toSet() +
                    riskRows.flatMap { it.blockers }.toSet() +
                    requiredPropertyRows.flatMap { it.blockers }.toSet() +
                    baseOverrideBlockers,
                capability = SkaldVaultV1ProviderInterfaceContractCapability.CurrentFailClosed,
                policyTokenEvidence = SkaldVaultV1ProviderInterfaceContractPolicyToken.redacted(
                    policyId = POLICY_ID,
                    topic = request.topic,
                    risk = request.risk,
                    requiredProperty = request.requiredProperty,
                ),
                providerSelectionPromotionEvidenceConsumed =
                    request.providerSelectionPromotionEvidenceSupplied,
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

    fun currentPolicySummary(): SkaldVaultV1ProviderInterfaceContractAuditSummary =
        SkaldVaultV1ProviderInterfaceContractAuditSummary(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            statuses = SkaldVaultV1ProviderInterfaceContractAuditStatus.entries.toSet(),
            topics = SkaldVaultV1ProviderInterfaceContractTopic.entries.toSet(),
            risks = SkaldVaultV1ProviderInterfaceContractRisk.entries.toSet(),
            requiredProperties = SkaldVaultV1ProviderInterfaceContractRequiredProperty.entries.toSet(),
            blockers = SkaldVaultV1ProviderInterfaceContractBlocker.entries.toSet(),
            redactionClasses = SkaldVaultV1ProviderInterfaceContractRedactionClass.entries.toSet(),
            capability = SkaldVaultV1ProviderInterfaceContractCapability.CurrentFailClosed,
            stillDisabled = true,
            evidenceOnly = true,
            providerNeutral = true,
            rejectsRuntimeMaterial = true,
            blocksExecution = true,
            blocksProviderSelection = true,
        )

    private fun topicRowsFor(
        topic: SkaldVaultV1ProviderInterfaceContractTopic?,
    ): List<SkaldVaultV1ProviderInterfaceContractTopicRow> {
        val rows = SkaldVaultV1ProviderInterfaceContractTopic.entries.map { currentTopic ->
            topicRow(currentTopic)
        }
        return topic?.let { requested -> rows.filter { it.topic == requested } } ?: rows
    }

    private fun riskRowsFor(
        risk: SkaldVaultV1ProviderInterfaceContractRisk?,
    ): List<SkaldVaultV1ProviderInterfaceContractRiskRow> {
        val rows = SkaldVaultV1ProviderInterfaceContractRisk.entries.map { currentRisk ->
            riskRow(currentRisk)
        }
        return risk?.let { requested -> rows.filter { it.risk == requested } } ?: rows
    }

    private fun requiredPropertyRowsFor(
        requiredProperty: SkaldVaultV1ProviderInterfaceContractRequiredProperty?,
    ): List<SkaldVaultV1ProviderInterfaceContractRequiredPropertyRow> {
        val rows = SkaldVaultV1ProviderInterfaceContractRequiredProperty.entries.map { currentProperty ->
            requiredPropertyRow(currentProperty)
        }
        return requiredProperty?.let { requested -> rows.filter { it.requiredProperty == requested } } ?: rows
    }

    private fun topicRow(
        topic: SkaldVaultV1ProviderInterfaceContractTopic,
    ): SkaldVaultV1ProviderInterfaceContractTopicRow =
        SkaldVaultV1ProviderInterfaceContractTopicRow(
            topic = topic,
            statuses = baseStatuses + topicStatus(topic),
            blockers = topicBlockers(topic) + baseOverrideBlockers,
            requiredProperties = SkaldVaultV1ProviderInterfaceContractRequiredProperty.entries.toSet(),
            providerNeutral = true,
            evidenceOnly = true,
            runtimeAvailable = false,
            selectable = false,
            operationAuthorizationRequired = true,
            promotionBlocked = true,
            readinessMatrixRequired = true,
            redactionClass = SkaldVaultV1ProviderInterfaceContractRedactionClass.AuditTopicOnly,
        )

    private fun riskRow(
        risk: SkaldVaultV1ProviderInterfaceContractRisk,
    ): SkaldVaultV1ProviderInterfaceContractRiskRow =
        SkaldVaultV1ProviderInterfaceContractRiskRow(
            risk = risk,
            statuses = baseStatuses + SkaldVaultV1ProviderInterfaceContractAuditStatus.MaterialRejected,
            blockers = riskBlockers(risk) + baseOverrideBlockers,
            rejected = true,
            accepted = false,
            canExecute = false,
            canBypassAuthorization = false,
            canChangeSelectability = false,
            exposesDiagnostics = false,
            redactionClass = SkaldVaultV1ProviderInterfaceContractRedactionClass.RiskCategoryOnly,
        )

    private fun requiredPropertyRow(
        requiredProperty: SkaldVaultV1ProviderInterfaceContractRequiredProperty,
    ): SkaldVaultV1ProviderInterfaceContractRequiredPropertyRow =
        SkaldVaultV1ProviderInterfaceContractRequiredPropertyRow(
            requiredProperty = requiredProperty,
            statuses = baseStatuses + requiredPropertyStatus(requiredProperty),
            blockers = requiredPropertyBlockers(requiredProperty) + baseOverrideBlockers,
            satisfiedForCurrentModel = true,
            futureRuntimeGateRequired = true,
            runtimeAvailable = false,
            selectable = false,
            redactionClass = SkaldVaultV1ProviderInterfaceContractRedactionClass.RequiredPropertyOnly,
        )

    private fun topicStatus(
        topic: SkaldVaultV1ProviderInterfaceContractTopic,
    ): SkaldVaultV1ProviderInterfaceContractAuditStatus =
        when (topic) {
            SkaldVaultV1ProviderInterfaceContractTopic.ProviderNeutralCommonInterfaceBoundary,
            SkaldVaultV1ProviderInterfaceContractTopic.DisabledProviderFacadeBoundary,
            SkaldVaultV1ProviderInterfaceContractTopic.RedactionLeakageBoundary ->
                SkaldVaultV1ProviderInterfaceContractAuditStatus.ProviderNeutral
            SkaldVaultV1ProviderInterfaceContractTopic.ProviderSelectionRegistryBoundary,
            SkaldVaultV1ProviderInterfaceContractTopic.ProviderPromotionBlockerBoundary ->
                SkaldVaultV1ProviderInterfaceContractAuditStatus.SelectionBlocked
            SkaldVaultV1ProviderInterfaceContractTopic.ProviderOperationAuthorizationBoundary,
            SkaldVaultV1ProviderInterfaceContractTopic.RuntimeRandomnessProviderCheckBoundary,
            SkaldVaultV1ProviderInterfaceContractTopic.KdfCalibrationAuthorizationBoundary,
            SkaldVaultV1ProviderInterfaceContractTopic.SecureStorageAuthorizationBoundary,
            SkaldVaultV1ProviderInterfaceContractTopic.CreationAuthorizationBoundary,
            SkaldVaultV1ProviderInterfaceContractTopic.UnlockAuthorizationBoundary ->
                SkaldVaultV1ProviderInterfaceContractAuditStatus.AuthorizationRequired
            else -> SkaldVaultV1ProviderInterfaceContractAuditStatus.PromotionBlocked
        }

    private fun topicBlockers(
        topic: SkaldVaultV1ProviderInterfaceContractTopic,
    ): Set<SkaldVaultV1ProviderInterfaceContractBlocker> =
        when (topic) {
            SkaldVaultV1ProviderInterfaceContractTopic.ProviderNeutralCommonInterfaceBoundary -> setOf(
                SkaldVaultV1ProviderInterfaceContractBlocker.ModelEvidenceOnly,
                SkaldVaultV1ProviderInterfaceContractBlocker.CommonSourcePlatformCryptoImportsForbidden,
            )
            SkaldVaultV1ProviderInterfaceContractTopic.DisabledProviderFacadeBoundary -> setOf(
                SkaldVaultV1ProviderInterfaceContractBlocker.ProviderInterfaceAuditStillDisabled,
                SkaldVaultV1ProviderInterfaceContractBlocker.ProviderOperationAuthorizationBlocked,
            )
            SkaldVaultV1ProviderInterfaceContractTopic.ProviderSelectionRegistryBoundary -> setOf(
                SkaldVaultV1ProviderInterfaceContractBlocker.ProviderSelectionLockedToDisabledProvider,
                SkaldVaultV1ProviderInterfaceContractBlocker.ProductionProviderSelectableFalse,
            )
            SkaldVaultV1ProviderInterfaceContractTopic.ProviderOperationAuthorizationBoundary -> setOf(
                SkaldVaultV1ProviderInterfaceContractBlocker.ProviderOperationAuthorizationBlocked,
            )
            SkaldVaultV1ProviderInterfaceContractTopic.ProviderCandidatePackagingBoundary -> setOf(
                SkaldVaultV1ProviderInterfaceContractBlocker.ProviderCandidatePackagingStillDisabled,
            )
            SkaldVaultV1ProviderInterfaceContractTopic.ProviderDependencyBuildEvidenceBoundary -> setOf(
                SkaldVaultV1ProviderInterfaceContractBlocker.ProviderDependencyBuildStillDisabled,
                SkaldVaultV1ProviderInterfaceContractBlocker.DependencyProbeInsufficient,
            )
            SkaldVaultV1ProviderInterfaceContractTopic.ProviderPromotionBlockerBoundary -> setOf(
                SkaldVaultV1ProviderInterfaceContractBlocker.ProviderSelectionPromotionBlocked,
            )
            SkaldVaultV1ProviderInterfaceContractTopic.ProviderAcceptanceContractBoundary -> setOf(
                SkaldVaultV1ProviderInterfaceContractBlocker.ProviderAcceptanceMissing,
            )
            SkaldVaultV1ProviderInterfaceContractTopic.KatContractBoundary -> setOf(
                SkaldVaultV1ProviderInterfaceContractBlocker.KatContractNotApproved,
            )
            SkaldVaultV1ProviderInterfaceContractTopic.RuntimeRandomnessProviderCheckBoundary -> setOf(
                SkaldVaultV1ProviderInterfaceContractBlocker.RuntimeRandomnessAuthorizationBlocked,
            )
            SkaldVaultV1ProviderInterfaceContractTopic.KdfCalibrationAuthorizationBoundary -> setOf(
                SkaldVaultV1ProviderInterfaceContractBlocker.KdfCalibrationAuthorizationBlocked,
            )
            SkaldVaultV1ProviderInterfaceContractTopic.SecureStorageAuthorizationBoundary -> setOf(
                SkaldVaultV1ProviderInterfaceContractBlocker.SecureStorageAuthorizationBlocked,
            )
            SkaldVaultV1ProviderInterfaceContractTopic.CreationAuthorizationBoundary -> setOf(
                SkaldVaultV1ProviderInterfaceContractBlocker.CreationAuthorizationBlocked,
            )
            SkaldVaultV1ProviderInterfaceContractTopic.UnlockAuthorizationBoundary -> setOf(
                SkaldVaultV1ProviderInterfaceContractBlocker.UnlockAuthorizationBlocked,
            )
            SkaldVaultV1ProviderInterfaceContractTopic.AuthorizationReadinessMatrixBoundary -> setOf(
                SkaldVaultV1ProviderInterfaceContractBlocker.AuthorizationReadinessMatrixBlocked,
            )
            SkaldVaultV1ProviderInterfaceContractTopic.RedactionLeakageBoundary -> setOf(
                SkaldVaultV1ProviderInterfaceContractBlocker.DiagnosticsRedacted,
                SkaldVaultV1ProviderInterfaceContractBlocker.RedactionLeakageReviewMissing,
            )
            SkaldVaultV1ProviderInterfaceContractTopic.ClearWipeStrategyBoundary -> setOf(
                SkaldVaultV1ProviderInterfaceContractBlocker.ClearWipeReviewMissing,
            )
            SkaldVaultV1ProviderInterfaceContractTopic.MigrationCorruptionBoundary -> setOf(
                SkaldVaultV1ProviderInterfaceContractBlocker.MigrationCorruptionReviewMissing,
            )
        }

    private fun riskBlockers(
        risk: SkaldVaultV1ProviderInterfaceContractRisk,
    ): Set<SkaldVaultV1ProviderInterfaceContractBlocker> =
        when (risk) {
            SkaldVaultV1ProviderInterfaceContractRisk.AcceptsRealKeyMaterial,
            SkaldVaultV1ProviderInterfaceContractRisk.AcceptsPassphrases,
            SkaldVaultV1ProviderInterfaceContractRisk.AcceptsRawKdfMaterial,
            SkaldVaultV1ProviderInterfaceContractRisk.AcceptsRandomSaltNonceMaterial,
            SkaldVaultV1ProviderInterfaceContractRisk.AcceptsCiphertextPlaintext,
            SkaldVaultV1ProviderInterfaceContractRisk.AcceptsHeaderContainerManifestIndexRecordMaterial ->
                setOf(
                    SkaldVaultV1ProviderInterfaceContractBlocker.SecretMaterialRejected,
                    SkaldVaultV1ProviderInterfaceContractBlocker.ByteMaterialRejected,
                    SkaldVaultV1ProviderInterfaceContractBlocker.ProviderRuntimeMaterialRejected,
                )
            SkaldVaultV1ProviderInterfaceContractRisk.AcceptsProviderImplementationObjects ->
                setOf(
                    SkaldVaultV1ProviderInterfaceContractBlocker.ProviderRuntimeMaterialRejected,
                    SkaldVaultV1ProviderInterfaceContractBlocker.ProviderSelectionPromotionBlocked,
                )
            SkaldVaultV1ProviderInterfaceContractRisk.AcceptsProviderHandles,
            SkaldVaultV1ProviderInterfaceContractRisk.ExposesProviderHandlesInDiagnostics ->
                setOf(
                    SkaldVaultV1ProviderInterfaceContractBlocker.ProviderHandlesRejected,
                    SkaldVaultV1ProviderInterfaceContractBlocker.DiagnosticsRedacted,
                )
            SkaldVaultV1ProviderInterfaceContractRisk.AcceptsPlatformCryptoObjects ->
                setOf(
                    SkaldVaultV1ProviderInterfaceContractBlocker.CryptoObjectsRejected,
                    SkaldVaultV1ProviderInterfaceContractBlocker.CommonSourcePlatformCryptoImportsForbidden,
                )
            SkaldVaultV1ProviderInterfaceContractRisk.ExposesByteMaterialInToString,
            SkaldVaultV1ProviderInterfaceContractRisk.ExposesSecretLookingValues ->
                setOf(
                    SkaldVaultV1ProviderInterfaceContractBlocker.DiagnosticsRedacted,
                    SkaldVaultV1ProviderInterfaceContractBlocker.SecretMaterialRejected,
                    SkaldVaultV1ProviderInterfaceContractBlocker.ByteMaterialRejected,
                )
            SkaldVaultV1ProviderInterfaceContractRisk.CanExecuteOperationsDirectly ->
                setOf(
                    SkaldVaultV1ProviderInterfaceContractBlocker.ProviderOperationAuthorizationBlocked,
                    SkaldVaultV1ProviderInterfaceContractBlocker.ProviderSelectionPromotionBlocked,
                )
            SkaldVaultV1ProviderInterfaceContractRisk.CanBypassProviderOperationAuthorization ->
                setOf(SkaldVaultV1ProviderInterfaceContractBlocker.ProviderOperationAuthorizationBlocked)
            SkaldVaultV1ProviderInterfaceContractRisk.CanBypassRuntimeRandomnessAuthorization ->
                setOf(SkaldVaultV1ProviderInterfaceContractBlocker.RuntimeRandomnessAuthorizationBlocked)
            SkaldVaultV1ProviderInterfaceContractRisk.CanBypassKdfAuthorization ->
                setOf(SkaldVaultV1ProviderInterfaceContractBlocker.KdfCalibrationAuthorizationBlocked)
            SkaldVaultV1ProviderInterfaceContractRisk.CanBypassSecureStorageAuthorization ->
                setOf(SkaldVaultV1ProviderInterfaceContractBlocker.SecureStorageAuthorizationBlocked)
            SkaldVaultV1ProviderInterfaceContractRisk.CanBypassCreationUnlockAuthorization ->
                setOf(
                    SkaldVaultV1ProviderInterfaceContractBlocker.CreationAuthorizationBlocked,
                    SkaldVaultV1ProviderInterfaceContractBlocker.UnlockAuthorizationBlocked,
                )
            SkaldVaultV1ProviderInterfaceContractRisk.CanBypassProviderSelectionPromotionBlockers ->
                setOf(SkaldVaultV1ProviderInterfaceContractBlocker.ProviderSelectionPromotionBlocked)
            SkaldVaultV1ProviderInterfaceContractRisk.CanBypassReadinessMatrix ->
                setOf(SkaldVaultV1ProviderInterfaceContractBlocker.AuthorizationReadinessMatrixBlocked)
            SkaldVaultV1ProviderInterfaceContractRisk.CanMakeProviderSelectable,
            SkaldVaultV1ProviderInterfaceContractRisk.CanChangeProductionProviderSelectable ->
                setOf(
                    SkaldVaultV1ProviderInterfaceContractBlocker.ProviderSelectionLockedToDisabledProvider,
                    SkaldVaultV1ProviderInterfaceContractBlocker.ProductionProviderSelectableFalse,
                )
            SkaldVaultV1ProviderInterfaceContractRisk.CanIntroducePlatformCryptoImportsInCommonSource ->
                setOf(SkaldVaultV1ProviderInterfaceContractBlocker.CommonSourcePlatformCryptoImportsForbidden)
        }

    private fun requiredPropertyStatus(
        requiredProperty: SkaldVaultV1ProviderInterfaceContractRequiredProperty,
    ): SkaldVaultV1ProviderInterfaceContractAuditStatus =
        when (requiredProperty) {
            SkaldVaultV1ProviderInterfaceContractRequiredProperty.ProviderFacingCommonContractsProviderNeutral,
            SkaldVaultV1ProviderInterfaceContractRequiredProperty.ModelEvidencePathsEnumsStatusesRedactedTokensOnly,
            SkaldVaultV1ProviderInterfaceContractRequiredProperty.DiagnosticsRedacted ->
                SkaldVaultV1ProviderInterfaceContractAuditStatus.ProviderNeutral
            SkaldVaultV1ProviderInterfaceContractRequiredProperty.NoRealSecretMaterialAccepted,
            SkaldVaultV1ProviderInterfaceContractRequiredProperty.NoByteMaterialAccepted,
            SkaldVaultV1ProviderInterfaceContractRequiredProperty.NoProviderHandlesAccepted,
            SkaldVaultV1ProviderInterfaceContractRequiredProperty.NoCryptoObjectsAccepted,
            SkaldVaultV1ProviderInterfaceContractRequiredProperty.NoPlatformCryptoApisImportedInCommonProductionSource ->
                SkaldVaultV1ProviderInterfaceContractAuditStatus.MaterialRejected
            else -> SkaldVaultV1ProviderInterfaceContractAuditStatus.AuthorizationRequired
        }

    private fun requiredPropertyBlockers(
        requiredProperty: SkaldVaultV1ProviderInterfaceContractRequiredProperty,
    ): Set<SkaldVaultV1ProviderInterfaceContractBlocker> =
        when (requiredProperty) {
            SkaldVaultV1ProviderInterfaceContractRequiredProperty.ProviderFacingCommonContractsProviderNeutral,
            SkaldVaultV1ProviderInterfaceContractRequiredProperty.ModelEvidencePathsEnumsStatusesRedactedTokensOnly ->
                setOf(SkaldVaultV1ProviderInterfaceContractBlocker.ModelEvidenceOnly)
            SkaldVaultV1ProviderInterfaceContractRequiredProperty.NoRealSecretMaterialAccepted ->
                setOf(SkaldVaultV1ProviderInterfaceContractBlocker.SecretMaterialRejected)
            SkaldVaultV1ProviderInterfaceContractRequiredProperty.NoByteMaterialAccepted ->
                setOf(SkaldVaultV1ProviderInterfaceContractBlocker.ByteMaterialRejected)
            SkaldVaultV1ProviderInterfaceContractRequiredProperty.NoProviderHandlesAccepted ->
                setOf(SkaldVaultV1ProviderInterfaceContractBlocker.ProviderHandlesRejected)
            SkaldVaultV1ProviderInterfaceContractRequiredProperty.NoCryptoObjectsAccepted ->
                setOf(SkaldVaultV1ProviderInterfaceContractBlocker.CryptoObjectsRejected)
            SkaldVaultV1ProviderInterfaceContractRequiredProperty.NoPlatformCryptoApisImportedInCommonProductionSource ->
                setOf(SkaldVaultV1ProviderInterfaceContractBlocker.CommonSourcePlatformCryptoImportsForbidden)
            SkaldVaultV1ProviderInterfaceContractRequiredProperty.NoProviderOperationExecutableFromModelBoundary,
            SkaldVaultV1ProviderInterfaceContractRequiredProperty.ProviderOperationAuthorizationRequiredBeforeFutureExecution ->
                setOf(SkaldVaultV1ProviderInterfaceContractBlocker.ProviderOperationAuthorizationBlocked)
            SkaldVaultV1ProviderInterfaceContractRequiredProperty.RuntimeRandomnessAuthorizationRequiredBeforeFutureRandomnessUse ->
                setOf(SkaldVaultV1ProviderInterfaceContractBlocker.RuntimeRandomnessAuthorizationBlocked)
            SkaldVaultV1ProviderInterfaceContractRequiredProperty.KdfCalibrationAuthorizationRequiredBeforeFutureKdfUse ->
                setOf(SkaldVaultV1ProviderInterfaceContractBlocker.KdfCalibrationAuthorizationBlocked)
            SkaldVaultV1ProviderInterfaceContractRequiredProperty.SecureStorageAuthorizationRequiredBeforeFutureWrappingStorageUse ->
                setOf(SkaldVaultV1ProviderInterfaceContractBlocker.SecureStorageAuthorizationBlocked)
            SkaldVaultV1ProviderInterfaceContractRequiredProperty.CreationAuthorizationRequiredBeforeFutureCreationUse ->
                setOf(SkaldVaultV1ProviderInterfaceContractBlocker.CreationAuthorizationBlocked)
            SkaldVaultV1ProviderInterfaceContractRequiredProperty.UnlockAuthorizationRequiredBeforeFutureUnlockUse ->
                setOf(SkaldVaultV1ProviderInterfaceContractBlocker.UnlockAuthorizationBlocked)
            SkaldVaultV1ProviderInterfaceContractRequiredProperty.PromotionBlockersPreventBuildEvidenceSelectability ->
                setOf(
                    SkaldVaultV1ProviderInterfaceContractBlocker.ProviderSelectionPromotionBlocked,
                    SkaldVaultV1ProviderInterfaceContractBlocker.ProviderDependencyBuildStillDisabled,
                )
            SkaldVaultV1ProviderInterfaceContractRequiredProperty.ReadinessMatrixPreventsWarningUserConsentTestOnlyPromotion ->
                setOf(
                    SkaldVaultV1ProviderInterfaceContractBlocker.AuthorizationReadinessMatrixBlocked,
                    SkaldVaultV1ProviderInterfaceContractBlocker.WarningOnlyEvidenceCannotAuthorize,
                    SkaldVaultV1ProviderInterfaceContractBlocker.UserConsentCannotOverride,
                    SkaldVaultV1ProviderInterfaceContractBlocker.TestOnlyEvidenceCannotAuthorizeProduction,
                )
            SkaldVaultV1ProviderInterfaceContractRequiredProperty.ProviderSelectionLockedToDisabledProvider ->
                setOf(SkaldVaultV1ProviderInterfaceContractBlocker.ProviderSelectionLockedToDisabledProvider)
            SkaldVaultV1ProviderInterfaceContractRequiredProperty.ProductionProviderSelectableFalse ->
                setOf(SkaldVaultV1ProviderInterfaceContractBlocker.ProductionProviderSelectableFalse)
            SkaldVaultV1ProviderInterfaceContractRequiredProperty.DiagnosticsRedacted ->
                setOf(SkaldVaultV1ProviderInterfaceContractBlocker.DiagnosticsRedacted)
        }

    private val baseStatuses: Set<SkaldVaultV1ProviderInterfaceContractAuditStatus> = setOf(
        SkaldVaultV1ProviderInterfaceContractAuditStatus.BoundaryModeled,
        SkaldVaultV1ProviderInterfaceContractAuditStatus.StillDisabled,
        SkaldVaultV1ProviderInterfaceContractAuditStatus.EvidenceOnly,
        SkaldVaultV1ProviderInterfaceContractAuditStatus.OperationExecutionBlocked,
        SkaldVaultV1ProviderInterfaceContractAuditStatus.PromotionBlocked,
        SkaldVaultV1ProviderInterfaceContractAuditStatus.SelectionBlocked,
        SkaldVaultV1ProviderInterfaceContractAuditStatus.WarningOnlyCannotAuthorize,
        SkaldVaultV1ProviderInterfaceContractAuditStatus.UserConsentCannotOverride,
        SkaldVaultV1ProviderInterfaceContractAuditStatus.TestOnlyRejectedForProduction,
    )

    private val baseOverrideBlockers: Set<SkaldVaultV1ProviderInterfaceContractBlocker> = setOf(
        SkaldVaultV1ProviderInterfaceContractBlocker.ProviderInterfaceAuditStillDisabled,
        SkaldVaultV1ProviderInterfaceContractBlocker.ProviderSelectionLockedToDisabledProvider,
        SkaldVaultV1ProviderInterfaceContractBlocker.ProductionProviderSelectableFalse,
        SkaldVaultV1ProviderInterfaceContractBlocker.AuthorizationReadinessMatrixBlocked,
        SkaldVaultV1ProviderInterfaceContractBlocker.WarningOnlyEvidenceCannotAuthorize,
        SkaldVaultV1ProviderInterfaceContractBlocker.UserConsentCannotOverride,
        SkaldVaultV1ProviderInterfaceContractBlocker.TestOnlyEvidenceCannotAuthorizeProduction,
        SkaldVaultV1ProviderInterfaceContractBlocker.MainnetDisabled,
    )
}
