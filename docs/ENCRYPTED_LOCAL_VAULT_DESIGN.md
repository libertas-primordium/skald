# Encrypted Local Vault Design

## Status

This document defines the app-controlled encrypted local vault design that Skald Vault must implement before any production secret persistence, sensitive metadata persistence, production wallet sync, production address index persistence, production UTXO persistence, or Nostr secret-bearing wallet flow can be enabled.

This is a design document only. It does not implement encryption, persist secrets, persist sensitive metadata, create wallets, activate production sync, construct transactions, sign, broadcast, parse Nostr keys, add Tor transport, add public endpoints, add Skald-operated infrastructure, or enable mainnet.

The focused dependency spike for pinned Argon2id/XChaCha20-Poly1305 candidate APIs is documented in [`ENCRYPTED_LOCAL_VAULT_DEPENDENCY_SPIKE.md`](ENCRYPTED_LOCAL_VAULT_DEPENDENCY_SPIKE.md). Desktop and Android runtime known-answer-vector validation is documented in [`ENCRYPTED_LOCAL_VAULT_KAT_VALIDATION.md`](ENCRYPTED_LOCAL_VAULT_KAT_VALIDATION.md). The libsodium/Kotlin packaging comparison is documented in [`ENCRYPTED_LOCAL_VAULT_LIBSODIUM_COMPARISON.md`](ENCRYPTED_LOCAL_VAULT_LIBSODIUM_COMPARISON.md). The Tink/Bouncy dependency, license, keyset/storage, and split-provider review is documented in [`ENCRYPTED_LOCAL_VAULT_DEPENDENCY_REVIEW.md`](ENCRYPTED_LOCAL_VAULT_DEPENDENCY_REVIEW.md). The disabled Skald-owned provider boundary is documented in [`ENCRYPTED_LOCAL_VAULT_CRYPTO_PROVIDER_BOUNDARY.md`](ENCRYPTED_LOCAL_VAULT_CRYPTO_PROVIDER_BOUNDARY.md), the disabled provider-selection boundary is documented in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md), the provider-level KAT contract is documented in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md), and the test-only provider KAT harness is documented in [`ENCRYPTED_LOCAL_VAULT_TEST_PROVIDER_KAT_HARNESS.md`](ENCRYPTED_LOCAL_VAULT_TEST_PROVIDER_KAT_HARNESS.md). Argon2id calibration policy and probe-only measurement planning is documented in [`ENCRYPTED_LOCAL_VAULT_ARGON2ID_CALIBRATION.md`](ENCRYPTED_LOCAL_VAULT_ARGON2ID_CALIBRATION.md), the non-final candidate Argon2id parameter tiers are documented in [`ENCRYPTED_LOCAL_VAULT_ARGON2ID_PARAMETER_POLICY.md`](ENCRYPTED_LOCAL_VAULT_ARGON2ID_PARAMETER_POLICY.md), the manual Android calibration evidence-capture protocol is documented in [`ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md), Android compatibility/entropy gates are documented in [`ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md), and runtime randomness/provider checks are documented in [`ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md`](ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md). Those probes add platform-scoped compile/package dependencies, package review evidence, public-vector tests, disabled provider policy models, a disabled provider-selection registry, a provider-level KAT contract, a test-only provider-interface KAT harness, bounded calibration probes, candidate parameter policy, manual evidence capture, compatibility/entropy policy models, and test-only randomness availability probes only; Android runtime KATs passed on Pixel 10 Pro XL / Android 16 after raw ADB IP:port targeting, and the expanded test-provider harness suite passed on the same runtime. They do not add vault storage, execute provider crypto in production, pass production provider-level KATs, select a non-disabled provider, select final KDF parameters, prove entropy quality, satisfy runtime provider/randomness gates for vault creation, or make secure storage available.

Current runtime behavior remains fail-closed:

- `SecureSecretStorage` is disabled.
- `SecureWalletMetadataRepository` is disabled.
- Production sync is disabled.
- Production observation/address-index/UTXO persistence is disabled.
- `EncryptedVaultReadinessPolicy` reports the vault implementation unavailable, dependency selection reviewed only at candidate level, KDF/AEAD verification incomplete, secure storage disabled, secure metadata disabled, production persistence disabled, and mainnet disabled.
- `DisabledVaultCryptoProvider` reports the provider boundary and provider-level KAT contract modeled but rejects KDF, AEAD, key generation, keyset storage, provider KAT, and persistence operations.
- `VaultCryptoProviderSelectionRegistry` selects only the disabled provider and blocks all future provider candidates from production selection.
- `SkaldVaultV1LockSessionLifecyclePolicy` models future locked, unlock-requested, unlock-blocked, session-expired, lock-required, and forced-locked evidence, but the current unlock decision remains blocked and active sessions remain unavailable.
- `SkaldVaultV1RedactionLeakagePolicy` models future value-kind, output-target, leakage-classification, forbidden-class, allowed-evidence-class, and source-guard material decisions, but it accepts only typed model requests and never accepts raw secrets, passphrases, key material, byte arrays, raw paths, payloads, labels, notes, credentials, or backend handles.
- `SkaldVaultV1PassphrasePolicyGate` models future passphrase input policy, normalization/encoding identifiers, retry/throttle/lockout requirements, redaction requirements, clear/wipe review, and unlock prerequisites, but the current decision remains blocked and no raw passphrase input is accepted.
- `SkaldVaultV1ClearWipeStrategyPolicy` models future sensitive value classes, lifecycle triggers, clear/wipe requirements, strategy classes, and limitations, but every current decision is blocked or model-only and no actual clearing, zeroization, JVM zeroization proof, provider clear call, storage clear call, or session invalidation exists.
- `SkaldVaultV1MigrationCorruptionPolicy` models future container/version/manifest/storage-index/record evidence, failure classes, required fail-closed actions, stale-record and rollback-suspicion review, partial-write handling, migration-required evidence, quarantine-required evidence, and redacted failure reporting, but every current decision remains blocked or model-only and no real storage parsing, migration, repair, quarantine, recovery, AEAD authentication, header-commitment verification, unlock, persistence, provider selection, or mainnet approval exists.

The crypto and key-lifecycle decision record is [`ENCRYPTED_LOCAL_VAULT_CRYPTO_DECISION.md`](ENCRYPTED_LOCAL_VAULT_CRYPTO_DECISION.md). It selects the target algorithm policy at the design level while keeping implementation disabled.

The code-level readiness policy models are documented in [`ENCRYPTED_VAULT_READINESS_POLICY.md`](ENCRYPTED_VAULT_READINESS_POLICY.md). They encode this design as typed disabled/fail-closed status only; they do not implement encryption, storage, unlock UI, or persistence.

The still-disabled lock/session lifecycle boundary is model-only evidence for future session state, timeout policy, background lock, close/shutdown lock, error lock, provider-change lock, storage-readiness-change lock, platform-security-change lock, mainnet request lock/block, redaction, and clear/wipe review gates. It does not accept or store passphrases or PINs, derive keys, hold decrypted keys, generate key material, implement memory wipe/zeroization, implement biometrics, implement Android Keystore, implement OS keyrings, implement password managers, add unlock UI, persist session state, run filesystem checks, construct real paths, read or write files, persist settings, enable vault unlock, enable vault persistence, approve production provider use, or approve mainnet.

