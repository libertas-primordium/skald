package com.libertasprimordium.skald.domain.onchain

enum class BitcoinBackendEndpointScheme(val label: String) {
    None("none"),
    Http("http"),
    Https("https"),
}

enum class BitcoinBackendEndpointTransportMode(val label: String) {
    Direct("direct"),
    TorRequired("Tor required"),
    ProxyPlanned("proxy planned"),
}

enum class BitcoinBackendEndpointHostKind(val label: String) {
    Ipv4Loopback("IPv4 loopback"),
    Ipv4PrivateLan("IPv4 private LAN"),
    Ipv4Public("IPv4 public"),
    Ipv6Loopback("IPv6 loopback"),
    Ipv6PrivateLan("IPv6 private LAN"),
    Ipv6Public("IPv6 public"),
    Localhost("localhost"),
    DnsHostname("DNS hostname"),
    Onion("onion"),
    Unknown("unknown"),
}

enum class BitcoinBackendEndpointPrivacyHint(val label: String) {
    LocalLoopback("local loopback"),
    PrivateLan("private LAN"),
    PublicDnsOrIp("public DNS or IP"),
    OnionTor("onion / Tor"),
    Unknown("unknown"),
}

enum class BitcoinBackendEndpointField(val label: String) {
    Address("address"),
    Port("port"),
    Tls("TLS flag"),
    Path("path"),
    CredentialReference("credential reference"),
}

enum class BitcoinBackendEndpointWarning(val label: String) {
    LocalLoopbackEndpoint("local loopback endpoint"),
    PrivateLanEndpoint("private LAN endpoint"),
    PublicEndpointPrivacyLeak("public backend can observe wallet queries"),
    OnionEndpointRequiresTor("onion endpoint requires Tor/proxy handling"),
    DnsEndpointNeedsTrustReview("DNS endpoint requires trust review"),
    CredentialsReferenceOnly("credential reference only; credential values rejected"),
    ProductionSyncDisabled("production sync disabled"),
}

enum class BitcoinBackendEndpointParseError(val label: String) {
    BlankAddress("address must not be blank"),
    UserInfoRejected("userinfo or credentials are rejected"),
    CredentialMaterialRejected("credential-like material is rejected"),
    UnsupportedScheme("scheme is unsupported for this backend type"),
    PathUnsupported("path is unsupported for this backend type"),
    MalformedBracketedIpv6("bracketed IPv6 literal is malformed"),
    AmbiguousEndpointPort("address and explicit port are ambiguous"),
    AmbiguousUnbracketedIpv6Port("unbracketed IPv6 cannot carry an embedded port"),
    MissingPort("port is required for this backend type"),
    InvalidPort("port must be from 1 to 65535"),
    UnsupportedHost("host/address is malformed"),
    MainnetDefaultEndpointRejected("mainnet-default endpoint is rejected during development"),
}

data class BitcoinBackendEndpointPolicy(
    val backendType: BitcoinBackendType,
    val addressFieldRequired: Boolean,
    val portRequired: Boolean,
    val pathAllowed: Boolean,
    val httpSchemeAllowed: Boolean,
    val tlsFlagAllowed: Boolean,
    val credentialReferenceAllowed: Boolean,
    val requiredFields: Set<BitcoinBackendEndpointField>,
    val optionalFields: Set<BitcoinBackendEndpointField>,
) {
    companion object {
        fun forBackendType(type: BitcoinBackendType): BitcoinBackendEndpointPolicy =
            when (type) {
                BitcoinBackendType.BitcoinCoreRpc -> BitcoinBackendEndpointPolicy(
                    backendType = type,
                    addressFieldRequired = true,
                    portRequired = true,
                    pathAllowed = false,
                    httpSchemeAllowed = false,
                    tlsFlagAllowed = false,
                    credentialReferenceAllowed = true,
                    requiredFields = setOf(
                        BitcoinBackendEndpointField.Address,
                        BitcoinBackendEndpointField.Port,
                    ),
                    optionalFields = setOf(BitcoinBackendEndpointField.CredentialReference),
                )
                BitcoinBackendType.Electrum -> BitcoinBackendEndpointPolicy(
                    backendType = type,
                    addressFieldRequired = true,
                    portRequired = true,
                    pathAllowed = false,
                    httpSchemeAllowed = false,
                    tlsFlagAllowed = true,
                    credentialReferenceAllowed = false,
                    requiredFields = setOf(
                        BitcoinBackendEndpointField.Address,
                        BitcoinBackendEndpointField.Port,
                    ),
                    optionalFields = setOf(BitcoinBackendEndpointField.Tls),
                )
                BitcoinBackendType.Esplora -> BitcoinBackendEndpointPolicy(
                    backendType = type,
                    addressFieldRequired = true,
                    portRequired = false,
                    pathAllowed = true,
                    httpSchemeAllowed = true,
                    tlsFlagAllowed = true,
                    credentialReferenceAllowed = false,
                    requiredFields = setOf(BitcoinBackendEndpointField.Address),
                    optionalFields = setOf(
                        BitcoinBackendEndpointField.Port,
                        BitcoinBackendEndpointField.Tls,
                        BitcoinBackendEndpointField.Path,
                    ),
                )
            }
    }
}

