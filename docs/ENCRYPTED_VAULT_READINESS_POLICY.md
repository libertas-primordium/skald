# Encrypted Vault Readiness Policy

## Status

Skald Vault now has small code-level readiness and policy models for the future app-controlled encrypted local vault:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/EncryptedVaultReadiness.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/VaultCryptoProviderSelection.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/AndroidVaultCompatibilityPolicy.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/RuntimeRandomnessProviderChecks.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/ProductionProviderAcceptanceContract.kt
```

These models are disabled and fail-closed. They encode the vault design and crypto decision record as typed policy state. The passphrase policy validator/NFC UTF-8 encoder, explicit-parameter Bouncy Castle Argon2id passphrase-to-root derivation, Argon2id calibration policy/candidate-selection/memory-failure/no-downgrade model, canonical header serializer, HKDF-SHA-256 expansion from caller-supplied root material, HMAC-SHA-256 header commitment verification, strict AAD serialization, Tink XChaCha20-Poly1305 record AEAD construction from caller-supplied 32-byte key material, in-memory container parser/writer, in-memory manifest parser/writer, and local manifest-relative stale-record decision policy now exist as still-disabled building blocks. A still-disabled integrated provider KAT harness now composes those building blocks and executes provider-level deterministic vectors plus randomized AEAD behavioral KATs in the required verification order. A metadata-only still-disabled provider facade now reports suite/policy metadata, building-block evidence, disabled reasons, remaining gates, and typed disabled operation results. Storage, atomicity, crash-recovery, secure-storage boundary, and rollback-limitation contracts are modeled as contract evidence only. The readiness policy still does not implement provider selectability, final production calibration approval, vault creation, vault unlock, file-backed vault parser/writer, manifest file/storage read/write, storage success, key generation, file writes, platform key stores, OS keyrings, secret persistence, sensitive metadata persistence, production sync, signing, broadcasting, Tor transport, or mainnet.

The dependency spike documented in [`ENCRYPTED_LOCAL_VAULT_DEPENDENCY_SPIKE.md`](ENCRYPTED_LOCAL_VAULT_DEPENDENCY_SPIKE.md) adds pinned platform-scoped Tink and Bouncy Castle compile probes. Desktop and Android runtime known-answer-vector validation is documented in [`ENCRYPTED_LOCAL_VAULT_KAT_VALIDATION.md`](ENCRYPTED_LOCAL_VAULT_KAT_VALIDATION.md). The libsodium/Kotlin packaging comparison is documented in [`ENCRYPTED_LOCAL_VAULT_LIBSODIUM_COMPARISON.md`](ENCRYPTED_LOCAL_VAULT_LIBSODIUM_COMPARISON.md). The Tink/Bouncy dependency, license, keyset/storage, Bouncy Castle Argon2id API, and split-provider review is documented in [`ENCRYPTED_LOCAL_VAULT_DEPENDENCY_REVIEW.md`](ENCRYPTED_LOCAL_VAULT_DEPENDENCY_REVIEW.md). The disabled provider boundary is documented in [`ENCRYPTED_LOCAL_VAULT_CRYPTO_PROVIDER_BOUNDARY.md`](ENCRYPTED_LOCAL_VAULT_CRYPTO_PROVIDER_BOUNDARY.md), the disabled provider-selection boundary is documented in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md), the provider-level KAT, still-disabled integrated harness, and stale-record manifest contracts are documented in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md), the vault container/manifest/storage contract is documented in [`ENCRYPTED_LOCAL_VAULT_CONTAINER_MANIFEST_STORAGE_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_CONTAINER_MANIFEST_STORAGE_CONTRACT.md), and the test-only provider KAT harness is documented in [`ENCRYPTED_LOCAL_VAULT_TEST_PROVIDER_KAT_HARNESS.md`](ENCRYPTED_LOCAL_VAULT_TEST_PROVIDER_KAT_HARNESS.md). Argon2id calibration policy/probes are documented in [`ENCRYPTED_LOCAL_VAULT_ARGON2ID_CALIBRATION.md`](ENCRYPTED_LOCAL_VAULT_ARGON2ID_CALIBRATION.md), non-final candidate parameter tiers are documented in [`ENCRYPTED_LOCAL_VAULT_ARGON2ID_PARAMETER_POLICY.md`](ENCRYPTED_LOCAL_VAULT_ARGON2ID_PARAMETER_POLICY.md), manual Android calibration evidence capture is documented in [`ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md), Android compatibility/entropy policy is documented in [`ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md), runtime randomness/provider checks are documented in [`ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md`](ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md), the Tink raw-key feasibility probes are documented in [`ENCRYPTED_LOCAL_VAULT_TINK_RAW_KEY_FEASIBILITY_PROBE.md`](ENCRYPTED_LOCAL_VAULT_TINK_RAW_KEY_FEASIBILITY_PROBE.md), the header commitment/AAD construction contract is documented in [`ENCRYPTED_LOCAL_VAULT_HEADER_COMMITMENT_AAD_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_HEADER_COMMITMENT_AAD_CONTRACT.md), the HKDF-SHA-256 key-expansion and HMAC-SHA-256 header-commitment primitive policy is documented in [`ENCRYPTED_LOCAL_VAULT_KEY_EXPANSION_COMMITMENT_POLICY.md`](ENCRYPTED_LOCAL_VAULT_KEY_EXPANSION_COMMITMENT_POLICY.md), deterministic non-secret canonical header/HKDF/HMAC vectors are documented in [`ENCRYPTED_LOCAL_VAULT_CANONICAL_HEADER_HKDF_HMAC_VECTORS.md`](ENCRYPTED_LOCAL_VAULT_CANONICAL_HEADER_HKDF_HMAC_VECTORS.md), and the v1 production-provider acceptance contract is documented in [`ENCRYPTED_LOCAL_VAULT_PRODUCTION_PROVIDER_ACCEPTANCE_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PRODUCTION_PROVIDER_ACCEPTANCE_CONTRACT.md). Those probes, reviews, test harnesses, evidence models, vector tests, still-disabled building blocks, and disabled models do not change the readiness result: the vault remains not implemented/not ready, provider selection returns only the disabled provider, secure secret storage remains unavailable, secure metadata storage remains disabled, still-disabled provider-level KAT execution does not make a provider selectable, in-memory container/manifest/stale-record evidence does not enable persistence, runtime randomness probes remain availability evidence only, runtime provider/randomness checks remain required before vault creation, raw-key feasibility is desktop/Android test-scope evidence only, canonical header/HKDF/HMAC/AAD vectors now match still-disabled building blocks, manifest storage read/write and storage-backed stale-record enforcement are unimplemented, manifest/storage atomicity and crash recovery remain unimplemented, no anti-rollback anchor exists and full local-directory rollback resistance is not claimed, the acceptance contract remains incomplete for production selectability, and final KDF parameters remain uncalibrated.

