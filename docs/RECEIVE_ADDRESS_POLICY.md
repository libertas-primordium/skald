# Receive Address Policy

## Status

This document describes the current Skald-owned receive-address state model and reuse-prevention policy.

Current status:

- Production receive address generation is not implemented.
- BDK-derived addresses are not wired into app runtime UI or repositories.
- No production address index state is persisted.
- No production backend observation, chain scan, UTXO scan, fee estimation, transaction construction, signing, or broadcasting is enabled.
- A Skald-owned backend observation/UTXO state boundary now exists for future sync adapters, but it is policy/model state only.
- Mainnet remains disabled.
- Secure storage remains disabled/fail-closed.

The implemented code is a pure Kotlin policy layer under the on-chain domain package. It does not import BDK and does not call BDK address APIs.

## Scope

The policy models:

- wallet/profile relationship for future receive-address state,
- receive keychain kind,
- derivation index,
- address network,
- script policy status when known,
- placeholder display values such as `ADDRESS_NOT_DERIVED`,
- reserved/displayed/observed address lifecycle states,
- displayed-versus-used distinction,
- backend observation requirements,
- address reuse warnings,
- source-specific warnings for imported-key and Nostr identity-linked wallet sources.

The model is intentionally non-operational. It exists so later regtest UTXO scan and operational receive-address work can share a clear state machine.

## Non-goals

This pass does not enable:

- production BDK address derivation,
- app receive address UI,
- real receive address display,
- production address index persistence,
- descriptor persistence,
- production wallet activation,
- secure storage,
- backend sync,
- UTXO scanning,
- fee estimation,
- transaction construction,
- PSBT import/export/finalization,
- signing,
- broadcasting,
- Nostr key parsing or Nostr-derived wallets,
- Lightning,
- Cashu,
- Payjoin,
- mainnet.

## State Model

`ReceiveAddressState` represents one future receive-address slot. It contains only Skald-owned fields:

- wallet profile ID and label,
- development network,
- receive/change keychain kind,
- derivation index,
- redacted or sentinel display value,
- script type if known,
- lifecycle state,
- provenance,
- source wallet category.

The current production-safe display sentinel is `ADDRESS_NOT_DERIVED`. Real app addresses are not created or stored by this model.

## Displayed Versus Used

Displaying an address is not the same as observing funds.

Policy rule:

- A reserved address may become displayed when shown to the user.
- A displayed address remains unused until a backend or chain scan observes receive activity.
- Backend observation may mark the address as unconfirmed-used or confirmed-used.
- A displayed-but-unobserved address may be re-shown if it has not been observed used.
- A used address is unsafe for normal receive reuse.

This distinction prevents a UI from burning address indices merely because an address was previewed, while still requiring strict reuse warnings once chain activity is observed.

## Backend Observation Requirement

The policy cannot mark an address used from UI display alone. A future backend scan or UTXO observation must provide the evidence that receive activity occurred.

Until backend observation is implemented:

- production address usage state is not updated from chain data,
- no UTXO scan exists,
- no address-monitoring backend is connected,
- receive-address state remains a policy foundation only.

The current backend observation state boundary is documented in [`BACKEND_OBSERVATION_STATE.md`](BACKEND_OBSERVATION_STATE.md). It can model a backend-observed receive event and map that event into this receive-address policy, but it does not add production sync, persistence, or app receive UI.

The production backend adapter and endpoint normalization boundary is documented in [`PRODUCTION_BACKEND_ADAPTER_BOUNDARY.md`](PRODUCTION_BACKEND_ADAPTER_BOUNDARY.md). It targets backend observation summaries while remaining disabled/fail-closed.

The disabled production sync service facade is documented in [`PRODUCTION_SYNC_SERVICE_BOUNDARY.md`](PRODUCTION_SYNC_SERVICE_BOUNDARY.md). It requires receive-address policy state during preflight and now surfaces the disabled result in Nodes as a read-only status card, but it still does not derive production addresses, sync wallets, persist observations, or expose a receive UI.

