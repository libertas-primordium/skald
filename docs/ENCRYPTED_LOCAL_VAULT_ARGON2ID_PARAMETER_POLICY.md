# Encrypted Local Vault Argon2id Parameter Policy

## Status

Skald Vault now has a candidate Argon2id parameter policy for the future app-controlled encrypted local vault unlock KDF.

This is design and policy only. It does not implement production KDF execution, executable `VaultCryptoProvider` behavior, AEAD execution, key generation, vault container read/write, secure secret storage, secure metadata persistence, production sync, backend clients, signing, broadcasting, Tor transport, Nostr parsing, public endpoints, Skald-operated infrastructure, or mainnet.

Runtime behavior remains fail-closed:

- `DisabledVaultCryptoProvider` rejects every KDF, AEAD, key generation, keyset storage, and persistence operation.
- `SecureSecretStorage` is disabled.
- `SecureWalletMetadataRepository` is disabled.
- `EncryptedVaultReadinessPolicy` still reports `KdfParametersUncalibrated`.
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

The Android result covers a high-end device class only. It must not be treated as evidence for low-end Android, mid-range Android, thermal-throttled devices, memory-pressure behavior, or universal Android defaults.

## Candidate Tiers

No tier is production-final.

| Tier | Candidate | Role | Production status |
| --- | --- | --- | --- |
| Desktop candidate | 64 MiB, 3 passes, 1 lane, 32-byte output, Argon2 version 19 | Candidate desktop starting point based on current Linux desktop JVM probe evidence. | Not final; not enabled. |
| High-end Android candidate | 32 MiB, 3 passes, 1 lane, 32-byte output, Argon2 version 19 | Candidate high-end Android starting point based on Pixel 10 Pro XL / Android 16 evidence. | Not universal Android policy; not final; not enabled. |
| Mobile fallback/probe floor | 16 MiB, 2 passes, 1 lane, 32-byte output, Argon2 version 19 | Minimum testing/fallback floor only. It is useful for bounded probes and potential degraded-path analysis. | Not preferred production default; not final; not enabled. |
| Android baseline unresolved | none | Represents missing low-end and mid-range Android coverage. | No universal Android baseline exists. |

The policy treats memory hardness as a security requirement. Unlock latency is a usability constraint that can reject unusable settings, but it must not silently downgrade memory cost without explicit degraded-strength review.

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

1. Low-end Android device probe.
2. Mid-range Android device probe.
3. Thermal/load repeatability checks.
4. Lock-screen/unlock UX measurement.
5. Background/foreground behavior checks.
6. Accessibility and timeout policy review.
7. Memory-pressure failure behavior review.
8. Provider-boundary known-answer vectors.
9. Production KDF implementation review behind the Skald-owned provider boundary.
10. Secure storage and secure metadata storage review.
11. Mainnet release-hardening review before any mainnet relevance.

These blockers are represented in common policy models. They keep `KdfParametersCalibrated` unresolved and keep production KDF execution disabled.

## Future Calibration Requirements

Future calibration must:

- use public non-secret fixtures only,
- avoid real passphrases, seed material, private keys, descriptors, labels, notes, addresses, txids, credentials, or wallet metadata,
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
- Android baseline: unresolved.

`EncryptedVaultReadinessPolicy` records the candidate parameter policy as reviewed-only. `KdfParametersCalibrated` remains unresolved, `KdfParametersUncalibrated` remains a blocker, and production persistence remains disabled.

`VaultCryptoDependencyProbeCatalog` records the Tink plus Bouncy Castle split stack as having candidate Argon2id parameter policy modeled. That stack remains candidate-only and is not production-approved.

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

The next focused branch should remain design/probe-only: either collect additional Android baseline calibration evidence, or design a still-disabled executable-provider/KAT scaffold that keeps production KDF execution and storage disabled. Do not proceed to vault container read/write or persistence from this policy pass.
