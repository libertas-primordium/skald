package com.libertasprimordium.skald.domain.onchain

import com.libertasprimordium.skald.domain.core.NetworkEnvironment
import com.libertasprimordium.skald.security.EncryptedVaultReadiness
import com.libertasprimordium.skald.security.EncryptedVaultReadinessPolicy
import com.libertasprimordium.skald.security.SecureMetadataPersistenceCapability
import com.libertasprimordium.skald.security.SecureMetadataPersistencePolicy
import com.libertasprimordium.skald.security.SecureStorageCapability
import com.libertasprimordium.skald.security.commonDisabledEncryptedVaultReadiness
import com.libertasprimordium.skald.security.commonDisabledSecureMetadataCapability

@JvmInline
value class BitcoinWalletSyncServiceId(val value: String) {
    init {
        require(value.isNotBlank()) { "Bitcoin wallet sync service id must not be blank." }
    }
}

enum class BitcoinWalletSyncStatus(val label: String) {
    NotConfigured("not configured"),
    EndpointInvalid("endpoint invalid"),
    MainnetRejected("mainnet rejected"),
    NoOperationalWallet("no operational wallet"),
    CredentialsUnavailable("credentials unavailable"),
    ProductionSyncDisabled("production sync disabled"),
}

enum class BitcoinWalletSyncCapability(val label: String, val enabledInProduction: Boolean) {
    PreflightPolicyEvaluation("preflight policy evaluation", enabledInProduction = true),
    EndpointPolicyEvaluation("endpoint policy evaluation", enabledInProduction = true),
    BackendAdapterBoundary("backend adapter boundary", enabledInProduction = true),
    BackendObservationSummaryTarget("backend observation summary target", enabledInProduction = true),
    ReceiveAddressPolicyBoundary("receive-address policy boundary", enabledInProduction = true),
    CredentialReferenceMetadataOnly("credential reference metadata only", enabledInProduction = true),
    EncryptedVaultReadinessBoundary("encrypted vault readiness boundary", enabledInProduction = true),
    SecureMetadataPersistenceBoundary("secure metadata persistence boundary", enabledInProduction = true),
    FutureProductionSync("future production sync", enabledInProduction = false),
    FutureObservationPersistence("future observation persistence", enabledInProduction = false),
    FutureAddressIndexPersistence("future address index persistence", enabledInProduction = false),
    NoProductionNetworking("no production networking", enabledInProduction = true),
    NoSigning("no signing", enabledInProduction = true),
    NoBroadcasting("no broadcasting", enabledInProduction = true),
    NoMainnet("no mainnet", enabledInProduction = true),
}

enum class BitcoinWalletSyncBlocker(val label: String) {
    SyncDisabled("sync disabled"),
    BackendNotConfigured("backend not configured"),
    EndpointInvalid("endpoint invalid"),
    CredentialMaterialRejected("credential material rejected"),
    MainnetDisabled("mainnet disabled"),
    NoOperationalWallet("no operational wallet"),
    WalletContextMissing("wallet context missing"),
    AddressCandidateMissing("receive-address candidate missing"),
    EncryptedVaultUnavailable("encrypted vault unavailable"),
    SecureStorageUnavailable("secure storage unavailable"),
    ProductionBackendDisabled("production backend disabled"),
    CredentialsUnavailable("credentials unavailable"),
    SecureMetadataPersistenceUnavailable("secure metadata persistence unavailable"),
    ObservationPersistenceUnavailable("observation persistence unavailable"),
    AddressIndexPersistenceUnavailable("address index persistence unavailable"),
    ReceiveAddressPolicyBlocked("receive-address policy blocked"),
}

