package com.libertasprimordium.skald.security

private const val LINUX_ROOT_EVIDENCE_MAX_BYTES = 240

enum class SkaldVaultV1LinuxRootResolutionSource(val label: String) {
    NoCandidateEvidence("no candidate evidence"),
    CallerSuppliedStaticDefaultUserDataEvidence("caller-supplied static default user-data evidence"),
    CallerSuppliedStaticCustomRootEvidence("caller-supplied static custom-root evidence"),
    UserDisplayLabel("user display label"),
    SecretMaterialCandidate("secret material candidate"),
    RequiresRealPlatformResolution("requires real platform resolution"),
}

enum class SkaldVaultV1LinuxRootResolutionStatus(val label: String) {
    NoCandidateEvidenceSupplied("no candidate evidence supplied"),
    DefaultLinuxRootEvidenceUnavailable("default Linux root evidence unavailable"),
    Rejected("root-resolution evidence rejected"),
    DefaultRootAcceptedStillDisabled("default root evidence accepted as still-disabled planned evidence"),
    CustomRootAcceptedStillDisabled("custom root evidence accepted as still-disabled planned evidence"),
}

enum class SkaldVaultV1LinuxRootTokenKind(val tokenId: String) {
    DefaultLinuxUserDataRoot("linux-default-user-data-root-token-v1"),
    CustomLinuxCandidateRoot("linux-custom-candidate-root-token-v1"),
    Absent("linux-root-absent-token-v1"),
}

enum class SkaldVaultV1LinuxRootResolutionEvidenceKind(val label: String) {
    None("none"),
    DefaultHomeEvidence("default home evidence"),
    DefaultUserDataBaseEvidence("default user-data-base evidence"),
    CustomRootCandidateEvidence("custom root candidate evidence"),
}

enum class SkaldVaultV1LinuxRootResolutionFailureReason(val label: String) {
    NoCandidateEvidenceSupplied("no candidate evidence supplied"),
    DefaultLinuxRootEvidenceUnavailable("default Linux root evidence unavailable"),
    DefaultHomeEvidenceRejected("caller-supplied default home evidence rejected"),
    DefaultUserDataBaseEvidenceRejected("caller-supplied default user-data-base evidence rejected"),
    CustomRootValidationFailed("caller-supplied custom-root validation failed"),
    CustomRootResolutionEvidenceRejected("caller-supplied custom-root resolution evidence rejected"),
    LabelCredentialSecretWalletMaterialRejected("label, credential, secret, wallet material, or token rejected"),
    RequiresRealPlatformResolution("candidate would require real platform resolution"),
    BlankEvidenceRejected("blank evidence rejected"),
    RelativePathRejected("relative path rejected"),
    TildePathRejected("tilde path rejected"),
    RootFilesystemRejected("root filesystem rejected"),
    TempRootRejected("temporary root rejected"),
    RuntimeRootRejected("runtime root rejected"),
    SystemRootRejected("system root rejected"),
    RemovableMediaRootRejected("removable media root rejected"),
    PathTraversalRejected("path traversal rejected"),
    EmptyPathSegmentRejected("empty path segment rejected"),
    ControlCharacterRejected("control character rejected"),
    ContainsWhitespaceRejected("whitespace rejected"),
    InvisibleFormatRejected("invisible format character rejected"),
    NonAsciiRejected("non-ASCII text rejected"),
    UnsupportedCharacterRejected("unsupported character rejected"),
    UriLikePrefixRejected("uri-like prefix rejected"),
    WindowsDrivePrefixRejected("Windows drive prefix rejected"),
    UncPathRejected("UNC-style path rejected"),
    CredentialUserInfoRejected("credential or user-info string rejected"),
    SecretMaterialRejected("secret-looking material rejected"),
    UserLabelRejected("user label rejected"),
    TooLong("root evidence is too long"),
}

