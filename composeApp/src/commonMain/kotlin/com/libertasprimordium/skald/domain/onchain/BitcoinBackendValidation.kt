package com.libertasprimordium.skald.domain.onchain

import com.libertasprimordium.skald.domain.core.NetworkEnvironment

object BitcoinBackendValidator {
    fun validate(input: EditableBitcoinBackendProfileInput): BackendProfileValidationResult {
        val errors = mutableListOf<BackendProfileValidationError>()
        val normalizedLabel = input.label.trim()
        val normalizedHostInput = input.host.trim()
        val normalizedPathInput = input.path.trim()

        if (normalizedLabel.isBlank()) {
            errors += BackendProfileValidationError.BlankLabel
        }
        if (!input.network.isDevelopmentSelectable || input.network.allowsMainnetOperations) {
            errors += BackendProfileValidationError.MainnetDisabled
        }
        if (containsCredentialMaterial(normalizedHostInput, normalizedPathInput)) {
            errors += BackendProfileValidationError.CredentialMaterialRejected
        }

        val endpointResult = endpointFor(input, normalizedHostInput, normalizedPathInput)
        errors += endpointResult.errors

        if (errors.isNotEmpty()) {
            return BackendProfileValidationResult(
                normalizedProfile = null,
                errors = errors.distinct(),
                warnings = warningsFor(input.trustModel),
            )
        }

        val endpoint = endpointResult.endpoint ?: BackendNotConfigured
        val profile = BitcoinBackendProfile(
            id = input.id ?: BitcoinBackendProfileId(generateProfileId(input.type, normalizedLabel, endpoint)),
            label = normalizedLabel,
            type = input.type,
            network = input.network,
            endpoint = endpoint,
            status = BitcoinBackendStatus.UserConfiguredTestnet,
            trustModel = input.trustModel,
            privacyLevel = privacyLevelFor(input.trustModel),
            credentialPolicy = credentialPolicyFor(input.type, input.trustModel),
            endpointValidationState = BackendEndpointValidationState.NotValidated,
            credentialReference = null,
            capabilities = capabilitiesFor(input.type),
            warnings = warningsFor(input.trustModel) + implementationWarningsFor(input.type),
            isSelected = false,
            isUserEditable = true,
        )

        return BackendProfileValidationResult(
            normalizedProfile = profile,
            errors = emptyList(),
            warnings = profile.warnings,
        )
    }

    fun isDevelopmentNetwork(network: NetworkEnvironment): Boolean =
        network.isDevelopmentSelectable && !network.allowsMainnetOperations

    private fun endpointFor(
        input: EditableBitcoinBackendProfileInput,
        normalizedHostInput: String,
        normalizedPathInput: String,
    ): EndpointValidation {
        if (normalizedHostInput.isBlank()) {
            return EndpointValidation(
                endpoint = null,
                errors = listOf(BackendProfileValidationError.MissingHost),
            )
        }

        return when (input.type) {
            BitcoinBackendType.BitcoinCoreRpc -> tcpEndpoint(input, normalizedHostInput, supportsUrlScheme = false)
            BitcoinBackendType.Electrum -> tcpEndpoint(input, normalizedHostInput, supportsUrlScheme = false)
            BitcoinBackendType.Esplora -> httpEndpoint(input, normalizedHostInput, normalizedPathInput)
        }
    }

    private fun tcpEndpoint(
        input: EditableBitcoinBackendProfileInput,
        normalizedHostInput: String,
        supportsUrlScheme: Boolean,
    ): EndpointValidation {
        if (!supportsUrlScheme && normalizedHostInput.contains("://")) {
            return EndpointValidation(
                endpoint = null,
                errors = listOf(BackendProfileValidationError.UnsupportedScheme),
            )
        }

        val port = parseRequiredPort(input.portText)
        if (port.error != null) {
            return EndpointValidation(endpoint = null, errors = listOf(port.error))
        }

        return EndpointValidation(
            endpoint = TcpEndpoint(
                host = normalizedHostInput,
                port = requireNotNull(port.value),
                useTls = input.useTls,
            ),
            errors = emptyList(),
        )
    }

