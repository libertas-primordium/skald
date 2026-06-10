package com.libertasprimordium.skald.security

interface SkaldVaultV1LockSessionLifecycleBoundary {
    fun evaluate(
        request: SkaldVaultV1VaultLockSessionRequest,
    ): SkaldVaultV1VaultLockSessionResult<SkaldVaultV1VaultLockSessionEvidence>
}

enum class SkaldVaultV1VaultLockSessionSource(val label: String) {
    NoEvidence("no lock/session lifecycle evidence supplied"),
    CurrentReadinessEvidence("typed readiness and lifecycle evidence supplied"),
    MissingPersistenceReadinessEvidence("persistence readiness evidence is missing"),
    MissingProviderEvidence("provider selection evidence is missing"),
    MissingStorageServiceEvidence("disabled storage service evidence is missing"),
    RejectedTypedEvidence("upstream typed evidence was already rejected"),
    RawLifecycleEvidenceCandidate("raw lifecycle evidence candidate"),
}

enum class SkaldVaultV1VaultLockSessionStatus(val label: String) {
    NoEvidenceAvailable("no lock/session lifecycle evidence available"),
    MissingPersistenceReadinessEvidence("persistence readiness evidence is missing"),
    MissingProviderEvidence("provider selection evidence is missing"),
    MissingStorageServiceEvidence("disabled storage service evidence is missing"),
    UpstreamEvidenceRejected("upstream typed evidence was already rejected"),
    RawLifecycleEvidenceRejected("raw lifecycle evidence is rejected"),
    UnlockBlockedStillDisabled("vault unlock remains blocked and still disabled"),
    ForcedLockRequiredStillDisabled("vault session must remain locked or forced locked"),
}

enum class SkaldVaultV1VaultLockSessionDecision(
    val unlockAvailable: Boolean,
    val activeSessionAvailable: Boolean,
    val mainnetAvailable: Boolean,
) {
    BlockedFailClosed(
        unlockAvailable = false,
        activeSessionAvailable = false,
        mainnetAvailable = false,
    ),
    LockedUnavailable(
        unlockAvailable = false,
        activeSessionAvailable = false,
        mainnetAvailable = false,
    ),
    UnlockRequestedButBlocked(
        unlockAvailable = false,
        activeSessionAvailable = false,
        mainnetAvailable = false,
    ),
    ForcedLocked(
        unlockAvailable = false,
        activeSessionAvailable = false,
        mainnetAvailable = false,
    ),
}

enum class SkaldVaultV1VaultLockState(val label: String) {
    NotInitialized("not initialized"),
    Locked("locked"),
    UnlockRequested("unlock requested"),
    UnlockBlocked("unlock blocked"),
    UnlockUnavailable("unlock unavailable"),
    ActiveSessionUnavailable("active session unavailable"),
    ActiveSessionModeledButNotUsable("active session modeled but not usable"),
    SessionExpired("session expired"),
    UserInitiatedLockRequired("user-initiated lock required"),
    BackgroundLockRequired("background lock required"),
    CloseShutdownLockRequired("close or shutdown lock required"),
    ErrorLockRequired("error lock required"),
    ProviderChangedLockRequired("provider change lock required"),
    StorageReadinessChangedLockRequired("storage readiness change lock required"),
    PlatformSecurityChangedLockRequired("platform security change lock required"),
    MainnetRequestLockBlockRequired("mainnet request lock or block required"),
    ForcedLockedFailClosed("forced locked fail closed"),
}

enum class SkaldVaultV1VaultSessionState(val label: String) {
    NoSession("no session"),
    Locked("locked"),
    UnlockRequestedButBlocked("unlock requested but blocked"),
    ActiveSessionUnavailable("active session unavailable"),
    ActiveSessionModeledButNotUsable("active session modeled but not usable"),
    Expired("expired"),
    ForcedLocked("forced locked"),
}

enum class SkaldVaultV1VaultSessionEventKind(val label: String) {
    AppStart("app start"),
    UserRequestsUnlock("user requests unlock"),
    UserCancelsUnlock("user cancels unlock"),
    UnlockAttemptBlocked("unlock attempt blocked"),
    UnlockAttemptFails("unlock attempt fails"),
    UserRequestsLock("user requests lock"),
    TimeoutReached("timeout reached"),
    AppBackgrounded("app backgrounded"),
    AppForegrounded("app foregrounded"),
    AppCloseShutdown("app close or shutdown"),
    ErrorFault("error or fault"),
    ProviderSelectionChanged("provider selection changed"),
    StorageReadinessChanged("storage readiness changed"),
    SecureStorageCapabilityChanged("secure storage capability changed"),
    PlatformRootPathEvidenceChanged("platform root or path evidence changed"),
    ScreenCaptureSecurityPolicyChanged("screen-capture security policy changed"),
    MainnetRequestAttempted("mainnet request attempted"),
    SessionStateQueried("session state queried"),
}

enum class SkaldVaultV1VaultSessionTimeoutPolicy(
    val label: String,
    val implementationAvailable: Boolean,
) {
    NotImplemented("timeout policy is not implemented", implementationAvailable = false),
    FutureReviewRequired("timeout policy requires future review", implementationAvailable = false),
}

enum class SkaldVaultV1VaultSessionLockTrigger(val label: String) {
    User("user-requested lock"),
    Timeout("timeout"),
    Background("background"),
    CloseShutdown("close or shutdown"),
    ErrorFault("error or fault"),
    ProviderChange("provider change"),
    StorageReadinessChange("storage readiness change"),
    PlatformSecurityChange("platform security change"),
    MainnetRequest("mainnet request"),
    FailClosedDefault("fail-closed default"),
}

enum class SkaldVaultV1VaultSessionRequiredGate(val label: String) {
    PersistenceReadinessApproved("persistence readiness approved"),
    ProviderSelectableApproved("selectable provider approved"),
    ProviderKatApproved("provider KATs approved"),
    FinalKdfCalibrationApproved("final KDF calibration approved"),
    SecureSecretStorageApproved("secure secret storage approved"),
    SecureMetadataStorageApproved("secure metadata storage approved"),
    StorageServiceReady("storage service ready"),
    LifecycleImplementationReviewed("lock/session lifecycle implementation reviewed"),
    RedactionLeakageReviewApproved("redaction and leakage review approved"),
    ClearWipeStrategyReviewed("clear/wipe strategy reviewed"),
    PassphraseInputPolicyReviewed("passphrase input policy reviewed"),
    PassphraseRetryThrottlingReviewed("passphrase retry/throttling policy reviewed"),
    TimeoutPolicyReviewed("timeout policy reviewed"),
    BackgroundLockPolicyReviewed("background lock policy reviewed"),
    CloseShutdownLockPolicyReviewed("close/shutdown lock policy reviewed"),
    ErrorLockPolicyReviewed("error lock policy reviewed"),
    AndroidLifecyclePolicyReviewed("Android lifecycle policy reviewed"),
    LinuxLifecyclePolicyReviewed("Linux lifecycle policy reviewed"),
    CrashRecoveryPolicyReviewed("crash recovery policy reviewed"),
    MigrationCorruptionPolicyReviewed("migration/corruption policy reviewed"),
    MainnetDisabledReleaseReview("mainnet remains disabled unless release review approves it"),
}

enum class SkaldVaultV1VaultSessionGateStatus(val label: String) {
    Missing("missing"),
    ModelOnly("model-only"),
    WarningOnly("warning-only"),
    UserConsentOnly("user-consent-only"),
    ImplementedStillDisabled("implemented but still disabled"),
    BlockedByPolicy("blocked by policy"),
    FutureReviewRequired("future review required"),
}

enum class SkaldVaultV1VaultSessionEvidenceKind(val label: String) {
    PersistenceReadiness("persistence readiness"),
    ProviderSelection("provider selection"),
    ProviderAcceptance("provider acceptance"),
    VaultReadiness("vault readiness"),
    DependencyProbe("dependency probe"),
    DisabledProviderFacade("disabled provider facade"),
    DisabledStorageServiceFacade("disabled storage service facade"),
    SecureSecretStorage("secure secret storage"),
    SecureMetadataStorage("secure metadata storage"),
    LifecycleEvent("lifecycle event"),
    TimeoutPolicy("timeout policy"),
    Redaction("redaction"),
    Clearance("clearance"),
    Mainnet("mainnet"),
}

