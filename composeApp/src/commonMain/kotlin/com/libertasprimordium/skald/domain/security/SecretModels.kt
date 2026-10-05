package com.libertasprimordium.skald.domain.security

@JvmInline
value class SecretId(val value: String) {
    init {
        require(value in setOf("SECRET_STORAGE_DISABLED", "app-seed", "mnemonic", "backup-key", "metadata-key")) {
            "Unapproved placeholder secret identifier"
        }
    }

    override fun toString(): String = "SecretId(REDACTED)"
}

@JvmInline
value class SecretLabel(val value: String) {
    init {
        require(SecretKind.entries.any { value == "${it.label} metadata" }) {
            "Unapproved placeholder secret label"
        }
    }

    override fun toString(): String = "SecretLabel(REDACTED)"
}

enum class SecretKind(val label: String) {
    MoneroRecoveryMaterial("Monero recovery material"),
    MoneroPrivateSpendKey("Monero private spend key"),
    MoneroPrivateViewKey("Monero private view key"),
    MoneroTransactionSecretMaterial("Monero transaction secret material"),
    MoneroDaemonCredential("Monero daemon credential"),
    MoneroLwsCredential("Monero LWS credential"),
    ImportedPrivateKey("imported private key"),
    TorProxyPassword("Tor proxy password"),
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
    MoneroViewKeyDisclosureRisk("private view-key disclosure exposes wallet scanning information"),
    RemoteNodeExternalBackupRequired("daemon access configuration does not restore local wallet keys or metadata"),
    MoneroRestoreContextRequired("Monero recovery requires supported recovery material and restore context"),
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
    init {
        require(createdAtDescription == "SECRET_STORAGE_NOT_IMPLEMENTED") {
            "Unapproved placeholder creation evidence"
        }
    }

    val containsSecretPayload: Boolean = false

    override fun toString(): String =
        "SecretMetadata(kind=${kind.name}, id=REDACTED, label=REDACTED, createdAtDescription=REDACTED)"
}
