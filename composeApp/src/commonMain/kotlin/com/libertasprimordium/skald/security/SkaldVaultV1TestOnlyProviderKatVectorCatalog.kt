package com.libertasprimordium.skald.security

data class SkaldVaultV1TestOnlyProviderKatVectorReferenceId(val value: String)

data class SkaldVaultV1TestOnlyProviderKatVectorProvenanceId(val value: String)

data class SkaldVaultV1TestOnlyProviderKatSafeLabel(val value: String)

enum class SkaldVaultV1TestOnlyProviderKatVectorCatalogStatus(val label: String) {
    CatalogModeled("catalog modeled"),
    StillDisabled("still disabled"),
    VectorReferencesOnly("vector references only"),
    NoRawVectorMaterial("no raw vector material"),
    NoExecutor("no executor"),
    NoProviderExecution("no provider execution"),
    PositiveVectorsCataloged("positive vectors cataloged"),
    NegativeVectorsCataloged("negative vectors cataloged"),
    RedactionChecksCataloged("redaction checks cataloged"),
    PlatformChecksCataloged("platform checks cataloged"),
    ProductionAuthorizationBlocked("production authorization blocked"),
    MainnetBlocked("mainnet blocked"),
}

enum class SkaldVaultV1TestOnlyProviderKatVectorSourceClass(val label: String) {
    Rfc9106Argon2idPublicVectorReference("RFC 9106 Argon2id public vector reference"),
    XChaChaDraftPublicVectorReference("XChaCha draft public vector reference"),
    SkaldCanonicalHeaderHkdfHmacVectorReference("Skald canonical header HKDF HMAC vector reference"),
    SkaldStrictAadSerializationVectorReference("Skald strict AAD serialization vector reference"),
    SkaldProviderContractVectorReference("Skald provider contract vector reference"),
    SkaldRedactionBehaviorVectorReference("Skald redaction behavior vector reference"),
    SkaldNegativeTamperClassReference("Skald negative tamper class reference"),
    SkaldPlatformRuntimeCheckReference("Skald platform runtime check reference"),
    SyntheticNonWalletTestOnlyReference("synthetic non-wallet test-only reference"),
}

enum class SkaldVaultV1TestOnlyProviderKatVectorProvenanceStatus(val label: String) {
    PublicStandardsDocumentReference("public standards document reference"),
    SkaldCanonicalDocumentationReference("Skald canonical documentation reference"),
    SkaldGeneratedNonWalletDeterministicFixtureReference(
        "Skald generated non-wallet deterministic fixture reference",
    ),
    SyntheticTestOnlyGeneratedAtRuntime("synthetic test-only generated at runtime"),
    DocumentationOnlyProvenance("documentation-only provenance"),
    TestOnlyProvenance("test-only provenance"),
    NotProductionAuthorizing("not production-authorizing"),
    RejectedWalletLikeProvenance("rejected wallet-like provenance"),
}

enum class SkaldVaultV1TestOnlyProviderKatPositiveVectorClass(val label: String) {
    Argon2idKnownAnswer("Argon2id known answer"),
    XChaCha20Poly1305KnownAnswer("XChaCha20 Poly1305 known answer"),
    HkdfSha256ExpansionKnownAnswer("HKDF SHA256 expansion known answer"),
    HmacSha256HeaderCommitmentKnownAnswer("HMAC SHA256 header commitment known answer"),
    CanonicalHeaderSerialization("canonical header serialization"),
    StrictAadSerialization("strict AAD serialization"),
    ProviderPolicyIdMapping("provider policy ID mapping"),
    RedactedSuccessResultShape("redacted success result shape"),
    PlatformRuntimeAvailabilityResultShape("platform runtime availability result shape"),
}

enum class SkaldVaultV1TestOnlyProviderKatNegativeVectorClass(val label: String) {
    WrongArgon2idMemory("wrong Argon2id memory"),
    WrongArgon2idIterations("wrong Argon2id iterations"),
    WrongArgon2idParallelism("wrong Argon2id parallelism"),
    WrongArgon2idOutputLength("wrong Argon2id output length"),
    WrongSuiteId("wrong suite ID"),
    WrongProviderId("wrong provider ID"),
    WrongHeaderVersion("wrong header version"),
    WrongHeaderCommitment("wrong header commitment"),
    WrongHkdfLabel("wrong HKDF label"),
    WrongHmacKeyClass("wrong HMAC key class"),
    WrongAad("wrong AAD"),
    WrongRecordPurpose("wrong record purpose"),
    WrongNoncePolicy("wrong N policy"),
    WrongAeadKeyClass("wrong AEAD key class"),
    TamperedCiphertext("tampered encrypted payload"),
    TamperedTag("tampered tag"),
    TruncatedPayload("truncated payload"),
    ExtraTrailingBytes("extra trailing bytes"),
    WrongPlatformPolicy("wrong platform policy"),
    WrongSourceSetPolicy("wrong source-set policy"),
    SecretLikeFixtureRejected("credential-like fixture rejected"),
    WalletLikeFixtureRejected("wallet-like fixture rejected"),
    ProviderHandleRejected("provider reference rejected"),
    CryptoObjectRejected("crypto reference rejected"),
    RawByteMaterialRejected("raw byte material rejected"),
}

enum class SkaldVaultV1TestOnlyProviderKatRedactionVectorClass(val label: String) {
    NoRawInputInResult("no raw input in result"),
    NoRawOutputInResult("no raw output in result"),
    NoKeyMaterialInResult("no key material in result"),
    NoNonceInResult("no N value in result"),
    NoSaltInResult("no S value in result"),
    NoPlaintextInResult("no clear payload in result"),
    NoCiphertextInResult("no encrypted payload in result"),
    NoProviderHandleInResult("no provider reference in result"),
    NoCryptoObjectInResult("no crypto reference in result"),
    NoFileLocationInResult("no file location in result"),
    NoWalletMetadataInResult("no wallet metadata in result"),
    NoBackendMetadataInResult("no backend metadata in result"),
    SafeVectorIdOnly("safe vector ID only"),
    SafeOperationLabelOnly("safe operation label only"),
    SafeFailureCodeOnly("safe failure code only"),
}

