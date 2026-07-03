# Encrypted Local Vault Test-Only Provider Synthetic Identity Namespace

This document records the still-disabled, model-only namespace contract for future test-only provider identity labels.

The identity decision models future test-only provider identity categories. The identity isolation guard proves those categories remain label-only and unreachable from runtime paths. This namespace contract is a separate label-hygiene boundary: it defines the only synthetic prefix and family tokens that a later branch may use for candidate labels, and it rejects aliases that could be confused with production providers, dependency candidates, registry keys, factory inputs, dispatcher inputs, executor targets, endpoints, wallet descriptors, secret material, storage paths, BDK wallet state, signing or broadcasting paths, or mainnet authorization.

## Current Status

The namespace contract is modeled and still disabled.

No provider is implemented. No provider identity is instantiable. No registry-selectable identity exists. No factory input, dispatcher input, executor target, KAT executor, provider operation, crypto operation, vault lifecycle operation, storage operation, wallet operation, settings persistence, UI entry point, backend client, signing, broadcasting, Tor transport, Nostr parsing, public endpoint default, or mainnet behavior is added.

Provider selection remains disabled-provider-only through `DisabledVaultCryptoProvider`, and `productionProviderSelectable` remains false.

## Allowed Label Shape

The only allowed namespace prefix for model-only candidate labels is:

```text
skald-test-only-provider-identity-v1-
```

Allowed model-only family tokens are:

- `deterministic-kat`
- `randomized-behavior-kat`
- `platform-runtime-kat`

After the family token, a candidate label must include at least one lowercase ASCII kebab-case purpose token. A syntactically accepted label is accepted only for future review. It does not implement a provider identity, does not create an executor target, and does not authorize provider selection.

## Rejected Aliases And Material Classes

The namespace rejects labels that look like or refer to:

- production, release, promotion, signing, broadcasting, or mainnet paths,
- dependency candidates such as Tink, Bouncy Castle, Lazysodium, IonSpin, BDK, Electrum, or Esplora,
- registry keys, factory inputs, dispatcher inputs, or executor targets,
- endpoint aliases or storage-path aliases,
- passphrases, seeds, mnemonics, private keys, xprv/xpub/nsec tokens, PSBTs, transaction material, salts, nonces, plaintext, ciphertext, AEAD tags, keysets, provider handles, crypto objects, wallet descriptors, backend credentials, wallet labels, UTXO labels, backend observation state, or real wallet addresses.

The contract also rejects path separators, dots, colons, at-signs, URL-like text, endpoint-like text, wallet-address-like text, and 64-hex-looking tokens.

## Non-Authorization Rules

Synthetic namespace evidence cannot authorize:

- provider implementation, instantiation, registration, selection, factory input, dispatcher input, or executor targeting,
- executable provider KATs or provider KAT executor implementation,
- provider operations, randomness, KDF, HKDF, HMAC, AEAD, key generation, or keyset storage,
- vault creation, unlock, session, persistence, secure secret storage, secure metadata storage, storage namespace use, or storage path use,
- production sync, backend clients, BDK wallet state, settings codecs, UI surfaces, signing, broadcasting, Tor transport, Nostr parsing, public endpoint defaults, or mainnet.

Documentation evidence is not authorization. Test-only evidence is not production authorization. Warning-only evidence is not authorization. User consent cannot override missing hard gates. Label syntax acceptance is not authorization.

## Skald Constraints Preserved

This boundary preserves the existing Skald constraints:

- no Skald-operated infrastructure,
- no Skald-managed default backend,
- no real funds,
- no secrets or live wallet data,
- no production crypto provider,
- no production vault persistence,
- no signing or broadcasting,
- mainnet disabled.

## Future Branch Requirement

A later branch would be required before any actual test-only provider identity implementation. That branch would still need explicit source-set placement review, namespace approval, registry/factory/dispatcher/executor-target exclusion proof, vault/storage/wallet/backend/UI exclusion proof, no-wallet-material fixture proof, and mainnet non-authorization proof.

Even if a future label passes this namespace syntax contract, the result remains label-only and non-authorizing.

## Source And Tests

Production model:

```text
composeApp/src/commonMain/kotlin/com/libertasprimordium/skald/security/SkaldVaultV1TestOnlyProviderSyntheticIdentityNamespace.kt
```

Focused tests:

```text
composeApp/src/commonTest/kotlin/com/libertasprimordium/skald/VaultTestOnlyProviderSyntheticIdentityNamespaceTest.kt
composeApp/src/desktopTest/kotlin/com/libertasprimordium/skald/ProductionBackendAdapterSourceGuardTest.kt
```