enum class SkaldVaultV1LinuxRootResolutionBlocker(val label: String) {
    AcceptedButPersistenceBlocked("accepted evidence still blocks persistence"),
    FilesystemResolutionAbsent("filesystem resolution absent"),
    PathConstructionAbsent("platform path construction absent"),
    SettingsPersistenceAbsent("settings persistence absent"),
    SafePathConstructionAbsent("safe path construction absent"),
    ContainmentUnverified("containment unverified"),
    SymlinkSafetyUnverified("symlink safety unverified"),
    PermissionsUnverified("permissions unverified"),
    OwnershipUnverified("ownership unverified"),
    DurabilityUnverified("durability unverified"),
    AtomicWriteUnverified("atomic write unverified"),
    ManifestReadWriteAbsent("manifest read/write absent"),
    StorageIndexReadWriteAbsent("storage-index read/write absent"),
    SecureSecretStorageAbsent("secure secret storage absent"),
    SecureMetadataStorageAbsent("secure metadata storage absent"),
    ProviderSelectionDisabled("provider selection disabled"),
    VaultCreationDisabled("vault creation disabled"),
    VaultUnlockDisabled("vault unlock disabled"),
    MainnetDisabled("mainnet disabled"),
}

enum class SkaldVaultV1LinuxRootResolutionWarning(val label: String) {
    StaticEvidenceOnly("static caller-supplied evidence only"),
    RootNotResolved("root is not resolved on a filesystem"),
    RootIsNotAPlatformPath("root token is not a platform path"),
    ExistenceNotProven("existence is not proven"),
    ContainmentNotProven("containment is not proven"),
    SymlinkSafetyNotProven("symlink safety is not proven"),
    PermissionOwnershipNotProven("permission and ownership safety is not proven"),
    DurabilityNotProven("durability is not proven"),
    SettingsPersistenceNotAvailable("Settings persistence is unavailable"),
    VaultCreationNotAvailable("vault creation is unavailable"),
}

class SkaldVaultV1LinuxRootResolutionRequest private constructor(
    val source: SkaldVaultV1LinuxRootResolutionSource,
    val evidenceKind: SkaldVaultV1LinuxRootResolutionEvidenceKind,
    val testOnlyDefaultHomeEvidence: String?,
    val testOnlyDefaultUserDataBaseEvidence: String?,
    val testOnlyCustomRootCandidate: String?,
    val customRootCandidateSource: SkaldVaultV1LinuxCustomRootCandidateSource?,
) {
    override fun toString(): String =
        "SkaldVaultV1LinuxRootResolutionRequest(source=${source.name}, evidenceKind=${evidenceKind.name}, rawEvidence=REDACTED)"

    companion object {
        fun noEvidence(): SkaldVaultV1LinuxRootResolutionRequest =
            SkaldVaultV1LinuxRootResolutionRequest(
                source = SkaldVaultV1LinuxRootResolutionSource.NoCandidateEvidence,
                evidenceKind = SkaldVaultV1LinuxRootResolutionEvidenceKind.None,
                testOnlyDefaultHomeEvidence = null,
                testOnlyDefaultUserDataBaseEvidence = null,
                testOnlyCustomRootCandidate = null,
                customRootCandidateSource = null,
            )

        fun defaultUserDataRoot(
            defaultHomeEvidence: String? = null,
            defaultUserDataBaseEvidence: String?,
            source: SkaldVaultV1LinuxRootResolutionSource =
                SkaldVaultV1LinuxRootResolutionSource.CallerSuppliedStaticDefaultUserDataEvidence,
        ): SkaldVaultV1LinuxRootResolutionRequest =
            SkaldVaultV1LinuxRootResolutionRequest(
                source = source,
                evidenceKind = SkaldVaultV1LinuxRootResolutionEvidenceKind.DefaultUserDataBaseEvidence,
                testOnlyDefaultHomeEvidence = defaultHomeEvidence,
                testOnlyDefaultUserDataBaseEvidence = defaultUserDataBaseEvidence,
                testOnlyCustomRootCandidate = null,
                customRootCandidateSource = null,
            )

        fun customRootCandidate(
            candidate: String?,
            source: SkaldVaultV1LinuxCustomRootCandidateSource =
                SkaldVaultV1LinuxCustomRootCandidateSource.UserSuppliedRootString,
        ): SkaldVaultV1LinuxRootResolutionRequest =
            SkaldVaultV1LinuxRootResolutionRequest(
                source = SkaldVaultV1LinuxRootResolutionSource.CallerSuppliedStaticCustomRootEvidence,
                evidenceKind = SkaldVaultV1LinuxRootResolutionEvidenceKind.CustomRootCandidateEvidence,
                testOnlyDefaultHomeEvidence = null,
                testOnlyDefaultUserDataBaseEvidence = null,
                testOnlyCustomRootCandidate = candidate,
                customRootCandidateSource = source,
            )
    }
}

