# Encrypted Local Vault V1 Format, Parser, And Crypto Reconciliation Audit

## Audit status

`INVENTORY_COMPLETE_DESIGN_DECISION_REQUIRED`

This is an `encrypted-vault-v1-format-parser-crypto-reconciliation-audit-only` pass. Production behavior was not changed. Parser semantics were not changed. Container, manifest, header-commitment, Argon2id, HKDF/HMAC, and record-AEAD semantics were not changed. Fixtures were not moved. No artifact was deprecated, and no canonical architecture was selected.

The factual repository state contains two overlapping lines:

1. An earlier production-compiled v1 byte-format and direct-crypto line with container and manifest serialization/parsing, canonical-header encoding, Argon2id root derivation, HKDF/HMAC header commitment, XChaCha20-Poly1305 record AEAD, commonMain fixtures, and a production-compiled KAT harness.
2. A newer production-compiled synthetic classifier plus commonTest-only catalog/audit line whose scope statements often use “no parser,” “no writer,” “no serialization,” “no crypto,” and “canonical layout unresolved” to describe that narrower sequence of passes.

Both lines remain present. Neither has an app, repository, storage, file, UI, sync, backend, or production wallet-data integration call site. `VaultCryptoProviderSelectionRegistry` still selects only `DisabledVaultCryptoProvider`, but the earlier direct crypto objects and commonMain KAT harness do not consult that registry.

## Snapshot and counting definitions

- Branch: `phase2-encrypted-vault-v1-format-parser-crypto-reconciliation-audit`
- Starting commit: `767c4da6844fe9788ffbd30314866a82240df109`
- Android production source sets: `commonMain` plus `androidMain`.
- Desktop production source sets: `commonMain` plus `desktopMain`.
- “Production-compiled” means present in one of those production source sets. It does not mean invoked by an app flow.
- “Production integration call site” means a call outside the owning implementation or the production-compiled KAT/fixture graph that connects the operation to app, repository, storage, file, UI, sync, backend, provider selection, or wallet data.
- “Raw parser” means a public operation accepting `ByteArray` and constructing a structured format model.
- “Synthetic classifier” means the public request-wrapped operation that reads bytes only to classify the newer test-source markers.
- “Full-format serializer” means a public container or manifest model-to-`ByteArray` operation. Canonical-header, HKDF-info, and AAD encoders are counted separately.
- Function overloads are separate callable declarations. Logical-operation totals are also stated where that distinction matters.

### Exact surface counts

| Category | Count | Declarations |
|---|---:|---|
| Production-compiled raw parsers | 2 | `SkaldVaultV1ContainerFormat.parse`, `SkaldVaultV1ManifestFormat.parse` |
| Production-compiled synthetic byte classifiers | 1 | `EncryptedVaultWorkingParser.parse` |
| Production-compiled parser/classifier surfaces in total | 3 | The two raw parsers plus the synthetic classifier |
| Full-format serializers | 2 | Container and standalone manifest `serialize` |
| Other public structural byte encoders | 4 declarations / 3 logical encoders | `canonicalHeaderBytes`, two `hkdfInfoBytes` overloads, `aadBytes` |
| Core public crypto-capable declarations | 9 / 7 logical operations | Two Argon2id overloads; two HKDF expansion operations; HMAC compute; two HMAC verify overloads; record encrypt/decrypt |
| Additional public KAT crypto API surfaces | 3 | `sealRecord`, `openRecord`, `runProviderLevelKat` |
| Public production-compiled crypto-capable API surfaces including KAT wrappers | 12 | Core 9 plus three KAT operation categories |
| Kotlin source method declarations including adapter overrides | 14 | The 12 surfaces plus two building-block override declarations |
| Common expect primitive declarations | 4 | Argon2id, HMAC, AEAD encrypt, AEAD decrypt |
| Platform actual primitive declarations | 8 | Four Android plus four desktop; there is no HKDF actual |
| Exact named production call expressions outside owning implementations | 4 | HKDF expand, HMAC verify, record encrypt, record decrypt; all in the commonMain KAT file |
| Additional direct Argon2id call in that KAT graph | 1 | `deriveRootMaterial` |
| App/repository/storage/UI/sync/backend integration calls | 0 | No production integration consumer exists |
| Direct test call expressions for the exact named reachability list after this audit test | 100 | Static source occurrences; one additional `expandRootMaterialForPurpose` call is reported separately |
| Explicit public commonMain fixture/vector byte creators | 11 | Header, container, manifest, record-AEAD, and KAT request builders/getters |
| Public fixed KAT string constants in commonMain | 10 | Fixed non-wallet passphrase/vector expectation material |

The private container section parsers, manifest section parsers, byte readers, byte-list encoders, and platform actual primitives are inventoried below but are not counted as additional public parser/serializer surfaces.

## Required artifact inventory

The required matrix is split into two keyed tables so every requested field remains readable. `A/D` means compiled into Android/Desktop production artifacts. “Raw in/out” describes the declaration’s public or internal signature/model, not a claim that bytes are safe. “Prod call” distinguishes a production-source reference from app integration; the notes identify fixture/KAT-only calls.

### Matrix A — placement, byte API, and execution

