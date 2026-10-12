# Canonical v1 purpose-specific provider contract

Status: **CANONICAL_V1_SPECIFIED_IMPLEMENTATION_BLOCKED**. Decision revision **2**, dated **2026-10-11**. The first supported persisted format remains **canonical version 1**. No supported canonical vault has shipped; no executable implementation or operation is approved by this document.

The [security amendment](ENCRYPTED_LOCAL_VAULT_CANONICAL_V1_SECURITY_AMENDMENT.md) owns authority, rationale, and production-review conditions. The [format specification](ENCRYPTED_LOCAL_VAULT_CANONICAL_V1_FORMAT_SPECIFICATION.md) is the single normative registry for all numeric IDs, lengths, limits, algorithm parameters, domain labels, transcripts, and credential-profile rules. This contract references that registry rather than defining a second set of byte constants. The [acceptance plan](ENCRYPTED_LOCAL_VAULT_CANONICAL_V1_ACCEPTANCE_PLAN.md) assigns future implementation evidence. A discrepancy must be resolved explicitly before implementation; an implementation cannot select whichever document or historical model is more permissive.

## 1. Scope and unchanged compiled boundary

This is a future, purpose-specific contract expressed as documentation pseudocode. The operation names below are not new Kotlin interfaces, factories, endpoints, or runtime authorization. Existing `VaultCryptoProvider`, `DisabledVaultCryptoProvider`, their selectors, payload restrictions, source guards, and compatibility models remain byte-identical in Phase 02. Selection still returns only `DisabledVaultCryptoProvider`, with `productionProviderSelectable=false`.

Production create/open/unlock, provider crypto, key/nonce generation, canonical parsing, live sessions, storage operations, persistence, networking, synchronization, signing, relay, and mainnet remain disabled. The two-page UI is unchanged. Existing `SecretPayload.placeholderOnly()` and `SecureMetadataPayload.placeholderOnly()` remain material-free. The public `redacted(...)` handle factories in the existing provider interface produce inert evidence; they are not cryptographic capabilities and cannot be converted into the future capabilities described here.

| Current boundary/evidence | Relationship to this future contract |
| --- | --- |
| `VaultCryptoProvider` generic `deriveKey`, `encryptRecord`, `decryptRecord`, `generateKey`, `storeKeyset`, and `validateKat` methods | Preserve their blocked behavior. Canonical purpose operations require explicit later interface alignment; these methods do not become a generic canonical crypto service. |
| `VaultCryptoMaterialRef` and current redacted key/plaintext/ciphertext handles | Preserve placeholder semantics. They contain no authenticated root, session epoch, or trusted snapshot and confer no authority. |
| Provider selector, authorization/readiness chains, disabled storage/session facades | Preserve every closed gate. Tests or document acceptance do not promote them. |
| `SecureSecretStorage`, `SecureWalletMetadataRepository`, Android/Desktop disabled adapters, capability/UI mapping | Preserve every unavailable read/write/list/delete result and current payload restrictions. Later repository integration is separately scoped. |
| Production-compiled canonical decision and P0 KDF compatibility models | Inert historical compatibility evidence. Old no-HMAC/root/AAD/credential assumptions do not override revision 2. Later reconciliation must be explicit. |
| Quarantined P0 helpers, fixtures/KAT harness, synthetic working-parser classifier | Test-only unsupported historical behavior under [Phase 01 quarantine](ENCRYPTED_LOCAL_VAULT_PROTOTYPE_QUARANTINE.md). No canonical decoder fallback, provider bypass, migration, or format endorsement. |

The app-owned multi-artifact topology, authoritative encrypted manifest, independent encrypted records, nondestructive staging/recovery, and separate backup/export hierarchy remain selected. The vault stores opaque secure data; this contract does not select any wallet schema, Monero library, network, recovery encoding, transport, or LWS behavior.

## 2. Trust boundaries and ownership

All canonical Argon2id, HKDF, HMAC, domain-separated artifact SHA-256 commitments, AEAD, and cryptographic randomness MUST route through the selected Skald-owned provider. Application, storage, and pure codec code MUST NOT call Bouncy Castle, Tink, JCA/JCE, platform RNG, or P0 helpers directly. A disabled, unaccepted, or revoked provider cannot execute creation, opening, sealing, or unsealing.

