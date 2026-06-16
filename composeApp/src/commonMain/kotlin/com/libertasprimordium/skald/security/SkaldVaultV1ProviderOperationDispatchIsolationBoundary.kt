package com.libertasprimordium.skald.security

interface SkaldVaultV1ProviderOperationDispatchIsolationBoundary {
    fun evaluate(
        request: SkaldVaultV1ProviderOperationDispatchIsolationRequest,
    ): SkaldVaultV1ProviderOperationDispatchIsolationResult<
        SkaldVaultV1ProviderOperationDispatchIsolationEvidence,
    >
}

enum class SkaldVaultV1ProviderOperationDispatchIsolationSource(val label: String) {
    CurrentTypedEvidence("current provider operation dispatch isolation evidence"),
    DispatchTopicAudit("provider operation dispatch topic audit"),
    DispatchRiskAudit("provider operation dispatch risk audit"),
    RequiredPropertyAudit("provider operation dispatch required-property audit"),
    EvidenceInteractionAudit("provider operation dispatch evidence interaction audit"),
}

enum class SkaldVaultV1ProviderOperationDispatchIsolationStatus(val label: String) {
    BoundaryModeled("provider operation dispatch isolation boundary modeled"),
    StillDisabled("still disabled"),
    EvidenceOnly("evidence only"),
    NoDispatcherAvailable("no provider operation dispatcher available"),
    DispatchIsolated("provider operation dispatch isolated"),
    DispatchExcluded("provider operation dispatch excluded"),
    RuntimeDispatchBlocked("provider runtime dispatch blocked"),
    ProviderSelectionLockedToDisabledProvider("provider selection locked to disabled provider"),
    OperationExecutionBlocked("provider operation execution blocked"),
    HeaderCommitmentBlocked("header commitment execution blocked"),
    RecordCryptoBlocked("record crypto execution blocked"),
    KeyWrappingBlocked("key wrapping execution blocked"),
    WarningOnlyCannotAuthorize("warning-only evidence cannot authorize"),
    UserConsentCannotOverride("user consent cannot override"),
    TestOnlyRejectedForProduction("test-only evidence rejected for production"),
    MainnetBlocked("mainnet blocked"),
}

enum class SkaldVaultV1ProviderOperationDispatchIsolationTopic(val label: String) {
    ProviderOperationDispatchSurface("provider operation dispatch surface"),
    DisabledProviderDispatchBehavior("disabled provider dispatch behavior"),
    NonDisabledProviderDispatchExcluded("non-disabled provider dispatch excluded"),
    FactoryDispatchExcluded("factory dispatch excluded"),
    RegistryDispatchExcluded("registry dispatch excluded"),
    SkeletonDispatchExcluded("skeleton dispatch excluded"),
    CandidateProviderDispatchExcluded("candidate provider dispatch excluded"),
    DependencyBuildDispatchExcluded("dependency/build dispatch excluded"),
    CandidatePackagingDispatchExcluded("candidate-packaging dispatch excluded"),
    InterfaceAuditDispatchExcluded("interface-audit dispatch excluded"),
    PromotionBlockerDispatchExcluded("promotion-blocker dispatch excluded"),
    AuthorizationReadinessMatrixDispatchExcluded("authorization/readiness matrix dispatch excluded"),
    ProviderOperationAuthorizationDispatchExcluded("provider-operation authorization dispatch excluded"),
    RuntimeRandomnessDispatchExcluded("runtime-randomness dispatch excluded"),
    KdfCalibrationDispatchExcluded("KDF-calibration dispatch excluded"),
    SecureStorageDispatchExcluded("secure-storage dispatch excluded"),
    CreationAuthorizationDispatchExcluded("creation authorization dispatch excluded"),
    UnlockAuthorizationDispatchExcluded("unlock authorization dispatch excluded"),
    PersistenceReadinessDispatchExcluded("persistence readiness dispatch excluded"),
    TestOnlyDispatchExcludedFromProduction("test-only dispatch excluded from production"),
    WarningOnlyEvidenceExcludedFromDispatch("warning-only evidence excluded from dispatch"),
    UserConsentEvidenceExcludedFromDispatch("user-consent evidence excluded from dispatch"),
    ReleaseMainnetEvidenceFutureExplicitReviewOnly("release/mainnet evidence requires future explicit review"),
}

enum class SkaldVaultV1ProviderOperationDispatchIsolationRisk(val label: String) {
    DispatchInvokesNonDisabledProvider("dispatch invokes non-disabled provider"),
    DispatchInvokesSkeletonProvider("dispatch invokes skeleton provider"),
    DispatchInvokesCandidateProvider("dispatch invokes candidate provider"),
    DispatchInvokesProviderFactory("dispatch invokes provider factory"),
    DispatchInvokesProviderRegistry("dispatch invokes provider registry"),
    DispatchTreatsDependencyBuildEvidenceAsExecutableOperation(
        "dispatch treats dependency/build evidence as executable operation",
    ),
    DispatchTreatsPackagingEvidenceAsExecutableOperation("dispatch treats packaging evidence as executable operation"),
    DispatchTreatsInterfaceAuditEvidenceAsExecutableOperation(
        "dispatch treats interface audit evidence as executable operation",
    ),
    DispatchTreatsPromotionBlockerEvidenceAsExecutableOperation(
        "dispatch treats promotion blocker evidence as executable operation",
    ),
    DispatchTreatsMatrixEvidenceAsExecutableOperation("dispatch treats matrix evidence as executable operation"),
    DispatchAcceptsProviderHandles("dispatch accepts provider handles"),
    DispatchAcceptsCryptoObjects("dispatch accepts crypto objects"),
    DispatchAcceptsByteMaterial("dispatch accepts byte material"),
    DispatchAcceptsPlatformCryptoApis("dispatch accepts platform crypto APIs"),
    DispatchImportsTinkBouncyJavaxCrypto("dispatch imports Tink/Bouncy/Javax crypto"),
    DispatchCallsSecureRandom("dispatch calls SecureRandom"),
    DispatchRunsKats("dispatch runs KATs"),
    DispatchCallsProviderOperations("dispatch calls provider operations"),
    DispatchReachesRandomnessKdfAead("dispatch reaches randomness/KDF/AEAD"),
    DispatchComputesHeaderCommitment("dispatch computes header commitment"),
    DispatchEncryptsDecryptsRecords("dispatch encrypts/decrypts records"),
    DispatchWrapsUnwrapsKeys("dispatch wraps/unwraps keys"),
    DispatchEnablesVaultCreationUnlockPersistence("dispatch enables vault creation/unlock/persistence"),
    DispatchEnablesMainnet("dispatch enables mainnet"),
}

