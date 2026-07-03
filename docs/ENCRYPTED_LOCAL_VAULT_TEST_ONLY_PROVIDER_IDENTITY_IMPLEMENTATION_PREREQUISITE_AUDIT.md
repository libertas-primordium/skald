# Encrypted Local Vault: Test-Only Provider Identity Implementation Prerequisite Audit

This document records the still-disabled, model-only Skald Vault v1 prerequisite audit for any future test-only provider identity implementation.

The identity decision, identity isolation guard, synthetic identity namespace contract, source-set confinement boundary, implementation decision gate, and prerequisite audit are separate boundaries. The identity decision classifies future categories. The isolation guard keeps them label-only and unreachable. The namespace contract constrains safe labels. The source-set confinement boundary constrains placement. The implementation decision gate says current implementation is not authorized. This prerequisite audit enumerates the evidence a later branch would need before implementation could even be considered. The current audit remains blocked and non-authorizing.

## Current State

The prerequisite audit is modeled and still disabled. Current outcomes are `CurrentAuditBlocked`, `PrerequisitesIncomplete`, `FutureBranchReviewRequired`, `ImplementationNotAuthorized`, `ProductionPromotionNotAuthorized`, and `MainnetNotAuthorized`.

This evidence does not implement a provider identity and does not authorize provider selection. Provider selection remains disabled-provider-only through `DisabledVaultCryptoProvider`, and `productionProviderSelectable` remains false.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_SCOPE_DECISION.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_SCOPE_DECISION.md) records the next separate still-disabled scope boundary; prerequisite audit evidence remains non-authorizing.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_CONTRACT.md) records the next separate still-disabled contract boundary; prerequisite audit evidence remains non-authorizing.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_READINESS_GATE.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_READINESS_GATE.md) records the next separate still-disabled readiness boundary; prerequisite audit evidence remains non-authorizing.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_RUNTIME_LINKAGE_GUARD.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_RUNTIME_LINKAGE_GUARD.md) records the separate still-disabled runtime containment boundary; prerequisite audit evidence remains disconnected from runtime provider surfaces.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_PROMOTION_BLOCKERS.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_PROMOTION_BLOCKERS.md) records the separate still-disabled promotion containment boundary; prerequisite audit evidence cannot promote into implementation, provider selection, production identity, or mainnet.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_SOURCE_GUARD_COVERAGE.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_SOURCE_GUARD_COVERAGE.md) records the separate still-disabled source-guard coverage boundary; prerequisite audit evidence, absence evidence, and guard coverage remain non-authorizing.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_REDACTION_GUARD.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_REDACTION_GUARD.md) records the separate still-disabled redaction boundary; prerequisite audit evidence, absence evidence, and safe-output evidence remain non-authorizing.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_ADMISSION_GATE.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_ADMISSION_GATE.md) records the final current model-only admission boundary; prerequisite audit evidence and absence evidence remain non-authorizing and do not admit implementation.

## Prerequisite Categories

The audit models these categories: branch approval, prior evidence review, synthetic identity namespace, source-set placement, production source absence, runtime reachability absence, provider selection absence, registry absence, factory absence, dispatcher absence, executor target absence, provider KAT executor absence, provider operation absence, vault lifecycle absence, persistence absence, secure storage absence, secure metadata absence, backend client absence, BDK wallet state absence, settings codec absence, UI surface absence, signing and broadcasting absence, Tor and Nostr absence, public endpoint absence, mainnet absence, source guard coverage, redaction/leakage review, and future implementation review.

## Prerequisite Items

Blocked current approval items: `ExplicitFutureBranchApprovalRecorded`, `SyntheticSafeIdApprovedForReview`, `TestOnlyNamespaceApprovedForReview`, `NonProductionSourceSetPlacementApprovedForReview`, `RedactionAndLeakageReviewComplete`, and `FutureTestOnlyImplementationReviewComplete`.

Prior-evidence items may be present as model evidence only: `IdentityDecisionEvidenceReviewed`, `IdentityIsolationEvidenceReviewed`, `SyntheticNamespaceEvidenceReviewed`, `SourceSetConfinementEvidenceReviewed`, and `ImplementationDecisionEvidenceReviewed`. They do not authorize implementation.

Negative absence evidence items may be modeled as currently true only to prove absence, not approval: `ProductionSourceAbsenceProven`, `ProviderSelectionAbsenceProven`, `RegistryAbsenceProven`, `FactoryAbsenceProven`, `DispatcherAbsenceProven`, `ExecutorTargetAbsenceProven`, `ProviderKatExecutorAbsenceProven`, `ProviderOperationAbsenceProven`, `VaultLifecycleAbsenceProven`, `PersistenceAbsenceProven`, `SecureStorageAbsenceProven`, `SecureMetadataAbsenceProven`, `BackendClientAbsenceProven`, `BdkWalletStateAbsenceProven`, `SettingsCodecAbsenceProven`, `UiSurfaceAbsenceProven`, `SigningAbsenceProven`, `BroadcastingAbsenceProven`, `TorAbsenceProven`, `NostrAbsenceProven`, `PublicEndpointDefaultAbsenceProven`, and `MainnetAbsenceProven`.

`SourceGuardCoverageComplete` may be represented only as source-guard evidence. It is not implementation authorization. Negative absence evidence is also not implementation authorization; it only records that a forbidden bridge is absent now.

## Forbidden Implementation Shortcuts

The audit forbids treating the branch name, prior identity decision, prior isolation guard, synthetic safe ID, source-set confinement, implementation decision, passing source guards, documentation, test-only evidence, user consent, warning-only evidence, release claims, absence evidence, future-review labels, provider-selection fallback, disabled provider status, public vector evidence, or KAT harness evidence as implementation approval.

## Forbidden Promotion Paths

The audit cannot promote into provider implementation, test-only provider identity implementation, production provider identity implementation, provider selection, `productionProviderSelectable=true`, registry entries, factories, dispatchers, executor targets, provider KAT executors, provider operations, randomness, KDF/HKDF/HMAC/AEAD, key generation, keyset storage, vault creation, vault unlock, vault sessions, vault persistence, secure storage success, secure metadata success, manifest read/write, migration, production sync, backend clients, BDK wallet state, settings codecs, UI surfaces, signing, broadcasting, Tor transport, Nostr parsing, public endpoint defaults, or mainnet.

## Non-Goals Preserved

This branch adds no provider, executor, KAT execution, crypto operation, storage operation, wallet operation, settings persistence, UI entry point, backend client, signing, broadcasting, Tor, Nostr, public endpoint, or mainnet behavior.

Skald still has no operated infrastructure, no Skald-managed default backend, no real funds path, no secrets path, no live wallet data path, no production crypto provider, no production vault persistence, no signing, no broadcasting, and mainnet remains disabled.

Any later implementation branch must obtain explicit future approval, prove source-set placement, prove safe synthetic identity handling, preserve all production absences, pass redaction/leakage and source-guard review, and keep prerequisite evidence non-authorizing for production provider selection.
