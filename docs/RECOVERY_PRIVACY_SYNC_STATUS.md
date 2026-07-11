# Recovery and Privacy Sync Status

## Status

Skald Vault now surfaces disabled production sync, backend observation, receive-address policy, encrypted-vault readiness, secure-storage, secure metadata, and observation-persistence blockers in Recovery Center and the Privacy Analyzer.

This is a status integration only. It does not enable production wallet sync, production backend clients, production observation persistence, app receive UI, wallet activation, descriptor persistence, address index persistence, UTXO persistence, secure storage, signing, broadcasting, Nostr parsing, Lightning, Cashu, Payjoin, public endpoint defaults, Skald-operated infrastructure, or mainnet.

The disabled provider-selection boundary documented in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md) reinforces these blockers: only the disabled provider is selected, and disabled secure storage/metadata persistence block any production provider use for wallet recovery or privacy metadata.

Manual Android Argon2id calibration evidence capture is documented in [`ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md), Android compatibility/entropy policy is documented in [`ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md), and runtime randomness/provider checks are documented in [`ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md`](ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md). They can organize parameter evidence and model runtime provider/randomness gates, but they do not make recovery storage, privacy metadata persistence, provider selection, or production sync available.

The encrypted vault storage readiness decision is documented in [`ENCRYPTED_LOCAL_VAULT_STORAGE_READINESS_DECISION.md`](ENCRYPTED_LOCAL_VAULT_STORAGE_READINESS_DECISION.md), the container-format v1 decision is documented in [`ENCRYPTED_LOCAL_VAULT_CONTAINER_FORMAT_V1_DECISION.md`](ENCRYPTED_LOCAL_VAULT_CONTAINER_FORMAT_V1_DECISION.md), the storage path/session lifecycle decision is documented in [`ENCRYPTED_LOCAL_VAULT_STORAGE_PATH_SESSION_LIFECYCLE_DECISION.md`](ENCRYPTED_LOCAL_VAULT_STORAGE_PATH_SESSION_LIFECYCLE_DECISION.md), the migration/corruption policy decision is documented in [`ENCRYPTED_LOCAL_VAULT_MIGRATION_CORRUPTION_POLICY_DECISION.md`](ENCRYPTED_LOCAL_VAULT_MIGRATION_CORRUPTION_POLICY_DECISION.md), the parser/writer admission gate is documented in [`ENCRYPTED_LOCAL_VAULT_PARSER_WRITER_ADMISSION_GATE.md`](ENCRYPTED_LOCAL_VAULT_PARSER_WRITER_ADMISSION_GATE.md), and the parser/writer test-vector admission is documented in [`ENCRYPTED_LOCAL_VAULT_PARSER_WRITER_TEST_VECTOR_ADMISSION.md`](ENCRYPTED_LOCAL_VAULT_PARSER_WRITER_TEST_VECTOR_ADMISSION.md). They admit only later separate design/implementation branches; no vectors, parser/writer implementation, serialization/parsing, vault bytes, migration execution, corruption detection/repair, backup creation, rollback, atomic replace, directory/file I/O, lock/unlock/session handling, KDF/AEAD/encryption/decryption, key/nonce generation, or Tink keyset creation/persistence is added, and observation metadata, address index state, UTXO state, wallet history, Recovery status, and Privacy Analyzer persistence remain blocked until encrypted vault storage and secure metadata success paths are separately approved.

The parser/writer implementation scaffold is documented in [`ENCRYPTED_LOCAL_VAULT_PARSER_WRITER_IMPLEMENTATION_SCAFFOLD.md`](ENCRYPTED_LOCAL_VAULT_PARSER_WRITER_IMPLEMENTATION_SCAFFOLD.md). It does not change Recovery/Privacy persistence: disabled parser/writer scaffolds reject without bytes, no test-source synthetic bytes were created, and observation metadata, address index state, UTXO state, wallet history, Recovery status, and Privacy Analyzer persistence remain blocked with no production vector bytes, serialization/parsing, file I/O, crypto execution, storage success, provider selection, UI, endpoint, or mainnet behavior added.

## Source Location

Recovery status models:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/domain/recovery/RecoverySyncStatusModels.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/ui/screens/RecoveryScreen.kt
```

Privacy status models:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/domain/privacy/PrivacySyncStatusModels.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/ui/screens/OverviewScreen.kt
```

