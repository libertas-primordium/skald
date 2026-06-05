# Phase 1 Completion Audit

## Status

Phase 1 is substantially complete as a non-operational architecture and safety-boundary foundation.

It is not complete as wallet functionality. Skald Vault still cannot create wallets, derive addresses, scan chains, construct transactions, create PSBTs, sign, broadcast, store secrets, or operate on mainnet.

This audit finds Phase 1 ready for user review and design discussion before any protocol-library integration. It does not recommend moving directly into implementation until the open design questions below are discussed.

## Scope audited

- Kotlin Multiplatform / Compose Multiplatform app scaffold.
- Android APK target.
- Linux desktop target.
- Linux `.deb` package target.
- Primary and overflow UI navigation.
- Shared UI components and screen extraction.
- Domain models for rails, networks, on-chain wallet planning, recovery, privacy, quotes, Lightning, Cashu, and Nostr placeholders.
- Persistent non-secret Bitcoin backend profile settings.
- Fail-closed secure-storage abstraction.
- Disabled Android and Linux secure-storage implementations.
- Metadata-only descriptor wallet profile workflow.
- Non-operational coin-control and PSBT draft planner.
- Simulated Bitcoin backend connection-test harness.
- Secure-storage design document.
- Descriptor/key-management design document.
- README honesty and design links.
- BUILD_HISTORY project memory.
- Common tests.
- `.gitignore` local-memory, build-artifact, and sensitive-runtime exclusions.

## Phase 1 completed capabilities

- Buildable KMP/Compose application scaffold for Android and Linux desktop.
- Linux `.deb` packaging target.
- Black/orange Skald visual identity with rail navigation.
- Primary navigation: Overview, On-chain, Lightning, Cashu.
- Overflow menu navigation: Nostr, Recovery, Nodes, Settings.
- Android safe-area handling.
- Descriptor-native on-chain domain modeling.
- Persistent, user-editable, non-secret Bitcoin backend profiles for Bitcoin Core RPC, Electrum, and Esplora metadata.
- Explicit backend trust/privacy warnings and no Skald-operated backend defaults.
- Fail-closed secure-storage abstraction with disabled Android and Linux implementations.
- Non-secret descriptor wallet profile metadata workflow.
- Recovery Center integration for descriptor profile blockers and rail-specific recovery limitations.
- Non-operational coin-control and PSBT draft planner using demo placeholders only.
- Simulated backend configuration validation harness that performs no network I/O.
- Design documents for secure storage and descriptor/key-management integration.

## Explicit non-capabilities

Skald Vault still does not implement:

- Real wallet creation.
- Real keys, seed material, or mnemonic generation.
- Real descriptors or descriptor parsing.
- Extended public/private key handling.
- Address derivation.
- Backend networking or backend connection testing.
- Chain sync, UTXO scan, balance scan, or history scan.
- Fee estimation from a backend.
- Transaction construction.
- Real PSBT serialization, parsing, import, export, or finalization.
- Signing.
- Broadcasting.
- Real secure storage.
- Payjoin.
- Silent Payments.
- Nostr public/private key parsing or signing.
- Lightning wallet or connector behavior.
- Cashu mint, melt, seed, or proof behavior.
- Mainnet operation.

## Safety invariants verified

- Mainnet remains disabled: `NetworkEnvironment` exposes only development-selectable regtest, signet, testnet, and testnet4; `MainnetDisabled` is not selectable and no enum permits mainnet operations.
- Secret storage remains fail-closed: common, Android, and desktop implementations delegate to disabled storage and reject write/read/list/delete operations.
- No real secrets are accepted by current backend, descriptor, or coin-control forms.
- No real descriptor text, key material, addresses, transaction data, or PSBTs are serialized by the inspected settings codecs.
- Backend connection testing is simulated only and records `CONNECTION_TEST_NOT_REAL_NETWORK` findings.
- Demo UTXOs are explicitly non-real, non-spendable placeholders with `DEMO_UTXO_ID_*` and `DEMO_OUTPOINT_NOT_REAL_*` identifiers.
- Descriptor profiles are metadata-only and operational capabilities remain disabled.
- Coin-control drafts are metadata-only and cannot enable PSBT export/import, signing, broadcast, or execution.
- No Skald-managed infrastructure default endpoint is configured.
- README continues to warn that the app has no real wallet functionality and must not be used with real funds.