The still-disabled redaction/leakage boundary is model-only evidence for future safe-output decisions. It classifies future vault/provider/session/storage/persistence/recovery/source-guard values into forbidden, redacted, summarized, public policy evidence, public non-wallet vector evidence, enum/capability, and count/statistic outcomes across default `toString()`, UI status text, operation results, error summaries, build history, source-guard diagnostics, test assertion messages, future structured logs, future crash reports, future support exports, future backup/export manifests, and future debug panels. It does not accept raw secrets, passphrases, PINs, mnemonics, seeds, private keys, xprv/tprv/WIF/nsec strings, provider keys, vault keys, decrypted records, encrypted record bytes, wallet database bytes, credentials, raw platform paths, endpoints with credentials, stack traces, or byte arrays. It does not hash, fingerprint, encode, partially reveal, log, crash-report, analyze, export, display, persist, or route diagnostic values at runtime. Public non-wallet cryptographic vectors remain allowed only in scoped docs, tests, KAT, and source-guard contexts; wallet, UTXO, sync, source, and production paths still reject hardcoded address, txid, secret, and wallet-material fixtures.

The still-disabled passphrase policy boundary is model-only evidence for future passphrase handling requirements. It models that passphrase input is currently not accepted, that the normalization and encoding policy identifiers are known, that minimum/maximum, control-character, whitespace, NFC, UTF-8, retry, throttle, lockout, memory lifetime, redaction, clear/wipe, and UI-entry policies still require review, and that unlock remains unavailable. It does not accept raw passphrases, PINs, biometrics, key material, passphrase bytes, hashes, fingerprints, files, paths, Settings values, or backend handles. It does not store passphrases, normalize or encode real input, hash or fingerprint passphrases, run Argon2id or any KDF/HKDF/HMAC/AEAD operation, implement retry/throttle/lockout, add passphrase UI, add unlock UI, implement memory wipe/zeroization, implement Android Keystore, implement OS keyrings, implement password managers, enable vault unlock, enable vault persistence, enable provider selection, approve production provider use, or approve mainnet.

The still-disabled clear/wipe strategy boundary is model-only evidence for future clearance and lifecycle cleanup requirements. It classifies future sensitive value categories such as passphrase/PIN buffers, mnemonic or seed material, private-key material, provider/vault/metadata/record/backup/wrapping keys, raw KDF input/output, raw AEAD keys, entropy buffers, decrypted records, staging buffers, provider/storage/session handles, retry/throttle state, diagnostic staging values, sensitive labels/notes, backend credentials, platform-wrapped key references, Android hardware-wrapped handles, and Linux optional key-wrapping handles. It maps future lifecycle triggers such as user lock, timeout, background, foreground-after-lock-required, close/shutdown, error, unlock failure/cancel, provider/KAT/storage/secure-storage/root/path/platform-security changes, mainnet request, migration/corruption, crash recovery, backup/export completion, debug/support diagnostics, record decrypt/encrypt completion, and session query to clear/wipe requirements. Current decisions remain evidence-only and fail-closed. The boundary does not accept raw sensitive values, passphrases, keys, buffers, paths, handles, Settings values, or backend handles; it does not clear real memory, zero real memory, prove JVM zeroization, use native memory APIs, implement provider clear calls, implement storage clear calls, implement session invalidation, add UI, enable vault unlock, enable vault persistence, enable provider selection, approve production provider use, or approve mainnet.

The still-disabled migration/corruption boundary is model-only evidence for future vault container/version/manifest/storage-index/record failure handling. It classifies evidence categories such as header, provider suite, KDF parameter, header commitment, AAD contract, manifest, storage-index, record descriptor, record nonce/counter, ciphertext placeholder, authentication failure, stale-record, rollback-suspicion, interrupted-write, partial-write, orphan/missing/duplicate/conflicting record, unknown future version, unsupported old version, migration-required, migration-plan, corruption-detected, recovery-attempt, quarantine-required, manual-review, and fail-closed evidence. It maps those categories to failure classes and required actions such as fail closed, reject operation, require manual review, require backup before migration, require dry-run migration first, require provider/storage/manifest/storage-index validation, require quarantine/stale/rollback/corruption review, require clear/wipe handling, require redacted diagnostics, require user-visible warning, and require release-hardening review. Current decisions remain evidence-only and fail-closed. The boundary does not accept raw persisted storage, ciphertext, plaintext, nonce/tag/header-commitment bytes, passphrases, keys, paths, provider handles, storage handles, Settings values, or backend handles; it does not parse real persisted storage, read files, write files, repair files, run migration or migration dry-run, quarantine records, recover records, verify AEAD tags, decrypt records, verify real header commitments, rewrite manifests or storage indexes, prove rollback resistance, prove crash recovery, enable vault unlock, enable vault persistence, enable provider selection, approve production provider use, or approve mainnet.

## Design Principles

- The encrypted local vault is the primary storage model for secrets and sensitive wallet metadata.
- OS keyrings are not primary storage. They may wrap vault keys only after explicit design review.
- Linux must prefer the app-controlled encrypted vault over libsecret/KWallet as primary storage because desktop keyrings may stay unlocked and broadly accessible after login.
- Android may use hardware-backed key wrapping where available, but the app-controlled vault and explicit session-lock model still define storage policy.
- All production persistence except general non-sensitive settings/preferences should be encrypted by default once real wallet functionality exists.
- Vault unlock is a session state, not a permanent app state.
- Closing, locking, timing out, or backgrounding the app should clear decrypted vault keys from memory as far as practical.
- Metadata is sensitive even when it is not key material.
- No production wallet operation should silently fall back to plaintext, public infrastructure, clearnet transport, or mainnet.
- No salt, nonce, future reviewed random vault material, unlock-related randomness, or vault record should use language-level or general-purpose randomness. Approved OS or reviewed-provider cryptographic randomness is required where randomness is needed, and hardware-backed key protection is a separate optional wrapping concern.

## Threat Model

The vault should reduce at-rest exposure from:

- stolen Android devices,
- stolen Linux laptops,
- filesystem reads by another local process,
- cloud backup of app files,
- plaintext config leakage,
- logs or crash reports,
- clipboard and screenshot capture,
- partial writes and corrupted local state,
- downgrade or migration bugs,
- public backend or Tor-routing metadata leakage from persisted history.

The vault cannot fully protect against:

- a malicious operating system,
- a fully compromised live device,
- malware reading process memory while the vault is unlocked,
- keylogging or UI capture during unlock,
- malicious firmware or hardware,
- user-approved export/reveal flows.

Skald must present those limitations honestly. The vault protects data at rest and provides a policy boundary; it is not a guarantee against a compromised running system.

## Non-Goals

This design does not enable:

- encrypted vault implementation,
- crypto dependency use beyond explicit disabled probe scope,
- vault unlock or usable active sessions,
- passphrase/PIN/biometric capture or storage,
- runtime logging, crash reporting, analytics, support export, secret hashing, or secret fingerprinting,
- decrypted key material, key generation, or memory-zeroization implementation,
- vault migration, migration dry-run, storage repair, record quarantine, record recovery, real corruption detection, real rollback protection, or real crash recovery,
- production secret persistence,
- production sensitive metadata persistence,
- production BDK persistence,
- production backend sync,
- production receive address generation,
- production address index persistence,
- production UTXO persistence,
- production wallet activation,
- transaction construction,
- PSBT import/export/finalization,
- signing,
- broadcasting,
- Nostr npub/nsec parsing,
- Nostr-derived wallets,
- Lightning credential storage,
- Cashu proof storage,
- Payjoin,
- integrated Tor,
- external Tor connection handling,
- mainnet.

## Data Classification

### Secret Payloads

These require secure secret storage inside the vault:

- app seed material,
- mnemonic material if Skald later uses mnemonic recovery,
- descriptor private keys,
- imported private keys,
- Nostr identity private-key material,
- Lightning credentials,
- NWC secrets,
- backend credentials and cookie material,
- Tor proxy credentials if ever supported,
- Cashu seed and proof material,
- backup encryption keys,
- metadata encryption keys.

### Sensitive Metadata

These require secure metadata storage inside the vault:

- address index state,
- receive-address lifecycle state,
- observed address usage,
- observed UTXO state,
- wallet labels,
- UTXO labels,
- transaction notes,
- wallet notes,
- backend observation history,
- backend endpoint metadata where privacy-sensitive,
- Tor routing policy and transport failure history,
- Nostr identity-linkage metadata,
- Privacy Analyzer state,
- Recovery metadata,
- wallet history and backup state.

### Non-Secret Settings

Existing non-secret settings may continue to store:

- general preferences,
- non-secret backend profile labels,
- normalized endpoint host/port/path fields without credentials,
- backend type and development network selection,
- explicit TLS/scheme/transport labels without credentials,
- trust classification,
- disabled workflow acknowledgements,
- development status preferences.

Non-secret settings must never store:

- secret payloads,
- credential values or userinfo,
- mnemonic words,
- seed bytes,
- private descriptors,
- private keys,
- xprvs,
- Nostr private-key material,
- Cashu proofs,
- Lightning credentials,
- real observed addresses,
- real txids or outpoints,
- real UTXO labels,
- wallet notes,
- transaction notes,
- address index state,
- production observation history.

## Conceptual Vault Layout

The vault should be a versioned local container under the app's reviewed user data directory. Exact file paths are an implementation decision and are not selected in this pass.

Conceptual sections:

- plaintext container header with format version, compatibility bounds, KDF algorithm identifier, KDF parameters, and encrypted record catalog metadata size,
- encrypted record catalog for record descriptors and collection indexes,
- encrypted secret payload records,
- encrypted sensitive metadata records,
- encrypted migration journal or transaction marker,
- integrity/authentication tag data for every encrypted section.

Plaintext header data may include only information needed to attempt unlock and migration. It must not include wallet labels, endpoint labels, addresses, txids, outpoints, descriptors, credential references, backend history, Nostr identity-linkage metadata, or other wallet metadata.

Each encrypted record should carry authenticated associated data that binds it to non-secret format facts such as vault format version, record type, record version, and collection class. The exact associated-data fields must be reviewed during implementation.

## Key Hierarchy

The intended hierarchy is:

```text
User unlock secret
        ↓
Passphrase/PIN key derivation
        ↓
Passphrase-derived root material
        ↓
Domain-separated key material
        ├── vault header commitment key material
        ├── record AEAD key material
        ↓
Derived or wrapped record-class keys
        ├── metadata encryption key
        ├── secret payload encryption key
        ├── backup/export encryption key
        └── optional per-record subkeys
```

Optional platform wrapping may protect convenience unlock material after review:

```text
Platform wrapping key
        ↓
Wrapped vault key material
```

Platform wrapping is optional defense-in-depth. It must not replace the app-controlled vault policy or session lock model.

For the v1 production-provider acceptance contract, Skald prefers passphrase-derived raw AEAD key material and no persisted Tink keyset, provided public supported Tink APIs can construct the pinned XChaCha20-Poly1305 primitive from caller-supplied derived key bytes. The vault must verify a separate header commitment over canonical header fields before any record decrypt because Tink XChaCha20-Poly1305 is non-key-committing.

## Key Derivation

Future implementation must use Argon2id as the target password-based key derivation function. scrypt is a reviewed compatibility fallback only. PBKDF2 is not acceptable as the default production vault KDF because it is not memory-hard.

The detailed decision, fallback policy, and dependency gates are in [`ENCRYPTED_LOCAL_VAULT_CRYPTO_DECISION.md`](ENCRYPTED_LOCAL_VAULT_CRYPTO_DECISION.md).

Requirements:

- Store KDF algorithm and parameters in the vault header.
- Tune parameters separately for Android and Linux.
- Support KDF parameter upgrades.
- Treat wrong passphrase/PIN as a locked/unavailable state.
- Never log unlock material, derived keys, salts, or intermediate values.
- Never keep user passphrases in process-global state.
- Use OS cryptographic randomness or reviewed crypto-provider randomness for salts, nonces, and any future reviewed random vault material.
- Do not use Kotlin, Java, or general-purpose random APIs such as `kotlin.random.Random`, `java.util.Random`, `Math.random`, timestamps, UUID-derived values, or ad hoc PRNGs for vault salts, nonces, future reviewed random vault material, unlock-related randomness, or vault records.
- Treat hardware-backed key protection separately from random-byte generation. Android Keystore/StrongBox and possible future Linux hardware-backed wrapping are optional key-protection mechanisms after review; they are not required entropy sources for basic vault compatibility.

## Record Encryption

Future record encryption must use authenticated encryption. The target vault record AEAD is XChaCha20-Poly1305 with a random per-record nonce if dependency review confirms stable Android and Linux desktop support. AES-GCM or AES-GCM-SIV may be used for platform wrapping or as a reviewed fallback, but XChaCha20-Poly1305 is the preferred record-envelope design.

The detailed nonce, associated-data, envelope, and dependency policy is in [`ENCRYPTED_LOCAL_VAULT_CRYPTO_DECISION.md`](ENCRYPTED_LOCAL_VAULT_CRYPTO_DECISION.md).

Requirements:

- Unique nonce per encrypted record.
- Separate record classes for secrets and sensitive metadata.
- No reuse of encryption keys for incompatible data classes.
- Authentication failure must fail closed.
- Decryption errors must not erase records automatically.
- `toString()`, logs, errors, and test assertions must remain redacted.
- Secret payload records and sensitive metadata records must not be stored in existing non-secret settings storage.

## Secret Payload Encryption

Secret payload encryption covers raw secret-bearing material and should use the secret payload encryption key or per-record keys derived from it.

Secret payload records should include encrypted payload plus encrypted metadata needed to validate class, origin, recovery warnings, and deletion behavior. Non-secret settings may reference a redacted record ID only if that reference cannot reveal the secret value.

Secret payload use must require:

- vault unlocked,
- record class permitted for the operation,
- user-presence policy satisfied where required,
- operation-specific confirmation for signing, exporting, deleting, or high-risk credential use.

