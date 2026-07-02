package com.libertasprimordium.skald.security

enum class SkaldVaultV1TestOnlyProviderKatExecutorContractStatus(val label: String) {
    ContractModeled("contract modeled"),
    StillDisabled("still disabled"),
    ExecutorNotImplemented("executor not implemented"),
    ExecutorSurfaceUnavailable("executor surface unavailable"),
    ExecutionNotAuthorized("execution not authorized"),
    TestOnlyImplementationDeferred("test-only implementation deferred"),
    ProductionImplementationForbidden("production implementation forbidden"),
    ProviderSelectionAuthorizationBlocked("provider selection authorization blocked"),
    ProductionProviderSelectableFalse("productionProviderSelectable false"),
    VaultLifecycleBlocked("vault lifecycle blocked"),
    PersistenceBlocked("persistence blocked"),
    ProductionSyncBlocked("production sync blocked"),
    MainnetBlocked("mainnet blocked"),
}

enum class SkaldVaultV1TestOnlyProviderKatExecutorRole(
    val label: String,
    val currentRole: Boolean,
    val futureReviewRequired: Boolean,
    val productionForbidden: Boolean,
) {
    NoExecutor("no executor", currentRole = false, futureReviewRequired = false, productionForbidden = false),
    ContractOnlyModel("contract-only model", currentRole = true, futureReviewRequired = false, productionForbidden = false),
    FutureDesktopTestExecutor(
        "future desktopTest executor",
        currentRole = false,
        futureReviewRequired = true,
        productionForbidden = false,
    ),
    FutureAndroidInstrumentedTestExecutor(
        "future androidInstrumentedTest executor",
        currentRole = false,
        futureReviewRequired = true,
        productionForbidden = false,
    ),
    FutureCommonTestModelOnlyAssertions(
        "future commonTest model-only assertions",
        currentRole = false,
        futureReviewRequired = true,
        productionForbidden = false,
    ),
    ProductionExecutorForbidden(
        "production executor forbidden",
        currentRole = false,
        futureReviewRequired = false,
        productionForbidden = true,
    ),
    ReleaseValidationEvidenceOnly(
        "release validation evidence only",
        currentRole = false,
        futureReviewRequired = true,
        productionForbidden = true,
    ),
}

enum class SkaldVaultV1TestOnlyProviderKatExecutorInputClass(
    val label: String,
    val futureAllowedAsReferenceOnly: Boolean,
    val currentAccepted: Boolean,
    val forbidden: Boolean,
) {
    PublicNonWalletKatVectorReference(
        "public non-wallet KAT vector reference",
        futureAllowedAsReferenceOnly = true,
        currentAccepted = false,
        forbidden = false,
    ),
    CanonicalSkaldNonWalletVectorReference(
        "canonical Skald non-wallet vector reference",
        futureAllowedAsReferenceOnly = true,
        currentAccepted = false,
        forbidden = false,
    ),
    SyntheticTestOnlyMaterialReference(
        "synthetic test-only material reference",
        futureAllowedAsReferenceOnly = true,
        currentAccepted = false,
        forbidden = false,
    ),
    DeterministicTestOnlyProviderIdentity(
        "deterministic test-only provider identity",
        futureAllowedAsReferenceOnly = true,
        currentAccepted = false,
        forbidden = false,
    ),
    TestOnlyOperationLabel(
        "test-only operation label",
        futureAllowedAsReferenceOnly = true,
        currentAccepted = false,
        forbidden = false,
    ),
    RedactedExpectedOutcomeLabel(
        "redacted expected outcome label",
        futureAllowedAsReferenceOnly = true,
        currentAccepted = false,
        forbidden = false,
    ),
    PlatformRuntimeEvidenceLabel(
        "platform runtime evidence label",
        futureAllowedAsReferenceOnly = true,
        currentAccepted = false,
        forbidden = false,
    ),
    SourceSetEvidenceLabel(
        "source-set evidence label",
        futureAllowedAsReferenceOnly = true,
        currentAccepted = false,
        forbidden = false,
    ),
    RawCredentialPhrase("raw credential phrase", futureAllowedAsReferenceOnly = false, currentAccepted = false, forbidden = true),
    RawPin("raw PIN", futureAllowedAsReferenceOnly = false, currentAccepted = false, forbidden = true),
    RawSeed("raw seed", futureAllowedAsReferenceOnly = false, currentAccepted = false, forbidden = true),
    RawMnemonic("raw mnemonic", futureAllowedAsReferenceOnly = false, currentAccepted = false, forbidden = true),
    RawDescriptor("raw descriptor", futureAllowedAsReferenceOnly = false, currentAccepted = false, forbidden = true),
    XprvTprvWif("xprv/tprv/WIF", futureAllowedAsReferenceOnly = false, currentAccepted = false, forbidden = true),
    NostrNsec("Nostr nsec", futureAllowedAsReferenceOnly = false, currentAccepted = false, forbidden = true),
    LightningCredential("Lightning credential", futureAllowedAsReferenceOnly = false, currentAccepted = false, forbidden = true),
    CashuProof("Cashu proof", futureAllowedAsReferenceOnly = false, currentAccepted = false, forbidden = true),
    BackendCredential("backend credential", futureAllowedAsReferenceOnly = false, currentAccepted = false, forbidden = true),
    WalletLabel("wallet label", futureAllowedAsReferenceOnly = false, currentAccepted = false, forbidden = true),
    UtxoLabel("UTXO label", futureAllowedAsReferenceOnly = false, currentAccepted = false, forbidden = true),
    TransactionNote("transaction note", futureAllowedAsReferenceOnly = false, currentAccepted = false, forbidden = true),
    BackendObservationMetadata(
        "backend observation metadata",
        futureAllowedAsReferenceOnly = false,
        currentAccepted = false,
        forbidden = true,
    ),
    SecureMetadataRecord("secure metadata record", futureAllowedAsReferenceOnly = false, currentAccepted = false, forbidden = true),
    ProductionVaultRecord("production vault record", futureAllowedAsReferenceOnly = false, currentAccepted = false, forbidden = true),
    RawKey("raw key", futureAllowedAsReferenceOnly = false, currentAccepted = false, forbidden = true),
    RawRandomnessValue("raw randomness value", futureAllowedAsReferenceOnly = false, currentAccepted = false, forbidden = true),
    RawDerivationInput("raw derivation input", futureAllowedAsReferenceOnly = false, currentAccepted = false, forbidden = true),
    RawClearPayload("raw clear payload", futureAllowedAsReferenceOnly = false, currentAccepted = false, forbidden = true),
    RawEncryptedPayload("raw encrypted payload", futureAllowedAsReferenceOnly = false, currentAccepted = false, forbidden = true),
    ProviderReference("provider reference", futureAllowedAsReferenceOnly = false, currentAccepted = false, forbidden = true),
    CryptoReference("crypto reference", futureAllowedAsReferenceOnly = false, currentAccepted = false, forbidden = true),
    FileLocation("file location", futureAllowedAsReferenceOnly = false, currentAccepted = false, forbidden = true),
    StorageReference("storage reference", futureAllowedAsReferenceOnly = false, currentAccepted = false, forbidden = true),
    BackendReference("backend reference", futureAllowedAsReferenceOnly = false, currentAccepted = false, forbidden = true),
}

