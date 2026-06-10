# Secure Metadata Boundary

## Status

Skald Vault now has a Skald-owned secure metadata persistence boundary for future production observation and wallet-history storage.

This boundary is disabled and fail-closed. It does not implement an encrypted vault, production sync, production UTXO persistence, production address index persistence, wallet activation, descriptor persistence, signing, broadcasting, Nostr parsing, Lightning, Cashu, Payjoin, public endpoints, Skald-operated infrastructure, or mainnet.

The app-controlled encrypted local vault architecture required before this boundary can return success is documented in [`ENCRYPTED_LOCAL_VAULT_DESIGN.md`](ENCRYPTED_LOCAL_VAULT_DESIGN.md). The crypto/key-lifecycle decision record is [`ENCRYPTED_LOCAL_VAULT_CRYPTO_DECISION.md`](ENCRYPTED_LOCAL_VAULT_CRYPTO_DECISION.md). The code-level readiness policy is documented in [`ENCRYPTED_VAULT_READINESS_POLICY.md`](ENCRYPTED_VAULT_READINESS_POLICY.md). The dependency spike is documented in [`ENCRYPTED_LOCAL_VAULT_DEPENDENCY_SPIKE.md`](ENCRYPTED_LOCAL_VAULT_DEPENDENCY_SPIKE.md), the desktop/Android KAT status is documented in [`ENCRYPTED_LOCAL_VAULT_KAT_VALIDATION.md`](ENCRYPTED_LOCAL_VAULT_KAT_VALIDATION.md), the libsodium/Kotlin comparison is documented in [`ENCRYPTED_LOCAL_VAULT_LIBSODIUM_COMPARISON.md`](ENCRYPTED_LOCAL_VAULT_LIBSODIUM_COMPARISON.md), the Tink/Bouncy dependency review is documented in [`ENCRYPTED_LOCAL_VAULT_DEPENDENCY_REVIEW.md`](ENCRYPTED_LOCAL_VAULT_DEPENDENCY_REVIEW.md), the disabled provider boundary is documented in [`ENCRYPTED_LOCAL_VAULT_CRYPTO_PROVIDER_BOUNDARY.md`](ENCRYPTED_LOCAL_VAULT_CRYPTO_PROVIDER_BOUNDARY.md), the provider-level KAT contract is documented in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md), the vault container/manifest/storage contract is documented in [`ENCRYPTED_LOCAL_VAULT_CONTAINER_MANIFEST_STORAGE_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_CONTAINER_MANIFEST_STORAGE_CONTRACT.md), the test-only provider KAT harness is documented in [`ENCRYPTED_LOCAL_VAULT_TEST_PROVIDER_KAT_HARNESS.md`](ENCRYPTED_LOCAL_VAULT_TEST_PROVIDER_KAT_HARNESS.md), Argon2id calibration policy/probes are documented in [`ENCRYPTED_LOCAL_VAULT_ARGON2ID_CALIBRATION.md`](ENCRYPTED_LOCAL_VAULT_ARGON2ID_CALIBRATION.md), non-final candidate parameter tiers are documented in [`ENCRYPTED_LOCAL_VAULT_ARGON2ID_PARAMETER_POLICY.md`](ENCRYPTED_LOCAL_VAULT_ARGON2ID_PARAMETER_POLICY.md), manual Android calibration evidence capture is documented in [`ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md), Android compatibility/entropy policy is documented in [`ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md), and runtime randomness/provider checks are documented in [`ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md`](ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md). These documents and models do not implement storage; they define the threat model, target KDF/AEAD policy, key hierarchy, platform strategy, session lifecycle, migration/corruption requirements, Tor metadata implications, recovery implications, readiness blockers, dependency-probe status, provider-boundary requirements, provider-level KAT contract gates, model-only container/manifest/storage requirements, test-only provider harness evidence, calibration planning, candidate KDF parameter policy, Android evidence capture discipline, runtime randomness availability checks, and acceptance criteria.

The disabled provider-selection boundary is documented in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md). It treats this disabled metadata repository as a blocker for production provider selection and selects only the disabled provider.

## Source Location

Production-safe common models:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SecureMetadataStorage.kt
```

Integration points:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/domain/onchain/BitcoinWalletSyncService.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/domain/recovery/RecoverySyncStatusModels.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/domain/privacy/PrivacySyncStatusModels.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/App.kt
```

Tests:

```text
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/SecureMetadataBoundaryTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/BitcoinWalletSyncServiceTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/RecoveryPrivacySyncStatusTest.kt
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/ProductionBackendAdapterSourceGuardTest.kt
```

## Sensitive Metadata Classes

The boundary classifies these wallet metadata categories as sensitive:

- address index state,
- receive-address lifecycle state,
- observed address usage,
- observed UTXO state,
- wallet labels,
- UTXO labels,
- transaction notes,
- backend observation history,
- privacy-sensitive backend endpoint metadata,
- Tor routing policy and transport metadata,
- Nostr identity-linkage metadata,
- Privacy Analyzer metadata,
- Recovery metadata.

All of these categories require app-controlled encrypted metadata storage before production persistence can succeed.

## Storage Policy

The primary future storage model is an app-controlled encrypted local vault.

OS keyrings are not treated as primary wallet metadata storage. They may later wrap keys only after explicit design review. Existing non-secret settings storage remains for general backend profile configuration and non-operational metadata only. It is not a wallet observation, address index, UTXO, label, transaction note, or wallet-history store.

The future container/manifest/storage contract is defined in [`ENCRYPTED_LOCAL_VAULT_CONTAINER_MANIFEST_STORAGE_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_CONTAINER_MANIFEST_STORAGE_CONTRACT.md). That contract now includes still-disabled in-memory container and manifest parser/writer building blocks plus local manifest-relative stale-record decision logic for caller-supplied byte arrays and fixed non-secret fixtures only. It also defines model-only platform storage boundary, platform storage-root, safe path-construction, symlink/traversal, permission/ownership, durability-capability, durability fail-closed, warning-only durability rejection, atomic write strategy, crash-recovery, interruption-test, and storage failure contracts, plus a still-disabled storage namespace/path policy that validates safe identifiers and deterministic relative segments without constructing paths. A still-disabled logical storage layout plan composes validated safe segments into deterministic rootless relative segment lists for future container, manifest, storage-index, record, temp, quarantine, and recovery artifacts. Unsupported, unknown, unreviewed, insufficient, unsafe, or failed durability blocks encrypted vault persistence; warning-only encrypted vault writes and user-consent durability overrides are not approved for v1. A shared-test in-memory storage atomicity/crash simulator now exercises the future write/recovery state machine with failure injection over byte arrays only. It does not add file I/O, vault persistence, manifest file/storage read/write, storage index read/write, storage success path, actual path construction, absolute path construction, path joining, directory creation, platform root selection, symlink checks, permission checks, durability probes, warning-only persistence, temp-file/journal/rename/fsync implementation, platform durability proof, secure metadata success path, global freshness, or an anti-rollback anchor.

