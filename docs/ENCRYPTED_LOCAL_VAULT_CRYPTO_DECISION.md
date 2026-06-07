# Encrypted Local Vault Crypto Decision

## Status

This document records the design decisions for Skald Vault's future app-controlled encrypted local vault cryptography and key lifecycle.

This is a decision record only. It does not implement encryption, persist secrets, persist sensitive metadata, enable production sync, create wallets, derive production addresses, sign, broadcast, add Tor transport, add public endpoints, add Skald-operated infrastructure, or enable mainnet.

The dependency spike now pins platform-scoped Tink and Bouncy Castle artifacts for compile/package evaluation only. Those dependencies are not wired into secure storage, secure metadata persistence, production sync, wallet operations, or any vault implementation.

Runtime behavior remains fail-closed:

- `SecureSecretStorage` is disabled.
- `SecureWalletMetadataRepository` is disabled.
- `EncryptedVaultReadinessPolicy` reports disabled/not implemented readiness.
- Production sync is disabled.
- Production observation/address-index/UTXO persistence is disabled.

The architecture design is documented in [`ENCRYPTED_LOCAL_VAULT_DESIGN.md`](ENCRYPTED_LOCAL_VAULT_DESIGN.md). The code-level readiness policy models are documented in [`ENCRYPTED_VAULT_READINESS_POLICY.md`](ENCRYPTED_VAULT_READINESS_POLICY.md). The focused dependency spike is documented in [`ENCRYPTED_LOCAL_VAULT_DEPENDENCY_SPIKE.md`](ENCRYPTED_LOCAL_VAULT_DEPENDENCY_SPIKE.md), with desktop known-answer-vector validation recorded in [`ENCRYPTED_LOCAL_VAULT_KAT_VALIDATION.md`](ENCRYPTED_LOCAL_VAULT_KAT_VALIDATION.md). The libsodium/Kotlin packaging comparison is documented in [`ENCRYPTED_LOCAL_VAULT_LIBSODIUM_COMPARISON.md`](ENCRYPTED_LOCAL_VAULT_LIBSODIUM_COMPARISON.md). This record resolves the main crypto/key-lifecycle open questions into implementation targets and explicitly marks the items that still require dependency review.

## Decision Summary

| Area | Decision |
| --- | --- |
| Primary storage model | App-controlled encrypted local vault. OS keyrings are never primary storage. |
| Passphrase KDF | Argon2id, calibrated per platform and per device class. |
| KDF fallback | scrypt only as a reviewed compatibility fallback; PBKDF2 is not acceptable for the default wallet vault. |
| Record AEAD | XChaCha20-Poly1305 for vault records if dependency review confirms stable Android and Linux desktop support. |
| AES role | AES-GCM or AES-GCM-SIV may be used for platform wrapping or if dependency review rejects XChaCha, but not as the first-choice record envelope. |
| Nonce strategy | Random 24-byte nonce per XChaCha record from platform CSPRNG; nonce stored in the record envelope; never reuse under the same key. |
| Associated data | Bind ciphertext to non-secret format context only: container version, vault ID, record ID, record class, schema version, key version, and envelope flags. |
| Key hierarchy | Passphrase/PIN-derived KEK unwraps a random vault root key; record-class keys are derived from the root key; metadata, secret payload, and backup/export keys are separated. |
| Android wrapping | Optional Android Keystore wrapping for vault key material; app-controlled passphrase/PIN vault remains primary. |
| Linux wrapping | Passphrase vault is primary. libsecret/KWallet are excluded from v1 primary storage and may only become optional wrapping helpers after review. |
| Container format | Versioned vault container with plaintext unlock header and encrypted catalog/records. |
| Backup/export | Separate encrypted export format with separate backup/export keys and explicit user-selected destination. |
| Migration/corruption | Fail closed, authenticate every encrypted section, preserve old records until migration succeeds, avoid automatic destructive repair. |
| Implementation readiness | Not ready. Desktop public KATs now pass, but Android runtime KATs, dependency review, calibration tests, package review, provider-boundary design, and lock/session tests are required before implementation. |

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

Open implementation detail:

- Exact starting parameters are deferred to an implementation spike that measures Android and Linux desktop unlock latency and memory pressure.

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

Decision: selected with Bouncy Castle for a packaging probe only; strong AEAD/keyset candidate, but not a complete KDF solution by itself.

Rationale:

- Provides high-level AEAD APIs and Java/Android support.
- Documentation lists Java support for XChaCha20-Poly1305 and other AEAD key types.
- Can reduce low-level envelope mistakes if the vault design can fit Tink's keyset and wire formats.

Risks to resolve:

- Still needs Argon2id or another memory-hard passphrase KDF from another dependency.
- Tink keyset storage must not become a parallel secret store outside the Skald vault.
- Android and Linux desktop behavior must be verified with the project's JVM target and minSdk.
- Some AEAD choices may require extra provider dependencies.

Decision gate:

- Tink is acceptable for AEAD only if the implementation keeps Skald's vault format and secure metadata boundary authoritative, and if a separate Argon2id dependency is selected.

Dependency-spike result:

- Pinned `com.google.crypto.tink:tink-android:1.21.0` for Android.
- Pinned `com.google.crypto.tink:tink:1.21.0` for Linux desktop/JVM.
- Platform compile probes confirm the `XChaCha20Poly1305Key` API is present.
- Desktop KAT validation confirms Tink `1.21.0` can match the public XChaCha draft AEAD vector when using its explicit-nonce internal probe API.
- No vault encryption, Tink keyset storage, or production persistence is enabled.

