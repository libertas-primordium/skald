package com.libertasprimordium.skald.security

interface SkaldVaultV1UnlockAuthorizationBoundary {
    fun evaluate(
        request: SkaldVaultV1VaultUnlockAuthorizationRequest,
    ): SkaldVaultV1VaultUnlockAuthorizationResult<SkaldVaultV1VaultUnlockAuthorizationEvidence>
}

enum class SkaldVaultV1VaultUnlockAuthorizationSource(val label: String) {
    NoEvidence("no unlock authorization evidence"),
    PolicySummary("unlock authorization policy summary"),
    OperationKind("unlock operation kind"),
    Purpose("unlock purpose"),
    CredentialClass("unlock credential class"),
    RequiredGate("unlock required gate"),
    ComposedTypedEvidence("composed typed unlock evidence"),
    RawUnlockCandidate("raw unlock candidate"),
}

enum class SkaldVaultV1VaultUnlockAuthorizationStatus(val label: String) {
    NoEvidenceAvailable("no unlock authorization evidence available"),
    PolicySummaryModeled("unlock authorization policy summary modeled"),
    OperationKindModeled("unlock operation kind modeled"),
    PurposeModeled("unlock purpose modeled"),
    CredentialClassModeled("unlock credential class modeled"),
    RequiredGateModeled("unlock required gate modeled"),
    UnlockAuthorizationBlockedStillDisabled(
        "unlock authorization blocked because the boundary is still disabled",
    ),
    RawCandidateRejected("raw unlock candidate rejected"),
}

enum class SkaldVaultV1VaultUnlockAuthorizationDecision(
    val label: String,
    val unlockAllowed: Boolean,
    val unlockAttemptAllowed: Boolean,
    val passphraseInputAllowed: Boolean,
    val kdfAllowed: Boolean,
    val providerOperationAllowed: Boolean,
    val storageReadAllowed: Boolean,
    val sessionCreationAllowed: Boolean,
    val persistenceAllowed: Boolean,
) {
    BlockedFailClosed(
        label = "blocked fail-closed",
        unlockAllowed = false,
        unlockAttemptAllowed = false,
        passphraseInputAllowed = false,
        kdfAllowed = false,
        providerOperationAllowed = false,
        storageReadAllowed = false,
        sessionCreationAllowed = false,
        persistenceAllowed = false,
    ),
    Unauthorized(
        label = "unauthorized",
        unlockAllowed = false,
        unlockAttemptAllowed = false,
        passphraseInputAllowed = false,
        kdfAllowed = false,
        providerOperationAllowed = false,
        storageReadAllowed = false,
        sessionCreationAllowed = false,
        persistenceAllowed = false,
    ),
    RejectCredential(
        label = "credential rejected",
        unlockAllowed = false,
        unlockAttemptAllowed = false,
        passphraseInputAllowed = false,
        kdfAllowed = false,
        providerOperationAllowed = false,
        storageReadAllowed = false,
        sessionCreationAllowed = false,
        persistenceAllowed = false,
    ),
    TestOnlyCredentialRejectedForProduction(
        label = "test-only credential rejected for production runtime",
        unlockAllowed = false,
        unlockAttemptAllowed = false,
        passphraseInputAllowed = false,
        kdfAllowed = false,
        providerOperationAllowed = false,
        storageReadAllowed = false,
        sessionCreationAllowed = false,
        persistenceAllowed = false,
    ),
    UnsupportedFailClosed(
        label = "unknown or unsupported credential fails closed",
        unlockAllowed = false,
        unlockAttemptAllowed = false,
        passphraseInputAllowed = false,
        kdfAllowed = false,
        providerOperationAllowed = false,
        storageReadAllowed = false,
        sessionCreationAllowed = false,
        persistenceAllowed = false,
    ),
    RejectMainnet(
        label = "mainnet unlock rejected",
        unlockAllowed = false,
        unlockAttemptAllowed = false,
        passphraseInputAllowed = false,
        kdfAllowed = false,
        providerOperationAllowed = false,
        storageReadAllowed = false,
        sessionCreationAllowed = false,
        persistenceAllowed = false,
    ),
}

enum class SkaldVaultV1VaultUnlockOperationKind(val label: String) {
    UnlockAvailabilityCheck("unlock availability check"),
    PassphraseUnlockAttempt("passphrase unlock attempt"),
    PinAssistedUnlockAttempt("PIN-assisted unlock attempt"),
    BiometricAssistedUnlockAttempt("biometric-assisted unlock attempt"),
    HardwareWrappedKeyUnlockAttempt("hardware-wrapped-key unlock attempt"),
    OsKeyringAssistedUnlockAttempt("OS-keyring-assisted unlock attempt"),
    PasswordManagerAssistedUnlockAttempt("password-manager-assisted unlock attempt"),
    RestoreImportUnlockAttempt("restore/import unlock attempt"),
    MigrationUnlockAttempt("migration unlock attempt"),
    RecoveryUnlockAttempt("recovery unlock attempt"),
    TestOnlyUnlockSimulation("test-only unlock simulation"),
    ReleaseValidationUnlockAttempt("release-validation unlock attempt"),
    ProductionRuntimeUnlockAttempt("production runtime unlock attempt"),
    MainnetUnlockAttempt("mainnet unlock attempt"),
    ActiveSessionCreation("active session creation"),
    ActiveSessionRefresh("active session refresh"),
    ActiveSessionResume("active session resume"),
    ActiveSessionCloseLock("active session close/lock"),
    ActiveSessionFailureCleanup("active session failure cleanup"),
    ActiveSessionStatusQuery("active session status query"),
}

enum class SkaldVaultV1VaultUnlockPurpose(val label: String) {
    CreateVault("create vault"),
    UnlockExistingVault("unlock existing vault"),
    InspectVaultStatus("inspect vault status"),
    MigrateVault("migrate vault"),
    RecoverVault("recover vault"),
    ImportBackup("import backup"),
    ExportBackup("export backup"),
    DecryptMetadata("decrypt metadata"),
    DecryptRecord("decrypt record"),
    PrepareWalletSync("prepare wallet sync"),
    PrepareSigningFlow("prepare signing flow"),
    PrepareRecoveryCenterStatus("prepare Recovery Center status"),
    PreparePrivacyAnalyzerStatus("prepare Privacy Analyzer status"),
    TestOnlyDeterministicSimulation("test-only deterministic simulation"),
    ReleaseValidation("release validation"),
    ProductionRuntime("production runtime"),
    MainnetValidation("mainnet validation"),
}

