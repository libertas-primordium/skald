package com.libertasprimordium.skald.security

data class SkaldVaultV1TestOnlyProviderSyntheticIdentitySafeId(
    val value: String,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderSyntheticIdentitySafeId(redacted)"
}

data class SkaldVaultV1TestOnlyProviderSyntheticIdentitySafeLabel(
    val value: String,
) {
    init {
        require(value.isNotBlank()) { "synthetic identity label must not be blank" }
    }

    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderSyntheticIdentitySafeLabel(redacted)"
}

enum class SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceStatus(val label: String) {
    NamespaceContractModeled("namespace contract modeled"),
    StillDisabled("still disabled"),
    LabelSyntaxPolicyModeled("label syntax policy modeled"),
    SyntheticPrefixModeled("synthetic prefix modeled"),
    FutureIdentityFamiliesModeled("future identity families modeled"),
    NoTestOnlyProviderIdentityImplemented("no test-only provider identity implemented"),
    NoProductionProviderIdentityImplemented("no production provider identity implemented"),
    NoInstantiableIdentity("no instantiable identity"),
    NoRegistryKey("no registry key"),
    NoFactoryInput("no factory input"),
    NoDispatcherInput("no dispatcher input"),
    NoExecutorTarget("no executor target"),
    NoEndpointAlias("no endpoint alias"),
    NoStoragePathAlias("no storage path alias"),
    ProviderSelectionAuthorizationBlocked("provider selection authorization blocked"),
    ProductionProviderSelectableFalse("productionProviderSelectable false"),
    VaultLifecycleBlocked("vault lifecycle blocked"),
    PersistenceBlocked("persistence blocked"),
    ProductionSyncBlocked("production sync blocked"),
    MainnetBlocked("mainnet blocked"),
    LabelSyntaxEvaluated("label syntax evaluated"),
    CandidateLabelAcceptedForFutureReview("candidate label accepted for future review"),
    CandidateLabelRejected("candidate label rejected"),
}

enum class SkaldVaultV1TestOnlyProviderSyntheticIdentityFamily(val token: String, val label: String) {
    DeterministicKat("deterministic-kat", "deterministic KAT"),
    RandomizedBehaviorKat("randomized-behavior-kat", "randomized behavior KAT"),
    PlatformRuntimeKat("platform-runtime-kat", "platform runtime KAT"),
}

enum class SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceRule(val label: String) {
    NonBlank("candidate safe ID must be non-blank"),
    LowercaseAsciiOnly("candidate safe ID must be lowercase ASCII only"),
    AllowedCharactersOnly("candidate safe ID may use only a-z, 0-9, and hyphen"),
    ExactPrefixRequired("candidate safe ID must start with exact synthetic prefix"),
    ExactlyOneFamilyTokenRequired("candidate safe ID must include exactly one allowed family token"),
    AdditionalPurposeTokenRequired("candidate safe ID must include at least one additional safe purpose token"),
    BoundedLength("candidate safe ID length must be bounded"),
    NoLeadingOrTrailingHyphen("candidate safe ID must have no leading or trailing hyphen"),
    NoDoubledHyphen("candidate safe ID must have no doubled hyphen"),
    NoPathSeparators("candidate safe ID must have no path separators"),
    NoDots("candidate safe ID must have no dots"),
    NoColons("candidate safe ID must have no colons"),
    NoAtSigns("candidate safe ID must have no at-signs"),
    NoUrlLikeStructure("candidate safe ID must have no URL-like structure"),
    NoEndpointLikeStructure("candidate safe ID must have no endpoint-like structure"),
    NoWalletAddressLikeStructure("candidate safe ID must have no wallet-address-like structure"),
    NoHex64Token("candidate safe ID must have no 64-hex-looking token"),
    NoDependencyCandidateAlias("candidate safe ID must have no dependency-candidate alias"),
    NoProductionProviderAlias("candidate safe ID must have no production provider alias"),
    NoPromotionAlias("candidate safe ID must have no mainnet, release, or promotion alias"),
    NoForbiddenMaterialToken("candidate safe ID must have no forbidden material token"),
    LabelOnlyNonAuthorizing("candidate safe ID is label-only and non-authorizing"),
    NoIdentityImplementationFromLabel("candidate safe ID never implements a provider identity"),
}