Unlocking a vault must never imply signing approval, broadcast approval, secret export approval, Lightning payment approval, Cashu melt/mint approval, or backend credential privilege escalation.

## Metadata Encryption

Sensitive metadata encryption covers wallet behavior and history:

- observation history,
- address index state,
- used-address state,
- UTXO state,
- labels,
- transaction notes,
- backend metadata,
- privacy-analysis state,
- recovery metadata,
- identity-linkage metadata.

The metadata encryption key must be unavailable while the vault is locked. Production sync must remain blocked until the metadata repository can durably and safely persist address index and observation state.

Metadata encryption does not make public-backend use private. It only protects local persisted history. Backend trust/privacy warnings must still be shown.

## Backup And Export Encryption

Backups are separate from local vault storage.

Future backup/export design should include:

- encrypted local export format,
- explicit user-selected destination,
- no Skald-operated backup service,
- no automatic upload,
- backup format version,
- backup manifest integrity checks,
- test restore command or flow,
- separate handling for descriptor exports, metadata backups, and secret backups,
- clear warnings that one seed does not restore every rail.

Backup/export encryption should use backup/export key material distinct from routine record encryption. Backup keys must themselves have a recovery plan.

Raw secret export, if ever allowed, must be deliberate, high-friction, short-lived, and clearly outside normal safe operation.

## Unlock And Lock Lifecycle

The intended lifecycle:

1. App starts with vault locked.
2. User unlocks with passphrase/PIN and optional platform authentication.
3. Passphrase-derived root material is available only in scoped session memory after successful header-commitment verification.
4. Only requested record-class keys are made available.
5. Operations use keys through scoped service calls.
6. Inactivity timeout, explicit lock, app backgrounding, app close, or high-risk device state clears unlocked vault state as far as practical.
7. Future operations require unlock again.

Session rules:

- Do not unlock automatically at process start.
- Do not keep decrypted keys available after user locks the app.
- Do not unlock secret classes merely because metadata viewing is allowed.
- Do not allow background sync to keep the vault unlocked indefinitely.
- Do not silently downgrade to metadata-only plaintext mode if unlock fails.

Memory clearing is best-effort on managed runtimes. Kotlin/JVM and Android cannot guarantee perfect zeroization for all objects. The implementation must minimize key lifetime, avoid string representations for sensitive bytes, avoid unnecessary copies, and state the limitation honestly.

## Android Strategy

Android strategy:

- app-controlled encrypted local vault is primary,
- Android OS cryptographic randomness or reviewed crypto-provider randomness is required for vault random bytes; do not claim Android randomness is always hardware-backed,
- Android Keystore may wrap vault key material where available,
- hardware-backed wrapping should be preferred where available,
- StrongBox may be used where available but must not be assumed universally present,
- biometric unlock is convenience/user-presence only,
- passphrase/PIN policy must remain compatible with recovery and session lock requirements,
- backgrounding or locking should clear unlocked vault state as far as practical,
- release builds must be non-debuggable,
- Android backup exclusion must be verified before real secret persistence is enabled,
- screenshot blocking should be available for reveal/export screens,
- clipboard use for secrets should be avoided; if ever allowed, it must be warned and short-lived,
- rooted/debuggable-device warnings should be surfaced where feasible.

Android Keystore invalidation on reinstall, device reset, OS changes, profile removal, or biometric enrollment changes must be handled as a locked/unavailable recovery state rather than data loss hidden behind vague errors.

## Linux Strategy

Linux strategy:

- app-controlled encrypted local vault is primary,
- Linux vault random bytes must come from kernel/OS CSPRNG-backed randomness such as `getrandom`/`urandom` through a reviewed provider or library path,
- Kotlin/JVM general-purpose random APIs, timestamps, UUID-derived values, and ad hoc PRNGs must not be used for vault salts, nonces, future reviewed random vault material, unlock-related randomness, or vault records,
- libsecret/KWallet are not primary storage,
- OS keyrings may wrap vault keys only after explicit design review,
- passphrase unlock must be supported,
- no plaintext secrets in config files,
- no plaintext sensitive metadata in desktop config files,
- encrypted vault files should use restrictive permissions,
- directory creation must account for umask and multi-user systems,
- Linux packaging must not place wallet data in the application package,
- desktop keyrings may remain unlocked after login and may be broadly accessible to same-user processes, so they are not sufficient primary wallet storage.

Linux clipboard and screenshot limitations must be documented. Future secret reveal/export views should warn that same-user malware or screen capture can still observe data while the app is unlocked.

## OS Keyring Role

OS keyrings may be used only as optional wrapping helpers after review.

Allowed future role:

- wrap a vault key-encryption key,
- cache an unlock helper when the user explicitly permits it,
- provide additional platform protection where the threat model supports it.

Forbidden role:

- primary storage for wallet seeds,
- primary storage for private keys,
- primary storage for sensitive metadata,
- unreviewed default unlock persistence,
- hidden fallback when the app-controlled vault fails.

If keyring wrapping is unavailable, Skald must either use passphrase-only vault unlock or report unavailable. It must not create plaintext secret or metadata storage.

## Passphrase, PIN, And Biometric Policy

The v1 passphrase validation/normalization policy is implemented as a still-disabled building block. PIN policy, biometric unlock, passphrase/PIN UI, vault unlock, and provider wiring are not implemented.

Design requirements:

- passphrase/PIN unlock must protect passphrase-derived root material,
- biometric unlock may be convenience only,
- biometric success must not authorize signing, broadcasting, secret export, backup-key export, or high-risk credential use,
- failed unlock must not reveal more metadata than safe status labels,
- passphrase changes must rewrap keys without decrypting and rewriting records unnecessarily where possible,
- passphrase loss must have clear recovery implications.

Open choice: whether Skald requires a full passphrase, allows PIN plus hardware-backed wrapping, or supports both with explicit risk labels.

## Tor And Network Metadata

Tor transport policy is privacy-sensitive metadata once production networking exists.

The vault must protect:

- integrated Tor service settings,
- external Tor/Orbot/local Tor daemon settings,
- proxy endpoint metadata when privacy-sensitive,
- onion-only routing policy,
- all-wallet-network-traffic-through-Tor routing policy,
- Tor-required fail-closed decisions,
- transport failure history when it can reveal wallet behavior,
- backend endpoint privacy metadata.

Routing policy must distinguish:

- direct transport,
- external Tor transport,
- future integrated Tor transport,
- onion-only routing,
- all-wallet-traffic-through-Tor routing.

If Tor is required and unavailable, wallet network operations must fail closed rather than falling back to clearnet. This design does not implement Tor transport.

## BDK Persistence Implications

BDK remains an implementation detail.

If future BDK wallet/database persistence is required for production:

- it must be encrypted,
- it must be inside or protected by the app-controlled vault design,
- BDK types must not leak into common UI/settings/persisted models,
- BDK must not create hidden backend defaults,
- BDK must not create hidden mainnet paths,
- BDK persistence must not bypass Skald's secure metadata repository.

Until that design is implemented and reviewed, production BDK persistence remains disabled.

