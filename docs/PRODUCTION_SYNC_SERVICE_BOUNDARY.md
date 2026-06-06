# Production Sync Service Boundary

## Status

Skald Vault now has a Skald-owned production wallet sync service facade over the backend adapter, endpoint policy, receive-address policy, secure-storage capability, and backend observation models.

The Nodes screen now includes a minimal read-only production sync preflight/status card backed by this facade. Recovery Center and the Privacy Analyzer also consume the same disabled result through status-only models documented in [`RECOVERY_PRIVACY_SYNC_STATUS.md`](RECOVERY_PRIVACY_SYNC_STATUS.md). These surfaces show blockers and warnings for selected backend/profile metadata, secure-storage state, receive-address policy, and observation persistence, but they expose no working sync action and perform no connection test.

The facade and UI surface are disabled and fail-closed. They do not enable production BDK sync, Bitcoin Core RPC, Electrum, Esplora, backend connection testing, UTXO scanning, receive UI, address or UTXO persistence, descriptor persistence, secure storage, transaction construction, PSBT handling, signing, broadcasting, public backend defaults, Skald-operated infrastructure, or mainnet.

## Source Location

Production-safe common models:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/domain/onchain/BitcoinWalletSyncService.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/domain/recovery/RecoverySyncStatusModels.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/domain/privacy/PrivacySyncStatusModels.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/ui/components/BitcoinWalletSyncStatusUiModel.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/ui/screens/NodesScreen.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/ui/screens/RecoveryScreen.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/ui/screens/OverviewScreen.kt
```

Tests:

```text
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/BitcoinWalletSyncServiceTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/BitcoinWalletSyncStatusUiModelTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/RecoveryPrivacySyncStatusTest.kt
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/ProductionBackendAdapterSourceGuardTest.kt
```

## Facade Model

The service boundary defines Skald-owned concepts for:

- sync service identity,
- sync request,
- sync preflight,
- sync result,
- sync status,
- sync blockers,
- sync warnings,
- sync capabilities,
- redacted Skald-owned sync errors.

The request and result types depend only on Skald-owned models:

- Bitcoin backend profile metadata,
- endpoint validation results,
- disabled production backend adapter boundary,
- backend observation summaries,
- receive-address wallet and address state,
- secure-storage capability state.

The facade does not expose BDK, Electrum, Esplora, Bitcoin Core RPC, HTTP, socket, process, wallet database, or platform-native client types.

## Nodes Status Surface

The Nodes screen renders a small `Production sync preflight` status block after the simulated backend validation section.

The surface displays:

- selected backend profile metadata,
- selected descriptor wallet metadata if present,
- requested development network,
- sync blockers,
- sync warnings,
- a locked action label explaining that sync is not implemented.

Expected blockers include:

- production sync disabled,
- backend adapter disabled,
- no backend configured,
- invalid endpoint metadata,
- no operational wallet,
- secure storage unavailable,
- credential references unavailable,
- observation persistence unavailable,
- mainnet disabled when applicable.

Expected warnings include:

- no network connection attempted,
- public backend privacy leakage,
- backend query linkage,
- onion/Tor labeling preserved,
- Tor transport not implemented,
- no Skald-managed infrastructure.

The status surface consumes only Skald-owned result models. It does not call BDK, create backend clients, start processes, open sockets, persist observations, derive addresses, or expose a working sync button.

## Recovery And Privacy Status Surfaces

Recovery Center renders a read-only `Sync and observation recovery` card. It states that production sync is disabled, secure storage is unavailable, descriptor wallet profiles remain metadata-only, address index state is not persisted, and test-only regtest BDK validation does not create recoverable production wallet state.

The Overview Privacy Analyzer option renders a read-only `Backend observation privacy` card. It shows policy-only findings for public backend privacy risk, backend query linkage, onion/Tor labeling, Tor transport not implemented, displayed-versus-used address state, address reuse warnings after observed use, identity-linked UTXO placeholders, and coin-control-required spend readiness.

Both surfaces are status-only and share the same disabled sync result used by Nodes. They do not add working sync, connection testing, production observation persistence, receive UI, Nostr parsing, signing, broadcasting, or mainnet.

## Disabled Implementation

`DisabledBitcoinWalletSyncService` is the only sync service implementation in this pass.

It returns blocked results for:

- production sync disabled,
- no backend profile,
- invalid endpoint metadata,
- credential material rejected,
- mainnet disabled,
- no operational wallet context,
- secure storage unavailable,
- disabled production backend adapter,
- backend credentials unavailable,
- observation persistence unavailable,
- receive-address policy blockers.

It may call the disabled production backend adapter boundary to obtain a sanitized `BackendObservationSummary` shape, but that adapter is also fail-closed and does not create a backend client or scan a wallet.

## Preflight Policy

`BitcoinWalletSyncPolicy` evaluates expected production sync prerequisites without performing sync:

- selected backend profile exists,
- endpoint validation has no credential or userinfo failures,
- network is a development network,
- mainnet is rejected,
- wallet context is operational before future sync,
- receive-address policy can represent the address state,
- secure storage is unavailable for current secret-bearing flows,
- production backend adapter remains disabled,
- observation persistence is unavailable,
- public backend and onion/Tor warnings are preserved,
- credential references are metadata only.

Expected policy failures return Skald-owned blockers and warnings rather than throwing.

## Relationship To Existing Boundaries

Future production sync must pass through this order:

```text
Endpoint policy
        ↓
