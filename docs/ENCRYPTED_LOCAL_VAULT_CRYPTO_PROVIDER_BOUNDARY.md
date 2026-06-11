# Encrypted Local Vault Crypto Provider Boundary

## Status

Skald Vault now has a narrow Skald-owned disabled `VaultCryptoProvider` boundary for the future app-controlled encrypted local vault.

This is a provider-boundary and policy-model pass. The passphrase policy validator, explicit-parameter Argon2id root derivation, Argon2id calibration policy/candidate-selection/memory-failure/no-downgrade model, canonical header serializer, HKDF-SHA-256 expansion, HMAC-SHA-256 header commitment verification, strict AAD serialization, Tink XChaCha20-Poly1305 record AEAD construction from caller-supplied 32-byte key material, in-memory vault container parser/writer, in-memory manifest parser/writer, local manifest-relative stale-record decision policy, metadata-only still-disabled provider facade, redaction/leakage boundary, passphrase policy boundary, clear/wipe strategy boundary, migration/corruption boundary, and model-only storage boundary/root/path/symlink/permission/durability/atomicity/secure-storage contracts now exist as still-disabled building blocks/evidence. The provider boundary still does not implement selectable provider crypto, final production calibration approval, provider-wired Argon2id passphrase KDF execution, provider-wired AEAD execution, real migration, migration dry-run, storage repair, record quarantine, record recovery, real storage parsing, real rollback protection, real crash recovery, passphrase input acceptance, passphrase normalization or encoding execution, passphrase hashing, passphrase fingerprinting, retry/throttle/lockout runtime behavior, key generation, Tink keyset creation or storage, raw key material storage, actual path construction, platform root selection, file-backed vault container read/write, manifest file/storage read/write, storage success, secure secret storage, secure metadata persistence, production sync, backend clients, signing, broadcasting, Tor transport, Nostr parsing, public endpoints, Skald-operated infrastructure, or mainnet.

