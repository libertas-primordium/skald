# Encrypted Local Vault Test-Only Provider KAT Source-Set Confinement

## Purpose

This document records the still-disabled, model-only source-set confinement boundary for any future test-only provider KAT executor work.

It follows the existing executable KAT evidence chain:

- the provider executable KAT decision gate defines whether executable provider-level KAT work may ever be considered,
- the prerequisite audit maps that gate to current model, documentation, and test-only evidence,
- the test-only executable provider KAT scope decision defines future source-set, operation, and material limits,
- the test-only provider KAT executor contract defines the non-executable contract for a future executor,
- the test-only provider KAT vector catalog defines future vector, provenance, fixture, redaction, and platform-check classes as references only,
- the test-only provider KAT executor readiness gate keeps executor implementation unauthorized,
- this source-set confinement boundary defines where a later executor branch may and may not place code, and what source guards must remain active.

The common source model is `SkaldVaultV1TestOnlyProviderKatSourceSetConfinementPolicy`. It accepts typed source-set categories, confinement rule IDs, source-guard requirement IDs, booleans, safe IDs, and redacted labels only. It does not accept byte material, raw vectors, credentials, provider handles, crypto objects, file paths, storage handles, backend handles, or platform objects.

## Current Decision

The source-set confinement boundary is modeled.

The boundary is still disabled.

Executor implementation is not authorized now.

No executor is implemented.

No runnable executor interface is exposed.

Current test-only provider KAT execution is not authorized.

Production provider KAT execution is forbidden.

Provider selection remains disabled-provider-only. `VaultCryptoProviderSelectionRegistry` continues to select only `DisabledVaultCryptoProvider`.

`productionProviderSelectable` remains false.

Vault creation, vault unlock, vault persistence, secure storage success, secure metadata success, production sync, signing, broadcasting, and mainnet remain blocked.

## Production-Forbidden Source Sets

Future executor implementation must remain absent from:

- `commonMain`,
- `androidMain`,
- `desktopMain`,
- app UI production source,
- settings codecs,
- secure storage repositories,
- secure metadata repositories,
- BDK adapter production paths,
- generated production sources,
- production resources,
- packaging outputs.

These categories are modeled as denylist evidence only. They are not implementation approval.

## Test Source Sets

A later explicit branch may consider test-only executor code only in:

- `desktopTest`, after future review,
- Android instrumented test source sets, after future review.

This branch does not authorize either location now. It only records that those are the only categories a later branch may review.

`commonTest` remains model-only. It may contain policy assertions, decision-gate tests, catalog tests, and source-set confinement tests. It must not contain an executor or provider KAT execution path.

## Docs, Resources, And Packaging Outputs

Docs are evidence-only. They may describe future constraints, but they cannot authorize executor implementation, executor execution, provider selection, or production readiness.

Resources and packaging outputs must not carry raw vector material, wallet-like material, provider runtime state, or production vault state. Packaging artifacts are runtime outputs only and are non-authorizing.

## Source Guard Requirements

Future executor work must preserve source guards proving:

- crypto imports remain absent from common production security boundaries,
- BDK imports remain absent from common production security boundaries,
- process, network, file, and settings APIs remain absent from common production security boundaries,
- raw material types remain absent from decision model files,
- runnable executor method names remain absent from this boundary,
- raw vector field names remain absent from this boundary,
- production enablement flags remain absent,
- wallet-material fixtures remain absent,
- provider selection remains disabled-provider-only,
- `productionProviderSelectable` remains false.

Source-set modeling is not implementation approval. Passing source guards is necessary evidence for a later branch, but it is not sufficient to authorize an executor.

## Non-Authorization Rules

Source-set confinement evidence cannot authorize:

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

Test-only evidence cannot authorize production provider selection.

Warning-only evidence cannot authorize execution or implementation.

User consent cannot override missing hard gates.

Executor results, even after a future executor exists, cannot authorize production provider selection, `productionProviderSelectable=true`, vault creation, vault unlock, vault persistence, production sync, signing, broadcasting, or mainnet.

## Future Branch Requirement

A later branch would still be required before any actual test-only executor implementation. That branch would need explicit review and implementation decisions for:

- source-set-specific executor design,
- test-only provider identity,
- test-only provider implementation boundary if needed,
- test-only vector material placement outside production source and resources,
- result redaction implementation,
- non-authorization enforcement tests,
- Android runtime executor-targeting plan,
- Linux/JVM executor-targeting plan,
- CI/local execution policy,
- no-wallet-material fixture guard.

Even that future branch would not authorize production provider selection, `productionProviderSelectable=true`, vault lifecycle, vault persistence, production sync, signing, broadcasting, Tor transport, Nostr parsing, public endpoints, or mainnet. Those remain separate later production-readiness decisions.

## Test-Only Provider Identity Decision

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_DECISION.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_DECISION.md) records the still-disabled identity decision that follows this confinement boundary. It models future test-only provider identity categories and keeps every such identity unimplemented, non-instantiable, non-registry-selectable, non-factory-backed, non-dispatcher-backed, and non-executor-targetable. Source-set confinement evidence does not authorize provider implementation, provider selection, `productionProviderSelectable=true`, vault lifecycle, persistence, production sync, or mainnet.

## Non-Goals

This source-set confinement boundary does not add:

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

## Source And Tests

Production model:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderKatSourceSetConfinement.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderIdentityDecision.kt
```

Focused tests:

```text
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultTestOnlyProviderKatSourceSetConfinementTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultTestOnlyProviderIdentityDecisionTest.kt
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/ProductionBackendAdapterSourceGuardTest.kt
```
