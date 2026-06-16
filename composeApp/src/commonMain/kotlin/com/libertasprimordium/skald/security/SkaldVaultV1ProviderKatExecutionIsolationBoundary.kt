package com.libertasprimordium.skald.security

interface SkaldVaultV1ProviderKatExecutionIsolationBoundary {
    fun evaluate(
        request: SkaldVaultV1ProviderKatExecutionIsolationRequest,
    ): SkaldVaultV1ProviderKatExecutionIsolationResult<SkaldVaultV1ProviderKatExecutionIsolationEvidence>
}

enum class SkaldVaultV1ProviderKatExecutionIsolationSource(val label: String) {
    CurrentTypedEvidence("current provider KAT execution isolation evidence"),
    KatTopicAudit("provider KAT execution isolation topic audit"),
    KatRiskAudit("provider KAT execution isolation risk audit"),
    RequiredPropertyAudit("provider KAT execution isolation required-property audit"),
    EvidenceInteractionAudit("provider KAT execution isolation evidence interaction audit"),
}

enum class SkaldVaultV1ProviderKatExecutionIsolationStatus(val label: String) {
    BoundaryModeled("provider KAT execution isolation boundary modeled"),
    StillDisabled("still disabled"),
    EvidenceOnly("evidence only"),
    NoKatExecutorAvailable("no provider KAT executor available"),
    KatExecutionIsolated("provider KAT execution isolated"),
    KatExecutionExcluded("provider KAT execution excluded"),
    RuntimeKatBlocked("provider runtime KAT blocked"),
    ProviderSelectionLockedToDisabledProvider("provider selection locked to disabled provider"),
    ProviderKatExecutionBlocked("provider KAT execution blocked"),
    PublicVectorDocumentationNonAuthorizing("public vector documentation is non-authorizing"),
    HeaderCommitmentBlocked("header commitment execution blocked"),
    RecordCryptoBlocked("record crypto execution blocked"),
    KeyWrappingBlocked("key wrapping execution blocked"),
    WarningOnlyCannotAuthorize("warning-only evidence cannot authorize"),
    UserConsentCannotOverride("user consent cannot override"),
    TestOnlyRejectedForProduction("test-only evidence rejected for production"),
    MainnetBlocked("mainnet blocked"),
}

enum class SkaldVaultV1ProviderKatExecutionIsolationTopic(val label: String) {
    ProviderKatExecutionSurface("provider KAT execution surface"),
    ProviderKatContractEvidence("provider KAT contract evidence"),
    TestProviderKatHarnessEvidence("test-provider KAT harness evidence"),
    PublicNonWalletVectorDocumentationEvidence("public non-wallet vector documentation evidence"),
    CanonicalHeaderHkdfHmacVectorDocumentationEvidence(
        "canonical header/HKDF/HMAC vector documentation evidence",
    ),
    ProviderSelfTestSurface("provider self-test surface"),
    ProviderRuntimeCheckSurface("provider runtime check surface"),
    DisabledProviderKatBehavior("disabled provider KAT behavior"),
    NonDisabledProviderKatExcluded("non-disabled provider KAT excluded"),
    FactoryKatExcluded("factory KAT excluded"),
    RegistryKatExcluded("registry KAT excluded"),
    DispatchKatExcluded("dispatch KAT excluded"),
    SkeletonKatExcluded("skeleton KAT excluded"),
    CandidateProviderKatExcluded("candidate provider KAT excluded"),
    DependencyBuildKatExcluded("dependency/build KAT excluded"),
    CandidatePackagingKatExcluded("candidate-packaging KAT excluded"),
    InterfaceAuditKatExcluded("interface-audit KAT excluded"),
    PromotionBlockerKatExcluded("promotion-blocker KAT excluded"),
    AuthorizationReadinessMatrixKatExcluded("authorization/readiness matrix KAT excluded"),
    ProviderOperationAuthorizationKatExcluded("provider-operation authorization KAT excluded"),
    RuntimeRandomnessKatExcluded("runtime-randomness KAT excluded"),
    KdfCalibrationKatExcluded("KDF-calibration KAT excluded"),
    SecureStorageKatExcluded("secure-storage KAT excluded"),
    CreationUnlockKatExcluded("creation/unlock KAT excluded"),
    PersistenceReadinessKatExcluded("persistence readiness KAT excluded"),
    TestOnlyKatEvidenceExcludedFromProduction("test-only KAT evidence excluded from production"),
    WarningOnlyEvidenceExcludedFromKatPromotion("warning-only evidence excluded from KAT promotion"),
    UserConsentEvidenceExcludedFromKatPromotion("user-consent evidence excluded from KAT promotion"),
    ReleaseMainnetEvidenceFutureExplicitReviewOnly("release/mainnet evidence requires future explicit review"),
}

enum class SkaldVaultV1ProviderKatExecutionIsolationRisk(val label: String) {
    KatInvokesNonDisabledProvider("KAT invokes non-disabled provider"),
    KatInvokesSkeletonProvider("KAT invokes skeleton provider"),
    KatInvokesCandidateProvider("KAT invokes candidate provider"),
    KatInvokesProviderFactory("KAT invokes provider factory"),
    KatInvokesProviderRegistry("KAT invokes provider registry"),
    KatInvokesProviderDispatcher("KAT invokes provider dispatcher"),
    KatTreatsDependencyBuildEvidenceAsExecutableProvider(
        "KAT treats dependency/build evidence as executable provider",
    ),
    KatTreatsPackagingEvidenceAsExecutableProvider("KAT treats packaging evidence as executable provider"),
    KatTreatsInterfaceAuditEvidenceAsExecutableProvider(
        "KAT treats interface audit evidence as executable provider",
    ),
    KatTreatsPromotionBlockerEvidenceAsExecutableProvider(
        "KAT treats promotion blocker evidence as executable provider",
    ),
    KatTreatsMatrixEvidenceAsExecutableProvider("KAT treats matrix evidence as executable provider"),
    KatTreatsPublicVectorDocsAsProductionApproval("KAT treats public vector docs as production approval"),
    KatTreatsCanonicalDocsAsRuntimeKdfHkdfHmacExecution(
        "KAT treats canonical docs as runtime KDF/HKDF/HMAC execution",
    ),
    KatAcceptsProviderHandles("KAT accepts provider handles"),
    KatAcceptsCryptoObjects("KAT accepts crypto objects"),
    KatAcceptsByteMaterial("KAT accepts byte material"),
    KatAcceptsPlatformCryptoApis("KAT accepts platform crypto APIs"),
    KatImportsTinkBouncyJavaxCrypto("KAT imports Tink/Bouncy/Javax crypto"),
    KatCallsSecureRandom("KAT calls SecureRandom"),
    KatRunsProviderOperations("KAT runs provider operations"),
    KatReachesRandomnessKdfAead("KAT reaches randomness/KDF/AEAD"),
    KatComputesHeaderCommitment("KAT computes header commitment"),
    KatEncryptsDecryptsRecords("KAT encrypts/decrypts records"),
    KatWrapsUnwrapsKeys("KAT wraps/unwraps keys"),
    KatAuthorizesProviderSelection("KAT authorizes provider selection"),
    KatAuthorizesProductionProviderSelectableTrue("KAT authorizes productionProviderSelectable true"),
    KatEnablesVaultCreationUnlockPersistence("KAT enables vault creation/unlock/persistence"),
    KatEnablesMainnet("KAT enables mainnet"),
}

