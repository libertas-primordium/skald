package com.libertasprimordium.skald.domain.security

@JvmInline
value class SecretId(val value: String)

@JvmInline
value class SecretLabel(val value: String)

enum class SecretKind(val label: String) {
    BitcoinSeed("Bitcoin seed"),
    Bip39Mnemonic("BIP39 mnemonic"),
    DescriptorPrivateKey("descriptor private key"),
    ImportedPrivateKey("imported private key"),
    NostrNsec("Nostr nsec"),
    BitcoinCoreRpcPassword("Bitcoin Core RPC password"),
    BitcoinCoreCookie("Bitcoin Core cookie"),
    ElectrumCredential("Electrum credential"),
    EsploraCredential("Esplora credential"),
    TorProxyPassword("Tor proxy password"),
    LightningMacaroon("Lightning macaroon"),
    CoreLightningRune("Core Lightning rune"),
    NwcSecret("NWC secret"),
    PhoenixdAuthToken("Phoenixd auth token"),
    CashuSeed("Cashu seed"),
    CashuProofMaterial("Cashu proof material"),
    BackupEncryptionKey("backup encryption key"),
    MetadataEncryptionKey("metadata encryption key"),
}

sealed interface SecretStorageStatus {
    val label: String
    val availableForSecretMaterial: Boolean

    data object NotImplemented : SecretStorageStatus {
        override val label: String = "not implemented"
        override val availableForSecretMaterial: Boolean = false
    }

    data object DisabledByPolicy : SecretStorageStatus {
        override val label: String = "disabled by policy"
        override val availableForSecretMaterial: Boolean = false
    }

    data object PlatformUnavailable : SecretStorageStatus {
        override val label: String = "platform unavailable"
        override val availableForSecretMaterial: Boolean = false
    }

    data object RequiresUserSetup : SecretStorageStatus {
        override val label: String = "requires user setup"
        override val availableForSecretMaterial: Boolean = false
    }

    data object AvailableButUnused : SecretStorageStatus {
        override val label: String = "available but unused"
        override val availableForSecretMaterial: Boolean = false
    }
}

enum class SecretRecoveryWarning(val label: String) {
    NativeSeedRequired("native seed recovery requires real seed storage"),
    SeparateImportedKeyBackupRequired("imported keys require separate backup"),
    NostrIdentityKeyRisk("Nostr identity keys can compromise identity and funds"),
    RemoteNodeExternalBackupRequired("remote-node credentials and funds require external backup"),
    CashuMintAndProofStateRequired("Cashu recovery depends on mint support and proof state"),
    BackupKeyRequired("backup encryption keys require a secure storage design"),
    MetadataEncryptionRequired("metadata encryption keys require a secure storage design"),
}

data class SecretMetadata(
    val id: SecretId,
    val label: SecretLabel,
    val kind: SecretKind,
    val createdAtDescription: String,
    val storageStatus: SecretStorageStatus,
    val recoveryWarning: SecretRecoveryWarning,
) {
    val containsSecretPayload: Boolean = false
}
