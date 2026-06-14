# Encrypted Local Vault Crypto Decision

## Status

This document records the design decisions for Skald Vault's future app-controlled encrypted local vault cryptography and key lifecycle.

This is a decision record only. It does not implement encryption, persist secrets, persist sensitive metadata, enable production sync, create wallets, derive production addresses, sign, broadcast, add Tor transport, add public endpoints, add Skald-operated infrastructure, or enable mainnet.

The dependency spike now pins platform-scoped Tink and Bouncy Castle artifacts for compile/package evaluation only. Those dependencies are not wired into secure storage, secure metadata persistence, production sync, wallet operations, or any vault implementation.

The provider candidate packaging boundary now records how future provider candidates must be named, packaged, source-set isolated, dependency-reviewed, and kept non-selectable until later review. It is still-disabled model evidence only: it does not add dependencies, instantiate Tink, Bouncy Castle, Javax/JCA crypto, Android Keystore, or any provider runtime, run provider operations or KATs, run randomness checks, run KDF/HKDF/HMAC/AEAD, generate or wrap keys, enable vault creation/unlock/persistence, or approve mainnet.

The provider dependency build boundary now records the current build-only dependency spike. This branch adds no new dependency because the existing platform-scoped Tink and Bouncy Castle declarations already provide declared/resolvable build evidence and the selected direction is a split stack. The boundary does not activate dependencies, import provider APIs in common production code, instantiate provider runtime, run provider operations or KATs, run randomness checks, run KDF/HKDF/HMAC/AEAD, add provider implementation or factory code, change provider selection, or enable vault creation/unlock/persistence/mainnet.

Runtime behavior remains fail-closed:

- `SecureSecretStorage` is disabled.
- `SecureWalletMetadataRepository` is disabled.
- `EncryptedVaultReadinessPolicy` reports disabled/not implemented readiness.
- `VaultCryptoProviderSelectionRegistry` selects only the disabled provider.
- Provider candidate packaging evidence cannot make a provider selectable; `productionProviderSelectable` remains false.
- Provider dependency build evidence cannot activate dependencies or make a provider selectable.
- Production sync is disabled.
- Production observation/address-index/UTXO persistence is disabled.