data class BitcoinBackendEndpointParseInput(
    val backendType: BitcoinBackendType,
    val address: String,
    val explicitPort: String,
    val useTls: Boolean,
    val path: String,
) {
    val policy: BitcoinBackendEndpointPolicy
        get() = BitcoinBackendEndpointPolicy.forBackendType(backendType)
}

data class NormalizedBitcoinBackendEndpoint(
    val backendType: BitcoinBackendType,
    val host: String,
    val port: Int?,
    val useTls: Boolean,
    val scheme: BitcoinBackendEndpointScheme,
    val path: String?,
    val hostKind: BitcoinBackendEndpointHostKind,
    val privacyHint: BitcoinBackendEndpointPrivacyHint,
    val transportMode: BitcoinBackendEndpointTransportMode,
    val warnings: Set<BitcoinBackendEndpointWarning>,
) {
    val displayAuthority: String
        get() {
            val hostText = host.toDisplayHost()
            val portText = port?.let { ":$it" }.orEmpty()
            return "$hostText$portText"
        }

    fun toBackendEndpoint(): BackendEndpoint =
        when (backendType) {
            BitcoinBackendType.BitcoinCoreRpc,
            BitcoinBackendType.Electrum,
            -> TcpEndpoint(
                host = host,
                port = requireNotNull(port) { "TCP backend endpoint requires a normalized port." },
                useTls = useTls,
            )
            BitcoinBackendType.Esplora -> HttpEndpoint(
                host = host,
                port = port,
                useTls = useTls,
                path = path,
            )
        }
}

data class BitcoinBackendEndpointParseResult(
    val normalizedEndpoint: NormalizedBitcoinBackendEndpoint?,
    val errors: Set<BitcoinBackendEndpointParseError>,
    val warnings: Set<BitcoinBackendEndpointWarning>,
) {
    val isValid: Boolean
        get() = normalizedEndpoint != null && errors.isEmpty()
}

