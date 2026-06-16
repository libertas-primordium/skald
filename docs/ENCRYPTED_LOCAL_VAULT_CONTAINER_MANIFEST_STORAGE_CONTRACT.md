# Encrypted Local Vault Container, Manifest, And Storage Contract

## Status

This document defines the Skald Vault v1 vault container, manifest, storage, stale-record, atomicity, crash-recovery, and secure-storage boundary contract before persistence implementation.

The in-memory v1 container parser/writer now exists as a still-disabled byte-level building block:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ContainerFormat.kt
```

It accepts caller-supplied byte arrays, returns byte arrays or typed parse/validation failures, and has deterministic non-secret fixture tests. It does not read files, write files, persist bytes, access storage, call secure storage, derive keys, verify passphrases, decrypt records, call Argon2id, call HKDF, call HMAC, call Tink AEAD, create vaults, unlock vaults, or make a provider selectable.

The in-memory v1 manifest parser/writer and local manifest-relative stale-record decision policy now also exist as still-disabled byte-level building blocks:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ManifestFormat.kt
```

They accept caller-supplied byte arrays and in-memory descriptors only, return byte arrays or typed parse/validation/decision results, and have deterministic non-secret fixture tests. They do not read files, write files, persist manifest bytes, access storage, call secure storage, derive keys, verify passphrases, decrypt records, call Argon2id, call HKDF, call HMAC, call Tink AEAD, create vaults, unlock vaults, or make a provider selectable.

An in-memory storage atomicity/crash simulator now exists as a still-disabled shared test harness:

```text
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultStorageAtomicitySimulatorTest.kt
```

It operates only on caller-supplied non-secret container and manifest byte arrays plus in-memory temporary/committed/quarantine state. It injects interruptions at the documented write phases, validates bytes through the existing in-memory container and manifest parsers, applies the local manifest-relative stale-record policy, and returns typed recovery decisions. It does not read files, write files, call platform storage, call database APIs, call DataStore or SharedPreferences, implement temp files, implement journals, implement rename/fsync behavior, persist bytes, access secure storage, or prove platform durability.

A still-disabled storage namespace/path policy building block now exists:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1StorageNamespacePathPolicy.kt
```

It validates stable namespace/policy identifiers and deterministically encodes fixed non-secret vault and record ID bytes into relative safe path segments. It does not construct absolute paths, choose platform storage roots, create directories, read files, write files, access storage, persist bytes, or approve persistence.

A still-disabled logical storage layout plan building block now exists:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1StorageLayoutPlan.kt
```

It composes validated namespace/path policy constants plus encoded fixed non-secret vault and record segments into deterministic rootless relative segment lists for current container, current manifest, current storage index, record artifacts, temporary artifacts, quarantine, and recovery metadata. Its output is a platform-neutral logical plan, not an operating-system path. It does not select a platform root, join path strings, construct absolute paths, return `File`/`Path`/`Uri` objects, create directories, read files, write files, access storage, persist bytes, or approve persistence.

A still-disabled path-containment planner building block now exists:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1PathContainmentPlanner.kt
```

It combines a reviewed root token with rootless logical layout segment lists to produce typed planned artifact locations. A planned location carries the root token, storage layout policy id, artifact kind, safe relative segments, and a segment-level containment proof flag. It is not an operating-system path, does not resolve Android or desktop roots, does not join path strings, does not construct absolute paths, does not return `File`/`Path`/`Uri` objects, does not check real filesystem containment, does not check symlinks, does not inspect permissions, does not probe durability, does not read or write files, and does not approve persistence.

The platform root settings policy is now modeled as a documented-only contract:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1PlatformRootSettingsPolicy.kt
```

It records Android app-private internal storage as the only v1 Android root policy, rejects Android external/shared storage and Android arbitrary user-selected roots, records Linux default user-data root policy with a `~/.local/share/` fallback convention, records future Linux custom-root configuration through Settings as planned but unimplemented, rejects OS keyring and password-manager integration for Skald-managed vault passphrase storage, and keeps passphrase-first as the default vault authority. It does not read environment variables, read `HOME`, resolve roots, construct paths, create directories, add Settings UI, persist settings, integrate libsecret/KWallet/GNOME Keyring, integrate Android Credential Manager/Autofill/Google Password Manager, store passphrases, or approve persistence.

A still-disabled Linux custom-root validation policy building block now exists:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1LinuxCustomRootValidationPolicy.kt
```

It performs pure static validation of candidate Linux custom vault-root strings. It can accept a candidate only as policy evidence and returns typed rejection reasons for relative paths, tilde paths, root/temp/system/runtime/removable-media roots, traversal, empty segments, control or invisible characters, whitespace, unsupported characters, URI-like prefixes, Windows drive prefixes, user-label sources, secret-looking material, and excessive length. Accepted candidates remain untrusted strings: they are not resolved, not expanded, not checked for existence, not checked for containment, not checked for symlinks, not checked for permissions, not checked for durability, not persisted as settings, and not usable for storage. The policy does not read `XDG_DATA_HOME`, read `HOME`, expand `~`, construct paths, create directories, call filesystem APIs, add Settings UI, persist settings, or approve persistence.

A still-disabled Linux root-resolution evidence policy now exists:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1LinuxRootResolutionPolicy.kt
```

It consumes caller-supplied static default-root evidence or the existing Linux custom-root validation result and returns typed, redacted, still-disabled root-resolution evidence. Accepted evidence produces a root token and capability model only. It is not a platform path, does not read `HOME`, does not read `XDG_DATA_HOME`, does not read environment variables or system properties, does not resolve real paths, does not construct platform paths, does not call filesystem APIs, does not prove existence, containment, symlink safety, permissions, ownership, durability, or atomic-write safety, does not add Settings UI or settings persistence, does not add secure storage, does not make custom roots usable, does not enable vault persistence, and does not make a provider selectable.

A still-disabled platform root resolver boundary now exists:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1PlatformRootResolver.kt
```

It accepts injected, caller-supplied evidence snapshots for future Android app-private internal root evidence, Linux default user-data root evidence, and Linux custom-root validation evidence. It returns typed, redacted root evidence tokens and a disabled capability model only. Android evidence remains app-private internal only; Android external/shared and user-selected roots are rejected. Linux evidence delegates to the existing Linux root-resolution and custom-root validation policies. The resolver boundary does not create directories, read files, write files, persist Settings, persist vault root choices, construct artifact paths, return platform path objects, prove existence, prove containment, prove symlink safety, prove permissions, prove ownership, prove durability, prove atomic-write safety, enable vault persistence, or make a provider selectable.

A still-disabled platform path-construction boundary now exists:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1PlatformPathConstructionBoundary.kt
```

It consumes accepted platform root resolver evidence and the existing logical storage layout plan, then reuses the path-containment planner to return typed, redacted planned artifact-location evidence for current container, current manifest, current storage index, record, temporary, quarantine, and recovery metadata artifacts. Planned artifact locations are root-token-bound logical relative segment evidence only. They are not platform paths and are not usable for file I/O. The boundary does not accept raw platform paths as a persistence input, does not construct real or absolute paths, does not return `File`, `Path`, `Uri`, or platform filesystem objects, does not create directories, does not read files, does not write files, does not persist Settings, does not prove existence, does not prove real containment, does not prove symlink safety, does not prove permissions or ownership, does not prove durability or atomic-write safety, does not enable vault persistence, and does not make a provider selectable.

A still-disabled storage safety preflight boundary now exists:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1StorageSafetyPreflightBoundary.kt
```

It consumes planned artifact-location evidence and future caller-supplied safety evidence to produce typed, redacted preflight gate evidence only. The gate vocabulary covers root evidence, planned artifact-location evidence, real path construction, containment, symlink safety, permission, ownership, durability, atomic write, crash recovery, manifest read/write, storage-index read/write, record read/write, storage failure mapping, corruption handling, migration handling, redaction/logging safety, secure secret storage, secure metadata storage, anti-rollback limitation review, provider selectability review, and mainnet-disabled review. Current results remain fail-closed and still-disabled. The boundary does not run filesystem checks, construct real or absolute paths, use `File` or `Path`, create directories, read files, write files, persist Settings, prove existence, prove containment, prove symlink safety, prove permissions, prove ownership, prove durability, prove atomic-write safety, prove crash-recovery safety, enable manifest read/write, enable storage-index read/write, enable record read/write, enable vault persistence, or make a provider selectable.

A disabled vault storage service facade now exists:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1DisabledStorageServiceFacade.kt
```

It consumes planned artifact-location evidence and storage safety preflight evidence to expose the future Skald-owned storage service operation vocabulary as typed disabled results. Modeled operations include namespace initialization, current container read/write, current manifest read/write, storage-index read/write, record read/write/list/delete/quarantine, interrupted-write recovery, storage health check, storage failure mapping, atomic write preparation/commit/rollback, anti-rollback/freshness evidence verification, and close/lock. Every operation currently returns disabled or rejected evidence; no operation returns success. The facade does not return storage handles, file handles, paths, streams, database handles, repositories, record payloads, raw record identifiers, or byte arrays. It does not run filesystem checks, construct real or absolute paths, use `File` or `Path`, create directories, read files, write files, persist Settings, read or write manifests, read or write storage indexes, read or write records, list or delete actual records, quarantine actual records, recover actual records, perform atomic writes, perform crash recovery, map real storage failures, enable vault persistence, or make a provider selectable.

