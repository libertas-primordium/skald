package com.libertasprimordium.skald.security

private const val MANIFEST_UINT16_MAX = 0xffff
private const val MANIFEST_VAULT_ID_BYTES = 16
private const val MANIFEST_RECORD_ID_BYTES = 16
private const val MANIFEST_HEADER_COMMITMENT_CONTEXT_BYTES = 32
private const val MANIFEST_MAX_CRASH_METADATA_BYTES = 1024
private const val MANIFEST_MAX_RECORD_REFERENCE_BYTES = 128
private const val MANIFEST_MAX_RECORDS = 8

enum class SkaldVaultV1ManifestRejectionReason(val label: String) {
    UnsupportedMagic("unsupported manifest magic"),
    UnsupportedVersion("unsupported manifest version"),
    UnsupportedSuiteId("unsupported provider suite id"),
    UnsupportedPolicyId("unsupported policy id"),
    UnsupportedStorageNamespace("unsupported storage namespace"),
    UnsupportedRecordNamespace("unsupported record namespace"),
    UnsupportedRecordType("unsupported record type"),
    MalformedLength("malformed length"),
    MalformedIntegerValue("malformed integer value"),
    MalformedStringValue("malformed string value"),
    MalformedRecordReference("malformed record reference"),
    MalformedTombstoneState("malformed tombstone state"),
    MalformedManifestSequence("malformed manifest sequence"),
    DuplicateRecordId("duplicate record id"),
    ConflictingLatestCounter("conflicting latest counter for record id"),
    TruncatedInput("truncated input"),
    TrailingBytes("trailing bytes"),
    UnknownField("unknown field in non-extensible section"),
    DuplicatedField("duplicated field in non-extensible section"),
}

sealed class SkaldVaultV1ManifestResult<out T> {
    data class Accepted<out T>(val value: T) : SkaldVaultV1ManifestResult<T>()

    data class Rejected(
        val reason: SkaldVaultV1ManifestRejectionReason,
        val safeMessage: String,
    ) : SkaldVaultV1ManifestResult<Nothing>()
}

data class SkaldVaultV1ManifestRecordEntry(
    val recordId: ByteArray,
    val recordTypeId: String,
    val latestRecordVersionCounter: Long,
    val recordReference: String,
    val tombstone: Boolean,
)

data class SkaldVaultV1Manifest(
    val manifestMagic: String = SkaldVaultV1ManifestFormat.MANIFEST_MAGIC,
    val manifestPolicyId: String = SkaldVaultV1ManifestFormat.MANIFEST_POLICY_ID,
    val manifestPolicyVersion: Int = SkaldVaultV1ManifestFormat.MANIFEST_POLICY_VERSION,
    val vaultId: ByteArray,
    val providerSuiteId: String = SkaldVaultV1HeaderCommitment.PROVIDER_SUITE_ID,
    val headerCommitmentContext: ByteArray,
    val storageNamespace: String = SkaldVaultV1RecordAead.STORAGE_NAMESPACE,
    val recordNamespace: String = SkaldVaultV1ManifestFormat.RECORD_NAMESPACE,
    val manifestSequence: Long,
    val crashRecoveryMetadata: ByteArray,
    val featureFlags: Int = SkaldVaultV1HeaderCommitment.FEATURE_FLAGS,
    val records: List<SkaldVaultV1ManifestRecordEntry>,
)

data class SkaldVaultV1CandidateRecordDescriptor(
    val recordId: ByteArray,
    val recordTypeId: String,
    val recordVersionCounter: Long,
    val recordReference: String? = null,
    val tombstone: Boolean = false,
)

