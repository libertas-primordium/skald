# Canonical v1 format specification

Status: `CANONICAL_V1_SPECIFIED_IMPLEMENTATION_BLOCKED`. Decision revision **2**, recorded **2026-10-11**; first supported persisted **format version 1**. No supported canonical vault has shipped. This is normative documentation, not an executable codec, provider, vector result or production authorization.

This document is the single normative registry for numeric IDs, octet layouts, domains, bounds, credential profile and authentication transcripts. The [security amendment](ENCRYPTED_LOCAL_VAULT_CANONICAL_V1_SECURITY_AMENDMENT.md) owns authority and rationale; the [provider contract](ENCRYPTED_LOCAL_VAULT_CANONICAL_V1_PROVIDER_CONTRACT.md) owns capability/operation semantics; the [acceptance plan](ENCRYPTED_LOCAL_VAULT_CANONICAL_V1_ACCEPTANCE_PLAN.md) owns future test obligations. A discrepancy must be resolved explicitly, never by choosing a convenient historical implementation.

## 1. Encoding and registry conventions

MUST and MUST NOT identify mandatory requirements of this application profile. All lengths are octets unless explicitly stated otherwise. `||` concatenates octets. `BE16`, `BE32`, `BE64` are fixed-width **unsigned big-endian** integer encodings. Wire counters are BE64 but supported counter values are **1..9,223,372,036,854,775,807**; reject zero and larger values wherever a positive counter is required. Arithmetic, multiplication, conversion, allocation and slicing MUST be checked before use. A declared length grants no allocation/work beyond actual enclosing input and all applicable caps.

```text
LP(x)       = BE32(byteLength(x)) || x
D(label)    = LP(exact ASCII octets of label)
TLV(id,x)   = BE16(id) || BE32(byteLength(x)) || x
FH(id,n)    = BE16(id) || BE32(n)
outer frame = magic[8] || BE16(1) || BE32(bodyLength) || body
```

`FH` is six octets; the outer prefix is fourteen. `bodyLength` excludes the prefix and MUST equal exactly all remaining octets. No native layout, signed varint, JSON, CBOR, implicit string encoding or NUL termination is used. Domains are exact, case-sensitive ASCII, with no whitespace or implicit suffix. The explicit zero byte in each magic is part of its eight-octet value, not a general string terminator.

All listed fields are mandatory exactly once in strictly increasing numeric ID order. Reject unknown/missing/duplicate/out-of-order fields, incorrect lengths, unknown versions/IDs, surplus nested data and trailing bytes. There are no optional extensions or feature flags. The only fixed nesting is outer frame → TLVs → decrypted manifest entry array → entry TLVs; no recursive generic object parser is permitted. Manifest plaintext itself has no extra outer frame/magic. Record payload is opaque, not recursively parsed by the vault.

| Registry | Supported value / exact meaning |
| --- | --- |
| Format version | 1; decision revision 2 is not an on-wire format version |
| Suite ID / suite version | 1 / 1; selects the whole algorithm, credential-domain and framing profile here |
| KDF ID | 1 = Argon2id; this registry ID is not Argon2's internal algorithm-type integer |
| KDF version | Decimal 19, encoded BE16 `00 13`; Argon2 algorithm version v1.3 |
| Credential profile ID | 1, defined in §10 |
| Root key-envelope version | 1 |
| Manifest schema | 1 |
| Record schema | 1 = opaque application-owned bytes, for either supported class |
| Record class | One octet: 1 = metadata; 2 = secret |
| Entry state | One octet: 0 = active; 1 = tombstone |
| Header magic | `53 4b 4c 44 48 44 52 00` = ASCII `SKLDHDR` plus zero |
| Manifest magic | `53 4b 4c 44 4d 4e 46 00` = ASCII `SKLDMNF` plus zero |
| Record magic | `53 4b 4c 44 52 45 43 00` = ASCII `SKLDREC` plus zero |