The architecture design is documented in [`ENCRYPTED_LOCAL_VAULT_DESIGN.md`](ENCRYPTED_LOCAL_VAULT_DESIGN.md). The code-level readiness policy models are documented in [`ENCRYPTED_VAULT_READINESS_POLICY.md`](ENCRYPTED_VAULT_READINESS_POLICY.md). The focused dependency spike is documented in [`ENCRYPTED_LOCAL_VAULT_DEPENDENCY_SPIKE.md`](ENCRYPTED_LOCAL_VAULT_DEPENDENCY_SPIKE.md), with desktop and Android runtime known-answer-vector validation recorded in [`ENCRYPTED_LOCAL_VAULT_KAT_VALIDATION.md`](ENCRYPTED_LOCAL_VAULT_KAT_VALIDATION.md). The libsodium/Kotlin packaging comparison is documented in [`ENCRYPTED_LOCAL_VAULT_LIBSODIUM_COMPARISON.md`](ENCRYPTED_LOCAL_VAULT_LIBSODIUM_COMPARISON.md). The candidate dependency, license, Tink keyset/storage, Bouncy Castle Argon2id API, and split-provider review is documented in [`ENCRYPTED_LOCAL_VAULT_DEPENDENCY_REVIEW.md`](ENCRYPTED_LOCAL_VAULT_DEPENDENCY_REVIEW.md). The disabled Skald-owned provider boundary is documented in [`ENCRYPTED_LOCAL_VAULT_CRYPTO_PROVIDER_BOUNDARY.md`](ENCRYPTED_LOCAL_VAULT_CRYPTO_PROVIDER_BOUNDARY.md), the disabled provider-selection boundary is documented in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md), the provider-level KAT contract and still-disabled integrated provider KAT harness are documented in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md), the vault container/manifest/storage contract is documented in [`ENCRYPTED_LOCAL_VAULT_CONTAINER_MANIFEST_STORAGE_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_CONTAINER_MANIFEST_STORAGE_CONTRACT.md), and the test-only provider KAT harness is documented in [`ENCRYPTED_LOCAL_VAULT_TEST_PROVIDER_KAT_HARNESS.md`](ENCRYPTED_LOCAL_VAULT_TEST_PROVIDER_KAT_HARNESS.md). Argon2id calibration policy and probe-only measurement planning is documented in [`ENCRYPTED_LOCAL_VAULT_ARGON2ID_CALIBRATION.md`](ENCRYPTED_LOCAL_VAULT_ARGON2ID_CALIBRATION.md), candidate parameter tiers are documented in [`ENCRYPTED_LOCAL_VAULT_ARGON2ID_PARAMETER_POLICY.md`](ENCRYPTED_LOCAL_VAULT_ARGON2ID_PARAMETER_POLICY.md), manual Android calibration evidence capture is documented in [`ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md), Android compatibility/entropy policy is documented in [`ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md), runtime randomness/provider checks are documented in [`ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md`](ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md), the Tink raw-key feasibility probes are documented in [`ENCRYPTED_LOCAL_VAULT_TINK_RAW_KEY_FEASIBILITY_PROBE.md`](ENCRYPTED_LOCAL_VAULT_TINK_RAW_KEY_FEASIBILITY_PROBE.md), the focused header commitment/AAD construction contract is documented in [`ENCRYPTED_LOCAL_VAULT_HEADER_COMMITMENT_AAD_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_HEADER_COMMITMENT_AAD_CONTRACT.md), the HKDF-SHA-256 key-expansion and HMAC-SHA-256 header-commitment primitive policy is documented in [`ENCRYPTED_LOCAL_VAULT_KEY_EXPANSION_COMMITMENT_POLICY.md`](ENCRYPTED_LOCAL_VAULT_KEY_EXPANSION_COMMITMENT_POLICY.md), deterministic non-secret canonical header/HKDF/HMAC vectors are documented in [`ENCRYPTED_LOCAL_VAULT_CANONICAL_HEADER_HKDF_HMAC_VECTORS.md`](ENCRYPTED_LOCAL_VAULT_CANONICAL_HEADER_HKDF_HMAC_VECTORS.md), and the v1 production-provider acceptance contract is documented in [`ENCRYPTED_LOCAL_VAULT_PRODUCTION_PROVIDER_ACCEPTANCE_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PRODUCTION_PROVIDER_ACCEPTANCE_CONTRACT.md). A metadata-only still-disabled provider facade now records the future provider boundary status and remaining gates, but it is not selectable and exposes no usable vault operation. The still-disabled passphrase policy boundary records future passphrase input, normalization, encoding, retry, throttle, lockout, redaction, clear/wipe, and unlock-prerequisite requirements as blocked evidence only; it does not accept raw passphrases, store passphrases, hash or fingerprint passphrases, run Argon2id or KDF/HKDF/HMAC/AEAD operations, implement retry/throttle/lockout, add passphrase UI, add unlock UI, or enable unlock/persistence/provider selection. The still-disabled clear/wipe strategy boundary records future sensitive value classes, lifecycle triggers, strategy classes, and JVM-zeroization limitations as blocked/model-only evidence only; it does not accept raw sensitive values, hold keys, clear memory, zero memory, prove JVM zeroization, call provider/storage clear functions, invalidate real sessions, add native memory handling, or enable unlock/persistence/provider selection. The still-disabled migration/corruption boundary records future container/version/manifest/storage-index/record evidence categories, failure classes, stale-record and rollback-suspicion review, partial-write handling, quarantine-required evidence, manual-review evidence, redacted failure reporting, and required fail-closed actions as blocked/model-only evidence only; it does not parse real storage, read files, write files, run migration or migration dry-run, repair storage, quarantine records, recover records, verify AEAD tags, decrypt records, verify real header commitments, rewrite manifests or storage indexes, prove rollback resistance, prove crash recovery, or enable unlock/persistence/provider selection. This record resolves the main crypto/key-lifecycle open questions into implementation targets and explicitly marks the items that still require selectable production provider implementation, production provider selection, final KDF parameter approval, Android and Linux runtime provider/randomness checks, entropy-quality review, file-backed container/storage review, manifest file/storage read/write, storage success, storage-backed stale-record enforcement, migration/corruption runtime handling, atomicity/crash recovery, secure-storage boundary implementation, lock/session, passphrase policy runtime review, clear/wipe strategy runtime review, redaction, and release review.

## Decision Summary

| Area | Decision |
| --- | --- |
| Primary storage model | App-controlled encrypted local vault. OS keyrings are never primary storage. |
| Passphrase KDF | Argon2id, calibrated per supported platform. Optional device-class evidence may inform UX and parameter review, but low-end and mid-range Android model testing are not hard compatibility blockers. |
| KDF fallback | scrypt only as a reviewed compatibility fallback; PBKDF2 is not acceptable for the default wallet vault. |
| Record AEAD | XChaCha20-Poly1305 for vault records if dependency review confirms stable Android and Linux desktop support. |
| AES role | AES-GCM or AES-GCM-SIV may be used for platform wrapping or if dependency review rejects XChaCha, but not as the first-choice record envelope. |
| Nonce strategy | Random 24-byte nonce per XChaCha record from OS cryptographic randomness or reviewed crypto-provider randomness; nonce stored in the record envelope; never reuse under the same key. |
| Associated data | Bind ciphertext to non-secret format context only: vault magic/domain marker, vault format version, provider suite id, vault ID, record format policy, AAD policy, key-expansion policy, header-commitment primitive/policy, record type, record ID, record version/counter, header commitment context, and integrity-critical metadata. |
| Key hierarchy | Argon2id derives 64-byte root material; HKDF-SHA-256 expands 32-byte header commitment and 32-byte record AEAD keys under stable labels; no random Tink vault key or persisted Tink keyset is selected for v1. |
| Android wrapping | Optional Android Keystore wrapping for vault key material; app-controlled passphrase/PIN vault remains primary. |
| Linux wrapping | Passphrase vault is primary. libsecret/KWallet are excluded from v1 primary storage and may only become optional wrapping helpers after review. |
| Container and manifest format | Versioned vault container with plaintext unlock header and encrypted catalog/records, plus an integrity-protected future manifest authority for latest local record state. Still-disabled in-memory container and manifest parser/writer building blocks now validate caller-supplied byte arrays and fixed non-secret fixtures, and a local manifest-relative stale-record decision policy classifies current/newer/stale/conflicting/unknown candidate records. No file-backed persistence, manifest file/storage read/write, storage index, or global freshness path exists. |
| Backup/export | Separate encrypted export format with separate backup/export keys and explicit user-selected destination. |
| Migration/corruption | Fail closed, authenticate every encrypted section, preserve old records until migration succeeds, avoid automatic destructive repair. A still-disabled model boundary classifies future container/version/manifest/storage-index/record evidence and required actions, but it does not parse storage, run migration, run repair, quarantine or recover records, verify AEAD/header commitments against real data, prove rollback resistance, prove crash recovery, or enable persistence. |
| Implementation readiness | Not ready. Desktop public KATs pass and Android instrumented runtime KATs passed on Pixel 10 Pro XL / Android 16. Test-only provider KATs also pass through the Skald-owned provider interface on desktop and Android runtime. Dependency/license/package/keyset/split-provider review is complete at candidate level, a disabled Skald-owned provider boundary exists, a metadata-only still-disabled provider facade exists, a disabled provider-selection boundary selects only the disabled provider, a provider-level KAT contract is modeled, and Argon2id calibration policy/probes plus a still-disabled floor/candidate-selection/memory-failure/no-downgrade policy building block, manual Android evidence capture, Android compatibility/entropy policy, runtime randomness/provider check models, desktop and Android Tink raw-key feasibility probes, a focused header commitment/AAD construction contract, and a v1 production-provider acceptance contract exist. Current Android calibration evidence is high-end debug/instrumented only and does not prove all-device performance. Test-only randomness probes prove only availability and non-failing behavior for small non-secret samples; they are not entropy-quality proof and do not implement production randomness. The acceptance contract pins Bouncy Castle Argon2id, Tink XChaCha20-Poly1305, and OS SecureRandom as the v1 review direction, adds the NFC UTF-8 no-whitespace passphrase encoding policy, records `FEASIBLE_PUBLIC_RAW_KEY_API` on desktop/JVM and `ANDROID_FEASIBLE_PUBLIC_RAW_KEY_API` on Android for passphrase-derived raw AEAD key material through public Tink APIs, and requires separate vault header commitment, canonical header encoding, key-separation labels, and strict AAD before record decrypt. Strict AAD serialization, Tink record AEAD construction, in-memory container parsing/writing, in-memory manifest parsing/writing, and local stale-record decision logic now exist as still-disabled building blocks, and a still-disabled integrated provider KAT harness now executes deterministic vectors plus randomized AEAD behavioral KATs in the required order. The model-only storage contracts now require durability to fail closed: unsupported, unknown, unreviewed, insufficient, unsafe, or failed durability blocks encrypted vault persistence, warning-only persistence is rejected, and user consent cannot override durability failure. The still-disabled clear/wipe strategy boundary now models future clearance requirements and explicitly records that JVM/Kotlin zeroization cannot be proven by this model; it does not clear or zero memory, call provider/storage clear functions, or invalidate real sessions. The still-disabled migration/corruption boundary now models future failure classification and required actions for malformed, unsupported, stale, rollback-suspected, partial-write, corrupted, migration-required, and quarantine-required evidence; it does not parse real storage, migrate, repair, quarantine, recover, verify AEAD/header commitments against real data, prove rollback resistance, or prove crash recovery. The still-disabled facade reports this evidence but cannot be selected and cannot create, unlock, persist, store, clear, wipe, migrate, repair, quarantine, recover, or read a vault. Production provider implementation, production provider selection, final bounded KDF parameter approval, Android and Linux runtime provider/randomness checks, selectable provider-boundary approval, storage integration, storage-backed stale-record enforcement, durability proof, migration/corruption runtime handling, atomicity/crash recovery, secure storage, lock/session tests, passphrase policy runtime review, and clear/wipe strategy runtime review are still required before implementation. |

## Candidate Evaluation

### KDF Candidates

#### Argon2id

Decision: preferred passphrase/PIN KDF.

Rationale:

- Memory-hard and designed for password hashing/key derivation.
- Blends Argon2i-style side-channel resistance and Argon2d-style GPU/ASIC resistance.
- Supported by established crypto libraries such as libsodium and Bouncy Castle Java.
- Fits Skald's threat model better than CPU-only stretching because stolen-device attacks can be offline.

Implementation policy:

- Store algorithm ID, salt, memory cost, time cost, parallelism, output length, and calibration profile in the plaintext vault header.
- Use a per-vault random salt.
- Derive a vault key-encryption key, not record encryption keys directly.
- Calibrate separately for Android and Linux desktop.
- Keep an explicit maximum memory cap for low-memory devices.
- Allow parameter upgrades through versioned header migration.

Current calibration status:

- Probe-only candidate rows and bounded desktop/Android measurement harnesses are documented in [`ENCRYPTED_LOCAL_VAULT_ARGON2ID_CALIBRATION.md`](ENCRYPTED_LOCAL_VAULT_ARGON2ID_CALIBRATION.md).
- Non-final candidate tiers are documented in [`ENCRYPTED_LOCAL_VAULT_ARGON2ID_PARAMETER_POLICY.md`](ENCRYPTED_LOCAL_VAULT_ARGON2ID_PARAMETER_POLICY.md): 64 MiB / 3 passes / 1 lane / 64-byte output as the shared v1 floor, 96 MiB / 3 passes / 1 lane / 64-byte output as a stronger desktop candidate, and historical 16/32 MiB timing evidence as below-floor evidence only.
- Manual Android calibration evidence capture is documented in [`ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md). It records optional device-class evidence consistently, but cannot approve calibration or provider-selectable KDF execution.
- Android compatibility planning is documented in [`ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md). It replaces mandatory low-end/mid-range model testing with supported OS baseline policy, runtime provider/primitive/randomness checks, and fail-closed vault creation behavior.
- Runtime randomness/provider checks are documented in [`ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md`](ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md). They prove only test-scope availability of approved OS/reviewed-provider randomness paths, keep hardware-backed key protection separate, forbid language PRNG source classes, and cannot approve production random-byte generation.
- They are not final production parameters and do not enable provider-selectable KDF execution, vault creation, unlock, or storage. The still-disabled calibration policy building block can validate floor/stronger candidates and model fail-closed execution results, but final production calibration approval remains absent.
- Exact starting parameters remain unresolved until timing evidence, memory pressure, UX tradeoffs, provider-level KATs, lock/session tests, and storage review are complete.

#### scrypt

Decision: compatibility fallback only.

Rationale:

- Memory-hard and widely deployed.
- Useful if Argon2id dependency packaging fails on one platform.
- Not the first choice because Argon2id is the preferred modern password-hashing target for the vault.

Implementation policy:

- Must be opt-in by vault format/version policy, not a silent downgrade.
- Must carry a visible degraded-compatibility label in diagnostics and Recovery Center if ever used.
- Must have platform-calibrated CPU/memory parameters before enabling.

#### PBKDF2

Decision: not acceptable for the default production vault.

Rationale:

- Widely available in platform crypto, but not memory-hard.
- Poor fit for Skald's offline stolen-device threat model when used as the primary wallet vault KDF.

Allowed role:

- Only a compatibility bridge or platform wrapping helper if a future migration absolutely requires it.
- Must never be selected silently as a fallback for wallet vault unlock.
- Must not be used for seed/private-key/sensitive-metadata protection without explicit review.

### AEAD Candidates

#### XChaCha20-Poly1305

Decision: preferred vault record AEAD if dependency review confirms stable support on Android and Linux desktop.

Rationale:

- Authenticated encryption with a large nonce space.
- Extended nonce reduces operational collision risk for many independently written records.
- Portable across CPU classes and does not depend on AES hardware acceleration.
- Works naturally with random per-record nonces and record envelopes.

Implementation policy:

- Use one random 24-byte nonce per record.
- Store the nonce outside the ciphertext but inside the authenticated envelope.
- Use associated data to bind the ciphertext to vault format context.
- Use separate keys for metadata records, secret payload records, and backup/export records.
- Reject authentication failures without attempting repair or downgrade.

#### ChaCha20-Poly1305

Decision: not first choice for the vault envelope.

Rationale:

- A sound AEAD when nonce discipline is correct.
- Smaller nonce space makes implementation discipline and counter/state management more important.
- XChaCha20-Poly1305 better fits Skald's multi-record local vault design.

Allowed role:

- Only if the selected dependency cannot support XChaCha20-Poly1305 and a reviewed deterministic/counter nonce strategy is implemented.

#### AES-256-GCM

Decision: acceptable for platform wrapping or as a reviewed fallback, not the first-choice record envelope.

Rationale:

- Well-supported by Android Keystore and Java/JVM crypto providers.
- Hardware acceleration may be strong on some devices.
- Catastrophic nonce reuse risk and provider variability make it less attractive for Skald's record envelope than XChaCha20-Poly1305.

Allowed role:

- Android Keystore wrapping.
- Platform-supported key wrapping or small envelope wrapping where nonce discipline is enforced by the platform.
- Record AEAD only if dependency review rejects XChaCha20-Poly1305 and tests prove safe nonce handling.

#### AES-GCM-SIV

Decision: candidate fallback if dependency support is straightforward.

Rationale:

- Better nonce-misuse resistance than AES-GCM.
- Availability and provider requirements must be verified for Android and Linux desktop before use.

Allowed role:

- Record AEAD fallback or export envelope only after dependency review.
- Not selected for v1 until platform support and packaging are verified.

### Dependency Candidates

#### libsodium

Decision: preferred primitive family only if a future Kotlin/Android/Linux packaging review can provide stable bindings without broad runtime risk. The current Lazysodium Java/Android candidate is rejected for this branch because Android packaging failed with duplicate JNA classes.

Rationale:

- Provides Argon2id, scrypt, XChaCha20-Poly1305, secretstream, and secure-memory-oriented APIs in one primitive family.
- Good fit for the selected algorithm set.

Risks to resolve:

- Kotlin Multiplatform/Android/Linux packaging and native library distribution.
- Debian packaging integration.
- Android ABI coverage.
- Reproducible build and F-Droid-style review.
- Exact Kotlin/JVM binding maintenance status.

Comparison result:

- `com.goterl:lazysodium-java:5.2.0` and `com.goterl:lazysodium-android:5.2.0` expose relevant Argon2id, XChaCha20-Poly1305, secretstream, and KDF APIs, but Android APK packaging failed at duplicate `com.sun.jna.*` classes from simultaneous JNA AAR/JAR variants.
- IonSpin KMP libsodium `0.9.5` artifacts were metadata/POM-inspected but not package-probed; Kotlin metadata, JNA/native-loader behavior, Android native libraries, Linux `.deb` output, and KAT mapping remain unverified.

Decision gate:

- Do not use a libsodium stack for vault implementation until a focused dependency spike confirms Android APK and Linux `.deb` packaging, native library loading, license compatibility, version pinning, and test-vector coverage without duplicate class or hidden native-library conflicts.

#### Google Tink

Decision: selected with Bouncy Castle as the current reviewed candidate only; strong AEAD/keyset candidate, but not a complete KDF solution by itself.

Rationale:

- Provides high-level AEAD APIs and Java/Android support.
- Documentation lists Java support for XChaCha20-Poly1305 and other AEAD key types.
- Can reduce low-level envelope mistakes if the vault design can fit Tink's keyset and wire formats.

Risks to resolve:

- Still needs Argon2id or another memory-hard passphrase KDF from another dependency.
- Tink keyset storage must not become a parallel secret store outside the Skald vault.
- Android and Linux desktop behavior must remain covered by package checks, runtime KATs, and future provider-boundary tests for the project's JVM target and minSdk.
- Some AEAD choices may require extra provider dependencies.

Decision gate:

- Tink is acceptable for AEAD only if the implementation keeps Skald's vault format and secure metadata boundary authoritative, and if a separate Argon2id dependency is selected.

Dependency-spike result:

- Pinned `com.google.crypto.tink:tink-android:1.21.0` for Android.
- Pinned `com.google.crypto.tink:tink:1.21.0` for Linux desktop/JVM.
- Platform compile probes confirm the `XChaCha20Poly1305Key` API is present.
- Desktop KAT validation confirms Tink `1.21.0` can match the public XChaCha draft AEAD vector when using its explicit-nonce internal probe API.
- Desktop and Android raw-key feasibility probing confirms `FEASIBLE_PUBLIC_RAW_KEY_API` and `ANDROID_FEASIBLE_PUBLIC_RAW_KEY_API`: public Tink `1.21.0` APIs can construct an XChaCha20-Poly1305 `Aead` primitive from caller-supplied fixed raw key bytes through a transient in-memory keyset handle, with no persisted keyset, no random Tink-generated vault key, no key rotation, no multiple active keys, no internal APIs, and no reflection. The still-disabled record AEAD building block uses that API family with caller-supplied 32-byte key material, deterministic strict AAD, and fixed non-secret behavior tests. It does not make a provider selectable.
- Android instrumented KAT source uses the same public vector and passed on Pixel 10 Pro XL / Android 16 when the raw ADB IP:port serial was targeted. Earlier connected-device failures were install-signature and stale mDNS target-selection blockers, not vector failures.
- No vault encryption, Tink keyset storage, or production persistence is enabled.

#### Bouncy Castle

Decision: selected with Tink as the current reviewed candidate only; candidate JVM crypto provider, not preferred as the sole vault dependency without further proof.

Rationale:

- Provides broad Java cryptography APIs, including Argon2 APIs and ChaCha20-Poly1305.
- Avoids native library packaging in many JVM contexts.

Risks to resolve:

- XChaCha20-Poly1305 support must be verified before using it for the preferred record envelope.
- Android provider interaction and dependency footprint must be reviewed.
- Lower-level APIs increase misuse risk compared with a smaller high-level vault-specific wrapper.

Decision gate:

- Do not choose Bouncy Castle-only for v1 unless tests prove the selected KDF, AEAD, nonce strategy, and Android/Linux packaging path.

Dependency-spike result:

- Pinned `org.bouncycastle:bcprov-jdk18on:1.84` for Android and Linux desktop/JVM.
- Platform compile probes confirm the `Argon2BytesGenerator` and `ChaCha20Poly1305` APIs are present.
- Desktop KAT validation confirms Bouncy Castle `1.84` `Argon2BytesGenerator` matches the RFC 9106 Argon2id public vector.
- Android instrumented KAT source uses the same RFC 9106 vector and passed on Pixel 10 Pro XL / Android 16 when the raw ADB IP:port serial was targeted. Earlier connected-device failures were install-signature and stale mDNS target-selection blockers, not vector failures.
- Bouncy Castle-only remains insufficient for the preferred XChaCha20-Poly1305 record envelope in this decision record.
- No KDF implementation, vault container, or production persistence is enabled.

#### Platform Crypto Only

Decision: insufficient for the default vault.

Rationale:

- Android Keystore and JVM crypto are useful for wrapping and platform-backed keys.
- Platform crypto does not solve the memory-hard passphrase KDF requirement consistently across Android and Linux desktop.
- A platform-only approach would likely push Skald toward PBKDF2 or AES-GCM-only defaults, which is not the preferred vault design.

Allowed role:

- Android Keystore wrapping.
- OS cryptographic randomness or reviewed crypto-provider randomness.
- Optional platform-backed key wrapping after review.

## Recommended Implementation Path

Recommended path after the dependency spike:

1. Keep the runtime fail-closed.
2. Review the code-level vault readiness/policy models with no storage success paths.
3. Treat the Tink plus Bouncy Castle split stack as the current dependency-reviewed, desktop and Android runtime KAT-validated candidate, not as an implementation-ready vault stack.
4. Use the Argon2id calibration policy/probes, candidate parameter tiers, provider-level KAT contract, and test-only provider KAT harness as planning evidence only; complete final KDF parameter approval, production provider implementation design, production provider-boundary KAT execution, vault container review, storage review, lock/session lifecycle tests, redaction tests, migration/corruption tests, and source-guard tests.
5. Only after those gates pass, implement a disabled vault container parser/validator.

Algorithm recommendation:

```text
Passphrase KDF: Argon2id
Record AEAD: XChaCha20-Poly1305
Record nonce: random 24-byte nonce per record from OS cryptographic randomness or reviewed crypto-provider randomness
Key separation: passphrase-derived root material -> separated header-commitment and record-class keys
Platform wrapping: optional; never primary storage
```

Dependency recommendation:

```text
Current dependency-probe outcome: Tink for XChaCha20-Poly1305 API plus Bouncy Castle for Argon2id API with desktop JVM and Android runtime public KAT validation, plus a separate provider-level KAT contract, test-only provider KAT harness, and still-disabled integrated provider KAT harness. Selectable production-provider KAT approval remains unavailable until a future production provider exists.
Preferred long-term dependency outcome: one reviewed dependency stack that provides Argon2id and XChaCha20-Poly1305 on Android and Linux desktop.
Fallback implementation outcome: Tink for AEAD plus a reviewed Argon2id provider.
Rejected default outcome: platform-only PBKDF2 plus AES-GCM for the wallet vault.
```

## Key Hierarchy Decision

The v1 construction direction is passphrase-derived root material, separated by domain and purpose:

```text
User unlock secret
        ↓
Argon2id
        ↓
Passphrase-derived root material
        ↓
Domain-separated key derivation
        ├── vault header commitment key material
        ├── record AEAD key material
        ├── metadata encryption key
        ├── secret payload encryption key
        ├── backup/export encryption key
        └── future class-specific keys
```

Rules:

- The v1 preferred design derives AEAD key material from passphrase-derived root material.
- Argon2id outputs 64 bytes of root material for v1.
- HKDF-SHA-256 is the selected v1 key-expansion primitive.
- HKDF-SHA-256 outputs a 32-byte header commitment key and a 32-byte record AEAD key.
- HMAC-SHA-256 over canonical vault header bytes is the selected v1 header-commitment primitive.
- The header commitment key material must be separated from record AEAD key material.
- Successful Tink AEAD record decrypt alone must not prove that the passphrase-derived key is the intended vault key.
- The vault header commitment must authenticate canonical header fields before any record decrypt.
- Do not generate random Tink vault keys for v1 production vaults.
- Do not persist plaintext or encrypted Tink keysets in v1 unless raw-key construction is rejected by explicit human review.
- Do not add Tink key rotation or multiple active AEAD keys in v1.
- Record-class keys are derived with domain-separated context labels.
- The v1 key-separation label contract is `skald-vault-v1-key-separation-labels-v1`; required labels are documented in [`ENCRYPTED_LOCAL_VAULT_HEADER_COMMITMENT_AAD_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_HEADER_COMMITMENT_AAD_CONTRACT.md).
- Metadata and secret payload keys are separate.
- Backup/export keys are separate from routine local record keys.
- Platform wrapping may protect unlock convenience material after review, but it does not replace the passphrase as recovery authority.

