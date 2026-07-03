# Skald Vault v1 Test-Only Provider Identity Implementation Runtime Linkage Guard

This document records the still-disabled, model-only runtime linkage guard for any future branch that may request review to introduce a test-only provider identity implementation.

The identity decision, identity isolation guard, synthetic namespace contract, source-set confinement boundary, implementation decision gate, prerequisite audit, scope decision, implementation contract, readiness gate, and runtime linkage guard are separate boundaries. The earlier boundaries classify labels, contain them, constrain placement, block implementation approval, audit prerequisites, define future-review scope, record contract requirements, and compose readiness evidence. This guard separately models runtime disconnection from production surfaces. None of those evidence layers authorizes implementation or provider selection.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_PROMOTION_BLOCKERS.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_PROMOTION_BLOCKERS.md) records the next separate still-disabled promotion containment boundary; runtime-linkage evidence cannot promote into implementation, provider selection, production identity, or mainnet.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_SOURCE_GUARD_COVERAGE.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_SOURCE_GUARD_COVERAGE.md) records the separate still-disabled source-guard coverage boundary; runtime-linkage evidence and guard coverage remain non-authorizing.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_REDACTION_GUARD.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_REDACTION_GUARD.md) records the separate still-disabled redaction boundary; runtime-linkage evidence and safe-output evidence remain non-authorizing.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_ADMISSION_GATE.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_ADMISSION_GATE.md) records the final current model-only admission boundary; runtime-linkage evidence remains non-authorizing and does not admit implementation.

Current runtime linkage guard evidence does not implement a provider identity, does not instantiate a provider, and does not authorize provider selection. Provider selection remains fail-closed to the disabled provider only, and `productionProviderSelectable` remains false.

## Runtime Surfaces

The guard models these runtime surfaces as disconnected: `ProviderSelection`, `ProviderRegistry`, `ProviderFactory`, `ProviderDispatcher`, `ExecutorTarget`, `ProviderKatExecutor`, `ProviderOperationDispatch`, `RuntimeRandomness`, `Kdf`, `Hkdf`, `Hmac`, `Aead`, `KeyGeneration`, `KeysetStorage`, `VaultCreation`, `VaultUnlock`, `VaultSession`, `VaultPersistence`, `SecureStorage`, `SecureMetadataStorage`, `StorageNamespace`, `StoragePath`, `ManifestReadWrite`, `Migration`, `ProductionSync`, `BackendClient`, `BdkWalletState`, `SettingsCodec`, `UiSurface`, `Signing`, `Broadcasting`, `TorTransport`, `NostrParsing`, `PublicEndpointDefault`, and `Mainnet`.

## Forbidden Runtime Bridges

The guard forbids test-only identity implementation linkage to provider selection, provider registry, provider factory, provider dispatcher, executor target, provider KAT executor, provider operation dispatch, runtime randomness, KDF, HKDF, HMAC, AEAD, key generation, keyset storage, vault creation, vault unlock, vault session, vault persistence, secure storage, secure metadata storage, storage namespace, storage path, manifest read/write, migration, production sync, backend client, BDK wallet state, settings codec, UI surface, signing, broadcasting, Tor transport, Nostr parsing, public endpoint defaults, and mainnet.

All current bridge booleans are false. Runtime-linkage evidence is evidence of blocked containment only; it is not implementation authorization.

## Forbidden Promotion Paths

The guard cannot promote into provider implementation, test-only provider identity implementation, production provider identity implementation, provider selection, `productionProviderSelectable=true`, registry entries, factories, dispatchers, executor targets, provider KAT executors, provider operations, randomness, KDF/HKDF/HMAC/AEAD, key generation, keyset storage, vault creation, vault unlock, vault sessions, vault persistence, secure storage success, secure metadata success, manifest read/write, migration, production sync, backend clients, BDK wallet state, settings codecs, UI surfaces, signing, broadcasting, Tor transport, Nostr parsing, public endpoint defaults, or mainnet.

## Non-Authorizing Evidence

Prior identity evidence, source guard coverage, redaction evidence, absence evidence, contract evidence, readiness evidence, documentation, warning-only evidence, user consent, release claims, public vector/KAT evidence, and runtime-linkage evidence are all non-authorizing. A future branch still requires explicit review and must not treat disconnected runtime surfaces as approval to implement or run anything.

## Non-Goals Preserved

This pass implements no provider, executor, KAT execution, crypto operation, storage operation, wallet operation, settings persistence, UI entry point, backend client, signing or broadcasting path, Tor transport, Nostr parsing, public endpoint, or mainnet behavior.

Skald constraints remain intact: no Skald-operated infrastructure, no Skald-managed default backend, no real funds, no secrets, no live wallet data, no production crypto provider, no production vault persistence, no signing/broadcasting, and mainnet remains disabled.