enum class SkaldVaultV1StaleRecordDecisionKind(
    val trustedCurrent: Boolean,
    val pendingManifestUpdate: Boolean,
    val acceptedForRecordUse: Boolean,
    val fullRollbackProtectionClaimed: Boolean,
) {
    CurrentTrusted(
        trustedCurrent = true,
        pendingManifestUpdate = false,
        acceptedForRecordUse = true,
        fullRollbackProtectionClaimed = false,
    ),
    NewerPendingManifestUpdate(
        trustedCurrent = false,
        pendingManifestUpdate = true,
        acceptedForRecordUse = false,
        fullRollbackProtectionClaimed = false,
    ),
    StaleRejected(
        trustedCurrent = false,
        pendingManifestUpdate = false,
        acceptedForRecordUse = false,
        fullRollbackProtectionClaimed = false,
    ),
    RecordTypeConflictRejected(
        trustedCurrent = false,
        pendingManifestUpdate = false,
        acceptedForRecordUse = false,
        fullRollbackProtectionClaimed = false,
    ),
    TombstoneConflictRejected(
        trustedCurrent = false,
        pendingManifestUpdate = false,
        acceptedForRecordUse = false,
        fullRollbackProtectionClaimed = false,
    ),
    UnknownRecordPendingManifestUpdate(
        trustedCurrent = false,
        pendingManifestUpdate = true,
        acceptedForRecordUse = false,
        fullRollbackProtectionClaimed = false,
    ),
    MalformedCandidateRejected(
        trustedCurrent = false,
        pendingManifestUpdate = false,
        acceptedForRecordUse = false,
        fullRollbackProtectionClaimed = false,
    ),
}

data class SkaldVaultV1StaleRecordDecision(
    val kind: SkaldVaultV1StaleRecordDecisionKind,
    val safeMessage: String,
) {
    val trustedCurrent: Boolean
        get() = kind.trustedCurrent

    val pendingManifestUpdate: Boolean
        get() = kind.pendingManifestUpdate

    val acceptedForRecordUse: Boolean
        get() = kind.acceptedForRecordUse

    val fullRollbackProtectionClaimed: Boolean
        get() = kind.fullRollbackProtectionClaimed
}

object SkaldVaultV1ManifestFormat {
    const val MANIFEST_MAGIC = "SKALD-VAULT-V1-MANIFEST"
    const val MANIFEST_POLICY_ID = "skald-vault-v1-manifest-contract-v1"
    const val MANIFEST_POLICY_VERSION = 1
    const val STORAGE_POLICY_ID = "skald-vault-v1-local-manifest-storage-policy-v1"
    const val STORAGE_POLICY_VERSION = 1
    const val RECORD_NAMESPACE = "skald-vault/v1/records"
    const val STALE_RECORD_POLICY_ID = "skald-vault-v1-stale-record-manifest-policy-v1"

    val FIXTURE_HEADER_COMMITMENT_CONTEXT: ByteArray
        get() = manifestHexToBytes("1d09a657d8929444c1e955410b2dcb3bfc0df2d19b128154e17a5fbd751237d5")

    fun vectorFixtureManifest(): SkaldVaultV1Manifest =
        SkaldVaultV1Manifest(
            vaultId = (0x20..0x2f).map { it.toByte() }.toByteArray(),
            headerCommitmentContext = FIXTURE_HEADER_COMMITMENT_CONTEXT,
            manifestSequence = 4,
            crashRecoveryMetadata =
                "skald-vault-v1-manifest-parser-crash-metadata-fixture".encodeToByteArray(),
            records = listOf(
                SkaldVaultV1ManifestRecordEntry(
                    recordId = activeFixtureRecordId(),
                    recordTypeId = SkaldVaultV1RecordType.SensitiveMetadata.typeId,
                    latestRecordVersionCounter = 7,
                    recordReference = "in-memory-manifest-record-0001",
                    tombstone = false,
                ),
                SkaldVaultV1ManifestRecordEntry(
                    recordId = tombstoneFixtureRecordId(),
                    recordTypeId = SkaldVaultV1RecordType.SecretPayload.typeId,
                    latestRecordVersionCounter = 3,
                    recordReference = "in-memory-manifest-record-0002",
                    tombstone = true,
                ),
            ),
        )

