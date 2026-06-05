# Descriptor and Key Management Design

## Status

Skald Vault currently has metadata-only descriptor wallet profile workflows. Saved descriptor wallet profiles are non-operational records used for planning and review.

The current implementation does not create or store descriptors, keys, extended public keys, extended private keys, addresses, PSBTs, signatures, transactions, or wallet funds. Descriptor text is represented only by `DESCRIPTOR_TEXT_NOT_STORED`, and key material is represented only by `KEY_MATERIAL_NOT_CREATED`.

Secure storage remains disabled and fail-closed. Android and Linux desktop secure-storage implementations reject secret operations. This document is a prerequisite before adding Bitcoin protocol libraries or descriptor parsing/key-management behavior.

## Scope

This design covers future integration for:

- Native descriptor wallets.
- BIP86 single-key Taproot wallets.
- Imported descriptors.
- Watch-only descriptors.
- Imported single-key wallets.
- Nostr public-key watch-only wallets.
- Nostr identity-key imported spend wallets.
- External signer workflows.
- Hardware signer workflows.
- PSBT workflows.
- Coin-control workflows.
- Backend scanning/querying.
- Descriptor export and backup.
- Recovery state integration.

The design defines boundaries and acceptance gates only. It does not choose a dependency or implement protocol behavior.

## Non-goals

This design does not currently enable:

- Mainnet.
- Real wallet creation.
- Key generation.
- Secret storage.
- Descriptor parsing.
- Descriptor checksum validation.
- Address derivation.
- Script generation.
- UTXO scanning.
- Transaction construction.
- PSBT serialization or parsing.
- Signing.
- Broadcasting.
- Payjoin.
- Lightning.
- Cashu.
- Nostr signing.
- Backend networking.

No Bitcoin, descriptor, Miniscript, secp256k1, PSBT, Nostr, Cashu, Lightning, networking, encryption, or secure-storage dependency is added by this design pass.

## Design principles

- Descriptor-native first: every on-chain wallet must be modeled around descriptors or an explicit future descriptor policy.
- No key material outside the secure-storage boundary.
- No raw secret in UI state, settings storage, logs, documentation, tests, or build history.
- No silent address reuse.
- No implicit coin selection for signing.
- No hidden backend, privacy, custody, or recovery trust boundary.
- No mainnet until explicitly enabled through a separate release-hardening review.
- No Skald-operated infrastructure.
- No fake cryptography.
- No fake PSBTs.
- Fail closed on validation uncertainty.
- Every wallet profile must have explicit backup and recovery status.
- Watch-only wallets must never be presented as spend-capable.
- Imported keys must be isolated and not treated as app-seed-recoverable accounts.
- Protocol library types must not leak directly into Compose UI state.

## Threat model

Descriptor and key-management integration must account for:

- Malformed descriptors.
- Malicious descriptors.
- Secret-bearing descriptors accidentally imported through a public-descriptor flow.
- Watch-only descriptors mistaken as spendable.
- Imported key backup failures.
- Nostr identity-key reuse linking funds and public identity.
- Wrong-network descriptors.
- Backend network mismatch.
- Address reuse.
- Gap-limit and abandoned-address mistakes.
- Backend privacy leakage from wallet queries.
- Extended public key leakage.
- Descriptor export leakage.
- Derivation path mistakes.
- Change-address mistakes.
- Coin-selection privacy mistakes.
- Cross-wallet input mixing.
- PSBT tampering.
- Signing wrong outputs.
- Clipboard substitution.
- Malicious QR imports.
- Unicode or homograph confusion in labels, imported text, and URIs.
- Device compromise.
- Logs or crash reports leaking sensitive data.

The app must assume protocol inputs can be hostile. If parser output, network classification, wallet capability, or recovery status is uncertain, the flow must stop in a typed blocked state.

## Library evaluation criteria

Before choosing any Bitcoin protocol dependency, evaluate:

- Kotlin Multiplatform compatibility.
- Android support.
- Linux desktop/JVM support.
- Descriptor support.
- Descriptor checksum support.
- BIP86 and Taproot support.
- PSBT support.
- Regtest, signet, testnet, and testnet4 support.
- Rust/JNI/FFI complexity if a Rust library is used.
- Reproducible build impact.
- Dependency size and transitive dependency surface.
- Maintenance status.
- API stability.
- Auditability and upstream test coverage.
- Ability to keep secrets behind Skald Vault's secure-storage boundary.
- Ability to support watch-only wallets.
- Ability to support external signer and hardware signer workflows.
- Ability to keep mainnet disabled until explicitly enabled.
- Deterministic testing support with regtest or local fakes.
- Clear error types for unsupported descriptors, wrong network, and invalid checksums.

Any claim about a library capability must be verified against current upstream documentation and tests before implementation.

## Candidate library strategy

Possible future strategies include:

- BDK or `bdk_wallet` for descriptor wallet, address derivation, and PSBT workflows if Kotlin/Android/Linux integration is practical.
- `rust-bitcoin` plus `rust-miniscript` through a wrapper layer if finer-grained control is required.
- `bitcoinj` for JVM-only protocol support if its descriptor/Taproot/PSBT capabilities and KMP limitations are acceptable.
- A small Skald-owned adapter layer around whichever protocol library is selected.

Open items before choosing:

- Whether Android and desktop can share the same protocol implementation.
- Whether Rust FFI/JNI complexity is acceptable for Android and Linux `.deb` packaging.
- Whether the chosen dependency supports testnet4 at the required level.
- Whether the chosen library can parse and classify descriptors without exposing secret payloads to UI code.
- Whether the chosen library can support future multisig, Miniscript, silent payments, and external signer flows.

The implementation should isolate protocol libraries behind Skald domain/service interfaces. UI and settings should consume Skald-owned models, not raw dependency types.

## Kotlin Multiplatform boundary

Common Kotlin code should keep:

- Domain models.
- Metadata-only settings models.
- Validation result models.
- Recovery models.
- Coin-control and PSBT workflow states.
- Service interfaces.
- Deterministic fake implementations for tests.

Platform or protocol implementation code may provide:

- Descriptor parsing and classification.
- Address derivation.
- Descriptor checksum validation.
- PSBT construction/parsing.
- Backend-specific scan adapters.
- External signer adapters.

Boundary rules:

- Protocol types must be converted into Skald-owned result types before crossing into UI.
- Secret payloads must not cross into common UI state.
- Any private-key or seed operation must go through `SecureSecretStorage`.
- Tests should use deterministic fakes first, then regtest/signet/testnet harnesses when protocol implementation exists.
- Mainnet must remain disabled by policy even if a library supports it.

## Descriptor wallet model

Future descriptor wallet implementation should keep distinct model layers:

- Metadata profile: non-secret label, origin, network, status, warnings, backup state, and selected state.
- Descriptor material: parsed descriptor, script policy, checksum, spend/watch classification, and descriptor export state.
- Secret material: seed, private key, private descriptor component, or identity-key material referenced only through secure storage.
- Public/watch-only material: public descriptor or public key data that is non-secret but privacy-sensitive.
- Backend state: selected backend profile, sync state, scan state, and backend trust class.
- Recovery state: backup requirements, descriptor export status, imported-key backup status, and external signer requirements.
- Address index state: receive/change index tracking, gap-limit state, and displayed-but-unused address state.
- Label metadata: wallet labels, address labels, UTXO labels, and clusters.
- UTXO metadata: backend-sourced UTXOs, labels, privacy state, spendability state, and reservation state.
- PSBT draft state: selected inputs, outputs, fee/change review, external signer state, and broadcast blocker state.

Today only metadata profiles exist. Descriptor material, address index state, scan state, and key material remain absent.

## Supported wallet types

### Native BIP86 Taproot descriptor wallet

- App-seed backed.
- Requires secure storage before seed material exists.
- Requires descriptor derivation and export policy.
- Requires descriptor export or strongly recommended export before use.
- Must start on regtest, signet, testnet, or testnet4.
- Must not enable mainnet until separate approval.