object BitcoinBackendEndpointParser {
    fun parse(input: BitcoinBackendEndpointParseInput): BitcoinBackendEndpointParseResult {
        val policy = input.policy
        val addressInput = input.address.trim()
        val pathInput = input.path.trim()
        val errors = mutableSetOf<BitcoinBackendEndpointParseError>()

        if (addressInput.isBlank()) {
            return BitcoinBackendEndpointParseResult(
                normalizedEndpoint = null,
                errors = setOf(BitcoinBackendEndpointParseError.BlankAddress),
                warnings = emptySet(),
            )
        }
        if (containsUserInfo(addressInput) || containsUserInfo(pathInput)) {
            errors += BitcoinBackendEndpointParseError.UserInfoRejected
        }
        if (containsCredentialMaterial(addressInput, pathInput)) {
            errors += BitcoinBackendEndpointParseError.CredentialMaterialRejected
        }

        val extracted = extractSchemeAuthorityAndPath(addressInput, policy)
        errors += extracted.errors
        val authority = extracted.authority
        val addressPath = extracted.path
        if (!policy.pathAllowed && (pathInput.isNotBlank() || addressPath.isNullOrBlank().not())) {
            errors += BitcoinBackendEndpointParseError.PathUnsupported
        }

        val authorityParts = parseAuthority(authority)
        errors += authorityParts.errors
        val explicitPort = input.explicitPort.trim()
        if (authorityParts.portText != null && explicitPort.isNotBlank()) {
            errors += BitcoinBackendEndpointParseError.AmbiguousEndpointPort
        }

        val port = parsePort(
            portText = explicitPort.ifBlank { authorityParts.portText.orEmpty() },
            required = policy.portRequired,
        )
        port.error?.let { errors += it }
        if (isMainnetDefaultPort(input.backendType, port.value)) {
            errors += BitcoinBackendEndpointParseError.MainnetDefaultEndpointRejected
        }

        val host = authorityParts.host.lowercase()
        val hostKind = classifyHost(host)
        if (hostKind == BitcoinBackendEndpointHostKind.Unknown) {
            errors += BitcoinBackendEndpointParseError.UnsupportedHost
        }

        if (errors.isNotEmpty()) {
            return BitcoinBackendEndpointParseResult(
                normalizedEndpoint = null,
                errors = errors,
                warnings = warningsFor(hostKind, input.backendType),
            )
        }

        val scheme = extracted.scheme ?: BitcoinBackendEndpointScheme.None
        val useTls = when (scheme) {
            BitcoinBackendEndpointScheme.Https -> true
            BitcoinBackendEndpointScheme.Http -> false
            BitcoinBackendEndpointScheme.None -> input.useTls && policy.tlsFlagAllowed
        }
        val normalizedPath = if (policy.pathAllowed) {
            pathInput.ifBlank { addressPath.orEmpty() }
                .ifBlank { null }
                ?.let { if (it.startsWith("/")) it else "/$it" }
        } else {
            null
        }
        val warnings = warningsFor(hostKind, input.backendType)
        val endpoint = NormalizedBitcoinBackendEndpoint(
            backendType = input.backendType,
            host = host,
            port = port.value,
            useTls = useTls,
            scheme = if (scheme == BitcoinBackendEndpointScheme.None && input.backendType == BitcoinBackendType.Esplora) {
                if (useTls) BitcoinBackendEndpointScheme.Https else BitcoinBackendEndpointScheme.Http
            } else {
                scheme
            },
            path = normalizedPath,
            hostKind = hostKind,
            privacyHint = privacyHintFor(hostKind),
            transportMode = if (hostKind == BitcoinBackendEndpointHostKind.Onion) {
                BitcoinBackendEndpointTransportMode.TorRequired
            } else {
                BitcoinBackendEndpointTransportMode.Direct
            },
            warnings = warnings,
        )

        return BitcoinBackendEndpointParseResult(
            normalizedEndpoint = endpoint,
            errors = emptySet(),
            warnings = warnings,
        )
    }

    private fun extractSchemeAuthorityAndPath(
        addressInput: String,
        policy: BitcoinBackendEndpointPolicy,
    ): ExtractedAddress {
        val schemeMarker = addressInput.indexOf("://")
        val errors = mutableSetOf<BitcoinBackendEndpointParseError>()
        if (schemeMarker >= 0) {
            val schemeText = addressInput.substring(0, schemeMarker).lowercase()
            val scheme = when (schemeText) {
                "http" -> BitcoinBackendEndpointScheme.Http
                "https" -> BitcoinBackendEndpointScheme.Https
                else -> null
            }
            if (scheme == null || !policy.httpSchemeAllowed) {
                return ExtractedAddress(
                    scheme = scheme,
                    authority = "",
                    path = null,
                    errors = setOf(BitcoinBackendEndpointParseError.UnsupportedScheme),
                )
            }
            val remainder = addressInput.substring(schemeMarker + 3)
            val pathStart = remainder.indexOf("/")
            return ExtractedAddress(
                scheme = scheme,
                authority = if (pathStart >= 0) remainder.substring(0, pathStart) else remainder,
                path = if (pathStart >= 0) remainder.substring(pathStart) else null,
                errors = errors,
            )
        }

        if (addressInput.contains("://")) {
            errors += BitcoinBackendEndpointParseError.UnsupportedScheme
        }
        val pathStart = addressInput.indexOf("/")
        return ExtractedAddress(
            scheme = null,
            authority = if (pathStart >= 0) addressInput.substring(0, pathStart) else addressInput,
            path = if (pathStart >= 0) addressInput.substring(pathStart) else null,
            errors = errors,
        )
    }

