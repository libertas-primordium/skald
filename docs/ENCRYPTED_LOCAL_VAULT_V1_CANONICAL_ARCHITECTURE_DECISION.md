# Encrypted Local Vault V1 Canonical Architecture Decision

## Status

`CANONICAL_ARCHITECTURE_SELECTED_IMPLEMENTATION_BLOCKED`

- Human approval date: 2026-07-12.
- Scope: `encrypted-vault-v1-canonical-architecture-decision-only`.
- Decision identifier: `skald-encrypted-local-vault-v1-canonical-architecture-decision`.
- Decision version: 1.
- Canonical persisted format version: 1.
- The reconciliation audit has been reviewed and its requested architecture decision has been made.
- `canonicalArchitectureDecisionPassed=true` means only that this approved package is recorded consistently.
- `decisionBlockerCount=0` is permitted because no architecture choice remains open in this package.
- `implementationBlockerCount` remains non-zero and `productionAuthorizationPresent=false`.

This decision does not mean that exact canonical bytes, a production provider, a parser, a writer, a codec, file I/O, storage, persistence, production provider selection, or mainnet exists or is authorized. The architecture is selected; implementation remains blocked behind separately authorized passes.

## Canonical Ownership And Versioning

The first supported persisted Skald encrypted local vault format remains canonical format version 1. Existing earlier `SkaldVaultV1*` persisted-byte formats are pre-release prototype P0 material with the disposition `PRE_RELEASE_PROTOTYPE_P0_UNSUPPORTED`.

That disposition applies to `SkaldVaultV1ContainerFormat`, `SkaldVaultV1ManifestFormat`, `SkaldVaultV1HeaderCommitment`, `SkaldVaultV1RecordAead`, their supporting canonical-header models and encoders, fixtures, KAT adapters, storage-contract fixtures, atomicity simulators, crash-recovery simulators, and their direct crypto graph.

Prototype P0 bytes are not canonical v1 persisted bytes. No compatibility or migration promise exists for them, and production integration of them is not allowed. Developer data made with prototype P0 bytes must be recreated. This branch does not delete, move, rename, annotate as deprecated, or semantically modify any prototype artifact. Prototype quarantine, deprecation, production-reachability restriction, and fixture relocation each require a separate pass.

`EncryptedVaultWorkingParser` has the disposition `SYNTHETIC_TEST_CONTRACT_CLASSIFIER_ONLY`. It remains a production-compiled commonMain synthetic marker classifier with a test-source catalog and no production call site. It is not the canonical vault parser and is not approved for production vault bytes or real-wallet data. This branch does not modify or remove it; future relocation or removal requires a separate pass.

Neither existing implementation line is the canonical storage implementation.

## Live-Vault Topology

The canonical topology is `MULTI_ARTIFACT_APP_CONTROLLED_VAULT_DIRECTORY`.

The live-vault directory has these semantic roles:

1. One minimal public header and wrapped-root envelope.
2. One authoritative encrypted manifest.
3. Independently encrypted opaque record artifacts.
4. Temporary staging artifacts.
5. Quarantine and recovery artifacts.

Backup/export is a sixth, separate role with a separate format and key hierarchy. It is not a live-vault artifact and does not reuse live record roots or keys.

The commonMain decision model uses only these safe policy labels for those roles:

| Role | Safe policy label |
|---|---|
| Public header | `skald-vault-v1-public-header-role` |
| Authoritative encrypted manifest | `skald-vault-v1-authoritative-encrypted-manifest-role` |
| Encrypted record | `skald-vault-v1-encrypted-record-role` |
| Temporary staging | `skald-vault-v1-temporary-staging-role` |
| Quarantine/recovery | `skald-vault-v1-quarantine-recovery-role` |
| Separate backup/export | `skald-vault-v1-separate-backup-export-role` |

These labels are not filenames, directory names, storage paths, magic values, field bytes, serialized headers, parser inputs, writer outputs, key identifiers, wallet identifiers, or real artifact names.

The authoritative manifest is semantic authority. Filenames and storage-directory metadata are not semantic authority. No concrete filename, directory name, storage path, directory creation, file read, file write, or file delete behavior is defined or implemented by this decision.

## Public Header And Wrapped-Root Authentication

The future public header is limited to the structural information necessary to identify and unlock the vault:

- future fixed magic;
- canonical format version;
- provider-suite identifier and version;
- KDF identifier and version;
- Argon2id memory, pass, and lane parameters;
- KDF salt;
- stable random vault identifier;
- key-envelope version;
- wrapped-root nonce/ciphertext/tag envelope representation.

It must not contain wallet names, labels, record identifiers, record classes, record references, tombstones, history, backend information, descriptors, credentials, recovery metadata, privacy metadata, or transaction metadata.

The header authentication policy is `WRAPPED_ROOT_AEAD_ASSOCIATED_DATA_NO_SEPARATE_HEADER_HMAC`:

- no separate persisted header HMAC or header-commitment tag is selected;
- the wrapped-root XChaCha20-Poly1305 operation authenticates the canonical public-header context as associated data;
- that associated data must be canonical and non-circular;
- the envelope ciphertext and authentication tag are excluded from their own associated data;
- a wrong passphrase and damaged wrapped-root authentication have the same generic unlock failure at the public boundary.

The public-header size limit is 65,536 bytes. Exact magic, numeric field identifiers, field framing, header-associated-data byte encoding, serialization, parsing, and authentication execution remain absent and require later decisions or implementation passes.

## Canonical Key Hierarchy

The selected hierarchy is `ARGON2ID_KEK_WRAPPED_RANDOM_VAULT_ROOT_HKDF_SEPARATED_ROOTS_PER_RECORD_KEYS`:

1. A future approved passphrase normalization and UTF-8 process produces bounded KDF input.
2. Argon2id derives an exactly 32-byte key-encryption key.
3. That key-encryption key unwraps a random exactly 32-byte vault root key with XChaCha20-Poly1305.
4. HKDF-SHA-256 domain-separates the vault root into three live derived roots: manifest, metadata-record, and secret-record.
5. A distinct per-record key is derived from the appropriate record root with context that includes record class, record identifier, schema version, and record version.

The vault root key is never used directly as an AEAD key. One shared record-AEAD key for all records is prohibited. Passphrase rotation rewraps the vault root key and does not require record reencryption. Backup/export has an independent key hierarchy and does not reuse live record roots.

The target algorithms are Argon2id for passphrase KDF, XChaCha20-Poly1305 for wrapped-root, manifest, and record AEAD, and HKDF-SHA-256 for domain separation. Every record encryption requires a fresh random 24-byte XChaCha20-Poly1305 nonce.

Optional platform wrapping may be reviewed later. Android platform wrapping is not selected or implemented here. Linux v1 is passphrase-first, and an OS keyring is not the primary vault store.

No key generation, wrapping, unwrapping, KDF, HKDF, AEAD, nonce generation, runtime key handle, runtime vault-root key, or runtime session key is implemented by this decision.

## Authoritative Manifest

The selected policy is `SINGLE_AUTHORITATIVE_ENCRYPTED_AUTHENTICATED_MANIFEST`.

Exactly one manifest is authoritative. It must be encrypted and authenticated. A plaintext standalone manifest and a duplicated embedded manifest are prohibited. The future manifest contains:

- vault-identifier confirmation;
- manifest schema version;
- generation counter;
- record directory;
- record identifiers and classes;
- record schema versions and latest record-version counters;
- opaque storage references;
- tombstone state;
- recovery/commit metadata;
- previous-manifest commitment or chain evidence.

The manifest contains sensitive wallet metadata and therefore cannot be plaintext. Filenames cannot replace it as authority. Its maximum encrypted size is 16,777,216 bytes and its maximum record-entry count is 65,536.

These are architecture requirements, not runtime claims. No manifest serialization, parsing, encryption, authentication, or persistence exists in the canonical implementation.

## Record Artifacts

The selected record policy is `PER_RECORD_KEY_FRESH_XCHACHA_NONCE_MINIMAL_PUBLIC_ENVELOPE`.

- individual record plaintext is limited to 4,194,304 bytes;
- ciphertext payload is limited to 4,194,304 bytes before bounded envelope overhead;
- an envelope-overhead cap is required, but its exact value remains a later binary-layout decision;
- every encryption uses a distinct per-record key and fresh random 24-byte nonce;
- shared record keys are prohibited;
- record metadata, identifier, class, version, labels, and references remain encrypted;
- public record framing is minimal;
- filenames carry no semantic record identity.

Record associated data binds the vault identifier, record identifier, record class, record schema version, record version, and format/provider suite. It excludes sensitive labels and notes, descriptors, addresses, and transaction identifiers.