The disabled capability reports:

- encrypted vault unavailable,
- metadata persistence disabled,
- no metadata listing,
- no metadata writes,
- no metadata reads,
- no metadata deletes.

The disabled repository rejects all sensitive metadata operations. It does not write files, use SharedPreferences, use desktop config files, call OS keyrings, use BDK persistence, serialize real observations, or return success for production wallet metadata.

## Sync Integration

The disabled production sync facade now receives a `SecureMetadataPersistenceCapability`.

Preflight remains blocked when secure metadata persistence is unavailable. The blockers distinguish:

- secure secret storage unavailable,
- secure metadata persistence unavailable,
- observation persistence unavailable,
- address index persistence unavailable.

Future production sync must target Skald-owned `BackendObservationSummary` and then persist any sensitive observation/address/UTXO metadata only through an approved encrypted metadata repository.

## Recovery And Privacy Integration

Recovery Center now surfaces:

- secure metadata vault unavailable,
- production observation history not persisted,
- address index state not persisted,
- UTXO state, labels, outpoints, and transaction notes not persisted,
- test-only BDK regtest validation does not create recoverable production wallet state.

Privacy Analyzer now surfaces:

- secure metadata storage unavailable,
- Privacy Analyzer state/history not persisted,
- public-backend and identity-linkage observations remain runtime/test-only until encrypted metadata storage exists.

These are status-only surfaces. They do not persist observations, display real UTXOs, query backends, derive production addresses, parse Nostr keys, sign, broadcast, or enable mainnet.

## Relationship To Secure Storage

`SecureSecretStorage` covers secret payloads such as seeds, private keys, Nostr private-key material, Lightning credentials, Cashu proof material, backend credentials, backup keys, and metadata encryption keys.

`SecureWalletMetadataRepository` covers sensitive wallet metadata that may not be secret key material but can reveal wallet behavior, balances, privacy state, labels, endpoints, address reuse, and recovery history.

Both boundaries are disabled. Future metadata persistence depends on both an encrypted metadata store and the key material required to protect it.

## Explicit Non-Capabilities

This boundary does not enable:

- encrypted vault storage,
- plaintext metadata persistence,
- production observation persistence,
- production UTXO persistence,
- production address index persistence,
- production sync,
- production backend clients,
- production BDK sync,
- app receive address generation,
- app UTXO display,
- descriptor persistence,
- wallet activation,
- secure secret storage,
- signing,
- broadcasting,
- Nostr, Lightning, Cashu, or Payjoin behavior,
- public backend defaults,
- Skald-operated infrastructure,
- mainnet.

## Testing

Tests verify that:

- every sensitive metadata kind requires encrypted metadata storage,
- the disabled repository rejects writes, reads, listing, and deletes,
- disabled repository operations never return success,
- payload display and `toString()` are redacted,
- mainnet metadata persistence cannot succeed,
- sync preflight includes secure metadata, observation, and address-index persistence blockers,
- Recovery status includes secure metadata vault and UTXO-state persistence blockers,
- Privacy status includes secure metadata storage unavailable,
- non-secret settings storage does not add a secure metadata store key,
- secure metadata and vault readiness production source remain BDK/client/process/persistence free.

## Next Step

The next focused pass should review runtime provider/primitive/randomness checks for supported Android and Linux paths or design a still-disabled production-provider skeleton with no storage before any production sync or observation persistence is enabled. IonSpin KMP libsodium packaging/KAT mapping and Lazysodium/JNA conflict strategy work remain separate replacement-stack probes if needed. Do not select a non-disabled provider, add plaintext observation storage, production backend clients, address persistence, signing, broadcasting, public endpoints, or mainnet as part of that work.
