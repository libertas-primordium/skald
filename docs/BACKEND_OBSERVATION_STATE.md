# Backend Observation State

## Status

Skald Vault now has a Skald-owned backend observation and UTXO state boundary for future on-chain sync work.

This is a domain/model/policy boundary only. It does not enable production wallet sync, production Electrum or Esplora support, production UTXO scanning, app receive UI, address index persistence, UTXO persistence, transaction construction, PSBT creation, signing, broadcasting, secure storage, public backend defaults, Skald-operated infrastructure, or mainnet.

The implemented models live in common Kotlin under the on-chain domain package and do not import BDK.

The production backend adapter interface and endpoint normalization boundary is documented in [`PRODUCTION_BACKEND_ADAPTER_BOUNDARY.md`](PRODUCTION_BACKEND_ADAPTER_BOUNDARY.md). That boundary targets this observation model but remains disabled/fail-closed.

## Purpose

The boundary defines how future backend adapters should hand chain observations to Skald without leaking BDK, Electrum, Esplora, Bitcoin Core RPC, process, or platform-specific types into common UI, settings, persisted models, Recovery Center state, coin-control models, or public app services.

Backend observation answers:

- which development network was observed,
- what backend/trust class produced the observation,
- whether the observation was local regtest, user-owned, onion-routed, public, or unknown,
- which addresses became backend-observed and used,
- which UTXOs were observed,
- whether those UTXOs are unconfirmed, confirmed, stale, spent/removed, or conflicting,
- which privacy or recovery warnings must follow the UTXO into future coin-control views,
- why observation alone does not authorize spending.

## Source Location

Pure domain models:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/domain/onchain/BackendObservationModels.kt
```

Tests:

```text
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/BackendObservationStateTest.kt
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/BackendObservationSourceGuardTest.kt
```

Related production backend adapter boundary tests:

```text
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/ProductionBackendAdapterBoundaryTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/BackendEndpointPolicyTest.kt
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/ProductionBackendAdapterSourceGuardTest.kt
```

Optional desktop-test mapping from the BDK Electrum validation result remains under:

```text
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/bdk/
```

## Model Scope

The boundary includes Skald-owned concepts for:

- backend observation session identity,
- backend observation source,
- backend observation trust class,
- backend observation status,
- backend observation capabilities,
- backend observation warnings,
- backend observation blocking issues,
- sanitized outpoint display,
- observed UTXO lifecycle,
- confirmation state,
- script class,
- wallet scope,
- UTXO risk flags,
- spend-readiness state,
- observed address usage,
- observation summary.

The model intentionally supports future backend sources without binding to a concrete implementation:

- Bitcoin Core RPC,
- Electrum,
- Esplora,
- compact filters,
- local regtest Electrum harness,
- unknown future adapters.

## Observation Versus Spend Authority

Backend observation never authorizes spending.

An observed UTXO is not spendable by default. The current model always requires future coin-control review before any spend path can use an observed UTXO.

The model explicitly records:

- `CoinControlRequiredBeforeSpend`,
- `ObservationDoesNotAuthorizeSpending`,
- `productionSyncEnabled = false`,
- `signingOrBroadcastEnabled = false`,
- `anySpendableWithoutCoinControl = false`.

Future spend flows must still enforce manual input review, fee/change review, explicit signing approval, and explicit broadcast approval.

## Address Usage Boundary

The observation policy uses the receive-address policy as the address state foundation.

Rules:

- Displaying or reserving an address does not mark it used.
- A positive backend-observed unconfirmed receive marks the address observed-unconfirmed and used.
- A positive backend-observed confirmed receive marks the address observed-confirmed and used.
- Stale, conflicting, spent/removed, or placeholder observations do not silently become safe.
- A used address produces a high-friction reuse warning through the receive-address policy.

## Backend Trust And Privacy

Observation sessions carry backend trust classification.

The current policy warns for:

- local regtest-only observations,
- public backend wallet-query leakage,
- backend address/query linkage,
- onion backend labeling,
- stale observations,
- reorg/conflict risk,
- identity-linked UTXOs,
- imported-key backup risk.

No public backend endpoint is added by this pass. No Skald-operated backend or indexer is introduced.

## Mainnet Policy

Mainnet remains disabled.

Backend observation rejects `MainnetDisabled` and does not produce an address-used transition for mainnet. The policy uses Skald `NetworkEnvironment` values and keeps `mainnetEnabled = false`.

## Persistence Policy

No production observation repository was added.

This pass does not persist:

- observed UTXOs,
- real addresses,
- address indexes,
- txids,
- outpoints,
- descriptors,
- wallet databases,
- backend credentials,
- secrets,
- runtime BDK wallet material.

Future persistence must be separately designed and must remain compatible with encrypted storage requirements before real wallet data is stored.

## BDK Boundary

Common backend observation models do not import `org.bitcoindevkit`.

The desktop-test-only BDK Electrum UTXO scan validation now maps its sanitized runtime regtest observation into `BackendObservationSummary`. That proves the adapter can translate BDK scan output into Skald-owned state without enabling production sync.

BDK wallet, address, Electrum, and scan APIs remain confined to desktop test validation code and platform probe files.

## Testing

Tests cover:

- displayed address remains unused before backend observation,
- unconfirmed backend observation marks an address used,
- confirmed backend observation carries confirmation depth,
- observed UTXOs are not spendable without future coin-control approval,
- mainnet observation is rejected,
- public backend trust produces privacy warnings,
- local regtest and onion backend trust classes stay explicit,
- stale and conflicting observations do not become safe,
- Nostr identity-linked sources carry future privacy warnings,
- imported-key sources carry backup warnings,
- backend observation models do not import BDK or process/backend-client APIs,
- production source sets do not use BDK scan APIs,
- no persistence code or real wallet-material fixtures were added.

## Relationship To Regtest Validation

The desktop-test-only BDK Electrum regtest UTXO scan validation remains documented in [`BDK_REGTEST_UTXO_SCAN_VALIDATION.md`](BDK_REGTEST_UTXO_SCAN_VALIDATION.md).

With local `electrs` configured through `SKALD_ELECTRS`, the opt-in validation can observe a funded runtime regtest UTXO through BDK and map that observation into the Skald-owned backend observation summary.

That path remains test-only. It does not create app wallet sync, app receive UI, production address persistence, production UTXO persistence, signing, broadcasting, or mainnet.

## Next Step

The production backend adapter interface and endpoint normalization boundary has been added and documented in [`PRODUCTION_BACKEND_ADAPTER_BOUNDARY.md`](PRODUCTION_BACKEND_ADAPTER_BOUNDARY.md). It defines disabled adapter request/result models and a parser for a single address input that recognizes IPv4, IPv6 including bracketed host/port forms, DNS hostnames, and `.onion` hosts while rejecting credentials and userinfo.

The next focused pass should migrate backend settings UI toward the unified address field if needed or design a disabled production sync service facade over the adapter boundary. Do not proceed to production sync or user-visible UTXO balances until secure storage, recovery-state integration, backend trust display, endpoint persistence, and observation persistence boundaries are explicitly reviewed.