    fun activeFixtureRecordId(): ByteArray =
        (0x40..0x4f).map { it.toByte() }.toByteArray()

    fun tombstoneFixtureRecordId(): ByteArray =
        (0x50..0x5f).map { it.toByte() }.toByteArray()

    fun serialize(manifest: SkaldVaultV1Manifest): SkaldVaultV1ManifestResult<ByteArray> {
        validateManifest(manifest)?.let { return rejected(it) }
        return accepted(
            manifestByteList {
                putStringField(1, manifest.manifestMagic)
                putStringField(2, manifest.manifestPolicyId)
                putU16Field(3, manifest.manifestPolicyVersion)
                putBytesField(4, manifest.vaultId)
                putStringField(5, manifest.providerSuiteId)
                putBytesField(6, manifest.headerCommitmentContext)
                putStringField(7, manifest.storageNamespace)
                putStringField(8, manifest.recordNamespace)
                putU64Field(9, manifest.manifestSequence)
                putBytesField(10, manifest.crashRecoveryMetadata)
                putU32Field(11, manifest.featureFlags)
                putBytesField(12, recordsSectionBytes(manifest.records))
            },
        )
    }

    fun parse(bytes: ByteArray): SkaldVaultV1ManifestResult<SkaldVaultV1Manifest> {
        val reader = ManifestByteReader(bytes)
        val manifest = SkaldVaultV1Manifest(
            manifestMagic = when (val result = reader.readStringField(1)) {
                is ManifestReadResult.Ok -> result.value
                is ManifestReadResult.Err -> return rejected(result.reason)
            },
            manifestPolicyId = when (val result = reader.readStringField(2)) {
                is ManifestReadResult.Ok -> result.value
                is ManifestReadResult.Err -> return rejected(result.reason)
            },
            manifestPolicyVersion = when (val result = reader.readU16Field(3)) {
                is ManifestReadResult.Ok -> result.value
                is ManifestReadResult.Err -> return rejected(result.reason)
            },
            vaultId = when (val result = reader.readBytesField(4)) {
                is ManifestReadResult.Ok -> result.value
                is ManifestReadResult.Err -> return rejected(result.reason)
            },
            providerSuiteId = when (val result = reader.readStringField(5)) {
                is ManifestReadResult.Ok -> result.value
                is ManifestReadResult.Err -> return rejected(result.reason)
            },
            headerCommitmentContext = when (val result = reader.readBytesField(6)) {
                is ManifestReadResult.Ok -> result.value
                is ManifestReadResult.Err -> return rejected(result.reason)
            },
            storageNamespace = when (val result = reader.readStringField(7)) {
                is ManifestReadResult.Ok -> result.value
                is ManifestReadResult.Err -> return rejected(result.reason)
            },
            recordNamespace = when (val result = reader.readStringField(8)) {
                is ManifestReadResult.Ok -> result.value
                is ManifestReadResult.Err -> return rejected(result.reason)
            },
            manifestSequence = when (val result = reader.readU64Field(9)) {
                is ManifestReadResult.Ok -> result.value
                is ManifestReadResult.Err -> return rejected(result.reason)
            },
            crashRecoveryMetadata = when (val result = reader.readBytesField(10)) {
                is ManifestReadResult.Ok -> result.value
                is ManifestReadResult.Err -> return rejected(result.reason)
            },
            featureFlags = when (val result = reader.readU32Field(11)) {
                is ManifestReadResult.Ok -> result.value
                is ManifestReadResult.Err -> return rejected(result.reason)
            },
            records = when (val result = parseRecordsSection(reader.readBytesField(12))) {
                is ManifestReadResult.Ok -> result.value
                is ManifestReadResult.Err -> return rejected(result.reason)
            },
        )
        if (reader.hasRemaining()) {
            return rejected(SkaldVaultV1ManifestRejectionReason.TrailingBytes)
        }
        validateManifest(manifest)?.let { return rejected(it) }
        return SkaldVaultV1ManifestResult.Accepted(manifest)
    }

