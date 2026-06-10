package com.libertasprimordium.skald.security

interface SkaldVaultV1VaultStorageService {
    fun status(): SkaldVaultV1VaultStorageFacadeStatus

    fun modeledOperations(): Set<SkaldVaultV1VaultStorageOperation>

    fun perform(
        request: SkaldVaultV1VaultStorageOperationRequest,
    ): SkaldVaultV1VaultStorageOperationResult<SkaldVaultV1VaultStorageDisabledEvidence>
}

enum class SkaldVaultV1VaultStorageOperation(val label: String) {
    InitializeStorageNamespace("initialize storage namespace"),
    ReadCurrentContainer("read current container"),
    WriteCurrentContainer("write current container"),
    ReadCurrentManifest("read current manifest"),
    WriteCurrentManifest("write current manifest"),
    ReadStorageIndex("read storage index"),
    WriteStorageIndex("write storage index"),
    ReadRecord("read record"),
    WriteRecord("write record"),
    ListRecordDescriptors("list record descriptors"),
    DeleteRecord("delete record"),
    QuarantineRecord("quarantine record"),
    RecoverInterruptedWrite("recover interrupted write"),
    RunStorageHealthCheck("run storage health check"),
    MapStorageFailure("map storage failure"),
    PrepareAtomicWrite("prepare atomic write"),
    CommitAtomicWrite("commit atomic write"),
    RollbackAtomicWrite("rollback atomic write"),
    VerifyAntiRollbackFreshnessEvidence("verify anti-rollback and freshness evidence"),
    CloseLockStorageSession("close or lock storage session"),
}

enum class SkaldVaultV1VaultStorageOperationSource(val label: String) {
    NoEvidence("no storage service evidence supplied"),
    ArtifactAndPreflightEvidence("planned artifact-location and storage safety preflight evidence"),
    MissingArtifactLocationEvidence("missing planned artifact-location evidence"),
    MissingStorageSafetyPreflightEvidence("missing storage safety preflight evidence"),
    RejectedArtifactLocationEvidence("rejected planned artifact-location evidence"),
    RejectedStorageSafetyPreflightEvidence("rejected storage safety preflight evidence"),
    RawStorageInputCandidate("raw storage input candidate"),
}

enum class SkaldVaultV1VaultStorageFacadeStatus(
    val label: String,
    val operationsEnabled: Boolean,
    val persistenceAvailable: Boolean,
    val providerSelectable: Boolean,
) {
    StillDisabled(
        label = "vault storage service facade remains disabled",
        operationsEnabled = false,
        persistenceAvailable = false,
        providerSelectable = false,
    ),
}

enum class SkaldVaultV1VaultStorageOperationStatus(val label: String) {
    NoEvidenceAvailable("no vault storage service evidence available"),
    MissingArtifactLocationEvidence("planned artifact-location evidence is missing"),
    MissingStorageSafetyPreflightEvidence("storage safety preflight evidence is missing"),
    ArtifactLocationEvidenceRejected("planned artifact-location evidence was already rejected"),
    StorageSafetyPreflightRejected("storage safety preflight evidence was already rejected"),
    RawStorageInputRejected("raw storage input is rejected"),
    StorageOperationDisabled("storage operation accepted as disabled evidence only"),
}

enum class SkaldVaultV1VaultStorageOperationFailureReason(val label: String) {
    NoCandidateEvidenceSupplied("no candidate vault storage service evidence supplied"),
    MissingArtifactLocationEvidence("planned artifact-location evidence is missing"),
    MissingStorageSafetyPreflightEvidence("storage safety preflight evidence is missing"),
    RejectedArtifactLocationEvidence("planned artifact-location evidence was already rejected"),
    RejectedStorageSafetyPreflightEvidence("storage safety preflight evidence was already rejected"),
    StorageServiceFacadeDisabled("disabled storage service facade has no success path"),
    StorageOperationDisabled("storage operation remains disabled"),
    RawStorageInputRejected("raw storage input is not accepted"),
    RawAbsoluteLocationInputRejected("raw absolute location input is not accepted"),
    RawRelativeLocationInputRejected("raw relative location input is not accepted"),
    LinkLikeInputRejected("link-like input is not accepted"),
    PlatformObjectLikeInputRejected("platform object-like input is not accepted"),
    SecretMaterialRejected("secret-looking storage input is rejected"),
    WalletMaterialRejected("wallet or key-looking storage input is rejected"),
    BitcoinAddressLikeRecordIdRejected("Bitcoin address-like record identifier is rejected"),
    TransactionLikeRecordIdRejected("transaction-id-like record identifier is rejected"),
    TraversalRejected("traversal-bearing storage input is rejected"),
    EmptyRecordIdRejected("empty record identifier is rejected"),
    UnsupportedRecordIdRejected("unsupported record identifier is rejected"),
    PlaintextPayloadRejected("plaintext-like payload input is rejected"),
    CiphertextPayloadRejected("ciphertext-like payload input is rejected"),
    UserConsentOverrideRejected("user consent cannot override disabled storage"),
    WarningOnlyEvidenceRejected("warning-only evidence cannot enable storage"),
}

