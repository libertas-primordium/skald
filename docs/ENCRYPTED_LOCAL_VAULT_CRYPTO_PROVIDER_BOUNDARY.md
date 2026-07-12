# Encrypted Local Vault Crypto Provider Boundary

## Status

Skald Vault now has a narrow Skald-owned disabled `VaultCryptoProvider` boundary for the future app-controlled encrypted local vault.

This is a provider-boundary and policy-model pass. The passphrase policy validator, explicit-parameter Argon2id root derivation, Argon2id calibration policy/candidate-selection/memory-failure/no-downgrade model, canonical header serializer, HKDF-SHA-256 expansion, HMAC-SHA-256 header commitment verification, strict AAD serialization, Tink XChaCha20-Poly1305 record AEAD construction from caller-supplied 32-byte key material, in-memory vault container parser/writer, in-memory manifest parser/writer, local manifest-relative stale-record decision policy, metadata-only still-disabled provider facade, redaction/leakage boundary, passphrase policy boundary, clear/wipe strategy boundary, migration/corruption boundary, and model-only storage boundary/root/path/symlink/permission/durability/atomicity/secure-storage contracts now exist as still-disabled building blocks/evidence. The provider boundary still does not implement selectable provider crypto, final production calibration approval, provider-wired Argon2id passphrase KDF execution, provider-wired AEAD execution, real migration, migration dry-run, storage repair, record quarantine, record recovery, real storage parsing, real rollback protection, real crash recovery, passphrase input acceptance, passphrase normalization or encoding execution, passphrase hashing, passphrase fingerprinting, retry/throttle/lockout runtime behavior, key generation, Tink keyset creation or storage, raw key material storage, actual path construction, platform root selection, file-backed vault container read/write, manifest file/storage read/write, storage success, secure secret storage, secure metadata persistence, production sync, backend clients, signing, broadcasting, Tor transport, Nostr parsing, public endpoints, Skald-operated infrastructure, or mainnet.

A still-disabled `SkaldVaultV1ProviderCandidatePackagingPolicy` now models future provider candidate packaging as evidence only. It names future candidate families, dependency categories, source-set placement rules, provider operation/KAT/randomness/KDF/AEAD/HKDF/HMAC/header-commitment/key-wrapping support statuses, review requirements, blockers, and redacted diagnostic tokens. It does not implement a provider, add or activate dependencies, instantiate Tink, Bouncy Castle, Javax/JCA crypto, Android Keystore, or any provider runtime, run provider operations, run KATs, run randomness checks, run KDF/HKDF/HMAC/AEAD, make a provider selectable, or enable vault creation, unlock, persistence, wallet behavior, or mainnet.

A still-disabled `SkaldVaultV1ProviderDependencyBuildPolicy` now models the provider dependency build spike as evidence only. This branch does not add a new Gradle dependency because the existing platform-scoped Tink/Bouncy declarations already provide declared/resolvable build evidence and the design remains a split stack rather than one new single dependency. The boundary records candidate dependency family, declaration status, source-set placement, build evidence status, promotion blockers, and disabled capabilities. It does not activate dependencies, import provider APIs in common production code, instantiate provider runtime, run provider operations or KATs, run randomness checks, run Argon2id/KDF/HKDF/HMAC/AEAD, add provider implementation or factory code, change provider selection, or enable vault creation, unlock, persistence, wallet behavior, or mainnet.

A still-disabled `SkaldVaultV1ProviderSelectionPromotionBlockersPolicy` now models provider selection promotion blockers as evidence only. It records the stages between candidate description, dependency/build evidence, dependency review, source-set review, implementation, factory, registry, test selectability, production selectability, provider operation authorization, KAT approval, runtime randomness, KDF/AEAD/key-wrapping support, creation/unlock/persistence integration, release validation, and mainnet approval. Every path past candidate description and build-only evidence remains blocked. It does not add dependencies, implement a provider, add a factory, enable a registry, instantiate provider code, run provider operations or KATs, run randomness checks, run KDF/HKDF/HMAC/AEAD, make a provider selectable, set `productionProviderSelectable=true`, enable vault creation, unlock, persistence, wallet behavior, or mainnet.

A still-disabled `SkaldVaultV1ProviderInterfaceContractAuditPolicy` now audits provider-facing contracts as model-only evidence. It records provider-neutral common boundaries, redacted diagnostics, required authorization gates, promotion blockers, source-set safety, and contract risks that would accept secrets, byte material, provider handles, crypto objects, executable operations, bypasses, selectable provider state, or platform crypto imports. The audit rejects those risks and does not add dependencies, implement a provider, add a factory, enable a registry, instantiate provider code, run provider operations or KATs, run randomness checks, run KDF/HKDF/HMAC/AEAD, make any provider selectable, enable vault creation, unlock, persistence, wallet behavior, or mainnet.

A still-disabled `SkaldVaultV1NonSelectableProviderSkeletonPolicy` now models the future provider skeleton shape as compile-shape evidence only. It records future provider identity, family, source-set placement, dependency visibility, provider implementation/factory/registry/selectability status, disabled operation surfaces, diagnostics/redaction, acceptance gates, promotion blockers, and readiness-matrix interaction. It does not implement executable provider behavior, implement `VaultCryptoProvider`, add a factory, enable a registry, instantiate provider code, run provider operations or KATs, run randomness checks, run KDF/HKDF/HMAC/AEAD, wrap keys, make any provider selectable, enable vault creation, unlock, persistence, wallet behavior, or mainnet.

A still-disabled `SkaldVaultV1ProviderRegistryIsolationGuardPolicy` now models provider registry isolation as evidence only. It records that the current provider-selection registry remains isolated to `DisabledVaultCryptoProvider` and that non-selectable skeletons, candidate-packaging evidence, dependency/build evidence, interface-audit evidence, promotion-blocker evidence, authorization/readiness matrix evidence, provider-operation authorization evidence, runtime-randomness evidence, KDF/secure-storage/creation/unlock evidence, warning-only evidence, user consent, test-only evidence, and release/mainnet evidence cannot become non-disabled registry entries. It does not add dependencies, implement a provider, add a factory, enable a non-disabled registry, instantiate provider code, run provider operations or KATs, run randomness checks, run KDF/HKDF/HMAC/AEAD, make any provider selectable, enable vault creation, unlock, persistence, wallet behavior, or mainnet.