sealed class SkaldVaultV1LinuxRootResolutionResult<out T> {
    class Accepted<out T>(
        val value: T,
    ) : SkaldVaultV1LinuxRootResolutionResult<T>() {
        override fun toString(): String =
            "SkaldVaultV1LinuxRootResolutionResult.Accepted(rawEvidence=REDACTED)"
    }

    class Rejected(
        val reason: SkaldVaultV1LinuxRootResolutionFailureReason,
        val evidenceKind: SkaldVaultV1LinuxRootResolutionEvidenceKind,
        val nestedReason: SkaldVaultV1LinuxRootResolutionFailureReason? = null,
        val customRootValidationFailureReason: SkaldVaultV1LinuxCustomRootValidationFailureReason? = null,
        val safeMessage: String = reason.label,
    ) : SkaldVaultV1LinuxRootResolutionResult<Nothing>() {
        override fun toString(): String =
            "SkaldVaultV1LinuxRootResolutionResult.Rejected(reason=${reason.name}, rawEvidence=REDACTED)"
    }
}

class SkaldVaultV1LinuxRootToken private constructor(
    val kind: SkaldVaultV1LinuxRootTokenKind,
    private val rawStaticEvidence: String?,
) {
    val containsResolvedPlatformPath: Boolean = false
    val usableForFileIo: Boolean = false
    val redactedSummary: String = "REDACTED_${kind.name}"

    fun testOnlyRawStaticEvidence(): String? = rawStaticEvidence

    override fun toString(): String =
        "SkaldVaultV1LinuxRootToken(kind=${kind.name}, rawEvidence=REDACTED)"

    companion object {
        fun absent(): SkaldVaultV1LinuxRootToken =
            SkaldVaultV1LinuxRootToken(
                kind = SkaldVaultV1LinuxRootTokenKind.Absent,
                rawStaticEvidence = null,
            )

        fun defaultUserDataRoot(rawStaticEvidence: String): SkaldVaultV1LinuxRootToken =
            SkaldVaultV1LinuxRootToken(
                kind = SkaldVaultV1LinuxRootTokenKind.DefaultLinuxUserDataRoot,
                rawStaticEvidence = rawStaticEvidence,
            )

        fun customCandidateRoot(rawStaticEvidence: String): SkaldVaultV1LinuxRootToken =
            SkaldVaultV1LinuxRootToken(
                kind = SkaldVaultV1LinuxRootTokenKind.CustomLinuxCandidateRoot,
                rawStaticEvidence = rawStaticEvidence,
            )
    }
}

