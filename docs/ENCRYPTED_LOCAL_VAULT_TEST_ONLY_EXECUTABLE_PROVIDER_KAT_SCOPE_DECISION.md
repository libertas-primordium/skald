# Encrypted Local Vault Test-Only Executable Provider KAT Scope Decision

## Purpose

This document records the still-disabled, model-only scope decision for any future test-only executable provider-level KAT path.

It follows the provider executable KAT decision gate and prerequisite audit:

- the decision gate defines the evidence boundary before executable provider KATs may ever be introduced,
- the prerequisite audit maps that evidence boundary to current model, documentation, and test-only evidence,
- this scope decision defines the only future test-only source-set, operation, and material classes that a later executor branch may propose.

This branch does not implement an executor. It does not add provider execution, a provider implementation, a provider factory, a provider dispatcher, a non-disabled registry entry, provider operation execution, randomness, KDF/HKDF/HMAC/AEAD execution, key generation, vault creation, vault unlock, vault persistence, secure storage success, secure metadata success, production sync, signing, broadcasting, Tor transport, Nostr parsing, public endpoints, or mainnet.

## Current Decision

A future test-only executable provider KAT path is permitted in principle only as a later branch category, and only if the hard constraints in this document are satisfied by explicit review evidence.

Current execution is not authorized.

Test-only executable KAT execution is not authorized now.

Production executable KAT execution is not authorized now.

Provider selection remains disabled-provider-only. `VaultCryptoProviderSelectionRegistry` continues to select only `DisabledVaultCryptoProvider`.

`productionProviderSelectable` remains false.

Vault creation, vault unlock, vault persistence, production sync, and mainnet remain blocked.

## Future Source-Set Scope

A later branch may consider executable test-only provider KAT code only in:

- `desktopTest`, for Linux/JVM runtime validation,
- Android instrumented test source sets, for Android runtime validation,
- `commonTest`, for model assertions only.

The following remain forbidden for executable provider KAT code:

- `commonMain`,
- `androidMain`,
- `desktopMain`,
- build scripts except explicit dependency declarations reviewed by a later branch.

Documentation remains allowed only as non-authorizing evidence.

## Future Test-Only Operations That May Be Considered

A later branch may consider test-only execution for:

- public non-wallet positive vectors,
- public non-wallet negative vectors,
- fixed deterministic provider vectors,
- randomized behavioral AEAD checks with explicitly test-only generated material,
- redaction assertions,
- provider self-test routing assertions,
- platform runtime checks in test source sets only.

These are future test-only categories only. They do not authorize execution in this branch and cannot authorize production.

## Operations Always Forbidden

Even in a future test-only KAT branch, the test-only path must not authorize or perform:

- production provider execution,
- production provider selection,
- `productionProviderSelectable=true`,
- production vault creation,
- production vault unlock,
- production vault persistence,
- production secure storage success,
- production secure metadata success,
- real credential input,
- real wallet material use,
- derivation inputs from real user state,
- real backend state use,
- BDK wallet material use,
- mainnet.

## Material Scope

A future test-only KAT path may use only:

- public non-wallet vectors,
- explicitly test-only generated material,
- deterministic non-wallet fixtures,
- redacted diagnostics,
- synthetic safe identifiers.

A future test-only KAT path must not use:

- user wallet material,
- real seeds,
- real mnemonics,
- real descriptors,
- real xprv/tprv/WIF material,
- Nostr nsec values,
- Lightning credentials,
- Cashu proofs,
- backend credentials,
- real wallet labels,
- real UTXO labels,
- transaction notes,
- backend observation metadata,
- secure metadata records,
- production vault records.

## Required Evidence Gates Before A Later Executor Branch

Before any later branch may implement a test-only executable provider KAT path, it must provide explicit review evidence for:

- prerequisite audit completion,
- source-set confinement policy completion,
- test-only executor design review,
- provider operation authorization continuing to block production,
- provider selection remaining disabled-provider-only,
- `productionProviderSelectable=false` assertions,
- public non-wallet vector provenance,
- canonical Skald vector provenance,
- negative KAT plan,
- redaction KAT plan,
- randomness policy for test-only material,
- no wallet-material fixture policy,
- Android instrumentation targeting plan,
- Linux/JVM runtime targeting plan,
- test-result non-authorizing policy,
- CI/local-only limitations,
- release/mainnet non-authorizing policy.

