# Encrypted Local Vault Key Expansion And Commitment Policy

## Status

This document records the finalized Skald Vault v1 key-expansion and header-commitment primitive decisions. The deterministic non-secret canonical header, HKDF, and HMAC vector contract is documented separately in [`ENCRYPTED_LOCAL_VAULT_CANONICAL_HEADER_HKDF_HMAC_VECTORS.md`](ENCRYPTED_LOCAL_VAULT_CANONICAL_HEADER_HKDF_HMAC_VECTORS.md).

The canonical header serializer, HKDF-SHA-256 key-expansion building block, HMAC-SHA-256 header-commitment computation/verification building block, strict AAD serializer, Tink record AEAD building block, in-memory container parser/writer, in-memory manifest parser/writer, and local manifest-relative stale-record decision policy are now implemented in production source and vector/behavior-tested against non-secret fixtures. They remain still-disabled building blocks. This branch does not implement calibrated production Argon2id execution, provider-wired passphrase-to-root-material derivation, provider-selectable Tink AEAD execution, random-byte generation, key generation, vault creation, vault unlock, file-backed vault container read/write, manifest file/storage read/write, Tink keyset persistence, secure secret storage success, secure metadata storage success, sync, wallet behavior, signing, broadcasting, Tor, Nostr, backend clients, public endpoints, Skald-operated infrastructure, or mainnet.

The provider operation dispatch isolation boundary now records that no non-disabled provider operation dispatcher can reach HKDF expansion, HMAC header commitment, strict AAD, AEAD, header commitment computation/verification, record encryption/decryption, key wrapping, randomness, KDF execution, factory/registry/skeleton/candidate paths, vault creation, unlock, persistence, or mainnet. It is model-only evidence and does not add dependencies, implement a provider, add a provider factory, add a provider dispatcher, instantiate provider code, make any provider selectable, or set `productionProviderSelectable=true`.

The provider KAT execution isolation boundary now records that public vector documentation, canonical header/HKDF/HMAC vector documentation, provider KAT contract evidence, and test-provider KAT harness concepts cannot authorize runtime HKDF, HMAC, AEAD, header commitment, record crypto, key wrapping, provider selection, or `productionProviderSelectable=true`. It is model-only evidence and does not add dependencies, implement a provider, add a provider factory, add a provider dispatcher, add a provider KAT executor, instantiate provider code, run provider operations or KATs, run randomness checks, run KDF/HKDF/HMAC/AEAD, compute header commitments, encrypt/decrypt records, wrap/unwrap keys, make any provider selectable, or enable vault creation/unlock/persistence/mainnet.

Runtime behavior remains fail-closed:

- `VaultCryptoProviderSelectionRegistry` selects only `DisabledVaultCryptoProvider`.
- `ProductionProviderAcceptanceAssessment.productionProviderSelectable` remains `false`.
- Production persistence remains disabled.
- Secure secret storage and secure metadata storage remain disabled.

## Selected V1 Policies

The selected v1 key-expansion primitive is:

```text
HKDF-SHA-256
```

The key-expansion policy id is:

```text
skald-vault-v1-hkdf-sha256-key-expansion-v1
```

The selected v1 header-commitment primitive is:

```text
HMAC-SHA-256 over canonical vault header bytes
```

The header-commitment primitive policy id is:

```text
skald-vault-v1-hmac-sha256-header-commitment-v1
```

The v1 output layout is:

| Output | Length | Status |
| --- | ---: | --- |
| Argon2id root material | 64 bytes | Selected policy; production KDF not implemented |
| Header commitment key | 32 bytes | HKDF output; still-disabled production-source HKDF building block implemented and vector-tested |
| Record AEAD key | 32 bytes | HKDF output feeding the probed Tink XChaCha20-Poly1305 raw-key path; still-disabled HKDF and record AEAD building blocks implemented, provider-selectable AEAD not implemented |

Reserved future wrapping, export, and migration outputs are not implemented in this branch.

The non-secret vector contract now fixes the test-scope output examples for this layout:

- 64-byte fixed Argon2id root-material fixture,
- 32-byte HKDF-SHA-256 header commitment key output,
- 32-byte HKDF-SHA-256 record AEAD key output,
- HMAC-SHA-256 tag over the canonical header vector bytes.

Those vectors are not production key material and do not implement Argon2id, passphrase handling, AEAD, vault unlock, or persistence.

## Verification Order

The future production unlock path must follow this order:

1. Parse the vault header.
2. Validate header syntax and supported versions/policies.
3. Canonicalize or reconstruct canonical header bytes.
4. Derive 64 bytes of Argon2id root material from the passphrase using the header KDF parameters.
5. Expand subkeys using HKDF-SHA-256 and stable labels.
6. Verify HMAC-SHA-256 header commitment over canonical header bytes.
7. Only then construct or use the record AEAD and decrypt records.

This branch implements steps 3, 5, and 6 only as isolated building blocks. It does not implement the full unlock sequence.