Primitive policy:

- HKDF-SHA-256 is not the offline-bruteforce defense and must not be used directly on a passphrase. Argon2id and passphrase entropy carry the offline guessing resistance.
- HKDF-SHA-256 avoids manual root-material slicing and gives stable domain-separated expansion points.
- HMAC-SHA-256 authenticates canonical header bytes with the derived header commitment key before record decrypt.
- The Tink raw-key feasibility question has the test-scope outcomes `FEASIBLE_PUBLIC_RAW_KEY_API` on desktop/JVM and `ANDROID_FEASIBLE_PUBLIC_RAW_KEY_API` on Android: pinned public Tink APIs can construct the pinned XChaCha20-Poly1305 primitive from caller-supplied raw key bytes without internal APIs or persisted keysets. The future provider still remains blocked until this evidence is incorporated into a disabled implementation design with provider-level KATs, header commitment, storage review, and explicit human approval.

## Record Envelope Decision

Each encrypted record should use a versioned envelope:

```text
Record envelope
├── envelope magic/version
├── vault format version
├── opaque vault ID
├── opaque record ID
├── record class
├── record schema version
├── key version
├── AEAD algorithm ID
├── nonce
├── ciphertext
└── authentication tag
```

Plaintext envelope fields must be limited to data needed to select the key and verify the record. They must not include wallet labels, transaction notes, real observed addresses, txids, outpoints, endpoint labels, descriptor text, credential references, Nostr identity-linkage metadata, or other sensitive wallet metadata.

