# Encrypted Local Vault KAT Validation

## Status

This document records the official non-secret known-answer-vector validation added for the pinned Tink plus Bouncy Castle vault crypto dependency probe.

This is crypto dependency validation only. It does not implement encryption, derive production keys, create a vault container, persist secrets, persist sensitive metadata, enable production sync, create wallets, derive production addresses, sign, broadcast, add Tor transport, add public endpoints, add Skald-operated infrastructure, or enable mainnet.

Runtime behavior remains fail-closed:

- `SecureSecretStorage` is disabled.
- `SecureWalletMetadataRepository` is disabled.
- `EncryptedVaultReadinessPolicy` reports the vault as not implemented/not ready.
- Production sync remains disabled.
- Production observation, address-index, UTXO, label, note, and wallet-history persistence remain disabled.

## Validated APIs

The desktop test `VaultCryptoKatValidationTest` validates the pinned probe stack with public cryptographic test vectors:

- Bouncy Castle `org.bouncycastle.crypto.generators.Argon2BytesGenerator` against the RFC 9106 Argon2id test vector.
- Tink `com.google.crypto.tink.aead.internal.InsecureNonceXChaCha20Poly1305` against the XChaCha draft AEAD_XChaCha20_Poly1305 test vector.

The Tink explicit-nonce class is used only because official AEAD KATs require a fixed nonce. It is not an approved production vault API and is not wired into secure storage, secure metadata persistence, sync, UI, settings, wallet code, or repositories. A future production implementation must wrap any primitive use behind a narrow Skald-owned provider boundary before vault storage is considered.

## Vector Sources

Argon2id:

- Source: RFC 9106, section 5.3, Argon2id test vectors: <https://www.ietf.org/rfc/rfc9106.html#section-5.3>.
- Parameters: version 19, memory 32 KiB, 3 passes, 4 lanes, 32-byte output.
- Public vector material: byte-pattern password, salt, secret, and associated data from the RFC.
- Result: Bouncy Castle `1.84` matches the expected public tag on desktop JVM.

XChaCha20-Poly1305:

- Source: `draft-irtf-cfrg-xchacha-02`, appendix A.1, AEAD_XCHACHA20_POLY1305 test vector: <https://datatracker.ietf.org/doc/html/draft-irtf-cfrg-xchacha-02#appendix-A.1>.
- Parameters: 32-byte key, 24-byte nonce, associated data, plaintext, ciphertext, and tag from the draft.
- Result: Tink `1.21.0` matches the expected public ciphertext-plus-tag on desktop JVM and decrypts it back to the public plaintext.

These vectors are public cryptographic test vectors only. They are not wallet data and do not contain mnemonic material, seed bytes, private descriptors, keys, credentials, addresses, txids, labels, notes, or production metadata.

## Android Runtime Status

Android remains compile/package verified only for this probe. The Android platform source set references the same pinned candidate API classes so Android compilation fails if those classes disappear, but this pass does not add Android instrumentation or device runtime tests.

Android runtime validation remains outstanding because adding a new instrumentation framework would broaden the pass beyond dependency/KAT validation. A future focused branch should decide whether to add Android host/device KAT execution or compare a different crypto stack first.

## Source Confinement

Crypto imports remain confined to:

- Android compile probe: `AndroidVaultCryptoDependencyCompileProbe.kt`.
- Desktop compile probe: `DesktopVaultCryptoDependencyCompileProbe.kt`.
- Desktop KAT test: `VaultCryptoKnownAnswerVectorTest.kt`.

Common vault readiness models, secure storage, secure metadata repositories, sync facade, UI, settings codecs, wallet/domain policy, and production repositories remain BDK-free and crypto-implementation-free.

## Current Decision

The Tink plus Bouncy Castle split stack moves from packaging/API-presence-only to a desktop KAT-validated implementation candidate.

It is not approved for production vault implementation yet. Remaining blockers:

- Android runtime KAT validation.
- Final dependency and license review.
- KDF calibration on Android and Linux desktop.
- Narrow Skald-owned `VaultCryptoProvider`-style boundary design.
- Container/envelope parser and writer design.
- Lock/session lifecycle tests.
- Redaction tests.
- Migration and corruption tests.
- Explicit approval before any encrypted storage implementation.

## Non-Capabilities

This KAT validation does not enable:

- encrypted vault storage,
- fake encryption,
- vault container parsing or writing,
- production key derivation,
- production AEAD record encryption,
- key generation,
- passphrase or unlock UI,
- secure secret storage,
- secure metadata persistence,
- production sync,
- production observation persistence,
- production address index persistence,
- UTXO persistence,
- wallet activation,
- signing,
- broadcasting,
- Tor transport,
- Nostr, Lightning, Cashu, or Payjoin behavior,
- public backend defaults,
- Skald-operated infrastructure,
- mainnet.

## Next Step

The next focused branch should either add Android runtime KAT validation for the same split stack, compare a libsodium/KMP candidate with Android and Linux packaging, or design the narrow disabled `VaultCryptoProvider` boundary before any vault container work.
