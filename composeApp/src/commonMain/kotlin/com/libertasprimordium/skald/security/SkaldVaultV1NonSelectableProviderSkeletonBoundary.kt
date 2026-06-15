package com.libertasprimordium.skald.security

interface SkaldVaultV1NonSelectableProviderSkeletonBoundary {
    fun evaluate(
        request: SkaldVaultV1NonSelectableProviderSkeletonRequest,
    ): SkaldVaultV1NonSelectableProviderSkeletonResult<SkaldVaultV1NonSelectableProviderSkeletonEvidence>
}

enum class SkaldVaultV1NonSelectableProviderSkeletonSource(val label: String) {
    CurrentSkeletonEvidence("current non-selectable provider skeleton evidence"),
    SkeletonTopicAudit("provider skeleton topic audit"),
    OperationSurfaceAudit("provider skeleton operation-surface audit"),
    EvidenceInteractionAudit("provider skeleton evidence interaction audit"),
}

enum class SkaldVaultV1NonSelectableProviderSkeletonStatus(val label: String) {
    BoundaryModeled("non-selectable provider skeleton boundary modeled"),
    StillDisabled("still disabled"),
    EvidenceOnly("evidence only"),
    CompileShapeOnly("compile shape only"),
    NonRegistered("not registered"),
    NonSelectable("non-selectable"),
    NonInstantiableByRegistry("not instantiable by provider registry"),
    OperationSurfaceDisabled("operation surface disabled"),
    KatSurfaceDisabled("KAT surface disabled"),
    RandomnessSurfaceDisabled("randomness surface disabled"),
    KdfSurfaceDisabled("KDF surface disabled"),
    AeadSurfaceDisabled("AEAD surface disabled"),
    KeyWrappingSurfaceDisabled("key wrapping surface disabled"),
    PromotionBlocked("provider promotion blocked"),
    WarningOnlyCannotAuthorize("warning-only evidence cannot authorize"),
    UserConsentCannotOverride("user consent cannot override"),
    TestOnlyRejectedForProduction("test-only evidence rejected for production"),
    UnsupportedFailClosed("unsupported fail closed"),
}

enum class SkaldVaultV1ProviderSkeletonFamily(val label: String) {
    TinkBouncyCastleSplitFutureSkeleton("Tink plus Bouncy Castle split-stack future skeleton"),
    TinkJvmFutureSkeleton("Tink JVM future skeleton"),
    BouncyCastleJvmFutureSkeleton("Bouncy Castle JVM future skeleton"),
    AndroidKeystoreWrapperFutureSkeleton("Android Keystore wrapper future skeleton"),
    PlatformOsCsprngFutureSkeleton("platform OS CSPRNG future skeleton"),
    TestOnlyDeterministicFutureSkeleton("test-only deterministic future skeleton"),
    UnknownFutureSkeleton("unknown future skeleton"),
    UnsupportedFutureSkeleton("unsupported future skeleton"),
}

enum class SkaldVaultV1ProviderSkeletonSourceSetPlacement(val label: String) {
    CommonModelEvidenceOnly("common model/evidence only"),
    CommonRuntimeImplementationForbidden("common runtime implementation forbidden"),
    AndroidRuntimeImplementationForbiddenThisBranch("Android runtime implementation forbidden in this branch"),
    DesktopRuntimeImplementationForbiddenThisBranch("desktop runtime implementation forbidden in this branch"),
    TestScaffoldingFutureReviewOnly("test scaffolding future-review only"),
}

enum class SkaldVaultV1ProviderSkeletonDependencyVisibility(val label: String) {
    BuildEvidenceOnly("build evidence only"),
    SourceSetDeclaredOnly("source-set declared only"),
    NotActive("not active"),
    NotImportedBySkeleton("not imported by skeleton"),
    FutureReviewRequired("future review required"),
}

enum class SkaldVaultV1ProviderSkeletonTopic(val label: String) {
    FutureProviderNameId("future provider name/id"),
    ProviderFamily("provider family"),
    SourceSetPlacement("source-set placement"),
    DependencyVisibility("dependency visibility"),
    ProviderImplementationClassStatus("provider implementation class status"),
    FactoryClassStatus("provider factory class status"),
    ProviderRegistryStatus("provider registry status"),
    ProviderSelectableStatus("provider selectable status"),
    OperationSurfaceStatus("operation-surface status"),
    KatSurfaceStatus("KAT surface status"),
    RandomnessSurfaceStatus("randomness surface status"),
    KdfSurfaceStatus("KDF surface status"),
    AeadSurfaceStatus("AEAD surface status"),
    HkdfHmacSurfaceStatus("HKDF/HMAC surface status"),
    HeaderCommitmentSurfaceStatus("header commitment surface status"),
    KeyWrappingSurfaceStatus("key wrapping surface status"),
    ClearDisposeSurfaceStatus("clear/dispose surface status"),
    DiagnosticsRedactionStatus("diagnostics/redaction status"),
    AcceptanceGateStatus("acceptance-gate status"),
    PromotionBlockerStatus("promotion-blocker status"),
    ReadinessMatrixStatus("readiness-matrix status"),
}

