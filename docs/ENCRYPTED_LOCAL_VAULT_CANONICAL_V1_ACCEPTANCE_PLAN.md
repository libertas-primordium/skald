# Canonical v1 acceptance plan

Decision revision 2; persisted format version 1. Recorded 2026-10-11. Status: `CANONICAL_V1_SPECIFIED_IMPLEMENTATION_BLOCKED`.

This is the implementation acceptance catalog for the [security amendment](ENCRYPTED_LOCAL_VAULT_CANONICAL_V1_SECURITY_AMENDMENT.md), [format specification](ENCRYPTED_LOCAL_VAULT_CANONICAL_V1_FORMAT_SPECIFICATION.md), and [provider contract](ENCRYPTED_LOCAL_VAULT_CANONICAL_V1_PROVIDER_CONTRACT.md). The format specification is the sole normative numeric, byte-layout, label, bound, and transcript registry. Values repeated below identify test boundaries; they do not define an alternate format. If a transcription disagrees with that registry, resolve the document defect before implementation rather than choosing a convenient interpretation.

**Every future canonical execution case in this document is NOT IMPLEMENTED / NOT RUN in Phase 02.** Existing executable P0 tests and current disabled-policy/source guards have a different purpose. No approved canonical provider, codec, credential processor, session, storage, or application integration is created by this plan. Case IDs below are documentation references, never production readiness enums or runtime permission flags.

## Evidence and responsibility boundaries

| Layer / owner | What that layer must establish | What its success cannot establish |
| --- | --- | --- |
| Bounded structural codec | Exact canonical framing, field ordering, lengths, public ranges and cross-field arithmetic; complete consumption before returning parsed values. | Authenticity, trusted root/record context, supported KDF execution, a current snapshot, storage durability or permission to unlock. |
| Canonical credential processor and provider | Pinned profile bytes, cost admission, provider-owned randomness/keys, exact derivation/authentication transcripts, authenticated manifest/record capabilities, safe failure and ownership. | An authoritative on-disk current snapshot, complete historical transitions, successful persistence, a live session, or automatic approval of the committing construction. |
| Transaction/session coordinator | Current authenticated snapshot authority, lawful mutations, capability epoch/current-snapshot revocation, complete transition validation and authorization. Provider independently revalidates its trusted inputs. | Durability from a successful encryption return, global freshness from a self-contained old snapshot, or permission for an unapproved provider. |
| Platform storage coordinator | Namespace confinement and collision information, bounded I/O, records-before-manifest ordering, atomic visibility plus actual crash durability, nondestructive failure and recovery. | Authority from a filename, authenticated bytes from a digest alone, rollback prevention without an external trust anchor, or app enablement merely because files were written. |
| Release/app integration owner | Accepted provider and dependencies, platform parity, required device/runtime evidence, closed gates until explicit integration approval, truthful application behavior. | A completed wallet, Monero protocol validation, or mainnet authorization from vault tests. |

Future implementation phases used in the catalog are **C** (pure bounded codec/transcript implementation), **P** (credential and purpose-specific provider implementation/independent vectors), **T** (authenticated transaction/capability implementation), **S** (session/platform storage implementation), and **I** (controlled integration/release acceptance). These are ownership labels, not authorization to execute all phases under this prompt. Each needs a separately bounded implementation scope. The minimum platform notation is **shared + D/A**: deterministic common cases run on desktop/Linux and Android, with real Android instrumented execution whenever provider/platform code or its compilation ownership changes. **D/A storage** additionally requires actual supported filesystem and lifecycle behavior; a JVM-only fake is insufficient.

For each implemented case, record its final source/input identity, exact test identity, command, platform/runtime, expected property, actual result, failure/error/skip counts, and remaining boundary. A source declaration count is discovery evidence, not an execution count. Common cases executed on multiple targets are repeated executions, not unique tests. An in-memory prohibited-source mutation must identify an existing marker, assert the intended match count, assert a changed result, and reject at the intended guard boundary; never install or execute the mutation in the app.

## 1. Framing and pre-crypto admission

All cases in this section: **NOT IMPLEMENTED / NOT RUN**. Owner/phase: bounded codec **C**, with provider admission assertions **P** where indicated. Coverage: shared + D/A. Require rejection without partial parsed authority, unchecked allocation, crypto execution or storage access.

