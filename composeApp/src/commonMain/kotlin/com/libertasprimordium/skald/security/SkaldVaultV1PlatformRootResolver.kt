package com.libertasprimordium.skald.security

interface SkaldVaultV1PlatformRootResolver {
    fun resolve(
        request: SkaldVaultV1PlatformRootResolverRequest,
    ): SkaldVaultV1PlatformRootResolverResult<SkaldVaultV1PlatformRootResolverEvidence>
}

enum class SkaldVaultV1PlatformRootResolverSource(val label: String) {
    NoEvidence("no platform root evidence supplied"),
    AndroidAppPrivateInternalEvidence("Android app-private internal root evidence"),
    AndroidAppPrivateInternalUnavailable("Android app-private internal root evidence unavailable"),
    AndroidExternalSharedEvidence("Android external or shared root evidence"),
    AndroidUserSelectedRootEvidence("Android user-selected root evidence"),
    LinuxDefaultRootSnapshot("Linux default root evidence snapshot"),
    LinuxCustomRootCandidate("Linux custom root candidate evidence"),
    LinuxCustomRootValidationResult("Linux custom root validation result evidence"),
    UnknownRootEvidence("unknown platform root evidence"),
}

enum class SkaldVaultV1PlatformRootKind(val label: String) {
    AndroidAppPrivateInternal("Android app-private internal root token"),
    AndroidExternalShared("Android external or shared root rejected token"),
    AndroidUserSelected("Android user-selected root rejected token"),
    LinuxXdgDataHome("Linux XDG-style user-data root token"),
    LinuxHomeFallbackUserData("Linux home fallback user-data root token"),
    LinuxCustomCandidate("Linux custom candidate root token"),
    None("no platform root token"),
}

enum class SkaldVaultV1PlatformRootResolverStatus(val label: String) {
    NoEvidenceAvailable("no platform root evidence available"),
    AndroidAppPrivateEvidenceAcceptedStillDisabled(
        "Android app-private internal evidence accepted as still-disabled root evidence",
    ),
    AndroidAppPrivateEvidenceUnavailable("Android app-private internal root evidence unavailable"),
    AndroidExternalSharedRootRejected("Android external or shared root rejected"),
    AndroidUserSelectedRootRejected("Android user-selected root rejected"),
    LinuxDefaultRootEvidenceAcceptedStillDisabled(
        "Linux default root evidence accepted as still-disabled root evidence",
    ),
    LinuxRootEvidenceUnavailable("Linux root evidence unavailable"),
    LinuxRootEvidenceRejected("Linux root evidence rejected"),
    LinuxCustomRootAcceptedStillDisabled(
        "Linux custom-root evidence accepted as still-disabled root evidence",
    ),
    LinuxCustomRootRejected("Linux custom-root evidence rejected"),
    UnknownRootEvidenceRejected("unknown root evidence rejected"),
}

enum class SkaldVaultV1PlatformRootResolverFailureReason(val label: String) {
    NoCandidateEvidenceSupplied("no candidate platform root evidence supplied"),
    AndroidAppPrivateRootEvidenceUnavailable("Android app-private internal root evidence unavailable"),
    AndroidExternalSharedRootRejected("Android external or shared root rejected"),
    AndroidUserSelectedRootRejected("Android user-selected root rejected"),
    UnknownPlatformRootRejected("unknown platform root evidence rejected"),
    LinuxDefaultRootEvidenceUnavailable("Linux default root evidence unavailable"),
    LinuxRootEvidenceRejected("Linux root evidence rejected"),
    LinuxCustomRootValidationFailed("Linux custom-root validation failed"),
    LinuxCustomRootEvidenceUnavailable("Linux custom-root evidence unavailable"),
    RequiresRealPlatformResolution("candidate would require real platform resolution"),
    AcceptedButPersistenceBlocked("accepted root evidence remains blocked from persistence"),
}

