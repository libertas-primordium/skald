package com.libertasprimordium.skald.security

enum class SkaldVaultV1StorageLayoutArtifact(val label: String) {
    CurrentContainer("current vault container"),
    CurrentManifest("current manifest"),
    CurrentStorageIndex("current storage index"),
    Record("record artifact"),
    TempContainer("temporary vault container"),
    TempManifest("temporary manifest"),
    TempStorageIndex("temporary storage index"),
    QuarantineRoot("quarantine root"),
    RecoveryMetadata("recovery metadata"),
}

enum class SkaldVaultV1StorageLayoutRejectionReason(val label: String) {
    InvalidStorageNamespace("invalid storage namespace"),
    InvalidVaultSegment("invalid vault segment"),
    InvalidRecordSegment("invalid record segment"),
    InvalidManifestSegment("invalid manifest segment"),
    InvalidArtifactSegment("invalid artifact segment"),
    UnsupportedLayoutPolicy("unsupported layout policy"),
    EmptyLayout("empty layout"),
    AbsolutePathRejected("absolute path rejected"),
    TraversalRejected("traversal rejected"),
    UnsafeSegmentRejected("unsafe segment rejected"),
    SecretMaterialRejected("secret-looking material rejected"),
    UserControlledInputRejected("user-controlled input rejected"),
    PlatformRootRequiredButAbsent("platform root required but absent"),
    PlatformPathConstructionNotImplemented("platform path construction not implemented"),
}

sealed class SkaldVaultV1StorageLayoutResult<out T> {
    data class Accepted<out T>(val value: T) : SkaldVaultV1StorageLayoutResult<T>()

    data class Rejected(
        val reason: SkaldVaultV1StorageLayoutRejectionReason,
        val safeMessage: String,
    ) : SkaldVaultV1StorageLayoutResult<Nothing>()
}

data class SkaldVaultV1LogicalStorageLocation(
    val artifact: SkaldVaultV1StorageLayoutArtifact,
    val segments: List<SkaldVaultV1StoragePathSegment>,
) {
    val segmentValues: List<String>
        get() = segments.map { it.value }
}

data class SkaldVaultV1StorageLayoutPlan(
    val policyId: String,
    val storageNamespaceId: String,
    val recordNamespaceId: String,
    val manifestNamespaceId: String,
    val vaultSegment: SkaldVaultV1StoragePathSegment,
    val currentContainer: SkaldVaultV1LogicalStorageLocation,
    val currentManifest: SkaldVaultV1LogicalStorageLocation,
    val currentStorageIndex: SkaldVaultV1LogicalStorageLocation,
    val recordLocations: List<SkaldVaultV1LogicalStorageLocation>,
    val tempContainer: SkaldVaultV1LogicalStorageLocation,
    val tempManifest: SkaldVaultV1LogicalStorageLocation,
    val tempStorageIndex: SkaldVaultV1LogicalStorageLocation,
    val quarantineRoot: SkaldVaultV1LogicalStorageLocation,
    val recoveryMetadata: SkaldVaultV1LogicalStorageLocation,
) {
    val allLocations: List<SkaldVaultV1LogicalStorageLocation>
        get() = listOf(
            currentContainer,
            currentManifest,
            currentStorageIndex,
        ) + recordLocations + listOf(
            tempContainer,
            tempManifest,
            tempStorageIndex,
            quarantineRoot,
            recoveryMetadata,
        )
}

object SkaldVaultV1StorageLayoutPlanPolicy {
    const val POLICY_ID = "skald-vault-v1-storage-layout-plan-v1"
    const val POLICY_VERSION = 1