A still-disabled `SkaldVaultV1ProviderFactoryIsolationPolicy` now models provider factory isolation as evidence only. It records that no non-disabled provider factory or constructor exists or is reachable from provider selection, registry isolation, non-selectable skeleton evidence, candidate-packaging evidence, dependency/build evidence, interface-audit evidence, promotion-blocker evidence, authorization/readiness matrix evidence, warning-only evidence, user consent, test-only evidence, or release/mainnet evidence. It does not add dependencies, implement a provider, add a provider factory, enable a non-disabled registry, instantiate provider code, expose provider handles or crypto objects, accept byte material, run provider operations or KATs, run randomness checks, run KDF/HKDF/HMAC/AEAD, make any provider selectable, enable vault creation, unlock, persistence, wallet behavior, or mainnet.

A still-disabled `SkaldVaultV1ProviderOperationDispatchIsolationPolicy` now models provider operation dispatch isolation as evidence only. It records that no non-disabled provider operation dispatcher exists or is reachable from provider selection, registry isolation, factory isolation, non-selectable skeleton evidence, candidate-packaging evidence, dependency/build evidence, interface-audit evidence, promotion-blocker evidence, authorization/readiness matrix evidence, provider-operation authorization, runtime-randomness authorization, KDF calibration authorization, secure-storage authorization, creation authorization, unlock authorization, persistence readiness, warning-only evidence, user consent, test-only evidence, or release/mainnet evidence. It does not add dependencies, implement a provider, add a provider factory, add a provider dispatcher, enable a non-disabled registry, instantiate provider code, expose provider handles or crypto objects, accept byte material, run provider operations or KATs, run randomness checks, run KDF/HKDF/HMAC/AEAD, compute header commitments, encrypt/decrypt records, wrap/unwrap keys, make any provider selectable, enable vault creation, unlock, persistence, wallet behavior, or mainnet.

A still-disabled `SkaldVaultV1ProviderKatExecutionIsolationPolicy` now models provider KAT execution isolation as evidence only. It records that no non-disabled provider KAT executor exists or is reachable from provider selection, registry isolation, factory isolation, dispatch isolation, non-selectable skeleton evidence, candidate-packaging evidence, dependency/build evidence, interface-audit evidence, promotion-blocker evidence, authorization/readiness matrix evidence, provider-operation authorization, runtime-randomness authorization, KDF calibration authorization, secure-storage authorization, creation authorization, unlock authorization, persistence readiness, public non-wallet vector documentation, canonical header/HKDF/HMAC vector documentation, test-provider KAT harness concepts, warning-only evidence, user consent, test-only evidence, or release/mainnet evidence. It does not add dependencies, implement a provider, add a provider factory, add a provider dispatcher, add a provider KAT executor, enable a non-disabled registry, instantiate provider code, expose provider handles or crypto objects, accept byte material, run provider operations or KATs, run randomness checks, run KDF/HKDF/HMAC/AEAD, compute header commitments, encrypt/decrypt records, wrap/unwrap keys, make any provider selectable, set `productionProviderSelectable=true`, enable vault creation, unlock, persistence, wallet behavior, or mainnet. Future KAT executor introduction requires an explicit later branch and review.

[`ENCRYPTED_LOCAL_VAULT_PROVIDER_EXECUTABLE_KAT_DECISION_GATE.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_EXECUTABLE_KAT_DECISION_GATE.md) records the still-disabled provider executable KAT decision gate. It defines the future evidence required before any later branch may introduce even a test-only executable provider-level KAT path, and records that executable KATs, production KATs, provider selection authorization, `productionProviderSelectable=true`, vault creation/unlock/persistence, storage, production sync, and mainnet remain blocked.

[`ENCRYPTED_LOCAL_VAULT_PROVIDER_EXECUTABLE_KAT_PREREQUISITE_AUDIT.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_EXECUTABLE_KAT_PREREQUISITE_AUDIT.md) records the still-disabled prerequisite audit for that gate. It maps the gate prerequisites to current model, documentation, and test-only evidence, but it does not authorize executable KAT introduction, provider selection, `productionProviderSelectable=true`, provider operation execution, vault creation/unlock/persistence, storage, production sync, or mainnet.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_EXECUTABLE_PROVIDER_KAT_SCOPE_DECISION.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_EXECUTABLE_PROVIDER_KAT_SCOPE_DECISION.md) records the still-disabled test-only executable provider KAT scope decision. It defines future source-set and material limits before any later branch may implement a test-only executor, but it does not add an executor, authorize KAT execution now, authorize production provider selection, set `productionProviderSelectable=true`, enable vault lifecycle/persistence, enable production sync, or enable mainnet.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_KAT_EXECUTOR_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_KAT_EXECUTOR_CONTRACT.md) records the still-disabled test-only provider KAT executor contract. It defines the non-executable contract for a possible future test-only executor, including label/reference-only future inputs, forbidden material classes, operation categories, source-set limits, redacted result policy, and non-authorizing result rules. It does not implement an executor, expose a runnable executor interface, authorize KAT execution now, authorize production provider selection, set `productionProviderSelectable=true`, enable vault lifecycle/persistence, enable production sync, or enable mainnet.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_KAT_VECTOR_CATALOG.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_KAT_VECTOR_CATALOG.md) records the still-disabled test-only provider KAT vector catalog. It defines future positive, negative, redaction, platform, provenance, and fixture policy classes as references only. It does not add raw vector material to common production source, implement an executor, authorize KAT execution now, authorize production provider selection, set `productionProviderSelectable=true`, enable vault lifecycle/persistence, enable production sync, or enable mainnet.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_KAT_EXECUTOR_READINESS_GATE.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_KAT_EXECUTOR_READINESS_GATE.md) records the still-disabled readiness gate for any later test-only provider KAT executor implementation branch. It evaluates the prior decision, audit, scope, contract, and catalog evidence, but the current result is not ready and not authorized: no executor, runnable interface, provider operation execution, provider selection change, vault lifecycle, persistence, production sync, or mainnet is enabled.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_KAT_SOURCE_SET_CONFINEMENT.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_KAT_SOURCE_SET_CONFINEMENT.md) records the still-disabled source-set confinement boundary for any later test-only provider KAT executor branch. It defines production-forbidden, model-only, evidence-only, and future-review-required source-set categories, but it does not authorize executor implementation, expose a runnable interface, run KATs, run provider operations, change provider selection, enable vault lifecycle or persistence, enable production sync, or enable mainnet.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_DECISION.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_DECISION.md) records the still-disabled test-only provider identity decision. It models future test-only provider identity categories without implementing a provider, exposing an instantiable identity, exposing a registry-selectable identity, exposing an executor-targetable identity, changing provider selection, enabling vault lifecycle or persistence, enabling production sync, or enabling mainnet.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_ISOLATION_GUARD.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_ISOLATION_GUARD.md) records the still-disabled test-only provider identity isolation guard. It keeps modeled identity categories label-only and absent from registry, factory, dispatcher, executor-target, vault-lifecycle, persistence, secure-storage, secure-metadata, production-sync, wallet-service, BDK, settings, UI, and mainnet paths. It does not implement a provider or expose an instantiable, selectable, or executor-targetable identity.