## Recovery Implications

The vault is not a substitute for recovery design.

Recovery Center must continue to distinguish:

- native seed recovery,
- descriptor export recovery,
- imported-key backup,
- Lightning channel-state backup,
- Cashu proof/state recovery,
- remote-node external backup,
- metadata backup,
- encrypted backup export.

Losing vault unlock material may make local metadata unreadable. Losing seed material may lose funds. Losing address index state may make wallet reconstruction slower or incomplete until rescan. Losing Cashu proof material or Lightning channel state can have rail-specific consequences that are not fixed by an on-chain seed.

Skald must never imply that one seed phrase restores every rail.

## Corruption And Partial Writes

Future implementation must handle:

- interrupted writes,
- truncated files,
- corrupted records,
- bad authentication tags,
- unknown future versions,
- migration failure,
- rollback attempts,
- duplicate record IDs,
- catalog/payload mismatch.

Policy:

- fail closed,
- preserve old records until migration succeeds,
- avoid automatic destructive repair,
- expose redacted recovery guidance,
- keep backups and export verification separate from routine storage.

Atomic replace and fsync behavior should be reviewed per platform before enabling production persistence.

## Migration And Versioning

Vault format must be versioned from the first implementation.

Required versioned items:

- container header,
- KDF parameters,
- key hierarchy,
- encrypted record format,
- metadata schema,
- secret payload schema,
- backup/export format,
- migration journal format,
- platform wrapping policy.

Unknown newer versions should be unavailable, not treated as empty. Downgrades must not silently reinterpret newer encrypted data.

The current `SkaldVaultV1MigrationCorruptionPolicy` is only the still-disabled evidence model for these future rules. It does not parse real persisted storage, run migration or migration dry-run, repair storage, quarantine or recover records, verify AEAD tags, verify real header commitments, rewrite manifests or storage indexes, prove rollback resistance, prove crash recovery, or enable persistence.

## Testing Strategy

Before enabling real vault storage, tests must cover:

- disabled/fail-closed state remains available,
- no crypto/storage dependency is added without review,
- passphrase/PIN wrong-entry behavior,
- lock, timeout, background, and app-close behavior,
- redaction for payloads, metadata, errors, and logs,
- no plaintext secret writes,
- no plaintext sensitive metadata writes,
- Android backup exclusion,
- Android Keystore wrapping behavior if used,
- Linux file permissions and directory permissions,
- OS keyring unavailable/locked behavior if wrapping is used,
- corrupted record behavior,
- partial write behavior,
- migration dry runs,
- unknown version behavior,
- concurrent access behavior,
- deletion and wipe behavior,
- Recovery Center status,
- Privacy Analyzer status,
- production sync preflight blockers,
- BDK type confinement,
- no public endpoint defaults,
- no mainnet persistence path.

Tests must use deterministic local fakes, regtest, signet, or obvious sentinel values. They must not use mainnet or real wallet material.

## Acceptance Criteria Before Enabling Persistence

Production secret or sensitive metadata persistence may not be enabled until:

- this design is reviewed,
- [`ENCRYPTED_LOCAL_VAULT_CRYPTO_DECISION.md`](ENCRYPTED_LOCAL_VAULT_CRYPTO_DECISION.md) is reviewed,
- crypto dependency choice is reviewed,
- KDF and AEAD parameters are reviewed,
- known-answer vectors pass through the selected provider boundary on required platforms,
- vault container format is implemented and tested,
- Android strategy is tested if Android storage is enabled,
- Linux strategy is tested if Linux storage is enabled,
- no plaintext secret or sensitive metadata write is possible,
- non-secret settings remain separate from wallet metadata,
- redaction tests pass,
- corruption and partial-write tests pass,
- migration tests pass,
- lock/session lifecycle tests pass,
- Recovery Center reflects vault and backup status,
- Privacy Analyzer reflects metadata persistence and Tor-routing privacy status,
- production sync preflight remains blocked unless the vault is unlocked and policy permits persistence,
- mainnet remains disabled until explicit release-hardening approval,
- the user explicitly approves enabling implementation work.

## Open Questions

- Whether the candidate-reviewed Tink plus Bouncy Castle split stack from [`ENCRYPTED_LOCAL_VAULT_DEPENDENCY_REVIEW.md`](ENCRYPTED_LOCAL_VAULT_DEPENDENCY_REVIEW.md) should become the implementation candidate after final KDF calibration, executable-provider design, the provider-level KAT contract in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md) passes through that executable provider, vault container review, and storage review.
- Whether the candidate Argon2id parameter tiers from [`ENCRYPTED_LOCAL_VAULT_ARGON2ID_PARAMETER_POLICY.md`](ENCRYPTED_LOCAL_VAULT_ARGON2ID_PARAMETER_POLICY.md) remain acceptable after runtime provider/randomness check review, thermal/load, unlock UX, memory-pressure, and release-mode review. Low-end and mid-range model testing may be collected as optional parameter evidence but is no longer a hard compatibility blocker.
- Should Android require hardware-backed wrapping for specific secret classes or make it an optional risk label?
- How should Linux vault files be located, permissioned, backed up, and migrated?
- Should optional Linux OS keyring wrapping be offered after v1, and how should already-unlocked-session risk be displayed?
- Should Skald ever support PIN-only unlock after hardware-backed wrapping review, or keep passphrase unlock mandatory?
- How should backup/export keys be generated, rotated, and recovered?
- Should BDK persistence ever be allowed directly, or should Skald translate all necessary state into Skald-owned encrypted records?
- How should all-wallet-traffic-through-Tor policy interact with background sync once sync exists?
- How should vault unlock state interact with future Lightning background services?
- How should Cashu proof updates be made atomic without plaintext exposure?
- How should Nostr identity-linked metadata be retained or intentionally forgotten after sweep/recovery flows?

## Implementation Sequence

Recommended next implementation sequence:

1. Review this design plus [`ENCRYPTED_LOCAL_VAULT_CRYPTO_DECISION.md`](ENCRYPTED_LOCAL_VAULT_CRYPTO_DECISION.md).
2. Review the code-level vault policy/readiness models in [`ENCRYPTED_VAULT_READINESS_POLICY.md`](ENCRYPTED_VAULT_READINESS_POLICY.md).
3. Keep tests proving secure storage, secure metadata, and vault readiness remain disabled.
4. Use the completed Tink/Bouncy dependency review, disabled provider boundary, provider-level KAT contract, Argon2id calibration policy/probes, and candidate parameter policy as candidate evidence, then complete final KDF parameter approval, executable-provider design, provider-boundary KAT execution, vault container review, and storage review, or choose a replacement stack through explicit review.
5. Keep the narrow disabled provider boundary documented in [`ENCRYPTED_LOCAL_VAULT_CRYPTO_PROVIDER_BOUNDARY.md`](ENCRYPTED_LOCAL_VAULT_CRYPTO_PROVIDER_BOUNDARY.md) as the only provider surface until final KDF calibration and the provider-level KAT contract in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md) pass through an executable Skald-owned provider.
6. Implement a disabled vault container parser/validator without storing real secrets.
7. Implement encrypted vault storage behind a disabled feature gate.
8. Add lock/session lifecycle tests.
9. Add Android wrapping tests where applicable.
10. Add Linux file-permission and optional keyring-wrapping tests where applicable.
11. Add migration, corruption, and partial-write tests.
12. Update Recovery/Privacy/Sync status surfaces.
13. Enable only the smallest low-risk persistence path after explicit approval.

