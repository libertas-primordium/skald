# Encrypted Local Vault: Test-Only Provider Identity Implementation Plan

This document records still-disabled, non-executable implementation planning evidence for the Skald Vault v1 test-only provider identity implementation chain.

This is the last fast planning pass before slower test-only implementation work. It defines the narrow future plan only; current plan evidence does not implement a provider identity, does not authorize provider selection, and does not admit current implementation. The first slower test-only implementation step has begun separately as an inert commonTest-only marker documented in [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_MARKER.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_MARKER.md), followed by an inert commonTest-only inventory documented in [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_INVENTORY.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_INVENTORY.md), an inert commonTest-only profile documented in [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_PROFILE.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_PROFILE.md), an inert commonTest-only validation report documented in [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_PROFILE_VALIDATION.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_PROFILE_VALIDATION.md), an inert commonTest-only reachability proof documented in [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_REACHABILITY_PROOF.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_REACHABILITY_PROOF.md), an inert commonTest-only capability matrix documented in [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_CAPABILITY_MATRIX.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_CAPABILITY_MATRIX.md), inert commonTest-only KAT fixture scope metadata documented in [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_KAT_FIXTURE_SCOPE.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_KAT_FIXTURE_SCOPE.md), and an inert commonTest-only KAT fixture catalog documented in [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_KAT_FIXTURE_CATALOG.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_KAT_FIXTURE_CATALOG.md).

The identity decision, identity isolation guard, synthetic namespace contract, source-set confinement boundary, implementation decision gate, prerequisite audit, scope decision, implementation contract, readiness gate, runtime linkage guard, promotion blockers, source-guard coverage, redaction guard, admission gate, and implementation plan are separate boundaries. Earlier boundaries classify, contain, constrain, deny authorization, compose readiness, prove runtime bridges absent, block promotion, record source-guard coverage, require safe output, and deny admission. This plan separately records what a future approved test-only branch may attempt and what remains forbidden now.

Provider selection remains fail-closed to `DisabledVaultCryptoProvider`, and `productionProviderSelectable` remains false.

## Planned Test-Only Artifacts

Future-only planned artifacts are `TestOnlyProviderIdentityClassOrObject`, `TestOnlySyntheticSafeIdConstant`, `TestOnlyNamespaceMarker`, `TestOnlySourceSetPlacement`, `TestOnlyNonProductionFixture`, `TestOnlyNoProviderSelectionProof`, `TestOnlyNoRegistryProof`, `TestOnlyNoFactoryProof`, `TestOnlyNoDispatcherProof`, `TestOnlyNoExecutorTargetProof`, `TestOnlyNoKatExecutorProof`, `TestOnlyNoProviderOperationProof`, `TestOnlyNoCryptoExecutionProof`, `TestOnlyNoVaultPersistenceProof`, `TestOnlyNoProductionSyncProof`, `TestOnlyNoSigningBroadcastingProof`, `TestOnlyNoMainnetProof`, `TestOnlyRedactionProof`, and `TestOnlySourceGuardProof`.

Except for the separate commonTest-only inert marker, inert inventory, inert profile, inert validation report, inert reachability proof, inert capability matrix, inert KAT fixture scope metadata, and inert KAT fixture catalog metadata, these artifacts remain future-only and do not authorize current implementation.

## Forbidden Current Artifacts

Current forbidden artifacts are `CurrentTestOnlyProviderIdentityImplementation`, `CurrentProductionProviderIdentityImplementation`, `CurrentVaultCryptoProviderImplementation`, `CurrentProviderSelectionEntry`, `CurrentNonDisabledRegistryEntry`, `CurrentProviderFactoryEntry`, `CurrentProviderDispatcherEntry`, `CurrentExecutorTarget`, `CurrentProviderKatExecutor`, `CurrentProviderOperationExecution`, `CurrentCryptoExecution`, `CurrentVaultCreation`, `CurrentVaultUnlock`, `CurrentVaultSession`, `CurrentVaultPersistence`, `CurrentSecureStorageSuccess`, `CurrentSecureMetadataSuccess`, `CurrentProductionSync`, `CurrentBackendClient`, `CurrentBdkWalletState`, `CurrentSettingsPersistence`, `CurrentUiSurface`, `CurrentSigning`, `CurrentBroadcasting`, `CurrentPublicEndpointDefault`, and `CurrentMainnet`.

## Constraints

Future implementation must use test-source-only placement, synthetic safe identifiers only, remain absent from provider selection, production registry, provider factory, provider dispatcher, executor targets, provider KAT executors, provider operations, randomness, KDF/HKDF/HMAC/AEAD, key generation, keyset storage, vault lifecycle, persistence, secure storage, secure metadata, backend clients, BDK wallet state, settings, UI, signing/broadcasting, public endpoints, and mainnet. It must remain redacted and covered by source guards.

## Acceptance Criteria

Future test-only implementation must compile only in an approved test source set, use only a synthetic safe identifier, prove no provider selection/registry/factory/dispatcher/executor/KAT/operation reachability, prove no crypto execution, prove no vault lifecycle or persistence reachability, prove no backend/BDK/settings/UI reachability, prove no signing/broadcasting/mainnet reachability, pass redaction tests, pass source-guard coverage, keep all prior model-only evidence non-authorizing, keep `productionProviderSelectable=false`, and keep `DisabledVaultCryptoProvider` as the only runtime selection.

Test-only implementation must prove out before production implementation review. Production implementation must reproduce the same expected behavior as the test-only implementation before it can be considered.

## Escalation Gates

Escalation requires explicit approval for the test-only implementation branch, reviewed test-only validation results, explicit approval for any production implementation branch, satisfaction of the production provider acceptance contract, production-provider KATs that pass the same behavior as the test implementation, vault persistence hardening review, and mainnet release-hardening approval.

## Rollback Criteria

Rollback is required if any provider-selection reachability, non-disabled registry entry, factory/dispatcher reachability, executor target, provider operation execution, crypto execution, vault persistence, production sync, signing/broadcasting, public endpoint default, mainnet enablement, raw material or secret leakage, or source-guard failure appears.

## Non-Authorizing Evidence

Planning evidence, admission evidence, redaction evidence, source-guard coverage, source guard passing, documentation, test-only evidence, warning-only evidence, release claims, and user consent are non-authorizing. Plan-as-authorization claims are blockers only.

## Non-Goals Preserved

This pass implements no provider, executor, KAT execution, crypto operation, storage operation, wallet operation, settings persistence, UI entry point, backend client, signing or broadcasting path, public endpoint, or mainnet behavior.

Skald constraints remain intact: no Skald-operated infrastructure, no Skald-managed default backend, no real funds, no secrets, no live wallet data, no production crypto provider, no production vault persistence, no signing/broadcasting, and mainnet remains disabled.
