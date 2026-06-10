package com.libertasprimordium.skald.security

interface SkaldVaultV1StorageSafetyPreflightBoundary {
    fun evaluate(
        request: SkaldVaultV1StorageSafetyPreflightRequest,
    ): SkaldVaultV1StorageSafetyPreflightResult<SkaldVaultV1StorageSafetyPreflightEvidence>
}

enum class SkaldVaultV1StorageSafetyPreflightSource(val label: String) {
    NoEvidence("no storage safety preflight evidence supplied"),
    PlannedArtifactLocationEvidence("planned artifact-location evidence"),
    RejectedArtifactLocationEvidence("rejected planned artifact-location evidence"),
    RawStorageSafetyEvidenceCandidate("raw storage safety evidence candidate"),
}

enum class SkaldVaultV1StorageSafetyPreflightStatus(val label: String) {
    NoEvidenceAvailable("no storage safety preflight evidence available"),
    MissingArtifactLocationEvidence("missing planned artifact-location evidence"),
    ArtifactLocationEvidenceRejected("planned artifact-location evidence was already rejected"),
    RawStorageSafetyEvidenceRejected("raw storage safety evidence is rejected"),
    StorageSafetyPreflightBlockedStillDisabled(
        "storage safety preflight evidence accepted but still blocked from persistence",
    ),
}

enum class SkaldVaultV1StorageSafetyEvidenceKind(val label: String) {
    RootEvidencePresent("root evidence present"),
    PlannedArtifactLocationEvidencePresent("planned artifact-location evidence present"),
    RealPathConstructionReviewed("real path construction reviewed"),
    ContainmentCheckReviewed("containment check reviewed"),
    SymlinkSafetyCheckReviewed("symlink safety check reviewed"),
    PermissionCheckReviewed("permission check reviewed"),
    OwnershipCheckReviewed("ownership check reviewed"),
    DurabilityCheckReviewed("durability check reviewed"),
    AtomicWriteMechanismReviewed("atomic write mechanism reviewed"),
    CrashRecoveryMechanismReviewed("crash-recovery mechanism reviewed"),
    ManifestReadWriteMechanismReviewed("manifest read/write mechanism reviewed"),
    StorageIndexReadWriteMechanismReviewed("storage-index read/write mechanism reviewed"),
    RecordReadWriteMechanismReviewed("record read/write mechanism reviewed"),
    StorageFailureMappingReviewed("storage failure mapping reviewed"),
    CorruptionHandlingReviewed("corruption handling reviewed"),
    MigrationHandlingReviewed("migration handling reviewed"),
    RedactionLoggingSafetyReviewed("redaction and logging safety reviewed"),
    SecureSecretStorageAvailable("secure secret storage available"),
    SecureMetadataStorageAvailable("secure metadata storage available"),
    AntiRollbackLimitationReviewed("anti-rollback limitation reviewed"),
    ProviderSelectabilityReviewed("provider selectability reviewed"),
    MainnetDisabled("mainnet remains disabled"),
}

enum class SkaldVaultV1StorageSafetyGateStatus(val label: String) {
    Missing("missing"),
    ModelOnly("model-only"),
    WarningOnly("warning-only"),
    UserConsentOnly("user-consent-only"),
    FutureReviewRequired("future review required"),
}