Record-specific sensitive metadata belongs inside the encrypted payload or encrypted catalog.

## Associated Data Decision

Associated data should authenticate the non-secret envelope and vault context:

- vault magic/domain marker,
- vault format version,
- provider suite id,
- opaque vault ID,
- record format policy id/version,
- AAD policy id/version,
- opaque record ID,
- record class,
- record version or monotonic counter,
- key commitment/header commitment policy id,
- canonical header commitment value or stable commitment identifier,
- integrity-critical record metadata,
- future storage namespace where relevant.

Associated data must not contain secret payloads, wallet labels, transaction notes, real addresses, real txids, outpoints, backend credential material, Nostr private-key material, or sensitive identity-linkage metadata.

If future implementation needs to authenticate sensitive metadata, that metadata must be encrypted as part of the record payload or encrypted catalog.

AAD mismatch must fail closed for copied ciphertext between vaults, copied ciphertext between record IDs, copied ciphertext between record types, wrong header commitment context, wrong policy version, and stale-record replay where the future version/counter policy rejects stale data. Strict AAD serialization and Tink record AEAD behavior are now still-disabled building blocks.

The v1 provider-level KAT strategy treats record AEAD ciphertext as randomized because Tink chooses XChaCha20-Poly1305 nonces internally. Provider-level KATs must therefore require deterministic passphrase/Argon2id/canonical-header/HKDF/HMAC/strict-AAD vectors and behavioral AEAD checks, not fixed ciphertext hex. Future provider-order KATs must prove header commitment verification before record decrypt and reject record decrypt when header commitment fails.