enum class SkaldVaultV1VaultLockSessionFailureReason(val label: String) {
    NoCandidateEvidenceSupplied("no candidate lock/session evidence supplied"),
    MissingPersistenceReadinessEvidence("persistence readiness evidence is missing"),
    MissingProviderEvidence("provider evidence is missing"),
    MissingStorageServiceEvidence("disabled storage service evidence is missing"),
    UpstreamEvidenceRejected("upstream typed evidence was already rejected"),
    PersistenceReadinessBlocked("persistence readiness is blocked"),
    DisabledProviderSelection("provider selection is disabled"),
    ProductionProviderNotSelectable("production provider is not selectable"),
    DisabledStorageServiceFacade("disabled storage service facade has no storage success path"),
    SecureSecretStorageUnavailable("secure secret storage is unavailable"),
    SecureMetadataStorageUnavailable("secure metadata storage is unavailable"),
    UnapprovedRequiredGate("a required lock/session gate is unapproved"),
    WarningOnlyEvidenceRejected("warning-only evidence cannot enable unlock"),
    UserConsentOverrideRejected("user consent cannot override missing hard gates"),
    MainnetUnavailable("mainnet remains unavailable"),
    RawLifecycleEvidenceInputRejected("raw lifecycle evidence input is not accepted"),
    PassphraseInputRejected("passphrase input is not accepted"),
    PinInputRejected("PIN input is not accepted"),
    BiometricInputRejected("biometric input is not accepted"),
    RawKeyInputRejected("raw key input is not accepted"),
    ProviderKeyMaterialRejected("provider key material is rejected"),
    RawAbsolutePathInputRejected("raw absolute location input is not accepted"),
    RawRelativePathInputRejected("raw relative location input is not accepted"),
    UriLikeInputRejected("link-like input is not accepted"),
    FileOrPathObjectInputRejected("platform object-like input is not accepted"),
    SecretMaterialRejected("secret-looking lifecycle material is rejected"),
    WalletMaterialRejected("wallet or key-looking lifecycle material is rejected"),
    BitcoinAddressLikeEvidenceRejected("Bitcoin address-like lifecycle material is rejected"),
    TransactionLikeEvidenceRejected("transaction-id-like lifecycle material is rejected"),
    TraversalRejected("traversal-bearing lifecycle input is rejected"),
    EmptyEvidenceNameRejected("empty lifecycle evidence is rejected"),
    UnsupportedEvidenceNameRejected("unsupported lifecycle evidence is rejected"),
    UnlockStillDisabled("vault unlock remains disabled"),
}

enum class SkaldVaultV1VaultUnlockBlocker(val label: String) {
    MissingPersistenceReadinessEvidence("persistence readiness evidence is missing"),
    MissingProviderEvidence("provider evidence is missing"),
    MissingStorageServiceEvidence("disabled storage service evidence is missing"),
    UpstreamEvidenceRejected("upstream typed evidence was rejected"),
    BlockedPersistenceReadiness("persistence readiness remains blocked"),
    DisabledProviderSelection("provider selection returns only the disabled provider"),
    ProductionProviderNotSelectable("production provider selectable remains false"),
    DisabledProviderFacadeStillDisabled("still-disabled provider facade remains non-selectable"),
    DisabledStorageServiceFacadeStillDisabled("disabled storage service facade has no success path"),
    SecureSecretStorageUnavailable("secure secret storage remains unavailable"),
    SecureMetadataStorageUnavailable("secure metadata storage remains unavailable"),
    UnapprovedRequiredGate("at least one required lock/session gate remains unapproved"),
    WarningOnlyEvidenceRejected("warning-only evidence cannot enable unlock"),
    UserConsentOverrideRejected("user consent cannot override missing hard gates"),
    UnlockUnavailable("vault unlock remains unavailable"),
    ActiveSessionUnavailable("active session remains unavailable"),
    DecryptedKeyMaterialAbsent("decrypted key material remains absent"),
    PassphraseNotAccepted("passphrase is not accepted"),
    PassphraseNotStored("passphrase is not stored"),
    ProviderCryptoUnavailable("provider crypto remains unavailable"),
    VaultCreationBlocked("vault creation remains unavailable"),
    VaultPersistenceBlocked("vault persistence remains unavailable"),
    SettingsPersistenceMissing("Settings persistence remains unavailable"),
    StorageImplementationMissing("storage implementation remains unavailable"),
    ManifestReadWriteMissing("manifest read/write remains unavailable"),
    StorageIndexReadWriteMissing("storage index read/write remains unavailable"),
    RecordReadWriteMissing("record read/write remains unavailable"),
    AtomicWriteMissing("atomic write remains unimplemented"),
    CrashRecoveryMissing("crash recovery remains unimplemented"),
    RealPathConstructionMissing("real platform path construction remains unavailable"),
    AbsolutePathConstructionMissing("absolute path construction remains unavailable"),
    RealPathContainmentMissing("real path containment remains unverified"),
    SymlinkSafetyMissing("symlink safety remains unverified"),
    PermissionOwnershipMissing("permission and ownership checks remain unverified"),
    DurabilityMissing("durability remains unverified"),
    AntiRollbackAnchorMissing("anti-rollback anchor remains unavailable"),
    WalletSyncUnavailable("wallet sync remains unavailable"),
    MainnetDisabled("mainnet remains unavailable"),
    TimeoutRequiresLock("timeout event requires locked or lock-required state"),
    BackgroundRequiresLock("background event requires locked or lock-required state"),
    CloseShutdownRequiresLock("close/shutdown event requires locked or lock-required state"),
    ErrorRequiresLock("error event requires locked or lock-required state"),
    ProviderChangeRequiresLock("provider change requires locked state"),
    StorageReadinessChangeRequiresLock("storage readiness change requires locked state"),
    PlatformSecurityChangeRequiresLock("platform security change requires locked state"),
    MainnetRequestRequiresBlock("mainnet request requires block or lock"),
}

enum class SkaldVaultV1VaultSessionWarning(val label: String) {
    EvidenceOnly("lock/session lifecycle boundary is evidence only"),
    NoPassphraseAccepted("no passphrase is accepted"),
    NoPassphraseStored("no passphrase is stored"),
    NoKeyMaterialHeld("no key material is held"),
    NoMemoryClearanceClaim("no memory clearance capability is claimed"),
    NoUnlockOrActiveSession("no vault unlock or active session is available"),
    NoProviderApproval("production provider use is not approved"),
    NoPersistence("vault persistence remains unavailable"),
    NoFilesystemOrStorage("no filesystem or storage work is performed"),
    NoBiometricOrPlatformKeyIntegration("biometric and platform key integrations are unavailable"),
    NoOsKeyringOrPasswordManager("OS keyrings and password managers are unavailable"),
    NoMainnetApproval("mainnet is not approved"),
    WarningOnlyCannotEnableUnlock("warning-only evidence cannot enable unlock"),
    UserConsentCannotOverrideHardGates("user consent cannot override missing hard gates"),
    RedactedByDefault("passphrase, key, root, path, record, payload, and provider evidence is redacted"),
}

data class SkaldVaultV1VaultSessionCapability(
    val unlockAvailable: Boolean,
    val activeSessionAvailable: Boolean,
    val decryptedKeyMaterialPresent: Boolean,
    val passphraseAccepted: Boolean,
    val passphraseStored: Boolean,
    val providerSelectable: Boolean,
    val productionProviderSelected: Boolean,
    val providerCryptoAvailable: Boolean,
    val vaultCreationAvailable: Boolean,
    val vaultUnlockAvailable: Boolean,
    val vaultPersistenceAvailable: Boolean,
    val settingsPersistenceAvailable: Boolean,
    val storageImplementationAvailable: Boolean,
    val manifestReadWriteAvailable: Boolean,
    val storageIndexReadWriteAvailable: Boolean,
    val recordReadWriteAvailable: Boolean,
    val atomicWriteAvailable: Boolean,
    val crashRecoveryAvailable: Boolean,
    val secureSecretStorageAvailable: Boolean,
    val secureMetadataStorageAvailable: Boolean,
    val realPathConstructed: Boolean,
    val absolutePathConstructed: Boolean,
    val realPathContainmentVerified: Boolean,
    val symlinkSafetyVerified: Boolean,
    val permissionsVerified: Boolean,
    val ownershipVerified: Boolean,
    val durabilityVerified: Boolean,
    val antiRollbackAnchorAvailable: Boolean,
    val walletSyncAvailable: Boolean,
    val mainnetAvailable: Boolean,
) {
    companion object {
        val StillDisabled = SkaldVaultV1VaultSessionCapability(
            unlockAvailable = false,
            activeSessionAvailable = false,
            decryptedKeyMaterialPresent = false,
            passphraseAccepted = false,
            passphraseStored = false,
            providerSelectable = false,
            productionProviderSelected = false,
            providerCryptoAvailable = false,
            vaultCreationAvailable = false,
            vaultUnlockAvailable = false,
            vaultPersistenceAvailable = false,
            settingsPersistenceAvailable = false,
            storageImplementationAvailable = false,
            manifestReadWriteAvailable = false,
            storageIndexReadWriteAvailable = false,
            recordReadWriteAvailable = false,
            atomicWriteAvailable = false,
            crashRecoveryAvailable = false,
            secureSecretStorageAvailable = false,
            secureMetadataStorageAvailable = false,
            realPathConstructed = false,
            absolutePathConstructed = false,
            realPathContainmentVerified = false,
            symlinkSafetyVerified = false,
            permissionsVerified = false,
            ownershipVerified = false,
            durabilityVerified = false,
            antiRollbackAnchorAvailable = false,
            walletSyncAvailable = false,
            mainnetAvailable = false,
        )
    }
}