    fun decideRecordState(
        manifest: SkaldVaultV1Manifest,
        candidate: SkaldVaultV1CandidateRecordDescriptor,
    ): SkaldVaultV1StaleRecordDecision {
        validateManifest(manifest)?.let {
            return decision(
                SkaldVaultV1StaleRecordDecisionKind.MalformedCandidateRejected,
                "Manifest evidence is malformed; candidate record is rejected.",
            )
        }
        if (candidate.recordId.size != MANIFEST_RECORD_ID_BYTES ||
            candidate.recordVersionCounter < 0 ||
            SkaldVaultV1RecordType.entries.none { it.typeId == candidate.recordTypeId } ||
            candidate.recordReference?.let { it.encodeToByteArray().size > MANIFEST_MAX_RECORD_REFERENCE_BYTES } == true
        ) {
            return decision(
                SkaldVaultV1StaleRecordDecisionKind.MalformedCandidateRejected,
                "Candidate record descriptor is malformed; it is rejected.",
            )
        }
        val current = manifest.records.singleOrNull { it.recordId.contentEquals(candidate.recordId) }
            ?: return decision(
                SkaldVaultV1StaleRecordDecisionKind.UnknownRecordPendingManifestUpdate,
                "Record id is not present in the trusted local manifest; it remains pending manifest update.",
            )
        if (current.recordTypeId != candidate.recordTypeId) {
            return decision(
                SkaldVaultV1StaleRecordDecisionKind.RecordTypeConflictRejected,
                "Record type conflicts with trusted local manifest state; candidate is rejected.",
            )
        }
        if (current.tombstone != candidate.tombstone) {
            return decision(
                SkaldVaultV1StaleRecordDecisionKind.TombstoneConflictRejected,
                "Record deletion state conflicts with trusted local manifest state; candidate is rejected.",
            )
        }
        if (candidate.recordVersionCounter < current.latestRecordVersionCounter) {
            return decision(
                SkaldVaultV1StaleRecordDecisionKind.StaleRejected,
                "Record version is lower than trusted local manifest state; candidate is stale.",
            )
        }
        if (candidate.recordVersionCounter > current.latestRecordVersionCounter) {
            return decision(
                SkaldVaultV1StaleRecordDecisionKind.NewerPendingManifestUpdate,
                "Record version is newer than trusted local manifest state; it remains pending manifest update.",
            )
        }
        return decision(
            SkaldVaultV1StaleRecordDecisionKind.CurrentTrusted,
            "Record descriptor matches trusted local manifest state.",
        )
    }

    private fun recordsSectionBytes(records: List<SkaldVaultV1ManifestRecordEntry>): ByteArray =
        manifestByteList {
            val orderedRecords = records.sortedWith(::compareRecordIds)
            putU16(orderedRecords.size)
            orderedRecords.forEach { record ->
                putLengthPrefixedBytes(recordEntryBytes(record))
            }
        }

    private fun recordEntryBytes(record: SkaldVaultV1ManifestRecordEntry): ByteArray =
        manifestByteList {
            putBytesField(1, record.recordId)
            putStringField(2, record.recordTypeId)
            putU64Field(3, record.latestRecordVersionCounter)
            putStringField(4, record.recordReference)
            putU16Field(5, if (record.tombstone) 1 else 0)
        }

