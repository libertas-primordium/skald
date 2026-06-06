# BDK JVM Linux Binding Decision

## Status

Resolved for the current Linux desktop development target.

Skald Vault now pins BDK Kotlin artifacts to `2.3.0` because the Maven-published `bdk-jvm:2.3.0` artifact includes a Linux x86_64 native binding, while `bdk-jvm:2.3.1` does not. This is a dependency/platform compatibility decision only. It does not enable production wallet creation, descriptor import, address derivation, backend sync, transaction construction, signing, broadcasting, secure storage, BDK persistence, or mainnet operation.

## Decision

Use:

```text
org.bitcoindevkit:bdk-android:2.3.0
org.bitcoindevkit:bdk-jvm:2.3.0
```

The version remains declared through `gradle/libs.versions.toml`, and BDK remains platform-scoped:

- `androidMain` depends on `bdk-android`.
- `desktopMain` depends on `bdk-jvm`.
- `commonMain` remains free of BDK dependencies and BDK public types.

## Why 2.3.0

`bdk-jvm:2.3.0` is the closest Maven-published candidate to the previous `2.3.1` pin that restores Linux desktop native binding support without requiring a major-version migration.

The previous `2.3.1` artifact compiled but blocked BDK wallet API validation on Linux because its JVM JAR contained only a Darwin ARM native library. The desktop packaging probe could still read JVM enum metadata, but wallet API calls require the native binding.

BDK `3.0.0` was inspected and also contains a Linux x86_64 native binding, but it is a major version and was not selected because `2.3.0` resolved the blocker with a smaller compatibility change.

## Compatibility Matrix

| Candidate | Artifacts inspected | JVM compile result | Linux native binding | Desktop BDK probe | Opt-in seed wallet validation | Android status | Decision |
| --- | --- | --- | --- | --- | --- | --- | --- |
| `2.3.1` | `bdk-jvm`, `bdk-android` | Previously compiled | Missing from JVM JAR; only `darwin-aarch64/libbdkffi.dylib` found | Packaging probe linked | Blocked by native binding | AAR contained Android ABI libraries | Rejected for Linux desktop wallet validation |
| `2.3.0` | `bdk-jvm`, `bdk-android` | Passed | Present: `linux-x86-64/libbdkffi.so` | Passed | Passed with opt-in desktop test | AAR contains Android ABI libraries; Android APK and Linux package checks passed | Accepted |
| `3.0.0` | `bdk-jvm`, `bdk-android` archive inventory only | Not compile-tested in Skald | Present: `linux-x86-64/libbdkffi.so` | Not run | Not run | AAR contains Android ABI libraries by archive inventory | Deferred because it is a major migration |

## Artifact Inventory Findings

The accepted JVM artifact contains these native entries:

```text
darwin-aarch64/libbdkffi.dylib
darwin-x86-64/libbdkffi.dylib
linux-x86-64/libbdkffi.so
win32-x86-64/bdkffi.dll
```

The accepted Android artifact contains Android JNI libraries for:

```text
jni/arm64-v8a/libbdkffi.so
jni/armeabi-v7a/libbdkffi.so
jni/x86_64/libbdkffi.so
```

The desktop test suite includes an artifact inventory test that inspects the resolved pinned JVM JAR on the desktop test classpath and asserts that `linux-x86-64/libbdkffi.so` is present. That test does not import BDK classes and does not create wallet material.

## Test-Only Validation Outcome

With `bdk-jvm:2.3.0`, the opt-in desktop validation can create and recover a test-only regtest BDK wallet identity from runtime-only entropy through the Skald-owned redacted validation boundary.

This validation remains:

- desktop-test-only,
- explicitly opt-in,
- regtest-only,
- in-memory through BDK's in-memory persister,
- redacted,
- disconnected from app UI wallet flows,
- free of production storage,
- free of backend networking,
- free of address derivation, signing, and broadcasting.

## Additional Harness Fix

During validation, the test-only recovery harness was corrected to copy both runtime entropy buffers before passing either one into BDK. The previous harness wiped the original entropy before the recovery copy was made, which caused a false identity mismatch after the native-binding issue was resolved.

The corrected flow wipes:

- the original runtime entropy buffer,
- the created-wallet entropy copy,
- the recovered-wallet entropy copy,
- the mnemonic entropy copy used inside each BDK call.

No mnemonic words, seed bytes, private descriptors, xprvs, private keys, addresses, PSBTs, transaction hex, or wallet database contents are logged, serialized, or stored in app production paths.

## Non-Capabilities

This decision does not enable:

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

## Next Step

The next BDK rollout layer can proceed to test-only regtest/signet address derivation behind Skald-owned adapter APIs. It must remain non-production, keep BDK types out of common UI/settings/persisted models, avoid production storage, avoid backend networking unless explicitly scoped as a local test harness, and keep mainnet disabled.
