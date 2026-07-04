# Encrypted Local Vault: Test-Only Provider Identity Inventory

This document records the second slower test-only implementation step for the Skald Vault v1 provider identity chain: a commonTest-only inert test-only provider identity inventory.

The third slower test-only step composes this inventory with the inert marker into a commonTest-only inert profile documented in [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_PROFILE.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_PROFILE.md).

The fourth slower test-only step validates the inert marker, inventory, and profile in a commonTest-only report documented in [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_PROFILE_VALIDATION.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_PROFILE_VALIDATION.md).

The fifth slower test-only step proves negative runtime/provider reachability for the marker and inventory chain in a commonTest-only report documented in [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_REACHABILITY_PROOF.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_REACHABILITY_PROOF.md).

The sixth slower test-only step summarizes all marker-chain capabilities as blocked in a commonTest-only inert capability matrix documented in [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_CAPABILITY_MATRIX.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_CAPABILITY_MATRIX.md).

The seventh slower test-only step defines commonTest-only KAT fixture metadata scope for this inventory chain in [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_KAT_FIXTURE_SCOPE.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_KAT_FIXTURE_SCOPE.md).

The eighth slower test-only step records one metadata-only KAT fixture catalog row for this inventory chain in [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_KAT_FIXTURE_CATALOG.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_KAT_FIXTURE_CATALOG.md).

The inventory exists only under `composeApp/src/commonTest`. It contains exactly one inert marker and is not commonMain, AndroidMain, DesktopMain, UI, settings, backend, BDK, storage, or provider-selection runtime code.

## Inventory

- Safe ID contained: `skald-test-only-provider-identity-v1-deterministic-kat-inert-marker`
- Source-set placement: commonTest only.
- Marker count: one.
- Contents: the existing inert test-only provider identity marker only.

The inventory is not a production provider registry, does not implement `VaultCryptoProvider`, does not hold a provider instance, does not expose provider handles or crypto objects, and redacts marker IDs from `toString`.

## Blocked Capabilities

The inventory is not selectable, registry-backed, factory-backed, dispatcher-backed, executor-targetable, provider-KAT-executor-reachable, or runtime-operational.

It cannot execute provider operations, execute crypto, participate in vault lifecycle, persist vault data, participate in production sync, touch backend clients or BDK wallet state, persist settings, expose UI, sign, broadcast, add public endpoint defaults, or enable mainnet.

## Production Scope

Production implementation remains out of scope. It requires later review after test-only implementation proves out and can be verified against the same expected behavior.

No provider, executor, KAT execution, crypto operation, storage operation, wallet operation, settings persistence, UI entry point, backend client, signing/broadcasting path, public endpoint, or mainnet behavior is implemented.

Skald constraints remain intact: no Skald-operated infrastructure, no Skald-managed default backend, no real funds, no secrets, no live wallet data, no production crypto provider, no production vault persistence, no signing/broadcasting, and mainnet remains disabled.