Do not enable production wallet creation, production sync, production address derivation, production UTXO persistence, signing, broadcasting, Nostr secret-bearing flows, public endpoint defaults, or mainnet as part of vault design work.

## Provider Operation Authorization Boundary

`SkaldVaultV1ProviderOperationAuthorizationPolicy` is the still-disabled v1 model for future crypto-provider operation authorization. It classifies typed provider operation requests by operation kind, operation purpose, required gate, blocker, warning, and disabled capability. Current authorization is always blocked/fail-closed because the registry still selects only `DisabledVaultCryptoProvider` and `productionProviderSelectable` remains false.

This model does not run provider operations, provider KATs, runtime randomness checks, entropy/salt/nonce/key generation, Argon2id/KDF/HKDF/HMAC, AEAD encrypt/decrypt, header commitment computation or verification, record encrypt/decrypt, manifest or storage-index authentication, key wrapping/unwrapping, provider clear/dispose, vault creation, vault unlock, vault persistence, provider selection, or mainnet. It does not accept raw passphrases, keys, ciphertext, plaintext, storage bytes, provider handles, paths, Settings values, OS keyring values, or password-manager values. OS keyrings and password managers remain rejected for Skald-managed vault passphrase storage, and passphrase-first remains the default authority.

## Runtime Randomness Authorization Boundary

`SkaldVaultV1RuntimeRandomnessAuthorizationPolicy` is the still-disabled v1 model for future runtime entropy, OS CSPRNG, reviewed provider randomness, hardware-backed entropy evidence, salt generation, nonce generation, key-generation input, KDF salt, AEAD nonce, platform entropy review, and test-vector scope. It classifies typed requests by operation kind, purpose, source kind, required gate, blocker, warning, disabled capability, and redacted policy token.

Current runtime randomness authorization is blocked/fail-closed for every operation. The boundary does not call `SecureRandom`, Kotlin Random, Java Random, `Math.random`, OS CSPRNG APIs, provider RNG APIs, or randomness health checks. It does not generate entropy, salts, nonces, keys, random byte fixtures, deterministic vectors, provider operations, KATs, KDF/HKDF/HMAC/AEAD, vault creation, vault unlock, vault persistence, provider selection, or mainnet.

Future acceptable randomness for secrets, salts, nonces, and keys must come from reviewed OS cryptographic randomness/CSPRNG or reviewed provider randomness. General-purpose PRNGs are forbidden for vault material. Android OS CSPRNG/SecureRandom remains future-reviewed only, Android hardware-backed key protection is separate from entropy quality, Linux entropy quality remains a required review gate, provider selection still selects only `DisabledVaultCryptoProvider`, and `productionProviderSelectable` remains false.

## KDF Calibration Authorization Boundary

`SkaldVaultV1KdfCalibrationAuthorizationPolicy` is the still-disabled v1 model for future Argon2id/KDF calibration authorization, parameter finalization, platform/device-class review, and KDF execution authorization. It classifies typed requests by KDF operation kind, purpose, parameter/evidence kind, platform/device class, required gate, blocker, warning, disabled capability, and redacted policy token.

Current authorization is blocked/fail-closed for every KDF/calibration operation. The boundary does not run Argon2id, run KDFs, run calibration, run benchmarks, inspect real host/device details, approve final KDF parameters, normalize or encode real passphrases, generate or consume salts, call randomness APIs, run provider operations, run provider KATs, derive vault keys, enable vault creation, enable vault unlock, enable vault persistence, approve provider selectability, or approve mainnet.

KDF authorization remains unavailable because provider operation authorization is blocked, runtime randomness authorization is blocked, passphrase input remains blocked, final KDF calibration is not approved, `productionProviderSelectable` remains false, and no production KDF execution is allowed. Android and Linux calibration remain future-reviewed only, test-vector profiles do not authorize production runtime unlock, mainnet KDF use remains blocked until release review, OS keyrings and password managers remain rejected for Skald-managed vault passphrase storage, and passphrase-first remains the default authority.

## Secure-Storage Authorization Boundary

`SkaldVaultV1SecureStorageAuthorizationPolicy` is the still-disabled v1 model for future secure-storage authorization. It classifies typed requests by secure-storage operation kind, sensitive value kind, storage target kind, required gate, blocker, warning, disabled capability, and redacted policy token before any future branch can store, retrieve, delete, wrap, unwrap, export, import, migrate, purge, or disclose vault secrets or sensitive metadata.

Current secure-storage authorization is blocked/fail-closed for every operation. The boundary does not store secrets, retrieve secrets, delete secrets, wrap keys, unwrap keys, store wrapped keys, store metadata, export backup material, import backup material, migrate secure storage, purge secure storage, use Android Keystore, use Android Credential Manager, use OS keyrings, use password managers, use SharedPreferences, use Settings storage, use files or databases, read or write the encrypted local vault, run provider operations, run KDF/HKDF/HMAC/AEAD, call randomness APIs, enable vault creation, enable vault unlock, enable vault persistence, approve provider selectability, or approve mainnet.

Secure-storage authorization remains unavailable because persistence readiness is blocked, secure storage and secure metadata are unavailable, provider operations are unauthorized, runtime randomness authorization is blocked, KDF calibration authorization is blocked, `productionProviderSelectable` remains false, and no target is approved for production secret storage. OS keyrings remain rejected as primary storage and for Skald-managed passphrase storage, password managers remain rejected for Skald-managed passphrase storage, Settings/preferences remain rejected for secrets and sensitive metadata, plaintext storage/logs/crash reports/analytics/support exports remain rejected for raw secret material, Android hardware-backed wrapping remains future-reviewed only, Linux optional key wrapping remains future-reviewed only, and passphrase-first remains the default authority.

## Vault Unlock Authorization Boundary

`SkaldVaultV1UnlockAuthorizationPolicy` is the still-disabled v1 model for future vault unlock authorization. It classifies typed requests by unlock operation kind, purpose, credential class, required gate, blocker, warning, disabled capability, and redacted policy token before any future branch can accept a passphrase, run KDF work, request randomness, call provider crypto, read secure storage, read metadata storage, read encrypted vault storage, create decrypted session material, or transition a vault session to active.

Current unlock authorization is blocked/fail-closed for every operation. The boundary does not accept passphrases, PINs, or biometrics; normalize or encode passphrases; run Argon2id/KDF/HKDF/HMAC/AEAD; generate or consume salts, nonces, or random bytes; call provider operations; read secure storage, metadata storage, or encrypted vault storage; retrieve wrapped keys; unwrap keys; decrypt records; create active sessions; hold decrypted key material; persist unlock state; add UI; enable vault creation, vault unlock, vault persistence, provider selectability, or mainnet.

