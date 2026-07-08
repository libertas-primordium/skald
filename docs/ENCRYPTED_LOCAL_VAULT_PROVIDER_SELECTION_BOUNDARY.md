# Encrypted Local Vault Provider Selection Boundary

## Status

Skald Vault now has a disabled provider-selection and registry boundary for the future app-controlled encrypted local vault crypto provider.

This is selection policy only. It does not implement provider selectability, final production calibration approval, key generation, Tink keyset creation or storage, raw key material persistence, actual path construction, absolute path construction, path joining, real path containment checks, directory creation, platform root selection/resolution, Linux custom-root resolution, Settings UI, settings persistence, OS keyring integration, password-manager integration, symlink checks, permission checks, durability probes, storage safety preflight filesystem checks, disabled storage service success, vault persistence readiness approval, lock/session lifecycle approval, passphrase policy approval, clear/wipe strategy approval, migration/corruption approval, actual clearing, actual zeroization, JVM zeroization proof, warning-only encrypted vault persistence, file-backed vault container read/write, manifest file/storage read/write, storage index read/write, record read/write/list/delete/quarantine/recovery, storage success, passphrase/PIN/biometric unlock UI, secure secret storage success, secure metadata persistence success, production sync, backend clients, signing, broadcasting, Tor transport, Nostr parsing, public endpoints, Skald-operated infrastructure, or mainnet. The passphrase policy validator, explicit-parameter Argon2id root derivation, Argon2id calibration policy/candidate-selection/memory-failure/no-downgrade model, canonical header serializer, HKDF-SHA-256 expansion, HMAC-SHA-256 verification, strict AAD serialization, Tink record AEAD construction, in-memory container parser/writer, in-memory manifest parser/writer, local manifest-relative stale-record decision policy, shared-test in-memory storage atomicity/crash simulator, storage namespace/path validation and relative segment encoding policy, rootless logical storage layout plan, path-containment planner for reviewed root tokens plus safe relative segment lists, Linux custom-root static validation policy, Linux root-resolution evidence policy, platform root resolver evidence boundary, platform path-construction evidence boundary, storage safety preflight evidence boundary, disabled vault storage service facade, vault persistence readiness gate, lock/session lifecycle boundary, redaction/leakage boundary, passphrase policy boundary, clear/wipe strategy boundary, migration/corruption boundary, integrated provider KAT harness, metadata-only still-disabled provider facade, and model-only storage boundary/root/settings/path/symlink/permission/durability/fail-closed/warning-only rejection/atomicity/secure-storage contracts exist only as still-disabled building blocks/evidence and are not provider-selectable.

