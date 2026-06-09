# Encrypted Local Vault Header Commitment And AAD Contract

## Status

This document defines the Skald Vault v1 header commitment, canonical header encoding, key-separation label, and AEAD associated-data acceptance contract. The selected HKDF-SHA-256 key-expansion primitive, HMAC-SHA-256 header-commitment primitive, output layout, and threat-model rationale are documented separately in [`ENCRYPTED_LOCAL_VAULT_KEY_EXPANSION_COMMITMENT_POLICY.md`](ENCRYPTED_LOCAL_VAULT_KEY_EXPANSION_COMMITMENT_POLICY.md).

It is design, model, and acceptance-contract evidence only. It does not implement production header commitment computation, canonical serialization, key derivation, AEAD encryption, AEAD decryption, random-byte generation, key generation, vault container read/write, keyset persistence, secure secret storage success, secure metadata storage success, sync, wallet behavior, signing, broadcasting, Tor, Nostr, backend clients, public endpoints, Skald-operated infrastructure, or mainnet.

Runtime behavior remains fail-closed:

- `VaultCryptoProviderSelectionRegistry` selects only `DisabledVaultCryptoProvider`.
- `ProductionProviderAcceptanceAssessment.productionProviderSelectable` remains `false`.
- Production persistence remains disabled.
- Secure secret storage and secure metadata storage remain disabled.

## Why This Contract Exists

Skald Vault v1 pins Tink XChaCha20-Poly1305 as the future record AEAD direction. Tink XChaCha20-Poly1305 is not key-committing. Successful AEAD record decryption must therefore never be treated as proof that the passphrase-derived vault key is the intended key for the vault.

The vault format must establish key correctness before any record decrypt. Skald does that at the vault-format layer by requiring a separate header commitment over canonical header bytes, verified with key material separated from record AEAD key material. Record decrypt is allowed only after header commitment verification succeeds.

The desktop/JVM and Android Tink raw-key feasibility probes remain useful because they show that public Tink APIs can construct the pinned primitive from caller-supplied fixed raw key bytes in test scope. They do not remove this header commitment requirement, do not prove key commitment, and do not implement production AEAD.

## Header Commitment Contract

The v1 header commitment policy id is:

```text
skald-vault-v1-header-commitment-v1
```

The v1 header-commitment primitive policy id is:

```text
skald-vault-v1-hmac-sha256-header-commitment-v1
```

Before any encrypted record decrypt, a future production vault must verify a vault-level HMAC-SHA-256 header commitment over canonical header bytes using the derived 32-byte header commitment key. Verification failure must block vault unlock and record decrypt.

The commitment must bind at least:

- vault magic/domain marker,
- vault format version,
- provider suite id,
- KDF algorithm id,
- KDF version,
- KDF memory parameter,
- KDF iteration/time parameter,
- KDF parallelism parameter,
- salt length and salt bytes,
- derived root material length,
- vault id,
- passphrase encoding policy id,
- key-separation policy id,
- header commitment policy id,
- AAD policy id/version,
- record format policy id/version,
- optional feature flags if present,
- all integrity-critical header metadata.

The commitment must fail closed for:

- modified header field,
- omitted required header field,
- duplicated required header field,
- unknown field in a non-extensible section,
- unknown provider suite id,
- unsupported KDF algorithm or version,
- unsupported KDF parameters,
- unsupported passphrase encoding policy,
- unsupported key-separation policy,
- unsupported AAD policy,
- unsupported record format policy,
- non-canonical header encoding,
- malformed length,
- malformed integer encoding,
- malformed UTF-8/string encoding,
- unknown policy version,
- unsupported future version,
- unimplemented or unavailable commitment verification.

Missing, unknown, documented/model-only, failed, unsupported, or unimplemented header commitment evidence blocks production provider selectability.

## Canonical Header Encoding Contract

The v1 canonical header encoding policy id is:

```text
skald-vault-v1-canonical-header-encoding-v1
```

Header commitment must operate over canonical bytes, not over unordered or implementation-dependent structures. The same logical header must have exactly one byte encoding on Linux desktop JVM and Android.

Required canonical encoding rules:

- include an explicit Skald Vault domain/magic prefix: `SKALD-VAULT-V1`,
- encode all integers in big-endian byte order,
- define the width of every numeric field,
- encode strings as UTF-8,
- keep policy identifiers and suite identifiers as ASCII constants,
- use explicit length prefixes for variable-length fields,
- use explicit field order,
- encode optional field presence or absence explicitly,
- reject unknown fields in non-extensible sections,
- define maximum lengths for variable-length fields before implementation,
- define versioning for future header formats,
- forbid default object serialization,
- forbid non-canonical JSON for committed bytes,
- forbid map iteration order unless keys are explicitly sorted and encoded,
- forbid platform-native integer/string serialization,
- require canonical header byte test vectors before production selectability.

This branch does not add a production serializer. A future implementation branch must produce non-secret canonical header test vectors before the production provider can be selectable.

## Key-Separation Label Contract

The v1 key-separation policy id is:

```text
skald-vault-v1-key-separation-labels-v1
```

