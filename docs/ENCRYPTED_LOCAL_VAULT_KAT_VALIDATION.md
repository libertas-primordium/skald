# Encrypted Local Vault KAT Validation

## Status

This document records the official non-secret known-answer-vector validation added for the pinned Tink plus Bouncy Castle vault crypto dependency probe.

This is crypto dependency validation only. It does not implement encryption, derive production keys, create a vault container, persist secrets, persist sensitive metadata, enable production sync, create wallets, derive production addresses, sign, broadcast, add Tor transport, add public endpoints, add Skald-operated infrastructure, or enable mainnet.

Runtime behavior remains fail-closed:

- `SecureSecretStorage` is disabled.
- `SecureWalletMetadataRepository` is disabled.
- `EncryptedVaultReadinessPolicy` reports the vault as not implemented/not ready.
- Production sync remains disabled.
- Production observation, address-index, UTXO, label, note, and wallet-history persistence remain disabled.

## Validated APIs

The desktop test `VaultCryptoKatValidationTest` validates the pinned probe stack with public cryptographic test vectors:

- Bouncy Castle `org.bouncycastle.crypto.generators.Argon2BytesGenerator` against the RFC 9106 Argon2id test vector.
- Tink `com.google.crypto.tink.aead.internal.InsecureNonceXChaCha20Poly1305` against the XChaCha draft AEAD_XChaCha20_Poly1305 test vector.

The Android instrumented test `VaultCryptoAndroidKatValidationTest` mirrors those same public vectors under `composeApp/src/androidInstrumentedTest`. The Android test APK assembles, and Android runtime execution passed on a Pixel 10 Pro XL running Android 16 after targeting the raw ADB IP:port serial `192.168.1.155:44127` with both `ANDROID_SERIAL` and `-Pandroid.injected.device.serial`.

Earlier connected-device attempts failed before any KAT assertion ran. The first failure was an APK install-signature conflict on a previously installed `com.libertasprimordium.skald` package. A later attempt was blocked by stale wireless-debugging mDNS target selection. The successful rerun is valid because `adb devices -l` reported the raw serial as `device`, `adb -s 192.168.1.155:44127 shell getprop ro.product.model` reported `Pixel 10 Pro XL`, `pm list packages` showed no installed Skald package before the test, and Gradle reported `Starting 2 tests on Pixel 10 Pro XL - 16` followed by `Finished 2 tests on Pixel 10 Pro XL - 16`.

The Tink explicit-nonce class is used only because official AEAD KATs require a fixed nonce. It is not an approved production vault API and is not wired into secure storage, secure metadata persistence, sync, UI, settings, wallet code, or repositories. A future production implementation must wrap any primitive use behind a narrow Skald-owned provider boundary before vault storage is considered.

The libsodium comparison documented in [`ENCRYPTED_LOCAL_VAULT_LIBSODIUM_COMPARISON.md`](ENCRYPTED_LOCAL_VAULT_LIBSODIUM_COMPARISON.md) did not add libsodium KATs. Lazysodium Java/Android was rejected at the Android packaging gate before KAT execution, and IonSpin KMP libsodium remains deferred pending an isolated packaging/KAT mapping spike.

## Vector Sources

Argon2id:

- Source: RFC 9106, section 5.3, Argon2id test vectors: <https://www.ietf.org/rfc/rfc9106.html#section-5.3>.
- Parameters: version 19, memory 32 KiB, 3 passes, 4 lanes, 32-byte output.
- Public vector material: byte-pattern password, salt, secret, and associated data from the RFC.
- Result: Bouncy Castle `1.84` matches the expected public tag on desktop JVM and Android runtime.

XChaCha20-Poly1305:

- Source: `draft-irtf-cfrg-xchacha-02`, appendix A.1, AEAD_XCHACHA20_POLY1305 test vector: <https://datatracker.ietf.org/doc/html/draft-irtf-cfrg-xchacha-02#appendix-A.1>.
- Parameters: 32-byte key, 24-byte nonce, associated data, plaintext, ciphertext, and tag from the draft.
- Result: Tink `1.21.0` matches the expected public ciphertext-plus-tag on desktop JVM and Android runtime and decrypts it back to the public plaintext.