data class SkaldVaultV1VaultSessionGateEvidence(
    val gate: SkaldVaultV1VaultSessionRequiredGate,
    val status: SkaldVaultV1VaultSessionGateStatus,
    val requiredForUnlock: Boolean = true,
    val approvedForCurrentUnlock: Boolean = false,
) {
    override fun toString(): String =
        "SkaldVaultV1VaultSessionGateEvidence(" +
            "gate=$gate, " +
            "status=$status, " +
            "requiredForUnlock=$requiredForUnlock, " +
            "approvedForCurrentUnlock=false, " +
            "detail=<redacted>" +
            ")"

    companion object {
        fun modelOnly(gate: SkaldVaultV1VaultSessionRequiredGate): SkaldVaultV1VaultSessionGateEvidence =
            SkaldVaultV1VaultSessionGateEvidence(gate, SkaldVaultV1VaultSessionGateStatus.ModelOnly)

        fun warningOnly(gate: SkaldVaultV1VaultSessionRequiredGate): SkaldVaultV1VaultSessionGateEvidence =
            SkaldVaultV1VaultSessionGateEvidence(gate, SkaldVaultV1VaultSessionGateStatus.WarningOnly)

        fun userConsentOnly(gate: SkaldVaultV1VaultSessionRequiredGate): SkaldVaultV1VaultSessionGateEvidence =
            SkaldVaultV1VaultSessionGateEvidence(gate, SkaldVaultV1VaultSessionGateStatus.UserConsentOnly)
    }
}

data class SkaldVaultV1VaultSessionRedactionEvidence(
    val diagnosticsRedactedByDefault: Boolean = true,
    val passphraseMaterialExposed: Boolean = false,
    val keyMaterialExposed: Boolean = false,
    val rootOrPathEvidenceExposed: Boolean = false,
    val recordOrPayloadEvidenceExposed: Boolean = false,
)

data class SkaldVaultV1VaultSessionClearanceEvidence(
    val strategyReviewed: Boolean = false,
    val actualClearancePerformed: Boolean = false,
    val keyMaterialHeld: Boolean = false,
)

data class SkaldVaultV1VaultSessionPolicySummary(
    val timeoutPolicy: SkaldVaultV1VaultSessionTimeoutPolicy,
    val modeledLockTriggers: Set<SkaldVaultV1VaultSessionLockTrigger>,
    val requiredGates: Set<SkaldVaultV1VaultSessionRequiredGate>,
    val stillDisabled: Boolean = true,
) {
    override fun toString(): String =
        "SkaldVaultV1VaultSessionPolicySummary(" +
            "timeoutPolicy=$timeoutPolicy, " +
            "modeledLockTriggers=$modeledLockTriggers, " +
            "requiredGateCount=${requiredGates.size}, " +
            "stillDisabled=true, " +
            "details=<redacted>" +
            ")"
}

