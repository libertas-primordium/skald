package com.libertasprimordium.skald.security

interface SkaldVaultV1CreationAuthorizationBoundary {
    fun evaluate(
        request: SkaldVaultV1VaultCreationAuthorizationRequest,
    ): SkaldVaultV1VaultCreationAuthorizationResult<SkaldVaultV1VaultCreationAuthorizationEvidence>
}

enum class SkaldVaultV1VaultCreationAuthorizationSource(val label: String) {
    NoEvidence("no vault creation authorization evidence"),
    PolicySummary("vault creation authorization policy summary"),
    OperationKind("vault creation operation kind"),
    Purpose("vault creation purpose"),
    InitializerClass("vault creation initializer class"),
    RequiredGate("vault creation required gate"),
    ComposedTypedEvidence("composed typed vault creation evidence"),
    RawCreationCandidate("raw vault creation candidate"),
}

enum class SkaldVaultV1VaultCreationAuthorizationStatus(val label: String) {
    NoEvidenceAvailable("no vault creation authorization evidence available"),
    PolicySummaryModeled("vault creation authorization policy summary modeled"),
    OperationKindModeled("vault creation operation kind modeled"),
    PurposeModeled("vault creation purpose modeled"),
    InitializerClassModeled("vault creation initializer class modeled"),
    RequiredGateModeled("vault creation required gate modeled"),
    CreationAuthorizationBlockedStillDisabled(
        "vault creation authorization blocked because the boundary is still disabled",
    ),
    RawCandidateRejected("raw vault creation candidate rejected"),
}

enum class SkaldVaultV1VaultCreationAuthorizationDecision(
    val label: String,
    val creationAllowed: Boolean,
    val creationAttemptAllowed: Boolean,
    val passphraseInputAllowed: Boolean,
    val keyGenerationAllowed: Boolean,
    val kdfAllowed: Boolean,
    val providerOperationAllowed: Boolean,
    val storageWriteAllowed: Boolean,
    val sessionCreationAllowed: Boolean,
    val persistenceAllowed: Boolean,
) {
    BlockedFailClosed(
        label = "blocked fail-closed",
        creationAllowed = false,
        creationAttemptAllowed = false,
        passphraseInputAllowed = false,
        keyGenerationAllowed = false,
        kdfAllowed = false,
        providerOperationAllowed = false,
        storageWriteAllowed = false,
        sessionCreationAllowed = false,
        persistenceAllowed = false,
    ),
    Unauthorized(
        label = "unauthorized",
        creationAllowed = false,
        creationAttemptAllowed = false,
        passphraseInputAllowed = false,
        keyGenerationAllowed = false,
        kdfAllowed = false,
        providerOperationAllowed = false,
        storageWriteAllowed = false,
        sessionCreationAllowed = false,
        persistenceAllowed = false,
    ),
    RejectInitializer(
        label = "initializer rejected",
        creationAllowed = false,
        creationAttemptAllowed = false,
        passphraseInputAllowed = false,
        keyGenerationAllowed = false,
        kdfAllowed = false,
        providerOperationAllowed = false,
        storageWriteAllowed = false,
        sessionCreationAllowed = false,
        persistenceAllowed = false,
    ),
    TestOnlyInitializerRejectedForProduction(
        label = "test-only initializer rejected for production runtime",
        creationAllowed = false,
        creationAttemptAllowed = false,
        passphraseInputAllowed = false,
        keyGenerationAllowed = false,
        kdfAllowed = false,
        providerOperationAllowed = false,
        storageWriteAllowed = false,
        sessionCreationAllowed = false,
        persistenceAllowed = false,
    ),
    UnsupportedFailClosed(
        label = "unknown or unsupported initializer fails closed",
        creationAllowed = false,
        creationAttemptAllowed = false,
        passphraseInputAllowed = false,
        keyGenerationAllowed = false,
        kdfAllowed = false,
        providerOperationAllowed = false,
        storageWriteAllowed = false,
        sessionCreationAllowed = false,
        persistenceAllowed = false,
    ),
    RejectMainnet(
        label = "mainnet creation rejected",
        creationAllowed = false,
        creationAttemptAllowed = false,
        passphraseInputAllowed = false,
        keyGenerationAllowed = false,
        kdfAllowed = false,
        providerOperationAllowed = false,
        storageWriteAllowed = false,
        sessionCreationAllowed = false,
        persistenceAllowed = false,
    ),
}

enum class SkaldVaultV1VaultCreationOperationKind(val label: String) {
    CreationAvailabilityCheck("creation availability check"),
    NewVaultCreationAttempt("new vault creation attempt"),
    InitialPassphraseCreationAttempt("initial passphrase creation attempt"),
    InitialKeyMaterialCreationAttempt("initial key material creation attempt"),
    InitialSaltNonceCreationAttempt("initial salt/nonce creation attempt"),
    InitialKdfExecutionAuthorization("initial KDF execution authorization"),
    InitialProviderOperationAuthorization("initial provider operation authorization"),
    InitialHeaderCreationAuthorization("initial header creation authorization"),
    InitialHeaderCommitmentAuthorization("initial header commitment authorization"),
    InitialContainerCreationAuthorization("initial container creation authorization"),
    InitialManifestCreationAuthorization("initial manifest creation authorization"),
    InitialStorageIndexCreationAuthorization("initial storage-index creation authorization"),
    InitialRecordCreationAuthorization("initial record creation authorization"),
    InitialSecureMetadataCreationAuthorization("initial secure metadata creation authorization"),
    InitialWrappedKeyStorageAuthorization("initial wrapped-key storage authorization"),
    InitialStorageNamespaceCreationAuthorization("initial storage namespace creation authorization"),
    InitialStorageSafetyCheckAuthorization("initial storage safety check authorization"),
    InitialPersistenceCommitAuthorization("initial persistence commit authorization"),
    PostCreateUnlockSessionAuthorization("post-create unlock/session authorization"),
    CreationRollbackFailureCleanupAuthorization("creation rollback/failure cleanup authorization"),
    TestOnlyCreationSimulation("test-only creation simulation"),
    ReleaseValidationCreationAttempt("release-validation creation attempt"),
    ProductionRuntimeCreationAttempt("production runtime creation attempt"),
    MainnetCreationAttempt("mainnet creation attempt"),
}

enum class SkaldVaultV1VaultCreationPurpose(val label: String) {
    CreateNewLocalVault("create new local vault"),
    CreateVaultForRestoreImport("create vault for restore/import"),
    CreateVaultForMigrationTarget("create vault for migration target"),
    CreateVaultForBackupExportStaging("create vault for backup/export staging"),
    CreateVaultForRecoveryCenterReadiness("create vault for Recovery Center readiness"),
    CreateVaultForPrivacyAnalyzerReadiness("create vault for Privacy Analyzer readiness"),
    CreateVaultBeforeWalletActivation("create vault before wallet activation"),
    CreateVaultBeforeWalletSync("create vault before wallet sync"),
    CreateVaultBeforeSigningSupport("create vault before signing support"),
    CreateTestOnlyDeterministicSimulation("create test-only deterministic simulation"),
    CreateReleaseValidationVault("create release-validation vault"),
    CreateProductionRuntimeVault("create production runtime vault"),
    CreateMainnetVault("create mainnet vault"),
}

