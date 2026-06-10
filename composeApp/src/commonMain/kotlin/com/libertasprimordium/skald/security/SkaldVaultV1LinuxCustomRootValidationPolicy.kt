package com.libertasprimordium.skald.security

private const val LINUX_CUSTOM_ROOT_MAX_BYTES = 240

enum class SkaldVaultV1LinuxCustomRootCandidateSource(val label: String) {
    UserSuppliedRootString("user-supplied root string"),
    UserDisplayLabel("user display label"),
    SecretMaterialCandidate("secret material candidate"),
}

enum class SkaldVaultV1LinuxCustomRootValidationFailureReason(val label: String) {
    EmptyCustomRoot("empty custom root"),
    RelativeCustomRootRejected("relative custom root rejected"),
    TildeCustomRootRejected("tilde custom root rejected"),
    RootFilesystemRejected("root filesystem rejected"),
    TempRootRejected("temporary root rejected"),
    SystemRootRejected("system root rejected"),
    RuntimeRootRejected("runtime root rejected"),
    RemovableMediaRootRejected("removable media root rejected"),
    PathTraversalRejected("path traversal rejected"),
    EmptyPathSegmentRejected("empty path segment rejected"),
    ControlCharacterRejected("control character rejected"),
    ContainsWhitespaceRejected("whitespace rejected"),
    WhitespaceOnlySegmentRejected("whitespace-only segment rejected"),
    InvisibleFormatRejected("invisible format character rejected"),
    UnsupportedCharacterRejected("unsupported character rejected"),
    UriLikePrefixRejected("URI-like prefix rejected"),
    WindowsDrivePrefixRejected("Windows drive prefix rejected"),
    SecretMaterialRejected("secret-looking material rejected"),
    UserLabelRejected("user label rejected"),
    TooLong("custom root is too long"),
    SettingsUiMissing("Settings UI missing"),
    SettingsPersistenceMissing("settings persistence missing"),
    RootResolutionMissing("root resolution missing"),
    ContainmentReviewMissing("containment review missing"),
    SymlinkReviewMissing("symlink review missing"),
    PermissionReviewMissing("permission review missing"),
    DurabilityReviewMissing("durability review missing"),
}

sealed class SkaldVaultV1LinuxCustomRootValidationResult<out T> {
    data class Accepted<out T>(val value: T) : SkaldVaultV1LinuxCustomRootValidationResult<T>()

    data class Rejected(
        val reason: SkaldVaultV1LinuxCustomRootValidationFailureReason,
        val safeMessage: String,
    ) : SkaldVaultV1LinuxCustomRootValidationResult<Nothing>()
}

data class SkaldVaultV1LinuxCustomRootCandidate(
    val rawCandidate: String,
    val validationPolicyId: String,
    val acceptedAsStaticPolicyCandidate: Boolean,
    val resolvedOnThisDevice: Boolean,
    val pathExistsProven: Boolean,
    val containmentReviewed: Boolean,
    val symlinkReviewed: Boolean,
    val permissionReviewed: Boolean,
    val durabilityReviewed: Boolean,
    val settingsUiImplemented: Boolean,
    val settingsPersistenceImplemented: Boolean,
    val platformStorageImplemented: Boolean,
    val persistenceEnabled: Boolean,
    val remainingBlockingFailureReasons: Set<SkaldVaultV1LinuxCustomRootValidationFailureReason>,
)

object SkaldVaultV1LinuxCustomRootValidationPolicy {
    const val POLICY_ID = "skald-vault-v1-linux-custom-root-validation-policy-v1"
    const val POLICY_VERSION = 1
    const val MAX_CANDIDATE_BYTES = LINUX_CUSTOM_ROOT_MAX_BYTES

