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
    StaleRecordManifestPolicyApproved("stale-record and rollback manifest policy approved"),
    RuntimeOsSecureRandomEvidenceApproved("OS SecureRandom runtime provider/algorithm evidence approved"),
    UnknownRandomnessProviderStateRejected("unknown randomness/provider state rejected"),
    ForbiddenRandomApisGuarded("forbidden language and ad hoc random APIs guarded"),
    SecureSecretStorageReviewed("secure secret storage reviewed"),
    SecureMetadataStorageReviewed("secure metadata storage reviewed"),
    CrashCorruptionPartialWriteReviewed("crash, corruption, and partial-write behavior reviewed"),
    RedactionLeakageChecksPassed("redaction, logging, and crash-report leakage checks pass"),
    AndroidOptionalWrappingSeparateFromPassphrase("Android optional wrapping remains separate from passphrase recovery"),
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
    NoManifestReadWriteInThisBranch("no manifest reader or writer exists in this branch"),
}

data class ProductionProviderStaleRecordManifestPolicy(
    val policyId: String,
    val manifestStoragePolicyId: String,
    val contractStatus: ProductionProviderConstructionContractStatus,
    val recordVersionCounterBoundIntoAad: Boolean,
    val bindings: Set<ProductionProviderStaleRecordManifestBinding>,
    val requirements: Set<ProductionProviderStaleRecordManifestRequirement>,
    val manifestReadWriteImplemented: Boolean,
    val storageIndexReadWriteImplemented: Boolean,
    val staleRecordEnforcementImplemented: Boolean,
    val fullLocalDirectoryRollbackResistanceClaimed: Boolean,
    val externalOrTrustedMonotonicAnchorDesigned: Boolean,
    val antiRollbackAnchorRequiredForGlobalRollbackResistance: Boolean,
    val providerSelectabilityBlockedUntilImplementedAndTested: Boolean,
)

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
                    ProductionProviderAcceptanceGate.StaleRecordManifestPolicyApproved to
                        ProductionProviderAcceptanceEvidenceState.DocumentedModelOnly,
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
    val canonicalHeaderEncodingPolicy: ProductionProviderCanonicalHeaderEncodingPolicy,
    val keySeparationPolicy: ProductionProviderKeySeparationPolicy,
    val passphraseEncodingPolicy: ProductionProviderPassphraseEncodingPolicy,
    val argon2idRootDerivationPolicy: ProductionProviderArgon2idRootDerivationPolicy,
    val tinkRawKeyHandlingPolicy: ProductionProviderTinkRawKeyHandlingPolicy,
    val aeadPolicy: ProductionProviderAeadAcceptancePolicy,
    val runtimeRandomnessPolicy: ProductionProviderRuntimeRandomnessAcceptancePolicy,
    val androidWrappingPolicy: ProductionProviderAndroidWrappingAcceptancePolicy,
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
                    contractStatus = ProductionProviderConstructionContractStatus.DocumentedModelOnly,
                    recordVersionCounterBoundIntoAad = true,
                    bindings = ProductionProviderStaleRecordManifestBinding.entries.toSet(),
                    requirements = ProductionProviderStaleRecordManifestRequirement.entries.toSet(),
                    manifestReadWriteImplemented = false,
                    storageIndexReadWriteImplemented = false,
                    staleRecordEnforcementImplemented = false,
                    fullLocalDirectoryRollbackResistanceClaimed = false,
                    externalOrTrustedMonotonicAnchorDesigned = false,
                    antiRollbackAnchorRequiredForGlobalRollbackResistance = true,
                    providerSelectabilityBlockedUntilImplementedAndTested = true,
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
            )
    }
}

fun commonProductionProviderAcceptanceContract(): ProductionProviderAcceptanceContract =
    ProductionProviderAcceptanceContract.v1()

fun commonCurrentProductionProviderAcceptanceAssessment(): ProductionProviderAcceptanceAssessment =
    commonProductionProviderAcceptanceContract().assess()