| Fully qualified declaration | File / source set | A/D | Visibility | Raw in | Raw out | Parses | Serializes | KDF | HKDF | HMAC | AEAD enc | AEAD dec |
|---|---|---|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| `com.libertasprimordium.skald.security.SkaldVaultV1ContainerFormat` | `SkaldVaultV1ContainerFormat.kt` / commonMain | Y/Y | public object | Y | Y | Y | Y | N | N | N | N | N |
| `com.libertasprimordium.skald.security.SkaldVaultV1ManifestFormat` | `SkaldVaultV1ManifestFormat.kt` / commonMain | Y/Y | public object | Y | Y | Y | Y | N | N | N | N | N |
| `com.libertasprimordium.skald.security.SkaldVaultV1HeaderCommitment` | `SkaldVaultV1HeaderCommitment.kt` / commonMain | Y/Y | public object | Y | Y | N | canonical/header/info | N | Y | Y | N | N |
| `com.libertasprimordium.skald.security.SkaldVaultV1RecordAead` | `SkaldVaultV1RecordAead.kt` / commonMain | Y/Y | public object | Y | Y | N | AAD only | N | N | N | Y | Y |
| `com.libertasprimordium.skald.security.SkaldVaultV1Argon2idRootDerivation` | `SkaldVaultV1Argon2idRootDerivation.kt` / commonMain | Y/Y | public object | Y | Y | N | N | Y | N | N | N | N |
| `com.libertasprimordium.skald.security.SkaldVaultV1CanonicalHeader` | `SkaldVaultV1HeaderCommitment.kt` / commonMain | Y/Y | public data class | Y | Y | N | N | N | N | N | N | N |
| `com.libertasprimordium.skald.security.SkaldVaultV1ExpandedKeys` | same / commonMain | Y/Y | public class; private constructor/internal factory | Y | Y | N | N | N | N | N | N | N |
| `com.libertasprimordium.skald.security.SkaldVaultV1Container` | `SkaldVaultV1ContainerFormat.kt` / commonMain | Y/Y | public data class | Y | Y | N | N | N | N | N | N | N |
| `com.libertasprimordium.skald.security.SkaldVaultV1ContainerRecordEntry` | same / commonMain | Y/Y | public data class | Y | Y | N | N | N | N | N | N | N |
| `com.libertasprimordium.skald.security.SkaldVaultV1ContainerManifestSection` | same / commonMain | Y/Y | public data class | Y | Y | N | N | N | N | N | N | N |
| `com.libertasprimordium.skald.security.SkaldVaultV1Manifest` | `SkaldVaultV1ManifestFormat.kt` / commonMain | Y/Y | public data class | Y | Y | N | N | N | N | N | N | N |
| `com.libertasprimordium.skald.security.SkaldVaultV1ManifestRecordEntry` | same / commonMain | Y/Y | public data class | Y | Y | N | N | N | N | N | N | N |
| `com.libertasprimordium.skald.security.SkaldVaultV1RecordAadContext` | `SkaldVaultV1RecordAead.kt` / commonMain | Y/Y | public data class | Y | Y | N | N | N | N | N | N | N |
| `com.libertasprimordium.skald.security.SkaldVaultV1RecordCiphertext` | same / commonMain | Y/Y | public class; private constructor/internal factory | Y | Y | N | N | N | N | N | N | N |
| `com.libertasprimordium.skald.security.SkaldVaultV1RecordPlaintext` | same / commonMain | Y/Y | public class; private constructor/internal factory | Y | Y | N | N | N | N | N | N | N |
| `com.libertasprimordium.skald.security.SkaldVaultV1StillDisabledProviderKatHarness` | `SkaldVaultV1StillDisabledProviderKatHarness.kt` / commonMain | Y/Y | public class | Y | evidence only | N | canonical/AAD internally | Y | Y | Y | Y | Y |
| `com.libertasprimordium.skald.security.SkaldVaultV1ProviderKatFixtures` / request models | same / commonMain | Y/Y | public object/data classes | Y | Y | N | N | N | N | N | N | N |
| `com.libertasprimordium.skald.security.EncryptedVaultParserWriterScaffold` | `EncryptedVaultParserWriterScaffold.kt` / commonMain | Y/Y | public data class | N | N | N | N | N | N | N | N | N |
| `com.libertasprimordium.skald.security.DisabledEncryptedVaultParserScaffold` | same / commonMain | Y/Y | public object | count request only | N | rejects only | N | N | N | N | N | N |
| `com.libertasprimordium.skald.security.DisabledEncryptedVaultWriterScaffold` | same / commonMain | Y/Y | public object | count request only | N | N | rejects only | N | N | N | N | N |
| `com.libertasprimordium.skald.security.EncryptedVaultWorkingParser` | `EncryptedVaultWorkingParser.kt` / commonMain | Y/Y | public object | request-wrapped | N | classifies synthetic bytes | N | N | N | N | N | N |
| `com.libertasprimordium.skald.security.EncryptedVaultWorkingParserRequest` | same / commonMain | Y/Y | public class/private constructor | Y via factory | N | N | N | N | N | N | N | N |
| `com.libertasprimordium.skald.security.EncryptedVaultWorkingParserResult` | same / commonMain | Y/Y | public data class | N | N | N | N | N | N | N | N | N |
| `com.libertasprimordium.skald.security.EncryptedVaultParserWriterSyntheticVectorCatalog` | commonTest catalog | N/N | public test object | fixture strings | Y | N | marker bytes only | N | N | N | N | N |
| `com.libertasprimordium.skald.security.VaultCryptoProvider` | `VaultCryptoProvider.kt` / commonMain | Y/Y | public interface | opaque refs | opaque refs | N | N | interface only | N | N | interface only | interface only |
| `com.libertasprimordium.skald.security.DisabledVaultCryptoProvider` | same / commonMain | Y/Y | public class | opaque refs | blocked result | N | N | N | N | N | N | N |
| `com.libertasprimordium.skald.security.VaultCryptoProviderSelectionRegistry` | `VaultCryptoProviderSelection.kt` / commonMain | Y/Y | public object | N | selection model | N | N | N | N | N | N | N |
| `com.libertasprimordium.skald.security.SkaldVaultV1HeaderCommitment.hkdfSha256` | header file / commonMain | Y/Y | private function | Y | Y | N | N | N | Y | via HMAC | N | N |
| `com.libertasprimordium.skald.security.skaldVaultV1Argon2idRootMaterial` | Android actual | Y/N | internal actual function | Y | Y | N | N | Y | N | N | N | N |
| `com.libertasprimordium.skald.security.skaldVaultV1Argon2idRootMaterial` | desktop actual | N/Y | internal actual function | Y | Y | N | N | Y | N | N | N | N |
| `com.libertasprimordium.skald.security.skaldVaultV1HmacSha256` | Android actual | Y/N | internal actual function | Y | Y | N | N | N | N | Y | N | N |
| `com.libertasprimordium.skald.security.skaldVaultV1HmacSha256` | desktop actual | N/Y | internal actual function | Y | Y | N | N | N | N | Y | N | N |
| `com.libertasprimordium.skald.security.skaldVaultV1TinkRecordAeadEncrypt` | Android actual | Y/N | internal actual function | Y | Y | N | N | N | N | N | Y | N |
| `com.libertasprimordium.skald.security.skaldVaultV1TinkRecordAeadEncrypt` | desktop actual | N/Y | internal actual function | Y | Y | N | N | N | N | N | Y | N |
| `com.libertasprimordium.skald.security.skaldVaultV1TinkRecordAeadDecrypt` | Android actual | Y/N | internal actual function | Y | Y | N | N | N | N | N | N | Y |
| `com.libertasprimordium.skald.security.skaldVaultV1TinkRecordAeadDecrypt` | desktop actual | N/Y | internal actual function | Y | Y | N | N | N | N | N | N | Y |

