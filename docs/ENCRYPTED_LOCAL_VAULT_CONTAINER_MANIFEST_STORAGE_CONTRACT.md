# Encrypted Local Vault Container, Manifest, And Storage Contract

## Status

This document defines the Skald Vault v1 vault container, manifest, storage, stale-record, atomicity, crash-recovery, and secure-storage boundary contract before persistence implementation.

This is contract evidence only. It does not implement provider selectability, vault creation, vault unlock, a vault parser, a vault writer, manifest read/write, storage index read/write, filesystem storage, database storage, DataStore or SharedPreferences storage, secure secret storage success, secure metadata storage success, migration, re-encryption, sync, wallet behavior, backend behavior, signing, broadcasting, Tor, Nostr, or mainnet.

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

The future persisted Skald Vault v1 container is a logical structure. Its exact parser/writer remains future work, but the v1 contract requires the container to carry or reference enough non-secret pre-unlock information for deterministic header reconstruction and fail-closed validation.

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
- A future parser must reject malformed, duplicated, unknown-in-non-extensible-section, unsupported, or non-canonical header evidence.
- A future parser must return typed failures, not uncontrolled exceptions, for expected malformed or untrusted input.
- This branch does not implement a parser, writer, container file format, filesystem path, database path, or persistence API.

## Manifest Contract

The future manifest is the authority for latest trusted local record state. AAD binds record version/counter into each record encryption operation, but the manifest is what will decide whether a record is fresh relative to the latest trusted local state.

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

The future manifest must be integrity-protected and bound to:

- vault id;
- provider suite id;
- header commitment context;
- manifest policy id/version;
- storage namespace;
- record namespace;
- latest record counters.

This branch does not implement manifest read/write, a manifest file, a storage index, record locations, tombstones, or conflict handling code.

## Stale-Record And Rollback Boundary

Strict AAD binding prevents undetected cross-vault, cross-provider, cross-record, cross-type, and cross-version ciphertext substitution. It does not by itself prove freshness.

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

- documented/model-only container contract;
- documented/model-only manifest contract;
- documented/model-only storage policy;
- documented/model-only stale-record policy;
- documented/model-only atomicity/crash-recovery contract;
- documented/model-only secure-storage boundary;
- absent anti-rollback anchor and no full rollback-resistance claim;
- absent parser/writer/storage/manifest implementation;
- disabled production persistence.

Missing, unknown, failed, unsupported, documented-only, or unimplemented container, manifest, stale-record, atomicity, or secure-storage evidence must block provider selectability and persistence.

Completed provider-level KATs, completed still-disabled building-block tests, and the metadata-only provider facade do not make the provider selectable and do not make storage persistence-ready.

## Remaining Gates

Before provider selectability:

- final Argon2id parameter approval;
- supported-platform runtime provider/randomness review;
- selectable production provider implementation;
- provider-level KAT execution through the selectable provider boundary;
- lock/session lifecycle and redaction/leakage testing;
- release and human review.

Before vault persistence:

- concrete v1 container parser/writer implementation and tests;
- manifest read/write implementation and tests;
- manifest-backed stale-record enforcement;
- storage atomicity and crash-recovery implementation;
- interruption/corruption tests;
- secure secret storage and secure metadata storage boundary implementation;
- explicit review of rollback limitations and any anti-rollback anchor decision.
