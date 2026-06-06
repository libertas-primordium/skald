# Production Backend Adapter Boundary

## Status

Skald Vault now has Skald-owned production backend adapter interfaces, endpoint normalization models, disabled/fail-closed adapter results, a Nodes/backend settings form that uses the unified address input boundary, and a disabled production sync service facade that composes these boundaries.

This is a boundary-only pass. It does not enable production Bitcoin Core RPC, Electrum, Esplora, BDK sync, UTXO scanning, receive UI, address index persistence, UTXO persistence, descriptor persistence, secure storage, transaction construction, PSBT handling, signing, broadcasting, public backend defaults, Skald-operated infrastructure, or mainnet.

## Purpose

Future production sync code must target Skald-owned state rather than BDK, Electrum, Esplora, Bitcoin Core RPC, HTTP, socket, or platform-specific client types.

The adapter boundary defines how a future backend implementation will accept a user-selected backend profile and eventually return a `BackendObservationSummary`. For now the only production adapter implementation is disabled and returns Skald-owned blocked results.

The production sync service facade is documented in [`PRODUCTION_SYNC_SERVICE_BOUNDARY.md`](PRODUCTION_SYNC_SERVICE_BOUNDARY.md). It currently calls only the disabled adapter boundary and returns fail-closed preflight/result state.

## Source Location

Production-safe common models:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/domain/onchain/BitcoinBackendAdapterModels.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/domain/onchain/BitcoinBackendEndpointPolicy.kt
```

Validation wiring:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/domain/onchain/BitcoinBackendValidation.kt
```

Tests:

```text
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/BackendEndpointPolicyTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/ProductionBackendAdapterBoundaryTest.kt
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/ProductionBackendAdapterSourceGuardTest.kt
```

## Adapter Model

The boundary defines Skald-owned concepts for:

- backend adapter identity,
- backend adapter kind,
- adapter capabilities,
- disabled/blocked status,
- adapter warnings,
- adapter blocking issues,
- Skald-owned adapter errors,
- network policy,
- connection policy,
- observation request,
- observation result.

The request and result types do not expose BDK classes, Electrum client classes, Esplora client classes, Bitcoin Core RPC clients, process handles, sockets, HTTP clients, or platform-specific APIs.

## Disabled Production Adapter

`DisabledProductionBitcoinBackendAdapter` is the only production adapter implementation in this pass.

It returns blocked results such as:

- `BACKEND_NOT_CONFIGURED`,
- `PRODUCTION_BACKEND_SYNC_DISABLED`,
- `MAINNET_DISABLED`,
- `BACKEND_CREDENTIALS_UNAVAILABLE`.

It never opens sockets, performs DNS lookups, creates HTTP/RPC/Electrum/Esplora clients, calls BDK sync APIs, persists observations, signs, broadcasts, or enables mainnet.

## Endpoint Normalization

`BitcoinBackendEndpointParser` provides the shared parser for the Nodes/backend settings address field and for future backend adapter implementations.

Supported address metadata:

- IPv4 literals,
- IPv4 `host:port`,
- DNS hostnames where backend policy allows user-selected hosts,
- IPv6 literals with a separate port field,
- bracketed IPv6 host/port forms such as `[::1]:50001`,
- `.onion` hosts.

Rejected address metadata:

- userinfo or credentials,
- credential-like strings,
- unsupported schemes,
- malformed bracketed IPv6,
- ambiguous address-plus-port combinations,
- unsupported paths for TCP backends,
- invalid ports,
- Bitcoin Core mainnet-default RPC port metadata during development.

The parser keeps scheme, TLS, port, path, and transport/proxy mode explicit. It does not invent default backend endpoints and does not add Skald-operated infrastructure.

The current Nodes UI uses one primary address field for host metadata. A port embedded in the address field is parsed and normalized; if the address omits a port, the UI keeps the explicit port control visible where backend policy requires or allows it. TLS, path, and future transport/proxy state remain separate controls or labels rather than hidden URL behavior.

Persisted backend settings remain backward-compatible with the existing non-secret normalized host/port/path fields. Loading old separate host/port profiles still works, and saving through the unified UI still serializes normalized non-secret endpoint metadata. Credential values and userinfo are still rejected and are not serialized.

## Backend Type Policy

The endpoint policy models future backend requirements:

- Bitcoin Core RPC requires address and port, allows a credential reference only, and rejects credential values.
- Electrum requires address and port, allows explicit TLS metadata, and rejects credential values.
- Esplora requires address, allows optional port, path, and explicit HTTP/HTTPS metadata, and rejects credential values.

Credential references remain metadata only. Secure storage is still disabled/fail-closed, so any profile with a credential reference is blocked by the disabled adapter.

## Trust And Privacy Labeling

Endpoint normalization classifies:

- local loopback,
- private LAN,
- public DNS/IP,
- onion/Tor,
- unknown.

The production adapter policy maps backend profiles into `BackendObservationTrust`, preserving onion/Tor labeling and public-backend privacy warnings for future UI.

## Observation Boundary

Future production sync must return `BackendObservationSummary`.

This pass proves only that:

- the adapter boundary can represent no backend configured,
- local regtest profile metadata can be blocked with production sync disabled,
- public backend privacy warnings are preserved,
- onion/Tor labeling is preserved,
- credential references are blocked while secure storage is disabled,
- mainnet is rejected,
- sanitized placeholder observations can be represented through Skald-owned `BackendObservationSummary`.

No production adapter currently produces real observations.

## Mainnet Policy

Mainnet remains disabled. The adapter network policy allows only development networks:

- regtest,
- signet,
- testnet,
- testnet4.

Mainnet observation or sync requests are rejected before any production backend client can exist.

## Relationship To Test Harnesses

The desktop-test-only local `bitcoind`, local `electrs`, and BDK Electrum scan validation path remains test infrastructure.

Those tests already prove that BDK can observe a funded runtime regtest UTXO through local Electrum and map it into `BackendObservationSummary`. This document defines the production-facing boundary that future runtime adapters must target without leaking BDK or backend client types.

## Explicit Non-Capabilities

This boundary does not enable:

- production backend networking,
- production Bitcoin Core RPC,
- production Electrum,
- production Esplora,
- production BDK sync,
- production UTXO scanning,
- app receive address generation,
- production receive UI,
- address index persistence,
- UTXO persistence,
- descriptor persistence,
- wallet activation,
- secure storage,
- PSBT construction,
- signing,
- broadcasting,
- Nostr, Lightning, Cashu, or Payjoin behavior,
- mainnet.

## Next Step

The disabled production sync service facade is now documented in [`PRODUCTION_SYNC_SERVICE_BOUNDARY.md`](PRODUCTION_SYNC_SERVICE_BOUNDARY.md).

The next focused pass should decide how to surface the disabled sync preflight status in On-chain or Nodes UI without adding a working sync button or backend connection path.

Do not add production sync, signing, broadcasting, public endpoint defaults, secret persistence, or mainnet until those boundaries are explicitly reviewed.