enum class SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty(val label: String) {
    NoProviderKatExecutorExistsForNonDisabledProviders(
        "no provider KAT executor exists for non-disabled providers",
    ),
    NoProviderKatExecutorReachableFromProviderSelection(
        "no provider KAT executor is reachable from provider selection",
    ),
    NoProviderKatExecutorReachableFromRegistryIsolation(
        "no provider KAT executor is reachable from registry isolation",
    ),
    NoProviderKatExecutorReachableFromFactoryIsolation(
        "no provider KAT executor is reachable from factory isolation",
    ),
    NoProviderKatExecutorReachableFromDispatchIsolation(
        "no provider KAT executor is reachable from dispatch isolation",
    ),
    NoProviderKatExecutorReachableFromSkeletonEvidence(
        "no provider KAT executor is reachable from skeleton evidence",
    ),
    NoProviderKatExecutorReachableFromCandidatePackagingEvidence(
        "no provider KAT executor is reachable from candidate packaging evidence",
    ),
    NoProviderKatExecutorReachableFromDependencyBuildEvidence(
        "no provider KAT executor is reachable from dependency/build evidence",
    ),
    NoProviderKatExecutorReachableFromPublicVectorDocs(
        "no provider KAT executor is reachable from public vector docs",
    ),
    NoProviderKatExecutorReachableFromCreationUnlockStorageReadiness(
        "no provider KAT executor is reachable from creation/unlock/storage readiness",
    ),
    NoProviderKatExecutorAcceptsProviderHandles("no provider KAT executor accepts provider handles"),
    NoProviderKatExecutorExposesCryptoObjects("no provider KAT executor exposes crypto objects"),
    NoProviderKatExecutorAcceptsByteMaterial("no provider KAT executor accepts byte material"),
    NoProviderKatExecutorHasPlatformCryptoImports("no provider KAT executor has platform crypto imports"),
    NoProviderKatExecutorCanExecuteProviderOperations(
        "no provider KAT executor can execute provider operations",
    ),
    NoProviderKatExecutorCanCallRandomness("no provider KAT executor can call randomness"),
    NoProviderKatExecutorCanRunKdfAeadHkdfHmac(
        "no provider KAT executor can run KDF/AEAD/HKDF/HMAC",
    ),
    NoProviderKatExecutorCanComputeHeaderCommitments(
        "no provider KAT executor can compute header commitments",
    ),
    NoProviderKatExecutorCanEncryptDecryptRecords("no provider KAT executor can encrypt/decrypt records"),
    NoProviderKatExecutorCanWrapUnwrapKeys("no provider KAT executor can wrap/unwrap keys"),
    NoProviderKatExecutorCanPromoteDependencyBuildEvidence(
        "no provider KAT executor can promote dependency/build evidence",
    ),
    NoProviderKatExecutorCanPromotePackagingSkeletonAuditMatrixEvidence(
        "no provider KAT executor can promote packaging/skeleton/audit/matrix evidence",
    ),
    NoProviderKatExecutorCanConvertPublicVectorDocsIntoProductionReadiness(
        "no provider KAT executor can convert public vector documentation into production readiness",
    ),
    WarningUserConsentTestOnlyCannotOverride("warning/user-consent/test-only evidence cannot override"),
    ProviderSelectionDisabledProviderOnly("provider selection remains disabled-provider-only"),
    ProductionProviderSelectableFalse("productionProviderSelectable remains false"),
    MainnetDisabled("mainnet remains disabled"),
}

enum class SkaldVaultV1ProviderKatExecutionIsolationBlocker(val label: String) {
    KatExecutionIsolationBoundaryStillDisabled("provider KAT execution isolation boundary still disabled"),
    ModelEvidenceOnly("model evidence only"),
    NoNonDisabledProviderKatExecutor("no non-disabled provider KAT executor"),
    NoProviderKatExecutor("no provider KAT executor"),
    ProviderSelectionLockedToDisabledProvider("provider selection locked to disabled provider"),
    ProductionProviderSelectableFalse("productionProviderSelectable is false"),
    ProviderOperationDispatchIsolationBlocked("provider operation dispatch isolation blocked"),
    ProviderFactoryIsolationBlocked("provider factory isolation blocked"),
    ProviderRegistryIsolationBlocked("provider registry isolation blocked"),
    NonSelectableProviderSkeletonStillDisabled("non-selectable provider skeleton still disabled"),
    SkeletonKatExcluded("skeleton KAT excluded"),
    CandidateKatExcluded("candidate KAT excluded"),
    FactoryKatExcluded("factory KAT excluded"),
    RegistryKatExcluded("registry KAT excluded"),
    DispatchKatExcluded("dispatch KAT excluded"),
    DependencyBuildKatExcluded("dependency/build KAT excluded"),
    CandidatePackagingKatExcluded("candidate packaging KAT excluded"),
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
    KatContractEvidenceNonAuthorizing("provider KAT contract evidence is non-authorizing"),
    TestProviderKatHarnessTestOnly("test-provider KAT harness evidence is test-only"),
    PublicVectorDocumentationNonAuthorizing("public vector documentation is non-authorizing"),
    CanonicalVectorDocumentationNonExecutable("canonical vector documentation is non-executable"),
    KatExecutorObjectsRejected("KAT executor objects rejected"),
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
    ProviderSelectionAuthorizationUnavailable("provider selection authorization unavailable"),
    ProductionProviderSelectableAuthorizationUnavailable(
        "productionProviderSelectable authorization unavailable",
    ),
    WarningOnlyEvidenceCannotAuthorize("warning-only evidence cannot authorize"),
    UserConsentCannotOverride("user consent cannot override"),
    TestOnlyEvidenceCannotAuthorizeProduction("test-only evidence cannot authorize production"),
    ReleaseReviewMissing("release review missing"),
    MainnetDisabled("mainnet disabled"),
}

enum class SkaldVaultV1ProviderKatExecutionIsolationRedactionClass(val label: String) {
    PolicyIdsOnly("policy ids only"),
    KatTopicOnly("KAT topic only"),
    RiskCategoryOnly("risk category only"),
    RequiredPropertyOnly("required property only"),
    BoundaryAndBlockerClassesOnly("boundary and blocker classes only"),
    NoDiagnosticPayload("no diagnostic payload"),
}

enum class SkaldVaultV1ProviderKatExecutionIsolationExternalEvidenceKind(val label: String) {
    ProviderKatContract("provider KAT contract"),
    TestProviderKatHarnessConcept("test-provider KAT harness concept"),
    PublicNonWalletVectorDocumentation("public non-wallet vector documentation"),
    CanonicalHeaderHkdfHmacVectorDocumentation("canonical header/HKDF/HMAC vector documentation"),
}

data class SkaldVaultV1ProviderKatExecutionIsolationExternalEvidence(
    val kind: SkaldVaultV1ProviderKatExecutionIsolationExternalEvidenceKind,
    val evidenceOnly: Boolean = true,
    val productionAuthorizing: Boolean = false,
    val runtimeExecutionAvailable: Boolean = false,
    val safeEvidenceId: String,
) {
    override fun toString(): String =
        "SkaldVaultV1ProviderKatExecutionIsolationExternalEvidence(" +
            "kind=$kind, " +
            "evidenceOnly=$evidenceOnly, " +
            "productionAuthorizing=$productionAuthorizing, " +
            "runtimeExecutionAvailable=$runtimeExecutionAvailable, " +
            "safeEvidenceId=<redacted>, " +
            "providerHandle=<redacted>, " +
            "katExecutorObject=<redacted>, " +
            "cryptoObject=<redacted>, " +
            "byteMaterial=<redacted>, " +
            "secretMaterial=<redacted>" +
            ")"
}