enum class SkaldVaultV1VaultCreationInitializerClass(
    val label: String,
    val rejected: Boolean,
    val futureOnly: Boolean,
    val testOnly: Boolean,
    val unknownOrUnsupported: Boolean,
    val osKeyringPrimary: Boolean,
    val passwordManager: Boolean,
    val generatedKey: Boolean,
    val mainnetBlocked: Boolean,
) {
    NoInitializerSupplied(
        label = "no initializer supplied",
        rejected = true,
        futureOnly = false,
        testOnly = false,
        unknownOrUnsupported = false,
        osKeyringPrimary = false,
        passwordManager = false,
        generatedKey = false,
        mainnetBlocked = false,
    ),
    PassphraseInitializerBlocked(
        label = "passphrase initializer blocked",
        rejected = true,
        futureOnly = false,
        testOnly = false,
        unknownOrUnsupported = false,
        osKeyringPrimary = false,
        passwordManager = false,
        generatedKey = false,
        mainnetBlocked = false,
    ),
    PassphrasePolicyEvidenceOnly(
        label = "passphrase policy evidence only",
        rejected = false,
        futureOnly = true,
        testOnly = false,
        unknownOrUnsupported = false,
        osKeyringPrimary = false,
        passwordManager = false,
        generatedKey = false,
        mainnetBlocked = false,
    ),
    GeneratedKeyInitializerBlocked(
        label = "generated-key initializer blocked",
        rejected = true,
        futureOnly = false,
        testOnly = false,
        unknownOrUnsupported = false,
        osKeyringPrimary = false,
        passwordManager = false,
        generatedKey = true,
        mainnetBlocked = false,
    ),
    HardwareWrappedKeyInitializerFutureOnly(
        label = "hardware-wrapped-key initializer future-only",
        rejected = false,
        futureOnly = true,
        testOnly = false,
        unknownOrUnsupported = false,
        osKeyringPrimary = false,
        passwordManager = false,
        generatedKey = false,
        mainnetBlocked = false,
    ),
    AndroidKeystoreWrapperEvidenceFutureOnly(
        label = "Android Keystore wrapper evidence future-only",
        rejected = false,
        futureOnly = true,
        testOnly = false,
        unknownOrUnsupported = false,
        osKeyringPrimary = false,
        passwordManager = false,
        generatedKey = false,
        mainnetBlocked = false,
    ),
    LinuxOptionalKeyWrapperEvidenceFutureOnly(
        label = "Linux optional key-wrapper evidence future-only",
        rejected = false,
        futureOnly = true,
        testOnly = false,
        unknownOrUnsupported = false,
        osKeyringPrimary = false,
        passwordManager = false,
        generatedKey = false,
        mainnetBlocked = false,
    ),
    OsKeyringInitializerRejectedAsPrimaryStorage(
        label = "OS-keyring initializer rejected as primary storage",
        rejected = true,
        futureOnly = false,
        testOnly = false,
        unknownOrUnsupported = false,
        osKeyringPrimary = true,
        passwordManager = false,
        generatedKey = false,
        mainnetBlocked = false,
    ),
    PasswordManagerInitializerRejectedForSkaldManagedPassphraseStorage(
        label = "password-manager initializer rejected for Skald-managed passphrase storage",
        rejected = true,
        futureOnly = false,
        testOnly = false,
        unknownOrUnsupported = false,
        osKeyringPrimary = false,
        passwordManager = true,
        generatedKey = false,
        mainnetBlocked = false,
    ),
    ImportedBackupInitializerFutureOnly(
        label = "imported-backup initializer future-only",
        rejected = false,
        futureOnly = true,
        testOnly = false,
        unknownOrUnsupported = false,
        osKeyringPrimary = false,
        passwordManager = false,
        generatedKey = false,
        mainnetBlocked = false,
    ),
    MigratedVaultInitializerFutureOnly(
        label = "migrated-vault initializer future-only",
        rejected = false,
        futureOnly = true,
        testOnly = false,
        unknownOrUnsupported = false,
        osKeyringPrimary = false,
        passwordManager = false,
        generatedKey = false,
        mainnetBlocked = false,
    ),
    TestOnlyPlaceholderInitializerRejectedForProduction(
        label = "test-only placeholder initializer rejected for production",
        rejected = true,
        futureOnly = false,
        testOnly = true,
        unknownOrUnsupported = false,
        osKeyringPrimary = false,
        passwordManager = false,
        generatedKey = false,
        mainnetBlocked = false,
    ),
    UnknownInitializer(
        label = "unknown initializer",
        rejected = true,
        futureOnly = false,
        testOnly = false,
        unknownOrUnsupported = true,
        osKeyringPrimary = false,
        passwordManager = false,
        generatedKey = false,
        mainnetBlocked = false,
    ),
    UnsupportedInitializer(
        label = "unsupported initializer",
        rejected = true,
        futureOnly = false,
        testOnly = false,
        unknownOrUnsupported = true,
        osKeyringPrimary = false,
        passwordManager = false,
        generatedKey = false,
        mainnetBlocked = false,
    ),
}

enum class SkaldVaultV1VaultCreationRequiredGate(val label: String) {
    PassphrasePolicyApprovedWherePassphraseInitializerIsUsed(
        "passphrase policy approved where passphrase initializer is used",
    ),
    PassphraseInputMechanismApproved("passphrase input mechanism approved"),
    PassphraseRedactionApproved("passphrase redaction approved"),
    KdfCalibrationAuthorizationApproved("KDF calibration authorization approved"),
    FinalKdfParametersApproved("final KDF parameters approved"),
    RuntimeRandomnessAuthorizationApprovedForSaltNonceKeyMaterial(
        "runtime randomness authorization approved for salt/nonce/key material",
    ),
    ProviderOperationAuthorizationApproved("provider operation authorization approved"),
    ProviderSelectableAndNotDisabled("provider selectable and not disabled"),
    ProviderKatsApproved("provider KATs approved"),
    SecureStorageAuthorizationApproved("secure-storage authorization approved"),
    SecureSecretStorageAvailable("secure secret storage available"),
    SecureMetadataStorageAvailable("secure metadata storage available"),
    EncryptedLocalVaultStorageAvailable("encrypted local vault storage available"),
    StorageSafetyPreflightApproved("storage safety preflight approved"),
    StorageServiceOperationsImplementedAndApproved("storage service operations implemented and approved"),
    StorageNamespaceCreationImplementedAndApproved("storage namespace creation implemented and approved"),
    PlatformRootPathSafetyApproved("platform root/path safety approved"),
    ManifestStorageIndexRecordInitialCreationApproved(
        "manifest/storage-index/record initial creation approved",
    ),
    HeaderCommitmentAadContractApproved("header commitment/AAD contract approved"),
    MigrationCorruptionPolicyApprovedWhereRestoreMigrationInvolved(
        "migration/corruption policy approved where restore/migration is involved",
    ),
    ClearWipeStrategyApproved("clear/wipe strategy approved"),
    LockSessionLifecycleApprovedWherePostCreateSessionInvolved(
        "lock/session lifecycle approved where post-create session is involved",
    ),
    RedactionLeakagePolicyApproved("redaction/leakage policy approved"),
    PersistenceReadinessApproved("persistence readiness approved"),
    AtomicWriteCrashRecoveryApprovedForInitialCommit("atomic write/crash recovery approved for initial commit"),
    AndroidAppPrivateStoragePolicyPreserved("Android app-private storage policy preserved"),
    LinuxCustomRootPolicyReviewedWhereCustomRootsAreUsed(
        "Linux custom-root policy reviewed where custom roots are used",
    ),
    OsKeyringNotUsedAsPrimaryStorage("OS keyring not used as primary storage"),
    PasswordManagerNotUsedForSkaldManagedPassphraseStorage(
        "password manager not used for Skald-managed passphrase storage",
    ),
    SettingsNotUsedForSecretsCreationStateOrUnlockState(
        "Settings not used for secrets, creation state, or unlock state",
    ),
    NoRawSecretDiagnostics("no raw secret diagnostics"),
    NoPlaintextExport("no plaintext export"),
    TestOnlySimulationNotUsedForProductionRuntime("test-only simulation not used for production runtime"),
    MainnetReleaseReviewApproved("mainnet remains disabled unless release review approves it"),
}

