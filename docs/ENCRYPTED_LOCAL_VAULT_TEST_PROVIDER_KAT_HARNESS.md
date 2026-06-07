# Encrypted Local Vault Test Provider KAT Harness

## Status

Skald Vault now has a test-only provider-level known-answer-test harness for the future app-controlled encrypted local vault `VaultCryptoProvider` boundary.

This is test scaffolding only. It does not implement a production provider, production KDF execution, production AEAD execution, key generation, Tink keyset creation or storage, raw key material persistence, vault container read/write, passphrase/PIN/biometric unlock UI, secure secret storage success, secure metadata persistence success, production sync, backend clients, signing, broadcasting, Tor transport, Nostr parsing, public endpoints, Skald-operated infrastructure, or mainnet.

Runtime behavior remains fail-closed:

- `DisabledVaultCryptoProvider` rejects every production operation and does not pass provider-level KATs.
- The test-provider harness lives only in desktop and Android test source sets.
- Production source still imports no Tink, Bouncy Castle, JCA/JCE, BDK, file, settings, network, or process APIs from the provider boundary.
- `SecureSecretStorage` remains disabled.
- `SecureWalletMetadataRepository` remains disabled.
- `EncryptedVaultReadinessPolicy` remains not implemented/not ready.
- Production persistence remains disabled.
- Production sync remains disabled.
- Mainnet remains disabled.

## Source Location

Production-safe common provider request/result models:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/VaultCryptoProvider.kt
```

Desktop test-only provider harness:

```text
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/VaultCryptoTestProviderKatHarnessTest.kt
```

Android instrumented test-only provider harness:

```text
composeApp/src/androidInstrumentedTest/kotlin/com/libertasprimordium/skald/VaultCryptoAndroidTestProviderKatHarnessTest.kt
```

Source guards:

```text
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/ProductionBackendAdapterSourceGuardTest.kt
```

## Provider Interface Path

The common provider boundary now includes a redacted KAT operation:

```text
VaultCryptoProvider.validateKat(VaultCryptoProviderKatRequest)
```

The request carries only Skald-owned KAT requirement identity, category, and typed associated-data context. It does not carry provider-specific objects, raw keys, passphrases, plaintext, ciphertext, nonce bytes, derived bytes, file paths, storage handles, BDK types, or platform types.

Successful test harness validation returns `VaultCryptoProviderKatEvidence` with `TestHarnessOnly` scope. The evidence is redacted by design and is not a production provider approval.

`DisabledVaultCryptoProvider.validateKat` returns a blocked result with `ProviderLevelKnownAnswerVectorsMissing`. It must not fake a provider KAT pass.

## What The Harness Proves

The harness proves that the current Skald-owned provider interface can express and validate the required public-vector behavior through a test-scope implementation:

- Argon2id RFC 9106 section 5.3 public KDF vector.
- XChaCha20-Poly1305 XChaCha draft appendix A.1 public AEAD vector.
- Wrong associated data fails closed.
- Modified ciphertext fails closed.
- Modified authentication tag fails closed.
- Wrong key fails closed.
- Unsupported algorithm policy is represented as rejected.
- Production nonce-bypass policy is represented as rejected.
- Caller-provided production nonce remains disallowed.
- PBKDF2 remains rejected as the default vault KDF.
- scrypt remains fallback-not-selected.
- Provider diagnostics and evidence remain redacted.
- Desktop and Android runtime test harness coverage exists.

The harness does not prove a production provider is correct, because no production provider exists. It also does not approve vault storage, vault container format, key hierarchy execution, key generation, keyset storage, lock/session lifecycle behavior, migration/corruption handling, or mainnet relevance.

## Current Runtime Evidence

Desktop JVM test-provider KAT harness execution passed through:

```bash
GRADLE_USER_HOME=/tmp/skald-gradle-home ANDROID_HOME=/home/spencer/Android/Sdk ./gradlew --no-daemon -Djava.net.preferIPv4Stack=true -Pkotlin.compiler.execution.strategy=in-process :composeApp:desktopTest --tests '*Provider*' --tests '*Kat*'
```

Android instrumented test-provider KAT harness execution passed on Pixel 10 Pro XL / Android 16 through:

```bash
GRADLE_USER_HOME=/tmp/skald-gradle-home ANDROID_USER_HOME=/tmp/skald-android-user-home ANDROID_HOME=/home/spencer/Android/Sdk ANDROID_SERIAL=192.168.1.155:44127 ./gradlew --no-daemon -Djava.net.preferIPv4Stack=true -Pkotlin.compiler.execution.strategy=in-process -Pandroid.injected.device.serial=192.168.1.155:44127 :composeApp:connectedDebugAndroidTest
```

Gradle reported:

```text
Starting 9 tests on Pixel 10 Pro XL - 16
Finished 9 tests on Pixel 10 Pro XL - 16
BUILD SUCCESSFUL
```

These are test-harness runtime results only. They are not production provider approval.

## Source Confinement

Tink and Bouncy Castle calls are confined to:

- platform compile probes,
- dependency-level KAT tests,
- Argon2id calibration probe tests,
- test-only provider KAT harnesses,
- artifact/source-guard tests.

The provider boundary in `commonMain` imports none of those libraries and performs no crypto. It only defines Skald-owned request, result, error, KAT contract, and redacted evidence types.

## Fixture Policy

The harness uses official public cryptographic vectors only:

- RFC 9106 section 5.3 for Argon2id.
- XChaCha draft appendix A.1 for AEAD_XCHACHA20_POLY1305.

Fixtures are public, non-wallet, and non-secret. They are not mnemonic material, seed bytes, private descriptors, private keys, WIFs, wallet databases, real addresses, real txids, PSBTs, transaction hex, Nostr private material, Lightning credentials, Cashu proofs, RPC cookies, backend credentials, wallet labels, UTXO labels, or transaction notes.

The harness does not persist inputs, outputs, ciphertexts, key material, keysets, timings, storage records, or vault containers.

## Readiness Alignment

`VaultCryptoDependencyProbeCatalog` now records that a test-only provider KAT harness is present for the Tink plus Bouncy Castle candidate. The candidate remains reviewed-only and not production-approved. `ProviderLevelKatExecutionMissing` remains a production blocker because the executable production provider is absent.

`EncryptedVaultReadinessPolicy` records a test-only provider KAT harness model as non-production capability. `ProviderBoundaryKnownAnswerVectorsPassed` remains absent, `ProviderKnownAnswerVectorsMissing` remains a blocker, production persistence remains disabled, and mainnet remains disabled.

## Next Step

The next focused branch must remain design/probe-only unless the user explicitly approves implementation scope. Recommended next decision point: decide whether to design a disabled executable production-provider skeleton with no storage, or collect remaining Android baseline Argon2id calibration evidence before provider implementation. Do not proceed to vault container read/write or persistence from this harness pass.