    private fun httpEndpoint(
        input: EditableBitcoinBackendProfileInput,
        normalizedHostInput: String,
        normalizedPathInput: String,
    ): EndpointValidation {
        val parsed = parseHttpLikeInput(normalizedHostInput)
        if (parsed.unsupportedScheme) {
            return EndpointValidation(
                endpoint = null,
                errors = listOf(BackendProfileValidationError.UnsupportedScheme),
            )
        }
        if (parsed.userInfoPresent) {
            return EndpointValidation(
                endpoint = null,
                errors = listOf(BackendProfileValidationError.CredentialMaterialRejected),
            )
        }

        val port = parseOptionalPort(input.portText.ifBlank { parsed.portText.orEmpty() })
        if (port.error != null) {
            return EndpointValidation(endpoint = null, errors = listOf(port.error))
        }

        val host = parsed.host.ifBlank { normalizedHostInput }
        if (host.isBlank()) {
            return EndpointValidation(
                endpoint = null,
                errors = listOf(BackendProfileValidationError.MissingHost),
            )
        }

        val path = normalizedPathInput.ifBlank { parsed.path.orEmpty() }.ifBlank { null }
        return EndpointValidation(
            endpoint = HttpEndpoint(
                host = host,
                port = port.value,
                useTls = parsed.useTls ?: input.useTls,
                path = path,
            ),
            errors = emptyList(),
        )
    }

    private fun parseHttpLikeInput(input: String): ParsedHttpInput {
        val schemeMarker = input.indexOf("://")
        if (schemeMarker < 0) {
            return ParsedHttpInput(host = input)
        }

        val scheme = input.substring(0, schemeMarker).lowercase()
        if (scheme != "http" && scheme != "https") {
            return ParsedHttpInput(host = "", unsupportedScheme = true)
        }

        val remainder = input.substring(schemeMarker + 3)
        val pathStart = remainder.indexOf("/")
        val authority = if (pathStart >= 0) remainder.substring(0, pathStart) else remainder
        val path = if (pathStart >= 0) remainder.substring(pathStart) else null
        if (authority.contains("@")) {
            return ParsedHttpInput(host = "", userInfoPresent = true)
        }

        val portStart = authority.lastIndexOf(":").takeIf { it > 0 }
        val host = portStart?.let { authority.substring(0, it) } ?: authority
        val portText = portStart?.let { authority.substring(it + 1) }
        return ParsedHttpInput(
            host = host,
            portText = portText,
            path = path,
            useTls = scheme == "https",
        )
    }

    private fun parseRequiredPort(input: String): ParsedPort {
        if (input.isBlank()) return ParsedPort(error = BackendProfileValidationError.MissingPort)
        return parseOptionalPort(input).let { parsed ->
            if (parsed.value == null && parsed.error == null) {
                ParsedPort(error = BackendProfileValidationError.MissingPort)
            } else {
                parsed
            }
        }
    }

    private fun parseOptionalPort(input: String): ParsedPort {
        if (input.isBlank()) return ParsedPort(value = null)
        val value = input.trim().toIntOrNull()
            ?: return ParsedPort(error = BackendProfileValidationError.InvalidPort)
        if (value !in 1..65535) {
            return ParsedPort(error = BackendProfileValidationError.InvalidPort)
        }
        return ParsedPort(value = value)
    }

    private fun containsCredentialMaterial(host: String, path: String): Boolean {
        val combined = "$host $path".lowercase()
        return host.contains("@") ||
            listOf("password", "passwd", "token", "cookie", "macaroon", "rune", "nwc", "secret", "nsec")
                .any { combined.contains(it) }
    }

    private fun privacyLevelFor(trustModel: BitcoinBackendTrustModel): BitcoinBackendPrivacyLevel =
        when (trustModel) {
            BitcoinBackendTrustModel.UserOwnedNode -> BitcoinBackendPrivacyLevel.UserOwnedNodePreferred
            BitcoinBackendTrustModel.TrustedThirdParty -> BitcoinBackendPrivacyLevel.TrustedNodeStillSeesQueries
            BitcoinBackendTrustModel.PublicBackend -> BitcoinBackendPrivacyLevel.PublicBackendLeaksWalletQueries
            BitcoinBackendTrustModel.Unknown -> BitcoinBackendPrivacyLevel.UnknownRisk
        }

