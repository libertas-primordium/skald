package com.libertasprimordium.skald.security

interface SkaldVaultV1PlatformPathConstructionBoundary {
    fun plan(
        request: SkaldVaultV1PlatformPathConstructionRequest,
    ): SkaldVaultV1PlatformPathConstructionResult<SkaldVaultV1PlatformPathConstructionEvidence>
}

enum class SkaldVaultV1PlatformPathConstructionSource(val label: String) {
    NoEvidence("no platform path-construction evidence supplied"),
    RootResolverAndLogicalLayout("platform root resolver evidence plus logical storage layout"),
    RootEvidenceOnly("platform root evidence without logical storage layout"),
    LogicalLayoutOnly("logical storage layout without platform root evidence"),
    RejectedRootEvidence("rejected platform root evidence"),
    RawPlatformPathCandidate("raw platform path candidate"),
    ExplicitSegmentValues("explicit relative segment values"),
}

enum class SkaldVaultV1PlatformPathConstructionStatus(val label: String) {
    NoEvidenceAvailable("no path-construction evidence available"),
    MissingPlatformRootEvidence("missing platform root evidence"),
    MissingLogicalArtifactEvidence("missing logical artifact evidence"),
    RootEvidenceRejected("platform root evidence rejected"),
    ArtifactSegmentEvidenceRejected("artifact segment evidence rejected"),
    RawPlatformPathInputRejected("raw platform path input rejected"),
    PlannedArtifactLocationsAcceptedStillDisabled(
        "planned artifact locations accepted as still-disabled evidence",
    ),
}

enum class SkaldVaultV1PlatformPathConstructionArtifactKind(val label: String) {
    CurrentContainer("current container artifact"),
    CurrentManifest("current manifest artifact"),
    CurrentStorageIndex("current storage index artifact"),
    RecordArtifact("record artifact"),
    TempContainer("temporary container artifact"),
    TempManifest("temporary manifest artifact"),
    TempStorageIndex("temporary storage-index artifact"),
    QuarantineArtifact("quarantine artifact"),
    RecoveryMetadata("recovery metadata artifact"),
    ExplicitSegmentProbe("explicit segment probe artifact"),
}

enum class SkaldVaultV1PlatformPathConstructionFailureReason(val label: String) {
    NoCandidateEvidenceSupplied("no candidate path-construction evidence supplied"),
    MissingPlatformRootEvidence("missing platform root evidence"),
    MissingLogicalArtifactEvidence("missing logical artifact evidence"),
    RejectedPlatformRootEvidence("platform root evidence was already rejected"),
    UnsupportedRootEvidence("platform root evidence is not supported for path construction"),
    ContainmentPlannerRejected("path-containment planner rejected the evidence"),
    RawPlatformPathInputRejected("raw platform path input is not accepted"),
    RawAbsolutePathInputRejected("raw absolute path input is not accepted"),
    RawRelativePathInputRejected("raw relative path input is not accepted"),
    UriOrUrlInputRejected("URI-like or URL-like path input is not accepted"),
    WindowsOrUncPathInputRejected("Windows or UNC path input is not accepted"),
    FileOrPathObjectInputRejected("File or Path object input is not accepted by this API"),
    SecretMaterialRejected("secret-looking artifact or path material is rejected"),
    WalletMaterialRejected("wallet/address/key-looking artifact or path material is rejected"),
    BitcoinAddressLikeArtifactRejected("Bitcoin address-like artifact material is rejected"),
    TransactionLikeArtifactRejected("transaction-id-like artifact material is rejected"),
    PathTraversalRejected("path traversal is rejected"),
    EmptyArtifactSegmentRejected("empty artifact segment is rejected"),
    UnsupportedArtifactSegmentRejected("unsupported artifact segment is rejected"),
    AcceptedButPersistenceBlocked("accepted location evidence remains blocked from persistence"),
}

enum class SkaldVaultV1PlatformPathConstructionBlocker(val label: String) {
    AcceptedButFileIoBlocked("accepted location evidence is not usable for file I/O"),
    AcceptedButPersistenceBlocked("accepted location evidence is not usable for persistence"),
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
    PermissionOwnershipMissing("permission and ownership checks remain unverified"),
    DurabilityMissing("durability remains unverified"),
    AntiRollbackAnchorMissing("anti-rollback anchor remains unavailable"),
    MainnetDisabled("mainnet remains unavailable"),
}

