# Encrypted Local Vault Design

## Status

This document defines the app-controlled encrypted local vault design that Skald Vault must implement before any production secret persistence, sensitive metadata persistence, production wallet sync, production address index persistence, production UTXO persistence, or Nostr secret-bearing wallet flow can be enabled.

This is a design document only. It does not implement encryption, add crypto dependencies, persist secrets, persist sensitive metadata, create wallets, activate production sync, construct transactions, sign, broadcast, parse Nostr keys, add Tor transport, add public endpoints, add Skald-operated infrastructure, or enable mainnet.

Current runtime behavior remains fail-closed:

- `SecureSecretStorage` is disabled.
- `SecureWalletMetadataRepository` is disabled.
- Production sync is disabled.
- Production observation/address-index/UTXO persistence is disabled.

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
- crypto dependency addition,
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
Vault key-encryption key
        ↓
Encrypted vault root key
        ↓
Derived or wrapped record-class keys
        ├── metadata encryption key
        ├── secret payload encryption key
        ├── backup/export encryption key
        └── optional per-record subkeys
```

Optional platform wrapping may protect the vault root key or key-encryption key:

```text
Platform wrapping key
        ↓
Wrapped vault key material
```

Platform wrapping is optional defense-in-depth. It must not replace the app-controlled vault policy or session lock model.

## Key Derivation

Future implementation must use a reviewed password-based key derivation function. Candidate families include memory-hard KDFs such as Argon2id or scrypt. Platform constraints may require a reviewed fallback, but weaker KDF parameters must not be chosen silently.

Requirements:

- Store KDF algorithm and parameters in the vault header.
- Tune parameters separately for Android and Linux.
- Support KDF parameter upgrades.
- Treat wrong passphrase/PIN as a locked/unavailable state.
- Never log unlock material, derived keys, salts, or intermediate values.
- Never keep user passphrases in process-global state.
- Use platform CSPRNG for vault key material.
- Future production seed generation should detect and prefer hardware-backed/platform CSPRNG entropy where available, but entropy detection is not implemented in this pass.

## Record Encryption

Future record encryption must use authenticated encryption. Candidate AEAD families may include AES-GCM where hardware support and nonce discipline are acceptable, or XChaCha20-Poly1305 where dependency and platform support are reviewed.

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
3. Vault root key is unwrapped/decrypted into session memory.
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

Passphrase/PIN policy is not implemented yet.

Design requirements:

- passphrase/PIN unlock must protect vault root key material,
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
- crypto dependency choice is reviewed,
- KDF and AEAD parameters are reviewed,
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

- Which crypto dependency should provide KDF and AEAD primitives for Kotlin Multiplatform, Android, and Linux desktop?
- Should the vault use Argon2id, scrypt, or another reviewed KDF for passphrase/PIN unlock?
- Should Android require hardware-backed wrapping where available or make it an optional risk label?
- How should Linux vault files be located, permissioned, backed up, and migrated?
- Should OS keyring wrapping be offered on Linux, and how should already-unlocked-session risk be displayed?
- Should Skald support PIN-only unlock, passphrase-only unlock, or both?
- How should backup/export keys be generated, rotated, and recovered?
- Should BDK persistence ever be allowed directly, or should Skald translate all necessary state into Skald-owned encrypted records?
- How should all-wallet-traffic-through-Tor policy interact with background sync once sync exists?
- How should vault unlock state interact with future Lightning background services?
- How should Cashu proof updates be made atomic without plaintext exposure?
- How should Nostr identity-linked metadata be retained or intentionally forgotten after sweep/recovery flows?

## Implementation Sequence

Recommended next implementation sequence:

1. Review this design and resolve open dependency/KDF/AEAD questions.
2. Add code-level vault policy/readiness models without storage success paths.
3. Add tests proving secure storage and secure metadata remain disabled.
4. Select crypto dependencies through explicit review.
5. Implement a disabled vault container parser/validator without storing real secrets.
6. Implement encrypted vault storage behind a disabled feature gate.
7. Add lock/session lifecycle tests.
8. Add Android wrapping tests where applicable.
9. Add Linux file-permission and optional keyring-wrapping tests where applicable.
10. Add migration, corruption, and partial-write tests.
11. Update Recovery/Privacy/Sync status surfaces.
12. Enable only the smallest low-risk persistence path after explicit approval.

Do not enable production wallet creation, production sync, production address derivation, production UTXO persistence, signing, broadcasting, Nostr secret-bearing flows, public endpoint defaults, or mainnet as part of vault design work.
