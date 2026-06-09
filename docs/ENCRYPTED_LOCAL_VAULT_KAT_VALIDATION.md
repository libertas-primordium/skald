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

The Android instrumented test `VaultCryptoAndroidKatValidationTest` mirrors those same public vectors under `composeApp/src/androidInstrumentedTest`. The Android test APK assembles, and Android runtime execution passed on a Pixel 10 Pro XL running Android 16 after targeting the connected device with both `ANDROID_SERIAL` and `-Pandroid.injected.device.serial`. Durable docs intentionally omit the concrete ADB serial/IP:port.

Earlier connected-device attempts failed before any KAT assertion ran. The first failure was an APK install-signature conflict on a previously installed `com.libertasprimordium.skald` package. A later attempt was blocked by stale wireless-debugging mDNS target selection. The successful rerun is valid because `adb devices -l` reported a connected target as `device`, device properties reported `Pixel 10 Pro XL` / Android 16, `pm list packages` showed no installed Skald package before the test, and Gradle reported `Starting 2 tests on Pixel 10 Pro XL - 16` followed by `Finished 2 tests on Pixel 10 Pro XL - 16`.

The Tink explicit-nonce class is used only because official AEAD KATs require a fixed nonce. It is not an approved production vault API and is not wired into secure storage, secure metadata persistence, sync, UI, settings, wallet code, or repositories. The separate Tink raw-key feasibility probes are documented in [`ENCRYPTED_LOCAL_VAULT_TINK_RAW_KEY_FEASIBILITY_PROBE.md`](ENCRYPTED_LOCAL_VAULT_TINK_RAW_KEY_FEASIBILITY_PROBE.md); they record `FEASIBLE_PUBLIC_RAW_KEY_API` on desktop/JVM and `ANDROID_FEASIBLE_PUBLIC_RAW_KEY_API` on Android through public APIs and do not use the internal explicit-nonce KAT API. A disabled Skald-owned provider boundary now exists and is documented in [`ENCRYPTED_LOCAL_VAULT_CRYPTO_PROVIDER_BOUNDARY.md`](ENCRYPTED_LOCAL_VAULT_CRYPTO_PROVIDER_BOUNDARY.md), but it performs no production crypto. The disabled provider-selection boundary is documented in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md); it selects only `DisabledVaultCryptoProvider` and treats dependency-level KATs plus test-provider KATs as insufficient for production selection. The provider-level KAT contract that future executable providers must satisfy is documented in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md). A test-only provider KAT harness is documented in [`ENCRYPTED_LOCAL_VAULT_TEST_PROVIDER_KAT_HARNESS.md`](ENCRYPTED_LOCAL_VAULT_TEST_PROVIDER_KAT_HARNESS.md); it runs the public vectors and required negative cases through `VaultCryptoProvider.validateKat(...)` on desktop and Android runtime while returning only redacted test-scope evidence. Argon2id calibration policy/probes are documented in [`ENCRYPTED_LOCAL_VAULT_ARGON2ID_CALIBRATION.md`](ENCRYPTED_LOCAL_VAULT_ARGON2ID_CALIBRATION.md), and non-final candidate parameter tiers are documented in [`ENCRYPTED_LOCAL_VAULT_ARGON2ID_PARAMETER_POLICY.md`](ENCRYPTED_LOCAL_VAULT_ARGON2ID_PARAMETER_POLICY.md), but they do not select final production KDF parameters. Runtime randomness/provider checks are documented in [`ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md`](ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md); they are availability probes only, do not prove entropy quality, and do not approve production random-byte generation. A future production executable provider implementation must pass provider-level KATs and selection gates before vault storage is considered.

