package com.libertasprimordium.skald.domain.security

object SecretPolicy {
    val plannedSecretKinds: List<SecretKind> = listOf(
        SecretKind.BitcoinSeed,
        SecretKind.Bip39Mnemonic,
        SecretKind.DescriptorPrivateKey,
        SecretKind.ImportedPrivateKey,
        SecretKind.NostrNsec,
        SecretKind.BitcoinCoreRpcPassword,
        SecretKind.BitcoinCoreCookie,
        SecretKind.LightningMacaroon,
        SecretKind.CoreLightningRune,
        SecretKind.NwcSecret,
        SecretKind.PhoenixdAuthToken,
        SecretKind.CashuSeed,
        SecretKind.CashuProofMaterial,
        SecretKind.BackupEncryptionKey,
        SecretKind.MetadataEncryptionKey,
    )

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
