# Encrypted Local Vault Provider Selection Boundary

## Status

Skald Vault now has a disabled provider-selection and registry boundary for the future app-controlled encrypted local vault crypto provider.

This is selection policy only. It does not implement executable provider crypto, production KDF execution, production AEAD execution, key generation, Tink keyset creation or storage, raw key material persistence, vault container read/write, passphrase/PIN/biometric unlock UI, secure secret storage success, secure metadata persistence success, production sync, backend clients, signing, broadcasting, Tor transport, Nostr parsing, public endpoints, Skald-operated infrastructure, or mainnet.

The only runtime provider selected by this branch is:

```text
DisabledVaultCryptoProvider
```

The disabled provider rejects every operation and performs no crypto.

## Source Location

Production-safe provider-selection models and registry:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/VaultCryptoProviderSelection.kt
```

Disabled provider boundary:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/VaultCryptoProvider.kt
```

Selection tests:

```text
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultCryptoProviderSelectionTest.kt
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
| Production provider implementation exists | Blocked. |
| Production provider-level KATs passed | Blocked. |
| Android and desktop runtime coverage exists | Candidate evidence exists for dependency/test-provider paths, but not a production provider. |
| Argon2id parameters final for the platform | Blocked. Candidate tiers are non-final. |
| Dependency and license review complete | Candidate-level review complete for Tink plus Bouncy Castle only. |
| Keyset or raw-key handling approved | Blocked. |
| Secure secret storage approved | Blocked. |
| Secure metadata storage approved | Blocked. |
| Vault container/storage review complete | Blocked. |
| Redaction and failure-mode tests passed | Blocked for production provider. |
| Migration and corruption tests passed | Blocked. |
| Mainnet release-hardening approved | Blocked. |

Passing dependency-level KATs or test-provider KATs must not bypass these gates.

## Platform And Parameter Policy

The Argon2id candidate policy is explicitly non-final:

- Desktop candidate: 64 MiB, 3 passes, 1 lane, 32-byte output, Argon2 version 19.
- High-end Android candidate: 32 MiB, 3 passes, 1 lane, 32-byte output, Argon2 version 19.
- Mobile fallback/probe floor: 16 MiB, 2 passes, 1 lane, 32-byte output, Argon2 version 19.
- Android baseline coverage remains unresolved.

The provider-selection boundary treats unresolved Android baseline coverage and non-final KDF parameters as blockers for production selection.

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
4. final Argon2id parameter approval for the target platform/device class,
5. keyset/raw-key handling approval,
6. secure storage and secure metadata approval,
7. vault container/storage review,
8. redaction and failure-mode tests,
9. migration/corruption tests,
10. explicit mainnet release-hardening if mainnet is requested.

Until then, the provider-selection registry must keep selecting the disabled provider only.

## Next Step

The next focused branch should remain design/probe-only unless the user explicitly approves executable provider scope. Recommended decision point: either collect missing Android baseline Argon2id evidence or design a still-disabled production-provider skeleton with no storage, then run production-provider KATs in a separate validation branch before any vault container or persistence work.
