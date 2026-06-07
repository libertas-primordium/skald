# Secure Storage Design

## Status

Skald Vault secure storage is currently disabled and fail-closed.

The current codebase has a common `SecureSecretStorage` interface, typed secret metadata models, and Android/Linux desktop implementations that delegate to disabled storage. Those implementations reject secret writes, reads, metadata listing, and deletes. No real secret persistence exists yet.

The current codebase also has a disabled/fail-closed secure wallet metadata persistence boundary documented in [`SECURE_METADATA_BOUNDARY.md`](SECURE_METADATA_BOUNDARY.md). That boundary classifies observation history, address index state, labels, backend metadata, UTXO state, wallet notes, transaction notes, recovery metadata, Privacy Analyzer metadata, and identity-linkage metadata as sensitive wallet metadata. It rejects all metadata reads, writes, listing, and deletes until app-controlled encrypted vault storage exists.

The detailed app-controlled encrypted local vault architecture and key-lifecycle plan is now documented in [`ENCRYPTED_LOCAL_VAULT_DESIGN.md`](ENCRYPTED_LOCAL_VAULT_DESIGN.md). The crypto/key-lifecycle decision record is [`ENCRYPTED_LOCAL_VAULT_CRYPTO_DECISION.md`](ENCRYPTED_LOCAL_VAULT_CRYPTO_DECISION.md). The code-level readiness models are documented in [`ENCRYPTED_VAULT_READINESS_POLICY.md`](ENCRYPTED_VAULT_READINESS_POLICY.md). The focused dependency spike is documented in [`ENCRYPTED_LOCAL_VAULT_DEPENDENCY_SPIKE.md`](ENCRYPTED_LOCAL_VAULT_DEPENDENCY_SPIKE.md), with desktop and Android runtime known-answer-vector validation documented in [`ENCRYPTED_LOCAL_VAULT_KAT_VALIDATION.md`](ENCRYPTED_LOCAL_VAULT_KAT_VALIDATION.md), the libsodium/Kotlin packaging comparison documented in [`ENCRYPTED_LOCAL_VAULT_LIBSODIUM_COMPARISON.md`](ENCRYPTED_LOCAL_VAULT_LIBSODIUM_COMPARISON.md), the Tink/Bouncy dependency review documented in [`ENCRYPTED_LOCAL_VAULT_DEPENDENCY_REVIEW.md`](ENCRYPTED_LOCAL_VAULT_DEPENDENCY_REVIEW.md), the disabled provider boundary documented in [`ENCRYPTED_LOCAL_VAULT_CRYPTO_PROVIDER_BOUNDARY.md`](ENCRYPTED_LOCAL_VAULT_CRYPTO_PROVIDER_BOUNDARY.md), the provider-level KAT contract documented in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md), the test-only provider KAT harness documented in [`ENCRYPTED_LOCAL_VAULT_TEST_PROVIDER_KAT_HARNESS.md`](ENCRYPTED_LOCAL_VAULT_TEST_PROVIDER_KAT_HARNESS.md), Argon2id calibration policy/probes documented in [`ENCRYPTED_LOCAL_VAULT_ARGON2ID_CALIBRATION.md`](ENCRYPTED_LOCAL_VAULT_ARGON2ID_CALIBRATION.md), non-final candidate Argon2id parameter tiers documented in [`ENCRYPTED_LOCAL_VAULT_ARGON2ID_PARAMETER_POLICY.md`](ENCRYPTED_LOCAL_VAULT_ARGON2ID_PARAMETER_POLICY.md), manual Android calibration evidence capture documented in [`ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md), Android compatibility/entropy policy documented in [`ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md), and runtime randomness/provider checks documented in [`ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md`](ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md). These documents and models define the required primary storage model for both secrets and sensitive wallet metadata while keeping runtime storage disabled. The capture model only structures Android parameter evidence; it does not make storage, unlock, or production KDF execution available. The compatibility/entropy and runtime randomness models require approved cryptographic randomness and fail-closed vault creation, but they do not implement entropy collection, key generation, key wrapping, or storage. OS keyrings are not primary storage; they may later wrap vault keys only after explicit design review.