Provider selection is documented separately in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md). Runtime randomness/provider checks are documented in [`ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md`](ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md). The Tink raw-key feasibility probes are documented in [`ENCRYPTED_LOCAL_VAULT_TINK_RAW_KEY_FEASIBILITY_PROBE.md`](ENCRYPTED_LOCAL_VAULT_TINK_RAW_KEY_FEASIBILITY_PROBE.md). The header commitment, canonical header encoding, key-separation label, strict AAD construction contract, deterministic AAD bytes, and record AEAD behavioral fixture are documented in [`ENCRYPTED_LOCAL_VAULT_HEADER_COMMITMENT_AAD_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_HEADER_COMMITMENT_AAD_CONTRACT.md). The selected HKDF-SHA-256 key expansion, HMAC-SHA-256 header commitment, and output layout are documented in [`ENCRYPTED_LOCAL_VAULT_KEY_EXPANSION_COMMITMENT_POLICY.md`](ENCRYPTED_LOCAL_VAULT_KEY_EXPANSION_COMMITMENT_POLICY.md). Deterministic non-secret canonical header/HKDF/HMAC vectors are documented in [`ENCRYPTED_LOCAL_VAULT_CANONICAL_HEADER_HKDF_HMAC_VECTORS.md`](ENCRYPTED_LOCAL_VAULT_CANONICAL_HEADER_HKDF_HMAC_VECTORS.md). The provider-level KAT strategy for randomized AEAD and stale-record/rollback manifest contract are documented in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md). The detailed vault container, manifest, storage, stale-record, rollback, atomicity, crash-recovery, and secure-storage boundary contract is documented in [`ENCRYPTED_LOCAL_VAULT_CONTAINER_MANIFEST_STORAGE_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_CONTAINER_MANIFEST_STORAGE_CONTRACT.md). The v1 production-provider acceptance contract is documented in [`ENCRYPTED_LOCAL_VAULT_PRODUCTION_PROVIDER_ACCEPTANCE_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PRODUCTION_PROVIDER_ACCEPTANCE_CONTRACT.md). That registry selects only `DisabledVaultCryptoProvider`; Tink plus Bouncy Castle remains a blocked future candidate and no production provider is selectable.

Runtime behavior remains fail-closed:

- `DisabledVaultCryptoProvider` rejects every modeled provider operation.
- `VaultCryptoProviderSelectionRegistry` selects only the disabled provider.
- `DisabledVaultCryptoProvider` rejects `validateKat`; provider-level KAT success is available only through test-only harnesses documented in [`ENCRYPTED_LOCAL_VAULT_TEST_PROVIDER_KAT_HARNESS.md`](ENCRYPTED_LOCAL_VAULT_TEST_PROVIDER_KAT_HARNESS.md).
- `SecureSecretStorage` remains disabled.
- `SecureWalletMetadataRepository` remains disabled.
- `EncryptedVaultReadinessPolicy` remains not implemented/not ready.
- Production persistence remains disabled.
- Production sync remains disabled.
- Mainnet remains disabled.

## Source Location

Production-safe common boundary:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/VaultCryptoProvider.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/VaultCryptoProviderSelection.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ContainerFormat.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ManifestFormat.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1StillDisabledProviderFacade.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1PassphrasePolicyBoundary.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1MigrationCorruptionBoundary.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/AndroidVaultCompatibilityPolicy.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/RuntimeRandomnessProviderChecks.kt
```

Tests and source guards:

```text
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultCryptoProviderBoundaryTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultContainerParserWriterTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultManifestParserStalePolicyTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultCryptoProviderKatContractTest.kt
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/VaultCryptoTestProviderKatHarnessTest.kt
composeApp/src/androidInstrumentedTest/kotlin/com/libertasprimordium/skald/VaultCryptoAndroidTestProviderKatHarnessTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/EncryptedVaultReadinessPolicyTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultCryptoDependencyProbeTest.kt
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/ProductionBackendAdapterSourceGuardTest.kt
```

## Boundary Purpose

The boundary defines the type shape future production vault cryptography must implement without exposing Tink, Bouncy Castle, JCA/JCE, BDK, platform, storage, or network types to common UI, settings codecs, storage models, Recovery Center models, Privacy Analyzer models, sync facade models, wallet domain models, or public app services.

The boundary models:

- provider implementation status,
- provider capabilities,
- provider blockers,
- typed KDF request/result/error models,
- typed AEAD encrypt/decrypt request/result/error models,
- key generation request/result models,
- keyset storage request/result models,
- provider-level KAT request/result/evidence models,
- record purposes,
- key roles,
- associated-data context,
- nonce mode policy,
- provider-level KAT requirements and contract registry,
- redacted diagnostics.

All operations return Skald-owned blocked results in this branch.

## Algorithm Policy

The provider boundary does not accept arbitrary caller-controlled algorithm strings.

It uses existing typed policy enums:

- target KDF: `Argon2id`,
- reviewed KDF fallback: `scrypt`,
- rejected default KDF: `PBKDF2`,
- target record AEAD: `XChaCha20-Poly1305`,
- reviewed fallback/platform roles: `ChaCha20-Poly1305`, `AES-256-GCM`, `AES-GCM-SIV`,
- nonce policy: random 24-byte XChaCha nonce per production record.

`ProviderKatFixedNonceOnly` exists only to model future provider-level public test vectors. It does not allow caller-provided production nonces.

## Record And Associated-Data Policy

Record purposes are typed:

- secret payload record,
- sensitive metadata record,
- backup/export record,
- platform wrapping record,
- provider KAT/test record.

Associated-data context is required for modeled AEAD operations. It carries only non-secret envelope context such as container version, record purpose, schema version, key version, and the design requirement that sensitive wallet metadata is excluded from associated data.

Associated data must not contain mnemonic material, seed bytes, private descriptors, private keys, wallet labels, UTXO labels, transaction notes, real observed addresses, txids, outpoints, endpoint labels, backend credentials, Nostr private-key material, or identity-linkage metadata.

## Disabled Provider Behavior

`DisabledVaultCryptoProvider` rejects:

- KDF derivation,
- AEAD record encryption,
- AEAD record decryption,
- key generation,
- keyset storage,
- provider-level KAT validation.

It returns redacted `VaultCryptoProviderResult.Blocked` values. Diagnostics contain only typed operation names and safe status codes. The boundary does not serialize payloads, keys, nonces, ciphertexts, plaintexts, keysets, vault containers, or wallet metadata.

## Provider-Level KAT Requirements

Dependency-level KATs already passed for the current candidate stack:

- Bouncy Castle Argon2id against RFC 9106 section 5.3 on desktop JVM and Android runtime.
- Tink XChaCha20-Poly1305 against the XChaCha draft appendix A.1 on desktop JVM and Android runtime.

Those are dependency/probe KATs, not provider-boundary KATs. The provider-level KAT contract is now documented separately in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md).