These vectors are public cryptographic test vectors only. They are not wallet data and do not contain mnemonic material, seed bytes, private descriptors, keys, credentials, addresses, txids, labels, notes, or production metadata.

## Android Runtime Status

Android runtime KAT execution is validated for the current probe stack.

What is verified:

- Android compile/package probe source still references the pinned Tink and Bouncy Castle API classes.
- Android instrumented KAT source exists under `composeApp/src/androidInstrumentedTest`.
- `:composeApp:assembleDebugAndroidTest` assembles the Android test APK.
- `:composeApp:assembleDebug` assembles the debug APK.
- `:composeApp:connectedDebugAndroidTest` executed two Android instrumented KAT tests on Pixel 10 Pro XL / Android 16 and passed when targeted with the raw ADB IP:port serial.

Runtime command that passed:

```bash
GRADLE_USER_HOME=/tmp/skald-gradle-home ANDROID_USER_HOME=/tmp/skald-android-user-home ANDROID_HOME=/home/spencer/Android/Sdk ANDROID_SERIAL="192.168.1.155:44127" ./gradlew --no-daemon -Djava.net.preferIPv4Stack=true -Pkotlin.compiler.execution.strategy=in-process -Pandroid.injected.device.serial="192.168.1.155:44127" :composeApp:connectedDebugAndroidTest
```

Gradle reported:

```text
Starting 2 tests on Pixel 10 Pro XL - 16
Finished 2 tests on Pixel 10 Pro XL - 16
BUILD SUCCESSFUL in 30s
```

What the earlier failures mean:

- The install-signature conflict was an environment/device-state blocker that occurred before KAT execution.
- The stale wireless-debugging mDNS target was an ADB device-targeting blocker.
- The later `pm list packages` check showed only `package:com.libertasprimordium.othernote` and no installed `com.libertasprimordium.skald` package before the successful run.
- `adb -s 192.168.1.155:44127 uninstall com.libertasprimordium.skald || true` returned `Failure [DELETE_FAILED_INTERNAL_ERROR]` because Skald was apparently not installed; no Skald package data was deleted.
- The raw IP:port serial plus Gradle's injected device serial avoided the stale mDNS target and executed the KATs on Android runtime.

## Source Confinement

Crypto imports remain confined to:

- Android compile probe: `AndroidVaultCryptoDependencyCompileProbe.kt`.
- Android instrumented KAT test: `VaultCryptoAndroidKatValidationTest.kt`.
- Desktop compile probe: `DesktopVaultCryptoDependencyCompileProbe.kt`.
- Desktop KAT test: `VaultCryptoKnownAnswerVectorTest.kt`.

Common vault readiness models, secure storage, secure metadata repositories, sync facade, UI, settings codecs, wallet/domain policy, and production repositories remain BDK-free and crypto-implementation-free.

## Current Decision

The Tink plus Bouncy Castle split stack remains a candidate only.

Current evidence:

- Desktop JVM official public KATs pass.
- Android compile/package probes pass.
- Android KAT test source is present and the test APK assembles.
- Android runtime KAT execution passed on Pixel 10 Pro XL / Android 16 with the same official public vectors.

It is not approved for production vault implementation yet. Remaining blockers:

- Final dependency and license review.
- KDF calibration on Android and Linux desktop.
- Narrow Skald-owned `VaultCryptoProvider`-style boundary design.
- Tink keyset/storage handling review.
- Split-provider boundary review.
- Container/envelope parser and writer design.
- Lock/session lifecycle tests.
- Redaction tests.
- Migration and corruption tests.
- Explicit approval before any encrypted storage implementation.

## Non-Capabilities

This KAT validation does not enable:

- encrypted vault storage,
- fake encryption,
- vault container parsing or writing,
- production key derivation,
- production AEAD record encryption,
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

The next focused branch should remain design/probe-only: complete dependency/license review, KDF calibration planning, Tink keyset/storage handling review, split-provider boundary review, or design a narrow disabled `VaultCryptoProvider` boundary before any vault container work. A production vault implementation is still not approved by this Android runtime KAT result.