enum class SkaldVaultV1VaultCreationAuthorizationBlocker(val label: String) {
    CreationAuthorizationStillDisabled("creation authorization remains still-disabled"),
    NoEvidenceAvailable("no creation authorization evidence available"),
    PassphrasePolicyBlocked("passphrase policy blocked"),
    PassphraseInputMechanismReviewMissing("passphrase input mechanism review missing"),
    PassphraseRedactionReviewMissing("passphrase redaction review missing"),
    KdfCalibrationAuthorizationBlocked("KDF calibration authorization blocked"),
    FinalKdfParametersMissing("final KDF parameters missing"),
    RuntimeRandomnessAuthorizationBlocked("runtime randomness authorization blocked"),
    ProviderOperationAuthorizationBlocked("provider operation authorization blocked"),
    DisabledProviderSelected("registry selects only the disabled provider"),
    ProductionProviderSelectableFalse("productionProviderSelectable remains false"),
    ProviderKatApprovalMissing("provider KAT approval missing"),
    SecureStorageAuthorizationBlocked("secure-storage authorization blocked"),
    SecureSecretStorageUnavailable("secure secret storage unavailable"),
    SecureMetadataStorageUnavailable("secure metadata storage unavailable"),
    EncryptedLocalVaultStorageUnavailable("encrypted local vault storage unavailable"),
    StorageSafetyPreflightBlocked("storage safety preflight blocked"),
    DisabledStorageFacadeBlocked("disabled storage facade blocked"),
    StorageServiceOperationsMissing("storage service operations missing"),
    StorageNamespaceCreationMissing("storage namespace creation missing"),
    PlatformRootPathSafetyBlocked("platform root/path safety blocked"),
    ManifestStorageIndexRecordInitialCreationMissing(
        "manifest/storage-index/record initial creation missing",
    ),
    HeaderCommitmentAadContractMissing("header commitment/AAD contract missing"),
    MigrationCorruptionBoundaryBlocked("migration/corruption boundary blocked"),
    ClearWipeStrategyBlocked("clear/wipe strategy blocked"),
    LockSessionLifecycleBlocked("lock/session lifecycle blocked"),
    RedactionLeakageUnsafe("redaction/leakage unsafe for diagnostics"),
    PersistenceReadinessBlocked("persistence readiness blocked"),
    AtomicWriteCrashRecoveryReviewMissing("atomic write/crash recovery review missing"),
    AndroidAppPrivateStoragePolicyUnapproved("Android app-private storage policy unapproved"),
    LinuxCustomRootPolicyReviewMissing("Linux custom-root policy review missing"),
    OsKeyringPrimaryStorageRejected("OS keyring primary storage rejected"),
    PasswordManagerPassphraseStorageRejected("password-manager passphrase storage rejected"),
    SettingsCreationUnlockStateRejected("Settings creation/unlock state rejected"),
    NoRawSecretDiagnosticsMissing("no-raw-secret diagnostics review missing"),
    PlaintextExportRejected("plaintext export rejected"),
    TestOnlySimulationRejectedForProduction("test-only simulation rejected for production"),
    WarningOnlyEvidenceRejected("warning-only evidence cannot authorize creation"),
    UserConsentOverrideRejected("user consent cannot override missing hard gates"),
    MainnetUnavailable("mainnet remains unavailable"),
    UnknownUnsupportedInitializer("unknown or unsupported initializer"),
    RawCreationCandidateRejected("raw creation candidate rejected"),
    InitialKeyMaterialUnavailable("initial key material unavailable"),
    InitialSaltNonceGenerationUnavailable("initial salt/nonce generation unavailable"),
    InitialKdfExecutionUnavailable("initial KDF execution unavailable"),
    InitialProviderOperationUnavailable("initial provider operation unavailable"),
    HeaderCreationUnavailable("header creation unavailable"),
    HeaderCommitmentUnavailable("header commitment unavailable"),
    ContainerCreationUnavailable("container creation unavailable"),
    ManifestCreationUnavailable("manifest creation unavailable"),
    StorageIndexCreationUnavailable("storage-index creation unavailable"),
    RecordCreationUnavailable("record creation unavailable"),
    SecureMetadataCreationUnavailable("secure metadata creation unavailable"),
    WrappedKeyStorageUnavailable("wrapped-key storage unavailable"),
    InitialPersistenceCommitUnavailable("initial persistence commit unavailable"),
    PostCreateUnlockUnavailable("post-create unlock unavailable"),
    CreationRollbackFailureCleanupUnavailable("creation rollback/failure cleanup unavailable"),
}

enum class SkaldVaultV1VaultCreationAuthorizationWarning(val label: String) {
    EvidenceOnly("vault creation authorization result is evidence only"),
    NoCreationImplementation("no vault creation implementation"),
    NoPassphrasePinOrBiometricAccepted("no passphrase, PIN, or biometric accepted"),
    NoPassphraseNormalizationOrEncoding("no passphrase normalization or encoding"),
    NoSaltNonceKeyGeneration("no salt, nonce, or key generation"),
    NoKdfHkdfHmacAeadExecution("no KDF/HKDF/HMAC/AEAD execution"),
    NoProviderOperations("no provider operations"),
    NoHeaderCreation("no header creation"),
    NoHeaderCommitmentComputation("no header commitment computation"),
    NoContainerManifestStorageIndexRecordCreation(
        "no container, manifest, storage-index, or record creation",
    ),
    NoSecureMetadataCreation("no secure metadata creation"),
    NoWrappedKeyStorage("no wrapped-key storage"),
    NoStorageNamespaceCreation("no storage namespace creation"),
    NoInitialPersistenceCommit("no initial persistence commit"),
    NoPostCreateUnlockOrSession("no post-create unlock or session"),
    NoCreationRollbackOrFailureCleanup("no creation rollback or failure cleanup"),
    OsKeyringRejectedAsPrimary("OS keyring rejected as primary storage"),
    PasswordManagerRejectedForPassphrase("password manager rejected for passphrase storage"),
    RedactedDiagnosticsOnly("diagnostics must remain redacted"),
    FutureImplementationRequiresReview("future creation implementation requires review"),
}

data class SkaldVaultV1VaultCreationAuthorizationCapability(
    val creationAuthorized: Boolean,
    val creationAttemptAvailable: Boolean,
    val initialPassphraseAccepted: Boolean,
    val initialKeyMaterialAvailable: Boolean,
    val initialSaltGenerationAvailable: Boolean,
    val initialNonceGenerationAvailable: Boolean,
    val initialKdfExecutionAvailable: Boolean,
    val initialProviderOperationAvailable: Boolean,
    val headerCreationAvailable: Boolean,
    val headerCommitmentAvailable: Boolean,
    val containerCreationAvailable: Boolean,
    val manifestCreationAvailable: Boolean,
    val storageIndexCreationAvailable: Boolean,
    val recordCreationAvailable: Boolean,
    val secureMetadataCreationAvailable: Boolean,
    val wrappedKeyStorageAvailable: Boolean,
    val storageNamespaceCreationAvailable: Boolean,
    val initialPersistenceCommitAvailable: Boolean,
    val postCreateUnlockAvailable: Boolean,
    val creationRollbackAvailable: Boolean,
    val creationFailureCleanupAvailable: Boolean,
    val unlockAuthorized: Boolean,
    val kdfCalibrationAuthorized: Boolean,
    val runtimeRandomnessAuthorized: Boolean,
    val providerOperationAuthorized: Boolean,
    val secureStorageAuthorized: Boolean,
    val secureSecretStorageAvailable: Boolean,
    val secureMetadataStorageAvailable: Boolean,
    val encryptedLocalVaultStorageAvailable: Boolean,
    val storageServiceAvailable: Boolean,
    val activeSessionAvailable: Boolean,
    val decryptedKeyMaterialPresent: Boolean,
    val providerSelectable: Boolean,
    val productionProviderSelected: Boolean,
    val providerCryptoAvailable: Boolean,
    val vaultCreationAvailable: Boolean,
    val vaultUnlockAvailable: Boolean,
    val vaultPersistenceAvailable: Boolean,
    val mainnetAvailable: Boolean,
) {
    companion object {
        val StillDisabled = SkaldVaultV1VaultCreationAuthorizationCapability(
            creationAuthorized = false,
            creationAttemptAvailable = false,
            initialPassphraseAccepted = false,
            initialKeyMaterialAvailable = false,
            initialSaltGenerationAvailable = false,
            initialNonceGenerationAvailable = false,
            initialKdfExecutionAvailable = false,
            initialProviderOperationAvailable = false,
            headerCreationAvailable = false,
            headerCommitmentAvailable = false,
            containerCreationAvailable = false,
            manifestCreationAvailable = false,
            storageIndexCreationAvailable = false,
            recordCreationAvailable = false,
            secureMetadataCreationAvailable = false,
            wrappedKeyStorageAvailable = false,
            storageNamespaceCreationAvailable = false,
            initialPersistenceCommitAvailable = false,
            postCreateUnlockAvailable = false,
            creationRollbackAvailable = false,
            creationFailureCleanupAvailable = false,
            unlockAuthorized = false,
            kdfCalibrationAuthorized = false,
            runtimeRandomnessAuthorized = false,
            providerOperationAuthorized = false,
            secureStorageAuthorized = false,
            secureSecretStorageAvailable = false,
            secureMetadataStorageAvailable = false,
            encryptedLocalVaultStorageAvailable = false,
            storageServiceAvailable = false,
            activeSessionAvailable = false,
            decryptedKeyMaterialPresent = false,
            providerSelectable = false,
            productionProviderSelected = false,
            providerCryptoAvailable = false,
            vaultCreationAvailable = false,
            vaultUnlockAvailable = false,
            vaultPersistenceAvailable = false,
            mainnetAvailable = false,
        )
    }
}

data class SkaldVaultV1VaultCreationPolicySummary(
    val policyId: String,
    val policyVersion: Int,
    val operationKinds: Set<SkaldVaultV1VaultCreationOperationKind>,
    val purposes: Set<SkaldVaultV1VaultCreationPurpose>,
    val initializerClasses: Set<SkaldVaultV1VaultCreationInitializerClass>,
    val requiredGates: Set<SkaldVaultV1VaultCreationRequiredGate>,
    val blockers: Set<SkaldVaultV1VaultCreationAuthorizationBlocker>,
    val warnings: Set<SkaldVaultV1VaultCreationAuthorizationWarning>,
    val capability: SkaldVaultV1VaultCreationAuthorizationCapability,
    val stillDisabled: Boolean,
)

