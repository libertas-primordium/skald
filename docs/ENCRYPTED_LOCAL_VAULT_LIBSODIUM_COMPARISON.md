# Encrypted Local Vault Libsodium Comparison

## Status

This document records a focused libsodium/Kotlin packaging and feasibility comparison for the future app-controlled encrypted local vault primitive stack.

This is dependency evaluation only. It does not implement encryption, a KDF, AEAD record handling, a vault crypto provider, a vault container, key generation, passphrase/PIN/biometric unlock UI, secure secret storage, secure metadata persistence, production sync, backend clients, signing, broadcasting, Tor transport, Nostr parsing, public endpoints, Skald-operated infrastructure, or mainnet.

Runtime behavior remains fail-closed:

- `SecureSecretStorage` is disabled.
- `SecureWalletMetadataRepository` is disabled.
- `EncryptedVaultReadinessPolicy` reports the vault as not implemented/not ready.
- Production sync remains disabled.
- Production secret and sensitive metadata persistence remain disabled.
- Mainnet remains disabled.
- The provider-selection registry documented in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md) selects only `DisabledVaultCryptoProvider`.

## Comparison Result

| Stack | Exact artifacts evaluated | Result | Reason |
| --- | --- | --- | --- |
| Tink plus Bouncy Castle split stack | `com.google.crypto.tink:tink-android:1.21.0`, `com.google.crypto.tink:tink:1.21.0`, `org.bouncycastle:bcprov-jdk18on:1.84` | Candidate reviewed; not production-approved and not selectable | Android and Linux packaging pass in this project, desktop JVM public KATs pass, Android runtime KATs passed on Pixel 10 Pro XL / Android 16, no native library artifacts were introduced by Tink/Bouncy, candidate-level dependency/license/keyset/split-provider review is complete in [`ENCRYPTED_LOCAL_VAULT_DEPENDENCY_REVIEW.md`](ENCRYPTED_LOCAL_VAULT_DEPENDENCY_REVIEW.md), a disabled Skald-owned provider boundary is documented in [`ENCRYPTED_LOCAL_VAULT_CRYPTO_PROVIDER_BOUNDARY.md`](ENCRYPTED_LOCAL_VAULT_CRYPTO_PROVIDER_BOUNDARY.md), provider-selection blocking is documented in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md), provider-level KAT contract requirements are modeled in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md), and a test-only provider KAT harness is documented in [`ENCRYPTED_LOCAL_VAULT_TEST_PROVIDER_KAT_HARNESS.md`](ENCRYPTED_LOCAL_VAULT_TEST_PROVIDER_KAT_HARNESS.md). Production executable provider implementation, production provider selection, and production provider-level KAT execution remain outstanding. |
| Lazysodium Java/Android | `com.goterl:lazysodium-java:5.2.0`, `com.goterl:lazysodium-android:5.2.0` | Rejected for the current vault branch | Primitive coverage is attractive, but Android packaging failed before APK output with duplicate `com.sun.jna.*` classes from `jna-5.17.0.aar` and `jna-5.17.0.jar`. The candidate was removed from runtime dependencies. |
| IonSpin Kotlin Multiplatform libsodium bindings | `com.ionspin.kotlin:multiplatform-crypto-libsodium-bindings:0.9.5`, `com.ionspin.kotlin:multiplatform-crypto-libsodium-bindings-jvm:0.9.5`, `com.ionspin.kotlin:multiplatform-crypto-libsodium-bindings-android:0.9.5` | Deferred after metadata/POM inspection | Promising KMP shape, but this pass did not add it to the build because Kotlin metadata compatibility, native-loader behavior, JNA/resource-loader transitive behavior, Android ABI packaging, Linux `.deb` behavior, and KAT mapping remain unverified. |

No libsodium dependency remains wired into the Gradle runtime after this comparison. The failed candidate is represented only in Skald-owned dependency/readiness models and documentation.

## Candidate 1: Current Tink Plus Bouncy Castle Split Stack

### Primitive Coverage