enum class BitcoinWalletSyncWarning(val label: String) {
    ProductionSyncDisabled("production sync disabled"),
    NoNetworkAttempted("no network attempted"),
    NoObservationPersisted("no observation persisted"),
    NoSecureMetadataPersisted("no secure metadata persisted"),
    EndpointPolicyOnly("endpoint parsing is policy-only"),
    PublicBackendPrivacyLeak("public backend can observe wallet queries"),
    BackendCanLinkWalletQueries("backend can link wallet queries"),
    OnionTorLabelPreserved("onion/Tor labeling preserved"),
    TorTransportNotImplemented("Tor transport not implemented"),
    UserOwnedNodePreferred("user-owned node preferred"),
    CredentialsRequireSecureStorage("credentials require secure storage"),
    EncryptedVaultUnavailable("encrypted vault unavailable"),
    EncryptedVaultReadinessOnly("encrypted vault readiness is policy-only"),
    MetadataRequiresEncryptedVault("metadata requires encrypted vault"),
    ReceiveAddressPolicyRequired("receive-address policy required"),
    CoinControlRequiredBeforeSpend("coin control required before spending"),
    NoSkaldManagedInfrastructure("no Skald-managed infrastructure"),
}

sealed interface BitcoinWalletSyncError {
    val code: String
    val safeDetail: String

    data object SyncDisabled : BitcoinWalletSyncError {
        override val code: String = "PRODUCTION_WALLET_SYNC_DISABLED"
        override val safeDetail: String =
            "Production wallet sync is disabled. No backend client, BDK sync, persistence, signing, or broadcast was attempted."
    }

    data object BackendNotConfigured : BitcoinWalletSyncError {
        override val code: String = "BACKEND_NOT_CONFIGURED"
        override val safeDetail: String = "No backend profile is selected for production sync preflight."
    }

    data object EndpointInvalid : BitcoinWalletSyncError {
        override val code: String = "BACKEND_ENDPOINT_INVALID"
        override val safeDetail: String = "Backend endpoint metadata failed Skald endpoint policy validation."
    }

    data object MainnetDisabled : BitcoinWalletSyncError {
        override val code: String = "MAINNET_DISABLED"
        override val safeDetail: String = "Mainnet sync remains disabled by Skald policy."
    }

    data object NoOperationalWallet : BitcoinWalletSyncError {
        override val code: String = "NO_OPERATIONAL_WALLET"
        override val safeDetail: String =
            "No operational development wallet context is available for production sync preflight."
    }

    data object CredentialsUnavailable : BitcoinWalletSyncError {
        override val code: String = "BACKEND_CREDENTIALS_UNAVAILABLE"
        override val safeDetail: String =
            "Credential references cannot be used until secure storage is implemented and available."
    }
}

data class BitcoinWalletSyncRequest(
    val backendProfile: BitcoinBackendProfile?,
    val backendValidation: BackendProfileValidationResult? = null,
    val wallet: ReceiveAddressWalletContext?,
    val candidate: ReceiveAddressState? = null,
    val secureStorageCapability: SecureStorageCapability,
    val encryptedVaultReadiness: EncryptedVaultReadiness = commonDisabledEncryptedVaultReadiness(),
    val secureMetadataCapability: SecureMetadataPersistenceCapability = commonDisabledSecureMetadataCapability(),
    val observationPersistenceAvailable: Boolean = false,
    val networkPolicy: BitcoinBackendNetworkPolicy = BitcoinBackendNetworkPolicy.DevelopmentOnly,
    val connectionPolicy: BitcoinBackendConnectionPolicy = BitcoinBackendConnectionPolicy.Disabled,
) {
    val requestedNetwork: NetworkEnvironment?
        get() = backendProfile?.network ?: wallet?.network ?: candidate?.network
}

data class BitcoinWalletSyncPreflight(
    val status: BitcoinWalletSyncStatus,
    val requestedNetwork: NetworkEnvironment?,
    val backendProfileId: BitcoinBackendProfileId?,
    val walletProfileId: DescriptorWalletProfileId?,
    val warnings: Set<BitcoinWalletSyncWarning>,
    val blockers: Set<BitcoinWalletSyncBlocker>,
    val capabilities: Set<BitcoinWalletSyncCapability>,
    val endpointValidationErrors: List<BackendProfileValidationError>,
    val receiveAddressDecision: ReceiveAddressPolicyDecision?,
) {
    val canAttemptProductionSync: Boolean = false

    val productionNetworkingEnabled: Boolean = false

    val productionSyncEnabled: Boolean = false

    val observationPersistenceEnabled: Boolean = false

    val signingOrBroadcastEnabled: Boolean = false

    val mainnetEnabled: Boolean = false
}

