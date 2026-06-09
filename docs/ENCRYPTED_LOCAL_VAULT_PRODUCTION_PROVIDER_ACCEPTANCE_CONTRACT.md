# Encrypted Local Vault Production Provider Acceptance Contract

## Status

This document defines the Skald Vault v1 production-provider acceptance contract.

It is design and acceptance-contract material only. It does not implement a production provider, production Argon2id execution, production Tink AEAD execution, production random-byte generation, key generation, vault container read/write, Android Keystore or StrongBox wrapping, biometric unlock, secure secret storage success, secure metadata storage success, production sync, backend clients, signing, broadcasting, Tor transport, Nostr parsing, public endpoints, Skald-operated infrastructure, or mainnet.

Runtime behavior remains fail-closed:

- `VaultCryptoProviderSelectionRegistry` selects only `DisabledVaultCryptoProvider`.
- Tink plus Bouncy Castle is a blocked future candidate, not a production-selectable provider.
- `SecureSecretStorage` remains disabled.
- `SecureWalletMetadataRepository` remains disabled.
- Vault creation and production persistence remain disabled.
- Mainnet remains disabled.

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
- password/passphrase encoding policy must be specified before production use,
- provider-level KATs must run through the future production provider, not only through dependency probes or test-only providers,
- Android and desktop performance can vary by CPU, RAM pressure, scheduler, build type, and thermal state.

Before Bouncy Castle Argon2id can be selectable, Skald must approve explicit parameter floors, bounded calibration behavior, fail-closed memory behavior, provider/version pinning, password encoding rules, and provider-level KAT coverage.

## Argon2id Parameter And Unlock Policy

The v1 minimum review floor is:

```text
Argon2id version 19, 64 MiB memory, t=3, p=1
```

Skald targets roughly a 1 second unlock where feasible, but an unlock/decrypting duration up to roughly 2 seconds is acceptable. Two seconds is not a failure condition, and parameters must not be weakened merely to force sub-1-second unlocks.

Existing vault parameters are authoritative:

- stored vault parameters must never be silently downgraded,
- a weaker device that cannot satisfy stored vault parameters must fail closed,
- the failure must have a clear user-facing message,
- parameter upgrades require explicit vault-format migration review.

This contract does not make current candidate parameters production final. It records the future approval rule.

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

Before production selectability, the vault format must implement and test:

- vault-level key commitment,
- header authentication before record decrypt,
- strict binding between the provider suite id and vault header,
- rejection of mismatched suite metadata before record decrypt,
- fail-closed behavior for wrong passphrase, wrong key, wrong suite, and tampered header cases.

The future key-commitment design must be a vault-format requirement. It is not implemented by this branch.

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

Vault secrets, salts, nonces, keys, unlock material, and vault records must not use language-level or ad hoc randomness.

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
3. Argon2id KATs pass through the production provider.
4. Argon2id calibration bounds and memory-failure behavior are approved.
5. XChaCha20-Poly1305 primitive/template/version is pinned.
6. AEAD KATs pass through the production provider.
7. Vault-level key commitment and header authentication are implemented and tested.
8. AEAD AAD policy binds vault header/version, provider suite id, record type, record id, record version/counter, and integrity-critical metadata.
9. Tamper tests cover header, ciphertext, nonce, tag, AAD, record metadata, and provider-suite metadata.
10. Runtime randomness uses OS SecureRandom with provider/algorithm evidence.
11. Unknown randomness/provider state blocks vault creation.
12. Forbidden random APIs remain guarded.
13. Secure secret storage is reviewed and approved.
14. Secure metadata storage is reviewed and approved.
15. Crash, corruption, and partial-write behavior are reviewed.
16. Redaction, logging, and crash-report leakage checks pass.
17. Android optional wrapping remains separate from entropy/randomness and passphrase recovery.
18. A production provider implementation exists behind Skald-owned interfaces.
19. Release readiness excludes debug and test-only providers from selection.

Passing dependency-level KATs, test-provider KATs, runtime randomness availability probes, or a design-only acceptance assessment must not bypass these gates.

## Source And Storage Boundaries

This contract does not allow:

- Bouncy Castle imports in commonMain production provider or selection code,
- Tink imports in commonMain production provider or selection code,
- production KDF execution,
- production AEAD execution,
- production random-byte generation,
- key generation,
- Tink keyset creation or storage,
- raw key persistence,
- vault container read/write,
- file/settings/SharedPreferences storage in the provider boundary,
- secure secret storage success,
- secure metadata storage success,
- production sync,
- backend clients,
- mainnet.

Source guards must continue proving that production provider/selection/readiness/contract code stays policy-only.

## Relationship To Provider Selection

`ProductionProviderAcceptanceContract` is evidence modeling only. The current assessment records satisfied design gates and missing implementation gates, but it always reports:

```text
productionProviderSelectable = false
productionPersistenceAllowed = false
```

`VaultCryptoProviderSelectionRegistry` still selects only the disabled provider. The contract is a prerequisite boundary, not an enablement path.

## Next Step

The next focused step should remain implementation-safe only if it is a still-disabled provider skeleton or additional design/source-guard work. Production KDF execution, Tink AEAD execution, key generation, vault container read/write, persistence, biometric wrapping, and unlock UI must remain out of scope until the acceptance gates above are reviewed and intentionally moved into an implementation branch.
