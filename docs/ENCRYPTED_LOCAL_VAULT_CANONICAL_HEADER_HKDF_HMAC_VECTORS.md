# Encrypted Local Vault Canonical Header, HKDF, And HMAC Vectors

## Status

This document defines deterministic Skald Vault v1 non-secret test-vector contracts and records the still-disabled production-source building blocks that match them for:

- canonical vault header bytes,
- HKDF-SHA-256 subkey expansion inputs and outputs,
- HMAC-SHA-256 header commitment input and output.

The canonical header serializer, HKDF-SHA-256 expansion from caller-supplied 64-byte root material, and HMAC-SHA-256 header commitment computation/verification now exist as isolated production-source building blocks. They are not wired into vault creation, vault unlock, provider selectability, vault container read/write, secure storage, or metadata persistence. This document and those building blocks do not implement production Argon2id execution, passphrase-to-root derivation, production Tink AEAD execution, production randomness, key generation, vault container read/write, keyset persistence, secure secret storage success, secure metadata storage success, sync, wallet behavior, signing, broadcasting, Tor, Nostr, backend clients, public endpoints, Skald-operated infrastructure, or mainnet.

All byte strings below are fixed non-secret fixtures. They are not passphrases, salts from a real vault, vault ids from a real vault, wallet seeds, private keys, wallet labels, transaction notes, Bitcoin addresses, txids, Nostr secrets, Cashu proofs, backend credentials, or device identifiers.

Runtime behavior remains fail-closed:

- `VaultCryptoProviderSelectionRegistry` selects only `DisabledVaultCryptoProvider`.
- `ProductionProviderAcceptanceAssessment.productionProviderSelectable` remains `false`.
- Production persistence remains disabled.
- Vector-matching building blocks do not make a provider selectable.

## Why Byte-Exact Vectors Are Required

Header commitment must authenticate canonical bytes, not an implementation-dependent object graph. A future production provider must match these vectors before production selectability because Tink XChaCha20-Poly1305 is non-key-committing and Skald must verify a vault-level header commitment before record decrypt.

These vectors let future still-disabled implementation work answer three exact questions:

1. Does the implementation serialize the v1 header fixture to the exact canonical bytes?
2. Does HKDF-SHA-256 expand the fixed non-secret root material into the expected separated header and record keys?
3. Does HMAC-SHA-256 over canonical header bytes produce the expected header commitment tag?

They do not prove vault security, do not prove production provider correctness, do not implement vault unlock, and do not replace provider-level KATs or production tamper tests.

## Canonical Header Encoding Rule

The v1 vector uses this deterministic field encoding:

```text
field := field_id || value
field_id := uint16 big-endian
string value := uint16 big-endian byte length || UTF-8 bytes
byte value := uint16 big-endian byte length || bytes
uint16 value := uint16 big-endian
uint32 value := uint32 big-endian
```

The vector has no optional fields present except an explicitly empty integrity-critical metadata field. Unknown fields do not appear. There is no JSON, map iteration, platform-native serialization, object serialization, locale formatting, filesystem behavior, or platform newline behavior.

## Canonical Header Logical Fixture

| Field id | Field | Type | Fixture value |
| ---: | --- | --- | --- |
| 1 | Vault magic/domain marker | string | `SKALD-VAULT-V1` |
| 2 | Vault format version | uint16 | `1` |
| 3 | Provider suite id | string | `skald-vault-v1-bouncycastle-argon2id-tink-xchacha20poly1305-os-securerandom` |
| 4 | KDF algorithm id | string | `argon2id` |
| 5 | KDF version | uint16 | `19` |
| 6 | KDF memory parameter | uint32 KiB | `65536` |
| 7 | KDF iteration/time parameter | uint32 | `3` |
| 8 | KDF parallelism parameter | uint32 | `1` |
| 9 | Salt length and salt bytes | bytes | `000102030405060708090a0b0c0d0e0f101112131415161718191a1b1c1d1e1f` |
| 10 | Derived root material length | uint16 bytes | `64` |
| 11 | Vault id | bytes | `202122232425262728292a2b2c2d2e2f` |
| 12 | Passphrase encoding policy id | string | `unicode-nfc-utf8-no-controls-no-whitespace-v1` |
| 13 | Key-expansion policy id | string | `skald-vault-v1-hkdf-sha256-key-expansion-v1` |
| 14 | Key-separation policy id | string | `skald-vault-v1-key-separation-labels-v1` |
| 15 | Header-commitment primitive policy id | string | `skald-vault-v1-hmac-sha256-header-commitment-v1` |
| 16 | Header commitment policy id | string | `skald-vault-v1-header-commitment-v1` |
| 17 | AAD policy id | string | `skald-vault-v1-record-aad-v1` |
| 18 | AAD policy version | uint16 | `1` |
| 19 | Record format policy id | string | `skald-vault-v1-record-format-v1` |
| 20 | Record format policy version | uint16 | `1` |
| 21 | Feature flags | uint32 bitset | `0` |
| 22 | Integrity-critical header metadata | bytes | empty |