enum class SkaldVaultV1VaultStorageOperationBlocker(val label: String) {
    MissingArtifactLocationEvidence("planned artifact-location evidence is missing"),
    MissingStorageSafetyPreflightEvidence("storage safety preflight evidence is missing"),
    RejectedArtifactLocationEvidence("planned artifact-location evidence was rejected upstream"),
    RejectedStorageSafetyPreflightEvidence("storage safety preflight evidence was rejected upstream"),
    WarningOnlyEvidenceRejected("warning-only evidence cannot enable storage"),
    UserConsentOverrideRejected("user consent cannot override disabled storage"),
    AcceptedButFileIoBlocked("vault storage service is not usable for file I/O"),
    AcceptedButPersistenceBlocked("vault storage service is not usable for persistence"),
    ProviderSelectionBlocked("provider selection remains blocked"),
    VaultCreationBlocked("vault creation remains unavailable"),
    VaultUnlockBlocked("vault unlock remains unavailable"),
    VaultPersistenceBlocked("vault persistence remains unavailable"),
    SettingsPersistenceMissing("Settings persistence remains unavailable"),
    StorageImplementationMissing("storage implementation remains unavailable"),
    ManifestReadWriteMissing("manifest read/write remains unavailable"),
    StorageIndexReadWriteMissing("storage index read/write remains unavailable"),
    RecordReadWriteMissing("record read/write remains unavailable"),
    RecordListingDeletionQuarantineRecoveryMissing("record listing, deletion, quarantine, and recovery remain unavailable"),
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
    StorageFailureMappingMissing("storage failure mapping remains model-only"),
    AntiRollbackAnchorMissing("anti-rollback anchor remains unavailable"),
    MainnetDisabled("mainnet remains unavailable"),
}

enum class SkaldVaultV1VaultStorageOperationWarning(val label: String) {
    EvidenceOnly("disabled vault storage service facade is evidence only"),
    EveryOperationDisabled("every modeled storage operation returns disabled evidence"),
    PlannedLocationsAreNotPaths("planned artifact locations are not platform paths"),
    StorageSafetyPreflightDoesNotRunChecks("storage safety preflight does not run filesystem checks"),
    NoStorageHandles("no storage handles, streams, repositories, or database handles are returned"),
    NoManifestStorageIndexOrRecordAccess("manifest, storage-index, and record access remain unavailable"),
    NoAtomicWriteOrCrashRecovery("atomic write and crash recovery remain unavailable"),
    NoSettingsPersistence("Settings persistence remains unavailable"),
    PersistenceStillDisabled("vault persistence remains disabled"),
    ProviderSelectionStillDisabled("provider selection remains disabled"),
    UserConsentCannotOverrideDisabledStorage("user consent cannot override disabled storage"),
    PayloadsRedacted("payload placeholders are redacted and contain no data"),
}

