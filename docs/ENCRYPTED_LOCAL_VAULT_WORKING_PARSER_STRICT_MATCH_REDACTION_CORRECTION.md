# Encrypted Local Vault Working Parser Strict-Match And Redaction Correction

## Status

This is the `strict-match-redaction-correction-only` pass. It corrects the three current-contract defects recorded by the initial working-parser validation completion audit without adding a new parser, catalog, report hierarchy, format version, or production integration.

The existing parser and synthetic catalog remain v1. Exact full-input matching now requires the existing lowercase prefix, separators, token, and discriminator to match one existing catalog marker with no leading, trailing, concatenated, case-altered, normalized, repaired, or otherwise ignored material. Valid markers with trailing bytes now fail closed, and concatenated valid markers now fail closed.

`EncryptedVaultWorkingParser.toString()` is now explicitly deterministic and redacted. It exposes no object identity, input, vector bytes, path, source location, stack trace, crypto material, or diagnostics payload.

The existing commonTest validation completion audit is reused. After this correction, runtime-derived exact-match checks, all twelve catalog outcomes, input-ownership checks, payload/redaction checks, and the production-call-site absence audit pass with `parserValidationCompletionAuditPassed=true`, `syntheticTestVectorExecutionValidated=true`, and a current-contract blocker count of zero.

## Preserved V1 Contract

- Parser id, kind, version, statuses, classifications, blockers, and accepted classification meanings are unchanged.
- Catalog id, version, enum names, marker values, and catalog outcomes are unchanged.
- The existing five successful synthetic structural vectors retain their classifications and section counts.
- The existing seven non-success vectors retain their exact fail-closed blocker meanings.
- The redacted-diagnostics vector remains non-success and is not a valid parsed vault.
- No new marker catalog or persistent negative-vector fixture set was created.
- Negative mutations are created only at test runtime.
- CommonMain still contains integer byte-code matching only; it contains no complete marker string or fixture byte array and does not decode parser input into a string.
- Caller input remains unmodified, the request keeps its defensive private copy, and results/diagnostics remain byte-free and payload-free.

## Production Boundary

The parser remains compiled into commonMain application artifacts, but no production parser call site, registry, factory, dispatcher, service, repository, adapter, storage integration, UI integration, production parser input, production vault-file parsing, or real-wallet parsing exists.

This correction adds no real vault parser, canonical binary layout, production input-size or streaming policy, authenticated container parsing, writer, serialization, parser/writer round trip, file I/O, directory creation, migration execution, corruption repair, KDF, AEAD, encryption, decryption, authentication execution, key generation, nonce generation, Tink keyset creation or persistence, secure-storage success, secure-metadata success, production persistence, production sync, backend client, production provider selection, signing, broadcasting, UI action, endpoint, or mainnet behavior.

Production selection remains `DisabledVaultCryptoProvider` only and `productionProviderSelectable=false`.

## Separate Future Decisions

The canonical binary layout remains unresolved. Production input-size and streaming policy remain unresolved. Authenticated parsing remains unresolved. Writer admission/implementation, parser/writer round-trip execution, encrypted repository success, secure secret storage success, secure metadata storage success, production sync, and production provider selection remain separately authorized future passes.