A still-disabled vault persistence readiness gate now exists:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1PersistenceReadinessGate.kt
```

It composes typed provider-selection evidence, production-provider acceptance evidence, dependency probe evidence, still-disabled provider facade evidence, platform root evidence, planned artifact-location evidence, storage safety preflight evidence, disabled storage service facade evidence, secure secret storage capability, and secure metadata storage capability into one typed, redacted readiness decision. The current decision is always blocked/fail-closed. It models the required production gates for provider selectability, provider KATs, final KDF calibration, runtime randomness/provider checks, secure secret storage, secure metadata storage, lock/session lifecycle, redaction/leakage review, migration/corruption handling, platform root evidence, path construction, containment, symlink safety, permissions/ownership, durability, storage safety preflight, storage service operations, manifest read/write, storage-index read/write, record read/write, atomic writes, crash recovery, anti-rollback limitation review, Settings/root persistence where custom roots are used, Android app-private storage policy, Linux custom-root review, provider operations without secret exposure, BDK persistence bypass review, managed-infrastructure rejection, and mainnet release hardening. It does not run filesystem checks, construct real platform paths, construct absolute paths, use `File` or `Path`, create directories, read files, write files, persist Settings, read or write manifests, read or write storage indexes, read or write records, list/delete/quarantine/recover actual records, perform atomic writes, perform crash recovery, map real storage failures, approve production provider use, approve mainnet, enable vault creation, enable vault unlock, enable vault persistence, or make a provider selectable.

A still-disabled vault lock/session lifecycle boundary now exists:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1LockSessionLifecycleBoundary.kt
```

It consumes typed persistence readiness, provider-selection, provider-acceptance, dependency, disabled provider facade, disabled storage facade, secure secret storage, secure metadata storage, and lifecycle-event evidence to model future lock/session decisions only. The state and event vocabulary covers locked, unlock requested, unlock blocked, unlock unavailable, active session unavailable, active session modeled but unusable, session expired, timeout, background, foreground, close/shutdown, error, provider changed, storage readiness changed, platform root/path evidence changed, platform security changed, mainnet request, and forced locked/fail-closed cases. Current results remain blocked/fail-closed and no result reports vault unlock readiness or a usable active session. The boundary does not accept passphrases or PINs, does not store passphrases, does not derive keys, does not hold decrypted keys, does not generate key material, does not implement memory wipe or zeroization, does not implement biometrics, does not implement Android Keystore, does not implement OS keyrings, does not implement password managers, does not add unlock UI, does not persist session state, does not run filesystem checks, does not construct real or absolute paths, does not use `File`/`Path`/filesystem APIs, does not create/read/write files, does not persist Settings, does not read/write manifests, storage indexes, or records, does not perform atomic writes or crash recovery, does not enable vault unlock, does not enable vault persistence, does not approve production provider use, does not approve mainnet, and does not make a provider selectable.

A still-disabled vault redaction/leakage boundary now exists:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1RedactionLeakageBoundary.kt
```

It consumes typed value-kind, output-target, scope, and redacted-token evidence only to model future safe-output decisions. The vocabulary covers forbidden secret classes, sensitive metadata classes, operational metadata, public non-wallet cryptographic vector evidence, public policy identifiers, sentinel placeholders, enum/status values, boolean capabilities, aggregate counts, source-guard material classes, and future output targets such as default `toString()`, UI status text, operation summaries, error summaries, build history, source-guard diagnostics, test assertions, structured logs, crash reports, support exports, backup/export manifests, and debug panels. Current decisions are fail-closed for unknown values and sensitive classes. The boundary does not accept raw secrets, passphrases, PINs, mnemonics, seeds, private keys, key material, decrypted records, encrypted record bytes, raw wallet databases, credentials, raw paths, endpoints with credentials, stack traces, byte arrays, payloads, wallet labels, transaction notes, BDK handles, storage handles, or backend handles. It does not hash or fingerprint secrets, does not produce stable secret identifiers, does not partially display first or last characters, does not log, does not add crash reporting, does not add analytics, does not add support export, does not add runtime diagnostics, does not persist diagnostic output, does not display secrets, does not implement unlock UI, does not implement provider execution, does not implement storage, does not enable vault unlock, does not enable vault persistence, does not enable provider selection, and does not approve mainnet. Public non-wallet cryptographic vectors remain allowed only in scoped docs/tests/KAT/source-guard contexts, and public-vector exceptions remain unavailable to wallet, UTXO, sync, or production source paths.

A still-disabled vault passphrase policy boundary now exists:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1PassphrasePolicyBoundary.kt
```

It consumes typed passphrase-policy requests and typed evidence from redaction/leakage, lock/session lifecycle, persistence readiness, provider selection, provider acceptance, disabled provider facade, dependency probe, secure storage, and secure metadata boundaries. Current decisions remain blocked or rejected. The boundary models future passphrase input policy, normalization and encoding policy identifiers, minimum/maximum policy review, control-character rejection, whitespace handling, NFC normalization, UTF-8 encoding, retry policy, throttle policy, lockout policy, memory lifetime, clear/wipe strategy, redaction requirements, absent UI entry policy, storage rejection, hash/fingerprint rejection, logging/build-history/crash/analytics rejection, biometric convenience unlock future scope, Android Keystore wrapping future scope, OS keyring/password-manager rejection, and unlock unavailability. It does not accept raw passphrases, PINs, biometrics, key material, passphrase bytes, hashes, fingerprints, raw platform paths, `File`, `Path`, `URI`, `URL`, streams, database handles, Settings values, backend URLs, labels, notes, descriptors, credentials, wallet database bytes, or BDK persistence handles. It does not store passphrases, normalize or encode real input, hash or fingerprint passphrases, run Argon2id, run KDF/HKDF/HMAC/AEAD, implement retry/throttle/lockout, add passphrase UI, add unlock UI, implement memory wipe/zeroization, implement Android Keystore, implement OS keyrings, implement password managers, enable vault unlock, enable vault persistence, enable provider selection, approve production provider use, or approve mainnet.

A still-disabled vault clear/wipe strategy boundary now exists:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ClearWipeStrategyBoundary.kt
```

It consumes typed clear/wipe policy requests and typed evidence from passphrase policy, redaction/leakage, lock/session lifecycle, persistence readiness, disabled provider facade, disabled storage facade, secure storage, secure metadata, and dependency/readiness boundaries. Current decisions remain blocked or model-only. The boundary models future sensitive value classes, lifecycle triggers, clear/wipe requirements, strategy classes, and limitations for passphrase/PIN buffers, mnemonic and seed material, private-key material, provider/vault/metadata/record/backup/wrapping keys, raw KDF input/output, raw AEAD keys, entropy buffers, decrypted records, staging buffers, provider/storage/session handles, retry/throttle state, redaction/diagnostic staging values, sensitive labels/notes, backend credentials, platform-wrapped key references, Android hardware-wrapped handles, and Linux optional key-wrapping handles. It records that JVM/Kotlin memory zeroization cannot be proven by this model. It does not accept raw sensitive values, passphrases, keys, byte arrays, char arrays, mutable buffers, raw paths, `File`, `Path`, `URI`, `URL`, streams, database handles, Settings values, provider handles, storage handles, backend URLs, labels, notes, descriptors, credentials, wallet database bytes, or BDK persistence handles. It does not clear real memory, zero real memory, prove JVM zeroization, use native memory, implement provider clear calls, implement storage clear calls, implement session invalidation, implement passphrase UI, implement unlock UI, implement Android Keystore, implement OS keyrings, implement password managers, enable vault unlock, enable vault persistence, enable provider selection, approve production provider use, or approve mainnet.

A still-disabled vault migration/corruption boundary now exists:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1MigrationCorruptionBoundary.kt
```

It consumes typed migration/corruption requests and typed evidence from the disabled storage service facade, storage safety preflight boundary, platform path-construction boundary, persistence readiness gate, redaction/leakage boundary, clear/wipe strategy boundary, lock/session lifecycle boundary, provider selection boundary, disabled provider facade, secure storage, and secure metadata boundaries. Current decisions remain blocked or model-only. The boundary models future container header, container version, provider suite id, KDF parameter, header commitment, AAD contract, manifest version, manifest current-record reference, storage-index version, storage-index record reference, record descriptor, record purpose, record nonce/counter, record ciphertext placeholder, record authentication failure, stale-record, rollback-suspicion, interrupted-write, partial-manifest-write, partial-storage-index-write, orphan-record, missing-record, duplicate-record, conflicting-record, unknown-future-version, unsupported-old-version, migration-required, migration-plan, corruption-detected, recovery-attempt, quarantine-required, manual-review-required, and fail-closed-required evidence. It maps those categories to failure classes and required actions including fail closed, reject operation, manual review, backup before migration, dry-run migration first, provider validation, storage safety validation, manifest validation, storage-index validation, record quarantine, stale-record review, rollback review, corruption review, clear/wipe handling, redacted diagnostics only, user-visible warning, release-hardening review, and unsupported/no action. It does not accept raw persisted container bytes, raw manifest bytes, raw storage-index bytes, raw record bytes, ciphertext, plaintext, AEAD tag bytes, nonce bytes, header-commitment bytes, KDF output, provider key material, passphrases, keys, raw paths, `File`, `Path`, `URI`, `URL`, streams, database handles, Settings values, backend URLs, labels, notes, descriptors, credentials, wallet database bytes, or BDK persistence handles. It does not parse real persisted storage, read files, write files, repair files, run migration, run migration dry-run, quarantine records, recover records, verify AEAD tags, decrypt records, verify real header commitments, rewrite manifests, rewrite storage indexes, prove rollback resistance, prove crash recovery, enable vault unlock, enable vault persistence, enable provider selection, approve production provider use, or approve mainnet.

