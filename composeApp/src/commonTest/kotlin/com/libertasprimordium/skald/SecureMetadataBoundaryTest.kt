package com.libertasprimordium.skald

import com.libertasprimordium.skald.domain.core.NetworkEnvironment
import com.libertasprimordium.skald.domain.onchain.BitcoinWalletSyncBlocker
import com.libertasprimordium.skald.domain.onchain.BitcoinWalletSyncRequest
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletProfileId
import com.libertasprimordium.skald.domain.onchain.DisabledBitcoinWalletSyncService
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressDerivationIndex
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressSource
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressState
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressWalletContext
import com.libertasprimordium.skald.domain.onchain.ReceiveAddressWalletOperationalState
import com.libertasprimordium.skald.domain.privacy.PrivacySyncFindingCategory
import com.libertasprimordium.skald.domain.privacy.PrivacySyncStatusAnalyzer
import com.libertasprimordium.skald.domain.recovery.RecoverySyncItemState
import com.libertasprimordium.skald.domain.recovery.RecoverySyncStatusPolicy
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletSettingsState
import com.libertasprimordium.skald.security.DisabledSecureSecretStorage
import com.libertasprimordium.skald.security.DisabledSecureWalletMetadataRepository
import com.libertasprimordium.skald.security.SecureMetadataPayload
import com.libertasprimordium.skald.security.SecureMetadataPersistencePolicy
import com.libertasprimordium.skald.security.SecureMetadataRecordDescriptor
import com.libertasprimordium.skald.security.SecureMetadataRecordId
import com.libertasprimordium.skald.security.SecureMetadataRepositoryResult
import com.libertasprimordium.skald.security.SecureMetadataStorageStatus
import com.libertasprimordium.skald.security.SensitiveMetadataKind
import com.libertasprimordium.skald.settings.SettingsStorageKey
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class SecureMetadataBoundaryTest {
    private val repository = DisabledSecureWalletMetadataRepository()
    private val secureStorage = DisabledSecureSecretStorage().capability
    private val secureMetadata = repository.capability

    @Test
    fun everySensitiveMetadataKindRequiresEncryptedMetadataStorage() {
        assertEquals(SensitiveMetadataKind.entries.toSet(), secureMetadata.sensitiveKinds)
        assertTrue(secureMetadata.isFailClosed)
        assertEquals(SecureMetadataStorageStatus.EncryptedVaultUnavailable, secureMetadata.status)
        assertTrue(SensitiveMetadataKind.entries.all { SecureMetadataPersistencePolicy.requiresEncryptedMetadataStorage(it) })
    }

    @Test
    fun disabledSecureMetadataRepositoryRejectsWritesReadsListsAndDeletes() {
        val descriptor = descriptor(SensitiveMetadataKind.ObservedUtxoState)
        val operations = listOf(
            repository.listMetadata(),
            repository.putMetadata(descriptor, SecureMetadataPayload.placeholderOnly()),
            repository.getMetadata(descriptor.id),
            repository.deleteMetadata(descriptor.id),
        )

        assertIs<SecureMetadataRepositoryResult.Disabled>(operations[0])
        assertIs<SecureMetadataRepositoryResult.Rejected>(operations[1])
        assertIs<SecureMetadataRepositoryResult.Unavailable>(operations[2])
        assertIs<SecureMetadataRepositoryResult.Disabled>(operations[3])
        assertTrue(operations.none { it is SecureMetadataRepositoryResult.Success<*> })
    }

    @Test
    fun disabledSecureMetadataRepositoryDoesNotExposePayloadSentinels() {
        val payload = SecureMetadataPayload.placeholderOnly()
        val result = repository.putMetadata(
            descriptor(SensitiveMetadataKind.TransactionNotes),
            payload,
        )

        assertEquals("SecureMetadataPayload(REDACTED)", payload.toString())
        assertEquals("REDACTED_SECURE_METADATA_PAYLOAD", payload.redactedDisplay())
        assertFalse(descriptor(SensitiveMetadataKind.TransactionNotes).toString().contains("SECURE_METADATA_DISABLED"))
        assertFalse(result.toString().contains("OBSERVATION_NOT_PERSISTED"))
        assertFalse(result.toString().contains("UTXO_NOT_PERSISTED"))
        assertFalse(result.toString().contains("ADDRESS_INDEX_NOT_PERSISTED"))
    }

    @Test
    fun mainnetMetadataPersistencePathCannotSucceed() {
        val result = repository.putMetadata(
            descriptor(
                kind = SensitiveMetadataKind.BackendObservationHistory,
                network = NetworkEnvironment.MainnetDisabled,
            ),
            SecureMetadataPayload.placeholderOnly(),
        )

        assertIs<SecureMetadataRepositoryResult.Rejected>(result)
        assertTrue(result.reason.contains("SECURE_METADATA_PERSISTENCE_DISABLED"))
        assertTrue(result.error.safeDetail.contains("Mainnet"))
    }

    @Test
    fun syncPreflightIncludesSecureMetadataPersistenceBlockers() {
        val wallet = wallet()
        val result = DisabledBitcoinWalletSyncService().sync(
            BitcoinWalletSyncRequest(
                backendProfile = null,
                wallet = wallet,
                candidate = displayedAddress(wallet),
                secureStorageCapability = secureStorage,
                secureMetadataCapability = secureMetadata,
            ),
        )

        assertContains(result.blockers, BitcoinWalletSyncBlocker.SecureMetadataPersistenceUnavailable)
        assertContains(result.blockers, BitcoinWalletSyncBlocker.ObservationPersistenceUnavailable)
        assertContains(result.blockers, BitcoinWalletSyncBlocker.AddressIndexPersistenceUnavailable)
        assertFalse(result.observationPersistenceEnabled)
    }

    @Test
    fun recoveryAndPrivacyStatusesUseSecureMetadataBoundary() {
        val wallet = wallet()
        val syncResult = DisabledBitcoinWalletSyncService().sync(
            BitcoinWalletSyncRequest(
                backendProfile = null,
                wallet = wallet,
                candidate = displayedAddress(wallet),
                secureStorageCapability = secureStorage,
                secureMetadataCapability = secureMetadata,
            ),
        )

        val recovery = RecoverySyncStatusPolicy.from(
            syncResult = syncResult,
            descriptorWalletSettings = DescriptorWalletSettingsState.Empty,
            secureStorageCapability = secureStorage,
            secureMetadataCapability = secureMetadata,
        )
        val privacy = PrivacySyncStatusAnalyzer.analyze(
            syncResult = syncResult,
            secureMetadataCapability = secureMetadata,
        )

        assertContains(recovery.items.map { it.label }, "Secure metadata vault")
        assertContains(recovery.items.map { it.label }, "UTXO state persistence")
        assertEquals(
            RecoverySyncItemState.DeferredUntilEncryptedVault,
            recovery.items.single { it.label == "Secure metadata vault" }.state,
        )
        assertContains(privacy.findings.map { it.category }, PrivacySyncFindingCategory.SecureMetadata)
        assertContains(privacy.findings.map { it.title }, "Secure metadata storage unavailable")
    }

    @Test
    fun nonSecretSettingsStorageHasNoSecureMetadataStoreKey() {
        assertTrue(SettingsStorageKey.entries.none { it.name.contains("Metadata", ignoreCase = true) })
        assertTrue(SettingsStorageKey.entries.none { it.fileName.contains("metadata", ignoreCase = true) })
        assertTrue(SettingsStorageKey.entries.none { it.preferenceKey.contains("metadata", ignoreCase = true) })
    }

    private fun descriptor(
        kind: SensitiveMetadataKind,
        network: NetworkEnvironment = NetworkEnvironment.Regtest,
    ): SecureMetadataRecordDescriptor =
        SecureMetadataRecordDescriptor(
            id = SecureMetadataRecordId("SECURE_METADATA_DISABLED"),
            kind = kind,
            network = network,
        )

    private fun wallet(): ReceiveAddressWalletContext =
        ReceiveAddressWalletContext(
            profileId = DescriptorWalletProfileId("secure-metadata-boundary-wallet"),
            profileLabel = "Secure metadata boundary placeholder",
            network = NetworkEnvironment.Regtest,
            source = ReceiveAddressSource.NativeDescriptor,
            operationalState = ReceiveAddressWalletOperationalState.OperationalDevelopmentWallet,
            canDeriveReceiveAddresses = true,
        )

    private fun displayedAddress(wallet: ReceiveAddressWalletContext): ReceiveAddressState =
        ReceiveAddressState.placeholderReserved(
            wallet = wallet,
            derivationIndex = ReceiveAddressDerivationIndex(0),
        ).markDisplayed()
}
