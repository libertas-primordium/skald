# Encrypted Local Vault Runtime Randomness Provider Checks

## Status

Skald Vault now has a Skald-owned runtime randomness/provider check model plus test-only Android and Linux/JVM availability probes.

This is design/model/test evidence only. It does not implement production entropy collection, production random-byte generation for vault records, key generation, platform key wrapping, production KDF execution, executable provider crypto, AEAD execution, vault container read/write, secure secret storage, secure metadata persistence, unlock UI, production sync, backend clients, signing, broadcasting, Tor, Nostr parsing, public endpoints, Skald-operated infrastructure, or mainnet.

The v1 production-provider acceptance contract in [`ENCRYPTED_LOCAL_VAULT_PRODUCTION_PROVIDER_ACCEPTANCE_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PRODUCTION_PROVIDER_ACCEPTANCE_CONTRACT.md) uses this runtime randomness policy as a prerequisite gate. It pins the review direction to OS SecureRandom, requires provider/algorithm evidence, rejects provider-wrapped or hybrid randomness for v1 unless separately reviewed, and keeps unknown or unavailable randomness state fail-closed.

Runtime behavior remains fail-closed:

- `VaultCryptoProviderSelectionRegistry` selects only `DisabledVaultCryptoProvider`.
- Tink plus Bouncy Castle remains a blocked future candidate, not a selectable production provider.
- `SecureSecretStorage` remains disabled.
- `SecureWalletMetadataRepository` remains disabled.
- Vault creation is not implemented.
- Production persistence remains disabled.
- Mainnet remains disabled.

## Source Location

Production-safe common policy/check models:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/RuntimeRandomnessProviderChecks.kt
```

Common policy tests:

```text
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/RuntimeRandomnessProviderPolicyTest.kt
```

Test-only runtime availability probes:

```text
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/VaultRuntimeRandomnessProviderProbeTest.kt
composeApp/src/androidInstrumentedTest/kotlin/com/libertasprimordium/skald/VaultCryptoAndroidRuntimeRandomnessProviderProbeTest.kt
```

Source guards:

```text
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/ProductionBackendAdapterSourceGuardTest.kt
```

## What The Checks Prove

The runtime availability probes prove only that a supported test environment can instantiate an approved cryptographic-randomness API path and obtain a small non-secret sample without failure.

They do not prove entropy quality, hardware entropy, production provider correctness, production key generation safety, KDF correctness, AEAD correctness, vault storage readiness, or mainnet readiness.

The common model separates:

- accepted randomness source classes,
- forbidden randomness source classes,
- runtime provider state,
- runtime primitive state,
- sample handling evidence,
- vault-creation fail-closed reasons,
- user-facing warning text.

The model can say that compatibility planning has enough randomness/provider evidence for a future gate. It still cannot allow vault creation because production provider, KDF, storage, container, lifecycle, and persistence gates remain closed.

## Accepted Randomness Source Classes

Vault secrets, salts, nonces, keys, unlock material, and vault records may only use reviewed cryptographic randomness from one of:

- OS cryptographic randomness,
- reviewed crypto-provider randomness.

On Linux desktop, the policy target is kernel/OS CSPRNG-backed randomness through a reviewed provider/library path, such as `getrandom` or `/dev/urandom` as exposed by an approved provider. This branch does not implement a production Linux entropy service and does not call `getrandom` directly.

On Android, `SecureRandom` through Android OS cryptographic randomness is acceptable for compatibility planning. This document does not claim Android random generation is always hardware-backed.

## Forbidden Source Classes

Skald must not use language-level or general-purpose random APIs for vault secrets, salts, nonces, keys, unlock material, or vault records.

Forbidden source classes include:

- Kotlin general-purpose random APIs,
- Java general-purpose random APIs,
- math-library random APIs,
- timestamp-derived values,
- UUID-derived values,
- ad hoc PRNGs,
- any other unreviewed non-cryptographic source.

If approved cryptographic randomness cannot be obtained or verified through the platform/provider path, vault creation must fail closed with a user-facing warning. Skald must not silently fall back to language PRNGs.

The common model names these forbidden categories without importing or executing the forbidden APIs.

## Hardware-Backed Key Protection Is Separate

Hardware-backed key protection is not the entropy source for vault records.

Android Keystore and StrongBox may be reviewed later as optional key wrapping or key-protection mechanisms. StrongBox is preferred if available, but it is not required for basic compatibility in this branch.

Linux hardware-backed wrapping, if ever added, is also optional future key protection. It is not required for basic compatibility and is not implemented.

The runtime randomness model treats hardware-backed key protection alone as a blocker if it is presented as a randomness source.

## Test-Only Probe Behavior

The desktop probe instantiates the JVM `SecureRandom` path in desktop test scope only, records the provider and algorithm names as non-secret metadata, obtains a 16-byte non-secret sample, marks the sample as availability evidence only, and clears the sample buffer. It does not log, persist, or use the bytes as vault material.

The Android instrumented probe does the same with Android `SecureRandom` in instrumented test scope only. It does not claim hardware-backed entropy and does not collect durable device identifiers.

Both probes set:

- sample persisted: false,
- sample logged: false,
- sample used as vault material: false,
- entropy-quality proof: false,
- production entropy collection: false.

Any future probe that logs samples, persists samples, uses samples as vault material, or presents a tiny sample as entropy-quality proof must fail the policy.

## Vault-Creation Gate

`VaultCreationRandomnessGate` maps runtime randomness/provider failures to fail-closed reasons such as:

- runtime crypto provider check missing,
- runtime primitive check missing,
- runtime randomness check missing,
- unknown provider state,
- unknown entropy state,
- forbidden randomness source,
- hardware-backed key protection mistaken for randomness.

The gate currently has `vaultCreationAllowed = false` by construction. This preserves the branch invariant that no vault can be created, even when runtime randomness availability is accepted for compatibility planning.

`UserFacingRandomnessFailureWarning` models the warning a future UI must show when vault creation is blocked because approved cryptographic randomness is unavailable or unverified. No UI is implemented in this branch.

## Source Guards

Source guards require:

- no Tink or Bouncy Castle imports in commonMain production provider, selection, evidence, compatibility, or randomness-check code,
- `SecureRandom` imports confined to approved test/probe files or existing desktop-test BDK validation harnesses,
- no forbidden random APIs in commonMain security/vault code,
- no file, settings, SharedPreferences, keyset, platform wrapping, vault container, production networking, or process client APIs in the randomness-check boundary.

The guards allow test files to mention forbidden API categories as negative scan patterns and policy names. They do not allow production security/vault code to import or call those APIs.

## Relationship To Provider Selection

Runtime randomness availability is one provider-selection gate, not provider approval.

Even if a platform passes the test-only runtime randomness probe, production provider selection remains blocked because:

- the v1 production-provider acceptance contract is not complete for production selectability,
- no executable production provider exists,
- production provider-level KATs have not passed,
- production KDF execution is disabled,
- production AEAD execution is disabled,
- final Argon2id parameters are not approved,
- Tink keyset/raw-key handling is not approved for storage,
- secure secret storage is disabled,
- secure metadata storage is disabled,
- vault container/storage review is missing,
- lock/session, redaction, failure-mode, migration, and corruption tests are missing,
- mainnet is disabled.

## Explicit Non-Capabilities

This boundary does not enable:

- production entropy collection,
- production random-byte generation,
- key generation,
- production KDF execution,
- executable production provider behavior,
- production AEAD execution,
- Tink keyset creation or storage,
- platform key wrapping,
- raw key material persistence,
- vault container parsing or writing,
- secure secret storage success,
- secure metadata storage success,
- passphrase, PIN, biometric, or unlock UI,
- production wallet sync,
- backend clients,
- signing,
- broadcasting,
- Tor transport,
- Nostr parsing,
- public backend defaults,
- Skald-operated infrastructure,
- mainnet.

## Next Step

The next focused pass should remain design/probe-only unless the user explicitly approves implementation scope. Recommended decision point: review the runtime provider, primitive, and randomness gate data together and decide the supported Android/Linux baseline for a future still-disabled production-provider skeleton. Do not add vault containers, production KDF execution, key generation, persistence, or unlock UI in that branch.
