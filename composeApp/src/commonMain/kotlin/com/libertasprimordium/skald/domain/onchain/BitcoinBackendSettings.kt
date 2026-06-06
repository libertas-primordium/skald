package com.libertasprimordium.skald.domain.onchain

import com.libertasprimordium.skald.domain.core.NetworkEnvironment

data class BitcoinBackendSettingsState(
    val profiles: List<BitcoinBackendProfile>,
    val selectedProfileId: BitcoinBackendProfileId?,
) {
    val selectedProfile: BitcoinBackendProfile?
        get() = profiles.firstOrNull { it.id == selectedProfileId }

    companion object {
        val Empty: BitcoinBackendSettingsState = BitcoinBackendSettingsState(
            profiles = emptyList(),
            selectedProfileId = null,
        )
    }
}

data class EditableBitcoinBackendProfileInput(
    val id: BitcoinBackendProfileId? = null,
    val label: String,
    val type: BitcoinBackendType,
    val network: NetworkEnvironment,
    val host: String,
    val portText: String,
    val useTls: Boolean,
    val path: String,
    val trustModel: BitcoinBackendTrustModel,
)

data class BackendProfileValidationResult(
    val normalizedProfile: BitcoinBackendProfile?,
    val errors: List<BackendProfileValidationError>,
    val warnings: List<String>,
) {
    val isValid: Boolean
        get() = errors.isEmpty() && normalizedProfile != null
}

enum class BackendProfileValidationError(val message: String) {
    BlankLabel("Label must not be blank."),
    MainnetDisabled("Mainnet is disabled during development."),
    MissingHost("Host must not be blank when an endpoint is configured."),
    MissingPort("Port is required for this backend type."),
    InvalidPort("Port must be a number from 1 to 65535."),
    CredentialMaterialRejected("Endpoint must not include userinfo, passwords, tokens, cookies, or credential-like material."),
    UnsupportedScheme("Endpoint scheme is not supported for this backend type."),
    UnsupportedPath("Endpoint path is not supported for this backend type."),
    MalformedEndpoint("Endpoint host/address is malformed."),
    AmbiguousEndpointPort("Endpoint address and port fields are ambiguous."),
    MainnetDefaultEndpointRejected("Mainnet-default endpoint metadata is rejected during development."),
}
