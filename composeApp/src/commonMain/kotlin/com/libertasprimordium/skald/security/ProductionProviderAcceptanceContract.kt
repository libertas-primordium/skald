package com.libertasprimordium.skald.security

enum class ProductionProviderSuiteModel(
    val label: String,
    val providerAgilityAllowedInV1: Boolean,
) {
    SinglePinnedSuite(
        label = "single pinned production provider suite",
        providerAgilityAllowedInV1 = false,
    ),
}

enum class ProductionProviderPrimitiveRole(val label: String) {
    Kdf("key derivation function"),
    Aead("record AEAD"),
    RuntimeRandomness("runtime cryptographic randomness"),
}

data class ProductionProviderPrimitiveIdentity(
    val role: ProductionProviderPrimitiveRole,
    val implementation: String,
    val algorithm: String,
    val versionOrTemplate: String,
    val artifact: String?,
)

data class ProductionProviderSuiteIdentity(
    val suiteId: String,
    val model: ProductionProviderSuiteModel,
    val kdf: ProductionProviderPrimitiveIdentity,
    val aead: ProductionProviderPrimitiveIdentity,
    val runtimeRandomness: ProductionProviderPrimitiveIdentity,
    val pinnedArtifacts: Set<String>,
)

enum class ProductionProviderAcceptanceGate(val label: String) {
    ExactProviderSuitePinned("exact provider suite id and dependency versions pinned"),
    Argon2idPolicyApproved("Argon2id algorithm, version, and parameter policy approved"),
    Argon2idBoundedCalibrationApproved("bounded Argon2id calibration policy approved"),
    Argon2idKatsPassed("Argon2id known-answer tests pass"),
    Argon2idCalibrationAndMemoryFailureApproved("Argon2id calibration bounds and memory-failure behavior approved"),
    XChaCha20Poly1305PrimitivePinned("XChaCha20-Poly1305 primitive/template/version pinned"),
    AeadKatsPassed("AEAD known-answer tests pass"),
    HeaderCommitmentPolicyApproved("vault-level header commitment policy approved"),
    HkdfSha256KeyExpansionPrimitiveApproved("HKDF-SHA-256 key-expansion primitive policy approved"),
    HmacSha256HeaderCommitmentPrimitiveApproved(
        "HMAC-SHA-256 header-commitment primitive policy approved",
    ),
    KeyExpansionOutputLayoutApproved("key-expansion output layout approved"),
    PrimitiveThreatModelRationaleDocumented("primitive threat model and rationale documented"),
    CanonicalHeaderByteVectorsApproved("canonical header byte vectors approved"),
    HkdfSha256VectorContractApproved("HKDF-SHA-256 vector contract approved"),
    HmacSha256HeaderCommitmentVectorContractApproved(
        "HMAC-SHA-256 header-commitment vector contract approved",
    ),
    CanonicalHeaderEncodingPolicyApproved("canonical vault header encoding policy approved"),
    KeySeparationLabelsPolicyApproved("key-separation labels policy approved"),
    PassphraseEncodingPolicyApproved("passphrase encoding policy approved"),
    Argon2idPassphraseRootDerivationImplemented(
        "Argon2id passphrase-to-root-material derivation implemented",
    ),
    TinkRawKeyFeasibilityApproved("Tink raw-key feasibility approved through public supported APIs"),
    VaultKeyCommitmentHeaderAuthenticationImplemented("vault-level key commitment and header authentication implemented"),
    AeadAadPolicyApproved("AEAD AAD policy approved"),
    TinkNonKeyCommitmentMitigationApproved("Tink non-key-commitment mitigation approved"),
    TamperTestsPassed("tamper tests cover header, ciphertext, nonce, tag, AAD, record metadata, and provider-suite metadata"),
    ProviderLevelKatStrategyApproved("provider-level KAT strategy approved"),
    RandomizedAeadBehavioralKatPolicyApproved("randomized AEAD behavioral KAT policy approved"),
    IntegratedVerificationOrderKatPolicyApproved("integrated verification-order KAT policy approved"),
    VaultContainerContractApproved("vault container contract approved"),
    ManifestContractApproved("manifest contract approved"),
    StaleRecordManifestPolicyApproved("stale-record and rollback manifest policy approved"),
    StoragePolicyContractApproved("storage policy contract approved"),
    PlatformStorageBoundaryContractApproved("platform storage boundary contract approved"),
    AtomicityCrashRecoveryContractApproved("atomicity and crash-recovery contract approved"),
    AtomicWriteStrategyContractApproved("atomic write strategy contract approved"),
    CrashRecoveryContractApproved("crash-recovery contract approved"),
    StorageInterruptionTestContractApproved("storage interruption-test contract approved"),
    StorageAtomicityCrashSimulatorExecuted("in-memory storage atomicity/crash simulator executed"),
    StorageFailureModelContractApproved("storage failure model contract approved"),
    StorageNamespacePathHygieneContractApproved("storage namespace and path hygiene contract approved"),
    StorageLayoutPlanImplementedAndTested("rootless logical storage layout plan implemented and tested"),
    PathContainmentPlannerImplementedAndTested("path-containment planner implemented and tested"),
    PlatformStorageRootContractApproved("platform storage root contract approved"),
    PlatformRootSettingsPolicyApproved("platform root settings policy approved"),
    LinuxCustomRootValidationPolicyImplementedAndTested(
        "Linux custom root validation policy implemented and tested",
    ),
    LinuxRootResolutionPolicyImplementedAndTested(
        "Linux root-resolution evidence policy implemented and tested",
    ),
    PlatformRootResolverBoundaryImplementedAndTested(
        "platform root resolver boundary implemented and tested",
    ),
    PlatformPathConstructionBoundaryImplementedAndTested(
        "platform path-construction boundary implemented and tested",
    ),
    StorageSafetyPreflightBoundaryImplementedAndTested(
        "storage safety preflight boundary implemented and tested",
    ),
    DisabledStorageServiceFacadeImplementedAndTested(
        "disabled storage service facade implemented and tested",
    ),
    PersistenceReadinessGateImplementedAndTested(
        "vault persistence readiness gate implemented and tested",
    ),
    LockSessionLifecycleBoundaryImplementedAndTested(
        "vault lock/session lifecycle boundary implemented and tested",
    ),
    RedactionLeakageBoundaryImplementedAndTested(
        "vault redaction/leakage boundary implemented and tested",
    ),
    PassphrasePolicyBoundaryImplementedAndTested(
        "vault passphrase policy boundary implemented and tested",
    ),
    ClearWipeStrategyBoundaryImplementedAndTested(
        "vault clear/wipe strategy boundary implemented and tested",
    ),
    MigrationCorruptionBoundaryImplementedAndTested(
        "vault migration/corruption boundary implemented and tested",
    ),
    ProviderOperationAuthorizationBoundaryImplementedAndTested(
        "vault provider operation authorization boundary implemented and tested",
    ),
    RuntimeRandomnessAuthorizationBoundaryImplementedAndTested(
        "vault runtime randomness authorization boundary implemented and tested",
    ),
    KdfCalibrationAuthorizationBoundaryImplementedAndTested(
        "vault KDF calibration authorization boundary implemented and tested",
    ),
    SecureStorageAuthorizationBoundaryImplementedAndTested(
        "vault secure-storage authorization boundary implemented and tested",
    ),
    SafePathConstructionContractApproved("safe path-construction contract approved"),
    SymlinkTraversalContractApproved("symlink and filesystem traversal contract approved"),
    StoragePermissionOwnershipContractApproved("storage permission and ownership contract approved"),
    DurabilityCapabilityContractApproved("durability capability contract approved"),
    DurabilityFailClosedPolicyApproved("durability fail-closed policy approved"),
    WarningOnlyDurabilityPersistenceRejected("warning-only durability persistence rejected"),
    SecureStorageBoundaryContractApproved("secure storage boundary contract approved"),
    RollbackLimitationAndAntiRollbackAnchorReviewed(
        "rollback limitation and anti-rollback anchor status reviewed",
    ),
    RuntimeOsSecureRandomEvidenceApproved("OS SecureRandom runtime provider/algorithm evidence approved"),
    UnknownRandomnessProviderStateRejected("unknown randomness/provider state rejected"),
    ForbiddenRandomApisGuarded("forbidden language and ad hoc random APIs guarded"),
    SecureSecretStorageReviewed("secure secret storage reviewed"),
    SecureMetadataStorageReviewed("secure metadata storage reviewed"),
    CrashCorruptionPartialWriteReviewed("crash, corruption, and partial-write behavior reviewed"),
    RedactionLeakageChecksPassed("redaction, logging, and crash-report leakage checks pass"),
    AndroidOptionalWrappingSeparateFromPassphrase("Android optional wrapping remains separate from passphrase recovery"),
    StillDisabledProviderFacadeApproved("still-disabled provider facade approved as metadata-only"),
    ProductionProviderImplementationExists("production provider implementation exists"),
    ReleaseReadinessExcludesDebugTestProviders("release readiness excludes debug and test-only providers"),
}

enum class ProductionProviderAcceptanceEvidenceState(
    val label: String,
    val satisfiesGate: Boolean,
) {
    Satisfied("satisfied", satisfiesGate = true),
    DocumentedModelOnly("documented/model-only", satisfiesGate = false),
    VectorInputsDefinedOutputsPending("vector inputs defined; outputs pending", satisfiesGate = false),
    TestScopeVectorsComplete("test-scope vectors complete", satisfiesGate = false),
    ApprovedForFutureImplementation("approved for future implementation", satisfiesGate = false),
    ImplementedTested("implemented and tested", satisfiesGate = true),
    Missing("missing", satisfiesGate = false),
    Failed("failed", satisfiesGate = false),
    Unsupported("unsupported", satisfiesGate = false),
    Unknown("unknown", satisfiesGate = false),
}

enum class ProductionProviderAcceptanceBlocker(val label: String) {
    MissingGateEvidence("missing acceptance-gate evidence"),
    ModelOnlyGateEvidence("model-only acceptance-gate evidence"),
    PendingVectorEvidence("test vector evidence has pending outputs"),
    TestScopeVectorEvidenceOnly("test vectors are complete only in test scope"),
    FailedGateEvidence("failed acceptance-gate evidence"),
    UnsupportedGateEvidence("unsupported acceptance-gate evidence"),
    UnknownGateEvidence("unknown acceptance-gate evidence"),
    DebugOrTestProviderNotReleaseSelectable("debug or test provider cannot be selectable in release readiness"),
    ProductionProviderSelectionStillDisabled("production provider selection remains disabled"),
    ProductionPersistenceStillDisabled("production vault persistence remains disabled"),
}

enum class ProductionProviderAadBindingField(val label: String) {
    VaultMagicDomainMarker("vault magic/domain marker"),
    VaultFormatVersion("vault format version"),
    ProviderSuiteId("provider suite id"),
    VaultId("vault id"),
    RecordFormatPolicyId("record format policy id/version"),
    AadPolicyId("AAD policy id/version"),
    KeyExpansionPolicyId("key-expansion policy id"),
    HeaderCommitmentPrimitivePolicyId("header-commitment primitive policy id"),
    RecordType("record type"),
    RecordId("record id"),
    RecordVersionOrCounter("record version/counter"),
    IntegrityCriticalRecordMetadata("integrity-critical record metadata"),
    HeaderCommitmentPolicyId("header commitment policy id"),
    CanonicalHeaderCommitmentValueOrIdentifier("canonical header commitment value or stable identifier"),
    StorageNamespace("future storage namespace when relevant"),
}

enum class ProductionProviderAadFailClosedCondition(val label: String) {
    WrongVaultId("wrong vault id"),
    WrongProviderSuiteId("wrong provider suite id"),
    WrongRecordType("wrong record type"),
    WrongRecordId("wrong record id"),
    WrongRecordVersionOrCounter("wrong record version/counter"),
    WrongRecordMetadata("wrong integrity-critical record metadata"),
    WrongAadPolicyVersion("wrong AAD policy version"),
    WrongHeaderCommitmentContext("wrong header commitment context"),
    CiphertextCopiedBetweenVaults("ciphertext copied between vaults"),
    CiphertextCopiedBetweenRecordIds("ciphertext copied between record ids"),
    CiphertextCopiedBetweenRecordTypes("ciphertext copied between record types"),
    ReplayedStaleRecord("replayed stale record where the version/counter policy rejects stale data"),
}

enum class ProductionProviderTamperCoverage(val label: String) {
    Header("vault header"),
    Ciphertext("ciphertext"),
    Nonce("nonce"),
    Tag("authentication tag"),
    Aad("associated data"),
    RecordMetadata("record metadata"),
    ProviderSuiteMetadata("provider-suite metadata"),
}

enum class ProductionProviderWeakDeviceFailureMode(val label: String) {
    FailClosedWithUserMessage("fail closed with a clear user-facing message"),
}

enum class ProductionProviderHeaderCommitmentField(val label: String) {
    VaultMagicDomainMarker("vault magic/domain marker"),
    VaultFormatVersion("vault format version"),
    ProviderSuiteId("provider suite id"),
    KdfAlgorithmId("KDF algorithm id"),
    KdfVersion("KDF version"),
    KdfMemoryParameter("KDF memory parameter"),
    KdfTimeParameter("KDF iteration/time parameter"),
    KdfParallelismParameter("KDF parallelism parameter"),
    SaltLength("salt length"),
    SaltBytes("salt bytes"),
    DerivedRootMaterialLength("derived root material length"),
    VaultId("vault id"),
    PassphraseEncodingPolicyId("passphrase encoding policy id"),
    KeyExpansionPolicyId("key-expansion policy id"),
    KeySeparationPolicyId("key-separation policy id"),
    HeaderCommitmentPrimitivePolicyId("header-commitment primitive policy id"),
    HeaderCommitmentPolicyId("header commitment policy id"),
    AadPolicyId("AAD policy id/version"),
    RecordFormatPolicyId("record format policy id/version"),
    OptionalFeatureFlags("optional feature flags"),
    IntegrityCriticalHeaderMetadata("all integrity-critical header metadata"),
}

enum class ProductionProviderHeaderCommitmentFailClosedCondition(val label: String) {
    HeaderModified("header modified"),
    RequiredHeaderFieldOmitted("required header field omitted"),
    RequiredHeaderFieldDuplicated("required header field duplicated"),
    UnknownFieldInNonExtensibleSection("unknown field in non-extensible section"),
    HeaderNonCanonical("header non-canonical"),
    UnknownSuiteId("unknown suite id"),
    UnsupportedKdfAlgorithmOrVersion("unsupported KDF algorithm/version"),
    UnsupportedKdfParameters("unsupported KDF parameters"),
    UnsupportedPassphraseEncodingPolicy("unsupported passphrase encoding policy"),
    UnsupportedKeySeparationPolicy("unsupported key-separation policy"),
    UnsupportedAadPolicy("unsupported AAD policy"),
    UnsupportedRecordFormatPolicy("unsupported record format policy"),
    UnknownPolicyVersion("unknown policy version"),
    MalformedLength("malformed length"),
    MalformedIntegerEncoding("malformed integer encoding"),
    MalformedUtf8StringEncoding("malformed UTF-8/string encoding"),
    UnsupportedFutureVersion("unsupported future version"),
    CommitmentUnimplemented("key commitment unimplemented"),
}

enum class ProductionProviderConstructionContractStatus(
    val label: String,
    val satisfiesProductionSelectability: Boolean,
) {
    Missing("missing", satisfiesProductionSelectability = false),
    Unknown("unknown", satisfiesProductionSelectability = false),
    DocumentedModelOnly("documented/model-only", satisfiesProductionSelectability = false),
    FailedUnsupported("failed or unsupported", satisfiesProductionSelectability = false),
    ApprovedForFutureImplementation("approved for future implementation", satisfiesProductionSelectability = false),
    ImplementedTested("implemented and tested", satisfiesProductionSelectability = true),
}

enum class ProductionProviderTestVectorContractStatus(
    val label: String,
    val satisfiesProductionSelectability: Boolean,
) {
    Missing("missing", satisfiesProductionSelectability = false),
    Unknown("unknown", satisfiesProductionSelectability = false),
    DocumentedOnly("documented only", satisfiesProductionSelectability = false),
    InputsDefinedOutputsPending("inputs defined; outputs pending", satisfiesProductionSelectability = false),
    VectorsCompleteInTestScope("vectors complete in test scope", satisfiesProductionSelectability = false),
    FailedUnsupported("failed or unsupported", satisfiesProductionSelectability = false),
    ProductionImplementedTested("production implemented and tested", satisfiesProductionSelectability = true),
}

data class ProductionProviderHeaderCommitmentAcceptancePolicy(
    val policyId: String,
    val policyVersion: Int,
    val contractStatus: ProductionProviderConstructionContractStatus,
    val requiredBeforeRecordDecrypt: Boolean,
    val recordDecryptAllowedBeforeVerification: Boolean,
    val commitmentKeyMaterialSeparatedFromRecordAeadKeyMaterial: Boolean,
    val canonicalHeaderFields: Set<ProductionProviderHeaderCommitmentField>,
    val failClosedConditions: Set<ProductionProviderHeaderCommitmentFailClosedCondition>,
    val productionExecutionImplemented: Boolean,
)

enum class ProductionProviderCanonicalHeaderEncodingRule(val label: String) {
    DeterministicAcrossJvmAndAndroid("deterministic across JVM desktop and Android"),
    BigEndianIntegers("all integers use explicit big-endian byte order"),
    ExplicitIntegerWidths("each numeric field has an explicit width"),
    Utf8Strings("strings are encoded as UTF-8"),
    PolicyAndSuiteIdsAsciiConstants("policy and suite identifiers are ASCII constants"),
    LengthPrefixesForVariableFields("variable-length fields use explicit length prefixes"),
    ExplicitFieldOrder("fields are encoded in explicit order"),
    OptionalFieldsExplicitlyEncoded("optional fields have explicit presence/absence encoding"),
    UnknownFieldsRejectedInNonExtensibleSections("unknown fields are rejected in non-extensible sections"),
    VariableLengthMaximums("variable-length fields have explicit maximum lengths"),
    DomainMagicPrefix("bytes include an explicit Skald Vault domain/magic prefix"),
    VersionedHeaderFormat("header format has an explicit version policy"),
    NoDefaultObjectSerialization("default object serialization is forbidden"),
    NoNonCanonicalJson("non-canonical JSON is forbidden for committed bytes"),
    NoUnsortedMapIterationOrder("map iteration order is forbidden unless keys are sorted and encoded"),
    NoPlatformNativeSerialization("platform-native integer/string serialization is forbidden"),
    SingleEncodingPerLogicalHeader("one logical header has exactly one byte encoding"),
    TestVectorsRequiredBeforeSelectability("canonical header byte vectors are required before selectability"),
}

data class ProductionProviderCanonicalHeaderEncodingPolicy(
    val policyId: String,
    val policyVersion: Int,
    val contractStatus: ProductionProviderConstructionContractStatus,
    val byteOrder: String,
    val stringEncoding: String,
    val domainMagic: String,
    val rules: Set<ProductionProviderCanonicalHeaderEncodingRule>,
    val productionSerializerImplemented: Boolean,
)

enum class ProductionProviderKeySeparationLabel(
    val labelValue: String,
    val productionLabel: Boolean,
) {
    RootDomain("skald-vault/v1/root-domain", productionLabel = true),
    HeaderCommitmentKey("skald-vault/v1/header-commitment-key", productionLabel = true),
    RecordAeadKey("skald-vault/v1/record-aead-key", productionLabel = true),
    FutureWrappingMetadata("skald-vault/v1/reserved/wrapping-metadata", productionLabel = false),
    FutureExportMigration("skald-vault/v1/reserved/export-migration", productionLabel = false),
    TestProbeDomain("skald-vault/test-only/raw-key-probe", productionLabel = false),
}

data class ProductionProviderKeySeparationPolicy(
    val policyId: String,
    val policyVersion: Int,
    val contractStatus: ProductionProviderConstructionContractStatus,
    val labels: Set<ProductionProviderKeySeparationLabel>,
    val rootMaterialUsedDirectlyForMultiplePurposes: Boolean,
    val recordAeadAndHeaderCommitmentKeyMaterialSeparated: Boolean,
    val reservedFutureLabelsNotImplemented: Boolean,
    val keyExpansionPrimitiveApproved: Boolean,
    val productionKeyDerivationImplemented: Boolean,
    val unknownUnsupportedPolicyBlocksSelectability: Boolean,
)

enum class ProductionProviderKeyExpansionPrimitive(val label: String) {
    HkdfSha256("HKDF-SHA-256"),
}

data class ProductionProviderKeyExpansionPrimitivePolicy(
    val policyId: String,
    val primitive: ProductionProviderKeyExpansionPrimitive,
    val contractStatus: ProductionProviderConstructionContractStatus,
    val argon2idRemainsPasswordKdf: Boolean,
    val usedOnlyAfterArgon2idRootMaterialExists: Boolean,
    val usedDirectlyOnPassphraseAllowed: Boolean,
    val domainSeparatedByStableAsciiLabels: Boolean,
    val avoidsManualRootMaterialSlicing: Boolean,
    val productionHkdfExecutionImplemented: Boolean,
)

enum class ProductionProviderHeaderCommitmentPrimitive(val label: String) {
    HmacSha256("HMAC-SHA-256"),
}

data class ProductionProviderHeaderCommitmentPrimitivePolicy(
    val policyId: String,
    val primitive: ProductionProviderHeaderCommitmentPrimitive,
    val contractStatus: ProductionProviderConstructionContractStatus,
    val inputDescription: String,
    val usesDerivedHeaderCommitmentKey: Boolean,
    val verifiesBeforeRecordDecrypt: Boolean,
    val successfulRecordDecryptAloneProvesCorrectVaultKey: Boolean,
    val productionHmacExecutionImplemented: Boolean,
    val productionHeaderCommitmentComputationImplemented: Boolean,
)

data class ProductionProviderKeyExpansionOutputLayoutPolicy(
    val contractStatus: ProductionProviderConstructionContractStatus,
    val argon2idRootMaterialBytes: Int,
    val headerCommitmentKeyBytes: Int,
    val recordAeadKeyBytes: Int,
    val recordAeadKeyFeedsTinkRawKeyPath: Boolean,
    val reservedFutureWrappingExportMigrationOutputsImplemented: Boolean,
)

data class ProductionProviderPrimitiveThreatModelPolicy(
    val contractStatus: ProductionProviderConstructionContractStatus,
    val offlineAttackBecomesPassphraseGuessing: Boolean,
    val dependsOnPassphraseEntropyAndArgon2idParameters: Boolean,
    val hkdfAndHmacExpectedNotWeakLinkWhenCorrectlyImplemented: Boolean,
    val liveEndpointCompromiseCovered: Boolean,
    val weakPassphraseCompensatedByHkdfOrHmac: Boolean,
)

data class ProductionProviderCanonicalHeaderVectorContract(
    val status: ProductionProviderTestVectorContractStatus,
    val documentPath: String,
    val logicalFixtureFieldsDefined: Boolean,
    val canonicalFieldOrderDefined: Boolean,
    val byteEncodingRulesDefined: Boolean,
    val finalCanonicalHeaderHexDocumented: Boolean,
    val testScopeEncoderExists: Boolean,
    val productionSerializerImplemented: Boolean,
)

data class ProductionProviderHkdfVectorContract(
    val status: ProductionProviderTestVectorContractStatus,
    val documentPath: String,
    val hash: String,
    val inputKeyingMaterialBytes: Int,
    val saltDefined: Boolean,
    val infoConstructionDefined: Boolean,
    val headerCommitmentInfoDefined: Boolean,
    val recordAeadInfoDefined: Boolean,
    val outputBytesPerPurpose: Int,
    val expectedOutputsDocumented: Boolean,
    val testScopeHkdfExecutionExists: Boolean,
    val productionHkdfExecutionImplemented: Boolean,
)

