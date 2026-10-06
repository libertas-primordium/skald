# Encrypted Local Vault Prototype Quarantine

Recorded 2026-10-06. Phase: `vault-01-prototype-quarantine`. Starting development and working-branch revision: `db2bf772e68192687ca57925a032ba11213828cb`.

This phase removes the unsupported P0 executable format/crypto graph, its attached fixed fixtures and integrated KAT harness, and the synthetic working-parser classifier from application compilation. Their historical contracts remain executable in tests. The production app retains disabled vault/storage/provider interfaces and the small material-free KDF compatibility model required by existing policy evidence.

The [July 12 canonical decision](ENCRYPTED_LOCAL_VAULT_V1_CANONICAL_ARCHITECTURE_DECISION.md) remains `CANONICAL_ARCHITECTURE_SELECTED_IMPLEMENTATION_BLOCKED`. Source isolation is a prerequisite to canonical implementation; it does not implement or approve a canonical codec, provider, session, persistence path, or real secret input. The [reconciliation audit](ENCRYPTED_LOCAL_VAULT_V1_FORMAT_PARSER_CRYPTO_RECONCILIATION_AUDIT.md) remains a dated inventory of the earlier production-compiled graph, not a current placement claim.

## Exact relocation boundary

All paths below are under `composeApp/src/`. Every listed file retains package `com.libertasprimordium.skald.security`. Within each source root, the suffix is `kotlin/com/libertasprimordium/skald/security/` followed by the filename. These are 16 relocated files: seven common P0 files, eight platform actual files, and one synthetic classifier file.

| File | Former production root | Test-only root |
| --- | --- | --- |
| `SkaldVaultV1ContainerFormat.kt` | `commonMain` | `prototypeTestSupport` |
| `SkaldVaultV1ManifestFormat.kt` | `commonMain` | `prototypeTestSupport` |
| `SkaldVaultV1HeaderCommitment.kt` | `commonMain` | `prototypeTestSupport` |
| `SkaldVaultV1RecordAead.kt` | `commonMain` | `prototypeTestSupport` |
| `SkaldVaultV1Argon2idRootDerivation.kt` | `commonMain` | `prototypeTestSupport` |
| `SkaldVaultV1PassphrasePolicy.kt` | `commonMain` | `prototypeTestSupport` |
| `SkaldVaultV1StillDisabledProviderKatHarness.kt` | `commonMain` | `prototypeTestSupport` |
| `EncryptedVaultWorkingParser.kt` | `commonMain` | `prototypeTestSupport` |
| `AndroidSkaldVaultV1Argon2idRootDerivation.kt` | `androidMain` | `androidPrototypeTestSupport` |
| `AndroidSkaldVaultV1HeaderCommitmentCrypto.kt` | `androidMain` | `androidPrototypeTestSupport` |
| `AndroidSkaldVaultV1PassphrasePolicy.kt` | `androidMain` | `androidPrototypeTestSupport` |
| `AndroidSkaldVaultV1RecordAead.kt` | `androidMain` | `androidPrototypeTestSupport` |
| `DesktopSkaldVaultV1Argon2idRootDerivation.kt` | `desktopMain` | `desktopTest` |
| `DesktopSkaldVaultV1HeaderCommitmentCrypto.kt` | `desktopMain` | `desktopTest` |
| `DesktopSkaldVaultV1PassphrasePolicy.kt` | `desktopMain` | `desktopTest` |
| `DesktopSkaldVaultV1RecordAead.kt` | `desktopMain` | `desktopTest` |

The relocated graph includes container/manifest serializers and readers, P0 canonical-header models/encoders, direct Argon2id, HKDF/HMAC, record AEAD, passphrase normalization, byte-bearing wrappers, fixed fixture constructors, KAT requests/results/adapters, and the integrated KAT executor. The synthetic classifier moves with its request/result/classification declarations. No production forwarding wrapper, substitute executable class, reflection bridge, runtime flag, fallback parser, or test-artifact dependency is added.

