# Encrypted Local Vault Provider Executable KAT Decision Gate

## Purpose

This document records the still-disabled, model-only decision gate for any future executable provider-level KAT path.

The gate exists because Skald already has provider KAT contracts, dependency-level public-vector evidence, a test-only provider KAT harness, a still-disabled integrated KAT harness, provider packaging evidence, dependency/build evidence, promotion blockers, provider interface audit evidence, registry/factory/dispatch isolation evidence, and provider KAT execution isolation evidence. None of that evidence is an executable production provider, and none of it is permission to add one.

The code model is `SkaldVaultV1ProviderExecutableKatDecisionGatePolicy` in common production source. It accepts typed evidence/status/request objects only and produces a deterministic blocked result. It is a decision boundary, not an executor.

## Current Decision

Executable provider-level KATs are not authorized.

Test-only executable provider-level KATs are not authorized yet.

Production executable provider-level KATs are not authorized.

Provider selection remains disabled-provider-only. `VaultCryptoProviderSelectionRegistry` continues to select only `DisabledVaultCryptoProvider`.

`productionProviderSelectable` remains false.

No KAT result may authorize provider selection, set `productionProviderSelectable=true`, authorize vault creation, authorize vault unlock, authorize vault persistence, authorize production sync, authorize signing, authorize broadcasting, or authorize mainnet.

## What This Boundary Does Not Implement

This boundary does not implement:

- encryption,
- provider implementation,
- provider factory,
- provider registry entry,
- provider dispatcher,
- provider KAT executor,
- provider self-test,
- runtime randomness,
- KDF, HKDF, HMAC, or AEAD execution,
- key generation,
- Tink keyset creation or storage,
- Bouncy Castle production KDF wrapper,
- vault container read/write,
- storage success paths,
- file persistence,
- SharedPreferences or Settings persistence for sensitive state,
- secure secret storage success,
- secure metadata storage success,
- vault creation,
- vault unlock,
- vault persistence,
- wallet activation,
- production sync,
- backend clients,
- signing,
- broadcasting,
- Tor transport,
- Nostr parsing,
- public endpoint defaults,
- mainnet.

## Evidence That Cannot Authorize Execution

Public vector documentation is evidence, not execution. It can define expected values and review provenance, but it cannot run provider code or approve provider readiness.

Dependency-level KATs are evidence, not provider-level KATs. Bouncy Castle Argon2id and Tink XChaCha20-Poly1305 public-vector checks show candidate library behavior on a runtime. They do not prove that a Skald-owned provider boundary exists or uses those libraries safely.

Test-only harnesses are not production authorization. A test-only provider harness or a still-disabled integrated harness can prove request/result expressiveness and fixed non-secret fixture behavior, but it cannot authorize production provider execution, provider selection, vault creation, unlock, storage, persistence, or mainnet.

Warning-only evidence cannot authorize execution.

User consent cannot override missing hard gates.

Release or mainnet evidence cannot bypass disabled provider selection.

## Future Evidence Required Before A Test-Only Executable Provider KAT Path

A later branch may propose a test-only executable provider-level KAT path only after explicit review evidence exists for all of the following:

- provider boundary reviewed,
- provider interface contract audited,
- provider implementation exists but remains non-production,
- provider factory isolation reviewed,
- provider registry isolation reviewed,
- provider operation dispatch isolation reviewed,
- provider KAT execution isolation reviewed,
- provider operation authorization reviewed,
- runtime randomness authorization reviewed,
- KDF calibration policy reviewed,
- passphrase policy reviewed,
- clear/wipe strategy reviewed,
- redaction/leakage policy reviewed,
- migration/corruption policy reviewed,
- secure storage boundary reviewed,
- secure metadata boundary reviewed,
- vault creation authorization reviewed,
- vault unlock authorization reviewed,
- persistence readiness reviewed,
- source-set confinement reviewed,
- provider-level positive KATs defined,
- provider-level negative KATs defined,
- provider-level redaction KATs defined,
- provider-level randomness KATs defined where applicable,
- provider-level storage-separation KATs defined where applicable,
- provider-level platform checks defined where applicable,
- public non-wallet vector provenance reviewed,
- canonical Skald vector provenance reviewed,
- Android runtime test strategy reviewed,
- Linux/JVM runtime test strategy reviewed.

