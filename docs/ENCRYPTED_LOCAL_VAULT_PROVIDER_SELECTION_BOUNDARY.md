# Encrypted Local Vault Provider Selection Boundary

## Status

Skald Vault now has a disabled provider-selection and registry boundary for the future app-controlled encrypted local vault crypto provider.

This is selection policy only. It does not implement executable provider crypto, production KDF execution, production AEAD execution, key generation, Tink keyset creation or storage, raw key material persistence, vault container read/write, passphrase/PIN/biometric unlock UI, secure secret storage success, secure metadata persistence success, production sync, backend clients, signing, broadcasting, Tor transport, Nostr parsing, public endpoints, Skald-operated infrastructure, or mainnet.

Manual Android calibration evidence capture is documented in [`ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md). Android compatibility and entropy policy is documented in [`ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md). Runtime randomness/provider checks are documented in [`ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md`](ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md). The Tink raw-key feasibility probes are documented in [`ENCRYPTED_LOCAL_VAULT_TINK_RAW_KEY_FEASIBILITY_PROBE.md`](ENCRYPTED_LOCAL_VAULT_TINK_RAW_KEY_FEASIBILITY_PROBE.md). The header commitment, canonical header encoding, key-separation label, and strict AAD construction contract is documented in [`ENCRYPTED_LOCAL_VAULT_HEADER_COMMITMENT_AAD_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_HEADER_COMMITMENT_AAD_CONTRACT.md). The v1 production-provider acceptance contract is documented in [`ENCRYPTED_LOCAL_VAULT_PRODUCTION_PROVIDER_ACCEPTANCE_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PRODUCTION_PROVIDER_ACCEPTANCE_CONTRACT.md). Current Pixel 10 Pro XL / Android 16 evidence remains high-end debug/instrumented timing evidence only and does not prove all-device performance. Low-end and mid-range model testing are no longer hard blockers for compatibility planning; supported Android baseline, runtime provider/primitive/randomness checks, and fail-closed vault-creation behavior are the compatibility gate.

The only runtime provider selected by this branch is:

```text
DisabledVaultCryptoProvider
```

The disabled provider rejects every operation and performs no crypto.

## Source Location

Production-safe provider-selection models and registry:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/VaultCryptoProviderSelection.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/AndroidVaultCompatibilityPolicy.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/RuntimeRandomnessProviderChecks.kt
```

Disabled provider boundary:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/VaultCryptoProvider.kt
```

Selection tests:

```text
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultCryptoProviderSelectionTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/RuntimeRandomnessProviderPolicyTest.kt
```

Source guards:

```text
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/ProductionBackendAdapterSourceGuardTest.kt
```

## Current Selection Result

`VaultCryptoProviderSelectionRegistry.select(...)` always returns `DisabledVaultCryptoProvider` as the selected runtime provider. A request for any future candidate still fails closed to the disabled provider.

The registry models candidate state and evidence, but it does not instantiate a Tink, Bouncy Castle, libsodium, JCA/JCE, platform, or storage-backed production provider.

| Candidate | Selection status | Reason |
| --- | --- | --- |
| Disabled provider | Selected for runtime fail-closed mode only | No crypto operation is executable. |
| Tink plus Bouncy Castle split stack | Blocked future candidate | Dependency-level KATs, Android/desktop runtime evidence, dependency review, and test-provider KAT harness evidence exist, but no production provider exists and storage gates are closed. |
| Lazysodium Java/Android | Rejected for current vault branch | Android packaging failed with duplicate JNA classes in the prior comparison. |
| IonSpin KMP libsodium | Deferred | Metadata/POM inspection only; package/runtime/KAT behavior remains unverified. |

No candidate is production-selectable.

## Evidence Model

The registry separates evidence into typed Skald-owned categories:

- dependency evidence,
- provider KAT evidence,
- platform runtime coverage evidence,
- Android compatibility and entropy policy evidence,
- runtime randomness/provider check evidence,
- Argon2id parameter policy evidence,
- secure storage and secure metadata readiness evidence,
- production approval gates,
- selection blockers.

