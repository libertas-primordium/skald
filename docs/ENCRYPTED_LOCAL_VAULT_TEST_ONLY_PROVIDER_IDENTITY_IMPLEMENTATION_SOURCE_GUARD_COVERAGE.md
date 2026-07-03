# Encrypted Local Vault: Test-Only Provider Identity Implementation Source-Guard Coverage

This document records still-disabled, model-only source-guard coverage evidence for the Skald Vault v1 test-only provider identity implementation evidence chain.

The identity decision, identity isolation guard, synthetic namespace contract, source-set confinement boundary, implementation decision gate, prerequisite audit, scope decision, implementation contract, readiness gate, runtime linkage guard, promotion blockers, and source-guard coverage are separate boundaries. Earlier boundaries classify labels, keep them contained, constrain placement, block implementation approval, audit prerequisites, define future-review scope, record contract requirements, compose readiness evidence, prove runtime bridges are absent, and block promotion. Source-guard coverage separately records that those model-only boundaries are covered by guard/scanning evidence. Coverage evidence does not implement a provider identity and does not authorize provider selection.

Provider selection remains fail-closed to `DisabledVaultCryptoProvider`, and `productionProviderSelectable` remains false.

## Modeled Boundaries

Modeled identity implementation boundaries are `TestOnlyProviderIdentityDecision`, `TestOnlyProviderIdentityIsolationGuard`, `TestOnlyProviderSyntheticIdentityNamespace`, `TestOnlyProviderIdentitySourceSetConfinement`, `TestOnlyProviderIdentityImplementationDecision`, `TestOnlyProviderIdentityImplementationPrerequisiteAudit`, `TestOnlyProviderIdentityImplementationScopeDecision`, `TestOnlyProviderIdentityImplementationContract`, `TestOnlyProviderIdentityImplementationReadinessGate`, `TestOnlyProviderIdentityImplementationRuntimeLinkageGuard`, `TestOnlyProviderIdentityImplementationPromotionBlockers`, and `TestOnlyProviderIdentityImplementationSourceGuardCoverage`.

Each boundary is represented as a safe enum identifier in the model. The common model does not carry repository-relative paths or source-location payloads.

## Coverage Categories

Guard coverage categories are `ModelOnlyBoundaryCoverage`, `IdentityImplementationChainCoverage`, `DesktopSourceGuardCoverage`, `ProductionRuntimeRootCoverage`, `ProviderSelectionRootCoverage`, `RegistryFactoryDispatcherRootCoverage`, `ExecutorTargetRootCoverage`, `BackendBdkRootCoverage`, `SettingsUiRootCoverage`, `AndroidDesktopRuntimeRootCoverage`, `PositiveFlagScanCoverage`, `MaterialScanCoverage`, `ForbiddenImportScanCoverage`, `RuntimeHookScanCoverage`, `PromotionFlagScanCoverage`, and `DocumentationCrossLinkCoverage`.

Coverage categories are evidence only. Source guard passing, scans, coverage evidence, absence evidence, and documentation cross-links are non-authorizing.

## Forbidden Runtime Roots

Forbidden runtime roots are `AndroidMain`, `DesktopMain`, `CommonMainUi`, `CommonMainSettings`, `CommonMainDomain`, `ProviderSelectionSource`, `ProviderRegistrySource`, `ProviderFactorySource`, `ProviderDispatcherSource`, `BackendSource`, `BdkSource`, and `ProductionAcceptanceSource`.

Those roots must remain free of implementation hooks, provider identity fixtures, provider-selection aliases, registry/factory/dispatcher reachability, executor targets, runtime bridge flags, promotion flags, settings persistence, UI surfaces, backend clients, BDK wallet state, signing/broadcasting, public endpoint defaults, and mainnet enablement.

## Forbidden Pattern Classes

Forbidden pattern classes are `ProviderIdentityImplementationFixture`, `TestOnlyIdentityImplementationFixture`, `ProductionIdentityImplementationFixture`, `PositiveImplementationFlag`, `PositivePromotionFlag`, `PositiveRuntimeBridgeFlag`, `PositiveProviderSelectionFlag`, `NonDisabledRegistryEntry`, `FactoryReachabilityHook`, `DispatcherReachabilityHook`, `ExecutorTargetHook`, `ProviderKatExecutorHook`, `ProviderOperationHook`, `CryptoExecutionHook`, `VaultPersistenceHook`, `ProductionSyncHook`, `SettingsPersistenceHook`, `UiEntryPointHook`, `BackendClientHook`, `BdkWalletStateHook`, `SigningBroadcastingHook`, `PublicEndpointDefaultHook`, `MainnetHook`, `RawMaterialPayload`, `WalletFixture`, and `SecretLookingFixture`.

## Forbidden Promotion Paths

Source-guard coverage cannot promote into provider implementation, test-only provider identity implementation, production provider identity implementation, provider selection, `productionProviderSelectable=true`, registry entries, factories, dispatchers, executor targets, provider KAT executors, provider operations, randomness, KDF/HKDF/HMAC/AEAD, key generation, keyset storage, vault creation, vault unlock, vault sessions, vault persistence, secure storage success, secure metadata success, manifest read/write, migration, production sync, backend clients, BDK wallet state, settings codecs, UI surfaces, signing, broadcasting, Tor transport, Nostr parsing, public endpoint defaults, or mainnet.

## Non-Authorizing Evidence

Prior evidence, source guard coverage, runtime-root coverage, forbidden-import scans, material scans, positive-flag scans, runtime-hook scans, promotion-flag scans, redaction evidence, absence evidence, contract evidence, readiness evidence, runtime-linkage evidence, promotion-blocker evidence, documentation, warning-only evidence, user consent, release claims, and public vector/KAT evidence are all non-authorizing.

## Non-Goals Preserved

This pass implements no provider, executor, KAT execution, crypto operation, storage operation, wallet operation, settings persistence, UI entry point, backend client, signing or broadcasting path, Tor transport, Nostr parsing, public endpoint, or mainnet behavior.

Skald constraints remain intact: no Skald-operated infrastructure, no Skald-managed default backend, no real funds, no secrets, no live wallet data, no production crypto provider, no production vault persistence, no signing/broadcasting, and mainnet remains disabled.