    private fun parseAuthority(authority: String): ParsedAuthority {
        if (authority.startsWith("[")) {
            val closing = authority.indexOf("]")
            if (closing <= 1) {
                return ParsedAuthority(
                    host = "",
                    portText = null,
                    errors = setOf(BitcoinBackendEndpointParseError.MalformedBracketedIpv6),
                )
            }
            val host = authority.substring(1, closing)
            val rest = authority.substring(closing + 1)
            val portText = when {
                rest.isBlank() -> null
                rest.startsWith(":") && rest.length > 1 -> rest.substring(1)
                else -> return ParsedAuthority(
                    host = host,
                    portText = null,
                    errors = setOf(BitcoinBackendEndpointParseError.MalformedBracketedIpv6),
                )
            }
            val errors = if (classifyIpv6(host) == BitcoinBackendEndpointHostKind.Unknown) {
                setOf(BitcoinBackendEndpointParseError.MalformedBracketedIpv6)
            } else {
                emptySet()
            }
            return ParsedAuthority(host = host, portText = portText, errors = errors)
        }

        val colonCount = authority.count { it == ':' }
        if (colonCount == 1) {
            val host = authority.substringBefore(":")
            val port = authority.substringAfter(":")
            return ParsedAuthority(host = host, portText = port, errors = emptySet())
        }
        if (colonCount > 1) {
            return ParsedAuthority(
                host = authority,
                portText = null,
                errors = emptySet(),
            )
        }
        return ParsedAuthority(host = authority, portText = null, errors = emptySet())
    }

    private fun parsePort(
        portText: String,
        required: Boolean,
    ): ParsedPort {
        if (portText.isBlank()) {
            return if (required) {
                ParsedPort(error = BitcoinBackendEndpointParseError.MissingPort)
            } else {
                ParsedPort(value = null)
            }
        }
        if (!portText.all { it.isDigit() }) {
            return ParsedPort(error = BitcoinBackendEndpointParseError.InvalidPort)
        }
        val value = portText.toIntOrNull()
            ?: return ParsedPort(error = BitcoinBackendEndpointParseError.InvalidPort)
        if (value !in 1..65535) {
            return ParsedPort(error = BitcoinBackendEndpointParseError.InvalidPort)
        }
        return ParsedPort(value = value)
    }

    private fun classifyHost(host: String): BitcoinBackendEndpointHostKind =
        when {
            host == "localhost" -> BitcoinBackendEndpointHostKind.Localhost
            host.endsWith(".onion") && host.removeSuffix(".onion").isNotBlank() ->
                BitcoinBackendEndpointHostKind.Onion
            classifyIpv4(host) != BitcoinBackendEndpointHostKind.Unknown -> classifyIpv4(host)
            host.contains(":") -> classifyIpv6(host)
            isDnsHostname(host) -> BitcoinBackendEndpointHostKind.DnsHostname
            else -> BitcoinBackendEndpointHostKind.Unknown
        }

    private fun classifyIpv4(host: String): BitcoinBackendEndpointHostKind {
        val parts = host.split(".")
        if (parts.size != 4) return BitcoinBackendEndpointHostKind.Unknown
        val octets = parts.map { part ->
            if (part.isBlank() || part.length > 3 || !part.all { it.isDigit() }) {
                return BitcoinBackendEndpointHostKind.Unknown
            }
            part.toIntOrNull() ?: return BitcoinBackendEndpointHostKind.Unknown
        }
        if (octets.any { it !in 0..255 }) return BitcoinBackendEndpointHostKind.Unknown
        return when {
            octets[0] == 127 -> BitcoinBackendEndpointHostKind.Ipv4Loopback
            octets[0] == 10 -> BitcoinBackendEndpointHostKind.Ipv4PrivateLan
            octets[0] == 192 && octets[1] == 168 -> BitcoinBackendEndpointHostKind.Ipv4PrivateLan
            octets[0] == 172 && octets[1] in 16..31 -> BitcoinBackendEndpointHostKind.Ipv4PrivateLan
            octets[0] == 169 && octets[1] == 254 -> BitcoinBackendEndpointHostKind.Ipv4PrivateLan
            else -> BitcoinBackendEndpointHostKind.Ipv4Public
        }
    }

    private fun classifyIpv6(host: String): BitcoinBackendEndpointHostKind {
        if (!host.contains(":")) return BitcoinBackendEndpointHostKind.Unknown
        if (host.count { it == ':' } > 7 || host.any { !it.isDigit() && it.lowercaseChar() !in 'a'..'f' && it != ':' }) {
            return BitcoinBackendEndpointHostKind.Unknown
        }
        if (host == "::1" || host == "0:0:0:0:0:0:0:1") {
            return BitcoinBackendEndpointHostKind.Ipv6Loopback
        }
        val lower = host.lowercase()
        return when {
            lower.startsWith("fc") || lower.startsWith("fd") || lower.startsWith("fe80") ->
                BitcoinBackendEndpointHostKind.Ipv6PrivateLan
            else -> BitcoinBackendEndpointHostKind.Ipv6Public
        }
    }

