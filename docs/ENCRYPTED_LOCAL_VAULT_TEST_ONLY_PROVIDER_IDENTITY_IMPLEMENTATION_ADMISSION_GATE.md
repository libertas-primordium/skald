# Encrypted Local Vault: Test-Only Provider Identity Implementation Admission Gate

This document records the still-disabled, model-only admission gate for the Skald Vault v1 test-only provider identity implementation evidence chain.

The identity decision, identity isolation guard, synthetic namespace contract, source-set confinement boundary, implementation decision gate, prerequisite audit, scope decision, implementation contract, readiness gate, runtime linkage guard, promotion blockers, source-guard coverage, redaction guard, and admission gate are separate boundaries. Earlier boundaries classify labels, contain them, constrain placement, block implementation approval, audit prerequisites, define future-review scope, record contract requirements, compose readiness evidence, prove runtime bridges are absent, block promotion, record source-guard coverage, and model safe outputs. This gate separately composes that chain into a current admission decision.

Admission gate evidence denies current implementation admission. It does not implement a provider identity and does not authorize provider selection. Provider selection remains fail-closed to `DisabledVaultCryptoProvider`, and `productionProviderSelectable` remains false.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_PLAN.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_PLAN.md) records the next non-executable planning boundary; admission evidence remains non-authorizing and does not admit implementation.

## Admission Sections

Admission sections are `ModelOnlyAdmission`, `PriorEvidenceAdmission`, `DecisionGateAdmission`, `PrerequisiteAuditAdmission`, `ScopeDecisionAdmission`, `ImplementationContractAdmission`, `ReadinessGateAdmission`, `RuntimeLinkageAdmission`, `PromotionBlockerAdmission`, `SourceGuardCoverageAdmission`, `RedactionGuardAdmission`, `ProviderSelectionAdmission`, `ProductionPromotionAdmission`, `VaultPersistenceAdmission`, `SyncSigningBroadcastingAdmission`, and `MainnetAdmission`.

## Admission Dependencies

Admission dependencies are `IdentityDecisionBoundary`, `IdentityIsolationGuard`, `SyntheticIdentityNamespaceContract`, `IdentitySourceSetConfinementBoundary`, `ImplementationDecisionGate`, `ImplementationPrerequisiteAudit`, `ImplementationScopeDecision`, `ImplementationContract`, `ImplementationReadinessGate`, `ImplementationRuntimeLinkageGuard`, `ImplementationPromotionBlockers`, `ImplementationSourceGuardCoverage`, `ImplementationRedactionGuard`, `ProviderSelectionFailClosedBoundary`, `DisabledProviderBoundary`, `VaultRedactionLeakageBoundary`, `SourceGuardCoverage`, and `ProductionProviderAcceptanceContract`.

Dependency presence is model evidence only. It is not approval to add implementation or runtime reachability.

## Admission Checks

Admission checks are `CurrentBranchModelOnly`, `CurrentBranchStillDisabled`, `PriorIdentityDecisionEvidenceIncluded`, `PriorIdentityIsolationEvidenceIncluded`, `PriorSyntheticNamespaceEvidenceIncluded`, `PriorSourceSetConfinementEvidenceIncluded`, `PriorImplementationDecisionEvidenceIncluded`, `PriorPrerequisiteAuditEvidenceIncluded`, `PriorScopeDecisionEvidenceIncluded`, `PriorImplementationContractEvidenceIncluded`, `PriorReadinessGateEvidenceIncluded`, `PriorRuntimeLinkageGuardEvidenceIncluded`, `PriorPromotionBlockersEvidenceIncluded`, `PriorSourceGuardCoverageEvidenceIncluded`, `PriorRedactionGuardEvidenceIncluded`, `AllPriorEvidenceNonAuthorizing`, `NoFutureBranchApproval`, `NoImplementationAdmission`, `NoTestOnlyIdentityImplementation`, `NoProductionIdentityImplementation`, `ProviderSelectionDisabledProviderOnly`, `ProductionProviderSelectableFalse`, `NoRegistryFactoryDispatcherReachability`, `NoExecutorTargetReachability`, `NoProviderKatExecutorReachability`, `NoProviderOperationExecution`, `NoCryptoExecution`, `NoVaultLifecycleExecution`, `NoVaultPersistence`, `NoSecureStorageSuccess`, `NoSecureMetadataSuccess`, `NoProductionSync`, `NoBackendClient`, `NoBdkWalletState`, `NoSettingsPersistence`, `NoUiSurface`, `NoSigningBroadcasting`, `NoTorNostrPublicEndpoint`, `MainnetDisabled`, `NoLeakageOutputs`, `NoPositiveRuntimeFlags`, and `NoPositivePromotionFlags`.