This branch also records the v1 durability fail-closed decision and warning-only rejection policy for encrypted vault writes. Unsupported, unknown, unreviewed, insufficient, unsafe, or failed durability blocks encrypted vault persistence. Warning-only encrypted vault persistence is not approved for v1, and user consent cannot override a required durability failure.

The platform storage-root, platform root settings, Linux custom-root validation, Linux root-resolution evidence, platform root resolver boundary, platform path-construction boundary, storage safety preflight boundary, disabled storage service facade, persistence readiness gate, lock/session lifecycle boundary, redaction/leakage boundary, passphrase policy boundary, clear/wipe strategy boundary, migration/corruption boundary, safe path-construction, symlink/traversal, permission/ownership, durability-capability, durability fail-closed, and warning-only rejection contracts are represented in `ProductionProviderAcceptanceContract`, `EncryptedVaultReadiness`, and `VaultCryptoDependencyProbe` only. They do not resolve Android or desktop storage roots, join real paths, check symlinks, inspect permissions, probe durability, create directories, read files, write files, list/delete/quarantine/recover records, parse real persisted storage, migrate storage, repair storage, verify real AEAD tags or header commitments, accept passphrases, hold keys, clear memory, zero memory, prove JVM zeroization, hash or fingerprint secrets or passphrases, run KDFs, log, add crash reporting, add analytics, add support export, add passphrase UI, add unlock UI, persist diagnostic output, persist session state, add Settings UI, persist settings, integrate OS keyrings, integrate password managers, approve provider use, approve mainnet, approve unlock, or approve persistence.

This remains contract and still-disabled building-block evidence only. It does not implement provider selectability, vault creation, vault unlock, vault persistence, manifest file/storage read/write, storage index read/write, record read/write/list/delete/quarantine/recovery, filesystem storage, database storage, DataStore or SharedPreferences storage, secure secret storage success, secure metadata storage success, migration, re-encryption, sync, import/export, wallet behavior, backend behavior, signing, broadcasting, Tor, Nostr, or mainnet.

Related evidence is modeled in:

- `ProductionProviderAcceptanceContract`
- `EncryptedVaultReadiness`
- `VaultCryptoDependencyProbe`

Required policy ids:

- Vault container policy id: `skald-vault-v1-container-contract-v1`
- Manifest policy id: `skald-vault-v1-manifest-contract-v1`
- Storage policy id: `skald-vault-v1-local-manifest-storage-policy-v1`
- Platform storage boundary policy id: `skald-vault-v1-platform-storage-boundary-policy-v1`
- Stale-record policy id: `skald-vault-v1-stale-record-manifest-policy-v1`
- Atomicity/crash-recovery policy id: `skald-vault-v1-atomicity-crash-recovery-policy-v1`
- Atomic write strategy policy id: `skald-vault-v1-atomic-write-strategy-policy-v1`
- Crash-recovery policy id: `skald-vault-v1-crash-recovery-policy-v1`
- Storage interruption-test policy id: `skald-vault-v1-storage-interruption-test-policy-v1`
- Storage failure model policy id: `skald-vault-v1-storage-failure-model-policy-v1`
- Storage namespace/path policy id: `skald-vault-v1-storage-namespace-path-policy-v1`
- Storage layout plan policy id: `skald-vault-v1-storage-layout-plan-v1`
- Path-containment planner policy id: `skald-vault-v1-path-containment-planner-v1`
- Platform storage root policy id: `skald-vault-v1-platform-storage-root-policy-v1`
- Platform root settings policy id: `skald-vault-v1-platform-root-settings-policy-v1`
- Android root policy id: `skald-vault-v1-android-app-private-internal-root-policy-v1`
- Linux root settings policy id: `skald-vault-v1-linux-root-settings-policy-v1`
- Linux custom-root validation policy id: `skald-vault-v1-linux-custom-root-validation-policy-v1`
- Linux root-resolution policy id: `skald-vault-v1-linux-root-resolution-policy-v1`
- Platform root resolver boundary policy id: `skald-vault-v1-platform-root-resolver-boundary-v1`
- Platform path-construction boundary policy id: `skald-vault-v1-platform-path-construction-boundary-v1`
- Storage safety preflight boundary policy id: `skald-vault-v1-storage-safety-preflight-boundary-v1`
- Disabled storage service facade policy id: `skald-vault-v1-disabled-storage-service-facade-v1`
- Vault persistence readiness gate policy id: `skald-vault-v1-persistence-readiness-gate-v1`
- Clear/wipe strategy boundary policy id: `skald-vault-v1-clear-wipe-strategy-boundary-v1`
- Migration/corruption boundary policy id: `skald-vault-v1-migration-corruption-boundary-v1`
- Vault lock/session lifecycle boundary policy id: `skald-vault-v1-lock-session-lifecycle-boundary-v1`
- Vault redaction/leakage boundary policy id: `skald-vault-v1-redaction-leakage-boundary-v1`
- Vault passphrase policy boundary policy id: `skald-vault-v1-passphrase-policy-boundary-v1`
- OS keyring passphrase policy id: `skald-vault-v1-os-keyring-passphrase-policy-v1`
- Password-manager passphrase policy id: `skald-vault-v1-password-manager-passphrase-policy-v1`
- Passphrase-first vault authority policy id: `skald-vault-v1-passphrase-first-vault-authority-policy-v1`
- Safe path-construction policy id: `skald-vault-v1-safe-path-construction-policy-v1`
- Symlink/traversal policy id: `skald-vault-v1-symlink-traversal-policy-v1`
- Storage permission/ownership policy id: `skald-vault-v1-storage-permission-ownership-policy-v1`
- Durability capability policy id: `skald-vault-v1-durability-capability-policy-v1`
- Durability fail-closed policy id: `skald-vault-v1-durability-fail-closed-policy-v1`
- Warning-only durability rejection policy id: `skald-vault-v1-warning-only-durability-rejection-policy-v1`
- In-memory storage atomicity simulator policy id: `skald-vault-v1-in-memory-storage-atomicity-simulator-policy-v1`
- Secure-storage boundary policy id: `skald-vault-v1-secure-storage-boundary-policy-v1`
- Anti-rollback anchor policy id: `skald-vault-v1-anti-rollback-anchor-policy-v1`

## Platform Storage Boundary Contract

The future Skald Vault storage abstraction is a boundary around byte arrays, not a boundary around secrets.

Future storage may eventually handle only:

- encrypted container bytes;
- manifest bytes;
- storage index metadata;
- crash-recovery temporary state;
- non-secret storage metadata.

Future storage must never handle:

- passphrases;
- normalized passphrase bytes;
- Argon2id root material;
- HKDF subkeys;
- plaintext record bodies;
- Tink keysets;
- wallet seed material;
- private keys;
- Nostr secrets;
- Cashu proofs;
- backend credentials.

Storage boundary requirements:

- The storage layer accepts only already-encrypted or non-secret bytes.
- The storage layer is not responsible for encryption.
- The storage layer is not responsible for passphrase handling.
- The storage layer must not log stored bytes.
- The storage layer must not log file paths containing secret-identifying material.
- The storage layer must return typed failures for expected I/O and recovery conditions.
- The storage layer must fail closed on unknown state.

This branch does not implement the storage layer, platform storage roots, path construction, file reads, file writes, database reads, database writes, DataStore, SharedPreferences, manifest file read/write, storage index read/write, secure storage success paths, or persistence.

## Platform Storage Root Contract

Future platform storage-root resolution must be reviewed before any persistence branch may use it.

Android v1 future root policy:

- Vault storage must use app-private internal storage.
- External or shared storage is not approved for v1 vault persistence.
- User-selected arbitrary paths are not approved for v1 vault persistence.
- Root resolution must be platform-owned and not controlled by user-supplied strings.
- Backup and restore behavior must be documented before persistence approval.
- OS uninstall behavior and user data deletion implications must be documented before persistence approval.
- This branch does not implement Android root resolution, Android `Context` file APIs, path construction, directory creation, or storage.

Linux desktop v1 future root policy:

- Vault storage defaults under the user's data directory, conventionally under `~/.local/share/` when no explicit user-data override is configured.
- If Skald later models XDG-style user-data resolution, it may use an `XDG_DATA_HOME`-style location with fallback to `~/.local/share/`, but this branch does not read environment variables or `HOME`.
- Linux users may later configure a custom vault storage directory through Skald Settings.
- A still-disabled static validation policy exists for future Linux custom-root candidate strings, but accepted candidates do not prove existence, containment, symlink safety, permissions, durability, or persistence readiness.
- A future custom root must still be configured through Settings, persisted through reviewed settings storage, resolved by platform-specific code, and must pass containment, symlink, permission, and durability review before persistence.
- A future custom root must not weaken encryption, storage atomicity, durability fail-closed behavior, or passphrase-first policy.
- OS keyrings such as libsecret, KWallet, and GNOME Keyring must not be treated as primary encrypted vault storage.
- OS keyrings must not be used or encouraged for Skald-managed vault passphrase storage.
- Password-manager integrations such as Android Credential Manager, Android Autofill, and Google Password Manager are not integrated for vault passphrase storage.
- User-managed external password storage is outside Skald. Skald does not encourage, enable, depend on, or document it as a recommended workflow.
- Root resolution must avoid embedding user labels, wallet labels, note text, or secrets in paths.
- The platform-specific root choice must be reviewed for permissions, backup expectations, and filesystem durability semantics.
- This branch does not implement Linux root resolution, Settings UI, settings persistence, actual path construction, directory creation, OS keyring integration, password-manager integration, or storage.