data class SkaldVaultV1VaultStorageOperationCapability(
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
        val StillDisabled = SkaldVaultV1VaultStorageOperationCapability(
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

enum class SkaldVaultV1VaultStoragePayloadPurpose(val label: String) {
    DisabledContainerWritePlaceholder("disabled container write placeholder"),
    DisabledManifestWritePlaceholder("disabled manifest write placeholder"),
    DisabledStorageIndexWritePlaceholder("disabled storage index write placeholder"),
    DisabledRecordWritePlaceholder("disabled record write placeholder"),
    DisabledRecoveryPlaceholder("disabled recovery placeholder"),
}

class SkaldVaultV1VaultStoragePayloadPlaceholder private constructor(
    val purpose: SkaldVaultV1VaultStoragePayloadPurpose,
) {
    val containsPlaintext: Boolean = false
    val containsCiphertext: Boolean = false
    val byteLengthExposed: Boolean = false

    override fun toString(): String =
        "SkaldVaultV1VaultStoragePayloadPlaceholder(" +
            "purpose=$purpose, " +
            "payload=<redacted>, " +
            "containsPlaintext=false, " +
            "containsCiphertext=false, " +
            "byteLengthExposed=false" +
            ")"

    companion object {
        fun disabledPlaceholder(
            purpose: SkaldVaultV1VaultStoragePayloadPurpose,
        ): SkaldVaultV1VaultStoragePayloadPlaceholder =
            SkaldVaultV1VaultStoragePayloadPlaceholder(purpose)
    }
}

sealed class SkaldVaultV1VaultStorageRecordDescriptorResult<out T> {
    data class Accepted<out T>(val value: T) : SkaldVaultV1VaultStorageRecordDescriptorResult<T>() {
        override fun toString(): String = "Accepted(value=<redacted-vault-storage-record-descriptor>)"
    }

    data class Rejected(
        val reason: SkaldVaultV1VaultStorageOperationFailureReason,
        val safeMessage: String = reason.label,
    ) : SkaldVaultV1VaultStorageRecordDescriptorResult<Nothing>() {
        override fun toString(): String =
            "Rejected(reason=$reason, safeMessage=$safeMessage, rawRecordIdentifier=<redacted>)"
    }
}

class SkaldVaultV1VaultStorageRecordDescriptor private constructor(
    private val recordIdentifier: String,
) {
    fun testOnlyRecordIdentifier(): String = recordIdentifier

    override fun toString(): String =
        "SkaldVaultV1VaultStorageRecordDescriptor(recordIdentifier=<redacted>)"

    companion object {
        fun safeTestFixture(recordIdentifier: String): SkaldVaultV1VaultStorageRecordDescriptor =
            when (val result = rawCandidate(recordIdentifier)) {
                is SkaldVaultV1VaultStorageRecordDescriptorResult.Accepted -> result.value
                is SkaldVaultV1VaultStorageRecordDescriptorResult.Rejected -> error(result.safeMessage)
            }

        fun rawCandidate(
            recordIdentifier: String?,
        ): SkaldVaultV1VaultStorageRecordDescriptorResult<SkaldVaultV1VaultStorageRecordDescriptor> {
            val reason = classifyStorageInput(recordIdentifier)
            return if (reason == null) {
                SkaldVaultV1VaultStorageRecordDescriptorResult.Accepted(
                    SkaldVaultV1VaultStorageRecordDescriptor(recordIdentifier ?: ""),
                )
            } else {
                SkaldVaultV1VaultStorageRecordDescriptorResult.Rejected(reason)
            }
        }
    }
}

class SkaldVaultV1VaultStorageOperationRequest private constructor(
    val operation: SkaldVaultV1VaultStorageOperation,
    val source: SkaldVaultV1VaultStorageOperationSource,
    private val artifactEvidence: SkaldVaultV1PlatformPathConstructionEvidence?,
    private val preflightEvidence: SkaldVaultV1StorageSafetyPreflightEvidence?,
    private val artifactFailureReason: SkaldVaultV1PlatformPathConstructionFailureReason?,
    private val preflightFailureReason: SkaldVaultV1StorageSafetyPreflightFailureReason?,
    private val recordDescriptor: SkaldVaultV1VaultStorageRecordDescriptor?,
    private val payload: SkaldVaultV1VaultStoragePayloadPlaceholder?,
    private val rawCandidate: String?,
) {
    fun testOnlyRawCandidate(): String? = rawCandidate

    internal fun artifactEvidenceOrNull(): SkaldVaultV1PlatformPathConstructionEvidence? = artifactEvidence

    internal fun preflightEvidenceOrNull(): SkaldVaultV1StorageSafetyPreflightEvidence? = preflightEvidence

    internal fun artifactFailureReasonOrNull(): SkaldVaultV1PlatformPathConstructionFailureReason? =
        artifactFailureReason

    internal fun preflightFailureReasonOrNull(): SkaldVaultV1StorageSafetyPreflightFailureReason? =
        preflightFailureReason

    internal fun recordDescriptorOrNull(): SkaldVaultV1VaultStorageRecordDescriptor? = recordDescriptor

    internal fun payloadOrNull(): SkaldVaultV1VaultStoragePayloadPlaceholder? = payload

    internal fun rawCandidateOrNull(): String? = rawCandidate

    override fun toString(): String =
        "SkaldVaultV1VaultStorageOperationRequest(" +
            "operation=$operation, " +
            "source=$source, " +
            "artifactEvidence=<redacted>, " +
            "preflightEvidence=<redacted>, " +
            "recordDescriptor=<redacted>, " +
            "payload=<redacted>, " +
            "rawCandidate=<redacted>" +
            ")"

    companion object {
        fun noEvidence(
            operation: SkaldVaultV1VaultStorageOperation,
        ): SkaldVaultV1VaultStorageOperationRequest =
            SkaldVaultV1VaultStorageOperationRequest(
                operation = operation,
                source = SkaldVaultV1VaultStorageOperationSource.NoEvidence,
                artifactEvidence = null,
                preflightEvidence = null,
                artifactFailureReason = null,
                preflightFailureReason = null,
                recordDescriptor = null,
                payload = null,
                rawCandidate = null,
            )

        fun fromEvidence(
            operation: SkaldVaultV1VaultStorageOperation,
            artifactEvidence: SkaldVaultV1PlatformPathConstructionEvidence?,
            preflightEvidence: SkaldVaultV1StorageSafetyPreflightEvidence?,
            recordDescriptor: SkaldVaultV1VaultStorageRecordDescriptor? = null,
            payload: SkaldVaultV1VaultStoragePayloadPlaceholder? = null,
        ): SkaldVaultV1VaultStorageOperationRequest =
            SkaldVaultV1VaultStorageOperationRequest(
                operation = operation,
                source = when {
                    artifactEvidence == null -> SkaldVaultV1VaultStorageOperationSource.MissingArtifactLocationEvidence
                    preflightEvidence == null ->
                        SkaldVaultV1VaultStorageOperationSource.MissingStorageSafetyPreflightEvidence
                    else -> SkaldVaultV1VaultStorageOperationSource.ArtifactAndPreflightEvidence
                },
                artifactEvidence = artifactEvidence,
                preflightEvidence = preflightEvidence,
                artifactFailureReason = null,
                preflightFailureReason = null,
                recordDescriptor = recordDescriptor,
                payload = payload,
                rawCandidate = null,
            )

        fun fromBoundaryResults(
            operation: SkaldVaultV1VaultStorageOperation,
            artifactResult: SkaldVaultV1PlatformPathConstructionResult<SkaldVaultV1PlatformPathConstructionEvidence>,
            preflightResult: SkaldVaultV1StorageSafetyPreflightResult<SkaldVaultV1StorageSafetyPreflightEvidence>,
            recordDescriptor: SkaldVaultV1VaultStorageRecordDescriptor? = null,
            payload: SkaldVaultV1VaultStoragePayloadPlaceholder? = null,
        ): SkaldVaultV1VaultStorageOperationRequest {
            val artifactEvidence = when (artifactResult) {
                is SkaldVaultV1PlatformPathConstructionResult.Accepted -> artifactResult.value
                is SkaldVaultV1PlatformPathConstructionResult.Rejected -> null
            }
            val artifactFailureReason = when (artifactResult) {
                is SkaldVaultV1PlatformPathConstructionResult.Accepted -> null
                is SkaldVaultV1PlatformPathConstructionResult.Rejected -> artifactResult.reason
            }
            val preflightEvidence = when (preflightResult) {
                is SkaldVaultV1StorageSafetyPreflightResult.Accepted -> preflightResult.value
                is SkaldVaultV1StorageSafetyPreflightResult.Rejected -> null
            }
            val preflightFailureReason = when (preflightResult) {
                is SkaldVaultV1StorageSafetyPreflightResult.Accepted -> null
                is SkaldVaultV1StorageSafetyPreflightResult.Rejected -> preflightResult.reason
            }
            val source = when {
                artifactFailureReason != null -> SkaldVaultV1VaultStorageOperationSource.RejectedArtifactLocationEvidence
                preflightFailureReason != null ->
                    SkaldVaultV1VaultStorageOperationSource.RejectedStorageSafetyPreflightEvidence
                artifactEvidence == null -> SkaldVaultV1VaultStorageOperationSource.MissingArtifactLocationEvidence
                preflightEvidence == null ->
                    SkaldVaultV1VaultStorageOperationSource.MissingStorageSafetyPreflightEvidence
                else -> SkaldVaultV1VaultStorageOperationSource.ArtifactAndPreflightEvidence
            }
            return SkaldVaultV1VaultStorageOperationRequest(
                operation = operation,
                source = source,
                artifactEvidence = artifactEvidence,
                preflightEvidence = preflightEvidence,
                artifactFailureReason = artifactFailureReason,
                preflightFailureReason = preflightFailureReason,
                recordDescriptor = recordDescriptor,
                payload = payload,
                rawCandidate = null,
            )
        }

        fun rawStorageInputCandidate(
            operation: SkaldVaultV1VaultStorageOperation,
            rawCandidate: String?,
        ): SkaldVaultV1VaultStorageOperationRequest =
            SkaldVaultV1VaultStorageOperationRequest(
                operation = operation,
                source = SkaldVaultV1VaultStorageOperationSource.RawStorageInputCandidate,
                artifactEvidence = null,
                preflightEvidence = null,
                artifactFailureReason = null,
                preflightFailureReason = null,
                recordDescriptor = null,
                payload = null,
                rawCandidate = rawCandidate,
            )
    }
}

class SkaldVaultV1VaultStorageOperationToken internal constructor(
    val operation: SkaldVaultV1VaultStorageOperation,
    val artifactCount: Int,
    val safetyGateCount: Int,
) {
    val containsPlatformLocationText: Boolean = false
    val containsRecordIdentifier: Boolean = false
    val containsPayload: Boolean = false
    val usableForFileIo: Boolean = false
    val usableForPersistence: Boolean = false

    override fun toString(): String =
        "SkaldVaultV1VaultStorageOperationToken(" +
            "operation=$operation, " +
            "artifactCount=$artifactCount, " +
            "safetyGateCount=$safetyGateCount, " +
            "artifactLocations=<redacted>, " +
            "recordIdentifier=<redacted>, " +
            "payload=<redacted>, " +
            "containsPlatformLocationText=false, " +
            "containsRecordIdentifier=false, " +
            "containsPayload=false, " +
            "usableForFileIo=false, " +
            "usableForPersistence=false" +
            ")"
}

class SkaldVaultV1VaultStorageDisabledEvidence internal constructor(
    val policyId: String,
    val policyVersion: Int,
    val facadeStatus: SkaldVaultV1VaultStorageFacadeStatus,
    val operationStatus: SkaldVaultV1VaultStorageOperationStatus,
    val operation: SkaldVaultV1VaultStorageOperation,
    val source: SkaldVaultV1VaultStorageOperationSource,
    val capability: SkaldVaultV1VaultStorageOperationCapability,
    private val operationMarker: SkaldVaultV1VaultStorageOperationToken,
    private val plannedLocations: List<SkaldVaultV1PlannedPlatformArtifactLocation>,
    val preflightGateEvidence: List<SkaldVaultV1StorageSafetyGateEvidence>,
    val blockers: Set<SkaldVaultV1VaultStorageOperationBlocker>,
    val warnings: Set<SkaldVaultV1VaultStorageOperationWarning>,
    val disabledStorageServiceFacadeModeled: Boolean = true,
    val storageServiceFacadeStillDisabled: Boolean = true,
    val storageServiceOperationsFailClosed: Boolean = true,
    val storageServiceDoesNotUseFilesystem: Boolean = true,
    val storageServiceDoesNotEnablePersistence: Boolean = true,
    val storageServiceDoesNotEnableProviderSelection: Boolean = true,
    val storageOperationFailureVocabularyModeled: Boolean = true,
    val plannedArtifactLocationEvidenceConsumed: Boolean = false,
    val storageSafetyPreflightEvidenceConsumed: Boolean = false,
    val noOperationReturnsSuccess: Boolean = true,
    val platformPathObjectReturned: Boolean = false,
    val payloadExposedByDefault: Boolean = false,
) {
    val token: SkaldVaultV1VaultStorageOperationToken
        get() = operationMarker

    fun locations(): List<SkaldVaultV1PlannedPlatformArtifactLocation> = plannedLocations

    override fun toString(): String =
        "SkaldVaultV1VaultStorageDisabledEvidence(" +
            "policyId=$policyId, " +
            "policyVersion=$policyVersion, " +
            "facadeStatus=$facadeStatus, " +
            "operationStatus=$operationStatus, " +
            "operation=$operation, " +
            "source=$source, " +
            "artifactCount=${plannedLocations.size}, " +
            "preflightGateCount=${preflightGateEvidence.size}, " +
            "artifactLocations=<redacted>, " +
            "recordIdentifier=<redacted>, " +
            "payload=<redacted>, " +
            "platformPathObjectReturned=false, " +
            "payloadExposedByDefault=false" +
            ")"
}

sealed class SkaldVaultV1VaultStorageOperationResult<out T> {
    abstract val success: Boolean

    data class Disabled<out T>(val value: T) : SkaldVaultV1VaultStorageOperationResult<T>() {
        override val success: Boolean = false

        override fun toString(): String = "Disabled(value=<redacted-disabled-storage-operation-evidence>)"
    }

    data class Rejected(
        val reason: SkaldVaultV1VaultStorageOperationFailureReason,
        val status: SkaldVaultV1VaultStorageOperationStatus,
        val source: SkaldVaultV1VaultStorageOperationSource,
        val safeMessage: String = reason.label,
        val artifactLocationFailureReason: SkaldVaultV1PlatformPathConstructionFailureReason? = null,
        val storageSafetyPreflightFailureReason: SkaldVaultV1StorageSafetyPreflightFailureReason? = null,
    ) : SkaldVaultV1VaultStorageOperationResult<Nothing>() {
        override val success: Boolean = false

        override fun toString(): String =
            "Rejected(" +
                "reason=$reason, " +
                "status=$status, " +
                "source=$source, " +
                "safeMessage=$safeMessage, " +
                "artifactLocationFailureReason=$artifactLocationFailureReason, " +
                "storageSafetyPreflightFailureReason=$storageSafetyPreflightFailureReason, " +
                "rawStorageInput=<redacted>" +
                ")"
    }
}

object SkaldVaultV1DisabledStorageServiceFacade : SkaldVaultV1VaultStorageService {
    const val POLICY_ID = "skald-vault-v1-disabled-storage-service-facade-v1"
    const val POLICY_VERSION = 1

    override fun status(): SkaldVaultV1VaultStorageFacadeStatus =
        SkaldVaultV1VaultStorageFacadeStatus.StillDisabled

    override fun modeledOperations(): Set<SkaldVaultV1VaultStorageOperation> =
        SkaldVaultV1VaultStorageOperation.entries.toSet()

    override fun perform(
        request: SkaldVaultV1VaultStorageOperationRequest,
    ): SkaldVaultV1VaultStorageOperationResult<SkaldVaultV1VaultStorageDisabledEvidence> =
        when (request.source) {
            SkaldVaultV1VaultStorageOperationSource.RawStorageInputCandidate ->
                rejectRawCandidate(request)
            else -> disabledEvidence(request)
        }

    private fun rejectRawCandidate(
        request: SkaldVaultV1VaultStorageOperationRequest,
    ): SkaldVaultV1VaultStorageOperationResult.Rejected =
        SkaldVaultV1VaultStorageOperationResult.Rejected(
            reason = classifyStorageInput(request.rawCandidateOrNull())
                ?: SkaldVaultV1VaultStorageOperationFailureReason.RawStorageInputRejected,
            status = SkaldVaultV1VaultStorageOperationStatus.RawStorageInputRejected,
            source = request.source,
        )

    private fun disabledEvidence(
        request: SkaldVaultV1VaultStorageOperationRequest,
    ): SkaldVaultV1VaultStorageOperationResult.Disabled<SkaldVaultV1VaultStorageDisabledEvidence> {
        val locations = request.artifactEvidenceOrNull()?.locations().orEmpty()
        val gates = request.preflightEvidenceOrNull()?.gateEvidence.orEmpty()
        val blockers = buildSet {
            addAll(disabledCapabilityBlockers())
            when (request.source) {
                SkaldVaultV1VaultStorageOperationSource.NoEvidence -> {
                    add(SkaldVaultV1VaultStorageOperationBlocker.MissingArtifactLocationEvidence)
                    add(SkaldVaultV1VaultStorageOperationBlocker.MissingStorageSafetyPreflightEvidence)
                }
                SkaldVaultV1VaultStorageOperationSource.MissingArtifactLocationEvidence ->
                    add(SkaldVaultV1VaultStorageOperationBlocker.MissingArtifactLocationEvidence)
                SkaldVaultV1VaultStorageOperationSource.MissingStorageSafetyPreflightEvidence ->
                    add(SkaldVaultV1VaultStorageOperationBlocker.MissingStorageSafetyPreflightEvidence)
                SkaldVaultV1VaultStorageOperationSource.RejectedArtifactLocationEvidence ->
                    add(SkaldVaultV1VaultStorageOperationBlocker.RejectedArtifactLocationEvidence)
                SkaldVaultV1VaultStorageOperationSource.RejectedStorageSafetyPreflightEvidence ->
                    add(SkaldVaultV1VaultStorageOperationBlocker.RejectedStorageSafetyPreflightEvidence)
                SkaldVaultV1VaultStorageOperationSource.ArtifactAndPreflightEvidence -> Unit
                SkaldVaultV1VaultStorageOperationSource.RawStorageInputCandidate -> Unit
            }
            val preflight = request.preflightEvidenceOrNull()
            if (preflight?.blockers?.contains(SkaldVaultV1StorageSafetyBlocker.WarningOnlyEvidenceRejected) == true) {
                add(SkaldVaultV1VaultStorageOperationBlocker.WarningOnlyEvidenceRejected)
            }
            if (preflight?.blockers?.contains(SkaldVaultV1StorageSafetyBlocker.UserConsentOverrideRejected) == true) {
                add(SkaldVaultV1VaultStorageOperationBlocker.UserConsentOverrideRejected)
            }
        }
        return SkaldVaultV1VaultStorageOperationResult.Disabled(
            SkaldVaultV1VaultStorageDisabledEvidence(
                policyId = POLICY_ID,
                policyVersion = POLICY_VERSION,
                facadeStatus = status(),
                operationStatus = operationStatusFor(request.source),
                operation = request.operation,
                source = request.source,
                capability = SkaldVaultV1VaultStorageOperationCapability.StillDisabled,
                operationMarker = SkaldVaultV1VaultStorageOperationToken(
                    operation = request.operation,
                    artifactCount = locations.size,
                    safetyGateCount = gates.size,
                ),
                plannedLocations = locations,
                preflightGateEvidence = gates,
                blockers = blockers,
                warnings = SkaldVaultV1VaultStorageOperationWarning.entries.toSet(),
                plannedArtifactLocationEvidenceConsumed = locations.isNotEmpty(),
                storageSafetyPreflightEvidenceConsumed = gates.isNotEmpty(),
            ),
        )
    }

    private fun operationStatusFor(
        source: SkaldVaultV1VaultStorageOperationSource,
    ): SkaldVaultV1VaultStorageOperationStatus =
        when (source) {
            SkaldVaultV1VaultStorageOperationSource.NoEvidence ->
                SkaldVaultV1VaultStorageOperationStatus.NoEvidenceAvailable
            SkaldVaultV1VaultStorageOperationSource.MissingArtifactLocationEvidence ->
                SkaldVaultV1VaultStorageOperationStatus.MissingArtifactLocationEvidence
            SkaldVaultV1VaultStorageOperationSource.MissingStorageSafetyPreflightEvidence ->
                SkaldVaultV1VaultStorageOperationStatus.MissingStorageSafetyPreflightEvidence
            SkaldVaultV1VaultStorageOperationSource.RejectedArtifactLocationEvidence ->
                SkaldVaultV1VaultStorageOperationStatus.ArtifactLocationEvidenceRejected
            SkaldVaultV1VaultStorageOperationSource.RejectedStorageSafetyPreflightEvidence ->
                SkaldVaultV1VaultStorageOperationStatus.StorageSafetyPreflightRejected
            SkaldVaultV1VaultStorageOperationSource.RawStorageInputCandidate ->
                SkaldVaultV1VaultStorageOperationStatus.RawStorageInputRejected
            SkaldVaultV1VaultStorageOperationSource.ArtifactAndPreflightEvidence ->
                SkaldVaultV1VaultStorageOperationStatus.StorageOperationDisabled
        }

    private fun disabledCapabilityBlockers(): Set<SkaldVaultV1VaultStorageOperationBlocker> =
        setOf(
            SkaldVaultV1VaultStorageOperationBlocker.AcceptedButFileIoBlocked,
            SkaldVaultV1VaultStorageOperationBlocker.AcceptedButPersistenceBlocked,
            SkaldVaultV1VaultStorageOperationBlocker.ProviderSelectionBlocked,
            SkaldVaultV1VaultStorageOperationBlocker.VaultCreationBlocked,
            SkaldVaultV1VaultStorageOperationBlocker.VaultUnlockBlocked,
            SkaldVaultV1VaultStorageOperationBlocker.VaultPersistenceBlocked,
            SkaldVaultV1VaultStorageOperationBlocker.SettingsPersistenceMissing,
            SkaldVaultV1VaultStorageOperationBlocker.StorageImplementationMissing,
            SkaldVaultV1VaultStorageOperationBlocker.ManifestReadWriteMissing,
            SkaldVaultV1VaultStorageOperationBlocker.StorageIndexReadWriteMissing,
            SkaldVaultV1VaultStorageOperationBlocker.RecordReadWriteMissing,
            SkaldVaultV1VaultStorageOperationBlocker.RecordListingDeletionQuarantineRecoveryMissing,
            SkaldVaultV1VaultStorageOperationBlocker.AtomicWriteMissing,
            SkaldVaultV1VaultStorageOperationBlocker.CrashRecoveryMissing,
            SkaldVaultV1VaultStorageOperationBlocker.SecureSecretStorageMissing,
            SkaldVaultV1VaultStorageOperationBlocker.SecureMetadataStorageMissing,
            SkaldVaultV1VaultStorageOperationBlocker.RealPathConstructionMissing,
            SkaldVaultV1VaultStorageOperationBlocker.AbsolutePathConstructionMissing,
            SkaldVaultV1VaultStorageOperationBlocker.RealPathContainmentMissing,
            SkaldVaultV1VaultStorageOperationBlocker.SymlinkSafetyMissing,
            SkaldVaultV1VaultStorageOperationBlocker.PermissionCheckMissing,
            SkaldVaultV1VaultStorageOperationBlocker.OwnershipCheckMissing,
            SkaldVaultV1VaultStorageOperationBlocker.DurabilityMissing,
            SkaldVaultV1VaultStorageOperationBlocker.StorageFailureMappingMissing,
            SkaldVaultV1VaultStorageOperationBlocker.AntiRollbackAnchorMissing,
            SkaldVaultV1VaultStorageOperationBlocker.MainnetDisabled,
        )
}

private fun classifyStorageInput(
    raw: String?,
): SkaldVaultV1VaultStorageOperationFailureReason? {
    if (raw == null || raw.isBlank()) {
        return SkaldVaultV1VaultStorageOperationFailureReason.EmptyRecordIdRejected
    }
    val lower = raw.lowercase()
    return when {
        lower.startsWith("file:") ||
            lower.startsWith("http:") ||
            lower.startsWith("https:") ||
            lower.startsWith("content:") ->
            SkaldVaultV1VaultStorageOperationFailureReason.LinkLikeInputRejected
        looksLikeWindowsOrUnc(raw) || raw.startsWith("/") ->
            SkaldVaultV1VaultStorageOperationFailureReason.RawAbsoluteLocationInputRejected
        raw.contains("..") ->
            SkaldVaultV1VaultStorageOperationFailureReason.TraversalRejected
        raw.contains("/") || raw.contains("\\") ->
            SkaldVaultV1VaultStorageOperationFailureReason.RawRelativeLocationInputRejected
        lower.contains("file-object") ||
            lower.contains("path-object") ||
            lower.contains("stream") ||
            lower.contains("database") ||
            lower.contains("settings") ->
            SkaldVaultV1VaultStorageOperationFailureReason.PlatformObjectLikeInputRejected
        lower.contains("plaintext") ->
            SkaldVaultV1VaultStorageOperationFailureReason.PlaintextPayloadRejected
        lower.contains("ciphertext") ->
            SkaldVaultV1VaultStorageOperationFailureReason.CiphertextPayloadRejected
        looksLikeSecretOrCredential(lower) ->
            SkaldVaultV1VaultStorageOperationFailureReason.SecretMaterialRejected
        looksLikePrivateKeyMaterial(raw, lower) ->
            SkaldVaultV1VaultStorageOperationFailureReason.WalletMaterialRejected
        looksLikeBitcoinAddress(lower) ->
            SkaldVaultV1VaultStorageOperationFailureReason.BitcoinAddressLikeRecordIdRejected
        containsLongHexSegment(raw) ->
            SkaldVaultV1VaultStorageOperationFailureReason.TransactionLikeRecordIdRejected
        hasUnsupportedStorageIdentifierCharacters(raw) ->
            SkaldVaultV1VaultStorageOperationFailureReason.UnsupportedRecordIdRejected
        else -> null
    }
}

private fun looksLikeWindowsOrUnc(value: String): Boolean =
    (
        value.length >= 3 &&
            value[1] == ':' &&
            value[2] == '\\'
        ) ||
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

private fun hasUnsupportedStorageIdentifierCharacters(raw: String): Boolean =
    raw.any { char ->
        !(char in 'a'..'z' ||
            char in 'A'..'Z' ||
            char in '0'..'9' ||
            char == '_' ||
            char == '-')
    }

private fun Char.isHex(): Boolean =
    this in '0'..'9' || this in 'a'..'f' || this in 'A'..'F'