data class ProductionProviderHmacHeaderCommitmentVectorContract(
    val status: ProductionProviderTestVectorContractStatus,
    val documentPath: String,
    val hash: String,
    val hmacKeySource: String,
    val messageSource: String,
    val expectedTagDocumented: Boolean,
    val testScopeHmacExecutionExists: Boolean,
    val productionHmacExecutionImplemented: Boolean,
    val productionHeaderCommitmentExecutionImplemented: Boolean,
)

enum class ProductionProviderDeterministicKatVector(val label: String) {
    PassphrasePolicyNormalizationVector("passphrase policy normalization vector"),
    Argon2idRootMaterialFixture("Argon2id fixed non-secret root-material fixture"),
    CanonicalHeaderByteVector("canonical header byte vector"),
    HkdfInfoByteVectors("HKDF info byte vectors"),
    HkdfHeaderCommitmentKeyVector("HKDF header commitment key vector"),
    HkdfRecordAeadKeyVector("HKDF record AEAD key vector"),
    HmacHeaderCommitmentVector("HMAC header commitment vector"),
    StrictAadByteVector("strict AAD byte vector"),
}

enum class ProductionProviderRandomizedAeadBehavioralKatCheck(val label: String) {
    EncryptDecryptRoundTrip("encrypt then decrypt fixed non-secret plaintext with same key and AAD"),
    CiphertextNotTreatedAsDeterministic("ciphertext is not treated as deterministic"),
    WrongAadFails("wrong AAD fails"),
    WrongKeyFails("wrong key fails"),
    TamperedCiphertextFails("tampered ciphertext fails"),
    TamperedTagFails("tampered tag fails"),
    WrongVaultIdFails("wrong vault id in AAD fails"),
    WrongRecordIdFails("wrong record id in AAD fails"),
    WrongRecordTypeFails("wrong record type in AAD fails"),
    WrongRecordVersionCounterFails("wrong record version/counter in AAD fails"),
    WrongProviderSuiteIdFails("wrong provider suite id in AAD fails"),
    WrongHeaderCommitmentContextFails("wrong header commitment context in AAD fails"),
}

data class ProductionProviderLevelKatStrategyPolicy(
    val policyId: String,
    val contractStatus: ProductionProviderConstructionContractStatus,
    val deterministicVectorsRequired: Set<ProductionProviderDeterministicKatVector>,
    val randomizedAeadBehavioralChecksRequired: Set<ProductionProviderRandomizedAeadBehavioralKatCheck>,
    val fixedCiphertextHexRequiredForRandomizedAead: Boolean,
    val deterministicAadHexRequired: Boolean,
    val providerLevelKatsMustRunOnDesktopJvm: Boolean,
    val providerLevelKatsMustRunOnAndroid: Boolean,
    val providerKatCompletionImpliesStorageApproval: Boolean,
    val stillDisabledProviderIntegrationHarnessImplemented: Boolean,
    val stillDisabledProviderLevelKatExecutionImplemented: Boolean,
    val productionProviderKatExecutionImplemented: Boolean,
)

data class ProductionProviderRandomizedAeadBehavioralKatPolicy(
    val policyId: String,
    val contractStatus: ProductionProviderConstructionContractStatus,
    val primitive: EncryptedVaultAeadAlgorithm,
    val tinkChoosesNonceInternally: Boolean,
    val fixedCiphertextHexRequired: Boolean,
    val deterministicAadHexRequired: Boolean,
    val behavioralChecksRequired: Set<ProductionProviderRandomizedAeadBehavioralKatCheck>,
    val publicDeterministicNonceTestModeApproved: Boolean,
    val stillDisabledBehavioralKatExecutionImplemented: Boolean,
    val productionProviderBehavioralKatExecutionImplemented: Boolean,
)

enum class ProductionProviderIntegratedVerificationOrderKatStep(val label: String) {
    ValidatePassphrasePolicy("validate passphrase policy"),
    DeriveArgon2idRootMaterial("derive Argon2id root material from fixed non-secret fixture"),
    DeriveHkdfSubkeys("derive HKDF subkeys"),
    CanonicalizeHeaderBytes("canonicalize header bytes"),
    VerifyHmacHeaderCommitment("verify HMAC header commitment"),
    ConstructRecordAeadAfterHeaderCommitment("construct/use record AEAD only after header commitment"),
    SerializeStrictAad("serialize strict AAD"),
    DecryptRecord("decrypt record"),
    RejectRecordDecryptWhenHeaderCommitmentFails("reject record decrypt when header commitment verification fails"),
}

data class ProductionProviderIntegratedVerificationOrderKatPolicy(
    val policyId: String,
    val contractStatus: ProductionProviderConstructionContractStatus,
    val orderedSteps: List<ProductionProviderIntegratedVerificationOrderKatStep>,
    val headerCommitmentMustPrecedeRecordDecrypt: Boolean,
    val recordDecryptRejectedWhenHeaderCommitmentFails: Boolean,
    val stillDisabledProviderIntegrationHarnessImplemented: Boolean,
    val stillDisabledVerificationOrderKatExecutionImplemented: Boolean,
    val fullProviderIntegrationImplemented: Boolean,
    val productionVerificationOrderKatsImplemented: Boolean,
)

enum class ProductionProviderStaleRecordManifestBinding(val label: String) {
    VaultId("vault id"),
    ProviderSuiteId("provider suite id"),
    HeaderCommitmentContext("header commitment context"),
    ManifestPolicyIdVersion("manifest policy id/version"),
    RecordNamespace("record namespace"),
    LatestTrustedRecordVersionCounterByRecordId("latest trusted record version/counter per record id"),
}

enum class ProductionProviderStaleRecordManifestRequirement(val label: String) {
    TracksLatestTrustedCounterPerRecordId("future manifest tracks latest trusted record version/counter per record id"),
    IntegrityProtectedManifest("future manifest is integrity-protected"),
    BoundToVaultProviderHeaderPolicyNamespace("future manifest binds vault id, provider suite id, header commitment context, policy, and namespace"),
    AtomicUpdateOrCrashSafeRecovery("future manifest is updated atomically with records or has crash-safe recovery"),
    RejectOrQuarantineLowerCounter("future manifest rejects or quarantines lower version/counter records"),
    RejectOrQuarantineConflictingDuplicateRecordId("future manifest rejects or quarantines duplicate record ids with conflicting latest counters"),
    ConflictHandlingBeforeSyncOrImport("conflict handling is defined before sync or import behavior"),
    NoGlobalRollbackClaimWithoutAnchor("no global rollback-resistance claim without external or trusted monotonic anchor"),
    NoManifestReadWriteInThisBranch("no manifest storage reader or writer exists in this branch"),
}

data class ProductionProviderStaleRecordManifestPolicy(
    val policyId: String,
    val manifestStoragePolicyId: String,
    val contractStatus: ProductionProviderConstructionContractStatus,
    val recordVersionCounterBoundIntoAad: Boolean,
    val bindings: Set<ProductionProviderStaleRecordManifestBinding>,
    val requirements: Set<ProductionProviderStaleRecordManifestRequirement>,
    val manifestParserImplemented: Boolean,
    val manifestWriterImplemented: Boolean,
    val staleRecordDecisionPolicyImplemented: Boolean,
    val manifestReadWriteImplemented: Boolean,
    val storageIndexReadWriteImplemented: Boolean,
    val staleRecordEnforcementImplemented: Boolean,
    val fullLocalDirectoryRollbackResistanceClaimed: Boolean,
    val externalOrTrustedMonotonicAnchorDesigned: Boolean,
    val antiRollbackAnchorRequiredForGlobalRollbackResistance: Boolean,
    val providerSelectabilityBlockedUntilImplementedAndTested: Boolean,
)

enum class ProductionProviderVaultContainerField(val label: String) {
    VaultMagicDomainMarker("vault magic/domain marker"),
    VaultFormatVersion("vault format version"),
    CanonicalVaultHeaderBytesOrReconstructableFields(
        "canonical vault header bytes or fields sufficient to reconstruct them",
    ),
    HeaderCommitmentTag("header commitment tag"),
    ProviderSuiteId("provider suite id"),
    KdfAlgorithmVersionParameters("KDF algorithm, version, and parameters"),
    SaltLengthAndBytes("salt length and salt bytes"),
    DerivedRootMaterialLength("derived root material length"),
    VaultId("vault id"),
    PassphrasePolicyId("passphrase policy id"),
    KeyExpansionPolicyId("key-expansion policy id"),
    KeySeparationPolicyId("key-separation policy id"),
    HeaderCommitmentPrimitivePolicyId("header commitment primitive policy id"),
    HeaderCommitmentPolicyId("header commitment policy id"),
    RecordFormatPolicyIdVersion("record format policy id/version"),
    AadPolicyIdVersion("AAD policy id/version"),
    ManifestPolicyIdVersion("manifest policy id/version"),
    StoragePolicyIdVersion("storage policy id/version"),
    FeatureFlags("feature flags"),
    EncryptedRecordsSectionOrReferences("encrypted records section or record references"),
    ManifestSectionOrReference("manifest section or manifest reference"),
    IntegrityCriticalPreUnlockMetadata("integrity-critical metadata needed before unlock"),
}

enum class ProductionProviderVaultContainerRequirement(val label: String) {
    NoPlaintextSecretsInContainer("plaintext secrets never appear in the container"),
    NoRootMaterialSubkeysPassphrasesPlaintextOrTinkKeysets(
        "root material, subkeys, passphrases, plaintext records, and Tink keysets never appear",
    ),
    HeaderCommitmentBeforeRecordAead("header commitment is verified before record AEAD use"),
    StrictAadForRecordAead("record AEAD uses strict AAD"),
    RandomizedCiphertextNotDeterministic("record ciphertext may be randomized and is not a fixed vector"),
    RejectMalformedDuplicatedUnknownUnsupportedNonCanonicalHeader(
        "future parser rejects malformed, duplicated, unknown, unsupported, or non-canonical header evidence",
    ),
    TypedFailuresForMalformedUntrustedInput("future parser returns typed failures for untrusted malformed input"),
    NoPersistenceInThisBranch("no persistence implementation exists in this branch"),
}

enum class ProductionProviderManifestField(val label: String) {
    ManifestMagicDomainMarker("manifest magic/domain marker"),
    ManifestPolicyIdVersion("manifest policy id/version"),
    VaultId("vault id"),
    ProviderSuiteId("provider suite id"),
    HeaderCommitmentContext("header commitment context"),
    StorageNamespace("storage namespace"),
    RecordNamespace("record namespace"),
    LatestTrustedRecordVersionCounterPerRecordId("latest trusted record version/counter per record id"),
    RecordTypePerRecordId("record type per record id"),
    RecordLocationReference("record location/reference"),
    TombstoneDeletionState("tombstone/deletion state"),
    ManifestSequenceVersion("manifest sequence/version"),
    CrashRecoveryMetadata("integrity-critical metadata needed for crash recovery"),
}

enum class ProductionProviderStorageAtomicityRequirement(val label: String) {
    AtomicAtContainerManifestBoundary("writes are atomic at the vault-container/manifest boundary"),
    PartialWritesRejected("partial writes are not treated as valid vault state"),
    ManifestAndRecordDurabilityOrSafePreviousState(
        "manifest update and record write both become durable or recovery chooses a safe previous state",
    ),
    NoNewerRecordWithoutManifestAuthority("crash recovery does not accept a newer record without manifest authority"),
    NoOrphanOrResurrectRecords("crash recovery does not silently orphan or resurrect records"),
    StartupRecoveryValidatesConsistencyBeforeUnlockSuccess(
        "startup recovery validates manifest/header/record consistency before unlock success",
    ),
    PlatformStrategyRequiredBeforePersistenceApproval(
        "temp-file, journal, rename, fsync, or equivalent strategy required before persistence approval",
    ),
    InterruptionTestsRequiredAtEachWritePhase("interruption tests are required at each write phase"),
    NoWriteRecoveryImplementationInThisBranch("no atomic write or recovery implementation exists in this branch"),
}

enum class ProductionProviderStorageBoundaryAllowedBytes(val label: String) {
    EncryptedContainerBytes("encrypted container bytes"),
    ManifestBytes("manifest bytes"),
    StorageIndexMetadata("storage index metadata"),
    CrashRecoveryTemporaryState("crash-recovery temporary state"),
    NonSecretStorageMetadata("non-secret storage metadata"),
}

enum class ProductionProviderStorageBoundaryForbiddenMaterial(val label: String) {
    Passphrases("passphrases"),
    NormalizedPassphraseBytes("normalized passphrase bytes"),
    Argon2idRootMaterial("Argon2id root material"),
    HkdfSubkeys("HKDF subkeys"),
    PlaintextRecordBodies("plaintext record bodies"),
    TinkKeysets("Tink keysets"),
    WalletSeedMaterial("wallet seed material"),
    PrivateKeys("private keys"),
    NostrSecrets("Nostr secrets"),
    CashuProofs("Cashu proofs"),
    BackendCredentials("backend credentials"),
}

enum class ProductionProviderStorageBoundaryRequirement(val label: String) {
    AlreadyEncryptedOrNonSecretBytesOnly("storage accepts only already-encrypted or non-secret bytes"),
    StorageLayerNotEncryptionBoundary("storage layer is not responsible for encryption"),
    StorageLayerNotPassphraseBoundary("storage layer is not responsible for passphrase handling"),
    DoNotLogStoredBytesOrSecretIdentifyingPaths("storage must not log stored bytes or secret-identifying paths"),
    TypedFailuresForExpectedIoRecoveryConditions("storage must return typed failures for expected I/O and recovery conditions"),
    FailClosedOnUnknownState("storage must fail closed on unknown state"),
    NoStorageImplementationInThisBranch("no platform storage implementation exists in this branch"),
}

enum class ProductionProviderAtomicWritePhase(val label: String) {
    BeforeTempContainerWrite("before temporary container write"),
    DuringTempContainerWrite("during temporary container write"),
    AfterTempContainerWriteBeforeValidation("after temporary container write before validation"),
    AfterTempContainerValidationBeforeTempManifestWrite(
        "after temporary container validation before temporary manifest write",
    ),
    DuringTempManifestWrite("during temporary manifest write"),
    AfterTempManifestWriteBeforeCommit("after temporary manifest write before commit"),
    AfterCommittingContainerBeforeManifest("after committing container before committing manifest"),
    AfterCommittingManifestBeforeCleanup("after committing manifest before cleanup"),
    DuringCleanupOldOrTempState("during cleanup of old or temporary state"),
    StartupRecovery("during startup recovery"),
}

enum class ProductionProviderAtomicWriteRequirement(val label: String) {
    WriteContainerBytesToTemporaryLocation("write new record/container bytes to a temporary location"),
    WriteManifestIndexBytesToTemporaryLocation("write new manifest/index bytes to a temporary location"),
    ValidateWrittenBytesBeforeCommit("validate written bytes before commit"),
    DurableCommitInSafeOrder("durably commit container/record and manifest/index bytes in a safe order"),
    AtomicReplaceWhereAvailable("use atomic replace semantics where available"),
    SyncParentDirectoryWhereSupported("sync parent directory or equivalent durability primitive where supported"),
    RetainPreviousKnownGoodUntilCommitComplete("retain previous known-good state until the new state is fully committed"),
    RemoveOrQuarantineIncompleteTemporaryState("remove or quarantine incomplete temporary state after recovery"),
    NeverAcceptNewerRecordWithoutManifestAuthority("never accept a newer record without manifest authority"),
    NeverAcceptManifestPointingToMissingMalformedData("never accept a manifest pointing to missing or malformed data"),
    NeverSilentlyResurrectRecords("never silently resurrect records after a crash"),
    NeverSilentlyOrphanRecords("never silently orphan records after a crash"),
    TypedRecoveryDecisions("return typed recovery decisions"),
    DesktopFilesystemStrategyRequiresReview("desktop filesystem strategy requires review before persistence approval"),
    AndroidAppPrivateFilesystemStrategyRequiresReview(
        "Android app-private filesystem strategy requires review before persistence approval",
    ),
    DatabaseStrategyRequiresSeparateReview("future database-backed strategy requires separate review"),
    UnsupportedPlatformFailsClosed("unsupported platform storage behavior fails closed"),
    NoAtomicWriteImplementationInThisBranch("no atomic write implementation exists in this branch"),
}

enum class ProductionProviderCrashRecoveryCheck(val label: String) {
    InspectStableCommittedState("inspect stable committed state"),
    InspectTemporaryInProgressState("inspect temporary or in-progress state"),
    ValidateContainerParserOutput("validate container parser output"),
    ValidateManifestParserOutput("validate manifest parser output"),
    ValidateManifestReferencesAgainstAvailableData("validate manifest references against available records/container data"),
    ApplyStaleRecordPolicyAgainstManifestState("apply stale-record policy against manifest state"),
    ChooseSafePreviousStateForIncompleteNewerState("choose safe previous state for incomplete newer state"),
    QuarantineInconsistentState("quarantine inconsistent state that cannot be safely accepted"),
    UserFacingUnrecoverableCorruptionModelRequired("require user-facing model for unrecoverable corruption"),
    TypedFailuresNoUncontrolledExceptions("avoid uncontrolled exceptions for expected corruption/interruption cases"),
}

enum class ProductionProviderCrashRecoveryFailClosedState(val label: String) {
    MissingManifestWhenRequired("missing manifest when manifest is required"),
    ManifestReferencesMissingRecordOrContainerData("manifest references missing record/container data"),
    RecordOrContainerDataWithoutManifestAuthority("record/container data exists without manifest authority"),
    MalformedManifest("malformed manifest"),
    MalformedContainer("malformed container"),
    ManifestContainerVaultIdMismatch("manifest/container vault id mismatch"),
    ProviderSuiteMismatch("provider suite mismatch"),
    HeaderCommitmentContextMismatch("header commitment context mismatch"),
    StorageNamespaceMismatch("storage namespace mismatch"),
    DuplicateLatestRecords("duplicate latest records"),
    ConflictingCounters("conflicting counters"),
    TruncatedTemporaryState("truncated temporary state"),
    UnknownRecoveryState("unknown recovery state"),
}

enum class ProductionProviderStorageFailureCategory(val label: String) {
    StorageUnavailable("storage unavailable"),
    PermissionDenied("permission denied"),
    ReadFailed("read failed"),
    WriteFailed("write failed"),
    DurabilitySyncUnsupported("durability sync unsupported"),
    DurabilitySyncFailed("durability sync failed"),
    AtomicReplaceUnsupported("atomic replace unsupported"),
    AtomicReplaceFailed("atomic replace failed"),
    TempStateIncomplete("temporary state incomplete"),
    ManifestMissing("manifest missing"),
    ManifestMalformed("manifest malformed"),
    ContainerMalformed("container malformed"),
    ManifestContainerMismatch("manifest/container mismatch"),
    RecordMissing("record missing"),
    RecordMalformed("record malformed"),
    StaleRecordDetected("stale record detected"),
    DuplicateRecordConflict("duplicate record conflict"),
    ConflictingCounter("conflicting counter"),
    RecoveryQuarantineRequired("recovery quarantine required"),
    RecoveryUserActionRequired("recovery user action required"),
    UnknownStorageState("unknown storage state"),
    PlatformRootUnavailable("platform root unavailable"),
    PlatformRootUnsafe("platform root unsafe"),
    PlatformRootUnreviewed("platform root unreviewed"),
    PathConstructionUnsupported("path construction unsupported"),
    PathContainmentFailed("path containment failed"),
    PathSegmentRejected("path segment rejected"),
    SymlinkStateUnknown("symlink state unknown"),
    SymlinkRejected("symlink rejected"),
    PermissionStateUnknown("permission state unknown"),
    UnsafePermissions("unsafe permissions"),
    DurabilityCapabilityUnknown("durability capability unknown"),
    DurabilityCapabilityInsufficient("durability capability insufficient"),
    WarningOnlyDurabilityRejected("warning-only durability rejected"),
    UserConsentDurabilityOverrideRejected("user consent durability override rejected"),
    EquivalentSafeStrategyUnreviewed("equivalent safe strategy unreviewed"),
    ExternalStorageRejected("external storage rejected"),
    UserPathRejected("user path rejected"),
}

enum class ProductionProviderStorageNamespacePathRule(val label: String) {
    StableAsciiNamespaceIds("storage namespace ids are stable ASCII constants or validated safe identifiers"),
    VaultIdsEncodedBeforePathUse("vault ids are encoded before any future path use"),
    UserControlledStringsNeverBecomePaths("user-controlled strings never become filesystem paths"),
    NoPathTraversal("path traversal is forbidden"),
    NoAbsoluteUserSuppliedPaths("absolute user-supplied paths are forbidden"),
    SymlinkBehaviorRequiresReview("symlink behavior requires review before implementation"),
    NoSecretValuesInPathNames("secret values must not appear in path names"),
    NoWalletLabelsOrNotesInPathNames("wallet labels and note text must not appear in path names"),
    FutureImplementationSelectsReviewedAppPrivateRoot(
        "future implementation selects a reviewed platform-specific app-private root",
    ),
    NoPathConstructionInThisBranch("no path construction is implemented in this branch"),
}

enum class ProductionProviderStorageLayoutPlanRule(val label: String) {
    RootlessRelativeSegmentLists("layout locations are rootless relative segment lists"),
    NoPlatformRootInLayout("layout does not include a platform root"),
    NoAbsolutePathInLayout("layout does not include absolute paths"),
    NoFilesystemObjectInLayout("layout does not return File, Path, Uri, or platform filesystem objects"),
    UsesValidatedNamespacePolicy("layout validates the storage namespace/path policy constants"),
    UsesEncodedVaultSegments("layout uses encoded vault storage segments"),
    UsesEncodedRecordSegments("layout uses encoded record storage segments"),
    UsesEncodedManifestSegment("layout uses the encoded manifest storage segment"),
    StableArtifactSegmentsPassSafeValidation("stable artifact segments pass safe-segment validation"),
    IncludesCurrentContainer("layout includes current container location"),
    IncludesCurrentManifest("layout includes current manifest location"),
    IncludesCurrentStorageIndex("layout includes current storage index location"),
    IncludesRecordArtifacts("layout includes record artifact locations by encoded record id"),
    IncludesTemporaryArtifacts("layout includes temporary container, manifest, and storage-index locations"),
    IncludesQuarantineAndRecoveryArtifacts("layout includes quarantine and recovery metadata locations"),
    LayoutOutputIsNotPlatformPath("layout output is not a platform path"),
    FuturePathConstructionRequiresReviewedRoot(
        "future path construction must join layout segments under a reviewed platform root",
    ),
    FuturePathConstructionRequiresContainmentCheck(
        "future path construction must prove containment under the reviewed root",
    ),
    NoStorageImplementationInThisBranch("no storage implementation exists in this branch"),
}

enum class ProductionProviderPathContainmentPlannerRule(val label: String) {
    ReviewedRootTokensOnly("planner accepts reviewed root tokens only"),
    AndroidAppPrivateInternalRootTokenModeled("Android app-private internal root token is modeled"),
    DesktopAppControlledUserDataRootTokenModeled("desktop app-controlled user-data root token is modeled"),
    TestOnlyReviewedRootTokenModeled("test-only reviewed root token is modeled"),
    ExternalSharedRootsRejected("external or shared roots are rejected"),
    UserPathRootsRejected("user-selected path roots are rejected"),
    UnknownUnreviewedUnsafeRootsRejected("unknown, unreviewed, or unsafe root tokens are rejected"),
    PlannedLocationsRootTokenBound("planned locations are bound to the reviewed root token"),
    PlannedLocationsUseLayoutPolicyId("planned locations carry the storage layout policy id"),
    PlannedLocationsUseArtifactKind("planned locations carry a typed artifact kind"),
    PlannedLocationsUseSafeRelativeSegments("planned locations use validated safe relative segments"),
    PlannedLocationsAreNotPlatformPaths("planned locations are not platform paths"),
    SegmentLevelContainmentOnly("planner proves segment-level containment only"),
    ActualRootResolutionAbsent("actual platform root resolution is absent"),
    ActualPathConstructionAbsent("actual path construction is absent"),
    RealContainmentChecksAbsent("real platform path containment checks are absent"),
    SymlinkPermissionDurabilityChecksAbsent("symlink, permission, and durability checks are absent"),
    NoStorageImplementationInThisBranch("no storage implementation exists in this branch"),
}