Unknown values MUST NOT be negotiated, guessed, downgraded or interpreted as generic future content. These are new canonical magic bytes. There is no P0 detection/fallback/migration in canonical decoding. `PRE_RELEASE_PROTOTYPE_P0_UNSUPPORTED` evidence remains test-only and has no migration promise.

Stable identity context:

```text
I = BE16(1) || BE16(suiteId) || BE16(suiteVersion) || vaultId[16]
```

`I` is exactly **22 octets**: format, suite identity and stable vault identity. It contains no credential-dependent field.

## 2. Randomness and allocation bounds

All vault roots, salts, nonces, IDs and references use the approved Skald provider randomness path. Every encryption obtains a fresh random 192-bit nonce; this provides probabilistic collision resistance, not an absolute global uniqueness proof. No deliberate reuse, timestamp/counter fallback, seeded test RNG, or weaker-provider retry is permitted. RNG failure fails the operation. An all-zero random salt or nonce is not independently invalid; its byte pattern cannot establish RNG quality.

Vault IDs, record IDs and active storage references are 16 random octets and nonzero. Each allocation permits at most **eight draws**. Reject zero; for record IDs, reject collision with any retained ID (including tombstones) or transaction-reserved ID. For storage references, reject collision with any live, staged, transaction-reserved or existing artifact in the authorized namespace. Exhaustion fails closed without a deterministic substitute. Vault ID allocation needs a nonzero check, not a global registry. A record reference is independently generated, not derived from its record ID. Future storage must provide trustworthy bounded collision/reservation information; this grants the provider no filesystem access. Canceled/failed sealing abandons its allocated reference/nonce and retry allocates anew.

All-zero digest values are reserved sentinels where §8–9 specify. A genuine digest equal to the sentinel cannot be encoded as an active digest or a noninitial predecessor commitment. Fail before commit. A record/manifest artifact can be freshly resealed only under valid authorization with new randomness; an immutable authoritative predecessor cannot be altered to escape a sentinel collision. No unbounded retry loop or alternate hash is authorized.

## 3. Public header

Exactly fifteen TLVs comprise the header body:

| Field ID | Value | Exact value size / constraint |
| ---: | --- | --- |
| 1 | Suite ID | BE16 = 1 |
| 2 | Suite version | BE16 = 1 |
| 3 | KDF ID | BE16 = 1 |
| 4 | KDF version | BE16 = 19 |
| 5 | Argon2 memory KiB | BE32; §4 |
| 6 | Argon2 passes | BE32; §4 |
| 7 | Argon2 lanes | BE32; §4 |
| 8 | KDF salt | 16..64; new creation/credential rewrap uses 32 |
| 9 | Vault ID | 16, random and nonzero |
| 10 | Credential profile ID | BE16 = 1 |
| 11 | Root key-envelope version | BE16 = 1 |
| 12 | Root-wrap nonce | 24 |
| 13 | Wrapped-root ciphertext C | 32 |
| 14 | Wrapped-root AEAD tag T | 16 |
| 15 | Root-envelope commitment | 32, complete HMAC-SHA-256 output |

No wallet names, record IDs/classes/references, credentials, passphrase length/strength/verifier shortcut, recovery secrets, addresses, labels, network metadata or extension blob appears in this public header. Vault identity and costs/salt are public; wrap fields are ciphertext/authentication material. Every structural field and framing length participates in authentication as specified below.

Let `s` be salt length. Fixed value bytes excluding salt total **144**; fifteen field headers total **90**. Therefore:

```text
bodyLength        = 234 + s
totalHeaderLength = 14 + bodyLength = 248 + s
minimum           = 264 (s=16)
maximum           = 312 (s=64)
new header        = 280 (s=32)
```

The historical **65,536-octet** outer header allocation cap remains a preliminary guard; exact v1 schema/lengths are stricter. It permits no extended/oversized valid v1 header. Salt, IDs and nonces are raw octets, never textual encodings.