    private const val LAYOUT_NAMESPACE_SEGMENT = "skald-vault-v1"
    private const val CONTAINER_SEGMENT = "container"
    private const val MANIFEST_SEGMENT = "manifest"
    private const val RECORDS_SEGMENT = "records"
    private const val INDEX_SEGMENT = "index"
    private const val TEMP_SEGMENT = "tmp"
    private const val QUARANTINE_SEGMENT = "quarantine"
    private const val RECOVERY_SEGMENT = "recovery"
    private const val CURRENT_SEGMENT = "current"
    private const val PENDING_SEGMENT = "pending"
    private const val CONTAINER_PENDING_SEGMENT = "container_pending"
    private const val MANIFEST_PENDING_SEGMENT = "manifest_pending"
    private const val INDEX_V1_SEGMENT = "index_v1"
    private const val INDEX_PENDING_SEGMENT = "index_pending"
    private const val RECOVERY_V1_SEGMENT = "recovery_v1"

    fun planForVault(
        vaultId: ByteArray,
        recordIds: List<ByteArray> = emptyList(),
        policyId: String = POLICY_ID,
    ): SkaldVaultV1StorageLayoutResult<SkaldVaultV1StorageLayoutPlan> {
        if (policyId != POLICY_ID) {
            return rejected(SkaldVaultV1StorageLayoutRejectionReason.UnsupportedLayoutPolicy)
        }

        val storageNamespace = when (
            val result = SkaldVaultV1StorageNamespacePathPolicy.validateStorageNamespaceId(
                SkaldVaultV1StorageNamespacePathPolicy.STORAGE_NAMESPACE_ID,
            )
        ) {
            is SkaldVaultV1StorageNamespacePathResult.Accepted -> result.value
            is SkaldVaultV1StorageNamespacePathResult.Rejected ->
                return rejected(mapNamespaceRejection(result.reason, namespaceFallback = true))
        }
        val recordNamespace = when (
            val result = SkaldVaultV1StorageNamespacePathPolicy.validateRecordNamespaceId(
                SkaldVaultV1StorageNamespacePathPolicy.RECORD_NAMESPACE_ID,
            )
        ) {
            is SkaldVaultV1StorageNamespacePathResult.Accepted -> result.value
            is SkaldVaultV1StorageNamespacePathResult.Rejected ->
                return rejected(mapNamespaceRejection(result.reason, namespaceFallback = true))
        }
        val manifestNamespace = when (
            val result = SkaldVaultV1StorageNamespacePathPolicy.validateManifestNamespaceId(
                SkaldVaultV1StorageNamespacePathPolicy.MANIFEST_NAMESPACE_ID,
            )
        ) {
            is SkaldVaultV1StorageNamespacePathResult.Accepted -> result.value
            is SkaldVaultV1StorageNamespacePathResult.Rejected ->
                return rejected(mapNamespaceRejection(result.reason, namespaceFallback = true))
        }
        val vaultSegment = when (val result = SkaldVaultV1StorageNamespacePathPolicy.encodeVaultStorageId(vaultId)) {
            is SkaldVaultV1StorageNamespacePathResult.Accepted -> result.value
            is SkaldVaultV1StorageNamespacePathResult.Rejected ->
                return rejected(mapNamespaceRejection(result.reason, segmentFallback = InvalidSegmentKind.Vault))
        }
        val manifestSegment = when (val result = SkaldVaultV1StorageNamespacePathPolicy.manifestStorageSegment()) {
            is SkaldVaultV1StorageNamespacePathResult.Accepted -> result.value
            is SkaldVaultV1StorageNamespacePathResult.Rejected ->
                return rejected(mapNamespaceRejection(result.reason, segmentFallback = InvalidSegmentKind.Manifest))
        }
        val recordSegments = buildList {
            recordIds.forEach { recordId ->
                when (val result = SkaldVaultV1StorageNamespacePathPolicy.encodeRecordStorageId(recordId)) {
                    is SkaldVaultV1StorageNamespacePathResult.Accepted -> add(result.value)
                    is SkaldVaultV1StorageNamespacePathResult.Rejected ->
                        return rejected(
                            mapNamespaceRejection(result.reason, segmentFallback = InvalidSegmentKind.Record),
                        )
                }
            }
        }

        val currentContainer = location(
            artifact = SkaldVaultV1StorageLayoutArtifact.CurrentContainer,
            vaultSegment = vaultSegment,
            artifactSegments = listOf(CONTAINER_SEGMENT, CURRENT_SEGMENT),
        )
        val currentManifest = location(
            artifact = SkaldVaultV1StorageLayoutArtifact.CurrentManifest,
            vaultSegment = vaultSegment,
            artifactSegments = listOf(MANIFEST_SEGMENT),
            terminalSegment = manifestSegment,
        )
        val currentStorageIndex = location(
            artifact = SkaldVaultV1StorageLayoutArtifact.CurrentStorageIndex,
            vaultSegment = vaultSegment,
            artifactSegments = listOf(INDEX_SEGMENT, INDEX_V1_SEGMENT),
        )
        val recordLocations = recordSegments.map { recordSegment ->
            location(
                artifact = SkaldVaultV1StorageLayoutArtifact.Record,
                vaultSegment = vaultSegment,
                artifactSegments = listOf(RECORDS_SEGMENT),
                terminalSegment = recordSegment,
            )
        }
        val plan = SkaldVaultV1StorageLayoutPlan(
            policyId = POLICY_ID,
            storageNamespaceId = storageNamespace,
            recordNamespaceId = recordNamespace,
            manifestNamespaceId = manifestNamespace,
            vaultSegment = vaultSegment,
            currentContainer = currentContainer,
            currentManifest = currentManifest,
            currentStorageIndex = currentStorageIndex,
            recordLocations = recordLocations,
            tempContainer = location(
                artifact = SkaldVaultV1StorageLayoutArtifact.TempContainer,
                vaultSegment = vaultSegment,
                artifactSegments = listOf(TEMP_SEGMENT, CONTAINER_PENDING_SEGMENT),
            ),
            tempManifest = location(
                artifact = SkaldVaultV1StorageLayoutArtifact.TempManifest,
                vaultSegment = vaultSegment,
                artifactSegments = listOf(TEMP_SEGMENT, MANIFEST_PENDING_SEGMENT),
            ),
            tempStorageIndex = location(
                artifact = SkaldVaultV1StorageLayoutArtifact.TempStorageIndex,
                vaultSegment = vaultSegment,
                artifactSegments = listOf(TEMP_SEGMENT, INDEX_PENDING_SEGMENT),
            ),
            quarantineRoot = location(
                artifact = SkaldVaultV1StorageLayoutArtifact.QuarantineRoot,
                vaultSegment = vaultSegment,
                artifactSegments = listOf(QUARANTINE_SEGMENT, PENDING_SEGMENT),
            ),
            recoveryMetadata = location(
                artifact = SkaldVaultV1StorageLayoutArtifact.RecoveryMetadata,
                vaultSegment = vaultSegment,
                artifactSegments = listOf(RECOVERY_SEGMENT, RECOVERY_V1_SEGMENT),
            ),
        )
        return if (plan.allLocations.isEmpty()) {
            rejected(SkaldVaultV1StorageLayoutRejectionReason.EmptyLayout)
        } else {
            SkaldVaultV1StorageLayoutResult.Accepted(plan)
        }
    }