The disabled provider-selection boundary is documented in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md). It treats disabled secure storage and disabled secure metadata persistence as blockers for production provider selection and selects only the disabled provider.

The dependency spike pins Tink and Bouncy Castle artifacts for platform compile/package evaluation plus desktop and Android runtime public-vector validation only. The dependency review records candidate-level package, license, keyset/storage, Bouncy Castle Argon2id API, and split-provider evidence. The disabled provider boundary models the future provider surface while rejecting every operation. The provider-selection boundary blocks non-disabled provider selection. The provider-level KAT contract models future positive and negative provider checks, and the test-only harness exercises those checks without approving production provider behavior. The Argon2id parameter policy defines candidate tiers only; it does not approve final production parameters. None of these documents or models makes `SecureSecretStorage` available or adds secret persistence.

This document is a design prerequisite before any implementation enables secret storage. It does not enable wallet creation, credential storage, signing, broadcasting, backend networking, Cashu proof persistence, Lightning credential persistence, Nostr key storage, backup encryption, production KDF execution, Android vault creation approval, or mainnet behavior.

## Scope

Future secure storage must cover secret persistence for:

- App seed material.
- BIP39 mnemonic material, if Skald Vault later uses BIP39.
- Descriptor private keys.
- Imported private keys.
- Nostr identity private-key material.
- Bitcoin Core RPC password or cookie material.
- Electrum credentials, if ever needed.
- Esplora credentials, if ever needed.
- Tor proxy password, if ever needed.
- LND macaroon and TLS material, if ever needed.
- Core Lightning rune material.
- NWC secret material.
- Phoenixd auth token material.
- Cashu seed material.
- Cashu proof material.
- Backup encryption keys.
- Metadata encryption keys.

Future encrypted metadata storage must also cover sensitive wallet metadata before production observation persistence is enabled:

- Observed addresses.
- Address index state.
- UTXO sets and outpoints.
- Wallet labels and clusters.
- Transaction notes and wallet history.
- Backend observation metadata.
- Privacy Analyzer state.

The secure-storage boundary must also support metadata-only references that let non-secret settings point to secret records without embedding the secret payload in settings storage.

## Non-goals

This design does not currently enable:

- Mainnet.
- Wallet creation.
- Key generation.
- Production random-byte generation.
- Descriptor derivation.
- Address derivation.
- Signing.
- Broadcasting.
- Chain sync.
- Backend connection/authentication.
- Cashu proof storage.
- Lightning credential storage.
- Nostr key storage.
- Backend credential storage.
- Backup export.
- Android Keystore implementation.
- Encrypted local vault implementation.
- Linux keyring wrapping implementation.
- Encrypted file storage.

Any future implementation must be explicitly approved as a separate pass and must keep the current disabled behavior until it passes the acceptance criteria in this document.

## Threat model

Secure storage should reduce damage from common local compromise scenarios, but it cannot protect secrets from every device compromise.

Threats in scope:

- Stolen Android phone.
- Stolen Linux laptop.
- Malicious app or process with filesystem access.
- Android backup leakage.
- Linux plaintext home-directory exposure.
- Accidental sync or cloud backup of secret files.
- Clipboard leakage.
- Logs and crash reports.
- Screenshots and screen recording.
- Rooted or debuggable Android devices.
- Compromised unlocked desktop session.
- Malicious connector endpoints requesting excessive credential permissions.
- Partial writes and corrupted vault records.
- Downgrade or migration failures.
- User forgetting passphrase, PIN, or other unlock material.
- Biometric bypass or OS-level compromise.
- Memory exposure while the app is unlocked.

Threats that local secure storage cannot fully solve:

- A fully compromised live device that can read process memory or intercept UI input.
- Malware running as the same user during an unlocked desktop session.
- A malicious operating system.
- A malicious hardware/firmware stack.
- User-approved reveal/export flows that expose secrets outside the app.

Skald Vault must state these limits plainly. Secure storage can reduce at-rest exposure; it cannot guarantee safety on a compromised running device.

## Secret classes

Secret classes define storage requirements, recovery consequences, and UI/export restrictions. The class is metadata; the secret payload is never metadata.

### Class A: master wallet material

