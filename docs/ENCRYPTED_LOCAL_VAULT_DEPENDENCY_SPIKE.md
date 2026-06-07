# Encrypted Local Vault Dependency Spike

## Status

This document records the focused dependency spike for the future app-controlled encrypted local vault primitive stack.

This is a dependency evaluation and build/probe record only. It does not implement encryption, key derivation, a vault container, secure secret storage, secure metadata persistence, production sync, production observation persistence, address index persistence, UTXO persistence, signing, broadcasting, Tor transport, public endpoint defaults, Skald-operated infrastructure, or mainnet.

Runtime behavior remains fail-closed:

- `SecureSecretStorage` is disabled.
- `SecureWalletMetadataRepository` is disabled.
- `EncryptedVaultReadinessPolicy` reports the vault as not implemented/not ready.
- Production sync remains disabled.
- Production secret and sensitive metadata persistence remain disabled.

The follow-up libsodium comparison is documented in [`ENCRYPTED_LOCAL_VAULT_LIBSODIUM_COMPARISON.md`](ENCRYPTED_LOCAL_VAULT_LIBSODIUM_COMPARISON.md). That comparison rejects Lazysodium Java/Android for the current vault branch because Android APK packaging failed at `checkDebugDuplicateClasses` with duplicate JNA classes, and it defers IonSpin KMP libsodium pending an isolated packaging/KAT spike. The follow-up Tink/Bouncy dependency, license, keyset/storage, Bouncy Castle Argon2id API, and split-provider review is documented in [`ENCRYPTED_LOCAL_VAULT_DEPENDENCY_REVIEW.md`](ENCRYPTED_LOCAL_VAULT_DEPENDENCY_REVIEW.md). The disabled Skald-owned provider boundary is documented in [`ENCRYPTED_LOCAL_VAULT_CRYPTO_PROVIDER_BOUNDARY.md`](ENCRYPTED_LOCAL_VAULT_CRYPTO_PROVIDER_BOUNDARY.md), the disabled provider-selection boundary is documented in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md), the provider-level KAT contract is documented in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md), and the test-only provider KAT harness is documented in [`ENCRYPTED_LOCAL_VAULT_TEST_PROVIDER_KAT_HARNESS.md`](ENCRYPTED_LOCAL_VAULT_TEST_PROVIDER_KAT_HARNESS.md). Argon2id calibration policy/probe planning is documented in [`ENCRYPTED_LOCAL_VAULT_ARGON2ID_CALIBRATION.md`](ENCRYPTED_LOCAL_VAULT_ARGON2ID_CALIBRATION.md), and non-final candidate Argon2id parameter tiers are documented in [`ENCRYPTED_LOCAL_VAULT_ARGON2ID_PARAMETER_POLICY.md`](ENCRYPTED_LOCAL_VAULT_ARGON2ID_PARAMETER_POLICY.md).

## Probe Scope

The spike answers a narrow build question:

```text
Can a pinned dependency candidate expose Argon2id and XChaCha20-Poly1305 APIs on Android and Linux desktop packaging without adding native-library packaging risk or enabling vault storage?
```

It does not answer all implementation questions that remain for a real vault:

- Final KDF parameter approval.
- Future executable production-provider-boundary known-answer-vector validation through the provider-level KAT contract. The current test-only provider harness is interface evidence only.
- Vault envelope implementation.
- Key hierarchy implementation.
- Secure memory/session lifecycle behavior.
- Migration/corruption behavior.
- Redaction and failure-mode coverage for real encrypted records.

## Candidate Summary

| Candidate | Spike result | Reason |
| --- | --- | --- |
| Tink AEAD plus Bouncy Castle Argon2id | Candidate reviewed; not production-approved | Provides Tink XChaCha20-Poly1305 API and Bouncy Castle Argon2id API with pinned JVM/Android artifacts, no native library entries observed in resolved JARs, desktop JVM public KAT validation, Android instrumented runtime KAT validation on Pixel 10 Pro XL / Android 16, and candidate-level dependency/license/keyset/split-provider review. Earlier connected-device failures were install/device-targeting environment blockers, not KAT failures. |
| Lazysodium Java/Android | Rejected for current vault branch | One primitive family covers Argon2id and XChaCha20-Poly1305 APIs, but Android packaging failed with duplicate JNA classes when `lazysodium-android:5.2.0` was added. |
| IonSpin KMP libsodium binding | Deferred after comparison | Exact artifacts exist, but Kotlin metadata compatibility, JNA/native-loader behavior, Android ABI packaging, Linux `.deb` behavior, and KAT mapping remain unverified. |
| Bouncy Castle only | Insufficient as primary stack | Provides Argon2id and ChaCha20-Poly1305-family APIs, but did not satisfy the preferred XChaCha20-Poly1305 record-AEAD target in this pass. |
| Platform crypto only | Rejected as default vault stack | Does not provide a cross-platform memory-hard Argon2id default or the preferred XChaCha20-Poly1305 record AEAD. |
| Kotlin Multiplatform crypto candidate | Deferred | Requires review for maintenance, audit history, Android/Linux packaging, known-answer vectors, and native dependencies before selection. |

