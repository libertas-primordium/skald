# Encrypted Local Vault Argon2id Parameter Policy

## Status

Skald Vault now has a still-disabled Argon2id parameter/calibration policy building block, deterministic candidate-selection model, memory/execution failure model, stored-parameter no-downgrade model, manual Android calibration evidence-capture model, and a still-disabled explicit-parameter Bouncy Castle Argon2id passphrase-to-root-material building block for the future app-controlled encrypted local vault unlock KDF.

This is design, policy, and isolated building-block implementation only. It does not implement executable selectable production `VaultCryptoProvider` behavior, provider-selectable KDF execution, vault creation, unlock UI, AEAD execution, key generation, file-backed vault container read/write, secure secret storage, secure metadata persistence, production sync, backend clients, signing, broadcasting, Tor transport, Nostr parsing, public endpoints, Skald-operated infrastructure, or mainnet.

The manual Android calibration capture protocol is documented in [`ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md). Android compatibility and entropy policy is documented in [`ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md). Runtime randomness/provider checks are documented in [`ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md`](ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md). The v1 production-provider acceptance contract is documented in [`ENCRYPTED_LOCAL_VAULT_PRODUCTION_PROVIDER_ACCEPTANCE_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PRODUCTION_PROVIDER_ACCEPTANCE_CONTRACT.md). The disabled provider-selection boundary is documented in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md). It treats this non-final parameter policy, unknown runtime provider/randomness checks, incomplete production-provider acceptance, disabled storage, and missing production provider approval as production-selection blockers.

Runtime behavior remains fail-closed:

- `DisabledVaultCryptoProvider` rejects every KDF, AEAD, key generation, keyset storage, KAT validation, and persistence operation.
- `SecureSecretStorage` is disabled.
- `SecureWalletMetadataRepository` is disabled.
- `EncryptedVaultReadinessPolicy` reports the passphrase/KDF/calibration policy building blocks as implemented but still disabled, and still reports `KdfParametersUncalibrated`.
- `VaultCryptoProviderSelectionRegistry` selects only the disabled provider.
- Production persistence remains disabled.
- Production sync remains disabled.
- Mainnet remains disabled.

## Evidence Reviewed

The policy uses the historical probe evidence documented in [`ENCRYPTED_LOCAL_VAULT_ARGON2ID_CALIBRATION.md`](ENCRYPTED_LOCAL_VAULT_ARGON2ID_CALIBRATION.md) and the current still-disabled v1 floor/candidate-selection model.

| Platform evidence | Candidate ID | Parameters | Coarse elapsed time | Interpretation |
| --- | --- | --- | ---: | --- |
| Linux desktop JVM | `argon2id-probe-16mib-2p-1lane` | 16 MiB, 2 passes, 1 lane, 32-byte output, Argon2 version 19 | 42 ms | historical below-floor evidence only |
| Linux desktop JVM | `argon2id-probe-32mib-3p-1lane` | 32 MiB, 3 passes, 1 lane, 32-byte output, Argon2 version 19 | 68 ms | historical below-floor evidence only |
| Linux desktop JVM | `argon2id-probe-64mib-3p-1lane` | 64 MiB, 3 passes, 1 lane, 32-byte output, Argon2 version 19 | 160 ms | historical timing evidence; current v1 output length is 64 bytes |
| Pixel 10 Pro XL / Android 16 | `argon2id-probe-16mib-2p-1lane` | 16 MiB, 2 passes, 1 lane, 32-byte output, Argon2 version 19 | 321 ms | historical below-floor evidence only |
| Pixel 10 Pro XL / Android 16 | `argon2id-probe-32mib-3p-1lane` | 32 MiB, 3 passes, 1 lane, 32-byte output, Argon2 version 19 | 707 ms | historical high-end timing evidence below the v1 floor |

The Android result covers a high-end device class only. The evidence-capture model records it as debug/instrumented high-end evidence, not release-like evidence. It must not be treated as all-device performance proof, release-like evidence, thermal-throttled behavior, memory-pressure behavior, or production Android default approval.

## Candidate Tiers

No tier is production-final.

| Tier | Candidate | Role | Production status |
| --- | --- | --- | --- |
| Shared v1 floor | 64 MiB, 3 passes, 1 lane, 64-byte output, Argon2 version 19 | Minimum v1 floor for Android and desktop creation/stored-parameter validation. | Implemented as still-disabled policy; final production approval still blocked. |
| Desktop stronger candidate | 96 MiB, 3 passes, 1 lane, 64-byte output, Argon2 version 19 | Bounded desktop candidate-selection evidence above the shared floor. | Not final; not enabled. |
| Historical 16/32 MiB probes | 16 MiB or 32 MiB, 32-byte output | Timing history only. | Below v1 floor; not active v1 fallback; not enabled. |
| Android supported-compatibility planning | none | Represents the supported Android OS baseline, runtime provider/primitive/randomness checks, and fail-closed vault-creation policy. | Not a production parameter; no mandatory low-end/mid-range model gate. |

The policy treats memory hardness as a security requirement. Unlock latency is a usability constraint that can reject unusable settings, but it must not silently downgrade memory cost. The still-disabled candidate-selection model treats about 1 second as preferred, about 2 seconds as acceptable and not failure, and never selects below the shared floor merely to force sub-1-second unlock.

The v1 production-provider acceptance contract records a shared minimum review floor of Argon2id version 19, 64 MiB memory, 3 passes, 1 lane, at least a 16-byte salt, 32-byte preferred salt for new vault creation, and 64-byte derived root material. The still-disabled policy enforces those values for candidate/stored-parameter validation. The preferred unlock target is roughly 1 second, but roughly 2 seconds is acceptable and is not a failure condition. Parameters must not be weakened merely to force sub-1-second unlocks. Existing vault parameters are authoritative and must never be silently downgraded; a weaker device that cannot satisfy stored parameters must fail closed with a clear user-facing message.

This means the older 32 MiB high-end Android probe remains useful timing evidence only. It no longer represents a sufficient v1 production floor. Android and desktop share the same 64 MiB / t=3 / p=1 review floor, while desktop may select stronger parameters after bounded calibration review.

The policy is not sufficient for production provider selection because final production calibration approval is still absent, the Argon2id building block is not wired into a selectable provider or vault creation path, runtime provider/randomness checks are availability evidence only until reviewed with a production provider, storage is disabled, and no selectable production provider exists.

The manual evidence model can compare optional future low-end, mid-range, and high-end Android runs using the same field set. It rejects ambiguous memory units, zero or negative elapsed timings, missing repeated-run summaries, and secret-like or personal-device fields. It cannot mark provider-wired KDF execution or production calibration approved.

## Fixed Non-Secret Root-Derivation Fixture

The explicit-parameter Argon2id root-derivation building block is tested with a Skald-owned deterministic fixture. This is not an external standards KAT and is not wallet material.

| Field | Fixture value |
| --- | --- |
| Passphrase text | `Skald-Vault.Test_Fixture-01` |
| Passphrase policy | `unicode-nfc-utf8-no-controls-no-whitespace-v1` |
| Normalization/encoding | NFC then UTF-8 |
| Salt hex | `000102030405060708090a0b0c0d0e0f101112131415161718191a1b1c1d1e1f` |
| KDF | Bouncy Castle Argon2id |
| Argon2 version | 19 / 1.3 |
| Memory | 64 MiB / 65,536 KiB |
| Iterations | 3 |
| Parallelism | 1 |
| Root output length | 64 bytes |
| Expected root hex | `36686ff5939587fce8eafdc430767fa36427ecc80b7eca0ac050fc3813fe754a982187d6315ae1e779d6479f486e9a3ec99c059371477497464302dc9167cff7` |

The fixture proves deterministic execution for this code path only. It does not prove calibration, memory-pressure behavior, side-channel resistance, wrong-passphrase UX, provider-level KAT coverage, vault unlock, or persistence.

## Rejection Rules

The current code-level policy rejects:

- zero or negative memory cost,
- ambiguous memory units such as `32M` or `64 K`,
- zero pass count,
- zero lane count,
- output shorter than 32 bytes,
- v1 memory below 64 MiB,
- v1 iterations below t=3,
- v1 parallelism other than p=1,
- salt shorter than 16 bytes,
- v1 root output length other than 64 bytes,
- unsupported Argon2 type/version,
- failed floor execution,
- unsupported stored parameters on the current device,
- silent downgrade attempts,
- PBKDF2 as the default production vault KDF,
- any provider-selectable KDF execution or calibration claim in this branch.

Memory units must be explicit: KiB or MiB. Argon2id version 19 is the only target version modeled.

scrypt remains a reviewed compatibility fallback only. It is not selected for the current vault KDF policy.

## Final Approval Blockers

Before any Argon2id parameter can become a production vault unlock policy, Skald needs:

1. Supported Android compatibility review, including minimum supported OS policy.
2. Runtime crypto-provider and primitive checks on supported Android and Linux paths.
3. Runtime cryptographic-randomness path checks that accept only OS or reviewed provider randomness and do not treat tiny non-secret samples as entropy-quality proof.
4. Vault-creation fail-closed warning review.
5. Final production calibration approval after bounded per-platform review at the shared 64 MiB / t=3 / p=1 floor.
6. Memory-allocation failure behavior proving vault creation fails closed if the floor cannot allocate or complete. The still-disabled policy model now represents this as fail-closed evidence; no vault creation path exists.
7. Existing-vault unlock failure behavior proving stored parameters are authoritative and never silently downgraded. The still-disabled policy model now represents this as fail-closed evidence; no unlock path exists.
8. Lock-screen/unlock UX measurement.
9. Background/foreground behavior checks.
10. Accessibility and timeout policy review.
11. Memory-pressure failure behavior review.
12. Production provider-boundary known-answer vectors. The current test-only provider KAT harness is interface evidence only.
13. Provider-wired KDF implementation review behind the Skald-owned provider boundary, including the provider-level KAT contract in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md). The current explicit-parameter root-derivation building block is not enough.
14. Provider-selection gate review according to [`ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md).
15. Secure storage and secure metadata storage review.
16. Mainnet release-hardening review before any mainnet relevance.