The model does not expose provider-specific types. Public common models do not carry Tink, Bouncy Castle, JCA/JCE, libsodium, BDK, platform, file, settings, network, or process types.

## Dependency Evidence Treatment

Dependency-level KATs remain necessary evidence, not sufficient selection evidence.

Current Tink plus Bouncy Castle evidence:

- Desktop JVM Argon2id KAT passed against RFC 9106 section 5.3.
- Desktop JVM XChaCha20-Poly1305 KAT passed against the XChaCha draft appendix A.1.
- Android runtime Argon2id KAT passed on Pixel 10 Pro XL / Android 16.
- Android runtime XChaCha20-Poly1305 KAT passed on Pixel 10 Pro XL / Android 16.
- Android APK and Linux `.deb` package probes passed in prior branches.
- Candidate-level dependency/license/keyset/split-provider review is documented.

This evidence does not select a provider for production because the production provider implementation is absent and production provider-level KATs have not passed.

## Test-Provider Evidence Treatment

The test-only provider KAT harness proves that the Skald-owned `VaultCryptoProvider` request/result path can carry:

- Argon2id public KDF vectors,
- XChaCha20-Poly1305 public AEAD vectors,
- wrong associated-data failure,
- ciphertext/tag tampering failure,
- wrong-key failure,
- unsupported algorithm rejection,
- production nonce-bypass rejection,
- redacted diagnostics.

That evidence is test-scope only. It does not satisfy production provider selection because the harness is not a production provider, does not store keys, does not write vault records, and does not exercise production storage or lock/session lifecycle behavior.

## Production Approval Gates

A future executable provider cannot become selectable until every required gate is satisfied:

| Gate | Current state |
| --- | --- |
| Production provider acceptance contract satisfied | Blocked. The contract is modeled but incomplete for production implementation and persistence. |
| Production provider implementation exists | Blocked. |
| Production provider-level KATs passed | Blocked. |
| Android and desktop runtime coverage exists | Candidate evidence exists for dependency/test-provider paths, but not a production provider. |
| Runtime randomness/provider checks pass | Test-only Android/Linux availability probes are modeled; they do not prove entropy quality or approve production randomness. |
| Argon2id parameters final for the platform | Blocked. Candidate tiers are non-final, and bounded calibration at the shared v1 floor is not approved. |
| Dependency and license review complete | Candidate-level review complete for Tink plus Bouncy Castle only. |
| Header commitment policy approved and implemented | Blocked. Canonical header commitment is modeled but production execution is absent. |
| Canonical header encoding policy approved and implemented | Blocked. Deterministic header bytes and test vectors are modeled, but no production serializer exists. |
| Key-separation labels policy approved and implemented | Blocked. Stable labels are modeled, but no production key derivation or key-expansion primitive is approved. |
| Strict AAD contract approved and implemented | Blocked. Required AAD bindings are modeled, but no production AEAD path exists. |
| Passphrase encoding policy approved | Blocked. `unicode-nfc-utf8-no-controls-no-whitespace-v1` is modeled, but production validation is not wired. |
| Keyset or raw-key handling approved | Partially evidenced. The desktop/JVM test-scope result is `FEASIBLE_PUBLIC_RAW_KEY_API` and the Android instrumented test-scope result is `ANDROID_FEASIBLE_PUBLIC_RAW_KEY_API`, but no production provider implementation, production provider KATs, header commitment implementation, or storage review is approved. No keyset persistence is approved. |
| Secure secret storage approved | Blocked. |
| Secure metadata storage approved | Blocked. |
| Vault container/storage review complete | Blocked. |
| Redaction and failure-mode tests passed | Blocked for production provider. |
| Migration and corruption tests passed | Blocked. |
| Mainnet release-hardening approved | Blocked. |

Passing dependency-level KATs or test-provider KATs must not bypass these gates.

The current v1 contract pins the review direction to Bouncy Castle Argon2id, Tink XChaCha20-Poly1305, and OS SecureRandom with one explicit suite id. It also records why Skald is not using day-one provider agility: one pinned suite reduces audit surface, KAT matrix size, migration complexity, and accidental selectability risk. This is a prerequisite contract only; it cannot select the provider.

