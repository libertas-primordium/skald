# Encrypted Local Vault Provider KAT Contract

## Status

Skald Vault now has a Skald-owned provider-level known-answer-test contract for the future app-controlled encrypted local vault `VaultCryptoProvider`.

A test-only provider KAT harness exists and is documented in [`ENCRYPTED_LOCAL_VAULT_TEST_PROVIDER_KAT_HARNESS.md`](ENCRYPTED_LOCAL_VAULT_TEST_PROVIDER_KAT_HARNESS.md). That harness proves the Skald-owned request/result path can carry the public KDF/AEAD vectors and required negative cases through a test-scope implementation on desktop and Android runtime.

This branch also adds a still-disabled integrated provider KAT harness in production source, `SkaldVaultV1StillDisabledProviderKatHarness`. It composes the existing passphrase policy, explicit-parameter Argon2id root derivation, HKDF subkey expansion, canonical header serialization, HMAC header commitment verification, strict AAD serialization, and Tink record AEAD building blocks with fixed non-secret fixtures. It has no selection, vault creation, storage, manifest, secure-storage, UI, wallet, or persistence API. It is not a selectable production provider implementation.

`SkaldVaultV1StillDisabledProviderFacade` now exists beside the harness as metadata/status evidence for the future provider boundary. It reports suite and policy ids, disabled reasons, remaining gates, and typed disabled results. It does not execute KATs, crypto, vault creation, unlock, storage, manifest, secure-storage, wallet, sync, backend, or persistence behavior, and it is not selectable.

`SkaldVaultV1ProviderCandidatePackagingPolicy` now exists as still-disabled provider-candidate packaging evidence. It names future Tink JVM, Bouncy Castle JVM, Android Keystore wrapper, platform OS CSPRNG, test-only deterministic, unknown, and unsupported candidates and records dependency category, source-set placement, review-gate, blocker, and disabled capability vocabulary. It does not implement or instantiate a provider, add or activate dependencies, run provider operations, run provider KATs, run randomness checks, run KDF/HKDF/HMAC/AEAD, generate keys, wrap keys, create or unlock a vault, persist storage, change provider selection, or approve mainnet.

`SkaldVaultV1ProviderDependencyBuildPolicy` now exists as still-disabled provider dependency build evidence. It records that the already-declared platform-scoped Tink/Bouncy dependencies are build-only evidence and that this branch adds no new dependency. The boundary is not KAT execution evidence: it does not import provider APIs in common production code, instantiate provider runtime, execute provider KATs, execute dependency KATs, run randomness checks, run KDF/HKDF/HMAC/AEAD, change provider selection, or approve provider promotion.

`SkaldVaultV1ProviderSelectionPromotionBlockersPolicy` now exists as still-disabled provider promotion evidence. It records that candidate description, dependency/build evidence, and KAT-related model evidence cannot promote a provider into implementation, factory, registry, test selectability, production selectability, provider operation authorization, creation/unlock/persistence integration, release validation, or mainnet approval. It is not KAT execution evidence and does not add dependencies, implement a provider, add a factory, enable a registry, run provider operations or KATs, run randomness checks, run KDF/HKDF/HMAC/AEAD, change provider selection, or approve provider promotion.

`SkaldVaultV1ProviderInterfaceContractAuditPolicy` now exists as still-disabled provider interface evidence. It audits provider-facing contracts for provider-neutral common boundaries, redaction, authorization gates, promotion blockers, source-set safety, and rejection of secrets, byte material, provider handles, crypto objects, executable operations, bypass paths, and selectable provider state. It is not KAT execution evidence and does not add dependencies, implement a provider, add a factory, enable a registry, instantiate provider code, run provider operations or KATs, run randomness checks, run KDF/HKDF/HMAC/AEAD, change provider selection, or approve provider promotion.

`SkaldVaultV1NonSelectableProviderSkeletonPolicy` now exists as still-disabled provider skeleton evidence. It models the future provider skeleton identity, source-set placement, dependency visibility, disabled operation surface list, diagnostics/redaction, acceptance gates, promotion blockers, and readiness-matrix interaction. It is not KAT execution evidence and does not implement `VaultCryptoProvider`, add a factory, enable a registry, instantiate provider code, run provider operations or KATs, use randomness, run KDF/HKDF/HMAC/AEAD, wrap keys, change provider selection, or approve provider promotion.

