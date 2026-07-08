# Encrypted Local Vault: Test-Only Provider Identity Executable Scope Admission

This document records a commonTest-only executable-scope-admission-only pass for the test-only provider identity chain.

The admission gate aggregates the implementation transition gate and the inert provider identity descriptor completion audit. It also records prior disabled-provider-boundary, dependency-level public KAT, and Argon2id calibration probe evidence as evidence only.

## Scoped Admission

- This pass explicitly admits a future test-source-only executable provider implementation path.
- `testOnlyExecutableProviderImplementationAdmitted=true` approves only a later test-source-only implementation branch.
- `publicKatProviderImplementationScopeAdmitted=true` approves only public KAT scope for later branches.
- The planned provider may cover KDF and AEAD public-vector operations in later branches.
- `plannedExecutableProviderCanCoverKdf=true` is future scope only and does not execute KDF in this branch.
- `plannedExecutableProviderCanCoverAead=true` is future scope only and does not execute AEAD in this branch.
- The later implementation must remain test-source-only.
- Future provider-level public KAT execution requires a separate branch.
- Future provider selection enablement requires a separate branch.

## Current Blocks

- This pass does not implement an executable provider.
- This pass executes no KDF.
- This pass executes no AEAD.
- This pass executes no provider operation.
- This pass creates no KAT executor.
- This pass enables no provider selection.
- No `VaultCryptoProvider` implementation is added in this branch.
- No provider registry, provider factory, provider dispatcher, or executor target is added.
- No trace payloads are created.
- No vault persistence, production sync, signing, broadcasting, UI, endpoint, or mainnet behavior is added.

## Source-Set Decision

Later executable provider work must remain test-source-only unless a future branch explicitly changes the design.

- `commonTest` may hold Skald-owned test provider metadata, shared result models, public-KAT labels, and shared expectations.
- `desktopTest` may hold desktop JVM executable test provider code in a later branch.
- `androidInstrumentedTest` may hold Android runtime executable test provider code in a later branch.
- `commonMain`, `androidMain`, and `desktopMain` production source must not receive the test-only executable provider implementation in this branch.

## Provider Selection

Production provider selection remains `DisabledVaultCryptoProvider` only. `productionProviderSelectable` remains false.

The scoped admission labels are commonTest-only admission evidence. They are not provider execution evidence, provider implementation evidence, production authorization, provider selection authorization, KAT execution authorization for this branch, vault persistence authorization, production sync authorization, signing/broadcasting authorization, UI authorization, endpoint authorization, or mainnet authorization.