A pure bounded codec may parse and encode the exact public structures and schema defined in the format specification. Its parsed objects are untrusted structural results. A successful parse, public data-class constructor, hash supplied by a caller, or correctly shaped ID cannot promote them into authenticated authority. Only the provider authenticates and issues root/manifest/record capabilities. Future session and storage orchestration establish which authenticated snapshot is current and which logical operation is authorized.

The provider has no filesystem, settings, endpoint, UI, or wallet-engine authority. Future storage supplies bounded, trustworthy collision/reservation information and current-snapshot evidence; this does not give the provider a path or file handle. The provider returns exact immutable encrypted artifacts for later authorized storage. Encryption success is not a durable commit.

Secret/key-bearing capabilities MUST have private/provider-controlled construction, redacted representations, no public raw-key getters, and explicit close/revoke semantics. They MUST NOT be Kotlin data classes with generated key-bearing `copy`, component, equality, hash, or diagnostic behavior. A handle is bound to its issuing provider instance, exact root capability, vault identity, permitted purpose, and session epoch. A capability issued by one provider alias/instance is not accepted by another merely because their display names match.

Authenticated manifest and record capabilities additionally bind the manifest generation and the digest of the **exact authenticated manifest artifact**. The provider computes this digest using the format registry's `skald/v1/previous-manifest` transcript as part of opening or sealing the manifest. This is the manifest's own artifact identity for capability binding; it is distinct from the previous-manifest commitment value stored inside that manifest. A caller cannot supply a digest as an assertion of trust.

Every normal content read or mutation requires the session/storage coordinator's current authoritative snapshot token as well as the relevant authenticated capability. A cryptographically valid historical snapshot is not automatically current authority. Successful commit supersedes the prior snapshot token, so older capabilities and in-flight results cannot authorize ordinary reads or writes. Reopen establishes authority only from the validated current authoritative manifest. Pending staged capabilities cannot become current before commit. An explicit historical-snapshot reading API is deferred and is not implicitly enabled by `openManifest`.

The provider and coordinator MUST prevent time-of-check/time-of-use substitution: artifact and context values used for authentication, hashing, and later publication are immutable owned values or defensively copied at the boundary. Capability validation is repeated before releasing a result or accepting a commit. A caller retaining a mutable input reference cannot alter the authenticated operation after validation.

## 3. Non-callable purpose operation surface

The following notation describes results and required authority, not runnable signatures. Authorization capabilities named in preconditions are mandatory parts of the future private call context even where omitted from the compact operation name. No public Boolean such as `approved=true` can replace them.

```text
createRootEnvelope(ownedCredential, approvedCreationParameters)
    -> PendingRootCapability + CanonicalHeaderArtifact
openRootEnvelope(validatedHeader, ownedCredential)
    -> AuthenticatedRootCapability
rewrapRoot(authorizedRootCapability, newOwnedCredential, approvedCreationParameters)
    -> PendingCanonicalHeaderArtifact
sealManifest(rootCapability, validatedOwnedManifest)
    -> PendingManifestReceipt
openManifest(rootCapability, encryptedManifestArtifact)
    -> AuthenticatedManifestCapability + ManifestArtifactDigest
sealRecord(rootCapability, authorizedMutationContext, ownedPlaintext)
    -> PendingRecordReceipt
openRecord(rootCapability, trustedManifestRecordCapability, encryptedRecordArtifact)
    -> OwnedPlaintext
close/revoke(capability or owned sensitive buffer)
    -> closed/revoked state; never a new authority
```

`PendingManifestReceipt` contains the exact encrypted canonical manifest artifact, its provider-computed artifact digest, and a pending authenticated manifest capability tied to the transaction. It is not a committed/current snapshot. An ordinary storage caller may obtain the immutable encrypted artifact needed for staging; it cannot manufacture or modify its authenticated identity. `PendingRecordReceipt` binds its logical identity/context, allocated reference, exact encrypted artifact, exact length, and provider-computed record digest. Neither receipt is a reusable permission to mutate another snapshot.

These operations expose no arbitrary algorithm/provider IDs, raw keys, caller AAD, domain labels, production nonce, storage reference, general `deriveKey`/`exportKey`, generic persisted keyset, runtime KAT endpoint, or public acceptance-flag override. Provider-specific objects do not escape into the application or codec layer. Test-only deterministic inputs remain confined to explicitly authorized test harnesses; they are not an app option.