    private fun parseRecordsSection(
        sectionResult: ManifestReadResult<ByteArray>,
    ): ManifestReadResult<List<SkaldVaultV1ManifestRecordEntry>> {
        return when (sectionResult) {
            is ManifestReadResult.Err -> sectionResult
            is ManifestReadResult.Ok -> {
                val reader = ManifestByteReader(sectionResult.value)
                val count = when (val result = reader.readU16Value()) {
                    is ManifestReadResult.Ok -> result.value
                    is ManifestReadResult.Err -> return result
                }
                if (count > MANIFEST_MAX_RECORDS) {
                    return ManifestReadResult.Err(SkaldVaultV1ManifestRejectionReason.MalformedLength)
                }
                val records = mutableListOf<SkaldVaultV1ManifestRecordEntry>()
                repeat(count) {
                    val entryBytes = when (val result = reader.readLengthPrefixedBytes()) {
                        is ManifestReadResult.Ok -> result.value
                        is ManifestReadResult.Err -> return result
                    }
                    records += when (val result = parseRecordEntry(entryBytes)) {
                        is ManifestReadResult.Ok -> result.value
                        is ManifestReadResult.Err -> return result
                    }
                }
                if (reader.hasRemaining()) {
                    return ManifestReadResult.Err(SkaldVaultV1ManifestRejectionReason.TrailingBytes)
                }
                validateRecords(records)?.let { return ManifestReadResult.Err(it) }
                ManifestReadResult.Ok(records.sortedWith(::compareRecordIds))
            }
        }
    }

    private fun parseRecordEntry(bytes: ByteArray): ManifestReadResult<SkaldVaultV1ManifestRecordEntry> {
        val reader = ManifestByteReader(bytes)
        val record = SkaldVaultV1ManifestRecordEntry(
            recordId = when (val result = reader.readBytesField(1)) {
                is ManifestReadResult.Ok -> result.value
                is ManifestReadResult.Err -> return result
            },
            recordTypeId = when (val result = reader.readStringField(2)) {
                is ManifestReadResult.Ok -> result.value
                is ManifestReadResult.Err -> return result
            },
            latestRecordVersionCounter = when (val result = reader.readU64Field(3)) {
                is ManifestReadResult.Ok -> result.value
                is ManifestReadResult.Err -> return result
            },
            recordReference = when (val result = reader.readStringField(4)) {
                is ManifestReadResult.Ok -> result.value
                is ManifestReadResult.Err -> return result
            },
            tombstone = when (val result = reader.readU16Field(5)) {
                is ManifestReadResult.Ok -> when (result.value) {
                    0 -> false
                    1 -> true
                    else -> return ManifestReadResult.Err(
                        SkaldVaultV1ManifestRejectionReason.MalformedTombstoneState,
                    )
                }
                is ManifestReadResult.Err -> return result
            },
        )
        if (reader.hasRemaining()) {
            return ManifestReadResult.Err(SkaldVaultV1ManifestRejectionReason.TrailingBytes)
        }
        validateRecord(record)?.let { return ManifestReadResult.Err(it) }
        return ManifestReadResult.Ok(record)
    }

    private fun validateManifest(manifest: SkaldVaultV1Manifest): SkaldVaultV1ManifestRejectionReason? {
        if (manifest.manifestMagic != MANIFEST_MAGIC) {
            return SkaldVaultV1ManifestRejectionReason.UnsupportedMagic
        }
        if (manifest.manifestPolicyVersion != MANIFEST_POLICY_VERSION) {
            return SkaldVaultV1ManifestRejectionReason.UnsupportedVersion
        }
        if (manifest.manifestPolicyId != MANIFEST_POLICY_ID) {
            return SkaldVaultV1ManifestRejectionReason.UnsupportedPolicyId
        }
        if (manifest.providerSuiteId != SkaldVaultV1HeaderCommitment.PROVIDER_SUITE_ID) {
            return SkaldVaultV1ManifestRejectionReason.UnsupportedSuiteId
        }
        if (manifest.storageNamespace != SkaldVaultV1RecordAead.STORAGE_NAMESPACE) {
            return SkaldVaultV1ManifestRejectionReason.UnsupportedStorageNamespace
        }
        if (manifest.recordNamespace != RECORD_NAMESPACE) {
            return SkaldVaultV1ManifestRejectionReason.UnsupportedRecordNamespace
        }
        if (manifest.vaultId.size != MANIFEST_VAULT_ID_BYTES ||
            manifest.headerCommitmentContext.size != MANIFEST_HEADER_COMMITMENT_CONTEXT_BYTES ||
            manifest.crashRecoveryMetadata.size > MANIFEST_MAX_CRASH_METADATA_BYTES ||
            manifest.records.size > MANIFEST_MAX_RECORDS
        ) {
            return SkaldVaultV1ManifestRejectionReason.MalformedLength
        }
        if (manifest.manifestSequence < 0) {
            return SkaldVaultV1ManifestRejectionReason.MalformedManifestSequence
        }
        if (manifest.featureFlags != SkaldVaultV1HeaderCommitment.FEATURE_FLAGS) {
            return SkaldVaultV1ManifestRejectionReason.UnsupportedVersion
        }
        listOf(
            manifest.manifestMagic,
            manifest.manifestPolicyId,
            manifest.providerSuiteId,
            manifest.storageNamespace,
            manifest.recordNamespace,
        ).forEach { value ->
            validateAsciiString(value)?.let { return it }
        }
        validateRecords(manifest.records)?.let { return it }
        return null
    }