    fun validateCandidate(
        candidate: String,
        source: SkaldVaultV1LinuxCustomRootCandidateSource =
            SkaldVaultV1LinuxCustomRootCandidateSource.UserSuppliedRootString,
    ): SkaldVaultV1LinuxCustomRootValidationResult<SkaldVaultV1LinuxCustomRootCandidate> {
        validateSource(source)?.let { return rejected(it) }
        validateText(candidate)?.let { return rejected(it) }
        return SkaldVaultV1LinuxCustomRootValidationResult.Accepted(
            SkaldVaultV1LinuxCustomRootCandidate(
                rawCandidate = candidate,
                validationPolicyId = POLICY_ID,
                acceptedAsStaticPolicyCandidate = true,
                resolvedOnThisDevice = false,
                pathExistsProven = false,
                containmentReviewed = false,
                symlinkReviewed = false,
                permissionReviewed = false,
                durabilityReviewed = false,
                settingsUiImplemented = false,
                settingsPersistenceImplemented = false,
                platformStorageImplemented = false,
                persistenceEnabled = false,
                remainingBlockingFailureReasons = setOf(
                    SkaldVaultV1LinuxCustomRootValidationFailureReason.SettingsUiMissing,
                    SkaldVaultV1LinuxCustomRootValidationFailureReason.SettingsPersistenceMissing,
                    SkaldVaultV1LinuxCustomRootValidationFailureReason.RootResolutionMissing,
                    SkaldVaultV1LinuxCustomRootValidationFailureReason.ContainmentReviewMissing,
                    SkaldVaultV1LinuxCustomRootValidationFailureReason.SymlinkReviewMissing,
                    SkaldVaultV1LinuxCustomRootValidationFailureReason.PermissionReviewMissing,
                    SkaldVaultV1LinuxCustomRootValidationFailureReason.DurabilityReviewMissing,
                ),
            ),
        )
    }

    private fun validateSource(
        source: SkaldVaultV1LinuxCustomRootCandidateSource,
    ): SkaldVaultV1LinuxCustomRootValidationFailureReason? =
        when (source) {
            SkaldVaultV1LinuxCustomRootCandidateSource.UserSuppliedRootString -> null
            SkaldVaultV1LinuxCustomRootCandidateSource.UserDisplayLabel ->
                SkaldVaultV1LinuxCustomRootValidationFailureReason.UserLabelRejected
            SkaldVaultV1LinuxCustomRootCandidateSource.SecretMaterialCandidate ->
                SkaldVaultV1LinuxCustomRootValidationFailureReason.SecretMaterialRejected
        }