enum class SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker(val label: String) {
    NamespaceContractModelOnly("namespace contract model-only"),
    NoTestOnlyProviderIdentityImplemented("no test-only provider identity implemented"),
    NoProductionProviderIdentityImplemented("no production provider identity implemented"),
    NoProviderImplementation("no provider implementation"),
    NoProviderFactory("no provider factory"),
    NoProviderDispatcher("no provider dispatcher"),
    NoNonDisabledRegistryEntry("no non-disabled registry entry"),
    NoExecutorTarget("no executor target"),
    ProviderSelectionDisabledProviderOnly("provider selection disabled-provider-only"),
    ProductionProviderSelectableFalse("productionProviderSelectable false"),
    ProviderOperationAuthorizationBlocked("provider operation authorization blocked"),
    RuntimeRandomnessAuthorizationBlocked("runtime randomness authorization blocked"),
    KdfCalibrationNonFinal("KDF calibration non-final"),
    SecureStorageDisabled("secure storage disabled"),
    SecureMetadataDisabled("secure metadata disabled"),
    VaultLifecycleDisabled("vault lifecycle disabled"),
    PersistenceDisabled("persistence disabled"),
    ProductionSyncDisabled("production sync disabled"),
    SigningBroadcastingDisabled("signing and broadcasting disabled"),
    PublicEndpointDefaultsDisabled("public endpoint defaults disabled"),
    UserConsentCannotOverride("user consent cannot override"),
    WarningOnlyEvidenceNonAuthorizing("warning-only evidence non-authorizing"),
    TestOnlyEvidenceNonAuthorizing("test-only evidence non-authorizing"),
    RegistryAliasRejected("registry alias rejected"),
    FactoryAliasRejected("factory alias rejected"),
    DispatcherAliasRejected("dispatcher alias rejected"),
    ExecutorTargetAliasRejected("executor target alias rejected"),
    DependencyCandidateAliasRejected("dependency candidate alias rejected"),
    EndpointAliasRejected("endpoint alias rejected"),
    StoragePathAliasRejected("storage path alias rejected"),
    MainnetAliasRejected("mainnet alias rejected"),
    LabelSyntaxRejected("label syntax rejected"),
    LabelAcceptedForFutureReviewOnly("label accepted for future review only"),
    MainnetDisabled("mainnet disabled"),
}

enum class SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason(val label: String) {
    Blank("blank input"),
    TooLong("too long"),
    UppercaseOrNonLowercaseAscii("uppercase or non-lowercase ASCII"),
    IllegalCharacter("illegal character"),
    MissingPrefix("missing required prefix"),
    MissingFamilyToken("missing allowed family token"),
    MultipleFamilyTokens("multiple family tokens"),
    MissingPurposeToken("missing safe purpose token"),
    LeadingOrTrailingHyphen("leading or trailing hyphen"),
    DoubledHyphen("doubled hyphen"),
    PathSeparator("path separator"),
    Dot("dot"),
    Colon("colon"),
    AtSign("at-sign"),
    UrlLikeStructure("URL-like structure"),
    EndpointLikeStructure("endpoint-like structure"),
    WalletAddressLikeStructure("wallet-address-like structure"),
    Hex64Token("64-hex-looking token"),
    DependencyCandidateAlias("dependency-candidate alias"),
    ProductionProviderAlias("production provider alias"),
    ProductionPromotionAlias("production, release, mainnet, signing, or broadcasting alias"),
    ForbiddenMaterialToken("forbidden material token"),
    ForbiddenLinkageToken("forbidden linkage token"),
}

enum class SkaldVaultV1TestOnlyProviderSyntheticIdentityForbiddenMaterialClass(val label: String) {
    RawPassphrase("raw passphrase"),
    SeedPhrase("seed phrase"),
    MnemonicPhrase("mnemonic phrase"),
    XprvToken("xprv token"),
    XpubToken("xpub token"),
    NsecToken("nsec token"),
    PrivateKey("private key"),
    Psbt("PSBT"),
    TransactionMaterial("transaction material"),
    SaltMaterial("salt material"),
    NonceMaterial("nonce material"),
    PlaintextMaterial("plaintext material"),
    CiphertextMaterial("ciphertext material"),
    AeadTag("AEAD tag"),
    KeysetMaterial("keyset material"),
    ProviderHandle("provider handle"),
    CryptoObject("crypto object"),
    WalletDescriptor("wallet descriptor"),
    StoragePath("storage path"),
    FileLocation("file location"),
    BackendCredential("backend credential"),
    NetworkEndpoint("network endpoint"),
    WalletAddress("wallet address"),
    WalletLabel("wallet label"),
    UtxoLabel("UTXO label"),
    BackendObservationState("backend observation state"),
}

enum class SkaldVaultV1TestOnlyProviderSyntheticIdentityForbiddenAlias(val label: String) {
    Tink("Tink"),
    BouncyCastle("Bouncy Castle"),
    Lazysodium("Lazysodium"),
    IonSpin("IonSpin"),
    Bdk("BDK"),
    Electrum("Electrum"),
    Esplora("Esplora"),
    ProductionProvider("production provider"),
    ReleaseProvider("release provider"),
    MainnetProvider("mainnet provider"),
    RegistryKey("registry key"),
    FactoryInput("factory input"),
    DispatcherInput("dispatcher input"),
    ExecutorTarget("executor target"),
    EndpointAlias("endpoint alias"),
    StoragePathAlias("storage path alias"),
    DependencyCandidateAlias("dependency candidate alias"),
}

enum class SkaldVaultV1TestOnlyProviderSyntheticIdentityForbiddenLinkage(val label: String) {
    ProviderSelection("provider selection"),
    ProviderRegistry("provider registry"),
    ProviderFactory("provider factory"),
    ProviderDispatcher("provider dispatcher"),
    ProviderKatExecutor("provider KAT executor"),
    ExecutorTargetCatalog("executor target catalog"),
    VaultCreation("vault creation"),
    VaultUnlock("vault unlock"),
    VaultSession("vault session"),
    VaultPersistence("vault persistence"),
    SecureStorage("secure storage"),
    SecureMetadataStorage("secure metadata storage"),
    StorageNamespace("storage namespace"),
    StoragePath("storage path"),
    ProductionSync("production sync"),
    BackendClient("backend client"),
    BdkWalletState("BDK wallet state"),
    SettingsCodec("settings codec"),
    UiSurface("UI surface"),
    Signing("signing"),
    Broadcasting("broadcasting"),
    TorTransport("Tor transport"),
    NostrParsing("Nostr parsing"),
    PublicEndpointDefault("public endpoint default"),
    Mainnet("mainnet"),
}