Manual Android calibration evidence capture is documented in [`ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md). Android compatibility and entropy policy is documented in [`ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md). Runtime randomness/provider checks are documented in [`ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md`](ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md). The Tink raw-key feasibility probes are documented in [`ENCRYPTED_LOCAL_VAULT_TINK_RAW_KEY_FEASIBILITY_PROBE.md`](ENCRYPTED_LOCAL_VAULT_TINK_RAW_KEY_FEASIBILITY_PROBE.md). The header commitment, canonical header encoding, key-separation label, and strict AAD construction contract is documented in [`ENCRYPTED_LOCAL_VAULT_HEADER_COMMITMENT_AAD_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_HEADER_COMMITMENT_AAD_CONTRACT.md). The canonical header/HKDF/HMAC non-secret vector contract is documented in [`ENCRYPTED_LOCAL_VAULT_CANONICAL_HEADER_HKDF_HMAC_VECTORS.md`](ENCRYPTED_LOCAL_VAULT_CANONICAL_HEADER_HKDF_HMAC_VECTORS.md). The provider-level KAT strategy for randomized AEAD and the stale-record/rollback manifest contract are documented in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md). The vault container/manifest/storage/stale-record/atomicity/crash-recovery/secure-storage contract is documented in [`ENCRYPTED_LOCAL_VAULT_CONTAINER_MANIFEST_STORAGE_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_CONTAINER_MANIFEST_STORAGE_CONTRACT.md). The v1 production-provider acceptance contract is documented in [`ENCRYPTED_LOCAL_VAULT_PRODUCTION_PROVIDER_ACCEPTANCE_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PRODUCTION_PROVIDER_ACCEPTANCE_CONTRACT.md). Current Pixel 10 Pro XL / Android 16 evidence remains high-end debug/instrumented timing evidence only and does not prove all-device performance. Low-end and mid-range model testing are no longer hard blockers for compatibility planning; supported Android baseline, runtime provider/primitive/randomness checks, and fail-closed vault-creation behavior are the compatibility gate.

The only runtime provider selected by this branch is:

```text
DisabledVaultCryptoProvider
```

The disabled provider rejects every operation and performs no crypto.

Test-source-only provider-selection validation is recorded in [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_SELECTION_VALIDATION.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_SELECTION_VALIDATION.md). That harness can explicitly select the test-only executable provider only from test source and only for public-KAT validation. It does not modify `VaultCryptoProviderSelectionRegistry.select()`, does not persist a provider choice, does not add provider-selection UI, does not add a production registry/factory/dispatcher/executor target, and does not make any provider production-selectable. Production selection still resolves only to `DisabledVaultCryptoProvider`, and `productionProviderSelectable` remains false.

The still-disabled provider candidate packaging boundary is additional evidence only. It describes future Tink JVM, Bouncy Castle JVM, Android Keystore wrapper, platform OS CSPRNG, test-only deterministic, unknown, and unsupported candidate packaging plus dependency categories, source-set placement constraints, review gates, and promotion blockers. It does not add dependencies, instantiate provider code, run provider operations or KATs, run randomness checks, run KDF/HKDF/HMAC/AEAD, or change this registry. Provider selection still selects only `DisabledVaultCryptoProvider`, `productionProviderSelectable` remains false, and no candidate is executable or production-authorized.

The provider dependency build boundary is also evidence only. No new dependency is added in this pass because Tink and Bouncy Castle are already declared in the platform source sets for build/probe evidence, and the current provider decision is a split stack rather than a single newly activated dependency. The boundary records those declarations as build-only, non-active, non-imported, non-executable, and non-selectable. It does not add provider implementation or factory code, instantiate Tink/Bouncy/JCA/Android provider APIs, run KATs or crypto, or change this registry.

The provider selection promotion blocker boundary is additional evidence only. It describes future promotion stages between candidate description, dependency/build evidence, dependency review, source-set review, implementation, factory, registry, test selectability, production selectability, provider operation authorization, provider KAT approval, runtime randomness, KDF/AEAD/key-wrapping support, creation/unlock/persistence integration, release validation, and mainnet approval. Every promotion path beyond candidate description and build-only evidence remains blocked. It does not add dependencies, implement a provider, add a factory, enable a registry, instantiate provider code, run provider operations or KATs, run randomness checks, run KDF/HKDF/HMAC/AEAD, make any provider selectable, set `productionProviderSelectable=true`, enable vault creation/unlock/persistence, or enable mainnet. Warning-only evidence, user consent, and test-only evidence cannot authorize promotion.

The provider interface contract audit is additional evidence only. It audits provider-facing contracts for provider-neutral common boundaries, redaction, authorization requirements, promotion blockers, source-set safety, and rejection of runtime material. It does not add dependencies, implement a provider, add a factory, enable a registry, instantiate provider code, accept secrets or byte material, accept provider handles or crypto objects, run provider operations or KATs, run randomness checks, run KDF/HKDF/HMAC/AEAD, make any provider selectable, set `productionProviderSelectable=true`, enable vault creation/unlock/persistence, or enable mainnet.

The non-selectable provider skeleton boundary is also evidence only. It models future provider skeleton identity, source-set placement, dependency visibility, disabled operation surfaces, diagnostics/redaction, acceptance gates, promotion blockers, and readiness-matrix interaction without implementing `VaultCryptoProvider`, adding a factory, enabling a registry, instantiating provider code, running provider operations or KATs, using randomness, running KDF/HKDF/HMAC/AEAD, wrapping keys, changing this registry, or making any provider selectable.

The provider registry isolation guard is also evidence only. It models the current provider-selection registry as isolated to `DisabledVaultCryptoProvider` and records that non-selectable skeletons, candidate-packaging evidence, dependency/build evidence, interface-audit evidence, promotion-blocker evidence, authorization/readiness matrix evidence, provider-operation authorization evidence, runtime-randomness evidence, KDF-calibration evidence, secure-storage evidence, creation/unlock authorization evidence, warning-only evidence, user-consent evidence, test-only evidence, and release/mainnet evidence cannot become registry entries or provider-promotion authority. It does not add dependencies, implement a provider, add a factory, enable a non-disabled registry, instantiate provider code, run provider operations or KATs, run randomness checks, run KDF/HKDF/HMAC/AEAD, make any provider selectable, set `productionProviderSelectable=true`, enable vault creation/unlock/persistence, or enable mainnet.

The provider factory isolation boundary is also evidence only. It models the current provider-factory surface as absent for non-disabled providers and records that no non-disabled provider factory or constructor is reachable from provider selection, registry isolation, non-selectable skeleton evidence, candidate-packaging evidence, dependency/build evidence, interface-audit evidence, promotion-blocker evidence, authorization/readiness matrix evidence, warning-only evidence, user-consent evidence, test-only evidence, or release/mainnet evidence. It does not add dependencies, implement a provider, add a provider factory, enable a non-disabled registry, instantiate provider code, expose provider handles or crypto objects, accept byte material, run provider operations or KATs, run randomness checks, run KDF/HKDF/HMAC/AEAD, make any provider selectable, set `productionProviderSelectable=true`, enable vault creation/unlock/persistence, or enable mainnet. Future factory introduction requires an explicit later branch and review.

The provider operation dispatch isolation boundary is also evidence only. It models the current provider-operation dispatch surface as absent for non-disabled providers and records that no dispatcher is reachable from provider selection, registry isolation, factory isolation, non-selectable skeleton evidence, candidate-packaging evidence, dependency/build evidence, interface-audit evidence, promotion-blocker evidence, authorization/readiness matrix evidence, creation authorization, unlock authorization, secure-storage authorization, or persistence readiness. It does not add dependencies, implement a provider, add a provider factory, add a provider dispatcher, enable a non-disabled registry, instantiate provider code, expose provider handles or crypto objects, accept byte material, run provider operations or KATs, run randomness checks, run KDF/HKDF/HMAC/AEAD, compute header commitments, encrypt/decrypt records, wrap/unwrap keys, make any provider selectable, set `productionProviderSelectable=true`, enable vault creation/unlock/persistence, or enable mainnet. Future dispatcher introduction requires an explicit later branch and review.

The provider KAT execution isolation boundary is also evidence only. It models the current provider KAT executor surface as absent for non-disabled providers and records that no KAT executor is reachable from provider selection, registry isolation, factory isolation, dispatch isolation, non-selectable skeleton evidence, candidate-packaging evidence, dependency/build evidence, interface-audit evidence, promotion-blocker evidence, authorization/readiness matrix evidence, creation authorization, unlock authorization, secure-storage authorization, persistence readiness, public non-wallet vector documentation, canonical header/HKDF/HMAC vector documentation, or test-provider KAT harness concepts. It does not add dependencies, implement a provider, add a provider factory, add a provider dispatcher, add a provider KAT executor, enable a non-disabled registry, instantiate provider code, expose provider handles or crypto objects, accept byte material, run provider operations or KATs, run randomness checks, run KDF/HKDF/HMAC/AEAD, compute header commitments, encrypt/decrypt records, wrap/unwrap keys, make any provider selectable, set `productionProviderSelectable=true`, enable vault creation/unlock/persistence, or enable mainnet. Future KAT executor introduction requires an explicit later branch and review.

## Source Location

Production-safe provider-selection models and registry:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/VaultCryptoProviderSelection.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/AndroidVaultCompatibilityPolicy.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/RuntimeRandomnessProviderChecks.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderCandidatePackagingBoundary.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderDependencyBuildBoundary.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderSelectionPromotionBlockers.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderInterfaceContractAudit.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1NonSelectableProviderSkeletonBoundary.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderRegistryIsolationGuard.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderFactoryIsolationBoundary.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderOperationDispatchIsolationBoundary.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderKatExecutionIsolationBoundary.kt
```

Disabled provider boundary:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/VaultCryptoProvider.kt
```

Metadata-only still-disabled provider facade:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1StillDisabledProviderFacade.kt
```

Disabled storage service facade:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1DisabledStorageServiceFacade.kt
```

The disabled storage service facade is not referenced by the provider-selection registry. It models future storage operations only as disabled/rejected evidence and cannot make a provider selectable.

Vault persistence readiness gate:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1PersistenceReadinessGate.kt
```

The vault persistence readiness gate composes existing provider, root, path, storage safety, disabled storage service, secure storage, secure metadata, migration/corruption, BDK-persistence-bypass, managed-infrastructure, and mainnet evidence into a single blocked/fail-closed readiness decision. It does not run filesystem checks, construct real or absolute paths, use `File`/`Path`/filesystem APIs, create directories, read files, write files, persist Settings, read/write manifests, read/write storage indexes, read/write/list/delete/quarantine/recover records, perform atomic writes, perform crash recovery, map real storage failures, enable vault persistence, approve production provider use, approve mainnet, or make a provider selectable.

Vault lock/session lifecycle boundary:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1LockSessionLifecycleBoundary.kt
```

The lock/session lifecycle boundary composes typed readiness, provider, disabled storage, secure storage, secure metadata, and lifecycle-event evidence into blocked/fail-closed session decisions. It models locked, unlock-requested, unlock-blocked, unlock-unavailable, active-session-unavailable, active-session-modeled-but-unusable, session-expired, background/close/error/provider-change/storage-change/platform-security-change/mainnet-block, and forced-locked states. It does not accept or store passphrases or PINs, derive keys, hold decrypted keys, implement memory wipe/zeroization, implement biometrics, implement Android Keystore, implement OS keyrings, implement password managers, add unlock UI, persist session state, run filesystem checks, construct paths, create/read/write files, persist Settings, enable vault unlock, enable vault persistence, approve production provider use, approve mainnet, or make a provider selectable.

Vault redaction/leakage boundary:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1RedactionLeakageBoundary.kt
```

The redaction/leakage boundary composes typed value-kind, output-target, scope, and redacted-token evidence into fail-closed safe-output decisions. It models future forbidden value classes, sensitive metadata summaries, operational metadata summaries, public non-wallet vector scope, public policy identifiers, enum/capability evidence, aggregate statistics, source-guard material classes, and future output targets. It does not accept raw secrets, passphrases, key material, byte arrays, raw paths, payloads, wallet labels, transaction notes, credentials, stack traces, provider handles, storage handles, or backend handles. It does not hash or fingerprint secrets, log, add crash reporting, add analytics, add support export, add runtime diagnostics, persist diagnostic output, display secrets, implement unlock UI, implement provider execution, implement storage, enable vault unlock, enable vault persistence, approve production provider use, approve mainnet, or make a provider selectable. Public non-wallet cryptographic vectors remain scoped to docs/tests/KAT/source-guard evidence only; wallet, UTXO, sync, and production source paths still reject hardcoded address, txid, secret, and wallet-material fixtures.

Vault passphrase policy boundary:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1PassphrasePolicyBoundary.kt
```

The passphrase policy boundary composes typed passphrase policy requests, policy category evidence, redaction/leakage evidence, lock/session evidence, persistence readiness evidence, provider evidence, dependency evidence, secure-storage evidence, and secure-metadata evidence into blocked/fail-closed policy decisions. It models future passphrase input policy, normalization/encoding policy identifiers, retry/throttle/lockout requirements, memory lifetime and clear/wipe requirements, redaction requirements, biometric/Android-Keystore future-only status, OS-keyring/password-manager passphrase-storage rejection, and unlock prerequisites. It does not accept actual passphrases, PINs, biometrics, password examples, mnemonic examples, passphrase bytes, hashes, fingerprints, key material, raw paths, Settings values, provider handles, storage handles, or database handles. It does not store passphrases, normalize or encode real passphrases, hash or fingerprint passphrases, run Argon2id/KDF/HKDF/HMAC/AEAD, implement retry/throttle/lockout runtime behavior, implement passphrase UI, implement unlock UI, implement wipe/zeroization, implement Android Keystore, implement OS keyrings, implement password managers, enable vault unlock, enable vault persistence, approve production provider use, approve mainnet, or make a provider selectable.

Vault clear/wipe strategy boundary:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ClearWipeStrategyBoundary.kt
```

The clear/wipe strategy boundary composes typed clear/wipe policy requests, value/event evidence, passphrase policy evidence, redaction/leakage evidence, lock/session evidence, persistence readiness evidence, disabled provider evidence, disabled storage evidence, dependency evidence, secure-storage evidence, and secure-metadata evidence into blocked/model-only policy decisions. It models future sensitive value classes, lifecycle triggers, clear/wipe requirements, strategy classes, provider/storage/session cleanup requirements, and the limitation that JVM zeroization cannot be proven by this model. It does not accept actual passphrases, PINs, biometrics, mnemonic text, seed bytes, private-key bytes, raw KDF input/output, raw AEAD keys, entropy bytes, decrypted records, byte arrays, char arrays, mutable buffers, key material, raw paths, Settings values, provider handles, storage handles, or database handles. It does not clear real memory, zero real memory, prove JVM zeroization, use native memory, implement provider clear calls, implement storage clear calls, implement session invalidation, implement passphrase UI, implement unlock UI, implement Android Keystore, implement OS keyrings, implement password managers, enable vault unlock, enable vault persistence, approve production provider use, approve mainnet, or make a provider selectable.

Vault migration/corruption boundary:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1MigrationCorruptionBoundary.kt
```

The migration/corruption boundary composes typed policy requests and typed evidence from disabled storage, storage safety, planned artifact-location, readiness, redaction/leakage, clear/wipe, lock/session, provider-selection, disabled provider, secure-storage, and secure-metadata boundaries into blocked/model-only policy decisions. It models future container/version/manifest/storage-index/record evidence categories, failure classes, required fail-closed actions, stale-record and rollback-suspicion review, partial-write handling, quarantine-required evidence, manual-review evidence, and redacted failure reporting. It does not accept raw persisted bytes, ciphertext, plaintext, nonce/tag/header-commitment bytes, key material, passphrases, raw paths, Settings values, provider handles, storage handles, labels, notes, credentials, wallet database bytes, or BDK persistence handles. It does not parse real storage, read files, write files, run migration, run migration dry-run, repair storage, quarantine records, recover records, verify AEAD tags, decrypt records, verify real header commitments, rewrite manifests or storage indexes, prove rollback resistance, prove crash recovery, enable vault unlock, enable vault persistence, approve production provider use, approve mainnet, or make a provider selectable.

Selection tests:

```text
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultCryptoProviderSelectionTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultProviderSelectionPromotionBlockersTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultProviderInterfaceContractAuditTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultNonSelectableProviderSkeletonBoundaryTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultProviderRegistryIsolationGuardTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultProviderFactoryIsolationBoundaryTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultProviderOperationDispatchIsolationBoundaryTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultProviderKatExecutionIsolationBoundaryTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/RuntimeRandomnessProviderPolicyTest.kt
```

Source guards:

```text
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/ProductionBackendAdapterSourceGuardTest.kt
```

## Current Selection Result

`VaultCryptoProviderSelectionRegistry.select(...)` always returns `DisabledVaultCryptoProvider` as the selected runtime provider. A request for any future candidate still fails closed to the disabled provider.

The registry models candidate state and evidence, but it does not instantiate a Tink, Bouncy Castle, libsodium, JCA/JCE, platform, or storage-backed production provider.

| Candidate | Selection status | Reason |
| --- | --- | --- |
| Disabled provider | Selected for runtime fail-closed mode only | No crypto operation is executable. |
| Tink plus Bouncy Castle split stack | Blocked future candidate | Dependency-level KATs, Android/desktop runtime evidence, dependency review, and test-provider KAT harness evidence exist, but no production provider exists and storage gates are closed. |
| Lazysodium Java/Android | Rejected for current vault branch | Android packaging failed with duplicate JNA classes in the prior comparison. |
| IonSpin KMP libsodium | Deferred | Metadata/POM inspection only; package/runtime/KAT behavior remains unverified. |

No candidate is production-selectable.

## Provider Registry Isolation Guard

`SkaldVaultV1ProviderRegistryIsolationGuardPolicy` is a still-disabled, model-only guard for the current registry boundary. It proves the active selection path remains disabled-provider-only and that current evidence models cannot be promoted into registry entries.

The guard covers the active provider-selection registry, disabled provider selection, non-selectable skeleton exclusion, candidate-packaging exclusion, dependency/build exclusion, interface-audit exclusion, promotion-blocker exclusion, authorization/readiness matrix exclusion, provider-operation authorization exclusion, runtime-randomness authorization exclusion, KDF-calibration authorization exclusion, secure-storage authorization exclusion, creation/unlock authorization exclusion, test-only evidence exclusion, warning-only evidence exclusion, user-consent exclusion, and release/mainnet future-review requirements.

It rejects risks where the registry would reference the skeleton or candidate families, treat evidence as registry entries, reference a test-only provider from production selection, set `productionProviderSelectable=true`, set `providerSelectable=true`, contain a non-disabled provider id, create provider instances, expose provider handles or crypto objects, import platform crypto APIs, call provider operations, run KATs, reach randomness/KDF/AEAD/HKDF/HMAC, enable vault creation/unlock/persistence, or enable mainnet.

The guard has no provider instance, factory, registry object, handle, crypto object, byte material, path/root value, storage identifier, or secret-bearing input/output. Its diagnostics are limited to policy ids, registry topic names, risk names, required-property names, blocker classes, and redacted tokens.

## Provider Factory Isolation Boundary

`SkaldVaultV1ProviderFactoryIsolationPolicy` is a still-disabled, model-only boundary for the absent non-disabled provider-factory surface. It proves that current selection, registry, skeleton, candidate, dependency/build, interface-audit, promotion-blocker, authorization/readiness matrix, provider-operation, runtime-randomness, KDF, secure-storage, creation/unlock, warning-only, user-consent, test-only, and release/mainnet evidence cannot construct, expose, return, register, or select a provider runtime object.

The boundary covers active factory surface, disabled construction surface, non-disabled provider construction exclusion, skeleton and candidate construction exclusion, dependency/build construction exclusion, packaging/audit/promotion/matrix construction exclusion, registry construction exclusion, provider-operation/randomness/KDF/secure-storage/creation/unlock construction exclusion, test-only factory evidence exclusion, warning-only and user-consent exclusion, and release/mainnet future-review requirements.

It rejects risks where a factory would create non-disabled, skeleton, or candidate providers; treat evidence models as construction authority; accept provider handles, crypto objects, byte material, or platform crypto APIs; import Tink/Bouncy/JCA APIs; call randomness, KATs, provider operations, KDF/AEAD/HKDF/HMAC, key wrapping, vault creation/unlock/persistence, or mainnet paths. It has no provider instance, factory object, registry object, handle, crypto object, byte material, path/root value, storage identifier, or secret-bearing input/output. Its diagnostics are limited to policy ids, factory topic names, risk names, required-property names, blocker classes, and redacted tokens.

## Provider Operation Dispatch Isolation Boundary

`SkaldVaultV1ProviderOperationDispatchIsolationPolicy` is a still-disabled, model-only boundary for the absent non-disabled provider-operation dispatcher surface. It proves that current selection, registry isolation, factory isolation, skeleton, candidate, dependency/build, interface-audit, promotion-blocker, authorization/readiness matrix, provider-operation, runtime-randomness, KDF, secure-storage, creation/unlock, persistence-readiness, warning-only, user-consent, test-only, and release/mainnet evidence cannot route requests into executable provider code.

The boundary covers provider operation dispatch surface, disabled provider dispatch behavior, non-disabled provider dispatch exclusion, factory and registry dispatch exclusion, skeleton and candidate dispatch exclusion, dependency/build dispatch exclusion, packaging/audit/promotion/matrix dispatch exclusion, provider-operation/randomness/KDF/secure-storage/creation/unlock/persistence dispatch exclusion, test-only dispatch exclusion, warning-only and user-consent exclusion, and release/mainnet future-review requirements.

It rejects risks where dispatch would invoke non-disabled, skeleton, or candidate providers; invoke a provider factory or registry; treat evidence models as executable operations; accept provider handles, crypto objects, byte material, or platform crypto APIs; import Tink/Bouncy/JCA APIs; call randomness, KATs, provider operations, KDF/AEAD/HKDF/HMAC, header commitment, record encryption/decryption, key wrapping, vault creation/unlock/persistence, or mainnet paths. It has no provider instance, dispatcher object, factory object, registry object, handle, crypto object, byte material, path/root value, storage identifier, or secret-bearing input/output. Its diagnostics are limited to policy ids, dispatch topic names, risk names, required-property names, blocker classes, and redacted tokens.

## Provider KAT Execution Isolation Boundary

`SkaldVaultV1ProviderKatExecutionIsolationPolicy` is a still-disabled, model-only boundary for the absent non-disabled provider KAT executor surface. It proves that current selection, registry isolation, factory isolation, dispatch isolation, skeleton, candidate, dependency/build, interface-audit, promotion-blocker, authorization/readiness matrix, provider-operation, runtime-randomness, KDF, secure-storage, creation/unlock, persistence-readiness, warning-only, user-consent, test-only, public-vector, and release/mainnet evidence cannot run KATs or authorize executable provider code.

The boundary covers provider KAT execution surface, provider KAT contract evidence, test-provider KAT harness evidence, public non-wallet vector documentation evidence, canonical header/HKDF/HMAC vector documentation evidence, provider self-test and runtime-check surfaces, disabled provider KAT behavior, non-disabled provider/factory/registry/dispatch/skeleton/candidate KAT exclusions, dependency/build KAT exclusion, packaging/audit/promotion/matrix KAT exclusion, provider-operation/randomness/KDF/secure-storage/creation/unlock/persistence KAT exclusion, test-only KAT evidence exclusion, warning-only and user-consent exclusion, and release/mainnet future-review requirements.

It rejects risks where KAT/vector/self-test evidence would invoke non-disabled, skeleton, or candidate providers; invoke a provider factory, registry, or dispatcher; treat evidence models as executable providers; treat public vector docs as production approval; treat canonical vector docs as runtime KDF/HKDF/HMAC execution; accept provider handles, crypto objects, byte material, or platform crypto APIs; import Tink/Bouncy/JCA APIs; call randomness, provider operations, KDF/AEAD/HKDF/HMAC, header commitment, record encryption/decryption, key wrapping, provider selection, `productionProviderSelectable=true`, vault creation/unlock/persistence, or mainnet paths. It has no provider instance, KAT executor object, dispatcher object, factory object, registry object, handle, crypto object, byte material, path/root value, storage identifier, or secret-bearing input/output. Its diagnostics are limited to policy ids, KAT topic names, risk names, required-property names, blocker classes, and redacted tokens.

## Evidence Model

The registry separates evidence into typed Skald-owned categories:

- dependency evidence,
- provider KAT evidence,
- platform runtime coverage evidence,
- Android compatibility and entropy policy evidence,
- runtime randomness/provider check evidence,
- Argon2id parameter policy evidence,
- secure storage and secure metadata readiness evidence,
- production approval gates,
- selection blockers.

The model does not expose provider-specific types. Public common models do not carry Tink, Bouncy Castle, JCA/JCE, libsodium, BDK, platform, file, settings, network, or process types.

## Dependency Evidence Treatment

Dependency-level KATs remain necessary evidence, not sufficient selection evidence.

Current Tink plus Bouncy Castle evidence:

- Desktop JVM Argon2id KAT passed against RFC 9106 section 5.3.
- Desktop JVM XChaCha20-Poly1305 KAT passed against the XChaCha draft appendix A.1.
- Android runtime Argon2id KAT passed on Pixel 10 Pro XL / Android 16.
- Android runtime XChaCha20-Poly1305 KAT passed on Pixel 10 Pro XL / Android 16.
- Android APK and Linux `.deb` package probes passed in prior branches.
- Candidate-level dependency/license/keyset/split-provider review is documented.

This evidence does not select a provider for production because the selectable production provider implementation is absent. Still-disabled integrated provider KAT execution is separate evidence and does not approve selection.

## Test-Provider Evidence Treatment

The test-only provider KAT harness proves that the Skald-owned `VaultCryptoProvider` request/result path can carry:

- Argon2id public KDF vectors,
- XChaCha20-Poly1305 public AEAD vectors,
- wrong associated-data failure,
- ciphertext/tag tampering failure,
- wrong-key failure,
- unsupported algorithm rejection,
- production nonce-bypass rejection,
- redacted diagnostics.

That evidence is test-scope only. It does not satisfy production provider selection because the harness is not a production provider, does not store keys, does not write vault records, and does not exercise production storage or lock/session lifecycle behavior.

## Still-Disabled Integrated KAT Evidence

`SkaldVaultV1StillDisabledProviderKatHarness` now composes the passphrase, Argon2id, HKDF, canonical header, HMAC header commitment, strict AAD, and Tink record AEAD building blocks with fixed non-secret fixtures. Its tests assert deterministic vector stages, randomized AEAD behavioral checks, and the required verification order: header commitment verification must complete before record AEAD use, and failed header commitment exits before record AEAD. This evidence is not selectable and exposes no vault creation, storage, manifest, secure-storage, wallet, or persistence API.

## Still-Disabled Provider Facade

`SkaldVaultV1StillDisabledProviderFacade` exists as a future provider-boundary status object only. It reports the pinned v1 suite id, selected KDF/AEAD/randomness labels, passphrase/key-expansion/key-separation/header-commitment/AAD/record-format policy ids, provider-level KAT policy ids, stale-record manifest policy id, calibration policy id, disabled reasons, remaining gates, and building-block evidence.

The facade is not wired into `VaultCryptoProviderSelectionRegistry`, is not a `VaultCryptoProvider`, and cannot be selected by debug flags, test flags, KAT success, calibration evidence, or readiness evidence. Its operation-like entry points return typed disabled results only. They do not accept passphrases, root material, record keys, plaintext, ciphertext, AAD, vault ids, storage paths, or wallet inputs, and they do not execute crypto, randomness, vault creation, unlock, storage, manifest, secure-storage, wallet, sync, backend, or persistence behavior.

## Production Approval Gates

A future executable provider cannot become selectable until every required gate is satisfied:

| Gate | Current state |
| --- | --- |
| Production provider acceptance contract satisfied | Blocked. The contract is modeled but incomplete for production implementation and persistence. |
| Production provider implementation exists | Blocked. |
| Still-disabled provider facade exists | Evidence exists as metadata/status only. It is not a selectable provider and exposes no usable vault operations. |
| Still-disabled provider-level KATs passed | Evidence exists through the still-disabled integrated harness, but this is not selection approval. |
| Provider-level KAT strategy approved | Still-disabled harness evidence exists for deterministic vectors plus randomized AEAD behavioral checks. |
| Randomized AEAD behavioral KATs passed | Still-disabled harness evidence exists without fixed ciphertext hex. |
| Integrated verification-order KATs passed | Still-disabled harness evidence proves header commitment verification happens before record AEAD use. |
| Android and desktop runtime coverage exists | Candidate evidence exists for dependency/test-provider paths, but not a production provider. |
| Runtime randomness/provider checks pass | Test-only Android/Linux availability probes are modeled; they do not prove entropy quality or approve production randomness. |
| Argon2id parameters final for the platform | Blocked. The shared floor/candidate-selection/no-downgrade policy is implemented as still-disabled evidence, but final production calibration approval is absent. |
| Dependency and license review complete | Candidate-level review complete for Tink plus Bouncy Castle only. |
| Header commitment policy approved and implemented | Blocked for selection. HMAC header-commitment compute/verify exists as a still-disabled building block, but it is not wired into a provider or vault unlock path. |
| HKDF-SHA-256 key-expansion primitive approved and implemented | Blocked for selection. HKDF expansion exists as a still-disabled building block from caller-supplied root material, but Argon2id/passphrase integration and provider wiring are absent. |
| HMAC-SHA-256 header-commitment primitive approved and implemented | Blocked for selection. HMAC computation/verification exists as a still-disabled building block, but provider integration is absent. |
| Key-expansion output layout approved and implemented | Blocked for selection. The 64-byte root, 32-byte header key, and 32-byte record AEAD key layout is implemented in the building block, but no provider/unlock path uses it. |
| Canonical header/HKDF/HMAC vectors approved and implemented | Blocked for selection. The non-secret vectors are matched by still-disabled building blocks, but provider-level KATs, Argon2id, AEAD, and storage are absent. |
| Canonical header encoding policy approved and implemented | Blocked for selection. Deterministic header bytes are implemented by the building block and validated by the in-memory container parser/writer, but no provider unlock path or selectable provider integration exists. |
| Key-separation labels policy approved and implemented | Blocked for selection. Stable labels are used by the still-disabled HKDF building block, but no provider/unlock path is wired. |
| Strict AAD contract approved and implemented | Blocked for selection. Strict AAD serialization and Tink record AEAD behavior are implemented as still-disabled building blocks, but no provider-wired AEAD path, vault container, storage manifest, or production provider KAT path exists. |
| Vault container/manifest/storage contract modeled | Evidence exists as contract ids: `skald-vault-v1-container-contract-v1`, `skald-vault-v1-manifest-contract-v1`, `skald-vault-v1-local-manifest-storage-policy-v1`, `skald-vault-v1-storage-namespace-path-policy-v1`, `skald-vault-v1-storage-layout-plan-v1`, `skald-vault-v1-path-containment-planner-v1`, `skald-vault-v1-platform-root-settings-policy-v1`, `skald-vault-v1-linux-custom-root-validation-policy-v1`, `skald-vault-v1-linux-root-resolution-policy-v1`, `skald-vault-v1-platform-root-resolver-boundary-v1`, `skald-vault-v1-platform-path-construction-boundary-v1`, `skald-vault-v1-storage-safety-preflight-boundary-v1`, `skald-vault-v1-disabled-storage-service-facade-v1`, `skald-vault-v1-persistence-readiness-gate-v1`, `skald-vault-v1-lock-session-lifecycle-boundary-v1`, `skald-vault-v1-atomicity-crash-recovery-policy-v1`, `skald-vault-v1-secure-storage-boundary-policy-v1`, and `skald-vault-v1-anti-rollback-anchor-policy-v1`. The container parser/writer, namespace/path policy, logical layout plan, path-containment planner, Linux custom-root static validation policy, Linux root-resolution evidence policy, platform root resolver boundary, platform path-construction boundary, storage safety preflight boundary, disabled storage service facade, vault persistence readiness gate, and lock/session lifecycle boundary are implemented only for in-memory/caller-supplied, injected, typed model, or fixed non-secret fixture values. The platform root settings policy is model-only evidence that records Android app-private internal storage, Linux user-data root/default `~/.local/share/` convention, future Linux custom roots through Settings after static validation plus containment/symlink/permission/durability review, OS keyring and password-manager passphrase storage rejection, and passphrase-first default authority. Accepted custom-root candidates, accepted Linux root-resolution evidence, accepted platform root resolver evidence, accepted platform path-construction evidence, accepted storage safety preflight evidence, disabled storage facade evidence, vault persistence readiness gate evidence, and lock/session lifecycle evidence are not resolved paths and do not prove existence, filesystem safety, containment, symlink state, permissions, ownership, durability, atomic-write safety, crash-recovery safety, Settings readiness, storage readiness, provider readiness, unlock readiness, active-session readiness, mainnet readiness, or persistence readiness. Linux root-resolution and platform root resolver evidence do not read `HOME`, `XDG_DATA_HOME`, environment variables, or system properties. Platform path-construction evidence consumes root evidence plus logical layout segments and does not construct real or absolute paths, return `File`/`Path`/`Uri`, call filesystem APIs, create directories, read files, or write files. Storage safety preflight evidence consumes planned artifact-location evidence and future safety-gate evidence only; it does not run filesystem checks or enable manifest/storage-index/record read/write. Disabled storage service facade evidence consumes planned artifact-location and preflight evidence but every operation returns disabled/rejected; it does not read/write manifests, read/write storage indexes, read/write/list/delete/quarantine/recover records, perform atomic writes, perform crash recovery, or map real storage failures. Vault persistence readiness gate evidence composes those disabled inputs and remains blocked. Lock/session lifecycle evidence composes readiness/provider/storage/secure-storage and lifecycle-event evidence but accepts no passphrases or key material and cannot create a usable active session. This does not enable persistence, unlock, root resolution, Settings UI, settings persistence, OS keyring integration, password-manager integration, actual path construction, provider selection, production provider approval, mainnet, or real path containment checks. |
| Passphrase policy boundary modeled | Evidence exists as `skald-vault-v1-passphrase-policy-boundary-v1`. It models future passphrase input, normalization/encoding policy ids, retry/throttle/lockout policy requirements, redaction requirements, clear/wipe requirements, biometric/Android-Keystore future-only status, OS-keyring/password-manager passphrase-storage rejection, and unlock prerequisites. It remains blocked and does not accept actual passphrases, PINs, biometrics, password examples, mnemonic examples, passphrase bytes, passphrase hashes, passphrase fingerprints, KDF input/output, key material, raw paths, Settings values, provider handles, storage handles, or database handles. It does not store, normalize, encode, hash, fingerprint, log, build-history, crash-report, analyze, or persist passphrases; run Argon2id/KDF/HKDF/HMAC/AEAD; implement retry, throttle, lockout, passphrase UI, unlock UI, wipe/zeroization, Android Keystore, OS keyrings, or password managers; or enable vault unlock, active sessions, provider selection, vault persistence, production provider approval, or mainnet. |
| Clear/wipe strategy boundary modeled | Evidence exists as `skald-vault-v1-clear-wipe-strategy-boundary-v1`. It models future sensitive value classes, lifecycle triggers, clear/wipe requirements, strategy classes, redaction-safe diagnostics, provider/storage/session cleanup requirements, and the limitation that JVM zeroization cannot be proven by this model. It remains blocked/model-only and does not accept actual passphrases, PINs, mnemonic text, seed bytes, private-key bytes, raw KDF input/output, raw AEAD keys, entropy bytes, decrypted records, byte arrays, char arrays, mutable buffers, key material, raw paths, Settings values, provider handles, storage handles, or database handles. It does not clear real memory, zero real memory, prove JVM zeroization, use native memory, implement provider clear calls, implement storage clear calls, implement session invalidation, implement UI, enable vault unlock, active sessions, provider selection, vault persistence, production provider approval, or mainnet. |
| Stale-record manifest policy approved and implemented | Blocked. AAD binds record version/counter, and the in-memory manifest/stale decision building blocks can classify records relative to a caller-supplied manifest, but no manifest file/storage reader or writer, storage index, durable latest-counter tracking, crash-safe atomic update policy, conflict policy, or anti-rollback anchor exists. |
| Passphrase encoding policy approved | Building block implemented and tested. Still blocked because it is not wired into vault creation, unlock, or provider selection. |
| Argon2id passphrase-to-root derivation implemented | Building block implemented and tested with fixed non-secret fixture input. Still blocked because final production calibration approval, provider wiring, vault creation, and storage remain absent. |
| Argon2id calibration policy implemented | Building block implemented and tested for the shared floor, candidate selection, memory-failure handling, stored-parameter authority, and no silent downgrade. Still blocked because final production calibration approval and provider selection remain absent. |
| Keyset or raw-key handling approved | Partially evidenced. The desktop/JVM test-scope result is `FEASIBLE_PUBLIC_RAW_KEY_API` and the Android instrumented test-scope result is `ANDROID_FEASIBLE_PUBLIC_RAW_KEY_API`, but no production provider implementation, production provider KATs, header commitment implementation, or storage review is approved. No keyset persistence is approved. |
| Secure secret storage approved | Blocked. |
| Secure metadata storage approved | Blocked. |
| Vault container/storage review complete | Blocked. |
| Manifest/storage atomicity and crash recovery reviewed | Blocked. Durability remains fail-closed: unsupported, unknown, unreviewed, insufficient, unsafe, or failed durability blocks encrypted vault persistence, and warning-only persistence/user-consent override is not approved for v1. |
| Redaction and failure-mode tests passed | Blocked for production provider. |
| Migration and corruption tests passed | Blocked. |
| Mainnet release-hardening approved | Blocked. |

Passing dependency-level KATs, test-provider KATs, deterministic vector tests, randomized AEAD building-block tests, the still-disabled provider facade checks, or documented KAT/manifest contracts must not bypass these gates.

The current v1 contract pins the review direction to Bouncy Castle Argon2id, Tink XChaCha20-Poly1305, and OS SecureRandom with one explicit suite id. It also records why Skald is not using day-one provider agility: one pinned suite reduces audit surface, KAT matrix size, migration complexity, and accidental selectability risk. This is a prerequisite contract only; it cannot select the provider.

## Platform, Compatibility, And Parameter Policy

The Argon2id candidate policy is explicitly non-final:

- Shared v1 floor: 64 MiB, 3 passes, 1 lane, at least 16-byte salt, preferred 32-byte salt for new vaults, and 64-byte derived root material.
- Desktop stronger candidate: 96 MiB, 3 passes, 1 lane, 64-byte output, Argon2 version 19.
- Historical high-end Android timing evidence: 32 MiB, 3 passes, 1 lane, 32-byte output, Argon2 version 19. This is historical probe evidence and does not satisfy the v1 floor.
- Historical 16 MiB fallback/probe evidence: below the active v1 floor and not a selectable fallback.
- Android supported-compatibility planning is modeled separately from final KDF parameter approval.
- Manual Android capture protocol exists for optional device-class and runtime-environment evidence; low-end and mid-range model evidence is no longer a compatibility hard blocker.

The provider-selection boundary treats missing final KDF parameter approval, missing selectable production provider implementation, still-disabled KAT harness evidence, missing stale-record manifest/storage policy, disabled storage, and runtime provider/primitive/randomness checks as production gates. Unknown runtime provider or randomness state blocks provider planning. Satisfied compatibility planning, still-disabled calibration evidence, and still-disabled integrated KAT execution still do not make a provider selectable. Test-only randomness probes generate only non-secret samples and are not entropy-quality proof. The desktop and Android raw-key feasibility probes and still-disabled record AEAD building block satisfy only the raw-key construction and isolated behavior questions for the pinned public Tink API path; they do not approve vault storage or provider selection.

## Storage And Mainnet Gates

Secure secret storage remains disabled/fail-closed. Secure metadata persistence remains disabled/fail-closed. The provider-selection boundary treats both as selection blockers for persistence use.

The registry does not create a vault container, does not read or write a manifest, does not maintain a storage index, does not write files, does not use SharedPreferences, does not use desktop config files, does not use OS keyrings, and does not serialize sensitive metadata.

Mainnet remains disabled. Any mainnet selection request adds a mainnet blocker and still returns the disabled provider.

## Source Confinement

The selection boundary imports no provider APIs and performs no crypto. Tink and Bouncy Castle calls remain confined to:

- platform compile probes,
- dependency-level KAT tests,
- Argon2id calibration probe tests,
- test-only provider KAT harnesses,
- approved still-disabled passphrase/KDF/header/AAD/record-AEAD building blocks,
- artifact/source-guard tests.

BDK imports remain confined to approved platform probes and desktop-test validation code. The provider-selection boundary imports no BDK types and does not open network or process clients.

## Explicit Non-Capabilities

This boundary does not enable:

- executable production provider crypto,
- provider-wired Argon2id passphrase derivation or final production calibration approval,
- provider-wired AEAD execution,
- fake encryption,
- metadata-only still-disabled provider facade selectability,
- key generation,
- Tink keyset creation or persistence,
- raw key material persistence,
- file-backed vault container read/write,
- warning-only encrypted vault persistence,
- root resolution, usable platform path construction, or real path containment checks,
- Settings UI or settings persistence for vault roots,
- OS keyring or password-manager integration for Skald-managed vault passphrases,
- passphrase, PIN, biometric, or unlock UI,
- passphrase policy boundary evidence as passphrase input acceptance, normalization or encoding execution, hashing, fingerprinting, retry/throttle/lockout implementation, Argon2id/KDF execution, provider readiness, or unlock readiness,
- creation authorization boundary evidence as vault creation authorization, initial passphrase acceptance, key/salt/nonce/id generation readiness, header/container/manifest/storage-index/record creation readiness, secure metadata creation readiness, wrapped-key storage readiness, initial persistence readiness, post-create session readiness, provider readiness, or persistence readiness,
- lock/session state persistence or usable active sessions,
- runtime logging, crash reporting, analytics, support export, runtime diagnostics, diagnostic persistence, secret display, secret hashing, secret fingerprinting, or redaction/leakage evidence as provider readiness,
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

## Acceptance Criteria Before Future Selection Work

The next provider-selection change may only consider a non-disabled provider after a separate branch provides evidence for:

1. executable production provider implementation behind Skald-owned interfaces,
2. provider-level KAT execution through that production provider,
3. Android and desktop runtime validation for the production provider,
4. supported Android compatibility/runtime provider/randomness checks where Android is in scope,
5. Linux runtime provider/randomness checks where Linux desktop is in scope,
6. final Argon2id parameter approval for the target platform after review of the still-disabled floor/candidate-selection/no-downgrade evidence,
7. header commitment provider integration and key-commitment policy approval,
8. passphrase encoding policy approval and production validation,
9. passphrase policy boundary production review for input handling, normalization/encoding execution, retry/throttle/lockout behavior, clear/wipe behavior, redaction, and unlock prerequisites,
10. clear/wipe strategy production review for sensitive value categories, lifecycle triggers, provider/storage/session cleanup, JVM zeroization limitations, and fail-closed lock behavior,
11. raw-key handling approval through public supported Tink APIs or a separate human-approved alternative,
12. canonical header encoding integration that matches the non-secret test vectors,
13. canonical header, HKDF-SHA-256, and HMAC-SHA-256 provider-level tests matching the non-secret vectors,
14. HKDF-SHA-256 key expansion and HMAC-SHA-256 header commitment wired and tested through the still-disabled provider,
15. key-separation implementation with the selected key-expansion primitive,
16. strict AAD implementation and tamper/copy/replay tests,
17. provider-level deterministic vector KATs and randomized AEAD behavioral KATs on desktop and Android,
18. integrated verification-order KATs proving header commitment before record decrypt,
19. stale-record manifest/storage policy implementation and tests,
20. secure storage, secure metadata, and lock/session lifecycle approval,
21. vault container/storage review,
22. manifest/storage atomicity and crash-recovery review, including the still-disabled simulator evidence, separate platform durability review, and proof that unsupported/unknown/failed durability blocks persistence rather than warning-only continuation,
23. redaction and failure-mode tests,
24. migration/corruption tests,
25. explicit mainnet release-hardening if mainnet is requested.

Until then, the provider-selection registry must keep selecting the disabled provider only.

## Next Step

The next focused branch should remain disabled unless the user explicitly approves provider-selectability scope. Remaining decision points are final Argon2id calibration approval, supported Android/Linux runtime provider and randomness review, passphrase policy production review, manifest/storage stale-record enforcement design, vault container/storage review, secure storage review, lock/session lifecycle production approval, redaction/leakage tests, migration/corruption tests, and release approval. The metadata-only facade is not a persistence or selection prerequisite by itself.

## Provider Operation Authorization Boundary

Provider selection now records `SkaldVaultV1ProviderOperationAuthorizationPolicy` as an adjacent still-disabled boundary. It models future provider operation authorization gates, but it cannot make a provider selectable and does not change registry behavior. The registry still selects only `DisabledVaultCryptoProvider`; Tink plus Bouncy Castle remains a blocked future candidate; Lazysodium remains rejected for this vault branch; IonSpin remains deferred; and `productionProviderSelectable` remains false.

Current provider operation authorization is blocked/fail-closed for every operation kind and purpose. The boundary does not run provider KATs, runtime randomness checks, entropy/salt/nonce/key generation, Argon2id/KDF/HKDF/HMAC, AEAD encrypt/decrypt, header commitment computation or verification, record encrypt/decrypt, manifest/storage-index authentication, key wrapping/unwrapping, provider clear/dispose, vault creation, vault unlock, vault persistence, provider selection, or mainnet. OS keyrings and password managers remain rejected for Skald-managed vault passphrase storage, and passphrase-first remains the default authority.

## Runtime Randomness Authorization Boundary

Provider selection now also records `SkaldVaultV1RuntimeRandomnessAuthorizationPolicy` as an adjacent still-disabled boundary. It models future runtime entropy, OS CSPRNG, provider randomness, hardware-backed entropy evidence, salt generation, nonce generation, key-generation input, KDF salt, AEAD nonce, source-kind review, required gates, blockers, warnings, and disabled capabilities, but it cannot make a provider selectable and does not change registry behavior.

Current runtime randomness authorization is blocked/fail-closed for every operation kind, purpose, and source kind. The boundary does not call `SecureRandom`, Kotlin Random, Java Random, `Math.random`, OS randomness APIs, provider randomness APIs, or runtime health checks. It does not generate entropy, salts, nonces, keys, random byte fixtures, deterministic vectors, provider operations, KATs, KDF/HKDF/HMAC/AEAD, vault creation, vault unlock, vault persistence, provider selection, or mainnet.

Future acceptable randomness must come from reviewed OS cryptographic randomness/CSPRNG or reviewed provider randomness. General-purpose PRNGs are forbidden for secrets, salts, nonces, and keys. Android OS CSPRNG/SecureRandom remains future-reviewed only, Android hardware-backed key protection is separate from entropy quality, Linux entropy quality remains a required review gate, the registry still selects only `DisabledVaultCryptoProvider`, and `productionProviderSelectable` remains false.

## KDF Calibration Authorization Boundary

Provider selection now treats `SkaldVaultV1KdfCalibrationAuthorizationPolicy` as a still-disabled model-only prerequisite. The boundary models future KDF operation kinds, purposes, parameter/evidence kinds, platform/device classes, required gates, blockers, warnings, disabled capabilities, and redacted tokens, but it authorizes no KDF calibration and no KDF execution.

Current KDF authorization is blocked because provider operations are unauthorized, runtime randomness is unauthorized, passphrase input remains blocked, final KDF calibration is not approved, `productionProviderSelectable` remains false, and no production KDF execution is allowed. The boundary does not run Argon2id, run KDFs, run calibration, benchmark devices, inspect real host/device details, approve final KDF parameters, normalize or encode real passphrases, generate or consume salts, run provider operations, run provider KATs, derive vault keys, enable vault unlock, enable persistence, make a provider selectable, or approve mainnet.

Android calibration remains future-reviewed only, Linux calibration remains future-reviewed only, test-vector profiles do not authorize production runtime unlock, mainnet KDF use remains blocked until release review, OS keyrings and password managers remain rejected for Skald-managed vault passphrase storage, and passphrase-first remains the default authority.

## Secure-Storage Authorization Boundary

Provider selection now treats `SkaldVaultV1SecureStorageAuthorizationPolicy` as a still-disabled model-only prerequisite. The boundary models future secure-storage operation kinds, value kinds, target kinds, required gates, blockers, warnings, disabled capabilities, and redacted tokens, but it authorizes no secure storage operation and cannot make a provider selectable.

Current secure-storage authorization is blocked because persistence readiness is blocked, secure secret storage and secure metadata storage are unavailable, provider operations are unauthorized, runtime randomness and KDF calibration authorization remain blocked, `productionProviderSelectable` remains false, and no target is approved for production secret storage. The boundary does not store, retrieve, or delete secrets; wrap or unwrap keys; store wrapped keys; store metadata; export or import backup material; migrate or purge secure storage; use Android Keystore, Android Credential Manager, OS keyrings, password managers, SharedPreferences, Settings storage, files, databases, or encrypted vault storage; run provider operations; run KDF/HKDF/HMAC/AEAD; call randomness APIs; enable vault unlock; enable persistence; make a provider selectable; or approve mainnet.

OS keyrings remain rejected as primary storage and for Skald-managed vault passphrase storage, password managers remain rejected for Skald-managed vault passphrase storage, Settings/preferences remain rejected for secrets and sensitive metadata, plaintext storage/logs/crash reports/analytics/support exports remain rejected for raw secret material, Android hardware-backed wrapping remains future-reviewed only, Linux optional key wrapping remains future-reviewed only, the registry still selects only `DisabledVaultCryptoProvider`, and `productionProviderSelectable` remains false.

## Vault Unlock Authorization Boundary

Provider selection now also treats `SkaldVaultV1UnlockAuthorizationPolicy` as a still-disabled model-only prerequisite. The boundary models future unlock operation kinds, purposes, credential classes, required gates, blockers, warnings, disabled capabilities, and redacted tokens, but it authorizes no unlock operation and cannot make a provider selectable.

Current unlock authorization is blocked because passphrase input is blocked, KDF calibration authorization is blocked, runtime randomness authorization is blocked, provider operations are unauthorized, secure-storage authorization is blocked, storage service operations are disabled, lock/session lifecycle is unavailable, persistence readiness is blocked, `productionProviderSelectable` remains false, and no production unlock execution is allowed. The boundary does not accept passphrases, PINs, or biometrics; normalize or encode passphrases; run Argon2id/KDF/HKDF/HMAC/AEAD; generate or consume salts, nonces, or random bytes; call provider operations; read secure storage, metadata storage, or encrypted vault storage; retrieve or unwrap wrapped keys; decrypt records; create active sessions; hold decrypted key material; persist unlock state; add UI; enable vault creation, vault unlock, vault persistence, make a provider selectable, or approve mainnet.

OS keyrings remain rejected as primary storage and for Skald-managed vault passphrase storage, password managers remain rejected for Skald-managed vault passphrase storage, Settings/preferences storage remains rejected for secrets and unlock state, Android unlock and hardware-wrapper evidence remains future-reviewed only, Linux unlock and optional-wrapper evidence remains future-reviewed only, mainnet unlock remains blocked until release review, the registry still selects only `DisabledVaultCryptoProvider`, and `productionProviderSelectable` remains false.

## Vault Creation Authorization Boundary

Provider selection now also treats `SkaldVaultV1CreationAuthorizationPolicy` as a still-disabled model-only prerequisite. The boundary models future creation operation kinds, purposes, initializer classes, required gates, blockers, warnings, disabled capabilities, and redacted tokens, but it authorizes no creation operation and cannot make a provider selectable.

Current creation authorization is blocked because passphrase input is blocked, KDF calibration authorization is blocked, runtime randomness authorization is blocked, provider operations are unauthorized, secure-storage authorization is blocked, storage service operations are disabled, storage safety is not runtime-approved, persistence readiness is blocked, unlock authorization is blocked, `productionProviderSelectable` remains false, and no production creation execution is allowed. The boundary does not accept passphrases, PINs, or biometrics; normalize or encode passphrases; generate salts, nonces, keys, or ids; run Argon2id/KDF/HKDF/HMAC/AEAD; call provider operations; create headers, header commitments, containers, manifests, storage indexes, records, secure metadata, wrapped keys, storage namespaces, persistence commits, rollback handlers, failure cleanup, or active sessions; write secure storage, metadata storage, encrypted vault storage, Settings, files, or databases; add UI; enable vault creation, vault unlock, vault persistence, make a provider selectable, or approve mainnet.

OS keyrings remain rejected as primary storage and for Skald-managed vault passphrase storage, password managers remain rejected for Skald-managed vault passphrase storage, Settings/preferences storage remains rejected for secrets, creation state, and unlock state, Android creation and hardware-wrapper evidence remains future-reviewed only, Linux creation and optional-wrapper evidence remains future-reviewed only, mainnet creation remains blocked until release review, the registry still selects only `DisabledVaultCryptoProvider`, and `productionProviderSelectable` remains false.

## Authorization/Readiness Matrix

Provider selection now also treats `SkaldVaultV1AuthorizationReadinessMatrixPolicy` as still-disabled model-only traceability evidence. The matrix summarizes all current blockers for future provider selectability, provider operations, randomness, KDF, secure storage, creation, unlock, active sessions, persistence, storage, lifecycle, platform gates, wallet work, and mainnet, but it cannot make a provider selectable and does not change registry behavior.

Every production/runtime capability remains blocked. Warning-only evidence cannot authorize production use, user consent cannot override missing hard gates, test-only evidence cannot authorize production runtime, mainnet remains disabled, the registry still selects only `DisabledVaultCryptoProvider`, Tink plus Bouncy Castle remains a blocked future candidate only, and `productionProviderSelectable` remains false.