enum class ProductionProviderPlatformStorageRootRule(val label: String) {
    AndroidAppPrivateInternalStorageRequired("Android vault storage uses app-private internal storage"),
    AndroidExternalSharedStorageRejected("Android external or shared storage is not approved for v1"),
    AndroidUserSelectedArbitraryPathsRejected("Android user-selected arbitrary paths are not approved for v1"),
    AndroidRootResolutionPlatformOwned("Android root resolution is platform-owned and not user-string-controlled"),
    AndroidBackupRestoreBehaviorMustBeDocumented(
        "Android backup and restore behavior must be documented before persistence approval",
    ),
    AndroidUninstallDataDeletionMustBeDocumented(
        "Android uninstall and user data deletion behavior must be documented before persistence approval",
    ),
    DesktopAppControlledUserDataLocationRequired(
        "desktop vault storage uses an app-controlled user-data location",
    ),
    DesktopOsKeyringsNotPrimaryVaultStorage(
        "desktop OS keyrings are not primary encrypted vault storage",
    ),
    DesktopUserSelectedArbitraryPathsRequireReview(
        "desktop user-selected arbitrary paths require later explicit review",
    ),
    DesktopRootAvoidsLabelsTextAndSecrets(
        "desktop root selection must avoid user labels, note text, and secrets in paths",
    ),
    DesktopPermissionsBackupDurabilityReviewRequired(
        "desktop root choice requires permissions, backup, and durability review",
    ),
    SharedNoSecretValuesInRootOrChildSegments("no secret values appear in root paths or child segments"),
    SharedNoRawUserControlledStrings("no raw user-controlled strings appear in root paths or child segments"),
    SharedNoRawVaultIdText("raw vault id text is not used without safe encoding"),
    SharedNoRawRecordIdText("raw record id text is not used without safe encoding"),
    SharedNoWalletLabelsOrNoteText("wallet labels, account labels, and note text are not used in paths"),
    SharedNoAbsoluteUserSuppliedPath("absolute user-supplied paths are rejected"),
    SharedNoPathTraversal("path traversal is rejected"),
    SharedNoSymlinkAssumptionsBeforeReview("symlink-following assumptions require review"),
    NoPlatformRootResolutionInThisBranch("no platform root resolution is implemented in this branch"),
}

enum class ProductionProviderPlatformRootSettingsRule(val label: String) {
    AndroidAppPrivateInternalRootRequired("Android app-private internal storage is required for v1"),
    AndroidExternalStorageRejected("Android external or shared storage is rejected for v1"),
    AndroidUserSelectedRootRejected("Android arbitrary user-selected roots are rejected for v1"),
    AndroidRootResolutionUnimplemented("Android root resolution is unimplemented"),
    AndroidBackupBehaviorUnreviewed("Android backup behavior remains unreviewed"),
    AndroidUninstallDataDeletionUnreviewed("Android uninstall and data-deletion behavior remains unreviewed"),
    LinuxDefaultUserDataRootPolicy("Linux default root uses a user-data location policy"),
    LinuxLocalShareFallbackPolicy("Linux default convention falls back to ~/.local/share/"),
    LinuxXdgStyleUserDataLocationFutureOnly("Linux XDG-style user-data resolution is future-only"),
    LinuxCustomRootSettingsPlanned("Linux custom root is a planned Settings option"),
    LinuxCustomRootValidationPolicyImplemented("Linux custom root static validation policy is implemented"),
    LinuxCustomRootUnimplemented("Linux custom root is unimplemented"),
    LinuxCustomRootUnreviewed("Linux custom root is unreviewed"),
    UserConfiguredRootRequiresValidation("user-configured roots require validation before use"),
    LinuxCustomRootRequiresContainmentReview("Linux custom roots require containment review"),
    LinuxCustomRootRequiresSymlinkReview("Linux custom roots require symlink review"),
    LinuxCustomRootRequiresPermissionReview("Linux custom roots require permission review"),
    LinuxCustomRootRequiresDurabilityReview("Linux custom roots require durability review"),
    LinuxCustomRootCannotWeakenVaultPolicies("Linux custom roots cannot weaken vault safety policies"),
    OsKeyringPrimaryStorageRejected("OS keyrings are rejected as primary vault storage"),
    OsKeyringPassphraseStorageRejected("OS keyring passphrase storage is rejected"),
    PasswordManagerIntegrationRejected("Skald-managed password-manager integration is rejected"),
    PassphraseFirstRequired("passphrase-first remains the default vault authority"),
    ExternalPasswordManagersOutsideSkald("manual external password-manager use is outside Skald"),
    SettingsUiUnimplemented("Settings UI is unimplemented"),
    SettingsPersistenceUnimplemented("settings persistence is unimplemented"),
    PlatformRootResolutionAbsent("platform root resolution is absent"),
    PlatformPathConstructionAbsent("platform path construction is absent"),
    NoStorageImplementationInThisBranch("no storage implementation exists in this branch"),
}

enum class ProductionProviderLinuxCustomRootValidationRule(val label: String) {
    StaticStringValidationOnly("accepted candidates are static string validation evidence only"),
    AbsoluteLinuxCandidateRequired("candidate strings must be intended absolute Linux paths"),
    RelativePathsRejected("relative custom root strings are rejected"),
    TildeExpansionRejected("tilde custom root strings are rejected"),
    SharedSystemRuntimeAndTempRootsRejected(
        "shared, system, runtime, removable-media, and temporary roots are rejected",
    ),
    PathTraversalRejected("path traversal is rejected"),
    EmptySegmentsRejected("empty path segments are rejected"),
    AmbiguousCharactersRejected("ambiguous shell and unsupported characters are rejected"),
    SecretMaterialAndLabelsRejected("secret-looking material and user labels are rejected"),
    UriAndWindowsPrefixesRejected("URI-like and Windows drive prefixes are rejected"),
    AcceptedCandidateDoesNotResolvePath("accepted candidate does not resolve a platform root"),
    AcceptedCandidateDoesNotProveFilesystemState("accepted candidate does not prove existence or safety"),
    SettingsUiAndPersistenceStillRequired("Settings UI and settings persistence are still required"),
    ContainmentSymlinkPermissionDurabilityReviewStillRequired(
        "containment, symlink, permission, and durability review are still required",
    ),
    NoStorageImplementationInThisBranch("no storage implementation exists in this branch"),
}

enum class ProductionProviderLinuxRootResolutionRule(val label: String) {
    CallerSuppliedStaticEvidenceOnly("root-resolution evidence consumes caller-supplied static evidence only"),
    DefaultUserDataEvidenceCanBePlanned("caller-supplied default user-data evidence can be planned"),
    CustomRootValidationResultIntegrated("custom-root validation results are integrated"),
    RejectedCustomRootValidationBlocksResolutionEvidence(
        "rejected custom-root validation blocks root-resolution evidence",
    ),
    AcceptedCustomRootValidationStillDisabled(
        "accepted custom-root validation produces still-disabled planned evidence only",
    ),
    RootTokensAreNotPlatformPaths("root tokens are not platform paths"),
    DiagnosticsRedactRootStrings("diagnostics redact root strings by default"),
    DoesNotReadEnvironmentOrSystemProperties("policy does not read environment variables or system properties"),
    DoesNotResolveLinuxUserDataRoot("policy does not resolve the Linux user-data root"),
    DoesNotConstructPlatformPaths("policy does not construct platform paths"),
    DoesNotTouchFilesystem("policy does not call filesystem APIs"),
    DoesNotPersistSettings("policy does not persist Settings"),
    DoesNotProveExistenceContainmentSymlinkPermissionOwnershipDurability(
        "policy does not prove existence, containment, symlink, permission, ownership, or durability state",
    ),
    DoesNotEnablePersistenceOrProviderSelection("policy does not enable persistence or provider selection"),
}

enum class ProductionProviderPlatformRootResolverRule(val label: String) {
    EvidenceOnly("resolver returns evidence only"),
    AndroidAppPrivateInternalEvidenceOnly("Android v1 accepts app-private internal root evidence only"),
    AndroidExternalSharedRootsRejected("Android external and shared roots are rejected"),
    AndroidUserSelectedRootsRejected("Android user-selected roots are rejected"),
    LinuxDefaultSnapshotEvidenceOnly("Linux default roots use supplied evidence snapshots only"),
    LinuxCustomRootValidationIntegrated("Linux custom-root validation evidence is integrated"),
    RootTokensAreNotPlatformPaths("root tokens are not platform paths"),
    DiagnosticsRedactRootStrings("diagnostics redact raw root strings by default"),
    DoesNotCreateDirectories("resolver does not create directories"),
    DoesNotReadOrWriteFiles("resolver does not read or write files"),
    DoesNotPersistSettings("resolver does not persist Settings"),
    DoesNotProveExistenceContainmentSymlinkPermissionOwnershipDurability(
        "resolver does not prove existence, containment, symlink, permission, ownership, or durability state",
    ),
    DoesNotEnablePersistenceOrProviderSelection("resolver does not enable persistence or provider selection"),
}

enum class ProductionProviderPlatformPathConstructionRule(val label: String) {
    EvidenceOnly("path-construction boundary returns planned evidence only"),
    ConsumesRootResolverEvidence("boundary consumes platform root resolver evidence"),
    ConsumesLogicalStorageLayout("boundary consumes logical storage layout evidence"),
    UsesPathContainmentPlanner("boundary reuses path-containment planner segment proof"),
    PlannedLocationsAreTyped("planned artifact locations are typed"),
    PlannedLocationsAreRedacted("planned artifact locations redact root evidence by default"),
    PlannedLocationsAreNotPlatformPaths("planned artifact locations are not platform paths"),
    DoesNotReturnFilePathUriOrPlatformObjects(
        "boundary does not return File, Path, Uri, or platform filesystem objects",
    ),
    DoesNotConstructRealOrAbsolutePaths("boundary does not construct real or absolute paths"),
    DoesNotReadOrWriteFiles("boundary does not read or write files"),
    DoesNotCreateDirectories("boundary does not create directories"),
    DoesNotPersistSettings("boundary does not persist Settings"),
    DoesNotProveExistenceContainmentSymlinkPermissionOwnershipDurability(
        "boundary does not prove existence, containment, symlink, permission, ownership, or durability state",
    ),
    DoesNotEnablePersistenceOrProviderSelection("boundary does not enable persistence or provider selection"),
}

enum class ProductionProviderStorageSafetyPreflightRule(val label: String) {
    EvidenceOnly("storage safety preflight returns evidence only"),
    ConsumesPlannedArtifactLocationEvidence("boundary consumes planned artifact-location evidence"),
    ConsumesFutureSafetyEvidence("boundary consumes future caller-supplied safety evidence"),
    GateVocabularyModeled("boundary models required storage safety gates"),
    DefaultFailClosed("boundary defaults to fail closed"),
    WarningOnlyCannotEnablePersistence("warning-only evidence cannot enable persistence"),
    UserConsentCannotOverrideFailedGates("user consent cannot override failed or missing safety gates"),
    DoesNotRunFilesystemChecks("boundary does not run filesystem checks"),
    DoesNotConstructRealOrAbsolutePaths("boundary does not construct real or absolute paths"),
    DoesNotUseFilePathOrFilesystemApis("boundary does not use File, Path, or filesystem APIs"),
    DoesNotCreateReadOrWriteFiles("boundary does not create, read, or write files"),
    DoesNotPersistSettings("boundary does not persist Settings"),
    DoesNotProveExistenceContainmentSymlinkPermissionOwnershipDurability(
        "boundary does not prove existence, containment, symlink, permission, ownership, or durability state",
    ),
    DoesNotEnableManifestStorageIndexRecordReadWrite(
        "boundary does not enable manifest, storage-index, or record read/write",
    ),
    DoesNotEnablePersistenceOrProviderSelection("boundary does not enable persistence or provider selection"),
}

enum class ProductionProviderDisabledStorageServiceFacadeRule(val label: String) {
    EvidenceOnly("disabled storage service facade returns evidence only"),
    ConsumesPlannedArtifactLocationEvidence("facade consumes planned artifact-location evidence"),
    ConsumesStorageSafetyPreflightEvidence("facade consumes storage safety preflight evidence"),
    OperationsFailClosed("every modeled storage operation returns disabled or rejected"),
    NoOperationReturnsSuccess("no storage operation returns success"),
    DoesNotReturnHandlesStreamsRepositories(
        "facade does not return storage handles, streams, repositories, or database handles",
    ),
    DoesNotUseFilePathOrFilesystemApis("facade does not use File, Path, or filesystem APIs"),
    DoesNotCreateReadWriteListDeleteQuarantineRecoverActualRecords(
        "facade does not create, read, write, list, delete, quarantine, or recover actual records",
    ),
    DoesNotReadWriteManifestStorageIndexRecords(
        "facade does not read or write manifests, storage indexes, or records",
    ),
    DoesNotPerformAtomicWriteOrCrashRecovery("facade does not perform atomic writes or crash recovery"),
    DoesNotMapRuntimeStorageFailures("facade does not map real runtime storage failures"),
    DoesNotPersistSettings("facade does not persist Settings"),
    DoesNotEnablePersistenceOrProviderSelection("facade does not enable persistence or provider selection"),
    RedactsRecordRootLocationPayloadEvidence("facade redacts record, root, location, and payload evidence"),
}

enum class ProductionProviderPersistenceReadinessGateRule(val label: String) {
    EvidenceOnly("vault persistence readiness gate returns evidence only"),
    ComposesProviderRootPathStorageSecureEvidence(
        "gate composes provider, root, path, storage safety, storage service, secure storage, and metadata evidence",
    ),
    DefaultDecisionBlocked("current readiness decision remains blocked"),
    RequiredGateVocabularyModeled("gate models the required production persistence gates"),
    WarningOnlyCannotEnablePersistence("warning-only evidence cannot enable persistence"),
    UserConsentCannotOverrideHardGates("user consent cannot override missing hard gates"),
    DoesNotUseFilePathOrFilesystemApis("gate does not use File, Path, or filesystem APIs"),
    DoesNotCreateReadWriteListDeleteQuarantineRecoverActualRecords(
        "gate does not create, read, write, list, delete, quarantine, or recover actual records",
    ),
    DoesNotReadWriteManifestStorageIndexRecords(
        "gate does not read or write manifests, storage indexes, or records",
    ),
    DoesNotApproveProductionProviderUse("gate does not approve production provider use"),
    DoesNotApproveMainnet("gate does not approve mainnet"),
    DoesNotEnablePersistenceOrProviderSelection("gate does not enable persistence or provider selection"),
    RedactsRootPathRecordPayloadProviderPassphraseEvidence(
        "gate redacts root, path, record, payload, provider, and passphrase evidence",
    ),
}

enum class ProductionProviderLockSessionLifecycleRule(val label: String) {
    EvidenceOnly("lock/session lifecycle boundary returns evidence only"),
    DefaultDecisionBlocked("current unlock/session decision remains blocked"),
    StateVocabularyModeled("boundary models future lock and session states"),
    EventVocabularyModeled("boundary models lifecycle event kinds"),
    RequiredGateVocabularyModeled("boundary models required future unlock gates"),
    WarningOnlyCannotEnableUnlock("warning-only evidence cannot enable unlock"),
    UserConsentCannotOverrideHardGates("user consent cannot override missing hard gates"),
    DoesNotAcceptPassphrasesOrPins("boundary does not accept passphrases or PINs"),
    DoesNotHoldOrGenerateKeyMaterial("boundary does not hold or generate key material"),
    DoesNotUseBiometricKeystoreKeyringPasswordManagerApis(
        "boundary does not use biometric, Keystore, keyring, or password-manager APIs",
    ),
    DoesNotUseFilePathOrFilesystemApis("boundary does not use File, Path, or filesystem APIs"),
    DoesNotPersistSessionState("boundary does not persist lock or session state"),
    DoesNotEnableUnlockPersistenceOrProviderSelection(
        "boundary does not enable unlock, persistence, or provider selection",
    ),
    RedactsPassphraseKeyRootPathRecordPayloadProviderEvidence(
        "boundary redacts passphrase, key, root, path, record, payload, and provider evidence",
    ),
}

enum class ProductionProviderRedactionLeakageRule(val label: String) {
    EvidenceOnly("redaction/leakage boundary returns evidence only"),
    ValueKindVocabularyModeled("boundary models sensitive value kinds"),
    OutputTargetVocabularyModeled("boundary models output targets"),
    DecisionVocabularyModeled("boundary models redaction decisions"),
    DefaultFailClosed("unknown or unsupported value classes fail closed"),
    DoesNotAcceptRawSecrets("boundary does not accept raw secrets"),
    DoesNotHashOrFingerprintSecrets("boundary does not hash or fingerprint secrets"),
    DoesNotImplementLoggingCrashAnalyticsOrSupportExport(
        "boundary does not implement logging, crash reporting, analytics, or support export",
    ),
    PublicVectorsScopedToDocsTestsAndSourceGuards(
        "public non-wallet vectors are scoped to docs, tests, and source guards",
    ),
    WalletUtxoSyncMaterialCannotUsePublicVectorException(
        "wallet, UTXO, and sync material cannot use the public-vector exception",
    ),
    DoesNotUseFilePathStorageSettingsOrPlatformApis(
        "boundary does not use File, Path, storage, Settings, or platform APIs",
    ),
    DoesNotEnableUnlockPersistenceOrProviderSelection(
        "boundary does not enable unlock, persistence, or provider selection",
    ),
    RedactsSecretRootPathRecordPayloadProviderEvidence(
        "boundary redacts secret, root, path, record, payload, and provider evidence",
    ),
}

enum class ProductionProviderPassphrasePolicyBoundaryRule(val label: String) {
    EvidenceOnly("passphrase policy boundary returns evidence only"),
    DefaultDecisionBlocked("current passphrase decision remains blocked"),
    PolicyVocabularyModeled("boundary models passphrase policy vocabulary"),
    NormalizationAndEncodingPolicyIdsOnly("boundary models normalization and encoding policy identifiers only"),
    RetryThrottleLockoutRequirementsModeled("boundary models retry, throttle, and lockout requirements"),
    DoesNotAcceptRawPassphrasesOrPins("boundary does not accept raw passphrases or PINs"),
    DoesNotHashFingerprintOrRunKdf("boundary does not hash, fingerprint, or run KDFs"),
    DoesNotImplementRetryThrottleLockout("boundary does not implement retry, throttle, or lockout behavior"),
    DoesNotUseBiometricKeystoreKeyringPasswordManagerApis(
        "boundary does not use biometric, Keystore, keyring, or password-manager APIs",
    ),
    DoesNotUseFilePathStorageSettingsOrPlatformApis(
        "boundary does not use File, Path, storage, Settings, or platform APIs",
    ),
    DoesNotEnableUnlockPersistenceOrProviderSelection(
        "boundary does not enable unlock, persistence, or provider selection",
    ),
    RedactsPassphraseKeyRootPathRecordPayloadProviderEvidence(
        "boundary redacts passphrase, key, root, path, record, payload, and provider evidence",
    ),
}

data class ProductionProviderPassphrasePolicyBoundaryEvidence(
    val policyId: String,
    val status: ProductionProviderConstructionContractStatus,
    val rules: Set<ProductionProviderPassphrasePolicyBoundaryRule>,
    val modeled: Boolean,
    val stillDisabled: Boolean,
    val inputStillRejected: Boolean,
    val doesNotAcceptRawPassphrases: Boolean,
    val doesNotHashOrFingerprint: Boolean,
    val doesNotRunKdf: Boolean,
    val doesNotEnableUnlock: Boolean,
    val doesNotEnablePersistence: Boolean,
    val doesNotEnableProviderSelection: Boolean,
    val failureVocabularyModeled: Boolean,
)

enum class ProductionProviderClearWipeStrategyBoundaryRule(val label: String) {
    EvidenceOnly("clear/wipe strategy boundary returns evidence only"),
    DefaultDecisionBlocked("current clear/wipe decision remains blocked or model-only"),
    SensitiveValueVocabularyModeled("boundary models future sensitive value classes"),
    LifecycleTriggerVocabularyModeled("boundary models lifecycle and failure triggers"),
    StrategyVocabularyModeled("boundary models clear/wipe strategy classes"),
    DoesNotAcceptRawSensitiveValues("boundary does not accept raw sensitive values"),
    DoesNotClearRealMemory("boundary does not clear real memory"),
    DoesNotProveJvmZeroization("boundary does not prove JVM zeroization"),
    DoesNotUseNativeMemoryOrZeroizationApis("boundary does not use native memory or zeroization APIs"),
    DoesNotCallProviderOrStorageClear("boundary does not call provider or storage clear operations"),
    DoesNotInvalidateRealSessions("boundary does not invalidate real sessions"),
    DoesNotUseFilePathStorageSettingsOrPlatformApis(
        "boundary does not use File, Path, storage, Settings, or platform APIs",
    ),
    DoesNotEnableUnlockPersistenceOrProviderSelection(
        "boundary does not enable unlock, persistence, or provider selection",
    ),
    RedactsPassphraseKeyBufferRootPathRecordPayloadProviderEvidence(
        "boundary redacts passphrase, key, buffer, root, path, record, payload, and provider evidence",
    ),
}

data class ProductionProviderClearWipeStrategyBoundaryEvidence(
    val policyId: String,
    val status: ProductionProviderConstructionContractStatus,
    val rules: Set<ProductionProviderClearWipeStrategyBoundaryRule>,
    val modeled: Boolean,
    val stillDisabled: Boolean,
    val doesNotAcceptRawSensitiveValues: Boolean,
    val doesNotClearRealMemory: Boolean,
    val doesNotProveJvmZeroization: Boolean,
    val doesNotEnableUnlock: Boolean,
    val doesNotEnablePersistence: Boolean,
    val doesNotEnableProviderSelection: Boolean,
    val failureVocabularyModeled: Boolean,
)

enum class ProductionProviderMigrationCorruptionBoundaryRule(val label: String) {
    EvidenceOnly("migration/corruption boundary returns evidence only"),
    DefaultDecisionBlocked("current migration/corruption decision remains blocked or model-only"),
    EvidenceCategoryVocabularyModeled("boundary models container, manifest, index, and record evidence categories"),
    FailureClassVocabularyModeled("boundary models migration/corruption failure classes"),
    RequiredActionVocabularyModeled("boundary models fail-closed required actions"),
    DoesNotAcceptRawStorageOrRecordBytes("boundary does not accept raw storage, ciphertext, or record bytes"),
    DoesNotParseRealStorage("boundary does not parse real persisted storage"),
    DoesNotRepairStorage("boundary does not repair storage"),
    DoesNotRunMigration("boundary does not run migration or migration dry-runs"),
    DoesNotQuarantineOrRecoverRecords("boundary does not quarantine or recover records"),
    DoesNotVerifyAeadOrHeaderCommitments(
        "boundary does not verify AEAD tags or real header commitments",
    ),
    DoesNotUseFilePathStorageSettingsOrPlatformApis(
        "boundary does not use File, Path, storage, Settings, or platform APIs",
    ),
    DoesNotEnableUnlockPersistenceOrProviderSelection(
        "boundary does not enable unlock, persistence, or provider selection",
    ),
    RedactsStorageRecordRootPathProviderEvidence(
        "boundary redacts storage, record, root, path, and provider evidence",
    ),
}

data class ProductionProviderMigrationCorruptionBoundaryEvidence(
    val policyId: String,
    val status: ProductionProviderConstructionContractStatus,
    val rules: Set<ProductionProviderMigrationCorruptionBoundaryRule>,
    val modeled: Boolean,
    val stillDisabled: Boolean,
    val classifiesFailureKinds: Boolean,
    val doesNotParseRealStorage: Boolean,
    val doesNotRepairStorage: Boolean,
    val doesNotRunMigration: Boolean,
    val doesNotEnableUnlock: Boolean,
    val doesNotEnablePersistence: Boolean,
    val doesNotEnableProviderSelection: Boolean,
    val failureVocabularyModeled: Boolean,
)