class SkaldVaultV1VaultCreationPolicyToken internal constructor(
    val tokenId: String,
    val containsPassphrase: Boolean = false,
    val containsPin: Boolean = false,
    val containsBiometricDetails: Boolean = false,
    val containsKeyMaterial: Boolean = false,
    val containsWrappedKeyBytes: Boolean = false,
    val containsProviderHandle: Boolean = false,
    val containsSecureStorageHandle: Boolean = false,
    val containsOsKeyringHandle: Boolean = false,
    val containsPasswordManagerEntry: Boolean = false,
    val containsAndroidKeystoreHandle: Boolean = false,
    val containsCredentialManagerCredential: Boolean = false,
    val containsKdfInputOutput: Boolean = false,
    val containsSaltNonceRandomBytes: Boolean = false,
    val containsHeaderCommitmentBytes: Boolean = false,
    val containsHeaderContainerManifestIndexRecordBytes: Boolean = false,
    val containsMetadataPayload: Boolean = false,
    val containsCiphertextPlaintextTagBytes: Boolean = false,
    val containsRecordIdentifier: Boolean = false,
    val containsRootOrPathText: Boolean = false,
    val containsPayload: Boolean = false,
) {
    override fun toString(): String =
        "SkaldVaultV1VaultCreationPolicyToken(tokenId=$tokenId, redacted=true)"
}

data class SkaldVaultV1VaultCreationAuthorizationEvidence(
    val policyId: String,
    val policyVersion: Int,
    val source: SkaldVaultV1VaultCreationAuthorizationSource,
    val status: SkaldVaultV1VaultCreationAuthorizationStatus,
    val decision: SkaldVaultV1VaultCreationAuthorizationDecision,
    val operationKind: SkaldVaultV1VaultCreationOperationKind?,
    val purpose: SkaldVaultV1VaultCreationPurpose?,
    val initializerClass: SkaldVaultV1VaultCreationInitializerClass?,
    val requiredGate: SkaldVaultV1VaultCreationRequiredGate?,
    val requiredGates: Set<SkaldVaultV1VaultCreationRequiredGate>,
    val blockers: Set<SkaldVaultV1VaultCreationAuthorizationBlocker>,
    val warnings: Set<SkaldVaultV1VaultCreationAuthorizationWarning>,
    val capability: SkaldVaultV1VaultCreationAuthorizationCapability,
    val policySummary: SkaldVaultV1VaultCreationPolicySummary,
    val policyTokenEvidence: SkaldVaultV1VaultCreationPolicyToken,
    val unlockAuthorizationEvidenceConsumed: Boolean,
    val secureStorageAuthorizationEvidenceConsumed: Boolean,
    val kdfCalibrationAuthorizationEvidenceConsumed: Boolean,
    val runtimeRandomnessAuthorizationEvidenceConsumed: Boolean,
    val providerOperationAuthorizationEvidenceConsumed: Boolean,
    val passphrasePolicyEvidenceConsumed: Boolean,
    val secureStorageCapabilityEvidenceConsumed: Boolean,
    val secureMetadataCapabilityEvidenceConsumed: Boolean,
    val lockSessionLifecycleEvidenceConsumed: Boolean,
    val redactionLeakageEvidenceConsumed: Boolean,
    val clearWipeStrategyEvidenceConsumed: Boolean,
    val migrationCorruptionEvidenceConsumed: Boolean,
    val persistenceReadinessEvidenceConsumed: Boolean,
    val disabledStorageFacadeEvidenceConsumed: Boolean,
    val storageSafetyPreflightEvidenceConsumed: Boolean,
    val platformPathConstructionEvidenceConsumed: Boolean,
    val providerSelectionEvidenceConsumed: Boolean,
    val providerAcceptanceEvidenceConsumed: Boolean,
    val dependencyProbeEvidenceConsumed: Boolean,
    val encryptedVaultReadinessEvidenceConsumed: Boolean,
    val creationAuthorizationBoundaryModeled: Boolean = true,
    val creationAuthorizationStillDisabled: Boolean = true,
    val creationAuthorizationBlocksAllOperations: Boolean = true,
    val creationAuthorizationDoesNotAcceptPassphrases: Boolean = true,
    val creationAuthorizationDoesNotGenerateKeys: Boolean = true,
    val creationAuthorizationDoesNotRunKdf: Boolean = true,
    val creationAuthorizationDoesNotWriteStorage: Boolean = true,
    val creationAuthorizationDoesNotCreateSession: Boolean = true,
    val creationAuthorizationDoesNotEnablePersistence: Boolean = true,
    val creationAuthorizationDoesNotEnableProviderSelection: Boolean = true,
    val creationAuthorizationFailureVocabularyModeled: Boolean = true,
    val creationReady: Boolean = false,
    val vaultCreationReady: Boolean = false,
    val creationAuthorized: Boolean = false,
    val initialPassphraseAccepted: Boolean = false,
    val initialKeyMaterialAvailable: Boolean = false,
    val initialKdfExecutionReady: Boolean = false,
    val headerCreationReady: Boolean = false,
    val containerCreationReady: Boolean = false,
    val manifestCreationReady: Boolean = false,
    val storageIndexCreationReady: Boolean = false,
    val recordCreationReady: Boolean = false,
    val initialPersistenceReady: Boolean = false,
    val unlockReady: Boolean = false,
    val vaultUnlockReady: Boolean = false,
    val activeSessionReady: Boolean = false,
    val providerSelectable: Boolean = false,
    val vaultStorageAvailable: Boolean = false,
    val persistenceReady: Boolean = false,
    val vaultPersistenceReady: Boolean = false,
) {
    override fun toString(): String =
        "SkaldVaultV1VaultCreationAuthorizationEvidence(" +
            "policyId=$policyId, " +
            "status=${status.name}, " +
            "decision=${decision.name}, " +
            "source=${source.name}, " +
            "operationKind=${operationKind?.name ?: "none"}, " +
            "purpose=${purpose?.name ?: "none"}, " +
            "initializerClass=${initializerClass?.name ?: "none"}, " +
            "requiredGate=${requiredGate?.name ?: "none"}, " +
            "capability=still-disabled, " +
            "policyTokenEvidence=$policyTokenEvidence)"
}

sealed class SkaldVaultV1VaultCreationAuthorizationResult<out T> {
    data class Blocked<out T>(
        val value: T,
    ) : SkaldVaultV1VaultCreationAuthorizationResult<T>() {
        override fun toString(): String =
            "SkaldVaultV1VaultCreationAuthorizationResult.Blocked(value=$value)"
    }

    data class Rejected(
        val reason: SkaldVaultV1VaultCreationFailureReason,
        val status: SkaldVaultV1VaultCreationAuthorizationStatus,
        val source: SkaldVaultV1VaultCreationAuthorizationSource,
        val safeMessage: String,
    ) : SkaldVaultV1VaultCreationAuthorizationResult<Nothing>() {
        override fun toString(): String =
            "SkaldVaultV1VaultCreationAuthorizationResult.Rejected(" +
                "reason=${reason.name}, " +
                "status=${status.name})"
    }
}

enum class SkaldVaultV1VaultCreationFailureReason(val label: String) {
    EmptyEvidenceRejected("empty creation evidence rejected"),
    ActualPassphraseRejected("actual passphrase rejected"),
    PinStringRejected("PIN string rejected"),
    BiometricResultRejected("biometric result rejected"),
    CredentialBytesRejected("credential bytes rejected"),
    GeneratedKeyBytesRejected("generated key bytes rejected"),
    AndroidKeystoreKeyRejected("Android Keystore key rejected"),
    OsKeyringHandleRejected("OS keyring handle rejected"),
    PasswordManagerEntryRejected("password-manager entry rejected"),
    WrappedKeyBytesRejected("wrapped key bytes rejected"),
    SecureStorageHandleRejected("secure-storage handle rejected"),
    ProviderHandleRejected("provider handle rejected"),
    DecryptedKeyMaterialRejected("decrypted key material rejected"),
    SessionKeyRejected("session key rejected"),
    RootRecordMetadataKeyRejected("root, record, or metadata key rejected"),
    SeedPrivateMnemonicRejected("seed, private key, or mnemonic rejected"),
    KdfInputOutputRejected("KDF input/output rejected"),
    SaltNonceRandomBytesRejected("salt, nonce, random, or entropy bytes rejected"),
    CiphertextPlaintextRecordBytesRejected("ciphertext, plaintext, or record bytes rejected"),
    HeaderCommitmentAadBytesRejected("header commitment or AAD bytes rejected"),
    RawHeaderContainerManifestStorageIndexRecordBytesRejected(
        "raw header, container, manifest, storage-index, or record bytes rejected",
    ),
    ByteArrayInputRejected("byte-array input rejected"),
    CharArrayInputRejected("char-array input rejected"),
    RandomObjectInputRejected("random object input rejected"),
    RawSettingsValueRejected("raw Settings value rejected"),
    RawAbsoluteLocationInputRejected("raw absolute location input rejected"),
    RawRelativeLocationInputRejected("raw relative location input rejected"),
    LinkLikeInputRejected("link-like input rejected"),
    PlatformObjectLikeInputRejected("platform object-like input rejected"),
    SecretMaterialRejected("secret-like material rejected"),
    WalletMaterialRejected("wallet material rejected"),
    BitcoinAddressLikeEvidenceRejected("Bitcoin address-like evidence rejected"),
    TransactionLikeEvidenceRejected("transaction-like evidence rejected"),
    TraversalRejected("traversal-like evidence rejected"),
    UnsupportedCharactersRejected("unsupported evidence characters rejected"),
    RawCreationInputRejected("raw creation input rejected"),
}