The boundary records provider-level KAT requirements for:

- deterministic passphrase policy, Argon2id root-material, canonical header, HKDF info/output, HMAC header commitment, and strict AAD vectors through the future Skald-owned provider interface,
- randomized XChaCha20-Poly1305 record AEAD behavioral checks through the future Skald-owned provider interface, without requiring fixed ciphertext hex,
- integrated verification-order checks proving HMAC header commitment before record AEAD use,
- negative misuse cases such as wrong associated data, tampered ciphertext/tag, and wrong key,
- unsupported algorithm and production nonce-policy bypass rejection,
- redacted provider diagnostics,
- desktop runtime execution,
- Android runtime execution,
- release-like runtime coverage as a future gate,
- public non-wallet vectors only.

The disabled provider marks public-vector requirements as dependency/test evidence only and marks randomized AEAD behavioral, verification-order, negative, redaction, platform, and storage requirements as required but unsatisfied. A separate still-disabled integrated KAT harness now executes the deterministic vector stages and randomized AEAD behavioral checks with fixed non-secret fixtures, but no selectable production provider-level KAT path exists because no selectable production provider implementation exists. Passing dependency-level KATs remains necessary evidence, but it does not satisfy provider approval.

The test-only provider harness runs the public positive vectors and required negative cases through `VaultCryptoProvider.validateKat(...)` in desktop and Android test source sets. Returned evidence is redacted and scoped as `TestHarnessOnly`. This proves the interface can carry the required checks; it does not approve production provider implementation or storage.

`SkaldVaultV1StillDisabledProviderKatHarness` composes the still-disabled passphrase, Argon2id, HKDF, canonical header, HMAC header commitment, strict AAD, and Tink record AEAD building blocks. Its tests assert header commitment verification before record AEAD use and assert that commitment failure exits before record AEAD. It exposes no vault creation, provider selection, manifest/storage, secure storage, wallet, or persistence API.

`SkaldVaultV1StillDisabledProviderFacade` is a separate metadata/status facade for the future provider boundary. It exposes the suite id, KDF/AEAD/randomness labels, stable policy ids, still-disabled evidence, disabled reasons, remaining gates, and typed disabled results for creation/unlock/persistence/storage/selection status. It is not a `VaultCryptoProvider`, is not referenced by `VaultCryptoProviderSelectionRegistry`, and exposes no usable vault creation, unlock, storage, manifest, secure-storage, wallet, sync, backend, key generation, randomness, or record AEAD API.

