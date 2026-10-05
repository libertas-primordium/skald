package com.libertasprimordium.skald.security

/**
 * Positive admission for inert policy evidence only. These domains preserve existing
 * factory encodings and reviewed fixed fixtures; none selects an operational vault format.
 * No trimming, case folding, escaping, arbitrary suffix or wallet-payload admission.
 */
internal object SkaldVaultV1ApprovedInputDomain {
    private val fixedStorageSegments = setOf(
        "skald-vault-v1", "container", "manifest", "records", "index", "tmp",
        "quarantine", "recovery", "current", "pending", "container_pending",
        "manifest_pending", "index_v1", "index_pending", "recovery_v1", "manifest_v1",
        "segment_0001", "vault_fixture",
    )

    fun storageSegment(value: String): Boolean =
        value in fixedStorageSegments || encodedIdentifier(value, "vault_") || encodedIdentifier(value, "record_")

    fun recordIdentifier(value: String): Boolean =
        value == "safe_record_fixture" || encodedIdentifier(value, "record_")

    private fun encodedIdentifier(value: String, prefix: String): Boolean =
        value.length == prefix.length + 32 && value.startsWith(prefix) &&
            value.drop(prefix.length).all { it in '0'..'9' || it in 'a'..'f' }

    // Existing static evidence only. Original path/source/root checks remain mandatory.
    fun rootFixture(value: String): Boolean = value in setOf(
        "/home/skald-test-user", "/home/skald-test-user/.local/share",
        "/home/skaldvault/vault_roots", "/home/testuser/.local/share",
        "/srv/skald-test-vaults/user-data", "/etc/skald-vaults",
        "/vault-root-fixture", "/vault/root",
    )

    fun androidRootEvidence(value: String?): Boolean =
        value == null || value == "android-app-private-internal-root-evidence-fixture" ||
            value == "android-app-private-internal-test-evidence"

    fun publicEvidenceIdentifier(value: String): Boolean =
        value == "public-non-wallet-vector-id" ||
            value == "skald-vault-v1-redaction-leakage-boundary-v1"

    fun syntheticPurpose(family: String, purpose: String): Boolean = when (family) {
        "deterministic-kat" -> purpose in setOf(
            "aad-round-trip", "absent-marker", "future-review", "inert-marker", "positive-case", "safe-review",
        )
        "platform-runtime-kat" -> purpose == "jvm-case" || purpose == "linux-jvm-check"
        "randomized-behavior-kat" -> purpose == "bounds-case" || purpose == "redaction-probe"
        else -> false
    }

    // Exact previously captured evidence and existing synthetic field fixtures only.
    fun calibrationEvidenceText(value: String): Boolean = value in setOf(
        "Android 16", "Google", "Pixel 10 Pro XL", "normal", "foreground", "not recorded",
        "manual note: no thermal repeatability series recorded",
        "manual note: foreground instrumented test runtime",
        "manual note: charging state not recorded",
        "manual note: memory pressure not recorded",
        "Pixel 10 Pro XL Android 16 debug instrumented calibration evidence; high-end class only.",
    )
}
