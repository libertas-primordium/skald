# Encrypted Local Vault Argon2id Parameter Policy

## Status

Skald Vault now has a candidate Argon2id parameter policy and manual Android calibration evidence-capture model for the future app-controlled encrypted local vault unlock KDF.

This is design and policy only. It does not implement production KDF execution, executable production `VaultCryptoProvider` behavior, AEAD execution, key generation, vault container read/write, secure secret storage, secure metadata persistence, production sync, backend clients, signing, broadcasting, Tor transport, Nostr parsing, public endpoints, Skald-operated infrastructure, or mainnet.

The manual Android calibration capture protocol is documented in [`ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md). Android compatibility and entropy policy is documented in [`ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md). Runtime randomness/provider checks are documented in [`ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md`](ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md). The v1 production-provider acceptance contract is documented in [`ENCRYPTED_LOCAL_VAULT_PRODUCTION_PROVIDER_ACCEPTANCE_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PRODUCTION_PROVIDER_ACCEPTANCE_CONTRACT.md). The disabled provider-selection boundary is documented in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md). It treats this non-final parameter policy, unknown runtime provider/randomness checks, incomplete production-provider acceptance, disabled storage, and missing production provider approval as production-selection blockers.

Runtime behavior remains fail-closed:

- `DisabledVaultCryptoProvider` rejects every KDF, AEAD, key generation, keyset storage, KAT validation, and persistence operation.
- `SecureSecretStorage` is disabled.
- `SecureWalletMetadataRepository` is disabled.
- `EncryptedVaultReadinessPolicy` still reports `KdfParametersUncalibrated`.
- `VaultCryptoProviderSelectionRegistry` selects only the disabled provider.
- Production persistence remains disabled.
- Production sync remains disabled.
- Mainnet remains disabled.

## Evidence Reviewed

The policy uses the probe evidence documented in [`ENCRYPTED_LOCAL_VAULT_ARGON2ID_CALIBRATION.md`](ENCRYPTED_LOCAL_VAULT_ARGON2ID_CALIBRATION.md).

| Platform evidence | Candidate ID | Parameters | Coarse elapsed time | Interpretation |
| --- | --- | --- | ---: | --- |
| Linux desktop JVM | `argon2id-probe-16mib-2p-1lane` | 16 MiB, 2 passes, 1 lane, 32-byte output, Argon2 version 19 | 42 ms | fallback/probe floor only |
| Linux desktop JVM | `argon2id-probe-32mib-3p-1lane` | 32 MiB, 3 passes, 1 lane, 32-byte output, Argon2 version 19 | 68 ms | intermediate probe evidence |
| Linux desktop JVM | `argon2id-probe-64mib-3p-1lane` | 64 MiB, 3 passes, 1 lane, 32-byte output, Argon2 version 19 | 160 ms | desktop candidate evidence |
| Pixel 10 Pro XL / Android 16 | `argon2id-probe-16mib-2p-1lane` | 16 MiB, 2 passes, 1 lane, 32-byte output, Argon2 version 19 | 321 ms | fallback/probe floor only |
| Pixel 10 Pro XL / Android 16 | `argon2id-probe-32mib-3p-1lane` | 32 MiB, 3 passes, 1 lane, 32-byte output, Argon2 version 19 | 707 ms | high-end Android candidate evidence |

The Android result covers a high-end device class only. The evidence-capture model records it as debug/instrumented high-end evidence, not release-like evidence. It must not be treated as all-device performance proof, release-like evidence, thermal-throttled behavior, memory-pressure behavior, or production Android default approval.

## Candidate Tiers

No tier is production-final.

| Tier | Candidate | Role | Production status |
| --- | --- | --- | --- |
| Desktop candidate | 64 MiB, 3 passes, 1 lane, 32-byte output, Argon2 version 19 | Candidate desktop starting point based on current Linux desktop JVM probe evidence. | Not final; not enabled. |
| High-end Android candidate | 32 MiB, 3 passes, 1 lane, 32-byte output, Argon2 version 19 | Candidate high-end Android starting point based on Pixel 10 Pro XL / Android 16 evidence. | Not universal Android policy; not final; not enabled. |
| Mobile fallback/probe floor | 16 MiB, 2 passes, 1 lane, 32-byte output, Argon2 version 19 | Minimum testing/fallback floor only. It is useful for bounded probes and potential degraded-path analysis. | Not preferred production default; not final; not enabled. |
| Android supported-compatibility planning | none | Represents the supported Android OS baseline, runtime provider/primitive/randomness checks, and fail-closed vault-creation policy. | Not a production parameter; no mandatory low-end/mid-range model gate. |

The policy treats memory hardness as a security requirement. Unlock latency is a usability constraint that can reject unusable settings, but it must not silently downgrade memory cost without explicit degraded-strength review.

The v1 production-provider acceptance contract records a shared minimum review floor of Argon2id version 19, 64 MiB memory, 3 passes, 1 lane, at least a 16-byte salt, 32-byte preferred salt for new vault creation, and 64-byte derived root material. The preferred unlock target is roughly 1 second, but roughly 2 seconds is acceptable and is not a failure condition. Parameters must not be weakened merely to force sub-1-second unlocks. Existing vault parameters are authoritative and must never be silently downgraded; a weaker device that cannot satisfy stored parameters must fail closed with a clear user-facing message.

This means the older 32 MiB high-end Android probe remains useful timing evidence only. It no longer represents a sufficient v1 production floor. Android and desktop share the same 64 MiB / t=3 / p=1 review floor, while desktop may select stronger parameters after bounded calibration review.

The policy is not sufficient for production provider selection because no tier is final, production KDF execution is absent, runtime provider/randomness checks are availability evidence only until reviewed with a production provider, storage is disabled, and no production provider exists.

The manual evidence model can compare optional future low-end, mid-range, and high-end Android runs using the same field set. It rejects ambiguous memory units, zero or negative elapsed timings, missing repeated-run summaries, and secret-like or personal-device fields. It cannot mark production KDF execution approved.

## Rejection Rules

The current code-level policy rejects:

- zero or negative memory cost,
- ambiguous memory units such as `32M` or `64 K`,
- zero pass count,
- zero lane count,
- output shorter than 32 bytes,
- PBKDF2 as the default production vault KDF,
- any production KDF execution claim in this branch.

Memory units must be explicit: KiB or MiB. Argon2id version 19 is the only target version modeled.

scrypt remains a reviewed compatibility fallback only. It is not selected for the current vault KDF policy.

## Final Approval Blockers

Before any Argon2id parameter can become a production vault unlock policy, Skald needs:

1. Supported Android compatibility review, including minimum supported OS policy.
2. Runtime crypto-provider and primitive checks on supported Android and Linux paths.
3. Runtime cryptographic-randomness path checks that accept only OS or reviewed provider randomness and do not treat tiny non-secret samples as entropy-quality proof.
4. Vault-creation fail-closed warning review.
5. Bounded per-platform calibration review at the shared 64 MiB / t=3 / p=1 floor.
6. Memory-allocation failure behavior proving vault creation fails closed if the floor cannot allocate or complete.
7. Existing-vault unlock failure behavior proving stored parameters are authoritative and never silently downgraded.
8. Lock-screen/unlock UX measurement.
9. Background/foreground behavior checks.
10. Accessibility and timeout policy review.
11. Memory-pressure failure behavior review.
12. Production provider-boundary known-answer vectors. The current test-only provider KAT harness is interface evidence only.
13. Production KDF implementation review behind the Skald-owned provider boundary, including the provider-level KAT contract in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md).
14. Provider-selection gate review according to [`ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md).
15. Secure storage and secure metadata storage review.
16. Mainnet release-hardening review before any mainnet relevance.