Includes app seed material, BIP39 mnemonic material if used, descriptor private keys, and metadata encryption keys that unlock wallet state.

- Storage requirement: strongest available platform protection plus encrypted payload storage.
- Recovery consequence: loss may permanently lose funds or make metadata unrecoverable.
- Export allowed: only deliberate, high-friction export/reveal flows after explicit confirmation.
- Display allowed: only short-lived reveal views with strong warnings.
- Clipboard allowed: discouraged by default; if ever allowed, use short auto-clear and warning.
- Biometric-only unlock acceptable: no; biometric may be convenience only, not sole recovery/control.
- Cloud backup: forbidden unless payload is independently encrypted with user-controlled material.
- Deletion confirmation: mandatory, with recovery impact shown.

### Class B: imported identity or bearer wallet material

Includes imported private keys, Nostr identity private-key material, Cashu seed material, and Cashu proof material.

- Storage requirement: secure payload storage with separate recovery warnings per origin.
- Recovery consequence: loss may lose funds or break identity-linked recovery; theft can compromise both funds and identity.
- Export allowed: only with explicit origin-specific warnings.
- Display allowed: only deliberate short-lived reveal/export flows.
- Clipboard allowed: generally forbidden for Cashu proof material and identity private keys; any exception requires explicit approval.
- Biometric-only unlock acceptable: no for high-risk material; biometric can only supplement a stronger unlock.
- Cloud backup: forbidden unless encrypted under user-controlled backup material.
- Deletion confirmation: mandatory, with clear warning that imported material is not restored by the app seed.

### Class C: Lightning and remote-node credentials

Includes LND macaroon/TLS material, Core Lightning runes, NWC secret material, Phoenixd auth token material, and other remote Lightning credentials.

- Storage requirement: secure payload storage with permission metadata visible to the user.
- Recovery consequence: loss may require re-pairing; theft may allow node control depending on credential permission.
- Export allowed: generally no; re-pairing should be preferred where possible.
- Display allowed: no raw reveal by default; future reveal requires explicit user approval and permission warning.
- Clipboard allowed: no by default.
- Biometric-only unlock acceptable: only for low-risk read-only credentials after review; not for spend/admin credentials.
- Cloud backup: forbidden unless encrypted and explicitly user-controlled.
- Deletion confirmation: mandatory for credentials with payment, channel, or admin authority.

### Class D: backend and transport credentials

Includes Bitcoin Core RPC password/cookie material, Electrum credentials if ever needed, Esplora credentials if ever needed, and Tor proxy password material.

- Storage requirement: secure payload storage or metadata-only credential reference until storage is enabled.
- Recovery consequence: loss usually breaks connectivity rather than losing funds, but theft can expose node access or privacy.
- Export allowed: usually no; reconfiguration is preferred.
- Display allowed: no raw reveal by default.
- Clipboard allowed: no by default.
- Biometric-only unlock acceptable: possible for low-risk connectivity credentials after review, never as a blanket policy.
- Cloud backup: forbidden unless encrypted and user-controlled.
- Deletion confirmation: required when deleting active credentials used by selected profiles.

### Class E: backup and metadata encryption keys

Includes backup encryption keys and metadata encryption keys.

- Storage requirement: strongest available storage with migration tests and recovery plan.
- Recovery consequence: loss may make encrypted backups or metadata unreadable.
- Export allowed: only through dedicated backup/recovery flows.
- Display allowed: no raw reveal by default.
- Clipboard allowed: no.
- Biometric-only unlock acceptable: no.
- Cloud backup: forbidden unless separately encrypted and intentionally exported by the user.
- Deletion confirmation: mandatory and should require typing or selecting the affected backup scope.

## Platform strategy

Skald Vault should keep the common `SecureSecretStorage` interface as the only boundary through which future secret-bearing features can persist or retrieve secret material.

Platform-specific implementations should be introduced behind explicit disabled feature flags or policy gates. The disabled/fail-closed implementation remains the default until:

- The implementation passes common interface tests.
- Platform-specific tests cover at-rest behavior and failure modes.
- Logging/redaction tests pass.
- Backup exclusion or encrypted backup behavior is verified.
- Recovery Center and UI warnings are updated.
- The user explicitly approves enabling real secret-storage implementation work.