Sync preflight policy
        ↓
Production backend adapter
        ↓
BackendObservationSummary
        ↓
Receive-address policy
        ↓
Future encrypted observation persistence
        ↓
Future coin-control review
```

This pass defines only the service boundary and blocked preflight state. It does not implement the future steps that require real backend clients, persistence, secure storage, wallet activation, signing approval, or broadcast approval.

Production observation persistence remains explicitly deferred until encrypted vault or equivalent approved secure metadata storage exists. Observed addresses, labels, UTXOs, transaction notes, backend metadata, wallet history, and address index state are sensitive metadata and are not persisted by the current facade.

## Explicit Non-Capabilities

This boundary does not enable:

- production wallet sync,
- production backend networking,
- production Bitcoin Core RPC,
- production Electrum,
- production Esplora,
- production BDK scan APIs,
- production UTXO persistence,
- production address index persistence,
- app receive address generation,
- app receive UI,
- descriptor persistence,
- secure storage,
- transaction construction,
- PSBT import/export/finalization,
- signing,
- broadcasting,
- Nostr, Lightning, Cashu, or Payjoin behavior,
- public endpoint defaults,
- Skald-operated infrastructure,
- mainnet.

## Testing

Tests cover:

- default fail-closed sync result,
- no backend profile blocker,
- invalid endpoint blocker,
- mainnet rejection,
- non-operational wallet blocker,
- secure-storage unavailable blocker,
- public backend privacy warning,
- onion/Tor labeling warning without Tor transport claims,
- credential reference blocked while secure storage is disabled,
- disabled backend adapter blocking sync,
- observation summary shape without persisted or real observations,
- disabled networking, sync, persistence, signing, broadcasting, mainnet, and Skald infrastructure flags.
- sync status presentation labels for blockers and warnings,
- public backend and onion/Tor warning display,
- invalid endpoint display without userinfo exposure,
- no working sync action in the presentation model,
- UI/status source files remaining BDK/client/process free.
- Recovery/Privacy status files remaining BDK/client/process/persistence free,
- Recovery status making encrypted-vault persistence deferral explicit,
- Privacy status distinguishing displayed addresses from backend-observed used addresses.

The source guard includes the sync facade and status UI files and asserts that production boundary/status files remain BDK/client/process free.

## Next Step

The next focused pass should design production observation persistence around encrypted vault storage or continue Recovery/Privacy planning for future regtest/signet wallet activation. Production backend clients and observation persistence should remain deferred until secure storage, recovery integration, and persistence boundaries are reviewed.
