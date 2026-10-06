# Monero Wallet Boundary Decision

**Monero target and proposed boundaries; engine, protocol, transport and storage selections deferred.**

Recorded: 2026-10-04. Audited baseline: `a9c239a125622ce0071b2a8f649aa677ef185cec`.

This document records the Monero product target and proposes the boundaries that future implementation must review. It is not a production authorization, dependency selection, protocol compatibility claim, or implemented interface. After the authorized protocol-removal pass, the executable is an inert Monero-oriented scaffold. No operational Monero engine, daemon adapter, LWS adapter, or canonical encrypted-vault persistence exists. The [migration map](MONERO_PIVOT_MIGRATION_MAP.md) separates the current work from the pinned historical inventory.

## Established intent and deferred choices

The product target supplied for this pass is a native Android/Linux Monero wallet with an app-controlled encrypted vault. Its default connectivity mode will use the user's private full node and local wallet scanning, with the endpoint initially unconfigured. Optional custom LWS requires explicit privacy warnings and consent before disclosure. There will be no Skald-operated infrastructure, default public endpoint, automatic public fallback, or protocol-service fee collection.

The boundary requirements below express the intended security constraints for later passes. They select no engine, JNI wrapper, subprocess architecture, transport library, LWS version, recovery format, or native storage contract. Documentation of an intended operation never authorizes executing it. Mainnet, real funds, production key handling, wallet import/create, account/subaddress generation, password entry, signing, relay, and persistence remain disabled.

The user explicitly authorized protocol removal and direct Monero validation/classification placeholders while preserving vault architecture, prototype evidence and closed operational gates. Local governance was reconciled at the end of the removal session under that direct instruction; subsequent sessions read the updated files. Earlier protocol/network approvals grant no Monero network permission. Deterministic offline tests are the current scope. The subsequent presentation-only pass simplifies the UI to Wallet and Settings. Work next returns to separately scoped canonical vault completion.

## Decisions made in the removal pass

- Secret categories cover Monero recovery material, private spend and view keys, transaction secret material, daemon and LWS credentials. Generic imported/proxy/backup classes survive only where retained contracts need them. Every secret category requires secure storage; classification accepts no secret payload.
- Sensitive metadata covers account/subaddress state, restore context, scan checkpoints, owned-output/spent state, transactions and LWS disclosure history, alongside generic labels/notes/recovery/endpoint/routing classifications. All reads, listing, writes and deletes stay unavailable. These are sensitivity labels, not scanner or serialization designs.
- `MoneroNetworkEvidence` labels `Unspecified`, `Mainnet`, `Testnet` and `Stagenet` independently of authorization. `Unspecified` is the default; mainnet is explicitly rejected and every other identity remains blocked by unavailable storage/provider gates. There is no selectable runtime network, port, chain probe or ordinal migration.
- Typed material declarations select safe rejection categories for addresses, recovery material, private keys, transaction secrets and credentials. Shape-only recognition, where present, is defensive classification only: a 64-hex string has ambiguous cryptographic meaning; address-shaped text does not prove checksum, network, ownership or key validity. Unknown material is still rejected.
- Vault identifiers, paths and provider evidence remain container infrastructure. Their positive admission is restricted to retained app-owned forms, fixed layout constants and supported synthetic evidence. Candidate recognition never grants admission. This is a deliberate restriction of placeholder acceptance, not a future canonical ID encoding; no old rejected input may become accepted and existing approved vault fixture bytes remain unchanged.
- The subsequently simplified UI has two destinations, Wallet and Settings, with unavailable balances and operations. Wallet contains one empty state; Settings presents read-only Vault, Connection, and About information. It has no settings persistence, wallet service or network client. Local full-node scanning and optional LWS remain target descriptions rather than selectable implementations.

These choices are metadata and rejection-policy decisions. They do not choose recovery word counts/languages, Monero address decoding, key/scalar validation, cryptography, amount arithmetic, JNI interfaces, a daemon protocol or LWS compatibility. Monero material may eventually be valid encrypted payload content under a separately approved vault policy; the present restrictions prevent it becoming raw identifiers, paths, diagnostics or inert evidence.

