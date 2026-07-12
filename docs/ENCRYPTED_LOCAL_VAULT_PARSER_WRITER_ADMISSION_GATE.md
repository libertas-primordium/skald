# Encrypted Local Vault Parser And Writer Admission Gate

This document records the encrypted-vault-parser-writer-admission-gate-only pass.

## Scope

- Parser-writer-admission-gate-only.
- Adds a commonMain policy/model for future encrypted local vault parser and writer implementation admission.
- Admits later separate branches for parser implementation, writer implementation, parser/writer KAT execution, encrypted repository success, secure secret storage success, secure metadata success, production sync, and production provider selection.
- `vaultParserWriterAdmissionGatePassed=true` is admission evidence only and authorizes only later separate branches.
- `vaultParserImplementationPathAdmitted=true` is not parser implementation.
- `vaultWriterImplementationPathAdmitted=true` is not writer implementation.
- `canonicalSerializationPolicyAdmitted=true` is not serialization.
- `authenticateBeforeParsePolicyAdmitted=true` is not authenticated parser implementation.
- `redactedParserErrorPolicyAdmitted=true` is not parser diagnostics implementation.
- `redactedWriterErrorPolicyAdmitted=true` is not writer diagnostics implementation.
- `parserKatRequirementAdmitted=true` is not parser KAT execution.
- `writerKatRequirementAdmitted=true` is not writer KAT execution.

The parser/writer test-vector admission is documented in [`ENCRYPTED_LOCAL_VAULT_PARSER_WRITER_TEST_VECTOR_ADMISSION.md`](ENCRYPTED_LOCAL_VAULT_PARSER_WRITER_TEST_VECTOR_ADMISSION.md). It consumes this admission gate as evidence and admits only later separate synthetic vector, parser, writer, and parser/writer KAT branches; it does not create vectors, parser inputs, writer outputs, serialized vault bytes, parser/writer implementation, serialization/parsing, file I/O, crypto execution, storage success, production sync, provider selection, UI, endpoints, or mainnet.

The parser/writer implementation scaffold is documented in [`ENCRYPTED_LOCAL_VAULT_PARSER_WRITER_IMPLEMENTATION_SCAFFOLD.md`](ENCRYPTED_LOCAL_VAULT_PARSER_WRITER_IMPLEMENTATION_SCAFFOLD.md). It consumes this gate and the test-vector admission as evidence and adds only disabled parser/writer scaffold surfaces; it does not add a working parser or writer, production vector bytes, production parser inputs, production writer outputs, serialization/parsing, vault bytes, file I/O, crypto execution, storage success, production sync, provider selection, UI, endpoints, or mainnet.

The parser/writer synthetic vector catalog is documented in [`ENCRYPTED_LOCAL_VAULT_PARSER_WRITER_SYNTHETIC_VECTOR_CATALOG.md`](ENCRYPTED_LOCAL_VAULT_PARSER_WRITER_SYNTHETIC_VECTOR_CATALOG.md). It is test-source-only catalog evidence with short synthetic bytes and disabled rejection checks; it does not add production vector bytes, parser/writer implementation, serialization/parsing, vault bytes, file I/O, crypto execution, storage success, production sync, provider selection, UI, endpoints, or mainnet.

## Parser Admission Policy

- Future parser must authenticate before trusting record metadata.
- Future parser must fail closed on unsupported critical features.
- Future parser must fail closed on unknown unsupported format versions.
- Future parser must distinguish parse failure, authentication failure, unsupported version, migration required, corruption suspected, and storage unavailable blockers.
- Future parser diagnostics must be redacted and safe-label-only.
- Future parser must not expose raw bytes, headers, salts, nonces, ciphertext, tags, MACs, hashes, descriptors, addresses, txids, labels, notes, filesystem paths, source locations, stack traces, or provider handles in display/debug output.
- No parser is implemented in this branch.
- No vault bytes are consumed in this branch.

## Writer Admission Policy

- Future writer must produce canonical deterministic structural serialization for non-secret structural fields.
- Future writer must use randomized per-record nonce policy where encryption is involved in later branches.
- Future writer must not write unauthenticated sensitive metadata.
- Future writer must not serialize plaintext secret payloads or plaintext sensitive metadata.
- Future writer must distinguish write failure, storage unavailable, atomic replace unavailable, backup required, migration required, and secure metadata unavailable blockers.
- Future writer diagnostics must be redacted and safe-label-only.
- Future writer must not expose temp filenames, vault paths, raw bytes, headers, salts, nonces, ciphertext, tags, MACs, hashes, descriptors, addresses, txids, labels, notes, filesystem paths, source locations, stack traces, or provider handles in display/debug output.
- No writer is implemented in this branch.
- No vault bytes are produced in this branch.

## Parser/Writer KAT Policy

- Future parser/writer implementation must include public, non-wallet, synthetic test vectors before storage repository success.
- Future vectors must be test-source-only unless separately approved.
- Future vectors must not contain real wallet material, descriptors, addresses, txids, PSBTs, Nostr secrets, Lightning credentials, Cashu proofs, backend credentials, file paths, or support-export payloads.
- Future vector outputs must avoid continuous 64-character hex tokens in production source and docs.
- Future parser/writer KAT execution requires a separate branch.
- No parser/writer KAT is executed in this branch.

## Non-Authorization

- No parser implementation is added.
- No writer implementation is added.
- No serialization is added.
- No parsing is added.
- No vault bytes are produced.
- No vault bytes are consumed.
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
- No production backend clients are added.
- No signing or broadcasting is added.
- No UI action enablement is added.
- No endpoints are added.
- Mainnet remains disabled.

## Future Gates

- Future parser implementation requires a separate branch.
- Future writer implementation requires a separate branch.
- Future parser/writer KAT execution requires a separate branch.
- Future encrypted repository success requires a separate branch.
- Future secure secret storage success requires a separate branch.
- Future secure metadata storage success requires a separate branch.
- Future production sync requires a separate branch.
- Future production provider selection requires a separate branch.

## Material Boundary

The admission model contains safe fixed labels, enums, booleans, counts, and safe failure labels only. It does not contain raw bytes, hex strings, KAT vector bytes, KAT vector hex, public vector bytes, public vector hex, salts, nonces, ciphertext, tags, MACs, hashes, key material, Tink keysets, file paths, directory paths, filesystem paths, SharedPreferences keys, settings storage keys, database names, trace payloads, provider-operation payloads, wallet material, descriptors, addresses, txids, PSBT strings, transaction hex, Nostr nsec values, Lightning credentials, Cashu proofs, backend credentials, provider handles, source locations, stack traces, diagnostics payloads, analytics payloads, crash-report payloads, or support-export payloads.
