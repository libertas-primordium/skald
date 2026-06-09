# Encrypted Local Vault Test Provider KAT Harness

## Status

Skald Vault now has a test-only provider-level known-answer-test harness for the future app-controlled encrypted local vault `VaultCryptoProvider` boundary.

This is test scaffolding only. It does not implement a production provider, production KDF execution, production AEAD execution, key generation, Tink keyset creation or storage, raw key material persistence, vault container read/write, passphrase/PIN/biometric unlock UI, secure secret storage success, secure metadata persistence success, production sync, backend clients, signing, broadcasting, Tor transport, Nostr parsing, public endpoints, Skald-operated infrastructure, or mainnet.

Runtime behavior remains fail-closed:

- `DisabledVaultCryptoProvider` rejects every production operation and does not pass provider-level KATs.
- The provider-selection registry documented in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md) selects only `DisabledVaultCryptoProvider`.
- Runtime randomness/provider checks documented in [`ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md`](ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md) remain separate availability evidence and do not approve this harness or any production provider.
- The test-provider harness lives only in desktop and Android test source sets.
- Production source still imports no Tink, Bouncy Castle, JCA/JCE, BDK, file, settings, network, or process APIs from the provider boundary.
- `SecureSecretStorage` remains disabled.
- `SecureWalletMetadataRepository` remains disabled.
- `EncryptedVaultReadinessPolicy` remains not implemented/not ready.
- Production persistence remains disabled.
- Production sync remains disabled.
- Mainnet remains disabled.

Tink raw-key feasibility is documented separately in [`ENCRYPTED_LOCAL_VAULT_TINK_RAW_KEY_FEASIBILITY_PROBE.md`](ENCRYPTED_LOCAL_VAULT_TINK_RAW_KEY_FEASIBILITY_PROBE.md). That probe records `FEASIBLE_PUBLIC_RAW_KEY_API` in desktop/JVM test scope only. It is not provider-level KAT evidence, does not use the Skald provider interface, does not implement production AEAD, and does not approve provider selection.

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

The provider-selection boundary treats this harness as interface evidence only. It does not allow Tink plus Bouncy Castle to be selected as a production provider.

Manual Android Argon2id calibration capture is a separate evidence boundary documented in [`ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md). It records timing context for parameter review; it is not provider KAT evidence and cannot approve a production provider or production KDF. Android compatibility and entropy policy are documented in [`ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md): low-end and mid-range model testing are no longer hard selection blockers, but supported OS baseline checks, runtime provider/primitive checks, approved cryptographic randomness, and fail-closed vault creation remain required. Runtime randomness/provider checks are documented in [`ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md`](ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md); they generate only small non-secret availability samples in test scope and are not entropy-quality proof or production provider approval. The Tink raw-key feasibility probe is also separate from this harness. It proves only that public Tink APIs can construct a pinned XChaCha20-Poly1305 primitive from caller-supplied fixed raw key bytes through a transient in-memory keyset handle. It does not prove production provider correctness or key commitment.

## Current Runtime Evidence

Desktop JVM test-provider KAT harness execution passed through:

```bash
GRADLE_USER_HOME=/tmp/skald-gradle-home ANDROID_HOME=/home/spencer/Android/Sdk ./gradlew --no-daemon -Djava.net.preferIPv4Stack=true -Pkotlin.compiler.execution.strategy=in-process :composeApp:desktopTest --tests '*Provider*' --tests '*Kat*'
```

Android instrumented test-provider KAT harness execution passed on Pixel 10 Pro XL / Android 16 through:

```bash
GRADLE_USER_HOME=/tmp/skald-gradle-home ANDROID_USER_HOME=/tmp/skald-android-user-home ANDROID_HOME=/home/spencer/Android/Sdk ANDROID_SERIAL=<serial-or-ip-port> ./gradlew --no-daemon -Djava.net.preferIPv4Stack=true -Pkotlin.compiler.execution.strategy=in-process -Pandroid.injected.device.serial=<serial-or-ip-port> :composeApp:connectedDebugAndroidTest
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

`VaultCryptoProviderSelectionRegistry` records the harness evidence separately from production provider KAT evidence. The registry still selects only the disabled provider and blocks production selection on missing production provider implementation, missing production provider-level KAT execution, non-final Argon2id parameters, missing runtime compatibility/randomness checks when evidence is unknown, disabled secure storage, disabled secure metadata persistence, missing vault container/storage review, and mainnet disablement.

## Next Step

The next focused branch must remain design/probe-only unless the user explicitly approves implementation scope. Recommended next decision point: review runtime provider/primitive/randomness checks for supported Android and Linux paths or design a still-disabled production-provider skeleton with no storage. Do not proceed to vault container read/write or persistence from this harness pass.