Shared sync request/status source:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/domain/onchain/BitcoinWalletSyncService.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/EncryptedVaultReadiness.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/VaultCryptoProviderSelection.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/ui/components/BitcoinWalletSyncStatusUiModel.kt
```

Tests:

```text
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/RecoveryPrivacySyncStatusTest.kt
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/ProductionBackendAdapterSourceGuardTest.kt
```

## Recovery Center Surface

Recovery Center includes a read-only `Sync and observation recovery` card.

It shows:

- production sync disabled,
- encrypted vault readiness unavailable,
- secure metadata vault unavailable,
- observation and UTXO persistence deferred until encrypted vault storage,
- secure storage unavailable,
- descriptor wallet profiles remain metadata-only/non-operational,
- production address index state is not persisted,
- production UTXO state is not persisted,
- desktop regtest BDK validation is test-only and does not create recoverable production wallet state.

The card keeps the core recovery warning intact: a seed phrase alone does not restore every rail. Current test-only regtest validation does not store a production seed, descriptor, address index, UTXO set, backend metadata, wallet history, Lightning state, Cashu proof material, Nostr key material, or backup encryption key.

## Privacy Analyzer Surface

The Overview Privacy Analyzer option now includes a read-only `Backend observation privacy` card before the existing placeholder risk list.

It can show policy findings for:

- production sync disabled,
- no production backend query attempted,
- encrypted vault readiness unavailable,
- secure metadata storage unavailable,
- observation persistence deferred,
- public backend wallet-query privacy risk,
- backend query linkage,
- onion/Tor endpoint labeling,
- Tor transport not implemented,
- displayed/reserved address does not equal used address,
- backend-observed address usage,
- address reuse warning after observed use,
- identity-linked/Nostr-source UTXO placeholder risk,
- coin-control-required spend readiness.

This is not cluster analysis. It does not parse Nostr keys, inspect real transactions, persist UTXOs, derive production addresses, or query a backend.

## Persistence Deferral

Production observation persistence is intentionally deferred.

Observed addresses, labels, UTXOs, outpoints, transaction notes, backend metadata, wallet history, address index state, and privacy-analysis state are sensitive wallet metadata. They can reveal wallet structure, timing, balances, backend choices, address reuse, identity-linked funds, and future spending plans.

Skald must not persist that metadata in plaintext. Production observation persistence must wait for:

- the app-controlled encrypted local vault described in [`ENCRYPTED_LOCAL_VAULT_DESIGN.md`](ENCRYPTED_LOCAL_VAULT_DESIGN.md),
- the code-level readiness policy documented in [`ENCRYPTED_VAULT_READINESS_POLICY.md`](ENCRYPTED_VAULT_READINESS_POLICY.md) to report implementation gates satisfied,
- the disabled secure metadata boundary documented in [`SECURE_METADATA_BOUNDARY.md`](SECURE_METADATA_BOUNDARY.md) to be replaced by an approved encrypted implementation.

Current desktop-test BDK observations remain runtime-only test state. No production observation repository exists. The current `DisabledSecureWalletMetadataRepository` rejects all sensitive metadata reads, writes, listing, and deletes.

## Relationship To Existing Boundaries

Recovery/Privacy status consumes Skald-owned models only:

- disabled production sync service result,
- encrypted vault readiness state,
- backend observation summary shape,
- receive-address policy state,
- secure-storage capability,
- secure metadata persistence capability,
- descriptor wallet metadata settings.

It does not consume BDK types, crypto provider types, Electrum/Esplora/Bitcoin Core clients, process handles, sockets, HTTP clients, or platform-native APIs.

Future production sync must still pass through:

```text
Endpoint policy
        ↓
Disabled-until-approved sync preflight
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
Encrypted observation persistence
        ↓
Recovery and Privacy status
        ↓
Future coin-control review
```

## Explicit Non-Capabilities

This status integration does not enable:

- production sync,
- production backend clients,
- production connection testing,
- production observation persistence,
- production UTXO display,
- secure metadata persistence,
- encrypted vault implementation,
- app receive address generation,
- production receive UI,
- address index persistence,
- descriptor persistence,
- wallet activation,
- secure storage,
- transaction construction,
- PSBT import/export/finalization,
- signing,
- broadcasting,
- Nostr key parsing or Nostr-derived wallets,
- Lightning,
- Cashu,
- Payjoin,
- public endpoint defaults,
- Skald-operated infrastructure,
- mainnet.

## Testing

Tests verify that:

- Recovery status reports production sync disabled,
- Recovery status reports observation persistence deferred until encrypted vault storage,
- Recovery status reports encrypted vault readiness unavailable,
- Recovery status reports secure metadata vault and UTXO-state persistence unavailable,
- Recovery status reports secure storage unavailable,
- Recovery status does not treat test-only BDK validation as production recovery state,
- Privacy status reports public backend warnings,
- Privacy status reports secure metadata storage unavailable,
- Privacy status reports encrypted vault readiness unavailable,
- Privacy status reports onion/Tor labeling without claiming Tor transport is implemented,
- Privacy status distinguishes displayed addresses from backend-observed used addresses,
- Privacy status reports address reuse warnings after observed use,
- Privacy status reports identity-linked/Nostr placeholder warnings without parsing Nostr keys,
- Recovery/Privacy status files remain BDK/client/process/persistence free.

## Next Step

The next focused pass should review runtime provider/primitive/randomness checks for supported Android and Linux paths or design a disabled production-provider skeleton with no storage. The disabled provider boundary is documented in [`ENCRYPTED_LOCAL_VAULT_CRYPTO_PROVIDER_BOUNDARY.md`](ENCRYPTED_LOCAL_VAULT_CRYPTO_PROVIDER_BOUNDARY.md), the provider-level KAT contract is documented in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md), the test-only provider KAT harness is documented in [`ENCRYPTED_LOCAL_VAULT_TEST_PROVIDER_KAT_HARNESS.md`](ENCRYPTED_LOCAL_VAULT_TEST_PROVIDER_KAT_HARNESS.md), Argon2id calibration policy/probes are documented in [`ENCRYPTED_LOCAL_VAULT_ARGON2ID_CALIBRATION.md`](ENCRYPTED_LOCAL_VAULT_ARGON2ID_CALIBRATION.md), non-final candidate parameter tiers are documented in [`ENCRYPTED_LOCAL_VAULT_ARGON2ID_PARAMETER_POLICY.md`](ENCRYPTED_LOCAL_VAULT_ARGON2ID_PARAMETER_POLICY.md), manual Android calibration capture is documented in [`ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md), Android compatibility/entropy policy is documented in [`ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md), runtime randomness/provider checks are documented in [`ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md`](ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md), and neither path enables production crypto or storage. Isolated libsodium/KMP native-packaging comparison remains a separate replacement-stack option if needed. Do not enable production sync, plaintext observation storage, backend clients, public endpoints, signing, broadcasting, Nostr parsing, or mainnet as part of that work.
