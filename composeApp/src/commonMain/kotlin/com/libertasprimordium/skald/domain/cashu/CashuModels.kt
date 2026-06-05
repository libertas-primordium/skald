package com.libertasprimordium.skald.domain.cashu

import com.libertasprimordium.skald.domain.core.BackendConnectionStatus

enum class CashuMintTrustModel(val label: String) {
    ManualMintSelection("manual mint selection"),
    MintTrustedEcash("mint-trusted ecash"),
    CapabilityClaimUnverified("capability claim unverified"),
}

data class CashuMintProfile(
    val label: String,
    val balanceSats: Long,
    val status: BackendConnectionStatus,
    val trustModel: CashuMintTrustModel,
    val exposureLimitSats: Long?,
    val plannedCapabilities: List<String>,
    val recoverySummary: String,
    val isDemoMint: Boolean,
)
