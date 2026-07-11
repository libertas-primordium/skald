# Production Sync Service Boundary

## Status

Skald Vault now has a Skald-owned production wallet sync service facade over the backend adapter, endpoint policy, receive-address policy, secure-storage capability, encrypted vault readiness capability, secure metadata persistence capability, and backend observation models.

The Nodes screen now includes a minimal read-only production sync preflight/status card backed by this facade. Recovery Center and the Privacy Analyzer also consume the same disabled result through status-only models documented in [`RECOVERY_PRIVACY_SYNC_STATUS.md`](RECOVERY_PRIVACY_SYNC_STATUS.md). These surfaces show blockers and warnings for selected backend/profile metadata, secure-storage state, secure metadata persistence, receive-address policy, and observation persistence, but they expose no working sync action and perform no connection test.

The facade and UI surface are disabled and fail-closed. They do not enable production BDK sync, Bitcoin Core RPC, Electrum, Esplora, backend connection testing, UTXO scanning, receive UI, address or UTXO persistence, descriptor persistence, secure storage, provider-selectable KDF execution, transaction construction, PSBT handling, signing, broadcasting, public backend defaults, Skald-operated infrastructure, or mainnet.

Manual Android Argon2id calibration evidence capture is documented in [`ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md), Android compatibility/entropy policy is documented in [`ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md), runtime randomness/provider checks are documented in [`ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md`](ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md), and the future vault container/manifest/storage contract is documented in [`ENCRYPTED_LOCAL_VAULT_CONTAINER_MANIFEST_STORAGE_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_CONTAINER_MANIFEST_STORAGE_CONTRACT.md). They do not change sync readiness: runtime provider/randomness checks remain required before vault creation, secure storage and secure metadata persistence remain disabled, manifest/storage conflict handling remains unimplemented, and production sync remains unavailable.

The encrypted vault storage readiness decision is documented in [`ENCRYPTED_LOCAL_VAULT_STORAGE_READINESS_DECISION.md`](ENCRYPTED_LOCAL_VAULT_STORAGE_READINESS_DECISION.md), the container-format v1 decision is documented in [`ENCRYPTED_LOCAL_VAULT_CONTAINER_FORMAT_V1_DECISION.md`](ENCRYPTED_LOCAL_VAULT_CONTAINER_FORMAT_V1_DECISION.md), the storage path/session lifecycle decision is documented in [`ENCRYPTED_LOCAL_VAULT_STORAGE_PATH_SESSION_LIFECYCLE_DECISION.md`](ENCRYPTED_LOCAL_VAULT_STORAGE_PATH_SESSION_LIFECYCLE_DECISION.md), the migration/corruption policy decision is documented in [`ENCRYPTED_LOCAL_VAULT_MIGRATION_CORRUPTION_POLICY_DECISION.md`](ENCRYPTED_LOCAL_VAULT_MIGRATION_CORRUPTION_POLICY_DECISION.md), and the parser/writer admission gate is documented in [`ENCRYPTED_LOCAL_VAULT_PARSER_WRITER_ADMISSION_GATE.md`](ENCRYPTED_LOCAL_VAULT_PARSER_WRITER_ADMISSION_GATE.md). They do not change this facade: production sync remains blocked, no production backend client is added, no parser/writer implementation, serialization/parsing, vault bytes, migration execution, corruption detection/repair, backup creation, rollback, atomic replace, directory/file I/O, lock/unlock/session handling, KDF/AEAD/encryption/decryption, key/nonce generation, or Tink keyset creation/persistence is added, no observation/address-index/UTXO/wallet-history persistence is added, and future production sync still requires a separate branch after encrypted vault storage and secure metadata success paths are approved.

## Source Location

Production-safe common models:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/domain/onchain/BitcoinWalletSyncService.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/domain/recovery/RecoverySyncStatusModels.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/domain/privacy/PrivacySyncStatusModels.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/ui/components/BitcoinWalletSyncStatusUiModel.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/EncryptedVaultReadiness.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SecureMetadataStorage.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/ui/screens/NodesScreen.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/ui/screens/RecoveryScreen.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/ui/screens/OverviewScreen.kt
```

Tests:

```text
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/BitcoinWalletSyncServiceTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/BitcoinWalletSyncStatusUiModelTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/EncryptedVaultReadinessPolicyTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/RecoveryPrivacySyncStatusTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/SecureMetadataBoundaryTest.kt
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/ProductionBackendAdapterSourceGuardTest.kt
```

## Facade Model

The service boundary defines Skald-owned concepts for:

- sync service identity,
- sync request,
- sync preflight,
- sync result,
- sync status,
- sync blockers,
- sync warnings,
- sync capabilities,
- redacted Skald-owned sync errors.

The request and result types depend only on Skald-owned models:

- Bitcoin backend profile metadata,
- endpoint validation results,
- disabled production backend adapter boundary,
- backend observation summaries,
- receive-address wallet and address state,
- secure-storage capability state,
- encrypted vault readiness state,
- secure metadata persistence capability state.

The facade does not expose BDK, Electrum, Esplora, Bitcoin Core RPC, HTTP, socket, process, wallet database, or platform-native client types.

## Nodes Status Surface

The Nodes screen renders a small `Production sync preflight` status block after the simulated backend validation section.

The surface displays:

- selected backend profile metadata,
- selected descriptor wallet metadata if present,
- requested development network,
- sync blockers,
- sync warnings,
- a locked action label explaining that sync is not implemented.

Expected blockers include:

- production sync disabled,
- backend adapter disabled,
- no backend configured,
- invalid endpoint metadata,
- no operational wallet,
- secure storage unavailable,
- credential references unavailable,
- secure metadata persistence unavailable,
- observation persistence unavailable,
- address index persistence unavailable,
- mainnet disabled when applicable.

Expected warnings include:

- no network connection attempted,
- public backend privacy leakage,
- backend query linkage,
- onion/Tor labeling preserved,
- Tor transport not implemented,
- no Skald-managed infrastructure.

The status surface consumes only Skald-owned result models. It does not call BDK, create backend clients, start processes, open sockets, persist observations, derive addresses, or expose a working sync button.

## Recovery And Privacy Status Surfaces

Recovery Center renders a read-only `Sync and observation recovery` card. It states that production sync is disabled, secure storage is unavailable, secure metadata vault storage is unavailable, descriptor wallet profiles remain metadata-only, address index and UTXO state are not persisted, and test-only regtest BDK validation does not create recoverable production wallet state.

The Overview Privacy Analyzer option renders a read-only `Backend observation privacy` card. It shows policy-only findings for public backend privacy risk, backend query linkage, onion/Tor labeling, Tor transport not implemented, displayed-versus-used address state, address reuse warnings after observed use, identity-linked UTXO placeholders, and coin-control-required spend readiness.

Both surfaces are status-only and share the same disabled sync result used by Nodes. They do not add working sync, connection testing, production observation persistence, receive UI, Nostr parsing, signing, broadcasting, or mainnet.

## Disabled Implementation

`DisabledBitcoinWalletSyncService` is the only sync service implementation in this pass.

It returns blocked results for:

- production sync disabled,
- no backend profile,
- invalid endpoint metadata,
- credential material rejected,
- mainnet disabled,
- no operational wallet context,
- encrypted vault unavailable,
- secure storage unavailable,
- disabled production backend adapter,
- backend credentials unavailable,
- secure metadata persistence unavailable,
- observation persistence unavailable,
- address index persistence unavailable,
- receive-address policy blockers.

It may call the disabled production backend adapter boundary to obtain a sanitized `BackendObservationSummary` shape, but that adapter is also fail-closed and does not create a backend client or scan a wallet.

## Preflight Policy

`BitcoinWalletSyncPolicy` evaluates expected production sync prerequisites without performing sync:

- selected backend profile exists,
- endpoint validation has no credential or userinfo failures,
- network is a development network,
- mainnet is rejected,
- wallet context is operational before future sync,
- receive-address policy can represent the address state,
- encrypted vault readiness remains unavailable,
- secure storage is unavailable for current secret-bearing flows,
- secure metadata persistence is unavailable for observation history and address index state,
- production backend adapter remains disabled,
- observation persistence is unavailable,
- public backend and onion/Tor warnings are preserved,
- credential references are metadata only.

Expected policy failures return Skald-owned blockers and warnings rather than throwing.

## Relationship To Existing Boundaries

Future production sync must pass through this order:

```text
Endpoint policy
        ↓
Sync preflight policy
        ↓
Production backend adapter
        ↓
BackendObservationSummary
        ↓
Receive-address policy
        ↓
Encrypted vault readiness policy
        ↓
Secure metadata persistence boundary
        ↓
Future encrypted observation persistence
        ↓
Future coin-control review
```

This pass defines only the service boundary and blocked preflight state. It does not implement the future steps that require real backend clients, persistence, secure storage, wallet activation, signing approval, or broadcast approval.

Production observation persistence remains explicitly deferred until app-controlled encrypted vault storage exists. Observed addresses, labels, UTXOs, transaction notes, backend metadata, wallet history, address index state, recovery metadata, Privacy Analyzer metadata, and identity-linkage metadata are sensitive metadata and are not persisted by the current facade. The future manifest/storage contract requires conflict handling before sync or import behavior is enabled. The disabled secure metadata repository is documented in [`SECURE_METADATA_BOUNDARY.md`](SECURE_METADATA_BOUNDARY.md).

The vault architecture and key-lifecycle plan required before any production observation persistence is documented in [`ENCRYPTED_LOCAL_VAULT_DESIGN.md`](ENCRYPTED_LOCAL_VAULT_DESIGN.md). The crypto/key-lifecycle decision record is [`ENCRYPTED_LOCAL_VAULT_CRYPTO_DECISION.md`](ENCRYPTED_LOCAL_VAULT_CRYPTO_DECISION.md). The code-level readiness policy is documented in [`ENCRYPTED_VAULT_READINESS_POLICY.md`](ENCRYPTED_VAULT_READINESS_POLICY.md). The dependency spike is documented in [`ENCRYPTED_LOCAL_VAULT_DEPENDENCY_SPIKE.md`](ENCRYPTED_LOCAL_VAULT_DEPENDENCY_SPIKE.md), with desktop and Android runtime public-vector validation documented in [`ENCRYPTED_LOCAL_VAULT_KAT_VALIDATION.md`](ENCRYPTED_LOCAL_VAULT_KAT_VALIDATION.md), libsodium/Kotlin comparison documented in [`ENCRYPTED_LOCAL_VAULT_LIBSODIUM_COMPARISON.md`](ENCRYPTED_LOCAL_VAULT_LIBSODIUM_COMPARISON.md), candidate-level Tink/Bouncy dependency review documented in [`ENCRYPTED_LOCAL_VAULT_DEPENDENCY_REVIEW.md`](ENCRYPTED_LOCAL_VAULT_DEPENDENCY_REVIEW.md), the disabled provider boundary documented in [`ENCRYPTED_LOCAL_VAULT_CRYPTO_PROVIDER_BOUNDARY.md`](ENCRYPTED_LOCAL_VAULT_CRYPTO_PROVIDER_BOUNDARY.md), the disabled provider-selection boundary documented in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md), the provider-level KAT contract documented in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md), the test-only provider KAT harness documented in [`ENCRYPTED_LOCAL_VAULT_TEST_PROVIDER_KAT_HARNESS.md`](ENCRYPTED_LOCAL_VAULT_TEST_PROVIDER_KAT_HARNESS.md), Argon2id calibration policy/probes documented in [`ENCRYPTED_LOCAL_VAULT_ARGON2ID_CALIBRATION.md`](ENCRYPTED_LOCAL_VAULT_ARGON2ID_CALIBRATION.md), and non-final candidate parameter tiers documented in [`ENCRYPTED_LOCAL_VAULT_ARGON2ID_PARAMETER_POLICY.md`](ENCRYPTED_LOCAL_VAULT_ARGON2ID_PARAMETER_POLICY.md). Those documents and models keep the app-controlled encrypted local vault as the primary storage model, treat OS keyrings only as optional future key-wrapping helpers, probe pinned Tink/Bouncy Castle candidate APIs for Argon2id plus XChaCha20-Poly1305, model a provider-level KAT contract, exercise that contract through a test-only harness without production approval, model calibration candidates and non-final parameter tiers without selecting production parameters, reject Lazysodium Java/Android for this branch after Android duplicate-JNA packaging failure, defer IonSpin KMP libsodium, select only the disabled provider, and keep production sync blocked until executable production-provider work, production provider-level KAT execution, provider-selection gates, encrypted vault readiness, secure secret storage, and secure metadata persistence are all approved.

