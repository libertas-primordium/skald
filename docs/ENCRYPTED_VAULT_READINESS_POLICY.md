# Encrypted Vault Readiness Policy

## Status

Skald Vault now has small code-level readiness and policy models for the future app-controlled encrypted local vault:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/EncryptedVaultReadiness.kt
```

These models are disabled and fail-closed. They encode the vault design and crypto decision record as typed policy state only. They do not implement encryption, generate keys, derive keys, encrypt data, decrypt data, write files, use platform key stores, use OS keyrings, persist secrets, persist sensitive metadata, enable production sync, sign, broadcast, add Tor transport, or enable mainnet.

The dependency spike documented in [`ENCRYPTED_LOCAL_VAULT_DEPENDENCY_SPIKE.md`](ENCRYPTED_LOCAL_VAULT_DEPENDENCY_SPIKE.md) adds pinned platform-scoped Tink and Bouncy Castle compile probes. Desktop and Android runtime known-answer-vector validation is documented in [`ENCRYPTED_LOCAL_VAULT_KAT_VALIDATION.md`](ENCRYPTED_LOCAL_VAULT_KAT_VALIDATION.md). The libsodium/Kotlin packaging comparison is documented in [`ENCRYPTED_LOCAL_VAULT_LIBSODIUM_COMPARISON.md`](ENCRYPTED_LOCAL_VAULT_LIBSODIUM_COMPARISON.md). The Tink/Bouncy dependency, license, keyset/storage, Bouncy Castle Argon2id API, and split-provider review is documented in [`ENCRYPTED_LOCAL_VAULT_DEPENDENCY_REVIEW.md`](ENCRYPTED_LOCAL_VAULT_DEPENDENCY_REVIEW.md). The disabled provider boundary is documented in [`ENCRYPTED_LOCAL_VAULT_CRYPTO_PROVIDER_BOUNDARY.md`](ENCRYPTED_LOCAL_VAULT_CRYPTO_PROVIDER_BOUNDARY.md). Argon2id calibration policy/probes are documented in [`ENCRYPTED_LOCAL_VAULT_ARGON2ID_CALIBRATION.md`](ENCRYPTED_LOCAL_VAULT_ARGON2ID_CALIBRATION.md). Those probes, reviews, and disabled models do not change the readiness result: the vault remains not implemented/not ready, secure secret storage remains unavailable, secure metadata storage remains disabled, and final KDF parameters remain uncalibrated.

## What The Models Represent

The readiness models capture:

- implementation status: not implemented,
- target KDF: Argon2id,
- reviewed KDF fallback: scrypt only,
- rejected default KDF: PBKDF2,
- preferred record AEAD: XChaCha20-Poly1305 for the current candidate-reviewed dependency stack,
- reviewed AEAD fallback candidates: ChaCha20-Poly1305, AES-256-GCM, and AES-GCM-SIV,
- nonce policy: random 24-byte per-record nonce for XChaCha20-Poly1305 records,
- associated data policy: bind only non-secret envelope context,
- key hierarchy requirements: separate metadata, secret payload, and backup/export keys,
- Android platform policy: app-controlled vault primary, optional Keystore wrapping later,
- Linux platform policy: app-controlled passphrase-first vault primary, no libsecret/KWallet primary storage,
- readiness gates: candidate-reviewed dependency selection, disabled provider boundary modeled, Argon2id calibration policy modeled, provider-boundary KAT validation, final KDF calibration, AEAD verification, executable provider implementation, container format, lock/session tests, redaction tests, migration/corruption tests, secure secret storage, secure metadata storage, production persistence approval, and mainnet release approval.

All production gates are unresolved, absent, candidate-reviewed only, or disabled by policy. Desktop public KATs pass for the pinned probe stack, Android instrumented runtime KATs passed on Pixel 10 Pro XL / Android 16 after raw ADB IP:port targeting, the disabled provider boundary exists as policy-only common code, and Argon2id calibration policy/probes exist as planning evidence only. The readiness decision still returns blockers for vault implementation unavailable, production provider implementation unavailable, crypto dependencies not selected for implementation beyond the candidate-reviewed probe, KDF parameters uncalibrated, AEAD dependency unverified, provider-boundary known-answer vectors incomplete, vault container format absent, lock/session lifecycle untested, redaction tests missing, migration/corruption tests missing, secure secret storage disabled, secure metadata storage disabled, production persistence disabled, and mainnet disabled.

## Sync Boundary Integration

The disabled production sync facade now accepts the encrypted vault readiness state as a Skald-owned preflight input. The default is `commonDisabledEncryptedVaultReadiness()`.

Current sync preflight adds:

- `EncryptedVaultUnavailable` blocker,
- `EncryptedVaultUnavailable` warning,
- `EncryptedVaultReadinessOnly` warning,
- existing secure-storage, secure-metadata, observation-persistence, and address-index persistence blockers.

This is status-only integration. It does not unlock a vault, initialize storage, connect to a backend, derive addresses, scan UTXOs, persist observations, sign, broadcast, or enable mainnet.

## Secure Metadata Classification

Tor routing policy and transport metadata are now represented as sensitive wallet metadata through `SensitiveMetadataKind.TorRoutingMetadata`.

This keeps the policy aligned with the vault design: future integrated Tor settings, external Tor/Orbot/local daemon settings, onion-only routing policy, all-wallet-traffic-through-Tor policy, Tor-required fail-closed decisions, and transport failure history must not be persisted in non-secret settings once production networking exists.

## Tests And Source Guards

Tests cover:

- disabled/not implemented vault readiness,
- production persistence blocked,
- Argon2id and XChaCha20-Poly1305 as design targets only,
- PBKDF2 rejected as the production default,
- every implementation gate unsatisfied,
- OS keyrings not primary storage,
- Linux passphrase-first policy,
- Android wrapping optional and not primary storage,
- Tor routing metadata classified as sensitive metadata,
- Argon2id calibration policy with explicit units, probe-only candidates, and production KDF disabled,
- disabled provider-boundary policy with no KDF/AEAD/key generation/keyset/storage success path,
- desktop public KAT validation and Pixel 10 Pro XL / Android 16 runtime KAT validation for the pinned Tink/Bouncy Castle probe,
- Android instrumented KAT test source, test APK assembly, and Pixel 10 Pro XL / Android 16 runtime execution for the pinned Tink/Bouncy Castle probe,
- candidate-level dependency inventory, POM license declaration, package inventory, Tink keyset/storage, Bouncy Castle Argon2id API risk, and split-provider review status,
- Lazysodium Java/Android rejected for this branch after Android duplicate-JNA packaging failure,
- IonSpin KMP libsodium deferred after metadata/POM inspection,
- sync preflight includes the encrypted-vault-unavailable blocker,
- crypto dependencies pinned and confined to platform dependency probes,
- no crypto/storage/client/process APIs imported by vault readiness or provider-boundary source.

## Explicit Non-Capabilities

This readiness policy does not enable:

- encrypted vault implementation,
- KDF implementation,
- AEAD implementation,
- fake encryption,
- production secret persistence,
- production sensitive metadata persistence,
- production observation persistence,
- production address index persistence,
- production backend clients,
- production BDK sync,
- wallet activation,
- production receive UI,
- signing,
- broadcasting,
- Nostr, Lightning, Cashu, or Payjoin behavior,
- Tor transport,
- public endpoint defaults,
- Skald-operated infrastructure,
- mainnet.

## Next Step

The next focused pass should review Argon2id calibration probe evidence and then design still-disabled executable-provider/KAT scaffolding, or evaluate an alternate KDF provider if the probe evidence is insufficient. IonSpin KMP libsodium packaging/KAT mapping and Lazysodium/JNA variant-resolution work remain separate replacement-stack probes if needed. Do not add production persistence, production sync, backend clients, signing, broadcasting, Tor transport, Nostr parsing, public endpoints, or mainnet as part of that work.
