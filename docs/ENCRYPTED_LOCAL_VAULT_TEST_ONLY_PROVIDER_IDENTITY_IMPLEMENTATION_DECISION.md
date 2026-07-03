# Encrypted Local Vault: Test-Only Provider Identity Implementation Decision

This document records the still-disabled, model-only Skald Vault v1 implementation decision gate for any future test-only provider identity implementation.

The identity decision, identity isolation guard, synthetic identity namespace contract, source-set confinement boundary, and implementation decision gate are separate boundaries. The identity decision classifies future identity categories. The isolation guard keeps those categories label-only and unreachable. The namespace contract constrains safe labels. The source-set confinement boundary constrains placement. This implementation decision gate asks whether those prior boundaries authorize an actual implementation. The current answer is no.

## Current State

The implementation decision is modeled and still disabled. Current outcomes are `CurrentDecisionBlocked`, `FutureBranchReviewRequired`, `ImplementationNotAuthorized`, `ProductionPromotionNotAuthorized`, and `MainnetNotAuthorized`.

This evidence does not implement a provider identity and does not authorize provider selection. Provider selection remains disabled-provider-only through `DisabledVaultCryptoProvider`, and `productionProviderSelectable` remains false.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_PREREQUISITE_AUDIT.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_PREREQUISITE_AUDIT.md) records the next separate still-disabled prerequisite audit boundary; this implementation decision evidence remains non-authorizing.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_SCOPE_DECISION.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_SCOPE_DECISION.md) records the separate still-disabled future implementation scope boundary; this implementation decision evidence remains non-authorizing.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_CONTRACT.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_CONTRACT.md) records the separate still-disabled future implementation contract; this implementation decision evidence remains non-authorizing.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_READINESS_GATE.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_READINESS_GATE.md) records the separate still-disabled future implementation readiness gate; this implementation decision evidence remains non-authorizing.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_RUNTIME_LINKAGE_GUARD.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_RUNTIME_LINKAGE_GUARD.md) records the separate still-disabled runtime containment boundary; this implementation decision evidence remains disconnected from runtime provider surfaces.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_PROMOTION_BLOCKERS.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_PROMOTION_BLOCKERS.md) records the separate still-disabled promotion containment boundary; this implementation decision evidence cannot promote into implementation, provider selection, production identity, or mainnet.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_SOURCE_GUARD_COVERAGE.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_SOURCE_GUARD_COVERAGE.md) records the separate still-disabled source-guard coverage boundary; implementation decision evidence and guard coverage remain non-authorizing.

[`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_REDACTION_GUARD.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_IMPLEMENTATION_REDACTION_GUARD.md) records the separate still-disabled redaction boundary; implementation decision evidence and safe-output evidence remain non-authorizing.

## Decision Gates

Blocked current approval gates: `ExplicitFutureBranchApproval`, `SourceSetPlacementApproved`, `SyntheticSafeIdApproved`, `TestOnlyNamespaceApproved`, `NonProductionSourceSetPlacementApproved`, `RedactionAndLeakageReviewComplete`, `SourceGuardCoverageComplete`, and `FutureTestOnlyImplementationReviewComplete`.

Prior-evidence review gates may be present as model evidence only: `PriorIdentityDecisionEvidenceReviewed`, `PriorIdentityIsolationEvidenceReviewed`, `PriorSyntheticNamespaceEvidenceReviewed`, and `PriorSourceSetConfinementEvidenceReviewed`. They do not authorize implementation.

Negative absence evidence gates may be modeled as currently true only to prove absence, not approval: `ProductionSourceAbsenceProven`, `ProviderSelectionAbsenceProven`, `RegistryAbsenceProven`, `FactoryAbsenceProven`, `DispatcherAbsenceProven`, `ExecutorTargetAbsenceProven`, `ProviderKatExecutorAbsenceProven`, `ProviderOperationAbsenceProven`, `VaultLifecycleAbsenceProven`, `PersistenceAbsenceProven`, `SecureStorageAbsenceProven`, `SecureMetadataAbsenceProven`, `BackendClientAbsenceProven`, `BdkWalletStateAbsenceProven`, `SettingsCodecAbsenceProven`, `UiSurfaceAbsenceProven`, `SigningAbsenceProven`, `BroadcastingAbsenceProven`, `TorAbsenceProven`, `NostrAbsenceProven`, `PublicEndpointDefaultAbsenceProven`, and `MainnetAbsenceProven`.

Negative absence evidence is not implementation authorization. It only records that a forbidden bridge is absent now.

## Forbidden Implementation Effects

The decision gate forbids creating a test-only or production provider identity implementation, implementing `VaultCryptoProvider`, adding a provider factory, dispatcher, registry entry, executor target, provider KAT executor, runnable executor interface, provider operations, randomness, KDF/HKDF/HMAC/AEAD execution, key generation, keyset storage, vault creation, vault unlock, vault sessions, vault persistence, secure storage success, secure metadata success, storage namespace writes, storage path writes, manifest read/write, migration, production sync, backend clients, BDK wallet state, settings codec writes, UI surfaces, signing, broadcasting, Tor transport, Nostr secret parsing, public endpoint defaults, or mainnet.

## Forbidden Promotion Paths

Identity decision evidence, identity isolation evidence, synthetic namespace evidence, source-set confinement evidence, documentation, public vector evidence, test-only KAT evidence, user consent, warning-only evidence, release claims, future-review labels, and safe-label syntax acceptance cannot promote into implementation.

A future test-only implementation, if ever approved in a later branch, still cannot promote itself into a production provider, `productionProviderSelectable=true`, vault persistence, production sync, signing, broadcasting, or mainnet.

## Non-Goals Preserved

This branch adds no provider, executor, KAT execution, crypto operation, storage operation, wallet operation, settings persistence, UI entry point, backend client, signing, broadcasting, Tor, Nostr, public endpoint, or mainnet behavior.

Skald still has no operated infrastructure, no Skald-managed default backend, no real funds path, no secrets path, no live wallet data path, no production crypto provider, no production vault persistence, no signing, no broadcasting, and mainnet remains disabled.

Any later implementation branch must obtain explicit future approval, prove source-set placement, prove safe synthetic identity handling, preserve all production absences, pass redaction/leakage and source-guard review, and keep test-only evidence non-authorizing for production provider selection.