For limited shape classification, primary documentation was pinned to Monero Docs commit `2b50e81860579f0a7dbe3abb306709c5939bb684`: [standard addresses](https://github.com/monero-project/monero-docs/blob/2b50e81860579f0a7dbe3abb306709c5939bb684/docs/en/public-address/standard-address.md), [subaddresses](https://github.com/monero-project/monero-docs/blob/2b50e81860579f0a7dbe3abb306709c5939bb684/docs/en/public-address/subaddress.md), [integrated addresses](https://github.com/monero-project/monero-docs/blob/2b50e81860579f0a7dbe3abb306709c5939bb684/docs/en/public-address/integrated-address.md), and [private keys](https://github.com/monero-project/monero-docs/blob/2b50e81860579f0a7dbe3abb306709c5939bb684/docs/en/cryptography/asymmetric/private-key.md). Standard/subaddress candidates use a 95-character shape and integrated candidates 106; neither shape identifies network or establishes validity. No upstream sample wallet material is copied into the repository, and no decoder/checksum/scalar implementation is introduced.

### Exact placeholder admission restriction

The retained grammars use [SkaldVaultV1ApprovedInputDomain](../composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1ApprovedInputDomain.kt) and bounded constructors, including [secret metadata](../composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/domain/security/SecretModels.kt) and [sensitive metadata](../composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SecureMetadataStorage.kt). Each caller's prior source/shape/root/purpose gates remain enforced:

| Accepting boundary | Restricted form |
| --- | --- |
| Storage/path segment | Eighteen existing fixed layout/fixture tokens, or the existing `vault_`/`record_` encoding followed by exactly 32 lowercase hex characters from a 16-byte identifier. No arbitrary suffix, case folding or trimming. |
| Disabled-facade record identity | Existing `safe_record_fixture`, or the exact `record_` encoding above. |
| Linux root evidence | Eight previously supported static fixtures, plus the unchanged root/source/containment policy checks. This is not a usable arbitrary filesystem-path configuration feature. |
| Android root evidence | Absent evidence or one of two existing fixed synthetic evidence tokens. |
| Public redaction evidence identity | The existing policy identifier or existing public non-wallet vector identifier. |
| Synthetic provider evidence | Ten existing family/purpose pairs and three existing display labels; no arbitrary suffix or credential-derived identity. |
| Android calibration text | Eleven existing captured/fixture strings. Numerical calibration/KDF behavior is unchanged; new device evidence text requires a later reviewed admission change. |
| Secret metadata placeholders | Five existing generic ID fixtures; labels drawn from the supported secret-kind label plus the existing metadata suffix; the fixed unavailable creation-description sentinel. IDs/labels/metadata diagnostics are redacted. |
| Sensitive metadata record identity | The existing disabled metadata identity token, with redacted display. |

This is a narrowed scaffold contract, deliberately unsuitable for accepting real wallet/user paths or arbitrary metadata. The fixed synthetic forms are not canonical vault encoding decisions. Each new accepted form was accepted at baseline; the entire fixed-width encoded-ID family is checked structurally as a subset, with bounded generative and actual baseline-comparison evidence supplementing that reasoning. Unknown raw requests may still be constructed as untrusted requests for rejection tests, but they cannot become accepted evidence through these validators. No old data import or persisted-format compatibility is promised.

## Canonical vault authority

The [July 12 canonical vault decision](ENCRYPTED_LOCAL_VAULT_V1_CANONICAL_ARCHITECTURE_DECISION.md) remains authoritative and unchanged. Its status is `CANONICAL_ARCHITECTURE_SELECTED_IMPLEMENTATION_BLOCKED`.

The architecture selects an app-controlled multi-artifact live-vault directory: a minimal public header with a wrapped-root envelope, one authoritative encrypted authenticated manifest, independently encrypted opaque records, temporary staging, and quarantine/recovery artifacts. Backup/export is a separate format with an independent key hierarchy.

Argon2id derives an exactly 32-byte key-encryption key (KEK). That KEK wraps a random exactly 32-byte vault root using XChaCha20-Poly1305. HKDF-SHA-256 separates manifest, metadata-record, and secret-record roots and derives distinct per-record keys. The vault root is not a direct AEAD key; records do not share one record key. Each record encryption requires a fresh random 24-byte nonce. Wrapped-root AEAD associated data authenticates the canonical public-header context. There is **no separately persisted header HMAC**.

Canonical encoding must be bounded, big-endian, strictly ordered, and nonrecursive, rejecting duplicate fields, unknown fields/features, and trailing input with checked allocation arithmetic. New record artifacts are installed before the authoritative encrypted manifest is atomically replaced as the final commit point. Durability failures must fail closed, with nondestructive quarantine and recovery rather than silent destructive repair. A consistent old whole-vault snapshot cannot reliably be detected without an external monotonic anchor; no such anchor is present.

All canonical production vault cryptography must route through Skald's `VaultCryptoProvider`, with opaque provider-owned key handles, owned-copy sensitive byte types, redacted diagnostics, and practical clearing on lock/close. Linux remains passphrase-first; an OS keyring is not primary storage. Production selection is `DisabledVaultCryptoProvider` only and `productionProviderSelectable=false`. Create/open/unlock/encrypt/decrypt, runtime vault keys and nonces, sessions, secure secret/metadata persistence, production parser integration, vault file I/O, sync, signing, broadcasting, and mainnet remain blocked.

Earlier `SkaldVaultV1ContainerFormat`, `SkaldVaultV1ManifestFormat`, `SkaldVaultV1HeaderCommitment`, `SkaldVaultV1RecordAead`, and their direct helper/fixture/KAT/atomicity graph are `PRE_RELEASE_PROTOTYPE_P0_UNSUPPORTED`. `EncryptedVaultWorkingParser` is `SYNTHETIC_TEST_CONTRACT_CLASSIFIER_ONLY`, not a canonical vault parser. Some direct helpers and the commonMain integrated KAT harness are executable and production-compiled, without app/storage integration; provider-selection rejection does not make those public helpers non-executable. Existing executable test crypto remains evidence in its current scope. Neither prototypes nor test success authorize a Monero vault, production provider selection, or persistence. Prototype quarantine and reachability restrictions need a separate pass; the pivot must not promote old helpers for convenience.

Exact canonical magic, field identifiers/framing, header AAD bytes, passphrase normalization/UTF-8 process, record envelope overhead, and final device KDF parameters remain deferred. Supported canonical persistence does not yet exist, so this plan promises no Bitcoin-to-Monero vault migration or P0-byte compatibility.

## Proposed boundaries

| Boundary | Owns | Must not grant or expose |
| --- | --- | --- |
| Skald wallet domain | Amount/network/capability policy, redacted state, recovery and operation review | Native/JNI/RPC types, raw keys, implicit spend readiness |
| Local wallet engine | Local keys, restore/create, full-node scanning, spend detection, transaction construction and signing | Independent storage, hidden networking, automatic signing/relay |
| Full-node adapter | Approved chain-data requests and explicitly approved relay to selected `monerod` | Wallet secrets, daemon administration, endpoint discovery or fallback |
| Optional LWS adapter | Reviewed server-scan protocol after wallet-specific consent | Spend keys, recovery seeds, implicit disclosure or mode switching |
| Transport policy | Endpoint identity, route enforcement, credentials by handle, bounds/deadlines | Credentials embedded in URLs, redirects with disclosed keys, proxy-to-direct retry |
| Vault/session/persistence | Authorization lifetime and encrypted state commits | Engine-specific persistence exemptions or secret access while locked |
| Transaction workflow | Independent build, sign, and relay approvals bound to reviewed state | Approval reuse after state changes or relay merely because signing succeeded |
| Android/Linux bridge | Native packaging, lifetimes, cancellation, safe error translation | Raw native errors, unowned callbacks, secret material in UI state |

These are responsibilities, not executable interfaces or registrations. Adding a factory, FFI/JNI binding, RPC client, launcher, endpoint parser, or capability flag requires a later bounded implementation pass.

### Skald-owned wallet domain

Future domain types need Monero network identity; checked integer atomic-XMR amounts; opaque wallet/account/subaddress identifiers; full, watch-only, and unavailable capabilities; restore context; scan checkpoints/provenance; owned-output and transfer state; and transaction review/build/sign/relay states. No floating-point money, numeric conversion of legacy sats, or reuse of Bitcoin enum ordinals is permitted. One XMR is 10^12 atomic units; representation, maximum values, checked arithmetic, and display parsing/formatting remain design decisions. [Monero daemon RPC documentation](https://docs.getmonero.org/rpc-library/monerod-rpc/)

Full node and LWS are connectivity/scanning modes for one asset. They are not separate assets or custody rails. Network identity is distinct from permission to use that network: naming mainnet cannot authorize mainnet. Offline fixtures are evidence, not a selectable chain.

State must distinguish endpoint unconfigured, transport reachable, chain checked, daemon synchronized, wallet scanned, wallet locked, and operations blocked. Total, unlocked, and pending balances need provenance and completeness. Unknown is not zero; a server success response is not proof of a verified balance. Stale scans, reorgs, interrupted work, uncertain spent state, and incomplete key-image information must stay visible and prevent unsupported spend-ready claims.

### Local wallet engine and platform bridge

The local engine owns wallet keys and provides reviewed Monero protocol behavior behind Skald-owned boundaries. In full-node mode it scans locally; signing stays local in either mode. Use a reviewed implementation rather than inventing Monero cryptography in Kotlin. Existing vault crypto dependencies are not Monero protocol engines.

Engine selection requires evidence for pinned versions, maintenance, licensing, reproducible builds, Android ABI/JNI packaging, Linux packaging, memory ownership, native thread/lifetime rules, cancellation, and bounded export/storage hooks. Neither Monero core nor `lwsf` is selected here. The `lwsf` project describes a shared `wallet2_api.h` interface for local and remote scanning but also distinct persisted formats. That establishes a candidate to investigate, not Android/Linux compatibility or compliance with Skald's vault. [lwsf project documentation](https://github.com/vtnerd/lwsf)

`monero-wallet-rpc` is a separate wallet service, with wallet creation, key access, storage, and transaction operations; it is not the chain-data daemon `monerod`. A remote key-bearing wallet-RPC service is outside the default native-wallet boundary. A separately authorized, isolated test-only oracle may be useful later, without granting production service or persistence permission. [Monero wallet RPC documentation](https://docs.getmonero.org/rpc-library/wallet-rpc/)

The bridge must translate errors to bounded Skald-owned reasons, keep secret/native types out of Compose state, and associate callbacks with the correct wallet, session, operation, and generation. Cancellation and lock must invalidate pending callbacks before they can publish results, disclose keys, or commit state. Reopening a wallet cannot make an old callback current. Native memory clearing is best effort and must be described honestly, including copies outside managed ownership.

### Engine storage is an admission blocker

Before selecting a bridge, inventory every native key file, wallet cache, database, autosave, temporary artifact, log, crash dump, default directory, native callback, and background write. Include error, cancellation, shutdown, and interrupted-initialization paths, and assess swap/crash exposure. A reviewed path must prevent library defaults from creating an independent sensitive store.

Native wallet files encrypted with a separate engine password do not automatically satisfy Skald's ownership, authentication, lifecycle, or atomicity contract. Review whether state can remain in memory and be exported into bounded vault records, or whether a subordinate encrypted blob adapter can satisfy the canonical contract. Neither choice is made here. No engine writes may bypass reviewed Skald persistence or occur while it is disabled.

The canonical record plaintext limit is 4,194,304 bytes; ciphertext payload has the same bound before separately bounded envelope overhead. A multi-megabyte native cache cannot be assumed to fit. Segmentation would need authenticated ordering, consistent generations, resource bounds, crash semantics, and a coherent manifest commit. Do not widen the vault limits or add ad hoc files to accommodate a convenient engine.

Prove storage isolation before accepting engine packaging as a viable integration. An engine requiring unapproved plaintext or independently persisted sensitive state fails admission, even if its cryptography and UI bridge otherwise work.

### Full-node daemon adapter

The target default is full-node/local scanning with **no configured endpoint**. The user selects their own `monerod` for chain data and eventual relay; the local wallet engine retains keys, scans, and signs. This does not mean Skald launches a daemon, embeds a blockchain on Android, or automatically connects to loopback. A desktop user may run a separate node, while Android may connect to the user's remote node. Android loopback denotes the phone itself. Bundling or managing a daemon remains unresolved and outside initial passes.

The daemon adapter must never transmit seed, private spend-key, or private view-key material. The daemon's documented chain-data and relay APIs are separate from wallet operations; they also expose administration/mining methods that Skald's wallet adapter must exclude. Use a minimal reviewed method allowlist and prefer restricted RPC. [Monero daemon RPC documentation](https://docs.getmonero.org/rpc-library/monerod-rpc/)

Endpoint ownership and daemon trust are explicit user policy and evidence, not conclusions derived from a private IP, onion address, TLS certificate, port, or label. Check chain/network identity independently of those labels, subject to the trust limits of server responses. Keep daemon height/synchronization, wallet scanned height, mempool state, unlocked funds, spent certainty, and reorg state distinct. Privacy review must also account for request patterns and transaction relay metadata; keeping keys local alone does not eliminate server observation.

No public defaults, server discovery, hidden telemetry, public price lookup, bootstrap substitution, or automatic fallback may be introduced by the app or engine. Node administration and remote wallet-RPC hosting are outside this boundary. A configured node failing must produce a safe, bounded failure with state marked incomplete where appropriate.

### Optional LWS and disclosure consent

LWS is optional server-side scanning with a different privacy boundary. Traditional Monero LWS accepts private view-key material for scanning; the `monero-lws` implementation documents storing view keys in its database and scanning in the background. Consequently, disclosing a view key gives the server continuing scan visibility, not merely access for one HTTP request. The selected protocol may let the server link wallet identity, incoming outputs and amounts, scan activity, and connection identity. Exact scope requires pinned compatibility review. [monero-lws project documentation](https://github.com/vtnerd/monero-lws)

Before any wallet-specific LWS request, require explicit opt-in bound to all of:

- The selected wallet identity.
- The selected normalized endpoint identity.
- The selected protocol/version and scan-key/disclosure scope.
- The privacy-warning version the user reviewed.

Any change to these bindings invalidates consent. Future policy must also define consent lifetime, expiry/revocation, and restoration behavior; no global forever-consent shortcut is acceptable. Request dispatch must recheck consent and the active session, including after queued work, retries, lock/unlock, or endpoint changes. Consent absence or invalidation must fail before secret access or key-bearing request construction.

Warnings must explain server visibility, trust and completeness limits, and irreversible disclosure. LWS must never receive a private spend key or recovery seed. Consent grants no permission to spend, sign, relay, enable a network, select a provider, unlock a vault, or bypass persistence gates. Protocol-specific key-image exchange, transaction-building behavior, and subaddress coverage require separate review; do not assume all LWS versions behave identically or provide complete spent/outgoing state.

Do not add preselected consent, background enrollment, key-bearing health checks, redirect-followed key submission, or automatic daemon-to-LWS fallback. A transport probe must not silently become wallet registration. Endpoint redirects cannot transfer consent to another destination. Retries must preserve the same approved wallet, endpoint, protocol, disclosure scope, route, and session requirements.

Disabling LWS stops future disclosure and requests but cannot make a server forget a view key already sent. Switching to full-node mode cannot restore that historical secrecy. Even a personally operated LWS places scan-key material on another process/system; do not erase that distinction with an ownership label. Consent and disclosure history are sensitive metadata and ultimately require encrypted persistence; no plaintext consent record is authorized now.

### Transport and endpoint policy

Keep normalized endpoint identity, credential handles, route policy, timeout/resource limits, and trust assertions separate. Define scheme/path/port allowlists; reject URL userinfo, unexpected query/fragment content, and ambiguous encodings. Review hostname, IPv6, IDNA, DNS resolution, TLS verification, redirect behavior, and proxy authentication before implementing normalization or clients. Credentials must never be embedded in endpoint strings or exposed through errors.

User-owned loopback and LAN nodes must be supported deliberately. A public-service SSRF denylist cannot simply prohibit the user's private node. Cleartext loopback/LAN exceptions, TLS, VPN/onion handling, and how the user recognizes the selected endpoint remain deferred decisions. Legacy Bitcoin endpoint policy does not decide them.

Tor-required traffic must fail closed when the approved route is unavailable, with no proxy-to-direct retry and no DNS leakage. Review all native-library network paths as well as Skald's explicit clients. Bound response sizes, decompression, parsing, pagination, concurrent requests, retries, and deadlines. Cancellation, malformed or partial replies, stale data, and unsolicited server fields require safe failure without secret-bearing diagnostics.

### Session, persistence, transaction review, and recovery

Future vault sessions authorize engine key access through opaque handles. Lock, timeout, close, and applicable background transitions must stop disclosures/network work, invalidate approvals and pending work, clear decrypted keys as far as practical, and prevent stale callbacks from committing state. Android background execution and any permitted locked-state behavior need a separate lifecycle decision; no background scanner or key-retention exception is authorized here.

Every engine-state commit must use the approved encrypted persistence path. Seed/recovery material, private view/spend keys, transaction secrets/proofs as applicable, daemon/LWS credentials, and vault/backup keys require explicit secret classification. Wallet names, restore heights, scanned checkpoints, owned/spent state, accounts/subaddresses, transaction notes, routing/endpoints, and LWS disclosure history require sensitive-metadata treatment. Keep all storage reads/listing/writes/deletes disabled until the appropriate gates pass.

The future transaction workflow must show recipients, integer amount, fees, relevant engine-supplied weight/policy data, selected outputs and privacy implications, change, unlock state, expiry, failure behavior, and the chosen relay route. Build, sign, and relay require independent approvals tied to the reviewed wallet, network, transaction intent, scan state, and session. Changed recipients, fees, output selection, route, or stale/reorg state must invalidate affected approvals. A returned signed blob never implicitly authorizes relay. Detailed Monero output-selection, decoy/privacy, change-display, and offline-signing policy remains unresolved; do not mechanically import PSBT/UTXO models or weaken existing review requirements.

Watch-only capability must not imply signing ability or complete spent-state knowledge. Monero's view-only guidance explains that incoming visibility alone cannot give a correct spent balance without relevant key images. Skald must expose completeness and the supported import/export scope rather than presenting uncertainty as zero or spendable funds. [Monero view-only wallet guidance](https://www.getmonero.org/resources/user-guides/view_only.html)

Recovery design must precede create/import flows. Decide supported seed/recovery formats and languages, restore height, account/subaddress reconstruction, watch-only import, spend detection/key-image completeness, transaction-key retention, notes, and metadata backups. Do not assume BIP39, one universal seed length, or that a seed restores all wallet metadata. Keep backup/export separate from live vault storage as the canonical decision requires.

Legacy Bitcoin settings/preferences/files must remain untouched by the runtime cutover, without being interpreted as Monero, converted numerically, automatically imported, overwritten, or deleted. Use separate versioned Monero namespaces only after approved storage design. Existing plaintext platform settings adapters are not wallet stores; any reusable harmless-preference subset needs an explicit sensitivity review.

## Independent negative properties for later validation

This table defines future tests to derive independently of the eventual implementation. It is not evidence that these behaviors have been exercised or implemented.

| Boundary | Required negative property and evidence |
| --- | --- |
| Domain | Negative, overflowing, malformed, excessive-precision, and out-of-range amounts fail; unknown balance cannot become zero; Bitcoin payloads cannot deserialize as Monero state. |
| Daemon | Unconfigured endpoints, wrong chain, misleading labels, stale/partial replies, and unavailable routes cannot produce spend-ready state; outgoing request inspection proves no wallet secret transmission. |
| LWS | Missing/expired/revoked consent or changed wallet/endpoint/protocol/scope/warning fails before key access or request construction; health checks and redirects cannot disclose keys. |
| Session/concurrency | Lock/cancel during native callbacks, queued requests, retries, or commit invalidates old work; reopened sessions cannot accept old results. |
| Storage | Engine initialization, scan, autosave, failure, shutdown, and cleanup cannot write sensitive state outside approved storage or while persistence is disabled; oversized state fails without widening limits. |
| Transport | Proxy failure cannot retry directly; Tor DNS cannot leak; malformed/oversized/compressed responses, timeouts, and interrupted transfers stay bounded. |
| Transactions | Build/sign/relay cannot approve one another; stale state or changed review invalidates approval; watch-only state cannot sign; duplicate/interrupted relay must report uncertainty safely. |
| Recovery | Partial scans, absent key images, missing metadata, and incompatible recovery formats cannot produce complete-recovery claims. |
| Redaction | Secrets, key-bearing LWS bodies, credentialed endpoints, wallet database bytes, raw native errors, and stack traces cannot reach logs, analytics, UI diagnostics, screenshots, or test reports. |
| Legacy data | Existing sats/descriptors/backend settings are neither read as Monero nor silently removed during the coordinated cutover. |

Use deterministic offline tests first. Real protocol validation later needs explicitly authorized, opt-in, isolated local Monero development harnesses, pinned engine/server versions, and test-only synthetic wallets. No production daemon, user wallet, real recovery seed, or mainnet funds may be used. Bitcoin regtest/signet assumptions do not select a Monero chain or harness. Each implemented path must be exercised through the highest practical app/domain/service/repository boundary; helper tests alone cannot establish working wallet behavior.

## Decisions required before operational implementation

1. Complete the vault through its separately authorized canonical sequence after the two-screen UI pass. Harmless preferences and Monero integration-network/harness policy remain unresolved; UI simplification adds neither persistence nor network use.
2. Select an engine and bridge only after Android/Linux packaging, lifecycle, licensing, maintenance, and native storage-isolation evidence. Embedding versus subprocess remains open.
3. Decide whether Skald will ever bundle/manage `monerod`; the current default target only connects to user-selected infrastructure.
4. Design canonical record/export or subordinate-blob ownership, segmentation, encryption, commit consistency, and crash recovery without bypassing the vault sequence.
5. Select recovery formats/languages, restore context, watch-only scope, account/subaddress recovery, transaction-key retention, and metadata backup semantics.
6. Decide endpoint normalization and transport exceptions, TLS/VPN/onion policy, credentials, Tor integration, and Android background lifecycle.
7. Pin and review LWS protocol/version, scan-key scope, subaddresses, outgoing/spent completeness, key-image exchange, consent lifetime, and disclosure history.
8. Select checked atomic-XMR representation/bounds/display parsing and Monero output-selection/privacy, review, offline-signing, and relay policies.

These operational questions remain outside the removal pass. Follow the [accepted sequence](MONERO_PIVOT_MIGRATION_MAP.md): the two-screen presentation pass followed by separately scoped canonical vault completion. The completed classifications and inert shell do not authorize engine integration, new storage, provider promotion, or mainnet.

## Evidence limits and source freshness

Protocol distinctions were checked against the linked primary sources on 2026-10-04. The older getmonero wallet-RPC page points to the current Monero Docs site used above. Upstream documentation and candidate repositories are not pinned dependency selections; reverify exact versions, protocol changes, licenses, storage behavior, and platform feasibility before integration. No upstream keys, addresses, credentials, transactions, wallet blobs, or command fixtures have been copied into this document.

The removal pass adds inert presentation and disabled Monero policy placeholders while retiring the former protocol graph. It does not claim Monero protocol validation, a production vault implementation, or tested engine compatibility. The pinned path inventory describes the original audited tree; local final reports record the changed tree and actual verification. Future operational boundaries remain proposed architecture.
