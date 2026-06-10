# Encrypted Local Vault Production Provider Acceptance Contract

## Status

This document defines the Skald Vault v1 production-provider acceptance contract.

It is design and acceptance-contract material with isolated still-disabled building blocks. The passphrase policy validator/NFC UTF-8 encoder, Bouncy Castle Argon2id explicit-parameter passphrase-to-root-material derivation, Argon2id calibration policy/candidate-selection/memory-failure/no-downgrade model, canonical header serializer, HKDF-SHA-256 expansion from caller-supplied 64-byte root material, HMAC-SHA-256 header commitment computation/verification, strict AAD serialization, Tink XChaCha20-Poly1305 record AEAD construction from caller-supplied 32-byte key material, in-memory vault container parser/writer, in-memory manifest parser/writer, local manifest-relative stale-record decision policy, storage namespace/path validation plus relative safe-segment encoding policy, rootless logical storage layout planning, and path-containment planning now exist as production-source building blocks. A still-disabled shared-test in-memory storage atomicity/crash simulator validates the future write/recovery state machine with fixed non-secret byte arrays, interruption injection, parser validation, and typed recovery decisions. A still-disabled integrated provider KAT harness now composes those building blocks with fixed non-secret fixtures and executes deterministic provider-level vectors plus randomized AEAD behavioral checks in the required verification order. A still-disabled provider facade now exposes metadata/status/typed-disabled-result evidence only. This contract also models the future storage, atomicity, crash-recovery, secure-storage boundary, durability fail-closed policy, warning-only durability rejection, and rollback-limitation contracts as documented-only evidence. It still does not implement a selectable production provider, production provider approval, final production calibration approval, production random-byte generation, key generation, vault creation, vault unlock, vault persistence, actual path construction, absolute path construction, platform root selection, real path containment checks, durability probe, warning-only encrypted vault persistence, file-backed vault container read/write, manifest file/storage read/write, storage index read/write, storage success, Android Keystore or StrongBox wrapping, biometric unlock, secure secret storage success, secure metadata storage success, production sync, backend clients, signing, broadcasting, Tor transport, Nostr parsing, public endpoints, Skald-operated infrastructure, or mainnet.

The focused v1 header commitment, canonical header encoding, key-separation label, and strict AAD construction contract is documented in [`ENCRYPTED_LOCAL_VAULT_HEADER_COMMITMENT_AAD_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_HEADER_COMMITMENT_AAD_CONTRACT.md). The selected HKDF-SHA-256 key-expansion primitive, HMAC-SHA-256 header-commitment primitive, output layout, and threat-model rationale are documented in [`ENCRYPTED_LOCAL_VAULT_KEY_EXPANSION_COMMITMENT_POLICY.md`](ENCRYPTED_LOCAL_VAULT_KEY_EXPANSION_COMMITMENT_POLICY.md). The deterministic non-secret canonical header, HKDF, and HMAC vectors are documented in [`ENCRYPTED_LOCAL_VAULT_CANONICAL_HEADER_HKDF_HMAC_VECTORS.md`](ENCRYPTED_LOCAL_VAULT_CANONICAL_HEADER_HKDF_HMAC_VECTORS.md). The provider-level KAT strategy, still-disabled integrated KAT harness, randomized Tink AEAD behavioral checks, verification-order checks, and stale-record/rollback manifest contract are documented in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md). The detailed v1 vault container, manifest, storage, stale-record, rollback, atomicity, crash-recovery, and secure-storage boundary contract is documented in [`ENCRYPTED_LOCAL_VAULT_CONTAINER_MANIFEST_STORAGE_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_CONTAINER_MANIFEST_STORAGE_CONTRACT.md). These documents are part of this acceptance contract; the vector-matched building blocks, integrated harness, and contract models remain non-selectable.

Runtime behavior remains fail-closed:

- `VaultCryptoProviderSelectionRegistry` selects only `DisabledVaultCryptoProvider`.
- Tink plus Bouncy Castle is a blocked future candidate, not a production-selectable provider.
- The still-disabled provider facade is not referenced by the provider-selection registry and is not a selectable provider.
- `SecureSecretStorage` remains disabled.
- `SecureWalletMetadataRepository` remains disabled.
- Vault creation and production persistence remain disabled.
- Mainnet remains disabled.

## Container, Manifest, Storage, And Rollback Policy IDs

The v1 storage-contract gate records these stable ids. Container and manifest byte formats plus local stale-record decisions are implemented/tested as still-disabled in-memory building blocks; storage, atomicity, secure-storage, and anti-rollback anchor evidence remains documented/model-only:

```text
skald-vault-v1-container-contract-v1
skald-vault-v1-manifest-contract-v1
skald-vault-v1-local-manifest-storage-policy-v1
skald-vault-v1-stale-record-manifest-policy-v1
skald-vault-v1-atomicity-crash-recovery-policy-v1
skald-vault-v1-platform-storage-boundary-policy-v1
skald-vault-v1-atomic-write-strategy-policy-v1
skald-vault-v1-crash-recovery-policy-v1
skald-vault-v1-storage-interruption-test-policy-v1
skald-vault-v1-storage-failure-model-policy-v1
skald-vault-v1-storage-namespace-path-policy-v1
skald-vault-v1-storage-layout-plan-v1
skald-vault-v1-path-containment-planner-v1
skald-vault-v1-platform-storage-root-policy-v1
skald-vault-v1-safe-path-construction-policy-v1
skald-vault-v1-symlink-traversal-policy-v1
skald-vault-v1-storage-permission-ownership-policy-v1
skald-vault-v1-durability-capability-policy-v1
skald-vault-v1-durability-fail-closed-policy-v1
skald-vault-v1-warning-only-durability-rejection-policy-v1
skald-vault-v1-in-memory-storage-atomicity-simulator-policy-v1
skald-vault-v1-secure-storage-boundary-policy-v1
skald-vault-v1-anti-rollback-anchor-policy-v1
```

Missing, unknown, failed, unsupported, unreviewed, insufficient, unsafe, documented-only, or unimplemented evidence for the container contract, manifest contract, storage policy, stale-record policy, atomicity/crash-recovery policy, in-memory simulator execution, logical storage layout planning, path-containment planning, platform root/path/symlink/permission/durability contracts, secure-storage boundary, or anti-rollback limitation review blocks provider selectability and persistence. Warning-only durability evidence and user-consent override evidence are not sufficient for encrypted vault persistence. Model-only evidence, simulator evidence, rootless layout evidence, and path-containment planner evidence do not enable persistence.

## V1 Provider Suite

Skald Vault v1 uses one pinned provider suite identity for review:

```text
skald-vault-v1-bouncycastle-argon2id-tink-xchacha20poly1305-os-securerandom
```

| Role | Selected v1 direction | Pinned identity |
| --- | --- | --- |
| Passphrase KDF | Bouncy Castle Argon2id | `org.bouncycastle:bcprov-jdk18on:1.84`, Argon2id version 19 |
| Record AEAD | Tink XChaCha20-Poly1305 | `com.google.crypto.tink:tink:1.21.0` and `com.google.crypto.tink:tink-android:1.21.0` |
| Runtime randomness | OS SecureRandom | Provider and algorithm evidence required at runtime |
| Provider model | One pinned suite | No day-one pluggable provider selection |
| Android wrapping | Optional future convenience layer | Not required, not an entropy source, not a passphrase replacement |

All gates in this contract are necessary and not sufficient in this branch. Even a fully satisfied contract assessment cannot make a provider selectable here because selection remains hard disabled until a later implementation and review branch.

## Why Bouncy Castle Argon2id

Bouncy Castle Argon2id is the v1 KDF direction because it keeps the v1 package surface simple on Android and Linux desktop:

- it is already pinned as a JVM/Android dependency,
- it avoids native-library, JNI, ABI, loader, and Debian packaging risk in v1,
- it has existing dependency-level public KAT evidence against RFC 9106 section 5.3 on desktop JVM and Android runtime,
- it keeps the production provider skeleton behind a Skald-owned provider interface instead of binding the app to native crypto packaging on day one.

Accepted tradeoffs remain:

- parameter calibration is not final,
- memory-pressure and allocation failure behavior still needs explicit fail-closed review,
- passphrase encoding policy must be specified before production use,
- selectable production-provider approval must include provider-boundary KAT coverage, not only dependency probes, test-only providers, or the still-disabled integrated harness,
- Android and desktop performance can vary by CPU, RAM pressure, scheduler, build type, and thermal state.

Before Bouncy Castle Argon2id can be selectable, Skald must approve final production parameter selection, provider/version pinning, passphrase encoding rules, and provider-level KAT coverage. The still-disabled calibration policy building block now models floor enforcement, candidate selection, fail-closed memory behavior, stored-parameter authority, and no silent downgrade, but it is not final provider approval.

## Argon2id Parameter And Unlock Policy

The v1 minimum review floor is shared across Android and Linux desktop:

