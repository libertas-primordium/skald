package com.libertasprimordium.skald.domain.onchain

import com.libertasprimordium.skald.domain.core.NetworkEnvironment
import com.libertasprimordium.skald.security.SecureStorageCapability

@JvmInline
value class BitcoinBackendAdapterId(val value: String) {
    init {
        require(value.isNotBlank()) { "Bitcoin backend adapter id must not be blank." }
    }
}

enum class BitcoinBackendAdapterKind(val label: String) {
    BitcoinCoreRpc("Bitcoin Core RPC adapter"),
    Electrum("Electrum adapter"),
    Esplora("Esplora adapter"),
    DisabledBoundary("disabled production boundary"),
}

enum class BitcoinBackendAdapterCapability(val label: String, val enabledInProduction: Boolean) {
    EndpointNormalization("endpoint normalization", enabledInProduction = true),
    BackendTrustClassification("backend trust classification", enabledInProduction = true),
    ObservationResultTranslation("observation result translation", enabledInProduction = true),
    FutureWalletSync("future wallet sync", enabledInProduction = false),
    FutureUtxoScan("future UTXO scan", enabledInProduction = false),
    FutureFeeEstimates("future fee estimates", enabledInProduction = false),
    FutureBroadcast("future broadcast", enabledInProduction = false),
    CredentialReferenceOnly("credential reference only", enabledInProduction = true),
    NoProductionNetworking("no production networking", enabledInProduction = true),
    NoSigning("no signing", enabledInProduction = true),
    NoBroadcasting("no broadcasting", enabledInProduction = true),
    NoMainnet("no mainnet", enabledInProduction = true),
}

enum class BitcoinBackendAdapterStatus(val label: String) {
    NotConfigured("not configured"),
    Blocked("blocked"),
    ProductionSyncDisabled("production sync disabled"),
    MainnetRejected("mainnet rejected"),
    CredentialsUnavailable("credentials unavailable"),
    PlaceholderObservation("placeholder observation"),
}

enum class BitcoinBackendAdapterWarning(val label: String) {
    ProductionSyncDisabled("production sync disabled"),
    NoNetworkAttempted("no network attempted"),
    PublicBackendPrivacyLeak("public backend can observe wallet queries"),
    BackendCanLinkWalletQueries("backend can link wallet queries"),
    OnionTorLabelPreserved("onion/Tor labeling preserved"),
    UserOwnedNodePreferred("user-owned node preferred"),
    CredentialsRequireSecureStorage("credentials require secure storage"),
    EndpointPolicyOnly("endpoint parsing is policy-only"),
    NoSkaldManagedInfrastructure("no Skald-managed infrastructure"),
}

enum class BitcoinBackendAdapterBlockingIssue(val label: String) {
    BackendNotConfigured("backend not configured"),
    ProductionSyncDisabled("production sync disabled"),
    MainnetDisabled("mainnet disabled"),
    CredentialsUnavailable("credentials unavailable"),
    InvalidEndpoint("invalid endpoint"),
    WalletContextMissing("wallet context missing"),
    AddressCandidateMissing("address candidate missing"),
}

sealed interface BitcoinBackendAdapterError {
    val code: String
    val safeDetail: String

    data class ProductionSyncDisabled(
        override val safeDetail: String = "Production backend sync is disabled. No backend client was created.",
    ) : BitcoinBackendAdapterError {
        override val code: String = "PRODUCTION_BACKEND_SYNC_DISABLED"
    }

    data class MainnetDisabled(
        override val safeDetail: String = "Mainnet backend observation is disabled by Skald policy.",
    ) : BitcoinBackendAdapterError {
        override val code: String = "MAINNET_DISABLED"
    }

    data class CredentialsUnavailable(
        override val safeDetail: String = "Credential references cannot be used until secure storage is available.",
    ) : BitcoinBackendAdapterError {
        override val code: String = "BACKEND_CREDENTIALS_UNAVAILABLE"
    }

    data class BackendNotConfigured(
        override val safeDetail: String = "No backend profile is configured for production observation.",
    ) : BitcoinBackendAdapterError {
        override val code: String = "BACKEND_NOT_CONFIGURED"
    }
}