Full stale-record/rollback enforcement remains deferred to a future trusted manifest, vault index, storage layer, or sync conflict policy. The future manifest must track latest trusted record version/counter per record id, bind vault id, provider suite id, header commitment context, manifest policy id/version, and record namespace, be integrity-protected, update atomically with records or define crash-safe recovery, and reject or quarantine stale/lower-counter records and conflicting duplicate record ids. Skald must not claim full rollback resistance against a rolled-back local storage directory without an external anchor, trusted monotonic counter, append-only log, remote checkpoint, or equivalent anti-rollback anchor.

The storage boundary, platform storage-root contract, safe path-construction contract, symlink/traversal contract, permission/ownership contract, durability-capability contract, durability fail-closed policy, warning-only durability rejection policy, atomic write strategy, crash-recovery behavior, interruption-test points, and storage failure categories are now documented and modeled in [`ENCRYPTED_LOCAL_VAULT_CONTAINER_MANIFEST_STORAGE_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_CONTAINER_MANIFEST_STORAGE_CONTRACT.md). For encrypted vault persistence, unsupported, unknown, unreviewed, insufficient, unsafe, or failed durability blocks persistence; warning-only continuation and user-consent override are not approved for v1 vault writes. A still-disabled namespace/path policy now validates stable identifiers and deterministic relative safe segments only. No filesystem, database, DataStore, SharedPreferences, manifest file read/write, storage index, temp-file, journal, rename, fsync, recovery routine, actual path-construction implementation, path joining, directory creation, platform root selection, symlink checks, permission checks, durability probes, warning-only persistence path, or persistence implementation exists.

## Nonce Strategy Decision

For XChaCha20-Poly1305 records:

- Generate a fresh random 24-byte nonce for every encryption.
- Use platform CSPRNG.
- Store the nonce in the record envelope.
- Treat duplicate nonce detection under the same record-class key as corruption.
- Do not derive nonces from addresses, txids, labels, timestamps, indexes, backend endpoints, or other wallet metadata.
- Do not use deterministic nonces for ordinary records.

For streaming backup/export chunks:

- Prefer a reviewed streaming AEAD API if dependency selection supports it.
- Otherwise use a random stream ID plus monotonically increasing chunk counter authenticated in associated data.
- Treat missing, repeated, reordered, or truncated chunks as backup corruption unless the format explicitly supports random access with authenticated indexing.

## Vault Container Decision

The vault container should contain:

- plaintext unlock header,
- encrypted record catalog,
- encrypted records,
- optional migration journal,
- integrity/authentication data for every encrypted section.

The v1 acceptance contract does not approve a random Tink vault key, persisted Tink keyset, or encrypted Tink keyset. Any future optional wrapping entry must be separately reviewed and must not replace passphrase recovery.

Plaintext unlock header may include:

- container magic,
- format version,
- minimum compatible app version,
- KDF algorithm ID,
- KDF parameter profile,
- salt,
- wrapping method metadata,
- optional wrapping envelope metadata if later reviewed,
- non-secret feature flags required to attempt unlock.

Plaintext unlock header must not include:

- wallet names,
- wallet labels,
- endpoint labels,
- real backend history,
- real observed addresses,
- real txids or outpoints,
- transaction notes,
- Nostr linkage metadata,
- descriptor text,
- credential values,
- secret references that reveal wallet structure.

## Platform Wrapping Decision

### Android

Android Keystore may wrap vault key material where available.

Policy:

- App-controlled vault remains primary.
- Passphrase/PIN unlock remains part of the recoverable vault policy.
- Hardware-backed wrapping is preferred where available.
- StrongBox may be used when available but must not be assumed.
- Biometric unlock is convenience/user-presence only.
- Keystore invalidation must surface as a locked/unavailable state, not as silent data loss.
- Android backup exclusion remains required before real persistence.

### Linux

Linux v1 should use passphrase vault unlock without OS keyring wrapping.

Policy:

- libsecret/KWallet are not primary storage.
- No plaintext key cache in desktop config files.
- Optional keyring wrapping may be reconsidered after vault v1 works and after already-unlocked-session risk is documented in UI.
- If optional keyring wrapping is unavailable, vault unlock falls back to passphrase-only or reports unavailable.

## Passphrase, PIN, And Biometric Decision

Decision:

- Passphrase unlock is required for v1 vault design.
- PIN-only unlock is not accepted for the first production secret-bearing vault.
- PIN may become an additional local convenience factor only when hardware-backed wrapping and lockout semantics are reviewed.
- Biometric unlock is convenience/user-presence only and never authorizes signing, broadcasting, secret export, backup export, or high-risk credential use.

The passphrase policy boundary keeps this decision blocked in code: current passphrase-related requests return typed blocked or rejected evidence only. The boundary may expose policy identifiers such as the selected normalization/encoding policy id, but it does not execute normalization, encoding, Argon2id, hashing, fingerprinting, retry throttling, lockout, memory wiping, biometric unlock, Android Keystore wrapping, OS keyring storage, password-manager integration, vault unlock, or persistence.

Rationale:

- A recoverable passphrase-backed vault is clearer across Android and Linux.
- PIN-only flows can be acceptable only with platform-enforced throttling and hardware binding, which is not cross-platform by default.
- Biometric state changes can invalidate platform keys and should not be the sole recovery path.

## Backup And Export Decision

Backup/export format is separate from the local vault.

Policy:

- Use a dedicated encrypted backup/export container.
- Use backup/export encryption keys separate from local metadata and secret payload keys.
- Use explicit user-selected destination only.
- Do not add Skald-operated backup infrastructure.
- Do not auto-upload.
- Authenticate a backup manifest.
- Include format version and compatibility bounds.
- Support restore dry-run tests before enabling backup as a user-facing feature.
- Keep descriptor export, secret export, and metadata export as separate flows with explicit recovery warnings.

Open implementation detail:

- Whether backup/export keys are derived from a backup passphrase, generated and then wrapped, or both remains unresolved.

## Corruption And Migration Decision

Policy:

- Authentication failure means the record is unavailable.
- Unknown newer vault versions are unavailable, not empty.
- Migration must be a dry-run-capable process.
- Old records remain preserved until the new catalog and records are fully authenticated.
- Partial writes must not leave a vault that appears successfully migrated.
- Automatic destructive repair is forbidden.
- Recovery guidance must be redacted and must not print paths or payload data that reveal local sensitive state.

Implementation target:

- Use write-ahead migration markers or atomic replacement after platform-specific fsync review.
- Keep a migration journal encrypted when it contains record metadata.
- Treat duplicate record IDs, duplicate nonces under one key, catalog/payload mismatch, and bad tags as corruption.

## Randomness And Key Protection Decision

Policy:

- Vault salts, nonces, and any future explicitly random vault material must come from OS cryptographic randomness or reviewed crypto-provider randomness. In the v1 acceptance contract, record AEAD key material is preferred to be derived from passphrase-derived root material rather than generated as a random Tink vault key.
- Kotlin, Java, or general-purpose random APIs must not be used for salts, nonces, future reviewed random vault material, or any other vault secret randomness. Forbidden sources include `kotlin.random.Random`, `java.util.Random`, `Math.random`, timestamps, UUID-derived values, and ad hoc PRNGs.
- Linux compatibility planning requires kernel/OS CSPRNG-backed randomness such as `getrandom`/`urandom` through a reviewed provider or library path.
- Android compatibility planning may use Android OS cryptographic randomness such as `SecureRandom` or a reviewed provider path, but this record does not claim Android random bytes are always hardware-backed.
- Hardware-backed key protection is separate from random-byte generation. Android Keystore/StrongBox and any future Linux hardware-backed wrapping are optional key-protection mechanisms after review, not required entropy sources for basic vault compatibility.
- If approved cryptographic randomness cannot be obtained or verified through the selected platform/provider path, vault creation must fail closed with a user-facing warning.

## Memory Lifecycle Decision

Policy:

- Keep derived root material and record-class keys in scoped session memory only.
- Clear unlocked session state on explicit lock, app close, inactivity timeout, app backgrounding where feasible, and high-risk device state.
- Avoid `String` for secret bytes.
- Minimize copies of passphrase and key material.
- Treat memory clearing as best-effort on Kotlin/JVM and Android.
- Do not claim perfect zeroization.

## Test Vector Strategy

Before implementation can be enabled, tests must include:

- official known-answer vectors for selected KDF and AEAD where available,
- Android runtime execution of those vectors where the production provider will run,
- Skald-owned non-secret envelope test vectors using obvious sentinel values,
- Skald-owned non-secret canonical header, HKDF-SHA-256, and HMAC-SHA-256 vectors matching [`ENCRYPTED_LOCAL_VAULT_CANONICAL_HEADER_HKDF_HMAC_VECTORS.md`](ENCRYPTED_LOCAL_VAULT_CANONICAL_HEADER_HKDF_HMAC_VECTORS.md),
- cross-platform Android/Linux round-trip tests,
- wrong-passphrase tests,
- KDF parameter upgrade tests,
- record authentication failure tests,
- duplicate nonce rejection tests,
- unknown version rejection tests,
- partial-write and migration dry-run tests,
- backup/export restore dry-run tests,
- source guards proving no BDK imports or production networking/process APIs in vault code,
- dependency scans proving crypto dependencies are pinned and reviewed,
- redaction tests for all errors and display strings.

Test fixtures must not include mnemonic words, seed bytes, private descriptors, private keys, WIFs, Nostr private-key material, Lightning credentials, Cashu proof material, real observed addresses, real txids/outpoints, real wallet labels, or real transaction notes.

## Acceptance Criteria Before Implementation

Before using the pinned probe dependencies for executable vault code:

- dependency decision reviewed at production-provider level,
- algorithm choices reviewed at production-provider level,
- license/package review remains current for the release artifacts,
- Android APK and Linux `.deb` packaging implications understood,
- Android runtime verification completed where required; Android test APK assembly alone is not enough,
- known-answer test vectors identified and passing through a selected production executable provider boundary according to [`ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md); current test-only and still-disabled integrated harness evidence is not enough,
- source-guard tests planned,
- secure metadata and secure secret storage remain fail-closed by default,
- no production persistence success path exists.