Most `SkaldVaultV1*` sources are inert policy boundaries and are not relocation targets. In particular, `SkaldVaultV1StillDisabledProviderFacade`, parser/writer disabled scaffolds and admission models, authorization/readiness/session/path/redaction models, provider identity policy, Monero rejection placeholders, and `SkaldVaultV1ApprovedInputDomain` remain production compatibility evidence. Existing test-source storage contracts, fixed vectors, and atomicity/crash simulators remain test evidence.

## Material-free KDF compatibility split

The new [SkaldVaultV1PrototypeKdfCompatibility.kt](../composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1PrototypeKdfCompatibility.kt) retains only the existing enum and parameter data class plus constant defaults. It contains no input bytes, keys, provider access, cryptographic method, fixture constructor, mutable state, or I/O.

| Retained declaration/property | Preserved P0 value or behavior |
| --- | --- |
| `SkaldVaultV1Argon2idType` | Existing order and labels: `Argon2id`, `Argon2i`, `Argon2d`; the alternatives remain rejection-policy evidence. |
| `SkaldVaultV1Argon2idParameters` | Same constructor field order, names, data-class behavior, and default algorithm `Argon2id`. |
| `ARGON2_VERSION_19` | `19` |
| `MEMORY_KIB` | `65_536` |
| `ITERATIONS` | `3` |
| `PARALLELISM` | `1` |
| `OUTPUT_BYTES` | `64` |

The constants belong to `SkaldVaultV1PrototypeKdfCompatibility`. The two version comparisons in `Argon2idCalibrationPolicy` now reference that object rather than the moved derivation object. Candidate selection, rejection, floors, no-downgrade policy, reporting, and readiness remain unchanged. `ProductionProviderAcceptanceContract` continues using the same enum without modification. The executable root derivation, its result/rejection types, root-material wrapper, and expect/actual primitive path are test-only.

The retained 64-byte output is a historical policy compatibility value. It is not a canonical vault root, a canonical KDF output, or approval of production KDF parameters. Canonical v1 still calls for a 32-byte Argon2id-derived KEK wrapping an independent random 32-byte vault root, HKDF-separated roots/per-record keys, XChaCha20-Poly1305, an encrypted authoritative manifest, and wrapped-root AEAD associated data without a separately persisted header HMAC.

## Test compilation ownership

The [Gradle source-set wiring](../composeApp/build.gradle.kts) shares one maintained physical copy of each helper:

| Compilation | Common helper input | Platform actual input |
| --- | --- | --- |
| Desktop tests | `commonTest` includes `src/prototypeTestSupport/kotlin` | Four moved files in `src/desktopTest/kotlin` |
| Android debug/release unit tests | `commonTest` includes the same common helper directory | `androidUnitTest` includes `src/androidPrototypeTestSupport/kotlin` |
| Android instrumented tests | `androidInstrumentedTest` depends on the `prototypeTestSupport` source set, whose default directory is the same common helper directory | `androidInstrumentedTest` includes the same Android actual directory |
| Desktop and Android production | Neither helper directory is an input | No moved actual file is an input |

The build explicitly applies the pinned Kotlin default hierarchy before adding the device-test support edge, preserving the existing target hierarchy. The custom support source set contains helper/fixture declarations only. It does not pull the common `@Test` suite into the device APK. Unit and instrumented compilations each receive one applicable Android actual implementation from a single maintained directory. The source-set paths are implementation details whose realized task inputs must be verified; a directory named “test” is not proof of isolation.

The pinned Bouncy Castle and Tink declarations remain in their prior scopes because unchanged inert production dependency probes still reference library classes. Library presence is distinct from first-party P0 presence. No library version, plugin, runtime dependency, app permission, or package identity changes in this phase.

## Preserved semantics and explicit exceptions

The historical P0 formats retain their exact magic/framing, fixture/vector bytes, header/HKDF/HMAC outputs, Argon2id behavior, NFC passphrase normalization, record AEAD behavior, ordering and rejection contracts. The unsupported prototypes do not gain a persisted-byte migration promise or a claim that they are safe for real secrets. Their deficiencies are preserved as historical test contracts, not silently repaired into canonical v1.

The intentional semantic-disposition exceptions are:

1. The KDF enum and parameter declarations move to the inert production compatibility file; their default references point to equal-valued constants. The executable derivation body stays unchanged in test support.
2. The two calibration-policy version references point to the inert constant with the same value.
3. The classifier source-set label changes from `CommonMainInMemoryParser` / `COMMON_MAIN_IN_MEMORY_PARSER` to `PrototypeTestSupportInMemoryParser` / `PROTOTYPE_TEST_SUPPORT_IN_MEMORY_PARSER`, including redacted displays and corresponding tests.
4. Current classifier/test-audit placement claims become `commonMainInMemoryParserPresent=false` and `parserCompiledIntoProductionArtifacts=false`. Marker syntax and exact matching, owned-input behavior, classifications, rejection reasons, payload-free results, and all operational authorization values remain unchanged.
5. Source-path/audit assertions now establish test-only placement and production absence rather than an allowlisted production location.

Unchanged behavior includes app startup and the Wallet/Settings UI, capability-snapshot ownership, identifier admission, disabled storage adapters, provider selection, and every vault/wallet/network gate. `DisabledVaultCryptoProvider` remains the only production selection with `productionProviderSelectable=false`. No create/open/unlock, live session, key/nonce generation, canonical parsing, storage success, persistence, sync, signing, relay, or mainnet capability is introduced.

## Verification contract and evidence

The phase closeout report under ignored `.skald-local/reports/vault-01-prototype-quarantine` records actual commands, input hashes, results, independent findings, and Q01–Q12 acceptance outcomes. This engineering record describes the implementation and its required proof; it does not replace fresh test, device, or package results with a documentation assertion.

Required evidence has three distinct layers:

1. **Source graph:** inspect realized desktop/Android production and test compilation inputs, prove neither helper directory enters production, and compare the production source corpus with the actual graph. Preserve source-corpus caching and enumerate the new helper roots in test coverage.
2. **Behavior and guards:** run unchanged P0 round-trip/malformed-input, vector, passphrase/KDF/AEAD/KAT, storage-contract/simulator, synthetic-classifier and non-overlap cases through their new test locations. Check KDF compatibility and unchanged admission/gates. Preserve all 131 central security case purposes and the corrected confinement scanner. In-memory mutations must reject helper/fixture/parser references, operation/material additions to the compatibility model, and test-support wiring into production; mutations must demonstrably change real inputs.
3. **Compiled and packaged ownership:** clean affected generated outputs through project build tasks; derive exact forbidden first-party symbols, including nested types and Kotlin file facades; inspect desktop and Android debug/release production classes, target APK DEX, and Debian runtime JARs. The moved executable families must be absent. Their presence in the separate Android test APK/test classpath is expected positive evidence. Record artifact hashes against final source/configuration inputs, with retained inert compatibility types as exact exceptions.

The required build/test gates include `:composeApp:allTests`, `:composeApp:compileKotlinDesktop`, `:composeApp:assembleDebug`, `:composeApp:packageDeb`, Android release/unit compilation and Android instrumented compilation/packaging. Connected verification executes the full existing instrumented suite on the currently observed authorized project device, explicitly targeted. The baseline discovery is 13 classes and 36 `@Test` declarations; execution counts must come from fresh result files and must not be inferred from declaration counts. Compilation, a filtered subset, or an empty device run does not establish this gate.

Package absence establishes a reduced application surface, not completed vault security. The source guards protect the reviewed corpus and supported mutation forms; they are not a complete Kotlin security sandbox. Pinned general-purpose library vocabulary and safe historical policy labels are not evidence that an executable P0 family remains shipped. Conversely, absence of app callers alone is insufficient while an executable first-party class remains packaged.

## Remaining canonical work

After review and Git closeout of this prerequisite, separately settle the exact canonical binary layout and provider contract before implementation depends on them: magic/field IDs/framing, non-circular header AAD bytes, normalization/UTF-8 policy, record-envelope representation/overhead, and opaque provider operations/ownership. Final device KDF parameters are still undecided. Later provider/KAT acceptance, canonical codecs/vectors, lock/session, storage/atomicity, repositories, backup/export, and persistence each require their own scope.

Vault cardinality, lock/background behavior, backup credentials/recovery, live secure-input policy, and Monero engine/transport/LWS integration remain outside this phase. Quarantine does not select those answers or authorize the next implementation step.
