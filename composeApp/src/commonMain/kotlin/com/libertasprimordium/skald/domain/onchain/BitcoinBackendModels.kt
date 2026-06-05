package com.libertasprimordium.skald.domain.onchain

import com.libertasprimordium.skald.domain.core.CredentialReference

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
}

data class BitcoinBackendProfile(
    val id: String,
    val type: BitcoinBackendType,
    val status: BitcoinBackendStatus,
    val trustModel: BitcoinBackendTrustModel,
    val privacyLevel: BitcoinBackendPrivacyLevel,
    val credentialPolicy: BackendCredentialPolicy,
    val endpointValidationState: BackendEndpointValidationState,
    val credentialReference: CredentialReference?,
    val endpointDisplay: String,
    val capabilities: List<BitcoinBackendCapability>,
    val noDefaultEndpoint: Boolean,
    val warnings: List<String>,
)