data class BitcoinWalletSyncResult(
    val serviceId: BitcoinWalletSyncServiceId,
    val status: BitcoinWalletSyncStatus,
    val preflight: BitcoinWalletSyncPreflight,
    val backendObservationResult: BitcoinBackendObservationResult?,
    val observationSummary: BackendObservationSummary?,
    val warnings: Set<BitcoinWalletSyncWarning>,
    val blockers: Set<BitcoinWalletSyncBlocker>,
    val capabilities: Set<BitcoinWalletSyncCapability>,
    val error: BitcoinWalletSyncError?,
    val diagnostic: String,
) {
    val productionNetworkingEnabled: Boolean = false

    val productionSyncEnabled: Boolean = false

    val observationPersistenceEnabled: Boolean = false

    val signingOrBroadcastEnabled: Boolean = false

    val mainnetEnabled: Boolean = false

    val usesSkaldManagedInfrastructure: Boolean = false
}

object BitcoinWalletSyncRequestFactory {
    fun disabledPreflightRequest(
        backendSettings: BitcoinBackendSettingsState,
        descriptorWalletSettings: DescriptorWalletSettingsState,
        secureStorageCapability: SecureStorageCapability,
        secureMetadataCapability: SecureMetadataPersistenceCapability = commonDisabledSecureMetadataCapability(),
    ): BitcoinWalletSyncRequest {
        val walletContext = descriptorWalletSettings.selectedProfile?.let(ReceiveAddressWalletContext::fromProfile)
        return BitcoinWalletSyncRequest(
            backendProfile = backendSettings.selectedProfile,
            backendValidation = backendSettings.selectedProfile?.toSyncValidationResult(),
            wallet = walletContext,
            candidate = walletContext?.let { wallet ->
                ReceiveAddressState.placeholderReserved(
                    wallet = wallet,
                    derivationIndex = ReceiveAddressDerivationIndex(0),
                ).markDisplayed()
            },
            secureStorageCapability = secureStorageCapability,
            encryptedVaultReadiness = commonDisabledEncryptedVaultReadiness(),
            secureMetadataCapability = secureMetadataCapability,
        )
    }

    private fun BitcoinBackendProfile.toSyncValidationResult(): BackendProfileValidationResult =
        BitcoinBackendValidator.validate(
            EditableBitcoinBackendProfileInput(
                id = id,
                label = label,
                type = type,
                network = network,
                host = when (endpoint) {
                    BackendNotConfigured -> ""
                    is HttpEndpoint -> endpoint.host
                    is TcpEndpoint -> endpoint.host
                },
                portText = when (endpoint) {
                    BackendNotConfigured -> ""
                    is HttpEndpoint -> endpoint.port?.toString().orEmpty()
                    is TcpEndpoint -> endpoint.port.toString()
                },
                useTls = when (endpoint) {
                    BackendNotConfigured -> false
                    is HttpEndpoint -> endpoint.useTls
                    is TcpEndpoint -> endpoint.useTls
                },
                path = when (endpoint) {
                    BackendNotConfigured -> ""
                    is HttpEndpoint -> endpoint.path.orEmpty()
                    is TcpEndpoint -> ""
                },
                trustModel = trustModel,
            ),
        )
}

interface BitcoinWalletSyncService {
    val id: BitcoinWalletSyncServiceId

    fun preflight(request: BitcoinWalletSyncRequest): BitcoinWalletSyncPreflight

    fun sync(request: BitcoinWalletSyncRequest): BitcoinWalletSyncResult
}