- Argon2id: Bouncy Castle `Argon2BytesGenerator`.
- XChaCha20-Poly1305: Tink `XChaCha20Poly1305Key` and internal explicit-nonce probe API for public KAT validation.
- Fixed-nonce KAT feasibility: proven on desktop JVM through the internal Tink explicit-nonce probe API, which must not become the production vault API.
- Random nonce production feasibility: design target only; no production nonce generation was implemented.
- Key expansion: Tink/Bouncy provide supporting primitives, but Skald has not selected a production key hierarchy implementation.
- Streaming/backup encryption: not proven by this stack in this pass.
- Misuse risk: split provider stack, Tink keyset semantics, Bouncy Castle lower-level KDF API, and Tink internal explicit-nonce API require a narrow Skald-owned provider boundary before any vault implementation. The current boundary is disabled and performs no crypto.

### Packaging And Runtime Evidence

- Android compile/package: passes after the existing narrow Bouncy Castle resource exclude for `META-INF/versions/9/OSGI-INF/MANIFEST.MF`.
- Linux desktop compile/package: passes.
- Native libraries: no Tink/Bouncy native `.so` entries were observed in the resolved JAR inventory from the earlier dependency spike.
- Transitives: Tink JVM brings Gson, Protobuf, jsr305, and error-prone annotations; Bouncy Castle is direct.
- Licensing: local POM review found Tink Apache-2.0 and Bouncy Castle Licence declarations; release-artifact review is still required.
- KAT coverage: desktop JVM public vectors pass for RFC 9106 Argon2id and XChaCha draft AEAD_XCHACHA20_POLY1305. Android instrumented runtime KATs mirror those vectors and passed on Pixel 10 Pro XL / Android 16 when the raw ADB IP:port serial was targeted. A test-only provider harness now carries those vectors and required negative cases through the Skald-owned provider interface in test source sets only.

### Status

This stack remains a candidate only. It is not production-approved, is not selected by the provider-selection registry, and does not enable storage.

## Candidate 2: Lazysodium Java/Android

### Exact Artifacts Inspected

- Maven metadata: `com.goterl:lazysodium-java`, latest/release `5.2.0`.
- Maven metadata: `com.goterl:lazysodium-android`, latest/release `5.2.0`.
- JVM artifact: `com.goterl:lazysodium-java:5.2.0`.
- Android artifact: `com.goterl:lazysodium-android:5.2.0`.
- Runtime transitives from POM inspection:
  - `net.java.dev.jna:jna:5.17.0`,
  - `com.goterl:resource-loader:2.1.0` on JVM,
  - `org.slf4j:slf4j-api:2.0.17` on JVM,
  - `androidx.core:core-ktx:1.16.0` on Android,
  - `org.jetbrains.kotlin:kotlin-stdlib:2.1.21` on Android.

### Primitive Coverage

Artifact/API inspection found Lazysodium exposes one primitive family relevant to the vault design:

- Argon2id: `cryptoPwHash` and `PwHash` constants.
- scrypt: `Scrypt` API.
- XChaCha20-Poly1305 AEAD: `cryptoAeadXChaCha20Poly1305Ietf*` APIs and `AEAD` constants.
- Random nonce support: libsodium random helpers are available, but this pass did not call them.
- Key derivation/key expansion: `cryptoKdf*` APIs and `KeyDerivation` constants.
- Streaming/chunked backup suitability: `cryptoSecretStream*` and `SecretStream` constants are present.
- Fixed-nonce KAT feasibility: appears feasible because native APIs expose caller-supplied nonce parameters, but no Lazysodium KAT was added after Android packaging failed.

This API surface is closer to the target vault primitive set than the split stack because Argon2id, XChaCha20-Poly1305, secretstream, KDF, and secure-memory-oriented APIs come from one family. That advantage is not enough to accept the packaging risk discovered in this branch.

### Native Library Entries

The JVM JAR inventory contains bundled native libraries:

- `linux64/libsodium.so`
- `linux/libsodium.so`
- `arm64/libsodium.so`
- `armv6/libsodium.so`
- `mac/libsodium.dylib`
- `mac_arm/libsodium.dylib`
- `windows/libsodium.dll`
- `windows64/libsodium.dll`

The Android AAR inventory contains native ABI libraries:

- `jni/arm64-v8a/libsodium.so`
- `jni/armeabi-v7a/libsodium.so`
- `jni/x86/libsodium.so`
- `jni/x86_64/libsodium.so`