data class BitcoinBackendNetworkPolicy(
    val allowedNetworks: Set<NetworkEnvironment>,
    val mainnetAllowed: Boolean,
) {
    fun allows(network: NetworkEnvironment): Boolean =
        network in allowedNetworks && !network.allowsMainnetOperations && network.isDevelopmentSelectable

    companion object {
        val DevelopmentOnly: BitcoinBackendNetworkPolicy = BitcoinBackendNetworkPolicy(
            allowedNetworks = setOf(
                NetworkEnvironment.Regtest,
                NetworkEnvironment.Signet,
                NetworkEnvironment.Testnet,
                NetworkEnvironment.Testnet4,
            ),
            mainnetAllowed = false,
        )
    }
}

data class BitcoinBackendConnectionPolicy(
    val productionNetworkingAllowed: Boolean,
    val productionSyncAllowed: Boolean,
    val signingAllowed: Boolean,
    val broadcastingAllowed: Boolean,
) {
    companion object {
        val Disabled: BitcoinBackendConnectionPolicy = BitcoinBackendConnectionPolicy(
            productionNetworkingAllowed = false,
            productionSyncAllowed = false,
            signingAllowed = false,
            broadcastingAllowed = false,
        )
    }
}

data class BitcoinBackendObservationRequest(
    val profile: BitcoinBackendProfile?,
    val wallet: ReceiveAddressWalletContext?,
    val candidate: ReceiveAddressState?,
    val networkPolicy: BitcoinBackendNetworkPolicy = BitcoinBackendNetworkPolicy.DevelopmentOnly,
    val connectionPolicy: BitcoinBackendConnectionPolicy = BitcoinBackendConnectionPolicy.Disabled,
    val secureStorageCapability: SecureStorageCapability,
) {
    val requestedNetwork: NetworkEnvironment?
        get() = profile?.network ?: wallet?.network ?: candidate?.network
}

data class BitcoinBackendObservationResult(
    val adapterId: BitcoinBackendAdapterId,
    val adapterKind: BitcoinBackendAdapterKind,
    val status: BitcoinBackendAdapterStatus,
    val observationSummary: BackendObservationSummary?,
    val warnings: Set<BitcoinBackendAdapterWarning>,
    val blockingIssues: Set<BitcoinBackendAdapterBlockingIssue>,
    val capabilities: Set<BitcoinBackendAdapterCapability>,
    val error: BitcoinBackendAdapterError?,
    val diagnostic: String,
) {
    val productionSyncEnabled: Boolean
        get() = false

    val productionNetworkingEnabled: Boolean
        get() = false

    val signingOrBroadcastEnabled: Boolean
        get() = false

    val mainnetEnabled: Boolean
        get() = false

    val usesSkaldManagedInfrastructure: Boolean
        get() = false
}

interface BitcoinBackendAdapter {
    val id: BitcoinBackendAdapterId
    val kind: BitcoinBackendAdapterKind

    fun observe(request: BitcoinBackendObservationRequest): BitcoinBackendObservationResult
}

object BitcoinBackendAdapterPolicy {
    fun adapterKindFor(type: BitcoinBackendType): BitcoinBackendAdapterKind =
        when (type) {
            BitcoinBackendType.BitcoinCoreRpc -> BitcoinBackendAdapterKind.BitcoinCoreRpc
            BitcoinBackendType.Electrum -> BitcoinBackendAdapterKind.Electrum
            BitcoinBackendType.Esplora -> BitcoinBackendAdapterKind.Esplora
        }

    fun observationSessionFor(profile: BitcoinBackendProfile): BackendObservationSession =
        BackendObservationSession(
            id = BackendObservationSessionId("production-disabled-${profile.id.value}"),
            network = profile.network,
            source = when (profile.type) {
                BitcoinBackendType.BitcoinCoreRpc -> BackendObservationSource.UserSelectedBitcoinCoreRpc
                BitcoinBackendType.Electrum -> BackendObservationSource.UserSelectedElectrum
                BitcoinBackendType.Esplora -> BackendObservationSource.UserSelectedEsplora
            },
            trust = observationTrustFor(profile),
            status = BackendObservationStatus.Blocked,
            capabilities = setOf(
                BackendObservationCapability.BackendTrustMetadata,
                BackendObservationCapability.NoProductionSync,
                BackendObservationCapability.NoSigning,
                BackendObservationCapability.NoBroadcasting,
                BackendObservationCapability.NoMainnet,
            ),
        )