enum class SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty(val label: String) {
    NoProviderOperationDispatcherExistsForNonDisabledProviders(
        "no provider operation dispatcher exists for non-disabled providers",
    ),
    NoProviderOperationDispatcherReachableFromProviderSelection(
        "no provider operation dispatcher is reachable from provider selection",
    ),
    NoProviderOperationDispatcherReachableFromRegistryIsolation(
        "no provider operation dispatcher is reachable from registry isolation",
    ),
    NoProviderOperationDispatcherReachableFromFactoryIsolation(
        "no provider operation dispatcher is reachable from factory isolation",
    ),
    NoProviderOperationDispatcherReachableFromSkeletonEvidence(
        "no provider operation dispatcher is reachable from skeleton evidence",
    ),
    NoProviderOperationDispatcherReachableFromCandidatePackagingEvidence(
        "no provider operation dispatcher is reachable from candidate packaging evidence",
    ),
    NoProviderOperationDispatcherReachableFromDependencyBuildEvidence(
        "no provider operation dispatcher is reachable from dependency/build evidence",
    ),
    NoProviderOperationDispatcherReachableFromCreationUnlockStorageReadiness(
        "no provider operation dispatcher is reachable from creation/unlock/storage readiness",
    ),
    NoProviderOperationDispatcherAcceptsProviderHandles(
        "no provider operation dispatcher accepts provider handles",
    ),
    NoProviderOperationDispatcherExposesCryptoObjects(
        "no provider operation dispatcher exposes crypto objects",
    ),
    NoProviderOperationDispatcherAcceptsByteMaterial(
        "no provider operation dispatcher accepts byte material",
    ),
    NoProviderOperationDispatcherHasPlatformCryptoImports(
        "no provider operation dispatcher has platform crypto imports",
    ),
    NoProviderOperationDispatcherCanExecuteProviderOperations(
        "no provider operation dispatcher can execute provider operations",
    ),
    NoProviderOperationDispatcherCanRunKats("no provider operation dispatcher can run KATs"),
    NoProviderOperationDispatcherCanCallRandomness("no provider operation dispatcher can call randomness"),
    NoProviderOperationDispatcherCanRunKdfAeadHkdfHmac(
        "no provider operation dispatcher can run KDF/AEAD/HKDF/HMAC",
    ),
    NoProviderOperationDispatcherCanComputeHeaderCommitments(
        "no provider operation dispatcher can compute header commitments",
    ),
    NoProviderOperationDispatcherCanEncryptDecryptRecords(
        "no provider operation dispatcher can encrypt/decrypt records",
    ),
    NoProviderOperationDispatcherCanWrapUnwrapKeys(
        "no provider operation dispatcher can wrap/unwrap keys",
    ),
    NoProviderOperationDispatcherCanPromoteDependencyBuildEvidence(
        "no provider operation dispatcher can promote dependency/build evidence",
    ),
    NoProviderOperationDispatcherCanPromotePackagingSkeletonAuditMatrixEvidence(
        "no provider operation dispatcher can promote packaging/skeleton/audit/matrix evidence",
    ),
    WarningUserConsentTestOnlyCannotOverride("warning/user-consent/test-only evidence cannot override"),
    ProviderSelectionDisabledProviderOnly("provider selection remains disabled-provider-only"),
    ProductionProviderSelectableFalse("productionProviderSelectable remains false"),
    MainnetDisabled("mainnet remains disabled"),
}

enum class SkaldVaultV1ProviderOperationDispatchIsolationBlocker(val label: String) {
    DispatchIsolationBoundaryStillDisabled("provider operation dispatch isolation boundary still disabled"),
    ModelEvidenceOnly("model evidence only"),
    NoNonDisabledProviderDispatcher("no non-disabled provider operation dispatcher"),
    NoProviderOperationDispatcher("no provider operation dispatcher"),
    ProviderSelectionLockedToDisabledProvider("provider selection locked to disabled provider"),
    ProductionProviderSelectableFalse("productionProviderSelectable is false"),
    ProviderFactoryIsolationBlocked("provider factory isolation blocked"),
    ProviderRegistryIsolationBlocked("provider registry isolation blocked"),
    NonSelectableProviderSkeletonStillDisabled("non-selectable provider skeleton still disabled"),
    SkeletonDispatchExcluded("skeleton dispatch excluded"),
    CandidateDispatchExcluded("candidate dispatch excluded"),
    FactoryDispatchExcluded("factory dispatch excluded"),
    RegistryDispatchExcluded("registry dispatch excluded"),
    DependencyBuildDispatchExcluded("dependency/build dispatch excluded"),
    CandidatePackagingDispatchExcluded("candidate packaging dispatch excluded"),
    ProviderInterfaceAuditEvidenceOnly("provider interface audit evidence only"),
    ProviderSelectionPromotionBlocked("provider selection promotion blocked"),
    AuthorizationReadinessMatrixBlocked("authorization/readiness matrix blocked"),
    ProviderOperationAuthorizationBlocked("provider operation authorization blocked"),
    RuntimeRandomnessAuthorizationBlocked("runtime randomness authorization blocked"),
    KdfCalibrationAuthorizationBlocked("KDF calibration authorization blocked"),
    SecureStorageAuthorizationBlocked("secure-storage authorization blocked"),
    CreationAuthorizationBlocked("creation authorization blocked"),
    UnlockAuthorizationBlocked("unlock authorization blocked"),
    PersistenceReadinessBlocked("persistence readiness blocked"),
    ProviderAcceptanceMissing("provider acceptance missing"),
    DependencyProbeInsufficient("dependency probe insufficient"),
    DispatcherObjectsRejected("dispatcher objects rejected"),
    FactoryObjectsRejected("factory objects rejected"),
    RegistryObjectsRejected("registry objects rejected"),
    ProviderHandlesRejected("provider handles rejected"),
    CryptoObjectsRejected("crypto objects rejected"),
    ByteMaterialRejected("byte material rejected"),
    SecretMaterialRejected("secret material rejected"),
    PlatformCryptoImportsForbidden("platform crypto imports forbidden"),
    TinkBouncyJavaxImportsForbidden("Tink/Bouncy/Javax imports forbidden"),
    SecureRandomForbidden("SecureRandom forbidden"),
    ProviderOperationExecutionForbidden("provider operation execution forbidden"),
    KatExecutionUnavailable("KAT execution unavailable"),
    HeaderCommitmentUnavailable("header commitment unavailable"),
    RecordCryptoUnavailable("record crypto unavailable"),
    KeyWrappingUnavailable("key wrapping unavailable"),
    WarningOnlyEvidenceCannotAuthorize("warning-only evidence cannot authorize"),
    UserConsentCannotOverride("user consent cannot override"),
    TestOnlyEvidenceCannotAuthorizeProduction("test-only evidence cannot authorize production"),
    ReleaseReviewMissing("release review missing"),
    MainnetDisabled("mainnet disabled"),
}

enum class SkaldVaultV1ProviderOperationDispatchIsolationRedactionClass(val label: String) {
    PolicyIdsOnly("policy ids only"),
    DispatchTopicOnly("dispatch topic only"),
    RiskCategoryOnly("risk category only"),
    RequiredPropertyOnly("required property only"),
    BoundaryAndBlockerClassesOnly("boundary and blocker classes only"),
    NoDiagnosticPayload("no diagnostic payload"),
}