    fun validateArtifactSegment(
        value: String,
        source: SkaldVaultV1StorageIdentifierSource = SkaldVaultV1StorageIdentifierSource.InternalPolicyConstant,
    ): SkaldVaultV1StorageLayoutResult<SkaldVaultV1StoragePathSegment> =
        when (val result = SkaldVaultV1StorageNamespacePathPolicy.validatePathSegment(value, source)) {
            is SkaldVaultV1StorageNamespacePathResult.Accepted ->
                SkaldVaultV1StorageLayoutResult.Accepted(result.value)
            is SkaldVaultV1StorageNamespacePathResult.Rejected ->
                rejected(mapNamespaceRejection(result.reason, segmentFallback = InvalidSegmentKind.Artifact))
        }

    private fun location(
        artifact: SkaldVaultV1StorageLayoutArtifact,
        vaultSegment: SkaldVaultV1StoragePathSegment,
        artifactSegments: List<String>,
        terminalSegment: SkaldVaultV1StoragePathSegment? = null,
    ): SkaldVaultV1LogicalStorageLocation {
        val segments = buildList {
            add(requiredArtifactSegment(LAYOUT_NAMESPACE_SEGMENT))
            add(vaultSegment)
            artifactSegments.forEach { add(requiredArtifactSegment(it)) }
            if (terminalSegment != null) {
                add(terminalSegment)
            }
        }
        return SkaldVaultV1LogicalStorageLocation(artifact = artifact, segments = segments)
    }