## Explicit Non-Capabilities

This boundary does not enable:

- production wallet sync,
- production backend networking,
- production Bitcoin Core RPC,
- production Electrum,
- production Esplora,
- production BDK scan APIs,
- production UTXO persistence,
- production address index persistence,
- app receive address generation,
- app receive UI,
- descriptor persistence,
- secure storage,
- encrypted vault implementation,
- secure metadata persistence,
- transaction construction,
- PSBT import/export/finalization,
- signing,
- broadcasting,
- Nostr, Lightning, Cashu, or Payjoin behavior,
- public endpoint defaults,
- Skald-operated infrastructure,
- mainnet.

## Testing

Tests cover:

- default fail-closed sync result,
- no backend profile blocker,
- invalid endpoint blocker,
- mainnet rejection,
- non-operational wallet blocker,
- secure-storage unavailable blocker,
- encrypted vault unavailable blocker,
- secure metadata persistence unavailable blocker,
- public backend privacy warning,
- onion/Tor labeling warning without Tor transport claims,
- credential reference blocked while secure storage is disabled,
- disabled backend adapter blocking sync,
- observation summary shape without persisted or real observations,
- secure metadata boundary tests for disabled reads, writes, listing, deletes, and redaction,
- disabled networking, sync, persistence, signing, broadcasting, mainnet, and Skald infrastructure flags.
- sync status presentation labels for blockers and warnings,
- public backend and onion/Tor warning display,
- invalid endpoint display without userinfo exposure,
- no working sync action in the presentation model,
- UI/status source files remaining BDK/client/process free.
- Recovery/Privacy status files remaining BDK/client/process/persistence free,
- Recovery status making encrypted-vault persistence deferral explicit,
- Privacy status distinguishing displayed addresses from backend-observed used addresses.

The source guard includes the sync facade, status UI files, and secure metadata boundary and asserts that production boundary/status files remain BDK/client/process/persistence free.

## Next Step

The next focused pass should review runtime provider/primitive/randomness checks for supported Android and Linux paths or design a still-disabled production-provider skeleton with no storage. IonSpin KMP libsodium packaging/KAT mapping and Lazysodium/JNA conflict strategy work remain separate replacement-stack probes if needed. Production backend clients and observation persistence should remain deferred until encrypted vault readiness, provider-selection gates, secure storage, secure metadata persistence, recovery integration, and backend trust boundaries are reviewed.