## Build and packaging status

Expected audit commands:

```bash
./gradlew :composeApp:allTests
./gradlew build
./gradlew :composeApp:assembleDebug
GRADLE_USER_HOME=/tmp/skald-gradle-home ANDROID_HOME=/home/spencer/Android/Sdk JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64 ./gradlew --no-daemon -Djava.net.preferIPv4Stack=true :composeApp:packageDeb
git diff --check
```

Audit result for this pass:

- `./gradlew :composeApp:allTests` passed.
- `./gradlew build` passed.
- `./gradlew :composeApp:assembleDebug` passed.
- `GRADLE_USER_HOME=/tmp/skald-gradle-home ANDROID_HOME=/home/spencer/Android/Sdk JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64 ./gradlew --no-daemon -Djava.net.preferIPv4Stack=true :composeApp:packageDeb` passed.
- `git diff --check` passed after this audit and BUILD_HISTORY update.
- Targeted Gradle-file scans found no forbidden Bitcoin, protocol, networking, encryption, or secure-storage dependencies.
- Targeted source scans found no obvious socket, HTTP, WebSocket, Ktor, OkHttp, or Java network API imports/calls.
- Targeted mainnet scans found no mainnet-enabled operation.
- Targeted Skald-managed infrastructure scans found only negative warning/documentation text.
- Targeted wallet-material scans found only existing negative PSBT-prefix assertions, not positive fixtures.

## Test coverage status

Existing common tests cover:

- `ArchitectureGuardTest`: demo state, mainnet disablement, strict coin-control policy, disabled PSBT signing/broadcast states, no default backend endpoints, public backend warnings, imported-key/Nostr recovery warnings, and placeholder service disablement.
- `BackendSettingsTest`: non-secret backend profile validation, save/read/select/delete repository behavior, credential omission, public backend warnings, and disabled legacy connection test placeholder.
- `SecureStorageBoundaryTest`: disabled capability, fail-closed secret operations, redacted payload display, secret metadata safety, backend credential omission, no secure-storage success results, and disabled UI status.
- `NavigationModelTest`: exact primary and overflow screen grouping with no missing or duplicated screens.
- `DescriptorWalletWorkflowTest`: metadata-only profile creation, mainnet rejection, acknowledgement requirements, no descriptor/key material, repository/codec round-trip, selection delete behavior, and disabled receive/spend/export/sign/broadcast capabilities.
- `CoinControlPsbtDraftWorkflowTest`: selected wallet requirement, demo UTXO requirements, non-real/non-spendable inputs, repository/codec round-trip, output metadata without address fields, amount validation, acknowledgements, PSBT construction blocker, no PSBT serialization, no transaction-like fixture, and profile-specific privacy warnings.
- `BitcoinBackendConnectionHarnessTest`: simulated Core/Electrum/Esplora validation, missing/invalid profile blockers, mainnet blocker, credential-like endpoint rejection, secure-storage credential blocker, no-network finding, future planned checks, public backend warning, and real-network-disabled capability.

Coverage gaps:

- No screenshot regression tests are configured.
- No Android emulator/device smoke test was run in this audit.
- No desktop GUI smoke test was run in this audit.
- Platform persistence is compile-checked and common-codec tested, but this audit did not run an Android or Linux restart persistence scenario.
- No real regtest harness exists yet.
- No protocol-library tests or official descriptor/address/PSBT vectors exist yet by design.

## Documentation status