### Imported descriptor wallet

- May be watch-only or spend-capable depending on descriptor content.
- Requires syntax, checksum, network, and capability validation.
- Requires a storage policy for public descriptors and any secret-bearing content.
- Requires backup warning and Recovery Center state.
- Must not auto-enable spending after import.

### Watch-only descriptor wallet

- Cannot spend.
- May coordinate PSBTs later.
- Public descriptors are non-secret but privacy-sensitive.
- Backend queries can reveal wallet activity.
- Storage is allowed only after descriptor validation and privacy warning.

### Imported single-key wallet

- High-risk and discouraged for meaningful balances.
- Must be treated as a separate wallet profile.
- Not recoverable from the app seed.
- Requires separate backup acknowledgement.
- Should default to isolated recovery/sweep workflows.
- Requires secure storage before any private key can be retained.

### Nostr public-key watch-only wallet

- Conceptually maps a Nostr public identity key to a Taproot watch profile.
- Watch-only only.
- Cannot spend.
- Requires identity-linkage warning.
- No real Nostr public-key parsing should occur before library/design review.

### Nostr identity-key spend wallet

- High-risk identity-key reuse flow.
- Requires explicit identity and funds blast-radius acknowledgement.
- Should be import-only or ephemeral by default until reviewed.
- Must not be recommended for meaningful balances.
- Requires secure storage before any identity private-key material can be retained.
- No real Nostr private-key parsing or signing should occur before review.

### External signer and hardware signer wallet

- Preferred for high-value use.
- Should use descriptor/watch-only coordinator mode.
- Requires external signer control and backup warnings.
- Requires PSBT export/import and output verification.
- Must not imply Skald Vault can restore signer-held keys.

## Network policy

- Development networks only for now: regtest, signet, testnet, and testnet4.
- Mainnet remains disabled.
- Wrong-network descriptor detection is required before future descriptor import or derivation.
- No silent fallback between networks.
- Backend network must match wallet network.
- Address display must show network and script policy.
- Any dependency defaulting to mainnet must be wrapped or rejected by Skald policy.

## Secret material boundary

This design depends on `docs/SECURE_STORAGE_DESIGN.md`.

Rules:

- No raw seed, private key, identity private-key material, extended private key, or private descriptor in UI state.
- No raw secret in settings storage.
- No raw secret in logs, errors, screenshots, README, docs, tests, or `BUILD_HISTORY.md`.
- Descriptor private material requires an explicit secure-storage decision.
- Public descriptors and extended public key material are non-secret but privacy-sensitive.
- Public descriptor exports must warn that they can reveal wallet structure and activity when used with a backend.
- Secret-bearing protocol outputs must be redacted before any UI or logging boundary.

## Descriptor import policy

Future descriptor import must use a high-friction flow:

1. Accept input only in a dedicated import screen with warnings.
2. Normalize and validate input without logging it.
3. Validate syntax, checksum, network, script policy, and supported constructs.
4. Classify watch-only versus spend-capable.
5. Detect secret-bearing descriptors.
6. Reject unsupported constructs.
7. Warn about backup, recovery, and privacy implications.
8. Store only after validation and user confirmation.
9. Never auto-enable spending without recovery review.
10. Never derive receive addresses until backend and wallet network match.

Unsupported or uncertain descriptors must fail closed with typed reasons.

## Watch-only policy

- Watch-only profiles cannot spend.
- Watch-only profiles may coordinate future PSBT workflows.
- Public descriptors and public key material are privacy-sensitive.
- Backend queries can leak the wallet's activity and graph.
- Descriptor export/import warnings must remain visible.
- A watch-only profile must never show spend, sign, or broadcast capability.

## Imported single-key policy

- Imported single keys are high-risk.
- Imported single keys are not hierarchical app-seed accounts.
- Separate backup is mandatory.
- They should be treated as isolated wallet profiles.
- They are suitable mainly for sweep/recovery flows, not long-lived meaningful balances.
- They must not mix UTXOs with other wallet profiles without an explicit future design and warning.

