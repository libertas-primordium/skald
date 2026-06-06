# Local Electrum Regtest Harness

## Status

Skald Vault has a desktop-test-only local Electrum-compatible regtest indexer harness boundary.

The harness is development/test infrastructure only. It does not enable production Electrum backend support, production wallet sync, production UTXO scanning, app receive UI, address-index persistence, BDK persistence, signing, broadcasting, secure storage, or mainnet.

The current local environment used for this pass has `bitcoind` and `bitcoin-cli` available, but no `electrs` binary was found on `PATH`. The opt-in harness therefore reports an unavailable result until `electrs` is installed or provided through `SKALD_ELECTRS`.

## Purpose

The previous BDK regtest UTXO scan validation boundary found that BDK `2.3.0` exposes Electrum, Esplora, and compact-filter scan clients, but no direct Bitcoin Core RPC scan client in the resolved JVM artifact. A local Electrum-compatible indexer is the preferred next local indexed backend because it can be started against the temporary `bitcoind` regtest harness without using public infrastructure.

This harness adds the local process and readiness boundary needed before BDK UTXO observation can safely attempt a local Electrum path.

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
4. Starts `electrs` with regtest, the temporary node datadir, local RPC address, temporary indexer database directory, and a localhost Electrum endpoint.
5. Waits for localhost Electrum TCP readiness.
6. Generates one regtest block through a temporary `bitcoind` mining wallet.
7. Stops `electrs`.
8. Stops `bitcoind`.
9. Deletes temporary state where practical.

The temporary mining wallet belongs to the test harness only. It is not a Skald wallet, not a BDK wallet, not app storage, and not production wallet material.

## BDK API Evidence

Local artifact inspection for pinned `bdk-jvm:2.3.0` shows an `ElectrumClient` constructor accepting string parameters and methods for `serverFeatures`, `fullScan`, and `sync`. The harness does not call these BDK APIs. It only provides the local indexed backend process boundary that a separate BDK Electrum adapter spike can use later.

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

This harness does not by itself complete BDK UTXO observation. It creates the safe local Electrum-compatible backend path. The BDK UTXO validation boundary remains blocked until a focused pass wires BDK's Electrum scan API to this local harness and proves that wallet updates can be applied through Skald-owned redacted result models.

## Next Step

Recommended next branch:

```text
phase2-prep-bdk-electrum-scan-adapter
```

That branch should install or point to a local `electrs` binary, run the opt-in harness, and then attempt the smallest safe BDK Electrum full-scan path. It must not add production Electrum defaults, production sync, signing, broadcasting, secure storage, or mainnet.