## What The Models Represent

The readiness models capture:

- implementation status: not implemented,
- target KDF: Argon2id,
- reviewed KDF fallback: scrypt only,
- rejected default KDF: PBKDF2,
- preferred record AEAD: XChaCha20-Poly1305 for the current candidate-reviewed dependency stack,
- reviewed AEAD fallback candidates: ChaCha20-Poly1305, AES-256-GCM, and AES-GCM-SIV,
- nonce policy: random 24-byte per-record nonce for XChaCha20-Poly1305 records,
- associated data policy: bind only non-secret vault, header-commitment, record identity, record version/counter, and integrity-critical context,
- key hierarchy requirements: 64-byte Argon2id root material, HKDF-SHA-256 key expansion, HMAC-SHA-256 header commitment, versioned key-separation labels, plus separate header commitment, record AEAD, metadata, secret payload, and backup/export key material,
- Android platform policy: app-controlled vault primary, Android OS cryptographic randomness or reviewed provider randomness required for vault random bytes, optional Keystore/StrongBox key protection later,
- Linux platform policy: app-controlled passphrase-first vault primary, no libsecret/KWallet primary storage,
- readiness gates: candidate-reviewed dependency selection, disabled provider boundary modeled, disabled provider-selection boundary modeled, provider-level KAT contract modeled, provider-level KAT strategy contract modeled, randomized AEAD behavioral KAT policy modeled, integrated verification-order KAT policy modeled, still-disabled provider integration harness implemented/tested, still-disabled provider-level KAT execution, still-disabled randomized AEAD behavioral KAT execution, still-disabled integrated verification-order KAT execution, still-disabled provider facade implemented/tested, still-disabled provider facade metadata-only status, still-disabled provider facade disabled operation results, vault container contract modeled, still-disabled in-memory container parser/writer implemented/tested, manifest contract modeled, still-disabled in-memory manifest parser/writer implemented/tested, still-disabled local manifest-relative stale-record decision policy implemented/tested, storage policy contract modeled, stale-record manifest policy modeled, atomicity/crash-recovery contract modeled, secure-storage boundary contract modeled, rollback limitation and anti-rollback anchor status modeled, Argon2id calibration policy/candidate selection/memory-failure/no-downgrade building block implemented, candidate parameter policy modeled, Android calibration evidence capture modeled, Android compatibility/entropy policy modeled, runtime randomness/provider checks modeled, v1 production-provider acceptance contract modeled, vault header commitment policy modeled, HKDF-SHA-256 key-expansion policy modeled, HMAC-SHA-256 header-commitment primitive modeled, key-expansion output layout modeled, primitive threat-model rationale modeled, canonical header/HKDF/HMAC vector contracts modeled, canonical header encoding policy modeled, key-separation label policy modeled, still-disabled passphrase policy validation, explicit-parameter Argon2id root derivation, canonical header serializer, HKDF, and HMAC building blocks, strict AAD contract modeled, Tink non-key-commitment mitigation modeled, passphrase encoding policy modeled, Tink raw-key feasibility policy modeled with `FEASIBLE_PUBLIC_RAW_KEY_API` desktop/JVM test-scope evidence and `ANDROID_FEASIBLE_PUBLIC_RAW_KEY_API` Android test-scope evidence, final KDF calibration approval, AEAD verification, selectable provider implementation, stale-record manifest/storage integration, file-backed container persistence integration, lock/session tests, redaction tests, migration/corruption tests, secure secret storage, secure metadata storage, production persistence approval, and mainnet release approval.