The current model lists these gates as future required, partially modeled, documentation-only, or non-authorizing. None authorizes execution now.

## Non-Authorizing Evidence

Test-only evidence cannot authorize production provider selection.

Test-only KAT results cannot set `productionProviderSelectable=true`.

Test-only KAT results cannot authorize vault creation, vault unlock, vault persistence, production sync, signing, broadcasting, Tor, Nostr parsing, public endpoints, or mainnet.

Warning-only evidence cannot authorize execution.

User consent cannot override missing hard gates.

Dependency-level public-vector KAT evidence is not provider-level execution evidence.

Public vector documentation is provenance evidence, not execution.

## What Remains Required After Any Future Test-Only KAT Path

Even if a later branch adds a confined test-only executable provider KAT path, production provider selection would still require separate reviewed evidence for a provider implementation, provider factory, registry change, dispatch authorization, runtime randomness authorization, final KDF calibration, executable credential policy, redaction/leakage behavior, clear/wipe behavior, migration/corruption behavior, secure storage readiness, secure metadata readiness, creation authorization, unlock authorization, persistence readiness, production acceptance, release review, and explicit approval to change `productionProviderSelectable`.

Vault persistence would still require encrypted vault storage implementation, storage safety, atomic write/crash recovery review, rollback/stale-record handling, secure secret storage, secure metadata storage, lock/session lifecycle, recovery-state integration, and fail-closed partial-failure handling.

Mainnet would still require explicit user approval and release-hardening review.

## Test-Only Executor Contract

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_KAT_EXECUTOR_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_KAT_EXECUTOR_CONTRACT.md) records the still-disabled non-executable contract that follows this scope decision. It defines the exact future input label/reference classes, forbidden input classes, future test-only operation categories, forbidden production operations, source-set contract, result redaction policy, and authorization limits a later executor branch must satisfy. It does not implement an executor, expose a runnable executor interface, authorize KAT execution now, authorize production provider selection, set `productionProviderSelectable=true`, enable vault creation/unlock/persistence, enable production sync, or enable mainnet.

## Test-Only Vector Catalog

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_KAT_VECTOR_CATALOG.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_KAT_VECTOR_CATALOG.md) records the still-disabled vector catalog that follows the executor contract. It defines future positive, negative, redaction, platform, provenance, and fixture policy classes as references only. It does not add raw vector material to common production source, implement an executor, authorize KAT execution now, authorize production provider selection, set `productionProviderSelectable=true`, enable vault creation/unlock/persistence, enable production sync, or enable mainnet.

## Test-Only Executor Readiness Gate

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_KAT_EXECUTOR_READINESS_GATE.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_KAT_EXECUTOR_READINESS_GATE.md) records the still-disabled readiness gate that follows the vector catalog. It evaluates whether the prior decision, audit, scope, contract, and catalog evidence can authorize an executor implementation branch. It keeps executor implementation unauthorized now and does not expose a runnable executor interface, authorize KAT execution, authorize provider selection, set `productionProviderSelectable=true`, enable vault creation/unlock/persistence, enable production sync, or enable mainnet.

## Test-Only Source-Set Confinement

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_KAT_SOURCE_SET_CONFINEMENT.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_KAT_SOURCE_SET_CONFINEMENT.md) records the still-disabled source-set confinement boundary that follows the readiness gate. It defines production-forbidden, model-only, evidence-only, and future-review-required source-set categories, but current executor implementation remains unauthorized. It does not expose a runnable executor interface, authorize KAT execution, authorize provider selection, set `productionProviderSelectable=true`, enable vault creation/unlock/persistence, enable production sync, or enable mainnet.

## Source And Tests

Production model:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecision.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderKatExecutorContract.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderKatVectorCatalog.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderKatExecutorReadinessGate.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderKatSourceSetConfinement.kt
```

Focused tests:

```text
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultProviderTestOnlyExecutableKatScopeDecisionTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultTestOnlyProviderKatExecutorContractTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultTestOnlyProviderKatVectorCatalogTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultTestOnlyProviderKatExecutorReadinessGateTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultTestOnlyProviderKatSourceSetConfinementTest.kt
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/ProductionBackendAdapterSourceGuardTest.kt
```