#### Bouncy Castle

Decision: selected with Tink for a packaging probe only; candidate JVM crypto provider, not preferred as the sole vault dependency without further proof.

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
- JVM secure random and platform entropy.
- Optional platform-backed key wrapping after review.

## Recommended Implementation Path

Recommended path after the dependency spike:

1. Keep the runtime fail-closed.
2. Review the code-level vault readiness/policy models with no storage success paths.
3. Treat the Tink plus Bouncy Castle split stack as the current desktop KAT-validated candidate, not as an implementation-ready vault stack.
4. Complete Android APK and Linux `.deb` packaging checks, dependency/license review, Android runtime KAT verification, KDF calibration, provider-boundary design, and source-guard tests.
5. Only after those gates pass, implement a disabled vault container parser/validator.

Algorithm recommendation:

```text
Passphrase KDF: Argon2id
Record AEAD: XChaCha20-Poly1305
Record nonce: random 24-byte nonce per record
Key separation: root key -> derived metadata/secret/backup keys
Platform wrapping: optional; never primary storage
```

Dependency recommendation:

```text
Current dependency-probe outcome: Tink for XChaCha20-Poly1305 API plus Bouncy Castle for Argon2id API with desktop JVM public KAT validation.
Preferred long-term dependency outcome: one reviewed dependency stack that provides Argon2id and XChaCha20-Poly1305 on Android and Linux desktop.
Fallback implementation outcome: Tink for AEAD plus a reviewed Argon2id provider.
Rejected default outcome: platform-only PBKDF2 plus AES-GCM for the wallet vault.
```

## Key Hierarchy Decision

The vault key hierarchy is:

```text
User unlock secret
        ↓
Argon2id
        ↓
Vault key-encryption key
        ↓
Encrypted random vault root key
        ↓
Record-class key derivation
        ├── metadata encryption key
        ├── secret payload encryption key
        ├── backup/export encryption key
        └── future class-specific keys
```

Rules:

- The passphrase-derived key encrypts or wraps the random vault root key.
- The vault root key is generated from platform CSPRNG during vault creation.
- The vault root key is not used directly for record AEAD.
- Record-class keys are derived with domain-separated context labels.
- Metadata and secret payload keys are separate.
- Backup/export keys are separate from routine local record keys.
- Platform wrapping may wrap the vault root key or a wrapping key, but it does not replace the user unlock secret.

Open implementation detail:

- The exact key-expansion primitive is deferred to dependency selection. HKDF-SHA-256 or a dependency-provided keyed derivation primitive are acceptable candidates if domain separation is explicit and tests cover cross-platform output.

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

Associated data should authenticate the non-secret envelope context:

- vault format version,
- opaque vault ID,
- opaque record ID,
- record class,
- record schema version,
- key version,
- AEAD algorithm ID,
- envelope flags.

Associated data must not contain secret payloads, wallet labels, transaction notes, real addresses, real txids, outpoints, backend credential material, Nostr private-key material, or sensitive identity-linkage metadata.

If future implementation needs to authenticate sensitive metadata, that metadata must be encrypted as part of the record payload or encrypted catalog.

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
- encrypted vault root key wrapping entries,
- encrypted record catalog,
- encrypted records,
- optional migration journal,
- integrity/authentication data for every encrypted section.

Plaintext unlock header may include:

- container magic,
- format version,
- minimum compatible app version,
- KDF algorithm ID,
- KDF parameter profile,
- salt,
- wrapping method metadata,
- encrypted root key envelope metadata,
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

## Hardware Entropy Decision

Policy:

- Vault root keys, record keys, salts, nonces, and backup keys must come from platform CSPRNG.
- Future production seed generation should detect and prefer hardware-backed/platform CSPRNG entropy where available.
- Hardware entropy detection is not part of this pass.
- If platform entropy is unavailable or fails health checks, vault creation must fail closed.

## Memory Lifecycle Decision

Policy:

- Keep decrypted vault root key and record-class keys in scoped session memory only.
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

Before using the pinned probe dependencies for vault code:

- dependency decision reviewed,
- algorithm choices reviewed,
- license/package review completed,
- Android APK and Linux `.deb` packaging implications understood,
- Android runtime verification completed where required,
- known-answer test vectors identified and passing through the selected provider boundary,
- source-guard tests planned,
- secure metadata and secure secret storage remain fail-closed by default,
- no production persistence success path exists.

Before enabling any real persistence:

- vault container parser and writer implemented behind disabled feature gate,
- passphrase KDF calibration tested,
- record AEAD tested,
- Android wrapping tested if used,
- Linux file permissions tested,
- backup exclusion tested,
- corruption/migration tests pass,
- lock/session lifecycle tests pass,
- Recovery/Privacy/Sync status updated,
- no plaintext secret or sensitive metadata writes are possible,
- user explicitly approves enabling the smallest production persistence class.

## Unresolved Decisions

These remain unresolved and require focused dependency review or implementation spikes:

- Whether the Tink plus Bouncy Castle split stack should become the implementation candidate after Android runtime validation, or be replaced by a single reviewed stack such as libsodium/KMP.
- Exact Argon2id starting parameters and calibration policy.
- Exact key-expansion primitive for record-class keys.
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