## Nostr npub/nsec policy

Skald Vault has two distinct Nostr-related on-chain stories:

1. Intentional public identity-bound on-chain payments or zaps.
2. Privacy-conscious recovery/sweep from funds sent to a Nostr public key.

Rules:

- Public identity-bound payments must be explicit.
- Recovery/sweep flows cannot erase the public history of payments sent to a Nostr-linked address.
- Nostr identity-key spend profiles require identity/funds blast-radius warning.
- Nostr public-key watch profiles cannot spend.
- No real Nostr public-key or private-key parsing is allowed until dependency/design review.
- Skald Vault must not recommend reusing an identity key for meaningful funds.
- Nostr-linked UTXOs must not be mixed with other wallet UTXOs without explicit privacy review.

## External signer and hardware signer policy

- External and hardware signers should be first-class on-chain wallet profiles.
- Skald Vault should coordinate descriptors, labels, backend sync, coin control, and PSBT review.
- Signer-held keys are not recoverable from Skald Vault.
- Signer fingerprint, policy, and pairing metadata are non-secret but privacy-sensitive.
- PSBT export/import must include output, change, fee, and wallet identity verification.
- High-value funds should prefer hardware/external signer flows over local hot-key storage.

## Address derivation policy

Future address derivation must enforce:

- Every receive action derives or reserves a fresh address.
- Displayed-but-unused addresses are tracked separately from confirmed used addresses.
- Address reuse is disabled by default.
- Any explicit address reuse requires warning and confirmation.
- Receive derivation and change derivation are separate.
- Change output and derivation context must be shown before signing.
- Gap-limit policy must be explicit and test-covered.
- Address display must show network and script policy.
- Clipboard and QR display risks need UI treatment.
- No address derivation should occur before descriptor validation and network selection are complete.

## UTXO scanning policy

- No real scanning until a backend profile is configured and validated.
- Backend test/sync behavior must distinguish simulated validation from real network use.
- User-owned node is preferred.
- Public backend queries can leak wallet activity.
- Scan state must track backend profile, network, gap-limit state, last scan result, and privacy warnings.
- Rescan behavior must be explicit.
- Labels and clusters must be preserved through scans.
- Metadata privacy must be protected; backend data should not silently overwrite user labels.

## Coin-control integration

Existing coin-control planner behavior should become the mandatory send boundary for real spends.

Rules:

- Every real send requires selected inputs.
- Suggested coin selection may exist only if the user reviews it.
- No implicit coin selection can proceed to signing.
- Privacy warnings must be shown before signing.
- Cross-wallet input mixing is blocked unless a future advanced flow explicitly models and warns about it.
- Change review and fee review are mandatory.
- Backend/broadcast path must be visible before signing and broadcasting.

## PSBT integration

Future PSBT flow should include:

1. Draft.
2. Coin-control review.
3. Fee/change review.
4. PSBT construction.
5. External signer export/import.
6. Signature verification.
7. Final review.
8. Broadcast approval.
9. Failure handling.

PSBT requirements:

- No PSBT can be signed without explicit selected-input, output, change, and fee review.
- Imported signed PSBTs must be checked against the original draft.
- Outputs must be verified before finalization.
- Change outputs must belong to the expected descriptor wallet.
- Fee deltas must be visible.
- Broadcast approval is separate from signing approval.
- Tampering, missing inputs, unknown outputs, or mismatched wallet/network state must fail closed.

## Recovery implications

- App seed recovery requires secure storage, derivation policy, descriptors, address index state, and metadata recovery.
- Descriptor export remains important even for native seed-backed wallets.
- Imported descriptors require separate descriptor backup.
- Imported single-key wallets require separate key backup and are not restored by app seed.
- Nostr identity-key material requires separate identity-key backup and risk acknowledgement.
- External signer wallets require signer control and external backup.
- Watch-only profiles do not prove spend recovery.
- Metadata backup preserves labels, selected backends, address index state, and recovery checklist state.
- One seed does not restore every rail or every wallet profile.