enum class ProductionProviderOperationAuthorizationBoundaryRule(val label: String) {
    EvidenceOnly("provider operation authorization boundary returns evidence only"),
    DefaultDecisionBlocked("current provider operation authorization decision remains blocked"),
    OperationKindVocabularyModeled("boundary models provider operation kinds"),
    OperationPurposeVocabularyModeled("boundary models provider operation purposes"),
    RequiredGateVocabularyModeled("boundary models required authorization gates"),
    BlocksAllOperations("boundary blocks all provider operations"),
    DoesNotAcceptRawProviderOrSecretInputs(
        "boundary does not accept provider handles, raw secrets, ciphertext, plaintext, or storage bytes",
    ),
    DoesNotRunProviderOperations("boundary does not run provider operations"),
    DoesNotRunKatRandomnessKdfHkdfHmacOrAead(
        "boundary does not run KAT, randomness, KDF, HKDF, HMAC, or AEAD execution",
    ),
    DoesNotGenerateEntropySaltNonceOrKeys(
        "boundary does not generate entropy, salts, nonces, or keys",
    ),
    DoesNotUseFilePathStorageSettingsOrPlatformApis(
        "boundary does not use File, Path, storage, Settings, or platform APIs",
    ),
    DoesNotEnableUnlockPersistenceOrProviderSelection(
        "boundary does not enable unlock, persistence, or provider selection",
    ),
    RedactsProviderCryptoStorageAndSecretEvidence(
        "boundary redacts provider, crypto, storage, path, and secret evidence",
    ),
}

data class ProductionProviderOperationAuthorizationBoundaryEvidence(
    val policyId: String,
    val status: ProductionProviderConstructionContractStatus,
    val rules: Set<ProductionProviderOperationAuthorizationBoundaryRule>,
    val modeled: Boolean,
    val stillDisabled: Boolean,
    val blocksAllOperations: Boolean,
    val doesNotRunCrypto: Boolean,
    val doesNotRunKat: Boolean,
    val doesNotGenerateRandomness: Boolean,
    val doesNotEnableUnlock: Boolean,
    val doesNotEnablePersistence: Boolean,
    val doesNotEnableProviderSelection: Boolean,
    val failureVocabularyModeled: Boolean,
)

enum class ProductionProviderRuntimeRandomnessAuthorizationBoundaryRule(val label: String) {
    EvidenceOnly("runtime randomness authorization boundary returns evidence only"),
    DefaultDecisionBlocked("current runtime randomness authorization decision remains blocked"),
    OperationKindVocabularyModeled("boundary models randomness operation kinds"),
    OperationPurposeVocabularyModeled("boundary models randomness operation purposes"),
    SourceKindVocabularyModeled("boundary models randomness source kinds"),
    RequiredGateVocabularyModeled("boundary models required randomness gates"),
    BlocksAllOperations("boundary blocks all randomness operations"),
    DoesNotAcceptRawRandomnessOrSecretInputs(
        "boundary does not accept random, entropy, salt, nonce, key, passphrase, ciphertext, plaintext, or storage bytes",
    ),
    DoesNotCallRandomApis("boundary does not call runtime randomness APIs"),
    DoesNotRunProviderOperations("boundary does not run provider operations"),
    DoesNotRunKatKdfHkdfHmacOrAead(
        "boundary does not run KAT, KDF, HKDF, HMAC, or AEAD execution",
    ),
    DoesNotGenerateEntropySaltNonceOrKeys(
        "boundary does not generate entropy, salts, nonces, or keys",
    ),
    DoesNotUseFilePathStorageSettingsOrPlatformApis(
        "boundary does not use File, Path, storage, Settings, or platform APIs",
    ),
    DoesNotEnableUnlockPersistenceOrProviderSelection(
        "boundary does not enable unlock, persistence, or provider selection",
    ),
    RedactsRandomnessCryptoStorageAndSecretEvidence(
        "boundary redacts randomness, crypto, storage, path, and secret evidence",
    ),
}

data class ProductionProviderRuntimeRandomnessAuthorizationBoundaryEvidence(
    val policyId: String,
    val status: ProductionProviderConstructionContractStatus,
    val rules: Set<ProductionProviderRuntimeRandomnessAuthorizationBoundaryRule>,
    val modeled: Boolean,
    val stillDisabled: Boolean,
    val blocksAllOperations: Boolean,
    val doesNotCallSecureRandom: Boolean,
    val doesNotGenerateEntropy: Boolean,
    val doesNotGenerateSaltOrNonce: Boolean,
    val doesNotGenerateKeys: Boolean,
    val doesNotEnableProviderOperations: Boolean,
    val doesNotEnableUnlock: Boolean,
    val doesNotEnablePersistence: Boolean,
    val doesNotEnableProviderSelection: Boolean,
    val failureVocabularyModeled: Boolean,
)

enum class ProductionProviderKdfCalibrationAuthorizationBoundaryRule(val label: String) {
    EvidenceOnly("KDF calibration authorization boundary returns evidence only"),
    DefaultDecisionBlocked("current KDF calibration authorization decision remains blocked"),
    OperationKindVocabularyModeled("boundary models KDF operation kinds"),
    OperationPurposeVocabularyModeled("boundary models KDF operation purposes"),
    ParameterEvidenceVocabularyModeled("boundary models KDF parameter/evidence kinds"),
    PlatformDeviceClassVocabularyModeled("boundary models KDF platform/device classes"),
    RequiredGateVocabularyModeled("boundary models required KDF authorization gates"),
    BlocksAllOperations("boundary blocks all KDF calibration and execution operations"),
    DoesNotAcceptRawPassphraseSaltKdfOrBenchmarkInputs(
        "boundary does not accept raw passphrases, salts, KDF material, or benchmark logs",
    ),
    DoesNotRunArgon2idKdfCalibrationOrBenchmarks(
        "boundary does not run Argon2id, KDFs, calibration, or benchmarks",
    ),
    DoesNotGenerateConsumeSaltOrRandomness("boundary does not generate or consume salts or randomness"),
    DoesNotRunProviderOperationsOrKats("boundary does not run provider operations or KATs"),
    DoesNotApproveFinalParameters("boundary does not approve final production KDF parameters"),
    DoesNotUseFilePathStorageSettingsOrPlatformApis(
        "boundary does not use File, Path, storage, Settings, or platform APIs",
    ),
    DoesNotEnableUnlockPersistenceOrProviderSelection(
        "boundary does not enable unlock, persistence, or provider selection",
    ),
    RedactsKdfCalibrationCryptoStorageAndSecretEvidence(
        "boundary redacts KDF, calibration, crypto, storage, path, and secret evidence",
    ),
}

data class ProductionProviderKdfCalibrationAuthorizationBoundaryEvidence(
    val policyId: String,
    val status: ProductionProviderConstructionContractStatus,
    val rules: Set<ProductionProviderKdfCalibrationAuthorizationBoundaryRule>,
    val modeled: Boolean,
    val stillDisabled: Boolean,
    val blocksAllOperations: Boolean,
    val doesNotRunArgon2id: Boolean,
    val doesNotRunCalibration: Boolean,
    val doesNotApproveFinalParameters: Boolean,
    val doesNotEnableUnlock: Boolean,
    val doesNotEnablePersistence: Boolean,
    val doesNotEnableProviderSelection: Boolean,
    val failureVocabularyModeled: Boolean,
)

enum class ProductionProviderSecureStorageAuthorizationBoundaryRule(val label: String) {
    EvidenceOnly("secure-storage authorization boundary returns evidence only"),
    DefaultDecisionBlocked("current secure-storage authorization decision remains blocked"),
    OperationKindVocabularyModeled("boundary models secure-storage operation kinds"),
    ValueKindVocabularyModeled("boundary models secure-storage value kinds"),
    TargetKindVocabularyModeled("boundary models secure-storage target kinds"),
    RequiredGateVocabularyModeled("boundary models required secure-storage authorization gates"),
    BlocksAllOperations("boundary blocks all secure-storage operations"),
    DoesNotAcceptRawSecretStorageOrProviderInputs(
        "boundary does not accept raw secrets, storage bytes, provider handles, or platform handles",
    ),
    DoesNotStoreRetrieveDeleteSecretsOrMetadata(
        "boundary does not store, retrieve, delete, export, import, migrate, or purge secure storage",
    ),
    DoesNotWrapOrUnwrapKeys("boundary does not wrap or unwrap keys"),
    DoesNotUseOsKeyringsPasswordManagersKeystoreSettingsFilesOrDatabases(
        "boundary does not use OS keyrings, password managers, Android wrapping, Settings, files, or databases",
    ),
    DoesNotRunProviderRandomnessKdfHkdfHmacOrAead(
        "boundary does not run provider, randomness, KDF, HKDF, HMAC, or AEAD execution",
    ),
    DoesNotEnableUnlockPersistenceOrProviderSelection(
        "boundary does not enable unlock, persistence, or provider selection",
    ),
    RedactsSecureStorageCryptoMetadataAndSecretEvidence(
        "boundary redacts secure-storage, crypto, metadata, path, and secret evidence",
    ),
}

data class ProductionProviderSecureStorageAuthorizationBoundaryEvidence(
    val policyId: String,
    val status: ProductionProviderConstructionContractStatus,
    val rules: Set<ProductionProviderSecureStorageAuthorizationBoundaryRule>,
    val modeled: Boolean,
    val stillDisabled: Boolean,
    val blocksAllOperations: Boolean,
    val doesNotStoreSecrets: Boolean,
    val doesNotUseOsKeyrings: Boolean,
    val doesNotUsePasswordManagers: Boolean,
    val doesNotUseSettingsStorage: Boolean,
    val doesNotEnableUnlock: Boolean,
    val doesNotEnablePersistence: Boolean,
    val doesNotEnableProviderSelection: Boolean,
    val failureVocabularyModeled: Boolean,
)

enum class ProductionProviderSafePathConstructionRule(val label: String) {
    ReviewedPlatformRootOnly("future path construction starts from a reviewed platform root"),
    ValidatedStorageNamespaceSegment("future path construction uses a validated storage namespace segment"),
    EncodedVaultSegment("future path construction uses an encoded vault segment"),
    EncodedManifestSegment("future path construction uses an encoded manifest segment"),
    EncodedRecordSegment("future path construction uses encoded record segments"),
    StableInternalFilenamesPassSafeSegmentValidation(
        "stable internal filenames or segments must pass safe-segment validation",
    ),
    JoinOnlyValidatedRelativeSegments("future joining uses only validated relative segments"),
    RejectAbsoluteSegments("absolute segments are rejected"),
    RejectDotParentAndEmptySegments("dot, parent, and empty segments are rejected"),
    RejectSlashBackslashInsideSegments("slash and backslash inside segments are rejected"),
    RejectNonAsciiInvisibleUnsupportedOrTooLongSegments(
        "non-ASCII, invisible, unsupported, or too-long segments are rejected",
    ),
    RejectUserLabelsTextAndSecretLookingInputs("user labels, note text, and secret-looking inputs are rejected"),
    FutureContainmentCheckUnderReviewedRootRequired(
        "future implementation must prove final path containment under the reviewed root",
    ),
    FailClosedIfContainmentCannotBeProven("future path construction fails closed if containment cannot be proven"),
    NoActualPathConstructionInThisBranch("no actual path construction is implemented in this branch"),
}

enum class ProductionProviderSymlinkTraversalRule(val label: String) {
    NoSymlinkBehaviorAssumptionsWithoutReview("symlink behavior is not assumed without platform review"),
    RejectOrAvoidSymlinkTraversal("future implementation rejects or avoids symlink traversal where possible"),
    VerifyResolvedTargetUnderRootWhereSupported(
        "future implementation verifies resolved targets stay under the app-controlled root where supported",
    ),
    FailClosedIfSymlinkOrContainmentUnknown("future implementation fails closed if symlink or containment state is unknown"),
    DoNotFollowAttackerControlledSymlinks("future implementation does not follow attacker-controlled symlinks"),
    ExternalHardLinksAliasesRequireReview("external hard-linked or alias paths require review"),
    PlatformBehaviorDocumentedBeforeApproval(
        "platform-specific symlink and traversal behavior is documented before persistence approval",
    ),
    NoSymlinkCheckImplementationInThisBranch("no symlink check implementation exists in this branch"),
}

enum class ProductionProviderStoragePermissionOwnershipRule(val label: String) {
    AppPrivateOsIsolationPreferred("app-private storage with OS-enforced per-user/app isolation is preferred"),
    RejectObviouslyUnsafePermissionsWhenDetectable(
        "future implementation rejects obviously unsafe root permissions when detectable",
    ),
    DesktopLinuxPermissionExpectationsDocumented("desktop Linux permission expectations are documented"),
    AndroidAppPrivateBehaviorDocumented("Android app-private storage behavior is documented"),
    FailClosedIfPermissionOwnershipUnknownOrUnsafe(
        "future implementation fails closed if root permission/ownership state is unknown or unsafe",
    ),
    NoWorldReadableOrSharedDirectories("vault data is not stored in world-readable or shared directories"),
    OsKeyringNotPrimaryVaultEncryption("OS keyrings are not primary vault encryption or primary vault storage"),
    NoPermissionCheckImplementationInThisBranch("no permission or ownership check implementation exists in this branch"),
}

enum class ProductionProviderDurabilityCapabilityRule(val label: String) {
    AtomicReplaceCapabilityDocumented("future implementation documents whether atomic replace is supported"),
    DurableSyncCapabilityDocumented("future implementation documents whether durable sync is supported"),
    ParentDirectorySyncCapabilityDocumented(
        "future implementation documents whether parent directory sync or equivalent is supported",
    ),
    OrderingGuaranteesDocumented("future implementation documents whether platform APIs guarantee expected ordering"),
    UnsupportedPrimitiveFallbackDefined(
        "future implementation defines reviewed equivalent safe strategies for unsupported durability primitives",
    ),
    RequiredDurabilityFailuresBlockEncryptedVaultPersistence(
        "unknown, unsupported, insufficient, unreviewed, unsafe, or failed durability blocks encrypted vault persistence",
    ),
    WarningOnlyEncryptedVaultPersistenceRejected(
        "warning-only encrypted vault persistence is rejected for v1",
    ),
    EquivalentSafeStrategyRequiresHumanReview(
        "equivalent safe durability strategies require explicit future human review",
    ),
    UserConsentCannotOverrideDurabilityFailure(
        "user consent cannot override required durability failure for encrypted vault writes",
    ),
    AndroidDurabilityRequiresReviewBeforePersistence(
        "Android app-private storage durability requires implementation proof and review before persistence",
    ),
    DesktopDurabilityRequiresReviewBeforePersistence(
        "desktop filesystem durability requires implementation proof and review before persistence",
    ),
    AndroidDesktopDifferencesDocumented("Android and desktop durability differences are documented"),
    NoDurabilityProbeImplementationInThisBranch("no durability probe implementation exists in this branch"),
}

enum class ProductionProviderDurabilityFailClosedCondition(val label: String) {
    DurabilityCapabilityUnknown("durability capability unknown"),
    DurabilityCapabilityInsufficient("durability capability insufficient"),
    DurabilitySyncUnsupported("durability sync unsupported"),
    DurabilitySyncFailed("durability sync failed"),
    AtomicReplaceUnsupported("atomic replace unsupported"),
    AtomicReplaceFailed("atomic replace failed"),
    PlatformRootUnreviewed("platform root unreviewed"),
    PlatformRootUnsafe("platform root unsafe"),
    PermissionStateUnknown("permission state unknown"),
    UnsafePermissions("unsafe permissions"),
    UnknownStorageState("unknown storage state"),
}

enum class ProductionProviderWarningOnlyDurabilityRule(val label: String) {
    WarningOnlyEncryptedVaultPersistenceRejected(
        "warning-only encrypted vault persistence is rejected for v1",
    ),
    UserConsentCannotOverrideDurabilityFailure(
        "user consent cannot override required durability failure",
    ),
    NonSecretDiagnosticsOnlyAfterReview(
        "future warning-only behavior is limited to non-secret diagnostics or reviewed non-critical artifacts",
    ),
    EquivalentSafeStrategyRequiresHumanReview(
        "equivalent safe durability strategies require future human review",
    ),
}

enum class ProductionProviderSecureStorageBoundaryRequirement(val label: String) {
    EncryptedContainerStorageSeparateFromSecureSecretStorage(
        "encrypted vault container storage is separate from secure secret storage",
    ),
    SecureSecretStorageDisabledFailClosed("secure secret storage remains disabled and fail-closed"),
    SecureMetadataStorageDisabledFailClosed("secure metadata storage remains disabled and fail-closed"),
    AndroidWrappingOptionalConvenienceOnly("Android hardware/biometric wrapping is optional future convenience only"),
    LinuxKeyringsNotPrimaryVaultProtection("Linux desktop keyrings are not primary vault protection"),
    NoStorageSuccessUntilSecureBoundariesImplementedAndTested(
        "no storage success path is approved until secure boundaries are implemented and tested",
    ),
}