| Case ID | Input / mutation | Expected property and responsible boundary |
| --- | --- | --- |
| FMT-01 | Each artifact magic; wrong one-byte magic, cross-artifact magic, old P0 magic, unsupported format/suite/KDF/profile/envelope IDs or versions. | Only the registry's exact artifact/profile combination parses; no guessing, downgrade, P0 fallback or migration. Unknown profile is distinguished from credential authentication failure by the provider. |
| FMT-02 | Truncate before/inside/after each outer, TLV and array-element framing boundary, and each required fixed value; append one or multiple octets. | Complete enclosing length equality and full consumption; no accepted prefix or invented missing value. |
| FMT-03 | Every fixed-width field encoded too short/long, duplicate/unknown/missing/out-of-order TLV, optional-extension attempt, unexpected nested bytes. | Exactly one increasing mandatory field sequence; no extension/feature-flag interpretation or recursive generic object parsing. |
| FMT-04 | Unsigned lengths at zero, exact expected size, one below/above, maximum encoded value; contradictory inner/outer lengths; multiplication/addition near overflow. | Checked arithmetic before allocation/slicing; attacker lengths never allocate beyond actual input or policy cap. Test narrow-host integer implementations too. |
| FMT-05 | Header salt minima/maxima and neighbors; actual schema sizes versus the larger allocation cap. | Exact header schema is stricter than the allocation cap; a cap-sized extended header is rejected. Creation's salt size and opening's accepted size range remain distinct. |
| FMT-06 | Structural KDF memory/pass/lane/salt bounds and each neighbor; memory not divisible by `4*lanes`; memory below `8*lanes`. | Reject rounding aliases and invalid arithmetic before any KDF work. Memory is total memory, never silently multiplied per lane. |
| FMT-07 | Structurally valid below-floor costs and lanes 2 through 4, versus eligible stored costs. | Codec may return structurally parsed public data; provider refuses unsupported costs without KDF invocation. Eligible bytes are used exactly; no lane/cost substitution. Phase P owns the invocation-count assertion. |
| FMT-08 | Eligible cost exceeding currently available resources; simultaneous provider aliases; canceled active worker. | Bounded resource failure before work where possible; one active process-wide canonical KDF, no unbounded internal queue, no weaker retry. Permit remains held until actual worker termination/cleanup. Phase P. |
| FMT-09 | Counter zero, maximum supported counter, encoded values above it; schema/class zero/unknown IDs. | Counter and registry constraints reject independently of host signed conversion; unknown schemas/classes do not become generic payload authority. |
| FMT-10 | Envelope nonce/tag lengths, ciphertext limits and neighbors; record zero payload; manifest schema maximum versus generic envelope cap. | Exact envelope overhead, full input consumption, valid zero-length record, and stricter manifest-schema bound all agree with the registry. |

## 2. Header transcript and commitment boundary

All cases: **NOT IMPLEMENTED / NOT RUN**. Owner/phase: transcript codec **C**, provider **P**, authorization **T/S**. Coverage: shared + D/A; provider ordering requires an instrumented test implementation with synthetic public material and observable call counts, not app logging.

| Case ID | Input / mutation | Expected property |
| --- | --- | --- |
| HDR-01 | Independently enumerate every header byte range for each accepted salt length; construct H from ranges and independently from registry fields. | Both representations agree; final outer length and all fifteen field headers are covered exactly once, while only C/T/commitment value ranges are excluded. No encode-then-zero ambiguity. |
| HDR-02 | Change every public field, magic/version/length byte, credential profile, KDF parameter/salt, vault ID or wrap nonce while retaining prior authenticators; separate structural rejection from authenticated mutation. | Every structurally admissible H change changes authenticated input; structural failures stop before KDF. No omitted field or mutable duplicate authority. |
| HDR-03 | Change C, T or stored commitment alone; commitment prefix match with last-byte mismatch; wrong complete commitment. | Full C/T coverage and full constant-time commitment comparison. Failed commitment means zero root-AEAD-open calls and no root handle. |
| HDR-04 | Independently inspect domains, LP framing, stable I, extract salts, expansion lengths, and separated wrap/commit keys. | Exact registered bytes and distinct purposes; no key reuse, omitted lengths, implicit terminators, swapped HKDF arguments or circular transcript. |
| HDR-05 | Correct commitment over a deliberately invalid AEAD result in a test-only controlled context. | AEAD still authenticates; failure releases no plaintext/root. Commitment success cannot replace AEAD success. |
| HDR-06 | Valid profile credential but wrong passphrase; commitment failure; post-commitment AEAD failure. | All expose only `AuthenticationFailed`; no failed-stage label, expected tag, secret-bearing exception, partial plaintext or pre-Argon verifier. Do not assert identical timings against malformed public input. |
| HDR-07 | Root authenticated but authoritative manifest absent, invalid, wrong-vault or unauthenticated. | Root capability remains insufficient for unlocked session, current snapshot, record read or content write. |
| HDR-08 | Correct root envelope from another suite/vault; old P0 commitment/transcript or old root length. | Exact suite/vault context and canonical length prevail; no old format fallback. Ordinary wrong-key rejection is not presented as formal commitment proof. |

