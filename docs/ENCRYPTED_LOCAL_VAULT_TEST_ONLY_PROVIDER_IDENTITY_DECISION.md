# Encrypted Local Vault Test-Only Provider Identity Decision

This document records the still-disabled, model-only provider identity decision for any future test-only provider KAT executor work.

It follows the existing chain of Skald Vault KAT boundaries:

- [`ENCRYPTED_LOCAL_VAULT_PROVIDER_EXECUTABLE_KAT_DECISION_GATE.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_EXECUTABLE_KAT_DECISION_GATE.md)
- [`ENCRYPTED_LOCAL_VAULT_PROVIDER_EXECUTABLE_KAT_PREREQUISITE_AUDIT.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_EXECUTABLE_KAT_PREREQUISITE_AUDIT.md)
- [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_EXECUTABLE_PROVIDER_KAT_SCOPE_DECISION.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_EXECUTABLE_PROVIDER_KAT_SCOPE_DECISION.md)
- [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_KAT_EXECUTOR_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_KAT_EXECUTOR_CONTRACT.md)
- [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_KAT_VECTOR_CATALOG.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_KAT_VECTOR_CATALOG.md)
- [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_KAT_EXECUTOR_READINESS_GATE.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_KAT_EXECUTOR_READINESS_GATE.md)
- [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_KAT_SOURCE_SET_CONFINEMENT.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_KAT_SOURCE_SET_CONFINEMENT.md)

The source-set confinement boundary defines where a future test-only executor may and may not live. This identity decision defines what kind of provider identity such a future executor may reference in principle, without implementing any provider and without making any provider selectable.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_ISOLATION_GUARD.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_ISOLATION_GUARD.md) records the still-disabled isolation guard that follows this identity decision. It keeps the modeled identity categories label-only and unreachable from registry, factory, dispatcher, executor-target, vault-lifecycle, persistence, secure-storage, secure-metadata, production-sync, wallet-service, BDK, settings, UI, and mainnet paths.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_SYNTHETIC_IDENTITY_NAMESPACE.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_SYNTHETIC_IDENTITY_NAMESPACE.md) records a separate still-disabled namespace contract for future safe synthetic identity labels. It is label hygiene only and does not implement or authorize any provider identity.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_SOURCE_SET_CONFINEMENT.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_SOURCE_SET_CONFINEMENT.md) records a separate still-disabled placement boundary for where any future test-only provider identity implementation may and may not live.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_DECISION.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_DECISION.md) records the separate still-disabled future implementation decision gate; current identity evidence does not authorize implementation.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_PREREQUISITE_AUDIT.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_PREREQUISITE_AUDIT.md) records the separate still-disabled future implementation prerequisite audit; current identity evidence remains non-authorizing.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_SCOPE_DECISION.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_SCOPE_DECISION.md) records the separate still-disabled future implementation scope decision; current identity evidence remains non-authorizing.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_CONTRACT.md) records the separate still-disabled future implementation contract; current identity evidence remains non-authorizing.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_READINESS_GATE.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_READINESS_GATE.md) records the separate still-disabled future implementation readiness gate; current identity evidence remains non-authorizing.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_RUNTIME_LINKAGE_GUARD.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_RUNTIME_LINKAGE_GUARD.md) records the separate still-disabled runtime containment boundary; current identity evidence remains disconnected from runtime provider surfaces.

## Current Decision

The current branch only models provider identity categories. It does not implement a provider.

- A future test-only provider identity is modeled as a possible later branch category only.
- No test-only provider identity is implemented now.
- No production provider identity is implemented now.
- No instantiable provider identity is exposed.
- No registry-selectable provider identity is exposed.
- No factory-created provider identity is exposed.
- No dispatcher-routable provider identity is exposed.
- No executor-targetable provider identity is exposed.
- Current provider selection remains disabled-provider-only.
- `productionProviderSelectable` remains false.
- Vault creation, vault unlock, vault persistence, secure storage success, secure metadata success, production sync, signing, broadcasting, and mainnet remain blocked.

