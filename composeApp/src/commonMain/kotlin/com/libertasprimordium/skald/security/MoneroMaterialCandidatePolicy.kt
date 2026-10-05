package com.libertasprimordium.skald.security

/** Caller-declared material classes only; these are never inferred key roles or import formats. */
enum class MoneroMaterialCandidateKind {
    Address,
    PrivateViewKey,
    PrivateSpendKey,
    RecoveryMaterial,
    TransactionSecretMaterial,
    DaemonCredential,
    LwsCredential,
    Unknown,
}

enum class MoneroMaterialRejectionReason {
    EmptyCandidate,
    OversizedCandidate,
    DeclaredMoneroMaterial,
    AmbiguousRawCryptographicMaterial,
    AddressShapedCandidate,
    UnsupportedRawCandidate,
}

/** Payload-free rejection. No candidate text or operation authorization is retained. */
data class MoneroMaterialRejection(
    val declaredKind: MoneroMaterialCandidateKind,
    val reason: MoneroMaterialRejectionReason,
)

object MoneroMaterialCandidatePolicy {
    const val MAX_CANDIDATE_CHARACTERS = 512

    fun reject(
        kind: MoneroMaterialCandidateKind,
        candidate: String?,
    ): MoneroMaterialRejection {
        val reason = when {
            candidate == null || candidate.isEmpty() -> MoneroMaterialRejectionReason.EmptyCandidate
            candidate.length > MAX_CANDIDATE_CHARACTERS -> MoneroMaterialRejectionReason.OversizedCandidate
            candidate.isBlank() -> MoneroMaterialRejectionReason.EmptyCandidate
            kind != MoneroMaterialCandidateKind.Unknown -> MoneroMaterialRejectionReason.DeclaredMoneroMaterial
            isAmbiguousRawCryptographicMaterial(candidate) ->
                MoneroMaterialRejectionReason.AmbiguousRawCryptographicMaterial
            isAddressShapedCandidate(candidate) -> MoneroMaterialRejectionReason.AddressShapedCandidate
            else -> MoneroMaterialRejectionReason.UnsupportedRawCandidate
        }
        return MoneroMaterialRejection(kind, reason)
    }

    /** Rejection hint only. A 32-byte hex shape cannot establish a key role or validity. */
    internal fun isAmbiguousRawCryptographicMaterial(candidate: String): Boolean =
        candidate.length == 64 && candidate.all {
            it in '0'..'9' || it in 'a'..'f' || it in 'A'..'F'
        }

    /**
     * Rejection hint only: no decoding, checksum, network or ownership validation.
     * Length/alphabet evidence: monero-project/monero-docs at
     * 2b50e81860579f0a7dbe3abb306709c5939bb684,
     * docs/en/public-address/standard-address.md and integrated-address.md.
     */
    internal fun isAddressShapedCandidate(candidate: String): Boolean =
        (candidate.length == 95 || candidate.length == 106) && candidate.all {
            it in "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz"
        }
}
