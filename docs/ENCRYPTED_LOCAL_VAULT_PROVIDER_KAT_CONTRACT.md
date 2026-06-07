# Encrypted Local Vault Provider KAT Contract

## Status

Skald Vault now has a Skald-owned provider-level known-answer-test contract for the future app-controlled encrypted local vault `VaultCryptoProvider`.

A test-only provider KAT harness now exists and is documented in [`ENCRYPTED_LOCAL_VAULT_TEST_PROVIDER_KAT_HARNESS.md`](ENCRYPTED_LOCAL_VAULT_TEST_PROVIDER_KAT_HARNESS.md). That harness proves the Skald-owned request/result path can carry the public KDF/AEAD vectors and required negative cases through a test-scope implementation on desktop and Android runtime. It is not a production provider implementation.

This is contract and policy scaffolding only. It does not implement executable provider crypto, production KDF execution, AEAD execution, key generation, Tink keyset creation or storage, raw key material persistence, vault container read/write, passphrase/PIN/biometric unlock UI, secure secret storage success, secure metadata persistence success, production sync, backend clients, signing, broadcasting, Tor transport, Nostr parsing, public endpoints, Skald-operated infrastructure, or mainnet.

Runtime behavior remains fail-closed:

- `DisabledVaultCryptoProvider` rejects every modeled provider operation.
- Provider-level KAT requirements are modeled, and test-only provider KATs execute in test source sets.
- Production provider-level KATs still cannot execute because no production provider exists.
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
```

Common tests:

```text
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultCryptoProviderBoundaryTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultCryptoProviderKatContractTest.kt
```

Test-only provider harnesses:

```text
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/VaultCryptoTestProviderKatHarnessTest.kt
composeApp/src/androidInstrumentedTest/kotlin/com/libertasprimordium/skald/VaultCryptoAndroidTestProviderKatHarnessTest.kt
```

Source guards:

```text
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/ProductionBackendAdapterSourceGuardTest.kt
```

## Distinction From Dependency-Level KATs

Dependency-level KATs prove that selected library APIs can reproduce public vectors on a runtime. They have passed for the current candidate stack:

- Bouncy Castle Argon2id against RFC 9106 section 5.3 on desktop JVM and Android runtime.
- Tink XChaCha20-Poly1305 against the XChaCha draft appendix A.1 on desktop JVM and Android runtime.

Provider-level KATs must prove that a future executable Skald-owned provider boundary uses those libraries correctly. That includes typed parameter selection, associated-data construction, nonce policy, redacted failures, algorithm rejection, platform runtime behavior, and provider-owned result/error mapping.

Because no executable production provider exists, production provider-level KATs do not pass in this branch. The disabled provider reports `ContractModeledProviderMissing`, `DependencyLevelKatsDoNotSatisfyProviderContract`, and `ExecutableProviderMissing`.

The test-only harness is separate evidence: it runs through `VaultCryptoProvider.validateKat(...)` and returns redacted `VaultCryptoProviderKatEvidence` with `TestHarnessOnly` scope. That proves interface expressiveness and failure-mode coverage, not production provider approval.

## Contract Registry

The provider contract registry models required categories:

| Category | Requirement |
| --- | --- |
| Positive KDF vector | Argon2id version 19 with explicit memory, passes, lanes, output length, and an official public vector such as RFC 9106 section 5.3. |
| Positive AEAD vector | XChaCha20-Poly1305 with a 24-byte nonce, associated data, and a public vector such as XChaCha draft appendix A.1. |
| Negative AEAD misuse | Wrong associated data, modified ciphertext, modified tag, and wrong key must fail closed. |
| Algorithm policy | Unsupported algorithms must be rejected, PBKDF2 must not become the production default, and scrypt must remain unselected unless explicitly reviewed. |
| Nonce policy | Production callers must not provide arbitrary nonces; fixed nonces may exist only in test/KAT-only paths. |
| Redaction | Diagnostics and errors must not expose plaintext, derived keys, unlock material, secret keys, or decrypted payloads. |
| Platform coverage | Desktop runtime, Android runtime, and future release-like runtime coverage are required before provider approval. |
| Storage separation | Passing provider KATs does not approve vault container or storage behavior. |

The code-level registry uses Skald-owned vector identities and categories only. It does not embed vector bytes, provider types, crypto imports, storage paths, or wallet metadata.

## Positive KAT Requirements

Future provider implementation must pass positive KATs through the Skald-owned interface. The current test-only harness already exercises these through the same request/result model:

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

The current dependency-level vectors are necessary evidence, but they are not provider approval because they do not exercise the Skald-owned request/result/error boundary.

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

1. Final Argon2id parameter policy must be approved per platform/device class.
2. The executable provider must expose no Tink, Bouncy Castle, JCA/JCE, BDK, platform, file, settings, network, or process types through public common models.
3. Positive KDF and AEAD provider KATs must pass on desktop and Android runtime.
4. Negative misuse KATs must pass and fail closed.
5. Redaction tests must pass.
6. Unsupported algorithm and nonce-policy-bypass tests must pass.
7. Provider errors must be Skald-owned and redacted.
8. Tink keyset or raw AEAD key material handling must be reviewed.
9. Lock/session lifecycle behavior must be tested.
10. Vault container and storage approval must remain separate.

## Explicit Non-Capabilities

This contract does not enable:

- executable provider crypto,
- production KDF execution,
- production AEAD execution,
- fake encryption,
- key generation,
- Tink keyset creation or persistence,
- raw key material persistence,
- vault container read/write,
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

`EncryptedVaultReadinessPolicy` records `ProviderKatContractModeled` as candidate-reviewed only and records the test-only provider harness as non-production capability. `ProviderBoundaryKnownAnswerVectorsPassed` remains absent, `ProviderKnownAnswerVectorsMissing` remains a blocker, production persistence remains disabled, and mainnet remains disabled.

`VaultCryptoDependencyProbeCatalog` records `ProviderKatContractModeled` and `TestOnlyProviderKatHarnessPresent` for the Tink plus Bouncy Castle candidate, but it also records `ProviderLevelKatExecutionMissing` for the production provider path. The candidate remains dependency-reviewed, provider-contract-modeled, and test-harness-validated only; it is not production-approved.

## Next Step

The next focused branch should remain design/probe-only unless the user explicitly approves executable provider work. Recommended next decision point: decide whether to design a disabled executable production-provider skeleton with no storage, or collect remaining Android baseline Argon2id calibration evidence before provider implementation. Do not proceed to vault container read/write or persistence from the test harness.