Unlock authorization remains unavailable because passphrase input is blocked, KDF calibration authorization is blocked, runtime randomness authorization is blocked, provider operations are unauthorized, secure-storage authorization is blocked, storage service operations are disabled, lock/session lifecycle is unavailable, persistence readiness is blocked, provider selection still selects only `DisabledVaultCryptoProvider`, and `productionProviderSelectable` remains false. OS keyrings remain rejected as primary storage and for Skald-managed passphrase storage, password managers remain rejected for Skald-managed passphrase storage, Settings/preferences storage remains rejected for secrets and unlock state, Android unlock and hardware-wrapper evidence remains future-reviewed only, Linux unlock and optional-wrapper evidence remains future-reviewed only, mainnet unlock remains blocked until release review, and passphrase-first remains the default authority.

## Vault Creation Authorization Boundary

`SkaldVaultV1CreationAuthorizationPolicy` is the still-disabled v1 model for future encrypted local vault creation authorization. It classifies typed requests by creation operation kind, purpose, initializer class, required gate, blocker, warning, disabled capability, and redacted policy token before any future branch can accept an initial passphrase, run KDF work, request randomness, call provider crypto, initialize container/header/manifest/storage-index/record state, create secure metadata, store wrapped key material, initialize storage, or create an initial active session.

Current creation authorization is blocked/fail-closed for every operation. The boundary does not accept passphrases, PINs, or biometrics; normalize or encode passphrases; generate salts, nonces, keys, container ids, record ids, or metadata ids; run Argon2id/KDF/HKDF/HMAC/AEAD; call provider operations; create headers, header commitments, containers, manifests, storage indexes, records, secure metadata, wrapped keys, storage namespaces, persistence commits, rollback handlers, failure cleanup, or active sessions; write secure storage, metadata storage, encrypted vault storage, Settings, files, or databases; add UI; enable vault creation, vault unlock, vault persistence, provider selectability, or mainnet.

Creation authorization remains unavailable because passphrase input is blocked, KDF calibration authorization is blocked, runtime randomness authorization is blocked, provider operations are unauthorized, secure-storage authorization is blocked, secure secret storage and secure metadata storage are unavailable, encrypted local vault storage is unavailable, storage service operations are disabled, storage safety is not approved for runtime use, migration/corruption handling and clear/wipe are model-only, lock/session lifecycle is unavailable, persistence readiness is blocked, unlock authorization is blocked, provider selection still selects only `DisabledVaultCryptoProvider`, and `productionProviderSelectable` remains false. OS keyrings remain rejected as primary storage and for Skald-managed passphrase storage, password managers remain rejected for Skald-managed passphrase storage, Settings/preferences storage remains rejected for secrets, creation state, and unlock state, Android creation and hardware-wrapper evidence remains future-reviewed only, Linux creation and optional-wrapper evidence remains future-reviewed only, mainnet creation remains blocked until release review, and passphrase-first remains the default authority.

## Authorization/Readiness Matrix

`SkaldVaultV1AuthorizationReadinessMatrixPolicy` is the still-disabled v1 matrix for auditing encrypted local vault readiness boundaries. It maps each future capability to current blocking boundaries, blocker categories, required future evidence, prohibited accidental readiness flags, override rules, mainnet status, and redacted diagnostic classes.

The matrix covers provider selectability, provider operations, runtime randomness, KDF calibration and final parameter approval, secure secret storage, secure metadata storage, vault creation, vault unlock, active sessions, vault persistence, encrypted local vault storage, manifest/storage-index/record read/write, atomic write, crash recovery, migration, corruption recovery, rollback protection, clear/wipe, redaction-safe diagnostics, passphrase input and retry/throttle, Android and Linux platform gates, BDK persistence, wallet sync, signing, broadcasting, Tor transport, Nostr parsing, and mainnet. It is evidence-only and does not enable any capability: every production/runtime capability remains blocked, warning-only evidence cannot authorize production use, user consent cannot override missing hard gates, test-only evidence cannot authorize production runtime, mainnet remains disabled, provider selection still selects only `DisabledVaultCryptoProvider`, and `productionProviderSelectable` remains false.

## Provider Candidate Packaging Boundary

`SkaldVaultV1ProviderCandidatePackagingPolicy` is the still-disabled v1 model for future crypto provider candidate packaging. It classifies future candidate families, dependency categories, source-set placement rules, provider operation surface status, KAT/randomness/KDF/AEAD/HKDF/HMAC/header-commitment/key-wrapping support status, platform support class, review requirements, blockers, disabled capabilities, and redacted policy tokens before any future provider can be implemented or promoted.

Current provider candidate packaging authorization is evidence-only and fail-closed. The boundary models Tink JVM, Bouncy Castle JVM, Android Keystore wrapper, platform OS CSPRNG, test-only deterministic, unknown, and unsupported candidates, but no candidate is implemented, runtime-instantiable, executable, selectable, or production-authorized. It does not add or activate dependencies, instantiate provider code, run provider operations, run KATs, run randomness checks, run Argon2id/KDF/HKDF/HMAC/AEAD, generate salts/nonces/keys, compute header commitments, wrap keys, create or unlock a vault, persist storage, add platform actuals, add UI, enable wallet behavior, or approve mainnet.

Provider candidate promotion remains unavailable because provider selection still selects only `DisabledVaultCryptoProvider`, `productionProviderSelectable` remains false, provider operation authorization is blocked, runtime randomness authorization is blocked, KDF calibration authorization is blocked, secure-storage authorization is blocked, creation and unlock authorization are blocked, the authorization/readiness matrix blocks promotion, provider KAT and runtime provider/randomness checks are not approved for production, and mainnet remains disabled until release review.

## Provider Dependency Build Boundary

`SkaldVaultV1ProviderDependencyBuildPolicy` is the still-disabled v1 model for the provider dependency build spike. It records whether a future provider dependency candidate is absent, declared-only, source-set scoped, test-only, blocked, forbidden, unknown, or build-only evidence before any future provider implementation can be considered.

Current dependency build evidence is still disabled. No new Gradle dependency is added in this branch: the existing platform-scoped Tink and Bouncy Castle declarations already provide declared/resolvable build evidence, while the current provider direction remains a split stack rather than one single newly activated dependency. Tink JVM and Bouncy Castle JVM are represented as source-set-declared build evidence only; Android platform wrapping, OS CSPRNG/platform wrapping, test-only deterministic providers, unknown candidates, and unsupported candidates remain absent, test-only, or fail-closed.

The boundary does not activate dependencies, import provider APIs in common production code, instantiate provider runtime, add provider implementation or factory classes, change the provider registry, run provider operations, run provider KATs, run randomness checks, call `SecureRandom`, run Argon2id/KDF/HKDF/HMAC/AEAD, generate salts/nonces/keys, wrap keys, create or unlock a vault, persist storage, add platform actuals, add UI, enable wallet behavior, or approve mainnet. Provider selection still selects only `DisabledVaultCryptoProvider`, `productionProviderSelectable` remains false, provider operation authorization remains blocked, the authorization/readiness matrix blocks promotion, and warning-only evidence, user consent, or test-only evidence cannot authorize production runtime use.

## Provider Selection Promotion Blockers