enum class SkaldVaultV1TestOnlyProviderKatExecutorOperationClass(
    val label: String,
    val futureTestOnlyAllowedAsCategory: Boolean,
    val currentAuthorized: Boolean,
    val productionForbidden: Boolean,
) {
    PositivePublicNonWalletKat(
        "positive public non-wallet KAT",
        futureTestOnlyAllowedAsCategory = true,
        currentAuthorized = false,
        productionForbidden = false,
    ),
    NegativePublicNonWalletKat(
        "negative public non-wallet KAT",
        futureTestOnlyAllowedAsCategory = true,
        currentAuthorized = false,
        productionForbidden = false,
    ),
    DeterministicSkaldCanonicalVectorKat(
        "deterministic Skald canonical vector KAT",
        futureTestOnlyAllowedAsCategory = true,
        currentAuthorized = false,
        productionForbidden = false,
    ),
    TestOnlyRandomizedAeadBehaviorCheck(
        "test-only randomized AEAD behavior check",
        futureTestOnlyAllowedAsCategory = true,
        currentAuthorized = false,
        productionForbidden = false,
    ),
    TestOnlyProviderSelfTestRoutingCheck(
        "test-only provider self-test routing check",
        futureTestOnlyAllowedAsCategory = true,
        currentAuthorized = false,
        productionForbidden = false,
    ),
    TestOnlyRedactionBehaviorCheck(
        "test-only redaction behavior check",
        futureTestOnlyAllowedAsCategory = true,
        currentAuthorized = false,
        productionForbidden = false,
    ),
    TestOnlyStorageSeparationAssertion(
        "test-only storage-separation assertion",
        futureTestOnlyAllowedAsCategory = true,
        currentAuthorized = false,
        productionForbidden = false,
    ),
    TestOnlyPlatformRuntimeCheck(
        "test-only platform runtime check",
        futureTestOnlyAllowedAsCategory = true,
        currentAuthorized = false,
        productionForbidden = false,
    ),
    ProductionProviderOperation(
        "production provider operation",
        futureTestOnlyAllowedAsCategory = false,
        currentAuthorized = false,
        productionForbidden = true,
    ),
    ProductionKdf("production KDF", futureTestOnlyAllowedAsCategory = false, currentAuthorized = false, productionForbidden = true),
    ProductionAead("production AEAD", futureTestOnlyAllowedAsCategory = false, currentAuthorized = false, productionForbidden = true),
    ProductionHkdf("production HKDF", futureTestOnlyAllowedAsCategory = false, currentAuthorized = false, productionForbidden = true),
    ProductionHmac("production HMAC", futureTestOnlyAllowedAsCategory = false, currentAuthorized = false, productionForbidden = true),
    ProductionKeyGeneration(
        "production key generation",
        futureTestOnlyAllowedAsCategory = false,
        currentAuthorized = false,
        productionForbidden = true,
    ),
    ProductionKeyWrapping(
        "production key wrapping",
        futureTestOnlyAllowedAsCategory = false,
        currentAuthorized = false,
        productionForbidden = true,
    ),
    ProductionKeysetStorage(
        "production keyset storage",
        futureTestOnlyAllowedAsCategory = false,
        currentAuthorized = false,
        productionForbidden = true,
    ),
    VaultCreation("vault creation", futureTestOnlyAllowedAsCategory = false, currentAuthorized = false, productionForbidden = true),
    VaultUnlock("vault unlock", futureTestOnlyAllowedAsCategory = false, currentAuthorized = false, productionForbidden = true),
    VaultPersistence("vault persistence", futureTestOnlyAllowedAsCategory = false, currentAuthorized = false, productionForbidden = true),
    SecureStorageWrite(
        "secure storage write",
        futureTestOnlyAllowedAsCategory = false,
        currentAuthorized = false,
        productionForbidden = true,
    ),
    SecureMetadataWrite(
        "secure metadata write",
        futureTestOnlyAllowedAsCategory = false,
        currentAuthorized = false,
        productionForbidden = true,
    ),
    WalletSync("wallet sync", futureTestOnlyAllowedAsCategory = false, currentAuthorized = false, productionForbidden = true),
    Signing("signing", futureTestOnlyAllowedAsCategory = false, currentAuthorized = false, productionForbidden = true),
    Broadcasting("broadcasting", futureTestOnlyAllowedAsCategory = false, currentAuthorized = false, productionForbidden = true),
    MainnetValidation(
        "mainnet validation",
        futureTestOnlyAllowedAsCategory = false,
        currentAuthorized = false,
        productionForbidden = true,
    ),
}