    private fun validateRecords(
        records: List<SkaldVaultV1ManifestRecordEntry>,
    ): SkaldVaultV1ManifestRejectionReason? {
        val seen = mutableMapOf<String, SkaldVaultV1ManifestRecordEntry>()
        records.forEach { record ->
            validateRecord(record)?.let { return it }
            val key = record.recordId.toManifestHex()
            val existing = seen[key]
            if (existing != null) {
                return if (existing.latestRecordVersionCounter != record.latestRecordVersionCounter) {
                    SkaldVaultV1ManifestRejectionReason.ConflictingLatestCounter
                } else {
                    SkaldVaultV1ManifestRejectionReason.DuplicateRecordId
                }
            }
            seen[key] = record
        }
        return null
    }

    private fun validateRecord(
        record: SkaldVaultV1ManifestRecordEntry,
    ): SkaldVaultV1ManifestRejectionReason? {
        if (record.recordId.size != MANIFEST_RECORD_ID_BYTES) {
            return SkaldVaultV1ManifestRejectionReason.MalformedLength
        }
        if (SkaldVaultV1RecordType.entries.none { it.typeId == record.recordTypeId }) {
            return SkaldVaultV1ManifestRejectionReason.UnsupportedRecordType
        }
        if (record.latestRecordVersionCounter < 0) {
            return SkaldVaultV1ManifestRejectionReason.MalformedIntegerValue
        }
        if (record.recordReference.encodeToByteArray().size !in 1..MANIFEST_MAX_RECORD_REFERENCE_BYTES) {
            return SkaldVaultV1ManifestRejectionReason.MalformedRecordReference
        }
        listOf(record.recordTypeId, record.recordReference).forEach { value ->
            validateAsciiString(value)?.let { return it }
        }
        return null
    }

    private fun validateAsciiString(value: String): SkaldVaultV1ManifestRejectionReason? {
        val bytes = value.encodeToByteArray()
        if (bytes.isEmpty() || bytes.size > MANIFEST_UINT16_MAX) {
            return SkaldVaultV1ManifestRejectionReason.MalformedLength
        }
        if (bytes.any { byte -> byte.toInt() !in 0x20..0x7e }) {
            return SkaldVaultV1ManifestRejectionReason.MalformedStringValue
        }
        return null
    }

    private fun accepted(bytes: ByteArray): SkaldVaultV1ManifestResult<ByteArray> =
        SkaldVaultV1ManifestResult.Accepted(bytes)

    private fun rejected(
        reason: SkaldVaultV1ManifestRejectionReason,
    ): SkaldVaultV1ManifestResult.Rejected =
        SkaldVaultV1ManifestResult.Rejected(
            reason = reason,
            safeMessage = reason.label,
        )