class SkaldVaultV1VaultLockSessionRequest private constructor(
    val source: SkaldVaultV1VaultLockSessionSource,
    val eventKind: SkaldVaultV1VaultSessionEventKind,
    val timeoutPolicy: SkaldVaultV1VaultSessionTimeoutPolicy,
    private val persistenceReadinessEvidence: SkaldVaultV1VaultPersistenceReadinessEvidence?,
    private val persistenceReadinessFailureReason: SkaldVaultV1VaultPersistenceReadinessFailureReason?,
    private val providerSelection: VaultCryptoProviderSelectionResult?,
    private val providerAcceptanceAssessment: ProductionProviderAcceptanceAssessment?,
    private val encryptedVaultReadinessDecision: EncryptedVaultReadinessDecision?,
    private val dependencyProbeResult: VaultCryptoDependencyProbeResult?,
    private val providerFacadeMetadata: SkaldVaultV1StillDisabledProviderFacadeMetadata?,
    private val storageServiceEvidence: SkaldVaultV1VaultStorageDisabledEvidence?,
    private val storageServiceFailureReason: SkaldVaultV1VaultStorageOperationFailureReason?,
    private val secureStorageCapability: SecureStorageCapability?,
    private val secureMetadataCapability: SecureMetadataPersistenceCapability?,
    private val gateEvidence: List<SkaldVaultV1VaultSessionGateEvidence>,
    private val rawCandidate: String?,
) {
    fun testOnlyRawCandidate(): String? = rawCandidate

    internal fun persistenceReadinessEvidenceOrNull(): SkaldVaultV1VaultPersistenceReadinessEvidence? =
        persistenceReadinessEvidence

    internal fun persistenceReadinessFailureReasonOrNull(): SkaldVaultV1VaultPersistenceReadinessFailureReason? =
        persistenceReadinessFailureReason

    internal fun providerSelectionOrNull(): VaultCryptoProviderSelectionResult? = providerSelection

    internal fun providerAcceptanceAssessmentOrNull(): ProductionProviderAcceptanceAssessment? =
        providerAcceptanceAssessment

    internal fun encryptedVaultReadinessDecisionOrNull(): EncryptedVaultReadinessDecision? =
        encryptedVaultReadinessDecision

    internal fun dependencyProbeResultOrNull(): VaultCryptoDependencyProbeResult? = dependencyProbeResult

    internal fun providerFacadeMetadataOrNull(): SkaldVaultV1StillDisabledProviderFacadeMetadata? =
        providerFacadeMetadata

    internal fun storageServiceEvidenceOrNull(): SkaldVaultV1VaultStorageDisabledEvidence? =
        storageServiceEvidence

    internal fun storageServiceFailureReasonOrNull(): SkaldVaultV1VaultStorageOperationFailureReason? =
        storageServiceFailureReason

    internal fun secureStorageCapabilityOrNull(): SecureStorageCapability? = secureStorageCapability

    internal fun secureMetadataCapabilityOrNull(): SecureMetadataPersistenceCapability? = secureMetadataCapability

    internal fun gateEvidence(): List<SkaldVaultV1VaultSessionGateEvidence> = gateEvidence

    internal fun rawCandidateOrNull(): String? = rawCandidate

    override fun toString(): String =
        "SkaldVaultV1VaultLockSessionRequest(" +
            "source=$source, " +
            "eventKind=$eventKind, " +
            "timeoutPolicy=$timeoutPolicy, " +
            "persistenceReadiness=<redacted>, " +
            "providerSelection=<redacted>, " +
            "providerAcceptance=<redacted>, " +
            "vaultReadiness=<redacted>, " +
            "dependencyProbe=<redacted>, " +
            "providerFacade=<redacted>, " +
            "storageService=<redacted>, " +
            "secureStorage=<redacted>, " +
            "secureMetadata=<redacted>, " +
            "gateEvidenceCount=${gateEvidence.size}, " +
            "rawCandidate=<redacted>" +
            ")"

    companion object {
        fun noEvidence(
            eventKind: SkaldVaultV1VaultSessionEventKind = SkaldVaultV1VaultSessionEventKind.AppStart,
        ): SkaldVaultV1VaultLockSessionRequest =
            SkaldVaultV1VaultLockSessionRequest(
                source = SkaldVaultV1VaultLockSessionSource.NoEvidence,
                eventKind = eventKind,
                timeoutPolicy = SkaldVaultV1VaultSessionTimeoutPolicy.NotImplemented,
                persistenceReadinessEvidence = null,
                persistenceReadinessFailureReason = null,
                providerSelection = null,
                providerAcceptanceAssessment = null,
                encryptedVaultReadinessDecision = null,
                dependencyProbeResult = null,
                providerFacadeMetadata = null,
                storageServiceEvidence = null,
                storageServiceFailureReason = null,
                secureStorageCapability = null,
                secureMetadataCapability = null,
                gateEvidence = emptyList(),
                rawCandidate = null,
            )

        fun fromEvidence(
            persistenceReadinessEvidence: SkaldVaultV1VaultPersistenceReadinessEvidence?,
            providerSelection: VaultCryptoProviderSelectionResult? = VaultCryptoProviderSelectionRegistry.select(),
            providerAcceptanceAssessment: ProductionProviderAcceptanceAssessment? =
                commonCurrentProductionProviderAcceptanceAssessment(),
            encryptedVaultReadinessDecision: EncryptedVaultReadinessDecision? =
                EncryptedVaultReadinessPolicy.evaluate(),
            dependencyProbeResult: VaultCryptoDependencyProbeResult? =
                VaultCryptoDependencyProbeCatalog.currentSpikeResults()
                    .single { it.candidate == VaultCryptoDependencyCandidate.TinkBouncyCastleSplit },
            providerFacadeMetadata: SkaldVaultV1StillDisabledProviderFacadeMetadata? =
                SkaldVaultV1StillDisabledProviderFacade.metadata(),
            storageServiceEvidence: SkaldVaultV1VaultStorageDisabledEvidence?,
            secureStorageCapability: SecureStorageCapability? = commonDisabledSecureStorageCapability(),
            secureMetadataCapability: SecureMetadataPersistenceCapability? = commonDisabledSecureMetadataCapability(),
            eventKind: SkaldVaultV1VaultSessionEventKind = SkaldVaultV1VaultSessionEventKind.SessionStateQueried,
            timeoutPolicy: SkaldVaultV1VaultSessionTimeoutPolicy =
                SkaldVaultV1VaultSessionTimeoutPolicy.NotImplemented,
            gateEvidence: List<SkaldVaultV1VaultSessionGateEvidence> = emptyList(),
        ): SkaldVaultV1VaultLockSessionRequest =
            SkaldVaultV1VaultLockSessionRequest(
                source = sourceFor(
                    persistenceReadinessEvidence = persistenceReadinessEvidence,
                    providerSelection = providerSelection,
                    storageServiceEvidence = storageServiceEvidence,
                ),
                eventKind = eventKind,
                timeoutPolicy = timeoutPolicy,
                persistenceReadinessEvidence = persistenceReadinessEvidence,
                persistenceReadinessFailureReason = null,
                providerSelection = providerSelection,
                providerAcceptanceAssessment = providerAcceptanceAssessment,
                encryptedVaultReadinessDecision = encryptedVaultReadinessDecision,
                dependencyProbeResult = dependencyProbeResult,
                providerFacadeMetadata = providerFacadeMetadata,
                storageServiceEvidence = storageServiceEvidence,
                storageServiceFailureReason = null,
                secureStorageCapability = secureStorageCapability,
                secureMetadataCapability = secureMetadataCapability,
                gateEvidence = gateEvidence,
                rawCandidate = null,
            )

        fun fromPersistenceReadinessResult(
            persistenceReadinessResult:
                SkaldVaultV1VaultPersistenceReadinessResult<SkaldVaultV1VaultPersistenceReadinessEvidence>,
            storageServiceEvidence: SkaldVaultV1VaultStorageDisabledEvidence?,
            providerSelection: VaultCryptoProviderSelectionResult? = VaultCryptoProviderSelectionRegistry.select(),
            providerAcceptanceAssessment: ProductionProviderAcceptanceAssessment? =
                commonCurrentProductionProviderAcceptanceAssessment(),
            encryptedVaultReadinessDecision: EncryptedVaultReadinessDecision? =
                EncryptedVaultReadinessPolicy.evaluate(),
            dependencyProbeResult: VaultCryptoDependencyProbeResult? =
                VaultCryptoDependencyProbeCatalog.currentSpikeResults()
                    .single { it.candidate == VaultCryptoDependencyCandidate.TinkBouncyCastleSplit },
            providerFacadeMetadata: SkaldVaultV1StillDisabledProviderFacadeMetadata? =
                SkaldVaultV1StillDisabledProviderFacade.metadata(),
            secureStorageCapability: SecureStorageCapability? = commonDisabledSecureStorageCapability(),
            secureMetadataCapability: SecureMetadataPersistenceCapability? = commonDisabledSecureMetadataCapability(),
            eventKind: SkaldVaultV1VaultSessionEventKind = SkaldVaultV1VaultSessionEventKind.SessionStateQueried,
            gateEvidence: List<SkaldVaultV1VaultSessionGateEvidence> = emptyList(),
        ): SkaldVaultV1VaultLockSessionRequest {
            val readinessEvidence = when (persistenceReadinessResult) {
                is SkaldVaultV1VaultPersistenceReadinessResult.Blocked -> persistenceReadinessResult.value
                is SkaldVaultV1VaultPersistenceReadinessResult.Rejected -> null
            }
            val readinessFailure = when (persistenceReadinessResult) {
                is SkaldVaultV1VaultPersistenceReadinessResult.Blocked -> null
                is SkaldVaultV1VaultPersistenceReadinessResult.Rejected -> persistenceReadinessResult.reason
            }
            return SkaldVaultV1VaultLockSessionRequest(
                source = sourceFor(
                    persistenceReadinessEvidence = readinessEvidence,
                    providerSelection = providerSelection,
                    storageServiceEvidence = storageServiceEvidence,
                    persistenceReadinessFailureReason = readinessFailure,
                ),
                eventKind = eventKind,
                timeoutPolicy = SkaldVaultV1VaultSessionTimeoutPolicy.NotImplemented,
                persistenceReadinessEvidence = readinessEvidence,
                persistenceReadinessFailureReason = readinessFailure,
                providerSelection = providerSelection,
                providerAcceptanceAssessment = providerAcceptanceAssessment,
                encryptedVaultReadinessDecision = encryptedVaultReadinessDecision,
                dependencyProbeResult = dependencyProbeResult,
                providerFacadeMetadata = providerFacadeMetadata,
                storageServiceEvidence = storageServiceEvidence,
                storageServiceFailureReason = null,
                secureStorageCapability = secureStorageCapability,
                secureMetadataCapability = secureMetadataCapability,
                gateEvidence = gateEvidence,
                rawCandidate = null,
            )
        }

        fun rawLifecycleEvidenceCandidate(rawCandidate: String?): SkaldVaultV1VaultLockSessionRequest =
            SkaldVaultV1VaultLockSessionRequest(
                source = SkaldVaultV1VaultLockSessionSource.RawLifecycleEvidenceCandidate,
                eventKind = SkaldVaultV1VaultSessionEventKind.SessionStateQueried,
                timeoutPolicy = SkaldVaultV1VaultSessionTimeoutPolicy.NotImplemented,
                persistenceReadinessEvidence = null,
                persistenceReadinessFailureReason = null,
                providerSelection = null,
                providerAcceptanceAssessment = null,
                encryptedVaultReadinessDecision = null,
                dependencyProbeResult = null,
                providerFacadeMetadata = null,
                storageServiceEvidence = null,
                storageServiceFailureReason = null,
                secureStorageCapability = null,
                secureMetadataCapability = null,
                gateEvidence = emptyList(),
                rawCandidate = rawCandidate,
            )

        private fun sourceFor(
            persistenceReadinessEvidence: SkaldVaultV1VaultPersistenceReadinessEvidence?,
            providerSelection: VaultCryptoProviderSelectionResult?,
            storageServiceEvidence: SkaldVaultV1VaultStorageDisabledEvidence?,
            persistenceReadinessFailureReason: SkaldVaultV1VaultPersistenceReadinessFailureReason? = null,
            storageServiceFailureReason: SkaldVaultV1VaultStorageOperationFailureReason? = null,
        ): SkaldVaultV1VaultLockSessionSource =
            when {
                persistenceReadinessFailureReason != null || storageServiceFailureReason != null ->
                    SkaldVaultV1VaultLockSessionSource.RejectedTypedEvidence
                persistenceReadinessEvidence == null ->
                    SkaldVaultV1VaultLockSessionSource.MissingPersistenceReadinessEvidence
                providerSelection == null -> SkaldVaultV1VaultLockSessionSource.MissingProviderEvidence
                storageServiceEvidence == null -> SkaldVaultV1VaultLockSessionSource.MissingStorageServiceEvidence
                else -> SkaldVaultV1VaultLockSessionSource.CurrentReadinessEvidence
            }
    }
}

class SkaldVaultV1VaultSessionLifecycleToken internal constructor(
    val eventKind: SkaldVaultV1VaultSessionEventKind,
    val lockState: SkaldVaultV1VaultLockState,
    val requiredGateCount: Int,
    val lockTriggers: Set<SkaldVaultV1VaultSessionLockTrigger>,
) {
    val containsPassphraseMaterial: Boolean = false
    val containsKeyMaterial: Boolean = false
    val containsProviderKeyMaterial: Boolean = false
    val containsRootText: Boolean = false
    val containsPlannedLocationText: Boolean = false
    val containsRecordIdentifier: Boolean = false
    val containsPayload: Boolean = false
    val unlockAvailable: Boolean = false
    val activeSessionAvailable: Boolean = false

    override fun toString(): String =
        "SkaldVaultV1VaultSessionLifecycleToken(" +
            "eventKind=$eventKind, " +
            "lockState=$lockState, " +
            "requiredGateCount=$requiredGateCount, " +
            "lockTriggers=$lockTriggers, " +
            "passphraseMaterial=<redacted>, " +
            "keyMaterial=<redacted>, " +
            "providerKeyMaterial=<redacted>, " +
            "root=<redacted>, " +
            "plannedLocations=<redacted>, " +
            "recordIdentifier=<redacted>, " +
            "payload=<redacted>, " +
            "unlockAvailable=false, " +
            "activeSessionAvailable=false" +
            ")"
}

