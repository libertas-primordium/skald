# Encrypted Local Vault Argon2id Calibration

## Status

Skald Vault now has a Skald-owned Argon2id calibration policy model, a non-final candidate parameter policy, a manual Android calibration evidence-capture model, and bounded test/probe-only Bouncy Castle Argon2id measurement harnesses.

This is calibration planning, manual evidence capture, and dependency probing only. It does not implement a production KDF, executable production `VaultCryptoProvider`, AEAD record encryption, key generation, vault container parsing or writing, secure secret storage, secure metadata persistence, production sync, backend clients, signing, broadcasting, Tor transport, Nostr parsing, public endpoints, Skald-operated infrastructure, or mainnet. The manual Android capture protocol is documented in [`ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md). Runtime randomness/provider availability checks are documented in [`ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md`](ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md). The provider-level KAT contract is documented in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md), the test-only harness that exercises it is documented in [`ENCRYPTED_LOCAL_VAULT_TEST_PROVIDER_KAT_HARNESS.md`](ENCRYPTED_LOCAL_VAULT_TEST_PROVIDER_KAT_HARNESS.md), and the disabled provider-selection boundary is documented in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md). Production provider-level KAT execution remains unavailable because no production provider exists, and provider selection returns only the disabled provider.

Runtime behavior remains fail-closed:

- `DisabledVaultCryptoProvider` still rejects KDF, AEAD, key generation, keyset storage, KAT validation, and persistence operations.
- `SecureSecretStorage` is disabled.
- `SecureWalletMetadataRepository` is disabled.
- `EncryptedVaultReadinessPolicy` still reports `KdfParametersUncalibrated`.
- `VaultCryptoProviderSelectionRegistry` blocks production selection on non-final parameters, missing production-provider evidence, disabled storage, and runtime provider/primitive/randomness gates when they are unknown.
- The candidate parameter policy is present but not final.
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
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/AndroidArgon2idCalibrationEvidenceTest.kt
```

Probe-only runtime tests:

```text
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/VaultCryptoArgon2idCalibrationProbeTest.kt
composeApp/src/androidInstrumentedTest/kotlin/com/libertasprimordium/skald/VaultCryptoAndroidArgon2idCalibrationProbeTest.kt
```

The production policy file imports no Bouncy Castle, Tink, JCA/JCE, BDK, file, settings, network, or process APIs. Bouncy Castle Argon2id execution is confined to the desktop and Android test/probe files above, the existing public-vector KAT tests, and the test-only provider KAT harness.

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
- candidate desktop/high-end Android/fallback tiers,
- supported Android compatibility planning,
- manual Android device calibration evidence records,
- Android device class, runtime environment, release-like, thermal/load, and repeated-run evidence fields,
- final-approval blockers,
- future calibration requirements,
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

## Candidate Parameter Policy

The current candidate policy is documented in [`ENCRYPTED_LOCAL_VAULT_ARGON2ID_PARAMETER_POLICY.md`](ENCRYPTED_LOCAL_VAULT_ARGON2ID_PARAMETER_POLICY.md).

Summary:

- Desktop candidate: 64 MiB, 3 passes, 1 lane, 32-byte output, Argon2 version 19, based on current Linux desktop JVM evidence.
- High-end Android candidate: 32 MiB, 3 passes, 1 lane, 32-byte output, Argon2 version 19, based on Pixel 10 Pro XL / Android 16 evidence.
- Mobile fallback/probe floor: 16 MiB, 2 passes, 1 lane, 32-byte output, Argon2 version 19, for testing/fallback analysis only.
- Android compatibility planning: based on supported Android OS baseline, runtime provider/primitive/randomness checks, and fail-closed vault creation gates rather than mandatory low-end/mid-range model testing.
- Manual Android evidence capture: modeled for optional low-end, mid-range, high-end, release-like, and thermal/load records; current captured evidence remains high-end debug/instrumented only and does not prove all-device performance.

No row is production-final, universal Android policy, enabled for production KDF execution, or sufficient for provider selection.

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
- all-device Android performance,
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
3. The provider-selection gates in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md) must be satisfied before any non-disabled provider can be selected.
4. Production provider-level Argon2id KATs must pass on Android and Linux desktop according to [`ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md). The current test-only harness is not production provider approval.
5. Parameter calibration must respect the supported Android baseline, runtime provider/primitive/randomness checks, Linux desktop behavior, and fail-closed vault creation policy documented in [`ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md) and [`ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md`](ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md). Low-end and mid-range Android evidence remains optional parameter/UX evidence, not a compatibility hard blocker.
6. Memory cost must be treated as a security requirement, not only a UX knob.
7. Unlock latency targets must be reviewed with user-visible tradeoffs.
8. Low-memory fallback behavior must fail closed or carry explicit degraded-strength labeling.
9. Wrong-passphrase behavior must be tested.
10. Lock/session lifecycle tests must prove derived key handles are unavailable after lock as far as practical.
11. Redaction tests must prove no inputs, salts, derived bytes, or provider internals appear in logs, errors, docs, or build history.
12. Vault container, migration, and corruption tests must pass before persistence.

## Readiness Alignment

`EncryptedVaultReadinessPolicy` now records that KDF calibration policy and candidate parameter policy are modeled at candidate level, while `KdfParametersCalibrated` remains unresolved and `KdfParametersUncalibrated` remains a blocker.

`VaultCryptoDependencyProbeCatalog` records the Tink plus Bouncy Castle split stack as having Argon2id calibration policy and candidate parameter policy modeled. That does not approve production use. The stack remains candidate-only.

`VaultCryptoProviderSelectionRegistry` treats the non-final parameter policy, missing production-provider evidence, disabled storage, and unknown runtime provider/primitive/randomness checks as blockers. It selects only the disabled provider.

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

The next focused branch should remain design/probe-only unless the user explicitly approves executable-provider work. Recommended next decision point: review runtime provider/primitive/randomness availability evidence for supported Android and Linux paths or capture optional additional Android calibration evidence for parameter/UX review before any vault container or persistence implementation.
