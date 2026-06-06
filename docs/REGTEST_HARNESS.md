# Local Regtest Harness

## Status

Skald Vault includes a local `bitcoind` regtest harness for deterministic development validation.

This harness is test/development infrastructure only. It does not enable production backend networking, app wallet sync, BDK wallet creation, descriptor storage, address derivation in app flows, UTXO scanning in the app UI, PSBT construction, signing, broadcasting, secure storage, or mainnet operation.

## Purpose

The harness proves that the development environment can start a local regtest node, check readiness, read chain metadata, generate a regtest block, shut down cleanly, and remove temporary state.

It exists to support BDK rollout layers such as seed-backed regtest wallet creation and recovery behind Skald-owned adapter APIs. The current seed-backed BDK validation boundary is documented in [`BDK_REGTEST_WALLET_VALIDATION.md`](BDK_REGTEST_WALLET_VALIDATION.md), the test-only address derivation boundary is documented in [`BDK_REGTEST_ADDRESS_DERIVATION.md`](BDK_REGTEST_ADDRESS_DERIVATION.md), and the JVM/Linux binding decision is documented in [`BDK_JVM_LINUX_BINDING_DECISION.md`](BDK_JVM_LINUX_BINDING_DECISION.md).

## Source Location

Harness code lives under desktop test sources:

```text
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/regtest/
```

No production source set uses `ProcessBuilder`, `bitcoin-cli`, `bitcoind`, or local regtest process execution for this pass.

## Required Binaries

The smoke test needs locally installed Bitcoin Core or Bitcoin Knots-compatible binaries:

```text
bitcoind
bitcoin-cli
```

Discovery checks environment variables first:

```text
SKALD_BITCOIND
SKALD_BITCOIN_CLI
```

If those are unset, the harness searches `PATH`.

## Smoke Command

Normal tests do not require Bitcoin binaries. The full local lifecycle is opt-in:

```bash
SKALD_RUN_REGTEST_INTEGRATION=1 ./gradlew :composeApp:desktopTest --tests '*Regtest*'
```

In sandboxed environments where Gradle needs an alternate cache or IPv4 preference, use:

```bash
GRADLE_USER_HOME=/tmp/skald-gradle-home SKALD_RUN_REGTEST_INTEGRATION=1 ./gradlew --no-daemon -Djava.net.preferIPv4Stack=true :composeApp:desktopTest --tests '*Regtest*'
```

If Gradle reports the test task as up-to-date after changing the opt-in environment variable, add `--rerun-tasks`.

## Skip and Availability Behavior

When `SKALD_RUN_REGTEST_INTEGRATION` is not set to `1`, the integration smoke test reports a disabled result and exits successfully.

When the opt-in flag is set but `bitcoind` or `bitcoin-cli` is unavailable, the harness reports an unavailable result with the missing binary names. It does not fall back to public infrastructure, testnet, mainnet, Electrum, Esplora, DNS, HTTP, socket clients, Tor, or Skald-operated endpoints.

When binaries are present and the harness starts local regtest, startup or CLI failures are treated as real integration failures.

## Harness Behavior

The full smoke path:

1. Creates a unique temporary datadir.
2. Starts `bitcoind` with `-regtest`.
3. Uses local-only flags such as `-listen=0`, `-dnsseed=0`, and `-fixedseeds=0`.
4. Waits for readiness through `bitcoin-cli -regtest getblockchaininfo`.
5. Reads blockchain info.
6. Creates a temporary `bitcoind` mining wallet only inside the temporary regtest datadir.
7. Generates one regtest block.
8. Stops `bitcoind` with `bitcoin-cli stop`.
9. Deletes the temporary datadir where practical.

The temporary mining wallet belongs to the `bitcoind` test harness only. It is not a Skald wallet, not a BDK wallet, not app storage, and not production wallet material.

## Safety Boundaries

The harness must not:

- Use mainnet.
- Use public endpoints.
- Use Skald-operated infrastructure.
- Create a BDK wallet.
- Persist a BDK database.
- Write to Skald app settings or app data.
- Store seed material, mnemonics, descriptors, keys, addresses, PSBTs, transaction hex, backend credentials, Nostr keys, Lightning credentials, or Cashu material in source, docs, settings, or build history.
- Add production RPC, HTTP, socket, Electrum, Esplora, or Tor client dependencies.
- Connect the app UI to a backend.

Runtime regtest addresses and block hashes may be produced by `bitcoind` during the smoke test, but they are not committed, documented as fixtures, or persisted by Skald.

## Relationship To Address Derivation

The BDK regtest/signet address derivation validation does not require this harness because BDK can derive the tested receive addresses offline from runtime-only test wallet material.

The local `bitcoind` harness remains the expected base for later regtest UTXO scan, fee, and transaction-validation work.

## Next Step

The next focused pass should introduce address reuse prevention/receive-address state modeling or a regtest UTXO scan validation path. Those passes must remain regtest/signet first, avoid production persistence until explicitly designed, and keep BDK types out of common UI/settings models.