```text
Argon2id version 19, 64 MiB memory, t=3, p=1, 64-byte derived root material
```

New vault creation must use a salt of at least 16 bytes. A 32-byte salt is preferred for new vaults. The production provider must bind the salt into the vault header commitment before any record decrypt.

Skald uses bounded per-platform calibration rather than fixed minimum-only parameters. The 64 MiB / t=3 / p=1 floor is the minimum acceptable review floor on every supported platform, but desktop may select stronger parameters than Android when bounded calibration and UX evidence justify it. The same floor across platforms avoids silently creating weaker vaults merely because a user created or opened a vault on Android first.

Skald targets roughly a 1 second unlock where feasible, but an unlock/decrypting duration up to roughly 2 seconds is acceptable. Two seconds is not a failure condition, and parameters must not be weakened merely to force sub-1-second unlocks.

If a device cannot allocate or complete the minimum floor during new vault creation, creation must fail closed. If an existing vault's stored parameters cannot allocate or complete on a weaker device, unlock must fail closed with a clear user-facing message. Skald must not silently reduce memory, time cost, parallelism, salt length, output length, or policy version to make the unlock complete.

Existing vault parameters are authoritative:

- stored vault parameters must never be silently downgraded,
- a weaker device that cannot satisfy stored vault parameters must fail closed,
- the failure must have a clear user-facing message,
- parameter upgrades require explicit vault-format migration review,
- any future downgrade or migration must require successful passphrase unlock and explicit user action.

The earlier 16 MiB and 32 MiB calibration probes remain useful timing evidence only. They do not satisfy the v1 production floor, do not approve a weaker Android default, and do not enable calibration or provider-wired KDF execution.

This contract does not make current candidate parameters production final. It records the future approval rule and keeps bounded calibration evidence missing until it is explicitly reviewed.

## Why Tink XChaCha20-Poly1305

Tink XChaCha20-Poly1305 is the v1 AEAD direction because it provides a high-level AEAD API and reduces direct low-level record-encryption wiring risk.

XChaCha20-Poly1305 is preferred for records because its large nonce space fits Skald's random per-record nonce design. That is a better fit for a local multi-record vault than fragile manually managed AES-GCM nonce discipline. AES-GCM may still be reviewed later for platform wrapping or fallback roles, but it is not the v1 record envelope direction.

Accepted tradeoffs remain:

- Tink keyset or raw-key handling must not become a parallel secret store outside the Skald vault,
- Tink XChaCha20-Poly1305 is non-key-committing,
- provider-level KATs and misuse tests must run through the future production provider,
- record AAD and vault-format authentication must be designed before any record decrypt path is selectable.

## Non-Key-Committing AEAD Requirement

Tink XChaCha20-Poly1305 must be treated as non-key-committing. Successful AEAD decryption alone must not be treated as proof that the passphrase-derived vault key is the intended vault key for the vault.

Before production selectability, the vault format must implement and test a separate vault-level header commitment over canonical vault header data. Record decrypt is allowed only after header commitment verification succeeds. The commitment must use key material separated from record AEAD key material.

The committed canonical header data must include:

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

The commitment must fail closed if the header is modified, omits a required field, duplicates a required field, includes an unknown field in a non-extensible section, is non-canonical, has an unknown suite id, has unsupported KDF algorithm/version/parameters, has unsupported policy ids, has malformed length/integer/string encoding, has an unknown or unsupported future version, or if commitment verification is unavailable. Unknown, missing, documented/model-only, failed, unsupported, or unimplemented key-commitment evidence blocks production provider selectability.

The v1 construction policy ids are:

```text
skald-vault-v1-header-commitment-v1
skald-vault-v1-canonical-header-encoding-v1
skald-vault-v1-key-separation-labels-v1
skald-vault-v1-record-aad-v1
skald-vault-v1-record-format-v1
```

Header commitment bytes must be canonical and deterministic across Linux desktop JVM and Android. The canonical header encoding contract requires an explicit domain/magic prefix, big-endian integers, explicit integer widths, UTF-8 strings, ASCII policy and suite ids, length prefixes, explicit field order, explicit optional-field handling, rejection of unknown non-extensible fields, maximum lengths for variable fields, versioning policy, and canonical header byte test vectors before production selectability. Default object serialization, non-canonical JSON, platform-native serialization, and unsorted map iteration are forbidden for committed bytes.

The v1 key-separation labels are:

```text
skald-vault/v1/root-domain
skald-vault/v1/header-commitment-key
skald-vault/v1/record-aead-key
skald-vault/v1/reserved/wrapping-metadata
skald-vault/v1/reserved/export-migration
skald-vault/test-only/raw-key-probe
```

