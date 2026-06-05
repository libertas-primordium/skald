package com.libertasprimordium.skald.domain.lightning

import com.libertasprimordium.skald.domain.core.BackendConnectionStatus

enum class LightningConnectorType(val label: String) {
    AlbyHubNwc("Alby Hub / NWC"),
    Phoenixd("Phoenixd"),
    Lnd("LND"),
    CoreLightning("Core Lightning"),
    EmbeddedNodePlanned("Embedded node later"),
}

enum class SetupComplexity(val label: String) {
    Low("low"),
    Medium("medium"),
    High("high"),
    Advanced("advanced"),
}

enum class LightningPermissionScope(val label: String) {
    ReadNode("read node"),
    CreateInvoice("create invoices"),
    SendWithAmountCap("send with amount cap"),
    KeysendOrZap("keysend/zap send"),
    ChannelOpen("channel open"),
    ChannelClose("channel close"),
    PeerManagement("peer management"),
    OnChainWithdraw("on-chain withdraw"),
    AdminHighRisk("admin/high-risk"),
    CustomPolicy("custom policy"),
}

data class LightningConnectorProfile(
    val type: LightningConnectorType,
    val status: BackendConnectionStatus,
    val setupComplexity: SetupComplexity,
    val permissionModel: List<LightningPermissionScope>,
    val plannedCapabilities: List<String>,
    val recoveryWarning: String,
)
