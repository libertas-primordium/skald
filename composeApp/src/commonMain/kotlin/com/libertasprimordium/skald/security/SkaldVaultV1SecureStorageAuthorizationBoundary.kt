package com.libertasprimordium.skald.security

interface SkaldVaultV1SecureStorageAuthorizationBoundary {
    fun evaluate(
        request: SkaldVaultV1VaultSecureStorageAuthorizationRequest,
    ): SkaldVaultV1VaultSecureStorageAuthorizationResult<SkaldVaultV1VaultSecureStorageAuthorizationEvidence>
}

enum class SkaldVaultV1VaultSecureStorageAuthorizationSource(val label: String) {
    NoEvidence("no secure-storage authorization evidence"),
    PolicySummary("secure-storage authorization policy summary"),
    OperationKind("secure-storage operation kind"),
    ValueKind("secure-storage value kind"),
    TargetKind("secure-storage target kind"),
    RequiredGate("secure-storage required gate"),
    ComposedTypedEvidence("composed typed secure-storage evidence"),
    RawSecureStorageCandidate("raw secure-storage candidate"),
}

enum class SkaldVaultV1VaultSecureStorageAuthorizationStatus(val label: String) {
    NoEvidenceAvailable("no secure-storage authorization evidence available"),
    PolicySummaryModeled("secure-storage authorization policy summary modeled"),
    OperationKindModeled("secure-storage operation kind modeled"),
    ValueKindModeled("secure-storage value kind modeled"),
    TargetKindModeled("secure-storage target kind modeled"),
    RequiredGateModeled("secure-storage required gate modeled"),
    SecureStorageAuthorizationBlockedStillDisabled(
        "secure-storage authorization blocked because the boundary is still disabled",
    ),
    RawCandidateRejected("raw secure-storage candidate rejected"),
}

enum class SkaldVaultV1VaultSecureStorageAuthorizationDecision(
    val label: String,
    val secureStorageAllowed: Boolean,
    val secretStorageAllowed: Boolean,
    val metadataStorageAllowed: Boolean,
    val wrapperAllowed: Boolean,
    val encryptedVaultStorageAllowed: Boolean,
    val unlockAllowed: Boolean,
    val persistenceAllowed: Boolean,
) {
    BlockedFailClosed(
        label = "blocked fail-closed",
        secureStorageAllowed = false,
        secretStorageAllowed = false,
        metadataStorageAllowed = false,
        wrapperAllowed = false,
        encryptedVaultStorageAllowed = false,
        unlockAllowed = false,
        persistenceAllowed = false,
    ),
    Unauthorized(
        label = "unauthorized",
        secureStorageAllowed = false,
        secretStorageAllowed = false,
        metadataStorageAllowed = false,
        wrapperAllowed = false,
        encryptedVaultStorageAllowed = false,
        unlockAllowed = false,
        persistenceAllowed = false,
    ),
    RejectTarget(
        label = "storage target rejected",
        secureStorageAllowed = false,
        secretStorageAllowed = false,
        metadataStorageAllowed = false,
        wrapperAllowed = false,
        encryptedVaultStorageAllowed = false,
        unlockAllowed = false,
        persistenceAllowed = false,
    ),
    TestOnlyTargetRejectedForProduction(
        label = "test-only storage target rejected for production runtime",
        secureStorageAllowed = false,
        secretStorageAllowed = false,
        metadataStorageAllowed = false,
        wrapperAllowed = false,
        encryptedVaultStorageAllowed = false,
        unlockAllowed = false,
        persistenceAllowed = false,
    ),
    UnsupportedFailClosed(
        label = "unknown or unsupported storage target fails closed",
        secureStorageAllowed = false,
        secretStorageAllowed = false,
        metadataStorageAllowed = false,
        wrapperAllowed = false,
        encryptedVaultStorageAllowed = false,
        unlockAllowed = false,
        persistenceAllowed = false,
    ),
}

enum class SkaldVaultV1VaultSecureStorageOperationKind(val label: String) {
    SecureStorageAvailabilityCheck("secure storage availability check"),
    SecureMetadataAvailabilityCheck("secure metadata availability check"),
    StoreSecret("store secret"),
    RetrieveSecret("retrieve secret"),
    DeleteSecret("delete secret"),
    RotateSecret("rotate secret"),
    WrapKey("wrap key"),
    UnwrapKey("unwrap key"),
    StoreWrappedKey("store wrapped key"),
    RetrieveWrappedKey("retrieve wrapped key"),
    StoreSensitiveMetadata("store sensitive metadata"),
    RetrieveSensitiveMetadata("retrieve sensitive metadata"),
    DeleteSensitiveMetadata("delete sensitive metadata"),
    StoreManifestMetadata("store manifest metadata"),
    RetrieveManifestMetadata("retrieve manifest metadata"),
    StoreRecoveryMetadata("store recovery metadata"),
    RetrieveRecoveryMetadata("retrieve recovery metadata"),
    StoreSessionAdjacentState("store session-adjacent state"),
    RetrieveSessionAdjacentState("retrieve session-adjacent state"),
    ExportBackupMaterial("export backup material"),
    ImportBackupMaterial("import backup material"),
    MigrateSecureStorage("migrate secure storage"),
    ValidateSecureStorageIntegrity("validate secure storage integrity"),
    PurgeSecureStorage("purge secure storage"),
    UseOsKeyring("use OS keyring"),
    UsePasswordManager("use password manager"),
    UseAndroidKeystore("use Android Keystore"),
    UseAndroidCredentialManager("use Android Credential Manager"),
    UseEncryptedLocalVaultStorage("use encrypted local vault storage"),
    UseSettingsStorage("use Settings storage"),
    UsePlaintextStorage("use plaintext storage"),
    UseTestOnlyFakeStorage("use test-only fake storage"),
    ProductionRuntimeSecureStorage("production runtime secure storage"),
    ReleaseValidationSecureStorage("release validation secure storage"),
    MainnetSecureStorage("mainnet secure storage"),
}

enum class SkaldVaultV1VaultSecureStorageValueKind(val label: String) {
    SkaldManagedVaultPassphrase("Skald-managed vault passphrase"),
    PassphraseRetryThrottleState("passphrase retry/throttle state"),
    PassphrasePolicyState("passphrase policy state"),
    VaultRootKey("vault root key"),
    MetadataEncryptionKey("metadata encryption key"),
    RecordEncryptionKey("record encryption key"),
    KeyWrappingKey("key-wrapping key"),
    ProviderRootKey("provider root key"),
    ProviderKeyHandle("provider key handle"),
    AndroidHardwareWrappedKeyHandle("Android hardware-wrapped key handle"),
    FutureLinuxOptionalKeyWrappingHandle("future Linux optional key-wrapping handle"),
    EncryptedLocalVaultContainerKeyMaterial("encrypted local vault container key material"),
    WrappedVaultKeyMaterial("wrapped vault key material"),
    SecureMetadataRecord("secure metadata record"),
    SensitiveMetadataRecord("sensitive metadata record"),
    ManifestMetadata("manifest metadata"),
    StorageIndexMetadata("storage-index metadata"),
    RecordDescriptorMetadata("record descriptor metadata"),
    RecoveryMetadata("recovery metadata"),
    BackupExportKeyMaterial("backup/export key material"),
    MigrationState("migration state"),
    CrashRecoveryState("crash-recovery state"),
    LockSessionToken("lock/session token"),
    ActiveSessionState("active-session state"),
    RedactionDiagnosticState("redaction/diagnostic state"),
    WalletLabel("wallet label"),
    TransactionNote("transaction note"),
    BackendCredential("backend credential"),
    LightningMacaroonRuneNwcSecret("Lightning macaroon/rune/NWC secret"),
    PhoenixdToken("Phoenixd token"),
    CashuProofMaterial("Cashu proof material"),
    NostrNsecPrivateMaterial("Nostr nsec/private material"),
    BdkPersistenceHandle("BDK persistence handle"),
    PublicPolicyEvidence("public policy evidence"),
    PublicNonWalletKatVectorEvidence("public non-wallet KAT/vector evidence"),
    EnumStatusCapabilityEvidence("enum/status/capability evidence"),
    AggregateCountStatistic("aggregate count/statistic"),
}

