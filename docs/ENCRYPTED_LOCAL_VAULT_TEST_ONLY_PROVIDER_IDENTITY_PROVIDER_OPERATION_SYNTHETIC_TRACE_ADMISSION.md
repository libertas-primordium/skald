# Encrypted Local Vault: Test-Only Provider Identity Provider-Operation Synthetic Trace Admission

This document records the thirtieth slower test-only implementation step for the Skald Vault v1 provider identity KAT chain: a commonTest-only admission gate for a future synthetic provider-operation trace.

The admission gate exists only under `composeApp/src/commonTest`. It models criteria for a future branch only. This pass does not create a synthetic trace, does not create trace payloads, and does not execute real provider operations.

The commonTest-only synthetic provider-operation trace artifact is documented in [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_PROVIDER_OPERATION_SYNTHETIC_TRACE.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_PROVIDER_OPERATION_SYNTHETIC_TRACE.md). It is payload-free metadata only and does not create trace payloads.

The admission gate reads the existing no-op provider-operation execution-boundary suite report and upstream no-op KAT chain to preserve the current boundary: only reading and reporting the fixed synthetic no-op result is permitted. Real provider-operation execution, crypto execution, KDF/HKDF/HMAC/AEAD, randomness, key generation, vault lifecycle, persistence, sync, settings, UI, backend, BDK, signing/broadcasting, public endpoint, and mainnet behavior remain blocked. This pass does not add a KAT runner or KAT executor.

`futureSyntheticTraceCriteriaModeled=true` is commonTest-only admission evidence. It is not production authorization, provider-selection authorization, provider-operation authorization, crypto authorization, KAT-executor authorization, vault-persistence authorization, sync authorization, signing/broadcasting authorization, UI authorization, endpoint authorization, or mainnet authorization.

## Source Set Placement

- Implementation: `composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderIdentityProviderOperationSyntheticTraceAdmission.kt`
- Test: `composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultTestOnlyProviderIdentityProviderOperationSyntheticTraceAdmissionTest.kt`
- Production/runtime roots: no implementation in `commonMain`, `androidMain`, or `desktopMain`.

## IDs

- Marker safe ID: `skald-test-only-provider-identity-v1-deterministic-kat-inert-marker`
- Fixture ID: `skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata`
- Vector ID: `skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata`
- Case ID: `skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata`
- Provider-operation metadata KAT ID: `skald-test-only-provider-identity-provider-operation-metadata-kat-v1-inert-identity`
- No-op provider-operation KAT ID: `skald-test-only-provider-identity-provider-operation-noop-kat-v1-inert-identity`
- No-op execution boundary ID: `skald-test-only-provider-identity-provider-operation-noop-execution-boundary-v1-inert-identity`
- No-op execution boundary suite report ID: `skald-test-only-provider-identity-provider-operation-noop-execution-boundary-suite-report-v1-inert-identity`
- Future synthetic trace ID: `skald-test-only-provider-identity-provider-operation-synthetic-trace-v1-inert-identity`

## Admission Outcomes

- SyntheticTraceAdmissionModeled
- FutureSyntheticTraceRequiresSeparateBranch
- CurrentSyntheticTraceNotPresent
- CurrentTracePayloadAbsent
- CurrentNoopProviderOperationExecutionAbsent
- CurrentRealProviderOperationExecutionAbsent
- CurrentCryptoExecutionAbsent
- CurrentKatRunnerAbsent
- CurrentKatExecutorAbsent
- ProviderSelectionAuthorizationAbsent
- ProductionAuthorizationAbsent
- ProviderOperationAuthorizationAbsent
- KatExecutorAuthorizationAbsent
- CryptoAuthorizationAbsent
- VaultPersistenceAuthorizationAbsent
- MainnetAuthorizationAbsent

## Future Synthetic Trace Criteria

- FutureBranchMustBeExplicitlyApproved
- FutureSyntheticTraceMustRemainCommonTestOnly
- FutureSyntheticTraceMustUseNoopExecutionBoundarySuiteOnly
- FutureSyntheticTraceMustReadFixedSyntheticNoopResultOnly
- FutureSyntheticTraceMustNotContainPayloadBytes
- FutureSyntheticTraceMustNotContainHex
- FutureSyntheticTraceMustNotContainProviderHandles
- FutureSyntheticTraceMustNotContainCryptoObjects
- FutureSyntheticTraceMustNotContainWalletMaterial
- FutureSyntheticTraceMustNotContainEndpointMaterial
- FutureSyntheticTraceMustNotImplementVaultCryptoProvider
- FutureSyntheticTraceMustNotUseVaultCryptoProviderInstance
- FutureSyntheticTraceMustNotUseProviderSelection
- FutureSyntheticTraceMustNotUseRegistry
- FutureSyntheticTraceMustNotUseFactory
- FutureSyntheticTraceMustNotUseDispatcher
- FutureSyntheticTraceMustNotUseExecutorTarget
- FutureSyntheticTraceMustNotRunKdfHkdfHmacAead
- FutureSyntheticTraceMustNotTouchVaultLifecycle
- FutureSyntheticTraceMustNotTouchPersistence
- FutureSyntheticTraceMustNotTouchBackendBdkSettingsUi
- FutureSyntheticTraceMustNotSignOrBroadcast
- FutureSyntheticTraceMustNotEnableMainnet
- FutureSyntheticTraceMustRemainCoveredBySourceGuards
- FutureSyntheticTraceMustRemainRedactedInOutput

## Forbidden Current Synthetic Trace States

- SyntheticTracePresent
- TracePayloadPresent
- NoopProviderOperationExecutionPresent
- RealProviderOperationExecutionPresent
- CryptoExecutionPresent
- KatRunnerPresent
- KatExecutorPresent
- ProviderSelectionAuthorizationPresent
- ProductionAuthorizationPresent
- ProviderOperationAuthorizationPresent
- KatExecutorAuthorizationPresent
- CryptoAuthorizationPresent
- VaultPersistenceAuthorizationPresent
- MainnetAuthorizationPresent

## Admission Scope

The admission gate reads existing commonTest evidence only: the no-op provider-operation execution-boundary suite report, no-op provider-operation execution-boundary validation report, no-op provider-operation execution boundary, no-op provider-operation KAT suite report, no-op provider-operation KAT validation report, no-op provider-operation KAT result, no-op provider-operation KAT admission gate, provider-operation metadata KAT suite report, marker, and capability matrix.

It records deterministic IDs, counts, enum coverage, Boolean evidence, and redaction only. It does not include raw KAT vector material, public vector bytes, public vector hex, provider-operation input, crypto input, wallet data, endpoint values, descriptor values, backend handles, storage handles, filesystem locations, executable provider behavior, synthetic trace output, or trace payloads.

The admission gate keeps output redacted and does not expose raw marker safe IDs, fixture IDs, vector IDs, case IDs, provider-operation metadata KAT IDs, no-op provider-operation KAT IDs, boundary IDs, boundary suite report IDs, or synthetic trace IDs from `toString`.

## Production Scope

Production implementation remains out of scope. No provider, executor, KAT runner, KAT executor, real provider-operation execution, synthetic trace artifact, trace payload, crypto KAT, crypto operation, storage operation, wallet operation, settings persistence, UI entry point, backend client, signing/broadcasting path, public endpoint, or mainnet behavior is implemented.

Skald constraints remain intact: no Skald-operated infrastructure, no Skald-managed default backend, no real funds, no secrets, no live wallet data, no production crypto provider, no production vault persistence, no signing/broadcasting, and mainnet remains disabled.