    private fun isDnsHostname(host: String): Boolean {
        if (host.length !in 1..253) return false
        if (host.contains("..")) return false
        return host.split(".").all { label ->
            label.isNotBlank() &&
                label.length <= 63 &&
                label.first().isLetterOrDigit() &&
                label.last().isLetterOrDigit() &&
                label.all { it.isLetterOrDigit() || it == '-' }
        }
    }

    private fun privacyHintFor(hostKind: BitcoinBackendEndpointHostKind): BitcoinBackendEndpointPrivacyHint =
        when (hostKind) {
            BitcoinBackendEndpointHostKind.Ipv4Loopback,
            BitcoinBackendEndpointHostKind.Ipv6Loopback,
            BitcoinBackendEndpointHostKind.Localhost,
            -> BitcoinBackendEndpointPrivacyHint.LocalLoopback
            BitcoinBackendEndpointHostKind.Ipv4PrivateLan,
            BitcoinBackendEndpointHostKind.Ipv6PrivateLan,
            -> BitcoinBackendEndpointPrivacyHint.PrivateLan
            BitcoinBackendEndpointHostKind.Ipv4Public,
            BitcoinBackendEndpointHostKind.Ipv6Public,
            BitcoinBackendEndpointHostKind.DnsHostname,
            -> BitcoinBackendEndpointPrivacyHint.PublicDnsOrIp
            BitcoinBackendEndpointHostKind.Onion -> BitcoinBackendEndpointPrivacyHint.OnionTor
            BitcoinBackendEndpointHostKind.Unknown -> BitcoinBackendEndpointPrivacyHint.Unknown
        }

    private fun warningsFor(
        hostKind: BitcoinBackendEndpointHostKind,
        backendType: BitcoinBackendType,
    ): Set<BitcoinBackendEndpointWarning> =
        buildSet {
            add(BitcoinBackendEndpointWarning.ProductionSyncDisabled)
            when (privacyHintFor(hostKind)) {
                BitcoinBackendEndpointPrivacyHint.LocalLoopback -> add(BitcoinBackendEndpointWarning.LocalLoopbackEndpoint)
                BitcoinBackendEndpointPrivacyHint.PrivateLan -> add(BitcoinBackendEndpointWarning.PrivateLanEndpoint)
                BitcoinBackendEndpointPrivacyHint.PublicDnsOrIp -> {
                    add(BitcoinBackendEndpointWarning.PublicEndpointPrivacyLeak)
                    add(BitcoinBackendEndpointWarning.DnsEndpointNeedsTrustReview)
                }
                BitcoinBackendEndpointPrivacyHint.OnionTor -> add(BitcoinBackendEndpointWarning.OnionEndpointRequiresTor)
                BitcoinBackendEndpointPrivacyHint.Unknown -> Unit
            }
            if (backendType == BitcoinBackendType.BitcoinCoreRpc) {
                add(BitcoinBackendEndpointWarning.CredentialsReferenceOnly)
            }
        }

    private fun isMainnetDefaultPort(type: BitcoinBackendType, port: Int?): Boolean =
        type == BitcoinBackendType.BitcoinCoreRpc && port == 8332

    private fun containsUserInfo(address: String): Boolean =
        address.contains("@")

    private fun containsCredentialMaterial(address: String, path: String): Boolean {
        val combined = "$address $path".lowercase()
        return listOf("password", "passwd", "token", "cookie", "macaroon", "rune", "nwc", "secret", "nsec")
            .any { combined.contains(it) }
    }

    private data class ExtractedAddress(
        val scheme: BitcoinBackendEndpointScheme?,
        val authority: String,
        val path: String?,
        val errors: Set<BitcoinBackendEndpointParseError>,
    )

    private data class ParsedAuthority(
        val host: String,
        val portText: String?,
        val errors: Set<BitcoinBackendEndpointParseError>,
    )

    private data class ParsedPort(
        val value: Int? = null,
        val error: BitcoinBackendEndpointParseError? = null,
    )
}

fun String.toDisplayHost(): String =
    if (contains(":") && !startsWith("[") && !endsWith("]")) {
        "[$this]"
    } else {
        this
    }