`SkaldVaultV1PassphrasePolicyBoundary` is separate still-disabled passphrase policy evidence. It exposes typed policy categories, normalization and encoding policy identifiers, retry/throttle/lockout requirements, redaction requirements, clear/wipe requirements, biometric and Android-Keystore future-only status, OS-keyring/password-manager passphrase-storage rejection, and unlock prerequisites. It accepts typed policy requests only and does not accept actual passphrases, PINs, biometrics, password examples, mnemonic examples, passphrase bytes, passphrase hashes, passphrase fingerprints, KDF input/output, provider key material, raw paths, Settings values, provider handles, or storage handles. It does not normalize or encode real passphrases, hash or fingerprint passphrases, run Argon2id/KDF/HKDF/HMAC/AEAD, implement retry/throttle/lockout, implement passphrase UI, implement unlock UI, enable vault unlock, enable persistence, approve provider use, or make a provider selectable.

`SkaldVaultV1MigrationCorruptionBoundary` is separate still-disabled migration/corruption evidence. It exposes typed evidence categories, failure classes, required fail-closed actions, stale-record and rollback-suspicion review, partial-write handling, quarantine-required evidence, manual-review evidence, and redacted failure reporting. It accepts typed policy requests only and does not accept raw persisted storage, ciphertext, plaintext, nonce/tag/header-commitment bytes, KDF output, provider key material, passphrases, raw paths, Settings values, provider handles, storage handles, or database handles. It does not parse real storage, read files, write files, run migration, run migration dry-run, repair storage, quarantine records, recover records, verify AEAD tags, decrypt records, verify real header commitments, rewrite manifests or storage indexes, prove rollback resistance, prove crash recovery, enable vault unlock, enable persistence, approve provider use, or make a provider selectable.