## 3. Provider cryptographic execution and vectors

All cases: **NOT IMPLEMENTED / NOT RUN**. Owner/phase: provider **P**, independent cryptographic assessor, later release **I**. Coverage: actual D/A implementations and approved release-like runtime coverage, not merely shared wrappers.

| Case ID | Input / mutation | Expected property |
| --- | --- | --- |
| CRY-01 | Published Argon2id, HKDF-SHA-256, HMAC-SHA-256 and XChaCha20-Poly1305 vectors with exact provenance and independent reference output. | Primitive correctness established separately from Skald composition. Canonical Argon2 secret/associated-data inputs are empty; output length and version follow the registry. Existing P0 vectors stay historical. |
| CRY-02 | Complete canonical public synthetic header, H, wrapAAD, C/T, commitInput, commitment, I, content roots, R, E, per-artifact keys/AAD and artifact hashes. | Independently obtained byte-for-byte reference vectors on D/A. Outputs generated solely by the implementation under test are not independent KAT evidence. Phase 02 supplies no executable canonical vectors. |
| CRY-03 | Cross-purpose root/key substitution, wrap versus content key, metadata versus secret, manifest versus record, backup versus live-vault context. | Domain/key separation prevents acceptance; no generic caller-selected derivation label, raw key export or arbitrary AAD API. |
| CRY-04 | Each nonce, ciphertext and tag mutation; combined versus detached library output; wrong key and wrong authenticated context. | Exact serialized C/T split, nonce and framing; failed authentication exposes no plaintext; library conventions add no hidden nonce/framing. |
| CRY-05 | Same stable root/ID with changed credentials/KDF/salt/wrap nonce; changed suite or root. | Credential rewrap preserves exact content derivation/AAD and existing bytes; suite/root change is a distinct unsupported rekey/migration operation. |
| CRY-06 | Calls to direct crypto/RNG, P0 helper, provider override, runtime KAT endpoint, test fixture or generic persisted keyset outside the accepted provider. | Source/reachability and operation guards reject bypasses. No test helper returns to production compilation or packages. |
| CRY-07 | Independent analysis of the exact separated-key committing wrapper and its transcript, including malicious-key and context-substitution questions. | Written dedicated crypto assessment before production acceptance. A 32-byte HMAC and wrong-key tests do not establish a quantified key-commitment theorem or committing semantics for individual records. |

## 4. Credential profile 1

All cases: **NOT IMPLEMENTED / NOT RUN**. Owner/phase: credential/provider **P**. Coverage: the same pinned Unicode 15.0.0 normalization corpus and synthetic case definitions on actual Android and Linux implementations. Normative preprocessing order and error categories come from the format specification and provider contract. The code-point notation below describes public synthetic inputs; it is not a supplied user's passphrase and does not create runtime fixtures in this pass.

No host-default Unicode category/normalization version is an implicit reference. Record the pinned UCD source hashes, generation/review process and actual provider bytes. Assert internal stage ordering with test-only instrumentation; public failures disclose neither offending text nor actual scalar/byte counts.