Non-secret settings repositories may store profile labels, enum statuses, secret IDs, secret kinds, recovery warnings, and credential references. They must never store raw secret payloads.

## Android design

Future Android secure storage may use Android Keystore-backed key wrapping or encryption for secret payload keys after review. Random byte generation is separate: vault secrets, salts, nonces, keys, and unlock material must use Android OS cryptographic randomness or reviewed crypto-provider randomness, and must not fall back to Kotlin/Java/general-purpose random APIs.

Design requirements:

- Treat the app-controlled encrypted local vault as primary storage.
- Require approved cryptographic randomness for vault key material, salts, nonces, and unlock-related secret material.
- Prefer hardware-backed keys when available.
- Use StrongBox where available, but do not make it the only supported path unless the app explicitly declares that requirement.
- Distinguish user-authenticated keys from app-authenticated keys.
- Treat biometric/PIN as unlock convenience and user-presence signal, not as sole recovery.
- Store encrypted payload records locally; do not store plaintext secret payloads in SharedPreferences, config files, logs, or crash reports.
- Exclude secret payload storage from Android Auto Backup.
- Keep `android:allowBackup="false"` unless a later encrypted backup design explicitly changes that policy.
- Provide an option to block screenshots/screen recording on secret reveal/export views.
- Avoid clipboard use for secrets; if a future flow permits clipboard use, auto-clear and warn.
- Warn on rooted, debuggable, or otherwise high-risk devices where feasible.
- Explain app reinstall implications: Android Keystore material may be lost on reinstall, device reset, profile removal, or OS/key invalidation events.
- Support versioned payload records and migration from one storage format to another.
- Fail closed if key generation, key lookup, decryption, authentication, or migration fails.

Android failure handling:

- If encrypted payload storage is missing, return unavailable rather than creating placeholder secrets.
- If authentication is required and not satisfied, return a typed locked/unavailable result.
- If decryption fails, do not retry by weakening policy.
- If migration fails, keep old data intact when possible and mark the secret unavailable until user action.
- If backup exclusion cannot be verified for a release build, do not enable secret storage.

## Linux desktop design

Future Linux desktop secure storage should use the app-controlled encrypted local vault as primary storage.

Linux platform keyrings such as libsecret or KWallet must not be treated as the primary secret or wallet metadata store. Desktop keyrings may remain unlocked after login and can be broadly accessible to same-user processes during an unlocked desktop session. They may later be evaluated only as optional vault-key wrapping helpers after explicit design review.

Design requirements:

- No plaintext secret files.
- No unencrypted JSON, text, or line-based secret payload files.
- No plaintext sensitive wallet metadata files.
- Use strict file permissions such as `0600` for any local encrypted payload file.
- Create directories with restrictive permissions and account for umask behavior.
- Store encrypted vault data under a reviewed user-local Skald data directory, never inside the application package.
- Treat desktop session compromise as a serious limitation: a process running as the same user during unlock may observe UI, clipboard, files, or process memory.
- Document Wayland and X11 clipboard/screenshot risks.
- Prefer hardware signers for high-value funds even if local secret storage exists.
- Support versioned records, migration tests, and corrupted-record handling.
- Fail closed if the vault cannot unlock, key wrapping is unavailable, migration fails, or encrypted records cannot be authenticated.

Linux encrypted vault requirements:

- Use a reviewed password-based key derivation design.
- Use authenticated encryption.
- Include per-record or per-vault versioning.
- Avoid storing passphrases in process-global state.
- Support partial-write protection, such as atomic replace after fsync where practical.
- Require explicit user setup before enabling.
- Treat libsecret/KWallet wrapping as optional defense-in-depth, not as a substitute for the vault.

## Metadata versus secret payloads

Skald Vault already stores non-secret profile metadata. That metadata may include:

- Profile IDs.
- Labels.
- Network enum choices.
- Backend endpoint host/port/path fields without credentials.
- Trust and privacy classifications.
- Secret IDs.
- Secret kinds.
- Secret labels.
- Storage status.
- Recovery warnings.
- Credential references.

