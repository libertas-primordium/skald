# Encrypted Local Vault: Test-Only Provider Identity Reachability Proof

This document records the fifth slower test-only implementation step for the Skald Vault v1 provider identity chain: a commonTest-only inert test-only provider identity reachability proof.

The sixth slower test-only step summarizes all marker-chain capabilities as blocked in a commonTest-only inert capability matrix documented in [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_CAPABILITY_MATRIX.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_CAPABILITY_MATRIX.md).

The seventh slower test-only step defines commonTest-only KAT fixture metadata scope for this reachability chain in [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_KAT_FIXTURE_SCOPE.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_KAT_FIXTURE_SCOPE.md).

The eighth slower test-only step records one metadata-only KAT fixture catalog row for this reachability chain in [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_KAT_FIXTURE_CATALOG.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_KAT_FIXTURE_CATALOG.md).

The reachability proof exists only under `composeApp/src/commonTest`. It is built from the inert marker, inert inventory, inert profile, and inert validation report. It does not add markers, inventory entries, profiles, provider implementations, runtime hooks, or authorization surfaces.

Negative reachability proof passing is not production authorization, provider-selection authorization, registry/factory/dispatcher/executor authorization, crypto authorization, vault-persistence authorization, sync authorization, signing/broadcasting authorization, public-endpoint authorization, or mainnet authorization.

## Proof Inputs

- Safe ID checked: `skald-test-only-provider-identity-v1-deterministic-kat-inert-marker`
- Source-set placement: commonTest only.
- Marker count: one.
- Inventory count: one.
- Profile count: one.
- Validation report count: one.

The proof reads the existing commonTest-only marker, inventory, profile, and validation report. It confirms the safe ID matches and keeps all proof outputs redacted.

## Reachability Surfaces

Blocked surfaces are `ProviderSelection`, `ProviderRegistry`, `ProviderFactory`, `ProviderDispatcher`, `ExecutorTarget`, `ProviderKatExecutor`, `ProviderOperation`, `RuntimeRandomness`, `Kdf`, `Hkdf`, `Hmac`, `Aead`, `KeyGeneration`, `KeysetStorage`, `VaultCreation`, `VaultUnlock`, `VaultSession`, `VaultPersistence`, `SecureStorage`, `SecureMetadata`, `ProductionSync`, `BackendClient`, `BdkWalletState`, `SettingsCodec`, `UiSurface`, `Signing`, `Broadcasting`, `PublicEndpointDefault`, and `Mainnet`.

## Blocked Capabilities

The proof is not selectable, registry-backed, factory-backed, dispatcher-backed, executor-targetable, provider-KAT-executor-targetable, or runtime-operational.

It cannot execute provider operations, execute crypto, participate in vault lifecycle, persist vault data, participate in production sync, touch backend clients or BDK wallet state, persist settings, expose UI, sign, broadcast, add public endpoint defaults, or enable mainnet.

The proof does not implement `VaultCryptoProvider`, does not hold a provider instance, does not expose provider handles or crypto objects, and redacts marker IDs from `toString`.

## Production Scope

Production implementation remains out of scope. It requires later review after test-only implementation proves out and can be verified against the same expected behavior.

No provider, executor, KAT execution, crypto operation, storage operation, wallet operation, settings persistence, UI entry point, backend client, signing/broadcasting path, public endpoint, or mainnet behavior is implemented.

Skald constraints remain intact: no Skald-operated infrastructure, no Skald-managed default backend, no real funds, no secrets, no live wallet data, no production crypto provider, no production vault persistence, no signing/broadcasting, and mainnet remains disabled.