enum class SkaldVaultV1TestOnlyProviderSyntheticIdentityEvidenceSource(val label: String) {
    TestOnlyProviderIdentityDecision("test-only provider identity decision"),
    TestOnlyProviderIdentityIsolationGuard("test-only provider identity isolation guard"),
    ProviderSelectionBoundary("provider selection boundary"),
    ProviderRegistryIsolationGuard("provider registry isolation guard"),
    ProviderFactoryIsolationBoundary("provider factory isolation boundary"),
    ProviderOperationDispatchIsolation("provider operation dispatch isolation"),
    TestOnlyKatSourceSetConfinement("test-only KAT source-set confinement"),
    TestOnlyKatExecutorReadinessGate("test-only KAT executor readiness gate"),
    TestOnlyKatExecutorContract("test-only KAT executor contract"),
    TestOnlyKatVectorCatalog("test-only KAT vector catalog"),
    ProductionProviderAcceptanceContract("production provider acceptance contract"),
    ReadmeSecurityStatus("README security status"),
}

enum class SkaldVaultV1TestOnlyProviderSyntheticIdentityRedactionClass(val label: String) {
    PolicyIdsOnly("policy IDs only"),
    SafeIdSyntaxOnly("safe ID syntax only"),
    SafeLabelsOnly("safe labels only"),
    RejectionReasonLabelsOnly("rejection reason labels only"),
    BlockerLabelsOnly("blocker labels only"),
    NoRawInput("no raw input"),
    NoMaterialValues("no material values"),
    NoProviderRuntimeReference("no provider runtime reference"),
    NoCryptoRuntimeReference("no crypto runtime reference"),
    NoStorageLocation("no storage location"),
    NoNetworkEndpoint("no network endpoint"),
    NoWalletDescriptor("no wallet descriptor"),
    NoBackendState("no backend state"),
}

enum class SkaldVaultV1TestOnlyProviderSyntheticIdentityFutureReviewRequirement(val label: String) {
    ExplicitNamespaceApproval("explicit namespace approval"),
    SourceSetConfinementReview("source-set confinement review"),
    SafeProviderIdReview("safe provider ID review"),
    RegistryExclusionProof("registry exclusion proof"),
    FactoryExclusionProof("factory exclusion proof"),
    DispatcherExclusionProof("dispatcher exclusion proof"),
    ExecutorTargetExclusionProof("executor target exclusion proof"),
    ProviderSelectionNonAuthorization("provider selection non-authorization"),
    StorageNonAuthorization("storage non-authorization"),
    WalletMaterialRejection("wallet material rejection"),
    EndpointAliasRejection("endpoint alias rejection"),
    MainnetNonAuthorization("mainnet non-authorization"),
}

data class SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceRuleRow(
    val rule: SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceRule,
    val represented: Boolean,
    val satisfiedForModelOnlyBoundary: Boolean,
    val authorizesCapability: Boolean,
)

data class SkaldVaultV1TestOnlyProviderSyntheticIdentityForbiddenMaterialRow(
    val materialClass: SkaldVaultV1TestOnlyProviderSyntheticIdentityForbiddenMaterialClass,
    val forbidden: Boolean,
    val acceptedNow: Boolean,
    val canAuthorize: Boolean,
)

data class SkaldVaultV1TestOnlyProviderSyntheticIdentityForbiddenAliasRow(
    val alias: SkaldVaultV1TestOnlyProviderSyntheticIdentityForbiddenAlias,
    val forbidden: Boolean,
    val currentAliasPresent: Boolean,
    val canAuthorize: Boolean,
)

data class SkaldVaultV1TestOnlyProviderSyntheticIdentityForbiddenLinkageRow(
    val linkage: SkaldVaultV1TestOnlyProviderSyntheticIdentityForbiddenLinkage,
    val forbidden: Boolean,
    val currentLinkPresent: Boolean,
    val canAuthorize: Boolean,
)

data class SkaldVaultV1TestOnlyProviderSyntheticIdentityFutureReviewRequirementRow(
    val requirement: SkaldVaultV1TestOnlyProviderSyntheticIdentityFutureReviewRequirement,
    val futureRequired: Boolean,
    val satisfiedNow: Boolean,
    val authorizesCurrentImplementation: Boolean,
)

