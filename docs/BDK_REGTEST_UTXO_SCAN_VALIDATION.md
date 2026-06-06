# BDK Regtest UTXO Scan Validation

## Status

Skald Vault now has a desktop-test-only BDK regtest UTXO scan validation boundary and a smallest-path BDK Electrum scan adapter.

BDK `2.3.0` on JVM exposes wallet scan request types and indexed backend clients, but the resolved Maven artifact does not expose a direct Bitcoin Core RPC scan client that can observe UTXOs from the existing local `bitcoind` harness alone.

A desktop-test-only local Electrum-compatible regtest indexer harness boundary exists and is documented in [`LOCAL_ELECTRUM_REGTEST_HARNESS.md`](LOCAL_ELECTRUM_REGTEST_HARNESS.md). The UTXO validation boundary attempts BDK's Electrum full-scan path only when both opt-in flags are set. With local `electrs` `v0.11.1` provided through `SKALD_ELECTRS`, the full desktop-test-only path now completes on Linux and observes a funded runtime regtest UTXO through BDK.

This is not production wallet sync. It is not wired into the app UI, settings repositories, descriptor wallet profile metadata, Recovery Center state, backend settings, coin-control drafts, or persisted app models.

## Source Location

The validation boundary lives under desktop test sources:

```text
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/bdk/
```

The local process harness remains separate:

```text
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/regtest/
```

No production source set uses BDK wallet scan APIs, local process execution, or backend client construction.

## What Was Checked

The validation inspects the resolved BDK JVM artifact and records only Skald-owned status:

- `FullScanRequest` is present.
- `SyncRequest` is present.
- `ElectrumClient` is present.
- `EsploraClient` is present.
- `CbfClient` is present.
- No direct Bitcoin Core RPC scan client class is present under the checked names.

When only `SKALD_RUN_BDK_REGTEST_UTXO_SCAN=1` is set, the validation path still reports:

```text
BDK_REGTEST_UTXO_SCAN_REQUIRES_LOCAL_INDEXER
```

This blocked result is deliberate. It prevents hidden fallback to public infrastructure and avoids pretending that app wallet sync exists.

When both `SKALD_RUN_BDK_REGTEST_UTXO_SCAN=1` and `SKALD_RUN_LOCAL_ELECTRUM_REGTEST=1` are set, the validation invokes a desktop-test-only BDK Electrum scan adapter. That adapter:

1. Starts the local `bitcoind` and local Electrum-compatible regtest harness.
2. Creates runtime-only test wallet material in memory.
3. Reveals a regtest receive address in test-only code.
4. Funds that address using the harness-owned temporary `bitcoind` mining wallet.
5. Builds a BDK full-scan request and calls BDK's Electrum full-scan API against the localhost harness endpoint.
6. Applies the BDK wallet update in memory.
7. Converts observed unspent outputs into Skald-owned sanitized scan results.
8. Maps the sanitized observation into the Skald-owned backend observation/UTXO state boundary.
9. Applies the receive-address policy transition from displayed/reserved to backend-observed/used.

With local `electrs` configured, the combined opt-in command completed the full observation path: local bitcoind started, local electrs started, BDK Electrum scan was attempted, the funded runtime regtest UTXO was observed, and receive-address policy marked the displayed address as backend-observed/used only after observation. If `electrs` is absent, the same path still reports a Skald-owned unavailable state rather than using public infrastructure. It does not use public Electrum, public Esplora, testnet, mainnet, DNS fallback, HTTP fallback, or Skald-operated infrastructure.

The Skald-owned backend observation state boundary is documented in [`BACKEND_OBSERVATION_STATE.md`](BACKEND_OBSERVATION_STATE.md). The desktop-test-only scan result now carries a `BackendObservationSummary` so future production adapter design can target Skald-owned state instead of BDK types.

The production backend adapter and endpoint normalization boundary is documented in [`PRODUCTION_BACKEND_ADAPTER_BOUNDARY.md`](PRODUCTION_BACKEND_ADAPTER_BOUNDARY.md). It defines the disabled production-facing interface that future runtime backend implementations must target. It does not turn this desktop-test BDK scan adapter into production sync.

## Receive-Address Policy Link

The tests also exercise the Skald-owned receive-address policy with sanitized fake scan observations:

1. A placeholder receive address starts displayed/reserved and unused.
2. A sanitized backend observation marks it observed and used.
3. A reuse attempt then requires explicit high-friction confirmation.

The non-integration policy test does not claim that BDK observed a real UTXO. The full BDK observation path is available only through the combined local Electrum opt-in with local binaries available.

## How To Run

Normal UTXO validation tests do not create runtime wallet material and do not start local backend processes:

```bash
./gradlew :composeApp:desktopTest --tests '*Utxo*'
```

The opt-in local-indexer-required validation path is:

```bash
SKALD_RUN_BDK_REGTEST_UTXO_SCAN=1 ./gradlew :composeApp:desktopTest --tests '*Utxo*' --rerun-tasks
```