These blockers are represented in common policy models. They keep `KdfParametersCalibrated` unresolved and keep provider-selectable KDF execution disabled.

## Future Calibration Requirements

Future calibration must:

- use public non-secret fixtures only,
- avoid real passphrases, seed material, private keys, descriptors, labels, notes, addresses, txids, credentials, or wallet metadata,
- follow the manual Android capture protocol for optional device-class evidence, Android version/API, generic model/manufacturer, build profile, thermal state, foreground/background state, battery/charging state, memory pressure, run count, and repeated timing summaries,
- measure both Android debug and release-like runtime behavior where feasible,
- include device-class caps and failure behavior,
- preserve clear user-visible unlock-time tradeoffs,
- document that JVM/Android memory clearing remains best effort,
- avoid benchmark overclaims because CPU, RAM, scheduler, power mode, thermal state, and app backgrounding affect results.

Probe timings do not prove side-channel resistance, memory zeroization, wrong-passphrase handling, lock/session lifecycle correctness, or production vault usability.

## Readiness Alignment

`Argon2idCalibrationPolicy` now models a still-disabled v1 floor/candidate-selection policy:

- shared floor: `argon2id-v1-floor-64mib-t3-p1-root64`,
- desktop stronger candidate: `argon2id-v1-desktop-stronger-96mib-t3-p1-root64`,
- historical 16/32 MiB probes: timing history below the active v1 floor,
- Android supported-compatibility planning: modeled separately from production parameter approval.