enum class SkaldVaultV1TestOnlyProviderKatPlatformCheckVectorClass(val label: String) {
    DesktopJvmCandidateApiAvailable("desktop JVM candidate API available"),
    AndroidRuntimeCandidateApiAvailable("Android runtime candidate API available"),
    AndroidInstrumentationTargetExplicit("Android instrumentation target explicit"),
    LinuxJvmRuntimeClasspathVerified("Linux JVM runtime classpath verified"),
    NoNativeTinkBouncyArtifactExpected("no native Tink/Bouncy artifact expected"),
    LazysodiumRejected("Lazysodium rejected"),
    IonSpinDeferred("IonSpin deferred"),
    BitcoinWalletLibraryUnrelated("Bitcoin wallet library unrelated"),
}

enum class SkaldVaultV1TestOnlyProviderKatFixtureClass(
    val label: String,
    val futureAllowedAsReferenceOnly: Boolean,
    val forbidden: Boolean,
) {
    PublicNonWalletVectorReference(
        "public non-wallet vector reference",
        futureAllowedAsReferenceOnly = true,
        forbidden = false,
    ),
    DeterministicSyntheticNonWalletReference(
        "deterministic synthetic non-wallet reference",
        futureAllowedAsReferenceOnly = true,
        forbidden = false,
    ),
    RuntimeGeneratedSyntheticNonWalletTestMaterial(
        "runtime-generated synthetic non-wallet test material",
        futureAllowedAsReferenceOnly = true,
        forbidden = false,
    ),
    RedactedExpectedOutcomeReference(
        "redacted expected outcome reference",
        futureAllowedAsReferenceOnly = true,
        forbidden = false,
    ),
    SafeVectorId(
        "safe vector ID",
        futureAllowedAsReferenceOnly = true,
        forbidden = false,
    ),
    SafeOperationLabel(
        "safe operation label",
        futureAllowedAsReferenceOnly = true,
        forbidden = false,
    ),
    WalletSeed("wallet seed", futureAllowedAsReferenceOnly = false, forbidden = true),
    MnemonicPhrase("mnemonic", futureAllowedAsReferenceOnly = false, forbidden = true),
    Descriptor("descriptor", futureAllowedAsReferenceOnly = false, forbidden = true),
    XprvTprvWif("xprv/tprv/WIF", futureAllowedAsReferenceOnly = false, forbidden = true),
    NostrNsec("Nostr nsec", futureAllowedAsReferenceOnly = false, forbidden = true),
    LightningCredential("Lightning credential", futureAllowedAsReferenceOnly = false, forbidden = true),
    CashuProof("Cashu proof", futureAllowedAsReferenceOnly = false, forbidden = true),
    BackendCredential("backend credential", futureAllowedAsReferenceOnly = false, forbidden = true),
    RealAddress("real address", futureAllowedAsReferenceOnly = false, forbidden = true),
    RealTxid("real txid", futureAllowedAsReferenceOnly = false, forbidden = true),
    Psbt("PSBT", futureAllowedAsReferenceOnly = false, forbidden = true),
    TransactionHex("transaction hex", futureAllowedAsReferenceOnly = false, forbidden = true),
    WalletLabel("wallet label", futureAllowedAsReferenceOnly = false, forbidden = true),
    UtxoLabel("UTXO label", futureAllowedAsReferenceOnly = false, forbidden = true),
    TransactionNote("transaction note", futureAllowedAsReferenceOnly = false, forbidden = true),
    BackendObservationMetadata(
        "backend observation metadata",
        futureAllowedAsReferenceOnly = false,
        forbidden = true,
    ),
    SecureMetadataRecord("secure metadata record", futureAllowedAsReferenceOnly = false, forbidden = true),
    ProductionVaultRecord("production vault record", futureAllowedAsReferenceOnly = false, forbidden = true),
    FileLocation("file location", futureAllowedAsReferenceOnly = false, forbidden = true),
    ProviderReference("provider reference", futureAllowedAsReferenceOnly = false, forbidden = true),
    CryptoReference("crypto reference", futureAllowedAsReferenceOnly = false, forbidden = true),
}