data class ProductionProviderContainerManifestStorageContract(
    val vaultContainerPolicyId: String,
    val manifestPolicyId: String,
    val storagePolicyId: String,
    val platformStorageBoundaryPolicyId: String,
    val staleRecordPolicyId: String,
    val atomicityCrashRecoveryPolicyId: String,
    val atomicWritePolicyId: String,
    val crashRecoveryPolicyId: String,
    val interruptionTestPolicyId: String,
    val storageFailureModelPolicyId: String,
    val storageNamespacePathPolicyId: String,
    val storageLayoutPlanPolicyId: String,
    val pathContainmentPlannerPolicyId: String,
    val platformStorageRootPolicyId: String,
    val platformRootSettingsPolicyId: String,
    val androidRootPolicyId: String,
    val linuxRootSettingsPolicyId: String,
    val linuxCustomRootValidationPolicyId: String,
    val linuxRootResolutionPolicyId: String,
    val platformRootResolverPolicyId: String,
    val platformPathConstructionBoundaryPolicyId: String,
    val storageSafetyPreflightBoundaryPolicyId: String,
    val disabledStorageServiceFacadePolicyId: String,
    val persistenceReadinessGatePolicyId: String,
    val lockSessionLifecycleBoundaryPolicyId: String,
    val redactionLeakageBoundaryPolicyId: String,
    val passphrasePolicyBoundaryEvidence: ProductionProviderPassphrasePolicyBoundaryEvidence,
    val clearWipeStrategyBoundaryEvidence: ProductionProviderClearWipeStrategyBoundaryEvidence,
    val migrationCorruptionBoundaryEvidence: ProductionProviderMigrationCorruptionBoundaryEvidence,
    val providerOperationAuthorizationBoundaryEvidence:
        ProductionProviderOperationAuthorizationBoundaryEvidence,
    val runtimeRandomnessAuthorizationBoundaryEvidence:
        ProductionProviderRuntimeRandomnessAuthorizationBoundaryEvidence,
    val kdfCalibrationAuthorizationBoundaryEvidence:
        ProductionProviderKdfCalibrationAuthorizationBoundaryEvidence,
    val secureStorageAuthorizationBoundaryEvidence:
        ProductionProviderSecureStorageAuthorizationBoundaryEvidence,
    val osKeyringPassphrasePolicyId: String,
    val passwordManagerPassphrasePolicyId: String,
    val passphraseFirstPolicyId: String,
    val safePathConstructionPolicyId: String,
    val symlinkTraversalPolicyId: String,
    val storagePermissionOwnershipPolicyId: String,
    val durabilityCapabilityPolicyId: String,
    val durabilityFailClosedPolicyId: String,
    val warningOnlyDurabilityRejectionPolicyId: String,
    val storageAtomicitySimulatorPolicyId: String,
    val secureStorageBoundaryPolicyId: String,
    val antiRollbackAnchorPolicyId: String,
    val vaultContainerContractStatus: ProductionProviderConstructionContractStatus,
    val manifestContractStatus: ProductionProviderConstructionContractStatus,
    val storagePolicyContractStatus: ProductionProviderConstructionContractStatus,
    val platformStorageBoundaryContractStatus: ProductionProviderConstructionContractStatus,
    val staleRecordPolicyStatus: ProductionProviderConstructionContractStatus,
    val atomicityCrashRecoveryContractStatus: ProductionProviderConstructionContractStatus,
    val atomicWriteContractStatus: ProductionProviderConstructionContractStatus,
    val crashRecoveryContractStatus: ProductionProviderConstructionContractStatus,
    val interruptionTestContractStatus: ProductionProviderConstructionContractStatus,
    val storageFailureModelStatus: ProductionProviderConstructionContractStatus,
    val storageNamespacePathHygieneStatus: ProductionProviderConstructionContractStatus,
    val storageLayoutPlanStatus: ProductionProviderConstructionContractStatus,
    val pathContainmentPlannerStatus: ProductionProviderConstructionContractStatus,
    val platformStorageRootContractStatus: ProductionProviderConstructionContractStatus,
    val platformRootSettingsPolicyStatus: ProductionProviderConstructionContractStatus,
    val linuxCustomRootValidationPolicyStatus: ProductionProviderConstructionContractStatus,
    val linuxRootResolutionPolicyStatus: ProductionProviderConstructionContractStatus,
    val platformRootResolverBoundaryStatus: ProductionProviderConstructionContractStatus,
    val platformPathConstructionBoundaryStatus: ProductionProviderConstructionContractStatus,
    val storageSafetyPreflightBoundaryStatus: ProductionProviderConstructionContractStatus,
    val disabledStorageServiceFacadeStatus: ProductionProviderConstructionContractStatus,
    val persistenceReadinessGateStatus: ProductionProviderConstructionContractStatus,
    val lockSessionLifecycleBoundaryStatus: ProductionProviderConstructionContractStatus,
    val redactionLeakageBoundaryStatus: ProductionProviderConstructionContractStatus,
    val safePathConstructionContractStatus: ProductionProviderConstructionContractStatus,
    val symlinkTraversalContractStatus: ProductionProviderConstructionContractStatus,
    val storagePermissionOwnershipContractStatus: ProductionProviderConstructionContractStatus,
    val durabilityCapabilityContractStatus: ProductionProviderConstructionContractStatus,
    val durabilityFailClosedPolicyStatus: ProductionProviderConstructionContractStatus,
    val warningOnlyDurabilityRejectionStatus: ProductionProviderConstructionContractStatus,
    val storageAtomicitySimulatorStatus: ProductionProviderConstructionContractStatus,
    val secureStorageBoundaryStatus: ProductionProviderConstructionContractStatus,
    val containerFields: Set<ProductionProviderVaultContainerField>,
    val containerRequirements: Set<ProductionProviderVaultContainerRequirement>,
    val manifestFields: Set<ProductionProviderManifestField>,
    val manifestBindings: Set<ProductionProviderStaleRecordManifestBinding>,
    val manifestRequirements: Set<ProductionProviderStaleRecordManifestRequirement>,
    val storageBoundaryAllowedBytes: Set<ProductionProviderStorageBoundaryAllowedBytes>,
    val storageBoundaryForbiddenMaterial: Set<ProductionProviderStorageBoundaryForbiddenMaterial>,
    val storageBoundaryRequirements: Set<ProductionProviderStorageBoundaryRequirement>,
    val atomicityRequirements: Set<ProductionProviderStorageAtomicityRequirement>,
    val atomicWritePhases: Set<ProductionProviderAtomicWritePhase>,
    val atomicWriteRequirements: Set<ProductionProviderAtomicWriteRequirement>,
    val crashRecoveryChecks: Set<ProductionProviderCrashRecoveryCheck>,
    val crashRecoveryFailClosedStates: Set<ProductionProviderCrashRecoveryFailClosedState>,
    val storageFailureCategories: Set<ProductionProviderStorageFailureCategory>,
    val storageNamespacePathRules: Set<ProductionProviderStorageNamespacePathRule>,
    val storageLayoutPlanRules: Set<ProductionProviderStorageLayoutPlanRule>,
    val pathContainmentPlannerRules: Set<ProductionProviderPathContainmentPlannerRule>,
    val platformStorageRootRules: Set<ProductionProviderPlatformStorageRootRule>,
    val platformRootSettingsRules: Set<ProductionProviderPlatformRootSettingsRule>,
    val linuxCustomRootValidationRules: Set<ProductionProviderLinuxCustomRootValidationRule>,
    val linuxRootResolutionRules: Set<ProductionProviderLinuxRootResolutionRule>,
    val platformRootResolverRules: Set<ProductionProviderPlatformRootResolverRule>,
    val platformPathConstructionRules: Set<ProductionProviderPlatformPathConstructionRule>,
    val storageSafetyPreflightRules: Set<ProductionProviderStorageSafetyPreflightRule>,
    val disabledStorageServiceFacadeRules: Set<ProductionProviderDisabledStorageServiceFacadeRule>,
    val persistenceReadinessGateRules: Set<ProductionProviderPersistenceReadinessGateRule>,
    val lockSessionLifecycleRules: Set<ProductionProviderLockSessionLifecycleRule>,
    val redactionLeakageRules: Set<ProductionProviderRedactionLeakageRule>,
    val safePathConstructionRules: Set<ProductionProviderSafePathConstructionRule>,
    val symlinkTraversalRules: Set<ProductionProviderSymlinkTraversalRule>,
    val storagePermissionOwnershipRules: Set<ProductionProviderStoragePermissionOwnershipRule>,
    val durabilityCapabilityRules: Set<ProductionProviderDurabilityCapabilityRule>,
    val durabilityFailClosedConditions: Set<ProductionProviderDurabilityFailClosedCondition>,
    val warningOnlyDurabilityRules: Set<ProductionProviderWarningOnlyDurabilityRule>,
    val secureStorageBoundaryRequirements: Set<ProductionProviderSecureStorageBoundaryRequirement>,
    val strictAadSubstitutionProtectionModeled: Boolean,
    val strictAadFreshnessProofClaimed: Boolean,
    val localManifestStaleRecordDetectionModeled: Boolean,
    val fullLocalDirectoryRollbackResistanceClaimed: Boolean,
    val externalOrTrustedMonotonicAntiRollbackAnchorImplemented: Boolean,
    val parserImplemented: Boolean,
    val writerImplemented: Boolean,
    val manifestParserImplemented: Boolean,
    val manifestWriterImplemented: Boolean,
    val staleRecordDecisionPolicyImplemented: Boolean,
    val vaultPersistenceImplemented: Boolean,
    val manifestReadWriteImplemented: Boolean,
    val storageIndexReadWriteImplemented: Boolean,
    val platformStorageImplementationAdded: Boolean,
    val filesystemVaultStorageImplemented: Boolean,
    val databaseVaultStorageImplemented: Boolean,
    val dataStoreVaultStorageImplemented: Boolean,
    val sharedPreferencesVaultStorageImplemented: Boolean,
    val tempFileImplementationAdded: Boolean,
    val journalImplementationAdded: Boolean,
    val atomicReplaceImplementationAdded: Boolean,
    val durabilitySyncImplementationAdded: Boolean,
    val crashRecoveryImplementationAdded: Boolean,
    val interruptionTestRuntimeHooksAdded: Boolean,
    val storageFailureRuntimeMappingImplemented: Boolean,
    val storagePathConstructionImplemented: Boolean,
    val platformRootResolutionImplemented: Boolean,
    val platformRootSelectionImplemented: Boolean,
    val androidAppPrivateInternalRootPolicyModeled: Boolean,
    val androidExternalStorageRejected: Boolean,
    val androidUserSelectedRootRejected: Boolean,
    val androidRootResolutionImplemented: Boolean,
    val androidBackupBehaviorReviewed: Boolean,
    val androidUninstallBehaviorReviewed: Boolean,
    val linuxDefaultUserDataRootPolicyModeled: Boolean,
    val linuxLocalShareFallbackModeled: Boolean,
    val linuxCustomRootSettingsContractModeled: Boolean,
    val linuxCustomRootUsable: Boolean,
    val linuxCustomRootValidationImplemented: Boolean,
    val linuxRootResolutionEvidenceModeled: Boolean,
    val linuxRootResolutionStillDisabled: Boolean,
    val linuxRootResolutionDoesNotResolveFilesystem: Boolean,
    val linuxRootResolutionDoesNotConstructPaths: Boolean,
    val linuxRootResolutionDoesNotEnablePersistence: Boolean,
    val platformRootResolverBoundaryModeled: Boolean,
    val androidAppPrivateRootEvidenceModeled: Boolean,
    val linuxDefaultRootResolverEvidenceModeled: Boolean,
    val platformRootResolverStillDisabled: Boolean,
    val platformRootResolverDoesNotEnablePersistence: Boolean,
    val platformRootResolverDoesNotProveDurability: Boolean,
    val platformRootResolverDoesNotEnableProviderSelection: Boolean,
    val platformPathConstructionBoundaryModeled: Boolean,
    val platformPathConstructionStillDisabled: Boolean,
    val platformPathConstructionDoesNotConstructRealPaths: Boolean,
    val platformPathConstructionDoesNotEnablePersistence: Boolean,
    val platformPathConstructionDoesNotEnableProviderSelection: Boolean,
    val plannedArtifactLocationEvidenceModeled: Boolean,
    val storageSafetyPreflightBoundaryModeled: Boolean,
    val storageSafetyPreflightStillDisabled: Boolean,
    val storageSafetyPreflightDoesNotRunFilesystemChecks: Boolean,
    val storageSafetyPreflightDoesNotEnablePersistence: Boolean,
    val storageSafetyPreflightDoesNotEnableProviderSelection: Boolean,
    val storageSafetyGateVocabularyModeled: Boolean,
    val disabledStorageServiceFacadeModeled: Boolean,
    val storageServiceFacadeStillDisabled: Boolean,
    val storageServiceOperationsFailClosed: Boolean,
    val storageServiceDoesNotUseFilesystem: Boolean,
    val storageServiceDoesNotEnablePersistence: Boolean,
    val storageServiceDoesNotEnableProviderSelection: Boolean,
    val storageOperationFailureVocabularyModeled: Boolean,
    val storageServiceOperationSuccessPathImplemented: Boolean,
    val persistenceReadinessGateModeled: Boolean,
    val persistenceReadinessGateStillBlocked: Boolean,
    val persistenceReadinessGateComposesStorageEvidence: Boolean,
    val persistenceReadinessGateDoesNotUseFilesystem: Boolean,
    val persistenceReadinessGateDoesNotEnablePersistence: Boolean,
    val persistenceReadinessGateDoesNotEnableProviderSelection: Boolean,
    val persistenceReadinessFailureVocabularyModeled: Boolean,
    val lockSessionLifecycleBoundaryModeled: Boolean,
    val lockSessionLifecycleStillDisabled: Boolean,
    val unlockDecisionStillBlocked: Boolean,
    val activeSessionUnavailable: Boolean,
    val lockSessionBoundaryDoesNotAcceptPassphrases: Boolean,
    val lockSessionBoundaryDoesNotHoldKeys: Boolean,
    val lockSessionBoundaryDoesNotEnablePersistence: Boolean,
    val lockSessionBoundaryDoesNotEnableProviderSelection: Boolean,
    val lockSessionFailureVocabularyModeled: Boolean,
    val redactionLeakageBoundaryModeled: Boolean,
    val redactionLeakageBoundaryStillDisabled: Boolean,
    val redactionLeakageClassifiesSensitiveValueKinds: Boolean,
    val redactionLeakageDoesNotAcceptRawSecrets: Boolean,
    val redactionLeakageDoesNotHashSecrets: Boolean,
    val redactionLeakageDoesNotLog: Boolean,
    val redactionLeakageDoesNotEnablePersistence: Boolean,
    val redactionLeakageDoesNotEnableProviderSelection: Boolean,
    val redactionLeakageFailureVocabularyModeled: Boolean,
    val settingsUiImplemented: Boolean,
    val settingsPersistenceImplemented: Boolean,
    val osKeyringPrimaryStorageRejected: Boolean,
    val osKeyringPassphraseStorageRejected: Boolean,
    val passwordManagerIntegrationRejected: Boolean,
    val passphraseFirstDefaultModeled: Boolean,
    val actualPathConstructionImplemented: Boolean,
    val pathJoinImplementationAdded: Boolean,
    val pathContainmentCheckImplementationAdded: Boolean,
    val directoryCreationImplementationAdded: Boolean,
    val symlinkCheckImplementationAdded: Boolean,
    val permissionCheckImplementationAdded: Boolean,
    val durabilityProbeImplementationAdded: Boolean,
    val warningOnlyEncryptedVaultPersistenceAllowed: Boolean,
    val userConsentDurabilityOverrideAllowed: Boolean,
    val equivalentSafeDurabilityStrategyApproved: Boolean,
    val androidDurabilityReviewed: Boolean,
    val desktopDurabilityReviewed: Boolean,
    val storageNamespacePathPolicyImplemented: Boolean,
    val storagePathSegmentEncodingImplemented: Boolean,
    val storageLayoutPlanImplemented: Boolean,
    val storageLayoutLocationsRootless: Boolean,
    val storageLayoutUsesSafeSegmentsOnly: Boolean,
    val reviewedRootTokenPolicyImplemented: Boolean,
    val pathContainmentPlannerImplemented: Boolean,
    val plannedLocationsRootTokenBound: Boolean,
    val plannedLocationsArePlatformPaths: Boolean,
    val plannedLocationsUseSafeSegmentsOnly: Boolean,
    val inMemoryAtomicityCrashSimulatorImplemented: Boolean,
    val inMemoryAtomicityCrashSimulatorInterruptionTestsExecuted: Boolean,
    val inMemoryAtomicityCrashSimulatorRecoveryDecisionsTested: Boolean,
    val secureSecretStorageSuccessPathImplemented: Boolean,
    val secureMetadataStorageSuccessPathImplemented: Boolean,
    val atomicWriteRecoveryImplementationAdded: Boolean,
) {
    val passphrasePolicyBoundaryPolicyId: String
        get() = passphrasePolicyBoundaryEvidence.policyId

    val passphrasePolicyBoundaryStatus: ProductionProviderConstructionContractStatus
        get() = passphrasePolicyBoundaryEvidence.status

    val passphrasePolicyBoundaryRules: Set<ProductionProviderPassphrasePolicyBoundaryRule>
        get() = passphrasePolicyBoundaryEvidence.rules

    val passphrasePolicyBoundaryModeled: Boolean
        get() = passphrasePolicyBoundaryEvidence.modeled

    val passphrasePolicyStillDisabled: Boolean
        get() = passphrasePolicyBoundaryEvidence.stillDisabled

    val passphraseInputStillRejected: Boolean
        get() = passphrasePolicyBoundaryEvidence.inputStillRejected

    val passphrasePolicyDoesNotAcceptRawPassphrases: Boolean
        get() = passphrasePolicyBoundaryEvidence.doesNotAcceptRawPassphrases

    val passphrasePolicyDoesNotHashOrFingerprint: Boolean
        get() = passphrasePolicyBoundaryEvidence.doesNotHashOrFingerprint

    val passphrasePolicyDoesNotRunKdf: Boolean
        get() = passphrasePolicyBoundaryEvidence.doesNotRunKdf

    val passphrasePolicyDoesNotEnableUnlock: Boolean
        get() = passphrasePolicyBoundaryEvidence.doesNotEnableUnlock

    val passphrasePolicyDoesNotEnablePersistence: Boolean
        get() = passphrasePolicyBoundaryEvidence.doesNotEnablePersistence

    val passphrasePolicyDoesNotEnableProviderSelection: Boolean
        get() = passphrasePolicyBoundaryEvidence.doesNotEnableProviderSelection

    val passphrasePolicyFailureVocabularyModeled: Boolean
        get() = passphrasePolicyBoundaryEvidence.failureVocabularyModeled

    val clearWipeStrategyBoundaryPolicyId: String
        get() = clearWipeStrategyBoundaryEvidence.policyId

    val clearWipeStrategyBoundaryStatus: ProductionProviderConstructionContractStatus
        get() = clearWipeStrategyBoundaryEvidence.status

    val clearWipeStrategyBoundaryRules: Set<ProductionProviderClearWipeStrategyBoundaryRule>
        get() = clearWipeStrategyBoundaryEvidence.rules

    val clearWipeStrategyBoundaryModeled: Boolean
        get() = clearWipeStrategyBoundaryEvidence.modeled

    val clearWipeStrategyStillDisabled: Boolean
        get() = clearWipeStrategyBoundaryEvidence.stillDisabled

    val clearWipeStrategyDoesNotAcceptRawSensitiveValues: Boolean
        get() = clearWipeStrategyBoundaryEvidence.doesNotAcceptRawSensitiveValues

    val clearWipeStrategyDoesNotClearRealMemory: Boolean
        get() = clearWipeStrategyBoundaryEvidence.doesNotClearRealMemory

    val clearWipeStrategyDoesNotProveJvmZeroization: Boolean
        get() = clearWipeStrategyBoundaryEvidence.doesNotProveJvmZeroization

    val clearWipeStrategyDoesNotEnableUnlock: Boolean
        get() = clearWipeStrategyBoundaryEvidence.doesNotEnableUnlock

    val clearWipeStrategyDoesNotEnablePersistence: Boolean
        get() = clearWipeStrategyBoundaryEvidence.doesNotEnablePersistence

    val clearWipeStrategyDoesNotEnableProviderSelection: Boolean
        get() = clearWipeStrategyBoundaryEvidence.doesNotEnableProviderSelection

    val clearWipeFailureVocabularyModeled: Boolean
        get() = clearWipeStrategyBoundaryEvidence.failureVocabularyModeled

    val migrationCorruptionBoundaryPolicyId: String
        get() = migrationCorruptionBoundaryEvidence.policyId

    val migrationCorruptionBoundaryStatus: ProductionProviderConstructionContractStatus
        get() = migrationCorruptionBoundaryEvidence.status

    val migrationCorruptionBoundaryRules: Set<ProductionProviderMigrationCorruptionBoundaryRule>
        get() = migrationCorruptionBoundaryEvidence.rules

    val migrationCorruptionBoundaryModeled: Boolean
        get() = migrationCorruptionBoundaryEvidence.modeled

    val migrationCorruptionBoundaryStillDisabled: Boolean
        get() = migrationCorruptionBoundaryEvidence.stillDisabled

    val migrationCorruptionClassifiesFailureKinds: Boolean
        get() = migrationCorruptionBoundaryEvidence.classifiesFailureKinds

    val migrationCorruptionDoesNotParseRealStorage: Boolean
        get() = migrationCorruptionBoundaryEvidence.doesNotParseRealStorage

    val migrationCorruptionDoesNotRepairStorage: Boolean
        get() = migrationCorruptionBoundaryEvidence.doesNotRepairStorage

    val migrationCorruptionDoesNotRunMigration: Boolean
        get() = migrationCorruptionBoundaryEvidence.doesNotRunMigration

    val migrationCorruptionDoesNotEnableUnlock: Boolean
        get() = migrationCorruptionBoundaryEvidence.doesNotEnableUnlock

    val migrationCorruptionDoesNotEnablePersistence: Boolean
        get() = migrationCorruptionBoundaryEvidence.doesNotEnablePersistence

    val migrationCorruptionDoesNotEnableProviderSelection: Boolean
        get() = migrationCorruptionBoundaryEvidence.doesNotEnableProviderSelection

    val migrationCorruptionFailureVocabularyModeled: Boolean
        get() = migrationCorruptionBoundaryEvidence.failureVocabularyModeled

    val providerOperationAuthorizationBoundaryPolicyId: String
        get() = providerOperationAuthorizationBoundaryEvidence.policyId

    val providerOperationAuthorizationBoundaryStatus: ProductionProviderConstructionContractStatus
        get() = providerOperationAuthorizationBoundaryEvidence.status

    val providerOperationAuthorizationBoundaryRules: Set<ProductionProviderOperationAuthorizationBoundaryRule>
        get() = providerOperationAuthorizationBoundaryEvidence.rules

    val providerOperationAuthorizationBoundaryModeled: Boolean
        get() = providerOperationAuthorizationBoundaryEvidence.modeled

    val providerOperationAuthorizationStillDisabled: Boolean
        get() = providerOperationAuthorizationBoundaryEvidence.stillDisabled

    val providerOperationAuthorizationBlocksAllOperations: Boolean
        get() = providerOperationAuthorizationBoundaryEvidence.blocksAllOperations

    val providerOperationAuthorizationDoesNotRunCrypto: Boolean
        get() = providerOperationAuthorizationBoundaryEvidence.doesNotRunCrypto

    val providerOperationAuthorizationDoesNotRunKat: Boolean
        get() = providerOperationAuthorizationBoundaryEvidence.doesNotRunKat

    val providerOperationAuthorizationDoesNotGenerateRandomness: Boolean
        get() = providerOperationAuthorizationBoundaryEvidence.doesNotGenerateRandomness

    val providerOperationAuthorizationDoesNotEnableUnlock: Boolean
        get() = providerOperationAuthorizationBoundaryEvidence.doesNotEnableUnlock

    val providerOperationAuthorizationDoesNotEnablePersistence: Boolean
        get() = providerOperationAuthorizationBoundaryEvidence.doesNotEnablePersistence

    val providerOperationAuthorizationDoesNotEnableProviderSelection: Boolean
        get() = providerOperationAuthorizationBoundaryEvidence.doesNotEnableProviderSelection

    val providerOperationFailureVocabularyModeled: Boolean
        get() = providerOperationAuthorizationBoundaryEvidence.failureVocabularyModeled

    val runtimeRandomnessAuthorizationBoundaryPolicyId: String
        get() = runtimeRandomnessAuthorizationBoundaryEvidence.policyId

    val runtimeRandomnessAuthorizationBoundaryStatus: ProductionProviderConstructionContractStatus
        get() = runtimeRandomnessAuthorizationBoundaryEvidence.status

    val runtimeRandomnessAuthorizationBoundaryRules:
        Set<ProductionProviderRuntimeRandomnessAuthorizationBoundaryRule>
        get() = runtimeRandomnessAuthorizationBoundaryEvidence.rules

    val runtimeRandomnessAuthorizationBoundaryModeled: Boolean
        get() = runtimeRandomnessAuthorizationBoundaryEvidence.modeled

    val runtimeRandomnessAuthorizationStillDisabled: Boolean
        get() = runtimeRandomnessAuthorizationBoundaryEvidence.stillDisabled

    val runtimeRandomnessAuthorizationBlocksAllOperations: Boolean
        get() = runtimeRandomnessAuthorizationBoundaryEvidence.blocksAllOperations

    val runtimeRandomnessAuthorizationDoesNotCallSecureRandom: Boolean
        get() = runtimeRandomnessAuthorizationBoundaryEvidence.doesNotCallSecureRandom

    val runtimeRandomnessAuthorizationDoesNotGenerateEntropy: Boolean
        get() = runtimeRandomnessAuthorizationBoundaryEvidence.doesNotGenerateEntropy

    val runtimeRandomnessAuthorizationDoesNotGenerateSaltOrNonce: Boolean
        get() = runtimeRandomnessAuthorizationBoundaryEvidence.doesNotGenerateSaltOrNonce

    val runtimeRandomnessAuthorizationDoesNotGenerateKeys: Boolean
        get() = runtimeRandomnessAuthorizationBoundaryEvidence.doesNotGenerateKeys

    val runtimeRandomnessAuthorizationDoesNotEnableProviderOperations: Boolean
        get() = runtimeRandomnessAuthorizationBoundaryEvidence.doesNotEnableProviderOperations

    val runtimeRandomnessAuthorizationDoesNotEnableUnlock: Boolean
        get() = runtimeRandomnessAuthorizationBoundaryEvidence.doesNotEnableUnlock

    val runtimeRandomnessAuthorizationDoesNotEnablePersistence: Boolean
        get() = runtimeRandomnessAuthorizationBoundaryEvidence.doesNotEnablePersistence

    val runtimeRandomnessAuthorizationDoesNotEnableProviderSelection: Boolean
        get() = runtimeRandomnessAuthorizationBoundaryEvidence.doesNotEnableProviderSelection

    val runtimeRandomnessFailureVocabularyModeled: Boolean
        get() = runtimeRandomnessAuthorizationBoundaryEvidence.failureVocabularyModeled

    val kdfCalibrationAuthorizationBoundaryPolicyId: String
        get() = kdfCalibrationAuthorizationBoundaryEvidence.policyId

    val kdfCalibrationAuthorizationBoundaryStatus: ProductionProviderConstructionContractStatus
        get() = kdfCalibrationAuthorizationBoundaryEvidence.status

    val kdfCalibrationAuthorizationBoundaryRules:
        Set<ProductionProviderKdfCalibrationAuthorizationBoundaryRule>
        get() = kdfCalibrationAuthorizationBoundaryEvidence.rules

    val kdfCalibrationAuthorizationBoundaryModeled: Boolean
        get() = kdfCalibrationAuthorizationBoundaryEvidence.modeled

    val kdfCalibrationAuthorizationStillDisabled: Boolean
        get() = kdfCalibrationAuthorizationBoundaryEvidence.stillDisabled

    val kdfCalibrationAuthorizationBlocksAllOperations: Boolean
        get() = kdfCalibrationAuthorizationBoundaryEvidence.blocksAllOperations

    val kdfCalibrationAuthorizationDoesNotRunArgon2id: Boolean
        get() = kdfCalibrationAuthorizationBoundaryEvidence.doesNotRunArgon2id

    val kdfCalibrationAuthorizationDoesNotRunCalibration: Boolean
        get() = kdfCalibrationAuthorizationBoundaryEvidence.doesNotRunCalibration

    val kdfCalibrationAuthorizationDoesNotApproveFinalParameters: Boolean
        get() = kdfCalibrationAuthorizationBoundaryEvidence.doesNotApproveFinalParameters

    val kdfCalibrationAuthorizationDoesNotEnableUnlock: Boolean
        get() = kdfCalibrationAuthorizationBoundaryEvidence.doesNotEnableUnlock

    val kdfCalibrationAuthorizationDoesNotEnablePersistence: Boolean
        get() = kdfCalibrationAuthorizationBoundaryEvidence.doesNotEnablePersistence

    val kdfCalibrationAuthorizationDoesNotEnableProviderSelection: Boolean
        get() = kdfCalibrationAuthorizationBoundaryEvidence.doesNotEnableProviderSelection

    val kdfCalibrationFailureVocabularyModeled: Boolean
        get() = kdfCalibrationAuthorizationBoundaryEvidence.failureVocabularyModeled

    val secureStorageAuthorizationBoundaryPolicyId: String
        get() = secureStorageAuthorizationBoundaryEvidence.policyId

    val secureStorageAuthorizationBoundaryStatus: ProductionProviderConstructionContractStatus
        get() = secureStorageAuthorizationBoundaryEvidence.status

    val secureStorageAuthorizationBoundaryRules:
        Set<ProductionProviderSecureStorageAuthorizationBoundaryRule>
        get() = secureStorageAuthorizationBoundaryEvidence.rules

    val secureStorageAuthorizationBoundaryModeled: Boolean
        get() = secureStorageAuthorizationBoundaryEvidence.modeled

    val secureStorageAuthorizationStillDisabled: Boolean
        get() = secureStorageAuthorizationBoundaryEvidence.stillDisabled

    val secureStorageAuthorizationBlocksAllOperations: Boolean
        get() = secureStorageAuthorizationBoundaryEvidence.blocksAllOperations

    val secureStorageAuthorizationDoesNotStoreSecrets: Boolean
        get() = secureStorageAuthorizationBoundaryEvidence.doesNotStoreSecrets

    val secureStorageAuthorizationDoesNotUseOsKeyrings: Boolean
        get() = secureStorageAuthorizationBoundaryEvidence.doesNotUseOsKeyrings

    val secureStorageAuthorizationDoesNotUsePasswordManagers: Boolean
        get() = secureStorageAuthorizationBoundaryEvidence.doesNotUsePasswordManagers

    val secureStorageAuthorizationDoesNotUseSettingsStorage: Boolean
        get() = secureStorageAuthorizationBoundaryEvidence.doesNotUseSettingsStorage

    val secureStorageAuthorizationDoesNotEnableUnlock: Boolean
        get() = secureStorageAuthorizationBoundaryEvidence.doesNotEnableUnlock

    val secureStorageAuthorizationDoesNotEnablePersistence: Boolean
        get() = secureStorageAuthorizationBoundaryEvidence.doesNotEnablePersistence

    val secureStorageAuthorizationDoesNotEnableProviderSelection: Boolean
        get() = secureStorageAuthorizationBoundaryEvidence.doesNotEnableProviderSelection

    val secureStorageFailureVocabularyModeled: Boolean
        get() = secureStorageAuthorizationBoundaryEvidence.failureVocabularyModeled
}