`EncryptedVaultReadinessPolicy` records the candidate parameter policy, still-disabled calibration/candidate-selection/memory-failure/no-downgrade policy building block, Android calibration evidence-capture model, Android compatibility/entropy policy, runtime randomness/provider check model, passphrase policy validation building block, and explicit-parameter Argon2id root-derivation building block. `KdfParametersCalibrated` remains unresolved, `KdfParametersUncalibrated` remains a blocker, provider selection remains disabled, and production persistence remains disabled.

The production-provider acceptance contract records implemented/tested still-disabled evidence for bounded-calibration policy and memory-failure handling, including the shared v1 floor, 64-byte derived root material, fail-closed minimum-floor allocation behavior, stored-parameter authority, and no silent downgrade. Final production calibration approval is still blocked.

`VaultCryptoDependencyProbeCatalog` records the Tink plus Bouncy Castle split stack as having candidate Argon2id parameter policy modeled plus fixed non-secret passphrase/root-derivation fixture evidence. That stack remains candidate-only and is not production-approved.

`VaultCryptoProviderSelectionRegistry` records the candidate policy as evidence only. It keeps Tink plus Bouncy Castle blocked because parameters are not final, runtime compatibility/randomness checks are separate gates, storage is disabled, and no production provider exists.

## Explicit Non-Capabilities