| Case ID | Precisely isolated synthetic case | Expected result |
| --- | --- | --- |
| CRED-01 | 19 repetitions of U+0041 versus 20, both otherwise legal and far below raw/byte maxima. | CREATE/CHANGE: `belowCreationMinimum` versus syntactic acceptance. OPEN admits both to authentication; no creation-minimum check. Acceptance is not authentication success. |
| CRED-02 | 19 versus 20 repetitions of U+1F600, a permitted supplementary scalar under the pinned data. | Scalar count is 19/20, not 38/40 UTF-16 units; CREATE/CHANGE boundary matches CRED-01. No assumption that every emoji sequence is allowed. |
| CRED-03 | Ten repetitions of the pair (U+0065, U+0301): 20 raw scalars but 10 after NFC. Separately use 19 and 20 such pairs. | Minimum is checked after NFC: reject 10/19 normalized scalars and admit 20 when all other rules pass. Do not count decomposed input length. |
| CRED-04 | Twenty U+00E9 versus twenty U+0065/U+0301 pairs. | Exact same NFC scalars and UTF-8 bytes on D/A; not two different credentials. Normalization has no platform-data drift. |
| CRED-05 | A legal 20-scalar base with leading/trailing U+0020, repeated internal U+0020, and variants with differing space counts or ASCII case. | Preserve all ordinary spaces; no trim/collapse/case fold. Distinct permitted sequences remain distinct unless NFC makes them canonically equivalent. |
| CRED-06 | Exactly twenty U+0020 characters. | Syntactically accepted for CREATE/CHANGE. Minimum is not entropy assurance; no unapproved dictionary, strength estimator or repeat ban is invented. |
| CRED-07 | Legal 20-scalar base plus one TAB, LF, CR, U+0085, U+00A0, U+2028 or U+2029, tested independently. | Each rejects as `forbiddenCodePoint` before derivation; controls, prohibited whitespace and line/paragraph separators are never silently removed. |
| CRED-08 | Legal base plus U+200C, U+200D, U+FE0F, or supplementary variation selector U+E0100, each separately. | Default-ignorable/formatting rejection; stripping them into an accepted credential is forbidden. |
| CRED-09 | Legal base plus U+E000, U+0378 (unassigned in 15.0), U+FDD0, U+FFFF or U+10FFFF, separately. | Pinned private-use, unassigned and noncharacter exclusions hold on every runtime, including runtimes assigning newer characters. |
| CRED-10 | Legal non-ignorable combining marks, including composition and noncomposition cases; raw and normalized property rejection coverage derived from the pinned corpus. | Ordinary marks remain allowed unless explicitly forbidden. Rules are applied before and after NFC; no blanket combining-mark ban. |
| CRED-11 | Lone high surrogate, lone low surrogate, reversed pair and a high surrogate followed by an ordinary scalar, all within resource bounds. | `malformedUnicode` without replacement characters, normalization, KDF or offending-content disclosure. Valid supplementary pairs pass well-formedness. |
| CRED-12 | 1,024 versus 1,025 U+0041 repetitions; both exceed the minimum but are below the raw limit. | Exact normalized UTF-8 maximum isolated: first passes profile limits, second is `normalizedLimit`. No truncation. |
| CRED-13 | 256 versus 257 U+1F600 repetitions: normalized UTF-8 lengths 1,024 versus 1,028, with raw lengths 512 versus 514. | Byte accounting is UTF-8, not scalar or UTF-16 count. Together with CRED-12 this covers the exact adjacent byte boundary and multibyte behavior. |
| CRED-14 | 4,096 versus 4,097 U+0041 UTF-16 code units. | First passes only the raw-resource stage and subsequently fails `normalizedLimit`; second fails `rawLimit` before normalization. Neither is a valid full credential. Do not claim a 4,096-unit input passed the profile, or use the later byte failure as proof of the raw check. |
| CRED-15 | 2,048 supplementary pairs versus those pairs plus one U+0041: 4,096 versus 4,097 raw UTF-16 units. | Raw-resource check counts code units; stage result follows CRED-14. Later byte limits are deliberately separate; malformed-surrogate tests are CRED-11. |
| CRED-16 | Empty input; one legal scalar under OPEN versus CREATE/CHANGE; unknown stored profile with otherwise eligible public costs. | Empty fails `normalizedLimit`; one scalar reaches OPEN authentication but fails CREATE/CHANGE minimum; unknown profile is `UnsupportedProfile`, without alternative normalization or P0 fallback. |
| CRED-17 | Mutation of an owned input buffer by a former caller; success/failure/cancel/revoke across normalization and KDF; diagnostics/copy/hash/string rendering. | Ownership/defensive-copy contract and best-effort clearing hold; no secret text/bytes/counts in public failures. A caller's retained copy cannot be erased by provider ownership. |
| CRED-18 | Pinned NormalizationTest.txt cases and a host runtime with a different Unicode data version. | Canonical conformance uses profile-1 data identically on D/A. Corpus normalization and profile admission are separate assertions: corpus inputs forbidden by the profile must not become admitted credentials. |