data class SkaldVaultV1LinuxRootResolutionCapability(
    val usableForPersistence: Boolean,
    val resolvedOnFilesystem: Boolean,
    val pathConstructed: Boolean,
    val settingsPersistenceAvailable: Boolean,
    val safePathConstructionAvailable: Boolean,
    val containmentVerified: Boolean,
    val symlinkSafetyVerified: Boolean,
    val permissionsVerified: Boolean,
    val ownershipVerified: Boolean,
    val durabilityVerified: Boolean,
    val atomicWriteVerified: Boolean,
    val manifestReadWriteAvailable: Boolean,
    val storageIndexReadWriteAvailable: Boolean,
    val secureSecretStorageAvailable: Boolean,
    val secureMetadataStorageAvailable: Boolean,
    val providerSelectable: Boolean,
    val vaultCreationAvailable: Boolean,
    val vaultUnlockAvailable: Boolean,
    val mainnetAvailable: Boolean,
) {
    companion object {
        val StillDisabled = SkaldVaultV1LinuxRootResolutionCapability(
            usableForPersistence = false,
            resolvedOnFilesystem = false,
            pathConstructed = false,
            settingsPersistenceAvailable = false,
            safePathConstructionAvailable = false,
            containmentVerified = false,
            symlinkSafetyVerified = false,
            permissionsVerified = false,
            ownershipVerified = false,
            durabilityVerified = false,
            atomicWriteVerified = false,
            manifestReadWriteAvailable = false,
            storageIndexReadWriteAvailable = false,
            secureSecretStorageAvailable = false,
            secureMetadataStorageAvailable = false,
            providerSelectable = false,
            vaultCreationAvailable = false,
            vaultUnlockAvailable = false,
            mainnetAvailable = false,
        )
    }
}

data class SkaldVaultV1LinuxRootResolutionEvidence(
    val policyId: String,
    val status: SkaldVaultV1LinuxRootResolutionStatus,
    val source: SkaldVaultV1LinuxRootResolutionSource,
    val evidenceKind: SkaldVaultV1LinuxRootResolutionEvidenceKind,
    val token: SkaldVaultV1LinuxRootToken,
    val capability: SkaldVaultV1LinuxRootResolutionCapability,
    val blockers: Set<SkaldVaultV1LinuxRootResolutionBlocker>,
    val warnings: Set<SkaldVaultV1LinuxRootResolutionWarning>,
) {
    val acceptedAsStillDisabledPlannedEvidence: Boolean = true
    val rawRootStringExposedByDefault: Boolean = false
}

object SkaldVaultV1LinuxRootResolutionPolicy {
    const val POLICY_ID = "skald-vault-v1-linux-root-resolution-policy-v1"
    const val POLICY_VERSION = 1
    const val FUTURE_LINUX_USER_DATA_CONVENTION = "~/.local/share/"
    const val MAX_EVIDENCE_BYTES = LINUX_ROOT_EVIDENCE_MAX_BYTES

    fun evaluate(
        request: SkaldVaultV1LinuxRootResolutionRequest,
    ): SkaldVaultV1LinuxRootResolutionResult<SkaldVaultV1LinuxRootResolutionEvidence> =
        when (request.evidenceKind) {
            SkaldVaultV1LinuxRootResolutionEvidenceKind.None ->
                rejected(
                    reason = SkaldVaultV1LinuxRootResolutionFailureReason.NoCandidateEvidenceSupplied,
                    evidenceKind = request.evidenceKind,
                )
            SkaldVaultV1LinuxRootResolutionEvidenceKind.DefaultHomeEvidence,
            SkaldVaultV1LinuxRootResolutionEvidenceKind.DefaultUserDataBaseEvidence,
            -> evaluateDefaultUserDataRootEvidence(
                defaultHomeEvidence = request.testOnlyDefaultHomeEvidence,
                defaultUserDataBaseEvidence = request.testOnlyDefaultUserDataBaseEvidence,
                source = request.source,
            )
            SkaldVaultV1LinuxRootResolutionEvidenceKind.CustomRootCandidateEvidence ->
                evaluateCustomRootCandidate(
                    candidate = request.testOnlyCustomRootCandidate,
                    source = request.customRootCandidateSource
                        ?: SkaldVaultV1LinuxCustomRootCandidateSource.UserSuppliedRootString,
                )
        }

