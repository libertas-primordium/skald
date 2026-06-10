package com.libertasprimordium.skald.security

enum class SkaldVaultV1PathContainmentRootToken(
    val tokenId: String,
    val acceptedForPlanning: Boolean,
) {
    AndroidAppPrivateInternalRoot(
        tokenId = "android-app-private-internal-root-v1",
        acceptedForPlanning = true,
    ),
    DesktopAppControlledUserDataRoot(
        tokenId = "desktop-app-controlled-user-data-root-v1",
        acceptedForPlanning = true,
    ),
    TestOnlyReviewedRoot(
        tokenId = "test-only-reviewed-root-v1",
        acceptedForPlanning = true,
    ),
    AndroidExternalSharedRoot(
        tokenId = "android-external-shared-root-rejected-v1",
        acceptedForPlanning = false,
    ),
    UserSelectedPathRoot(
        tokenId = "user-selected-path-root-rejected-v1",
        acceptedForPlanning = false,
    ),
    UnknownRoot(
        tokenId = "unknown-root-rejected-v1",
        acceptedForPlanning = false,
    ),
    UnreviewedRoot(
        tokenId = "unreviewed-root-rejected-v1",
        acceptedForPlanning = false,
    ),
    UnsafeRoot(
        tokenId = "unsafe-root-rejected-v1",
        acceptedForPlanning = false,
    ),
}

enum class SkaldVaultV1PlannedArtifactKind(val label: String) {
    CurrentContainer("current vault container"),
    CurrentManifest("current manifest"),
    CurrentStorageIndex("current storage index"),
    RecordArtifact("record artifact"),
    TempContainer("temporary vault container"),
    TempManifest("temporary manifest"),
    TempStorageIndex("temporary storage index"),
    QuarantineRoot("quarantine root"),
    RecoveryMetadata("recovery metadata"),
}

enum class SkaldVaultV1PathContainmentProof(val label: String) {
    RootTokenBoundRelativeSegments("root-token-bound relative segments"),
}

enum class SkaldVaultV1PathContainmentRejectionReason(val label: String) {
    ReviewedRootMissing("reviewed root token missing"),
    ReviewedRootUnknown("reviewed root token unknown"),
    ReviewedRootRejected("reviewed root rejected"),
    ExternalStorageRootRejected("external storage root rejected"),
    UserPathRootRejected("user path root rejected"),
    PlatformRootResolutionAbsent("platform root resolution absent"),
    EmptySegmentList("empty segment list"),
    UnsafeSegmentRejected("unsafe segment rejected"),
    AbsoluteSegmentRejected("absolute segment rejected"),
    DotSegmentRejected("dot segment rejected"),
    ParentSegmentRejected("parent segment rejected"),
    SegmentContainsSeparator("segment contains separator"),
    SegmentContainsControlCharacter("segment contains control character"),
    SegmentContainsWhitespace("segment contains whitespace"),
    SegmentContainsInvisibleFormat("segment contains invisible format character"),
    SegmentContainsNonAscii("segment contains non-ASCII text"),
    SegmentTooLong("segment too long"),
    SecretMaterialRejected("secret-looking material rejected"),
    UserControlledLabelRejected("user-controlled label rejected"),
    UnsupportedLayoutPolicy("unsupported storage layout policy"),
    ActualPathConstructionNotImplemented("actual path construction not implemented"),
    ContainmentProofUnavailable("real containment proof unavailable"),
    SymlinkCheckNotImplemented("symlink check not implemented"),
    PermissionCheckNotImplemented("permission check not implemented"),
    DurabilityProbeNotImplemented("durability probe not implemented"),
}

sealed class SkaldVaultV1PathContainmentResult<out T> {
    data class Accepted<out T>(val value: T) : SkaldVaultV1PathContainmentResult<T>()

    data class Rejected(
        val reason: SkaldVaultV1PathContainmentRejectionReason,
        val safeMessage: String,
    ) : SkaldVaultV1PathContainmentResult<Nothing>()
}

data class SkaldVaultV1PlannedArtifactLocation(
    val rootToken: SkaldVaultV1PathContainmentRootToken,
    val storageLayoutPolicyId: String,
    val artifactKind: SkaldVaultV1PlannedArtifactKind,
    val segments: List<SkaldVaultV1StoragePathSegment>,
    val containmentProof: SkaldVaultV1PathContainmentProof,
    val rootTokenBound: Boolean,
    val relativeSegmentsOnly: Boolean,
    val platformPathConstructed: Boolean,
    val realFilesystemContainmentChecked: Boolean,
    val symlinkChecked: Boolean,
    val permissionChecked: Boolean,
    val durabilityProbed: Boolean,
) {
    val segmentValues: List<String>
        get() = segments.map { it.value }
}