enum class SkaldVaultV1TestOnlyProviderKatVectorAuthorizationLimit(val label: String) {
    ExecutorImplementation("executor implementation"),
    ExecutorExecution("executor execution"),
    ProviderOperationExecution("provider operation execution"),
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

enum class SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker(val label: String) {
    CatalogReferencesOnly("catalog references only"),
    NoRawVectorMaterial("no raw vector material"),
    ExecutorImplementationDeferred("executor implementation deferred"),
    NoExecutorCallableSurface("no executor callable surface"),
    NoProviderImplementation("no provider implementation"),
    NoProviderFactory("no provider factory"),
    NoProviderDispatcher("no provider dispatcher"),
    NoNonDisabledRegistryEntry("no non-disabled registry entry"),
    ProviderOperationAuthorizationBlocked("provider operation authorization blocked"),
    RuntimeRandomnessAuthorizationBlocked("runtime randomness authorization blocked"),
    KdfCalibrationNonFinal("KDF calibration non-final"),
    SecureStorageDisabled("secure storage disabled"),
    SecureMetadataDisabled("secure metadata disabled"),
    VaultLifecycleDisabled("vault lifecycle disabled"),
    PersistenceDisabled("persistence disabled"),
    ProviderSelectionDisabledProviderOnly("provider selection disabled-provider-only"),
    ProductionProviderSelectableFalse("productionProviderSelectable false"),
    VectorCatalogEvidenceNonAuthorizing("vector catalog evidence non-authorizing"),
    TestOnlyEvidenceNonAuthorizing("test-only evidence non-authorizing"),
    WarningOnlyEvidenceNonAuthorizing("warning-only evidence non-authorizing"),
    UserConsentCannotOverride("user consent cannot override"),
    ReleaseReviewMissing("release review missing"),
    MainnetDisabled("mainnet disabled"),
}

enum class SkaldVaultV1TestOnlyProviderKatVectorRedactionClass(val label: String) {
    PolicyIdsOnly("policy IDs only"),
    VectorIdsOnly("vector IDs only"),
    ProvenanceIdsOnly("provenance IDs only"),
    SourceClassLabelsOnly("source-class labels only"),
    PositiveClassLabelsOnly("positive-class labels only"),
    NegativeClassLabelsOnly("negative-class labels only"),
    RedactionClassLabelsOnly("redaction-class labels only"),
    PlatformClassLabelsOnly("platform-class labels only"),
    FixtureClassLabelsOnly("fixture-class labels only"),
    AuthorizationLimitLabelsOnly("authorization-limit labels only"),
    BlockerLabelsOnly("blocker labels only"),
    SafeOperationLabelsOnly("safe operation labels only"),
    SafeFailureCodesOnly("safe failure codes only"),
    NoRawInput("no raw input"),
    NoRawOutput("no raw output"),
    NoKeyMaterial("no key material"),
    NoRandomnessValue("no randomness value"),
    NoClearPayload("no clear payload"),
    NoEncryptedPayload("no encrypted payload"),
    NoProviderReferences("no provider references"),
    NoCryptoReferences("no crypto references"),
    NoFileLocations("no file locations"),
    NoWalletMetadata("no wallet metadata"),
    NoBackendMetadata("no backend metadata"),
}

enum class SkaldVaultV1TestOnlyProviderKatVectorFutureWork(val label: String) {
    TestOnlyExecutorBranchRequired("test-only executor branch required"),
    SourceSetConfinementReviewRequired("source-set confinement review required"),
    PublicVectorProvenanceReviewRequired("public vector provenance review required"),
    CanonicalVectorProvenanceReviewRequired("canonical vector provenance review required"),
    NegativeVectorPlanReviewRequired("negative vector plan review required"),
    RedactionVectorPlanReviewRequired("redaction vector plan review required"),
    PlatformRuntimeTargetingReviewRequired("platform runtime targeting review required"),
    NoWalletFixtureReviewRequired("no wallet fixture review required"),
    ResultNonAuthorizationReviewRequired("result non-authorization review required"),
    ProviderSelectionDisabledAssertionRequired("provider selection disabled assertion required"),
    ProductionProviderSelectableFalseAssertionRequired("productionProviderSelectable false assertion required"),
    ReleaseMainnetNonAuthorizationReviewRequired("release/mainnet non-authorization review required"),
}

data class SkaldVaultV1TestOnlyProviderKatVectorCatalogCapability(
    val catalogModeled: Boolean,
    val rawVectorMaterialPresent: Boolean,
    val executorImplemented: Boolean,
    val executorCallable: Boolean,
    val canRunVectorsNow: Boolean,
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
    val canAuthorizeSecureStorageSuccess: Boolean,
    val canAuthorizeSecureMetadataSuccess: Boolean,
    val canAuthorizeProductionSync: Boolean,
    val canAuthorizeSigning: Boolean,
    val canAuthorizeBroadcasting: Boolean,
    val canAuthorizeMainnet: Boolean,
) {
    companion object {
        val Current = SkaldVaultV1TestOnlyProviderKatVectorCatalogCapability(
            catalogModeled = true,
            rawVectorMaterialPresent = false,
            executorImplemented = false,
            executorCallable = false,
            canRunVectorsNow = false,
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
            canAuthorizeSecureStorageSuccess = false,
            canAuthorizeSecureMetadataSuccess = false,
            canAuthorizeProductionSync = false,
            canAuthorizeSigning = false,
            canAuthorizeBroadcasting = false,
            canAuthorizeMainnet = false,
        )
    }
}

data class SkaldVaultV1TestOnlyProviderKatVectorSourceRow(
    val sourceClass: SkaldVaultV1TestOnlyProviderKatVectorSourceClass,
    val referenceId: SkaldVaultV1TestOnlyProviderKatVectorReferenceId,
    val provenanceId: SkaldVaultV1TestOnlyProviderKatVectorProvenanceId,
    val provenanceStatuses: Set<SkaldVaultV1TestOnlyProviderKatVectorProvenanceStatus>,
    val referenceOnly: Boolean,
    val rawMaterialPresent: Boolean,
    val currentExecutable: Boolean,
    val canAuthorizeExecution: Boolean,
    val canAuthorizeProduction: Boolean,
    val redactionClass: SkaldVaultV1TestOnlyProviderKatVectorRedactionClass,
)

data class SkaldVaultV1TestOnlyProviderKatPositiveVectorRow(
    val vectorClass: SkaldVaultV1TestOnlyProviderKatPositiveVectorClass,
    val referenceId: SkaldVaultV1TestOnlyProviderKatVectorReferenceId,
    val referenceOnly: Boolean,
    val rawMaterialPresent: Boolean,
    val currentExecutable: Boolean,
    val canAuthorizeExecution: Boolean,
    val canAuthorizeProduction: Boolean,
    val redactionClass: SkaldVaultV1TestOnlyProviderKatVectorRedactionClass,
)

data class SkaldVaultV1TestOnlyProviderKatNegativeVectorRow(
    val vectorClass: SkaldVaultV1TestOnlyProviderKatNegativeVectorClass,
    val referenceId: SkaldVaultV1TestOnlyProviderKatVectorReferenceId,
    val referenceOnly: Boolean,
    val rawMaterialPresent: Boolean,
    val currentExecutable: Boolean,
    val canAuthorizeExecution: Boolean,
    val canAuthorizeProduction: Boolean,
    val redactionClass: SkaldVaultV1TestOnlyProviderKatVectorRedactionClass,
)

data class SkaldVaultV1TestOnlyProviderKatRedactionVectorRow(
    val vectorClass: SkaldVaultV1TestOnlyProviderKatRedactionVectorClass,
    val referenceId: SkaldVaultV1TestOnlyProviderKatVectorReferenceId,
    val referenceOnly: Boolean,
    val rawMaterialPresent: Boolean,
    val currentExecutable: Boolean,
    val canAuthorizeExecution: Boolean,
    val canAuthorizeProduction: Boolean,
    val redactionClass: SkaldVaultV1TestOnlyProviderKatVectorRedactionClass,
)

data class SkaldVaultV1TestOnlyProviderKatPlatformCheckVectorRow(
    val vectorClass: SkaldVaultV1TestOnlyProviderKatPlatformCheckVectorClass,
    val referenceId: SkaldVaultV1TestOnlyProviderKatVectorReferenceId,
    val referenceOnly: Boolean,
    val rawMaterialPresent: Boolean,
    val currentExecutable: Boolean,
    val canAuthorizeExecution: Boolean,
    val canAuthorizeProduction: Boolean,
    val redactionClass: SkaldVaultV1TestOnlyProviderKatVectorRedactionClass,
)

data class SkaldVaultV1TestOnlyProviderKatFixtureClassRow(
    val fixtureClass: SkaldVaultV1TestOnlyProviderKatFixtureClass,
    val futureAllowedAsReferenceOnly: Boolean,
    val forbidden: Boolean,
    val currentAccepted: Boolean,
    val payloadAllowed: Boolean,
    val canAuthorizeExecution: Boolean,
    val canAuthorizeProduction: Boolean,
    val redactionClass: SkaldVaultV1TestOnlyProviderKatVectorRedactionClass,
)

data class SkaldVaultV1TestOnlyProviderKatVectorAuthorizationLimitRow(
    val limit: SkaldVaultV1TestOnlyProviderKatVectorAuthorizationLimit,
    val catalogEntryCanAuthorize: Boolean,
    val currentCatalogCanAuthorize: Boolean,
    val blockers: Set<SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker>,
)

data class SkaldVaultV1TestOnlyProviderKatVectorFutureWorkRow(
    val work: SkaldVaultV1TestOnlyProviderKatVectorFutureWork,
    val requiredBeforeExecutorBranch: Boolean,
    val requiredBeforeProductionSelection: Boolean,
    val currentBranchCompletesWork: Boolean,
    val safeLabel: SkaldVaultV1TestOnlyProviderKatSafeLabel,
)

data class SkaldVaultV1TestOnlyProviderKatVectorCatalogRequest(
    val includeFutureReferences: Boolean,
    val warningOnlyEvidenceClaimed: Boolean,
    val userConsentOverrideRequested: Boolean,
    val releaseEvidenceClaimed: Boolean,
    val safeCatalogId: String,
) {
    override fun toString(): String =
        "SkaldVaultV1TestOnlyProviderKatVectorCatalogRequest(" +
            "includeFutureReferences=$includeFutureReferences, " +
            "warningOnlyEvidenceClaimed=$warningOnlyEvidenceClaimed, " +
            "userConsentOverrideRequested=$userConsentOverrideRequested, " +
            "releaseEvidenceClaimed=$releaseEvidenceClaimed, " +
            "safeCatalogId=<redacted>, " +
            "rawVector=<redacted>, " +
            "payload=<redacted>, " +
            "providerReference=<redacted>, " +
            "cryptoReference=<redacted>, " +
            "fileLocation=<redacted>, " +
            "storageReference=<redacted>, " +
            "backendReference=<redacted>" +
            ")"

    companion object {
        fun currentCatalog(
            includeFutureReferences: Boolean = true,
            safeCatalogId: String = "current-test-only-provider-kat-vector-catalog",
        ): SkaldVaultV1TestOnlyProviderKatVectorCatalogRequest =
            SkaldVaultV1TestOnlyProviderKatVectorCatalogRequest(
                includeFutureReferences = includeFutureReferences,
                warningOnlyEvidenceClaimed = false,
                userConsentOverrideRequested = false,
                releaseEvidenceClaimed = false,
                safeCatalogId = safeCatalogId,
            )
    }
}

data class SkaldVaultV1TestOnlyProviderKatVectorCatalogEvidence(
    val status: SkaldVaultV1TestOnlyProviderKatVectorCatalogStatus,
    val statuses: Set<SkaldVaultV1TestOnlyProviderKatVectorCatalogStatus>,
    val sourceRows: List<SkaldVaultV1TestOnlyProviderKatVectorSourceRow>,
    val positiveVectorRows: List<SkaldVaultV1TestOnlyProviderKatPositiveVectorRow>,
    val negativeVectorRows: List<SkaldVaultV1TestOnlyProviderKatNegativeVectorRow>,
    val redactionVectorRows: List<SkaldVaultV1TestOnlyProviderKatRedactionVectorRow>,
    val platformCheckVectorRows: List<SkaldVaultV1TestOnlyProviderKatPlatformCheckVectorRow>,
    val fixtureClassRows: List<SkaldVaultV1TestOnlyProviderKatFixtureClassRow>,
    val authorizationLimitRows: List<SkaldVaultV1TestOnlyProviderKatVectorAuthorizationLimitRow>,
    val futureRequiredWork: List<SkaldVaultV1TestOnlyProviderKatVectorFutureWorkRow>,
    val blockers: Set<SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker>,
    val redactionClasses: Set<SkaldVaultV1TestOnlyProviderKatVectorRedactionClass>,
    val disabledCapabilities: SkaldVaultV1TestOnlyProviderKatVectorCatalogCapability,
    val catalogModeled: Boolean,
    val rawVectorMaterialPresent: Boolean,
    val executorImplemented: Boolean,
    val executorCallable: Boolean,
    val currentExecutionAuthorized: Boolean,
    val providerSelectionDisabledProviderOnly: Boolean,
    val productionProviderSelectable: Boolean,
    val vaultLifecycleBlocked: Boolean,
    val persistenceBlocked: Boolean,
    val productionSyncBlocked: Boolean,
    val mainnetBlocked: Boolean,
)

sealed interface SkaldVaultV1TestOnlyProviderKatVectorCatalogResult<out T> {
    val value: T