## Platform, Compatibility, And Parameter Policy

The Argon2id candidate policy is explicitly non-final:

- Desktop candidate: 64 MiB, 3 passes, 1 lane, 32-byte output, Argon2 version 19.
- High-end Android timing evidence: 32 MiB, 3 passes, 1 lane, 32-byte output, Argon2 version 19. This is historical probe evidence and does not satisfy the v1 floor.
- Mobile fallback/probe floor: 16 MiB, 2 passes, 1 lane, 32-byte output, Argon2 version 19.
- V1 acceptance-contract floor: 64 MiB, 3 passes, 1 lane, at least 16-byte salt, preferred 32-byte salt for new vaults, and 64-byte derived root material.
- Android supported-compatibility planning is modeled separately from final KDF parameter approval.
- Manual Android capture protocol exists for optional device-class and runtime-environment evidence; low-end and mid-range model evidence is no longer a compatibility hard blocker.

The provider-selection boundary treats non-final KDF parameters, missing bounded-calibration approval, missing production provider implementation, missing production provider-level KATs, disabled storage, and runtime provider/primitive/randomness checks as production gates. Unknown runtime provider or randomness state blocks provider planning. Satisfied compatibility planning still does not make a provider selectable. Test-only randomness probes generate only non-secret samples and are not entropy-quality proof. The desktop and Android raw-key feasibility probes satisfy only the raw-key construction question for the pinned public Tink API path; they do not approve production AEAD execution, vault storage, or provider selection.

## Storage And Mainnet Gates

Secure secret storage remains disabled/fail-closed. Secure metadata persistence remains disabled/fail-closed. The provider-selection boundary treats both as selection blockers for persistence use.

The registry does not create a vault container, does not write files, does not use SharedPreferences, does not use desktop config files, does not use OS keyrings, and does not serialize sensitive metadata.

Mainnet remains disabled. Any mainnet selection request adds a mainnet blocker and still returns the disabled provider.

## Source Confinement

The selection boundary imports no provider APIs and performs no crypto. Tink and Bouncy Castle calls remain confined to:

- platform compile probes,
- dependency-level KAT tests,
- Argon2id calibration probe tests,
- test-only provider KAT harnesses,
- artifact/source-guard tests.

BDK imports remain confined to approved platform probes and desktop-test validation code. The provider-selection boundary imports no BDK types and does not open network or process clients.

## Explicit Non-Capabilities

This boundary does not enable:

- executable production provider crypto,
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

## Acceptance Criteria Before Future Selection Work

The next provider-selection change may only consider a non-disabled provider after a separate branch provides evidence for:

1. executable production provider implementation behind Skald-owned interfaces,
2. provider-level KAT execution through that production provider,
3. Android and desktop runtime validation for the production provider,
4. supported Android compatibility/runtime provider/randomness checks where Android is in scope,
5. Linux runtime provider/randomness checks where Linux desktop is in scope,
6. final Argon2id parameter approval for the target platform,
7. header commitment implementation and key-commitment policy approval,
8. passphrase encoding policy approval and production validation,
9. raw-key handling approval through public supported Tink APIs or a separate human-approved alternative,
10. canonical header encoding implementation and test vectors,
11. key-separation implementation with reviewed key-expansion primitive,
12. strict AAD implementation and tamper/copy/replay tests,
13. secure storage and secure metadata approval,
14. vault container/storage review,
15. redaction and failure-mode tests,
16. migration/corruption tests,
17. explicit mainnet release-hardening if mainnet is requested.

Until then, the provider-selection registry must keep selecting the disabled provider only.

## Next Step

The next focused branch should remain design/probe-only unless the user explicitly approves executable provider scope. Recommended decision point: review runtime provider/primitive/randomness check evidence for supported Android and Linux paths or design a still-disabled production-provider skeleton with no storage, then run production-provider KATs in a separate validation branch before any vault container or persistence work.
