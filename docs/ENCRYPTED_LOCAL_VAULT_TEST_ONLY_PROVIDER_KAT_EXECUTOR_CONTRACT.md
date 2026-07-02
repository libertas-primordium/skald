# Encrypted Local Vault Test-Only Provider KAT Executor Contract

## Purpose

This document records the still-disabled, model-only contract for any future test-only provider KAT executor.

It follows the existing executable KAT evidence chain:

- the provider executable KAT decision gate defines when any executable provider-level KAT path may be considered,
- the prerequisite audit maps that gate to current model, documentation, and test-only evidence,
- the test-only scope decision defines the future source-set, operation, and material limits,
- this contract defines the non-executable policy shape a future test-only executor would have to satisfy before a later branch implements one.

This branch does not implement an executor. It also does not expose an executor interface whose method shape can run crypto. There is no `run`, `execute`, `encrypt`, `decrypt`, `derive`, `generate`, `wrap`, or `unwrap` executor surface.

The common source model is `SkaldVaultV1TestOnlyProviderKatExecutorContractPolicy`. It accepts typed evidence labels, booleans, safe identifiers, and enum categories only. It does not accept test-vector payloads, byte material, raw passphrases, salts, nonces, keys, plaintext, ciphertext, provider handles, crypto objects, file paths, storage handles, backend handles, or platform objects.

## Current Decision

The contract is modeled.

The executor is not implemented.

The executor is not callable.

Current test-only provider KAT execution is not authorized.

Production provider KAT execution is forbidden.

Provider selection remains disabled-provider-only. `VaultCryptoProviderSelectionRegistry` continues to select only `DisabledVaultCryptoProvider`.

`productionProviderSelectable` remains false.

Vault creation, vault unlock, vault persistence, production sync, signing, broadcasting, and mainnet remain blocked.

## Future Input Categories

A later executor branch may consider only label/reference inputs such as:

- public non-wallet KAT vector references,
- canonical Skald non-wallet vector references,
- synthetic test-only material references,
- deterministic test-only provider identities,
- test-only operation labels,
- redacted expected outcome labels,
- platform runtime evidence labels,
- source-set evidence labels.

Those categories are references only. They are not accepted by this branch, and they are not payload-carrying request types.

Forbidden input categories include raw passphrases, raw PINs, seeds, mnemonics, descriptors, xprv/tprv/WIF material, Nostr nsec values, Lightning credentials, Cashu proofs, backend credentials, wallet labels, UTXO labels, transaction notes, backend observation metadata, secure metadata records, production vault records, raw keys, raw nonces, raw salts, raw plaintext, raw ciphertext, provider handles, crypto objects, file paths, storage handles, and backend handles.

## Future Operation Categories

A later test-only branch may consider these operation labels only:

- positive public non-wallet KAT,
- negative public non-wallet KAT,
- deterministic Skald canonical vector KAT,
- test-only randomized AEAD behavior check,
- test-only provider self-test routing check,
- test-only redaction behavior check,
- test-only storage-separation assertion,
- test-only platform runtime check.

These are future test-only categories. They are not current execution authorization.

Forbidden operations include production provider operations, production KDF, production AEAD, production HKDF, production HMAC, production key generation, production key wrapping, production keyset storage, vault creation, vault unlock, vault persistence, secure storage writes, secure metadata writes, wallet sync, signing, broadcasting, and mainnet validation.

## Source-Set Contract

A later branch may consider executor implementation only in:

- `desktopTest`, after future review,
- Android instrumented test source sets, after future review.

`commonTest` may contain model assertions only, not provider KAT execution.

Executor implementation remains forbidden in:

- `commonMain`,
- `androidMain`,
- `desktopMain`,
- build scripts except explicit dependency declarations and test task wiring reviewed by a later branch.

Documentation remains non-authorizing evidence only.

## Result Policy

Future executor results may expose only:

- pass/fail status,
- vector ID,
- operation class,
- platform class,
- redacted diagnostic code.

Future executor results must not expose raw input, raw output, derived key material, provider handles, crypto objects, filesystem paths, wallet metadata, or backend metadata.

Executor results cannot authorize provider selection, `productionProviderSelectable=true`, vault creation, vault unlock, vault persistence, secure storage success, secure metadata success, production sync, signing, broadcasting, or mainnet.

## Non-Authorizing Evidence

Test-only evidence cannot authorize production provider selection.

Warning-only evidence cannot authorize execution.

User consent cannot override missing hard gates.

Dependency-level public-vector KAT evidence is not provider-level execution evidence.

Public vector documentation is provenance evidence, not execution.

Release evidence cannot bypass disabled provider selection or mainnet policy.

## Non-Goals

This contract does not add:

- executable provider KAT execution,
- test-only executor implementation,
- runnable executor interface,
- provider implementation,
- provider factory,
- provider dispatcher,
- non-disabled provider registry entry,
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

A later branch would be required before any actual test-only executor implementation. That branch would need to satisfy the decision gate, prerequisite audit, and scope decision; add explicit source-set confinement; define a non-production provider implementation boundary if needed; prove no wallet-material fixtures; keep results non-authorizing; and add focused source guards proving the executor cannot enter production source sets or provider selection.

Even after a future confined test-only executor exists, production provider selection would still require separate provider implementation, factory, registry, dispatcher, operation authorization, runtime randomness authorization, final KDF calibration, redaction, clear/wipe, migration/corruption, secure storage, secure metadata, vault creation, vault unlock, persistence readiness, production acceptance, release review, and an explicit later decision to change `productionProviderSelectable`.

## Test-Only Vector Catalog

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_KAT_VECTOR_CATALOG.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_KAT_VECTOR_CATALOG.md) records the still-disabled reference catalog for future executor inputs. It defines positive, negative, redaction, platform, provenance, and fixture policy classes as references only. It does not add raw vector material to common production source, implement an executor, expose a runnable executor interface, authorize KAT execution now, authorize production provider selection, set `productionProviderSelectable=true`, enable vault lifecycle or persistence, enable production sync, or enable mainnet.

## Source And Tests

Production model:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderKatExecutorContract.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderKatVectorCatalog.kt
```

Focused tests:

```text
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultTestOnlyProviderKatExecutorContractTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultTestOnlyProviderKatVectorCatalogTest.kt
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/ProductionBackendAdapterSourceGuardTest.kt
```
