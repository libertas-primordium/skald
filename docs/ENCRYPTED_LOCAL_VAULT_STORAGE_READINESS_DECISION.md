# Encrypted Local Vault Storage Readiness Decision

This document records the encrypted-vault-storage-readiness-decision-only pass for the next encrypted local vault boundary.

## Scope

- Storage-readiness-decision-only.
- Adds a code-level commonMain policy/admission model only.
- Aggregates existing design, crypto decision, dependency review, dependency KAT, Android runtime KAT, Argon2id calibration probe, disabled provider, test-only provider implementation, provider-level public KAT, test-only provider-selection validation, provider-selection validation completion audit, secure storage, secure metadata, production sync, recovery/privacy, backend observation, production backend adapter, and receive-address policy evidence.
- Admits only a later separate branch for encrypted vault container format design/modeling, disabled repository integration planning, storage path policy, lock/session lifecycle policy, migration/corruption policy, and backup/export policy.
- `encryptedVaultStorageImplementationPathAdmitted=true` is readiness evidence for a later branch only.
- `vaultContainerFormatDecisionAdmitted=true` is not vault container implementation.
- `storagePathPolicyDecisionAdmitted=true` is not storage write authorization.
- `lockSessionLifecycleDecisionAdmitted=true` is not unlock implementation.
- `migrationCorruptionPolicyDecisionAdmitted=true` is not migration execution.
- `backupExportPolicyDecisionAdmitted=true` is not backup/export implementation.
- Future vault container implementation requires a separate branch.
- Future secure secret storage success requires a separate branch.
- Future secure metadata storage success requires a separate branch.
- Future production sync requires a separate branch.
- Future production provider selection requires a separate branch.

## Evidence

- Provider-level public KDF/AEAD KATs passed as test-source-only public-vector evidence.
- Selected-provider public KDF/AEAD KATs passed as test-source-only validation evidence.
- Test-only provider-selection validation completion audit passed as audit evidence.
- Disabled secure secret storage remains fail-closed.
- Disabled secure metadata storage remains fail-closed.
- Production sync remains fail-closed.
- Production provider selection remains `DisabledVaultCryptoProvider` only.
- `productionProviderSelectable=false`.

## Non-Authorization

- No storage implementation is added.
- No vault container parser or writer is added.
- No encrypted vault file format is implemented.
- No encrypted vault repository success path is added.
- No secure secret storage success path is added.
- No secure metadata storage success path is added.
- No production observation persistence is added.
- No production address index persistence is added.
- No production UTXO persistence is added.
- No production wallet history persistence is added.
- No production sync is added.
- No production backend client is added.
- No production provider selection is added.
- No production provider implementation is added.
- No production provider registry, factory, dispatcher, or executor target is added.
- No provider choice persistence is added.
- No provider-selection UI is added.
- No signing or broadcasting is added.
- No UI action enablement is added.
- No endpoints are added.
- Mainnet remains disabled.

## Material Boundary

The decision model contains safe fixed labels, enums, booleans, counts, and safe failure labels only. It does not contain raw bytes, hex strings, KAT vector bytes, public vector bytes, public vector hex, trace payloads, provider-operation payloads, wallet material, descriptors, addresses, txids, PSBT strings, transaction hex, Nostr nsec values, Lightning credentials, Cashu proofs, backend credentials, provider handles, filesystem paths, source locations, stack traces, diagnostics payloads, analytics payloads, crash-report payloads, or support-export payloads.