Provider selection is documented separately in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_SELECTION_BOUNDARY.md). Runtime randomness/provider checks are documented in [`ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md`](ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md). The Tink raw-key feasibility probes are documented in [`ENCRYPTED_LOCAL_VAULT_TINK_RAW_KEY_FEASIBILITY_PROBE.md`](ENCRYPTED_LOCAL_VAULT_TINK_RAW_KEY_FEASIBILITY_PROBE.md). The header commitment, canonical header encoding, key-separation label, strict AAD construction contract, deterministic AAD bytes, and record AEAD behavioral fixture are documented in [`ENCRYPTED_LOCAL_VAULT_HEADER_COMMITMENT_AAD_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_HEADER_COMMITMENT_AAD_CONTRACT.md). The selected HKDF-SHA-256 key expansion, HMAC-SHA-256 header commitment, and output layout are documented in [`ENCRYPTED_LOCAL_VAULT_KEY_EXPANSION_COMMITMENT_POLICY.md`](ENCRYPTED_LOCAL_VAULT_KEY_EXPANSION_COMMITMENT_POLICY.md). Deterministic non-secret canonical header/HKDF/HMAC vectors are documented in [`ENCRYPTED_LOCAL_VAULT_CANONICAL_HEADER_HKDF_HMAC_VECTORS.md`](ENCRYPTED_LOCAL_VAULT_CANONICAL_HEADER_HKDF_HMAC_VECTORS.md). The provider-level KAT strategy for randomized AEAD and stale-record/rollback manifest contract are documented in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md). The detailed vault container, manifest, storage, stale-record, rollback, atomicity, crash-recovery, and secure-storage boundary contract is documented in [`ENCRYPTED_LOCAL_VAULT_CONTAINER_MANIFEST_STORAGE_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_CONTAINER_MANIFEST_STORAGE_CONTRACT.md). The v1 production-provider acceptance contract is documented in [`ENCRYPTED_LOCAL_VAULT_PRODUCTION_PROVIDER_ACCEPTANCE_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PRODUCTION_PROVIDER_ACCEPTANCE_CONTRACT.md). That registry selects only `DisabledVaultCryptoProvider`; Tink plus Bouncy Castle remains a blocked future candidate and no production provider is selectable.

Test-source-only provider-selection validation is documented in [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_SELECTION_VALIDATION.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_SELECTION_VALIDATION.md). It selects the test-only executable provider only through explicit test validation harnesses for public KDF/AEAD KAT scope. It does not modify production provider selection, does not make the provider production-selectable, does not persist a provider choice, does not add provider-selection UI, and does not add vault persistence, secure storage success, secure metadata success, production sync, signing/broadcasting, endpoint, UI, or mainnet behavior.

The test-source/commonTest provider-selection validation completion audit is documented in [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_SELECTION_VALIDATION_COMPLETION_AUDIT.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_SELECTION_VALIDATION_COMPLETION_AUDIT.md). It is audit evidence only and does not authorize production provider selection, provider choice persistence, vault persistence, secure storage, secure metadata, sync, signing/broadcasting, endpoint, UI, or mainnet behavior.

Runtime behavior remains fail-closed:

- `DisabledVaultCryptoProvider` rejects every modeled provider operation.
- `VaultCryptoProviderSelectionRegistry` selects only the disabled provider.
- `DisabledVaultCryptoProvider` rejects `validateKat`; provider-level KAT success is available only through test-only harnesses documented in [`ENCRYPTED_LOCAL_VAULT_TEST_PROVIDER_KAT_HARNESS.md`](ENCRYPTED_LOCAL_VAULT_TEST_PROVIDER_KAT_HARNESS.md).
- `SecureSecretStorage` remains disabled.
- `SecureWalletMetadataRepository` remains disabled.
- `EncryptedVaultReadinessPolicy` remains not implemented/not ready.
- Production persistence remains disabled.
- Production sync remains disabled.
- Mainnet remains disabled.

## Source Location

Production-safe common boundary:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/VaultCryptoProvider.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/VaultCryptoProviderSelection.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ContainerFormat.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ManifestFormat.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1StillDisabledProviderFacade.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderCandidatePackagingBoundary.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderDependencyBuildBoundary.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderSelectionPromotionBlockers.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderInterfaceContractAudit.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1NonSelectableProviderSkeletonBoundary.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderRegistryIsolationGuard.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderFactoryIsolationBoundary.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderOperationDispatchIsolationBoundary.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderKatExecutionIsolationBoundary.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderExecutableKatDecisionGate.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderExecutableKatPrerequisiteAudit.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ProviderTestOnlyExecutableKatScopeDecision.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderKatExecutorContract.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderKatVectorCatalog.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderKatExecutorReadinessGate.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderKatSourceSetConfinement.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderIdentityDecision.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderIdentityIsolationGuard.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1PassphrasePolicyBoundary.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1MigrationCorruptionBoundary.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/AndroidVaultCompatibilityPolicy.kt
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/RuntimeRandomnessProviderChecks.kt
```

Tests and source guards:

```text
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultCryptoProviderBoundaryTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultProviderCandidatePackagingBoundaryTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultProviderSelectionPromotionBlockersTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultProviderInterfaceContractAuditTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultNonSelectableProviderSkeletonBoundaryTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultProviderRegistryIsolationGuardTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultProviderFactoryIsolationBoundaryTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultProviderOperationDispatchIsolationBoundaryTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultProviderKatExecutionIsolationBoundaryTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultProviderExecutableKatDecisionGateTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultProviderExecutableKatPrerequisiteAuditTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultProviderTestOnlyExecutableKatScopeDecisionTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultTestOnlyProviderKatExecutorContractTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultTestOnlyProviderKatVectorCatalogTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultTestOnlyProviderKatExecutorReadinessGateTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultTestOnlyProviderKatSourceSetConfinementTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultTestOnlyProviderIdentityDecisionTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultTestOnlyProviderIdentityIsolationGuardTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultContainerParserWriterTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultManifestParserStalePolicyTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultCryptoProviderKatContractTest.kt
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/VaultCryptoTestProviderKatHarnessTest.kt
composeApp/src/androidInstrumentedTest/kotlin/com/libertasprimordium/skald/VaultCryptoAndroidTestProviderKatHarnessTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/EncryptedVaultReadinessPolicyTest.kt
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultCryptoDependencyProbeTest.kt
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/ProductionBackendAdapterSourceGuardTest.kt
```

## Boundary Purpose

The boundary defines the type shape future production vault cryptography must implement without exposing Tink, Bouncy Castle, JCA/JCE, BDK, platform, storage, or network types to common UI, settings codecs, storage models, Recovery Center models, Privacy Analyzer models, sync facade models, wallet domain models, or public app services.

The boundary models:

- provider implementation status,
- provider capabilities,
- provider blockers,
- typed KDF request/result/error models,
- typed AEAD encrypt/decrypt request/result/error models,
- key generation request/result models,
- keyset storage request/result models,
- provider-level KAT request/result/evidence models,
- record purposes,
- key roles,
- associated-data context,
- nonce mode policy,
- provider-level KAT requirements and contract registry,
- redacted diagnostics.

All operations return Skald-owned blocked results in this branch.

## Algorithm Policy

The provider boundary does not accept arbitrary caller-controlled algorithm strings.

It uses existing typed policy enums:

- target KDF: `Argon2id`,
- reviewed KDF fallback: `scrypt`,
- rejected default KDF: `PBKDF2`,
- target record AEAD: `XChaCha20-Poly1305`,
- reviewed fallback/platform roles: `ChaCha20-Poly1305`, `AES-256-GCM`, `AES-GCM-SIV`,
- nonce policy: random 24-byte XChaCha nonce per production record.

`ProviderKatFixedNonceOnly` exists only to model future provider-level public test vectors. It does not allow caller-provided production nonces.

## Record And Associated-Data Policy

Record purposes are typed:

- secret payload record,
- sensitive metadata record,
- backup/export record,
- platform wrapping record,
- provider KAT/test record.

Associated-data context is required for modeled AEAD operations. It carries only non-secret envelope context such as container version, record purpose, schema version, key version, and the design requirement that sensitive wallet metadata is excluded from associated data.

Associated data must not contain mnemonic material, seed bytes, private descriptors, private keys, wallet labels, UTXO labels, transaction notes, real observed addresses, txids, outpoints, endpoint labels, backend credentials, Nostr private-key material, or identity-linkage metadata.

## Disabled Provider Behavior

`DisabledVaultCryptoProvider` rejects:

- KDF derivation,
- AEAD record encryption,
- AEAD record decryption,
- key generation,
- keyset storage,
- provider-level KAT validation.

It returns redacted `VaultCryptoProviderResult.Blocked` values. Diagnostics contain only typed operation names and safe status codes. The boundary does not serialize payloads, keys, nonces, ciphertexts, plaintexts, keysets, vault containers, or wallet metadata.

## Provider-Level KAT Requirements

Dependency-level KATs already passed for the current candidate stack:

- Bouncy Castle Argon2id against RFC 9106 section 5.3 on desktop JVM and Android runtime.
- Tink XChaCha20-Poly1305 against the XChaCha draft appendix A.1 on desktop JVM and Android runtime.

Those are dependency/probe KATs, not provider-boundary KATs. The provider-level KAT contract is now documented separately in [`ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PROVIDER_KAT_CONTRACT.md).

The boundary records provider-level KAT requirements for:

- deterministic passphrase policy, Argon2id root-material, canonical header, HKDF info/output, HMAC header commitment, and strict AAD vectors through the future Skald-owned provider interface,
- randomized XChaCha20-Poly1305 record AEAD behavioral checks through the future Skald-owned provider interface, without requiring fixed ciphertext hex,
- integrated verification-order checks proving HMAC header commitment before record AEAD use,
- negative misuse cases such as wrong associated data, tampered ciphertext/tag, and wrong key,
- unsupported algorithm and production nonce-policy bypass rejection,
- redacted provider diagnostics,
- desktop runtime execution,
- Android runtime execution,
- release-like runtime coverage as a future gate,
- public non-wallet vectors only.

The disabled provider marks public-vector requirements as dependency/test evidence only and marks randomized AEAD behavioral, verification-order, negative, redaction, platform, and storage requirements as required but unsatisfied. A separate still-disabled integrated KAT harness now executes the deterministic vector stages and randomized AEAD behavioral checks with fixed non-secret fixtures, but no selectable production provider-level KAT path exists because no selectable production provider implementation exists. Passing dependency-level KATs remains necessary evidence, but it does not satisfy provider approval.

The test-only provider harness runs the public positive vectors and required negative cases through `VaultCryptoProvider.validateKat(...)` in desktop and Android test source sets. Returned evidence is redacted and scoped as `TestHarnessOnly`. This proves the interface can carry the required checks; it does not approve production provider implementation or storage.

`SkaldVaultV1StillDisabledProviderKatHarness` composes the still-disabled passphrase, Argon2id, HKDF, canonical header, HMAC header commitment, strict AAD, and Tink record AEAD building blocks. Its tests assert header commitment verification before record AEAD use and assert that commitment failure exits before record AEAD. It exposes no vault creation, provider selection, manifest/storage, secure storage, wallet, or persistence API.

`SkaldVaultV1StillDisabledProviderFacade` is a separate metadata/status facade for the future provider boundary. It exposes the suite id, KDF/AEAD/randomness labels, stable policy ids, still-disabled evidence, disabled reasons, remaining gates, and typed disabled results for creation/unlock/persistence/storage/selection status. It is not a `VaultCryptoProvider`, is not referenced by `VaultCryptoProviderSelectionRegistry`, and exposes no usable vault creation, unlock, storage, manifest, secure-storage, wallet, sync, backend, key generation, randomness, or record AEAD API.

`SkaldVaultV1PassphrasePolicyBoundary` is separate still-disabled passphrase policy evidence. It exposes typed policy categories, normalization and encoding policy identifiers, retry/throttle/lockout requirements, redaction requirements, clear/wipe requirements, biometric and Android-Keystore future-only status, OS-keyring/password-manager passphrase-storage rejection, and unlock prerequisites. It accepts typed policy requests only and does not accept actual passphrases, PINs, biometrics, password examples, mnemonic examples, passphrase bytes, passphrase hashes, passphrase fingerprints, KDF input/output, provider key material, raw paths, Settings values, provider handles, or storage handles. It does not normalize or encode real passphrases, hash or fingerprint passphrases, run Argon2id/KDF/HKDF/HMAC/AEAD, implement retry/throttle/lockout, implement passphrase UI, implement unlock UI, enable vault unlock, enable persistence, approve provider use, or make a provider selectable.

`SkaldVaultV1MigrationCorruptionBoundary` is separate still-disabled migration/corruption evidence. It exposes typed evidence categories, failure classes, required fail-closed actions, stale-record and rollback-suspicion review, partial-write handling, quarantine-required evidence, manual-review evidence, and redacted failure reporting. It accepts typed policy requests only and does not accept raw persisted storage, ciphertext, plaintext, nonce/tag/header-commitment bytes, KDF output, provider key material, passphrases, raw paths, Settings values, provider handles, storage handles, or database handles. It does not parse real storage, read files, write files, run migration, run migration dry-run, repair storage, quarantine records, recover records, verify AEAD tags, decrypt records, verify real header commitments, rewrite manifests or storage indexes, prove rollback resistance, prove crash recovery, enable vault unlock, enable persistence, approve provider use, or make a provider selectable.

Argon2id calibration policy is documented separately in [`ENCRYPTED_LOCAL_VAULT_ARGON2ID_CALIBRATION.md`](ENCRYPTED_LOCAL_VAULT_ARGON2ID_CALIBRATION.md). It now models the shared 64 MiB / t=3 / p=1 floor, 64-byte output, deterministic candidate selection, about-1-second preferred target, about-2-second acceptable target, floor execution failure, stored-parameter authority, and no silent downgrade as still-disabled evidence. Manual Android Argon2id calibration capture is documented separately in [`ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_CALIBRATION_CAPTURE.md). These inform future parameter policy review only; they do not add provider crypto, run provider-level KATs, approve final production calibration, or make any provider selectable.

Runtime randomness/provider checks are documented separately in [`ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md`](ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md). They prove only test-scope availability of approved randomness paths with small non-secret samples; they do not prove entropy quality, generate production vault material, run provider-level KATs, or make any provider selectable.

The production-provider acceptance contract pins the v1 review direction to Bouncy Castle Argon2id, HKDF-SHA-256 key expansion, HMAC-SHA-256 header commitment over canonical header bytes, Tink XChaCha20-Poly1305, and OS SecureRandom. It also documents the non-key-committing AEAD risk and requires future vault-level header commitment over canonical header fields, deterministic canonical header bytes, separated commitment key material, strict AAD binding to vault and record context, the `unicode-nfc-utf8-no-controls-no-whitespace-v1` passphrase encoding policy, Tink raw-key feasibility review, bounded Argon2id calibration, provider-level deterministic vector KATs, randomized AEAD behavioral KATs, verification-order KATs, stale-record manifest/storage policy, and production implementation tests matching the non-secret canonical header/HKDF/HMAC/AAD vectors before any production provider can be selectable.

The raw-key feasibility review now has test-scope outcomes of `FEASIBLE_PUBLIC_RAW_KEY_API` on desktop/JVM and `ANDROID_FEASIBLE_PUBLIC_RAW_KEY_API` on Android. The probes use fixed non-secret bytes and public Tink APIs to build a transient in-memory primitive from caller-supplied key bytes. The still-disabled record AEAD building block now uses that public API family. It does not persist a Tink keyset, does not generate a Tink vault key, does not use internal APIs, does not make a provider selectable, and does not remove the header-commitment requirement.

## Provider Type Confinement

The provider boundary exposes no:

- Tink types,
- Bouncy Castle types,
- JCA/JCE crypto types,
- BDK types,
- platform keystore/keyring types,
- file or settings storage types,
- network/process/client types.

Tink and Bouncy Castle imports remain confined to platform compile probes, approved still-disabled platform building blocks, and KAT/probe tests. The explicit-nonce Tink API remains KAT/probe-only and is not a production provider boundary. Record AEAD building-block source guards forbid keyset persistence, random Tink vault-key generation, internal Tink APIs, reflection, logging, file writes, storage APIs, and Android wrapping APIs.

## Readiness Alignment

`EncryptedVaultReadinessPolicy` now records that a disabled provider boundary, provider-level KAT contract, still-disabled Argon2id calibration policy building block, still-disabled integrated provider KAT execution, metadata-only still-disabled provider facade, still-disabled in-memory container parser/writer, still-disabled in-memory manifest parser/writer, local manifest-relative stale-record decision policy, and model-only storage/atomicity/secure-storage contracts exist. This does not satisfy production persistence or provider selectability.

The readiness model still blocks on:

- production provider implementation unavailable,
- still-disabled provider integration harness not selectable,
- final KDF parameter approval missing,
- KDF calibration policy implemented as a still-disabled floor/candidate-selection/no-downgrade model with non-final parameter tiers documented in [`ENCRYPTED_LOCAL_VAULT_ARGON2ID_PARAMETER_POLICY.md`](ENCRYPTED_LOCAL_VAULT_ARGON2ID_PARAMETER_POLICY.md),
- AEAD verification incomplete,
- vault container persistence implementation missing,
- stale-record manifest/storage integration unimplemented,
- manifest/storage atomicity and crash recovery unreviewed,
- lock/session lifecycle tests absent,
- redaction tests missing,
- migration/corruption boundary still disabled,
- passphrase policy runtime review missing,
- migration/corruption tests missing,
- secure secret storage disabled,
- secure metadata storage disabled,
- production persistence disabled,
- mainnet disabled.

`VaultCryptoDependencyProbeCatalog` records the Tink plus Bouncy Castle stack as a candidate with the disabled provider boundary, provider-level KAT contract, test-only provider KAT harness, still-disabled integrated provider KAT harness evidence, and still-disabled provider facade evidence. It remains candidate-only and not production-approved because no selectable production provider exists.

The provider dependency build boundary records the same stack as pre-existing platform source-set declarations only. It classifies Tink JVM and Bouncy Castle JVM as source-set-declared build evidence from prior dependency work, not as active, approved, runtime-instantiable, imported, executable, or selectable provider code. Android platform wrapping, OS CSPRNG/platform wrapping, test-only deterministic providers, unknown candidates, and unsupported candidates remain absent, test-only, or fail-closed. Warning-only evidence, user consent, and test-only evidence cannot authorize provider promotion.

`VaultCryptoProviderSelectionRegistry` records the same evidence but treats dependency-level KATs, test-only provider KATs, test-only runtime randomness probes, still-disabled canonical/HKDF/HMAC/AAD/record-AEAD building blocks, the still-disabled integrated provider KAT harness, and the test-scope desktop/Android Tink raw-key feasibility probes as insufficient for production selection. It blocks Tink plus Bouncy Castle on missing selectable production provider implementation, non-final Argon2id parameters, missing bounded-calibration approval, missing runtime compatibility/randomness checks when evidence is unknown, disabled secure storage, disabled secure metadata persistence, missing vault container/storage review, missing manifest/stale-record integration, missing redaction/failure-mode tests, missing migration/corruption tests, and mainnet disablement.

## Explicit Non-Capabilities

This boundary does not enable:

- encrypted vault implementation,
- fake encryption,
- still-disabled provider facade selectability,
- provider-wired Argon2id passphrase KDF execution or final calibration approval,
- production AEAD execution,
- production key generation,
- Tink keyset creation,
- Tink keyset persistence,
- raw key material persistence,
- file-backed vault container read/write,
- passphrase, PIN, biometric, or unlock UI,
- passphrase policy boundary evidence as passphrase acceptance, normalization or encoding execution, hashing, fingerprinting, Argon2id/KDF execution, retry/throttle/lockout implementation, provider readiness, or unlock readiness,
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

## Acceptance Gates Before Provider Implementation

Before any future branch implements provider crypto:

1. Argon2id calibration policy/probe evidence and candidate parameter tiers must be reviewed, and final KDF parameter policy must be approved for Android and Linux desktop. The still-disabled calibration model now enforces the shared floor and no-downgrade/fail-closed rules, but final production approval is still absent. Android compatibility planning must use the supported OS baseline, runtime provider/primitive checks, approved cryptographic randomness checks, and fail-closed vault creation behavior documented in [`ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md`](ENCRYPTED_LOCAL_VAULT_ANDROID_COMPATIBILITY_ENTROPY_POLICY.md) and [`ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md`](ENCRYPTED_LOCAL_VAULT_RUNTIME_RANDOMNESS_PROVIDER_CHECKS.md); low-end and mid-range model testing are no longer hard blockers.
2. The v1 production-provider acceptance contract in [`ENCRYPTED_LOCAL_VAULT_PRODUCTION_PROVIDER_ACCEPTANCE_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_PRODUCTION_PROVIDER_ACCEPTANCE_CONTRACT.md) must be satisfied.
3. Provider-level public KATs must be defined through the Skald-owned interface.
4. Canonical vault header commitment, HKDF-SHA-256 key expansion, HMAC-SHA-256 header commitment, separated commitment key material, strict AAD, and record AEAD must pass still-disabled integrated provider KATs.
5. The `unicode-nfc-utf8-no-controls-no-whitespace-v1` passphrase encoding policy must be implemented and tested without adding vault creation in the same step.
6. Tink raw AEAD key material handling must be approved through public supported APIs, or a separate human-approved alternative must be documented.
7. Split-provider invariants must be reviewed again at implementation level.
8. Redacted error behavior must be tested against failure modes.
9. No provider-specific types may escape the boundary.
10. No vault container or storage success path may be added unless that branch is explicitly scoped and approved.

## Next Step

The next focused branch should remain design/probe-only unless the user explicitly approves executable provider work: review runtime provider/primitive/randomness checks for supported Android and Linux paths or design a disabled production-provider skeleton with no storage, without file-backed vault container read/write or persistence.

## Test-Only Implementation Status

The test-source-only executable provider implementation pass is recorded in [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_EXECUTABLE_PROVIDER_IMPLEMENTATION.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_EXECUTABLE_PROVIDER_IMPLEMENTATION.md). It adds a named test-source provider implementation surface and capability labels only. It does not add a production provider implementation, does not enable provider selection, does not make `productionProviderSelectable` true, and does not accept provider-level public KDF/AEAD KAT execution in that branch.

The follow-on provider-level public KDF/AEAD KAT execution pass is recorded in [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_LEVEL_PUBLIC_KAT_EXECUTION.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_LEVEL_PUBLIC_KAT_EXECUTION.md). It executes only public vectors through the test-source-only provider on desktop and Android test paths. That success is test-source-only KAT evidence and is not provider selection authorization, production provider implementation authorization, vault persistence authorization, secure-storage authorization, secure-metadata authorization, sync authorization, signing/broadcasting authorization, UI authorization, endpoint authorization, or mainnet authorization.

## Provider Operation Authorization Boundary

`SkaldVaultV1ProviderOperationAuthorizationPolicy` is now the Skald-owned authorization model that future executable provider code must satisfy before any provider operation can run. It models provider availability checks, KAT execution, runtime randomness checks, entropy/salt/nonce/key generation, Argon2id/KDF/HKDF/HMAC, header commitment work, AEAD encrypt/decrypt, record encrypt/decrypt, manifest/storage-index authentication, key wrapping, provider clear/dispose, vault create, vault unlock, vault persistence, and mainnet operation categories.

In the current branch every category remains unauthorized. The boundary accepts typed policy evidence only, emits redacted evidence-only blocked results, and records required gates such as non-disabled provider selection, `productionProviderSelectable=true`, production provider implementation, provider KAT approval, dependency probe approval, runtime randomness approval, final KDF calibration, passphrase policy approval, lock/session approval, redaction/leakage approval, clear/wipe approval, persistence/storage safety approval where storage is involved, migration/corruption approval where parsing or migration is involved, secure storage approval, secure metadata approval, BDK persistence-bypass review, network-mode approval, and mainnet release review.

It does not run provider operations, KATs, randomness checks, entropy/salt/nonce/key generation, Argon2id/KDF/HKDF/HMAC, AEAD encrypt/decrypt, header commitment computation or verification against real data, record encrypt/decrypt, manifest/storage-index authentication, key wrapping/unwrapping, or provider clear/dispose. It does not create provider handles, make a provider selectable, approve production provider use, enable vault creation, enable vault unlock, enable vault persistence, or approve mainnet. Provider selection still selects only `DisabledVaultCryptoProvider`, `productionProviderSelectable` remains false, OS keyrings and password managers remain rejected for Skald-managed vault passphrase storage, and passphrase-first remains the default authority.

## Runtime Randomness Authorization Boundary

`SkaldVaultV1RuntimeRandomnessAuthorizationPolicy` now exists as a still-disabled provider-adjacent authorization model. It models future randomness operation kinds, purposes, source kinds, required gates, blockers, warnings, and disabled capabilities for OS CSPRNG, provider randomness, hardware-backed entropy evidence, salts, nonces, key-generation input, KDF salts, AEAD nonces, test-vector scope, production runtime scope, release validation, Android entropy review, Linux entropy review, and mainnet randomness.

Current authorization is blocked/fail-closed for every randomness operation. The boundary does not call `SecureRandom`, Kotlin Random, Java Random, `Math.random`, OS CSPRNG APIs, provider RNG APIs, or randomness health checks. It does not generate entropy, salts, nonces, keys, deterministic vectors, random byte fixtures, provider operations, KATs, Argon2id/KDF/HKDF/HMAC, AEAD, header commitment logic, record crypto, key wrapping, vault creation, vault unlock, vault persistence, provider selection, or mainnet.

Future provider crypto cannot obtain entropy, salt, nonce, or key-generation input until this boundary is satisfied with reviewed OS cryptographic randomness/CSPRNG or reviewed provider randomness. General-purpose PRNGs remain forbidden for vault material, Android hardware-backed key protection remains separate from entropy quality, Linux entropy quality remains a required review gate, provider selection still selects only `DisabledVaultCryptoProvider`, and `productionProviderSelectable` remains false.

## KDF Calibration Authorization Boundary

`SkaldVaultV1KdfCalibrationAuthorizationPolicy` now exists as a still-disabled provider-adjacent authorization model. It models future KDF availability checks, Argon2id calibration requests, parameter finalization, Android/Linux/desktop review, low-memory and high-memory profile review, cost review, parameter approval, test-vector profile review, production runtime review, release validation, mainnet review, and KDF execution authorization.

Current KDF authorization is blocked/fail-closed for every operation. The boundary does not run Argon2id, run KDFs, run calibration, run benchmarks, inspect real host/device details, approve final KDF parameters, normalize or encode real passphrases, generate or consume salts, call randomness APIs, run provider operations, run provider KATs, derive vault keys, enable vault unlock, enable persistence, make a provider selectable, or approve mainnet.

Future provider crypto cannot run KDF work until provider operation authorization, runtime randomness authorization for salts, passphrase policy, clear/wipe policy, redaction/leakage policy, lock/session lifecycle, persistence readiness where needed, secure storage, secure metadata, Android/Linux calibration review, DoS/UX thresholds, migration compatibility where needed, and release review are satisfied. Android and Linux calibration remain future-reviewed only, test-vector profiles do not authorize production runtime, provider selection still selects only `DisabledVaultCryptoProvider`, and `productionProviderSelectable` remains false.

## Secure-Storage Authorization Boundary

`SkaldVaultV1SecureStorageAuthorizationPolicy` now exists as a still-disabled provider-adjacent authorization model. It models future secure-storage availability, secure metadata availability, secret storage, secret retrieval, deletion, rotation, key wrapping, key unwrapping, wrapped-key storage, sensitive metadata storage, manifest/recovery/session-adjacent metadata storage, backup/export/import, secure-storage migration, secure-storage purge, OS keyring targets, password-manager targets, Android wrapper targets, encrypted local vault targets, Settings targets, plaintext targets, test-only targets, production runtime, release validation, and mainnet secure-storage requests.

Current secure-storage authorization is blocked/fail-closed for every operation. The boundary does not store or retrieve secrets or metadata, wrap or unwrap keys, store wrapped keys, export or import backup material, migrate or purge secure storage, use Android Keystore, Android Credential Manager, OS keyrings, password managers, SharedPreferences, Settings storage, files, databases, or encrypted vault storage, run provider operations, run KDF/HKDF/HMAC/AEAD, call randomness APIs, enable unlock, enable persistence, make a provider selectable, or approve mainnet.

Future provider crypto cannot store provider material, wrapped keys, KDF-derived material, metadata keys, manifest metadata, recovery metadata, or session-adjacent state until secure-storage authorization, persistence readiness, storage service approval, storage safety, platform root/path safety, provider operation authorization, runtime randomness authorization, KDF calibration authorization, passphrase policy, lock/session lifecycle, redaction/leakage, clear/wipe, migration/corruption, Android wrapper review where used, Linux optional wrapper review where used, and release review are approved. OS keyrings remain rejected as primary storage, password managers remain rejected for Skald-managed vault passphrase storage, Settings/preferences remain rejected for secrets and sensitive metadata, provider selection still selects only `DisabledVaultCryptoProvider`, and `productionProviderSelectable` remains false.

## Vault Unlock Authorization Boundary

`SkaldVaultV1UnlockAuthorizationPolicy` now exists as a still-disabled provider-adjacent authorization model. It models future unlock availability checks, passphrase/PIN/biometric/hardware-wrapper/OS-keyring/password-manager assisted unlock attempts, restore/import/migration/recovery unlock attempts, test-only and release-validation unlock attempts, production runtime and mainnet unlock attempts, active-session creation/refresh/resume/close/failure-cleanup/status requests, purposes, credential classes, required gates, blockers, warnings, disabled capabilities, and redacted policy tokens.

Current unlock authorization is blocked/fail-closed for every operation. The boundary does not accept passphrases, PINs, or biometrics; normalize or encode passphrases; run Argon2id/KDF/HKDF/HMAC/AEAD; generate or consume salts, nonces, or random bytes; call provider operations; read secure storage, metadata storage, or encrypted vault storage; retrieve wrapped keys; unwrap keys; decrypt records; create active sessions; hold decrypted key material; persist unlock state; add UI; enable vault creation, vault unlock, vault persistence, make a provider selectable, or approve mainnet.

Future provider crypto cannot participate in unlock until passphrase policy, KDF calibration authorization, final KDF parameters, runtime randomness authorization, provider operation authorization, non-disabled provider selection, provider KATs, secure-storage authorization, secure secret storage, secure metadata storage, encrypted local vault storage, storage safety preflight, storage service operations, migration/corruption policy, clear/wipe strategy, lock/session lifecycle, redaction/leakage, platform lifecycle policy, wrapper review where used, persistence readiness, and release/mainnet review gates are approved. OS keyrings remain rejected as primary storage and for Skald-managed passphrase storage, password managers remain rejected for Skald-managed passphrase storage, Settings/preferences storage remains rejected for secrets and unlock state, provider selection still selects only `DisabledVaultCryptoProvider`, and `productionProviderSelectable` remains false.

## Vault Creation Authorization Boundary

`SkaldVaultV1CreationAuthorizationPolicy` now exists as a still-disabled provider-adjacent authorization model. It models future creation availability checks, new vault creation, initial passphrase, initial key material, salt/nonce, KDF execution, provider operation, header, header commitment, container, manifest, storage-index, record, secure metadata, wrapped-key storage, storage namespace, storage safety, initial persistence commit, post-create unlock/session, rollback/failure cleanup, test-only simulation, release validation, production runtime, and mainnet creation requests.

Current creation authorization is blocked/fail-closed for every operation. The boundary does not accept passphrases, PINs, or biometrics; normalize or encode passphrases; generate salts, nonces, keys, container ids, record ids, or metadata ids; run Argon2id/KDF/HKDF/HMAC/AEAD; call provider operations; create headers, header commitments, containers, manifests, storage indexes, records, secure metadata, wrapped keys, storage namespaces, persistence commits, rollback handlers, failure cleanup, or active sessions; write secure storage, metadata storage, encrypted vault storage, Settings, files, or databases; add UI; enable vault creation, vault unlock, vault persistence, make a provider selectable, or approve mainnet.

Future provider crypto cannot participate in vault creation until passphrase policy, KDF calibration authorization, final KDF parameters, runtime randomness authorization, provider operation authorization, non-disabled provider selection, provider KATs, secure-storage authorization, secure secret storage, secure metadata storage, encrypted local vault storage, storage safety preflight, storage service operations, storage namespace/path safety, header commitment/AAD approval, manifest/storage-index/record initial creation approval, atomic write/crash recovery approval, migration/corruption policy, clear/wipe strategy, lock/session lifecycle, redaction/leakage, persistence readiness, and release/mainnet review gates are approved. OS keyrings remain rejected as primary storage and for Skald-managed passphrase storage, password managers remain rejected for Skald-managed passphrase storage, Settings/preferences storage remains rejected for secrets, creation state, and unlock state, provider selection still selects only `DisabledVaultCryptoProvider`, and `productionProviderSelectable` remains false.

## Repository reconciliation status (2026-07-12)

The [`v1 format/parser/crypto reconciliation audit`](ENCRYPTED_LOCAL_VAULT_V1_FORMAT_PARSER_CRYPTO_RECONCILIATION_AUDIT.md) confirms disabled provider selection while documenting that earlier production-compiled Argon2id, HKDF/HMAC, record-AEAD, and KAT paths call platform primitives directly without this provider/selection boundary. No app integration calls those paths. Whether direct crypto remains permissible requires human decision.
