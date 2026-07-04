# Encrypted Local Vault: Test-Only Provider Identity KAT Case Binding

This document records the thirteenth slower test-only implementation step for the Skald Vault v1 provider identity chain: a commonTest-only non-executable test-only provider identity KAT case binding.

The KAT case binding exists only under `composeApp/src/commonTest`. It is built from the existing inert marker, KAT fixture catalog, KAT fixture validation report, public-vector admission gate, public vector fixture, public vector validation report, and capability matrix.

The binding contains exactly one non-executable metadata case. It references the existing marker safe ID, fixture ID, and public vector ID through safe wrappers, and uses the case ID listed below. KAT case binding passing is not production authorization, provider-selection authorization, KAT execution authorization, crypto authorization, vault-persistence authorization, sync authorization, signing/broadcasting authorization, public-endpoint authorization, or mainnet authorization.

## Case Inputs

- Marker safe ID: `skald-test-only-provider-identity-v1-deterministic-kat-inert-marker`
- Fixture ID: `skald-test-only-provider-identity-kat-fixture-v1-inert-identity-metadata`
- Public vector ID: `skald-test-only-provider-identity-kat-public-vector-v1-inert-identity-metadata`
- KAT case ID: `skald-test-only-provider-identity-kat-case-v1-inert-identity-metadata`
- Source-set placement: commonTest only.
- Marker count: one.
- Fixture row count: one.
- Public vector row count: one.
- Case count: one.

The case records only safe labels, enum labels, counts, and expected Boolean flags. The binding output redacts the marker safe ID, fixture ID, public vector ID, and case ID.

## Material Boundary

The case binding contains no raw bytes, no hex, no crypto material, no wallet material, no endpoint material, and no provider handles.

It includes no raw KAT vector bytes, raw KAT vector hex, public vector bytes, public vector hex, executable KATs, KAT executor, provider operation input, crypto input, storage handle, wallet data, backend handle, descriptor value, or path value.

## Blocked Capabilities

The KAT case binding is not selectable, registry-backed, factory-backed, dispatcher-backed, executor-targetable, provider-KAT-executor-reachable, or runtime-operational.

It cannot execute provider operations, execute crypto, participate in vault lifecycle, persist vault data, participate in production sync, touch backend clients or BDK wallet state, persist settings, expose UI, sign, broadcast, add public endpoint defaults, or enable mainnet.

The binding does not implement `VaultCryptoProvider`, does not hold a provider instance, does not expose provider handles or crypto objects, and does not add executable KATs.

## Production Scope

Production implementation remains out of scope. It requires later review after test-only implementation proves out and can be verified against the same expected behavior.

No provider, executor, KAT execution, crypto operation, storage operation, wallet operation, settings persistence, UI entry point, backend client, signing/broadcasting path, public endpoint, or mainnet behavior is implemented.

Skald constraints remain intact: no Skald-operated infrastructure, no Skald-managed default backend, no real funds, no secrets, no live wallet data, no production crypto provider, no production vault persistence, no signing/broadcasting, and mainnet remains disabled.