object BitcoinWalletSyncPolicy {
    fun preflight(request: BitcoinWalletSyncRequest): BitcoinWalletSyncPreflight {
        val warnings = mutableSetOf(
            BitcoinWalletSyncWarning.ProductionSyncDisabled,
            BitcoinWalletSyncWarning.NoNetworkAttempted,
            BitcoinWalletSyncWarning.NoObservationPersisted,
            BitcoinWalletSyncWarning.NoSecureMetadataPersisted,
            BitcoinWalletSyncWarning.EndpointPolicyOnly,
            BitcoinWalletSyncWarning.MetadataRequiresEncryptedVault,
            BitcoinWalletSyncWarning.ReceiveAddressPolicyRequired,
            BitcoinWalletSyncWarning.NoSkaldManagedInfrastructure,
        )
        val blockers = mutableSetOf(
            BitcoinWalletSyncBlocker.SyncDisabled,
            BitcoinWalletSyncBlocker.ProductionBackendDisabled,
        )

        if (!request.observationPersistenceAvailable) {
            blockers += BitcoinWalletSyncBlocker.ObservationPersistenceUnavailable
            blockers += BitcoinWalletSyncBlocker.AddressIndexPersistenceUnavailable
        }
        val vaultDecision = EncryptedVaultReadinessPolicy.evaluate(
            readiness = request.encryptedVaultReadiness,
            secureStorageCapability = request.secureStorageCapability,
            secureMetadataCapability = request.secureMetadataCapability,
        )
        if (!vaultDecision.canEnableProductionPersistence) {
            blockers += BitcoinWalletSyncBlocker.EncryptedVaultUnavailable
            warnings += BitcoinWalletSyncWarning.EncryptedVaultUnavailable
            warnings += BitcoinWalletSyncWarning.EncryptedVaultReadinessOnly
            warnings += BitcoinWalletSyncWarning.MetadataRequiresEncryptedVault
        }
        val metadataDecision = SecureMetadataPersistencePolicy.evaluate(request.secureMetadataCapability)
        if (!metadataDecision.canPersistSensitiveMetadata) {
            blockers += BitcoinWalletSyncBlocker.SecureMetadataPersistenceUnavailable
            blockers += BitcoinWalletSyncBlocker.ObservationPersistenceUnavailable
            blockers += BitcoinWalletSyncBlocker.AddressIndexPersistenceUnavailable
            warnings += BitcoinWalletSyncWarning.NoSecureMetadataPersisted
            warnings += BitcoinWalletSyncWarning.MetadataRequiresEncryptedVault
        }
        if (!request.connectionPolicy.productionSyncAllowed || !request.connectionPolicy.productionNetworkingAllowed) {
            blockers += BitcoinWalletSyncBlocker.SyncDisabled
            warnings += BitcoinWalletSyncWarning.NoNetworkAttempted
        }
        if (!request.secureStorageCapability.status.availableForSecretMaterial ||
            !request.secureStorageCapability.canReadSecrets ||
            !request.secureStorageCapability.canStoreSecrets
        ) {
            blockers += BitcoinWalletSyncBlocker.SecureStorageUnavailable
        }

        val endpointValidationErrors = request.backendValidation?.errors.orEmpty()
        if (endpointValidationErrors.isNotEmpty()) {
            blockers += BitcoinWalletSyncBlocker.EndpointInvalid
        }
        if (BackendProfileValidationError.CredentialMaterialRejected in endpointValidationErrors) {
            blockers += BitcoinWalletSyncBlocker.CredentialMaterialRejected
            blockers += BitcoinWalletSyncBlocker.CredentialsUnavailable
            warnings += BitcoinWalletSyncWarning.CredentialsRequireSecureStorage
        }

        val profile = request.backendProfile
        if (profile == null) {
            blockers += BitcoinWalletSyncBlocker.BackendNotConfigured
        } else {
            warnings += warningsForProfile(profile)
            if (!request.networkPolicy.allows(profile.network) || profile.network.allowsMainnetOperations) {
                blockers += BitcoinWalletSyncBlocker.MainnetDisabled
            }
            if (!profile.endpoint.isConfigured) {
                blockers += BitcoinWalletSyncBlocker.BackendNotConfigured
                blockers += BitcoinWalletSyncBlocker.EndpointInvalid
            }
            if (profile.credentialReference != null ||
                profile.credentialPolicy == BackendCredentialPolicy.RequiresSecureStorageBeforeUse ||
                profile.credentialPolicy == BackendCredentialPolicy.CredentialReferenceOnly
            ) {
                warnings += BitcoinWalletSyncWarning.CredentialsRequireSecureStorage
                if (!request.secureStorageCapability.canReadSecrets) {
                    blockers += BitcoinWalletSyncBlocker.CredentialsUnavailable
                }
            }
        }

        val wallet = request.wallet
        if (wallet == null) {
            blockers += BitcoinWalletSyncBlocker.WalletContextMissing
            blockers += BitcoinWalletSyncBlocker.NoOperationalWallet
        } else {
            if (!wallet.isOperational) {
                blockers += BitcoinWalletSyncBlocker.NoOperationalWallet
            }
            if (!ReceiveAddressPolicy.isDevelopmentReceiveNetworkAllowed(wallet.network)) {
                blockers += BitcoinWalletSyncBlocker.MainnetDisabled
            }
        }

        val candidate = request.candidate
        if (candidate == null) {
            blockers += BitcoinWalletSyncBlocker.AddressCandidateMissing
        } else if (!ReceiveAddressPolicy.isDevelopmentReceiveNetworkAllowed(candidate.network)) {
            blockers += BitcoinWalletSyncBlocker.MainnetDisabled
        }

        val receiveDecision = if (wallet != null && candidate != null) {
            ReceiveAddressPolicy.evaluate(
                ReceiveAddressPolicyRequest(
                    action = ReceiveAddressPolicyAction.ReshowReservedOrDisplayedAddress,
                    wallet = wallet,
                    candidate = candidate,
                ),
            ).also { decision ->
                if (decision.blockingIssues.isNotEmpty()) {
                    blockers += BitcoinWalletSyncBlocker.ReceiveAddressPolicyBlocked
                }
                if (decision.warnings.any {
                        it == ReceiveAddressPolicyWarning.NostrIdentityLinkageWarningRequired ||
                            it == ReceiveAddressPolicyWarning.ImportedKeyBackupWarningRequired
                    }
                ) {
                    warnings += BitcoinWalletSyncWarning.BackendCanLinkWalletQueries
                }
            }
        } else {
            null
        }

        val status = statusFor(blockers)
        return BitcoinWalletSyncPreflight(
            status = status,
            requestedNetwork = request.requestedNetwork,
            backendProfileId = profile?.id,
            walletProfileId = wallet?.profileId,
            warnings = warnings,
            blockers = blockers,
            capabilities = boundaryCapabilities(),
            endpointValidationErrors = endpointValidationErrors,
            receiveAddressDecision = receiveDecision,
        )
    }

