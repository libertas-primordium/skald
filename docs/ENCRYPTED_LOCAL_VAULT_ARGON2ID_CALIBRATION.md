# Encrypted Local Vault Argon2id Calibration

## Status

Skald Vault now has a Skald-owned Argon2id calibration policy model and bounded test/probe-only Bouncy Castle Argon2id measurement harnesses.

This is calibration planning and dependency probing only. It does not implement a production KDF, executable `VaultCryptoProvider`, AEAD record encryption, key generation, vault container parsing or writing, secure secret storage, secure metadata persistence, production sync, backend clients, signing, broadcasting, Tor transport, Nostr parsing, public endpoints, Skald-operated infrastructure, or mainnet.

Runtime behavior remains fail-closed:

- `DisabledVaultCryptoProvider` still rejects KDF, AEAD, key generation, keyset storage, and persistence operations.
- `SecureSecretStorage` is disabled.
- `SecureWalletMetadataRepository` is disabled.
- `EncryptedVaultReadinessPolicy` still reports `KdfParametersUncalibrated`.
- Production persistence remains disabled.
- Production sync remains disabled.
- Mainnet remains disabled.

## Source Location

Policy-only common models:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/Argon2idCalibrationPolicy.kt
```

Policy tests:

```text
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/Argon2idCalibrationPolicyTest.kt
```

Probe-only runtime tests:

```text
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/VaultCryptoArgon2idCalibrationProbeTest.kt
composeApp/src/androidInstrumentedTest/kotlin/com/libertasprimordium/skald/VaultCryptoAndroidArgon2idCalibrationProbeTest.kt
```

The production policy file imports no Bouncy Castle, Tink, JCA/JCE, BDK, file, settings, network, or process APIs. Bouncy Castle Argon2id execution is confined to the desktop and Android test/probe files above and the existing public-vector KAT tests.

## Policy Model

The calibration policy models:

- Argon2id version 19,
- explicit memory units (`KiB` and `MiB` only),
- pass count,
- lane count,
- output length with a 32-byte minimum for vault KDF output,
- platform/device-class labels,
- target latency bands,
- candidate rejection reasons,
- probe-only warnings,
- coarse calibration result summaries,
- production KDF disabled status.

The model rejects:

- zero or negative memory,
- ambiguous memory labels such as `32M` or `64 K`,
- zero pass count,
- zero lane count,
- output shorter than 32 bytes,
- PBKDF2 as the default production vault KDF,
- any claim that production KDF execution is available in this branch.

scrypt remains a reviewed compatibility fallback only. It is not selected for the current vault KDF policy.

## Probe Candidates

These candidates are explicitly probe-only and are not production recommendations:

| Candidate ID | Memory | Passes | Lanes | Output | Version | Platform scope |
| --- | ---: | ---: | ---: | ---: | --- | --- |
| `argon2id-probe-16mib-2p-1lane` | 16 MiB | 2 | 1 | 32 bytes | Argon2 version 19 | Linux desktop JVM, Android runtime, Android memory-constrained class |
| `argon2id-probe-32mib-3p-1lane` | 32 MiB | 3 | 1 | 32 bytes | Argon2 version 19 | Linux desktop JVM, Android runtime |
| `argon2id-probe-64mib-3p-1lane` | 64 MiB | 3 | 1 | 32 bytes | Argon2 version 19 | Linux desktop JVM, desktop extended probe |

The Android runtime probe intentionally runs only the 16 MiB and 32 MiB rows. The 64 MiB row is modeled for desktop probing and future explicit Android/device-class review; it is not run by default on Android in this branch to avoid unnecessary memory pressure.

## Probe Fixture Policy

The probe tests use public non-wallet byte-pattern fixture inputs and salts. They are not passphrases, seeds, private keys, descriptors, addresses, txids, labels, notes, credentials, or wallet metadata.

The tests do not persist probe results. They print coarse timing summaries only:

```text
ARGON2ID_CALIBRATION_PROBE candidate=<candidate-id> platform=<platform-class> elapsedMs=<milliseconds> persisted=false finalProductionSetting=false
```

The tests do not print input bytes, salt bytes, derived output bytes, or provider internals.

## Timing Interpretation

Probe timings are evidence for future calibration planning, not final production settings.

Recorded timing evidence for this branch:

| Platform evidence | Candidate ID | Coarse elapsed time | Status |
| --- | --- | ---: | --- |
| Linux desktop JVM, desktop test run on 2026-06-07 | `argon2id-probe-16mib-2p-1lane` | 42 ms | probe-only, not persisted, not final |
| Linux desktop JVM, desktop test run on 2026-06-07 | `argon2id-probe-32mib-3p-1lane` | 68 ms | probe-only, not persisted, not final |
| Linux desktop JVM, desktop test run on 2026-06-07 | `argon2id-probe-64mib-3p-1lane` | 160 ms | probe-only, not persisted, not final |
| Pixel 10 Pro XL / Android 16 instrumented runtime on 2026-06-07 | `argon2id-probe-16mib-2p-1lane` | 321 ms | probe-only, not persisted, not final |
| Pixel 10 Pro XL / Android 16 instrumented runtime on 2026-06-07 | `argon2id-probe-32mib-3p-1lane` | 707 ms | probe-only, not persisted, not final |

The Android runtime evidence came from the same explicit raw ADB IP:port targeting used for the prior Android KAT validation. The test-only log tag emitted only the redacted summary string shown above; it did not log input bytes, salt bytes, derived output bytes, key material, wallet metadata, or provider internals.

They do not prove:

- side-channel resistance,
- memory zeroization,
- release-build behavior,
- low-end Android behavior,
- thermal-throttled behavior,
- backgrounded-app behavior,
- wrong-passphrase behavior,
- lock/session lifecycle correctness,
- production vault usability.

Android timing varies by RAM, CPU class, thermal state, battery mode, background restrictions, OEM behavior, and whether a device is running debug or release builds. Linux desktop timing varies by CPU, memory pressure, scheduler behavior, power mode, JVM warmup, and package/runtime environment.

## Production Calibration Requirements

Before Argon2id can be used for production vault unlock:

1. Final dependency/provider choice must be explicitly approved.
2. A Skald-owned executable provider boundary must exist behind disabled gates.
3. Provider-level Argon2id KATs must pass on Android and Linux desktop.
4. Parameter calibration must cover supported Android device classes and Linux desktop.
5. Memory cost must be treated as a security requirement, not only a UX knob.
6. Unlock latency targets must be reviewed with user-visible tradeoffs.
7. Low-memory fallback behavior must fail closed or carry explicit degraded-strength labeling.
8. Wrong-passphrase behavior must be tested.
9. Lock/session lifecycle tests must prove derived key handles are unavailable after lock as far as practical.
10. Redaction tests must prove no inputs, salts, derived bytes, or provider internals appear in logs, errors, docs, or build history.
11. Vault container, migration, and corruption tests must pass before persistence.

## Readiness Alignment

`EncryptedVaultReadinessPolicy` now records that KDF calibration policy is modeled at candidate level, while `KdfParametersCalibrated` remains unresolved and `KdfParametersUncalibrated` remains a blocker.

`VaultCryptoDependencyProbeCatalog` records the Tink plus Bouncy Castle split stack as having Argon2id calibration policy modeled. That does not approve production use. The stack remains candidate-only.

## Explicit Non-Capabilities

This calibration branch does not enable:

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

The next focused branch should remain design/probe-only unless the user explicitly approves executable-provider work. Recommended next decision point: review calibration timing evidence and decide whether to design a still-disabled executable provider/KAT scaffold or compare an alternate Argon2id provider before any vault container or persistence implementation.