Current model evidence enumerates these requirements but does not satisfy them and does not authorize execution.

## Future Stage Boundary

The modeled stages are:

- no executable KAT,
- model-only decision gate,
- test-only deterministic provider KAT prototype,
- test-only randomized behavioral provider KAT prototype,
- non-production provider self-test,
- production provider startup self-test,
- release validation KAT,
- mainnet release validation.

The current repository state is model-only decision gate / no executable KAT. It must not progress beyond that in this branch.

## Blockers That Remain Active

The current gate remains blocked by:

- no executable provider implementation,
- no provider KAT executor,
- provider selection disabled-provider-only,
- `productionProviderSelectable=false`,
- provider operation authorization blocked,
- runtime randomness authorization blocked,
- KDF calibration not final,
- passphrase policy not executable,
- secure storage disabled,
- secure metadata storage disabled,
- vault creation blocked,
- vault unlock blocked,
- persistence readiness blocked,
- storage readiness blocked,
- lifecycle/lock policy not executable,
- redaction/leakage gates incomplete,
- clear/wipe gates incomplete,
- migration/corruption gates incomplete,
- dependency-level KATs cannot authorize provider KATs,
- public vector documentation cannot authorize execution,
- test-only harness evidence cannot authorize production,
- test-only evidence cannot authorize production,
- warning-only evidence cannot authorize execution,
- user consent cannot override missing hard gates,
- release review missing,
- mainnet disabled.

## What Remains Blocked Even After A Future Executable KAT Path

Even if a later branch adds a test-only executable provider-level KAT path, that path by itself must not authorize:

- provider selection,
- production provider selectability,
- vault creation,
- vault unlock,
- vault persistence,
- secure secret storage,
- secure metadata storage,
- production sync,
- signing,
- broadcasting,
- Tor,
- Nostr parsing,
- public endpoints,
- mainnet.

Production provider selection would still require a separate reviewed provider implementation, provider factory, registry change, dispatch authorization, runtime randomness authorization, final KDF calibration, passphrase policy execution, redaction/leakage tests, clear/wipe tests, migration/corruption tests, secure storage readiness, secure metadata readiness, vault creation authorization, vault unlock authorization, persistence readiness, release review, and an explicit later decision to change `productionProviderSelectable`.

Vault persistence would still require encrypted vault storage implementation, storage safety, manifest/storage-index/record read/write review, atomic write/crash recovery review, stale-record/rollback handling, secure secret storage, secure metadata storage, lock/session lifecycle, recovery-state integration, and fail-closed partial-failure behavior.

Mainnet would still require explicit user approval and release-hardening review.

## Redaction

The decision gate exposes only policy ids, evidence class names, blocker labels, statuses, enums, booleans, and redacted-safe identifiers.

It must not accept or emit raw material, provider handles, crypto objects, diagnostic payloads, filesystem paths, backend handles, raw key material, raw nonces, ciphertext, plaintext, passphrases, salts, storage handles, platform crypto objects, or file paths.

## Prerequisite Audit

[`ENCRYPTED_LOCAL_VAULT_PROVIDER_EXECUTABLE_KAT_PREREQUISITE_AUDIT.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_EXECUTABLE_KAT_PREREQUISITE_AUDIT.md) records the still-disabled audit that maps this gate's prerequisites to current model, documentation, and test-only evidence. The audit is traceability evidence only: it does not authorize executable KAT introduction, provider selection, `productionProviderSelectable=true`, vault creation, vault unlock, vault persistence, production sync, or mainnet.

## Source And Tests

Production model:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderExecutableKatDecisionGate.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderExecutableKatPrerequisiteAudit.kt
```

Focused tests:

```text
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultProviderExecutableKatDecisionGateTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultProviderExecutableKatPrerequisiteAuditTest.kt
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/ProductionBackendAdapterSourceGuardTest.kt
```