    private fun decision(
        kind: SkaldVaultV1StaleRecordDecisionKind,
        safeMessage: String,
    ): SkaldVaultV1StaleRecordDecision =
        SkaldVaultV1StaleRecordDecision(kind = kind, safeMessage = safeMessage)

    private fun compareRecordIds(
        left: SkaldVaultV1ManifestRecordEntry,
        right: SkaldVaultV1ManifestRecordEntry,
    ): Int {
        val leftId = left.recordId
        val rightId = right.recordId
        val size = minOf(leftId.size, rightId.size)
        repeat(size) { index ->
            val leftByte = leftId[index].toInt() and 0xff
            val rightByte = rightId[index].toInt() and 0xff
            if (leftByte != rightByte) {
                return leftByte - rightByte
            }
        }
        return leftId.size - rightId.size
    }

    private sealed class ManifestReadResult<out T> {
        data class Ok<out T>(val value: T) : ManifestReadResult<T>()
        data class Err(val reason: SkaldVaultV1ManifestRejectionReason) : ManifestReadResult<Nothing>()
    }

    private class ManifestByteReader(private val bytes: ByteArray) {
        private var position: Int = 0
        private var lastFieldId: Int = 0

        fun hasRemaining(): Boolean = position < bytes.size

        fun readStringField(expectedFieldId: Int): ManifestReadResult<String> =
            when (val result = readBytesField(expectedFieldId)) {
                is ManifestReadResult.Err -> result
                is ManifestReadResult.Ok -> {
                    val value = result.value
                    if (value.isEmpty() || value.any { byte -> byte.toInt() !in 0x20..0x7e }) {
                        ManifestReadResult.Err(SkaldVaultV1ManifestRejectionReason.MalformedStringValue)
                    } else {
                        ManifestReadResult.Ok(value.decodeToString())
                    }
                }
            }

        fun readBytesField(expectedFieldId: Int): ManifestReadResult<ByteArray> =
            when (val field = readFieldId(expectedFieldId)) {
                is ManifestReadResult.Err -> field
                is ManifestReadResult.Ok -> readLengthPrefixedBytes()
            }

        fun readU16Field(expectedFieldId: Int): ManifestReadResult<Int> =
            when (val field = readFieldId(expectedFieldId)) {
                is ManifestReadResult.Err -> field
                is ManifestReadResult.Ok -> readU16Value()
            }

        fun readU32Field(expectedFieldId: Int): ManifestReadResult<Int> =
            when (val field = readFieldId(expectedFieldId)) {
                is ManifestReadResult.Err -> field
                is ManifestReadResult.Ok -> readU32Value()
            }

        fun readU64Field(expectedFieldId: Int): ManifestReadResult<Long> =
            when (val field = readFieldId(expectedFieldId)) {
                is ManifestReadResult.Err -> field
                is ManifestReadResult.Ok -> readU64Value()
            }

        fun readLengthPrefixedBytes(): ManifestReadResult<ByteArray> {
            val length = when (val result = readU16Value()) {
                is ManifestReadResult.Ok -> result.value
                is ManifestReadResult.Err -> return result
            }
            if (position + length > bytes.size) {
                return ManifestReadResult.Err(SkaldVaultV1ManifestRejectionReason.TruncatedInput)
            }
            val value = bytes.copyOfRange(position, position + length)
            position += length
            return ManifestReadResult.Ok(value)
        }

        fun readU16Value(): ManifestReadResult<Int> {
            if (position + 2 > bytes.size) {
                return ManifestReadResult.Err(SkaldVaultV1ManifestRejectionReason.TruncatedInput)
            }
            val value = ((bytes[position].toInt() and 0xff) shl 8) or
                (bytes[position + 1].toInt() and 0xff)
            position += 2
            return ManifestReadResult.Ok(value)
        }