enum class SkaldVaultV1TestOnlyProviderKatExecutorSourceSetCategory(
    val label: String,
    val futureReviewRequired: Boolean,
    val currentImplementationAuthorized: Boolean,
    val executorImplementationForbidden: Boolean,
) {
    DesktopTestOnlyFutureReviewRequired(
        "desktopTest only, future review required",
        futureReviewRequired = true,
        currentImplementationAuthorized = false,
        executorImplementationForbidden = false,
    ),
    AndroidInstrumentedTestOnlyFutureReviewRequired(
        "androidInstrumentedTest only, future review required",
        futureReviewRequired = true,
        currentImplementationAuthorized = false,
        executorImplementationForbidden = false,
    ),
    CommonTestModelAssertionsOnly(
        "commonTest only for model assertions, not execution",
        futureReviewRequired = true,
        currentImplementationAuthorized = false,
        executorImplementationForbidden = false,
    ),
    CommonMainForbiddenForExecutorImplementation(
        "commonMain forbidden for executor implementation",
        futureReviewRequired = false,
        currentImplementationAuthorized = false,
        executorImplementationForbidden = true,
    ),
    AndroidMainForbiddenForExecutorImplementation(
        "androidMain forbidden for executor implementation",
        futureReviewRequired = false,
        currentImplementationAuthorized = false,
        executorImplementationForbidden = true,
    ),
    DesktopMainForbiddenForExecutorImplementation(
        "desktopMain forbidden for executor implementation",
        futureReviewRequired = false,
        currentImplementationAuthorized = false,
        executorImplementationForbidden = true,
    ),
    BuildScriptsForbiddenExceptExplicitDependencyDeclarationsAndTestTaskWiring(
        "build scripts forbidden except explicit dependency declarations and test task wiring",
        futureReviewRequired = true,
        currentImplementationAuthorized = false,
        executorImplementationForbidden = true,
    ),
    DocsAllowedAsNonAuthorizingEvidence(
        "docs allowed as non-authorizing evidence",
        futureReviewRequired = false,
        currentImplementationAuthorized = false,
        executorImplementationForbidden = false,
    ),
}

enum class SkaldVaultV1TestOnlyProviderKatExecutorResultPolicyRule(
    val label: String,
    val futureResultMayExpose: Boolean,
    val futureResultMustNotExpose: Boolean,
) {
    MayExposePassFailStatus("may expose pass/fail status", futureResultMayExpose = true, futureResultMustNotExpose = false),
    MayExposeVectorId("may expose vector ID", futureResultMayExpose = true, futureResultMustNotExpose = false),
    MayExposeOperationClass("may expose operation class", futureResultMayExpose = true, futureResultMustNotExpose = false),
    MayExposePlatformClass("may expose platform class", futureResultMayExpose = true, futureResultMustNotExpose = false),
    MayExposeRedactedDiagnosticCode(
        "may expose redacted diagnostic code",
        futureResultMayExpose = true,
        futureResultMustNotExpose = false,
    ),
    MustNotExposeRawInput("must not expose raw input", futureResultMayExpose = false, futureResultMustNotExpose = true),
    MustNotExposeRawOutput("must not expose raw output", futureResultMayExpose = false, futureResultMustNotExpose = true),
    MustNotExposeDerivedKeyMaterial(
        "must not expose derived key material",
        futureResultMayExpose = false,
        futureResultMustNotExpose = true,
    ),
    MustNotExposeProviderReferences(
        "must not expose provider references",
        futureResultMayExpose = false,
        futureResultMustNotExpose = true,
    ),
    MustNotExposeCryptoReferences(
        "must not expose crypto references",
        futureResultMayExpose = false,
        futureResultMustNotExpose = true,
    ),
    MustNotExposeFilesystemLocations(
        "must not expose filesystem locations",
        futureResultMayExpose = false,
        futureResultMustNotExpose = true,
    ),
    MustNotExposeWalletMetadata(
        "must not expose wallet metadata",
        futureResultMayExpose = false,
        futureResultMustNotExpose = true,
    ),
    MustNotExposeBackendMetadata(
        "must not expose backend metadata",
        futureResultMayExpose = false,
        futureResultMustNotExpose = true,
    ),
}

enum class SkaldVaultV1TestOnlyProviderKatExecutorAuthorizationLimit(val label: String) {
    ProviderSelection("provider selection"),
    ProductionProviderSelectableTrue("productionProviderSelectable true"),
    VaultCreation("vault creation"),
    VaultUnlock("vault unlock"),
    VaultPersistence("vault persistence"),
    SecureStorageSuccess("secure storage success"),
    SecureMetadataSuccess("secure metadata success"),
    ProductionSync("production sync"),
    Signing("signing"),
    Broadcasting("broadcasting"),
    Mainnet("mainnet"),
}