### Exact H ranges and structural coverage

Offsets below are zero-based half-open ranges in the complete header, with `s` as above. TLVs 1..7 end at offset 76; field 8 header is `[76,82)`, salt `[82,82+s)`. Fields 9..12 then end at `150+s`.

| Region | Header range | Included in H? |
| --- | --- | --- |
| Outer prefix and complete TLVs 1..12 | `[0,150+s)` | Yes |
| FH(13,32) | `[150+s,156+s)` | Yes |
| C | `[156+s,188+s)` | No; bound by commitment |
| FH(14,16) | `[188+s,194+s)` | Yes |
| T | `[194+s,210+s)` | No; bound by commitment |
| FH(15,32) | `[210+s,216+s)` | Yes |
| Commitment value | `[216+s,248+s)` | No; checked separately |

```text
H = complete outer prefix containing FINAL bodyLength
    || TLV(1,...) || ... || TLV(12,...)
    || FH(13,32) || FH(14,16) || FH(15,32)
  = header[0 : 156+s] || header[188+s : 194+s] || header[210+s : 216+s]
length(H) = totalHeaderLength - 80 = 168 + s
```

Thus H is 184..232 octets, or **200** with the new salt length. Use exact canonical ranges or reconstruction from strictly validated fields with this sole encoding. No encode-then-zero reconstruction is permitted. There are no unauthenticated framing bytes: the omitted 80 octets are only C/T/commitment values, not their field headers.

## 4. KDF structure, security admission and work admission

| Parameter | Structurally representable | Eligible suite-1 provider execution |
| --- | --- | --- |
| Total memory KiB | 8,192..262,144 | At least 65,536, within structural maximum |
| Passes | 1..10 | At least 3, within structural maximum |
| Lanes | 1..4 | Exactly 1 for create, open and rewrap |
| Salt | 16..64 octets | Use eligible stored bytes exactly; new salt length from §3 |
| Output | Exactly 32 octets | Exactly 32-octet KEK |

