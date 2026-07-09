# Encrypted Local Vault Storage Path And Session Lifecycle Decision

This document records the encrypted-vault-storage-path-session-lifecycle-decision-only pass.

## Scope

- Storage-path-session-lifecycle-decision-only.
- Adds a commonMain policy/model for future storage path and lock/session lifecycle decisions.
- Admits later separate branches for storage path implementation, lock/session implementation, vault parser/writer implementation, repository success, secure secret storage success, secure metadata success, production sync, and production provider selection.
- `vaultStoragePathSessionLifecycleDecisionAdmitted=true` is decision evidence only and authorizes only later separate branches.
- `androidAppPrivateStoragePolicyAdmitted=true` is not Android storage implementation.
- `linuxUserDataStoragePolicyAdmitted=true` is not Linux storage implementation.
- `appControlledVaultDirectoryPolicyAdmitted=true` is not directory creation.
- `explicitUnlockRequiredPolicyAdmitted=true` is not unlock implementation.
- `sessionKeyMemoryHandlingPolicyAdmitted=true` is not runtime session-key handling.
- `platformWrappingPolicyReferenceAdmitted=true` is not Android Keystore or Linux keyring implementation.

## Storage Path Policy

- Future Android vault storage must use app-private storage only.
- Future Linux vault storage must use user-local app data storage only.
- Future storage paths must be platform-resolved by reviewed platform code, not hardcoded path literals in common code.
- Future storage paths must not be logged.
- Future storage paths must not be included in support-export payloads.
- Future storage paths must not appear in `toString`, display, or debug output.
- Future encrypted vault data must not be stored in non-secret settings storage.
- Future encrypted vault data must not be stored in Android SharedPreferences.
- Future encrypted vault data must not be stored in desktop plain config files.
- Future backup/export storage destinations must be explicit and separate from the local vault path.
- No directory creation, file read, file write, or file delete occurs in this branch.

## Lock And Session Lifecycle Policy

- Future unlock must be explicit.
- Future runtime session key material must be memory-only and never serialized.
- Future session lock must occur on explicit user lock.
- Future session lock must occur on process death or restart.
- Future Android behavior must lock or require revalidation after app backgrounding according to later platform lifecycle review.
- Future Linux desktop behavior must lock on explicit user action and reviewed inactivity timeout.
- Future lock/session lifecycle implementation requires a separate branch.
- This branch does not hold runtime keys.
- This branch does not implement unlock.
- This branch does not implement lock.
- This branch does not create a session object.
- This branch does not cache secrets.

## Platform Wrapping Policy

- Android Keystore wrapping remains optional future wrapping, not primary vault storage.
- Linux OS keyring wrapping remains optional and deferred, not primary storage.
- Platform wrapping does not replace app-controlled encrypted vault storage.
- No platform wrapping is implemented in this branch.

## Non-Authorization

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

- Future storage path implementation requires a separate branch.
- Future lock/session implementation requires a separate branch.
- Future vault parser requires a separate branch.
- Future vault writer requires a separate branch.
- Future encrypted repository success requires a separate branch.
- Future secure secret storage success requires a separate branch.
- Future secure metadata storage success requires a separate branch.
- Future production sync requires a separate branch.
- Future production provider selection requires a separate branch.

## Material Boundary

The decision model contains safe fixed labels, enums, booleans, counts, and safe failure labels only. It does not contain raw bytes, hex strings, KAT vector bytes, KAT vector hex, public vector bytes, public vector hex, salts, nonces, ciphertext, tags, MACs, hashes, key material, Tink keysets, file paths, directory paths, filesystem paths, SharedPreferences keys, settings storage keys, database names, trace payloads, provider-operation payloads, wallet material, descriptors, addresses, txids, PSBT strings, transaction hex, Nostr nsec values, Lightning credentials, Cashu proofs, backend credentials, provider handles, source locations, stack traces, diagnostics payloads, analytics payloads, crash-report payloads, or support-export payloads.
