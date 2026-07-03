# Encrypted Local Vault Test-Only Provider KAT Executor Readiness Gate

## Purpose

This document records the still-disabled, model-only readiness gate for deciding whether a later branch may implement a test-only provider KAT executor.

It follows the existing executable KAT evidence chain:

- the provider executable KAT decision gate defines whether executable provider-level KAT work may ever be considered,
- the prerequisite audit maps that gate to current model, documentation, and test-only evidence,
- the test-only executable provider KAT scope decision defines future source-set, operation, and material limits,
- the test-only provider KAT executor contract defines the non-executable contract for a future executor,
- the test-only provider KAT vector catalog defines future positive, negative, redaction, platform, provenance, and fixture policy classes as references only,
- this readiness gate evaluates whether that evidence is sufficient to authorize a later executor implementation branch.

The common source model is `SkaldVaultV1TestOnlyProviderKatExecutorReadinessGatePolicy`. It accepts typed evidence labels, requirements, classifications, booleans, safe IDs, and redacted labels only. It does not accept byte material, raw vector material, raw credentials, provider handles, crypto objects, file paths, storage handles, backend handles, or platform objects.

## Current Decision

The readiness gate is modeled.

The gate is still disabled.

Executor implementation is not authorized now.

No executor is implemented.

No runnable executor interface is exposed.

Current test-only provider KAT execution is not authorized.

Production provider KAT execution is forbidden.

Provider selection remains disabled-provider-only. `VaultCryptoProviderSelectionRegistry` continues to select only `DisabledVaultCryptoProvider`.

`productionProviderSelectable` remains false.

Vault creation, vault unlock, vault persistence, secure storage success, secure metadata success, production sync, signing, broadcasting, and mainnet remain blocked.

## Evidence Modeled

The gate classifies the following as prior model-only or documentation/test-only evidence:

- executable KAT decision gate,
- prerequisite audit,
- test-only scope decision,
- executor contract,
- vector catalog,
- provider KAT execution isolation,
- provider KAT contract,
- provider selection boundary,
- provider registry isolation,
- provider factory isolation,
- provider operation dispatch isolation,
- runtime randomness policy,
- Argon2id calibration policy,
- secure storage boundary,
- secure metadata boundary,
- encrypted vault readiness policy,
- production provider acceptance contract.

That evidence is traceability evidence. It is not implementation authorization.

## Insufficient Evidence

The current repository remains not ready for executor implementation because:

- no executor implementation exists,
- no runnable executor surface exists,
- source-set implementation review is still missing,
- no provider implementation exists,
- no provider factory exists,
- no provider dispatcher exists,
- no non-disabled registry entry exists,
- provider operation authorization remains blocked,
- runtime randomness authorization remains blocked,
- KDF calibration is non-final,
- production provider acceptance remains incomplete,
- secure storage remains disabled,
- secure metadata remains disabled,
- vault lifecycle remains disabled,
- persistence remains disabled,
- production sync remains disabled,
- mainnet remains disabled.

Source-set modeling is not implementation approval. The existing scope decision and contract identify possible future test-only source-set categories, but this branch does not place executable code in those source sets and does not authorize doing so now.

Vector catalog evidence is not execution approval. The catalog lists future reference classes only; it does not contain raw vector material, run vectors, or authorize provider operations.

## Non-Authorization Rules

Test-only evidence cannot authorize production provider selection.

Warning-only evidence cannot authorize execution or implementation.

User consent cannot override missing hard gates.

Executor results, even after a future executor exists, cannot authorize:

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

## Future Branch Requirement

A later branch would still be required before any actual test-only executor implementation. That branch would need explicit review and implementation decisions for:

- source-set-specific executor design,
- test-only provider identity,
- test-only provider implementation boundary if needed,
- test-only vector material placement,
- result redaction implementation,
- non-authorization enforcement tests,
- Android runtime executor-targeting plan,
- Linux/JVM executor-targeting plan,
- CI/local execution policy,
- no-wallet-material fixture guard.

Even that future branch would not authorize production provider selection, `productionProviderSelectable=true`, vault lifecycle, vault persistence, production sync, signing, broadcasting, Tor transport, Nostr parsing, public endpoints, or mainnet. Those remain separate later production-readiness decisions.

## Test-Only Source-Set Confinement

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_KAT_SOURCE_SET_CONFINEMENT.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_KAT_SOURCE_SET_CONFINEMENT.md) records the still-disabled source-set confinement boundary that follows this readiness gate. It defines production-forbidden source sets, model-only common-test participation, evidence-only docs, future-review-required desktop and Android test source sets, and source guard requirements. The current answer remains no: executor implementation, runnable executor interfaces, KAT execution, provider selection, `productionProviderSelectable=true`, vault lifecycle, persistence, production sync, and mainnet remain blocked.

## Test-Only Provider Identity Decision

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_DECISION.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_DECISION.md) records the still-disabled identity decision that follows source-set confinement. It answers what kind of provider identity a future test-only executor may reference in principle. The current answer remains model-only: no provider identity is implemented, instantiable, registry-selectable, factory-backed, dispatcher-backed, or executor-targetable, and provider selection, `productionProviderSelectable=true`, vault lifecycle, persistence, production sync, and mainnet remain blocked.

## Test-Only Provider Identity Isolation Guard

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_ISOLATION_GUARD.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_ISOLATION_GUARD.md) records the still-disabled isolation guard that follows the identity decision. It proves the modeled identity categories remain label-only and cannot leak into registry, factory, dispatcher, executor target, vault lifecycle, persistence, secure storage, secure metadata, production sync, wallet services, BDK paths, settings codecs, app UI, or mainnet. The readiness gate remains not ready and not authorized.

## Non-Goals

This readiness gate does not add:

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
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderKatExecutorReadinessGate.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderKatSourceSetConfinement.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderIdentityDecision.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderIdentityIsolationGuard.kt
```

Focused tests:

```text
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultTestOnlyProviderKatExecutorReadinessGateTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultTestOnlyProviderKatSourceSetConfinementTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultTestOnlyProviderIdentityDecisionTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultTestOnlyProviderIdentityIsolationGuardTest.kt
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/ProductionBackendAdapterSourceGuardTest.kt
```
