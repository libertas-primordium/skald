# Encrypted Local Vault: Test-Only Provider Identity Marker

This document records the first slower test-only implementation step for the Skald Vault v1 provider identity chain: a commonTest-only inert test-only provider identity marker.

The marker exists only under `composeApp/src/commonTest`. It is not commonMain, AndroidMain, DesktopMain, UI, settings, backend, BDK, storage, or provider-selection runtime code.

## Marker

- Safe ID: `skald-test-only-provider-identity-v1-deterministic-kat-inert-marker`
- Source-set placement: commonTest only.
- Namespace: synthetic test-only provider identity v1.
- Family: `deterministic-kat`.
- Purpose: `inert-marker`.

The marker does not implement `VaultCryptoProvider`, does not hold a provider instance, does not expose provider handles or crypto objects, and redacts its safe ID from `toString`.

## Blocked Capabilities

The marker is not selectable, registry-backed, factory-backed, dispatcher-backed, executor-targetable, provider-KAT-executor-reachable, or runtime-operational.

It cannot execute provider operations, execute crypto, participate in vault lifecycle, persist vault data, participate in production sync, touch backend clients or BDK wallet state, persist settings, expose UI, sign, broadcast, add public endpoint defaults, or enable mainnet.

## Production Scope

Production implementation remains out of scope. It requires later review after test-only implementation proves out and can be verified against the same expected behavior.

No provider, executor, KAT execution, crypto operation, storage operation, wallet operation, settings persistence, UI entry point, backend client, signing/broadcasting path, public endpoint, or mainnet behavior is implemented.

Skald constraints remain intact: no Skald-operated infrastructure, no Skald-managed default backend, no real funds, no secrets, no live wallet data, no production crypto provider, no production vault persistence, no signing/broadcasting, and mainnet remains disabled.
