# Skald Vault

Skald Vault is a sovereign multi-rail Bitcoin wallet application for advanced users who keep their own keys, choose their own infrastructure, and need explicit custody, privacy, fee, and recovery boundaries.

Current status: initial Kotlin Multiplatform / Compose Multiplatform scaffold with Phase 1 descriptor-native on-chain domain modeling, non-secret Bitcoin backend profile settings, simulated backend configuration validation, non-secret descriptor wallet profile metadata, non-operational coin-control/PSBT draft planning, a fail-closed secure-storage abstraction, a pinned BDK adapter packaging spike, a local `bitcoind` regtest harness, a resolved BDK JVM/Linux binding decision, a desktop-test-only BDK regtest wallet validation boundary, a desktop-test-only BDK regtest/signet address derivation validation boundary, and receive-address state/reuse policy modeling. This repository does not yet implement real wallet functionality. Do not use it with real funds.

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

The local regtest smoke harness is opt-in and requires local Bitcoin Core or Bitcoin Knots-compatible `bitcoind` and `bitcoin-cli` binaries:

```bash
SKALD_RUN_REGTEST_INTEGRATION=1 ./gradlew :composeApp:desktopTest --tests '*Regtest*'
```

The seed-backed BDK regtest wallet validation boundary is also opt-in and desktop-test-only:

```bash
SKALD_RUN_BDK_REGTEST_WALLET_VALIDATION=1 ./gradlew :composeApp:desktopTest --tests '*Bdk*' --rerun-tasks
```

The test-only BDK regtest/signet address derivation validation boundary is opt-in and desktop-test-only:

```bash
SKALD_RUN_BDK_REGTEST_ADDRESS_DERIVATION=1 ./gradlew :composeApp:desktopTest --tests '*AddressDerivation*' --rerun-tasks
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
- Descriptor/key-management implementation is still absent. The future integration design is documented in [`docs/DESCRIPTOR_KEY_MANAGEMENT_DESIGN.md`](docs/DESCRIPTOR_KEY_MANAGEMENT_DESIGN.md).
- A BDK dependency/adapter packaging spike exists with pinned `org.bitcoindevkit:bdk-android:2.3.0` and `org.bitcoindevkit:bdk-jvm:2.3.0` platform dependencies behind Skald-owned adapter models. It is documented in [`docs/BDK_INTEGRATION_SPIKE.md`](docs/BDK_INTEGRATION_SPIKE.md). It does not enable production wallet creation, key generation, descriptors, app receive addresses, backend sync, PSBTs, signing, broadcasting, persistence, or mainnet.
- The BDK JVM/Linux binding decision is documented in [`docs/BDK_JVM_LINUX_BINDING_DECISION.md`](docs/BDK_JVM_LINUX_BINDING_DECISION.md). The accepted `2.3.0` JVM artifact contains a Linux x86_64 native binding; the previously pinned `2.3.1` artifact was rejected for Linux desktop wallet validation.
- A desktop-test-only seed-backed BDK regtest wallet validation boundary exists and is documented in [`docs/BDK_REGTEST_WALLET_VALIDATION.md`](docs/BDK_REGTEST_WALLET_VALIDATION.md). It completes on the current Linux desktop target through an opt-in, redacted, test-only path and does not expose or persist wallet material.
- A desktop-test-only BDK regtest/signet address derivation validation boundary exists and is documented in [`docs/BDK_REGTEST_ADDRESS_DERIVATION.md`](docs/BDK_REGTEST_ADDRESS_DERIVATION.md). It derives runtime-generated test addresses only under explicit opt-in and does not add app receive UI, address index persistence, backend sync, signing, broadcasting, production wallet storage, or mainnet.
- Receive-address state and reuse-prevention policy modeling exists and is documented in [`docs/RECEIVE_ADDRESS_POLICY.md`](docs/RECEIVE_ADDRESS_POLICY.md). It distinguishes displayed/reserved addresses from backend-observed used addresses, but production receive address generation, BDK-derived app addresses, address index persistence, backend observation, and real receive UI remain disabled.
- A local `bitcoind` regtest harness exists for desktop development tests only. It is documented in [`docs/REGTEST_HARNESS.md`](docs/REGTEST_HARNESS.md). It uses temporary regtest state and does not connect the app UI to a backend, create a Skald or BDK wallet, persist wallet databases, sync app wallets, sign, broadcast, or enable mainnet.
- Coin-control and PSBT draft planning uses demo UTXO placeholders only. No real UTXOs are scanned, no transaction is constructed, no PSBT is created, and signing/broadcasting remain disabled.
- Backend profiles, UTXO views, coin-control drafts, and PSBT workflows are non-operational placeholder models only.
- Bitcoin backend profiles are local non-secret configuration state only. The app does not store backend credentials, run real backend connection tests, sync wallets, scan chains, derive addresses, sign, or broadcast.
- The backend connection-test harness is simulated validation only. It checks profile metadata and planned future test steps, but it does not perform DNS, socket, RPC, Electrum, Esplora, HTTP, Tor/proxy, chain-sync, fee-estimation, wallet-query, or broadcast network calls.
- A secure-storage abstraction exists for future secret-bearing features, but all secret storage is disabled/not implemented. Secret writes, reads, metadata listing, and deletes fail closed. The future secure-storage design is documented in [`docs/SECURE_STORAGE_DESIGN.md`](docs/SECURE_STORAGE_DESIGN.md).

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
- `docs/SECURE_STORAGE_DESIGN.md` defines the future Android/Linux secure-storage design gates. It is a design document only; it does not enable real secret persistence.
- `docs/DESCRIPTOR_KEY_MANAGEMENT_DESIGN.md` defines the future descriptor parser, wallet, key-management, PSBT, recovery, and protocol-library integration gates. It is a design document only; it does not enable descriptor parsing, key management, address derivation, or wallet operations.
- `docs/BDK_INTEGRATION_SPIKE.md` records the first BDK packaging spike. BDK is present only as platform dependencies behind a Skald-owned adapter probe; common UI/domain/settings models still do not expose BDK types.
- `docs/REGTEST_HARNESS.md` records the local `bitcoind` regtest harness. The harness is opt-in desktop test infrastructure and does not add production backend networking or app wallet behavior.
- `docs/BDK_JVM_LINUX_BINDING_DECISION.md` records the BDK JVM/Linux native binding decision and the accepted `2.3.0` pin.
- `docs/BDK_REGTEST_WALLET_VALIDATION.md` records the desktop-test-only seed-backed BDK regtest wallet validation boundary.
- `docs/BDK_REGTEST_ADDRESS_DERIVATION.md` records the desktop-test-only BDK regtest/signet address derivation validation boundary. The app still has no production receive-address flow or address index persistence.
- `docs/RECEIVE_ADDRESS_POLICY.md` records the production-safe receive-address state model and reuse-prevention policy. It is a policy/model document only; it does not enable production address derivation, address persistence, backend sync, or receive UI.
- `docs/PHASE1_COMPLETION_AUDIT.md` records the Phase 1 hardening/completion audit. It is an audit artifact only; it does not enable wallet functionality.
- `DemoPortfolioRepository` is static design-preview data only. It is deliberately named as demo state and must not be treated as live wallet data.
- `DemoOnChainRepository`, `DemoCoinControlRepository`, `FakeBitcoinBackendConnectionTester`, and `DemoRecoveryRepository` drive Phase 1 UI scaffolding with sentinel placeholders such as `DESCRIPTOR_NOT_CREATED`, `BACKEND_NOT_CONFIGURED`, `CONNECTION_TEST_NOT_REAL_NETWORK`, `DEMO_UTXO_ID_*`, and `PSBT_NOT_CREATED`.