## 4. Root-envelope creation and opening

### `createRootEnvelope`

Preconditions: an accepted provider; unrevoked creation authorization; an owned credential; creation parameters supplied by a future approved calibration decision; reviewed randomness/resource availability; and no conflicting pending operation that can bypass the process-wide KDF admission bound. Parameters and credential profile MUST pass the format registry's supported admission rules before KDF allocation. The documented floor/reference tuple is not itself a universal approved deployment setting.

The provider validates the credential under CREATE rules, allocates a new random nonzero vault ID under the bounded draw rule, generates a fresh salt, root-wrap nonce, and independent random vault root, and constructs the exact canonical header, `H`, wrapping keys, `wrapAAD`, ciphertext/tag, and separated-key commitment from the format specification. It derives the KEK without exposing it and keeps all root/key material inside the provider. The input credential does not become the root.

Success returns the canonical header artifact and an incomplete pending root capability. That capability may seal **only the initial empty manifest** with the format's initial generation and sentinel commitment. It cannot seal/open records or grant ordinary reads, writes, or an unlocked session. It remains pending until the initial header/manifest persistence transaction succeeds under later storage/session rules. Failure or cancellation produces no usable root capability; any incomplete encrypted artifact cannot be treated as a created vault. The provider does not create a directory, overwrite a file, or initialize user storage.

### `openRootEnvelope`

Preconditions: an accepted provider; authorized opening attempt and live epoch; an immutable strictly structurally validated canonical header; supported suite/profile/KDF costs; device resource admission; and an owned credential. The provider revalidates security-relevant structure and admission rather than trusting the caller's type name. OPEN credential rules apply, without the CREATE/CHANGE minimum.

The provider derives the exact KEK and separated wrap/commit keys. It computes and constant-time compares the **complete** stored commitment before invoking root AEAD opening. A commitment mismatch forbids the unwrap call. Only after a match may it authenticate/decrypt using the exact registry `wrapAAD` and nonce. It requires the exact root plaintext length and publishes no failed or partial plaintext.

A valid-profile wrong credential, commitment mismatch, and root AEAD authentication failure all return `AuthenticationFailed`. No result exposes the failed stage, expected tag, key hash, root fragment, or provider exception. A public-format/profile/cost failure remains distinguishable under section 10; there is no pre-Argon credential verifier or alternate-normalization fallback.

Success returns only an authenticated root capability. It is **not an unlocked vault session** and cannot authorize content writes. The authoritative manifest must subsequently authenticate, parse strictly, match the authenticated vault ID, and satisfy all snapshot invariants. Current authority must then be established by the session/storage coordinator. Merely obtaining the root does not imply every referenced record is intact or has been read.

## 5. Manifest authentication and transactional sealing

### `openManifest`

Preconditions: live authenticated root capability for the same provider/vault/epoch; immutable bounded encrypted manifest bytes selected by authorized opening/coordinator work. Opening an arbitrary artifact can establish cryptographic authenticity, but cannot declare it current.

The provider enforces the envelope limits, derives `Kmanifest` from its own root hierarchy and the exact envelope nonce, constructs the registry manifest AAD, and authenticates before plaintext can influence trusted authority. It applies the strict plaintext schema atomically: exact vault identity, supported schema, counters, sorted unique IDs, active-reference uniqueness, lengths/digests, and complete tombstone/sentinel rules. A partially parsed or partially valid manifest issues no capabilities. Unknown schema/class is unsupported, not a generic extension.

The provider computes the manifest artifact digest itself over the exact encrypted bytes. Success returns an authenticated manifest capability and that digest, bound to this root, epoch, generation, and artifact identity. The capability does not establish global freshness or prove historical transitions. If an authenticated predecessor is available, its digest and consecutive generation must agree; absence of a historical predecessor alone does not invalidate a structurally and cryptographically valid snapshot. Current corruption does not authorize an older-manifest fallback.

The current-snapshot coordinator can derive private trusted record capabilities only from active entries of the authenticated authoritative manifest. No public descriptor constructor can fabricate this authority. A tombstone cannot authorize record reads. Referenced record corruption is a distinct per-record failure on access; manifest success is not an implicit full integrity scan.

### `sealManifest`