enum class ProductionProviderPassphraseForbiddenClass(val label: String) {
    EmptyPassphrase("empty passphrase"),
    UnicodeControlCharacters("Unicode control characters"),
    UnicodeWhitespaceCharacters("Unicode whitespace characters"),
    UnicodeSeparatorCharacters("Unicode separator characters"),
    InvisibleFormatCharacters("invisible format characters"),
}

enum class ProductionProviderPassphraseNoTransformRule(val label: String) {
    DoNotTrim("do not trim"),
    DoNotLowercase("do not lowercase"),
    DoNotUppercase("do not uppercase"),
    DoNotCollapseRepeatedCharacters("do not collapse repeated characters"),
    DoNotApplyLocaleSensitiveTransforms("do not apply locale-sensitive transforms"),
    DoNotSilentlyRemoveCharacters("do not silently remove characters"),
}

enum class ProductionProviderPassphraseAllowedClass(val label: String) {
    VisibleUnicodeLetters("visible Unicode letters"),
    VisibleUnicodeCombiningMarksAfterNfc("visible Unicode combining marks valid after NFC"),
    VisibleUnicodeNumbers("visible Unicode numbers"),
    VisibleUnicodePunctuation("visible Unicode punctuation"),
    VisibleUnicodeSymbols("visible Unicode symbols"),
    EmojiWithoutRejectedCharacters("emoji without rejected control, whitespace, separator, or format characters"),
}

data class ProductionProviderPassphraseEncodingPolicy(
    val policyId: String,
    val contractStatus: ProductionProviderConstructionContractStatus,
    val normalizationForm: String,
    val encodedForm: String,
    val forbiddenClasses: Set<ProductionProviderPassphraseForbiddenClass>,
    val noTransformRules: Set<ProductionProviderPassphraseNoTransformRule>,
    val allowedClasses: Set<ProductionProviderPassphraseAllowedClass>,
    val composedAndDecomposedFormsMustCanonicalizeToSameNfcBytes: Boolean,
    val visibleSeparatorsSuggestedAsAlternativesToSpaces: Set<String>,
    val productionValidationImplemented: Boolean,
    val productionVaultCreationWired: Boolean,
)

data class ProductionProviderArgon2idRootDerivationPolicy(
    val contractStatus: ProductionProviderConstructionContractStatus,
    val implementation: String,
    val type: SkaldVaultV1Argon2idType,
    val version: Argon2idVersion,
    val minimumMemoryMiB: Int,
    val minimumIterations: Int,
    val parallelism: Int,
    val minimumSaltBytes: Int,
    val preferredNewVaultSaltBytes: Int,
    val outputRootMaterialBytes: Int,
    val explicitCallerSuppliedParametersRequired: Boolean,
    val automaticCalibrationImplemented: Boolean,
    val parameterDowngradeImplemented: Boolean,
    val productionRootDerivationImplemented: Boolean,
    val productionVaultCreationWired: Boolean,
)

enum class ProductionProviderTinkRawKeyFeasibilityStatus(
    val label: String,
    val satisfiesFeasibilityGate: Boolean,
) {
    FEASIBLE_PUBLIC_RAW_KEY_API(
        label = "public supported API constructs XChaCha20-Poly1305 from caller-supplied raw key bytes",
        satisfiesFeasibilityGate = true,
    ),
    NOT_FEASIBLE_WITH_CURRENT_TINK_API(
        label = "current Tink API does not provide a clean public raw-key construction path",
        satisfiesFeasibilityGate = false,
    ),
    INCONCLUSIVE_REQUIRES_HUMAN_REVIEW(
        label = "API path is ambiguous, deprecated, experimental, unstable, or poorly documented",
        satisfiesFeasibilityGate = false,
    ),
}

enum class ProductionProviderAndroidTinkRawKeyFeasibilityStatus(
    val label: String,
    val satisfiesAndroidFeasibilityGate: Boolean,
) {
    ANDROID_FEASIBLE_PUBLIC_RAW_KEY_API(
        label = "Android public supported API constructs XChaCha20-Poly1305 from caller-supplied raw key bytes",
        satisfiesAndroidFeasibilityGate = true,
    ),
    ANDROID_NOT_FEASIBLE_WITH_CURRENT_TINK_API(
        label = "current Android Tink API does not provide a clean public raw-key construction path",
        satisfiesAndroidFeasibilityGate = false,
    ),
    ANDROID_INCONCLUSIVE_REQUIRES_HUMAN_REVIEW(
        label = "Android API path is ambiguous, deprecated, experimental, unstable, platform-fragile, or poorly documented",
        satisfiesAndroidFeasibilityGate = false,
    ),
}

data class ProductionProviderTinkRawKeyHandlingPolicy(
    val preferredCallerSuppliedDerivedRawKeyMaterial: Boolean,
    val persistedPlaintextTinkKeysetsAllowed: Boolean,
    val persistedEncryptedTinkKeysetsAllowedInV1: Boolean,
    val randomTinkVaultKeysAllowed: Boolean,
    val tinkKeyRotationInV1Allowed: Boolean,
    val multipleActiveAeadKeysInV1Allowed: Boolean,
    val publicSupportedApiRequired: Boolean,
    val internalUnsupportedReflectiveApisAllowed: Boolean,
    val fallbackEncryptedKeysetModelImplemented: Boolean,
    val productionAeadExecutionImplemented: Boolean,
    val stillDisabledRecordAeadBuildingBlockImplemented: Boolean,
    val desktopFeasibilityStatus: ProductionProviderTinkRawKeyFeasibilityStatus,
    val androidFeasibilityStatus: ProductionProviderAndroidTinkRawKeyFeasibilityStatus,
    val desktopTestedPublicApiPath: String,
    val androidTestedPublicApiPath: String,
    val androidPathMatchesDesktopPath: Boolean,
    val transientInMemoryTinkKeysetHandleRequired: Boolean,
    val persistedTinkKeysetRequired: Boolean,
    val randomTinkGeneratedVaultKeyRequired: Boolean,
) {
    val crossPlatformFeasibilitySatisfied: Boolean
        get() = desktopFeasibilityStatus.satisfiesFeasibilityGate &&
            androidFeasibilityStatus.satisfiesAndroidFeasibilityGate
}

data class ProductionProviderArgon2idAcceptancePolicy(
    val version: Argon2idVersion,
    val minimumMemoryMiB: Int,
    val passes: Int,
    val lanes: Int,
    val minimumSaltBytes: Int,
    val preferredNewVaultSaltBytes: Int,
    val derivedRootMaterialBytes: Int,
    val preferredUnlockMillis: Int,
    val acceptableUnlockMillis: Int,
    val twoSecondsIsFailureCondition: Boolean,
    val weakenToForceSubOneSecondAllowed: Boolean,
    val boundedPerPlatformCalibrationRequired: Boolean,
    val sharedMinimumFloorAcrossPlatforms: Boolean,
    val desktopMaySelectStrongerParametersThanAndroid: Boolean,
    val minimumFloorAllocationFailureBlocksVaultCreation: Boolean,
    val storedParameterAllocationFailureBlocksUnlock: Boolean,
    val existingVaultParametersAuthoritative: Boolean,
    val silentParameterDowngradeAllowed: Boolean,
    val downgradeMigrationRequiresSuccessfulUnlockAndExplicitUserAction: Boolean,
    val weakerDeviceFailureMode: ProductionProviderWeakDeviceFailureMode,
)

data class ProductionProviderAeadAcceptancePolicy(
    val primitive: EncryptedVaultAeadAlgorithm,
    val aadPolicyId: String,
    val aadPolicyVersion: Int,
    val recordFormatPolicyId: String,
    val recordFormatPolicyVersion: Int,
    val contractStatus: ProductionProviderConstructionContractStatus,
    val nonKeyCommitting: Boolean,
    val successfulDecryptAloneProvesCorrectVaultKey: Boolean,
    val vaultLevelKeyCommitmentRequiredBeforeRecordDecrypt: Boolean,
    val headerAuthenticationRequiredBeforeRecordDecrypt: Boolean,
    val strictAadBindingFields: Set<ProductionProviderAadBindingField>,
    val aadMismatchFailClosedConditions: Set<ProductionProviderAadFailClosedCondition>,
    val requiredTamperCoverage: Set<ProductionProviderTamperCoverage>,
    val rawKeyFeasibilityBypassesHeaderCommitment: Boolean,
    val wrongPassphraseResolvedByHeaderCommitmentBeforeRecordDecrypt: Boolean,
    val strictAadSerializationImplemented: Boolean,
    val recordAeadBuildingBlockImplemented: Boolean,
    val recordVersionCounterBoundIntoAad: Boolean,
    val staleRecordEnforcementDeferredToManifestOrStorage: Boolean,
    val productionProviderWired: Boolean,
    val productionAeadExecutionImplemented: Boolean,
)

data class ProductionProviderRuntimeRandomnessAcceptancePolicy(
    val sourceKind: RuntimeRandomnessSourceKind,
    val exactProviderPathLabel: String,
    val unknownProviderStateBlocksVaultCreation: Boolean,
    val forbiddenRandomApisRejected: Boolean,
    val tinySampleIsEntropyQualityProof: Boolean,
)

data class ProductionProviderAndroidWrappingAcceptancePolicy(
    val optionalFutureConvenienceLayerOnly: Boolean,
    val hardwareOrBiometricWrappingRequired: Boolean,
    val passphrasePrimaryAuthority: Boolean,
    val passwordOnlyModeFirstClass: Boolean,
    val biometricUnlockReplacesPassphrase: Boolean,
    val roughlyWeeklyPassphrasePromptAfterBiometricUnlockRequired: Boolean,
)

data class ProductionProviderStillDisabledFacadePolicy(
    val contractStatus: ProductionProviderConstructionContractStatus,
    val facadeImplemented: Boolean,
    val metadataOnly: Boolean,
    val operationsDisabled: Boolean,
    val selectableThroughProviderRegistry: Boolean,
    val debugOrTestFlagCanSelect: Boolean,
    val vaultCreationEnabled: Boolean,
    val vaultUnlockEnabled: Boolean,
    val vaultPersistenceEnabled: Boolean,
    val manifestStorageImplemented: Boolean,
    val secureStorageEnabled: Boolean,
    val katHarnessSuccessImpliesSelectability: Boolean,
    val calibrationEvidenceImpliesSelectability: Boolean,
    val releaseApprovalPresent: Boolean,
)

data class ProductionProviderAcceptanceGateState(
    val gate: ProductionProviderAcceptanceGate,
    val state: ProductionProviderAcceptanceEvidenceState,
    val safeDetail: String,
) {
    val satisfied: Boolean
        get() = state.satisfiesGate
}

data class UserFacingProductionProviderFailureWarning(
    val title: String,
    val safeMessage: String,
    val blockers: Set<ProductionProviderAcceptanceBlocker>,
)

data class ProductionProviderAcceptanceEvidence(
    val gateStates: Map<ProductionProviderAcceptanceGate, ProductionProviderAcceptanceEvidenceState>,
) {
    fun stateFor(gate: ProductionProviderAcceptanceGate): ProductionProviderAcceptanceEvidenceState =
        gateStates[gate] ?: ProductionProviderAcceptanceEvidenceState.Unknown

    companion object {
        fun currentDesignOnly(): ProductionProviderAcceptanceEvidence =
            ProductionProviderAcceptanceEvidence(
                gateStates = mapOf(
                    ProductionProviderAcceptanceGate.ExactProviderSuitePinned to
                        ProductionProviderAcceptanceEvidenceState.Satisfied,
                    ProductionProviderAcceptanceGate.XChaCha20Poly1305PrimitivePinned to
                        ProductionProviderAcceptanceEvidenceState.Satisfied,
                    ProductionProviderAcceptanceGate.UnknownRandomnessProviderStateRejected to
                        ProductionProviderAcceptanceEvidenceState.Satisfied,
                    ProductionProviderAcceptanceGate.ForbiddenRandomApisGuarded to
                        ProductionProviderAcceptanceEvidenceState.Satisfied,
                    ProductionProviderAcceptanceGate.AndroidOptionalWrappingSeparateFromPassphrase to
                        ProductionProviderAcceptanceEvidenceState.Satisfied,
                    ProductionProviderAcceptanceGate.TinkRawKeyFeasibilityApproved to
                        ProductionProviderAcceptanceEvidenceState.Satisfied,
                    ProductionProviderAcceptanceGate.Argon2idPolicyApproved to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.Argon2idBoundedCalibrationApproved to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.Argon2idCalibrationAndMemoryFailureApproved to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.PassphraseEncodingPolicyApproved to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.Argon2idPassphraseRootDerivationImplemented to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.HeaderCommitmentPolicyApproved to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.HkdfSha256KeyExpansionPrimitiveApproved to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.HmacSha256HeaderCommitmentPrimitiveApproved to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.KeyExpansionOutputLayoutApproved to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.PrimitiveThreatModelRationaleDocumented to
                        ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                    ProductionProviderAcceptanceGate.CanonicalHeaderByteVectorsApproved to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.HkdfSha256VectorContractApproved to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.HmacSha256HeaderCommitmentVectorContractApproved to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.CanonicalHeaderEncodingPolicyApproved to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.KeySeparationLabelsPolicyApproved to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.AeadAadPolicyApproved to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.TinkNonKeyCommitmentMitigationApproved to
                        ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                    ProductionProviderAcceptanceGate.ProviderLevelKatStrategyApproved to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.RandomizedAeadBehavioralKatPolicyApproved to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.IntegratedVerificationOrderKatPolicyApproved to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.VaultContainerContractApproved to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.ManifestContractApproved to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.StaleRecordManifestPolicyApproved to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.StoragePolicyContractApproved to
                        ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                    ProductionProviderAcceptanceGate.PlatformStorageBoundaryContractApproved to
                        ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                    ProductionProviderAcceptanceGate.AtomicityCrashRecoveryContractApproved to
                        ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                    ProductionProviderAcceptanceGate.AtomicWriteStrategyContractApproved to
                        ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                    ProductionProviderAcceptanceGate.CrashRecoveryContractApproved to
                        ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                    ProductionProviderAcceptanceGate.StorageInterruptionTestContractApproved to
                        ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                    ProductionProviderAcceptanceGate.StorageAtomicityCrashSimulatorExecuted to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.StorageFailureModelContractApproved to
                        ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                    ProductionProviderAcceptanceGate.StorageNamespacePathHygieneContractApproved to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.StorageLayoutPlanImplementedAndTested to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.PathContainmentPlannerImplementedAndTested to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.PlatformStorageRootContractApproved to
                        ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                    ProductionProviderAcceptanceGate.PlatformRootSettingsPolicyApproved to
                        ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                    ProductionProviderAcceptanceGate.LinuxCustomRootValidationPolicyImplementedAndTested to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.LinuxRootResolutionPolicyImplementedAndTested to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.PlatformRootResolverBoundaryImplementedAndTested to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.PlatformPathConstructionBoundaryImplementedAndTested to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.StorageSafetyPreflightBoundaryImplementedAndTested to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.DisabledStorageServiceFacadeImplementedAndTested to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.PersistenceReadinessGateImplementedAndTested to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.LockSessionLifecycleBoundaryImplementedAndTested to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.RedactionLeakageBoundaryImplementedAndTested to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.PassphrasePolicyBoundaryImplementedAndTested to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.ClearWipeStrategyBoundaryImplementedAndTested to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.MigrationCorruptionBoundaryImplementedAndTested to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.ProviderOperationAuthorizationBoundaryImplementedAndTested to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.RuntimeRandomnessAuthorizationBoundaryImplementedAndTested to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.KdfCalibrationAuthorizationBoundaryImplementedAndTested to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.SecureStorageAuthorizationBoundaryImplementedAndTested to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.RedactionLeakageChecksPassed to
                        ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                    ProductionProviderAcceptanceGate.SafePathConstructionContractApproved to
                        ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                    ProductionProviderAcceptanceGate.SymlinkTraversalContractApproved to
                        ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                    ProductionProviderAcceptanceGate.StoragePermissionOwnershipContractApproved to
                        ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                    ProductionProviderAcceptanceGate.DurabilityCapabilityContractApproved to
                        ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                    ProductionProviderAcceptanceGate.DurabilityFailClosedPolicyApproved to
                        ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                    ProductionProviderAcceptanceGate.WarningOnlyDurabilityPersistenceRejected to
                        ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                    ProductionProviderAcceptanceGate.SecureStorageBoundaryContractApproved to
                        ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                    ProductionProviderAcceptanceGate.RollbackLimitationAndAntiRollbackAnchorReviewed to
                        ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
                    ProductionProviderAcceptanceGate.StillDisabledProviderFacadeApproved to
                        ProductionProviderAcceptanceEvidenceState.ImplementedTested,
                    ProductionProviderAcceptanceGate.ReleaseReadinessExcludesDebugTestProviders to
                        ProductionProviderAcceptanceEvidenceState.Satisfied,
                ),
            )

        fun allSatisfiedForReviewOnly(): ProductionProviderAcceptanceEvidence =
            ProductionProviderAcceptanceEvidence(
                gateStates = ProductionProviderAcceptanceGate.entries.associateWith {
                    ProductionProviderAcceptanceEvidenceState.Satisfied
                },
            )
    }
}

data class ProductionProviderAcceptanceAssessment(
    val contract: ProductionProviderAcceptanceContract,
    val gateStates: List<ProductionProviderAcceptanceGateState>,
    val blockers: Set<ProductionProviderAcceptanceBlocker>,
    val userFacingWarning: UserFacingProductionProviderFailureWarning?,
    val providerImplementationState: VaultCryptoProviderImplementationState,
) {
    val allRequiredGatesSatisfied: Boolean
        get() = gateStates.all { it.satisfied }

    val productionProviderSelectable: Boolean = false

    val productionPersistenceAllowed: Boolean = false
}

