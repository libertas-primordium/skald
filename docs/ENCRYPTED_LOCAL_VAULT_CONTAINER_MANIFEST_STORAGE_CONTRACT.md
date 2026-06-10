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

This remains contract and still-disabled building-block evidence only. It does not implement provider selectability, vault creation, vault unlock, vault persistence, manifest file/storage read/write, storage index read/write, filesystem storage, database storage, DataStore or SharedPreferences storage, secure secret storage success, secure metadata storage success, migration, re-encryption, sync, import/export, wallet behavior, backend behavior, signing, broadcasting, Tor, Nostr, or mainnet.

Related evidence is modeled in:

- `ProductionProviderAcceptanceContract`
- `EncryptedVaultReadiness`
- `VaultCryptoDependencyProbe`

Required policy ids:

- Vault container policy id: `skald-vault-v1-container-contract-v1`
- Manifest policy id: `skald-vault-v1-manifest-contract-v1`
- Storage policy id: `skald-vault-v1-local-manifest-storage-policy-v1`
- Stale-record policy id: `skald-vault-v1-stale-record-manifest-policy-v1`
- Atomicity/crash-recovery policy id: `skald-vault-v1-atomicity-crash-recovery-policy-v1`
- Secure-storage boundary policy id: `skald-vault-v1-secure-storage-boundary-policy-v1`
- Anti-rollback anchor policy id: `skald-vault-v1-anti-rollback-anchor-policy-v1`

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

Required future behavior:

- Writes must be atomic at the vault-container/manifest consistency boundary.
- Partial writes must not be treated as valid vault state.
- Manifest update and record write must either both become durable, or recovery must choose a safe previous state.
- Crash during write must not silently accept a newer record without manifest authority.
- Crash during manifest update must not silently orphan or resurrect records.
- Startup recovery must validate manifest/header/record consistency before unlock success.
- Future implementation must define a temp-file, journal, rename, fsync, or equivalent platform strategy before persistence is approved.
- Future implementation must test interruption at each write phase.

This branch does not implement an atomic write strategy, journal, recovery routine, temp-file workflow, rename/fsync sequence, or interruption tests.

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
- documented/model-only storage policy;
- documented/model-only atomicity/crash-recovery contract;
- documented/model-only secure-storage boundary;
- absent anti-rollback anchor and no full rollback-resistance claim;
- absent storage, manifest file/storage read/write, storage index, atomic write/recovery, and secure-storage implementation;
- disabled production persistence.

Missing, unknown, failed, unsupported, documented-only, or unimplemented container, manifest, stale-record, atomicity, or secure-storage evidence must block provider selectability and persistence.

Completed in-memory container parser/writer tests, completed in-memory manifest parser/writer tests, completed local stale-record decision tests, completed provider-level KATs, completed still-disabled crypto building-block tests, and the metadata-only provider facade do not make the provider selectable and do not make storage persistence-ready.

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
- storage atomicity and crash-recovery implementation;
- interruption/corruption tests;
- secure secret storage and secure metadata storage boundary implementation;
- explicit review of rollback limitations and any anti-rollback anchor decision.