## Backup and export implications

Future backup/export design must include:

- Descriptor export.
- Watch-only descriptor export.
- Encrypted metadata backup.
- Label backup.
- Address index state backup.
- External signer policy export.
- Clear exported-data classification.
- No Skald-operated cloud backup.
- No automatic Skald server backup.
- User-chosen backup locations only.

Secret exports must follow the secure-storage design and require high-friction confirmation. Public descriptor exports must warn about privacy leakage.

## Error handling and validation

Fail closed for:

- Invalid descriptor.
- Unsupported descriptor.
- Descriptor checksum failure.
- Wrong network.
- Mainnet disabled.
- Missing secure storage.
- Backend mismatch.
- Corrupted metadata.
- Malformed import.
- Duplicate wallet identifiers.
- Conflicting address indices.
- Missing recovery requirements.
- Unknown script policy.
- Unsupported Taproot, multisig, Miniscript, or silent-payment construct.

Errors should be typed and user-facing where appropriate. Error strings must never include raw secret material.

## Testing strategy

Before real implementation, define tests for:

- Descriptor parser behavior.
- Invalid descriptor rejection.
- Malicious descriptor rejection.
- Wrong-network rejection.
- BIP86 derivation using safe known test vectors only after a library is added.
- Watch-only classification.
- Secret-bearing descriptor detection.
- Secret-bearing descriptor rejection or secure-storage routing.
- Nostr public/private-key classification.
- Address reuse prevention.
- Gap-limit behavior.
- Change address separation.
- PSBT review and tampering checks.
- Recovery state updates.
- Backend network mismatch.
- Mainnet disabled guards.
- No-secret-logging guards.
- Regtest integration before any mainnet discussion.

Do not add actual descriptor, key, address, PSBT, or transaction test vectors in this design pass.

## Acceptance criteria before adding Bitcoin libraries

Bitcoin protocol libraries must not be added until:

- This design is reviewed.
- The secure-storage design dependency is satisfied for any secret-bearing path.
- A candidate library is selected.
- The dependency and transitive dependencies are reviewed.
- Kotlin Multiplatform/platform boundary is decided.
- No-mainnet guard tests are in place.
- Secret-boundary tests are in place.
- Descriptor import validation plan is accepted.
- Recovery Center integration plan is accepted.
- Regtest/signet/testnet harness plan is accepted.
- Build and packaging impact is understood.
- `BUILD_HISTORY.md` is updated.
- The user explicitly approves adding a Bitcoin protocol dependency.

## Open questions

- BDK versus `rust-bitcoin`/`rust-miniscript` versus JVM-library strategy.
- Rust FFI/JNI versus JVM-only tradeoffs for Android and Linux.
- BIP86-only first versus broader descriptor support.
- When to support multisig and Miniscript.
- How to support external signers on Android and Linux.
- Whether Nostr identity-key import should be ephemeral by default.
- How to handle silent payments scanning.
- Whether address index state belongs in encrypted metadata.
- How to build deterministic regtest harnesses.
- Whether protocol libraries can support testnet4 at the required maturity.
- How to package native protocol code reproducibly for `.deb` and Android APK targets.

## Implementation sequence

1. Complete design review.
2. Select library/dependency strategy.
3. Add the chosen library behind a non-operational interface.
4. Add parser/classifier tests with safe test vectors.
5. Implement testnet/regtest-only descriptor validation.
6. Implement watch-only descriptor import first.
7. Implement address derivation for watch-only testnet only.
8. Implement backend scan on regtest/signet only.
9. Implement PSBT construction without signing.
10. Integrate secure storage before private-key wallets.
11. Only later enable private-key wallet creation/import.
12. Keep mainnet disabled until separate explicit approval.

No step should enable key generation, secret persistence, signing, broadcasting, Payjoin, Lightning, Cashu, Nostr signing, backend networking, or mainnet by implication.
