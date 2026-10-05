package com.libertasprimordium.skald.security

import com.libertasprimordium.skald.domain.monero.MoneroNetworkEvidence

@JvmInline
value class SecureMetadataRecordId(val value: String) {
    init {
        require(value == "SECURE_METADATA_DISABLED") { "Unapproved placeholder metadata identifier" }
    }

    override fun toString(): String = "SecureMetadataRecordId(REDACTED)"
}

enum class WalletMetadataSensitivity(val label: String) {
    SensitiveWalletMetadata("sensitive wallet metadata"),
}

enum class SensitiveMetadataKind(
    val label: String,
    val sensitivity: WalletMetadataSensitivity,
) {
    MoneroAccountMetadata("Monero account metadata", WalletMetadataSensitivity.SensitiveWalletMetadata),
    MoneroSubaddressMetadata("Monero subaddress metadata", WalletMetadataSensitivity.SensitiveWalletMetadata),
    MoneroRestoreContext("Monero restore context", WalletMetadataSensitivity.SensitiveWalletMetadata),
    MoneroScanCheckpoint("Monero scan checkpoint", WalletMetadataSensitivity.SensitiveWalletMetadata),
    MoneroOwnedOutputState("Monero owned-output state", WalletMetadataSensitivity.SensitiveWalletMetadata),
    MoneroSpentState("Monero spent state", WalletMetadataSensitivity.SensitiveWalletMetadata),
    MoneroTransactionMetadata("Monero transaction metadata", WalletMetadataSensitivity.SensitiveWalletMetadata),
    MoneroLwsDisclosureHistory("Monero LWS disclosure history", WalletMetadataSensitivity.SensitiveWalletMetadata),
    WalletLabels("wallet labels", WalletMetadataSensitivity.SensitiveWalletMetadata),
    TransactionNotes("transaction notes", WalletMetadataSensitivity.SensitiveWalletMetadata),
    BackendEndpointMetadata("privacy-sensitive backend endpoint metadata", WalletMetadataSensitivity.SensitiveWalletMetadata),
    RoutingMetadata("routing policy and transport metadata", WalletMetadataSensitivity.SensitiveWalletMetadata),
    RecoveryMetadata("recovery metadata", WalletMetadataSensitivity.SensitiveWalletMetadata),
}

enum class SecureMetadataStorageStatus(
    val label: String,
    val availableForSensitiveMetadata: Boolean,
) {
    EncryptedVaultUnavailable("encrypted vault unavailable", availableForSensitiveMetadata = false),
    MetadataPersistenceDisabled("metadata persistence disabled", availableForSensitiveMetadata = false),
    SecureStorageUnavailable("secure storage unavailable", availableForSensitiveMetadata = false),
}

enum class SecureMetadataRequirement(val label: String) {
    AppControlledEncryptedVault("app-controlled encrypted vault required"),
    NoPlaintextSettingsStorage("plaintext settings storage forbidden"),
    NoOsKeyringAsPrimaryStore("OS keyring must not be primary metadata store"),
}

data class SecureMetadataPersistenceCapability(
    val status: SecureMetadataStorageStatus,
    val canListMetadata: Boolean,
    val canStoreMetadata: Boolean,
    val canReadMetadata: Boolean,
    val canDeleteMetadata: Boolean,
    val requirements: Set<SecureMetadataRequirement>,
    val sensitiveKinds: Set<SensitiveMetadataKind>,
    val implementationNote: String,
    val futureImplementationHint: String,
) {
    val isFailClosed: Boolean
        get() = !status.availableForSensitiveMetadata &&
            !canListMetadata &&
            !canStoreMetadata &&
            !canReadMetadata &&
            !canDeleteMetadata
}

data class SecureMetadataPolicyDecision(
    val capability: SecureMetadataPersistenceCapability,
    val requiredKinds: Set<SensitiveMetadataKind>,
    val blockers: Set<SecureMetadataRepositoryError>,
) {
    val canPersistSensitiveMetadata: Boolean
        get() = blockers.isEmpty() && capability.status.availableForSensitiveMetadata
}

object SecureMetadataPersistencePolicy {
    val allSensitiveKinds: Set<SensitiveMetadataKind> = SensitiveMetadataKind.entries.toSet()

    fun requiresEncryptedMetadataStorage(kind: SensitiveMetadataKind): Boolean =
        kind.sensitivity == WalletMetadataSensitivity.SensitiveWalletMetadata

    fun evaluate(
        capability: SecureMetadataPersistenceCapability,
        requestedKinds: Set<SensitiveMetadataKind> = allSensitiveKinds,
    ): SecureMetadataPolicyDecision {
        val blockers = buildSet {
            if (!capability.status.availableForSensitiveMetadata) {
                add(SecureMetadataRepositoryError.EncryptedVaultUnavailable)
            }
            if (!capability.canStoreMetadata || !capability.canReadMetadata) {
                add(SecureMetadataRepositoryError.MetadataPersistenceDisabled)
            }
            if (SecureMetadataRequirement.AppControlledEncryptedVault !in capability.requirements) {
                add(SecureMetadataRepositoryError.EncryptedVaultUnavailable)
            }
        }
        return SecureMetadataPolicyDecision(
            capability = capability,
            requiredKinds = requestedKinds,
            blockers = blockers,
        )
    }
}

data class SecureMetadataRecordDescriptor(
    val id: SecureMetadataRecordId,
    val kind: SensitiveMetadataKind,
    val network: MoneroNetworkEvidence = MoneroNetworkEvidence.Unspecified,
) {
    val containsSensitivePayload: Boolean = false

    override fun toString(): String =
        "SecureMetadataRecordDescriptor(kind=${kind.name}, network=${network.name}, id=REDACTED)"
}