Also require `memoryKiB >= 8*lanes` and divisibility by `4*lanes`. Validate all ranges, divisibility and arithmetic before any KDF allocation/execution. Memory is total memory, not per-lane memory. These conditions prevent distinct encoded costs collapsing through the library's memory rounding. The outer wire version is BE16; the reviewed algorithm receives Argon2's specified v1.3 parameter, not a replacement wire layout. [RFC 9106 §3.1](https://www.rfc-editor.org/rfc/rfc9106.html#section-3.1), September 2021, checked 2026-10-11.

A parseable below-floor header or lanes 2..4 header is ineligible for supported execution. Never repair its fields or retry with weakened parameters, different salt/lanes, altered credential, P0 root length or another provider. The floor tuple is a measurement reference, not a universal approved deployment setting. Future accepted calibration selects eligible creation parameters; a resource-constrained device must refuse rather than weaken the floor. Historical P0 measurements do not approve the new KEK/wrap pipeline.

At most **one active canonical KDF per process**, across all provider instances/aliases. No unbounded internal queue. Concurrent attempts return `ResourceUnavailable`. Cancellation retains the permit until the actual worker stops and temporary material is best-effort cleared. Scheduling threads may differ only if encoded lanes and derived bytes do not change. Work/resource admission must precede expensive work; no implementation is supplied here.

## 5. Root wrapping and separated-key commitment

Profile 1 credential bytes (§10), exact admitted costs and salt feed Argon2id. Both optional Argon2 secret and associated-data inputs are empty octet strings. The KEK is exactly 32 octets and never exported. Use RFC 5869 HKDF-SHA-256 with explicit extract and expand:

```text
WrapPRK = HKDF-Extract(salt=vaultId[16], IKM=KEK[32])
Kwrap   = HKDF-Expand(WrapPRK, D("skald/v1/wrap-aead-key") || I, 32)
Kcommit = HKDF-Expand(WrapPRK, D("skald/v1/wrap-commit-key") || I, 32)
wrapAAD = D("skald/v1/wrap-aad") || LP(H)

(C[32], T[16]) = XChaCha20-Poly1305-Seal(
    key=Kwrap, nonce=header.field12, plaintext=vaultRoot[32], aad=wrapAAD)

commitInput = D("skald/v1/wrap-commit") || LP(H) || LP(C) || LP(T)
commitment  = HMAC-SHA-256(Kcommit, commitInput)
```

The root is independently random, not derived from the credential. Store detached C/T in fields 13/14 and all 32 commitment octets in field 15. A combined-mode library must split exactly `C||T`; no extra framing or nonce belongs in those fields.

All final lengths are known before encryption. H omits C/T/commitment values; root AEAD authenticates H, then HMAC binds H plus C/T. HMAC does not include its own value. The excluded commitment is compared, not ignored. This is non-circular. Distinct HKDF info values separate wrap and commitment keys. The quarantined P0 header encoding, 64-byte root and header-commitment helpers are neither an implementation nor vectors for this construction.

Open order is mandatory:

1. Bounded structural, version/profile, cost/security and resource admission.
2. Profile validation and admitted Argon2id execution; derive separated keys.
3. Reconstruct exact H/commitInput and compute all commitment octets.
4. Constant-time full-tag comparison. On mismatch, **do not invoke root AEAD open**.
5. Only on match, root AEAD open with the exact nonce/wrapAAD; require successful authentication and exactly 32 root octets. Failed plaintext never escapes or becomes a capability.
6. Root authentication grants only an authenticated root capability. The current authoritative manifest must authenticate and validate before session authority; no unlocked session follows from a commitment/root success alone.

For a valid-profile credential, wrong credential, commitment mismatch and root AEAD failure all return public `AuthenticationFailed`, without failed-stage/tag/hash/plaintext detail. No fast pre-Argon verifier exists. Malformed public input can fail earlier; identical overall timing is not claimed.

The commitment requirement applies at this root-envelope boundary. Unmodified XChaCha20-Poly1305 is not claimed key-committing, records are not claimed independently committing AEADs, and 32 tag octets do not prove 256-bit commitment security. This exact application profile requires dedicated cryptographic acceptance before production; see the [amendment](ENCRYPTED_LOCAL_VAULT_CANONICAL_V1_SECURITY_AMENDMENT.md).

## 6. Stable content key hierarchy and domain registry

After root authentication:

```text
RootPRK = HKDF-Extract(salt=vaultId[16], IKM=vaultRoot[32])
Mroot   = HKDF-Expand(RootPRK, D("skald/v1/manifest-root") || I, 32)
Droot   = HKDF-Expand(RootPRK, D("skald/v1/metadata-root") || I, 32)
Sroot   = HKDF-Expand(RootPRK, D("skald/v1/secret-root") || I, 32)

R = class[1] || recordId[16] || BE32(schema) || BE64(recordVersion) || storageRef[16]
Kmanifest = HKDF-Expand(Mroot, D("skald/v1/manifest-key") || I || manifestNonce[24], 32)
Krecord   = HKDF-Expand(selectedRecordRoot, D("skald/v1/record-key") || I || R || recordNonce[24], 32)
```

`R` is exactly **45 octets**. Class 1 selects Droot; class 2 selects Sroot; no other class is accepted. Mroot/Droot/Sroot are HKDF-Expand keys for purpose-specific keys, not direct AEAD keys. Root, KEK, derivation roots, wrap/commitment/manifest/record keys are distinct purposes. Backup/export must use a separate future hierarchy. The exact fresh nonce is included both in derivation and AEAD; this does not authorize reuse or replace RNG requirements.

This complete domain registry is exhaustive; labels are not caller input:

| Exact ASCII label | Purpose |
| --- | --- |
| `skald/v1/wrap-aead-key` | KEK-derived root AEAD key |
| `skald/v1/wrap-commit-key` | KEK-derived outer commitment key |
| `skald/v1/wrap-aad` | Root AEAD additional-data framing |
| `skald/v1/wrap-commit` | Root-envelope HMAC transcript |
| `skald/v1/manifest-root` | Root-derived manifest derivation root |
| `skald/v1/metadata-root` | Root-derived metadata derivation root |
| `skald/v1/secret-root` | Root-derived secret derivation root |
| `skald/v1/manifest-key` | Per-manifest AEAD key |
| `skald/v1/record-key` | Per-record/context/nonce AEAD key |
| `skald/v1/manifest-aad` | Manifest AEAD context |
| `skald/v1/record-aad` | Record AEAD context |
| `skald/v1/previous-manifest` | Exact encrypted manifest artifact digest, also snapshot capability identity |
| `skald/v1/record-artifact` | Exact encrypted record artifact digest |

Each D value uses §1 LP framing; no purpose reuses another label. Fixed contexts are I = 22, R = 45, H = 168 + saltLength, E = 56 octets. HKDF is RFC 5869 §§2.2–2.3/3.2 (May 2010), using SHA-256 throughout; no platform-default hash or info encoding. Expanded roots used as later Expand keys are this Skald hierarchy, not an API permission to derive arbitrary keys. [HKDF definition](https://www.rfc-editor.org/rfc/rfc5869.html#section-2.2), checked 2026-10-11.

Content key/AAD excludes credential bytes, KDF salt/costs, credential profile, wrap nonce/C/T and commitment. Rewrap preserves vault ID/root and exact manifest/record bytes. Changing suite/root is a separately designed rekey/migration, not credential rewrap. An older valid header plus its older credential still unlocks the same root; credential rotation cannot revoke that copy or promise flash/journal erasure.

## 7. Manifest and record public envelopes

Each uses its distinct magic and exactly three TLVs:

| ID | Value | Size |
| ---: | --- | --- |
| 1 | Nonce | 24 |
| 2 | Ciphertext | n |
| 3 | AEAD tag | 16 |

The prefix 14 + field headers 18 + nonce/tag 40 gives exactly **72 octets overhead**, with `bodyLength=58+n` and whole size `72+n`. XChaCha does not pad; n equals plaintext length. No public ID, class, schema, record version/reference, label or generation is added.

```text
E = complete outer prefix containing FINAL bodyLength
    || TLV(1,nonce[24]) || FH(2,n) || FH(3,16)
  = envelope[0:50] || envelope[50+n:56+n]
length(E) = 56
manifestAAD = D("skald/v1/manifest-aad") || I || BE16(1) || LP(E)
recordAAD   = D("skald/v1/record-aad") || I || R || LP(E)
```

Ciphertext occupies `[50,50+n)`; tag occupies `[56+n,72+n)`. Every framing byte and nonce enters E. Manifest derivation/AAD does not require encrypted generation, avoiding an unlock bootstrap cycle. Record context comes only from an authenticated manifest capability and current-snapshot authorization, never filenames or an untrusted envelope assertion. Validate exact artifact length/digest/context before releasing plaintext; provider-owned construction prevents a caller minting trusted context through a public data class.

Record n is **0..4,194,304**, inclusive: a zero-length opaque payload is valid. Whole record size is **72..4,194,376**. Whole encrypted manifest outer cap is **16,777,216**, yielding general envelope ciphertext cap **16,777,144**, but §8's exact schema is stricter. Enforce all enclosing/schema limits without allocating to an attacker-supplied cap. Combined versus detached library APIs cannot change serialization.

## 8. Authoritative manifest plaintext

Exactly five TLVs, without extra magic:

| ID | Value | Size / constraint |
| ---: | --- | --- |
| 1 | Vault ID | 16; equals authenticated header stable ID |
| 2 | Manifest schema | BE16 = 1 |
| 3 | Generation | BE64 positive supported counter |
| 4 | Previous-manifest commitment | 32; §9 |
| 5 | Record-entry array | Defined below |

```text
array = BE32(entryCount) || (BE32(entryByteLength) || entryBytes) repeated entryCount times
```

entryCount is **0..65,536**. Every entry has exactly eight TLVs:

| ID | Value | Size / constraint |
| ---: | --- | --- |
| 1 | Record ID | 16 nonzero octets |
| 2 | Record class | One octet, registered class |
| 3 | Record schema | BE32 = 1 |
| 4 | Record version | BE64 positive supported counter |
| 5 | State | One octet, registered state |
| 6 | Opaque storage reference | 16 |
| 7 | Whole encrypted record artifact length | BE32 |
| 8 | Whole encrypted record artifact digest | 32 |

Entry values total 82; headers 48; `entryBytes=130`, and entryByteLength MUST be 130. Each array element uses 134 octets including length. The array's initial count occupies 4. Manifest's other values total 58 and its five headers 30. Thus:

```text
manifest plaintext = 30 + 58 + 4 + 134*entryCount = 92 + 134*entryCount
empty initial plaintext = 92; whole encrypted artifact = 164
maximum plaintext = 8,781,916; maximum whole encrypted artifact = 8,781,988
```

The stricter schema maximum leaves unused capacity beneath the outer 16 MiB cap; it grants no extension space. Reject inconsistent count/length before allocating entries, and validate the complete manifest atomically before authorizing any record access.

Entries are strictly sorted by unsigned lexicographic record-ID bytes and IDs are unique. Active entries require nonzero independently random storageRef, unique among live entries, whole artifact length within §7's record bound, and the §9 digest unequal to the all-zero sentinel. References are not semantic identifiers. Tombstones require zero storageRef, zero artifact length and all-zero digest, while retaining ID/class/schema/version. Reject mixed sentinel states. Class/schema are explicit, not inferred from payload. Both classes remain encrypted; schema 1 defines opaque application bytes, not wallet recovery, engine, credentials or business serialization.

## 9. Transitions, artifact digests and freshness limits

```text
manifestArtifactDigest = SHA-256(D("skald/v1/previous-manifest") || LP(exact encrypted manifest artifact))
recordArtifactDigest   = SHA-256(D("skald/v1/record-artifact") || LP(exact encrypted record artifact))
```

Each exact artifact includes its complete outer frame, nonce, ciphertext and tag. The provider computes these hashes. The manifestArtifactDigest identifies the authenticated manifest capability and is the next manifest's predecessor value; callers cannot assert that identity. Record digests/lengths enter active entries only via provider-verified pending receipts or unchanged trusted entries. These hashes are authenticated inside the encrypted manifest, not standalone authenticators, passphrase verifiers or freshness proofs.

Every generation-1 snapshot MUST have zero entries and an all-zero predecessor commitment. This invariant applies both to creation and to decoding/authenticating an opened snapshot; reject a generation-1 snapshot with any entry or a nonzero predecessor. Creation starts with exactly that empty snapshot. Record creation occurs only in subsequent transactions. Generation >1 requires a nonzero predecessor digest. With an available predecessor, exact digest and consecutive generation MUST agree. Without historical predecessor bytes an otherwise valid current snapshot is not rejected solely for that absence; no complete-history verification is implied.

Every semantic commit increments generation exactly one. Header-only rewrap leaves generation/chain unchanged. New records use fresh nonzero IDs, version 1, chosen class, schema 1 and active state. Class is immutable; IDs are never reused. Updates increment version exactly one and allocate fresh reference/nonce for a new immutable artifact. Deletion increments version exactly one and yields a tombstone, with no new record artifact. Counters fail on overflow.

The new manifest ID set equals every previous ID plus fresh IDs. No previous entry disappears. For each previous entry:

- A retained tombstone is byte-identical; no resurrection or reuse.
- An active entry is byte-identical, or an active version+1 entry with identical ID/class/schema and a fresh reference, or the exact version+1 tombstone.
- No other transition is legal. New IDs have version 1 and active state.

A semantic commit must represent a legal content transition, not credential rewrap. Complete transition validation belongs to provider plus authorized transaction orchestration against the prior authoritative snapshot; pure parsing only establishes self-contained structure. Retained tombstones count toward lifetime capacity. At 65,536 entries a new ID allocation returns `CapacityExceeded`; existing updates/deletions may proceed if their counters and all other conditions permit. No tombstone compaction/discard/migration is authorized in v1. Generation and predecessor digest are the entire commit metadata: no timestamp, plaintext journal or unspecified extension blob.

A single valid snapshot cannot prove global latest generation or every historical version transition. Normal read/write capabilities require the coordinator's current-snapshot authority; historical cryptographic validity alone is insufficient. Successful commits supersede old snapshot tokens, including in-flight results. There is no implicit historical-read API or fallback to older manifests upon corruption. Full header/manifest/record rollback can still form a valid old snapshot; no external monotonic trust anchor is selected and anti-rollback protection is not claimed.

## 10. Credential profile 1

Profile 1 pins **Unicode 15.0.0 data and NFC**, independent of Android/JDK default Unicode versions. A later Unicode rule change needs an explicit profile/version decision, not silent reinterpretation of old credentials. This section owns persisted credential semantics; no normalization dependency or UI API is implemented/selected now.

Processing order for CREATE, CHANGE and OPEN:

1. Enforce at most **4,096 UTF-16 code units** at the UI/provider boundary, then validate all surrogate pairing. Reject malformed Unicode without replacement. This is a raw resource bound, not the strength measure.
2. Decode scalars and apply the pinned forbidden-property rules below to raw scalars.
3. Normalize to NFC with the pinned data, then apply the same rules to the normalized scalars.
4. Strict UTF-8 encoding without substitution; require **1..1,024 normalized UTF-8 octets**. Bound normalization/encoding work and check lengths before allocating outputs; never truncate.
5. For CREATE/CHANGE only, require **at least 20 normalized Unicode scalar values**, after NFC. Do not count raw decomposed scalars, UTF-16 units or grapheme clusters. OPEN imposes no creation minimum, while retaining profile validation and maximum limits.

Reject any scalar with Unicode 15.0.0 General_Category **Cc, Cf, Cs, Cn, Co, Zl or Zp**; any **White_Space except U+0020**; any **Default_Ignorable_Code_Point**; and every Unicode noncharacter. U+0020 is the only accepted whitespace. Unassigned/private-use characters are rejected under the pinned data. Surrogates cannot survive well-formed scalar decoding. Ordinary combining marks remain allowed unless an explicit forbidden property applies. Emoji sequences must pass their actual scalar properties: ZWJ/ZWNJ, variation selectors and other forbidden default-ignorables reject; they are never stripped.

Preserve leading/trailing/repeated U+0020 and case exactly. No trim, collapse, case-fold, NFKC, transliteration, autocorrection, or silent substitution. Valid canonically equivalent strings converge under NFC; case/space differences remain distinct. Twenty spaces or 20 repeated allowed scalars pass the syntactic minimum: this is not an entropy guarantee, blocklist or password estimator. Future strength advice cannot silently redefine this profile.

The header contains only profile ID, not actual length, score, plaintext hash or extra verifier. Unknown profile is `UnsupportedProfile`, with no alternate normalization/P0 fallback. Validation failures use `CredentialRejected` with only `rawLimit`, `malformedUnicode`, `forbiddenCodePoint`, `normalizedLimit` or `belowCreationMinimum` (last only CREATE/CHANGE), without offending content or actual counts. Once OPEN validation passes, failed root authentication uses the single §5 result.

### Reproducible Unicode authority

The pinned algorithm explanation is [UAX #15 revision 53, Unicode 15.0.0, dated 2022-08-17](https://www.unicode.org/reports/tr15/tr15-53.html), especially normalization forms/description, conformance and stability. The [current UAX #15 page](https://www.unicode.org/reports/tr15/) was checked 2026-10-11 and reports revision 58/Unicode 18.0.0 dated 2026-08-12. It is current explanatory context, **not** this profile's data authority. No latest-data substitution is allowed.

The following exact pinned files were retrieved 2026-10-11. Their content SHA-256 values make the data choice reproducible. Normalization algorithm implementation must also handle the algorithmic Hangul rules in pinned UAX #15; these tables are not a replacement for that algorithm.

| Unicode 15.0.0 file / role | Retrieved content SHA-256 |
| --- | --- |
| [UnicodeData.txt](https://www.unicode.org/Public/15.0.0/ucd/UnicodeData.txt): categories, decomposition, combining classes | `806e9aed65037197f1ec85e12be6e8cd870fc5608b4de0fffd990f689f376a73` |
| [DerivedCoreProperties.txt](https://www.unicode.org/Public/15.0.0/ucd/DerivedCoreProperties.txt): default-ignorables | `d367290bc0867e6b484c68370530bdd1a08b6b32404601b8c7accaf83e05628d` |
| [PropList.txt](https://www.unicode.org/Public/15.0.0/ucd/PropList.txt): White_Space/noncharacters | `e05c0a2811d113dae4abd832884199a3ea8d187ee1b872d8240a788a96540bfd` |
| [DerivedNormalizationProps.txt](https://www.unicode.org/Public/15.0.0/ucd/DerivedNormalizationProps.txt): derived normalization properties | `d5687a48c95c7d6e1ec59cb29c0f2e8b052018eb069a4371b7368d0561e12a29` |
| [CompositionExclusions.txt](https://www.unicode.org/Public/15.0.0/ucd/CompositionExclusions.txt): composition exclusions | `3b019c0a33c3140cbc920c078f4f9af2680ba4f71869c8d4de5190667c70b6a3` |
| [NormalizationTest.txt](https://www.unicode.org/Public/15.0.0/ucd/NormalizationTest.txt): conformance corpus | `fb9ac8cc154a80cad6caac9897af55a4e75176af6f4e2bb6edc2bf8b1d57f326` |

Both Android/Linux must demonstrate byte equality against this corpus and the acceptance plan's isolated boundary cases. Existing P0 normalization tests are not profile 1 conformance evidence. Documentation checks here neither normalize real credentials nor create cryptographic known-answer results.

## 11. Storage and implementation boundary

This is an app-owned multi-artifact vault: minimal public header, one authoritative encrypted manifest, immutable independent encrypted records, staging, and recovery/quarantine roles. Backup/export remains separate. No wallet schema, address parser, endpoint or engine storage is encoded here.

The provider emits bounded artifacts and opaque receipts, never writes files. Future storage resolves references only within its authorized vault root, with bounded I/O and symlink/traversal protection. A reference may map to exactly 32 lowercase hexadecimal characters; no concrete directory/path is selected and that text grants no semantic authority. Record IDs and storage references remain distinct roles.

New records must be installed and durable before authoritative manifest replacement, the logical semantic commit point. Header rewrap has a separate atomic replacement transaction. Atomic visibility is not crash durability; future Android/Linux work must establish filesystem/directory synchronization guarantees. Staging/orphans remain nondestructive until authority is established. No automatic empty-vault initialization, older-snapshot fallback, P0 migration, wiping or deletion of suspicious files follows failure.

Concurrent-writer locking, platform locations, durable rename/recovery mechanics, backup/export, platform wrapping, lifecycle triggers and UI/engine integration remain future implementation responsibilities. The byte/transcript/profile contract above is fixed by this decision; these deferred integrations do not leave its encoding unspecified. Production selection remains DisabledVaultCryptoProvider only; every current executable source, test, dependency, compatibility placeholder and operational gate is frozen in Phase 02.
