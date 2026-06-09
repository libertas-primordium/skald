# Encrypted Local Vault Crypto Provider Boundary

## Status

Skald Vault now has a narrow Skald-owned disabled `VaultCryptoProvider` boundary for the future app-controlled encrypted local vault.

This is a provider-boundary and policy-model pass. The passphrase policy validator, explicit-parameter Argon2id root derivation, canonical header serializer, HKDF-SHA-256 expansion, HMAC-SHA-256 header commitment verification, strict AAD serialization, and Tink XChaCha20-Poly1305 record AEAD construction from caller-supplied 32-byte key material now exist as still-disabled building blocks. The provider boundary still does not implement selectable provider crypto, calibration, provider-wired Argon2id passphrase KDF execution, provider-wired AEAD execution, key generation, Tink keyset creation or storage, raw key material storage, vault container parsing or writing, secure secret storage, secure metadata persistence, production sync, backend clients, signing, broadcasting, Tor transport, Nostr parsing, public endpoints, Skald-operated infrastructure, or mainnet.

Provider selection is documented separately in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md). Runtime randomness/provider checks are documented in [`ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md`](ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md). The Tink raw-key feasibility probes are documented in [`ENCRYPTED_LOCAL_VAULT_TINK_RAW_KEY_FEASIBILITY_PROBE.md`](ENCRYPTED_LOCAL_VAULT_TINK_RAW_KEY_FEASIBILITY_PROBE.md). The header commitment, canonical header encoding, key-separation label, strict AAD construction contract, deterministic AAD bytes, and record AEAD behavioral fixture are documented in [`ENCRYPTED_LOCAL_VAULT_HEADER_COMMITMENT_AAD_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_HEADER_COMMITMENT_AAD_CONTRACT.md). The selected HKDF-SHA-256 key expansion, HMAC-SHA-256 header commitment, and output layout are documented in [`ENCRYPTED_LOCAL_VAULT_KEY_EXPANSION_COMMITMENT_POLICY.md`](ENCRYPTED_LOCAL_VAULT_KEY_EXPANSION_COMMITMENT_POLICY.md). Deterministic non-secret canonical header/HKDF/HMAC vectors are documented in [`ENCRYPTED_LOCAL_VAULT_CANONICAL_HEADER_HKDF_HMAC_VECTORS.md`](ENCRYPTED_LOCAL_VAULT_CANONICAL_HEADER_HKDF_HMAC_VECTORS.md). The v1 production-provider acceptance contract is documented in [`ENCRYPTED_LOCAL_VAULT_PRODUCTION_PROVIDER_ACCEPTANCE_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PRODUCTION_PROVIDER_ACCEPTANCE_CONTRACT.md). That registry selects only `DisabledVaultCryptoProvider`; Tink plus Bouncy Castle remains a blocked future candidate and no production provider is selectable.

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
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/AndroidVaultCompatibilityPolicy.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/RuntimeRandomnessProviderChecks.kt
```

Tests and source guards:

```text
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultCryptoProviderBoundaryTest.kt
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

- Argon2id KDF through the future Skald-owned provider interface,
- XChaCha20-Poly1305 record AEAD through the future Skald-owned provider interface,
- negative misuse cases such as wrong associated data, tampered ciphertext/tag, and wrong key,
- unsupported algorithm and production nonce-policy bypass rejection,
- redacted provider diagnostics,
- desktop runtime execution,
- Android runtime execution,
- release-like runtime coverage as a future gate,
- public non-wallet vectors only.

The disabled provider marks positive public-vector requirements as `DependencyLevelPassedProviderLevelMissing` and marks negative/redaction/platform/storage requirements as required but unsatisfied. No production provider-level KAT path exists yet because no production executable provider implementation exists. Passing dependency-level KATs remains necessary evidence, but it does not satisfy provider approval.

The test-only provider harness runs the public positive vectors and required negative cases through `VaultCryptoProvider.validateKat(...)` in desktop and Android test source sets. Returned evidence is redacted and scoped as `TestHarnessOnly`. This proves the interface can carry the required checks; it does not approve production provider implementation or storage.