There is no platform HKDF actual. HKDF-SHA-256 is implemented as the private commonMain helper above using the platform HMAC actual.

### Matrix B — fixtures, ownership, redaction, reachability, and classification

| Declaration | Fixed fixture bytes | Fixed fixture strings | Crypto vectors | Mutable array reference exposed | Input copy | Output copy | Explicit redacted `toString` | Prod call | Test call | Provider boundary used | Selection required | Current documentation classification / factual note |
|---|---:|---:|---:|---:|---|---|---|---|---:|---:|---:|---|
| `SkaldVaultV1ContainerFormat` | Y | Y | N | via returned models | whole input N; parsed fields Y | serializer Y | N | no integration | Y | N | N | Earlier still-disabled byte-level parser/writer; production-compiled and callable. |
| `SkaldVaultV1ManifestFormat` | Y | Y | N | via returned models | whole input N; parsed fields Y | serializer Y | N | no integration | Y | N | N | Plaintext manifest parser/writer; no storage call site. |
| `SkaldVaultV1HeaderCommitment` | Y | Y | project deterministic | raw outputs | not retained | Y | N | container + KAT | Y | N | N | Direct canonical/HKDF/HMAC building block. |
| `SkaldVaultV1RecordAead` | Y | Y | project deterministic | AAD model Y; wrappers N | wrapper copies key/plain/cipher | wrapper getters Y | N | KAT adapter | Y | N | N | Direct Tink record-AEAD building block. |
| `SkaldVaultV1Argon2idRootDerivation` | N | policy strings | public KAT exercised | root wrapper N | Y | Y | N | KAT harness | Y | N | N | Direct Bouncy Castle Argon2id building block. |
| `SkaldVaultV1CanonicalHeader` | fixture builder | policy strings | N | Y | N | N | N | format/KAT | Y | N | N | Default data-class copy/equality/display semantics are unsafe for arrays. |
| `SkaldVaultV1ExpandedKeys` | N | N | N | N | Y | Y | N | KAT | Y | N | N | Defensive byte copies; default identity display remains unreviewed. |
| `SkaldVaultV1Container` | Y | Y | N | Y | N | selected computed getters only | N | format | Y | N | N | Public mutable nested arrays/lists and generated data-class display. |
| `SkaldVaultV1ContainerRecordEntry` | Y | Y | N | Y | N | N | N | format | Y | N | N | Exposes record ID, ciphertext, metadata, and reference model state. |
| `SkaldVaultV1ContainerManifestSection` | Y | Y | N | Y | N | N | N | format | Y | N | N | Embedded plaintext manifest model. |
| `SkaldVaultV1Manifest` | Y | Y | N | Y | N | N | N | format | Y | N | N | Standalone plaintext manifest model. |
| `SkaldVaultV1ManifestRecordEntry` | Y | Y | N | Y | N | N | N | format | Y | N | N | Plaintext record state/reference model. |
| `SkaldVaultV1RecordAadContext` | Y | Y | N | Y | N | N | N | AEAD/KAT | Y | N | N | AAD inputs exposed as mutable arrays. |
| `SkaldVaultV1RecordCiphertext` | N | N | N | N | Y | Y | N | AEAD/KAT | Y | N | N | Defensive wrapper; no explicit redacted display. |
| `SkaldVaultV1RecordPlaintext` | N | N | N | N | Y | Y | N | AEAD/KAT | Y | N | N | Defensive wrapper retains plaintext privately; no clear/wipe API. |
| `SkaldVaultV1StillDisabledProviderKatHarness` | via default request | Y | Y | request model Y | partial | evidence only | N | no app caller | Y | N | N | Public/callable composite direct-crypto execution despite “still-disabled” name. |
| KAT fixtures/request models | Y | Y | Y | Y | constructor N; two helper replacements Y | N | N | KAT default | Y | N | N | Default display can expose fixed passphrase/vector strings and arrays. |
| `EncryptedVaultParserWriterScaffold` | N | safe labels | N | N | N/A | N/A | Y | policy refs | Y | provider status only | N | Newer-line status model; global absence booleans are not repository inventory. |
| disabled parser scaffold | N | safe labels | N | N | N/A | N/A | object N; results Y | none | Y | N | N | Rejects counts only; not the earlier raw parser. |
| disabled writer scaffold | N | safe labels | N | N | N/A | N/A | object N; results Y | none | Y | N | N | Rejects counts only; not the earlier serializers. |
| `EncryptedVaultWorkingParser` | N | no complete marker fixtures | N | N | request-owned | no raw result | Y | none | Y | N | N | Production-compiled synthetic classifier, no production input source. |
| working-parser request | N | safe labels | N | no getter | Y | N/A | Y | none | Y | N | N | Factory copies caller bytes; parser/result retain no input. |
| working-parser result | N | safe labels | N | N | N/A | N/A | Y | none | Y | N | N | Payload-free synthetic classification result. |
| synthetic catalog | generated test bytes | Y | N | fresh array only | N/A | Y | Y | N | Y | N | N | CommonTest-only newer marker catalog. |
| `VaultCryptoProvider` | N | policy IDs | N | opaque models | N/A | N/A | N/A | type/model refs | Y | Y | Y | Interface does not encompass the earlier direct static path. |
| `DisabledVaultCryptoProvider` | N | safe labels | N | N | N/A | N/A | N | registry constructs | Y | Y | Y | All operations return blocked; default identity display. |
| provider-selection registry | N | safe labels | N | N | N/A | N/A | N | 13 model consumers | Y | Y | Y | Always selects disabled; does not gate direct v1 objects. |
| private commonMain HKDF | N | domain labels supplied | N | output only | no retained input | Y | N/A | public expand callers | Y | N | N | Intermediate arrays are not explicitly cleared. |
| Android/Desktop Argon actuals | N | N | N | output only | Y; local copies cleared | Y | N/A | through direct wrapper | Y | N | N | Four primitive operations per target includes this KDF. |
| Android/Desktop HMAC actuals | N | algorithm name | N | output only | N | Y | N/A | through direct wrapper | Y | N | N | JCA HMAC; no provider routing. |
| Android/Desktop AEAD actuals | transient fixed non-secret key ID | N | N | output only | key copied/cleared; other inputs wrapper-copied | Y | N/A | through direct wrapper | Y | N | N | Transient Tink keyset; no persistence; nonce/tag remain opaque ciphertext. |