## Probe Dependencies

Pinned dependency aliases:

```text
com.google.crypto.tink:tink-android:1.21.0
com.google.crypto.tink:tink:1.21.0
org.bouncycastle:bcprov-jdk18on:1.84
```

Source-set placement:

- `androidMain`: `tink-android` plus `bcprov-jdk18on`.
- `desktopMain`: `tink` plus `bcprov-jdk18on`.
- `commonMain`: no crypto dependency.

The probe adds platform compile-probe objects only:

```text
composeApp/src/androidMain/kotlin/com/libertasprimordium/skald/security/AndroidVaultCryptoDependencyCompileProbe.kt
composeApp/src/desktopMain/kotlin/com/libertasprimordium/skald/security/DesktopVaultCryptoDependencyCompileProbe.kt
```

These files reference candidate classes by type so Android and desktop compilation fail if the expected APIs disappear. They include Tink's explicit-nonce XChaCha class only as a probe surface for public KAT validation. They do not derive production keys, generate keys, encrypt wallet data, decrypt wallet data, write files, call platform key stores, or persist anything.

## API Presence

The probe confirms these API classes compile on platform source sets:

```text
com.google.crypto.tink.aead.XChaCha20Poly1305Key
com.google.crypto.tink.aead.internal.InsecureNonceXChaCha20Poly1305
org.bouncycastle.crypto.generators.Argon2BytesGenerator
org.bouncycastle.crypto.modes.ChaCha20Poly1305
```

The desktop artifact probe also loads those class names with `Class.forName()` as a runtime classpath check.

The KAT validation documented in [`ENCRYPTED_LOCAL_VAULT_KAT_VALIDATION.md`](ENCRYPTED_LOCAL_VAULT_KAT_VALIDATION.md) adds cryptographic correctness evidence for public vectors on desktop JVM and Android runtime. The Android runtime run executed two instrumented tests on Pixel 10 Pro XL / Android 16 after explicit ADB serial targeting; the earlier install-signature conflict and stale wireless-debugging mDNS target were environment blockers that occurred before the successful run. Durable docs intentionally omit the concrete device serial/IP:port.

## Artifact And Packaging Observations

Local Gradle artifact inspection found:

- `tink-1.21.0.jar`: approximately 2.7 MiB.
- `tink-android-1.21.0.jar`: approximately 3.2 MiB.
- `bcprov-jdk18on-1.84.jar`: approximately 8.6 MiB.
- Desktop Tink runtime transitives include `gson:2.13.2`, `protobuf-java:4.33.0`, `jsr305:3.0.2`, and `error_prone_annotations:2.41.0`.
- Android Tink runtime transitives reported by Gradle include `androidx.annotation:annotation-jvm`, `gson:2.13.2`, `jsr305:3.0.2`, and `error_prone_annotations:2.41.0`.
- No `.so` entries were found in the resolved desktop Tink, Bouncy Castle, Gson, or Protobuf JARs inspected during this pass.
- Android assembly initially failed at `mergeDebugJavaResource` because `bcprov-jdk18on:1.84` and `org.jspecify:jspecify:1.0.0` both contributed `META-INF/versions/9/OSGI-INF/MANIFEST.MF`.
- The probe adds a narrow Android resource exclude for `META-INF/versions/9/OSGI-INF/MANIFEST.MF`; this removes a duplicate packaging metadata file only and does not affect vault storage behavior.

Packaging checks completed in this pass:

- Android `assembleDebug` passes when `ANDROID_USER_HOME` is pointed at temporary writable state in this sandbox. Without that override, this environment cannot create the debug keystore under `/home/spencer/.android`.
- Linux `packageDeb` passes with JDK 21 and the pinned probe dependencies.

Remaining package/runtime checks before this spike can become a production implementation decision:

- Release-build dependency/license review against final release artifacts and notices.
- Review whether the Android resource exclude remains acceptable for release packaging.