enum class SkaldVaultV1TestOnlyProviderKatExecutorBlocker(val label: String) {
    ExecutorImplementationDeferred("executor implementation deferred"),
    NoExecutorCallableSurface("no executor callable surface"),
    NoProviderImplementation("no provider implementation"),
    NoProviderFactory("no provider factory"),
    NoProviderDispatcher("no provider dispatcher"),
    NoNonDisabledRegistryEntry("no non-disabled registry entry"),
    ProviderSelectionDisabledProviderOnly("provider selection disabled-provider-only"),
    ProductionProviderSelectableFalse("productionProviderSelectable false"),
    ProviderOperationAuthorizationBlocked("provider operation authorization blocked"),
    RuntimeRandomnessAuthorizationBlocked("runtime randomness authorization blocked"),
    KdfCalibrationNonFinal("KDF calibration non-final"),
    ProviderAcceptanceIncomplete("provider acceptance incomplete"),
    SecureStorageDisabled("secure storage disabled"),
    SecureMetadataDisabled("secure metadata disabled"),
    VaultLifecycleDisabled("vault lifecycle disabled"),
    PersistenceDisabled("persistence disabled"),
    TestOnlyEvidenceNonAuthorizing("test-only evidence non-authorizing"),
    WarningOnlyEvidenceNonAuthorizing("warning-only evidence non-authorizing"),
    UserConsentCannotOverride("user consent cannot override"),
    MainnetDisabled("mainnet disabled"),
}

enum class SkaldVaultV1TestOnlyProviderKatExecutorRedactionClass(val label: String) {
    PolicyIdsOnly("policy IDs only"),
    StatusLabelsOnly("status labels only"),
    RoleLabelsOnly("role labels only"),
    InputClassLabelsOnly("input class labels only"),
    OperationClassLabelsOnly("operation class labels only"),
    SourceSetLabelsOnly("source-set labels only"),
    ResultPolicyLabelsOnly("result-policy labels only"),
    AuthorizationLimitLabelsOnly("authorization-limit labels only"),
    BlockerLabelsOnly("blocker labels only"),
    SafeIdsOnly("safe IDs only"),
    NoRawMaterial("no raw material"),
    NoProviderReferences("no provider references"),
    NoCryptoReferences("no crypto references"),
    NoFilesystemLocations("no filesystem locations"),
    NoStorageReferences("no storage references"),
    NoBackendReferences("no backend references"),
    NoDiagnosticPayloads("no diagnostic payloads"),
}