Before enabling any real persistence:

- vault container and manifest parser/writer evidence reviewed as in-memory building blocks only,
- passphrase KDF calibration tested,
- record AEAD tested,
- platform storage boundary implemented and reviewed,
- platform storage root resolution implemented and reviewed,
- safe path construction, path containment, and directory creation implemented and reviewed,
- symlink/traversal behavior reviewed and checked,
- permission/ownership checks implemented and reviewed,
- durability capability probes implemented and reviewed, with unknown/unsupported/insufficient/unreviewed/unsafe/failed durability blocking encrypted vault writes,
- warning-only encrypted vault persistence and user-consent durability overrides rejected,
- atomic write strategy implemented and interruption-tested,
- crash recovery implemented and corruption-tested,
- typed storage failure mapping implemented,
- namespace/path policy implemented and reviewed,
- Android wrapping tested if used,
- Linux file permissions tested,
- backup exclusion tested,
- corruption/migration tests pass,
- lock/session lifecycle tests pass,
- Recovery/Privacy/Sync status updated,
- no plaintext secret or sensitive metadata writes are possible,
- user explicitly approves enabling the smallest production persistence class.

## Provider Operation Authorization Boundary

Skald Vault v1 now has a still-disabled provider operation authorization boundary as model-only evidence. It classifies future provider operation kinds, operation purposes, required gates, blockers, warnings, disabled capabilities, and redacted policy tokens before any executable provider crypto can exist.

