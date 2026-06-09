# Encrypted Local Vault Header Commitment And AAD Contract

## Status

This document defines the Skald Vault v1 header commitment, canonical header encoding, key-separation label, and AEAD associated-data acceptance contract. The selected HKDF-SHA-256 key-expansion primitive, HMAC-SHA-256 header-commitment primitive, output layout, and threat-model rationale are documented separately in [`ENCRYPTED_LOCAL_VAULT_KEY_EXPANSION_COMMITMENT_POLICY.md`](ENCRYPTED_LOCAL_VAULT_KEY_EXPANSION_COMMITMENT_POLICY.md). Deterministic non-secret canonical header, HKDF, and HMAC vectors are documented in [`ENCRYPTED_LOCAL_VAULT_CANONICAL_HEADER_HKDF_HMAC_VECTORS.md`](ENCRYPTED_LOCAL_VAULT_CANONICAL_HEADER_HKDF_HMAC_VECTORS.md).

The passphrase policy validator, explicit-parameter Argon2id root derivation, canonical header serializer, HKDF-SHA-256 key expansion, HMAC-SHA-256 header commitment computation/verification, strict record AAD serialization, and Tink XChaCha20-Poly1305 record AEAD construction from caller-supplied 32-byte key material now exist as still-disabled production-source building blocks. This contract still does not implement calibration, provider-wired KDF execution, provider-selectable AEAD, random-byte generation, key generation, vault container read/write, keyset persistence, secure secret storage success, secure metadata storage success, sync, wallet behavior, signing, broadcasting, Tor, Nostr, backend clients, public endpoints, Skald-operated infrastructure, or mainnet.

Runtime behavior remains fail-closed:

- `VaultCryptoProviderSelectionRegistry` selects only `DisabledVaultCryptoProvider`.
- `ProductionProviderAcceptanceAssessment.productionProviderSelectable` remains `false`.
- Production persistence remains disabled.
- Secure secret storage and secure metadata storage remain disabled.

## Why This Contract Exists

Skald Vault v1 pins Tink XChaCha20-Poly1305 as the future record AEAD direction. Tink XChaCha20-Poly1305 is not key-committing. Successful AEAD record decryption must therefore never be treated as proof that the passphrase-derived vault key is the intended key for the vault.

The vault format must establish key correctness before any record decrypt. Skald does that at the vault-format layer by requiring a separate header commitment over canonical header bytes, verified with key material separated from record AEAD key material. Record decrypt is allowed only after header commitment verification succeeds.

The desktop/JVM and Android Tink raw-key feasibility probes remain useful because they show that public Tink APIs can construct the pinned primitive from caller-supplied fixed raw key bytes in test scope. The still-disabled record AEAD building block now uses that public API family. It does not remove this header commitment requirement, does not prove key commitment, and does not make a production provider selectable.

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
- key-expansion policy id,
- key-separation policy id,
- header-commitment primitive policy id,
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

The production-source serializer in `SkaldVaultV1HeaderCommitment` now matches the non-secret canonical header vector. It is not a vault container parser/writer, does not read or write files, and is not wired into provider selection or persistence. Future provider work must still integrate this serializer through a still-disabled format/provider boundary before the production provider can be selectable.

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

HKDF-SHA-256 remains a still-disabled expansion building block from caller-supplied 64-byte root material. The separate explicit-parameter Argon2id building block can derive that root material from normalized passphrase bytes and a caller-supplied salt, but calibration, provider wiring, vault creation, unlock ordering, and persistence remain absent.

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
- key-expansion policy id,
- header-commitment primitive policy id,
- header commitment policy id,
- canonical header commitment value or stable commitment identifier,
- record type,
- record id,
- record version or monotonic counter,
- integrity-critical record metadata,
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

The production-source strict AAD serializer in `SkaldVaultV1RecordAead` now encodes these fields deterministically using explicit field order, big-endian integers, UTF-8 ASCII policy strings, and length-prefixed byte/string fields. It rejects unsupported suite ids, unsupported policy ids, unsupported versions, unsupported record types, malformed lengths, malformed integer values, and malformed strings before AEAD use.

The current non-secret AAD fixture binds:

- vault id bytes `202122232425262728292a2b2c2d2e2f`,
- header commitment context `1d09a657d8929444c1e955410b2dcb3bfc0df2d19b128154e17a5fbd751237d5`,
- record type `sensitive-metadata`,
- record id bytes `404142434445464748494a4b4c4d4e4f`,
- record version/counter `7`,
- integrity-critical metadata string `skald-vault-v1-record-metadata-fixture`,
- reserved local storage namespace `skald-vault/v1/local-records`.

The deterministic AAD bytes for that fixture are:

```text
0001000e534b414c442d5641554c542d5631000200010003004b736b616c642d7661756c742d76312d626f756e6379636173746c652d6172676f6e3269642d74696e6b2d786368616368613230706f6c79313330352d6f732d73656375726572616e646f6d00040010202122232425262728292a2b2c2d2e2f0005001f736b616c642d7661756c742d76312d7265636f72642d666f726d61742d7631000600010007001c736b616c642d7661756c742d76312d7265636f72642d6161642d7631000800010009002b736b616c642d7661756c742d76312d686b64662d7368613235362d6b65792d657870616e73696f6e2d7631000a002f736b616c642d7661756c742d76312d686d61632d7368613235362d6865616465722d636f6d6d69746d656e742d7631000b0023736b616c642d7661756c742d76312d6865616465722d636f6d6d69746d656e742d7631000c00201d09a657d8929444c1e955410b2dcb3bfc0df2d19b128154e17a5fbd751237d5000d001273656e7369746976652d6d65746164617461000e0010404142434445464748494a4b4c4d4e4f000f000000000000000700100026736b616c642d7661756c742d76312d7265636f72642d6d657461646174612d666978747572650011001c736b616c642d7661756c742f76312f6c6f63616c2d7265636f726473
```

The record AEAD building block uses caller-supplied 32-byte record AEAD key material and the public Tink raw-key path proven on desktop/JVM and Android. It creates a transient in-memory keyset handle and does not persist keysets, generate Tink vault keys, rotate keys, use multiple active AEAD keys, use internal Tink APIs, or use reflection. Tests assert round-trip decrypt, wrong AAD failure, wrong vault id failure, wrong record identity failure, wrong version/counter failure, wrong header commitment context failure, wrong key failure, tampered ciphertext failure, tampered tag failure, invalid key length rejection, and unsupported policy rejection.

Tink internally chooses the XChaCha20-Poly1305 nonce, so ciphertext is intentionally treated as nondeterministic. The fixture asserts deterministic AAD bytes and behavioral AEAD outcomes instead of a fixed ciphertext hex value.

AAD binding mitigates cross-vault confusion, cross-record copying, record-type confusion, and cross-version substitution. It does not by itself provide freshness or full rollback resistance. Full stale-record enforcement is a future manifest/storage responsibility documented in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md).

A future local manifest or vault index must track the latest trusted record version/counter per record id, be integrity-protected, bind vault id, provider suite id, header commitment context, manifest policy id/version, and record namespace, and update atomically with record writes or define crash-safe recovery. It must reject or quarantine records with a lower version/counter than the latest trusted local manifest state and reject or quarantine duplicate record ids with conflicting latest counters. Conflict handling must be defined before sync or import behavior is enabled.

For v1 local-only persistence, Skald must distinguish intra-vault substitution detection via AAD from stale-record detection against the latest trusted local manifest state. It must not claim global rollback resistance against a fully rolled-back local storage directory unless an external anchor, trusted monotonic counter, append-only log, remote checkpoint, or other anti-rollback anchor is designed. This branch does not implement a manifest reader, manifest writer, storage index, conflict resolver, sync path, storage path, or anti-rollback anchor.

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

Tests assert that missing, unknown, documented/model-only, failed, unsupported, or unimplemented evidence blocks selectability, and that the implemented strict AAD/record AEAD building blocks still do not make a provider selectable:

```text
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultRecordAeadBuildingBlockTest.kt
composeApp/src/androidInstrumentedTest/kotlin/com/libertasprimordium/skald/VaultCryptoAndroidRecordAeadBuildingBlockTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/ProductionProviderAcceptanceContractTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/EncryptedVaultReadinessPolicyTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultCryptoDependencyProbeTest.kt
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/ProductionBackendAdapterSourceGuardTest.kt
```

Source guards continue to prove that common production provider/selection/readiness code does not import Tink or Bouncy Castle provider APIs, does not persist keysets, does not generate random Tink vault keys, and does not add vault storage. Tink record AEAD execution is confined to exact approved still-disabled platform building-block files and approved tests. HKDF/HMAC/header-commitment execution is confined to the approved still-disabled building-block files and vector tests.

## Remaining Implementation Blockers

Before a still-disabled provider implementation can proceed, human review must still resolve:

- integration of the vector-tested canonical header serializer, HKDF-SHA-256 expansion, and HMAC-SHA-256 verification into a still-disabled provider/format boundary,
- provider-wired Argon2id passphrase-to-root-material derivation or calibration,
- provider integration for the strict AAD serializer and Tink record AEAD building block,
- record version/counter and stale-record manifest/index policy,
- production provider-level KAT execution,
- final bounded Argon2id calibration,
- runtime randomness/provider evidence for supported platforms,
- lock/session lifecycle,
- redaction and failure-mode tests,
- crash/corruption/partial-write behavior,
- vault container/storage review,
- secure secret storage and secure metadata storage review.

The next step should remain a design/probe or still-disabled implementation-skeleton branch. It must not be a vault persistence branch.