The test/probe label is not a production label. The reserved labels are not implemented.

The selected v1 key-expansion primitive is HKDF-SHA-256:

```text
skald-vault-v1-hkdf-sha256-key-expansion-v1
```

Argon2id remains the expensive password KDF and produces 64 bytes of root material. HKDF-SHA-256 is used only after that root material exists. It expands stable, domain-separated labels into a 32-byte header commitment key and a 32-byte record AEAD key. It is not used directly on the passphrase and does not replace Argon2id.

The selected v1 header-commitment primitive is HMAC-SHA-256 over canonical vault header bytes:

```text
skald-vault-v1-hmac-sha256-header-commitment-v1
```

HMAC-SHA-256 uses the derived 32-byte header commitment key. It authenticates the exact canonical header before record decrypt and is the vault-format mitigation for Tink XChaCha20-Poly1305 being non-key-committing.

This branch implements passphrase policy validation/normalization, explicit-parameter Argon2id root derivation, still-disabled Argon2id calibration policy/candidate selection/fail-closed memory/no-downgrade modeling, HKDF, HMAC, canonical header serialization, header-commitment verification, strict AAD serialization, Tink record AEAD, an in-memory container parser/writer, an in-memory manifest parser/writer, a local manifest-relative stale-record decision policy, a still-disabled integrated provider KAT harness, and a metadata-only still-disabled provider facade. The facade reports the suite id, stable policy ids, building-block evidence, disabled reasons, remaining gates, and typed disabled results; it does not accept secret inputs or perform vault operations. The in-memory container and manifest parser/writer building blocks validate caller-supplied byte arrays and fixed non-secret format fixtures only; they perform no file I/O, manifest storage, secure storage, KDF/HKDF/HMAC/Tink execution, vault creation, vault unlock, or persistence. Storage, storage-backed stale-record enforcement, atomicity, crash-recovery, secure-storage boundary, and rollback-limitation policy remain contract/model evidence. This branch does not implement final production calibration approval, selectable production-provider approval, a user vault unlock flow, vault creation, manifest file/storage read/write, storage behavior, or persistence.

The non-secret vector contract fixes the first canonical header byte fixture and the HKDF/HMAC outputs for a sentinel 64-byte root-material fixture. The building blocks match those vectors. The Argon2id root-derivation fixture is Skald-owned deterministic evidence using fixed non-secret passphrase text, a fixed non-secret 32-byte salt, Argon2id version 19, 64 MiB, t=3, p=1, and 64-byte output. These fixtures do not implement provider-selectable AEAD, vault unlock, vault storage, or provider selectability.

Every future record AEAD operation must bind strict AAD to vault magic/domain marker, vault format version, provider suite id, vault id, record format policy id/version, AAD policy id/version, key-expansion policy id, header-commitment primitive policy id, header commitment policy id, canonical header commitment value or stable commitment identifier, record type, record id, record version or monotonic counter, integrity-critical record metadata, and any future storage namespace where relevant. AAD mismatch must fail closed for wrong vault, suite, record type, record id, record version/counter, record metadata, AAD policy, header commitment context, copied ciphertext between vaults/records/types, and stale-record replay where the record version/counter policy rejects stale data.

The still-disabled strict AAD serializer now has deterministic non-secret fixture bytes, and the Tink record AEAD building block now has behavioral tests for round trip and mismatch/tamper failures. Tink ciphertext remains nondeterministic because the public Tink AEAD path internally chooses the XChaCha nonce. The still-disabled integrated harness proves header commitment verification happens before record AEAD use and exits before record AEAD when the commitment fails. The in-memory manifest policy now classifies current, newer, stale, conflicting, tombstone-conflicting, and unknown record descriptors relative to an in-memory manifest, but this branch does not implement vault unlock UI/flow, container/storage implementation, manifest file/storage read/write, storage-backed stale-record enforcement, or full stale-record/rollback enforcement. The detailed header/AAD contract is in [`ENCRYPTED_LOCAL_VAULT_HEADER_COMMITMENT_AAD_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_HEADER_COMMITMENT_AAD_CONTRACT.md), and the detailed container/manifest/storage contract is in [`ENCRYPTED_LOCAL_VAULT_CONTAINER_MANIFEST_STORAGE_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_CONTAINER_MANIFEST_STORAGE_CONTRACT.md).

Before production selectability, the broader vault format must also implement and test:

- strict binding between the provider suite id and vault header,
- rejection of mismatched suite metadata before record decrypt,
- fail-closed behavior for wrong passphrase, wrong key, wrong suite, and tampered header cases.

The future key-commitment design is a vault-format requirement. It is not implemented by this branch.

## Passphrase Encoding Policy

The v1 passphrase encoding policy id is:

```text
unicode-nfc-utf8-no-controls-no-whitespace-v1
```

The still-disabled passphrase policy component implements this policy for future provider use:

- normalize the passphrase to Unicode NFC,
- encode the normalized result as UTF-8,
- reject empty passphrases,
- reject all Unicode control characters,
- reject all Unicode whitespace characters,
- reject all Unicode separator characters,
- reject invisible format characters,
- avoid trimming leading or trailing characters,
- avoid lowercasing,
- avoid uppercasing,
- avoid collapsing repeated characters,
- avoid locale-sensitive transforms,
- avoid silently removing characters.

The policy allows visible Unicode letters, visible Unicode combining marks when valid after NFC normalization and not rejected by the forbidden-character rules, visible Unicode numbers, visible Unicode punctuation, visible Unicode symbols, and emoji only when represented without rejected control, whitespace, separator, or invisible format characters. It does not add special-case emoji exceptions.

Spaces are intentionally rejected to reduce recovery ambiguity from leading spaces, trailing spaces, non-breaking spaces, zero-width spaces, copied whitespace, mobile keyboard alterations, and platform differences. Users who want visible separation can use visible punctuation such as hyphens, periods, or underscores.

This branch does not wire passphrase validation into vault creation or user unlock because those paths do not exist. Missing, unknown, unsupported, or failed passphrase-encoding evidence remains a production-selectability blocker; implemented passphrase validation evidence alone is not sufficient because calibration, selectable provider approval, manifest/storage, and release gates are still absent.

## Argon2id Passphrase-To-Root Building Block

The still-disabled Argon2id root-derivation component uses Bouncy Castle `Argon2BytesGenerator` with explicit caller-supplied normalized passphrase bytes, salt bytes, and parameters. It enforces:

- Argon2id only,
- Argon2 version 19 / version 1.3,
- memory at least 64 MiB,
- iterations/time cost at least t=3,
- parallelism exactly p=1 for v1,
- salt length at least 16 bytes,
- 32-byte salt preferred for new vaults,
- 64-byte root material output.

The component does not choose parameters automatically, does not calibrate, does not downgrade, does not generate salts, does not persist root material, does not log passphrases or derived bytes, and is not wired into vault creation, unlock, provider selection, or storage. The fixed non-secret fixture is Skald-owned deterministic evidence, not an external standards KAT.

Missing, unknown, unsupported, failed, or unverified Argon2id root-derivation evidence remains a production-selectability blocker. Implemented fixture-tested evidence, still-disabled calibration policy evidence, and still-disabled provider KAT execution still do not make a provider selectable without final production calibration approval, selectable provider approval, manifest/storage review, and release gates.

## Tink Raw-Key Handling Policy

The v1 preferred design is passphrase-derived raw AEAD key material with no persisted Tink keyset, but only if public supported Tink APIs cleanly support constructing the pinned XChaCha20-Poly1305 primitive from caller-supplied derived key bytes.

Required v1 policy:

- do not persist plaintext Tink keysets,
- do not persist encrypted Tink keysets unless raw-key construction is rejected by explicit human review,
- do not generate random Tink vault keys,
- do not add Tink key rotation,
- do not add multiple active AEAD keys,
- do not use internal, unsupported, reflective, or unstable Tink APIs,
- do not add a fallback encrypted Tink keyset model in this branch,
- do not make AEAD encryption or decryption provider-selectable or wire it to vault creation/storage.

The test-scope feasibility probes are documented in [`ENCRYPTED_LOCAL_VAULT_TINK_RAW_KEY_FEASIBILITY_PROBE.md`](ENCRYPTED_LOCAL_VAULT_TINK_RAW_KEY_FEASIBILITY_PROBE.md). Their exact results are:

```text
FEASIBLE_PUBLIC_RAW_KEY_API
ANDROID_FEASIBLE_PUBLIC_RAW_KEY_API
```

The tested public API path is the same on desktop/JVM and Android:

```text
AeadConfig.register()
SecretBytes.copyFrom(fixedNonSecretKeyBytes, InsecureSecretKeyAccess.get())
XChaCha20Poly1305Key.create(XChaCha20Poly1305Parameters.Variant.NO_PREFIX, secretBytes, null)
KeysetHandle.importKey(key).withFixedId(fixedNonSecretKeyId).setStatus(KeyStatus.ENABLED).makePrimary()
KeysetHandle.newBuilder().addEntry(importedEntry).build()
keysetHandle.getPrimitive(RegistryConfiguration.get(), Aead::class.java)
```