data class SkaldVaultV1PathContainmentPlan(
    val plannerPolicyId: String,
    val rootToken: SkaldVaultV1PathContainmentRootToken,
    val storageLayoutPolicyId: String,
    val plannedLocations: List<SkaldVaultV1PlannedArtifactLocation>,
) {
    val isPlatformPathPlan: Boolean = false
}

object SkaldVaultV1PathContainmentPlanner {
    const val POLICY_ID = "skald-vault-v1-path-containment-planner-v1"
    const val POLICY_VERSION = 1

    fun planForLayout(
        rootToken: SkaldVaultV1PathContainmentRootToken?,
        layoutPlan: SkaldVaultV1StorageLayoutPlan,
    ): SkaldVaultV1PathContainmentResult<SkaldVaultV1PathContainmentPlan> {
        val acceptedRoot = when (val result = validateRootToken(rootToken)) {
            is SkaldVaultV1PathContainmentResult.Accepted -> result.value
            is SkaldVaultV1PathContainmentResult.Rejected -> return result
        }
        if (layoutPlan.policyId != SkaldVaultV1StorageLayoutPlanPolicy.POLICY_ID) {
            return rejected(SkaldVaultV1PathContainmentRejectionReason.UnsupportedLayoutPolicy)
        }
        val locations = buildList {
            layoutPlan.allLocations.forEach { logicalLocation ->
                when (val result = planLocation(acceptedRoot, layoutPlan.policyId, logicalLocation)) {
                    is SkaldVaultV1PathContainmentResult.Accepted -> add(result.value)
                    is SkaldVaultV1PathContainmentResult.Rejected -> return result
                }
            }
        }
        return SkaldVaultV1PathContainmentResult.Accepted(
            SkaldVaultV1PathContainmentPlan(
                plannerPolicyId = POLICY_ID,
                rootToken = acceptedRoot,
                storageLayoutPolicyId = layoutPlan.policyId,
                plannedLocations = locations,
            ),
        )
    }

    fun planFromSegmentValues(
        rootToken: SkaldVaultV1PathContainmentRootToken?,
        storageLayoutPolicyId: String,
        artifactKind: SkaldVaultV1PlannedArtifactKind,
        segmentValues: List<String>,
        segmentSource: SkaldVaultV1StorageIdentifierSource =
            SkaldVaultV1StorageIdentifierSource.EncodedIdentifier,
    ): SkaldVaultV1PathContainmentResult<SkaldVaultV1PlannedArtifactLocation> {
        val acceptedRoot = when (val result = validateRootToken(rootToken)) {
            is SkaldVaultV1PathContainmentResult.Accepted -> result.value
            is SkaldVaultV1PathContainmentResult.Rejected -> return result
        }
        if (storageLayoutPolicyId != SkaldVaultV1StorageLayoutPlanPolicy.POLICY_ID) {
            return rejected(SkaldVaultV1PathContainmentRejectionReason.UnsupportedLayoutPolicy)
        }
        val segments = when (val result = validateSegmentValues(segmentValues, segmentSource)) {
            is SkaldVaultV1PathContainmentResult.Accepted -> result.value
            is SkaldVaultV1PathContainmentResult.Rejected -> return result
        }
        return acceptedLocation(
            rootToken = acceptedRoot,
            storageLayoutPolicyId = storageLayoutPolicyId,
            artifactKind = artifactKind,
            segments = segments,
        )
    }

