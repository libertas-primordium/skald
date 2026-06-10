# Encrypted Local Vault Argon2id Calibration

## Status

Skald Vault now has a Skald-owned Argon2id calibration policy building block, deterministic candidate-selection policy, memory/execution failure modeling, stored-parameter no-downgrade modeling, a manual Android calibration evidence-capture model, bounded test/probe-only Bouncy Castle Argon2id measurement harnesses, and a still-disabled explicit-parameter Bouncy Castle Argon2id passphrase-to-root-material building block.

The v1 calibration policy id exposed by the still-disabled provider facade is:

```text
skald-vault-v1-argon2id-calibration-policy-v1
```

This is calibration policy, manual evidence capture, dependency probing, and isolated building-block implementation only. It does not implement vault creation, unlock UI, storage, an executable selectable production `VaultCryptoProvider`, provider-selectable KDF execution, production AEAD record encryption, production key generation, vault container parsing or writing, secure secret storage, secure metadata persistence, production sync, backend clients, signing, broadcasting, Tor transport, Nostr parsing, public endpoints, Skald-operated infrastructure, or mainnet. The manual Android capture protocol is documented in [`ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md). Runtime randomness/provider availability checks are documented in [`ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md`](ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md). The provider-level KAT contract is documented in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md), the test-only harness that exercises it is documented in [`ENCRYPTED_LOCAL_VAULT_TEST_PROVIDER_KAT_HARNESS.md`](ENCRYPTED_LOCAL_VAULT_TEST_PROVIDER_KAT_HARNESS.md), and the disabled provider-selection boundary is documented in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md). Production provider selectability remains unavailable because no selectable production provider exists, and provider selection returns only the disabled provider.

Runtime behavior remains fail-closed:

- `DisabledVaultCryptoProvider` still rejects KDF, AEAD, key generation, keyset storage, KAT validation, and persistence operations.
- `SecureSecretStorage` is disabled.
- `SecureWalletMetadataRepository` is disabled.
- `EncryptedVaultReadinessPolicy` reports Argon2id calibration policy, candidate selection, memory-failure handling, and stored-parameter no-downgrade modeling as implemented but still disabled.
- `EncryptedVaultReadinessPolicy` still reports `KdfParametersUncalibrated` and final bounded calibration approval blockers.
- `VaultCryptoProviderSelectionRegistry` blocks production selection on non-final parameters, missing production-provider evidence, disabled storage, and runtime provider/primitive/randomness gates when they are unknown.
- `SkaldVaultV1StillDisabledProviderFacade` reports calibration evidence as disabled metadata only; calibration evidence does not make the facade or any provider selectable.
- The shared 64 MiB / t=3 / p=1 floor policy is implemented as a still-disabled building block, but final production calibration approval remains blocked.
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

The calibration policy file imports no Bouncy Castle, Tink, JCA/JCE, BDK, file, settings, network, or process APIs. Bouncy Castle Argon2id execution is confined to the desktop and Android test/probe files above, the existing public-vector KAT tests, the test-only provider KAT harness, and the still-disabled platform actuals for explicit-parameter root derivation.

## Policy Model

The calibration policy models:

- Argon2id version 19,
- explicit memory units (`KiB` and `MiB` only),
- pass count,
- lane count,
- output length with the v1 64-byte root-material requirement,
- platform/device-class labels,
- target latency bands,
- candidate rejection reasons,
- probe-only warnings,
- candidate desktop/shared-floor/high-end Android evidence tiers,
- deterministic candidate selection from synthetic observation inputs,
- preferred unlock target around 1 second,
- acceptable unlock duration around 2 seconds,
- no weakening below the v1 floor merely to force sub-1-second unlock,
- minimum-floor allocation/execution failure as fail-closed evidence,
- existing stored vault parameters as authoritative,
- stored-parameter unsupported-on-device failure as fail-closed unlock evidence,
- silent downgrade rejection,
- supported Android compatibility planning,
- manual Android device calibration evidence records,
- Android device class, runtime environment, release-like, thermal/load, and repeated-run evidence fields,
- final-approval blockers,
- future calibration requirements,
- coarse calibration result summaries,
- provider-selectable KDF disabled status.

The model rejects:

- zero or negative memory,
- ambiguous memory labels such as `32M` or `64 K`,
- zero pass count,
- zero lane count,
- output shorter than 32 bytes in legacy policy parsing,
- v1 creation/stored parameters below 64 MiB memory,
- v1 creation/stored parameters below t=3,
- v1 creation/stored parameters with p other than 1,
- salt shorter than 16 bytes,
- root output length other than 64 bytes,
- unsupported Argon2 type or version,
- invalid elapsed timing observations,
- failed minimum-floor execution,
- stored parameters unsupported on the current device,
- silent downgrade attempts,
- PBKDF2 as the default production vault KDF,
- any claim that calibration or provider-selectable KDF execution is available in this branch.

scrypt remains a reviewed compatibility fallback only. It is not selected for the current vault KDF policy.

## Probe Candidates

These candidates are still-disabled policy/probe candidates and are not production recommendations:

| Candidate ID | Memory | Passes | Lanes | Output | Version | Platform scope |
| --- | ---: | ---: | ---: | ---: | --- | --- |
| `argon2id-v1-floor-64mib-t3-p1-root64` | 64 MiB | 3 | 1 | 64 bytes | Argon2 version 19 | Linux desktop JVM, Android runtime |
| `argon2id-v1-desktop-stronger-96mib-t3-p1-root64` | 96 MiB | 3 | 1 | 64 bytes | Argon2 version 19 | Linux desktop JVM, desktop extended probe |

The Android runtime probe now runs the shared 64 MiB / t=3 / p=1 floor row only. The desktop probe runs the shared floor and the 96 MiB stronger desktop candidate. Both probes remain test/probe-only and do not persist results or approve production parameters.

## Candidate Parameter Policy

The current candidate policy is documented in [`ENCRYPTED_LOCAL_VAULT_ARGON2ID_PARAMETER_POLICY.md`](ENCRYPTED_LOCAL_VAULT_ARGON2ID_PARAMETER_POLICY.md).

Summary:

- Shared v1 floor: 64 MiB, 3 passes, 1 lane, 64-byte output, Argon2 version 19, used by Android and desktop as the minimum policy floor.
- Desktop stronger candidate: 96 MiB, 3 passes, 1 lane, 64-byte output, Argon2 version 19, for bounded desktop candidate-selection evidence only.
- Historical 16 MiB and 32 MiB probe evidence remains useful timing history only; it is below the v1 floor and is not an active v1 floor or fallback.
- Android compatibility planning: based on supported Android OS baseline, runtime provider/primitive/randomness checks, and fail-closed vault creation gates rather than mandatory low-end/mid-range model testing.
- Manual Android evidence capture: modeled for optional low-end, mid-range, high-end, release-like, and thermal/load records; current captured evidence remains high-end debug/instrumented only and does not prove all-device performance.

No row is production-final, universal Android policy, enabled for provider-selectable KDF execution, or sufficient for provider selection.

The v1 production-provider acceptance contract requires the shared minimum review floor of Argon2id version 19, 64 MiB, 3 passes, 1 lane, at least a 16-byte salt, preferred 32-byte salt for new vaults, and 64-byte derived root material. The policy building block enforces those minima for creation/stored-parameter validation. Bounded calibration may choose stronger desktop parameters, but it must not weaken parameters to force sub-1-second unlocks. Roughly 2 seconds is acceptable and not a failure condition.

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
6. Bounded calibration must approve the shared 64 MiB / t=3 / p=1 floor and any stronger platform-specific choices. The still-disabled policy model now enforces the floor, but final production calibration approval remains a separate blocker.
7. Memory cost must be treated as a security requirement, not only a UX knob.
8. Unlock latency targets must be reviewed with user-visible tradeoffs; roughly 2 seconds is acceptable.
9. Minimum-floor allocation or execution failure during vault creation must fail closed. The still-disabled policy model maps floor execution failure to a fail-closed result; no vault creation path exists.
10. Stored existing-vault parameters must remain authoritative; unlock on a weaker device must fail closed if those parameters cannot allocate or complete. The still-disabled policy model maps unsupported stored parameters to a fail-closed result; no unlock path exists.
11. Any future downgrade or migration must require successful passphrase unlock and explicit user action.
12. Wrong-passphrase behavior must be tested.
13. Lock/session lifecycle tests must prove derived key handles are unavailable after lock as far as practical.
14. Redaction tests must prove no inputs, salts, derived bytes, or provider internals appear in logs, errors, docs, or build history.
15. Vault container, migration, and corruption tests must pass before persistence.

## Readiness Alignment

`EncryptedVaultReadinessPolicy` now records that the Argon2id calibration policy, deterministic candidate selection, memory-failure handling, and stored-parameter no-downgrade modeling are implemented as still-disabled building blocks, while passphrase policy validation and explicit-parameter Argon2id root derivation are also implemented but still disabled. `KdfParametersCalibrated` remains unresolved and `KdfParametersUncalibrated` remains a blocker.

`VaultCryptoDependencyProbeCatalog` records the Tink plus Bouncy Castle split stack as having still-disabled Argon2id calibration policy, candidate selection, memory-failure handling, and stored-parameter no-downgrade evidence. That does not approve production use. The stack remains candidate-only.

`VaultCryptoProviderSelectionRegistry` treats missing final production calibration approval, missing production-provider evidence, disabled storage, and unknown runtime provider/primitive/randomness checks as blockers. It selects only the disabled provider.

The production-provider acceptance contract records bounded calibration and memory-failure evidence as implemented/tested at the still-disabled building-block level, while final production calibration approval and release/storage/provider gates remain blocked.

## Explicit Non-Capabilities

This calibration branch does not enable:

- provider-selectable KDF execution,
- vault creation or unlock,
- final production calibration approval,
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

## Next Step

The next focused branch should keep provider selection disabled unless the user explicitly approves provider-selectability work. Recommended next decision point: review runtime provider/primitive/randomness availability evidence for supported Android and Linux paths, then decide whether final production calibration approval can move forward before any vault container or persistence implementation.
