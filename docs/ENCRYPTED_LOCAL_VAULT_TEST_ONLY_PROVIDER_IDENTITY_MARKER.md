# Encrypted Local Vault: Test-Only Provider Identity Marker

This document records the first slower test-only implementation step for the Skald Vault v1 provider identity chain: a commonTest-only inert test-only provider identity marker.

After the implementation transition gate, the user explicitly authorized this exact inert-marker pass. That authorization is scoped only to the commonTest-only inert implementation marker recorded here. It does not authorize a provider implementation, `VaultCryptoProvider` implementation, provider selection, provider-operation execution, crypto execution, KAT runner or KAT executor work, vault persistence, sync, signing/broadcasting, UI, endpoint behavior, or mainnet.

This marker is the first actual test-only provider identity implementation artifact. It remains inert implementation-marker evidence only.

A later commonTest-only marker validation report is documented in [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_MARKER_VALIDATION.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_MARKER_VALIDATION.md). That validation keeps `markerValidationPassed=true` as validation evidence only and does not authorize provider implementation, provider selection, provider-operation execution, crypto execution, KAT runner or KAT executor work, vault persistence, sync, signing/broadcasting, UI, endpoint behavior, or mainnet.

A later commonTest-only marker suite report is documented in [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_MARKER_SUITE_REPORT.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_MARKER_SUITE_REPORT.md). That suite report keeps `markerSuiteReportPassed=true` as suite-report evidence only and does not authorize provider implementation, provider selection, provider-operation execution, crypto execution, KAT runner or KAT executor work, vault persistence, sync, signing/broadcasting, UI, endpoint behavior, or mainnet.

A later commonTest-only marker completion audit is documented in [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_MARKER_COMPLETION_AUDIT.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_MARKER_COMPLETION_AUDIT.md). That completion audit keeps `markerCompletionAuditPassed=true` as completion-audit evidence only and does not authorize provider implementation, provider selection, provider-operation execution, crypto execution, KAT runner or KAT executor work, vault persistence, sync, signing/broadcasting, UI, endpoint behavior, or mainnet.

The second slower test-only step wraps this marker in a commonTest-only inert inventory documented in [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_INVENTORY.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_INVENTORY.md).

The third slower test-only step composes this marker and inventory into a commonTest-only inert profile documented in [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_PROFILE.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_PROFILE.md).

The fourth slower test-only step validates the inert marker, inventory, and profile in a commonTest-only report documented in [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_PROFILE_VALIDATION.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_PROFILE_VALIDATION.md).

The fifth slower test-only step proves negative runtime/provider reachability for the marker chain in a commonTest-only report documented in [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_REACHABILITY_PROOF.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_REACHABILITY_PROOF.md).

The sixth slower test-only step summarizes all marker-chain capabilities as blocked in a commonTest-only inert capability matrix documented in [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_CAPABILITY_MATRIX.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_CAPABILITY_MATRIX.md).

The seventh slower test-only step defines commonTest-only KAT fixture metadata scope for this marker chain in [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_KAT_FIXTURE_SCOPE.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_KAT_FIXTURE_SCOPE.md).

The eighth slower test-only step records one metadata-only KAT fixture catalog row for this marker chain in [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_KAT_FIXTURE_CATALOG.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_KAT_FIXTURE_CATALOG.md).

The ninth slower test-only step validates that catalog as metadata-only evidence in [`ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_KAT_FIXTURE_VALIDATION.md`](ENCRYPTED_LOCAL_VAULT_TEST_ONLY_PROVIDER_IDENTITY_KAT_FIXTURE_VALIDATION.md).

The marker exists only under `composeApp/src/commonTest`. It is not commonMain, AndroidMain, DesktopMain, UI, settings, backend, BDK, storage, or provider-selection runtime code.

## Marker

- Safe ID: `skald-test-only-provider-identity-v1-deterministic-kat-inert-marker`
- Source-set placement: commonTest only.
- Namespace: synthetic test-only provider identity v1.
- Family: `deterministic-kat`.
- Purpose: `inert-marker`.
- `markerCreated=true` is commonTest-only inert marker evidence only.

The marker does not implement `VaultCryptoProvider`, does not hold a provider instance, does not expose provider handles or crypto objects, and redacts its safe ID from `toString`.

## Scoped Approval

The human approval for this pass permits only this inert commonTest-only marker. It does not broaden the earlier transition gate and does not turn `reviewReadyForHumanDecision=true` into implementation authorization.

`markerCreated=true` is not provider implementation authorization, provider-selection authorization, provider-operation authorization, crypto authorization, KAT-runner authorization, KAT-executor authorization, vault-persistence authorization, sync authorization, signing/broadcasting authorization, UI authorization, endpoint authorization, or mainnet authorization.

## Blocked Capabilities

The marker is not selectable, registry-backed, factory-backed, dispatcher-backed, executor-targetable, provider-KAT-executor-reachable, or runtime-operational.

It cannot execute provider operations, execute crypto, participate in vault lifecycle, persist vault data, participate in production sync, touch backend clients or BDK wallet state, persist settings, expose UI, sign, broadcast, add public endpoint defaults, or enable mainnet.

## Production Scope

Production implementation remains out of scope. It requires later review after test-only implementation proves out and can be verified against the same expected behavior.

No provider, executor, KAT execution, crypto operation, storage operation, wallet operation, settings persistence, UI entry point, backend client, signing/broadcasting path, public endpoint, or mainnet behavior is implemented.

Provider selection remains `DisabledVaultCryptoProvider` only. `productionProviderSelectable` remains false.

Skald constraints remain intact: no Skald-operated infrastructure, no Skald-managed default backend, no real funds, no secrets, no live wallet data, no production crypto provider, no production vault persistence, no signing/broadcasting, and mainnet remains disabled.