## What This Boundary Does Not Implement

This boundary does not add:

- a provider implementation,
- a class or object implementing `VaultCryptoProvider`,
- a provider factory,
- a provider dispatcher,
- a provider registry entry,
- an executor target,
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

## Modeled Future Identity Categories

The model classifies provider identity categories as labels only:

- disabled provider identity,
- future test-only deterministic provider identity,
- future test-only randomized behavior provider identity,
- future test-only platform runtime provider identity,
- future production candidate provider identity,
- future Android wrapping provider identity,
- future Linux credential-first provider identity,
- rejected production provider identity,
- unsupported provider identity,
- unknown provider identity.

Only the disabled provider identity is present as the current selectable identity. Future test-only identities are not implemented, not selectable, not factory-backed, not dispatcher-backed, and not executor-targetable in this branch.

## Test-Only Identity Limits

A future test-only provider identity would require a later explicit branch. That later branch would still need to prove that the identity:

- uses a synthetic safe provider ID only,
- uses a test-only namespace,
- is explicitly source-set confined,
- is absent from production source sets,
- is absent from the production registry,
- is absent from provider factory paths,
- is absent from provider dispatcher paths,
- does not implement a production provider interface in production source,
- does not hold provider references,
- does not expose crypto references,
- does not accept raw material,
- does not persist state,
- does not create vault records,
- does not create keysets,
- does not write files,
- does not access secure storage,
- does not access secure metadata storage,
- does not access wallet metadata,
- does not access backend metadata,
- does not authorize production.

## Forbidden Linkage

The identity decision forbids linking any future test-only provider identity to:

- production provider selection,
- production registry entries,
- provider factories,
- provider dispatchers,
- vault creation,
- vault unlock,
- vault persistence,
- secure storage success,
- secure metadata success,
- production sync,
- BDK wallet state,
- wallet labels,
- UTXO labels,
- backend observation state,
- mainnet.

## Non-Authorization Rules

Identity decision evidence cannot authorize:

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

User consent cannot promote a modeled identity. Warning-only evidence cannot promote a modeled identity. Test-only evidence cannot promote a production identity.

## Identity Isolation Guard

The identity isolation guard is the next still-disabled model-only boundary after this decision. It does not add a provider, provider constructor, registry entry, factory route, dispatcher route, executor target, vault lifecycle dependency, persistence dependency, secure-storage dependency, secure-metadata dependency, production-sync dependency, wallet-service dependency, BDK adapter path, settings state, UI state, or mainnet path.

Identity decision evidence and identity isolation evidence remain non-authorizing. They cannot authorize provider selection, `productionProviderSelectable=true`, vault creation, vault unlock, vault persistence, secure storage success, secure metadata success, production sync, signing, broadcasting, or mainnet.

## Future Branch Requirement

A later branch would be required before any actual test-only provider identity implementation is introduced. That branch would need an explicit test-only identity implementation decision, source-set placement review, synthetic safe provider ID review, non-registry-selectable proof, non-factory proof, non-dispatcher proof, executor target prohibition review, provider selection non-authorization review, vault lifecycle non-authorization review, production sync non-authorization review, and mainnet non-authorization review.

Even after a future test-only identity exists, its existence cannot by itself authorize production provider selection, `productionProviderSelectable=true`, vault creation, vault unlock, vault persistence, secure storage success, secure metadata success, production sync, signing, broadcasting, or mainnet.

## Source And Tests

Model source:

- `composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderIdentityDecision.kt`
- `composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderIdentityIsolationGuard.kt`

Focused tests:

- `composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultTestOnlyProviderIdentityDecisionTest.kt`
- `composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultTestOnlyProviderIdentityIsolationGuardTest.kt`

Source guards:

- `composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/ProductionBackendAdapterSourceGuardTest.kt`
