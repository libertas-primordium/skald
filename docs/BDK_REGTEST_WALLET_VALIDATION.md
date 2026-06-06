# BDK Regtest Wallet Validation

## Status

Skald Vault now has a desktop-test-only boundary for seed-backed BDK regtest wallet creation and recovery validation.

The boundary is not production wallet functionality. It is not wired into the app UI, settings repositories, descriptor profile metadata, Recovery Center state, backend settings, coin-control drafts, or any persisted app model.

On the current Linux desktop target, the opt-in validation is blocked by BDK JVM native binding availability for pinned `org.bitcoindevkit:bdk-jvm:2.3.1`. Local artifact inspection found the resolved JVM jar contains:

```text
darwin-aarch64/libbdkffi.dylib
```

and no Linux `.so` native binding. The validation therefore reports a Skald-owned blocked result before completing BDK wallet creation/recovery on Linux. It does not silently upgrade BDK or move secret-bearing code into production paths.

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

## Current Linux Blocker

The current Linux desktop environment cannot complete the BDK wallet validation with pinned BDK `2.3.1` because the resolved `bdk-jvm` artifact does not provide a Linux native library for BDK FFI wallet calls.

Normal BDK packaging/linkage tests still pass because the existing adapter probe only reads JVM enum metadata. Wallet APIs require the native binding and therefore report:

```text
BDK_REGTEST_WALLET_VALIDATION_NATIVE_BINDING_UNAVAILABLE
```

This is a dependency/runtime packaging blocker, not a reason to add production persistence, enable mainnet, use public infrastructure, or store wallet material.

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

On the current Linux target, this command passes by verifying the explicit native-binding blocked state and safety invariants. It does not prove completed seed-backed BDK wallet recovery until a Linux-compatible BDK JVM native binding is available.

## Explicit Non-Capabilities

This validation boundary does not enable:

- production wallet creation,
- production BDK persistence,
- secure storage,
- descriptor storage,
- address derivation in app flows,
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
- free of address derivation, signing, and broadcasting.

## Next Step

Before address derivation or seed-backed wallet work can proceed on Linux, the next pass should decide how to obtain a Linux-compatible BDK native binding without broadening production wallet behavior. Options to evaluate:

- confirm whether BDK `2.3.1` publishes a Linux JVM artifact/classifier elsewhere,
- evaluate BDK `3.0.0` packaging on Android and Linux,
- alter the source-set/native packaging strategy if BDK supports it,
- defer Linux JVM BDK wallet validation until the dependency strategy is resolved.
