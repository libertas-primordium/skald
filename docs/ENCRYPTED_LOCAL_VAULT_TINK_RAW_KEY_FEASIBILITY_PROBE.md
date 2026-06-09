# Encrypted Local Vault Tink Raw-Key Feasibility Probe

## Status

This document records the Skald Vault v1 Tink raw-key feasibility probes.

This began as test/probe evidence only and now feeds a still-disabled record AEAD building block. It does not implement a production provider, provider-selectable AEAD execution, production key derivation, production randomness, key generation, vault container read/write, Tink keyset persistence, secure secret storage success, secure metadata storage success, sync, wallet behavior, signing, broadcasting, Tor, Nostr, backend clients, public endpoints, Skald-operated infrastructure, or mainnet.

Runtime behavior remains fail-closed:

- `VaultCryptoProviderSelectionRegistry` selects only `DisabledVaultCryptoProvider`.
- `ProductionProviderAcceptanceAssessment.productionProviderSelectable` remains `false`.
- Production persistence remains disabled.
- Secure secret storage and secure metadata storage remain disabled.
- Header commitment, passphrase validation, bounded Argon2id approval, provider implementation, production provider KATs, vault container/storage, and redaction/migration tests remain required.
- The header commitment, canonical header encoding, key-separation label, and strict AAD contract remains a separate gate documented in [`ENCRYPTED_LOCAL_VAULT_HEADER_COMMITMENT_AAD_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_HEADER_COMMITMENT_AAD_CONTRACT.md).
- The selected HKDF-SHA-256 key-expansion and HMAC-SHA-256 header-commitment primitive policy remains a separate gate documented in [`ENCRYPTED_LOCAL_VAULT_KEY_EXPANSION_COMMITMENT_POLICY.md`](ENCRYPTED_LOCAL_VAULT_KEY_EXPANSION_COMMITMENT_POLICY.md).
- Deterministic non-secret canonical header/HKDF/HMAC vectors are a separate test-vector contract documented in [`ENCRYPTED_LOCAL_VAULT_CANONICAL_HEADER_HKDF_HMAC_VECTORS.md`](ENCRYPTED_LOCAL_VAULT_CANONICAL_HEADER_HKDF_HMAC_VECTORS.md).

## Results

The desktop/JVM probe outcome is:

```text
FEASIBLE_PUBLIC_RAW_KEY_API
```

Meaning:

- public Tink APIs available to the pinned `com.google.crypto.tink:tink:1.21.0` desktop/JVM artifact can construct the pinned XChaCha20-Poly1305 AEAD primitive from caller-supplied raw key bytes;
- no internal Tink API is used;
- no reflection is used;
- no persisted Tink keyset is required;
- no random Tink-generated vault key is required;
- no key rotation or multiple active AEAD keys are required.

The Android instrumented probe outcome is:

```text
ANDROID_FEASIBLE_PUBLIC_RAW_KEY_API
```

Meaning:

- public Tink APIs available to the pinned `com.google.crypto.tink:tink-android:1.21.0` Android artifact can construct the pinned XChaCha20-Poly1305 AEAD primitive from caller-supplied raw key bytes;
- the Android path matches the desktop/JVM public API path;
- no internal Tink API is used;
- no reflection is used;
- no persisted Tink keyset is required;
- no random Tink-generated vault key is required;
- no key rotation or multiple active AEAD keys are required.

Together these results satisfy only the cross-platform raw-key feasibility question. The future v1 policy expects the 32-byte record AEAD key to come from HKDF-SHA-256 expansion of the 64-byte Argon2id root material, but this probe does not implement that production derivation. It does not approve the production provider, does not approve vault persistence, does not prove key commitment, and does not solve the non-key-committing AEAD risk.

## Tested API Paths

Desktop test source:

```text
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/VaultCryptoTinkRawKeyFeasibilityProbeTest.kt
```

Android instrumented test source:

```text
composeApp/src/androidInstrumentedTest/kotlin/com/libertasprimordium/skald/VaultCryptoAndroidTinkRawKeyFeasibilityProbeTest.kt
```

Both probes use fixed non-secret test bytes only. The tested public API path is the same on desktop/JVM and Android:

```text
AeadConfig.register()
SecretBytes.copyFrom(fixedNonSecretKeyBytes, InsecureSecretKeyAccess.get())
XChaCha20Poly1305Key.create(XChaCha20Poly1305Parameters.Variant.NO_PREFIX, secretBytes, null)
KeysetHandle.importKey(key).withFixedId(fixedNonSecretKeyId).setStatus(KeyStatus.ENABLED).makePrimary()
KeysetHandle.newBuilder().addEntry(importedEntry).build()
keysetHandle.getPrimitive(RegistryConfiguration.get(), Aead::class.java)
```

Each probe then performs one round trip with fixed non-secret plaintext and fixed non-secret associated data. The Android probe also confirms wrong associated data fails. The public `Aead` API does not require a caller-supplied nonce; therefore the probes do not provide one. Resulting test ciphertext is not logged, persisted, written to files, treated as vault material, or used outside assertions.

## Keyset Handling

The public Tink primitive lookup path requires a transient in-memory `KeysetHandle` wrapping the imported caller-supplied key. This is not persisted and is not a storage model.

The probes do not use:

- `KeysetHandle.generateNew(...)`,
- `KeysetHandle.generateEntryFromParameters(...)`,
- `withRandomId()`,
- `SecretBytes.randomBytes(...)`,
- `CleartextKeysetHandle`,
- keyset readers or writers,
- keyset JSON or binary serialization,
- `KeysetManager`,
- Tink key rotation,
- multiple active keys.

The fixed test key id is non-secret metadata used only to satisfy the public builder requirement. It is not a vault key id, record id, wallet id, address, txid, label, note, credential, or persisted value.

## Internal API And Reflection Boundary

The raw-key feasibility probes do not import:

- `com.google.crypto.tink.aead.internal.*`,
- `com.google.crypto.tink.internal.*`,
- `com.google.crypto.tink.subtle.*`.

They do not use reflection, `Class.forName`, declared-member access, or unsupported Tink internals.

The existing dependency-level XChaCha public-vector KATs still use Tink's explicit-nonce internal probe API because official KAT vectors require fixed nonces. That KAT-only internal API remains separate from this raw-key feasibility probe and is not approved for production provider code.

## What This Proves

The probes prove that, in test scope on the current desktop/JVM and Android targets, pinned Tink `1.21.0` exposes a public API path to construct an XChaCha20-Poly1305 `Aead` primitive from caller-supplied fixed raw key bytes without persisted keysets or random Tink key generation.

They also prove the Skald acceptance model can represent platform-specific desktop/JVM and Android results while keeping provider selectability disabled.

## What This Does Not Prove

The probes do not prove:

- production AEAD security,
- production provider correctness,
- production KDF correctness,
- entropy quality,
- nonce uniqueness over a vault lifetime,
- vault-level key commitment,
- header authentication,
- strict record AAD binding,
- passphrase encoding correctness,
- lock/session memory lifecycle,
- crash/corruption behavior,
- secure storage readiness,
- metadata storage readiness,
- migration readiness,
- redaction readiness,
- mainnet readiness.

Tink XChaCha20-Poly1305 remains non-key-committing. Skald must still implement and test vault-level header commitment over canonical header fields, separated header commitment key material, strict record AAD, and production implementation behavior matching the non-secret canonical header/HKDF/HMAC vectors before any record decrypt path can become selectable. Raw-key feasibility does not prove key commitment and cannot be used as a wrong-passphrase oracle.

The still-disabled record AEAD building block now uses the same public API family with caller-supplied 32-byte record AEAD key material and deterministic strict AAD bytes. It keeps a transient in-memory Tink keyset handle, does not persist keysets, does not generate a random Tink vault key, does not use internal APIs or reflection, and does not make the provider selectable.

## Source Guard Expectations

Source guards must continue to prove:

- production source imports no Tink APIs,
- production source does not use Tink keyset serialization or persistence,
- production source does not call Tink key generation APIs,
- production source does not use Tink internal or subtle packages,
- production source does not execute AEAD encrypt/decrypt,
- the desktop raw-key probe remains confined to desktop test/probe scope,
- the Android raw-key probe remains confined to Android instrumented test/probe scope.

## Next Step

The next implementation-safe step is still a disabled production-provider skeleton or additional design/source-guard review. Production provider implementation must remain blocked until header commitment, passphrase validation, bounded Argon2id approval, provider-level KATs, storage, redaction, corruption, migration, lock/session, and release-hardening gates are reviewed.