This path uses caller-supplied fixed non-secret key bytes, a fixed non-secret key id, and a transient in-memory Tink `KeysetHandle`. It does not persist a Tink keyset, does not use `CleartextKeysetHandle`, does not use keyset readers or writers, does not generate a Tink vault key, does not call Tink key rotation APIs, and does not use internal Tink APIs or reflection. Android instrumented parity confirms wrong associated data fails through the same public `Aead` primitive.

These results satisfy only the cross-platform raw-key feasibility gate. The still-disabled record AEAD building block uses the same public API family, but it does not approve production provider selection, does not approve vault persistence, and does not remove the vault-level key commitment/header authentication requirement.

## AAD And Tamper Requirements

Every encrypted record must bind associated data to integrity-critical context:

- vault header and vault format version,
- provider suite id,
- record type,
- record id,
- record version or counter,
- integrity-critical metadata.

Tamper tests must cover:

- vault header,
- ciphertext,
- nonce,
- tag,
- AAD,
- record metadata,
- provider-suite metadata.

The provider must reject authentication failures without repair, downgrade, alternate-provider fallback, or secret-bearing diagnostics.

## Why OS SecureRandom

OS SecureRandom is the v1 runtime randomness direction because Android/JVM OS cryptographic randomness is the most compatible and packageable path for Skald's supported platforms.

On Android, this means Android OS cryptographic randomness exposed through `SecureRandom` or an approved provider path. This document does not claim Android randomness is always hardware-backed.

On Linux/JVM, this means JVM `SecureRandom` through a reviewed provider path backed by kernel/OS CSPRNG behavior, such as `getrandom` or `/dev/urandom` through the provider/library stack. This branch does not implement direct Linux entropy collection.

Provider-wrapped or hybrid randomness is rejected for v1 because it mostly adds abstraction around OS entropy without enough additional control to justify the added audit surface, failure modes, and implementation complexity.

Before production selectability, runtime randomness review must record provider and algorithm evidence for supported Android and Linux paths. Unknown, unavailable, forbidden, or unverified randomness/provider state must block vault creation and produce a user-facing warning.

Tiny non-secret test samples are availability evidence only. They do not prove entropy quality, hardware entropy, nonce uniqueness over time, or production key-generation safety.

## Forbidden Randomness

Vault salts, nonces, future reviewed random vault material, unlock-related randomness, and vault records must not use language-level or ad hoc randomness.

Forbidden source classes include:

- Kotlin general-purpose random APIs,
- Java general-purpose random APIs,
- math-library random APIs,
- timestamp-derived values,
- UUID-derived values,
- ad hoc PRNGs,
- any unreviewed non-cryptographic random source.

If OS cryptographic randomness or reviewed provider randomness cannot be obtained and verified through the approved platform/provider path, vault creation must fail closed. Skald must not silently fall back to language PRNGs.

## Passphrase-First Vault Protection

The passphrase remains the primary authority and recovery mechanism for v1. Password-only mode is first-class.

This preserves a user-controlled recovery path that does not depend on a device-specific biometric enrollment, hardware-backed module, account state, or platform key availability. It also keeps the encrypted local vault portable across supported desktop and Android recovery scenarios after the user supplies the passphrase.

Passphrase-first protection does not remove the need for future lock/session lifecycle review, memory-clearing review, timeout behavior, wrong-passphrase handling, and redaction tests.

## Android Optional Wrapping

Android hardware-backed or biometric wrapping is separate from randomness. Android Keystore and StrongBox are optional future key-wrapping or key-protection mechanisms, not entropy sources for vault records.

The v1 acceptance contract requires:

- Android hardware-backed wrapping is optional, never mandatory,
- StrongBox is preferred if available only after review, not required,
- biometric unlock must never replace the passphrase as recovery authority,
- password-only mode remains supported,
- optional biometric unlock must require roughly weekly passphrase re-entry after biometric unlock so users do not forget the passphrase.

No Android Keystore, StrongBox, biometric, or wrapping implementation is added by this contract.

## Why One Pinned Suite

Skald Vault v1 intentionally avoids pluggable crypto providers.

One pinned suite reduces:

- audit surface,
- KAT matrix size,
- dependency and packaging review scope,
- vault-format migration complexity,
- accidental selectability risk,
- ambiguity around which provider wrote a vault record.