The Android ABI coverage is broad enough for a first packaging probe, but the native loader and duplicate JNA behavior are unresolved blockers.

### Packaging Probe Outcome

The candidate was temporarily added to platform source sets for a packaging probe:

- Android: `implementation(libs.lazysodium.android)`.
- Linux desktop: `implementation(libs.lazysodium.jvm)`.

The Linux `.deb` task passed during the attempted probe and wrote a package. This only proves that the desktop package task could complete with the candidate present in that transient state; it does not prove native loader behavior or production suitability.

The Android APK task failed before output at `:composeApp:checkDebugDuplicateClasses`. The earliest failing stage was duplicate JNA classes:

```text
Duplicate class com.sun.jna.* found in modules jna-5.17.0.aar and jna-5.17.0.jar
```

The runtime dependency tree showed the root cause: Lazysodium Android introduces Android JNA `5.17.0`, while the project already resolves JNA on the Android classpath through existing dependencies. The result is simultaneous Android AAR and JVM JAR variants of JNA with the same `com.sun.jna.*` classes.

Because APK packaging failed, Lazysodium was removed from Gradle runtime dependencies. No exclusion or resolution workaround was added in this branch.

### Licensing Notes

POM inspection found:

- Lazysodium Java/Android: Mozilla Public License 2.0.
- resource-loader: MIT.
- JNA: dual LGPL-2.1-or-later or Apache-2.0.

These are preliminary notes only. Release review would need to include bundled native libsodium license provenance, source availability, native build reproducibility, JNA variant handling, and Android/Linux package notices.

### Misuse And Review Risks

- Broad low-level native API surface; a future provider boundary would need to expose only the exact Skald vault operations.
- Native loader behavior must be reviewed on Android and Linux desktop.
- JVM artifact bundles native libraries for multiple platforms, increasing package/release-review scope.
- Android duplicate JNA conflict is a hard current packaging failure.
- KATs were not added because packaging failed first.
- Android runtime behavior was not validated.
- No native-library provenance/reproducible-build review was performed.

### Status

Rejected for the current vault branch and non-selectable in the provider-selection registry. It remains a possible future investigation target only if a narrower branch can resolve the JNA variant conflict without weakening the build, hiding duplicate classes, or broadening production crypto usage.

## Candidate 3: IonSpin Kotlin Multiplatform Libsodium Bindings

### Exact Artifacts Inspected

Maven metadata/POM inspection identified:

- `com.ionspin.kotlin:multiplatform-crypto-libsodium-bindings:0.9.5`
- `com.ionspin.kotlin:multiplatform-crypto-libsodium-bindings-jvm:0.9.5`
- `com.ionspin.kotlin:multiplatform-crypto-libsodium-bindings-android:0.9.5`

POM observations:

- License: Apache-2.0.
- Common/JVM artifacts depend on Kotlin `1.9.23`.
- JVM artifact depends on `com.goterl:resource-loader:2.0.2` and `net.java.dev.jna:jna:5.18.1`.
- Android artifact is an AAR and also references JNA/resource-loader transitives.

### Primitive Coverage

The project did not add IonSpin to the build in this pass. At the metadata/design level it is still relevant because it is a Kotlin Multiplatform libsodium binding intended to expose libsodium primitives including Argon2id and XChaCha20-Poly1305. The exact Skald API mapping, fixed-nonce KAT path, native artifacts, and Android/Linux runtime behavior remain unverified.

### Deferral Reasons

- Kotlin metadata compatibility with this project's Kotlin `2.1.21` build was not validated.
- Android AAR/native library inventory was not fully inspected through Gradle resolution.
- Linux `.deb` behavior was not package-probed.
- It appears to use JNA/resource-loader transitives, which may reproduce or differ from the Lazysodium packaging issue.
- No KAT mapping was proven.
- No Android runtime probe was added.

### Status

Deferred after comparison and non-selectable in the provider-selection registry. A future branch may evaluate it only if the scope is explicitly narrowed to IonSpin packaging, KMP metadata compatibility, native library inventory, and public-vector mapping.

## Readiness Model Alignment

`VaultCryptoDependencyProbeCatalog` now records:

- `TinkBouncyCastleSplit`: `DependencyLicenseAndKeysetReviewCompleteCandidate` with a disabled provider boundary, disabled provider-selection boundary, provider-level KAT contract, test-only provider KAT harness, Argon2id calibration policy/probe-only status, and non-final candidate parameter policy modeled.
- `LazysodiumJavaAndroid`: `RejectedForCurrentVault`.
- `IonSpinKmpLibsodium`: `DeferredAfterComparison`.

No candidate is production-approved or production-selectable. All candidates report `implementationEnabled = false`, `storageEnabled = false`, `productionPersistenceEnabled = false`, and `mainnetEnabled = false`. `VaultCryptoProviderSelectionRegistry` selects only `DisabledVaultCryptoProvider`.

The Lazysodium model includes `AndroidDuplicateJnaClasspath`, `NativePackagingUnverified`, `NativePackagingReviewRequired`, `NativeLoaderRuntimeRisk`, `KnownAnswerVectorTestsMissing`, `AndroidKnownAnswerVectorRuntimeMissing`, `AndroidRuntimeProbeMissing`, `ProductionProviderBoundaryMissing`, and `VaultImplementationStillDisabled` blockers.

## Acceptance Gates Before Implementation

Before any encrypted vault implementation can use any primitive stack:

1. Resolve final dependency selection through explicit review.
2. Prove Android and Linux package behavior without duplicate classes or hidden native-library conflicts.
3. Run official public KATs on every runtime that will execute the primitives.
4. Keep the disabled Skald-owned `VaultCryptoProvider` boundary provider-free and storage-free until executable-provider work is explicitly approved.
5. Pass provider-level public KATs and required negative/redaction/platform checks through any future executable production provider boundary according to [`ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md); the current test-only harness is not production approval or provider-selection approval.
6. Keep explicit nonce handling and key hierarchy inside that reviewed boundary only.
7. Review Argon2id calibration probe evidence, candidate parameter tiers, and runtime provider/primitive/randomness checks for supported Android and Linux paths, then approve final memory-hard KDF parameter policy per supported platform. Optional low-end/mid-range Android timing evidence may inform UX and parameter choice, but it is no longer a hard compatibility blocker.
8. Define redaction, error, logging, and test-vector policy.
9. Verify lock/session lifecycle and memory-clearing limits.
10. Verify corruption and migration behavior.
11. Keep secure secret storage and secure metadata persistence disabled until all storage acceptance gates pass.

## Acceptance Gates Before Production Persistence

Before any production secret or sensitive metadata persistence can succeed:

1. Encrypted vault container and record format reviewed.
2. Secure secret storage implementation passes acceptance tests.
3. Secure metadata repository passes disabled-to-enabled migration tests.
4. Observation history, address index state, UTXO state, labels, notes, backend metadata, Tor routing metadata, Privacy Analyzer state, Recovery metadata, and Nostr identity-linkage metadata are encrypted at rest.
5. Non-secret settings remain separate from wallet metadata.
6. Recovery Center accurately explains what is and is not recoverable.
7. No public backend defaults or Skald-operated infrastructure are introduced.
8. Mainnet remains blocked until a separate release-hardening review explicitly approves it.

## Current Recommendation

Do not implement the vault on the Lazysodium Java/Android candidate in the current project state. The Android duplicate-JNA packaging failure is a concrete blocker.

Keep the Tink plus Bouncy Castle split stack as the only currently packaged, dependency-reviewed, desktop and Android runtime KAT-validated candidate with a test-only provider KAT harness, but do not promote it to production implementation. Its remaining blockers are final KDF parameter approval after candidate-policy review and runtime provider/randomness check review, executable production provider implementation, production provider-level KAT contract execution, lock/session lifecycle tests, migration/corruption tests, production storage review, mainnet release-hardening review, and explicit approval for any executable provider work.

The next focused branch should either:

- review runtime provider/primitive/randomness checks for supported Android and Linux paths,
- design a disabled production-provider skeleton with no storage,
- evaluate IonSpin KMP libsodium packaging and KAT mapping in isolation,
- or investigate a specific Lazysodium/JNA variant-resolution strategy without adding storage or provider implementation.
