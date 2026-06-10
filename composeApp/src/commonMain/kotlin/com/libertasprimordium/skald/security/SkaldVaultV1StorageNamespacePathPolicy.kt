package com.libertasprimordium.skald.security

private const val STORAGE_PATH_VAULT_ID_BYTES = 16
private const val STORAGE_PATH_RECORD_ID_BYTES = 16
private const val STORAGE_PATH_MAX_SEGMENT_BYTES = 96

enum class SkaldVaultV1StorageIdentifierSource(val label: String) {
    InternalPolicyConstant("internal policy constant"),
    EncodedIdentifier("encoded identifier"),
    UserControlledText("user-controlled text"),
    SecretMaterialCandidate("secret material candidate"),
}

enum class SkaldVaultV1StorageNamespacePathRejectionReason(val label: String) {
    EmptyIdentifier("empty identifier"),
    DotSegment("dot path segment"),
    ParentSegment("parent path segment"),
    ContainsPathSeparator("contains path separator"),
    ContainsTraversal("contains path traversal"),
    ContainsControlCharacter("contains control character"),
    ContainsWhitespace("contains whitespace"),
    ContainsInvisibleFormat("contains invisible format character"),
    ContainsNonAscii("contains non-ASCII character"),
    ContainsUnsupportedCharacter("contains unsupported character"),
    TooLong("identifier is too long"),
    WrongVaultIdLength("wrong vault id length"),
    WrongRecordIdLength("wrong record id length"),
    UserControlledInputRejected("user-controlled input rejected"),
    SecretMaterialRejected("secret-looking material rejected"),
    AbsolutePathRejected("absolute path rejected"),
    UriLikePrefixRejected("URI-like prefix rejected"),
    WindowsDrivePrefixRejected("Windows drive prefix rejected"),
    UnknownNamespacePolicy("unknown namespace policy"),
}

sealed class SkaldVaultV1StorageNamespacePathResult<out T> {
    data class Accepted<out T>(val value: T) : SkaldVaultV1StorageNamespacePathResult<T>()

    data class Rejected(
        val reason: SkaldVaultV1StorageNamespacePathRejectionReason,
        val safeMessage: String,
    ) : SkaldVaultV1StorageNamespacePathResult<Nothing>()
}

data class SkaldVaultV1StoragePathSegment(
    val value: String,
)

object SkaldVaultV1StorageNamespacePathPolicy {
    const val POLICY_ID = "skald-vault-v1-storage-namespace-path-policy-v1"
    const val POLICY_VERSION = 1
    const val STORAGE_POLICY_ID = "skald-vault-v1-local-manifest-storage-policy-v1"
    const val STORAGE_NAMESPACE_ID = "skald-vault/v1/local-records"
    const val RECORD_NAMESPACE_ID = "skald-vault/v1/records"
    const val MANIFEST_NAMESPACE_ID = "skald-vault/v1/manifests"
    const val MANIFEST_STORAGE_SEGMENT = "manifest_v1"
    const val MAX_SEGMENT_BYTES = STORAGE_PATH_MAX_SEGMENT_BYTES

    fun validateStorageNamespaceId(
        value: String,
        source: SkaldVaultV1StorageIdentifierSource = SkaldVaultV1StorageIdentifierSource.InternalPolicyConstant,
    ): SkaldVaultV1StorageNamespacePathResult<String> =
        validateRequiredNamespace(value = value, expected = STORAGE_NAMESPACE_ID, source = source)

    fun validateRecordNamespaceId(
        value: String,
        source: SkaldVaultV1StorageIdentifierSource = SkaldVaultV1StorageIdentifierSource.InternalPolicyConstant,
    ): SkaldVaultV1StorageNamespacePathResult<String> =
        validateRequiredNamespace(value = value, expected = RECORD_NAMESPACE_ID, source = source)

    fun validateManifestNamespaceId(
        value: String,
        source: SkaldVaultV1StorageIdentifierSource = SkaldVaultV1StorageIdentifierSource.InternalPolicyConstant,
    ): SkaldVaultV1StorageNamespacePathResult<String> =
        validateRequiredNamespace(value = value, expected = MANIFEST_NAMESPACE_ID, source = source)

    fun validateStoragePolicyId(
        value: String,
        source: SkaldVaultV1StorageIdentifierSource = SkaldVaultV1StorageIdentifierSource.InternalPolicyConstant,
    ): SkaldVaultV1StorageNamespacePathResult<String> =
        validateRequiredNamespace(value = value, expected = STORAGE_POLICY_ID, source = source)

