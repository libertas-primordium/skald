# BDK Integration Packaging Spike

## Status

This spike adds pinned BDK Kotlin platform dependencies and a Skald-owned adapter probe. It proves that the current Android and Linux desktop source sets can compile and package with BDK present.

It does not enable wallet functionality. Skald Vault still does not create production wallets, generate production keys, parse app descriptors, derive app receive addresses, scan UTXOs, construct PSBTs, sign, broadcast, store secrets, use production BDK persistence, connect to backends, or operate on mainnet.

## Pinned Dependencies

Pinned BDK version:

```text
2.3.0
```

Artifacts:

```text
org.bitcoindevkit:bdk-android:2.3.0
org.bitcoindevkit:bdk-jvm:2.3.0
```

The versions are declared in `gradle/libs.versions.toml`. The artifacts are intentionally placed in platform source sets only:

- `androidMain` uses `bdk-android`.
- `desktopMain` uses `bdk-jvm`.
- `commonMain` does not depend on BDK.

## Adapter Boundary

Skald-owned common models live under:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/domain/onchain/bdk/
```

The common source set exposes only Skald-owned types:

- `BdkAdapterVersion`
- `BdkAdapterPlatform`
- `BdkAdapterProbeState`
- `BdkAdapterCapability`
- `BdkAdapterBlockingIssue`
- `BdkAdapterNetworkProbe`
- `BdkAdapterError`
- `BdkAdapterProbeResult`
- `BdkAdapterProbe`

Direct `org.bitcoindevkit` imports are allowed only in platform adapter implementation files for this pass:

- Android actual probe.
- Desktop JVM actual probe.

BDK types must not enter Compose UI state, settings codecs, persisted settings, high-level domain policy, recovery models, descriptor metadata models, coin-control models, or public Skald service interfaces.

## Probe Behavior

The probe performs a minimal platform binding check by reading BDK development-network enum names for:

- Regtest
- Signet
- Testnet
- Testnet4

The probe deliberately does not request or map BDK mainnet. Skald's `NetworkEnvironment.MainnetDisabled` remains unavailable and `allowsMainnetOperations` remains false for every network.

The probe returns Skald-owned status and error types. If a platform binding error occurs, the result uses bounded non-sensitive diagnostics and does not surface raw BDK exception text.

## Explicit Non-Capabilities

This spike leaves these capabilities disabled:

- Wallet creation.
- Key generation.
- Descriptor parsing or validation.
- Production/app address derivation.
- Wallet sync.
- Backend networking.
- BDK persistence or wallet databases.
- PSBT construction, import, export, or finalization.
- Signing.
- Broadcasting.
- Mainnet operation.
- Secure secret storage.

## UI Surface

Settings includes a compact BDK adapter status card. It labels the state as a packaging probe, not wallet readiness. The copy states that no wallet, descriptor, backend, storage, signing, broadcast, or mainnet behavior is enabled.

## Tests

The spike adds boundary tests for:

- pinned version metadata,
- packaging-only capabilities,
- wallet operation disablement,
- development-only network probe modeling,
- default blockers,
- Skald-owned safe error translation,
- desktop JVM BDK probe execution,
- direct BDK import confinement to platform adapter files.

Android BDK linkage is verified by compile, build, APK assembly, and packaging checks rather than an instrumented test.

## Regtest Harness Layer

The next rollout layer after this packaging spike is documented in [`REGTEST_HARNESS.md`](REGTEST_HARNESS.md).

That harness is opt-in desktop test/development infrastructure. It starts a local `bitcoind` regtest node with a temporary datadir, checks readiness with `bitcoin-cli`, reads regtest chain metadata, generates a regtest block through a temporary `bitcoind` test wallet, shuts down, and cleans up temporary state where practical.

The regtest harness does not create a Skald wallet, create a BDK wallet, enable BDK persistence, connect the app UI to a backend, sync app wallets, sign, broadcast, or enable mainnet.

## JVM/Linux Binding Decision

The Linux desktop JVM native-binding decision is documented in [`BDK_JVM_LINUX_BINDING_DECISION.md`](BDK_JVM_LINUX_BINDING_DECISION.md).

Skald pins BDK `2.3.0` because `bdk-jvm:2.3.0` contains `linux-x86-64/libbdkffi.so`. The previously pinned `2.3.1` JVM artifact did not contain a Linux native binding and blocked opt-in BDK wallet API validation on Linux. BDK `3.0.0` was inspected and contains a Linux native binding, but it was deferred because `2.3.0` resolves the blocker without a major-version migration.

## Seed-Backed Regtest Validation Layer

The follow-up seed-backed regtest wallet validation boundary is documented in [`BDK_REGTEST_WALLET_VALIDATION.md`](BDK_REGTEST_WALLET_VALIDATION.md).

That boundary remains desktop-test-only, regtest-only, opt-in, redacted, and disconnected from production app wallet flows. With the accepted `2.3.0` pin, the opt-in Linux desktop validation completes through the Skald-owned redacted test boundary. It still does not enable production wallet creation, app address derivation, backend sync, BDK persistence, signing, broadcasting, or mainnet.

## Address Derivation Validation Layer

The test-only regtest/signet address derivation validation boundary is documented in [`BDK_REGTEST_ADDRESS_DERIVATION.md`](BDK_REGTEST_ADDRESS_DERIVATION.md).

That boundary remains desktop-test-only, opt-in, redacted, offline, and disconnected from production app wallet flows. It proves deterministic receive-address derivation from the same runtime-only test seed on regtest and signet. It still does not enable app receive UI, production address derivation, address index persistence, backend sync, UTXO scan, signing, broadcasting, production storage, or mainnet.

## UTXO Scan Validation Boundary

The test-only regtest UTXO scan validation boundary is documented in [`BDK_REGTEST_UTXO_SCAN_VALIDATION.md`](BDK_REGTEST_UTXO_SCAN_VALIDATION.md).

That boundary now includes a desktop-test-only BDK Electrum scan adapter. Without the local Electrum opt-in it reports a safe local-indexer-required state. With both opt-ins and a local `electrs` binary, it uses only a localhost regtest Electrum endpoint from the test harness. The combined path now observes a funded runtime regtest UTXO through BDK and applies Skald-owned receive-address used-state policy. No production wallet sync, backend client, UTXO scan, signing, broadcasting, or mainnet behavior is enabled.

## Local Electrum Regtest Harness Boundary

The desktop-test-only local Electrum-compatible regtest indexer harness is documented in [`LOCAL_ELECTRUM_REGTEST_HARNESS.md`](LOCAL_ELECTRUM_REGTEST_HARNESS.md).

That harness discovers a local `electrs` binary through `SKALD_ELECTRS` or `PATH`, starts it only against the temporary local `bitcoind` regtest harness when explicitly opted in, waits for localhost Electrum readiness, and cleans up temporary state. For the Electrum harness path, temporary `bitcoind` exposes only a localhost-bound P2P listener so electrs can index local regtest blocks. It does not add production Electrum support, public backend defaults, app wallet sync, BDK wallet material, signing, broadcasting, or mainnet.

The current local environment validates with local `electrs` `v0.11.1` supplied through `SKALD_ELECTRS`.

## Next Step

The next implementation pass should design the production-safe backend observation state boundary before any production sync work. Do not enable production storage, mainnet, hidden backend defaults, production wallet sync, signing, broadcasting, or persisted production key material as part of that work.