data class SkaldVaultV1ProviderKatExecutionIsolationCapability(
    val providerKatExecutionIsolationModeled: Boolean,
    val providerKatExecutorExistsForNonDisabledProvider: Boolean,
    val providerKatExecutorReachableFromSelection: Boolean,
    val providerKatExecutorReachableFromRegistry: Boolean,
    val providerKatExecutorReachableFromFactory: Boolean,
    val providerKatExecutorReachableFromDispatch: Boolean,
    val providerKatExecutorCanInvokeSkeleton: Boolean,
    val providerKatExecutorCanInvokeCandidate: Boolean,
    val providerKatExecutorCanInvokeProviderRuntime: Boolean,
    val providerKatExecutorCanExposeProviderHandle: Boolean,
    val providerKatExecutorCanExposeCryptoObject: Boolean,
    val providerKatExecutorAcceptsByteMaterial: Boolean,
    val providerKatExecutorCanExecuteProviderOperations: Boolean,
    val providerKatExecutorCanUseRandomness: Boolean,
    val providerKatExecutorCanRunKdf: Boolean,
    val providerKatExecutorCanRunAead: Boolean,
    val providerKatExecutorCanComputeHeaderCommitment: Boolean,
    val providerKatExecutorCanEncryptRecords: Boolean,
    val providerKatExecutorCanDecryptRecords: Boolean,
    val providerKatExecutorCanWrapKeys: Boolean,
    val providerKatExecutorCanAuthorizeProviderSelection: Boolean,
    val providerKatExecutorCanAuthorizeProductionProviderSelectable: Boolean,
    val providerKatExecutorCanCreateVault: Boolean,
    val providerKatExecutorCanUnlockVault: Boolean,
    val providerKatExecutorCanPersistVault: Boolean,
    val providerKatExecutorCanEnableMainnet: Boolean,
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
        val StillDisabled = SkaldVaultV1ProviderKatExecutionIsolationCapability(
            providerKatExecutionIsolationModeled = true,
            providerKatExecutorExistsForNonDisabledProvider = false,
            providerKatExecutorReachableFromSelection = false,
            providerKatExecutorReachableFromRegistry = false,
            providerKatExecutorReachableFromFactory = false,
            providerKatExecutorReachableFromDispatch = false,
            providerKatExecutorCanInvokeSkeleton = false,
            providerKatExecutorCanInvokeCandidate = false,
            providerKatExecutorCanInvokeProviderRuntime = false,
            providerKatExecutorCanExposeProviderHandle = false,
            providerKatExecutorCanExposeCryptoObject = false,
            providerKatExecutorAcceptsByteMaterial = false,
            providerKatExecutorCanExecuteProviderOperations = false,
            providerKatExecutorCanUseRandomness = false,
            providerKatExecutorCanRunKdf = false,
            providerKatExecutorCanRunAead = false,
            providerKatExecutorCanComputeHeaderCommitment = false,
            providerKatExecutorCanEncryptRecords = false,
            providerKatExecutorCanDecryptRecords = false,
            providerKatExecutorCanWrapKeys = false,
            providerKatExecutorCanAuthorizeProviderSelection = false,
            providerKatExecutorCanAuthorizeProductionProviderSelectable = false,
            providerKatExecutorCanCreateVault = false,
            providerKatExecutorCanUnlockVault = false,
            providerKatExecutorCanPersistVault = false,
            providerKatExecutorCanEnableMainnet = false,
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

class SkaldVaultV1ProviderKatExecutionIsolationPolicyToken private constructor(
    val policyId: String,
    val topic: SkaldVaultV1ProviderKatExecutionIsolationTopic?,
    val risk: SkaldVaultV1ProviderKatExecutionIsolationRisk?,
    val requiredProperty: SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty?,
) {
    val containsProviderHandle: Boolean = false
    val containsKatExecutorObject: Boolean = false
    val containsDispatcherObject: Boolean = false
    val containsFactoryObject: Boolean = false
    val containsRegistryObject: Boolean = false
    val containsCryptoObject: Boolean = false
    val containsByteMaterial: Boolean = false
    val containsPathOrRootText: Boolean = false
    val containsStorageIdentifier: Boolean = false
    val containsSecretMaterial: Boolean = false

    override fun toString(): String =
        "SkaldVaultV1ProviderKatExecutionIsolationPolicyToken(" +
            "policyId=$policyId, " +
            "topic=$topic, " +
            "risk=$risk, " +
            "requiredProperty=$requiredProperty, " +
            "providerHandle=<redacted>, " +
            "katExecutorObject=<redacted>, " +
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
            topic: SkaldVaultV1ProviderKatExecutionIsolationTopic?,
            risk: SkaldVaultV1ProviderKatExecutionIsolationRisk?,
            requiredProperty: SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty?,
        ): SkaldVaultV1ProviderKatExecutionIsolationPolicyToken {
            return SkaldVaultV1ProviderKatExecutionIsolationPolicyToken(
                policyId = policyId,
                topic = topic,
                risk = risk,
                requiredProperty = requiredProperty,
            )
        }
    }
}

class SkaldVaultV1ProviderKatExecutionIsolationRequest private constructor(
    val source: SkaldVaultV1ProviderKatExecutionIsolationSource,
    val topic: SkaldVaultV1ProviderKatExecutionIsolationTopic?,
    val risk: SkaldVaultV1ProviderKatExecutionIsolationRisk?,
    val requiredProperty: SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty?,
    private val providerSelectionResult: VaultCryptoProviderSelectionResult?,
    private val providerOperationDispatchIsolationEvidence:
        SkaldVaultV1ProviderOperationDispatchIsolationEvidence?,
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
    private val providerKatContractEvidence: VaultCryptoProviderKatContract?,
    private val productionProviderAcceptanceEvidence: ProductionProviderAcceptanceAssessment?,
    private val testProviderKatHarnessEvidence: SkaldVaultV1ProviderKatExecutionIsolationExternalEvidence?,
    private val publicVectorDocumentationEvidence: SkaldVaultV1ProviderKatExecutionIsolationExternalEvidence?,
    private val canonicalVectorDocumentationEvidence: SkaldVaultV1ProviderKatExecutionIsolationExternalEvidence?,
) {
    val providerSelectionEvidenceSupplied: Boolean
        get() = providerSelectionResult != null

    val providerOperationDispatchIsolationEvidenceSupplied: Boolean
        get() = providerOperationDispatchIsolationEvidence != null

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

    val providerKatContractEvidenceSupplied: Boolean
        get() = providerKatContractEvidence != null

    val productionProviderAcceptanceEvidenceSupplied: Boolean
        get() = productionProviderAcceptanceEvidence != null

    val testProviderKatHarnessEvidenceSupplied: Boolean
        get() = testProviderKatHarnessEvidence != null

    val publicVectorDocumentationEvidenceSupplied: Boolean
        get() = publicVectorDocumentationEvidence != null

    val canonicalVectorDocumentationEvidenceSupplied: Boolean
        get() = canonicalVectorDocumentationEvidence != null

    override fun toString(): String =
        "SkaldVaultV1ProviderKatExecutionIsolationRequest(" +
            "source=$source, " +
            "topic=$topic, " +
            "risk=$risk, " +
            "requiredProperty=$requiredProperty, " +
            "providerSelection=<redacted>, " +
            "providerOperationDispatchIsolation=<redacted>, " +
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
            "persistenceReadiness=<redacted>, " +
            "providerKatContract=<redacted>, " +
            "productionProviderAcceptance=<redacted>, " +
            "testProviderKatHarness=<redacted>, " +
            "publicVectorDocumentation=<redacted>, " +
            "canonicalVectorDocumentation=<redacted>" +
            ")"

    companion object {
        fun currentEvidence(
            providerSelectionResult: VaultCryptoProviderSelectionResult? = null,
            providerOperationDispatchIsolationEvidence:
                SkaldVaultV1ProviderOperationDispatchIsolationEvidence? = null,
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
            providerKatContractEvidence: VaultCryptoProviderKatContract? = null,
            productionProviderAcceptanceEvidence: ProductionProviderAcceptanceAssessment? = null,
            testProviderKatHarnessEvidence: SkaldVaultV1ProviderKatExecutionIsolationExternalEvidence? = null,
            publicVectorDocumentationEvidence: SkaldVaultV1ProviderKatExecutionIsolationExternalEvidence? = null,
            canonicalVectorDocumentationEvidence: SkaldVaultV1ProviderKatExecutionIsolationExternalEvidence? = null,
        ): SkaldVaultV1ProviderKatExecutionIsolationRequest =
            SkaldVaultV1ProviderKatExecutionIsolationRequest(
                source = SkaldVaultV1ProviderKatExecutionIsolationSource.CurrentTypedEvidence,
                topic = null,
                risk = null,
                requiredProperty = null,
                providerSelectionResult = providerSelectionResult,
                providerOperationDispatchIsolationEvidence = providerOperationDispatchIsolationEvidence,
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
                providerKatContractEvidence = providerKatContractEvidence,
                productionProviderAcceptanceEvidence = productionProviderAcceptanceEvidence,
                testProviderKatHarnessEvidence = testProviderKatHarnessEvidence,
                publicVectorDocumentationEvidence = publicVectorDocumentationEvidence,
                canonicalVectorDocumentationEvidence = canonicalVectorDocumentationEvidence,
            )

        fun forTopic(
            topic: SkaldVaultV1ProviderKatExecutionIsolationTopic,
        ): SkaldVaultV1ProviderKatExecutionIsolationRequest =
            audit(
                source = SkaldVaultV1ProviderKatExecutionIsolationSource.KatTopicAudit,
                topic = topic,
            )

        fun forRisk(
            risk: SkaldVaultV1ProviderKatExecutionIsolationRisk,
        ): SkaldVaultV1ProviderKatExecutionIsolationRequest =
            audit(
                source = SkaldVaultV1ProviderKatExecutionIsolationSource.KatRiskAudit,
                risk = risk,
            )

        fun forRequiredProperty(
            requiredProperty: SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty,
        ): SkaldVaultV1ProviderKatExecutionIsolationRequest =
            audit(
                source = SkaldVaultV1ProviderKatExecutionIsolationSource.RequiredPropertyAudit,
                requiredProperty = requiredProperty,
            )

        fun evidenceInteractionAudit(): SkaldVaultV1ProviderKatExecutionIsolationRequest =
            audit(source = SkaldVaultV1ProviderKatExecutionIsolationSource.EvidenceInteractionAudit)

        fun externalEvidence(
            kind: SkaldVaultV1ProviderKatExecutionIsolationExternalEvidenceKind,
            safeEvidenceId: String,
        ): SkaldVaultV1ProviderKatExecutionIsolationExternalEvidence =
            SkaldVaultV1ProviderKatExecutionIsolationExternalEvidence(
                kind = kind,
                safeEvidenceId = safeEvidenceId,
            )

        private fun audit(
            source: SkaldVaultV1ProviderKatExecutionIsolationSource,
            topic: SkaldVaultV1ProviderKatExecutionIsolationTopic? = null,
            risk: SkaldVaultV1ProviderKatExecutionIsolationRisk? = null,
            requiredProperty: SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty? = null,
        ): SkaldVaultV1ProviderKatExecutionIsolationRequest =
            SkaldVaultV1ProviderKatExecutionIsolationRequest(
                source = source,
                topic = topic,
                risk = risk,
                requiredProperty = requiredProperty,
                providerSelectionResult = null,
                providerOperationDispatchIsolationEvidence = null,
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
                providerKatContractEvidence = null,
                productionProviderAcceptanceEvidence = null,
                testProviderKatHarnessEvidence = null,
                publicVectorDocumentationEvidence = null,
                canonicalVectorDocumentationEvidence = null,
            )
    }
}