data class SkaldVaultV1ProviderOperationDispatchIsolationCapability(
    val providerOperationDispatchIsolationModeled: Boolean,
    val providerOperationDispatcherExistsForNonDisabledProvider: Boolean,
    val providerOperationDispatcherReachableFromSelection: Boolean,
    val providerOperationDispatcherReachableFromRegistry: Boolean,
    val providerOperationDispatcherReachableFromFactory: Boolean,
    val providerOperationDispatcherCanInvokeSkeleton: Boolean,
    val providerOperationDispatcherCanInvokeCandidate: Boolean,
    val providerOperationDispatcherCanInvokeProviderRuntime: Boolean,
    val providerOperationDispatcherCanExposeProviderHandle: Boolean,
    val providerOperationDispatcherCanExposeCryptoObject: Boolean,
    val providerOperationDispatcherAcceptsByteMaterial: Boolean,
    val providerOperationDispatcherCanExecuteProviderOperations: Boolean,
    val providerOperationDispatcherCanRunKat: Boolean,
    val providerOperationDispatcherCanUseRandomness: Boolean,
    val providerOperationDispatcherCanRunKdf: Boolean,
    val providerOperationDispatcherCanRunAead: Boolean,
    val providerOperationDispatcherCanComputeHeaderCommitment: Boolean,
    val providerOperationDispatcherCanEncryptRecords: Boolean,
    val providerOperationDispatcherCanDecryptRecords: Boolean,
    val providerOperationDispatcherCanWrapKeys: Boolean,
    val providerOperationDispatcherCanCreateVault: Boolean,
    val providerOperationDispatcherCanUnlockVault: Boolean,
    val providerOperationDispatcherCanPersistVault: Boolean,
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
        val StillDisabled = SkaldVaultV1ProviderOperationDispatchIsolationCapability(
            providerOperationDispatchIsolationModeled = true,
            providerOperationDispatcherExistsForNonDisabledProvider = false,
            providerOperationDispatcherReachableFromSelection = false,
            providerOperationDispatcherReachableFromRegistry = false,
            providerOperationDispatcherReachableFromFactory = false,
            providerOperationDispatcherCanInvokeSkeleton = false,
            providerOperationDispatcherCanInvokeCandidate = false,
            providerOperationDispatcherCanInvokeProviderRuntime = false,
            providerOperationDispatcherCanExposeProviderHandle = false,
            providerOperationDispatcherCanExposeCryptoObject = false,
            providerOperationDispatcherAcceptsByteMaterial = false,
            providerOperationDispatcherCanExecuteProviderOperations = false,
            providerOperationDispatcherCanRunKat = false,
            providerOperationDispatcherCanUseRandomness = false,
            providerOperationDispatcherCanRunKdf = false,
            providerOperationDispatcherCanRunAead = false,
            providerOperationDispatcherCanComputeHeaderCommitment = false,
            providerOperationDispatcherCanEncryptRecords = false,
            providerOperationDispatcherCanDecryptRecords = false,
            providerOperationDispatcherCanWrapKeys = false,
            providerOperationDispatcherCanCreateVault = false,
            providerOperationDispatcherCanUnlockVault = false,
            providerOperationDispatcherCanPersistVault = false,
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

class SkaldVaultV1ProviderOperationDispatchIsolationPolicyToken private constructor(
    val policyId: String,
    val topic: SkaldVaultV1ProviderOperationDispatchIsolationTopic?,
    val risk: SkaldVaultV1ProviderOperationDispatchIsolationRisk?,
    val requiredProperty: SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty?,
) {
    val containsProviderHandle: Boolean = false
    val containsDispatcherObject: Boolean = false
    val containsFactoryObject: Boolean = false
    val containsRegistryObject: Boolean = false
    val containsCryptoObject: Boolean = false
    val containsByteMaterial: Boolean = false
    val containsPathOrRootText: Boolean = false
    val containsStorageIdentifier: Boolean = false
    val containsSecretMaterial: Boolean = false

    override fun toString(): String =
        "SkaldVaultV1ProviderOperationDispatchIsolationPolicyToken(" +
            "policyId=$policyId, " +
            "topic=$topic, " +
            "risk=$risk, " +
            "requiredProperty=$requiredProperty, " +
            "providerHandle=<redacted>, " +
            "dispatcherObject=<redacted>, " +
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
            topic: SkaldVaultV1ProviderOperationDispatchIsolationTopic?,
            risk: SkaldVaultV1ProviderOperationDispatchIsolationRisk?,
            requiredProperty: SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty?,
        ): SkaldVaultV1ProviderOperationDispatchIsolationPolicyToken {
            return SkaldVaultV1ProviderOperationDispatchIsolationPolicyToken(
                policyId = policyId,
                topic = topic,
                risk = risk,
                requiredProperty = requiredProperty,
            )
        }
    }
}

class SkaldVaultV1ProviderOperationDispatchIsolationRequest private constructor(
    val source: SkaldVaultV1ProviderOperationDispatchIsolationSource,
    val topic: SkaldVaultV1ProviderOperationDispatchIsolationTopic?,
    val risk: SkaldVaultV1ProviderOperationDispatchIsolationRisk?,
    val requiredProperty: SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty?,
    private val providerSelectionResult: VaultCryptoProviderSelectionResult?,
    private val providerFactoryIsolationEvidence: SkaldVaultV1ProviderFactoryIsolationEvidence?,
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
    private val persistenceReadinessEvidence: SkaldVaultV1VaultPersistenceReadinessEvidence?,
) {
    val providerSelectionEvidenceSupplied: Boolean
        get() = providerSelectionResult != null

    val providerFactoryIsolationEvidenceSupplied: Boolean
        get() = providerFactoryIsolationEvidence != null

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

    val persistenceReadinessEvidenceSupplied: Boolean
        get() = persistenceReadinessEvidence != null

    override fun toString(): String =
        "SkaldVaultV1ProviderOperationDispatchIsolationRequest(" +
            "source=$source, " +
            "topic=$topic, " +
            "risk=$risk, " +
            "requiredProperty=$requiredProperty, " +
            "providerSelection=<redacted>, " +
            "providerFactoryIsolation=<redacted>, " +
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
            "unlockAuthorization=<redacted>, " +
            "persistenceReadiness=<redacted>" +
            ")"

    companion object {
        fun currentEvidence(
            providerSelectionResult: VaultCryptoProviderSelectionResult? = null,
            providerFactoryIsolationEvidence: SkaldVaultV1ProviderFactoryIsolationEvidence? = null,
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
            persistenceReadinessEvidence: SkaldVaultV1VaultPersistenceReadinessEvidence? = null,
        ): SkaldVaultV1ProviderOperationDispatchIsolationRequest =
            SkaldVaultV1ProviderOperationDispatchIsolationRequest(
                source = SkaldVaultV1ProviderOperationDispatchIsolationSource.CurrentTypedEvidence,
                topic = null,
                risk = null,
                requiredProperty = null,
                providerSelectionResult = providerSelectionResult,
                providerFactoryIsolationEvidence = providerFactoryIsolationEvidence,
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
                persistenceReadinessEvidence = persistenceReadinessEvidence,
            )

        fun forTopic(
            topic: SkaldVaultV1ProviderOperationDispatchIsolationTopic,
        ): SkaldVaultV1ProviderOperationDispatchIsolationRequest =
            SkaldVaultV1ProviderOperationDispatchIsolationRequest(
                source = SkaldVaultV1ProviderOperationDispatchIsolationSource.DispatchTopicAudit,
                topic = topic,
                risk = null,
                requiredProperty = null,
                providerSelectionResult = null,
                providerFactoryIsolationEvidence = null,
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
                persistenceReadinessEvidence = null,
            )

        fun forRisk(
            risk: SkaldVaultV1ProviderOperationDispatchIsolationRisk,
        ): SkaldVaultV1ProviderOperationDispatchIsolationRequest =
            SkaldVaultV1ProviderOperationDispatchIsolationRequest(
                source = SkaldVaultV1ProviderOperationDispatchIsolationSource.DispatchRiskAudit,
                topic = null,
                risk = risk,
                requiredProperty = null,
                providerSelectionResult = null,
                providerFactoryIsolationEvidence = null,
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
                persistenceReadinessEvidence = null,
            )

        fun forRequiredProperty(
            requiredProperty: SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty,
        ): SkaldVaultV1ProviderOperationDispatchIsolationRequest =
            SkaldVaultV1ProviderOperationDispatchIsolationRequest(
                source = SkaldVaultV1ProviderOperationDispatchIsolationSource.RequiredPropertyAudit,
                topic = null,
                risk = null,
                requiredProperty = requiredProperty,
                providerSelectionResult = null,
                providerFactoryIsolationEvidence = null,
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
                persistenceReadinessEvidence = null,
            )

        fun evidenceInteractionAudit(): SkaldVaultV1ProviderOperationDispatchIsolationRequest =
            SkaldVaultV1ProviderOperationDispatchIsolationRequest(
                source = SkaldVaultV1ProviderOperationDispatchIsolationSource.EvidenceInteractionAudit,
                topic = null,
                risk = null,
                requiredProperty = null,
                providerSelectionResult = null,
                providerFactoryIsolationEvidence = null,
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
                persistenceReadinessEvidence = null,
            )
    }
}