`SkaldVaultV1ProviderRegistryIsolationGuardPolicy` now exists as still-disabled provider registry isolation evidence. It confirms the current provider-selection registry remains isolated to `DisabledVaultCryptoProvider` and that provider KAT evidence, dependency/build evidence, candidate-packaging evidence, interface-audit evidence, promotion-blocker evidence, non-selectable skeleton evidence, authorization/readiness matrix evidence, warning-only evidence, user consent, test-only evidence, and release/mainnet evidence cannot become non-disabled registry entries. It is not KAT execution evidence and does not implement a provider, add a factory, enable a non-disabled registry, instantiate provider code, run provider operations or KATs, run randomness checks, run KDF/HKDF/HMAC/AEAD, change provider selection, or approve provider promotion.

Provider selection is documented in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md). Runtime randomness/provider checks are documented in [`ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md`](ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md). The detailed v1 vault container, manifest, storage, stale-record, atomicity, crash-recovery, secure-storage boundary, and rollback-limitation contract is documented in [`ENCRYPTED_LOCAL_VAULT_CONTAINER_MANIFEST_STORAGE_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_CONTAINER_MANIFEST_STORAGE_CONTRACT.md). The selection registry treats dependency-level KATs, test-only provider KATs, randomized AEAD building-block tests, in-memory container/manifest/stale-record building-block tests, model-only storage contracts, and test-only runtime randomness probes as insufficient for production selection and returns only the disabled provider.

This is still-disabled integration and policy scaffolding only. It does not implement provider selectability, provider-selectable vault creation, production unlock, key generation, runtime random vault material generation, Tink keyset creation or storage, raw key material persistence, file-backed vault container read/write, manifest file/storage read/write, passphrase/PIN/biometric unlock UI, secure secret storage success, secure metadata persistence success, production sync, backend clients, signing, broadcasting, Tor transport, Nostr parsing, public endpoints, Skald-operated infrastructure, or mainnet.

Runtime behavior remains fail-closed:

- `DisabledVaultCryptoProvider` rejects every modeled provider operation.
- `VaultCryptoProviderSelectionRegistry` selects only the disabled provider and blocks all future candidates.
- Provider candidate packaging evidence is modeled but cannot authorize provider KAT execution or provider promotion.
- Provider selection promotion blocker evidence is modeled but cannot authorize provider implementation, factory, registry, selectability, operation authorization, KAT approval, or production promotion.
- Provider interface contract audit evidence is modeled but cannot accept runtime material, execute provider operations, run KATs, bypass authorization, promote providers, change selection, or approve production runtime.
- Non-selectable provider skeleton evidence is modeled but cannot register, select, instantiate, or execute a provider and cannot authorize provider KAT execution.
- Provider registry isolation guard evidence is modeled but cannot add non-disabled registry entries, factories, provider constructors, provider handles, crypto objects, provider operation calls, KAT execution, randomness, KDF/AEAD/HKDF/HMAC, vault creation/unlock/persistence, provider promotion, or production runtime.
- Provider-level KAT requirements are modeled, test-only provider KATs execute in test source sets, and the still-disabled integrated harness executes the v1 deterministic and randomized AEAD provider-level KATs with fixed non-secret fixtures.
- The still-disabled provider facade reports KAT/building-block evidence as metadata only; it does not make that evidence selectable.
- The v1 provider-level KAT strategy is now explicit: deterministic vectors are required for passphrase, Argon2id, canonical header, HKDF, HMAC, and strict AAD; record AEAD is validated behaviorally because Tink chooses XChaCha20-Poly1305 nonces internally.
- Stale-record and rollback handling is modeled as a future manifest/storage responsibility, not as an AEAD property.
- Selectable production-provider KATs still cannot execute because no selectable production provider exists.
- Dependency-level KAT evidence does not satisfy provider-level KAT approval.
- `SecureSecretStorage` remains disabled.
- `SecureWalletMetadataRepository` remains disabled.
- `EncryptedVaultReadinessPolicy` remains not implemented/not ready.
- Production persistence remains disabled.
- Production sync remains disabled.
- Mainnet remains disabled.

## Source Location

Production-safe common contract models:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/VaultCryptoProvider.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1StillDisabledProviderKatHarness.kt
```

Common tests:

```text
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultCryptoProviderBoundaryTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultCryptoProviderKatContractTest.kt
```

Test-only provider harnesses:

```text
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/VaultCryptoTestProviderKatHarnessTest.kt
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/SkaldVaultV1StillDisabledProviderKatHarnessTest.kt
composeApp/src/androidInstrumentedTest/kotlin/com/libertasprimordium/skald/VaultCryptoAndroidTestProviderKatHarnessTest.kt
composeApp/src/androidInstrumentedTest/kotlin/com/libertasprimordium/skald/VaultCryptoAndroidStillDisabledProviderKatHarnessTest.kt
```

Source guards:

```text
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/ProductionBackendAdapterSourceGuardTest.kt
```

## Distinction From Dependency-Level KATs

Dependency-level KATs prove that selected library APIs can reproduce public vectors on a runtime. They have passed for the current candidate stack:

- Bouncy Castle Argon2id against RFC 9106 section 5.3 on desktop JVM and Android runtime.
- Tink XChaCha20-Poly1305 against the XChaCha draft appendix A.1 on desktop JVM and Android runtime.

Provider-level KATs must prove that a future executable Skald-owned provider boundary uses those libraries correctly. That includes passphrase normalization, typed Argon2id parameters, canonical header bytes, HKDF info construction, HMAC header commitment, strict AAD serialization, randomized AEAD behavior, redacted failures, algorithm rejection, platform runtime behavior, and provider-owned result/error mapping.

Because no selectable production provider exists, production provider-level KATs do not make the provider selectable in this branch. The disabled provider reports `ContractModeledProviderMissing`, `DependencyLevelKatsDoNotSatisfyProviderContract`, and `ExecutableProviderMissing`.

The test-only harness is separate evidence: it runs through `VaultCryptoProvider.validateKat(...)` and returns redacted `VaultCryptoProviderKatEvidence` with `TestHarnessOnly` scope. That proves interface expressiveness and failure-mode coverage, not production provider approval.

The still-disabled integrated provider KAT harness is separate evidence from both dependency-level KATs and the older test-only provider harness. It executes the v1 building-block order with fixed non-secret fixtures and returns only typed, redacted stage/evidence results. It does not expose `VaultCryptoProvider`, cannot be selected by `VaultCryptoProviderSelectionRegistry`, and has no storage or vault lifecycle API.

The still-disabled provider facade is also separate from provider-level KAT execution. It can report that still-disabled KAT evidence exists and that remaining gates are blocked, but KAT success, facade existence, and calibration evidence do not imply provider selectability or storage approval.

Argon2id calibration evidence is also separate from provider-level KAT evidence. The still-disabled calibration policy building block now models the 64 MiB / t=3 / p=1 floor, 64-byte root output, deterministic candidate selection, about-1-second preferred target, about-2-second acceptable target, fail-closed floor execution failure, stored-parameter authority, and no silent downgrade. Manual Android calibration capture is documented in [`ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md) and records timing context for future parameter policy only. Neither the calibration policy building block nor manual capture evidence satisfies dependency-level KATs, test-provider KATs, production provider KATs, final production calibration approval, or provider selection. Android compatibility and entropy gates are modeled separately in [`ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md): low-end and mid-range model testing are not hard compatibility blockers, but supported OS baseline checks, runtime provider/primitive checks, approved cryptographic randomness, and fail-closed vault creation are required. Runtime randomness/provider probes are availability checks only; small non-secret samples are not entropy-quality proof and do not approve production randomness.

## Contract Registry

The provider contract registry models required categories:

| Category | Requirement |
| --- | --- |
| Deterministic provider vectors | Passphrase policy, Argon2id root material, canonical header bytes, HKDF info/output, HMAC header commitment, and strict AAD bytes must match documented fixed non-secret vectors. |
| Randomized AEAD behavior | XChaCha20-Poly1305 must round trip fixed non-secret plaintext with deterministic AAD and fail closed for wrong AAD, wrong key, tampered ciphertext/tag, and wrong vault/record/header context without requiring fixed ciphertext hex. |
| Legacy dependency/test-harness AEAD vector | Fixed-nonce public vectors may remain dependency or test-harness evidence only; they do not replace provider-level behavioral AEAD KATs. |
| Algorithm policy | Unsupported algorithms must be rejected, PBKDF2 must not become the production default, and scrypt must remain unselected unless explicitly reviewed. |
| Nonce policy | Production callers must not provide arbitrary nonces; fixed nonces may exist only in test/KAT-only paths. |
| Redaction | Diagnostics and errors must not expose plaintext, derived keys, unlock material, secret keys, or decrypted payloads. |
| Platform coverage | Desktop runtime, Android runtime, and future release-like runtime coverage are required before provider approval. |
| Storage separation | Passing provider KATs does not approve vault container or storage behavior. |

The code-level registry uses Skald-owned vector identities and categories only. It does not embed vector bytes, provider types, crypto imports, storage paths, or wallet metadata.

## V1 Provider-Level KAT Strategy

The v1 production provider must validate the integrated provider without relying on deterministic ciphertext. Tink XChaCha20-Poly1305 uses randomized nonces through the public AEAD API path, so fixed ciphertext hex is not a provider-level requirement unless a public, supported deterministic nonce test mode is separately approved later. The approved v1 strategy is:

- deterministic vectors for the deterministic parts of the pipeline,
- deterministic strict AAD bytes,
- behavioral AEAD checks for randomized record encryption,
- integrated order checks proving header commitment happens before record decrypt,
- platform execution on desktop/JVM and Android before selectability,
- storage and manifest review as separate gates after provider KATs.

Required deterministic vector KATs:

- passphrase policy normalization vector,
- Argon2id fixed non-secret root-material fixture,
- canonical header byte vector,
- HKDF info byte vectors,
- HKDF header commitment key vector,
- HKDF record AEAD key vector,
- HMAC header commitment vector,
- strict AAD byte vector.

Required randomized AEAD behavioral KATs:

- encrypt fixed non-secret plaintext with fixed non-secret record AEAD key and deterministic AAD,
- decrypt the resulting ciphertext with the same key and AAD,
- assert plaintext round trip succeeds,
- assert ciphertext is not treated as deterministic,
- assert wrong AAD fails,
- assert wrong key fails,
- assert tampered ciphertext fails,
- assert tampered tag fails,
- assert wrong vault id in AAD fails,
- assert wrong record id in AAD fails,
- assert wrong record type in AAD fails,
- assert wrong record version/counter in AAD fails,
- assert wrong provider suite id in AAD fails,
- assert wrong header commitment context in AAD fails.

This strategy does not weaken the AEAD requirement. It recognizes that the ciphertext includes nonce-dependent randomness while still requiring deterministic inputs, deterministic AAD, successful round trip, and negative tamper/mismatch behavior.

## Integrated Verification-Order KAT Strategy

Future provider-level KATs must prove the full provider order:

1. Validate passphrase policy.
2. Derive Argon2id root material from the fixed non-secret fixture.
3. Derive HKDF subkeys.
4. Canonicalize header bytes.
5. Verify HMAC-SHA-256 header commitment.
6. Only then construct or use record AEAD.
7. Serialize strict AAD.
8. Decrypt record.
9. Reject record decrypt attempts when header commitment verification fails.

These KATs now execute through `SkaldVaultV1StillDisabledProviderKatHarness` with fixed non-secret fixtures. The tests assert the deterministic passphrase, Argon2id, HKDF, canonical header, HMAC, and strict AAD vectors; assert randomized AEAD round trip and mismatch/tamper failures; and assert that header commitment failure exits before the record AEAD stage. The harness remains still-disabled evidence only: no selectable provider, vault creation, unlock flow, manifest/storage layer, secure storage, or persistence path is added.

## Stale-Record And Rollback Manifest Contract

The current strict AAD building block binds record version/counter into AAD. This detects cross-vault, cross-record, cross-type, and cross-version substitution for a given ciphertext/AAD context, but it does not prove freshness by itself.

Full stale-record and rollback handling is a future manifest/storage responsibility. A future local manifest or vault index must:

- track the latest trusted record version/counter per record id,
- be integrity-protected,
- bind vault id, provider suite id, header commitment context, manifest policy id/version, and record namespace,
- update atomically with record writes or define a crash-safe recovery strategy,
- reject or quarantine records with a lower version/counter than the latest trusted local manifest state,
- reject or quarantine duplicate record ids with conflicting latest counters,
- define conflict handling before sync or import behavior is enabled.

For v1 local-only persistence, Skald must distinguish:

- intra-vault substitution detection via AAD,
- stale-record detection against the latest trusted local manifest state,
- full storage rollback resistance, which is not claimed unless an external anchor, trusted monotonic counter, append-only log, remote checkpoint, or other anti-rollback anchor is designed.

The full container/manifest/storage contract is now specified in [`ENCRYPTED_LOCAL_VAULT_CONTAINER_MANIFEST_STORAGE_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_CONTAINER_MANIFEST_STORAGE_CONTRACT.md). Still-disabled in-memory vault container and manifest parser/writer building blocks now validate caller-supplied byte arrays and fixed non-secret fixtures, and the local stale-record decision policy classifies candidate records relative to an in-memory manifest. This branch does not implement a manifest file/storage reader, manifest file/storage writer, storage index, persistence path, conflict resolver, sync path, crash-safe atomic update strategy, or anti-rollback anchor. Provider selectability remains blocked until manifest/storage stale-record enforcement, storage integration, and crash/corruption/partial-write behavior are implemented and reviewed.

## Legacy Dependency/Test-Harness KAT Requirements

The current dependency-level and test-only harness evidence includes fixed public vectors through the Skald-owned request/result model:

- Argon2id KDF:
  - Argon2id only.
  - Argon2 version 19.
  - explicit memory units.
  - explicit pass count.
  - explicit lane count.
  - explicit output length.
  - official public vector requirement.
- XChaCha20-Poly1305 AEAD:
  - 24-byte nonce.
  - associated data authenticated by the provider.
  - public vector requirement.
  - fixed nonce allowed only through a test/KAT-only path.

The current dependency-level and test-harness vectors are necessary evidence, but they are not provider approval because they do not exercise the future full Skald-owned production provider pipeline or manifest/storage policy. They also do not replace the randomized AEAD behavioral provider KAT strategy above, and their fixed-nonce vector shape must not be copied into ordinary production record encryption.

## Negative KAT Requirements

Future provider implementation must also pass negative KATs. The current test-only harness exercises the following negative categories through the Skald-owned KAT request/result path:

- wrong associated data fails closed,
- modified ciphertext fails closed,
- modified authentication tag fails closed,
- wrong key fails closed,
- unsupported algorithm rejected,
- production nonce policy cannot be bypassed,
- production caller cannot provide arbitrary nonce,
- PBKDF2 default remains rejected,
- scrypt fallback remains not selected unless explicitly reviewed.

Negative KAT failures must return Skald-owned blocked or authentication-failure results with redacted diagnostics. They must not log or return plaintext, key material, nonce material, derived bytes, decrypted payloads, or provider internals.

## Platform Coverage Requirements

Future provider-level KAT approval requires:

- desktop runtime provider KAT execution,
- Android runtime provider KAT execution,
- release-like runtime validation before production persistence or mainnet relevance.

Android test APK assembly alone is not enough. Desktop JVM dependency KATs and Android dependency KATs remain evidence for library behavior, not proof that the provider boundary is correct.

## Redaction Requirements

Provider diagnostics must remain non-secret by construction:

- no plaintext in errors,
- no derived key bytes,
- no passphrase or unlock bytes,
- no secret key bytes,
- no decrypted payloads,
- no wallet labels, UTXO labels, transaction notes, real observed addresses, real outpoints, backend credentials, Nostr private material, or sensitive metadata.

The disabled provider already uses redacted handles and safe status codes. A future executable provider must keep the same public model shape and add provider-level redaction tests before approval.

## Acceptance Gates Before Provider Approval

Before a future executable provider can be approved for vault implementation:

1. Final Argon2id parameter policy must be approved per supported platform, with Android compatibility based on supported OS baseline, runtime provider/primitive/randomness checks, the still-disabled calibration policy floor/no-downgrade/fail-closed evidence, and fail-closed behavior rather than mandatory exhaustive device-class coverage.
2. The executable provider must expose no Tink, Bouncy Castle, JCA/JCE, BDK, platform, file, settings, network, or process types through public common models.
3. Deterministic vector KATs must pass for passphrase policy, Argon2id, canonical header bytes, HKDF info/output, HMAC header commitment, and strict AAD.
4. Randomized AEAD behavioral KATs must pass without requiring fixed ciphertext hex.
5. Integrated verification-order KATs must prove header commitment before record decrypt and reject decrypt when commitment verification fails.
6. Provider-level KATs must pass on desktop and Android runtime.
7. Negative misuse KATs must pass and fail closed.
8. Redaction tests must pass.
9. Unsupported algorithm and nonce-policy-bypass tests must pass.
10. Provider errors must be Skald-owned and redacted.
11. Tink keyset or raw AEAD key material handling must be reviewed.
12. Lock/session lifecycle behavior must be tested.
13. Vault container and storage approval must remain separate.
14. Stale-record manifest/storage policy, atomicity, crash recovery, and rollback claims must be reviewed before persistence.

Runtime randomness availability must be reviewed as a provider-selection and vault-creation gate, but it is not itself a provider KAT and must not be used to bypass provider KAT requirements.

## Explicit Non-Capabilities

This contract does not enable:

- executable provider crypto,
- provider-selectable KDF execution,
- production AEAD execution,
- fake encryption,
- key generation,
- Tink keyset creation or persistence,
- raw key material persistence,
- file-backed vault container read/write,
- passphrase, PIN, biometric, or unlock UI,
- secure secret storage success,
- secure metadata persistence success,
- production observation/address-index/UTXO/label/note/wallet-history persistence,
- production sync,
- production backend clients,
- BDK production persistence,
- Nostr parsing,
- Lightning, Cashu, or Payjoin behavior,
- Tor transport,
- public backend defaults,
- Skald-operated infrastructure,
- mainnet.

## Test Harness Evidence

The test-only harness currently covers:

- Argon2id RFC 9106 section 5.3 through `VaultCryptoProvider.validateKat`.
- XChaCha20-Poly1305 draft appendix A.1 through `VaultCryptoProvider.validateKat`.
- wrong associated data,
- modified ciphertext,
- modified authentication tag,
- wrong key,
- unsupported algorithm rejection,
- production nonce-bypass rejection,
- caller-provided production nonce rejection,
- PBKDF2 default rejection,
- scrypt fallback non-selection,
- redacted diagnostic/evidence behavior,
- desktop runtime coverage,
- Android runtime coverage.

The Android connected run executed the expanded instrumented suite on Pixel 10 Pro XL / Android 16 and reported `Starting 9 tests` and `Finished 9 tests`. This result is test-scope evidence only.

## Current Model Status

`VaultCryptoProvider.kt` now models:

- provider KAT vector identities,
- provider KAT categories,
- provider KAT blockers,
- provider KAT contract status,
- provider KAT validation result shape,
- provider KAT request and redacted evidence shape,
- positive, negative, redaction, platform, nonce-policy, algorithm-policy, and storage-separation requirements.

`EncryptedVaultReadinessPolicy` records `ProviderKatContractModeled` as candidate-reviewed only and records the test-only provider harness plus still-disabled integrated provider KAT harness as non-production capabilities. Production persistence remains disabled, and mainnet remains disabled.

`EncryptedVaultReadinessPolicy` also records `ProviderLevelKatStrategyContractModeled`, `RandomizedAeadBehavioralKatPolicyModeled`, `IntegratedVerificationOrderKatPolicyModeled`, `StillDisabledProviderIntegrationHarnessImplementedAndTested`, `ProviderLevelKatsExecutedInStillDisabledHarness`, `RandomizedAeadBehavioralKatsExecutedInStillDisabledHarness`, `IntegratedVerificationOrderKatsExecutedInStillDisabledHarness`, and `StaleRecordManifestPolicyModeled`. It records the still-disabled harness as not selectable, and still records manifest/storage stale-record integration and manifest/storage atomicity review as blockers.

`VaultCryptoDependencyProbeCatalog` records `ProviderKatContractModeled`, `ProviderLevelKatStrategyContractModeled`, `RandomizedAeadBehavioralKatPolicyModeled`, `IntegratedVerificationOrderKatPolicyModeled`, `StaleRecordManifestPolicyModeled`, `TestOnlyProviderKatHarnessPresent`, `StillDisabledProviderIntegrationHarnessTested`, `ProviderLevelKatsExecutedInStillDisabledHarness`, `RandomizedAeadBehavioralKatsExecutedInStillDisabledHarness`, and `IntegratedVerificationOrderKatsExecutedInStillDisabledHarness` for the Tink plus Bouncy Castle candidate, but it also records that the still-disabled harness is not selectable, manifest/storage stale-record integration is missing, and manifest/storage atomicity review is missing for the production provider path. The candidate remains dependency-reviewed, provider-contract-modeled, and harness-validated only; it is not production-approved.

`VaultCryptoProviderSelectionRegistry` records Tink plus Bouncy Castle as a blocked future candidate. Dependency-level KAT evidence and test-provider KAT evidence are retained as evidence fields but do not satisfy production provider selection.

## Next Step

The next focused branch should remain design/probe-only unless the user explicitly approves executable provider work. Recommended next decision point: review runtime provider/primitive/randomness checks for supported Android and Linux paths, or decide whether to design a disabled executable production-provider skeleton with no storage. Do not proceed to file-backed vault container read/write or persistence from the test harness.