`validatedOwnedManifest` is a private transaction assembly, not arbitrary caller-supplied plaintext or a public list of asserted hashes. In the creation case it is restricted to the format's initial empty manifest. Otherwise it binds a live current-snapshot transaction capability, the authenticated predecessor and its provider-computed artifact digest, unchanged trusted entries, pending record receipts, and authorized deletion intents tied to prior active-entry capabilities. The transaction cannot mix receipts or entries from another provider, root, vault, epoch, generation, or prior manifest digest.

The provider validates the complete legal transition defined in the format specification. All prior IDs remain present; retained tombstones remain byte-identical; unchanged active entries remain byte-identical; updates and deletions use the required next version and preserve immutable identity/class/schema; new entries are fresh active allocations. Generation and version arithmetic fail before overflow. Capacity includes tombstones. At lifetime capacity only legal updates/deletions of existing records remain eligible.

A deletion supplies no new record artifact or receipt. The provider derives the exact next-version tombstone from the trusted prior active entry and authorized deletion intent. It rejects caller-invented identity, length, digest, reference, tombstone, version, or predecessor claims. Receipts can be consumed only by their bound transaction; duplicate/conflicting intents for an ID are rejected. No deletion drops the ID from the manifest or makes it reusable.

The provider constructs the canonical ordered plaintext, generates a fresh nonce, derives `Kmanifest`, authenticates the exact envelope context, and computes the new manifest artifact digest. Success returns a pending manifest receipt. It does not mark a session current, perform an atomic replace, or acknowledge persistence. The coordinator can promote it only after the authorized storage commit succeeds and the predecessor authority is still current. On commit, prior snapshot tokens are invalidated; failed/canceled staging never changes current authority.

## 6. Record sealing and opening

### `sealRecord`

Preconditions: authenticated root; a live current-snapshot transaction authority; privately constructed and provider-revalidated mutation context; and an owned opaque plaintext payload within the registry bound. `authorizedMutationContext` carries the allowed logical operation, explicit class, existing record ID or authorization to allocate a new ID, supported schema, next version, and prior current-snapshot authority. It supplies neither nonce nor storage reference. A pending creation root is ineligible.

For a new record the provider allocates a fresh nonzero ID under the registry's collision rules, including retained tombstones and transaction-reserved IDs. For an update it verifies the prior active capability and exact next version. In either case it allocates a fresh storage reference and nonce, checks/reserves the reference against live, staged, transaction-reserved, and existing artifacts in the authorized namespace using trustworthy bounded storage evidence, constructs the exact `R`, chooses the class-specific derivation root, and derives/seals according to the registry. Reference allocation is independent of the record ID; neither is inferred from content, labels, paths, or addresses.

The provider computes the exact complete-artifact length and domain-separated record digest and returns an unforgeable immutable pending receipt bound to them. No artifact is written by this operation. Zero-length opaque plaintext is valid under the same authentication contract. Provider output has the specified detached ciphertext/tag representation without library-added prefixes or extra nonce framing.

Cancellation or failure revokes the receipt and clears no-longer-needed owned plaintext/key material. Retrying sealing allocates a new reference and nonce rather than replaying a previous random outcome or partially produced receipt. Reserved/colliding names cannot be treated as safely reusable merely because an earlier attempt was canceled; the later storage transaction owns reservation lifetime and nondestructive orphan reconciliation. Allocation exhaustion or unknown collision state fails closed.

### `openRecord`

Preconditions: live root; a private trusted active-record capability derived from the **current** authoritative manifest; matching current snapshot token; and immutable bounded encrypted artifact bytes obtained through authorized storage. The record capability binds class, ID, schema, version, storage reference, artifact length and digest, plus provider/root/vault/epoch/generation/manifest identity. It cannot be substituted with an untrusted public entry.

The provider verifies exact artifact length and domain-separated digest against that authenticated entry, strictly validates the public envelope, constructs `R` from trusted manifest context, derives the appropriate record key using the exact envelope nonce, and authenticates/decrypts with the registry record AAD. Context is never obtained from a filename or an unauthenticated envelope claim. A digest match is not a substitute for AEAD authentication. All checks, including current token/epoch revalidation, must succeed before owned plaintext is released.