These blockers are represented in common policy models. They keep `KdfParametersCalibrated` unresolved and keep production KDF execution disabled.

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

`Argon2idCalibrationPolicy` now models a non-final candidate parameter policy:

- desktop candidate: `argon2id-probe-64mib-3p-1lane`,
- high-end Android candidate: `argon2id-probe-32mib-3p-1lane`,
- mobile fallback/probe floor: `argon2id-probe-16mib-2p-1lane`,
- Android supported-compatibility planning: modeled separately from production parameter approval.

`EncryptedVaultReadinessPolicy` records the candidate parameter policy, Android calibration evidence-capture model, Android compatibility/entropy policy, and runtime randomness/provider check model as reviewed-only policy capabilities. `KdfParametersCalibrated` remains unresolved, `KdfParametersUncalibrated` remains a blocker, and production persistence remains disabled.

The production-provider acceptance contract adds a separate bounded-calibration gate for the shared v1 floor, 64-byte derived root material, fail-closed minimum-floor allocation behavior, stored-parameter authority, and no silent downgrade. That gate is modeled but not satisfied.

`VaultCryptoDependencyProbeCatalog` records the Tink plus Bouncy Castle split stack as having candidate Argon2id parameter policy modeled. That stack remains candidate-only and is not production-approved.

`VaultCryptoProviderSelectionRegistry` records the candidate policy as evidence only. It keeps Tink plus Bouncy Castle blocked because parameters are not final, runtime compatibility/randomness checks are separate gates, storage is disabled, and no production provider exists.

## Explicit Non-Capabilities

This parameter-policy branch does not enable:

- production KDF execution,
- production `VaultCryptoProvider` execution,
- AEAD execution beyond existing dependency KAT tests,
- encrypted vault implementation,
- fake encryption,
- key generation,
- Tink keyset creation or storage,
- raw key material persistence,
- vault container read/write,
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

## Next Step

The next focused branch should remain design/probe-only: review runtime provider/primitive/randomness availability evidence for supported Android and Linux paths, or capture optional additional Android calibration evidence for parameter/UX review. Do not proceed to vault container read/write or persistence from this policy pass.