enum class SkaldVaultV1ProviderSkeletonOperationSurface(val label: String) {
    AvailabilityCheck("availability check"),
    ProviderSelfTest("provider self-test"),
    ProviderKat("provider KAT"),
    RuntimeRandomnessCheck("runtime randomness check"),
    RandomnessRequest("randomness request"),
    SaltGeneration("salt generation"),
    NonceGeneration("nonce generation"),
    KeyGeneration("key generation"),
    KdfArgon2id("KDF/Argon2id"),
    HkdfExtractExpand("HKDF extract/expand"),
    Hmac("HMAC"),
    HeaderCommitmentCompute("header commitment compute"),
    HeaderCommitmentVerify("header commitment verify"),
    AeadEncrypt("AEAD encrypt"),
    AeadDecrypt("AEAD decrypt"),
    RecordEncrypt("record encrypt"),
    RecordDecrypt("record decrypt"),
    KeyWrap("key wrap"),
    KeyUnwrap("key unwrap"),
    ProviderClearDispose("provider clear/dispose"),
}

enum class SkaldVaultV1ProviderSkeletonBlocker(val label: String) {
    SkeletonStillDisabled("provider skeleton still disabled"),
    ModelEvidenceOnly("model evidence only"),
    CompileShapeOnly("compile shape only"),
    RuntimeProviderImplementationForbidden("runtime provider implementation forbidden"),
    ProviderImplementationMissing("provider implementation missing"),
    FactoryMissing("provider factory missing"),
    RegistryDisabled("provider registry disabled"),
    ProviderSelectionLockedToDisabledProvider("provider selection locked to disabled provider"),
    ProductionProviderSelectableFalse("productionProviderSelectable is false"),
    OperationSurfaceDisabled("operation surface disabled"),
    ProviderOperationAuthorizationBlocked("provider operation authorization blocked"),
    KatExecutionUnavailable("KAT execution unavailable"),
    RuntimeRandomnessAuthorizationBlocked("runtime randomness authorization blocked"),
    KdfCalibrationAuthorizationBlocked("KDF calibration authorization blocked"),
    SecureStorageAuthorizationBlocked("secure-storage authorization blocked"),
    CreationAuthorizationBlocked("creation authorization blocked"),
    UnlockAuthorizationBlocked("unlock authorization blocked"),
    ProviderInterfaceAuditRequired("provider interface audit required"),
    ProviderSelectionPromotionBlocked("provider selection promotion blocked"),
    ProviderCandidatePackagingStillDisabled("provider candidate packaging still disabled"),
    ProviderDependencyBuildStillDisabled("provider dependency build still disabled"),
    AuthorizationReadinessMatrixBlocked("authorization/readiness matrix blocked"),
    ProviderAcceptanceMissing("provider acceptance missing"),
    DiagnosticsRedacted("diagnostics redacted"),
    WarningOnlyEvidenceCannotAuthorize("warning-only evidence cannot authorize"),
    UserConsentCannotOverride("user consent cannot override"),
    TestOnlyEvidenceCannotAuthorizeProduction("test-only evidence cannot authorize production"),
    MainnetDisabled("mainnet disabled"),
}

enum class SkaldVaultV1ProviderSkeletonRedactionClass(val label: String) {
    PolicyIdsOnly("policy ids only"),
    SkeletonTopicOnly("skeleton topic only"),
    OperationSurfaceOnly("operation surface only"),
    BoundaryAndBlockerClassesOnly("boundary and blocker classes only"),
    NoDiagnosticPayload("no diagnostic payload"),
}