    fun validateRootToken(
        rootToken: SkaldVaultV1PathContainmentRootToken?,
    ): SkaldVaultV1PathContainmentResult<SkaldVaultV1PathContainmentRootToken> {
        if (rootToken == null) {
            return rejected(SkaldVaultV1PathContainmentRejectionReason.ReviewedRootMissing)
        }
        if (rootToken.acceptedForPlanning) {
            return SkaldVaultV1PathContainmentResult.Accepted(rootToken)
        }
        return rejected(
            when (rootToken) {
                SkaldVaultV1PathContainmentRootToken.AndroidExternalSharedRoot ->
                    SkaldVaultV1PathContainmentRejectionReason.ExternalStorageRootRejected
                SkaldVaultV1PathContainmentRootToken.UserSelectedPathRoot ->
                    SkaldVaultV1PathContainmentRejectionReason.UserPathRootRejected
                SkaldVaultV1PathContainmentRootToken.UnknownRoot ->
                    SkaldVaultV1PathContainmentRejectionReason.ReviewedRootUnknown
                SkaldVaultV1PathContainmentRootToken.UnreviewedRoot,
                SkaldVaultV1PathContainmentRootToken.UnsafeRoot,
                -> SkaldVaultV1PathContainmentRejectionReason.ReviewedRootRejected
                SkaldVaultV1PathContainmentRootToken.AndroidAppPrivateInternalRoot,
                SkaldVaultV1PathContainmentRootToken.DesktopAppControlledUserDataRoot,
                SkaldVaultV1PathContainmentRootToken.TestOnlyReviewedRoot,
                -> SkaldVaultV1PathContainmentRejectionReason.ReviewedRootRejected
            },
        )
    }

    fun validateSegmentValues(
        segmentValues: List<String>,
        segmentSource: SkaldVaultV1StorageIdentifierSource =
            SkaldVaultV1StorageIdentifierSource.EncodedIdentifier,
    ): SkaldVaultV1PathContainmentResult<List<SkaldVaultV1StoragePathSegment>> {
        if (segmentValues.isEmpty()) {
            return rejected(SkaldVaultV1PathContainmentRejectionReason.EmptySegmentList)
        }
        val segments = buildList {
            segmentValues.forEach { value ->
                when (
                    val result = SkaldVaultV1StorageNamespacePathPolicy.validatePathSegment(
                        value = value,
                        source = segmentSource,
                    )
                ) {
                    is SkaldVaultV1StorageNamespacePathResult.Accepted -> add(result.value)
                    is SkaldVaultV1StorageNamespacePathResult.Rejected ->
                        return rejected(mapSegmentRejection(result.reason))
                }
            }
        }
        return SkaldVaultV1PathContainmentResult.Accepted(segments)
    }

    private fun planLocation(
        rootToken: SkaldVaultV1PathContainmentRootToken,
        storageLayoutPolicyId: String,
        logicalLocation: SkaldVaultV1LogicalStorageLocation,
    ): SkaldVaultV1PathContainmentResult<SkaldVaultV1PlannedArtifactLocation> {
        val segments = when (val result = validateSegmentValues(logicalLocation.segmentValues)) {
            is SkaldVaultV1PathContainmentResult.Accepted -> result.value
            is SkaldVaultV1PathContainmentResult.Rejected -> return result
        }
        return acceptedLocation(
            rootToken = rootToken,
            storageLayoutPolicyId = storageLayoutPolicyId,
            artifactKind = logicalLocation.artifact.toPlannedKind(),
            segments = segments,
        )
    }

    private fun acceptedLocation(
        rootToken: SkaldVaultV1PathContainmentRootToken,
        storageLayoutPolicyId: String,
        artifactKind: SkaldVaultV1PlannedArtifactKind,
        segments: List<SkaldVaultV1StoragePathSegment>,
    ): SkaldVaultV1PathContainmentResult.Accepted<SkaldVaultV1PlannedArtifactLocation> =
        SkaldVaultV1PathContainmentResult.Accepted(
            SkaldVaultV1PlannedArtifactLocation(
                rootToken = rootToken,
                storageLayoutPolicyId = storageLayoutPolicyId,
                artifactKind = artifactKind,
                segments = segments,
                containmentProof = SkaldVaultV1PathContainmentProof.RootTokenBoundRelativeSegments,
                rootTokenBound = true,
                relativeSegmentsOnly = true,
                platformPathConstructed = false,
                realFilesystemContainmentChecked = false,
                symlinkChecked = false,
                permissionChecked = false,
                durabilityProbed = false,
            ),
        )