## License Notes

Local Maven POM inspection found:

- Tink 1.21.0 declares Apache License, Version 2.0.
- Bouncy Castle 1.84 declares the Bouncy Castle Licence.

The detailed candidate-level dependency/license review is in [`ENCRYPTED_LOCAL_VAULT_DEPENDENCY_REVIEW.md`](ENCRYPTED_LOCAL_VAULT_DEPENDENCY_REVIEW.md). A final implementation branch must still keep that review current against the release artifacts before enabling storage.

## Known-Answer Vectors

Known-answer-vector coverage now exists for desktop JVM and Android runtime:

- Bouncy Castle `Argon2BytesGenerator` matches the RFC 9106 Argon2id public test vector.
- Tink `InsecureNonceXChaCha20Poly1305` matches the XChaCha draft AEAD_XCHACHA20_POLY1305 public test vector.

Provider KAT status:

- Test-only provider positive KDF/AEAD KAT execution now passes through `VaultCryptoProvider.validateKat(...)` on desktop and Android runtime.
- Test-only provider negative misuse, nonce-policy, algorithm-policy, redaction, platform-coverage, and storage-separation checks from [`ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md) now pass in test source sets.
- Future production-provider execution of that contract remains missing because no production provider exists.

No wallet data, mnemonic material, private descriptors, real addresses, real txids, credentials, labels, notes, or production metadata may be used as test vectors.

## Current Recommendation

Use the Tink plus Bouncy Castle split stack as a dependency-reviewed, desktop and Android runtime KAT-validated candidate, not as an approved production vault implementation.

Rationale:

- It keeps common policy/source models independent of crypto providers.
- It avoids native-library packaging risk for the first JVM/Android build probe.
- It exposes the target AEAD API from Tink and the target KDF API from Bouncy Castle.
- It now has desktop JVM and Android runtime public KAT evidence for both selected primitives.
- The Lazysodium Java/Android path hit a concrete Android packaging blocker; IonSpin KMP remains a separate deferred comparison target rather than an accepted replacement.

Blockers before implementation:

- Final KDF parameter approval.
- Skald-owned executable production provider implementation, production provider selection, and production provider-level KAT contract execution.
- Envelope/key-hierarchy implementation review.
- Vault container and storage review.
- Lock/session lifecycle implementation and tests.
- Redaction/migration/corruption tests.
- Explicit approval to implement the encrypted vault behind disabled gates.

## Explicit Non-Capabilities

This dependency spike does not enable:

- encrypted vault storage,
- fake encryption,
- KDF implementation,
- AEAD implementation,
- key generation,
- passphrase or unlock UI,
- secure secret storage,
- secure metadata persistence,
- production sync,
- production observation persistence,
- production address index persistence,
- UTXO persistence,
- wallet activation,
- signing,
- broadcasting,
- Tor transport,
- Nostr, Lightning, Cashu, or Payjoin behavior,
- public backend defaults,
- Skald-operated infrastructure,
- mainnet.

## Current Boundary Status

The disabled `VaultCryptoProvider` boundary now exists as Skald-owned common policy code. It models provider-level requests, blockers, redacted diagnostics, record purposes, associated-data context, and KAT requirements. The provider-level KAT contract is modeled separately from dependency-level KAT evidence. A test-only provider harness now exercises that contract in desktop and Android test source sets, but production provider-level KAT execution remains unsatisfied until a future production provider exists. The disabled provider-selection registry now models candidate evidence and selection blockers, but it selects only `DisabledVaultCryptoProvider`. The Argon2id policy also models manual Android calibration evidence capture for high-end, optional low-end/mid-range, release-like, and thermal/load parameter review. Android compatibility/entropy policy is modeled separately: supported OS baseline plus runtime provider/primitive/randomness checks replace exhaustive low-end/mid-range model testing as the compatibility gate, approved cryptographic randomness is required, and hardware-backed key protection remains optional after review. The disabled boundary and selection registry do not call Tink or Bouncy Castle, do not run KDF/AEAD operations, do not generate keys, do not store keysets, and do not write vault containers or persistence.

## Next Step

Decide whether the next focused branch should:

- review runtime provider/primitive/randomness checks for supported Android and Linux paths,
- design a disabled production-provider skeleton with no storage,
- evaluate IonSpin KMP libsodium packaging and KAT mapping in isolation,
- investigate a specific Lazysodium/JNA variant-resolution strategy,
- or review the vault container format before any persistence work.