## HKDF-SHA-256 Rationale

Argon2id remains the expensive password KDF and offline-bruteforce defense. HKDF-SHA-256 is selected only as the lightweight subkey-expansion and key-separation step after Argon2id root material already exists.

HKDF-SHA-256 is a standard, conservative key-separation primitive. It avoids manual slicing of root material and gives Skald stable, labeled expansion points for header commitment and record AEAD keys. The labels must remain stable, versioned, ASCII, and domain-separated:

```text
skald-vault/v1/root-domain
skald-vault/v1/header-commitment-key
skald-vault/v1/record-aead-key
skald-vault/v1/reserved/wrapping-metadata
skald-vault/v1/reserved/export-migration
skald-vault/test-only/raw-key-probe
```

HKDF-SHA-256 must not be used directly on a passphrase and must not be described as replacing Argon2id. It should not weaken encryption if Argon2id root material is strong, labels are stable and domain-separated, and implementation tests cover cross-platform output. It does not compensate for weak passphrases, implementation mistakes, live endpoint compromise, or supply-chain compromise.

## HMAC-SHA-256 Rationale

HMAC-SHA-256 is selected as keyed authentication over the canonical vault header. It uses the derived 32-byte header commitment key. Its input is the canonical vault header bytes defined by the header commitment/AAD contract.

The commitment proves possession of the passphrase-derived header commitment key for this exact header. It binds the suite id, KDF parameters, salt, vault id, passphrase policy, key-separation policy, AAD policy, and record-format policy before any record decrypt.

This is Skald Vault's vault-format mitigation for Tink XChaCha20-Poly1305 being non-key-committing. Successful record AEAD decrypt alone must not be used as proof that the passphrase-derived vault key is the intended vault key. Wrong-passphrase behavior must be decided by header commitment verification before arbitrary record decrypt attempts.

HMAC-SHA-256 does not replace record AEAD authentication. Record AEAD authentication does not replace header commitment.

## Threat Model

For an offline vault-file attack, the proposed construction is intended to be strong against direct offline cryptanalytic attack when implemented correctly. The main offline attack becomes passphrase guessing. Resistance depends on passphrase entropy and Argon2id parameters. HKDF-SHA-256 and HMAC-SHA-256 are not expected to be the weak link when correctly wired.

No absolute guarantee is made against very capable attackers. The construction is appropriate for resisting direct offline cryptanalysis by capable attackers only when implemented correctly and paired with high-entropy passphrases.

This local vault design does not guarantee protection if the endpoint is compromised while the vault is unlocked or while the passphrase is entered. Non-covered threats include:

- malware on the device while unlocked,
- keylogger or passphrase capture,
- compromised OS, kernel, or firmware,
- malicious keyboard or input method,
- memory extraction while keys are live,
- dependency or supply-chain compromise,
- weak or reused passphrases,
- implementation bugs,
- coercion or physical compromise while unlocked.

The policy must not be described as nation-state proof. HKDF/HMAC do not compensate for weak passphrases or compromised endpoints.

## Acceptance-Contract Effect

The selected primitive decisions are represented in:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/ProductionProviderAcceptanceContract.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/EncryptedVaultReadiness.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/VaultCryptoDependencyProbe.kt
```

Missing, unknown, failed, unsupported, documented/model-only, or unimplemented key-expansion primitive evidence blocks production provider selectability. The same is true for header-commitment primitive evidence and output-layout evidence.

The current branch records deterministic non-secret vectors and matches them from still-disabled production-source building blocks. Vector-matching HKDF/HMAC/header-commitment evidence, fixed passphrase/Argon2id fixture evidence, strict AAD/record-AEAD behavior tests, in-memory container/manifest parser tests, local stale-record decision tests, provider operation dispatch isolation evidence, and provider KAT execution isolation evidence still do not make the provider selectable because production provider implementation, provider-wired Argon2id passphrase derivation and calibration, provider-wired AEAD execution, selectable provider-level KAT execution, vault storage integration, manifest storage-backed stale-record enforcement, and persistence review remain absent. Raw-key feasibility, header/AAD contract evidence, dependency-level KATs, test-provider KATs, vector tests, dispatch-isolation evidence, and KAT-isolation evidence do not bypass these gates.

## Remaining Work

Before a still-disabled provider implementation can proceed, reviewers still need:

- provider-level KAT execution through the future production provider,
- integration of the canonical serializer, HKDF expansion, and HMAC verification into a still-disabled provider/format boundary,
- provider-wired Argon2id passphrase-to-root-material derivation or calibration,
- provider integration for the strict AAD serializer and Tink record AEAD building block,
- record version/counter and stale-record manifest/index policy,
- final bounded Argon2id calibration approval,
- lock/session lifecycle tests,
- redaction and failure-mode tests,
- crash/corruption/partial-write review,
- secure secret storage and secure metadata storage review,
- vault container/storage review.

The next step must remain a still-disabled implementation-skeleton or test-vector design branch. It must not be vault persistence.