Manual Android Argon2id calibration capture is documented separately in [`ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md). It informs future parameter policy review only; it does not add provider crypto, run provider-level KATs, or make any provider selectable.

Runtime randomness/provider checks are documented separately in [`ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md`](ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md). They prove only test-scope availability of approved randomness paths with small non-secret samples; they do not prove entropy quality, generate production vault material, run provider-level KATs, or make any provider selectable.

The production-provider acceptance contract pins the v1 review direction to Bouncy Castle Argon2id, HKDF-SHA-256 key expansion, HMAC-SHA-256 header commitment over canonical header bytes, Tink XChaCha20-Poly1305, and OS SecureRandom. It also documents the non-key-committing AEAD risk and requires future vault-level header commitment over canonical header fields, deterministic canonical header bytes, separated commitment key material, strict AAD binding to vault and record context, the `unicode-nfc-utf8-no-controls-no-whitespace-v1` passphrase encoding policy, Tink raw-key feasibility review, bounded Argon2id calibration, and production implementation tests matching the non-secret canonical header/HKDF/HMAC vectors before any production provider can be selectable.

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

`EncryptedVaultReadinessPolicy` now records that a disabled provider boundary and provider-level KAT contract are modeled. This does not satisfy production persistence.

The readiness model still blocks on:

- production provider implementation unavailable,
- provider-level KATs missing,
- final KDF parameter approval missing,
- KDF calibration policy modeled with probe-only candidates and non-final parameter tiers documented in [`ENCRYPTED_LOCAL_VAULT_ARGON2ID_PARAMETER_POLICY.md`](ENCRYPTED_LOCAL_VAULT_ARGON2ID_PARAMETER_POLICY.md),
- AEAD verification incomplete,
- vault container format absent,
- lock/session lifecycle tests absent,
- redaction tests missing,
- migration/corruption tests missing,
- secure secret storage disabled,
- secure metadata storage disabled,
- production persistence disabled,
- mainnet disabled.

`VaultCryptoDependencyProbeCatalog` records the Tink plus Bouncy Castle stack as a candidate with the disabled provider boundary, provider-level KAT contract, and test-only provider KAT harness modeled. It remains candidate-only and not production-approved because production provider-level KAT execution is still missing.

`VaultCryptoProviderSelectionRegistry` records the same evidence but treats dependency-level KATs, test-only provider KATs, test-only runtime randomness probes, still-disabled canonical/HKDF/HMAC/AAD/record-AEAD building blocks, and the test-scope desktop/Android Tink raw-key feasibility probes as insufficient for production selection. It blocks Tink plus Bouncy Castle on missing production provider implementation, missing production provider-level KATs, non-final Argon2id parameters, missing bounded-calibration approval, missing runtime compatibility/randomness checks when evidence is unknown, missing provider integration for header commitment, canonical header encoding, key separation, output layout, strict AAD, and record AEAD, unwired passphrase encoding validation, disabled secure storage, disabled secure metadata persistence, missing vault container/storage review, missing redaction/failure-mode tests, missing migration/corruption tests, and mainnet disablement.

## Explicit Non-Capabilities

This boundary does not enable:

- encrypted vault implementation,
- fake encryption,
- provider-wired Argon2id passphrase KDF execution or calibration,
- production AEAD execution,
- production key generation,
- Tink keyset creation,
- Tink keyset persistence,
- raw key material persistence,
- vault container read/write,
- passphrase, PIN, biometric, or unlock UI,
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

1. Argon2id calibration policy/probe evidence and candidate parameter tiers must be reviewed, and final KDF parameter policy must be approved for Android and Linux desktop. Android compatibility planning must use the supported OS baseline, runtime provider/primitive checks, approved cryptographic randomness checks, and fail-closed vault creation behavior documented in [`ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md) and [`ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md`](ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md); low-end and mid-range model testing are no longer hard blockers.
2. The v1 production-provider acceptance contract in [`ENCRYPTED_LOCAL_VAULT_PRODUCTION_PROVIDER_ACCEPTANCE_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PRODUCTION_PROVIDER_ACCEPTANCE_CONTRACT.md) must be satisfied.
3. Provider-level public KATs must be defined through the Skald-owned interface.
4. Canonical vault header commitment, HKDF-SHA-256 key expansion, HMAC-SHA-256 header commitment, and separated commitment key material must be integrated and tested through a still-disabled provider.
5. The `unicode-nfc-utf8-no-controls-no-whitespace-v1` passphrase encoding policy must be implemented and tested without adding vault creation in the same step.
6. Tink raw AEAD key material handling must be approved through public supported APIs, or a separate human-approved alternative must be documented.
7. Split-provider invariants must be reviewed again at implementation level.
8. Redacted error behavior must be tested against failure modes.
9. No provider-specific types may escape the boundary.
10. No vault container or storage success path may be added unless that branch is explicitly scoped and approved.

## Next Step

The next focused branch should remain design/probe-only unless the user explicitly approves executable provider work: review runtime provider/primitive/randomness checks for supported Android and Linux paths or design a disabled production-provider skeleton with no storage, without vault container read/write or persistence.