    data class Blocked<out T>(
        override val value: T,
    ) : SkaldVaultV1TestOnlyProviderKatVectorCatalogResult<T>
}

data class SkaldVaultV1TestOnlyProviderKatVectorCatalogSummary(
    val policyId: String,
    val policyVersion: Int,
    val statuses: Set<SkaldVaultV1TestOnlyProviderKatVectorCatalogStatus>,
    val sourceClasses: Set<SkaldVaultV1TestOnlyProviderKatVectorSourceClass>,
    val provenanceStatuses: Set<SkaldVaultV1TestOnlyProviderKatVectorProvenanceStatus>,
    val positiveVectorClasses: Set<SkaldVaultV1TestOnlyProviderKatPositiveVectorClass>,
    val negativeVectorClasses: Set<SkaldVaultV1TestOnlyProviderKatNegativeVectorClass>,
    val redactionVectorClasses: Set<SkaldVaultV1TestOnlyProviderKatRedactionVectorClass>,
    val platformCheckVectorClasses: Set<SkaldVaultV1TestOnlyProviderKatPlatformCheckVectorClass>,
    val fixtureClasses: Set<SkaldVaultV1TestOnlyProviderKatFixtureClass>,
    val authorizationLimits: Set<SkaldVaultV1TestOnlyProviderKatVectorAuthorizationLimit>,
    val futureRequiredWork: Set<SkaldVaultV1TestOnlyProviderKatVectorFutureWork>,
    val blockers: Set<SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker>,
    val redactionClasses: Set<SkaldVaultV1TestOnlyProviderKatVectorRedactionClass>,
    val capability: SkaldVaultV1TestOnlyProviderKatVectorCatalogCapability,
)

object SkaldVaultV1TestOnlyProviderKatVectorCatalogPolicy {
    const val POLICY_ID: String = "skald-vault-v1-test-only-provider-kat-vector-catalog-v1"
    const val POLICY_VERSION: Int = 1