    private fun requiredArtifactSegment(value: String): SkaldVaultV1StoragePathSegment =
        when (val result = validateArtifactSegment(value)) {
            is SkaldVaultV1StorageLayoutResult.Accepted -> result.value
            is SkaldVaultV1StorageLayoutResult.Rejected -> error("invalid internal storage layout segment")
        }

    private enum class InvalidSegmentKind {
        Vault,
        Record,
        Manifest,
        Artifact,
    }

    private fun mapNamespaceRejection(
        reason: SkaldVaultV1StorageNamespacePathRejectionReason,
        namespaceFallback: Boolean = false,
        segmentFallback: InvalidSegmentKind = InvalidSegmentKind.Artifact,
    ): SkaldVaultV1StorageLayoutRejectionReason =
        when (reason) {
            SkaldVaultV1StorageNamespacePathRejectionReason.UserControlledInputRejected ->
                SkaldVaultV1StorageLayoutRejectionReason.UserControlledInputRejected
            SkaldVaultV1StorageNamespacePathRejectionReason.SecretMaterialRejected ->
                SkaldVaultV1StorageLayoutRejectionReason.SecretMaterialRejected
            SkaldVaultV1StorageNamespacePathRejectionReason.AbsolutePathRejected ->
                SkaldVaultV1StorageLayoutRejectionReason.AbsolutePathRejected
            SkaldVaultV1StorageNamespacePathRejectionReason.ContainsTraversal ->
                SkaldVaultV1StorageLayoutRejectionReason.TraversalRejected
            SkaldVaultV1StorageNamespacePathRejectionReason.WrongVaultIdLength ->
                SkaldVaultV1StorageLayoutRejectionReason.InvalidVaultSegment
            SkaldVaultV1StorageNamespacePathRejectionReason.WrongRecordIdLength ->
                SkaldVaultV1StorageLayoutRejectionReason.InvalidRecordSegment
            SkaldVaultV1StorageNamespacePathRejectionReason.UnknownNamespacePolicy ->
                if (namespaceFallback) {
                    SkaldVaultV1StorageLayoutRejectionReason.InvalidStorageNamespace
                } else {
                    fallbackSegmentRejection(segmentFallback)
                }
            else -> fallbackSegmentRejection(segmentFallback)
        }

    private fun fallbackSegmentRejection(
        segmentFallback: InvalidSegmentKind,
    ): SkaldVaultV1StorageLayoutRejectionReason =
        when (segmentFallback) {
            InvalidSegmentKind.Vault -> SkaldVaultV1StorageLayoutRejectionReason.InvalidVaultSegment
            InvalidSegmentKind.Record -> SkaldVaultV1StorageLayoutRejectionReason.InvalidRecordSegment
            InvalidSegmentKind.Manifest -> SkaldVaultV1StorageLayoutRejectionReason.InvalidManifestSegment
            InvalidSegmentKind.Artifact -> SkaldVaultV1StorageLayoutRejectionReason.UnsafeSegmentRejected
        }

    private fun rejected(
        reason: SkaldVaultV1StorageLayoutRejectionReason,
    ): SkaldVaultV1StorageLayoutResult.Rejected =
        SkaldVaultV1StorageLayoutResult.Rejected(
            reason = reason,
            safeMessage = reason.label,
        )
}
