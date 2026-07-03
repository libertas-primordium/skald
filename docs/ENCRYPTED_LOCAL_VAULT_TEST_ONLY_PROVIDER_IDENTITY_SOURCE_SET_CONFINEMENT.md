# Encrypted Local Vault: Test-Only Provider Identity Source-Set Confinement

This document records the still-disabled, model-only Skald Vault v1 source-set confinement boundary for any future test-only provider identity implementation.

It follows the test-only provider identity decision, identity isolation guard, and synthetic identity namespace contract, but it is a separate boundary. The decision boundary classifies future identity categories, the isolation guard proves those labels cannot bridge into runtime surfaces, the namespace contract constrains safe labels, and this boundary constrains where implementation code may and may not be placed.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_DECISION.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_DECISION.md) records the separate still-disabled future implementation decision gate; source-set placement evidence does not authorize implementation.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_PREREQUISITE_AUDIT.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_PREREQUISITE_AUDIT.md) records the separate still-disabled future implementation prerequisite audit; source-set placement evidence remains non-authorizing.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_SCOPE_DECISION.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_SCOPE_DECISION.md) records the separate still-disabled future implementation scope decision; source-set placement evidence remains non-authorizing.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_CONTRACT.md) records the separate still-disabled future implementation contract; source-set placement evidence remains non-authorizing.

## Current State

This branch does not implement a provider identity. It does not expose an instantiable identity, registry key, factory input, dispatcher input, executor target, KAT executor, provider operation, vault lifecycle hook, persistence path, secure storage success path, secure metadata success path, backend client, BDK wallet state, settings codec, UI entry point, signing path, broadcasting path, Tor transport, Nostr parsing path, public endpoint default, or mainnet authorization.

Provider selection remains disabled-provider-only. `productionProviderSelectable` remains false. Source-set placement evidence is not authorization.

## Source-Set Model

`commonMain` may contain model-only policy evidence like this file's companion Kotlin model. It must not contain any test-only provider identity implementation.

`androidMain` and `desktopMain` are production runtime source sets and must not contain any test-only provider identity implementation, synthetic identity alias, provider identity fixture, provider selection bridge, registry bridge, factory bridge, dispatcher bridge, executor target bridge, vault lifecycle bridge, storage bridge, settings bridge, UI bridge, backend bridge, signing or broadcasting bridge, public endpoint bridge, or mainnet bridge.

`commonTest`, `desktopTest`, and Android instrumented test sources are modeled as future-review-only. They do not authorize implementation in this branch and can never authorize production reachability by themselves.

Build scripts may not activate provider identity dependencies or make production runtime depend on a test-only identity. Documentation may describe blocked placement only and does not authorize implementation.

## Forbidden Reachability

Future test-only provider identity implementation code must remain unreachable from production provider selection, production registry, provider factory, provider dispatcher, executor targets, provider KAT executors, provider operations, randomness, KDF/HKDF/HMAC/AEAD, key generation, keyset storage, vault creation, vault unlock, vault sessions, vault persistence, secure storage success, secure metadata success, storage namespaces, storage paths, production sync, backend clients, BDK wallet state, settings codecs, UI surfaces, signing, broadcasting, Tor transport, Nostr parsing, public endpoint defaults, and mainnet.

## Non-Authorization

Test-only identity evidence, namespace label evidence, source-set placement evidence, documentation, public vector/KAT evidence, release claims, warning-only evidence, and user consent do not authorize provider implementation, provider selection, `productionProviderSelectable=true`, executor targeting, KAT execution, vault creation, vault unlock, vault persistence, secure storage success, secure metadata success, production sync, signing, broadcasting, public endpoints, or mainnet.

## Preserved Skald Constraints

Skald still has no operated infrastructure, no Skald-managed default backend, no real funds path, no secrets path, no live wallet data path, no production crypto provider, no production vault persistence, no signing, no broadcasting, and mainnet remains disabled.

Any future branch that wants to implement a test-only provider identity must explicitly review source-set placement, prove production source sets remain free of that implementation, prove registry/factory/dispatcher/executor/vault/storage/sync/backend/settings/UI/mainnet isolation, and keep the resulting evidence non-authorizing for production provider selection.