The current outcomes are `CurrentAdmissionDenied`, `AdmissionReviewRequired`, `DependenciesIncomplete`, `ImplementationNotAuthorized`, `ProductionPromotionNotAuthorized`, and `MainnetNotAuthorized`.

## Forbidden Shortcuts

Forbidden shortcuts are `AdmissionGateImpliesImplementation`, `AdmissionGateImpliesFutureBranchApproval`, `AdmissionGateImpliesProviderSelection`, `AdmissionGateImpliesProductionProviderSelectable`, `AdmissionGateImpliesRuntimeLinkage`, `AdmissionGateImpliesPromotion`, `PriorEvidenceImpliesAdmission`, `SourceGuardCoverageImpliesAdmission`, `RedactionGuardImpliesAdmission`, `ReadinessGateImpliesAdmission`, `RuntimeLinkageGuardImpliesAdmission`, `PromotionBlockersImpliesAdmission`, `UserConsentImpliesAdmission`, `WarningOnlyEvidenceImpliesAdmission`, `TestOnlyEvidenceImpliesAdmission`, `ReleaseClaimImpliesAdmission`, `DocumentationImpliesAdmission`, `PublicVectorEvidenceImpliesAdmission`, `KATHarnessEvidenceImpliesAdmission`, `SafeLabelSyntaxImpliesAdmission`, `AbsenceEvidenceImpliesAdmission`, and `FutureReviewLabelImpliesAdmission`.

## Forbidden Targets

Forbidden targets are `TestOnlyProviderIdentityImplementationNow`, `ProductionProviderIdentityImplementation`, `VaultCryptoProviderImplementation`, `ProviderSelectionEntry`, `ProductionProviderSelectableTrue`, `NonDisabledRegistryEntry`, `ProviderFactoryEntry`, `ProviderDispatcherEntry`, `ExecutorTarget`, `ProviderKatExecutor`, `ProviderOperationExecution`, `RuntimeRandomnessExecution`, `KdfExecution`, `HkdfExecution`, `HmacExecution`, `AeadExecution`, `KeyGeneration`, `KeysetStorage`, `VaultCreation`, `VaultUnlock`, `VaultSession`, `VaultPersistence`, `SecureStorageSuccess`, `SecureMetadataStorageSuccess`, `ManifestReadWrite`, `Migration`, `ProductionSync`, `BackendClient`, `BdkWalletState`, `SettingsCodecPersistence`, `UiSurface`, `Signing`, `Broadcasting`, `TorTransport`, `NostrParsing`, `PublicEndpointDefault`, and `Mainnet`.

## Forbidden Promotion Paths

Admission gate evidence cannot promote into provider implementation, test-only provider identity implementation, production provider identity implementation, provider selection, `productionProviderSelectable=true`, registry entries, factories, dispatchers, executor targets, provider KAT executors, provider operations, randomness, KDF/HKDF/HMAC/AEAD, key generation, keyset storage, vault creation, vault unlock, vault sessions, vault persistence, secure storage success, secure metadata success, manifest read/write, migration, production sync, backend clients, BDK wallet state, settings codecs, UI surfaces, signing, broadcasting, Tor transport, Nostr parsing, public endpoint defaults, or mainnet.

## Non-Authorizing Evidence

All prior evidence, source guard passing, scans, coverage evidence, redaction evidence, absence evidence, safe-output evidence, doc cross-links, documentation, warning-only evidence, user consent, release claims, and public vector/KAT evidence are non-authorizing. Admission evidence is also non-authorizing: it records denial, not approval.

## Non-Goals Preserved

This pass implements no provider, executor, KAT execution, crypto operation, storage operation, wallet operation, settings persistence, UI entry point, backend client, signing or broadcasting path, Tor transport, Nostr parsing, public endpoint, or mainnet behavior.

Skald constraints remain intact: no Skald-operated infrastructure, no Skald-managed default backend, no real funds, no secrets, no live wallet data, no production crypto provider, no production vault persistence, no signing/broadcasting, and mainnet remains disabled.