    fun evaluateCatalog(
        request: SkaldVaultV1TestOnlyProviderKatVectorCatalogRequest =
            SkaldVaultV1TestOnlyProviderKatVectorCatalogRequest.currentCatalog(),
    ): SkaldVaultV1TestOnlyProviderKatVectorCatalogResult.Blocked<
        SkaldVaultV1TestOnlyProviderKatVectorCatalogEvidence,
    > =
        SkaldVaultV1TestOnlyProviderKatVectorCatalogResult.Blocked(
            currentCatalogEvidence(request),
        )

    fun currentPolicySummary(): SkaldVaultV1TestOnlyProviderKatVectorCatalogSummary =
        SkaldVaultV1TestOnlyProviderKatVectorCatalogSummary(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            statuses = SkaldVaultV1TestOnlyProviderKatVectorCatalogStatus.entries.toSet(),
            sourceClasses = SkaldVaultV1TestOnlyProviderKatVectorSourceClass.entries.toSet(),
            provenanceStatuses = SkaldVaultV1TestOnlyProviderKatVectorProvenanceStatus.entries.toSet(),
            positiveVectorClasses = SkaldVaultV1TestOnlyProviderKatPositiveVectorClass.entries.toSet(),
            negativeVectorClasses = SkaldVaultV1TestOnlyProviderKatNegativeVectorClass.entries.toSet(),
            redactionVectorClasses = SkaldVaultV1TestOnlyProviderKatRedactionVectorClass.entries.toSet(),
            platformCheckVectorClasses = SkaldVaultV1TestOnlyProviderKatPlatformCheckVectorClass.entries.toSet(),
            fixtureClasses = SkaldVaultV1TestOnlyProviderKatFixtureClass.entries.toSet(),
            authorizationLimits = SkaldVaultV1TestOnlyProviderKatVectorAuthorizationLimit.entries.toSet(),
            futureRequiredWork = SkaldVaultV1TestOnlyProviderKatVectorFutureWork.entries.toSet(),
            blockers = currentBlockers(),
            redactionClasses = SkaldVaultV1TestOnlyProviderKatVectorRedactionClass.entries.toSet(),
            capability = SkaldVaultV1TestOnlyProviderKatVectorCatalogCapability.Current,
        )