data class SkaldVaultV1ProviderOperationDispatchIsolationTopicRow(
    val topic: SkaldVaultV1ProviderOperationDispatchIsolationTopic,
    val statuses: Set<SkaldVaultV1ProviderOperationDispatchIsolationStatus>,
    val blockers: Set<SkaldVaultV1ProviderOperationDispatchIsolationBlocker>,
    val modeled: Boolean,
    val evidenceOnly: Boolean,
    val dispatchExcluded: Boolean,
    val dispatcherAvailable: Boolean,
    val runtimeDispatchAllowed: Boolean,
    val providerRuntimeInvoked: Boolean,
    val redactionClass: SkaldVaultV1ProviderOperationDispatchIsolationRedactionClass,
)

data class SkaldVaultV1ProviderOperationDispatchIsolationRiskRow(
    val risk: SkaldVaultV1ProviderOperationDispatchIsolationRisk,
    val statuses: Set<SkaldVaultV1ProviderOperationDispatchIsolationStatus>,
    val blockers: Set<SkaldVaultV1ProviderOperationDispatchIsolationBlocker>,
    val rejected: Boolean,
    val accepted: Boolean,
    val canDispatch: Boolean,
    val canInvokeRuntime: Boolean,
    val canExecute: Boolean,
    val canChangeSelectability: Boolean,
    val redactionClass: SkaldVaultV1ProviderOperationDispatchIsolationRedactionClass,
)

data class SkaldVaultV1ProviderOperationDispatchIsolationRequiredPropertyRow(
    val requiredProperty: SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty,
    val statuses: Set<SkaldVaultV1ProviderOperationDispatchIsolationStatus>,
    val blockers: Set<SkaldVaultV1ProviderOperationDispatchIsolationBlocker>,
    val satisfiedForCurrentState: Boolean,
    val futureExplicitReviewRequired: Boolean,
    val dispatcherRuntimeAvailable: Boolean,
    val providerOperationDispatchAllowed: Boolean,
    val redactionClass: SkaldVaultV1ProviderOperationDispatchIsolationRedactionClass,
)

data class SkaldVaultV1ProviderOperationDispatchIsolationSummary(
    val policyId: String,
    val policyVersion: Int,
    val statuses: Set<SkaldVaultV1ProviderOperationDispatchIsolationStatus>,
    val topics: Set<SkaldVaultV1ProviderOperationDispatchIsolationTopic>,
    val risks: Set<SkaldVaultV1ProviderOperationDispatchIsolationRisk>,
    val requiredProperties: Set<SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty>,
    val blockers: Set<SkaldVaultV1ProviderOperationDispatchIsolationBlocker>,
    val redactionClasses: Set<SkaldVaultV1ProviderOperationDispatchIsolationRedactionClass>,
    val capability: SkaldVaultV1ProviderOperationDispatchIsolationCapability,
    val stillDisabled: Boolean,
    val evidenceOnly: Boolean,
    val confirmsNoDispatcher: Boolean,
    val blocksDispatch: Boolean,
    val blocksProviderSelection: Boolean,
)

