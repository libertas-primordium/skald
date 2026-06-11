package com.libertasprimordium.skald.security

interface SkaldVaultV1VaultPersistenceReadinessGate {
    fun evaluate(
        request: SkaldVaultV1VaultPersistenceReadinessRequest,
    ): SkaldVaultV1VaultPersistenceReadinessResult<SkaldVaultV1VaultPersistenceReadinessEvidence>
}

enum class SkaldVaultV1VaultPersistenceReadinessSource(val label: String) {
    NoEvidence("no vault persistence readiness evidence supplied"),
    ComposedTypedEvidence("typed provider, root, location, safety, and storage facade evidence"),
    MissingProviderEvidence("provider evidence is missing"),
    MissingRootEvidence("platform root evidence is missing"),
    MissingPathEvidence("planned artifact-location evidence is missing"),
    MissingStorageSafetyPreflightEvidence("storage safety preflight evidence is missing"),
    MissingStorageServiceEvidence("disabled storage service facade evidence is missing"),
    RejectedTypedEvidence("upstream typed evidence was already rejected"),
    RawReadinessEvidenceCandidate("raw readiness evidence candidate"),
}

enum class SkaldVaultV1VaultPersistenceReadinessStatus(val label: String) {
    NoEvidenceAvailable("no vault persistence readiness evidence available"),
    MissingProviderEvidence("provider evidence is missing"),
    MissingRootEvidence("platform root evidence is missing"),
    MissingPathEvidence("planned artifact-location evidence is missing"),
    MissingStorageSafetyPreflightEvidence("storage safety preflight evidence is missing"),
    MissingStorageServiceEvidence("disabled storage service facade evidence is missing"),
    UpstreamEvidenceRejected("upstream typed evidence was already rejected"),
    RawReadinessEvidenceRejected("raw readiness evidence is rejected"),
    PersistenceReadinessBlockedStillDisabled("vault persistence readiness remains blocked"),
}

enum class SkaldVaultV1VaultPersistenceReadinessDecision(
    val readyForPersistence: Boolean,
    val providerSelectable: Boolean,
    val mainnetAvailable: Boolean,
) {
    BlockedFailClosed(
        readyForPersistence = false,
        providerSelectable = false,
        mainnetAvailable = false,
    ),
}

enum class SkaldVaultV1VaultPersistenceRequiredGate(val label: String) {
    ProviderSelectedAndProductionSelectable("provider selected and production-selectable"),
    ProviderKatsApproved("provider KATs approved"),
    FinalKdfCalibrationApproved("final KDF calibration approved"),
    RuntimeRandomnessProviderChecksApproved("runtime randomness and provider checks approved"),
    SecureSecretStorageAvailable("secure secret storage available"),
    SecureMetadataStorageAvailable("secure metadata storage available"),
    LockSessionLifecycleApproved("lock/session lifecycle approved"),
    RedactionLeakageReviewApproved("redaction and leakage review approved"),
    ClearWipeStrategyApproved("clear/wipe strategy approved"),
    MigrationCorruptionHandlingApproved("migration and corruption handling approved"),
    PlatformRootEvidenceApproved("platform root evidence approved"),
    PathConstructionApproved("platform path-construction evidence approved"),
    PathContainmentApproved("path containment approved"),
    SymlinkSafetyApproved("symlink safety approved"),
    PermissionOwnershipApproved("permissions and ownership approved"),
    DurabilityApproved("durability approved"),
    StorageSafetyPreflightApproved("storage safety preflight approved"),
    StorageServiceOperationsImplemented("storage service operations implemented"),
    ManifestReadWriteImplemented("manifest read/write implemented"),
    StorageIndexReadWriteImplemented("storage-index read/write implemented"),
    RecordReadWriteImplemented("record read/write implemented"),
    AtomicWriteImplemented("atomic write implemented"),
    CrashRecoveryImplemented("crash recovery implemented"),
    AntiRollbackLimitationReviewed("anti-rollback limitation reviewed"),
    SettingsRootPersistenceApproved("Settings/root persistence approved where custom roots are used"),
    AndroidAppPrivateStoragePolicyPreserved("Android app-private-only storage policy preserved"),
    LinuxCustomRootPolicyReviewed("Linux custom-root policy reviewed where used"),
    ProviderOperationsConnectedWithoutSecretExposure("provider operations connected without exposing secrets"),
    NoBdkProductionPersistenceBypass("no BDK production persistence bypass"),
    NoManagedInfrastructureDependency("no public endpoint or Skald-operated infrastructure dependency"),
    MainnetReleaseHardeningApproved("mainnet remains disabled unless release review approves it"),
}

enum class SkaldVaultV1VaultPersistenceGateStatus(val label: String) {
    Missing("missing"),
    ModelOnly("model-only"),
    WarningOnly("warning-only"),
    UserConsentOnly("user-consent-only"),
    ImplementedStillDisabled("implemented but still disabled"),
    BlockedByPolicy("blocked by policy"),
    FutureReviewRequired("future review required"),
}

enum class SkaldVaultV1VaultPersistenceReadinessEvidenceKind(val label: String) {
    ProviderSelection("provider selection"),
    ProviderAcceptance("provider acceptance"),
    DependencyProbe("dependency probe"),
    DisabledProviderFacade("disabled provider facade"),
    PlatformRoot("platform root"),
    PlannedArtifactLocation("planned artifact location"),
    StorageSafetyPreflight("storage safety preflight"),
    DisabledStorageServiceFacade("disabled storage service facade"),
    SecureSecretStorage("secure secret storage"),
    SecureMetadataStorage("secure metadata storage"),
    LockSession("lock/session"),
    Redaction("redaction"),
    ClearWipe("clear/wipe"),
    MigrationCorruption("migration/corruption"),
    Mainnet("mainnet"),
}

enum class SkaldVaultV1VaultPersistenceReadinessFailureReason(val label: String) {
    NoCandidateEvidenceSupplied("no candidate persistence readiness evidence supplied"),
    MissingProviderEvidence("provider evidence is missing"),
    MissingRootEvidence("platform root evidence is missing"),
    MissingPathEvidence("planned artifact-location evidence is missing"),
    MissingStorageSafetyPreflightEvidence("storage safety preflight evidence is missing"),
    MissingStorageServiceEvidence("disabled storage service facade evidence is missing"),
    UpstreamEvidenceRejected("upstream typed evidence was already rejected"),
    DisabledProviderSelection("provider selection is disabled"),
    ProductionProviderNotSelectable("production provider is not selectable"),
    DisabledStorageServiceFacade("disabled storage service facade has no storage success path"),
    SecureSecretStorageUnavailable("secure secret storage is unavailable"),
    SecureMetadataStorageUnavailable("secure metadata storage is unavailable"),
    UnapprovedRequiredGate("a required persistence readiness gate is unapproved"),
    WarningOnlyEvidenceRejected("warning-only evidence cannot enable persistence"),
    UserConsentOverrideRejected("user consent cannot override missing hard gates"),
    MainnetUnavailable("mainnet remains unavailable"),
    RawReadinessEvidenceInputRejected("raw readiness evidence input is not accepted"),
    RawAbsoluteLocationInputRejected("raw absolute location input is not accepted"),
    RawRelativeLocationInputRejected("raw relative location input is not accepted"),
    LinkLikeInputRejected("link-like input is not accepted"),
    PlatformObjectLikeInputRejected("platform object-like input is not accepted"),
    SecretMaterialRejected("secret-looking readiness material is rejected"),
    PassphraseMaterialRejected("passphrase-like readiness material is rejected"),
    ProviderKeyMaterialRejected("provider-key-like readiness material is rejected"),
    WalletMaterialRejected("wallet or key-looking readiness material is rejected"),
    BitcoinAddressLikeEvidenceRejected("Bitcoin address-like readiness material is rejected"),
    TransactionLikeEvidenceRejected("transaction-id-like readiness material is rejected"),
    TraversalRejected("traversal-bearing readiness input is rejected"),
    EmptyEvidenceRejected("empty readiness evidence is rejected"),
    UnsupportedEvidenceRejected("unsupported readiness evidence is rejected"),
    PersistenceReadinessBlocked("vault persistence readiness remains blocked"),
}

