# Encrypted Local Vault Test-Only Provider Identity Isolation Guard

This document records the still-disabled, model-only isolation guard for future test-only provider identity categories.

It follows the previous KAT gating work:

- [`ENCRYPTED_LOCAL_VAULT_PROVIDER_EXECUTABLE_KAT_DECISION_GATE.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_EXECUTABLE_KAT_DECISION_GATE.md)
- [`ENCRYPTED_LOCAL_VAULT_PROVIDER_EXECUTABLE_KAT_PREREQUISITE_AUDIT.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_EXECUTABLE_KAT_PREREQUISITE_AUDIT.md)
- [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_EXECUTABLE_PROVIDER_KAT_SCOPE_DECISION.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_EXECUTABLE_PROVIDER_KAT_SCOPE_DECISION.md)
- [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_KAT_EXECUTOR_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_KAT_EXECUTOR_CONTRACT.md)
- [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_KAT_VECTOR_CATALOG.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_KAT_VECTOR_CATALOG.md)
- [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_KAT_EXECUTOR_READINESS_GATE.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_KAT_EXECUTOR_READINESS_GATE.md)
- [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_KAT_SOURCE_SET_CONFINEMENT.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_KAT_SOURCE_SET_CONFINEMENT.md)
- [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_DECISION.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_DECISION.md)

The identity decision modeled future test-only provider identity categories. This isolation guard proves those modeled categories remain label-only and cannot leak into provider registry, factory, dispatcher, executor-target, vault lifecycle, persistence, secure storage, secure metadata, production sync, wallet services, BDK paths, settings codecs, app UI, or mainnet paths.

## Current Decision

The identity isolation guard is modeled.

The guard is still disabled.

Identity categories are labels only.

No test-only provider identity is implemented.

No production provider identity is implemented.

No instantiable provider identity is exposed.

No registry-selectable identity is exposed.

No factory-reachable identity is exposed.

No dispatcher-reachable identity is exposed.

No executor-targetable identity is exposed.

No vault-lifecycle-reachable identity is exposed.

No persistence-reachable identity is exposed.

Provider selection remains disabled-provider-only. `VaultCryptoProviderSelectionRegistry` continues to select only `DisabledVaultCryptoProvider`.

`productionProviderSelectable` remains false.

Vault creation, vault unlock, vault persistence, secure storage success, secure metadata success, production sync, signing, broadcasting, and mainnet remain blocked.

## What This Branch Does Not Implement

This branch does not add:

- a provider implementation,
- a class or object implementing `VaultCryptoProvider`,
- an instantiable provider identity,
- a registry-selectable identity,
- a factory-reachable identity,
- a dispatcher-reachable identity,
- an executor-targetable identity,
- an executable provider KAT executor,
- a test-only executor implementation,
- a runnable executor interface,
- provider operation execution,
- randomness execution,
- KDF, HKDF, HMAC, or AEAD execution,
- key generation,
- keyset creation or storage,
- vault container read/write,
- vault creation,
- vault unlock,
- vault persistence,
- secure storage success,
- secure metadata success,
- production sync,
- signing,
- broadcasting,
- Tor transport,
- Nostr parsing,
- public endpoint defaults,
- mainnet.

## Label-Only Identity Categories

The guard isolates these modeled categories:

- future test-only deterministic identity,
- future test-only randomized behavior identity,
- future test-only platform runtime identity,
- future production candidate identity,
- future Android wrapping identity,
- future Linux credential-first identity,
- unknown identity,
- rejected identity.

These are labels and categories only. They are not provider classes, provider objects, constructors, factories, registry entries, dispatcher targets, executor targets, or vault dependencies.

## Isolation Surfaces

Modeled identity categories must remain isolated from:

- provider selection registry,
- provider factory,
- provider dispatcher,
- provider operation authorization,
- provider KAT executor,
- executor target catalog,
- vault creation authorization,
- vault unlock authorization,
- vault persistence readiness,
- secure secret storage,
- secure metadata storage,
- production sync service,
- wallet domain services,
- BDK adapter paths,
- settings codecs,
- app UI,
- Android production source,
- Linux desktop production source,
- common production source,
- mainnet policy.

Isolation evidence does not authorize implementation. It only records the surfaces that must stay unreachable.

## Escape Risks

The guard models and blocks risks where an identity category would:

- become a registry entry,
- become a factory product,
- become a dispatcher target,
- become an executor target,
- become a provider operation target,
- become a vault creation dependency,
- become an unlock dependency,
- become a persistence dependency,
- become a secure storage dependency,
- become a secure metadata dependency,
- become a production sync dependency,
- become settings state,
- become UI state,
- reach BDK adapter paths,
- reach mainnet policy,
- carry provider handles,
- carry crypto objects,
- carry raw material.

Every current escape risk is modeled as blocked. No modeled risk is present as a current implementation path.

## Non-Authorization Rules

Identity isolation evidence cannot authorize:

- provider implementation,
- provider factory creation,
- provider dispatcher routing,
- provider registry entries,
- executor targets,
- executor implementation,
- executor execution,
- provider operation execution,
- provider selection,
- `productionProviderSelectable=true`,
- vault creation,
- vault unlock,
- vault persistence,
- secure storage success,
- secure metadata success,
- production sync,
- signing,
- broadcasting,
- mainnet.

User consent cannot promote an isolated identity. Warning-only evidence cannot promote an isolated identity. Test-only evidence cannot promote a production identity.

## Future Branch Requirement

A later branch would be required before any actual test-only provider identity implementation. That branch would need explicit approval for source-set placement, synthetic safe provider ID, registry exclusion, factory exclusion, dispatcher exclusion, executor target exclusion, vault lifecycle exclusion, persistence exclusion, storage exclusion, sync exclusion, wallet-service exclusion, BDK adapter exclusion, settings/UI exclusion, and mainnet non-authorization.

Even after a future test-only provider identity exists, the identity cannot by itself authorize production provider selection, `productionProviderSelectable=true`, vault creation, vault unlock, vault persistence, secure storage success, secure metadata success, production sync, signing, broadcasting, or mainnet.

## Source And Tests

Production model:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderIdentityIsolationGuard.kt
```

Focused tests:

```text
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultTestOnlyProviderIdentityIsolationGuardTest.kt
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/ProductionBackendAdapterSourceGuardTest.kt
```