data class SkaldVaultV1ProviderOperationDispatchIsolationEvidence(
    val policyId: String,
    val policyVersion: Int,
    val source: SkaldVaultV1ProviderOperationDispatchIsolationSource,
    val status: SkaldVaultV1ProviderOperationDispatchIsolationStatus,
    val topicRows: List<SkaldVaultV1ProviderOperationDispatchIsolationTopicRow>,
    val riskRows: List<SkaldVaultV1ProviderOperationDispatchIsolationRiskRow>,
    val requiredPropertyRows: List<SkaldVaultV1ProviderOperationDispatchIsolationRequiredPropertyRow>,
    val blockers: Set<SkaldVaultV1ProviderOperationDispatchIsolationBlocker>,
    val capability: SkaldVaultV1ProviderOperationDispatchIsolationCapability,
    val policyTokenEvidence: SkaldVaultV1ProviderOperationDispatchIsolationPolicyToken,
    val providerSelectionEvidenceConsumed: Boolean,
    val providerFactoryIsolationEvidenceConsumed: Boolean,
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
    val persistenceReadinessEvidenceConsumed: Boolean,
    val providerOperationDispatchIsolationBoundaryModeled: Boolean = true,
    val providerOperationDispatchIsolationStillDisabled: Boolean = true,
    val providerOperationDispatchIsolationConfirmsNoDispatcher: Boolean = true,
    val providerOperationDispatchIsolationExcludesSkeletonDispatch: Boolean = true,
    val providerOperationDispatchIsolationExcludesCandidateDispatch: Boolean = true,
    val providerOperationDispatchIsolationExcludesRuntimeProviderDispatch: Boolean = true,
    val providerOperationDispatchIsolationDoesNotRegisterProvider: Boolean = true,
    val providerOperationDispatchIsolationDoesNotEnableProviderSelection: Boolean = true,
    val providerOperationDispatchIsolationDoesNotRunCrypto: Boolean = true,
    val providerOperationDispatchIsolationDoesNotEnableCreation: Boolean = true,
    val providerOperationDispatchIsolationDoesNotEnableUnlock: Boolean = true,
    val providerOperationDispatchIsolationDoesNotEnablePersistence: Boolean = true,
    val providerOperationDispatchIsolationModeled: Boolean = true,
    val providerOperationDispatcherAdded: Boolean = false,
    val providerOperationDispatcherExistsForNonDisabledProvider: Boolean = false,
    val providerOperationDispatcherReachableFromSelection: Boolean = false,
    val providerOperationDispatcherReachableFromRegistry: Boolean = false,
    val providerOperationDispatcherReachableFromFactory: Boolean = false,
    val providerOperationDispatcherCanInvokeSkeleton: Boolean = false,
    val providerOperationDispatcherCanInvokeCandidate: Boolean = false,
    val providerOperationDispatcherCanInvokeProviderRuntime: Boolean = false,
    val providerOperationDispatcherCanExposeProviderHandle: Boolean = false,
    val providerOperationDispatcherCanExposeCryptoObject: Boolean = false,
    val providerOperationDispatcherAcceptsByteMaterial: Boolean = false,
    val providerOperationDispatcherCanExecuteProviderOperations: Boolean = false,
    val providerOperationDispatcherCanRunKat: Boolean = false,
    val providerOperationDispatcherCanUseRandomness: Boolean = false,
    val providerOperationDispatcherCanRunKdf: Boolean = false,
    val providerOperationDispatcherCanRunAead: Boolean = false,
    val providerOperationDispatcherCanComputeHeaderCommitment: Boolean = false,
    val providerOperationDispatcherCanEncryptRecords: Boolean = false,
    val providerOperationDispatcherCanDecryptRecords: Boolean = false,
    val providerOperationDispatcherCanWrapKeys: Boolean = false,
    val providerOperationDispatcherCanCreateVault: Boolean = false,
    val providerOperationDispatcherCanUnlockVault: Boolean = false,
    val providerOperationDispatcherCanPersistVault: Boolean = false,
    val providerFactoryAdded: Boolean = false,
    val providerFactoryExistsForNonDisabledProvider: Boolean = false,
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
    val headerCommitmentExecutionAvailable: Boolean = false,
    val recordCryptoExecutionAvailable: Boolean = false,
    val keyWrappingAvailable: Boolean = false,
    val vaultCreationAvailable: Boolean = false,
    val vaultUnlockAvailable: Boolean = false,
    val vaultPersistenceAvailable: Boolean = false,
    val mainnetAvailable: Boolean = false,
) {
    override fun toString(): String =
        "SkaldVaultV1ProviderOperationDispatchIsolationEvidence(" +
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

sealed class SkaldVaultV1ProviderOperationDispatchIsolationResult<out T> {
    data class Blocked<out T>(
        val value: T,
    ) : SkaldVaultV1ProviderOperationDispatchIsolationResult<T>() {
        override fun toString(): String =
            "SkaldVaultV1ProviderOperationDispatchIsolationResult.Blocked(" +
                "value=<redacted-provider-operation-dispatch-isolation-evidence>)"
    }
}

object SkaldVaultV1ProviderOperationDispatchIsolationPolicy :
    SkaldVaultV1ProviderOperationDispatchIsolationBoundary {
    const val POLICY_ID = "skald-vault-v1-provider-operation-dispatch-isolation-boundary-v1"
    const val POLICY_VERSION = 1

    override fun evaluate(
        request: SkaldVaultV1ProviderOperationDispatchIsolationRequest,
    ): SkaldVaultV1ProviderOperationDispatchIsolationResult<
        SkaldVaultV1ProviderOperationDispatchIsolationEvidence,
    > {
        val topicRows = topicRowsFor(request.topic)
        val riskRows = riskRowsFor(request.risk)
        val requiredPropertyRows = requiredPropertyRowsFor(request.requiredProperty)
        return SkaldVaultV1ProviderOperationDispatchIsolationResult.Blocked(
            SkaldVaultV1ProviderOperationDispatchIsolationEvidence(
                policyId = POLICY_ID,
                policyVersion = POLICY_VERSION,
                source = request.source,
                status = SkaldVaultV1ProviderOperationDispatchIsolationStatus.StillDisabled,
                topicRows = topicRows,
                riskRows = riskRows,
                requiredPropertyRows = requiredPropertyRows,
                blockers = topicRows.flatMap { it.blockers }.toSet() +
                    riskRows.flatMap { it.blockers }.toSet() +
                    requiredPropertyRows.flatMap { it.blockers }.toSet() +
                    baseBlockers,
                capability = SkaldVaultV1ProviderOperationDispatchIsolationCapability.StillDisabled,
                policyTokenEvidence = SkaldVaultV1ProviderOperationDispatchIsolationPolicyToken.redacted(
                    policyId = POLICY_ID,
                    topic = request.topic,
                    risk = request.risk,
                    requiredProperty = request.requiredProperty,
                ),
                providerSelectionEvidenceConsumed = request.providerSelectionEvidenceSupplied,
                providerFactoryIsolationEvidenceConsumed = request.providerFactoryIsolationEvidenceSupplied,
                providerRegistryIsolationEvidenceConsumed = request.providerRegistryIsolationEvidenceSupplied,
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
                persistenceReadinessEvidenceConsumed = request.persistenceReadinessEvidenceSupplied,
            ),
        )
    }

    fun currentPolicySummary(): SkaldVaultV1ProviderOperationDispatchIsolationSummary =
        SkaldVaultV1ProviderOperationDispatchIsolationSummary(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            statuses = SkaldVaultV1ProviderOperationDispatchIsolationStatus.entries.toSet(),
            topics = SkaldVaultV1ProviderOperationDispatchIsolationTopic.entries.toSet(),
            risks = SkaldVaultV1ProviderOperationDispatchIsolationRisk.entries.toSet(),
            requiredProperties = SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty.entries.toSet(),
            blockers = SkaldVaultV1ProviderOperationDispatchIsolationBlocker.entries.toSet(),
            redactionClasses = SkaldVaultV1ProviderOperationDispatchIsolationRedactionClass.entries.toSet(),
            capability = SkaldVaultV1ProviderOperationDispatchIsolationCapability.StillDisabled,
            stillDisabled = true,
            evidenceOnly = true,
            confirmsNoDispatcher = true,
            blocksDispatch = true,
            blocksProviderSelection = true,
        )

    private fun topicRowsFor(
        topic: SkaldVaultV1ProviderOperationDispatchIsolationTopic?,
    ): List<SkaldVaultV1ProviderOperationDispatchIsolationTopicRow> {
        val rows = SkaldVaultV1ProviderOperationDispatchIsolationTopic.entries.map { currentTopic ->
            topicRow(currentTopic)
        }
        return topic?.let { requested -> rows.filter { it.topic == requested } } ?: rows
    }

    private fun riskRowsFor(
        risk: SkaldVaultV1ProviderOperationDispatchIsolationRisk?,
    ): List<SkaldVaultV1ProviderOperationDispatchIsolationRiskRow> {
        val rows = SkaldVaultV1ProviderOperationDispatchIsolationRisk.entries.map { currentRisk ->
            riskRow(currentRisk)
        }
        return risk?.let { requested -> rows.filter { it.risk == requested } } ?: rows
    }

    private fun requiredPropertyRowsFor(
        requiredProperty: SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty?,
    ): List<SkaldVaultV1ProviderOperationDispatchIsolationRequiredPropertyRow> {
        val rows = SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty.entries.map { currentProperty ->
            requiredPropertyRow(currentProperty)
        }
        return requiredProperty?.let { requested ->
            rows.filter { it.requiredProperty == requested }
        } ?: rows
    }

    private fun topicRow(
        topic: SkaldVaultV1ProviderOperationDispatchIsolationTopic,
    ): SkaldVaultV1ProviderOperationDispatchIsolationTopicRow =
        SkaldVaultV1ProviderOperationDispatchIsolationTopicRow(
            topic = topic,
            statuses = baseStatuses + topicStatus(topic),
            blockers = topicBlockers(topic) + baseBlockers,
            modeled = true,
            evidenceOnly = true,
            dispatchExcluded =
                topic != SkaldVaultV1ProviderOperationDispatchIsolationTopic.ProviderOperationDispatchSurface &&
                    topic != SkaldVaultV1ProviderOperationDispatchIsolationTopic.DisabledProviderDispatchBehavior,
            dispatcherAvailable = false,
            runtimeDispatchAllowed = false,
            providerRuntimeInvoked = false,
            redactionClass = SkaldVaultV1ProviderOperationDispatchIsolationRedactionClass.DispatchTopicOnly,
        )

    private fun riskRow(
        risk: SkaldVaultV1ProviderOperationDispatchIsolationRisk,
    ): SkaldVaultV1ProviderOperationDispatchIsolationRiskRow =
        SkaldVaultV1ProviderOperationDispatchIsolationRiskRow(
            risk = risk,
            statuses = baseStatuses + riskStatus(risk),
            blockers = riskBlockers(risk) + baseBlockers,
            rejected = true,
            accepted = false,
            canDispatch = false,
            canInvokeRuntime = false,
            canExecute = false,
            canChangeSelectability = false,
            redactionClass = SkaldVaultV1ProviderOperationDispatchIsolationRedactionClass.RiskCategoryOnly,
        )

    private fun requiredPropertyRow(
        requiredProperty: SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty,
    ): SkaldVaultV1ProviderOperationDispatchIsolationRequiredPropertyRow =
        SkaldVaultV1ProviderOperationDispatchIsolationRequiredPropertyRow(
            requiredProperty = requiredProperty,
            statuses = baseStatuses + SkaldVaultV1ProviderOperationDispatchIsolationStatus.DispatchIsolated,
            blockers = requiredPropertyBlockers(requiredProperty) + baseBlockers,
            satisfiedForCurrentState = true,
            futureExplicitReviewRequired = true,
            dispatcherRuntimeAvailable = false,
            providerOperationDispatchAllowed = false,
            redactionClass = SkaldVaultV1ProviderOperationDispatchIsolationRedactionClass.RequiredPropertyOnly,
        )

    private fun topicStatus(
        topic: SkaldVaultV1ProviderOperationDispatchIsolationTopic,
    ): SkaldVaultV1ProviderOperationDispatchIsolationStatus =
        when (topic) {
            SkaldVaultV1ProviderOperationDispatchIsolationTopic.ProviderOperationDispatchSurface,
            SkaldVaultV1ProviderOperationDispatchIsolationTopic.DisabledProviderDispatchBehavior ->
                SkaldVaultV1ProviderOperationDispatchIsolationStatus.NoDispatcherAvailable
            SkaldVaultV1ProviderOperationDispatchIsolationTopic.NonDisabledProviderDispatchExcluded,
            SkaldVaultV1ProviderOperationDispatchIsolationTopic.FactoryDispatchExcluded,
            SkaldVaultV1ProviderOperationDispatchIsolationTopic.RegistryDispatchExcluded,
            SkaldVaultV1ProviderOperationDispatchIsolationTopic.SkeletonDispatchExcluded,
            SkaldVaultV1ProviderOperationDispatchIsolationTopic.CandidateProviderDispatchExcluded ->
                SkaldVaultV1ProviderOperationDispatchIsolationStatus.DispatchExcluded
            SkaldVaultV1ProviderOperationDispatchIsolationTopic.ReleaseMainnetEvidenceFutureExplicitReviewOnly ->
                SkaldVaultV1ProviderOperationDispatchIsolationStatus.MainnetBlocked
            else -> SkaldVaultV1ProviderOperationDispatchIsolationStatus.RuntimeDispatchBlocked
        }

    private fun riskStatus(
        risk: SkaldVaultV1ProviderOperationDispatchIsolationRisk,
    ): SkaldVaultV1ProviderOperationDispatchIsolationStatus =
        when (risk) {
            SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchInvokesNonDisabledProvider,
            SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchInvokesSkeletonProvider,
            SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchInvokesCandidateProvider,
            SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchInvokesProviderFactory,
            SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchInvokesProviderRegistry ->
                SkaldVaultV1ProviderOperationDispatchIsolationStatus.DispatchExcluded
            SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchCallsProviderOperations,
            SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchRunsKats,
            SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchReachesRandomnessKdfAead ->
                SkaldVaultV1ProviderOperationDispatchIsolationStatus.OperationExecutionBlocked
            SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchComputesHeaderCommitment ->
                SkaldVaultV1ProviderOperationDispatchIsolationStatus.HeaderCommitmentBlocked
            SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchEncryptsDecryptsRecords ->
                SkaldVaultV1ProviderOperationDispatchIsolationStatus.RecordCryptoBlocked
            SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchWrapsUnwrapsKeys ->
                SkaldVaultV1ProviderOperationDispatchIsolationStatus.KeyWrappingBlocked
            SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchEnablesMainnet ->
                SkaldVaultV1ProviderOperationDispatchIsolationStatus.MainnetBlocked
            else -> SkaldVaultV1ProviderOperationDispatchIsolationStatus.RuntimeDispatchBlocked
        }

    private fun topicBlockers(
        topic: SkaldVaultV1ProviderOperationDispatchIsolationTopic,
    ): Set<SkaldVaultV1ProviderOperationDispatchIsolationBlocker> =
        when (topic) {
            SkaldVaultV1ProviderOperationDispatchIsolationTopic.ProviderOperationDispatchSurface,
            SkaldVaultV1ProviderOperationDispatchIsolationTopic.DisabledProviderDispatchBehavior ->
                setOf(
                    SkaldVaultV1ProviderOperationDispatchIsolationBlocker.NoProviderOperationDispatcher,
                    SkaldVaultV1ProviderOperationDispatchIsolationBlocker.NoNonDisabledProviderDispatcher,
                )
            SkaldVaultV1ProviderOperationDispatchIsolationTopic.NonDisabledProviderDispatchExcluded ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.NoNonDisabledProviderDispatcher)
            SkaldVaultV1ProviderOperationDispatchIsolationTopic.FactoryDispatchExcluded ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.FactoryDispatchExcluded)
            SkaldVaultV1ProviderOperationDispatchIsolationTopic.RegistryDispatchExcluded ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.RegistryDispatchExcluded)
            SkaldVaultV1ProviderOperationDispatchIsolationTopic.SkeletonDispatchExcluded ->
                setOf(
                    SkaldVaultV1ProviderOperationDispatchIsolationBlocker.SkeletonDispatchExcluded,
                    SkaldVaultV1ProviderOperationDispatchIsolationBlocker.NonSelectableProviderSkeletonStillDisabled,
                )
            SkaldVaultV1ProviderOperationDispatchIsolationTopic.CandidateProviderDispatchExcluded ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.CandidateDispatchExcluded)
            SkaldVaultV1ProviderOperationDispatchIsolationTopic.DependencyBuildDispatchExcluded ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.DependencyBuildDispatchExcluded)
            SkaldVaultV1ProviderOperationDispatchIsolationTopic.CandidatePackagingDispatchExcluded ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.CandidatePackagingDispatchExcluded)
            SkaldVaultV1ProviderOperationDispatchIsolationTopic.InterfaceAuditDispatchExcluded ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ProviderInterfaceAuditEvidenceOnly)
            SkaldVaultV1ProviderOperationDispatchIsolationTopic.PromotionBlockerDispatchExcluded ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ProviderSelectionPromotionBlocked)
            SkaldVaultV1ProviderOperationDispatchIsolationTopic.AuthorizationReadinessMatrixDispatchExcluded ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.AuthorizationReadinessMatrixBlocked)
            SkaldVaultV1ProviderOperationDispatchIsolationTopic.ProviderOperationAuthorizationDispatchExcluded ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ProviderOperationAuthorizationBlocked)
            SkaldVaultV1ProviderOperationDispatchIsolationTopic.RuntimeRandomnessDispatchExcluded ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.RuntimeRandomnessAuthorizationBlocked)
            SkaldVaultV1ProviderOperationDispatchIsolationTopic.KdfCalibrationDispatchExcluded ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.KdfCalibrationAuthorizationBlocked)
            SkaldVaultV1ProviderOperationDispatchIsolationTopic.SecureStorageDispatchExcluded ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.SecureStorageAuthorizationBlocked)
            SkaldVaultV1ProviderOperationDispatchIsolationTopic.CreationAuthorizationDispatchExcluded ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.CreationAuthorizationBlocked)
            SkaldVaultV1ProviderOperationDispatchIsolationTopic.UnlockAuthorizationDispatchExcluded ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.UnlockAuthorizationBlocked)
            SkaldVaultV1ProviderOperationDispatchIsolationTopic.PersistenceReadinessDispatchExcluded ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.PersistenceReadinessBlocked)
            SkaldVaultV1ProviderOperationDispatchIsolationTopic.TestOnlyDispatchExcludedFromProduction ->
                setOf(
                    SkaldVaultV1ProviderOperationDispatchIsolationBlocker.TestOnlyEvidenceCannotAuthorizeProduction,
                )
            SkaldVaultV1ProviderOperationDispatchIsolationTopic.WarningOnlyEvidenceExcludedFromDispatch ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.WarningOnlyEvidenceCannotAuthorize)
            SkaldVaultV1ProviderOperationDispatchIsolationTopic.UserConsentEvidenceExcludedFromDispatch ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.UserConsentCannotOverride)
            SkaldVaultV1ProviderOperationDispatchIsolationTopic.ReleaseMainnetEvidenceFutureExplicitReviewOnly ->
                setOf(
                    SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ReleaseReviewMissing,
                    SkaldVaultV1ProviderOperationDispatchIsolationBlocker.MainnetDisabled,
                )
        }

    private fun riskBlockers(
        risk: SkaldVaultV1ProviderOperationDispatchIsolationRisk,
    ): Set<SkaldVaultV1ProviderOperationDispatchIsolationBlocker> =
        when (risk) {
            SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchInvokesNonDisabledProvider ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.NoNonDisabledProviderDispatcher)
            SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchInvokesSkeletonProvider ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.SkeletonDispatchExcluded)
            SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchInvokesCandidateProvider ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.CandidateDispatchExcluded)
            SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchInvokesProviderFactory ->
                setOf(
                    SkaldVaultV1ProviderOperationDispatchIsolationBlocker.FactoryDispatchExcluded,
                    SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ProviderFactoryIsolationBlocked,
                )
            SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchInvokesProviderRegistry ->
                setOf(
                    SkaldVaultV1ProviderOperationDispatchIsolationBlocker.RegistryDispatchExcluded,
                    SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ProviderRegistryIsolationBlocked,
                )
            SkaldVaultV1ProviderOperationDispatchIsolationRisk
                .DispatchTreatsDependencyBuildEvidenceAsExecutableOperation ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.DependencyBuildDispatchExcluded)
            SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchTreatsPackagingEvidenceAsExecutableOperation ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.CandidatePackagingDispatchExcluded)
            SkaldVaultV1ProviderOperationDispatchIsolationRisk
                .DispatchTreatsInterfaceAuditEvidenceAsExecutableOperation ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ProviderInterfaceAuditEvidenceOnly)
            SkaldVaultV1ProviderOperationDispatchIsolationRisk
                .DispatchTreatsPromotionBlockerEvidenceAsExecutableOperation ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ProviderSelectionPromotionBlocked)
            SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchTreatsMatrixEvidenceAsExecutableOperation ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.AuthorizationReadinessMatrixBlocked)
            SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchAcceptsProviderHandles ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ProviderHandlesRejected)
            SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchAcceptsCryptoObjects ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.CryptoObjectsRejected)
            SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchAcceptsByteMaterial ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ByteMaterialRejected)
            SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchAcceptsPlatformCryptoApis ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.PlatformCryptoImportsForbidden)
            SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchImportsTinkBouncyJavaxCrypto ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.TinkBouncyJavaxImportsForbidden)
            SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchCallsSecureRandom ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.SecureRandomForbidden)
            SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchRunsKats ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.KatExecutionUnavailable)
            SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchCallsProviderOperations ->
                setOf(
                    SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ProviderOperationAuthorizationBlocked,
                    SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ProviderOperationExecutionForbidden,
                )
            SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchReachesRandomnessKdfAead ->
                setOf(
                    SkaldVaultV1ProviderOperationDispatchIsolationBlocker.RuntimeRandomnessAuthorizationBlocked,
                    SkaldVaultV1ProviderOperationDispatchIsolationBlocker.KdfCalibrationAuthorizationBlocked,
                    SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ProviderOperationAuthorizationBlocked,
                )
            SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchComputesHeaderCommitment ->
                setOf(
                    SkaldVaultV1ProviderOperationDispatchIsolationBlocker.HeaderCommitmentUnavailable,
                    SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ProviderOperationAuthorizationBlocked,
                )
            SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchEncryptsDecryptsRecords ->
                setOf(
                    SkaldVaultV1ProviderOperationDispatchIsolationBlocker.RecordCryptoUnavailable,
                    SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ProviderOperationAuthorizationBlocked,
                )
            SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchWrapsUnwrapsKeys ->
                setOf(
                    SkaldVaultV1ProviderOperationDispatchIsolationBlocker.KeyWrappingUnavailable,
                    SkaldVaultV1ProviderOperationDispatchIsolationBlocker.SecureStorageAuthorizationBlocked,
                )
            SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchEnablesVaultCreationUnlockPersistence ->
                setOf(
                    SkaldVaultV1ProviderOperationDispatchIsolationBlocker.CreationAuthorizationBlocked,
                    SkaldVaultV1ProviderOperationDispatchIsolationBlocker.UnlockAuthorizationBlocked,
                    SkaldVaultV1ProviderOperationDispatchIsolationBlocker.PersistenceReadinessBlocked,
                )
            SkaldVaultV1ProviderOperationDispatchIsolationRisk.DispatchEnablesMainnet ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.MainnetDisabled)
        }

    private fun requiredPropertyBlockers(
        requiredProperty: SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty,
    ): Set<SkaldVaultV1ProviderOperationDispatchIsolationBlocker> =
        when (requiredProperty) {
            SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty
                .NoProviderOperationDispatcherExistsForNonDisabledProviders ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.NoNonDisabledProviderDispatcher)
            SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty
                .NoProviderOperationDispatcherReachableFromProviderSelection,
            SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty.ProviderSelectionDisabledProviderOnly ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ProviderSelectionLockedToDisabledProvider)
            SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty
                .NoProviderOperationDispatcherReachableFromRegistryIsolation ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ProviderRegistryIsolationBlocked)
            SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty
                .NoProviderOperationDispatcherReachableFromFactoryIsolation ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ProviderFactoryIsolationBlocked)
            SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty
                .NoProviderOperationDispatcherReachableFromSkeletonEvidence ->
                setOf(
                    SkaldVaultV1ProviderOperationDispatchIsolationBlocker.SkeletonDispatchExcluded,
                    SkaldVaultV1ProviderOperationDispatchIsolationBlocker.NonSelectableProviderSkeletonStillDisabled,
                )
            SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty
                .NoProviderOperationDispatcherReachableFromCandidatePackagingEvidence ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.CandidatePackagingDispatchExcluded)
            SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty
                .NoProviderOperationDispatcherReachableFromDependencyBuildEvidence ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.DependencyBuildDispatchExcluded)
            SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty
                .NoProviderOperationDispatcherReachableFromCreationUnlockStorageReadiness ->
                setOf(
                    SkaldVaultV1ProviderOperationDispatchIsolationBlocker.CreationAuthorizationBlocked,
                    SkaldVaultV1ProviderOperationDispatchIsolationBlocker.UnlockAuthorizationBlocked,
                    SkaldVaultV1ProviderOperationDispatchIsolationBlocker.SecureStorageAuthorizationBlocked,
                    SkaldVaultV1ProviderOperationDispatchIsolationBlocker.PersistenceReadinessBlocked,
                )
            SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty
                .NoProviderOperationDispatcherAcceptsProviderHandles ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ProviderHandlesRejected)
            SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty
                .NoProviderOperationDispatcherExposesCryptoObjects ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.CryptoObjectsRejected)
            SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty
                .NoProviderOperationDispatcherAcceptsByteMaterial ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ByteMaterialRejected)
            SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty
                .NoProviderOperationDispatcherHasPlatformCryptoImports ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.PlatformCryptoImportsForbidden)
            SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty
                .NoProviderOperationDispatcherCanExecuteProviderOperations ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ProviderOperationExecutionForbidden)
            SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty.NoProviderOperationDispatcherCanRunKats ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.KatExecutionUnavailable)
            SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty
                .NoProviderOperationDispatcherCanCallRandomness ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.RuntimeRandomnessAuthorizationBlocked)
            SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty
                .NoProviderOperationDispatcherCanRunKdfAeadHkdfHmac ->
                setOf(
                    SkaldVaultV1ProviderOperationDispatchIsolationBlocker.KdfCalibrationAuthorizationBlocked,
                    SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ProviderOperationAuthorizationBlocked,
                )
            SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty
                .NoProviderOperationDispatcherCanComputeHeaderCommitments ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.HeaderCommitmentUnavailable)
            SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty
                .NoProviderOperationDispatcherCanEncryptDecryptRecords ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.RecordCryptoUnavailable)
            SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty
                .NoProviderOperationDispatcherCanWrapUnwrapKeys ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.KeyWrappingUnavailable)
            SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty
                .NoProviderOperationDispatcherCanPromoteDependencyBuildEvidence ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.DependencyBuildDispatchExcluded)
            SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty
                .NoProviderOperationDispatcherCanPromotePackagingSkeletonAuditMatrixEvidence ->
                setOf(
                    SkaldVaultV1ProviderOperationDispatchIsolationBlocker.CandidatePackagingDispatchExcluded,
                    SkaldVaultV1ProviderOperationDispatchIsolationBlocker.NonSelectableProviderSkeletonStillDisabled,
                    SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ProviderInterfaceAuditEvidenceOnly,
                    SkaldVaultV1ProviderOperationDispatchIsolationBlocker.AuthorizationReadinessMatrixBlocked,
                )
            SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty.WarningUserConsentTestOnlyCannotOverride ->
                setOf(
                    SkaldVaultV1ProviderOperationDispatchIsolationBlocker.WarningOnlyEvidenceCannotAuthorize,
                    SkaldVaultV1ProviderOperationDispatchIsolationBlocker.UserConsentCannotOverride,
                    SkaldVaultV1ProviderOperationDispatchIsolationBlocker.TestOnlyEvidenceCannotAuthorizeProduction,
                )
            SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty.ProductionProviderSelectableFalse ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ProductionProviderSelectableFalse)
            SkaldVaultV1ProviderOperationDispatchIsolationRequiredProperty.MainnetDisabled ->
                setOf(SkaldVaultV1ProviderOperationDispatchIsolationBlocker.MainnetDisabled)
        }

    private val baseStatuses: Set<SkaldVaultV1ProviderOperationDispatchIsolationStatus> =
        setOf(
            SkaldVaultV1ProviderOperationDispatchIsolationStatus.BoundaryModeled,
            SkaldVaultV1ProviderOperationDispatchIsolationStatus.StillDisabled,
            SkaldVaultV1ProviderOperationDispatchIsolationStatus.EvidenceOnly,
            SkaldVaultV1ProviderOperationDispatchIsolationStatus.WarningOnlyCannotAuthorize,
            SkaldVaultV1ProviderOperationDispatchIsolationStatus.UserConsentCannotOverride,
            SkaldVaultV1ProviderOperationDispatchIsolationStatus.TestOnlyRejectedForProduction,
        )

    private val baseBlockers: Set<SkaldVaultV1ProviderOperationDispatchIsolationBlocker> =
        setOf(
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.DispatchIsolationBoundaryStillDisabled,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ModelEvidenceOnly,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.NoNonDisabledProviderDispatcher,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ProviderSelectionLockedToDisabledProvider,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ProductionProviderSelectableFalse,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.ProviderOperationAuthorizationBlocked,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.WarningOnlyEvidenceCannotAuthorize,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.UserConsentCannotOverride,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.TestOnlyEvidenceCannotAuthorizeProduction,
            SkaldVaultV1ProviderOperationDispatchIsolationBlocker.MainnetDisabled,
        )
}
