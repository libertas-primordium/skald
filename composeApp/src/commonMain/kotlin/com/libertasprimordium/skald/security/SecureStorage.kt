package com.libertasprimordium.skald.security

import com.libertasprimordium.skald.domain.security.SecretId
import com.libertasprimordium.skald.domain.security.SecretMetadata
import com.libertasprimordium.skald.domain.security.SecretStorageStatus

enum class SecureStoragePlatform(val label: String) {
    CommonDisabled("common disabled placeholder"),
    AndroidDisabled("Android disabled placeholder"),
    DesktopLinuxDisabled("Linux desktop disabled placeholder"),
}

data class SecureStorageCapability(
    val platform: SecureStoragePlatform,
    val status: SecretStorageStatus,
    val canListMetadata: Boolean,
    val canStoreSecrets: Boolean,
    val canReadSecrets: Boolean,
    val canDeleteSecrets: Boolean,
    val implementationNote: String,
    val futureImplementationHint: String,
) {
    val isFailClosed: Boolean
        get() = !status.availableForSecretMaterial &&
            !canStoreSecrets &&
            !canReadSecrets &&
            !canDeleteSecrets
}

sealed interface SecureStorageResult<out T> {
    data class Success<T>(
        val value: T,
    ) : SecureStorageResult<T>

    data class Disabled(
        val reason: String,
    ) : SecureStorageResult<Nothing>

    data class Rejected(
        val reason: String,
    ) : SecureStorageResult<Nothing>

    data class Unavailable(
        val reason: String,
    ) : SecureStorageResult<Nothing>
}

class SecretPayload private constructor() {
    override fun toString(): String = "SecretPayload(REDACTED)"

    fun redactedDisplay(): String = "REDACTED_SECRET_PAYLOAD"

    companion object {
        fun placeholderOnly(): SecretPayload = SecretPayload()
    }
}

interface SecureSecretStorage {
    val capability: SecureStorageCapability

    fun listMetadata(): SecureStorageResult<List<SecretMetadata>>

    fun putSecret(
        metadata: SecretMetadata,
        secret: SecretPayload,
    ): SecureStorageResult<SecretId>

    fun getSecret(id: SecretId): SecureStorageResult<SecretPayload>

    fun deleteSecret(id: SecretId): SecureStorageResult<Unit>
}

data class SecureStorageUiStatus(
    val title: String,
    val state: String,
    val detail: String,
    val plannedSecretClasses: List<String>,
    val disabledActions: List<String>,
)

fun SecureStorageCapability.toUiStatus(): SecureStorageUiStatus =
    SecureStorageUiStatus(
        title = "Secret storage",
        state = "disabled / not implemented",
        detail = implementationNote,
        plannedSecretClasses = listOf(
            "App seed",
            "Imported private keys",
            "Nostr nsec",
            "Bitcoin Core credentials",
            "Lightning node credentials",
            "NWC secrets",
            "Cashu recovery/proof material",
            "Backup encryption keys",
        ),
        disabledActions = listOf(
            "Initialize secret storage",
            "Import secret",
            "Export secret metadata",
        ),
    )
