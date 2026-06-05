# Skald Vault

Skald Vault is a sovereign multi-rail Bitcoin wallet application for advanced users who keep their own keys, choose their own infrastructure, and need explicit custody, privacy, fee, and recovery boundaries.

Current status: initial Kotlin Multiplatform / Compose Multiplatform scaffold with Phase 1 descriptor-native on-chain domain modeling started. This repository does not yet implement real wallet functionality. Do not use it with real funds.

## Targets

- Android APK
- Linux desktop app
- Linux `.deb` package

## Build Commands

The intended Gradle tasks are:

```bash
./gradlew build
./gradlew :composeApp:assembleDebug
./gradlew :composeApp:run
./gradlew :composeApp:packageDeb
```

Linux packaging requires a JDK that includes `jpackage`. If Gradle selects an Android Studio JBR without `jpackage`, run:

```bash
JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64 ./gradlew :composeApp:packageDeb
```

If the Gradle wrapper has not been generated in a fresh checkout, use a local Gradle installation to run:

```bash
gradle wrapper --gradle-version 8.11.1
```

## Security Status

- No real Bitcoin keys, seeds, descriptors, transactions, signatures, Lightning payments, Cashu proofs, Payjoin payloads, Nostr private key parsing, or wallet credentials are implemented.
- Visible balances are static demo/testnet placeholders.
- Mainnet operations are unavailable in the scaffold.
- Development networks are regtest, signet, testnet, and testnet4.
- A seed phrase alone cannot restore every rail; Recovery Center modeling is first-class even in the scaffold.
- Descriptor wallet profiles, backend profiles, UTXO views, coin-control drafts, and PSBT workflows are non-operational placeholder models only.

## No Managed Infrastructure

Skald Vault is client software. It does not operate wallet infrastructure. Users must configure their own nodes, mints, LSPs, relays, Payjoin directories, and backends when those features are implemented.

The scaffold includes no Skald-operated defaults for:

- Bitcoin Core, Electrum, or Esplora backends
- Lightning nodes or LSPs
- Cashu mints
- Nostr relays
- Payjoin coordinators or directories
- Analytics, notification relays, or fee-taking transaction services

## Architecture Notes

- `composeApp/src/commonMain` holds shared domain models and the shared Compose UI.
- `composeApp/src/androidMain` holds the Android launcher.
- `composeApp/src/desktopMain` holds the Linux desktop launcher.
- Domain packages now separate core rails/network state, descriptor-native on-chain models, backend configuration models, strict coin-control models, disabled PSBT workflow models, recovery state, quote modeling, privacy warnings, and Lightning/Cashu/Nostr placeholders.
- `DemoPortfolioRepository` is static design-preview data only. It is deliberately named as demo state and must not be treated as live wallet data.
- `DemoOnChainRepository` and `DemoRecoveryRepository` drive Phase 1 UI scaffolding with sentinel placeholders such as `DESCRIPTOR_NOT_CREATED`, `BACKEND_NOT_CONFIGURED`, and `PSBT_NOT_CREATED`.