    fun statusFor(blockers: Set<BitcoinWalletSyncBlocker>): BitcoinWalletSyncStatus =
        when {
            BitcoinWalletSyncBlocker.MainnetDisabled in blockers -> BitcoinWalletSyncStatus.MainnetRejected
            BitcoinWalletSyncBlocker.EndpointInvalid in blockers -> BitcoinWalletSyncStatus.EndpointInvalid
            BitcoinWalletSyncBlocker.BackendNotConfigured in blockers -> BitcoinWalletSyncStatus.NotConfigured
            BitcoinWalletSyncBlocker.CredentialsUnavailable in blockers -> BitcoinWalletSyncStatus.CredentialsUnavailable
            BitcoinWalletSyncBlocker.NoOperationalWallet in blockers -> BitcoinWalletSyncStatus.NoOperationalWallet
            else -> BitcoinWalletSyncStatus.ProductionSyncDisabled
        }

    fun errorFor(status: BitcoinWalletSyncStatus): BitcoinWalletSyncError =
        when (status) {
            BitcoinWalletSyncStatus.NotConfigured -> BitcoinWalletSyncError.BackendNotConfigured
            BitcoinWalletSyncStatus.EndpointInvalid -> BitcoinWalletSyncError.EndpointInvalid
            BitcoinWalletSyncStatus.MainnetRejected -> BitcoinWalletSyncError.MainnetDisabled
            BitcoinWalletSyncStatus.NoOperationalWallet -> BitcoinWalletSyncError.NoOperationalWallet
            BitcoinWalletSyncStatus.CredentialsUnavailable -> BitcoinWalletSyncError.CredentialsUnavailable
            BitcoinWalletSyncStatus.ProductionSyncDisabled -> BitcoinWalletSyncError.SyncDisabled
        }