Future provider migration or agility may be designed later, but v1 must have one reviewed suite and one explicit suite id. Any future migration path must preserve header authentication, provider-suite metadata binding, downgrade resistance, and user-visible recovery behavior.

## Required Acceptance Gates

A production provider cannot become selectable until every gate below is satisfied:

1. Exact provider suite id and dependency versions are pinned.
2. Argon2id algorithm, version, and parameter policy are approved.
3. Bounded Argon2id calibration policy is approved.
4. Argon2id KATs pass through the production provider.
5. Argon2id calibration bounds and memory-failure behavior are approved.
6. XChaCha20-Poly1305 primitive/template/version is pinned.
7. AEAD KATs pass through the production provider.
8. Vault-level header commitment policy is approved.
9. HKDF-SHA-256 key-expansion primitive policy is implemented and tested.
10. HMAC-SHA-256 header-commitment primitive policy is implemented and tested.
11. The 64-byte root, 32-byte header commitment key, and 32-byte record AEAD key output layout is implemented and tested.
12. Primitive threat-model and rationale review is complete.
13. Canonical header byte vectors are complete and matched by still-disabled building-block implementation tests.
14. HKDF-SHA-256 vectors are complete and matched by still-disabled building-block implementation tests.
15. HMAC-SHA-256 header-commitment vectors are complete and matched by still-disabled building-block implementation tests.
16. Canonical header encoding policy is approved.
17. Key-separation labels policy is approved.
18. Passphrase encoding policy is approved and implemented as a still-disabled building block.
19. Argon2id explicit-parameter passphrase-to-root-material derivation is implemented and tested as a still-disabled building block.
20. Tink raw-key feasibility is approved through public supported APIs.
21. Vault-level key commitment and header authentication are implemented and tested.
22. AEAD AAD policy binds vault header/version, provider suite id, vault id, key-expansion policy id, header-commitment primitive policy id, record type, record id, record version/counter, header commitment context, and integrity-critical metadata.
23. Tink non-key-commitment mitigation is approved at the vault-format layer.
24. Tamper tests cover header, ciphertext, nonce, tag, AAD, record metadata, and provider-suite metadata.
25. Provider-level KAT strategy is approved and still-disabled harness KATs execute deterministic vectors plus randomized AEAD behavioral checks.
26. Randomized AEAD behavioral KAT policy is approved and still-disabled harness KATs do not require fixed ciphertext hex for Tink XChaCha20-Poly1305.
27. Integrated verification-order KATs prove in the still-disabled harness that header commitment verification happens before record decrypt.
28. Stale-record and rollback manifest policy is approved, including local manifest binding, stale-record quarantine/rejection behavior, and no global rollback-resistance claim without an anti-rollback anchor.
29. Platform storage boundary contract is approved: future storage accepts only encrypted/non-secret bytes and never handles passphrases, root material, subkeys, plaintext records, keysets, wallet seeds, private keys, Nostr secrets, Cashu proofs, or backend credentials.
30. Atomic write strategy contract is approved for desktop filesystem, Android app-private filesystem, future database strategy review, and unsupported-platform fail-closed behavior.
31. Crash-recovery contract is approved for committed state, temporary state, container/manifest validation, manifest reference validation, stale-record policy, safe previous state, quarantine, and user-facing unrecoverable corruption.
32. Interruption-test contract is approved for every write/recovery phase before persistence approval.
33. In-memory storage atomicity/crash simulator evidence executes every documented interruption point and typed recovery decision with fixed non-secret byte arrays, without platform storage or persistence.
34. Storage failure model is approved with typed categories for unavailable storage, permissions, read/write failures, durability sync, atomic replace, malformed/missing manifest/container/record data, stale/conflicting records, quarantine, user action, unknown state, platform-root failures, path-construction failures, symlink state, permission state, durability capability, external storage rejection, and user path rejection.
35. Storage namespace/path policy is approved: stable ASCII namespaces, deterministic encoded vault/record segments, no raw vault ids as path text, no user-controlled paths, no path traversal, no absolute paths, no URI or Windows-drive prefixes, and no secrets or labels in path names.
36. Logical storage layout plan is implemented and tested: future container, manifest, storage-index, record, temp, quarantine, and recovery artifact locations are represented as deterministic rootless relative segment lists using validated namespace/path policy output, not platform paths.
37. Platform storage-root contract is approved: Android app-private internal storage, desktop app-controlled user-data roots, no user-selected arbitrary roots, no external/shared Android storage, no OS keyring as primary vault storage, reviewed backup/uninstall/permission/durability behavior, and no root resolution in this branch.
38. Safe path-construction contract is approved: future paths combine only a reviewed root and validated/encoded relative segments, reject absolute/dot/traversal/unsafe/secret-looking/user-label segments, prove containment under the reviewed root, and fail closed if containment cannot be proven.
39. Symlink/traversal contract is approved: future implementation avoids or rejects symlink traversal where possible, verifies resolved targets under the root where supported, fails closed on unknown symlink or containment state, and does not follow attacker-controlled links.
40. Storage permission/ownership contract is approved: future implementation prefers app-private OS isolation, rejects obviously unsafe permissions where detectable, documents Android and Linux expectations, and does not store vault data in world-readable or shared directories.
41. Durability capability contract is approved: future implementation documents and tests atomic replace, durable sync, parent-directory sync or equivalents, write ordering, unsupported-primitive fallback, and Android/desktop differences before persistence.
42. Durability fail-closed policy is approved: unknown, unsupported, unreviewed, insufficient, unsafe, or failed durability/root/permission/storage state blocks encrypted vault persistence unless a future explicitly reviewed equivalent safe strategy is approved.
43. Warning-only encrypted vault persistence is rejected: user consent cannot override required durability failure for v1 vault writes.
44. Runtime randomness uses OS SecureRandom with provider/algorithm evidence.
45. Unknown randomness/provider state blocks vault creation.
46. Forbidden random APIs remain guarded.
47. Secure secret storage is reviewed and approved.
48. Secure metadata storage is reviewed and approved.
49. Crash, corruption, and partial-write behavior are reviewed.
50. Redaction, logging, and crash-report leakage checks pass.
51. Android optional wrapping remains separate from entropy/randomness and passphrase recovery.
52. The still-disabled provider facade is approved as metadata/status only and remains non-selectable.
53. A production provider implementation exists behind Skald-owned interfaces.
54. Release readiness excludes debug and test-only providers from selection.