“Copies arrays on input/output” is not one repository-wide yes/no property. The matrix records the exact split: byte readers create field copies and defensive wrappers copy, while most public earlier data-class constructors/getters/copies do not.

## Format identity and accepted-byte overlap

### Earlier v1 byte identity

- Vault magic: `SKALD-VAULT-V1`.
- Vault format version: `1`.
- Feature flags: exactly `0`; any other value is rejected as unsupported.
- Container policy: `skald-vault-v1-container-contract-v1`, version `1`.
- Manifest magic: `SKALD-VAULT-V1-MANIFEST`.
- Manifest policy: `skald-vault-v1-manifest-contract-v1`, version `1`.
- Storage policy: `skald-vault-v1-local-manifest-storage-policy-v1`, version `1`.
- Record storage namespace: `skald-vault/v1/local-records`.
- Record namespace: `skald-vault/v1/records`.
- A separate path policy also defines a manifest namespace, `skald-vault/v1/manifests`.

The earlier encodings use strictly ordered unsigned 16-bit big-endian field identifiers. Variable-width strings and byte arrays have unsigned 16-bit big-endian lengths. Integers are explicit big-endian u16/u32/u64 values. Strings must be non-empty printable ASCII. There is no JSON, reflection-based serialization, unknown-field skipping, case folding, normalization, or extension-field preservation.

- Canonical header: fields `1..22`.
- Outer container: fields `1..32`.
- Container record entry: fields `1..6`.
- Embedded container manifest: fields `1..11`.
- Standalone manifest: fields `1..12`.
- Record AAD: fields `1..17`.

Unknown fields, duplicate/out-of-order fields, truncation, and trailing bytes fail closed. The standalone manifest sorts record entries by record ID when serializing and after parsing. The container record list is not similarly canonicalized by sorting.

### Newer synthetic identity

The newer parser recognizes only exact, complete commonTest catalog markers with the lowercase three-byte synthetic prefix, separators, a catalog token, and one discriminator. It is not a reader for the earlier field-ID encoding. Its parser/catalog version remains `1`, but that version identifies a different synthetic classification contract, not the earlier binary format.

### Non-overlap result

The accepted byte languages do not overlap:

- Every earlier container serialization begins with the big-endian encoding of field ID `1`, not the newer lowercase synthetic prefix. The newer classifier therefore rejects bytes emitted by `SkaldVaultV1ContainerFormat`.
- The earlier container and manifest readers require field ID `1` at the start. Every existing newer synthetic catalog marker therefore fails their first field-ID read.
- The desktop audit executes both directions: all 12 existing synthetic markers are rejected by both earlier readers, while representative serialized container and manifest fixtures are rejected by the newer classifier.

This non-overlap is a fact, not a decision that either format should survive.

## Key hierarchy reconciliation

The implemented earlier hierarchy is:

```text
normalized passphrase bytes + salt
        |
        v
Argon2id -> 64-byte root material
        |
        v
HKDF-SHA-256 with the header salt and two purpose labels
        |-- 32-byte header-commitment key
        `-- 32-byte shared record-AEAD key