    fun evaluateDefaultUserDataRootEvidence(
        defaultHomeEvidence: String? = null,
        defaultUserDataBaseEvidence: String?,
        source: SkaldVaultV1LinuxRootResolutionSource =
            SkaldVaultV1LinuxRootResolutionSource.CallerSuppliedStaticDefaultUserDataEvidence,
    ): SkaldVaultV1LinuxRootResolutionResult<SkaldVaultV1LinuxRootResolutionEvidence> {
        validateSource(source)?.let { reason ->
            return rejected(
                reason = reason,
                evidenceKind = SkaldVaultV1LinuxRootResolutionEvidenceKind.DefaultUserDataBaseEvidence,
            )
        }
        if (defaultHomeEvidence != null) {
            validateStaticRootText(defaultHomeEvidence)?.let { reason ->
                return rejected(
                    reason = SkaldVaultV1LinuxRootResolutionFailureReason.DefaultHomeEvidenceRejected,
                    evidenceKind = SkaldVaultV1LinuxRootResolutionEvidenceKind.DefaultHomeEvidence,
                    nestedReason = reason,
                )
            }
        }
        val userDataEvidence = defaultUserDataBaseEvidence ?: return rejected(
            reason = SkaldVaultV1LinuxRootResolutionFailureReason.DefaultLinuxRootEvidenceUnavailable,
            evidenceKind = SkaldVaultV1LinuxRootResolutionEvidenceKind.DefaultUserDataBaseEvidence,
        )
        validateStaticRootText(userDataEvidence)?.let { reason ->
            return rejected(
                reason = SkaldVaultV1LinuxRootResolutionFailureReason.DefaultUserDataBaseEvidenceRejected,
                evidenceKind = SkaldVaultV1LinuxRootResolutionEvidenceKind.DefaultUserDataBaseEvidence,
                nestedReason = reason,
            )
        }
        return accepted(
            status = SkaldVaultV1LinuxRootResolutionStatus.DefaultRootAcceptedStillDisabled,
            source = source,
            evidenceKind = SkaldVaultV1LinuxRootResolutionEvidenceKind.DefaultUserDataBaseEvidence,
            token = SkaldVaultV1LinuxRootToken.defaultUserDataRoot(userDataEvidence),
        )
    }

    fun evaluateCustomRootCandidate(
        candidate: String?,
        source: SkaldVaultV1LinuxCustomRootCandidateSource =
            SkaldVaultV1LinuxCustomRootCandidateSource.UserSuppliedRootString,
    ): SkaldVaultV1LinuxRootResolutionResult<SkaldVaultV1LinuxRootResolutionEvidence> {
        val rawCandidate = candidate ?: return rejected(
            reason = SkaldVaultV1LinuxRootResolutionFailureReason.NoCandidateEvidenceSupplied,
            evidenceKind = SkaldVaultV1LinuxRootResolutionEvidenceKind.CustomRootCandidateEvidence,
        )
        return when (
            val validation = SkaldVaultV1LinuxCustomRootValidationPolicy.validateCandidate(
                candidate = rawCandidate,
                source = source,
            )
        ) {
            is SkaldVaultV1LinuxCustomRootValidationResult.Rejected ->
                rejected(
                    reason = SkaldVaultV1LinuxRootResolutionFailureReason.CustomRootValidationFailed,
                    evidenceKind = SkaldVaultV1LinuxRootResolutionEvidenceKind.CustomRootCandidateEvidence,
                    customRootValidationFailureReason = validation.reason,
                )
            is SkaldVaultV1LinuxCustomRootValidationResult.Accepted ->
                evaluateAcceptedCustomRootValidation(validation.value)
        }
    }

