# Encrypted Local Vault: Test-Only Provider Identity KAT Fixture Scope

This document records the seventh slower test-only implementation step for the Skald Vault v1 provider identity chain: a commonTest-only inert test-only provider identity KAT fixture scope.

The eighth slower test-only step records one metadata-only KAT fixture catalog row for this scoped chain in [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_KAT_FIXTURE_CATALOG.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_KAT_FIXTURE_CATALOG.md).

The KAT fixture scope exists only under `composeApp/src/commonTest`. It is built from the inert marker, inert inventory, inert profile, validation report, reachability proof, and capability matrix. It defines metadata scope for future KAT fixture names, purposes, placement, redaction, and source-guard evidence only.

KAT fixture scope passing is not production authorization, provider-selection authorization, KAT execution authorization, crypto authorization, vault-persistence authorization, sync authorization, signing/broadcasting authorization, public-endpoint authorization, or mainnet authorization.

## Scope Inputs

- Safe ID checked: `skald-test-only-provider-identity-v1-deterministic-kat-inert-marker`
- Source-set placement: commonTest only.
- Marker count: one.
- Inventory count: one.
- Profile count: one.
- Validation report count: one.
- Reachability proof count: one.
- Capability matrix count: one.

The scope reads the existing commonTest-only chain, confirms the safe ID matches, and keeps all scope output redacted. It adds no executable KAT, KAT executor, provider operation, raw vector material, or runtime hook.

## Fixture Scope Categories

Metadata-only categories are `IdentityMarkerFixtureMetadata`, `IdentityInventoryFixtureMetadata`, `IdentityProfileFixtureMetadata`, `IdentityValidationFixtureMetadata`, `IdentityReachabilityFixtureMetadata`, `IdentityCapabilityFixtureMetadata`, `FutureKatFixtureNameOnly`, `FutureKatFixturePurposeOnly`, `FutureKatFixtureSourceSetOnly`, `FutureKatFixtureRedactionOnly`, and `FutureKatFixtureSourceGuardOnly`.

Forbidden material classes are `RawKatVectorBytes`, `RawKatVectorHex`, `PlaintextBytes`, `CiphertextBytes`, `NonceBytes`, `SaltBytes`, `AeadTagBytes`, `KeyBytes`, `KeysetBytes`, `PassphraseMaterial`, `SeedMaterial`, `MnemonicMaterial`, `PrivateKeyMaterial`, `XprvMaterial`, `XpubMaterial`, `NsecMaterial`, `PsbtMaterial`, `TransactionMaterial`, `WalletDescriptorMaterial`, `BackendEndpointMaterial`, `ProviderHandleMaterial`, and `CryptoObjectMaterial`.

## Blocked Capabilities

The scope is not selectable, registry-backed, factory-backed, dispatcher-backed, executor-targetable, provider-KAT-executor-reachable, or runtime-operational.

It cannot execute provider operations, execute crypto, participate in vault lifecycle, persist vault data, participate in production sync, touch backend clients or BDK wallet state, persist settings, expose UI, sign, broadcast, add public endpoint defaults, or enable mainnet.

The scope does not implement `VaultCryptoProvider`, does not hold a provider instance, does not expose provider handles or crypto objects, and redacts marker IDs from `toString`.

## Production Scope

Production implementation remains out of scope. It requires later review after test-only implementation proves out and can be verified against the same expected behavior.

No provider, executor, KAT execution, crypto operation, storage operation, wallet operation, settings persistence, UI entry point, backend client, signing/broadcasting path, public endpoint, or mainnet behavior is implemented.

Skald constraints remain intact: no Skald-operated infrastructure, no Skald-managed default backend, no real funds, no secrets, no live wallet data, no production crypto provider, no production vault persistence, no signing/broadcasting, and mainnet remains disabled.