Current provider operation authorization is blocked/fail-closed. The boundary records that the provider registry still selects only `DisabledVaultCryptoProvider`, `productionProviderSelectable` remains false, and no production provider use is approved.

The boundary does not run provider operations, provider KATs, runtime randomness checks, entropy generation, salt generation, nonce generation, key generation, Argon2id, KDF, HKDF, HMAC, AEAD encrypt/decrypt, header commitment computation or verification, record encryption/decryption, manifest authentication, storage-index authentication, key wrapping, key unwrapping, or provider clear/dispose calls. It does not create provider handles, make a provider selectable, enable vault creation, enable vault unlock, enable vault persistence, or approve mainnet.

The boundary accepts only typed policy requests and evidence. It does not accept passphrases, keys, provider handles, entropy/random/salt/nonce bytes, ciphertext, plaintext, record bytes, manifest bytes, storage-index bytes, persisted container bytes, raw paths, filesystem handles, Settings values, backend URLs, descriptors, credentials, or wallet database bytes.

OS keyrings and password managers remain rejected for Skald-managed vault passphrase storage. Passphrase-first remains the default vault authority.

## Runtime Randomness Authorization Boundary

Skald Vault v1 now has a still-disabled runtime randomness authorization boundary as model-only evidence. It classifies future randomness operation kinds, purposes, source kinds, required gates, blockers, warnings, disabled capabilities, and redacted policy tokens before any future branch can obtain entropy, salt, nonce, or key-generation input.

Current runtime randomness authorization is blocked/fail-closed. The boundary records that provider operations remain unauthorized, runtime randomness checks are not production gates, no platform randomness source is reviewed or enabled for production use, the registry still selects only `DisabledVaultCryptoProvider`, and `productionProviderSelectable` remains false.

The boundary does not call `SecureRandom`, Kotlin Random, Java Random, `Math.random`, OS CSPRNG APIs, provider randomness APIs, or randomness health checks. It does not generate entropy, salts, nonces, keys, deterministic vectors, or random byte fixtures; it does not run provider operations, KATs, Argon2id/KDF/HKDF/HMAC, AEAD encrypt/decrypt, header commitment logic, record crypto, key wrapping, vault creation, vault unlock, vault persistence, provider selection, or mainnet.

Future vault secrets, salts, nonces, and keys must use reviewed OS cryptographic randomness/CSPRNG or reviewed provider randomness. General-purpose PRNGs are forbidden for secret/key/nonce/salt material. Android OS CSPRNG/SecureRandom remains future-reviewed only, Android hardware-backed key protection is separate from entropy quality, Linux entropy quality remains a required review gate, OS keyrings and password managers remain rejected for Skald-managed vault passphrase storage, and passphrase-first remains the default authority.

## KDF Calibration Authorization Boundary

Skald Vault v1 now has a still-disabled KDF calibration authorization boundary as model-only evidence. It models future KDF operation kinds, purposes, parameter/evidence kinds, platform/device classes, required gates, blockers, warnings, disabled capabilities, and redacted policy tokens before any future branch can approve Argon2id parameters or authorize KDF execution.

Current KDF calibration authorization is blocked/fail-closed. The boundary records that provider operations remain unauthorized, runtime randomness remains unauthorized, passphrase input remains blocked, final KDF calibration is not approved, the registry still selects only `DisabledVaultCryptoProvider`, `productionProviderSelectable` remains false, and no production KDF execution is allowed.

The boundary does not run Argon2id, run KDFs, run calibration, run benchmarks, inspect real host/device details, approve final KDF parameters, normalize or encode real passphrases, generate or consume salts, call randomness APIs, run provider operations, run provider KATs, derive vault keys, enable vault creation, enable vault unlock, enable vault persistence, approve provider selectability, or approve mainnet. Android calibration and Linux calibration remain future-reviewed only. Test-vector KDF profiles do not authorize production runtime unlock, and mainnet KDF use remains blocked until release review.

## Secure-Storage Authorization Boundary

Skald Vault v1 now has a still-disabled secure-storage authorization boundary as model-only evidence. It models future secure-storage operation kinds, value kinds, target kinds, required gates, blockers, warnings, disabled capabilities, and redacted policy tokens before any future branch can store, retrieve, delete, wrap, unwrap, export, import, migrate, purge, or disclose vault secrets, wrapped keys, sensitive metadata, provider material, manifest metadata, recovery metadata, or session-adjacent state.

Current secure-storage authorization is blocked/fail-closed. The boundary records that persistence readiness is blocked, secure secret storage and secure metadata storage are unavailable, provider operations are unauthorized, runtime randomness authorization is blocked, KDF calibration authorization is blocked, the registry still selects only `DisabledVaultCryptoProvider`, `productionProviderSelectable` remains false, and no target is approved for production secret storage.