In sandboxed environments that need a writable Gradle cache and IPv4 preference:

```bash
GRADLE_USER_HOME=/tmp/skald-gradle-home SKALD_RUN_BDK_REGTEST_UTXO_SCAN=1 ./gradlew --no-daemon -Djava.net.preferIPv4Stack=true :composeApp:desktopTest --tests '*Utxo*' --rerun-tasks
```

That command passes by asserting the local-indexer-required state. It does not fund a BDK wallet address, call BDK sync, apply a wallet update, or query public infrastructure.

The combined local Electrum adapter command is:

```bash
SKALD_RUN_LOCAL_ELECTRUM_REGTEST=1 SKALD_RUN_BDK_REGTEST_UTXO_SCAN=1 ./gradlew :composeApp:desktopTest --tests '*Utxo*' --rerun-tasks
```

In sandboxed environments that need a writable Gradle cache and IPv4 preference:

```bash
GRADLE_USER_HOME=/tmp/skald-gradle-home SKALD_RUN_LOCAL_ELECTRUM_REGTEST=1 SKALD_RUN_BDK_REGTEST_UTXO_SCAN=1 ./gradlew --no-daemon -Djava.net.preferIPv4Stack=true :composeApp:desktopTest --tests '*Utxo*' --rerun-tasks
```

If `electrs` is absent, the command reports the missing binary and exits through the safe unavailable/blocked result. If `electrs` is present and compatible, the command should complete the local observation path; startup or BDK scan failures are treated as targeted integration failures for that opt-in path.

The local Electrum-compatible harness command is documented separately:

```bash
SKALD_RUN_LOCAL_ELECTRUM_REGTEST=1 ./gradlew :composeApp:desktopTest --tests '*Electrum*' --rerun-tasks
```

If `electrs` is absent, the command reports the missing binary instead of using a public backend.

## Explicit Non-Capabilities

This validation boundary does not enable:

- production BDK sync,
- production UTXO scanning,
- app receive address generation,
- production receive UI,
- production address index persistence,
- descriptor persistence,
- app wallet activation,
- secure storage,
- transaction construction,
- PSBT import, export, or finalization,
- signing,
- broadcasting,
- Nostr key parsing,
- Lightning,
- Cashu,
- Payjoin,
- mainnet,
- public backend defaults,
- Skald-operated infrastructure.

## Safety Rules

The boundary must remain:

- desktop test/development only,
- regtest only,
- explicitly opt-in for the BDK scan validation result,
- redacted in result models and assertion messages,
- free of committed address and transaction fixtures,
- free of production storage paths,
- free of public backend defaults,
- free of signing and broadcasting.

Runtime-generated regtest addresses or transaction identifiers may be used only in future opt-in test execution. They must not be committed as fixtures, written to docs, written to build history, or exposed through result `toString()` output.

Observed runtime UTXOs are represented as sanitized Skald-owned state. Observation does not authorize spending, does not make a UTXO spendable without coin-control review, and does not enable production sync.

## Local Indexer Decision

The existing local harness uses `bitcoind` and `bitcoin-cli` to start a temporary regtest node and mine blocks. BDK `2.3.0` does not expose a direct local Bitcoin Core RPC scanner in the resolved JVM artifact, so BDK cannot observe wallet UTXOs from that harness alone.

The BDK Electrum scan adapter now covers the smallest safe indexed-backend path. The local harness starts bitcoind with a localhost-only P2P listener for the Electrum harness path and passes both local RPC and local P2P addresses to electrs. BDK `ElectrumClient` is constructed with the local endpoint only, then `ping`, `fullScan`, `applyUpdate`, and `listUnspent` run in desktop test code.

With `SKALD_ELECTRS` pointing at local electrs `v0.11.1`, the combined opt-in UTXO validation passes. Remaining chain-source decisions before production work are:

- add a local Esplora indexer harness if Electrum proves unsuitable,
- design a compact-filter local peer harness separately,
- adapt the BDK test adapter around a different safe local chain-source strategy,
- evaluate whether a newer BDK version changes the backend API enough to justify migration.

None of those options should be added implicitly. They require a focused pass because they introduce real local backend behavior and new failure modes.

## Backend Observation Boundary

The follow-up backend observation state boundary has been added in common Kotlin. It models:

- backend observation source and trust class,
- observed UTXO lifecycle,
- confirmation state,
- address-used transitions,
- stale/conflict warnings,
- public-backend privacy warnings,
- identity-linked and imported-key risk flags,
- coin-control-required spend readiness.

It remains production-safe model state only. It does not add production UTXO persistence, app UTXO display, signing, broadcasting, public endpoints, or mainnet.

## Next Step

The next focused branch should add a disabled production sync service facade over the adapter boundary. Do not proceed to production wallet sync, production receive UI, PSBT construction, signing, broadcasting, or Nostr payment flows until secure storage, recovery-state integration, backend trust display, and persistence boundaries are explicitly reviewed.
