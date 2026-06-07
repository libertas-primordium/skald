# Encrypted Local Vault Android Calibration Capture

## Status

Skald Vault now has a Skald-owned manual Android Argon2id calibration evidence-capture model and protocol.

This is evidence modeling and manual capture guidance only. It does not implement production KDF execution, executable production `VaultCryptoProvider` behavior, AEAD execution, key generation, Tink keyset creation or storage, raw key material persistence, vault container read/write, passphrase/PIN/biometric unlock UI, secure secret storage success, secure metadata persistence success, production sync, backend clients, signing, broadcasting, Tor transport, Nostr parsing, public endpoints, Skald-operated infrastructure, or mainnet.

The current Pixel 10 Pro XL / Android 16 calibration evidence remains high-end Android debug/instrumented evidence only. It does not satisfy a universal Android baseline, release-like runtime coverage, low-end coverage, mid-range coverage, thermal/load repeatability, final parameter approval, provider selection, storage readiness, or production KDF approval.

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

Manual calibration evidence can satisfy future Android baseline evidence requirements only when the reviewed device-class, release-like, thermal/load, and fixture gates are complete. Manual evidence cannot mark the production KDF approved. Final production parameters still require provider implementation review, production provider-level KATs, lock/session lifecycle tests, storage review, redaction/failure-mode tests, migration/corruption tests, and release-hardening review.

## Device-Class Coverage

Use these classes for review triage. They are not marketing labels.

| Class | Intended evidence role | Notes |
| --- | --- | --- |
| High-end Android | Upper-bound performance and UX evidence. | Current Pixel 10 Pro XL / Android 16 evidence belongs here only. |
| Mid-range Android | Mainstream supported-device baseline evidence. | Required before universal Android policy. |
| Low-end Android | Memory-pressure and latency floor evidence. | Required before universal Android policy. |
| Unknown | Temporary holding state for incomplete manual records. | Does not satisfy baseline coverage. |

At least one high-end, one mid-range, and one low-end device-class evidence record is required before Android baseline coverage can be considered. Release-like evidence and thermal/load repeatability are separate gates; debug/instrumented evidence does not satisfy them.

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
- Android baseline satisfied:
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

Manual Android evidence feeds the future Argon2id parameter policy as device-class coverage and runtime-environment context. It may help decide whether the high-end Android candidate, mobile fallback/probe floor, or a future Android baseline candidate is usable.

It does not approve:

- production KDF execution,
- production provider implementation,
- provider selection beyond the disabled provider,
- final Android parameters,
- secure storage,
- secure metadata persistence,
- vault container storage,
- production sync,
- mainnet relevance.

## Next Step

The next focused pass should remain design/probe-only: capture additional Android calibration evidence on at least one mid-range and one low-end supported device, preferably with release-like and thermal/load repeatability notes. Executable production provider work, vault containers, and persistence must remain separate branches with explicit approval.