enum class SkaldVaultV1StorageSafetyPreflightFailureReason(val label: String) {
    NoCandidateEvidenceSupplied("no candidate storage safety evidence supplied"),
    MissingArtifactLocationEvidence("missing planned artifact-location evidence"),
    RejectedArtifactLocationEvidence("planned artifact-location evidence was already rejected"),
    MissingSafetyEvidence("storage safety evidence is missing"),
    UnapprovedSafetyGate("storage safety gate is not approved for persistence"),
    WarningOnlyEvidenceRejected("warning-only evidence cannot enable persistence"),
    UserConsentOverrideRejected("user consent cannot override failed or missing storage safety gates"),
    RawStorageSafetyEvidenceInputRejected("raw storage safety evidence input is not accepted"),
    RawAbsolutePathInputRejected("raw absolute path input is not accepted"),
    RawRelativePathInputRejected("raw relative path input is not accepted"),
    UriLikeInputRejected("URI-like or web-link input is not accepted"),
    FileOrPathObjectInputRejected("file-object or path-object input is not accepted by this API"),
    SecretMaterialRejected("secret-looking evidence material is rejected"),
    WalletMaterialRejected("wallet or key-looking evidence material is rejected"),
    BitcoinAddressLikeEvidenceRejected("Bitcoin address-like evidence material is rejected"),
    TransactionLikeEvidenceRejected("transaction-id-like evidence material is rejected"),
    PathTraversalRejected("path traversal evidence is rejected"),
    EmptyEvidenceNameRejected("empty evidence name is rejected"),
    UnsupportedEvidenceNameRejected("unsupported evidence name is rejected"),
    AcceptedButPersistenceBlocked("accepted storage safety preflight evidence remains blocked from persistence"),
}

enum class SkaldVaultV1StorageSafetyBlocker(val label: String) {
    MissingArtifactLocationEvidence("planned artifact-location evidence is missing"),
    RejectedArtifactLocationEvidence("planned artifact-location evidence was rejected upstream"),
    MissingSafetyEvidence("storage safety evidence is missing"),
    UnapprovedSafetyGate("at least one required safety gate remains unapproved"),
    WarningOnlyEvidenceRejected("warning-only evidence cannot enable persistence"),
    UserConsentOverrideRejected("user consent cannot override failed or missing safety gates"),
    AcceptedButFileIoBlocked("storage safety evidence is not usable for file I/O"),
    AcceptedButPersistenceBlocked("storage safety evidence is not usable for persistence"),
    ProviderSelectionBlocked("provider selection remains blocked"),
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
    PermissionCheckMissing("permission checks remain unverified"),
    OwnershipCheckMissing("ownership checks remain unverified"),
    DurabilityMissing("durability remains unverified"),
    StorageFailureMappingMissing("storage failure mapping remains unimplemented"),
    CorruptionHandlingMissing("corruption handling remains unimplemented"),
    MigrationHandlingMissing("migration handling remains unimplemented"),
    RedactionLoggingReviewMissing("redaction and logging safety review remains incomplete"),
    AntiRollbackAnchorMissing("anti-rollback anchor remains unavailable"),
    MainnetDisabled("mainnet remains unavailable"),
}

enum class SkaldVaultV1StorageSafetyWarning(val label: String) {
    EvidenceOnly("storage safety preflight is a still-disabled evidence model only"),
    PlannedLocationsAreNotPaths("planned artifact locations are not platform paths"),
    NoFilesystemChecks("no filesystem checks are run"),
    NoFileIo("no file I/O is available"),
    NoSettingsPersistence("Settings persistence remains unavailable"),
    PersistenceStillDisabled("vault persistence remains disabled"),
    WarningOnlyCannotEnablePersistence("warning-only evidence cannot enable persistence"),
    UserConsentCannotOverrideFailedGates("user consent cannot override failed or missing gates"),
    AntiRollbackLimitationsOnly("anti-rollback limitations are modeled without an anchor"),
}

