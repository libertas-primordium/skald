# BDK Regtest Address Derivation Validation

## Status

Skald Vault now has a desktop-test-only validation boundary for deriving runtime-generated BDK receive addresses on development networks.

This is not production wallet functionality. It is not wired into the app UI, descriptor wallet profile metadata, settings repositories, Recovery Center state, backend settings, coin-control drafts, or persisted app models.

The validation currently passes on the Linux desktop target with pinned `org.bitcoindevkit:bdk-jvm:2.3.0`. The JVM/Linux binding decision is documented in [`BDK_JVM_LINUX_BINDING_DECISION.md`](BDK_JVM_LINUX_BINDING_DECISION.md).

The production-safe receive-address state and reuse-prevention policy is documented separately in [`RECEIVE_ADDRESS_POLICY.md`](RECEIVE_ADDRESS_POLICY.md). That policy layer is pure Skald-owned Kotlin and does not call BDK address APIs.

## Source Location

The validation code lives under desktop test sources:

```text
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/bdk/
```

Direct BDK address and wallet API imports remain confined to this desktop test validation package and the existing platform adapter probe files. Common UI, common domain policy, settings codecs, persisted models, and production services remain free of BDK public types.

## What The Validation Proves

When explicitly enabled, the validation:

1. creates runtime-only entropy,
2. creates a runtime-only mnemonic from that entropy,
3. creates runtime-only BIP86 descriptors for a development network,
4. uses BDK's in-memory persister,
5. creates a test-only BDK wallet,
6. peeks the first external receive address,
7. peeks a second external receive address,
8. recreates the wallet from the same in-memory entropy,
9. peeks the first external receive address again,
10. confirms the first receive address is deterministic for the same runtime seed,
11. confirms a different receive index produces a different address,
12. returns only Skald-owned redacted status.

The regtest path is the primary validation path. Signet derivation is also exercised by the opt-in test because BDK `2.3.0` exposes the required signet network enum and the derivation path is offline.

## Address Material Policy

Runtime-generated regtest and signet addresses are public test-network values, but they are still treated carefully:

- no address fixtures are hardcoded in source, docs, tests, screenshots, or build history,
- no address values are included in result `toString()` output,
- no address values are written to app storage,
- no address values are used to query a backend,
- no address index state is persisted in production,
- no receive UI is enabled.

Result models may hold runtime-generated test address text for assertions, but display/debug strings redact it as `RUNTIME_TEST_ADDRESS_REDACTED`.

## How To Run

Normal BDK tests do not create runtime seed material:

```bash
./gradlew :composeApp:desktopTest --tests '*Bdk*'
```

The opt-in address derivation validation is:

```bash
SKALD_RUN_BDK_REGTEST_ADDRESS_DERIVATION=1 ./gradlew :composeApp:desktopTest --tests '*AddressDerivation*' --rerun-tasks
```

In sandboxed environments that need a writable Gradle cache and IPv4 preference:

```bash
GRADLE_USER_HOME=/tmp/skald-gradle-home SKALD_RUN_BDK_REGTEST_ADDRESS_DERIVATION=1 ./gradlew --no-daemon -Djava.net.preferIPv4Stack=true :composeApp:desktopTest --tests '*AddressDerivation*' --rerun-tasks
```

This validation does not require a running `bitcoind` node because BDK can derive these addresses offline from the runtime-only test wallet.

## Explicit Non-Capabilities

This validation boundary does not enable:

- production receive UI,
- production wallet activation,
- production address index persistence,
- production descriptor persistence,
- secure storage,
- app wallet creation flow,
- app address derivation path,
- backend sync,
- UTXO scanning,
- fee estimation,
- transaction construction,
- PSBT import/export/finalization,
- signing,
- broadcasting,
- Nostr key parsing,
- Lightning,
- Cashu,
- Payjoin,
- mainnet.

## Safety Rules

The validation must remain:

- desktop test/development only,
- explicitly opt-in for runtime seed material,
- regtest/signet only,
- offline,
- backed by BDK's in-memory persister,
- redacted in result models and assertion messages,
- free of committed secret or address fixtures,
- free of production storage paths,
- free of backend networking,
- free of signing and broadcasting.

Mainnet requests are rejected before runtime wallet material is created.

## UTXO Scan Follow-Up

The follow-up regtest UTXO scan validation boundary is documented in [`BDK_REGTEST_UTXO_SCAN_VALIDATION.md`](BDK_REGTEST_UTXO_SCAN_VALIDATION.md).

That boundary currently reports a safe blocked state. BDK `2.3.0` exposes wallet scan requests and indexed backend clients, but the resolved JVM artifact does not expose a direct Bitcoin Core RPC scan client that can observe UTXOs from the existing local `bitcoind` harness alone.

## Next Step

The next focused pass should decide and implement a local indexed regtest backend harness, or revise the BDK adapter strategy before attempting full UTXO observation again.

That path must keep production app flows disabled until the secure-storage, recovery, and backend-trust boundaries are ready.