enum class SkaldVaultV1VaultSecureStorageTargetKind(
    val label: String,
    val forbidden: Boolean,
    val futureReviewOnly: Boolean,
    val testOnly: Boolean,
    val unknownOrUnsupported: Boolean,
    val osKeyringPrimary: Boolean,
    val passwordManager: Boolean,
    val settingsStorage: Boolean,
    val plaintext: Boolean,
    val mainnetBlocked: Boolean,
) {
    AppControlledEncryptedLocalVault(
        label = "app-controlled encrypted local vault",
        forbidden = false,
        futureReviewOnly = true,
        testOnly = false,
        unknownOrUnsupported = false,
        osKeyringPrimary = false,
        passwordManager = false,
        settingsStorage = false,
        plaintext = false,
        mainnetBlocked = false,
    ),
    AppPrivateAndroidInternalStorageFutureEncryptedVaultOnly(
        label = "app-private Android internal storage, future encrypted vault only",
        forbidden = false,
        futureReviewOnly = true,
        testOnly = false,
        unknownOrUnsupported = false,
        osKeyringPrimary = false,
        passwordManager = false,
        settingsStorage = false,
        plaintext = false,
        mainnetBlocked = false,
    ),
    LinuxUserDataEncryptedVaultFutureEncryptedVaultOnly(
        label = "Linux user-data encrypted vault, future encrypted vault only",
        forbidden = false,
        futureReviewOnly = true,
        testOnly = false,
        unknownOrUnsupported = false,
        osKeyringPrimary = false,
        passwordManager = false,
        settingsStorage = false,
        plaintext = false,
        mainnetBlocked = false,
    ),
    AndroidKeystoreKeyWrappingFutureOptionalWrapperOnly(
        label = "Android Keystore key wrapping, future optional wrapper only",
        forbidden = false,
        futureReviewOnly = true,
        testOnly = false,
        unknownOrUnsupported = false,
        osKeyringPrimary = false,
        passwordManager = false,
        settingsStorage = false,
        plaintext = false,
        mainnetBlocked = false,
    ),
    HardwareBackedAndroidWrapperFutureOptionalWrapperOnly(
        label = "hardware-backed Android wrapper, future optional wrapper only",
        forbidden = false,
        futureReviewOnly = true,
        testOnly = false,
        unknownOrUnsupported = false,
        osKeyringPrimary = false,
        passwordManager = false,
        settingsStorage = false,
        plaintext = false,
        mainnetBlocked = false,
    ),
    LinuxOsKeyringRejectedAsPrimaryStorage(
        label = "Linux OS keyring, rejected as primary storage",
        forbidden = true,
        futureReviewOnly = false,
        testOnly = false,
        unknownOrUnsupported = false,
        osKeyringPrimary = true,
        passwordManager = false,
        settingsStorage = false,
        plaintext = false,
        mainnetBlocked = false,
    ),
    LinuxOsKeyringFutureOptionalWrapperOnlyAfterReview(
        label = "Linux OS keyring, future optional wrapper only after review",
        forbidden = false,
        futureReviewOnly = true,
        testOnly = false,
        unknownOrUnsupported = false,
        osKeyringPrimary = false,
        passwordManager = false,
        settingsStorage = false,
        plaintext = false,
        mainnetBlocked = false,
    ),
    PasswordManagerRejectedForSkaldManagedPassphraseStorage(
        label = "password manager, rejected for Skald-managed passphrase storage",
        forbidden = true,
        futureReviewOnly = false,
        testOnly = false,
        unknownOrUnsupported = false,
        osKeyringPrimary = false,
        passwordManager = true,
        settingsStorage = false,
        plaintext = false,
        mainnetBlocked = false,
    ),
    SettingsPreferencesStorageRejectedForSecretsAndSensitiveMetadata(
        label = "Settings/preferences storage, rejected for secrets and sensitive metadata",
        forbidden = true,
        futureReviewOnly = false,
        testOnly = false,
        unknownOrUnsupported = false,
        osKeyringPrimary = false,
        passwordManager = false,
        settingsStorage = true,
        plaintext = false,
        mainnetBlocked = false,
    ),
    PlaintextFileStorageRejected(
        label = "plaintext file storage, rejected",
        forbidden = true,
        futureReviewOnly = false,
        testOnly = false,
        unknownOrUnsupported = false,
        osKeyringPrimary = false,
        passwordManager = false,
        settingsStorage = false,
        plaintext = true,
        mainnetBlocked = false,
    ),
    DatabasePlaintextStorageRejected(
        label = "database plaintext storage, rejected",
        forbidden = true,
        futureReviewOnly = false,
        testOnly = false,
        unknownOrUnsupported = false,
        osKeyringPrimary = false,
        passwordManager = false,
        settingsStorage = false,
        plaintext = true,
        mainnetBlocked = false,
    ),
    DebugLogStorageRejected(
        label = "debug log storage, rejected",
        forbidden = true,
        futureReviewOnly = false,
        testOnly = false,
        unknownOrUnsupported = false,
        osKeyringPrimary = false,
        passwordManager = false,
        settingsStorage = false,
        plaintext = true,
        mainnetBlocked = false,
    ),
    CrashReportStorageRejected(
        label = "crash report storage, rejected",
        forbidden = true,
        futureReviewOnly = false,
        testOnly = false,
        unknownOrUnsupported = false,
        osKeyringPrimary = false,
        passwordManager = false,
        settingsStorage = false,
        plaintext = true,
        mainnetBlocked = false,
    ),
    AnalyticsStorageRejected(
        label = "analytics storage, rejected",
        forbidden = true,
        futureReviewOnly = false,
        testOnly = false,
        unknownOrUnsupported = false,
        osKeyringPrimary = false,
        passwordManager = false,
        settingsStorage = false,
        plaintext = true,
        mainnetBlocked = false,
    ),
    SupportExportStorageRejectedUnlessFutureRedactedReview(
        label = "support export storage, rejected unless explicit future redacted export review",
        forbidden = true,
        futureReviewOnly = true,
        testOnly = false,
        unknownOrUnsupported = false,
        osKeyringPrimary = false,
        passwordManager = false,
        settingsStorage = false,
        plaintext = true,
        mainnetBlocked = false,
    ),
    BackupExportPackageFutureReviewOnly(
        label = "backup/export package, future review only",
        forbidden = false,
        futureReviewOnly = true,
        testOnly = false,
        unknownOrUnsupported = false,
        osKeyringPrimary = false,
        passwordManager = false,
        settingsStorage = false,
        plaintext = false,
        mainnetBlocked = false,
    ),
    TestOnlyFakeStorageNotProduction(
        label = "test-only fake storage, not production",
        forbidden = false,
        futureReviewOnly = false,
        testOnly = true,
        unknownOrUnsupported = false,
        osKeyringPrimary = false,
        passwordManager = false,
        settingsStorage = false,
        plaintext = false,
        mainnetBlocked = false,
    ),
    UnknownTarget(
        label = "unknown target",
        forbidden = true,
        futureReviewOnly = false,
        testOnly = false,
        unknownOrUnsupported = true,
        osKeyringPrimary = false,
        passwordManager = false,
        settingsStorage = false,
        plaintext = false,
        mainnetBlocked = false,
    ),
    UnsupportedTarget(
        label = "unsupported target",
        forbidden = true,
        futureReviewOnly = false,
        testOnly = false,
        unknownOrUnsupported = true,
        osKeyringPrimary = false,
        passwordManager = false,
        settingsStorage = false,
        plaintext = false,
        mainnetBlocked = false,
    ),
}