    fun observationTrustFor(profile: BitcoinBackendProfile): BackendObservationTrust {
        val endpointHostKind = when (val endpoint = profile.endpoint) {
            BackendNotConfigured -> BitcoinBackendEndpointHostKind.Unknown
            is HttpEndpoint -> BitcoinBackendEndpointParser.parse(
                BitcoinBackendEndpointParseInput(
                    backendType = profile.type,
                    address = endpoint.host,
                    explicitPort = endpoint.port?.toString().orEmpty(),
                    useTls = endpoint.useTls,
                    path = endpoint.path.orEmpty(),
                ),
            ).normalizedEndpoint?.hostKind ?: BitcoinBackendEndpointHostKind.Unknown
            is TcpEndpoint -> BitcoinBackendEndpointParser.parse(
                BitcoinBackendEndpointParseInput(
                    backendType = profile.type,
                    address = endpoint.host,
                    explicitPort = endpoint.port.toString(),
                    useTls = endpoint.useTls,
                    path = "",
                ),
            ).normalizedEndpoint?.hostKind ?: BitcoinBackendEndpointHostKind.Unknown
        }
        if (endpointHostKind == BitcoinBackendEndpointHostKind.Onion) {
            return BackendObservationTrust.UserSelectedOnionBackend
        }
        return when (profile.trustModel) {
            BitcoinBackendTrustModel.UserOwnedNode -> BackendObservationTrust.UserOwnedNode
            BitcoinBackendTrustModel.TrustedThirdParty -> BackendObservationTrust.UserSelectedPrivateBackend
            BitcoinBackendTrustModel.PublicBackend -> BackendObservationTrust.UserSelectedPublicBackend
            BitcoinBackendTrustModel.Unknown -> BackendObservationTrust.Unknown
        }
    }

    fun warningsFor(profile: BitcoinBackendProfile?): Set<BitcoinBackendAdapterWarning> =
        buildSet {
            add(BitcoinBackendAdapterWarning.ProductionSyncDisabled)
            add(BitcoinBackendAdapterWarning.NoNetworkAttempted)
            add(BitcoinBackendAdapterWarning.EndpointPolicyOnly)
            add(BitcoinBackendAdapterWarning.NoSkaldManagedInfrastructure)
            if (profile == null) return@buildSet
            when (profile.trustModel) {
                BitcoinBackendTrustModel.UserOwnedNode -> add(BitcoinBackendAdapterWarning.UserOwnedNodePreferred)
                BitcoinBackendTrustModel.TrustedThirdParty,
                BitcoinBackendTrustModel.Unknown,
                -> add(BitcoinBackendAdapterWarning.BackendCanLinkWalletQueries)
                BitcoinBackendTrustModel.PublicBackend -> {
                    add(BitcoinBackendAdapterWarning.PublicBackendPrivacyLeak)
                    add(BitcoinBackendAdapterWarning.BackendCanLinkWalletQueries)
                }
            }
            if (observationTrustFor(profile) == BackendObservationTrust.UserSelectedOnionBackend) {
                add(BitcoinBackendAdapterWarning.OnionTorLabelPreserved)
            }
            if (profile.credentialReference != null ||
                profile.credentialPolicy == BackendCredentialPolicy.RequiresSecureStorageBeforeUse ||
                profile.credentialPolicy == BackendCredentialPolicy.CredentialReferenceOnly
            ) {
                add(BitcoinBackendAdapterWarning.CredentialsRequireSecureStorage)
            }
        }

    fun disabledObservationSummary(
        request: BitcoinBackendObservationRequest,
        profile: BitcoinBackendProfile,
    ): BackendObservationSummary =
        BackendObservationPolicy.evaluate(
            BackendObservationPolicyRequest(
                session = observationSessionFor(profile),
                wallet = request.wallet,
                candidate = request.candidate,
                observedUtxos = emptyList(),
            ),
        )

    fun sanitizedPlaceholderObservationResult(
        profile: BitcoinBackendProfile,
        summary: BackendObservationSummary,
    ): BitcoinBackendObservationResult =
        BitcoinBackendObservationResult(
            adapterId = BitcoinBackendAdapterId("placeholder-${profile.id.value}"),
            adapterKind = adapterKindFor(profile.type),
            status = BitcoinBackendAdapterStatus.PlaceholderObservation,
            observationSummary = summary,
            warnings = warningsFor(profile),
            blockingIssues = emptySet(),
            capabilities = boundaryCapabilities(),
            error = null,
            diagnostic = "Sanitized placeholder observation result; no backend client, sync, signing, broadcast, or persistence exists.",
        )

