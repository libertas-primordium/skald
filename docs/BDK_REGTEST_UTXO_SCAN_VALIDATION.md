# BDK Regtest UTXO Scan Validation

## Status

Skald Vault now has a desktop-test-only BDK regtest UTXO scan validation boundary.

The boundary is intentionally blocked in the current source tree. BDK `2.3.0` on JVM exposes wallet scan request types and indexed backend clients, but the resolved Maven artifact does not expose a direct Bitcoin Core RPC scan client that can observe UTXOs from the existing local `bitcoind` harness alone.

This is not production wallet sync. It is not wired into the app UI, settings repositories, descriptor wallet profile metadata, Recovery Center state, backend settings, coin-control drafts, or persisted app models.

## Source Location

The validation boundary lives under desktop test sources:

```text
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/bdk/
```

The local process harness remains separate:

```text
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/regtest/
```

No production source set uses BDK wallet scan APIs, local process execution, or backend client construction.

## What Was Checked

The validation inspects the resolved BDK JVM artifact and records only Skald-owned status:

- `FullScanRequest` is present.
- `SyncRequest` is present.
- `ElectrumClient` is present.
- `EsploraClient` is present.
- `CbfClient` is present.
- No direct Bitcoin Core RPC scan client class is present under the checked names.

Because the current harness starts only a temporary local `bitcoind` node and does not provide a local Electrum, Esplora, or compact-filter scan harness, the BDK UTXO observation path reports:

```text
BDK_REGTEST_UTXO_SCAN_REQUIRES_LOCAL_INDEXER
```

This blocked result is deliberate. It prevents hidden fallback to public infrastructure and avoids pretending that app wallet sync exists.

## Receive-Address Policy Link

The tests also exercise the Skald-owned receive-address policy with sanitized fake scan observations:

1. A placeholder receive address starts displayed/reserved and unused.
2. A sanitized backend observation marks it observed and used.
3. A reuse attempt then requires explicit high-friction confirmation.

This policy test does not claim that BDK observed a real UTXO. It only proves that the state model can represent the transition once a safe local scan backend exists.

## How To Run

Normal UTXO validation tests do not create runtime wallet material and do not start local backend processes:

```bash
./gradlew :composeApp:desktopTest --tests '*Utxo*'
```

The opt-in blocked validation path is:

```bash
SKALD_RUN_BDK_REGTEST_UTXO_SCAN=1 ./gradlew :composeApp:desktopTest --tests '*Utxo*' --rerun-tasks
```

In sandboxed environments that need a writable Gradle cache and IPv4 preference:

```bash
GRADLE_USER_HOME=/tmp/skald-gradle-home SKALD_RUN_BDK_REGTEST_UTXO_SCAN=1 ./gradlew --no-daemon -Djava.net.preferIPv4Stack=true :composeApp:desktopTest --tests '*Utxo*' --rerun-tasks
```

The opt-in command currently passes by asserting the blocked state. It does not fund a BDK wallet address, call BDK sync, apply a wallet update, or query public infrastructure.

## Explicit Non-Capabilities

This validation boundary does not enable:

- production BDK sync,
- production UTXO scanning,
- app receive address generation,
- production receive UI,
- production address index persistence,
- descriptor persistence,
- app wallet activation,
- secure storage,
- transaction construction,
- PSBT import, export, or finalization,
- signing,
- broadcasting,
- Nostr key parsing,
- Lightning,
- Cashu,
- Payjoin,
- mainnet,
- public backend defaults,
- Skald-operated infrastructure.

## Safety Rules

The boundary must remain:

- desktop test/development only,
- regtest only,
- explicitly opt-in for the BDK scan validation result,
- redacted in result models and assertion messages,
- free of committed address and transaction fixtures,
- free of production storage paths,
- free of public backend defaults,
- free of signing and broadcasting.

Runtime-generated regtest addresses or transaction identifiers may be used only in future opt-in test execution. They must not be committed as fixtures, written to docs, written to build history, or exposed through result `toString()` output.

## Current Blocker

The existing local harness uses `bitcoind` and `bitcoin-cli` to start a temporary regtest node and mine blocks. BDK `2.3.0` does not expose a direct local Bitcoin Core RPC scanner in the resolved JVM artifact, so BDK cannot observe wallet UTXOs from that harness alone.

The available BDK scan clients require one of these next decisions:

- add a local Electrum or Esplora indexer harness for regtest,
- design a compact-filter local peer harness separately,
- adapt the BDK test adapter around a different safe local chain-source strategy,
- evaluate whether a newer BDK version changes the backend API enough to justify migration.

None of those options should be added implicitly. They require a focused pass because they introduce real local backend behavior and new failure modes.

## Next Step

The next focused branch should decide and implement a local indexed regtest backend harness, or explicitly revise the BDK adapter strategy before attempting UTXO scan validation again.

Do not proceed to production wallet sync, production receive UI, PSBT construction, signing, broadcasting, or Nostr payment flows until this local scan backend decision is resolved.
