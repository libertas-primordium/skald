# Encrypted Local Vault: Test-Only Provider Identity Implementation Promotion Blockers

This document records the still-disabled, model-only Skald Vault v1 promotion blockers for any future branch that may request review to introduce a test-only provider identity implementation.

The identity decision, identity isolation guard, synthetic namespace contract, source-set confinement boundary, implementation decision gate, prerequisite audit, scope decision, implementation contract, readiness gate, runtime linkage guard, and promotion blockers are separate boundaries. The earlier boundaries classify labels, keep them contained, constrain placement, block implementation approval, audit prerequisites, define future-review scope, record contract requirements, compose readiness evidence, and prove current runtime bridges are absent. These promotion blockers separately prove that none of that evidence can promote into production identity, provider selection, runtime reachability, vault persistence, production sync, public endpoints, or mainnet.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_SOURCE_GUARD_COVERAGE.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_SOURCE_GUARD_COVERAGE.md) records the next separate still-disabled source-guard coverage boundary; promotion-blocker evidence and guard coverage remain non-authorizing.

Current promotion blocker evidence does not implement a provider identity, does not instantiate a provider, and does not authorize provider selection. Provider selection remains fail-closed to `DisabledVaultCryptoProvider`, and `productionProviderSelectable` remains false.

## Promotion Stages

Modeled stages are `ModelEvidenceToImplementation`, `ImplementationToTestOnlyIdentity`, `TestOnlyIdentityToProviderSelection`, `TestOnlyIdentityToRegistryEntry`, `TestOnlyIdentityToProviderFactory`, `TestOnlyIdentityToProviderDispatcher`, `TestOnlyIdentityToExecutorTarget`, `TestOnlyIdentityToProviderKatExecutor`, `TestOnlyIdentityToProviderOperation`, `TestOnlyIdentityToProductionIdentity`, `ProductionIdentityToProductionProvider`, `ProductionProviderToProductionProviderSelectable`, `ProductionProviderSelectableToVaultPersistence`, `VaultPersistenceToProductionSync`, `ProductionSyncToSigning`, `SigningToBroadcasting`, `BroadcastingToMainnet`, `EvidenceToPublicEndpointDefault`, `EvidenceToUiSurface`, `EvidenceToBackendClient`, `EvidenceToBdkWalletState`, and `EvidenceToSettingsCodec`.

Every current promotion stage is blocked and non-authorizing.

## Forbidden Promotion Sources

Forbidden sources are `IdentityDecisionEvidence`, `IdentityIsolationEvidence`, `SyntheticNamespaceEvidence`, `SourceSetConfinementEvidence`, `ImplementationDecisionEvidence`, `PrerequisiteAuditEvidence`, `ScopeDecisionEvidence`, `ImplementationContractEvidence`, `ReadinessGateEvidence`, `RuntimeLinkageGuardEvidence`, `DocumentationEvidence`, `PublicVectorEvidence`, `TestOnlyKatEvidence`, `SourceGuardEvidence`, `RedactionEvidence`, `AbsenceEvidence`, `UserConsent`, `WarningOnlyEvidence`, `ReleaseClaim`, `FutureBranchName`, `FutureReviewLabel`, `SafeIdSyntaxAcceptance`, `DisabledProviderSelection`, and `DisabledProviderRuntime`.

These sources may be evidence of blocked containment only. They cannot become implementation approval, production promotion, provider selection, or `productionProviderSelectable=true`.

## Forbidden Promotion Targets

Forbidden targets are `TestOnlyProviderIdentityImplementationNow`, `ProductionProviderIdentityImplementation`, `VaultCryptoProviderImplementation`, `ProviderSelectionEntry`, `NonDisabledRegistryEntry`, `ProviderFactoryEntry`, `ProviderDispatcherEntry`, `ExecutorTarget`, `ProviderKatExecutor`, `ProviderOperationExecution`, `RuntimeRandomnessExecution`, `KdfExecution`, `HkdfExecution`, `HmacExecution`, `AeadExecution`, `KeyGeneration`, `KeysetStorage`, `VaultCreation`, `VaultUnlock`, `VaultSession`, `VaultPersistence`, `SecureStorageSuccess`, `SecureMetadataStorageSuccess`, `ManifestReadWrite`, `Migration`, `ProductionSync`, `BackendClient`, `BdkWalletState`, `SettingsCodecPersistence`, `UiSurface`, `Signing`, `Broadcasting`, `TorTransport`, `NostrParsing`, `PublicEndpointDefault`, and `Mainnet`.

## Forbidden Promotion Paths

Forbidden paths are `PromotionBlockersToProviderImplementation`, `PromotionBlockersToTestOnlyProviderIdentityImplementation`, `PromotionBlockersToProductionProviderIdentityImplementation`, `PromotionBlockersToProviderSelection`, `PromotionBlockersToProductionProviderSelectable`, `PromotionBlockersToRegistryEntry`, `PromotionBlockersToFactory`, `PromotionBlockersToDispatcher`, `PromotionBlockersToExecutorTarget`, `PromotionBlockersToProviderKatExecutor`, `PromotionBlockersToProviderOperation`, `PromotionBlockersToRuntimeRandomness`, `PromotionBlockersToKdf`, `PromotionBlockersToHkdf`, `PromotionBlockersToHmac`, `PromotionBlockersToAead`, `PromotionBlockersToKeyGeneration`, `PromotionBlockersToKeysetStorage`, `PromotionBlockersToVaultCreation`, `PromotionBlockersToVaultUnlock`, `PromotionBlockersToVaultSession`, `PromotionBlockersToVaultPersistence`, `PromotionBlockersToSecureStorageSuccess`, `PromotionBlockersToSecureMetadataSuccess`, `PromotionBlockersToManifestReadWrite`, `PromotionBlockersToMigration`, `PromotionBlockersToProductionSync`, `PromotionBlockersToBackendClient`, `PromotionBlockersToBdkWalletState`, `PromotionBlockersToSettingsCodec`, `PromotionBlockersToUiSurface`, `PromotionBlockersToSigning`, `PromotionBlockersToBroadcasting`, `PromotionBlockersToTorTransport`, `PromotionBlockersToNostrParsing`, `PromotionBlockersToPublicEndpointDefault`, and `PromotionBlockersToMainnet`.

## Non-Authorizing Evidence

Prior evidence, source guard coverage, redaction evidence, absence evidence, contract evidence, readiness evidence, runtime-linkage evidence, promotion-blocker evidence, documentation, warning-only evidence, user consent, release claims, and public vector/KAT evidence are all non-authorizing. Negative absence evidence proves only that current paths remain absent; it is not permission to add those paths.

## Non-Goals Preserved

This pass implements no provider, executor, KAT execution, crypto operation, storage operation, wallet operation, settings persistence, UI entry point, backend client, signing or broadcasting path, Tor transport, Nostr parsing, public endpoint, or mainnet behavior.

Skald constraints remain intact: no Skald-operated infrastructure, no Skald-managed default backend, no real funds, no secrets, no live wallet data, no production crypto provider, no production vault persistence, no signing/broadcasting, and mainnet remains disabled.