class SkaldVaultV1VaultCreationAuthorizationRequest private constructor(
    val source: SkaldVaultV1VaultCreationAuthorizationSource,
    val operationKind: SkaldVaultV1VaultCreationOperationKind?,
    val purpose: SkaldVaultV1VaultCreationPurpose?,
    val initializerClass: SkaldVaultV1VaultCreationInitializerClass?,
    val requiredGate: SkaldVaultV1VaultCreationRequiredGate?,
    private val rawCandidate: String?,
    internal val unlockAuthorizationEvidence: SkaldVaultV1VaultUnlockAuthorizationEvidence?,
    internal val secureStorageAuthorizationEvidence:
        SkaldVaultV1VaultSecureStorageAuthorizationEvidence?,
    internal val kdfCalibrationAuthorizationEvidence:
        SkaldVaultV1VaultKdfCalibrationAuthorizationEvidence?,
    internal val runtimeRandomnessAuthorizationEvidence:
        SkaldVaultV1VaultRuntimeRandomnessAuthorizationEvidence?,
    internal val providerOperationAuthorizationEvidence:
        SkaldVaultV1VaultProviderOperationAuthorizationEvidence?,
    internal val passphrasePolicyEvidence: SkaldVaultV1VaultPassphrasePolicyEvidence?,
    internal val secureStorageCapability: SecureStorageCapability?,
    internal val secureMetadataCapability: SecureMetadataPersistenceCapability?,
    internal val lockSessionLifecycleEvidence: SkaldVaultV1VaultLockSessionEvidence?,
    internal val redactionLeakageEvidence: SkaldVaultV1VaultRedactionEvidence?,
    internal val clearWipeStrategyEvidence: SkaldVaultV1VaultClearWipeEvidence?,
    internal val migrationCorruptionEvidence: SkaldVaultV1VaultMigrationCorruptionEvidence?,
    internal val persistenceReadinessEvidence: SkaldVaultV1VaultPersistenceReadinessEvidence?,
    internal val disabledStorageFacadeEvidence: SkaldVaultV1VaultStorageDisabledEvidence?,
    internal val storageSafetyPreflightEvidence: SkaldVaultV1StorageSafetyPreflightEvidence?,
    internal val platformPathConstructionEvidence: SkaldVaultV1PlatformPathConstructionEvidence?,
    internal val providerSelectionResult: VaultCryptoProviderSelectionResult?,
    internal val providerAcceptanceAssessment: ProductionProviderAcceptanceAssessment?,
    internal val dependencyProbeResult: VaultCryptoDependencyProbeResult?,
    internal val encryptedVaultReadiness: EncryptedVaultReadiness?,
) {
    val rawCandidateRejected: Boolean
        get() = source == SkaldVaultV1VaultCreationAuthorizationSource.RawCreationCandidate

    internal fun rawCandidateOrNull(): String? = rawCandidate

    override fun toString(): String =
        "SkaldVaultV1VaultCreationAuthorizationRequest(" +
            "source=${source.name}, " +
            "operationKind=${operationKind?.name ?: "none"}, " +
            "purpose=${purpose?.name ?: "none"}, " +
            "initializerClass=${initializerClass?.name ?: "none"}, " +
            "requiredGate=${requiredGate?.name ?: "none"}, " +
            "rawCandidate=REDACTED)"

    companion object {
        fun noEvidence(): SkaldVaultV1VaultCreationAuthorizationRequest =
            base(SkaldVaultV1VaultCreationAuthorizationSource.NoEvidence)

        fun summary(): SkaldVaultV1VaultCreationAuthorizationRequest =
            base(SkaldVaultV1VaultCreationAuthorizationSource.PolicySummary)

        fun forOperationKind(
            operationKind: SkaldVaultV1VaultCreationOperationKind,
            purpose: SkaldVaultV1VaultCreationPurpose? = null,
            initializerClass: SkaldVaultV1VaultCreationInitializerClass? = null,
        ): SkaldVaultV1VaultCreationAuthorizationRequest =
            base(
                source = SkaldVaultV1VaultCreationAuthorizationSource.OperationKind,
                operationKind = operationKind,
                purpose = purpose,
                initializerClass = initializerClass,
            )

        fun forPurpose(
            purpose: SkaldVaultV1VaultCreationPurpose,
            operationKind: SkaldVaultV1VaultCreationOperationKind? = null,
            initializerClass: SkaldVaultV1VaultCreationInitializerClass? = null,
        ): SkaldVaultV1VaultCreationAuthorizationRequest =
            base(
                source = SkaldVaultV1VaultCreationAuthorizationSource.Purpose,
                operationKind = operationKind,
                purpose = purpose,
                initializerClass = initializerClass,
            )

        fun forInitializerClass(
            initializerClass: SkaldVaultV1VaultCreationInitializerClass,
            operationKind: SkaldVaultV1VaultCreationOperationKind? = null,
            purpose: SkaldVaultV1VaultCreationPurpose? = null,
        ): SkaldVaultV1VaultCreationAuthorizationRequest =
            base(
                source = SkaldVaultV1VaultCreationAuthorizationSource.InitializerClass,
                operationKind = operationKind,
                purpose = purpose,
                initializerClass = initializerClass,
            )

        fun forRequiredGate(
            requiredGate: SkaldVaultV1VaultCreationRequiredGate,
        ): SkaldVaultV1VaultCreationAuthorizationRequest =
            base(
                source = SkaldVaultV1VaultCreationAuthorizationSource.RequiredGate,
                requiredGate = requiredGate,
            )

        fun fromEvidence(
            unlockAuthorizationEvidence: SkaldVaultV1VaultUnlockAuthorizationEvidence? = null,
            secureStorageAuthorizationEvidence:
                SkaldVaultV1VaultSecureStorageAuthorizationEvidence? = null,
            kdfCalibrationAuthorizationEvidence:
                SkaldVaultV1VaultKdfCalibrationAuthorizationEvidence? = null,
            runtimeRandomnessAuthorizationEvidence:
                SkaldVaultV1VaultRuntimeRandomnessAuthorizationEvidence? = null,
            providerOperationAuthorizationEvidence:
                SkaldVaultV1VaultProviderOperationAuthorizationEvidence? = null,
            passphrasePolicyEvidence: SkaldVaultV1VaultPassphrasePolicyEvidence? = null,
            secureStorageCapability: SecureStorageCapability? = null,
            secureMetadataCapability: SecureMetadataPersistenceCapability? = null,
            lockSessionLifecycleEvidence: SkaldVaultV1VaultLockSessionEvidence? = null,
            redactionLeakageEvidence: SkaldVaultV1VaultRedactionEvidence? = null,
            clearWipeStrategyEvidence: SkaldVaultV1VaultClearWipeEvidence? = null,
            migrationCorruptionEvidence: SkaldVaultV1VaultMigrationCorruptionEvidence? = null,
            persistenceReadinessEvidence: SkaldVaultV1VaultPersistenceReadinessEvidence? = null,
            disabledStorageFacadeEvidence: SkaldVaultV1VaultStorageDisabledEvidence? = null,
            storageSafetyPreflightEvidence: SkaldVaultV1StorageSafetyPreflightEvidence? = null,
            platformPathConstructionEvidence: SkaldVaultV1PlatformPathConstructionEvidence? = null,
            providerSelectionResult: VaultCryptoProviderSelectionResult? = null,
            providerAcceptanceAssessment: ProductionProviderAcceptanceAssessment? = null,
            dependencyProbeResult: VaultCryptoDependencyProbeResult? = null,
            encryptedVaultReadiness: EncryptedVaultReadiness? = null,
            operationKind: SkaldVaultV1VaultCreationOperationKind? = null,
            purpose: SkaldVaultV1VaultCreationPurpose? = null,
            initializerClass: SkaldVaultV1VaultCreationInitializerClass? = null,
        ): SkaldVaultV1VaultCreationAuthorizationRequest =
            base(
                source = SkaldVaultV1VaultCreationAuthorizationSource.ComposedTypedEvidence,
                operationKind = operationKind,
                purpose = purpose,
                initializerClass = initializerClass,
                unlockAuthorizationEvidence = unlockAuthorizationEvidence,
                secureStorageAuthorizationEvidence = secureStorageAuthorizationEvidence,
                kdfCalibrationAuthorizationEvidence = kdfCalibrationAuthorizationEvidence,
                runtimeRandomnessAuthorizationEvidence = runtimeRandomnessAuthorizationEvidence,
                providerOperationAuthorizationEvidence = providerOperationAuthorizationEvidence,
                passphrasePolicyEvidence = passphrasePolicyEvidence,
                secureStorageCapability = secureStorageCapability,
                secureMetadataCapability = secureMetadataCapability,
                lockSessionLifecycleEvidence = lockSessionLifecycleEvidence,
                redactionLeakageEvidence = redactionLeakageEvidence,
                clearWipeStrategyEvidence = clearWipeStrategyEvidence,
                migrationCorruptionEvidence = migrationCorruptionEvidence,
                persistenceReadinessEvidence = persistenceReadinessEvidence,
                disabledStorageFacadeEvidence = disabledStorageFacadeEvidence,
                storageSafetyPreflightEvidence = storageSafetyPreflightEvidence,
                platformPathConstructionEvidence = platformPathConstructionEvidence,
                providerSelectionResult = providerSelectionResult,
                providerAcceptanceAssessment = providerAcceptanceAssessment,
                dependencyProbeResult = dependencyProbeResult,
                encryptedVaultReadiness = encryptedVaultReadiness,
            )

        fun rawCreationCandidate(
            rawCandidate: String?,
        ): SkaldVaultV1VaultCreationAuthorizationRequest =
            base(
                source = SkaldVaultV1VaultCreationAuthorizationSource.RawCreationCandidate,
                rawCandidate = rawCandidate,
            )

        private fun base(
            source: SkaldVaultV1VaultCreationAuthorizationSource,
            operationKind: SkaldVaultV1VaultCreationOperationKind? = null,
            purpose: SkaldVaultV1VaultCreationPurpose? = null,
            initializerClass: SkaldVaultV1VaultCreationInitializerClass? = null,
            requiredGate: SkaldVaultV1VaultCreationRequiredGate? = null,
            rawCandidate: String? = null,
            unlockAuthorizationEvidence: SkaldVaultV1VaultUnlockAuthorizationEvidence? = null,
            secureStorageAuthorizationEvidence:
                SkaldVaultV1VaultSecureStorageAuthorizationEvidence? = null,
            kdfCalibrationAuthorizationEvidence:
                SkaldVaultV1VaultKdfCalibrationAuthorizationEvidence? = null,
            runtimeRandomnessAuthorizationEvidence:
                SkaldVaultV1VaultRuntimeRandomnessAuthorizationEvidence? = null,
            providerOperationAuthorizationEvidence:
                SkaldVaultV1VaultProviderOperationAuthorizationEvidence? = null,
            passphrasePolicyEvidence: SkaldVaultV1VaultPassphrasePolicyEvidence? = null,
            secureStorageCapability: SecureStorageCapability? = null,
            secureMetadataCapability: SecureMetadataPersistenceCapability? = null,
            lockSessionLifecycleEvidence: SkaldVaultV1VaultLockSessionEvidence? = null,
            redactionLeakageEvidence: SkaldVaultV1VaultRedactionEvidence? = null,
            clearWipeStrategyEvidence: SkaldVaultV1VaultClearWipeEvidence? = null,
            migrationCorruptionEvidence: SkaldVaultV1VaultMigrationCorruptionEvidence? = null,
            persistenceReadinessEvidence: SkaldVaultV1VaultPersistenceReadinessEvidence? = null,
            disabledStorageFacadeEvidence: SkaldVaultV1VaultStorageDisabledEvidence? = null,
            storageSafetyPreflightEvidence: SkaldVaultV1StorageSafetyPreflightEvidence? = null,
            platformPathConstructionEvidence: SkaldVaultV1PlatformPathConstructionEvidence? = null,
            providerSelectionResult: VaultCryptoProviderSelectionResult? = null,
            providerAcceptanceAssessment: ProductionProviderAcceptanceAssessment? = null,
            dependencyProbeResult: VaultCryptoDependencyProbeResult? = null,
            encryptedVaultReadiness: EncryptedVaultReadiness? = null,
        ) = SkaldVaultV1VaultCreationAuthorizationRequest(
            source = source,
            operationKind = operationKind,
            purpose = purpose,
            initializerClass = initializerClass,
            requiredGate = requiredGate,
            rawCandidate = rawCandidate,
            unlockAuthorizationEvidence = unlockAuthorizationEvidence,
            secureStorageAuthorizationEvidence = secureStorageAuthorizationEvidence,
            kdfCalibrationAuthorizationEvidence = kdfCalibrationAuthorizationEvidence,
            runtimeRandomnessAuthorizationEvidence = runtimeRandomnessAuthorizationEvidence,
            providerOperationAuthorizationEvidence = providerOperationAuthorizationEvidence,
            passphrasePolicyEvidence = passphrasePolicyEvidence,
            secureStorageCapability = secureStorageCapability,
            secureMetadataCapability = secureMetadataCapability,
            lockSessionLifecycleEvidence = lockSessionLifecycleEvidence,
            redactionLeakageEvidence = redactionLeakageEvidence,
            clearWipeStrategyEvidence = clearWipeStrategyEvidence,
            migrationCorruptionEvidence = migrationCorruptionEvidence,
            persistenceReadinessEvidence = persistenceReadinessEvidence,
            disabledStorageFacadeEvidence = disabledStorageFacadeEvidence,
            storageSafetyPreflightEvidence = storageSafetyPreflightEvidence,
            platformPathConstructionEvidence = platformPathConstructionEvidence,
            providerSelectionResult = providerSelectionResult,
            providerAcceptanceAssessment = providerAcceptanceAssessment,
            dependencyProbeResult = dependencyProbeResult,
            encryptedVaultReadiness = encryptedVaultReadiness,
        )
    }
}

