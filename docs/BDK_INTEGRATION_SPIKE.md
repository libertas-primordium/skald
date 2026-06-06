# BDK Integration Packaging Spike

## Status

This spike adds pinned BDK Kotlin platform dependencies and a Skald-owned adapter probe. It proves that the current Android and Linux desktop source sets can compile and package with BDK present.

It does not enable wallet functionality. Skald Vault still does not create wallets, generate keys, parse descriptors, derive addresses, scan UTXOs, construct PSBTs, sign, broadcast, store secrets, use BDK persistence, connect to backends, or operate on mainnet.

## Pinned Dependencies

Pinned BDK version:

```text
2.3.1
```

Artifacts:

```text
org.bitcoindevkit:bdk-android:2.3.1
org.bitcoindevkit:bdk-jvm:2.3.1
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
- Address derivation.
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

## Next Step

The next implementation pass should add a local `bitcoind` regtest harness. That pass should still avoid production storage, mainnet, hidden backend defaults, and any persisted key material.
