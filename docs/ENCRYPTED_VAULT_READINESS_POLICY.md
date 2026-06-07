# Encrypted Vault Readiness Policy

## Status

Skald Vault now has small code-level readiness and policy models for the future app-controlled encrypted local vault:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/EncryptedVaultReadiness.kt
```

These models are disabled and fail-closed. They encode the vault design and crypto decision record as typed policy state only. They do not implement encryption, generate keys, derive keys, encrypt data, decrypt data, write files, use platform key stores, use OS keyrings, persist secrets, persist sensitive metadata, enable production sync, sign, broadcast, add Tor transport, or enable mainnet.

The dependency spike documented in [`ENCRYPTED_LOCAL_VAULT_DEPENDENCY_SPIKE.md`](ENCRYPTED_LOCAL_VAULT_DEPENDENCY_SPIKE.md) adds pinned platform-scoped Tink and Bouncy Castle compile probes. Those probes do not change the readiness result: the vault remains not implemented/not ready, secure secret storage remains unavailable, and secure metadata storage remains disabled.

## What The Models Represent

The readiness models capture:

- implementation status: not implemented,
- target KDF: Argon2id,
- reviewed KDF fallback: scrypt only,
- rejected default KDF: PBKDF2,
- preferred record AEAD: XChaCha20-Poly1305 pending dependency review,
- reviewed AEAD fallback candidates: ChaCha20-Poly1305, AES-256-GCM, and AES-GCM-SIV,
- nonce policy: random 24-byte per-record nonce for XChaCha20-Poly1305 records,
- associated data policy: bind only non-secret envelope context,
- key hierarchy requirements: separate metadata, secret payload, and backup/export keys,
- Android platform policy: app-controlled vault primary, optional Keystore wrapping later,
- Linux platform policy: app-controlled passphrase-first vault primary, no libsecret/KWallet primary storage,
- readiness gates: dependency selection beyond the packaging-probe candidate, KDF calibration, AEAD verification, known-answer vectors, container format, lock/session tests, redaction tests, migration/corruption tests, secure secret storage, secure metadata storage, production persistence approval, and mainnet release approval.

All current gates are unresolved, absent, or disabled by policy. The readiness decision returns blockers for vault implementation unavailable, crypto dependencies not selected for implementation beyond the packaging probe, KDF parameters uncalibrated, AEAD dependency unverified, known-answer vectors missing, vault container format absent, lock/session lifecycle untested, redaction tests missing, migration/corruption tests missing, secure secret storage disabled, secure metadata storage disabled, production persistence disabled, and mainnet disabled.

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
- sync preflight includes the encrypted-vault-unavailable blocker,
- crypto dependencies pinned and confined to platform dependency probes,
- no crypto/storage/client/process APIs imported by vault readiness source.

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

The next focused pass should complete package verification and dependency review for the pinned Tink/Bouncy Castle probe, add official non-secret known-answer-vector tests if approved, or compare a libsodium/KMP native-packaging option. Do not add production persistence, production sync, backend clients, signing, broadcasting, Tor transport, Nostr parsing, public endpoints, or mainnet as part of that work.