    fun boundaryCapabilities(): Set<BitcoinWalletSyncCapability> =
        setOf(
            BitcoinWalletSyncCapability.PreflightPolicyEvaluation,
            BitcoinWalletSyncCapability.EndpointPolicyEvaluation,
            BitcoinWalletSyncCapability.BackendAdapterBoundary,
            BitcoinWalletSyncCapability.BackendObservationSummaryTarget,
            BitcoinWalletSyncCapability.ReceiveAddressPolicyBoundary,
            BitcoinWalletSyncCapability.CredentialReferenceMetadataOnly,
            BitcoinWalletSyncCapability.EncryptedVaultReadinessBoundary,
            BitcoinWalletSyncCapability.SecureMetadataPersistenceBoundary,
            BitcoinWalletSyncCapability.FutureProductionSync,
            BitcoinWalletSyncCapability.FutureObservationPersistence,
            BitcoinWalletSyncCapability.FutureAddressIndexPersistence,
            BitcoinWalletSyncCapability.NoProductionNetworking,
            BitcoinWalletSyncCapability.NoSigning,
            BitcoinWalletSyncCapability.NoBroadcasting,
            BitcoinWalletSyncCapability.NoMainnet,
        )

    fun warningsForProfile(profile: BitcoinBackendProfile): Set<BitcoinWalletSyncWarning> =
        buildSet {
            when (profile.trustModel) {
                BitcoinBackendTrustModel.UserOwnedNode -> add(BitcoinWalletSyncWarning.UserOwnedNodePreferred)
                BitcoinBackendTrustModel.TrustedThirdParty,
                BitcoinBackendTrustModel.Unknown,
                -> add(BitcoinWalletSyncWarning.BackendCanLinkWalletQueries)
                BitcoinBackendTrustModel.PublicBackend -> {
                    add(BitcoinWalletSyncWarning.PublicBackendPrivacyLeak)
                    add(BitcoinWalletSyncWarning.BackendCanLinkWalletQueries)
                }
            }
            if (BitcoinBackendAdapterPolicy.observationTrustFor(profile) == BackendObservationTrust.UserSelectedOnionBackend) {
                add(BitcoinWalletSyncWarning.OnionTorLabelPreserved)
                add(BitcoinWalletSyncWarning.TorTransportNotImplemented)
            }
        }
}