The header commitment, canonical header encoding, key-separation label, and strict AAD construction contract is documented in [`ENCRYPTED_LOCAL_VAULT_HEADER_COMMITMENT_AAD_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_HEADER_COMMITMENT_AAD_CONTRACT.md). Dependency-level KATs, test-provider KATs, and raw-key feasibility probes do not satisfy that construction contract and must not be used as evidence that Tink's non-key-committing AEAD risk is mitigated.

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
- `:composeApp:connectedDebugAndroidTest` executed two Android instrumented KAT tests on Pixel 10 Pro XL / Android 16 and passed when targeted with the connected ADB serial.

Runtime command that passed:

```bash
GRADLE_USER_HOME=/tmp/skald-gradle-home ANDROID_USER_HOME=/tmp/skald-android-user-home ANDROID_HOME=/home/spencer/Android/Sdk ANDROID_SERIAL="<serial-or-ip-port>" ./gradlew --no-daemon -Djava.net.preferIPv4Stack=true -Pkotlin.compiler.execution.strategy=in-process -Pandroid.injected.device.serial="<serial-or-ip-port>" :composeApp:connectedDebugAndroidTest
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
- `adb -s <serial-or-ip-port> uninstall com.libertasprimordium.skald || true` returned `Failure [DELETE_FAILED_INTERNAL_ERROR]` because Skald was apparently not installed; no Skald package data was deleted.
- Explicit ADB serial pinning plus Gradle's injected device serial avoided the stale mDNS target and executed the KATs on Android runtime.

## Test Provider Harness Status

The test-only provider harness adds a second validation layer above dependency-level KATs:

- Dependency KATs prove Tink and Bouncy Castle APIs can reproduce public vectors directly.
- Test-provider KATs prove the Skald-owned `VaultCryptoProvider` request/result path can carry those vectors and required negative cases through a test-scope implementation.
- Production provider KATs remain unproven because no production provider exists.

The connected Android test command executed the expanded suite on Pixel 10 Pro XL / Android 16 and reported `Starting 9 tests` and `Finished 9 tests`.

Manual Android Argon2id calibration capture is documented in [`ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md). It is parameter-policy evidence capture only, separate from dependency-level KATs, test-provider KATs, and production provider KAT approval.

Runtime randomness/provider checks are documented in [`ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md`](ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md). They are separate from dependency-level KATs and provider KATs: a small non-secret sample can show API availability and non-failing behavior, but it does not prove entropy quality or provider correctness.

## Source Confinement

Crypto imports remain confined to:

- Android compile probe: `AndroidVaultCryptoDependencyCompileProbe.kt`.
- Android instrumented KAT test: `VaultCryptoAndroidKatValidationTest.kt`.
- Android instrumented test-provider KAT harness: `VaultCryptoAndroidTestProviderKatHarnessTest.kt`.
- Desktop compile probe: `DesktopVaultCryptoDependencyCompileProbe.kt`.
- Desktop KAT test: `VaultCryptoKnownAnswerVectorTest.kt`.
- Desktop test-provider KAT harness: `VaultCryptoTestProviderKatHarnessTest.kt`.

Common vault readiness models, the disabled provider boundary, secure storage, secure metadata repositories, sync facade, UI, settings codecs, wallet/domain policy, and production repositories remain BDK-free and crypto-implementation-free.

## Current Decision

The Tink plus Bouncy Castle split stack remains a candidate only.

Current evidence:

- Desktop JVM official public KATs pass.
- Android compile/package probes pass.
- Android KAT test source is present and the test APK assembles.
- Android runtime KAT execution passed on Pixel 10 Pro XL / Android 16 with the same official public vectors.
- Candidate-level dependency, license, keyset/storage, Bouncy Castle Argon2id API, and split-provider review is documented in [`ENCRYPTED_LOCAL_VAULT_DEPENDENCY_REVIEW.md`](ENCRYPTED_LOCAL_VAULT_DEPENDENCY_REVIEW.md).
- Provider-level KAT contract requirements are modeled in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md), and a test-only provider harness now exercises those public vectors and required negative cases through the Skald interface.
- Runtime randomness/provider check models and test-only Android/Linux availability probes are documented in [`ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md`](ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md), but they are not entropy-quality proof and do not approve production randomness.
- The Tink raw-key feasibility probes are documented in [`ENCRYPTED_LOCAL_VAULT_TINK_RAW_KEY_FEASIBILITY_PROBE.md`](ENCRYPTED_LOCAL_VAULT_TINK_RAW_KEY_FEASIBILITY_PROBE.md) and report `FEASIBLE_PUBLIC_RAW_KEY_API` for desktop/JVM test scope plus `ANDROID_FEASIBLE_PUBLIC_RAW_KEY_API` for Android instrumented test scope. They do not approve production provider selection.
- No production executable provider exists, so dependency-level and test-provider KATs do not satisfy production provider approval or provider selection.

It is not approved for production vault implementation yet. Remaining blockers:

- Final KDF parameter approval on Android and Linux desktop; current calibration probes, runtime randomness probes, and candidate tiers are planning evidence only.
- Executable Skald-owned production `VaultCryptoProvider` implementation design; the current production boundary is disabled only.
- Provider selection beyond `DisabledVaultCryptoProvider`; the selection boundary blocks Tink plus Bouncy Castle until production provider, storage, parameter, and mainnet gates are satisfied.
- Production provider-boundary public KATs and required negative/redaction/platform contract checks on Android and desktop runtime through that executable provider.
- Container/envelope parser and writer design.
- Lock/session lifecycle tests.
- Redaction tests.
- Migration and corruption tests.
- Release-artifact dependency/license review before any release.
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

The next focused branch should remain design/probe-only unless explicitly narrowed otherwise: review runtime provider/primitive/randomness checks for supported Android and Linux paths or design a disabled production-provider skeleton with no storage before any vault container work. A production vault implementation is still not approved by this Android runtime KAT result, by Android compatibility/entropy policy modeling, by runtime randomness availability probes, or by the test-only provider KAT harness.