enum class SkaldVaultV1PlatformPathConstructionWarning(val label: String) {
    EvidenceOnly("path-construction evidence is a still-disabled model only"),
    TokenIsNotPlatformPath("planned location token is not a platform path"),
    RootStringsRedacted("root strings are redacted by default"),
    LogicalSegmentsOnly("planned locations contain logical relative segments only"),
    AndroidRootStillAppPrivateOnly("Android v1 remains app-private internal only"),
    LinuxCustomRootsStillSettingsFutureWork("Linux custom roots remain future Settings work"),
}

data class SkaldVaultV1PlatformPathConstructionCapability(
    val usableForFileIo: Boolean,
    val usableForPersistence: Boolean,
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
        val StillDisabled = SkaldVaultV1PlatformPathConstructionCapability(
            usableForFileIo = false,
            usableForPersistence = false,
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

class SkaldVaultV1PlatformPathConstructionRequest private constructor(
    val source: SkaldVaultV1PlatformPathConstructionSource,
    private val rootEvidence: SkaldVaultV1PlatformRootResolverEvidence?,
    private val rejectedRootReason: SkaldVaultV1PlatformRootResolverFailureReason?,
    private val layoutPlan: SkaldVaultV1StorageLayoutPlan?,
    private val rawCandidate: String?,
    private val explicitArtifactKind: SkaldVaultV1PlatformPathConstructionArtifactKind?,
    private val explicitSegmentValues: List<String>?,
) {
    fun testOnlyRawCandidate(): String? = rawCandidate

    fun testOnlyExplicitSegmentValues(): List<String>? = explicitSegmentValues

    internal fun rootEvidenceOrNull(): SkaldVaultV1PlatformRootResolverEvidence? = rootEvidence

    internal fun rejectedRootReasonOrNull(): SkaldVaultV1PlatformRootResolverFailureReason? = rejectedRootReason

    internal fun layoutPlanOrNull(): SkaldVaultV1StorageLayoutPlan? = layoutPlan

    internal fun rawCandidateOrNull(): String? = rawCandidate

    internal fun explicitArtifactKindOrNull(): SkaldVaultV1PlatformPathConstructionArtifactKind? =
        explicitArtifactKind

    internal fun explicitSegmentValuesOrNull(): List<String>? = explicitSegmentValues

    override fun toString(): String =
        "SkaldVaultV1PlatformPathConstructionRequest(" +
            "source=$source, " +
            "rootEvidence=<redacted>, " +
            "rejectedRootReason=$rejectedRootReason, " +
            "layoutPlan=<logical-layout-redacted>, " +
            "rawCandidate=<redacted>, " +
            "explicitArtifactKind=$explicitArtifactKind, " +
            "explicitSegmentValues=<redacted>" +
            ")"

    companion object {
        fun noEvidence(): SkaldVaultV1PlatformPathConstructionRequest =
            SkaldVaultV1PlatformPathConstructionRequest(
                source = SkaldVaultV1PlatformPathConstructionSource.NoEvidence,
                rootEvidence = null,
                rejectedRootReason = null,
                layoutPlan = null,
                rawCandidate = null,
                explicitArtifactKind = null,
                explicitSegmentValues = null,
            )

        fun fromRootResultAndLayout(
            rootResult:
                SkaldVaultV1PlatformRootResolverResult<SkaldVaultV1PlatformRootResolverEvidence>,
            layoutPlan: SkaldVaultV1StorageLayoutPlan?,
        ): SkaldVaultV1PlatformPathConstructionRequest =
            when (rootResult) {
                is SkaldVaultV1PlatformRootResolverResult.Accepted -> fromRootEvidenceAndLayout(
                    rootEvidence = rootResult.value,
                    layoutPlan = layoutPlan,
                )
                is SkaldVaultV1PlatformRootResolverResult.Rejected ->
                    SkaldVaultV1PlatformPathConstructionRequest(
                        source = SkaldVaultV1PlatformPathConstructionSource.RejectedRootEvidence,
                        rootEvidence = null,
                        rejectedRootReason = rootResult.reason,
                        layoutPlan = layoutPlan,
                        rawCandidate = null,
                        explicitArtifactKind = null,
                        explicitSegmentValues = null,
                    )
            }

        fun fromRootEvidenceAndLayout(
            rootEvidence: SkaldVaultV1PlatformRootResolverEvidence?,
            layoutPlan: SkaldVaultV1StorageLayoutPlan?,
        ): SkaldVaultV1PlatformPathConstructionRequest =
            SkaldVaultV1PlatformPathConstructionRequest(
                source = when {
                    rootEvidence != null && layoutPlan != null ->
                        SkaldVaultV1PlatformPathConstructionSource.RootResolverAndLogicalLayout
                    rootEvidence != null ->
                        SkaldVaultV1PlatformPathConstructionSource.RootEvidenceOnly
                    layoutPlan != null ->
                        SkaldVaultV1PlatformPathConstructionSource.LogicalLayoutOnly
                    else -> SkaldVaultV1PlatformPathConstructionSource.NoEvidence
                },
                rootEvidence = rootEvidence,
                rejectedRootReason = null,
                layoutPlan = layoutPlan,
                rawCandidate = null,
                explicitArtifactKind = null,
                explicitSegmentValues = null,
            )

        fun fromRootEvidenceAndSegmentValues(
            rootEvidence: SkaldVaultV1PlatformRootResolverEvidence?,
            artifactKind: SkaldVaultV1PlatformPathConstructionArtifactKind,
            segmentValues: List<String>,
        ): SkaldVaultV1PlatformPathConstructionRequest =
            SkaldVaultV1PlatformPathConstructionRequest(
                source = SkaldVaultV1PlatformPathConstructionSource.ExplicitSegmentValues,
                rootEvidence = rootEvidence,
                rejectedRootReason = null,
                layoutPlan = null,
                rawCandidate = null,
                explicitArtifactKind = artifactKind,
                explicitSegmentValues = segmentValues,
            )

        fun rawPlatformPathCandidate(rawCandidate: String?): SkaldVaultV1PlatformPathConstructionRequest =
            SkaldVaultV1PlatformPathConstructionRequest(
                source = SkaldVaultV1PlatformPathConstructionSource.RawPlatformPathCandidate,
                rootEvidence = null,
                rejectedRootReason = null,
                layoutPlan = null,
                rawCandidate = rawCandidate,
                explicitArtifactKind = null,
                explicitSegmentValues = null,
            )
    }
}

class SkaldVaultV1PlatformArtifactLocationToken internal constructor(
    val rootKind: SkaldVaultV1PlatformRootKind,
    val artifactKind: SkaldVaultV1PlatformPathConstructionArtifactKind,
    val segmentCount: Int,
) {
    val containsPlatformPath: Boolean = false
    val containsAbsolutePath: Boolean = false
    val usableForFileIo: Boolean = false

    override fun toString(): String =
        "SkaldVaultV1PlatformArtifactLocationToken(" +
            "rootKind=$rootKind, " +
            "artifactKind=$artifactKind, " +
            "segmentCount=$segmentCount, " +
            "root=<redacted>, " +
            "containsPlatformPath=false, " +
            "containsAbsolutePath=false, " +
            "usableForFileIo=false" +
            ")"
}

class SkaldVaultV1PlannedPlatformArtifactLocation internal constructor(
    val rootKind: SkaldVaultV1PlatformRootKind,
    val rootToken: SkaldVaultV1PathContainmentRootToken,
    val artifactKind: SkaldVaultV1PlatformPathConstructionArtifactKind,
    val storageLayoutPolicyId: String,
    val pathContainmentPlannerPolicyId: String,
    private val segmentValues: List<String>,
    val token: SkaldVaultV1PlatformArtifactLocationToken,
    val rootTokenBound: Boolean,
    val relativeSegmentsOnly: Boolean,
    val platformPathConstructed: Boolean,
    val realFilesystemContainmentChecked: Boolean,
    val symlinkChecked: Boolean,
    val permissionChecked: Boolean,
    val durabilityProbed: Boolean,
) {
    val usableForFileIo: Boolean = false
    val usableForPersistence: Boolean = false
    val manifestReadWriteAvailable: Boolean = false
    val storageIndexReadWriteAvailable: Boolean = false
    val recordReadWriteAvailable: Boolean = false

    fun testOnlySegmentValues(): List<String> = segmentValues

    override fun toString(): String =
        "SkaldVaultV1PlannedPlatformArtifactLocation(" +
            "rootKind=$rootKind, " +
            "artifactKind=$artifactKind, " +
            "segmentCount=${segmentValues.size}, " +
            "root=<redacted>, " +
            "segments=<redacted>, " +
            "platformPathConstructed=false, " +
            "usableForFileIo=false, " +
            "usableForPersistence=false" +
            ")"
}

class SkaldVaultV1PlatformPathConstructionEvidence internal constructor(
    val policyId: String,
    val policyVersion: Int,
    val status: SkaldVaultV1PlatformPathConstructionStatus,
    val source: SkaldVaultV1PlatformPathConstructionSource,
    val rootKind: SkaldVaultV1PlatformRootKind,
    val capability: SkaldVaultV1PlatformPathConstructionCapability,
    private val plannedLocations: List<SkaldVaultV1PlannedPlatformArtifactLocation>,
    val blockers: Set<SkaldVaultV1PlatformPathConstructionBlocker>,
    val warnings: Set<SkaldVaultV1PlatformPathConstructionWarning>,
    val platformPathConstructionStillDisabled: Boolean = true,
    val plannedArtifactLocationEvidenceModeled: Boolean = true,
    val rawRootStringExposedByDefault: Boolean = false,
    val platformPathObjectReturned: Boolean = false,
) {
    fun locations(): List<SkaldVaultV1PlannedPlatformArtifactLocation> = plannedLocations

    override fun toString(): String =
        "SkaldVaultV1PlatformPathConstructionEvidence(" +
            "policyId=$policyId, " +
            "policyVersion=$policyVersion, " +
            "status=$status, " +
            "source=$source, " +
            "rootKind=$rootKind, " +
            "plannedLocationCount=${plannedLocations.size}, " +
            "root=<redacted>, " +
            "locations=<redacted>, " +
            "platformPathObjectReturned=false" +
            ")"
}

sealed class SkaldVaultV1PlatformPathConstructionResult<out T> {
    data class Accepted<out T>(val value: T) : SkaldVaultV1PlatformPathConstructionResult<T>() {
        override fun toString(): String = "Accepted(value=<redacted-platform-artifact-location-evidence>)"
    }

    data class Rejected(
        val reason: SkaldVaultV1PlatformPathConstructionFailureReason,
        val status: SkaldVaultV1PlatformPathConstructionStatus,
        val source: SkaldVaultV1PlatformPathConstructionSource,
        val safeMessage: String = reason.label,
        val rootResolverFailureReason: SkaldVaultV1PlatformRootResolverFailureReason? = null,
        val containmentFailureReason: SkaldVaultV1PathContainmentRejectionReason? = null,
    ) : SkaldVaultV1PlatformPathConstructionResult<Nothing>() {
        override fun toString(): String =
            "Rejected(" +
                "reason=$reason, " +
                "status=$status, " +
                "source=$source, " +
                "safeMessage=$safeMessage, " +
                "rootResolverFailureReason=$rootResolverFailureReason, " +
                "containmentFailureReason=$containmentFailureReason" +
                ")"
    }
}

object SkaldVaultV1PlatformPathConstructionPolicy : SkaldVaultV1PlatformPathConstructionBoundary {
    const val POLICY_ID = "skald-vault-v1-platform-path-construction-boundary-v1"
    const val POLICY_VERSION = 1

    override fun plan(
        request: SkaldVaultV1PlatformPathConstructionRequest,
    ): SkaldVaultV1PlatformPathConstructionResult<SkaldVaultV1PlatformPathConstructionEvidence> =
        when (request.source) {
            SkaldVaultV1PlatformPathConstructionSource.NoEvidence -> reject(
                reason = SkaldVaultV1PlatformPathConstructionFailureReason.NoCandidateEvidenceSupplied,
                status = SkaldVaultV1PlatformPathConstructionStatus.NoEvidenceAvailable,
                source = request.source,
            )
            SkaldVaultV1PlatformPathConstructionSource.RootEvidenceOnly -> reject(
                reason = SkaldVaultV1PlatformPathConstructionFailureReason.MissingLogicalArtifactEvidence,
                status = SkaldVaultV1PlatformPathConstructionStatus.MissingLogicalArtifactEvidence,
                source = request.source,
            )
            SkaldVaultV1PlatformPathConstructionSource.LogicalLayoutOnly -> reject(
                reason = SkaldVaultV1PlatformPathConstructionFailureReason.MissingPlatformRootEvidence,
                status = SkaldVaultV1PlatformPathConstructionStatus.MissingPlatformRootEvidence,
                source = request.source,
            )
            SkaldVaultV1PlatformPathConstructionSource.RejectedRootEvidence -> reject(
                reason = SkaldVaultV1PlatformPathConstructionFailureReason.RejectedPlatformRootEvidence,
                status = SkaldVaultV1PlatformPathConstructionStatus.RootEvidenceRejected,
                source = request.source,
                rootResolverFailureReason = request.rejectedRootReasonOrNull(),
            )
            SkaldVaultV1PlatformPathConstructionSource.RootResolverAndLogicalLayout ->
                planFromRootAndLayout(request)
            SkaldVaultV1PlatformPathConstructionSource.RawPlatformPathCandidate ->
                rejectRawCandidate(request)
            SkaldVaultV1PlatformPathConstructionSource.ExplicitSegmentValues ->
                planFromExplicitSegments(request)
        }

    private fun planFromRootAndLayout(
        request: SkaldVaultV1PlatformPathConstructionRequest,
    ): SkaldVaultV1PlatformPathConstructionResult<SkaldVaultV1PlatformPathConstructionEvidence> {
        val rootEvidence = request.rootEvidenceOrNull()
            ?: return reject(
                reason = SkaldVaultV1PlatformPathConstructionFailureReason.MissingPlatformRootEvidence,
                status = SkaldVaultV1PlatformPathConstructionStatus.MissingPlatformRootEvidence,
                source = request.source,
            )
        val layoutPlan = request.layoutPlanOrNull()
            ?: return reject(
                reason = SkaldVaultV1PlatformPathConstructionFailureReason.MissingLogicalArtifactEvidence,
                status = SkaldVaultV1PlatformPathConstructionStatus.MissingLogicalArtifactEvidence,
                source = request.source,
            )
        val rootToken = rootEvidence.toContainmentRootToken()
            ?: return reject(
                reason = SkaldVaultV1PlatformPathConstructionFailureReason.UnsupportedRootEvidence,
                status = SkaldVaultV1PlatformPathConstructionStatus.RootEvidenceRejected,
                source = request.source,
            )
        return when (
            val containment = SkaldVaultV1PathContainmentPlanner.planForLayout(rootToken, layoutPlan)
        ) {
            is SkaldVaultV1PathContainmentResult.Accepted -> accept(
                source = request.source,
                rootKind = rootEvidence.rootKind,
                plannedLocations = containment.value.plannedLocations.map {
                    it.toPlatformLocation(rootEvidence.rootKind)
                },
            )
            is SkaldVaultV1PathContainmentResult.Rejected -> reject(
                reason = SkaldVaultV1PlatformPathConstructionFailureReason.ContainmentPlannerRejected,
                status = SkaldVaultV1PlatformPathConstructionStatus.ArtifactSegmentEvidenceRejected,
                source = request.source,
                containmentFailureReason = containment.reason,
            )
        }
    }

    private fun planFromExplicitSegments(
        request: SkaldVaultV1PlatformPathConstructionRequest,
    ): SkaldVaultV1PlatformPathConstructionResult<SkaldVaultV1PlatformPathConstructionEvidence> {
        val rootEvidence = request.rootEvidenceOrNull()
            ?: return reject(
                reason = SkaldVaultV1PlatformPathConstructionFailureReason.MissingPlatformRootEvidence,
                status = SkaldVaultV1PlatformPathConstructionStatus.MissingPlatformRootEvidence,
                source = request.source,
            )
        val rootToken = rootEvidence.toContainmentRootToken()
            ?: return reject(
                reason = SkaldVaultV1PlatformPathConstructionFailureReason.UnsupportedRootEvidence,
                status = SkaldVaultV1PlatformPathConstructionStatus.RootEvidenceRejected,
                source = request.source,
            )
        val segmentValues = request.explicitSegmentValuesOrNull().orEmpty()
        if (segmentValues.isEmpty()) {
            return reject(
                reason = SkaldVaultV1PlatformPathConstructionFailureReason.EmptyArtifactSegmentRejected,
                status = SkaldVaultV1PlatformPathConstructionStatus.ArtifactSegmentEvidenceRejected,
                source = request.source,
            )
        }
        classifyExplicitSegmentValues(segmentValues)?.let { reason ->
            return reject(
                reason = reason,
                status = SkaldVaultV1PlatformPathConstructionStatus.ArtifactSegmentEvidenceRejected,
                source = request.source,
            )
        }
        val artifactKind = request.explicitArtifactKindOrNull()
            ?: SkaldVaultV1PlatformPathConstructionArtifactKind.ExplicitSegmentProbe
        val plannedKind = artifactKind.toContainmentArtifactKind()
        return when (
            val containment = SkaldVaultV1PathContainmentPlanner.planFromSegmentValues(
                rootToken = rootToken,
                storageLayoutPolicyId = SkaldVaultV1StorageLayoutPlanPolicy.POLICY_ID,
                artifactKind = plannedKind,
                segmentValues = segmentValues,
            )
        ) {
            is SkaldVaultV1PathContainmentResult.Accepted -> accept(
                source = request.source,
                rootKind = rootEvidence.rootKind,
                plannedLocations = listOf(
                    containment.value.toPlatformLocation(
                        rootKind = rootEvidence.rootKind,
                        artifactKindOverride = artifactKind,
                    ),
                ),
            )
            is SkaldVaultV1PathContainmentResult.Rejected -> reject(
                reason = containment.toPathConstructionFailureReason(),
                status = SkaldVaultV1PlatformPathConstructionStatus.ArtifactSegmentEvidenceRejected,
                source = request.source,
                containmentFailureReason = containment.reason,
            )
        }
    }

    private fun rejectRawCandidate(
        request: SkaldVaultV1PlatformPathConstructionRequest,
    ): SkaldVaultV1PlatformPathConstructionResult.Rejected {
        val raw = request.rawCandidateOrNull()
        val reason = classifyRawCandidate(raw)
        return reject(
            reason = reason,
            status = SkaldVaultV1PlatformPathConstructionStatus.RawPlatformPathInputRejected,
            source = request.source,
        )
    }

    private fun accept(
        source: SkaldVaultV1PlatformPathConstructionSource,
        rootKind: SkaldVaultV1PlatformRootKind,
        plannedLocations: List<SkaldVaultV1PlannedPlatformArtifactLocation>,
    ): SkaldVaultV1PlatformPathConstructionResult.Accepted<SkaldVaultV1PlatformPathConstructionEvidence> =
        SkaldVaultV1PlatformPathConstructionResult.Accepted(
            SkaldVaultV1PlatformPathConstructionEvidence(
                policyId = POLICY_ID,
                policyVersion = POLICY_VERSION,
                status = SkaldVaultV1PlatformPathConstructionStatus.PlannedArtifactLocationsAcceptedStillDisabled,
                source = source,
                rootKind = rootKind,
                capability = SkaldVaultV1PlatformPathConstructionCapability.StillDisabled,
                plannedLocations = plannedLocations,
                blockers = SkaldVaultV1PlatformPathConstructionBlocker.entries.toSet(),
                warnings = warningsFor(rootKind),
            ),
        )

    private fun reject(
        reason: SkaldVaultV1PlatformPathConstructionFailureReason,
        status: SkaldVaultV1PlatformPathConstructionStatus,
        source: SkaldVaultV1PlatformPathConstructionSource,
        rootResolverFailureReason: SkaldVaultV1PlatformRootResolverFailureReason? = null,
        containmentFailureReason: SkaldVaultV1PathContainmentRejectionReason? = null,
    ): SkaldVaultV1PlatformPathConstructionResult.Rejected =
        SkaldVaultV1PlatformPathConstructionResult.Rejected(
            reason = reason,
            status = status,
            source = source,
            rootResolverFailureReason = rootResolverFailureReason,
            containmentFailureReason = containmentFailureReason,
        )

    private fun warningsFor(
        rootKind: SkaldVaultV1PlatformRootKind,
    ): Set<SkaldVaultV1PlatformPathConstructionWarning> {
        val base = setOf(
            SkaldVaultV1PlatformPathConstructionWarning.EvidenceOnly,
            SkaldVaultV1PlatformPathConstructionWarning.TokenIsNotPlatformPath,
            SkaldVaultV1PlatformPathConstructionWarning.RootStringsRedacted,
            SkaldVaultV1PlatformPathConstructionWarning.LogicalSegmentsOnly,
        )
        return when (rootKind) {
            SkaldVaultV1PlatformRootKind.AndroidAppPrivateInternal ->
                base + SkaldVaultV1PlatformPathConstructionWarning.AndroidRootStillAppPrivateOnly
            SkaldVaultV1PlatformRootKind.LinuxCustomCandidate ->
                base + SkaldVaultV1PlatformPathConstructionWarning.LinuxCustomRootsStillSettingsFutureWork
            else -> base
        }
    }

    private fun SkaldVaultV1PlatformRootResolverEvidence.toContainmentRootToken():
        SkaldVaultV1PathContainmentRootToken? =
        when (rootKind) {
            SkaldVaultV1PlatformRootKind.AndroidAppPrivateInternal ->
                SkaldVaultV1PathContainmentRootToken.AndroidAppPrivateInternalRoot
            SkaldVaultV1PlatformRootKind.LinuxXdgDataHome,
            SkaldVaultV1PlatformRootKind.LinuxHomeFallbackUserData,
            SkaldVaultV1PlatformRootKind.LinuxCustomCandidate,
            -> SkaldVaultV1PathContainmentRootToken.DesktopAppControlledUserDataRoot
            SkaldVaultV1PlatformRootKind.AndroidExternalShared,
            SkaldVaultV1PlatformRootKind.AndroidUserSelected,
            SkaldVaultV1PlatformRootKind.None,
            -> null
        }

    private fun SkaldVaultV1PlannedArtifactLocation.toPlatformLocation(
        rootKind: SkaldVaultV1PlatformRootKind,
        artifactKindOverride: SkaldVaultV1PlatformPathConstructionArtifactKind? = null,
    ): SkaldVaultV1PlannedPlatformArtifactLocation {
        val artifactKind = artifactKindOverride ?: artifactKind.toPathConstructionArtifactKind()
        val segmentValues = segments.map { it.value }
        val token = SkaldVaultV1PlatformArtifactLocationToken(
            rootKind = rootKind,
            artifactKind = artifactKind,
            segmentCount = segmentValues.size,
        )
        return SkaldVaultV1PlannedPlatformArtifactLocation(
            rootKind = rootKind,
            rootToken = rootToken,
            artifactKind = artifactKind,
            storageLayoutPolicyId = storageLayoutPolicyId,
            pathContainmentPlannerPolicyId = SkaldVaultV1PathContainmentPlanner.POLICY_ID,
            segmentValues = segmentValues,
            token = token,
            rootTokenBound = rootTokenBound,
            relativeSegmentsOnly = relativeSegmentsOnly,
            platformPathConstructed = platformPathConstructed,
            realFilesystemContainmentChecked = realFilesystemContainmentChecked,
            symlinkChecked = symlinkChecked,
            permissionChecked = permissionChecked,
            durabilityProbed = durabilityProbed,
        )
    }

    private fun SkaldVaultV1PlannedArtifactKind.toPathConstructionArtifactKind():
        SkaldVaultV1PlatformPathConstructionArtifactKind =
        when (this) {
            SkaldVaultV1PlannedArtifactKind.CurrentContainer ->
                SkaldVaultV1PlatformPathConstructionArtifactKind.CurrentContainer
            SkaldVaultV1PlannedArtifactKind.CurrentManifest ->
                SkaldVaultV1PlatformPathConstructionArtifactKind.CurrentManifest
            SkaldVaultV1PlannedArtifactKind.CurrentStorageIndex ->
                SkaldVaultV1PlatformPathConstructionArtifactKind.CurrentStorageIndex
            SkaldVaultV1PlannedArtifactKind.RecordArtifact ->
                SkaldVaultV1PlatformPathConstructionArtifactKind.RecordArtifact
            SkaldVaultV1PlannedArtifactKind.TempContainer ->
                SkaldVaultV1PlatformPathConstructionArtifactKind.TempContainer
            SkaldVaultV1PlannedArtifactKind.TempManifest ->
                SkaldVaultV1PlatformPathConstructionArtifactKind.TempManifest
            SkaldVaultV1PlannedArtifactKind.TempStorageIndex ->
                SkaldVaultV1PlatformPathConstructionArtifactKind.TempStorageIndex
            SkaldVaultV1PlannedArtifactKind.QuarantineRoot ->
                SkaldVaultV1PlatformPathConstructionArtifactKind.QuarantineArtifact
            SkaldVaultV1PlannedArtifactKind.RecoveryMetadata ->
                SkaldVaultV1PlatformPathConstructionArtifactKind.RecoveryMetadata
        }

    private fun SkaldVaultV1PlatformPathConstructionArtifactKind.toContainmentArtifactKind():
        SkaldVaultV1PlannedArtifactKind =
        when (this) {
            SkaldVaultV1PlatformPathConstructionArtifactKind.CurrentContainer ->
                SkaldVaultV1PlannedArtifactKind.CurrentContainer
            SkaldVaultV1PlatformPathConstructionArtifactKind.CurrentManifest ->
                SkaldVaultV1PlannedArtifactKind.CurrentManifest
            SkaldVaultV1PlatformPathConstructionArtifactKind.CurrentStorageIndex ->
                SkaldVaultV1PlannedArtifactKind.CurrentStorageIndex
            SkaldVaultV1PlatformPathConstructionArtifactKind.RecordArtifact ->
                SkaldVaultV1PlannedArtifactKind.RecordArtifact
            SkaldVaultV1PlatformPathConstructionArtifactKind.TempContainer ->
                SkaldVaultV1PlannedArtifactKind.TempContainer
            SkaldVaultV1PlatformPathConstructionArtifactKind.TempManifest ->
                SkaldVaultV1PlannedArtifactKind.TempManifest
            SkaldVaultV1PlatformPathConstructionArtifactKind.TempStorageIndex ->
                SkaldVaultV1PlannedArtifactKind.TempStorageIndex
            SkaldVaultV1PlatformPathConstructionArtifactKind.QuarantineArtifact ->
                SkaldVaultV1PlannedArtifactKind.QuarantineRoot
            SkaldVaultV1PlatformPathConstructionArtifactKind.RecoveryMetadata,
            SkaldVaultV1PlatformPathConstructionArtifactKind.ExplicitSegmentProbe,
            -> SkaldVaultV1PlannedArtifactKind.RecoveryMetadata
        }

    private fun SkaldVaultV1PathContainmentResult.Rejected.toPathConstructionFailureReason():
        SkaldVaultV1PlatformPathConstructionFailureReason =
        when (reason) {
            SkaldVaultV1PathContainmentRejectionReason.EmptySegmentList ->
                SkaldVaultV1PlatformPathConstructionFailureReason.EmptyArtifactSegmentRejected
            SkaldVaultV1PathContainmentRejectionReason.ParentSegmentRejected,
            SkaldVaultV1PathContainmentRejectionReason.DotSegmentRejected,
            SkaldVaultV1PathContainmentRejectionReason.SegmentContainsSeparator,
            SkaldVaultV1PathContainmentRejectionReason.AbsoluteSegmentRejected,
            -> SkaldVaultV1PlatformPathConstructionFailureReason.PathTraversalRejected
            SkaldVaultV1PathContainmentRejectionReason.SecretMaterialRejected,
            SkaldVaultV1PathContainmentRejectionReason.UserControlledLabelRejected,
            -> SkaldVaultV1PlatformPathConstructionFailureReason.SecretMaterialRejected
            else -> SkaldVaultV1PlatformPathConstructionFailureReason.UnsupportedArtifactSegmentRejected
        }

    private fun classifyRawCandidate(
        raw: String?,
    ): SkaldVaultV1PlatformPathConstructionFailureReason {
        if (raw == null || raw.isBlank()) {
            return SkaldVaultV1PlatformPathConstructionFailureReason.EmptyArtifactSegmentRejected
        }
        val lower = raw.lowercase()
        return when {
            lower.startsWith("file:") ||
                lower.startsWith("http:") ||
                lower.startsWith("https:") ||
                lower.startsWith("content:") ->
                SkaldVaultV1PlatformPathConstructionFailureReason.UriOrUrlInputRejected
            looksLikeWindowsOrUnc(raw) ->
                SkaldVaultV1PlatformPathConstructionFailureReason.WindowsOrUncPathInputRejected
            raw.startsWith("/") ->
                SkaldVaultV1PlatformPathConstructionFailureReason.RawAbsolutePathInputRejected
            raw.contains("..") ->
                SkaldVaultV1PlatformPathConstructionFailureReason.PathTraversalRejected
            raw.contains("/") || raw.contains("\\") ->
                SkaldVaultV1PlatformPathConstructionFailureReason.RawRelativePathInputRejected
            looksLikeSecretOrCredential(lower) ->
                SkaldVaultV1PlatformPathConstructionFailureReason.SecretMaterialRejected
            looksLikePrivateKeyMaterial(raw, lower) ->
                SkaldVaultV1PlatformPathConstructionFailureReason.WalletMaterialRejected
            looksLikeBitcoinAddress(lower) ->
                SkaldVaultV1PlatformPathConstructionFailureReason.BitcoinAddressLikeArtifactRejected
            containsLongHexSegment(raw) ->
                SkaldVaultV1PlatformPathConstructionFailureReason.TransactionLikeArtifactRejected
            else -> SkaldVaultV1PlatformPathConstructionFailureReason.RawPlatformPathInputRejected
        }
    }

    private fun classifyExplicitSegmentValues(
        segmentValues: List<String>,
    ): SkaldVaultV1PlatformPathConstructionFailureReason? =
        segmentValues.firstNotNullOfOrNull { segment ->
            val lower = segment.lowercase()
            when {
                segment.isBlank() ->
                    SkaldVaultV1PlatformPathConstructionFailureReason.EmptyArtifactSegmentRejected
                segment.contains("..") || segment.contains("/") || segment.contains("\\") ->
                    SkaldVaultV1PlatformPathConstructionFailureReason.PathTraversalRejected
                looksLikeSecretOrCredential(lower) ->
                    SkaldVaultV1PlatformPathConstructionFailureReason.SecretMaterialRejected
                looksLikePrivateKeyMaterial(segment, lower) ->
                    SkaldVaultV1PlatformPathConstructionFailureReason.WalletMaterialRejected
                looksLikeBitcoinAddress(lower) ->
                    SkaldVaultV1PlatformPathConstructionFailureReason.BitcoinAddressLikeArtifactRejected
                containsLongHexSegment(segment) ->
                    SkaldVaultV1PlatformPathConstructionFailureReason.TransactionLikeArtifactRejected
                else -> null
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

    private fun Char.isHex(): Boolean =
        this in '0'..'9' || this in 'a'..'f' || this in 'A'..'F'
}