Passing dependency-level KATs, test-provider KATs, runtime randomness availability probes, vector-matched canonical/HKDF/HMAC building blocks, strict AAD/record-AEAD building-block tests, still-disabled integrated provider harness KATs, still-disabled provider facade checks, documented stale-record manifest policy, or a design-only acceptance assessment must not bypass the remaining gates.

## Source And Storage Boundaries

This contract does not allow:

- Bouncy Castle imports in commonMain production provider or selection code,
- Tink imports in commonMain production provider or selection code,
- provider-selectable KDF execution or calibration,
- Argon2id execution outside the approved still-disabled building-block files and approved tests/probes,
- HKDF execution outside the approved still-disabled building-block files,
- HMAC execution outside the approved still-disabled building-block files,
- provider-selectable AEAD execution,
- production random-byte generation,
- key generation,
- Tink keyset creation or storage,
- Tink key rotation,
- multiple active Tink AEAD keys,
- internal or unsupported Tink APIs,
- raw key persistence,
- file-backed vault container read/write,
- file/settings/SharedPreferences storage in the provider boundary,
- manifest file read/write,
- storage index read/write,
- platform root resolver,
- actual path construction,
- absolute path construction,
- path join or containment implementation,
- symlink check implementation,
- permission check implementation,
- durability probe implementation,
- warning-only encrypted vault persistence path,
- user-consent durability override path,
- temp-file, journal, rename, fsync, or recovery implementation,
- platform interruption-test runtime hooks,
- secure secret storage success,
- secure metadata storage success,
- production sync,
- backend clients,
- mainnet.

Source guards must continue proving that production provider/selection/readiness/contract code stays policy-only, and that Argon2id/HKDF/HMAC/header-commitment and Tink record-AEAD execution is confined to the approved building-block files and vector/fixture tests.

## Relationship To Provider Selection

`ProductionProviderAcceptanceContract` is evidence modeling only. The current assessment records satisfied design gates and missing implementation gates, but it always reports:

```text
productionProviderSelectable = false
productionPersistenceAllowed = false
```

`VaultCryptoProviderSelectionRegistry` still selects only the disabled provider. The contract is a prerequisite boundary, not an enablement path.

## Next Step

The next focused step should remain implementation-safe only if it is additional still-disabled design/model/source-guard work. Provider selectability, calibrated production KDF execution, file-backed vault container read/write, platform root resolution, actual path construction, persistence, biometric wrapping, and unlock UI must remain out of scope until the acceptance gates above are reviewed and intentionally moved into an implementation branch.