    fun evaluateAcceptedCustomRootValidation(
        candidate: SkaldVaultV1LinuxCustomRootCandidate,
    ): SkaldVaultV1LinuxRootResolutionResult<SkaldVaultV1LinuxRootResolutionEvidence> {
        validateStaticRootText(candidate.rawCandidate)?.let { reason ->
            return rejected(
                reason = SkaldVaultV1LinuxRootResolutionFailureReason.CustomRootResolutionEvidenceRejected,
                evidenceKind = SkaldVaultV1LinuxRootResolutionEvidenceKind.CustomRootCandidateEvidence,
                nestedReason = reason,
            )
        }
        return accepted(
            status = SkaldVaultV1LinuxRootResolutionStatus.CustomRootAcceptedStillDisabled,
            source = SkaldVaultV1LinuxRootResolutionSource.CallerSuppliedStaticCustomRootEvidence,
            evidenceKind = SkaldVaultV1LinuxRootResolutionEvidenceKind.CustomRootCandidateEvidence,
            token = SkaldVaultV1LinuxRootToken.customCandidateRoot(candidate.rawCandidate),
        )
    }

    private fun validateSource(
        source: SkaldVaultV1LinuxRootResolutionSource,
    ): SkaldVaultV1LinuxRootResolutionFailureReason? =
        when (source) {
            SkaldVaultV1LinuxRootResolutionSource.NoCandidateEvidence ->
                SkaldVaultV1LinuxRootResolutionFailureReason.NoCandidateEvidenceSupplied
            SkaldVaultV1LinuxRootResolutionSource.CallerSuppliedStaticDefaultUserDataEvidence,
            SkaldVaultV1LinuxRootResolutionSource.CallerSuppliedStaticCustomRootEvidence,
            -> null
            SkaldVaultV1LinuxRootResolutionSource.UserDisplayLabel ->
                SkaldVaultV1LinuxRootResolutionFailureReason.UserLabelRejected
            SkaldVaultV1LinuxRootResolutionSource.SecretMaterialCandidate ->
                SkaldVaultV1LinuxRootResolutionFailureReason.SecretMaterialRejected
            SkaldVaultV1LinuxRootResolutionSource.RequiresRealPlatformResolution ->
                SkaldVaultV1LinuxRootResolutionFailureReason.RequiresRealPlatformResolution
        }

    private fun validateStaticRootText(
        value: String,
    ): SkaldVaultV1LinuxRootResolutionFailureReason? {
        val bytes = value.encodeToByteArray()
        if (bytes.isEmpty() || value.all(::isAsciiWhitespace)) {
            return SkaldVaultV1LinuxRootResolutionFailureReason.BlankEvidenceRejected
        }
        if (bytes.size > LINUX_ROOT_EVIDENCE_MAX_BYTES) {
            return SkaldVaultV1LinuxRootResolutionFailureReason.TooLong
        }
        if (looksLikeUserLabel(value)) {
            return SkaldVaultV1LinuxRootResolutionFailureReason.UserLabelRejected
        }
        if (hasUriLikePrefix(value)) {
            return SkaldVaultV1LinuxRootResolutionFailureReason.UriLikePrefixRejected
        }
        if (hasWindowsDrivePrefix(value)) {
            return SkaldVaultV1LinuxRootResolutionFailureReason.WindowsDrivePrefixRejected
        }
        if (value.startsWith("\\\\") || value.startsWith("//")) {
            return SkaldVaultV1LinuxRootResolutionFailureReason.UncPathRejected
        }
        if (value.startsWith("~")) {
            return SkaldVaultV1LinuxRootResolutionFailureReason.TildePathRejected
        }
        if (looksLikeCredentialUserInfo(value)) {
            return SkaldVaultV1LinuxRootResolutionFailureReason.CredentialUserInfoRejected
        }
        if (!value.startsWith("/")) {
            return SkaldVaultV1LinuxRootResolutionFailureReason.RelativePathRejected
        }
        if (value == "/") {
            return SkaldVaultV1LinuxRootResolutionFailureReason.RootFilesystemRejected
        }
        if (containsTraversal(value)) {
            return SkaldVaultV1LinuxRootResolutionFailureReason.PathTraversalRejected
        }
        if (looksLikeSecretMaterial(value)) {
            return SkaldVaultV1LinuxRootResolutionFailureReason.SecretMaterialRejected
        }

        val segments = value.split('/')
        if (segments.drop(1).any { it.isEmpty() }) {
            return SkaldVaultV1LinuxRootResolutionFailureReason.EmptyPathSegmentRejected
        }
        if (segments.drop(1).any { it == "." }) {
            return SkaldVaultV1LinuxRootResolutionFailureReason.PathTraversalRejected
        }
        if (segments.drop(1).any { it.all(::isAsciiWhitespace) }) {
            return SkaldVaultV1LinuxRootResolutionFailureReason.BlankEvidenceRejected
        }
        blockedRootReason(segments)?.let { return it }

        value.forEach { char ->
            val code = char.code
            if (code in 0x00..0x1f || code == 0x7f) {
                return SkaldVaultV1LinuxRootResolutionFailureReason.ControlCharacterRejected
            }
            if (isInvisibleFormat(code)) {
                return SkaldVaultV1LinuxRootResolutionFailureReason.InvisibleFormatRejected
            }
            if (isAsciiWhitespace(char)) {
                return SkaldVaultV1LinuxRootResolutionFailureReason.ContainsWhitespaceRejected
            }
            if (code !in 0x21..0x7e) {
                return SkaldVaultV1LinuxRootResolutionFailureReason.NonAsciiRejected
            }
            if (!isAllowedStaticRootCharacter(char)) {
                return SkaldVaultV1LinuxRootResolutionFailureReason.UnsupportedCharacterRejected
            }
        }
        return null
    }