    fun validatePathSegment(
        value: String,
        source: SkaldVaultV1StorageIdentifierSource = SkaldVaultV1StorageIdentifierSource.EncodedIdentifier,
    ): SkaldVaultV1StorageNamespacePathResult<SkaldVaultV1StoragePathSegment> {
        validateIdentifierSource(source)?.let { return rejected(it) }
        validateTextShape(value = value, allowNamespaceSlash = false)?.let { return rejected(it) }
        return accepted(SkaldVaultV1StoragePathSegment(value))
    }

    fun encodeVaultStorageId(
        vaultId: ByteArray,
    ): SkaldVaultV1StorageNamespacePathResult<SkaldVaultV1StoragePathSegment> {
        if (vaultId.size != STORAGE_PATH_VAULT_ID_BYTES) {
            return rejected(SkaldVaultV1StorageNamespacePathRejectionReason.WrongVaultIdLength)
        }
        return validatePathSegment("vault_${vaultId.toLowerHex()}")
    }

    fun encodeRecordStorageId(
        recordId: ByteArray,
    ): SkaldVaultV1StorageNamespacePathResult<SkaldVaultV1StoragePathSegment> {
        if (recordId.size != STORAGE_PATH_RECORD_ID_BYTES) {
            return rejected(SkaldVaultV1StorageNamespacePathRejectionReason.WrongRecordIdLength)
        }
        return validatePathSegment("record_${recordId.toLowerHex()}")
    }

    fun manifestStorageSegment(): SkaldVaultV1StorageNamespacePathResult<SkaldVaultV1StoragePathSegment> =
        validatePathSegment(MANIFEST_STORAGE_SEGMENT)

    private fun validateRequiredNamespace(
        value: String,
        expected: String,
        source: SkaldVaultV1StorageIdentifierSource,
    ): SkaldVaultV1StorageNamespacePathResult<String> {
        validateIdentifierSource(source)?.let { return rejected(it) }
        validateTextShape(value = value, allowNamespaceSlash = true)?.let { return rejected(it) }
        if (value != expected) {
            return rejected(SkaldVaultV1StorageNamespacePathRejectionReason.UnknownNamespacePolicy)
        }
        return accepted(value)
    }

    private fun validateIdentifierSource(
        source: SkaldVaultV1StorageIdentifierSource,
    ): SkaldVaultV1StorageNamespacePathRejectionReason? =
        when (source) {
            SkaldVaultV1StorageIdentifierSource.InternalPolicyConstant,
            SkaldVaultV1StorageIdentifierSource.EncodedIdentifier,
            -> null
            SkaldVaultV1StorageIdentifierSource.UserControlledText ->
                SkaldVaultV1StorageNamespacePathRejectionReason.UserControlledInputRejected
            SkaldVaultV1StorageIdentifierSource.SecretMaterialCandidate ->
                SkaldVaultV1StorageNamespacePathRejectionReason.SecretMaterialRejected
        }