object SkaldVaultV1CreationAuthorizationPolicy : SkaldVaultV1CreationAuthorizationBoundary {
    const val POLICY_ID: String = "skald-vault-v1-creation-authorization-boundary-v1"
    const val POLICY_VERSION: Int = 1

    fun currentPolicySummary(): SkaldVaultV1VaultCreationPolicySummary =
        SkaldVaultV1VaultCreationPolicySummary(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            operationKinds = SkaldVaultV1VaultCreationOperationKind.entries.toSet(),
            purposes = SkaldVaultV1VaultCreationPurpose.entries.toSet(),
            initializerClasses = SkaldVaultV1VaultCreationInitializerClass.entries.toSet(),
            requiredGates = SkaldVaultV1VaultCreationRequiredGate.entries.toSet(),
            blockers = SkaldVaultV1VaultCreationAuthorizationBlocker.entries.toSet(),
            warnings = SkaldVaultV1VaultCreationAuthorizationWarning.entries.toSet(),
            capability = SkaldVaultV1VaultCreationAuthorizationCapability.StillDisabled,
            stillDisabled = true,
        )

    override fun evaluate(
        request: SkaldVaultV1VaultCreationAuthorizationRequest,
    ): SkaldVaultV1VaultCreationAuthorizationResult<SkaldVaultV1VaultCreationAuthorizationEvidence> {
        if (request.source == SkaldVaultV1VaultCreationAuthorizationSource.RawCreationCandidate) {
            return SkaldVaultV1VaultCreationAuthorizationResult.Rejected(
                reason = classifyRawCandidate(request.rawCandidateOrNull()),
                status = SkaldVaultV1VaultCreationAuthorizationStatus.RawCandidateRejected,
                source = request.source,
                safeMessage = "Raw vault creation authorization candidates are rejected; use typed policy evidence only.",
            )
        }

        return SkaldVaultV1VaultCreationAuthorizationResult.Blocked(
            value = evidence(request),
        )
    }