Metadata must not include:

- Raw secret bytes or strings.
- Mnemonic words.
- Private keys.
- Descriptor private material.
- Nostr identity private-key material.
- Node credentials.
- Cashu proof material.
- Backup encryption keys.
- Authentication cookies, tokens, or headers.

Observed addresses, address index state, UTXO sets, outpoints, backend observation metadata, labels, transaction notes, wallet history, privacy-analysis state, recovery metadata, Tor routing policy, transport failure history, and identity-linkage metadata are sensitive metadata even when they are not secret key material. Production persistence for that data is deferred until the app-controlled encrypted local vault exists. The disabled secure metadata boundary rejects all operations and does not use non-secret settings, OS keyrings, BDK persistence, plaintext files, SharedPreferences, or desktop config files as a metadata store.

Rules:

- Metadata may identify that a secret exists.
- Metadata must not contain the secret.
- Credential references may point to secure-storage metadata only.
- Logs must never contain secret payloads.
- Error messages must not embed user-provided secret material.
- UI must never show raw secrets except in deliberate, short-lived, high-friction export/reveal flows.
- `SecretPayload.toString()` must remain redacted.
- Serialization tests must prove settings codecs omit raw credential and secret payload fields.

## Recovery implications

Secure storage does not replace recovery design. It only controls local secret persistence.

Recovery requirements:

- Native app seed material may restore native on-chain keys only if descriptors, derivation policy, and address index state are recoverable.
- Descriptor exports remain important even for seed-backed wallets.
- Imported private keys are not recoverable from the app seed.
- Imported descriptors must be backed up separately if descriptor text is not stored or export is unavailable.
- Watch-only profiles cannot spend and do not prove spend recovery.
- Nostr identity private-key material can create identity and funds risk; storing it must require a separate warning and recovery plan.
- Lightning channel state is not restored by an app seed alone.
- Remote-node funds depend on the remote node backup model and credential recovery.
- Cashu recovery depends on mint support, seed/proof state, and proof synchronization assumptions.
- Backup encryption keys must themselves be recoverable or intentionally user-held.

The app must never claim that one seed restores every rail. Recovery Center must keep rail-specific recovery limitations visible.

## Backup implications

Skald Vault must not introduce Skald-operated cloud backup or automatic server backup.

Future backup design should include:

- Encrypted metadata backups.
- Descriptor exports.
- Watch-only descriptor exports.
- Explicit secret exports where allowed.
- Backup verification steps.
- Local-only mode.
- User-chosen backup locations.
- Clear separation between metadata backup and secret backup.
- No automatic upload to Skald infrastructure.
- No managed backup service dependency.

Backup rules:

- Secret payload backups must be encrypted under user-controlled material.
- Descriptor/watch-only exports must be labeled with spend capability and recovery limitations.
- Imported key backups must be separate and origin-specific.
- Lightning and Cashu backups must not be implied by on-chain backup flows.
- Backup deletion and replacement must show consequences before confirmation.

## Unlock model

Future unlock policies should separate app access from secret access.

Proposed unlock layers:

- App unlock: allows opening the app and viewing non-secret metadata.
- Secret unlock: allows reading specific secret payload classes.
- High-risk action confirmation: required before signing, credential use with spend authority, secret export, or backup-key use.
- Deletion confirmation: required before deleting any active secret metadata or payload.
- Export/reveal confirmation: required before any raw secret leaves the protected boundary.

Policy requirements:

- Optional biometric unlock may be convenience only.
- PIN/passphrase policy must be defined before seed or imported-key storage.
- Inactivity lock must clear unlocked secret session state.
- Failed unlock handling must avoid leaking whether a specific secret exists beyond safe metadata.
- High-risk flows must not use a background unlock silently.

## Authentication and user presence

User presence should be explicit for secret use.

Future implementation should distinguish:

- Opening the app.
- Viewing metadata.
- Unlocking a secret class.
- Using a secret for signing or connector authentication.
- Revealing or exporting a secret.
- Deleting a secret.

User-presence rules:

- Spending and signing flows must require explicit user approval independent of secret unlock.
- Remote-node credentials with payment authority must show permission scope before use.
- Backend credentials may unlock connection testing, but wallet sync must still show backend privacy implications.
- Biometric success should not automatically authorize spend, export, broadcast, or credential privilege escalation.

## Failure modes

Secure storage must fail closed.

Required typed failure modes:

- Storage not implemented.
- Disabled by policy.
- Platform unavailable.
- User setup required.
- Secret locked.
- Authentication failed.
- Permission denied.
- Payload missing.
- Metadata/payload mismatch.
- Decryption failed.
- Record version unsupported.
- Migration failed.
- Partial write detected.
- Backup exclusion unverifiable.
- Device risk warning required.

Failure handling rules:

- Do not create a usable placeholder secret after failure.
- Do not fall back to plaintext.
- Do not downgrade encryption policy automatically.
- Do not enable wallet operations when secure storage is unavailable.
- Do not log payload bytes, raw strings, or rejected secret input.
- Surface enough user-facing reason text to explain what is blocked.

## Migration and versioning

Secret payload records must be versioned from the first real implementation.

Migration rules:

- Version every metadata schema and payload schema.
- Version platform capability reports.
- Support migration dry-run tests.
- Treat unknown newer versions as unavailable, not as empty.
- Preserve old payloads until a migration is verified.
- Use atomic write/replace patterns where practical.
- Test partial write and corrupted vault behavior.
- Keep downgrade behavior explicit; older app versions must not misinterpret newer records.

Migration must not silently discard secret metadata or payload references. If recovery requires user action, the UI and Recovery Center must say so.

## Logging and telemetry rules

Skald Vault should not add analytics or telemetry for secrets.

Rules:

- No secret payloads in logs.
- No mnemonic, seed, key, descriptor private material, credential, Cashu proof, or backup-key material in crash reports.
- No raw backend endpoint credentials in logs.
- No Nostr identity private-key material in logs.
- No Cashu proof material in logs.
- No Lightning credential material in logs.
- No automatic screenshot capture of secret views.
- Use redacted display types.
- Keep `SecretPayload.toString()` redacted.
- Redaction tests must fail if display or exception strings include payload material.

If diagnostics are required, they must log only typed stages, redacted IDs, enum states, and non-secret metadata.

## Testing strategy

Before enabling real storage, tests must cover the common interface and each platform implementation.

Required test groups:

- Common `SecureSecretStorage` interface behavior.
- Disabled/fail-closed behavior remains available as a policy option.
- Android instrumentation or host-side tests for platform storage behavior where feasible.
- Linux encrypted vault tests and optional key-wrapping tests where feasible.
- Filesystem permission tests for local encrypted payload storage.
- Backup exclusion tests.
- Redaction tests for payload display, `toString()`, errors, and logs.
- Serialization tests proving settings codecs omit raw secrets.
- Wrong passphrase/PIN behavior.
- Locked-device or user-authentication behavior where feasible.
- Uninstall/reinstall behavior.
- Corrupted-record tests.
- Partial-write tests.
- Migration tests.
- Concurrent access tests.
- Deletion/wipe behavior tests.
- Recovery Center state updates.
- Recovery Center and Privacy Analyzer status for disabled sync, observation persistence, and encrypted metadata blockers.
- UI tests or scripted verification for reveal/export/deletion warnings.

Testing must use local deterministic harnesses, regtest, signet, testnet/testnet4, or explicit placeholders. Mainnet must never be used for wallet/security-sensitive tests.

## Acceptance criteria before enabling real storage

Real secret storage may not be enabled until all of these gates are satisfied:

- Threat model reviewed.
- Platform dependency choices reviewed.
- Common interface tests pass.
- Android implementation tests pass, if Android storage is enabled.
- Linux implementation tests pass, if desktop storage is enabled.
- No plaintext secret writes.
- No raw secret payload logs.
- Payload display and error messages are redacted.
- Android backup exclusion is verified or payload backups are independently encrypted.
- Linux file permissions, encrypted vault behavior, and optional key-wrapping behavior are verified.
- Deletion behavior is tested.
- Migration behavior is tested.
- Corruption/partial-write behavior is tested.
- Recovery Center is updated.
- UI warnings are implemented.
- README warning is updated.
- BUILD_HISTORY is updated.
- The user explicitly approves enabling real secret-storage implementation work.

