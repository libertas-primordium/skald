# Local Electrum Regtest Harness

## Status

Skald Vault has a desktop-test-only local Electrum-compatible regtest indexer harness boundary.

The harness is development/test infrastructure only. It does not enable production Electrum backend support, production wallet sync, production UTXO scanning, app receive UI, address-index persistence, BDK persistence, signing, broadcasting, secure storage, or mainnet.

The current local environment has `bitcoind`, `bitcoin-cli`, and local `electrs` `v0.11.1` available when `SKALD_ELECTRS` points at the built binary. The opt-in harness now starts the local indexer successfully, and the BDK Electrum scan adapter completes desktop-test-only regtest UTXO observation through it.

## Purpose

The previous BDK regtest UTXO scan validation boundary found that BDK `2.3.0` exposes Electrum, Esplora, and compact-filter scan clients, but no direct Bitcoin Core RPC scan client in the resolved JVM artifact. A local Electrum-compatible indexer is the preferred next local indexed backend because it can be started against the temporary `bitcoind` regtest harness without using public infrastructure.

This harness adds the local process and readiness boundary used by the desktop-test-only BDK Electrum scan adapter. The adapter is documented in [`BDK_REGTEST_UTXO_SCAN_VALIDATION.md`](BDK_REGTEST_UTXO_SCAN_VALIDATION.md).

## Source Location

Harness code lives under desktop test sources:

```text
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/regtest/
```

No production source set uses `electrs`, Electrum process execution, or local indexer lifecycle code.

## Required Binaries

The full opt-in smoke path needs:

```text
bitcoind
bitcoin-cli
electrs
```

Discovery checks:

```text
SKALD_BITCOIND
SKALD_BITCOIN_CLI
SKALD_ELECTRS
```

If those variables are unset, the harness searches `PATH`.

## Commands

Normal tests do not require `electrs`:

```bash
./gradlew :composeApp:desktopTest --tests '*Electrum*'
```

The full local indexer smoke path is opt-in:

```bash
SKALD_RUN_LOCAL_ELECTRUM_REGTEST=1 ./gradlew :composeApp:desktopTest --tests '*Electrum*' --rerun-tasks
```

In sandboxed environments that need a writable Gradle cache and IPv4 preference:

```bash
GRADLE_USER_HOME=/tmp/skald-gradle-home SKALD_RUN_LOCAL_ELECTRUM_REGTEST=1 ./gradlew --no-daemon -Djava.net.preferIPv4Stack=true :composeApp:desktopTest --tests '*Electrum*' --rerun-tasks
```

## Skip and Availability Behavior

When `SKALD_RUN_LOCAL_ELECTRUM_REGTEST` is not set to `1`, the harness reports `disabled` and exits successfully.

When the opt-in flag is set but required binaries are missing, the harness reports `unavailable` with the missing binary names. It does not fall back to public Electrum, public Esplora, testnet, mainnet, DNS, HTTP, Tor/proxy, or any Skald-operated endpoint.

When binaries are present and opt-in is enabled, startup or readiness failures are treated as real integration failures for the targeted test.

## Harness Behavior

The intended full smoke path:

1. Creates temporary `bitcoind` and indexer datadirs.
2. Starts local `bitcoind` with `-regtest`.
3. Waits for node readiness with `bitcoin-cli getblockchaininfo`.
4. Starts `electrs` with regtest, the temporary node datadir, local RPC address, local P2P address, temporary indexer database directory, and a localhost Electrum endpoint.
5. Waits for localhost Electrum TCP readiness.
6. Generates one regtest block through a temporary `bitcoind` mining wallet.
7. Stops `electrs`.
8. Stops `bitcoind`.
9. Deletes temporary state where practical.

The temporary mining wallet belongs to the test harness only. It is not a Skald wallet, not a BDK wallet, not app storage, and not production wallet material.

## BDK Adapter Use

Local artifact inspection for pinned `bdk-jvm:2.3.0` shows an `ElectrumClient` constructor accepting a server endpoint string and methods for `ping`, `fullScan`, and `sync`. The harness itself does not import BDK. It only provides a localhost regtest Electrum endpoint and a safe harness-owned funding method.

The BDK Electrum scan adapter lives separately under desktop test BDK validation code. It uses this harness only when both of these opt-in variables are set:

```text
SKALD_RUN_LOCAL_ELECTRUM_REGTEST=1
SKALD_RUN_BDK_REGTEST_UTXO_SCAN=1
```

The adapter does not use public Electrum endpoints and does not create production wallet sync.

## Safety Boundaries

The harness must remain:

- desktop test/development only,
- local regtest only,
- explicitly opt-in for process startup,
- localhost-only,
- free of public endpoints,
- free of production source-set networking,
- free of BDK wallet material,
- free of production storage paths,
- free of signing and broadcasting by Skald or BDK wallets,
- free of mainnet.

Runtime regtest addresses or block identifiers may be produced by the temporary `bitcoind` harness. They must not be committed as fixtures, written to docs, written to build history, or exposed through result `toString()` output.

## Relationship To UTXO Scan Validation

This harness does not by itself enable production BDK UTXO observation. It creates the safe local Electrum-compatible backend path. The BDK UTXO validation boundary includes a desktop-test-only BDK Electrum scan adapter that uses the harness when `electrs` is available.

With local `electrs` configured through `SKALD_ELECTRS`, the combined opt-in UTXO scan command observes a funded runtime regtest UTXO through BDK and applies Skald's receive-address used-state policy. If `electrs` is absent, the command still reports unavailable instead of using public infrastructure.

The sanitized BDK scan result now maps into the Skald-owned backend observation/UTXO state boundary documented in [`BACKEND_OBSERVATION_STATE.md`](BACKEND_OBSERVATION_STATE.md). That boundary remains common-domain model state only and does not add production Electrum support, UTXO persistence, app UTXO display, signing, broadcasting, public endpoints, or mainnet.

The production backend adapter and endpoint normalization boundary is documented in [`PRODUCTION_BACKEND_ADAPTER_BOUNDARY.md`](PRODUCTION_BACKEND_ADAPTER_BOUNDARY.md). It is the disabled production-facing interface future runtime sync work must target; this local harness remains desktop-test-only.

## Next Step

Recommended next branch:

```text
phase2-prep-backend-settings-unified-address-ui
```

That branch should migrate the backend settings UI toward the unified address field if needed, using the existing endpoint parser. It must not add production Electrum defaults, production sync, signing, broadcasting, secure storage, public endpoint defaults, or mainnet.