enum class SkaldVaultV1VaultPersistenceReadinessBlocker(val label: String) {
    MissingProviderEvidence("provider evidence is missing"),
    MissingRootEvidence("platform root evidence is missing"),
    MissingPathEvidence("planned artifact-location evidence is missing"),
    MissingStorageSafetyPreflightEvidence("storage safety preflight evidence is missing"),
    MissingStorageServiceEvidence("disabled storage service facade evidence is missing"),
    UpstreamEvidenceRejected("upstream typed evidence was rejected"),
    DisabledProviderSelection("provider selection returns only the disabled provider"),
    ProductionProviderNotSelectable("production provider selectable remains false"),
    ProductionProviderPersistenceNotApproved("production provider persistence remains unapproved"),
    DependencyProbePersistenceBlocked("dependency probe still blocks persistence"),
    DisabledProviderFacadeStillDisabled("still-disabled provider facade remains non-selectable"),
    DisabledStorageServiceFacadeStillDisabled("disabled storage service facade has no success path"),
    UnapprovedRequiredGate("at least one required persistence gate remains unapproved"),
    WarningOnlyEvidenceRejected("warning-only evidence cannot enable persistence"),
    UserConsentOverrideRejected("user consent cannot override missing hard gates"),
    ReadyForPersistenceBlocked("ready-for-persistence remains false"),
    FileIoBlocked("file I/O remains unavailable"),
    ProviderSelectionBlocked("provider selection remains blocked"),
    ProductionProviderSelectionBlocked("production provider selection remains blocked"),
    VaultCreationBlocked("vault creation remains unavailable"),
    VaultUnlockBlocked("vault unlock remains unavailable"),
    VaultPersistenceBlocked("vault persistence remains unavailable"),
    SettingsPersistenceMissing("Settings persistence remains unavailable"),
    StorageImplementationMissing("storage implementation remains unavailable"),
    ManifestReadWriteMissing("manifest read/write remains unavailable"),
    StorageIndexReadWriteMissing("storage index read/write remains unavailable"),
    RecordReadWriteMissing("record read/write remains unavailable"),
    AtomicWriteMissing("atomic write remains unimplemented"),
    CrashRecoveryMissing("crash recovery remains unimplemented"),
    SecureSecretStorageMissing("secure secret storage remains unavailable"),
    SecureMetadataStorageMissing("secure metadata storage remains unavailable"),
    RealPathConstructionMissing("real platform path construction remains unavailable"),
    AbsolutePathConstructionMissing("absolute path construction remains unavailable"),
    RealPathContainmentMissing("real path containment remains unverified"),
    SymlinkSafetyMissing("symlink safety remains unverified"),
    PermissionOwnershipMissing("permission and ownership checks remain unverified"),
    DurabilityMissing("durability remains unverified"),
    AntiRollbackAnchorMissing("anti-rollback anchor remains unavailable"),
    BdkProductionPersistenceBypassUnreviewed("BDK production persistence bypass remains unavailable and unreviewed"),
    ManagedInfrastructureDependencyRejected("Skald-operated or hidden public endpoint dependency remains rejected"),
    WalletSyncUnavailable("wallet sync remains unavailable"),
    MainnetDisabled("mainnet remains unavailable"),
}

enum class SkaldVaultV1VaultPersistenceReadinessWarning(val label: String) {
    EvidenceOnly("vault persistence readiness gate is evidence only"),
    ComposesDisabledBoundaries("gate composes still-disabled provider, root, path, safety, and storage evidence"),
    NoFilesystemChecks("no filesystem checks are run"),
    NoRealOrAbsolutePathConstruction("no real or absolute platform paths are constructed"),
    NoStorageHandles("no storage handles, streams, repositories, or database handles are returned"),
    NoManifestStorageIndexOrRecordAccess("manifest, storage-index, and record access remain unavailable"),
    NoProviderApproval("production provider use is not approved"),
    NoMainnetApproval("mainnet is not approved"),
    NoSettingsPersistence("Settings persistence remains unavailable"),
    WarningOnlyCannotEnablePersistence("warning-only evidence cannot enable persistence"),
    UserConsentCannotOverrideHardGates("user consent cannot override missing hard gates"),
    RedactedByDefault("root, planned-location, record, payload, provider, and passphrase evidence is redacted"),
}