enum class SkaldVaultV1VaultSecureStorageRequiredGate(val label: String) {
    ValueKindAllowedForSecureStorageTarget("value kind is allowed for secure storage target"),
    TargetIsNotForbidden("target is not forbidden"),
    AppControlledEncryptedLocalVaultReadinessApproved(
        "app-controlled encrypted local vault readiness approved",
    ),
    SecureSecretStorageDesignApproved("secure secret storage design approved"),
    SecureMetadataStorageDesignApproved("secure metadata storage design approved"),
    PersistenceReadinessApproved("persistence readiness approved"),
    StorageServiceImplementedAndApproved("storage service implemented and approved"),
    StorageSafetyPreflightApproved("storage safety preflight approved"),
    PlatformRootPathSafetyApproved("platform root/path safety approved"),
    ProviderOperationAuthorizationApprovedWhereWrappingCryptoIsInvolved(
        "provider operation authorization approved where wrapping/crypto is involved",
    ),
    RuntimeRandomnessAuthorizationApprovedWhereKeysSaltsNoncesAreInvolved(
        "runtime randomness authorization approved where keys/salts/nonces are involved",
    ),
    KdfCalibrationAuthorizationApprovedWherePassphraseDerivedMaterialIsInvolved(
        "KDF calibration authorization approved where passphrase-derived material is involved",
    ),
    PassphrasePolicyApprovedWherePassphraseAdjacentStateIsInvolved(
        "passphrase policy approved where passphrase-adjacent state is involved",
    ),
    LockSessionLifecycleApprovedWhereSessionStateIsInvolved(
        "lock/session lifecycle approved where session state is involved",
    ),
    RedactionLeakagePolicyApproved("redaction/leakage policy approved"),
    ClearWipeStrategyApproved("clear/wipe strategy approved"),
    MigrationCorruptionPolicyApprovedWhereMigrationRecoveryIsInvolved(
        "migration/corruption policy approved where migration/recovery is involved",
    ),
    AndroidKeystoreOptionalWrappingReviewApprovedWhereAndroidWrapperIsUsed(
        "Android Keystore optional wrapping review approved where Android wrapper is used",
    ),
    LinuxOptionalKeyWrappingReviewApprovedWhereLinuxWrapperIsUsed(
        "Linux optional key-wrapping review approved where Linux wrapper is used",
    ),
    OsKeyringNotUsedAsPrimaryStorage("OS keyring not used as primary storage"),
    PasswordManagerNotUsedForSkaldManagedPassphraseStorage(
        "password manager not used for Skald-managed passphrase storage",
    ),
    SettingsNotUsedForSecretsOrSensitiveMetadata("Settings not used for secrets or sensitive metadata"),
    NoRawSecretDiagnostics("no raw secret diagnostics"),
    NoPlaintextExport("no plaintext export"),
    TestOnlyFakeStorageNotUsedForProductionRuntime(
        "test-only fake storage not used for production runtime",
    ),
    MainnetReleaseReviewApproved("mainnet remains disabled unless release review approves it"),
}

enum class SkaldVaultV1VaultSecureStorageBlocker(val label: String) {
    SecureStorageAuthorizationStillDisabled("secure-storage authorization remains still-disabled"),
    NoEvidenceAvailable("no secure-storage authorization evidence available"),
    PersistenceReadinessBlocked("persistence readiness is blocked"),
    DisabledStorageFacadeBlocked("disabled storage facade blocks encrypted-vault storage target"),
    StorageSafetyPreflightBlocked("storage safety preflight is blocked"),
    PlatformRootPathSafetyBlocked("platform root/path safety is blocked"),
    ProviderOperationAuthorizationBlocked("provider operation authorization is blocked"),
    RuntimeRandomnessAuthorizationBlocked("runtime randomness authorization is blocked"),
    KdfCalibrationAuthorizationBlocked("KDF calibration authorization is blocked"),
    PassphrasePolicyBlocked("passphrase policy is blocked"),
    LockSessionLifecycleBlocked("lock/session lifecycle is blocked"),
    RedactionLeakageUnsafe("redaction/leakage policy is unsafe for diagnostics or export"),
    ClearWipeStrategyBlocked("clear/wipe strategy remains disabled"),
    MigrationCorruptionBoundaryBlocked("migration/corruption boundary remains disabled"),
    SecureSecretStorageUnavailable("secure secret storage is unavailable"),
    SecureMetadataStorageUnavailable("secure metadata storage is unavailable"),
    EncryptedLocalVaultStorageUnavailable("encrypted local vault storage is unavailable"),
    StorageServiceImplementationMissing("storage service implementation is missing"),
    StorageTargetForbidden("storage target is forbidden"),
    OsKeyringPrimaryStorageRejected("OS keyring target rejected as primary storage"),
    PasswordManagerPassphraseStorageRejected("password manager target rejected for Skald-managed passphrase"),
    SettingsSecretStorageRejected("Settings target rejected for secrets or sensitive metadata"),
    PlaintextStorageRejected("plaintext storage target rejected"),
    AndroidKeystoreWrappingReviewMissing("Android Keystore wrapping review missing"),
    LinuxOptionalKeyWrappingReviewMissing("Linux optional key-wrapping review missing"),
    TestOnlyFakeStorageRejectedForProduction("test-only fake storage does not authorize production runtime"),
    WarningOnlyEvidenceRejected("warning-only evidence cannot authorize secure storage"),
    UserConsentOverrideRejected("user consent cannot override missing hard gates"),
    MainnetUnavailable("mainnet remains unavailable"),
    UnknownUnsupportedTarget("storage target is unknown or unsupported"),
    RawSecureStorageCandidateRejected("raw secure-storage candidate rejected"),
}

enum class SkaldVaultV1VaultSecureStorageWarning(val label: String) {
    EvidenceOnly("secure-storage authorization result is evidence only"),
    NoStorageImplementation("no secure-storage implementation is added"),
    NoSecretRetrievalOrStorage("no secret retrieval, storage, deletion, or rotation occurs"),
    NoKeyWrappingOrUnwrapping("no key wrapping or unwrapping occurs"),
    NoMetadataStorage("no sensitive metadata storage occurs"),
    NoPlatformStorageApis("no platform storage APIs are used"),
    OsKeyringsRejectedAsPrimaryStorage("OS keyrings are rejected as primary vault storage"),
    PasswordManagersRejectedForSkaldManagedPassphrase(
        "password managers are rejected for Skald-managed vault passphrase storage",
    ),
    SettingsRejectedForSecrets("Settings/preferences are rejected for secrets and sensitive metadata"),
    AndroidWrappingFutureReviewedOnly("Android hardware-backed wrapping is future-reviewed only"),
    LinuxOptionalWrappingFutureReviewedOnly("Linux optional key wrapping is future-reviewed only"),
    RedactedDiagnosticsOnly("diagnostics must remain redacted"),
}

data class SkaldVaultV1VaultSecureStorageCapability(
    val secureStorageAuthorized: Boolean,
    val secureSecretStorageAvailable: Boolean,
    val secureMetadataStorageAvailable: Boolean,
    val encryptedLocalVaultStorageAvailable: Boolean,
    val osKeyringPrimaryStorageAvailable: Boolean,
    val osKeyringOptionalWrappingAvailable: Boolean,
    val passwordManagerPassphraseStorageAvailable: Boolean,
    val androidKeystoreWrappingAvailable: Boolean,
    val androidHardwareBackedWrappingAvailable: Boolean,
    val linuxOptionalKeyWrappingAvailable: Boolean,
    val settingsSecretStorageAvailable: Boolean,
    val plaintextStorageAvailable: Boolean,
    val storeSecretAvailable: Boolean,
    val retrieveSecretAvailable: Boolean,
    val deleteSecretAvailable: Boolean,
    val wrapKeyAvailable: Boolean,
    val unwrapKeyAvailable: Boolean,
    val storeMetadataAvailable: Boolean,
    val retrieveMetadataAvailable: Boolean,
    val exportBackupMaterialAvailable: Boolean,
    val importBackupMaterialAvailable: Boolean,
    val secureStorageMigrationAvailable: Boolean,
    val secureStoragePurgeAvailable: Boolean,
    val providerOperationAuthorized: Boolean,
    val runtimeRandomnessAuthorized: Boolean,
    val kdfCalibrationAuthorized: Boolean,
    val providerSelectable: Boolean,
    val vaultCreationAvailable: Boolean,
    val vaultUnlockAvailable: Boolean,
    val vaultPersistenceAvailable: Boolean,
    val mainnetAvailable: Boolean,
) {
    companion object {
        val StillDisabled = SkaldVaultV1VaultSecureStorageCapability(
            secureStorageAuthorized = false,
            secureSecretStorageAvailable = false,
            secureMetadataStorageAvailable = false,
            encryptedLocalVaultStorageAvailable = false,
            osKeyringPrimaryStorageAvailable = false,
            osKeyringOptionalWrappingAvailable = false,
            passwordManagerPassphraseStorageAvailable = false,
            androidKeystoreWrappingAvailable = false,
            androidHardwareBackedWrappingAvailable = false,
            linuxOptionalKeyWrappingAvailable = false,
            settingsSecretStorageAvailable = false,
            plaintextStorageAvailable = false,
            storeSecretAvailable = false,
            retrieveSecretAvailable = false,
            deleteSecretAvailable = false,
            wrapKeyAvailable = false,
            unwrapKeyAvailable = false,
            storeMetadataAvailable = false,
            retrieveMetadataAvailable = false,
            exportBackupMaterialAvailable = false,
            importBackupMaterialAvailable = false,
            secureStorageMigrationAvailable = false,
            secureStoragePurgeAvailable = false,
            providerOperationAuthorized = false,
            runtimeRandomnessAuthorized = false,
            kdfCalibrationAuthorized = false,
            providerSelectable = false,
            vaultCreationAvailable = false,
            vaultUnlockAvailable = false,
            vaultPersistenceAvailable = false,
            mainnetAvailable = false,
        )
    }
}