    private fun evidence(
        request: SkaldVaultV1VaultCreationAuthorizationRequest,
    ): SkaldVaultV1VaultCreationAuthorizationEvidence =
        SkaldVaultV1VaultCreationAuthorizationEvidence(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            source = request.source,
            status = statusFor(request.source),
            decision = decisionFor(request),
            operationKind = request.operationKind,
            purpose = request.purpose,
            initializerClass = request.initializerClass,
            requiredGate = request.requiredGate,
            requiredGates = requiredGatesFor(request),
            blockers = blockersFor(request),
            warnings = SkaldVaultV1VaultCreationAuthorizationWarning.entries.toSet(),
            capability = SkaldVaultV1VaultCreationAuthorizationCapability.StillDisabled,
            policySummary = currentPolicySummary(),
            policyTokenEvidence = SkaldVaultV1VaultCreationPolicyToken(
                tokenId = "$POLICY_ID:current:redacted",
            ),
            unlockAuthorizationEvidenceConsumed = request.unlockAuthorizationEvidence != null,
            secureStorageAuthorizationEvidenceConsumed =
                request.secureStorageAuthorizationEvidence != null,
            kdfCalibrationAuthorizationEvidenceConsumed =
                request.kdfCalibrationAuthorizationEvidence != null,
            runtimeRandomnessAuthorizationEvidenceConsumed =
                request.runtimeRandomnessAuthorizationEvidence != null,
            providerOperationAuthorizationEvidenceConsumed =
                request.providerOperationAuthorizationEvidence != null,
            passphrasePolicyEvidenceConsumed = request.passphrasePolicyEvidence != null,
            secureStorageCapabilityEvidenceConsumed = request.secureStorageCapability != null,
            secureMetadataCapabilityEvidenceConsumed = request.secureMetadataCapability != null,
            lockSessionLifecycleEvidenceConsumed = request.lockSessionLifecycleEvidence != null,
            redactionLeakageEvidenceConsumed = request.redactionLeakageEvidence != null,
            clearWipeStrategyEvidenceConsumed = request.clearWipeStrategyEvidence != null,
            migrationCorruptionEvidenceConsumed = request.migrationCorruptionEvidence != null,
            persistenceReadinessEvidenceConsumed = request.persistenceReadinessEvidence != null,
            disabledStorageFacadeEvidenceConsumed = request.disabledStorageFacadeEvidence != null,
            storageSafetyPreflightEvidenceConsumed = request.storageSafetyPreflightEvidence != null,
            platformPathConstructionEvidenceConsumed = request.platformPathConstructionEvidence != null,
            providerSelectionEvidenceConsumed = request.providerSelectionResult != null,
            providerAcceptanceEvidenceConsumed = request.providerAcceptanceAssessment != null,
            dependencyProbeEvidenceConsumed = request.dependencyProbeResult != null,
            encryptedVaultReadinessEvidenceConsumed = request.encryptedVaultReadiness != null,
        )

    private fun statusFor(
        source: SkaldVaultV1VaultCreationAuthorizationSource,
    ): SkaldVaultV1VaultCreationAuthorizationStatus =
        when (source) {
            SkaldVaultV1VaultCreationAuthorizationSource.NoEvidence ->
                SkaldVaultV1VaultCreationAuthorizationStatus.NoEvidenceAvailable
            SkaldVaultV1VaultCreationAuthorizationSource.PolicySummary ->
                SkaldVaultV1VaultCreationAuthorizationStatus.PolicySummaryModeled
            SkaldVaultV1VaultCreationAuthorizationSource.OperationKind ->
                SkaldVaultV1VaultCreationAuthorizationStatus.OperationKindModeled
            SkaldVaultV1VaultCreationAuthorizationSource.Purpose ->
                SkaldVaultV1VaultCreationAuthorizationStatus.PurposeModeled
            SkaldVaultV1VaultCreationAuthorizationSource.InitializerClass ->
                SkaldVaultV1VaultCreationAuthorizationStatus.InitializerClassModeled
            SkaldVaultV1VaultCreationAuthorizationSource.RequiredGate ->
                SkaldVaultV1VaultCreationAuthorizationStatus.RequiredGateModeled
            SkaldVaultV1VaultCreationAuthorizationSource.ComposedTypedEvidence ->
                SkaldVaultV1VaultCreationAuthorizationStatus.CreationAuthorizationBlockedStillDisabled
            SkaldVaultV1VaultCreationAuthorizationSource.RawCreationCandidate ->
                SkaldVaultV1VaultCreationAuthorizationStatus.RawCandidateRejected
        }

    private fun decisionFor(
        request: SkaldVaultV1VaultCreationAuthorizationRequest,
    ): SkaldVaultV1VaultCreationAuthorizationDecision =
        when {
            request.operationKind == SkaldVaultV1VaultCreationOperationKind.MainnetCreationAttempt ||
                request.purpose == SkaldVaultV1VaultCreationPurpose.CreateMainnetVault ||
                request.initializerClass?.mainnetBlocked == true ->
                SkaldVaultV1VaultCreationAuthorizationDecision.RejectMainnet
            request.initializerClass?.unknownOrUnsupported == true ->
                SkaldVaultV1VaultCreationAuthorizationDecision.UnsupportedFailClosed
            request.initializerClass?.testOnly == true ||
                request.operationKind == SkaldVaultV1VaultCreationOperationKind.TestOnlyCreationSimulation ||
                request.purpose == SkaldVaultV1VaultCreationPurpose.CreateTestOnlyDeterministicSimulation ->
                SkaldVaultV1VaultCreationAuthorizationDecision.TestOnlyInitializerRejectedForProduction
            request.initializerClass?.rejected == true ||
                request.initializerClass?.osKeyringPrimary == true ||
                request.initializerClass?.passwordManager == true ||
                request.initializerClass?.generatedKey == true ->
                SkaldVaultV1VaultCreationAuthorizationDecision.RejectInitializer
            request.source == SkaldVaultV1VaultCreationAuthorizationSource.NoEvidence ||
                request.source == SkaldVaultV1VaultCreationAuthorizationSource.ComposedTypedEvidence ->
                SkaldVaultV1VaultCreationAuthorizationDecision.BlockedFailClosed
            else -> SkaldVaultV1VaultCreationAuthorizationDecision.Unauthorized
        }

    private fun requiredGatesFor(
        request: SkaldVaultV1VaultCreationAuthorizationRequest,
    ): Set<SkaldVaultV1VaultCreationRequiredGate> =
        buildSet {
            addAll(SkaldVaultV1VaultCreationRequiredGate.entries)
            request.requiredGate?.let(::add)
        }