data class SkaldVaultV1TestOnlyProviderKatExecutorContractCapability(
    val executorContractModeled: Boolean,
    val executorImplemented: Boolean,
    val executorCallable: Boolean,
    val canImplementExecutorNow: Boolean,
    val canRunExecutorNow: Boolean,
    val canUseDesktopTestExecutionNow: Boolean,
    val canUseAndroidInstrumentedTestExecutionNow: Boolean,
    val canUseCommonMainExecution: Boolean,
    val canUseAndroidMainExecution: Boolean,
    val canUseDesktopMainExecution: Boolean,
    val canAcceptRawMaterial: Boolean,
    val canAcceptProviderHandles: Boolean,
    val canAcceptCryptoObjects: Boolean,
    val canExecuteProviderOperations: Boolean,
    val canExecuteRandomness: Boolean,
    val canExecuteKdf: Boolean,
    val canExecuteAead: Boolean,
    val canExecuteHkdf: Boolean,
    val canExecuteHmac: Boolean,
    val canGenerateKeys: Boolean,
    val canStoreKeysets: Boolean,
    val canAuthorizeProviderSelection: Boolean,
    val canSetProductionProviderSelectable: Boolean,
    val canAuthorizeVaultCreation: Boolean,
    val canAuthorizeVaultUnlock: Boolean,
    val canAuthorizeVaultPersistence: Boolean,
    val canAuthorizeProductionSync: Boolean,
    val canAuthorizeMainnet: Boolean,
) {
    companion object {
        val Current = SkaldVaultV1TestOnlyProviderKatExecutorContractCapability(
            executorContractModeled = true,
            executorImplemented = false,
            executorCallable = false,
            canImplementExecutorNow = false,
            canRunExecutorNow = false,
            canUseDesktopTestExecutionNow = false,
            canUseAndroidInstrumentedTestExecutionNow = false,
            canUseCommonMainExecution = false,
            canUseAndroidMainExecution = false,
            canUseDesktopMainExecution = false,
            canAcceptRawMaterial = false,
            canAcceptProviderHandles = false,
            canAcceptCryptoObjects = false,
            canExecuteProviderOperations = false,
            canExecuteRandomness = false,
            canExecuteKdf = false,
            canExecuteAead = false,
            canExecuteHkdf = false,
            canExecuteHmac = false,
            canGenerateKeys = false,
            canStoreKeysets = false,
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

data class SkaldVaultV1TestOnlyProviderKatExecutorRoleRow(
    val role: SkaldVaultV1TestOnlyProviderKatExecutorRole,
    val currentRole: Boolean,
    val futureReviewRequired: Boolean,
    val productionForbidden: Boolean,
    val currentImplementationAuthorized: Boolean,
    val blockers: Set<SkaldVaultV1TestOnlyProviderKatExecutorBlocker>,
)

data class SkaldVaultV1TestOnlyProviderKatExecutorInputClassRow(
    val inputClass: SkaldVaultV1TestOnlyProviderKatExecutorInputClass,
    val futureAllowedAsReferenceOnly: Boolean,
    val currentAccepted: Boolean,
    val forbidden: Boolean,
    val blockers: Set<SkaldVaultV1TestOnlyProviderKatExecutorBlocker>,
    val redactionClass: SkaldVaultV1TestOnlyProviderKatExecutorRedactionClass,
)

data class SkaldVaultV1TestOnlyProviderKatExecutorOperationClassRow(
    val operationClass: SkaldVaultV1TestOnlyProviderKatExecutorOperationClass,
    val futureTestOnlyAllowedAsCategory: Boolean,
    val currentAuthorized: Boolean,
    val productionForbidden: Boolean,
    val blockers: Set<SkaldVaultV1TestOnlyProviderKatExecutorBlocker>,
    val redactionClass: SkaldVaultV1TestOnlyProviderKatExecutorRedactionClass,
)

data class SkaldVaultV1TestOnlyProviderKatExecutorSourceSetRow(
    val sourceSetCategory: SkaldVaultV1TestOnlyProviderKatExecutorSourceSetCategory,
    val futureReviewRequired: Boolean,
    val currentImplementationAuthorized: Boolean,
    val executorImplementationForbidden: Boolean,
    val blockers: Set<SkaldVaultV1TestOnlyProviderKatExecutorBlocker>,
    val redactionClass: SkaldVaultV1TestOnlyProviderKatExecutorRedactionClass,
)

data class SkaldVaultV1TestOnlyProviderKatExecutorResultPolicyRow(
    val rule: SkaldVaultV1TestOnlyProviderKatExecutorResultPolicyRule,
    val futureResultMayExpose: Boolean,
    val futureResultMustNotExpose: Boolean,
    val currentResultSurfaceAvailable: Boolean,
    val canAuthorizeProduction: Boolean,
    val redactionClass: SkaldVaultV1TestOnlyProviderKatExecutorRedactionClass,
)

data class SkaldVaultV1TestOnlyProviderKatExecutorAuthorizationLimitRow(
    val limit: SkaldVaultV1TestOnlyProviderKatExecutorAuthorizationLimit,
    val futureExecutorResultCanAuthorize: Boolean,
    val currentExecutorResultCanAuthorize: Boolean,
    val blockers: Set<SkaldVaultV1TestOnlyProviderKatExecutorBlocker>,
)

data class SkaldVaultV1TestOnlyProviderKatExecutorContractRequest(
    val includeFutureCategories: Boolean,
    val warningOnlyEvidenceClaimed: Boolean,
    val userConsentOverrideRequested: Boolean,
    val releaseEvidenceClaimed: Boolean,
    val safeContractId: String,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderKatExecutorContractRequest(" +
            "includeFutureCategories=$includeFutureCategories, " +
            "warningOnlyEvidenceClaimed=$warningOnlyEvidenceClaimed, " +
            "userConsentOverrideRequested=$userConsentOverrideRequested, " +
            "releaseEvidenceClaimed=$releaseEvidenceClaimed, " +
            "safeContractId=<redacted>, " +
            "rawMaterial=<redacted>, " +
            "providerReference=<redacted>, " +
            "cryptoReference=<redacted>, " +
            "fileLocation=<redacted>, " +
            "storageReference=<redacted>, " +
            "backendReference=<redacted>" +
            ")"

    companion object {
        fun currentContract(
            includeFutureCategories: Boolean = true,
            safeContractId: String = "current-test-only-provider-kat-executor-contract",
        ): SkaldVaultV1TestOnlyProviderKatExecutorContractRequest =
            SkaldVaultV1TestOnlyProviderKatExecutorContractRequest(
                includeFutureCategories = includeFutureCategories,
                warningOnlyEvidenceClaimed = false,
                userConsentOverrideRequested = false,
                releaseEvidenceClaimed = false,
                safeContractId = safeContractId,
            )
    }
}

data class SkaldVaultV1TestOnlyProviderKatExecutorContractEvidence(
    val status: SkaldVaultV1TestOnlyProviderKatExecutorContractStatus,
    val statuses: Set<SkaldVaultV1TestOnlyProviderKatExecutorContractStatus>,
    val currentRoles: Set<SkaldVaultV1TestOnlyProviderKatExecutorRole>,
    val roleRows: List<SkaldVaultV1TestOnlyProviderKatExecutorRoleRow>,
    val inputClassRows: List<SkaldVaultV1TestOnlyProviderKatExecutorInputClassRow>,
    val operationClassRows: List<SkaldVaultV1TestOnlyProviderKatExecutorOperationClassRow>,
    val sourceSetRows: List<SkaldVaultV1TestOnlyProviderKatExecutorSourceSetRow>,
    val resultPolicyRows: List<SkaldVaultV1TestOnlyProviderKatExecutorResultPolicyRow>,
    val authorizationLimitRows: List<SkaldVaultV1TestOnlyProviderKatExecutorAuthorizationLimitRow>,
    val blockers: Set<SkaldVaultV1TestOnlyProviderKatExecutorBlocker>,
    val redactionClasses: Set<SkaldVaultV1TestOnlyProviderKatExecutorRedactionClass>,
    val disabledCapabilities: SkaldVaultV1TestOnlyProviderKatExecutorContractCapability,
    val executorContractModeled: Boolean,
    val executorImplemented: Boolean,
    val executorCallable: Boolean,
    val currentExecutionAuthorized: Boolean,
    val productionExecutorForbidden: Boolean,
    val providerSelectionDisabledProviderOnly: Boolean,
    val productionProviderSelectable: Boolean,
    val vaultLifecycleBlocked: Boolean,
    val persistenceBlocked: Boolean,
    val productionSyncBlocked: Boolean,
    val mainnetBlocked: Boolean,
)

sealed interface SkaldVaultV1TestOnlyProviderKatExecutorContractResult<out T> {
    val value: T

    data class Blocked<out T>(
        override val value: T,
    ) : SkaldVaultV1TestOnlyProviderKatExecutorContractResult<T>
}

data class SkaldVaultV1TestOnlyProviderKatExecutorContractSummary(
    val policyId: String,
    val policyVersion: Int,
    val statuses: Set<SkaldVaultV1TestOnlyProviderKatExecutorContractStatus>,
    val roles: Set<SkaldVaultV1TestOnlyProviderKatExecutorRole>,
    val inputClasses: Set<SkaldVaultV1TestOnlyProviderKatExecutorInputClass>,
    val operationClasses: Set<SkaldVaultV1TestOnlyProviderKatExecutorOperationClass>,
    val sourceSetCategories: Set<SkaldVaultV1TestOnlyProviderKatExecutorSourceSetCategory>,
    val resultPolicyRules: Set<SkaldVaultV1TestOnlyProviderKatExecutorResultPolicyRule>,
    val authorizationLimits: Set<SkaldVaultV1TestOnlyProviderKatExecutorAuthorizationLimit>,
    val blockers: Set<SkaldVaultV1TestOnlyProviderKatExecutorBlocker>,
    val redactionClasses: Set<SkaldVaultV1TestOnlyProviderKatExecutorRedactionClass>,
    val capability: SkaldVaultV1TestOnlyProviderKatExecutorContractCapability,
)

object SkaldVaultV1TestOnlyProviderKatExecutorContractPolicy {
    const val POLICY_ID: String = "skald-vault-v1-test-only-provider-kat-executor-contract-v1"
    const val POLICY_VERSION: Int = 1

    fun evaluateContract(
        request: SkaldVaultV1TestOnlyProviderKatExecutorContractRequest =
            SkaldVaultV1TestOnlyProviderKatExecutorContractRequest.currentContract(),
    ): SkaldVaultV1TestOnlyProviderKatExecutorContractResult.Blocked<
        SkaldVaultV1TestOnlyProviderKatExecutorContractEvidence,
    > =
        SkaldVaultV1TestOnlyProviderKatExecutorContractResult.Blocked(
            currentContractEvidence(request),
        )

    fun currentPolicySummary(): SkaldVaultV1TestOnlyProviderKatExecutorContractSummary =
        SkaldVaultV1TestOnlyProviderKatExecutorContractSummary(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            statuses = SkaldVaultV1TestOnlyProviderKatExecutorContractStatus.entries.toSet(),
            roles = SkaldVaultV1TestOnlyProviderKatExecutorRole.entries.toSet(),
            inputClasses = SkaldVaultV1TestOnlyProviderKatExecutorInputClass.entries.toSet(),
            operationClasses = SkaldVaultV1TestOnlyProviderKatExecutorOperationClass.entries.toSet(),
            sourceSetCategories = SkaldVaultV1TestOnlyProviderKatExecutorSourceSetCategory.entries.toSet(),
            resultPolicyRules = SkaldVaultV1TestOnlyProviderKatExecutorResultPolicyRule.entries.toSet(),
            authorizationLimits = SkaldVaultV1TestOnlyProviderKatExecutorAuthorizationLimit.entries.toSet(),
            blockers = currentBlockers(),
            redactionClasses = SkaldVaultV1TestOnlyProviderKatExecutorRedactionClass.entries.toSet(),
            capability = SkaldVaultV1TestOnlyProviderKatExecutorContractCapability.Current,
        )

    fun currentContractEvidence(
        request: SkaldVaultV1TestOnlyProviderKatExecutorContractRequest =
            SkaldVaultV1TestOnlyProviderKatExecutorContractRequest.currentContract(),
    ): SkaldVaultV1TestOnlyProviderKatExecutorContractEvidence {
        val roleRows = currentRoleRows()
        val inputRows = currentInputClassRows()
        val operationRows = currentOperationClassRows()
        val sourceRows = currentSourceSetRows()
        val limitRows = currentAuthorizationLimitRows()
        val blockers = (
            currentBlockers() +
                roleRows.flatMap { it.blockers } +
                inputRows.flatMap { it.blockers } +
                operationRows.flatMap { it.blockers } +
                sourceRows.flatMap { it.blockers } +
                limitRows.flatMap { it.blockers } +
                requestBlockers(request)
            ).toSet()
        return SkaldVaultV1TestOnlyProviderKatExecutorContractEvidence(
            status = SkaldVaultV1TestOnlyProviderKatExecutorContractStatus.StillDisabled,
            statuses = currentStatuses(),
            currentRoles = setOf(SkaldVaultV1TestOnlyProviderKatExecutorRole.ContractOnlyModel),
            roleRows = roleRows,
            inputClassRows = inputRows,
            operationClassRows = operationRows,
            sourceSetRows = sourceRows,
            resultPolicyRows = currentResultPolicyRows(),
            authorizationLimitRows = limitRows,
            blockers = blockers,
            redactionClasses = SkaldVaultV1TestOnlyProviderKatExecutorRedactionClass.entries.toSet(),
            disabledCapabilities = SkaldVaultV1TestOnlyProviderKatExecutorContractCapability.Current,
            executorContractModeled = true,
            executorImplemented = false,
            executorCallable = false,
            currentExecutionAuthorized = false,
            productionExecutorForbidden = true,
            providerSelectionDisabledProviderOnly = true,
            productionProviderSelectable = false,
            vaultLifecycleBlocked = true,
            persistenceBlocked = true,
            productionSyncBlocked = true,
            mainnetBlocked = true,
        )
    }

    fun currentStatuses(): Set<SkaldVaultV1TestOnlyProviderKatExecutorContractStatus> =
        setOf(
            SkaldVaultV1TestOnlyProviderKatExecutorContractStatus.ContractModeled,
            SkaldVaultV1TestOnlyProviderKatExecutorContractStatus.StillDisabled,
            SkaldVaultV1TestOnlyProviderKatExecutorContractStatus.ExecutorNotImplemented,
            SkaldVaultV1TestOnlyProviderKatExecutorContractStatus.ExecutorSurfaceUnavailable,
            SkaldVaultV1TestOnlyProviderKatExecutorContractStatus.ExecutionNotAuthorized,
            SkaldVaultV1TestOnlyProviderKatExecutorContractStatus.TestOnlyImplementationDeferred,
            SkaldVaultV1TestOnlyProviderKatExecutorContractStatus.ProductionImplementationForbidden,
            SkaldVaultV1TestOnlyProviderKatExecutorContractStatus.ProviderSelectionAuthorizationBlocked,
            SkaldVaultV1TestOnlyProviderKatExecutorContractStatus.ProductionProviderSelectableFalse,
            SkaldVaultV1TestOnlyProviderKatExecutorContractStatus.VaultLifecycleBlocked,
            SkaldVaultV1TestOnlyProviderKatExecutorContractStatus.PersistenceBlocked,
            SkaldVaultV1TestOnlyProviderKatExecutorContractStatus.ProductionSyncBlocked,
            SkaldVaultV1TestOnlyProviderKatExecutorContractStatus.MainnetBlocked,
        )

    fun currentBlockers(): Set<SkaldVaultV1TestOnlyProviderKatExecutorBlocker> =
        setOf(
            SkaldVaultV1TestOnlyProviderKatExecutorBlocker.ExecutorImplementationDeferred,
            SkaldVaultV1TestOnlyProviderKatExecutorBlocker.NoExecutorCallableSurface,
            SkaldVaultV1TestOnlyProviderKatExecutorBlocker.NoProviderImplementation,
            SkaldVaultV1TestOnlyProviderKatExecutorBlocker.NoProviderFactory,
            SkaldVaultV1TestOnlyProviderKatExecutorBlocker.NoProviderDispatcher,
            SkaldVaultV1TestOnlyProviderKatExecutorBlocker.NoNonDisabledRegistryEntry,
            SkaldVaultV1TestOnlyProviderKatExecutorBlocker.ProviderSelectionDisabledProviderOnly,
            SkaldVaultV1TestOnlyProviderKatExecutorBlocker.ProductionProviderSelectableFalse,
            SkaldVaultV1TestOnlyProviderKatExecutorBlocker.ProviderOperationAuthorizationBlocked,
            SkaldVaultV1TestOnlyProviderKatExecutorBlocker.RuntimeRandomnessAuthorizationBlocked,
            SkaldVaultV1TestOnlyProviderKatExecutorBlocker.KdfCalibrationNonFinal,
            SkaldVaultV1TestOnlyProviderKatExecutorBlocker.ProviderAcceptanceIncomplete,
            SkaldVaultV1TestOnlyProviderKatExecutorBlocker.SecureStorageDisabled,
            SkaldVaultV1TestOnlyProviderKatExecutorBlocker.SecureMetadataDisabled,
            SkaldVaultV1TestOnlyProviderKatExecutorBlocker.VaultLifecycleDisabled,
            SkaldVaultV1TestOnlyProviderKatExecutorBlocker.PersistenceDisabled,
            SkaldVaultV1TestOnlyProviderKatExecutorBlocker.TestOnlyEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderKatExecutorBlocker.WarningOnlyEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderKatExecutorBlocker.UserConsentCannotOverride,
            SkaldVaultV1TestOnlyProviderKatExecutorBlocker.MainnetDisabled,
        )

    fun requestBlockers(
        request: SkaldVaultV1TestOnlyProviderKatExecutorContractRequest,
    ): Set<SkaldVaultV1TestOnlyProviderKatExecutorBlocker> =
        buildSet {
            if (request.warningOnlyEvidenceClaimed) {
                add(SkaldVaultV1TestOnlyProviderKatExecutorBlocker.WarningOnlyEvidenceNonAuthorizing)
            }
            if (request.userConsentOverrideRequested) {
                add(SkaldVaultV1TestOnlyProviderKatExecutorBlocker.UserConsentCannotOverride)
            }
            if (request.releaseEvidenceClaimed) {
                add(SkaldVaultV1TestOnlyProviderKatExecutorBlocker.MainnetDisabled)
            }
        }

    fun currentRoleRows(): List<SkaldVaultV1TestOnlyProviderKatExecutorRoleRow> =
        SkaldVaultV1TestOnlyProviderKatExecutorRole.entries.map { role ->
            SkaldVaultV1TestOnlyProviderKatExecutorRoleRow(
                role = role,
                currentRole = role.currentRole,
                futureReviewRequired = role.futureReviewRequired,
                productionForbidden = role.productionForbidden,
                currentImplementationAuthorized = false,
                blockers = if (role.currentRole) {
                    setOf(SkaldVaultV1TestOnlyProviderKatExecutorBlocker.NoExecutorCallableSurface)
                } else if (role.productionForbidden) {
                    setOf(
                        SkaldVaultV1TestOnlyProviderKatExecutorBlocker.ProviderSelectionDisabledProviderOnly,
                        SkaldVaultV1TestOnlyProviderKatExecutorBlocker.ProductionProviderSelectableFalse,
                        SkaldVaultV1TestOnlyProviderKatExecutorBlocker.MainnetDisabled,
                    )
                } else {
                    setOf(
                        SkaldVaultV1TestOnlyProviderKatExecutorBlocker.ExecutorImplementationDeferred,
                        SkaldVaultV1TestOnlyProviderKatExecutorBlocker.TestOnlyEvidenceNonAuthorizing,
                    )
                },
            )
        }

    fun currentInputClassRows(): List<SkaldVaultV1TestOnlyProviderKatExecutorInputClassRow> =
        SkaldVaultV1TestOnlyProviderKatExecutorInputClass.entries.map { inputClass ->
            SkaldVaultV1TestOnlyProviderKatExecutorInputClassRow(
                inputClass = inputClass,
                futureAllowedAsReferenceOnly = inputClass.futureAllowedAsReferenceOnly,
                currentAccepted = false,
                forbidden = inputClass.forbidden,
                blockers = if (inputClass.futureAllowedAsReferenceOnly) {
                    setOf(
                        SkaldVaultV1TestOnlyProviderKatExecutorBlocker.ExecutorImplementationDeferred,
                        SkaldVaultV1TestOnlyProviderKatExecutorBlocker.NoExecutorCallableSurface,
                        SkaldVaultV1TestOnlyProviderKatExecutorBlocker.TestOnlyEvidenceNonAuthorizing,
                    )
                } else {
                    setOf(
                        SkaldVaultV1TestOnlyProviderKatExecutorBlocker.NoExecutorCallableSurface,
                        SkaldVaultV1TestOnlyProviderKatExecutorBlocker.ProviderOperationAuthorizationBlocked,
                        SkaldVaultV1TestOnlyProviderKatExecutorBlocker.PersistenceDisabled,
                    )
                },
                redactionClass = SkaldVaultV1TestOnlyProviderKatExecutorRedactionClass.InputClassLabelsOnly,
            )
        }

    fun currentOperationClassRows(): List<SkaldVaultV1TestOnlyProviderKatExecutorOperationClassRow> =
        SkaldVaultV1TestOnlyProviderKatExecutorOperationClass.entries.map { operationClass ->
            SkaldVaultV1TestOnlyProviderKatExecutorOperationClassRow(
                operationClass = operationClass,
                futureTestOnlyAllowedAsCategory = operationClass.futureTestOnlyAllowedAsCategory,
                currentAuthorized = false,
                productionForbidden = operationClass.productionForbidden,
                blockers = if (operationClass.futureTestOnlyAllowedAsCategory) {
                    setOf(
                        SkaldVaultV1TestOnlyProviderKatExecutorBlocker.ExecutorImplementationDeferred,
                        SkaldVaultV1TestOnlyProviderKatExecutorBlocker.NoExecutorCallableSurface,
                        SkaldVaultV1TestOnlyProviderKatExecutorBlocker.TestOnlyEvidenceNonAuthorizing,
                    )
                } else {
                    setOf(
                        SkaldVaultV1TestOnlyProviderKatExecutorBlocker.ProviderOperationAuthorizationBlocked,
                        SkaldVaultV1TestOnlyProviderKatExecutorBlocker.ProviderSelectionDisabledProviderOnly,
                        SkaldVaultV1TestOnlyProviderKatExecutorBlocker.ProductionProviderSelectableFalse,
                    )
                },
                redactionClass = SkaldVaultV1TestOnlyProviderKatExecutorRedactionClass.OperationClassLabelsOnly,
            )
        }

    fun currentSourceSetRows(): List<SkaldVaultV1TestOnlyProviderKatExecutorSourceSetRow> =
        SkaldVaultV1TestOnlyProviderKatExecutorSourceSetCategory.entries.map { sourceSetCategory ->
            SkaldVaultV1TestOnlyProviderKatExecutorSourceSetRow(
                sourceSetCategory = sourceSetCategory,
                futureReviewRequired = sourceSetCategory.futureReviewRequired,
                currentImplementationAuthorized = false,
                executorImplementationForbidden = sourceSetCategory.executorImplementationForbidden,
                blockers = if (sourceSetCategory.executorImplementationForbidden) {
                    setOf(
                        SkaldVaultV1TestOnlyProviderKatExecutorBlocker.ExecutorImplementationDeferred,
                        SkaldVaultV1TestOnlyProviderKatExecutorBlocker.ProviderSelectionDisabledProviderOnly,
                        SkaldVaultV1TestOnlyProviderKatExecutorBlocker.ProductionProviderSelectableFalse,
                    )
                } else {
                    setOf(
                        SkaldVaultV1TestOnlyProviderKatExecutorBlocker.ExecutorImplementationDeferred,
                        SkaldVaultV1TestOnlyProviderKatExecutorBlocker.NoExecutorCallableSurface,
                    )
                },
                redactionClass = SkaldVaultV1TestOnlyProviderKatExecutorRedactionClass.SourceSetLabelsOnly,
            )
        }

    fun currentResultPolicyRows(): List<SkaldVaultV1TestOnlyProviderKatExecutorResultPolicyRow> =
        SkaldVaultV1TestOnlyProviderKatExecutorResultPolicyRule.entries.map { rule ->
            SkaldVaultV1TestOnlyProviderKatExecutorResultPolicyRow(
                rule = rule,
                futureResultMayExpose = rule.futureResultMayExpose,
                futureResultMustNotExpose = rule.futureResultMustNotExpose,
                currentResultSurfaceAvailable = false,
                canAuthorizeProduction = false,
                redactionClass = SkaldVaultV1TestOnlyProviderKatExecutorRedactionClass.ResultPolicyLabelsOnly,
            )
        }

    fun currentAuthorizationLimitRows(): List<SkaldVaultV1TestOnlyProviderKatExecutorAuthorizationLimitRow> =
        SkaldVaultV1TestOnlyProviderKatExecutorAuthorizationLimit.entries.map { limit ->
            SkaldVaultV1TestOnlyProviderKatExecutorAuthorizationLimitRow(
                limit = limit,
                futureExecutorResultCanAuthorize = false,
                currentExecutorResultCanAuthorize = false,
                blockers = setOf(
                    SkaldVaultV1TestOnlyProviderKatExecutorBlocker.NoExecutorCallableSurface,
                    SkaldVaultV1TestOnlyProviderKatExecutorBlocker.TestOnlyEvidenceNonAuthorizing,
                    SkaldVaultV1TestOnlyProviderKatExecutorBlocker.ProviderSelectionDisabledProviderOnly,
                    SkaldVaultV1TestOnlyProviderKatExecutorBlocker.ProductionProviderSelectableFalse,
                ),
            )
        }
}