    fun currentCatalogEvidence(
        request: SkaldVaultV1TestOnlyProviderKatVectorCatalogRequest =
            SkaldVaultV1TestOnlyProviderKatVectorCatalogRequest.currentCatalog(),
    ): SkaldVaultV1TestOnlyProviderKatVectorCatalogEvidence {
        val limitRows = currentAuthorizationLimitRows()
        val blockers = (
            currentBlockers() +
                limitRows.flatMap { it.blockers } +
                requestBlockers(request)
            ).toSet()
        return SkaldVaultV1TestOnlyProviderKatVectorCatalogEvidence(
            status = SkaldVaultV1TestOnlyProviderKatVectorCatalogStatus.StillDisabled,
            statuses = currentStatuses(),
            sourceRows = currentSourceRows(),
            positiveVectorRows = currentPositiveVectorRows(),
            negativeVectorRows = currentNegativeVectorRows(),
            redactionVectorRows = currentRedactionVectorRows(),
            platformCheckVectorRows = currentPlatformCheckVectorRows(),
            fixtureClassRows = currentFixtureClassRows(),
            authorizationLimitRows = limitRows,
            futureRequiredWork = currentFutureWorkRows(),
            blockers = blockers,
            redactionClasses = SkaldVaultV1TestOnlyProviderKatVectorRedactionClass.entries.toSet(),
            disabledCapabilities = SkaldVaultV1TestOnlyProviderKatVectorCatalogCapability.Current,
            catalogModeled = true,
            rawVectorMaterialPresent = false,
            executorImplemented = false,
            executorCallable = false,
            currentExecutionAuthorized = false,
            providerSelectionDisabledProviderOnly = true,
            productionProviderSelectable = false,
            vaultLifecycleBlocked = true,
            persistenceBlocked = true,
            productionSyncBlocked = true,
            mainnetBlocked = true,
        )
    }

    fun currentStatuses(): Set<SkaldVaultV1TestOnlyProviderKatVectorCatalogStatus> =
        setOf(
            SkaldVaultV1TestOnlyProviderKatVectorCatalogStatus.CatalogModeled,
            SkaldVaultV1TestOnlyProviderKatVectorCatalogStatus.StillDisabled,
            SkaldVaultV1TestOnlyProviderKatVectorCatalogStatus.VectorReferencesOnly,
            SkaldVaultV1TestOnlyProviderKatVectorCatalogStatus.NoRawVectorMaterial,
            SkaldVaultV1TestOnlyProviderKatVectorCatalogStatus.NoExecutor,
            SkaldVaultV1TestOnlyProviderKatVectorCatalogStatus.NoProviderExecution,
            SkaldVaultV1TestOnlyProviderKatVectorCatalogStatus.PositiveVectorsCataloged,
            SkaldVaultV1TestOnlyProviderKatVectorCatalogStatus.NegativeVectorsCataloged,
            SkaldVaultV1TestOnlyProviderKatVectorCatalogStatus.RedactionChecksCataloged,
            SkaldVaultV1TestOnlyProviderKatVectorCatalogStatus.PlatformChecksCataloged,
            SkaldVaultV1TestOnlyProviderKatVectorCatalogStatus.ProductionAuthorizationBlocked,
            SkaldVaultV1TestOnlyProviderKatVectorCatalogStatus.MainnetBlocked,
        )

    fun currentBlockers(): Set<SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker> =
        setOf(
            SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker.CatalogReferencesOnly,
            SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker.NoRawVectorMaterial,
            SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker.ExecutorImplementationDeferred,
            SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker.NoExecutorCallableSurface,
            SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker.NoProviderImplementation,
            SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker.NoProviderFactory,
            SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker.NoProviderDispatcher,
            SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker.NoNonDisabledRegistryEntry,
            SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker.ProviderOperationAuthorizationBlocked,
            SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker.RuntimeRandomnessAuthorizationBlocked,
            SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker.KdfCalibrationNonFinal,
            SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker.SecureStorageDisabled,
            SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker.SecureMetadataDisabled,
            SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker.VaultLifecycleDisabled,
            SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker.PersistenceDisabled,
            SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker.ProviderSelectionDisabledProviderOnly,
            SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker.ProductionProviderSelectableFalse,
            SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker.VectorCatalogEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker.TestOnlyEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker.WarningOnlyEvidenceNonAuthorizing,
            SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker.UserConsentCannotOverride,
            SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker.ReleaseReviewMissing,
            SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker.MainnetDisabled,
        )

    fun requestBlockers(
        request: SkaldVaultV1TestOnlyProviderKatVectorCatalogRequest,
    ): Set<SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker> =
        buildSet {
            if (request.warningOnlyEvidenceClaimed) {
                add(SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker.WarningOnlyEvidenceNonAuthorizing)
            }
            if (request.userConsentOverrideRequested) {
                add(SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker.UserConsentCannotOverride)
            }
            if (request.releaseEvidenceClaimed) {
                add(SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker.ReleaseReviewMissing)
                add(SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker.MainnetDisabled)
            }
        }

