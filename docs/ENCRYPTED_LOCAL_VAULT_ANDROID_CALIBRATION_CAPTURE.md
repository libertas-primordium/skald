# Encrypted Local Vault Android Calibration Capture

## Status

Skald Vault now has a Skald-owned manual Android Argon2id calibration evidence-capture model and protocol.

This is evidence modeling and manual capture guidance only. The still-disabled calibration policy building block is documented in [`ENCRYPTED_LOCAL_VAULT_ARGON2ID_CALIBRATION.md`](ENCRYPTED_LOCAL_VAULT_ARGON2ID_CALIBRATION.md); this capture protocol does not implement provider-selectable calibration, provider-selectable KDF execution, executable production `VaultCryptoProvider` behavior, AEAD execution, key generation, Tink keyset creation or storage, raw key material persistence, file-backed vault container read/write, passphrase/PIN/biometric unlock UI, secure secret storage success, secure metadata persistence success, production sync, backend clients, signing, broadcasting, Tor transport, Nostr parsing, public endpoints, Skald-operated infrastructure, or mainnet.

The current Pixel 10 Pro XL / Android 16 calibration evidence remains high-end Android debug/instrumented evidence only. It does not prove performance across all supported Android devices, release-like runtime behavior, thermal/load repeatability, final parameter approval, provider selection, storage readiness, or production KDF approval.