data class SkaldVaultV1VaultSecureStoragePolicySummary(
    val policyId: String,
    val policyVersion: Int,
    val operationKinds: Set<SkaldVaultV1VaultSecureStorageOperationKind>,
    val valueKinds: Set<SkaldVaultV1VaultSecureStorageValueKind>,
    val targetKinds: Set<SkaldVaultV1VaultSecureStorageTargetKind>,
    val requiredGates: Set<SkaldVaultV1VaultSecureStorageRequiredGate>,
    val blockers: Set<SkaldVaultV1VaultSecureStorageBlocker>,
    val warnings: Set<SkaldVaultV1VaultSecureStorageWarning>,
    val capability: SkaldVaultV1VaultSecureStorageCapability,
    val stillDisabled: Boolean,
)

class SkaldVaultV1VaultSecureStoragePolicyToken internal constructor(
    val tokenId: String,
    val containsPassphrase: Boolean = false,
    val containsKeyMaterial: Boolean = false,
    val containsWrappedKeyBytes: Boolean = false,
    val containsProviderHandle: Boolean = false,
    val containsOsKeyringHandle: Boolean = false,
    val containsPasswordManagerEntry: Boolean = false,
    val containsAndroidKeystoreHandle: Boolean = false,
    val containsCredentialManagerCredential: Boolean = false,
    val containsMetadataPayload: Boolean = false,
    val containsManifestStorageIndexRecordBytes: Boolean = false,
    val containsCiphertextPlaintextTagNonceSaltKdfRandomBytes: Boolean = false,
    val containsRecordIdentifier: Boolean = false,
    val containsRootOrPathText: Boolean = false,
    val containsPayload: Boolean = false,
) {
    override fun toString(): String =
        "SkaldVaultV1VaultSecureStoragePolicyToken(tokenId=$tokenId, redacted=true)"
}

data class SkaldVaultV1VaultSecureStorageAuthorizationEvidence(
    val policyId: String,
    val policyVersion: Int,
    val source: SkaldVaultV1VaultSecureStorageAuthorizationSource,
    val status: SkaldVaultV1VaultSecureStorageAuthorizationStatus,
    val decision: SkaldVaultV1VaultSecureStorageAuthorizationDecision,
    val operationKind: SkaldVaultV1VaultSecureStorageOperationKind?,
    val valueKind: SkaldVaultV1VaultSecureStorageValueKind?,
    val targetKind: SkaldVaultV1VaultSecureStorageTargetKind?,
    val requiredGate: SkaldVaultV1VaultSecureStorageRequiredGate?,
    val requiredGates: Set<SkaldVaultV1VaultSecureStorageRequiredGate>,
    val blockers: Set<SkaldVaultV1VaultSecureStorageBlocker>,
    val warnings: Set<SkaldVaultV1VaultSecureStorageWarning>,
    val capability: SkaldVaultV1VaultSecureStorageCapability,
    val policySummary: SkaldVaultV1VaultSecureStoragePolicySummary,
    val policyTokenEvidence: SkaldVaultV1VaultSecureStoragePolicyToken,
    val secureStorageCapabilityEvidenceConsumed: Boolean,
    val secureMetadataCapabilityEvidenceConsumed: Boolean,
    val persistenceReadinessEvidenceConsumed: Boolean,
    val storageSafetyPreflightEvidenceConsumed: Boolean,
    val disabledStorageFacadeEvidenceConsumed: Boolean,
    val platformPathConstructionEvidenceConsumed: Boolean,
    val providerOperationAuthorizationEvidenceConsumed: Boolean,
    val runtimeRandomnessAuthorizationEvidenceConsumed: Boolean,
    val kdfCalibrationAuthorizationEvidenceConsumed: Boolean,
    val passphrasePolicyEvidenceConsumed: Boolean,
    val redactionLeakageEvidenceConsumed: Boolean,
    val clearWipeStrategyEvidenceConsumed: Boolean,
    val lockSessionLifecycleEvidenceConsumed: Boolean,
    val migrationCorruptionEvidenceConsumed: Boolean,
    val providerSelectionEvidenceConsumed: Boolean,
    val providerAcceptanceEvidenceConsumed: Boolean,
    val dependencyProbeEvidenceConsumed: Boolean,
    val encryptedVaultReadinessEvidenceConsumed: Boolean,
    val secureStorageAuthorizationBoundaryModeled: Boolean = true,
    val secureStorageAuthorizationStillDisabled: Boolean = true,
    val secureStorageAuthorizationBlocksAllOperations: Boolean = true,
    val secureStorageAuthorizationDoesNotStoreSecrets: Boolean = true,
    val secureStorageAuthorizationDoesNotUseOsKeyrings: Boolean = true,
    val secureStorageAuthorizationDoesNotUsePasswordManagers: Boolean = true,
    val secureStorageAuthorizationDoesNotUseSettingsStorage: Boolean = true,
    val secureStorageAuthorizationDoesNotEnableUnlock: Boolean = true,
    val secureStorageAuthorizationDoesNotEnablePersistence: Boolean = true,
    val secureStorageAuthorizationDoesNotEnableProviderSelection: Boolean = true,
    val secureStorageFailureVocabularyModeled: Boolean = true,
    val secureStorageReady: Boolean = false,
    val secureSecretStorageReady: Boolean = false,
    val secureMetadataStorageReady: Boolean = false,
    val encryptedLocalVaultStorageReady: Boolean = false,
    val androidKeystoreWrappingReady: Boolean = false,
    val linuxOptionalKeyWrappingReady: Boolean = false,
    val osKeyringStorageReady: Boolean = false,
    val passwordManagerStorageReady: Boolean = false,
    val settingsSecretStorageReady: Boolean = false,
    val storeSecretReady: Boolean = false,
    val retrieveSecretReady: Boolean = false,
    val wrapKeyReady: Boolean = false,
    val unwrapKeyReady: Boolean = false,
    val metadataStorageReady: Boolean = false,
    val backupExportReady: Boolean = false,
    val providerSelectable: Boolean = false,
    val vaultUnlockReady: Boolean = false,
    val vaultStorageAvailable: Boolean = false,
    val persistenceReady: Boolean = false,
    val vaultPersistenceReady: Boolean = false,
) {
    override fun toString(): String =
        "SkaldVaultV1VaultSecureStorageAuthorizationEvidence(" +
            "policyId=$policyId, " +
            "status=${status.name}, " +
            "decision=${decision.name}, " +
            "source=${source.name}, " +
            "operationKind=${operationKind?.name ?: "none"}, " +
            "valueKind=${valueKind?.name ?: "none"}, " +
            "targetKind=${targetKind?.name ?: "none"}, " +
            "requiredGate=${requiredGate?.name ?: "none"}, " +
            "capability=still-disabled, " +
            "policyTokenEvidence=$policyTokenEvidence)"
}

sealed class SkaldVaultV1VaultSecureStorageAuthorizationResult<out T> {
    data class Blocked<out T>(
        val value: T,
    ) : SkaldVaultV1VaultSecureStorageAuthorizationResult<T>() {
        override fun toString(): String =
            "SkaldVaultV1VaultSecureStorageAuthorizationResult.Blocked(value=$value)"
    }

    data class Rejected(
        val reason: SkaldVaultV1VaultSecureStorageFailureReason,
        val status: SkaldVaultV1VaultSecureStorageAuthorizationStatus,
        val source: SkaldVaultV1VaultSecureStorageAuthorizationSource,
        val safeMessage: String,
    ) : SkaldVaultV1VaultSecureStorageAuthorizationResult<Nothing>() {
        override fun toString(): String =
            "SkaldVaultV1VaultSecureStorageAuthorizationResult.Rejected(" +
                "reason=${reason.name}, " +
                "status=${status.name})"
    }
}