data class ProductionProviderAcceptanceContract(
    val suite: ProductionProviderSuiteIdentity,
    val requiredGates: Set<ProductionProviderAcceptanceGate>,
    val argon2idPolicy: ProductionProviderArgon2idAcceptancePolicy,
    val headerCommitmentPolicy: ProductionProviderHeaderCommitmentAcceptancePolicy,
    val keyExpansionPrimitivePolicy: ProductionProviderKeyExpansionPrimitivePolicy,
    val headerCommitmentPrimitivePolicy: ProductionProviderHeaderCommitmentPrimitivePolicy,
    val keyExpansionOutputLayoutPolicy: ProductionProviderKeyExpansionOutputLayoutPolicy,
    val primitiveThreatModelPolicy: ProductionProviderPrimitiveThreatModelPolicy,
    val canonicalHeaderVectorContract: ProductionProviderCanonicalHeaderVectorContract,
    val hkdfVectorContract: ProductionProviderHkdfVectorContract,
    val hmacHeaderCommitmentVectorContract: ProductionProviderHmacHeaderCommitmentVectorContract,
    val providerLevelKatStrategyPolicy: ProductionProviderLevelKatStrategyPolicy,
    val randomizedAeadBehavioralKatPolicy: ProductionProviderRandomizedAeadBehavioralKatPolicy,
    val integratedVerificationOrderKatPolicy: ProductionProviderIntegratedVerificationOrderKatPolicy,
    val staleRecordManifestPolicy: ProductionProviderStaleRecordManifestPolicy,
    val containerManifestStorageContract: ProductionProviderContainerManifestStorageContract,
    val canonicalHeaderEncodingPolicy: ProductionProviderCanonicalHeaderEncodingPolicy,
    val keySeparationPolicy: ProductionProviderKeySeparationPolicy,
    val passphraseEncodingPolicy: ProductionProviderPassphraseEncodingPolicy,
    val argon2idRootDerivationPolicy: ProductionProviderArgon2idRootDerivationPolicy,
    val tinkRawKeyHandlingPolicy: ProductionProviderTinkRawKeyHandlingPolicy,
    val aeadPolicy: ProductionProviderAeadAcceptancePolicy,
    val runtimeRandomnessPolicy: ProductionProviderRuntimeRandomnessAcceptancePolicy,
    val androidWrappingPolicy: ProductionProviderAndroidWrappingAcceptancePolicy,
    val stillDisabledProviderFacadePolicy: ProductionProviderStillDisabledFacadePolicy,
) {
    fun assess(
        evidence: ProductionProviderAcceptanceEvidence = ProductionProviderAcceptanceEvidence.currentDesignOnly(),
        providerImplementationState: VaultCryptoProviderImplementationState =
            VaultCryptoProviderImplementationState.ExecutableUnavailable,
        releaseReadiness: Boolean = true,
    ): ProductionProviderAcceptanceAssessment {
        val gateStates = requiredGates.map { gate ->
            val state = evidence.stateFor(gate)
            ProductionProviderAcceptanceGateState(
                gate = gate,
                state = state,
                safeDetail = when (state) {
                    ProductionProviderAcceptanceEvidenceState.Satisfied -> "Gate evidence is recorded."
                    ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly ->
                        "Gate evidence is documented and modeled only."
                    ProductionProviderAcceptanceEvidenceState.VectorInputsDefinedOutputsPending ->
                        "Vector inputs are defined, but one or more expected outputs are pending."
                    ProductionProviderAcceptanceEvidenceState.TestScopeVectorsComplete ->
                        "Vector evidence is complete in test scope only; production implementation is absent."
                    ProductionProviderAcceptanceEvidenceState.ApprovedForFutureImplementation ->
                        "Gate evidence is approved for future implementation only."
                    ProductionProviderAcceptanceEvidenceState.ImplementedTested ->
                        "Gate evidence is implemented and tested."
                    ProductionProviderAcceptanceEvidenceState.Missing -> "Gate evidence is missing."
                    ProductionProviderAcceptanceEvidenceState.Failed -> "Gate evidence failed."
                    ProductionProviderAcceptanceEvidenceState.Unsupported -> "Gate evidence is unsupported."
                    ProductionProviderAcceptanceEvidenceState.Unknown -> "Gate evidence is unknown."
                },
            )
        }
        val blockers = buildSet {
            if (gateStates.any { it.state == ProductionProviderAcceptanceEvidenceState.Missing }) {
                add(ProductionProviderAcceptanceBlocker.MissingGateEvidence)
            }
            if (
                gateStates.any {
                    it.state == ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly ||
                        it.state == ProductionProviderAcceptanceEvidenceState.VectorInputsDefinedOutputsPending ||
                        it.state == ProductionProviderAcceptanceEvidenceState.TestScopeVectorsComplete ||
                        it.state == ProductionProviderAcceptanceEvidenceState.ApprovedForFutureImplementation
                }
            ) {
                add(ProductionProviderAcceptanceBlocker.ModelOnlyGateEvidence)
            }
            if (
                gateStates.any {
                    it.state == ProductionProviderAcceptanceEvidenceState.VectorInputsDefinedOutputsPending
                }
            ) {
                add(ProductionProviderAcceptanceBlocker.PendingVectorEvidence)
            }
            if (
                gateStates.any {
                    it.state == ProductionProviderAcceptanceEvidenceState.TestScopeVectorsComplete
                }
            ) {
                add(ProductionProviderAcceptanceBlocker.TestScopeVectorEvidenceOnly)
            }
            if (gateStates.any { it.state == ProductionProviderAcceptanceEvidenceState.Failed }) {
                add(ProductionProviderAcceptanceBlocker.FailedGateEvidence)
            }
            if (gateStates.any { it.state == ProductionProviderAcceptanceEvidenceState.Unsupported }) {
                add(ProductionProviderAcceptanceBlocker.UnsupportedGateEvidence)
            }
            if (gateStates.any { it.state == ProductionProviderAcceptanceEvidenceState.Unknown }) {
                add(ProductionProviderAcceptanceBlocker.UnknownGateEvidence)
            }
            if (releaseReadiness && providerImplementationState.testOnly) {
                add(ProductionProviderAcceptanceBlocker.DebugOrTestProviderNotReleaseSelectable)
            }
            add(ProductionProviderAcceptanceBlocker.ProductionProviderSelectionStillDisabled)
            add(ProductionProviderAcceptanceBlocker.ProductionPersistenceStillDisabled)
        }
        return ProductionProviderAcceptanceAssessment(
            contract = this,
            gateStates = gateStates,
            blockers = blockers,
            userFacingWarning = warning(blockers),
            providerImplementationState = providerImplementationState,
        )
    }

    private fun warning(
        blockers: Set<ProductionProviderAcceptanceBlocker>,
    ): UserFacingProductionProviderFailureWarning? =
        if (blockers.isEmpty()) {
            null
        } else {
            UserFacingProductionProviderFailureWarning(
                title = "Vault provider unavailable",
                safeMessage = "The encrypted vault provider is not approved for production use; vault creation and persistence must remain disabled.",
                blockers = blockers,
            )
        }

    companion object {
        fun v1(): ProductionProviderAcceptanceContract =
            ProductionProviderAcceptanceContract(
                suite = ProductionProviderSuiteIdentity(
                    suiteId = "skald-vault-v1-bouncycastle-argon2id-tink-xchacha20poly1305-os-securerandom",
                    model = ProductionProviderSuiteModel.SinglePinnedSuite,
                    kdf = ProductionProviderPrimitiveIdentity(
                        role = ProductionProviderPrimitiveRole.Kdf,
                        implementation = "Bouncy Castle",
                        algorithm = "Argon2id",
                        versionOrTemplate = "Argon2id version 19",
                        artifact = "org.bouncycastle:bcprov-jdk18on:1.84",
                    ),
                    aead = ProductionProviderPrimitiveIdentity(
                        role = ProductionProviderPrimitiveRole.Aead,
                        implementation = "Tink",
                        algorithm = "XChaCha20-Poly1305",
                        versionOrTemplate = "Tink XChaCha20-Poly1305 key/template pinned for v1",
                        artifact = "com.google.crypto.tink:tink/tink-android:1.21.0",
                    ),
                    runtimeRandomness = ProductionProviderPrimitiveIdentity(
                        role = ProductionProviderPrimitiveRole.RuntimeRandomness,
                        implementation = "OS SecureRandom",
                        algorithm = "OS cryptographic randomness",
                        versionOrTemplate = "provider and algorithm evidence required at runtime",
                        artifact = null,
                    ),
                    pinnedArtifacts = setOf(
                        "org.bouncycastle:bcprov-jdk18on:1.84",
                        "com.google.crypto.tink:tink:1.21.0",
                        "com.google.crypto.tink:tink-android:1.21.0",
                    ),
                ),
                requiredGates = ProductionProviderAcceptanceGate.entries.toSet(),
                argon2idPolicy = ProductionProviderArgon2idAcceptancePolicy(
                    version = Argon2idVersion.Version19,
                    minimumMemoryMiB = 64,
                    passes = 3,
                    lanes = 1,
                    minimumSaltBytes = 16,
                    preferredNewVaultSaltBytes = 32,
                    derivedRootMaterialBytes = 64,
                    preferredUnlockMillis = 1_000,
                    acceptableUnlockMillis = 2_000,
                    twoSecondsIsFailureCondition = false,
                    weakenToForceSubOneSecondAllowed = false,
                    boundedPerPlatformCalibrationRequired = true,
                    sharedMinimumFloorAcrossPlatforms = true,
                    desktopMaySelectStrongerParametersThanAndroid = true,
                    minimumFloorAllocationFailureBlocksVaultCreation = true,
                    storedParameterAllocationFailureBlocksUnlock = true,
                    existingVaultParametersAuthoritative = true,
                    silentParameterDowngradeAllowed = false,
                    downgradeMigrationRequiresSuccessfulUnlockAndExplicitUserAction = true,
                    weakerDeviceFailureMode = ProductionProviderWeakDeviceFailureMode.FailClosedWithUserMessage,
                ),
                headerCommitmentPolicy = ProductionProviderHeaderCommitmentAcceptancePolicy(
                    policyId = "skald-vault-v1-header-commitment-v1",
                    policyVersion = 1,
                    contractStatus = ProductionProviderConstructionContractStatus.ImplementedTested,
                    requiredBeforeRecordDecrypt = true,
                    recordDecryptAllowedBeforeVerification = false,
                    commitmentKeyMaterialSeparatedFromRecordAeadKeyMaterial = true,
                    canonicalHeaderFields = ProductionProviderHeaderCommitmentField.entries.toSet(),
                    failClosedConditions =
                        ProductionProviderHeaderCommitmentFailClosedCondition.entries.toSet(),
                    productionExecutionImplemented = true,
                ),
                keyExpansionPrimitivePolicy = ProductionProviderKeyExpansionPrimitivePolicy(
                    policyId = "skald-vault-v1-hkdf-sha256-key-expansion-v1",
                    primitive = ProductionProviderKeyExpansionPrimitive.HkdfSha256,
                    contractStatus = ProductionProviderConstructionContractStatus.ImplementedTested,
                    argon2idRemainsPasswordKdf = true,
                    usedOnlyAfterArgon2idRootMaterialExists = true,
                    usedDirectlyOnPassphraseAllowed = false,
                    domainSeparatedByStableAsciiLabels = true,
                    avoidsManualRootMaterialSlicing = true,
                    productionHkdfExecutionImplemented = true,
                ),
                headerCommitmentPrimitivePolicy = ProductionProviderHeaderCommitmentPrimitivePolicy(
                    policyId = "skald-vault-v1-hmac-sha256-header-commitment-v1",
                    primitive = ProductionProviderHeaderCommitmentPrimitive.HmacSha256,
                    contractStatus = ProductionProviderConstructionContractStatus.ImplementedTested,
                    inputDescription = "canonical vault header bytes",
                    usesDerivedHeaderCommitmentKey = true,
                    verifiesBeforeRecordDecrypt = true,
                    successfulRecordDecryptAloneProvesCorrectVaultKey = false,
                    productionHmacExecutionImplemented = true,
                    productionHeaderCommitmentComputationImplemented = true,
                ),
                keyExpansionOutputLayoutPolicy = ProductionProviderKeyExpansionOutputLayoutPolicy(
                    contractStatus = ProductionProviderConstructionContractStatus.ImplementedTested,
                    argon2idRootMaterialBytes = 64,
                    headerCommitmentKeyBytes = 32,
                    recordAeadKeyBytes = 32,
                    recordAeadKeyFeedsTinkRawKeyPath = true,
                    reservedFutureWrappingExportMigrationOutputsImplemented = false,
                ),
                primitiveThreatModelPolicy = ProductionProviderPrimitiveThreatModelPolicy(
                    contractStatus = ProductionProviderConstructionContractStatus.DocumentedModelOnly,
                    offlineAttackBecomesPassphraseGuessing = true,
                    dependsOnPassphraseEntropyAndArgon2idParameters = true,
                    hkdfAndHmacExpectedNotWeakLinkWhenCorrectlyImplemented = true,
                    liveEndpointCompromiseCovered = false,
                    weakPassphraseCompensatedByHkdfOrHmac = false,
                ),
                canonicalHeaderVectorContract = ProductionProviderCanonicalHeaderVectorContract(
                    status = ProductionProviderTestVectorContractStatus.ProductionImplementedTested,
                    documentPath =
                        "docs/ENCRYPTED_LOCAL_VAULT_CANONICAL_HEADER_HKDF_HMAC_VECTORS.md",
                    logicalFixtureFieldsDefined = true,
                    canonicalFieldOrderDefined = true,
                    byteEncodingRulesDefined = true,
                    finalCanonicalHeaderHexDocumented = true,
                    testScopeEncoderExists = true,
                    productionSerializerImplemented = true,
                ),
                hkdfVectorContract = ProductionProviderHkdfVectorContract(
                    status = ProductionProviderTestVectorContractStatus.ProductionImplementedTested,
                    documentPath =
                        "docs/ENCRYPTED_LOCAL_VAULT_CANONICAL_HEADER_HKDF_HMAC_VECTORS.md",
                    hash = "SHA-256",
                    inputKeyingMaterialBytes = 64,
                    saltDefined = true,
                    infoConstructionDefined = true,
                    headerCommitmentInfoDefined = true,
                    recordAeadInfoDefined = true,
                    outputBytesPerPurpose = 32,
                    expectedOutputsDocumented = true,
                    testScopeHkdfExecutionExists = true,
                    productionHkdfExecutionImplemented = true,
                ),
                hmacHeaderCommitmentVectorContract =
                    ProductionProviderHmacHeaderCommitmentVectorContract(
                        status = ProductionProviderTestVectorContractStatus.ProductionImplementedTested,
                        documentPath =
                            "docs/ENCRYPTED_LOCAL_VAULT_CANONICAL_HEADER_HKDF_HMAC_VECTORS.md",
                        hash = "SHA-256",
                        hmacKeySource = "HKDF header commitment key vector output",
                        messageSource = "canonical header vector bytes",
                        expectedTagDocumented = true,
                        testScopeHmacExecutionExists = true,
                        productionHmacExecutionImplemented = true,
                        productionHeaderCommitmentExecutionImplemented = true,
                    ),
                providerLevelKatStrategyPolicy = ProductionProviderLevelKatStrategyPolicy(
                    policyId = "skald-vault-v1-provider-level-kat-strategy-v1",
                    contractStatus = ProductionProviderConstructionContractStatus.ImplementedTested,
                    deterministicVectorsRequired = ProductionProviderDeterministicKatVector.entries.toSet(),
                    randomizedAeadBehavioralChecksRequired =
                        ProductionProviderRandomizedAeadBehavioralKatCheck.entries.toSet(),
                    fixedCiphertextHexRequiredForRandomizedAead = false,
                    deterministicAadHexRequired = true,
                    providerLevelKatsMustRunOnDesktopJvm = true,
                    providerLevelKatsMustRunOnAndroid = true,
                    providerKatCompletionImpliesStorageApproval = false,
                    stillDisabledProviderIntegrationHarnessImplemented = true,
                    stillDisabledProviderLevelKatExecutionImplemented = true,
                    productionProviderKatExecutionImplemented = false,
                ),
                randomizedAeadBehavioralKatPolicy = ProductionProviderRandomizedAeadBehavioralKatPolicy(
                    policyId = "skald-vault-v1-randomized-aead-behavioral-kat-v1",
                    contractStatus = ProductionProviderConstructionContractStatus.ImplementedTested,
                    primitive = EncryptedVaultAeadAlgorithm.XChaCha20Poly1305,
                    tinkChoosesNonceInternally = true,
                    fixedCiphertextHexRequired = false,
                    deterministicAadHexRequired = true,
                    behavioralChecksRequired =
                        ProductionProviderRandomizedAeadBehavioralKatCheck.entries.toSet(),
                    publicDeterministicNonceTestModeApproved = false,
                    stillDisabledBehavioralKatExecutionImplemented = true,
                    productionProviderBehavioralKatExecutionImplemented = false,
                ),
                integratedVerificationOrderKatPolicy = ProductionProviderIntegratedVerificationOrderKatPolicy(
                    policyId = "skald-vault-v1-integrated-verification-order-kat-v1",
                    contractStatus = ProductionProviderConstructionContractStatus.ImplementedTested,
                    orderedSteps = listOf(
                        ProductionProviderIntegratedVerificationOrderKatStep.ValidatePassphrasePolicy,
                        ProductionProviderIntegratedVerificationOrderKatStep.DeriveArgon2idRootMaterial,
                        ProductionProviderIntegratedVerificationOrderKatStep.DeriveHkdfSubkeys,
                        ProductionProviderIntegratedVerificationOrderKatStep.CanonicalizeHeaderBytes,
                        ProductionProviderIntegratedVerificationOrderKatStep.VerifyHmacHeaderCommitment,
                        ProductionProviderIntegratedVerificationOrderKatStep
                            .ConstructRecordAeadAfterHeaderCommitment,
                        ProductionProviderIntegratedVerificationOrderKatStep.SerializeStrictAad,
                        ProductionProviderIntegratedVerificationOrderKatStep.DecryptRecord,
                        ProductionProviderIntegratedVerificationOrderKatStep
                            .RejectRecordDecryptWhenHeaderCommitmentFails,
                    ),
                    headerCommitmentMustPrecedeRecordDecrypt = true,
                    recordDecryptRejectedWhenHeaderCommitmentFails = true,
                    stillDisabledProviderIntegrationHarnessImplemented = true,
                    stillDisabledVerificationOrderKatExecutionImplemented = true,
                    fullProviderIntegrationImplemented = false,
                    productionVerificationOrderKatsImplemented = false,
                ),
                staleRecordManifestPolicy = ProductionProviderStaleRecordManifestPolicy(
                    policyId = "skald-vault-v1-stale-record-manifest-policy-v1",
                    manifestStoragePolicyId = "skald-vault-v1-local-manifest-storage-policy-v1",
                    contractStatus = ProductionProviderConstructionContractStatus.ImplementedTested,
                    recordVersionCounterBoundIntoAad = true,
                    bindings = ProductionProviderStaleRecordManifestBinding.entries.toSet(),
                    requirements = ProductionProviderStaleRecordManifestRequirement.entries.toSet(),
                    manifestParserImplemented = true,
                    manifestWriterImplemented = true,
                    staleRecordDecisionPolicyImplemented = true,
                    manifestReadWriteImplemented = false,
                    storageIndexReadWriteImplemented = false,
                    staleRecordEnforcementImplemented = false,
                    fullLocalDirectoryRollbackResistanceClaimed = false,
                    externalOrTrustedMonotonicAnchorDesigned = false,
                    antiRollbackAnchorRequiredForGlobalRollbackResistance = true,
                    providerSelectabilityBlockedUntilImplementedAndTested = true,
                ),
                containerManifestStorageContract = ProductionProviderContainerManifestStorageContract(
                    vaultContainerPolicyId = "skald-vault-v1-container-contract-v1",
                    manifestPolicyId = "skald-vault-v1-manifest-contract-v1",
                    storagePolicyId = "skald-vault-v1-local-manifest-storage-policy-v1",
                    platformStorageBoundaryPolicyId =
                        "skald-vault-v1-platform-storage-boundary-policy-v1",
                    staleRecordPolicyId = "skald-vault-v1-stale-record-manifest-policy-v1",
                    atomicityCrashRecoveryPolicyId = "skald-vault-v1-atomicity-crash-recovery-policy-v1",
                    atomicWritePolicyId = "skald-vault-v1-atomic-write-strategy-policy-v1",
                    crashRecoveryPolicyId = "skald-vault-v1-crash-recovery-policy-v1",
                    interruptionTestPolicyId = "skald-vault-v1-storage-interruption-test-policy-v1",
                    storageFailureModelPolicyId = "skald-vault-v1-storage-failure-model-policy-v1",
                    storageNamespacePathPolicyId =
                        SkaldVaultV1StorageNamespacePathPolicy.POLICY_ID,
                    storageLayoutPlanPolicyId =
                        SkaldVaultV1StorageLayoutPlanPolicy.POLICY_ID,
                    pathContainmentPlannerPolicyId =
                        SkaldVaultV1PathContainmentPlanner.POLICY_ID,
                    platformStorageRootPolicyId =
                        "skald-vault-v1-platform-storage-root-policy-v1",
                    platformRootSettingsPolicyId =
                        SkaldVaultV1PlatformRootSettingsPolicy.POLICY_ID,
                    androidRootPolicyId =
                        SkaldVaultV1PlatformRootSettingsPolicy.ANDROID_ROOT_POLICY_ID,
                    linuxRootSettingsPolicyId =
                        SkaldVaultV1PlatformRootSettingsPolicy.LINUX_ROOT_SETTINGS_POLICY_ID,
                    linuxCustomRootValidationPolicyId =
                        SkaldVaultV1LinuxCustomRootValidationPolicy.POLICY_ID,
                    linuxRootResolutionPolicyId =
                        SkaldVaultV1LinuxRootResolutionPolicy.POLICY_ID,
                    platformRootResolverPolicyId =
                        SkaldVaultV1PlatformRootResolverPolicy.POLICY_ID,
                    platformPathConstructionBoundaryPolicyId =
                        SkaldVaultV1PlatformPathConstructionPolicy.POLICY_ID,
                    storageSafetyPreflightBoundaryPolicyId =
                        SkaldVaultV1StorageSafetyPreflightPolicy.POLICY_ID,
                    disabledStorageServiceFacadePolicyId =
                        SkaldVaultV1DisabledStorageServiceFacade.POLICY_ID,
                    persistenceReadinessGatePolicyId =
                        SkaldVaultV1PersistenceReadinessGate.POLICY_ID,
                    lockSessionLifecycleBoundaryPolicyId =
                        SkaldVaultV1LockSessionLifecyclePolicy.POLICY_ID,
                    redactionLeakageBoundaryPolicyId =
                        SkaldVaultV1RedactionLeakagePolicy.POLICY_ID,
                    passphrasePolicyBoundaryEvidence =
                        ProductionProviderPassphrasePolicyBoundaryEvidence(
                            policyId = SkaldVaultV1PassphrasePolicyGate.POLICY_ID,
                            status = ProductionProviderConstructionContractStatus.ImplementedTested,
                            rules = ProductionProviderPassphrasePolicyBoundaryRule.entries.toSet(),
                            modeled = true,
                            stillDisabled = true,
                            inputStillRejected = true,
                            doesNotAcceptRawPassphrases = true,
                            doesNotHashOrFingerprint = true,
                            doesNotRunKdf = true,
                            doesNotEnableUnlock = true,
                            doesNotEnablePersistence = true,
                            doesNotEnableProviderSelection = true,
                            failureVocabularyModeled = true,
                        ),
                    clearWipeStrategyBoundaryEvidence =
                        ProductionProviderClearWipeStrategyBoundaryEvidence(
                            policyId = SkaldVaultV1ClearWipeStrategyPolicy.POLICY_ID,
                            status = ProductionProviderConstructionContractStatus.ImplementedTested,
                            rules = ProductionProviderClearWipeStrategyBoundaryRule.entries.toSet(),
                            modeled = true,
                            stillDisabled = true,
                            doesNotAcceptRawSensitiveValues = true,
                            doesNotClearRealMemory = true,
                            doesNotProveJvmZeroization = true,
                            doesNotEnableUnlock = true,
                            doesNotEnablePersistence = true,
                            doesNotEnableProviderSelection = true,
                            failureVocabularyModeled = true,
                        ),
                    migrationCorruptionBoundaryEvidence =
                        ProductionProviderMigrationCorruptionBoundaryEvidence(
                            policyId = SkaldVaultV1MigrationCorruptionPolicy.POLICY_ID,
                            status = ProductionProviderConstructionContractStatus.ImplementedTested,
                            rules = ProductionProviderMigrationCorruptionBoundaryRule.entries.toSet(),
                            modeled = true,
                            stillDisabled = true,
                            classifiesFailureKinds = true,
                            doesNotParseRealStorage = true,
                            doesNotRepairStorage = true,
                            doesNotRunMigration = true,
                            doesNotEnableUnlock = true,
                            doesNotEnablePersistence = true,
                            doesNotEnableProviderSelection = true,
                            failureVocabularyModeled = true,
                        ),
                    providerOperationAuthorizationBoundaryEvidence =
                        ProductionProviderOperationAuthorizationBoundaryEvidence(
                            policyId = SkaldVaultV1ProviderOperationAuthorizationPolicy.POLICY_ID,
                            status = ProductionProviderConstructionContractStatus.ImplementedTested,
                            rules = ProductionProviderOperationAuthorizationBoundaryRule.entries.toSet(),
                            modeled = true,
                            stillDisabled = true,
                            blocksAllOperations = true,
                            doesNotRunCrypto = true,
                            doesNotRunKat = true,
                            doesNotGenerateRandomness = true,
                            doesNotEnableUnlock = true,
                            doesNotEnablePersistence = true,
                            doesNotEnableProviderSelection = true,
                            failureVocabularyModeled = true,
                        ),
                    runtimeRandomnessAuthorizationBoundaryEvidence =
                        ProductionProviderRuntimeRandomnessAuthorizationBoundaryEvidence(
                            policyId = SkaldVaultV1RuntimeRandomnessAuthorizationPolicy.POLICY_ID,
                            status = ProductionProviderConstructionContractStatus.ImplementedTested,
                            rules = ProductionProviderRuntimeRandomnessAuthorizationBoundaryRule.entries.toSet(),
                            modeled = true,
                            stillDisabled = true,
                            blocksAllOperations = true,
                            doesNotCallSecureRandom = true,
                            doesNotGenerateEntropy = true,
                            doesNotGenerateSaltOrNonce = true,
                            doesNotGenerateKeys = true,
                            doesNotEnableProviderOperations = true,
                            doesNotEnableUnlock = true,
                            doesNotEnablePersistence = true,
                            doesNotEnableProviderSelection = true,
                            failureVocabularyModeled = true,
                        ),
                    kdfCalibrationAuthorizationBoundaryEvidence =
                        ProductionProviderKdfCalibrationAuthorizationBoundaryEvidence(
                            policyId = SkaldVaultV1KdfCalibrationAuthorizationPolicy.POLICY_ID,
                            status = ProductionProviderConstructionContractStatus.ImplementedTested,
                            rules = ProductionProviderKdfCalibrationAuthorizationBoundaryRule.entries.toSet(),
                            modeled = true,
                            stillDisabled = true,
                            blocksAllOperations = true,
                            doesNotRunArgon2id = true,
                            doesNotRunCalibration = true,
                            doesNotApproveFinalParameters = true,
                            doesNotEnableUnlock = true,
                            doesNotEnablePersistence = true,
                            doesNotEnableProviderSelection = true,
                            failureVocabularyModeled = true,
                        ),
                    secureStorageAuthorizationBoundaryEvidence =
                        ProductionProviderSecureStorageAuthorizationBoundaryEvidence(
                            policyId = SkaldVaultV1SecureStorageAuthorizationPolicy.POLICY_ID,
                            status = ProductionProviderConstructionContractStatus.ImplementedTested,
                            rules = ProductionProviderSecureStorageAuthorizationBoundaryRule.entries.toSet(),
                            modeled = true,
                            stillDisabled = true,
                            blocksAllOperations = true,
                            doesNotStoreSecrets = true,
                            doesNotUseOsKeyrings = true,
                            doesNotUsePasswordManagers = true,
                            doesNotUseSettingsStorage = true,
                            doesNotEnableUnlock = true,
                            doesNotEnablePersistence = true,
                            doesNotEnableProviderSelection = true,
                            failureVocabularyModeled = true,
                        ),
                    osKeyringPassphrasePolicyId =
                        SkaldVaultV1PlatformRootSettingsPolicy.OS_KEYRING_PASSPHRASE_POLICY_ID,
                    passwordManagerPassphrasePolicyId =
                        SkaldVaultV1PlatformRootSettingsPolicy.PASSWORD_MANAGER_PASSPHRASE_POLICY_ID,
                    passphraseFirstPolicyId =
                        SkaldVaultV1PlatformRootSettingsPolicy.PASSPHRASE_FIRST_POLICY_ID,
                    safePathConstructionPolicyId =
                        "skald-vault-v1-safe-path-construction-policy-v1",
                    symlinkTraversalPolicyId =
                        "skald-vault-v1-symlink-traversal-policy-v1",
                    storagePermissionOwnershipPolicyId =
                        "skald-vault-v1-storage-permission-ownership-policy-v1",
                    durabilityCapabilityPolicyId =
                        "skald-vault-v1-durability-capability-policy-v1",
                    durabilityFailClosedPolicyId =
                        "skald-vault-v1-durability-fail-closed-policy-v1",
                    warningOnlyDurabilityRejectionPolicyId =
                        "skald-vault-v1-warning-only-durability-rejection-policy-v1",
                    storageAtomicitySimulatorPolicyId =
                        "skald-vault-v1-in-memory-storage-atomicity-simulator-policy-v1",
                    secureStorageBoundaryPolicyId = "skald-vault-v1-secure-storage-boundary-policy-v1",
                    antiRollbackAnchorPolicyId = "skald-vault-v1-anti-rollback-anchor-policy-v1",
                    vaultContainerContractStatus =
                        ProductionProviderConstructionContractStatus.ImplementedTested,
                    manifestContractStatus = ProductionProviderConstructionContractStatus.ImplementedTested,
                    storagePolicyContractStatus =
                        ProductionProviderConstructionContractStatus.DocumentedModelOnly,
                    platformStorageBoundaryContractStatus =
                        ProductionProviderConstructionContractStatus.DocumentedModelOnly,
                    staleRecordPolicyStatus = ProductionProviderConstructionContractStatus.ImplementedTested,
                    atomicityCrashRecoveryContractStatus =
                        ProductionProviderConstructionContractStatus.DocumentedModelOnly,
                    atomicWriteContractStatus =
                        ProductionProviderConstructionContractStatus.DocumentedModelOnly,
                    crashRecoveryContractStatus =
                        ProductionProviderConstructionContractStatus.DocumentedModelOnly,
                    interruptionTestContractStatus =
                        ProductionProviderConstructionContractStatus.DocumentedModelOnly,
                    storageFailureModelStatus =
                        ProductionProviderConstructionContractStatus.DocumentedModelOnly,
                    storageNamespacePathHygieneStatus =
                        ProductionProviderConstructionContractStatus.ImplementedTested,
                    storageLayoutPlanStatus =
                        ProductionProviderConstructionContractStatus.ImplementedTested,
                    pathContainmentPlannerStatus =
                        ProductionProviderConstructionContractStatus.ImplementedTested,
                    platformStorageRootContractStatus =
                        ProductionProviderConstructionContractStatus.DocumentedModelOnly,
                    platformRootSettingsPolicyStatus =
                        ProductionProviderConstructionContractStatus.DocumentedModelOnly,
                    linuxCustomRootValidationPolicyStatus =
                        ProductionProviderConstructionContractStatus.ImplementedTested,
                    linuxRootResolutionPolicyStatus =
                        ProductionProviderConstructionContractStatus.ImplementedTested,
                    platformRootResolverBoundaryStatus =
                        ProductionProviderConstructionContractStatus.ImplementedTested,
                    platformPathConstructionBoundaryStatus =
                        ProductionProviderConstructionContractStatus.ImplementedTested,
                    storageSafetyPreflightBoundaryStatus =
                        ProductionProviderConstructionContractStatus.ImplementedTested,
                    disabledStorageServiceFacadeStatus =
                        ProductionProviderConstructionContractStatus.ImplementedTested,
                    persistenceReadinessGateStatus =
                        ProductionProviderConstructionContractStatus.ImplementedTested,
                    lockSessionLifecycleBoundaryStatus =
                        ProductionProviderConstructionContractStatus.ImplementedTested,
                    redactionLeakageBoundaryStatus =
                        ProductionProviderConstructionContractStatus.ImplementedTested,
                    safePathConstructionContractStatus =
                        ProductionProviderConstructionContractStatus.DocumentedModelOnly,
                    symlinkTraversalContractStatus =
                        ProductionProviderConstructionContractStatus.DocumentedModelOnly,
                    storagePermissionOwnershipContractStatus =
                        ProductionProviderConstructionContractStatus.DocumentedModelOnly,
                    durabilityCapabilityContractStatus =
                        ProductionProviderConstructionContractStatus.DocumentedModelOnly,
                    durabilityFailClosedPolicyStatus =
                        ProductionProviderConstructionContractStatus.DocumentedModelOnly,
                    warningOnlyDurabilityRejectionStatus =
                        ProductionProviderConstructionContractStatus.DocumentedModelOnly,
                    storageAtomicitySimulatorStatus =
                        ProductionProviderConstructionContractStatus.ImplementedTested,
                    secureStorageBoundaryStatus =
                        ProductionProviderConstructionContractStatus.DocumentedModelOnly,
                    containerFields = ProductionProviderVaultContainerField.entries.toSet(),
                    containerRequirements = ProductionProviderVaultContainerRequirement.entries.toSet(),
                    manifestFields = ProductionProviderManifestField.entries.toSet(),
                    manifestBindings = ProductionProviderStaleRecordManifestBinding.entries.toSet(),
                    manifestRequirements = ProductionProviderStaleRecordManifestRequirement.entries.toSet(),
                    storageBoundaryAllowedBytes =
                        ProductionProviderStorageBoundaryAllowedBytes.entries.toSet(),
                    storageBoundaryForbiddenMaterial =
                        ProductionProviderStorageBoundaryForbiddenMaterial.entries.toSet(),
                    storageBoundaryRequirements =
                        ProductionProviderStorageBoundaryRequirement.entries.toSet(),
                    atomicityRequirements = ProductionProviderStorageAtomicityRequirement.entries.toSet(),
                    atomicWritePhases = ProductionProviderAtomicWritePhase.entries.toSet(),
                    atomicWriteRequirements = ProductionProviderAtomicWriteRequirement.entries.toSet(),
                    crashRecoveryChecks = ProductionProviderCrashRecoveryCheck.entries.toSet(),
                    crashRecoveryFailClosedStates =
                        ProductionProviderCrashRecoveryFailClosedState.entries.toSet(),
                    storageFailureCategories = ProductionProviderStorageFailureCategory.entries.toSet(),
                    storageNamespacePathRules =
                        ProductionProviderStorageNamespacePathRule.entries.toSet(),
                    storageLayoutPlanRules =
                        ProductionProviderStorageLayoutPlanRule.entries.toSet(),
                    pathContainmentPlannerRules =
                        ProductionProviderPathContainmentPlannerRule.entries.toSet(),
                    platformStorageRootRules =
                        ProductionProviderPlatformStorageRootRule.entries.toSet(),
                    platformRootSettingsRules =
                        ProductionProviderPlatformRootSettingsRule.entries.toSet(),
                    linuxCustomRootValidationRules =
                        ProductionProviderLinuxCustomRootValidationRule.entries.toSet(),
                    linuxRootResolutionRules =
                        ProductionProviderLinuxRootResolutionRule.entries.toSet(),
                    platformRootResolverRules =
                        ProductionProviderPlatformRootResolverRule.entries.toSet(),
                    platformPathConstructionRules =
                        ProductionProviderPlatformPathConstructionRule.entries.toSet(),
                    storageSafetyPreflightRules =
                        ProductionProviderStorageSafetyPreflightRule.entries.toSet(),
                    disabledStorageServiceFacadeRules =
                        ProductionProviderDisabledStorageServiceFacadeRule.entries.toSet(),
                    persistenceReadinessGateRules =
                        ProductionProviderPersistenceReadinessGateRule.entries.toSet(),
                    lockSessionLifecycleRules =
                        ProductionProviderLockSessionLifecycleRule.entries.toSet(),
                    redactionLeakageRules =
                        ProductionProviderRedactionLeakageRule.entries.toSet(),
                    safePathConstructionRules =
                        ProductionProviderSafePathConstructionRule.entries.toSet(),
                    symlinkTraversalRules =
                        ProductionProviderSymlinkTraversalRule.entries.toSet(),
                    storagePermissionOwnershipRules =
                        ProductionProviderStoragePermissionOwnershipRule.entries.toSet(),
                    durabilityCapabilityRules =
                        ProductionProviderDurabilityCapabilityRule.entries.toSet(),
                    durabilityFailClosedConditions =
                        ProductionProviderDurabilityFailClosedCondition.entries.toSet(),
                    warningOnlyDurabilityRules =
                        ProductionProviderWarningOnlyDurabilityRule.entries.toSet(),
                    secureStorageBoundaryRequirements =
                        ProductionProviderSecureStorageBoundaryRequirement.entries.toSet(),
                    strictAadSubstitutionProtectionModeled = true,
                    strictAadFreshnessProofClaimed = false,
                    localManifestStaleRecordDetectionModeled = true,
                    fullLocalDirectoryRollbackResistanceClaimed = false,
                    externalOrTrustedMonotonicAntiRollbackAnchorImplemented = false,
                    parserImplemented = true,
                    writerImplemented = true,
                    manifestParserImplemented = true,
                    manifestWriterImplemented = true,
                    staleRecordDecisionPolicyImplemented = true,
                    vaultPersistenceImplemented = false,
                    manifestReadWriteImplemented = false,
                    storageIndexReadWriteImplemented = false,
                    platformStorageImplementationAdded = false,
                    filesystemVaultStorageImplemented = false,
                    databaseVaultStorageImplemented = false,
                    dataStoreVaultStorageImplemented = false,
                    sharedPreferencesVaultStorageImplemented = false,
                    tempFileImplementationAdded = false,
                    journalImplementationAdded = false,
                    atomicReplaceImplementationAdded = false,
                    durabilitySyncImplementationAdded = false,
                    crashRecoveryImplementationAdded = false,
                    interruptionTestRuntimeHooksAdded = false,
                    storageFailureRuntimeMappingImplemented = false,
                    storagePathConstructionImplemented = false,
                    platformRootResolutionImplemented = false,
                    platformRootSelectionImplemented = false,
                    androidAppPrivateInternalRootPolicyModeled = true,
                    androidExternalStorageRejected = true,
                    androidUserSelectedRootRejected = true,
                    androidRootResolutionImplemented = false,
                    androidBackupBehaviorReviewed = false,
                    androidUninstallBehaviorReviewed = false,
                    linuxDefaultUserDataRootPolicyModeled = true,
                    linuxLocalShareFallbackModeled = true,
                    linuxCustomRootSettingsContractModeled = true,
                    linuxCustomRootUsable = false,
                    linuxCustomRootValidationImplemented = true,
                    linuxRootResolutionEvidenceModeled = true,
                    linuxRootResolutionStillDisabled = true,
                    linuxRootResolutionDoesNotResolveFilesystem = true,
                    linuxRootResolutionDoesNotConstructPaths = true,
                    linuxRootResolutionDoesNotEnablePersistence = true,
                    platformRootResolverBoundaryModeled = true,
                    androidAppPrivateRootEvidenceModeled = true,
                    linuxDefaultRootResolverEvidenceModeled = true,
                    platformRootResolverStillDisabled = true,
                    platformRootResolverDoesNotEnablePersistence = true,
                    platformRootResolverDoesNotProveDurability = true,
                    platformRootResolverDoesNotEnableProviderSelection = true,
                    platformPathConstructionBoundaryModeled = true,
                    platformPathConstructionStillDisabled = true,
                    platformPathConstructionDoesNotConstructRealPaths = true,
                    platformPathConstructionDoesNotEnablePersistence = true,
                    platformPathConstructionDoesNotEnableProviderSelection = true,
                    plannedArtifactLocationEvidenceModeled = true,
                    storageSafetyPreflightBoundaryModeled = true,
                    storageSafetyPreflightStillDisabled = true,
                    storageSafetyPreflightDoesNotRunFilesystemChecks = true,
                    storageSafetyPreflightDoesNotEnablePersistence = true,
                    storageSafetyPreflightDoesNotEnableProviderSelection = true,
                    storageSafetyGateVocabularyModeled = true,
                    disabledStorageServiceFacadeModeled = true,
                    storageServiceFacadeStillDisabled = true,
                    storageServiceOperationsFailClosed = true,
                    storageServiceDoesNotUseFilesystem = true,
                    storageServiceDoesNotEnablePersistence = true,
                    storageServiceDoesNotEnableProviderSelection = true,
                    storageOperationFailureVocabularyModeled = true,
                    storageServiceOperationSuccessPathImplemented = false,
                    persistenceReadinessGateModeled = true,
                    persistenceReadinessGateStillBlocked = true,
                    persistenceReadinessGateComposesStorageEvidence = true,
                    persistenceReadinessGateDoesNotUseFilesystem = true,
                    persistenceReadinessGateDoesNotEnablePersistence = true,
                    persistenceReadinessGateDoesNotEnableProviderSelection = true,
                    persistenceReadinessFailureVocabularyModeled = true,
                    lockSessionLifecycleBoundaryModeled = true,
                    lockSessionLifecycleStillDisabled = true,
                    unlockDecisionStillBlocked = true,
                    activeSessionUnavailable = true,
                    lockSessionBoundaryDoesNotAcceptPassphrases = true,
                    lockSessionBoundaryDoesNotHoldKeys = true,
                    lockSessionBoundaryDoesNotEnablePersistence = true,
                    lockSessionBoundaryDoesNotEnableProviderSelection = true,
                    lockSessionFailureVocabularyModeled = true,
                    redactionLeakageBoundaryModeled = true,
                    redactionLeakageBoundaryStillDisabled = true,
                    redactionLeakageClassifiesSensitiveValueKinds = true,
                    redactionLeakageDoesNotAcceptRawSecrets = true,
                    redactionLeakageDoesNotHashSecrets = true,
                    redactionLeakageDoesNotLog = true,
                    redactionLeakageDoesNotEnablePersistence = true,
                    redactionLeakageDoesNotEnableProviderSelection = true,
                    redactionLeakageFailureVocabularyModeled = true,
                    settingsUiImplemented = false,
                    settingsPersistenceImplemented = false,
                    osKeyringPrimaryStorageRejected = true,
                    osKeyringPassphraseStorageRejected = true,
                    passwordManagerIntegrationRejected = true,
                    passphraseFirstDefaultModeled = true,
                    actualPathConstructionImplemented = false,
                    pathJoinImplementationAdded = false,
                    pathContainmentCheckImplementationAdded = false,
                    directoryCreationImplementationAdded = false,
                    symlinkCheckImplementationAdded = false,
                    permissionCheckImplementationAdded = false,
                    durabilityProbeImplementationAdded = false,
                    warningOnlyEncryptedVaultPersistenceAllowed = false,
                    userConsentDurabilityOverrideAllowed = false,
                    equivalentSafeDurabilityStrategyApproved = false,
                    androidDurabilityReviewed = false,
                    desktopDurabilityReviewed = false,
                    storageNamespacePathPolicyImplemented = true,
                    storagePathSegmentEncodingImplemented = true,
                    storageLayoutPlanImplemented = true,
                    storageLayoutLocationsRootless = true,
                    storageLayoutUsesSafeSegmentsOnly = true,
                    reviewedRootTokenPolicyImplemented = true,
                    pathContainmentPlannerImplemented = true,
                    plannedLocationsRootTokenBound = true,
                    plannedLocationsArePlatformPaths = false,
                    plannedLocationsUseSafeSegmentsOnly = true,
                    inMemoryAtomicityCrashSimulatorImplemented = true,
                    inMemoryAtomicityCrashSimulatorInterruptionTestsExecuted = true,
                    inMemoryAtomicityCrashSimulatorRecoveryDecisionsTested = true,
                    secureSecretStorageSuccessPathImplemented = false,
                    secureMetadataStorageSuccessPathImplemented = false,
                    atomicWriteRecoveryImplementationAdded = false,
                ),
                canonicalHeaderEncodingPolicy = ProductionProviderCanonicalHeaderEncodingPolicy(
                    policyId = "skald-vault-v1-canonical-header-encoding-v1",
                    policyVersion = 1,
                    contractStatus = ProductionProviderConstructionContractStatus.ImplementedTested,
                    byteOrder = "big-endian",
                    stringEncoding = "UTF-8",
                    domainMagic = "SKALD-VAULT-V1",
                    rules = ProductionProviderCanonicalHeaderEncodingRule.entries.toSet(),
                    productionSerializerImplemented = true,
                ),
                keySeparationPolicy = ProductionProviderKeySeparationPolicy(
                    policyId = "skald-vault-v1-key-separation-labels-v1",
                    policyVersion = 1,
                    contractStatus = ProductionProviderConstructionContractStatus.ImplementedTested,
                    labels = ProductionProviderKeySeparationLabel.entries.toSet(),
                    rootMaterialUsedDirectlyForMultiplePurposes = false,
                    recordAeadAndHeaderCommitmentKeyMaterialSeparated = true,
                    reservedFutureLabelsNotImplemented = true,
                    keyExpansionPrimitiveApproved = true,
                    productionKeyDerivationImplemented = true,
                    unknownUnsupportedPolicyBlocksSelectability = true,
                ),
                passphraseEncodingPolicy = ProductionProviderPassphraseEncodingPolicy(
                    policyId = "unicode-nfc-utf8-no-controls-no-whitespace-v1",
                    contractStatus = ProductionProviderConstructionContractStatus.ImplementedTested,
                    normalizationForm = "NFC",
                    encodedForm = "UTF-8",
                    forbiddenClasses = ProductionProviderPassphraseForbiddenClass.entries.toSet(),
                    noTransformRules = ProductionProviderPassphraseNoTransformRule.entries.toSet(),
                    allowedClasses = ProductionProviderPassphraseAllowedClass.entries.toSet(),
                    composedAndDecomposedFormsMustCanonicalizeToSameNfcBytes = true,
                    visibleSeparatorsSuggestedAsAlternativesToSpaces = setOf("-", ".", "_"),
                    productionValidationImplemented = true,
                    productionVaultCreationWired = false,
                ),
                argon2idRootDerivationPolicy = ProductionProviderArgon2idRootDerivationPolicy(
                    contractStatus = ProductionProviderConstructionContractStatus.ImplementedTested,
                    implementation = "Bouncy Castle Argon2id explicit-parameter root derivation",
                    type = SkaldVaultV1Argon2idType.Argon2id,
                    version = Argon2idVersion.Version19,
                    minimumMemoryMiB = 64,
                    minimumIterations = 3,
                    parallelism = 1,
                    minimumSaltBytes = 16,
                    preferredNewVaultSaltBytes = 32,
                    outputRootMaterialBytes = 64,
                    explicitCallerSuppliedParametersRequired = true,
                    automaticCalibrationImplemented = false,
                    parameterDowngradeImplemented = false,
                    productionRootDerivationImplemented = true,
                    productionVaultCreationWired = false,
                ),
                tinkRawKeyHandlingPolicy = ProductionProviderTinkRawKeyHandlingPolicy(
                    preferredCallerSuppliedDerivedRawKeyMaterial = true,
                    persistedPlaintextTinkKeysetsAllowed = false,
                    persistedEncryptedTinkKeysetsAllowedInV1 = false,
                    randomTinkVaultKeysAllowed = false,
                    tinkKeyRotationInV1Allowed = false,
                    multipleActiveAeadKeysInV1Allowed = false,
                    publicSupportedApiRequired = true,
                    internalUnsupportedReflectiveApisAllowed = false,
                    fallbackEncryptedKeysetModelImplemented = false,
                    productionAeadExecutionImplemented = false,
                    stillDisabledRecordAeadBuildingBlockImplemented = true,
                    desktopFeasibilityStatus =
                        ProductionProviderTinkRawKeyFeasibilityStatus.FEASIBLE_PUBLIC_RAW_KEY_API,
                    androidFeasibilityStatus =
                        ProductionProviderAndroidTinkRawKeyFeasibilityStatus
                            .ANDROID_FEASIBLE_PUBLIC_RAW_KEY_API,
                    desktopTestedPublicApiPath = "caller-supplied fixed bytes -> public Tink secret-byte wrapper -> " +
                        "public Tink XChaCha20-Poly1305 key object -> transient in-memory Tink keyset handle " +
                        "import -> public AEAD primitive lookup",
                    androidTestedPublicApiPath = "caller-supplied fixed bytes -> public Tink secret-byte wrapper -> " +
                        "public Tink XChaCha20-Poly1305 key object -> transient in-memory Tink keyset handle " +
                        "import -> public AEAD primitive lookup",
                    androidPathMatchesDesktopPath = true,
                    transientInMemoryTinkKeysetHandleRequired = true,
                    persistedTinkKeysetRequired = false,
                    randomTinkGeneratedVaultKeyRequired = false,
                ),
                aeadPolicy = ProductionProviderAeadAcceptancePolicy(
                    primitive = EncryptedVaultAeadAlgorithm.XChaCha20Poly1305,
                    aadPolicyId = "skald-vault-v1-record-aad-v1",
                    aadPolicyVersion = 1,
                    recordFormatPolicyId = "skald-vault-v1-record-format-v1",
                    recordFormatPolicyVersion = 1,
                    contractStatus = ProductionProviderConstructionContractStatus.ImplementedTested,
                    nonKeyCommitting = true,
                    successfulDecryptAloneProvesCorrectVaultKey = false,
                    vaultLevelKeyCommitmentRequiredBeforeRecordDecrypt = true,
                    headerAuthenticationRequiredBeforeRecordDecrypt = true,
                    strictAadBindingFields = ProductionProviderAadBindingField.entries.toSet(),
                    aadMismatchFailClosedConditions =
                        ProductionProviderAadFailClosedCondition.entries.toSet(),
                    requiredTamperCoverage = ProductionProviderTamperCoverage.entries.toSet(),
                    rawKeyFeasibilityBypassesHeaderCommitment = false,
                    wrongPassphraseResolvedByHeaderCommitmentBeforeRecordDecrypt = true,
                    strictAadSerializationImplemented = true,
                    recordAeadBuildingBlockImplemented = true,
                    recordVersionCounterBoundIntoAad = true,
                    staleRecordEnforcementDeferredToManifestOrStorage = true,
                    productionProviderWired = false,
                    productionAeadExecutionImplemented = false,
                ),
                runtimeRandomnessPolicy = ProductionProviderRuntimeRandomnessAcceptancePolicy(
                    sourceKind = RuntimeRandomnessSourceKind.OsCryptographicRandomness,
                    exactProviderPathLabel = "OS SecureRandom",
                    unknownProviderStateBlocksVaultCreation = true,
                    forbiddenRandomApisRejected = true,
                    tinySampleIsEntropyQualityProof = false,
                ),
                androidWrappingPolicy = ProductionProviderAndroidWrappingAcceptancePolicy(
                    optionalFutureConvenienceLayerOnly = true,
                    hardwareOrBiometricWrappingRequired = false,
                    passphrasePrimaryAuthority = true,
                    passwordOnlyModeFirstClass = true,
                    biometricUnlockReplacesPassphrase = false,
                    roughlyWeeklyPassphrasePromptAfterBiometricUnlockRequired = true,
                ),
                stillDisabledProviderFacadePolicy = ProductionProviderStillDisabledFacadePolicy(
                    contractStatus = ProductionProviderConstructionContractStatus.ImplementedTested,
                    facadeImplemented = true,
                    metadataOnly = true,
                    operationsDisabled = true,
                    selectableThroughProviderRegistry = false,
                    debugOrTestFlagCanSelect = false,
                    vaultCreationEnabled = false,
                    vaultUnlockEnabled = false,
                    vaultPersistenceEnabled = false,
                    manifestStorageImplemented = false,
                    secureStorageEnabled = false,
                    katHarnessSuccessImpliesSelectability = false,
                    calibrationEvidenceImpliesSelectability = false,
                    releaseApprovalPresent = false,
                ),
            )
    }
}

fun commonProductionProviderAcceptanceContract(): ProductionProviderAcceptanceContract =
    ProductionProviderAcceptanceContract.v1()

fun commonCurrentProductionProviderAcceptanceAssessment(): ProductionProviderAcceptanceAssessment =
    commonProductionProviderAcceptanceContract().assess()