    private fun validateText(candidate: String): SkaldVaultV1LinuxCustomRootValidationFailureReason? {
        val bytes = candidate.encodeToByteArray()
        if (bytes.isEmpty()) {
            return SkaldVaultV1LinuxCustomRootValidationFailureReason.EmptyCustomRoot
        }
        if (bytes.size > LINUX_CUSTOM_ROOT_MAX_BYTES) {
            return SkaldVaultV1LinuxCustomRootValidationFailureReason.TooLong
        }
        if (hasUriLikePrefix(candidate)) {
            return SkaldVaultV1LinuxCustomRootValidationFailureReason.UriLikePrefixRejected
        }
        if (hasWindowsDrivePrefix(candidate)) {
            return SkaldVaultV1LinuxCustomRootValidationFailureReason.WindowsDrivePrefixRejected
        }
        if (candidate.startsWith("~")) {
            return SkaldVaultV1LinuxCustomRootValidationFailureReason.TildeCustomRootRejected
        }
        if (!candidate.startsWith("/")) {
            return SkaldVaultV1LinuxCustomRootValidationFailureReason.RelativeCustomRootRejected
        }
        if (candidate == "/") {
            return SkaldVaultV1LinuxCustomRootValidationFailureReason.RootFilesystemRejected
        }
        if (containsTraversal(candidate)) {
            return SkaldVaultV1LinuxCustomRootValidationFailureReason.PathTraversalRejected
        }
        if (looksLikeSecretMaterial(candidate)) {
            return SkaldVaultV1LinuxCustomRootValidationFailureReason.SecretMaterialRejected
        }

        val segments = candidate.split('/')
        if (segments.drop(1).any { it.isEmpty() }) {
            return SkaldVaultV1LinuxCustomRootValidationFailureReason.EmptyPathSegmentRejected
        }
        if (segments.drop(1).any { it == "." }) {
            return SkaldVaultV1LinuxCustomRootValidationFailureReason.PathTraversalRejected
        }
        if (segments.drop(1).any { it.all(::isAsciiWhitespace) }) {
            return SkaldVaultV1LinuxCustomRootValidationFailureReason.WhitespaceOnlySegmentRejected
        }
        blockedRootReason(segments)?.let { return it }

        candidate.forEach { char ->
            val code = char.code
            if (code in 0x00..0x1f || code == 0x7f) {
                return SkaldVaultV1LinuxCustomRootValidationFailureReason.ControlCharacterRejected
            }
            if (isInvisibleFormat(code)) {
                return SkaldVaultV1LinuxCustomRootValidationFailureReason.InvisibleFormatRejected
            }
            if (isAsciiWhitespace(char)) {
                return SkaldVaultV1LinuxCustomRootValidationFailureReason.ContainsWhitespaceRejected
            }
            if (code !in 0x21..0x7e) {
                return SkaldVaultV1LinuxCustomRootValidationFailureReason.UnsupportedCharacterRejected
            }
            if (!isAllowedLinuxCustomRootCharacter(char)) {
                return SkaldVaultV1LinuxCustomRootValidationFailureReason.UnsupportedCharacterRejected
            }
        }
        return null
    }

    private fun blockedRootReason(
        segments: List<String>,
    ): SkaldVaultV1LinuxCustomRootValidationFailureReason? {
        val first = segments.getOrNull(1)?.toAsciiLower() ?: return null
        val second = segments.getOrNull(2)?.toAsciiLower()
        return when {
            first == "tmp" -> SkaldVaultV1LinuxCustomRootValidationFailureReason.TempRootRejected
            first == "var" && second == "tmp" ->
                SkaldVaultV1LinuxCustomRootValidationFailureReason.TempRootRejected
            first == "dev" || first == "proc" || first == "sys" ->
                SkaldVaultV1LinuxCustomRootValidationFailureReason.SystemRootRejected
            first == "run" -> SkaldVaultV1LinuxCustomRootValidationFailureReason.RuntimeRootRejected
            first == "mnt" || first == "media" ->
                SkaldVaultV1LinuxCustomRootValidationFailureReason.RemovableMediaRootRejected
            else -> null
        }
    }

    private fun isAllowedLinuxCustomRootCharacter(char: Char): Boolean =
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
            lower.startsWith("jar:") ||
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

    private fun looksLikeSecretMaterial(value: String): Boolean {
        val lower = value.toAsciiLower()
        if (lower.contains("mnemonic") ||
            lower.contains("password") ||
            lower.contains("passphrase") ||
            lower.contains("private") ||
            lower.contains("secret") ||
            lower.contains("nsec")
        ) {
            return true
        }
        val compact = lower.replace("/", "").replace("-", "").replace("_", "").replace(".", "")
        if (compact.length >= 64 && compact.all { it in '0'..'9' || it in 'a'..'f' }) {
            return true
        }
        return lower.split('/').any { segment ->
            val compactSegment = segment.replace("-", "").replace("_", "").replace(".", "")
            compactSegment.length >= 64 && compactSegment.all { it in '0'..'9' || it in 'a'..'f' }
        }
    }

    private fun rejected(
        reason: SkaldVaultV1LinuxCustomRootValidationFailureReason,
    ): SkaldVaultV1LinuxCustomRootValidationResult.Rejected =
        SkaldVaultV1LinuxCustomRootValidationResult.Rejected(
            reason = reason,
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