class SkaldVaultV1VaultLockSessionEvidence internal constructor(
    val policyId: String,
    val policyVersion: Int,
    val status: SkaldVaultV1VaultLockSessionStatus,
    val decision: SkaldVaultV1VaultLockSessionDecision,
    val lockState: SkaldVaultV1VaultLockState,
    val sessionState: SkaldVaultV1VaultSessionState,
    val source: SkaldVaultV1VaultLockSessionSource,
    val eventKind: SkaldVaultV1VaultSessionEventKind,
    val capability: SkaldVaultV1VaultSessionCapability,
    val lifecycleMarker: SkaldVaultV1VaultSessionLifecycleToken,
    val gateEvidence: List<SkaldVaultV1VaultSessionGateEvidence>,
    val evidenceKinds: Set<SkaldVaultV1VaultSessionEvidenceKind>,
    val redactionEvidence: SkaldVaultV1VaultSessionRedactionEvidence,
    val clearanceEvidence: SkaldVaultV1VaultSessionClearanceEvidence,
    val policySummary: SkaldVaultV1VaultSessionPolicySummary,
    val blockers: Set<SkaldVaultV1VaultUnlockBlocker>,
    val warnings: Set<SkaldVaultV1VaultSessionWarning>,
    val lockSessionLifecycleBoundaryModeled: Boolean = true,
    val lockSessionLifecycleStillDisabled: Boolean = true,
    val unlockDecisionStillBlocked: Boolean = true,
    val activeSessionUnavailable: Boolean = true,
    val lockSessionBoundaryDoesNotAcceptPassphrases: Boolean = true,
    val lockSessionBoundaryDoesNotHoldKeys: Boolean = true,
    val lockSessionBoundaryDoesNotEnablePersistence: Boolean = true,
    val lockSessionBoundaryDoesNotEnableProviderSelection: Boolean = true,
    val lockSessionFailureVocabularyModeled: Boolean = true,
    val persistenceReadinessEvidenceConsumed: Boolean = false,
    val providerSelectionEvidenceConsumed: Boolean = false,
    val providerAcceptanceEvidenceConsumed: Boolean = false,
    val vaultReadinessEvidenceConsumed: Boolean = false,
    val dependencyProbeEvidenceConsumed: Boolean = false,
    val disabledProviderFacadeEvidenceConsumed: Boolean = false,
    val disabledStorageServiceEvidenceConsumed: Boolean = false,
    val secureStorageEvidenceConsumed: Boolean = false,
    val secureMetadataEvidenceConsumed: Boolean = false,
    val passphraseExposedByDefault: Boolean = false,
    val keyMaterialExposedByDefault: Boolean = false,
    val rootOrPathExposedByDefault: Boolean = false,
    val recordOrPayloadExposedByDefault: Boolean = false,
) {
    val unlockReady: Boolean = false
    val activeSessionReady: Boolean = false

    val token: SkaldVaultV1VaultSessionLifecycleToken
        get() = lifecycleMarker

    override fun toString(): String =
        "SkaldVaultV1VaultLockSessionEvidence(" +
            "policyId=$policyId, " +
            "policyVersion=$policyVersion, " +
            "status=$status, " +
            "decision=$decision, " +
            "lockState=$lockState, " +
            "sessionState=$sessionState, " +
            "source=$source, " +
            "eventKind=$eventKind, " +
            "gateCount=${gateEvidence.size}, " +
            "evidenceKinds=$evidenceKinds, " +
            "passphraseMaterial=<redacted>, " +
            "keyMaterial=<redacted>, " +
            "providerKeyMaterial=<redacted>, " +
            "root=<redacted>, " +
            "plannedLocations=<redacted>, " +
            "recordIdentifier=<redacted>, " +
            "payload=<redacted>, " +
            "unlockReady=false, " +
            "activeSessionReady=false" +
            ")"
}

sealed class SkaldVaultV1VaultLockSessionResult<out T> {
    abstract val unlockAvailable: Boolean
    abstract val activeSessionAvailable: Boolean

    data class Blocked<out T>(val value: T) : SkaldVaultV1VaultLockSessionResult<T>() {
        override val unlockAvailable: Boolean = false
        override val activeSessionAvailable: Boolean = false

        override fun toString(): String = "Blocked(value=<redacted-lock-session-lifecycle-evidence>)"
    }

    data class Rejected(
        val reason: SkaldVaultV1VaultLockSessionFailureReason,
        val status: SkaldVaultV1VaultLockSessionStatus,
        val source: SkaldVaultV1VaultLockSessionSource,
        val safeMessage: String = reason.label,
    ) : SkaldVaultV1VaultLockSessionResult<Nothing>() {
        override val unlockAvailable: Boolean = false
        override val activeSessionAvailable: Boolean = false

        override fun toString(): String =
            "Rejected(" +
                "reason=$reason, " +
                "status=$status, " +
                "source=$source, " +
                "safeMessage=$safeMessage, " +
                "rawLifecycleEvidence=<redacted>" +
                ")"
    }
}

object SkaldVaultV1LockSessionLifecyclePolicy : SkaldVaultV1LockSessionLifecycleBoundary {
    const val POLICY_ID = "skald-vault-v1-lock-session-lifecycle-boundary-v1"
    const val POLICY_VERSION = 1

    override fun evaluate(
        request: SkaldVaultV1VaultLockSessionRequest,
    ): SkaldVaultV1VaultLockSessionResult<SkaldVaultV1VaultLockSessionEvidence> =
        when (request.source) {
            SkaldVaultV1VaultLockSessionSource.RawLifecycleEvidenceCandidate ->
                rejectRawCandidate(request)
            else -> blockedEvidence(request)
        }

    fun currentPolicySummary(): SkaldVaultV1VaultSessionPolicySummary =
        SkaldVaultV1VaultSessionPolicySummary(
            timeoutPolicy = SkaldVaultV1VaultSessionTimeoutPolicy.NotImplemented,
            modeledLockTriggers = SkaldVaultV1VaultSessionLockTrigger.entries.toSet(),
            requiredGates = SkaldVaultV1VaultSessionRequiredGate.entries.toSet(),
        )

    private fun blockedEvidence(
        request: SkaldVaultV1VaultLockSessionRequest,
    ): SkaldVaultV1VaultLockSessionResult.Blocked<SkaldVaultV1VaultLockSessionEvidence> {
        val requiredGates = requiredGateEvidence(request)
        val lockState = lockStateFor(request)
        val lifecycleMarker = SkaldVaultV1VaultSessionLifecycleToken(
            eventKind = request.eventKind,
            lockState = lockState,
            requiredGateCount = requiredGates.size,
            lockTriggers = lockTriggersFor(request.eventKind),
        )
        return SkaldVaultV1VaultLockSessionResult.Blocked(
            SkaldVaultV1VaultLockSessionEvidence(
                POLICY_ID,
                POLICY_VERSION,
                statusFor(request.source, request.eventKind),
                decisionFor(request.eventKind),
                lockState,
                sessionStateFor(lockState),
                request.source,
                request.eventKind,
                SkaldVaultV1VaultSessionCapability.StillDisabled,
                lifecycleMarker,
                requiredGates,
                evidenceKindsFor(request),
                SkaldVaultV1VaultSessionRedactionEvidence(),
                SkaldVaultV1VaultSessionClearanceEvidence(),
                currentPolicySummary(),
                blockersFor(request, requiredGates),
                SkaldVaultV1VaultSessionWarning.entries.toSet(),
                persistenceReadinessEvidenceConsumed = request.persistenceReadinessEvidenceOrNull() != null,
                providerSelectionEvidenceConsumed = request.providerSelectionOrNull() != null,
                providerAcceptanceEvidenceConsumed = request.providerAcceptanceAssessmentOrNull() != null,
                vaultReadinessEvidenceConsumed = request.encryptedVaultReadinessDecisionOrNull() != null,
                dependencyProbeEvidenceConsumed = request.dependencyProbeResultOrNull() != null,
                disabledProviderFacadeEvidenceConsumed = request.providerFacadeMetadataOrNull() != null,
                disabledStorageServiceEvidenceConsumed = request.storageServiceEvidenceOrNull() != null,
                secureStorageEvidenceConsumed = request.secureStorageCapabilityOrNull() != null,
                secureMetadataEvidenceConsumed = request.secureMetadataCapabilityOrNull() != null,
            ),
        )
    }

    private fun rejectRawCandidate(
        request: SkaldVaultV1VaultLockSessionRequest,
    ): SkaldVaultV1VaultLockSessionResult.Rejected =
        SkaldVaultV1VaultLockSessionResult.Rejected(
            reason = classifyLifecycleInput(request.rawCandidateOrNull()),
            status = SkaldVaultV1VaultLockSessionStatus.RawLifecycleEvidenceRejected,
            source = request.source,
        )

    private fun statusFor(
        source: SkaldVaultV1VaultLockSessionSource,
        eventKind: SkaldVaultV1VaultSessionEventKind,
    ): SkaldVaultV1VaultLockSessionStatus =
        when (source) {
            SkaldVaultV1VaultLockSessionSource.NoEvidence ->
                SkaldVaultV1VaultLockSessionStatus.NoEvidenceAvailable
            SkaldVaultV1VaultLockSessionSource.MissingPersistenceReadinessEvidence ->
                SkaldVaultV1VaultLockSessionStatus.MissingPersistenceReadinessEvidence
            SkaldVaultV1VaultLockSessionSource.MissingProviderEvidence ->
                SkaldVaultV1VaultLockSessionStatus.MissingProviderEvidence
            SkaldVaultV1VaultLockSessionSource.MissingStorageServiceEvidence ->
                SkaldVaultV1VaultLockSessionStatus.MissingStorageServiceEvidence
            SkaldVaultV1VaultLockSessionSource.RejectedTypedEvidence ->
                SkaldVaultV1VaultLockSessionStatus.UpstreamEvidenceRejected
            SkaldVaultV1VaultLockSessionSource.RawLifecycleEvidenceCandidate ->
                SkaldVaultV1VaultLockSessionStatus.RawLifecycleEvidenceRejected
            SkaldVaultV1VaultLockSessionSource.CurrentReadinessEvidence ->
                if (eventKind in forcedLockEvents) {
                    SkaldVaultV1VaultLockSessionStatus.ForcedLockRequiredStillDisabled
                } else {
                    SkaldVaultV1VaultLockSessionStatus.UnlockBlockedStillDisabled
                }
        }

