# Encrypted Local Vault Android Compatibility And Entropy Policy

## Status

Skald Vault now has a Skald-owned Android compatibility and entropy policy model for future encrypted local vault creation.

This is policy/model/test evidence only. It does not implement entropy collection, random byte generation, platform key wrapping, production KDF execution, executable provider crypto, AEAD execution, key generation, vault container read/write, secure secret storage, secure metadata persistence, unlock UI, production sync, backend clients, signing, broadcasting, Tor, Nostr parsing, public endpoints, Skald-operated infrastructure, or mainnet.

Runtime behavior remains fail-closed:

- `VaultCryptoProviderSelectionRegistry` selects only `DisabledVaultCryptoProvider`.
- Tink plus Bouncy Castle remains a blocked future candidate, not a selectable production provider.
- `SecureSecretStorage` remains disabled.
- `SecureWalletMetadataRepository` remains disabled.
- Vault creation is not implemented.
- Production persistence remains disabled.
- Mainnet remains disabled.

## Source Location

Production-safe common policy models:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/AndroidVaultCompatibilityPolicy.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/RuntimeRandomnessProviderChecks.kt
```

Tests and source guards:

```text
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/AndroidVaultCompatibilityEntropyPolicyTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/RuntimeRandomnessProviderPolicyTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/AndroidArgon2idCalibrationEvidenceTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultCryptoProviderSelectionTest.kt
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/VaultRuntimeRandomnessProviderProbeTest.kt
composeApp/src/androidInstrumentedTest/kotlin/com/libertasprimordium/skald/VaultCryptoAndroidRuntimeRandomnessProviderProbeTest.kt
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/ProductionBackendAdapterSourceGuardTest.kt
```

## Compatibility Decision

The Android compatibility gate is no longer exhaustive low-end, mid-range, and high-end device model testing.

The branch records the user decision:

- low-end Android model testing is not required for compatibility planning,
- mid-range Android model testing is not required for compatibility planning,
- very old Android OS compatibility is not a design goal,
- supported Android baseline plus runtime provider, primitive, and randomness checks is the compatibility planning gate,
- vault creation must fail closed when required platform/provider/randomness/storage gates are missing or unknown.

The current code models the supported baseline from the project Android policy:

```text
minimum API level: 26
very old Android compatibility goal: false
runtime crypto provider check required: true
runtime primitive check required: true
runtime cryptographic randomness check required: true
```

This does not approve production vault creation. It only prevents future branches from treating missing low-end or mid-range model testing as the reason to block compatibility planning.

## Randomness Versus Key Protection

Entropy/random byte generation is separate from hardware-backed key protection.

Vault secrets, salts, nonces, keys, and unlock material require cryptographic randomness from one of:

- OS cryptographic randomness,
- reviewed crypto-provider randomness.

Android compatibility planning may accept Android OS cryptographic randomness such as the reviewed platform/provider path used by `SecureRandom`, but this document does not claim Android randomness is always hardware-backed.

Linux compatibility planning must use kernel/OS CSPRNG-backed randomness through a reviewed provider/library path, such as `getrandom`/`urandom` as exposed by the approved crypto/provider layer. This branch does not implement that path.

Forbidden sources for vault material include:

- `kotlin.random.Random`,
- `java.util.Random`,
- `Math.random`,
- timestamps,
- UUID-derived values,
- ad hoc PRNGs,
- any other language/general-purpose random API or unreviewed pseudo-random source.

If cryptographic randomness cannot be obtained or verified through an approved platform/provider path, vault creation must fail closed with a user-facing warning. Skald must not silently fall back to language PRNGs.

Runtime randomness/provider check modeling and test-only Android/Linux availability probes are documented in [`ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md`](ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md). Those probes generate only small non-secret samples to prove API availability and non-failing behavior. They do not prove entropy quality, do not log or persist samples, do not generate production vault material, and do not enable vault creation.

## Hardware-Backed Key Protection

Hardware-backed key protection is optional future defense-in-depth, not the entropy source for vault records.

Android Keystore and StrongBox may be reviewed later for key wrapping or key-protection roles. StrongBox is preferred if available but is not required for basic vault compatibility in this branch.

Linux hardware-backed wrapping, if ever added, is also optional future key protection. It is not required for basic vault compatibility and is not implemented.

The policy model records:

- hardware-backed key protection preferred: true,
- hardware-backed key protection required: false,
- StrongBox optional/preferred if available: true,
- OS software CSPRNG fallback allowed: true.

## Fail-Closed Vault Creation

`VaultCreationFailClosedReason` models why future vault creation must stop before writing anything.

Current fail-closed reasons include:

- unsupported/outdated Android,
- missing runtime crypto-provider check,
- missing runtime primitive check,
- missing runtime cryptographic-randomness check,
- unknown provider state,
- unknown entropy/randomness state,
- forbidden non-cryptographic randomness source,
- hardware-backed key protection mistaken for randomness,
- missing production provider implementation,
- disabled-only provider selection,
- disabled production KDF execution,
- disabled secure secret storage,
- disabled secure metadata storage,
- missing vault container/storage review.

`UserFacingVaultCreationWarning` models the warning that future UI must show when vault creation is unavailable. No UI is implemented in this branch.

## Relationship To Calibration

Current Android Argon2id timing evidence remains high-end debug/instrumented evidence only. It does not prove performance across all Android devices, it does not approve production KDF parameters, and it does not approve a production provider.

Low-end and mid-range model testing may still be useful optional evidence for UX and parameter tuning. It is no longer a hard blocker for Android compatibility planning.

Final Argon2id parameters remain unapproved because production KDF execution, runtime provider checks, runtime randomness checks, unlock UX, memory-pressure behavior, provider-level KAT execution, storage review, lock/session lifecycle, and redaction/migration/corruption tests are still unresolved.

## Relationship To Provider Selection

The provider-selection registry now treats Android compatibility as a supported-baseline plus runtime-check policy rather than an exhaustive device-class coverage policy.

When Android runtime provider/randomness evidence is unknown, Android provider planning is blocked. When supported-baseline and runtime provider/primitive/randomness checks are modeled as passing, compatibility planning can be satisfied, but production provider selection still remains blocked because:

- no executable production provider exists,
- production provider-level KATs are missing,
- dependency-level KATs are insufficient,
- test-provider KATs are insufficient,
- final KDF parameters are not approved,
- Tink keyset/raw-key handling is unapproved,
- secure secret storage is disabled,
- secure metadata storage is disabled,
- vault container/storage review is missing,
- redaction/failure-mode tests are missing,
- migration/corruption tests are missing,
- mainnet is disabled.

## Explicit Non-Capabilities

This policy does not enable:

- entropy collection,
- random byte generation,
- hardware-backed wrapping,
- production KDF execution,
- executable production provider behavior,
- production AEAD execution,
- key generation,
- Tink keyset creation or storage,
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

The next focused pass should remain design/probe-only unless explicitly narrowed by the user. Recommended next decision point: review runtime provider/primitive/randomness check evidence together and decide whether a still-disabled production-provider skeleton is warranted. Do not implement vault creation, production KDF execution, key generation, or storage in that branch.