data class SkaldVaultV1VaultPersistenceReadinessCapability(
    val readyForPersistence: Boolean,
    val usableForFileIo: Boolean,
    val providerSelectable: Boolean,
    val productionProviderSelected: Boolean,
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
        val StillBlocked = SkaldVaultV1VaultPersistenceReadinessCapability(
            readyForPersistence = false,
            usableForFileIo = false,
            providerSelectable = false,
            productionProviderSelected = false,
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

data class SkaldVaultV1VaultPersistenceGateEvidence(
    val gate: SkaldVaultV1VaultPersistenceRequiredGate,
    val status: SkaldVaultV1VaultPersistenceGateStatus,
    val requiredForPersistence: Boolean = true,
    val approvedForCurrentPersistence: Boolean = false,
) {
    override fun toString(): String =
        "SkaldVaultV1VaultPersistenceGateEvidence(" +
            "gate=$gate, " +
            "status=$status, " +
            "requiredForPersistence=$requiredForPersistence, " +
            "approvedForCurrentPersistence=false, " +
            "detail=<redacted>" +
            ")"

    companion object {
        fun modelOnly(
            gate: SkaldVaultV1VaultPersistenceRequiredGate,
        ): SkaldVaultV1VaultPersistenceGateEvidence =
            SkaldVaultV1VaultPersistenceGateEvidence(gate, SkaldVaultV1VaultPersistenceGateStatus.ModelOnly)

        fun warningOnly(
            gate: SkaldVaultV1VaultPersistenceRequiredGate,
        ): SkaldVaultV1VaultPersistenceGateEvidence =
            SkaldVaultV1VaultPersistenceGateEvidence(gate, SkaldVaultV1VaultPersistenceGateStatus.WarningOnly)

        fun userConsentOnly(
            gate: SkaldVaultV1VaultPersistenceRequiredGate,
        ): SkaldVaultV1VaultPersistenceGateEvidence =
            SkaldVaultV1VaultPersistenceGateEvidence(gate, SkaldVaultV1VaultPersistenceGateStatus.UserConsentOnly)
    }
}

class SkaldVaultV1VaultPersistenceReadinessRequest private constructor(
    val source: SkaldVaultV1VaultPersistenceReadinessSource,
    private val providerSelection: VaultCryptoProviderSelectionResult?,
    private val providerAcceptanceAssessment: ProductionProviderAcceptanceAssessment?,
    private val encryptedVaultReadinessDecision: EncryptedVaultReadinessDecision?,
    private val dependencyProbeResult: VaultCryptoDependencyProbeResult?,
    private val providerFacadeMetadata: SkaldVaultV1StillDisabledProviderFacadeMetadata?,
    private val rootEvidence: SkaldVaultV1PlatformRootResolverEvidence?,
    private val rootFailureReason: SkaldVaultV1PlatformRootResolverFailureReason?,
    private val pathEvidence: SkaldVaultV1PlatformPathConstructionEvidence?,
    private val pathFailureReason: SkaldVaultV1PlatformPathConstructionFailureReason?,
    private val storageSafetyPreflightEvidence: SkaldVaultV1StorageSafetyPreflightEvidence?,
    private val storageSafetyPreflightFailureReason: SkaldVaultV1StorageSafetyPreflightFailureReason?,
    private val storageServiceEvidence: SkaldVaultV1VaultStorageDisabledEvidence?,
    private val storageServiceFailureReason: SkaldVaultV1VaultStorageOperationFailureReason?,
    private val secureStorageCapability: SecureStorageCapability?,
    private val secureMetadataCapability: SecureMetadataPersistenceCapability?,
    private val gateEvidence: List<SkaldVaultV1VaultPersistenceGateEvidence>,
    private val rawCandidate: String?,
) {
    fun testOnlyRawCandidate(): String? = rawCandidate

    internal fun providerSelectionOrNull(): VaultCryptoProviderSelectionResult? = providerSelection

    internal fun providerAcceptanceAssessmentOrNull(): ProductionProviderAcceptanceAssessment? =
        providerAcceptanceAssessment

    internal fun encryptedVaultReadinessDecisionOrNull(): EncryptedVaultReadinessDecision? =
        encryptedVaultReadinessDecision

    internal fun dependencyProbeResultOrNull(): VaultCryptoDependencyProbeResult? = dependencyProbeResult

    internal fun providerFacadeMetadataOrNull(): SkaldVaultV1StillDisabledProviderFacadeMetadata? =
        providerFacadeMetadata

    internal fun rootEvidenceOrNull(): SkaldVaultV1PlatformRootResolverEvidence? = rootEvidence

    internal fun rootFailureReasonOrNull(): SkaldVaultV1PlatformRootResolverFailureReason? =
        rootFailureReason

    internal fun pathEvidenceOrNull(): SkaldVaultV1PlatformPathConstructionEvidence? = pathEvidence

    internal fun pathFailureReasonOrNull(): SkaldVaultV1PlatformPathConstructionFailureReason? =
        pathFailureReason

    internal fun storageSafetyPreflightEvidenceOrNull(): SkaldVaultV1StorageSafetyPreflightEvidence? =
        storageSafetyPreflightEvidence

    internal fun storageSafetyPreflightFailureReasonOrNull(): SkaldVaultV1StorageSafetyPreflightFailureReason? =
        storageSafetyPreflightFailureReason

    internal fun storageServiceEvidenceOrNull(): SkaldVaultV1VaultStorageDisabledEvidence? =
        storageServiceEvidence

    internal fun storageServiceFailureReasonOrNull(): SkaldVaultV1VaultStorageOperationFailureReason? =
        storageServiceFailureReason

    internal fun secureStorageCapabilityOrNull(): SecureStorageCapability? = secureStorageCapability

    internal fun secureMetadataCapabilityOrNull(): SecureMetadataPersistenceCapability? = secureMetadataCapability

    internal fun gateEvidence(): List<SkaldVaultV1VaultPersistenceGateEvidence> = gateEvidence

    internal fun rawCandidateOrNull(): String? = rawCandidate

    override fun toString(): String =
        "SkaldVaultV1VaultPersistenceReadinessRequest(" +
            "source=$source, " +
            "providerSelection=<redacted>, " +
            "providerAcceptance=<redacted>, " +
            "readiness=<redacted>, " +
            "dependencyProbe=<redacted>, " +
            "providerFacade=<redacted>, " +
            "rootEvidence=<redacted>, " +
            "pathEvidence=<redacted>, " +
            "storageSafetyPreflight=<redacted>, " +
            "storageService=<redacted>, " +
            "secureStorage=<redacted>, " +
            "secureMetadata=<redacted>, " +
            "gateEvidenceCount=${gateEvidence.size}, " +
            "rawCandidate=<redacted>" +
            ")"

    companion object {
        fun noEvidence(): SkaldVaultV1VaultPersistenceReadinessRequest =
            SkaldVaultV1VaultPersistenceReadinessRequest(
                source = SkaldVaultV1VaultPersistenceReadinessSource.NoEvidence,
                providerSelection = null,
                providerAcceptanceAssessment = null,
                encryptedVaultReadinessDecision = null,
                dependencyProbeResult = null,
                providerFacadeMetadata = null,
                rootEvidence = null,
                rootFailureReason = null,
                pathEvidence = null,
                pathFailureReason = null,
                storageSafetyPreflightEvidence = null,
                storageSafetyPreflightFailureReason = null,
                storageServiceEvidence = null,
                storageServiceFailureReason = null,
                secureStorageCapability = null,
                secureMetadataCapability = null,
                gateEvidence = emptyList(),
                rawCandidate = null,
            )

        fun fromEvidence(
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
            rootEvidence: SkaldVaultV1PlatformRootResolverEvidence?,
            pathEvidence: SkaldVaultV1PlatformPathConstructionEvidence?,
            storageSafetyPreflightEvidence: SkaldVaultV1StorageSafetyPreflightEvidence?,
            storageServiceEvidence: SkaldVaultV1VaultStorageDisabledEvidence?,
            secureStorageCapability: SecureStorageCapability? = commonDisabledSecureStorageCapability(),
            secureMetadataCapability: SecureMetadataPersistenceCapability? = commonDisabledSecureMetadataCapability(),
            gateEvidence: List<SkaldVaultV1VaultPersistenceGateEvidence> = emptyList(),
        ): SkaldVaultV1VaultPersistenceReadinessRequest =
            SkaldVaultV1VaultPersistenceReadinessRequest(
                source = sourceFor(
                    providerSelection = providerSelection,
                    rootEvidence = rootEvidence,
                    pathEvidence = pathEvidence,
                    storageSafetyPreflightEvidence = storageSafetyPreflightEvidence,
                    storageServiceEvidence = storageServiceEvidence,
                ),
                providerSelection = providerSelection,
                providerAcceptanceAssessment = providerAcceptanceAssessment,
                encryptedVaultReadinessDecision = encryptedVaultReadinessDecision,
                dependencyProbeResult = dependencyProbeResult,
                providerFacadeMetadata = providerFacadeMetadata,
                rootEvidence = rootEvidence,
                rootFailureReason = null,
                pathEvidence = pathEvidence,
                pathFailureReason = null,
                storageSafetyPreflightEvidence = storageSafetyPreflightEvidence,
                storageSafetyPreflightFailureReason = null,
                storageServiceEvidence = storageServiceEvidence,
                storageServiceFailureReason = null,
                secureStorageCapability = secureStorageCapability,
                secureMetadataCapability = secureMetadataCapability,
                gateEvidence = gateEvidence,
                rawCandidate = null,
            )

        fun fromBoundaryResults(
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
            rootResult: SkaldVaultV1PlatformRootResolverResult<SkaldVaultV1PlatformRootResolverEvidence>,
            pathResult: SkaldVaultV1PlatformPathConstructionResult<SkaldVaultV1PlatformPathConstructionEvidence>,
            storageSafetyPreflightResult:
                SkaldVaultV1StorageSafetyPreflightResult<SkaldVaultV1StorageSafetyPreflightEvidence>,
            storageServiceResult:
                SkaldVaultV1VaultStorageOperationResult<SkaldVaultV1VaultStorageDisabledEvidence>,
            secureStorageCapability: SecureStorageCapability? = commonDisabledSecureStorageCapability(),
            secureMetadataCapability: SecureMetadataPersistenceCapability? = commonDisabledSecureMetadataCapability(),
            gateEvidence: List<SkaldVaultV1VaultPersistenceGateEvidence> = emptyList(),
        ): SkaldVaultV1VaultPersistenceReadinessRequest {
            val rootEvidence = when (rootResult) {
                is SkaldVaultV1PlatformRootResolverResult.Accepted -> rootResult.value
                is SkaldVaultV1PlatformRootResolverResult.Rejected -> null
            }
            val rootFailureReason = when (rootResult) {
                is SkaldVaultV1PlatformRootResolverResult.Accepted -> null
                is SkaldVaultV1PlatformRootResolverResult.Rejected -> rootResult.reason
            }
            val pathEvidence = when (pathResult) {
                is SkaldVaultV1PlatformPathConstructionResult.Accepted -> pathResult.value
                is SkaldVaultV1PlatformPathConstructionResult.Rejected -> null
            }
            val pathFailureReason = when (pathResult) {
                is SkaldVaultV1PlatformPathConstructionResult.Accepted -> null
                is SkaldVaultV1PlatformPathConstructionResult.Rejected -> pathResult.reason
            }
            val preflightEvidence = when (storageSafetyPreflightResult) {
                is SkaldVaultV1StorageSafetyPreflightResult.Accepted -> storageSafetyPreflightResult.value
                is SkaldVaultV1StorageSafetyPreflightResult.Rejected -> null
            }
            val preflightFailureReason = when (storageSafetyPreflightResult) {
                is SkaldVaultV1StorageSafetyPreflightResult.Accepted -> null
                is SkaldVaultV1StorageSafetyPreflightResult.Rejected -> storageSafetyPreflightResult.reason
            }
            val storageEvidence = when (storageServiceResult) {
                is SkaldVaultV1VaultStorageOperationResult.Disabled -> storageServiceResult.value
                is SkaldVaultV1VaultStorageOperationResult.Rejected -> null
            }
            val storageFailureReason = when (storageServiceResult) {
                is SkaldVaultV1VaultStorageOperationResult.Disabled -> null
                is SkaldVaultV1VaultStorageOperationResult.Rejected -> storageServiceResult.reason
            }
            return SkaldVaultV1VaultPersistenceReadinessRequest(
                source = sourceFor(
                    providerSelection = providerSelection,
                    rootEvidence = rootEvidence,
                    pathEvidence = pathEvidence,
                    storageSafetyPreflightEvidence = preflightEvidence,
                    storageServiceEvidence = storageEvidence,
                    rootFailureReason = rootFailureReason,
                    pathFailureReason = pathFailureReason,
                    storageSafetyPreflightFailureReason = preflightFailureReason,
                    storageServiceFailureReason = storageFailureReason,
                ),
                providerSelection = providerSelection,
                providerAcceptanceAssessment = providerAcceptanceAssessment,
                encryptedVaultReadinessDecision = encryptedVaultReadinessDecision,
                dependencyProbeResult = dependencyProbeResult,
                providerFacadeMetadata = providerFacadeMetadata,
                rootEvidence = rootEvidence,
                rootFailureReason = rootFailureReason,
                pathEvidence = pathEvidence,
                pathFailureReason = pathFailureReason,
                storageSafetyPreflightEvidence = preflightEvidence,
                storageSafetyPreflightFailureReason = preflightFailureReason,
                storageServiceEvidence = storageEvidence,
                storageServiceFailureReason = storageFailureReason,
                secureStorageCapability = secureStorageCapability,
                secureMetadataCapability = secureMetadataCapability,
                gateEvidence = gateEvidence,
                rawCandidate = null,
            )
        }

        fun rawReadinessEvidenceCandidate(rawCandidate: String?): SkaldVaultV1VaultPersistenceReadinessRequest =
            SkaldVaultV1VaultPersistenceReadinessRequest(
                source = SkaldVaultV1VaultPersistenceReadinessSource.RawReadinessEvidenceCandidate,
                providerSelection = null,
                providerAcceptanceAssessment = null,
                encryptedVaultReadinessDecision = null,
                dependencyProbeResult = null,
                providerFacadeMetadata = null,
                rootEvidence = null,
                rootFailureReason = null,
                pathEvidence = null,
                pathFailureReason = null,
                storageSafetyPreflightEvidence = null,
                storageSafetyPreflightFailureReason = null,
                storageServiceEvidence = null,
                storageServiceFailureReason = null,
                secureStorageCapability = null,
                secureMetadataCapability = null,
                gateEvidence = emptyList(),
                rawCandidate = rawCandidate,
            )

        private fun sourceFor(
            providerSelection: VaultCryptoProviderSelectionResult?,
            rootEvidence: SkaldVaultV1PlatformRootResolverEvidence?,
            pathEvidence: SkaldVaultV1PlatformPathConstructionEvidence?,
            storageSafetyPreflightEvidence: SkaldVaultV1StorageSafetyPreflightEvidence?,
            storageServiceEvidence: SkaldVaultV1VaultStorageDisabledEvidence?,
            rootFailureReason: SkaldVaultV1PlatformRootResolverFailureReason? = null,
            pathFailureReason: SkaldVaultV1PlatformPathConstructionFailureReason? = null,
            storageSafetyPreflightFailureReason: SkaldVaultV1StorageSafetyPreflightFailureReason? = null,
            storageServiceFailureReason: SkaldVaultV1VaultStorageOperationFailureReason? = null,
        ): SkaldVaultV1VaultPersistenceReadinessSource =
            when {
                rootFailureReason != null ||
                    pathFailureReason != null ||
                    storageSafetyPreflightFailureReason != null ||
                    storageServiceFailureReason != null ->
                    SkaldVaultV1VaultPersistenceReadinessSource.RejectedTypedEvidence
                providerSelection == null -> SkaldVaultV1VaultPersistenceReadinessSource.MissingProviderEvidence
                rootEvidence == null -> SkaldVaultV1VaultPersistenceReadinessSource.MissingRootEvidence
                pathEvidence == null -> SkaldVaultV1VaultPersistenceReadinessSource.MissingPathEvidence
                storageSafetyPreflightEvidence == null ->
                    SkaldVaultV1VaultPersistenceReadinessSource.MissingStorageSafetyPreflightEvidence
                storageServiceEvidence == null ->
                    SkaldVaultV1VaultPersistenceReadinessSource.MissingStorageServiceEvidence
                else -> SkaldVaultV1VaultPersistenceReadinessSource.ComposedTypedEvidence
            }
    }
}

class SkaldVaultV1VaultPersistenceReadinessToken internal constructor(
    val gateCount: Int,
    val plannedLocationCount: Int,
    val storageSafetyGateCount: Int,
    val storageOperation: SkaldVaultV1VaultStorageOperation?,
) {
    val containsRootText: Boolean = false
    val containsPlannedLocationText: Boolean = false
    val containsRecordIdentifier: Boolean = false
    val containsPayload: Boolean = false
    val containsProviderKeyMaterial: Boolean = false
    val containsPassphraseMaterial: Boolean = false
    val readyForPersistence: Boolean = false

    override fun toString(): String =
        "SkaldVaultV1VaultPersistenceReadinessToken(" +
            "gateCount=$gateCount, " +
            "plannedLocationCount=$plannedLocationCount, " +
            "storageSafetyGateCount=$storageSafetyGateCount, " +
            "storageOperation=$storageOperation, " +
            "root=<redacted>, " +
            "plannedLocations=<redacted>, " +
            "recordIdentifier=<redacted>, " +
            "payload=<redacted>, " +
            "providerKeyMaterial=<redacted>, " +
            "passphraseMaterial=<redacted>, " +
            "readyForPersistence=false" +
            ")"
}

class SkaldVaultV1VaultPersistenceReadinessEvidence internal constructor(
    val policyId: String,
    val policyVersion: Int,
    val status: SkaldVaultV1VaultPersistenceReadinessStatus,
    val decision: SkaldVaultV1VaultPersistenceReadinessDecision,
    val source: SkaldVaultV1VaultPersistenceReadinessSource,
    val capability: SkaldVaultV1VaultPersistenceReadinessCapability,
    val token: SkaldVaultV1VaultPersistenceReadinessToken,
    val gateEvidence: List<SkaldVaultV1VaultPersistenceGateEvidence>,
    val evidenceKinds: Set<SkaldVaultV1VaultPersistenceReadinessEvidenceKind>,
    val blockers: Set<SkaldVaultV1VaultPersistenceReadinessBlocker>,
    val warnings: Set<SkaldVaultV1VaultPersistenceReadinessWarning>,
    val persistenceReadinessGateModeled: Boolean = true,
    val persistenceReadinessGateStillBlocked: Boolean = true,
    val persistenceReadinessGateComposesStorageEvidence: Boolean = true,
    val persistenceReadinessGateDoesNotUseFilesystem: Boolean = true,
    val persistenceReadinessGateDoesNotEnablePersistence: Boolean = true,
    val persistenceReadinessGateDoesNotEnableProviderSelection: Boolean = true,
    val persistenceReadinessFailureVocabularyModeled: Boolean = true,
    val rootEvidenceConsumed: Boolean = false,
    val plannedArtifactLocationEvidenceConsumed: Boolean = false,
    val storageSafetyPreflightEvidenceConsumed: Boolean = false,
    val disabledStorageServiceFacadeEvidenceConsumed: Boolean = false,
    val providerEvidenceConsumed: Boolean = false,
    val secureStorageEvidenceConsumed: Boolean = false,
    val secureMetadataEvidenceConsumed: Boolean = false,
    val productionReady: Boolean = false,
    val platformPathObjectReturned: Boolean = false,
    val payloadExposedByDefault: Boolean = false,
) {
    val readyForPersistence: Boolean = false

    override fun toString(): String =
        "SkaldVaultV1VaultPersistenceReadinessEvidence(" +
            "policyId=$policyId, " +
            "policyVersion=$policyVersion, " +
            "status=$status, " +
            "decision=$decision, " +
            "source=$source, " +
            "gateCount=${gateEvidence.size}, " +
            "evidenceKinds=$evidenceKinds, " +
            "root=<redacted>, " +
            "plannedLocations=<redacted>, " +
            "recordIdentifier=<redacted>, " +
            "payload=<redacted>, " +
            "providerKeyMaterial=<redacted>, " +
            "passphraseMaterial=<redacted>, " +
            "readyForPersistence=false, " +
            "productionReady=false" +
            ")"
}

sealed class SkaldVaultV1VaultPersistenceReadinessResult<out T> {
    abstract val readyForPersistence: Boolean

    data class Blocked<out T>(val value: T) : SkaldVaultV1VaultPersistenceReadinessResult<T>() {
        override val readyForPersistence: Boolean = false

        override fun toString(): String = "Blocked(value=<redacted-vault-persistence-readiness-evidence>)"
    }

    data class Rejected(
        val reason: SkaldVaultV1VaultPersistenceReadinessFailureReason,
        val status: SkaldVaultV1VaultPersistenceReadinessStatus,
        val source: SkaldVaultV1VaultPersistenceReadinessSource,
        val safeMessage: String = reason.label,
    ) : SkaldVaultV1VaultPersistenceReadinessResult<Nothing>() {
        override val readyForPersistence: Boolean = false

        override fun toString(): String =
            "Rejected(" +
                "reason=$reason, " +
                "status=$status, " +
                "source=$source, " +
                "safeMessage=$safeMessage, " +
                "rawReadinessEvidence=<redacted>" +
                ")"
    }
}

object SkaldVaultV1PersistenceReadinessGate : SkaldVaultV1VaultPersistenceReadinessGate {
    const val POLICY_ID = "skald-vault-v1-persistence-readiness-gate-v1"
    const val POLICY_VERSION = 1

    override fun evaluate(
        request: SkaldVaultV1VaultPersistenceReadinessRequest,
    ): SkaldVaultV1VaultPersistenceReadinessResult<SkaldVaultV1VaultPersistenceReadinessEvidence> =
        when (request.source) {
            SkaldVaultV1VaultPersistenceReadinessSource.RawReadinessEvidenceCandidate ->
                rejectRawCandidate(request)
            else -> blockedEvidence(request)
        }

    private fun blockedEvidence(
        request: SkaldVaultV1VaultPersistenceReadinessRequest,
    ): SkaldVaultV1VaultPersistenceReadinessResult.Blocked<SkaldVaultV1VaultPersistenceReadinessEvidence> {
        val plannedLocations = plannedLocations(request)
        val storageSafetyGateEvidence = request.storageSafetyPreflightEvidenceOrNull()?.gateEvidence.orEmpty()
        val requiredGates = requiredGateEvidence(request)
        val blockers = blockersFor(request, requiredGates)
        val readinessMarker = SkaldVaultV1VaultPersistenceReadinessToken(
            gateCount = requiredGates.size,
            plannedLocationCount = plannedLocations.size,
            storageSafetyGateCount = storageSafetyGateEvidence.size,
            storageOperation = request.storageServiceEvidenceOrNull()?.operation,
        )
        return SkaldVaultV1VaultPersistenceReadinessResult.Blocked(
            SkaldVaultV1VaultPersistenceReadinessEvidence(
                POLICY_ID,
                POLICY_VERSION,
                statusFor(request.source),
                SkaldVaultV1VaultPersistenceReadinessDecision.BlockedFailClosed,
                request.source,
                SkaldVaultV1VaultPersistenceReadinessCapability.StillBlocked,
                readinessMarker,
                requiredGates,
                evidenceKindsFor(request),
                blockers,
                SkaldVaultV1VaultPersistenceReadinessWarning.entries.toSet(),
                rootEvidenceConsumed = request.rootEvidenceOrNull() != null,
                plannedArtifactLocationEvidenceConsumed = plannedLocations.isNotEmpty(),
                storageSafetyPreflightEvidenceConsumed = storageSafetyGateEvidence.isNotEmpty(),
                disabledStorageServiceFacadeEvidenceConsumed = request.storageServiceEvidenceOrNull() != null,
                providerEvidenceConsumed = request.providerSelectionOrNull() != null ||
                    request.providerAcceptanceAssessmentOrNull() != null ||
                    request.dependencyProbeResultOrNull() != null ||
                    request.providerFacadeMetadataOrNull() != null,
                secureStorageEvidenceConsumed = request.secureStorageCapabilityOrNull() != null,
                secureMetadataEvidenceConsumed = request.secureMetadataCapabilityOrNull() != null,
            ),
        )
    }

    private fun rejectRawCandidate(
        request: SkaldVaultV1VaultPersistenceReadinessRequest,
    ): SkaldVaultV1VaultPersistenceReadinessResult.Rejected =
        SkaldVaultV1VaultPersistenceReadinessResult.Rejected(
            reason = classifyReadinessInput(request.rawCandidateOrNull()),
            status = SkaldVaultV1VaultPersistenceReadinessStatus.RawReadinessEvidenceRejected,
            source = request.source,
        )

    private fun statusFor(
        source: SkaldVaultV1VaultPersistenceReadinessSource,
    ): SkaldVaultV1VaultPersistenceReadinessStatus =
        when (source) {
            SkaldVaultV1VaultPersistenceReadinessSource.NoEvidence ->
                SkaldVaultV1VaultPersistenceReadinessStatus.NoEvidenceAvailable
            SkaldVaultV1VaultPersistenceReadinessSource.MissingProviderEvidence ->
                SkaldVaultV1VaultPersistenceReadinessStatus.MissingProviderEvidence
            SkaldVaultV1VaultPersistenceReadinessSource.MissingRootEvidence ->
                SkaldVaultV1VaultPersistenceReadinessStatus.MissingRootEvidence
            SkaldVaultV1VaultPersistenceReadinessSource.MissingPathEvidence ->
                SkaldVaultV1VaultPersistenceReadinessStatus.MissingPathEvidence
            SkaldVaultV1VaultPersistenceReadinessSource.MissingStorageSafetyPreflightEvidence ->
                SkaldVaultV1VaultPersistenceReadinessStatus.MissingStorageSafetyPreflightEvidence
            SkaldVaultV1VaultPersistenceReadinessSource.MissingStorageServiceEvidence ->
                SkaldVaultV1VaultPersistenceReadinessStatus.MissingStorageServiceEvidence
            SkaldVaultV1VaultPersistenceReadinessSource.RejectedTypedEvidence ->
                SkaldVaultV1VaultPersistenceReadinessStatus.UpstreamEvidenceRejected
            SkaldVaultV1VaultPersistenceReadinessSource.RawReadinessEvidenceCandidate ->
                SkaldVaultV1VaultPersistenceReadinessStatus.RawReadinessEvidenceRejected
            SkaldVaultV1VaultPersistenceReadinessSource.ComposedTypedEvidence ->
                SkaldVaultV1VaultPersistenceReadinessStatus.PersistenceReadinessBlockedStillDisabled
        }

    private fun plannedLocations(
        request: SkaldVaultV1VaultPersistenceReadinessRequest,
    ): List<SkaldVaultV1PlannedPlatformArtifactLocation> =
        request.pathEvidenceOrNull()?.locations()
            ?: request.storageSafetyPreflightEvidenceOrNull()?.locations()
            ?: request.storageServiceEvidenceOrNull()?.locations()
            ?: emptyList()

    private fun requiredGateEvidence(
        request: SkaldVaultV1VaultPersistenceReadinessRequest,
    ): List<SkaldVaultV1VaultPersistenceGateEvidence> {
        val suppliedByGate = request.gateEvidence().associateBy { it.gate }
        return SkaldVaultV1VaultPersistenceRequiredGate.entries.map { gate ->
            val supplied = suppliedByGate[gate]
            SkaldVaultV1VaultPersistenceGateEvidence(
                gate = gate,
                status = supplied?.status ?: defaultGateStatus(gate, request),
                requiredForPersistence = supplied?.requiredForPersistence ?: true,
                approvedForCurrentPersistence = false,
            )
        }
    }

    private fun defaultGateStatus(
        gate: SkaldVaultV1VaultPersistenceRequiredGate,
        request: SkaldVaultV1VaultPersistenceReadinessRequest,
    ): SkaldVaultV1VaultPersistenceGateStatus =
        when (gate) {
            SkaldVaultV1VaultPersistenceRequiredGate.PlatformRootEvidenceApproved ->
                if (request.rootEvidenceOrNull() != null) {
                    SkaldVaultV1VaultPersistenceGateStatus.ImplementedStillDisabled
                } else {
                    SkaldVaultV1VaultPersistenceGateStatus.Missing
                }
            SkaldVaultV1VaultPersistenceRequiredGate.PathConstructionApproved ->
                if (request.pathEvidenceOrNull() != null) {
                    SkaldVaultV1VaultPersistenceGateStatus.ImplementedStillDisabled
                } else {
                    SkaldVaultV1VaultPersistenceGateStatus.Missing
                }
            SkaldVaultV1VaultPersistenceRequiredGate.StorageSafetyPreflightApproved ->
                if (request.storageSafetyPreflightEvidenceOrNull() != null) {
                    SkaldVaultV1VaultPersistenceGateStatus.ImplementedStillDisabled
                } else {
                    SkaldVaultV1VaultPersistenceGateStatus.Missing
                }
            SkaldVaultV1VaultPersistenceRequiredGate.StorageServiceOperationsImplemented ->
                if (request.storageServiceEvidenceOrNull() != null) {
                    SkaldVaultV1VaultPersistenceGateStatus.ImplementedStillDisabled
                } else {
                    SkaldVaultV1VaultPersistenceGateStatus.Missing
                }
            SkaldVaultV1VaultPersistenceRequiredGate.ProviderSelectedAndProductionSelectable,
            SkaldVaultV1VaultPersistenceRequiredGate.ProviderKatsApproved,
            SkaldVaultV1VaultPersistenceRequiredGate.FinalKdfCalibrationApproved,
            SkaldVaultV1VaultPersistenceRequiredGate.RuntimeRandomnessProviderChecksApproved,
            SkaldVaultV1VaultPersistenceRequiredGate.SecureSecretStorageAvailable,
            SkaldVaultV1VaultPersistenceRequiredGate.SecureMetadataStorageAvailable,
            SkaldVaultV1VaultPersistenceRequiredGate.AndroidAppPrivateStoragePolicyPreserved,
            SkaldVaultV1VaultPersistenceRequiredGate.MainnetReleaseHardeningApproved ->
                if (request.providerSelectionOrNull() != null ||
                    request.secureStorageCapabilityOrNull() != null ||
                    request.secureMetadataCapabilityOrNull() != null
                ) {
                    SkaldVaultV1VaultPersistenceGateStatus.BlockedByPolicy
                } else {
                    SkaldVaultV1VaultPersistenceGateStatus.Missing
                }
            else -> SkaldVaultV1VaultPersistenceGateStatus.FutureReviewRequired
        }

    private fun blockersFor(
        request: SkaldVaultV1VaultPersistenceReadinessRequest,
        requiredGates: List<SkaldVaultV1VaultPersistenceGateEvidence>,
    ): Set<SkaldVaultV1VaultPersistenceReadinessBlocker> =
        buildSet {
            addAll(disabledCapabilityBlockers())
            when (request.source) {
                SkaldVaultV1VaultPersistenceReadinessSource.NoEvidence -> {
                    add(SkaldVaultV1VaultPersistenceReadinessBlocker.MissingProviderEvidence)
                    add(SkaldVaultV1VaultPersistenceReadinessBlocker.MissingRootEvidence)
                    add(SkaldVaultV1VaultPersistenceReadinessBlocker.MissingPathEvidence)
                    add(SkaldVaultV1VaultPersistenceReadinessBlocker.MissingStorageSafetyPreflightEvidence)
                    add(SkaldVaultV1VaultPersistenceReadinessBlocker.MissingStorageServiceEvidence)
                }
                SkaldVaultV1VaultPersistenceReadinessSource.MissingProviderEvidence ->
                    add(SkaldVaultV1VaultPersistenceReadinessBlocker.MissingProviderEvidence)
                SkaldVaultV1VaultPersistenceReadinessSource.MissingRootEvidence ->
                    add(SkaldVaultV1VaultPersistenceReadinessBlocker.MissingRootEvidence)
                SkaldVaultV1VaultPersistenceReadinessSource.MissingPathEvidence ->
                    add(SkaldVaultV1VaultPersistenceReadinessBlocker.MissingPathEvidence)
                SkaldVaultV1VaultPersistenceReadinessSource.MissingStorageSafetyPreflightEvidence ->
                    add(SkaldVaultV1VaultPersistenceReadinessBlocker.MissingStorageSafetyPreflightEvidence)
                SkaldVaultV1VaultPersistenceReadinessSource.MissingStorageServiceEvidence ->
                    add(SkaldVaultV1VaultPersistenceReadinessBlocker.MissingStorageServiceEvidence)
                SkaldVaultV1VaultPersistenceReadinessSource.RejectedTypedEvidence ->
                    add(SkaldVaultV1VaultPersistenceReadinessBlocker.UpstreamEvidenceRejected)
                SkaldVaultV1VaultPersistenceReadinessSource.ComposedTypedEvidence,
                SkaldVaultV1VaultPersistenceReadinessSource.RawReadinessEvidenceCandidate -> Unit
            }
            val providerSelection = request.providerSelectionOrNull()
            if (providerSelection == null || providerSelection.selectedProviderIsDisabled) {
                add(SkaldVaultV1VaultPersistenceReadinessBlocker.DisabledProviderSelection)
            }
            if (providerSelection?.productionProviderSelectable != true) {
                add(SkaldVaultV1VaultPersistenceReadinessBlocker.ProductionProviderNotSelectable)
            }
            val providerAcceptance = request.providerAcceptanceAssessmentOrNull()
            if (providerAcceptance?.productionProviderSelectable != true ||
                providerAcceptance?.productionPersistenceAllowed != true
            ) {
                add(SkaldVaultV1VaultPersistenceReadinessBlocker.ProductionProviderPersistenceNotApproved)
            }
            val dependency = request.dependencyProbeResultOrNull()
            if (dependency?.storageEnabled != true || dependency?.productionPersistenceEnabled != true) {
                add(SkaldVaultV1VaultPersistenceReadinessBlocker.DependencyProbePersistenceBlocked)
            }
            val readiness = request.encryptedVaultReadinessDecisionOrNull()
            if (readiness?.canEnableProductionPersistence != true) {
                add(SkaldVaultV1VaultPersistenceReadinessBlocker.ReadyForPersistenceBlocked)
            }
            val providerFacade = request.providerFacadeMetadataOrNull()
            if (providerFacade?.status?.selectable != true || providerFacade?.status?.operationsEnabled != true) {
                add(SkaldVaultV1VaultPersistenceReadinessBlocker.DisabledProviderFacadeStillDisabled)
            }
            val storageService = request.storageServiceEvidenceOrNull()
            if (storageService == null || storageService.noOperationReturnsSuccess) {
                add(SkaldVaultV1VaultPersistenceReadinessBlocker.DisabledStorageServiceFacadeStillDisabled)
            }
            if (request.storageServiceFailureReasonOrNull() != null ||
                request.rootFailureReasonOrNull() != null ||
                request.pathFailureReasonOrNull() != null ||
                request.storageSafetyPreflightFailureReasonOrNull() != null
            ) {
                add(SkaldVaultV1VaultPersistenceReadinessBlocker.UpstreamEvidenceRejected)
            }
            val secureStorage = request.secureStorageCapabilityOrNull()
            if (secureStorage?.status?.availableForSecretMaterial != true ||
                secureStorage?.canStoreSecrets != true ||
                secureStorage?.canReadSecrets != true
            ) {
                add(SkaldVaultV1VaultPersistenceReadinessBlocker.SecureSecretStorageMissing)
            }
            val secureMetadata = request.secureMetadataCapabilityOrNull()
            if (secureMetadata?.status?.availableForSensitiveMetadata != true ||
                secureMetadata?.canStoreMetadata != true ||
                secureMetadata?.canReadMetadata != true
            ) {
                add(SkaldVaultV1VaultPersistenceReadinessBlocker.SecureMetadataStorageMissing)
            }
            if (requiredGates.any { !it.approvedForCurrentPersistence }) {
                add(SkaldVaultV1VaultPersistenceReadinessBlocker.UnapprovedRequiredGate)
            }
            if (requiredGates.any { it.status == SkaldVaultV1VaultPersistenceGateStatus.WarningOnly }) {
                add(SkaldVaultV1VaultPersistenceReadinessBlocker.WarningOnlyEvidenceRejected)
            }
            if (requiredGates.any { it.status == SkaldVaultV1VaultPersistenceGateStatus.UserConsentOnly }) {
                add(SkaldVaultV1VaultPersistenceReadinessBlocker.UserConsentOverrideRejected)
            }
        }

    private fun disabledCapabilityBlockers(): Set<SkaldVaultV1VaultPersistenceReadinessBlocker> =
        setOf(
            SkaldVaultV1VaultPersistenceReadinessBlocker.ReadyForPersistenceBlocked,
            SkaldVaultV1VaultPersistenceReadinessBlocker.FileIoBlocked,
            SkaldVaultV1VaultPersistenceReadinessBlocker.ProviderSelectionBlocked,
            SkaldVaultV1VaultPersistenceReadinessBlocker.ProductionProviderSelectionBlocked,
            SkaldVaultV1VaultPersistenceReadinessBlocker.VaultCreationBlocked,
            SkaldVaultV1VaultPersistenceReadinessBlocker.VaultUnlockBlocked,
            SkaldVaultV1VaultPersistenceReadinessBlocker.VaultPersistenceBlocked,
            SkaldVaultV1VaultPersistenceReadinessBlocker.SettingsPersistenceMissing,
            SkaldVaultV1VaultPersistenceReadinessBlocker.StorageImplementationMissing,
            SkaldVaultV1VaultPersistenceReadinessBlocker.ManifestReadWriteMissing,
            SkaldVaultV1VaultPersistenceReadinessBlocker.StorageIndexReadWriteMissing,
            SkaldVaultV1VaultPersistenceReadinessBlocker.RecordReadWriteMissing,
            SkaldVaultV1VaultPersistenceReadinessBlocker.AtomicWriteMissing,
            SkaldVaultV1VaultPersistenceReadinessBlocker.CrashRecoveryMissing,
            SkaldVaultV1VaultPersistenceReadinessBlocker.SecureSecretStorageMissing,
            SkaldVaultV1VaultPersistenceReadinessBlocker.SecureMetadataStorageMissing,
            SkaldVaultV1VaultPersistenceReadinessBlocker.RealPathConstructionMissing,
            SkaldVaultV1VaultPersistenceReadinessBlocker.AbsolutePathConstructionMissing,
            SkaldVaultV1VaultPersistenceReadinessBlocker.RealPathContainmentMissing,
            SkaldVaultV1VaultPersistenceReadinessBlocker.SymlinkSafetyMissing,
            SkaldVaultV1VaultPersistenceReadinessBlocker.PermissionOwnershipMissing,
            SkaldVaultV1VaultPersistenceReadinessBlocker.DurabilityMissing,
            SkaldVaultV1VaultPersistenceReadinessBlocker.AntiRollbackAnchorMissing,
            SkaldVaultV1VaultPersistenceReadinessBlocker.BdkProductionPersistenceBypassUnreviewed,
            SkaldVaultV1VaultPersistenceReadinessBlocker.ManagedInfrastructureDependencyRejected,
            SkaldVaultV1VaultPersistenceReadinessBlocker.WalletSyncUnavailable,
            SkaldVaultV1VaultPersistenceReadinessBlocker.MainnetDisabled,
        )

    private fun evidenceKindsFor(
        request: SkaldVaultV1VaultPersistenceReadinessRequest,
    ): Set<SkaldVaultV1VaultPersistenceReadinessEvidenceKind> =
        buildSet {
            if (request.providerSelectionOrNull() != null) {
                add(SkaldVaultV1VaultPersistenceReadinessEvidenceKind.ProviderSelection)
            }
            if (request.providerAcceptanceAssessmentOrNull() != null) {
                add(SkaldVaultV1VaultPersistenceReadinessEvidenceKind.ProviderAcceptance)
            }
            if (request.dependencyProbeResultOrNull() != null) {
                add(SkaldVaultV1VaultPersistenceReadinessEvidenceKind.DependencyProbe)
            }
            if (request.providerFacadeMetadataOrNull() != null) {
                add(SkaldVaultV1VaultPersistenceReadinessEvidenceKind.DisabledProviderFacade)
            }
            if (request.rootEvidenceOrNull() != null) {
                add(SkaldVaultV1VaultPersistenceReadinessEvidenceKind.PlatformRoot)
            }
            if (request.pathEvidenceOrNull() != null) {
                add(SkaldVaultV1VaultPersistenceReadinessEvidenceKind.PlannedArtifactLocation)
            }
            if (request.storageSafetyPreflightEvidenceOrNull() != null) {
                add(SkaldVaultV1VaultPersistenceReadinessEvidenceKind.StorageSafetyPreflight)
            }
            if (request.storageServiceEvidenceOrNull() != null) {
                add(SkaldVaultV1VaultPersistenceReadinessEvidenceKind.DisabledStorageServiceFacade)
            }
            if (request.secureStorageCapabilityOrNull() != null) {
                add(SkaldVaultV1VaultPersistenceReadinessEvidenceKind.SecureSecretStorage)
            }
            if (request.secureMetadataCapabilityOrNull() != null) {
                add(SkaldVaultV1VaultPersistenceReadinessEvidenceKind.SecureMetadataStorage)
            }
            add(SkaldVaultV1VaultPersistenceReadinessEvidenceKind.LockSession)
            add(SkaldVaultV1VaultPersistenceReadinessEvidenceKind.Redaction)
            add(SkaldVaultV1VaultPersistenceReadinessEvidenceKind.ClearWipe)
            add(SkaldVaultV1VaultPersistenceReadinessEvidenceKind.MigrationCorruption)
            add(SkaldVaultV1VaultPersistenceReadinessEvidenceKind.Mainnet)
        }

    private fun classifyReadinessInput(
        raw: String?,
    ): SkaldVaultV1VaultPersistenceReadinessFailureReason {
        if (raw == null || raw.isBlank()) {
            return SkaldVaultV1VaultPersistenceReadinessFailureReason.EmptyEvidenceRejected
        }
        val lower = raw.lowercase()
        return when {
            lower.startsWith("file:") ||
                lower.startsWith("http:") ||
                lower.startsWith("https:") ||
                lower.startsWith("content:") ->
                SkaldVaultV1VaultPersistenceReadinessFailureReason.LinkLikeInputRejected
            lower.contains("file-object") ||
                lower.contains("path-object") ||
                lower.contains("stream") ||
                lower.contains("database") ||
                lower.contains("settings") ->
                SkaldVaultV1VaultPersistenceReadinessFailureReason.PlatformObjectLikeInputRejected
            looksLikeWindowsOrUnc(raw) || raw.startsWith("/") ->
                SkaldVaultV1VaultPersistenceReadinessFailureReason.RawAbsoluteLocationInputRejected
            raw.contains("..") ->
                SkaldVaultV1VaultPersistenceReadinessFailureReason.TraversalRejected
            raw.contains("/") || raw.contains("\\") ->
                SkaldVaultV1VaultPersistenceReadinessFailureReason.RawRelativeLocationInputRejected
            lower.contains("passphrase") ->
                SkaldVaultV1VaultPersistenceReadinessFailureReason.PassphraseMaterialRejected
            lower.contains("provider-key") ||
                lower.contains("provider_key") ||
                lower.contains("key material") ->
                SkaldVaultV1VaultPersistenceReadinessFailureReason.ProviderKeyMaterialRejected
            looksLikeSecretOrCredential(lower) ->
                SkaldVaultV1VaultPersistenceReadinessFailureReason.SecretMaterialRejected
            looksLikePrivateKeyMaterial(raw, lower) ->
                SkaldVaultV1VaultPersistenceReadinessFailureReason.WalletMaterialRejected
            looksLikeBitcoinAddress(lower) ->
                SkaldVaultV1VaultPersistenceReadinessFailureReason.BitcoinAddressLikeEvidenceRejected
            containsLongHexSegment(raw) ->
                SkaldVaultV1VaultPersistenceReadinessFailureReason.TransactionLikeEvidenceRejected
            hasUnsupportedEvidenceCharacters(raw) ->
                SkaldVaultV1VaultPersistenceReadinessFailureReason.UnsupportedEvidenceRejected
            else -> SkaldVaultV1VaultPersistenceReadinessFailureReason.RawReadinessEvidenceInputRejected
        }
    }

    private fun looksLikeWindowsOrUnc(value: String): Boolean =
        value.length >= 3 &&
            value[1] == ':' &&
            value[2] == '\\' ||
            value.startsWith("\\\\")

    private fun looksLikeSecretOrCredential(lower: String): Boolean =
        listOf(
            "password",
            "passwd",
            "secret",
            "token",
            "key=",
            "api_key",
            "seed",
            "mnemonic",
            "recovery phrase",
            "private",
        ).any { lower.contains(it) } ||
            lower.contains(":") && lower.contains("@")

    private fun looksLikePrivateKeyMaterial(raw: String, lower: String): Boolean =
        lower.contains("nsec") ||
            raw.startsWith("xprv") ||
            raw.startsWith("tprv") ||
            isWifLike(raw)

    private fun looksLikeBitcoinAddress(lower: String): Boolean =
        lower.startsWith("bc1") ||
            lower.startsWith("tb1") ||
            lower.startsWith("bcrt1")

    private fun isWifLike(raw: String): Boolean =
        raw.length in 51..52 &&
            raw.firstOrNull() in setOf('K', 'L', '5') &&
            raw.all {
                it in '1'..'9' ||
                    it in 'A'..'H' ||
                    it in 'J'..'N' ||
                    it in 'P'..'Z' ||
                    it in 'a'..'k' ||
                    it in 'm'..'z'
            }

    private fun containsLongHexSegment(raw: String): Boolean {
        var run = 0
        raw.forEach { char ->
            run = if (char.isHex()) run + 1 else 0
            if (run >= 64) return true
        }
        return false
    }

    private fun hasUnsupportedEvidenceCharacters(raw: String): Boolean =
        raw.any { char ->
            !(char in 'a'..'z' ||
                char in 'A'..'Z' ||
                char in '0'..'9' ||
                char == '_' ||
                char == '-')
        }

    private fun Char.isHex(): Boolean =
        this in '0'..'9' || this in 'a'..'f' || this in 'A'..'F'
}