    private fun blockedRootReason(
        segments: List<String>,
    ): SkaldVaultV1LinuxRootResolutionFailureReason? {
        val first = segments.getOrNull(1)?.toAsciiLower() ?: return null
        val second = segments.getOrNull(2)?.toAsciiLower()
        return when {
            first == "tmp" -> SkaldVaultV1LinuxRootResolutionFailureReason.TempRootRejected
            first == "var" && second == "tmp" ->
                SkaldVaultV1LinuxRootResolutionFailureReason.TempRootRejected
            first == "dev" && second == "shm" ->
                SkaldVaultV1LinuxRootResolutionFailureReason.TempRootRejected
            first == "run" || first == "var" && second == "run" ->
                SkaldVaultV1LinuxRootResolutionFailureReason.RuntimeRootRejected
            first == "dev" ||
                first == "proc" ||
                first == "sys" ||
                first == "bin" ||
                first == "sbin" ||
                first == "usr" ||
                first == "lib" ||
                first == "lib64" ||
                first == "etc" ||
                first == "var" ||
                first == "boot" ->
                SkaldVaultV1LinuxRootResolutionFailureReason.SystemRootRejected
            first == "mnt" || first == "media" || first == "volumes" ->
                SkaldVaultV1LinuxRootResolutionFailureReason.RemovableMediaRootRejected
            else -> null
        }
    }

    private fun isAllowedStaticRootCharacter(char: Char): Boolean =
        char == '/' ||
            char == '-' ||
            char == '_' ||
            char == '.' ||
            char in '0'..'9' ||
            char in 'A'..'Z' ||
            char in 'a'..'z'

    private fun isAsciiWhitespace(char: Char): Boolean =
        char.code == 0x20 ||
            char.code == 0x09 ||
            char.code == 0x0a ||
            char.code == 0x0b ||
            char.code == 0x0c ||
            char.code == 0x0d

    private fun isInvisibleFormat(code: Int): Boolean =
        code == 0x200b ||
            code == 0x200c ||
            code == 0x200d ||
            code == 0x2060 ||
            code == 0xfeff

    private fun hasUriLikePrefix(value: String): Boolean {
        val lower = value.toAsciiLower()
        return lower.startsWith("file:") ||
            lower.startsWith("content:") ||
            lower.startsWith("http:") ||
            lower.startsWith("https:") ||
            lower.contains("://")
    }

    private fun hasWindowsDrivePrefix(value: String): Boolean =
        value.length >= 2 &&
            ((value[0] in 'a'..'z') || (value[0] in 'A'..'Z')) &&
            value[1] == ':'