- `README.md` is honest about the current scaffold and links to the secure-storage and descriptor/key-management design documents.
- `docs/SECURE_STORAGE_DESIGN.md` documents future Android/Linux secure-storage gates and states that real storage remains disabled.
- `docs/DESCRIPTOR_KEY_MANAGEMENT_DESIGN.md` documents future descriptor parser, wallet, key-management, and protocol-library gates and states that no implementation exists.
- This audit document records Phase 1 status, gaps, and pre-Phase-2 discussion topics.
- `BUILD_HISTORY.md` is append-only local project memory and records prior passes. It contains historical misplaced-entry corrections for descriptor/key-management and this audit because entries were inserted near repeated section headings; these are left intact per append-only rules.

## UI status

- UI has been extracted into focused screen/component files with `App.kt` acting as app shell and wiring.
- Black/orange Skald styling remains centralized in the theme and components.
- Android safe-area top padding remains in the app scaffold.
- Primary and overflow navigation groupings are test-covered.
- The On-chain locked-action cards use stacked labels/statuses, preserving the prior compact Android readability fix.
- Nodes exposes simulated backend validation without implying real network testing.
- Settings and Recovery expose disabled secure-storage status.
- No screenshot or device-level visual regression check was run in this audit.

## Data persistence status

Persisted non-secret data:

- Bitcoin backend profile metadata: profile ID, label, backend type, development network, endpoint host/port/path, TLS flag, trust model, and selected profile ID.
- Descriptor wallet profile metadata: profile ID, label, origin, development network, workflow/status enums, spend policy, backup requirement, acknowledgements, blockers, capabilities, and selected profile ID.
- Coin-control draft metadata: draft ID, wallet profile ID/label, selected demo UTXO IDs, recipient note, amount, acknowledgements, blocker/status enums, capability enums, and selected draft ID.

Not persisted:

- Secrets.
- Seed material or mnemonics.
- Private keys.
- Descriptor text.
- Extended public/private key material.
- Addresses.
- Real UTXOs or outpoints.
- PSBTs.
- Transactions or transaction hex.
- Backend credentials.
- Nostr identity private-key material.
- Lightning credentials.
- Cashu seed or proof material.

## Security boundary status

The secure-storage boundary exists and is fail-closed. `SecureSecretStorage` defines list/write/read/delete operations, `SecretPayload` has redacted display behavior, and the disabled implementations reject all secret operations.

Android reports disabled secure storage and does not use SharedPreferences for secrets. Linux desktop reports disabled secure storage and does not write a plaintext secret file.

The secure-storage design document defines acceptance gates before any real secret persistence can be enabled.

## No-managed-infrastructure verification

The inspected code and docs contain no default Skald-operated Bitcoin backend, Electrum server, Esplora server, Lightning node, LSP, Cashu mint, Nostr relay, Payjoin coordinator, analytics, notification relay, or fee-taking transaction service.

The scan for Skald-operated endpoint assumptions only found negative warning/documentation text and Nodes-screen no-managed-infrastructure warnings.

## Mainnet disablement verification

`NetworkEnvironment` keeps all entries with `allowsMainnetOperations = false`; only regtest, signet, testnet, and testnet4 are development-selectable. `MainnetDisabled` is not selectable.

Targeted scans found no source setting that enables mainnet operations. Tests assert that no network entry permits mainnet operations.

## Secret-material verification

Targeted scans covered docs, README, common source, and common tests for obvious real-looking wallet-material patterns. The only PSBT-prefix pattern hit is used in negative assertions that serialized drafts must not contain that prefix.

This is not a cryptographic proof that no sensitive string exists anywhere, but the inspected source, docs, tests, and build-history changes for Phase 1 use sentinel placeholders and negative statements rather than real wallet material.

## Networking disablement verification

Build files contain no networking client dependency. Targeted source scans found no obvious Java/Ktor/OkHttp/socket/WebSocket/HTTP imports or API calls.