    private fun decisionFor(eventKind: SkaldVaultV1VaultSessionEventKind): SkaldVaultV1VaultLockSessionDecision =
        when (eventKind) {
            SkaldVaultV1VaultSessionEventKind.UserRequestsUnlock ->
                SkaldVaultV1VaultLockSessionDecision.UnlockRequestedButBlocked
            SkaldVaultV1VaultSessionEventKind.UserRequestsLock,
            SkaldVaultV1VaultSessionEventKind.UserCancelsUnlock,
            SkaldVaultV1VaultSessionEventKind.AppStart,
            SkaldVaultV1VaultSessionEventKind.AppForegrounded,
            SkaldVaultV1VaultSessionEventKind.SessionStateQueried,
            -> SkaldVaultV1VaultLockSessionDecision.LockedUnavailable
            else -> SkaldVaultV1VaultLockSessionDecision.ForcedLocked
        }

    private fun lockStateFor(request: SkaldVaultV1VaultLockSessionRequest): SkaldVaultV1VaultLockState =
        when (request.eventKind) {
            SkaldVaultV1VaultSessionEventKind.AppStart -> SkaldVaultV1VaultLockState.Locked
            SkaldVaultV1VaultSessionEventKind.UserRequestsUnlock -> SkaldVaultV1VaultLockState.UnlockBlocked
            SkaldVaultV1VaultSessionEventKind.UserCancelsUnlock -> SkaldVaultV1VaultLockState.Locked
            SkaldVaultV1VaultSessionEventKind.UnlockAttemptBlocked,
            SkaldVaultV1VaultSessionEventKind.UnlockAttemptFails,
            -> SkaldVaultV1VaultLockState.UnlockBlocked
            SkaldVaultV1VaultSessionEventKind.UserRequestsLock -> SkaldVaultV1VaultLockState.UserInitiatedLockRequired
            SkaldVaultV1VaultSessionEventKind.TimeoutReached -> SkaldVaultV1VaultLockState.SessionExpired
            SkaldVaultV1VaultSessionEventKind.AppBackgrounded -> SkaldVaultV1VaultLockState.BackgroundLockRequired
            SkaldVaultV1VaultSessionEventKind.AppForegrounded -> SkaldVaultV1VaultLockState.Locked
            SkaldVaultV1VaultSessionEventKind.AppCloseShutdown -> SkaldVaultV1VaultLockState.CloseShutdownLockRequired
            SkaldVaultV1VaultSessionEventKind.ErrorFault -> SkaldVaultV1VaultLockState.ErrorLockRequired
            SkaldVaultV1VaultSessionEventKind.ProviderSelectionChanged ->
                SkaldVaultV1VaultLockState.ProviderChangedLockRequired
            SkaldVaultV1VaultSessionEventKind.StorageReadinessChanged ->
                SkaldVaultV1VaultLockState.StorageReadinessChangedLockRequired
            SkaldVaultV1VaultSessionEventKind.SecureStorageCapabilityChanged,
            SkaldVaultV1VaultSessionEventKind.PlatformRootPathEvidenceChanged,
            SkaldVaultV1VaultSessionEventKind.ScreenCaptureSecurityPolicyChanged,
            -> SkaldVaultV1VaultLockState.PlatformSecurityChangedLockRequired
            SkaldVaultV1VaultSessionEventKind.MainnetRequestAttempted ->
                SkaldVaultV1VaultLockState.MainnetRequestLockBlockRequired
            SkaldVaultV1VaultSessionEventKind.SessionStateQueried ->
                if (request.persistenceReadinessEvidenceOrNull() == null) {
                    SkaldVaultV1VaultLockState.ForcedLockedFailClosed
                } else {
                    SkaldVaultV1VaultLockState.UnlockUnavailable
                }
        }

    private fun sessionStateFor(lockState: SkaldVaultV1VaultLockState): SkaldVaultV1VaultSessionState =
        when (lockState) {
            SkaldVaultV1VaultLockState.NotInitialized -> SkaldVaultV1VaultSessionState.NoSession
            SkaldVaultV1VaultLockState.Locked,
            SkaldVaultV1VaultLockState.UserInitiatedLockRequired,
            -> SkaldVaultV1VaultSessionState.Locked
            SkaldVaultV1VaultLockState.UnlockRequested,
            SkaldVaultV1VaultLockState.UnlockBlocked,
            SkaldVaultV1VaultLockState.UnlockUnavailable,
            -> SkaldVaultV1VaultSessionState.UnlockRequestedButBlocked
            SkaldVaultV1VaultLockState.ActiveSessionUnavailable ->
                SkaldVaultV1VaultSessionState.ActiveSessionUnavailable
            SkaldVaultV1VaultLockState.ActiveSessionModeledButNotUsable ->
                SkaldVaultV1VaultSessionState.ActiveSessionModeledButNotUsable
            SkaldVaultV1VaultLockState.SessionExpired -> SkaldVaultV1VaultSessionState.Expired
            SkaldVaultV1VaultLockState.BackgroundLockRequired,
            SkaldVaultV1VaultLockState.CloseShutdownLockRequired,
            SkaldVaultV1VaultLockState.ErrorLockRequired,
            SkaldVaultV1VaultLockState.ProviderChangedLockRequired,
            SkaldVaultV1VaultLockState.StorageReadinessChangedLockRequired,
            SkaldVaultV1VaultLockState.PlatformSecurityChangedLockRequired,
            SkaldVaultV1VaultLockState.MainnetRequestLockBlockRequired,
            SkaldVaultV1VaultLockState.ForcedLockedFailClosed,
            -> SkaldVaultV1VaultSessionState.ForcedLocked
        }

    private fun lockTriggersFor(
        eventKind: SkaldVaultV1VaultSessionEventKind,
    ): Set<SkaldVaultV1VaultSessionLockTrigger> =
        when (eventKind) {
            SkaldVaultV1VaultSessionEventKind.UserRequestsLock ->
                setOf(SkaldVaultV1VaultSessionLockTrigger.User)
            SkaldVaultV1VaultSessionEventKind.TimeoutReached ->
                setOf(SkaldVaultV1VaultSessionLockTrigger.Timeout)
            SkaldVaultV1VaultSessionEventKind.AppBackgrounded ->
                setOf(SkaldVaultV1VaultSessionLockTrigger.Background)
            SkaldVaultV1VaultSessionEventKind.AppCloseShutdown ->
                setOf(SkaldVaultV1VaultSessionLockTrigger.CloseShutdown)
            SkaldVaultV1VaultSessionEventKind.ErrorFault ->
                setOf(SkaldVaultV1VaultSessionLockTrigger.ErrorFault)
            SkaldVaultV1VaultSessionEventKind.ProviderSelectionChanged ->
                setOf(SkaldVaultV1VaultSessionLockTrigger.ProviderChange)
            SkaldVaultV1VaultSessionEventKind.StorageReadinessChanged ->
                setOf(SkaldVaultV1VaultSessionLockTrigger.StorageReadinessChange)
            SkaldVaultV1VaultSessionEventKind.SecureStorageCapabilityChanged,
            SkaldVaultV1VaultSessionEventKind.PlatformRootPathEvidenceChanged,
            SkaldVaultV1VaultSessionEventKind.ScreenCaptureSecurityPolicyChanged,
            -> setOf(SkaldVaultV1VaultSessionLockTrigger.PlatformSecurityChange)
            SkaldVaultV1VaultSessionEventKind.MainnetRequestAttempted ->
                setOf(SkaldVaultV1VaultSessionLockTrigger.MainnetRequest)
            else -> setOf(SkaldVaultV1VaultSessionLockTrigger.FailClosedDefault)
        }

    private fun requiredGateEvidence(
        request: SkaldVaultV1VaultLockSessionRequest,
    ): List<SkaldVaultV1VaultSessionGateEvidence> {
        val suppliedByGate = request.gateEvidence().associateBy { it.gate }
        return SkaldVaultV1VaultSessionRequiredGate.entries.map { gate ->
            val supplied = suppliedByGate[gate]
            SkaldVaultV1VaultSessionGateEvidence(
                gate = gate,
                status = supplied?.status ?: defaultGateStatus(gate, request),
                requiredForUnlock = supplied?.requiredForUnlock ?: true,
                approvedForCurrentUnlock = false,
            )
        }
    }