Argon2id calibration policy is documented separately in [`ENCRYPTED_LOCAL_VAULT_ARGON2ID_CALIBRATION.md`](ENCRYPTED_LOCAL_VAULT_ARGON2ID_CALIBRATION.md). It now models the shared 64 MiB / t=3 / p=1 floor, 64-byte output, deterministic candidate selection, about-1-second preferred target, about-2-second acceptable target, floor execution failure, stored-parameter authority, and no silent downgrade as still-disabled evidence. Manual Android Argon2id calibration capture is documented separately in [`ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md). These inform future parameter policy review only; they do not add provider crypto, run provider-level KATs, approve final production calibration, or make any provider selectable.

Runtime randomness/provider checks are documented separately in [`ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md`](ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md). They prove only test-scope availability of approved randomness paths with small non-secret samples; they do not prove entropy quality, generate production vault material, run provider-level KATs, or make any provider selectable.

The production-provider acceptance contract pins the v1 review direction to Bouncy Castle Argon2id, HKDF-SHA-256 key expansion, HMAC-SHA-256 header commitment over canonical header bytes, Tink XChaCha20-Poly1305, and OS SecureRandom. It also documents the non-key-committing AEAD risk and requires future vault-level header commitment over canonical header fields, deterministic canonical header bytes, separated commitment key material, strict AAD binding to vault and record context, the `unicode-nfc-utf8-no-controls-no-whitespace-v1` passphrase encoding policy, Tink raw-key feasibility review, bounded Argon2id calibration, provider-level deterministic vector KATs, randomized AEAD behavioral KATs, verification-order KATs, stale-record manifest/storage policy, and production implementation tests matching the non-secret canonical header/HKDF/HMAC/AAD vectors before any production provider can be selectable.

The raw-key feasibility review now has test-scope outcomes of `FEASIBLE_PUBLIC_RAW_KEY_API` on desktop/JVM and `ANDROID_FEASIBLE_PUBLIC_RAW_KEY_API` on Android. The probes use fixed non-secret bytes and public Tink APIs to build a transient in-memory primitive from caller-supplied key bytes. The still-disabled record AEAD building block now uses that public API family. It does not persist a Tink keyset, does not generate a Tink vault key, does not use internal APIs, does not make a provider selectable, and does not remove the header-commitment requirement.

## Provider Type Confinement

The provider boundary exposes no:

- Tink types,
- Bouncy Castle types,
- JCA/JCE crypto types,
- BDK types,
- platform keystore/keyring types,
- file or settings storage types,
- network/process/client types.

Tink and Bouncy Castle imports remain confined to platform compile probes, approved still-disabled platform building blocks, and KAT/probe tests. The explicit-nonce Tink API remains KAT/probe-only and is not a production provider boundary. Record AEAD building-block source guards forbid keyset persistence, random Tink vault-key generation, internal Tink APIs, reflection, logging, file writes, storage APIs, and Android wrapping APIs.

## Readiness Alignment

`EncryptedVaultReadinessPolicy` now records that a disabled provider boundary, provider-level KAT contract, still-disabled Argon2id calibration policy building block, still-disabled integrated provider KAT execution, metadata-only still-disabled provider facade, still-disabled in-memory container parser/writer, still-disabled in-memory manifest parser/writer, local manifest-relative stale-record decision policy, and model-only storage/atomicity/secure-storage contracts exist. This does not satisfy production persistence or provider selectability.

The readiness model still blocks on:

- production provider implementation unavailable,
- still-disabled provider integration harness not selectable,
- final KDF parameter approval missing,
- KDF calibration policy implemented as a still-disabled floor/candidate-selection/no-downgrade model with non-final parameter tiers documented in [`ENCRYPTED_LOCAL_VAULT_ARGON2ID_PARAMETER_POLICY.md`](ENCRYPTED_LOCAL_VAULT_ARGON2ID_PARAMETER_POLICY.md),
- AEAD verification incomplete,
- vault container persistence implementation missing,
- stale-record manifest/storage integration unimplemented,
- manifest/storage atomicity and crash recovery unreviewed,
- lock/session lifecycle tests absent,
- redaction tests missing,
- migration/corruption boundary still disabled,
- passphrase policy runtime review missing,
- migration/corruption tests missing,
- secure secret storage disabled,
- secure metadata storage disabled,
- production persistence disabled,
- mainnet disabled.

`VaultCryptoDependencyProbeCatalog` records the Tink plus Bouncy Castle stack as a candidate with the disabled provider boundary, provider-level KAT contract, test-only provider KAT harness, still-disabled integrated provider KAT harness evidence, and still-disabled provider facade evidence. It remains candidate-only and not production-approved because no selectable production provider exists.

`VaultCryptoProviderSelectionRegistry` records the same evidence but treats dependency-level KATs, test-only provider KATs, test-only runtime randomness probes, still-disabled canonical/HKDF/HMAC/AAD/record-AEAD building blocks, the still-disabled integrated provider KAT harness, and the test-scope desktop/Android Tink raw-key feasibility probes as insufficient for production selection. It blocks Tink plus Bouncy Castle on missing selectable production provider implementation, non-final Argon2id parameters, missing bounded-calibration approval, missing runtime compatibility/randomness checks when evidence is unknown, disabled secure storage, disabled secure metadata persistence, missing vault container/storage review, missing manifest/stale-record integration, missing redaction/failure-mode tests, missing migration/corruption tests, and mainnet disablement.

## Explicit Non-Capabilities

This boundary does not enable:

- encrypted vault implementation,
- fake encryption,
- still-disabled provider facade selectability,
- provider-wired Argon2id passphrase KDF execution or final calibration approval,
- production AEAD execution,
- production key generation,
- Tink keyset creation,
- Tink keyset persistence,
- raw key material persistence,
- file-backed vault container read/write,
- passphrase, PIN, biometric, or unlock UI,
- passphrase policy boundary evidence as passphrase acceptance, normalization or encoding execution, hashing, fingerprinting, Argon2id/KDF execution, retry/throttle/lockout implementation, provider readiness, or unlock readiness,
- secure secret storage success,
- secure metadata persistence success,
- production observation/address-index/UTXO/label/note/wallet-history persistence,
- production sync,
- production backend clients,
- BDK production persistence,
- Nostr parsing,
- Lightning, Cashu, or Payjoin behavior,
- Tor transport,
- public backend defaults,
- Skald-operated infrastructure,
- mainnet.

## Acceptance Gates Before Provider Implementation

Before any future branch implements provider crypto:

1. Argon2id calibration policy/probe evidence and candidate parameter tiers must be reviewed, and final KDF parameter policy must be approved for Android and Linux desktop. The still-disabled calibration model now enforces the shared floor and no-downgrade/fail-closed rules, but final production approval is still absent. Android compatibility planning must use the supported OS baseline, runtime provider/primitive checks, approved cryptographic randomness checks, and fail-closed vault creation behavior documented in [`ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md) and [`ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md`](ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md); low-end and mid-range model testing are no longer hard blockers.
2. The v1 production-provider acceptance contract in [`ENCRYPTED_LOCAL_VAULT_PRODUCTION_PROVIDER_ACCEPTANCE_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PRODUCTION_PROVIDER_ACCEPTANCE_CONTRACT.md) must be satisfied.
3. Provider-level public KATs must be defined through the Skald-owned interface.
4. Canonical vault header commitment, HKDF-SHA-256 key expansion, HMAC-SHA-256 header commitment, separated commitment key material, strict AAD, and record AEAD must pass still-disabled integrated provider KATs.
5. The `unicode-nfc-utf8-no-controls-no-whitespace-v1` passphrase encoding policy must be implemented and tested without adding vault creation in the same step.
6. Tink raw AEAD key material handling must be approved through public supported APIs, or a separate human-approved alternative must be documented.
7. Split-provider invariants must be reviewed again at implementation level.
8. Redacted error behavior must be tested against failure modes.
9. No provider-specific types may escape the boundary.
10. No vault container or storage success path may be added unless that branch is explicitly scoped and approved.

## Next Step

The next focused branch should remain design/probe-only unless the user explicitly approves executable provider work: review runtime provider/primitive/randomness checks for supported Android and Linux paths or design a disabled production-provider skeleton with no storage, without file-backed vault container read/write or persistence.

## Provider Operation Authorization Boundary

`SkaldVaultV1ProviderOperationAuthorizationPolicy` is now the Skald-owned authorization model that future executable provider code must satisfy before any provider operation can run. It models provider availability checks, KAT execution, runtime randomness checks, entropy/salt/nonce/key generation, Argon2id/KDF/HKDF/HMAC, header commitment work, AEAD encrypt/decrypt, record encrypt/decrypt, manifest/storage-index authentication, key wrapping, provider clear/dispose, vault create, vault unlock, vault persistence, and mainnet operation categories.

In the current branch every category remains unauthorized. The boundary accepts typed policy evidence only, emits redacted evidence-only blocked results, and records required gates such as non-disabled provider selection, `productionProviderSelectable=true`, production provider implementation, provider KAT approval, dependency probe approval, runtime randomness approval, final KDF calibration, passphrase policy approval, lock/session approval, redaction/leakage approval, clear/wipe approval, persistence/storage safety approval where storage is involved, migration/corruption approval where parsing or migration is involved, secure storage approval, secure metadata approval, BDK persistence-bypass review, network-mode approval, and mainnet release review.

It does not run provider operations, KATs, randomness checks, entropy/salt/nonce/key generation, Argon2id/KDF/HKDF/HMAC, AEAD encrypt/decrypt, header commitment computation or verification against real data, record encrypt/decrypt, manifest/storage-index authentication, key wrapping/unwrapping, or provider clear/dispose. It does not create provider handles, make a provider selectable, approve production provider use, enable vault creation, enable vault unlock, enable vault persistence, or approve mainnet. Provider selection still selects only `DisabledVaultCryptoProvider`, `productionProviderSelectable` remains false, OS keyrings and password managers remain rejected for Skald-managed vault passphrase storage, and passphrase-first remains the default authority.