`SkaldVaultV1ProviderSelectionPromotionBlockersPolicy` is the still-disabled v1 model for future provider promotion traceability. It records what blocks a future provider candidate from moving from candidate description and dependency/build evidence into dependency review, source-set review, implementation, factory, registry, test selectability, production selectability, provider operation authorization, KAT approval, runtime randomness approval, KDF/AEAD/key-wrapping support, creation/unlock integration, persistence integration, release validation, or mainnet approval.

Current promotion evidence is model-only and fail-closed. Candidate description and existing build-only dependency evidence do not imply implementation, factory availability, registry enablement, selectability, production authorization, provider operation authorization, KAT readiness, randomness readiness, KDF/AEAD/HKDF/HMAC readiness, key-wrapping readiness, vault creation/unlock/persistence readiness, or mainnet readiness. The disabled provider remains the only selected provider, Tink JVM and Bouncy Castle JVM remain future-only/non-selectable candidates with build-only evidence, Android Keystore remains an optional future wrapper, platform OS CSPRNG remains a future randomness source rather than a provider, test-only deterministic evidence is rejected for production, and unknown/unsupported candidates fail closed.

The boundary does not add dependencies, implement a provider, add a factory, enable a registry, instantiate provider code, change provider selection, set `productionProviderSelectable=true`, run provider operations, run provider KATs, run randomness checks, call `SecureRandom`, run Argon2id/KDF/HKDF/HMAC/AEAD, generate salts/nonces/keys, wrap keys, create or unlock a vault, persist storage, add platform actuals, add UI, enable wallet behavior, or approve mainnet. Promotion remains blocked by provider selection, `productionProviderSelectable=false`, provider operation authorization, provider candidate packaging, provider dependency build evidence, the authorization/readiness matrix, provider acceptance, dependency probe sufficiency, KAT approval, runtime randomness, KDF calibration, secure-storage authorization, creation authorization, unlock authorization, redaction/leakage review, clear/wipe review, migration/corruption review, release review, and mainnet policy. Warning-only evidence, user consent, or test-only evidence cannot authorize production runtime use.

## Provider Interface Contract Audit

`SkaldVaultV1ProviderInterfaceContractAuditPolicy` is the still-disabled v1 audit for provider-facing contract safety. It reviews provider-neutral common interfaces, the disabled provider facade, provider selection registry, provider operation authorization, provider candidate packaging, dependency/build evidence, promotion blockers, provider acceptance, KAT and runtime-randomness gates, KDF calibration, secure storage, creation, unlock, the authorization/readiness matrix, redaction/leakage, clear/wipe, and migration/corruption boundaries.

Current provider interface audit evidence is model-only and fail-closed. The audit records that provider-facing model/evidence paths carry enums, statuses, blocker classes, policy ids, and redacted tokens only. Contract risks that would accept secrets, byte material, provider handles, crypto objects, platform crypto imports, direct operation execution, authorization bypasses, promotion bypasses, provider selectability, or `productionProviderSelectable=true` are rejected.

The audit does not add dependencies, implement a provider, add a factory, enable a registry, instantiate provider code, change provider selection, run provider operations, run provider KATs, run randomness checks, call `SecureRandom`, run Argon2id/KDF/HKDF/HMAC/AEAD, generate salts/nonces/keys, wrap keys, create or unlock a vault, persist storage, add platform actuals, add UI, enable wallet behavior, or approve mainnet. Future implementation remains behind provider-operation authorization, runtime randomness authorization, KDF authorization, secure-storage authorization, creation/unlock authorization, provider selection promotion blockers, and authorization/readiness matrix gates.

## Non-Selectable Provider Skeleton Boundary

`SkaldVaultV1NonSelectableProviderSkeletonPolicy` is the still-disabled v1 model for the future provider skeleton shape. It records the future provider skeleton name/family, source-set placement, dependency visibility, implementation/factory/registry/selectability status, operation-surface status, KAT/randomness/KDF/AEAD/HKDF/HMAC/header-commitment/key-wrapping/clear-dispose surface status, diagnostics/redaction, acceptance gates, promotion blockers, and readiness-matrix interaction.

Current non-selectable provider skeleton evidence is model-only and fail-closed. The skeleton is modeled, but it is not registered, not selectable, not instantiable by the provider registry, and not an implementation of the runtime provider interface. Every operation surface remains disabled: availability checks, provider self-tests, provider KATs, runtime randomness checks, randomness requests, salt generation, nonce generation, key generation, KDF/Argon2id, HKDF extract/expand, HMAC, header commitment compute/verify, AEAD encrypt/decrypt, record encrypt/decrypt, key wrap/unwrap, and provider clear/dispose.

The skeleton boundary does not add dependencies, implement executable provider behavior, implement `VaultCryptoProvider`, add a factory, enable a registry, instantiate provider code, change provider selection, set `productionProviderSelectable=true`, run provider operations, run provider KATs, run randomness checks, call `SecureRandom`, run Argon2id/KDF/HKDF/HMAC/AEAD, generate salts/nonces/keys, wrap keys, create or unlock a vault, persist storage, add platform actuals, add UI, enable wallet behavior, or approve mainnet. Future implementation remains blocked by the interface audit, provider selection promotion blockers, provider candidate packaging, provider dependency/build evidence, provider-operation authorization, runtime randomness authorization, KDF calibration authorization, secure-storage authorization, creation/unlock authorization, and authorization/readiness matrix gates.

`SkaldVaultV1ProviderRegistryIsolationGuardPolicy` is the still-disabled v1 guard for provider-selection registry isolation. It records the active provider-selection registry, disabled provider selection, non-selectable skeleton exclusion, candidate-packaging exclusion, dependency/build exclusion, interface-audit exclusion, promotion-blocker exclusion, authorization/readiness matrix exclusion, provider-operation authorization exclusion, runtime-randomness authorization exclusion, KDF/secure-storage/creation/unlock authorization exclusion, test-only evidence exclusion, warning-only evidence exclusion, user-consent exclusion, and release/mainnet future-review requirements.

Current registry isolation evidence is model-only and fail-closed. The current registry selects only `DisabledVaultCryptoProvider`; it contains no skeleton entries, no candidate entries, no provider factories, no provider constructors, no provider handles, no crypto objects, and no platform crypto imports. It cannot execute provider operations, run provider KATs, call randomness, call KDF/AEAD/HKDF/HMAC, promote dependency/build evidence, promote packaging/skeleton/audit/matrix evidence, be overridden by warning-only/user-consent/test-only evidence, set `productionProviderSelectable=true`, or approve mainnet.

The registry isolation guard does not add dependencies, implement executable provider behavior, implement `VaultCryptoProvider`, add a factory, enable a non-disabled registry, instantiate provider code, change provider selection, set `productionProviderSelectable=true`, run provider operations, run provider KATs, run randomness checks, call `SecureRandom`, run Argon2id/KDF/HKDF/HMAC/AEAD, generate salts/nonces/keys, wrap keys, create or unlock a vault, persist storage, add platform actuals, add UI, enable wallet behavior, or approve mainnet. Future registry promotion requires an explicit later branch and review.