Wrong root/vault/purpose, stale authority, tombstone, context mismatch, altered envelope, incorrect digest/length, failed AEAD, or bounds violation returns the appropriate bounded failure with no plaintext or partial result. There is no automatic retry with another key, class, version, old snapshot, P0 parser, or provider. Application payloads may eventually be consumed through a separately authorized owned-plaintext boundary; the contract does not promise that all decrypted user data remains forever inside a crypto provider.

## 7. Credential rotation and unchanged content

`rewrapRoot` requires a live authenticated root and a private rewrap authorization capability issued under future session/reauthentication policy. Arbitrary parsed header bytes or possession of a material-free placeholder are insufficient. The capability binds provider, root, vault, epoch, permitted operation, and current session authority. UI flows, reauthentication timing, biometric convenience, passwordless/reset recovery, and lock timers are not selected here.

The new owned credential is processed under CHANGE rules. Creation parameters require approved calibration and exact supported admission. The provider generates a fresh approved salt and root-wrap nonce, derives new KEK/wrap/commit keys, and creates a new complete canonical header using the same authenticated vault ID and root. It regenerates every credential/KDF-dependent header field and commitment as specified.

Success returns a pending immutable header artifact, not a file overwrite or completed credential change. Later storage performs its separately reviewed atomic header replacement while preserving recovery semantics until success. Cancellation/lock revokes pending authority and prevents late publication. Manifest generation, manifest bytes, record bytes, and their stable content contexts/keys remain unchanged. Rewrap does not rewrite the manifest chain or masquerade as root/suite migration.

A stolen old valid header and its older credential still recover the same root. Passphrase rotation cannot revoke that copy or promise secure deletion from flash, snapshots, or journals. Actual compromise recovery needs a separately designed full-root/key rotation and migration operation.

## 8. Credential, KDF, randomness, and library obligations

The provider implements the **exact pinned credential profile** in the format specification; it cannot delegate persisted semantics to an unstated Android/JDK Unicode version. It applies raw resource bounds, well-formed scalar decoding, raw rejection rules, pinned NFC, normalized rejection rules, and strict UTF-8 encoding before KDF use. Ordinary spaces are preserved. CREATE/CHANGE enforce the normalized scalar minimum; OPEN does not. No alternate profile, P0 normalization, trimming, case-folding, compatibility normalization, or hidden strength filter is permitted. The approved minimum is syntactic, not an entropy guarantee.

Structural KDF admissibility and supported provider execution are separate checks. The provider enforces both the registry's checked structural bounds/divisibility rules and its stricter security floor and lane restriction, then device resource admission. It uses eligible stored values exactly. It never rounds encoded costs into an alias, reduces cost, truncates salt, substitutes another passphrase/profile, restores the P0 output length, or retries another provider. The approved Argon2 optional secret/associated-data inputs and all HKDF/HMAC/AEAD inputs are fixed by the registry. Deployment calibration remains unapproved by Phase 02 and cannot be inferred from historical P0 timings.

There is at most **one active canonical KDF per process across all provider instances**, with no internal unbounded queue. A concurrent attempt returns `ResourceUnavailable`; creating aliases or additional instances cannot bypass the permit. A canceled operation retains it until its actual worker has stopped and temporary material has been cleared to the supported best-effort extent. Caller cancellation notification does not imply a still-running native/JVM worker has relinquished the permit. Resource refusal occurs before expensive work and permits no weaker fallback. Library scheduling may change only without changing encoded Argon2 lanes or derived bytes.

Every root, salt, nonce, and random ID/reference uses the approved provider randomness path. RNG failure is fatal to the operation. No timestamp, counter, deterministic/test RNG, UUID shortcut, or weaker-provider fallback is acceptable. The format registry specifies nonzero/collision retry limits for IDs/references; no global vault-ID registry is implied. Zero salt/nonce byte patterns alone do not prove RNG failure. Random nonces have probabilistic collision resistance, not a global uniqueness proof; retry and reservation behavior must prevent deliberate reuse.

Primitive adapters must produce exactly the registry bytes and use the exact provider-selected nonce in both key derivation and AEAD. A high-level AEAD API that silently chooses a second nonce, adds its own framing/prefix, or prevents that binding is not compliant merely because it uses the same algorithm name. Existing P0 Tink feasibility evidence does not establish this requirement. A later implementation must demonstrate supported public API feasibility on both platforms without internal/reflection-based library bypasses, changed dependencies by convenience, or silently altered transcripts.