This parameter-policy branch does not enable:

- provider-selectable KDF execution or unlock flow,
- production `VaultCryptoProvider` execution,
- AEAD execution beyond existing dependency KAT tests,
- encrypted vault implementation,
- fake encryption,
- key generation,
- Tink keyset creation or storage,
- raw key material persistence,
- file-backed vault container read/write,
- passphrase, PIN, biometric, or unlock UI,
- secure secret storage success,
- secure metadata persistence success,
- production observation/address-index/UTXO/label/note/wallet-history persistence,
- production sync,
- production backend clients,
- BDK production persistence,
- Nostr parsing,
- Lightning, Cashu, or Payjoin behavior,
- Tor transport,
- public backend defaults,
- Skald-operated infrastructure,
- mainnet.

## KDF Calibration Authorization Boundary

`SkaldVaultV1KdfCalibrationAuthorizationPolicy` now records the separate authorization gate that must be satisfied before any future Argon2id parameters can be treated as final production parameters or used for production KDF execution. It models future KDF operation kinds, purposes, parameter/evidence kinds, platform/device classes, required gates, blockers, warnings, disabled capabilities, and redacted policy tokens, but it authorizes no operation in this branch.

The boundary does not run Argon2id, run KDFs, run calibration, run benchmarks, inspect real host/device details, approve final KDF parameters, normalize or encode real passphrases, generate or consume salts, call randomness APIs, run provider operations, run provider KATs, derive vault keys, enable unlock, enable persistence, make a provider selectable, or approve mainnet. Android and Linux calibration remain future-reviewed only, test-vector profiles do not authorize production runtime unlock, and mainnet KDF use remains blocked until release review.

## Vault Unlock Authorization Boundary

`SkaldVaultV1UnlockAuthorizationPolicy` now records the separate authorization gate that must be satisfied before any future Argon2id parameter set can be used in a vault unlock attempt. It models future unlock operation kinds, purposes, credential classes, required gates, blockers, warnings, disabled capabilities, and redacted policy tokens, but it authorizes no operation in this branch.

Current unlock authorization is blocked/fail-closed because passphrase input is blocked, KDF calibration authorization is blocked, runtime randomness authorization is blocked, provider operations are unauthorized, secure-storage authorization is blocked, storage service operations are disabled, lock/session lifecycle is unavailable, persistence readiness is blocked, provider selection still selects only `DisabledVaultCryptoProvider`, and `productionProviderSelectable` remains false. The boundary does not accept passphrases, PINs, or biometrics; normalize or encode passphrases; run Argon2id/KDF/HKDF/HMAC/AEAD; generate or consume salts, nonces, or random bytes; call provider operations; read secure storage, metadata storage, or encrypted vault storage; retrieve or unwrap wrapped keys; decrypt records; create active sessions; hold decrypted key material; persist unlock state; add UI; enable vault creation, unlock, persistence, provider selectability, or mainnet.

## Next Step

The next focused branch should remain design/probe-only: review runtime provider/primitive/randomness availability evidence for supported Android and Linux paths, or capture optional additional Android calibration evidence for parameter/UX review. Do not proceed to file-backed vault container read/write or persistence from this policy pass.