No record serialization, parsing, encryption, decryption, or nonce generation is implemented here.

## Mandatory Provider Routing

The selected routing policy is `MANDATORY_SELECTED_SKALD_VAULT_CRYPTO_PROVIDER`.

All canonical production cryptography must route through the selected Skald-owned `VaultCryptoProvider`. A selected provider is required for vault create, open, encrypt, and decrypt operations. If selection resolves to `DisabledVaultCryptoProvider`, all of those operations remain unavailable.

Canonical implementation code may not directly call Bouncy Castle Argon2, direct HKDF/HMAC helpers, Tink primitives, JCA/JCE crypto, platform randomness APIs, or the direct `SkaldVaultV1*` prototype crypto graph. Opaque provider key handles are required. Provider-level KAT validation and production-provider acceptance remain required gates.

Existing direct prototype crypto remains present in the repository, is noncanonical, and is unchanged by this branch. It does not become an approved provider path.

No production provider implementation is present. Production provider selection remains disabled, `productionProviderSelectable=false`, and selection remains `DisabledVaultCryptoProvider` only. No provider crypto execution is added.

## Strict V1 Encoding

The selected encoding policy is `FIXED_BIG_ENDIAN_STRICT_ORDERED_FIELDS_NO_UNKNOWN_NO_DUPLICATES_NO_TRAILING_NON_RECURSIVE`:

- fixed big-endian integer encoding;
- fixed field identifiers;
- explicit lengths;
- fields in strictly increasing numeric-identifier order;
- duplicate fields rejected;
- unknown fields rejected;
- unknown feature bits rejected;
- unknown non-critical fields are not accepted in v1;
- trailing bytes rejected;
- checked integer arithmetic before allocation;
- fixed, non-recursive structural nesting;
- UTF-8 strings with an explicit byte limit.

Canonical v1 is deliberately strict and non-extensible. A structural change requires a future format-version increment rather than permissive unknown-field handling.

Exact magic, numeric field identifiers, field framing, header-associated-data byte construction, passphrase normalization, and record-envelope representation remain undefined. No canonical codec is implemented.

## Hard Resource Limits

| Resource | Canonical limit |
|---|---:|
| Public header | 65,536 bytes |
| Encrypted manifest | 16,777,216 bytes |
| Manifest record entries | 65,536 |
| Individual record plaintext | 4,194,304 bytes |
| Individual record ciphertext payload | 4,194,304 bytes before bounded envelope overhead |
| Policy or identifier string | 4,096 UTF-8 bytes |
| Passphrase after future normalization/UTF-8 encoding | 1,024 bytes |
| Argon2id salt | 16–64 bytes |
| Argon2id memory | 8,192–262,144 KiB |
| Argon2id passes | 1–10 |
| Argon2id lanes | 1–4 |
| Derived key-encryption key | exactly 32 bytes |
| Vault root key | exactly 32 bytes |
| XChaCha nonce | exactly 24 bytes |
| Structural nesting | fixed and non-recursive |

The exact record-envelope overhead cap and final per-device Argon2id parameters remain separate decisions. This decision does not replace the existing calibration policy.

## Atomic Commit And Rollback Boundary

The approved live-vault commit order is:

1. Write new or replacement record artifacts to temporary locations.
2. Flush record contents.
3. Atomically install record artifacts.
4. Write the new encrypted manifest to a temporary location.
5. Flush the manifest contents.
6. Atomically replace the authoritative manifest.
7. Flush the containing directory where supported.
8. Quarantine or garbage-collect unreferenced artifacts later.

Records are installed before manifest commit, and authoritative-manifest replacement is the final commit point. Temporary artifacts are required. Quarantine is a future implementation requirement; destructive repair is not allowed by default.

The manifest contains a generation counter and previous-manifest commitment/chain evidence. Local inconsistent-state detection is required. Skald must not claim complete rollback prevention: a fully consistent old vault snapshot cannot be detected without a trusted external monotonic anchor. No such anchor is present, and the app does not claim guaranteed whole-snapshot rollback prevention.

No atomic replace, directory flush, quarantine, rollback detection, or recovery implementation is added by this decision.

## Ownership, Clearing, And Redaction

The selected policy is `OWNED_COPY_OPAQUE_REDACTED_NO_SENSITIVE_DATA_CLASS`.

Sensitive byte-bearing production types must:

- be ordinary classes, not data classes;
- copy constructor input arrays;
- never expose mutable-array references;
- avoid generated sensitive-array `copy()` methods;
- avoid default array equality/hash semantics;
- use explicit redacted `toString()` output;
- expose opaque key handles rather than raw keys;
- support best-effort explicit clearing on close or lock where applicable;
- avoid global secret caching and memoization;
- keep passphrase input and raw key material out of parser/codec results.

These are requirements for future canonical production types. This branch adds no sensitive byte-bearing type, key handle, raw-key getter, or clearing implementation.

## Fixture Confinement

The selected policy is `TEST_SOURCE_ONLY_FIXED_FIXTURES`.

Fixed KAT keys, KAT plaintext, KAT ciphertext, salts, vault identifiers, record identifiers, prototype headers, prototype manifests, record references, and synthetic parser markers are not allowed in production source. Production source may retain safe protocol constants such as format/version/algorithm/policy identifiers, later-approved field identifiers, safe limits, and enum labels.

Existing prototype fixtures remain where they are because this branch does not move or remove them. Their future relocation requires a separate pass. The policy selection does not retroactively make those existing production-compiled fixtures canonical.

## Backup/Export And Platform Wrapping

Backup/export is a separate format with an independent hierarchy. It is not another live-vault artifact, does not reuse live record roots, and requires a separate design and implementation pass.

Platform wrapping is optional and requires later review. Android platform wrapping may be reviewed later. Linux v1 remains passphrase-first; Linux OS keyrings are not primary storage.

## Explicitly Deferred Layout Decisions

The following exact details are intentionally not selected here:

- magic bytes;
- numeric field identifiers;
- exact byte framing;
- exact canonical header associated-data byte construction;
- exact passphrase normalization and UTF-8 process;
- exact record-envelope representation and overhead cap;
- final per-device Argon2id parameter choice.

They require separately authorized decision passes. No value may be inferred from prototype P0 bytes.

## Explicit Non-Implementation And Non-Authorization

This decision adds no canonical binary layout, header codec, key envelope, manifest codec, record envelope, parser, writer, serializer, deserializer, canonical vector execution, vault directory, storage path, directory creation, file read/write/delete, atomic storage, repository, storage adapter, key generation, nonce generation, KDF, HKDF, HMAC, AEAD, encryption, decryption, authentication, provider implementation, provider selection, lock/session, unlock, runtime vault-root key, runtime session key, secure secret storage success, secure metadata storage success, production observation/address-index/UTXO/history persistence, production sync, production backend client, signing, broadcasting, UI action, endpoint, or mainnet behavior.

Architecture-selection booleans are policy evidence only. They do not authorize or assert runtime implementation.

## Required Future Branch Sequence

1. Prototype quarantine and production-reachability guards.
2. Prototype fixture relocation/removal decision.
3. Exact canonical binary-layout decision.
4. Provider API alignment decision.
5. Production provider implementation and provider-level KAT validation.
6. Canonical in-memory codec implementation.
7. Canonical codec vectors on desktop and Android.
8. Lock/session implementation.
9. Storage-path and atomicity implementation.
10. Secure storage/metadata repository integration.
11. Production persistence.
12. Production sync.
13. Release hardening.
14. Explicit mainnet review.

Every item is a separate authorization boundary. Prototype deprecation, synthetic-parser relocation/removal, exact magic/field IDs/framing, header AAD encoding, passphrase normalization, record-overhead limit, provider API revision, provider implementation/selection, canonical codec/vector execution, storage, lock/session, backup/export, platform wrapping, secure-storage/metadata success, persistence, sync, signing/broadcasting, UI, endpoint, and mainnet review remain implementation blockers after this decision.

## Relationship To Historical Records

The [`v1 format/parser/crypto reconciliation audit`](ENCRYPTED_LOCAL_VAULT_V1_FORMAT_PARSER_CRYPTO_RECONCILIATION_AUDIT.md) retains its historical outcome `INVENTORY_COMPLETE_DESIGN_DECISION_REQUIRED`; that audit inventoried the overlap and requested human review but did not select this architecture. This document records the later human resolution.

Earlier prototype, admission, scaffold, synthetic-parser, validation, and correction documents retain their pass-specific history. Their older statements are not rewritten into global repository claims. Current status is governed by this decision: prototype P0 is unsupported, the working parser is synthetic-test-contract-only, and neither line is the canonical storage implementation.