No general-purpose key export or persisted library keyset substitutes for the vault hierarchy. Existing dependency pins do not certify a future adapter. Provider-level vectors, library/version/source review, runtime RNG/resource evidence, normalization conformance, and dedicated cryptographic assessment remain required before production acceptance. Ordinary wrong-key rejection tests cannot establish a formal key-commitment proof.

## 9. Sensitive buffers, cancellation, and revocation

At invocation handoff, `ownedCredential`, `newOwnedCredential`, `ownedPlaintext`, and the owned buffers/assembly in `validatedOwnedManifest` transfer exclusively to the provider. The caller MUST NOT reuse or mutate them after handoff. Consumption applies on every result, including validation failure, `ResourceUnavailable`, and cancellation; it is not conditional on success. The provider closes and best-effort clears consumed sensitive buffers when no longer needed. If an external input cannot transfer exclusive ownership, the boundary must first make an owned defensive copy; it cannot ambiguously borrow a mutable secret across asynchronous work. Public encrypted artifact inputs are immutable snapshots or copied before validation.

Root, epoch, snapshot, transaction and other authorization capabilities referenced by an operation are borrowed authority, not consumed buffers. Consuming a manifest assembly does not close the caller's live root/session or transfer authority ownership. Their owners retain the ability to revoke them during the operation, and the provider must revalidate before publishing a result. Owned plaintext output transfers to the caller only on fully authenticated, validated and still-authorized success; failure transfers no partial secret result. Internal ownership cannot erase a caller-retained original, immutable `String`, or separately made copy. No credential, raw key, temporary PRK, failed plaintext, or secret-bearing library object is retained in a public request/result or diagnostic.

KEK, vault root, derivation roots, wrap/commit keys, and manifest/record keys never leave the provider. Temporary credential/KDF/PRK/key buffers are cleared when no longer needed after success, and on failure, cancellation, close, and revocation to the extent supported by the runtime. Root-derived material retained for a valid capability is cleared when that capability's lifetime ends. Failed AEAD plaintext is discarded and cleared before any result can escape. Close/revoke is safe to repeat and grants no authority.

JVM/Android immutable strings, garbage collection, copied buffers, native library behavior, and OS snapshots prevent a general perfect-zeroization guarantee. Future APIs must minimize immutable secret strings and copies and document remaining limitations honestly. This document does not redesign secure input or enable a UI credential field.

Lock/cancellation/epoch revocation invalidates pending and in-flight results. A late KDF or decrypt completion must clear its unpublished material; it cannot recreate a session, release plaintext, authorize storage, or reactivate a revoked handle. A successful content commit similarly invalidates old snapshot authority before an in-flight result can authorize an ordinary old-snapshot read or write. Results already explicitly released through an authorized owned boundary cannot be retroactively erased from caller copies; session integration must account for owned-result revocation/clearing limits.

Interrupted operations expose no partially successful secret result. Provider and coordinator need a reviewed atomic authority-publication boundary so commit, lock, and result release cannot race around those checks. Concrete scheduling, concurrent-writer locking, platform lifecycle triggers, inactivity values, and durable transaction mechanics remain later implementation work, but cannot weaken these required semantics.

## 10. Bounded failure contract

Errors are Skald-owned finite categories. They never contain raw exception text/stack traces, object dumps, input snippets, offending code points, actual credential counts, key material, secret identifiers, tags, hashes as credential verifiers, paths, or partial plaintext. A diagnostic may identify a nonsecret public structural field or safe operation category. Internal sensitive context is not made safe by placing it in an exception cause.