    private fun defaultGateStatus(
        gate: SkaldVaultV1VaultSessionRequiredGate,
        request: SkaldVaultV1VaultLockSessionRequest,
    ): SkaldVaultV1VaultSessionGateStatus =
        when (gate) {
            SkaldVaultV1VaultSessionRequiredGate.PersistenceReadinessApproved ->
                if (request.persistenceReadinessEvidenceOrNull() != null) {
                    SkaldVaultV1VaultSessionGateStatus.ImplementedStillDisabled
                } else {
                    SkaldVaultV1VaultSessionGateStatus.Missing
                }
            SkaldVaultV1VaultSessionRequiredGate.StorageServiceReady ->
                if (request.storageServiceEvidenceOrNull() != null) {
                    SkaldVaultV1VaultSessionGateStatus.ImplementedStillDisabled
                } else {
                    SkaldVaultV1VaultSessionGateStatus.Missing
                }
            SkaldVaultV1VaultSessionRequiredGate.ProviderSelectableApproved,
            SkaldVaultV1VaultSessionRequiredGate.ProviderKatApproved,
            SkaldVaultV1VaultSessionRequiredGate.FinalKdfCalibrationApproved,
            SkaldVaultV1VaultSessionRequiredGate.SecureSecretStorageApproved,
            SkaldVaultV1VaultSessionRequiredGate.SecureMetadataStorageApproved,
            SkaldVaultV1VaultSessionRequiredGate.MainnetDisabledReleaseReview,
            -> if (request.providerSelectionOrNull() != null ||
                request.secureStorageCapabilityOrNull() != null ||
                request.secureMetadataCapabilityOrNull() != null
            ) {
                SkaldVaultV1VaultSessionGateStatus.BlockedByPolicy
            } else {
                SkaldVaultV1VaultSessionGateStatus.Missing
            }
            else -> SkaldVaultV1VaultSessionGateStatus.FutureReviewRequired
        }

    private fun blockersFor(
        request: SkaldVaultV1VaultLockSessionRequest,
        requiredGates: List<SkaldVaultV1VaultSessionGateEvidence>,
    ): Set<SkaldVaultV1VaultUnlockBlocker> =
        buildSet {
            addAll(disabledCapabilityBlockers())
            when (request.source) {
                SkaldVaultV1VaultLockSessionSource.NoEvidence -> {
                    add(SkaldVaultV1VaultUnlockBlocker.MissingPersistenceReadinessEvidence)
                    add(SkaldVaultV1VaultUnlockBlocker.MissingProviderEvidence)
                    add(SkaldVaultV1VaultUnlockBlocker.MissingStorageServiceEvidence)
                }
                SkaldVaultV1VaultLockSessionSource.MissingPersistenceReadinessEvidence ->
                    add(SkaldVaultV1VaultUnlockBlocker.MissingPersistenceReadinessEvidence)
                SkaldVaultV1VaultLockSessionSource.MissingProviderEvidence ->
                    add(SkaldVaultV1VaultUnlockBlocker.MissingProviderEvidence)
                SkaldVaultV1VaultLockSessionSource.MissingStorageServiceEvidence ->
                    add(SkaldVaultV1VaultUnlockBlocker.MissingStorageServiceEvidence)
                SkaldVaultV1VaultLockSessionSource.RejectedTypedEvidence ->
                    add(SkaldVaultV1VaultUnlockBlocker.UpstreamEvidenceRejected)
                SkaldVaultV1VaultLockSessionSource.CurrentReadinessEvidence,
                SkaldVaultV1VaultLockSessionSource.RawLifecycleEvidenceCandidate,
                -> Unit
            }
            if (request.persistenceReadinessFailureReasonOrNull() != null ||
                request.storageServiceFailureReasonOrNull() != null
            ) {
                add(SkaldVaultV1VaultUnlockBlocker.UpstreamEvidenceRejected)
            }
            val persistenceReadiness = request.persistenceReadinessEvidenceOrNull()
            if (persistenceReadiness == null || !persistenceReadiness.readyForPersistence) {
                add(SkaldVaultV1VaultUnlockBlocker.BlockedPersistenceReadiness)
            }
            val providerSelection = request.providerSelectionOrNull()
            if (providerSelection == null || providerSelection.selectedProviderIsDisabled) {
                add(SkaldVaultV1VaultUnlockBlocker.DisabledProviderSelection)
            }
            if (providerSelection?.productionProviderSelectable != true) {
                add(SkaldVaultV1VaultUnlockBlocker.ProductionProviderNotSelectable)
            }
            val providerAcceptance = request.providerAcceptanceAssessmentOrNull()
            if (providerAcceptance?.productionProviderSelectable != true ||
                providerAcceptance?.productionPersistenceAllowed != true
            ) {
                add(SkaldVaultV1VaultUnlockBlocker.ProductionProviderNotSelectable)
            }
            val readiness = request.encryptedVaultReadinessDecisionOrNull()
            if (readiness?.canEnableProductionPersistence != true) {
                add(SkaldVaultV1VaultUnlockBlocker.BlockedPersistenceReadiness)
            }
            val dependency = request.dependencyProbeResultOrNull()
            if (dependency?.readyForVaultImplementation != true) {
                add(SkaldVaultV1VaultUnlockBlocker.ProviderCryptoUnavailable)
            }
            val providerFacade = request.providerFacadeMetadataOrNull()
            if (providerFacade?.status?.selectable != true || providerFacade?.status?.operationsEnabled != true) {
                add(SkaldVaultV1VaultUnlockBlocker.DisabledProviderFacadeStillDisabled)
            }
            val storageService = request.storageServiceEvidenceOrNull()
            if (storageService == null || storageService.noOperationReturnsSuccess) {
                add(SkaldVaultV1VaultUnlockBlocker.DisabledStorageServiceFacadeStillDisabled)
            }
            val secureStorage = request.secureStorageCapabilityOrNull()
            if (secureStorage?.status?.availableForSecretMaterial != true ||
                secureStorage?.canStoreSecrets != true ||
                secureStorage?.canReadSecrets != true
            ) {
                add(SkaldVaultV1VaultUnlockBlocker.SecureSecretStorageUnavailable)
            }
            val secureMetadata = request.secureMetadataCapabilityOrNull()
            if (secureMetadata?.status?.availableForSensitiveMetadata != true ||
                secureMetadata?.canStoreMetadata != true ||
                secureMetadata?.canReadMetadata != true
            ) {
                add(SkaldVaultV1VaultUnlockBlocker.SecureMetadataStorageUnavailable)
            }
            if (requiredGates.any { it.requiredForUnlock && !it.approvedForCurrentUnlock }) {
                add(SkaldVaultV1VaultUnlockBlocker.UnapprovedRequiredGate)
            }
            if (requiredGates.any { it.status == SkaldVaultV1VaultSessionGateStatus.WarningOnly }) {
                add(SkaldVaultV1VaultUnlockBlocker.WarningOnlyEvidenceRejected)
            }
            if (requiredGates.any { it.status == SkaldVaultV1VaultSessionGateStatus.UserConsentOnly }) {
                add(SkaldVaultV1VaultUnlockBlocker.UserConsentOverrideRejected)
            }
            addAll(eventBlockersFor(request.eventKind))
        }

    private fun disabledCapabilityBlockers(): Set<SkaldVaultV1VaultUnlockBlocker> =
        setOf(
            SkaldVaultV1VaultUnlockBlocker.UnlockUnavailable,
            SkaldVaultV1VaultUnlockBlocker.ActiveSessionUnavailable,
            SkaldVaultV1VaultUnlockBlocker.DecryptedKeyMaterialAbsent,
            SkaldVaultV1VaultUnlockBlocker.PassphraseNotAccepted,
            SkaldVaultV1VaultUnlockBlocker.PassphraseNotStored,
            SkaldVaultV1VaultUnlockBlocker.ProviderCryptoUnavailable,
            SkaldVaultV1VaultUnlockBlocker.VaultCreationBlocked,
            SkaldVaultV1VaultUnlockBlocker.VaultPersistenceBlocked,
            SkaldVaultV1VaultUnlockBlocker.SettingsPersistenceMissing,
            SkaldVaultV1VaultUnlockBlocker.StorageImplementationMissing,
            SkaldVaultV1VaultUnlockBlocker.ManifestReadWriteMissing,
            SkaldVaultV1VaultUnlockBlocker.StorageIndexReadWriteMissing,
            SkaldVaultV1VaultUnlockBlocker.RecordReadWriteMissing,
            SkaldVaultV1VaultUnlockBlocker.AtomicWriteMissing,
            SkaldVaultV1VaultUnlockBlocker.CrashRecoveryMissing,
            SkaldVaultV1VaultUnlockBlocker.RealPathConstructionMissing,
            SkaldVaultV1VaultUnlockBlocker.AbsolutePathConstructionMissing,
            SkaldVaultV1VaultUnlockBlocker.RealPathContainmentMissing,
            SkaldVaultV1VaultUnlockBlocker.SymlinkSafetyMissing,
            SkaldVaultV1VaultUnlockBlocker.PermissionOwnershipMissing,
            SkaldVaultV1VaultUnlockBlocker.DurabilityMissing,
            SkaldVaultV1VaultUnlockBlocker.AntiRollbackAnchorMissing,
            SkaldVaultV1VaultUnlockBlocker.WalletSyncUnavailable,
            SkaldVaultV1VaultUnlockBlocker.MainnetDisabled,
        )