enum class SkaldVaultV1PlatformRootResolverBlocker(val label: String) {
    AcceptedButPersistenceBlocked("accepted root evidence is not usable for persistence"),
    ProviderSelectionBlocked("provider selection remains blocked"),
    VaultCreationBlocked("vault creation remains unavailable"),
    VaultUnlockBlocked("vault unlock remains unavailable"),
    VaultPersistenceBlocked("vault persistence remains unavailable"),
    SettingsPersistenceMissing("Settings persistence remains unavailable"),
    StorageImplementationMissing("storage implementation remains unavailable"),
    SafePathConstructionMissing("safe path construction remains unavailable"),
    RealPathContainmentMissing("real path containment remains unverified"),
    SymlinkSafetyMissing("symlink safety remains unverified"),
    PermissionOwnershipMissing("permission and ownership checks remain unverified"),
    DurabilityMissing("durability remains unverified"),
    AtomicWriteMissing("atomic write remains unverified"),
    CrashRecoveryMissing("crash recovery remains unavailable"),
    ManifestReadWriteMissing("manifest read/write remains unavailable"),
    StorageIndexReadWriteMissing("storage index read/write remains unavailable"),
    SecureSecretStorageMissing("secure secret storage remains unavailable"),
    SecureMetadataStorageMissing("secure metadata storage remains unavailable"),
    AntiRollbackAnchorMissing("anti-rollback anchor remains unavailable"),
    MainnetDisabled("mainnet remains unavailable"),
}

enum class SkaldVaultV1PlatformRootResolverWarning(val label: String) {
    EvidenceOnly("root evidence is a still-disabled model only"),
    RootStringsRedacted("raw root strings are redacted by default"),
    TokenIsNotPlatformPath("root token is not a platform path"),
    AndroidRootResolutionFutureWork("Android root resolution remains future work"),
    LinuxDefaultResolutionFutureWork("Linux default root resolution remains future work"),
    LinuxCustomRootSettingsFutureWork("Linux custom roots remain future Settings work"),
}