    private fun validateTextShape(
        value: String,
        allowNamespaceSlash: Boolean,
    ): SkaldVaultV1StorageNamespacePathRejectionReason? {
        val bytes = value.encodeToByteArray()
        if (bytes.isEmpty()) {
            return SkaldVaultV1StorageNamespacePathRejectionReason.EmptyIdentifier
        }
        if (bytes.size > STORAGE_PATH_MAX_SEGMENT_BYTES) {
            return SkaldVaultV1StorageNamespacePathRejectionReason.TooLong
        }
        if (value == ".") {
            return SkaldVaultV1StorageNamespacePathRejectionReason.DotSegment
        }
        if (value == "..") {
            return SkaldVaultV1StorageNamespacePathRejectionReason.ParentSegment
        }
        if (looksLikeSecretMaterial(value)) {
            return SkaldVaultV1StorageNamespacePathRejectionReason.SecretMaterialRejected
        }
        if (hasUriLikePrefix(value)) {
            return SkaldVaultV1StorageNamespacePathRejectionReason.UriLikePrefixRejected
        }
        if (hasWindowsDrivePrefix(value)) {
            return SkaldVaultV1StorageNamespacePathRejectionReason.WindowsDrivePrefixRejected
        }
        if (value.startsWith("/") || value.startsWith("\\")) {
            return SkaldVaultV1StorageNamespacePathRejectionReason.AbsolutePathRejected
        }
        if (containsTraversal(value)) {
            return SkaldVaultV1StorageNamespacePathRejectionReason.ContainsTraversal
        }
        if (!allowNamespaceSlash && value.contains('/')) {
            return SkaldVaultV1StorageNamespacePathRejectionReason.ContainsPathSeparator
        }
        if (value.contains('\\')) {
            return SkaldVaultV1StorageNamespacePathRejectionReason.ContainsPathSeparator
        }
        if (value.startsWith("-")) {
            return SkaldVaultV1StorageNamespacePathRejectionReason.ContainsUnsupportedCharacter
        }

        var previousWasSlash = false
        value.forEach { char ->
            val code = char.code
            if (code in 0x00..0x1f || code == 0x7f) {
                return SkaldVaultV1StorageNamespacePathRejectionReason.ContainsControlCharacter
            }
            if (isInvisibleFormat(code)) {
                return SkaldVaultV1StorageNamespacePathRejectionReason.ContainsInvisibleFormat
            }
            if (isAsciiWhitespace(code)) {
                return SkaldVaultV1StorageNamespacePathRejectionReason.ContainsWhitespace
            }
            if (code !in 0x21..0x7e) {
                return SkaldVaultV1StorageNamespacePathRejectionReason.ContainsNonAscii
            }
            if (char == '/') {
                if (!allowNamespaceSlash || previousWasSlash) {
                    return SkaldVaultV1StorageNamespacePathRejectionReason.ContainsPathSeparator
                }
                previousWasSlash = true
            } else {
                previousWasSlash = false
                if (!isSafeStorageCharacter(char)) {
                    return SkaldVaultV1StorageNamespacePathRejectionReason.ContainsUnsupportedCharacter
                }
            }
        }
        if (allowNamespaceSlash && value.endsWith("/")) {
            return SkaldVaultV1StorageNamespacePathRejectionReason.ContainsPathSeparator
        }
        return null
    }

    private fun isSafeStorageCharacter(char: Char): Boolean =
        char in 'a'..'z' ||
            char in '0'..'9' ||
            char == '-' ||
            char == '_'

    private fun isAsciiWhitespace(code: Int): Boolean =
        code == 0x20 ||
            code == 0x09 ||
            code == 0x0a ||
            code == 0x0b ||
            code == 0x0c ||
            code == 0x0d

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
        return lower.contains("../") ||
            lower.contains("..\\") ||
            lower.contains("/..") ||
            lower.contains("\\..") ||
            lower.contains("%2e%2e") ||
            lower.contains("%2f") ||
            lower.contains("%5c")
    }

    private fun looksLikeSecretMaterial(value: String): Boolean {
        val lower = value.toAsciiLower()
        if (lower.startsWith("nsec1") ||
            lower.startsWith("xprv") ||
            lower.startsWith("tprv") ||
            lower.startsWith("zprv") ||
            lower.startsWith("yprv") ||
            lower.startsWith("vprv") ||
            lower.startsWith("uprv")
        ) {
            return true
        }
        if (lower.contains("mnemonic") ||
            lower.contains("password") ||
            lower.contains("passphrase") ||
            lower.contains("private") ||
            lower.contains("secret")
        ) {
            return true
        }
        return lower.length >= 64 && lower.all { it in '0'..'9' || it in 'a'..'f' }
    }

    private fun accepted(value: String): SkaldVaultV1StorageNamespacePathResult.Accepted<String> =
        SkaldVaultV1StorageNamespacePathResult.Accepted(value)

    private fun accepted(
        value: SkaldVaultV1StoragePathSegment,
    ): SkaldVaultV1StorageNamespacePathResult.Accepted<SkaldVaultV1StoragePathSegment> =
        SkaldVaultV1StorageNamespacePathResult.Accepted(value)

    private fun rejected(
        reason: SkaldVaultV1StorageNamespacePathRejectionReason,
    ): SkaldVaultV1StorageNamespacePathResult.Rejected =
        SkaldVaultV1StorageNamespacePathResult.Rejected(
            reason = reason,
            safeMessage = reason.label,
        )
}

private fun ByteArray.toLowerHex(): String =
    joinToString(separator = "") { byte ->
        val value = byte.toInt() and 0xff
        val high = value ushr 4
        val low = value and 0x0f
        "${high.toHexChar()}${low.toHexChar()}"
    }

private fun Int.toHexChar(): Char =
    if (this < 10) {
        ('0'.code + this).toChar()
    } else {
        ('a'.code + this - 10).toChar()
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