Argon2id derives passphrase-derived root material. Root material must not be reused directly for multiple purposes. Header commitment key material and record AEAD key material must be separated. Future wrapping metadata, export keys, or migration keys must not reuse record AEAD key material.

Policy labels are stable, versioned ASCII constants:

| Purpose | Label |
| --- | --- |
| Root/domain context | `skald-vault/v1/root-domain` |
| Header commitment key material | `skald-vault/v1/header-commitment-key` |
| Record AEAD key material | `skald-vault/v1/record-aead-key` |
| Reserved future wrapping metadata | `skald-vault/v1/reserved/wrapping-metadata` |
| Reserved future export/migration | `skald-vault/v1/reserved/export-migration` |
| Test/probe-only domain | `skald-vault/test-only/raw-key-probe` |

The reserved labels are not implemented by this branch. The test/probe label is not a production label.

The v1 key-expansion primitive is HKDF-SHA-256 under policy id:

```text
skald-vault-v1-hkdf-sha256-key-expansion-v1
```

Argon2id produces 64 bytes of root material. HKDF-SHA-256 expands that root material into a 32-byte header commitment key and a 32-byte record AEAD key. The record AEAD key feeds the previously probed public Tink XChaCha20-Poly1305 raw-key API path.

This branch does not implement HKDF, HMAC, or any production key derivation.

Unknown or unsupported key-separation policy evidence blocks production provider selectability.

## AEAD AAD Contract

The v1 record AAD policy id is:

```text
skald-vault-v1-record-aad-v1
```

The v1 record format policy id is:

```text
skald-vault-v1-record-format-v1
```

Every future record AEAD operation must bind encrypted records to the exact vault context and record identity. AAD must bind at least:

- vault magic/domain marker,
- vault format version,
- provider suite id,
- vault id,
- record format policy id/version,
- AAD policy id/version,
- record type,
- record id,
- record version or monotonic counter,
- integrity-critical record metadata,
- header commitment policy id,
- canonical header commitment value or stable commitment identifier,
- future storage namespace if relevant.

AAD mismatch must fail closed for:

- wrong vault id,
- wrong provider suite id,
- wrong record type,
- wrong record id,
- wrong record version/counter,
- wrong integrity-critical record metadata,
- wrong AAD policy version,
- wrong header commitment context,
- copied ciphertext between vaults,
- copied ciphertext between record ids,
- copied ciphertext between record types,
- replayed stale record where the version/counter policy rejects stale data.

The AAD contract prevents cross-vault confusion, cross-record copying, record-type confusion, and stale-record replay only after the future format defines and implements the relevant record version/counter policy. This branch models that requirement; it does not implement AEAD execution or record storage.

## Tink Non-Key-Commitment Integration

The future unlock path must not try arbitrary record decrypts until one succeeds. Wrong-passphrase and wrong-key behavior must be decided by header commitment verification before record decrypt.

The required order is:

1. Parse and validate the plaintext unlock header structure.
2. Reject unsupported suite, KDF, passphrase encoding, key-separation, header commitment, AAD, and record format policies.
3. Canonicalize or reconstruct canonical header bytes.
4. Derive 64 bytes of Argon2id root material according to the stored header parameters.
5. Expand subkeys using HKDF-SHA-256 and stable labels.
6. Verify the HMAC-SHA-256 header commitment over canonical header bytes.
7. Only after successful verification, attempt record decrypt with strict AAD.

Raw-key feasibility evidence cannot bypass steps 1-5. Dependency-level KATs, test-provider KATs, and Tink raw-key probes are necessary evidence, but they do not satisfy header commitment, canonical encoding, key-separation, AAD, tamper-test, storage, or provider selectability gates.

## Model And Test Evidence

The policy is represented in:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/ProductionProviderAcceptanceContract.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/EncryptedVaultReadiness.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/VaultCryptoDependencyProbe.kt
```

Tests assert that missing, unknown, documented/model-only, failed, unsupported, or unimplemented evidence blocks selectability:

```text
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/ProductionProviderAcceptanceContractTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/EncryptedVaultReadinessPolicyTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultCryptoDependencyProbeTest.kt
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/ProductionBackendAdapterSourceGuardTest.kt
```

Source guards continue to prove that common production source does not import Tink or Bouncy Castle provider APIs, does not execute production AEAD/KDF/HKDF/HMAC operations, does not persist keysets, does not generate random Tink vault keys, does not implement header commitment computation, and does not add vault storage.

## Remaining Implementation Blockers

Before a still-disabled provider implementation can proceed, human review must still resolve:

- production HKDF-SHA-256 key-expansion implementation and non-secret test vectors,
- canonical header byte test vectors,
- production HMAC-SHA-256 header-commitment implementation and non-secret test vectors,
- record version/counter and stale-record policy,
- production provider-level KAT execution,
- final bounded Argon2id calibration,
- runtime randomness/provider evidence for supported platforms,
- lock/session lifecycle,
- redaction and failure-mode tests,
- crash/corruption/partial-write behavior,
- vault container/storage review,
- secure secret storage and secure metadata storage review.

The next step should remain a design/probe or still-disabled implementation-skeleton branch. It must not be a vault persistence branch.