## Reuse Prevention

Normal receive flows must avoid address reuse.

Policy behavior:

- Re-showing a reserved/displayed unused address is allowed with an observation warning.
- Reusing an address with observed receive activity requires explicit high-friction confirmation.
- Used addresses are marked unsafe for normal receive use.
- Mainnet receive paths are blocked.
- Wallet/profile state can block receive decisions when the profile is non-operational.

Future UI should present the warning before any address reuse is allowed. Address reuse should never be silent.

## Source-Specific Warnings

The policy includes source categories that can add future warnings:

- native descriptor wallet,
- imported descriptor wallet,
- watch-only descriptor wallet,
- imported single-key wallet,
- Nostr npub watch-only wallet,
- Nostr nsec imported spend wallet,
- external signer,
- hardware signer.

Nostr identity-linked sources require an identity-linkage warning. Imported single-key and Nostr nsec spend sources require separate-backup warnings.

No Nostr npub/nsec parsing or Nostr-derived wallet creation is implemented.

## Network Policy

Receive-address policy allows development-network modeling only:

- regtest,
- signet,
- testnet,
- testnet4.

Mainnet remains unavailable and rejected by policy. No silent network fallback is allowed. Future backend network and wallet network must match before receive behavior becomes operational.

## Persistence Policy

No receive-address metadata repository was added in this pass.

Future persistence must be reviewed before storing any address index state. At minimum it must:

- store only non-secret address/index metadata,
- avoid private descriptors, seeds, mnemonics, private keys, wallet databases, transaction data, backend credentials, or secret material,
- distinguish displayed from used,
- preserve address index state across restarts,
- be safe under partial writes and rollback,
- remain disabled for mainnet until mainnet is separately approved.

## BDK Boundary

The receive-address policy layer is independent of BDK. It does not import `org.bitcoindevkit` and does not call wallet/address APIs.

The existing BDK address derivation validation remains desktop-test-only, opt-in, offline, and redacted. That validation proves library behavior but does not feed production UI or storage.

## Secure Storage Boundary

Secure storage remains disabled/fail-closed. The receive-address policy does not create or store seed material, mnemonic material, descriptor private keys, imported keys, Nostr secrets, backend credentials, Lightning credentials, Cashu material, or backup keys.

Future production seed generation should evaluate platform entropy quality and prefer hardware-backed/platform CSPRNG support where available. Hardware entropy detection is not implemented in this pass.

## Testing

Current tests cover:

- displayed does not equal used,
- unconfirmed observation marks an address used,
- confirmed observation marks an address used,
- fresh receive request is blocked for current non-operational descriptor profile metadata,
- mainnet is rejected,
- regtest and signet are allowed policy networks,
- reserved/displayed unused address can be re-shown with observation warning,
- explicit reuse attempt requires confirmation even before backend observation,
- observed address reuse requires explicit warning,
- Nostr identity-linked source warning,
- imported single-key backup warning,
- sentinel display value behavior,
- receive policy files do not import BDK or call BDK wallet/address APIs,
- receive policy files do not add persistence calls or hardcoded address fixtures.

## Next Step

The backend observation and UTXO state boundary is documented in [`BACKEND_OBSERVATION_STATE.md`](BACKEND_OBSERVATION_STATE.md).

The desktop-test-only regtest UTXO scan validation boundary is documented in [`BDK_REGTEST_UTXO_SCAN_VALIDATION.md`](BDK_REGTEST_UTXO_SCAN_VALIDATION.md).

That boundary uses this receive-address policy and the backend observation model as the state foundation for backend-observed address usage. With local `electrs` configured through `SKALD_ELECTRS`, the combined opt-in desktop test observes a funded runtime regtest UTXO through BDK and maps the observation into the Skald-owned model.

Recommended next pass:

```text
recovery/privacy integration planning or production observation persistence boundary
```

That next pass should still avoid production wallet activation, production address persistence, production backend sync, signing, broadcasting, public endpoint defaults, and mainnet.
