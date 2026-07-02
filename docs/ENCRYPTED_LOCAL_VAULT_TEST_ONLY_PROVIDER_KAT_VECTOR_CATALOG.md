# Encrypted Local Vault Test-Only Provider KAT Vector Catalog

## Purpose

This document records the still-disabled, model-only catalog for future test-only provider-level KAT vector references.

It follows the existing executable KAT evidence chain:

- the provider executable KAT decision gate defines when any executable provider-level KAT path may be considered,
- the prerequisite audit maps that gate to current model, documentation, and test-only evidence,
- the test-only executable KAT scope decision defines future source-set, operation, and material limits,
- the test-only provider KAT executor contract defines the non-executable contract any later executor would have to satisfy,
- this catalog defines the future vector, provenance, fixture, negative-case, redaction, and platform-check classes that a later executor branch may reference,
- the test-only provider KAT executor readiness gate evaluates whether that evidence is sufficient to authorize a later executor implementation branch.

The common source model is `SkaldVaultV1TestOnlyProviderKatVectorCatalogPolicy`. It is a reference catalog only. It does not contain raw vector bytes and does not implement an executor.

## Current Decision

The vector catalog is modeled.

The catalog is still disabled.

The catalog contains vector references, provenance identifiers, operation classes, fixture classes, redaction classes, and platform-check classes only.

No raw vector material is added to common production source.

No executor is implemented or callable.

Current provider KAT execution is not authorized.

Provider selection remains disabled-provider-only. `VaultCryptoProviderSelectionRegistry` continues to select only `DisabledVaultCryptoProvider`.

`productionProviderSelectable` remains false.

Vault creation, vault unlock, vault persistence, secure storage success, secure metadata success, production sync, signing, broadcasting, and mainnet remain blocked.

## Cataloged Positive Vector Classes

The catalog models positive vector classes for:

- Argon2id known answers,
- XChaCha20-Poly1305 known answers,
- HKDF-SHA-256 expansion known answers,
- HMAC-SHA-256 header-commitment known answers,
- canonical header serialization,
- strict AAD serialization,
- provider policy ID mapping,
- redacted success result shape,
- platform runtime availability result shape.

These are catalog references only. They do not execute provider code and do not prove provider readiness.

## Cataloged Negative Vector Classes

The catalog models negative classes for wrong Argon2id parameters, wrong suite/provider/header policy, wrong header commitment, wrong HKDF/HMAC/AAD/key-class policy, wrong record purpose, wrong nonce policy, tampered encrypted payloads, tampered tags, truncated payloads, extra trailing bytes, wrong platform or source-set policy, credential-like fixture rejection, wallet-like fixture rejection, provider-reference rejection, crypto-reference rejection, and raw byte-material rejection.

These are future negative-case categories only. They do not execute and do not authorize execution.

## Cataloged Redaction Checks

The catalog models redaction checks requiring future results to expose only safe vector IDs, safe operation labels, and safe failure codes while excluding raw input, raw output, key material, randomness values, clear payloads, encrypted payloads, provider references, crypto references, file locations, wallet metadata, and backend metadata.

## Cataloged Platform Checks

The catalog models platform-check classes for desktop JVM candidate API availability, Android runtime candidate API availability, explicit Android instrumentation targets, Linux/JVM runtime classpath verification, no expected native Tink/Bouncy artifact, Lazysodium rejection, IonSpin deferral, and BDK unrelatedness.

Platform checks are test/source-set evidence only. They do not enable production provider selection.

## Vector Provenance

The catalog distinguishes provenance from authorization.

Allowed reference provenance includes public standards documents, Skald canonical documentation, Skald generated non-wallet deterministic fixture references, and synthetic test-only generated-at-runtime references.

Public standards vectors are non-wallet and non-authorizing. They may be used to identify future test-only checks, but the catalog does not copy raw bytes into common production source and does not turn public-vector documentation into provider execution.

Wallet-like provenance is rejected.

## Fixture Policy

Future allowed fixture classes are references only:

- public non-wallet vector reference,
- deterministic synthetic non-wallet reference,
- runtime-generated synthetic non-wallet test material,
- redacted expected outcome reference,
- safe vector ID,
- safe operation label.

Forbidden fixture classes include wallet seeds, mnemonics, descriptors, xprv/tprv/WIF material, Nostr nsec values, Lightning credentials, Cashu proofs, backend credentials, real addresses, real txids, PSBTs, transaction hex, wallet labels, UTXO labels, transaction notes, backend observation metadata, secure metadata records, production vault records, file locations, provider references, and crypto references.

## Non-Authorizing Catalog Evidence

Vector catalog evidence cannot authorize:

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

Warning-only evidence cannot authorize execution.

User consent cannot override missing hard gates.

Test-only evidence cannot authorize production.

## Non-Goals

This catalog does not add:

- raw vector material in common production source,
- executable provider KAT executor,
- test-only executor implementation,
- runnable executor interface,
- provider implementation,
- provider factory,
- provider dispatch path,
- provider registry entry for a non-disabled provider,
- production provider selection,
- `productionProviderSelectable=true`,
- provider operation execution,
- randomness execution,
- KDF/HKDF/HMAC/AEAD execution,
- key generation,
- keyset creation or storage,
- Bouncy Castle production KDF wrapper,
- Tink production AEAD wrapper,
- vault container read/write,
- storage success path,
- secure storage success path,
- secure metadata success path,
- file persistence,
- SharedPreferences sensitive persistence,
- wallet activation,
- production sync,
- backend clients,
- signing,
- broadcasting,
- Tor transport,
- Nostr parsing,
- public endpoint defaults,
- mainnet.

## Future Branch Requirement

A later branch would still be required before any actual test-only executor implementation. That branch would need to satisfy the decision gate, prerequisite audit, scope decision, executor contract, source-set confinement, vector provenance review, no-wallet-material fixture review, redacted result policy, and non-authorization policy.

Even after a confined test-only executor exists, production provider selection would still require separate provider implementation, factory, registry, dispatcher, operation authorization, runtime randomness authorization, final KDF calibration, redaction, clear/wipe, migration/corruption, secure storage, secure metadata, vault creation, vault unlock, persistence readiness, production acceptance, release review, and an explicit later decision to change `productionProviderSelectable`.

## Test-Only Executor Readiness Gate

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_KAT_EXECUTOR_READINESS_GATE.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_KAT_EXECUTOR_READINESS_GATE.md) records the still-disabled readiness gate that follows this catalog. It evaluates whether the prior decision, audit, scope, contract, and catalog evidence can authorize executor implementation. The current answer is no: executor implementation, runnable executor interfaces, KAT execution, provider selection, `productionProviderSelectable=true`, vault lifecycle, persistence, production sync, and mainnet remain blocked.

## Test-Only Source-Set Confinement

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_KAT_SOURCE_SET_CONFINEMENT.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_KAT_SOURCE_SET_CONFINEMENT.md) records the still-disabled source-set confinement boundary that follows the readiness gate. It keeps vector catalog evidence from becoming implementation approval, defines production-forbidden and future-review-required source-set categories, and keeps executor implementation, KAT execution, provider selection, `productionProviderSelectable=true`, vault lifecycle, persistence, production sync, and mainnet blocked.

## Source And Tests

Production model:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderKatVectorCatalog.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderKatExecutorReadinessGate.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderKatSourceSetConfinement.kt
```

Focused tests:

```text
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultTestOnlyProviderKatVectorCatalogTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultTestOnlyProviderKatExecutorReadinessGateTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultTestOnlyProviderKatSourceSetConfinementTest.kt
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/ProductionBackendAdapterSourceGuardTest.kt
```