All production gates are unresolved, absent, candidate-reviewed only, implemented-but-still-disabled, or disabled by policy. Desktop public KATs pass for the pinned probe stack, Android instrumented runtime KATs passed on Pixel 10 Pro XL / Android 16 after raw ADB IP:port targeting, the disabled provider boundary, provider-selection boundary, provider-level KAT strategy contract, metadata-only still-disabled provider facade, vault container/manifest/storage contract, stale-record manifest contract, and v1 production-provider acceptance contract exist as common code, a test-only provider KAT harness validates interface expressiveness in test source sets, deterministic non-secret passphrase/Argon2id, calibration floor/candidate-selection/no-downgrade policy evidence, canonical header/HKDF/HMAC, strict AAD/record-AEAD, in-memory container fixtures, in-memory manifest fixtures, and local stale-record decisions match still-disabled building blocks, and the still-disabled integrated provider KAT harness executes those deterministic vectors plus randomized AEAD behavioral KATs in the required order. The readiness decision still returns blockers for vault implementation unavailable, production provider implementation unavailable, provider selection production blocked, production provider acceptance contract incomplete, still-disabled provider integration harness not selectable, still-disabled provider facade not selectable, stale-record manifest/storage integration missing, vault container persistence implementation missing, manifest storage read/write implementation missing, storage success path absent, anti-rollback anchor absent with no full rollback-resistance claim, manifest/storage atomicity review missing, Tink raw-key feasibility probe-only status, final Argon2id calibration approval missing, crypto dependencies not selected for implementation beyond the candidate-reviewed probe, KDF parameters uncalibrated, AEAD dependency unverified, lock/session lifecycle untested, redaction tests missing, migration/corruption tests missing, secure secret storage disabled, secure metadata storage disabled, production persistence disabled, and mainnet disabled.

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
- Argon2id calibration policy with explicit units, shared 64 MiB / t=3 / p=1 / 64-byte floor, deterministic candidate selection, 1-second preferred target, 2-second acceptable target, fail-closed floor execution failure, stored-parameter authority, no silent downgrade, non-final candidate parameter tiers, and production KDF disabled,
- Android Argon2id calibration evidence capture with high-end-only Pixel evidence, optional low/mid/release-like/thermal timing context, explicit MiB units, positive elapsed timing, repeated timing summaries, and secret-like field rejection,
- Android compatibility/entropy policy with supported OS baseline planning, low/mid model testing no longer a hard blocker, OS/reviewed-provider cryptographic randomness allowed, language/general-purpose random sources forbidden, optional hardware-backed key protection modeled separately, and fail-closed user-facing vault-creation warnings,
- runtime randomness/provider policy with accepted OS/reviewed-provider source classes, forbidden language/ad hoc source classes, optional hardware-backed key protection separation, test-only non-secret samples, no entropy-quality proof, and fail-closed user-facing randomness warnings,
- v1 production-provider acceptance contract with pinned Bouncy Castle Argon2id, Tink XChaCha20-Poly1305, OS SecureRandom, passphrase-first protection, optional Android wrapping, one pinned suite id, canonical header commitment prerequisites, deterministic header encoding policy, key-separation label policy, strict AAD contract, `unicode-nfc-utf8-no-controls-no-whitespace-v1` passphrase encoding policy, explicit-parameter Argon2id root derivation fixture evidence, Tink raw-key feasibility results `FEASIBLE_PUBLIC_RAW_KEY_API` and `ANDROID_FEASIBLE_PUBLIC_RAW_KEY_API`, bounded Argon2id calibration gates, non-key-committing AEAD prerequisites, and disabled/non-selectable assessment results,
- disabled provider-boundary policy with no KDF/AEAD/key generation/keyset/storage success path,
- disabled provider-selection policy that selects only the disabled provider and keeps Tink plus Bouncy Castle blocked as a future candidate,
- provider-level KAT strategy categories, deterministic vector requirements, randomized AEAD behavioral KAT policy, verification-order KAT policy, blockers, platform coverage, redaction requirements, dependency-level KAT insufficiency, and test-only provider harness status,
- stale-record and rollback manifest policy with record version/counter AAD binding, implemented still-disabled local manifest-relative stale-record decisions, future manifest/storage tracking requirements, no manifest file/storage read/write implementation, and no global rollback-resistance claim without an anti-rollback anchor,
- vault container contract plus still-disabled in-memory container parser/writer evidence, manifest contract plus still-disabled in-memory manifest parser/writer evidence, and storage, atomicity/crash-recovery, secure-storage boundary, and anti-rollback-anchor contracts as evidence that does not enable provider selectability or persistence,
- in-memory vault container and manifest fixture serialization/parsing with typed malformed-input failures and no file I/O, storage, KDF/HKDF/HMAC, or Tink AEAD execution,
- desktop public KAT validation and Pixel 10 Pro XL / Android 16 runtime KAT validation for the pinned Tink/Bouncy Castle probe,
- Android instrumented KAT test source, test APK assembly, and Pixel 10 Pro XL / Android 16 runtime execution for the pinned Tink/Bouncy Castle probe,
- candidate-level dependency inventory, POM license declaration, package inventory, Tink keyset/storage, Bouncy Castle Argon2id API risk, and split-provider review status,
- Lazysodium Java/Android rejected for this branch after Android duplicate-JNA packaging failure,
- IonSpin KMP libsodium deferred after metadata/POM inspection,
- sync preflight includes the encrypted-vault-unavailable blocker,
- crypto dependencies pinned and confined to platform dependency probes,
- no crypto/storage/client/process APIs imported by vault readiness or provider-boundary source,
- `SecureRandom` imports confined to approved test/probe files or existing desktop-test BDK validation harnesses,
- no forbidden language-level random APIs in commonMain security/vault code,
- no provider-selectable implementation, file-backed vault container parser/reader/writer, manifest file/storage reader/writer, storage index reader/writer, filesystem/database/DataStore/SharedPreferences storage, secure-storage success path, or storage API added by the provider KAT/stale-record/container/manifest contracts.
- no still-disabled provider facade registry reference, storage, manifest, randomness, crypto execution, logging, or persistence path.

## Explicit Non-Capabilities

This readiness policy does not enable:

- encrypted vault implementation,
- still-disabled provider facade selectability,
- provider-selectable Argon2id passphrase KDF implementation or final production calibration approval,
- provider-selectable AEAD implementation,
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

The next focused pass should review runtime provider/primitive/randomness check evidence for supported Android and Linux paths, then decide whether final Argon2id calibration approval can proceed before provider selectability. IonSpin KMP libsodium packaging/KAT mapping and Lazysodium/JNA variant-resolution work remain separate replacement-stack probes if needed. Do not add production entropy collection, key generation, production persistence, production sync, backend clients, signing, broadcasting, Tor transport, Nostr parsing, public endpoints, or mainnet as part of that work.
