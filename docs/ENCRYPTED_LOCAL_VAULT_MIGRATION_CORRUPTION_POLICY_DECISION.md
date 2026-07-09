# Encrypted Local Vault Migration And Corruption Policy Decision

This document records the encrypted-vault-migration-corruption-policy-decision-only pass.

## Scope

- Migration-corruption-policy-decision-only.
- Adds a commonMain policy/model for future encrypted local vault migration and corruption handling decisions.
- Admits later separate branches for migration implementation, corruption detection implementation, vault parser/writer implementation, repository success, secure secret storage success, secure metadata success, production sync, and production provider selection.
- `vaultMigrationCorruptionPolicyDecisionAdmitted=true` is decision evidence only and authorizes only later separate branches.
- `authenticateBeforeMigrationPolicyAdmitted=true` is not authenticated parser implementation.
- `migrationDryRunPolicyAdmitted=true` is not migration execution.
- `migrationBackupPreconditionPolicyAdmitted=true` is not backup/export implementation.
- `migrationRollbackPolicyAdmitted=true` is not rollback implementation.
- `failClosedCorruptionPolicyAdmitted=true` is not corruption detection implementation.
- `noDestructiveRepairDefaultPolicyAdmitted=true` is not repair implementation.
- `redactedCorruptionDiagnosticsPolicyAdmitted=true` is not diagnostics implementation.
- `atomicReplacePolicyReferenceAdmitted=true` is not file write implementation.
- `partialWriteDetectionPolicyAdmitted=true` is not partial-write detection implementation.

## Migration Policy

- Future migration must authenticate the source vault before reading or transforming records.
- Future migration must support a dry-run or planning mode before writing migrated state.
- Future migration must require a backup/export or equivalent user-confirmed recovery precondition before destructive migration.
- Future migration must not silently drop unknown critical record classes.
- Future migration must fail closed on unknown unsupported format versions.
- Future migration must preserve record-class separation and key-separation policy.
- Future migration must not log record payloads, record IDs, addresses, descriptors, txids, labels, notes, filesystem paths, stack traces, or crypto material.
- No migration runs in this branch.

## Corruption Policy

- Future corruption detection must authenticate before trusting container metadata.
- Future corruption handling must fail closed.
- Future destructive repair must be opt-in and separate from default open/read behavior.
- Future diagnostics must be redacted and safe-label-only.
- Future partial-write detection must not expose raw bytes, hashes, MACs, tags, filenames, or paths in display/debug output.
- No corruption detection or repair runs in this branch.

## Atomic Write Policy

- Future writer should use a reviewed atomic replace strategy appropriate to each platform.
- Future writer must not expose temp filenames or storage paths in logs or support output.
- Future writer must distinguish write failure, authentication failure, parse failure, migration-required, and unsupported-version blockers.
- No writer or atomic replace is implemented in this branch.

## Non-Authorization

- No migration implementation is added.
- No migration execution is added.
- No corruption detection is added.
- No corruption repair is added.
- No backup creation is added.
- No rollback is added.
- No atomic replace implementation is added.
- No partial-write detection implementation is added.
- No storage implementation is added.
- No directory creation is added.
- No file read, write, or delete behavior is added.
- No vault container parser or writer is added.
- No serialization or parsing is added.
- No KDF execution is added.
- No AEAD execution is added.
- No key generation is added.
- No nonce generation is added.
- No Tink keyset creation or persistence is added.
- No unlock implementation is added.
- No lock implementation is added.
- No runtime session-key handling is added.
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

- Future migration implementation requires a separate branch.
- Future corruption detection implementation requires a separate branch.
- Future vault parser requires a separate branch.
- Future vault writer requires a separate branch.
- Future encrypted repository success requires a separate branch.
- Future secure secret storage success requires a separate branch.
- Future secure metadata storage success requires a separate branch.
- Future production sync requires a separate branch.
- Future production provider selection requires a separate branch.

## Material Boundary

The decision model contains safe fixed labels, enums, booleans, counts, and safe failure labels only. It does not contain raw bytes, hex strings, KAT vector bytes, KAT vector hex, public vector bytes, public vector hex, salts, nonces, ciphertext, tags, MACs, hashes, key material, Tink keysets, file paths, directory paths, filesystem paths, SharedPreferences keys, settings storage keys, database names, trace payloads, provider-operation payloads, wallet material, descriptors, addresses, txids, PSBT strings, transaction hex, Nostr nsec values, Lightning credentials, Cashu proofs, backend credentials, provider handles, source locations, stack traces, diagnostics payloads, analytics payloads, crash-report payloads, or support-export payloads.
