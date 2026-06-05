package com.libertasprimordium.skald.security

import com.libertasprimordium.skald.domain.security.SecretId
import com.libertasprimordium.skald.domain.security.SecretMetadata
import com.libertasprimordium.skald.domain.security.SecretStorageStatus

class DisabledSecureSecretStorage(
    override val capability: SecureStorageCapability = commonDisabledSecureStorageCapability(),
) : SecureSecretStorage {
    override fun listMetadata(): SecureStorageResult<List<SecretMetadata>> =
        SecureStorageResult.Disabled(disabledReason("list secret metadata"))

    override fun putSecret(
        metadata: SecretMetadata,
        secret: SecretPayload,
    ): SecureStorageResult<SecretId> =
        SecureStorageResult.Rejected(disabledReason("store ${metadata.kind.label}"))

    override fun getSecret(id: SecretId): SecureStorageResult<SecretPayload> =
        SecureStorageResult.Unavailable(disabledReason("read secret payload"))

    override fun deleteSecret(id: SecretId): SecureStorageResult<Unit> =
        SecureStorageResult.Disabled(disabledReason("delete secret metadata or payload"))

    private fun disabledReason(operation: String): String =
        "SECRET_STORAGE_NOT_IMPLEMENTED - cannot $operation until an audited secure-storage implementation exists."
}

fun commonDisabledSecureStorageCapability(): SecureStorageCapability =
    SecureStorageCapability(
        platform = SecureStoragePlatform.CommonDisabled,
        status = SecretStorageStatus.NotImplemented,
        canListMetadata = false,
        canStoreSecrets = false,
        canReadSecrets = false,
        canDeleteSecrets = false,
        implementationNote = "Skald Vault has not enabled storage for seeds, private keys, Nostr nsecs, Lightning credentials, Cashu proof material, backend passwords, or backup encryption keys.",
        futureImplementationHint = "Future implementations must use platform-protected or encrypted storage and pass explicit recovery/security tests before any secret-bearing flow is enabled.",
    )

fun androidDisabledSecureStorageCapability(): SecureStorageCapability =
    commonDisabledSecureStorageCapability().copy(
        platform = SecureStoragePlatform.AndroidDisabled,
        implementationNote = "Android secret storage is disabled. Future work should use an audited Android Keystore-backed design, not SharedPreferences or plaintext files.",
        futureImplementationHint = "Android Keystore-backed encryption may be evaluated later, but no key generation or secret persistence exists in this pass.",
    )

fun desktopDisabledSecureStorageCapability(): SecureStorageCapability =
    commonDisabledSecureStorageCapability().copy(
        platform = SecureStoragePlatform.DesktopLinuxDisabled,
        implementationNote = "Linux desktop secret storage is disabled. Future work should use an audited system keyring or encrypted vault design, not plaintext config files.",
        futureImplementationHint = "libsecret, KWallet, or passphrase-encrypted vault designs may be evaluated later, but no secret persistence exists in this pass.",
    )
