# Recovery and Privacy Sync Status

## Status

Skald Vault now surfaces disabled production sync, backend observation, receive-address policy, encrypted-vault readiness, secure-storage, secure metadata, and observation-persistence blockers in Recovery Center and the Privacy Analyzer.

This is a status integration only. It does not enable production wallet sync, production backend clients, production observation persistence, app receive UI, wallet activation, descriptor persistence, address index persistence, UTXO persistence, secure storage, signing, broadcasting, Nostr parsing, Lightning, Cashu, Payjoin, public endpoint defaults, Skald-operated infrastructure, or mainnet.

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

The next focused pass should complete KDF calibration planning or design still-disabled executable-provider/KAT scaffolding. The disabled provider boundary is documented in [`ENCRYPTED_LOCAL_VAULT_CRYPTO_PROVIDER_BOUNDARY.md`](ENCRYPTED_LOCAL_VAULT_CRYPTO_PROVIDER_BOUNDARY.md) and does not enable crypto or storage. Isolated libsodium/KMP native-packaging comparison remains a separate replacement-stack option if needed. Do not enable production sync, plaintext observation storage, backend clients, public endpoints, signing, broadcasting, Nostr parsing, or mainnet as part of that work.