data class SkaldVaultV1ProviderSkeletonCapability(
    val providerSkeletonModeled: Boolean,
    val providerSkeletonImplementsRuntimeProvider: Boolean,
    val providerSkeletonRegistered: Boolean,
    val providerSkeletonSelectable: Boolean,
    val providerSkeletonInstantiableByRegistry: Boolean,
    val providerSkeletonExecutesOperations: Boolean,
    val providerSkeletonRunsKat: Boolean,
    val providerSkeletonUsesRandomness: Boolean,
    val providerSkeletonRunsKdf: Boolean,
    val providerSkeletonRunsAead: Boolean,
    val providerSkeletonWrapsKeys: Boolean,
    val providerSkeletonCreatesVault: Boolean,
    val providerSkeletonUnlocksVault: Boolean,
    val providerSkeletonPersistsVault: Boolean,
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
        val StillDisabled = SkaldVaultV1ProviderSkeletonCapability(
            providerSkeletonModeled = true,
            providerSkeletonImplementsRuntimeProvider = false,
            providerSkeletonRegistered = false,
            providerSkeletonSelectable = false,
            providerSkeletonInstantiableByRegistry = false,
            providerSkeletonExecutesOperations = false,
            providerSkeletonRunsKat = false,
            providerSkeletonUsesRandomness = false,
            providerSkeletonRunsKdf = false,
            providerSkeletonRunsAead = false,
            providerSkeletonWrapsKeys = false,
            providerSkeletonCreatesVault = false,
            providerSkeletonUnlocksVault = false,
            providerSkeletonPersistsVault = false,
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

class SkaldVaultV1ProviderSkeletonPolicyToken private constructor(
    val policyId: String,
    val topic: SkaldVaultV1ProviderSkeletonTopic?,
    val operationSurface: SkaldVaultV1ProviderSkeletonOperationSurface?,
) {
    val containsProviderHandle: Boolean = false
    val containsCryptoObject: Boolean = false
    val containsByteMaterial: Boolean = false
    val containsPathOrRootText: Boolean = false
    val containsStorageIdentifier: Boolean = false
    val containsSecretMaterial: Boolean = false

    override fun toString(): String =
        "SkaldVaultV1ProviderSkeletonPolicyToken(" +
            "policyId=$policyId, " +
            "topic=$topic, " +
            "operationSurface=$operationSurface, " +
            "providerHandle:<redacted>, " +
            "cryptoObject:<redacted>, " +
            "byteMaterial:<redacted>, " +
            "pathOrRoot:<redacted>, " +
            "storageIdentifier:<redacted>, " +
            "secretMaterial:<redacted>" +
            ")"

    companion object {
        fun redacted(
            policyId: String,
            topic: SkaldVaultV1ProviderSkeletonTopic?,
            operationSurface: SkaldVaultV1ProviderSkeletonOperationSurface?,
        ): SkaldVaultV1ProviderSkeletonPolicyToken {
            return SkaldVaultV1ProviderSkeletonPolicyToken(policyId, topic, operationSurface)
        }
    }
}

class SkaldVaultV1NonSelectableProviderSkeletonRequest private constructor(
    val source: SkaldVaultV1NonSelectableProviderSkeletonSource,
    val family: SkaldVaultV1ProviderSkeletonFamily,
    val sourceSetPlacement: SkaldVaultV1ProviderSkeletonSourceSetPlacement,
    val dependencyVisibility: SkaldVaultV1ProviderSkeletonDependencyVisibility,
    val topic: SkaldVaultV1ProviderSkeletonTopic?,
    val operationSurface: SkaldVaultV1ProviderSkeletonOperationSurface?,
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
        "SkaldVaultV1NonSelectableProviderSkeletonRequest(" +
            "source=$source, " +
            "family=$family, " +
            "sourceSetPlacement=$sourceSetPlacement, " +
            "dependencyVisibility=$dependencyVisibility, " +
            "topic=$topic, " +
            "operationSurface=$operationSurface, " +
            "providerInterfaceAudit:<redacted>, " +
            "providerSelectionPromotion:<redacted>, " +
            "dependencyBuild:<redacted>, " +
            "providerCandidatePackaging:<redacted>, " +
            "authorizationReadinessMatrix:<redacted>, " +
            "providerOperation:<redacted>, " +
            "runtimeRandomness:<redacted>, " +
            "kdfCalibration:<redacted>, " +
            "secureStorage:<redacted>, " +
            "creationAuthorization:<redacted>, " +
            "unlockAuthorization:<redacted>" +
            ")"

    companion object {
        fun currentEvidence(
            family: SkaldVaultV1ProviderSkeletonFamily =
                SkaldVaultV1ProviderSkeletonFamily.TinkBouncyCastleSplitFutureSkeleton,
            sourceSetPlacement: SkaldVaultV1ProviderSkeletonSourceSetPlacement =
                SkaldVaultV1ProviderSkeletonSourceSetPlacement.CommonModelEvidenceOnly,
            dependencyVisibility: SkaldVaultV1ProviderSkeletonDependencyVisibility =
                SkaldVaultV1ProviderSkeletonDependencyVisibility.BuildEvidenceOnly,
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
        ): SkaldVaultV1NonSelectableProviderSkeletonRequest =
            SkaldVaultV1NonSelectableProviderSkeletonRequest(
                source = SkaldVaultV1NonSelectableProviderSkeletonSource.CurrentSkeletonEvidence,
                family = family,
                sourceSetPlacement = sourceSetPlacement,
                dependencyVisibility = dependencyVisibility,
                topic = null,
                operationSurface = null,
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
            topic: SkaldVaultV1ProviderSkeletonTopic,
        ): SkaldVaultV1NonSelectableProviderSkeletonRequest =
            SkaldVaultV1NonSelectableProviderSkeletonRequest(
                source = SkaldVaultV1NonSelectableProviderSkeletonSource.SkeletonTopicAudit,
                family = SkaldVaultV1ProviderSkeletonFamily.TinkBouncyCastleSplitFutureSkeleton,
                sourceSetPlacement = SkaldVaultV1ProviderSkeletonSourceSetPlacement.CommonModelEvidenceOnly,
                dependencyVisibility = SkaldVaultV1ProviderSkeletonDependencyVisibility.BuildEvidenceOnly,
                topic = topic,
                operationSurface = null,
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

        fun forOperationSurface(
            operationSurface: SkaldVaultV1ProviderSkeletonOperationSurface,
        ): SkaldVaultV1NonSelectableProviderSkeletonRequest =
            SkaldVaultV1NonSelectableProviderSkeletonRequest(
                source = SkaldVaultV1NonSelectableProviderSkeletonSource.OperationSurfaceAudit,
                family = SkaldVaultV1ProviderSkeletonFamily.TinkBouncyCastleSplitFutureSkeleton,
                sourceSetPlacement = SkaldVaultV1ProviderSkeletonSourceSetPlacement.CommonModelEvidenceOnly,
                dependencyVisibility = SkaldVaultV1ProviderSkeletonDependencyVisibility.BuildEvidenceOnly,
                topic = null,
                operationSurface = operationSurface,
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

        fun evidenceInteractionAudit(): SkaldVaultV1NonSelectableProviderSkeletonRequest =
            SkaldVaultV1NonSelectableProviderSkeletonRequest(
                source = SkaldVaultV1NonSelectableProviderSkeletonSource.EvidenceInteractionAudit,
                family = SkaldVaultV1ProviderSkeletonFamily.TinkBouncyCastleSplitFutureSkeleton,
                sourceSetPlacement = SkaldVaultV1ProviderSkeletonSourceSetPlacement.CommonModelEvidenceOnly,
                dependencyVisibility = SkaldVaultV1ProviderSkeletonDependencyVisibility.BuildEvidenceOnly,
                topic = null,
                operationSurface = null,
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

data class SkaldVaultV1ProviderSkeletonTopicRow(
    val topic: SkaldVaultV1ProviderSkeletonTopic,
    val statuses: Set<SkaldVaultV1NonSelectableProviderSkeletonStatus>,
    val blockers: Set<SkaldVaultV1ProviderSkeletonBlocker>,
    val modeled: Boolean,
    val evidenceOnly: Boolean,
    val runtimeAvailable: Boolean,
    val selectable: Boolean,
    val registered: Boolean,
    val instantiableByRegistry: Boolean,
    val requiredFutureEvidence: Set<SkaldVaultV1ProviderSkeletonBlocker>,
    val redactionClass: SkaldVaultV1ProviderSkeletonRedactionClass,
)

data class SkaldVaultV1ProviderSkeletonOperationSurfaceRow(
    val operationSurface: SkaldVaultV1ProviderSkeletonOperationSurface,
    val statuses: Set<SkaldVaultV1NonSelectableProviderSkeletonStatus>,
    val blockers: Set<SkaldVaultV1ProviderSkeletonBlocker>,
    val modeled: Boolean,
    val disabled: Boolean,
    val executable: Boolean,
    val authorized: Boolean,
    val productionRuntimeAvailable: Boolean,
    val redactionClass: SkaldVaultV1ProviderSkeletonRedactionClass,
)

data class SkaldVaultV1NonSelectableProviderSkeletonSummary(
    val policyId: String,
    val policyVersion: Int,
    val statuses: Set<SkaldVaultV1NonSelectableProviderSkeletonStatus>,
    val families: Set<SkaldVaultV1ProviderSkeletonFamily>,
    val sourceSetPlacements: Set<SkaldVaultV1ProviderSkeletonSourceSetPlacement>,
    val dependencyVisibility: Set<SkaldVaultV1ProviderSkeletonDependencyVisibility>,
    val topics: Set<SkaldVaultV1ProviderSkeletonTopic>,
    val operationSurfaces: Set<SkaldVaultV1ProviderSkeletonOperationSurface>,
    val blockers: Set<SkaldVaultV1ProviderSkeletonBlocker>,
    val redactionClasses: Set<SkaldVaultV1ProviderSkeletonRedactionClass>,
    val capability: SkaldVaultV1ProviderSkeletonCapability,
    val stillDisabled: Boolean,
    val evidenceOnly: Boolean,
    val compileShapeOnly: Boolean,
    val nonSelectable: Boolean,
)

data class SkaldVaultV1NonSelectableProviderSkeletonEvidence(
    val policyId: String,
    val policyVersion: Int,
    val source: SkaldVaultV1NonSelectableProviderSkeletonSource,
    val status: SkaldVaultV1NonSelectableProviderSkeletonStatus,
    val family: SkaldVaultV1ProviderSkeletonFamily,
    val sourceSetPlacement: SkaldVaultV1ProviderSkeletonSourceSetPlacement,
    val dependencyVisibility: SkaldVaultV1ProviderSkeletonDependencyVisibility,
    val topicRows: List<SkaldVaultV1ProviderSkeletonTopicRow>,
    val operationSurfaceRows: List<SkaldVaultV1ProviderSkeletonOperationSurfaceRow>,
    val blockers: Set<SkaldVaultV1ProviderSkeletonBlocker>,
    val capability: SkaldVaultV1ProviderSkeletonCapability,
    val redactedPolicyEvidence: SkaldVaultV1ProviderSkeletonPolicyToken,
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
    val nonSelectableProviderSkeletonBoundaryModeled: Boolean = true,
    val nonSelectableProviderSkeletonStillDisabled: Boolean = true,
    val nonSelectableProviderSkeletonDoesNotImplementExecutableProvider: Boolean = true,
    val nonSelectableProviderSkeletonDoesNotAddFactory: Boolean = true,
    val nonSelectableProviderSkeletonDoesNotRegisterProvider: Boolean = true,
    val nonSelectableProviderSkeletonDoesNotEnableProviderSelection: Boolean = true,
    val nonSelectableProviderSkeletonDoesNotRunCrypto: Boolean = true,
    val nonSelectableProviderSkeletonDoesNotRunKat: Boolean = true,
    val nonSelectableProviderSkeletonDoesNotEnableCreation: Boolean = true,
    val nonSelectableProviderSkeletonDoesNotEnableUnlock: Boolean = true,
    val nonSelectableProviderSkeletonDoesNotEnablePersistence: Boolean = true,
    val providerSkeletonModeled: Boolean = true,
    val providerSkeletonImplementsRuntimeProvider: Boolean = false,
    val providerSkeletonRegistered: Boolean = false,
    val providerSkeletonSelectable: Boolean = false,
    val providerSkeletonInstantiableByRegistry: Boolean = false,
    val providerSkeletonExecutesOperations: Boolean = false,
    val providerSkeletonRunsKat: Boolean = false,
    val providerSkeletonUsesRandomness: Boolean = false,
    val providerSkeletonRunsKdf: Boolean = false,
    val providerSkeletonRunsAead: Boolean = false,
    val providerSkeletonWrapsKeys: Boolean = false,
    val providerSkeletonCreatesVault: Boolean = false,
    val providerSkeletonUnlocksVault: Boolean = false,
    val providerSkeletonPersistsVault: Boolean = false,
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
        "SkaldVaultV1NonSelectableProviderSkeletonEvidence(" +
            "policyId=$policyId, " +
            "policyVersion=$policyVersion, " +
            "source=$source, " +
            "status=$status, " +
            "family=$family, " +
            "topicRows=${topicRows.size}, " +
            "operationSurfaceRows=${operationSurfaceRows.size}, " +
            "blockers=${blockers.size}, " +
            "redactedPolicyEvidence:<redacted>" +
            ")"
}

sealed class SkaldVaultV1NonSelectableProviderSkeletonResult<out T> {
    data class Blocked<out T>(
        val value: T,
    ) : SkaldVaultV1NonSelectableProviderSkeletonResult<T>() {
        override fun toString(): String =
            "SkaldVaultV1NonSelectableProviderSkeletonResult.Blocked(" +
                "value=<redacted-non-selectable-provider-skeleton-evidence>)"
    }
}

object SkaldVaultV1NonSelectableProviderSkeletonPolicy :
    SkaldVaultV1NonSelectableProviderSkeletonBoundary {
    const val POLICY_ID = "skald-vault-v1-non-selectable-provider-skeleton-boundary-v1"
    const val POLICY_VERSION = 1

    override fun evaluate(
        request: SkaldVaultV1NonSelectableProviderSkeletonRequest,
    ): SkaldVaultV1NonSelectableProviderSkeletonResult<SkaldVaultV1NonSelectableProviderSkeletonEvidence> {
        val topicRows = topicRowsFor(request.topic)
        val operationRows = operationRowsFor(request.operationSurface)
        return SkaldVaultV1NonSelectableProviderSkeletonResult.Blocked(
            SkaldVaultV1NonSelectableProviderSkeletonEvidence(
                policyId = POLICY_ID,
                policyVersion = POLICY_VERSION,
                source = request.source,
                status = SkaldVaultV1NonSelectableProviderSkeletonStatus.StillDisabled,
                family = request.family,
                sourceSetPlacement = request.sourceSetPlacement,
                dependencyVisibility = request.dependencyVisibility,
                topicRows = topicRows,
                operationSurfaceRows = operationRows,
                blockers = topicRows.flatMap { it.blockers }.toSet() +
                    operationRows.flatMap { it.blockers }.toSet() +
                    baseBlockers,
                capability = SkaldVaultV1ProviderSkeletonCapability.StillDisabled,
                redactedPolicyEvidence = SkaldVaultV1ProviderSkeletonPolicyToken.redacted(
                    policyId = POLICY_ID,
                    topic = request.topic,
                    operationSurface = request.operationSurface,
                ),
                providerInterfaceAuditEvidenceConsumed =
                    request.providerInterfaceAuditEvidenceSupplied,
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

    fun currentPolicySummary(): SkaldVaultV1NonSelectableProviderSkeletonSummary =
        SkaldVaultV1NonSelectableProviderSkeletonSummary(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            statuses = SkaldVaultV1NonSelectableProviderSkeletonStatus.entries.toSet(),
            families = SkaldVaultV1ProviderSkeletonFamily.entries.toSet(),
            sourceSetPlacements = SkaldVaultV1ProviderSkeletonSourceSetPlacement.entries.toSet(),
            dependencyVisibility = SkaldVaultV1ProviderSkeletonDependencyVisibility.entries.toSet(),
            topics = SkaldVaultV1ProviderSkeletonTopic.entries.toSet(),
            operationSurfaces = SkaldVaultV1ProviderSkeletonOperationSurface.entries.toSet(),
            blockers = SkaldVaultV1ProviderSkeletonBlocker.entries.toSet(),
            redactionClasses = SkaldVaultV1ProviderSkeletonRedactionClass.entries.toSet(),
            capability = SkaldVaultV1ProviderSkeletonCapability.StillDisabled,
            stillDisabled = true,
            evidenceOnly = true,
            compileShapeOnly = true,
            nonSelectable = true,
        )

    private fun topicRowsFor(
        topic: SkaldVaultV1ProviderSkeletonTopic?,
    ): List<SkaldVaultV1ProviderSkeletonTopicRow> {
        val rows = SkaldVaultV1ProviderSkeletonTopic.entries.map { currentTopic ->
            topicRow(currentTopic)
        }
        return topic?.let { requested -> rows.filter { it.topic == requested } } ?: rows
    }

    private fun operationRowsFor(
        operationSurface: SkaldVaultV1ProviderSkeletonOperationSurface?,
    ): List<SkaldVaultV1ProviderSkeletonOperationSurfaceRow> {
        val rows = SkaldVaultV1ProviderSkeletonOperationSurface.entries.map { currentSurface ->
            operationSurfaceRow(currentSurface)
        }
        return operationSurface?.let { requested ->
            rows.filter { it.operationSurface == requested }
        } ?: rows
    }

    private fun topicRow(
        topic: SkaldVaultV1ProviderSkeletonTopic,
    ): SkaldVaultV1ProviderSkeletonTopicRow {
        val blockers = topicBlockers(topic) + baseBlockers
        return SkaldVaultV1ProviderSkeletonTopicRow(
            topic = topic,
            statuses = baseStatuses + topicStatus(topic),
            blockers = blockers,
            modeled = true,
            evidenceOnly = true,
            runtimeAvailable = false,
            selectable = false,
            registered = false,
            instantiableByRegistry = false,
            requiredFutureEvidence = blockers,
            redactionClass = SkaldVaultV1ProviderSkeletonRedactionClass.SkeletonTopicOnly,
        )
    }

    private fun operationSurfaceRow(
        operationSurface: SkaldVaultV1ProviderSkeletonOperationSurface,
    ): SkaldVaultV1ProviderSkeletonOperationSurfaceRow =
        SkaldVaultV1ProviderSkeletonOperationSurfaceRow(
            operationSurface = operationSurface,
            statuses = baseStatuses + operationStatus(operationSurface),
            blockers = operationBlockers(operationSurface) + baseBlockers,
            modeled = true,
            disabled = true,
            executable = false,
            authorized = false,
            productionRuntimeAvailable = false,
            redactionClass = SkaldVaultV1ProviderSkeletonRedactionClass.OperationSurfaceOnly,
        )

    private fun topicStatus(
        topic: SkaldVaultV1ProviderSkeletonTopic,
    ): SkaldVaultV1NonSelectableProviderSkeletonStatus =
        when (topic) {
            SkaldVaultV1ProviderSkeletonTopic.FutureProviderNameId,
            SkaldVaultV1ProviderSkeletonTopic.ProviderFamily,
            SkaldVaultV1ProviderSkeletonTopic.SourceSetPlacement,
            SkaldVaultV1ProviderSkeletonTopic.DependencyVisibility ->
                SkaldVaultV1NonSelectableProviderSkeletonStatus.CompileShapeOnly
            SkaldVaultV1ProviderSkeletonTopic.ProviderRegistryStatus,
            SkaldVaultV1ProviderSkeletonTopic.ProviderSelectableStatus ->
                SkaldVaultV1NonSelectableProviderSkeletonStatus.NonSelectable
            SkaldVaultV1ProviderSkeletonTopic.DiagnosticsRedactionStatus ->
                SkaldVaultV1NonSelectableProviderSkeletonStatus.EvidenceOnly
            else -> SkaldVaultV1NonSelectableProviderSkeletonStatus.OperationSurfaceDisabled
        }

    private fun operationStatus(
        operationSurface: SkaldVaultV1ProviderSkeletonOperationSurface,
    ): SkaldVaultV1NonSelectableProviderSkeletonStatus =
        when (operationSurface) {
            SkaldVaultV1ProviderSkeletonOperationSurface.ProviderKat,
            SkaldVaultV1ProviderSkeletonOperationSurface.ProviderSelfTest ->
                SkaldVaultV1NonSelectableProviderSkeletonStatus.KatSurfaceDisabled
            SkaldVaultV1ProviderSkeletonOperationSurface.RuntimeRandomnessCheck,
            SkaldVaultV1ProviderSkeletonOperationSurface.RandomnessRequest,
            SkaldVaultV1ProviderSkeletonOperationSurface.SaltGeneration,
            SkaldVaultV1ProviderSkeletonOperationSurface.NonceGeneration,
            SkaldVaultV1ProviderSkeletonOperationSurface.KeyGeneration ->
                SkaldVaultV1NonSelectableProviderSkeletonStatus.RandomnessSurfaceDisabled
            SkaldVaultV1ProviderSkeletonOperationSurface.KdfArgon2id,
            SkaldVaultV1ProviderSkeletonOperationSurface.HkdfExtractExpand,
            SkaldVaultV1ProviderSkeletonOperationSurface.Hmac,
            SkaldVaultV1ProviderSkeletonOperationSurface.HeaderCommitmentCompute,
            SkaldVaultV1ProviderSkeletonOperationSurface.HeaderCommitmentVerify ->
                SkaldVaultV1NonSelectableProviderSkeletonStatus.KdfSurfaceDisabled
            SkaldVaultV1ProviderSkeletonOperationSurface.AeadEncrypt,
            SkaldVaultV1ProviderSkeletonOperationSurface.AeadDecrypt,
            SkaldVaultV1ProviderSkeletonOperationSurface.RecordEncrypt,
            SkaldVaultV1ProviderSkeletonOperationSurface.RecordDecrypt ->
                SkaldVaultV1NonSelectableProviderSkeletonStatus.AeadSurfaceDisabled
            SkaldVaultV1ProviderSkeletonOperationSurface.KeyWrap,
            SkaldVaultV1ProviderSkeletonOperationSurface.KeyUnwrap ->
                SkaldVaultV1NonSelectableProviderSkeletonStatus.KeyWrappingSurfaceDisabled
            else -> SkaldVaultV1NonSelectableProviderSkeletonStatus.OperationSurfaceDisabled
        }

    private fun topicBlockers(
        topic: SkaldVaultV1ProviderSkeletonTopic,
    ): Set<SkaldVaultV1ProviderSkeletonBlocker> =
        when (topic) {
            SkaldVaultV1ProviderSkeletonTopic.FutureProviderNameId,
            SkaldVaultV1ProviderSkeletonTopic.ProviderFamily,
            SkaldVaultV1ProviderSkeletonTopic.SourceSetPlacement ->
                setOf(
                    SkaldVaultV1ProviderSkeletonBlocker.CompileShapeOnly,
                    SkaldVaultV1ProviderSkeletonBlocker.ModelEvidenceOnly,
                )
            SkaldVaultV1ProviderSkeletonTopic.DependencyVisibility ->
                setOf(SkaldVaultV1ProviderSkeletonBlocker.ProviderDependencyBuildStillDisabled)
            SkaldVaultV1ProviderSkeletonTopic.ProviderImplementationClassStatus ->
                setOf(
                    SkaldVaultV1ProviderSkeletonBlocker.RuntimeProviderImplementationForbidden,
                    SkaldVaultV1ProviderSkeletonBlocker.ProviderImplementationMissing,
                )
            SkaldVaultV1ProviderSkeletonTopic.FactoryClassStatus ->
                setOf(SkaldVaultV1ProviderSkeletonBlocker.FactoryMissing)
            SkaldVaultV1ProviderSkeletonTopic.ProviderRegistryStatus ->
                setOf(
                    SkaldVaultV1ProviderSkeletonBlocker.RegistryDisabled,
                    SkaldVaultV1ProviderSkeletonBlocker.ProviderSelectionLockedToDisabledProvider,
                )
            SkaldVaultV1ProviderSkeletonTopic.ProviderSelectableStatus ->
                setOf(
                    SkaldVaultV1ProviderSkeletonBlocker.ProviderSelectionLockedToDisabledProvider,
                    SkaldVaultV1ProviderSkeletonBlocker.ProductionProviderSelectableFalse,
                )
            SkaldVaultV1ProviderSkeletonTopic.OperationSurfaceStatus ->
                setOf(
                    SkaldVaultV1ProviderSkeletonBlocker.OperationSurfaceDisabled,
                    SkaldVaultV1ProviderSkeletonBlocker.ProviderOperationAuthorizationBlocked,
                )
            SkaldVaultV1ProviderSkeletonTopic.KatSurfaceStatus ->
                setOf(SkaldVaultV1ProviderSkeletonBlocker.KatExecutionUnavailable)
            SkaldVaultV1ProviderSkeletonTopic.RandomnessSurfaceStatus ->
                setOf(SkaldVaultV1ProviderSkeletonBlocker.RuntimeRandomnessAuthorizationBlocked)
            SkaldVaultV1ProviderSkeletonTopic.KdfSurfaceStatus,
            SkaldVaultV1ProviderSkeletonTopic.HkdfHmacSurfaceStatus,
            SkaldVaultV1ProviderSkeletonTopic.HeaderCommitmentSurfaceStatus ->
                setOf(SkaldVaultV1ProviderSkeletonBlocker.KdfCalibrationAuthorizationBlocked)
            SkaldVaultV1ProviderSkeletonTopic.AeadSurfaceStatus ->
                setOf(SkaldVaultV1ProviderSkeletonBlocker.ProviderOperationAuthorizationBlocked)
            SkaldVaultV1ProviderSkeletonTopic.KeyWrappingSurfaceStatus ->
                setOf(SkaldVaultV1ProviderSkeletonBlocker.SecureStorageAuthorizationBlocked)
            SkaldVaultV1ProviderSkeletonTopic.ClearDisposeSurfaceStatus ->
                setOf(SkaldVaultV1ProviderSkeletonBlocker.OperationSurfaceDisabled)
            SkaldVaultV1ProviderSkeletonTopic.DiagnosticsRedactionStatus ->
                setOf(SkaldVaultV1ProviderSkeletonBlocker.DiagnosticsRedacted)
            SkaldVaultV1ProviderSkeletonTopic.AcceptanceGateStatus ->
                setOf(SkaldVaultV1ProviderSkeletonBlocker.ProviderAcceptanceMissing)
            SkaldVaultV1ProviderSkeletonTopic.PromotionBlockerStatus ->
                setOf(SkaldVaultV1ProviderSkeletonBlocker.ProviderSelectionPromotionBlocked)
            SkaldVaultV1ProviderSkeletonTopic.ReadinessMatrixStatus ->
                setOf(SkaldVaultV1ProviderSkeletonBlocker.AuthorizationReadinessMatrixBlocked)
        }

    private fun operationBlockers(
        operationSurface: SkaldVaultV1ProviderSkeletonOperationSurface,
    ): Set<SkaldVaultV1ProviderSkeletonBlocker> =
        when (operationSurface) {
            SkaldVaultV1ProviderSkeletonOperationSurface.AvailabilityCheck ->
                setOf(
                    SkaldVaultV1ProviderSkeletonBlocker.OperationSurfaceDisabled,
                    SkaldVaultV1ProviderSkeletonBlocker.RegistryDisabled,
                )
            SkaldVaultV1ProviderSkeletonOperationSurface.ProviderSelfTest,
            SkaldVaultV1ProviderSkeletonOperationSurface.ProviderKat ->
                setOf(
                    SkaldVaultV1ProviderSkeletonBlocker.KatExecutionUnavailable,
                    SkaldVaultV1ProviderSkeletonBlocker.ProviderOperationAuthorizationBlocked,
                )
            SkaldVaultV1ProviderSkeletonOperationSurface.RuntimeRandomnessCheck,
            SkaldVaultV1ProviderSkeletonOperationSurface.RandomnessRequest,
            SkaldVaultV1ProviderSkeletonOperationSurface.SaltGeneration,
            SkaldVaultV1ProviderSkeletonOperationSurface.NonceGeneration,
            SkaldVaultV1ProviderSkeletonOperationSurface.KeyGeneration ->
                setOf(
                    SkaldVaultV1ProviderSkeletonBlocker.RuntimeRandomnessAuthorizationBlocked,
                    SkaldVaultV1ProviderSkeletonBlocker.ProviderOperationAuthorizationBlocked,
                )
            SkaldVaultV1ProviderSkeletonOperationSurface.KdfArgon2id,
            SkaldVaultV1ProviderSkeletonOperationSurface.HkdfExtractExpand,
            SkaldVaultV1ProviderSkeletonOperationSurface.Hmac,
            SkaldVaultV1ProviderSkeletonOperationSurface.HeaderCommitmentCompute,
            SkaldVaultV1ProviderSkeletonOperationSurface.HeaderCommitmentVerify ->
                setOf(
                    SkaldVaultV1ProviderSkeletonBlocker.KdfCalibrationAuthorizationBlocked,
                    SkaldVaultV1ProviderSkeletonBlocker.ProviderOperationAuthorizationBlocked,
                )
            SkaldVaultV1ProviderSkeletonOperationSurface.AeadEncrypt,
            SkaldVaultV1ProviderSkeletonOperationSurface.AeadDecrypt,
            SkaldVaultV1ProviderSkeletonOperationSurface.RecordEncrypt,
            SkaldVaultV1ProviderSkeletonOperationSurface.RecordDecrypt ->
                setOf(SkaldVaultV1ProviderSkeletonBlocker.ProviderOperationAuthorizationBlocked)
            SkaldVaultV1ProviderSkeletonOperationSurface.KeyWrap,
            SkaldVaultV1ProviderSkeletonOperationSurface.KeyUnwrap ->
                setOf(
                    SkaldVaultV1ProviderSkeletonBlocker.SecureStorageAuthorizationBlocked,
                    SkaldVaultV1ProviderSkeletonBlocker.ProviderOperationAuthorizationBlocked,
                )
            SkaldVaultV1ProviderSkeletonOperationSurface.ProviderClearDispose ->
                setOf(SkaldVaultV1ProviderSkeletonBlocker.OperationSurfaceDisabled)
        }

    private val baseStatuses: Set<SkaldVaultV1NonSelectableProviderSkeletonStatus> = setOf(
        SkaldVaultV1NonSelectableProviderSkeletonStatus.BoundaryModeled,
        SkaldVaultV1NonSelectableProviderSkeletonStatus.StillDisabled,
        SkaldVaultV1NonSelectableProviderSkeletonStatus.EvidenceOnly,
        SkaldVaultV1NonSelectableProviderSkeletonStatus.NonRegistered,
        SkaldVaultV1NonSelectableProviderSkeletonStatus.NonSelectable,
        SkaldVaultV1NonSelectableProviderSkeletonStatus.NonInstantiableByRegistry,
        SkaldVaultV1NonSelectableProviderSkeletonStatus.PromotionBlocked,
        SkaldVaultV1NonSelectableProviderSkeletonStatus.WarningOnlyCannotAuthorize,
        SkaldVaultV1NonSelectableProviderSkeletonStatus.UserConsentCannotOverride,
        SkaldVaultV1NonSelectableProviderSkeletonStatus.TestOnlyRejectedForProduction,
    )

    private val baseBlockers: Set<SkaldVaultV1ProviderSkeletonBlocker> = setOf(
        SkaldVaultV1ProviderSkeletonBlocker.SkeletonStillDisabled,
        SkaldVaultV1ProviderSkeletonBlocker.ModelEvidenceOnly,
        SkaldVaultV1ProviderSkeletonBlocker.RuntimeProviderImplementationForbidden,
        SkaldVaultV1ProviderSkeletonBlocker.ProviderImplementationMissing,
        SkaldVaultV1ProviderSkeletonBlocker.FactoryMissing,
        SkaldVaultV1ProviderSkeletonBlocker.RegistryDisabled,
        SkaldVaultV1ProviderSkeletonBlocker.ProviderSelectionLockedToDisabledProvider,
        SkaldVaultV1ProviderSkeletonBlocker.ProductionProviderSelectableFalse,
        SkaldVaultV1ProviderSkeletonBlocker.ProviderInterfaceAuditRequired,
        SkaldVaultV1ProviderSkeletonBlocker.ProviderSelectionPromotionBlocked,
        SkaldVaultV1ProviderSkeletonBlocker.ProviderCandidatePackagingStillDisabled,
        SkaldVaultV1ProviderSkeletonBlocker.ProviderDependencyBuildStillDisabled,
        SkaldVaultV1ProviderSkeletonBlocker.AuthorizationReadinessMatrixBlocked,
        SkaldVaultV1ProviderSkeletonBlocker.ProviderOperationAuthorizationBlocked,
        SkaldVaultV1ProviderSkeletonBlocker.RuntimeRandomnessAuthorizationBlocked,
        SkaldVaultV1ProviderSkeletonBlocker.KdfCalibrationAuthorizationBlocked,
        SkaldVaultV1ProviderSkeletonBlocker.SecureStorageAuthorizationBlocked,
        SkaldVaultV1ProviderSkeletonBlocker.CreationAuthorizationBlocked,
        SkaldVaultV1ProviderSkeletonBlocker.UnlockAuthorizationBlocked,
        SkaldVaultV1ProviderSkeletonBlocker.WarningOnlyEvidenceCannotAuthorize,
        SkaldVaultV1ProviderSkeletonBlocker.UserConsentCannotOverride,
        SkaldVaultV1ProviderSkeletonBlocker.TestOnlyEvidenceCannotAuthorizeProduction,
        SkaldVaultV1ProviderSkeletonBlocker.MainnetDisabled,
    )
}