```

Code-grounded conclusions:

- Argon2id output is used directly as HKDF input keying material.
- HKDF-SHA-256 is implemented in private commonMain code over platform HMAC-SHA-256.
- Only the header-commitment and shared record-AEAD purposes are accepted.
- All record types use the same record-AEAD key; the record context is separated by AAD, not by per-record keys.
- No random vault root key is generated.
- No random vault root key is wrapped.
- There is no implemented key-envelope section or envelope type in the earlier binary format.
- Reserved wrapping/export labels are constants only; unsupported-purpose derivation rejects them.
- There is no wrapping key, metadata-specific key, secret-payload-specific key, backup/export key, or per-record key.
- Passphrase replacement without record reencryption is not supported: changing passphrase-derived root material changes the record key.

That implementation does not match the newer conceptual key-envelope decision. This audit does not select between them.

## Header integrity and authentication

The plaintext canonical header contains:

1. vault magic and format version;
2. provider suite ID;
3. KDF algorithm/version/memory/time/parallelism, salt, and output length;
4. vault ID;
5. passphrase-encoding, key-expansion, and key-separation policy IDs;
6. header-commitment primitive and policy IDs;
7. AAD policy ID/version;
8. record-format policy ID/version;
9. feature flags;
10. integrity-critical header metadata.

`computeHeaderCommitment` calculates HMAC-SHA-256 over exactly those canonical bytes using the HKDF-derived header key. Verification can occur only after the caller obtains the passphrase-derived root or a header key. `SkaldVaultV1ContainerFormat.parse` reconstructs and validates canonical byte evidence, but it does not run HMAC verification.

The outer container stores both a canonical-header byte blob and duplicated scalar/byte header fields. Parsing reconstructs canonical bytes from the duplicated fields and content-compares them with the embedded canonical-header blob. It also cross-checks the embedded manifest vault ID, provider suite, and header-commitment-context value against outer/header values.

The commitment does not cover:

- outer container/manifest/storage policy fields;
- the commitment tag itself;
- record entries or ciphertext;
- record references;
- embedded or standalone manifest bytes;
- manifest sequence, tombstones, or crash metadata;
- outer field framing or record/ciphertext resource lengths.

Structural parser failures can identify malformed input before authentication. For structurally valid input, wrong passphrase/root/key, modified committed header data, and a modified tag all reduce to a Boolean verification failure; the current verification API does not distinguish the cryptographic cause.

Direct record decryption does not require evidence that header commitment verification ran first. Only the commonMain KAT orchestrator imposes that order within its test flow.

## Manifest confidentiality and integrity

There are two plaintext manifest representations:

1. an embedded section inside `SkaldVaultV1Container`; and
2. a separately serializable `SkaldVaultV1Manifest`.

The embedded and standalone layouts are not byte-identical: the standalone form includes feature flags and uses a different final field number. Neither representation calls HMAC or AEAD. Neither is encrypted. Neither is protected merely because it carries a `headerCommitmentContext`; that field stores/cross-checks a tag value, while the tag authenticates only canonical-header bytes. No automatic equality or authentication relationship connects the standalone manifest to the embedded one.

### Field inventory and current sensitivity classification

| Field class | Embedded | Standalone | Protection in current code | Metadata-policy classification |
|---|---:|---:|---|---|
| Manifest magic/policy/version | Y | Y | plaintext structural validation | public format policy |
| Vault ID | Y | Y | plaintext, structurally cross-checked only in embedded form | persistent correlator; human sensitivity decision required |
| Provider suite ID | Y | Y | plaintext | public format policy |
| Header-commitment context | Y | Y | plaintext context value, not manifest authentication | integrity/correlation metadata; not secret but security-sensitive |
| Storage namespace | Y | Y | plaintext | public policy label |
| Record namespace | Y | Y | plaintext | public policy label |
| Manifest sequence | Y | Y | plaintext | state/history metadata; sensitive |
| Crash-recovery metadata | Y | Y | plaintext | explicitly sensitive metadata |
| Feature flags | N | Y | plaintext | public format policy/state |
| Record ID | Y | Y | plaintext | record correlator; sensitive |
| Record type | Y | Y | plaintext | reveals secret/metadata/backup class; sensitive |
| Latest record version/counter | Y | Y | plaintext | state/history metadata; sensitive |
| Record reference | Y | Y | plaintext and not record AAD | storage/linkage metadata; sensitive |
| Tombstone state | Y | Y | plaintext | deletion/history metadata; sensitive |

Current Skald policy treats wallet labels, transaction notes, backup state, history, credentials, and other wallet metadata as sensitive and requires encrypted persistence. Even without a storage call site, the plaintext manifest representation conflicts with any claim that these representations are ready to persist sensitive metadata.

The standalone manifest rejects duplicate record IDs and conflicting counters. The embedded container validator verifies counts and each state’s correspondence to a record, but it does not establish uniqueness of embedded manifest-state IDs. A repeated state for one record can satisfy the count while another record is omitted. This is an existing safety gap requiring human review; it is not corrected here.

## Record AEAD reconciliation

- One shared record key is derived for all records.
- Tink XChaCha20-Poly1305 performs randomized encryption. The Skald wrapper exposes no nonce parameter, and Tink selects nonce material internally.
- The actual uses `NO_PREFIX` and constructs a transient in-memory keyset/primitive from caller key material for each operation. No Tink keyset is persisted and no random key is generated.
- Ciphertext is treated as opaque. Skald code does not separately encode or parse a nonce or tag envelope.
- AAD authenticates 17 fields: vault magic/version/provider/vault ID; record-format and AAD policy IDs/versions; key-expansion, HMAC primitive, and header-commitment policies; commitment context; record type, ID, version counter, and integrity metadata; and storage namespace.
- AAD does not include record reference, tombstone state, manifest sequence/policy, container policy, or ciphertext length.
- The container exposes record type, record ID, version counter, integrity metadata, reference, and ciphertext framing outside encrypted payload. Reference is not AAD-authenticated.
- The container fixture ciphertext is a placeholder; it is not produced or authenticated by the AEAD path.

The authenticated version counter plus the standalone manifest decision can reject a lower counter relative to a separately trusted current manifest. That is only manifest-relative stale-record detection. No integrated load path enforces it, and restoring an older consistent container/manifest state is not prevented by any external monotonic anchor. Whole-file rollback protection remains false.

## Production reachability

| Operation | Production-compiled | Calls outside owning implementation | App integration calls | Test calls after audit |
|---|---:|---:|---:|---:|
| Container serialize | Y | 0 | 0 | 9 |
| Container parse | Y | 0 | 0 | 17 |
| Manifest serialize | Y | 0 | 0 | 8 |
| Manifest parse | Y | 0 | 0 | 20 |
| Header expand root | Y | 1, commonMain KAT | 0 | 6 |
| Header compute commitment | Y | 0 | 0 | 4 |
| Header verify commitment | Y | 1, commonMain KAT | 0 | 10 |
| Record encrypt | Y | 1, commonMain KAT adapter | 0 | 3 |
| Record decrypt | Y | 1, commonMain KAT adapter | 0 | 3 |
| Synthetic parser parse | Y | 0 | 0 | 20 |
| Argon2id derive | Y | 1, commonMain KAT | 0 | 4 |
| Full KAT run | Y | no production caller | 0 | 6 |

The 100 direct test-call total covers the exact named list in the desktop audit (all rows except Argon2id and full KAT). One additional direct test call targets `expandRootMaterialForPurpose`. Counts are static call-expression occurrences, not runtime invocation counts within loops.

No registry entry, parser/format factory, dispatcher, app service, repository, storage adapter, UI action, sync path, backend path, settings source, database source, network source, descriptor source, or wallet-data source invokes the container/manifest/newer parser operations. The production-compiled KAT harness is the only non-owning production graph that invokes the earlier direct cryptographic objects; it has no app caller but is public and test-callable.

## Fixture placement

Fixed non-wallet fixture/vector material exists in production commonMain. Without recording its byte values, the inventory is:

- canonical-header salt, vault ID, and header builder;
- container commitment tag, record ID, record type/reference, ciphertext placeholder, record metadata, manifest context, and crash metadata;
- standalone manifest context, vault/record IDs, record references/types/counters/tombstones, sequence, and crash metadata;
- record-AEAD key, plaintext, vault/record IDs, commitment context, and record metadata;
- commonMain KAT passphrase, Argon2id root expectation, HKDF info/key expectations, canonical header expectation, HMAC tag expectation, strict AAD expectation, and request builder.

The core fixture getters/builders generally return new arrays, and the material is labeled fixed/public/non-wallet. It is nevertheless production-compiled, public API material reachable by app code. That placement is inconsistent with a global reading of the later test-source-only vector policy.

The later 12-marker synthetic catalog remains in commonTest only. Its complete marker strings/fixture arrays were not copied into commonMain, androidMain, or desktopMain. The correct distinction is therefore:

- production commonMain earlier-v1 fixture/vector material: present;
- newer synthetic marker catalog in production: absent.

No fixture is moved or removed by this audit.

## Resource limits and allocation behavior

| Surface | Limit in current code | Status note |
|---|---:|---|
| Variable field payload | 65,535 bytes from u16 framing | Implicit per-field bound; not a named total-input policy |
| Vault ID | 16 bytes | Exact |
| Record ID | 16 bytes | Exact |
| Header commitment/tag/context | 32 bytes | Exact |
| Salt in header/container | 16–64 bytes | Header validation bound |
| Header integrity metadata | 1,024 bytes | Exact maximum |
| Container pre-unlock metadata | 1,024 bytes | Same header metadata carried outside |
| Record integrity metadata | 1,024 bytes | Container and AAD maximum |
| Manifest crash metadata | 1,024 bytes | Exact maximum |
| Container record ciphertext | 1–4,096 bytes | Enforced only through container validation |
| Record reference | 1–128 encoded bytes | Container/manifest maximum |
| Record count | 8 | Container and manifest maximum |
| Storage safe path segment | 96 characters/bytes under its policy | Separate model-only path policy |
| Canonical Argon2id memory | 65,536 KiB | Header/container require exactly this value |
| Canonical Argon2id passes | 3 | Header/container require exactly this value |
| Canonical Argon2id parallelism | 1 | Exact |
| Argon2id output/root | 64 bytes | Exact |
| Derived header key | 32 bytes | Exact |
| Derived record key | 32 bytes | Exact |
| Direct record plaintext/ciphertext API | no public bound | Container bound does not protect direct calls |
| Total container input | no named total bound | Each expected field is bounded by framing; caller may still provide large trailing input |
| Total manifest input | no named total bound | Same |
| Argon2id maximum memory/passes | none | Public derive accepts values above floors |
| Argon2id maximum salt/passphrase bytes | none | Only non-empty passphrase and minimum salt are enforced |
| Storage layout `recordIds` input | no count bound | Model surface only; separate from format maximum |
| Recursion/nesting | no recursive grammar | Fixed nested container/record/manifest depth |

The byte readers allocate `copyOfRange` arrays for length-prefixed fields and allocate nested section/entry arrays. Fixed record counts and u16 lengths bound obvious nested amplification, but no reviewed total input budget or streaming policy exists. Direct AEAD and Argon2id surfaces have broader resource exposure than the container format.

There is also a format/policy mismatch: header/container validation requires the exact canonical KDF parameters above, while production-provider acceptance language allows a stronger calibrated desktop choice. Such a stronger value cannot currently serialize as this v1 header without rejection. This audit does not resolve whether the format, calibration policy, or versioning model should change.

## Array ownership, equality, copying, and redaction

### Defensive-copy surfaces

- `SkaldVaultV1ExpandedKeys` copies both keys on construction and every getter.
- `SkaldVaultV1RecordCiphertext` and `SkaldVaultV1RecordPlaintext` copy on construction and getter.
- `SkaldVaultV1RootMaterial` and normalized-passphrase wrappers copy their material.
- Container/manifest byte readers copy parsed field slices, so parsed models do not alias the caller’s original parse input.
- The newer working-parser request copies caller input and its result/diagnostics contain no byte arrays.

### Mutable-reference surfaces

The following earlier public data classes accept arrays without constructor copies and expose those same mutable references through public properties: canonical header; container record/manifest-state/manifest-section/container; standalone manifest record/manifest/candidate; record AAD context; and the KAT request. Nested lists are also not defensively copied.

Consequences:

- caller mutation can alter a model after validation/construction;
- generated data-class `copy()` is shallow and aliases arrays/lists unless every value is replaced;
- generated equality/hash behavior for arrays is reference-based rather than content-semantic;
- default generated data-class display uses array-content rendering on the JVM and also prints string metadata such as record references, record types, policies, and KAT expectations;
- accepted-result data classes can transitively display their accepted values;
- the KAT request/expected-vector data classes can display the fixed passphrase and expected-vector strings;
- non-data wrappers and singleton objects without overrides use identity-style display rather than a reviewed redacted contract.

The earlier core format/header/AEAD objects and models do not explicitly redact `toString()`. Fixed fixture getters return fresh arrays, but public callers can still obtain their contents. Raw plaintext and ciphertext getters exist on defensive wrappers and return copies; those copies remain raw material owned by the caller. No wrapper clear/dispose operation is present.

The newer working-parser object, request, result, and diagnostics do explicitly redact display, and its result retains no input. That narrower result does not establish safety for the earlier data models.

No redaction or ownership behavior is patched in this branch. Required human review must define which earlier types may remain public, which must copy, which require content equality, which require explicit redaction, and which raw getters are acceptable.

## Provider-boundary consistency

`VaultCryptoProvider` is a production-compiled interface, and `VaultCryptoProviderSelectionRegistry` has 13 production-source `select()` consumers. Those consumers build policy/readiness/admission evidence. The registry always selects `DisabledVaultCryptoProvider`, `productionProviderSelectable=false`, and no production code invokes a selected provider’s crypto methods.

That disabled selection does not block the earlier direct line:

- Argon2id calls its platform actual directly.
- private commonMain HKDF calls the platform HMAC actual directly.
- header compute/verify calls the platform HMAC actual directly.
- record encrypt/decrypt calls the platform Tink actual directly.
- the commonMain KAT harness composes all of those operations without referencing `VaultCryptoProvider`, the provider-operation authorization boundary, or the selection registry.

Therefore all of these statements are simultaneously true:

- no selectable production provider exists;
- no app/repository/storage call site invokes vault crypto;
- direct production-compiled crypto entry points exist;
- a production-compiled public KAT harness can execute them;
- desktop and Android tests execute that commonMain harness;
- provider selection is not the only current crypto execution boundary.

The production-compiled KAT harness name and evidence fields call it “still-disabled,” but the method body is executable. Its effective restriction is absence of an app caller, not enforcement by provider selection.

## Documentation statement reconciliation

Historical statements are not rewritten. The classification below records how they must be read now.

| Statement | Documents/surfaces | Original scope | Current factual status | Required clarification |
|---|---|---|---|---|
| “No parser exists.” | Newer admission/scaffold/audit docs; README status | Often a named pass or newer chain | False globally; two earlier raw readers and one newer classifier exist | Say no production file/storage/app parsing call site |
| “No writer exists.” | Newer parser docs | Newer chain / pass | False globally; two earlier serializers exist | Say no newer writer and no integrated writer/storage path |
| “No serialization exists.” | README and newer scaffold/audit wording | Sometimes written globally | False globally | Scope to the newer chain or pass |
| “No parsing exists.” | Same | Sometimes written globally | False globally | Scope to app integration when that is the intended claim |
| “No production crypto execution exists.” | Design/readiness/provider history | Candidate/provider-selection context | Ambiguous/contradictory: compiled direct execution and KAT exist; app invocation does not | Distinguish compiled/callable, test-invoked, and app-invoked |
| “Provider selection remains disabled.” | Provider boundary/selection/readiness docs | Global selection registry | Accurate | Add that direct v1 objects bypass selection |
| “No production vector bytes exist.” | Newer catalog/parser docs and README | Newer marker policy, sometimes unqualified | False globally; accurate for newer synthetic markers only | Name the catalog/policy being scoped |
| “Canonical binary layout remains unresolved.” | Newer format/parser docs | Newer conceptual line | Contradictory globally | Say canonical ownership between two v1 meanings is unresolved |
| “The v1 container parser/writer exists.” | Container/manifest contract, provider docs | Earlier line | Accurate | Pair with no app/storage integration |
| “The manifest parser/writer exists.” | Container/manifest contract | Earlier line | Accurate | State plaintext/unauthenticated status |
| “The provider boundary is the only crypto boundary.” | Implied by some architecture language | Global implication | Not established/false | Registry is only selectable-provider surface; direct primitives also exist |
| “The format has a key-envelope section.” | Newer container-format decision/design | Conceptual future section | Not implemented in earlier byte line | Mark as unresolved design decision |
| “Storage is absent.” | Newer/readiness/sync/storage docs | Usually production persistence | Accurate only for integrated persistence/file I/O | Models, namespaces, manifests, and simulators already exist |

Examples requiring historical/pass-specific reading include the dependency review, KAT validation, libsodium comparison, parser/writer admission sequence, initial working-parser audit, and strict-correction document. They accurately record what each pass added or did not add. They are not a reliable global inventory when read without their pass qualifiers.

The existing working-parser validation report also hardcodes repository-wide-looking negatives such as `realVaultFormatParserPresent=false`, `canonicalBinaryLayoutImplemented=false`, and `vaultContainerSerializationPresent=false`. Those values are true only within the newer narrow report’s evidence line; they are factually false as a whole-repository inventory. This branch does not change that commonTest report.

## Source-guard coverage and gaps

Existing source guards do prove or constrain:

- newer working-parser external production call/registry/factory/service/repository/storage/UI absence;
- earlier container/manifest parser-writer placement in approved files;
- no file I/O or storage integration in those building blocks;
- crypto imports/primitives confined to allowlisted direct building-block/platform/KAT files;
- no Tink keyset persistence or random key generation in record AEAD;
- no selected production provider and no provider crypto call through the registry;
- commonTest synthetic catalog confinement and absence of its complete markers from production source.

They do not prove:

1. mandatory routing through `VaultCryptoProvider`;
2. absence of direct static crypto entry points;
3. stable parser, serializer, or crypto entry-point counts;
4. commonMain fixture absence—fixed material is intentionally allowlisted/present;
5. manifest confidentiality or authentication;
6. header commitment coverage of manifest/records/references;
7. array ownership, content equality, shallow-copy safety, or redacted display;
8. total parser input/resource budgets;
9. absence of a new wrapper around `encryptRecord`/`decryptRecord`, because some patterns look only for platform `.encrypt(`/`.decrypt(`;
10. all direct root-expansion calls, because the HKDF guard does not count every public expansion wrapper;
11. alignment between stronger KDF calibration values and exact v1 header acceptance.

Some existing guards explicitly allow the commonMain KAT harness and direct crypto files, and one harness guard forbids a registry reference. They preserve the current bypass rather than reconcile it. This audit strengthens factual inventory coverage without weakening those historical guards.

## Blocking human decisions

Inventory is complete for this pass. Reconciliation is not complete. Human decisions are required for:

1. Which artifact owns the canonical meaning of “Skald Vault v1.”
2. Whether `SkaldVaultV1ContainerFormat` remains, is revised/versioned later, or is eventually deprecated.
3. Whether `SkaldVaultV1ManifestFormat` remains separate, becomes embedded-only, or changes protection/placement.
4. Whether `EncryptedVaultWorkingParser` remains a synthetic admission tool, becomes related to a future real parser, or is renamed/deprecated later.
5. Whether the canonical key hierarchy uses direct passphrase-derived root material or a random wrapped vault root/key envelope.
6. Whether passphrase rewrapping without record reencryption is required.
7. Whether all crypto must flow through `VaultCryptoProvider` and provider-operation authorization, or direct reviewed primitives remain permitted.
8. Whether the production-compiled KAT harness and fixed commonMain fixtures are acceptable production artifacts.
9. Whether manifests are encrypted, authenticated plaintext, included under header authentication, protected as records, or redesigned.
10. Which manifest identifiers/references/state fields are sensitive and what may be visible before unlock.
11. Whether one shared record key is acceptable or class/per-record derivation is required.
12. Whether Tink’s opaque nonce/tag ciphertext remains canonical or an explicit record envelope owns nonce/tag framing.
13. Which record/container/manifest/Argon2id/resource limits are canonical, and what total input/streaming policy applies.
14. How array ownership, defensive copying, equality/hash semantics, raw getters, clearing, and `toString()` redaction must work.
15. How embedded manifest duplicate-state handling and rollback anchoring are resolved.
16. How exact KDF values coexist with calibration/stronger-parameter policy.
17. What versioning and migration rules apply if the surviving design changes the existing byte layout.
18. What naming, compatibility, deprecation, and removal strategy applies after architecture selection.

## Audit outcome

The outcome remains `INVENTORY_COMPLETE_DESIGN_DECISION_REQUIRED`.

- Production source and behavior were not changed.
- No parser, serializer, KDF, HMAC, AEAD, fixture, provider, format, or resource-limit semantics were changed.
- No fixture was moved.
- No artifact was selected, deprecated, renamed, or deleted.
- No parser, writer, registry, factory, dispatcher, service, repository, storage adapter, or UI integration was added.
- No file I/O, persistence, secure-storage success, secure-metadata success, production sync, provider selection, signing, broadcasting, endpoint, or mainnet path was added.
- The production provider remains `DisabledVaultCryptoProvider` only and `productionProviderSelectable=false`.
- All overlaps, contradictions, safety gaps, and naming/version questions above require human review before further format, parser, writer, crypto, storage, or persistence work.

## Human resolution — 2026-07-12

The historical audit outcome remains `INVENTORY_COMPLETE_DESIGN_DECISION_REQUIRED`. This audit itself did not select an architecture: production behavior was not changed, and no canonical architecture was selected in the audit pass.

The audit's requested human decision is now resolved by [`ENCRYPTED_LOCAL_VAULT_V1_CANONICAL_ARCHITECTURE_DECISION.md`](ENCRYPTED_LOCAL_VAULT_V1_CANONICAL_ARCHITECTURE_DECISION.md), with status `CANONICAL_ARCHITECTURE_SELECTED_IMPLEMENTATION_BLOCKED`. That later decision classifies the earlier `SkaldVaultV1*` line as unsupported prototype P0, the working parser as synthetic-test-contract-only, and neither line as the canonical storage implementation. It records the approved architecture without changing any audited production artifact or authorizing implementation.