data class SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceCapability(
    val canImplementProviderNow: Boolean,
    val canInstantiateProviderNow: Boolean,
    val canRegisterProviderNow: Boolean,
    val canUseAsProviderSelectionId: Boolean,
    val canUseAsRegistryKey: Boolean,
    val canUseAsFactoryInput: Boolean,
    val canUseAsDispatcherInput: Boolean,
    val canUseAsExecutorTarget: Boolean,
    val canUseForProviderKatExecutor: Boolean,
    val canExecuteProviderOperations: Boolean,
    val canExecuteRandomness: Boolean,
    val canExecuteKdf: Boolean,
    val canExecuteHkdf: Boolean,
    val canExecuteHmac: Boolean,
    val canExecuteAead: Boolean,
    val canGenerateKeys: Boolean,
    val canStoreKeysets: Boolean,
    val canUseForVaultCreation: Boolean,
    val canUseForVaultUnlock: Boolean,
    val canUseForVaultSession: Boolean,
    val canUseForVaultPersistence: Boolean,
    val canUseForSecureStorage: Boolean,
    val canUseForSecureMetadataStorage: Boolean,
    val canUseForStorageNamespace: Boolean,
    val canUseForStoragePath: Boolean,
    val canUseForProductionSync: Boolean,
    val canUseForBackendClient: Boolean,
    val canUseForBdkWalletState: Boolean,
    val canUseForSettingsCodec: Boolean,
    val canUseForUiSurface: Boolean,
    val canUseForSigning: Boolean,
    val canUseForBroadcasting: Boolean,
    val canUseForTorTransport: Boolean,
    val canUseForNostrParsing: Boolean,
    val canUseForPublicEndpointDefault: Boolean,
    val canUseForMainnet: Boolean,
    val productionProviderSelectable: Boolean,
) {
    companion object {
        val Current = SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceCapability(
            canImplementProviderNow = false,
            canInstantiateProviderNow = false,
            canRegisterProviderNow = false,
            canUseAsProviderSelectionId = false,
            canUseAsRegistryKey = false,
            canUseAsFactoryInput = false,
            canUseAsDispatcherInput = false,
            canUseAsExecutorTarget = false,
            canUseForProviderKatExecutor = false,
            canExecuteProviderOperations = false,
            canExecuteRandomness = false,
            canExecuteKdf = false,
            canExecuteHkdf = false,
            canExecuteHmac = false,
            canExecuteAead = false,
            canGenerateKeys = false,
            canStoreKeysets = false,
            canUseForVaultCreation = false,
            canUseForVaultUnlock = false,
            canUseForVaultSession = false,
            canUseForVaultPersistence = false,
            canUseForSecureStorage = false,
            canUseForSecureMetadataStorage = false,
            canUseForStorageNamespace = false,
            canUseForStoragePath = false,
            canUseForProductionSync = false,
            canUseForBackendClient = false,
            canUseForBdkWalletState = false,
            canUseForSettingsCodec = false,
            canUseForUiSurface = false,
            canUseForSigning = false,
            canUseForBroadcasting = false,
            canUseForTorTransport = false,
            canUseForNostrParsing = false,
            canUseForPublicEndpointDefault = false,
            canUseForMainnet = false,
            productionProviderSelectable = false,
        )
    }
}

