# Encrypted Local Vault Parser And Writer Synthetic Vector Catalog

This document records the encrypted-vault-parser-writer-synthetic-vector-catalog-only pass.

## Scope

- Parser-writer-synthetic-vector-catalog-only.
- Adds a commonTest synthetic vector catalog with short in-memory synthetic byte fixtures.
- Adds a commonTest rejection/confinement test for the disabled parser/writer scaffold.
- Adds an Android instrumented test with Android test-source-only synthetic bytes.
- `syntheticVectorCatalogPresent=true` is test-source catalog evidence only.
- `syntheticVectorBytesPresent=true` is allowed only in test source sets.
- `testSourceSyntheticVectorBytesPresent=true` is not production vector authorization.
- `disabledParserRejectedSyntheticBytes=true` is not parser implementation.
- `disabledWriterRejectedWithoutProducingBytes=true` is not writer implementation.

The later working-parser admission gate is documented in [`ENCRYPTED_LOCAL_VAULT_WORKING_PARSER_ADMISSION_GATE.md`](ENCRYPTED_LOCAL_VAULT_WORKING_PARSER_ADMISSION_GATE.md). It admits only a future commonMain in-memory parser branch for synthetic test-source vector execution; it does not add a working parser, parser execution, serialization/parsing, production vector bytes, vault bytes, file I/O, crypto/authentication execution, storage success, writer implementation, production sync, provider selection, UI, endpoints, or mainnet.

The later working-parser synthetic-vector execution branch is documented in [`ENCRYPTED_LOCAL_VAULT_WORKING_PARSER_SYNTHETIC_VECTOR_EXECUTION.md`](ENCRYPTED_LOCAL_VAULT_WORKING_PARSER_SYNTHETIC_VECTOR_EXECUTION.md). It consumes these test-source synthetic bytes only through a minimal in-memory parser and returns redacted Skald-owned results; it does not add production vector bytes, production parser inputs or writer outputs, a writer, production vault bytes, file I/O, crypto/authentication execution, storage success, production sync, provider selection, UI, endpoints, or mainnet.

The [`working-parser validation completion audit`](ENCRYPTED_LOCAL_VAULT_WORKING_PARSER_VALIDATION_COMPLETION_AUDIT.md) reuses this catalog and creates no second catalog or persistent negative fixture set. All twelve catalog outcomes match; exact-match validation remains blocked for suffixed and concatenated runtime-derived inputs, with no parser semantic change.

## Synthetic Vector Status

- Synthetic vector bytes were created only in test source sets.
- The test-source synthetic bytes are short, non-wallet, non-secret, non-network, and test-only.
- The test-source synthetic bytes are not real vault data and do not represent production serialized vault bytes.
- The test-source synthetic bytes are passed only through count/flag request fields to disabled parser/writer scaffolds.
- Disabled parser rejection does not parse, inspect, decode, consume, or expose payload bytes.
- Disabled writer rejection does not serialize, produce, or expose payload bytes.
- No production vector bytes are created.
- No production parser inputs are created.
- No production writer outputs are created.

## Non-Authorization

- No working parser implementation is added.
- No working writer implementation is added.
- No serialization is added.
- No parsing is added.
- No vault bytes are produced by production code.
- No vault bytes are consumed by production code.
- No directory creation is added.
- No file read, write, or delete behavior is added.
- No atomic replace implementation is added.
- No partial-write detection implementation is added.
- No migration implementation is added.
- No migration execution is added.
- No corruption detection is added.
- No corruption repair is added.
- No backup creation is added.
- No rollback is added.
- No KDF execution is added.
- No AEAD execution is added.
- No encryption is added.
- No decryption is added.
- No key generation is added.
- No nonce generation is added.
- No Tink keyset creation or persistence is added.
- No unlock implementation is added.
- No lock implementation is added.
- No runtime session-key handling is added.
- No secure secret storage success path is added.
- No secure metadata storage success path is added.
- No production persistence is added.
- No production sync is added.
- No production provider selection is added.
- No production provider implementation is added.
- No vault persistence is added.
- No production backend clients are added.
- No signing or broadcasting is added.
- No UI action enablement is added.
- No endpoints are added.
- Mainnet remains disabled.

## Future Gates

- Future working parser implementation requires a separate branch.
- Future working writer implementation requires a separate branch.
- Future parser/writer vector execution with working parser/writer requires a separate branch.
- Future encrypted repository success requires a separate branch.
- Future secure secret storage success requires a separate branch.
- Future secure metadata storage success requires a separate branch.
- Future production sync requires a separate branch.
- Future production provider selection requires a separate branch.

## Source Confinement

The catalog source guard proves the synthetic vector catalog class names and byte markers are absent from `commonMain`, `androidMain`, and `desktopMain` production source. The normal source/material guard corpus still excludes `BUILD_HISTORY.md` and `.skald-local/`; docs and README remain documentation scan surfaces only.

## Material Boundary

The catalog records test-source-only synthetic metadata and short synthetic bytes only in test source. It does not add raw production bytes, production parser inputs, production writer outputs, vault container bytes, file paths, directory paths, filesystem paths, storage keys, database names, hashes, MACs, tags, salts, nonces, ciphertext, plaintext, key material, Tink keysets, wallet material, descriptors, addresses, txids, PSBT strings, transaction hex, Nostr nsec values, Lightning credentials, Cashu proofs, backend credentials, provider handles, source locations, stack traces, diagnostics payloads, analytics payloads, crash-report payloads, or support-export payloads.