Android compatibility and entropy policy is documented in [`ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md). Runtime randomness/provider checks are documented in [`ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md`](ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md). Those policies record the user decision to forego low-end Android model testing as a hard compatibility blocker. Low-end and mid-range calibration evidence remains useful optional parameter/UX evidence; supported Android OS baseline, runtime provider/primitive/randomness checks, and fail-closed vault creation gates now define compatibility planning.

## Source Location

Production-safe common evidence models:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/Argon2idCalibrationPolicy.kt
```

Common tests:

```text
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/AndroidArgon2idCalibrationEvidenceTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/Argon2idCalibrationPolicyTest.kt
```

Existing Android probe-only runtime test:

```text
composeApp/src/androidInstrumentedTest/kotlin/com/libertasprimordium/skald/VaultCryptoAndroidArgon2idCalibrationProbeTest.kt
```

The common evidence model imports no Tink, Bouncy Castle, JCA/JCE, BDK, platform keystore, file, SharedPreferences, settings, network, or process APIs. Bouncy Castle Argon2id execution remains confined to approved KAT, calibration, and test-provider harness files in test/platform source sets.

## Evidence Model

The common model records:

- Android device class: low-end, mid-range, high-end, or unknown.
- Android version and API level.
- Generic manufacturer and model if manually recorded.
- Build profile: debug/instrumented, release-like manual, or unknown.
- Thermal state note.
- Foreground/background note.
- Battery/charging note.
- Memory pressure note.
- Argon2id version.
- Memory recorded in explicit MiB only.
- Pass count.
- Lane count.
- Output byte length.
- Elapsed milliseconds.
- Run count.
- Optional min/median/max elapsed milliseconds for repeated runs.
- Optional redacted failure reason.
- Public non-secret fixture flag.
- Thermal/load repeatability flag.

The model rejects ambiguous memory units, KiB labels in Android manual evidence, zero or negative elapsed time, missing repeated-run timing summaries for repeated evidence, unordered repeated timing summaries, missing required runtime fields, invalid API levels, and secret-like or personal-device fields.

## Sufficiency Gates

`AndroidArgon2idCalibrationEvidencePolicy.assess(...)` reports:

- high-end evidence present,
- mid-range evidence present or missing,
- low-end evidence present or missing,
- release-like evidence present or missing,
- thermal/load repeatability present or missing,
- public non-secret fixture discipline,
- final production KDF approval remains false.

Manual calibration evidence can inform future Android parameter review when the recorded device class, runtime profile, thermal/load notes, and fixture discipline are clear. It no longer functions as a mandatory low-end/mid-range compatibility gate. Manual evidence cannot mark the production KDF approved. Final production parameters still require supported-platform runtime provider and randomness checks, provider implementation review, production provider-level KATs, lock/session lifecycle tests, storage review, redaction/failure-mode tests, migration/corruption tests, and release-hardening review.

## Device-Class Coverage

Use these classes for review triage. They are not marketing labels.

| Class | Intended evidence role | Notes |
| --- | --- | --- |
| High-end Android | Upper-bound performance and UX evidence. | Current Pixel 10 Pro XL / Android 16 evidence belongs here only. |
| Mid-range Android | Optional mainstream supported-device parameter and UX evidence. | Useful for tuning, no longer a hard compatibility blocker. |
| Low-end Android | Optional memory-pressure and latency floor evidence. | Useful for tuning, no longer a hard compatibility blocker. |
| Unknown | Temporary holding state for incomplete manual records. | Does not prove performance generalization. |

The compatibility gate is not exhaustive device-class coverage. Supported Android baseline plus runtime primitive/provider checks, approved cryptographic randomness path, and fail-closed vault-creation behavior replace low-end/mid-range coverage as the Android compatibility gate. Release-like evidence and thermal/load repeatability remain desirable for UX and release review; debug/instrumented evidence does not satisfy release-like evidence.

## Manual Command Recipes

Use a temporary Gradle home and Android user home for repeatability:

```bash
GRADLE_USER_HOME=/tmp/skald-gradle-home
ANDROID_USER_HOME=/tmp/skald-android-user-home
ANDROID_HOME=/home/spencer/Android/Sdk
JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
```

List connected devices and choose the exact target:

```bash
/home/spencer/Android/Sdk/platform-tools/adb devices -l
```

If using wireless debugging, connect first and then list devices again:

```bash
/home/spencer/Android/Sdk/platform-tools/adb connect <ip>:<port>
/home/spencer/Android/Sdk/platform-tools/adb devices -l
```

Pin the same target in both ADB and Gradle. This avoids mDNS or stale wireless target drift:

```bash
GRADLE_USER_HOME=/tmp/skald-gradle-home ANDROID_USER_HOME=/tmp/skald-android-user-home ANDROID_HOME=/home/spencer/Android/Sdk ANDROID_SERIAL=<serial-or-ip-port> ./gradlew --no-daemon -Djava.net.preferIPv4Stack=true -Pkotlin.compiler.execution.strategy=in-process -Pandroid.injected.device.serial=<serial-or-ip-port> -Pandroid.testInstrumentationRunnerArguments.class=com.libertasprimordium.skald.VaultCryptoAndroidArgon2idCalibrationProbeTest :composeApp:connectedDebugAndroidTest
```

Assemble the Android test APK before a device run when checking package readiness:

```bash
GRADLE_USER_HOME=/tmp/skald-gradle-home ANDROID_USER_HOME=/tmp/skald-android-user-home ANDROID_HOME=/home/spencer/Android/Sdk ./gradlew --no-daemon -Djava.net.preferIPv4Stack=true -Pkotlin.compiler.execution.strategy=in-process :composeApp:assembleDebugAndroidTest
```

Do not uninstall unless explicitly intended. If uninstall is required for a local package-state blocker, target only Skald's package:

```bash
/home/spencer/Android/Sdk/platform-tools/adb -s <serial-or-ip-port> uninstall com.libertasprimordium.skald
```

## Evidence Template

Record evidence in a reviewer note or future checked-in documentation only after removing serials and personal identifiers.

```text
Evidence ID:
Recorder:
Date:

Device class:
Class rationale:
Manufacturer:
Model:
Android version:
API level:
Build profile: debug/instrumented | release-like manual | unknown

Thermal state note:
Foreground/background note:
Battery/charging note:
Memory pressure note:

Command:
ADB target pinned: yes | no
ADB serial recorded in evidence: no

Run results:
- candidate:
  Argon2id version: 19
  memory MiB:
  passes:
  lanes:
  output bytes:
  elapsed milliseconds:
  run count:
  min/median/max milliseconds if repeated:
  failure reason, redacted if any:

Fixture policy:
- public non-secret fixture only: yes | no
- no passphrase, PIN, biometric secret, wallet label, UTXO label, transaction note, address, txid, seed, mnemonic, private key, credential, Nostr private material, Lightning credential, Cashu proof, RPC cookie, backend token, or wallet database recorded: yes | no

Assessment:
- high-end evidence present:
- mid-range evidence present:
- low-end evidence present:
- release-like evidence present:
- thermal/load repeatability present:
- compatibility planning affected by this evidence:
- all-device performance proven: no
- production KDF approved: no
```

## What Not To Record

Do not record:

- passphrases, PINs, biometric enrollment details, or unlock secrets,
- mnemonics, entropy, seeds, private descriptors, private keys, WIFs, xprv/tprv values, or wallet databases,
- Nostr private material,
- backend credentials, RPC cookies, API tokens, Lightning credentials, or Cashu proofs,
- wallet labels, UTXO labels, transaction notes, observed addresses, txids, PSBTs, or transaction hex,
- ADB serials, IMEI values, Android IDs, account names, user names, or other personal device identifiers.

Generic manufacturer, model, Android version, and API level are enough for manual calibration review.

## How Evidence Feeds Policy

Manual Android evidence feeds the future Argon2id parameter policy as device-class and runtime-environment context. It may help decide whether the high-end Android candidate, mobile fallback/probe floor, or a future supported-Android candidate is usable. It is not a substitute for the compatibility/entropy gates in [`ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md), nor for the runtime randomness/provider checks in [`ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md`](ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md).

It does not approve:

- calibration or provider-selectable KDF execution,
- production provider implementation,
- provider selection beyond the disabled provider,
- final Android parameters,
- secure storage,
- secure metadata persistence,
- vault container storage,
- production sync,
- mainnet relevance.

## KDF Calibration Authorization Boundary

`SkaldVaultV1KdfCalibrationAuthorizationPolicy` now consumes Android calibration evidence only as typed, redacted model evidence. It models future Android calibration review alongside KDF operation kinds, purposes, parameter/evidence kinds, platform/device classes, required gates, blockers, warnings, and disabled capabilities, but current Android KDF calibration authorization is blocked/fail-closed.

The boundary does not run Android calibration, run benchmarks, inspect real Android device identifiers, inspect real memory/CPU details, run Argon2id, run KDFs, approve final Android KDF parameters, normalize or encode real passphrases, generate or consume salts, call Android randomness APIs, run provider operations, run provider KATs, derive vault keys, enable unlock, enable persistence, make a provider selectable, or approve mainnet. Android calibration remains future-reviewed only and hardware-backed key protection is not KDF approval by itself.

## Next Step

The next focused pass should remain design/probe-only unless explicitly narrowed by the user: review runtime provider/primitive/randomness availability evidence for supported Android and Linux paths, or capture optional additional Android calibration evidence for parameter/UX review. Executable production provider work, vault containers, entropy collection, key generation, and persistence must remain separate branches with explicit approval.