enum class SkaldVaultV1VaultSecureStorageFailureReason(val label: String) {
    EmptyEvidenceRejected("empty secure-storage evidence rejected"),
    ActualPassphraseRejected("actual passphrase rejected"),
    PinRejected("PIN input rejected"),
    MnemonicRejected("mnemonic input rejected"),
    SeedBytesRejected("seed bytes rejected"),
    KeyBytesRejected("key bytes rejected"),
    WrappedKeyBytesRejected("wrapped key bytes rejected"),
    ProviderKeyMaterialRejected("provider key material rejected"),
    ProviderHandleRejected("provider handle rejected"),
    AndroidKeystoreKeyRejected("Android Keystore key rejected"),
    OsKeyringHandleRejected("OS keyring handle rejected"),
    PasswordManagerEntryRejected("password manager entry rejected"),
    CredentialManagerCredentialRejected("Credential Manager credential rejected"),
    SecureStorageHandleRejected("secure storage handle rejected"),
    DatabaseHandleRejected("database handle rejected"),
    FileHandleRejected("file handle rejected"),
    RawManifestMetadataRecordContainerBytesRejected("raw manifest, metadata, record, or container bytes rejected"),
    CiphertextPlaintextTagNonceSaltKdfRandomBytesRejected(
        "ciphertext, plaintext, tag, nonce, salt, KDF, random, or entropy bytes rejected",
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
    RawSecureStorageInputRejected("raw secure-storage input rejected"),
}

class SkaldVaultV1VaultSecureStorageAuthorizationRequest private constructor(
    val source: SkaldVaultV1VaultSecureStorageAuthorizationSource,
    val operationKind: SkaldVaultV1VaultSecureStorageOperationKind?,
    val valueKind: SkaldVaultV1VaultSecureStorageValueKind?,
    val targetKind: SkaldVaultV1VaultSecureStorageTargetKind?,
    val requiredGate: SkaldVaultV1VaultSecureStorageRequiredGate?,
    private val rawCandidate: String?,
    internal val secureStorageCapability: SecureStorageCapability?,
    internal val secureMetadataCapability: SecureMetadataPersistenceCapability?,
    internal val persistenceReadinessEvidence: SkaldVaultV1VaultPersistenceReadinessEvidence?,
    internal val storageSafetyPreflightEvidence: SkaldVaultV1StorageSafetyPreflightEvidence?,
    internal val disabledStorageFacadeEvidence: SkaldVaultV1VaultStorageDisabledEvidence?,
    internal val platformPathConstructionEvidence: SkaldVaultV1PlatformPathConstructionEvidence?,
    internal val providerOperationAuthorizationEvidence:
        SkaldVaultV1VaultProviderOperationAuthorizationEvidence?,
    internal val runtimeRandomnessAuthorizationEvidence:
        SkaldVaultV1VaultRuntimeRandomnessAuthorizationEvidence?,
    internal val kdfCalibrationAuthorizationEvidence:
        SkaldVaultV1VaultKdfCalibrationAuthorizationEvidence?,
    internal val passphrasePolicyEvidence: SkaldVaultV1VaultPassphrasePolicyEvidence?,
    internal val redactionLeakageEvidence: SkaldVaultV1VaultRedactionEvidence?,
    internal val clearWipeStrategyEvidence: SkaldVaultV1VaultClearWipeEvidence?,
    internal val lockSessionLifecycleEvidence: SkaldVaultV1VaultLockSessionEvidence?,
    internal val migrationCorruptionEvidence: SkaldVaultV1VaultMigrationCorruptionEvidence?,
    internal val providerSelectionResult: VaultCryptoProviderSelectionResult?,
    internal val providerAcceptanceAssessment: ProductionProviderAcceptanceAssessment?,
    internal val dependencyProbeResult: VaultCryptoDependencyProbeResult?,
    internal val encryptedVaultReadiness: EncryptedVaultReadiness?,
) {
    val rawCandidateRejected: Boolean
        get() = source == SkaldVaultV1VaultSecureStorageAuthorizationSource.RawSecureStorageCandidate

    internal fun rawCandidateOrNull(): String? = rawCandidate

    override fun toString(): String =
        "SkaldVaultV1VaultSecureStorageAuthorizationRequest(" +
            "source=${source.name}, " +
            "operationKind=${operationKind?.name ?: "none"}, " +
            "valueKind=${valueKind?.name ?: "none"}, " +
            "targetKind=${targetKind?.name ?: "none"}, " +
            "requiredGate=${requiredGate?.name ?: "none"}, " +
            "rawCandidate=REDACTED)"

    companion object {
        fun noEvidence(): SkaldVaultV1VaultSecureStorageAuthorizationRequest =
            base(SkaldVaultV1VaultSecureStorageAuthorizationSource.NoEvidence)

        fun summary(): SkaldVaultV1VaultSecureStorageAuthorizationRequest =
            base(SkaldVaultV1VaultSecureStorageAuthorizationSource.PolicySummary)

        fun forOperationKind(
            operationKind: SkaldVaultV1VaultSecureStorageOperationKind,
            valueKind: SkaldVaultV1VaultSecureStorageValueKind? = null,
            targetKind: SkaldVaultV1VaultSecureStorageTargetKind? = null,
        ): SkaldVaultV1VaultSecureStorageAuthorizationRequest =
            base(
                source = SkaldVaultV1VaultSecureStorageAuthorizationSource.OperationKind,
                operationKind = operationKind,
                valueKind = valueKind,
                targetKind = targetKind,
            )

        fun forValueKind(
            valueKind: SkaldVaultV1VaultSecureStorageValueKind,
            targetKind: SkaldVaultV1VaultSecureStorageTargetKind? = null,
        ): SkaldVaultV1VaultSecureStorageAuthorizationRequest =
            base(
                source = SkaldVaultV1VaultSecureStorageAuthorizationSource.ValueKind,
                valueKind = valueKind,
                targetKind = targetKind,
            )

        fun forTargetKind(
            targetKind: SkaldVaultV1VaultSecureStorageTargetKind,
            valueKind: SkaldVaultV1VaultSecureStorageValueKind? = null,
        ): SkaldVaultV1VaultSecureStorageAuthorizationRequest =
            base(
                source = SkaldVaultV1VaultSecureStorageAuthorizationSource.TargetKind,
                valueKind = valueKind,
                targetKind = targetKind,
            )

        fun forRequiredGate(
            requiredGate: SkaldVaultV1VaultSecureStorageRequiredGate,
        ): SkaldVaultV1VaultSecureStorageAuthorizationRequest =
            base(
                source = SkaldVaultV1VaultSecureStorageAuthorizationSource.RequiredGate,
                requiredGate = requiredGate,
            )

        fun fromEvidence(
            secureStorageCapability: SecureStorageCapability? = null,
            secureMetadataCapability: SecureMetadataPersistenceCapability? = null,
            persistenceReadinessEvidence: SkaldVaultV1VaultPersistenceReadinessEvidence? = null,
            storageSafetyPreflightEvidence: SkaldVaultV1StorageSafetyPreflightEvidence? = null,
            disabledStorageFacadeEvidence: SkaldVaultV1VaultStorageDisabledEvidence? = null,
            platformPathConstructionEvidence: SkaldVaultV1PlatformPathConstructionEvidence? = null,
            providerOperationAuthorizationEvidence:
                SkaldVaultV1VaultProviderOperationAuthorizationEvidence? = null,
            runtimeRandomnessAuthorizationEvidence:
                SkaldVaultV1VaultRuntimeRandomnessAuthorizationEvidence? = null,
            kdfCalibrationAuthorizationEvidence:
                SkaldVaultV1VaultKdfCalibrationAuthorizationEvidence? = null,
            passphrasePolicyEvidence: SkaldVaultV1VaultPassphrasePolicyEvidence? = null,
            redactionLeakageEvidence: SkaldVaultV1VaultRedactionEvidence? = null,
            clearWipeStrategyEvidence: SkaldVaultV1VaultClearWipeEvidence? = null,
            lockSessionLifecycleEvidence: SkaldVaultV1VaultLockSessionEvidence? = null,
            migrationCorruptionEvidence: SkaldVaultV1VaultMigrationCorruptionEvidence? = null,
            providerSelectionResult: VaultCryptoProviderSelectionResult? = null,
            providerAcceptanceAssessment: ProductionProviderAcceptanceAssessment? = null,
            dependencyProbeResult: VaultCryptoDependencyProbeResult? = null,
            encryptedVaultReadiness: EncryptedVaultReadiness? = null,
            operationKind: SkaldVaultV1VaultSecureStorageOperationKind? = null,
            valueKind: SkaldVaultV1VaultSecureStorageValueKind? = null,
            targetKind: SkaldVaultV1VaultSecureStorageTargetKind? = null,
        ): SkaldVaultV1VaultSecureStorageAuthorizationRequest =
            base(
                source = SkaldVaultV1VaultSecureStorageAuthorizationSource.ComposedTypedEvidence,
                operationKind = operationKind,
                valueKind = valueKind,
                targetKind = targetKind,
                secureStorageCapability = secureStorageCapability,
                secureMetadataCapability = secureMetadataCapability,
                persistenceReadinessEvidence = persistenceReadinessEvidence,
                storageSafetyPreflightEvidence = storageSafetyPreflightEvidence,
                disabledStorageFacadeEvidence = disabledStorageFacadeEvidence,
                platformPathConstructionEvidence = platformPathConstructionEvidence,
                providerOperationAuthorizationEvidence = providerOperationAuthorizationEvidence,
                runtimeRandomnessAuthorizationEvidence = runtimeRandomnessAuthorizationEvidence,
                kdfCalibrationAuthorizationEvidence = kdfCalibrationAuthorizationEvidence,
                passphrasePolicyEvidence = passphrasePolicyEvidence,
                redactionLeakageEvidence = redactionLeakageEvidence,
                clearWipeStrategyEvidence = clearWipeStrategyEvidence,
                lockSessionLifecycleEvidence = lockSessionLifecycleEvidence,
                migrationCorruptionEvidence = migrationCorruptionEvidence,
                providerSelectionResult = providerSelectionResult,
                providerAcceptanceAssessment = providerAcceptanceAssessment,
                dependencyProbeResult = dependencyProbeResult,
                encryptedVaultReadiness = encryptedVaultReadiness,
            )

        fun rawSecureStorageCandidate(
            rawCandidate: String?,
        ): SkaldVaultV1VaultSecureStorageAuthorizationRequest =
            base(
                source = SkaldVaultV1VaultSecureStorageAuthorizationSource.RawSecureStorageCandidate,
                rawCandidate = rawCandidate,
            )

        private fun base(
            source: SkaldVaultV1VaultSecureStorageAuthorizationSource,
            operationKind: SkaldVaultV1VaultSecureStorageOperationKind? = null,
            valueKind: SkaldVaultV1VaultSecureStorageValueKind? = null,
            targetKind: SkaldVaultV1VaultSecureStorageTargetKind? = null,
            requiredGate: SkaldVaultV1VaultSecureStorageRequiredGate? = null,
            rawCandidate: String? = null,
            secureStorageCapability: SecureStorageCapability? = null,
            secureMetadataCapability: SecureMetadataPersistenceCapability? = null,
            persistenceReadinessEvidence: SkaldVaultV1VaultPersistenceReadinessEvidence? = null,
            storageSafetyPreflightEvidence: SkaldVaultV1StorageSafetyPreflightEvidence? = null,
            disabledStorageFacadeEvidence: SkaldVaultV1VaultStorageDisabledEvidence? = null,
            platformPathConstructionEvidence: SkaldVaultV1PlatformPathConstructionEvidence? = null,
            providerOperationAuthorizationEvidence:
                SkaldVaultV1VaultProviderOperationAuthorizationEvidence? = null,
            runtimeRandomnessAuthorizationEvidence:
                SkaldVaultV1VaultRuntimeRandomnessAuthorizationEvidence? = null,
            kdfCalibrationAuthorizationEvidence:
                SkaldVaultV1VaultKdfCalibrationAuthorizationEvidence? = null,
            passphrasePolicyEvidence: SkaldVaultV1VaultPassphrasePolicyEvidence? = null,
            redactionLeakageEvidence: SkaldVaultV1VaultRedactionEvidence? = null,
            clearWipeStrategyEvidence: SkaldVaultV1VaultClearWipeEvidence? = null,
            lockSessionLifecycleEvidence: SkaldVaultV1VaultLockSessionEvidence? = null,
            migrationCorruptionEvidence: SkaldVaultV1VaultMigrationCorruptionEvidence? = null,
            providerSelectionResult: VaultCryptoProviderSelectionResult? = null,
            providerAcceptanceAssessment: ProductionProviderAcceptanceAssessment? = null,
            dependencyProbeResult: VaultCryptoDependencyProbeResult? = null,
            encryptedVaultReadiness: EncryptedVaultReadiness? = null,
        ) = SkaldVaultV1VaultSecureStorageAuthorizationRequest(
            source = source,
            operationKind = operationKind,
            valueKind = valueKind,
            targetKind = targetKind,
            requiredGate = requiredGate,
            rawCandidate = rawCandidate,
            secureStorageCapability = secureStorageCapability,
            secureMetadataCapability = secureMetadataCapability,
            persistenceReadinessEvidence = persistenceReadinessEvidence,
            storageSafetyPreflightEvidence = storageSafetyPreflightEvidence,
            disabledStorageFacadeEvidence = disabledStorageFacadeEvidence,
            platformPathConstructionEvidence = platformPathConstructionEvidence,
            providerOperationAuthorizationEvidence = providerOperationAuthorizationEvidence,
            runtimeRandomnessAuthorizationEvidence = runtimeRandomnessAuthorizationEvidence,
            kdfCalibrationAuthorizationEvidence = kdfCalibrationAuthorizationEvidence,
            passphrasePolicyEvidence = passphrasePolicyEvidence,
            redactionLeakageEvidence = redactionLeakageEvidence,
            clearWipeStrategyEvidence = clearWipeStrategyEvidence,
            lockSessionLifecycleEvidence = lockSessionLifecycleEvidence,
            migrationCorruptionEvidence = migrationCorruptionEvidence,
            providerSelectionResult = providerSelectionResult,
            providerAcceptanceAssessment = providerAcceptanceAssessment,
            dependencyProbeResult = dependencyProbeResult,
            encryptedVaultReadiness = encryptedVaultReadiness,
        )
    }
}

object SkaldVaultV1SecureStorageAuthorizationPolicy :
    SkaldVaultV1SecureStorageAuthorizationBoundary {
    const val POLICY_ID: String = "skald-vault-v1-secure-storage-authorization-boundary-v1"
    const val POLICY_VERSION: Int = 1

    fun currentPolicySummary(): SkaldVaultV1VaultSecureStoragePolicySummary =
        SkaldVaultV1VaultSecureStoragePolicySummary(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            operationKinds = SkaldVaultV1VaultSecureStorageOperationKind.entries.toSet(),
            valueKinds = SkaldVaultV1VaultSecureStorageValueKind.entries.toSet(),
            targetKinds = SkaldVaultV1VaultSecureStorageTargetKind.entries.toSet(),
            requiredGates = SkaldVaultV1VaultSecureStorageRequiredGate.entries.toSet(),
            blockers = SkaldVaultV1VaultSecureStorageBlocker.entries.toSet(),
            warnings = SkaldVaultV1VaultSecureStorageWarning.entries.toSet(),
            capability = SkaldVaultV1VaultSecureStorageCapability.StillDisabled,
            stillDisabled = true,
        )

    override fun evaluate(
        request: SkaldVaultV1VaultSecureStorageAuthorizationRequest,
    ): SkaldVaultV1VaultSecureStorageAuthorizationResult<SkaldVaultV1VaultSecureStorageAuthorizationEvidence> {
        if (request.source == SkaldVaultV1VaultSecureStorageAuthorizationSource.RawSecureStorageCandidate) {
            return SkaldVaultV1VaultSecureStorageAuthorizationResult.Rejected(
                reason = classifyRawCandidate(request.rawCandidateOrNull()),
                status = SkaldVaultV1VaultSecureStorageAuthorizationStatus.RawCandidateRejected,
                source = request.source,
                safeMessage = "Raw secure-storage authorization candidates are rejected; use typed policy evidence only.",
            )
        }

        return SkaldVaultV1VaultSecureStorageAuthorizationResult.Blocked(
            value = evidence(request),
        )
    }

    private fun evidence(
        request: SkaldVaultV1VaultSecureStorageAuthorizationRequest,
    ): SkaldVaultV1VaultSecureStorageAuthorizationEvidence =
        SkaldVaultV1VaultSecureStorageAuthorizationEvidence(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            source = request.source,
            status = statusFor(request.source),
            decision = decisionFor(request),
            operationKind = request.operationKind,
            valueKind = request.valueKind,
            targetKind = request.targetKind,
            requiredGate = request.requiredGate,
            requiredGates = requiredGatesFor(request),
            blockers = blockersFor(request),
            warnings = SkaldVaultV1VaultSecureStorageWarning.entries.toSet(),
            capability = SkaldVaultV1VaultSecureStorageCapability.StillDisabled,
            policySummary = currentPolicySummary(),
            policyTokenEvidence = SkaldVaultV1VaultSecureStoragePolicyToken(
                tokenId = "$POLICY_ID:current:redacted",
            ),
            secureStorageCapabilityEvidenceConsumed = request.secureStorageCapability != null,
            secureMetadataCapabilityEvidenceConsumed = request.secureMetadataCapability != null,
            persistenceReadinessEvidenceConsumed = request.persistenceReadinessEvidence != null,
            storageSafetyPreflightEvidenceConsumed = request.storageSafetyPreflightEvidence != null,
            disabledStorageFacadeEvidenceConsumed = request.disabledStorageFacadeEvidence != null,
            platformPathConstructionEvidenceConsumed = request.platformPathConstructionEvidence != null,
            providerOperationAuthorizationEvidenceConsumed =
                request.providerOperationAuthorizationEvidence != null,
            runtimeRandomnessAuthorizationEvidenceConsumed =
                request.runtimeRandomnessAuthorizationEvidence != null,
            kdfCalibrationAuthorizationEvidenceConsumed =
                request.kdfCalibrationAuthorizationEvidence != null,
            passphrasePolicyEvidenceConsumed = request.passphrasePolicyEvidence != null,
            redactionLeakageEvidenceConsumed = request.redactionLeakageEvidence != null,
            clearWipeStrategyEvidenceConsumed = request.clearWipeStrategyEvidence != null,
            lockSessionLifecycleEvidenceConsumed = request.lockSessionLifecycleEvidence != null,
            migrationCorruptionEvidenceConsumed = request.migrationCorruptionEvidence != null,
            providerSelectionEvidenceConsumed = request.providerSelectionResult != null,
            providerAcceptanceEvidenceConsumed = request.providerAcceptanceAssessment != null,
            dependencyProbeEvidenceConsumed = request.dependencyProbeResult != null,
            encryptedVaultReadinessEvidenceConsumed = request.encryptedVaultReadiness != null,
        )

    private fun statusFor(
        source: SkaldVaultV1VaultSecureStorageAuthorizationSource,
    ): SkaldVaultV1VaultSecureStorageAuthorizationStatus =
        when (source) {
            SkaldVaultV1VaultSecureStorageAuthorizationSource.NoEvidence ->
                SkaldVaultV1VaultSecureStorageAuthorizationStatus.NoEvidenceAvailable
            SkaldVaultV1VaultSecureStorageAuthorizationSource.PolicySummary ->
                SkaldVaultV1VaultSecureStorageAuthorizationStatus.PolicySummaryModeled
            SkaldVaultV1VaultSecureStorageAuthorizationSource.OperationKind ->
                SkaldVaultV1VaultSecureStorageAuthorizationStatus.OperationKindModeled
            SkaldVaultV1VaultSecureStorageAuthorizationSource.ValueKind ->
                SkaldVaultV1VaultSecureStorageAuthorizationStatus.ValueKindModeled
            SkaldVaultV1VaultSecureStorageAuthorizationSource.TargetKind ->
                SkaldVaultV1VaultSecureStorageAuthorizationStatus.TargetKindModeled
            SkaldVaultV1VaultSecureStorageAuthorizationSource.RequiredGate ->
                SkaldVaultV1VaultSecureStorageAuthorizationStatus.RequiredGateModeled
            SkaldVaultV1VaultSecureStorageAuthorizationSource.ComposedTypedEvidence ->
                SkaldVaultV1VaultSecureStorageAuthorizationStatus.SecureStorageAuthorizationBlockedStillDisabled
            SkaldVaultV1VaultSecureStorageAuthorizationSource.RawSecureStorageCandidate ->
                SkaldVaultV1VaultSecureStorageAuthorizationStatus.RawCandidateRejected
        }

    private fun decisionFor(
        request: SkaldVaultV1VaultSecureStorageAuthorizationRequest,
    ): SkaldVaultV1VaultSecureStorageAuthorizationDecision =
        when {
            request.operationKind == SkaldVaultV1VaultSecureStorageOperationKind.MainnetSecureStorage ||
                request.targetKind?.mainnetBlocked == true ->
                SkaldVaultV1VaultSecureStorageAuthorizationDecision.RejectTarget
            request.targetKind?.unknownOrUnsupported == true ->
                SkaldVaultV1VaultSecureStorageAuthorizationDecision.UnsupportedFailClosed
            request.targetKind?.testOnly == true ||
                request.operationKind == SkaldVaultV1VaultSecureStorageOperationKind.UseTestOnlyFakeStorage ->
                SkaldVaultV1VaultSecureStorageAuthorizationDecision.TestOnlyTargetRejectedForProduction
            request.targetKind?.forbidden == true ->
                SkaldVaultV1VaultSecureStorageAuthorizationDecision.RejectTarget
            request.source == SkaldVaultV1VaultSecureStorageAuthorizationSource.NoEvidence ||
                request.source == SkaldVaultV1VaultSecureStorageAuthorizationSource.ComposedTypedEvidence ->
                SkaldVaultV1VaultSecureStorageAuthorizationDecision.BlockedFailClosed
            else -> SkaldVaultV1VaultSecureStorageAuthorizationDecision.Unauthorized
        }

    private fun requiredGatesFor(
        request: SkaldVaultV1VaultSecureStorageAuthorizationRequest,
    ): Set<SkaldVaultV1VaultSecureStorageRequiredGate> =
        buildSet {
            addAll(SkaldVaultV1VaultSecureStorageRequiredGate.entries)
            request.requiredGate?.let(::add)
        }

    private fun blockersFor(
        request: SkaldVaultV1VaultSecureStorageAuthorizationRequest,
    ): Set<SkaldVaultV1VaultSecureStorageBlocker> =
        buildSet {
            add(SkaldVaultV1VaultSecureStorageBlocker.SecureStorageAuthorizationStillDisabled)
            add(SkaldVaultV1VaultSecureStorageBlocker.PersistenceReadinessBlocked)
            add(SkaldVaultV1VaultSecureStorageBlocker.DisabledStorageFacadeBlocked)
            add(SkaldVaultV1VaultSecureStorageBlocker.StorageSafetyPreflightBlocked)
            add(SkaldVaultV1VaultSecureStorageBlocker.PlatformRootPathSafetyBlocked)
            add(SkaldVaultV1VaultSecureStorageBlocker.ProviderOperationAuthorizationBlocked)
            add(SkaldVaultV1VaultSecureStorageBlocker.RuntimeRandomnessAuthorizationBlocked)
            add(SkaldVaultV1VaultSecureStorageBlocker.KdfCalibrationAuthorizationBlocked)
            add(SkaldVaultV1VaultSecureStorageBlocker.PassphrasePolicyBlocked)
            add(SkaldVaultV1VaultSecureStorageBlocker.LockSessionLifecycleBlocked)
            add(SkaldVaultV1VaultSecureStorageBlocker.RedactionLeakageUnsafe)
            add(SkaldVaultV1VaultSecureStorageBlocker.ClearWipeStrategyBlocked)
            add(SkaldVaultV1VaultSecureStorageBlocker.MigrationCorruptionBoundaryBlocked)
            add(SkaldVaultV1VaultSecureStorageBlocker.SecureSecretStorageUnavailable)
            add(SkaldVaultV1VaultSecureStorageBlocker.SecureMetadataStorageUnavailable)
            add(SkaldVaultV1VaultSecureStorageBlocker.EncryptedLocalVaultStorageUnavailable)
            add(SkaldVaultV1VaultSecureStorageBlocker.StorageServiceImplementationMissing)
            add(SkaldVaultV1VaultSecureStorageBlocker.WarningOnlyEvidenceRejected)
            add(SkaldVaultV1VaultSecureStorageBlocker.UserConsentOverrideRejected)
            add(SkaldVaultV1VaultSecureStorageBlocker.MainnetUnavailable)
            if (request.source == SkaldVaultV1VaultSecureStorageAuthorizationSource.NoEvidence) {
                add(SkaldVaultV1VaultSecureStorageBlocker.NoEvidenceAvailable)
            }
            when (request.targetKind) {
                SkaldVaultV1VaultSecureStorageTargetKind.LinuxOsKeyringRejectedAsPrimaryStorage -> {
                    add(SkaldVaultV1VaultSecureStorageBlocker.StorageTargetForbidden)
                    add(SkaldVaultV1VaultSecureStorageBlocker.OsKeyringPrimaryStorageRejected)
                }
                SkaldVaultV1VaultSecureStorageTargetKind.PasswordManagerRejectedForSkaldManagedPassphraseStorage -> {
                    add(SkaldVaultV1VaultSecureStorageBlocker.StorageTargetForbidden)
                    add(SkaldVaultV1VaultSecureStorageBlocker.PasswordManagerPassphraseStorageRejected)
                }
                SkaldVaultV1VaultSecureStorageTargetKind.SettingsPreferencesStorageRejectedForSecretsAndSensitiveMetadata -> {
                    add(SkaldVaultV1VaultSecureStorageBlocker.StorageTargetForbidden)
                    add(SkaldVaultV1VaultSecureStorageBlocker.SettingsSecretStorageRejected)
                }
                SkaldVaultV1VaultSecureStorageTargetKind.PlaintextFileStorageRejected,
                SkaldVaultV1VaultSecureStorageTargetKind.DatabasePlaintextStorageRejected,
                SkaldVaultV1VaultSecureStorageTargetKind.DebugLogStorageRejected,
                SkaldVaultV1VaultSecureStorageTargetKind.CrashReportStorageRejected,
                SkaldVaultV1VaultSecureStorageTargetKind.AnalyticsStorageRejected,
                SkaldVaultV1VaultSecureStorageTargetKind.SupportExportStorageRejectedUnlessFutureRedactedReview,
                -> {
                    add(SkaldVaultV1VaultSecureStorageBlocker.StorageTargetForbidden)
                    add(SkaldVaultV1VaultSecureStorageBlocker.PlaintextStorageRejected)
                }
                SkaldVaultV1VaultSecureStorageTargetKind.AndroidKeystoreKeyWrappingFutureOptionalWrapperOnly,
                SkaldVaultV1VaultSecureStorageTargetKind.HardwareBackedAndroidWrapperFutureOptionalWrapperOnly,
                -> add(SkaldVaultV1VaultSecureStorageBlocker.AndroidKeystoreWrappingReviewMissing)
                SkaldVaultV1VaultSecureStorageTargetKind.LinuxOsKeyringFutureOptionalWrapperOnlyAfterReview,
                -> add(SkaldVaultV1VaultSecureStorageBlocker.LinuxOptionalKeyWrappingReviewMissing)
                SkaldVaultV1VaultSecureStorageTargetKind.TestOnlyFakeStorageNotProduction,
                -> add(SkaldVaultV1VaultSecureStorageBlocker.TestOnlyFakeStorageRejectedForProduction)
                SkaldVaultV1VaultSecureStorageTargetKind.UnknownTarget,
                SkaldVaultV1VaultSecureStorageTargetKind.UnsupportedTarget,
                -> add(SkaldVaultV1VaultSecureStorageBlocker.UnknownUnsupportedTarget)
                SkaldVaultV1VaultSecureStorageTargetKind.AppControlledEncryptedLocalVault,
                SkaldVaultV1VaultSecureStorageTargetKind.AppPrivateAndroidInternalStorageFutureEncryptedVaultOnly,
                SkaldVaultV1VaultSecureStorageTargetKind.LinuxUserDataEncryptedVaultFutureEncryptedVaultOnly,
                SkaldVaultV1VaultSecureStorageTargetKind.BackupExportPackageFutureReviewOnly,
                null,
                -> Unit
            }
            if (request.operationKind == SkaldVaultV1VaultSecureStorageOperationKind.MainnetSecureStorage) {
                add(SkaldVaultV1VaultSecureStorageBlocker.MainnetUnavailable)
            }
        }

    private fun classifyRawCandidate(candidate: String?): SkaldVaultV1VaultSecureStorageFailureReason {
        val value = candidate?.trim().orEmpty()
        val lower = value.lowercase()
        if (value.isBlank()) return SkaldVaultV1VaultSecureStorageFailureReason.EmptyEvidenceRejected
        if (".." in value) return SkaldVaultV1VaultSecureStorageFailureReason.TraversalRejected
        if (value.any { it == '#' || it == '\u0000' }) {
            return SkaldVaultV1VaultSecureStorageFailureReason.UnsupportedCharactersRejected
        }
        if (value.startsWith("/") || Regex("""^[A-Za-z]:[\\/].*""").matches(value)) {
            return SkaldVaultV1VaultSecureStorageFailureReason.RawAbsoluteLocationInputRejected
        }
        if ("://" in value) return SkaldVaultV1VaultSecureStorageFailureReason.LinkLikeInputRejected
        if ("/" in value || "\\" in value) {
            return SkaldVaultV1VaultSecureStorageFailureReason.RawRelativeLocationInputRejected
        }
        if (Regex("""^[0-9a-fA-F]{64}$""").matches(value)) {
            return SkaldVaultV1VaultSecureStorageFailureReason.TransactionLikeEvidenceRejected
        }
        if (Regex("""^(bc1|tb1|bcrt1)[a-z0-9]{20,}$""").matches(lower)) {
            return SkaldVaultV1VaultSecureStorageFailureReason.BitcoinAddressLikeEvidenceRejected
        }
        if (lower.startsWith("nsec") || lower.startsWith("xprv") || lower.startsWith("tprv") ||
            Regex("""^[KL5][1-9A-HJ-NP-Za-km-z]{50,51}$""").matches(value)
        ) {
            return SkaldVaultV1VaultSecureStorageFailureReason.WalletMaterialRejected
        }

        return when {
            "passphrase" in lower -> SkaldVaultV1VaultSecureStorageFailureReason.ActualPassphraseRejected
            "pin" in lower -> SkaldVaultV1VaultSecureStorageFailureReason.PinRejected
            "mnemonic" in lower || "seed-phrase" in lower ->
                SkaldVaultV1VaultSecureStorageFailureReason.MnemonicRejected
            "seed-bytes" in lower -> SkaldVaultV1VaultSecureStorageFailureReason.SeedBytesRejected
            "wrapped-key" in lower -> SkaldVaultV1VaultSecureStorageFailureReason.WrappedKeyBytesRejected
            "key-bytes" in lower || "private-key" in lower ->
                SkaldVaultV1VaultSecureStorageFailureReason.KeyBytesRejected
            "provider-key-material" in lower ->
                SkaldVaultV1VaultSecureStorageFailureReason.ProviderKeyMaterialRejected
            "provider-handle" in lower -> SkaldVaultV1VaultSecureStorageFailureReason.ProviderHandleRejected
            "android-keystore-key" in lower ->
                SkaldVaultV1VaultSecureStorageFailureReason.AndroidKeystoreKeyRejected
            "os-keyring-handle" in lower -> SkaldVaultV1VaultSecureStorageFailureReason.OsKeyringHandleRejected
            "password-manager-entry" in lower ->
                SkaldVaultV1VaultSecureStorageFailureReason.PasswordManagerEntryRejected
            "credential-manager-credential" in lower ->
                SkaldVaultV1VaultSecureStorageFailureReason.CredentialManagerCredentialRejected
            "secure-storage-handle" in lower ->
                SkaldVaultV1VaultSecureStorageFailureReason.SecureStorageHandleRejected
            "database-handle" in lower -> SkaldVaultV1VaultSecureStorageFailureReason.DatabaseHandleRejected
            "file-handle" in lower -> SkaldVaultV1VaultSecureStorageFailureReason.FileHandleRejected
            "manifest-bytes" in lower || "metadata-bytes" in lower ||
                "record-bytes" in lower || "container-bytes" in lower ->
                SkaldVaultV1VaultSecureStorageFailureReason.RawManifestMetadataRecordContainerBytesRejected
            "ciphertext" in lower || "plaintext" in lower || "aead-tag" in lower ||
                "nonce-bytes" in lower || "salt-bytes" in lower || "kdf-input" in lower ||
                "kdf-output" in lower || "random-bytes" in lower || "entropy-bytes" in lower ->
                SkaldVaultV1VaultSecureStorageFailureReason
                    .CiphertextPlaintextTagNonceSaltKdfRandomBytesRejected
            "bytearray" in lower -> SkaldVaultV1VaultSecureStorageFailureReason.ByteArrayInputRejected
            "chararray" in lower -> SkaldVaultV1VaultSecureStorageFailureReason.CharArrayInputRejected
            "rng-object" in lower || "random-object" in lower ->
                SkaldVaultV1VaultSecureStorageFailureReason.RandomObjectInputRejected
            "settings-value" in lower -> SkaldVaultV1VaultSecureStorageFailureReason.RawSettingsValueRejected
            "file-object" in lower || "path-object" in lower || "uri-object" in lower ||
                "url-object" in lower ->
                SkaldVaultV1VaultSecureStorageFailureReason.PlatformObjectLikeInputRejected
            "secret" in lower || "credential" in lower ->
                SkaldVaultV1VaultSecureStorageFailureReason.SecretMaterialRejected
            else -> SkaldVaultV1VaultSecureStorageFailureReason.RawSecureStorageInputRejected
        }
    }
}