## Canonical Header Final Hex

The final canonical header byte sequence is 509 bytes:

```text
0001000e534b414c442d5641554c542d5631000200010003004b736b616c642d7661756c742d76312d
626f756e6379636173746c652d6172676f6e3269642d74696e6b2d786368616368613230706f6c
79313330352d6f732d73656375726572616e646f6d000400086172676f6e3269640005001300
060001000000070000000300080000000100090020000102030405060708090a0b0c0d0e0f10
1112131415161718191a1b1c1d1e1f000a0040000b0010202122232425262728292a2b2c2d2e
2f000c002d756e69636f64652d6e66632d757466382d6e6f2d636f6e74726f6c732d6e6f2d77
6869746573706163652d7631000d002b736b616c642d7661756c742d76312d686b64662d7368
613235362d6b65792d657870616e73696f6e2d7631000e0027736b616c642d7661756c742d76
312d6b65792d73657061726174696f6e2d6c6162656c732d7631000f002f736b616c642d7661
756c742d76312d686d61632d7368613235362d6865616465722d636f6d6d69746d656e742d76
3100100023736b616c642d7661756c742d76312d6865616465722d636f6d6d69746d656e742d76
310011001c736b616c642d7661756c742d76312d7265636f72642d6161642d76310012000100
13001f736b616c642d7661756c742d76312d7265636f72642d666f726d61742d763100140001
00150000000000160000
```

The production-source serializer in `SkaldVaultV1HeaderCommitment` and the desktop vector test `VaultCanonicalHeaderHkdfHmacVectorTest` assert that this exact hex is produced. No vault container parser, vault file serializer, or persistence path is added.

## HKDF-SHA-256 Vector

HKDF-SHA-256 is implemented as a still-disabled production-source key-expansion building block from caller-supplied 64-byte root material. The separate explicit-parameter Argon2id building block now produces root material from fixed non-secret passphrase fixtures for tests, but HKDF itself is not passphrase handling, and neither building block is wired into vault creation, unlock, persistence, or provider selectability.

Input keying material is a fixed 64-byte non-secret Argon2id root-material fixture:

```text
a0a1a2a3a4a5a6a7a8a9aaabacadaeafb0b1b2b3b4b5b6b7b8b9babbbcbdbebf
c0c1c2c3c4c5c6c7c8c9cacbcccdcecfd0d1d2d3d4d5d6d7d8d9dadbdcdddedf
```

HKDF salt is the header salt bytes from the canonical fixture:

```text
000102030405060708090a0b0c0d0e0f101112131415161718191a1b1c1d1e1f
```

Info is encoded with the same field format used for the header vector:

| Field id | Field | Type |
| ---: | --- | --- |
| 1 | Root/domain label | string |
| 2 | Provider suite id | string |
| 3 | Purpose label | string |
| 4 | Key-expansion policy id | string |
| 5 | Vault format version | uint16 |

Header commitment key info fields:

```text
root/domain label: skald-vault/v1/root-domain
provider suite id: skald-vault-v1-bouncycastle-argon2id-tink-xchacha20poly1305-os-securerandom
purpose label: skald-vault/v1/header-commitment-key
key-expansion policy id: skald-vault-v1-hkdf-sha256-key-expansion-v1
vault format version: 1
```

Header commitment key info hex:

```text
0001001a736b616c642d7661756c742f76312f726f6f742d646f6d61696e0002004b736b616c
642d7661756c742d76312d626f756e6379636173746c652d6172676f6e3269642d74696e6b2d
786368616368613230706f6c79313330352d6f732d73656375726572616e646f6d0003002473
6b616c642d7661756c742f76312f6865616465722d636f6d6d69746d656e742d6b6579000400
2b736b616c642d7661756c742d76312d686b64662d7368613235362d6b65792d657870616e73
696f6e2d763100050001
```

Record AEAD key info fields:

```text
root/domain label: skald-vault/v1/root-domain
provider suite id: skald-vault-v1-bouncycastle-argon2id-tink-xchacha20poly1305-os-securerandom
purpose label: skald-vault/v1/record-aead-key
key-expansion policy id: skald-vault-v1-hkdf-sha256-key-expansion-v1
vault format version: 1
```

Record AEAD key info hex:

```text
0001001a736b616c642d7661756c742f76312f726f6f742d646f6d61696e0002004b736b616c
642d7661756c742d76312d626f756e6379636173746c652d6172676f6e3269642d74696e6b2d
786368616368613230706f6c79313330352d6f732d73656375726572616e646f6d0003001e73
6b616c642d7661756c742f76312f7265636f72642d616561642d6b65790004002b736b616c64
2d7661756c742d76312d686b64662d7368613235362d6b65792d657870616e73696f6e2d7631
00050001
```

Expected 32-byte header commitment key output:

```text
dc852862ead9ba057d6fb32842cff96b5dd853215fdcce2cff085383906d40fd
```

Expected 32-byte record AEAD key output:

```text
7634139c7f7d165280344d986374090b9124a4caa45739915c75ff2b300a0872
```

## HMAC-SHA-256 Header Commitment Vector

HMAC-SHA-256 is implemented as a still-disabled production-source header-commitment computation and verification building block. It is not wired into vault unlock, record decrypt, AEAD use, provider selectability, or vault persistence.

HMAC key source:

```text
HKDF-SHA-256 header commitment key output from this document
```

HMAC key:

```text
dc852862ead9ba057d6fb32842cff96b5dd853215fdcce2cff085383906d40fd
```

HMAC message:

```text
canonical header bytes from this document
```

Expected 32-byte HMAC-SHA-256 tag:

```text
1d09a657d8929444c1e955410b2dcb3bfc0df2d19b128154e17a5fbd751237d5
```

The desktop vector test asserts the production-source HKDF outputs and HMAC tag exactly.

## Readiness And Selectability Effect

The vector contracts are represented in:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/ProductionProviderAcceptanceContract.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/EncryptedVaultReadiness.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/VaultCryptoDependencyProbe.kt
```

Statuses distinguish:

- missing,
- unknown,
- documented only,
- inputs defined with outputs pending,
- vectors complete in test scope,
- failed or unsupported,
- production implemented and tested.

Missing, unknown, incomplete, pending, failed, unsupported, documented-only, test-scope-only, or unintegrated vector/building-block evidence blocks production provider selectability. Completed vectors and vector-matching building blocks still do not make the provider selectable because production provider implementation, production provider-level KATs, provider-wired Argon2id passphrase derivation and calibration, Tink AEAD execution, strict AAD implementation, vault storage, secure secret storage, secure metadata storage, and release review remain absent.

## Source-Guard Expectations

Source guards must continue proving:

- canonical header serialization appears only in the approved still-disabled building-block file and tests,
- HKDF execution appears only in the approved still-disabled building-block file and tests,
- HMAC execution appears only in the approved still-disabled building-block files and tests,
- header commitment computation appears only in the approved still-disabled building-block file and tests,
- no production AEAD encrypt/decrypt path was added,
- no production vault persistence was added,
- no production key generation was added,
- no Tink keyset persistence was added,
- no random Tink vault key generation path was added,
- no internal Tink API usage was added,
- no Android Keystore, StrongBox, biometric, or wrapping implementation was added.

The approved implementation/vector files for this branch are:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1HeaderCommitment.kt
composeApp/src/androidMain/kotlin/com/libertasprimordium/skald/security/AndroidSkaldVaultV1HeaderCommitmentCrypto.kt
composeApp/src/desktopMain/kotlin/com/libertasprimordium/skald/security/DesktopSkaldVaultV1HeaderCommitmentCrypto.kt
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/VaultCanonicalHeaderHkdfHmacVectorTest.kt
```

## Remaining Work

Before still-disabled provider implementation can proceed, reviewers still need:

- provider-level KATs through the future production provider,
- integration of the vector-tested canonical serializer, HKDF expansion, and HMAC verification into a still-disabled provider/format boundary,
- provider-wired Argon2id passphrase-to-root-material derivation or calibration,
- production Tink AEAD implementation with strict AAD,
- final bounded Argon2id calibration approval,
- record version/counter and stale-record policy,
- vault container/storage review,
- lock/session lifecycle review,
- redaction and failure-mode tests,
- migration/corruption/partial-write review,
- secure secret storage and secure metadata storage review.

The next branch must still not enable vault persistence.
