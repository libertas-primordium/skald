# Encrypted Local Vault Dependency Review

## Status

This document records the dependency, license, Tink keyset/storage, Bouncy Castle Argon2id API, and split-provider risk review for the current Tink plus Bouncy Castle vault primitive candidate.

This is a design/review/probe record only. It does not implement encryption, a production crypto provider, key derivation, AEAD record handling, key generation, Tink keyset storage, a vault container, secure secret storage, secure metadata persistence, production sync, backend clients, signing, broadcasting, Tor transport, Nostr parsing, public endpoints, Skald-operated infrastructure, or mainnet.

Runtime behavior remains fail-closed:

- `SecureSecretStorage` is disabled.
- `SecureWalletMetadataRepository` is disabled.
- `EncryptedVaultReadinessPolicy` reports the vault as not implemented/not ready.
- Production persistence remains disabled.
- Production sync remains disabled.
- Mainnet remains disabled.

## Review Conclusion

The pinned Tink plus Bouncy Castle split stack remains the only current candidate with Android and Linux compile/package evidence, desktop JVM public-vector KAT evidence, and Android runtime public-vector KAT evidence.

The review does not approve production vault implementation. It changes the candidate status only from "Android runtime KAT-validated candidate" to "dependency, license, and keyset review complete candidate." Production implementation remains blocked by KDF calibration, a reviewed Skald-owned provider boundary, provider-level vectors, Tink keyset/key-material handling decisions, split-provider invariants, lock/session lifecycle tests, migration/corruption tests, redaction tests, vault container review, production storage review, and mainnet release-hardening review.

## Exact Dependency Inventory

Pinned candidate artifacts:

| Role | Coordinate | Version | Source-set placement |
| --- | --- | --- | --- |
| Android AEAD candidate | `com.google.crypto.tink:tink-android` | `1.21.0` | `androidMain` |
| Desktop AEAD candidate | `com.google.crypto.tink:tink` | `1.21.0` | `desktopMain` |
| Android/Desktop KDF candidate | `org.bouncycastle:bcprov-jdk18on` | `1.84` | `androidMain`, `desktopMain` |

`commonMain` remains free of direct crypto-provider dependencies. The dependencies are not wired into secure storage, secure metadata repositories, sync services, UI, settings codecs, or production wallet services.

Resolved vault-candidate transitives from the reviewed Gradle configurations:

| Configuration | Direct candidate artifacts | Relevant candidate transitives |
| --- | --- | --- |
| `desktopRuntimeClasspath` | `bcprov-jdk18on:1.84`, `tink:1.21.0` | `gson:2.13.2`, `protobuf-java:4.33.0`, `jsr305:3.0.2`, `error_prone_annotations:2.41.0` |
| `debugRuntimeClasspath` | `bcprov-jdk18on:1.84`, `tink-android:1.21.0` | `androidx.annotation:annotation-jvm:1.9.1`, `gson:2.13.2`, `jsr305:3.0.2`, `error_prone_annotations:2.41.0` |

The Tink POM also lists `google-http-client`, but it was not present in the resolved desktop or Android runtime classpath for this project during this review.

No Lazysodium, IonSpin, or libsodium runtime dependency is wired into Gradle on this branch.

## Artifact And Package Inventory

Local artifact inspection found zero native entries in:

- `tink-1.21.0.jar`
- `tink-android-1.21.0.jar`
- `bcprov-jdk18on-1.84.jar`
- `gson-2.13.2.jar`
- `protobuf-java-4.33.0.jar`
- `error_prone_annotations-2.41.0.jar`
- `jsr305-3.0.2.jar`
- `annotation-jvm-1.9.1.jar`
- `jspecify-1.0.0.jar`

Android debug APK inventory:

- Candidate dependencies are packaged as JVM/Android bytecode, not candidate native libraries.
- Native entries observed in the APK are existing non-vault app dependencies, including AndroidX graphics path, BDK FFI, and JNA libraries across Android ABIs.
- The Android test APK had no native entries in the checked output.

Linux `.deb` inventory:

- The packaged app library directory includes `bcprov-jdk18on-1.84`, `tink-1.21.0`, `gson-2.13.2`, `protobuf-java-4.33.0`, `jsr305-3.0.2`, `error_prone_annotations-2.41.0`, and `annotation-jvm-1.9.1`.
- The `.deb` also contains existing non-vault runtime/native components such as Skiko, the application launcher, JNA, and BDK JVM artifacts.
- No Tink/Bouncy native library entry was added by the vault candidate stack.

Android resource packaging:

- `bcprov-jdk18on:1.84` and `org.jspecify:jspecify:1.0.0` both contribute `META-INF/versions/9/OSGI-INF/MANIFEST.MF`.
- The build excludes that duplicate multi-release OSGI manifest resource for Android packaging.
- This exclude removes duplicate metadata only; it does not add storage, crypto execution, or provider behavior.
- Release review must re-check that this metadata exclude remains acceptable for final Android packaging.

## License Review Notes

Local Maven POM/license declarations inspected:

| Artifact | Declared license |
| --- | --- |
| `com.google.crypto.tink:tink:1.21.0` | Apache License, Version 2.0 |
| `com.google.crypto.tink:tink-android:1.21.0` | Apache License, Version 2.0 |
| `org.bouncycastle:bcprov-jdk18on:1.84` | Bouncy Castle Licence |
| `com.google.code.gson:gson:2.13.2` | Apache-2.0 |
| `com.google.protobuf:protobuf-java:4.33.0` | BSD-3-Clause inherited from Protobuf parent/BOM |
| `com.google.errorprone:error_prone_annotations:2.41.0` | Apache 2.0 |
| `com.google.code.findbugs:jsr305:3.0.2` | Apache Software License, Version 2.0 |
| `androidx.annotation:annotation-jvm:1.9.1` | Apache Software License, Version 2.0 |
| `org.jspecify:jspecify:1.0.0` | Apache License, Version 2.0 |

These notes are dependency-review evidence, not legal advice. A release checklist still needs:

- bundled notices for direct and transitive runtime artifacts,
- source/provenance review for every shipped artifact,
- dependency update and pinning policy,
- advisory/CVE monitoring for Tink, Bouncy Castle, Gson, Protobuf, AndroidX, JNA, BDK, and Compose/Skiko runtime artifacts,
- confirmation that no candidate native library has been introduced,
- release-package license inventory for APK and `.deb`,
- confirmation that the Android resource exclude does not remove a required notice or license file.

## Tink Keyset And Storage Handling

Tink is designed around key templates, keysets, key managers, primitives, and encrypted keyset handling. That model can reduce primitive misuse when an application can accept Tink's keyset lifecycle and serialization formats.

Skald must not blindly persist Tink keysets outside the encrypted vault:

- A Tink keyset can contain key material or metadata that becomes wallet-sensitive in Skald's vault model.
- Keyset JSON/binary storage is a secret-storage decision, not a general settings decision.
- Tink keyset handles, serialized keysets, key IDs, primary-key selection, and key rotation metadata must not leak into UI state, settings codecs, recovery metadata, privacy analyzer state, sync facade models, or non-secret repositories.
- If Tink keysets are used, they must be protected by the app-controlled encrypted vault or an explicitly reviewed wrapping path inside that vault lifecycle.

Open design choices before a provider boundary:

| Option | Review conclusion |
| --- | --- |
| Persist Tink keysets directly | Not approved. This risks making Tink's keyset format a parallel secret store and complicates Skald's versioned envelope and metadata classification. |
| Store raw AEAD key material inside a Skald-owned encrypted envelope | Plausible, but it requires a narrow provider boundary, explicit key hierarchy, redacted errors, and tests proving no provider-specific types escape. |
| Use a narrow Tink wrapper hidden behind Skald-owned interfaces | Plausible for AEAD, but it must not expose Tink keyset handles or internal explicit-nonce APIs outside probe/test code. |

Required tests before storing any Tink keyset or key material:

- provider-boundary public KATs on Android runtime and desktop runtime,
- key version and rotation behavior through Skald-owned models,
- associated-data binding tests,
- no provider type leakage source guards,
- redacted error tests,
- negative tests for malformed key metadata,
- vault lock/session lifecycle tests proving no keyset remains usable after lock as far as practical,
- storage tests proving keysets/key material never enter non-secret settings.

The current explicit-nonce Tink API remains probe/test-only because public fixed-nonce KATs require deterministic nonce control. It is not approved as a production vault boundary.

## Bouncy Castle Argon2id API Risk Review

Bouncy Castle exposes `Argon2BytesGenerator` and lower-level parameter builders. This gives Skald explicit control, but it also creates implementation risk.

Risks that a future Skald-owned KDF boundary must eliminate:

- selecting Argon2d or Argon2i instead of Argon2id,
- using the wrong Argon2 version,
- confusing memory units and producing a lower memory cost than intended,
- allowing arbitrary caller-controlled iterations, memory, lanes, or parallelism,
- accepting output lengths that do not match the reviewed key hierarchy,
- mishandling optional secret and associated-data fields,
- reusing mutable parameter builders across calls,
- returning raw derived bytes to arbitrary callers,
- embedding input material or derived output in errors, assertions, logs, docs, or build history,
- overclaiming zeroization on JVM/Android where byte-array clearing is best-effort and object copies may exist.