enum class SkaldVaultV1VaultUnlockCredentialClass(
    val label: String,
    val rejected: Boolean,
    val futureOnly: Boolean,
    val testOnly: Boolean,
    val unknownOrUnsupported: Boolean,
    val osKeyringPrimary: Boolean,
    val passwordManager: Boolean,
    val pin: Boolean,
    val biometric: Boolean,
    val mainnetBlocked: Boolean,
) {
    NoCredentialSupplied(
        label = "no credential supplied",
        rejected = true,
        futureOnly = false,
        testOnly = false,
        unknownOrUnsupported = false,
        osKeyringPrimary = false,
        passwordManager = false,
        pin = false,
        biometric = false,
        mainnetBlocked = false,
    ),
    PassphraseNotAccepted(
        label = "passphrase not accepted",
        rejected = true,
        futureOnly = false,
        testOnly = false,
        unknownOrUnsupported = false,
        osKeyringPrimary = false,
        passwordManager = false,
        pin = false,
        biometric = false,
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
        pin = false,
        biometric = false,
        mainnetBlocked = false,
    ),
    PinRejected(
        label = "PIN rejected",
        rejected = true,
        futureOnly = false,
        testOnly = false,
        unknownOrUnsupported = false,
        osKeyringPrimary = false,
        passwordManager = false,
        pin = true,
        biometric = false,
        mainnetBlocked = false,
    ),
    BiometricConvenienceRejectedFutureOnly(
        label = "biometric convenience rejected/future-only",
        rejected = true,
        futureOnly = true,
        testOnly = false,
        unknownOrUnsupported = false,
        osKeyringPrimary = false,
        passwordManager = false,
        pin = false,
        biometric = true,
        mainnetBlocked = false,
    ),
    AndroidHardwareWrappedKeyEvidenceOnly(
        label = "Android hardware-wrapped key evidence only",
        rejected = false,
        futureOnly = true,
        testOnly = false,
        unknownOrUnsupported = false,
        osKeyringPrimary = false,
        passwordManager = false,
        pin = false,
        biometric = false,
        mainnetBlocked = false,
    ),
    AndroidKeystoreEvidenceFutureOnly(
        label = "Android key-wrapper evidence future-only",
        rejected = false,
        futureOnly = true,
        testOnly = false,
        unknownOrUnsupported = false,
        osKeyringPrimary = false,
        passwordManager = false,
        pin = false,
        biometric = false,
        mainnetBlocked = false,
    ),
    OsKeyringEvidenceRejectedAsPrimaryStorage(
        label = "OS keyring evidence rejected as primary storage",
        rejected = true,
        futureOnly = false,
        testOnly = false,
        unknownOrUnsupported = false,
        osKeyringPrimary = true,
        passwordManager = false,
        pin = false,
        biometric = false,
        mainnetBlocked = false,
    ),
    OsKeyringOptionalWrappingFutureOnly(
        label = "OS keyring optional wrapping future-only",
        rejected = false,
        futureOnly = true,
        testOnly = false,
        unknownOrUnsupported = false,
        osKeyringPrimary = false,
        passwordManager = false,
        pin = false,
        biometric = false,
        mainnetBlocked = false,
    ),
    PasswordManagerEvidenceRejectedForSkaldManagedPassphraseStorage(
        label = "password-manager evidence rejected for Skald-managed passphrase storage",
        rejected = true,
        futureOnly = false,
        testOnly = false,
        unknownOrUnsupported = false,
        osKeyringPrimary = false,
        passwordManager = true,
        pin = false,
        biometric = false,
        mainnetBlocked = false,
    ),
    SecureStorageWrappedKeyEvidenceFutureOnly(
        label = "secure-storage wrapped-key evidence future-only",
        rejected = false,
        futureOnly = true,
        testOnly = false,
        unknownOrUnsupported = false,
        osKeyringPrimary = false,
        passwordManager = false,
        pin = false,
        biometric = false,
        mainnetBlocked = false,
    ),
    TestOnlyPlaceholderCredentialRejectedForProduction(
        label = "test-only placeholder credential rejected for production",
        rejected = false,
        futureOnly = false,
        testOnly = true,
        unknownOrUnsupported = false,
        osKeyringPrimary = false,
        passwordManager = false,
        pin = false,
        biometric = false,
        mainnetBlocked = false,
    ),
    UnknownCredential(
        label = "unknown credential",
        rejected = true,
        futureOnly = false,
        testOnly = false,
        unknownOrUnsupported = true,
        osKeyringPrimary = false,
        passwordManager = false,
        pin = false,
        biometric = false,
        mainnetBlocked = false,
    ),
    UnsupportedCredential(
        label = "unsupported credential",
        rejected = true,
        futureOnly = false,
        testOnly = false,
        unknownOrUnsupported = true,
        osKeyringPrimary = false,
        passwordManager = false,
        pin = false,
        biometric = false,
        mainnetBlocked = false,
    ),
}

enum class SkaldVaultV1VaultUnlockRequiredGate(val label: String) {
    PassphrasePolicyApproved("passphrase policy approved"),
    PassphraseInputMechanismApproved("passphrase input mechanism approved"),
    PassphraseRedactionApproved("passphrase redaction approved"),
    PassphraseRetryThrottleLockoutApprovedOrReviewed(
        "passphrase retry/throttle/lockout approved or reviewed",
    ),
    KdfCalibrationAuthorizationApproved("KDF calibration authorization approved"),
    FinalKdfParametersApproved("final KDF parameters approved"),
    RuntimeRandomnessAuthorizationApproved("runtime randomness authorization approved"),
    ProviderOperationAuthorizationApproved("provider operation authorization approved"),
    ProviderSelectableAndNotDisabled("provider selectable and not disabled"),
    ProviderKatsApproved("provider KATs approved"),
    SecureStorageAuthorizationApproved("secure-storage authorization approved"),
    SecureSecretStorageAvailable("secure secret storage available"),
    SecureMetadataStorageAvailable("secure metadata storage available"),
    EncryptedLocalVaultStorageAvailable("encrypted local vault storage available"),
    StorageSafetyPreflightApproved("storage safety preflight approved"),
    StorageServiceOperationsImplementedAndApproved(
        "storage service operations implemented and approved",
    ),
    MigrationCorruptionPolicyApproved("migration/corruption policy approved"),
    ClearWipeStrategyApproved("clear/wipe strategy approved"),
    LockSessionLifecycleApproved("lock/session lifecycle approved"),
    RedactionLeakagePolicyApproved("redaction/leakage policy approved"),
    AndroidLifecyclePolicyApprovedWhereAndroidRuntimeIsUsed(
        "Android lifecycle policy approved where Android runtime is used",
    ),
    LinuxLifecyclePolicyApprovedWhereLinuxRuntimeIsUsed(
        "Linux lifecycle policy approved where Linux runtime is used",
    ),
    AndroidHardwareWrapperReviewApprovedWhereUsed(
        "Android hardware-wrapper review approved where used",
    ),
    LinuxOptionalWrapperReviewApprovedWhereUsed("Linux optional wrapper review approved where used"),
    OsKeyringNotUsedAsPrimaryStorage("OS keyring not used as primary storage"),
    PasswordManagerNotUsedForSkaldManagedPassphraseStorage(
        "password manager not used for Skald-managed passphrase storage",
    ),
    SettingsNotUsedForSecretsOrUnlockState("Settings not used for secrets or unlock state"),
    NoRawSecretDiagnostics("no raw secret diagnostics"),
    PersistenceReadinessApprovedWhereUnlockRequiresStorageAccess(
        "persistence readiness approved where unlock requires storage access",
    ),
    MainnetReleaseReviewApproved("mainnet remains disabled unless release review approves it"),
}

