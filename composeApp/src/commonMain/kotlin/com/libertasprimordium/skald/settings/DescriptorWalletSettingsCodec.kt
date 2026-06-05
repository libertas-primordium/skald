package com.libertasprimordium.skald.settings

import com.libertasprimordium.skald.domain.core.NetworkEnvironment
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletBackupRequirement
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletBlockingIssue
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletCapability
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletMetadataProfile
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletOrigin
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletProfileId
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletProfileLabel
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletProfileStatus
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletRiskAcknowledgement
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletSettingsState
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletSpendPolicy
import com.libertasprimordium.skald.domain.onchain.DescriptorWalletWorkflowState

object DescriptorWalletSettingsCodec {
    private const val Version = "skald.descriptor-wallet-settings.v1"
    private const val Empty = "-"

    fun encode(state: DescriptorWalletSettingsState): String {
        val consistent = state.withConsistentSelection()
        return buildString {
            appendLine(Version)
            appendLine("selected|${encodePart(consistent.selectedProfileId?.value ?: Empty)}")
            consistent.profiles.forEach { profile ->
                appendLine(encodeProfile(profile))
            }
        }
    }

    fun decode(raw: String?): DescriptorWalletSettingsState {
        if (raw.isNullOrBlank()) return DescriptorWalletSettingsState.Empty
        return runCatching {
            val lines = raw.lineSequence()
                .map { it.trimEnd() }
                .filter { it.isNotBlank() }
                .toList()
            if (lines.firstOrNull() != Version) {
                return@runCatching DescriptorWalletSettingsState.Empty
            }

            val selectedId = lines
                .firstOrNull { it.startsWith("selected|") }
                ?.split("|")
                ?.getOrNull(1)
                ?.let(::decodePart)
                ?.takeIf { it != Empty }
                ?.let(::DescriptorWalletProfileId)

            val profiles = lines
                .filter { it.startsWith("profile|") }
                .mapNotNull(::decodeProfile)

            DescriptorWalletSettingsState(
                profiles = profiles,
                selectedProfileId = selectedId,
            ).withConsistentSelection()
        }.getOrElse {
            DescriptorWalletSettingsState.Empty
        }
    }

    private fun encodeProfile(profile: DescriptorWalletMetadataProfile): String {
        val parts = listOf(
            "profile",
            profile.id.value,
            profile.label.value,
            profile.origin.name,
            profile.network.name,
            profile.profileStatus.name,
            profile.workflowState.name,
            profile.spendPolicy.name,
            profile.backupRequirement.name,
            profile.riskAcknowledgements.joinToString(",") { it.name }.ifBlank { Empty },
            profile.blockingIssues.joinToString(",") { it.name }.ifBlank { Empty },
            profile.capabilities.joinToString(",") { it.name }.ifBlank { Empty },
        )
        return parts.joinToString("|") { encodePart(it) }
    }

    private fun decodeProfile(line: String): DescriptorWalletMetadataProfile? {
        val parts = line.split("|").map(::decodePart)
        if (parts.size != 12 || parts[0] != "profile") return null

        val id = DescriptorWalletProfileId(parts[1])
        val label = parts[2].trim().takeIf { it.isNotBlank() } ?: return null
        val origin = enumValueOrNull<DescriptorWalletOrigin>(parts[3]) ?: return null
        val network = enumValueOrNull<NetworkEnvironment>(parts[4]) ?: return null
        if (!network.isDevelopmentSelectable || network.allowsMainnetOperations) return null
        val status = enumValueOrNull<DescriptorWalletProfileStatus>(parts[5]) ?: return null
        val workflowState = enumValueOrNull<DescriptorWalletWorkflowState>(parts[6]) ?: return null
        val spendPolicy = enumValueOrNull<DescriptorWalletSpendPolicy>(parts[7]) ?: return null
        val backupRequirement = enumValueOrNull<DescriptorWalletBackupRequirement>(parts[8]) ?: return null
        val acknowledgements = decodeEnumSet<DescriptorWalletRiskAcknowledgement>(parts[9])
        val blockingIssues = decodeEnumSet<DescriptorWalletBlockingIssue>(parts[10])
        val capabilities = decodeEnumSet<DescriptorWalletCapability>(parts[11])
            .takeIf { it.isNotEmpty() }
            ?: return null

        return DescriptorWalletMetadataProfile(
            id = id,
            label = DescriptorWalletProfileLabel(label),
            origin = origin,
            network = network,
            profileStatus = status,
            workflowState = workflowState,
            spendPolicy = spendPolicy,
            backupRequirement = backupRequirement,
            riskAcknowledgements = acknowledgements,
            blockingIssues = blockingIssues,
            capabilities = capabilities,
            descriptorTextState = "DESCRIPTOR_TEXT_NOT_STORED",
            keyMaterialState = "KEY_MATERIAL_NOT_CREATED",
            isSelected = false,
            isOperational = false,
        )
    }

    private inline fun <reified T : Enum<T>> decodeEnumSet(raw: String): Set<T> {
        if (raw == Empty || raw.isBlank()) return emptySet()
        return raw.split(",")
            .mapNotNull { enumValueOrNull<T>(it) }
            .toSet()
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