The test runner must assert the intended rejection boundary for each malformed/resource case rather than count any rejection as success. For code points with multiple forbidden properties, a single bounded `forbiddenCodePoint` result is sufficient; revealing the actual character or property is unnecessary. Cases with raw-limit failures must establish that normalization/KDF was not invoked. Profile validation errors are not reclassified as a wrong passphrase; eligible OPEN authentication failures share the provider's single public authentication category.

## 5. Authoritative manifest and transitions

All cases: **NOT IMPLEMENTED / NOT RUN**. Owner/phase: codec **C**, provider **P**, trusted transition coordinator **T**. Coverage: shared + D/A.

| Case ID | Input / mutation | Expected property |
| --- | --- | --- |
| MAN-01 | Empty and maximum-count manifests; one additional entry; entry length wrong by one; exact outer size calculations. | Empty initial snapshot accepted structurally; exact entry/array/plaintext/artifact sizes match registry. Lifetime entry capacity is enforced; unused generic envelope capacity is no extension mechanism. |
| MAN-02 | Duplicate IDs, out-of-order unsigned byte ordering, zero IDs, duplicate live references, zero live reference, unknown class/schema, vault ID mismatch. | Entire manifest rejected atomically, with no partially trusted entries or record-read authority. References are distinct opaque identities, not filenames or protocol labels. |
| MAN-03 | Active lengths/digests at valid limits; zero digest sentinel; mixed active/tombstone sentinel combinations. | Active fields and all-zero tombstone fields obey their exact mutually exclusive invariants. Zero-payload active record remains valid and distinct from tombstone. |
| MAN-04 | New ID/version 1 active; record update/deletion at exactly next version; unchanged active or tombstone; arbitrary removed prior ID. | Complete ID set equals all prior IDs plus new IDs; no prior entry disappears. Tombstones remain byte-identical and cannot resurrect. |
| MAN-05 | Changed class/schema/ID during update; reused reference; skipped/decreased version; new tombstone; reused former ID. | Reject illegal transition against trusted prior authority even when the new isolated snapshot is structurally valid. |
| MAN-06 | Generation 1 with nonempty array/nonzero predecessor; later generation with zero predecessor; consecutive/nonconsecutive generation. | Creation seals only empty generation 1 with a zero predecessor. Snapshot decoding/opening rejects any generation-1 entries or nonzero predecessor, and rejects a zero predecessor for later generations. Transition-specific consecutive generation checks remain distinct. |
| MAN-07 | Exact predecessor artifact hash versus altered framing/nonce/C/T; absent historical predecessor; inconsistent available predecessor. | Hash includes complete predecessor artifact. Available predecessor must match hash and consecutive generation; an otherwise valid current snapshot does not fail merely because old predecessor bytes are unavailable. |
| MAN-08 | Update/delete at maximum counter; generation increment at maximum; allocation at lifetime maximum including tombstones. | `CounterExhausted` or `CapacityExceeded` without partial state. At entry capacity, lawful updates/deletions remain allowed if their counters permit. |
| MAN-09 | Same valid historical snapshot after a successful later commit; complete self-consistent old vault replacement after reopen. | Current-snapshot tokens reject the first within known authority. The second demonstrates the documented whole-vault rollback limitation; do not expect an unavailable monotonic anchor. |
| MAN-10 | Caller-invented new entry/digest/length/context; receipt bound to wrong transaction/prior snapshot; deletion lacking prior active capability. | Provider rejects forged authority. Valid receipts and unchanged trusted entries/deletion intents alone construct a lawful sealed manifest; deletion produces no record receipt/artifact. |
| MAN-11 | Test-only digest outcome forced to the reserved all-zero sentinel for a new record or predecessor commitment. | Fail or safely retry before commit under the format's sentinel rule; never reinterpret a real artifact as absent or install a partially changed manifest. No such forcing hook belongs in a production API. |

## 6. Record binding and read integrity

All cases: **NOT IMPLEMENTED / NOT RUN**. Owner/phase: codec/provider **C/P**, snapshot authority **T/S**. Coverage: shared + D/A.