data class SkaldVaultV1StorageSafetyCapability(
    val usableForPersistence: Boolean,
    val usableForFileIo: Boolean,
    val providerSelectable: Boolean,
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
        val StillDisabled = SkaldVaultV1StorageSafetyCapability(
            usableForPersistence = false,
            usableForFileIo = false,
            providerSelectable = false,
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

data class SkaldVaultV1StorageSafetyEvidence(
    val kind: SkaldVaultV1StorageSafetyEvidenceKind,
    val status: SkaldVaultV1StorageSafetyGateStatus,
    val requiredForPersistence: Boolean = true,
    val approvedForCurrentPersistence: Boolean = false,
) {
    override fun toString(): String =
        "SkaldVaultV1StorageSafetyEvidence(" +
            "kind=$kind, " +
            "status=$status, " +
            "requiredForPersistence=$requiredForPersistence, " +
            "approvedForCurrentPersistence=false, " +
            "detail=<redacted>" +
            ")"

    companion object {
        fun modelOnly(kind: SkaldVaultV1StorageSafetyEvidenceKind): SkaldVaultV1StorageSafetyEvidence =
            SkaldVaultV1StorageSafetyEvidence(kind, SkaldVaultV1StorageSafetyGateStatus.ModelOnly)

        fun warningOnly(kind: SkaldVaultV1StorageSafetyEvidenceKind): SkaldVaultV1StorageSafetyEvidence =
            SkaldVaultV1StorageSafetyEvidence(kind, SkaldVaultV1StorageSafetyGateStatus.WarningOnly)

        fun userConsentOnly(kind: SkaldVaultV1StorageSafetyEvidenceKind): SkaldVaultV1StorageSafetyEvidence =
            SkaldVaultV1StorageSafetyEvidence(kind, SkaldVaultV1StorageSafetyGateStatus.UserConsentOnly)

        fun futureReviewRequired(kind: SkaldVaultV1StorageSafetyEvidenceKind): SkaldVaultV1StorageSafetyEvidence =
            SkaldVaultV1StorageSafetyEvidence(kind, SkaldVaultV1StorageSafetyGateStatus.FutureReviewRequired)
    }
}

data class SkaldVaultV1StorageSafetyGateEvidence(
    val kind: SkaldVaultV1StorageSafetyEvidenceKind,
    val status: SkaldVaultV1StorageSafetyGateStatus,
    val requiredForPersistence: Boolean,
    val approvedForCurrentPersistence: Boolean,
)

class SkaldVaultV1StorageSafetyPreflightRequest private constructor(
    val source: SkaldVaultV1StorageSafetyPreflightSource,
    private val artifactEvidence: SkaldVaultV1PlatformPathConstructionEvidence?,
    private val rejectedArtifactReason: SkaldVaultV1PlatformPathConstructionFailureReason?,
    private val safetyEvidence: List<SkaldVaultV1StorageSafetyEvidence>,
    private val rawCandidate: String?,
) {
    fun testOnlyRawCandidate(): String? = rawCandidate

    internal fun artifactEvidenceOrNull(): SkaldVaultV1PlatformPathConstructionEvidence? = artifactEvidence

    internal fun rejectedArtifactReasonOrNull(): SkaldVaultV1PlatformPathConstructionFailureReason? =
        rejectedArtifactReason

    internal fun safetyEvidence(): List<SkaldVaultV1StorageSafetyEvidence> = safetyEvidence

    internal fun rawCandidateOrNull(): String? = rawCandidate

    override fun toString(): String =
        "SkaldVaultV1StorageSafetyPreflightRequest(" +
            "source=$source, " +
            "artifactEvidence=<redacted>, " +
            "rejectedArtifactReason=$rejectedArtifactReason, " +
            "safetyEvidenceCount=${safetyEvidence.size}, " +
            "rawCandidate=<redacted>" +
            ")"

    companion object {
        fun noEvidence(): SkaldVaultV1StorageSafetyPreflightRequest =
            SkaldVaultV1StorageSafetyPreflightRequest(
                source = SkaldVaultV1StorageSafetyPreflightSource.NoEvidence,
                artifactEvidence = null,
                rejectedArtifactReason = null,
                safetyEvidence = emptyList(),
                rawCandidate = null,
            )

        fun fromPathConstructionResult(
            result: SkaldVaultV1PlatformPathConstructionResult<SkaldVaultV1PlatformPathConstructionEvidence>,
            safetyEvidence: List<SkaldVaultV1StorageSafetyEvidence> = emptyList(),
        ): SkaldVaultV1StorageSafetyPreflightRequest =
            when (result) {
                is SkaldVaultV1PlatformPathConstructionResult.Accepted -> fromArtifactLocationEvidence(
                    artifactEvidence = result.value,
                    safetyEvidence = safetyEvidence,
                )
                is SkaldVaultV1PlatformPathConstructionResult.Rejected ->
                    SkaldVaultV1StorageSafetyPreflightRequest(
                        source = SkaldVaultV1StorageSafetyPreflightSource.RejectedArtifactLocationEvidence,
                        artifactEvidence = null,
                        rejectedArtifactReason = result.reason,
                        safetyEvidence = safetyEvidence,
                        rawCandidate = null,
                    )
            }

        fun fromArtifactLocationEvidence(
            artifactEvidence: SkaldVaultV1PlatformPathConstructionEvidence?,
            safetyEvidence: List<SkaldVaultV1StorageSafetyEvidence> = emptyList(),
        ): SkaldVaultV1StorageSafetyPreflightRequest =
            SkaldVaultV1StorageSafetyPreflightRequest(
                source = SkaldVaultV1StorageSafetyPreflightSource.PlannedArtifactLocationEvidence,
                artifactEvidence = artifactEvidence,
                rejectedArtifactReason = null,
                safetyEvidence = safetyEvidence,
                rawCandidate = null,
            )

        fun rawStorageSafetyEvidenceCandidate(rawCandidate: String?): SkaldVaultV1StorageSafetyPreflightRequest =
            SkaldVaultV1StorageSafetyPreflightRequest(
                source = SkaldVaultV1StorageSafetyPreflightSource.RawStorageSafetyEvidenceCandidate,
                artifactEvidence = null,
                rejectedArtifactReason = null,
                safetyEvidence = emptyList(),
                rawCandidate = rawCandidate,
            )
    }
}

class SkaldVaultV1StorageSafetyPreflightToken internal constructor(
    val artifactCount: Int,
    val gateCount: Int,
) {
    val containsPlatformPath: Boolean = false
    val containsSecretMaterial: Boolean = false
    val usableForFileIo: Boolean = false
    val usableForPersistence: Boolean = false

    override fun toString(): String =
        "SkaldVaultV1StorageSafetyPreflightToken(" +
            "artifactCount=$artifactCount, " +
            "gateCount=$gateCount, " +
            "artifactLocations=<redacted>, " +
            "containsPlatformPath=false, " +
            "containsSecretMaterial=false, " +
            "usableForFileIo=false, " +
            "usableForPersistence=false" +
            ")"
}

class SkaldVaultV1StorageSafetyPreflightEvidence internal constructor(
    val policyId: String,
    val policyVersion: Int,
    val status: SkaldVaultV1StorageSafetyPreflightStatus,
    val source: SkaldVaultV1StorageSafetyPreflightSource,
    val capability: SkaldVaultV1StorageSafetyCapability,
    val token: SkaldVaultV1StorageSafetyPreflightToken,
    private val plannedLocations: List<SkaldVaultV1PlannedPlatformArtifactLocation>,
    val gateEvidence: List<SkaldVaultV1StorageSafetyGateEvidence>,
    val blockers: Set<SkaldVaultV1StorageSafetyBlocker>,
    val warnings: Set<SkaldVaultV1StorageSafetyWarning>,
    val storageSafetyPreflightStillDisabled: Boolean = true,
    val storageSafetyGateVocabularyModeled: Boolean = true,
    val plannedArtifactLocationEvidenceConsumed: Boolean = true,
    val storageSafetyPreflightDoesNotRunFilesystemChecks: Boolean = true,
    val storageSafetyPreflightDoesNotEnablePersistence: Boolean = true,
    val storageSafetyPreflightDoesNotEnableProviderSelection: Boolean = true,
    val platformPathObjectReturned: Boolean = false,
    val rawStorageEvidenceExposedByDefault: Boolean = false,
) {
    fun locations(): List<SkaldVaultV1PlannedPlatformArtifactLocation> = plannedLocations

    override fun toString(): String =
        "SkaldVaultV1StorageSafetyPreflightEvidence(" +
            "policyId=$policyId, " +
            "policyVersion=$policyVersion, " +
            "status=$status, " +
            "source=$source, " +
            "artifactCount=${plannedLocations.size}, " +
            "gateCount=${gateEvidence.size}, " +
            "artifactLocations=<redacted>, " +
            "storageEvidence=<redacted>, " +
            "platformPathObjectReturned=false" +
            ")"
}

sealed class SkaldVaultV1StorageSafetyPreflightResult<out T> {
    data class Accepted<out T>(val value: T) : SkaldVaultV1StorageSafetyPreflightResult<T>() {
        override fun toString(): String = "Accepted(value=<redacted-storage-safety-preflight-evidence>)"
    }

    data class Rejected(
        val reason: SkaldVaultV1StorageSafetyPreflightFailureReason,
        val status: SkaldVaultV1StorageSafetyPreflightStatus,
        val source: SkaldVaultV1StorageSafetyPreflightSource,
        val safeMessage: String = reason.label,
        val artifactLocationFailureReason: SkaldVaultV1PlatformPathConstructionFailureReason? = null,
    ) : SkaldVaultV1StorageSafetyPreflightResult<Nothing>() {
        override fun toString(): String =
            "Rejected(" +
                "reason=$reason, " +
                "status=$status, " +
                "source=$source, " +
                "safeMessage=$safeMessage, " +
                "artifactLocationFailureReason=$artifactLocationFailureReason" +
                ")"
    }
}

object SkaldVaultV1StorageSafetyPreflightPolicy : SkaldVaultV1StorageSafetyPreflightBoundary {
    const val POLICY_ID = "skald-vault-v1-storage-safety-preflight-boundary-v1"
    const val POLICY_VERSION = 1

    override fun evaluate(
        request: SkaldVaultV1StorageSafetyPreflightRequest,
    ): SkaldVaultV1StorageSafetyPreflightResult<SkaldVaultV1StorageSafetyPreflightEvidence> =
        when (request.source) {
            SkaldVaultV1StorageSafetyPreflightSource.NoEvidence -> reject(
                reason = SkaldVaultV1StorageSafetyPreflightFailureReason.NoCandidateEvidenceSupplied,
                status = SkaldVaultV1StorageSafetyPreflightStatus.NoEvidenceAvailable,
                source = request.source,
            )
            SkaldVaultV1StorageSafetyPreflightSource.RejectedArtifactLocationEvidence -> reject(
                reason = SkaldVaultV1StorageSafetyPreflightFailureReason.RejectedArtifactLocationEvidence,
                status = SkaldVaultV1StorageSafetyPreflightStatus.ArtifactLocationEvidenceRejected,
                source = request.source,
                artifactLocationFailureReason = request.rejectedArtifactReasonOrNull(),
            )
            SkaldVaultV1StorageSafetyPreflightSource.RawStorageSafetyEvidenceCandidate ->
                rejectRawCandidate(request)
            SkaldVaultV1StorageSafetyPreflightSource.PlannedArtifactLocationEvidence ->
                acceptStillDisabled(request)
        }

    private fun acceptStillDisabled(
        request: SkaldVaultV1StorageSafetyPreflightRequest,
    ): SkaldVaultV1StorageSafetyPreflightResult<SkaldVaultV1StorageSafetyPreflightEvidence> {
        val artifactEvidence = request.artifactEvidenceOrNull()
            ?: return reject(
                reason = SkaldVaultV1StorageSafetyPreflightFailureReason.MissingArtifactLocationEvidence,
                status = SkaldVaultV1StorageSafetyPreflightStatus.MissingArtifactLocationEvidence,
                source = request.source,
            )
        val suppliedEvidence = request.safetyEvidence()
        val suppliedByKind = suppliedEvidence.associateBy { it.kind }
        val gateEvidence = SkaldVaultV1StorageSafetyEvidenceKind.entries.map { kind ->
            val supplied = suppliedByKind[kind]
            SkaldVaultV1StorageSafetyGateEvidence(
                kind = kind,
                status = supplied?.status ?: SkaldVaultV1StorageSafetyGateStatus.Missing,
                requiredForPersistence = supplied?.requiredForPersistence ?: true,
                approvedForCurrentPersistence = false,
            )
        }
        val blockers = buildSet {
            addAll(disabledCapabilityBlockers())
            if (suppliedEvidence.isEmpty()) {
                add(SkaldVaultV1StorageSafetyBlocker.MissingSafetyEvidence)
            }
            if (gateEvidence.any { it.status != SkaldVaultV1StorageSafetyGateStatus.Missing }) {
                add(SkaldVaultV1StorageSafetyBlocker.UnapprovedSafetyGate)
            }
            if (gateEvidence.any { it.status == SkaldVaultV1StorageSafetyGateStatus.WarningOnly }) {
                add(SkaldVaultV1StorageSafetyBlocker.WarningOnlyEvidenceRejected)
            }
            if (gateEvidence.any { it.status == SkaldVaultV1StorageSafetyGateStatus.UserConsentOnly }) {
                add(SkaldVaultV1StorageSafetyBlocker.UserConsentOverrideRejected)
            }
        }
        val locations = artifactEvidence.locations()
        return SkaldVaultV1StorageSafetyPreflightResult.Accepted(
            SkaldVaultV1StorageSafetyPreflightEvidence(
                policyId = POLICY_ID,
                policyVersion = POLICY_VERSION,
                status = SkaldVaultV1StorageSafetyPreflightStatus.StorageSafetyPreflightBlockedStillDisabled,
                source = request.source,
                capability = SkaldVaultV1StorageSafetyCapability.StillDisabled,
                token = SkaldVaultV1StorageSafetyPreflightToken(
                    artifactCount = locations.size,
                    gateCount = gateEvidence.size,
                ),
                plannedLocations = locations,
                gateEvidence = gateEvidence,
                blockers = blockers,
                warnings = SkaldVaultV1StorageSafetyWarning.entries.toSet(),
            ),
        )
    }

    private fun rejectRawCandidate(
        request: SkaldVaultV1StorageSafetyPreflightRequest,
    ): SkaldVaultV1StorageSafetyPreflightResult.Rejected =
        reject(
            reason = classifyRawCandidate(request.rawCandidateOrNull()),
            status = SkaldVaultV1StorageSafetyPreflightStatus.RawStorageSafetyEvidenceRejected,
            source = request.source,
        )

    private fun reject(
        reason: SkaldVaultV1StorageSafetyPreflightFailureReason,
        status: SkaldVaultV1StorageSafetyPreflightStatus,
        source: SkaldVaultV1StorageSafetyPreflightSource,
        artifactLocationFailureReason: SkaldVaultV1PlatformPathConstructionFailureReason? = null,
    ): SkaldVaultV1StorageSafetyPreflightResult.Rejected =
        SkaldVaultV1StorageSafetyPreflightResult.Rejected(
            reason = reason,
            status = status,
            source = source,
            artifactLocationFailureReason = artifactLocationFailureReason,
        )

    private fun disabledCapabilityBlockers(): Set<SkaldVaultV1StorageSafetyBlocker> =
        setOf(
            SkaldVaultV1StorageSafetyBlocker.AcceptedButFileIoBlocked,
            SkaldVaultV1StorageSafetyBlocker.AcceptedButPersistenceBlocked,
            SkaldVaultV1StorageSafetyBlocker.ProviderSelectionBlocked,
            SkaldVaultV1StorageSafetyBlocker.VaultCreationBlocked,
            SkaldVaultV1StorageSafetyBlocker.VaultUnlockBlocked,
            SkaldVaultV1StorageSafetyBlocker.VaultPersistenceBlocked,
            SkaldVaultV1StorageSafetyBlocker.SettingsPersistenceMissing,
            SkaldVaultV1StorageSafetyBlocker.StorageImplementationMissing,
            SkaldVaultV1StorageSafetyBlocker.ManifestReadWriteMissing,
            SkaldVaultV1StorageSafetyBlocker.StorageIndexReadWriteMissing,
            SkaldVaultV1StorageSafetyBlocker.RecordReadWriteMissing,
            SkaldVaultV1StorageSafetyBlocker.AtomicWriteMissing,
            SkaldVaultV1StorageSafetyBlocker.CrashRecoveryMissing,
            SkaldVaultV1StorageSafetyBlocker.SecureSecretStorageMissing,
            SkaldVaultV1StorageSafetyBlocker.SecureMetadataStorageMissing,
            SkaldVaultV1StorageSafetyBlocker.RealPathConstructionMissing,
            SkaldVaultV1StorageSafetyBlocker.AbsolutePathConstructionMissing,
            SkaldVaultV1StorageSafetyBlocker.RealPathContainmentMissing,
            SkaldVaultV1StorageSafetyBlocker.SymlinkSafetyMissing,
            SkaldVaultV1StorageSafetyBlocker.PermissionCheckMissing,
            SkaldVaultV1StorageSafetyBlocker.OwnershipCheckMissing,
            SkaldVaultV1StorageSafetyBlocker.DurabilityMissing,
            SkaldVaultV1StorageSafetyBlocker.StorageFailureMappingMissing,
            SkaldVaultV1StorageSafetyBlocker.CorruptionHandlingMissing,
            SkaldVaultV1StorageSafetyBlocker.MigrationHandlingMissing,
            SkaldVaultV1StorageSafetyBlocker.RedactionLoggingReviewMissing,
            SkaldVaultV1StorageSafetyBlocker.AntiRollbackAnchorMissing,
            SkaldVaultV1StorageSafetyBlocker.MainnetDisabled,
        )

    private fun classifyRawCandidate(
        raw: String?,
    ): SkaldVaultV1StorageSafetyPreflightFailureReason {
        if (raw == null || raw.isBlank()) {
            return SkaldVaultV1StorageSafetyPreflightFailureReason.EmptyEvidenceNameRejected
        }
        val lower = raw.lowercase()
        return when {
            lower.startsWith("file:") ||
                lower.startsWith("http:") ||
                lower.startsWith("https:") ||
                lower.startsWith("content:") ->
                SkaldVaultV1StorageSafetyPreflightFailureReason.UriLikeInputRejected
            lower.contains("file-object") ||
                lower.contains("path-object") ->
                SkaldVaultV1StorageSafetyPreflightFailureReason.FileOrPathObjectInputRejected
            looksLikeWindowsOrUnc(raw) ->
                SkaldVaultV1StorageSafetyPreflightFailureReason.RawAbsolutePathInputRejected
            raw.startsWith("/") ->
                SkaldVaultV1StorageSafetyPreflightFailureReason.RawAbsolutePathInputRejected
            raw.contains("..") ->
                SkaldVaultV1StorageSafetyPreflightFailureReason.PathTraversalRejected
            raw.contains("/") || raw.contains("\\") ->
                SkaldVaultV1StorageSafetyPreflightFailureReason.RawRelativePathInputRejected
            looksLikeSecretOrCredential(lower) ->
                SkaldVaultV1StorageSafetyPreflightFailureReason.SecretMaterialRejected
            looksLikePrivateKeyMaterial(raw, lower) ->
                SkaldVaultV1StorageSafetyPreflightFailureReason.WalletMaterialRejected
            looksLikeBitcoinAddress(lower) ->
                SkaldVaultV1StorageSafetyPreflightFailureReason.BitcoinAddressLikeEvidenceRejected
            containsLongHexSegment(raw) ->
                SkaldVaultV1StorageSafetyPreflightFailureReason.TransactionLikeEvidenceRejected
            hasUnsupportedEvidenceCharacters(raw) ->
                SkaldVaultV1StorageSafetyPreflightFailureReason.UnsupportedEvidenceNameRejected
            else -> SkaldVaultV1StorageSafetyPreflightFailureReason.RawStorageSafetyEvidenceInputRejected
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
            raw.all { it in '1'..'9' || it in 'A'..'H' || it in 'J'..'N' || it in 'P'..'Z' || it in 'a'..'k' || it in 'm'..'z' }

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