class DisabledBitcoinWalletSyncService(
    override val id: BitcoinWalletSyncServiceId = BitcoinWalletSyncServiceId("disabled-bitcoin-wallet-sync-service"),
    private val backendAdapter: BitcoinBackendAdapter = DisabledProductionBitcoinBackendAdapter(),
) : BitcoinWalletSyncService {
    override fun preflight(request: BitcoinWalletSyncRequest): BitcoinWalletSyncPreflight =
        BitcoinWalletSyncPolicy.preflight(request)

    override fun sync(request: BitcoinWalletSyncRequest): BitcoinWalletSyncResult {
        val preflight = preflight(request)
        val backendResult = if (
            request.backendProfile != null &&
            BitcoinWalletSyncBlocker.EndpointInvalid !in preflight.blockers
        ) {
            backendAdapter.observe(
                BitcoinBackendObservationRequest(
                    profile = request.backendProfile,
                    wallet = request.wallet,
                    candidate = request.candidate,
                    networkPolicy = request.networkPolicy,
                    connectionPolicy = request.connectionPolicy,
                    secureStorageCapability = request.secureStorageCapability,
                ),
            )
        } else {
            null
        }

        val blockers = preflight.blockers + backendResult.toSyncBlockers()
        val warnings = preflight.warnings + backendResult.toSyncWarnings()
        val status = BitcoinWalletSyncPolicy.statusFor(blockers)
        return BitcoinWalletSyncResult(
            serviceId = id,
            status = status,
            preflight = preflight,
            backendObservationResult = backendResult,
            observationSummary = backendResult?.observationSummary,
            warnings = warnings,
            blockers = blockers,
            capabilities = BitcoinWalletSyncPolicy.boundaryCapabilities(),
            error = BitcoinWalletSyncPolicy.errorFor(status),
            diagnostic = "Production wallet sync facade is disabled. No backend client, BDK sync, observation persistence, signing, broadcast, or mainnet path was attempted.",
        )
    }

    private fun BitcoinBackendObservationResult?.toSyncBlockers(): Set<BitcoinWalletSyncBlocker> =
        this?.blockingIssues.orEmpty().map { issue ->
            when (issue) {
                BitcoinBackendAdapterBlockingIssue.BackendNotConfigured -> BitcoinWalletSyncBlocker.BackendNotConfigured
                BitcoinBackendAdapterBlockingIssue.ProductionSyncDisabled -> BitcoinWalletSyncBlocker.ProductionBackendDisabled
                BitcoinBackendAdapterBlockingIssue.MainnetDisabled -> BitcoinWalletSyncBlocker.MainnetDisabled
                BitcoinBackendAdapterBlockingIssue.CredentialsUnavailable -> BitcoinWalletSyncBlocker.CredentialsUnavailable
                BitcoinBackendAdapterBlockingIssue.InvalidEndpoint -> BitcoinWalletSyncBlocker.EndpointInvalid
                BitcoinBackendAdapterBlockingIssue.WalletContextMissing -> BitcoinWalletSyncBlocker.WalletContextMissing
                BitcoinBackendAdapterBlockingIssue.AddressCandidateMissing -> BitcoinWalletSyncBlocker.AddressCandidateMissing
            }
        }.toSet()

    private fun BitcoinBackendObservationResult?.toSyncWarnings(): Set<BitcoinWalletSyncWarning> =
        this?.warnings.orEmpty().map { warning ->
            when (warning) {
                BitcoinBackendAdapterWarning.ProductionSyncDisabled -> BitcoinWalletSyncWarning.ProductionSyncDisabled
                BitcoinBackendAdapterWarning.NoNetworkAttempted -> BitcoinWalletSyncWarning.NoNetworkAttempted
                BitcoinBackendAdapterWarning.PublicBackendPrivacyLeak -> BitcoinWalletSyncWarning.PublicBackendPrivacyLeak
                BitcoinBackendAdapterWarning.BackendCanLinkWalletQueries -> BitcoinWalletSyncWarning.BackendCanLinkWalletQueries
                BitcoinBackendAdapterWarning.OnionTorLabelPreserved -> BitcoinWalletSyncWarning.OnionTorLabelPreserved
                BitcoinBackendAdapterWarning.UserOwnedNodePreferred -> BitcoinWalletSyncWarning.UserOwnedNodePreferred
                BitcoinBackendAdapterWarning.CredentialsRequireSecureStorage -> BitcoinWalletSyncWarning.CredentialsRequireSecureStorage
                BitcoinBackendAdapterWarning.EndpointPolicyOnly -> BitcoinWalletSyncWarning.EndpointPolicyOnly
                BitcoinBackendAdapterWarning.NoSkaldManagedInfrastructure -> BitcoinWalletSyncWarning.NoSkaldManagedInfrastructure
            }
        }.toSet()
}
