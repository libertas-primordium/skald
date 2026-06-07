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

## Probe Scope

The spike answers a narrow build question:

```text
Can a pinned dependency candidate expose Argon2id and XChaCha20-Poly1305 APIs on Android and Linux desktop packaging without adding native-library packaging risk or enabling vault storage?
```

It does not answer the implementation questions that remain for a real vault:

- KDF parameter calibration.
- Known-answer vector test selection.
- Vault envelope implementation.
- Key hierarchy implementation.
- Android runtime instrumentation.
- Secure memory/session lifecycle behavior.
- Migration/corruption behavior.
- Redaction and failure-mode coverage for real encrypted records.

## Candidate Summary

| Candidate | Spike result | Reason |
| --- | --- | --- |
| Tink AEAD plus Bouncy Castle Argon2id | Selected for packaging probe only | Provides Tink XChaCha20-Poly1305 API and Bouncy Castle Argon2id API with pinned JVM/Android artifacts and no native library entries observed in resolved JARs. |
| libsodium/KMP binding | Deferred | One primitive family could cover Argon2id and XChaCha20-Poly1305, but native library packaging, ABI coverage, binding maintenance, and `.deb` behavior need a separate review. |
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

These files reference candidate classes by type so Android and desktop compilation fail if the expected APIs disappear. They do not derive keys, generate keys, encrypt data, decrypt data, write files, call platform key stores, or persist anything.

## API Presence

The probe confirms these API classes compile on platform source sets:

```text
com.google.crypto.tink.aead.XChaCha20Poly1305Key
org.bouncycastle.crypto.generators.Argon2BytesGenerator
org.bouncycastle.crypto.modes.ChaCha20Poly1305
```

The desktop artifact probe also loads those class names with `Class.forName()` as a runtime classpath check.

This is not a cryptographic correctness test. Known-answer-vector tests are still required before implementation.

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

Remaining package/runtime checks before this spike can become a dependency decision for implementation:

- Android runtime/instrumentation probe if the vault implementation later uses these APIs on device.
- Release-build dependency/license review.
- Review whether the Android resource exclude remains acceptable for release packaging.

## License Notes

Local Maven POM inspection found:

- Tink 1.21.0 declares Apache License, Version 2.0.
- Bouncy Castle 1.84 declares the Bouncy Castle Licence.

This is a preliminary packaging-spike note only. A final implementation branch should still perform dependency/license review, transitive dependency review, and release packaging review before enabling storage.

## Known-Answer Vectors

Known-answer-vector testing was intentionally not added in this pass.

Required future KAT work:

- Argon2id official/reference vectors for the selected Bouncy Castle API usage or replacement provider.
- XChaCha20-Poly1305 vectors for the selected Tink API usage or replacement provider.
- Skald-owned envelope vectors using obvious non-wallet sentinel values.
- Cross-platform Android/Linux verification.

No wallet data, mnemonic material, private descriptors, real addresses, real txids, credentials, labels, notes, or production metadata may be used as test vectors.

## Current Recommendation

Use the Tink plus Bouncy Castle split stack as the next implementation-spike candidate, not as an approved production vault implementation.

Rationale:

- It keeps common policy/source models independent of crypto providers.
- It avoids native-library packaging risk for the first JVM/Android build probe.
- It exposes the target AEAD API from Tink and the target KDF API from Bouncy Castle.
- It keeps libsodium available as a later alternative if a single primitive family is preferred and native packaging review passes.

Blockers before implementation:

- Final dependency and license review.
- Android runtime/API verification.
- KDF parameter calibration.
- Known-answer-vector tests.
- Envelope/key-hierarchy implementation review.
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

## Next Step

Decide whether the next focused branch should:

- add official non-secret KAT tests for the selected APIs without creating vault storage,
- compare libsodium/KMP native packaging directly,
- or start a disabled encrypted-vault container/parser implementation behind fail-closed readiness gates.
