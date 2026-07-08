# Encrypted Local Vault Container Format V1 Decision

This document records the encrypted-vault-container-format-v1-decision-only pass.

## Scope

- Container-format-v1-decision-only.
- Adds a commonMain policy/model for the v1 container format decision.
- Admits later separate branches for v1 header, KDF section, key-envelope section, record-directory section, record-envelope section, associated-data policy, nonce policy, record-class policy, migration/corruption policy, and backup/export separation policy.
- `vaultContainerFormatV1DecisionAdmitted=true` is format-decision evidence only and authorizes only later separate branches.
- `vaultContainerHeaderModelAdmitted=true` is not header serialization.
- `vaultKdfSectionModelAdmitted=true` is not KDF execution.
- `vaultKeyEnvelopeSectionModelAdmitted=true` is not key wrapping or unwrapping.
- `vaultRecordDirectoryModelAdmitted=true` is not record persistence.
- `vaultRecordEnvelopeModelAdmitted=true` is not encryption or decryption.
- `vaultAssociatedDataPolicyAdmitted=true` is not runtime associated-data construction.
- `vaultNoncePolicyAdmitted=true` is not nonce generation.
- `vaultMigrationCorruptionPolicyAdmitted=true` is not migration execution.
- `vaultBackupExportSeparationPolicyAdmitted=true` is not backup/export implementation.

## Conceptual Sections

The v1 decision models these future sections as safe labels only:

- `skald-encrypted-local-vault-container-v1`,
- `skald-vault-v1-header`,
- `skald-vault-v1-kdf-section`,
- `skald-vault-v1-key-envelope-section`,
- `skald-vault-v1-record-directory-section`,
- `skald-vault-v1-record-envelope-section`,
- `skald-vault-v1-integrity-metadata-section`.

These labels are not file bytes, magic bytes, serialized bytes, storage paths, headers written to disk, or parser targets in this pass.

## Future Policies

- Future associated data may contain only non-secret structural context: format version label, record purpose label, algorithm suite label, migration version label, and canonical record class label.
- Future associated data must not contain wallet names, labels, transaction notes, endpoint values, descriptors, addresses, txids, PSBTs, Nostr identifiers or secret keys, Lightning credentials, Cashu proofs, backend credentials, filesystem paths, source locations, or stack traces.
- Future XChaCha record nonce policy requires a random 24-byte nonce per record.
- No nonce generation occurs in this branch.
- No caller-provided production nonce support is added in this branch.
- Existing explicit-nonce KAT support remains test/probe-only.
- Future record classes are safe enum labels only and contain no payload values.

## Non-Authorization

- No storage implementation is added.
- No vault container parser or writer is added by this pass.
- No vault file read or write path is added.
- No serialization or parsing is added by this pass.
- No KDF execution is added.
- No AEAD execution is added.
- No key generation is added.
- No nonce generation is added.
- No Tink keyset creation or persistence is added.
- No secure secret storage success path is added.
- No secure metadata storage success path is added.
- No production persistence is added.
- No production sync is added.
- No production provider selection is added.
- No production provider implementation is added.
- No production backend clients are added.
- No signing or broadcasting is added.
- No UI action enablement is added.
- No endpoints are added.
- Mainnet remains disabled.

## Future Gates

- Future vault parser requires a separate branch.
- Future vault writer requires a separate branch.
- Future encrypted repository success requires a separate branch.
- Future secure secret storage success requires a separate branch.
- Future secure metadata storage success requires a separate branch.
- Future production sync requires a separate branch.
- Future production provider selection requires a separate branch.

## Material Boundary

The decision model contains safe fixed labels, enums, booleans, counts, and safe failure labels only. It does not contain raw bytes, hex strings, KAT vector bytes, KAT vector hex, public vector bytes, public vector hex, salts, nonces, ciphertext, tags, MACs, hashes, key material, Tink keysets, trace payloads, provider-operation payloads, wallet material, descriptors, addresses, txids, PSBT strings, transaction hex, Nostr nsec values, Lightning credentials, Cashu proofs, backend credentials, provider handles, filesystem paths, source locations, stack traces, diagnostics payloads, analytics payloads, crash-report payloads, or support-export payloads.