| Case ID | Input / mutation | Expected property |
| --- | --- | --- |
| REC-01 | Mutate every R component: class, ID, schema, version or storage reference; use wrong vault/suite context. | Exact authenticated-manifest context drives key/AAD. No public data class, filename or unauthenticated envelope can mint trusted R. |
| REC-02 | Envelope nonce or E framing/length change; record/manifest envelope substitution; correct payload under another reference. | Key derivation and AEAD both use the specified nonce; artifact-specific E and magic prevent substitution. |
| REC-03 | Truncated/oversized exact artifact, wrong declared length, altered SHA-256 digest, ciphertext/tag mutation. | Verify exact authoritative length/digest/context and AEAD before releasing plaintext. Digests authenticated by a manifest do not independently prove freshness. |
| REC-04 | Tombstone, noncurrent manifest capability, pending uncommitted receipt, wrong instance/root/vault/generation/digest. | Reject normal record read; an old cryptographically valid record does not override current snapshot/session authority. |
| REC-05 | Empty opaque payload, exact maximum payload, one byte above maximum. | Empty and exact maximum obey whole-artifact limits; over-limit rejection occurs before oversized crypto/allocation. No wallet protocol or nonempty-payload assumption. |
| REC-06 | Manifest successfully opens while one referenced record is corrupted or absent. | Manifest opening does not imply that record was read. Explicit per-record integrity failure with no partial plaintext; no automatic fallback, deletion or reset. |
| REC-07 | Seal retry after cancellation/failure; collision with live/staged/reserved/existing namespace reference or retained/reserved record ID; repeated zero draws. | Provider allocates fresh context within bounded draw rules and trustworthy storage collision information. Exhaustion/RNG failure fails without deterministic fallback or replay of a prior random result. |

## 7. Credential rotation

All cases: **NOT IMPLEMENTED / NOT RUN**. Owner/phase: provider **P**, authorized session/atomic header replacement **T/S**. Coverage: shared + actual D/A storage when persisted.

| Case ID | Input / mutation | Expected property |
| --- | --- | --- |
| ROT-01 | Authorized rewrap with valid new CREATE/CHANGE credential and approved parameters. | Same root/vault ID, new salt/wrap nonce and separated keys/commitment. Exact existing manifest/record bytes and content contexts remain unchanged; no manifest generation increment. |
| ROT-02 | Invalid new credential, unsupported costs, arbitrary parsed header without authorization, closed or stale rewrap capability. | Bounded refusal with no replaced header, root leak or partial success; no credential-reset or passwordless recovery shortcut. |
| ROT-03 | Cancel or revoke before/after KDF/wrap returns; storage failure at every header replacement point. | Pending result cannot reactivate authority or overwrite storage; old recoverable state remains until independently established atomic replacement/durability. |
| ROT-04 | Preserve an old valid header and old credential after rotation. | Demonstrate old copy still unwraps the same root within isolated test evidence. Document that rewrap is not compromise recovery, root revocation or secure flash/journal erasure. |

## 8. Capability ownership, concurrency and failure

All cases: **NOT IMPLEMENTED / NOT RUN**. Owner/phase: provider **P**, current snapshot/epoch coordinator **T/S**. Coverage: shared + D/A, with deterministic race controls and actual platform worker cancellation evidence.

| Case ID | Input / mutation | Expected property |
| --- | --- | --- |
| CAP-01 | Wrong provider instance/root/vault/purpose/epoch; closed/revoked handle; forged public constructor/byte getter/reflection bypass attempted through supported boundaries. | Private/provider-controlled construction and exact binding reject use. No raw KEK/root/derived-key getter, generic derive/export API or mutable secret data class. |
| CAP-02 | Fresh pending creation attempts record read/write; header-open root attempts writes without current authenticated manifest; staged manifest attempts current authority. | Creation can seal only its initial empty manifest. No pending capability becomes an unlocked/current session or authorizes content operations before the required commit. |
| CAP-03 | Lock/revoke/cancel during credential processing, KDF, decrypt, manifest validation or result delivery. | Late results are cleared/discarded, cannot return plaintext, reactivate a session or commit storage. No partially successful secret result escapes. |
| CAP-04 | Concurrent canonical KDF requests across multiple instances/aliases; cancellation requested while native worker still runs. | One process-wide active KDF; contender receives `ResourceUnavailable`; permit retained until real termination and best-effort cleanup. No internal unbounded queue or weaker retry. |
| CAP-05 | Success/failure/cancel/close cleanup with instrumented owned buffers and caller-retained copies. | Owned inputs transfer at invocation handoff even on validation/resource failure or cancellation; caller reuse is forbidden. Borrowed authorization capabilities remain revocable without implicit consumption. Clear what is actually owned when no longer needed; document immutable/GC/native/OS limitations. No claim that all caller copies or all decrypted authorized payload uses stay inside the provider forever. |
| CAP-06 | Entropy failure and bounded nonzero/reference allocation exhaustion for every randomness purpose. | Operation fails with no time/counter/test-RNG fallback, deterministic reuse or weaker provider. Fresh random nonce policy is probabilistic, not an absolute global uniqueness theorem. |
| CAP-07 | Successful semantic commit while old read/decrypt result is in flight; then ordinary read/mutation through prior generation/digest capability. | Successful commit supersedes prior current-snapshot tokens, including late results. Provider-computed manifest digest is bound into authenticated capabilities; caller hashes are not authority. |
| CAP-08 | Every public error path, exception source and `toString`/copy/equality/hash/diagnostic/report surface, using synthetic inputs. | Only bounded approved categories; no raw identifiers, credential content/counts, key bytes, plaintext, native exception text or secret-verifier leaks. Public-format versus admitted authentication timings are not falsely equated. |

