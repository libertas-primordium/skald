# BDK Regtest Wallet Validation

## Status

Skald Vault now has a desktop-test-only boundary for seed-backed BDK regtest wallet creation and recovery validation.

The boundary is not production wallet functionality. It is not wired into the app UI, settings repositories, descriptor profile metadata, Recovery Center state, backend settings, coin-control drafts, or any persisted app model.

On the current Linux desktop target, the opt-in validation completes with pinned `org.bitcoindevkit:bdk-jvm:2.3.0`. The JVM/Linux binding decision is documented in [`BDK_JVM_LINUX_BINDING_DECISION.md`](BDK_JVM_LINUX_BINDING_DECISION.md). The previously pinned `2.3.1` JVM artifact was rejected because it lacked a Linux native binding.

The follow-up address derivation validation boundary is documented in [`BDK_REGTEST_ADDRESS_DERIVATION.md`](BDK_REGTEST_ADDRESS_DERIVATION.md). It remains separate, desktop-test-only, opt-in, and disconnected from production receive flows.

## Source Location

The test-only validation code lives under:

```text
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/bdk/
```

Direct BDK wallet API imports are allowed only in this desktop test validation package and the existing platform adapter probe files. Common UI, common domain policy, settings codecs, persisted models, and production services remain free of BDK public types.

## What The Boundary Attempts

When explicitly enabled, the validation attempts to:

1. create runtime-only entropy,
2. create a runtime-only mnemonic from that entropy,
3. create a runtime-only BDK descriptor secret on regtest,
4. create test-only BIP86 descriptors,
5. use BDK's in-memory persister,
6. create a test-only BDK wallet,
7. recreate the wallet identity from the same in-memory entropy,
8. compare internal identity values,
9. return only Skald-owned redacted status.

The result model exposes no mnemonic words, seed bytes, private descriptors, xprvs, private keys, WIFs, addresses, PSBTs, transaction hex, or wallet database contents.

## Linux Binding Resolution

The Linux desktop environment completes the BDK wallet validation with pinned BDK `2.3.0` because the resolved `bdk-jvm` artifact provides:

```text
linux-x86-64/libbdkffi.so
```

The test boundary still handles native-binding failures as a Skald-owned blocked result for future artifact regressions, but the current accepted dependency no longer hits that blocker on Linux.

## How To Run

Normal BDK tests do not create runtime wallet material:

```bash
./gradlew :composeApp:desktopTest --tests '*Bdk*'
```

The opt-in validation path is:

```bash
SKALD_RUN_BDK_REGTEST_WALLET_VALIDATION=1 ./gradlew :composeApp:desktopTest --tests '*Bdk*' --rerun-tasks
```

In sandboxed environments that need a writable Gradle cache and IPv4 preference:

```bash
GRADLE_USER_HOME=/tmp/skald-gradle-home SKALD_RUN_BDK_REGTEST_WALLET_VALIDATION=1 ./gradlew --no-daemon -Djava.net.preferIPv4Stack=true :composeApp:desktopTest --tests '*Bdk*' --rerun-tasks
```

On the current Linux target with BDK `2.3.0`, this command completes the seed-backed regtest wallet validation through the test-only boundary.

## Explicit Non-Capabilities

This validation boundary does not enable:

- production wallet creation,
- production BDK persistence,
- secure storage,
- descriptor storage,
- production address derivation in app flows,
- backend sync,
- UTXO scanning,
- fee estimation,
- transaction construction,
- PSBT import/export/finalization,
- signing,
- broadcasting,
- Nostr key parsing,
- Lightning,
- Cashu,
- Payjoin,
- mainnet.

## Safety Rules

The validation must remain:

- desktop test/development only,
- regtest only,
- explicitly opt-in for runtime seed material,
- redacted in result models and assertion messages,
- free of committed secret fixtures,
- free of production storage paths,
- free of backend networking,
- free of production app address derivation, signing, and broadcasting.

## UTXO Scan Follow-Up

The later regtest UTXO scan validation boundary is documented in [`BDK_REGTEST_UTXO_SCAN_VALIDATION.md`](BDK_REGTEST_UTXO_SCAN_VALIDATION.md).

That boundary now includes a desktop-test-only BDK Electrum scan adapter because BDK `2.3.0` does not expose a direct Bitcoin Core RPC scan client in the resolved JVM artifact. The local Electrum-compatible regtest indexer harness boundary is documented in [`LOCAL_ELECTRUM_REGTEST_HARNESS.md`](LOCAL_ELECTRUM_REGTEST_HARNESS.md). With local `electrs` configured through `SKALD_ELECTRS`, the combined opt-in path now observes a funded runtime regtest UTXO through BDK and applies Skald-owned receive-address used-state policy.

## Next Step

The backend observation state boundary is documented in [`BACKEND_OBSERVATION_STATE.md`](BACKEND_OBSERVATION_STATE.md).

The production backend adapter and endpoint normalization boundary is documented in [`PRODUCTION_BACKEND_ADAPTER_BOUNDARY.md`](PRODUCTION_BACKEND_ADAPTER_BOUNDARY.md).

The next pass should add a disabled production sync service facade over the adapter boundary. It must not wire receive addresses into production app UI without explicit approval, enable production BDK persistence, sync wallets in production flows, sign, broadcast, store secrets, or enable mainnet.