Until these gates are met, the disabled/fail-closed implementation remains the only acceptable runtime behavior.

## Open questions

- Should Android use Android Keystore only, or combine Keystore-wrapped keys with a user passphrase-encrypted layer?
- Should biometric unlock be optional convenience or required for specific secret classes?
- Whether the candidate-reviewed Tink plus Bouncy Castle split stack should become the implementation candidate after final KDF parameter approval, disabled-boundary-to-executable-production-provider work, production provider-level KAT contract execution, vault container review, and storage review, as tracked in [`ENCRYPTED_LOCAL_VAULT_DEPENDENCY_REVIEW.md`](ENCRYPTED_LOCAL_VAULT_DEPENDENCY_REVIEW.md), [`ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md), and [`ENCRYPTED_LOCAL_VAULT_TEST_PROVIDER_KAT_HARNESS.md`](ENCRYPTED_LOCAL_VAULT_TEST_PROVIDER_KAT_HARNESS.md). Desktop and Android runtime public KATs now pass for the probe stack, including Pixel 10 Pro XL / Android 16 runtime execution; the test-only provider harness also passes through the Skald-owned interface. The earlier install conflict and stale mDNS targeting failures were environment blockers, not KAT failures. The disabled provider boundary exists but performs no production crypto. Candidate Argon2id parameter tiers exist but still need runtime provider/randomness check review, thermal/load, unlock UX, memory-pressure, and release-mode review. Low-end and mid-range model testing can remain optional parameter evidence, but it is not a hard compatibility blocker. The libsodium comparison rejected Lazysodium Java/Android for this branch due Android duplicate-JNA packaging failure and deferred IonSpin KMP pending isolated package/KAT review.
- Should Android and Linux share the same vault container format and record format?
- Should Linux offer libsecret/KWallet key wrapping at all, given already-unlocked-session risk?
- How should the app present passphrase-only unlock versus optional platform wrapping?
- Should Cashu proof material use a different storage/update model from static credentials?
- How should Lightning channel-state backup interact with secret storage?
- Should Nostr identity private-key material be storable at all, or only imported ephemerally for sweep/recovery flows?
- How should hardware signers be preferred for high-value funds?
- How should encrypted metadata backups be formatted and verified?
- How should secure storage interact with reproducible builds and F-Droid packaging constraints?
- What user-facing recovery phrase format, if any, should Skald Vault support for native on-chain seed material?

## Implementation sequence

1. Finalize and review this design plus [`ENCRYPTED_LOCAL_VAULT_DESIGN.md`](ENCRYPTED_LOCAL_VAULT_DESIGN.md) and [`ENCRYPTED_LOCAL_VAULT_CRYPTO_DECISION.md`](ENCRYPTED_LOCAL_VAULT_CRYPTO_DECISION.md).
2. Use the completed Tink/Bouncy Castle candidate review, disabled provider boundary, provider-level KAT contract, test-only provider KAT harness, Argon2id calibration policy/probes, and candidate parameter policy as input, then complete final KDF parameter approval, executable production-provider design, production provider-boundary KAT execution, vault container review, storage review, and platform wrapping choices through explicit review, or run a narrower replacement-stack review for IonSpin KMP or a Lazysodium/JNA conflict strategy.
3. Expand common secure-storage interface tests for versioning, redaction, deletion, and failure states.
4. Keep code-level vault readiness/policy models disabled and without storage success paths.
5. Implement encrypted vault storage behind a disabled feature flag.
6. Add redaction and no-logging guard tests.
7. Add Android backup exclusion and optional wrapping tests.
8. Add Linux file-permission and optional key-wrapping tests.
9. Add migration, corruption, partial-write, lock/session, and timeout tests.
10. Enable storage only for the smallest explicitly approved persistence class.
11. Later enable seed/imported-key storage only after a separate security review.

No implementation step should enable wallet creation, signing, broadcasting, backend networking, Cashu proof persistence, Lightning credential use, Nostr identity-key storage, or mainnet by implication.
