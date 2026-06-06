package com.libertasprimordium.skald

import com.libertasprimordium.skald.domain.onchain.BitcoinBackendEndpointHostKind
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendEndpointParseError
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendEndpointParseInput
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendEndpointParser
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendEndpointPrivacyHint
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendEndpointScheme
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendEndpointTransportMode
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendEndpointWarning
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendType
import com.libertasprimordium.skald.domain.onchain.HttpEndpoint
import com.libertasprimordium.skald.domain.onchain.TcpEndpoint
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class BackendEndpointPolicyTest {
    @Test
    fun parsesIpv4LiteralWithExplicitPort() {
        val result = parse(
            type = BitcoinBackendType.BitcoinCoreRpc,
            address = "127.0.0.1",
            port = "18443",
        )

        val endpoint = assertNotNull(result.normalizedEndpoint)
        assertEquals("127.0.0.1", endpoint.host)
        assertEquals(18443, endpoint.port)
        assertEquals(BitcoinBackendEndpointHostKind.Ipv4Loopback, endpoint.hostKind)
        assertEquals(BitcoinBackendEndpointPrivacyHint.LocalLoopback, endpoint.privacyHint)
        assertContains(endpoint.warnings, BitcoinBackendEndpointWarning.LocalLoopbackEndpoint)
        assertIs<TcpEndpoint>(endpoint.toBackendEndpoint())
        assertEquals("tcp://127.0.0.1:18443", endpoint.toBackendEndpoint().displayText)
    }

    @Test
    fun parsesIpv4HostPortInSingleAddressField() {
        val result = parse(
            type = BitcoinBackendType.Electrum,
            address = "127.0.0.1:50001",
            port = "",
        )

        val endpoint = assertNotNull(result.normalizedEndpoint)
        assertEquals("127.0.0.1", endpoint.host)
        assertEquals(50001, endpoint.port)
        assertEquals("tcp://127.0.0.1:50001", endpoint.toBackendEndpoint().displayText)
    }

    @Test
    fun parsesDnsHostPortInSingleAddressField() {
        val result = parse(
            type = BitcoinBackendType.Electrum,
            address = "example.invalid:50001",
            port = "",
        )

        val endpoint = assertNotNull(result.normalizedEndpoint)
        assertEquals("example.invalid", endpoint.host)
        assertEquals(50001, endpoint.port)
        assertEquals(BitcoinBackendEndpointHostKind.DnsHostname, endpoint.hostKind)
        assertContains(endpoint.warnings, BitcoinBackendEndpointWarning.PublicEndpointPrivacyLeak)
        assertEquals("tcp://example.invalid:50001", endpoint.toBackendEndpoint().displayText)
    }

    @Test
    fun parsesUnbracketedIpv6LiteralWhenPortIsSeparate() {
        val result = parse(
            type = BitcoinBackendType.Electrum,
            address = "::1",
            port = "50001",
        )

        val endpoint = assertNotNull(result.normalizedEndpoint)
        assertEquals("::1", endpoint.host)
        assertEquals(50001, endpoint.port)
        assertEquals(BitcoinBackendEndpointHostKind.Ipv6Loopback, endpoint.hostKind)
        assertEquals("tcp://[::1]:50001", endpoint.toBackendEndpoint().displayText)
    }

    @Test
    fun parsesBracketedIpv6HostPortInSingleAddressField() {
        val result = parse(
            type = BitcoinBackendType.Electrum,
            address = "[::1]:50001",
            port = "",
        )

        val endpoint = assertNotNull(result.normalizedEndpoint)
        assertEquals("::1", endpoint.host)
        assertEquals(50001, endpoint.port)
        assertEquals(BitcoinBackendEndpointHostKind.Ipv6Loopback, endpoint.hostKind)
        assertEquals("tcp://[::1]:50001", endpoint.toBackendEndpoint().displayText)
    }

    @Test
    fun parsesOnionHostAndPreservesTorLabeling() {
        val result = parse(
            type = BitcoinBackendType.Electrum,
            address = "exampleexampleexample.onion",
            port = "50001",
        )

        val endpoint = assertNotNull(result.normalizedEndpoint)
        assertEquals(BitcoinBackendEndpointHostKind.Onion, endpoint.hostKind)
        assertEquals(BitcoinBackendEndpointPrivacyHint.OnionTor, endpoint.privacyHint)
        assertEquals(BitcoinBackendEndpointTransportMode.TorRequired, endpoint.transportMode)
        assertContains(endpoint.warnings, BitcoinBackendEndpointWarning.OnionEndpointRequiresTor)
    }

    @Test
    fun parsesDnsHostnameWhereBackendPolicyAllowsUserSelectedHost() {
        val result = parse(
            type = BitcoinBackendType.Esplora,
            address = "example.invalid",
            port = "",
            useTls = true,
            path = "/api",
        )

        val endpoint = assertNotNull(result.normalizedEndpoint)
        assertEquals("example.invalid", endpoint.host)
        assertEquals(BitcoinBackendEndpointHostKind.DnsHostname, endpoint.hostKind)
        assertEquals(BitcoinBackendEndpointPrivacyHint.PublicDnsOrIp, endpoint.privacyHint)
        assertEquals(BitcoinBackendEndpointScheme.Https, endpoint.scheme)
        assertEquals("/api", endpoint.path)
        assertContains(endpoint.warnings, BitcoinBackendEndpointWarning.PublicEndpointPrivacyLeak)
        assertIs<HttpEndpoint>(endpoint.toBackendEndpoint())
        assertEquals("https://example.invalid/api", endpoint.toBackendEndpoint().displayText)
    }

    @Test
    fun parsesExplicitHttpsEsploraInputWithoutHidingTlsState() {
        val result = parse(
            type = BitcoinBackendType.Esplora,
            address = "https://example.invalid/api",
            port = "",
            useTls = false,
        )

        val endpoint = assertNotNull(result.normalizedEndpoint)
        assertEquals(BitcoinBackendEndpointScheme.Https, endpoint.scheme)
        assertTrue(endpoint.useTls)
        assertEquals("/api", endpoint.path)
        assertEquals("https://example.invalid/api", endpoint.toBackendEndpoint().displayText)
    }

    @Test
    fun parsesEsploraHostPortWithExplicitTlsAndPathControls() {
        val result = parse(
            type = BitcoinBackendType.Esplora,
            address = "example.invalid:8080",
            port = "",
            useTls = true,
            path = "api",
        )

        val endpoint = assertNotNull(result.normalizedEndpoint)
        assertEquals("example.invalid", endpoint.host)
        assertEquals(8080, endpoint.port)
        assertEquals(BitcoinBackendEndpointScheme.Https, endpoint.scheme)
        assertEquals("/api", endpoint.path)
        assertEquals("https://example.invalid:8080/api", endpoint.toBackendEndpoint().displayText)
    }

    @Test
    fun rejectsUserInfoAndCredentialMaterial() {
        val userInfo = parse(
            type = BitcoinBackendType.Esplora,
            address = "https://DEMO_VALUE_DO_NOT_USE@example.invalid/api",
            port = "",
        )
        val tokenPath = parse(
            type = BitcoinBackendType.Esplora,
            address = "https://example.invalid/api?token=DEMO_VALUE_DO_NOT_USE",
            port = "",
        )

        assertContains(userInfo.errors, BitcoinBackendEndpointParseError.UserInfoRejected)
        assertContains(tokenPath.errors, BitcoinBackendEndpointParseError.CredentialMaterialRejected)
        assertFalse(userInfo.isValid)
        assertFalse(tokenPath.isValid)
    }

    @Test
    fun rejectsUnsupportedSchemeForTcpBackends() {
        val result = parse(
            type = BitcoinBackendType.Electrum,
            address = "ssl://example.invalid:50001",
            port = "",
        )

        assertContains(result.errors, BitcoinBackendEndpointParseError.UnsupportedScheme)
        assertFalse(result.isValid)
    }

    @Test
    fun enforcesPortRequirementsWithoutDefaultPorts() {
        val coreMissing = parse(
            type = BitcoinBackendType.BitcoinCoreRpc,
            address = "127.0.0.1",
            port = "",
        )
        val esploraOptional = parse(
            type = BitcoinBackendType.Esplora,
            address = "https://example.invalid/api",
            port = "",
        )

        assertContains(coreMissing.errors, BitcoinBackendEndpointParseError.MissingPort)
        assertFalse(coreMissing.isValid)
        assertTrue(esploraOptional.isValid)
        assertEquals(null, assertNotNull(esploraOptional.normalizedEndpoint).port)
    }

    @Test
    fun rejectsMalformedBracketedIpv6AndRequiresSeparatePortForUnbracketedIpv6() {
        val malformedBracket = parse(
            type = BitcoinBackendType.Electrum,
            address = "[::1",
            port = "",
        )
        val unbracketedWithoutPort = parse(
            type = BitcoinBackendType.Electrum,
            address = "::1",
            port = "",
        )

        assertContains(malformedBracket.errors, BitcoinBackendEndpointParseError.MalformedBracketedIpv6)
        assertContains(unbracketedWithoutPort.errors, BitcoinBackendEndpointParseError.MissingPort)
        assertFalse(malformedBracket.isValid)
        assertFalse(unbracketedWithoutPort.isValid)
    }

    @Test
    fun rejectsAmbiguousPortAndMainnetDefaultCorePort() {
        val ambiguous = parse(
            type = BitcoinBackendType.Electrum,
            address = "127.0.0.1:50001",
            port = "50002",
        )
        val mainnetDefault = parse(
            type = BitcoinBackendType.BitcoinCoreRpc,
            address = "127.0.0.1",
            port = "8332",
        )

        assertContains(ambiguous.errors, BitcoinBackendEndpointParseError.AmbiguousEndpointPort)
        assertContains(mainnetDefault.errors, BitcoinBackendEndpointParseError.MainnetDefaultEndpointRejected)
        assertFalse(ambiguous.isValid)
        assertFalse(mainnetDefault.isValid)
    }

    private fun parse(
        type: BitcoinBackendType,
        address: String,
        port: String,
        useTls: Boolean = false,
        path: String = "",
    ) = BitcoinBackendEndpointParser.parse(
        BitcoinBackendEndpointParseInput(
            backendType = type,
            address = address,
            explicitPort = port,
            useTls = useTls,
            path = path,
        ),
    )
}
