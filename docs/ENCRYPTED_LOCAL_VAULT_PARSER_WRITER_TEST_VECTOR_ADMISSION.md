# Encrypted Local Vault Parser And Writer Test Vector Admission

This document records the encrypted-vault-parser-writer-test-vector-admission-only pass.

## Scope

- Parser-writer-test-vector-admission-only.
- Adds a commonMain policy/model for future parser/writer public synthetic test-vector admission.
- `parserWriterTestVectorAdmissionPassed=true` is test-vector admission evidence only and authorizes only later separate branches.
- `futureSyntheticVectorCreationAdmitted=true` is not vector creation in this branch.
- `futureInMemoryVectorExecutionAdmitted=true` is not vector execution in this branch.
- `futureParserVectorCoverageAdmitted=true` is not parser implementation.
- `futureWriterVectorCoverageAdmitted=true` is not writer implementation.
- `futureRoundTripVectorCoverageAdmitted=true` is not round-trip execution.
- `futureNegativeVectorCoverageAdmitted=true` is not negative-vector execution.
- `futureRedactedVectorDiagnosticsAdmitted=true` is not diagnostics implementation.

The parser/writer implementation scaffold is documented in [`ENCRYPTED_LOCAL_VAULT_PARSER_WRITER_IMPLEMENTATION_SCAFFOLD.md`](ENCRYPTED_LOCAL_VAULT_PARSER_WRITER_IMPLEMENTATION_SCAFFOLD.md). It consumes this admission as evidence and adds only commonMain interfaces, request/result/blocker models, redacted diagnostics, and disabled fail-closed parser/writer scaffolds; it creates no synthetic bytes, executes no vectors, implements no working parser/writer, serializes or parses nothing, produces or consumes no vault bytes, performs no file I/O or crypto execution, and enables no storage, sync, provider selection, UI, endpoint, or mainnet behavior.

The test-source synthetic vector catalog is documented in [`ENCRYPTED_LOCAL_VAULT_PARSER_WRITER_SYNTHETIC_VECTOR_CATALOG.md`](ENCRYPTED_LOCAL_VAULT_PARSER_WRITER_SYNTHETIC_VECTOR_CATALOG.md). It creates short synthetic bytes only in test source sets and uses them only to prove disabled parser/writer rejection through safe request counts/flags; it does not add production vector bytes, parser inputs, writer outputs, working parser/writer implementation, serialization/parsing, vault bytes, file I/O, crypto execution, storage success, sync, provider selection, UI, endpoint, or mainnet behavior.

The working-parser admission gate is documented in [`ENCRYPTED_LOCAL_VAULT_WORKING_PARSER_ADMISSION_GATE.md`](ENCRYPTED_LOCAL_VAULT_WORKING_PARSER_ADMISSION_GATE.md). It admits only a later commonMain in-memory parser branch that may use synthetic test-source vectors after separate authorization; it does not add parser execution, a working parser, a working writer, serialization/parsing, vault bytes, file I/O, crypto/authentication execution, storage success, sync, provider selection, UI, endpoint, or mainnet behavior.

The working-parser synthetic-vector execution branch is documented in [`ENCRYPTED_LOCAL_VAULT_WORKING_PARSER_SYNTHETIC_VECTOR_EXECUTION.md`](ENCRYPTED_LOCAL_VAULT_WORKING_PARSER_SYNTHETIC_VECTOR_EXECUTION.md). It is the scoped parser execution pass for test-source synthetic vectors only and does not authorize writer implementation, production vector bytes, production parser inputs or writer outputs, production vault bytes, file I/O, crypto/authentication execution, storage success, sync, provider selection, UI, endpoints, or mainnet.

## Test Vector Admission Policy

- Future parser/writer implementation may create synthetic in-memory vector bytes only in test source sets after a separate implementation branch.
- Future parser/writer vectors must be synthetic, non-wallet, non-user, non-network, non-secret, and explicitly marked as test vectors.
- Future parser/writer vectors must not contain real wallet material, descriptors, addresses, txids, PSBTs, transaction hex, seed phrases, private keys, Nostr secrets, Lightning credentials, Cashu proofs, backend credentials, file paths, source locations, stack traces, or support-export payloads.
- Future parser/writer vectors may include safe structural labels, safe enum labels, small synthetic counters, and synthetic record-class markers.
- Future parser/writer vectors must not include continuous 64-character hex tokens in production source or docs.
- Future parser/writer vectors must not be placed in commonMain, androidMain, or desktopMain production source unless a later explicit branch authorizes production-safe non-secret fixtures.
- Future parser/writer vector bytes must not appear in `toString`, display, or debug output.
- Future parser/writer vector diagnostics must be safe-label-only and payload-free.
- Future parser/writer vector execution must prove desktop JVM behavior before storage repository success.
- Future parser/writer vector execution must prove Android runtime/instrumented behavior before storage repository success.
- Future parser/writer negative vectors must distinguish unsupported version, unsupported critical feature, parse failure, authentication failure placeholder, corruption suspected, migration required, and storage unavailable blockers.
- This branch creates no vectors and executes no parser/writer KAT.

## Future Synthetic Vector Classes

These are safe enum labels only and do not carry payload values:

- `MinimalHeaderOnlySyntheticVector`
- `HeaderAndKdfSectionSyntheticVector`
- `HeaderKeyEnvelopeDirectorySyntheticVector`
- `SingleRecordEnvelopeSyntheticVector`
- `MultiRecordDirectorySyntheticVector`
- `UnsupportedVersionSyntheticVector`
- `UnknownCriticalFeatureSyntheticVector`
- `TruncatedHeaderSyntheticVector`
- `TruncatedRecordEnvelopeSyntheticVector`
- `RedactedDiagnosticsSyntheticVector`
- `MigrationRequiredSyntheticVector`
- `CorruptionSuspectedSyntheticVector`

## Non-Authorization

- No vectors are created.
- No in-memory vector bytes are created.
- No production vector bytes are created.
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
- No vault persistence is added.
- No production backend clients are added.
- No signing or broadcasting is added.
- No UI action enablement is added.
- No endpoints are added.
- Mainnet remains disabled.

## Future Gates

- Future synthetic parser/writer vector creation requires a separate branch.
- Future parser implementation requires a separate branch.
- Future writer implementation requires a separate branch.
- Future parser/writer KAT execution requires a separate branch.
- Future encrypted repository success requires a separate branch.
- Future secure secret storage success requires a separate branch.
- Future secure metadata storage success requires a separate branch.
- Future production sync requires a separate branch.
- Future production provider selection requires a separate branch.

## Material Boundary

The admission model contains safe fixed labels, enums, booleans, counts, and safe failure labels only. It does not contain raw bytes, hex strings, KAT vector bytes, KAT vector hex, public vector bytes, public vector hex, salts, nonces, ciphertext, tags, MACs, hashes, key material, Tink keysets, parser input bytes, writer output bytes, serialized vault bytes, file paths, directory paths, filesystem paths, SharedPreferences keys, settings storage keys, database names, trace payloads, provider-operation payloads, wallet material, descriptors, addresses, txids, PSBT strings, transaction hex, Nostr nsec values, Lightning credentials, Cashu proofs, backend credentials, provider handles, source locations, stack traces, diagnostics payloads, analytics payloads, crash-report payloads, or support-export payloads.
