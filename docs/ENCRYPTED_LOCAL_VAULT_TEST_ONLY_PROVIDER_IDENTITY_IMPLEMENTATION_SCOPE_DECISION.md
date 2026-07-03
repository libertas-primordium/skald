# Encrypted Local Vault: Test-Only Provider Identity Implementation Scope Decision

This document records the still-disabled, model-only Skald Vault v1 scope decision for any future branch that may request review to introduce a test-only provider identity implementation.

The identity decision, identity isolation guard, synthetic identity namespace contract, source-set confinement boundary, implementation decision gate, prerequisite audit, and scope decision are separate boundaries. The identity decision classifies future categories. The isolation guard keeps them label-only and unreachable. The namespace contract constrains safe labels. The source-set confinement boundary constrains placement. The implementation decision gate says implementation is not authorized now. The prerequisite audit enumerates missing prerequisites and non-authorizing absence evidence. This scope decision defines what a later branch may request for review and what remains out of scope. Current scope evidence remains blocked and non-authorizing.

## Current State

The scope decision is modeled and still disabled. Current outcomes are `CurrentScopeBlocked`, `ScopeReviewRequired`, `ImplementationNotAuthorized`, `ProductionPromotionNotAuthorized`, and `MainnetNotAuthorized`.

This evidence does not implement a provider identity and does not authorize provider selection. Provider selection remains disabled-provider-only through `DisabledVaultCryptoProvider`, and `productionProviderSelectable` remains false.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_CONTRACT.md) records the next separate still-disabled contract boundary; scope decision evidence remains non-authorizing.

## Scope Categories

The scope decision models these categories: model-only scope, future test-only identity scope, source-set placement scope, synthetic namespace scope, non-runtime evidence scope, production-forbidden scope, runtime reachability forbidden scope, provider selection forbidden scope, registry/factory/dispatcher forbidden scope, executor-target forbidden scope, provider KAT executor forbidden scope, provider operation forbidden scope, vault lifecycle forbidden scope, persistence forbidden scope, secure storage forbidden scope, secure metadata forbidden scope, backend client forbidden scope, BDK wallet state forbidden scope, settings codec forbidden scope, UI surface forbidden scope, signing/broadcasting forbidden scope, Tor/Nostr forbidden scope, public endpoint forbidden scope, and mainnet forbidden scope.

## Allowed Future Scope Items

Allowed future scope items are future-review-only and non-authorizing. They do not permit current implementation.

The future branch may request review for a test-only identity implementation, propose a synthetic safe ID, propose a test-only namespace, propose non-production source-set placement, add test-source-only fixtures for review, add model-only evidence rows, add source guards, add redaction tests, document blocked implementation, and prove production/runtime/provider-selection/registry-factory-dispatcher/executor-target/vault-lifecycle/persistence/mainnet absence.

## Forbidden Scope Items

The current scope forbids implementing a provider identity now, implementing a production provider identity, implementing `VaultCryptoProvider`, adding provider factory/dispatcher/registry entries, adding executor targets, adding provider KAT executors or runnable executor interfaces, executing provider operations, runtime randomness, KDF/HKDF/HMAC/AEAD, generating keys, storing keysets, creating/unlocking/sessioning/persisting vaults, succeeding secure storage or secure metadata storage, writing storage namespaces or storage paths, reading/writing manifests, running migration, starting production sync, creating backend clients or BDK wallet state, writing settings codecs, exposing UI surfaces, signing, broadcasting, starting Tor transport, parsing Nostr secrets, adding public endpoint defaults, or enabling mainnet.

## Forbidden Shortcuts

The scope decision forbids treating scope evidence, prerequisite evidence, future-branch claims, allowed future scope, prior identity decision evidence, identity isolation evidence, synthetic namespace evidence, source-set confinement evidence, implementation decision evidence, documentation, test-only evidence, user consent, warning-only evidence, release claims, absence evidence, disabled provider status, or KAT harness evidence as scope approval or implementation readiness.

## Forbidden Promotion Paths

The scope decision cannot promote into provider implementation, test-only provider identity implementation, production provider identity implementation, provider selection, `productionProviderSelectable=true`, registry entries, factories, dispatchers, executor targets, provider KAT executors, provider operations, randomness, KDF/HKDF/HMAC/AEAD, key generation, keyset storage, vault creation, vault unlock, vault sessions, vault persistence, secure storage success, secure metadata success, manifest read/write, migration, production sync, backend clients, BDK wallet state, settings codecs, UI surfaces, signing, broadcasting, Tor transport, Nostr parsing, public endpoint defaults, or mainnet.

## Non-Goals Preserved

This branch adds no provider, executor, KAT execution, crypto operation, storage operation, wallet operation, settings persistence, UI entry point, backend client, signing, broadcasting, Tor, Nostr, public endpoint, or mainnet behavior.

Skald still has no operated infrastructure, no Skald-managed default backend, no real funds path, no secrets path, no live wallet data path, no production crypto provider, no production vault persistence, no signing, no broadcasting, and mainnet remains disabled.

Any later implementation branch must obtain explicit future approval, satisfy prerequisite audit items, request scope review, prove source-set placement, prove safe synthetic identity handling, preserve all production absences, pass redaction/leakage and source-guard review, and keep scope evidence non-authorizing for production provider selection.