data class SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceRequest(
    val candidateSafeId: SkaldVaultV1TestOnlyProviderSyntheticIdentitySafeId? = null,
    val includePriorIdentityDecisionEvidence: Boolean = true,
    val includePriorIsolationEvidence: Boolean = true,
    val userConsentOverrideRequested: Boolean = false,
    val warningOnlyEvidenceClaimed: Boolean = false,
    val testOnlyEvidenceClaimedAsProductionPromotion: Boolean = false,
    val registryAliasClaimed: Boolean = false,
    val factoryAliasClaimed: Boolean = false,
    val dispatcherAliasClaimed: Boolean = false,
    val executorTargetAliasClaimed: Boolean = false,
    val dependencyCandidateAliasClaimed: Boolean = false,
    val endpointAliasClaimed: Boolean = false,
    val storagePathAliasClaimed: Boolean = false,
    val mainnetAliasClaimed: Boolean = false,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceRequest(" +
            "candidateSafeId=redacted, " +
            "priorEvidenceFlags=modeled, " +
            "falsePositiveClaims=modeled" +
            ")"
}

data class SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceEvidence(
    val policyId: String,
    val policyVersion: Int,
    val allowedPrefix: String,
    val allowedFamilyTokens: Set<String>,
    val statuses: Set<SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceStatus>,
    val ruleRows: List<SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceRuleRow>,
    val forbiddenMaterialRows: List<SkaldVaultV1TestOnlyProviderSyntheticIdentityForbiddenMaterialRow>,
    val forbiddenAliasRows: List<SkaldVaultV1TestOnlyProviderSyntheticIdentityForbiddenAliasRow>,
    val forbiddenLinkageRows: List<SkaldVaultV1TestOnlyProviderSyntheticIdentityForbiddenLinkageRow>,
    val futureReviewRequirementRows:
        List<SkaldVaultV1TestOnlyProviderSyntheticIdentityFutureReviewRequirementRow>,
    val evidenceSources: Set<SkaldVaultV1TestOnlyProviderSyntheticIdentityEvidenceSource>,
    val blockers: Set<SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker>,
    val rejectionReasons: Set<SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason>,
    val redactionClasses: Set<SkaldVaultV1TestOnlyProviderSyntheticIdentityRedactionClass>,
    val disabledCapabilities: SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceCapability,
    val namespaceContractModeled: Boolean,
    val stillDisabled: Boolean,
    val labelSyntaxEvaluated: Boolean,
    val candidateLabelAcceptedForFutureReview: Boolean,
    val candidateLabelRejected: Boolean,
    val candidateFamily: SkaldVaultV1TestOnlyProviderSyntheticIdentityFamily?,
    val candidateSafeLabel: SkaldVaultV1TestOnlyProviderSyntheticIdentitySafeLabel,
    val priorIdentityDecisionEvidenceIncluded: Boolean,
    val priorIsolationEvidenceIncluded: Boolean,
    val priorIdentityDecisionEvidenceNonAuthorizing: Boolean,
    val priorIsolationEvidenceNonAuthorizing: Boolean,
    val testOnlyProviderIdentityImplemented: Boolean,
    val productionProviderIdentityImplemented: Boolean,
    val providerSelectionDisabledProviderOnly: Boolean,
    val productionProviderSelectable: Boolean,
    val allEvidenceNonAuthorizing: Boolean,
) {
    val modeledButStillDisabled: Boolean =
        namespaceContractModeled &&
            stillDisabled &&
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceStatus.NamespaceContractModeled in statuses &&
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceStatus.StillDisabled in statuses
}

object SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespacePolicy {
    const val POLICY_ID: String = "skald-vault-v1-test-only-provider-synthetic-identity-namespace-v1"
    const val POLICY_VERSION: Int = 1
    const val ALLOWED_NAMESPACE_PREFIX: String = "skald-test-only-provider-identity-v1-"
    const val MAX_SAFE_ID_LENGTH: Int = 120

    fun evaluateNamespace(
        request: SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceRequest =
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceRequest(),
    ): SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceEvidence {
        val candidateSafeId = request.candidateSafeId
        val rejectionReasons = if (candidateSafeId == null) {
            emptySet()
        } else {
            validateCandidate(candidateSafeId)
        }
        val labelSyntaxEvaluated = candidateSafeId != null
        val candidateLabelAcceptedForFutureReview = labelSyntaxEvaluated && rejectionReasons.isEmpty()
        val candidateLabelRejected = labelSyntaxEvaluated && rejectionReasons.isNotEmpty()
        val requestBlockers = requestBlockers(request)
        val labelBlockers = buildSet {
            if (candidateLabelRejected) {
                add(SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.LabelSyntaxRejected)
            }
            if (candidateLabelAcceptedForFutureReview) {
                add(
                    SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker
                        .LabelAcceptedForFutureReviewOnly,
                )
            }
        }

        return SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceEvidence(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            allowedPrefix = ALLOWED_NAMESPACE_PREFIX,
            allowedFamilyTokens = SkaldVaultV1TestOnlyProviderSyntheticIdentityFamily.entries
                .map { it.token }
                .toSet(),
            statuses = currentStatuses(
                labelSyntaxEvaluated = labelSyntaxEvaluated,
                candidateLabelAcceptedForFutureReview = candidateLabelAcceptedForFutureReview,
                candidateLabelRejected = candidateLabelRejected,
            ),
            ruleRows = currentRuleRows(),
            forbiddenMaterialRows = currentForbiddenMaterialRows(),
            forbiddenAliasRows = currentForbiddenAliasRows(),
            forbiddenLinkageRows = currentForbiddenLinkageRows(),
            futureReviewRequirementRows = currentFutureReviewRequirementRows(),
            evidenceSources = currentEvidenceSources(request),
            blockers = currentBlockers() + requestBlockers + labelBlockers,
            rejectionReasons = rejectionReasons,
            redactionClasses = currentRedactionClasses(),
            disabledCapabilities = SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceCapability.Current,
            namespaceContractModeled = true,
            stillDisabled = true,
            labelSyntaxEvaluated = labelSyntaxEvaluated,
            candidateLabelAcceptedForFutureReview = candidateLabelAcceptedForFutureReview,
            candidateLabelRejected = candidateLabelRejected,
            candidateFamily = candidateSafeId?.let { candidateFamily(it) },
            candidateSafeLabel = SkaldVaultV1TestOnlyProviderSyntheticIdentitySafeLabel(
                if (labelSyntaxEvaluated) {
                    "candidate label redacted"
                } else {
                    "candidate label absent"
                },
            ),
            priorIdentityDecisionEvidenceIncluded = request.includePriorIdentityDecisionEvidence,
            priorIsolationEvidenceIncluded = request.includePriorIsolationEvidence,
            priorIdentityDecisionEvidenceNonAuthorizing = true,
            priorIsolationEvidenceNonAuthorizing = true,
            testOnlyProviderIdentityImplemented = false,
            productionProviderIdentityImplemented = false,
            providerSelectionDisabledProviderOnly = true,
            productionProviderSelectable = false,
            allEvidenceNonAuthorizing = true,
        )
    }

    fun currentNamespaceEvidence(): SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceEvidence =
        evaluateNamespace()

    fun currentPolicySummary(): SkaldVaultV1TestOnlyProviderSyntheticIdentitySafeLabel =
        SkaldVaultV1TestOnlyProviderSyntheticIdentitySafeLabel(
            "Model-only synthetic identity namespace; accepted labels are future-review-only.",
        )

    fun currentStatuses(
        labelSyntaxEvaluated: Boolean = false,
        candidateLabelAcceptedForFutureReview: Boolean = false,
        candidateLabelRejected: Boolean = false,
    ): Set<SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceStatus> =
        buildSet {
            add(SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceStatus.NamespaceContractModeled)
            add(SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceStatus.StillDisabled)
            add(SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceStatus.LabelSyntaxPolicyModeled)
            add(SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceStatus.SyntheticPrefixModeled)
            add(SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceStatus.FutureIdentityFamiliesModeled)
            add(
                SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceStatus
                    .NoTestOnlyProviderIdentityImplemented,
            )
            add(
                SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceStatus
                    .NoProductionProviderIdentityImplemented,
            )
            add(SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceStatus.NoInstantiableIdentity)
            add(SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceStatus.NoRegistryKey)
            add(SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceStatus.NoFactoryInput)
            add(SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceStatus.NoDispatcherInput)
            add(SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceStatus.NoExecutorTarget)
            add(SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceStatus.NoEndpointAlias)
            add(SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceStatus.NoStoragePathAlias)
            add(
                SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceStatus
                    .ProviderSelectionAuthorizationBlocked,
            )
            add(SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceStatus.ProductionProviderSelectableFalse)
            add(SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceStatus.VaultLifecycleBlocked)
            add(SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceStatus.PersistenceBlocked)
            add(SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceStatus.ProductionSyncBlocked)
            add(SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceStatus.MainnetBlocked)
            if (labelSyntaxEvaluated) {
                add(SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceStatus.LabelSyntaxEvaluated)
            }
            if (candidateLabelAcceptedForFutureReview) {
                add(
                    SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceStatus
                        .CandidateLabelAcceptedForFutureReview,
                )
            }
            if (candidateLabelRejected) {
                add(SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceStatus.CandidateLabelRejected)
            }
        }

    fun currentBlockers(): Set<SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker> =
        setOf(
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.NamespaceContractModelOnly,
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.NoTestOnlyProviderIdentityImplemented,
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.NoProductionProviderIdentityImplemented,
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.NoProviderImplementation,
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.NoProviderFactory,
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.NoProviderDispatcher,
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.NoNonDisabledRegistryEntry,
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.NoExecutorTarget,
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.ProviderSelectionDisabledProviderOnly,
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.ProductionProviderSelectableFalse,
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.ProviderOperationAuthorizationBlocked,
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.RuntimeRandomnessAuthorizationBlocked,
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.KdfCalibrationNonFinal,
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.SecureStorageDisabled,
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.SecureMetadataDisabled,
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.VaultLifecycleDisabled,
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.PersistenceDisabled,
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.ProductionSyncDisabled,
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.SigningBroadcastingDisabled,
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.PublicEndpointDefaultsDisabled,
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.MainnetDisabled,
        )

    fun currentRedactionClasses(): Set<SkaldVaultV1TestOnlyProviderSyntheticIdentityRedactionClass> =
        SkaldVaultV1TestOnlyProviderSyntheticIdentityRedactionClass.entries.toSet()

    fun currentRuleRows(): List<SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceRuleRow> =
        SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceRule.entries.map { rule ->
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceRuleRow(
                rule = rule,
                represented = true,
                satisfiedForModelOnlyBoundary = true,
                authorizesCapability = false,
            )
        }

    fun currentForbiddenMaterialRows():
        List<SkaldVaultV1TestOnlyProviderSyntheticIdentityForbiddenMaterialRow> =
        SkaldVaultV1TestOnlyProviderSyntheticIdentityForbiddenMaterialClass.entries.map { materialClass ->
            SkaldVaultV1TestOnlyProviderSyntheticIdentityForbiddenMaterialRow(
                materialClass = materialClass,
                forbidden = true,
                acceptedNow = false,
                canAuthorize = false,
            )
        }

    fun currentForbiddenAliasRows(): List<SkaldVaultV1TestOnlyProviderSyntheticIdentityForbiddenAliasRow> =
        SkaldVaultV1TestOnlyProviderSyntheticIdentityForbiddenAlias.entries.map { alias ->
            SkaldVaultV1TestOnlyProviderSyntheticIdentityForbiddenAliasRow(
                alias = alias,
                forbidden = true,
                currentAliasPresent = false,
                canAuthorize = false,
            )
        }

    fun currentForbiddenLinkageRows():
        List<SkaldVaultV1TestOnlyProviderSyntheticIdentityForbiddenLinkageRow> =
        SkaldVaultV1TestOnlyProviderSyntheticIdentityForbiddenLinkage.entries.map { linkage ->
            SkaldVaultV1TestOnlyProviderSyntheticIdentityForbiddenLinkageRow(
                linkage = linkage,
                forbidden = true,
                currentLinkPresent = false,
                canAuthorize = false,
            )
        }

    fun currentFutureReviewRequirementRows():
        List<SkaldVaultV1TestOnlyProviderSyntheticIdentityFutureReviewRequirementRow> =
        SkaldVaultV1TestOnlyProviderSyntheticIdentityFutureReviewRequirement.entries.map { requirement ->
            SkaldVaultV1TestOnlyProviderSyntheticIdentityFutureReviewRequirementRow(
                requirement = requirement,
                futureRequired = true,
                satisfiedNow = false,
                authorizesCurrentImplementation = false,
            )
        }

    fun currentEvidenceSources(
        request: SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceRequest =
            SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceRequest(),
    ): Set<SkaldVaultV1TestOnlyProviderSyntheticIdentityEvidenceSource> =
        buildSet {
            if (request.includePriorIdentityDecisionEvidence) {
                add(
                    SkaldVaultV1TestOnlyProviderSyntheticIdentityEvidenceSource
                        .TestOnlyProviderIdentityDecision,
                )
            }
            if (request.includePriorIsolationEvidence) {
                add(
                    SkaldVaultV1TestOnlyProviderSyntheticIdentityEvidenceSource
                        .TestOnlyProviderIdentityIsolationGuard,
                )
            }
            add(SkaldVaultV1TestOnlyProviderSyntheticIdentityEvidenceSource.ProviderSelectionBoundary)
            add(SkaldVaultV1TestOnlyProviderSyntheticIdentityEvidenceSource.ProviderRegistryIsolationGuard)
            add(SkaldVaultV1TestOnlyProviderSyntheticIdentityEvidenceSource.ProviderFactoryIsolationBoundary)
            add(SkaldVaultV1TestOnlyProviderSyntheticIdentityEvidenceSource.ProviderOperationDispatchIsolation)
            add(SkaldVaultV1TestOnlyProviderSyntheticIdentityEvidenceSource.TestOnlyKatSourceSetConfinement)
            add(SkaldVaultV1TestOnlyProviderSyntheticIdentityEvidenceSource.TestOnlyKatExecutorReadinessGate)
            add(SkaldVaultV1TestOnlyProviderSyntheticIdentityEvidenceSource.TestOnlyKatExecutorContract)
            add(SkaldVaultV1TestOnlyProviderSyntheticIdentityEvidenceSource.TestOnlyKatVectorCatalog)
            add(SkaldVaultV1TestOnlyProviderSyntheticIdentityEvidenceSource.ProductionProviderAcceptanceContract)
            add(SkaldVaultV1TestOnlyProviderSyntheticIdentityEvidenceSource.ReadmeSecurityStatus)
        }

    fun requestBlockers(
        request: SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceRequest,
    ): Set<SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker> =
        buildSet {
            if (request.userConsentOverrideRequested) {
                add(SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.UserConsentCannotOverride)
            }
            if (request.warningOnlyEvidenceClaimed) {
                add(
                    SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker
                        .WarningOnlyEvidenceNonAuthorizing,
                )
            }
            if (request.testOnlyEvidenceClaimedAsProductionPromotion) {
                add(SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.TestOnlyEvidenceNonAuthorizing)
            }
            if (request.registryAliasClaimed) {
                add(SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.RegistryAliasRejected)
            }
            if (request.factoryAliasClaimed) {
                add(SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.FactoryAliasRejected)
            }
            if (request.dispatcherAliasClaimed) {
                add(SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.DispatcherAliasRejected)
            }
            if (request.executorTargetAliasClaimed) {
                add(SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.ExecutorTargetAliasRejected)
            }
            if (request.dependencyCandidateAliasClaimed) {
                add(SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.DependencyCandidateAliasRejected)
            }
            if (request.endpointAliasClaimed) {
                add(SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.EndpointAliasRejected)
            }
            if (request.storagePathAliasClaimed) {
                add(SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.StoragePathAliasRejected)
            }
            if (request.mainnetAliasClaimed) {
                add(SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespaceBlocker.MainnetAliasRejected)
            }
        }

    fun validateCandidate(
        candidate: SkaldVaultV1TestOnlyProviderSyntheticIdentitySafeId,
    ): Set<SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason> {
        val candidateValue = candidate.value
        val lower = candidateValue.lowercase()
        val purposeTokens = purposeTokens(candidate)
        return buildSet {
            if (candidateValue.isBlank()) {
                add(SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason.Blank)
            }
            if (candidateValue.length > MAX_SAFE_ID_LENGTH) {
                add(SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason.TooLong)
            }
            if (candidateValue != lower || !candidateValue.all { it.code in 0..127 }) {
                add(
                    SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason
                        .UppercaseOrNonLowercaseAscii,
                )
            }
            if (!candidateValue.all { isAllowedSafeIdCharacter(it) }) {
                add(SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason.IllegalCharacter)
            }
            if (!candidateValue.startsWith(ALLOWED_NAMESPACE_PREFIX)) {
                add(SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason.MissingPrefix)
            }
            if (candidateValue.startsWith("-") || candidateValue.endsWith("-")) {
                add(SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason.LeadingOrTrailingHyphen)
            }
            if ("--" in candidateValue) {
                add(SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason.DoubledHyphen)
            }
            if ("/" in candidateValue || "\\" in candidateValue) {
                add(SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason.PathSeparator)
            }
            if ("." in candidateValue) {
                add(SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason.Dot)
            }
            if (":" in candidateValue) {
                add(SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason.Colon)
            }
            if ("@" in candidateValue) {
                add(SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason.AtSign)
            }
            if ("://" in candidateValue || lower.startsWith("http-") || lower.startsWith("https-")) {
                add(SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason.UrlLikeStructure)
            }
            addAll(familyRejectionReasons(candidate))
            if (purposeTokens.any { it.length == 64 && it.all { character -> isHexDigit(character) } }) {
                add(SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason.Hex64Token)
            }
            if (hasEndpointLikeToken(purposeTokens)) {
                add(SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason.EndpointLikeStructure)
            }
            if (hasWalletAddressLikeToken(candidate)) {
                add(SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason.WalletAddressLikeStructure)
            }
            if (purposeTokens.any { it in dependencyAliasTokens() }) {
                add(SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason.DependencyCandidateAlias)
            }
            if (purposeTokens.any { it in productionProviderAliasTokens() }) {
                add(SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason.ProductionProviderAlias)
            }
            if (purposeTokens.any { it in productionPromotionTokens() }) {
                add(SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason.ProductionPromotionAlias)
            }
            if (purposeTokens.any { it in forbiddenMaterialTokens() }) {
                add(SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason.ForbiddenMaterialToken)
            }
            if (purposeTokens.any { it in forbiddenLinkageTokens() }) {
                add(SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason.ForbiddenLinkageToken)
            }
        }
    }

    fun candidateFamily(
        candidate: SkaldVaultV1TestOnlyProviderSyntheticIdentitySafeId,
    ): SkaldVaultV1TestOnlyProviderSyntheticIdentityFamily? {
        if (!candidate.value.startsWith(ALLOWED_NAMESPACE_PREFIX)) {
            return null
        }
        val afterPrefix = candidate.value.removePrefix(ALLOWED_NAMESPACE_PREFIX)
        return SkaldVaultV1TestOnlyProviderSyntheticIdentityFamily.entries.singleOrNull { family ->
            afterPrefix == family.token || afterPrefix.startsWith("${family.token}-")
        }
    }

    fun purposeTokens(candidate: SkaldVaultV1TestOnlyProviderSyntheticIdentitySafeId): List<String> {
        val family = candidateFamily(candidate) ?: return emptyList()
        val purposePart = candidate.value
            .removePrefix(ALLOWED_NAMESPACE_PREFIX)
            .removePrefix(family.token)
            .removePrefix("-")
        return if (purposePart.isBlank()) {
            emptyList()
        } else {
            purposePart.split("-").filter { it.isNotBlank() }
        }
    }

    fun familyRejectionReasons(
        candidate: SkaldVaultV1TestOnlyProviderSyntheticIdentitySafeId,
    ): Set<SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason> {
        if (!candidate.value.startsWith(ALLOWED_NAMESPACE_PREFIX)) {
            return setOf(SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason.MissingFamilyToken)
        }
        val afterPrefix = candidate.value.removePrefix(ALLOWED_NAMESPACE_PREFIX)
        val matchingFamilies = SkaldVaultV1TestOnlyProviderSyntheticIdentityFamily.entries.filter { family ->
            afterPrefix == family.token || afterPrefix.startsWith("${family.token}-")
        }
        return buildSet {
            if (matchingFamilies.isEmpty()) {
                add(SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason.MissingFamilyToken)
            }
            if (matchingFamilies.size > 1) {
                add(SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason.MultipleFamilyTokens)
            }
            val family = matchingFamilies.singleOrNull()
            val purposePart = family
                ?.let { afterPrefix.removePrefix(it.token).removePrefix("-") }
                .orEmpty()
            if (family != null && purposePart.isBlank()) {
                add(SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason.MissingPurposeToken)
            }
            if (
                family != null &&
                SkaldVaultV1TestOnlyProviderSyntheticIdentityFamily.entries
                    .filter { it != family }
                    .any { it.token in purposePart }
            ) {
                add(SkaldVaultV1TestOnlyProviderSyntheticIdentityRejectionReason.MultipleFamilyTokens)
            }
        }
    }

    fun isAllowedSafeIdCharacter(character: Char): Boolean =
        character in 'a'..'z' || character in '0'..'9' || character == '-'

    fun isHexDigit(character: Char): Boolean =
        character in '0'..'9' || character in 'a'..'f' || character in 'A'..'F'

    fun hasEndpointLikeToken(tokens: List<String>): Boolean =
        tokens.any { it in endpointLikeTokens() }

    fun hasWalletAddressLikeToken(candidate: SkaldVaultV1TestOnlyProviderSyntheticIdentitySafeId): Boolean {
        val lower = candidate.value.lowercase()
        return lower.startsWith("bc1") ||
            lower.startsWith("tb1") ||
            lower.startsWith("bcrt1") ||
            "-bc1" in lower ||
            "-tb1" in lower ||
            "-bcrt1" in lower
    }

    fun endpointLikeTokens(): Set<String> =
        setOf("http", "https", "tcp", "udp", "onion", "localhost", "port", "endpoint")

    fun dependencyAliasTokens(): Set<String> =
        setOf("tink", "bouncy", "bouncycastle", "lazysodium", "ionspin", "bdk", "electrum", "esplora")

    fun productionProviderAliasTokens(): Set<String> =
        setOf("production", "prod", "release", "mainnet")

    fun productionPromotionTokens(): Set<String> =
        setOf("production", "prod", "release", "mainnet", "promote", "promotion", "signing", "broadcasting")

    fun forbiddenMaterialTokens(): Set<String> =
        setOf(
            "passphrase",
            "secret",
            "seed",
            "mnemonic",
            "xprv",
            "xpub",
            "nsec",
            "key",
            "credential",
            "descriptor",
            "path",
            "file",
            "backend",
            "wallet",
            "psbt",
            "transaction",
            "salt",
            "nonce",
            "ciphertext",
            "plaintext",
            "keyset",
            "handle",
            "object",
        )

    fun forbiddenLinkageTokens(): Set<String> =
        setOf(
            "registry",
            "factory",
            "dispatcher",
            "executor",
            "storage",
            "settings",
            "ui",
            "sync",
            "tor",
            "nostr",
        )
}