    fun currentSourceRows(): List<SkaldVaultV1TestOnlyProviderKatVectorSourceRow> =
        listOf(
            sourceRow(
                SkaldVaultV1TestOnlyProviderKatVectorSourceClass.Rfc9106Argon2idPublicVectorReference,
                "kat-source-rfc9106-argon2id-public-reference",
                "kat-provenance-rfc9106-public-standards",
                setOf(
                    SkaldVaultV1TestOnlyProviderKatVectorProvenanceStatus.PublicStandardsDocumentReference,
                    SkaldVaultV1TestOnlyProviderKatVectorProvenanceStatus.DocumentationOnlyProvenance,
                    SkaldVaultV1TestOnlyProviderKatVectorProvenanceStatus.NotProductionAuthorizing,
                ),
            ),
            sourceRow(
                SkaldVaultV1TestOnlyProviderKatVectorSourceClass.XChaChaDraftPublicVectorReference,
                "kat-source-xchacha-draft-public-reference",
                "kat-provenance-xchacha-draft-public-standards",
                setOf(
                    SkaldVaultV1TestOnlyProviderKatVectorProvenanceStatus.PublicStandardsDocumentReference,
                    SkaldVaultV1TestOnlyProviderKatVectorProvenanceStatus.TestOnlyProvenance,
                    SkaldVaultV1TestOnlyProviderKatVectorProvenanceStatus.NotProductionAuthorizing,
                ),
            ),
            sourceRow(
                SkaldVaultV1TestOnlyProviderKatVectorSourceClass.SkaldCanonicalHeaderHkdfHmacVectorReference,
                "kat-source-skald-canonical-header-hkdf-hmac-reference",
                "kat-provenance-skald-canonical-documentation",
                setOf(
                    SkaldVaultV1TestOnlyProviderKatVectorProvenanceStatus.SkaldCanonicalDocumentationReference,
                    SkaldVaultV1TestOnlyProviderKatVectorProvenanceStatus.DocumentationOnlyProvenance,
                    SkaldVaultV1TestOnlyProviderKatVectorProvenanceStatus.NotProductionAuthorizing,
                ),
            ),
            sourceRow(
                SkaldVaultV1TestOnlyProviderKatVectorSourceClass.SkaldStrictAadSerializationVectorReference,
                "kat-source-skald-strict-aad-reference",
                "kat-provenance-skald-aad-contract",
                setOf(
                    SkaldVaultV1TestOnlyProviderKatVectorProvenanceStatus.SkaldCanonicalDocumentationReference,
                    SkaldVaultV1TestOnlyProviderKatVectorProvenanceStatus.DocumentationOnlyProvenance,
                    SkaldVaultV1TestOnlyProviderKatVectorProvenanceStatus.NotProductionAuthorizing,
                ),
            ),
            sourceRow(
                SkaldVaultV1TestOnlyProviderKatVectorSourceClass.SkaldProviderContractVectorReference,
                "kat-source-skald-provider-contract-reference",
                "kat-provenance-provider-contract",
                setOf(
                    SkaldVaultV1TestOnlyProviderKatVectorProvenanceStatus.SkaldCanonicalDocumentationReference,
                    SkaldVaultV1TestOnlyProviderKatVectorProvenanceStatus.NotProductionAuthorizing,
                ),
            ),
            sourceRow(
                SkaldVaultV1TestOnlyProviderKatVectorSourceClass.SkaldRedactionBehaviorVectorReference,
                "kat-source-skald-redaction-behavior-reference",
                "kat-provenance-redaction-contract",
                setOf(
                    SkaldVaultV1TestOnlyProviderKatVectorProvenanceStatus.SkaldCanonicalDocumentationReference,
                    SkaldVaultV1TestOnlyProviderKatVectorProvenanceStatus.NotProductionAuthorizing,
                ),
            ),
            sourceRow(
                SkaldVaultV1TestOnlyProviderKatVectorSourceClass.SkaldNegativeTamperClassReference,
                "kat-source-skald-negative-tamper-class-reference",
                "kat-provenance-negative-class-catalog",
                setOf(
                    SkaldVaultV1TestOnlyProviderKatVectorProvenanceStatus.SkaldCanonicalDocumentationReference,
                    SkaldVaultV1TestOnlyProviderKatVectorProvenanceStatus.NotProductionAuthorizing,
                ),
            ),
            sourceRow(
                SkaldVaultV1TestOnlyProviderKatVectorSourceClass.SkaldPlatformRuntimeCheckReference,
                "kat-source-skald-platform-runtime-check-reference",
                "kat-provenance-platform-check-catalog",
                setOf(
                    SkaldVaultV1TestOnlyProviderKatVectorProvenanceStatus.TestOnlyProvenance,
                    SkaldVaultV1TestOnlyProviderKatVectorProvenanceStatus.NotProductionAuthorizing,
                ),
            ),
            sourceRow(
                SkaldVaultV1TestOnlyProviderKatVectorSourceClass.SyntheticNonWalletTestOnlyReference,
                "kat-source-synthetic-non-wallet-test-only-reference",
                "kat-provenance-synthetic-non-wallet-test-only",
                setOf(
                    SkaldVaultV1TestOnlyProviderKatVectorProvenanceStatus
                        .SkaldGeneratedNonWalletDeterministicFixtureReference,
                    SkaldVaultV1TestOnlyProviderKatVectorProvenanceStatus.SyntheticTestOnlyGeneratedAtRuntime,
                    SkaldVaultV1TestOnlyProviderKatVectorProvenanceStatus.TestOnlyProvenance,
                    SkaldVaultV1TestOnlyProviderKatVectorProvenanceStatus.NotProductionAuthorizing,
                ),
            ),
        )

    fun currentPositiveVectorRows(): List<SkaldVaultV1TestOnlyProviderKatPositiveVectorRow> =
        SkaldVaultV1TestOnlyProviderKatPositiveVectorClass.entries.map { vectorClass ->
            SkaldVaultV1TestOnlyProviderKatPositiveVectorRow(
                vectorClass = vectorClass,
                referenceId = SkaldVaultV1TestOnlyProviderKatVectorReferenceId(
                    "kat-positive-${vectorClass.name.safeCatalogToken()}-reference",
                ),
                referenceOnly = true,
                rawMaterialPresent = false,
                currentExecutable = false,
                canAuthorizeExecution = false,
                canAuthorizeProduction = false,
                redactionClass = SkaldVaultV1TestOnlyProviderKatVectorRedactionClass.PositiveClassLabelsOnly,
            )
        }