Future KDF boundary requirements:

- one fixed Argon2id parameter-set type selected by calibration,
- no runtime algorithm selection by general callers,
- typed output roles for vault key wrapping and key expansion,
- explicit domain separation and associated-data policy,
- redacted Skald-owned errors,
- public KATs and calibration tests on Android and desktop,
- no Bouncy Castle types in storage, UI, sync, recovery, privacy, or domain models.

No production KDF wrapper is implemented in this branch.

## Split-Provider Boundary Review

The current candidate uses Bouncy Castle for Argon2id and Tink for XChaCha20-Poly1305. This avoids native-library packaging risk and has passed public-vector runtime checks, but it means Skald would own the boundary between a low-level KDF provider and an AEAD provider.

Required invariants before implementation:

- one reviewed Skald-owned provider interface,
- no arbitrary caller-controlled algorithm selection,
- typed parameter sets for KDF, key expansion, nonce policy, and AEAD context,
- explicit associated-data policy tied to vault container and record metadata,
- no provider-specific types escaping into storage, UI, domain, recovery, privacy, sync, or settings models,
- redacted errors with no key material, nonce material, plaintext, ciphertext, tag, or metadata values,
- deterministic public vectors at the provider boundary,
- Android runtime and desktop runtime provider-boundary tests,
- version pinning and source guards,
- no Tink keyset or Bouncy Castle parameter object persistence,
- no production storage success path until vault container review passes.

The split stack remains acceptable as a candidate after this review because no native candidate has yet shown lower packaging risk in this repo, and the selected primitives have public-vector evidence on Android and desktop runtime. It is still not an implementation-approved stack.

## Provider-Boundary Prerequisites

A future disabled `VaultCryptoProvider` design branch should remain interface/policy-only unless explicitly approved otherwise.

Required checklist for that branch:

- define a Skald-owned provider interface with no provider-specific public types,
- expose only disabled/fail-closed provider readiness by default,
- keep production storage and key generation disabled,
- require candidate public KAT vectors at the provider boundary,
- require Android runtime and desktop runtime vector execution,
- encode redacted Skald-owned error types,
- encode no-storage and no-keyset-persistence restrictions,
- encode provider version and algorithm policy as read-only status,
- preserve source guards that confine provider imports,
- avoid passphrase, PIN, biometric, unlock, and vault setup UI,
- avoid vault container parsing/writing until a separate container review branch.

Provider-boundary non-goals:

- no production vault implementation,
- no production key generation,
- no production KDF/AEAD record implementation,
- no Tink keyset persistence,
- no Bouncy Castle production KDF wrapper,
- no secure storage success path,
- no metadata persistence success path,
- no production sync or wallet activation,
- no mainnet path.

## Current Model Status

`VaultCryptoDependencyProbeCatalog` records the Tink plus Bouncy Castle split stack as `DependencyLicenseAndKeysetReviewCompleteCandidate`.

That status means:

- dependency inventory was inspected,
- local POM license declarations were inspected,
- APK and `.deb` package inventories were inspected,
- Tink keyset/storage risks were documented,
- Bouncy Castle Argon2id API risks were documented,
- split-provider boundary requirements were documented.

That status does not mean:

- production dependency approval,
- legal approval,
- KDF calibration,
- provider-boundary approval,
- keyset storage approval,
- encrypted vault implementation,
- secret or metadata persistence,
- production sync,
- mainnet approval.

`EncryptedVaultReadinessPolicy` still reports the vault as not implemented/not ready. Its dependency-selection requirement is candidate-reviewed only and does not satisfy production persistence.

## What Remains Unproven

- KDF parameter calibration for Android and Linux desktop.
- Provider-boundary KAT behavior through a Skald-owned interface.
- Whether Tink keysets or raw AEAD key material will be used inside the vault.
- Whether Tink's production APIs can satisfy Skald's random-nonce record envelope without relying on internal explicit-nonce APIs.
- Vault container parser/writer design.
- Atomic write, fsync, migration, and corruption behavior.
- Lock/session memory lifecycle behavior.
- Redaction and failure-mode coverage.
- Release legal review and notices.
- Mainnet release-hardening review.

## Next Step

The next focused branch should remain design/probe-only unless the user explicitly narrows it otherwise: design a disabled Skald-owned `VaultCryptoProvider` boundary with provider-level KAT requirements and no storage success path, or run KDF calibration planning for Argon2id on Android and Linux desktop. Neither step should implement a vault container or enable persistence.
