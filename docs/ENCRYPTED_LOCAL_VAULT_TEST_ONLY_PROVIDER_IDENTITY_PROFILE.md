# Encrypted Local Vault: Test-Only Provider Identity Profile

This document records the third slower test-only implementation step for the Skald Vault v1 provider identity chain: a commonTest-only inert test-only provider identity profile.

The profile exists only under `composeApp/src/commonTest`. It is built from the inert inventory and exactly one inert marker, and it is not commonMain, AndroidMain, DesktopMain, UI, settings, backend, BDK, storage, or provider-selection runtime code.

## Profile

- Safe ID referenced: `skald-test-only-provider-identity-v1-deterministic-kat-inert-marker`
- Source-set placement: commonTest only.
- Inventory size: one.
- Profile kind: inert test-only identity profile.
- Profile purpose: future validation metadata only.

The profile is not a production provider profile, not a production registry, not a factory input, not a dispatcher input, and not an executor target. It does not implement `VaultCryptoProvider`, does not hold a provider instance, does not expose provider handles or crypto objects, and redacts marker IDs from `toString`.

## Blocked Capabilities

The profile is not selectable, registry-backed, factory-backed, dispatcher-backed, executor-targetable, provider-KAT-executor-reachable, or runtime-operational.

It cannot execute provider operations, execute crypto, participate in vault lifecycle, persist vault data, participate in production sync, touch backend clients or BDK wallet state, persist settings, expose UI, sign, broadcast, add public endpoint defaults, or enable mainnet.

## Production Scope

Production implementation remains out of scope. It requires later review after test-only implementation proves out and can be verified against the same expected behavior.

No provider, executor, KAT execution, crypto operation, storage operation, wallet operation, settings persistence, UI entry point, backend client, signing/broadcasting path, public endpoint, or mainnet behavior is implemented.

Skald constraints remain intact: no Skald-operated infrastructure, no Skald-managed default backend, no real funds, no secrets, no live wallet data, no production crypto provider, no production vault persistence, no signing/broadcasting, and mainnet remains disabled.