data class SkaldVaultV1PlatformRootResolverCapability(
    val usableForPersistence: Boolean,
    val providerSelectable: Boolean,
    val vaultCreationAvailable: Boolean,
    val vaultUnlockAvailable: Boolean,
    val vaultPersistenceAvailable: Boolean,
    val settingsPersistenceAvailable: Boolean,
    val storageImplementationAvailable: Boolean,
    val manifestReadWriteAvailable: Boolean,
    val storageIndexReadWriteAvailable: Boolean,
    val atomicWriteAvailable: Boolean,
    val crashRecoveryAvailable: Boolean,
    val secureSecretStorageAvailable: Boolean,
    val secureMetadataStorageAvailable: Boolean,
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
        val StillDisabled = SkaldVaultV1PlatformRootResolverCapability(
            usableForPersistence = false,
            providerSelectable = false,
            vaultCreationAvailable = false,
            vaultUnlockAvailable = false,
            vaultPersistenceAvailable = false,
            settingsPersistenceAvailable = false,
            storageImplementationAvailable = false,
            manifestReadWriteAvailable = false,
            storageIndexReadWriteAvailable = false,
            atomicWriteAvailable = false,
            crashRecoveryAvailable = false,
            secureSecretStorageAvailable = false,
            secureMetadataStorageAvailable = false,
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

class SkaldVaultV1LinuxRootResolverInputSnapshot private constructor(
    private val xdgDataHomeEvidence: String?,
    private val homeFallbackUserDataEvidence: String?,
) {
    fun testOnlyXdgDataHomeEvidence(): String? = xdgDataHomeEvidence

    fun testOnlyHomeFallbackUserDataEvidence(): String? = homeFallbackUserDataEvidence

    internal fun xdgDataHomeEvidenceOrNull(): String? = xdgDataHomeEvidence

    internal fun homeFallbackUserDataEvidenceOrNull(): String? = homeFallbackUserDataEvidence

    override fun toString(): String =
        "SkaldVaultV1LinuxRootResolverInputSnapshot(" +
            "xdgDataHomeEvidence=<redacted>, " +
            "homeFallbackUserDataEvidence=<redacted>" +
            ")"

    companion object {
        fun defaultRootEvidence(
            xdgDataHomeEvidence: String? = null,
            homeFallbackUserDataEvidence: String? = null,
        ): SkaldVaultV1LinuxRootResolverInputSnapshot =
            SkaldVaultV1LinuxRootResolverInputSnapshot(
                xdgDataHomeEvidence = xdgDataHomeEvidence,
                homeFallbackUserDataEvidence = homeFallbackUserDataEvidence,
            )
    }
}

class SkaldVaultV1PlatformRootResolverRequest private constructor(
    val source: SkaldVaultV1PlatformRootResolverSource,
    val rootKind: SkaldVaultV1PlatformRootKind,
    private val androidStaticEvidence: String?,
    private val linuxSnapshot: SkaldVaultV1LinuxRootResolverInputSnapshot?,
    private val linuxCustomRootCandidate: String?,
    private val linuxCustomRootValidationResult:
        SkaldVaultV1LinuxCustomRootValidationResult<SkaldVaultV1LinuxCustomRootCandidate>?,
) {
    fun testOnlyAndroidStaticEvidence(): String? = androidStaticEvidence

    fun testOnlyLinuxCustomRootCandidate(): String? = linuxCustomRootCandidate

    internal fun androidStaticEvidenceOrNull(): String? = androidStaticEvidence

    internal fun linuxSnapshotOrNull(): SkaldVaultV1LinuxRootResolverInputSnapshot? = linuxSnapshot

    internal fun linuxCustomRootCandidateOrNull(): String? = linuxCustomRootCandidate

    internal fun linuxCustomRootValidationResultOrNull():
        SkaldVaultV1LinuxCustomRootValidationResult<SkaldVaultV1LinuxCustomRootCandidate>? =
        linuxCustomRootValidationResult

    override fun toString(): String =
        "SkaldVaultV1PlatformRootResolverRequest(" +
            "source=$source, " +
            "rootKind=$rootKind, " +
            "androidStaticEvidence=<redacted>, " +
            "linuxSnapshot=<redacted>, " +
            "linuxCustomRootCandidate=<redacted>" +
            ")"

    companion object {
        fun noEvidence(): SkaldVaultV1PlatformRootResolverRequest =
            SkaldVaultV1PlatformRootResolverRequest(
                source = SkaldVaultV1PlatformRootResolverSource.NoEvidence,
                rootKind = SkaldVaultV1PlatformRootKind.None,
                androidStaticEvidence = null,
                linuxSnapshot = null,
                linuxCustomRootCandidate = null,
                linuxCustomRootValidationResult = null,
            )

        fun androidAppPrivateInternalEvidence(
            staticEvidence: String? = null,
            available: Boolean = true,
        ): SkaldVaultV1PlatformRootResolverRequest =
            SkaldVaultV1PlatformRootResolverRequest(
                source = if (available) {
                    SkaldVaultV1PlatformRootResolverSource.AndroidAppPrivateInternalEvidence
                } else {
                    SkaldVaultV1PlatformRootResolverSource.AndroidAppPrivateInternalUnavailable
                },
                rootKind = SkaldVaultV1PlatformRootKind.AndroidAppPrivateInternal,
                androidStaticEvidence = staticEvidence,
                linuxSnapshot = null,
                linuxCustomRootCandidate = null,
                linuxCustomRootValidationResult = null,
            )

        fun androidExternalSharedEvidence(
            staticEvidence: String? = null,
        ): SkaldVaultV1PlatformRootResolverRequest =
            SkaldVaultV1PlatformRootResolverRequest(
                source = SkaldVaultV1PlatformRootResolverSource.AndroidExternalSharedEvidence,
                rootKind = SkaldVaultV1PlatformRootKind.AndroidExternalShared,
                androidStaticEvidence = staticEvidence,
                linuxSnapshot = null,
                linuxCustomRootCandidate = null,
                linuxCustomRootValidationResult = null,
            )

        fun androidUserSelectedRootEvidence(
            staticEvidence: String? = null,
        ): SkaldVaultV1PlatformRootResolverRequest =
            SkaldVaultV1PlatformRootResolverRequest(
                source = SkaldVaultV1PlatformRootResolverSource.AndroidUserSelectedRootEvidence,
                rootKind = SkaldVaultV1PlatformRootKind.AndroidUserSelected,
                androidStaticEvidence = staticEvidence,
                linuxSnapshot = null,
                linuxCustomRootCandidate = null,
                linuxCustomRootValidationResult = null,
            )

        fun linuxDefaultRootEvidence(
            snapshot: SkaldVaultV1LinuxRootResolverInputSnapshot,
        ): SkaldVaultV1PlatformRootResolverRequest =
            SkaldVaultV1PlatformRootResolverRequest(
                source = SkaldVaultV1PlatformRootResolverSource.LinuxDefaultRootSnapshot,
                rootKind = SkaldVaultV1PlatformRootKind.LinuxXdgDataHome,
                androidStaticEvidence = null,
                linuxSnapshot = snapshot,
                linuxCustomRootCandidate = null,
                linuxCustomRootValidationResult = null,
            )

        fun linuxCustomRootCandidate(candidate: String?): SkaldVaultV1PlatformRootResolverRequest =
            SkaldVaultV1PlatformRootResolverRequest(
                source = SkaldVaultV1PlatformRootResolverSource.LinuxCustomRootCandidate,
                rootKind = SkaldVaultV1PlatformRootKind.LinuxCustomCandidate,
                androidStaticEvidence = null,
                linuxSnapshot = null,
                linuxCustomRootCandidate = candidate,
                linuxCustomRootValidationResult = null,
            )

        fun linuxCustomRootValidationResult(
            result: SkaldVaultV1LinuxCustomRootValidationResult<SkaldVaultV1LinuxCustomRootCandidate>,
        ): SkaldVaultV1PlatformRootResolverRequest =
            SkaldVaultV1PlatformRootResolverRequest(
                source = SkaldVaultV1PlatformRootResolverSource.LinuxCustomRootValidationResult,
                rootKind = SkaldVaultV1PlatformRootKind.LinuxCustomCandidate,
                androidStaticEvidence = null,
                linuxSnapshot = null,
                linuxCustomRootCandidate = null,
                linuxCustomRootValidationResult = result,
            )

        fun unknownRootEvidence(): SkaldVaultV1PlatformRootResolverRequest =
            SkaldVaultV1PlatformRootResolverRequest(
                source = SkaldVaultV1PlatformRootResolverSource.UnknownRootEvidence,
                rootKind = SkaldVaultV1PlatformRootKind.None,
                androidStaticEvidence = null,
                linuxSnapshot = null,
                linuxCustomRootCandidate = null,
                linuxCustomRootValidationResult = null,
            )
    }
}

class SkaldVaultV1PlatformRootEvidenceToken private constructor(
    val kind: SkaldVaultV1PlatformRootKind,
    private val rawStaticEvidence: String?,
) {
    val containsResolvedPlatformPath: Boolean = false
    val usableForFileIo: Boolean = false

    fun testOnlyRawStaticEvidence(): String? = rawStaticEvidence

    override fun toString(): String =
        "SkaldVaultV1PlatformRootEvidenceToken(" +
            "kind=$kind, " +
            "rawStaticEvidence=<redacted>, " +
            "containsResolvedPlatformPath=false, " +
            "usableForFileIo=false" +
            ")"

    companion object {
        fun accepted(
            kind: SkaldVaultV1PlatformRootKind,
            rawStaticEvidence: String?,
        ): SkaldVaultV1PlatformRootEvidenceToken =
            SkaldVaultV1PlatformRootEvidenceToken(
                kind = kind,
                rawStaticEvidence = rawStaticEvidence,
            )
    }
}

data class SkaldVaultV1PlatformRootResolverEvidence(
    val policyId: String,
    val policyVersion: Int,
    val status: SkaldVaultV1PlatformRootResolverStatus,
    val source: SkaldVaultV1PlatformRootResolverSource,
    val rootKind: SkaldVaultV1PlatformRootKind,
    val token: SkaldVaultV1PlatformRootEvidenceToken,
    val capability: SkaldVaultV1PlatformRootResolverCapability,
    val blockers: Set<SkaldVaultV1PlatformRootResolverBlocker>,
    val warnings: Set<SkaldVaultV1PlatformRootResolverWarning>,
    val linuxRootResolutionStatus: SkaldVaultV1LinuxRootResolutionStatus? = null,
    val rawRootStringExposedByDefault: Boolean = false,
    val platformRootResolverStillDisabled: Boolean = true,
    val rootEvidenceOnly: Boolean = true,
    val platformPathObjectReturned: Boolean = false,
)

sealed class SkaldVaultV1PlatformRootResolverResult<out T> {
    data class Accepted<out T>(val value: T) : SkaldVaultV1PlatformRootResolverResult<T>() {
        override fun toString(): String = "Accepted(value=<redacted-platform-root-evidence>)"
    }

    data class Rejected(
        val reason: SkaldVaultV1PlatformRootResolverFailureReason,
        val status: SkaldVaultV1PlatformRootResolverStatus,
        val source: SkaldVaultV1PlatformRootResolverSource,
        val rootKind: SkaldVaultV1PlatformRootKind,
        val safeMessage: String = reason.label,
        val linuxRootResolutionReason: SkaldVaultV1LinuxRootResolutionFailureReason? = null,
        val linuxRootResolutionNestedReason: SkaldVaultV1LinuxRootResolutionFailureReason? = null,
        val customRootValidationFailureReason: SkaldVaultV1LinuxCustomRootValidationFailureReason? = null,
    ) : SkaldVaultV1PlatformRootResolverResult<Nothing>() {
        override fun toString(): String =
            "Rejected(" +
                "reason=$reason, " +
                "status=$status, " +
                "source=$source, " +
                "rootKind=$rootKind, " +
                "safeMessage=$safeMessage, " +
                "linuxRootResolutionReason=$linuxRootResolutionReason, " +
                "linuxRootResolutionNestedReason=$linuxRootResolutionNestedReason, " +
                "customRootValidationFailureReason=$customRootValidationFailureReason" +
                ")"
    }
}

object SkaldVaultV1PlatformRootResolverPolicy : SkaldVaultV1PlatformRootResolver {
    const val POLICY_ID = "skald-vault-v1-platform-root-resolver-boundary-v1"
    const val POLICY_VERSION = 1

    override fun resolve(
        request: SkaldVaultV1PlatformRootResolverRequest,
    ): SkaldVaultV1PlatformRootResolverResult<SkaldVaultV1PlatformRootResolverEvidence> =
        when (request.source) {
            SkaldVaultV1PlatformRootResolverSource.NoEvidence -> reject(
                reason = SkaldVaultV1PlatformRootResolverFailureReason.NoCandidateEvidenceSupplied,
                status = SkaldVaultV1PlatformRootResolverStatus.NoEvidenceAvailable,
                source = request.source,
                rootKind = request.rootKind,
            )
            SkaldVaultV1PlatformRootResolverSource.AndroidAppPrivateInternalEvidence ->
                acceptAndroidAppPrivate(request)
            SkaldVaultV1PlatformRootResolverSource.AndroidAppPrivateInternalUnavailable -> reject(
                reason = SkaldVaultV1PlatformRootResolverFailureReason.AndroidAppPrivateRootEvidenceUnavailable,
                status = SkaldVaultV1PlatformRootResolverStatus.AndroidAppPrivateEvidenceUnavailable,
                source = request.source,
                rootKind = request.rootKind,
            )
            SkaldVaultV1PlatformRootResolverSource.AndroidExternalSharedEvidence -> reject(
                reason = SkaldVaultV1PlatformRootResolverFailureReason.AndroidExternalSharedRootRejected,
                status = SkaldVaultV1PlatformRootResolverStatus.AndroidExternalSharedRootRejected,
                source = request.source,
                rootKind = request.rootKind,
            )
            SkaldVaultV1PlatformRootResolverSource.AndroidUserSelectedRootEvidence -> reject(
                reason = SkaldVaultV1PlatformRootResolverFailureReason.AndroidUserSelectedRootRejected,
                status = SkaldVaultV1PlatformRootResolverStatus.AndroidUserSelectedRootRejected,
                source = request.source,
                rootKind = request.rootKind,
            )
            SkaldVaultV1PlatformRootResolverSource.LinuxDefaultRootSnapshot ->
                resolveLinuxDefaultSnapshot(request)
            SkaldVaultV1PlatformRootResolverSource.LinuxCustomRootCandidate ->
                resolveLinuxCustomCandidate(request)
            SkaldVaultV1PlatformRootResolverSource.LinuxCustomRootValidationResult ->
                resolveLinuxCustomValidationResult(request)
            SkaldVaultV1PlatformRootResolverSource.UnknownRootEvidence -> reject(
                reason = SkaldVaultV1PlatformRootResolverFailureReason.UnknownPlatformRootRejected,
                status = SkaldVaultV1PlatformRootResolverStatus.UnknownRootEvidenceRejected,
                source = request.source,
                rootKind = request.rootKind,
            )
        }

    private fun acceptAndroidAppPrivate(
        request: SkaldVaultV1PlatformRootResolverRequest,
    ): SkaldVaultV1PlatformRootResolverResult.Accepted<SkaldVaultV1PlatformRootResolverEvidence> =
        accept(
            status = SkaldVaultV1PlatformRootResolverStatus.AndroidAppPrivateEvidenceAcceptedStillDisabled,
            source = request.source,
            rootKind = SkaldVaultV1PlatformRootKind.AndroidAppPrivateInternal,
            rawStaticEvidence = request.androidStaticEvidenceOrNull(),
            warnings = setOf(
                SkaldVaultV1PlatformRootResolverWarning.EvidenceOnly,
                SkaldVaultV1PlatformRootResolverWarning.RootStringsRedacted,
                SkaldVaultV1PlatformRootResolverWarning.TokenIsNotPlatformPath,
                SkaldVaultV1PlatformRootResolverWarning.AndroidRootResolutionFutureWork,
            ),
        )

    private fun resolveLinuxDefaultSnapshot(
        request: SkaldVaultV1PlatformRootResolverRequest,
    ): SkaldVaultV1PlatformRootResolverResult<SkaldVaultV1PlatformRootResolverEvidence> {
        val snapshot = request.linuxSnapshotOrNull()
            ?: return reject(
                reason = SkaldVaultV1PlatformRootResolverFailureReason.LinuxDefaultRootEvidenceUnavailable,
                status = SkaldVaultV1PlatformRootResolverStatus.LinuxRootEvidenceUnavailable,
                source = request.source,
                rootKind = request.rootKind,
            )
        val xdgEvidence = snapshot.xdgDataHomeEvidenceOrNull()
        if (xdgEvidence != null) {
            return convertLinuxDefaultEvidence(
                result = SkaldVaultV1LinuxRootResolutionPolicy.evaluateDefaultUserDataRootEvidence(
                    defaultUserDataBaseEvidence = xdgEvidence,
                    source = SkaldVaultV1LinuxRootResolutionSource.CallerSuppliedStaticDefaultUserDataEvidence,
                ),
                source = request.source,
                rootKind = SkaldVaultV1PlatformRootKind.LinuxXdgDataHome,
            )
        }
        val fallbackEvidence = snapshot.homeFallbackUserDataEvidenceOrNull()
        if (fallbackEvidence != null) {
            return convertLinuxDefaultEvidence(
                result = SkaldVaultV1LinuxRootResolutionPolicy.evaluateDefaultUserDataRootEvidence(
                    defaultUserDataBaseEvidence = fallbackEvidence,
                    source = SkaldVaultV1LinuxRootResolutionSource.CallerSuppliedStaticDefaultUserDataEvidence,
                ),
                source = request.source,
                rootKind = SkaldVaultV1PlatformRootKind.LinuxHomeFallbackUserData,
            )
        }
        return reject(
            reason = SkaldVaultV1PlatformRootResolverFailureReason.LinuxDefaultRootEvidenceUnavailable,
            status = SkaldVaultV1PlatformRootResolverStatus.LinuxRootEvidenceUnavailable,
            source = request.source,
            rootKind = request.rootKind,
        )
    }

    private fun resolveLinuxCustomCandidate(
        request: SkaldVaultV1PlatformRootResolverRequest,
    ): SkaldVaultV1PlatformRootResolverResult<SkaldVaultV1PlatformRootResolverEvidence> {
        val candidate = request.linuxCustomRootCandidateOrNull()
            ?: return reject(
                reason = SkaldVaultV1PlatformRootResolverFailureReason.LinuxCustomRootEvidenceUnavailable,
                status = SkaldVaultV1PlatformRootResolverStatus.LinuxCustomRootRejected,
                source = request.source,
                rootKind = request.rootKind,
            )
        return convertLinuxCustomEvidence(
            result = SkaldVaultV1LinuxRootResolutionPolicy.evaluateCustomRootCandidate(candidate),
            source = request.source,
        )
    }

    private fun resolveLinuxCustomValidationResult(
        request: SkaldVaultV1PlatformRootResolverRequest,
    ): SkaldVaultV1PlatformRootResolverResult<SkaldVaultV1PlatformRootResolverEvidence> =
        when (val result = request.linuxCustomRootValidationResultOrNull()) {
            null -> reject(
                reason = SkaldVaultV1PlatformRootResolverFailureReason.LinuxCustomRootEvidenceUnavailable,
                status = SkaldVaultV1PlatformRootResolverStatus.LinuxCustomRootRejected,
                source = request.source,
                rootKind = request.rootKind,
            )
            is SkaldVaultV1LinuxCustomRootValidationResult.Accepted -> convertLinuxCustomEvidence(
                result = SkaldVaultV1LinuxRootResolutionPolicy.evaluateAcceptedCustomRootValidation(result.value),
                source = request.source,
            )
            is SkaldVaultV1LinuxCustomRootValidationResult.Rejected -> reject(
                reason = SkaldVaultV1PlatformRootResolverFailureReason.LinuxCustomRootValidationFailed,
                status = SkaldVaultV1PlatformRootResolverStatus.LinuxCustomRootRejected,
                source = request.source,
                rootKind = request.rootKind,
                customRootValidationFailureReason = result.reason,
            )
        }

    private fun convertLinuxDefaultEvidence(
        result: SkaldVaultV1LinuxRootResolutionResult<SkaldVaultV1LinuxRootResolutionEvidence>,
        source: SkaldVaultV1PlatformRootResolverSource,
        rootKind: SkaldVaultV1PlatformRootKind,
    ): SkaldVaultV1PlatformRootResolverResult<SkaldVaultV1PlatformRootResolverEvidence> =
        when (result) {
            is SkaldVaultV1LinuxRootResolutionResult.Accepted -> accept(
                status = SkaldVaultV1PlatformRootResolverStatus.LinuxDefaultRootEvidenceAcceptedStillDisabled,
                source = source,
                rootKind = rootKind,
                rawStaticEvidence = result.value.token.testOnlyRawStaticEvidence(),
                linuxRootResolutionStatus = result.value.status,
                warnings = setOf(
                    SkaldVaultV1PlatformRootResolverWarning.EvidenceOnly,
                    SkaldVaultV1PlatformRootResolverWarning.RootStringsRedacted,
                    SkaldVaultV1PlatformRootResolverWarning.TokenIsNotPlatformPath,
                    SkaldVaultV1PlatformRootResolverWarning.LinuxDefaultResolutionFutureWork,
                ),
            )
            is SkaldVaultV1LinuxRootResolutionResult.Rejected -> reject(
                reason = SkaldVaultV1PlatformRootResolverFailureReason.LinuxRootEvidenceRejected,
                status = SkaldVaultV1PlatformRootResolverStatus.LinuxRootEvidenceRejected,
                source = source,
                rootKind = rootKind,
                linuxRootResolutionReason = result.reason,
                linuxRootResolutionNestedReason = result.nestedReason,
                customRootValidationFailureReason = result.customRootValidationFailureReason,
            )
        }

    private fun convertLinuxCustomEvidence(
        result: SkaldVaultV1LinuxRootResolutionResult<SkaldVaultV1LinuxRootResolutionEvidence>,
        source: SkaldVaultV1PlatformRootResolverSource,
    ): SkaldVaultV1PlatformRootResolverResult<SkaldVaultV1PlatformRootResolverEvidence> =
        when (result) {
            is SkaldVaultV1LinuxRootResolutionResult.Accepted -> accept(
                status = SkaldVaultV1PlatformRootResolverStatus.LinuxCustomRootAcceptedStillDisabled,
                source = source,
                rootKind = SkaldVaultV1PlatformRootKind.LinuxCustomCandidate,
                rawStaticEvidence = result.value.token.testOnlyRawStaticEvidence(),
                linuxRootResolutionStatus = result.value.status,
                warnings = setOf(
                    SkaldVaultV1PlatformRootResolverWarning.EvidenceOnly,
                    SkaldVaultV1PlatformRootResolverWarning.RootStringsRedacted,
                    SkaldVaultV1PlatformRootResolverWarning.TokenIsNotPlatformPath,
                    SkaldVaultV1PlatformRootResolverWarning.LinuxCustomRootSettingsFutureWork,
                ),
            )
            is SkaldVaultV1LinuxRootResolutionResult.Rejected -> {
                val reason =
                    if (result.reason == SkaldVaultV1LinuxRootResolutionFailureReason.CustomRootValidationFailed) {
                        SkaldVaultV1PlatformRootResolverFailureReason.LinuxCustomRootValidationFailed
                    } else {
                        SkaldVaultV1PlatformRootResolverFailureReason.LinuxRootEvidenceRejected
                    }
                reject(
                    reason = reason,
                    status = SkaldVaultV1PlatformRootResolverStatus.LinuxCustomRootRejected,
                    source = source,
                    rootKind = SkaldVaultV1PlatformRootKind.LinuxCustomCandidate,
                    linuxRootResolutionReason = result.reason,
                    linuxRootResolutionNestedReason = result.nestedReason,
                    customRootValidationFailureReason = result.customRootValidationFailureReason,
                )
            }
        }

    private fun accept(
        status: SkaldVaultV1PlatformRootResolverStatus,
        source: SkaldVaultV1PlatformRootResolverSource,
        rootKind: SkaldVaultV1PlatformRootKind,
        rawStaticEvidence: String?,
        warnings: Set<SkaldVaultV1PlatformRootResolverWarning>,
        linuxRootResolutionStatus: SkaldVaultV1LinuxRootResolutionStatus? = null,
    ): SkaldVaultV1PlatformRootResolverResult.Accepted<SkaldVaultV1PlatformRootResolverEvidence> =
        SkaldVaultV1PlatformRootResolverResult.Accepted(
            SkaldVaultV1PlatformRootResolverEvidence(
                policyId = POLICY_ID,
                policyVersion = POLICY_VERSION,
                status = status,
                source = source,
                rootKind = rootKind,
                token = SkaldVaultV1PlatformRootEvidenceToken.accepted(
                    kind = rootKind,
                    rawStaticEvidence = rawStaticEvidence,
                ),
                capability = SkaldVaultV1PlatformRootResolverCapability.StillDisabled,
                blockers = SkaldVaultV1PlatformRootResolverBlocker.entries.toSet(),
                warnings = warnings,
                linuxRootResolutionStatus = linuxRootResolutionStatus,
            ),
        )

    private fun reject(
        reason: SkaldVaultV1PlatformRootResolverFailureReason,
        status: SkaldVaultV1PlatformRootResolverStatus,
        source: SkaldVaultV1PlatformRootResolverSource,
        rootKind: SkaldVaultV1PlatformRootKind,
        linuxRootResolutionReason: SkaldVaultV1LinuxRootResolutionFailureReason? = null,
        linuxRootResolutionNestedReason: SkaldVaultV1LinuxRootResolutionFailureReason? = null,
        customRootValidationFailureReason: SkaldVaultV1LinuxCustomRootValidationFailureReason? = null,
    ): SkaldVaultV1PlatformRootResolverResult.Rejected =
        SkaldVaultV1PlatformRootResolverResult.Rejected(
            reason = reason,
            status = status,
            source = source,
            rootKind = rootKind,
            linuxRootResolutionReason = linuxRootResolutionReason,
            linuxRootResolutionNestedReason = linuxRootResolutionNestedReason,
            customRootValidationFailureReason = customRootValidationFailureReason,
        )
}