## 9. Later persistence and recovery

All cases: **NOT IMPLEMENTED / NOT RUN**. Owner/phase: storage/session **S**, with provider **P/T** integration. Coverage: D/A storage and controlled crash/failure injection; in-memory simulations alone are insufficient.

| Case ID | Input / mutation | Expected property |
| --- | --- | --- |
| STO-01 | Crash/short-write/error before and after each artifact installation, file flush, directory sync and manifest replacement. | New immutable records durable before authoritative manifest commit; distinguish atomic visibility from crash durability using actual filesystem guarantees. |
| STO-02 | Interrupted header-only rotation, failed durability step, concurrent content transaction. | Authorized coordination preserves a recoverable authoritative state and content bytes. Exact concurrent-writer mechanics remain implementation design; no opportunistic overwrite. |
| STO-03 | Traversal/symlink attacks, namespace/reference collision, untrusted path text or semantic record label used as path. | Only confined authorized storage resolution; future hexadecimal rendering is not authority. No arbitrary header/manifest string becomes a path. |
| STO-04 | Staging/orphans/suspicious files with current corruption or failed credential; missing record or invalid manifest. | Nondestructive handling until authority established; no wipe, empty initialization, old-snapshot fallback, P0 migration or automatic suspicious-file deletion. |
| STO-05 | Rollback of header alone, manifest alone, record alone and whole consistent vault; available versus unavailable trusted predecessor. | Detect substitutions the available trusted context permits; explicitly retain whole-snapshot rollback limitation without an external monotonic anchor. |
| STO-06 | Concurrent writers and stale transaction receipt; crash/reopen current-token reconstruction; errors during bounded collision inventory. | No stale commit/current capability, duplicate reference allocation or partial manifest authority; provider receives bounded trustworthy collision information without gaining arbitrary filesystem authority. |

## 10. Later provider and release integration

All cases: **NOT IMPLEMENTED / NOT RUN**. Owner/phase: provider assessor and integration **I**, with responsible C/P/T/S owners. Coverage: Android/Linux supported runtimes, actual connected device and rebuilt production/test package separation.

| Case ID | Input / mutation | Expected property |
| --- | --- | --- |
| INT-01 | End-to-end synthetic create/open/record/update/delete/rewrap through approved future boundaries on D/A. | Real implemented path exercised, with exact bytes and independent vectors; no fake success or real wallet material. The app stays disabled until separately approved integration. |
| INT-02 | Provider/dependency revision, native packaging and runtime lifecycle changes; failure of one required platform gate. | Pin review inputs and required provider-level KAT/device evidence; no unavailable gate relabeled PASS. Dedicated crypto assessment is separate from this document review. |
| INT-03 | Production helper/fixture/test-source reintroduction or direct crypto/RNG bypass; compiled/package class inventories. | Phase-01 quarantine remains effective; test execution does not imply test helpers are allowed in shipped first-party code. |
| INT-04 | App startup/navigation, storage capability display, provider selection and operation gating before explicit enablement. | Existing UI and disabled behavior remain unchanged until authorized integration; no hidden endpoint, Monero engine, real wallet inputs, signing, relay or mainnet inferred from vault acceptance. |
| INT-05 | Backup/export or optional platform wrapping proposed after live-vault acceptance. | Separate format/key hierarchy and explicit review, including recovery/credential policy; no live-record key reuse or automatic inheritance of approval. |

## Phase 02 evidence: what can actually be verified now