The boundary does not store secrets, retrieve secrets, delete secrets, wrap keys, unwrap keys, store wrapped keys, store metadata, export or import backup material, migrate or purge secure storage, use Android Keystore, use Android Credential Manager, use OS keyrings, use password managers, use SharedPreferences, use Settings storage, use files or databases, read or write the encrypted local vault, run provider operations, run KDF/HKDF/HMAC/AEAD, call randomness APIs, enable vault creation, enable vault unlock, enable vault persistence, approve production provider use, or approve mainnet. OS keyrings remain rejected as primary storage, password managers remain rejected for Skald-managed vault passphrase storage, Settings/preferences remain rejected for secrets and sensitive metadata, plaintext diagnostics/export targets remain rejected for raw secret material, Android wrapping and Linux optional key wrapping remain future-reviewed only, and passphrase-first remains the default authority.

## Unresolved Decisions

These remain unresolved and require focused provider-implementation, calibration, or implementation spikes:

- Whether the candidate-reviewed Tink plus Bouncy Castle split stack should become the implementation candidate after final KDF parameter approval, selectable production-provider design, production provider-boundary KAT approval, vault container review, and storage review, or be replaced by a single reviewed stack such as libsodium/KMP.
- Exact production Argon2id parameters remain unresolved; the current candidate tiers are planning evidence only and still need runtime provider/randomness check review, thermal/load, unlock UX, memory-pressure, and release-mode review. Low-end and mid-range Android model testing may still inform parameter choices, but it is not a hard compatibility blocker.
- The still-disabled integrated provider KAT harness now composes the vector-tested passphrase policy, explicit-parameter Argon2id root derivation, canonical header serializer, HKDF-SHA-256 expansion, HMAC-SHA-256 verification, strict AAD serializer, and Tink record AEAD building block. In-memory container/manifest parser/writer and local stale-record decision building blocks now exist. Selectable provider approval remains unresolved because final calibration, storage-backed manifest/latest-counter enforcement, atomicity/crash recovery, secure storage, lock/session lifecycle, and provider selectability remain absent.
- Whether backup/export uses dependency streaming AEAD or a Skald chunked envelope.
- Whether Linux should ever offer optional libsecret/KWallet wrapping after v1.
- Whether Android should require hardware-backed wrapping for specific secret classes or only label risk.
- Whether BDK persistence is ever needed, or whether all required wallet state can be translated into Skald-owned encrypted records.

## References Checked

- Libsodium password hashing documentation: <https://libsodium.gitbook.io/doc/password_hashing>
- Libsodium AEAD documentation: <https://libsodium.gitbook.io/doc/secret-key_cryptography/aead>
- Libsodium encrypted-message guidance: <https://libsodium.gitbook.io/doc/secret-key_cryptography/encrypted-messages>
- Google Tink Java setup documentation: <https://developers.google.com/tink/setup/java>
- Google Tink supported key types: <https://developers.google.com/tink/supported-key-types>
- Android Keystore documentation: <https://developer.android.com/privacy-and-security/keystore>
- Android hardware-backed Keystore overview: <https://source.android.com/docs/security/features/keystore>
- Bouncy Castle Java documentation: <https://www.bouncycastle.org/documentation/documentation-java/>
- RFC 9106 Argon2id test vectors: <https://www.ietf.org/rfc/rfc9106.html#section-5.3>
- XChaCha draft AEAD_XCHACHA20_POLY1305 test vector: <https://datatracker.ietf.org/doc/html/draft-irtf-cfrg-xchacha-02#appendix-A.1>

## Vault Unlock Authorization Boundary

Skald Vault v1 now has a still-disabled unlock authorization boundary as model-only evidence. It models future unlock operation kinds, purposes, credential classes, required gates, blockers, warnings, disabled capabilities, and redacted policy tokens before any future branch can accept a passphrase, run Argon2id/KDF work, request randomness, call provider crypto, read secure storage, read metadata storage, read encrypted vault storage, create decrypted session material, or transition a vault session to active.

Current unlock authorization is blocked/fail-closed. The boundary records that passphrase input is blocked, KDF calibration authorization is blocked, runtime randomness authorization is blocked, provider operations are unauthorized, secure-storage authorization is blocked, storage service operations are disabled, lock/session lifecycle is unavailable, persistence readiness is blocked, the registry still selects only `DisabledVaultCryptoProvider`, `productionProviderSelectable` remains false, and no production unlock execution is allowed.

The boundary does not accept passphrases, PINs, or biometrics; normalize or encode passphrases; run Argon2id/KDF/HKDF/HMAC/AEAD; generate or consume salts, nonces, or random bytes; call provider operations; read secure storage, metadata storage, or encrypted vault storage; retrieve wrapped keys; unwrap keys; decrypt records; create active sessions; hold decrypted key material; persist unlock state; add UI; enable vault creation, vault unlock, vault persistence, approve production provider use, or approve mainnet. OS keyrings remain rejected as primary storage and for Skald-managed passphrase storage, password managers remain rejected for Skald-managed passphrase storage, Settings/preferences storage remains rejected for secrets and unlock state, Android/Linux unlock evidence remains future-reviewed only, and passphrase-first remains the default authority.

## Vault Creation Authorization Boundary

Skald Vault v1 now has a still-disabled creation authorization boundary as model-only evidence. It models future creation operation kinds, purposes, initializer classes, required gates, blockers, warnings, disabled capabilities, and redacted policy tokens before any future branch can create a new encrypted local vault, accept an initial passphrase, run Argon2id/KDF work, request randomness, call provider crypto, create header/container/manifest/storage-index/record state, create secure metadata, store wrapped key material, initialize storage, or create an initial active session.

Current creation authorization is blocked/fail-closed. The boundary records that passphrase input is blocked, KDF calibration authorization is blocked, runtime randomness authorization is blocked, provider operations are unauthorized, secure-storage authorization is blocked, storage service operations are disabled, storage safety is not approved for runtime use, persistence readiness is blocked, unlock authorization is blocked, the registry still selects only `DisabledVaultCryptoProvider`, `productionProviderSelectable` remains false, and no production creation execution is allowed.

The boundary does not accept passphrases, PINs, or biometrics; normalize or encode passphrases; generate salts, nonces, keys, container ids, record ids, or metadata ids; run Argon2id/KDF/HKDF/HMAC/AEAD; call provider operations; create headers, header commitments, containers, manifests, storage indexes, records, secure metadata, wrapped keys, storage namespaces, persistence commits, rollback handlers, failure cleanup, or active sessions; write secure storage, metadata storage, encrypted vault storage, Settings, files, or databases; add UI; enable vault creation, vault unlock, vault persistence, approve production provider use, or approve mainnet. OS keyrings remain rejected as primary storage and for Skald-managed passphrase storage, password managers remain rejected for Skald-managed passphrase storage, Settings/preferences storage remains rejected for secrets, creation state, and unlock state, Android/Linux creation evidence remains future-reviewed only, and passphrase-first remains the default authority.