    private fun credentialPolicyFor(
        type: BitcoinBackendType,
        trustModel: BitcoinBackendTrustModel,
    ): BackendCredentialPolicy =
        when {
            trustModel == BitcoinBackendTrustModel.PublicBackend -> BackendCredentialPolicy.PublicEndpointNoCredentialStillLeaks
            type == BitcoinBackendType.BitcoinCoreRpc -> BackendCredentialPolicy.RequiresSecureStorageBeforeUse
            else -> BackendCredentialPolicy.NoCredentialStored
        }

    private fun capabilitiesFor(type: BitcoinBackendType): List<BitcoinBackendCapability> =
        when (type) {
            BitcoinBackendType.BitcoinCoreRpc -> listOf(
                BitcoinBackendCapability.SyncWallet,
                BitcoinBackendCapability.BroadcastTransaction,
                BitcoinBackendCapability.FeeEstimates,
                BitcoinBackendCapability.DescriptorWalletRpc,
            )
            BitcoinBackendType.Electrum -> listOf(
                BitcoinBackendCapability.SyncWallet,
                BitcoinBackendCapability.BroadcastTransaction,
                BitcoinBackendCapability.FeeEstimates,
                BitcoinBackendCapability.BlockHeaderVerification,
            )
            BitcoinBackendType.Esplora -> listOf(
                BitcoinBackendCapability.SyncWallet,
                BitcoinBackendCapability.BroadcastTransaction,
                BitcoinBackendCapability.FeeEstimates,
                BitcoinBackendCapability.MempoolView,
            )
        }

    private fun warningsFor(trustModel: BitcoinBackendTrustModel): List<String> =
        when (trustModel) {
            BitcoinBackendTrustModel.UserOwnedNode -> listOf("User-owned Bitcoin infrastructure is preferred for wallet privacy.")
            BitcoinBackendTrustModel.TrustedThirdParty -> listOf("Trusted third-party backends can still observe wallet queries.")
            BitcoinBackendTrustModel.PublicBackend -> listOf("Public backends can observe wallet queries. A user-owned node is preferred.")
            BitcoinBackendTrustModel.Unknown -> listOf("Unknown backend trust means wallet-query privacy risk is not classified.")
        }

    private fun implementationWarningsFor(type: BitcoinBackendType): List<String> =
        when (type) {
            BitcoinBackendType.BitcoinCoreRpc -> listOf(
                "Credentials are intentionally out of scope in this pass.",
                "Connection testing and wallet sync are not implemented yet.",
            )
            BitcoinBackendType.Electrum -> listOf("Connection testing and wallet sync are not implemented yet.")
            BitcoinBackendType.Esplora -> listOf("Connection testing and wallet sync are not implemented yet.")
        }

    private fun generateProfileId(
        type: BitcoinBackendType,
        label: String,
        endpoint: BackendEndpoint,
    ): String {
        val basis = "${type.name}-${label}-${endpoint.displayText}"
        val normalized = basis
            .lowercase()
            .map { character ->
                when {
                    character in 'a'..'z' || character in '0'..'9' -> character
                    else -> '-'
                }
            }
            .joinToString("")
            .trim('-')
            .replace(Regex("-+"), "-")
        return normalized.ifBlank { "backend-profile-placeholder" }
    }

    private data class EndpointValidation(
        val endpoint: BackendEndpoint?,
        val errors: List<BackendProfileValidationError>,
    )

    private data class ParsedPort(
        val value: Int? = null,
        val error: BackendProfileValidationError? = null,
    )

    private data class ParsedHttpInput(
        val host: String,
        val portText: String? = null,
        val path: String? = null,
        val useTls: Boolean? = null,
        val unsupportedScheme: Boolean = false,
        val userInfoPresent: Boolean = false,
    )
}