    private fun blockersFor(
        request: SkaldVaultV1VaultCreationAuthorizationRequest,
    ): Set<SkaldVaultV1VaultCreationAuthorizationBlocker> =
        buildSet {
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.CreationAuthorizationStillDisabled)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.PassphrasePolicyBlocked)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.PassphraseInputMechanismReviewMissing)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.PassphraseRedactionReviewMissing)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.KdfCalibrationAuthorizationBlocked)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.FinalKdfParametersMissing)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.RuntimeRandomnessAuthorizationBlocked)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.ProviderOperationAuthorizationBlocked)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.DisabledProviderSelected)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.ProductionProviderSelectableFalse)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.ProviderKatApprovalMissing)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.SecureStorageAuthorizationBlocked)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.SecureSecretStorageUnavailable)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.SecureMetadataStorageUnavailable)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.EncryptedLocalVaultStorageUnavailable)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.StorageSafetyPreflightBlocked)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.DisabledStorageFacadeBlocked)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.StorageServiceOperationsMissing)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.StorageNamespaceCreationMissing)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.PlatformRootPathSafetyBlocked)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.ManifestStorageIndexRecordInitialCreationMissing)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.HeaderCommitmentAadContractMissing)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.MigrationCorruptionBoundaryBlocked)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.ClearWipeStrategyBlocked)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.LockSessionLifecycleBlocked)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.RedactionLeakageUnsafe)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.PersistenceReadinessBlocked)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.AtomicWriteCrashRecoveryReviewMissing)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.AndroidAppPrivateStoragePolicyUnapproved)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.LinuxCustomRootPolicyReviewMissing)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.SettingsCreationUnlockStateRejected)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.NoRawSecretDiagnosticsMissing)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.PlaintextExportRejected)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.WarningOnlyEvidenceRejected)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.UserConsentOverrideRejected)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.MainnetUnavailable)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.InitialKeyMaterialUnavailable)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.InitialSaltNonceGenerationUnavailable)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.InitialKdfExecutionUnavailable)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.InitialProviderOperationUnavailable)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.HeaderCreationUnavailable)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.HeaderCommitmentUnavailable)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.ContainerCreationUnavailable)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.ManifestCreationUnavailable)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.StorageIndexCreationUnavailable)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.RecordCreationUnavailable)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.SecureMetadataCreationUnavailable)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.WrappedKeyStorageUnavailable)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.InitialPersistenceCommitUnavailable)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.PostCreateUnlockUnavailable)
            add(SkaldVaultV1VaultCreationAuthorizationBlocker.CreationRollbackFailureCleanupUnavailable)
            if (request.source == SkaldVaultV1VaultCreationAuthorizationSource.NoEvidence) {
                add(SkaldVaultV1VaultCreationAuthorizationBlocker.NoEvidenceAvailable)
            }
            when (request.initializerClass) {
                SkaldVaultV1VaultCreationInitializerClass.NoInitializerSupplied,
                SkaldVaultV1VaultCreationInitializerClass.PassphraseInitializerBlocked,
                -> add(SkaldVaultV1VaultCreationAuthorizationBlocker.PassphrasePolicyBlocked)
                SkaldVaultV1VaultCreationInitializerClass.GeneratedKeyInitializerBlocked,
                -> add(SkaldVaultV1VaultCreationAuthorizationBlocker.InitialKeyMaterialUnavailable)
                SkaldVaultV1VaultCreationInitializerClass.HardwareWrappedKeyInitializerFutureOnly,
                SkaldVaultV1VaultCreationInitializerClass.AndroidKeystoreWrapperEvidenceFutureOnly,
                -> add(SkaldVaultV1VaultCreationAuthorizationBlocker.AndroidAppPrivateStoragePolicyUnapproved)
                SkaldVaultV1VaultCreationInitializerClass.LinuxOptionalKeyWrapperEvidenceFutureOnly,
                -> add(SkaldVaultV1VaultCreationAuthorizationBlocker.LinuxCustomRootPolicyReviewMissing)
                SkaldVaultV1VaultCreationInitializerClass.OsKeyringInitializerRejectedAsPrimaryStorage,
                -> add(SkaldVaultV1VaultCreationAuthorizationBlocker.OsKeyringPrimaryStorageRejected)
                SkaldVaultV1VaultCreationInitializerClass
                    .PasswordManagerInitializerRejectedForSkaldManagedPassphraseStorage,
                -> add(SkaldVaultV1VaultCreationAuthorizationBlocker.PasswordManagerPassphraseStorageRejected)
                SkaldVaultV1VaultCreationInitializerClass.ImportedBackupInitializerFutureOnly,
                SkaldVaultV1VaultCreationInitializerClass.MigratedVaultInitializerFutureOnly,
                -> add(SkaldVaultV1VaultCreationAuthorizationBlocker.MigrationCorruptionBoundaryBlocked)
                SkaldVaultV1VaultCreationInitializerClass.TestOnlyPlaceholderInitializerRejectedForProduction,
                -> add(SkaldVaultV1VaultCreationAuthorizationBlocker.TestOnlySimulationRejectedForProduction)
                SkaldVaultV1VaultCreationInitializerClass.UnknownInitializer,
                SkaldVaultV1VaultCreationInitializerClass.UnsupportedInitializer,
                -> add(SkaldVaultV1VaultCreationAuthorizationBlocker.UnknownUnsupportedInitializer)
                SkaldVaultV1VaultCreationInitializerClass.PassphrasePolicyEvidenceOnly,
                null,
                -> Unit
            }
            if (request.operationKind == SkaldVaultV1VaultCreationOperationKind.MainnetCreationAttempt ||
                request.purpose == SkaldVaultV1VaultCreationPurpose.CreateMainnetVault
            ) {
                add(SkaldVaultV1VaultCreationAuthorizationBlocker.MainnetUnavailable)
            }
        }

    private fun classifyRawCandidate(candidate: String?): SkaldVaultV1VaultCreationFailureReason {
        val value = candidate?.trim().orEmpty()
        val lower = value.lowercase()
        if (value.isBlank()) return SkaldVaultV1VaultCreationFailureReason.EmptyEvidenceRejected
        if (".." in value) return SkaldVaultV1VaultCreationFailureReason.TraversalRejected
        if (value.any { it == '#' || it == '\u0000' }) {
            return SkaldVaultV1VaultCreationFailureReason.UnsupportedCharactersRejected
        }
        if (value.startsWith("/") || Regex("""^[A-Za-z]:[\\/].*""").matches(value)) {
            return SkaldVaultV1VaultCreationFailureReason.RawAbsoluteLocationInputRejected
        }
        if ("://" in value) return SkaldVaultV1VaultCreationFailureReason.LinkLikeInputRejected
        if ("/" in value || "\\" in value) {
            return SkaldVaultV1VaultCreationFailureReason.RawRelativeLocationInputRejected
        }
        if (Regex("""^[0-9a-fA-F]{64}$""").matches(value)) {
            return SkaldVaultV1VaultCreationFailureReason.TransactionLikeEvidenceRejected
        }
        if (Regex("""^(bc1|tb1|bcrt1)[a-z0-9]{20,}$""").matches(lower)) {
            return SkaldVaultV1VaultCreationFailureReason.BitcoinAddressLikeEvidenceRejected
        }
        if (lower.startsWith("nsec") || lower.startsWith("xprv") || lower.startsWith("tprv") ||
            Regex("""^[KL5][1-9A-HJ-NP-Za-km-z]{50,51}$""").matches(value)
        ) {
            return SkaldVaultV1VaultCreationFailureReason.WalletMaterialRejected
        }

        return when {
            "passphrase" in lower -> SkaldVaultV1VaultCreationFailureReason.ActualPassphraseRejected
            "pin-string" in lower || lower == "pin" ->
                SkaldVaultV1VaultCreationFailureReason.PinStringRejected
            "biometric-result" in lower -> SkaldVaultV1VaultCreationFailureReason.BiometricResultRejected
            "credential-bytes" in lower -> SkaldVaultV1VaultCreationFailureReason.CredentialBytesRejected
            "generated-key-bytes" in lower -> SkaldVaultV1VaultCreationFailureReason.GeneratedKeyBytesRejected
            "android-keystore-key" in lower ->
                SkaldVaultV1VaultCreationFailureReason.AndroidKeystoreKeyRejected
            "os-keyring-handle" in lower -> SkaldVaultV1VaultCreationFailureReason.OsKeyringHandleRejected
            "password-manager-entry" in lower ->
                SkaldVaultV1VaultCreationFailureReason.PasswordManagerEntryRejected
            "wrapped-key" in lower -> SkaldVaultV1VaultCreationFailureReason.WrappedKeyBytesRejected
            "secure-storage-handle" in lower ->
                SkaldVaultV1VaultCreationFailureReason.SecureStorageHandleRejected
            "provider-handle" in lower -> SkaldVaultV1VaultCreationFailureReason.ProviderHandleRejected
            "decrypted-key-material" in lower ->
                SkaldVaultV1VaultCreationFailureReason.DecryptedKeyMaterialRejected
            "session-key" in lower -> SkaldVaultV1VaultCreationFailureReason.SessionKeyRejected
            "root-key" in lower || "record-key" in lower || "metadata-key" in lower ->
                SkaldVaultV1VaultCreationFailureReason.RootRecordMetadataKeyRejected
            "seed-bytes" in lower || "private-key" in lower || "mnemonic" in lower ||
                "seed-phrase" in lower ->
                SkaldVaultV1VaultCreationFailureReason.SeedPrivateMnemonicRejected
            "kdf-input" in lower || "kdf-output" in lower ->
                SkaldVaultV1VaultCreationFailureReason.KdfInputOutputRejected
            "salt-bytes" in lower || "nonce-bytes" in lower || "random-bytes" in lower ||
                "entropy-bytes" in lower ->
                SkaldVaultV1VaultCreationFailureReason.SaltNonceRandomBytesRejected
            "ciphertext" in lower || "plaintext" in lower || "aead-tag" in lower ||
                "record-bytes" in lower ->
                SkaldVaultV1VaultCreationFailureReason.CiphertextPlaintextRecordBytesRejected
            "header-commitment-bytes" in lower || "aad-bytes" in lower ->
                SkaldVaultV1VaultCreationFailureReason.HeaderCommitmentAadBytesRejected
            "header-bytes" in lower || "container-bytes" in lower || "manifest-bytes" in lower ||
                "storage-index-bytes" in lower ->
                SkaldVaultV1VaultCreationFailureReason
                    .RawHeaderContainerManifestStorageIndexRecordBytesRejected
            "bytearray" in lower -> SkaldVaultV1VaultCreationFailureReason.ByteArrayInputRejected
            "chararray" in lower -> SkaldVaultV1VaultCreationFailureReason.CharArrayInputRejected
            "rng-object" in lower || "random-object" in lower ->
                SkaldVaultV1VaultCreationFailureReason.RandomObjectInputRejected
            "settings-value" in lower -> SkaldVaultV1VaultCreationFailureReason.RawSettingsValueRejected
            "file-object" in lower || "path-object" in lower || "uri-object" in lower ||
                "url-object" in lower ->
                SkaldVaultV1VaultCreationFailureReason.PlatformObjectLikeInputRejected
            "secret" in lower || "credential" in lower ->
                SkaldVaultV1VaultCreationFailureReason.SecretMaterialRejected
            else -> SkaldVaultV1VaultCreationFailureReason.RawCreationInputRejected
        }
    }
}