    private fun SkaldVaultV1StorageLayoutArtifact.toPlannedKind(): SkaldVaultV1PlannedArtifactKind =
        when (this) {
            SkaldVaultV1StorageLayoutArtifact.CurrentContainer ->
                SkaldVaultV1PlannedArtifactKind.CurrentContainer
            SkaldVaultV1StorageLayoutArtifact.CurrentManifest ->
                SkaldVaultV1PlannedArtifactKind.CurrentManifest
            SkaldVaultV1StorageLayoutArtifact.CurrentStorageIndex ->
                SkaldVaultV1PlannedArtifactKind.CurrentStorageIndex
            SkaldVaultV1StorageLayoutArtifact.Record ->
                SkaldVaultV1PlannedArtifactKind.RecordArtifact
            SkaldVaultV1StorageLayoutArtifact.TempContainer ->
                SkaldVaultV1PlannedArtifactKind.TempContainer
            SkaldVaultV1StorageLayoutArtifact.TempManifest ->
                SkaldVaultV1PlannedArtifactKind.TempManifest
            SkaldVaultV1StorageLayoutArtifact.TempStorageIndex ->
                SkaldVaultV1PlannedArtifactKind.TempStorageIndex
            SkaldVaultV1StorageLayoutArtifact.QuarantineRoot ->
                SkaldVaultV1PlannedArtifactKind.QuarantineRoot
            SkaldVaultV1StorageLayoutArtifact.RecoveryMetadata ->
                SkaldVaultV1PlannedArtifactKind.RecoveryMetadata
        }

    private fun mapSegmentRejection(
        reason: SkaldVaultV1StorageNamespacePathRejectionReason,
    ): SkaldVaultV1PathContainmentRejectionReason =
        when (reason) {
            SkaldVaultV1StorageNamespacePathRejectionReason.EmptyIdentifier ->
                SkaldVaultV1PathContainmentRejectionReason.EmptySegmentList
            SkaldVaultV1StorageNamespacePathRejectionReason.DotSegment ->
                SkaldVaultV1PathContainmentRejectionReason.DotSegmentRejected
            SkaldVaultV1StorageNamespacePathRejectionReason.ParentSegment ->
                SkaldVaultV1PathContainmentRejectionReason.ParentSegmentRejected
            SkaldVaultV1StorageNamespacePathRejectionReason.ContainsPathSeparator ->
                SkaldVaultV1PathContainmentRejectionReason.SegmentContainsSeparator
            SkaldVaultV1StorageNamespacePathRejectionReason.ContainsTraversal ->
                SkaldVaultV1PathContainmentRejectionReason.UnsafeSegmentRejected
            SkaldVaultV1StorageNamespacePathRejectionReason.ContainsControlCharacter ->
                SkaldVaultV1PathContainmentRejectionReason.SegmentContainsControlCharacter
            SkaldVaultV1StorageNamespacePathRejectionReason.ContainsWhitespace ->
                SkaldVaultV1PathContainmentRejectionReason.SegmentContainsWhitespace
            SkaldVaultV1StorageNamespacePathRejectionReason.ContainsInvisibleFormat ->
                SkaldVaultV1PathContainmentRejectionReason.SegmentContainsInvisibleFormat
            SkaldVaultV1StorageNamespacePathRejectionReason.ContainsNonAscii ->
                SkaldVaultV1PathContainmentRejectionReason.SegmentContainsNonAscii
            SkaldVaultV1StorageNamespacePathRejectionReason.ContainsUnsupportedCharacter ->
                SkaldVaultV1PathContainmentRejectionReason.UnsafeSegmentRejected
            SkaldVaultV1StorageNamespacePathRejectionReason.TooLong ->
                SkaldVaultV1PathContainmentRejectionReason.SegmentTooLong
            SkaldVaultV1StorageNamespacePathRejectionReason.UserControlledInputRejected ->
                SkaldVaultV1PathContainmentRejectionReason.UserControlledLabelRejected
            SkaldVaultV1StorageNamespacePathRejectionReason.SecretMaterialRejected ->
                SkaldVaultV1PathContainmentRejectionReason.SecretMaterialRejected
            SkaldVaultV1StorageNamespacePathRejectionReason.AbsolutePathRejected ->
                SkaldVaultV1PathContainmentRejectionReason.AbsoluteSegmentRejected
            SkaldVaultV1StorageNamespacePathRejectionReason.UriLikePrefixRejected,
            SkaldVaultV1StorageNamespacePathRejectionReason.WindowsDrivePrefixRejected,
            SkaldVaultV1StorageNamespacePathRejectionReason.UnknownNamespacePolicy,
            SkaldVaultV1StorageNamespacePathRejectionReason.WrongVaultIdLength,
            SkaldVaultV1StorageNamespacePathRejectionReason.WrongRecordIdLength,
            -> SkaldVaultV1PathContainmentRejectionReason.UnsafeSegmentRejected
        }

    private fun rejected(
        reason: SkaldVaultV1PathContainmentRejectionReason,
    ): SkaldVaultV1PathContainmentResult.Rejected =
        SkaldVaultV1PathContainmentResult.Rejected(
            reason = reason,
            safeMessage = reason.label,
        )
}