enum class SkaldVaultV1VaultUnlockAuthorizationBlocker(val label: String) {
    UnlockAuthorizationStillDisabled("unlock authorization remains still-disabled"),
    NoEvidenceAvailable("no unlock authorization evidence available"),
    PassphrasePolicyBlocked("passphrase policy blocks passphrase unlock"),
    PassphraseInputMechanismReviewMissing("passphrase input mechanism review missing"),
    PassphraseRedactionReviewMissing("passphrase redaction review missing"),
    PassphraseRetryThrottleLockoutReviewMissing("passphrase retry/throttle/lockout review missing"),
    KdfCalibrationAuthorizationBlocked("KDF calibration authorization is blocked"),
    FinalKdfParametersMissing("final KDF parameters are not approved"),
    RuntimeRandomnessAuthorizationBlocked("runtime randomness authorization is blocked"),
    ProviderOperationAuthorizationBlocked("provider operation authorization is blocked"),
    DisabledProviderSelected("provider selection chooses the disabled provider"),
    ProductionProviderSelectableFalse("productionProviderSelectable remains false"),
    ProviderKatApprovalMissing("provider KAT approval is missing"),
    SecureStorageAuthorizationBlocked("secure-storage authorization is blocked"),
    SecureSecretStorageUnavailable("secure secret storage is unavailable"),
    SecureMetadataStorageUnavailable("secure metadata storage is unavailable"),
    EncryptedLocalVaultStorageUnavailable("encrypted local vault storage is unavailable"),
    StorageSafetyPreflightBlocked("storage safety preflight is blocked"),
    DisabledStorageFacadeBlocked("disabled storage facade blocks vault storage access"),
    StorageServiceOperationsMissing("storage service operations are missing"),
    MigrationCorruptionBoundaryBlocked("migration/corruption boundary remains disabled"),
    ClearWipeStrategyBlocked("clear/wipe strategy remains disabled"),
    LockSessionLifecycleBlocked("lock/session lifecycle is unavailable"),
    RedactionLeakageUnsafe("redaction/leakage policy is unsafe for unlock diagnostics"),
    PersistenceReadinessBlocked("persistence readiness is blocked"),
    AndroidLifecyclePolicyMissing("Android lifecycle policy review missing"),
    LinuxLifecyclePolicyMissing("Linux lifecycle policy review missing"),
    AndroidHardwareWrapperReviewMissing("Android hardware wrapper review missing"),
    LinuxOptionalWrapperReviewMissing("Linux optional wrapper review missing"),
    OsKeyringPrimaryStorageRejected("OS keyring credential rejected as primary storage"),
    PasswordManagerPassphraseStorageRejected(
        "password-manager credential rejected for Skald-managed passphrase",
    ),
    SettingsUnlockStateRejected("Settings storage rejected for secrets or unlock state"),
    NoRawSecretDiagnosticsMissing("raw-secret diagnostics prohibition is not approved"),
    TestOnlyCredentialRejectedForProduction("test-only credential does not authorize production runtime"),
    WarningOnlyEvidenceRejected("warning-only evidence cannot authorize unlock"),
    UserConsentOverrideRejected("user consent cannot override missing hard gates"),
    MainnetUnavailable("mainnet unlock remains unavailable"),
    UnknownUnsupportedCredential("credential class is unknown or unsupported"),
    RawUnlockCandidateRejected("raw unlock candidate rejected"),
}

enum class SkaldVaultV1VaultUnlockAuthorizationWarning(val label: String) {
    EvidenceOnly("unlock authorization result is evidence only"),
    NoUnlockImplementation("no unlock implementation is added"),
    NoPassphrasePinOrBiometricAccepted("no passphrase, PIN, or biometric input is accepted"),
    NoPassphraseNormalizationOrEncoding("no passphrase normalization or encoding occurs"),
    NoKdfHkdfHmacAeadExecution("no KDF, HKDF, HMAC, or AEAD execution occurs"),
    NoRandomnessSaltNonceBytes("no randomness, salt, or nonce bytes are generated or consumed"),
    NoProviderOperations("no provider operations are called"),
    NoSecureStorageReads("no secure storage reads occur"),
    NoMetadataOrVaultReads("no metadata storage or encrypted vault storage reads occur"),
    NoKeyUnwrapOrRecordDecrypt("no key unwrap or record decrypt occurs"),
    NoActiveSessionCreated("no active session is created"),
    NoDecryptedKeyMaterial("no decrypted key material is present"),
    NoUnlockStatePersistence("no unlock state is persisted"),
    OsKeyringRejectedAsPrimary("OS keyrings are rejected as primary vault storage"),
    PasswordManagerRejectedForPassphrase(
        "password managers are rejected for Skald-managed vault passphrase storage",
    ),
    RedactedDiagnosticsOnly("diagnostics must remain redacted"),
    FutureImplementationRequiresReview("future unlock implementation requires explicit review"),
}