    fun currentNegativeVectorRows(): List<SkaldVaultV1TestOnlyProviderKatNegativeVectorRow> =
        SkaldVaultV1TestOnlyProviderKatNegativeVectorClass.entries.map { vectorClass ->
            SkaldVaultV1TestOnlyProviderKatNegativeVectorRow(
                vectorClass = vectorClass,
                referenceId = SkaldVaultV1TestOnlyProviderKatVectorReferenceId(
                    "kat-negative-${vectorClass.name.safeCatalogToken()}-reference",
                ),
                referenceOnly = true,
                rawMaterialPresent = false,
                currentExecutable = false,
                canAuthorizeExecution = false,
                canAuthorizeProduction = false,
                redactionClass = SkaldVaultV1TestOnlyProviderKatVectorRedactionClass.NegativeClassLabelsOnly,
            )
        }

    fun currentRedactionVectorRows(): List<SkaldVaultV1TestOnlyProviderKatRedactionVectorRow> =
        SkaldVaultV1TestOnlyProviderKatRedactionVectorClass.entries.map { vectorClass ->
            SkaldVaultV1TestOnlyProviderKatRedactionVectorRow(
                vectorClass = vectorClass,
                referenceId = SkaldVaultV1TestOnlyProviderKatVectorReferenceId(
                    "kat-redaction-${vectorClass.name.safeCatalogToken()}-reference",
                ),
                referenceOnly = true,
                rawMaterialPresent = false,
                currentExecutable = false,
                canAuthorizeExecution = false,
                canAuthorizeProduction = false,
                redactionClass = SkaldVaultV1TestOnlyProviderKatVectorRedactionClass.RedactionClassLabelsOnly,
            )
        }

    fun currentPlatformCheckVectorRows(): List<SkaldVaultV1TestOnlyProviderKatPlatformCheckVectorRow> =
        SkaldVaultV1TestOnlyProviderKatPlatformCheckVectorClass.entries.map { vectorClass ->
            SkaldVaultV1TestOnlyProviderKatPlatformCheckVectorRow(
                vectorClass = vectorClass,
                referenceId = SkaldVaultV1TestOnlyProviderKatVectorReferenceId(
                    "kat-platform-${vectorClass.name.safeCatalogToken()}-reference",
                ),
                referenceOnly = true,
                rawMaterialPresent = false,
                currentExecutable = false,
                canAuthorizeExecution = false,
                canAuthorizeProduction = false,
                redactionClass = SkaldVaultV1TestOnlyProviderKatVectorRedactionClass.PlatformClassLabelsOnly,
            )
        }

    fun currentFixtureClassRows(): List<SkaldVaultV1TestOnlyProviderKatFixtureClassRow> =
        SkaldVaultV1TestOnlyProviderKatFixtureClass.entries.map { fixtureClass ->
            SkaldVaultV1TestOnlyProviderKatFixtureClassRow(
                fixtureClass = fixtureClass,
                futureAllowedAsReferenceOnly = fixtureClass.futureAllowedAsReferenceOnly,
                forbidden = fixtureClass.forbidden,
                currentAccepted = false,
                payloadAllowed = false,
                canAuthorizeExecution = false,
                canAuthorizeProduction = false,
                redactionClass = SkaldVaultV1TestOnlyProviderKatVectorRedactionClass.FixtureClassLabelsOnly,
            )
        }

    fun currentAuthorizationLimitRows(): List<SkaldVaultV1TestOnlyProviderKatVectorAuthorizationLimitRow> =
        SkaldVaultV1TestOnlyProviderKatVectorAuthorizationLimit.entries.map { limit ->
            SkaldVaultV1TestOnlyProviderKatVectorAuthorizationLimitRow(
                limit = limit,
                catalogEntryCanAuthorize = false,
                currentCatalogCanAuthorize = false,
                blockers = setOf(
                    SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker.VectorCatalogEvidenceNonAuthorizing,
                    SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker.NoExecutorCallableSurface,
                    SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker.ProviderSelectionDisabledProviderOnly,
                    SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker.ProductionProviderSelectableFalse,
                    SkaldVaultV1TestOnlyProviderKatVectorCatalogBlocker.MainnetDisabled,
                ),
            )
        }

    fun currentFutureWorkRows(): List<SkaldVaultV1TestOnlyProviderKatVectorFutureWorkRow> =
        SkaldVaultV1TestOnlyProviderKatVectorFutureWork.entries.map { work ->
            SkaldVaultV1TestOnlyProviderKatVectorFutureWorkRow(
                work = work,
                requiredBeforeExecutorBranch = true,
                requiredBeforeProductionSelection = true,
                currentBranchCompletesWork = false,
                safeLabel = SkaldVaultV1TestOnlyProviderKatSafeLabel(work.label),
            )
        }

    fun sourceRow(
        sourceClass: SkaldVaultV1TestOnlyProviderKatVectorSourceClass,
        referenceId: String,
        provenanceId: String,
        provenanceStatuses: Set<SkaldVaultV1TestOnlyProviderKatVectorProvenanceStatus>,
    ): SkaldVaultV1TestOnlyProviderKatVectorSourceRow =
        SkaldVaultV1TestOnlyProviderKatVectorSourceRow(
            sourceClass = sourceClass,
            referenceId = SkaldVaultV1TestOnlyProviderKatVectorReferenceId(referenceId),
            provenanceId = SkaldVaultV1TestOnlyProviderKatVectorProvenanceId(provenanceId),
            provenanceStatuses = provenanceStatuses,
            referenceOnly = true,
            rawMaterialPresent = false,
            currentExecutable = false,
            canAuthorizeExecution = false,
            canAuthorizeProduction = false,
            redactionClass = SkaldVaultV1TestOnlyProviderKatVectorRedactionClass.SourceClassLabelsOnly,
        )

    fun String.safeCatalogToken(): String =
        fold(StringBuilder()) { builder, character ->
            if (character.isUpperCase() && builder.isNotEmpty()) {
                builder.append('-')
            }
            builder.append(character.lowercaseChar())
        }.toString()
}
