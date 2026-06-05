package com.libertasprimordium.skald.settings

import com.libertasprimordium.skald.domain.core.NetworkEnvironment
import com.libertasprimordium.skald.domain.onchain.BackendNotConfigured
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendProfile
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendProfileId
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendSettingsState
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendTrustModel
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendType
import com.libertasprimordium.skald.domain.onchain.BitcoinBackendValidator
import com.libertasprimordium.skald.domain.onchain.EditableBitcoinBackendProfileInput
import com.libertasprimordium.skald.domain.onchain.HttpEndpoint
import com.libertasprimordium.skald.domain.onchain.TcpEndpoint

object BitcoinBackendSettingsCodec {
    private const val Version = "skald.backend-settings.v1"
    private const val Empty = "-"

    fun encode(state: BitcoinBackendSettingsState): String {
        val consistent = state.withConsistentSelection()
        return buildString {
            appendLine(Version)
            appendLine("selected|${encodePart(consistent.selectedProfileId?.value ?: Empty)}")
            consistent.profiles.forEach { profile ->
                appendLine(encodeProfile(profile))
            }
        }
    }

    fun decode(raw: String?): BitcoinBackendSettingsState {
        if (raw.isNullOrBlank()) return BitcoinBackendSettingsState.Empty
        return runCatching {
            val lines = raw.lineSequence()
                .map { it.trimEnd() }
                .filter { it.isNotBlank() }
                .toList()
            if (lines.firstOrNull() != Version) {
                return@runCatching BitcoinBackendSettingsState.Empty
            }

            val selectedId = lines
                .firstOrNull { it.startsWith("selected|") }
                ?.split("|")
                ?.getOrNull(1)
                ?.let(::decodePart)
                ?.takeIf { it != Empty }
                ?.let(::BitcoinBackendProfileId)

            val profiles = lines
                .filter { it.startsWith("profile|") }
                .mapNotNull(::decodeProfile)

            BitcoinBackendSettingsState(
                profiles = profiles,
                selectedProfileId = selectedId,
            ).withConsistentSelection()
        }.getOrElse {
            BitcoinBackendSettingsState.Empty
        }
    }

    private fun encodeProfile(profile: BitcoinBackendProfile): String {
        val endpointKind = when (profile.endpoint) {
            BackendNotConfigured -> "none"
            is HttpEndpoint -> "http"
            is TcpEndpoint -> "tcp"
        }
        val host = when (val endpoint = profile.endpoint) {
            BackendNotConfigured -> ""
            is HttpEndpoint -> endpoint.host
            is TcpEndpoint -> endpoint.host
        }
        val port = when (val endpoint = profile.endpoint) {
            BackendNotConfigured -> ""
            is HttpEndpoint -> endpoint.port?.toString().orEmpty()
            is TcpEndpoint -> endpoint.port.toString()
        }
        val tls = when (val endpoint = profile.endpoint) {
            BackendNotConfigured -> false
            is HttpEndpoint -> endpoint.useTls
            is TcpEndpoint -> endpoint.useTls
        }
        val path = when (val endpoint = profile.endpoint) {
            BackendNotConfigured -> ""
            is HttpEndpoint -> endpoint.path.orEmpty()
            is TcpEndpoint -> ""
        }
        val parts = listOf(
            "profile",
            profile.id.value,
            profile.label,
            profile.type.name,
            profile.network.name,
            endpointKind,
            host,
            port,
            tls.toString(),
            path,
            profile.trustModel.name,
        )
        return parts.joinToString("|") { encodePart(it) }
    }

    private fun decodeProfile(line: String): BitcoinBackendProfile? {
        val parts = line.split("|").map(::decodePart)
        if (parts.size != 11 || parts[0] != "profile") return null

        val id = BitcoinBackendProfileId(parts[1])
        val label = parts[2]
        val type = enumValueOrNull<BitcoinBackendType>(parts[3]) ?: return null
        val network = enumValueOrNull<NetworkEnvironment>(parts[4]) ?: return null
        if (!BitcoinBackendValidator.isDevelopmentNetwork(network)) return null
        val endpointKind = parts[5]
        val host = parts[6]
        val port = parts[7]
        val tls = parts[8].toBooleanStrictOrNull() ?: return null
        val path = parts[9]
        val trustModel = enumValueOrNull<BitcoinBackendTrustModel>(parts[10]) ?: return null

        if (endpointKind == "none") return null
        val validation = BitcoinBackendValidator.validate(
            EditableBitcoinBackendProfileInput(
                id = id,
                label = label,
                type = type,
                network = network,
                host = host,
                portText = port,
                useTls = tls,
                path = path,
                trustModel = trustModel,
            ),
        )
        return validation.normalizedProfile
    }

    private inline fun <reified T : Enum<T>> enumValueOrNull(value: String): T? =
        enumValues<T>().firstOrNull { it.name == value }

    private fun encodePart(value: String): String =
        value
            .replace("%", "%25")
            .replace("|", "%7C")
            .replace("\n", "%0A")
            .replace("\r", "%0D")

    private fun decodePart(value: String): String =
        value
            .replace("%0D", "\r")
            .replace("%0A", "\n")
            .replace("%7C", "|")
            .replace("%25", "%")
}