    fun boundaryCapabilities(): Set<BitcoinBackendAdapterCapability> =
        setOf(
            BitcoinBackendAdapterCapability.EndpointNormalization,
            BitcoinBackendAdapterCapability.BackendTrustClassification,
            BitcoinBackendAdapterCapability.ObservationResultTranslation,
            BitcoinBackendAdapterCapability.CredentialReferenceOnly,
            BitcoinBackendAdapterCapability.NoProductionNetworking,
            BitcoinBackendAdapterCapability.NoSigning,
            BitcoinBackendAdapterCapability.NoBroadcasting,
            BitcoinBackendAdapterCapability.NoMainnet,
            BitcoinBackendAdapterCapability.FutureWalletSync,
            BitcoinBackendAdapterCapability.FutureUtxoScan,
            BitcoinBackendAdapterCapability.FutureFeeEstimates,
            BitcoinBackendAdapterCapability.FutureBroadcast,
        )
}

class DisabledProductionBitcoinBackendAdapter(
    override val id: BitcoinBackendAdapterId = BitcoinBackendAdapterId("disabled-production-backend-adapter"),
    override val kind: BitcoinBackendAdapterKind = BitcoinBackendAdapterKind.DisabledBoundary,
) : BitcoinBackendAdapter {
    override fun observe(request: BitcoinBackendObservationRequest): BitcoinBackendObservationResult {
        val profile = request.profile
            ?: return BitcoinBackendObservationResult(
                adapterId = id,
                adapterKind = kind,
                status = BitcoinBackendAdapterStatus.NotConfigured,
                observationSummary = null,
                warnings = BitcoinBackendAdapterPolicy.warningsFor(null),
                blockingIssues = setOf(BitcoinBackendAdapterBlockingIssue.BackendNotConfigured),
                capabilities = BitcoinBackendAdapterPolicy.boundaryCapabilities(),
                error = BitcoinBackendAdapterError.BackendNotConfigured(),
                diagnostic = "No backend profile is configured. No production backend client was created.",
            )

        val issues = mutableSetOf(BitcoinBackendAdapterBlockingIssue.ProductionSyncDisabled)
        var status = BitcoinBackendAdapterStatus.ProductionSyncDisabled
        var error: BitcoinBackendAdapterError = BitcoinBackendAdapterError.ProductionSyncDisabled()
        if (!request.networkPolicy.allows(profile.network) || profile.network.allowsMainnetOperations) {
            issues += BitcoinBackendAdapterBlockingIssue.MainnetDisabled
            status = BitcoinBackendAdapterStatus.MainnetRejected
            error = BitcoinBackendAdapterError.MainnetDisabled()
        }
        if (profile.credentialReference != null && !request.secureStorageCapability.canReadSecrets) {
            issues += BitcoinBackendAdapterBlockingIssue.CredentialsUnavailable
            status = BitcoinBackendAdapterStatus.CredentialsUnavailable
            error = BitcoinBackendAdapterError.CredentialsUnavailable()
        }
        if (!profile.endpoint.isConfigured) {
            issues += BitcoinBackendAdapterBlockingIssue.BackendNotConfigured
        }
        if (request.wallet == null) {
            issues += BitcoinBackendAdapterBlockingIssue.WalletContextMissing
        }
        if (request.candidate == null) {
            issues += BitcoinBackendAdapterBlockingIssue.AddressCandidateMissing
        }

        return BitcoinBackendObservationResult(
            adapterId = id,
            adapterKind = BitcoinBackendAdapterPolicy.adapterKindFor(profile.type),
            status = status,
            observationSummary = BitcoinBackendAdapterPolicy.disabledObservationSummary(request, profile),
            warnings = BitcoinBackendAdapterPolicy.warningsFor(profile),
            blockingIssues = issues,
            capabilities = BitcoinBackendAdapterPolicy.boundaryCapabilities(),
            error = error,
            diagnostic = "Production backend adapter boundary is disabled. No socket, HTTP, RPC, Electrum, Esplora, BDK sync, signing, broadcast, or persistence was attempted.",
        )
    }
}