        private fun readU32Value(): ManifestReadResult<Int> {
            if (position + 4 > bytes.size) {
                return ManifestReadResult.Err(SkaldVaultV1ManifestRejectionReason.TruncatedInput)
            }
            val value = ((bytes[position].toInt() and 0xff) shl 24) or
                ((bytes[position + 1].toInt() and 0xff) shl 16) or
                ((bytes[position + 2].toInt() and 0xff) shl 8) or
                (bytes[position + 3].toInt() and 0xff)
            position += 4
            if (value < 0) {
                return ManifestReadResult.Err(SkaldVaultV1ManifestRejectionReason.MalformedIntegerValue)
            }
            return ManifestReadResult.Ok(value)
        }

        private fun readU64Value(): ManifestReadResult<Long> {
            if (position + 8 > bytes.size) {
                return ManifestReadResult.Err(SkaldVaultV1ManifestRejectionReason.TruncatedInput)
            }
            var value = 0L
            repeat(8) { index ->
                value = (value shl 8) or (bytes[position + index].toLong() and 0xffL)
            }
            position += 8
            if (value < 0L) {
                return ManifestReadResult.Err(SkaldVaultV1ManifestRejectionReason.MalformedIntegerValue)
            }
            return ManifestReadResult.Ok(value)
        }

        private fun readFieldId(expectedFieldId: Int): ManifestReadResult<Int> {
            val fieldId = when (val result = readU16Value()) {
                is ManifestReadResult.Ok -> result.value
                is ManifestReadResult.Err -> return result
            }
            if (fieldId != expectedFieldId) {
                return if (fieldId <= lastFieldId) {
                    ManifestReadResult.Err(SkaldVaultV1ManifestRejectionReason.DuplicatedField)
                } else {
                    ManifestReadResult.Err(SkaldVaultV1ManifestRejectionReason.UnknownField)
                }
            }
            lastFieldId = fieldId
            return ManifestReadResult.Ok(fieldId)
        }
    }

    private class ManifestByteList {
        private val bytes = mutableListOf<Byte>()

        fun toByteArray(): ByteArray = bytes.toByteArray()

        fun putStringField(fieldId: Int, value: String) {
            putBytesField(fieldId, value.encodeToByteArray())
        }

        fun putBytesField(fieldId: Int, value: ByteArray) {
            putU16(fieldId)
            putLengthPrefixedBytes(value)
        }

        fun putU16Field(fieldId: Int, value: Int) {
            putU16(fieldId)
            putU16(value)
        }

        fun putU32Field(fieldId: Int, value: Int) {
            putU16(fieldId)
            putU32(value)
        }

        fun putU64Field(fieldId: Int, value: Long) {
            putU16(fieldId)
            putU64(value)
        }

        fun putLengthPrefixedBytes(value: ByteArray) {
            putU16(value.size)
            bytes.addAll(value.toList())
        }

        fun putU16(value: Int) {
            require(value in 0..MANIFEST_UINT16_MAX)
            bytes += ((value ushr 8) and 0xff).toByte()
            bytes += (value and 0xff).toByte()
        }

        private fun putU32(value: Int) {
            require(value >= 0)
            bytes += ((value ushr 24) and 0xff).toByte()
            bytes += ((value ushr 16) and 0xff).toByte()
            bytes += ((value ushr 8) and 0xff).toByte()
            bytes += (value and 0xff).toByte()
        }

        private fun putU64(value: Long) {
            require(value >= 0)
            for (shift in 56 downTo 0 step 8) {
                bytes += ((value ushr shift) and 0xff).toByte()
            }
        }
    }

    private fun manifestByteList(block: ManifestByteList.() -> Unit): ByteArray {
        val list = ManifestByteList()
        list.block()
        return list.toByteArray()
    }

    private fun ByteArray.toManifestHex(): String =
        joinToString(separator = "") { byte -> byte.toUByte().toString(16).padStart(2, '0') }

    private fun manifestHexToBytes(hex: String): ByteArray {
        require(hex.length % 2 == 0)
        return ByteArray(hex.length / 2) { index ->
            hex.substring(index * 2, index * 2 + 2).toInt(16).toByte()
        }
    }
}
