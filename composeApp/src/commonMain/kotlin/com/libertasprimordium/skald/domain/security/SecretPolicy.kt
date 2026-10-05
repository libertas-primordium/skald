package com.libertasprimordium.skald.domain.security

object SecretPolicy {
    val plannedSecretKinds: List<SecretKind> = SecretKind.entries.toList()

    fun requiresSecureStorage(kind: SecretKind): Boolean =
        plannedSecretKinds.contains(kind)

    fun placeholderMetadata(
        id: SecretId,
        label: SecretLabel,
        kind: SecretKind,
        recoveryWarning: SecretRecoveryWarning,
    ): SecretMetadata =
        SecretMetadata(
            id = id,
            label = label,
            kind = kind,
            createdAtDescription = "SECRET_STORAGE_NOT_IMPLEMENTED",
            storageStatus = SecretStorageStatus.NotImplemented,
            recoveryWarning = recoveryWarning,
        )
}