data class SkaldVaultV1VaultUnlockAuthorizationCapability(
    val unlockAuthorized: Boolean,
    val unlockAttemptAvailable: Boolean,
    val passphraseInputAccepted: Boolean,
    val pinInputAccepted: Boolean,
    val biometricUnlockAvailable: Boolean,
    val hardwareWrappedKeyUnlockAvailable: Boolean,
    val osKeyringUnlockAvailable: Boolean,
    val passwordManagerUnlockAvailable: Boolean,
    val kdfCalibrationAuthorized: Boolean,
    val runtimeRandomnessAuthorized: Boolean,
    val providerOperationAuthorized: Boolean,
    val secureStorageAuthorized: Boolean,
    val secureSecretStorageAvailable: Boolean,
    val secureMetadataStorageAvailable: Boolean,
    val encryptedLocalVaultStorageAvailable: Boolean,
    val storageServiceAvailable: Boolean,
    val migrationCorruptionApproved: Boolean,
    val clearWipeApproved: Boolean,
    val redactionApproved: Boolean,
    val lockSessionApproved: Boolean,
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
        val StillDisabled = SkaldVaultV1VaultUnlockAuthorizationCapability(
            unlockAuthorized = false,
            unlockAttemptAvailable = false,
            passphraseInputAccepted = false,
            pinInputAccepted = false,
            biometricUnlockAvailable = false,
            hardwareWrappedKeyUnlockAvailable = false,
            osKeyringUnlockAvailable = false,
            passwordManagerUnlockAvailable = false,
            kdfCalibrationAuthorized = false,
            runtimeRandomnessAuthorized = false,
            providerOperationAuthorized = false,
            secureStorageAuthorized = false,
            secureSecretStorageAvailable = false,
            secureMetadataStorageAvailable = false,
            encryptedLocalVaultStorageAvailable = false,
            storageServiceAvailable = false,
            migrationCorruptionApproved = false,
            clearWipeApproved = false,
            redactionApproved = false,
            lockSessionApproved = false,
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

data class SkaldVaultV1VaultUnlockPolicySummary(
    val policyId: String,
    val policyVersion: Int,
    val operationKinds: Set<SkaldVaultV1VaultUnlockOperationKind>,
    val purposes: Set<SkaldVaultV1VaultUnlockPurpose>,
    val credentialClasses: Set<SkaldVaultV1VaultUnlockCredentialClass>,
    val requiredGates: Set<SkaldVaultV1VaultUnlockRequiredGate>,
    val blockers: Set<SkaldVaultV1VaultUnlockAuthorizationBlocker>,
    val warnings: Set<SkaldVaultV1VaultUnlockAuthorizationWarning>,
    val capability: SkaldVaultV1VaultUnlockAuthorizationCapability,
    val stillDisabled: Boolean,
)

class SkaldVaultV1VaultUnlockPolicyToken internal constructor(
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
    val containsMetadataPayload: Boolean = false,
    val containsManifestStorageIndexRecordBytes: Boolean = false,
    val containsCiphertextPlaintextTagBytes: Boolean = false,
    val containsRecordIdentifier: Boolean = false,
    val containsRootOrPathText: Boolean = false,
    val containsPayload: Boolean = false,
) {
    override fun toString(): String =
        "SkaldVaultV1VaultUnlockPolicyToken(tokenId=$tokenId, redacted=true)"
}

data class SkaldVaultV1VaultUnlockAuthorizationEvidence(
    val policyId: String,
    val policyVersion: Int,
    val source: SkaldVaultV1VaultUnlockAuthorizationSource,
    val status: SkaldVaultV1VaultUnlockAuthorizationStatus,
    val decision: SkaldVaultV1VaultUnlockAuthorizationDecision,
    val operationKind: SkaldVaultV1VaultUnlockOperationKind?,
    val purpose: SkaldVaultV1VaultUnlockPurpose?,
    val credentialClass: SkaldVaultV1VaultUnlockCredentialClass?,
    val requiredGate: SkaldVaultV1VaultUnlockRequiredGate?,
    val requiredGates: Set<SkaldVaultV1VaultUnlockRequiredGate>,
    val blockers: Set<SkaldVaultV1VaultUnlockAuthorizationBlocker>,
    val warnings: Set<SkaldVaultV1VaultUnlockAuthorizationWarning>,
    val capability: SkaldVaultV1VaultUnlockAuthorizationCapability,
    val policySummary: SkaldVaultV1VaultUnlockPolicySummary,
    val policyTokenEvidence: SkaldVaultV1VaultUnlockPolicyToken,
    val passphrasePolicyEvidenceConsumed: Boolean,
    val kdfCalibrationAuthorizationEvidenceConsumed: Boolean,
    val runtimeRandomnessAuthorizationEvidenceConsumed: Boolean,
    val providerOperationAuthorizationEvidenceConsumed: Boolean,
    val secureStorageAuthorizationEvidenceConsumed: Boolean,
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
    val unlockAuthorizationBoundaryModeled: Boolean = true,
    val unlockAuthorizationStillDisabled: Boolean = true,
    val unlockAuthorizationBlocksAllOperations: Boolean = true,
    val unlockAuthorizationDoesNotAcceptPassphrases: Boolean = true,
    val unlockAuthorizationDoesNotRunKdf: Boolean = true,
    val unlockAuthorizationDoesNotReadStorage: Boolean = true,
    val unlockAuthorizationDoesNotCreateSession: Boolean = true,
    val unlockAuthorizationDoesNotEnablePersistence: Boolean = true,
    val unlockAuthorizationDoesNotEnableProviderSelection: Boolean = true,
    val unlockAuthorizationFailureVocabularyModeled: Boolean = true,
    val unlockReady: Boolean = false,
    val vaultUnlockReady: Boolean = false,
    val unlockAuthorized: Boolean = false,
    val activeSessionReady: Boolean = false,
    val passphraseAccepted: Boolean = false,
    val kdfReady: Boolean = false,
    val secureStorageReady: Boolean = false,
    val providerOperationAuthorized: Boolean = false,
    val providerCryptoReady: Boolean = false,
    val providerSelectable: Boolean = false,
    val vaultStorageAvailable: Boolean = false,
    val persistenceReady: Boolean = false,
    val vaultPersistenceReady: Boolean = false,
) {
    override fun toString(): String =
        "SkaldVaultV1VaultUnlockAuthorizationEvidence(" +
            "policyId=$policyId, " +
            "status=${status.name}, " +
            "decision=${decision.name}, " +
            "source=${source.name}, " +
            "operationKind=${operationKind?.name ?: "none"}, " +
            "purpose=${purpose?.name ?: "none"}, " +
            "credentialClass=${credentialClass?.name ?: "none"}, " +
            "requiredGate=${requiredGate?.name ?: "none"}, " +
            "capability=still-disabled, " +
            "policyTokenEvidence=$policyTokenEvidence)"
}

sealed class SkaldVaultV1VaultUnlockAuthorizationResult<out T> {
    data class Blocked<out T>(
        val value: T,
    ) : SkaldVaultV1VaultUnlockAuthorizationResult<T>() {
        override fun toString(): String =
            "SkaldVaultV1VaultUnlockAuthorizationResult.Blocked(value=$value)"
    }

    data class Rejected(
        val reason: SkaldVaultV1VaultUnlockFailureReason,
        val status: SkaldVaultV1VaultUnlockAuthorizationStatus,
        val source: SkaldVaultV1VaultUnlockAuthorizationSource,
        val safeMessage: String,
    ) : SkaldVaultV1VaultUnlockAuthorizationResult<Nothing>() {
        override fun toString(): String =
            "SkaldVaultV1VaultUnlockAuthorizationResult.Rejected(" +
                "reason=${reason.name}, " +
                "status=${status.name})"
    }
}

enum class SkaldVaultV1VaultUnlockFailureReason(val label: String) {
    EmptyEvidenceRejected("empty unlock evidence rejected"),
    ActualPassphraseRejected("actual passphrase rejected"),
    PinStringRejected("PIN string rejected"),
    BiometricResultRejected("biometric result rejected"),
    CredentialBytesRejected("credential bytes rejected"),
    AndroidKeystoreKeyRejected("Android key-wrapper key rejected"),
    OsKeyringHandleRejected("OS keyring handle rejected"),
    PasswordManagerEntryRejected("password manager entry rejected"),
    WrappedKeyBytesRejected("wrapped key bytes rejected"),
    SecureStorageHandleRejected("secure storage handle rejected"),
    ProviderHandleRejected("provider handle rejected"),
    DecryptedKeyMaterialRejected("decrypted key material rejected"),
    SessionKeyRejected("session key rejected"),
    RootRecordMetadataKeyRejected("root, record, or metadata key rejected"),
    SeedPrivateMnemonicRejected("seed, private-key, or mnemonic input rejected"),
    KdfInputOutputRejected("KDF input or output rejected"),
    SaltNonceRandomBytesRejected("salt, nonce, random, or entropy bytes rejected"),
    CiphertextPlaintextRecordBytesRejected("ciphertext, plaintext, or record bytes rejected"),
    RawManifestStorageIndexContainerBytesRejected(
        "raw manifest, storage-index, or container bytes rejected",
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
    RawUnlockInputRejected("raw unlock input rejected"),
}

class SkaldVaultV1VaultUnlockAuthorizationRequest private constructor(
    val source: SkaldVaultV1VaultUnlockAuthorizationSource,
    val operationKind: SkaldVaultV1VaultUnlockOperationKind?,
    val purpose: SkaldVaultV1VaultUnlockPurpose?,
    val credentialClass: SkaldVaultV1VaultUnlockCredentialClass?,
    val requiredGate: SkaldVaultV1VaultUnlockRequiredGate?,
    private val rawCandidate: String?,
    internal val passphrasePolicyEvidence: SkaldVaultV1VaultPassphrasePolicyEvidence?,
    internal val kdfCalibrationAuthorizationEvidence:
        SkaldVaultV1VaultKdfCalibrationAuthorizationEvidence?,
    internal val runtimeRandomnessAuthorizationEvidence:
        SkaldVaultV1VaultRuntimeRandomnessAuthorizationEvidence?,
    internal val providerOperationAuthorizationEvidence:
        SkaldVaultV1VaultProviderOperationAuthorizationEvidence?,
    internal val secureStorageAuthorizationEvidence:
        SkaldVaultV1VaultSecureStorageAuthorizationEvidence?,
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
        get() = source == SkaldVaultV1VaultUnlockAuthorizationSource.RawUnlockCandidate

    internal fun rawCandidateOrNull(): String? = rawCandidate

    override fun toString(): String =
        "SkaldVaultV1VaultUnlockAuthorizationRequest(" +
            "source=${source.name}, " +
            "operationKind=${operationKind?.name ?: "none"}, " +
            "purpose=${purpose?.name ?: "none"}, " +
            "credentialClass=${credentialClass?.name ?: "none"}, " +
            "requiredGate=${requiredGate?.name ?: "none"}, " +
            "rawCandidate=REDACTED)"

    companion object {
        fun noEvidence(): SkaldVaultV1VaultUnlockAuthorizationRequest =
            base(SkaldVaultV1VaultUnlockAuthorizationSource.NoEvidence)

        fun summary(): SkaldVaultV1VaultUnlockAuthorizationRequest =
            base(SkaldVaultV1VaultUnlockAuthorizationSource.PolicySummary)

        fun forOperationKind(
            operationKind: SkaldVaultV1VaultUnlockOperationKind,
            purpose: SkaldVaultV1VaultUnlockPurpose? = null,
            credentialClass: SkaldVaultV1VaultUnlockCredentialClass? = null,
        ): SkaldVaultV1VaultUnlockAuthorizationRequest =
            base(
                source = SkaldVaultV1VaultUnlockAuthorizationSource.OperationKind,
                operationKind = operationKind,
                purpose = purpose,
                credentialClass = credentialClass,
            )

        fun forPurpose(
            purpose: SkaldVaultV1VaultUnlockPurpose,
            credentialClass: SkaldVaultV1VaultUnlockCredentialClass? = null,
        ): SkaldVaultV1VaultUnlockAuthorizationRequest =
            base(
                source = SkaldVaultV1VaultUnlockAuthorizationSource.Purpose,
                purpose = purpose,
                credentialClass = credentialClass,
            )

        fun forCredentialClass(
            credentialClass: SkaldVaultV1VaultUnlockCredentialClass,
            operationKind: SkaldVaultV1VaultUnlockOperationKind? = null,
            purpose: SkaldVaultV1VaultUnlockPurpose? = null,
        ): SkaldVaultV1VaultUnlockAuthorizationRequest =
            base(
                source = SkaldVaultV1VaultUnlockAuthorizationSource.CredentialClass,
                operationKind = operationKind,
                purpose = purpose,
                credentialClass = credentialClass,
            )

        fun forRequiredGate(
            requiredGate: SkaldVaultV1VaultUnlockRequiredGate,
        ): SkaldVaultV1VaultUnlockAuthorizationRequest =
            base(
                source = SkaldVaultV1VaultUnlockAuthorizationSource.RequiredGate,
                requiredGate = requiredGate,
            )

        fun fromEvidence(
            passphrasePolicyEvidence: SkaldVaultV1VaultPassphrasePolicyEvidence? = null,
            kdfCalibrationAuthorizationEvidence:
                SkaldVaultV1VaultKdfCalibrationAuthorizationEvidence? = null,
            runtimeRandomnessAuthorizationEvidence:
                SkaldVaultV1VaultRuntimeRandomnessAuthorizationEvidence? = null,
            providerOperationAuthorizationEvidence:
                SkaldVaultV1VaultProviderOperationAuthorizationEvidence? = null,
            secureStorageAuthorizationEvidence:
                SkaldVaultV1VaultSecureStorageAuthorizationEvidence? = null,
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
            operationKind: SkaldVaultV1VaultUnlockOperationKind? = null,
            purpose: SkaldVaultV1VaultUnlockPurpose? = null,
            credentialClass: SkaldVaultV1VaultUnlockCredentialClass? = null,
        ): SkaldVaultV1VaultUnlockAuthorizationRequest =
            base(
                source = SkaldVaultV1VaultUnlockAuthorizationSource.ComposedTypedEvidence,
                operationKind = operationKind,
                purpose = purpose,
                credentialClass = credentialClass,
                passphrasePolicyEvidence = passphrasePolicyEvidence,
                kdfCalibrationAuthorizationEvidence = kdfCalibrationAuthorizationEvidence,
                runtimeRandomnessAuthorizationEvidence = runtimeRandomnessAuthorizationEvidence,
                providerOperationAuthorizationEvidence = providerOperationAuthorizationEvidence,
                secureStorageAuthorizationEvidence = secureStorageAuthorizationEvidence,
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

        fun rawUnlockCandidate(
            rawCandidate: String?,
        ): SkaldVaultV1VaultUnlockAuthorizationRequest =
            base(
                source = SkaldVaultV1VaultUnlockAuthorizationSource.RawUnlockCandidate,
                rawCandidate = rawCandidate,
            )

        private fun base(
            source: SkaldVaultV1VaultUnlockAuthorizationSource,
            operationKind: SkaldVaultV1VaultUnlockOperationKind? = null,
            purpose: SkaldVaultV1VaultUnlockPurpose? = null,
            credentialClass: SkaldVaultV1VaultUnlockCredentialClass? = null,
            requiredGate: SkaldVaultV1VaultUnlockRequiredGate? = null,
            rawCandidate: String? = null,
            passphrasePolicyEvidence: SkaldVaultV1VaultPassphrasePolicyEvidence? = null,
            kdfCalibrationAuthorizationEvidence:
                SkaldVaultV1VaultKdfCalibrationAuthorizationEvidence? = null,
            runtimeRandomnessAuthorizationEvidence:
                SkaldVaultV1VaultRuntimeRandomnessAuthorizationEvidence? = null,
            providerOperationAuthorizationEvidence:
                SkaldVaultV1VaultProviderOperationAuthorizationEvidence? = null,
            secureStorageAuthorizationEvidence:
                SkaldVaultV1VaultSecureStorageAuthorizationEvidence? = null,
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
        ) = SkaldVaultV1VaultUnlockAuthorizationRequest(
            source = source,
            operationKind = operationKind,
            purpose = purpose,
            credentialClass = credentialClass,
            requiredGate = requiredGate,
            rawCandidate = rawCandidate,
            passphrasePolicyEvidence = passphrasePolicyEvidence,
            kdfCalibrationAuthorizationEvidence = kdfCalibrationAuthorizationEvidence,
            runtimeRandomnessAuthorizationEvidence = runtimeRandomnessAuthorizationEvidence,
            providerOperationAuthorizationEvidence = providerOperationAuthorizationEvidence,
            secureStorageAuthorizationEvidence = secureStorageAuthorizationEvidence,
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

object SkaldVaultV1UnlockAuthorizationPolicy : SkaldVaultV1UnlockAuthorizationBoundary {
    const val POLICY_ID: String = "skald-vault-v1-unlock-authorization-boundary-v1"
    const val POLICY_VERSION: Int = 1

    fun currentPolicySummary(): SkaldVaultV1VaultUnlockPolicySummary =
        SkaldVaultV1VaultUnlockPolicySummary(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            operationKinds = SkaldVaultV1VaultUnlockOperationKind.entries.toSet(),
            purposes = SkaldVaultV1VaultUnlockPurpose.entries.toSet(),
            credentialClasses = SkaldVaultV1VaultUnlockCredentialClass.entries.toSet(),
            requiredGates = SkaldVaultV1VaultUnlockRequiredGate.entries.toSet(),
            blockers = SkaldVaultV1VaultUnlockAuthorizationBlocker.entries.toSet(),
            warnings = SkaldVaultV1VaultUnlockAuthorizationWarning.entries.toSet(),
            capability = SkaldVaultV1VaultUnlockAuthorizationCapability.StillDisabled,
            stillDisabled = true,
        )

    override fun evaluate(
        request: SkaldVaultV1VaultUnlockAuthorizationRequest,
    ): SkaldVaultV1VaultUnlockAuthorizationResult<SkaldVaultV1VaultUnlockAuthorizationEvidence> {
        if (request.source == SkaldVaultV1VaultUnlockAuthorizationSource.RawUnlockCandidate) {
            return SkaldVaultV1VaultUnlockAuthorizationResult.Rejected(
                reason = classifyRawCandidate(request.rawCandidateOrNull()),
                status = SkaldVaultV1VaultUnlockAuthorizationStatus.RawCandidateRejected,
                source = request.source,
                safeMessage = "Raw unlock authorization candidates are rejected; use typed policy evidence only.",
            )
        }

        return SkaldVaultV1VaultUnlockAuthorizationResult.Blocked(
            value = evidence(request),
        )
    }

    private fun evidence(
        request: SkaldVaultV1VaultUnlockAuthorizationRequest,
    ): SkaldVaultV1VaultUnlockAuthorizationEvidence =
        SkaldVaultV1VaultUnlockAuthorizationEvidence(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            source = request.source,
            status = statusFor(request.source),
            decision = decisionFor(request),
            operationKind = request.operationKind,
            purpose = request.purpose,
            credentialClass = request.credentialClass,
            requiredGate = request.requiredGate,
            requiredGates = requiredGatesFor(request),
            blockers = blockersFor(request),
            warnings = SkaldVaultV1VaultUnlockAuthorizationWarning.entries.toSet(),
            capability = SkaldVaultV1VaultUnlockAuthorizationCapability.StillDisabled,
            policySummary = currentPolicySummary(),
            policyTokenEvidence = SkaldVaultV1VaultUnlockPolicyToken(
                tokenId = "$POLICY_ID:current:redacted",
            ),
            passphrasePolicyEvidenceConsumed = request.passphrasePolicyEvidence != null,
            kdfCalibrationAuthorizationEvidenceConsumed =
                request.kdfCalibrationAuthorizationEvidence != null,
            runtimeRandomnessAuthorizationEvidenceConsumed =
                request.runtimeRandomnessAuthorizationEvidence != null,
            providerOperationAuthorizationEvidenceConsumed =
                request.providerOperationAuthorizationEvidence != null,
            secureStorageAuthorizationEvidenceConsumed =
                request.secureStorageAuthorizationEvidence != null,
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
        source: SkaldVaultV1VaultUnlockAuthorizationSource,
    ): SkaldVaultV1VaultUnlockAuthorizationStatus =
        when (source) {
            SkaldVaultV1VaultUnlockAuthorizationSource.NoEvidence ->
                SkaldVaultV1VaultUnlockAuthorizationStatus.NoEvidenceAvailable
            SkaldVaultV1VaultUnlockAuthorizationSource.PolicySummary ->
                SkaldVaultV1VaultUnlockAuthorizationStatus.PolicySummaryModeled
            SkaldVaultV1VaultUnlockAuthorizationSource.OperationKind ->
                SkaldVaultV1VaultUnlockAuthorizationStatus.OperationKindModeled
            SkaldVaultV1VaultUnlockAuthorizationSource.Purpose ->
                SkaldVaultV1VaultUnlockAuthorizationStatus.PurposeModeled
            SkaldVaultV1VaultUnlockAuthorizationSource.CredentialClass ->
                SkaldVaultV1VaultUnlockAuthorizationStatus.CredentialClassModeled
            SkaldVaultV1VaultUnlockAuthorizationSource.RequiredGate ->
                SkaldVaultV1VaultUnlockAuthorizationStatus.RequiredGateModeled
            SkaldVaultV1VaultUnlockAuthorizationSource.ComposedTypedEvidence ->
                SkaldVaultV1VaultUnlockAuthorizationStatus.UnlockAuthorizationBlockedStillDisabled
            SkaldVaultV1VaultUnlockAuthorizationSource.RawUnlockCandidate ->
                SkaldVaultV1VaultUnlockAuthorizationStatus.RawCandidateRejected
        }

    private fun decisionFor(
        request: SkaldVaultV1VaultUnlockAuthorizationRequest,
    ): SkaldVaultV1VaultUnlockAuthorizationDecision =
        when {
            request.operationKind == SkaldVaultV1VaultUnlockOperationKind.MainnetUnlockAttempt ||
                request.purpose == SkaldVaultV1VaultUnlockPurpose.MainnetValidation ||
                request.credentialClass?.mainnetBlocked == true ->
                SkaldVaultV1VaultUnlockAuthorizationDecision.RejectMainnet
            request.credentialClass?.unknownOrUnsupported == true ->
                SkaldVaultV1VaultUnlockAuthorizationDecision.UnsupportedFailClosed
            request.credentialClass?.testOnly == true ||
                request.operationKind == SkaldVaultV1VaultUnlockOperationKind.TestOnlyUnlockSimulation ||
                request.purpose == SkaldVaultV1VaultUnlockPurpose.TestOnlyDeterministicSimulation ->
                SkaldVaultV1VaultUnlockAuthorizationDecision.TestOnlyCredentialRejectedForProduction
            request.credentialClass?.rejected == true ||
                request.credentialClass?.pin == true ||
                request.credentialClass?.biometric == true ||
                request.credentialClass?.osKeyringPrimary == true ||
                request.credentialClass?.passwordManager == true ->
                SkaldVaultV1VaultUnlockAuthorizationDecision.RejectCredential
            request.source == SkaldVaultV1VaultUnlockAuthorizationSource.NoEvidence ||
                request.source == SkaldVaultV1VaultUnlockAuthorizationSource.ComposedTypedEvidence ->
                SkaldVaultV1VaultUnlockAuthorizationDecision.BlockedFailClosed
            else -> SkaldVaultV1VaultUnlockAuthorizationDecision.Unauthorized
        }

    private fun requiredGatesFor(
        request: SkaldVaultV1VaultUnlockAuthorizationRequest,
    ): Set<SkaldVaultV1VaultUnlockRequiredGate> =
        buildSet {
            addAll(SkaldVaultV1VaultUnlockRequiredGate.entries)
            request.requiredGate?.let(::add)
        }

    private fun blockersFor(
        request: SkaldVaultV1VaultUnlockAuthorizationRequest,
    ): Set<SkaldVaultV1VaultUnlockAuthorizationBlocker> =
        buildSet {
            add(SkaldVaultV1VaultUnlockAuthorizationBlocker.UnlockAuthorizationStillDisabled)
            add(SkaldVaultV1VaultUnlockAuthorizationBlocker.PassphrasePolicyBlocked)
            add(SkaldVaultV1VaultUnlockAuthorizationBlocker.PassphraseInputMechanismReviewMissing)
            add(SkaldVaultV1VaultUnlockAuthorizationBlocker.PassphraseRedactionReviewMissing)
            add(SkaldVaultV1VaultUnlockAuthorizationBlocker.PassphraseRetryThrottleLockoutReviewMissing)
            add(SkaldVaultV1VaultUnlockAuthorizationBlocker.KdfCalibrationAuthorizationBlocked)
            add(SkaldVaultV1VaultUnlockAuthorizationBlocker.FinalKdfParametersMissing)
            add(SkaldVaultV1VaultUnlockAuthorizationBlocker.RuntimeRandomnessAuthorizationBlocked)
            add(SkaldVaultV1VaultUnlockAuthorizationBlocker.ProviderOperationAuthorizationBlocked)
            add(SkaldVaultV1VaultUnlockAuthorizationBlocker.DisabledProviderSelected)
            add(SkaldVaultV1VaultUnlockAuthorizationBlocker.ProductionProviderSelectableFalse)
            add(SkaldVaultV1VaultUnlockAuthorizationBlocker.ProviderKatApprovalMissing)
            add(SkaldVaultV1VaultUnlockAuthorizationBlocker.SecureStorageAuthorizationBlocked)
            add(SkaldVaultV1VaultUnlockAuthorizationBlocker.SecureSecretStorageUnavailable)
            add(SkaldVaultV1VaultUnlockAuthorizationBlocker.SecureMetadataStorageUnavailable)
            add(SkaldVaultV1VaultUnlockAuthorizationBlocker.EncryptedLocalVaultStorageUnavailable)
            add(SkaldVaultV1VaultUnlockAuthorizationBlocker.StorageSafetyPreflightBlocked)
            add(SkaldVaultV1VaultUnlockAuthorizationBlocker.DisabledStorageFacadeBlocked)
            add(SkaldVaultV1VaultUnlockAuthorizationBlocker.StorageServiceOperationsMissing)
            add(SkaldVaultV1VaultUnlockAuthorizationBlocker.MigrationCorruptionBoundaryBlocked)
            add(SkaldVaultV1VaultUnlockAuthorizationBlocker.ClearWipeStrategyBlocked)
            add(SkaldVaultV1VaultUnlockAuthorizationBlocker.LockSessionLifecycleBlocked)
            add(SkaldVaultV1VaultUnlockAuthorizationBlocker.RedactionLeakageUnsafe)
            add(SkaldVaultV1VaultUnlockAuthorizationBlocker.PersistenceReadinessBlocked)
            add(SkaldVaultV1VaultUnlockAuthorizationBlocker.AndroidLifecyclePolicyMissing)
            add(SkaldVaultV1VaultUnlockAuthorizationBlocker.LinuxLifecyclePolicyMissing)
            add(SkaldVaultV1VaultUnlockAuthorizationBlocker.AndroidHardwareWrapperReviewMissing)
            add(SkaldVaultV1VaultUnlockAuthorizationBlocker.LinuxOptionalWrapperReviewMissing)
            add(SkaldVaultV1VaultUnlockAuthorizationBlocker.SettingsUnlockStateRejected)
            add(SkaldVaultV1VaultUnlockAuthorizationBlocker.NoRawSecretDiagnosticsMissing)
            add(SkaldVaultV1VaultUnlockAuthorizationBlocker.WarningOnlyEvidenceRejected)
            add(SkaldVaultV1VaultUnlockAuthorizationBlocker.UserConsentOverrideRejected)
            add(SkaldVaultV1VaultUnlockAuthorizationBlocker.MainnetUnavailable)
            if (request.source == SkaldVaultV1VaultUnlockAuthorizationSource.NoEvidence) {
                add(SkaldVaultV1VaultUnlockAuthorizationBlocker.NoEvidenceAvailable)
            }
            when (request.credentialClass) {
                SkaldVaultV1VaultUnlockCredentialClass.NoCredentialSupplied,
                SkaldVaultV1VaultUnlockCredentialClass.PassphraseNotAccepted,
                -> add(SkaldVaultV1VaultUnlockAuthorizationBlocker.PassphrasePolicyBlocked)
                SkaldVaultV1VaultUnlockCredentialClass.PinRejected,
                -> add(SkaldVaultV1VaultUnlockAuthorizationBlocker.PassphraseInputMechanismReviewMissing)
                SkaldVaultV1VaultUnlockCredentialClass.BiometricConvenienceRejectedFutureOnly,
                -> add(SkaldVaultV1VaultUnlockAuthorizationBlocker.AndroidLifecyclePolicyMissing)
                SkaldVaultV1VaultUnlockCredentialClass.AndroidHardwareWrappedKeyEvidenceOnly,
                SkaldVaultV1VaultUnlockCredentialClass.AndroidKeystoreEvidenceFutureOnly,
                -> add(SkaldVaultV1VaultUnlockAuthorizationBlocker.AndroidHardwareWrapperReviewMissing)
                SkaldVaultV1VaultUnlockCredentialClass.OsKeyringEvidenceRejectedAsPrimaryStorage,
                -> add(SkaldVaultV1VaultUnlockAuthorizationBlocker.OsKeyringPrimaryStorageRejected)
                SkaldVaultV1VaultUnlockCredentialClass.OsKeyringOptionalWrappingFutureOnly,
                -> add(SkaldVaultV1VaultUnlockAuthorizationBlocker.LinuxOptionalWrapperReviewMissing)
                SkaldVaultV1VaultUnlockCredentialClass
                    .PasswordManagerEvidenceRejectedForSkaldManagedPassphraseStorage,
                -> add(SkaldVaultV1VaultUnlockAuthorizationBlocker.PasswordManagerPassphraseStorageRejected)
                SkaldVaultV1VaultUnlockCredentialClass.TestOnlyPlaceholderCredentialRejectedForProduction,
                -> add(SkaldVaultV1VaultUnlockAuthorizationBlocker.TestOnlyCredentialRejectedForProduction)
                SkaldVaultV1VaultUnlockCredentialClass.UnknownCredential,
                SkaldVaultV1VaultUnlockCredentialClass.UnsupportedCredential,
                -> add(SkaldVaultV1VaultUnlockAuthorizationBlocker.UnknownUnsupportedCredential)
                SkaldVaultV1VaultUnlockCredentialClass.PassphrasePolicyEvidenceOnly,
                SkaldVaultV1VaultUnlockCredentialClass.SecureStorageWrappedKeyEvidenceFutureOnly,
                null,
                -> Unit
            }
            if (request.operationKind == SkaldVaultV1VaultUnlockOperationKind.MainnetUnlockAttempt ||
                request.purpose == SkaldVaultV1VaultUnlockPurpose.MainnetValidation
            ) {
                add(SkaldVaultV1VaultUnlockAuthorizationBlocker.MainnetUnavailable)
            }
        }

    private fun classifyRawCandidate(candidate: String?): SkaldVaultV1VaultUnlockFailureReason {
        val value = candidate?.trim().orEmpty()
        val lower = value.lowercase()
        if (value.isBlank()) return SkaldVaultV1VaultUnlockFailureReason.EmptyEvidenceRejected
        if (".." in value) return SkaldVaultV1VaultUnlockFailureReason.TraversalRejected
        if (value.any { it == '#' || it == '\u0000' }) {
            return SkaldVaultV1VaultUnlockFailureReason.UnsupportedCharactersRejected
        }
        if (value.startsWith("/") || Regex("""^[A-Za-z]:[\\/].*""").matches(value)) {
            return SkaldVaultV1VaultUnlockFailureReason.RawAbsoluteLocationInputRejected
        }
        if ("://" in value) return SkaldVaultV1VaultUnlockFailureReason.LinkLikeInputRejected
        if ("/" in value || "\\" in value) {
            return SkaldVaultV1VaultUnlockFailureReason.RawRelativeLocationInputRejected
        }
        if (Regex("""^[0-9a-fA-F]{64}$""").matches(value)) {
            return SkaldVaultV1VaultUnlockFailureReason.TransactionLikeEvidenceRejected
        }
        if (Regex("""^(bc1|tb1|bcrt1)[a-z0-9]{20,}$""").matches(lower)) {
            return SkaldVaultV1VaultUnlockFailureReason.BitcoinAddressLikeEvidenceRejected
        }
        if (lower.startsWith("nsec") || lower.startsWith("xprv") || lower.startsWith("tprv") ||
            Regex("""^[KL5][1-9A-HJ-NP-Za-km-z]{50,51}$""").matches(value)
        ) {
            return SkaldVaultV1VaultUnlockFailureReason.WalletMaterialRejected
        }

        return when {
            "passphrase" in lower -> SkaldVaultV1VaultUnlockFailureReason.ActualPassphraseRejected
            "pin-string" in lower || lower == "pin" -> SkaldVaultV1VaultUnlockFailureReason.PinStringRejected
            "biometric-result" in lower -> SkaldVaultV1VaultUnlockFailureReason.BiometricResultRejected
            "credential-bytes" in lower -> SkaldVaultV1VaultUnlockFailureReason.CredentialBytesRejected
            "android-keystore-key" in lower ->
                SkaldVaultV1VaultUnlockFailureReason.AndroidKeystoreKeyRejected
            "os-keyring-handle" in lower -> SkaldVaultV1VaultUnlockFailureReason.OsKeyringHandleRejected
            "password-manager-entry" in lower ->
                SkaldVaultV1VaultUnlockFailureReason.PasswordManagerEntryRejected
            "wrapped-key" in lower -> SkaldVaultV1VaultUnlockFailureReason.WrappedKeyBytesRejected
            "secure-storage-handle" in lower ->
                SkaldVaultV1VaultUnlockFailureReason.SecureStorageHandleRejected
            "provider-handle" in lower -> SkaldVaultV1VaultUnlockFailureReason.ProviderHandleRejected
            "decrypted-key-material" in lower ->
                SkaldVaultV1VaultUnlockFailureReason.DecryptedKeyMaterialRejected
            "session-key" in lower -> SkaldVaultV1VaultUnlockFailureReason.SessionKeyRejected
            "root-key" in lower || "record-key" in lower || "metadata-key" in lower ->
                SkaldVaultV1VaultUnlockFailureReason.RootRecordMetadataKeyRejected
            "seed-bytes" in lower || "private-key" in lower || "mnemonic" in lower ||
                "seed-phrase" in lower ->
                SkaldVaultV1VaultUnlockFailureReason.SeedPrivateMnemonicRejected
            "kdf-input" in lower || "kdf-output" in lower ->
                SkaldVaultV1VaultUnlockFailureReason.KdfInputOutputRejected
            "salt-bytes" in lower || "nonce-bytes" in lower || "random-bytes" in lower ||
                "entropy-bytes" in lower ->
                SkaldVaultV1VaultUnlockFailureReason.SaltNonceRandomBytesRejected
            "ciphertext" in lower || "plaintext" in lower || "aead-tag" in lower ||
                "record-bytes" in lower ->
                SkaldVaultV1VaultUnlockFailureReason.CiphertextPlaintextRecordBytesRejected
            "manifest-bytes" in lower || "storage-index-bytes" in lower || "container-bytes" in lower ->
                SkaldVaultV1VaultUnlockFailureReason.RawManifestStorageIndexContainerBytesRejected
            "bytearray" in lower -> SkaldVaultV1VaultUnlockFailureReason.ByteArrayInputRejected
            "chararray" in lower -> SkaldVaultV1VaultUnlockFailureReason.CharArrayInputRejected
            "rng-object" in lower || "random-object" in lower ->
                SkaldVaultV1VaultUnlockFailureReason.RandomObjectInputRejected
            "settings-value" in lower -> SkaldVaultV1VaultUnlockFailureReason.RawSettingsValueRejected
            "file-object" in lower || "path-object" in lower || "uri-object" in lower ||
                "url-object" in lower ->
                SkaldVaultV1VaultUnlockFailureReason.PlatformObjectLikeInputRejected
            "secret" in lower || "credential" in lower ->
                SkaldVaultV1VaultUnlockFailureReason.SecretMaterialRejected
            else -> SkaldVaultV1VaultUnlockFailureReason.RawUnlockInputRejected
        }
    }
}
