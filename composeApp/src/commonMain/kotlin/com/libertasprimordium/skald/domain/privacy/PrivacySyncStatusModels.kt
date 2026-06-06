package com.libertasprimordium.skald.domain.privacy

import com.libertasprimordium.skald.domain.core.WalletRail
import com.libertasprimordium.skald.domain.onchain.BackendObservationSummary
import com.libertasprimordium.skald.domain.onchain.BackendObservationWarning
import com.libertasprimordium.skald.domain.onchain.BitcoinWalletSyncBlocker
import com.libertasprimordium.skald.domain.onchain.BitcoinWalletSyncResult
import com.libertasprimordium.skald.domain.onchain.BitcoinWalletSyncWarning

enum class PrivacySyncFindingCategory(val label: String) {
    ProductionSync("production sync"),
    BackendTrust("backend trust"),
    TorLabeling("Tor/onion labeling"),
    ObservationPersistence("observation persistence"),
    AddressUsage("address usage"),
    AddressReuse("address reuse"),
    IdentityLinkage("identity linkage"),
    CoinControl("coin control"),
}

data class PrivacySyncFinding(
    val category: PrivacySyncFindingCategory,
    val level: PrivacyRiskLevel,
    val title: String,
    val detail: String,
)

data class PrivacySyncStatus(
    val title: String,
    val state: String,
    val summary: String,
    val findings: List<PrivacySyncFinding>,
    val canRunAnalysis: Boolean,
    val productionObservationPersistenceEnabled: Boolean,
    val torTransportImplemented: Boolean,
)

object PrivacySyncStatusAnalyzer {
    fun analyze(
        syncResult: BitcoinWalletSyncResult,
        observationSummary: BackendObservationSummary? = syncResult.observationSummary,
    ): PrivacySyncStatus =
        PrivacySyncStatus(
            title = "Backend observation privacy",
            state = "preflight only",
            summary = "Privacy status is policy-only. No production backend query, chain scan, address derivation, observation persistence, or cluster analysis is performed.",
            findings = findings(syncResult, observationSummary),
            canRunAnalysis = false,
            productionObservationPersistenceEnabled = false,
            torTransportImplemented = false,
        )

    fun asPortfolioRisks(status: PrivacySyncStatus): List<PrivacyRisk> =
        status.findings.map { finding ->
            PrivacyRisk(
                rail = WalletRail.OnChain,
                level = finding.level,
                title = finding.title,
                detail = finding.detail,
            )
        }

    private fun findings(
        syncResult: BitcoinWalletSyncResult,
        observationSummary: BackendObservationSummary?,
    ): List<PrivacySyncFinding> =
        buildList {
            add(
                PrivacySyncFinding(
                    category = PrivacySyncFindingCategory.ProductionSync,
                    level = PrivacyRiskLevel.Info,
                    title = "Production sync disabled",
                    detail = "The sync facade is fail-closed and no network connection is attempted.",
                ),
            )
            if (
                BitcoinWalletSyncBlocker.ObservationPersistenceUnavailable in syncResult.blockers ||
                BitcoinWalletSyncWarning.NoObservationPersisted in syncResult.warnings
            ) {
                add(
                    PrivacySyncFinding(
                        category = PrivacySyncFindingCategory.ObservationPersistence,
                        level = PrivacyRiskLevel.Warning,
                        title = "Observation persistence deferred",
                        detail = "Observed addresses, UTXOs, backend metadata, labels, and wallet history are sensitive metadata and are not persisted before encrypted vault storage exists.",
                    ),
                )
            }
            if (BitcoinWalletSyncWarning.PublicBackendPrivacyLeak in syncResult.warnings) {
                add(
                    PrivacySyncFinding(
                        category = PrivacySyncFindingCategory.BackendTrust,
                        level = PrivacyRiskLevel.Danger,
                        title = "Public backend privacy risk",
                        detail = "A public backend can observe and link wallet queries. A user-owned node remains preferred.",
                    ),
                )
            }
            if (BitcoinWalletSyncWarning.BackendCanLinkWalletQueries in syncResult.warnings) {
                add(
                    PrivacySyncFinding(
                        category = PrivacySyncFindingCategory.BackendTrust,
                        level = PrivacyRiskLevel.Warning,
                        title = "Backend query linkage",
                        detail = "The selected backend trust class can link wallet query metadata when future sync exists.",
                    ),
                )
            }
            if (BitcoinWalletSyncWarning.OnionTorLabelPreserved in syncResult.warnings) {
                add(
                    PrivacySyncFinding(
                        category = PrivacySyncFindingCategory.TorLabeling,
                        level = PrivacyRiskLevel.Info,
                        title = "Onion endpoint labeled",
                        detail = "Onion/Tor endpoint labeling is preserved for future policy and warnings.",
                    ),
                )
            }
            if (BitcoinWalletSyncWarning.TorTransportNotImplemented in syncResult.warnings) {
                add(
                    PrivacySyncFinding(
                        category = PrivacySyncFindingCategory.TorLabeling,
                        level = PrivacyRiskLevel.Warning,
                        title = "Tor transport not implemented",
                        detail = "A .onion endpoint preserves Tor labeling only; proxy transport remains unavailable.",
                    ),
                )
            }
            add(
                PrivacySyncFinding(
                    category = PrivacySyncFindingCategory.AddressUsage,
                    level = PrivacyRiskLevel.Info,
                    title = "Displayed address is not used",
                    detail = "Receive-address policy keeps displayed/reserved addresses unused until backend observation reports receive activity.",
                ),
            )
            observationSummary?.addressUsage?.let { usage ->
                if (usage.addressMarkedUsed) {
                    add(
                        PrivacySyncFinding(
                            category = PrivacySyncFindingCategory.AddressUsage,
                            level = PrivacyRiskLevel.Warning,
                            title = "Backend-observed address marked used",
                            detail = "The observed receive transition moved the address from ${usage.lifecycleBefore.label} to ${usage.lifecycleAfter.label}.",
                        ),
                    )
                    add(
                        PrivacySyncFinding(
                            category = PrivacySyncFindingCategory.AddressReuse,
                            level = PrivacyRiskLevel.Danger,
                            title = "Address reuse requires confirmation",
                            detail = "Observed/used address reuse requires explicit high-friction confirmation and is unsafe for normal receive use.",
                        ),
                    )
                }
            }
            val observationWarnings = observationSummary?.warnings.orEmpty()
            if (BackendObservationWarning.IdentityLinkedUtxo in observationWarnings) {
                add(
                    PrivacySyncFinding(
                        category = PrivacySyncFindingCategory.IdentityLinkage,
                        level = PrivacyRiskLevel.Danger,
                        title = "Identity-linked UTXO placeholder",
                        detail = "Nostr-linked or identity-linked UTXOs require future isolation warnings; no Nostr key parsing occurs in this status pass.",
                    ),
                )
            }
            if (BackendObservationWarning.CoinControlRequiredBeforeSpend in observationWarnings ||
                observationSummary?.observedUtxos?.isNotEmpty() == true
            ) {
                add(
                    PrivacySyncFinding(
                        category = PrivacySyncFindingCategory.CoinControl,
                        level = PrivacyRiskLevel.Warning,
                        title = "Coin control required before spend",
                        detail = "Backend observation does not make a UTXO spendable. Future spending still requires coin-control review and approval.",
                    ),
                )
            }
        }
}