data class SkaldVaultV1ProviderKatExecutionIsolationTopicRow(
    val topic: SkaldVaultV1ProviderKatExecutionIsolationTopic,
    val statuses: Set<SkaldVaultV1ProviderKatExecutionIsolationStatus>,
    val blockers: Set<SkaldVaultV1ProviderKatExecutionIsolationBlocker>,
    val modeled: Boolean,
    val evidenceOnly: Boolean,
    val katExcluded: Boolean,
    val katExecutorAvailable: Boolean,
    val runtimeKatAllowed: Boolean,
    val providerRuntimeInvoked: Boolean,
    val katEvidenceAuthorizesProduction: Boolean,
    val redactionClass: SkaldVaultV1ProviderKatExecutionIsolationRedactionClass,
)

data class SkaldVaultV1ProviderKatExecutionIsolationRiskRow(
    val risk: SkaldVaultV1ProviderKatExecutionIsolationRisk,
    val statuses: Set<SkaldVaultV1ProviderKatExecutionIsolationStatus>,
    val blockers: Set<SkaldVaultV1ProviderKatExecutionIsolationBlocker>,
    val rejected: Boolean,
    val accepted: Boolean,
    val canRunKat: Boolean,
    val canInvokeRuntime: Boolean,
    val canExecute: Boolean,
    val canChangeSelectability: Boolean,
    val redactionClass: SkaldVaultV1ProviderKatExecutionIsolationRedactionClass,
)

data class SkaldVaultV1ProviderKatExecutionIsolationRequiredPropertyRow(
    val requiredProperty: SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty,
    val statuses: Set<SkaldVaultV1ProviderKatExecutionIsolationStatus>,
    val blockers: Set<SkaldVaultV1ProviderKatExecutionIsolationBlocker>,
    val satisfiedForCurrentState: Boolean,
    val futureExplicitReviewRequired: Boolean,
    val katExecutorRuntimeAvailable: Boolean,
    val providerKatExecutionAllowed: Boolean,
    val redactionClass: SkaldVaultV1ProviderKatExecutionIsolationRedactionClass,
)

data class SkaldVaultV1ProviderKatExecutionIsolationSummary(
    val policyId: String,
    val policyVersion: Int,
    val statuses: Set<SkaldVaultV1ProviderKatExecutionIsolationStatus>,
    val topics: Set<SkaldVaultV1ProviderKatExecutionIsolationTopic>,
    val risks: Set<SkaldVaultV1ProviderKatExecutionIsolationRisk>,
    val requiredProperties: Set<SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty>,
    val blockers: Set<SkaldVaultV1ProviderKatExecutionIsolationBlocker>,
    val redactionClasses: Set<SkaldVaultV1ProviderKatExecutionIsolationRedactionClass>,
    val capability: SkaldVaultV1ProviderKatExecutionIsolationCapability,
    val stillDisabled: Boolean,
    val evidenceOnly: Boolean,
    val confirmsNoKatExecutor: Boolean,
    val blocksKatExecution: Boolean,
    val blocksProviderSelection: Boolean,
)

