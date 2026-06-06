# Production Sync Service Boundary

## Status

Skald Vault now has a Skald-owned production wallet sync service facade over the backend adapter, endpoint policy, receive-address policy, secure-storage capability, and backend observation models.

The facade is disabled and fail-closed. It does not enable production BDK sync, Bitcoin Core RPC, Electrum, Esplora, backend connection testing, UTXO scanning, receive UI, address or UTXO persistence, descriptor persistence, secure storage, transaction construction, PSBT handling, signing, broadcasting, public backend defaults, Skald-operated infrastructure, or mainnet.

## Source Location

Production-safe common models:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/domain/onchain/BitcoinWalletSyncService.kt
```

Tests:

```text
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/BitcoinWalletSyncServiceTest.kt
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

The source guard includes the sync facade file and asserts that production boundary files remain BDK/client/process free.

## Next Step

The next focused pass should decide how future production sync preflight status is surfaced in On-chain or Nodes UI without adding a working sync button. Production backend clients and observation persistence should remain deferred until secure storage, recovery integration, and persistence boundaries are reviewed.
