# Encrypted Local Vault Tink Raw-Key Feasibility Probe

## Status

This document records the Skald Vault v1 Tink raw-key feasibility probe.

This is test/probe evidence only. It does not implement a production provider, production AEAD execution, production key derivation, production randomness, key generation, vault container read/write, Tink keyset persistence, secure secret storage success, secure metadata storage success, sync, wallet behavior, signing, broadcasting, Tor, Nostr, backend clients, public endpoints, Skald-operated infrastructure, or mainnet.

Runtime behavior remains fail-closed:

- `VaultCryptoProviderSelectionRegistry` selects only `DisabledVaultCryptoProvider`.
- `ProductionProviderAcceptanceAssessment.productionProviderSelectable` remains `false`.
- Production persistence remains disabled.
- Secure secret storage and secure metadata storage remain disabled.
- Header commitment, passphrase validation, bounded Argon2id approval, provider implementation, production provider KATs, vault container/storage, and redaction/migration tests remain required.

## Result

The exact probe outcome is:

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

This result satisfies only the raw-key feasibility question. It does not approve the production provider, does not approve vault persistence, does not prove key commitment, and does not solve the non-key-committing AEAD risk.

## Tested API Path

Desktop test source:

```text
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/VaultCryptoTinkRawKeyFeasibilityProbeTest.kt
```

The probe uses fixed non-secret test bytes only. The tested public API path is:

```text
AeadConfig.register()
SecretBytes.copyFrom(fixedNonSecretKeyBytes, InsecureSecretKeyAccess.get())
XChaCha20Poly1305Key.create(XChaCha20Poly1305Parameters.Variant.NO_PREFIX, secretBytes, null)
KeysetHandle.importKey(key).withFixedId(fixedNonSecretKeyId).setStatus(KeyStatus.ENABLED).makePrimary()
KeysetHandle.newBuilder().addEntry(importedEntry).build()
keysetHandle.getPrimitive(RegistryConfiguration.get(), Aead::class.java)
```

The probe then performs one round trip with fixed non-secret plaintext and fixed non-secret associated data. The public `Aead` API does not require a caller-supplied nonce; therefore the probe does not provide one. The resulting test ciphertext is not logged, persisted, written to files, treated as vault material, or used outside the assertion.

## Keyset Handling

The public Tink primitive lookup path requires a transient in-memory `KeysetHandle` wrapping the imported caller-supplied key. This is not persisted and is not a storage model.

The probe does not use:

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

The raw-key feasibility probe does not import:

- `com.google.crypto.tink.aead.internal.*`,
- `com.google.crypto.tink.internal.*`,
- `com.google.crypto.tink.subtle.*`.

It does not use reflection, `Class.forName`, declared-member access, or unsupported Tink internals.

The existing dependency-level XChaCha public-vector KATs still use Tink's explicit-nonce internal probe API because official KAT vectors require fixed nonces. That KAT-only internal API remains separate from this raw-key feasibility probe and is not approved for production provider code.

## What This Proves

The probe proves that, in test scope on the current desktop/JVM target, pinned Tink `1.21.0` exposes a public API path to construct an XChaCha20-Poly1305 `Aead` primitive from caller-supplied fixed raw key bytes without persisted keysets or random Tink key generation.

It also proves the Skald acceptance model can represent that result while keeping provider selectability disabled.

## What This Does Not Prove

The probe does not prove:

- production AEAD security,
- production provider correctness,
- Android runtime raw-key behavior,
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

Tink XChaCha20-Poly1305 remains non-key-committing. Skald must still implement and test vault-level header commitment over canonical header fields before any record decrypt path can become selectable.

## Source Guard Expectations

Source guards must continue to prove:

- production source imports no Tink APIs,
- production source does not use Tink keyset serialization or persistence,
- production source does not call Tink key generation APIs,
- production source does not use Tink internal or subtle packages,
- production source does not execute AEAD encrypt/decrypt,
- this raw-key probe remains confined to desktop test/probe scope.

## Next Step

The next implementation-safe step is still a disabled production-provider skeleton or additional design/source-guard review. Production provider implementation must remain blocked until header commitment, passphrase validation, bounded Argon2id approval, provider-level KATs, storage, redaction, corruption, migration, lock/session, and release-hardening gates are reviewed.