data class SkaldVaultV1ProviderKatExecutionIsolationEvidence(
    val policyId: String,
    val policyVersion: Int,
    val source: SkaldVaultV1ProviderKatExecutionIsolationSource,
    val status: SkaldVaultV1ProviderKatExecutionIsolationStatus,
    val topicRows: List<SkaldVaultV1ProviderKatExecutionIsolationTopicRow>,
    val riskRows: List<SkaldVaultV1ProviderKatExecutionIsolationRiskRow>,
    val requiredPropertyRows: List<SkaldVaultV1ProviderKatExecutionIsolationRequiredPropertyRow>,
    val blockers: Set<SkaldVaultV1ProviderKatExecutionIsolationBlocker>,
    val capability: SkaldVaultV1ProviderKatExecutionIsolationCapability,
    val policyTokenEvidence: SkaldVaultV1ProviderKatExecutionIsolationPolicyToken,
    val providerSelectionEvidenceConsumed: Boolean,
    val providerOperationDispatchIsolationEvidenceConsumed: Boolean,
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
    val providerKatContractEvidenceConsumed: Boolean,
    val productionProviderAcceptanceEvidenceConsumed: Boolean,
    val testProviderKatHarnessEvidenceConsumed: Boolean,
    val publicVectorDocumentationEvidenceConsumed: Boolean,
    val canonicalVectorDocumentationEvidenceConsumed: Boolean,
    val providerKatExecutionIsolationBoundaryModeled: Boolean = true,
    val providerKatExecutionIsolationStillDisabled: Boolean = true,
    val providerKatExecutionIsolationConfirmsNoKatExecutor: Boolean = true,
    val providerKatExecutionIsolationExcludesSkeletonKat: Boolean = true,
    val providerKatExecutionIsolationExcludesCandidateKat: Boolean = true,
    val providerKatExecutionIsolationExcludesRuntimeProviderKat: Boolean = true,
    val providerKatExecutionIsolationDoesNotRegisterProvider: Boolean = true,
    val providerKatExecutionIsolationDoesNotEnableProviderSelection: Boolean = true,
    val providerKatExecutionIsolationDoesNotRunCrypto: Boolean = true,
    val providerKatExecutionIsolationDoesNotRunKat: Boolean = true,
    val providerKatExecutionIsolationDoesNotEnableCreation: Boolean = true,
    val providerKatExecutionIsolationDoesNotEnableUnlock: Boolean = true,
    val providerKatExecutionIsolationDoesNotEnablePersistence: Boolean = true,
    val providerKatExecutionIsolationModeled: Boolean = true,
    val providerKatExecutorAdded: Boolean = false,
    val providerKatExecutorExistsForNonDisabledProvider: Boolean = false,
    val providerKatExecutorReachableFromSelection: Boolean = false,
    val providerKatExecutorReachableFromRegistry: Boolean = false,
    val providerKatExecutorReachableFromFactory: Boolean = false,
    val providerKatExecutorReachableFromDispatch: Boolean = false,
    val providerKatExecutorCanInvokeSkeleton: Boolean = false,
    val providerKatExecutorCanInvokeCandidate: Boolean = false,
    val providerKatExecutorCanInvokeProviderRuntime: Boolean = false,
    val providerKatExecutorCanExposeProviderHandle: Boolean = false,
    val providerKatExecutorCanExposeCryptoObject: Boolean = false,
    val providerKatExecutorAcceptsByteMaterial: Boolean = false,
    val providerKatExecutorCanExecuteProviderOperations: Boolean = false,
    val providerKatExecutorCanUseRandomness: Boolean = false,
    val providerKatExecutorCanRunKdf: Boolean = false,
    val providerKatExecutorCanRunAead: Boolean = false,
    val providerKatExecutorCanComputeHeaderCommitment: Boolean = false,
    val providerKatExecutorCanEncryptRecords: Boolean = false,
    val providerKatExecutorCanDecryptRecords: Boolean = false,
    val providerKatExecutorCanWrapKeys: Boolean = false,
    val providerKatExecutorCanAuthorizeProviderSelection: Boolean = false,
    val providerKatExecutorCanAuthorizeProductionProviderSelectable: Boolean = false,
    val providerKatExecutorCanCreateVault: Boolean = false,
    val providerKatExecutorCanUnlockVault: Boolean = false,
    val providerKatExecutorCanPersistVault: Boolean = false,
    val providerKatExecutorCanEnableMainnet: Boolean = false,
    val providerOperationDispatcherAdded: Boolean = false,
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
        "SkaldVaultV1ProviderKatExecutionIsolationEvidence(" +
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

sealed class SkaldVaultV1ProviderKatExecutionIsolationResult<out T> {
    data class Blocked<out T>(
        val value: T,
    ) : SkaldVaultV1ProviderKatExecutionIsolationResult<T>() {
        override fun toString(): String =
            "SkaldVaultV1ProviderKatExecutionIsolationResult.Blocked(" +
                "value=<redacted-provider-kat-execution-isolation-evidence>)"
    }
}

object SkaldVaultV1ProviderKatExecutionIsolationPolicy :
    SkaldVaultV1ProviderKatExecutionIsolationBoundary {
    const val POLICY_ID = "skald-vault-v1-provider-kat-execution-isolation-boundary-v1"
    const val POLICY_VERSION = 1

    override fun evaluate(
        request: SkaldVaultV1ProviderKatExecutionIsolationRequest,
    ): SkaldVaultV1ProviderKatExecutionIsolationResult<SkaldVaultV1ProviderKatExecutionIsolationEvidence> {
        val topicRows = topicRowsFor(request.topic)
        val riskRows = riskRowsFor(request.risk)
        val requiredPropertyRows = requiredPropertyRowsFor(request.requiredProperty)
        return SkaldVaultV1ProviderKatExecutionIsolationResult.Blocked(
            SkaldVaultV1ProviderKatExecutionIsolationEvidence(
                policyId = POLICY_ID,
                policyVersion = POLICY_VERSION,
                source = request.source,
                status = SkaldVaultV1ProviderKatExecutionIsolationStatus.StillDisabled,
                topicRows = topicRows,
                riskRows = riskRows,
                requiredPropertyRows = requiredPropertyRows,
                blockers = topicRows.flatMap { it.blockers }.toSet() +
                    riskRows.flatMap { it.blockers }.toSet() +
                    requiredPropertyRows.flatMap { it.blockers }.toSet() +
                    baseBlockers,
                capability = SkaldVaultV1ProviderKatExecutionIsolationCapability.StillDisabled,
                policyTokenEvidence = SkaldVaultV1ProviderKatExecutionIsolationPolicyToken.redacted(
                    policyId = POLICY_ID,
                    topic = request.topic,
                    risk = request.risk,
                    requiredProperty = request.requiredProperty,
                ),
                providerSelectionEvidenceConsumed = request.providerSelectionEvidenceSupplied,
                providerOperationDispatchIsolationEvidenceConsumed =
                    request.providerOperationDispatchIsolationEvidenceSupplied,
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
                providerKatContractEvidenceConsumed = request.providerKatContractEvidenceSupplied,
                productionProviderAcceptanceEvidenceConsumed =
                    request.productionProviderAcceptanceEvidenceSupplied,
                testProviderKatHarnessEvidenceConsumed = request.testProviderKatHarnessEvidenceSupplied,
                publicVectorDocumentationEvidenceConsumed =
                    request.publicVectorDocumentationEvidenceSupplied,
                canonicalVectorDocumentationEvidenceConsumed =
                    request.canonicalVectorDocumentationEvidenceSupplied,
            ),
        )
    }

    fun currentPolicySummary(): SkaldVaultV1ProviderKatExecutionIsolationSummary =
        SkaldVaultV1ProviderKatExecutionIsolationSummary(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            statuses = SkaldVaultV1ProviderKatExecutionIsolationStatus.entries.toSet(),
            topics = SkaldVaultV1ProviderKatExecutionIsolationTopic.entries.toSet(),
            risks = SkaldVaultV1ProviderKatExecutionIsolationRisk.entries.toSet(),
            requiredProperties = SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty.entries.toSet(),
            blockers = SkaldVaultV1ProviderKatExecutionIsolationBlocker.entries.toSet(),
            redactionClasses = SkaldVaultV1ProviderKatExecutionIsolationRedactionClass.entries.toSet(),
            capability = SkaldVaultV1ProviderKatExecutionIsolationCapability.StillDisabled,
            stillDisabled = true,
            evidenceOnly = true,
            confirmsNoKatExecutor = true,
            blocksKatExecution = true,
            blocksProviderSelection = true,
        )

    private fun topicRowsFor(
        topic: SkaldVaultV1ProviderKatExecutionIsolationTopic?,
    ): List<SkaldVaultV1ProviderKatExecutionIsolationTopicRow> {
        val rows = SkaldVaultV1ProviderKatExecutionIsolationTopic.entries.map { currentTopic ->
            topicRow(currentTopic)
        }
        return topic?.let { requested -> rows.filter { it.topic == requested } } ?: rows
    }

    private fun riskRowsFor(
        risk: SkaldVaultV1ProviderKatExecutionIsolationRisk?,
    ): List<SkaldVaultV1ProviderKatExecutionIsolationRiskRow> {
        val rows = SkaldVaultV1ProviderKatExecutionIsolationRisk.entries.map { currentRisk ->
            riskRow(currentRisk)
        }
        return risk?.let { requested -> rows.filter { it.risk == requested } } ?: rows
    }

    private fun requiredPropertyRowsFor(
        requiredProperty: SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty?,
    ): List<SkaldVaultV1ProviderKatExecutionIsolationRequiredPropertyRow> {
        val rows = SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty.entries.map { currentProperty ->
            requiredPropertyRow(currentProperty)
        }
        return requiredProperty?.let { requested ->
            rows.filter { it.requiredProperty == requested }
        } ?: rows
    }

    private fun topicRow(
        topic: SkaldVaultV1ProviderKatExecutionIsolationTopic,
    ): SkaldVaultV1ProviderKatExecutionIsolationTopicRow =
        SkaldVaultV1ProviderKatExecutionIsolationTopicRow(
            topic = topic,
            statuses = baseStatuses + topicStatus(topic),
            blockers = topicBlockers(topic) + baseBlockers,
            modeled = true,
            evidenceOnly = true,
            katExcluded = topic !in nonExcludedEvidenceTopics,
            katExecutorAvailable = false,
            runtimeKatAllowed = false,
            providerRuntimeInvoked = false,
            katEvidenceAuthorizesProduction = false,
            redactionClass = SkaldVaultV1ProviderKatExecutionIsolationRedactionClass.KatTopicOnly,
        )

    private fun riskRow(
        risk: SkaldVaultV1ProviderKatExecutionIsolationRisk,
    ): SkaldVaultV1ProviderKatExecutionIsolationRiskRow =
        SkaldVaultV1ProviderKatExecutionIsolationRiskRow(
            risk = risk,
            statuses = baseStatuses + riskStatus(risk),
            blockers = riskBlockers(risk) + baseBlockers,
            rejected = true,
            accepted = false,
            canRunKat = false,
            canInvokeRuntime = false,
            canExecute = false,
            canChangeSelectability = false,
            redactionClass = SkaldVaultV1ProviderKatExecutionIsolationRedactionClass.RiskCategoryOnly,
        )

    private fun requiredPropertyRow(
        requiredProperty: SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty,
    ): SkaldVaultV1ProviderKatExecutionIsolationRequiredPropertyRow =
        SkaldVaultV1ProviderKatExecutionIsolationRequiredPropertyRow(
            requiredProperty = requiredProperty,
            statuses = baseStatuses + SkaldVaultV1ProviderKatExecutionIsolationStatus.KatExecutionIsolated,
            blockers = requiredPropertyBlockers(requiredProperty) + baseBlockers,
            satisfiedForCurrentState = true,
            futureExplicitReviewRequired = true,
            katExecutorRuntimeAvailable = false,
            providerKatExecutionAllowed = false,
            redactionClass = SkaldVaultV1ProviderKatExecutionIsolationRedactionClass.RequiredPropertyOnly,
        )

    private fun topicStatus(
        topic: SkaldVaultV1ProviderKatExecutionIsolationTopic,
    ): SkaldVaultV1ProviderKatExecutionIsolationStatus =
        when (topic) {
            SkaldVaultV1ProviderKatExecutionIsolationTopic.ProviderKatExecutionSurface,
            SkaldVaultV1ProviderKatExecutionIsolationTopic.DisabledProviderKatBehavior ->
                SkaldVaultV1ProviderKatExecutionIsolationStatus.NoKatExecutorAvailable
            SkaldVaultV1ProviderKatExecutionIsolationTopic.ProviderKatContractEvidence,
            SkaldVaultV1ProviderKatExecutionIsolationTopic.TestProviderKatHarnessEvidence,
            SkaldVaultV1ProviderKatExecutionIsolationTopic.PublicNonWalletVectorDocumentationEvidence,
            SkaldVaultV1ProviderKatExecutionIsolationTopic.CanonicalHeaderHkdfHmacVectorDocumentationEvidence ->
                SkaldVaultV1ProviderKatExecutionIsolationStatus.PublicVectorDocumentationNonAuthorizing
            SkaldVaultV1ProviderKatExecutionIsolationTopic.NonDisabledProviderKatExcluded,
            SkaldVaultV1ProviderKatExecutionIsolationTopic.FactoryKatExcluded,
            SkaldVaultV1ProviderKatExecutionIsolationTopic.RegistryKatExcluded,
            SkaldVaultV1ProviderKatExecutionIsolationTopic.DispatchKatExcluded,
            SkaldVaultV1ProviderKatExecutionIsolationTopic.SkeletonKatExcluded,
            SkaldVaultV1ProviderKatExecutionIsolationTopic.CandidateProviderKatExcluded ->
                SkaldVaultV1ProviderKatExecutionIsolationStatus.KatExecutionExcluded
            SkaldVaultV1ProviderKatExecutionIsolationTopic.ReleaseMainnetEvidenceFutureExplicitReviewOnly ->
                SkaldVaultV1ProviderKatExecutionIsolationStatus.MainnetBlocked
            else -> SkaldVaultV1ProviderKatExecutionIsolationStatus.RuntimeKatBlocked
        }

    private fun riskStatus(
        risk: SkaldVaultV1ProviderKatExecutionIsolationRisk,
    ): SkaldVaultV1ProviderKatExecutionIsolationStatus =
        when (risk) {
            SkaldVaultV1ProviderKatExecutionIsolationRisk.KatInvokesNonDisabledProvider,
            SkaldVaultV1ProviderKatExecutionIsolationRisk.KatInvokesSkeletonProvider,
            SkaldVaultV1ProviderKatExecutionIsolationRisk.KatInvokesCandidateProvider,
            SkaldVaultV1ProviderKatExecutionIsolationRisk.KatInvokesProviderFactory,
            SkaldVaultV1ProviderKatExecutionIsolationRisk.KatInvokesProviderRegistry,
            SkaldVaultV1ProviderKatExecutionIsolationRisk.KatInvokesProviderDispatcher ->
                SkaldVaultV1ProviderKatExecutionIsolationStatus.KatExecutionExcluded
            SkaldVaultV1ProviderKatExecutionIsolationRisk.KatTreatsPublicVectorDocsAsProductionApproval,
            SkaldVaultV1ProviderKatExecutionIsolationRisk.KatTreatsCanonicalDocsAsRuntimeKdfHkdfHmacExecution ->
                SkaldVaultV1ProviderKatExecutionIsolationStatus.PublicVectorDocumentationNonAuthorizing
            SkaldVaultV1ProviderKatExecutionIsolationRisk.KatRunsProviderOperations,
            SkaldVaultV1ProviderKatExecutionIsolationRisk.KatReachesRandomnessKdfAead ->
                SkaldVaultV1ProviderKatExecutionIsolationStatus.ProviderKatExecutionBlocked
            SkaldVaultV1ProviderKatExecutionIsolationRisk.KatComputesHeaderCommitment ->
                SkaldVaultV1ProviderKatExecutionIsolationStatus.HeaderCommitmentBlocked
            SkaldVaultV1ProviderKatExecutionIsolationRisk.KatEncryptsDecryptsRecords ->
                SkaldVaultV1ProviderKatExecutionIsolationStatus.RecordCryptoBlocked
            SkaldVaultV1ProviderKatExecutionIsolationRisk.KatWrapsUnwrapsKeys ->
                SkaldVaultV1ProviderKatExecutionIsolationStatus.KeyWrappingBlocked
            SkaldVaultV1ProviderKatExecutionIsolationRisk.KatEnablesMainnet ->
                SkaldVaultV1ProviderKatExecutionIsolationStatus.MainnetBlocked
            else -> SkaldVaultV1ProviderKatExecutionIsolationStatus.RuntimeKatBlocked
        }

    private fun topicBlockers(
        topic: SkaldVaultV1ProviderKatExecutionIsolationTopic,
    ): Set<SkaldVaultV1ProviderKatExecutionIsolationBlocker> =
        when (topic) {
            SkaldVaultV1ProviderKatExecutionIsolationTopic.ProviderKatExecutionSurface,
            SkaldVaultV1ProviderKatExecutionIsolationTopic.DisabledProviderKatBehavior ->
                setOf(
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.NoProviderKatExecutor,
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.NoNonDisabledProviderKatExecutor,
                )
            SkaldVaultV1ProviderKatExecutionIsolationTopic.ProviderKatContractEvidence ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.KatContractEvidenceNonAuthorizing)
            SkaldVaultV1ProviderKatExecutionIsolationTopic.TestProviderKatHarnessEvidence ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.TestProviderKatHarnessTestOnly)
            SkaldVaultV1ProviderKatExecutionIsolationTopic.PublicNonWalletVectorDocumentationEvidence ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.PublicVectorDocumentationNonAuthorizing)
            SkaldVaultV1ProviderKatExecutionIsolationTopic.CanonicalHeaderHkdfHmacVectorDocumentationEvidence ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.CanonicalVectorDocumentationNonExecutable)
            SkaldVaultV1ProviderKatExecutionIsolationTopic.ProviderSelfTestSurface,
            SkaldVaultV1ProviderKatExecutionIsolationTopic.ProviderRuntimeCheckSurface ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.KatExecutionUnavailable)
            SkaldVaultV1ProviderKatExecutionIsolationTopic.NonDisabledProviderKatExcluded ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.NoNonDisabledProviderKatExecutor)
            SkaldVaultV1ProviderKatExecutionIsolationTopic.FactoryKatExcluded ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.FactoryKatExcluded)
            SkaldVaultV1ProviderKatExecutionIsolationTopic.RegistryKatExcluded ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.RegistryKatExcluded)
            SkaldVaultV1ProviderKatExecutionIsolationTopic.DispatchKatExcluded ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.DispatchKatExcluded)
            SkaldVaultV1ProviderKatExecutionIsolationTopic.SkeletonKatExcluded ->
                setOf(
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.SkeletonKatExcluded,
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.NonSelectableProviderSkeletonStillDisabled,
                )
            SkaldVaultV1ProviderKatExecutionIsolationTopic.CandidateProviderKatExcluded ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.CandidateKatExcluded)
            SkaldVaultV1ProviderKatExecutionIsolationTopic.DependencyBuildKatExcluded ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.DependencyBuildKatExcluded)
            SkaldVaultV1ProviderKatExecutionIsolationTopic.CandidatePackagingKatExcluded ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.CandidatePackagingKatExcluded)
            SkaldVaultV1ProviderKatExecutionIsolationTopic.InterfaceAuditKatExcluded ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderInterfaceAuditEvidenceOnly)
            SkaldVaultV1ProviderKatExecutionIsolationTopic.PromotionBlockerKatExcluded ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderSelectionPromotionBlocked)
            SkaldVaultV1ProviderKatExecutionIsolationTopic.AuthorizationReadinessMatrixKatExcluded ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.AuthorizationReadinessMatrixBlocked)
            SkaldVaultV1ProviderKatExecutionIsolationTopic.ProviderOperationAuthorizationKatExcluded ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderOperationAuthorizationBlocked)
            SkaldVaultV1ProviderKatExecutionIsolationTopic.RuntimeRandomnessKatExcluded ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.RuntimeRandomnessAuthorizationBlocked)
            SkaldVaultV1ProviderKatExecutionIsolationTopic.KdfCalibrationKatExcluded ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.KdfCalibrationAuthorizationBlocked)
            SkaldVaultV1ProviderKatExecutionIsolationTopic.SecureStorageKatExcluded ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.SecureStorageAuthorizationBlocked)
            SkaldVaultV1ProviderKatExecutionIsolationTopic.CreationUnlockKatExcluded ->
                setOf(
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.CreationAuthorizationBlocked,
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.UnlockAuthorizationBlocked,
                )
            SkaldVaultV1ProviderKatExecutionIsolationTopic.PersistenceReadinessKatExcluded ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.PersistenceReadinessBlocked)
            SkaldVaultV1ProviderKatExecutionIsolationTopic.TestOnlyKatEvidenceExcludedFromProduction ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.TestOnlyEvidenceCannotAuthorizeProduction)
            SkaldVaultV1ProviderKatExecutionIsolationTopic.WarningOnlyEvidenceExcludedFromKatPromotion ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.WarningOnlyEvidenceCannotAuthorize)
            SkaldVaultV1ProviderKatExecutionIsolationTopic.UserConsentEvidenceExcludedFromKatPromotion ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.UserConsentCannotOverride)
            SkaldVaultV1ProviderKatExecutionIsolationTopic.ReleaseMainnetEvidenceFutureExplicitReviewOnly ->
                setOf(
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.ReleaseReviewMissing,
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.MainnetDisabled,
                )
        }

    private fun riskBlockers(
        risk: SkaldVaultV1ProviderKatExecutionIsolationRisk,
    ): Set<SkaldVaultV1ProviderKatExecutionIsolationBlocker> =
        when (risk) {
            SkaldVaultV1ProviderKatExecutionIsolationRisk.KatInvokesNonDisabledProvider ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.NoNonDisabledProviderKatExecutor)
            SkaldVaultV1ProviderKatExecutionIsolationRisk.KatInvokesSkeletonProvider ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.SkeletonKatExcluded)
            SkaldVaultV1ProviderKatExecutionIsolationRisk.KatInvokesCandidateProvider ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.CandidateKatExcluded)
            SkaldVaultV1ProviderKatExecutionIsolationRisk.KatInvokesProviderFactory ->
                setOf(
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.FactoryKatExcluded,
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderFactoryIsolationBlocked,
                )
            SkaldVaultV1ProviderKatExecutionIsolationRisk.KatInvokesProviderRegistry ->
                setOf(
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.RegistryKatExcluded,
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderRegistryIsolationBlocked,
                )
            SkaldVaultV1ProviderKatExecutionIsolationRisk.KatInvokesProviderDispatcher ->
                setOf(
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.DispatchKatExcluded,
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderOperationDispatchIsolationBlocked,
                )
            SkaldVaultV1ProviderKatExecutionIsolationRisk
                .KatTreatsDependencyBuildEvidenceAsExecutableProvider ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.DependencyBuildKatExcluded)
            SkaldVaultV1ProviderKatExecutionIsolationRisk.KatTreatsPackagingEvidenceAsExecutableProvider ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.CandidatePackagingKatExcluded)
            SkaldVaultV1ProviderKatExecutionIsolationRisk
                .KatTreatsInterfaceAuditEvidenceAsExecutableProvider ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderInterfaceAuditEvidenceOnly)
            SkaldVaultV1ProviderKatExecutionIsolationRisk
                .KatTreatsPromotionBlockerEvidenceAsExecutableProvider ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderSelectionPromotionBlocked)
            SkaldVaultV1ProviderKatExecutionIsolationRisk.KatTreatsMatrixEvidenceAsExecutableProvider ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.AuthorizationReadinessMatrixBlocked)
            SkaldVaultV1ProviderKatExecutionIsolationRisk.KatTreatsPublicVectorDocsAsProductionApproval ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.PublicVectorDocumentationNonAuthorizing)
            SkaldVaultV1ProviderKatExecutionIsolationRisk.KatTreatsCanonicalDocsAsRuntimeKdfHkdfHmacExecution ->
                setOf(
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.CanonicalVectorDocumentationNonExecutable,
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.KdfCalibrationAuthorizationBlocked,
                )
            SkaldVaultV1ProviderKatExecutionIsolationRisk.KatAcceptsProviderHandles ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderHandlesRejected)
            SkaldVaultV1ProviderKatExecutionIsolationRisk.KatAcceptsCryptoObjects ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.CryptoObjectsRejected)
            SkaldVaultV1ProviderKatExecutionIsolationRisk.KatAcceptsByteMaterial ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.ByteMaterialRejected)
            SkaldVaultV1ProviderKatExecutionIsolationRisk.KatAcceptsPlatformCryptoApis ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.PlatformCryptoImportsForbidden)
            SkaldVaultV1ProviderKatExecutionIsolationRisk.KatImportsTinkBouncyJavaxCrypto ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.TinkBouncyJavaxImportsForbidden)
            SkaldVaultV1ProviderKatExecutionIsolationRisk.KatCallsSecureRandom ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.SecureRandomForbidden)
            SkaldVaultV1ProviderKatExecutionIsolationRisk.KatRunsProviderOperations ->
                setOf(
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderOperationAuthorizationBlocked,
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderOperationExecutionForbidden,
                )
            SkaldVaultV1ProviderKatExecutionIsolationRisk.KatReachesRandomnessKdfAead ->
                setOf(
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.RuntimeRandomnessAuthorizationBlocked,
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.KdfCalibrationAuthorizationBlocked,
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderOperationAuthorizationBlocked,
                )
            SkaldVaultV1ProviderKatExecutionIsolationRisk.KatComputesHeaderCommitment ->
                setOf(
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.HeaderCommitmentUnavailable,
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderOperationAuthorizationBlocked,
                )
            SkaldVaultV1ProviderKatExecutionIsolationRisk.KatEncryptsDecryptsRecords ->
                setOf(
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.RecordCryptoUnavailable,
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderOperationAuthorizationBlocked,
                )
            SkaldVaultV1ProviderKatExecutionIsolationRisk.KatWrapsUnwrapsKeys ->
                setOf(
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.KeyWrappingUnavailable,
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.SecureStorageAuthorizationBlocked,
                )
            SkaldVaultV1ProviderKatExecutionIsolationRisk.KatAuthorizesProviderSelection ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderSelectionAuthorizationUnavailable)
            SkaldVaultV1ProviderKatExecutionIsolationRisk.KatAuthorizesProductionProviderSelectableTrue ->
                setOf(
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker
                        .ProductionProviderSelectableAuthorizationUnavailable,
                )
            SkaldVaultV1ProviderKatExecutionIsolationRisk.KatEnablesVaultCreationUnlockPersistence ->
                setOf(
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.CreationAuthorizationBlocked,
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.UnlockAuthorizationBlocked,
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.PersistenceReadinessBlocked,
                )
            SkaldVaultV1ProviderKatExecutionIsolationRisk.KatEnablesMainnet ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.MainnetDisabled)
        }

    private fun requiredPropertyBlockers(
        requiredProperty: SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty,
    ): Set<SkaldVaultV1ProviderKatExecutionIsolationBlocker> =
        when (requiredProperty) {
            SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty
                .NoProviderKatExecutorExistsForNonDisabledProviders ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.NoNonDisabledProviderKatExecutor)
            SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty
                .NoProviderKatExecutorReachableFromProviderSelection,
            SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty.ProviderSelectionDisabledProviderOnly ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderSelectionLockedToDisabledProvider)
            SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty
                .NoProviderKatExecutorReachableFromRegistryIsolation ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderRegistryIsolationBlocked)
            SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty
                .NoProviderKatExecutorReachableFromFactoryIsolation ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderFactoryIsolationBlocked)
            SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty
                .NoProviderKatExecutorReachableFromDispatchIsolation ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderOperationDispatchIsolationBlocked)
            SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty
                .NoProviderKatExecutorReachableFromSkeletonEvidence ->
                setOf(
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.SkeletonKatExcluded,
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.NonSelectableProviderSkeletonStillDisabled,
                )
            SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty
                .NoProviderKatExecutorReachableFromCandidatePackagingEvidence ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.CandidatePackagingKatExcluded)
            SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty
                .NoProviderKatExecutorReachableFromDependencyBuildEvidence ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.DependencyBuildKatExcluded)
            SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty
                .NoProviderKatExecutorReachableFromPublicVectorDocs ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.PublicVectorDocumentationNonAuthorizing)
            SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty
                .NoProviderKatExecutorReachableFromCreationUnlockStorageReadiness ->
                setOf(
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.CreationAuthorizationBlocked,
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.UnlockAuthorizationBlocked,
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.SecureStorageAuthorizationBlocked,
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.PersistenceReadinessBlocked,
                )
            SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty
                .NoProviderKatExecutorAcceptsProviderHandles ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderHandlesRejected)
            SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty
                .NoProviderKatExecutorExposesCryptoObjects ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.CryptoObjectsRejected)
            SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty
                .NoProviderKatExecutorAcceptsByteMaterial ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.ByteMaterialRejected)
            SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty
                .NoProviderKatExecutorHasPlatformCryptoImports ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.PlatformCryptoImportsForbidden)
            SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty
                .NoProviderKatExecutorCanExecuteProviderOperations ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderOperationExecutionForbidden)
            SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty.NoProviderKatExecutorCanCallRandomness ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.RuntimeRandomnessAuthorizationBlocked)
            SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty.NoProviderKatExecutorCanRunKdfAeadHkdfHmac ->
                setOf(
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.KdfCalibrationAuthorizationBlocked,
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderOperationAuthorizationBlocked,
                )
            SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty
                .NoProviderKatExecutorCanComputeHeaderCommitments ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.HeaderCommitmentUnavailable)
            SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty.NoProviderKatExecutorCanEncryptDecryptRecords ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.RecordCryptoUnavailable)
            SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty.NoProviderKatExecutorCanWrapUnwrapKeys ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.KeyWrappingUnavailable)
            SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty
                .NoProviderKatExecutorCanPromoteDependencyBuildEvidence ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.DependencyBuildKatExcluded)
            SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty
                .NoProviderKatExecutorCanPromotePackagingSkeletonAuditMatrixEvidence ->
                setOf(
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.CandidatePackagingKatExcluded,
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.NonSelectableProviderSkeletonStillDisabled,
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderInterfaceAuditEvidenceOnly,
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.AuthorizationReadinessMatrixBlocked,
                )
            SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty
                .NoProviderKatExecutorCanConvertPublicVectorDocsIntoProductionReadiness ->
                setOf(
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.PublicVectorDocumentationNonAuthorizing,
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.CanonicalVectorDocumentationNonExecutable,
                )
            SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty.WarningUserConsentTestOnlyCannotOverride ->
                setOf(
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.WarningOnlyEvidenceCannotAuthorize,
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.UserConsentCannotOverride,
                    SkaldVaultV1ProviderKatExecutionIsolationBlocker.TestOnlyEvidenceCannotAuthorizeProduction,
                )
            SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty.ProductionProviderSelectableFalse ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProductionProviderSelectableFalse)
            SkaldVaultV1ProviderKatExecutionIsolationRequiredProperty.MainnetDisabled ->
                setOf(SkaldVaultV1ProviderKatExecutionIsolationBlocker.MainnetDisabled)
        }

    private val nonExcludedEvidenceTopics: Set<SkaldVaultV1ProviderKatExecutionIsolationTopic> =
        setOf(
            SkaldVaultV1ProviderKatExecutionIsolationTopic.ProviderKatExecutionSurface,
            SkaldVaultV1ProviderKatExecutionIsolationTopic.ProviderKatContractEvidence,
            SkaldVaultV1ProviderKatExecutionIsolationTopic.TestProviderKatHarnessEvidence,
            SkaldVaultV1ProviderKatExecutionIsolationTopic.PublicNonWalletVectorDocumentationEvidence,
            SkaldVaultV1ProviderKatExecutionIsolationTopic.CanonicalHeaderHkdfHmacVectorDocumentationEvidence,
            SkaldVaultV1ProviderKatExecutionIsolationTopic.DisabledProviderKatBehavior,
        )

    private val baseStatuses: Set<SkaldVaultV1ProviderKatExecutionIsolationStatus> =
        setOf(
            SkaldVaultV1ProviderKatExecutionIsolationStatus.BoundaryModeled,
            SkaldVaultV1ProviderKatExecutionIsolationStatus.StillDisabled,
            SkaldVaultV1ProviderKatExecutionIsolationStatus.EvidenceOnly,
            SkaldVaultV1ProviderKatExecutionIsolationStatus.WarningOnlyCannotAuthorize,
            SkaldVaultV1ProviderKatExecutionIsolationStatus.UserConsentCannotOverride,
            SkaldVaultV1ProviderKatExecutionIsolationStatus.TestOnlyRejectedForProduction,
        )

    private val baseBlockers: Set<SkaldVaultV1ProviderKatExecutionIsolationBlocker> =
        setOf(
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.KatExecutionIsolationBoundaryStillDisabled,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.ModelEvidenceOnly,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.NoNonDisabledProviderKatExecutor,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderSelectionLockedToDisabledProvider,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProductionProviderSelectableFalse,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.ProviderOperationAuthorizationBlocked,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.KatExecutionUnavailable,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.PublicVectorDocumentationNonAuthorizing,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.WarningOnlyEvidenceCannotAuthorize,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.UserConsentCannotOverride,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.TestOnlyEvidenceCannotAuthorizeProduction,
            SkaldVaultV1ProviderKatExecutionIsolationBlocker.MainnetDisabled,
        )
}