Shared root policy:

- No secret values in root paths or child segments.
- No raw user-controlled strings in root paths or child segments.
- No raw vault id text or record id text without safe encoding.
- No wallet, account, or note labels in path names.
- No absolute user-supplied path input.
- No path traversal.
- No symlink-following assumptions before review.

This branch does not implement root resolution, platform root selection, Android `Context` file APIs, desktop filesystem root lookup, environment-variable lookup, `HOME` lookup, Settings UI, settings persistence, directory creation, or storage.

## Platform Root Settings Contract

The v1 platform root settings policy id is:

```text
skald-vault-v1-platform-root-settings-policy-v1
```

Android root decisions:

- `skald-vault-v1-android-app-private-internal-root-policy-v1` requires app-private internal storage for v1.
- Android external/shared storage is rejected for v1.
- Android arbitrary user-selected roots are rejected for v1.
- Android root resolution remains unimplemented.
- Android backup/restore and uninstall/data-deletion implications remain blocking review items.

Linux root/settings decisions:

- `skald-vault-v1-linux-root-settings-policy-v1` records the future default as an app-controlled user-data location, conventionally under `~/.local/share/`.
- XDG-style user-data resolution is future-only and must not read environment variables in this branch.
- Linux custom vault roots are planned only as a future Settings-configurable option.
- `skald-vault-v1-linux-custom-root-validation-policy-v1` validates static custom-root candidate strings only.
- Accepted custom-root candidates are not resolved paths and do not prove path existence, filesystem safety, containment, symlink state, permissions, durability, Settings readiness, storage readiness, or persistence readiness.
- `skald-vault-v1-linux-root-resolution-policy-v1` consumes caller-supplied static evidence only. It may turn accepted default-root evidence or accepted custom-root validation evidence into a redacted, still-disabled root token, but that token is not a filesystem path and is not usable for file I/O.
- Linux root-resolution evidence does not read `HOME`, read `XDG_DATA_HOME`, read environment variables, read system properties, expand `~`, resolve real roots, construct platform paths, call filesystem APIs, prove existence, prove containment, prove symlink safety, prove permissions, prove ownership, prove durability, prove atomic-write safety, persist settings, enable storage, enable persistence, or make a provider selectable.
- `skald-vault-v1-platform-root-resolver-boundary-v1` records a Skald-owned resolver interface and typed platform evidence models only. It accepts injected Android app-private evidence, Linux default-root evidence snapshots, or Linux custom-root validation evidence, but it does not perform Android `Context` root lookup, Linux environment or home lookup, directory creation, file I/O, Settings persistence, path construction, storage, containment checks, symlink checks, permission or ownership checks, durability probes, or persistence approval.
- Custom roots are not usable until Settings UI, settings persistence, root resolution, containment review, symlink review, permission review, durability review, and storage implementation exist.
- Custom roots must not contain secrets, wallet labels, account labels, note text, or unsafe user-controlled path content.

OS keyring, password-manager, and passphrase decisions:

- `skald-vault-v1-os-keyring-passphrase-policy-v1` rejects OS keyring storage for Skald-managed vault passphrases.
- OS keyrings are not primary encrypted vault storage.
- `skald-vault-v1-password-manager-passphrase-policy-v1` rejects Skald-managed password-manager integration for vault passphrases.
- `skald-vault-v1-passphrase-first-vault-authority-policy-v1` keeps passphrase-first as the default and primary vault authority.
- User-managed external password storage is outside Skald and is not encouraged as a product workflow.
- Optional Android biometric or hardware wrapping remains future convenience only and does not replace passphrase recovery.

The policy is represented as typed model evidence only. Missing, unknown, failed, unreviewed, documented-only, or unimplemented root/settings evidence blocks persistence and provider selectability. The policy does not implement platform roots, actual paths, Settings UI, settings persistence, file/database storage, OS keyring integration, password-manager integration, secure storage success, or Android wrapping.

## Linux Custom Root Static Validation Policy

The Linux custom-root validation policy id is:

```text
skald-vault-v1-linux-custom-root-validation-policy-v1
```

The policy accepts only candidate strings that pass conservative static checks. Accepted means only that the untrusted string is policy-valid for later review. It does not mean the path exists, it does not mean the path is safe on the current filesystem, it does not mean the candidate is contained under a reviewed root, it does not mean permissions or symlinks are safe, it does not mean durability is sufficient, and it does not enable persistence.

The policy rejects:

- empty strings;
- relative paths;
- tilde paths such as `~/vaults`;
- `/`, `/tmp`, `/var/tmp`, `/dev`, `/proc`, `/sys`, `/run`, `/mnt`, and `/media` roots or descendants;
- traversal such as `..` or percent-encoded traversal markers;
- empty path segments;
- control characters, whitespace, whitespace-only segments, invisible format characters, non-ASCII characters, and unsupported punctuation;
- URI-like prefixes such as `file:`;
- Windows drive prefixes;
- user-label or display-text sources;
- secret-looking material;
- too-long candidates.

This policy never reads `XDG_DATA_HOME`, never reads `HOME`, never expands `~`, never resolves roots, never constructs an operating-system path, never checks existence, never checks permissions, never checks symlinks, never probes durability, never creates directories, never persists settings, never integrates OS keyrings or password managers, and never enables storage.

## Safe Path-Construction Contract

Future path construction may only combine:

- a reviewed platform root;
- a validated storage namespace segment;
- an encoded vault segment from `SkaldVaultV1StorageNamespacePathPolicy`;
- an encoded manifest segment from `SkaldVaultV1StorageNamespacePathPolicy`;
- encoded record segments from `SkaldVaultV1StorageNamespacePathPolicy`;
- stable internal filenames or segment constants that pass safe-segment validation.

Future path construction must:

- join only validated relative segments;
- reject absolute segments;
- reject `.`, `..`, and empty segments;
- reject `/` or `\` inside segments;
- reject non-ASCII, invisible, unsupported, or too-long segments;
- reject segments derived from user labels or note text;
- reject secret-looking inputs;
- normalize and check final path containment under the reviewed root in a future implementation;
- fail closed if containment cannot be proven.

The still-disabled logical storage layout plan may prepare rootless relative segment lists for future path construction, and the still-disabled path-containment planner may bind those lists to reviewed root tokens at the model level. Those planned locations are not paths and cannot be used without later root resolution, path joining, and a real containment check under a reviewed platform root.

This branch does not implement path joining, containment checks against real filesystem paths, absolute path construction, or directory creation.

## Symlink And Filesystem Traversal Contract

Future implementation must:

- not rely on symlink behavior without platform review;
- reject or avoid symlink traversal where possible;
- verify that any resolved target remains under the app-controlled root where APIs support this;
- fail closed when symlink or containment state is unknown;
- not follow attacker-controlled symlinks for vault files;
- not accept external hard-linked or alias paths without review;
- document platform-specific symlink and traversal behavior before persistence approval.

This branch does not implement symlink checks, link resolution, alias checks, or containment enforcement.

## Permissions And Ownership Contract

Future implementation must:

- prefer app-private storage with OS-enforced per-user or per-app isolation;
- reject roots with obviously unsafe permissions when detectable;
- document desktop Linux permission expectations;
- document Android app-private behavior;
- fail closed if root permission or ownership state is unknown or unsafe where meaningful checks exist;
- never store vault data in world-readable or shared directories;
- not rely on OS keyrings as primary vault encryption or primary vault storage.

This branch does not implement permission checks, ownership checks, or platform storage selection.

## Durability Capability And Fail-Closed Contract

Future implementation must document and test:

- whether atomic replace is supported;
- whether durable sync or equivalent is supported;
- whether parent directory sync or equivalent is supported;
- whether platform APIs can guarantee expected write ordering;
- what reviewed equivalent safe strategy exists if durability primitives are unsupported;
- how Android and desktop behavior differ.

For Skald Vault v1 encrypted vault persistence, the following conditions fail closed and block persistence:

- `DurabilityCapabilityUnknown`
- `DurabilityCapabilityInsufficient`
- `DurabilitySyncUnsupported`
- `DurabilitySyncFailed`
- `AtomicReplaceUnsupported`
- `AtomicReplaceFailed`
- `PlatformRootUnreviewed`
- `PlatformRootUnsafe`
- `PermissionStateUnknown`
- `UnsafePermissions`
- `UnknownStorageState`

Unsupported durable sync or atomic replace can be accepted only if a future platform storage implementation explicitly selects an equivalent safe strategy and that strategy receives human review. No such strategy is approved here.

Warning-only encrypted vault persistence is not approved for v1. The app must not allow users to continue with encrypted vault writes when required durability capability is missing, unknown, unsupported, insufficient, unsafe, unreviewed, or failed. User consent cannot override required durability failure. Future warning-only behavior may be considered only for non-secret diagnostics or explicitly reviewed non-critical artifacts, not encrypted vault writes.

Android app-private storage durability and desktop filesystem durability both still require implementation proof and review before persistence. Unsupported or unknown platform durability blocks persistence.

This branch does not implement durability probes, fsync/sync calls, temp files, journals, rename, atomic replace, or platform crash-recovery behavior.

## Vault Container Contract

The future persisted Skald Vault v1 container is a logical structure. The still-disabled in-memory parser/writer implements the byte-level container model for caller-supplied byte arrays only; persistence, storage locations, atomic write behavior, recovery behavior, and manifest storage remain future work. The v1 container carries or references enough non-secret pre-unlock information for deterministic header reconstruction and fail-closed validation.

Required logical fields:

- vault magic/domain marker;
- vault format version;
- canonical vault header bytes, or fields sufficient to reconstruct the canonical bytes exactly;
- HMAC-SHA-256 header commitment tag;
- provider suite id;
- KDF algorithm, version, and parameters;
- salt length and salt bytes;
- derived root material length;
- vault id;
- passphrase policy id;
- key-expansion policy id;
- key-separation policy id;
- header commitment primitive policy id;
- header commitment policy id;
- record format policy id/version;
- AAD policy id/version;
- manifest policy id/version;
- storage policy id/version;
- feature flags;
- encrypted records section or record references;
- manifest section or manifest reference;
- integrity-critical metadata needed before unlock.

Container requirements:

- Plaintext secrets must never appear in the container.
- Root material, subkeys, passphrases, normalized passphrase bytes, plaintext records, Tink keysets, and random Tink vault keys must never appear in the container.
- Header commitment verification must happen before record AEAD use.
- Record AEAD must use strict AAD.
- Record ciphertext may be randomized; future tests must not rely on deterministic ciphertext for Tink XChaCha20-Poly1305.
- The in-memory parser rejects malformed, duplicated, unknown-in-non-extensible-section, unsupported, or non-canonical header evidence.
- The in-memory parser returns typed failures, not uncontrolled exceptions, for expected malformed or untrusted input.
- The in-memory writer validates unsupported or malformed model values before returning bytes.
- The in-memory parser/writer is a byte-array building block only. It is not a file format approval, filesystem path, database path, storage API, vault creation path, vault unlock path, or persistence API.

## In-Memory Parser/Writer Fixture

`SkaldVaultV1ContainerFormat.vectorFixtureContainer()` defines the fixed non-secret v1 format fixture. It uses:

- the canonical non-secret header fixture salt and vault id;
- a fixed non-secret HMAC header commitment tag value;
- one fixed non-secret record id, record type, version/counter, record reference, and record metadata value;
- one fixed non-secret ciphertext placeholder byte string;
- one fixed non-secret in-memory manifest section with vault id, provider suite id, header commitment context, storage namespace, record namespace, sequence, crash-recovery metadata, and latest trusted record state.

The ciphertext placeholder is a container-format fixture only. It is not a Tink AEAD correctness vector, is not randomized ciphertext generated by the record AEAD building block, and must not be treated as proof of record decrypt behavior.

The fixture serializes deterministically and the byte-exact expected hex is asserted in:

```text
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultContainerParserWriterTest.kt
```

The parser/writer tests assert:

- fixture serialization is deterministic;
- serialized fixture bytes match the expected hex;
- `parse(serialize(fixture))` returns the logical fixture;
- malformed magic, versions, suite ids, policy ids, KDF parameters, lengths, truncation, trailing bytes, unknown fields, duplicate fields, record entries, and manifest sections fail closed with typed reasons;
- parser/writer evidence does not enable persistence or provider selectability.

## Manifest Contract

The manifest is the authority for latest trusted local record state. AAD binds record version/counter into each record encryption operation, but the manifest is what decides whether a record is fresh relative to the latest trusted local state.

The still-disabled in-memory manifest parser/writer implements the byte-level manifest model for caller-supplied byte arrays only; manifest files, storage locations, storage indexes, atomic write behavior, recovery behavior, and persistence remain future work.

Required manifest fields:

- manifest magic/domain marker;
- manifest policy id/version;
- vault id;
- provider suite id;
- header commitment context;
- storage namespace;
- record namespace;
- latest trusted record version/counter per record id;
- record type per record id;
- record location/reference if future storage layout needs it;
- tombstone/deletion state if supported;
- manifest sequence/version;
- integrity-critical crash-recovery metadata.

The future stored manifest must be integrity-protected and bound to:

- vault id;
- provider suite id;
- header commitment context;
- manifest policy id/version;
- storage namespace;
- record namespace;
- latest record counters.

The in-memory manifest model includes fixed non-secret record references and tombstone/deletion state for format and stale-policy testing. This branch does not implement manifest file/storage read/write, a manifest file location, a storage index, sync/import conflict handling, or persistence.

## In-Memory Manifest Parser/Writer Fixture

`SkaldVaultV1ManifestFormat.vectorFixtureManifest()` defines the fixed non-secret v1 manifest fixture. It uses:

- fixed non-secret vault id bytes `20..2f`;
- fixed non-secret header commitment context bytes matching the header-commitment fixture tag;
- storage namespace `skald-vault/v1/local-records`;
- record namespace `skald-vault/v1/records`;
- manifest sequence `4`;
- fixed non-secret crash-recovery metadata bytes;
- two fixed non-secret record ids;
- one active `sensitive-metadata` record with latest counter `7`;
- one tombstoned `secret-payload` record with latest counter `3`;
- fixed non-secret in-memory record reference strings.

The fixture serializes deterministically and the byte-exact expected hex is asserted in:

```text
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultManifestParserStalePolicyTest.kt
```

The manifest parser/writer tests assert:

- fixture serialization is deterministic;
- serialized fixture bytes match the expected hex;
- `parse(serialize(fixture))` returns the logical fixture;
- record entries are serialized in deterministic record-id order;
- malformed magic, unsupported manifest policy/version, unsupported suite id, malformed storage namespace, malformed record namespace, malformed length prefixes, truncation, trailing bytes, unknown fields, duplicate fields, duplicate record ids, conflicting counters, malformed record references, and malformed tombstone states fail closed with typed reasons;
- serializer rejects unsupported or malformed model values before returning bytes;
- parser/writer evidence does not enable persistence or provider selectability.

## Stale-Record And Rollback Boundary

Strict AAD binding prevents undetected cross-vault, cross-provider, cross-record, cross-type, and cross-version ciphertext substitution. It does not by itself prove freshness.

The still-disabled local stale-record decision policy in `SkaldVaultV1ManifestFormat.decideRecordState(...)` implements in-memory, local manifest-relative decisions only:

- a candidate whose record id, type, tombstone state, and version/counter equal the latest trusted manifest state is `CurrentTrusted`;
- a candidate with a higher version/counter is `NewerPendingManifestUpdate`, not trusted current;
- a candidate with a lower version/counter is `StaleRejected`;
- a candidate whose record type conflicts with manifest state is `RecordTypeConflictRejected`;
- a candidate whose tombstone/deletion state conflicts with manifest state is `TombstoneConflictRejected`;
- an unknown record id is `UnknownRecordPendingManifestUpdate`, not trusted current;
- malformed candidate descriptors are rejected.

Future manifest/storage behavior must:

- reject or quarantine records whose version/counter is lower than the latest trusted local manifest state for the record id;
- reject or quarantine duplicate record ids with conflicting latest counters;
- define conflict handling before sync or import behavior is allowed.

Local manifest tracking can detect stale records relative to the latest trusted local manifest state. Local manifest tracking alone cannot protect against full rollback of the entire local vault directory to an older internally consistent state.

Full rollback resistance against complete local directory rollback requires an external anchor, trusted monotonic counter, append-only log, remote checkpoint, hardware-backed monotonic state, or another anti-rollback mechanism. No such anchor is implemented or claimed for v1 local-only storage in this contract.

## Atomicity And Crash Recovery

Future persistence must define atomicity at the vault-container/manifest consistency boundary before storage can be approved.

Required future atomic write phases:

- write new record/container bytes to a temporary location;
- write new manifest/index bytes to a temporary location;
- validate written bytes before commit;
- durably commit record/container bytes and manifest/index bytes in a safe order;
- use atomic rename or equivalent platform-specific replacement semantics where available;
- sync parent directory or use an equivalent durability primitive where supported;
- retain previous known-good state until the new state is fully committed;
- remove or quarantine incomplete temporary state after recovery.

Required future atomicity behavior:

- Writes must be atomic at the vault-container/manifest consistency boundary.
- Partial writes must not be treated as valid vault state.
- Manifest update and record write must either both become durable, or recovery must choose a safe previous state.
- Crash during write must not silently accept a newer record without manifest authority.
- Crash during manifest update must not silently orphan or resurrect records.
- Startup recovery must validate manifest/header/record consistency before unlock success.
- Future implementation must define a temp-file, journal, rename, fsync, or equivalent platform strategy before persistence is approved.
- Future implementation must test interruption at each write phase.

The implementation strategy must distinguish:

- desktop filesystem behavior;
- Android app-private filesystem behavior;
- future database-backed behavior if a database is ever chosen;
- unsupported platform behavior that must fail closed.

The still-disabled in-memory simulator tests these phases as a state-machine contract over byte arrays only. That simulator evidence is useful for future implementation review, but it is not a platform storage implementation and does not prove filesystem, database, rename, fsync, Android app-private storage, or crash-durability behavior.

This branch does not implement an atomic write strategy, journal, recovery routine, temp-file workflow, rename/fsync sequence, platform interruption hooks, or durable storage tests.

## Crash-Recovery Contract

Future startup recovery must:

- inspect stable committed state;
- inspect temporary or in-progress state;
- validate container parser output;
- validate manifest parser output;
- validate manifest references against available record/container data;
- apply stale-record policy against manifest state;
- choose a safe previous state when a newer state is incomplete;
- quarantine inconsistent state when it cannot be safely accepted;
- require an explicit user-facing recovery model for unrecoverable corruption;
- avoid uncontrolled exceptions for expected corruption or interruption cases.

Recovery must fail closed for:

- missing manifest when manifest is required;
- manifest references missing record/container data;
- record/container data without manifest authority;
- malformed manifest;
- malformed container;
- manifest/container vault id mismatch;
- provider suite mismatch;
- header commitment context mismatch;
- storage namespace mismatch;
- duplicate latest records;
- conflicting counters;
- truncated temporary state;
- unknown recovery state.

The in-memory simulator returns typed recovery decisions for expected malformed, missing, mismatched, stale, conflicting, and unknown states. It does not implement startup recovery against platform storage.

This branch does not implement production recovery.

## Interruption-Test Contract

Future persistence approval requires interruption tests at these points:

- before temporary container write;
- during temporary container write;
- after temporary container write before validation;
- after temporary container validation before temporary manifest write;
- during temporary manifest write;
- after temporary manifest write before commit;
- after committing container but before committing manifest;
- after committing manifest but before cleanup;
- during cleanup of old or temporary state;
- during startup recovery.

Future tests must prove:

- no partial write is treated as valid;
- previous known-good state remains usable or inconsistent state is quarantined;
- newer records are not accepted without manifest authority;
- manifest does not point to missing records;
- stale records are rejected or quarantined according to policy;
- typed recovery decisions are returned.

The in-memory simulator executes these interruption points against fixed non-secret byte-array fixtures. These are contract/state-machine tests only; they are not runtime platform interruption hooks and do not approve persistence.

## Storage Failure Model

Future storage must return typed failures for expected storage and recovery conditions. The modeled categories are:

- `StorageUnavailable`
- `PermissionDenied`
- `ReadFailed`
- `WriteFailed`
- `DurabilitySyncUnsupported`
- `DurabilitySyncFailed`
- `AtomicReplaceUnsupported`
- `AtomicReplaceFailed`
- `TempStateIncomplete`
- `ManifestMissing`
- `ManifestMalformed`
- `ContainerMalformed`
- `ManifestContainerMismatch`
- `RecordMissing`
- `RecordMalformed`
- `StaleRecordDetected`
- `DuplicateRecordConflict`
- `ConflictingCounter`
- `RecoveryQuarantineRequired`
- `RecoveryUserActionRequired`
- `UnknownStorageState`
- `PlatformRootUnavailable`
- `PlatformRootUnsafe`
- `PlatformRootUnreviewed`
- `PathConstructionUnsupported`
- `PathContainmentFailed`
- `PathSegmentRejected`
- `SymlinkStateUnknown`
- `SymlinkRejected`
- `PermissionStateUnknown`
- `UnsafePermissions`
- `DurabilityCapabilityUnknown`
- `DurabilityCapabilityInsufficient`
- `WarningOnlyDurabilityRejected`
- `UserConsentDurabilityOverrideRejected`
- `EquivalentSafeStrategyUnreviewed`
- `ExternalStorageRejected`
- `UserPathRejected`

This branch models the categories only. It does not map real platform exceptions or I/O return values.

## Storage Namespace And Path Hygiene

The storage namespace/path policy id is:

```text
skald-vault-v1-storage-namespace-path-policy-v1
```

The still-disabled policy validates these stable internal identifiers:

- storage policy id: `skald-vault-v1-local-manifest-storage-policy-v1`;
- storage namespace id: `skald-vault/v1/local-records`;
- record namespace id: `skald-vault/v1/records`;
- manifest namespace id: `skald-vault/v1/manifests`.

Future storage namespace and path rules:

- storage namespace ids must be stable ASCII constants or validated safe identifiers;
- namespace IDs may contain `/` only as internal namespace identifiers, never as filesystem path segments;
- path segments must be encoded by explicit policy before any future filesystem use;
- path segments are lowercase ASCII letters, digits, hyphen, and underscore only;
- vault ids must not be used directly as raw filesystem paths;
- fixed non-secret 16-byte vault ids encode deterministically as `vault_` plus lowercase hex;
- fixed non-secret 16-byte record ids encode deterministically as `record_` plus lowercase hex;
- the manifest segment is the stable safe segment `manifest_v1`;
- user-controlled strings must not become filesystem paths;
- path traversal is forbidden;
- absolute user-supplied paths are forbidden;
- Windows drive prefixes and URI-like prefixes such as `file:` are forbidden;
- whitespace, control characters, invisible format characters, non-ASCII text, and unsupported punctuation are forbidden in path segments;
- secret-looking material must not appear in path names;
- wallet labels, note text, wallet names, record titles, and other user-provided names must not appear in path names;
- future implementation must choose a reviewed platform-specific app-private root;
- symlink-following behavior requires review before implementation.

The policy returns typed rejection reasons for empty identifiers, dot segments, parent segments, path separators, traversal, control characters, whitespace, invisible format characters, non-ASCII characters, unsupported characters, excessive length, wrong vault/record ID length, user-controlled input, secret-looking material, absolute paths, URI-like prefixes, Windows drive prefixes, and unknown namespace policy values.

This branch implements only pure validation and relative safe segment encoding. It does not implement path construction, absolute paths, platform root selection, directory creation, symlink behavior, storage index read/write, file reads, file writes, database storage, DataStore, SharedPreferences, manifest file/storage read/write, secure-storage success paths, or persistence.

## Logical Storage Layout Plan

The storage layout plan policy id is:

```text
skald-vault-v1-storage-layout-plan-v1
```

`SkaldVaultV1StorageLayoutPlanPolicy` composes already validated namespace/path policy segments into rootless logical locations. Every location is represented as a list of safe relative segments. The list elements are not joined into one path string, and no location contains a platform root, absolute path, filesystem object, Android URI, storage handle, or database key.

For a fixed non-secret vault id, the plan includes:

- current container: `skald-vault-v1`, `vault_<hex>`, `container`, `current`;
- current manifest: `skald-vault-v1`, `vault_<hex>`, `manifest`, `manifest_v1`;
- current storage index: `skald-vault-v1`, `vault_<hex>`, `index`, `index_v1`;
- record artifact: `skald-vault-v1`, `vault_<hex>`, `records`, `record_<hex>`;
- temporary container: `skald-vault-v1`, `vault_<hex>`, `tmp`, `container_pending`;
- temporary manifest: `skald-vault-v1`, `vault_<hex>`, `tmp`, `manifest_pending`;
- temporary storage index: `skald-vault-v1`, `vault_<hex>`, `tmp`, `index_pending`;
- quarantine root: `skald-vault-v1`, `vault_<hex>`, `quarantine`, `pending`;
- recovery metadata: `skald-vault-v1`, `vault_<hex>`, `recovery`, `recovery_v1`.

All stable artifact segment constants must pass the same safe-segment validation as encoded vault, manifest, and record segments. The plan rejects unsupported layout policy ids, wrong-length vault ids, wrong-length record ids, unsafe artifact segments, absolute-looking segments, traversal, user-controlled labels/text, and secret-looking input with typed results.

Layout output is not a filesystem path. Future platform path construction must join these segments only under a reviewed Android or desktop platform root, prove final containment under that root, and separately satisfy symlink, permission, ownership, durability, atomicity, recovery, and secure-storage review. Layout planning evidence does not imply platform root approval, actual path construction, storage index read/write, vault persistence, provider selectability, or production storage safety.

This branch implements only pure logical layout planning. It does not implement path joins, path containment, absolute paths, platform root selection, directory creation, symlink checks, permission checks, durability probes, file reads, file writes, database storage, DataStore, SharedPreferences, manifest file/storage read/write, storage index read/write, secure-storage success paths, or persistence.

## Path-Containment Planner

The path-containment planner policy id is:

```text
skald-vault-v1-path-containment-planner-v1
```

`SkaldVaultV1PathContainmentPlanner` accepts only reviewed root tokens and safe relative layout segments. The allowed root-token classes are:

- Android app-private internal storage root token;
- desktop app-controlled user-data root token;
- test-only reviewed root token.

The planner rejects external/shared roots, user-selected path roots, unknown roots, unreviewed roots, unsafe roots, and missing root tokens with typed failures. Root tokens are identifiers for future reviewed root classes only. They do not contain filesystem path strings, absolute paths, user-selected paths, Android `Context` references, storage handles, or platform root resolution logic.

A planned artifact location contains:

- reviewed root token;
- storage layout policy id;
- artifact kind;
- list of safe relative segments from the logical storage layout plan;
- a segment-level containment proof status.

Required planned artifact kinds are current container, current manifest, current storage index, record artifact, temporary container, temporary manifest, temporary storage index, quarantine root, and recovery metadata.

The planner revalidates segment lists using the same safe-segment policy used by the namespace/path and layout building blocks. It rejects empty segment lists, absolute-looking segments, dot and parent segments, slash or backslash inside segments, path traversal, control characters, whitespace, invisible format characters, non-ASCII text, unsupported punctuation, too-long segments, user-label/text inputs, and secret-looking inputs. Accepted output is root-token-bound and relative-only.

Planner output is not a platform path. Future platform path construction must join the planned segments only under a resolved and reviewed platform root, prove real containment under that root, and separately satisfy symlink, permission, ownership, durability, atomicity, recovery, and secure-storage review. Planner evidence does not imply platform root resolution, actual path construction, real containment checks, platform storage implementation, vault persistence, provider selectability, or production storage safety.

This branch implements only pure path-containment planning. It does not implement root resolution, path joins, absolute paths, path normalization, real containment checks, platform root selection, directory creation, symlink checks, permission checks, durability probes, file reads, file writes, database storage, DataStore, SharedPreferences, manifest file/storage read/write, storage index read/write, secure-storage success paths, or persistence.

## Secure Storage Boundary

Encrypted vault container storage is separate from secure secret storage.

The boundary for this contract is:

- secure secret storage remains disabled and fail-closed;
- secure metadata storage remains disabled and fail-closed;
- Android hardware or biometric wrapping remains optional future convenience only and must not replace passphrase recovery;
- Linux/desktop must not rely on OS keyrings as the primary vault protection model;
- no storage success path is approved until secure secret and secure metadata boundaries are implemented and tested.

Provider KAT success and the still-disabled provider facade do not enable storage. They prove crypto building-block evidence and provider-boundary status only.

## Model And Test Expectations

The readiness and acceptance models must distinguish:

- implemented/tested still-disabled in-memory container parser/writer;
- implemented/tested still-disabled in-memory manifest parser/writer;
- implemented/tested still-disabled local manifest-relative stale-record decision policy;
- implemented/tested still-disabled in-memory storage atomicity/crash simulator;
- implemented/tested still-disabled storage namespace/path policy;
- implemented/tested still-disabled logical storage layout plan;
- implemented/tested still-disabled path-containment planner;
- implemented/tested still-disabled Linux root-resolution evidence policy;
- implemented/tested still-disabled storage safety preflight boundary;
- documented/model-only platform storage boundary;
- documented/model-only atomic write strategy;
- documented/model-only crash-recovery contract;
- documented/model-only interruption-test contract;
- documented/model-only storage failure model;
- documented/model-only secure-storage boundary;
- documented/model-only platform storage-root contract;
- documented/model-only platform root settings policy;
- documented/model-only safe path-construction contract;
- documented/model-only symlink/traversal contract;
- documented/model-only storage permission/ownership contract;
- documented/model-only durability capability contract;
- documented/model-only durability fail-closed policy;
- documented/model-only warning-only durability rejection policy;
- absent anti-rollback anchor and no full rollback-resistance claim;
- absent actual path construction, path joining, real containment checks, directory creation, platform root selection/resolution, Settings UI, settings persistence, OS keyring integration, password-manager integration, symlink checks, permission checks, durability probes, storage safety preflight filesystem checks, warning-only encrypted vault persistence path, storage, manifest file/storage read/write, storage index read/write, real atomic write/recovery, platform interruption hooks, and secure-storage implementation;
- disabled production persistence;
- implemented/tested still-disabled passphrase policy boundary;
- absent passphrase input acceptance, passphrase normalization/encoding execution, passphrase hashing/fingerprinting, retry/throttle/lockout runtime behavior, passphrase UI, unlock UI, Android Keystore passphrase wrapping, OS keyring passphrase storage, and password-manager passphrase storage.

Missing, unknown, failed, unsupported, unreviewed, insufficient, unsafe, documented-only, or unimplemented container, manifest, stale-record, namespace/path, logical layout, path-containment planner, Linux root-resolution, platform-root, platform root settings, Settings UI/persistence, path-construction, storage safety preflight, symlink/traversal, permission/ownership, durability, atomicity, or secure-storage evidence must block provider selectability and persistence. Warning-only durability/preflight evidence and user-consent override evidence are not sufficient for encrypted vault persistence.

Completed in-memory container parser/writer tests, completed in-memory manifest parser/writer tests, completed local stale-record decision tests, completed in-memory storage atomicity/crash simulator tests, completed namespace/path policy tests, completed logical storage layout tests, completed path-containment planner tests, completed Linux root-resolution evidence tests, completed storage safety preflight tests, completed provider-level KATs, completed still-disabled crypto building-block tests, and the metadata-only provider facade do not make the provider selectable and do not make storage persistence-ready.

The still-disabled provider operation authorization boundary now models future provider operation kinds, purposes, hard gates, blockers, warnings, and disabled capabilities for provider-backed container, manifest, storage-index, and record work. Current authorization is blocked/fail-closed because the registry selects only `DisabledVaultCryptoProvider` and `productionProviderSelectable` remains false. The boundary does not run provider operations, KATs, runtime randomness checks, entropy/salt/nonce/key generation, Argon2id/KDF/HKDF/HMAC, AEAD encrypt/decrypt, header commitment computation or verification, record encryption/decryption, manifest authentication, storage-index authentication, key wrapping/unwrapping, provider clear/dispose, storage parsing, migration, repair, quarantine, vault creation, vault unlock, vault persistence, or mainnet operation. It accepts no raw storage bytes, ciphertext, plaintext, nonce/tag/header commitment bytes, passphrases, keys, provider handles, paths, Settings values, or storage handles. It is required gate evidence only.

The still-disabled provider operation dispatch isolation boundary now models the absent dispatch/routing surface for future provider-backed container, manifest, storage-index, and record work. Current dispatch isolation confirms that no non-disabled provider operation dispatcher exists or is reachable from provider selection, registry isolation, factory isolation, skeleton evidence, candidate packaging evidence, dependency/build evidence, creation/unlock/storage readiness, or persistence readiness. It does not add dependencies, implement a provider, add a provider factory, add a provider dispatcher, enable a non-disabled registry, instantiate provider code, expose provider handles or crypto objects, accept byte material, run provider operations or KATs, call randomness, run KDF/HKDF/HMAC/AEAD, compute header commitments, encrypt/decrypt records, wrap/unwrap keys, parse storage, migrate, repair, quarantine, create a vault, unlock a vault, persist a vault, or approve mainnet.

The still-disabled provider KAT execution isolation boundary now models the absent provider KAT executor surface for future provider-backed container, manifest, storage-index, and record work. Current KAT isolation confirms that no non-disabled provider KAT executor exists or is reachable from provider selection, registry isolation, factory isolation, dispatch isolation, skeleton evidence, candidate packaging evidence, dependency/build evidence, public vector docs, creation/unlock/storage readiness, or persistence readiness. Public non-wallet vector docs, canonical header/HKDF/HMAC vector docs, provider KAT contract evidence, and test-provider KAT harness concepts cannot authorize production provider readiness by themselves. It does not add dependencies, implement a provider, add a provider factory, add a provider dispatcher, add a provider KAT executor, enable a non-disabled registry, instantiate provider code, expose provider handles or crypto objects, accept byte material, run provider operations or KATs, call randomness, run KDF/HKDF/HMAC/AEAD, compute header commitments, encrypt/decrypt records, wrap/unwrap keys, parse storage, migrate, repair, quarantine, create a vault, unlock a vault, persist a vault, change provider selection, set `productionProviderSelectable=true`, or approve mainnet.

The still-disabled runtime randomness authorization boundary now models future OS CSPRNG, reviewed provider randomness, hardware-backed entropy evidence, salt generation, nonce generation, key-generation input, KDF salt, AEAD nonce, storage-related nonce/counter seed, backup/export nonce/salt, migration/recovery nonce/salt, test-vector scope, production runtime scope, and mainnet randomness requests. Current authorization is blocked/fail-closed because provider operations remain unauthorized, runtime randomness checks do not approve production use, no platform randomness source is reviewed/enabled, the registry selects only `DisabledVaultCryptoProvider`, and `productionProviderSelectable` remains false. The boundary does not call `SecureRandom`, Kotlin Random, Java Random, `Math.random`, OS randomness APIs, provider randomness APIs, runtime health checks, entropy generation, salt generation, nonce generation, key generation, random byte fixtures, deterministic-vector generation, provider operations, KATs, KDF/HKDF/HMAC/AEAD, storage parsing, migration, repair, quarantine, vault creation, vault unlock, vault persistence, or mainnet operation. Future storage-related randomness remains blocked until reviewed OS cryptographic randomness/CSPRNG or reviewed provider randomness, storage safety, persistence readiness, migration/corruption, clear/wipe, redaction, and provider-operation gates are approved. Android hardware-backed key protection remains separate from entropy quality, Linux entropy quality remains a required review gate, OS keyrings/password managers remain rejected for Skald-managed vault passphrase storage, and passphrase-first remains the default authority.

## Remaining Gates

Before provider selectability:

- final Argon2id parameter approval;
- supported-platform runtime provider/randomness review;
- selectable production provider implementation;
- provider-level KAT execution through the selectable provider boundary;
- lock/session lifecycle and redaction/leakage testing;
- release and human review.

Before vault persistence:

- manifest file/storage read/write implementation and tests;
- manifest-backed storage integration for stale-record enforcement;
- platform storage implementation for the selected supported platforms;
- atomic write implementation;
- crash-recovery implementation;
- interruption/corruption tests;
- storage failure runtime mapping;
- logical layout and path-containment planner review remain necessary but not sufficient; platform path construction from that layout is still absent;
- platform root resolution implementation and review;
- Android app-private internal root implementation/review and Linux default user-data root implementation/review;
- Settings UI, settings persistence, custom-root root resolution, and containment/symlink/permission/durability review before any Linux custom root is usable;
- OS keyring and password-manager integration must remain absent for Skald-managed vault passphrase storage unless a future explicit design review changes the policy;
- actual path construction, real path containment checks, and directory creation review;
- symlink/traversal behavior review and checks;
- permission/ownership checks;
- durability capability probes and platform-specific durability review proving required behavior or an explicitly reviewed equivalent safe strategy;
- durability fail-closed runtime evidence, with no warning-only or user-consent override path for encrypted vault writes;
- secure secret storage and secure metadata storage boundary implementation;
- explicit review of rollback limitations and any anti-rollback anchor decision.

## KDF Calibration Authorization Boundary

The storage contract now records `SkaldVaultV1KdfCalibrationAuthorizationPolicy` as still-disabled model-only evidence before any future storage/unlock path can rely on KDF parameters or KDF execution. It models future KDF operation kinds, purposes, parameter/evidence kinds, platform/device classes, required gates, blockers, warnings, disabled capabilities, and redacted policy tokens.

Current KDF calibration authorization is blocked/fail-closed because provider operations are unauthorized, runtime randomness is unauthorized, passphrase input remains blocked, final KDF calibration is not approved, `productionProviderSelectable` remains false, and no production KDF execution is allowed. The boundary does not run Argon2id, run KDFs, run calibration, benchmark devices, inspect real host/device details, approve final KDF parameters, normalize or encode real passphrases, generate or consume salts, call randomness APIs, run provider operations, run provider KATs, derive vault keys, enable vault unlock, enable vault persistence, read or write container/manifest/storage-index/record data, make a provider selectable, or approve mainnet.

Android calibration remains future-reviewed only, Linux calibration remains future-reviewed only, test-vector profiles do not authorize production runtime unlock, mainnet KDF use remains blocked until release review, OS keyrings and password managers remain rejected for Skald-managed vault passphrase storage, and passphrase-first remains the default authority.

## Secure-Storage Authorization Boundary

The storage contract now records `SkaldVaultV1SecureStorageAuthorizationPolicy` as still-disabled model-only evidence before any future storage/unlock path can store, retrieve, delete, wrap, unwrap, export, import, migrate, purge, or disclose sensitive vault values. It models future secure-storage operation kinds, secure value kinds, storage target kinds, required gates, blockers, warnings, disabled capabilities, and redacted policy tokens.

Current secure-storage authorization is blocked/fail-closed because persistence readiness is blocked, secure secret storage and secure metadata storage are unavailable, provider operations are unauthorized, runtime randomness authorization is blocked, KDF calibration authorization is blocked, `productionProviderSelectable` remains false, and no target is approved for production secret storage. The boundary does not store secrets, retrieve secrets, delete secrets, wrap keys, unwrap keys, store wrapped keys, store sensitive metadata, retrieve sensitive metadata, store manifest metadata, retrieve manifest metadata, store recovery metadata, retrieve recovery metadata, export backup material, import backup material, migrate secure storage, validate secure storage integrity, purge secure storage, use Android Keystore, use Android Credential Manager, use OS keyrings, use password managers, use SharedPreferences, use Settings storage, use files or databases, read or write encrypted local vault storage, approve encrypted local vault storage, approve Android hardware-backed wrapping, approve Linux optional key wrapping, run provider operations, run KDF/HKDF/HMAC/AEAD, call randomness APIs, enable vault creation, enable vault unlock, enable vault persistence, make a provider selectable, or approve mainnet.

OS keyrings remain rejected as primary storage and for Skald-managed vault passphrase storage. Password managers remain rejected for Skald-managed vault passphrase storage. Settings/preferences storage remains rejected for secrets and sensitive metadata. Plaintext storage, logs, crash reports, analytics, and support exports remain rejected for raw secret material. Test-only fake storage does not authorize production runtime. Android secure-storage evidence remains app-private internal only and future-reviewed; Android external/shared roots remain rejected. Linux default root evidence remains insufficient for persistence, Linux custom roots remain future Settings-configurable only and unusable, and Linux optional key wrapping remains future-reviewed only.

## Vault Unlock Authorization Boundary

The storage contract now records `SkaldVaultV1UnlockAuthorizationPolicy` as still-disabled model-only evidence before any future unlock path can read encrypted local vault storage, retrieve secure metadata, retrieve wrapped keys, decrypt records, or create an active session. It models future unlock operation kinds, purposes, credential classes, required gates, blockers, warnings, disabled capabilities, and redacted policy tokens.

Current unlock authorization is blocked/fail-closed because passphrase input is blocked, KDF calibration authorization is blocked, runtime randomness authorization is blocked, provider operations are unauthorized, secure-storage authorization is blocked, storage service operations are disabled, lock/session lifecycle is unavailable, persistence readiness is blocked, `productionProviderSelectable` remains false, and no production unlock execution is allowed. The boundary does not accept passphrases, PINs, or biometrics; normalize or encode passphrases; run Argon2id/KDF/HKDF/HMAC/AEAD; generate or consume salts, nonces, or random bytes; call provider operations; read secure storage, metadata storage, container data, manifest data, storage-index data, record data, or encrypted vault storage; retrieve or unwrap wrapped keys; decrypt records; create active sessions; hold decrypted key material; persist unlock state; add UI; enable vault creation, vault unlock, vault persistence, make a provider selectable, or approve mainnet.

Android unlock evidence remains app-private-internal-root only and future-reviewed; Android external/shared roots remain rejected. Linux default root evidence remains insufficient for unlock or persistence, Linux custom roots remain future Settings-configurable only and unusable, Linux optional wrapping remains future-reviewed only, OS keyrings remain rejected as primary storage and for Skald-managed vault passphrase storage, password managers remain rejected for Skald-managed vault passphrase storage, Settings/preferences storage remains rejected for secrets and unlock state, provider selection still selects only `DisabledVaultCryptoProvider`, and passphrase-first remains the default authority.

## Vault Creation Authorization Boundary

The storage contract now records `SkaldVaultV1CreationAuthorizationPolicy` as still-disabled model-only evidence before any future creation path can initialize encrypted local vault storage, create secure metadata, store wrapped key material, create a manifest/storage index/record, or commit initial persistence. It models future creation operation kinds, purposes, initializer classes, required gates, blockers, warnings, disabled capabilities, and redacted policy tokens.

Current creation authorization is blocked/fail-closed because passphrase input is blocked, KDF calibration authorization is blocked, runtime randomness authorization is blocked, provider operations are unauthorized, secure-storage authorization is blocked, storage service operations are disabled, storage safety is not runtime-approved, persistence readiness is blocked, unlock authorization is blocked, `productionProviderSelectable` remains false, and no production creation execution is allowed. The boundary does not accept passphrases, PINs, or biometrics; normalize or encode passphrases; generate salts, nonces, keys, or ids; run Argon2id/KDF/HKDF/HMAC/AEAD; call provider operations; create headers, header commitments, containers, manifests, storage indexes, records, secure metadata, wrapped keys, storage namespaces, persistence commits, rollback handlers, failure cleanup, or active sessions; write secure storage, metadata storage, container data, manifest data, storage-index data, record data, Settings, files, or databases; add UI; enable vault creation, vault unlock, vault persistence, make a provider selectable, or approve mainnet.

Android creation evidence remains app-private-internal-root only and future-reviewed; Android external/shared roots remain rejected. Linux default root evidence remains insufficient for creation or persistence, Linux custom roots remain future Settings-configurable only and unusable, Linux optional wrapping remains future-reviewed only, OS keyrings remain rejected as primary storage and for Skald-managed vault passphrase storage, password managers remain rejected for Skald-managed vault passphrase storage, Settings/preferences storage remains rejected for secrets, creation state, and unlock state, provider selection still selects only `DisabledVaultCryptoProvider`, and passphrase-first remains the default authority.

## Authorization/Readiness Matrix

The storage contract now records `SkaldVaultV1AuthorizationReadinessMatrixPolicy` as still-disabled model-only evidence for cross-boundary traceability. It summarizes which current boundary blocks each future production/runtime capability across provider selection, provider operations, runtime randomness, KDF calibration, secure storage, vault creation, vault unlock, active sessions, persistence, encrypted local vault storage, manifest/storage-index/record read/write, atomic write, crash recovery, migration, corruption recovery, rollback protection, clear/wipe, diagnostics, passphrase gates, Android and Linux platform gates, BDK persistence, wallet work, and mainnet.

The matrix does not implement or approve storage. Every production/runtime capability remains blocked; warning-only evidence cannot authorize production use; user consent cannot override missing hard gates; test-only evidence cannot authorize production runtime; mainnet remains disabled; provider selection still selects only `DisabledVaultCryptoProvider`; and `productionProviderSelectable` remains false.
