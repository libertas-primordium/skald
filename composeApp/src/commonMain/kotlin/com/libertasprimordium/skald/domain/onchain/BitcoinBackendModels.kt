package com.libertasprimordium.skald.domain.onchain

import com.libertasprimordium.skald.domain.core.CredentialReference
import com.libertasprimordium.skald.domain.core.NetworkEnvironment

@JvmInline
value class BitcoinBackendProfileId(val value: String)

enum class BitcoinBackendType(val label: String) {
    BitcoinCoreRpc("Bitcoin Core RPC"),
    Electrum("Electrum"),
    Esplora("Esplora"),
}

enum class BitcoinBackendStatus(val label: String) {
    NotConfigured("not configured"),
    Planned("planned"),
    DisabledPlaceholder("disabled placeholder"),
    ConnectionTestingNotImplemented("connection testing not implemented"),
    UserConfiguredTestnet("user-configured testnet"),
}

enum class BitcoinBackendTrustModel(val label: String) {
    UserOwnedNode("user-owned node"),
    TrustedThirdParty("trusted third party"),
    PublicBackend("public backend"),
    Unknown("unknown"),
}

enum class BitcoinBackendPrivacyLevel(val label: String) {
    PublicBackendLeaksWalletQueries("public backend leaks wallet queries"),
    TrustedNodeStillSeesQueries("trusted node still sees queries"),
    UserOwnedNodePreferred("user-owned node preferred"),
    TorProxyPlanned("Tor/proxy planned"),
    UnknownRisk("unknown risk"),
}

enum class BitcoinBackendCapability(val label: String) {
    SyncWallet("wallet sync"),
    BroadcastTransaction("transaction broadcast"),
    FeeEstimates("fee estimates"),
    MempoolView("mempool view"),
    DescriptorWalletRpc("descriptor wallet RPC"),
    BlockHeaderVerification("block header verification"),
}

enum class BackendCredentialPolicy(val label: String) {
    NoCredentialStored("no credential stored"),
    CredentialReferenceOnly("credential reference only"),
    RequiresSecureStorageBeforeUse("requires secure storage before use"),
    PublicEndpointNoCredentialStillLeaks("public endpoint without credential still leaks queries"),
    DisabledPlaceholder("disabled placeholder"),
}

enum class BackendEndpointValidationState(val label: String) {
    Empty("empty"),
    PlaceholderOnly("placeholder only"),
    NotValidated("not validated"),
    ValidatedTestnetPlaceholder("validated testnet placeholder"),
    Invalid("invalid"),
}

sealed interface BackendEndpoint {
    val displayText: String
    val isConfigured: Boolean
}

data object BackendNotConfigured : BackendEndpoint {
    override val displayText: String = "BACKEND_NOT_CONFIGURED"
    override val isConfigured: Boolean = false
}

data class HttpEndpoint(
    val host: String,
    val port: Int?,
    val useTls: Boolean,
    val path: String?,
) : BackendEndpoint {
    override val isConfigured: Boolean
        get() = host.isNotBlank()

    override val displayText: String
        get() {
            if (!isConfigured) return BackendNotConfigured.displayText
            val scheme = if (useTls) "https" else "http"
            val portText = port?.let { ":$it" } ?: ""
            val pathText = path
                ?.takeIf { it.isNotBlank() }
                ?.let { if (it.startsWith("/")) it else "/$it" }
                ?: ""
            return "$scheme://${host.trim().toDisplayHost()}$portText$pathText"
        }
}

data class TcpEndpoint(
    val host: String,
    val port: Int,
    val useTls: Boolean,
) : BackendEndpoint {
    override val isConfigured: Boolean
        get() = host.isNotBlank()

    override val displayText: String
        get() {
            if (!isConfigured) return BackendNotConfigured.displayText
            val scheme = if (useTls) "tls" else "tcp"
            return "$scheme://${host.trim().toDisplayHost()}:$port"
        }
}

data class BitcoinBackendProfile(
    val id: BitcoinBackendProfileId,
    val label: String,
    val type: BitcoinBackendType,
    val network: NetworkEnvironment,
    val endpoint: BackendEndpoint,
    val status: BitcoinBackendStatus,
    val trustModel: BitcoinBackendTrustModel,
    val privacyLevel: BitcoinBackendPrivacyLevel,
    val credentialPolicy: BackendCredentialPolicy,
    val endpointValidationState: BackendEndpointValidationState,
    val credentialReference: CredentialReference?,
    val capabilities: List<BitcoinBackendCapability>,
    val warnings: List<String>,
    val isSelected: Boolean,
    val isUserEditable: Boolean,
) {
    val endpointDisplay: String
        get() = endpoint.displayText

    val noDefaultEndpoint: Boolean
        get() = !endpoint.isConfigured
}