    private fun eventBlockersFor(
        eventKind: SkaldVaultV1VaultSessionEventKind,
    ): Set<SkaldVaultV1VaultUnlockBlocker> =
        when (eventKind) {
            SkaldVaultV1VaultSessionEventKind.TimeoutReached ->
                setOf(SkaldVaultV1VaultUnlockBlocker.TimeoutRequiresLock)
            SkaldVaultV1VaultSessionEventKind.AppBackgrounded ->
                setOf(SkaldVaultV1VaultUnlockBlocker.BackgroundRequiresLock)
            SkaldVaultV1VaultSessionEventKind.AppCloseShutdown ->
                setOf(SkaldVaultV1VaultUnlockBlocker.CloseShutdownRequiresLock)
            SkaldVaultV1VaultSessionEventKind.ErrorFault ->
                setOf(SkaldVaultV1VaultUnlockBlocker.ErrorRequiresLock)
            SkaldVaultV1VaultSessionEventKind.ProviderSelectionChanged ->
                setOf(SkaldVaultV1VaultUnlockBlocker.ProviderChangeRequiresLock)
            SkaldVaultV1VaultSessionEventKind.StorageReadinessChanged ->
                setOf(SkaldVaultV1VaultUnlockBlocker.StorageReadinessChangeRequiresLock)
            SkaldVaultV1VaultSessionEventKind.SecureStorageCapabilityChanged,
            SkaldVaultV1VaultSessionEventKind.PlatformRootPathEvidenceChanged,
            SkaldVaultV1VaultSessionEventKind.ScreenCaptureSecurityPolicyChanged,
            -> setOf(SkaldVaultV1VaultUnlockBlocker.PlatformSecurityChangeRequiresLock)
            SkaldVaultV1VaultSessionEventKind.MainnetRequestAttempted ->
                setOf(SkaldVaultV1VaultUnlockBlocker.MainnetRequestRequiresBlock)
            else -> emptySet()
        }

    private fun evidenceKindsFor(
        request: SkaldVaultV1VaultLockSessionRequest,
    ): Set<SkaldVaultV1VaultSessionEvidenceKind> =
        buildSet {
            add(SkaldVaultV1VaultSessionEvidenceKind.LifecycleEvent)
            add(SkaldVaultV1VaultSessionEvidenceKind.TimeoutPolicy)
            add(SkaldVaultV1VaultSessionEvidenceKind.Redaction)
            add(SkaldVaultV1VaultSessionEvidenceKind.Clearance)
            add(SkaldVaultV1VaultSessionEvidenceKind.Mainnet)
            if (request.persistenceReadinessEvidenceOrNull() != null) {
                add(SkaldVaultV1VaultSessionEvidenceKind.PersistenceReadiness)
            }
            if (request.providerSelectionOrNull() != null) {
                add(SkaldVaultV1VaultSessionEvidenceKind.ProviderSelection)
            }
            if (request.providerAcceptanceAssessmentOrNull() != null) {
                add(SkaldVaultV1VaultSessionEvidenceKind.ProviderAcceptance)
            }
            if (request.encryptedVaultReadinessDecisionOrNull() != null) {
                add(SkaldVaultV1VaultSessionEvidenceKind.VaultReadiness)
            }
            if (request.dependencyProbeResultOrNull() != null) {
                add(SkaldVaultV1VaultSessionEvidenceKind.DependencyProbe)
            }
            if (request.providerFacadeMetadataOrNull() != null) {
                add(SkaldVaultV1VaultSessionEvidenceKind.DisabledProviderFacade)
            }
            if (request.storageServiceEvidenceOrNull() != null) {
                add(SkaldVaultV1VaultSessionEvidenceKind.DisabledStorageServiceFacade)
            }
            if (request.secureStorageCapabilityOrNull() != null) {
                add(SkaldVaultV1VaultSessionEvidenceKind.SecureSecretStorage)
            }
            if (request.secureMetadataCapabilityOrNull() != null) {
                add(SkaldVaultV1VaultSessionEvidenceKind.SecureMetadataStorage)
            }
        }

    private fun classifyLifecycleInput(rawCandidate: String?): SkaldVaultV1VaultLockSessionFailureReason {
        val raw = rawCandidate ?: return SkaldVaultV1VaultLockSessionFailureReason.EmptyEvidenceNameRejected
        val candidate = raw.trim()
        if (candidate.isEmpty()) {
            return SkaldVaultV1VaultLockSessionFailureReason.EmptyEvidenceNameRejected
        }
        val lower = candidate.lowercase()
        return when {
            "passphrase" in lower ->
                SkaldVaultV1VaultLockSessionFailureReason.PassphraseInputRejected
            lower == "pin" || lower.startsWith("pin-") || "-pin-" in lower ->
                SkaldVaultV1VaultLockSessionFailureReason.PinInputRejected
            "biometric" in lower ->
                SkaldVaultV1VaultLockSessionFailureReason.BiometricInputRejected
            "provider-key" in lower ->
                SkaldVaultV1VaultLockSessionFailureReason.ProviderKeyMaterialRejected
            "raw-key" in lower || "key-bytes" in lower || "key-material" in lower ->
                SkaldVaultV1VaultLockSessionFailureReason.RawKeyInputRejected
            looksLikeAbsoluteLocation(candidate) ->
                SkaldVaultV1VaultLockSessionFailureReason.RawAbsolutePathInputRejected
            looksLikeLink(candidate) ->
                SkaldVaultV1VaultLockSessionFailureReason.UriLikeInputRejected
            "/" in candidate || "\\" in candidate ->
                SkaldVaultV1VaultLockSessionFailureReason.RawRelativePathInputRejected
            "file-object" in lower || "path-object" in lower || lower.endsWith(".path") ->
                SkaldVaultV1VaultLockSessionFailureReason.FileOrPathObjectInputRejected
            ".." in candidate || "%2e" in lower ->
                SkaldVaultV1VaultLockSessionFailureReason.TraversalRejected
            "password" in lower || "token" in lower || "credential" in lower ||
                "secret" in lower || "user:pass" in lower ->
                SkaldVaultV1VaultLockSessionFailureReason.SecretMaterialRejected
            looksLikeBitcoinAddress(candidate) ->
                SkaldVaultV1VaultLockSessionFailureReason.BitcoinAddressLikeEvidenceRejected
            looksLikeWalletMaterial(candidate) ->
                SkaldVaultV1VaultLockSessionFailureReason.WalletMaterialRejected
            candidate.length == 64 && candidate.all { it.isHexDigit() } ->
                SkaldVaultV1VaultLockSessionFailureReason.TransactionLikeEvidenceRejected
            !candidate.all { it.isSafeEvidenceChar() } ->
                SkaldVaultV1VaultLockSessionFailureReason.UnsupportedEvidenceNameRejected
            else -> SkaldVaultV1VaultLockSessionFailureReason.RawLifecycleEvidenceInputRejected
        }
    }

    private fun looksLikeAbsoluteLocation(value: String): Boolean =
        value.startsWith("/") ||
            value.startsWith("\\\\") ||
            (value.length >= 3 && value[1] == ':' && value[2] == '\\')

    private fun looksLikeLink(value: String): Boolean {
        val lower = value.lowercase()
        return lower.startsWith("file:") ||
            lower.startsWith("content:") ||
            lower.startsWith("http:") ||
            lower.startsWith("https:")
    }

    private fun looksLikeBitcoinAddress(value: String): Boolean {
        val lower = value.lowercase()
        return (lower.startsWith("bc1") || lower.startsWith("tb1") || lower.startsWith("bcrt1")) &&
            lower.length >= 20
    }

    private fun looksLikeWalletMaterial(value: String): Boolean {
        val lower = value.lowercase()
        return (lower.startsWith("nsec1") && value.length >= 12) ||
            (lower.startsWith("xprv") && value.length >= 12) ||
            (lower.startsWith("tprv") && value.length >= 12) ||
            ((value.firstOrNull() == 'K' || value.firstOrNull() == 'L' || value.firstOrNull() == '5') &&
                value.length >= 50)
    }

    private fun Char.isHexDigit(): Boolean =
        this in '0'..'9' || this in 'a'..'f' || this in 'A'..'F'

    private fun Char.isSafeEvidenceChar(): Boolean =
        this in 'a'..'z' ||
            this in 'A'..'Z' ||
            this in '0'..'9' ||
            this == '-' ||
            this == '_'

    private val forcedLockEvents = setOf(
        SkaldVaultV1VaultSessionEventKind.TimeoutReached,
        SkaldVaultV1VaultSessionEventKind.AppBackgrounded,
        SkaldVaultV1VaultSessionEventKind.AppCloseShutdown,
        SkaldVaultV1VaultSessionEventKind.ErrorFault,
        SkaldVaultV1VaultSessionEventKind.ProviderSelectionChanged,
        SkaldVaultV1VaultSessionEventKind.StorageReadinessChanged,
        SkaldVaultV1VaultSessionEventKind.SecureStorageCapabilityChanged,
        SkaldVaultV1VaultSessionEventKind.PlatformRootPathEvidenceChanged,
        SkaldVaultV1VaultSessionEventKind.ScreenCaptureSecurityPolicyChanged,
        SkaldVaultV1VaultSessionEventKind.MainnetRequestAttempted,
    )
}