The phase closeout under ignored `.skald-local/reports/vault-02-canonical-format-contract` must record actual results, not mark this future catalog executed. Its portable bundle must contain the final maintained files, complete patch including new documents, input manifests, historical-prefix proof, arithmetic/coverage checks, primary-source references, fresh focused XML/logs, review findings/dispositions and limitations. The tracked documents do not depend on workstation-only links for normative requirements.

| Current check | Required evidence | Scope of conclusion |
| --- | --- | --- |
| Repository and frozen executable tree | Correct main checkout/branch; HEAD/tree/index/remote comparison; every tracked non-document hash/path unchanged; exact maintained-doc allowlist and untracked inventory. | Phase 02 adds no runtime, test, build, fixture, dependency, permission, UI or operational change. |
| Exact document contract | Independent integer arithmetic, field/domain registry and byte-range/transcript coverage; authority matrix; local links and historical byte-prefix preservation. | Specification is internally reviewable and exact; no executable codec, crypto vector or formal security proof is established. |
| Existing focused desktop regression | Fresh XML identities/counts, exact command and final protected-document input identity; source-guard task rerun when nonstandard document inputs change. | Existing historical/disabled/source-isolation guards continue passing unchanged. Their old no-HMAC typed-model assertion remains historical compatibility evidence, not revision-2 format authority. |
| Independent reviews | At least two distinct reviewers, exact final files/diff, concrete findings and corrections, revalidation of affected claims. | Independent specification review; dedicated production cryptographic review remains future work. |
| Historical journal and portable handoff | Append-only history prefix hash; final complete patch, review ZIP member/hash validation and final Git identity. | User can review actual final work before Git closeout; no staging, commit, implementation push or new phase is implied. |

The actual current focused suites discovered in this checkout are `VaultSourceGuardTest` (131 declarations), `VaultImportConfinementTest` (10), `PrototypeQuarantineBoundaryTest` (6), `EncryptedVaultV1CanonicalArchitectureDecisionTest` (13, common tests included in desktop), and `EncryptedVaultV1FormatParserCryptoReconciliationAuditTest` (10). These are **170 discovered existing test declarations**, not a claim that any command executed 170 tests. The closeout must inspect fresh XML and confirm actual class/case identities, failures/errors/skips and final input freshness. The historical canonical model test named `publicHeaderPolicyUsesWrappedRootAadWithoutSeparateCommitment` is deliberately unchanged; the amendment explicitly supersedes that inert model's normative design claim without weakening its current fail-closed behavior.

With the established Java/SDK/local-cache environment, the intended focused command is:

```text
./gradlew :composeApp:desktopTest --rerun --tests '*VaultSourceGuardTest' --tests '*VaultImportConfinementTest' --tests '*PrototypeQuarantineBoundaryTest' --tests '*EncryptedVaultV1CanonicalArchitectureDecisionTest' --tests '*EncryptedVaultV1FormatParserCryptoReconciliationAuditTest'
```

Record the exact invocation actually used, including environment and any offline/task flags, in the closeout. This docs-only pass does not require a new APK/Debian or connected run absent a separate applicable gate: compiled/configuration inputs are frozen. Previous Phase-01 package/device results must be labeled historical, never rerun by implication. Canonical crypto, Unicode implementation, vector execution, storage crash behavior and session runtime tests in this plan remain **NOT IMPLEMENTED / NOT RUN**, regardless of the existing focused guard outcome.

## Next bounded implementation and later work

After review and user-controlled Git closeout, a suitable first executable scope is the bounded pure canonical framing/header codec and exact transcript construction with malformed-input tests and independently checked structural examples from this registry. It must produce parsed, untrusted public values only; no provider enablement, secret input, crypto execution, storage or unlocked session. Its prompt must explicitly reconcile only the historical policy claims it actually touches and retain the remaining gates. This recommendation is not authorization to start it now.

Separate provider/credential implementation follows with pinned Unicode-data delivery decisions, independent canonical crypto vectors, purpose-specific opaque ownership, process-wide KDF exclusion and dedicated cryptographic assessment. Then implement and test transaction/current-snapshot authorization, actual platform storage atomicity/recovery and session lifecycle integration, separate backup/recovery, and finally controlled app integration. Exact deployment KDF calibration, platform durability/locking, lifecycle triggers/timers, secure-input UI, backup credentials and future wallet integration remain later obligations. Their implementation does not require leaving the canonical byte/transcript/profile contract vague or adding another chain of inert compiled readiness models.