class SecureMetadataPayload private constructor() {
    override fun toString(): String = "SecureMetadataPayload(REDACTED)"

    fun redactedDisplay(): String = "REDACTED_SECURE_METADATA_PAYLOAD"

    companion object {
        fun placeholderOnly(): SecureMetadataPayload = SecureMetadataPayload()
    }
}

sealed interface SecureMetadataRepositoryResult<out T> {
    data class Success<T>(
        val value: T,
    ) : SecureMetadataRepositoryResult<T>

    data class Disabled(
        val error: SecureMetadataRepositoryError,
        val reason: String,
    ) : SecureMetadataRepositoryResult<Nothing>

    data class Rejected(
        val error: SecureMetadataRepositoryError,
        val reason: String,
    ) : SecureMetadataRepositoryResult<Nothing>

    data class Unavailable(
        val error: SecureMetadataRepositoryError,
        val reason: String,
    ) : SecureMetadataRepositoryResult<Nothing>
}

enum class SecureMetadataRepositoryError(val code: String, val safeDetail: String) {
    EncryptedVaultUnavailable(
        code = "ENCRYPTED_VAULT_UNAVAILABLE",
        safeDetail = "Encrypted vault storage is unavailable for sensitive wallet metadata.",
    ),
    MetadataPersistenceDisabled(
        code = "METADATA_PERSISTENCE_DISABLED",
        safeDetail = "Sensitive wallet metadata persistence is disabled until encrypted vault storage exists.",
    ),
    SecureStorageUnavailable(
        code = "SECURE_STORAGE_UNAVAILABLE",
        safeDetail = "Secure storage is unavailable for metadata encryption key material.",
    ),
    ProductionWalletNotOperational(
        code = "PRODUCTION_WALLET_NOT_OPERATIONAL",
        safeDetail = "No operational production wallet state may persist metadata.",
    ),
    MainnetDisabled(
        code = "MAINNET_DISABLED",
        safeDetail = "Mainnet metadata persistence is disabled.",
    ),
}

interface SecureWalletMetadataRepository {
    val capability: SecureMetadataPersistenceCapability

    fun listMetadata(
        kind: SensitiveMetadataKind? = null,
    ): SecureMetadataRepositoryResult<List<SecureMetadataRecordDescriptor>>

    fun putMetadata(
        descriptor: SecureMetadataRecordDescriptor,
        payload: SecureMetadataPayload,
    ): SecureMetadataRepositoryResult<SecureMetadataRecordId>

    fun getMetadata(id: SecureMetadataRecordId): SecureMetadataRepositoryResult<SecureMetadataPayload>

    fun deleteMetadata(id: SecureMetadataRecordId): SecureMetadataRepositoryResult<Unit>
}

class DisabledSecureWalletMetadataRepository(
    override val capability: SecureMetadataPersistenceCapability = commonDisabledSecureMetadataCapability(),
) : SecureWalletMetadataRepository {
    override fun listMetadata(
        kind: SensitiveMetadataKind?,
    ): SecureMetadataRepositoryResult<List<SecureMetadataRecordDescriptor>> =
        SecureMetadataRepositoryResult.Disabled(
            error = SecureMetadataRepositoryError.MetadataPersistenceDisabled,
            reason = disabledReason("list sensitive metadata"),
        )

    override fun putMetadata(
        descriptor: SecureMetadataRecordDescriptor,
        payload: SecureMetadataPayload,
    ): SecureMetadataRepositoryResult<SecureMetadataRecordId> =
        SecureMetadataRepositoryResult.Rejected(
            error = if (descriptor.network == MoneroNetworkEvidence.Mainnet) {
                SecureMetadataRepositoryError.MainnetDisabled
            } else {
                SecureMetadataRepositoryError.EncryptedVaultUnavailable
            },
            reason = disabledReason("store ${descriptor.kind.label}"),
        )

    override fun getMetadata(id: SecureMetadataRecordId): SecureMetadataRepositoryResult<SecureMetadataPayload> =
        SecureMetadataRepositoryResult.Unavailable(
            error = SecureMetadataRepositoryError.EncryptedVaultUnavailable,
            reason = disabledReason("read sensitive metadata"),
        )

    override fun deleteMetadata(id: SecureMetadataRecordId): SecureMetadataRepositoryResult<Unit> =
        SecureMetadataRepositoryResult.Disabled(
            error = SecureMetadataRepositoryError.MetadataPersistenceDisabled,
            reason = disabledReason("delete sensitive metadata"),
        )

    private fun disabledReason(operation: String): String =
        "SECURE_METADATA_PERSISTENCE_DISABLED - cannot $operation until app-controlled encrypted vault storage exists."
}

fun commonDisabledSecureMetadataCapability(): SecureMetadataPersistenceCapability =
    SecureMetadataPersistenceCapability(
        status = SecureMetadataStorageStatus.EncryptedVaultUnavailable,
        canListMetadata = false,
        canStoreMetadata = false,
        canReadMetadata = false,
        canDeleteMetadata = false,
        requirements = setOf(
            SecureMetadataRequirement.AppControlledEncryptedVault,
            SecureMetadataRequirement.NoPlaintextSettingsStorage,
            SecureMetadataRequirement.NoOsKeyringAsPrimaryStore,
        ),
        sensitiveKinds = SecureMetadataPersistencePolicy.allSensitiveKinds,
        implementationNote = "Secure wallet metadata persistence is disabled. Monero account, subaddress, scan, output, transaction, endpoint, routing, and disclosure metadata are not stored.",
        futureImplementationHint = "Future production sync must use app-controlled encrypted vault storage before sensitive wallet metadata can be persisted.",
    )