    private fun containsTraversal(value: String): Boolean {
        val lower = value.toAsciiLower()
        return lower.contains("..") ||
            lower.contains("%2e") ||
            lower.contains("%2f") ||
            lower.contains("%5c")
    }

    private fun looksLikeUserLabel(value: String): Boolean {
        val lower = value.toAsciiLower()
        return !value.startsWith("/") &&
            lower.any(::isAsciiWhitespace) &&
            (
                lower.contains("vault") ||
                    lower.contains("wallet") ||
                    lower.contains("storage") ||
                    lower.contains("cold")
                )
    }

    private fun looksLikeCredentialUserInfo(value: String): Boolean {
        val atIndex = value.indexOf('@')
        if (atIndex <= 0) {
            return false
        }
        val colonBeforeAt = value.substring(0, atIndex).indexOf(':')
        return colonBeforeAt >= 0
    }

    private fun looksLikeSecretMaterial(value: String): Boolean {
        val lower = value.toAsciiLower()
        if (lower.contains("password") ||
            lower.contains("passwd") ||
            lower.contains("passphrase") ||
            lower.contains("secret") ||
            lower.contains("token") ||
            lower.contains("key=") ||
            lower.contains("api_key") ||
            lower.contains("nsec") ||
            lower.contains("xprv") ||
            lower.contains("tprv") ||
            lower.contains("seed") ||
            lower.contains("mnemonic") ||
            lower.contains("recovery phrase") ||
            lower.contains("private")
        ) {
            return true
        }
        return lower.split('/').any { segment ->
            val compact = segment.replace("-", "").replace("_", "").replace(".", "")
            compact.length >= 64 && compact.all { it in '0'..'9' || it in 'a'..'f' } ||
                segment.startsWith("nsec1") ||
                segment.startsWith("xprv") ||
                segment.startsWith("tprv") ||
                segment.startsWith("bc1") ||
                segment.startsWith("tb1") ||
                segment.startsWith("bcrt1") ||
                segment.startsWith("wif")
        }
    }

    private fun accepted(
        status: SkaldVaultV1LinuxRootResolutionStatus,
        source: SkaldVaultV1LinuxRootResolutionSource,
        evidenceKind: SkaldVaultV1LinuxRootResolutionEvidenceKind,
        token: SkaldVaultV1LinuxRootToken,
    ): SkaldVaultV1LinuxRootResolutionResult.Accepted<SkaldVaultV1LinuxRootResolutionEvidence> =
        SkaldVaultV1LinuxRootResolutionResult.Accepted(
            SkaldVaultV1LinuxRootResolutionEvidence(
                policyId = POLICY_ID,
                status = status,
                source = source,
                evidenceKind = evidenceKind,
                token = token,
                capability = SkaldVaultV1LinuxRootResolutionCapability.StillDisabled,
                blockers = SkaldVaultV1LinuxRootResolutionBlocker.entries.toSet(),
                warnings = SkaldVaultV1LinuxRootResolutionWarning.entries.toSet(),
            ),
        )

    private fun rejected(
        reason: SkaldVaultV1LinuxRootResolutionFailureReason,
        evidenceKind: SkaldVaultV1LinuxRootResolutionEvidenceKind,
        nestedReason: SkaldVaultV1LinuxRootResolutionFailureReason? = null,
        customRootValidationFailureReason: SkaldVaultV1LinuxCustomRootValidationFailureReason? = null,
    ): SkaldVaultV1LinuxRootResolutionResult.Rejected =
        SkaldVaultV1LinuxRootResolutionResult.Rejected(
            reason = reason,
            evidenceKind = evidenceKind,
            nestedReason = nestedReason,
            customRootValidationFailureReason = customRootValidationFailureReason,
            safeMessage = reason.label,
        )
}

private fun String.toAsciiLower(): String =
    buildString(length) {
        this@toAsciiLower.forEach { char ->
            append(
                if (char in 'A'..'Z') {
                    (char.code + 32).toChar()
                } else {
                    char
                },
            )
        }
    }