The desktop non-secret settings storage uses `java.io.File`, which is local filesystem access, not networking. The simulated backend harness models planned checks and explicitly records that no socket, DNS, RPC, Electrum, or HTTP call occurred.

## Remaining Phase 1 gaps

- Phase 1 audit has not yet been reviewed by the user.
- No screenshot regression tests exist.
- No Android emulator/device smoke test was run in this audit.
- No desktop GUI smoke test was run in this audit.
- No platform restart persistence smoke test was run in this audit.
- No formal Bitcoin library decision exists.
- No deterministic regtest harness exists.
- No secure-storage implementation exists; only the disabled boundary and design doc exist.
- No descriptor parser or key-management implementation exists; only metadata workflows and design doc exist.
- No official descriptor/address/PSBT test-vector policy has been approved.
- `BUILD_HISTORY.md` contains historical misplaced-entry corrections left intact by append-only policy.

## Open design questions before implementation

- Bitcoin library choice: BDK or `bdk_wallet`, `rust-bitcoin`/Miniscript through FFI/JNI, bitcoinj, or another strategy.
- Kotlin Multiplatform protocol boundary: common Kotlin domain models versus platform-specific protocol implementations, JNI/FFI risk, and Android/Linux reproducibility impact.
- Testnet4 support in the chosen library, backends, and local harnesses.
- Deterministic regtest harness design: bitcoind regtest, fake backend versus real local backend, scripted dev commands, wallet scanning tests, and PSBT construction tests.
- Safe descriptor test-vector policy: avoiding real-looking private material, when to use official public test vectors, redaction rules, and preventing confusion between test vectors and real wallet material.
- Android secure-storage policy: Keystore strategy, StrongBox policy, user-authenticated keys, unlock model, backup exclusion, and migration.
- Linux secure-storage policy: libsecret/KWallet versus passphrase-encrypted vault, unlock model, file permissions, backup, and migration.
- Whether Nostr identity private-key import should be ephemeral by default.
- External signer and hardware signer strategy for Android and Linux.
- Whether the next implementation starts with watch-only descriptor import before any private-key wallet path.

## Recommended discussions before Phase 2

- Choose a Bitcoin protocol-library evaluation path before adding dependencies.
- Decide whether Phase 2 starts with watch-only descriptor import or with a regtest harness and protocol boundary first.
- Decide how testnet4 support will be verified or deferred.
- Decide safe public test-vector policy before descriptor/address/PSBT tests are introduced.
- Decide Android/Linux secure-storage dependency and unlock direction before any secret-bearing wallet work.
- Decide how hardware/external signer support should shape descriptor and PSBT boundaries.
- Decide Nostr identity-key policy before any Nostr-derived spend wallet implementation.

## Acceptance criteria for declaring Phase 1 complete

- [x] Buildable Android APK target.
- [x] Buildable Linux desktop target.
- [x] Buildable Linux `.deb` package target.
- [x] Black/orange Skald UI with rail navigation.
- [x] Non-secret backend profiles.
- [x] Fail-closed secure-storage abstraction.
- [x] Metadata-only descriptor wallet profile workflow.
- [x] Non-operational coin-control/PSBT draft planner.
- [x] Simulated backend connection harness.
- [x] Secure-storage design document.
- [x] Descriptor/key-management design document.
- [x] Phase 1 hardening audit document created.
- [ ] Phase 1 hardening audit reviewed by user.
- [ ] Unresolved design questions discussed.
- [ ] User explicitly approves moving into protocol-library integration.

## Recommended next branches

- `phase1-audit-fixes` for any user-requested documentation/test cleanup found during review.
- `phase2-library-selection-design-review` for dependency evaluation and KMP/protocol-boundary decisions.
- `phase2-regtest-harness-design` for local deterministic backend/test harness planning.
- `phase2-watch-only-descriptor-integration` only after protocol-library and test-vector policy decisions are approved.
