# Encrypted Local Vault: Test-Only Provider Identity Implementation Redaction Guard

This document records the still-disabled, model-only redaction guard for the Skald Vault v1 test-only provider identity implementation evidence chain.

The identity decision, identity isolation guard, synthetic namespace contract, source-set confinement boundary, implementation decision gate, prerequisite audit, scope decision, implementation contract, readiness gate, runtime linkage guard, promotion blockers, source-guard coverage, and redaction guard are separate boundaries. Earlier boundaries classify labels, contain them, constrain placement, block implementation approval, audit prerequisites, define future-review scope, record contract requirements, compose readiness evidence, prove runtime bridges are absent, block promotion, and record source-guard coverage. This guard separately models safe-output requirements. Redaction guard evidence does not implement a provider identity and does not authorize provider selection.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_ADMISSION_GATE.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_ADMISSION_GATE.md) records the final current model-only admission boundary; redaction evidence and safe-output evidence remain non-authorizing and do not admit implementation.

Provider selection remains fail-closed to `DisabledVaultCryptoProvider`, and `productionProviderSelectable` remains false.

## Redaction Surfaces

Modeled redaction surfaces are `PolicyIdOutput`, `StatusOutput`, `OutcomeOutput`, `BoundaryNameOutput`, `EvidenceSourceOutput`, `RowLabelOutput`, `SafeDecisionSummaryOutput`, `SourceGuardSummaryOutput`, `FutureReviewSummaryOutput`, `BlockerSummaryOutput`, `TestAssertionOutput`, `DocumentationOutput`, and `BuildHistoryOutput`.

## Sensitive Reference Classes

Sensitive reference classes are `CandidateIdentityId`, `SyntheticSafeId`, `SourceSetReference`, `PlacementReference`, `ProviderReference`, `ProviderHandle`, `CryptoObjectReference`, `StorageReference`, `StoragePathReference`, `BackendReference`, `EndpointReference`, `WalletReference`, `DescriptorReference`, `CredentialReference`, `SourceLocationReference`, `FutureApprovalReference`, `ImplementationPayloadReference`, `PrerequisitePayloadReference`, `ScopePayloadReference`, `ContractPayloadReference`, `ReadinessPayloadReference`, `RuntimeLinkagePayloadReference`, `PromotionPayloadReference`, `SourceGuardPayloadReference`, `RawMaterialReference`, `SecretLookingReference`, and `WalletFixtureReference`.

These are safe enum identifiers only. Current evidence does not carry raw references or payload fields.

## Allowed Output Classes

Allowed output classes are `PolicyIdOnly`, `PolicyVersionOnly`, `EnumNameOnly`, `SafeLabelOnly`, `RedactedLabelOnly`, `BooleanEvidenceOnly`, `CountEvidenceOnly`, `NonAuthorizingSummaryOnly`, `BlockedStatusOnly`, `FutureReviewRequiredOnly`, `NoCurrentImplementationOnly`, `NoRuntimeLinkageOnly`, `NoPromotionOnly`, `SourceGuardCoverageSummaryOnly`, and `DocumentationCrossLinkOnly`.

Allowed outputs are non-authorizing. Safe output evidence does not imply implementation approval.

## Forbidden Output Classes

Forbidden output classes are `RawCandidateIdentityId`, `RawSyntheticSafeId`, `RawSourceSetPath`, `RawProviderHandle`, `RawCryptoObject`, `RawStoragePath`, `RawBackendEndpoint`, `RawWalletDescriptor`, `RawWalletCredential`, `RawSourceLocation`, `RawFutureApprovalPayload`, `RawImplementationPayload`, `RawPrerequisitePayload`, `RawScopePayload`, `RawContractPayload`, `RawReadinessPayload`, `RawRuntimeLinkagePayload`, `RawPromotionPayload`, `RawSourceGuardPayload`, `RawSecretMaterial`, `RawWalletFixture`, `RawDiagnosticPayload`, `HashOfSecretMaterial`, `FingerprintOfSecretMaterial`, `CrashReportPayload`, `AnalyticsPayload`, and `SupportExportPayload`.

## Forbidden Leakage Paths

Forbidden leakage paths are `RedactionGuardToProviderSelectionDiagnostics`, `RedactionGuardToProviderRegistryDiagnostics`, `RedactionGuardToProviderFactoryDiagnostics`, `RedactionGuardToProviderDispatcherDiagnostics`, `RedactionGuardToExecutorTargetDiagnostics`, `RedactionGuardToProviderKatExecutorDiagnostics`, `RedactionGuardToProviderOperationDiagnostics`, `RedactionGuardToCryptoDiagnostics`, `RedactionGuardToVaultLifecycleDiagnostics`, `RedactionGuardToPersistenceDiagnostics`, `RedactionGuardToSecureStorageDiagnostics`, `RedactionGuardToSecureMetadataDiagnostics`, `RedactionGuardToBackendDiagnostics`, `RedactionGuardToBdkDiagnostics`, `RedactionGuardToSettingsDiagnostics`, `RedactionGuardToUiDiagnostics`, `RedactionGuardToSigningDiagnostics`, `RedactionGuardToBroadcastingDiagnostics`, `RedactionGuardToTorDiagnostics`, `RedactionGuardToNostrDiagnostics`, `RedactionGuardToPublicEndpointDiagnostics`, `RedactionGuardToMainnetDiagnostics`, `RedactionGuardToCrashReport`, `RedactionGuardToAnalytics`, `RedactionGuardToSupportExport`, `RedactionGuardToBuildLogMaterial`, and `RedactionGuardToTestFailureMaterial`.

All leakage booleans are false in current evidence.

## Forbidden Promotion Paths

Redaction guard evidence cannot promote into provider implementation, test-only provider identity implementation, production provider identity implementation, provider selection, `productionProviderSelectable=true`, registry entries, factories, dispatchers, executor targets, provider KAT executors, provider operations, randomness, KDF/HKDF/HMAC/AEAD, key generation, keyset storage, vault creation, vault unlock, vault sessions, vault persistence, secure storage success, secure metadata success, manifest read/write, migration, production sync, backend clients, BDK wallet state, settings codecs, UI surfaces, signing, broadcasting, Tor transport, Nostr parsing, public endpoint defaults, or mainnet.

## Non-Authorizing Evidence

Safe output evidence, redaction evidence, source guard passing, scans, coverage evidence, absence evidence, doc cross-links, prior identity evidence, contract evidence, readiness evidence, runtime-linkage evidence, promotion-blocker evidence, documentation, warning-only evidence, user consent, release claims, and public vector/KAT evidence are all non-authorizing.

## Non-Goals Preserved

This pass implements no provider, executor, KAT execution, crypto operation, storage operation, wallet operation, settings persistence, UI entry point, backend client, signing or broadcasting path, Tor transport, Nostr parsing, public endpoint, or mainnet behavior.

Skald constraints remain intact: no Skald-operated infrastructure, no Skald-managed default backend, no real funds, no secrets, no live wallet data, no production crypto provider, no production vault persistence, no signing/broadcasting, and mainnet remains disabled.
