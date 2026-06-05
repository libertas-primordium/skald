# Skald Vault

Skald Vault is a sovereign multi-rail Bitcoin wallet application for advanced users who keep their own keys, choose their own infrastructure, and need explicit custody, privacy, fee, and recovery boundaries.

Current status: initial Kotlin Multiplatform / Compose Multiplatform scaffold with Phase 1 descriptor-native on-chain domain modeling, non-secret Bitcoin backend profile settings, non-secret descriptor wallet profile metadata, non-operational coin-control/PSBT draft planning, and a fail-closed secure-storage abstraction started. This repository does not yet implement real wallet functionality. Do not use it with real funds.

## Targets

- Android APK
- Linux desktop app
- Linux `.deb` package

## Build Commands

The intended Gradle tasks are:

```bash
./gradlew build
./gradlew :composeApp:allTests
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
- Descriptor wallet profiles are non-secret metadata only. The app can save planned profile records, but it does not store descriptor text, xpubs, xprvs, private keys, seeds, nsecs, PSBTs, transaction data, or wallet funds.
- Coin-control and PSBT draft planning uses demo UTXO placeholders only. No real UTXOs are scanned, no transaction is constructed, no PSBT is created, and signing/broadcasting remain disabled.
- Backend profiles, UTXO views, coin-control drafts, and PSBT workflows are non-operational placeholder models only.
- Bitcoin backend profiles are local non-secret configuration state only. The app does not store backend credentials, test connections, sync wallets, scan chains, derive addresses, sign, or broadcast.
- A secure-storage abstraction exists for future secret-bearing features, but all secret storage is disabled/not implemented. Secret writes, reads, metadata listing, and deletes fail closed.

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

- `composeApp/src/commonMain` holds shared domain models and the shared Compose UI. The UI is split into app shell/navigation, reusable Skald components, and focused screen files.
- `composeApp/src/androidMain` holds the Android launcher.
- `composeApp/src/desktopMain` holds the Linux desktop launcher.
- Domain packages now separate core rails/network state, descriptor-native on-chain models, backend configuration models, strict coin-control models, disabled PSBT workflow models, recovery state, quote modeling, privacy warnings, and Lightning/Cashu/Nostr placeholders.
- `settings` holds small repositories/codecs for user-editable, persistent, non-secret Bitcoin backend profiles, descriptor wallet profile metadata, and coin-control/PSBT draft metadata. Android uses SharedPreferences and desktop uses local config files; these stores only hold non-secret labels, enum/status values, development network choices, backend endpoint fields, trust classification, demo UTXO IDs, draft notes/amounts, acknowledgements, blockers, and disabled workflow states.
- `security` holds the secure-storage service boundary and disabled platform implementations. Android and Linux desktop currently report secure storage as unavailable and do not store seeds, private keys, Nostr nsecs, node credentials, Cashu material, or backup keys.
- `DemoPortfolioRepository` is static design-preview data only. It is deliberately named as demo state and must not be treated as live wallet data.
- `DemoOnChainRepository`, `DemoCoinControlRepository`, and `DemoRecoveryRepository` drive Phase 1 UI scaffolding with sentinel placeholders such as `DESCRIPTOR_NOT_CREATED`, `BACKEND_NOT_CONFIGURED`, `DEMO_UTXO_ID_*`, and `PSBT_NOT_CREATED`.