| Public result category | Required meaning and limit |
| --- | --- |
| `MalformedPublicFormat` / `UnsupportedPublicFormat` | Public framing/schema/length/order/ID/version is malformed or unsupported. Does not authenticate or repair input. |
| `UnsupportedProfile` | Stored credential profile is unknown. Do not try another normalization profile. |
| `UnsupportedCostPolicy` | Structurally representable KDF values are outside supported security/lane admission. No KDF attempt with adjusted values. |
| `CredentialRejected(reason)` | Profile validation fails with only `malformedUnicode`, `forbiddenCodePoint`, `rawLimit`, `normalizedLimit`, or `belowCreationMinimum`. The last reason applies only to CREATE/CHANGE. No offending character or actual length is returned. |
| `ResourceUnavailable` | Safe memory/work/entropy/allocation capacity or the process-wide KDF permit is unavailable. No weaker retry; absence of trustworthy collision/reservation state also fails the operation without allocation fallback. |
| `ProviderUnavailable` | Provider is disabled, unaccepted, or required primitive/randomness path unavailable. No alternate provider is selected by this result. |
| `CapacityExceeded` | A new record would exceed lifetime manifest entry capacity, including tombstones. Existing legal updates/deletions remain distinct. |
| `CounterExhausted` | Required generation or record-version increment would exceed the supported counter domain. Never wrap or saturate. |
| `StaleOrClosedCapability` | Wrong provider/root/vault/purpose, revoked/closed epoch, stale snapshot, invalid pending receipt, or missing private operation authority. No secret/context details. |
| `AuthenticationFailed` | Valid-profile OPEN credential fails root commitment or root AEAD authentication. Wrong credential and both authentication stages share this result. |
| `CorruptManifest` / `ManifestAuthenticationFailed` | After root authentication, manifest bytes fail complete schema/integrity or AEAD verification. No partial manifest authority or recovery fallback. |
| `CorruptRecord` / `RecordAuthenticationFailed` | After root authentication and current trusted-entry admission, record bytes fail length/digest/schema/context or AEAD verification. No plaintext or automatic old-record fallback. |
| `Canceled` | The authorized operation was canceled; no successful secret result is released. Resource/worker cleanup and permit release follow actual termination, not merely return of this notification. |

Unknown/unclassified internal provider failures must fail closed through an appropriate bounded unavailable/corrupt category; they must not be surfaced verbatim. Invalid authenticated transition proposals fail without sealing or committing; their public result is a bounded malformed/unsupported request or stale authority category, with no raw entry data. Internal codes used for deterministic tests must not become a stage oracle for root authentication.

These rules do not claim that public-format rejection and an admitted expensive KDF have identical total timings. Commitment comparison must be constant-time over its full tag, while implementation timing and side-channel properties need dedicated evidence. The format's public profile/cost fields are not secrets, but they do not justify secret-bearing error detail.

## 11. Storage, session, and platform responsibilities

Future storage confines artifacts to the authorized app-controlled vault root, never paths constructed from untrusted header/manifest strings. Record ID and storage reference are distinct. A future path adapter may use the exact storage-reference representation allowed by the registry, but its spelling is not identity/authentication authority. Symlink/traversal protections, ownership/permissions, bounded reads, and trustworthy reservation/collision checks are storage obligations. This phase selects no directory name, platform path, settings key, or concrete filesystem implementation.

New immutable records must become durable before replacement of the single authoritative encrypted manifest. That manifest replacement is the logical semantic-content commit point. The coordinator validates the predecessor/current token and legal transition, then publishes the new snapshot authority only with the successful storage commit. Header rotation has a separate atomic replacement contract. Atomic visibility alone does not establish crash durability; Android/Linux filesystem and parent-directory synchronization guarantees require actual later platform evidence.

Staged artifacts and orphan recovery remain nondestructive until authoritative state is established. Authentication/parse failure cannot trigger a wipe, empty-vault initialization, automatic old-snapshot fallback, P0 migration, or deletion of suspicious artifacts. A consistent old whole-vault snapshot can still authenticate; generation and predecessor hashes do not provide anti-rollback without a separately selected external monotonic anchor.

The provider performs no storage commit or filesystem probing. Storage handles encrypted/public artifact bytes and authorized opaque references, not passphrases, raw keys, or plaintext payloads. Session orchestration governs current snapshot authority, logical mutation/rewrap authorization, revocation, and publication; pure parsing only validates structure. No one layer may claim another's completed acceptance merely because it received a well-typed value.

The [acceptance plan](ENCRYPTED_LOCAL_VAULT_CANONICAL_V1_ACCEPTANCE_PLAN.md) separates codec, provider, storage, and session evidence. Later phases still need exact API implementation review, independent canonical vectors/KATs, dedicated cryptographic review of the commitment application profile, Android/Linux byte parity and resource behavior, calibrated deployment costs, storage atomicity/crash recovery, lifecycle integration, backup/export, and controlled app integration. All canonical execution tests remain **NOT IMPLEMENTED / NOT RUN** in this documentation phase.
